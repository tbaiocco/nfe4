package com.master.root;

import java.sql.*;
import java.util.*;
import auth.*;

public class TipoStatusClientesBean{

    private String oid_Pessoa;
    private String NR_Aduana;
	private String oidTipoStatus;
	private String cdTipoStatus;
	private String nmTipoStatus;
	private String dmStatus;
	private String usuarioStamp;
	private String dtStamp;
	private String dmStamp;
    private int oid;
    
	public TipoStatusClientesBean() {}



	public void insert() throws Exception{

		Connection conn = null;
		try{
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		}
		catch (Exception e){
			e.printStackTrace();
			throw e;
		}

		StringBuffer buff = new StringBuffer();
		buff.append("INSERT INTO status_clientes (oid_cliente, oid_tipo_status, dm_status, dt_stamp, usuario_stamp, dm_stamp) ");
		buff.append("VALUES (?,?,?,?,?,?)");
		
		try{
			PreparedStatement pstmt = conn.prepareStatement(buff.toString());
			pstmt.setString(1, getOid_Pessoa());
			pstmt.setInt(2, Integer.parseInt(getOidTipoStatus()));
			pstmt.setString(3, getDmStatus());
			pstmt.setString(4, getDtStamp());
			pstmt.setString(5, getUsuarioStamp());
			pstmt.setString(6, getDmStamp());

			pstmt.executeUpdate();
		}
		catch (Exception e){
			conn.rollback();
			e.printStackTrace();
			throw e;
		}

		try{
			conn.commit();
			conn.close();
		}
		catch (Exception e){
			e.printStackTrace();
			throw e;
		}
	}

	public void update() throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try{
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		}
		catch (Exception e){
			e.printStackTrace();
			throw e;
		}

		StringBuffer buff = new StringBuffer();
		buff.append("UPDATE status_clientes SET dm_status=?, dt_Stamp=?, usuario_stamp=?, dm_stamp=? ");
		buff.append("WHERE oid_cliente=? AND oid_tipo_status=?");
		
		try{
			PreparedStatement pstmt = conn.prepareStatement(buff.toString());
			pstmt.setString(1, getDmStatus());
			pstmt.setString(2, getDtStamp());
			pstmt.setString(3, getUsuarioStamp());
			pstmt.setString(4, getDmStamp());
			pstmt.setString(5, getOid_Pessoa());
			pstmt.setInt(6, Integer.parseInt(getOidTipoStatus()));
			pstmt.executeUpdate();
		}
		catch (Exception e){
			conn.rollback();
			e.printStackTrace();
			throw e;
		}
		
		try{
			conn.commit();
			conn.close();
		}
		catch (Exception e){
			e.printStackTrace();
			throw e;
		}
	}

	public void delete() throws Exception{
		Connection conn = null;
		try{
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		}
		catch (Exception e){
			e.printStackTrace();
			throw e;
		}

		StringBuffer buff = new StringBuffer();
		buff.append("DELETE FROM status_clientes ");
		buff.append("WHERE oid_cliente = ? AND oid_tipo_status = ?");

		try{
			PreparedStatement pstmt = conn.prepareStatement(buff.toString());
			pstmt.setString(1, getOid_Pessoa());
			pstmt.setInt(2, Integer.parseInt(getOidTipoStatus()));
			pstmt.executeUpdate();
		}
		catch (Exception e){
			conn.rollback();
			e.printStackTrace();
			throw e;
		}

		try{
			conn.commit();
			conn.close();
		}
		catch (Exception e){
			e.printStackTrace();
			throw e;
		}
	}


    public String getCdTipoStatus() {
        return cdTipoStatus;
    }
    public void setCdTipoStatus(String cdTipoStatus) {
        this.cdTipoStatus = cdTipoStatus;
    }
    public String getDmStamp() {
        return dmStamp;
    }
    public void setDmStamp(String dmStamp) {
        this.dmStamp = dmStamp;
    }
    public String getDtStamp() {
        return dtStamp;
    }
    public void setDtStamp(String dtStamp) {
        this.dtStamp = dtStamp;
    }
    public String getNmTipoStatus() {
        return nmTipoStatus;
    }
    public void setNmTipoStatus(String nmTipoStatus) {
        this.nmTipoStatus = nmTipoStatus;
    }
    public String getNR_Aduana() {
        return NR_Aduana;
    }
    public void setNR_Aduana(String aduana) {
        NR_Aduana = aduana;
    }
    public int getOid() {
        return oid;
    }
    public void setOid(int oid) {
        this.oid = oid;
    }
    public String getOid_Pessoa() {
        return oid_Pessoa;
    }
    public void setOid_Pessoa(String oid_Pessoa) {
        this.oid_Pessoa = oid_Pessoa;
    }
    public String getOidTipoStatus() {
        return oidTipoStatus;
    }
    public void setOidTipoStatus(String oidTipoStatus) {
        this.oidTipoStatus = oidTipoStatus;
    }
    public String getUsuarioStamp() {
        return usuarioStamp;
    }
    public void setUsuarioStamp(String usuarioStamp) {
        this.usuarioStamp = usuarioStamp;
    }
    public String getDmStatus() {
        return dmStatus;
    }
    public void setDmStatus(String dmStatus) {
        this.dmStatus = dmStatus;
    }
	public static void main(String args[])throws Exception{	}

}
