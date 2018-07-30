package com.master.root;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import auth.OracleConnection2;

public class Tipo_VencimentoBean 
{
	private String CD_Tipo_Vencimento;
	private String NM_Tipo_Vencimento;
	private String Usuario_Stamp;
	private String Dt_Stamp;
	private String Dm_Stamp;
	private int oid;
	
	public Tipo_VencimentoBean() {} 
	
	public String getCD_Tipo_Vencimento() 
	{
		return CD_Tipo_Vencimento;
	}
	public void setCD_Tipo_Vencimento(String CD_Tipo_Vencimento) 
	{
		this.CD_Tipo_Vencimento = CD_Tipo_Vencimento;
	}
	public String getNM_Tipo_Vencimento() 
	{
		return NM_Tipo_Vencimento;
	}
	public void setNM_Tipo_Vencimento(String NM_Tipo_Vencimento) 
	{
		this.NM_Tipo_Vencimento = NM_Tipo_Vencimento;
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
			// passando como parâmetro o NM_Tipo_Vencimento do DSN 
			// o NM_Tipo_Vencimento de usuário e a senha do banco.
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
				"SELECT MAX(OID_Tipo_Vencimento) FROM Tipos_Vencimentos");
			
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
		buff.append("INSERT INTO Tipos_Vencimentos (OID_Tipo_Vencimento, CD_Tipo_Vencimento, NM_Tipo_Vencimento, Dt_Stamp, Usuario_Stamp, Dm_Stamp) ");
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
			pstmt.setString(2, getCD_Tipo_Vencimento());
			pstmt.setString(3, getNM_Tipo_Vencimento());
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
			// passando como parâmetro o NM_Tipo_Vencimento do DSN 
			// o NM_Tipo_Vencimento de usuário e a senha do banco.
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
		buff.append("UPDATE Tipos_Vencimentos SET NM_Tipo_Vencimento=?, Dt_Stamp=?, Usuario_Stamp=?, Dm_Stamp=? ");
		buff.append("WHERE OID_Tipo_Vencimento=?");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try 
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setString(1, getNM_Tipo_Vencimento());
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
			// passando como parâmetro o NM_Tipo_Vencimento do DSN 
			// o NM_Tipo_Vencimento de usuário e a senha do banco.
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
		buff.append("DELETE FROM Tipos_Vencimentos ");
		buff.append("WHERE OID_Tipo_Vencimento=?");
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
	
	public static final Tipo_VencimentoBean getByOID(int oid) 
		throws Exception 
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try 
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Tipo_Vencimento do DSN 
			// o NM_Tipo_Vencimento de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
		
		Tipo_VencimentoBean p = new Tipo_VencimentoBean();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Tipo_Vencimento, ");
			buff.append("	CD_Tipo_Vencimento, ");
			buff.append("	NM_Tipo_Vencimento ");
			buff.append("FROM Tipos_Vencimentos ");
			buff.append("WHERE OID_Tipo_Vencimento= ");
			buff.append(oid);
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = 
				stmt.executeQuery(buff.toString());
			
			while (cursor.next()) 
			{
				p.setOID(cursor.getInt(1));
				p.setCD_Tipo_Vencimento(cursor.getString(2));
				p.setNM_Tipo_Vencimento(cursor.getString(3));				
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
	
	public static final Tipo_VencimentoBean getByCD_Tipo_Vencimento(String CD_Tipo_Vencimento) 
		throws Exception 
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try 
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Tipo_Vencimento do DSN 
			// o NM_Tipo_Vencimento de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
		
		Tipo_VencimentoBean p = new Tipo_VencimentoBean();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Tipo_Vencimento, ");
			buff.append("	CD_Tipo_Vencimento, ");
			buff.append("	NM_Tipo_Vencimento ");
			buff.append("FROM Tipos_Vencimentos ");
			buff.append("WHERE CD_Tipo_Vencimento= '");
			buff.append(CD_Tipo_Vencimento);
			buff.append("'");
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = 
				stmt.executeQuery(buff.toString());
			
			while (cursor.next()) 
			{
				p.setOID(cursor.getInt(1));
				p.setCD_Tipo_Vencimento(cursor.getString(2));
				p.setNM_Tipo_Vencimento(cursor.getString(3));
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
	
	public static final List getByNM_Tipo_Vencimento(String NM_Tipo_Vencimento) 
		throws Exception 
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try 
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Tipo_Vencimento do DSN 
			// o NM_Tipo_Vencimento de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
		
		List Tipos_Vencimentos_Lista = new ArrayList();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Tipo_Vencimento, ");
			buff.append("	CD_Tipo_Vencimento, ");
			buff.append("	NM_Tipo_Vencimento ");
			buff.append("FROM Tipos_Vencimentos ");
			buff.append("WHERE NM_Tipo_Vencimento LIKE'");
			buff.append(NM_Tipo_Vencimento);
			buff.append("%'");
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = 
				stmt.executeQuery(buff.toString());
			
			while (cursor.next()) 
			{
				Tipo_VencimentoBean p = new Tipo_VencimentoBean();
				p.setOID(cursor.getInt(1));
				p.setCD_Tipo_Vencimento(cursor.getString(2));
				p.setNM_Tipo_Vencimento(cursor.getString(3));
				Tipos_Vencimentos_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return Tipos_Vencimentos_Lista;
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
		
		List Tipos_Vencimentos_Lista = new ArrayList();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Tipo_Vencimento, ");
			buff.append("	CD_Tipo_Vencimento, ");
			buff.append("	NM_Tipo_Vencimento ");
			buff.append("FROM Tipos_Vencimentos");
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = 
				stmt.executeQuery(buff.toString()); 
			
			while (cursor.next()) 
			{
				Tipo_VencimentoBean p = new Tipo_VencimentoBean(); 
				p.setOID(cursor.getInt(1));
				p.setCD_Tipo_Vencimento(cursor.getString(2));
				p.setNM_Tipo_Vencimento(cursor.getString(3));
				Tipos_Vencimentos_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)  
		{
			e.printStackTrace();
		}
		return Tipos_Vencimentos_Lista;
	}	
	
	public static void main(String args[]) 
		throws Exception 
	{
		Tipo_VencimentoBean pp = new Tipo_VencimentoBean();
		pp.setOID(2);
		pp.setCD_Tipo_Vencimento("LLL");
		pp.setNM_Tipo_Vencimento("Litro");
		pp.insert();
		
		Tipo_VencimentoBean p = getByOID(2);
		// //// System.out.println(p.getOID());
		// //// System.out.println(p.getCD_Tipo_Vencimento());
		// //// System.out.println(p.getNM_Tipo_Vencimento());
		
		
		
	}
}
