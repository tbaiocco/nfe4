package com.master.root;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import auth.OracleConnection2;

public class CotacaoBean
{
	private int NR_Cotacao;
	private String DT_Cotacao;
	private String NM_Solicitante;
	private int QT_Volumes;
	private int NR_Peso;
	private double VL_Mercadoria;
	private double VL_Calculo;
	private double PE_Desconto;
	private double VL_Cotacao;
	private String DM_Coleta;
	private String TX_Observacao;
	private String Usuario_Stamp;
	private String Dt_Stamp;
	private String Dm_Stamp;
	private int oid;
	private int oid_Modal;
	private int oid_Unidade;
	private int oid_Cidade;
	private int oid_Cidade_Destino;
	private String oid_Pessoa;
	private String NM_Razao_Social;


	public CotacaoBean()
	{
		NR_Cotacao=0;
		NM_Solicitante="";
		QT_Volumes=0;
		NR_Peso=0;
		VL_Mercadoria=0;
		VL_Calculo=0;
		PE_Desconto=0;
		VL_Cotacao=0;
		DM_Coleta="";
		TX_Observacao=" ";
		Usuario_Stamp="";

		Dm_Stamp="";
		oid=0;
		oid_Modal=0;
		oid_Unidade=0;
		oid_Cidade=0;
		oid_Cidade_Destino=0;
		oid_Pessoa="";
		NM_Razao_Social="";

	}

	public int getNR_Cotacao()
	{
		return NR_Cotacao;
	}
	public void setNR_Cotacao(int NR_Cotacao)
	{
		this.NR_Cotacao = NR_Cotacao;
	}

	public String getDT_Cotacao()
	{
		FormataDataBean DataFormatada = new FormataDataBean();
		DataFormatada.setDT_FormataData(DT_Cotacao);
		DT_Cotacao = DataFormatada.getDT_FormataData();

		return DT_Cotacao;
	}

	public void setDT_Cotacao(String DT_Cotacao)
	{
		this.DT_Cotacao = DT_Cotacao;
	}

	public String getNM_Solicitante()
	{
		return NM_Solicitante;
	}
	public void setNM_Solicitante(String NM_Solicitante)
	{
		this.NM_Solicitante = NM_Solicitante;
	}

	public int getQT_Volumes()
	{
		return QT_Volumes;
	}
	public void setQT_Volumes(int QT_Volumes)
	{
		this.QT_Volumes = QT_Volumes;
	}

	public int getNR_Peso()
	{
		return NR_Peso;
	}
	public void setNR_Peso(int NR_Peso)
	{
		this.NR_Peso = NR_Peso;
	}

	public double getVL_Mercadoria()
	{
		return VL_Mercadoria;
	}
	public void setVL_Mercadoria(double VL_Mercadoria)
	{
		this.VL_Mercadoria = VL_Mercadoria;
	}

	public double getVL_Calculo()
	{
		return VL_Calculo;
	}
	public void setVL_Calculo(double VL_Calculo)
	{
		this.VL_Calculo = VL_Calculo;
	}

	public double getPE_Desconto()
	{
		return PE_Desconto;
	}
	public void setPE_Desconto(double PE_Desconto)
	{
		this.PE_Desconto = PE_Desconto;
	}

	public double getVL_Cotacao()
	{
		return VL_Cotacao;
	}
	public void setVL_Cotacao(double VL_Cotacao)
	{
		this.VL_Cotacao = VL_Cotacao;
	}

	public String getDM_Coleta()
	{
		return DM_Coleta;
	}
	public void setDM_Coleta(String DM_Coleta)
	{
		this.DM_Coleta = DM_Coleta;
	}

	public String getTX_Observacao()
	{
		return TX_Observacao;
	}
	public void setTX_Observacao(String TX_Observacao)
	{
		this.TX_Observacao = TX_Observacao;
	}

	public int getOID_Modal()
	{
		return oid_Modal;
	}
	public void setOID_Modal(int n)
	{
		this.oid_Modal = n;
	}

	public int getOID_Unidade()
	{
		return oid_Unidade;
	}
	public void setOID_Unidade(int n)
	{
		this.oid_Unidade = n;
	}

	public String getOID_Pessoa()
	{
		return oid_Pessoa;
	}
	public void setOID_Pessoa(String n)
	{
		this.oid_Pessoa = n;
	}

	public int getOID_Cidade()
	{
		return oid_Cidade;
	}
	public void setOID_Cidade(int n)
	{
		this.oid_Cidade = n;
	}

	public int getOID_Cidade_Destino()
	{
		return oid_Cidade_Destino;
	}
	public void setOID_Cidade_Destino(int n)
	{
		this.oid_Cidade_Destino = n;
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
			// passando como parâmetro o NM_Cotacao do DSN
			// o NM_Cotacao de usuário e a senha do banco.
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
				"SELECT MAX(OID_Cotacao) FROM Cotacoes");

			while (cursor.next())
			{
				int oid = cursor.getInt(1);
				setOID(oid + 1);
			}

			StringBuffer buff = new StringBuffer();
			buff.append(" SELECT NR_Proxima_Cotacao ");
			buff.append(" FROM Parametros_Filiais ");
			buff.append(" WHERE OID_Unidade = ");
			buff.append(getOID_Unidade());

			ResultSet cursor2 =
				stmt.executeQuery(buff.toString());

			while (cursor2.next())
			{
				setNR_Cotacao(cursor2.getInt(1));
			}

			cursor.close();
			cursor2.close();
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
		buff.append("INSERT INTO Cotacoes (OID_Cotacao, OID_Unidade, OID_Modal, OID_Pessoa, OID_Cidade, OID_Cidade_Destino, NR_Cotacao, DT_Cotacao, NM_Solicitante, QT_Volumes, NR_Peso, VL_Mercadoria, VL_Calculo, PE_Desconto, VL_Cotacao, DM_Coleta, TX_Observacao) ");
		buff.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		/*
		 * Define os dados do SQL
		* e executa o insert no banco.
		*/
		try
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setInt(1, getOID());
			pstmt.setInt(2, getOID_Unidade());
			pstmt.setInt(3, getOID_Modal());
			pstmt.setString(4, getOID_Pessoa());
			pstmt.setInt(5, getOID_Cidade());
			pstmt.setInt(6, getOID_Cidade_Destino());
			pstmt.setInt(7, getNR_Cotacao());
			pstmt.setString(8, this.DT_Cotacao);
			pstmt.setString(9, getNM_Solicitante());
			pstmt.setInt(10, getQT_Volumes());
			pstmt.setInt(11, getNR_Peso());
			pstmt.setDouble(12, getVL_Mercadoria());
			pstmt.setDouble(13, getVL_Calculo());
			pstmt.setDouble(14, getPE_Desconto());
			pstmt.setDouble(15, getVL_Cotacao());
			pstmt.setString(16, getDM_Coleta());
			pstmt.setString(17, getTX_Observacao());
			pstmt.executeUpdate();

			/*
			 * Define o update.
			*/

			setNR_Cotacao(getNR_Cotacao() + 1);

			buff.delete(0,buff.length());

			buff.append("UPDATE Parametros_Filiais SET NR_Proxima_Cotacao=? ");
			buff.append("WHERE OID_Unidade =?");

			PreparedStatement pstmt1 =
				conn.prepareStatement(buff.toString());

			pstmt1.setInt(1, getNR_Cotacao());
			pstmt1.setInt(2, getOID_Unidade());
			pstmt1.executeUpdate();

			setNR_Cotacao(getNR_Cotacao() - 1);

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
			// passando como parâmetro o NM_Cotacao do DSN
			// o NM_Cotacao de usuário e a senha do banco.
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
		buff.append("UPDATE Cotacoes SET OID_Modal=?, OID_Cidade=?, OID_Cidade_Destino=?, NM_Solicitante=?, QT_Volumes=?, NR_Peso=?, VL_Mercadoria=?, VL_Calculo=?, PE_Desconto=?, VL_Cotacao=?, DM_Coleta=?, TX_Observacao=?, DT_Cotacao=? ");
		buff.append("WHERE OID_Cotacao=?");
		/*
		 * Define os dados do SQL
		* e executa o insert no banco.
		*/
		try
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());

			pstmt.setInt(1, getOID_Modal());
			pstmt.setInt(2, getOID_Cidade());
			pstmt.setInt(3, getOID_Cidade_Destino());
			pstmt.setString(4, getNM_Solicitante());
			pstmt.setInt(5, getQT_Volumes());
			pstmt.setInt(6, getNR_Peso());
			pstmt.setDouble(7, getVL_Mercadoria());
			pstmt.setDouble(8, getVL_Calculo());
			pstmt.setDouble(9, getPE_Desconto());
			pstmt.setDouble(10, getVL_Cotacao());
			pstmt.setString(11, getDM_Coleta());
			pstmt.setString(12, getTX_Observacao());
			pstmt.setString(13, this.DT_Cotacao);
			pstmt.setInt(14, getOID());
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
			// passando como parâmetro o NM_Cotacao do DSN
			// o NM_Cotacao de usuário e a senha do banco.
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
		buff.append("DELETE FROM Cotacoes ");
		buff.append("WHERE OID_Cotacao=?");
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

	public static final CotacaoBean getByOID(int oid)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		*/
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Cotacao do DSN
			// o NM_Cotacao de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		CotacaoBean p = new CotacaoBean();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Cotacao, OID_Unidade, OID_Modal, OID_Pessoa, OID_Cidade, OID_Cidade_Destino, NR_Cotacao, DT_Cotacao, NM_Solicitante, QT_Volumes, NR_Peso, VL_Mercadoria, VL_Calculo, PE_Desconto, VL_Cotacao, DM_Coleta, TX_Observacao, Dt_Stamp, Usuario_Stamp, Dm_Stamp ");
			buff.append("FROM Cotacoes ");
			buff.append("WHERE OID_Cotacao= ");
			buff.append(oid);

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				p.setOID(cursor.getInt(1));
				p.setOID_Unidade(cursor.getInt(2));
				p.setOID_Modal(cursor.getInt(3));
				p.setOID_Pessoa(cursor.getString(4));
				p.setOID_Cidade(cursor.getInt(5));
				p.setOID_Cidade_Destino(cursor.getInt(6));
				p.setNR_Cotacao(cursor.getInt(7));
				p.setDT_Cotacao(cursor.getString(8));
				p.setNM_Solicitante(cursor.getString(9));
				p.setQT_Volumes(cursor.getInt(10));
				p.setNR_Peso(cursor.getInt(11));
				p.setVL_Mercadoria(cursor.getDouble(12));
				p.setVL_Calculo(cursor.getDouble(13));
				p.setPE_Desconto(cursor.getDouble(14));
				p.setVL_Cotacao(cursor.getDouble(15));
				p.setDM_Coleta(cursor.getString(16));
				p.setTX_Observacao(cursor.getString(17));
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

	public static final CotacaoBean getByNR_Cotacao(int NR_Cotacao)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		*/
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Cotacao do DSN
			// o NM_Cotacao de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		CotacaoBean p = new CotacaoBean();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Cotacao, OID_Unidade, OID_Modal, OID_Pessoa, OID_Cidade, OID_Cidade_Destino, NR_Cotacao, DT_Cotacao, NM_Solicitante, QT_Volumes, NR_Peso, VL_Mercadoria, VL_Calculo, PE_Desconto, VL_Cotacao, DM_Coleta, TX_Observacao, Dt_Stamp, Usuario_Stamp, Dm_Stamp ");
			buff.append("FROM Cotacoes ");
			buff.append("WHERE NR_Cotacao = ");
			buff.append(NR_Cotacao);

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				p.setOID(cursor.getInt(1));
				p.setOID_Unidade(cursor.getInt(2));
				p.setOID_Modal(cursor.getInt(3));
				p.setOID_Pessoa(cursor.getString(4));
				p.setOID_Cidade(cursor.getInt(5));
				p.setOID_Cidade_Destino(cursor.getInt(6));
				p.setNR_Cotacao(cursor.getInt(7));
				p.setDT_Cotacao(cursor.getString(8));
				p.setNM_Solicitante(cursor.getString(9));
				p.setQT_Volumes(cursor.getInt(10));
				p.setNR_Peso(cursor.getInt(11));
				p.setVL_Mercadoria(cursor.getDouble(12));
				p.setVL_Calculo(cursor.getDouble(13));
				p.setPE_Desconto(cursor.getDouble(14));
				p.setVL_Cotacao(cursor.getDouble(15));
				p.setDM_Coleta(cursor.getString(16));
				p.setTX_Observacao(cursor.getString(17));
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

	public static final List getByNR_Cotacao_Lista(int NR_Cotacao)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Cotacao do DSN
			// o NM_Cotacao de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		List Cotacoes_Lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append(" SELECT OID_Cotacao, OID_Unidade, OID_Modal, Cotacoes.OID_Pessoa,  Cotacoes.OID_Cidade, OID_Cidade_Destino, NR_Cotacao, DT_Cotacao, NM_Solicitante, QT_Volumes, NR_Peso, VL_Mercadoria, VL_Calculo, PE_Desconto, VL_Cotacao, DM_Coleta, TX_Observacao, NM_Razao_Social ");
			buff.append(" FROM Cotacoes, Pessoas WHERE Cotacoes.OID_Pessoa = Pessoas.OID_Pessoa ");
			buff.append(" AND NR_Cotacao = ");
			buff.append(NR_Cotacao);
			buff.append(" ORDER BY Cotacoes.NR_Cotacao ");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				CotacaoBean p = new CotacaoBean();
				p.setOID(cursor.getInt(1));
				p.setOID_Unidade(cursor.getInt(2));
				p.setOID_Modal(cursor.getInt(3));
				p.setOID_Pessoa(cursor.getString(4));
				p.setOID_Cidade(cursor.getInt(5));
				p.setOID_Cidade_Destino(cursor.getInt(6));
				p.setNR_Cotacao(cursor.getInt(7));
				p.setDT_Cotacao(cursor.getString(8));
				p.setNM_Solicitante(cursor.getString(9));
				p.setQT_Volumes(cursor.getInt(10));
				p.setNR_Peso(cursor.getInt(11));
				p.setVL_Mercadoria(cursor.getDouble(12));
				p.setVL_Calculo(cursor.getDouble(13));
				p.setPE_Desconto(cursor.getDouble(14));
				p.setVL_Cotacao(cursor.getDouble(15));
				p.setDM_Coleta(cursor.getString(16));
				p.setTX_Observacao(cursor.getString(17));
				p.setNM_Razao_Social(cursor.getString(18));
				Cotacoes_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return Cotacoes_Lista;
	}

	public static final List getByOID_Cliente_Lista(String OID_Cliente)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		*/
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Cotacao do DSN
			// o NM_Cotacao de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		List Cotacoes_Lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append(" SELECT OID_Cotacao, OID_Unidade, OID_Modal, Cotacoes.OID_Pessoa,  Cotacoes.OID_Cidade, OID_Cidade_Destino, NR_Cotacao, DT_Cotacao, NM_Solicitante, QT_Volumes, NR_Peso, VL_Mercadoria, VL_Calculo, PE_Desconto, VL_Cotacao, DM_Coleta, TX_Observacao, NM_Razao_Social ");
			buff.append(" FROM Cotacoes, Pessoas WHERE Cotacoes.OID_Pessoa = Pessoas.OID_Pessoa ");
			buff.append(" AND Pessoas.OID_Pessoa = '");
			buff.append(OID_Cliente);
			buff.append("'");
			buff.append(" ORDER BY Cotacoes.NR_Cotacao ");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				CotacaoBean p = new CotacaoBean();
				p.setOID(cursor.getInt(1));
				p.setOID_Unidade(cursor.getInt(2));
				p.setOID_Modal(cursor.getInt(3));
				p.setOID_Pessoa(cursor.getString(4));
				p.setOID_Cidade(cursor.getInt(5));
				p.setOID_Cidade_Destino(cursor.getInt(6));
				p.setNR_Cotacao(cursor.getInt(7));
				p.setDT_Cotacao(cursor.getString(8));
				p.setNM_Solicitante(cursor.getString(9));
				p.setQT_Volumes(cursor.getInt(10));
				p.setNR_Peso(cursor.getInt(11));
				p.setVL_Mercadoria(cursor.getDouble(12));
				p.setVL_Calculo(cursor.getDouble(13));
				p.setPE_Desconto(cursor.getDouble(14));
				p.setVL_Cotacao(cursor.getDouble(15));
				p.setDM_Coleta(cursor.getString(16));
				p.setTX_Observacao(cursor.getString(17));
				p.setNM_Razao_Social(cursor.getString(18));
				Cotacoes_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return Cotacoes_Lista;
	}

	public static final List getByNM_Cotacao(String NM_Cotacao)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		*/
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Cotacao do DSN
			// o NM_Cotacao de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		List Cotacoes_Lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Cotacao, OID_Unidade, OID_Modal, OID_Pessoa, OID_Cidade, OID_Cidade_Destino, NR_Cotacao, DT_Cotacao, NM_Solicitante, QT_Volumes, NR_Peso, VL_Mercadoria, VL_Calculo, PE_Desconto, VL_Cotacao, DM_Coleta, TX_Observacao, Dt_Stamp, Usuario_Stamp, Dm_Stamp ");
			buff.append("FROM Cotacoes ");
			buff.append("WHERE NM_Cotacao LIKE'");
			buff.append(NM_Cotacao);
			buff.append("%'");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				CotacaoBean p = new CotacaoBean();
				p.setOID(cursor.getInt(1));
				p.setOID_Unidade(cursor.getInt(2));
				p.setOID_Modal(cursor.getInt(3));
				p.setOID_Pessoa(cursor.getString(4));
				p.setOID_Cidade(cursor.getInt(5));
				p.setOID_Cidade_Destino(cursor.getInt(6));
				p.setNR_Cotacao(cursor.getInt(7));
				p.setDT_Cotacao(cursor.getString(8));
				p.setNM_Solicitante(cursor.getString(9));
				p.setQT_Volumes(cursor.getInt(10));
				p.setNR_Peso(cursor.getInt(11));
				p.setVL_Mercadoria(cursor.getDouble(12));
				p.setVL_Calculo(cursor.getDouble(13));
				p.setPE_Desconto(cursor.getDouble(14));
				p.setVL_Cotacao(cursor.getDouble(15));
				p.setDM_Coleta(cursor.getString(16));
				p.setTX_Observacao(cursor.getString(17));
				Cotacoes_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return Cotacoes_Lista;
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

		List Cotacoes_Lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append(" SELECT OID_Cotacao, OID_Unidade, OID_Modal, Cotacoes.OID_Pessoa,  Cotacoes.OID_Cidade, OID_Cidade_Destino, NR_Cotacao, DT_Cotacao, NM_Solicitante, QT_Volumes, NR_Peso, VL_Mercadoria, VL_Calculo, PE_Desconto, VL_Cotacao, DM_Coleta, TX_Observacao, NM_Razao_Social ");
			buff.append(" FROM Cotacoes, Pessoas WHERE Cotacoes.OID_Pessoa = Pessoas.OID_Pessoa ");
			buff.append(" ORDER BY Cotacoes.NR_Cotacao ");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				CotacaoBean p = new CotacaoBean();
				p.setOID(cursor.getInt(1));
				p.setOID_Unidade(cursor.getInt(2));
				p.setOID_Modal(cursor.getInt(3));
				p.setOID_Pessoa(cursor.getString(4));
				p.setOID_Cidade(cursor.getInt(5));
				p.setOID_Cidade_Destino(cursor.getInt(6));
				p.setNR_Cotacao(cursor.getInt(7));
				p.setDT_Cotacao(cursor.getString(8));
				p.setNM_Solicitante(cursor.getString(9));
				p.setQT_Volumes(cursor.getInt(10));
				p.setNR_Peso(cursor.getInt(11));
				p.setVL_Mercadoria(cursor.getDouble(12));
				p.setVL_Calculo(cursor.getDouble(13));
				p.setPE_Desconto(cursor.getDouble(14));
				p.setVL_Cotacao(cursor.getDouble(15));
				p.setDM_Coleta(cursor.getString(16));
				p.setTX_Observacao(cursor.getString(17));
				p.setNM_Razao_Social(cursor.getString(18));
				Cotacoes_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return Cotacoes_Lista;
	}

	public static void main(String args[])
		throws Exception
	{
		//CotacaoBean pp = new CotacaoBean();
		//pp.setOID(2);
		//pp.setNR_Cotacao(1);
		//pp.insert();

		CotacaoBean p = getByOID(1);
		// //// System.out.println(p.getOID());
		// //// System.out.println(p.getNR_Cotacao());
		// //// System.out.println(p.getDT_Cotacao());



	}
}


