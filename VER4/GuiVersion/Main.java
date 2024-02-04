package GuiVersion;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

// this is the driver class

public class Main {
    public static void main(String[] args) {
        
    	 try {
            for (javax.swing.UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    	
        GUI view = new GUI();
        new Event(view);
    }
}