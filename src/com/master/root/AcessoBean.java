package com.master.root;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import auth.OracleConnection2;

public class AcessoBean 
{
	private String CD_Usuario;
	private String NM_Usuario;
	private String NM_URL;
	private String DM_Perfil;	
	private String Usuario_Stamp;
	private String Dt_Stamp;
	private String Dm_Stamp;
	private int oid;
	private int OID_Grupo;	
	private int OID_Sistema;		
	private int OID_Usuario;			
	private int OID_Unidade;					
	private int OID_Programa;						
	private String CD_Sistema;
	private String NM_Sistema;
	private String CD_Grupo;
	private String NM_Grupo;
	private String DM_Perfil_Programa;
	private String CD_Unidade;
	private String NM_Sigla;
	private String CD_Programa;	
	private String NM_Programa;	
	
	public AcessoBean() {} 
	
	public String getCD_Usuario() 
	{
		return CD_Usuario;
	}
	public void setCD_Usuario(String CD_Usuario) 
	{
		this.CD_Usuario = CD_Usuario;
	}
	public String getNM_Usuario() 
	{
		return NM_Usuario;
	}
	public void setNM_Usuario(String NM_Usuario) 
	{
		this.NM_Usuario = NM_Usuario;
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
	public String getDM_Perfil_Programa() 
	{
		return DM_Perfil_Programa;
	}
	public void setDM_Perfil_Programa(String DM_Perfil_Programa) 
	{
		this.DM_Perfil_Programa = DM_Perfil_Programa;
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
	
	public int getOID_Unidade() 
	{
		return OID_Unidade;
	}
	public void setOID_Unidade(int n) 
	{
		this.OID_Unidade = n;
	}
	
	public int getOID_Usuario() 
	{
		return OID_Usuario;
	}
	public void setOID_Usuario(int n) 
	{
		this.OID_Usuario = n;
	}
	
	public int getOID_Programa() 
	{
		return OID_Programa;
	}
	public void setOID_Programa(int n) 
	{
		this.OID_Programa = n;
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
	
	public String getCD_Unidade() 
	{
		return CD_Unidade;
	}
	public void setCD_Unidade(String CD_Unidade) 
	{
		this.CD_Unidade = CD_Unidade;
	}
	public String getNM_Sigla() 
	{
		return NM_Sigla;
	}
	public void setNM_Sigla(String NM_Sigla) 
	{
		this.NM_Sigla = NM_Sigla;
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
			// passando como parâmetro o NM_Usuario do DSN 
			// o NM_Usuario de usuário e a URL do banco.
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
				"SELECT MAX(OID_Acesso) FROM Acessos");
			
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
		buff.append("INSERT INTO Acessos (OID_Acesso, OID_Sistema, OID_Usuario, Dt_Stamp, Usuario_Stamp, Dm_Stamp ) ");
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
			pstmt.setInt(2, getOID_Sistema());			
			pstmt.setInt(3, getOID_Usuario());											
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
			// passando como parâmetro o NM_Usuario do DSN 
			// o NM_Usuario de usuário e a URL do banco.
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
		buff.append("UPDATE Acessos SET NM_Usuario=?, NM_URL=?, DM_Perfil=? ");
		buff.append("WHERE OID_Acesso=?");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try 
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			
			
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
			// passando como parâmetro o NM_Usuario do DSN 
			// o NM_Usuario de usuário e a URL do banco.
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
		buff.append("DELETE FROM Acessos ");
		buff.append("WHERE OID_Acesso=?");
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
	
	public static final AcessoBean getByOID(int oid) 
		throws Exception 
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try 
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Usuario do DSN 
			// o NM_Usuario de usuário e a URL do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
		
		AcessoBean p = new AcessoBean();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Acesso, ");
			buff.append("	Usuarios.OID_Usuario, ");
			buff.append("	Usuarios.CD_Usuario, ");
			buff.append("	Usuarios.NM_Usuario, ");
			buff.append("	Usuarios.DM_Perfil,  ");															
			buff.append("	Sistemas.OID_Sistema, ");						
			buff.append("	Sistemas.CD_Sistema, ");									
			buff.append("	Sistemas.NM_Sistema, ");												
			buff.append("	Grupos.OID_Grupo, ");						
			buff.append("	Grupos.CD_Grupo, ");									
			buff.append("	Grupos.NM_Grupo,  ");												
			buff.append("	Programas.OID_Programa, ");						
			buff.append("	Programas.CD_Programa, ");									
			buff.append("	Programas.NM_Programa,  ");												
			buff.append("	Programas.NM_URL,  ");															
			buff.append("	Programas.DM_Perfil,  ");																		
			buff.append("	Unidades.OID_Unidade, ");						
			buff.append("	Unidades.CD_Unidade, ");									
			buff.append("	Unidades.NM_Sigla  ");												
			buff.append("FROM Acessos, Sistemas, Grupos, Usuarios, Unidades ");
			buff.append("WHERE  Acessos.OID_Usuario  = Usuarios.OID_Usuario AND ");
			buff.append("       Acessos.OID_Sistema  = Sistemas.OID_Sistema AND ");
			buff.append("	    Sistemas.OID_Sistema = Programas.OID_Sistemas AND ");
			buff.append("       Programas.OID_Grupo  = Grupos.OID_Grupo AND ");
			buff.append("	    Usuarios.OID_Unidade = Unidades.OID_Unidade AND ");
			buff.append("	    OID_Acesso= ");
			buff.append(oid);
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = 
				stmt.executeQuery(buff.toString());
			
			while (cursor.next()) 
			{
				p.setOID(cursor.getInt(1));
				p.setOID_Usuario(cursor.getInt(2));				
				p.setCD_Usuario(cursor.getString(3));
				p.setNM_Usuario(cursor.getString(4));				
				p.setDM_Perfil(cursor.getString(5));								
				p.setOID_Sistema(cursor.getInt(6));				
				p.setCD_Sistema(cursor.getString(7));												
				p.setNM_Sistema(cursor.getString(8));								
				p.setOID_Grupo(cursor.getInt(9));				
				p.setCD_Grupo(cursor.getString(10));								
				p.setNM_Grupo(cursor.getString(11));								
				p.setOID_Programa(cursor.getInt(12));				
				p.setCD_Programa(cursor.getString(13));								
				p.setNM_Programa(cursor.getString(14));								
				p.setNM_URL(cursor.getString(15));								
				p.setDM_Perfil_Programa(cursor.getString(16));								
				p.setOID_Unidade(cursor.getInt(17));				
				p.setCD_Unidade(cursor.getString(18));								
				p.setNM_Sigla(cursor.getString(19));												
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
	
	
	public static final List getAll22()
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
		
		List Acessos_Lista = new ArrayList();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Acesso, ");
			buff.append("	Acessos.OID_Usuario, ");		
			buff.append("	CD_Usuario, ");		
			buff.append("	NM_Usuario, ");			
			buff.append("	DM_Perfil  ");
			buff.append("FROM Acessos, Usuarios  ");
			buff.append("WHERE  Acessos.OID_Usuario  = Usuarios.OID_Usuario ");
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = 
				stmt.executeQuery(buff.toString()); 
			
			while (cursor.next()) 
			{
				AcessoBean p = new AcessoBean(); 
				p.setOID(cursor.getInt(1));
				p.setOID_Usuario(cursor.getInt(2));				
				p.setCD_Usuario(cursor.getString(3));
				p.setNM_Usuario(cursor.getString(4));				
				p.setDM_Perfil(cursor.getString(5));								
				Acessos_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)  
		{
			e.printStackTrace();
		}
		return Acessos_Lista;
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
		
		List Acessos_Lista = new ArrayList();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT  ");
			buff.append("	Acessos.OID_Acesso, ");		
			buff.append("	Acessos.OID_Usuario, ");
			buff.append("	Usuarios.CD_Usuario, ");		
			buff.append("	Usuarios.NM_Usuario, ");			
			buff.append("	Usuarios.DM_Perfil,  ");
			buff.append("	Acessos.OID_Sistema, ");						
			buff.append("	Sistemas.CD_Sistema, ");									
			buff.append("	Sistemas.NM_Sistema, ");												
			buff.append("	Grupos.OID_Grupo, ");						
			buff.append("	Grupos.CD_Grupo, ");									
			buff.append("	Grupos.NM_Grupo,  ");												
			buff.append("	Programas.OID_Programa, ");						
			buff.append("	Programas.CD_Programa, ");									
			buff.append("	Programas.NM_Programa,  ");												
			buff.append("	Programas.NM_URL,  ");															
			buff.append("	Programas.DM_Perfil,  ");																		
			buff.append("	Unidades.OID_Unidade, ");						
			buff.append("	Unidades.CD_Unidade, ");									
			buff.append("	Unidades.NM_Sigla  ");												
			buff.append(" FROM Acessos, Sistemas, Grupos, Usuarios, Unidades, Programas  ");
			buff.append(" WHERE  Acessos.OID_Usuario  = Usuarios.OID_Usuario  ");
			buff.append(" AND    Acessos.OID_Sistema  = Sistemas.OID_Sistema  ");
			buff.append(" AND	 Sistemas.OID_Sistema = Programas.OID_Sistema  ");
			buff.append(" AND    Programas.OID_Grupo  = Grupos.OID_Grupo  ");
			buff.append(" AND	 Usuarios.OID_Unidade = Unidades.OID_Unidade ");
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = 
				stmt.executeQuery(buff.toString()); 
			
			while (cursor.next()) 
			{
				AcessoBean p = new AcessoBean(); 
				p.setOID(cursor.getInt(1));
				p.setOID_Usuario(cursor.getInt(2));				
				p.setCD_Usuario(cursor.getString(3));
				p.setNM_Usuario(cursor.getString(4));				
				p.setDM_Perfil(cursor.getString(5));								
				
				p.setOID_Sistema(cursor.getInt(6));				
				p.setCD_Sistema(cursor.getString(7));												
				p.setNM_Sistema(cursor.getString(8));								
				
				p.setOID_Grupo(cursor.getInt(9));				
				p.setCD_Grupo(cursor.getString(10));								
				p.setNM_Grupo(cursor.getString(11));								
				
				p.setOID_Programa(cursor.getInt(12));				
				p.setCD_Programa(cursor.getString(13));								
				p.setNM_Programa(cursor.getString(14));								
				p.setNM_URL(cursor.getString(15));								
				p.setDM_Perfil_Programa(cursor.getString(16));								
				
				p.setOID_Unidade(cursor.getInt(17));				
				p.setCD_Unidade(cursor.getString(18));								
				p.setNM_Sigla(cursor.getString(19));												
				
				Acessos_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)  
		{
			e.printStackTrace();
		}
		return Acessos_Lista;
	}	
	
	
	public static final List getNM_Acesso()
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
		
		List Acessos_Lista = new ArrayList();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append("	Usuarios.OID_Usuario, ");
			buff.append("	Usuarios.CD_Usuario, ");
			buff.append("	Usuarios.NM_Usuario, ");
			buff.append("	Usuarios.DM_Perfil,  ");															
			buff.append("	Sistemas.OID_Sistema, ");						
			buff.append("	Sistemas.CD_Sistema, ");									
			buff.append("	Sistemas.NM_Sistema, ");												
			buff.append("	Grupos.OID_Grupo, ");						
			buff.append("	Grupos.CD_Grupo, ");									
			buff.append("	Grupos.NM_Grupo,  ");												
			buff.append("	Programas.OID_Programa, ");						
			buff.append("	Programas.CD_Programa, ");									
			buff.append("	Programas.NM_Programa,  ");												
			buff.append("	Programas.NM_URL,  ");															
			buff.append("	Programas.DM_Perfil,  ");																		
			buff.append("	Unidades.OID_Unidade, ");						
			buff.append("	Unidades.CD_Unidade, ");									
			buff.append("	Unidades.NM_Sigla  ");												
			buff.append("FROM Acessos, Sistemas, Grupos, Usuarios, Unidades ");
			buff.append("WHERE  Acessos.OID_Usuario  = Usuarios.OID_Usuario AND ");
			buff.append("       Acessos.OID_Sistema  = Sistemas.OID_Sistema AND ");
			buff.append("	    Sistemas.OID_Sistema = Programas.OID_Sistemas AND ");
			buff.append("       Programas.OID_Grupo  = Grupos.OID_Grupo AND ");
			buff.append("	    Usuarios.OID_Unidade = Unidades.OID_Unidade ");
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = 
				stmt.executeQuery(buff.toString()); 
			
			while (cursor.next()) 
			{
				AcessoBean p = new AcessoBean(); 
				p.setOID(cursor.getInt(1));
				p.setOID_Usuario(cursor.getInt(2));				
				p.setCD_Usuario(cursor.getString(3));
				p.setNM_Usuario(cursor.getString(4));				
				p.setDM_Perfil(cursor.getString(5));								
				p.setOID_Sistema(cursor.getInt(6));				
				p.setCD_Sistema(cursor.getString(7));												
				p.setNM_Sistema(cursor.getString(8));								
				p.setOID_Grupo(cursor.getInt(9));				
				p.setCD_Grupo(cursor.getString(10));								
				p.setNM_Grupo(cursor.getString(11));								
				p.setOID_Programa(cursor.getInt(12));				
				p.setCD_Programa(cursor.getString(13));								
				p.setNM_Programa(cursor.getString(14));								
				p.setNM_URL(cursor.getString(15));								
				p.setDM_Perfil_Programa(cursor.getString(16));								
				p.setOID_Unidade(cursor.getInt(17));				
				p.setCD_Unidade(cursor.getString(18));								
				p.setNM_Sigla(cursor.getString(19));												
				
				Acessos_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)  
		{
			e.printStackTrace();
		}
		return Acessos_Lista;
	}	
	
	
	public static void main(String args[]) 
		throws Exception 
	{
		AcessoBean pp = new AcessoBean();
		pp.setOID(10);
		pp.setOID_Usuario(1);
		pp.setOID_Sistema(1);
		
		pp.insert();
		
	}
}


