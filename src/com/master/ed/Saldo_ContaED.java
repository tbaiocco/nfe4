package com.master.ed;

public class Saldo_ContaED {

	private long oid_saldo_conta;
	private String dt_referencia;
	private long oid_conta;
	private double vl_saldo_inicial;
	private double vl_debito;
	private double vl_credito;
	private double vl_saldo_final;

	public String getDt_referencia() {
		return dt_referencia;
	}
	public void setDt_referencia(String dt_referencia) {
		this.dt_referencia = dt_referencia;
	}
	public long getOid_conta() {
		return oid_conta;
	}
	public void setOid_conta(long oid_conta) {
		this.oid_conta = oid_conta;
	}
	public long getOid_saldo_conta() {
		return oid_saldo_conta;
	}
	public void setOid_saldo_conta(long oid_saldo_conta) {
		this.oid_saldo_conta = oid_saldo_conta;
	}
	public double getVl_credito() {
		return vl_credito;
	}
	public void setVl_credito(double vl_credito) {
		this.vl_credito = vl_credito;
	}
	public double getVl_debito() {
		return vl_debito;
	}
	public void setVl_debito(double vl_debito) {
		this.vl_debito = vl_debito;
	}
	public double getVl_saldo_final() {
		return vl_saldo_final;
	}
	public void setVl_saldo_final(double vl_saldo_final) {
		this.vl_saldo_final = vl_saldo_final;
	}
	public double getVl_saldo_inicial() {
		return vl_saldo_inicial;
	}
	public void setVl_saldo_inicial(double vl_saldo_inicial) {
		this.vl_saldo_inicial = vl_saldo_inicial;
	}

}
