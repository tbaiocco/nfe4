package com.master.ed;

import java.io.Serializable;

/**
 * @author Regis
 *
 */
public class Menu_SistemaED extends MasterED implements Serializable {

	private static final long serialVersionUID = 4511106871313059824L;

	public Menu_SistemaED() {
		super();
	}

	private int oid_Menu_Sistema;
	private int oid_Sistema;
	private String cd_Opcao;
	private String nm_Opcao;
	private String nm_Tela;
	private String cd_Opcao_Pai;
	private String nm_Sistema;
	private int nr_Nivel;
	
	public String getCd_Opcao() {
		return cd_Opcao;
	}
	public void setCd_Opcao(String cd_Opcao) {
		this.cd_Opcao = cd_Opcao;
	}
	public String getNm_Opcao() {
		return nm_Opcao;
	}
	public void setNm_Opcao(String nm_Opcao) {
		this.nm_Opcao = nm_Opcao;
	}
	public int getOid_Menu_Sistema() {
		return oid_Menu_Sistema;
	}
	public void setOid_Menu_Sistema(int oid_Menu_Sistema) {
		this.oid_Menu_Sistema = oid_Menu_Sistema;
	}
	public int getOid_Sistema() {
		return oid_Sistema;
	}
	public void setOid_Sistema(int oid_Sistema) {
		this.oid_Sistema = oid_Sistema;
	}
	public String getNm_Tela() {
		return nm_Tela;
	}
	public void setNm_Tela(String nm_Tela) {
		this.nm_Tela = nm_Tela;
	}
	public String getCd_Opcao_Pai() {
		return cd_Opcao_Pai;
	}
	public void setCd_Opcao_Pai(String cd_Opcao_Pai) {
		this.cd_Opcao_Pai = cd_Opcao_Pai;
	}
	public String getNm_Sistema() {
		return nm_Sistema;
	}
	public void setNm_Sistema(String nm_Sistema) {
		this.nm_Sistema = nm_Sistema;
	}
	public int getNr_Nivel() {
		return nr_Nivel;
	}
	public void setNr_Nivel(int nr_Nivel) {
		this.nr_Nivel = nr_Nivel;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

}
