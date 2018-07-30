package com.master.root;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.master.util.FormataData;

import auth.OracleConnection2;



public class AIDOFBean
{
	private String CD_AIDOF;
	private String NM_Serie;
	private String NM_Tipo_Documento;
	private String CD_Tipo_Documento;
	private int NR_Inicial;
	private int NR_Final;
	private int NR_Proximo;
	private String Usuario_Stamp;
	private String Dt_Stamp;
	private String Dm_Stamp;
	private int oid;
	private int oid_Tipo_Documento;
  private String DM_Formulario;

	public AIDOFBean() {}

	public int getOID_Tipo_Documento()
	{
		return oid_Tipo_Documento;
	}
	public void setOID_Tipo_Documento(int n)
	{
		this.oid_Tipo_Documento = n;
	}
	public String getCD_AIDOF()
	{
		return CD_AIDOF;
	}
	public void setCD_AIDOF(String CD_AIDOF)
	{
		this.CD_AIDOF = CD_AIDOF;
	}
	public String getNM_Tipo_Documento()
	{
		return NM_Tipo_Documento;
	}
	public void setNM_Tipo_Documento(String NM_Tipo_Documento)
	{
		this.NM_Tipo_Documento = NM_Tipo_Documento;
	}
	public String getCD_Tipo_Documento()
	{
		return CD_Tipo_Documento;
	}
	public void setCD_Tipo_Documento(String CD_Tipo_Documento)
	{
		this.CD_Tipo_Documento = CD_Tipo_Documento;
	}
	public String getNM_Serie()
	{
		return NM_Serie;
	}
	public void setNM_Serie(String NM_Serie)
	{
		this.NM_Serie = NM_Serie;
	}
	public int getNR_Inicial()
	{
		return NR_Inicial;
	}
	public void setNR_Inicial(int NR_Inicial)
	{
		this.NR_Inicial = NR_Inicial;
	}
	public int getNR_Final()
	{
		return NR_Final;
	}
	public void setNR_Final(int NR_Final)
	{
		this.NR_Final = NR_Final;
	}
	public int getNR_Proximo()
	{
		return NR_Proximo;
	}
	public void setNR_Proximo(int NR_Proximo)
	{
		this.NR_Proximo = NR_Proximo;
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
			// passando como parâmetro o NR_Inicial do DSN
			// o NR_Inicial de usuário e a senha do banco.
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
				"SELECT MAX(OID_AIDOF) FROM AIDOF");

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
		buff.append("INSERT INTO AIDOF (OID_AIDOF, OID_Tipo_Documento, CD_AIDOF, NR_Inicial, NR_Final, NR_Proximo, NM_Serie, Dt_Stamp, Usuario_Stamp, Dm_Stamp, DM_Formulario) ");
		buff.append("VALUES (?,?,?,?,?,?,?,?,?,?,?)");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setInt(1, getOID());
			pstmt.setInt(2, getOID_Tipo_Documento());
			pstmt.setString(3, getCD_AIDOF());
			pstmt.setInt(4, getNR_Inicial());
			pstmt.setInt(5, getNR_Final());
			pstmt.setInt(6, getNR_Proximo());
			pstmt.setString(7, getNM_Serie());
			pstmt.setDate(8, FormataData.formataDataTB(getDt_Stamp()));
			pstmt.setString(9, getUsuario_Stamp());
			pstmt.setString(10, getDm_Stamp());
			pstmt.setString(11, getDM_Formulario());

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
			// passando como parâmetro o NR_Inicial do DSN
			// o NR_Inicial de usuário e a senha do banco.
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
		buff.append("UPDATE AIDOF SET CD_AIDOF=?, NR_Inicial=?, NR_Final=?, NR_Proximo=?, NM_Serie=?, Dt_Stamp=?, Usuario_Stamp=?, Dm_Stamp=? , Dm_Formulario=?");
		buff.append("WHERE OID_AIDOF=?");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setString(1, getCD_AIDOF());
			pstmt.setInt(2, getNR_Inicial());
			pstmt.setInt(3, getNR_Final());
			pstmt.setInt(4, getNR_Proximo());
			pstmt.setString(5, getNM_Serie());
			pstmt.setDate(6, FormataData.formataDataTB(getDt_Stamp()));
			pstmt.setString(7, getUsuario_Stamp());
			pstmt.setString(8, getDm_Stamp());
			pstmt.setString(9, getDM_Formulario());
			pstmt.setInt(10, getOID());
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
			// passando como parâmetro o NR_Inicial do DSN
			// o NR_Inicial de usuário e a senha do banco.
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
		buff.append("DELETE FROM AIDOF ");
		buff.append("WHERE OID_AIDOF=?");
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

	public static final AIDOFBean getByOID(int oid)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NR_Inicial do DSN
			// o NR_Inicial de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		AIDOFBean p = new AIDOFBean();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT                                 ");
			buff.append("   AIDOF.OID_AIDOF,                    ");
			buff.append("	AIDOF.OID_Tipo_Documento,           ");
			buff.append("	AIDOF.CD_AIDOF,                     ");
			buff.append("	AIDOF.NM_Serie,                     ");
			buff.append("	AIDOF.NR_Inicial,                   ");
			buff.append("	AIDOF.NR_Final,                     ");
			buff.append("	AIDOF.NR_Proximo,                   ");
			buff.append("	AIDOF.DM_Formulario,                ");
			buff.append("	Tipos_Documentos.NM_Tipo_Documento, ");
			buff.append("	Tipos_Documentos.CD_Tipo_Documento  ");
			buff.append(" FROM AIDOF, Tipos_Documentos          ");
			buff.append(" WHERE AIDOF.OID_Tipo_Documento = Tipos_Documentos.OID_Tipo_Documento ");
			buff.append("AND OID_AIDOF= ");
			buff.append(oid);

//			// System.out.println("AIDOFBean.getByOID() sql = "+buff.toString());
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				p.setOID(cursor.getInt(1));
				p.setOID_Tipo_Documento(cursor.getInt(2));
				p.setCD_AIDOF(cursor.getString(3));
				p.setNM_Serie(cursor.getString(4));
				p.setNR_Inicial(cursor.getInt(5));
				p.setNR_Final(cursor.getInt(6));
				p.setNR_Proximo(cursor.getInt(7));
				p.setDM_Formulario(cursor.getString(8));
				p.setNM_Tipo_Documento(cursor.getString(9));
				p.setCD_Tipo_Documento(cursor.getString(10));
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

	public static final List getByOID_Tipo_Documento(int OID_Tipo_Documento)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NR_Inicial do DSN
			// o NR_Inicial de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		List AIDOF_Lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT                                 ");
			buff.append("   AIDOF.OID_AIDOF,                    ");
			buff.append("	AIDOF.OID_Tipo_Documento,           ");
			buff.append("	AIDOF.CD_AIDOF,                     ");
			buff.append("	AIDOF.NM_Serie,                     ");
			buff.append("	AIDOF.NR_Inicial,                   ");
			buff.append("	AIDOF.NR_Final,                     ");
			buff.append("	AIDOF.NR_Proximo,                   ");
			buff.append("	Tipos_Documentos.NM_Tipo_Documento, ");
			buff.append("	Tipos_Documentos.CD_Tipo_Documento  ");
			buff.append(" FROM AIDOF, Tipos_Documentos          ");
			buff.append(" WHERE AIDOF.OID_Tipo_Documento = Tipos_Documentos.OID_Tipo_Documento ");
			buff.append("AND OID_Tipo_Documento =");
			buff.append(OID_Tipo_Documento);

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				AIDOFBean p = new AIDOFBean();
				p.setOID(cursor.getInt(1));
				p.setOID_Tipo_Documento(cursor.getInt(2));
				p.setCD_AIDOF(cursor.getString(3));
				p.setNM_Serie(cursor.getString(4));
				p.setNR_Inicial(cursor.getInt(5));
				p.setNR_Final(cursor.getInt(6));
				p.setNR_Proximo(cursor.getInt(7));
				p.setNM_Tipo_Documento(cursor.getString(8));
				p.setCD_Tipo_Documento(cursor.getString(9));
				AIDOF_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return AIDOF_Lista;
	}

	public static final AIDOFBean getByCD_AIDOF(String CD_AIDOF)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Serie do DSN
			// o NM_Serie de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		AIDOFBean p = new AIDOFBean();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT                                 ");
			buff.append("   AIDOF.OID_AIDOF,                    ");
			buff.append("	AIDOF.OID_Tipo_Documento,           ");
			buff.append("	AIDOF.CD_AIDOF,                     ");
			buff.append("	AIDOF.NM_Serie,                     ");
			buff.append("	AIDOF.NR_Inicial,                   ");
			buff.append("	AIDOF.NR_Final,                     ");
			buff.append("	AIDOF.NR_Proximo,                   ");
			buff.append("	AIDOF.DM_Formulario,                ");
			buff.append("	Tipos_Documentos.NM_Tipo_Documento, ");
			buff.append("	Tipos_Documentos.CD_Tipo_Documento  ");
			buff.append(" FROM AIDOF, Tipos_Documentos          ");
			buff.append(" WHERE AIDOF.OID_Tipo_Documento = Tipos_Documentos.OID_Tipo_Documento ");
			buff.append(" AND AIDOF.CD_AIDOF = '");
			buff.append(CD_AIDOF);
			buff.append("'");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				p.setOID(cursor.getInt(1));
				p.setOID_Tipo_Documento(cursor.getInt(2));
				p.setCD_AIDOF(cursor.getString(3));
				p.setNM_Serie(cursor.getString(4));
				p.setNR_Inicial(cursor.getInt(5));
				p.setNR_Final(cursor.getInt(6));
				p.setNR_Proximo(cursor.getInt(7));
				p.setDM_Formulario(cursor.getString(8));
				p.setNM_Tipo_Documento(cursor.getString(9));
				p.setCD_Tipo_Documento(cursor.getString(10));
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

	public static final List getByNM_Serie(String NM_Serie)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Serie do DSN
			// o NM_Serie de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		List AIDOF_Lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT                                 ");
			buff.append("   AIDOF.OID_AIDOF,                    ");
			buff.append("	AIDOF.OID_Tipo_Documento,           ");
			buff.append("	AIDOF.CD_AIDOF,                     ");
			buff.append("	AIDOF.NM_Serie,                     ");
			buff.append("	AIDOF.NR_Inicial,                   ");
			buff.append("	AIDOF.NR_Final,                     ");
			buff.append("	AIDOF.NR_Proximo,                   ");
			buff.append("	Tipos_Documentos.NM_Tipo_Documento, ");
			buff.append("	Tipos_Documentos.CD_Tipo_Documento  ");
			buff.append(" FROM AIDOF, Tipos_Documentos          ");
			buff.append(" WHERE AIDOF.OID_Tipo_Documento = Tipos_Documentos.OID_Tipo_Documento ");
			buff.append(" AND NM_Serie LIKE'");
			buff.append(NM_Serie);
			buff.append("%'");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				AIDOFBean p = new AIDOFBean();
				p.setOID(cursor.getInt(1));
				p.setOID_Tipo_Documento(cursor.getInt(2));
				p.setCD_AIDOF(cursor.getString(3));
				p.setNM_Serie(cursor.getString(4));
				p.setNR_Inicial(cursor.getInt(5));
				p.setNR_Final(cursor.getInt(6));
				p.setNR_Proximo(cursor.getInt(7));
				p.setNM_Tipo_Documento(cursor.getString(8));
				p.setCD_Tipo_Documento(cursor.getString(9));
				AIDOF_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return AIDOF_Lista;
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

		List AIDOF_Lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT                                 ");
			buff.append("   AIDOF.OID_AIDOF,                    ");
			buff.append("	AIDOF.OID_Tipo_Documento,           ");
			buff.append("	AIDOF.CD_AIDOF,                     ");
			buff.append("	AIDOF.NM_Serie,                     ");
			buff.append("	AIDOF.NR_Inicial,                   ");
			buff.append("	AIDOF.NR_Final,                     ");
			buff.append("	AIDOF.NR_Proximo,                   ");
			buff.append("	Tipos_Documentos.NM_Tipo_Documento, ");
			buff.append("	Tipos_Documentos.CD_Tipo_Documento  ");
			buff.append(" FROM AIDOF, Tipos_Documentos          ");
			buff.append(" WHERE AIDOF.OID_Tipo_Documento = Tipos_Documentos.OID_Tipo_Documento ");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				AIDOFBean p = new AIDOFBean();
				p.setOID(cursor.getInt(1));
				p.setOID_Tipo_Documento(cursor.getInt(2));
				p.setCD_AIDOF(cursor.getString(3));
				p.setNM_Serie(cursor.getString(4));
				p.setNR_Inicial(cursor.getInt(5));
				p.setNR_Final(cursor.getInt(6));
				p.setNR_Proximo(cursor.getInt(7));
				p.setNM_Tipo_Documento(cursor.getString(8));
				p.setCD_Tipo_Documento(cursor.getString(9));
				AIDOF_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return AIDOF_Lista;
	}

	public static void main(String args[])
		throws Exception
	{
		AIDOFBean pp = new AIDOFBean();
		pp.setOID(3);
		pp.setNR_Inicial(9000);
		pp.update();

		AIDOFBean  p = getByOID(3);
		// //// System.out.println(p.getOID());
		// //// System.out.println(p.getOID_Tipo_Documento());
		// //// System.out.println(p.getCD_AIDOF());
		// //// System.out.println(p.getNR_Inicial());

	}
  public void setDM_Formulario(String DM_Formulario) {
    this.DM_Formulario = DM_Formulario;
  }
  public String getDM_Formulario() {
    return DM_Formulario;
  }
}
