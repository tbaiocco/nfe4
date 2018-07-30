package com.master.root;

import java.sql.*;
import java.util.*;
import auth.*;

public class AduanasClientesBean{
	private int CD_Aduana;
	private String NM_Aduana;
	private String Usuario_Stamp;
	private String Dt_Stamp;
	private String Dm_Stamp;
	private String oid_Pessoa;
    private String NR_Aduana;

	public AduanasClientesBean() {}



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
		buff.append("INSERT INTO aduanas_clientes (oid_cliente, oid_aduana, dt_Stamp, usuario_stamp, dm_Stamp) ");
		buff.append("VALUES (?,?,?,?,?)");
		
		try{
			PreparedStatement pstmt = conn.prepareStatement(buff.toString());
			pstmt.setString(1, getOid_Pessoa());
			pstmt.setInt(2, getCD_Aduana());
			pstmt.setString(3, getDt_Stamp());
			pstmt.setString(4, getUsuario_Stamp());
			pstmt.setString(5, getDm_Stamp());

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
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Aduana do DSN
			// o NM_Aduana de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		/*
		 * Define o update.
		 */
		StringBuffer buff = new StringBuffer();
		buff.append("UPDATE Aduanas SET NM_Aduana=?, Dt_Stamp=?, Usuario_Stamp=?, Dm_Stamp=?, NR_Aduana=? ");
		buff.append("WHERE OID_Aduana=?");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setString(1, getNM_Aduana());
			pstmt.setString(2, getDt_Stamp());
			pstmt.setString(3, getUsuario_Stamp());
			pstmt.setString(4, getDm_Stamp());
			pstmt.setString(5, getNR_Aduana());

			pstmt.setString(6, getOid_Pessoa());
			pstmt.executeUpdate();
		} catch (Exception e)
		{
			conn.rollback();
			e.printStackTrace();
			throw e;
		}
		/*
		 * Faz o commit e fecha a conexão.
		 */
		try
		{
			conn.commit();
			conn.close();
		} catch (Exception e)
		{
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
		buff.append("DELETE FROM aduanas_clientes ");
		buff.append("WHERE oid_cliente = ? AND oid_Aduana = ?");

		try{
			PreparedStatement pstmt = conn.prepareStatement(buff.toString());
			pstmt.setString(1, getOid_Pessoa());
			pstmt.setInt(2, getCD_Aduana());
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


	
    public int getCD_Aduana() {
        return CD_Aduana;
    }
    public void setCD_Aduana(int aduana) {
        CD_Aduana = aduana;
    }
    public String getDm_Stamp() {
        return Dm_Stamp;
    }
    public void setDm_Stamp(String dm_Stamp) {
        Dm_Stamp = dm_Stamp;
    }
    public String getDt_Stamp() {
        return Dt_Stamp;
    }
    public void setDt_Stamp(String dt_Stamp) {
        Dt_Stamp = dt_Stamp;
    }
    public String getNM_Aduana() {
        return NM_Aduana;
    }
    public void setNM_Aduana(String aduana) {
        NM_Aduana = aduana;
    }
    public String getNR_Aduana() {
        return NR_Aduana;
    }
    public void setNR_Aduana(String aduana) {
        NR_Aduana = aduana;
    }
    public String getOid_Pessoa() {
        return oid_Pessoa;
    }
    public void setOid_Pessoa(String oid_Pessoa) {
        this.oid_Pessoa = oid_Pessoa;
    }
    public String getUsuario_Stamp() {
        return Usuario_Stamp;
    }
    public void setUsuario_Stamp(String usuario_Stamp) {
        Usuario_Stamp = usuario_Stamp;
    }
    
    
	public static void main(String args[])throws Exception{	}

}
