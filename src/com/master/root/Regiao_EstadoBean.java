package com.master.root;
import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import auth.OracleConnection2;

import com.master.util.ManipulaArquivo;


public class Regiao_EstadoBean
{
	private String NM_Regiao_Estado;
	private String CD_Regiao_Estado;
	private String NM_Estado;
	private String CD_Estado;
	private String NM_Regiao_Pais;
	private String CD_Regiao_Pais;
	private String NM_Pais;
	private String CD_Pais;
	private String Usuario_Stamp;
	private String Dt_Stamp;
	private String Dm_Stamp;
	private int oid;
	private int oid_Estado;
	private int oid_Regiao_Pais;
	private int oid_Pais;
  private int oid_Subregiao;

	public Regiao_EstadoBean()
	{
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
		int oid_Estado=0;
		int oid_Regiao_Pais=0;
		int oid_Pais=0;
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
			// passando como parâmetro o NM_Regiao_Estado do DSN
			// o NM_Regiao_Estado de usuário e a senha do banco.
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
				"SELECT MAX(OID_Regiao_Estado) FROM Regioes_Estados");

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
		buff.append("INSERT INTO Regioes_Estados (OID_Regiao_Estado, OID_Estado, CD_Regiao_Estado, NM_Regiao_Estado, Dt_Stamp, Usuario_Stamp, Dm_Stamp, oid_subregiao) ");
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
			pstmt.setInt(2, getOID_Estado());
			pstmt.setString(3, getCD_Regiao_Estado());
			pstmt.setString(4, getNM_Regiao_Estado());
			pstmt.setString(5, getDt_Stamp());
			pstmt.setString(6, getUsuario_Stamp());
			pstmt.setString(7, getDm_Stamp());
			pstmt.setInt(8, getOid_Subregiao());

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
			// passando como parâmetro o NM_Regiao_Estado do DSN
			// o NM_Regiao_Estado de usuário e a senha do banco.
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
		buff.append("UPDATE Regioes_Estados SET NM_Regiao_Estado=?, Dt_Stamp=?, Usuario_Stamp=?, Dm_Stamp=?, oid_subregiao=? ");
		buff.append("WHERE OID_Regiao_Estado=?");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try
		{
			PreparedStatement pstmt =

                conn.prepareStatement(buff.toString());
			pstmt.setString(1, getNM_Regiao_Estado());
			pstmt.setString(2, getDt_Stamp());
			pstmt.setString(3, getUsuario_Stamp());
			pstmt.setString(4, getDm_Stamp());
			pstmt.setInt(5, getOid_Subregiao());
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
			// passando como parâmetro o NM_Regiao_Estado do DSN
			// o NM_Regiao_Estado de usuário e a senha do banco.
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
		buff.append("DELETE FROM Regioes_Estados ");
		buff.append("WHERE OID_Regiao_Estado=?");
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

	public static final Regiao_EstadoBean getByOID(int oid)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Regiao_Estado do DSN
			// o NM_Regiao_Estado de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		Regiao_EstadoBean p = new Regiao_EstadoBean();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Regiao_Estado, ");
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
			buff.append("	Paises.CD_Pais,");
			buff.append("	Regioes_Estados.oid_subregiao ");
			buff.append(" FROM Regioes_Estados, Estados, Regioes_Paises, Paises ");
			buff.append(" WHERE Regioes_Estados.OID_Estado = Estados.OID_Estado ");
			buff.append(" AND Estados.OID_Regiao_Pais = Regioes_Paises.OID_Regiao_Pais ");
			buff.append(" AND Regioes_Paises.OID_Pais = Paises.OID_Pais ");
			buff.append(" AND OID_Regiao_Estado= ");
			buff.append(oid);

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				p.setOID(cursor.getInt(1));
				p.setNM_Regiao_Estado(cursor.getString(2));
				p.setCD_Regiao_Estado(cursor.getString(3));
				p.setOID_Estado(cursor.getInt(4));
				p.setNM_Estado(cursor.getString(5));
				p.setCD_Estado(cursor.getString(6));
				p.setOID_Regiao_Pais(cursor.getInt(7));
				p.setNM_Regiao_Pais(cursor.getString(8));
				p.setCD_Regiao_Pais(cursor.getString(9));
				p.setOID_Pais(cursor.getInt(10));
				p.setNM_Pais(cursor.getString(11));
				p.setCD_Pais(cursor.getString(12));
				p.setOid_Subregiao(cursor.getInt(13));

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

	public static final Regiao_EstadoBean getByCD_Regiao_Estado(String CD_Regiao_Estado)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Regiao_Estado do DSN
			// o NM_Regiao_Estado de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		Regiao_EstadoBean p = new Regiao_EstadoBean();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Regiao_Estado, ");
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
			buff.append(" FROM Regioes_Estados, Estados, Regioes_Paises, Paises ");
			buff.append(" WHERE Regioes_Estados.OID_Estado = Estados.OID_Estado ");
			buff.append(" AND Estados.OID_Regiao_Pais = Regioes_Paises.OID_Regiao_Pais ");
			buff.append(" AND Regioes_Paises.OID_Pais = Paises.OID_Pais ");
			buff.append(" AND Regioes_Estados.CD_Regiao_Estado= '");
			buff.append(CD_Regiao_Estado);
			buff.append("'");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{

				p.setOID(cursor.getInt(1));
				p.setNM_Regiao_Estado(cursor.getString(2));
				p.setCD_Regiao_Estado(cursor.getString(3));
				p.setOID_Estado(cursor.getInt(4));
				p.setNM_Estado(cursor.getString(5));
				p.setCD_Estado(cursor.getString(6));
				p.setOID_Regiao_Pais(cursor.getInt(7));
				p.setNM_Regiao_Pais(cursor.getString(8));
				p.setCD_Regiao_Pais(cursor.getString(9));
				p.setOID_Pais(cursor.getInt(10));
				p.setNM_Pais(cursor.getString(11));
				p.setCD_Pais(cursor.getString(12));
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

	public static final List getByNM_Regiao_Estado(String NM_Regiao_Estado)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Regiao_Estado do DSN
			// o NM_Regiao_Estado de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		List Regiao_Estado_Lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Regiao_Estado, ");
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
			buff.append(" FROM Regioes_Estados, Estados, Regioes_Paises, Paises ");
			buff.append(" WHERE Regioes_Estados.OID_Estado = Estados.OID_Estado ");
			buff.append(" AND Estados.OID_Regiao_Pais = Regioes_Paises.OID_Regiao_Pais ");
			buff.append(" AND Regioes_Paises.OID_Pais = Paises.OID_Pais ");
			buff.append(" AND NM_Regiao_Estado LIKE'");
			buff.append(NM_Regiao_Estado);
			buff.append("%'");
			buff.append(" Order by NM_Regiao_Estado ");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				Regiao_EstadoBean p = new Regiao_EstadoBean();
				p.setOID(cursor.getInt(1));
				p.setNM_Regiao_Estado(cursor.getString(2));
				p.setCD_Regiao_Estado(cursor.getString(3));
				p.setOID_Estado(cursor.getInt(4));
				p.setNM_Estado(cursor.getString(5));
				p.setCD_Estado(cursor.getString(6));
				p.setOID_Regiao_Pais(cursor.getInt(7));
				p.setNM_Regiao_Pais(cursor.getString(8));
				p.setCD_Regiao_Pais(cursor.getString(9));
				p.setOID_Pais(cursor.getInt(10));
				p.setNM_Pais(cursor.getString(11));
				p.setCD_Pais(cursor.getString(12));
				Regiao_Estado_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return Regiao_Estado_Lista;
	}

	public static final List getByRegiao_Estado(String NM_Regiao_Estado, String oid_Pais, String oid_Regiao_Pais, String oid_Estado, String oid_Regiao_Estado)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Regiao_Estado do DSN
			// o NM_Regiao_Estado de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		List Regiao_Estado_Lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Regiao_Estado, ");
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
			buff.append(" FROM Regioes_Estados, Estados, Regioes_Paises, Paises ");
			buff.append(" WHERE Regioes_Estados.OID_Estado = Estados.OID_Estado ");
			buff.append(" AND Estados.OID_Regiao_Pais = Regioes_Paises.OID_Regiao_Pais ");
			buff.append(" AND Regioes_Paises.OID_Pais = Paises.OID_Pais ");
			buff.append(" AND NM_Regiao_Estado LIKE'");
			buff.append(NM_Regiao_Estado);
			buff.append("%'");
                        if (oid_Pais != null && !oid_Pais.equals("")  && !oid_Pais.equals("null")){
                          buff.append(" AND Paises.OID_Pais= ");
                          buff.append(oid_Pais);
                          buff.append("");
                        }
                        if (oid_Regiao_Pais != null && !oid_Regiao_Pais.equals("")  && !oid_Regiao_Pais.equals("null")){
                          buff.append(" AND Regioes_Paises.oid_Regiao_Pais= ");
                          buff.append(oid_Regiao_Pais);
                          buff.append("");
                        }
                        if (oid_Estado != null && !oid_Estado.equals("")  && !oid_Estado.equals("null")){
                          buff.append(" AND Estados.oid_Estado= ");
                          buff.append(oid_Estado);
                          buff.append("");
                        }
                        if (oid_Regiao_Estado != null && !oid_Regiao_Estado.equals("")  && !oid_Regiao_Estado.equals("null")){
                          buff.append(" AND Regioes_Estados.oid_Regiao_Estado= ");
                          buff.append(oid_Regiao_Estado);
                          buff.append("");
                        }

			buff.append(" Order by NM_Regiao_Estado ");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				Regiao_EstadoBean p = new Regiao_EstadoBean();
				p.setOID(cursor.getInt(1));
				p.setNM_Regiao_Estado(cursor.getString(2));
				p.setCD_Regiao_Estado(cursor.getString(3));
				p.setOID_Estado(cursor.getInt(4));
				p.setNM_Estado(cursor.getString(5));
				p.setCD_Estado(cursor.getString(6));
				p.setOID_Regiao_Pais(cursor.getInt(7));
				p.setNM_Regiao_Pais(cursor.getString(8));
				p.setCD_Regiao_Pais(cursor.getString(9));
				p.setOID_Pais(cursor.getInt(10));
				p.setNM_Pais(cursor.getString(11));
				p.setCD_Pais(cursor.getString(12));
				Regiao_Estado_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return Regiao_Estado_Lista;
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

		List Regiao_Estado_Lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Regiao_Estado, ");
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
			buff.append(" FROM Regioes_Estados, Estados, Regioes_Paises, Paises ");
			buff.append(" WHERE Regioes_Estados.OID_Estado = Estados.OID_Estado ");
			buff.append(" AND Estados.OID_Regiao_Pais = Regioes_Paises.OID_Regiao_Pais ");
			buff.append(" AND Regioes_Paises.OID_Pais = Paises.OID_Pais ");
			buff.append(" Order by NM_Regiao_Estado ");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				Regiao_EstadoBean p = new Regiao_EstadoBean();
				p.setOID(cursor.getInt(1));
				p.setNM_Regiao_Estado(cursor.getString(2));
				p.setCD_Regiao_Estado(cursor.getString(3));
				p.setOID_Estado(cursor.getInt(4));
				p.setNM_Estado(cursor.getString(5));
				p.setCD_Estado(cursor.getString(6));
				p.setOID_Regiao_Pais(cursor.getInt(7));
				p.setNM_Regiao_Pais(cursor.getString(8));
				p.setCD_Regiao_Pais(cursor.getString(9));
				p.setOID_Pais(cursor.getInt(10));
				p.setNM_Pais(cursor.getString(11));
				p.setCD_Pais(cursor.getString(12));
				Regiao_Estado_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return Regiao_Estado_Lista;
	}

	public void importa_Regiao_Estado() throws Exception
	{
          Connection conn = null;
          try
          {
            conn = OracleConnection2.getWEB();
            conn.setAutoCommit(false);
          } catch (Exception e)
          {
            e.printStackTrace();
            throw e;
          }
          try{
            ManipulaArquivo man = new ManipulaArquivo(";");

            BufferedReader buff = man.leArquivo("C:\\temp\\regiao_estado.txt");

            //Tod a esta rotina terá de ser feita para ler uma linha
            //e depois ler cada elemento
            //separado pelo delimitador
            StringTokenizer st = null;
            String a = null;
            String Pula_Campo = null;
            while ((a = buff.readLine()) != null){

              st = new StringTokenizer(a, ",");
              while (st.hasMoreTokens()) {
                Pula_Campo = st.nextToken();
                Pula_Campo = st.nextToken();
                this.oid = new Integer(st.nextToken()).intValue();
                this.oid_Estado = new Integer(st.nextToken()).intValue();
                this.CD_Regiao_Estado = st.nextToken();
                this.NM_Regiao_Estado = st.nextToken();
              }

              StringBuffer buff2 = new StringBuffer();
              buff2.append("INSERT INTO Regioes_Estados (OID_Regiao_Estado, OID_Estado, CD_Regiao_Estado, NM_Regiao_Estado) ");
              buff2.append("VALUES (?,?,?,?)");
              try
              {
                PreparedStatement pstmt =
                        conn.prepareStatement(buff2.toString());
                pstmt.setInt(1, this.getOID());
                pstmt.setInt(2, this.getOID_Estado());
                pstmt.setString(3, this.getCD_Regiao_Estado());
                pstmt.setString(4, this.getNM_Regiao_Estado());
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
              } catch (Exception e)
              {
                e.printStackTrace();
                throw e;
              }
            }
            conn.close();
          } catch (Exception e)
          {
            e.printStackTrace();
            throw e;
          }
	}

	public static void main(String args[])
		throws Exception
	{
                Regiao_EstadoBean pp = new Regiao_EstadoBean();
		pp.importa_Regiao_Estado();

//		Regiao_EstadoBean p = getByOID(1);
//		// //// System.out.println(p.getOID());
//		// //// System.out.println(p.getCD_Regiao_Estado());
//		// //// System.out.println(p.getNM_Regiao_Estado());

	}
  public void setOid_Subregiao(int oid_Subregiao) {
    this.oid_Subregiao = oid_Subregiao;
  }
  public int getOid_Subregiao() {
    return oid_Subregiao;
  }
}