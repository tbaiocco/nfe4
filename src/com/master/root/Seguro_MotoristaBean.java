package com.master.root;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import auth.OracleConnection2;

public class Seguro_MotoristaBean
{
	private String NR_Liberacao;
	private String DT_Liberacao;
	private String DT_Validade;
	private String Usuario_Stamp; 
	private String Dt_Stamp;
	private String Dm_Stamp;
	private int oid;
	private int oid_Seguradora;
	private String oid_Pessoa;
	private String NM_Razao_Social;
	private String NM_Seguradora;
	private String CD_Seguradora;

	public Seguro_MotoristaBean() {}

	public String getNR_Liberacao()
	{
		return NR_Liberacao;
	}
	public void setNR_Liberacao(String NR_Liberacao)
	{
		this.NR_Liberacao = NR_Liberacao;
	}

	public String getDT_Liberacao()
	{
		FormataDataBean DataFormatada = new FormataDataBean();
		DataFormatada.setDT_FormataData(DT_Liberacao);
		DT_Liberacao = DataFormatada.getDT_FormataData();

		return DT_Liberacao;
	}
	public void setDT_Liberacao(String DT_Liberacao)
	{
		this.DT_Liberacao = DT_Liberacao;
	}

	public String getDT_Validade()
	{
          FormataDataBean DataFormatada = new FormataDataBean();
          DataFormatada.setDT_FormataData(DT_Validade);
          DT_Validade = DataFormatada.getDT_FormataData();
          return DT_Validade;
	}
	public void setDT_Validade(String DT_Validade)
	{
		this.DT_Validade = DT_Validade;
	}

	public int getOID_Seguradora()
	{
		return oid_Seguradora;
	}
	public void setOID_Seguradora(int n)
	{
		this.oid_Seguradora = n;
	}
	public String getNM_Seguradora()
	{
		return NM_Seguradora;
	}
	public void setNM_Seguradora(String NM_Seguradora)
	{
		this.NM_Seguradora = NM_Seguradora;
	}
	public String getCD_Seguradora()
	{
		return CD_Seguradora;
	}
	public void setCD_Seguradora(String CD_Seguradora)
	{
		this.CD_Seguradora = CD_Seguradora;
	}

	public String getOID_Pessoa()
	{
		return oid_Pessoa;
	}
	public void setOID_Pessoa(String n)
	{
		this.oid_Pessoa = n;
	}
	public String getNM_Razao_Social()
	{
		return NM_Razao_Social;
	}
	public void setNM_Razao_Social(String NM_Razao_Social)
	{
		this.NM_Razao_Social = NM_Razao_Social;
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
			// passando como parâmetro o NM_Seguro_Motorista do DSN
			// o NM_Seguro_Motorista de usuário e a senha do banco.
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
				"SELECT MAX(OID_Seguro_Motorista) FROM Seguros_Motoristas");

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
		buff.append("INSERT INTO Seguros_Motoristas (OID_Seguro_Motorista, OID_Seguradora, OID_Pessoa,  NR_Liberacao, DT_Liberacao, DT_Validade, Dt_Stamp, Usuario_Stamp, Dm_Stamp) ");
		buff.append("VALUES (?,?,?,?,?,?,?,?,?)");
		/*
		 * Define os dados do SQL
		* e executa o insert no banco.
		*/
		try
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setInt(1, getOID());
			pstmt.setInt(2, getOID_Seguradora());
			pstmt.setString(3, getOID_Pessoa());
			pstmt.setString(4, getNR_Liberacao());
			pstmt.setString(5, this.DT_Liberacao);
			pstmt.setString(6, this.DT_Validade);
			pstmt.setString(7, getDt_Stamp());
			pstmt.setString(8, getUsuario_Stamp());
			pstmt.setString(9, getDm_Stamp());
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
			// passando como parâmetro o NM_Seguro_Motorista do DSN
			// o NM_Seguro_Motorista de usuário e a senha do banco.
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
		buff.append("UPDATE Seguros_Motoristas SET  NR_Liberacao=?, DT_Validade=? ");
		buff.append("WHERE OID_Seguro_Motorista=?");
		/*
		 * Define os dados do SQL
		* e executa o insert no banco.
		*/
		try
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setString(1, this.NR_Liberacao);
			pstmt.setString(2, this.DT_Validade);
			pstmt.setInt(3, getOID());
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

	public void deleta() throws Exception
	{
		/*
		 * Abre a conexão com o banco
		*/
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Seguro_Motorista do DSN
			// o NM_Seguro_Motorista de usuário e a senha do banco.
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

                ////// System.out.println(getOID());

		StringBuffer buff = new StringBuffer();
		buff.append("DELETE FROM Seguros_Motoristas ");
		buff.append("WHERE OID_Seguro_Motorista=?");
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

	public static final Seguro_MotoristaBean getByOID(int oid)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		*/
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Seguro_Motorista do DSN
			// o NM_Seguro_Motorista de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		Seguro_MotoristaBean p = new Seguro_MotoristaBean();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append(" SELECT Seguros_Motoristas.OID_Seguro_Motorista, Seguros_Motoristas.OID_Seguradora, Seguros_Motoristas.OID_Pessoa, NR_Liberacao, DT_Liberacao, DT_Validade, Seguros_Motoristas.Dt_Stamp, Seguros_Motoristas.Usuario_Stamp, Seguros_Motoristas.Dm_Stamp, Pessoas.NM_Razao_Social, Seguradoras.NM_Seguradora, Seguradoras.CD_Seguradora ");
			buff.append(" FROM Seguros_Motoristas, Pessoas, Seguradoras ");
			buff.append(" WHERE Seguros_Motoristas.OID_Seguradora = Seguradoras.OID_Seguradora ");
			buff.append(" AND Seguros_Motoristas.OID_Pessoa = Pessoas.OID_Pessoa ");
			buff.append(" AND Seguros_Motoristas.OID_Seguro_Motorista =");
			buff.append(oid);

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				p.setOID(cursor.getInt(1));
				p.setOID_Seguradora(cursor.getInt(2));
				p.setOID_Pessoa(cursor.getString(3));
				p.setNR_Liberacao(cursor.getString(4));
				p.setDT_Liberacao(cursor.getString(5));
				p.setDT_Validade(cursor.getString(6));
				p.setDt_Stamp(cursor.getString(7));
				p.setUsuario_Stamp(cursor.getString(8));
				p.setDm_Stamp(cursor.getString(9));
				p.setNM_Razao_Social(cursor.getString(10)+"*-999*---**");
				p.setNM_Seguradora(cursor.getString(11));
				p.setCD_Seguradora(cursor.getString(12));
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

	public static final Seguro_MotoristaBean getByOID_Motorista(String oid_Pessoa)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		*/
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Seguro_Motorista do DSN
			// o NM_Seguro_Motorista de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		Seguro_MotoristaBean p = new Seguro_MotoristaBean();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append(" SELECT Seguros_Motoristas.OID_Seguro_Motorista, Seguros_Motoristas.OID_Seguradora, Seguros_Motoristas.OID_Pessoa, NR_Liberacao, DT_Liberacao, DT_Validade, Seguros_Motoristas.Dt_Stamp, Seguros_Motoristas.Usuario_Stamp, Seguros_Motoristas.Dm_Stamp, Pessoas.NM_Razao_Social, Seguradoras.NM_Seguradora, Seguradoras.CD_Seguradora ");
			buff.append(" FROM Seguros_Motoristas, Pessoas, Seguradoras ");
			buff.append(" WHERE Seguros_Motoristas.OID_Seguradora = Seguradoras.OID_Seguradora ");
			buff.append(" AND Seguros_Motoristas.OID_Pessoa = Pessoas.OID_Pessoa ");
			buff.append(" AND Seguros_Motoristas.OID_Pessoa ='");
			buff.append(oid_Pessoa);
			buff.append("'");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				p.setOID(cursor.getInt(1));
				p.setOID_Seguradora(cursor.getInt(2));
				p.setOID_Pessoa(cursor.getString(3));
				p.setNR_Liberacao(cursor.getString(4));
				p.setDT_Liberacao(cursor.getString(5));
				p.setDT_Validade(cursor.getString(6));
				p.setDt_Stamp(cursor.getString(7));
				p.setUsuario_Stamp(cursor.getString(8));
				p.setDm_Stamp(cursor.getString(9));
				p.setNM_Razao_Social(cursor.getString(10));
				p.setNM_Seguradora(cursor.getString(11));
				p.setCD_Seguradora(cursor.getString(12));

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


	
	public static final Seguro_MotoristaBean getByLiberacao(String oid_Pessoa, String dt_Liberacao, String dt_Validade)
	throws Exception
{
	/*
	 * Abre a conexão com o banco
	*/
	Connection conn = null;
	try
	{
		// Pede uma conexão ao gerenciador do driver
		// passando como parâmetro o NM_Seguro_Motorista do DSN
		// o NM_Seguro_Motorista de usuário e a senha do banco.
		conn = OracleConnection2.getWEB();
		conn.setAutoCommit(false);
	} catch (Exception e)
	{
		e.printStackTrace();
		throw e;
	}

	Seguro_MotoristaBean p = new Seguro_MotoristaBean();
	try
	{
		StringBuffer buff = new StringBuffer();
		buff.append(" SELECT Seguros_Motoristas.OID_Seguro_Motorista, Seguros_Motoristas.OID_Seguradora, Seguros_Motoristas.OID_Pessoa, NR_Liberacao, DT_Liberacao, DT_Validade, Seguros_Motoristas.Dt_Stamp, Seguros_Motoristas.Usuario_Stamp, Seguros_Motoristas.Dm_Stamp, Pessoas.NM_Razao_Social, Seguradoras.NM_Seguradora, Seguradoras.CD_Seguradora ");
		buff.append(" FROM Seguros_Motoristas, Pessoas, Seguradoras ");
		buff.append(" WHERE Seguros_Motoristas.OID_Seguradora = Seguradoras.OID_Seguradora ");
		buff.append(" AND Seguros_Motoristas.OID_Pessoa = Pessoas.OID_Pessoa ");
		buff.append(" AND Seguros_Motoristas.OID_Pessoa ='");
		buff.append(oid_Pessoa);
	
		buff.append("' AND Seguros_Motoristas.DT_Validade  >='");
		buff.append(dt_Validade);
	
		buff.append("'");
		// System.out.println(buff.toString());
		
		Statement stmt = conn.createStatement();
		ResultSet cursor =
			stmt.executeQuery(buff.toString());

		while (cursor.next())
		{
			p.setOID(cursor.getInt(1));
			p.setOID_Seguradora(cursor.getInt(2));
			p.setOID_Pessoa(cursor.getString(3));
			p.setNR_Liberacao(cursor.getString(4));
			p.setDT_Liberacao(cursor.getString(5));
			p.setDT_Validade(cursor.getString(6));
			p.setDt_Stamp(cursor.getString(7));
			p.setUsuario_Stamp(cursor.getString(8));
			p.setDm_Stamp(cursor.getString(9));
			p.setNM_Razao_Social(cursor.getString(10));
			p.setNM_Seguradora(cursor.getString(11));
			p.setCD_Seguradora(cursor.getString(12));

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

		List Seguro_Motoristas_Lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append(" SELECT Seguros_Motoristas.OID_Seguro_Motorista, Seguros_Motoristas.OID_Seguradora, Seguros_Motoristas.OID_Pessoa, NR_Liberacao, DT_Liberacao, DT_Validade, Seguros_Motoristas.Dt_Stamp, Seguros_Motoristas.Usuario_Stamp, Seguros_Motoristas.Dm_Stamp, Pessoas.NM_Razao_Social, Seguradoras.NM_Seguradora, Seguradoras.CD_Seguradora ");
			buff.append(" FROM Seguros_Motoristas, Pessoas, Seguradoras ");
			buff.append(" WHERE Seguros_Motoristas.OID_Seguradora = Seguradoras.OID_Seguradora ");
			buff.append(" AND Seguros_Motoristas.OID_Pessoa = Pessoas.OID_Pessoa ");
			buff.append(" ORDER BY Seguros_Motoristas.DT_Validade ");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				Seguro_MotoristaBean p = new Seguro_MotoristaBean();
				p.setOID(cursor.getInt(1));
				p.setOID_Seguradora(cursor.getInt(2));
				p.setOID_Pessoa(cursor.getString(3));
				p.setNR_Liberacao(cursor.getString(4));
				p.setDT_Liberacao(cursor.getString(5));
				p.setDT_Validade(cursor.getString(6));
				p.setDt_Stamp(cursor.getString(7));
				p.setUsuario_Stamp(cursor.getString(8));
				p.setDm_Stamp(cursor.getString(9));
				p.setNM_Razao_Social(cursor.getString(10));
				p.setNM_Seguradora(cursor.getString(11));
				p.setCD_Seguradora(cursor.getString(12));
				Seguro_Motoristas_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return Seguro_Motoristas_Lista;
	}

	public static final List getByNR_CNPJ_CPF(String NR_CNPJ_CPF )
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

		List Seguro_Motoristas_Lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append(" SELECT Seguros_Motoristas.OID_Seguro_Motorista, Seguros_Motoristas.OID_Seguradora, Seguros_Motoristas.OID_Pessoa, NR_Liberacao, DT_Liberacao, DT_Validade, Seguros_Motoristas.Dt_Stamp, Seguros_Motoristas.Usuario_Stamp, Seguros_Motoristas.Dm_Stamp, Pessoas.NM_Razao_Social, Seguradoras.NM_Seguradora, Seguradoras.CD_Seguradora ");
			buff.append(" FROM Seguros_Motoristas, Pessoas, Seguradoras ");
			buff.append(" WHERE Seguros_Motoristas.OID_Seguradora = Seguradoras.OID_Seguradora ");
			buff.append(" AND Seguros_Motoristas.OID_Pessoa = Pessoas.OID_Pessoa ");
			buff.append(" AND Seguros_Motoristas.OID_Pessoa =");
			buff.append(NR_CNPJ_CPF);
			buff.append(" ");
			buff.append(" ORDER BY Seguros_Motoristas.DT_Validade ");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				Seguro_MotoristaBean p = new Seguro_MotoristaBean();
				p.setOID(cursor.getInt(1));
				p.setOID_Seguradora(cursor.getInt(2));
				p.setOID_Pessoa(cursor.getString(3));
				p.setNR_Liberacao(cursor.getString(4));
				p.setDT_Liberacao(cursor.getString(5));
				p.setDT_Validade(cursor.getString(6));
				p.setDt_Stamp(cursor.getString(7));
				p.setUsuario_Stamp(cursor.getString(8));
				p.setDm_Stamp(cursor.getString(9));
				p.setNM_Razao_Social(cursor.getString(10));
				p.setNM_Seguradora(cursor.getString(11));
				p.setCD_Seguradora(cursor.getString(12));
				Seguro_Motoristas_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return Seguro_Motoristas_Lista;
	}

	public static void main(String args[])
		throws Exception
	{
		Seguro_MotoristaBean pp = new Seguro_MotoristaBean();
		pp.setOID(1);
		pp.setOID_Pessoa("11111111111");
		pp.setOID_Seguradora(1);
		pp.update();

		Seguro_MotoristaBean p = getByOID(1);
		// //// System.out.println(p.getOID());




	}

	
	
}
