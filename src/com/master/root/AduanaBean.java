package com.master.root;

import java.sql.*;
import java.util.*;
import auth.*;

public class AduanaBean{
    
	private String CD_Aduana;
	private String NM_Aduana;
	private String Usuario_Stamp;
	private String Dt_Stamp;
	private String Dm_Stamp;
	private int oid;
    private String NR_Aduana;
    private String oid_Pessoa;

    public int getOid() {
        return oid;
    }
    public void setOid(int oid) {
        this.oid = oid;
    }
    public String getOid_Pessoa() {
        return oid_Pessoa;
    }
    public void setOid_Pessoa(String oid_Pessoa) {
        this.oid_Pessoa = oid_Pessoa;
    }
	public AduanaBean() {}

	public String getCD_Aduana()
	{
		return CD_Aduana;
	}
	public void setCD_Aduana(String CD_Aduana)
	{
		this.CD_Aduana = CD_Aduana;
	}
	public String getNM_Aduana()
	{
		return NM_Aduana;
	}
	public void setNM_Aduana(String NM_Aduana)
	{
		this.NM_Aduana = NM_Aduana;
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
			// passando como parâmetro o NM_Aduana do DSN
			// o NM_Aduana de usuário e a senha do banco.
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
				"SELECT MAX(OID_Aduana) FROM Aduanas");

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
		buff.append("INSERT INTO Aduanas (OID_Aduana, CD_Aduana, NM_Aduana, Dt_Stamp, Usuario_Stamp, Dm_Stamp, NR_Aduana) ");
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
			pstmt.setString(2, getCD_Aduana());
			pstmt.setString(3, getNM_Aduana());
			pstmt.setString(4, getDt_Stamp());
			pstmt.setString(5, getUsuario_Stamp());
			pstmt.setString(6, getDm_Stamp());
			pstmt.setString(7, getNR_Aduana());

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
			// passando como parâmetro o NM_Aduana do DSN
			// o NM_Aduana de usuário e a senha do banco.
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
		buff.append("UPDATE Aduanas SET NM_Aduana=?, Dt_Stamp=?, Usuario_Stamp=?, Dm_Stamp=?, NR_Aduana=? ");
		buff.append("WHERE OID_Aduana=?");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setString(1, getNM_Aduana());
			pstmt.setString(2, getDt_Stamp());
			pstmt.setString(3, getUsuario_Stamp());
			pstmt.setString(4, getDm_Stamp());
			pstmt.setString(5, getNR_Aduana());

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
			// passando como parâmetro o NM_Aduana do DSN
			// o NM_Aduana de usuário e a senha do banco.
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
		buff.append("DELETE FROM Aduanas ");
		buff.append("WHERE OID_Aduana=?");
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

	public static final AduanaBean getByOID(int oid)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Aduana do DSN
			// o NM_Aduana de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		AduanaBean p = new AduanaBean();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Aduana, ");
			buff.append("	CD_Aduana, ");
			buff.append("	NM_Aduana, ");
			buff.append("	NR_Aduana ");
			buff.append("FROM Aduanas ");
			buff.append("WHERE OID_Aduana= ");
			buff.append(oid);

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
                            /// //// System.out.println("achou");
                                p.setOID(cursor.getInt(1));
				p.setCD_Aduana(cursor.getString(2));
				p.setNM_Aduana(cursor.getString(3));
				p.setNR_Aduana(cursor.getString(4));
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

	public static final AduanaBean getByCD_Aduana(String CD_Aduana)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Aduana do DSN
			// o NM_Aduana de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		AduanaBean p = new AduanaBean();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Aduana, ");
			buff.append("	CD_Aduana, ");
			buff.append("	NM_Aduana, ");
			buff.append("	NR_Aduana ");
			buff.append("FROM Aduanas ");
			buff.append("WHERE CD_Aduana= '");
			buff.append(CD_Aduana);
			buff.append("'");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				p.setOID(cursor.getInt(1));
				p.setCD_Aduana(cursor.getString(2));
				p.setNM_Aduana(cursor.getString(3));
				p.setNR_Aduana(cursor.getString(4));
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

	public static final List getByNM_Aduana(String NM_Aduana)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Aduana do DSN
			// o NM_Aduana de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		List Aduanas_Lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Aduana, ");
			buff.append("	CD_Aduana, ");
			buff.append("	NM_Aduana, ");
			buff.append("	NR_Aduana ");
			buff.append("FROM Aduanas ");
			buff.append("WHERE NM_Aduana LIKE'");
			buff.append(NM_Aduana);
			buff.append("%'");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				AduanaBean p = new AduanaBean();
				p.setOID(cursor.getInt(1));
				p.setCD_Aduana(cursor.getString(2));
				p.setNM_Aduana(cursor.getString(3));
				p.setNR_Aduana(cursor.getString(4));
				Aduanas_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return Aduanas_Lista;
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

		List Aduanas_Lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Aduana, ");
			buff.append("	CD_Aduana, ");
			buff.append("	NM_Aduana, ");
			buff.append("	NR_Aduana ");
			buff.append("FROM Aduanas");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				AduanaBean p = new AduanaBean();
				p.setOID(cursor.getInt(1));
				p.setCD_Aduana(cursor.getString(2));
				p.setNM_Aduana(cursor.getString(3));
				p.setNR_Aduana(cursor.getString(4));

				Aduanas_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return Aduanas_Lista;
	}

	public static void main(String args[])
		throws Exception
	{
		AduanaBean p = getByOID(1);
		// //// System.out.println(p.getOID());
		// //// System.out.println(p.getCD_Aduana());
		// //// System.out.println(p.getNM_Aduana());

	}
  public void setNR_Aduana(String NR_Aduana) {
    this.NR_Aduana = NR_Aduana;
  }
  public String getNR_Aduana() {
    return NR_Aduana;
  }
}
