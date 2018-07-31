package com.master.ed;

public class Composicao_ChassisED extends RelatorioBaseED {

	/**
	 *
	 */
	private static final long serialVersionUID = 820459775283969069L;

	private long oid_Composicao_Chassis;
	private long oid_Chassis;
	private String nm_Descricao;
	private String dm_Eixo;
	private String dm_Posicao_Pneu;
	private String dm_Posicao_Anterior;

	private long oid_Pneu;
	private String cd_Pneu;

	public String getDm_Eixo() {
		return dm_Eixo;
	}
	public void setDm_Eixo(String dm_Eixo) {
		this.dm_Eixo = dm_Eixo;
	}
	public String getDm_Posicao_Pneu() {
		return dm_Posicao_Pneu;
	}
	public void setDm_Posicao_Pneu(String dm_Posicao_Pneu) {
		this.dm_Posicao_Pneu = dm_Posicao_Pneu;
	}
	public String getNm_Descricao() {
		return nm_Descricao;
	}
	public void setNm_Descricao(String nm_Descricao) {
		this.nm_Descricao = nm_Descricao;
	}
	public long getOid_Chassis() {
		return oid_Chassis;
	}
	public void setOid_Chassis(long oid_Chassis) {
		this.oid_Chassis = oid_Chassis;
	}
	public long getOid_Composicao_Chassis() {
		return oid_Composicao_Chassis;
	}
	public void setOid_Composicao_Chassis(long oid_Composicao_Chassis) {
		this.oid_Composicao_Chassis = oid_Composicao_Chassis;
	}
	public String getCd_Pneu() {
		return cd_Pneu;
	}
	public void setCd_Pneu(String cd_Pneu) {
		this.cd_Pneu = cd_Pneu;
	}
	public long getOid_Pneu() {
		return oid_Pneu;
	}
	public void setOid_Pneu(long oid_Pneu) {
		this.oid_Pneu = oid_Pneu;
	}
	public String getDm_Posicao_Anterior() {
		return dm_Posicao_Anterior;
	}
	public void setDm_Posicao_Anterior(String dm_Posicao_Anterior) {
		this.dm_Posicao_Anterior = dm_Posicao_Anterior;
	}

}
