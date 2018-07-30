package com.master.root;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.master.util.Excecoes;
import com.master.util.JavaUtil;

import auth.OracleConnection2;

public class Tipo_MovimentoBean
{
	private String CD_Tipo_Movimento;
	private String NM_Tipo_Movimento;
	private String DM_Tipo_Movimento;
	private String DM_Percentual_Valor;
	private String DM_Debito_Credito;
	private String DM_Base_ICMS;
	private String DM_Calcula_Frete;
	private String NR_Sequencia_Calculo;
	private String Usuario_Stamp;
	private String Dt_Stamp;
	private String Dm_Stamp;
	private int oid;
	private String DM_Totaliza;
	private String DM_Lancamento_Multiplo;
	private String DM_Nacional_Internacional;


    public void setDM_Totaliza(String DM_Totaliza) {
        this.DM_Totaliza = DM_Totaliza;
	}
	public String getDM_Totaliza() {
	    return DM_Totaliza;
	}
    public String getDM_Nacional_Internacional() {
        return this.DM_Nacional_Internacional;
    }
    public void setDM_Nacional_Internacional(String nacional_Internacional) {
        this.DM_Nacional_Internacional = nacional_Internacional;
    }
	public Tipo_MovimentoBean() {}

	public String getCD_Tipo_Movimento()
	{
		return CD_Tipo_Movimento;
	}
	public void setCD_Tipo_Movimento(String CD_Tipo_Movimento)
	{
		this.CD_Tipo_Movimento = CD_Tipo_Movimento;
	}
	public String getNM_Tipo_Movimento()
	{
		return NM_Tipo_Movimento;
	}
	public void setNM_Tipo_Movimento(String NM_Tipo_Movimento)
	{
		this.NM_Tipo_Movimento = NM_Tipo_Movimento;
	}
	public String getDM_Tipo_Movimento()
	{
		return DM_Tipo_Movimento;
	}
	public void setDM_Tipo_Movimento(String DM_Tipo_Movimento)
	{
		this.DM_Tipo_Movimento = DM_Tipo_Movimento;
	}
	public String getDM_Percentual_Valor()
	{
		return DM_Percentual_Valor;
	}
	public void setDM_Percentual_Valor(String DM_Percentual_Valor)
	{
		this.DM_Percentual_Valor = DM_Percentual_Valor;
	}
	public String getDM_Debito_Credito()
	{
		return DM_Debito_Credito;
	}
	public void setDM_Debito_Credito(String DM_Debito_Credito)
	{
		this.DM_Debito_Credito = DM_Debito_Credito;
	}
	public String getDM_Base_ICMS()
	{
		return DM_Base_ICMS;
	}
	public void setDM_Base_ICMS(String DM_Base_ICMS)
	{
		this.DM_Base_ICMS = DM_Base_ICMS;
	}
	public String getDM_Calcula_Frete()
	{
		return DM_Calcula_Frete;
	}
	public void setDM_Calcula_Frete(String DM_Calcula_Frete)
	{
		this.DM_Calcula_Frete = DM_Calcula_Frete;
	}
	public String getNR_Sequencia_Calculo()
	{
		return NR_Sequencia_Calculo;
	}
	public void setNR_Sequencia_Calculo(String NR_Sequencia_Calculo)
	{
		this.NR_Sequencia_Calculo = NR_Sequencia_Calculo;
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

	public void insert(HttpServletRequest request)
	throws Excecoes {
	    String NM_Tipo_Movimento = request.getParameter("FT_NM_Tipo_Movimento");
	    String CD_Tipo_Movimento = request.getParameter("FT_CD_Tipo_Movimento");
	    String DM_Tipo_Movimento = request.getParameter("Option_Dm_Tipo_Movimento");
		String DM_Percentual_Valor = request.getParameter("Option_Dm_Percentual_Valor");
		String DM_Debito_Credito = request.getParameter("FT_DM_Debito_Credito");
		String DM_Base_ICMS = request.getParameter("FT_DM_Base_ICMS");
		String DM_Totaliza = request.getParameter("FT_DM_Totaliza");
		String DM_Lancamento_Multiplo = request.getParameter("FT_DM_Lancamento_Multiplo");
		String DM_Calcula_Frete = request.getParameter("FT_DM_Calcula_Frete");
		String Nr_Sequencia_Calculo = request.getParameter("FT_Nr_Sequencia_Calculo");
		String DM_Nacional_Internacional = request.getParameter("FT_DM_Nacional_Internacional");

	    if (NM_Tipo_Movimento != null && NM_Tipo_Movimento.length() > 0)
			setNM_Tipo_Movimento(NM_Tipo_Movimento);

		if (CD_Tipo_Movimento != null && CD_Tipo_Movimento.length() > 0)
			setCD_Tipo_Movimento(CD_Tipo_Movimento);

		if (DM_Tipo_Movimento != null && DM_Tipo_Movimento.length() > 0)
			setDM_Tipo_Movimento(DM_Tipo_Movimento);

		if (DM_Percentual_Valor != null && DM_Percentual_Valor.length() > 0)
			setDM_Percentual_Valor(DM_Percentual_Valor);

		if (DM_Debito_Credito != null && DM_Debito_Credito.length() > 0)
			setDM_Debito_Credito(DM_Debito_Credito);

		if (DM_Base_ICMS != null && DM_Base_ICMS.length() > 0)
			setDM_Base_ICMS(DM_Base_ICMS);

		if (DM_Totaliza != null && DM_Totaliza.length() > 0)
			setDM_Totaliza(DM_Totaliza);

		if (DM_Lancamento_Multiplo != null && DM_Lancamento_Multiplo.length() > 0)
			setDM_Lancamento_Multiplo(DM_Lancamento_Multiplo);
		

		if (DM_Calcula_Frete != null && DM_Calcula_Frete.length() > 0)
			setDM_Calcula_Frete(DM_Calcula_Frete);

		if (Nr_Sequencia_Calculo != null && Nr_Sequencia_Calculo.length() > 0)
			setNR_Sequencia_Calculo(Nr_Sequencia_Calculo);

		if (JavaUtil.doValida(DM_Nacional_Internacional))
			setDM_Nacional_Internacional(DM_Nacional_Internacional);

		try {
		    insert();
		} catch (Exception e) {
		    throw new Excecoes(e.getMessage(), e, getClass().getName(), "insert(HttpServletRequest request)");
		}
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
			// passando como parâmetro o NM_Tipo_Movimento do DSN
			// o NM_Tipo_Movimento de usuário e a senha do banco.
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
				"SELECT MAX(OID_Tipo_Movimento) FROM Tipos_Movimentos");

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
		buff.append("INSERT INTO Tipos_Movimentos (OID_Tipo_Movimento, CD_Tipo_Movimento, NM_Tipo_Movimento, DM_Tipo_Movimento, DM_Percentual_Valor, NR_Sequencia_Calculo, Dt_Stamp, Usuario_Stamp, Dm_Stamp, DM_Debito_Credito, DM_Base_ICMS, DM_Calcula_Frete, DM_Totaliza, DM_Nacional_Internacional, DM_Lancamento_Multiplo) ");
		buff.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		/*
		 * Define os dados do SQL
		* e executa o insert no banco.
		*/
		try
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setInt(1, getOID());
			pstmt.setString(2, getCD_Tipo_Movimento());
			pstmt.setString(3, getNM_Tipo_Movimento());
			pstmt.setString(4, getDM_Tipo_Movimento());
			pstmt.setString(5, getDM_Percentual_Valor());
			pstmt.setString(6, getNR_Sequencia_Calculo());
			pstmt.setString(7, getDt_Stamp());
			pstmt.setString(8, getUsuario_Stamp());
			pstmt.setString(9, getDm_Stamp());
			pstmt.setString(10, getDM_Debito_Credito());
			pstmt.setString(11, getDM_Base_ICMS());
			pstmt.setString(12, getDM_Calcula_Frete());
			pstmt.setString(13, getDM_Totaliza());
			pstmt.setString(14, getDM_Nacional_Internacional());
			pstmt.setString(15, getDM_Lancamento_Multiplo());
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

	public void update(HttpServletRequest request) throws Excecoes {
		String oid = request.getParameter("oid");
		String CD_Tipo_Movimento = request.getParameter("FT_CD_Tipo_Movimento");
		String NM_Tipo_Movimento = request.getParameter("FT_NM_Tipo_Movimento");
		String DM_Tipo_Movimento = request.getParameter("Option_DM_Tipo_Movimento");
		String DM_Percentual_Valor = request.getParameter("Option_DM_Percentual_Valor");
		String DM_Debito_Credito = request.getParameter("FT_DM_Debito_Credito");
		String DM_Base_ICMS = request.getParameter("FT_DM_Base_ICMS");
		String DM_Calcula_Frete = request.getParameter("FT_DM_Calcula_Frete");
		String DM_Totaliza = request.getParameter("FT_DM_Totaliza");
		String DM_Lancamento_Multiplo = request.getParameter("FT_DM_Lancamento_Multiplo");
		String NR_Sequencia_Calculo = request.getParameter("FT_NR_Sequencia_Calculo");
		String DM_Nacional_Internacional = request.getParameter("FT_DM_Nacional_Internacional");

		if (oid != null && oid.length() > 0) {
			int a = (new Integer(oid)).intValue();
			setOID(a);
		}

		if (JavaUtil.doValida(CD_Tipo_Movimento))
			setCD_Tipo_Movimento(CD_Tipo_Movimento);
		if (NM_Tipo_Movimento != null && NM_Tipo_Movimento.length() > 0)
			setNM_Tipo_Movimento(NM_Tipo_Movimento);

		if (DM_Tipo_Movimento != null && DM_Tipo_Movimento.length() > 0)
			setDM_Tipo_Movimento(DM_Tipo_Movimento);

		if (DM_Percentual_Valor != null && DM_Percentual_Valor.length() > 0)
			setDM_Percentual_Valor(DM_Percentual_Valor);

		if (DM_Debito_Credito != null && DM_Debito_Credito.length() > 0)
			setDM_Debito_Credito(DM_Debito_Credito);

		if (DM_Base_ICMS != null && DM_Base_ICMS.length() > 0)
			setDM_Base_ICMS(DM_Base_ICMS);

		if (DM_Calcula_Frete != null && DM_Calcula_Frete.length() > 0)
			setDM_Calcula_Frete(DM_Calcula_Frete);

		if (DM_Totaliza != null && DM_Totaliza.length() > 0)
			setDM_Totaliza(DM_Totaliza);

		if (DM_Lancamento_Multiplo != null && DM_Lancamento_Multiplo.length() > 0)
			setDM_Lancamento_Multiplo(DM_Lancamento_Multiplo);
		
		if (NR_Sequencia_Calculo != null && NR_Sequencia_Calculo.length() > 0)
			setNR_Sequencia_Calculo(NR_Sequencia_Calculo);

		if (JavaUtil.doValida(DM_Nacional_Internacional))
			setDM_Nacional_Internacional(DM_Nacional_Internacional);
		try {
            update();
        } catch (Exception e) {
	         throw new Excecoes(e.getMessage(), e, getClass().getName(), "update(HttpServletRequest request)");
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
			// passando como parâmetro o NM_Tipo_Movimento do DSN
			// o NM_Tipo_Movimento de usuário e a senha do banco.
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
		buff.append("UPDATE Tipos_Movimentos SET NM_Tipo_Movimento=?, DM_Tipo_Movimento=?, DM_Percentual_Valor=?, NR_Sequencia_Calculo=?, Dt_Stamp=?, Usuario_Stamp=?, Dm_Stamp=?, DM_Debito_Credito=?, DM_Base_ICMS=?, DM_Calcula_Frete=?, DM_Totaliza=?, DM_Nacional_Internacional=?, CD_Tipo_Movimento=?, DM_Lancamento_Multiplo=? ");
		buff.append("WHERE OID_Tipo_Movimento=?");
		/*
		 * Define os dados do SQL
		* e executa o insert no banco.
		*/
		try
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setString(1, getNM_Tipo_Movimento());
			pstmt.setString(2, getDM_Tipo_Movimento());
			pstmt.setString(3, getDM_Percentual_Valor());
			pstmt.setString(4, getNR_Sequencia_Calculo());
			pstmt.setString(5, getDt_Stamp());
			pstmt.setString(6, getUsuario_Stamp());
			pstmt.setString(7, getDm_Stamp());
			pstmt.setString(8, getDM_Debito_Credito());
			pstmt.setString(9, getDM_Base_ICMS());
			pstmt.setString(10, getDM_Calcula_Frete());
			pstmt.setString(11, getDM_Totaliza());
			pstmt.setString(12, getDM_Nacional_Internacional());
			pstmt.setString(13, getCD_Tipo_Movimento());
			pstmt.setString(14, getDM_Lancamento_Multiplo());
			pstmt.setInt(15, getOID());
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
			// passando como parâmetro o NM_Tipo_Movimento do DSN
			// o NM_Tipo_Movimento de usuário e a senha do banco.
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
		buff.append("DELETE FROM Tipos_Movimentos ");
		buff.append("WHERE OID_Tipo_Movimento=?");
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

	public static final Tipo_MovimentoBean getByOID(int oid)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		*/
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Tipo_Movimento do DSN
			// o NM_Tipo_Movimento de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		Tipo_MovimentoBean p = new Tipo_MovimentoBean();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT Tipos_Movimentos.OID_Tipo_Movimento, ");
			buff.append("	Tipos_Movimentos.CD_Tipo_Movimento, ");
			buff.append("	Tipos_Movimentos.NM_Tipo_Movimento, ");
			buff.append("	Tipos_Movimentos.DM_Tipo_Movimento, ");
			buff.append("	Tipos_Movimentos.DM_Percentual_Valor, ");
			buff.append("	Tipos_Movimentos.NR_Sequencia_Calculo, ");
			buff.append("	Tipos_Movimentos.DM_Debito_Credito, ");
			buff.append("	Tipos_Movimentos.DM_Base_ICMS, ");
			buff.append("	Tipos_Movimentos.DM_Calcula_Frete, ");
			buff.append("	Tipos_Movimentos.DM_Totaliza, ");
			buff.append("	Tipos_Movimentos.DM_Lancamento_Multiplo, ");
			buff.append("	Tipos_Movimentos.DM_Nacional_Internacional ");
			buff.append("FROM Tipos_Movimentos ");
			buff.append("WHERE OID_Tipo_Movimento= ");
			buff.append(oid);

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				p.setOID(cursor.getInt("OID_Tipo_Movimento"));
				p.setCD_Tipo_Movimento(cursor.getString("CD_Tipo_Movimento"));
				p.setNM_Tipo_Movimento(cursor.getString("NM_Tipo_Movimento"));
				p.setDM_Tipo_Movimento(cursor.getString("DM_Tipo_Movimento"));
				p.setDM_Percentual_Valor(cursor.getString("DM_Percentual_Valor"));
				p.setNR_Sequencia_Calculo(cursor.getString("NR_Sequencia_Calculo"));
				p.setDM_Debito_Credito(cursor.getString("DM_Debito_Credito"));
				p.setDM_Base_ICMS(cursor.getString("DM_Base_ICMS"));
				p.setDM_Calcula_Frete(cursor.getString("DM_Calcula_Frete"));
				p.setDM_Totaliza(cursor.getString("DM_Totaliza"));
				p.setDM_Lancamento_Multiplo(cursor.getString("DM_Lancamento_Multiplo"));
				p.setDM_Nacional_Internacional(cursor.getString("DM_Nacional_Internacional"));
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

	public static final Tipo_MovimentoBean getByCD_Tipo_Movimento(String CD_Tipo_Movimento)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		*/
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Tipo_Movimento do DSN
			// o NM_Tipo_Movimento de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		Tipo_MovimentoBean p = new Tipo_MovimentoBean();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT Tipos_Movimentos.OID_Tipo_Movimento, ");
			buff.append("	Tipos_Movimentos.CD_Tipo_Movimento, ");
			buff.append("	Tipos_Movimentos.NM_Tipo_Movimento, ");
			buff.append("	Tipos_Movimentos.DM_Tipo_Movimento, ");
			buff.append("	Tipos_Movimentos.DM_Percentual_Valor, ");
			buff.append("	Tipos_Movimentos.NR_Sequencia_Calculo, ");
			buff.append("	Tipos_Movimentos.DM_Debito_Credito, ");
			buff.append("	Tipos_Movimentos.DM_Totaliza, ");
			buff.append("	Tipos_Movimentos.DM_Nacional_Internacional ");
			buff.append("FROM Tipos_Movimentos ");
			buff.append("WHERE CD_Tipo_Movimento= '");
			buff.append(CD_Tipo_Movimento);
			buff.append("'");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next()) {
				p.setOID(cursor.getInt("OID_Tipo_Movimento"));
				p.setCD_Tipo_Movimento(cursor.getString("CD_Tipo_Movimento"));
				p.setNM_Tipo_Movimento(cursor.getString("NM_Tipo_Movimento"));
				p.setDM_Tipo_Movimento(cursor.getString("DM_Tipo_Movimento"));
				p.setDM_Percentual_Valor(cursor.getString("DM_Percentual_Valor"));
				p.setNR_Sequencia_Calculo(cursor.getString("NR_Sequencia_Calculo"));
				p.setDM_Debito_Credito(cursor.getString("DM_Debito_Credito"));
				p.setDM_Totaliza(cursor.getString("DM_Totaliza"));
				p.setDM_Nacional_Internacional(cursor.getString("DM_Nacional_Internacional"));
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			throw e;
		}
		return p;
	}

	public static final List getByNM_Tipo_Movimento(String NM_Tipo_Movimento)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		*/
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Tipo_Movimento do DSN
			// o NM_Tipo_Movimento de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		List Tipos_Movimentos_Lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT Tipos_Movimentos.OID_Tipo_Movimento, ");
			buff.append("	Tipos_Movimentos.CD_Tipo_Movimento, ");
			buff.append("	Tipos_Movimentos.NM_Tipo_Movimento, ");
			buff.append("	Tipos_Movimentos.DM_Tipo_Movimento, ");
			buff.append("	Tipos_Movimentos.DM_Percentual_Valor, ");
			buff.append("	Tipos_Movimentos.NR_Sequencia_Calculo, ");
			buff.append("	Tipos_Movimentos.DM_Debito_Credito, ");
			buff.append("	Tipos_Movimentos.DM_Totaliza, ");
			buff.append("	Tipos_Movimentos.DM_Nacional_Internacional ");
			buff.append("FROM Tipos_Movimentos ");
			buff.append("WHERE NM_Tipo_Movimento LIKE'");
			buff.append(NM_Tipo_Movimento);
			buff.append("%'");
			buff.append(" ORDER BY Tipos_Movimentos.NM_Tipo_Movimento ");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				Tipo_MovimentoBean p = new Tipo_MovimentoBean();
				p.setOID(cursor.getInt("OID_Tipo_Movimento"));
				p.setCD_Tipo_Movimento(cursor.getString("CD_Tipo_Movimento"));
				p.setNM_Tipo_Movimento(cursor.getString("NM_Tipo_Movimento"));
				p.setDM_Tipo_Movimento(cursor.getString("DM_Tipo_Movimento"));
				p.setDM_Percentual_Valor(cursor.getString("DM_Percentual_Valor"));
				p.setNR_Sequencia_Calculo(cursor.getString("NR_Sequencia_Calculo"));
				p.setDM_Debito_Credito(cursor.getString("DM_Debito_Credito"));
				p.setDM_Totaliza(cursor.getString("DM_Totaliza"));
				p.setDM_Nacional_Internacional(cursor.getString("DM_Nacional_Internacional"));
				Tipos_Movimentos_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return Tipos_Movimentos_Lista;
	}

	public static final List getByNM_Tipo_Movimento(String NM_Tipo_Movimento, String DM_Tipo_Movimento)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		*/
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Tipo_Movimento do DSN
			// o NM_Tipo_Movimento de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		List Tipos_Movimentos_Lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT Tipos_Movimentos.OID_Tipo_Movimento, ");
			buff.append("	Tipos_Movimentos.CD_Tipo_Movimento, ");
			buff.append("	Tipos_Movimentos.NM_Tipo_Movimento, ");
			buff.append("	Tipos_Movimentos.DM_Tipo_Movimento, ");
			buff.append("	Tipos_Movimentos.DM_Percentual_Valor, ");
			buff.append("	Tipos_Movimentos.NR_Sequencia_Calculo, ");
			buff.append("	Tipos_Movimentos.DM_Debito_Credito, ");
			buff.append("	Tipos_Movimentos.DM_Totaliza, ");
			buff.append("	Tipos_Movimentos.DM_Nacional_Internacional ");
			buff.append("FROM Tipos_Movimentos ");
			buff.append("WHERE NM_Tipo_Movimento LIKE'");
			buff.append(NM_Tipo_Movimento);
			buff.append("%'");
                        if (DM_Tipo_Movimento != null && DM_Tipo_Movimento.length()>0 && !DM_Tipo_Movimento.equals("null")){
                          buff.append("AND DM_Tipo_Movimento ='" + DM_Tipo_Movimento + "'");
                        }
                        
			buff.append(" ORDER BY Tipos_Movimentos.NM_Tipo_Movimento ");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				Tipo_MovimentoBean p = new Tipo_MovimentoBean();
				p.setOID(cursor.getInt("OID_Tipo_Movimento"));
				p.setCD_Tipo_Movimento(cursor.getString("CD_Tipo_Movimento"));
				p.setNM_Tipo_Movimento(cursor.getString("NM_Tipo_Movimento"));
				p.setDM_Tipo_Movimento(cursor.getString("DM_Tipo_Movimento"));
				p.setDM_Percentual_Valor(cursor.getString("DM_Percentual_Valor"));
				p.setNR_Sequencia_Calculo(cursor.getString("NR_Sequencia_Calculo"));
				p.setDM_Debito_Credito(cursor.getString("DM_Debito_Credito"));
				p.setDM_Totaliza(cursor.getString("DM_Totaliza"));
				p.setDM_Nacional_Internacional(cursor.getString("DM_Nacional_Internacional"));
				Tipos_Movimentos_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return Tipos_Movimentos_Lista;
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

		List Tipos_Movimentos_Lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT Tipos_Movimentos.OID_Tipo_Movimento, ");
			buff.append("	Tipos_Movimentos.CD_Tipo_Movimento, ");
			buff.append("	Tipos_Movimentos.NM_Tipo_Movimento, ");
			buff.append("	Tipos_Movimentos.DM_Tipo_Movimento, ");
			buff.append("	Tipos_Movimentos.DM_Percentual_Valor, ");
			buff.append("	Tipos_Movimentos.NR_Sequencia_Calculo, ");
			buff.append("	Tipos_Movimentos.DM_Debito_Credito, ");
			buff.append("	Tipos_Movimentos.DM_Totaliza, ");
			buff.append("	Tipos_Movimentos.DM_Nacional_Internacional ");
			buff.append("FROM Tipos_Movimentos ");
			buff.append(" ORDER BY Tipos_Movimentos.NM_Tipo_Movimento ");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				Tipo_MovimentoBean p = new Tipo_MovimentoBean();
				p.setOID(cursor.getInt("OID_Tipo_Movimento"));
				p.setCD_Tipo_Movimento(cursor.getString("CD_Tipo_Movimento"));
				p.setNM_Tipo_Movimento(cursor.getString("NM_Tipo_Movimento"));
				p.setDM_Tipo_Movimento(cursor.getString("DM_Tipo_Movimento"));
				p.setDM_Percentual_Valor(cursor.getString("DM_Percentual_Valor"));
				p.setNR_Sequencia_Calculo(cursor.getString("NR_Sequencia_Calculo"));
				p.setDM_Debito_Credito(cursor.getString("DM_Debito_Credito"));
				p.setDM_Totaliza(cursor.getString("DM_Totaliza"));
				p.setDM_Nacional_Internacional(cursor.getString("DM_Nacional_Internacional"));
				Tipos_Movimentos_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return Tipos_Movimentos_Lista;
	}
	public String getDM_Lancamento_Multiplo() {
		return DM_Lancamento_Multiplo;
	}
	public void setDM_Lancamento_Multiplo(String lancamento_Multiplo) {
		DM_Lancamento_Multiplo = lancamento_Multiplo;
	}
}
