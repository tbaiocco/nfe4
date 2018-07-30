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

public class Permisso_UnidadeBean{

	private int oid_Permisso_Unidade;
	private int oid_Unidade_Origem;
	private int oid_Unidade_Destino;
	private int oid_Unidade_Fronteira;
	private String nr_Permisso;
	private int oid_Aidof;
	private int km_Distancia;
	private String hr_Distancia;
	private String nm_Tramo1;
	private String nm_Tramo2;
	private String nm_Tramo3;
	private String nm_Tramo4;
	private double pe_Frete1;
	private double pe_Frete2;
	private double pe_Frete3;
	private double pe_Frete4;
	private double pe_Frete_Externo;
	
	private int oid_Aidof_Mic;
	
	

	public Permisso_UnidadeBean() {}
	
	
	public int getOid_Aidof() {
		return oid_Aidof;
	}
	public void setOid_Aidof(int oid_Aidof) {
		this.oid_Aidof = oid_Aidof;
	}
	public int getOid_Aidof_Mic() {
		return oid_Aidof_Mic;
	}
	public void setOid_Aidof_Mic(int oid_Aidof_Mic) {
		this.oid_Aidof_Mic = oid_Aidof_Mic;
	}
	public String getHr_Distancia() {
		return hr_Distancia;
	}
	public void setHr_Distancia(String hr_Distancia) {
		this.hr_Distancia = hr_Distancia;
	}
	public int getKm_Distancia() {
		return km_Distancia;
	}
	public void setKm_Distancia(int km_Distancia) {
		this.km_Distancia = km_Distancia;
	}
	public String getNm_Tramo1() {
		return nm_Tramo1;
	}
	public void setNm_Tramo1(String nm_Tramo1) {
		this.nm_Tramo1 = nm_Tramo1;
	}
	public String getNm_Tramo2() {
		return nm_Tramo2;
	}
	public void setNm_Tramo2(String nm_Tramo2) {
		this.nm_Tramo2 = nm_Tramo2;
	}
	public String getNm_Tramo3() {
		return nm_Tramo3;
	}
	public void setNm_Tramo3(String nm_Tramo3) {
		this.nm_Tramo3 = nm_Tramo3;
	}
	public String getNm_Tramo4() {
		return nm_Tramo4;
	}
	public void setNm_Tramo4(String nm_Tramo4) {
		this.nm_Tramo4 = nm_Tramo4;
	}
	public String getNr_Permisso() {
		return nr_Permisso;
	}
	public void setNr_Permisso(String nr_Permisso) {
		this.nr_Permisso = nr_Permisso;
	}
	public int getOid_Permisso_Unidade() {
		return oid_Permisso_Unidade;
	}
	public void setOid_Permisso_Unidade(int oid_Permisso_Unidade) {
		this.oid_Permisso_Unidade = oid_Permisso_Unidade;
	}
	public int getOid_Unidade_Destino() {
		return oid_Unidade_Destino;
	}
	public void setOid_Unidade_Destino(int oid_Unidade_Destino) {
		this.oid_Unidade_Destino = oid_Unidade_Destino;
	}
	public int getOid_Unidade_Fronteira() {
		return oid_Unidade_Fronteira;
	}
	public void setOid_Unidade_Fronteira(int oid_Unidade_Fronteira) {
		this.oid_Unidade_Fronteira = oid_Unidade_Fronteira;
	}
	public int getOid_Unidade_Origem() {
		return oid_Unidade_Origem;
	}
	public void setOid_Unidade_Origem(int oid_Unidade_Origem) {
		this.oid_Unidade_Origem = oid_Unidade_Origem;
	}
	public double getPe_Frete_Externo() {
		return pe_Frete_Externo;
	}
	public void setPe_Frete_Externo(double pe_Frete_Externo) {
		this.pe_Frete_Externo = pe_Frete_Externo;
	}	
	public double getPe_Frete1() {
		return pe_Frete1;
	}
	public void setPe_Frete1(double pe_Frete1) {
		this.pe_Frete1 = pe_Frete1;
	}
	public double getPe_Frete2() {
		return pe_Frete2;
	}
	public void setPe_Frete2(double pe_Frete2) {
		this.pe_Frete2 = pe_Frete2;
	}
	public double getPe_Frete3() {
		return pe_Frete3;
	}
	public void setPe_Frete3(double pe_Frete3) {
		this.pe_Frete3 = pe_Frete3;
	}
	public double getPe_Frete4() {
		return pe_Frete4;
	}
	public void setPe_Frete4(double pe_Frete4) {
		this.pe_Frete4 = pe_Frete4;
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
			ResultSet cursor = stmt.executeQuery("SELECT MAX(oid_permisso_unidade) FROM permissos_unidades");

			while (cursor.next()){
				int oid = cursor.getInt(1);
				setOid_Permisso_Unidade(oid + 1);
			}
			cursor.close();
			stmt.close();
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}

		StringBuffer buff = new StringBuffer();
		buff.append("INSERT INTO permissos_unidades " +
				    "(oid_permisso_unidade, oid_unidade_origem, oid_unidade_destino, oid_unidade_fronteira, " +
				    "nr_permisso, oid_aidof, km_distancia, hr_distancia, nm_tramo1, nm_tramo2, nm_tramo3, nm_tramo4, " +
				    "pe_frete1, pe_frete2, pe_frete3, pe_frete4, pe_frete_externo, oid_aidof_Mic)");
		buff.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

		try{
			PreparedStatement pstmt = conn.prepareStatement(buff.toString());
			pstmt.setInt(1, getOid_Permisso_Unidade());
			pstmt.setInt(2, getOid_Unidade_Origem());
			pstmt.setInt(3, getOid_Unidade_Destino());
			pstmt.setInt(4, getOid_Unidade_Fronteira());
			pstmt.setString(5, getNr_Permisso());
			pstmt.setInt(6, getOid_Aidof());
			pstmt.setInt(7, getKm_Distancia());
			pstmt.setString(8, getHr_Distancia());
			pstmt.setString(9, getNm_Tramo1());
			pstmt.setString(10, getNm_Tramo2());
			pstmt.setString(11, getNm_Tramo3());
			pstmt.setString(12, getNm_Tramo4());
			pstmt.setDouble(13, getPe_Frete1());
			pstmt.setDouble(14, getPe_Frete2());
			pstmt.setDouble(15, getPe_Frete3());
			pstmt.setDouble(16, getPe_Frete4());
			pstmt.setDouble(17, getPe_Frete_Externo());
			pstmt.setInt(18, getOid_Aidof_Mic());
			
//			// System.out.println("Permisso_UnidadeBean.inlcui() sql = "+pstmt.toString());
			
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
		buff.append("UPDATE permissos_unidades SET oid_unidade_origem=?, oid_unidade_destino=?, " +
				    "oid_unidade_fronteira=?, nr_permisso=?, oid_aidof=?, km_distancia=?, hr_distancia=?, " +
				    "nm_tramo1=?, nm_tramo2=?, nm_tramo3=?,nm_tramo4=?, " +
				    "pe_frete1=?, pe_frete2=?, pe_frete3=?, pe_frete4=?, pe_frete_externo=?, oid_aidof_mic=? ");
		buff.append("WHERE oid_permisso_unidade=?");

		try{
			PreparedStatement pstmt = conn.prepareStatement(buff.toString());
			pstmt.setInt(1, getOid_Unidade_Origem());
			pstmt.setInt(2, getOid_Unidade_Destino());
			pstmt.setInt(3, getOid_Unidade_Fronteira());
			pstmt.setString(4, getNr_Permisso());
			pstmt.setInt(5, getOid_Aidof());
			pstmt.setInt(6, getKm_Distancia());
			pstmt.setString(7, getHr_Distancia());
			pstmt.setString(8, getNm_Tramo1());
			pstmt.setString(9, getNm_Tramo2());
			pstmt.setString(10, getNm_Tramo3());
			pstmt.setString(11, getNm_Tramo4());
			pstmt.setDouble(12, getPe_Frete1());
			pstmt.setDouble(13, getPe_Frete2());
			pstmt.setDouble(14, getPe_Frete3());
			pstmt.setDouble(15, getPe_Frete4());
			pstmt.setDouble(16, getPe_Frete_Externo());
			pstmt.setInt(17, getOid_Aidof_Mic());
			pstmt.setInt(18, getOid_Permisso_Unidade());
			
//			// System.out.println("Permisso_UnidBean.update() sql = "+pstmt.toString());
			
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
		buff.append("DELETE FROM permissos_unidades ");
		buff.append("WHERE OID_permisso_unidade=?");

		try{
			PreparedStatement pstmt = conn.prepareStatement(buff.toString());
			pstmt.setInt(1, getOid_Permisso_Unidade());
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

	public static final Permisso_UnidadeBean getByOID(int oid) throws Exception{

		Connection conn = null;
	    
		try{
	        conn = OracleConnection2.getWEB();
	        conn.setAutoCommit(false);
	    }
		catch(Exception e){
	        e.printStackTrace();
	        throw e;
	    }
	    
		Permisso_UnidadeBean pu = new Permisso_UnidadeBean();
	    
	    try{
	    	
	        StringBuffer buff = new StringBuffer();
	        buff.append("SELECT * FROM permissos_unidades ");
	        buff.append("WHERE OID_permisso_unidade = " + oid);
	        
	        Statement stmt = conn.createStatement();
	        ResultSet cursor = stmt.executeQuery(buff.toString());
	        
	        if (cursor.next()){
	        	pu.setOid_Permisso_Unidade(cursor.getInt("oid_permisso_unidade"));
	        	pu.setOid_Unidade_Origem(cursor.getInt("oid_unidade_origem"));
	        	pu.setOid_Unidade_Destino(cursor.getInt("oid_unidade_destino"));
	        	pu.setOid_Unidade_Fronteira(cursor.getInt("oid_unidade_fronteira"));
	        	pu.setNr_Permisso(cursor.getString("nr_permisso"));
	        	pu.setOid_Aidof(cursor.getInt("oid_aidof"));
	        	pu.setKm_Distancia(cursor.getInt("km_distancia"));
	        	pu.setHr_Distancia(cursor.getString("hr_distancia"));
	        	pu.setNm_Tramo1(cursor.getString("nm_tramo1"));
	        	pu.setNm_Tramo2(cursor.getString("nm_tramo2"));
	        	pu.setNm_Tramo3(cursor.getString("nm_tramo3"));
	        	pu.setNm_Tramo4(cursor.getString("nm_tramo4"));
	        	pu.setPe_Frete1(cursor.getDouble("pe_frete1"));
	        	pu.setPe_Frete2(cursor.getDouble("pe_frete2"));
	        	pu.setPe_Frete3(cursor.getDouble("pe_frete3"));
	        	pu.setPe_Frete4(cursor.getDouble("pe_frete4"));
	        	pu.setPe_Frete_Externo(cursor.getDouble("pe_frete_externo"));
	        	pu.setOid_Aidof_Mic(cursor.getInt("oid_aidof_Mic"));
	        }
	        cursor.close();
	        stmt.close();
	        conn.close();
	    }
	    catch(SQLException e){
	        throw new Excecoes(e.getMessage(), e, Tipo_FaturamentoBean.class.getName(), "getByOID(int oid)");
	    }
	    return pu;
	}


	public static final Permisso_UnidadeBean getPermissoUnidadeByOidUnidades(
			int oid_Unidade_Origem, int oid_Unidade_Destino, int oid_Unidade_Fronteira, String nr_Permisso) throws Exception{

		Connection conn = null;
	    
		try{
	        conn = OracleConnection2.getWEB();
	        conn.setAutoCommit(false);
	    }
		catch(Exception e){
	        e.printStackTrace();
	        throw e;
	    }
	    
		Permisso_UnidadeBean pu = new Permisso_UnidadeBean();
	    
	    try{
	    	
	        StringBuffer buff = new StringBuffer();
	        buff.append("SELECT * FROM permissos_unidades ");
	        buff.append("WHERE oid_unidade_origem = " + oid_Unidade_Origem + " ");
	        buff.append("AND oid_unidade_destino = " + oid_Unidade_Destino + " ");
	        buff.append("AND oid_unidade_fronteira = " + oid_Unidade_Fronteira);
	        
	        if(JavaUtil.doValida(nr_Permisso)){
	        	buff.append(" AND nr_permisso = '" + nr_Permisso +"'");
	        }
	        
// System.out.println("Permisso_UnidBean.getPermissoUnidadeByOidUnidades() sql = " + buff.toString());
	        
	        Statement stmt = conn.createStatement();
	        
	        ResultSet cursor = stmt.executeQuery(buff.toString());
	        
	        if (cursor.next()){
	        	pu.setOid_Permisso_Unidade(cursor.getInt("oid_permisso_unidade"));
	        	pu.setOid_Unidade_Origem(cursor.getInt("oid_unidade_origem"));
	        	pu.setOid_Unidade_Destino(cursor.getInt("oid_unidade_destino"));
	        	pu.setOid_Unidade_Fronteira(cursor.getInt("oid_unidade_fronteira"));
	        	pu.setNr_Permisso(cursor.getString("nr_permisso"));
	        	pu.setOid_Aidof(cursor.getInt("oid_aidof"));
	        	pu.setKm_Distancia(cursor.getInt("km_distancia"));
	        	pu.setHr_Distancia(cursor.getString("hr_distancia"));
	        	pu.setNm_Tramo1(cursor.getString("nm_tramo1"));
	        	pu.setNm_Tramo2(cursor.getString("nm_tramo2"));
	        	pu.setNm_Tramo3(cursor.getString("nm_tramo3"));
	        	pu.setNm_Tramo4(cursor.getString("nm_tramo4"));
	        	pu.setPe_Frete1(cursor.getDouble("pe_frete1"));
	        	pu.setPe_Frete2(cursor.getDouble("pe_frete2"));
	        	pu.setPe_Frete3(cursor.getDouble("pe_frete3"));
	        	pu.setPe_Frete4(cursor.getDouble("pe_frete4"));
	        	pu.setPe_Frete_Externo(cursor.getDouble("pe_frete_externo"));
	        	pu.setOid_Aidof_Mic(cursor.getInt("oid_aidof_Mic"));
	        }
	        cursor.close();
	        stmt.close();
	        conn.close();
	    }
	    catch(SQLException e){
	        throw new Excecoes(e.getMessage(), e, Tipo_FaturamentoBean.class.getName(), "getByOID(int oid)");
	    }
	    return pu;
	}
	

	public static final boolean validaPermissoUnidadeByOidUnidades(
			int oid_Unidade_Origem, int oid_Unidade_Destino, int oid_Unidade_Fronteira) throws Exception{

		boolean erro = false;
	    
	    try{
	    	int oid_Unid_Orig = PessoaBean.getByEndereco_Completo(UnidadeBean.getByOID_Unidade(oid_Unidade_Origem).getOID_Pessoa()).getOid_Pais();
	    	int oid_Unid_Dest = PessoaBean.getByEndereco_Completo(UnidadeBean.getByOID_Unidade(oid_Unidade_Destino).getOID_Pessoa()).getOid_Pais();
	    	int oid_Unid_Fron = PessoaBean.getByEndereco_Completo(UnidadeBean.getByOID_Unidade(oid_Unidade_Fronteira).getOID_Pessoa()).getOid_Pais();

	    	if(oid_Unid_Orig == oid_Unid_Dest && oid_Unid_Orig == oid_Unid_Fron && oid_Unid_Dest == oid_Unid_Fron){
	    		erro = true;
	    	}
	    	
	    }
	    catch(SQLException e){
	        throw new Excecoes(e.getMessage(), e, Tipo_FaturamentoBean.class.getName(), 
	        		"validaPermissoUnidadeByOidUnidades(int oid_Unidade_Origem, int oid_Unidade_Destino, " +
	        		"int oid_Unidade_Fronteira)");
	    }
	    return erro;
	}
	
	
	public static final List getAll(
			int oid_Unidade_Origem, int oid_Unidade_Destino, int oid_Unidade_Fronteira, String nr_Permisso) throws Exception{
		
		Connection conn = null;
		
		try{
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}

		List Permisso_Unidade_Lista = new ArrayList();
		
		try{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT * FROM permissos_unidades WHERE 1=1 ");

			if(oid_Unidade_Origem > 0){
				buff.append("AND oid_unidade_origem = " + oid_Unidade_Origem + " ");
			}
			
			if(oid_Unidade_Destino > 0){
				buff.append("AND oid_unidade_destino = " + oid_Unidade_Destino + " ");
			}
			
	        if(oid_Unidade_Fronteira > 0){
	        	buff.append("AND oid_unidade_fronteira = " + oid_Unidade_Fronteira);
	        }
			
	        if(JavaUtil.doValida(nr_Permisso)){
	        	buff.append(" AND nr_permisso = '" + nr_Permisso +"'");
	        }
			
//			// System.out.println("Permisso_UnidBean.getAll() sql = "+buff.toString());
	        
			Statement stmt = conn.createStatement();
			ResultSet cursor = stmt.executeQuery(buff.toString());

			while (cursor.next()){
				Permisso_UnidadeBean pu = new Permisso_UnidadeBean();
				pu.setOid_Permisso_Unidade(cursor.getInt("oid_permisso_unidade"));
	        	pu.setOid_Unidade_Origem(cursor.getInt("oid_unidade_origem"));
	        	pu.setOid_Unidade_Destino(cursor.getInt("oid_unidade_destino"));
	        	pu.setOid_Unidade_Fronteira(cursor.getInt("oid_unidade_fronteira"));
	        	pu.setNr_Permisso(cursor.getString("nr_permisso"));
	        	pu.setOid_Aidof(cursor.getInt("oid_aidof"));
	        	pu.setKm_Distancia(cursor.getInt("km_distancia"));
	        	pu.setHr_Distancia(cursor.getString("hr_distancia"));
	        	pu.setNm_Tramo1(cursor.getString("nm_tramo1"));
	        	pu.setNm_Tramo2(cursor.getString("nm_tramo2"));
	        	pu.setNm_Tramo3(cursor.getString("nm_tramo3"));
	        	pu.setNm_Tramo4(cursor.getString("nm_tramo4"));
	        	pu.setPe_Frete1(cursor.getDouble("pe_frete1"));
	        	pu.setPe_Frete2(cursor.getDouble("pe_frete2"));
	        	pu.setPe_Frete3(cursor.getDouble("pe_frete3"));
	        	pu.setPe_Frete4(cursor.getDouble("pe_frete4"));
	        	pu.setPe_Frete_Externo(cursor.getDouble("pe_frete_externo"));
	        	pu.setOid_Aidof_Mic(cursor.getInt("oid_aidof_Mic"));
	        	Permisso_Unidade_Lista.add(pu);
			}
			cursor.close();
			stmt.close();
			conn.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return Permisso_Unidade_Lista;
	}

	public static void main(String args[]) throws Exception{

	}
}