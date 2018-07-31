package com.master.ed;

public class Evento_EMailED
    extends MasterED {

  private long oid_Evento;
  private String cd_Evento;
  private String nm_Evento;
  private String dt_stamp_Evento;
  private String usuario_stamp;
  private String dt_stamp_tipo;
  public String getCd_Evento () {
    return cd_Evento;
  }

  public String getDt_stamp_Evento () {
    return dt_stamp_Evento;
  }

  public String getDt_stamp_tipo () {
    return dt_stamp_tipo;
  }

  public String getNm_Evento () {

    return nm_Evento;
  }

  public long getOid_Evento () {

    return oid_Evento;
  }

  public String getUsuario_stamp () {
    return usuario_stamp;
  }

  public void setUsuario_stamp (String usuario_stamp) {
    this.usuario_stamp = usuario_stamp;
  }

  public void setOid_Evento (long oid_Evento) {

    this.oid_Evento = oid_Evento;
  }

  public void setNm_Evento (String nm_Evento) {

    this.nm_Evento = nm_Evento;
  }

  public void setDt_stamp_tipo (String dt_stamp_tipo) {
    this.dt_stamp_tipo = dt_stamp_tipo;
  }

  public void setDt_stamp_Evento (String dt_stamp_Evento) {
    this.dt_stamp_Evento = dt_stamp_Evento;
  }

  public void setCd_Evento (String cd_Evento) {
    this.cd_Evento = cd_Evento;
  }

}