package com.master.ed;

public class Pessoa_EDIED
    extends MasterED {

  private long oid_padrao_Imp;
  private long oid_padrao_Exp;

  private String nm_arquivo_Imp;
  private String nm_arquivo_Exp;
  private String oid_Pessoa;

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
  private long oid_pessoa_edi;
  private String nm_razao_social;
  private String nm_descricao;
  private String dm_classe;
  private String dm_Pasta;

  public String getNm_arquivo_Exp () {
    return nm_arquivo_Exp;
  }

  public String getNm_arquivo_Imp () {
    return nm_arquivo_Imp;
  }

  public long getOid_padrao_Exp () {
    return oid_padrao_Exp;
  }

  public long getOid_padrao_Imp () {
    return oid_padrao_Imp;
  }

  public String getOid_Pessoa () {
    return oid_Pessoa;
  }

  public void setOid_Pessoa (String oid_Pessoa) {
    this.oid_Pessoa = oid_Pessoa;
  }

  public void setOid_padrao_Imp (long oid_padrao_Imp) {
    this.oid_padrao_Imp = oid_padrao_Imp;
  }

  public void setOid_padrao_Exp (long oid_padrao_Exp) {
    this.oid_padrao_Exp = oid_padrao_Exp;
  }

  public void setNm_arquivo_Imp (String nm_arquivo_Imp) {
    this.nm_arquivo_Imp = nm_arquivo_Imp;
  }

  public void setNm_arquivo_Exp (String nm_arquivo_Exp) {
    this.nm_arquivo_Exp = nm_arquivo_Exp;
  }

  public String getCd_tipo () {
    return cd_tipo;
  }

  public String getCd_padrao () {
    return cd_padrao;
  }

  public String getDm_tipo_padrao () {
    return dm_tipo_padrao;
  }

  public String getDm_tipo_transacao () {
    return dm_tipo_transacao;
  }

  public String getDt_stamp_padrao () {
    return dt_stamp_padrao;
  }

  public String getDt_stamp_tipo () {
    return dt_stamp_tipo;
  }

  public void setCd_padrao (String cd_padrao) {
    this.cd_padrao = cd_padrao;
  }

  public void setCd_tipo (String cd_tipo) {
    this.cd_tipo = cd_tipo;
  }

  public void setDm_tipo_padrao (String dm_tipo_padrao) {
    this.dm_tipo_padrao = dm_tipo_padrao;
  }

  public void setDm_tipo_transacao (String dm_tipo_transacao) {
    this.dm_tipo_transacao = dm_tipo_transacao;
  }

  public void setDt_stamp_padrao (String dt_stamp_padrao) {
    this.dt_stamp_padrao = dt_stamp_padrao;
  }

  public void setDt_stamp_tipo (String dt_stamp_tipo) {
    this.dt_stamp_tipo = dt_stamp_tipo;
  }

  public String getNm_descricao_padrao () {
    return nm_descricao_padrao;
  }

  public String getNm_descricao_tipo () {
    return nm_descricao_tipo;
  }

  public void setNm_descricao_padrao (String nm_descricao_padrao) {
    this.nm_descricao_padrao = nm_descricao_padrao;
  }

  public void setNm_descricao_tipo (String nm_descricao_tipo) {
    this.nm_descricao_tipo = nm_descricao_tipo;
  }

  public String getUsuario_stamp () {
    return usuario_stamp;
  }

  public String getUsuario_stamp_tipo () {
    return usuario_stamp_tipo;
  }

  public void setUsuario_stamp (String usuario_stamp) {
    this.usuario_stamp = usuario_stamp;
  }

  public void setUsuario_stamp_tipo (String usuario_stamp_tipo) {
    this.usuario_stamp_tipo = usuario_stamp_tipo;
  }

  public void setOid_pessoa_edi (long oid_pessoa_edi) {
    this.oid_pessoa_edi = oid_pessoa_edi;
  }

  public long getOid_pessoa_edi () {
    return oid_pessoa_edi;
  }

  public void setNm_razao_social (String nm_razao_social) {
    this.nm_razao_social = nm_razao_social;
  }

  public String getNm_razao_social () {
    return nm_razao_social;
  }

  public void setNm_descricao (String nm_descricao) {
    this.nm_descricao = nm_descricao;
  }

  public String getNm_descricao () {
    return nm_descricao;
  }

  public void setDm_classe (String dm_classe) {
    this.dm_classe = dm_classe;
  }

  public void setDm_Pasta (String dm_Pasta) {
    this.dm_Pasta = dm_Pasta;
  }

  public String getDm_classe () {
    return dm_classe;
  }

  public String getDm_Pasta () {
    return dm_Pasta;
  }

}