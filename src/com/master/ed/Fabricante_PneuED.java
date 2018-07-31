/*
 * Created on 11/11/2004
 */
package com.master.ed;

/**
 * @author Régis Steigleder
 * @serial Fabricantes de pneus
 * @serialData 06/2007
 */
public class Fabricante_PneuED extends RelatorioBaseED {

	private static final long serialVersionUID = -702531689095492809L;

	private int oid_Fabricante_Pneu;
	private int oid_Empresa;
	private String CD_Fabricante_Pneu;
	private String NM_Fabricante_Pneu;
	private String dm_Todos;
	private String dm_Sucateados;
	private String dm_Nao_Sucateados;
	private double vl_Fabricante_Pneu;
	private String msg_Stamp;

	public String getMsg_Stamp() {
		return msg_Stamp;
	}

	public void setMsg_Stamp(String msg_Stamp) {
		this.msg_Stamp = msg_Stamp;
	}

	public Fabricante_PneuED () {
	}

	public Fabricante_PneuED (int oid_Fabricante_Pneu , String CD_Fabricante_Pneu , String NM_Fabricante_Pneu) {
		super ();
		this.oid_Fabricante_Pneu = oid_Fabricante_Pneu;
		this.CD_Fabricante_Pneu = CD_Fabricante_Pneu;
		this.NM_Fabricante_Pneu = NM_Fabricante_Pneu;
	}

	public String getCD_Fabricante_Pneu () {
		return CD_Fabricante_Pneu;
	}

	public void setCD_Fabricante_Pneu (String fabricante_Pneu) {
		CD_Fabricante_Pneu = fabricante_Pneu;
	}

	public String getNM_Fabricante_Pneu () {
		return NM_Fabricante_Pneu;
	}

	public void setNM_Fabricante_Pneu (String fabricante_Pneu) {
		NM_Fabricante_Pneu = fabricante_Pneu;
	}

	public int getOid_Fabricante_Pneu () {
		return oid_Fabricante_Pneu;
	}

	public void setOid_Fabricante_Pneu (int oid_Fabricante_Pneu) {
		this.oid_Fabricante_Pneu = oid_Fabricante_Pneu;
	}

	public int getOid_Empresa() {
		return oid_Empresa;
	}

	public void setOid_Empresa(int oid_Empresa) {
		this.oid_Empresa = oid_Empresa;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getDm_Nao_Sucateados() {
		return dm_Nao_Sucateados;
	}

	public void setDm_Nao_Sucateados(String dm_Nao_Sucateados) {
		this.dm_Nao_Sucateados = dm_Nao_Sucateados;
	}

	public String getDm_Sucateados() {
		return dm_Sucateados;
	}

	public void setDm_Sucateados(String dm_Sucateados) {
		this.dm_Sucateados = dm_Sucateados;
	}

	public String getDm_Todos() {
		return dm_Todos;
	}

	public void setDm_Todos(String dm_Todos) {
		this.dm_Todos = dm_Todos;
	}

	public double getVl_Fabricante_Pneu() {
		return vl_Fabricante_Pneu;
	}

	public void setVl_Fabricante_Pneu(double vl_Fabricante_Pneu) {
		this.vl_Fabricante_Pneu = vl_Fabricante_Pneu;
	}
}
