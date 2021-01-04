package model;

/**
 *
 * @author leandro melo
 */
import java.util.ArrayList;
import java.util.List;


public class Field {

	private final int linha;
	private final int coluna;
	
	private boolean aberto = false;
	private boolean minado = false;
	private boolean marcado = false;
	
  private List<Field> vizinhos = new ArrayList<>();
  // private List<BiConsumer<Campo, FieldEvent>> obeservadores = new ArrayList<>();
  private List<FieldObserver> observadores = new ArrayList<>();
	
	Field(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
  }
  
  public void registrarObservador(FieldObserver observador) {
    observadores.add(observador);
  }

  private void notificarObservadores(FieldEvent evento) {
    observadores.stream()
      .forEach(o -> o.eventoOcorreu(this, evento));
  }
	
	boolean adicionarVizinho(Field vizinho) {
		boolean linhaDiferente = linha != vizinho.linha;
		boolean colunaDiferente = coluna != vizinho.coluna;
		boolean diagonal = linhaDiferente && colunaDiferente;
		
		int deltaLinha = Math.abs(linha - vizinho.linha);
		int deltaColuna = Math.abs(coluna - vizinho.coluna);
		int deltaGeral = deltaLinha + deltaColuna;
		
		if(deltaGeral == 1 && !diagonal) {
			vizinhos.add(vizinho);
			return true;
		} else if(deltaGeral == 2 && diagonal) {
			vizinhos.add(vizinho);
			return true;
		}
		
		return false;
	}
	
	public void alternarMarcacao() {
		if(!aberto) {
      marcado = !marcado;
      
      if(marcado) {
        notificarObservadores(FieldEvent.MARCAR);
      } else {
        notificarObservadores(FieldEvent.DESMARCAR);
      }
		}
	}
	
	public boolean abrir() {
		
		if(!aberto && !marcado) {
      if(minado) {
        notificarObservadores(FieldEvent.EXPLODIR);
        return true;
      }
      
      setAberto(true);
			
			if(vizinhancaSegura()) {
				vizinhos.forEach(v -> v.abrir());
			}
			
			return true;
		}
		
		return false;
	}
	
	public boolean vizinhancaSegura() {
		return vizinhos.stream().noneMatch(v -> v.minado);
	}
	
	void minar() {
		minado = true;
	}
	
	public boolean isMinado() {
		return minado;
	}
	
	public boolean isMarcado() {
		return marcado;
	}
	
	void setAberto(boolean aberto) {
    this.aberto = aberto;
    
    if(aberto) {
      notificarObservadores(FieldEvent.ABRIR);
    }
	}
	
	public boolean isAberto() {
		return aberto;
	}
	
	public boolean isFechado() {
		return !isAberto();
	}

	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}
	
	boolean objetivoAlcancado() {
		boolean desvendado = !minado && aberto;
		boolean protegido = minado && marcado;
		return desvendado || protegido;
	}
	
	public int minasNaVizinhanca() {
		return (int)vizinhos.stream().filter(v -> v.minado).count();
	}
	
	void reiniciar() {
            aberto = false;
            minado = false;
            marcado = false;
    notificarObservadores(FieldEvent.REINICIAR);
	}
}
