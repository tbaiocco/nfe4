package com.master.ed;

import javax.servlet.http.*;

import java.awt.image.*;

public class DuplicataPesquisaED
    extends DuplicataED {

  public DuplicataPesquisaED () {
  }

  public DuplicataPesquisaED (HttpServletResponse response , String nomeRelatorio) {
    super (response , nomeRelatorio);
  }

  private String data_Vencimento_Inicial;
  private String data_Vencimento_Final;
  private String data_Credito_Inicial;
  private String data_Credito_Final;
  private String data_Emissao_Inicial;
  private String data_Emissao_Final;
  private String data_Movimento_Inicial;
  private String data_Movimento_Final;
  private String DM_Relatorio;
  private String DM_Classificar;
  private String DM_Tipo_Impressao_Fatura;
  private String path_Imagens;
  private String DM_Atualiza_Saldo;
  private String OID_Movimento_Duplicata;

  private String Linha_Digitavel;
  private String Codigo_Barra;
  private String Lista_CRT;
  private String Nr_Dcto;
  private String Nosso_Numero;
  private String Ag_Cod;
  private String Carteira;
  private String TX_Instrucoes;
  private BufferedImage img_Barras;
  private String DM_Quebra;
  private String DM_Totaliza_Vencimento;

  private String Nr_Remessa;
  private double VL_Cotacao_Informada;

  private double VL_Credito;
  private double VL_Debito;
  private double VL_Saldo_Inicial;
  private double VL_Saldo;

  private java.util.Collection DiarioDetalhes;
  private String dataRel;
  private String siglaRel;

  private String NM_Praca_Pagamento;
  private String DM_Origem;
  private String DM_Cliente;

  public String getPath_Imagens () {
    return path_Imagens;
  }

  public void setPath_Imagens (String path_Imagens) {
    this.path_Imagens = path_Imagens;
  }

  public String getDM_Tipo_Impressao_Fatura () {
    return DM_Tipo_Impressao_Fatura;
  }

  public void setDM_Tipo_Impressao_Fatura (String tipo_Impressao_Fatura) {
    DM_Tipo_Impressao_Fatura = tipo_Impressao_Fatura;
  }

  public String getData_Vencimento_Inicial () {
    return data_Vencimento_Inicial;
  }

  public void setData_Vencimento_Inicial (String data_Vencimento_Inicial) {
    this.data_Vencimento_Inicial = data_Vencimento_Inicial;
  }

  public void setData_Vencimento_Final (String data_Vencimento_Final) {
    this.data_Vencimento_Final = data_Vencimento_Final;
  }

  public String getData_Vencimento_Final () {
    return data_Vencimento_Final;
  }

  public void setData_Emissao_Inicial (String data_Emissao_Inicial) {
    this.data_Emissao_Inicial = data_Emissao_Inicial;
  }

  public String getData_Emissao_Inicial () {
    return data_Emissao_Inicial;
  }

  public void setData_Emissao_Final (String data_Emissao_Final) {
    this.data_Emissao_Final = data_Emissao_Final;
  }

  public String getData_Emissao_Final () {
    return data_Emissao_Final;
  }

  public void setData_Movimento_Inicial (String data_Movimento_Inicial) {
    this.data_Movimento_Inicial = data_Movimento_Inicial;
  }

  public String getData_Movimento_Inicial () {
    return data_Movimento_Inicial;
  }

  public void setData_Movimento_Final (String data_Movimento_Final) {
    this.data_Movimento_Final = data_Movimento_Final;
  }

  public void setDM_Atualiza_Saldo (String DM_Atualiza_Saldo) {

    this.DM_Atualiza_Saldo = DM_Atualiza_Saldo;
  }

  public String getData_Movimento_Final () {
    return data_Movimento_Final;
  }

  public String getDM_Atualiza_Saldo () {

    return DM_Atualiza_Saldo;
  }

  public void setDM_Relatorio (String DM_Relatorio) {
    this.DM_Relatorio = DM_Relatorio;
  }

  public String getDM_Relatorio () {
    return DM_Relatorio;
  }

  public String getAg_Cod () {
    return Ag_Cod;
  }

  public void setAg_Cod (String ag_Cod) {
    Ag_Cod = ag_Cod;
  }

  public String getCarteira () {
    return Carteira;
  }

  public void setCarteira (String carteira) {
    Carteira = carteira;
  }

  public String getCodigo_Barra () {
    return Codigo_Barra;
  }

  public void setCodigo_Barra (String codigo_Barra) {
    Codigo_Barra = codigo_Barra;
  }

  public String getLinha_Digitavel () {
    return Linha_Digitavel;
  }

  public void setLinha_Digitavel (String linha_Digitavel) {
    Linha_Digitavel = linha_Digitavel;
  }

  public String getLista_CRT () {
    return Lista_CRT;
  }

  public void setLista_CRT (String lista_CRT) {
    Lista_CRT = lista_CRT;
  }

  public String getNosso_Numero () {
    return Nosso_Numero;
  }

  public void setNosso_Numero (String nosso_Numero) {
    Nosso_Numero = nosso_Numero;
  }

  public String getNr_Dcto () {
    return Nr_Dcto;
  }

  public void setNr_Dcto (String nr_Dcto) {
    Nr_Dcto = nr_Dcto;
  }

  public BufferedImage getImg_Barras () {
    return img_Barras;
  }

  public double getVL_Cotacao_Informada () {
    return VL_Cotacao_Informada;
  }

  public String getDM_Totaliza_Vencimento () {
    return DM_Totaliza_Vencimento;
  }

  public String getDM_Quebra () {
    return DM_Quebra;
  }

  public void setImg_Barras (BufferedImage img_Barras) {
    this.img_Barras = img_Barras;
  }

  public void setVL_Cotacao_Informada (double VL_Cotacao_Informada) {
    this.VL_Cotacao_Informada = VL_Cotacao_Informada;
  }

  public void setDM_Totaliza_Vencimento (String DM_Totaliza_Vencimento) {
    this.DM_Totaliza_Vencimento = DM_Totaliza_Vencimento;
  }

  public void setDM_Quebra (String DM_Quebra) {
    this.DM_Quebra = DM_Quebra;
  }

  public String getTX_Instrucoes () {
    return TX_Instrucoes;
  }

  public void setTX_Instrucoes (String instrucoes) {
    TX_Instrucoes = instrucoes;
  }

  public String getNr_Remessa () {
    return Nr_Remessa;
  }

  public void setNr_Remessa (String nr_Remessa) {
    Nr_Remessa = nr_Remessa;
  }

  public double getVL_Credito () {
    return VL_Credito;
  }

  public void setVL_Credito (double credito) {
    VL_Credito = credito;
  }

  public double getVL_Debito () {
    return VL_Debito;
  }

  public void setVL_Debito (double debito) {
    VL_Debito = debito;
  }

  public String getDataRel () {
    return dataRel;
  }

  public void setDataRel (String dataRel) {
    this.dataRel = dataRel;
  }

  public java.util.Collection getDiarioDetalhes () {
    return DiarioDetalhes;
  }

  public void setDiarioDetalhes (java.util.Collection diarioDetalhes) {
    DiarioDetalhes = diarioDetalhes;
  }

  public String getSiglaRel () {
    return siglaRel;
  }

  public String getData_Credito_Final () {
    return data_Credito_Final;
  }

  public String getData_Credito_Inicial () {
    return data_Credito_Inicial;
  }

  public String getDM_Cliente () {
    return DM_Cliente;
  }

  public String getDM_Origem () {
    return DM_Origem;
  }

  public String getDM_Classificar () {
    return DM_Classificar;
  }

  public void setSiglaRel (String siglaRel) {
    this.siglaRel = siglaRel;
  }

  public void setData_Credito_Final (String data_Credito_Final) {
    this.data_Credito_Final = data_Credito_Final;
  }

  public void setData_Credito_Inicial (String data_Credito_Inicial) {
    this.data_Credito_Inicial = data_Credito_Inicial;
  }

  public void setDM_Cliente (String DM_Cliente) {
    this.DM_Cliente = DM_Cliente;
  }

  public void setDM_Origem (String DM_Origem) {
    this.DM_Origem = DM_Origem;
  }

  public void setDM_Classificar (String DM_Classificar) {
    this.DM_Classificar = DM_Classificar;
  }

  public double getVL_Saldo_Inicial () {
    return VL_Saldo_Inicial;
  }

  public void setVL_Saldo_Inicial (double saldo_Inicial) {
    VL_Saldo_Inicial = saldo_Inicial;
  }

  public double getVL_Saldo () {
    return VL_Saldo;
  }

  public void setVL_Saldo (double saldo) {
    VL_Saldo = saldo;
  }

  public String getNM_Praca_Pagamento () {
    return NM_Praca_Pagamento;
  }

  public void setNM_Praca_Pagamento (String praca_Pagamento) {
    NM_Praca_Pagamento = praca_Pagamento;
  }

public String getOID_Movimento_Duplicata() {
	return OID_Movimento_Duplicata;
}

public void setOID_Movimento_Duplicata(String movimento_Duplicata) {
	OID_Movimento_Duplicata = movimento_Duplicata;
}

}