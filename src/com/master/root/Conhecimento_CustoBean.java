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

public class Conhecimento_CustoBean extends BancoUtil {

	private int oid_Conhecimento_Custo;
	private String oid_Conhecimento;
	private int oid_Tipo_Faturamento;
	private String oid_Pessoa;
	private String DT_Emissao;
	private String DM_Situacao;
	private String DM_Gerado_Lancado;
	private double VL_Faturar;
	private double VL_Faturar_Adicional;
	private String TX_Observacao;
	private int oid_Conhecimento_Estadia;
	private String NR_Conhecimento;
	
	private String DT_Inicial;
	private String DT_Final;
	private int NR_Dias;
	private int NR_Caminhoes;
	private double VL_Base;
	private double VL_Desconto;
	
	private int oid_Moeda;
	private String cd_Moeda;
	private String nm_Moeda;
	
	ExecutaSQL executasql;

	
	public Conhecimento_CustoBean() {}
    
    
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
    public String getDT_Emissao() {
        return DT_Emissao;
    }
    public void setDT_Emissao(String emissao) {
        DT_Emissao = emissao;
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
    public ExecutaSQL getExecutasql() {
        return executasql;
    }
    public void setExecutasql(ExecutaSQL executasql) {
        this.executasql = executasql;
    }
    public int getNR_Caminhoes() {
        return NR_Caminhoes;
    }
    public void setNR_Caminhoes(int caminhoes) {
        NR_Caminhoes = caminhoes;
    }
    public String getNR_Conhecimento() {
        return NR_Conhecimento;
    }
    public void setNR_Conhecimento(String conhecimento) {
        NR_Conhecimento = conhecimento;
    }
    public int getNR_Dias() {
        return NR_Dias;
    }
    public void setNR_Dias(int dias) {
        NR_Dias = dias;
    }
    public String getOid_Conhecimento() {
        return oid_Conhecimento;
    }
    public void setOid_Conhecimento(String oid_Conhecimento) {
        this.oid_Conhecimento = oid_Conhecimento;
    }
    public int getOid_Conhecimento_Custo() {
        return oid_Conhecimento_Custo;
    }
    public void setOid_Conhecimento_Custo(int oid_Conhecimento_Custo) {
        this.oid_Conhecimento_Custo = oid_Conhecimento_Custo;
    }
    public int getOid_Conhecimento_Estadia() {
        return oid_Conhecimento_Estadia;
    }
    public void setOid_Conhecimento_Estadia(int oid_Conhecimento_Estadia) {
        this.oid_Conhecimento_Estadia = oid_Conhecimento_Estadia;
    }
    public String getOid_Pessoa() {
        return oid_Pessoa;
    }
    public void setOid_Pessoa(String oid_Pessoa) {
        this.oid_Pessoa = oid_Pessoa;
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
    public String getCd_Moeda() {
        return cd_Moeda;
    }
    public void setCd_Moeda(String cd_Moeda) {
        this.cd_Moeda = cd_Moeda;
    }
    public String getNm_Moeda() {
        return nm_Moeda;
    }
    public void setNm_Moeda(String nm_Moeda) {
        this.nm_Moeda = nm_Moeda;
    }
    public int getOid_Moeda() {
        return oid_Moeda;
    }
    public void setOid_Moeda(int oid_Moeda) {
        this.oid_Moeda = oid_Moeda;
    }
    
    
	public void insert(ExecutaSQL sql) 
	throws Excecoes {
	     
	    this.sql = sql;
	    executasql = sql;
	    int valOid = 1;

	    //int i = getAutoIncremento("oid_conhecimento_custo","Conhecimentos_custos", true);
		//setOid_Conhecimento_Custo(i);
	    try{

	        ResultSet rs = executasql.executarConsulta("select max(oid_conhecimento_custo) as result from Conhecimentos_custos");
		    while (rs.next()){
		       valOid = rs.getInt("result") + 1;
		    }
			setOid_Conhecimento_Custo(valOid);
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
		buff.append("INSERT INTO Conhecimentos_custos " +
				    "(oid_conhecimento_custo, oid_conhecimento, oid_tipo_faturamento, " +
				    "dt_emissao, dm_situacao, dm_gerado_lancado, vl_faturar, tx_observacao, oid_moeda) ");
		buff.append("VALUES (");

		buff.append(getOid_Conhecimento_Custo());
		buff.append(", " + getSQLString(getOid_Conhecimento()));
		buff.append(", " + getOid_Tipo_Faturamento());
		buff.append(", " + getSQLString(getDT_Emissao()));
		buff.append(", " + getSQLString(getDM_Situacao()));
		buff.append(", " + getSQLString(getDM_Gerado_Lancado()));
		buff.append(", " + getVL_Faturar());
		buff.append(", " + getSQLString(getTX_Observacao()));
		buff.append(", " + getOid_Moeda());
		buff.append(")");
		
// System.out.println("Conhec_custo.insert() sql = " + buff.toString());
		
		try {
            executasql.executarUpdate(buff.toString());
        } catch (SQLException e) {
            throw new Excecoes(e.getMessage(), e, getClass().getName(), "insert(ExecutaSQL sql)");
        }

	}
	
	public void insert() throws Exception {
	    inicioTransacao();
	    try {
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
		buff.append("UPDATE Conhecimentos_custos SET " +
				    "oid_conhecimento=?, oid_tipo_faturamento=?, " +
				    "dt_emissao=?, dm_situacao=?, dm_gerado_lancado=?, vl_faturar=?, tx_observacao=?, oid_moeda=? ");
		buff.append("WHERE oid_conhecimento_custo=?");

		try{
			PreparedStatement pstmt = conn.prepareStatement(buff.toString());
			pstmt.setString(1, getOid_Conhecimento());
			pstmt.setInt(2, getOid_Tipo_Faturamento());
			pstmt.setString(3, getDT_Emissao());
			pstmt.setString(4, getDM_Situacao());
			pstmt.setString(5, getDM_Gerado_Lancado());
			pstmt.setDouble(6, getVL_Faturar());
			pstmt.setString(7, getTX_Observacao());
			pstmt.setInt(8, getOid_Moeda());
			pstmt.setInt(9, getOid_Conhecimento_Custo());
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
		buff.append("UPDATE Conhecimentos_custos SET vl_faturar_adicional=? ");
		buff.append("WHERE oid_conhecimento_custo=?");

		try{
			PreparedStatement pstmt = conn.prepareStatement(buff.toString());
			pstmt.setDouble(1, getVL_Faturar_Adicional());
			pstmt.setInt(2, getOid_Conhecimento_Custo());
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
		buff.append("DELETE FROM Conhecimentos_custos WHERE oid_conhecimento_custo=?");
		
		try{
			PreparedStatement pstmt = conn.prepareStatement(buff.toString());
			pstmt.setInt(1, getOid_Conhecimento_Custo());
			pstmt.executeUpdate();

			if(!JavaUtil.doValida(getOid_Conhecimento())){
			    throw new Exception("Oid_Conhecimento é null - Conhecimento_custo.delete()");
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

	public static final Conhecimento_CustoBean getByOID(int oid_conhecimento_custo) throws Exception{

		Connection conn = null;
	    
		try{
	        conn = OracleConnection2.getWEB();
	        conn.setAutoCommit(false);
	    }
		catch(Exception e){
	        e.printStackTrace();
	        throw e;
	    }
	    
		Conhecimento_CustoBean cf = new Conhecimento_CustoBean();
	    
	    try{
	    	
	        StringBuffer buff = new StringBuffer();
	        buff.append("SELECT Conhecimentos_custos.* FROM Conhecimentos_custos ");
	        buff.append("WHERE oid_conhecimento_custo= " + oid_conhecimento_custo);
	        
	        Statement stmt = conn.createStatement();
	        ResultSet cursor = stmt.executeQuery(buff.toString());
	        
	        if (cursor.next()){
	        	cf.setOid_Conhecimento_Custo(cursor.getInt("oid_conhecimento_custo"));
	        	cf.setOid_Conhecimento(cursor.getString("oid_conhecimento"));
	        	cf.setOid_Tipo_Faturamento(cursor.getInt("oid_tipo_faturamento"));
	        	cf.setDT_Emissao(cursor.getString("dt_emissao"));
	        	cf.setDM_Situacao(cursor.getString("dm_situacao"));
	        	cf.setDM_Gerado_Lancado(cursor.getString("dm_gerado_lancado"));
	        	cf.setVL_Faturar(cursor.getDouble("vl_faturar"));
	        	cf.setVL_Faturar_Adicional(cursor.getDouble("vl_faturar_adicional"));
	        	cf.setTX_Observacao(cursor.getString("tx_observacao"));
	        	cf.setOid_Moeda(cursor.getInt("oid_moeda"));
	        	MoedaBean moeda = MoedaBean.getByOID(cf.getOid_Moeda());
	        	cf.setCd_Moeda(moeda.getCD_Moeda());
	        	cf.setNm_Moeda(moeda.getNM_Moeda());
	        }
	        cursor.close();
	        stmt.close();
	        conn.close();
	    }
	    catch(SQLException e){
	        throw new Excecoes(e.getMessage(), e, Conhecimento_CustoBean.class.getName(), "getByOID(int oid)");
	    }
	    return cf;
	}


	/**
	 * Se os parâmetros forem válidos serão incluídos como WHERE no SELECT * FROM Conhecimentos_custos
	 * 
	 * @param int oid_Conhecimento_custo > 0
	 * @param doValida(String oid_Conhecimento)
	 *
	 * @throws Exception
	 */
	public static final List getAll(int oid_Conhecimento_custo, String oid_Conhecimento) throws Exception{
		
		Connection conn = null;
		
		try{
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}

		List Conhecimentos_custos_Lista = new ArrayList();
		
		try{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT * FROM Conhecimentos_custos ");
			
			if(oid_Conhecimento_custo > 0 || JavaUtil.doValida(oid_Conhecimento)){
				buff.append("WHERE ");
				
				if(oid_Conhecimento_custo > 0){
					buff.append("oid_conhecimento_custo = " + oid_Conhecimento_custo);
				}
				
				if(JavaUtil.doValida(oid_Conhecimento)){
					if(oid_Conhecimento_custo > 0) buff.append(" AND ");
					buff.append("oid_conhecimento = '" + oid_Conhecimento + "'");
				}
			}

//			// System.out.println("Conhec_FatBean.getAll() sql = " + buff.toString());
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = stmt.executeQuery(buff.toString());

			while (cursor.next()){
				Conhecimento_CustoBean cf = new Conhecimento_CustoBean();
	        	cf.setOid_Conhecimento_Custo(cursor.getInt("oid_conhecimento_custo"));
	        	cf.setOid_Conhecimento(cursor.getString("oid_conhecimento"));
	        	cf.setOid_Tipo_Faturamento(cursor.getInt("oid_tipo_faturamento"));
	        	cf.setDT_Emissao(cursor.getString("dt_emissao"));
	        	cf.setDM_Situacao(cursor.getString("dm_situacao"));
	        	cf.setDM_Gerado_Lancado(cursor.getString("dm_gerado_lancado"));
	        	cf.setVL_Faturar(cursor.getDouble("vl_faturar"));
	        	cf.setVL_Faturar_Adicional(cursor.getDouble("vl_faturar_adicional"));
	        	cf.setTX_Observacao(cursor.getString("tx_observacao"));
	        	cf.setOid_Moeda(cursor.getInt("oid_moeda"));
	        	MoedaBean moeda = MoedaBean.getByOID(cf.getOid_Moeda());
	        	cf.setCd_Moeda(moeda.getCD_Moeda());
	        	cf.setNm_Moeda(moeda.getNM_Moeda());
	        	Conhecimentos_custos_Lista.add(cf);
			}
			cursor.close();
			stmt.close();
			conn.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return Conhecimentos_custos_Lista;
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
			buff.append("SELECT count(oid_Conhecimento_custo) FROM Conhecimentos_custos where ");
			buff.append("oid_conhecimento = '" + oid_Conhecimento + "'");

// System.out.println("Conhec_FatBean.getAll() sql = " + buff.toString());
			
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
	
	public void Libera_custo() throws Exception{
		
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
		buff.append("UPDATE Conhecimentos_custos SET dm_situacao='L' ");
		buff.append("WHERE oid_conhecimento_custo="+getOid_Conhecimento_Custo());

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
				if (JavaUtil.doValida(cursor.getString(1)) && cursor.getString(1).equals("L")) r = true;
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
	
	public static final boolean getSituacao(int oid_Conhecimento_custo) throws Exception{
		
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
			buff.append("SELECT dm_situacao FROM Conhecimentos_custos where ");
			buff.append("oid_Conhecimento_custo = '" + oid_Conhecimento_custo + "'");

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