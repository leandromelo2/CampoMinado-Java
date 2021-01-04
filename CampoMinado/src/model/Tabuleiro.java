package model;
/**
 *
 * @author leandro melo
 */
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class Tabuleiro implements FieldObserver {

	private final int linhas;
	private final int colunas;
	private final int minas;
	
  private final List<Field> campos = new ArrayList<>();
  private final List<Consumer<ResultadoEvento>> observadores = new ArrayList<>();

	public Tabuleiro(int linhas, int colunas, int minas) {
		this.linhas = linhas;
		this.colunas = colunas;
		this.minas = minas;
		
		gerarCampos();
		associarVizinhos();
		sortearMinas();
  }
  
  public void registrarObservador(Consumer<ResultadoEvento> observador) {
    observadores.add(observador);
  }

  private void notificarObservadores(boolean resultado) {
    observadores.stream()
      .forEach(o -> o.accept(new ResultadoEvento(resultado)));
  }
	
	public void abrir(int linha, int coluna) {
    campos.parallelStream()
    .filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
    .findFirst()
    .ifPresent(c -> c.abrir());
  }
  
  private void mostrarMinas() {
    campos.stream()
      .filter(c -> c.isMinado())
      .filter(c -> !c.isMarcado())
      .forEach(c -> c.setAberto(true));
  }
	
    public void alternarMarcacao(int linha, int coluna) {
		campos.parallelStream()
		.filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
		.findFirst()
		.ifPresent(c -> c.alternarMarcacao());
    }

    private void gerarCampos() {
	for (int l = 0; l < linhas; l++) {
			for (int c = 0; c < colunas; c++) {
        Field campo = new Field(l, c);
        campo.registrarObservador(this);
        campos.add(campo);
			}
		}
    }
	
    private void associarVizinhos() {
		for(Field c1: campos) {
			for(Field c2: campos) {
				c1.adicionarVizinho(c2);
			}
		}
    }	
	
    private void sortearMinas() {
	long minasArmadas = 0;
	Predicate<Field> minado = c -> c.isMinado();
		
            do {
		int aleatorio = (int) (Math.random() * campos.size());

		campos.get(aleatorio).minar();
		minasArmadas = campos.stream().filter(minado).count();
            } while(minasArmadas < minas);
	
    }
	
    public boolean objetivoAlcancado() {
		return campos.stream().allMatch(c -> c.objetivoAlcancado());
    }
	
    public void reiniciar() {
	campos.stream().forEach(c -> c.reiniciar());
	sortearMinas();
    }
    
    public void fechar(){
    
    }
  
  @Override
  public void eventoOcorreu(Field campo, FieldEvent evento) {
    if(evento == FieldEvent.EXPLODIR) {
      mostrarMinas();
      notificarObservadores(false);
    } else if(objetivoAlcancado()) {
      notificarObservadores(true);
    }
  }

  public void paraCadaCampo(Consumer<Field> funcao) {
    campos.forEach(funcao);
  }

  public int getLinhas() {
    return linhas;
  }

  public int getColunas() {
    return colunas;
  }
}
