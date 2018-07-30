package com.master.ed;

/**
 * <p>Title: Modelo da Nota Fiscal de parametrização</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Delta Guia </p>
 * @author Claudia Galmarini Welter
 * @version 1.0
 */


public class ModeloNotaFiscalED extends MasterED{

    public ModeloNotaFiscalED() {
        super();
	}
    
    public ModeloNotaFiscalED(long oid_Modelo_Nota_Fiscal) {
        super();
        Oid_Modelo_Nota_Fiscal = oid_Modelo_Nota_Fiscal;
    }
        
    private long Oid_Modelo_Nota_Fiscal;
    private String NM_Nota_Fiscal;
    private String DM_Nota_Fiscal;
    private String TX_Observacao;
 private String CD_Nota_Fiscal;
 private String DM_Aceita_Contabilizacao;
 private long Oid_Sugestao_Contabil;
 private String DM_Aceita_Estoque; //Se faz inserçao em estoque
 private String DM_Estoque_Soma; //Se a inserçao eh de aumento na qtd do produto//Se a inserçao eh de reducao na qtd do produto
 private String DM_Aceita_Escrita; //Se faz inserçao em livro fiscal
 private String DM_Aceita_Financeiro; //Se faz inserçao de compromissos
 private String DM_Aceita_Imobilizado; //Se faz inserçao em estoque imobilizado
 private String DM_INSS; //Se associa contabilizaçao do tributo
 private String DM_IRRF; //Se associa contabilizaçao do tributo
 private String DM_IPI; //Se associa contabilizaçao do tributo
 private String DM_ISQN; //Se associa contabilizaçao do tributo
 private String DM_ICMS; //Se associa contabilizaçao do tributo


 public void setCD_Nota_Fiscal(String CD_Nota_Fiscal) {
    this.CD_Nota_Fiscal = CD_Nota_Fiscal;
  }
  public void setDM_Aceita_Contabilizacao(String DM_Aceita_Contabilizacao) {
    this.DM_Aceita_Contabilizacao = DM_Aceita_Contabilizacao;
  }
  public void setDM_Aceita_Escrita(String DM_Aceita_Escrita) {
    this.DM_Aceita_Escrita = DM_Aceita_Escrita;
  }
  public void setDM_Aceita_Estoque(String DM_Aceita_Estoque) {
    this.DM_Aceita_Estoque = DM_Aceita_Estoque;
  }
  public void setDM_Aceita_Financeiro(String DM_Aceita_Financeiro) {
    this.DM_Aceita_Financeiro = DM_Aceita_Financeiro;
  }
  public void setDM_Aceita_Imobilizado(String DM_Aceita_Imobilizado) {
    this.DM_Aceita_Imobilizado = DM_Aceita_Imobilizado;
  }
  public void setDM_Estoque_Soma(String DM_Estoque_Soma) {
    this.DM_Estoque_Soma = DM_Estoque_Soma;
  }
  public void setDM_ICMS(String DM_ICMS) {
    this.DM_ICMS = DM_ICMS;
  }
  public void setDM_INSS(String DM_INSS) {
    this.DM_INSS = DM_INSS;
  }
  public void setDM_IPI(String DM_IPI) {
    this.DM_IPI = DM_IPI;
  }
  public void setDM_IRRF(String DM_IRRF) {
    this.DM_IRRF = DM_IRRF;
  }
  public void setDM_ISQN(String DM_ISQN) {
    this.DM_ISQN = DM_ISQN;
  }
  public void setNM_Nota_Fiscal(String NM_Nota_Fiscal) {
    this.NM_Nota_Fiscal = NM_Nota_Fiscal;
  }
  public void setOid_Modelo_Nota_Fiscal(long Oid_Modelo_Nota_Fiscal) {
    this.Oid_Modelo_Nota_Fiscal = Oid_Modelo_Nota_Fiscal;
  }
  public void setOid_Sugestao_Contabil(long Oid_Sugestao_Contabil) {
    this.Oid_Sugestao_Contabil = Oid_Sugestao_Contabil;
  }
  public String getCD_Nota_Fiscal() {
    return CD_Nota_Fiscal;
  }
  public String getDM_Aceita_Contabilizacao() {
    return DM_Aceita_Contabilizacao;
  }
  public String getDM_Aceita_Escrita() {
    return DM_Aceita_Escrita;
  }
  public String getDM_Aceita_Estoque() {
    return DM_Aceita_Estoque;
  }
  public String getDM_Aceita_Financeiro() {
    return DM_Aceita_Financeiro;
  }
  public String getDM_Aceita_Imobilizado() {
    return DM_Aceita_Imobilizado;
  }
  public String getDM_Estoque_Soma() {
    return DM_Estoque_Soma;
  }
  public String getDM_ICMS() {
    return DM_ICMS;
  }
  public String getDM_INSS() {
    return DM_INSS;
  }
  public String getDM_IPI() {
    return DM_IPI;
  }
  public String getDM_IRRF() {
    return DM_IRRF;
  }
  public String getDM_ISQN() {
    return DM_ISQN;
  }
  public String getNM_Nota_Fiscal() {
    return NM_Nota_Fiscal;
  }
  public long getOid_Modelo_Nota_Fiscal() {
    return Oid_Modelo_Nota_Fiscal;
  }
  public long getOid_Sugestao_Contabil() {
    return Oid_Sugestao_Contabil;
  }

public String getDM_Nota_Fiscal() {
	return DM_Nota_Fiscal;
}

public void setDM_Nota_Fiscal(String nota_Fiscal) {
	DM_Nota_Fiscal = nota_Fiscal;
}

public String getTX_Observacao() {
	return TX_Observacao;
}

public void setTX_Observacao(String observacao) {
	TX_Observacao = observacao;
}

}