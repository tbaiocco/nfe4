package com.master.root;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import auth.OracleConnection2;


public class ContatoBean 
{
	private String NM_Contato;
	private String NM_Email;
	private String NR_Telefone;
	private String NR_Celular;
	private String DT_Nascimento;
	private String NM_Setor;
	private String TX_Observacao;
	private String NR_Telefone_Residencial;
	private String NR_Fax;
	private String NR_Ramal;
	private String NR_CNPJ_CPF;
	private String NM_Razao_Social;
	private String NM_Fantasia;
	private String Usuario_Stamp;
	private String Dt_Stamp;
	private String Dm_Stamp;
	private int oid;
	private String oid_Pessoa;
	
	public ContatoBean() 
	{
		NM_Contato="";
		NM_Email=" ";
		NR_Telefone=" ";
		NR_Telefone_Residencial=" ";
		NR_Ramal=" ";
		NR_Celular=" ";
		NM_Setor="";
		TX_Observacao=" ";
		NR_CNPJ_CPF="";
		NM_Razao_Social="";
		NM_Fantasia="";
		Usuario_Stamp="";
		Dm_Stamp="";
		oid=0;
		oid_Pessoa="";
		NR_Fax = "";
	} 
	
	
	
	public String getNR_Fax() {
		return NR_Fax;
	}
	public void setNR_Fax(String fax) {
		NR_Fax = fax;
	}
	public String getNM_Contato() 
	{
		return NM_Contato;
	}
	public void setNM_Contato(String NM_Contato) 
	{
		this.NM_Contato = NM_Contato;
	}
	public String getNM_Email() 
	{
		return NM_Email;
	}
	public void setNM_Email(String NM_Email) 
	{
		this.NM_Email = NM_Email;
	}
	public String getNR_Telefone() 
	{
		return NR_Telefone;
	}
	public void setNR_Telefone(String NR_Telefone) 
	{
		this.NR_Telefone = NR_Telefone;
	}
	public String getNR_Telefone_Residencial() 
	{
		return NR_Telefone_Residencial;
	}
	public void setNR_Telefone_Residencial(String NR_Telefone_Residencial) 
	{
		this.NR_Telefone_Residencial = NR_Telefone_Residencial;
	}
	public String getNR_Ramal() 
	{
		return NR_Ramal;
	}
	public void setNR_Ramal(String NR_Ramal) 
	{
		this.NR_Ramal = NR_Ramal;
	}
	public String getNR_Celular() 
	{
		return NR_Celular;
	}
	public void setNR_Celular(String NR_Celular) 
	{
		this.NR_Celular = NR_Celular;
	}
	public String getNM_Setor() 
	{
		return NM_Setor;
	}
	public void setNM_Setor(String NM_Setor) 
	{
		this.NM_Setor = NM_Setor;
	}
	
	public String getTX_Observacao() 
	{
		return TX_Observacao;
	}
	public void setTX_Observacao(String TX_Observacao) 
	{
		this.TX_Observacao = TX_Observacao;
	}
	public String getDT_Nascimento() 
	{
		FormataDataBean DataFormatada = new FormataDataBean();
		DataFormatada.setDT_FormataData(DT_Nascimento);
		DT_Nascimento = DataFormatada.getDT_FormataData();
		
		return DT_Nascimento;
	}
	public void setDT_Nascimento(String DT_Nascimento) 
	{
		this.DT_Nascimento = DT_Nascimento;
	}
	public String getNR_CNPJ_CPF() 
	{
		
		return NR_CNPJ_CPF;
	}
	public void setNR_CNPJ_CPF(String NR_CNPJ_CPF) 
	{
		this.NR_CNPJ_CPF = NR_CNPJ_CPF;
	}
	public String getNM_Razao_Social() 
	{
		return NM_Razao_Social;
	}
	public void setNM_Razao_Social(String NM_Razao_Social) 
	{
		this.NM_Razao_Social = NM_Razao_Social;
	}
	public String getNM_Fantasia() 
	{
		return NM_Fantasia;
	}
	public void setNM_Fantasia(String NM_Fantasia) 
	{
		this.NM_Fantasia = NM_Fantasia;
	}
	
	/* 
	 *---------------- Bloco Padrão para Todas Classes ------------------
	 */
	public String getUsuario_Stamp() 
	{
		return Usuario_Stamp;
	}
	public void setUsuario_Stamp(String Usuario_Stamp) 
	{
		this.Usuario_Stamp = Usuario_Stamp;
	}
	public String getDt_Stamp() 
	{
		return Dt_Stamp;
	}
	public void setDt_Stamp(String Dt_Stamp) 
	{
		this.Dt_Stamp = Dt_Stamp;
	}
	public String getDm_Stamp() 
	{
		return Dm_Stamp;
	}
	public void setDm_Stamp(String Dm_Stamp) 
	{
		this.Dm_Stamp = Dm_Stamp;
	}
	public int getOID() 
	{
		return oid;
	}
	public void setOID(int n) 
	{
		this.oid = n;
	}
	public String getOID_Pessoa() 
	{
		return oid_Pessoa;
	}
	public void setOID_Pessoa(String n) 
	{
		this.oid_Pessoa = n;
	}
	
	public void insert() throws Exception 
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try 
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Contato do DSN 
			// o NM_Contato de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}		
		/*
		 * Gera um novo código (OID)
		 */
		try 
		{
			Statement stmt = conn.createStatement();
			ResultSet cursor = stmt.executeQuery(
				"SELECT MAX(OID_Contato) FROM Contatos");
			
			while (cursor.next()) 
			{
				int oid = cursor.getInt(1);
				setOID(oid + 1);
			}
			cursor.close();
			stmt.close();
		} catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
		/*
		 * Define o insert.
		 */
		StringBuffer buff = new StringBuffer();
		buff.append("INSERT INTO Contatos (OID_Contato, OID_Pessoa, NM_Contato, NM_Email, NR_Telefone, " +
				    "NR_Telefone_Residencial, NR_Ramal, NR_Celular, NM_Setor, TX_Observacao, Dt_Stamp, " +
				    "Usuario_Stamp, Dm_Stamp, DT_Nascimento, nr_fax) ");
		buff.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try 
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setInt(1, getOID());
			pstmt.setString(2, getOID_Pessoa());
			pstmt.setString(3, getNM_Contato());
			pstmt.setString(4, getNM_Email());
			pstmt.setString(5, getNR_Telefone());
			pstmt.setString(6, getNR_Telefone_Residencial());
			pstmt.setString(7, getNR_Ramal());
			pstmt.setString(8, getNR_Celular());
			pstmt.setString(9, getNM_Setor());
			pstmt.setString(10, getTX_Observacao());
			pstmt.setString(11, getDt_Stamp());
			pstmt.setString(12, getUsuario_Stamp());
			pstmt.setString(13, getDm_Stamp());
			pstmt.setString(14, this.DT_Nascimento);
			pstmt.setString(15, getNR_Fax());
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
	
	public void update() throws Exception 
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try 
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Contato do DSN 
			// o NM_Contato de usuário e a senha do banco.
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
		buff.append("UPDATE Contatos SET NM_Contato=?, NM_Email=?, NR_Telefone=?, NR_Telefone_Residencial=?, " +
				    "NR_Ramal=?, NR_Celular=?, NM_Setor=?, TX_Observacao=?, Dt_Stamp=?, Usuario_Stamp=?, " +
				    "Dm_Stamp=?, DT_Nascimento=?, nr_fax=? ");
		buff.append("WHERE OID_Contato=?");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try 
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setString(1, getNM_Contato());
			pstmt.setString(2, getNM_Email());
			pstmt.setString(3, getNR_Telefone());
			pstmt.setString(4, getNR_Telefone_Residencial());
			pstmt.setString(5, getNR_Ramal());
			pstmt.setString(6, getNR_Celular());
			pstmt.setString(7, getNM_Setor());
			pstmt.setString(8, getTX_Observacao());
			pstmt.setString(9, getDt_Stamp());
			pstmt.setString(10, getUsuario_Stamp());
			pstmt.setString(11, getDm_Stamp());
			pstmt.setString(12, this.DT_Nascimento);
			pstmt.setString(13, getNR_Fax());
			pstmt.setInt(14, getOID());
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
	
	public void delete() throws Exception 
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try 
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Contato do DSN 
			// o NM_Contato de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
		/*
		 * Define o DELETE.
		 */
		StringBuffer buff = new StringBuffer();
		buff.append("DELETE FROM Contatos ");
		buff.append("WHERE OID_Contato=?");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try 
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setInt(1, getOID());
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
	
	public static final ContatoBean getByOID(int oid) throws Exception{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try 
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Contato do DSN 
			// o NM_Contato de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
		
		ContatoBean p = new ContatoBean();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT Contatos.OID_Contato, ");
			buff.append("	Contatos.OID_Pessoa, ");
			buff.append("	Contatos.NM_Contato, ");
			buff.append("	Contatos.NM_Email, ");
			buff.append("	Contatos.NR_Telefone, ");
			buff.append("	Contatos.NR_Telefone_Residencial, ");
			buff.append("	Contatos.NR_Ramal, ");
			buff.append("	Contatos.NR_Celular, ");
			buff.append("	Contatos.NM_Setor, ");
			buff.append("	Contatos.TX_Observacao, ");
			buff.append("	Pessoas.NR_CNPJ_CPF, ");
			buff.append("	Pessoas.NM_Razao_Social, ");
			buff.append("	Pessoas.NM_Fantasia, ");
			buff.append("	Contatos.DT_Nascimento, ");
			buff.append("	Contatos.nr_fax ");
			buff.append(" FROM Contatos, Pessoas ");
			buff.append(" WHERE Contatos.OID_Pessoa = Pessoas.OID_Pessoa ");
			buff.append(" AND Contatos.OID_Contato= ");
			buff.append(oid);
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = 
				stmt.executeQuery(buff.toString());
			
			while (cursor.next()) 
			{
				p.setOID(cursor.getInt(1));
				p.setOID_Pessoa(cursor.getString(2));
				p.setNM_Contato(cursor.getString(3));
				p.setNM_Email(cursor.getString(4));
				p.setNR_Telefone(cursor.getString(5));
				p.setNR_Telefone_Residencial(cursor.getString(6));
				p.setNR_Ramal(cursor.getString(7));
				p.setNR_Celular(cursor.getString(8));
				p.setNM_Setor(cursor.getString(9));
				p.setTX_Observacao(cursor.getString(10));
				p.setNR_CNPJ_CPF(cursor.getString(11));
				p.setNM_Razao_Social(cursor.getString(12));
				p.setNM_Fantasia(cursor.getString(13));
				p.setDT_Nascimento(cursor.getString(14));
				p.setNR_Fax(cursor.getString(15));
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}		
		return p;
	}
	
	public static final List getByNR_CNPJ_CPF(String NR_CNPJ_CPF) 
		throws Exception 
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try 
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Razao_Social do DSN 
			// o NM_Razao_Social de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
		
		List Contato_Lista = new ArrayList();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT Contatos.OID_Contato, ");
			buff.append("	Contatos.OID_Pessoa, ");
			buff.append("	Contatos.NM_Contato, ");
			buff.append("	Contatos.NM_Email, ");
			buff.append("	Contatos.NR_Telefone, ");
			buff.append("	Contatos.NR_Telefone_Residencial, ");
			buff.append("	Contatos.NR_Ramal, ");
			buff.append("	Contatos.NR_Celular, ");
			buff.append("	Contatos.NM_Setor, ");
			buff.append("	Contatos.TX_Observacao, ");
			buff.append("	Pessoas.NR_CNPJ_CPF, ");
			buff.append("	Pessoas.NM_Razao_Social, ");
			buff.append("	Pessoas.NM_Fantasia, ");
			buff.append("	Contatos.DT_Nascimento, ");
			buff.append("	Contatos.nr_fax ");
			buff.append(" FROM Contatos, Pessoas ");
			buff.append(" WHERE Contatos.OID_Pessoa = Pessoas.OID_Pessoa ");
			buff.append(" AND Pessoas.NR_CNPJ_CPF= '");
			buff.append(NR_CNPJ_CPF);
			buff.append("'");
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = stmt.executeQuery(buff.toString());
			
			while (cursor.next()) 
			{
				ContatoBean p = new ContatoBean();
				p.setOID(cursor.getInt(1));
				p.setOID_Pessoa(cursor.getString(2));
				p.setNM_Contato(cursor.getString(3));
				p.setNM_Email(cursor.getString(4));
				p.setNR_Telefone(cursor.getString(5));
				p.setNR_Telefone_Residencial(cursor.getString(6));
				p.setNR_Ramal(cursor.getString(7));
				p.setNR_Celular(cursor.getString(8));
				p.setNM_Setor(cursor.getString(9));
				p.setTX_Observacao(cursor.getString(10));
				p.setNR_CNPJ_CPF(cursor.getString(11));
				p.setNM_Razao_Social(cursor.getString(12));
				p.setNM_Fantasia(cursor.getString(13));
				p.setDT_Nascimento(cursor.getString(14));
				p.setNR_Fax(cursor.getString(15));
				Contato_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return Contato_Lista;
	}
	
	public static final List getByNM_Contato(String NM_Contato) 
		throws Exception 
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try 
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Razao_Social do DSN 
			// o NM_Razao_Social de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
		
		List Contato_Lista = new ArrayList();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT Contatos.OID_Contato, ");
			buff.append("	Contatos.OID_Pessoa, ");
			buff.append("	Contatos.NM_Contato, ");
			buff.append("	Contatos.NM_Email, ");
			buff.append("	Contatos.NR_Telefone, ");
			buff.append("	Contatos.NR_Telefone_Residencial, ");
			buff.append("	Contatos.NR_Ramal, ");
			buff.append("	Contatos.NR_Celular, ");
			buff.append("	Contatos.NM_Setor, ");
			buff.append("	Contatos.TX_Observacao, ");
			buff.append("	Pessoas.NR_CNPJ_CPF, ");
			buff.append("	Pessoas.NM_Razao_Social, ");
			buff.append("	Pessoas.NM_Fantasia, ");
			buff.append("	Contatos.DT_Nascimento, ");
			buff.append("	Contatos.nr_fax ");
			buff.append(" FROM Contatos, Pessoas ");
			buff.append(" WHERE Contatos.OID_Pessoa = Pessoas.OID_Pessoa ");
			buff.append(" AND Contatos.NM_Razao_Social LIKE'");
			buff.append(NM_Contato);
			buff.append("%'");
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = 
				stmt.executeQuery(buff.toString());
			
			while (cursor.next()) 
			{
				ContatoBean p = new ContatoBean();
				p.setOID(cursor.getInt(1));
				p.setOID_Pessoa(cursor.getString(2));
				p.setNM_Contato(cursor.getString(3));
				p.setNM_Email(cursor.getString(4));
				p.setNR_Telefone(cursor.getString(5));
				p.setNR_Telefone_Residencial(cursor.getString(6));
				p.setNR_Ramal(cursor.getString(7));
				p.setNR_Celular(cursor.getString(8));
				p.setNM_Setor(cursor.getString(9));
				p.setTX_Observacao(cursor.getString(10));
				p.setNR_CNPJ_CPF(cursor.getString(11));
				p.setNM_Razao_Social(cursor.getString(12));
				p.setNM_Fantasia(cursor.getString(13));
				p.setDT_Nascimento(cursor.getString(14));
				p.setNR_Fax(cursor.getString(15));
				Contato_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return Contato_Lista;
	}
	
	public static final List getAll()
		throws Exception 
	{
		Connection conn = null;
		try 
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o nome do DSN 
			// o nome de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
		
		List Contato_Lista = new ArrayList();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT Contatos.OID_Contato, ");
			buff.append("	Contatos.OID_Pessoa, ");
			buff.append("	Contatos.NM_Contato, ");
			buff.append("	Contatos.NM_Email, ");
			buff.append("	Contatos.NR_Telefone, ");
			buff.append("	Contatos.NR_Telefone_Residencial, ");
			buff.append("	Contatos.NR_Ramal, ");
			buff.append("	Contatos.NR_Celular, ");
			buff.append("	Contatos.NM_Setor, ");
			buff.append("	Contatos.TX_Observacao, ");
			buff.append("	Pessoas.NR_CNPJ_CPF, ");
			buff.append("	Pessoas.NM_Razao_Social, ");
			buff.append("	Pessoas.NM_Fantasia, ");
			buff.append("	Contatos.DT_Nascimento, ");
			buff.append("	Contatos.nr_fax ");
			buff.append(" FROM Contatos, Pessoas ");
			buff.append(" WHERE Contatos.OID_Pessoa = Pessoas.OID_Pessoa ");
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = 
				stmt.executeQuery(buff.toString()); 
			
			while (cursor.next()) 
			{
				ContatoBean p = new ContatoBean(); 
				p.setOID(cursor.getInt(1));
				p.setOID_Pessoa(cursor.getString(2));
				p.setNM_Contato(cursor.getString(3));
				p.setNM_Email(cursor.getString(4));
				p.setNR_Telefone(cursor.getString(5));
				p.setNR_Telefone_Residencial(cursor.getString(6));
				p.setNR_Ramal(cursor.getString(7));
				p.setNR_Celular(cursor.getString(8));
				p.setNM_Setor(cursor.getString(9));
				p.setTX_Observacao(cursor.getString(10));
				p.setNR_CNPJ_CPF(cursor.getString(11));
				p.setNM_Razao_Social(cursor.getString(12));
				p.setNM_Fantasia(cursor.getString(13));
				p.setDT_Nascimento(cursor.getString(14));
				p.setNR_Fax(cursor.getString(15));
				Contato_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)  
		{
			e.printStackTrace();
		}
		return Contato_Lista;
	}
	
	public static final ContatoBean getByOid(String oid) throws Exception{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try 
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Contato do DSN 
			// o NM_Contato de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
		
		ContatoBean p = new ContatoBean();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT Contatos.OID_Contato, ");
			buff.append("	Contatos.OID_Pessoa, ");
			buff.append("	Contatos.NM_Contato, ");
			buff.append("	Contatos.NM_Email, ");
			buff.append("	Contatos.NR_Telefone, ");
			buff.append("	Contatos.NR_Telefone_Residencial, ");
			buff.append("	Contatos.NR_Ramal, ");
			buff.append("	Contatos.NR_Celular, ");
			buff.append("	Contatos.NM_Setor, ");
			buff.append("	Contatos.TX_Observacao, ");
			buff.append("	Pessoas.NR_CNPJ_CPF, ");
			buff.append("	Pessoas.NM_Razao_Social, ");
			buff.append("	Pessoas.NM_Fantasia, ");
			buff.append("	Contatos.DT_Nascimento, ");
			buff.append("	Contatos.nr_fax ");
			buff.append(" FROM Contatos, Pessoas ");
			buff.append(" WHERE Contatos.OID_Pessoa = Pessoas.OID_Pessoa ");
			buff.append(" AND Contatos.OID_Contato= ");
			buff.append(oid);
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = stmt.executeQuery(buff.toString());
			
			while (cursor.next()) 
			{
				p.setOID(cursor.getInt(1));
				p.setOID_Pessoa(cursor.getString(2));
				p.setNM_Contato(cursor.getString(3));
				p.setNM_Email(cursor.getString(4));
				p.setNR_Telefone(cursor.getString(5));
				p.setNR_Telefone_Residencial(cursor.getString(6));
				p.setNR_Ramal(cursor.getString(7));
				p.setNR_Celular(cursor.getString(8));
				p.setNM_Setor(cursor.getString(9));
				p.setTX_Observacao(cursor.getString(10));
				p.setNR_CNPJ_CPF(cursor.getString(11));
				p.setNM_Razao_Social(cursor.getString(12));
				p.setNM_Fantasia(cursor.getString(13));
				p.setDT_Nascimento(cursor.getString(14));
				p.setNR_Fax(cursor.getString(15));
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}		
		return p;
	}
	
	public static void main(String args[]) 
		throws Exception 
	{
		ContatoBean pp = new ContatoBean();
		pp.setOID(1);
		pp.setNR_Celular("9");
		pp.update();
	}
}