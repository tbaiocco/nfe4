package com.master.root;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import auth.OracleConnection2;

import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.bd.ExecutaSQL;

public class Conhecimento_FaturamentoBean extends BancoUtil {

	private int oid_Conhecimento_Faturamento;
	private String oid_Conhecimento;
	private int oid_Tipo_Faturamento;
	private String oid_Pessoa_Pagador;
	private int oid_Duplicata;
	private String DT_Emissao;
	private String DM_Situacao;
	private String DM_Gerado_Lancado;
	private double VL_Faturar;
	private double VL_Faturar_Adicional;
	private String TX_Observacao;
	private String NR_Duplicata;
	private int oid_Conhecimento_Estadia;
	private String NR_Conhecimento;
	
	private String DT_Inicial;
	private String DT_Final;
	private int NR_Dias;
	private int NR_Caminhoes;
	private double VL_Base;
	private double VL_Desconto;
	
	ExecutaSQL executasql;

	
	public Conhecimento_FaturamentoBean() {}

	public String getDM_Gerado_Lancado() {
		return DM_Gerado_Lancado;
	}
	public void setDM_Gerado_Lancado(String gerado_Lancado) {
		DM_Gerado_Lancado = gerado_Lancado;
	}
	public String getDM_Situacao() {
		return DM_Situacao;
	}
	public void setDM_Situacao(String situacao) {
		DM_Situacao = situacao;
	}
	public int getOid_Duplicata() {
		return oid_Duplicata;
	}
	public void setOid_Duplicata(int oid_Duplicata) {
		this.oid_Duplicata = oid_Duplicata;
	}
	public String getDT_Emissao() {
		return DT_Emissao;
	}
	public void setDT_Emissao(String emissao) {
		DT_Emissao = emissao;
	}
	public String getOid_Conhecimento() {
		return oid_Conhecimento;
	}
	public void setOid_Conhecimento(String oid_Conhecimento) {
		this.oid_Conhecimento = oid_Conhecimento;
	}
	public int getOid_Conhecimento_Faturamento() {
		return oid_Conhecimento_Faturamento;
	}
	public void setOid_Conhecimento_Faturamento(int oid_Conhecimento_Faturamento) {
		this.oid_Conhecimento_Faturamento = oid_Conhecimento_Faturamento;
	}
	public String getOid_Pessoa_Pagador() {
		return oid_Pessoa_Pagador;
	}
	public void setOid_Pessoa_Pagador(String oid_Pessoa_Pagador) {
		this.oid_Pessoa_Pagador = oid_Pessoa_Pagador;
	}
	public int getOid_Tipo_Faturamento() {
		return oid_Tipo_Faturamento;
	}
	public void setOid_Tipo_Faturamento(int oid_Tipo_Faturamento) {
		this.oid_Tipo_Faturamento = oid_Tipo_Faturamento;
	}
	public String getTX_Observacao() {
		return TX_Observacao;
	}
	public void setTX_Observacao(String observacao) {
		TX_Observacao = observacao;
	}
	public double getVL_Faturar() {
		return VL_Faturar;
	}
	public void setVL_Faturar(double faturar) {
		VL_Faturar = faturar;
	}
	
	public double getVL_Faturar_Adicional() {
		return VL_Faturar_Adicional;
	}
	public void setVL_Faturar_Adicional(double faturar_Adicional) {
		VL_Faturar_Adicional = faturar_Adicional;
	}
		
    public String getNR_Duplicata() {
        return NR_Duplicata;
    }
    public void setNR_Duplicata(String duplicata) {
        NR_Duplicata = duplicata;
    }
    public int getOid_Conhecimento_Estadia() {
        return oid_Conhecimento_Estadia;
    }
    public void setOid_Conhecimento_Estadia(int oid_Conhecimento_Estadia) {
        this.oid_Conhecimento_Estadia = oid_Conhecimento_Estadia;
    }
    public String getNR_Conhecimento() {
        return NR_Conhecimento;
    }
    public void setNR_Conhecimento(String conhecimento) {
        NR_Conhecimento = conhecimento;
    }
    public String getDT_Final() {
        return DT_Final;
    }
    public void setDT_Final(String final1) {
        DT_Final = final1;
    }
    public String getDT_Inicial() {
        return DT_Inicial;
    }
    public void setDT_Inicial(String inicial) {
        DT_Inicial = inicial;
    }
    public int getNR_Caminhoes() {
        return NR_Caminhoes;
    }
    public void setNR_Caminhoes(int caminhoes) {
        NR_Caminhoes = caminhoes;
    }
    public int getNR_Dias() {
        return NR_Dias;
    }
    public void setNR_Dias(int dias) {
        NR_Dias = dias;
    }
    public double getVL_Base() {
        return VL_Base;
    }
    public void setVL_Base(double base) {
        VL_Base = base;
    }
    public double getVL_Desconto() {
        return VL_Desconto;
    }
    public void setVL_Desconto(double desconto) {
        VL_Desconto = desconto;
    }
    
    
	public void insert(ExecutaSQL sql) 
	throws Excecoes {
	     
	    this.sql = sql;
	    executasql = sql;
	    int valOid = 1;

		//int i = getAutoIncremento("oid_conhecimento_faturamento","Conhecimentos_Faturamentos");
		//setOid_Conhecimento_Faturamento(i);
	    try{

	        ResultSet rs = executasql.executarConsulta("select max(oid_conhecimento_faturamento) as result from Conhecimentos_Faturamentos");
		    while (rs.next()){
		       valOid = rs.getInt("result") + 1;
		    }
			setOid_Conhecimento_Faturamento(valOid);
		    }

	    catch(Exception exc){
	      Excecoes excecoes = new Excecoes();
	      excecoes.setClasse(this.getClass().getName());
	      excecoes.setMensagem("Erro ao incluir");
	      excecoes.setMetodo("insert(ExecutaSQL sql)");
	      excecoes.setExc(exc);
	      throw excecoes;
	    }
	    
		StringBuffer buff = new StringBuffer();
		buff.append("INSERT INTO Conhecimentos_Faturamentos " +
				    "(oid_conhecimento_faturamento, oid_conhecimento, oid_tipo_faturamento, oid_pessoa_pagador, " +
				    "oid_duplicata, dt_emissao, dm_situacao, dm_gerado_lancado, vl_faturar, tx_observacao) ");
		buff.append("VALUES (");

		buff.append(getOid_Conhecimento_Faturamento());
		buff.append(", " + getSQLString(getOid_Conhecimento()));
		buff.append(", " + getOid_Tipo_Faturamento());
		buff.append(", " + getSQLString(getOid_Pessoa_Pagador()));
		buff.append(", " + 0);
		buff.append(", " + getSQLString(getDT_Emissao()));
		buff.append(", " + getSQLString(getDM_Situacao()));
		buff.append(", " + getSQLString(getDM_Gerado_Lancado()));
		buff.append(", " + getVL_Faturar());
		buff.append(", " + getSQLString(getTX_Observacao()));
		buff.append(")");
		
// System.out.println("Conhec_Faturamento.insert() sql = " + buff.toString());
		
		try {
            executasql.executarUpdate(buff.toString());
        } catch (SQLException e) {
            throw new Excecoes(e.getMessage(), e, getClass().getName(), "insert(ExecutaSQL sql)");
        }
		
		StringBuffer buffCRT = new StringBuffer();
		buffCRT.append("UPDATE Conhecimentos_Internacionais SET dm_situacao='L' ");
		buffCRT.append("WHERE oid_conhecimento='" + getOid_Conhecimento() + "'");
		
// System.out.println("Conhec_Faturamento.insert() sql2 = " + buffCRT.toString());

		try {
            executasql.executarUpdate(buffCRT.toString());
        } catch (SQLException e1) {
            throw new Excecoes(e1.getMessage(), e1, getClass().getName(), "insert(ExecutaSQL sql)");
        }

	}
	
	public void insert() throws Exception {
	    
	    try {
	        //int i = getAutoIncremento("oid_conhecimento_faturamento","Conhecimentos_Faturamentos", true);
			//setOid_Conhecimento_Faturamento(i);
			
			inicioTransacao();
	        executasql = sql;
	        insert(executasql);
	        fimTransacao(true);
	    } 
	    catch(Excecoes e){
	        abortaTransacao();
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
		buff.append("UPDATE Conhecimentos_Faturamentos SET " +
				    "oid_conhecimento=?, oid_tipo_faturamento=?, oid_pessoa_pagador=?, oid_duplicata=?, " +
				    "dt_emissao=?, dm_situacao=?, dm_gerado_lancado=?, vl_faturar=?, tx_observacao=? ");
		buff.append("WHERE oid_conhecimento_faturamento=?");

		try{
			PreparedStatement pstmt = conn.prepareStatement(buff.toString());
			pstmt.setString(1, getOid_Conhecimento());
			pstmt.setInt(2, getOid_Tipo_Faturamento());
			pstmt.setString(3, getOid_Pessoa_Pagador());
			pstmt.setInt(4, getOid_Duplicata());
			pstmt.setString(5, getDT_Emissao());
			pstmt.setString(6, getDM_Situacao());
			pstmt.setString(7, getDM_Gerado_Lancado());
			pstmt.setDouble(8, getVL_Faturar());
			pstmt.setString(9, getTX_Observacao());
			pstmt.setInt(10, getOid_Conhecimento_Faturamento());
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

	public void update_Adicional() throws Exception{
		
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
		buff.append("UPDATE Conhecimentos_Faturamentos SET vl_faturar_adicional=? ");
		buff.append("WHERE oid_conhecimento_faturamento=?");

		try{
			PreparedStatement pstmt = conn.prepareStatement(buff.toString());
			pstmt.setDouble(1, getVL_Faturar_Adicional());
			pstmt.setInt(2, getOid_Conhecimento_Faturamento());
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
		buff.append("DELETE FROM Conhecimentos_Faturamentos WHERE oid_conhecimento_faturamento=?");
		
		StringBuffer buff_Fat_Consol = new StringBuffer();
		buff_Fat_Consol.append("DELETE FROM Faturamentos_Consolidados WHERE oid_conhecimento=?");

		try{
			PreparedStatement pstmt = conn.prepareStatement(buff.toString());
			pstmt.setInt(1, getOid_Conhecimento_Faturamento());
			pstmt.executeUpdate();

			if(!JavaUtil.doValida(getOid_Conhecimento())){
			    throw new Exception("Oid_Conhecimento é null - Conhecimento_Faturamento.delete()");
			}
			
			PreparedStatement pstmt_Fat_Consol = conn.prepareStatement(buff_Fat_Consol.toString());
			pstmt_Fat_Consol.setString(1, getOid_Conhecimento());
			pstmt_Fat_Consol.executeUpdate();
		}
		catch(Exception e){
			conn.rollback();
			conn.close();
			e.printStackTrace();
			throw e;
		}
		

		try{
			conn.commit();
			conn.close();
			
			if (getOid_Conhecimento() != null){
			    if (getCont_Movimentos(getOid_Conhecimento())){
			        update_CRT_Liberado(getOid_Conhecimento());
			    }
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}

	public static final Conhecimento_FaturamentoBean getByOID(int oid_conhecimento_faturamento) throws Exception{

		Connection conn = null;
	    
		try{
	        conn = OracleConnection2.getWEB();
	        conn.setAutoCommit(false);
	    }
		catch(Exception e){
	        e.printStackTrace();
	        throw e;
	    }
	    
		Conhecimento_FaturamentoBean cf = new Conhecimento_FaturamentoBean();
	    
	    try{
	    	
	        StringBuffer buff = new StringBuffer();
	        buff.append("SELECT Conhecimentos_Faturamentos.* FROM Conhecimentos_Faturamentos ");
	        buff.append("WHERE oid_conhecimento_faturamento= " + oid_conhecimento_faturamento);
	        
	        Statement stmt = conn.createStatement();
	        ResultSet cursor = stmt.executeQuery(buff.toString());
	        
	        if (cursor.next()){
	        	cf.setOid_Conhecimento_Faturamento(cursor.getInt("oid_conhecimento_faturamento"));
	        	cf.setOid_Conhecimento(cursor.getString("oid_conhecimento"));
	        	cf.setOid_Tipo_Faturamento(cursor.getInt("oid_tipo_faturamento"));
	        	cf.setOid_Pessoa_Pagador(cursor.getString("oid_pessoa_pagador"));
	        	cf.setOid_Duplicata(cursor.getInt("oid_duplicata"));
	        	cf.setNR_Duplicata(getNR_Duplicata(cf.getOid_Duplicata()));
	        	cf.setDT_Emissao(cursor.getString("dt_emissao"));
	        	cf.setDM_Situacao(cursor.getString("dm_situacao"));
	        	cf.setDM_Gerado_Lancado(cursor.getString("dm_gerado_lancado"));
	        	cf.setVL_Faturar(cursor.getDouble("vl_faturar"));
	        	cf.setVL_Faturar_Adicional(cursor.getDouble("vl_faturar_adicional"));
	        	cf.setTX_Observacao(cursor.getString("tx_observacao"));
	        }
	        cursor.close();
	        stmt.close();
	        conn.close();
	    }
	    catch(SQLException e){
	        throw new Excecoes(e.getMessage(), e, Conhecimento_FaturamentoBean.class.getName(), "getByOID(int oid)");
	    }
	    return cf;
	}


	/**
	 * Se os parâmetros forem válidos serão incluídos como WHERE no SELECT * FROM Conhecimentos_Faturamentos
	 * 
	 * @param int oid_Conhecimento_Faturamento > 0
	 * @param doValida(String oid_Conhecimento)
	 *
	 * @throws Exception
	 */
	public static final List getAll(int oid_Conhecimento_Faturamento, String oid_Conhecimento) throws Exception{
		
		Connection conn = null;
		
		try{
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}

		List Conhecimentos_Faturamentos_Lista = new ArrayList();
		
		try{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT * FROM Conhecimentos_Faturamentos ");
			
			if(oid_Conhecimento_Faturamento > 0 || JavaUtil.doValida(oid_Conhecimento)){
				buff.append("WHERE ");
				
				if(oid_Conhecimento_Faturamento > 0){
					buff.append("oid_conhecimento_faturamento = " + oid_Conhecimento_Faturamento);
				}
				
				if(JavaUtil.doValida(oid_Conhecimento)){
					if(oid_Conhecimento_Faturamento > 0) buff.append(" AND ");
					buff.append("oid_conhecimento = '" + oid_Conhecimento + "'");
				}
			}

//			// System.out.println("Conhec_FatBean.getAll() sql = " + buff.toString());
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = stmt.executeQuery(buff.toString());

			while (cursor.next()){
				Conhecimento_FaturamentoBean cf = new Conhecimento_FaturamentoBean();
	        	cf.setOid_Conhecimento_Faturamento(cursor.getInt("oid_conhecimento_faturamento"));
	        	cf.setOid_Conhecimento(cursor.getString("oid_conhecimento"));
	        	cf.setOid_Tipo_Faturamento(cursor.getInt("oid_tipo_faturamento"));
	        	cf.setOid_Pessoa_Pagador(cursor.getString("oid_pessoa_pagador"));
	        	cf.setOid_Duplicata(cursor.getInt("oid_duplicata"));
	        	cf.setNR_Duplicata(getNR_Duplicata(cf.getOid_Duplicata()));
	        	cf.setDT_Emissao(cursor.getString("dt_emissao"));
	        	cf.setDM_Situacao(cursor.getString("dm_situacao"));
	        	cf.setDM_Gerado_Lancado(cursor.getString("dm_gerado_lancado"));
	        	cf.setVL_Faturar(cursor.getDouble("vl_faturar"));
	        	cf.setVL_Faturar_Adicional(cursor.getDouble("vl_faturar_adicional"));
	        	cf.setTX_Observacao(cursor.getString("tx_observacao"));
	        	Conhecimentos_Faturamentos_Lista.add(cf);
			}
			cursor.close();
			stmt.close();
			conn.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return Conhecimentos_Faturamentos_Lista;
	}
	
	public void update_CRT_PreFaturado(String oid) throws Exception{
		
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
		buff.append("UPDATE Conhecimentos_Internacionais SET " +
				    "dm_situacao='L' ");
		buff.append("WHERE oid_conhecimento='" + oid + "'");

		try{
			PreparedStatement pstmt = conn.prepareStatement(buff.toString());
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

	
	public static final String getNomeSituacaoFatura(String situacao){

		if(JavaUtil.doValida(situacao)){
			
			if(situacao.equals("L")){
				situacao = "Liberado";
			}
			else if(situacao.equals("B")){
				situacao = "Bloqueado";
			}
			else if(situacao.equals("F")){
				situacao = "Faturado";
			}
		}
		
		return situacao;
		
	}
	
	public static final boolean getCont_Movimentos(String oid_Conhecimento) throws Exception{
		
		Connection conn = null;
		boolean r = false;
		
		try{
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}

		try{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT count(oid_Conhecimento_Faturamento) FROM Conhecimentos_Faturamentos where ");
			buff.append("oid_conhecimento = '" + oid_Conhecimento + "'");

//			// System.out.println("Conhec_FatBean.getAll() sql = " + buff.toString());
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = stmt.executeQuery(buff.toString());

			while (cursor.next()){
				if (cursor.getInt(1) == 0) r = true;
				else r = false;
			}
			cursor.close();
			stmt.close();
			conn.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return r;
	}
	
	public void update_CRT_Liberado(String oid) throws Exception{
		
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
		buff.append("UPDATE Conhecimentos_Internacionais SET " +
				    "dm_situacao='I' ");
		buff.append("WHERE oid_conhecimento='" + oid + "'");

		try{
			PreparedStatement pstmt = conn.prepareStatement(buff.toString());
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
	
	public void Libera_Faturamento() throws Exception{
		
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
		buff.append("UPDATE Conhecimentos_Faturamentos SET dm_situacao='L' ");
		buff.append("WHERE oid_conhecimento_faturamento="+getOid_Conhecimento_Faturamento());

		try{
			PreparedStatement pstmt = conn.prepareStatement(buff.toString());
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
	
	public static final String getNR_Duplicata(int oid_Duplicata) throws Exception{
		
		Connection conn = null;
		String nr = "0";
		
		try{
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}

		try{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT nr_duplicata FROM duplicatas where ");
			buff.append("oid_duplicata = '" + oid_Duplicata + "'");

//			// System.out.println("Conhec_FatBean.getAll() sql = " + buff.toString());
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = stmt.executeQuery(buff.toString());

			while (cursor.next()){
				nr = cursor.getString("nr_duplicata");
			}
			cursor.close();
			stmt.close();
			conn.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return nr;
	}
	
	public static final String getNR_Conhecimento(String oid_CRT) throws Exception{
		
		Connection conn = null;
		String nr = "0";
		
		try{
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}

		try{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT nr_conhecimento FROM conhecimentos_Internacionais where ");
			buff.append("oid_conhecimento = '" + oid_CRT + "'");

//			// System.out.println("Conhec_FatBean.getAll() sql = " + buff.toString());
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = stmt.executeQuery(buff.toString());

			while (cursor.next()){
				nr = cursor.getString("nr_conhecimento");
			}
			cursor.close();
			stmt.close();
			conn.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return nr;
	}
	
	
	public void insert_Estadia(ExecutaSQL sql) 
	throws Excecoes {
	     
	    this.sql = sql;
	    executasql = sql;
	    int valOid = 1;

	    //int i = getAutoIncremento("oid_conhecimento_estadia","Conhecimentos_Estadias", false);
		//setOid_Conhecimento_Estadia(i);
	    
	    try{

	        ResultSet rs = executasql.executarConsulta("select max(oid_conhecimento_estadia) as result from Conhecimentos_Estadias");
		    while (rs.next()){
		       valOid = rs.getInt("result") + 1;
		    }
			setOid_Conhecimento_Estadia(valOid);
		    }

	    catch(Exception exc){
	      Excecoes excecoes = new Excecoes();
	      excecoes.setClasse(this.getClass().getName());
	      excecoes.setMensagem("Erro ao incluir Estadia");
	      excecoes.setMetodo("insert(ExecutaSQL sql)");
	      excecoes.setExc(exc);
	      throw excecoes;
	    }

		StringBuffer buff = new StringBuffer();
		buff.append("INSERT INTO Conhecimentos_Estadias " +
				    "(oid_conhecimento_estadia, oid_conhecimento, oid_tipo_faturamento, oid_pessoa_pagador, " +
				    "oid_duplicata, dt_emissao, dm_situacao, dm_gerado_lancado, vl_faturar, tx_observacao, " +
				    "dt_inicial, dt_final, nr_dias, nr_caminhoes, vl_desconto, vl_base) ");
		buff.append("VALUES (");

		buff.append(getOid_Conhecimento_Estadia());
		buff.append(", " + getSQLString(getOid_Conhecimento()));
		buff.append(", " + getOid_Tipo_Faturamento());
		buff.append(", " + getSQLString(getOid_Pessoa_Pagador()));
		buff.append(", " + 0);
		buff.append(", " + getSQLString(getDT_Emissao()));
		buff.append(", " + getSQLString(getDM_Situacao()));
		buff.append(", " + getSQLString(getDM_Gerado_Lancado()));
		buff.append(", " + getVL_Faturar());
		buff.append(", " + getSQLString(getTX_Observacao()));
		buff.append(", " + getSQLString(getDT_Inicial()));
		buff.append(", " + getSQLString(getDT_Final()));
		buff.append(", " + getNR_Dias());
		buff.append(", " + getNR_Caminhoes());
		buff.append(", " + getVL_Desconto());
		buff.append(", " + getVL_Base());
		buff.append(")");
		
// System.out.println("Conhec_Estadia.insert() sql = " + buff.toString());
		
		try {
            executasql.executarUpdate(buff.toString());
        } catch (SQLException e) {
            throw new Excecoes(e.getMessage(), e, getClass().getName(), "insert(ExecutaSQL sql)");
        }
		
//		StringBuffer buffCRT = new StringBuffer();
//		buffCRT.append("UPDATE Conhecimentos_Internacionais SET dm_situacao='L' ");
//		buffCRT.append("WHERE oid_conhecimento='" + getOid_Conhecimento() + "'");
//		
////		// System.out.println("Conhec_Faturamento.insert() sql2 = " + buffCRT.toString());
//
//		try {
//            executasql.executarUpdate(buffCRT.toString());
//        } catch (SQLException e1) {
//            throw new Excecoes(e1.getMessage(), e1, getClass().getName(), "insert(ExecutaSQL sql)");
//        }

	}
	
	public void insert_Estadia() throws Exception {
	    inicioTransacao();
	    try {
	        executasql = sql;
	        insert_Estadia(executasql);
	        fimTransacao(true);
	    } 
	    catch(Excecoes e){
	        abortaTransacao();
	        throw e;
	    }
	}
	
	public void update_Estadia() throws Exception{
		
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
		buff.append("UPDATE Conhecimentos_Estadias SET " +
				    "oid_conhecimento=?, oid_tipo_faturamento=?, oid_pessoa_pagador=?, oid_duplicata=?, " +
				    "dt_emissao=?, dm_gerado_lancado=?, vl_faturar=?, tx_observacao=?, " +
				    "dt_inicial=?, dt_final=?, nr_dias=?, nr_caminhoes=?, vl_desconto=?, vl_base=? ");
		buff.append("WHERE oid_conhecimento_estadia=?");

		try{
			PreparedStatement pstmt = conn.prepareStatement(buff.toString());
			pstmt.setString(1, getOid_Conhecimento());
			pstmt.setInt(2, getOid_Tipo_Faturamento());
			pstmt.setString(3, getOid_Pessoa_Pagador());
			pstmt.setInt(4, getOid_Duplicata());
			pstmt.setString(5, getDT_Emissao());
			pstmt.setString(6, getDM_Gerado_Lancado());
			pstmt.setDouble(7, getVL_Faturar());
			pstmt.setString(8, getTX_Observacao());
			pstmt.setString(9, getDT_Inicial());
			pstmt.setString(10, getDT_Final());
			pstmt.setInt(11, getNR_Dias());
			pstmt.setInt(12, getNR_Caminhoes());
			pstmt.setDouble(13, getVL_Desconto());
			pstmt.setDouble(14, getVL_Base());
			pstmt.setInt(15, getOid_Conhecimento_Estadia());
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
	
	public static final Conhecimento_FaturamentoBean getByOID_Estadia(int oid_conhecimento_estadia) throws Exception{

		Connection conn = null;
	    
		try{
	        conn = OracleConnection2.getWEB();
	        conn.setAutoCommit(false);
	    }
		catch(Exception e){
	        e.printStackTrace();
	        throw e;
	    }
	    
		Conhecimento_FaturamentoBean cf = new Conhecimento_FaturamentoBean();
	    
	    try{
	    	
	        StringBuffer buff = new StringBuffer();
	        buff.append("SELECT Conhecimentos_Estadias.* FROM Conhecimentos_Estadias ");
	        buff.append("WHERE oid_conhecimento_estadia= " + oid_conhecimento_estadia);
	        
	        Statement stmt = conn.createStatement();
	        ResultSet cursor = stmt.executeQuery(buff.toString());
	        
	        if (cursor.next()){
	        	cf.setOid_Conhecimento_Estadia(cursor.getInt("oid_conhecimento_estadia"));
	        	cf.setOid_Conhecimento_Faturamento(cursor.getInt("oid_conhecimento_faturamento"));
	        	cf.setOid_Conhecimento(cursor.getString("oid_conhecimento"));
	        	cf.setOid_Tipo_Faturamento(cursor.getInt("oid_tipo_faturamento"));
	        	cf.setOid_Pessoa_Pagador(cursor.getString("oid_pessoa_pagador"));
	        	cf.setNR_Conhecimento(getNR_Conhecimento(cf.getOid_Conhecimento()));
	        	cf.setDT_Emissao(cursor.getString("dt_emissao"));
	        	cf.setDM_Situacao(cursor.getString("dm_situacao"));
	        	cf.setDM_Gerado_Lancado(cursor.getString("dm_gerado_lancado"));
	        	cf.setVL_Faturar(cursor.getDouble("vl_faturar"));
	        	cf.setTX_Observacao(cursor.getString("tx_observacao"));
	        	
	        	cf.setDT_Inicial(cursor.getString("DT_Inicial"));
	        	cf.setDT_Final(cursor.getString("DT_Final"));
	        	cf.setNR_Dias(cursor.getInt("NR_Dias"));
	        	cf.setNR_Caminhoes(cursor.getInt("NR_Caminhoes"));
	        	cf.setVL_Base(cursor.getDouble("VL_Base"));
	        	cf.setVL_Desconto(cursor.getDouble("VL_Desconto"));
	        }
	        cursor.close();
	        stmt.close();
	        conn.close();
	    }
	    catch(SQLException e){
	        throw new Excecoes(e.getMessage(), e, Conhecimento_FaturamentoBean.class.getName(), "getByOID(int oid)");
	    }
	    return cf;
	}
	
	public static final List getEstadia(int oid_Conhecimento_Estadia, String oid_Conhecimento, String oid_pagador) throws Exception{
		
		Connection conn = null;
		
		try{
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}

		List Conhecimentos_Faturamentos_Lista = new ArrayList();
		
		try{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT * FROM Conhecimentos_Estadias ");
			
			if(oid_Conhecimento_Estadia > 0 || JavaUtil.doValida(oid_Conhecimento) || JavaUtil.doValida(oid_pagador)){
				buff.append("WHERE ");
				
				if(oid_Conhecimento_Estadia > 0){
					buff.append("oid_conhecimento_estadia = " + oid_Conhecimento_Estadia);
				}
				
				if(JavaUtil.doValida(oid_Conhecimento)){
					if(oid_Conhecimento_Estadia > 0) buff.append(" AND ");
					buff.append("oid_conhecimento = '" + oid_Conhecimento + "'");
				}
				
				if(JavaUtil.doValida(oid_pagador)){
					if(oid_Conhecimento_Estadia > 0 || JavaUtil.doValida(oid_Conhecimento)) buff.append(" AND ");
					buff.append("oid_pessoa_pagador = '" + oid_pagador + "'");
				}
			}

// System.out.println("Conhec_EstBean.getEst() sql = " + buff.toString());
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = stmt.executeQuery(buff.toString());

			while (cursor.next()){
				Conhecimento_FaturamentoBean cf = new Conhecimento_FaturamentoBean();
	        	cf.setOid_Conhecimento_Estadia(cursor.getInt("oid_conhecimento_estadia"));
	        	cf.setOid_Conhecimento(cursor.getString("oid_conhecimento"));
	        	cf.setOid_Tipo_Faturamento(cursor.getInt("oid_tipo_faturamento"));
	        	cf.setOid_Pessoa_Pagador(cursor.getString("oid_pessoa_pagador"));
	        	cf.setNR_Conhecimento(getNR_Conhecimento(cf.getOid_Conhecimento()));
	        	cf.setDT_Emissao(cursor.getString("dt_emissao"));
	        	cf.setDM_Situacao(cursor.getString("dm_situacao"));
	        	cf.setDM_Gerado_Lancado(cursor.getString("dm_gerado_lancado"));
	        	cf.setVL_Faturar(cursor.getDouble("vl_faturar"));
	        	cf.setTX_Observacao(cursor.getString("tx_observacao"));
	        	
	        	cf.setDT_Inicial(cursor.getString("DT_Inicial"));
	        	cf.setDT_Final(cursor.getString("DT_Final"));
	        	cf.setNR_Dias(cursor.getInt("NR_Dias"));
	        	cf.setNR_Caminhoes(cursor.getInt("NR_Caminhoes"));
	        	cf.setVL_Base(cursor.getDouble("VL_Base"));
	        	cf.setVL_Desconto(cursor.getDouble("VL_Desconto"));
	        	
	        	Conhecimentos_Faturamentos_Lista.add(cf);
			}
			cursor.close();
			stmt.close();
			conn.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return Conhecimentos_Faturamentos_Lista;
	}
	
	public void delete_Estadia() throws Exception{

		Connection conn = null;
		
		try{
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		
		Conhecimento_FaturamentoBean cf = new Conhecimento_FaturamentoBean();
		cf = getByOID_Estadia(getOid_Conhecimento_Estadia());
		if(cf.getOid_Conhecimento_Faturamento()>0){
		    if(getSituacao(cf.getOid_Conhecimento_Faturamento())){
		        Conhecimento_FaturamentoBean cf2 = new Conhecimento_FaturamentoBean();
		        cf2 = getByOID(cf.getOid_Conhecimento_Faturamento());
		        throw new Exception("Já está faturado na duplicata nr. "+cf2.getNR_Duplicata());
		    }
		}
		
		StringBuffer buff = new StringBuffer();
		buff.append("DELETE FROM Conhecimentos_Estadias WHERE oid_conhecimento_estadia=?");

		try{
		    if(!getSituacao(cf.getOid_Conhecimento_Faturamento())){
		        PreparedStatement pstmt2 = conn.prepareStatement("DELETE FROM Conhecimentos_Faturamentos WHERE oid_conhecimento_faturamento=?");
				pstmt2.setInt(1, cf.getOid_Conhecimento_Faturamento());
				pstmt2.executeUpdate();
		    }
		    
			PreparedStatement pstmt = conn.prepareStatement(buff.toString());
			pstmt.setInt(1, getOid_Conhecimento_Estadia());
			pstmt.executeUpdate();

			if(!JavaUtil.doValida(getOid_Conhecimento())){
			    throw new Exception("Oid_Conhecimento é null - Conhecimento_Faturamento.delete()");
			}
			
		}
		catch(Exception e){
			conn.rollback();
			conn.close();
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
	
	public void Libera_Estadia() throws Exception{
		
		Connection conn = null;
		
		try{
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		StringBuffer max = new StringBuffer();
		max.append("select max(oid_conhecimento_faturamento) as count from Conhecimentos_Faturamentos");
		PreparedStatement prepst = conn.prepareStatement(max.toString());
		ResultSet res = prepst.executeQuery();
		int i = 0;
		while(res.next()) {
		    i = res.getInt(1);
		}
		
		setOid_Conhecimento_Faturamento(++i);

		StringBuffer buff = new StringBuffer();
		buff.append("INSERT INTO Conhecimentos_Faturamentos " +
				    "(oid_conhecimento_faturamento, oid_conhecimento, oid_tipo_faturamento, oid_pessoa_pagador, " +
				    "oid_duplicata, dt_emissao, dm_situacao, dm_gerado_lancado, vl_faturar, tx_observacao) ");
		buff.append("VALUES (");

		buff.append(getOid_Conhecimento_Faturamento());
		buff.append(", " + getSQLString(getOid_Conhecimento()));
		buff.append(", " + getOid_Tipo_Faturamento());
		buff.append(", " + getSQLString(getOid_Pessoa_Pagador()));
		buff.append(", " + 0);
		buff.append(", " + getSQLString(getDT_Emissao()));
		buff.append(", " + getSQLString(getDM_Situacao()));
		buff.append(", " + getSQLString(getDM_Gerado_Lancado()));
		buff.append(", " + getVL_Faturar());
		buff.append(", " + getSQLString(getTX_Observacao()));
		buff.append(")");
		
// System.out.println("Conhec_Faturamento.insert() sql = " + buff.toString());

		StringBuffer buff2 = new StringBuffer();
		buff2.append("UPDATE Conhecimentos_Estadias SET dm_situacao='L', ");
		buff2.append("oid_conhecimento_faturamento='" + getOid_Conhecimento_Faturamento() + "' ");
		buff2.append("WHERE oid_conhecimento_estadia="+getOid_Conhecimento_Estadia());
		
// System.out.println("Conhec_Faturamento.insert() sql = " + buff2.toString());

		try {
		    PreparedStatement pstmt = conn.prepareStatement(buff.toString());
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement(buff2.toString());
			pstmt.executeUpdate();
        } catch (SQLException e) {
            conn.rollback();
            e.printStackTrace();
            throw new Excecoes(e.getMessage(), e, getClass().getName(), "insert(ExecutaSQL sql)");
        } catch(Exception e){
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
	
	public static final boolean getSituacaoCRT(String oid_Conhecimento) throws Exception{
		
		Connection conn = null;
		boolean r = false;
		
		try{
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}

		try{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT dm_situacao FROM Conhecimentos_Internacionais where ");
			buff.append("oid_conhecimento = '" + oid_Conhecimento + "'");

//			// System.out.println("Conhec_FatBean.getAll() sql = " + buff.toString());
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = stmt.executeQuery(buff.toString());

			while (cursor.next()){
				if (JavaUtil.doValida(cursor.getString(1)) && (cursor.getString(1).equals("L") || cursor.getString(1).equals("F"))) r = true;
				else r = false;
			}
			cursor.close();
			stmt.close();
			conn.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return r;
	}
	
	public static final boolean getSituacao(int oid_Conhecimento_Faturamento) throws Exception{
		
		Connection conn = null;
		boolean r = false;
		
		try{
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}

		try{
			
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT dm_situacao FROM Conhecimentos_Faturamentos where ");
			buff.append("oid_Conhecimento_Faturamento = '" + oid_Conhecimento_Faturamento + "'");

// System.out.println("Conhec_FatBean.getSituacao() sql = " + buff.toString());
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = stmt.executeQuery(buff.toString());

			while (cursor.next()){
				if (JavaUtil.doValida(cursor.getString(1)) && cursor.getString(1).equals("F")) r = true;
				else r = false;
			}
			cursor.close();
			stmt.close();
			conn.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return r;
	}
	
	
	public static void main(String args[]) throws Exception{

	}
	
}