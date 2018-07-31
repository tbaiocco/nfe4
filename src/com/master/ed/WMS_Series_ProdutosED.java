package com.master.ed;

/**
 * Título: WMS_Series_ProdutosED
 * Descrição: Series - ED
 * Data da criação: 12/2003
 * Atualizado em: 12/2003
 * Empresa: ÊxitoLogística Mastercom
 * Autor: Carlos Eduardo de Holleben
*/

public class WMS_Series_ProdutosED extends MasterED {

  private int OID_Serie_Produto;
  private int OID_Movimento_Produto;
  private String NR_Fabrica;
  private String NM_Produto;
  private String NM_Razao_Social;
  private String OID_Produto_Cliente;

  public String getNR_Fabrica() {
    return NR_Fabrica;
  }
  public int getOID_Movimento_Produto() {
    return OID_Movimento_Produto;
  }
  public int getOID_Serie_Produto() {
    return OID_Serie_Produto;
  }
  public void setNR_Fabrica(String NR_Fabrica) {
    this.NR_Fabrica = NR_Fabrica;
  }
  public void setOID_Movimento_Produto(int OID_Movimento_Produto) {
    this.OID_Movimento_Produto = OID_Movimento_Produto;
  }
  public void setOID_Serie_Produto(int OID_Serie_Produto) {
    this.OID_Serie_Produto = OID_Serie_Produto;
  }
  public String getNM_Produto() {
    return NM_Produto;
  }
  public String getNM_Razao_Social() {
    return NM_Razao_Social;
  }
  public void setNM_Produto(String NM_Produto) {
    this.NM_Produto = NM_Produto;
  }
  public void setNM_Razao_Social(String NM_Razao_Social) {
    this.NM_Razao_Social = NM_Razao_Social;
  }
  public String getOID_Produto_Cliente() {
    return OID_Produto_Cliente;
  }
  public void setOID_Produto_Cliente(String OID_Produto_Cliente) {
    this.OID_Produto_Cliente = OID_Produto_Cliente;
  }



}