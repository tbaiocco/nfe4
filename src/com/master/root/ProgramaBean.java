package com.master.root;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import auth.OracleConnection2;

public class ProgramaBean 
{
	private String CD_Programa;
	private String NM_Programa;
	private String NM_URL;
	private String DM_Perfil;	
	private String Usuario_Stamp;
	private String Dt_Stamp;
	private String Dm_Stamp;
	private int oid;
	private int OID_Grupo;	
	private int OID_Sistema;		
	private String CD_Sistema;
	private String NM_Sistema;
	private String CD_Grupo;
	private String NM_Grupo;
	
	public ProgramaBean() {} 
	
	public String getCD_Programa() 
	{
		return CD_Programa;
	}
	public void setCD_Programa(String CD_Programa) 
	{
		this.CD_Programa = CD_Programa;
	}
	public String getNM_Programa() 
	{
		return NM_Programa;
	}
	public void setNM_Programa(String NM_Programa) 
	{
		this.NM_Programa = NM_Programa;
	}
	public String getNM_URL() 
	{
		return NM_URL;
	}
	public void setNM_URL(String NM_URL) 
	{
		this.NM_URL = NM_URL;
	}
	public String getDM_Perfil() 
	{
		return DM_Perfil;
	}
	public void setDM_Perfil(String DM_Perfil) 
	{
		this.DM_Perfil = DM_Perfil;
	}
	public int getOID_Grupo() 
	{
		return OID_Grupo;
	}
	public void setOID_Grupo(int n) 
	{
		this.OID_Grupo = n;
	}
	public int getOID_Sistema() 
	{
		return OID_Sistema;
	}
	public void setOID_Sistema(int n) 
	{
		this.OID_Sistema = n;
	}
	public String getCD_Grupo() 
	{
		return CD_Grupo;
	}
	public void setCD_Grupo(String CD_Grupo) 
	{
		this.CD_Grupo = CD_Grupo;
	}
	public String getNM_Grupo() 
	{
		return NM_Grupo;
	}
	public void setNM_Grupo(String NM_Grupo) 
	{
		this.NM_Grupo = NM_Grupo;
	}
	
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
			// passando como parâmetro o NM_Programa do DSN 
			// o NM_Programa de usuário e a URL do banco.
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
				"SELECT MAX(OID_Programa) FROM Programas");
			
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
		buff.append("INSERT INTO Programas (OID_Programa, OID_Sistema, OID_Grupo, CD_Programa, NM_Programa, NM_URL, DM_Perfil, Dt_Stamp, Usuario_Stamp, Dm_Stamp ) ");
		buff.append("VALUES (?,?,?,?,?,?,?,?,?,?)");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try 
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setInt(1, getOID());
			pstmt.setInt(2, getOID_Sistema());			
			pstmt.setInt(3, getOID_Grupo());								
			pstmt.setString(4, getCD_Programa());
			pstmt.setString(5, getNM_Programa());
			pstmt.setString(6, getNM_URL());			
			pstmt.setString(7, getDM_Perfil());			
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
			// passando como parâmetro o NM_Programa do DSN 
			// o NM_Programa de usuário e a URL do banco.
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
		buff.append("UPDATE Programas SET NM_Programa=?, NM_URL=?, DM_Perfil=? ");
		buff.append("WHERE OID_Programa=?");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try 
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setString(1, getNM_Programa());
			pstmt.setString(2, getNM_URL());			
			pstmt.setString(3, getDM_Perfil());						
			pstmt.setInt(4, getOID());
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
			// passando como parâmetro o NM_Programa do DSN 
			// o NM_Programa de usuário e a URL do banco.
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
		buff.append("DELETE FROM Programas ");
		buff.append("WHERE OID_Programa=?");
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
	
	public static final ProgramaBean getByOID(int oid) 
		throws Exception 
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try 
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Programa do DSN 
			// o NM_Programa de usuário e a URL do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
		
		ProgramaBean p = new ProgramaBean();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Programa, ");
			buff.append("	CD_Programa, ");
			buff.append("	NM_Programa, ");
			buff.append("	NM_URL, ");
			buff.append("	DM_Perfil, ");			
			buff.append("	Sistemas.OID_Sistema, ");						
			buff.append("	Sistemas.CD_Sistema, ");									
			buff.append("	Sistemas.NM_Sistema, ");												
			buff.append("	Grupos.OID_Grupo, ");						
			buff.append("	Grupos.CD_Grupo, ");									
			buff.append("	Grupos.NM_Grupo  ");												
			buff.append("FROM Programas, Sistemas, Grupos ");
			buff.append("WHERE Programas.OID_Sistema = Sistemas.OID_Sistema AND Programas.OID_Grupo = Grupos.OID_Grupo ");
			buff.append("AND OID_Programa= ");
			buff.append(oid);
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = 
				stmt.executeQuery(buff.toString());
			
			while (cursor.next()) 
			{
				p.setOID(cursor.getInt(1));
				p.setCD_Programa(cursor.getString(2));
				p.setNM_Programa(cursor.getString(3));				
				p.setNM_URL(cursor.getString(4));								
				p.setDM_Perfil(cursor.getString(5));								
				p.setOID_Sistema(cursor.getInt(6));				
				p.setCD_Sistema(cursor.getString(7));												
				p.setNM_Sistema(cursor.getString(8));								
				p.setOID_Grupo(cursor.getInt(9));				
				p.setCD_Grupo(cursor.getString(10));								
				p.setNM_Grupo(cursor.getString(11));								
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
	
	public static final ProgramaBean getByCD_Programa(String CD_Programa) 
		throws Exception 
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try 
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Programa do DSN 
			// o NM_Programa de usuário e a URL do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
		
		ProgramaBean p = new ProgramaBean();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Programa, ");
			buff.append("	CD_Programa, ");
			buff.append("	NM_Programa, ");
			buff.append("	NM_URL, ");
			buff.append("	DM_Perfil, ");			
			buff.append("	Sistemas.OID_Sistema, ");						
			buff.append("	Sistemas.CD_Sistema, ");									
			buff.append("	Sistemas.NM_Sistema, ");												
			buff.append("	Grupos.OID_Grupo, ");						
			buff.append("	Grupos.CD_Grupo, ");									
			buff.append("	Grupos.NM_Grupo  ");												
			buff.append("FROM Programas, Sistemas, Grupos ");
			buff.append("WHERE Programas.OID_Sistema = Sistemas.OID_Sistema AND Programas.OID_Grupo = Grupos.OID_Grupo ");
			buff.append("AND CD_Programa =' ");
			buff.append(CD_Programa);
			buff.append("'");
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = 
				stmt.executeQuery(buff.toString());
			
			while (cursor.next()) 
			{
				p.setOID(cursor.getInt(1));
				p.setCD_Programa(cursor.getString(2));
				p.setNM_Programa(cursor.getString(3));				
				p.setNM_URL(cursor.getString(4));								
				p.setDM_Perfil(cursor.getString(5));								
				p.setOID_Sistema(cursor.getInt(6));				
				p.setCD_Sistema(cursor.getString(7));												
				p.setNM_Sistema(cursor.getString(8));								
				p.setOID_Grupo(cursor.getInt(9));				
				p.setCD_Grupo(cursor.getString(10));								
				p.setNM_Grupo(cursor.getString(11));								
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
	
	
	
	public static final List getByNM_Programa(String NM_Programa) 
		throws Exception 
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try 
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Programa do DSN 
			// o NM_Programa de usuário e a URL do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
		
		List Programas_Lista = new ArrayList();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Programa, ");
			buff.append("	CD_Programa, ");
			buff.append("	NM_Programa, ");
			buff.append("	NM_URL, ");
			buff.append("	DM_Perfil, ");			
			buff.append("	Sistemas.OID_Sistema, ");						
			buff.append("	Sistemas.CD_Sistema, ");									
			buff.append("	Sistemas.NM_Sistema, ");												
			buff.append("	Grupos.OID_Grupo, ");						
			buff.append("	Grupos.CD_Grupo, ");									
			buff.append("	Grupos.NM_Grupo  ");												
			buff.append("FROM Programas, Sistemas, Grupos ");
			buff.append("WHERE Programas.OID_Sistema = Sistemas.OID_Sistema AND Programas.OID_Grupo = Grupos.OID_Grupo ");
			buff.append("AND NM_Programa LIKE'");
			buff.append(NM_Programa);
			buff.append("%'");
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = 
				stmt.executeQuery(buff.toString());
			
			while (cursor.next()) 
			{
				ProgramaBean p = new ProgramaBean();
				p.setOID(cursor.getInt(1));
				p.setCD_Programa(cursor.getString(2));
				p.setNM_Programa(cursor.getString(3));				
				p.setNM_URL(cursor.getString(4));								
				p.setDM_Perfil(cursor.getString(5));								
				p.setOID_Sistema(cursor.getInt(6));				
				p.setCD_Sistema(cursor.getString(7));												
				p.setNM_Sistema(cursor.getString(8));								
				p.setOID_Grupo(cursor.getInt(9));				
				p.setCD_Grupo(cursor.getString(10));								
				p.setNM_Grupo(cursor.getString(11));								
				Programas_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return Programas_Lista;
	}
	
	public static final List getByCD_Sistema(String CD_Sistema) 
		throws Exception 
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try 
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Programa do DSN 
			// o NM_Programa de usuário e a URL do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
		
		List Programas_Lista = new ArrayList();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Programa, ");
			buff.append("	CD_Programa, ");
			buff.append("	NM_Programa, ");
			buff.append("	NM_URL, ");
			buff.append("	DM_Perfil, ");			
			buff.append("	Sistemas.OID_Sistema, ");						
			buff.append("	Sistemas.CD_Sistema, ");									
			buff.append("	Sistemas.NM_Sistema, ");												
			buff.append("	Grupos.OID_Grupo, ");						
			buff.append("	Grupos.CD_Grupo, ");									
			buff.append("	Grupos.NM_Grupo  ");												
			buff.append("FROM Programas, Sistemas, Grupos ");
			buff.append("WHERE Programas.OID_Sistema = Sistemas.OID_Sistema AND Programas.OID_Grupo = Grupos.OID_Grupo ");
			buff.append("AND CD_Sistema =' ");
			buff.append(CD_Sistema);
			buff.append("'");
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = 
				stmt.executeQuery(buff.toString());
			
			while (cursor.next()) 
			{
				ProgramaBean p = new ProgramaBean();
				p.setOID(cursor.getInt(1));
				p.setCD_Programa(cursor.getString(2));
				p.setNM_Programa(cursor.getString(3));				
				p.setNM_URL(cursor.getString(4));								
				p.setDM_Perfil(cursor.getString(5));								
				p.setOID_Sistema(cursor.getInt(6));				
				p.setCD_Sistema(cursor.getString(7));												
				p.setNM_Sistema(cursor.getString(8));								
				p.setOID_Grupo(cursor.getInt(9));				
				p.setCD_Grupo(cursor.getString(10));								
				p.setNM_Grupo(cursor.getString(11));								
				Programas_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return Programas_Lista;
	}
	
	public static final List getByOID_Sistema(int OID_Sistema) 
		throws Exception 
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try 
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Programa do DSN 
			// o NM_Programa de usuário e a URL do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
		
		List Programas_Lista = new ArrayList();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Programa, ");
			buff.append("	CD_Programa, ");
			buff.append("	NM_Programa, ");
			buff.append("	NM_URL, ");
			buff.append("	DM_Perfil, ");			
			buff.append("	Sistemas.OID_Sistema, ");						
			buff.append("	Sistemas.CD_Sistema, ");									
			buff.append("	Sistemas.NM_Sistema, ");												
			buff.append("	Grupos.OID_Grupo, ");						
			buff.append("	Grupos.CD_Grupo, ");									
			buff.append("	Grupos.NM_Grupo  ");												
			buff.append("FROM Programas, Sistemas, Grupos ");
			buff.append("WHERE Programas.OID_Sistema = Sistemas.OID_Sistema AND Programas.OID_Grupo = Grupos.OID_Grupo ");
			buff.append("AND OID_Sistema = ");
			buff.append(OID_Sistema);
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = 
				stmt.executeQuery(buff.toString());
			
			while (cursor.next()) 
			{
				ProgramaBean p = new ProgramaBean();
				p.setOID(cursor.getInt(1));
				p.setCD_Programa(cursor.getString(2));
				p.setNM_Programa(cursor.getString(3));				
				p.setNM_URL(cursor.getString(4));								
				p.setDM_Perfil(cursor.getString(5));								
				p.setOID_Sistema(cursor.getInt(6));				
				p.setCD_Sistema(cursor.getString(7));												
				p.setNM_Sistema(cursor.getString(8));								
				p.setOID_Grupo(cursor.getInt(9));				
				p.setCD_Grupo(cursor.getString(10));								
				p.setNM_Grupo(cursor.getString(11));								
				Programas_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return Programas_Lista;
	}
	
	
	public static final List getAll()
		throws Exception 
	{
		Connection conn = null;
		try 
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o nome do DSN 
			// o nome de usuário e a URL do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
		
		List Programas_Lista = new ArrayList();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Programa, ");
			buff.append("	CD_Programa, ");
			buff.append("	NM_Programa, ");
			buff.append("	NM_URL, ");
			buff.append("	DM_Perfil, ");			
			buff.append("	Sistemas.OID_Sistema, ");						
			buff.append("	Sistemas.CD_Sistema, ");									
			buff.append("	Sistemas.NM_Sistema, ");												
			buff.append("	Grupos.OID_Grupo, ");						
			buff.append("	Grupos.CD_Grupo, ");									
			buff.append("	Grupos.NM_Grupo  ");												
			buff.append("FROM Programas, Sistemas, Grupos ");
			buff.append("WHERE Programas.OID_Sistema = Sistemas.OID_Sistema ");
			buff.append("AND   Programas.OID_Grupo   = Grupos.OID_Grupo ");
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = 
				stmt.executeQuery(buff.toString()); 
			
			while (cursor.next()) 
			{
				ProgramaBean p = new ProgramaBean(); 
				p.setOID(cursor.getInt(1));
				p.setCD_Programa(cursor.getString(2));
				p.setNM_Programa(cursor.getString(3));				
				p.setNM_URL(cursor.getString(4));								
				p.setDM_Perfil(cursor.getString(5));								
				p.setOID_Sistema(cursor.getInt(6));				
				p.setCD_Sistema(cursor.getString(7));												
				p.setNM_Sistema(cursor.getString(8));								
				p.setOID_Grupo(cursor.getInt(9));				
				p.setCD_Grupo(cursor.getString(10));								
				p.setNM_Grupo(cursor.getString(11));								
				
				Programas_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)  
		{
			e.printStackTrace();
		}
		return Programas_Lista;
	}	
	
	
	public static void main(String args[]) 
		throws Exception 
	{
		ProgramaBean pp = new ProgramaBean();
		pp.setOID(2);
		pp.setOID_Grupo(1);		
		pp.setCD_Programa("RoP");
		pp.setNM_Programa("Ronaldo Petuco");
		pp.insert();
		
		ProgramaBean p = getByOID(2);
		// //// System.out.println(p.getOID());
		// //// System.out.println(p.getCD_Programa());
		// //// System.out.println(p.getNM_Programa());
		
		
		
	}
}


