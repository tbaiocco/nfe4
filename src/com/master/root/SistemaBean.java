package com.master.root;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import auth.OracleConnection2;

public class SistemaBean 
{
	private String CD_Sistema;
	private String NM_Sistema;
	private String Usuario_Stamp;
	private String Dt_Stamp;
	private String Dm_Stamp;
	private int oid;
	
	public SistemaBean() {} 
	
	public String getCD_Sistema() 
	{
		return CD_Sistema;
	}
	public void setCD_Sistema(String CD_Sistema) 
	{
		this.CD_Sistema = CD_Sistema;
	}
	public String getNM_Sistema() 
	{
		return NM_Sistema;
	}
	public void setNM_Sistema(String NM_Sistema) 
	{
		this.NM_Sistema = NM_Sistema;
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
			// passando como parâmetro o NM_Sistema do DSN 
			// o NM_Sistema de usuário e a senha do banco.
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
				"SELECT MAX(OID_Sistema) FROM Sistemas");
			
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
		buff.append("INSERT INTO Sistemas (OID_Sistema, CD_Sistema, NM_Sistema, Dt_Stamp, Usuario_Stamp, Dm_Stamp) ");
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
			pstmt.setString(2, getCD_Sistema());
			pstmt.setString(3, getNM_Sistema());
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
			// passando como parâmetro o NM_Sistema do DSN 
			// o NM_Sistema de usuário e a senha do banco.
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
		buff.append("UPDATE Sistemas SET NM_Sistema=?, Dt_Stamp=?, Usuario_Stamp=?, Dm_Stamp=? ");
		buff.append("WHERE OID_Sistema=?");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try 
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setString(1, getNM_Sistema());
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
			// passando como parâmetro o NM_Sistema do DSN 
			// o NM_Sistema de usuário e a senha do banco.
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
		buff.append("DELETE FROM Sistemas ");
		buff.append("WHERE OID_Sistema=?");
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
	
	public static final SistemaBean getByOID(int oid) 
		throws Exception 
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try 
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Sistema do DSN 
			// o NM_Sistema de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
		
		SistemaBean p = new SistemaBean();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Sistema, ");
			buff.append("	CD_Sistema, ");
			buff.append("	NM_Sistema ");
			buff.append("FROM Sistemas ");
			buff.append("WHERE OID_Sistema= ");
			buff.append(oid);
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = 
				stmt.executeQuery(buff.toString());
			
			while (cursor.next()) 
			{
				p.setOID(cursor.getInt(1));
				p.setCD_Sistema(cursor.getString(2));
				p.setNM_Sistema(cursor.getString(3));				
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
	
	public static final SistemaBean getByCD_Sistema(String CD_Sistema) 
		throws Exception 
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try 
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Sistema do DSN 
			// o NM_Sistema de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
		
		SistemaBean p = new SistemaBean();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Sistema, ");
			buff.append("	CD_Sistema, ");
			buff.append("	NM_Sistema ");
			buff.append("FROM Sistemas ");
			buff.append("WHERE CD_Sistema= '");
			buff.append(CD_Sistema);
			buff.append("'");
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = 
				stmt.executeQuery(buff.toString());
			
			while (cursor.next()) 
			{
				p.setOID(cursor.getInt(1));
				p.setCD_Sistema(cursor.getString(2));
				p.setNM_Sistema(cursor.getString(3));
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
	
	public static final List getByNM_Sistema(String NM_Sistema) 
		throws Exception 
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try 
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Sistema do DSN 
			// o NM_Sistema de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
		
		List Sistemas_Lista = new ArrayList();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Sistema, ");
			buff.append("	CD_Sistema, ");
			buff.append("	NM_Sistema ");
			buff.append("FROM Sistemas ");
			buff.append("WHERE NM_Sistema LIKE'");
			buff.append(NM_Sistema);
			buff.append("%'");
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = 
				stmt.executeQuery(buff.toString());
			
			while (cursor.next()) 
			{
				SistemaBean p = new SistemaBean();
				p.setOID(cursor.getInt(1));
				p.setCD_Sistema(cursor.getString(2));
				p.setNM_Sistema(cursor.getString(3));
				Sistemas_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return Sistemas_Lista;
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
		
		List Sistemas_Lista = new ArrayList();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Sistema, ");
			buff.append("	CD_Sistema, ");
			buff.append("	NM_Sistema ");
			buff.append("FROM Sistemas");
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = 
				stmt.executeQuery(buff.toString()); 
			
			while (cursor.next()) 
			{
				SistemaBean p = new SistemaBean(); 
				p.setOID(cursor.getInt(1));
				p.setCD_Sistema(cursor.getString(2));
				p.setNM_Sistema(cursor.getString(3));
				Sistemas_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)  
		{
			e.printStackTrace();
		}
		return Sistemas_Lista;
	}	
	
	public static void main(String args[]) 
		throws Exception 
	{
		SistemaBean pp = new SistemaBean();
		pp.setOID(2);
		pp.setCD_Sistema("LLL");
		pp.setNM_Sistema("Litro");
		pp.insert();
		
		SistemaBean p = getByOID(2);
		// //// System.out.println(p.getOID());
		// //// System.out.println(p.getCD_Sistema());
		// //// System.out.println(p.getNM_Sistema());
		
		
		
	}
}


