package view;
/**
 *
 * @author leandro melo
 */
import javax.swing.JFrame;

import model.Tabuleiro;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import model.StrategyDificuldadeGame;
import model.TipoDificuldade;

@SuppressWarnings("serial")

public class FieldPainel extends JFrame {
    
 private  Tabuleiro tabuleiro;
 int linha = 0;
 int coluna = 0;
 int minas = 0; 
 int opcaoDificuldade = 0;
 

  public void createPainel() {
   
    opcaoDificuldade = Integer.parseInt(JOptionPane.showInputDialog("Insira a dificuldade (1) Easy (10X15),    (2) Normal (20X20),    (3) Difícil (30X30)"));
    TipoDificuldade tipoDificuldade = TipoDificuldade.values()[opcaoDificuldade -1];
   
    StrategyDificuldadeGame dificuldade = tipoDificuldade.obterDificuldade();
      
    int l = dificuldade.linha(linha);
    int c = dificuldade.coluna(coluna);
    int m = dificuldade.minas(minas);
                        
    linha = l;
    coluna = c;
    minas  = m;

    tabuleiro = new Tabuleiro(linha,coluna,minas);
    add( new PainelTabuleiro(tabuleiro));
 
    
    setSize(800, 600);
    setLocationRelativeTo(null); // jframe inicializar no meio da tela
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setVisible(true);
   
  }
  
    public FieldPainel() {
        super("Jogo Campo Minado");
    }
  
}
