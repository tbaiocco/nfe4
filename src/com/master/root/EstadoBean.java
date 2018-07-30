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


public class EstadoBean
{
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
	private int oid_Regiao_Pais;
	private int oid_Pais;

	public EstadoBean()
	{
		NM_Estado="";
		CD_Estado="";
		NM_Regiao_Pais="";
		CD_Regiao_Pais="";
		NM_Pais="";
		CD_Pais="";
		Usuario_Stamp="";
		Dm_Stamp="";
		int oid=0;
		int oid_Regiao_Pais=0;
		int oid_Pais=0;
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
			// passando como parâmetro o NM_Estado do DSN
			// o NM_Estado de usuário e a senha do banco.
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
				"SELECT MAX(OID_Estado) FROM Estados");

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
		buff.append("INSERT INTO Estados (OID_Estado, OID_Regiao_Pais, CD_Estado, NM_Estado, Dt_Stamp, Usuario_Stamp, Dm_Stamp) ");
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
			pstmt.setInt(2, getOID_Regiao_Pais());
			pstmt.setString(3, getCD_Estado());
			pstmt.setString(4, getNM_Estado());
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
			// passando como parâmetro o NM_Estado do DSN
			// o NM_Estado de usuário e a senha do banco.
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
		buff.append("UPDATE Estados SET NM_Estado=?, Dt_Stamp=?, Usuario_Stamp=?, Dm_Stamp=? ");
		buff.append("WHERE OID_Estado=?");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setString(1, getNM_Estado());
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
			// passando como parâmetro o NM_Estado do DSN
			// o NM_Estado de usuário e a senha do banco.
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
		buff.append("DELETE FROM Estados ");
		buff.append("WHERE OID_Estado=?");
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

	public static final EstadoBean getByOID(int oid)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Estado do DSN
			// o NM_Estado de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		EstadoBean p = new EstadoBean();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Estado, ");
			buff.append("	Estados.NM_Estado, ");
			buff.append("	Estados.CD_Estado, ");
			buff.append("	Regioes_Paises.OID_Regiao_Pais, ");
			buff.append("	Regioes_Paises.NM_Regiao_Pais, ");
			buff.append("	Regioes_Paises.CD_Regiao_Pais, ");
			buff.append("	Paises.OID_Pais, ");
			buff.append("	Paises.NM_Pais, ");
			buff.append("	Paises.CD_Pais ");
			buff.append("	,Estados.nm_codigo_ibge ");
			buff.append(" FROM Estados, Regioes_Paises, Paises ");
			buff.append(" WHERE Estados.OID_Regiao_Pais = Regioes_Paises.OID_Regiao_Pais ");
			buff.append(" AND Regioes_Paises.OID_Pais = Paises.OID_Pais ");
			buff.append(" AND OID_Estado= ");
			buff.append(oid);

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				p.setOID(cursor.getInt(1));
				p.setNM_Estado(cursor.getString(2));
				p.setCD_Estado(cursor.getString(3));
				p.setOID_Regiao_Pais(cursor.getInt(4));
				p.setNM_Regiao_Pais(cursor.getString(5));
				p.setCD_Regiao_Pais(cursor.getString(6));
				p.setOID_Pais(cursor.getInt(7));
				p.setNM_Pais(cursor.getString(8));
				p.setCD_Pais(cursor.getString(9));
				p.setDm_Stamp(cursor.getString(10));
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

	public static final EstadoBean getByCD_Estado(String CD_Estado)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Estado do DSN
			// o NM_Estado de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		EstadoBean p = new EstadoBean();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Estado, ");
			buff.append("	Estados.NM_Estado, ");
			buff.append("	Estados.CD_Estado, ");
			buff.append("	Regioes_Paises.OID_Regiao_Pais, ");
			buff.append("	Regioes_Paises.NM_Regiao_Pais, ");
			buff.append("	Regioes_Paises.CD_Regiao_Pais, ");
			buff.append("	Paises.OID_Pais, ");
			buff.append("	Paises.NM_Pais, ");
			buff.append("	Paises.CD_Pais ");
			buff.append(" FROM Estados, Regioes_Paises, Paises ");
			buff.append(" WHERE Estados.OID_Regiao_Pais = Regioes_Paises.OID_Regiao_Pais ");
			buff.append(" AND Regioes_Paises.OID_Pais = Paises.OID_Pais ");
			buff.append(" AND Estados.CD_Estado= '");
			buff.append(CD_Estado);
			buff.append("'");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				p.setOID(cursor.getInt(1));
				p.setNM_Estado(cursor.getString(2));
				p.setCD_Estado(cursor.getString(3));
				p.setOID_Regiao_Pais(cursor.getInt(4));
				p.setNM_Regiao_Pais(cursor.getString(5));
				p.setCD_Regiao_Pais(cursor.getString(6));
				p.setOID_Pais(cursor.getInt(7));
				p.setNM_Pais(cursor.getString(8));
				p.setCD_Pais(cursor.getString(9));
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

	public static final List getByNM_Estado(String NM_Estado)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Estado do DSN
			// o NM_Estado de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		List Estado_Lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Estado, ");
			buff.append("	Estados.NM_Estado, ");
			buff.append("	Estados.CD_Estado, ");
			buff.append("	Regioes_Paises.OID_Regiao_Pais, ");
			buff.append("	Regioes_Paises.NM_Regiao_Pais, ");
			buff.append("	Regioes_Paises.CD_Regiao_Pais, ");
			buff.append("	Paises.OID_Pais, ");
			buff.append("	Paises.NM_Pais, ");
			buff.append("	Paises.CD_Pais ");
			buff.append(" FROM Estados, Regioes_Paises, Paises ");
			buff.append(" WHERE Estados.OID_Regiao_Pais = Regioes_Paises.OID_Regiao_Pais ");
			buff.append(" AND Regioes_Paises.OID_Pais = Paises.OID_Pais ");
			buff.append(" AND NM_Estado LIKE'");
			buff.append(NM_Estado);
			buff.append("%'");
			buff.append(" Order by Estados.NM_Estado ");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				EstadoBean p = new EstadoBean();
				p.setOID(cursor.getInt(1));
				p.setNM_Estado(cursor.getString(2));
				p.setCD_Estado(cursor.getString(3));
				p.setOID_Regiao_Pais(cursor.getInt(4));
				p.setNM_Regiao_Pais(cursor.getString(5));
				p.setCD_Regiao_Pais(cursor.getString(6));
				p.setOID_Pais(cursor.getInt(7));
				p.setNM_Pais(cursor.getString(8));
				p.setCD_Pais(cursor.getString(9));
				Estado_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return Estado_Lista;
	}

	public static final List getByEstado(String NM_Estado, String oid_Pais, String oid_Regiao_Pais)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Estado do DSN
			// o NM_Estado de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		List Estado_Lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Estado, ");
			buff.append("	Estados.NM_Estado, ");
			buff.append("	Estados.CD_Estado, ");
			buff.append("	Regioes_Paises.OID_Regiao_Pais, ");
			buff.append("	Regioes_Paises.NM_Regiao_Pais, ");
			buff.append("	Regioes_Paises.CD_Regiao_Pais, ");
			buff.append("	Paises.OID_Pais, ");
			buff.append("	Paises.NM_Pais, ");
			buff.append("	Paises.CD_Pais ");
			buff.append(" FROM Estados, Regioes_Paises, Paises ");
			buff.append(" WHERE Estados.OID_Regiao_Pais = Regioes_Paises.OID_Regiao_Pais ");
			buff.append(" AND Regioes_Paises.OID_Pais = Paises.OID_Pais ");
			buff.append(" AND NM_Estado LIKE'");
			buff.append(NM_Estado);
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
			buff.append(" Order by Estados.NM_Estado ");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				EstadoBean p = new EstadoBean();
				p.setOID(cursor.getInt(1));
				p.setNM_Estado(cursor.getString(2));
				p.setCD_Estado(cursor.getString(3));
				p.setOID_Regiao_Pais(cursor.getInt(4));
				p.setNM_Regiao_Pais(cursor.getString(5));
				p.setCD_Regiao_Pais(cursor.getString(6));
				p.setOID_Pais(cursor.getInt(7));
				p.setNM_Pais(cursor.getString(8));
				p.setCD_Pais(cursor.getString(9));
				Estado_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return Estado_Lista;
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

		List Estado_Lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Estado, ");
			buff.append("	Estados.NM_Estado, ");
			buff.append("	Estados.CD_Estado, ");
			buff.append("	Regioes_Paises.OID_Regiao_Pais, ");
			buff.append("	Regioes_Paises.NM_Regiao_Pais, ");
			buff.append("	Regioes_Paises.CD_Regiao_Pais, ");
			buff.append("	Paises.OID_Pais, ");
			buff.append("	Paises.NM_Pais, ");
			buff.append("	Paises.CD_Pais ");
			buff.append(" FROM Estados, Regioes_Paises, Paises ");
			buff.append(" WHERE Estados.OID_Regiao_Pais = Regioes_Paises.OID_Regiao_Pais ");
			buff.append(" AND Regioes_Paises.OID_Pais = Paises.OID_Pais ");
			buff.append(" WHERE Estados.OID_Pais = Paises.OID_Pais ");
			buff.append(" Order by Estados.NM_Estado ");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				EstadoBean p = new EstadoBean();
				p.setOID(cursor.getInt(1));
				p.setCD_Estado(cursor.getString(2));
				p.setNM_Estado(cursor.getString(3));
				p.setOID_Regiao_Pais(cursor.getInt(4));
				p.setNM_Regiao_Pais(cursor.getString(5));
				p.setCD_Regiao_Pais(cursor.getString(6));
				p.setOID_Pais(cursor.getInt(7));
				p.setNM_Pais(cursor.getString(8));
				p.setCD_Pais(cursor.getString(9));
				Estado_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return Estado_Lista;
	}

	public void importa_Estado() throws Exception
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

            BufferedReader buff = man.leArquivo("C:\\temp\\estado.txt");

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
                this.oid_Regiao_Pais = new Integer(st.nextToken()).intValue();
                this.CD_Estado = st.nextToken();
                this.NM_Estado = st.nextToken();
              }

              StringBuffer buff2 = new StringBuffer();
              buff2.append("INSERT INTO Estados (OID_Estado, OID_Regiao_Pais, CD_Estado, NM_Estado) ");
              buff2.append("VALUES (?,?,?,?)");
              try
              {
                PreparedStatement pstmt =
                        conn.prepareStatement(buff2.toString());
                pstmt.setInt(1, this.getOID());
                pstmt.setInt(2, this.getOID_Regiao_Pais());
                pstmt.setString(3, this.getCD_Estado());
                pstmt.setString(4, this.getNM_Estado());
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
		EstadoBean pp = new EstadoBean();
		pp.importa_Estado();

//		EstadoBean p = getByOID(1);
//		// //// System.out.println(p.getOID());
//		// //// System.out.println(p.getCD_Estado());
//		// //// System.out.println(p.getNM_Estado());

	}
	
	public static final EstadoBean getByOIDCidade(int oid_Cidade)
	throws Exception {
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try {
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Estado do DSN
			// o NM_Estado de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		EstadoBean p = new EstadoBean();
		try {
			String sqlBusca = 
				" SELECT Estados.OID_Estado, " +
				"	Estados.NM_Estado, " +
				"	Estados.CD_Estado, " +
				"	Regioes_Paises.OID_Regiao_Pais, " +
				"	Regioes_Paises.NM_Regiao_Pais, " +
				"	Regioes_Paises.CD_Regiao_Pais, " +
				"	Paises.OID_Pais, " +
				"	Paises.NM_Pais, " +
				"	Paises.CD_Pais " +
				" FROM Cidades, Regioes_Estados, Estados, Regioes_Paises, Paises " +
				" WHERE Cidades.OID_Cidade = '" +  oid_Cidade + "'" +
				"   AND Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado " +
				"   AND Regioes_Estados.OID_Estado = Estados.OID_Estado " +
				"   AND Estados.OID_Regiao_Pais = Regioes_Paises.OID_Regiao_Pais " +
				"   AND Regioes_Paises.OID_Pais = Paises.OID_Pais ";

			Statement stmt = conn.createStatement();
			ResultSet cursor = stmt.executeQuery(sqlBusca);

			if (cursor.next()) {
				p.setOID(cursor.getInt(1));
				p.setNM_Estado(cursor.getString(2));
				p.setCD_Estado(cursor.getString(3));
				p.setOID_Regiao_Pais(cursor.getInt(4));
				p.setNM_Regiao_Pais(cursor.getString(5));
				p.setCD_Regiao_Pais(cursor.getString(6));
				p.setOID_Pais(cursor.getInt(7));
				p.setNM_Pais(cursor.getString(8));
				p.setCD_Pais(cursor.getString(9));
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}
}