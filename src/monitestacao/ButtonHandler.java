/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitestacao;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ButtonHandler implements ActionListener {
  public String ip;
  
  public String Hostname;
  
  public String localizacao;
  
  public void actionPerformed(ActionEvent event) {
    JFrame frame = new JFrame();
    frame.setLocationRelativeTo((Component)null);
    JPanel panel = new JPanel();
    Estacao estacao = new Estacao();
    try {
      estacao.LerArquivoEstacao(this.ip, this.Hostname, this.localizacao);
    } catch (IOException ex) {
      Logger.getLogger(ButtonHandler.class.getName()).log(Level.SEVERE, (String)null, ex);
    } 
    frame.add(panel);
  }
}