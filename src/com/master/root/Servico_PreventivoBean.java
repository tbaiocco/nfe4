package com.master.root;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import auth.OracleConnection2;


public class Servico_PreventivoBean
{
	private String CD_Tipo_Servico;
	private String NM_Tipo_Servico;
	private String CD_Modelo_Veiculo;
	private String NM_Modelo_Veiculo;
	private String NM_Servico_Preventivo;
	private String Usuario_Stamp;
	private String Dt_Stamp;
	private String Dm_Stamp;
	private int oid;
	private int oid_Modelo_Veiculo;
	private int OID_Tipo_Servico;
        private long NR_Kilometragem_Servico;

	public Servico_PreventivoBean() {}

	public String getCD_Modelo_Veiculo()
	{
		return CD_Modelo_Veiculo;
	}
	public void setCD_Modelo_Veiculo(String CD_Modelo_Veiculo)
	{
		this.CD_Modelo_Veiculo = CD_Modelo_Veiculo;
	}
	public String getNM_Modelo_Veiculo()
	{
		return NM_Modelo_Veiculo;
	}
	public void setNM_Modelo_Veiculo(String NM_Modelo_Veiculo)
	{
		this.NM_Modelo_Veiculo = NM_Modelo_Veiculo;
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
	public String getNM_Servico_Preventivo()
	{
		return NM_Servico_Preventivo;
	}
	public void setNM_Servico_Preventivo(String NM_Servico_Preventivo)
	{
		this.NM_Servico_Preventivo = NM_Servico_Preventivo;
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
	public int getOID_Modelo_Veiculo()
	{
		return oid_Modelo_Veiculo;
	}
	public void setOID_Modelo_Veiculo(int n)
	{
		this.oid_Modelo_Veiculo = n;
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
			// passando como parâmetro o NM_Modelo_Veiculo do DSN
			// o NM_Modelo_Veiculo de usuário e a senha do banco.
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
				"SELECT MAX(OID_Servico_Preventivo) FROM Servicos_Preventivos");

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
		buff.append(" INSERT INTO Servicos_Preventivos (OID_Servico_Preventivo, OID_Modelo_Veiculo, OID_Tipo_Servico, NR_Kilometragem_Servico, Dt_Stamp, Usuario_Stamp, Dm_Stamp) ");
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
			pstmt.setInt(2, getOID_Modelo_Veiculo());
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
			// passando como parâmetro o NM_Modelo_Veiculo do DSN
			// o NM_Modelo_Veiculo de usuário e a senha do banco.
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
		buff.append("UPDATE Servicos_Preventivos SET OID_Tipo_Servico=?, OID_Modelo_Veiculo=?, NR_Kilometragem_Servico=?, Dt_Stamp=?, Usuario_Stamp=?, Dm_Stamp=? ");
		buff.append("WHERE OID_Servico_Preventivo=?");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setInt(1, getOID_Tipo_Servico());
			pstmt.setInt(2, getOID_Modelo_Veiculo());
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
			// passando como parâmetro o NM_Modelo_Veiculo do DSN
			// o NM_Modelo_Veiculo de usuário e a senha do banco.
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
		buff.append("DELETE FROM Servicos_Preventivos ");
		buff.append("WHERE OID_Servico_Preventivo=?");
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

	public static final Servico_PreventivoBean getByOID(int oid)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Modelo_Veiculo do DSN
			// o NM_Modelo_Veiculo de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		Servico_PreventivoBean p = new Servico_PreventivoBean();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT ");
			buff.append("	Servicos_Preventivos.OID_Servico_Preventivo, ");
			buff.append("	Servicos_Preventivos.OID_Modelo_Veiculo, ");
			buff.append("	Servicos_Preventivos.OID_Tipo_Servico, ");
			buff.append("	Tipos_Servicos.CD_Tipo_Servico, ");
			buff.append("	Tipos_Servicos.NM_Tipo_Servico, ");
			buff.append("	Modelos_Veiculos.CD_Modelo_Veiculo, ");
			buff.append("	Modelos_Veiculos.NM_Modelo_Veiculo, ");
			buff.append("	Servicos_Preventivos.NR_Kilometragem_Servico ");
			buff.append("FROM Servicos_Preventivos, Modelos_Veiculos, Tipos_Servicos ");
			buff.append("WHERE Servicos_Preventivos.OID_Modelo_Veiculo = Modelos_Veiculos.OID_Modelo_Veiculo ");
			buff.append("AND Servicos_Preventivos.OID_Tipo_Servico = Tipos_Servicos.OID_Tipo_Servico ");
			buff.append("AND Servicos_Preventivos.OID_Servico_Preventivo = ");
			buff.append(oid);

                        // System.out.println(buff.toString());

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				p.setOID(cursor.getInt(1));
				p.setOID_Modelo_Veiculo(cursor.getInt(2));
				p.setOID_Tipo_Servico(cursor.getInt(3));
				p.setCD_Tipo_Servico(cursor.getString(4));
				p.setNM_Tipo_Servico(cursor.getString(5));
				p.setCD_Modelo_Veiculo(cursor.getString(6));
				p.setNM_Modelo_Veiculo(cursor.getString(7));
				p.setNR_Kilometragem_Servico(cursor.getLong(8));
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

		List Servico_Preventivo_Lista = new ArrayList();
		try
		{


			StringBuffer buff = new StringBuffer();
			buff.append("SELECT ");
			buff.append("	Servicos_Preventivos.OID_Servico_Preventivo, ");
			buff.append("	Servicos_Preventivos.OID_Modelo_Veiculo, ");
			buff.append("	Servicos_Preventivos.OID_Tipo_Servico, ");
			buff.append("	Tipos_Servicos.CD_Tipo_Servico, ");
			buff.append("	Tipos_Servicos.NM_Tipo_Servico, ");
			buff.append("	Modelos_Veiculos.CD_Modelo_Veiculo, ");
			buff.append("	Modelos_Veiculos.NM_Modelo_Veiculo, ");
			buff.append("	Servicos_Preventivos.NR_Kilometragem_Servico ");
			buff.append("FROM Servicos_Preventivos, Modelos_Veiculos, Tipos_Servicos ");
			buff.append("WHERE Servicos_Preventivos.OID_Modelo_Veiculo = Modelos_Veiculos.OID_Modelo_Veiculo ");
			buff.append("AND Servicos_Preventivos.OID_Tipo_Servico = Tipos_Servicos.OID_Tipo_Servico ");
			buff.append(" ORDER BY  Servicos_Preventivos.OID_Modelo_Veiculo, NR_Kilometragem_Servico ");

                        // System.out.println(buff.toString());

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				Servico_PreventivoBean p = new Servico_PreventivoBean();
				p.setOID(cursor.getInt(1));
				p.setOID_Modelo_Veiculo(cursor.getInt(2));
				p.setOID_Tipo_Servico(cursor.getInt(3));
				p.setCD_Tipo_Servico(cursor.getString(4));
				p.setNM_Tipo_Servico(cursor.getString(5));
				p.setCD_Modelo_Veiculo(cursor.getString(6));
				p.setNM_Modelo_Veiculo(cursor.getString(7));
				p.setNR_Kilometragem_Servico(cursor.getLong(8));
				Servico_Preventivo_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return Servico_Preventivo_Lista;
	}


}
