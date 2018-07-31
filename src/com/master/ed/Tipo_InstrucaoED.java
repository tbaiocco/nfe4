package com.master.ed;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class Tipo_InstrucaoED extends MasterED {

  private Integer oid_Tipo_Instrucao;
  private String cd_Tipo_Instrucao;
  private String nm_Tipo_Instrucao;
  private String dm_Altera_Titulo;
  private String dm_Gera_Movimento;
  private String dm_Gera_Ocorrencia;
  private String dm_Gera_Instrucao;
  private String cd_Conta_Contabil;
  private String nm_Historico;
  private String cd_Historico;
  private Integer oid_Historico;
  private String cd_Valor;
  private String DM_Atualiza_Saldo;

  private String DM_Diario_Razao;

  public String getCd_Tipo_Instrucao() {
    return cd_Tipo_Instrucao;
  }
  public String getDm_Altera_Titulo() {
    return dm_Altera_Titulo;
  }
  public String getDm_Gera_Instrucao() {
    return dm_Gera_Instrucao;
  }
  public String getDm_Gera_Movimento() {
    return dm_Gera_Movimento;
  }
  public String getDm_Gera_Ocorrencia() {
    return dm_Gera_Ocorrencia;
  }
  public String getNm_Tipo_Instrucao() {
    return nm_Tipo_Instrucao;
  }
  public Integer getOid_Tipo_Instrucao() {
    return oid_Tipo_Instrucao;
  }
  public void setOid_Tipo_Instrucao(Integer oid_Tipo_Instrucao) {
    this.oid_Tipo_Instrucao = oid_Tipo_Instrucao;
  }
  public void setNm_Tipo_Instrucao(String nm_Tipo_Instrucao) {
    this.nm_Tipo_Instrucao = nm_Tipo_Instrucao;
  }
  public void setDm_Gera_Ocorrencia(String dm_Gera_Ocorrencia) {
    this.dm_Gera_Ocorrencia = dm_Gera_Ocorrencia;
  }
  public void setDm_Gera_Movimento(String dm_Gera_Movimento) {
    this.dm_Gera_Movimento = dm_Gera_Movimento;
  }
  public void setDm_Gera_Instrucao(String dm_Gera_Instrucao) {
    this.dm_Gera_Instrucao = dm_Gera_Instrucao;
  }
  public void setDm_Altera_Titulo(String dm_Altera_Titulo) {
    this.dm_Altera_Titulo = dm_Altera_Titulo;
  }
  public void setCd_Tipo_Instrucao(String cd_Tipo_Instrucao) {
    this.cd_Tipo_Instrucao = cd_Tipo_Instrucao;
  }
  public void setCd_Conta_Contabil(String cd_Conta_Contabil) {
    this.cd_Conta_Contabil = cd_Conta_Contabil;
  }
  public String getCd_Conta_Contabil() {
    return cd_Conta_Contabil;
  }
  public void setOid_Historico(Integer oid_Historico) {
    this.oid_Historico = oid_Historico;
  }
  public Integer getOid_Historico() {
    return oid_Historico;
  }
  public void setCd_Historico(String cd_Historico) {
    this.cd_Historico = cd_Historico;
  }
  public String getCd_Historico() {
    return cd_Historico;
  }
  public void setNm_Historico(String nm_Historico) {
    this.nm_Historico = nm_Historico;
  }
  public String getNm_Historico() {
    return nm_Historico;
  }
  public void setCd_Valor(String cd_Valor) {
    this.cd_Valor = cd_Valor;
  }
  public String getCd_Valor() {
    return cd_Valor;
  }
  public void setDM_Atualiza_Saldo(String DM_Atualiza_Saldo) {
    this.DM_Atualiza_Saldo = DM_Atualiza_Saldo;
  }
  public String getDM_Atualiza_Saldo() {
    return DM_Atualiza_Saldo;
  }
public String getDM_Diario_Razao() {
	return DM_Diario_Razao;
}
public void setDM_Diario_Razao(String diario_Razao) {
	DM_Diario_Razao = diario_Razao;
}


}