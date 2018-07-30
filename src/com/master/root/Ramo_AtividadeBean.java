package com.master.root;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import auth.OracleConnection2;

public class Ramo_AtividadeBean
{
	private String CD_Ramo_Atividade;
	private String NM_Ramo_Atividade;
	private String Usuario_Stamp;
	private String Dt_Stamp;
	private String Dm_Stamp;
	private int oid;
  private String CD_Grupo_CFO;

	public Ramo_AtividadeBean() {}

	public String getCD_Ramo_Atividade()
	{
		return CD_Ramo_Atividade;
	}
	public void setCD_Ramo_Atividade(String CD_Ramo_Atividade)
	{
		this.CD_Ramo_Atividade = CD_Ramo_Atividade;
	}
	public String getNM_Ramo_Atividade()
	{
		return NM_Ramo_Atividade;
	}
	public void setNM_Ramo_Atividade(String NM_Ramo_Atividade)
	{
		this.NM_Ramo_Atividade = NM_Ramo_Atividade;
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
			// passando como parâmetro o NM_Ramo_Atividade do DSN
			// o NM_Ramo_Atividade de usuário e a senha do banco.
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
				"SELECT MAX(OID_Ramo_Atividade) FROM Ramos_Atividades");

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
		buff.append("INSERT INTO Ramos_Atividades (OID_Ramo_Atividade, CD_Ramo_Atividade, NM_Ramo_Atividade, Dt_Stamp, Usuario_Stamp, Dm_Stamp, CD_Grupo_CFO) ");
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
			pstmt.setString(2, getCD_Ramo_Atividade());
			pstmt.setString(3, getNM_Ramo_Atividade());
			pstmt.setString(4, getDt_Stamp());
			pstmt.setString(5, getUsuario_Stamp());
			pstmt.setString(6, getDm_Stamp());
			pstmt.setString(7, getCD_Grupo_CFO());

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
			// passando como parâmetro o NM_Ramo_Atividade do DSN
			// o NM_Ramo_Atividade de usuário e a senha do banco.
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
		buff.append("UPDATE Ramos_Atividades SET NM_Ramo_Atividade=?, Dt_Stamp=?, Usuario_Stamp=?, Dm_Stamp=?, CD_Grupo_CFO=? ");
		buff.append("WHERE OID_Ramo_Atividade=?");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setString(1, getNM_Ramo_Atividade());
			pstmt.setString(2, getDt_Stamp());
			pstmt.setString(3, getUsuario_Stamp());
			pstmt.setString(4, getDm_Stamp());
			pstmt.setString(5, getCD_Grupo_CFO());
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
			// passando como parâmetro o NM_Ramo_Atividade do DSN
			// o NM_Ramo_Atividade de usuário e a senha do banco.
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
		buff.append("DELETE FROM Ramos_Atividades ");
		buff.append("WHERE OID_Ramo_Atividade=?");
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

	public static final Ramo_AtividadeBean getByOID(int oid)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Ramo_Atividade do DSN
			// o NM_Ramo_Atividade de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		Ramo_AtividadeBean p = new Ramo_AtividadeBean();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Ramo_Atividade, ");
			buff.append("	CD_Ramo_Atividade, ");
			buff.append("	NM_Ramo_Atividade,");
			buff.append("	CD_Grupo_CFO ");
			buff.append("FROM Ramos_Atividades ");
			buff.append("WHERE OID_Ramo_Atividade= ");
			buff.append(oid);

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				p.setOID(cursor.getInt(1));
				p.setCD_Ramo_Atividade(cursor.getString(2));
				p.setNM_Ramo_Atividade(cursor.getString(3));
				p.setCD_Grupo_CFO(cursor.getString(4));
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

	public static final Ramo_AtividadeBean getByCD_Ramo_Atividade(String CD_Ramo_Atividade)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Ramo_Atividade do DSN
			// o NM_Ramo_Atividade de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		Ramo_AtividadeBean p = new Ramo_AtividadeBean();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Ramo_Atividade, ");
			buff.append("	CD_Ramo_Atividade, ");
			buff.append("	NM_Ramo_Atividade,");
			buff.append("	CD_Grupo_CFO ");
			buff.append("FROM Ramos_Atividades ");
			buff.append("WHERE CD_Ramo_Atividade= '");
			buff.append(CD_Ramo_Atividade);
			buff.append("'");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				p.setOID(cursor.getInt(1));
				p.setCD_Ramo_Atividade(cursor.getString(2));
				p.setNM_Ramo_Atividade(cursor.getString(3));
				p.setCD_Grupo_CFO(cursor.getString(4));

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

	public static final List getByNM_Ramo_Atividade(String NM_Ramo_Atividade)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Ramo_Atividade do DSN
			// o NM_Ramo_Atividade de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		List Ramos_Atividades_Lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Ramo_Atividade, ");
			buff.append("	CD_Ramo_Atividade, ");
			buff.append("	NM_Ramo_Atividade,");
			buff.append("	CD_Grupo_CFO ");
			buff.append("FROM Ramos_Atividades ");
			buff.append("WHERE NM_Ramo_Atividade LIKE'");
			buff.append(NM_Ramo_Atividade);
			buff.append("%'");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				Ramo_AtividadeBean p = new Ramo_AtividadeBean();
				p.setOID(cursor.getInt(1));
				p.setCD_Ramo_Atividade(cursor.getString(2));
				p.setNM_Ramo_Atividade(cursor.getString(3));
				p.setCD_Grupo_CFO(cursor.getString(4));

				Ramos_Atividades_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return Ramos_Atividades_Lista;
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

		List Ramos_Atividades_Lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Ramo_Atividade, ");
			buff.append("	CD_Ramo_Atividade, ");
			buff.append("	NM_Ramo_Atividade,");
			buff.append("	CD_Grupo_CFO ");
			buff.append("FROM Ramos_Atividades");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				Ramo_AtividadeBean p = new Ramo_AtividadeBean();
				p.setOID(cursor.getInt(1));
				p.setCD_Ramo_Atividade(cursor.getString(2));
				p.setNM_Ramo_Atividade(cursor.getString(3));
				p.setCD_Grupo_CFO(cursor.getString(4));

				Ramos_Atividades_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return Ramos_Atividades_Lista;
	}

	public static void main(String args[])
		throws Exception
	{
		Ramo_AtividadeBean pp = new Ramo_AtividadeBean();
		pp.setOID(2);
		pp.setCD_Ramo_Atividade("LLL");
		pp.setNM_Ramo_Atividade("Litro");
		pp.insert();

		Ramo_AtividadeBean p = getByOID(2);
		// //// System.out.println(p.getOID());
		// //// System.out.println(p.getCD_Ramo_Atividade());
		// //// System.out.println(p.getNM_Ramo_Atividade());



	}
  public void setCD_Grupo_CFO(String CD_Grupo_CFO) {
    this.CD_Grupo_CFO = CD_Grupo_CFO;
  }
  public String getCD_Grupo_CFO() {
    return CD_Grupo_CFO;
  }
}


