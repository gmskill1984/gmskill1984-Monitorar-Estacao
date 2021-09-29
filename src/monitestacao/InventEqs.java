 package monitestacao;
import java.awt.EventQueue;
import javax.swing.LayoutStyle;
import java.awt.event.ActionEvent;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.LayoutManager;
import javax.swing.GroupLayout;
import java.net.UnknownHostException;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.File;
import javax.swing.border.Border;
import java.awt.Container;
import javax.swing.JOptionPane;
import jxl.write.WriteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Component;
import javax.swing.JProgressBar;
import java.io.IOException;
import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JFrame;

// 
// Decompiled by Procyon v0.5.36
// 

public class InventEqs extends JFrame
{
    Boolean verificaExecucao;
    int temp;
    int contador;
    private Runnable monitora1;
    private Runnable barra;
    private JMenuItem Add_estacao;
    private JMenuBar Barra_Menu;
    private JMenu Esta\u00e7\u00e3o;
    private JMenu Sobre;
    private JButton btn_gerarInventario;
    private JButton btn_ok_tempo;
    private JComboBox cb_tempo;
    private JFrame jFrame1;
    private JLabel lbl_min;
    private JLabel lbl_tempo_atualizacao;
    private JLabel lbl_titulo;
    private JMenuItem mi_Sobre;
    private JMenuItem mi_atualizar;
    private JMenuItem mi_excluir_alteracao;
    private JMenuItem mi_gerarInventario;
    private JMenuItem mi_sair;
    private JPanel pn_principal;
    private JTextField tf_tempo;
    private JButton[] btnEstacoes;
    
    public InventEqs() throws IOException, UnknownHostException, InterruptedException {
        this.verificaExecucao = false;
        this.temp = 10000;
        this.monitora1 = new Runnable() {
            @Override
            public void run() {
                final Estacao estacao = new Estacao();
                estacao.lerEstacoes();
                int i = 1;
                final Boolean tempo = false;
                while (true) {
                    if (i < estacao.qteEstacoes) {
                        InventEqs.this.btnEstacoes[i].setBorder(BorderFactory.createEtchedBorder(InventEqs.this.temp, Color.BLACK, Color.BLACK));
                        try {
                            if (!estacao.MonitorarEsatacao(estacao.ip[i])) {
                                InventEqs.this.btnEstacoes[i].setEnabled(true);
                                InventEqs.this.btnEstacoes[i].setBackground(Color.red);
                                InventEqs.this.btnEstacoes[i].setBorder(BorderFactory.createLineBorder(Color.red));
                            }
                            else if (estacao.MonitorarEsatacao(estacao.ip[i]) && estacao.verificarArquivoEstacao(estacao.ip[i], estacao.Estacao[i])) {
                                InventEqs.this.btnEstacoes[i].setBorder(BorderFactory.createLineBorder(Color.green));
                                InventEqs.this.btnEstacoes[i].setEnabled(true);
                                InventEqs.this.btnEstacoes[i].setBackground(Color.green);
                            }
                            else {
                                InventEqs.this.btnEstacoes[i].setBorder(BorderFactory.createLineBorder(Color.yellow));
                                InventEqs.this.btnEstacoes[i].setEnabled(true);
                                InventEqs.this.btnEstacoes[i].setBackground(Color.yellow);
                            }
                            if (i + 1 == estacao.TamanhoArquivo()) {
                                InventEqs.this.mi_gerarInventario.setEnabled(true);
                                InventEqs.this.cb_tempo.setVisible(true);
                                InventEqs.this.tf_tempo.setVisible(true);
                                InventEqs.this.btn_ok_tempo.setVisible(true);
                                InventEqs.this.btn_gerarInventario.setVisible(true);
                                Thread.sleep(InventEqs.this.temp);
                            }
                        }
                        catch (IOException evt) {}
                        finally {
                            ++i;
                            continue;
                        }
                    }
                    i = 1;
                }
            }
        };
        this.barra = new Runnable() {
            @Override
            public void run() {
                final Estacao estacao = new Estacao();
                estacao.lerEstacoes();
                final int i = 1;
                final Boolean tempo = false;
                final JFrame f = new JFrame("Inventario");
                final Container content = f.getContentPane();
                final JProgressBar pbar = new JProgressBar();
                f.setLocationRelativeTo(null);
                pbar.setStringPainted(true);
                final Border border = BorderFactory.createTitledBorder("Realizando aguarde...");
                pbar.setBorder(border);
                content.add(pbar, "North");
                f.setSize(300, 100);
                f.setVisible(true);
                InventEqs.this.contador = 1;
                while (InventEqs.this.contador < estacao.TamanhoArquivo()) {
                    try {
                        final int contadorbarra = InventEqs.this.contador + 1;
                        final float total = (float)(contadorbarra * 100 / estacao.TamanhoArquivo());
                        if (estacao.MonitorarEsatacao(estacao.ip[InventEqs.this.contador])) {
                            pbar.setValue((int)Math.floor(total));
                            estacao.GerarInventario(estacao.ip[InventEqs.this.contador], estacao.Estacao[InventEqs.this.contador], estacao.localizacao[InventEqs.this.contador], InventEqs.this.contador);
                        }
                        else {
                            pbar.setValue((int)Math.floor(total));
                            estacao.GerarInventario(estacao.ip[InventEqs.this.contador], estacao.Estacao[InventEqs.this.contador], estacao.localizacao[InventEqs.this.contador], InventEqs.this.contador);
                        }
                    }
                    catch (IOException ex) {
                        Logger.getLogger(InventEqs.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    catch (WriteException ex2) {
                        Logger.getLogger(InventEqs.class.getName()).log(Level.SEVERE, null, (Throwable)ex2);
                    }
                    catch (InterruptedException ex3) {
                        Logger.getLogger(InventEqs.class.getName()).log(Level.SEVERE, null, ex3);
                    }
                    final InventEqs this$0 = InventEqs.this;
                    ++this$0.contador;
                }
                try {
                    estacao.Criarplanilha();
                }
                catch (IOException ex) {
                    Logger.getLogger(InventEqs.class.getName()).log(Level.SEVERE, null, ex);
                }
                catch (WriteException ex2) {
                    Logger.getLogger(InventEqs.class.getName()).log(Level.SEVERE, null, (Throwable)ex2);
                }
                f.dispose();
                JOptionPane.showMessageDialog(null, "Arquivo gerado em C:");
            }
        };
        this.initComponents();
        final File arquivo = new File("estacoes.txt");
        if (!arquivo.exists()) {
            try (final FileWriter fw = new FileWriter(arquivo, true)) {
                final String IP = JOptionPane.showInputDialog(this.rootPane, "IP");
                fw.write(IP);
                fw.write(" ");
                final String NomeEst = JOptionPane.showInputDialog(this.rootPane, "Nome da esta\u00e7\u00e3o");
                fw.write(NomeEst);
                fw.write("-");
                final String Local = JOptionPane.showInputDialog(this.rootPane, "Local da esta\u00e7\u00e3o");
                fw.write(Local);
                fw.flush();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        this.mi_gerarInventario.setEnabled(false);
        this.cb_tempo.setVisible(false);
        this.tf_tempo.setVisible(false);
        this.btn_ok_tempo.setVisible(false);
        this.btn_gerarInventario.setVisible(false);
        final String commandClosed1 = "net use m: /delete";
        final String commandClosed2 = "net use j: /delete";
        final String commandClosed3 = "net use p: /delete";
        try {
            final Process Fechar1 = Runtime.getRuntime().exec(commandClosed1);
            final Process Fechar2 = Runtime.getRuntime().exec(commandClosed2);
            final Process Fechar3 = Runtime.getRuntime().exec(commandClosed3);
        }
        catch (IOException ex2) {
            Logger.getLogger(InventEqs.class.getName()).log(Level.SEVERE, null, ex2);
        }
        this.setSize(920, 1000);
        final Estacao estacao = new Estacao();
        estacao.lerEstacoes();
        this.lbl_titulo.setAlignmentX(0.5f);
        this.btnEstacoes = new JButton[estacao.TamanhoArquivo()];
        for (int i = 1; i < estacao.TamanhoArquivo(); ++i) {
            this.btnEstacoes[i] = new JButton("");
            this.pn_principal.add(this.btnEstacoes[i]);
            this.btnEstacoes[i].setBounds(10, 86 * i, 10 * i, 10);
            if (i > 9 && i <= 18) {
                this.btnEstacoes[i].setBounds(156, 86 * i - 774, 10, 10);
            }
            if (i >= 19 && i <= 27) {
                this.btnEstacoes[i].setBounds(302, 86 * i - 1548, 10, 10);
            }
            if (i >= 28 && i <= 36) {
                this.btnEstacoes[i].setBounds(448, 86 * i - 2322, 10, 10);
            }
            if (i >= 37 && i <= 45) {
                this.btnEstacoes[i].setBounds(594, 86 * i - 3096, 10, 10);
            }
            if (i >= 46 && i <= 54) {
                this.btnEstacoes[i].setBounds(740, 86 * i - 3870, 10, 10);
            }
            if (i >= 55 && i <= 63) {
                this.btnEstacoes[i].setBounds(886, 80 * i - 3840, 10, 10);
            }
            if (i >= 64 && i <= 72) {
                this.btnEstacoes[i].setBounds(1032, 80 * i - 4480, 10, 10);
            }
            if (i >= 73 && i <= 81) {
                this.btnEstacoes[i].setBounds(1178, 80 * i - 5120, 10, 10);
            }
            if (i < 82 || i <= 91) {}
            final ButtonHandler btnacao = new ButtonHandler();
            btnacao.Hostname = estacao.Estacao[i];
            btnacao.ip = estacao.ip[i];
            btnacao.localizacao = estacao.localizacao[i];
            this.btnEstacoes[i].addActionListener(btnacao);
            this.btnEstacoes[i].setSize(130, 70);
            this.btnEstacoes[i].setEnabled(false);
            this.btnEstacoes[i].setText(estacao.Estacao[i]);
            this.btnEstacoes[i].setFont(new Font("Arial", 1, 18));
            this.btnEstacoes[i].setName(estacao.Estacao[i]);
        }
        new Thread(this.monitora1).start();
    }
    
    private void initComponents() {
        this.jFrame1 = new JFrame();
        this.pn_principal = new JPanel();
        this.lbl_titulo = new JLabel();
        this.lbl_tempo_atualizacao = new JLabel();
        this.cb_tempo = new JComboBox();
        this.btn_gerarInventario = new JButton();
        this.tf_tempo = new JTextField();
        this.lbl_min = new JLabel();
        this.btn_ok_tempo = new JButton();
        this.Barra_Menu = new JMenuBar();
        this.Esta\u00e7\u00e3o = new JMenu();
        this.Add_estacao = new JMenuItem();
        this.mi_gerarInventario = new JMenuItem();
        this.mi_atualizar = new JMenuItem();
        this.mi_excluir_alteracao = new JMenuItem();
        this.mi_sair = new JMenuItem();
        this.Sobre = new JMenu();
        this.mi_Sobre = new JMenuItem();
        final GroupLayout jFrame1Layout = new GroupLayout(this.jFrame1.getContentPane());
        this.jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(jFrame1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 400, 32767));
        jFrame1Layout.setVerticalGroup(jFrame1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 300, 32767));
        this.setDefaultCloseOperation(3);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent evt) {
                InventEqs.this.formMouseClicked(evt);
            }
        });
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent evt) {
                InventEqs.this.formWindowClosing(evt);
            }
        });
        this.pn_principal.setBorder(BorderFactory.createEtchedBorder());
        this.lbl_titulo.setFont(new Font("Arial", 1, 48));
        this.lbl_titulo.setText("Monitoramento Estacoes");
        final GroupLayout pn_principalLayout = new GroupLayout(this.pn_principal);
        this.pn_principal.setLayout(pn_principalLayout);
        pn_principalLayout.setHorizontalGroup(pn_principalLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(pn_principalLayout.createSequentialGroup().addGap(188, 188, 188).addComponent(this.lbl_titulo).addContainerGap(252, 32767)));
        pn_principalLayout.setVerticalGroup(pn_principalLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(pn_principalLayout.createSequentialGroup().addComponent(this.lbl_titulo).addContainerGap(560, 32767)));
        this.lbl_tempo_atualizacao.setText("Tempo de atualiza\u00e7\u00e3o  : ");
        this.cb_tempo.setModel(new DefaultComboBoxModel<String>(new String[] { "30 seg", "1 min", "5 min", "10 min" }));
        this.cb_tempo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                InventEqs.this.cb_tempoActionPerformed(evt);
            }
        });
        this.btn_gerarInventario.setText("Gerar Inventario");
        this.btn_gerarInventario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                InventEqs.this.btn_gerarInventarioActionPerformed(evt);
            }
        });
        this.tf_tempo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                InventEqs.this.tf_tempoActionPerformed(evt);
            }
        });
        this.lbl_min.setText("Min");
        this.btn_ok_tempo.setText("inserir");
        this.btn_ok_tempo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                InventEqs.this.btn_ok_tempoActionPerformed(evt);
            }
        });
        this.Esta\u00e7\u00e3o.setText("Estacao");
        this.Add_estacao.setText("Adicionar  Estacao");
        this.Add_estacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                InventEqs.this.Add_estacaoActionPerformed(evt);
            }
        });
        this.Esta\u00e7\u00e3o.add(this.Add_estacao);
        this.mi_gerarInventario.setText("Gerar Inventario");
        this.mi_gerarInventario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                InventEqs.this.mi_gerarInventarioActionPerformed(evt);
            }
        });
        this.Esta\u00e7\u00e3o.add(this.mi_gerarInventario);
        this.mi_atualizar.setText("Atualizar esta\u00e7\u00f5es");
        this.mi_atualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                InventEqs.this.mi_atualizarActionPerformed(evt);
            }
        });
        this.Esta\u00e7\u00e3o.add(this.mi_atualizar);
        this.mi_excluir_alteracao.setText("Excluir esta\u00e7\u00e3o");
        this.mi_excluir_alteracao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                InventEqs.this.mi_excluir_alteracaoActionPerformed(evt);
            }
        });
        this.Esta\u00e7\u00e3o.add(this.mi_excluir_alteracao);
        this.mi_sair.setText("Sair");
        this.mi_sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                InventEqs.this.mi_sairActionPerformed(evt);
            }
        });
        this.Esta\u00e7\u00e3o.add(this.mi_sair);
        this.Barra_Menu.add(this.Esta\u00e7\u00e3o);
        this.Sobre.setText("Sobre");
        this.mi_Sobre.setText("Sobre");
        this.mi_Sobre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                InventEqs.this.mi_SobreActionPerformed(evt);
            }
        });
        this.Sobre.add(this.mi_Sobre);
        this.Barra_Menu.add(this.Sobre);
        this.setJMenuBar(this.Barra_Menu);
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addComponent(this.pn_principal, -1, -1, 32767).addContainerGap()).addGroup(layout.createSequentialGroup().addComponent(this.lbl_tempo_atualizacao).addGap(18, 18, 18).addComponent(this.cb_tempo, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.tf_tempo, -2, 66, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.lbl_min).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.btn_ok_tempo).addGap(133, 133, 133).addComponent(this.btn_gerarInventario).addGap(217, 217, 217)))));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap().addComponent(this.pn_principal, -1, -1, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.lbl_tempo_atualizacao).addComponent(this.cb_tempo, -2, -1, -2).addComponent(this.btn_gerarInventario).addComponent(this.tf_tempo, -2, -1, -2).addComponent(this.lbl_min).addComponent(this.btn_ok_tempo)).addContainerGap()));
        this.pack();
    }
    
    private void formMouseClicked(final MouseEvent evt) {
    }
    
    private void Add_estacaoActionPerformed(final ActionEvent evt) {
        final CadastroImpressoras cadastro = new CadastroImpressoras();
        cadastro.setVisible(true);
    }
    
    private void cb_tempoActionPerformed(final ActionEvent evt) {
        final String tempo = (String)this.cb_tempo.getSelectedItem();
        if (tempo == "30 seg") {
            this.temp = 30000;
            this.lbl_min.setText("30 Segundos");
        }
        if (tempo == "1 min") {
            this.temp = 60000;
            this.lbl_min.setText("1 Minuto");
        }
        if (tempo == "5 min") {
            this.temp = 300000;
            this.lbl_min.setText("5 Minutos");
        }
        if (tempo == "10 min") {
            this.temp = 600000;
            this.lbl_min.setText("10 Minutos");
        }
    }
    
    private void btn_gerarInventarioActionPerformed(final ActionEvent evt) {
        new Thread(this.barra).start();
    }
    
    private void formWindowClosing(final WindowEvent evt) {
        final String commandClosed1 = "net use m: /delete";
        final String commandClosed2 = "net use j: /delete";
        final String commandClosed3 = "net use p: /delete";
        try {
            final Process Fechar1 = Runtime.getRuntime().exec(commandClosed1);
            final Process Fechar2 = Runtime.getRuntime().exec(commandClosed2);
            final Process Fechar3 = Runtime.getRuntime().exec(commandClosed3);
        }
        catch (IOException ex) {
            Logger.getLogger(InventEqs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void mi_gerarInventarioActionPerformed(final ActionEvent evt) {
        new Thread(this.barra).start();
    }
    
    private void mi_SobreActionPerformed(final ActionEvent evt) {
        final Sobre sobre = new Sobre();
        sobre.setLocationRelativeTo(null);
        sobre.setVisible(true);
    }
    
    private void mi_atualizarActionPerformed(final ActionEvent evt) {
        this.dispose();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new InventEqs().setVisible(true);
                }
                catch (IOException ex) {
                    Logger.getLogger(InventEqs.class.getName()).log(Level.SEVERE, null, ex);
                }
                catch (InterruptedException ex2) {
                    Logger.getLogger(InventEqs.class.getName()).log(Level.SEVERE, null, ex2);
                }
            }
        });
    }
    
    private void mi_sairActionPerformed(final ActionEvent evt) {
        this.dispose();
    }
    
    private void mi_excluir_alteracaoActionPerformed(final ActionEvent evt) {
        final String estacao = JOptionPane.showInputDialog(this.rootPane, "Favor inserir ip da esta\u00e7\u00e3o da esta\u00e7\u00e3o");
        final Estacao est = new Estacao();
    }
    
    private void tf_tempoActionPerformed(final ActionEvent evt) {
    }
    
    private void btn_ok_tempoActionPerformed(final ActionEvent evt) {
        final String Dadosusuario = this.tf_tempo.getText();
        final int Tempo = Integer.parseInt(Dadosusuario);
        this.temp = Tempo * 60000;
        this.lbl_min.setText(Dadosusuario + " Minutos ");
        this.tf_tempo.setText("");
    }
    
    public static void main(final String[] args) throws IOException {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new InventEqs().setVisible(true);
                }
                catch (IOException ex) {
                    Logger.getLogger(InventEqs.class.getName()).log(Level.SEVERE, null, ex);
                }
                catch (InterruptedException ex2) {
                    Logger.getLogger(InventEqs.class.getName()).log(Level.SEVERE, null, ex2);
                }
            }
        });
    }
}



