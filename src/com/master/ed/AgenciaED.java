package com.master.ed;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class AgenciaED extends MasterED implements java.io.Serializable{

  private Integer oid_Agencia;
  private Integer oid_Cidade;
  private Integer oid_Banco;
  private String cd_Agencia;
  private String nome_Pessoa;
  private String nm_Cidade;

  public String getCd_Agencia() {
    return cd_Agencia;
  }
  public void setCd_Agencia(String cd_Agencia) {
    this.cd_Agencia = cd_Agencia;
  }
  public void setOid_Agencia(Integer oid_Agencia) {
    this.oid_Agencia = oid_Agencia;
  }
  public Integer getOid_Agencia() {
    return oid_Agencia;
  }
  public void setOid_Cidade(Integer oid_Cidade) {
    this.oid_Cidade = oid_Cidade;
  }
  public Integer getOid_Cidade() {
    return oid_Cidade;
  }
  public void setOid_Banco(Integer oid_Banco) {
    this.oid_Banco = oid_Banco;
  }
  public Integer getOid_Banco() {
    return oid_Banco;
  }
  public void setNome_Pessoa(String nome_Pessoa) {
    this.nome_Pessoa = nome_Pessoa;
  }
  public String getNome_Pessoa() {
    return nome_Pessoa;
  }
  public void setNm_Cidade(String nm_Cidade) {
    this.nm_Cidade = nm_Cidade;
  }
  public String getNm_Cidade() {
    return nm_Cidade;
  }

}