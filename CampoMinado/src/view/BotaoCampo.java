package view;
/**
 *
 * @author leandro melo
 */
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.Field;
import model.FieldEvent;
import model.FieldObserver;

@SuppressWarnings("serial")
public class BotaoCampo extends JButton implements FieldObserver, MouseListener {

  private final Color CAMPO_PADRAO = new Color(100,100,100);
  private final Color BG_MARCAR = new Color(8, 179, 247);
  private final Color CAMPO_EXPLODIR = new Color(255, 66   , 68);
  private final Color TEXTO_VERDE = new Color(0, 100, 0);

  private Field campo;

  public BotaoCampo(Field campo) {
    this.campo = campo;
    setBackground(CAMPO_PADRAO);
    setOpaque(true);
    setBorder(BorderFactory.createBevelBorder(0));

    addMouseListener(this);
    campo.registrarObservador(this);
  }

  @Override
  public void eventoOcorreu(Field campo, FieldEvent evento) {
    switch(evento) {
      case ABRIR:
        aplicarEstiloAbrir();
        break;
      case MARCAR:
        Bandeira();
        break;
      case EXPLODIR:
        aplicarEstiloExplodir();
        break;
      default:
        aplicarEstiloPadrao();
    }

    SwingUtilities.invokeLater(() -> {
      repaint();
      validate();
    }); 

  }

  private void aplicarEstiloPadrao() {
    setBackground(CAMPO_PADRAO);
    setBorder(BorderFactory.createBevelBorder(0));
    setForeground(Color.WHITE);
    setText("");
  }

  private void aplicarEstiloExplodir() {
    setBackground(CAMPO_EXPLODIR);
    setForeground(Color.WHITE);
    setText("X");
  }

  private void Bandeira() {
//    setBackground(BG_MARCAR);
    setForeground(Color.BLACK);
    setText("B");//Indicação de Bomba
  }

  private void aplicarEstiloAbrir() {

    setBorder(BorderFactory.createLineBorder(Color.WHITE));
    
    if(campo.isMinado()) {
      setBackground(CAMPO_EXPLODIR);
      return;
    }

//    setBackground(BG_PADRAO);

//    switch (campo.minasNaVizinhanca()) {
//      case 1:
//        setForeground(TEXTO_VERDE);
//        break;
//      case 2:
//        setForeground(Color.BLUE);
//        break;
//      case 3:
//        setForeground(Color.YELLOW);
//        break;
//      case 4:
//      case 5:
//      case 6:
//        setForeground(Color.RED);
//        break;
//      default:
//        setForeground(Color.PINK);
//    }

    String valor = !campo.vizinhancaSegura() ? campo.minasNaVizinhanca() + "" : "";
    setText(valor); setForeground(Color.WHITE);setBackground(BG_MARCAR);
  }

  // interface dos eventos dos mouses
  @Override
  public void mousePressed(MouseEvent e) {
    if(e.getButton() == 1) {
      // System.out.println("Botão esquerdo");
      campo.abrir();
    } else {
      // System.out.println("Botão direito");
      campo.alternarMarcacao();
    }
  }

  public void mouseClicked(MouseEvent arg0) {}
  public void mouseEntered(MouseEvent arg0) {}
  public void mouseExited(MouseEvent arg0) {}
  public void mouseReleased(MouseEvent arg0) {}
}
