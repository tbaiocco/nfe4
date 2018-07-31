package com.master.ed;


public class EDI_SincoED extends MasterED{

//---- Dados para Entrada de Informações ----\\

	public long OID_Unidade;
	public String CD_Tipo;
	public String DT_Inicial;
	public String DT_Final;
	public String DM_Operacao;
	
	private String dt_Lancamento;
	private String cd_Conta;	
	private String cd_Centro_Custo;
	private String cd_Contra_Partida;
	private String vl_Lancamento;
	private String dm_Tipo_Debito_Credito;
	private String nm_Historico_Lancamento;
	private String dt_Saldo;
	private String vl_Saldo_Inicial;
	private String dm_Tipo_Saldo_Inicial;
	private String vl_Total_Debito;
	private String vl_Total_Credito;
	private String vl_Saldo_Final;
	private String dm_Tipo_Saldo_Final;
	private String dt_Inicial;
	private String dm_Tipo_Conta;
	private String cd_Contabil;
	private String nm_Conta;
	

//--------------- Getters and Setters ---------------\\
	
	public EDI_SincoED(){
	}
	public String getCD_Tipo() {
		return CD_Tipo;
	}
	public void setCD_Tipo(String CD_Tipo) {
		this.CD_Tipo = CD_Tipo;
	}
	public String getDM_Operacao() {
		return DM_Operacao;
	}
	public void setDM_Operacao(String DM_Operacao) {
	    this.DM_Operacao = DM_Operacao;
	}
	public String getDT_Final() {
	    return DT_Final;
	}
	public void setDT_Final(String DT_Final) {
	    this.DT_Final = DT_Final;
	}
	public void setDT_Inicial(String DT_Inicial) {
	    this.DT_Inicial = DT_Inicial;
	}
	public String getDT_Inicial() {
	    return DT_Inicial;
	}
	public void setOID_Unidade(long OID_Unidade) {
	    this.OID_Unidade = OID_Unidade;
	  }
	public long getOID_Unidade() {
		return OID_Unidade;
	}
	public String getCd_Centro_Custo() {
		return cd_Centro_Custo;
	}
	public void setCd_Centro_Custo(String cd_Centro_Custo) {
		this.cd_Centro_Custo = cd_Centro_Custo;
	}
	public String getCd_Conta() {
		return cd_Conta;
	}
	public void setCd_Conta(String cd_Conta) {
		this.cd_Conta = cd_Conta;
	}
	public String getCd_Contabil() {
		return cd_Contabil;
	}
	public void setCd_Contabil(String cd_Contabil) {
		this.cd_Contabil = cd_Contabil;
	}
	public String getCd_Contra_Partida() {
		return cd_Contra_Partida;
	}
	public void setCd_Contra_Partida(String cd_Contra_Partida) {
		this.cd_Contra_Partida = cd_Contra_Partida;
	}
	public String getDm_Tipo_Conta() {
		return dm_Tipo_Conta;
	}
	public void setDm_Tipo_Conta(String dm_Tipo_Conta) {
		this.dm_Tipo_Conta = dm_Tipo_Conta;
	}
	public String getDm_Tipo_Debito_Credito() {
		return dm_Tipo_Debito_Credito;
	}
	public void setDm_Tipo_Debito_Credito(String dm_Tipo_Debito_Credito) {
		this.dm_Tipo_Debito_Credito = dm_Tipo_Debito_Credito;
	}
	public String getDm_Tipo_Saldo_Final() {
		return dm_Tipo_Saldo_Final;
	}
	public void setDm_Tipo_Saldo_Final(String dm_Tipo_Saldo_Final) {
		this.dm_Tipo_Saldo_Final = dm_Tipo_Saldo_Final;
	}
	public String getDm_Tipo_Saldo_Inicial() {
		return dm_Tipo_Saldo_Inicial;
	}
	public void setDm_Tipo_Saldo_Inicial(String dm_Tipo_Saldo_Inicial) {
		this.dm_Tipo_Saldo_Inicial = dm_Tipo_Saldo_Inicial;
	}
	public String getDt_Inicial() {
		return dt_Inicial;
	}
	public void setDt_Inicial(String dt_Inicial) {
		this.dt_Inicial = dt_Inicial;
	}
	public String getDt_Lancamento() {
		return dt_Lancamento;
	}
	public void setDt_Lancamento(String dt_Lancamento) {
		this.dt_Lancamento = dt_Lancamento;
	}
	public String getDt_Saldo() {
		return dt_Saldo;
	}
	public void setDt_Saldo(String dt_Saldo) {
		this.dt_Saldo = dt_Saldo;
	}
	public String getNm_Conta() {
		return nm_Conta;
	}
	public void setNm_Conta(String nm_Conta) {
		this.nm_Conta = nm_Conta;
	}
	public String getNm_Historico_Lancamento() {
		return nm_Historico_Lancamento;
	}
	public void setNm_Historico_Lancamento(String nm_Historico_Lancamento) {
		this.nm_Historico_Lancamento = nm_Historico_Lancamento;
	}
	public String getVl_Lancamento() {
		return vl_Lancamento;
	}
	public void setVl_Lancamento(String vl_Lancamento) {
		this.vl_Lancamento = vl_Lancamento;
	}
	public String getVl_Saldo_Final() {
		return vl_Saldo_Final;
	}
	public void setVl_Saldo_Final(String vl_Saldo_Final) {
		this.vl_Saldo_Final = vl_Saldo_Final;
	}
	public String getVl_Saldo_Inicial() {
		return vl_Saldo_Inicial;
	}
	public void setVl_Saldo_Inicial(String vl_Saldo_Inicial) {
		this.vl_Saldo_Inicial = vl_Saldo_Inicial;
	}
	public String getVl_Total_Credito() {
		return vl_Total_Credito;
	}
	public void setVl_Total_Credito(String vl_Total_Credito) {
		this.vl_Total_Credito = vl_Total_Credito;
	}
	public String getVl_Total_Debito() {
		return vl_Total_Debito;
	}
	public void setVl_Total_Debito(String vl_Total_Debito) {
		this.vl_Total_Debito = vl_Total_Debito;
	}

	


}
