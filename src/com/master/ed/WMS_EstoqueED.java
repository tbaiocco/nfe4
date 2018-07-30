package com.master.ed;

/**
 * Título: WMS_EstoqueED
 * Descrição: Estoques - ED
 * Data da criação: 10/2003
 * Atualizado em: 12/2003
 * Empresa: ÊxitoLogística Mastercom
 * Autor: Carlos Eduardo de Holleben
*/

public class WMS_EstoqueED extends MasterED {

    public WMS_EstoqueED() {
        super();
    }
    public WMS_EstoqueED(String estoque) {
        OID_Estoque = estoque;
    }

  private String OID_Estoque;
  private String OID_Produto_Cliente;

  private String NM_Produto;
  private String CD_Produto;
  private int OID_Produto;

  private String NM_Razao_Social;
  private String NR_CNPJ_CPF;
  private String OID_Pessoa;

  private int OID_Localizacao;
  private double NR_Quantidade;
  private double NR_Quantidade_Devolucao;
  private double NR_Quantidade_Pendente;
  private String NR_Lote_Produto;
  private int OID_Tipo_Estoque;
  private String NM_Tipo_Estoque;
  private String CD_Tipo_Estoque;

  private String oid_Pessoa_Distribuidor;
  private String NR_CNPJ_CPF_Distribuidor;
  private String NM_Distribuidor;
  private String tx_Observacao;

  public String getTx_Observacao() {
	return tx_Observacao;
}
public void setTx_Observacao(String tx_Observacao) {
	this.tx_Observacao = tx_Observacao;
}
public double getNR_Quantidade() {
    return NR_Quantidade;
  }
  public String getOID_Estoque() {
    return OID_Estoque;
  }
  public int getOID_Localizacao() {
    return OID_Localizacao;
  }
  public String getOID_Produto_Cliente() {
    return OID_Produto_Cliente;
  }
  public int getOID_Tipo_Estoque() {
    return OID_Tipo_Estoque;
  }
  public void setNR_Quantidade(double NR_Quantidade) {
    this.NR_Quantidade = NR_Quantidade;
  }
  public void setOID_Estoque(String OID_Estoque) {
    this.OID_Estoque = OID_Estoque;
  }
  public void setOID_Localizacao(int OID_Localizacao) {
    this.OID_Localizacao = OID_Localizacao;
  }
  public void setOID_Produto_Cliente(String OID_Produto_Cliente) {
    this.OID_Produto_Cliente = OID_Produto_Cliente;
  }
  public void setOID_Tipo_Estoque(int OID_Tipo_Estoque) {
    this.OID_Tipo_Estoque = OID_Tipo_Estoque;
  }
  public String getCD_Produto() {
    return CD_Produto;
  }
  public String getNM_Produto() {
    return NM_Produto;
  }
  public String getNM_Razao_Social() {
    return NM_Razao_Social;
  }
  public String getNM_Tipo_Estoque() {
    return NM_Tipo_Estoque;
  }
  public String getNR_CNPJ_CPF() {
    return NR_CNPJ_CPF;
  }
  public String getOID_Pessoa() {
    return OID_Pessoa;
  }
  public int getOID_Produto() {
    return OID_Produto;
  }
  public void setCD_Produto(String CD_Produto) {
    this.CD_Produto = CD_Produto;
  }
  public void setNM_Produto(String NM_Produto) {
    this.NM_Produto = NM_Produto;
  }
  public void setNM_Razao_Social(String NM_Razao_Social) {
    this.NM_Razao_Social = NM_Razao_Social;
  }
  public void setNM_Tipo_Estoque(String NM_Tipo_Estoque) {
    this.NM_Tipo_Estoque = NM_Tipo_Estoque;
  }
  public void setNR_CNPJ_CPF(String NR_CNPJ_CPF) {
    this.NR_CNPJ_CPF = NR_CNPJ_CPF;
  }
  public void setOID_Pessoa(String OID_Pessoa) {
    this.OID_Pessoa = OID_Pessoa;
  }
  public void setOID_Produto(int OID_Produto) {
    this.OID_Produto = OID_Produto;
  }
  public String getCD_Tipo_Estoque() {
    return CD_Tipo_Estoque;
  }
  public void setCD_Tipo_Estoque(String CD_Tipo_Estoque) {
    this.CD_Tipo_Estoque = CD_Tipo_Estoque;
  }

public String getNM_Distribuidor() {
    return NM_Distribuidor;
}
public void setNM_Distribuidor(String distribuidor) {
    NM_Distribuidor = distribuidor;
}
public String getNR_CNPJ_CPF_Distribuidor() {
    return NR_CNPJ_CPF_Distribuidor;
}
public void setNR_CNPJ_CPF_Distribuidor(String distribuidor) {
    NR_CNPJ_CPF_Distribuidor = distribuidor;
}
public String getOid_Pessoa_Distribuidor() {
    return oid_Pessoa_Distribuidor;
}
public void setOid_Pessoa_Distribuidor(String oid_Pessoa_Distribuidor) {
    this.oid_Pessoa_Distribuidor = oid_Pessoa_Distribuidor;
}
public String getNR_Lote_Produto() {
	return NR_Lote_Produto;
}
public void setNR_Lote_Produto(String lote_Produto) {
	NR_Lote_Produto = lote_Produto;
}
public double getNR_Quantidade_Devolucao() {
	return NR_Quantidade_Devolucao;
}
public void setNR_Quantidade_Devolucao(double quantidade_Devolucao) {
	NR_Quantidade_Devolucao = quantidade_Devolucao;
}
public double getNR_Quantidade_Pendente() {
	return NR_Quantidade_Pendente;
}
public void setNR_Quantidade_Pendente(double quantidade_Pendente) {
	NR_Quantidade_Pendente = quantidade_Pendente;
}
}