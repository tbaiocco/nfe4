package com.master.root;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import auth.OracleConnection2;

public class Regiao_CidadeBean
{
	private String CD_Regiao_Cidade;
	private String NM_Regiao_Cidade;
	private String CD_Cidade;
	private String NM_Cidade;
	private String CD_Regiao_Estado;
	private String NM_Regiao_Estado;
	private String CD_Estado;
	private String NM_Estado;
	private String CD_Regiao_Pais;
	private String NM_Regiao_Pais;
	private String CD_Pais;
	private String NM_Pais;
	private String Usuario_Stamp;
	private String Dt_Stamp;
	private String Dm_Stamp;
	private int oid;
	private int oid_Cidade;
	private int oid_Regiao_Estado;
	private int oid_Estado;
	private int oid_Regiao_Pais;
	private int oid_Pais;

	public Regiao_CidadeBean()
	{
		NM_Regiao_Cidade="";
		CD_Regiao_Cidade="";
		NM_Cidade="";
		CD_Cidade="";
		NM_Regiao_Estado="";
		CD_Regiao_Estado="";
		NM_Estado="";
		CD_Estado="";
		NM_Regiao_Pais="";
		CD_Regiao_Pais="";
		NM_Pais="";
		CD_Pais="";
		Usuario_Stamp="";
		Dm_Stamp="";
		int oid=0;
		int oid_Cidade=0;
		int oid_Estado=0;
		int oid_Regiao_Estado=0;
		int oid_Regiao_Pais=0;
		int oid_Pais=0;
	}

	public String getCD_Regiao_Cidade()
	{
		return CD_Regiao_Cidade;
	}
	public void setCD_Regiao_Cidade(String CD_Regiao_Cidade)
	{
		this.CD_Regiao_Cidade = CD_Regiao_Cidade;
	}
	public String getNM_Regiao_Cidade()
	{
		return NM_Regiao_Cidade;
	}
	public void setNM_Regiao_Cidade(String NM_Regiao_Cidade)
	{
		this.NM_Regiao_Cidade = NM_Regiao_Cidade;
	}
	public String getCD_Cidade()
	{
		return CD_Cidade;
	}
	public void setCD_Cidade(String CD_Cidade)
	{
		this.CD_Cidade = CD_Cidade;
	}
	public String getNM_Cidade()
	{
		return NM_Cidade;
	}
	public void setNM_Cidade(String NM_Cidade)
	{
		this.NM_Cidade = NM_Cidade;
	}
	public String getCD_Estado()
	{
		return CD_Estado;
	}
	public void setCD_Estado(String CD_Estado)
	{
		this.CD_Estado = CD_Estado;
	}
	public String getNM_Estado()
	{
		return NM_Estado;
	}
	public void setNM_Estado(String NM_Estado)
	{
		this.NM_Estado = NM_Estado;
	}
	public String getCD_Regiao_Estado()
	{
		return CD_Regiao_Estado;
	}
	public void setCD_Regiao_Estado(String CD_Regiao_Estado)
	{
		this.CD_Regiao_Estado = CD_Regiao_Estado;
	}
	public String getNM_Regiao_Estado()
	{
		return NM_Regiao_Estado;
	}
	public void setNM_Regiao_Estado(String NM_Regiao_Estado)
	{
		this.NM_Regiao_Estado = NM_Regiao_Estado;
	}
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
	public String getCD_Regiao_Pais()
	{
		return CD_Regiao_Pais;
	}
	public void setCD_Regiao_Pais(String CD_Regiao_Pais)
	{
		this.CD_Regiao_Pais = CD_Regiao_Pais;
	}
	public String getNM_Regiao_Pais()
	{
		return NM_Regiao_Pais;
	}
	public void setNM_Regiao_Pais(String NM_Regiao_Pais)
	{
		this.NM_Regiao_Pais = NM_Regiao_Pais;
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
	public int getOID_Cidade()
	{
		return oid_Cidade;
	}
	public void setOID_Cidade(int n)
	{
		this.oid_Cidade = n;
	}
	public int getOID_Regiao_Estado()
	{
		return oid_Regiao_Estado;
	}
	public void setOID_Regiao_Estado(int n)
	{
		this.oid_Regiao_Estado = n;
	}
	public int getOID_Estado()
	{
		return oid_Estado;
	}
	public void setOID_Estado(int n)
	{
		this.oid_Estado = n;
	}
	public int getOID_Pais()
	{
		return oid_Pais;
	}
	public void setOID_Pais(int n)
	{
		this.oid_Pais = n;
	}
	public int getOID_Regiao_Pais()
	{
		return oid_Regiao_Pais;
	}
	public void setOID_Regiao_Pais(int n)
	{
		this.oid_Regiao_Pais = n;
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
			// passando como parâmetro o NM_Regiao_Cidade do DSN
			// o NM_Regiao_Cidade de usuário e a senha do banco.
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
				"SELECT MAX(OID_Regiao_Cidade) FROM Regioes_Cidades");

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
		buff.append("INSERT INTO Regioes_Cidades (OID_Regiao_Cidade, OID_Cidade, CD_Regiao_Cidade, NM_Regiao_Cidade, Dt_Stamp, Usuario_Stamp, Dm_Stamp) ");
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
			pstmt.setInt(2, getOID_Cidade());
			pstmt.setString(3, getCD_Regiao_Cidade());
			pstmt.setString(4, getNM_Regiao_Cidade());
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
			// passando como parâmetro o NM_Regiao_Cidade do DSN
			// o NM_Regiao_Cidade de usuário e a senha do banco.
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
		buff.append("UPDATE Regioes_Cidades SET NM_Regiao_Cidade=?, Dt_Stamp=?, Usuario_Stamp=?, Dm_Stamp=? ");
		buff.append("WHERE OID_Regiao_Cidade=?");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setString(1, getNM_Regiao_Cidade());
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
			// passando como parâmetro o NM_Regiao_Cidade do DSN
			// o NM_Regiao_Cidade de usuário e a senha do banco.
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
		buff.append("DELETE FROM Regioes_Cidades ");
		buff.append("WHERE OID_Regiao_Cidade=?");
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

	public static final Regiao_CidadeBean getByOID(int oid)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Regiao_Cidade do DSN
			// o NM_Regiao_Cidade de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		Regiao_CidadeBean p = new Regiao_CidadeBean();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Regiao_Cidade, ");
			buff.append("	Regioes_Cidades.NM_Regiao_Cidade, ");
			buff.append("	Regioes_Cidades.CD_Regiao_Cidade, ");
			buff.append("	Cidades.OID_Cidade, ");
			buff.append("	Cidades.NM_Cidade, ");
			buff.append("	Cidades.CD_Cidade, ");
			buff.append("	Regioes_Estados.OID_Regiao_Estado, ");
			buff.append("	Regioes_Estados.NM_Regiao_Estado, ");
			buff.append("	Regioes_Estados.CD_Regiao_Estado, ");
			buff.append("	Estados.OID_Estado, ");
			buff.append("	Estados.NM_Estado, ");
			buff.append("	Estados.CD_Estado, ");
			buff.append("	Regioes_Paises.OID_Regiao_Pais, ");
			buff.append("	Regioes_Paises.NM_Regiao_Pais, ");
			buff.append("	Regioes_Paises.CD_Regiao_Pais, ");
			buff.append("	Paises.OID_Pais, ");
			buff.append("	Paises.NM_Pais, ");
			buff.append("	Paises.CD_Pais ");
			buff.append(" FROM Regioes_Cidades, Cidades, Regioes_Estados, Estados, Regioes_Paises, Paises ");
			buff.append(" WHERE Regioes_Cidades.OID_Cidade = Cidades.OID_Cidade ");
			buff.append(" AND Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado ");
			buff.append(" AND Regioes_Estados.OID_Estado = Estados.OID_Estado ");
			buff.append(" AND Estados.OID_Regiao_Pais = Regioes_Paises.OID_Regiao_Pais ");
			buff.append(" AND Regioes_Paises.OID_Pais = Paises.OID_Pais ");
			buff.append(" AND Regioes_Cidades.OID_Regiao_Cidade= ");
			buff.append(oid);

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				p.setOID(cursor.getInt(1));
				p.setNM_Regiao_Cidade(cursor.getString(2));
				p.setCD_Regiao_Cidade(cursor.getString(3));
				p.setOID_Cidade(cursor.getInt(4));
				p.setNM_Cidade(cursor.getString(5));
				p.setCD_Cidade(cursor.getString(6));
				p.setOID_Regiao_Estado(cursor.getInt(7));
				p.setNM_Regiao_Estado(cursor.getString(8));
				p.setCD_Regiao_Estado(cursor.getString(9));
				p.setOID_Estado(cursor.getInt(10));
				p.setNM_Estado(cursor.getString(11));
				p.setCD_Estado(cursor.getString(12));
				p.setOID_Regiao_Pais(cursor.getInt(13));
				p.setNM_Regiao_Pais(cursor.getString(14));
				p.setCD_Regiao_Pais(cursor.getString(15));
				p.setOID_Pais(cursor.getInt(16));
				p.setNM_Pais(cursor.getString(17));
				p.setCD_Pais(cursor.getString(18));
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

	public static final Regiao_CidadeBean getByCD_Regiao_Cidade(String CD_Regiao_Cidade)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Regiao_Cidade do DSN
			// o NM_Regiao_Cidade de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		Regiao_CidadeBean p = new Regiao_CidadeBean();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Regiao_Cidade, ");
			buff.append("	Regioes_Cidades.NM_Regiao_Cidade, ");
			buff.append("	Regioes_Cidades.CD_Regiao_Cidade, ");
			buff.append("	Cidades.OID_Cidade, ");
			buff.append("	Cidades.NM_Cidade, ");
			buff.append("	Cidades.CD_Cidade, ");
			buff.append("	Regioes_Estados.OID_Regiao_Estado, ");
			buff.append("	Regioes_Estados.NM_Regiao_Estado, ");
			buff.append("	Regioes_Estados.CD_Regiao_Estado, ");
			buff.append("	Estados.OID_Estado, ");
			buff.append("	Estados.NM_Estado, ");
			buff.append("	Estados.CD_Estado, ");
			buff.append("	Regioes_Paises.OID_Regiao_Pais, ");
			buff.append("	Regioes_Paises.NM_Regiao_Pais, ");
			buff.append("	Regioes_Paises.CD_Regiao_Pais, ");
			buff.append("	Paises.OID_Pais, ");
			buff.append("	Paises.NM_Pais, ");
			buff.append("	Paises.CD_Pais ");
			buff.append(" FROM Regioes_Cidades, Cidades, Regioes_Estados, Estados, Regioes_Paises, Paises ");
			buff.append(" WHERE Regioes_Cidades.OID_Cidade = Cidades.OID_Cidade ");
			buff.append(" AND Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado ");
			buff.append(" AND Regioes_Estados.OID_Estado = Estados.OID_Estado ");
			buff.append(" AND Estados.OID_Regiao_Pais = Regioes_Paises.OID_Regiao_Pais ");
			buff.append(" AND Regioes_Paises.OID_Pais = Paises.OID_Pais ");
			buff.append(" AND Regioes_Cidades.CD_Regiao_Cidade= '");
			buff.append(CD_Regiao_Cidade);
			buff.append("'");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				p.setOID(cursor.getInt(1));
				p.setNM_Regiao_Cidade(cursor.getString(2));
				p.setCD_Regiao_Cidade(cursor.getString(3));
				p.setOID_Cidade(cursor.getInt(4));
				p.setNM_Cidade(cursor.getString(5));
				p.setCD_Cidade(cursor.getString(6));
				p.setOID_Regiao_Estado(cursor.getInt(7));
				p.setNM_Regiao_Estado(cursor.getString(8));
				p.setCD_Regiao_Estado(cursor.getString(9));
				p.setOID_Estado(cursor.getInt(10));
				p.setNM_Estado(cursor.getString(11));
				p.setCD_Estado(cursor.getString(12));
				p.setOID_Regiao_Pais(cursor.getInt(13));
				p.setNM_Regiao_Pais(cursor.getString(14));
				p.setCD_Regiao_Pais(cursor.getString(15));
				p.setOID_Pais(cursor.getInt(16));
				p.setNM_Pais(cursor.getString(17));
				p.setCD_Pais(cursor.getString(18));
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

	public static final List getByNM_Regiao_Cidade(String NM_Regiao_Cidade)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Regiao_Cidade do DSN
			// o NM_Regiao_Cidade de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		List Regiao_Cidade_Lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Regiao_Cidade, ");
			buff.append("	Regioes_Cidades.NM_Regiao_Cidade, ");
			buff.append("	Regioes_Cidades.CD_Regiao_Cidade, ");
			buff.append("	Cidades.OID_Cidade, ");
			buff.append("	Cidades.NM_Cidade, ");
			buff.append("	Cidades.CD_Cidade, ");
			buff.append("	Regioes_Estados.OID_Regiao_Estado, ");
			buff.append("	Regioes_Estados.NM_Regiao_Estado, ");
			buff.append("	Regioes_Estados.CD_Regiao_Estado, ");
			buff.append("	Estados.OID_Estado, ");
			buff.append("	Estados.NM_Estado, ");
			buff.append("	Estados.CD_Estado, ");
			buff.append("	Regioes_Paises.OID_Regiao_Pais, ");
			buff.append("	Regioes_Paises.NM_Regiao_Pais, ");
			buff.append("	Regioes_Paises.CD_Regiao_Pais, ");
			buff.append("	Paises.OID_Pais, ");
			buff.append("	Paises.NM_Pais, ");
			buff.append("	Paises.CD_Pais ");
			buff.append(" FROM Regioes_Cidades, Cidades, Regioes_Estados, Estados, Regioes_Paises, Paises ");
			buff.append(" WHERE Regioes_Cidades.OID_Cidade = Cidades.OID_Cidade ");
			buff.append(" AND Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado ");
			buff.append(" AND Regioes_Estados.OID_Estado = Estados.OID_Estado ");
			buff.append(" AND Estados.OID_Regiao_Pais = Regioes_Paises.OID_Regiao_Pais ");
			buff.append(" AND Regioes_Paises.OID_Pais = Paises.OID_Pais ");
			buff.append(" AND Regioes_Cidades.NM_Regiao_Cidade LIKE'");
			buff.append(NM_Regiao_Cidade);
			buff.append("%'");
			buff.append(" Order by Regioes_Cidades.NM_Regiao_Cidade ");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				Regiao_CidadeBean p = new Regiao_CidadeBean();
				p.setOID(cursor.getInt(1));
				p.setNM_Regiao_Cidade(cursor.getString(2));
				p.setCD_Regiao_Cidade(cursor.getString(3));
				p.setOID_Cidade(cursor.getInt(4));
				p.setNM_Cidade(cursor.getString(5));
				p.setCD_Cidade(cursor.getString(6));
				p.setOID_Regiao_Estado(cursor.getInt(7));
				p.setNM_Regiao_Estado(cursor.getString(8));
				p.setCD_Regiao_Estado(cursor.getString(9));
				p.setOID_Estado(cursor.getInt(10));
				p.setNM_Estado(cursor.getString(11));
				p.setCD_Estado(cursor.getString(12));
				p.setOID_Regiao_Pais(cursor.getInt(13));
				p.setNM_Regiao_Pais(cursor.getString(14));
				p.setCD_Regiao_Pais(cursor.getString(15));
				p.setOID_Pais(cursor.getInt(16));
				p.setNM_Pais(cursor.getString(17));
				p.setCD_Pais(cursor.getString(18));
				Regiao_Cidade_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return Regiao_Cidade_Lista;
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

		List Regiao_Cidade_Lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Regiao_Cidade, ");
			buff.append("	Regioes_Cidades.NM_Regiao_Cidade, ");
			buff.append("	Regioes_Cidades.CD_Regiao_Cidade, ");
			buff.append("	Cidades.OID_Cidade, ");
			buff.append("	Cidades.NM_Cidade, ");
			buff.append("	Cidades.CD_Cidade, ");
			buff.append("	Regioes_Estados.OID_Regiao_Estado, ");
			buff.append("	Regioes_Estados.NM_Regiao_Estado, ");
			buff.append("	Regioes_Estados.CD_Regiao_Estado, ");
			buff.append("	Estados.OID_Estado, ");
			buff.append("	Estados.NM_Estado, ");
			buff.append("	Estados.CD_Estado, ");
			buff.append("	Regioes_Paises.OID_Regiao_Pais, ");
			buff.append("	Regioes_Paises.NM_Regiao_Pais, ");
			buff.append("	Regioes_Paises.CD_Regiao_Pais, ");
			buff.append("	Paises.OID_Pais, ");
			buff.append("	Paises.NM_Pais, ");
			buff.append("	Paises.CD_Pais ");
			buff.append(" FROM Regioes_Cidades, Cidades, Regioes_Estados, Estados, Regioes_Paises, Paises ");
			buff.append(" WHERE Regioes_Cidades.OID_Cidade = Cidades.OID_Cidade ");
			buff.append(" AND Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado ");
			buff.append(" AND Regioes_Estados.OID_Estado = Estados.OID_Estado ");
			buff.append(" AND Estados.OID_Regiao_Pais = Regioes_Paises.OID_Regiao_Pais ");
			buff.append(" AND Regioes_Paises.OID_Pais = Paises.OID_Pais ");
			buff.append(" Order by Regioes_Cidades.NM_Regiao_Cidade ");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				Regiao_CidadeBean p = new Regiao_CidadeBean();
				p.setOID(cursor.getInt(1));
				p.setNM_Regiao_Cidade(cursor.getString(2));
				p.setCD_Regiao_Cidade(cursor.getString(3));
				p.setOID_Cidade(cursor.getInt(4));
				p.setNM_Cidade(cursor.getString(5));
				p.setCD_Cidade(cursor.getString(6));
				p.setOID_Regiao_Estado(cursor.getInt(7));
				p.setNM_Regiao_Estado(cursor.getString(8));
				p.setCD_Regiao_Estado(cursor.getString(9));
				p.setOID_Estado(cursor.getInt(10));
				p.setNM_Estado(cursor.getString(11));
				p.setCD_Estado(cursor.getString(12));
				p.setOID_Regiao_Pais(cursor.getInt(13));
				p.setNM_Regiao_Pais(cursor.getString(14));
				p.setCD_Regiao_Pais(cursor.getString(15));
				p.setOID_Pais(cursor.getInt(16));
				p.setNM_Pais(cursor.getString(17));
				p.setCD_Pais(cursor.getString(18));
				Regiao_Cidade_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return Regiao_Cidade_Lista;
	}

	public static void main(String args[])
		throws Exception
	{
		Regiao_CidadeBean p = getByOID(1);
		// //// System.out.println(p.getOID());
		// //// System.out.println(p.getCD_Regiao_Cidade());
		// //// System.out.println(p.getNM_Regiao_Cidade());

	}
}