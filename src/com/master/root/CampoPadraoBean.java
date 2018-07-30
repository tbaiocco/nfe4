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
import com.master.util.JavaUtil;

public class CampoPadraoBean{

	private int oid_Campo_Padrao;
	private String DM_Campo;
	private String DM_Origem;
	private String NM_Linha1;
	private String TX_Descricao;


	public CampoPadraoBean() {}

	public String getDM_Campo() {
		return DM_Campo;
	}
	public void setDM_Campo(String campo) {
		DM_Campo = campo;
	}
	public String getDM_Origem() {
		return DM_Origem;
	}
	public void setDM_Origem(String origem) {
		DM_Origem = origem;
	}
	public String getNM_Linha1() {
		return NM_Linha1;
	}
	public void setNM_Linha1(String linha1) {
		NM_Linha1 = linha1;
	}
	public int getOid_Campo_Padrao() {
		return oid_Campo_Padrao;
	}
	public void setOid_Campo_Padrao(int oid_Campo_Padrao) {
		this.oid_Campo_Padrao = oid_Campo_Padrao;
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
			ResultSet cursor = stmt.executeQuery("SELECT MAX(oid_campo_padrao) FROM campos_padroes");

			while (cursor.next()){
				int oid = cursor.getInt(1);
				setOid_Campo_Padrao(oid + 1);
			}
			cursor.close();
			stmt.close();
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}

		StringBuffer buff = new StringBuffer();
		buff.append("INSERT INTO campos_padroes " +
				    "(oid_campo_padrao, dm_campo, dm_origem, nm_linha1, tx_descricao) ");
		buff.append("VALUES (?,?,?,?,?)");

		try{
			PreparedStatement pstmt = conn.prepareStatement(buff.toString());
			pstmt.setInt(1, getOid_Campo_Padrao());
			pstmt.setString(2, getDM_Campo());
			pstmt.setString(3, getDM_Origem());
			pstmt.setString(4, getNM_Linha1());
			pstmt.setString(5, getTX_Descricao());
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
		buff.append("UPDATE campos_padroes SET dm_campo=?, dm_origem=?, nm_linha1=?, tx_descricao=?");
		buff.append("WHERE oid_campo_padrao=?");

		try{
			PreparedStatement pstmt = conn.prepareStatement(buff.toString());
			pstmt.setString(1, getDM_Campo());
			pstmt.setString(2, getDM_Origem());
			pstmt.setString(3, getNM_Linha1());
			pstmt.setString(4, getTX_Descricao());
			pstmt.setInt(5, getOid_Campo_Padrao());
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
		buff.append("DELETE FROM campos_padroes ");
		buff.append("WHERE oid_campo_padrao=?");

		try{
			PreparedStatement pstmt = conn.prepareStatement(buff.toString());
			pstmt.setInt(1, getOid_Campo_Padrao());
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

	public static final CampoPadraoBean getByOID(int oid) throws Exception{

		Connection conn = null;

		try{
	        conn = OracleConnection2.getWEB();
	        conn.setAutoCommit(false);
	    }
		catch(Exception e){
	        e.printStackTrace();
	        throw e;
	    }

	    CampoPadraoBean cpBean = new CampoPadraoBean();

	    try{

	        StringBuffer buff = new StringBuffer();
	        buff.append("SELECT * FROM campos_padroes ");
	        buff.append("WHERE oid_campo_padrao= " + oid);

	        Statement stmt = conn.createStatement();
	        ResultSet cursor = stmt.executeQuery(buff.toString());

	        if (cursor.next()){
	        	cpBean.setOid_Campo_Padrao(cursor.getInt("oid_campo_padrao"));
	        	cpBean.setDM_Campo(cursor.getString("dm_campo"));
	        	cpBean.setDM_Origem(cursor.getString("dm_origem"));
	        	cpBean.setNM_Linha1(cursor.getString("nm_linha1"));
	        	cpBean.setTX_Descricao(cursor.getString("tx_descricao"));
	        }
	        cursor.close();
	        stmt.close();
	        conn.close();
	    }
	    catch(SQLException e){
	        throw new Excecoes(e.getMessage(), e, CampoPadraoBean.class.getName(), "getByOID(int oid)");
	    }
	    return cpBean;
	}


	public static final CampoPadraoBean getByCampo_Origem(String dm_Campo, String dm_Origem) throws Exception{

		Connection conn = null;

		try{
	        conn = OracleConnection2.getWEB();
	        conn.setAutoCommit(false);
	    }
		catch(Exception e){
	        e.printStackTrace();
	        throw e;
	    }

		CampoPadraoBean cpBean = new CampoPadraoBean();

	    try{
	        StringBuffer buff = new StringBuffer();
	        buff.append("SELECT * FROM campos_padroes ");
	        buff.append("WHERE dm_campo= '" + dm_Campo + "' ");
	        buff.append("AND dm_origem= '" + dm_Origem + "'");

//	        // System.out.println("CampoPadraoBean.getByDM_Campo_Dm_Origem() sql = "+buff.toString());

	        Statement stmt = conn.createStatement();
	        ResultSet cursor = stmt.executeQuery(buff.toString());

	        if (cursor.next()){
	            cpBean.setOid_Campo_Padrao(cursor.getInt("oid_campo_padrao"));
	            cpBean.setDM_Campo(cursor.getString("dm_campo"));
	            cpBean.setDM_Origem(cursor.getString("dm_origem"));
	            cpBean.setNM_Linha1(cursor.getString("nm_linha1"));
	            cpBean.setTX_Descricao(cursor.getString("tx_descricao"));
	        }
	        cursor.close();
	        stmt.close();
	        conn.close();
	    }
	    catch(Exception e){
	        e.printStackTrace();
	    }
	    return cpBean;
	}


	public static final List getByCampo_Origem_Lista(String dm_Campo, String dm_Origem) throws Exception{

		Connection conn = null;

		try{
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}

		List CampoPadraoBean_Lista = new ArrayList();

		try{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT * FROM campos_padroes WHERE 1=1 ");

			if(JavaUtil.doValida(dm_Campo)){
			    buff.append("AND dm_campo= '" + dm_Campo + "' ");
			}
			if(JavaUtil.doValida(dm_Origem)){
			    buff.append("AND dm_origem= '" + dm_Origem + "'");
			}

//			// System.out.println("CampoPadraoBean.getByCampo_Origem_Lista() sql = " + buff.toString());

			Statement stmt = conn.createStatement();
			ResultSet cursor = stmt.executeQuery(buff.toString());

			while (cursor.next()){
			    CampoPadraoBean cpBean = new CampoPadraoBean();
			    cpBean.setOid_Campo_Padrao(cursor.getInt("oid_campo_padrao"));
			    cpBean.setDM_Campo(cursor.getString("dm_campo"));
			    cpBean.setDM_Origem(cursor.getString("dm_origem"));
			    cpBean.setNM_Linha1(cursor.getString("nm_linha1"));
			    cpBean.setTX_Descricao(cursor.getString("tx_descricao"));
			    CampoPadraoBean_Lista.add(cpBean);
			}
			cursor.close();
			stmt.close();
			conn.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return CampoPadraoBean_Lista;
	}


	public static final List getByNM_Linha1(String nm_Linha1) throws Exception{

		Connection conn = null;

		try{
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}

		List CampoPadraoBean_Lista = new ArrayList();

		try{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT * FROM campos_padroes ");
			buff.append("WHERE nm_linha1 LIKE '%" + nm_Linha1 + "%'");

			Statement stmt = conn.createStatement();
			ResultSet cursor = stmt.executeQuery(buff.toString());

			while (cursor.next()){
			    CampoPadraoBean cpBean = new CampoPadraoBean();
			    cpBean.setOid_Campo_Padrao(cursor.getInt("odi_campo_padrao"));
			    cpBean.setDM_Campo(cursor.getString("dm_campo"));
			    cpBean.setDM_Origem(cursor.getString("dm_origem"));
			    cpBean.setNM_Linha1(cursor.getString("nm_linha1"));
			    cpBean.setTX_Descricao(cursor.getString("tx_descricao"));
			    CampoPadraoBean_Lista.add(cpBean);
			}
			cursor.close();
			stmt.close();
			conn.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return CampoPadraoBean_Lista;
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

		List CampoPadraoBean_Lista = new ArrayList();

		try{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT * FROM campos_padroes");

			Statement stmt = conn.createStatement();
			ResultSet cursor = stmt.executeQuery(buff.toString());

			while (cursor.next()){
			    CampoPadraoBean cpBean = new CampoPadraoBean();
			    cpBean.setOid_Campo_Padrao(cursor.getInt("oid_campo_padrao"));
			    cpBean.setDM_Campo(cursor.getString("dm_campo"));
			    cpBean.setDM_Origem(cursor.getString("dm_origem"));
			    cpBean.setNM_Linha1(cursor.getString("nm_linha1"));
			    cpBean.setTX_Descricao(cursor.getString("tx_descricao"));
			    CampoPadraoBean_Lista.add(cpBean);
			}
			cursor.close();
			stmt.close();
			conn.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return CampoPadraoBean_Lista;
	}


	public static final String montaComboDM_Origem(){

	    String combo = "<select size=\"1\" name=\"FT_DM_Origem\">\"" +
	    			   "<option value=\"\" selected></option>" +
        			   "<option value=\"C\">Conhecimento</option>" +
        			   "<option value=\"M\">MIC</option>" +
        			   "<option value=\"O\">Ordem de Pagamento</option>" +
        			   "<option value=\"P\">Pedido de Carga</option>" +
        			   "</select>";

	    return combo;
	}


	public static void main(String args[]) throws Exception{

	}

	public String getTX_Descricao() {
		return TX_Descricao;
	}

	public void setTX_Descricao(String descricao) {
		TX_Descricao = descricao;
	}
}