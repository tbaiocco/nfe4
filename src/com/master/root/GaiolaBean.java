package com.master.root;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import auth.OracleConnection2;

public class GaiolaBean
{
	private String CD_Gaiola;
	private String NM_Gaiola;
	private String Usuario_Stamp;
	private String Dt_Stamp;
	private String Dm_Stamp;
	private int oid;
  private String DM_Situacao;

	public GaiolaBean() {}

	public String getCD_Gaiola()
	{
		return CD_Gaiola;
	}
	public void setCD_Gaiola(String CD_Gaiola)
	{
		this.CD_Gaiola = CD_Gaiola;
	}
	public String getNM_Gaiola()
	{
		return NM_Gaiola;
	}
	public void setNM_Gaiola(String NM_Gaiola)
	{
		this.NM_Gaiola = NM_Gaiola;
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
			// passando como parâmetro o NM_Gaiola do DSN
			// o NM_Gaiola de usuário e a senha do banco.
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
				"SELECT MAX(OID_Gaiola) FROM Gaiolas");

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
		buff.append("INSERT INTO Gaiolas (OID_Gaiola, CD_Gaiola, NM_Gaiola, Dt_Stamp, Usuario_Stamp, Dm_Stamp, DM_Situacao) ");
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
			pstmt.setString(2, getCD_Gaiola());
			pstmt.setString(3, getNM_Gaiola());
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
			// passando como parâmetro o NM_Gaiola do DSN
			// o NM_Gaiola de usuário e a senha do banco.
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
		buff.append("UPDATE Gaiolas SET NM_Gaiola=?, Dt_Stamp=?, Usuario_Stamp=?, Dm_Stamp=?, DM_Situacao=? ");
		buff.append("WHERE OID_Gaiola=?");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setString(1, getNM_Gaiola());
			pstmt.setString(2, getDt_Stamp());
			pstmt.setString(3, getUsuario_Stamp());
			pstmt.setString(4, getDm_Stamp());
			pstmt.setString(5, getDM_Situacao());
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
			// passando como parâmetro o NM_Gaiola do DSN
			// o NM_Gaiola de usuário e a senha do banco.
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
		buff.append("DELETE FROM Gaiolas ");
		buff.append("WHERE OID_Gaiola=?");
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

	public static final GaiolaBean getByOID(int oid)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Gaiola do DSN
			// o NM_Gaiola de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		GaiolaBean p = new GaiolaBean();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Gaiola, ");
			buff.append("	CD_Gaiola, ");
			buff.append("	NM_Gaiola, ");
			buff.append("	DM_Situacao ");
			buff.append("FROM Gaiolas ");
			buff.append("WHERE OID_Gaiola= ");
			buff.append(oid);

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				p.setOID(cursor.getInt(1));
				p.setCD_Gaiola(cursor.getString(2));
				p.setNM_Gaiola(cursor.getString(3));
				p.setDM_Situacao(cursor.getString(4));
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

	public static final GaiolaBean getByCD_Gaiola(String CD_Gaiola)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Gaiola do DSN
			// o NM_Gaiola de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		GaiolaBean p = new GaiolaBean();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Gaiola, ");
			buff.append("	CD_Gaiola, ");
			buff.append("	NM_Gaiola, ");
			buff.append("	DM_Situacao ");
			buff.append("FROM Gaiolas ");
			buff.append("WHERE CD_Gaiola= '");
			buff.append(CD_Gaiola);
			buff.append("'");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				p.setOID(cursor.getInt(1));
				p.setCD_Gaiola(cursor.getString(2));
				p.setNM_Gaiola(cursor.getString(3));
				p.setDM_Situacao(cursor.getString(4));
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

	public static final List getByNM_Gaiola(String NM_Gaiola)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Gaiola do DSN
			// o NM_Gaiola de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		List Gaiolas_Lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Gaiola, ");
			buff.append("	CD_Gaiola, ");
			buff.append("	NM_Gaiola, ");
			buff.append("	DM_Situacao ");
			buff.append("FROM Gaiolas ");
			buff.append("WHERE NM_Gaiola LIKE'");
			buff.append(NM_Gaiola);
			buff.append("%'");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				GaiolaBean p = new GaiolaBean();
				p.setOID(cursor.getInt(1));
				p.setCD_Gaiola(cursor.getString(2));
				p.setNM_Gaiola(cursor.getString(3));
				p.setDM_Situacao("LIBERADA");
				if ("U".equals(cursor.getString(4)))
					p.setDM_Situacao("USO");
				if ("M".equals(cursor.getString(4)))
					p.setDM_Situacao("MANUTENCAO");
				if ("D".equals(cursor.getString(4)))
					p.setDM_Situacao("DESATIVADA");
				Gaiolas_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return Gaiolas_Lista;
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

		List Gaiolas_Lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Gaiola, ");
			buff.append("	CD_Gaiola, ");
			buff.append("	NM_Gaiola, ");
			buff.append("	DM_Situacao ");
			buff.append("FROM Gaiolas");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				GaiolaBean p = new GaiolaBean();
				p.setOID(cursor.getInt(1));
				p.setCD_Gaiola(cursor.getString(2));
				p.setNM_Gaiola(cursor.getString(3));
				p.setDM_Situacao("LIBERADA");
				if ("U".equals(cursor.getString(4)))
					p.setDM_Situacao("USO");
				if ("M".equals(cursor.getString(4)))
					p.setDM_Situacao("MANUTENCAO");
				if ("D".equals(cursor.getString(4)))
					p.setDM_Situacao("DESATIVADA");
				
				Gaiolas_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return Gaiolas_Lista;
	}

	public static void main(String args[])
		throws Exception
	{
		GaiolaBean pp = new GaiolaBean();
		pp.setOID(2);
		pp.setCD_Gaiola("LLL");
		pp.setNM_Gaiola("Litro");
		pp.insert();

		GaiolaBean p = getByOID(2);
		// //// System.out.println(p.getOID());
		// //// System.out.println(p.getCD_Gaiola());
		// //// System.out.println(p.getNM_Gaiola());



	}
  public void setDM_Situacao(String DM_Situacao) {
    this.DM_Situacao = DM_Situacao;
  }
  public String getDM_Situacao() {
    return DM_Situacao;
  }
}