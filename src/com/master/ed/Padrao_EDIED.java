package com.master.ed;

public class Padrao_EDIED extends MasterED {

       private long oid_padrao;
       private long oid_tipo_padrao;
       private String cd_padrao;
       private String cd_tipo;
       private String nm_descricao_padrao;
       private String nm_descricao_tipo;
       private String dt_stamp_padrao;
       private String usuario_stamp;
       private String dt_stamp_tipo;
       private String usuario_stamp_tipo;
  private String dm_tipo_padrao;
  private String dm_tipo_transacao;
  private String dm_classe;

  public String getCd_padrao() {
    return cd_padrao;
  }
  public String getCd_tipo() {
    return cd_tipo;
  }
  public String getDt_stamp_padrao() {
    return dt_stamp_padrao;
  }
  public String getDt_stamp_tipo() {
    return dt_stamp_tipo;
  }
  public String getNm_descricao_padrao() {
    return nm_descricao_padrao;
  }
  public String getNm_descricao_tipo() {
    return nm_descricao_tipo;
  }
  public long getOid_padrao() {
    return oid_padrao;
  }
  public long getOid_tipo_padrao() {
    return oid_tipo_padrao;
  }
  public String getUsuario_stamp() {
    return usuario_stamp;
  }
  public String getUsuario_stamp_tipo() {
    return usuario_stamp_tipo;
  }
  public void setUsuario_stamp_tipo(String usuario_stamp_tipo) {
    this.usuario_stamp_tipo = usuario_stamp_tipo;
  }
  public void setUsuario_stamp(String usuario_stamp) {
    this.usuario_stamp = usuario_stamp;
  }
  public void setOid_tipo_padrao(long oid_tipo_padrao) {
    this.oid_tipo_padrao = oid_tipo_padrao;
  }
  public void setOid_padrao(long oid_padrao) {
    this.oid_padrao = oid_padrao;
  }
  public void setNm_descricao_tipo(String nm_descricao_tipo) {
    this.nm_descricao_tipo = nm_descricao_tipo;
  }
  public void setNm_descricao_padrao(String nm_descricao_padrao) {
    this.nm_descricao_padrao = nm_descricao_padrao;
  }
  public void setDt_stamp_tipo(String dt_stamp_tipo) {
    this.dt_stamp_tipo = dt_stamp_tipo;
  }
  public void setDt_stamp_padrao(String dt_stamp_padrao) {
    this.dt_stamp_padrao = dt_stamp_padrao;
  }
  public void setCd_tipo(String cd_tipo) {
    this.cd_tipo = cd_tipo;
  }
  public void setCd_padrao(String cd_padrao) {
    this.cd_padrao = cd_padrao;
  }
  public void setDm_tipo_padrao(String dm_tipo_padrao) {
    this.dm_tipo_padrao = dm_tipo_padrao;
  }
  public String getDm_tipo_padrao() {
    return dm_tipo_padrao;
  }
  public void setDm_tipo_transacao(String dm_tipo_transacao) {
    this.dm_tipo_transacao = dm_tipo_transacao;
  }
  public String getDm_tipo_transacao() {
    return dm_tipo_transacao;
  }
  public void setDm_classe(String dm_classe) {
    this.dm_classe = dm_classe;
  }
  public String getDm_classe() {
    return dm_classe;
  }

}