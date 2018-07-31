package com.master.ed;

public class CategoriaED extends MasterED {

    public CategoriaED() {
        super();
  }
    public CategoriaED(String oid_Categoria) {
        this.oid_Categoria = oid_Categoria;
    }

  private String oid_Categoria;
  private String nm_Categoria;
  private Double pe_Fator_Anual;
  private long nr_Anos;
  private Integer oid_Conta;
  private String nm_Conta;
  private String cd_Conta;

  private Integer OID_Conta_Credora_Venda;
  private Integer OID_Conta_Devedora_Venda;
  private String CD_Conta_Credora_Venda;
  private String NM_Conta_Credora_Venda;
  private String CD_Conta_Devedora_Venda;
  private String NM_Conta_Devedora_Venda;

  private Integer OID_Conta_Credora_Baixa_Depreciacao;
  private Integer OID_Conta_Devedora_Baixa_Depreciacao;
  private String CD_Conta_Credora_Baixa_Depreciacao;
  private String NM_Conta_Credora_Baixa_Depreciacao;
  private String CD_Conta_Devedora_Baixa_Depreciacao;
  private String NM_Conta_Devedora_Baixa_Depreciacao;

  private Integer OID_Conta_Credora_Ganho_Venda;
  private Integer OID_Conta_Devedora_Ganho_Venda;
  private String CD_Conta_Credora_Ganho_Venda;
  private String NM_Conta_Credora_Ganho_Venda;
  private String CD_Conta_Devedora_Ganho_Venda;
  private String NM_Conta_Devedora_Ganho_Venda;

  private Integer OID_Conta_Credora_Perda_Venda;
  private Integer OID_Conta_Devedora_Perda_Venda;
  private String CD_Conta_Credora_Perda_Venda;
  private String NM_Conta_Credora_Perda_Venda;
  private String CD_Conta_Devedora_Perda_Venda;
  private String NM_Conta_Devedora_Perda_Venda;

public String getCd_Conta() {
	return cd_Conta;
}
public void setCd_Conta(String cd_Conta) {
	this.cd_Conta = cd_Conta;
}
public String getNm_Categoria() {
	return nm_Categoria;
}
public void setNm_Categoria(String nm_Categoria) {
	this.nm_Categoria = nm_Categoria;
}
public String getNm_Conta() {
	return nm_Conta;
}
public void setNm_Conta(String nm_Conta) {
	this.nm_Conta = nm_Conta;
}
public long getNr_Anos() {
	return nr_Anos;
}
public void setNr_Anos(long nr_Anos) {
	this.nr_Anos = nr_Anos;
}
public String getOid_Categoria() {
	return oid_Categoria;
}
public void setOid_Categoria(String oid_Categoria) {
	this.oid_Categoria = oid_Categoria;
}
public Integer getOid_Conta() {
	return oid_Conta;
}
public void setOid_Conta(Integer oid_Conta) {
	this.oid_Conta = oid_Conta;
}
public Double getPe_Fator_Anual() {
	return pe_Fator_Anual;
}
public void setPe_Fator_Anual(Double pe_Fator_Anual) {
	this.pe_Fator_Anual = pe_Fator_Anual;
}
public String getCD_Conta_Credora_Baixa_Depreciacao() {
	return CD_Conta_Credora_Baixa_Depreciacao;
}
public void setCD_Conta_Credora_Baixa_Depreciacao(
		String conta_Credora_Baixa_Depreciacao) {
	CD_Conta_Credora_Baixa_Depreciacao = conta_Credora_Baixa_Depreciacao;
}
public String getCD_Conta_Credora_Ganho_Venda() {
	return CD_Conta_Credora_Ganho_Venda;
}
public void setCD_Conta_Credora_Ganho_Venda(String conta_Credora_Ganho_Venda) {
	CD_Conta_Credora_Ganho_Venda = conta_Credora_Ganho_Venda;
}
public String getCD_Conta_Credora_Perda_Venda() {
	return CD_Conta_Credora_Perda_Venda;
}
public void setCD_Conta_Credora_Perda_Venda(String conta_Credora_Perda_Venda) {
	CD_Conta_Credora_Perda_Venda = conta_Credora_Perda_Venda;
}
public String getCD_Conta_Credora_Venda() {
	return CD_Conta_Credora_Venda;
}
public void setCD_Conta_Credora_Venda(String conta_Credora_Venda) {
	CD_Conta_Credora_Venda = conta_Credora_Venda;
}
public String getCD_Conta_Devedora_Baixa_Depreciacao() {
	return CD_Conta_Devedora_Baixa_Depreciacao;
}
public void setCD_Conta_Devedora_Baixa_Depreciacao(
		String conta_Devedora_Baixa_Depreciacao) {
	CD_Conta_Devedora_Baixa_Depreciacao = conta_Devedora_Baixa_Depreciacao;
}
public String getCD_Conta_Devedora_Ganho_Venda() {
	return CD_Conta_Devedora_Ganho_Venda;
}
public void setCD_Conta_Devedora_Ganho_Venda(String conta_Devedora_Ganho_Venda) {
	CD_Conta_Devedora_Ganho_Venda = conta_Devedora_Ganho_Venda;
}
public String getCD_Conta_Devedora_Perda_Venda() {
	return CD_Conta_Devedora_Perda_Venda;
}
public void setCD_Conta_Devedora_Perda_Venda(String conta_Devedora_Perda_Venda) {
	CD_Conta_Devedora_Perda_Venda = conta_Devedora_Perda_Venda;
}
public String getCD_Conta_Devedora_Venda() {
	return CD_Conta_Devedora_Venda;
}
public void setCD_Conta_Devedora_Venda(String conta_Devedora_Venda) {
	CD_Conta_Devedora_Venda = conta_Devedora_Venda;
}
public String getNM_Conta_Credora_Baixa_Depreciacao() {
	return NM_Conta_Credora_Baixa_Depreciacao;
}
public void setNM_Conta_Credora_Baixa_Depreciacao(
		String conta_Credora_Baixa_Depreciacao) {
	NM_Conta_Credora_Baixa_Depreciacao = conta_Credora_Baixa_Depreciacao;
}
public String getNM_Conta_Credora_Ganho_Venda() {
	return NM_Conta_Credora_Ganho_Venda;
}
public void setNM_Conta_Credora_Ganho_Venda(String conta_Credora_Ganho_Venda) {
	NM_Conta_Credora_Ganho_Venda = conta_Credora_Ganho_Venda;
}
public String getNM_Conta_Credora_Perda_Venda() {
	return NM_Conta_Credora_Perda_Venda;
}
public void setNM_Conta_Credora_Perda_Venda(String conta_Credora_Perda_Venda) {
	NM_Conta_Credora_Perda_Venda = conta_Credora_Perda_Venda;
}
public String getNM_Conta_Credora_Venda() {
	return NM_Conta_Credora_Venda;
}
public void setNM_Conta_Credora_Venda(String conta_Credora_Venda) {
	NM_Conta_Credora_Venda = conta_Credora_Venda;
}
public String getNM_Conta_Devedora_Baixa_Depreciacao() {
	return NM_Conta_Devedora_Baixa_Depreciacao;
}
public void setNM_Conta_Devedora_Baixa_Depreciacao(
		String conta_Devedora_Baixa_Depreciacao) {
	NM_Conta_Devedora_Baixa_Depreciacao = conta_Devedora_Baixa_Depreciacao;
}
public String getNM_Conta_Devedora_Ganho_Venda() {
	return NM_Conta_Devedora_Ganho_Venda;
}
public void setNM_Conta_Devedora_Ganho_Venda(String conta_Devedora_Ganho_Venda) {
	NM_Conta_Devedora_Ganho_Venda = conta_Devedora_Ganho_Venda;
}
public String getNM_Conta_Devedora_Perda_Venda() {
	return NM_Conta_Devedora_Perda_Venda;
}
public void setNM_Conta_Devedora_Perda_Venda(String conta_Devedora_Perda_Venda) {
	NM_Conta_Devedora_Perda_Venda = conta_Devedora_Perda_Venda;
}
public String getNM_Conta_Devedora_Venda() {
	return NM_Conta_Devedora_Venda;
}
public void setNM_Conta_Devedora_Venda(String conta_Devedora_Venda) {
	NM_Conta_Devedora_Venda = conta_Devedora_Venda;
}
public Integer getOID_Conta_Credora_Baixa_Depreciacao() {
	return OID_Conta_Credora_Baixa_Depreciacao;
}
public void setOID_Conta_Credora_Baixa_Depreciacao(
		Integer conta_Credora_Baixa_Depreciacao) {
	OID_Conta_Credora_Baixa_Depreciacao = conta_Credora_Baixa_Depreciacao;
}
public Integer getOID_Conta_Credora_Ganho_Venda() {
	return OID_Conta_Credora_Ganho_Venda;
}
public void setOID_Conta_Credora_Ganho_Venda(Integer conta_Credora_Ganho_Venda) {
	OID_Conta_Credora_Ganho_Venda = conta_Credora_Ganho_Venda;
}
public Integer getOID_Conta_Credora_Perda_Venda() {
	return OID_Conta_Credora_Perda_Venda;
}
public void setOID_Conta_Credora_Perda_Venda(Integer conta_Credora_Perda_Venda) {
	OID_Conta_Credora_Perda_Venda = conta_Credora_Perda_Venda;
}
public Integer getOID_Conta_Credora_Venda() {
	return OID_Conta_Credora_Venda;
}
public void setOID_Conta_Credora_Venda(Integer conta_Credora_Venda) {
	OID_Conta_Credora_Venda = conta_Credora_Venda;
}
public Integer getOID_Conta_Devedora_Baixa_Depreciacao() {
	return OID_Conta_Devedora_Baixa_Depreciacao;
}
public void setOID_Conta_Devedora_Baixa_Depreciacao(
		Integer conta_Devedora_Baixa_Depreciacao) {
	OID_Conta_Devedora_Baixa_Depreciacao = conta_Devedora_Baixa_Depreciacao;
}
public Integer getOID_Conta_Devedora_Ganho_Venda() {
	return OID_Conta_Devedora_Ganho_Venda;
}
public void setOID_Conta_Devedora_Ganho_Venda(Integer conta_Devedora_Ganho_Venda) {
	OID_Conta_Devedora_Ganho_Venda = conta_Devedora_Ganho_Venda;
}
public Integer getOID_Conta_Devedora_Perda_Venda() {
	return OID_Conta_Devedora_Perda_Venda;
}
public void setOID_Conta_Devedora_Perda_Venda(Integer conta_Devedora_Perda_Venda) {
	OID_Conta_Devedora_Perda_Venda = conta_Devedora_Perda_Venda;
}
public Integer getOID_Conta_Devedora_Venda() {
	return OID_Conta_Devedora_Venda;
}
public void setOID_Conta_Devedora_Venda(Integer conta_Devedora_Venda) {
	OID_Conta_Devedora_Venda = conta_Devedora_Venda;
}
}