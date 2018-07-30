package com.master.root;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import auth.OracleConnection2;

public class Marca_VeiculoBean 
{
	private String CD_Marca_Veiculo;
	private String NM_Marca_Veiculo;
	private String Usuario_Stamp;
	private String Dt_Stamp;
	private String Dm_Stamp;
	private int oid;
	
	public Marca_VeiculoBean() {} 
	
	public String getCD_Marca_Veiculo() 
	{
		return CD_Marca_Veiculo;
	}
	public void setCD_Marca_Veiculo(String CD_Marca_Veiculo) 
	{
		this.CD_Marca_Veiculo = CD_Marca_Veiculo;
	}
	public String getNM_Marca_Veiculo() 
	{
		return NM_Marca_Veiculo;
	}
	public void setNM_Marca_Veiculo(String NM_Marca_Veiculo) 
	{
		this.NM_Marca_Veiculo = NM_Marca_Veiculo;
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
			// passando como parâmetro o NM_Marca_Veiculo do DSN 
			// o NM_Marca_Veiculo de usuário e a senha do banco.
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
				"SELECT MAX(OID_Marca_Veiculo) FROM Marcas_Veiculos");
			
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
		buff.append("INSERT INTO Marcas_Veiculos (OID_Marca_Veiculo, CD_Marca_Veiculo, NM_Marca_Veiculo, Dt_Stamp, Usuario_Stamp, Dm_Stamp) ");
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
			pstmt.setString(2, getCD_Marca_Veiculo());
			pstmt.setString(3, getNM_Marca_Veiculo());
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
			// passando como parâmetro o NM_Marca_Veiculo do DSN 
			// o NM_Marca_Veiculo de usuário e a senha do banco.
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
		buff.append("UPDATE Marcas_Veiculos SET NM_Marca_Veiculo=?, Dt_Stamp=?, Usuario_Stamp=?, Dm_Stamp=? ");
		buff.append("WHERE OID_Marca_Veiculo=?");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try 
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setString(1, getNM_Marca_Veiculo());
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
			// passando como parâmetro o NM_Marca_Veiculo do DSN 
			// o NM_Marca_Veiculo de usuário e a senha do banco.
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
		buff.append("DELETE FROM Marcas_Veiculos ");
		buff.append("WHERE OID_Marca_Veiculo=?");
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
	
	public static final Marca_VeiculoBean getByOID(int oid) 
		throws Exception 
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try 
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Marca_Veiculo do DSN 
			// o NM_Marca_Veiculo de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
		
		Marca_VeiculoBean p = new Marca_VeiculoBean();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Marca_Veiculo, ");
			buff.append("	CD_Marca_Veiculo, ");
			buff.append("	NM_Marca_Veiculo ");
			buff.append("FROM Marcas_Veiculos ");
			buff.append("WHERE OID_Marca_Veiculo= ");
			buff.append(oid);
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = 
				stmt.executeQuery(buff.toString());
			
			while (cursor.next()) 
			{
				p.setOID(cursor.getInt(1));
				p.setCD_Marca_Veiculo(cursor.getString(2));
				p.setNM_Marca_Veiculo(cursor.getString(3));				
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
	
	public static final Marca_VeiculoBean getByCD_Marca_Veiculo(String CD_Marca_Veiculo) 
		throws Exception 
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try 
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Marca_Veiculo do DSN 
			// o NM_Marca_Veiculo de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
		
		Marca_VeiculoBean p = new Marca_VeiculoBean();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Marca_Veiculo, ");
			buff.append("	CD_Marca_Veiculo, ");
			buff.append("	NM_Marca_Veiculo ");
			buff.append("FROM Marcas_Veiculos ");
			buff.append("WHERE CD_Marca_Veiculo= '");
			buff.append(CD_Marca_Veiculo);
			buff.append("'");
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = 
				stmt.executeQuery(buff.toString());
			
			while (cursor.next()) 
			{
				p.setOID(cursor.getInt(1));
				p.setCD_Marca_Veiculo(cursor.getString(2));
				p.setNM_Marca_Veiculo(cursor.getString(3));
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
	
	public static final List getByNM_Marca_Veiculo(String NM_Marca_Veiculo) 
		throws Exception 
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try 
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Marca_Veiculo do DSN 
			// o NM_Marca_Veiculo de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
		
		List Marcas_Veiculos_Lista = new ArrayList();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Marca_Veiculo, ");
			buff.append("	CD_Marca_Veiculo, ");
			buff.append("	NM_Marca_Veiculo ");
			buff.append("FROM Marcas_Veiculos ");
			buff.append("WHERE NM_Marca_Veiculo LIKE'");
			buff.append(NM_Marca_Veiculo);
			buff.append("%'");
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = 
				stmt.executeQuery(buff.toString());
			
			while (cursor.next()) 
			{
				Marca_VeiculoBean p = new Marca_VeiculoBean();
				p.setOID(cursor.getInt(1));
				p.setCD_Marca_Veiculo(cursor.getString(2));
				p.setNM_Marca_Veiculo(cursor.getString(3));
				Marcas_Veiculos_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return Marcas_Veiculos_Lista;
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
		
		List Marcas_Veiculos_Lista = new ArrayList();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Marca_Veiculo, ");
			buff.append("	CD_Marca_Veiculo, ");
			buff.append("	NM_Marca_Veiculo ");
			buff.append("FROM Marcas_Veiculos");
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = 
				stmt.executeQuery(buff.toString()); 
			
			while (cursor.next()) 
			{
				Marca_VeiculoBean p = new Marca_VeiculoBean(); 
				p.setOID(cursor.getInt(1));
				p.setCD_Marca_Veiculo(cursor.getString(2));
				p.setNM_Marca_Veiculo(cursor.getString(3));
				Marcas_Veiculos_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)  
		{
			e.printStackTrace();
		}
		return Marcas_Veiculos_Lista;
	}	
	
	public static void main(String args[]) 
		throws Exception 
	{
		Marca_VeiculoBean pp = new Marca_VeiculoBean();
		pp.setOID(2);
		pp.setCD_Marca_Veiculo("LLL");
		pp.setNM_Marca_Veiculo("Litro");
		pp.insert();
		
		Marca_VeiculoBean p = getByOID(2);
		// //// System.out.println(p.getOID());
		// //// System.out.println(p.getCD_Marca_Veiculo());
		// //// System.out.println(p.getNM_Marca_Veiculo());
		
		
		
	}
}
