package monitestacao;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import jxl.Workbook;
import jxl.biff.FontRecord;
import jxl.format.CellFormat;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class Estacao {
  public String[] ip = new String[TamanhoArquivo()];
  
  public String[] Estacao = new String[TamanhoArquivo()];
  
  public String[] localizacao = new String[TamanhoArquivo()];
  
  public String serialMicroExt = "";
  
  public String modeloMicroExt = "";
  
  public String fabricanteMicroExt = "";
  
  public String serialMonitorExt = "";
  
  public String modeloMonitorExt = "";
  
  public String fabricanteMonitorExt = "";
  
  public String serialScannerExt = "";
  
  public String tipoScannerExt = "";
  
  public String tipoEstacaoExt = "";
  
  public String patrimonioVwExt = "";
  
  public Boolean verificarArquivo = Boolean.valueOf(false);
  
  public int qteEstacoes = 1;
  
  public String[] IPEstacaoListG = new String[TamanhoArquivo()];
  
  public String[] NomeEstacaoListG = new String[TamanhoArquivo()];
  
  public String[] localizacaoListG = new String[TamanhoArquivo()];
  
  public String[] serialMicroListG = new String[TamanhoArquivo()];
  
  public String[] fabricanteMicroListG = new String[TamanhoArquivo()];
  
  public String[] modeloMicroListG = new String[TamanhoArquivo()];
  
  public String[] serialMonitorListG = new String[TamanhoArquivo()];
  
  public String[] fabricanteMonitorListG = new String[TamanhoArquivo()];
  
  public String[] modeloMonitorListG = new String[TamanhoArquivo()];
  
  public String[] serialScannerListG = new String[TamanhoArquivo()];
  
  public String[] tipoScannerListG = new String[TamanhoArquivo()];
  
  public String[] tipoEstacaoListG = new String[TamanhoArquivo()];
  
  public String[] patrimonioVwListG = new String[TamanhoArquivo()];
  
  public void LerArquivoEstacao(String IPEstacao, String NomeEstacao, String localizacao) throws IOException {
    String commandOpen = "net use j: \\\\" + IPEstacao + "\\C$ /user:" + NomeEstacao + "\\administrator eqstaubate";
    Process Abrir = Runtime.getRuntime().exec(commandOpen);
    try {
      Thread.sleep(2000L);
    } catch (Exception e) {}
    File arquivo = new File("j:\\inv\\dados.txt");
    if (arquivo.exists()) {
      this.verificarArquivo = Boolean.valueOf(true);
      try {
        JOptionPane.showMessageDialog(null, "Esta+ NomeEstacao" + "\n IP: +"+ IPEstacao+" +  \n Localiza +"+ localizacao+" +  \n" + filtraDados(arquivo));
      } catch (IOException|java.awt.HeadlessException e) {
        e.printStackTrace();
      } 
      String commandClosed = "net use j: /delete";
      Process Fechar = Runtime.getRuntime().exec(commandClosed);
    } else {
      JOptionPane.showMessageDialog(null, " Esta+ NomeEstacao IP:+ IPEstacao"+ localizacao +  " + localizacao Arquivo nencontrado");
      String commandClosed = "net use j: /delete";
      Process Fechar = Runtime.getRuntime().exec(commandClosed);
    } 
    try {
      Thread.sleep(1000L);
    } catch (Exception e) {}
  }
  
  public void GerarInventario(String IPEstacao, String NomeEstacao, String localizacao, int contador) throws IOException, WriteException, UnknownHostException, InterruptedException {
    String commandOpen = "net use p: \\\\" + IPEstacao + "\\C$ /user:" + NomeEstacao + "\\administrator eqstaubate";
    Process Abrir = Runtime.getRuntime().exec(commandOpen);
    try {
      Thread.sleep(2000L);
    } catch (Exception e) {}
    File arquivo = new File("p:\\inv\\dados.txt");
    inventExcel invent = new inventExcel();
    if (MonitorarEsatacao(IPEstacao) != true) {
      GerarInventExcel(IPEstacao, NomeEstacao, localizacao, " nlocalizado", "nlocalizado", " nlocalizado ", " nlocalizado ", " nlocalizado", "  nlocalizado", " nlocalizado ", " nlocalizado ", "  nlocalizado", "  nlocalizado", contador);
    } else if (arquivo.exists()) {
      try {
        formataDadosInventario(arquivo);
        GerarInventExcel(IPEstacao, NomeEstacao, localizacao, this.serialMicroExt, this.fabricanteMicroExt, this.modeloMicroExt, this.serialMonitorExt, this.fabricanteMonitorExt, this.modeloMonitorExt, this.serialScannerExt, this.tipoScannerExt, this.tipoEstacaoExt, this.patrimonioVwExt, contador);
        String commandClosed = "net use p: /delete";
        Process Fechar = Runtime.getRuntime().exec(commandClosed);
      } catch (IOException ex) {
        ex.printStackTrace();
      } 
    } else {
      GerarInventExcel(IPEstacao, NomeEstacao, localizacao, " nlocalizado", "nlocalizado", " nlocalizado ", " nlocalizado ", " nlocalizado", "  nlocalizado", " nlocalizado ", " nlocalizado ", "  nlocalizado", "  nlocalizado", contador);
      String commandClosed = "net use p: /delete";
      Process Fechar = Runtime.getRuntime().exec(commandClosed);
    } 
  }
  
  public void lerEstacoes() {
    int i = 1;
    File arquivo = new File("estacoes.txt");
    try {
      FileReader fr = new FileReader(arquivo);
      BufferedReader br = new BufferedReader(fr);
      while (br.ready()) {
        String linha = br.readLine();
        int posicaoi = linha.indexOf(" ");
        int posicaof = linha.indexOf("-");
        this.ip[i] = linha.substring(0, posicaoi);
        this.Estacao[i] = linha.substring(posicaoi + 1, posicaof);
        this.localizacao[i] = linha.substring(posicaof, linha.length());
        i++;
        this.qteEstacoes++;
      } 
      br.close();
      fr.close();
    } catch (IOException ex) {
      ex.printStackTrace();
    } 
  }
  
  public int TamanhoArquivo() {
    int tamanhoArquivo = 1;
    File arquivo = new File("estacoes.txt");
    try {
      FileReader fr = new FileReader(arquivo);
      BufferedReader br = new BufferedReader(fr);
      while (br.ready()) {
        String linha = br.readLine();
        int res = linha.indexOf(" ");
        tamanhoArquivo++;
      } 
      br.close();
      fr.close();
    } catch (IOException ex) {
      ex.printStackTrace();
    } 
    return tamanhoArquivo;
  }
  
  public boolean MonitorarEsatacao(String IP) throws UnknownHostException, IOException, InterruptedException {
    InetAddress inet = InetAddress.getByName(IP);
    if (inet.isReachable(3000))
      return true; 
    return false;
  }
  
  public boolean verificarArquivoEstacao(String IPEstacao, String NomeEstacao) throws IOException {
    String commandOpen = "net use m: \\\\" + IPEstacao + "\\C$ /user:" + NomeEstacao + "\\administrator eqstaubate";
    Process Abrir = Runtime.getRuntime().exec(commandOpen);
    try {
      Thread.sleep(1000L);
    } catch (Exception e) {}
    File arquivo = new File("m:\\inv\\dados.txt");
    if (arquivo.exists()) {
      String str = "net use m: /delete";
      Process process = Runtime.getRuntime().exec(str);
      return true;
    } 
    String commandClosed = "net use m: /delete";
    Process Fechar = Runtime.getRuntime().exec(commandClosed);
    return false;
  }
  
  public String filtraDados(File arquivo) throws IOException {
    String serialMicro = "";
    String fabricanteMicro = "";
    String modeloMicro = "";
    String serialMonitor = "";
    String fabricanteMonitor = "";
    String modeloMonitor = "";
    String serialScanner = "";
    String tipoScanner = "";
    String tipoEstacao = "";
    String patrimonioVw = "";
    Scanner in = new Scanner(new FileReader(arquivo));
    while (in.hasNextLine()) {
      String temp = in.nextLine();
      if (temp.contains("_0536")) {
        temp = temp.trim();
        if (temp.contains("01C1") || temp.contains("01c1") || temp.contains("0461")) {
          tipoScanner = "Com Fio";
        } else if (temp.contains("0261")) {
          tipoScanner = "Sem Fio";
        } else {
          tipoScanner = "Desconhecido";
        } 
        serialScanner = temp.substring(31);
        continue;
      } 
      if (temp.contains("SerialMonitor")) {
        serialMonitor = temp.substring(31);
        continue;
      } 
      if (temp.contains("VESA Manufacturer")) {
        fabricanteMonitor = temp.substring(22);
        continue;
      } 
      if (temp.contains("Model Name")) {
        modeloMonitor = temp.substring(12);
        continue;
      } 
      if (temp.contains("SerialMicro")) {
        serialMicro = temp.substring(29);
        continue;
      } 
      if (temp.contains("SystemManufacturer")) {
        fabricanteMicro = temp.substring(36);
        continue;
      } 
      if (temp.contains("SystemProductName")) {
        modeloMicro = temp.substring(35);
        continue;
      } 
      if (temp.contains("Number:")) {
        if (serialMonitor.equals(""))
          serialMonitor = temp.substring(15); 
        continue;
      } 
      if (temp.contains("Number=")) {
        if (serialMicro.equals(""))
          serialMicro = temp.substring(13); 
        continue;
      } 
      if (temp.contains("TipoEstacao")) {
        tipoEstacao = temp.substring(29);
        continue;
      } 
      if (temp.contains("PatrimonioVW"))
        patrimonioVw = temp.substring(30); 
    } 
    String retorno = "Serial Micro: " + serialMicro + "\nModelo Micro: " + modeloMicro + "\nFabricante Micro: " + fabricanteMicro + "\nSerial Monitor: " + serialMonitor + "\nModelo Monitor: " + modeloMonitor + "\nFabricante Monitor: " + fabricanteMonitor + "\nScanner: " + serialScanner + "\nTipo Scanner: " + tipoScanner + "\nTipo Esta" + (tipoEstacao.equals("") ? "Rack Antigo" : tipoEstacao) + (patrimonioVw.equals("") ? "" : ("\nPatrimVW: " + patrimonioVw));
    System.out.print(retorno);
    in.close();
    return retorno;
  }
  
  public String formataDadosInventario(File arquivo) throws IOException {
    String serialMicro = "";
    String fabricanteMicro = "";
    String modeloMicro = "";
    String serialMonitor = "";
    String fabricanteMonitor = "";
    String modeloMonitor = "";
    String serialScanner = "";
    String tipoScanner = "";
    String tipoEstacao = "";
    String patrimonioVw = "";
    Scanner in = new Scanner(new FileReader(arquivo));
    while (in.hasNextLine()) {
      String temp = in.nextLine();
      if (temp.contains("_0536")) {
        temp = temp.trim();
        if (temp.contains("01C1") || temp.contains("0461")) {
          tipoScanner = "Com Fio";
        } else if (temp.contains("0261")) {
          tipoScanner = "Sem Fio";
        } else {
          tipoScanner = "Desconhecido";
        } 
        serialScanner = temp.substring(31);
        System.out.println(temp.substring(31));
        continue;
      } 
      if (temp.contains("SerialMonitor")) {
        serialMonitor = temp.substring(31);
        continue;
      } 
      if (temp.contains("VESA Manufacturer")) {
        fabricanteMonitor = temp.substring(22);
        continue;
      } 
      if (temp.contains("Model Name")) {
        modeloMonitor = temp.substring(12);
        continue;
      } 
      if (temp.contains("SerialMicro")) {
        serialMicro = temp.substring(29);
        continue;
      } 
      if (temp.contains("SystemManufacturer")) {
        fabricanteMicro = temp.substring(36);
        continue;
      } 
      if (temp.contains("SystemProductName")) {
        modeloMicro = temp.substring(35);
        continue;
      } 
      if (temp.contains("Number:")) {
        if (serialMonitor.equals(""))
          serialMonitor = temp.substring(15); 
        continue;
      } 
      if (temp.contains("Number=")) {
        if (serialMicro.equals(""))
          serialMicro = temp.substring(13); 
        continue;
      } 
      if (temp.contains("TipoEstacao")) {
        tipoEstacao = temp.substring(29);
        continue;
      } 
      if (temp.contains("PatrimonioVW"))
        patrimonioVw = temp.substring(30); 
    } 
    String retorno = serialMicro + " " + modeloMicro + " " + fabricanteMicro + " " + serialMonitor + " " + modeloMonitor + " " + fabricanteMonitor + " " + serialScanner + " " + tipoScanner + (tipoEstacao.equals("") ? " Rack Antigo" : tipoEstacao) + (patrimonioVw.equals("") ? "" : patrimonioVw);
    this.serialMicroExt = serialMicro;
    this.modeloMicroExt = modeloMicro;
    this.fabricanteMicroExt = fabricanteMicro;
    this.serialMonitorExt = serialMonitor;
    this.modeloMonitorExt = modeloMonitor;
    this.fabricanteMonitorExt = fabricanteMonitor;
    this.serialScannerExt = serialScanner;
    this.tipoScannerExt = tipoScanner;
    this.tipoEstacaoExt = tipoEstacao.equals("") ? " Rack Antigo" : tipoEstacao;
    this.patrimonioVwExt = patrimonioVw;
    in.close();
    return retorno;
  }
  
  public void GerarInventExcel(String IPEstacao, String NomeEstacao, String localizacao, String serialMicro, String fabricanteMicro, String modeloMicro, String serialMonitor, String fabricanteMonitor, String modeloMonitor, String serialScanner, String tipoScanner, String tipoEstacao, String patrimonioVw, int contador) {
    this.IPEstacaoListG[contador] = IPEstacao;
    this.NomeEstacaoListG[contador] = NomeEstacao;
    this.localizacaoListG[contador] = localizacao;
    this.serialMicroListG[contador] = serialMicro;
    this.fabricanteMicroListG[contador] = fabricanteMicro;
    this.modeloMicroListG[contador] = modeloMicro;
    this.serialMonitorListG[contador] = serialMonitor;
    this.fabricanteMonitorListG[contador] = fabricanteMonitor;
    this.modeloMonitorListG[contador] = modeloMonitor;
    this.serialScannerListG[contador] = serialScanner;
    this.tipoScannerListG[contador] = tipoScanner;
    this.tipoEstacaoListG[contador] = tipoEstacao;
    this.patrimonioVwListG[contador] = patrimonioVw;
  }
  
  public void Criarplanilha() throws IOException, WriteException {
    DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy");
    Date date = new Date();
    String data = dateFormat.format(date);
    String caminho = "c:/InventeQS_";
    JFileChooser file = new JFileChooser();
    int result = file.showOpenDialog(null);
    if (result == 1) {
      caminho = "c:/InventeQS_" + data + ".xls";
    } else {
      caminho = file.getSelectedFile().getPath();
    } 
    try {
      WritableWorkbook planilha = Workbook.createWorkbook(new File(caminho));
      WritableSheet aba = planilha.createSheet("Invetario Eqs", 0);
      String[] cabecalho = new String[13];
      cabecalho[0] = "IP";
      cabecalho[1] = "Estacao";
      cabecalho[2] = "Localiza";
      cabecalho[3] = "Serial Micro";
      cabecalho[4] = "Fabricante Micro";
      cabecalho[5] = "Modelo Micro";
      cabecalho[6] = "Serial Monitor";
      cabecalho[7] = "Fabricante Monitor";
      cabecalho[8] = "Modelo Monitor";
      cabecalho[9] = "Serial Scanner";
      cabecalho[10] = "Tipo Scanner";
      cabecalho[11] = "Tipo Estacao";
      cabecalho[12] = "Patrimonio Vw";
      Colour bckcolor = Colour.GRAY_25;
      WritableCellFormat cellFormat = new WritableCellFormat();
      cellFormat.setBackground(bckcolor);
      WritableFont fonte = new WritableFont(WritableFont.ARIAL);
      fonte.setColour(Colour.BLACK);
      cellFormat.setFont((FontRecord)fonte);
      for (int i = 0; i < cabecalho.length; i++) {
        Label label = new Label(i, 0, cabecalho[i]);
        aba.addCell((WritableCell)label);
        WritableCell cell = aba.getWritableCell(i, 0);
        cell.setCellFormat((CellFormat)cellFormat);
      } 
      for (int linha = 1; linha < TamanhoArquivo(); linha++) {
        Label label = new Label(0, linha, this.IPEstacaoListG[linha]);
        aba.addCell((WritableCell)label);
        label = new Label(1, linha, this.NomeEstacaoListG[linha]);
        aba.addCell((WritableCell)label);
        label = new Label(2, linha, this.localizacaoListG[linha]);
        aba.addCell((WritableCell)label);
        label = new Label(3, linha, this.serialMicroListG[linha]);
        aba.addCell((WritableCell)label);
        label = new Label(4, linha, this.fabricanteMicroListG[linha]);
        aba.addCell((WritableCell)label);
        label = new Label(5, linha, this.modeloMicroListG[linha]);
        aba.addCell((WritableCell)label);
        label = new Label(6, linha, this.serialMonitorListG[linha]);
        aba.addCell((WritableCell)label);
        label = new Label(7, linha, this.fabricanteMonitorListG[linha]);
        aba.addCell((WritableCell)label);
        label = new Label(8, linha, this.modeloMonitorListG[linha]);
        aba.addCell((WritableCell)label);
        label = new Label(9, linha, this.serialScannerListG[linha]);
        aba.addCell((WritableCell)label);
        label = new Label(10, linha, this.tipoScannerListG[linha]);
        aba.addCell((WritableCell)label);
        label = new Label(11, linha, this.tipoEstacaoListG[linha]);
        aba.addCell((WritableCell)label);
        label = new Label(12, linha, this.patrimonioVwListG[linha]);
        aba.addCell((WritableCell)label);
      } 
      planilha.write();
      planilha.close();
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}
