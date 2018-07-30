package com.master.root;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import auth.OracleConnection2;

public class VisitaBean
{
	private int NR_Visita;
	private String NM_Contato;
	private String DT_Visita;
	private String HR_Visita;
	private String DT_Agendado;
	private String HR_Agendado;
	private String NM_Setor;
	private String NM_Equipe;
	private String DT_Revisao;
	private String DM_KIT;
	private String TX_Descricao;
	private String Usuario_Stamp;
	private String Dt_Stamp;
	private String Dm_Stamp;
	private int oid;
	private int oid_Tipo_Visita;
	private String oid_Vendedor;
	private String oid_Pessoa;
	private String NM_Razao_Social;
	private String NM_Vendedor;
	private String NM_Tipo_Visita;
	private String CD_Vendedor;
	private String CD_Tipo_Visita;

	public VisitaBean()
	{
		NM_Contato = " ";
		HR_Visita = " ";
		HR_Agendado = " ";
		NM_Setor = " ";
		NM_Equipe = " ";
		TX_Descricao = " ";
	}

	public int getNR_Visita()
	{
		return NR_Visita;
	}
	public void setNR_Visita(int NR_Visita)
	{
		this.NR_Visita = NR_Visita;
	}

	public String getNM_Contato()
	{
		return NM_Contato;
	}
	public void setNM_Contato(String NM_Contato)
	{
		this.NM_Contato = NM_Contato;
	}

	public String getDT_Visita()
	{
		FormataDataBean DataFormatada = new FormataDataBean();
		DataFormatada.setDT_FormataData(DT_Visita);
		DT_Visita = DataFormatada.getDT_FormataData();

		return DT_Visita;
	}
	public void setDT_Visita(String DT_Visita)
	{
		this.DT_Visita = DT_Visita;
	}

	public String getHR_Visita()
	{
		return HR_Visita;
	}
	public void setHR_Visita(String HR_Visita)
	{
		this.HR_Visita = HR_Visita;
	}

	public String getDT_Agendado()
	{
		return DT_Agendado;
	}
	public void setDT_Agendado(String DT_Agendado)
	{
		this.DT_Agendado = DT_Agendado;
	}

	public String getHR_Agendado()
	{
		return HR_Agendado;
	}
	public void setHR_Agendado(String HR_Agendado)
	{
		this.HR_Agendado = HR_Agendado;
	}

	public String getNM_Setor()
	{
		return NM_Setor;
	}
	public void setNM_Setor(String NM_Setor)
	{
		this.NM_Setor = NM_Setor;
	}

	public String getNM_Equipe()
	{
		return NM_Equipe;
	}
	public void setNM_Equipe(String NM_Equipe)
	{
		this.NM_Equipe = NM_Equipe;
	}

	public String getDT_Revisao()
	{
		return DT_Revisao;
	}
	public void setDT_Revisao(String DT_Revisao)
	{
		this.DT_Revisao = DT_Revisao;
	}

	public String getTX_Descricao()
	{
		return TX_Descricao;
	}
	public void setTX_Descricao(String TX_Descricao)
	{
		this.TX_Descricao = TX_Descricao;
	}

	public int getOID_Tipo_Visita()
	{
		return oid_Tipo_Visita;
	}
	public void setOID_Tipo_Visita(int n)
	{
		this.oid_Tipo_Visita = n;
	}
	public String getNM_Tipo_Visita()
	{
		return NM_Tipo_Visita;
	}

	public void setNM_Tipo_Visita(String NM_Tipo_Visita)
	{
		this.NM_Tipo_Visita = NM_Tipo_Visita;
	}

	public String getCD_Tipo_Visita()
	{
		return CD_Tipo_Visita;
	}
	public void setCD_Tipo_Visita(String CD_Tipo_Visita)
	{
		this.CD_Tipo_Visita = CD_Tipo_Visita;
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

	public String getOID_Vendedor()
	{
		return oid_Vendedor;
	}
	public void setOID_Vendedor(String n)
	{
		this.oid_Vendedor = n;
	}
	public String getNM_Vendedor()
	{
		return NM_Vendedor;
	}
	public void setNM_Vendedor(String NM_Vendedor)
	{
		this.NM_Vendedor = NM_Vendedor;
	}
	public String getCD_Vendedor()
	{
		return CD_Vendedor;
	}
	public void setCD_Vendedor(String CD_Vendedor)
	{
		this.CD_Vendedor = CD_Vendedor;
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
			// passando como parâmetro o NM_Visita do DSN
			// o NM_Visita de usuário e a senha do banco.
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
				"SELECT MAX(OID_Visita) FROM Visitas");

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
		buff.append("INSERT INTO Visitas (OID_Visita, OID_Tipo_Visita, OID_Pessoa, OID_Vendedor, NR_Visita, NM_Contato, DT_Visita, HR_Visita, NM_Setor, NM_Equipe, TX_Descricao) ");
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
			pstmt.setInt(2, getOID_Tipo_Visita());
			pstmt.setString(3, getOID_Pessoa());
			pstmt.setString(4, getOID_Vendedor());
			pstmt.setInt(5, getNR_Visita());
			pstmt.setString(6, getNM_Contato());
			pstmt.setString(7, this.DT_Visita);
			pstmt.setString(8, getHR_Visita());
			pstmt.setString(9, getNM_Setor());
			pstmt.setString(10, getNM_Equipe());
			pstmt.setString(11, getTX_Descricao());
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
			// passando como parâmetro o NM_Visita do DSN
			// o NM_Visita de usuário e a senha do banco.
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
		buff.append("UPDATE Visitas SET OID_Tipo_Visita=?, OID_Vendedor=?, NR_Visita=?, NM_Contato=?, NM_Setor=?, NM_Equipe=?, TX_Descricao=?, DT_Visita=?, HR_Visita=? ");
		buff.append("WHERE OID_Visita=?");
		/*
		 * Define os dados do SQL
		* e executa o insert no banco.
		*/
		try
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());

			pstmt.setInt(1, getOID_Tipo_Visita());
			pstmt.setString(2, getOID_Vendedor());
			pstmt.setInt(3, getNR_Visita());
			pstmt.setString(4, getNM_Contato());
			pstmt.setString(5, getNM_Setor());
			pstmt.setString(6, getNM_Equipe());
			pstmt.setString(7, getTX_Descricao());
			pstmt.setString(8, this.DT_Visita);
			pstmt.setString(9, getHR_Visita());
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
			// passando como parâmetro o NM_Visita do DSN
			// o NM_Visita de usuário e a senha do banco.
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
		buff.append("DELETE FROM Visitas ");
		buff.append("WHERE OID_Visita=?");
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

	public static final VisitaBean getByOID(int oid)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		*/
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Visita do DSN
			// o NM_Visita de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		VisitaBean p = new VisitaBean();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT Visitas.OID_Visita,	Visitas.OID_Tipo_Visita, Visitas.OID_Pessoa, Visitas.OID_Vendedor, NR_Visita, NM_Contato, DT_Visita, HR_Visita, DT_Agendado, HR_Agendado, NM_Setor, NM_Equipe, DT_Revisao, TX_Descricao, Visitas.Dt_Stamp, Visitas.Usuario_Stamp, Visitas.Dm_Stamp, Pessoas.NM_Razao_Social, Vendedores.DM_Situacao, Tipos_Visitas.NM_Tipo_Visita, Vendedores.CD_Vendedor, Tipos_Visitas.CD_Tipo_Visita ");
			buff.append("FROM Visitas, Pessoas, Tipos_Visitas, Vendedores ");
			buff.append("WHERE Visitas.OID_Tipo_Visita = Tipos_Visitas.OID_Tipo_Visita AND Visitas.OID_Pessoa = Pessoas.OID_Pessoa AND Visitas.OID_Vendedor = Vendedores.OID_Vendedor ");
			buff.append(" AND Visitas.OID_Visita =");
			buff.append(oid);

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				p.setOID(cursor.getInt(1));
				p.setOID_Tipo_Visita(cursor.getInt(2));
				p.setOID_Pessoa(cursor.getString(3));
				p.setOID_Vendedor(cursor.getString(4));
				p.setNR_Visita(cursor.getInt(5));
				p.setNM_Contato(cursor.getString(6));
				p.setDT_Visita(cursor.getString(7));
				p.setHR_Visita(cursor.getString(8));
				p.setDT_Agendado(cursor.getString(9));
				p.setHR_Agendado(cursor.getString(10));
				p.setNM_Setor(cursor.getString(11));
				p.setNM_Equipe(cursor.getString(12));
				p.setDT_Revisao(cursor.getString(13));
				p.setTX_Descricao(cursor.getString(14));
				p.setDt_Stamp(cursor.getString(15));
				p.setUsuario_Stamp(cursor.getString(16));
				p.setDm_Stamp(cursor.getString(17));
				p.setNM_Razao_Social(cursor.getString(18));
				p.setNM_Vendedor(cursor.getString(19));
				p.setNM_Tipo_Visita(cursor.getString(20));
				p.setCD_Vendedor(cursor.getString(21));
				p.setCD_Tipo_Visita(cursor.getString(22));
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

	public static final VisitaBean getByNR_Visita(int NR_Visita)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		*/
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Visita do DSN
			// o NM_Visita de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		VisitaBean p = new VisitaBean();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT Visitas.OID_Visita,	Visitas.OID_Tipo_Visita, Visitas.OID_Pessoa, Visitas.OID_Vendedor, NR_Visita, NM_Contato, DT_Visita, HR_Visita, DT_Agendado, HR_Agendado, NM_Setor, NM_Equipe, DT_Revisao, TX_Descricao, Visitas.Dt_Stamp, Visitas.Usuario_Stamp, Visitas.Dm_Stamp, Pessoas.NM_Razao_Social, Vendedores.DM_Situacao, Tipos_Visitas.NM_Tipo_Visita, Vendedores.CD_Vendedor, Tipos_Visitas.CD_Tipo_Visita ");
			buff.append("FROM Visitas, Pessoas, Tipos_Visitas, Vendedores ");
			buff.append("WHERE Visitas.OID_Tipo_Visita = Tipos_Visitas.OID_Tipo_Visita AND Visitas.OID_Pessoa = Pessoas.OID_Pessoa AND Visitas.OID_Vendedor = Vendedores.OID_Vendedor ");
			buff.append(" AND Visitas.NR_Visita =");
			buff.append(NR_Visita);

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				p.setOID(cursor.getInt(1));
				p.setOID_Tipo_Visita(cursor.getInt(2));
				p.setOID_Pessoa(cursor.getString(3));
				p.setOID_Vendedor(cursor.getString(4));
				p.setNR_Visita(cursor.getInt(5));
				p.setNM_Contato(cursor.getString(6));
				p.setDT_Visita(cursor.getString(7));
				p.setHR_Visita(cursor.getString(8));
				p.setDT_Agendado(cursor.getString(9));
				p.setHR_Agendado(cursor.getString(10));
				p.setNM_Setor(cursor.getString(11));
				p.setNM_Equipe(cursor.getString(12));
				p.setDT_Revisao(cursor.getString(13));
				p.setTX_Descricao(cursor.getString(14));
				p.setDt_Stamp(cursor.getString(15));
				p.setUsuario_Stamp(cursor.getString(16));
				p.setDm_Stamp(cursor.getString(17));
				p.setNM_Razao_Social(cursor.getString(18));
				p.setNM_Vendedor(cursor.getString(19));
				p.setNM_Tipo_Visita(cursor.getString(20));
				p.setCD_Vendedor(cursor.getString(21));
				p.setCD_Tipo_Visita(cursor.getString(22));
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

	public static final List getByNR_Visita_Lista(int NR_Visita)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		*/
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Visita do DSN
			// o NM_Visita de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		List Visitas_Lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT Visitas.OID_Visita,	Visitas.OID_Tipo_Visita, Visitas.OID_Pessoa, Visitas.OID_Vendedor, NR_Visita, NM_Contato, DT_Visita, HR_Visita, DT_Agendado, HR_Agendado, NM_Setor, NM_Equipe, DT_Revisao, TX_Descricao, Visitas.Dt_Stamp, Visitas.Usuario_Stamp, Visitas.Dm_Stamp, Pessoas.NM_Razao_Social, Vendedores.DM_Situacao, Tipos_Visitas.NM_Tipo_Visita, Vendedores.CD_Vendedor, Tipos_Visitas.CD_Tipo_Visita ");
			buff.append("FROM Visitas, Pessoas, Tipos_Visitas, Vendedores ");
			buff.append("WHERE Visitas.OID_Tipo_Visita = Tipos_Visitas.OID_Tipo_Visita AND Visitas.OID_Pessoa = Pessoas.OID_Pessoa AND Visitas.OID_Vendedor = Vendedores.OID_Vendedor ");
			buff.append(" AND Visitas.NR_Visita =");
			buff.append(NR_Visita);


			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				VisitaBean p = new VisitaBean();
				p.setOID(cursor.getInt(1));
				p.setOID_Tipo_Visita(cursor.getInt(2));
				p.setOID_Pessoa(cursor.getString(3));
				p.setOID_Vendedor(cursor.getString(4));
				p.setNR_Visita(cursor.getInt(5));
				p.setNM_Contato(cursor.getString(6));
				p.setDT_Visita(cursor.getString(7));
				p.setHR_Visita(cursor.getString(8));
				p.setDT_Agendado(cursor.getString(9));
				p.setHR_Agendado(cursor.getString(10));
				p.setNM_Setor(cursor.getString(11));
				p.setNM_Equipe(cursor.getString(12));
				p.setDT_Revisao(cursor.getString(13));
				p.setTX_Descricao(cursor.getString(14));
				p.setDt_Stamp(cursor.getString(15));
				p.setUsuario_Stamp(cursor.getString(16));
				p.setDm_Stamp(cursor.getString(17));
				p.setNM_Razao_Social(cursor.getString(18));
				p.setNM_Vendedor(cursor.getString(19));
				p.setNM_Tipo_Visita(cursor.getString(20));
				p.setCD_Vendedor(cursor.getString(21));
				p.setCD_Tipo_Visita(cursor.getString(22));
				Visitas_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return Visitas_Lista;
	}


	public static final List getByOID_Cliente_Lista(String OID_Cliente)
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

		List Visitas_Lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT Visitas.OID_Visita,	Visitas.OID_Tipo_Visita, Visitas.OID_Pessoa, Visitas.OID_Vendedor, NR_Visita, NM_Contato, DT_Visita, HR_Visita, DT_Agendado, HR_Agendado, NM_Setor, NM_Equipe, DT_Revisao, TX_Descricao, Visitas.Dt_Stamp, Visitas.Usuario_Stamp, Visitas.Dm_Stamp, Pessoas.NM_Razao_Social, Vendedores.DM_Situacao, Tipos_Visitas.NM_Tipo_Visita, Vendedores.CD_Vendedor, Tipos_Visitas.CD_Tipo_Visita ");
			buff.append("FROM Visitas, Pessoas, Tipos_Visitas, Vendedores ");
			buff.append("WHERE Visitas.OID_Tipo_Visita = Tipos_Visitas.OID_Tipo_Visita AND Visitas.OID_Pessoa = Pessoas.OID_Pessoa AND Visitas.OID_Vendedor = Vendedores.OID_Vendedor ");
			buff.append(" AND Pessoas.OID_Pessoa = '");
			buff.append(OID_Cliente);
			buff.append("'");
			buff.append(" ORDER BY Visitas.NR_Visita ");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				VisitaBean p = new VisitaBean();
				p.setOID(cursor.getInt(1));
				p.setOID_Tipo_Visita(cursor.getInt(2));
				p.setOID_Pessoa(cursor.getString(3));
				p.setOID_Vendedor(cursor.getString(4));
				p.setNR_Visita(cursor.getInt(5));
				p.setNM_Contato(cursor.getString(6));
				p.setDT_Visita(cursor.getString(7));
				p.setHR_Visita(cursor.getString(8));
				p.setDT_Agendado(cursor.getString(9));
				p.setHR_Agendado(cursor.getString(10));
				p.setNM_Setor(cursor.getString(11));
				p.setNM_Equipe(cursor.getString(12));
				p.setDT_Revisao(cursor.getString(13));
				p.setTX_Descricao(cursor.getString(14));
				p.setDt_Stamp(cursor.getString(15));
				p.setUsuario_Stamp(cursor.getString(16));
				p.setDm_Stamp(cursor.getString(17));
				p.setNM_Razao_Social(cursor.getString(18));
				p.setNM_Vendedor(cursor.getString(19));
				p.setNM_Tipo_Visita(cursor.getString(20));
				p.setCD_Vendedor(cursor.getString(21));
				p.setCD_Tipo_Visita(cursor.getString(22));
				Visitas_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return Visitas_Lista;
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

		List Visitas_Lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT Visitas.OID_Visita,	Visitas.OID_Tipo_Visita, Visitas.OID_Pessoa, Visitas.OID_Vendedor, NR_Visita, NM_Contato, DT_Visita, HR_Visita, DT_Agendado, HR_Agendado, NM_Setor, NM_Equipe, DT_Revisao, TX_Descricao, Visitas.Dt_Stamp, Visitas.Usuario_Stamp, Visitas.Dm_Stamp, Pessoas.NM_Razao_Social, Vendedores.DM_Situacao, Tipos_Visitas.NM_Tipo_Visita, Vendedores.CD_Vendedor, Tipos_Visitas.CD_Tipo_Visita ");
			buff.append("FROM Visitas, Pessoas, Tipos_Visitas, Vendedores ");
			buff.append("WHERE Visitas.OID_Tipo_Visita = Tipos_Visitas.OID_Tipo_Visita AND Visitas.OID_Pessoa = Pessoas.OID_Pessoa AND Visitas.OID_Vendedor = Vendedores.OID_Vendedor ");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				VisitaBean p = new VisitaBean();
				p.setOID(cursor.getInt(1));
				p.setOID_Tipo_Visita(cursor.getInt(2));
				p.setOID_Pessoa(cursor.getString(3));
				p.setOID_Vendedor(cursor.getString(4));
				p.setNR_Visita(cursor.getInt(5));
				p.setNM_Contato(cursor.getString(6));
				p.setDT_Visita(cursor.getString(7));
				p.setHR_Visita(cursor.getString(8));
				p.setDT_Agendado(cursor.getString(9));
				p.setHR_Agendado(cursor.getString(10));
				p.setNM_Setor(cursor.getString(11));
				p.setNM_Equipe(cursor.getString(12));
				p.setDT_Revisao(cursor.getString(13));
				p.setTX_Descricao(cursor.getString(14));
				p.setDt_Stamp(cursor.getString(15));
				p.setUsuario_Stamp(cursor.getString(16));
				p.setDm_Stamp(cursor.getString(17));
				p.setNM_Razao_Social(cursor.getString(18));
				p.setNM_Vendedor(cursor.getString(19));
				p.setNM_Tipo_Visita(cursor.getString(20));
				p.setCD_Vendedor(cursor.getString(21));
				p.setCD_Tipo_Visita(cursor.getString(22));
				Visitas_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return Visitas_Lista;
	}

	public static void main(String args[])
		throws Exception
	{
		VisitaBean pp = new VisitaBean();
		pp.setOID(1);
		pp.setOID_Pessoa("11111111111");
		pp.setOID_Tipo_Visita(1);
		pp.setOID_Vendedor("1");
		pp.setNR_Visita(9999);
		pp.update();

		VisitaBean p = getByOID(1);
		// //// System.out.println(p.getOID());
		// //// System.out.println(p.getNR_Visita());



	}
}
