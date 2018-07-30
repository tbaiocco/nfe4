package com.master.root;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import auth.OracleConnection2;

public class SeguradoraBean
{
	private String CD_Seguradora;
	private String NM_Seguradora;
	private String Usuario_Stamp;
	private String Dt_Stamp;
	private String Dm_Stamp;
	private int oid;
  private String dm_Padrao_EDI;

	public SeguradoraBean() {}

	public String getCD_Seguradora()
	{
		return CD_Seguradora;
	}
	public void setCD_Seguradora(String CD_Seguradora)
	{
		this.CD_Seguradora = CD_Seguradora;
	}
	public String getNM_Seguradora()
	{
		return NM_Seguradora;
	}
	public void setNM_Seguradora(String NM_Seguradora)
	{
		this.NM_Seguradora = NM_Seguradora;
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
			// passando como parâmetro o NM_Seguradora do DSN
			// o NM_Seguradora de usuário e a senha do banco.
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
				"SELECT MAX(OID_Seguradora) FROM Seguradoras");

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
		buff.append("INSERT INTO Seguradoras (OID_Seguradora, CD_Seguradora, NM_Seguradora, Dt_Stamp, Usuario_Stamp, Dm_Stamp, DM_Padrao_EDI) ");
		buff.append("VALUES (?,?,?,?,?,?,?)");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setInt(1, getOID());
			pstmt.setString(2, getCD_Seguradora());
			pstmt.setString(3, getNM_Seguradora());
			pstmt.setString(4, getDt_Stamp());
			pstmt.setString(5, getUsuario_Stamp());
			pstmt.setString(6, getDm_Stamp());
			pstmt.setString(7, "N");
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
			// passando como parâmetro o NM_Seguradora do DSN
			// o NM_Seguradora de usuário e a senha do banco.
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
		buff.append("UPDATE Seguradoras SET NM_Seguradora=?, Dt_Stamp=?, Usuario_Stamp=?, Dm_Stamp=?, DM_Padrao_EDI=? ");
		buff.append("WHERE OID_Seguradora=?");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setString(1, getNM_Seguradora());
			pstmt.setString(2, getDt_Stamp());
			pstmt.setString(3, getUsuario_Stamp());
			pstmt.setString(4, getDm_Stamp());
			pstmt.setString(5, getDm_Padrao_EDI());
			pstmt.setInt(6, getOID());
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
			// passando como parâmetro o NM_Seguradora do DSN
			// o NM_Seguradora de usuário e a senha do banco.
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
		buff.append("DELETE FROM Seguradoras ");
		buff.append("WHERE OID_Seguradora=?");
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

	public static final SeguradoraBean getByOID(int oid)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Seguradora do DSN
			// o NM_Seguradora de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		SeguradoraBean p = new SeguradoraBean();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Seguradora, ");
			buff.append("	CD_Seguradora, ");
			buff.append("	NM_Seguradora, ");
			buff.append("	DM_Padrao_EDI ");
			buff.append("FROM Seguradoras ");
			buff.append("WHERE OID_Seguradora= ");
			buff.append(oid);

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				p.setOID(cursor.getInt(1));
				p.setCD_Seguradora(cursor.getString(2));
				p.setNM_Seguradora(cursor.getString(3));
				p.setDm_Padrao_EDI(cursor.getString(4));
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

	public static final SeguradoraBean getByCD_Seguradora(String CD_Seguradora)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Seguradora do DSN
			// o NM_Seguradora de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		SeguradoraBean p = new SeguradoraBean();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Seguradora, ");
			buff.append("	CD_Seguradora, ");
			buff.append("	NM_Seguradora, ");
			buff.append("	DM_Padrao_EDI ");
			buff.append("FROM Seguradoras ");
			buff.append("WHERE CD_Seguradora= '");
			buff.append(CD_Seguradora);
			buff.append("'");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				p.setOID(cursor.getInt(1));
				p.setCD_Seguradora(cursor.getString(2));
				p.setNM_Seguradora(cursor.getString(3));
				p.setDm_Padrao_EDI(cursor.getString(4));
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

	public static final List getByNM_Seguradora(String NM_Seguradora)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Seguradora do DSN
			// o NM_Seguradora de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		List Seguradoras_Lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Seguradora, ");
			buff.append("	CD_Seguradora, ");
			buff.append("	NM_Seguradora ");
			buff.append("FROM Seguradoras ");
			buff.append("WHERE NM_Seguradora LIKE'");
			buff.append(NM_Seguradora);
			buff.append("%'");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				SeguradoraBean p = new SeguradoraBean();
				p.setOID(cursor.getInt(1));
				p.setCD_Seguradora(cursor.getString(2));
				p.setNM_Seguradora(cursor.getString(3));
				Seguradoras_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return Seguradoras_Lista;
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

		List Seguradoras_Lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Seguradora, ");
			buff.append("	CD_Seguradora, ");
			buff.append("	NM_Seguradora ");
			buff.append("FROM Seguradoras");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				SeguradoraBean p = new SeguradoraBean();
				p.setOID(cursor.getInt(1));
				p.setCD_Seguradora(cursor.getString(2));
				p.setNM_Seguradora(cursor.getString(3));
				Seguradoras_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return Seguradoras_Lista;
	}

	public static void main(String args[])
		throws Exception
	{
		SeguradoraBean pp = new SeguradoraBean();
		pp.setOID(2);
		pp.setCD_Seguradora("LLL");
		pp.setNM_Seguradora("Litro");
		pp.insert();

		SeguradoraBean p = getByOID(2);
		// //// System.out.println(p.getOID());
		// //// System.out.println(p.getCD_Seguradora());
		// //// System.out.println(p.getNM_Seguradora());



	}
  public void setDm_Padrao_EDI(String dm_Padrao_EDI) {
    this.dm_Padrao_EDI = dm_Padrao_EDI;
  }
  public String getDm_Padrao_EDI() {
    return dm_Padrao_EDI;
  }
}