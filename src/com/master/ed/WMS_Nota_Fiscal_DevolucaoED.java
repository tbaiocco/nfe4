package com.master.ed;

/**
 * Título: WMS_Nota_Fiscal_DevolucaoED
 * Descrição: Notas Fiscais Devolução - ED
 * Data da criação: 03/2004
 * Atualizado em: 03/2004
 * Empresa: ÊxitoLogística Mastercom
 * Autor: Carlos Eduardo de Holleben
*/

public class WMS_Nota_Fiscal_DevolucaoED extends MasterED{

  private int OID_Nota_Fiscal_Devolucao;
  private String OID_Nota_Fiscal_Entrada_Cliente;
  private String OID_Nota_Fiscal_Saida;
  private String OID_Nota_Fiscal_Saida_Cliente;
  private String OID_Item_Saida_Cliente;
  private String OID_Item_Saida;
  private String OID_Item_Entrada_Cliente;
  private int NR_Quantidade;
  private double VL_Produto;
  
  public double getVL_Produto() {
    return VL_Produto;
  }

  public void setVL_Produto(double vl_Produto) {
    this.VL_Produto = vl_Produto;
  }

  public int getOID_Nota_Fiscal_Devolucao() {
    return OID_Nota_Fiscal_Devolucao;
  }

  public void setOID_Nota_Fiscal_Devolucao(int oid_Nota_Fiscal_Devolucao) {
    this.OID_Nota_Fiscal_Devolucao = oid_Nota_Fiscal_Devolucao;
  }
  
  public int getNR_Quantidade() {
    return NR_Quantidade;
  }

  public void setNR_Quantidade(int nr_Quantidade) {
    this.NR_Quantidade = nr_Quantidade;
  }
  
  public String getOID_Nota_Fiscal_Entrada_Cliente() {
    return OID_Nota_Fiscal_Entrada_Cliente;
  }

  public void setOID_Nota_Fiscal_Entrada_Cliente(String oid_Nota_Fiscal_Entrada_Cliente) {
    this.OID_Nota_Fiscal_Entrada_Cliente = oid_Nota_Fiscal_Entrada_Cliente;
  }
  
  public String getOID_Nota_Fiscal_Saida() {
    return OID_Nota_Fiscal_Saida;
  }

  public void setOID_Nota_Fiscal_Saida(String oid_Nota_Fiscal_Saida) {
    this.OID_Nota_Fiscal_Saida = oid_Nota_Fiscal_Saida;
  }
  
  public String getOID_Item_Saida_Cliente() {
    return OID_Item_Saida_Cliente;
  }

  public void setOID_Item_Saida_Cliente(String oid_Item_Saida_Cliente) {
    this.OID_Item_Saida_Cliente = oid_Item_Saida_Cliente;
  }
  
  public String getOID_Nota_Fiscal_Saida_Cliente() {
    return OID_Nota_Fiscal_Saida_Cliente;
  }

  public void setOID_Nota_Fiscal_Saida_Cliente(String oid_Nota_Fiscal_Saida_Cliente) {
    this.OID_Nota_Fiscal_Saida_Cliente = oid_Nota_Fiscal_Saida_Cliente;
  }
  
  public String getOID_Item_Entrada_Cliente() {
    return OID_Item_Entrada_Cliente;
  }

  public void setOID_Item_Entrada_Cliente(String oid_Item_Entrada_Cliente) {
    this.OID_Item_Entrada_Cliente = oid_Item_Entrada_Cliente;
  }
  
  public String getOID_Item_Saida() {
    return OID_Item_Saida;
  }

  public void setOID_Item_Saida(String oid_Item_Saida) {
    this.OID_Item_Saida = oid_Item_Saida;
  }
}