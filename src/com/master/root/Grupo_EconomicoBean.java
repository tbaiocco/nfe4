package com.master.root;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import auth.OracleConnection2;

public class Grupo_EconomicoBean 
{
	private String CD_Grupo_Economico;
	private String NM_Grupo_Economico;
	private String Usuario_Stamp;
	private String Dt_Stamp;
	private String Dm_Stamp;
	private int oid;
	
	public Grupo_EconomicoBean() {} 
	
	public String getCD_Grupo_Economico() 
	{
		return CD_Grupo_Economico;
	}
	public void setCD_Grupo_Economico(String CD_Grupo_Economico) 
	{
		this.CD_Grupo_Economico = CD_Grupo_Economico;
	}
	public String getNM_Grupo_Economico() 
	{
		return NM_Grupo_Economico;
	}
	public void setNM_Grupo_Economico(String NM_Grupo_Economico) 
	{
		this.NM_Grupo_Economico = NM_Grupo_Economico;
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
			// passando como parâmetro o NM_Grupo_Economico do DSN 
			// o NM_Grupo_Economico de usuário e a senha do banco.
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
				"SELECT MAX(OID_Grupo_Economico) FROM Grupos_Economicos");
			
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
		buff.append("INSERT INTO Grupos_Economicos (OID_Grupo_Economico, CD_Grupo_Economico, NM_Grupo_Economico, Dt_Stamp, Usuario_Stamp, Dm_Stamp) ");
		buff.append("VALUES (?,?,?,?,?,?)");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try 
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setInt(1, getOID());
			pstmt.setString(2, getCD_Grupo_Economico());
			pstmt.setString(3, getNM_Grupo_Economico());
			pstmt.setString(4, getDt_Stamp());
			pstmt.setString(5, getUsuario_Stamp());
			pstmt.setString(6, getDm_Stamp());
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
			// passando como parâmetro o NM_Grupo_Economico do DSN 
			// o NM_Grupo_Economico de usuário e a senha do banco.
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
		buff.append("UPDATE Grupos_Economicos SET NM_Grupo_Economico=?, Dt_Stamp=?, Usuario_Stamp=?, Dm_Stamp=? ");
		buff.append("WHERE OID_Grupo_Economico=?");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try 
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setString(1, getNM_Grupo_Economico());
			pstmt.setString(2, getDt_Stamp());
			pstmt.setString(3, getUsuario_Stamp());
			pstmt.setString(4, getDm_Stamp());
			pstmt.setInt(5, getOID());
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
			// passando como parâmetro o NM_Grupo_Economico do DSN 
			// o NM_Grupo_Economico de usuário e a senha do banco.
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
		buff.append("DELETE FROM Grupos_Economicos ");
		buff.append("WHERE OID_Grupo_Economico=?");
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
	
	public static final Grupo_EconomicoBean getByOID(int oid) 
		throws Exception 
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try 
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Grupo_Economico do DSN 
			// o NM_Grupo_Economico de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
		
		Grupo_EconomicoBean p = new Grupo_EconomicoBean();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Grupo_Economico, ");
			buff.append("	CD_Grupo_Economico, ");
			buff.append("	NM_Grupo_Economico ");
			buff.append("FROM Grupos_Economicos ");
			buff.append("WHERE OID_Grupo_Economico= ");
			buff.append(oid);
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = 
				stmt.executeQuery(buff.toString());
			
			while (cursor.next()) 
			{
				p.setOID(cursor.getInt(1));
				p.setCD_Grupo_Economico(cursor.getString(2));
				p.setNM_Grupo_Economico(cursor.getString(3));				
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
	
	public static final Grupo_EconomicoBean getByCD_Grupo_Economico(String CD_Grupo_Economico) 
		throws Exception 
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try 
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Grupo_Economico do DSN 
			// o NM_Grupo_Economico de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
		
		Grupo_EconomicoBean p = new Grupo_EconomicoBean();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Grupo_Economico, ");
			buff.append("	CD_Grupo_Economico, ");
			buff.append("	NM_Grupo_Economico ");
			buff.append("FROM Grupos_Economicos ");
			buff.append("WHERE CD_Grupo_Economico= '");
			buff.append(CD_Grupo_Economico);
			buff.append("'");
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = 
				stmt.executeQuery(buff.toString());
			
			while (cursor.next()) 
			{
				p.setOID(cursor.getInt(1));
				p.setCD_Grupo_Economico(cursor.getString(2));
				p.setNM_Grupo_Economico(cursor.getString(3));
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
	
	public static final List getByNM_Grupo_Economico(String NM_Grupo_Economico) 
		throws Exception 
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try 
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Grupo_Economico do DSN 
			// o NM_Grupo_Economico de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
		
		List Grupos_Economicos_Lista = new ArrayList();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Grupo_Economico, ");
			buff.append("	CD_Grupo_Economico, ");
			buff.append("	NM_Grupo_Economico ");
			buff.append("FROM Grupos_Economicos ");
			buff.append("WHERE NM_Grupo_Economico LIKE'");
			buff.append(NM_Grupo_Economico);
			buff.append("%' ORDER BY NM_Grupo_Economico ");
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = 
				stmt.executeQuery(buff.toString());
			
			while (cursor.next()) 
			{
				Grupo_EconomicoBean p = new Grupo_EconomicoBean();
				p.setOID(cursor.getInt(1));
				p.setCD_Grupo_Economico(cursor.getString(2));
				p.setNM_Grupo_Economico(cursor.getString(3));
				Grupos_Economicos_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return Grupos_Economicos_Lista;
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
		
		List Grupos_Economicos_Lista = new ArrayList();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Grupo_Economico, ");
			buff.append("	CD_Grupo_Economico, ");
			buff.append("	NM_Grupo_Economico ");
			buff.append("FROM Grupos_Economicos ORDER BY NM_Grupo_Economico ");
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = 
				stmt.executeQuery(buff.toString()); 
			
			while (cursor.next()) 
			{
				Grupo_EconomicoBean p = new Grupo_EconomicoBean(); 
				p.setOID(cursor.getInt(1));
				p.setCD_Grupo_Economico(cursor.getString(2));
				p.setNM_Grupo_Economico(cursor.getString(3));
				Grupos_Economicos_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)  
		{
			e.printStackTrace();
		}
		return Grupos_Economicos_Lista;
	}	
	
	public static void main(String args[]) 
		throws Exception 
	{
		Grupo_EconomicoBean pp = new Grupo_EconomicoBean();
		pp.setOID(2);
		pp.setCD_Grupo_Economico("LLL");
		pp.setNM_Grupo_Economico("Litro");
		pp.insert();
		
		Grupo_EconomicoBean p = getByOID(2);
		// //// System.out.println(p.getOID());
		// //// System.out.println(p.getCD_Grupo_Economico());
		// //// System.out.println(p.getNM_Grupo_Economico());
		
		
		
	}
}
