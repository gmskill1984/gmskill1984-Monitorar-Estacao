package monitestacao;

import java.io.File;
import java.io.IOException;
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

public class inventExcel {
  Estacao estacao = new Estacao();
  
  public String[] IPEstacaoListG = new String[this.estacao.TamanhoArquivo()];
  
  public String[] NomeEstacaoListG = new String[10];
  
  public String[] localizacaoListG = new String[this.estacao.TamanhoArquivo()];
  
  public String[] serialMicroListG = new String[this.estacao.TamanhoArquivo()];
  
  public String[] fabricanteMicroListG = new String[this.estacao.TamanhoArquivo()];
  
  public String[] modeloMicroListG = new String[this.estacao.TamanhoArquivo()];
  
  public String[] serialMonitorListG = new String[this.estacao.TamanhoArquivo()];
  
  public String[] fabricanteMonitorListG = new String[this.estacao.TamanhoArquivo()];
  
  public String[] modeloMonitorListG = new String[this.estacao.TamanhoArquivo()];
  
  public String[] serialScannerListG = new String[this.estacao.TamanhoArquivo()];
  
  public String[] tipoScannerListG = new String[this.estacao.TamanhoArquivo()];
  
  public String[] tipoEstacaoListG = new String[this.estacao.TamanhoArquivo()];
  
  public String[] patrimonioVwListG = new String[this.estacao.TamanhoArquivo()];
  
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
    System.out.println(this.NomeEstacaoListG[contador]);
  }
  
  public void Criarplanilha() throws IOException, WriteException {
    try {
      WritableWorkbook planilha = Workbook.createWorkbook(new File("c:/InventeQS.xls"));
      WritableSheet aba = planilha.createSheet("ListaAlunos", 0);
      String[] cabecalho = new String[13];
      cabecalho[0] = "IP";
      cabecalho[1] = "Estacao";
      cabecalho[2] = "Localizacao";
      cabecalho[3] = "Fabricante Micro";
      cabecalho[4] = "Modelo Micro";
      cabecalho[5] = "Serial Monitor";
      cabecalho[6] = "Fabricante Monitor";
      cabecalho[7] = "Modelo Monitor";
      cabecalho[8] = "E-mail";
      cabecalho[9] = "Serial Scanner";
      cabecalho[10] = "Tipo Scanner";
      cabecalho[11] = "Tipo Estacao";
      cabecalho[12] = "Patrimonio Vw";
      Colour bckcolor = Colour.DARK_GREEN;
      WritableCellFormat cellFormat = new WritableCellFormat();
      cellFormat.setBackground(bckcolor);
      WritableFont fonte = new WritableFont(WritableFont.ARIAL);
      fonte.setColour(Colour.GOLD);
      cellFormat.setFont((FontRecord)fonte);
      for (int i = 0; i < cabecalho.length; i++) {
        Label label = new Label(i, 0, cabecalho[i]);
        aba.addCell((WritableCell)label);
        WritableCell cell = aba.getWritableCell(i, 0);
        cell.setCellFormat((CellFormat)cellFormat);
      } 
      for (int linha = 1; linha < this.estacao.TamanhoArquivo(); linha++) {
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
