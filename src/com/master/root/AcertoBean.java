package com.master.root;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.OracleConnection2;

import com.master.bd.Acerto_ViagemBD;
import com.master.rl.AcertoRL;
import com.master.rl.Acerto_ViagemRL;
import com.master.util.EnviaPDF;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.bd.ExecutaSQL;

public class AcertoBean extends BancoUtil implements Serializable  {
    ExecutaSQL executasql;

	private long NR_Acerto;

	private String DT_Emissao;

	private String DT_Saida;

	private String DT_Chegada;

	private long NR_Kilometragem_Chegada;

	private double VL_Total_Frete;

	private double VL_Adiantamento_Viagem;

	private double VL_Total_Despesas_Faturadas;

	private double VL_Total_Despesas_Pagas;

	private String TX_Observacao;

	private String Usuario_Stamp;

	private String Dt_Stamp;

	private String Dm_Stamp;

	private long oid;

	private long oid_Ordem_Servico;

	private long oid_Unidade;

	private String oid_Motorista;

	private String oid_Veiculo;

	private double VL_Comissao;

	private double PE_Comissao;

	private double VL_Frete_Peso;

	private double VL_Frete_Terceiro;

	private double VL_Frete_Proprio;
	private long oid_Movimento_Conta_Corrente;
	private double VL_Cotacao;
	private String DM_Situacao;
	private double PE_Base_Comissao;
	private double VL_Base_Comissao;
	private String DM_Impresso;
	private double VL_Recebido;
	private double VL_Devolvido;

  public AcertoBean() {
		NR_Kilometragem_Chegada = 0;
		TX_Observacao = " ";
		Usuario_Stamp = "";
		Dm_Stamp = "";
		oid = 0;
		oid_Unidade = 0;
		oid_Ordem_Servico = 0;
		oid_Motorista = "";
		oid_Veiculo = "";
	}

	public long getNR_Acerto() {
		return NR_Acerto;
	}

	public void setNR_Acerto(long NR_Acerto) {
		this.NR_Acerto = NR_Acerto;
	}

	public String getDT_Emissao() {
		FormataDataBean DataFormatada = new FormataDataBean();
		DataFormatada.setDT_FormataData(DT_Emissao);
		DT_Emissao = DataFormatada.getDT_FormataData();

		return DT_Emissao;
	}

	public void setDT_Emissao(String DT_Emissao) {
		this.DT_Emissao = DT_Emissao;
	}

	public String getDT_Saida() {
		FormataDataBean DataFormatada = new FormataDataBean();
		DataFormatada.setDT_FormataData(DT_Saida);
		DT_Saida = DataFormatada.getDT_FormataData();

		return DT_Saida;
	}

	public void setDT_Saida(String DT_Saida) {
		this.DT_Saida = DT_Saida;
	}

	public String getDT_Chegada() {
		FormataDataBean DataFormatada = new FormataDataBean();
		DataFormatada.setDT_FormataData(DT_Chegada);
		DT_Chegada = DataFormatada.getDT_FormataData();

		return DT_Chegada;
	}

	public void setDT_Chegada(String DT_Chegada) {
		this.DT_Chegada = DT_Chegada;
	}

	public long getNR_Kilometragem_Chegada() {
		return NR_Kilometragem_Chegada;
	}

	public void setNR_Kilometragem_Chegada(long NR_Kilometragem_Chegada) {
		this.NR_Kilometragem_Chegada = NR_Kilometragem_Chegada;
	}

	public long getOID_Ordem_Servico() {
		return oid_Ordem_Servico;
	}

	public void setOID_Ordem_Servico(long n) {
		this.oid_Ordem_Servico = n;
	}

	public double getVL_Total_Frete() {
		return VL_Total_Frete;
	}

	public void setVL_Total_Frete(double n) {
		this.VL_Total_Frete = n;
	}

	public double getVL_Adiantamento_Viagem() {
		return VL_Adiantamento_Viagem;
	}

	public void setVL_Adiantamento_Viagem(double n) {
		this.VL_Adiantamento_Viagem = n;
	}

	public double getVL_Total_Despesas_Faturadas() {
		return VL_Total_Despesas_Faturadas;
	}

	public void setVL_Total_Despesas_Faturadas(double n) {
		this.VL_Total_Despesas_Faturadas = n;
	}

	public double getVL_Total_Despesas_Pagas() {
		return VL_Total_Despesas_Pagas;
	}

	public void setVL_Total_Despesas_Pagas(double n) {
		this.VL_Total_Despesas_Pagas = n;
	}

	public String getTX_Observacao() {
		return TX_Observacao;
	}

	public void setTX_Observacao(String n) {
		this.TX_Observacao = n;
	}

	public String getOID_Motorista() {
		return oid_Motorista;
	}

	public void setOID_Motorista(String n) {
		this.oid_Motorista = n;
	}

	public String getOID_Veiculo() {
		return oid_Veiculo;
	}

	public void setOID_Veiculo(String n) {
		this.oid_Veiculo = n;
	}

	public long getOID_Unidade() {
		return oid_Unidade;
	}

	public void setOID_Unidade(long n) {
		this.oid_Unidade = n;
	}
    public double getPE_Base_Comissao() {
        return this.PE_Base_Comissao;
    }
    public void setPE_Base_Comissao(double base_Comissao) {
        this.PE_Base_Comissao = base_Comissao;
    }
    public double getVL_Base_Comissao() {
        return this.VL_Base_Comissao;
    }
    public void setVL_Base_Comissao(double base_Comissao) {
        this.VL_Base_Comissao = base_Comissao;
    }
	/*
	 * ---------------- Bloco Padrão para Todas Classes ------------------
	 */
	public String getUsuario_Stamp() {
		return Usuario_Stamp;
	}

	public void setUsuario_Stamp(String Usuario_Stamp) {
		this.Usuario_Stamp = Usuario_Stamp;
	}

	public String getDt_Stamp() {
		return Dt_Stamp;
	}

	public void setDt_Stamp(String Dt_Stamp) {
		this.Dt_Stamp = Dt_Stamp;
	}

	public String getDm_Stamp() {
		return Dm_Stamp;
	}

	public void setDm_Stamp(String Dm_Stamp) {
		this.Dm_Stamp = Dm_Stamp;
	}

	public long getOID() {
		return oid;
	}

	public void setOID(long n) {
		this.oid = n;
	}


	public void atualizaValores(Movimento_Ordem_ServicoBean valores) throws Exception {
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try {
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Acerto do DSN
			// o NM_Acerto de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		/*
		 * Define o update.
		 */
		StringBuffer buff = new StringBuffer();
		buff.append("UPDATE Acertos SET VL_Total_Frete=?, VL_Total_Despesas_Pagas=?, VL_Total_Despesas_Faturadas=?, VL_Adiantamento_Viagem=?, VL_Frete_Proprio=?, VL_Frete_Terceiro=?  ");
		buff.append("WHERE OID_Acerto=?");

                /*
		 * Define os dados do SQL e executa o insert no banco.
		 */
		try {
			PreparedStatement pstmt = conn.prepareStatement(buff.toString());

			pstmt.setDouble(1, valores.getVL_Total_Frete());
			pstmt.setDouble(2, valores.getVL_Movimento_Pago());
			pstmt.setDouble(3, valores.getVL_Movimento_Faturado());
			pstmt.setDouble(4, valores.getVL_Adiantamento_Viagem());
			pstmt.setDouble(5, valores.getVL_Frete_Proprio());
			pstmt.setDouble(6, valores.getVL_Frete_Terceiro());
			pstmt.setLong(7, getOID());
			pstmt.executeUpdate();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
			throw e;
		}
		/*
		 * Faz o commit e fecha a conexão.
		 */
		try {
			conn.commit();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void atualiza_Conta_Corrente() throws Exception {
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try {
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Acerto do DSN
			// o NM_Acerto de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		/*
		 * Define o update.
		 */
		StringBuffer buff = new StringBuffer();
		buff.append("UPDATE Acertos SET OID_Movimento_Conta_Corrente=? ");
		buff.append("WHERE OID_Acerto=?");
		/*
		 * Define os dados do SQL e executa o insert no banco.
		 */
		try {
			PreparedStatement pstmt = conn.prepareStatement(buff.toString());

			pstmt.setLong(1, getOid_Movimento_Conta_Corrente());
			pstmt.setLong(2, getOID());
			pstmt.executeUpdate();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
			throw e;
		}
		/*
		 * Faz o commit e fecha a conexão.
		 */
		try {
			conn.commit();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void delete() throws Exception {
		inicioTransacao();
		try {
		    executasql = sql;

		    String sqlUpdateConhecimentos =
		        " Update Conhecimentos set oid_Acerto=null " +
		        " WHERE OID_Acerto = " + getOID();
		    executasql.executarUpdate(sqlUpdateConhecimentos);

		    String sqlUpdateOF =
		        " Update Ordens_Fretes set oid_Acerto = null " +
		        " WHERE OID_Acerto = " + getOID();
		    executasql.executarUpdate(sqlUpdateOF);

		    String sql =
		        " DELETE FROM Acertos " +
		        " WHERE OID_Acerto = " + getOID();
		    executasql.executarUpdate(sql);

		    String sqlUpdateOS =
		        " UPDATE Ordens_Servicos SET DT_Encerramento = null " +
		        " WHERE OID_Ordem_Servico = " + getOID_Ordem_Servico();
		    executasql.executarUpdate(sqlUpdateOS);

		    fimTransacao(true);
		} catch (SQLException e) {
		    abortaTransacao();
		    throw e;
		}
	}

	public AcertoBean getByOid(long oid)
	throws Excecoes {
	    inicioTransacao();
	    try {
	        executasql = sql;
			try {
			    AcertoBean p = new AcertoBean();
			    String sqlBusca =
			        " SELECT Acertos.OID_Acerto, " +
			        "        Acertos.OID_Ordem_Servico, " +
			        "        Acertos.OID_Motorista, " +
			        "        Acertos.NR_Acerto, " +
			        "        Acertos.DT_Emissao, " +
			        "        Acertos.DT_Saida,  " +
			        "        Acertos.DT_Chegada, " +
			        "        Acertos.NR_Kilometragem_Chegada, " +
			        "        Acertos.Dt_Stamp, " +
			        "        Acertos.Usuario_Stamp, " +
			        "        Acertos.Dm_Stamp, " +
			        "        Acertos.VL_Total_Frete, " +
			        "        Acertos.VL_Total_Despesas_Pagas, " +
			        "        Acertos.VL_Total_Despesas_Faturadas, " +
			        "        Acertos.TX_Observacao, " +
			        "        Acertos.VL_Adiantamento_Viagem, " +
			        "        Acertos.VL_Frete_Proprio, " +
			        "        Acertos.VL_Frete_Terceiro, " +
			        "        Acertos.VL_Comissao, " +
			        "        Acertos.PE_Comissao, " +
			        "        Acertos.oid_Movimento_Conta_Corrente, " +
			        "        Acertos.DM_Situacao, " +
			        "        Acertos.PE_Base_Comissao, " +
			        "        Acertos.VL_Base_Comissao, " +
			        "        Acertos.VL_Frete_Peso, " +
			        "        Acertos.DM_Impresso, " +
			        "        Acertos.VL_Recebido, " +
			        "        Acertos.VL_Devolvido, " +
			        "        Ordens_Servicos.oid_Veiculo " +
			        " FROM Acertos " +
			        "      inner join Ordens_Servicos " +
			        "        on Acertos.oid_Ordem_Servico = Ordens_Servicos.oid_Ordem_Servico " +
			        " WHERE OID_Acerto = " + oid;
				ResultSet cursor = executasql.executarConsulta(sqlBusca);

				if (cursor.next()) {
				    p.setOID(cursor.getLong(1));
					p.setOID_Ordem_Servico(cursor.getLong(2));
					p.setOID_Motorista(cursor.getString(3));
					p.setNR_Acerto(cursor.getLong(4));
					p.setDT_Emissao(cursor.getString(5));
					p.setDT_Saida(cursor.getString(6));
					p.setDT_Chegada(cursor.getString(7));
					p.setNR_Kilometragem_Chegada(cursor.getLong(8));
					p.setDt_Stamp(cursor.getString(9));
					p.setUsuario_Stamp(cursor.getString(10));
					p.setDm_Stamp(cursor.getString(11));
					p.setVL_Total_Frete(cursor.getDouble(12));
					p.setVL_Total_Despesas_Pagas(cursor.getDouble(13));
					p.setVL_Total_Despesas_Faturadas(cursor.getDouble(14)); 
					p.setTX_Observacao(cursor.getString(15));
					p.setVL_Adiantamento_Viagem(cursor.getDouble(16));
					p.setVL_Frete_Proprio(cursor.getDouble(17));
					p.setVL_Frete_Terceiro(cursor.getDouble(18));
					p.setVL_Comissao(cursor.getDouble(19));
					p.setPE_Comissao(cursor.getDouble(20));
					p.setOid_Movimento_Conta_Corrente(cursor.getLong(21));
	                p.setDM_Situacao(cursor.getString(22));
	                p.setPE_Base_Comissao(cursor.getDouble("PE_Base_Comissao"));
	                p.setVL_Base_Comissao(cursor.getDouble("VL_Base_Comissao"));
	                p.setVL_Frete_Peso(cursor.getDouble("VL_Frete_Peso"));
	                p.setDM_Impresso(cursor.getString("DM_Impresso"));
	                p.setOID_Veiculo("oid_Veiculo");
	                p.setVL_Recebido(cursor.getDouble("VL_Recebido"));
	                p.setVL_Devolvido(cursor.getDouble("VL_Devolvido"));
				}
				return p;
			} catch (SQLException e) {
			    throw new Excecoes(e.getMessage(), e, getClass().getName(), "getByOID(long oid)");
			}
	    } finally {
	        fimTransacao(false);
	    }
	}

	public static final AcertoBean getByOID(long oid) throws Exception {
		return new AcertoBean().getByOid(oid);
	}

	public static final AcertoBean getByNR_Acerto(long NR_Acerto)
			throws Exception {
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try {
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Acerto do DSN
			// o NM_Acerto de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		AcertoBean p = new AcertoBean();
		try {
			StringBuffer buff = new StringBuffer();
			buff.append(" SELECT OID_Acerto, OID_Ordem_Servico, OID_Motorista, NR_Acerto, DT_Emissao, DT_Saida,  DT_Chegada, NR_Kilometragem_Chegada, Dt_Stamp, Usuario_Stamp, Dm_Stamp, VL_Total_Frete, VL_Total_Despesas_Pagas, VL_Total_Despesas_Faturadas, TX_Observacao, VL_Adiantamento_Viagem, DM_Impresso ");
			buff.append(" FROM Acertos ");
			buff.append(" WHERE Acertos.NR_Acerto =");
			buff.append(NR_Acerto);

			Statement stmt = conn.createStatement();
			ResultSet cursor = stmt.executeQuery(buff.toString());

			while (cursor.next()) {
				p.setOID(cursor.getLong(1));
				p.setOID_Ordem_Servico(cursor.getLong(2));
				p.setOID_Motorista(cursor.getString(3));
				p.setNR_Acerto(cursor.getLong(4));
				p.setDT_Emissao(cursor.getString(5));
				p.setDT_Saida(cursor.getString(6));
				p.setDT_Chegada(cursor.getString(7));
				p.setNR_Kilometragem_Chegada(cursor.getLong(8));
				p.setDt_Stamp(cursor.getString(9));
				p.setUsuario_Stamp(cursor.getString(10));
				p.setDm_Stamp(cursor.getString(11));
				p.setVL_Total_Frete(cursor.getDouble(12));
				p.setVL_Total_Despesas_Pagas(cursor.getDouble(13));
				p.setVL_Total_Despesas_Faturadas(cursor.getDouble(14));
				p.setTX_Observacao(cursor.getString(15));
				p.setVL_Adiantamento_Viagem(cursor.getDouble(16));
				p.setDM_Impresso(cursor.getString("DM_Impresso"));
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

	public static final List getByOID_Ordem_Servico_Lista(long OID_Ordem_Servico)
			throws Exception {
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try {
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Acerto do DSN
			// o NM_Acerto de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		List Acertos_Lista = new ArrayList();
		try {
			StringBuffer buff = new StringBuffer();
			buff.append(" SELECT OID_Acerto, OID_Ordem_Servico, OID_Motorista, NR_Acerto, DT_Emissao, DT_Saida,  DT_Chegada, NR_Kilometragem_Chegada, Dt_Stamp, Usuario_Stamp, Dm_Stamp, VL_Total_Frete, VL_Total_Despesas_Pagas, VL_Total_Despesas_Faturadas, TX_Observacao, VL_Adiantamento_Viagem, DM_Impresso ");
			buff.append(" FROM Acertos ");
			buff.append(" WHERE Acertos.OID_Ordem_Servico =");
			buff.append(OID_Ordem_Servico);

			Statement stmt = conn.createStatement();
			ResultSet cursor = stmt.executeQuery(buff.toString());

			while (cursor.next()) {
				AcertoBean p = new AcertoBean();
				p.setOID(cursor.getLong(1));
				p.setOID_Ordem_Servico(cursor.getLong(2));
				p.setOID_Motorista(cursor.getString(3));
				p.setNR_Acerto(cursor.getLong(4));
				p.setDT_Emissao(cursor.getString(5));
				p.setDT_Saida(cursor.getString(6));
				p.setDT_Chegada(cursor.getString(7));
				p.setNR_Kilometragem_Chegada(cursor.getLong(8));
				p.setDt_Stamp(cursor.getString(9));
				p.setUsuario_Stamp(cursor.getString(10));
				p.setDm_Stamp(cursor.getString(11));
				p.setVL_Total_Frete(cursor.getDouble(12));
				p.setVL_Total_Despesas_Pagas(cursor.getDouble(13));
				p.setVL_Total_Despesas_Faturadas(cursor.getDouble(14));
				p.setTX_Observacao(cursor.getString(15));
				p.setVL_Adiantamento_Viagem(cursor.getDouble(16));
				p.setDM_Impresso(cursor.getString("DM_Impresso"));
				Acertos_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return Acertos_Lista;
	}

	public static final AcertoBean getByOID_Ordem_Servico(long OID_Ordem_Servico)
	throws Excecoes {
	    Iterator iterator;
        try {
            iterator = getByOID_Ordem_Servico_Lista(OID_Ordem_Servico).iterator();
        } catch (Exception e) {
            throw new Excecoes(e.getMessage(), e, AcertoBean.class.getName(), "getByOID_Ordem_Servico(long OID_Ordem_Servico)");
        }
	    if (iterator.hasNext()) {
	        return (AcertoBean)iterator.next();
	    } else return new AcertoBean();
	}

	public static final List getAll() throws Exception {
		Connection conn = null;
		try {
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o nome do DSN
			// o nome de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		List Acertos_Lista = new ArrayList();
		try {
			StringBuffer buff = new StringBuffer();
			buff.append(" SELECT OID_Acerto, OID_Ordem_Servico, OID_Motorista, NR_Acerto, DT_Emissao, DT_Saida,  DT_Chegada, NR_Kilometragem_Chegada, Dt_Stamp, Usuario_Stamp, Dm_Stamp, VL_Total_Frete, VL_Total_Despesas_Pagas, VL_Total_Despesas_Faturadas, TX_Observacao, VL_Adiantamento_Viagem, DM_Impresso ");
			buff.append(" FROM Acertos ");
			buff.append(" ORDER BY Acertos.NR_Acerto ");

			Statement stmt = conn.createStatement();
			ResultSet cursor = stmt.executeQuery(buff.toString());

			while (cursor.next()) {
				AcertoBean p = new AcertoBean();
				p.setOID(cursor.getLong(1));
				p.setOID_Ordem_Servico(cursor.getLong(2));
				p.setOID_Motorista(cursor.getString(3));
				p.setNR_Acerto(cursor.getLong(4));
				p.setDT_Emissao(cursor.getString(5));
				p.setDT_Saida(cursor.getString(6));
				p.setDT_Chegada(cursor.getString(7));
				p.setNR_Kilometragem_Chegada(cursor.getLong(8));
				p.setDt_Stamp(cursor.getString(9));
				p.setUsuario_Stamp(cursor.getString(10));
				p.setDm_Stamp(cursor.getString(11));
				p.setVL_Total_Frete(cursor.getDouble(12));
				p.setVL_Total_Despesas_Pagas(cursor.getDouble(13));
				p.setVL_Total_Despesas_Faturadas(cursor.getDouble(14));
				p.setTX_Observacao(cursor.getString(15));
				p.setVL_Adiantamento_Viagem(cursor.getDouble(16));
				p.setDM_Impresso(cursor.getString("DM_Impresso"));
				Acertos_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Acertos_Lista;
	}
	
	public void geraRelAcerto_de_Contas(javax.servlet.http.HttpServletRequest req,
	        javax.servlet.http.HttpServletResponse res)
	throws Excecoes {
	    this.setOID(new Long(req.getParameter("oid_Acerto")).longValue());
	    AcertoBean acerto;
	    try {
	        acerto = AcertoBean.getByOID(getOID());
	    } catch (Exception e) {
	        throw new Excecoes(e.getMessage(), e, getClass().getName(), "geraRelAcerto_de_Contas(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res)");
	    }
	    String sqlBusca =
	        " SELECT Ordens_Servicos.OID_Ordem_Servico,	" +
	        "        Ordens_Servicos.OID_Unidade, " +
	        "        Ordens_Servicos.OID_Tipo_Servico, " +
	        "        Ordens_Servicos.NR_Ordem_Servico, " +
	        "        Ordens_Servicos.NR_Kilometragem, " +
	        "        Ordens_Servicos.DT_Ordem_Servico, " +
	        "        Ordens_Servicos.DT_Encerramento, " +
	        "        Ordens_Servicos.NR_Meses_Amortizacao, " +
	        "        Ordens_Servicos.VL_Previsto, " +
	        "        Ordens_Servicos.TX_Observacao, " +
	        "        Tipos_Servicos.CD_Tipo_Servico, " +
	        "        Tipos_Servicos.NM_Tipo_Servico, " +
	        "        Tipos_Servicos.DM_Medida, " +
	        "        Tipos_Servicos.CD_Tipo_Servico, " +
	        "        Tipos_Servicos.DM_Tipo_Despesa, " +
	        "        Unidades.CD_Unidade, " +
	        "        Ordens_Servicos.OID_Veiculo, " +
	        "        Pessoa_Fornecedor.NM_Razao_Social as NM_Fornecedor, " +
	        "        Pessoa_Motorista.NM_Razao_Social as NM_Motorista , " +
	        "        Acertos.OID_Acerto, " +
	        "        Acertos.OID_Motorista, " +
	        "        Acertos.NR_Acerto, " +
	        "        Acertos.DT_Emissao, " +
	        "        Acertos.DT_Saida, " +
	        "        Acertos.DT_Chegada, " +
	        "        Acertos.NR_Kilometragem_Chegada, " +
	        "        Acertos.VL_Total_Frete, " +
	        "        Acertos.VL_Total_Despesas_Pagas, " +
	        "        Acertos.VL_Total_Despesas_Faturadas, " +
	        "        Acertos.TX_Observacao as TX_Observacao_Acerto, " +
	        "        Acertos.VL_Adiantamento_Viagem, " +
	        "        Acertos.VL_Frete_Proprio, " +
	        "        Acertos.VL_Frete_Terceiro, " +
	        "        Acertos.VL_Comissao, " +
	        "        Acertos.PE_Comissao, " +
	        "        Acertos.PE_Base_Comissao, " +
	        "        Acertos.VL_Base_Comissao, " +
	        "        Acertos.VL_Recebido, " +
	        "        Acertos.VL_Devolvido, " +
	        "        Movimentos_Ordens_Servicos.DT_Movimento_Ordem_Servico, " +
	        "        Movimentos_Ordens_Servicos.NR_Documento, " +
	        "        Movimentos_Ordens_Servicos.NR_Quantidade, " +
	        "        Movimentos_Ordens_Servicos.NR_Kilometragem as Odometro, " +
	        "        Movimentos_Ordens_Servicos.DM_Faturado_Pago, " +
	        "        Movimentos_Ordens_Servicos.oid_Moeda, " +
	        "        Movimentos_Ordens_Servicos.VL_Movimento, " +
	        "        Movimentos_Ordens_Servicos.VL_Documento, " +
	        "        Acertos.VL_Cotacao as VL_Cotacao_Acerto, " +
	        "        Movimentos_Ordens_Servicos.VL_Cotacao as VL_Cotacao_Movimento, " +
	        "        Moedas.CD_Moeda " +
	        " FROM Ordens_Servicos, Unidades, Veiculos, Tipos_Servicos, Acertos, Pessoas Pessoa_Fornecedor, Pessoas Pessoa_Motorista, Movimentos_Ordens_Servicos, Moedas " +
	        " WHERE Ordens_Servicos.OID_Unidade = Unidades.OID_Unidade " +
	        "  AND Acertos.OID_Ordem_Servico = Ordens_Servicos.oid_ordem_servico " +
	        "  AND Movimentos_Ordens_Servicos.OID_Ordem_Servico = Ordens_Servicos.oid_ordem_servico " +
	        "  AND Movimentos_Ordens_Servicos.oid_Tipo_Servico = Tipos_Servicos.OID_Tipo_Servico " +
	        "  AND Movimentos_Ordens_Servicos.oid_Moeda = Moedas.oid_Moeda " +
	        "  AND Movimentos_Ordens_Servicos.oid_Pessoa = Pessoa_Fornecedor.OID_Pessoa " +
	        "  AND Ordens_Servicos.OID_Veiculo = Veiculos.OID_Veiculo " +
	        "  AND Acertos.oid_Motorista = Pessoa_Motorista.oid_pessoa " +
	        "  AND Acertos.Oid_Acerto = " + this.getOID() +
	        "  ORDER BY Acertos.oid_acerto, Ordens_servicos.oid_veiculo, Movimentos_Ordens_Servicos.DT_Movimento_Ordem_Servico ";
	    inicioTransacao();
	    try {
	        byte[] b;
	        ResultSet cursor = this.sql.executarConsulta(sqlBusca);
	        try {
	            b = new AcertoRL().geraRelAcerto_de_Contas(cursor);
	        } finally {
	            closeResultset(cursor);
	        }
	        new EnviaPDF().enviaBytes(req, res, b);
	        try {
	            if (!"S".equals(acerto.getDM_Impresso())) {
	                new VeiculoBean().atualizaKMAtual(acerto.getOID_Veiculo(), acerto.NR_Kilometragem_Chegada, this.sql);
	                atualizaAcertoImpresso(acerto.getOID(), sql);
	            }
	        } catch (Exception e) {
	            throw new Excecoes(e.getMessage(), e, getClass().getName(), "geraRelAcerto_de_Contas(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res)");
	        }
	        fimTransacao(true);
	    } catch (Excecoes e) {
	        abortaTransacao();
	        throw e;
	    }
	}


	private void atualizaAcertoImpresso(long oid_ACerto, ExecutaSQL sql)
	throws Excecoes {
	    String sqlUpdate =
	        " update Acertos " +
	        " set DM_Impresso = 'S' " +
	        " where Acertos.oid_Acerto = " + oid_ACerto;
	    try {
            sql.executarUpdate(sqlUpdate);
        } catch (SQLException e) {
            throw new Excecoes(e.getMessage(), e, getClass().getName(), "atualizaAcertoImpresso(long oid_ACerto, ExecutaSQL sql)");
        }
	}
	

	public byte[] geraRelAcertos_Veiculos(HttpServletRequest request, HttpServletResponse Response)
    throws Excecoes
{
    String DT_Inicial;
    String DT_Final;
    String DM_Relatorio;
    String sqlBusca;
    
    DT_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
    DT_Final = request.getParameter("FT_DT_Emissao_Final");
    String NR_Placa = request.getParameter("FT_NR_Placa");
    DM_Relatorio = request.getParameter("FT_DM_Relatorio");
    
    sqlBusca = " SELECT Ordens_Servicos.OID_Ordem_Servico, " +
    		   " Ordens_Servicos.OID_Unidade, Ordens_Servicos.OID_Tipo_Servico, " +
    		   " Ordens_Servicos.NR_Ordem_Servico, Ordens_Servicos.NR_Kilometragem, " +
    		   " Ordens_Servicos.DT_Ordem_Servico, Ordens_Servicos.DT_Encerramento, " +
    		   " Ordens_Servicos.NR_Meses_Amortizacao, Ordens_Servicos.VL_Previsto, " +
    		   " Ordens_Servicos.TX_Observacao,  Unidades.CD_Unidade, Ordens_Servicos.OID_Veiculo, " +
    		   " Acertos.OID_Acerto, Acertos.OID_Motorista, Acertos.NR_Acerto, Acertos.DT_Emissao, " +
    		   " Acertos.DT_Saida,  Acertos.DT_Chegada, Acertos.NR_Kilometragem_Chegada, " +
    		   " Acertos.VL_Total_Frete, Acertos.VL_Total_Despesas_Pagas, Acertos.VL_Total_Despesas_Faturadas, " +
    		   " Acertos.TX_Observacao as TX_Observacao_Acerto, Acertos.VL_Adiantamento_Viagem, " +
    		   " VL_Frete_Proprio, VL_Frete_Terceiro, VL_Comissao, PE_Comissao  " +
    		   " FROM Ordens_Servicos, Unidades, Veiculos, Acertos " +
    		   " WHERE Ordens_Servicos.OID_Unidade = Unidades.OID_Unidade  " +
    		   " AND Acertos.OID_Ordem_Servico = Ordens_Servicos.oid_ordem_servico  " +
    		   " AND Ordens_Servicos.OID_Veiculo = Veiculos.OID_Veiculo  " +
    		   " AND Acertos.NR_Kilometragem_Chegada > 0 ";
    if(NR_Placa != null && NR_Placa.length() > 0)
        sqlBusca = sqlBusca + " AND Ordens_Servicos.OID_Veiculo = '" + NR_Placa + "'";
    if(DT_Inicial != null)
        sqlBusca = sqlBusca + " AND Acertos.DT_Saida >= '" + DT_Inicial + "'";
    if(DT_Final != null)
        sqlBusca = sqlBusca + " AND Acertos.DT_Saida <= '" + DT_Final + "'";
    sqlBusca = sqlBusca + "  ORDER BY Ordens_servicos.oid_veiculo,  Acertos.oid_acerto ";
    
    try{
	    inicioTransacao();
	    ResultSet cursor = super.sql.executarConsulta(sqlBusca);
	    byte abyte0[];
	    AcertoRL acertoRL = new AcertoRL();
	    abyte0 = acertoRL.geraRelAcerto_Veiculos(cursor, super.sql, DT_Inicial, DT_Final, DM_Relatorio);
	    closeResultset(cursor);
	    fimTransacao(false);
	    return abyte0;
    } catch (Exception exc){
        fimTransacao(false);
        Excecoes e = new Excecoes();
        e.setExc(exc);
        throw e;
    }
    
}

public byte[] geraRelAcertos_Motorista(HttpServletRequest request, HttpServletResponse Response)
    throws Excecoes
{
    String DT_Inicial;
    String DT_Final;
    String DM_Relatorio;
    String sqlBusca;
    DT_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
    DT_Final = request.getParameter("FT_DT_Emissao_Final");
    String oid_Motorista = request.getParameter("oid_Motorista");
    DM_Relatorio = request.getParameter("FT_DM_Relatorio");
    String DM_Tipo_Despesa = request.getParameter("FT_DM_Tipo_Despesa");
    
    sqlBusca = " SELECT Pessoas.NM_Razao_Social, Ordens_Servicos.OID_Ordem_Servico, " +
    		   " Movimentos_Ordens_Servicos.OID_Movimento_Ordem_Servico, Ordens_Servicos.OID_Unidade, " +
    		   " Ordens_Servicos.OID_Tipo_Servico, Ordens_Servicos.NR_Ordem_Servico, Ordens_Servicos.NR_Kilometragem, " +
    		   " Ordens_Servicos.DT_Ordem_Servico, Ordens_Servicos.DT_Encerramento, Ordens_Servicos.NR_Meses_Amortizacao, " +
    		   " Ordens_Servicos.VL_Previsto, Ordens_Servicos.TX_Observacao,  Ordens_Servicos.OID_Veiculo,  Acertos.OID_Acerto, " +
    		   " Acertos.OID_Motorista, Acertos.NR_Acerto, Acertos.DT_Emissao, Acertos.DT_Saida,  Acertos.DT_Chegada, " +
    		   " Acertos.NR_Kilometragem_Chegada, Acertos.VL_Total_Frete, Acertos.VL_Total_Despesas_Pagas, " +
    		   " Acertos.VL_Total_Despesas_Faturadas, Acertos.TX_Observacao as TX_Observacao_Acerto, " +
    		   " Acertos.VL_Adiantamento_Viagem, VL_Frete_Proprio, VL_Frete_Terceiro, VL_Comissao, PE_Comissao , " +
    		   " Acertos.VL_Cotacao as VL_Cotacao_Acerto  " +
    		   " FROM Ordens_Servicos, Movimentos_Ordens_Servicos, Acertos, Pessoas, Tipos_Servicos " +
    		   " WHERE Acertos.OID_Ordem_Servico = Ordens_Servicos.oid_ordem_servico  " +
    		   " AND Acertos.OID_Motorista = Pessoas.OID_Pessoa  " +
    		   " AND Movimentos_Ordens_Servicos.OID_Tipo_Servico = Tipos_Servicos.OID_Tipo_Servico  " +
    		   " AND Ordens_Servicos.OID_Ordem_Servico = Movimentos_Ordens_Servicos.OID_Ordem_Servico  " +
    		   " AND Acertos.NR_Kilometragem_Chegada > 0 ";
    if(oid_Motorista != null && oid_Motorista.length() > 0)
        sqlBusca = sqlBusca + " AND Acertos.oid_Motorista = '" + oid_Motorista + "'";
    if(DT_Inicial != null)
        sqlBusca = sqlBusca + " AND Movimentos_Ordens_Servicos.dt_movimento_ordem_servico >= '" + DT_Inicial + "'";
    if(DT_Final != null)
        sqlBusca = sqlBusca + " AND Movimentos_Ordens_Servicos.dt_movimento_ordem_servico <= '" + DT_Final + "'";
    if(String.valueOf(DM_Tipo_Despesa) != null && !String.valueOf(DM_Tipo_Despesa).equals("null") && !String.valueOf(DM_Tipo_Despesa).equals("T") && !String.valueOf(DM_Tipo_Despesa).equals(""))
        sqlBusca = sqlBusca + " and Tipos_Servicos.DM_Tipo_Despesa = '" + DM_Tipo_Despesa + "'";
    sqlBusca = sqlBusca + "  ORDER BY Acertos.oid_Motorista,  Movimentos_Ordens_Servicos.dt_movimento_ordem_servico ";
    try{
	    inicioTransacao();
	    ResultSet cursor = super.sql.executarConsulta(sqlBusca);
	    byte abyte0[];
	    AcertoRL acertoRL = new AcertoRL();
	    abyte0 = acertoRL.geraRelAcerto_Motoristas(cursor, super.sql, DT_Inicial, DT_Final, DM_Relatorio);
	    closeResultset(cursor);
	    fimTransacao(false);
	    return abyte0;
    }
    catch(Exception exception){
	    fimTransacao(false);
	    Excecoes e = new Excecoes();
        e.setExc(exception);
        throw e;
    }
}



	public void setVL_Comissao(double VL_Comissao) {
		this.VL_Comissao = VL_Comissao;
	}

	public double getVL_Comissao() {
		return VL_Comissao;
	}

	public void setPE_Comissao(double PE_Comissao) {
		this.PE_Comissao = PE_Comissao;
	}

	public double getPE_Comissao() {
		return PE_Comissao;
	}

	public void setVL_Frete_Peso(double VL_Frete_Peso) {
		this.VL_Frete_Peso = VL_Frete_Peso;
	}

	public double getVL_Frete_Peso() {
		return VL_Frete_Peso;
	}

	public void setVL_Frete_Proprio(double VL_Frete_Proprio) {
		this.VL_Frete_Proprio = VL_Frete_Proprio;
	}

	public double getVL_Frete_Proprio() {
		return VL_Frete_Proprio;
	}

	public void setVL_Frete_Terceiro(double VL_Frete_Terceiro) {
		this.VL_Frete_Terceiro = VL_Frete_Terceiro;
	}

	public double getVL_Frete_Terceiro() {
		return VL_Frete_Terceiro;
	}
  public void setOid_Movimento_Conta_Corrente(long oid_Movimento_Conta_Corrente) {
    this.oid_Movimento_Conta_Corrente = oid_Movimento_Conta_Corrente;
  }

  public void setDM_Situacao (String DM_Situacao) {
    this.DM_Situacao = DM_Situacao;
  }

  public void setVL_Cotacao (double VL_Cotacao) {
    this.VL_Cotacao = VL_Cotacao;
  }

  public long getOid_Movimento_Conta_Corrente() {
    return oid_Movimento_Conta_Corrente;
  }

  public String getDM_Situacao () {
    return DM_Situacao;
  }

  public double getVL_Cotacao () {
    return VL_Cotacao;
  }
  public String getDM_Impresso() {
      return this.DM_Impresso;
  }
  public void setDM_Impresso(String impresso) {
      this.DM_Impresso = impresso;
  }
  public double getVL_Devolvido() {
      return this.VL_Devolvido;
  }
  public void setVL_Devolvido(double devolvido) {
      this.VL_Devolvido = devolvido;
  }
  public double getVL_Recebido() {
      return this.VL_Recebido;
  }
  public void setVL_Recebido(double recebido) {
      this.VL_Recebido = recebido;
  }
  public double getVL_Retido() {
      return VL_Recebido - VL_Devolvido;
  }
}
