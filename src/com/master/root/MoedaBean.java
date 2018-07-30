package com.master.root;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import auth.OracleConnection2;

public class MoedaBean 
{
	private String CD_Moeda;
	private String NM_Moeda;
	private String NM_Fracao_Singular;
	private String NM_Fracao_Plural;
	private String NM_Extenso_Singular;
	private String NM_Extenso_Plural;
	
	private String Usuario_Stamp;
	private String Dt_Stamp;
	private String Dm_Stamp;
	private int oid;
	
	public MoedaBean() {} 
	
	public String getCD_Moeda() 
	{
		return CD_Moeda;
	}
	public void setCD_Moeda(String CD_Moeda) 
	{
		this.CD_Moeda = CD_Moeda;
	}
	public String getNM_Moeda() 
	{
		return NM_Moeda;
	}
	public void setNM_Moeda(String NM_Moeda) 
	{
		this.NM_Moeda = NM_Moeda;
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
	
	public void insert() throws Exception 
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try 
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Moeda do DSN 
			// o NM_Moeda de usuário e a senha do banco.
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
				"SELECT MAX(OID_Moeda) FROM Moedas");
			
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
		buff.append("INSERT INTO Moedas (" +
					"OID_Moeda" +
					", CD_Moeda" +
					", NM_Fracao_Singular" +
					", NM_Fracao_Plural" +
					", NM_Moeda" +
					", NM_Extenso_Singular" +
					", NM_Extenso_Plural" +
					", Dt_Stamp, Usuario_Stamp, Dm_Stamp) ");
		buff.append("VALUES (?,?,?,?,?,?,?,?,?,?)");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try 
		{
			PreparedStatement pstmt = conn.prepareStatement(buff.toString());
			pstmt.setInt(1, getOID());
			pstmt.setString(2, getCD_Moeda());
			pstmt.setString(3, getNM_Fracao_Singular());
			pstmt.setString(4, getNM_Fracao_Plural());
			pstmt.setString(5, getNM_Moeda());
			pstmt.setString(6, getNM_Extenso_Singular());
			pstmt.setString(7, getNM_Extenso_Plural());
			pstmt.setString(8, getDt_Stamp());
			pstmt.setString(9, getUsuario_Stamp());
			pstmt.setString(10, getDm_Stamp());
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
			// passando como parâmetro o NM_Moeda do DSN 
			// o NM_Moeda de usuário e a senha do banco.
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
		buff.append(" UPDATE Moedas SET " +
		        	"  	NM_Fracao_Singular=?" +
		        	"	,NM_Fracao_Plural=?" +
		        	"	,NM_Moeda=?" +
		        	"	,NM_Extenso_Singular=?" +
		        	"	,NM_Extenso_Plural=?" +
					"	,Dt_Stamp=?, Usuario_Stamp=?, Dm_Stamp=? ");
		buff.append(" WHERE OID_Moeda=?");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try 
		{
			PreparedStatement pstmt = conn.prepareStatement(buff.toString());
			pstmt.setString(1, getNM_Fracao_Singular());
			pstmt.setString(2, getNM_Fracao_Plural());
			pstmt.setString(3, getNM_Moeda());
			pstmt.setString(4, getNM_Extenso_Singular());
			pstmt.setString(5, getNM_Extenso_Plural());
			pstmt.setString(6, getDt_Stamp());
			pstmt.setString(7, getUsuario_Stamp());
			pstmt.setString(8, getDm_Stamp());
			pstmt.setInt(9, getOID());
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
			// passando como parâmetro o NM_Moeda do DSN 
			// o NM_Moeda de usuário e a senha do banco.
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
		buff.append("DELETE FROM Moedas ");
		buff.append("WHERE OID_Moeda=?");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try 
		{
			PreparedStatement pstmt = conn.prepareStatement(buff.toString());
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
	
	public static final MoedaBean getByOID(int oid) 
		throws Exception 
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try 
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Moeda do DSN 
			// o NM_Moeda de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
		
		MoedaBean p = new MoedaBean();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append(" SELECT oid_Moeda ");
			buff.append("		,CD_Moeda");
			buff.append("		,NM_Fracao_Singular");
			buff.append("		,NM_Fracao_Plural");
			buff.append("		,NM_Moeda");
			buff.append("		,NM_Extenso_Singular");
			buff.append("		,NM_Extenso_Plural");
			buff.append(" FROM Moedas ");
			buff.append(" WHERE OID_Moeda= ");
			buff.append(oid);
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = stmt.executeQuery(buff.toString());
			
			while (cursor.next()) 
			{
				p.setOID(cursor.getInt(1));
				p.setCD_Moeda(cursor.getString(2));
				p.setNM_Fracao_Singular(cursor.getString(3));
				p.setNM_Fracao_Plural(cursor.getString(4));
				p.setNM_Moeda(cursor.getString(5));
				p.setNM_Extenso_Singular(cursor.getString(6));
				p.setNM_Extenso_Plural(cursor.getString(7));
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
	
	public static final MoedaBean getByCD_Moeda(String CD_Moeda) 
		throws Exception 
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try 
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Moeda do DSN 
			// o NM_Moeda de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
		
		MoedaBean p = new MoedaBean();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append(" SELECT oid_Moeda ");
			buff.append("		,CD_Moeda");
			buff.append("		,NM_Fracao_Singular");
			buff.append("		,NM_Fracao_Plural");
			buff.append("		,NM_Moeda");
			buff.append("		,NM_Extenso_Singular");
			buff.append("		,NM_Extenso_Plural");
			buff.append(" FROM Moedas ");
			buff.append(" WHERE CD_Moeda= '");
			buff.append(CD_Moeda);
			buff.append("'");
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = 
				stmt.executeQuery(buff.toString());
			
			while (cursor.next()) 
			{
				p.setOID(cursor.getInt(1));
				p.setCD_Moeda(cursor.getString(2));
				p.setNM_Fracao_Singular(cursor.getString(3));
				p.setNM_Fracao_Plural(cursor.getString(4));
				p.setNM_Moeda(cursor.getString(5));
				p.setNM_Extenso_Singular(cursor.getString(6));
				p.setNM_Extenso_Plural(cursor.getString(7));
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
	
	public static final List getByNM_Moeda(String NM_Moeda) 
		throws Exception 
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try 
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Moeda do DSN 
			// o NM_Moeda de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
		
		List Moedas_Lista = new ArrayList();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append(" SELECT oid_Moeda ");
			buff.append("		,CD_Moeda");
			buff.append("		,NM_Fracao_Singular");
			buff.append("		,NM_Fracao_Plural");
			buff.append("		,NM_Moeda");
			buff.append("		,NM_Extenso_Singular");
			buff.append("		,NM_Extenso_Plural");
			buff.append(" FROM Moedas ");
			buff.append(" WHERE NM_Moeda LIKE'");
			buff.append(NM_Moeda);
			buff.append("%'");
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = stmt.executeQuery(buff.toString());
			
			while (cursor.next()) 
			{
				MoedaBean p = new MoedaBean();
				p.setOID(cursor.getInt(1));
				p.setCD_Moeda(cursor.getString(2));
				p.setNM_Fracao_Singular(cursor.getString(3));
				p.setNM_Fracao_Plural(cursor.getString(4));
				p.setNM_Moeda(cursor.getString(5));
				p.setNM_Extenso_Singular(cursor.getString(6));
				p.setNM_Extenso_Plural(cursor.getString(7));
				Moedas_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return Moedas_Lista;
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
		
		List Moedas_Lista = new ArrayList();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append(" SELECT oid_Moeda ");
			buff.append("		,CD_Moeda");
			buff.append("		,NM_Fracao_Singular");
			buff.append("		,NM_Fracao_Plural");
			buff.append("		,NM_Moeda");
			buff.append("		,NM_Extenso_Singular");
			buff.append("		,NM_Extenso_Plural");
			buff.append(" FROM Moedas");
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = stmt.executeQuery(buff.toString()); 
			
			while (cursor.next()) 
			{
				MoedaBean p = new MoedaBean(); 
				p.setOID(cursor.getInt(1));
				p.setCD_Moeda(cursor.getString(2));
				p.setNM_Fracao_Singular(cursor.getString(3));
				p.setNM_Fracao_Plural(cursor.getString(4));
				p.setNM_Moeda(cursor.getString(5));
				p.setNM_Extenso_Singular(cursor.getString(6));
				p.setNM_Extenso_Plural(cursor.getString(7));
				Moedas_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)  
		{
			e.printStackTrace();
		}
		return Moedas_Lista;
	}	
    public String getNM_Extenso_Plural() {
        return NM_Extenso_Plural;
    }
    public String getNM_Extenso_Singular() {
        return NM_Extenso_Singular;
    }
    public String getNM_Fracao_Plural() {
        return NM_Fracao_Plural;
    }
    public String getNM_Fracao_Singular() {
        return NM_Fracao_Singular;
    }
    public void setNM_Extenso_Plural(String extenso_Plural) {
        NM_Extenso_Plural = extenso_Plural;
    }
    public void setNM_Extenso_Singular(String extenso_Singular) {
        NM_Extenso_Singular = extenso_Singular;
    }
    public void setNM_Fracao_Plural(String fracao_Plural) {
        NM_Fracao_Plural = fracao_Plural;
    }
    public void setNM_Fracao_Singular(String fracao_Singular) {
        NM_Fracao_Singular = fracao_Singular;
    }
}
