package com.master.ed;

/**
 * <p>Title: Autorizacao_ComprasBD </p>
 * <p>Description: Cadastro, exclus�o e altera��o de Autorizadores. Gerenciamento de autoriza��es
 * de Solicita��es e requisi��es.</p>
 * <p>Copyright: �xitoLog�stica & MasterCOM (c) 2004</p>
 * <p>Company: �xitoLog�stica Consultoria e Sistemas Ltda.</p>
 * @author Te�filo Poletto Baiocco
 * @version 1.0
 */

public class Autorizacao_ComprasED
    extends MasterED implements java.io.Serializable {

  public long oid_Autorizador;
  public long oid_Usuario;
  public long oid_Perfil_Compra;
  public double VL_Alcada;
  public String DT_Vencimento;
  public String NM_Perfil_Compra;
  public String NM_Usuario;

  public String getDT_Vencimento () {
    return DT_Vencimento;
  }

  public void setDT_Vencimento (String vencimento) {
    DT_Vencimento = vencimento;
  }

  public long getOid_Autorizador () {
    return oid_Autorizador;
  }

  public void setOid_Autorizador (long oid_Autorizador) {
    this.oid_Autorizador = oid_Autorizador;
  }

  public long getOid_Perfil_Compra () {
    return oid_Perfil_Compra;
  }

  public void setOid_Perfil_Compra (long oid_Perfil_Compra) {
    this.oid_Perfil_Compra = oid_Perfil_Compra;
  }

  public long getOid_Usuario () {
    return oid_Usuario;
  }

  public void setOid_Usuario (long oid_Usuario) {
    this.oid_Usuario = oid_Usuario;
  }

  public double getVL_Alcada () {
    return VL_Alcada;
  }

  public void setVL_Alcada (double alcada) {
    VL_Alcada = alcada;
  }

  public String getNM_Perfil_Compra () {
    return NM_Perfil_Compra;
  }

  public void setNM_Perfil_Compra (String perfil_Compra) {
    NM_Perfil_Compra = perfil_Compra;
  }

  public String getNM_Usuario () {
    return NM_Usuario;
  }

  public void setNM_Usuario (String usuario) {
    NM_Usuario = usuario;
  }
}
