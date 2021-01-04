package view;
/**
 *
 * @author leandro melo
 */
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import model.Tabuleiro;
import java.awt.GridLayout;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

//@SuppressWarnings("serial")
public class PainelTabuleiro extends JPanel {
    public FieldPainel fieldpainel;

  public PainelTabuleiro(Tabuleiro tabuleiro) {

    setLayout(new GridLayout(tabuleiro.getLinhas(), tabuleiro.getColunas()));

    tabuleiro.paraCadaCampo(c -> add(new BotaoCampo(c)));

    tabuleiro.registrarObservador(e -> {
      SwingUtilities.invokeLater(() -> {
        if(e.isGanhou()) {
          JOptionPane.showMessageDialog(this, "Você Venceu!");
        } else {
          JOptionPane.showMessageDialog(this, "Fim do Jogo!");

        }

         FieldPainel w = new FieldPainel();
         w.createPainel();
//        tabuleiro.reiniciar();
      });

    });

  }

}
