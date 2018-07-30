package com.master.root;
import java.sql.*;
import java.util.*;
import auth.*;

public class Unidade_ProdutoBean 
{
	private String CD_Unidade_Produto;
	private String NM_Unidade_Produto;
	private String Usuario_Stamp;
	private String Dt_Stamp;
	private String Dm_Stamp;
	private String Dm_Pesagem;
	private int oid;
	
	public Unidade_ProdutoBean() {} 
	
	public String getCD_Unidade_Produto() 
	{
		return CD_Unidade_Produto;
	}
	public void setCD_Unidade_Produto(String CD_Unidade_Produto) 
	{
		this.CD_Unidade_Produto = CD_Unidade_Produto;
	}
	public String getNM_Unidade_Produto() 
	{
		return NM_Unidade_Produto;
	}
	public void setNM_Unidade_Produto(String NM_Unidade_Produto) 
	{
		this.NM_Unidade_Produto = NM_Unidade_Produto;
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
			// passando como parâmetro o NM_Unidade_Produto do DSN 
			// o NM_Unidade_Produto de usuário e a senha do banco.
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
				"SELECT MAX(OID_Unidade_Produto) FROM Unidades_Produtos");
			
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
		buff.append("INSERT INTO Unidades_Produtos (OID_Unidade_Produto, CD_Unidade_Produto, NM_Unidade_Produto, Dt_Stamp, Usuario_Stamp, Dm_Stamp) ");
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
			pstmt.setString(2, getCD_Unidade_Produto());
			pstmt.setString(3, getNM_Unidade_Produto());
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
			// passando como parâmetro o NM_Unidade_Produto do DSN 
			// o NM_Unidade_Produto de usuário e a senha do banco.
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
		buff.append("UPDATE Unidades_Produtos SET NM_Unidade_Produto=?, Dt_Stamp=?, Usuario_Stamp=?, Dm_Stamp=? ");
		buff.append("WHERE OID_Unidade_Produto=?");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try 
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setString(1, getNM_Unidade_Produto());
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
			// passando como parâmetro o NM_Unidade_Produto do DSN 
			// o NM_Unidade_Produto de usuário e a senha do banco.
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
		buff.append("DELETE FROM Unidades_Produtos ");
		buff.append("WHERE OID_Unidade_Produto=?");
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
	
	public static final Unidade_ProdutoBean getByOID(int oid) 
		throws Exception 
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try 
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Unidade_Produto do DSN 
			// o NM_Unidade_Produto de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
		
		Unidade_ProdutoBean p = new Unidade_ProdutoBean();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Unidade_Produto, ");
			buff.append("	CD_Unidade_Produto, ");
			buff.append("	NM_Unidade_Produto ");
			buff.append("FROM Unidades_Produtos ");
			buff.append("WHERE OID_Unidade_Produto= ");
			buff.append(oid);
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = 
				stmt.executeQuery(buff.toString());
			
			while (cursor.next()) 
			{
				p.setOID(cursor.getInt(1));
				p.setCD_Unidade_Produto(cursor.getString(2));
				p.setNM_Unidade_Produto(cursor.getString(3));				
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
	
	public static final Unidade_ProdutoBean getByCD_Unidade_Produto(String CD_Unidade_Produto) 
		throws Exception 
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try 
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Unidade_Produto do DSN 
			// o NM_Unidade_Produto de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
		
		Unidade_ProdutoBean p = new Unidade_ProdutoBean();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Unidade_Produto, ");
			buff.append("	CD_Unidade_Produto, ");
			buff.append("	NM_Unidade_Produto ");
			buff.append("FROM Unidades_Produtos ");
			buff.append("WHERE CD_Unidade_Produto= '");
			buff.append(CD_Unidade_Produto);
			buff.append("'");
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = 
				stmt.executeQuery(buff.toString());
			
			while (cursor.next()) 
			{
				p.setOID(cursor.getInt(1));
				p.setCD_Unidade_Produto(cursor.getString(2));
				p.setNM_Unidade_Produto(cursor.getString(3));
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
	
	public static final List getByNM_Unidade_Produto(String NM_Unidade_Produto) 
		throws Exception 
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try 
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Unidade_Produto do DSN 
			// o NM_Unidade_Produto de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
		
		List Unidades_Produtos_Lista = new ArrayList();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Unidade_Produto, ");
			buff.append("	CD_Unidade_Produto, ");
			buff.append("	NM_Unidade_Produto ");
			buff.append("FROM Unidades_Produtos ");
			buff.append("WHERE NM_Unidade_Produto LIKE'");
			buff.append(NM_Unidade_Produto);
			buff.append("%'");
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = 
				stmt.executeQuery(buff.toString());
			
			while (cursor.next()) 
			{
				Unidade_ProdutoBean p = new Unidade_ProdutoBean();
				p.setOID(cursor.getInt(1));
				p.setCD_Unidade_Produto(cursor.getString(2));
				p.setNM_Unidade_Produto(cursor.getString(3));
				Unidades_Produtos_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return Unidades_Produtos_Lista;
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
		
		List Unidades_Produtos_Lista = new ArrayList();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Unidade_Produto, ");
			buff.append("	CD_Unidade_Produto, ");
			buff.append("	NM_Unidade_Produto ");
			buff.append("FROM Unidades_Produtos");
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = 
				stmt.executeQuery(buff.toString()); 
			
			while (cursor.next()) 
			{
				Unidade_ProdutoBean p = new Unidade_ProdutoBean(); 
				p.setOID(cursor.getInt(1));
				p.setCD_Unidade_Produto(cursor.getString(2));
				p.setNM_Unidade_Produto(cursor.getString(3));
				Unidades_Produtos_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)  
		{
			e.printStackTrace();
		}
		return Unidades_Produtos_Lista;
	}	
	
	public static void main(String args[]) 
		throws Exception 
	{
		Unidade_ProdutoBean pp = new Unidade_ProdutoBean();
		pp.setOID(1);
		pp.setCD_Unidade_Produto("LLL");
		pp.setNM_Unidade_Produto("Litro");
		pp.insert();
		
		Unidade_ProdutoBean p = getByOID(2);
		// // // System.out.println(p.getOID());
		// // // System.out.println(p.getCD_Unidade_Produto());
		// // // System.out.println(p.getNM_Unidade_Produto());
		
		
		
	}

	public String getDm_Pesagem() {
		return Dm_Pesagem;
	}

	public void setDm_Pesagem(String dm_Pesagem) {
		Dm_Pesagem = dm_Pesagem;
	}
}