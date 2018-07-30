package com.master.root;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import auth.OracleConnection2;

import com.master.util.Excecoes;

public class Tipo_FaturamentoBean{

	private int oid_Tipo_Faturamento;
	private String CD_Tipo_Faturamento;
	private String NM_Tipo_Faturamento;
	private String DM_Multiplo;


	public Tipo_FaturamentoBean() {}



	public String getCD_Tipo_Faturamento() {
		return CD_Tipo_Faturamento;
	}
	public void setCD_Tipo_Faturamento(String tipo_Faturamento) {
		CD_Tipo_Faturamento = tipo_Faturamento;
	}
	public String getDM_Multiplo() {
		return DM_Multiplo;
	}
	public void setDM_Multiplo(String multiplo) {
		DM_Multiplo = multiplo;
	}
	public String getNM_Tipo_Faturamento() {
		return NM_Tipo_Faturamento;
	}
	public void setNM_Tipo_Faturamento(String tipo_Faturamento) {
		NM_Tipo_Faturamento = tipo_Faturamento;
	}
	public int getOid_Tipo_Faturamento() {
		return oid_Tipo_Faturamento;
	}
	public void setOid_Tipo_Faturamento(int oid_Tipo_Faturamento) {
		this.oid_Tipo_Faturamento = oid_Tipo_Faturamento;
	}
	
	
	
	public void insert() throws Exception{

		Connection conn = null;

		try{
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}

		try{
			Statement stmt = conn.createStatement();
			ResultSet cursor = stmt.executeQuery("SELECT MAX(OID_Tipo_Faturamento) FROM Tipos_Faturamentos");

			while (cursor.next()){
				int oid = cursor.getInt(1);
				setOid_Tipo_Faturamento(oid + 1);
			}
			cursor.close();
			stmt.close();
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}

		StringBuffer buff = new StringBuffer();
		buff.append("INSERT INTO Tipos_Faturamentos " +
				    "(OID_Tipo_Faturamento, CD_Tipo_Faturamento, NM_Tipo_Faturamento, DM_Multiplo) ");
		buff.append("VALUES (?,?,?,?)");

		try{
			PreparedStatement pstmt = conn.prepareStatement(buff.toString());
			pstmt.setInt(1, getOid_Tipo_Faturamento());
			pstmt.setString(2, getCD_Tipo_Faturamento());
			pstmt.setString(3, getNM_Tipo_Faturamento());
			pstmt.setString(4, getDM_Multiplo());
			pstmt.executeUpdate();
		}
		catch(Exception e){
			conn.rollback();
			e.printStackTrace();
			throw e;
		}

		try{
			conn.commit();
			conn.close();
		}catch(Exception e){
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
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}

		StringBuffer buff = new StringBuffer();
		buff.append("UPDATE Tipos_Faturamentos SET CD_Tipo_Faturamento=?, NM_Tipo_Faturamento=?, DM_Multiplo=?");
		buff.append("WHERE OID_Tipo_Faturamento=?");

		try{
			PreparedStatement pstmt = conn.prepareStatement(buff.toString());
			pstmt.setString(1, getCD_Tipo_Faturamento());
			pstmt.setString(2, getNM_Tipo_Faturamento());
			pstmt.setString(3, getDM_Multiplo());
			pstmt.setInt(4, getOid_Tipo_Faturamento());
			pstmt.executeUpdate();
		}
		catch(Exception e){
			conn.rollback();
			e.printStackTrace();
			throw e;
		}

		try{
			conn.commit();
			conn.close();
		}
		catch(Exception e){
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
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}

		StringBuffer buff = new StringBuffer();
		buff.append("DELETE FROM Tipos_Faturamentos ");
		buff.append("WHERE OID_Tipo_Faturamento=?");

		try{
			PreparedStatement pstmt = conn.prepareStatement(buff.toString());
			pstmt.setInt(1, getOid_Tipo_Faturamento());
			pstmt.executeUpdate();
		}
		catch(Exception e){
			conn.rollback();
			e.printStackTrace();
			throw e;
		}

		try{
			conn.commit();
			conn.close();
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}

	public static final Tipo_FaturamentoBean getByOID(int oid) throws Exception{

		Connection conn = null;
	    
		try{
	        conn = OracleConnection2.getWEB();
	        conn.setAutoCommit(false);
	    }
		catch(Exception e){
	        e.printStackTrace();
	        throw e;
	    }
	    
	    Tipo_FaturamentoBean tf = new Tipo_FaturamentoBean();
	    
	    try{
	    	
	        StringBuffer buff = new StringBuffer();
	        buff.append("SELECT * FROM Tipos_Faturamentos ");
	        buff.append("WHERE OID_Tipo_Faturamento= " + oid);
	        
	        Statement stmt = conn.createStatement();
	        ResultSet cursor = stmt.executeQuery(buff.toString());
	        
	        if (cursor.next()){
	        	tf.setOid_Tipo_Faturamento(cursor.getInt("oid_tipo_faturamento"));
	        	tf.setCD_Tipo_Faturamento(cursor.getString("cd_tipo_faturamento"));
	        	tf.setNM_Tipo_Faturamento(cursor.getString("nm_tipo_faturamento"));
	        	tf.setDM_Multiplo(cursor.getString("dm_multiplo"));
	        }
	        cursor.close();
	        stmt.close();
	        conn.close();
	    }
	    catch(SQLException e){
	        throw new Excecoes(e.getMessage(), e, Tipo_FaturamentoBean.class.getName(), "getByOID(int oid)");
	    }
	    return tf;
	}


	public static final Tipo_FaturamentoBean getByCD_Tipo_Faturamento(String CD_Tipo_Faturamento) throws Exception{

		Connection conn = null;
	    
		try{
	        conn = OracleConnection2.getWEB();
	        conn.setAutoCommit(false);
	    }
		catch(Exception e){
	        e.printStackTrace();
	        throw e;
	    }
	    
		Tipo_FaturamentoBean tf = new Tipo_FaturamentoBean();
	    
	    try{
	        StringBuffer buff = new StringBuffer();
	        buff.append("SELECT * FROM Tipos_Faturamentos ");
	        buff.append("WHERE CD_Tipo_Faturamento= '" + CD_Tipo_Faturamento + "'");
	        
//	        // System.out.println("Tipo_Fat.getByCD_Tipo_Faturamento() sql = "+buff.toString());
	        
	        Statement stmt = conn.createStatement();
	        ResultSet cursor = stmt.executeQuery(buff.toString());
	        
	        if (cursor.next()){
	        	tf.setOid_Tipo_Faturamento(cursor.getInt("oid_tipo_faturamento"));
	        	tf.setCD_Tipo_Faturamento(cursor.getString("cd_tipo_faturamento"));
	        	tf.setNM_Tipo_Faturamento(cursor.getString("nm_tipo_faturamento"));
	        	tf.setDM_Multiplo(cursor.getString("dm_multiplo"));
	        }
	        cursor.close();
	        stmt.close();
	        conn.close();
	    }
	    catch(Exception e){
	        e.printStackTrace();
	    }
	    return tf;
	}



	public static final List getByNM_Tipo_Faturamento(String NM_Tipo_Faturamento) throws Exception{

		Connection conn = null;
		
		try{
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}

		List Tipos_Faturamentos_Lista = new ArrayList();
		
		try{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT * FROM Tipos_Faturamentos ");
			buff.append("WHERE NM_Tipo_Faturamento LIKE '%" + NM_Tipo_Faturamento + "%'");

			Statement stmt = conn.createStatement();
			ResultSet cursor = stmt.executeQuery(buff.toString());

			while (cursor.next()){
				Tipo_FaturamentoBean tf = new Tipo_FaturamentoBean();
	        	tf.setOid_Tipo_Faturamento(cursor.getInt("oid_tipo_faturamento"));
	        	tf.setCD_Tipo_Faturamento(cursor.getString("cd_tipo_faturamento"));
	        	tf.setNM_Tipo_Faturamento(cursor.getString("nm_tipo_faturamento"));
	        	tf.setDM_Multiplo(cursor.getString("dm_multiplo"));
				Tipos_Faturamentos_Lista.add(tf);
			}
			cursor.close();
			stmt.close();
			conn.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return Tipos_Faturamentos_Lista;
	}

	public static final List getAll() throws Exception{
		
		Connection conn = null;
		
		try{
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}

		List Tipos_Faturamentos_Lista = new ArrayList();
		
		try{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT * FROM Tipos_Faturamentos");

			Statement stmt = conn.createStatement();
			ResultSet cursor = stmt.executeQuery(buff.toString());

			while (cursor.next()){
				Tipo_FaturamentoBean tf = new Tipo_FaturamentoBean();
	        	tf.setOid_Tipo_Faturamento(cursor.getInt("oid_tipo_faturamento"));
	        	tf.setCD_Tipo_Faturamento(cursor.getString("cd_tipo_faturamento"));
	        	tf.setNM_Tipo_Faturamento(cursor.getString("nm_tipo_faturamento"));
	        	tf.setDM_Multiplo(cursor.getString("dm_multiplo"));
				Tipos_Faturamentos_Lista.add(tf);
			}
			cursor.close();
			stmt.close();
			conn.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return Tipos_Faturamentos_Lista;
	}

	public static void main(String args[]) throws Exception{

	}
}