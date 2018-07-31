package com.master.ed;

import java.io.Serializable;

/**
 * @author Regis
 * 
 */
public class Menu_PerfilED extends MasterED implements Serializable {

	private static final long serialVersionUID = -929541386088925906L;

	public Menu_PerfilED() {
		super();
	}

	private int oid_Empresa;
	private int oid_Menu_Perfil;
	private int oid_Sistema;
	private String nm_Menu_Perfil;
	private String nm_Sistema;
	private String array;

	
	public String getNm_Menu_Perfil() {
		return nm_Menu_Perfil;
	}

	public void setNm_Menu_Perfil(String nm_Menu_Perfil) {
		this.nm_Menu_Perfil = nm_Menu_Perfil;
	}

	public int getOid_Menu_Perfil() {
		return oid_Menu_Perfil;
	}

	public void setOid_Menu_Perfil(int oid_Menu_Perfil) {
		this.oid_Menu_Perfil = oid_Menu_Perfil;
	}

	public int getOid_Sistema() {
		return oid_Sistema;
	}

	public void setOid_Sistema(int oid_Sistema) {
		this.oid_Sistema = oid_Sistema;
	}

	public int getOid_Empresa() {
		return oid_Empresa;
	}

	public void setOid_Empresa(int oid_Empresa) {
		this.oid_Empresa = oid_Empresa;
	}

	public String getNm_Sistema() {
		return nm_Sistema;
	}

	public void setNm_Sistema(String nm_Sistema) {
		this.nm_Sistema = nm_Sistema;
	}

	public String getArray() {
		return array;
	}

	public void setArray(String array) {
		this.array = array;
	}

}
