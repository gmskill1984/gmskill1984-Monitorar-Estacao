/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitestacao;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author UUMMSAL
 */
public class MonitEstacao {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here

            InventEqs invent = new InventEqs();
           invent.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(MonitEstacao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(MonitEstacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
