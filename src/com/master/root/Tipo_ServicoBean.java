package com.master.root;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.master.util.Data;

import com.master.ed.ContaED;
import com.master.rn.ContaRN;
import com.master.util.Excecoes;
import com.master.util.Mensagens;

import auth.OracleConnection2;

public class Tipo_ServicoBean
{
	private String CD_Tipo_Servico;
	private String NM_Tipo_Servico;
	private String DM_Tipo_Despesa;
	private String DM_Custo;
	private String DM_Medida;
	private String Usuario_Stamp;
	private String Dt_Stamp;
	private String Dm_Stamp;
	private int oid;
	private double NR_Quantidade;
	private long NR_KM_Aviso;
	private double VL_Previsto;

	private Integer oid_Conta;
	private String nm_conta;
	private String cd_conta;

	public Tipo_ServicoBean() {}

	public String getCD_Tipo_Servico()
	{
		return CD_Tipo_Servico;
	}
	public void setCD_Tipo_Servico(String CD_Tipo_Servico)
	{
		this.CD_Tipo_Servico = CD_Tipo_Servico;
	}
	public String getNM_Tipo_Servico()
	{
		return NM_Tipo_Servico;
	}
	public void setNM_Tipo_Servico(String NM_Tipo_Servico)
	{
		this.NM_Tipo_Servico = NM_Tipo_Servico;
	}

	public String getDM_Tipo_Despesa()
	{
		return DM_Tipo_Despesa;
	}
	public void setDM_Tipo_Despesa(String DM_Tipo_Despesa)
	{
		this.DM_Tipo_Despesa = DM_Tipo_Despesa;
	}

	public String getDM_Custo()
	{
		return DM_Custo;
	}
	public void setDM_Custo(String DM_Custo)
	{
		this.DM_Custo = DM_Custo;
	}

	public String getDM_Medida()
	{
		return DM_Medida;
	}
	public void setDM_Medida(String DM_Medida)
	{
		this.DM_Medida = DM_Medida;
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

	public void insert()
	throws Exception {
		Connection conn = OracleConnection2.getWEB();
		conn.setAutoCommit(false);
		Statement stmt = conn.createStatement();

		// Verifica se existe outro registo com o mesmo código (OID)
		ResultSet cursor = stmt.executeQuery("select count(*) as existe from Tipos_Servicos where CD_Tipo_Servico = '" + getCD_Tipo_Servico() + "'");
		if (cursor.next()) {
			if (cursor.getInt("existe") > 0) {
				throw new Mensagens("Este código já foi cadastrado, selecione outro!");
			}
		}
		cursor.close();
		stmt.close();
		// Gera um novo código (OID)
		try {
			stmt = conn.createStatement();
			cursor = stmt.executeQuery("SELECT MAX(OID_Tipo_Servico) FROM Tipos_Servicos");

			while (cursor.next()) {
				int oid = cursor.getInt(1);
				setOID(oid + 1);
			}
			cursor.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		// Define o insert.
		StringBuffer buff = new StringBuffer();
		buff.append("INSERT INTO Tipos_Servicos (OID_Tipo_Servico, CD_Tipo_Servico, NM_Tipo_Servico, DM_Tipo_Despesa, DM_Custo, DM_Medida, Dt_Stamp, Usuario_Stamp, Dm_Stamp, NR_Quantidade, NR_KM_Aviso, oid_Conta, VL_Previsto) ");
		buff.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try {
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setInt(1, getOID());
			pstmt.setString(2, getCD_Tipo_Servico());
			pstmt.setString(3, getNM_Tipo_Servico());
			pstmt.setString(4, getDM_Tipo_Despesa());
			pstmt.setString(5, getDM_Custo());
			pstmt.setString(6, getDM_Medida());
			pstmt.setString(7, getDt_Stamp());
			pstmt.setString(8, getUsuario_Stamp());
			pstmt.setString(9, getDm_Stamp());
			pstmt.setDouble(10, getNR_Quantidade());
			pstmt.setDouble(11, getNR_KM_Aviso());
			pstmt.setInt(12, getOid_Conta().intValue());
			pstmt.setDouble(13, getVL_Previsto());
			pstmt.executeUpdate();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
			throw new Excecoes(e.getMessage(), e, getClass().getName(), "insert()");
		}
		/*
		 * Faz o commit e fecha a conexão.
		 */
		try {
			conn.commit();
			conn.close();
		} catch (Exception e) {
			throw new Excecoes(e.getMessage(), e, getClass().getName(), "insert()");
		}
	}

	public void update()
	throws Exception {
		// Abre a conexão com o banco
		Connection conn = OracleConnection2.getWEB();
		conn.setAutoCommit(false);
		Statement stmt = conn.createStatement();

		// Verifica se existe outro registo com o mesmo código (OID)
		String sqlBusca = "select count(*) as existe from Tipos_Servicos where CD_Tipo_Servico = '" + getCD_Tipo_Servico() + "' and oid_tipo_servico <> " + getOID();
		// System.out.println(sqlBusca);
		ResultSet cursor = stmt.executeQuery(sqlBusca);
		if (cursor.next()) {
			if (cursor.getInt("existe") > 0) {
				throw new Mensagens("Este código já foi cadastrado, selecione outro!");
			}
		}

		// Define o update
		StringBuffer buff = new StringBuffer();
		buff.append("UPDATE Tipos_Servicos SET NM_Tipo_Servico=?, DM_Tipo_Despesa=?, DM_Custo=?, DM_Medida=?, Dt_Stamp=?, Usuario_Stamp=?, Dm_Stamp=? , NR_Quantidade=? , NR_KM_Aviso=? ,oid_Conta=?, VL_Previsto=?");
		buff.append("WHERE OID_Tipo_Servico=?");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try {
			PreparedStatement pstmt = conn.prepareStatement(buff.toString());
			pstmt.setString(1, getNM_Tipo_Servico());
			pstmt.setString(2, getDM_Tipo_Despesa());
			pstmt.setString(3, getDM_Custo());
			pstmt.setString(4, getDM_Medida());
			pstmt.setString(5, Data.getDataDMY());
			pstmt.setString(6, getUsuario_Stamp());
			pstmt.setString(7, getDm_Stamp());
			pstmt.setDouble(8, getNR_Quantidade());
			pstmt.setDouble(9, getNR_KM_Aviso());
			pstmt.setInt(10, getOid_Conta().intValue());
			pstmt.setDouble(11, getVL_Previsto());
			pstmt.setInt(12, getOID());
			pstmt.executeUpdate();
		} catch (Exception e) {
			conn.rollback();
			throw new Excecoes(e.getMessage(), e, getClass().getName(), "update()");
		}
		// Faz o commit e fecha a conexão
		try {
			conn.commit();
			conn.close();
		} catch (Exception e) {
			throw new Excecoes(e.getMessage(), e, getClass().getName(), "update()");
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
			// passando como parâmetro o NM_Tipo_Servico do DSN
			// o NM_Tipo_Servico de usuário e a senha do banco.
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
		buff.append("DELETE FROM Tipos_Servicos ");
		buff.append("WHERE OID_Tipo_Servico=?");
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

	public static final Tipo_ServicoBean getByOID(int oid)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Tipo_Servico do DSN
			// o NM_Tipo_Servico de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		Tipo_ServicoBean p = new Tipo_ServicoBean();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Tipo_Servico, ");
			buff.append("	CD_Tipo_Servico, ");
			buff.append("	NM_Tipo_Servico, ");
			buff.append("	DM_Tipo_Despesa, ");
			buff.append("	DM_Custo,        ");
			buff.append("	DM_Medida,       ");
			buff.append("	NR_Quantidade,   ");
			buff.append("	NR_KM_Aviso,   ");
			buff.append("	oid_Conta,       ");
			buff.append("	VL_Previsto      ");
			buff.append("FROM Tipos_Servicos ");
			buff.append("WHERE OID_Tipo_Servico= ");
			buff.append(oid);

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				p.setOID(cursor.getInt(1));
				p.setCD_Tipo_Servico(cursor.getString(2));
				p.setNM_Tipo_Servico(cursor.getString(3));
				p.setDM_Tipo_Despesa(cursor.getString(4));
				p.setDM_Custo(cursor.getString(5));
				p.setDM_Medida(cursor.getString(6));
				p.setNR_Quantidade(cursor.getDouble(7));
				p.setNR_KM_Aviso(cursor.getLong(8));
				p.setOid_Conta(new Integer(cursor.getInt(9)));
				if (p.getOid_Conta().intValue() > 0) {
				    ContaED ed = new ContaRN().getByRecord(new ContaED(p.getOid_Conta()));
				    p.setCd_conta(ed.getCd_Conta());
				    p.setNm_conta(ed.getNm_Conta());
				}
				p.setVL_Previsto(cursor.getDouble("VL_Previsto"));
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

	public static final Tipo_ServicoBean getByCD_Tipo_Servico(String CD_Tipo_Servico)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Tipo_Servico do DSN
			// o NM_Tipo_Servico de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		Tipo_ServicoBean p = new Tipo_ServicoBean();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Tipo_Servico, ");
			buff.append("	CD_Tipo_Servico, ");
			buff.append("	NM_Tipo_Servico, ");
			buff.append("	DM_Tipo_Despesa, ");
			buff.append("	DM_Custo,        ");
			buff.append("	DM_Medida,       ");
			buff.append("	NR_Quantidade,   ");
			buff.append("	NR_KM_Aviso,   ");
			buff.append("	oid_Conta,       ");
			buff.append("	VL_Previsto      ");
			buff.append("FROM Tipos_Servicos ");
			buff.append("WHERE CD_Tipo_Servico= '");
			buff.append(CD_Tipo_Servico);
			buff.append("'");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				p.setOID(cursor.getInt(1));
				p.setCD_Tipo_Servico(cursor.getString(2));
				p.setNM_Tipo_Servico(cursor.getString(3));
				p.setDM_Tipo_Despesa(cursor.getString(4));
				p.setDM_Custo(cursor.getString(5));
				p.setDM_Medida(cursor.getString(6));
				p.setNR_Quantidade(cursor.getDouble(7));
				p.setNR_KM_Aviso(cursor.getLong(8));
				p.setOid_Conta(new Integer(cursor.getInt(9)));
				if (p.getOid_Conta().intValue() > 0) {
				    ContaED ed = new ContaRN().getByRecord(new ContaED(p.getOid_Conta()));
				    p.setCd_conta(ed.getCd_Conta());
				    p.setNm_conta(ed.getNm_Conta());
				}
				p.setVL_Previsto(cursor.getDouble("VL_Previsto"));
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

	public static final List getByNM_Tipo_Servico(String NM_Tipo_Servico)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Tipo_Servico do DSN
			// o NM_Tipo_Servico de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		List Tipos_Servicos_Lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Tipo_Servico, ");
			buff.append("	CD_Tipo_Servico, ");
			buff.append("	NM_Tipo_Servico, ");
			buff.append("	DM_Tipo_Despesa, ");
			buff.append("	DM_Custo,        ");
			buff.append("	DM_Medida,       ");
			buff.append("	oid_Conta        ");
			buff.append("FROM Tipos_Servicos ");
			buff.append("WHERE NM_Tipo_Servico LIKE'");
			buff.append(NM_Tipo_Servico);
			buff.append("%'");
			buff.append(" order by NM_Tipo_Servico ");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				Tipo_ServicoBean p = new Tipo_ServicoBean();
				p.setOID(cursor.getInt(1));
				p.setCD_Tipo_Servico(cursor.getString(2));
				p.setNM_Tipo_Servico(cursor.getString(3));
				p.setDM_Tipo_Despesa(cursor.getString(4));
				p.setDM_Custo(cursor.getString(5));
				p.setDM_Medida(cursor.getString(6));
				p.setOid_Conta(new Integer(cursor.getInt(7)));
				if (p.getOid_Conta().intValue() > 0) {
				    ContaED ed = new ContaRN().getByRecord(new ContaED(p.getOid_Conta()));
				    p.setCd_conta(ed.getCd_Conta());
				    p.setNm_conta(ed.getNm_Conta());
				}

				Tipos_Servicos_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return Tipos_Servicos_Lista;
	}

	public static final List getByGrupo_Servico(String NM_Tipo_Servico)
	throws Exception
{
	/*
	 * Abre a conexão com o banco
	 */
	Connection conn = null;
	try
	{
		// Pede uma conexão ao gerenciador do driver
		// passando como parâmetro o NM_Tipo_Servico do DSN
		// o NM_Tipo_Servico de usuário e a senha do banco.
		conn = OracleConnection2.getWEB();
		conn.setAutoCommit(false);
	} catch (Exception e)
	{
		e.printStackTrace();
		throw e;
	}

	List Tipos_Servicos_Lista = new ArrayList();
	try
	{
		StringBuffer buff = new StringBuffer();
		buff.append("SELECT OID_Tipo_Servico, ");
		buff.append("	CD_Tipo_Servico, ");
		buff.append("	NM_Tipo_Servico, ");
		buff.append("	DM_Tipo_Despesa, ");
		buff.append("	DM_Custo,        ");
		buff.append("	DM_Medida,       ");
		buff.append("	oid_Conta        ");
		buff.append("FROM Tipos_Servicos ");
		buff.append("WHERE DM_CUSTO='G' AND NM_Tipo_Servico LIKE'");
		buff.append(NM_Tipo_Servico);
		buff.append("%'");
		buff.append(" order by NM_Tipo_Servico ");

		Statement stmt = conn.createStatement();
		ResultSet cursor =
			stmt.executeQuery(buff.toString());

		while (cursor.next())
		{
			Tipo_ServicoBean p = new Tipo_ServicoBean();
			p.setOID(cursor.getInt(1));
			p.setCD_Tipo_Servico(cursor.getString(2));
			p.setNM_Tipo_Servico(cursor.getString(3));
			p.setDM_Tipo_Despesa(cursor.getString(4));
			p.setDM_Custo(cursor.getString(5));
			p.setDM_Medida(cursor.getString(6));
			p.setOid_Conta(new Integer(cursor.getInt(7)));
			if (p.getOid_Conta().intValue() > 0) {
			    ContaED ed = new ContaRN().getByRecord(new ContaED(p.getOid_Conta()));
			    p.setCd_conta(ed.getCd_Conta());
			    p.setNm_conta(ed.getNm_Conta());
			}

			Tipos_Servicos_Lista.add(p);
		}
		cursor.close();
		stmt.close();
		conn.close();
	} catch (Exception e)
	{
		e.printStackTrace();
	}
	return Tipos_Servicos_Lista;
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

		List Tipos_Servicos_Lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Tipo_Servico, ");
			buff.append("	CD_Tipo_Servico, ");
			buff.append("	NM_Tipo_Servico, ");
			buff.append("	DM_Tipo_Despesa, ");
			buff.append("	DM_Custo,        ");
			buff.append("	DM_Medida,       ");
			buff.append("	oid_Conta        ");
			buff.append("FROM Tipos_Servicos ORDER By NM_Tipo_Servico");
			buff.append(" order by NM_Tipo_Servico ");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				Tipo_ServicoBean p = new Tipo_ServicoBean();
				p.setOID(cursor.getInt(1));
				p.setCD_Tipo_Servico(cursor.getString(2));
				p.setNM_Tipo_Servico(cursor.getString(3));
				p.setDM_Tipo_Despesa(cursor.getString(4));
				p.setDM_Custo(cursor.getString(5));
				p.setDM_Medida(cursor.getString(6));
				p.setOid_Conta(new Integer(cursor.getInt(7)));
				if (p.getOid_Conta().intValue() > 0) {
				    ContaED ed = new ContaRN().getByRecord(new ContaED(p.getOid_Conta()));
				    p.setCd_conta(ed.getCd_Conta());
				    p.setNm_conta(ed.getNm_Conta());
				}

				Tipos_Servicos_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return Tipos_Servicos_Lista;
	}

    public String getCd_conta() {
        return cd_conta;
    }
    public String getNm_conta() {
        return nm_conta;
    }
    public Integer getOid_Conta() {
        return oid_Conta;
    }
    public void setCd_conta(String cd_conta) {
        this.cd_conta = cd_conta;
    }
    public void setNm_conta(String nm_conta) {
        this.nm_conta = nm_conta;
    }
    public void setOid_Conta(Integer oid_Conta) {
        this.oid_Conta = oid_Conta;
    }
    public void setNR_Quantidade(double NR_Quantidade) {
    	this.NR_Quantidade = NR_Quantidade;
  	}
  	public double getNR_Quantidade() {
    	return NR_Quantidade;
  	}
	public double getVL_Previsto() {
		return VL_Previsto;
	}
	public void setVL_Previsto(double previsto) {
		VL_Previsto = previsto;
	}

	public long getNR_KM_Aviso() {
		return NR_KM_Aviso;
	}

	public void setNR_KM_Aviso(long aviso) {
		NR_KM_Aviso = aviso;
	}
}
