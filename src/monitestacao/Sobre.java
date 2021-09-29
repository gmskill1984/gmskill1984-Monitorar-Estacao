package monitestacao;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;

public class Sobre extends JFrame {
  private JLabel jLabel1;
  
  private JPanel jPanel1;
  
  private JLabel lbl_tituloInventario;
  
  private JLabel lbl_tituloInventario1;
  
  public Sobre() {
    initComponents();
  }
  
  private void initComponents() {
    this.lbl_tituloInventario = new JLabel();
    this.jPanel1 = new JPanel();
    this.jLabel1 = new JLabel();
    this.lbl_tituloInventario1 = new JLabel();
    setDefaultCloseOperation(2);
    this.lbl_tituloInventario.setFont(new Font("Tahoma", 0, 24));
    this.lbl_tituloInventario.setText("Invent eQS ");
    this.jPanel1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
    this.jLabel1.setText("Desenvolvido: Gabriel Sales / Daniel Brinco");
    this.lbl_tituloInventario1.setFont(new Font("Tahoma", 0, 14));
    this.lbl_tituloInventario1.setText("Inventario do sistema eQS  -  Vers1.0");
    GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
    this.jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(35, 35, 35).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.lbl_tituloInventario1).addComponent(this.jLabel1)).addContainerGap(34, 32767)));
    jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(45, 45, 45).addComponent(this.lbl_tituloInventario1).addGap(50, 50, 50).addComponent(this.jLabel1).addContainerGap(70, 32767)));
    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(94, 94, 94).addComponent(this.lbl_tituloInventario).addContainerGap(-1, 32767)).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -1, -1, 32767).addContainerGap()));
    layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.lbl_tituloInventario).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel1, -1, -1, 32767).addGap(23, 23, 23)));
    pack();
  }
  
  public static void main(String[] args) {
    try {
      for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          UIManager.setLookAndFeel(info.getClassName());
          break;
        } 
      } 
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(Sobre.class.getName()).log(Level.SEVERE, (String)null, ex);
    } catch (InstantiationException ex) {
      Logger.getLogger(Sobre.class.getName()).log(Level.SEVERE, (String)null, ex);
    } catch (IllegalAccessException ex) {
      Logger.getLogger(Sobre.class.getName()).log(Level.SEVERE, (String)null, ex);
    } catch (UnsupportedLookAndFeelException ex) {
      Logger.getLogger(Sobre.class.getName()).log(Level.SEVERE, (String)null, ex);
    } 
    EventQueue.invokeLater(new Runnable() {
          public void run() {
            (new Sobre()).setVisible(true);
          }
        });
  }
}
