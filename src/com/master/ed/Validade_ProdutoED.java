package com.master.ed;

/**
 * Título: WMS_LocalizacaoED
 * Descrição: Localizações - ED
 * Data da criação: 10/2003
 * Atualizado em: 12/2003
 * Empresa: ÊxitoLogística Mastercom
 * Autor: Carlos Eduardo de Holleben
*/

public class Validade_ProdutoED extends MasterED {
    
    public Validade_ProdutoED() {
        super();
    }

    public Validade_ProdutoED(long validade_Produto) {
        super();
        OID_Validade_Produto = validade_Produto;
    }
    
  private int oid_Deposito;
  private String nm_Rua;
  private int nr_Endereco;
  private int nr_Apartamento;
  private String oid_Localizacao;
  private String NM_Deposito;
  private String NM_Fantasia;
  private String NM_Razao_Social;
  private String DM_Situacao;
  private String DT_Validade;
  private long NR_Quantidade;
  private String OID_Produto_Cliente;
  private long OID_Validade_Produto;
  private String NR_Lote;
  private String OID_Produto;
  private String OID_Nota_Fiscal;

  public String getOid_Localizacao() {
    return oid_Localizacao;
  }
  public String getNm_Rua() {
    return nm_Rua;
  }
  public int getNr_Apartamento() {
    return nr_Apartamento;
  }
  public int getNr_Endereco() {
    return nr_Endereco;
  }
  public int getOid_Deposito() {
    return oid_Deposito;
  }

  public void setOid_Localizacao(String oid_Localizacao) {
    this.oid_Localizacao = oid_Localizacao;
  }
  public void setNm_Rua(String nm_Rua) {
    this.nm_Rua = nm_Rua;
  }
  public void setNr_Apartamento(int nr_Apartamento) {
    this.nr_Apartamento = nr_Apartamento;
  }
  public void setNr_Endereco(int nr_Endereco) {
    this.nr_Endereco = nr_Endereco;
  }
  public void setOid_Deposito(int oid_Deposito) {
    this.oid_Deposito = oid_Deposito;
  }
  public void setNM_Deposito(String NM_Deposito) {
    this.NM_Deposito = NM_Deposito;
  }
  public String getNM_Deposito() {
    return NM_Deposito;
  }
  public void setNM_Fantasia(String NM_Fantasia) {
    this.NM_Fantasia = NM_Fantasia;
  }
  public String getNM_Fantasia() {
    return NM_Fantasia;
  }
  public void setNM_Razao_Social(String NM_Razao_Social) {
    this.NM_Razao_Social = NM_Razao_Social;
  }
  public String getNM_Razao_Social() {
    return NM_Razao_Social;
  }
  public void setDM_Situacao(String DM_Situacao) {
    this.DM_Situacao = DM_Situacao;
  }
  public String getDM_Situacao() {
    return DM_Situacao;
  }
  public void setNR_Lote(String NR_Lote) {
    this.NR_Lote = NR_Lote;
  }
  public String getNR_Lote() {
    return NR_Lote;
  }
  public void setDT_Validade(String DT_Validade) {
    this.DT_Validade = DT_Validade;
  }
  public String getDT_Validade() {
    return DT_Validade;
  }
  public void setNR_Quantidade(long NR_Quantidade) {
    this.NR_Quantidade = NR_Quantidade;
  }
  public long getNR_Quantidade() {
    return NR_Quantidade;
  }
  public void setOID_Produto_Cliente(String OID_Produto_Cliente) {
    this.OID_Produto_Cliente = OID_Produto_Cliente;
  }
  public String getOID_Produto_Cliente() {
    return OID_Produto_Cliente;
  }
  public void setOID_Validade_Produto(long OID_Validade_Produto) {
    this.OID_Validade_Produto = OID_Validade_Produto;
  }
  public long getOID_Validade_Produto() {
    return OID_Validade_Produto;
  }
  public void setOID_Produto(String OID_Produto) {
    this.OID_Produto = OID_Produto;
  }
  public String getOID_Produto() {
    return OID_Produto;
  }
  public void setOID_Nota_Fiscal(String OID_Nota_Fiscal) {
    this.OID_Nota_Fiscal = OID_Nota_Fiscal;
  }
  public String getOID_Nota_Fiscal() {
    return OID_Nota_Fiscal;
  }

}