package com.master.ed;

/**
 * <p>Title: Saldo_LoteED </p>
 * <p>Description: Interface de dados para Saldos_Lotes. </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: ExitoLogistica & MasterCOM </p>
 * @author Teofilo Poletto Baiocco
 * @version 1.0
 */

public class Saldo_LoteED extends MasterED{

  private long oid_Saldo;
  private String DT_Saldo;
  private double VL_Saldo;
  private String DT_Inicial;
  private String DT_Final;
  private double VL_Programado;

  public Saldo_LoteED() {
  }

  public String getDT_Saldo() {
    return DT_Saldo;
  }
  public void setDT_Saldo(String saldo) {
    DT_Saldo = saldo;
  }
  public long getOid_Saldo() {
    return oid_Saldo;
  }
  public void setOid_Saldo(long oid_Saldo) {
    this.oid_Saldo = oid_Saldo;
  }
  public double getVL_Saldo() {
    return VL_Saldo;
  }
  public void setVL_Saldo(double saldo) {
    VL_Saldo = saldo;
  }
  public String getDT_Final() {
    return DT_Final;
  }
  public void setDT_Final(String final1) {
    DT_Final = final1;
  }
  public String getDT_Inicial() {
    return DT_Inicial;
  }
  public void setDT_Inicial(String inicial) {
    DT_Inicial = inicial;
  }
  public double getVL_Programado() {
    return VL_Programado;
  }
  public void setVL_Programado(double programado) {
    VL_Programado = programado;
  }
}