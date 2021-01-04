package view;
/**
 *
 * @author leandro melo
 */
import javax.swing.JFrame;

public class MainWIndow extends JFrame {
    
    public void CreateWindow(){
    
    }
       
    public static void main(String[] args) {
        
//        MainWIndow p = new MainWIndow();
//        p.CreateWindow();
                
        FieldPainel w = new FieldPainel();
        w.createPainel();
       
                

    }
    
}
