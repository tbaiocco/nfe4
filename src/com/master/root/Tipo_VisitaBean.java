package com.master.root;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import auth.OracleConnection2;

public class Tipo_VisitaBean 
{
	private String CD_Tipo_Visita;
	private String NM_Tipo_Visita;
	private String Usuario_Stamp;
	private String Dt_Stamp;
	private String Dm_Stamp;
	private int oid;
	
	public Tipo_VisitaBean() {} 
	
	public String getCD_Tipo_Visita() 
	{
		return CD_Tipo_Visita;
	}
	public void setCD_Tipo_Visita(String CD_Tipo_Visita) 
	{
		this.CD_Tipo_Visita = CD_Tipo_Visita;
	}
	public String getNM_Tipo_Visita() 
	{
		return NM_Tipo_Visita;
	}
	public void setNM_Tipo_Visita(String NM_Tipo_Visita) 
	{
		this.NM_Tipo_Visita = NM_Tipo_Visita;
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
			// passando como parâmetro o NM_Tipo_Visita do DSN 
			// o NM_Tipo_Visita de usuário e a senha do banco.
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
				"SELECT MAX(OID_Tipo_Visita) FROM Tipos_Visitas");
			
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
		buff.append("INSERT INTO Tipos_Visitas (OID_Tipo_Visita, CD_Tipo_Visita, NM_Tipo_Visita, Dt_Stamp, Usuario_Stamp, Dm_Stamp) ");
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
			pstmt.setString(2, getCD_Tipo_Visita());
			pstmt.setString(3, getNM_Tipo_Visita());
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
			// passando como parâmetro o NM_Tipo_Visita do DSN 
			// o NM_Tipo_Visita de usuário e a senha do banco.
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
		buff.append("UPDATE Tipos_Visitas SET NM_Tipo_Visita=?, Dt_Stamp=?, Usuario_Stamp=?, Dm_Stamp=? ");
		buff.append("WHERE OID_Tipo_Visita=?");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try 
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setString(1, getNM_Tipo_Visita());
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
			// passando como parâmetro o NM_Tipo_Visita do DSN 
			// o NM_Tipo_Visita de usuário e a senha do banco.
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
		buff.append("DELETE FROM Tipos_Visitas ");
		buff.append("WHERE OID_Tipo_Visita=?");
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
	
	public static final Tipo_VisitaBean getByOID(int oid) 
		throws Exception 
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try 
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Tipo_Visita do DSN 
			// o NM_Tipo_Visita de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
		
		Tipo_VisitaBean p = new Tipo_VisitaBean();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Tipo_Visita, ");
			buff.append("	CD_Tipo_Visita, ");
			buff.append("	NM_Tipo_Visita ");
			buff.append("FROM Tipos_Visitas ");
			buff.append("WHERE OID_Tipo_Visita= ");
			buff.append(oid);
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = 
				stmt.executeQuery(buff.toString());
			
			while (cursor.next()) 
			{
				p.setOID(cursor.getInt(1));
				p.setCD_Tipo_Visita(cursor.getString(2));
				p.setNM_Tipo_Visita(cursor.getString(3));				
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
	
	public static final Tipo_VisitaBean getByCD_Tipo_Visita(String CD_Tipo_Visita) 
		throws Exception 
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try 
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Tipo_Visita do DSN 
			// o NM_Tipo_Visita de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
		
		Tipo_VisitaBean p = new Tipo_VisitaBean();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Tipo_Visita, ");
			buff.append("	CD_Tipo_Visita, ");
			buff.append("	NM_Tipo_Visita ");
			buff.append("FROM Tipos_Visitas ");
			buff.append("WHERE CD_Tipo_Visita= '");
			buff.append(CD_Tipo_Visita);
			buff.append("'");
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = 
				stmt.executeQuery(buff.toString());
			
			while (cursor.next()) 
			{
				p.setOID(cursor.getInt(1));
				p.setCD_Tipo_Visita(cursor.getString(2));
				p.setNM_Tipo_Visita(cursor.getString(3));
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
	
	public static final List getByNM_Tipo_Visita(String NM_Tipo_Visita) 
		throws Exception 
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try 
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Tipo_Visita do DSN 
			// o NM_Tipo_Visita de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
		
		List Tipos_Visitas_Lista = new ArrayList();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Tipo_Visita, ");
			buff.append("	CD_Tipo_Visita, ");
			buff.append("	NM_Tipo_Visita ");
			buff.append("FROM Tipos_Visitas ");
			buff.append("WHERE NM_Tipo_Visita LIKE'");
			buff.append(NM_Tipo_Visita);
			buff.append("%'");
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = 
				stmt.executeQuery(buff.toString());
			
			while (cursor.next()) 
			{
				Tipo_VisitaBean p = new Tipo_VisitaBean();
				p.setOID(cursor.getInt(1));
				p.setCD_Tipo_Visita(cursor.getString(2));
				p.setNM_Tipo_Visita(cursor.getString(3));
				Tipos_Visitas_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return Tipos_Visitas_Lista;
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
		
		List Tipos_Visitas_Lista = new ArrayList();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Tipo_Visita, ");
			buff.append("	CD_Tipo_Visita, ");
			buff.append("	NM_Tipo_Visita ");
			buff.append("FROM Tipos_Visitas");
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = 
				stmt.executeQuery(buff.toString()); 
			
			while (cursor.next()) 
			{
				Tipo_VisitaBean p = new Tipo_VisitaBean(); 
				p.setOID(cursor.getInt(1));
				p.setCD_Tipo_Visita(cursor.getString(2));
				p.setNM_Tipo_Visita(cursor.getString(3));
				Tipos_Visitas_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)  
		{
			e.printStackTrace();
		}
		return Tipos_Visitas_Lista;
	}	
	
	public static void main(String args[]) 
		throws Exception 
	{
		Tipo_VisitaBean pp = new Tipo_VisitaBean();
		pp.setOID(2);
		pp.setCD_Tipo_Visita("LLL");
		pp.setNM_Tipo_Visita("Litro");
		pp.insert();
		
		Tipo_VisitaBean p = getByOID(2);
		// //// System.out.println(p.getOID());
		// //// System.out.println(p.getCD_Tipo_Visita());
		// //// System.out.println(p.getNM_Tipo_Visita());
		
		
		
	}
}
