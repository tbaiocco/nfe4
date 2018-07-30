package com.master.root;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import auth.OracleConnection2;


public class Grupo_ServicoBean
{
	private String CD_Tipo_Servico;
	private String NM_Tipo_Servico;
	private String CD_Grupo_Servico;
	private String NM_Grupo_Servico;
	private String Usuario_Stamp;
	private String Dt_Stamp;
	private String Dm_Stamp;
	private int oid;
	private int oid_Grupo_Servico;
	private int OID_Tipo_Servico;
        private long NR_Kilometragem_Servico;

	public Grupo_ServicoBean() {}

	public String getCD_Grupo_Servico()
	{
		return CD_Grupo_Servico;
	}
	public void setCD_Grupo_Servico(String CD_Grupo_Servico)
	{
		this.CD_Grupo_Servico = CD_Grupo_Servico;
	}
	public String getNM_Tipo_Servico()
	{
		return NM_Tipo_Servico;
	}
	public void setNM_Tipo_Servico(String NM_Tipo_Servico)
	{
		this.NM_Tipo_Servico = NM_Tipo_Servico;
	}
	public String getCD_Tipo_Servico()
	{
		return CD_Tipo_Servico;
	}
	public void setCD_Tipo_Servico(String CD_Tipo_Servico)
	{
		this.CD_Tipo_Servico = CD_Tipo_Servico;
	}
	public long getNR_Kilometragem_Servico()
	{
		return NR_Kilometragem_Servico;
	}
	public void setNR_Kilometragem_Servico(long NR_Kilometragem_Servico)
	{
		this.NR_Kilometragem_Servico = NR_Kilometragem_Servico;
	}
	public String getNM_Grupo_Servico()
	{
		return NM_Grupo_Servico;
	}
	public void setNM_Grupo_Servico(String NM_Grupo_Servico)
	{
		this.NM_Grupo_Servico = NM_Grupo_Servico;
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
	public int getOID_Grupo_Servico()
	{
		return oid_Grupo_Servico;
	}
	public void setOID_Grupo_Servico(int n)
	{
		this.oid_Grupo_Servico = n;
	}
	public int getOID_Tipo_Servico()
	{
		return OID_Tipo_Servico;
	}
	public void setOID_Tipo_Servico(int n)
	{
		this.OID_Tipo_Servico = n;
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
			// passando como parâmetro o NM_Grupo_Servico do DSN
			// o NM_Grupo_Servico de usuário e a senha do banco.
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
				"SELECT MAX(oid_grupo_servico_preventivo) FROM Grupos_Servicos");

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
		buff.append(" INSERT INTO Grupos_Servicos (oid_grupo_servico_preventivo, OID_Grupo_Servico, OID_Tipo_Servico, NR_Kilometragem_Servico, Dt_Stamp, Usuario_Stamp, Dm_Stamp) ");
		buff.append(" VALUES (?,?,?,?,?,?,?)");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setInt(1, getOID());
			pstmt.setInt(2, getOID_Grupo_Servico());
			pstmt.setInt(3, getOID_Tipo_Servico());
			pstmt.setLong(4, getNR_Kilometragem_Servico());
			pstmt.setString(5, getDt_Stamp());
			pstmt.setString(6, getUsuario_Stamp());
			pstmt.setString(7, getDm_Stamp());
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
			// passando como parâmetro o NM_Grupo_Servico do DSN
			// o NM_Grupo_Servico de usuário e a senha do banco.
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
		buff.append("UPDATE Grupos_Servicos SET OID_Tipo_Servico=?, OID_Grupo_Servico=?, NR_Kilometragem_Servico=?, Dt_Stamp=?, Usuario_Stamp=?, Dm_Stamp=? ");
		buff.append("WHERE OID_Grupo_Servico=?");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setInt(1, getOID_Tipo_Servico());
			pstmt.setInt(2, getOID_Grupo_Servico());
			pstmt.setLong(3, getNR_Kilometragem_Servico());
			pstmt.setString(4, getDt_Stamp());
			pstmt.setString(5, getUsuario_Stamp());
			pstmt.setString(6, getDm_Stamp());
			pstmt.setInt(7, getOID());

                        // System.out.println(pstmt.toString());

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
			// passando como parâmetro o NM_Grupo_Servico do DSN
			// o NM_Grupo_Servico de usuário e a senha do banco.
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
		buff.append("DELETE FROM Grupos_Servicos ");
		buff.append("WHERE oid_grupo_servico_preventivo=?");
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

	public static final Grupo_ServicoBean getByOID(int oid)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Grupo_Servico do DSN
			// o NM_Grupo_Servico de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		Grupo_ServicoBean p = new Grupo_ServicoBean();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT ");
			buff.append("	Grupos_Servicos.OID_Grupo_Servico_Preventivo, ");
			buff.append("	Grupos_Servicos.OID_Grupo_Servico, ");
			buff.append("	Grupos_Servicos.OID_Tipo_Servico, ");
			buff.append("	Tipos_Servicos.CD_Tipo_Servico, ");
			buff.append("	Tipos_Servicos.NM_Tipo_Servico, ");
			buff.append("	Grupo_Tipos_Servicos.CD_Tipo_Servico, ");
			buff.append("	Grupo_Tipos_Servicos.NM_Tipo_Servico ");
			buff.append("FROM Grupos_Servicos, Tipos_Servicos Grupo_Tipos_Servicos, Tipos_Servicos ");
			buff.append("WHERE Grupos_Servicos.OID_Grupo_Servico = Grupo_Tipos_Servicos.OID_Tipo_Servico ");
			buff.append("AND Grupos_Servicos.OID_Tipo_Servico = Tipos_Servicos.OID_Tipo_Servico ");
			buff.append(" AND Grupos_Servicos.oid_grupo_servico_preventivo = ");
			buff.append(oid);


			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				p.setOID(cursor.getInt(1));
				p.setOID_Grupo_Servico(cursor.getInt(2));
				p.setOID_Tipo_Servico(cursor.getInt(3));
				p.setCD_Tipo_Servico(cursor.getString(4));
				p.setNM_Tipo_Servico(cursor.getString(5));
				p.setCD_Grupo_Servico(cursor.getString(6));
				p.setNM_Grupo_Servico(cursor.getString(7));

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

		List Grupo_Servico_Lista = new ArrayList();
		try
		{


			StringBuffer buff = new StringBuffer();
			buff.append("SELECT ");
			buff.append("	Grupos_Servicos.OID_Grupo_Servico_Preventivo, ");
			buff.append("	Grupos_Servicos.OID_Grupo_Servico, ");
			buff.append("	Grupos_Servicos.OID_Tipo_Servico, ");
			buff.append("	Tipos_Servicos.CD_Tipo_Servico, ");
			buff.append("	Tipos_Servicos.NM_Tipo_Servico, ");
			buff.append("	Grupo_Tipos_Servicos.CD_Tipo_Servico, ");
			buff.append("	Grupo_Tipos_Servicos.NM_Tipo_Servico, ");
			buff.append("	Grupos_Servicos.NR_Kilometragem_Servico ");
			buff.append("FROM Grupos_Servicos, Tipos_Servicos Grupo_Tipos_Servicos, Tipos_Servicos ");
			buff.append("WHERE Grupos_Servicos.OID_Grupo_Servico = Grupo_Tipos_Servicos.OID_Tipo_Servico ");
			buff.append("AND Grupos_Servicos.OID_Tipo_Servico = Tipos_Servicos.OID_Tipo_Servico ");
			buff.append(" ORDER BY  Grupo_Tipos_Servicos.CD_Tipo_Servico, Tipos_Servicos.NM_Tipo_Servico ");


			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				Grupo_ServicoBean p = new Grupo_ServicoBean();
				p.setOID(cursor.getInt(1));
				p.setOID_Grupo_Servico(cursor.getInt(2));
				p.setOID_Tipo_Servico(cursor.getInt(3));
				p.setCD_Tipo_Servico(cursor.getString(4));
				p.setNM_Tipo_Servico(cursor.getString(5));
				p.setCD_Grupo_Servico(cursor.getString(6));
				p.setNM_Grupo_Servico(cursor.getString(7));
				p.setNR_Kilometragem_Servico(cursor.getLong(8));
				Grupo_Servico_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return Grupo_Servico_Lista;
	}


}
