package com.master.root;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import auth.OracleConnection2;

public class PaisBean
{
	private String NM_Pais;
	private String CD_Pais;
	private String Usuario_Stamp;
	private String Dt_Stamp;
	private String Dm_Stamp;
	private int oid;
  private String NR_Permisso;
  private String NR_Pais;

	public PaisBean() {}

	public String getCD_Pais()
	{
		return CD_Pais;
	}
	public void setCD_Pais(String CD_Pais)
	{
		this.CD_Pais = CD_Pais;
	}
	public String getNM_Pais()
	{
		return NM_Pais;
	}
	public void setNM_Pais(String NM_Pais)
	{
		this.NM_Pais = NM_Pais;
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
			// passando como parâmetro o NM_Pais do DSN
			// o NM_Pais de usuário e a senha do banco.
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
				"SELECT MAX(OID_Pais) FROM Paises");

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
		buff.append("INSERT INTO Paises (OID_Pais, CD_Pais, NM_Pais, Dt_Stamp, Usuario_Stamp, Dm_Stamp, NR_Permisso, NR_Pais) ");
		buff.append("VALUES (?,?,?,?,?,?,?,?)");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setInt(1, getOID());
			pstmt.setString(2, getCD_Pais());
			pstmt.setString(3, getNM_Pais());
			pstmt.setString(4, getDt_Stamp());
			pstmt.setString(5, getUsuario_Stamp());
			pstmt.setString(6, getDm_Stamp());
			pstmt.setString(7, getNR_Permisso());
			pstmt.setString(8, getNR_Pais());
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
			// passando como parâmetro o NM_Pais do DSN
			// o NM_Pais de usuário e a senha do banco.
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
		buff.append("UPDATE Paises SET CD_Pais=?, Dt_Stamp=?, Usuario_Stamp=?, Dm_Stamp=?, NR_Permisso=?, NR_Pais=?, NM_Pais=? ");
		buff.append("WHERE OID_Pais=?");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		
		// System.out.println("PaisBean.update() sql = " + buff.toString());
		
		try{
			PreparedStatement pstmt = conn.prepareStatement(buff.toString());
			
			pstmt.setString(1, getCD_Pais());
			pstmt.setString(2, getDt_Stamp());
			pstmt.setString(3, getUsuario_Stamp());
			pstmt.setString(4, getDm_Stamp());
			pstmt.setString(5, getNR_Permisso());
			pstmt.setString(6, getNR_Pais());
			pstmt.setString(7, getNM_Pais());
			pstmt.setInt(8, getOID());
			
			// System.out.println(pstmt.toString());
			
			pstmt.executeUpdate();
		} 
		catch (Exception e){
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
			// passando como parâmetro o NM_Pais do DSN
			// o NM_Pais de usuário e a senha do banco.
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
		buff.append("DELETE FROM Paises ");
		buff.append("WHERE OID_Pais=?");
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

	public static final PaisBean getByOID(int oid)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Pais do DSN
			// o NM_Pais de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		PaisBean p = new PaisBean();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Pais, ");
			buff.append("	NM_Pais, ");
			buff.append("	CD_Pais, ");
			buff.append("	NR_Permisso, ");
			buff.append("	NR_Pais ");
			buff.append("FROM Paises ");
			buff.append("WHERE OID_Pais= ");
			buff.append(oid);

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				p.setOID(cursor.getInt(1));
				p.setNM_Pais(cursor.getString(2));
				p.setCD_Pais(cursor.getString(3));
				p.setNR_Permisso(cursor.getString(4));
				p.setNR_Pais(cursor.getString(5));
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

	public static final PaisBean getByCD_Pais(String CD_Pais)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Pais do DSN
			// o NM_Pais de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		PaisBean p = new PaisBean();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Pais, ");
			buff.append("	NM_Pais, ");
			buff.append("	CD_Pais, ");
			buff.append("	NR_Permisso, ");
			buff.append("	NR_Pais ");
			buff.append("FROM Paises ");
			buff.append("WHERE CD_Pais= '");
			buff.append(CD_Pais);
			buff.append("'");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				p.setOID(cursor.getInt(1));
				p.setNM_Pais(cursor.getString(2));
				p.setCD_Pais(cursor.getString(3));
				p.setNR_Permisso(cursor.getString(4));
				p.setNR_Pais(cursor.getString(5));
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

	public static final List getByNM_Pais(String NM_Pais)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Pais do DSN
			// o NM_Pais de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		List Pais_Lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Pais, ");
			buff.append("	NM_Pais, ");
			buff.append("	CD_Pais ");
			buff.append("FROM Paises ");
			buff.append("WHERE NM_Pais LIKE '");
			buff.append(NM_Pais);
			buff.append("%'");
			buff.append(" Order by NM_Pais ");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				PaisBean p = new PaisBean();
				p.setOID(cursor.getInt(1));
				p.setNM_Pais(cursor.getString(2));
				p.setCD_Pais(cursor.getString(3));
				Pais_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return Pais_Lista;
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

		List Pais_Lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Pais, ");
			buff.append("	CD_Pais, ");
			buff.append("	NM_Pais ");
			buff.append(" FROM PAISES ");
			buff.append(" Order by NM_Pais ");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				PaisBean p = new PaisBean();
				p.setOID(cursor.getInt(1));
				p.setCD_Pais(cursor.getString(2));
				p.setNM_Pais(cursor.getString(3));
				Pais_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return Pais_Lista;
	}

	public static void main(String args[])
		throws Exception
	{
		PaisBean p = getByOID(1);
		// //// System.out.println(p.getOID());
		// //// System.out.println(p.getCD_Pais());
		// //// System.out.println(p.getNM_Pais());

	}
  public void setNR_Permisso(String NR_Permisso) {
    this.NR_Permisso = NR_Permisso;
  }
  public String getNR_Permisso() {
    return NR_Permisso;
  }
  public void setNR_Pais(String NR_Pais) {
    this.NR_Pais = NR_Pais;
  }
  public String getNR_Pais() {
    return NR_Pais;
  }
}