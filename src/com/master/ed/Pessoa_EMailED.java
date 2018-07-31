package com.master.ed;

public class Pessoa_EMailED
    extends MasterED {

  private long oid_evento;

  private String nm_Email_Destino;
  private String nm_Email_Origem;
  private String oid_Pessoa;

  private String cd_Evento;
  private String nm_Evento;
  private String usuario_stamp;
  private long oid_Pessoa_EMail;
  private String nm_Razao_Social;
  private String nm_Usuario;
  private String nm_Usuario_Destino;
  private long oid_Host;
  public String getNm_Email_Origem () {

    return nm_Email_Origem;
  }

  public String getNm_Email_Destino () {

    return nm_Email_Destino;
  }

  public long getOid_evento () {

    return oid_evento;
  }

  public String getOid_Pessoa () {
    return oid_Pessoa;
  }

  public void setOid_Pessoa (String oid_Pessoa) {
    this.oid_Pessoa = oid_Pessoa;
  }

  public void setOid_evento (long oid_evento) {

    this.oid_evento = oid_evento;
  }

  public void setNm_Email_Destino (String nm_Email_Destino) {

    this.nm_Email_Destino = nm_Email_Destino;
  }

  public void setNm_Email_Origem (String nm_Email_Origem) {

    this.nm_Email_Origem = nm_Email_Origem;
  }

  public String getCd_Evento () {

    return cd_Evento;
  }

  public void setCd_Evento (String cd_Evento) {

    this.cd_Evento = cd_Evento;
  }

  public String getNm_Evento () {

    return nm_Evento;
  }

  public void setNm_Evento (String nm_Evento) {

    this.nm_Evento = nm_Evento;
  }

  public String getUsuario_stamp () {
    return usuario_stamp;
  }

  public void setUsuario_stamp (String usuario_stamp) {
    this.usuario_stamp = usuario_stamp;
  }

  public void setOid_Pessoa_EMail (long oid_Pessoa_EMail) {
    this.oid_Pessoa_EMail = oid_Pessoa_EMail;
  }

  public long getOid_Pessoa_EMail () {
    return oid_Pessoa_EMail;
  }

  public void setNm_Razao_Social (String nm_Razao_Social) {

    this.nm_Razao_Social = nm_Razao_Social;
  }

  public String getNm_Razao_Social () {

    return nm_Razao_Social;
  }

  public void setNm_Usuario (String nm_Usuario) {

    this.nm_Usuario = nm_Usuario;
  }

  public void setOid_Host (long oid_Host) {
    this.oid_Host = oid_Host;
  }

  public void setNm_Usuario_Destino (String nm_Usuario_Destino) {
    this.nm_Usuario_Destino = nm_Usuario_Destino;
  }

  public String getNm_Usuario () {

    return nm_Usuario;
  }

  public long getOid_Host () {
    return oid_Host;
  }

  public String getNm_Usuario_Destino () {
    return nm_Usuario_Destino;
  }

}