package com.master.ed;

import java.io.Serializable;

/**
 * @author Regis
 *
 */
public class Menu_UsuarioED extends MasterED implements Serializable {

	private static final long serialVersionUID = -5265898544596369729L;

	public Menu_UsuarioED() {
		super();
	}

	private int oid_Menu_Usuario;
	private int oid_Usuario;
	private int oid_Menu_Perfil_Sistema;
	private int oid_Sistema;
	private int oid_Menu_Sistema;
	private int oid_Perfil_Menu;
	private String dt_stamp;
	private String usuario_stamp;
	private String dm_stamp;
	
	public String getDm_stamp() {
		return dm_stamp;
	}
	public void setDm_stamp(String dm_stamp) {
		this.dm_stamp = dm_stamp;
	}
	public String getDt_stamp() {
		return dt_stamp;
	}
	public void setDt_stamp(String dt_stamp) {
		this.dt_stamp = dt_stamp;
	}
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
	public int getOid_Menu_Usuario() {
		return oid_Menu_Usuario;
	}
	public void setOid_Menu_Usuario(int oid_Menu_Usuario) {
		this.oid_Menu_Usuario = oid_Menu_Usuario;
	}
	public int getOid_Perfil_Menu() {
		return oid_Perfil_Menu;
	}
	public void setOid_Perfil_Menu(int oid_Perfil_Menu) {
		this.oid_Perfil_Menu = oid_Perfil_Menu;
	}
	public int getOid_Sistema() {
		return oid_Sistema;
	}
	public void setOid_Sistema(int oid_Sistema) {
		this.oid_Sistema = oid_Sistema;
	}
	public int getOid_Usuario() {
		return oid_Usuario;
	}
	public void setOid_Usuario(int oid_Usuario) {
		this.oid_Usuario = oid_Usuario;
	}
	public String getUsuario_stamp() {
		return usuario_stamp;
	}
	public void setUsuario_stamp(String usuario_stamp) {
		this.usuario_stamp = usuario_stamp;
	}

}
