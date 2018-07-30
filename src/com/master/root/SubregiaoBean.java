package com.master.root;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import auth.OracleConnection2;

public class SubregiaoBean
{
	private String CD_Subregiao;
	private String NM_Subregiao;
	private String Usuario_Stamp;
	private String Dt_Stamp;
	private String Dm_Stamp;
	private int oid;

	public SubregiaoBean() {}

	public String getCD_Subregiao()
	{
		return CD_Subregiao;
	}
	public void setCD_Subregiao(String CD_Subregiao)
	{
		this.CD_Subregiao = CD_Subregiao;
	}
	public String getNM_Subregiao()
	{
		return NM_Subregiao;
	}
	public void setNM_Subregiao(String NM_Subregiao)
	{
		this.NM_Subregiao = NM_Subregiao;
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
			// passando como parâmetro o NM_Subregiao do DSN
			// o NM_Subregiao de usuário e a senha do banco.
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
				"SELECT MAX(OID_Subregiao) FROM Subregioes");

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
		buff.append("INSERT INTO Subregioes (OID_Subregiao, CD_Subregiao, NM_Subregiao, Dt_Stamp, Usuario_Stamp, Dm_Stamp) ");
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
			pstmt.setString(2, getCD_Subregiao());
			pstmt.setString(3, getNM_Subregiao());
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
			// passando como parâmetro o NM_Subregiao do DSN
			// o NM_Subregiao de usuário e a senha do banco.
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
		buff.append("UPDATE Subregioes SET NM_Subregiao=?, Dt_Stamp=?, Usuario_Stamp=?, Dm_Stamp=? ");
		buff.append("WHERE OID_Subregiao=?");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setString(1, getNM_Subregiao());
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
			// passando como parâmetro o NM_Subregiao do DSN
			// o NM_Subregiao de usuário e a senha do banco.
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
		buff.append("DELETE FROM Subregioes ");
		buff.append("WHERE OID_Subregiao=?");
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

	public static final SubregiaoBean getByOID(int oid)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Subregiao do DSN
			// o NM_Subregiao de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		SubregiaoBean p = new SubregiaoBean();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Subregiao, ");
			buff.append("	CD_Subregiao, ");
			buff.append("	NM_Subregiao ");
			buff.append("FROM Subregioes ");
			buff.append("WHERE OID_Subregiao= ");
			buff.append(oid);

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
                            /// //// System.out.println("achou");
                                p.setOID(cursor.getInt(1));
				p.setCD_Subregiao(cursor.getString(2));
				p.setNM_Subregiao(cursor.getString(3));
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

	public static final SubregiaoBean getByCD_Subregiao(String CD_Subregiao)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Subregiao do DSN
			// o NM_Subregiao de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		SubregiaoBean p = new SubregiaoBean();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Subregiao, ");
			buff.append("	CD_Subregiao, ");
			buff.append("	NM_Subregiao ");
			buff.append("FROM Subregioes ");
			buff.append("WHERE CD_Subregiao= '");
			buff.append(CD_Subregiao);
			buff.append("'");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				p.setOID(cursor.getInt(1));
				p.setCD_Subregiao(cursor.getString(2));
				p.setNM_Subregiao(cursor.getString(3));
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

	public static final List getByNM_Subregiao(String NM_Subregiao)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Subregiao do DSN
			// o NM_Subregiao de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		List Subregioes_Lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Subregiao, ");
			buff.append("	CD_Subregiao, ");
			buff.append("	NM_Subregiao ");
			buff.append("FROM Subregioes ");
			buff.append("WHERE NM_Subregiao LIKE'");
			buff.append(NM_Subregiao);
			buff.append("%'");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				SubregiaoBean p = new SubregiaoBean();
				p.setOID(cursor.getInt(1));
				p.setCD_Subregiao(cursor.getString(2));
				p.setNM_Subregiao(cursor.getString(3));
				Subregioes_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return Subregioes_Lista;
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

		List Subregioes_Lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Subregiao, ");
			buff.append("	CD_Subregiao, ");
			buff.append("	NM_Subregiao ");
			buff.append("FROM Subregioes");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				SubregiaoBean p = new SubregiaoBean();
				p.setOID(cursor.getInt(1));
				p.setCD_Subregiao(cursor.getString(2));
				p.setNM_Subregiao(cursor.getString(3));
				Subregioes_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return Subregioes_Lista;
	}

	public static void main(String args[])
		throws Exception
	{
		SubregiaoBean p = getByOID(1);
		// //// System.out.println(p.getOID());
		// //// System.out.println(p.getCD_Subregiao());
		// //// System.out.println(p.getNM_Subregiao());

	}
}
