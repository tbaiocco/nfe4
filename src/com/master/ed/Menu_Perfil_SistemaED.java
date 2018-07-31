package com.master.ed;

import java.io.Serializable;

/**
 * @author Regis
 *
 */
public class Menu_Perfil_SistemaED extends MasterED implements Serializable {

	private static final long serialVersionUID = -7507902958216614357L;

	public Menu_Perfil_SistemaED() {
		super();
	}

	private int oid_Menu_Perfil_Sistema;
	private int oid_Menu_Perfil;
	private int oid_Menu_Sistema;
	private String dm_Acesso;
	private String cd_Opcao;
	private String cd_Opcao_Pai;
	private String nm_Opcao;
	private String nm_Tela;
	private int nr_Nivel;
	private boolean identado;

	public int getOid_Menu_Perfil_Sistema() {
		return oid_Menu_Perfil_Sistema;
	}
	public void setOid_Menu_Perfil_Sistema(int oid_Menu_Perfil_Sistema) {
		this.oid_Menu_Perfil_Sistema = oid_Menu_Perfil_Sistema;
	}
	public int getOid_Menu_Sistema() {
		return oid_Menu_Sistema;
	}
	public void setOid_Menu_Sistema(int oid_Menu_Sistema) {
		this.oid_Menu_Sistema = oid_Menu_Sistema;
	}
	public int getOid_Menu_Perfil() {
		return oid_Menu_Perfil;
	}
	public void setOid_Menu_Perfil(int oid_Menu_Perfil) {
		this.oid_Menu_Perfil = oid_Menu_Perfil;
	}
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
	public String getDm_Acesso() {
		return dm_Acesso;
	}
	public void setDm_Acesso(String dm_Acesso) {
		this.dm_Acesso = dm_Acesso;
	}
	public String getCd_Opcao_Pai() {
		return cd_Opcao_Pai;
	}
	public void setCd_Opcao_Pai(String cd_Opcao_Pai) {
		this.cd_Opcao_Pai = cd_Opcao_Pai;
	}
	public boolean isIdentado() {
		return identado;
	}
	public void setIdentado(boolean identado) {
		this.identado = identado;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public int getNr_Nivel() {
		return nr_Nivel;
	}
	public void setNr_Nivel(int nr_Nivel) {
		this.nr_Nivel = nr_Nivel;
	}
	public String getNm_Tela() {
		return nm_Tela;
	}
	public void setNm_Tela(String nm_Tela) {
		this.nm_Tela = nm_Tela;
	}

}
