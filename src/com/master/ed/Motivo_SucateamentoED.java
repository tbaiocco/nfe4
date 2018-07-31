package com.master.ed;

import java.io.Serializable;

/**
 * @author Ralph
 * @serial Motivos de Sucateamento
 * @serialData 06/2007
 */
public class Motivo_SucateamentoED extends RelatorioBaseED {

	private static final long serialVersionUID = 949830467769856668L;

	public Motivo_SucateamentoED() {
	}

	private long oid_Motivo_Sucateamento;
	private String nm_Motivo_Sucateamento;
	private long oid_Fabricante_Pneu;
	private String nm_Fabricante_Pneu;
	private double vl_Motivo_Sucateamento;
	private long oid_Empresa;

	public String getNm_Motivo_Sucateamento() {
		return nm_Motivo_Sucateamento;
	}
	public void setNm_Motivo_Sucateamento(String nm_Motivo_Sucateamento) {
		this.nm_Motivo_Sucateamento = nm_Motivo_Sucateamento;
	}
	public long getOid_Empresa() {
		return oid_Empresa;
	}
	public void setOid_Empresa(long oid_Empresa) {
		this.oid_Empresa = oid_Empresa;
	}
	public long getOid_Motivo_Sucateamento() {
		return oid_Motivo_Sucateamento;
	}
	public void setOid_Motivo_Sucateamento(long oid_Motivo_Sucateamento) {
		this.oid_Motivo_Sucateamento = oid_Motivo_Sucateamento;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getNm_Fabricante_Pneu() {
		return nm_Fabricante_Pneu;
	}
	public void setNm_Fabricante_Pneu(String nm_Fabricante_Pneu) {
		this.nm_Fabricante_Pneu = nm_Fabricante_Pneu;
	}
	public long getOid_Fabricante_Pneu() {
		return oid_Fabricante_Pneu;
	}
	public void setOid_Fabricante_Pneu(long oid_Fabricante_Pneu) {
		this.oid_Fabricante_Pneu = oid_Fabricante_Pneu;
	}
	public double getVl_Motivo_Sucateamento() {
		return vl_Motivo_Sucateamento;
	}
	public void setVl_Motivo_Sucateamento(double vl_Motivo_Sucateamento) {
		this.vl_Motivo_Sucateamento = vl_Motivo_Sucateamento;
	}


}
