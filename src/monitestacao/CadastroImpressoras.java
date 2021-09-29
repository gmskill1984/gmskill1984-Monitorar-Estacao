package monitestacao;



/**
 *
 * @author UUMMSAL
 */
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class CadastroImpressoras extends JFrame {
  private JTextField TF_Estacao;
  
  private JTextField TF_IP;
  
  private JButton btn_ok_cadastro;
  
  private JLabel lbl_Estacao;
  
  private JLabel lbl_cadastro_estacao;
  
  private JLabel lbl_ip;
  
  private JLabel lbl_localizacao;
  
  private JTextField tf_localizacao;
  
  public CadastroImpressoras() {
    initComponents();
  }
  
  private void initComponents() {
    this.lbl_cadastro_estacao = new JLabel();
    this.lbl_ip = new JLabel();
    this.lbl_Estacao = new JLabel();
    this.TF_IP = new JTextField();
    this.TF_Estacao = new JTextField();
    this.btn_ok_cadastro = new JButton();
    this.lbl_localizacao = new JLabel();
    this.tf_localizacao = new JTextField();
    setDefaultCloseOperation(2);
    this.lbl_cadastro_estacao.setFont(new Font("Tahoma", 0, 14));
    this.lbl_cadastro_estacao.setText("Cadastro de  Esta");
    this.lbl_ip.setText("IP : ");
    this.lbl_Estacao.setText("Esta");
    this.btn_ok_cadastro.setText("OK");
    this.btn_ok_cadastro.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            CadastroImpressoras.this.btn_ok_cadastroActionPerformed(evt);
          }
        });
    this.lbl_localizacao.setText("Localizacao");
    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(99, 99, 99).addComponent(this.lbl_cadastro_estacao).addContainerGap(-1, 32767)).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap(23, 32767).addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.lbl_localizacao).addComponent(this.lbl_Estacao).addComponent(this.lbl_ip)).addGap(33, 33, 33).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.TF_IP, -1, 151, 32767).addComponent(this.TF_Estacao).addGroup(layout.createSequentialGroup().addGap(33, 33, 33).addComponent(this.btn_ok_cadastro)).addComponent(this.tf_localizacao)).addGap(97, 97, 97)));
    layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.lbl_cadastro_estacao).addGap(62, 62, 62).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.lbl_ip).addComponent(this.TF_IP, -2, -1, -2)).addGap(21, 21, 21).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.lbl_Estacao).addComponent(this.TF_Estacao, -2, -1, -2)).addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.tf_localizacao, -2, -1, -2).addComponent(this.lbl_localizacao)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 34, 32767).addComponent(this.btn_ok_cadastro).addGap(26, 26, 26)));
    pack();
  }
  
  private void btn_ok_cadastroActionPerformed(ActionEvent evt) {
    File arquivo = new File("estacoes.txt");
    try (FileWriter fw = new FileWriter(arquivo, true)) {
      fw.write(System.getProperty("line.separator"));
      fw.write(this.TF_IP.getText().trim());
      fw.write(" ");
      fw.write(this.TF_Estacao.getText().toUpperCase().trim());
      fw.write("-");
      fw.write(this.tf_localizacao.getText().toUpperCase().trim());
      fw.flush();
    } catch (IOException ex) {
      ex.printStackTrace();
    } 
    dispose();
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
      Logger.getLogger(CadastroImpressoras.class.getName()).log(Level.SEVERE, (String)null, ex);
    } catch (InstantiationException ex) {
      Logger.getLogger(CadastroImpressoras.class.getName()).log(Level.SEVERE, (String)null, ex);
    } catch (IllegalAccessException ex) {
      Logger.getLogger(CadastroImpressoras.class.getName()).log(Level.SEVERE, (String)null, ex);
    } catch (UnsupportedLookAndFeelException ex) {
      Logger.getLogger(CadastroImpressoras.class.getName()).log(Level.SEVERE, (String)null, ex);
    } 
    EventQueue.invokeLater(new Runnable() {
          public void run() {
            (new CadastroImpressoras()).setVisible(true);
          }
        });
  }
}
