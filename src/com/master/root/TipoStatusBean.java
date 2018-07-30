package com.master.root;

import java.sql.*;
import java.util.*;
import auth.*;

public class TipoStatusBean{
    
	private String oidTipoStatus;
	private String cdTipoStatus;
	private String nmTipoStatus;
	private String dmTipoStatus;
	private String usuarioStamp;
	private String dtStamp;
	private String dmStamp;
    private String oid_Pessoa;
    private int oid;

	public TipoStatusBean() {}

	
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

		try{
			Statement stmt = conn.createStatement();
			ResultSet cursor = stmt.executeQuery("SELECT MAX(oid_tipo_status) FROM tipo_status");

			while (cursor.next()){
				oid = cursor.getInt(1);
				oid = oid + 1;
			}
			cursor.close();
			stmt.close();
		}
		catch (Exception e){
			e.printStackTrace();
			throw e;
		}

		StringBuffer buff = new StringBuffer();
		buff.append("INSERT INTO tipo_status (oid_tipo_status, cd_tipo_status, nm_tipo_status, dt_stamp, usuario_Stamp, dm_stamp) ");
		buff.append("VALUES (?,?,?,?,?,?)");

		try{
			PreparedStatement pstmt = conn.prepareStatement(buff.toString());
			pstmt.setInt(1, oid);
			pstmt.setString(2, getCdTipoStatus());
			pstmt.setString(3, getNmTipoStatus());
			pstmt.setString(4, getDtStamp());
			pstmt.setString(5, getUsuarioStamp());
			pstmt.setString(6, getDmStamp());
			
//			// System.out.println("TipoStatusBean.update() - sql = "+pstmt.toString());

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

	public void update() throws Exception{

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
		buff.append("UPDATE tipo_status SET cd_tipo_status=?, nm_tipo_status=?, dt_stamp=?, usuario_Stamp=?, dm_stamp=? ");
		buff.append("WHERE oid_tipo_status=?");

		try{
			PreparedStatement pstmt = conn.prepareStatement(buff.toString());
			pstmt.setString(1, getCdTipoStatus());
			pstmt.setString(2, getNmTipoStatus());
			pstmt.setString(3, getDtStamp());
			pstmt.setString(4, getUsuarioStamp());
			pstmt.setString(5, getDmStamp());
			pstmt.setInt(6, Integer.parseInt(getOidTipoStatus()));

//			// System.out.println("TipoStatusBean.update() sql - "+pstmt.toString());
			
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
		buff.append("DELETE FROM tipo_status WHERE oid_tipo_status=?");

		try{
			PreparedStatement pstmt = conn.prepareStatement(buff.toString());
			pstmt.setInt(1, Integer.parseInt(getOidTipoStatus()));
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

	public static final TipoStatusBean getByOID(int oid) throws Exception{

		Connection conn = null;
		try{
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		}
		catch (Exception e){
			e.printStackTrace();
			throw e;
		}

		TipoStatusBean tsb = new TipoStatusBean();
		try{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT oid_tipo_status, cd_tipo_status, nm_tipo_status ");
			buff.append("FROM tipo_status ");
			buff.append("WHERE oid_tipo_status = " + oid);

//			// System.out.println("TipoStatusBean.getByOID() - sql = "+buff.toString());
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = stmt.executeQuery(buff.toString());

			while (cursor.next()){
                tsb.setOidTipoStatus(String.valueOf(cursor.getInt(1)));
                tsb.setCdTipoStatus(cursor.getString(2));
                tsb.setNmTipoStatus(cursor.getString(3));
                tsb.setDmTipoStatus(cursor.getString(4));
			}
			cursor.close();
			stmt.close();
			conn.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return tsb;
	}
	
	public static final TipoStatusBean getByOIDStatusCliente(int oid, String oidPessoa) throws Exception{

		Connection conn = null;
		try{
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		}
		catch (Exception e){
			e.printStackTrace();
			throw e;
		}

		TipoStatusBean tsb = new TipoStatusBean();
		try{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT TS.oid_tipo_status, TS.cd_tipo_status, TS.nm_tipo_status, SC.dm_status ");
			buff.append("FROM tipo_status TS, status_clientes SC ");
			buff.append("WHERE TS.oid_tipo_status = " + oid);
			buff.append("AND TS.oid_tipo_status = SC.oid_tipo_status ");
			buff.append("AND SC.oid_cliente = " + oidPessoa);

//			// System.out.println("TipoStatusBean.getByOID() - sql = "+buff.toString());
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = stmt.executeQuery(buff.toString());

			while (cursor.next()){
                tsb.setOidTipoStatus(String.valueOf(cursor.getInt(1)));
                tsb.setCdTipoStatus(cursor.getString(2));
                tsb.setNmTipoStatus(cursor.getString(3));
                tsb.setDmTipoStatus(cursor.getString(4));
			}
			cursor.close();
			stmt.close();
			conn.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return tsb;
	}

	public static final TipoStatusBean getByCDTipoStatus(String cdTipoStatus) throws Exception{

		Connection conn = null;
		try{
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		}
		catch (Exception e){
			e.printStackTrace();
			throw e;
		}

		TipoStatusBean stb = new TipoStatusBean();
		try{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT oid_tipo_status, cd_tipo_status, nm_tipo_status FROM tipo_status ");
			buff.append("WHERE cd_tipo_status= '" + cdTipoStatus + "'");

			Statement stmt = conn.createStatement();
			ResultSet cursor = stmt.executeQuery(buff.toString());

			while (cursor.next()){
			    stb.setOidTipoStatus(String.valueOf(cursor.getInt(1)));
			    stb.setCdTipoStatus(cursor.getString(2));
			    stb.setNmTipoStatus(cursor.getString(3));
			}
			cursor.close();
			stmt.close();
			conn.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return stb;
	}

	public static final List getByNMTipoStatus(String nmTipoStatus) throws Exception{

		Connection conn = null;
		try{
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		}
		catch (Exception e){
			e.printStackTrace();
			throw e;
		}

		List TiposStatusLista = new ArrayList();
		try{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT oid_tipo_status, cd_tipo_status, nm_tipo_status FROM tipo_status ");
			buff.append("WHERE nm_tipo_status LIKE '" + nmTipoStatus + "%'");

			Statement stmt = conn.createStatement();
			ResultSet cursor = stmt.executeQuery(buff.toString());

			while (cursor.next()){
			    TipoStatusBean tsb = new TipoStatusBean();
			    tsb.setOidTipoStatus(String.valueOf(cursor.getInt(1)));
			    tsb.setCdTipoStatus(cursor.getString(2));
			    tsb.setNmTipoStatus(cursor.getString(3));
			    TiposStatusLista.add(tsb);
			}
			cursor.close();
			stmt.close();
			conn.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return TiposStatusLista;
	}

	public static final List getAll() throws Exception{
		Connection conn = null;
		try{
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		}
		catch (Exception e){
			e.printStackTrace();
			throw e;
		}

		List Aduanas_Lista = new ArrayList();
		try{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT oid_tipo_status, cd_tipo_status, nm_tipo_status FROM tipo_status");

			Statement stmt = conn.createStatement();
			ResultSet cursor = stmt.executeQuery(buff.toString());

			while (cursor.next()){
			    TipoStatusBean tsb = new TipoStatusBean();
				tsb.setOidTipoStatus(String.valueOf(cursor.getInt(1)));
				tsb.setCdTipoStatus(cursor.getString(2));
				tsb.setNmTipoStatus(cursor.getString(3));

				Aduanas_Lista.add(tsb);
			}
			cursor.close();
			stmt.close();
			conn.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return Aduanas_Lista;
	}

	public static void main(String args[]) throws Exception{
//		TipoStatusBean tsb = getByOID(1, "");  
		// //// System.out.println(p.getOID());
		// //// System.out.println(p.getCD_Aduana());
		// //// System.out.println(p.getNM_Aduana());
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
    
    public String getDmTipoStatus() {
        return dmTipoStatus;
    }
    public void setDmTipoStatus(String dmTipoStatus) {
        this.dmTipoStatus = dmTipoStatus;
    }
}