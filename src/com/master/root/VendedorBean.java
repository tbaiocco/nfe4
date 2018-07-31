package com.master.root;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.PessoaRelED;
import com.master.util.JavaUtil;

import auth.OracleConnection2;

public class VendedorBean extends JavaUtil {
	
	
	private String CD_Vendedor;
	private String DM_Pagar_Comissao;
	private String DM_Desc_Desconto_CTO;
	private String DM_Situacao;
	private String DM_Desc_Frete_Carreteiro;
	private String DM_Desc_ICMS;
	private String DM_Desc_PIS_Cofins;
	private String NR_CNPJ_CPF;
	private String NM_Razao_Social;
	private String NM_Fantasia;
	private String NR_Telefone_Comercial;
	private String NR_Ramal;
	private String NR_Telefone_Residencial;
	private String NR_Telefone_Fax;
	private String NR_Celular;
	private String NM_Email;
	private String CD_Unidade;
	private String Usuario_Stamp;
	private String Dt_Stamp;
	private String Dm_Stamp;
	private double VL_Salario;
	private double PE_Comissao;
	private double PE_Acrescimo_Maximo;
	private double PE_Desconto_Maximo;
	private String oid_Vendedor;
	
	private String oid_Supervisor;
	private String CD_Supervisor;
	private String NM_Supervisor;
	private String oid_Pessoa;
	private int oid_Ramo_Atividade;
	private String NM_Ramo_Atividade;
	private String CD_Ramo_Atividade;
	private int oid_Unidade;
	private String NM_Fantasia_Unidade;
	private String DM_Tipo_Vendedor;
    private String DM_Margem;
	private int oid_Tipo_Tabela_Venda;

	public VendedorBean()
	{
		CD_Vendedor="";
		DM_Pagar_Comissao="";
		VL_Salario=0;
		DM_Desc_PIS_Cofins="";
		PE_Comissao=0;
		DM_Desc_Desconto_CTO="";
		DM_Situacao="";
		DM_Desc_Frete_Carreteiro="";
		DM_Desc_ICMS="";
		NR_CNPJ_CPF="";
		NM_Razao_Social="";
		NM_Fantasia="";
		NR_Telefone_Comercial="";
		NR_Ramal="";
		NR_Telefone_Residencial="";
		NR_Telefone_Fax = "";
		NR_Celular="";
		NM_Email="";
		CD_Unidade="";
		Usuario_Stamp="";

		Dm_Stamp="";
		oid_Vendedor="";
		oid_Pessoa="";
		oid_Ramo_Atividade=0;
		NM_Ramo_Atividade="";
		CD_Ramo_Atividade="";
		oid_Unidade=0;
		NM_Fantasia_Unidade="";
		DM_Tipo_Vendedor="";
		oid_Supervisor="";
		PE_Acrescimo_Maximo=0;
		PE_Desconto_Maximo=0;
        DM_Margem="";
	}
	
	public String getNR_Telefone_Fax() {
		return NR_Telefone_Fax;
	}
	public void setNR_Telefone_Fax(String telefone_Fax) {
		NR_Telefone_Fax = telefone_Fax;
	}
    /**
     * @return Returns the oid_Supervisor.
     */
    public String getOid_Supervisor() {
        return oid_Supervisor;
    }
    /**
     * @param oid_Supervisor The oid_Supervisor to set.
     */
    public void setOid_Supervisor(String oid_Supervisor) {
        this.oid_Supervisor = oid_Supervisor;
    }
	public String getCD_Vendedor()
	{
		return CD_Vendedor;
	}
	public void setCD_Vendedor(String CD_Vendedor)
	{
		this.CD_Vendedor = CD_Vendedor;
	}
	public String getCD_Unidade()
	{
		return CD_Unidade;
	}
	public void setCD_Unidade(String CD_Unidade)
	{
		this.CD_Unidade = CD_Unidade;
	}
	public String getDM_Situacao()
	{
		return DM_Situacao;
	}
	public void setDM_Situacao(String DM_Situacao)
	{
		this.DM_Situacao = DM_Situacao;
	}
	public String getDM_Pagar_Comissao()
	{
		return DM_Pagar_Comissao;
	}
	public void setDM_Pagar_Comissao(String DM_Pagar_Comissao)
	{
		this.DM_Pagar_Comissao = DM_Pagar_Comissao;
	}
	public double getVL_Salario()
	{
		return VL_Salario;
	}
	public void setVL_Salario(double VL_Salario)
	{
		this.VL_Salario = VL_Salario;
	}
	public String getDM_Desc_PIS_Cofins()
	{
		return DM_Desc_PIS_Cofins;
	}
	public void setDM_Desc_PIS_Cofins(String DM_Desc_PIS_Cofins)
	{
		this.DM_Desc_PIS_Cofins = DM_Desc_PIS_Cofins;
	}
	public double getPE_Comissao()
	{
		return PE_Comissao;
	}
	public void setPE_Comissao(double PE_Comissao)
	{
		this.PE_Comissao = PE_Comissao;
	}
	public String getDM_Desc_Desconto_CTO()
	{
		return DM_Desc_Desconto_CTO;
	}
	public void setDM_Desc_Desconto_CTO(String DM_Desc_Desconto_CTO)
	{
		this.DM_Desc_Desconto_CTO = DM_Desc_Desconto_CTO;
	}
	public String getDM_Desc_Frete_Carreteiro()
	{
		return DM_Desc_Frete_Carreteiro;
	}
	public void setDM_Desc_Frete_Carreteiro(String DM_Desc_Frete_Carreteiro)
	{
		this.DM_Desc_Frete_Carreteiro = DM_Desc_Frete_Carreteiro;
	}

	public String getDM_Desc_ICMS()
	{
		return DM_Desc_ICMS;
	}
	public void setDM_Desc_ICMS(String DM_Desc_ICMS)
	{
		this.DM_Desc_ICMS = DM_Desc_ICMS;
	}
	public String getNR_CNPJ_CPF()
	{
		return NR_CNPJ_CPF;
	}
	public void setNR_CNPJ_CPF(String NR_CNPJ_CPF)
	{
		this.NR_CNPJ_CPF = NR_CNPJ_CPF;
	}
	public String getNM_Razao_Social()
	{
		return NM_Razao_Social;
	}
	public void setNM_Razao_Social(String NM_Razao_Social)
	{
		this.NM_Razao_Social = NM_Razao_Social;
	}
	public String getNM_Fantasia()
	{
		return NM_Fantasia;
	}
	public void setNM_Fantasia(String NM_Fantasia)
	{
		this.NM_Fantasia = NM_Fantasia;
	}
	public String getNM_Fantasia_Unidade()
	{
		return NM_Fantasia_Unidade;
	}
	public void setNM_Fantasia_Unidade(String NM_Fantasia_Unidade)
	{
		this.NM_Fantasia_Unidade = NM_Fantasia_Unidade;
	}
	public String getNM_Ramo_Atividade()
	{
		return NM_Ramo_Atividade;
	}
	public void setNM_Ramo_Atividade(String NM_Ramo_Atividade)
	{
		this.NM_Ramo_Atividade = NM_Ramo_Atividade;
	}
	public String getCD_Ramo_Atividade()
	{
		return CD_Ramo_Atividade;
	}
	public void setCD_Ramo_Atividade(String CD_Ramo_Atividade)
	{
		this.CD_Ramo_Atividade = CD_Ramo_Atividade;
	}
	public String getNR_Telefone_Comercial()
	{
		return NR_Telefone_Comercial;
	}
	public void setNR_Telefone_Comercial(String NR_Telefone_Comercial)
	{
		this.NR_Telefone_Comercial = NR_Telefone_Comercial;
	}
	public String getNR_Ramal()
	{
		return NR_Ramal;
	}
	public void setNR_Ramal(String NR_Ramal)
	{
		this.NR_Ramal = NR_Ramal;
	}
	public String getNR_Telefone_Residencial()
	{
		return NR_Telefone_Residencial;
	}
	public void setNR_Telefone_Residencial(String NR_Telefone_Residencial)
	{
		this.NR_Telefone_Residencial = NR_Telefone_Residencial;
	}
	public String getNR_Celular()
	{
		return NR_Celular;
	}
	public void setNR_Celular(String NR_Celular)
	{
		this.NR_Celular = NR_Celular;
	}
	public String getNM_Email()
	{
		return NM_Email;
	}
	public void setNM_Email(String NM_Email)
	{
		this.NM_Email = NM_Email;
	}

	/*
	 *---------------- Bloco Padr�o para Todas Classes ------------------
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
	public String getOID_Vendedor()
	{
		return oid_Vendedor;
	}
	public void setOID_Vendedor(String n)
	{
		this.oid_Vendedor = n;
	}
	public String getOID_Pessoa()
	{
		return oid_Pessoa;
	}
	public void setOID_Pessoa(String n)
	{
		this.oid_Pessoa = n;
	}
	public int getOID_Unidade()
	{
		return oid_Unidade;
	}
	public void setOID_Unidade(int n)
	{
		this.oid_Unidade = n;
	}
	public int getOID_Ramo_Atividade()
	{
		return oid_Ramo_Atividade;
	}
	public void setOID_Ramo_Atividade(int n)
	{
		this.oid_Ramo_Atividade = n;
	}
    /**
     * @return Returns the tP_Vendedor.
     */
    public String getDM_Tipo_Vendedor() {
        return DM_Tipo_Vendedor;
    }
    /**
     * @param vendedor The tP_Vendedor to set.
     */
    public void setDM_Tipo_Vendedor(String vendedor) {
        DM_Tipo_Vendedor = vendedor;
    }
    /**
     * @return Returns the cD_Supervisor.
     */
    public String getCD_Supervisor() {
        return CD_Supervisor;
    }
    /**
     * @param supervisor The cD_Supervisor to set.
     */
    public void setCD_Supervisor(String supervisor) {
        CD_Supervisor = supervisor;
    }
    /**
     * @return Returns the nM_Supervisor.
     */
    public String getNM_Supervisor() {
        return NM_Supervisor;
    }
    /**
     * @param supervisor The nM_Supervisor to set.
     */
    public void setNM_Supervisor(String supervisor) {
        NM_Supervisor = supervisor;
    }
    /**
     * @return Returns the pE_Acrescimo_Maximo.
     */
    public double getPE_Acrescimo_Maximo() {
        return PE_Acrescimo_Maximo;
    }
    /**
     * @param acrescimo_Maximo The pE_Acrescimo_Maximo to set.
     */
    public void setPE_Acrescimo_Maximo(double acrescimo_Maximo) {
        PE_Acrescimo_Maximo = acrescimo_Maximo;
    }
    /**
     * @return Returns the pE_Desconto_Maximo.
     */
    public double getPE_Desconto_Maximo() {
        return PE_Desconto_Maximo;
    }
    /**
     * @param desconto_Maximo The pE_Desconto_Maximo to set.
     */
    public void setPE_Desconto_Maximo(double desconto_Maximo) {
        PE_Desconto_Maximo = desconto_Maximo;
    }

	public void insert() throws Exception
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
		/*
		 * Define o insert.
		 */
		StringBuffer buff = new StringBuffer();
		buff.append("INSERT INTO Vendedores (OID_Vendedor, OID_Pessoa, OID_Ramo_Atividade, OID_Unidade, " +
				    "CD_Vendedor, DM_Pagar_Comissao, VL_Salario, DM_Desc_PIS_Cofins, PE_Comissao, " +
				    "DM_Desc_Desconto_CTO, DM_Desc_Frete_Carreteiro, DM_Desc_ICMS, NR_Telefone_Comercial, " +
				    "NR_Ramal, NR_Telefone_Residencial, NR_Celular, NM_Email, DM_Situacao, DM_Tipo_Vendedor, " +
				    "OID_Supervisor, PE_Acrescimo_Maximo, PE_Desconto_Maximo, Dt_Stamp, Usuario_Stamp, Dm_Stamp, " +
				    "NR_Telefone_Fax, oid_Tipo_Tabela_Venda, DM_Margem) ");
		buff.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try
		{
			PreparedStatement pstmt = conn.prepareStatement(buff.toString());
			pstmt.setString(1, getOID_Vendedor());
			pstmt.setString(2, getOID_Pessoa());
			pstmt.setInt(3, getOID_Ramo_Atividade());
			pstmt.setInt(4, getOID_Unidade());
			pstmt.setString(5, getCD_Vendedor());
			pstmt.setString(6, getDM_Pagar_Comissao());
			pstmt.setDouble(7, getVL_Salario());
			pstmt.setString(8, getDM_Desc_PIS_Cofins());
			pstmt.setDouble(9, getPE_Comissao());
			pstmt.setString(10, getDM_Desc_Desconto_CTO());
			pstmt.setString(11, getDM_Desc_Frete_Carreteiro());
			pstmt.setString(12, getDM_Desc_ICMS());
			pstmt.setString(13, getNR_Telefone_Comercial());
			pstmt.setString(14, getNR_Ramal());
			pstmt.setString(15, getNR_Telefone_Residencial());
			pstmt.setString(16, getNR_Celular());
			pstmt.setString(17, getNM_Email());
			pstmt.setString(18, getDM_Situacao());
			pstmt.setString(19, getDM_Tipo_Vendedor());
			pstmt.setString(20, getOid_Supervisor());
			pstmt.setDouble(21, getPE_Acrescimo_Maximo());
			pstmt.setDouble(22, getPE_Desconto_Maximo());
			pstmt.setString(23, getDt_Stamp());
			pstmt.setString(24, getUsuario_Stamp());
			pstmt.setString(25, getDm_Stamp());
			pstmt.setString(26, getNR_Telefone_Fax());
			pstmt.setInt(27, getOid_Tipo_Tabela_Venda());
            pstmt.setString(28, getDM_Margem());
			pstmt.executeUpdate();
		} catch (Exception e)
		{
			conn.rollback();
			e.printStackTrace();
			throw e;
		}
		/*
		 * Faz o commit e fecha a conex�o.
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
		 * Abre a conex�o com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conex�o ao gerenciador do driver
			// passando como par�metro o NM_Tipo_Documento do DSN
			// o NM_Tipo_Documento de usu�rio e a senha do banco.
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
		buff.append("UPDATE Vendedores SET OID_Unidade=?, OID_Ramo_Atividade=?, DM_Situacao=?, " +
				    "DM_Pagar_Comissao=?, VL_Salario=?, DM_Desc_PIS_Cofins=?, PE_Comissao=?, " +
				    "DM_Desc_Desconto_CTO=?, DM_Desc_Frete_Carreteiro=?, DM_Desc_ICMS=?, " +
				    "NR_Telefone_Comercial=?, NR_Ramal=?, NR_Telefone_Residencial=?, NR_Celular=?, " +
				    "NM_Email=?, DM_Tipo_Vendedor=?, OID_Supervisor=?, PE_Acrescimo_Maximo=?, " +
				    "PE_Desconto_Maximo=?, Dt_Stamp=?, Usuario_Stamp=?, Dm_Stamp=?, NR_Telefone_Fax=?, oid_Tipo_Tabela_Venda=?, DM_Margem=? ");
		buff.append("WHERE OID_Vendedor=?");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setInt(1, getOID_Unidade());
			pstmt.setInt(2, getOID_Ramo_Atividade());
			pstmt.setString(3, getDM_Situacao());
			pstmt.setString(4, getDM_Pagar_Comissao());
			pstmt.setDouble(5, getVL_Salario());
			pstmt.setString(6, getDM_Desc_PIS_Cofins());
			pstmt.setDouble(7, getPE_Comissao());
			pstmt.setString(8, getDM_Desc_Desconto_CTO());
			pstmt.setString(9, getDM_Desc_Frete_Carreteiro());
			pstmt.setString(10, getDM_Desc_ICMS());
			pstmt.setString(11, getNR_Telefone_Comercial());
			pstmt.setString(12, getNR_Ramal());
			pstmt.setString(13, getNR_Telefone_Residencial());
			pstmt.setString(14, getNR_Celular());
			pstmt.setString(15, getNM_Email());
			pstmt.setString(16, getDM_Tipo_Vendedor());
			pstmt.setString(17, getOid_Supervisor());
			pstmt.setDouble(18, getPE_Acrescimo_Maximo());
			pstmt.setDouble(19, getPE_Desconto_Maximo());
			pstmt.setString(20, getDt_Stamp());
			pstmt.setString(21, getUsuario_Stamp());
			pstmt.setString(22, getDm_Stamp());
			pstmt.setString(23, getNR_Telefone_Fax());
			pstmt.setInt(24, getOid_Tipo_Tabela_Venda());
            pstmt.setString(25, getDM_Margem());
			pstmt.setString(26, getOID_Vendedor());
			
			// System.out.println(pstmt.toString());
			
			pstmt.executeUpdate();
		} catch (Exception e)
		{
			conn.rollback();
			e.printStackTrace();
			throw e;
		}
		/*
		 * Faz o commit e fecha a conex�o.
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
		 * Abre a conex�o com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conex�o ao gerenciador do driver
			// passando como par�metro o NM_Tipo_Documento do DSN
			// o NM_Tipo_Documento de usu�rio e a senha do banco.
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
		buff.append(" DELETE FROM Vendedores ");
		buff.append(" WHERE OID_Vendedor =?");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setString(1, getOID_Vendedor());
			pstmt.executeUpdate();
		} catch (Exception e)
		{
			conn.rollback();
			e.printStackTrace();
			throw e;
		}
		/*
		 * Faz o commit e fecha a conex�o.
		 */
		try
		{
                  StringBuffer buff_Pessoa = new StringBuffer();
                  buff_Pessoa.append("DELETE FROM Grupos_Pessoas_Cargas ");
                  buff_Pessoa.append("WHERE OID_Pessoa =?");
                  PreparedStatement pstmt =
                          conn.prepareStatement(buff_Pessoa.toString());
                  pstmt.setString(1, getOID_Pessoa());
                  pstmt.executeUpdate();

                  buff_Pessoa.delete(0,buff_Pessoa.length());

                  buff_Pessoa.append("DELETE FROM Pessoas ");
                  buff_Pessoa.append("WHERE OID_Pessoa =?");
                  pstmt = conn.prepareStatement(buff_Pessoa.toString());
                  pstmt.setString(1, getOID_Pessoa());
                  pstmt.executeUpdate();

		} catch (Exception e)
		{
			conn.rollback();
			e.printStackTrace();
			throw e;
		}
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

	public static final VendedorBean getByOID_Vendedor(String oid) throws Exception{
		/*
		 * Abre a conex�o com o banco
		 */
		Connection conn = null;
		try{
			// Pede uma conex�o ao gerenciador do driver
			// passando como par�metro o NM_Tipo_Documento do DSN
			// o NM_Tipo_Documento de usu�rio e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		VendedorBean p = new VendedorBean();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT                                  ");
			buff.append("   Vendedores.OID_Vendedor,             ");
			buff.append("	Vendedores.OID_Pessoa,               ");
			buff.append("	Vendedores.OID_Unidade,              ");
			buff.append("	Vendedores.OID_Ramo_Atividade,       ");
			buff.append("	Vendedores.CD_Vendedor,              ");
			buff.append("	Vendedores.DM_Pagar_Comissao,        ");
			buff.append("	Vendedores.VL_Salario,               ");
			buff.append("	Vendedores.DM_Desc_PIS_Cofins,       ");
			buff.append("	Vendedores.PE_Comissao,              ");
			buff.append("	Vendedores.DM_Desc_Desconto_CTO,     ");
			buff.append("	Vendedores.DM_Desc_Frete_Carreteiro, ");
			buff.append("	Vendedores.DM_Desc_ICMS,             ");
			buff.append("	Vendedores.DM_Situacao,              ");
			buff.append("	Vendedores.NR_Telefone_Comercial,    ");
			buff.append("	Vendedores.NR_Ramal,                 ");
			buff.append("	Vendedores.NR_Telefone_Residencial,  ");
			buff.append("	Vendedores.NR_Celular,               ");
			buff.append("	Vendedores.NM_Email,                 ");
			buff.append("	Pessoas.NR_CNPJ_CPF,                 ");
			buff.append("	Pessoas.NM_Razao_Social,             ");
			buff.append("	Pessoas.NM_Fantasia,                 ");
			buff.append("	Unidades.CD_Unidade,                 ");
			buff.append("	Ramos_Atividades.NM_Ramo_Atividade,  ");
			buff.append("	Ramos_Atividades.CD_Ramo_Atividade,  ");
			buff.append("	Pessoa_Unidade.NM_Fantasia,          ");
			buff.append("	Vendedores.DM_Tipo_Vendedor,  		 ");
			buff.append("	Vendedores.OID_Supervisor,    		 ");
			buff.append("	Vendedores.PE_Acrescimo_Maximo, 	 ");
			buff.append("	Vendedores.PE_Desconto_Maximo,  	 ");
			buff.append("	Vendedores.oid_Tipo_Tabela_Venda,  	 ");
			buff.append("	Vendedores.nr_telefone_fax,  		 ");
            buff.append("   Vendedores.DM_Margem                 ");
			buff.append(" FROM Vendedores, Pessoas, Pessoas Pessoa_Unidade, Unidades, Ramos_Atividades ");
			buff.append(" WHERE Vendedores.OID_Pessoa         = Pessoas.OID_Pessoa                     ");
			buff.append(" AND   Vendedores.OID_Ramo_Atividade = Ramos_Atividades.OID_Ramo_Atividade    ");
			buff.append(" AND   Vendedores.OID_Unidade        = Unidades.OID_Unidade                   ");
			buff.append(" AND   Pessoa_Unidade.OID_Pessoa     = Unidades.OID_Pessoa                    ");
			buff.append(" AND Vendedores.OID_Vendedor = '");
			buff.append(oid);
			buff.append("'");
			buff.append(" ORDER BY Pessoas.NM_Razao_Social");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				p.setOID_Vendedor(cursor.getString(1));
				p.setOID_Pessoa(cursor.getString(2));
				p.setOID_Unidade(cursor.getInt(3));
				p.setOID_Ramo_Atividade(cursor.getInt(4));
				p.setCD_Vendedor(cursor.getString(5));
				p.setDM_Pagar_Comissao(cursor.getString(6));
				p.setVL_Salario(cursor.getDouble(7));
				p.setDM_Desc_PIS_Cofins(cursor.getString(8));
				p.setPE_Comissao(cursor.getDouble(9));
				p.setDM_Desc_Desconto_CTO(cursor.getString(10));
				p.setDM_Desc_Frete_Carreteiro(cursor.getString(11));
				p.setDM_Desc_ICMS(cursor.getString(12));
				p.setDM_Situacao(cursor.getString(13));
				p.setNR_Telefone_Comercial(cursor.getString(14));
				p.setNR_Ramal(cursor.getString(15));
				p.setNR_Telefone_Residencial(cursor.getString(16));
				p.setNR_Celular(cursor.getString(17));
				p.setNM_Email(cursor.getString(18));
				p.setNR_CNPJ_CPF(cursor.getString(19));
				p.setNM_Razao_Social(cursor.getString(20));
				p.setNM_Fantasia(cursor.getString(21));
				p.setCD_Unidade(cursor.getString(22));
				p.setNM_Ramo_Atividade(cursor.getString(23));
				p.setCD_Ramo_Atividade(cursor.getString(24));
				p.setNM_Fantasia_Unidade(cursor.getString(25));
				p.setDM_Tipo_Vendedor(cursor.getString(26));
				p.setOid_Supervisor(cursor.getString(27));
				p.setPE_Acrescimo_Maximo(cursor.getDouble(28));
				p.setPE_Desconto_Maximo(cursor.getDouble(29));
				p.setOid_Tipo_Tabela_Venda(cursor.getInt(30));
				p.setNR_Telefone_Fax(cursor.getString(31));
                p.setDM_Margem(cursor.getString("DM_Margem"));
				
				VendedorBean s = new VendedorBean();
			    s = getByOID_Supervisor(p.getOid_Supervisor());
			    p.setCD_Supervisor(s.getCD_Supervisor());
			    p.setNM_Supervisor(s.getNM_Supervisor());
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		return p;
	}

	public static final VendedorBean getByOID_Pessoa(String oid)
		throws Exception
	{
		/*
		 * Abre a conex�o com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conex�o ao gerenciador do driver
			// passando como par�metro o NM_Tipo_Documento do DSN
			// o NM_Tipo_Documento de usu�rio e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		VendedorBean p = new VendedorBean();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT                                  ");
			buff.append("   Vendedores.OID_Vendedor,             ");
			buff.append("	Vendedores.OID_Pessoa,               ");
			buff.append("	Vendedores.OID_Unidade,              ");
			buff.append("	Vendedores.OID_Ramo_Atividade,       ");
			buff.append("	Vendedores.CD_Vendedor,              ");
			buff.append("	Vendedores.DM_Pagar_Comissao,        ");
			buff.append("	Vendedores.VL_Salario,               ");
			buff.append("	Vendedores.DM_Desc_PIS_Cofins,       ");
			buff.append("	Vendedores.PE_Comissao,              ");
			buff.append("	Vendedores.DM_Desc_Desconto_CTO,     ");
			buff.append("	Vendedores.DM_Desc_Frete_Carreteiro, ");
			buff.append("	Vendedores.DM_Desc_ICMS,             ");
			buff.append("	Vendedores.DM_Situacao,              ");
			buff.append("	Vendedores.NR_Telefone_Comercial,    ");
			buff.append("	Vendedores.NR_Ramal,                 ");
			buff.append("	Vendedores.NR_Telefone_Residencial,  ");
			buff.append("	Vendedores.NR_Celular,               ");
			buff.append("	Vendedores.NM_Email,                 ");
			buff.append("	Pessoas.NR_CNPJ_CPF,                 ");
			buff.append("	Pessoas.NM_Razao_Social,             ");
			buff.append("	Pessoas.NM_Fantasia,                 ");
			buff.append("	Unidades.CD_Unidade,                 ");
			buff.append("	Ramos_Atividades.NM_Ramo_Atividade,  ");
			buff.append("	Ramos_Atividades.CD_Ramo_Atividade,  ");
			buff.append("	Pessoa_Unidade.NM_Fantasia,          ");
			buff.append("	Vendedores.DM_Tipo_Vendedor,  		 ");
			buff.append("	Vendedores.OID_Supervisor,    		 ");
			buff.append("	Vendedores.PE_Acrescimo_Maximo, 	 ");
			buff.append("	Vendedores.PE_Desconto_Maximo,  	 ");
			buff.append("	Vendedores.oid_Tipo_Tabela_Venda,  	 ");
			buff.append("	Vendedores.NR_Telefone_Fax,          ");
            buff.append("   Vendedores.DM_Margem                 ");
			buff.append(" FROM Vendedores, Pessoas, Pessoas Pessoa_Unidade, Unidades, Ramos_Atividades ");
			buff.append(" WHERE Vendedores.OID_Pessoa         = Pessoas.OID_Pessoa                     ");
			buff.append(" AND   Vendedores.OID_Ramo_Atividade = Ramos_Atividades.OID_Ramo_Atividade    ");
			buff.append(" AND   Vendedores.OID_Unidade        = Unidades.OID_Unidade                   ");
			buff.append(" AND   Pessoa_Unidade.OID_Pessoa     = Unidades.OID_Pessoa                    ");
			buff.append(" AND Vendedores.OID_Pessoa = '");
			buff.append(oid);
			buff.append("'");

			Statement stmt = conn.createStatement();
			
			ResultSet cursor = stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				p.setOID_Vendedor(cursor.getString(1));
				p.setOID_Pessoa(cursor.getString(2));
				p.setOID_Unidade(cursor.getInt(3));
				p.setOID_Ramo_Atividade(cursor.getInt(4));
				p.setCD_Vendedor(cursor.getString(5));
				p.setDM_Pagar_Comissao(cursor.getString(6));
				p.setVL_Salario(cursor.getDouble(7));
				p.setDM_Desc_PIS_Cofins(cursor.getString(8));
				p.setPE_Comissao(cursor.getDouble(9));
				p.setDM_Desc_Desconto_CTO(cursor.getString(10));
				p.setDM_Desc_Frete_Carreteiro(cursor.getString(11));
				p.setDM_Desc_ICMS(cursor.getString(12));
				p.setDM_Situacao(cursor.getString(13));
				p.setNR_Telefone_Comercial(cursor.getString(14));
				p.setNR_Ramal(cursor.getString(15));
				p.setNR_Telefone_Residencial(cursor.getString(16));
				p.setNR_Celular(cursor.getString(17));
				p.setNM_Email(cursor.getString(18));
				p.setNR_CNPJ_CPF(cursor.getString(19));
				p.setNM_Razao_Social(cursor.getString(20));
				p.setNM_Fantasia(cursor.getString(21));
				p.setCD_Unidade(cursor.getString(22));
				p.setNM_Ramo_Atividade(cursor.getString(23));
				p.setCD_Ramo_Atividade(cursor.getString(24));
				p.setNM_Fantasia_Unidade(cursor.getString(25));
				p.setDM_Tipo_Vendedor(cursor.getString(26));
				p.setOid_Supervisor(cursor.getString(27));
				p.setPE_Acrescimo_Maximo(cursor.getDouble(28));
				p.setPE_Desconto_Maximo(cursor.getDouble(29));
				p.setOid_Tipo_Tabela_Venda(cursor.getInt(30));
				p.setNR_Telefone_Fax(cursor.getString(31));
                p.setDM_Margem(cursor.getString("DM_Margem"));
				
				VendedorBean s = new VendedorBean();
			    s = getByOID_Supervisor(p.getOid_Supervisor());
			    p.setCD_Supervisor(s.getCD_Supervisor());
			    p.setNM_Supervisor(s.getNM_Supervisor());
				
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		return p;
	}

    public static final VendedorBean getByCD_Vendedor(String CD_Vendedor) throws Exception
    {
        Connection conn = null;
        try {
            conn = OracleConnection2.getWEB();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    
        VendedorBean p = new VendedorBean();
        try
        {
            StringBuffer buff = new StringBuffer();
            buff.append("SELECT                                  ");
            buff.append("   Vendedores.OID_Vendedor,             ");
            buff.append("   Vendedores.OID_Pessoa,               ");
            buff.append("   Vendedores.OID_Unidade,              ");
            buff.append("   Vendedores.OID_Ramo_Atividade,       ");
            buff.append("   Vendedores.CD_Vendedor,              ");
            buff.append("   Vendedores.DM_Pagar_Comissao,        ");
            buff.append("   Vendedores.VL_Salario,               ");
            buff.append("   Vendedores.DM_Desc_PIS_Cofins,       ");
            buff.append("   Vendedores.PE_Comissao,              ");
            buff.append("   Vendedores.DM_Desc_Desconto_CTO,     ");
            buff.append("   Vendedores.DM_Desc_Frete_Carreteiro, ");
            buff.append("   Vendedores.DM_Desc_ICMS,             ");
            buff.append("   Vendedores.DM_Situacao,              ");
            buff.append("   Vendedores.NR_Telefone_Comercial,    ");
            buff.append("   Vendedores.NR_Ramal,                 ");
            buff.append("   Vendedores.NR_Telefone_Residencial,  ");
            buff.append("   Vendedores.NR_Celular,               ");
            buff.append("   Vendedores.NM_Email,                 ");
            buff.append("   Pessoas.NR_CNPJ_CPF,                 ");
            buff.append("   Pessoas.NM_Razao_Social,             ");
            buff.append("   Pessoas.NM_Fantasia,                 ");
            buff.append("   Unidades.CD_Unidade,                 ");
            buff.append("   Ramos_Atividades.NM_Ramo_Atividade,  ");
            buff.append("   Ramos_Atividades.CD_Ramo_Atividade,  ");
            buff.append("   Pessoa_Unidade.NM_Fantasia,          ");
            buff.append("   Vendedores.DM_Tipo_Vendedor,         ");
            buff.append("   Vendedores.OID_Supervisor,           ");
            buff.append("   Vendedores.PE_Acrescimo_Maximo,      ");
            buff.append("   Vendedores.PE_Desconto_Maximo,       ");
            buff.append("   Vendedores.oid_Tipo_Tabela_Venda,    ");
            buff.append("   Vendedores.NR_Telefone_Fax,  ");
            buff.append("   Vendedores.DM_Margem                 ");
            buff.append(" FROM Vendedores, Pessoas, Pessoas Pessoa_Unidade, Unidades, Ramos_Atividades ");
            buff.append(" WHERE Vendedores.OID_Pessoa         = Pessoas.OID_Pessoa                     ");
            buff.append(" AND   Vendedores.OID_Ramo_Atividade = Ramos_Atividades.OID_Ramo_Atividade    ");
            buff.append(" AND   Vendedores.OID_Unidade        = Unidades.OID_Unidade                   ");
            buff.append(" AND   Pessoa_Unidade.OID_Pessoa     = Unidades.OID_Pessoa                    ");
            buff.append(" AND   Vendedores.CD_Vendedor = '");
            buff.append(CD_Vendedor);
            buff.append("'");
    
            Statement stmt = conn.createStatement();
            ResultSet cursor = stmt.executeQuery(buff.toString());
    
            while (cursor.next())
            {
                p.setOID_Vendedor(cursor.getString(1));
                p.setOID_Pessoa(cursor.getString(2));
                p.setOID_Unidade(cursor.getInt(3));
                p.setOID_Ramo_Atividade(cursor.getInt(4));
                p.setCD_Vendedor(cursor.getString(5));
                p.setDM_Pagar_Comissao(cursor.getString(6));
                p.setVL_Salario(cursor.getDouble(7));
                p.setDM_Desc_PIS_Cofins(cursor.getString(8));
                p.setPE_Comissao(cursor.getDouble(9));
                p.setDM_Desc_Desconto_CTO(cursor.getString(10));
                p.setDM_Desc_Frete_Carreteiro(cursor.getString(11));
                p.setDM_Desc_ICMS(cursor.getString(12));
                p.setDM_Situacao(cursor.getString(13));
                p.setNR_Telefone_Comercial(cursor.getString(14));
                p.setNR_Ramal(cursor.getString(15));
                p.setNR_Telefone_Residencial(cursor.getString(16));
                p.setNR_Celular(cursor.getString(17));
                p.setNM_Email(cursor.getString(18));
                p.setNR_CNPJ_CPF(cursor.getString(19));
                p.setNM_Razao_Social(cursor.getString(20));
                p.setNM_Fantasia(JavaUtil.getValueDef(cursor.getString(21),p.getNM_Razao_Social()));
                p.setCD_Unidade(cursor.getString(22));
                p.setNM_Ramo_Atividade(cursor.getString(23));
                p.setCD_Ramo_Atividade(cursor.getString(24));
                p.setNM_Fantasia_Unidade(cursor.getString(25));
                p.setDM_Tipo_Vendedor(cursor.getString(26));
                p.setOid_Supervisor(cursor.getString(27));
                p.setPE_Acrescimo_Maximo(cursor.getDouble(28));
                p.setPE_Desconto_Maximo(cursor.getDouble(29));
                p.setOid_Tipo_Tabela_Venda(cursor.getInt(30));
                p.setNR_Telefone_Fax(cursor.getString(31));
                p.setDM_Margem(cursor.getString("DM_Margem"));
                
                VendedorBean s = new VendedorBean();
                s = getByOID_Supervisor(p.getOid_Supervisor());
                p.setCD_Supervisor(s.getCD_Supervisor());
                p.setNM_Supervisor(s.getNM_Supervisor());
                
            }
            cursor.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return p;
    }


	public static final List getByCD_Vendedor1(String CD_Vendedor)
		throws Exception
	{
		/*
		 * Abre a conex�o com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conex�o ao gerenciador do driver
			// passando como par�metro o NM_Tipo_Documento do DSN
			// o NM_Tipo_Documento de usu�rio e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		List Vendedor_Lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT                                  ");
			buff.append("   Vendedores.OID_Vendedor,             ");
			buff.append("	Vendedores.OID_Pessoa,               ");
			buff.append("	Vendedores.OID_Unidade,              ");
			buff.append("	Vendedores.OID_Ramo_Atividade,       ");
			buff.append("	Vendedores.CD_Vendedor,              ");
			buff.append("	Vendedores.DM_Pagar_Comissao,        ");
			buff.append("	Vendedores.VL_Salario,               ");
			buff.append("	Vendedores.DM_Desc_PIS_Cofins,       ");
			buff.append("	Vendedores.PE_Comissao,              ");
			buff.append("	Vendedores.DM_Desc_Desconto_CTO,     ");
			buff.append("	Vendedores.DM_Desc_Frete_Carreteiro, ");
			buff.append("	Vendedores.DM_Desc_ICMS,             ");
			buff.append("	Vendedores.DM_Situacao,              ");
			buff.append("	Vendedores.NR_Telefone_Comercial,    ");
			buff.append("	Vendedores.NR_Ramal,                 ");
			buff.append("	Vendedores.NR_Telefone_Residencial,  ");
			buff.append("	Vendedores.NR_Celular,               ");
			buff.append("	Vendedores.NM_Email,                 ");
			buff.append("	Pessoas.NR_CNPJ_CPF,                 ");
			buff.append("	Pessoas.NM_Razao_Social,             ");
			buff.append("	Pessoas.NM_Fantasia,                 ");
			buff.append("	Unidades.CD_Unidade,                 ");
			buff.append("	Ramos_Atividades.NM_Ramo_Atividade,  ");
			buff.append("	Ramos_Atividades.CD_Ramo_Atividade,  ");
			buff.append("	Pessoa_Unidade.NM_Fantasia,          ");
			buff.append("	Vendedores.DM_Tipo_Vendedor,  		 ");
			buff.append("	Vendedores.OID_Supervisor,    		 ");
			buff.append("	Vendedores.PE_Acrescimo_Maximo, 	 ");
			buff.append("	Vendedores.PE_Desconto_Maximo,  	 ");
			buff.append("	Vendedores.oid_Tipo_Tabela_Venda,  	 ");
			buff.append("	Vendedores.NR_Telefone_fax,  ");
            buff.append("   Vendedores.DM_Margem                 ");
			buff.append(" FROM Vendedores, Pessoas, Pessoas Pessoa_Unidade, Unidades, Ramos_Atividades ");
			buff.append(" WHERE Vendedores.OID_Pessoa         = Pessoas.OID_Pessoa                     ");
			buff.append(" AND   Vendedores.OID_Ramo_Atividade = Ramos_Atividades.OID_Ramo_Atividade    ");
			buff.append(" AND   Vendedores.OID_Unidade        = Unidades.OID_Unidade                   ");
			buff.append(" AND   Pessoa_Unidade.OID_Pessoa     = Unidades.OID_Pessoa                    ");
			buff.append(" AND Vendedores.CD_Vendedor LIKE'");
			buff.append(CD_Vendedor);
			buff.append("%'");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				VendedorBean p = new VendedorBean();
				p.setOID_Vendedor(cursor.getString(1));
				p.setOID_Pessoa(cursor.getString(2));
				p.setOID_Unidade(cursor.getInt(3));
				p.setOID_Ramo_Atividade(cursor.getInt(4));
				p.setCD_Vendedor(cursor.getString(5));
				p.setDM_Pagar_Comissao(cursor.getString(6));
				p.setVL_Salario(cursor.getDouble(7));
				p.setDM_Desc_PIS_Cofins(cursor.getString(8));
				p.setPE_Comissao(cursor.getDouble(9));
				p.setDM_Desc_Desconto_CTO(cursor.getString(10));
				p.setDM_Desc_Frete_Carreteiro(cursor.getString(11));
				p.setDM_Desc_ICMS(cursor.getString(12));
				p.setDM_Situacao(cursor.getString(13));
				p.setNR_Telefone_Comercial(cursor.getString(14));
				p.setNR_Ramal(cursor.getString(15));
				p.setNR_Telefone_Residencial(cursor.getString(16));
				p.setNR_Celular(cursor.getString(17));
				p.setNM_Email(cursor.getString(18));
				p.setNR_CNPJ_CPF(cursor.getString(19));
				p.setNM_Razao_Social(cursor.getString(20));
                p.setNM_Fantasia(JavaUtil.getValueDef(cursor.getString(21),p.getNM_Razao_Social()));
				p.setCD_Unidade(cursor.getString(22));
				p.setNM_Ramo_Atividade(cursor.getString(23));
				p.setCD_Ramo_Atividade(cursor.getString(24));
				p.setNM_Fantasia_Unidade(cursor.getString(25));
				p.setDM_Tipo_Vendedor(cursor.getString(26));
				p.setOid_Supervisor(cursor.getString(27));
				p.setPE_Acrescimo_Maximo(cursor.getDouble(28));
				p.setPE_Desconto_Maximo(cursor.getDouble(29));
				p.setOid_Tipo_Tabela_Venda(cursor.getInt(30));
				p.setNR_Telefone_Fax(cursor.getString(31));
                p.setDM_Margem(cursor.getString("DM_Margem"));
				
			    VendedorBean s = new VendedorBean();
			    s = getByOID_Supervisor(p.getOid_Supervisor());
			    p.setCD_Supervisor(s.getCD_Supervisor());
			    p.setNM_Supervisor(s.getNM_Supervisor());				    
				     
				Vendedor_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		return Vendedor_Lista;
	}
	
	public static final List getByNM_Vendedor(String NM_Vendedor) throws Exception {
		/*
		 * Abre a conex�o com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conex�o ao gerenciador do driver
			// passando como par�metro o NM_Tipo_Documento do DSN
			// o NM_Tipo_Documento de usu�rio e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
	
		List Vendedor_Lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT                                  ");
			buff.append("   Vendedores.OID_Vendedor,             ");
			buff.append("	Vendedores.OID_Pessoa,               ");
			buff.append("	Vendedores.OID_Unidade,              ");
			buff.append("	Vendedores.OID_Ramo_Atividade,       ");
			buff.append("	Vendedores.CD_Vendedor,              ");
			buff.append("	Vendedores.DM_Pagar_Comissao,        ");
			buff.append("	Vendedores.VL_Salario,               ");
			buff.append("	Vendedores.DM_Desc_PIS_Cofins,       ");
			buff.append("	Vendedores.PE_Comissao,              ");
			buff.append("	Vendedores.DM_Desc_Desconto_CTO,     ");
			buff.append("	Vendedores.DM_Desc_Frete_Carreteiro, ");
			buff.append("	Vendedores.DM_Desc_ICMS,             ");
			buff.append("	Vendedores.DM_Situacao,              ");
			buff.append("	Vendedores.NR_Telefone_Comercial,    ");
			buff.append("	Vendedores.NR_Ramal,                 ");
			buff.append("	Vendedores.NR_Telefone_Residencial,  ");
			buff.append("	Vendedores.NR_Celular,               ");
			buff.append("	Vendedores.NM_Email,                 ");
			buff.append("	Pessoas.NR_CNPJ_CPF,                 ");
			buff.append("	Pessoas.NM_Razao_Social,             ");
			buff.append("	Pessoas.NM_Fantasia,                 ");
			buff.append("	Unidades.CD_Unidade,                 ");
			buff.append("	Ramos_Atividades.NM_Ramo_Atividade,  ");
			buff.append("	Ramos_Atividades.CD_Ramo_Atividade,  ");
			buff.append("	Pessoa_Unidade.NM_Fantasia,          ");
			buff.append("	Vendedores.DM_Tipo_Vendedor,  		 ");
			buff.append("	Vendedores.OID_Supervisor,    		 ");
			buff.append("	Vendedores.PE_Acrescimo_Maximo, 	 ");
			buff.append("	Vendedores.PE_Desconto_Maximo,  	 ");
			buff.append("	Vendedores.oid_Tipo_Tabela_Venda,  	 ");
			buff.append("	Vendedores.NR_Telefone_fax,  ");
            buff.append("   Vendedores.DM_Margem                 ");
			buff.append(" FROM Vendedores, Pessoas, Pessoas Pessoa_Unidade, Unidades, Ramos_Atividades ");
			buff.append(" WHERE Vendedores.OID_Pessoa         = Pessoas.OID_Pessoa                     ");
			buff.append(" AND   Vendedores.OID_Ramo_Atividade = Ramos_Atividades.OID_Ramo_Atividade    ");
			buff.append(" AND   Vendedores.OID_Unidade        = Unidades.OID_Unidade                   ");
			buff.append(" AND   Pessoa_Unidade.OID_Pessoa     = Unidades.OID_Pessoa                    ");
			buff.append(" AND   Pessoas.NM_Razao_Social LIKE'");
			buff.append(NM_Vendedor);
			buff.append("%'");
			buff.append(" ORDER BY Vendedores.CD_Vendedor");
	
			Statement stmt = conn.createStatement();
			ResultSet cursor = stmt.executeQuery(buff.toString());
	
			while (cursor.next())
			{
				VendedorBean p = new VendedorBean();
				p.setOID_Vendedor(cursor.getString(1));
				p.setOID_Pessoa(cursor.getString(2));
				p.setOID_Unidade(cursor.getInt(3));
				p.setOID_Ramo_Atividade(cursor.getInt(4));
				p.setCD_Vendedor(cursor.getString(5));
				p.setDM_Pagar_Comissao(cursor.getString(6));
				p.setVL_Salario(cursor.getDouble(7));
				p.setDM_Desc_PIS_Cofins(cursor.getString(8));
				p.setPE_Comissao(cursor.getDouble(9));
				p.setDM_Desc_Desconto_CTO(cursor.getString(10));
				p.setDM_Desc_Frete_Carreteiro(cursor.getString(11));
				p.setDM_Desc_ICMS(cursor.getString(12));
				p.setDM_Situacao(cursor.getString(13));
				p.setNR_Telefone_Comercial(cursor.getString(14));
				p.setNR_Ramal(cursor.getString(15));
				p.setNR_Telefone_Residencial(cursor.getString(16));
				p.setNR_Celular(cursor.getString(17));
				p.setNM_Email(cursor.getString(18));
				p.setNR_CNPJ_CPF(cursor.getString(19));
				p.setNM_Razao_Social(cursor.getString(20));
				p.setNM_Fantasia(cursor.getString(21));
				p.setCD_Unidade(cursor.getString(22));
				p.setNM_Ramo_Atividade(cursor.getString(23));
				p.setCD_Ramo_Atividade(cursor.getString(24));
				p.setNM_Fantasia_Unidade(cursor.getString(25));
				p.setDM_Tipo_Vendedor(cursor.getString(26));
				p.setOid_Supervisor(cursor.getString(27));
				p.setPE_Acrescimo_Maximo(cursor.getDouble(28));
				p.setPE_Desconto_Maximo(cursor.getDouble(29));
				p.setOid_Tipo_Tabela_Venda(cursor.getInt(30));
				p.setNR_Telefone_Fax(cursor.getString(31));
                p.setDM_Margem(cursor.getString("DM_Margem"));
				
			    VendedorBean s = new VendedorBean();
			    s = getByOID_Supervisor(p.getOid_Supervisor());
			    p.setCD_Supervisor(s.getCD_Supervisor());
			    p.setNM_Supervisor(s.getNM_Supervisor());				    
				     
				Vendedor_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		return Vendedor_Lista;
	}
	
	public static final VendedorBean getByOID_Supervisor(String OID_Supervisor) throws Exception
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
    
    	VendedorBean p = new VendedorBean();
    	try
    	{
    		StringBuffer buff = new StringBuffer();
    		buff.append("SELECT                                  ");
    		buff.append("   Vendedores.OID_Vendedor,             ");
    		buff.append("	Vendedores.CD_Vendedor,              ");
    		buff.append("	Pessoas.NM_Fantasia                  ");
    		buff.append(" FROM Vendedores, Pessoas, Pessoas Pessoa_Unidade, Unidades, Ramos_Atividades ");
    		buff.append(" WHERE Vendedores.OID_Pessoa         = Pessoas.OID_Pessoa                     ");
    		buff.append(" AND   Vendedores.OID_Ramo_Atividade = Ramos_Atividades.OID_Ramo_Atividade    ");
    		buff.append(" AND   Vendedores.OID_Unidade        = Unidades.OID_Unidade                   ");
    		buff.append(" AND   Pessoa_Unidade.OID_Pessoa     = Unidades.OID_Pessoa                    ");
    		buff.append(" AND   Vendedores.DM_Tipo_Vendedor   = 'S'");
    		buff.append(" AND   Vendedores.OID_Vendedor 	  = '"+OID_Supervisor+"'");
    		
    		Statement stmt = conn.createStatement();
    		ResultSet cursor = stmt.executeQuery(buff.toString());
    
    		while (cursor.next())
    		{			
    			p.setOid_Supervisor(cursor.getString(1));
    			p.setCD_Supervisor(cursor.getString(2));
    			p.setNM_Supervisor(cursor.getString(3));
    		}
    		cursor.close();
    		stmt.close();
    		conn.close();
    		
    	} catch (Exception e)
    	{
    		e.printStackTrace();
    		throw e;
    	}
    	return p;
	}
    public static final VendedorBean getByCD_Supervisor(String CD_Supervisor) throws Exception
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
    
        VendedorBean p = new VendedorBean();
        try
        {
            StringBuffer buff = new StringBuffer();
            buff.append("SELECT                                  ");
            buff.append("   Vendedores.OID_Vendedor,             ");
            buff.append("   Vendedores.CD_Vendedor,              ");
            buff.append("   Pessoas.NM_Razao_Social              ");
            buff.append(" FROM Vendedores, Pessoas, Pessoas Pessoa_Unidade, Unidades, Ramos_Atividades ");
            buff.append(" WHERE Vendedores.OID_Pessoa         = Pessoas.OID_Pessoa                     ");
            buff.append(" AND   Vendedores.OID_Ramo_Atividade = Ramos_Atividades.OID_Ramo_Atividade    ");
            buff.append(" AND   Vendedores.OID_Unidade        = Unidades.OID_Unidade                   ");
            buff.append(" AND   Pessoa_Unidade.OID_Pessoa     = Unidades.OID_Pessoa                    ");
            buff.append(" AND   Vendedores.DM_Tipo_Vendedor   = 'S'");
            buff.append(" AND   Vendedores.CD_Vendedor       = '"+CD_Supervisor+"'");
            
            Statement stmt = conn.createStatement();
            ResultSet cursor = stmt.executeQuery(buff.toString());
    
            while (cursor.next())
            {           
                p.setOid_Supervisor(cursor.getString(1));
                p.setCD_Supervisor(cursor.getString(2));
                p.setNM_Supervisor(cursor.getString(3));
                p.setNM_Razao_Social(cursor.getString(3));
            }
            cursor.close();
            stmt.close();
            conn.close();
            
        } catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        }
        return p;
    }
	
	public static final List Lista_Tipo_Vendedor(String Tipo)
	throws Exception
{
	/*
	 * Abre a conex�o com o banco
	 */
	Connection conn = null;
	try
	{
		// Pede uma conex�o ao gerenciador do driver
		// passando como par�metro o NM_Tipo_Documento do DSN
		// o NM_Tipo_Documento de usu�rio e a senha do banco.
		conn = OracleConnection2.getWEB();
		conn.setAutoCommit(false);
	} catch (Exception e)
	{
		e.printStackTrace();
		throw e;
	}

	List Vendedor_Lista = new ArrayList();
	try
	{
		StringBuffer buff = new StringBuffer();
		buff.append("SELECT                                  ");
		buff.append("   Vendedores.OID_Vendedor,             ");
		buff.append("	Vendedores.CD_Vendedor,              ");
		buff.append("	Pessoas.NM_Fantasia,                 ");
        buff.append("   Pessoas.NM_Razao_Social              ");
		buff.append(" FROM Vendedores, Pessoas, Ramos_Atividades , Unidades ");
		buff.append(" WHERE Vendedores.OID_Pessoa         = Pessoas.OID_Pessoa                     ");
		buff.append(" AND   Vendedores.OID_Ramo_Atividade = Ramos_Atividades.OID_Ramo_Atividade    ");
		buff.append(" AND   Vendedores.OID_Unidade        = Unidades.OID_Unidade                   ");
		buff.append(" AND   Vendedores.DM_Tipo_Vendedor = '"+Tipo+"'");

		Statement stmt = conn.createStatement();
		ResultSet cursor = stmt.executeQuery(buff.toString());

		while (cursor.next())
		{
			VendedorBean p = new VendedorBean();
			p.setOID_Vendedor(cursor.getString(1));
			p.setCD_Vendedor(cursor.getString(2));
			p.setNM_Fantasia(JavaUtil.getValueDef(cursor.getString(3), cursor.getString(4)));

			Vendedor_Lista.add(p);
		}
		cursor.close();
		stmt.close();
		conn.close();
	} catch (Exception e)
	{
		e.printStackTrace();
		throw e;
	}
	return Vendedor_Lista;
}

	public static final List getByNR_CNPJ_CPF(String NR_CNPJ_CPF)
		throws Exception
	{
		/*
		 * Abre a conex�o com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conex�o ao gerenciador do driver
			// passando como par�metro o NM_Tipo_Documento do DSN
			// o NM_Tipo_Documento de usu�rio e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		List Vendedor_Lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT                                  ");
			buff.append("   Vendedores.OID_Vendedor,             ");
			buff.append("	Vendedores.OID_Pessoa,               ");
			buff.append("	Vendedores.OID_Unidade,              ");
			buff.append("	Vendedores.OID_Ramo_Atividade,       ");
			buff.append("	Vendedores.CD_Vendedor,              ");
			buff.append("	Vendedores.DM_Pagar_Comissao,        ");
			buff.append("	Vendedores.VL_Salario,               ");
			buff.append("	Vendedores.DM_Desc_PIS_Cofins,       ");
			buff.append("	Vendedores.PE_Comissao,              ");
			buff.append("	Vendedores.DM_Desc_Desconto_CTO,     ");
			buff.append("	Vendedores.DM_Desc_Frete_Carreteiro, ");
			buff.append("	Vendedores.DM_Desc_ICMS,             ");
			buff.append("	Vendedores.DM_Situacao,              ");
			buff.append("	Vendedores.NR_Telefone_Comercial,    ");
			buff.append("	Vendedores.NR_Ramal,                 ");
			buff.append("	Vendedores.NR_Telefone_Residencial,  ");
			buff.append("	Vendedores.NR_Celular,               ");
			buff.append("	Vendedores.NM_Email,                 ");
			buff.append("	Pessoas.NR_CNPJ_CPF,                 ");
			buff.append("	Pessoas.NM_Razao_Social,             ");
			buff.append("	Pessoas.NM_Fantasia,                 ");
			buff.append("	Unidades.CD_Unidade,                 ");
			buff.append("	Ramos_Atividades.NM_Ramo_Atividade,  ");
			buff.append("	Ramos_Atividades.CD_Ramo_Atividade,  ");
			buff.append("	Pessoa_Unidade.NM_Fantasia,          ");
			buff.append("	Vendedores.DM_Tipo_Vendedor,  		 ");
			buff.append("	Vendedores.OID_Supervisor,    		 ");
			buff.append("	Vendedores.PE_Acrescimo_Maximo, 	 ");
			buff.append("	Vendedores.PE_Desconto_Maximo,  	 ");
			buff.append("	Vendedores.oid_Tipo_Tabela_Venda,  	 ");
			buff.append("	Vendedores.NR_Telefone_fax,  ");
            buff.append("   Vendedores.DM_Margem                 ");
			buff.append(" FROM Vendedores, Pessoas, Pessoas Pessoa_Unidade, Unidades, Ramos_Atividades ");
			buff.append(" WHERE Vendedores.OID_Pessoa         = Pessoas.OID_Pessoa                     ");
			buff.append(" AND   Vendedores.OID_Ramo_Atividade = Ramos_Atividades.OID_Ramo_Atividade    ");
			buff.append(" AND   Vendedores.OID_Unidade        = Unidades.OID_Unidade                   ");
			buff.append(" AND   Pessoa_Unidade.OID_Pessoa     = Unidades.OID_Pessoa                    ");
			buff.append(" AND Pessoas.NR_CNPJ_CPF = '");
			buff.append(NR_CNPJ_CPF);
			buff.append("'");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				VendedorBean p = new VendedorBean();
				p.setOID_Vendedor(cursor.getString(1));
				p.setOID_Pessoa(cursor.getString(2));
				p.setOID_Unidade(cursor.getInt(3));
				p.setOID_Ramo_Atividade(cursor.getInt(4));
				p.setCD_Vendedor(cursor.getString(5));
				p.setDM_Pagar_Comissao(cursor.getString(6));
				p.setVL_Salario(cursor.getDouble(7));
				p.setDM_Desc_PIS_Cofins(cursor.getString(8));
				p.setPE_Comissao(cursor.getDouble(9));
				p.setDM_Desc_Desconto_CTO(cursor.getString(10));
				p.setDM_Desc_Frete_Carreteiro(cursor.getString(11));
				p.setDM_Desc_ICMS(cursor.getString(12));
				p.setDM_Situacao(cursor.getString(13));
				p.setNR_Telefone_Comercial(cursor.getString(14));
				p.setNR_Ramal(cursor.getString(15));
				p.setNR_Telefone_Residencial(cursor.getString(16));
				p.setNR_Celular(cursor.getString(17));
				p.setNM_Email(cursor.getString(18));
				p.setNR_CNPJ_CPF(cursor.getString(19));
				p.setNM_Razao_Social(cursor.getString(20));
				p.setNM_Fantasia(cursor.getString(21));
				p.setCD_Unidade(cursor.getString(22));
				p.setNM_Ramo_Atividade(cursor.getString(23));
				p.setCD_Ramo_Atividade(cursor.getString(24));
				p.setNM_Fantasia_Unidade(cursor.getString(25));
				p.setDM_Tipo_Vendedor(cursor.getString(26));
				p.setOid_Supervisor(cursor.getString(27));
				p.setPE_Acrescimo_Maximo(cursor.getDouble(28));
				p.setPE_Desconto_Maximo(cursor.getDouble(29));
				p.setOid_Tipo_Tabela_Venda(cursor.getInt(30));
				p.setNR_Telefone_Fax(cursor.getString(31));
                p.setDM_Margem(cursor.getString("DM_Margem"));
				
				VendedorBean s = new VendedorBean();
			    s = getByOID_Supervisor(p.getOid_Supervisor());
			    p.setCD_Supervisor(s.getCD_Supervisor());
			    p.setNM_Supervisor(s.getNM_Supervisor());

				Vendedor_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		return Vendedor_Lista;
	}

    public static final List getAll() throws Exception
    {
        return getAll(null);
    }
	public static final List getAll(String dmTipo_Vendedor) throws Exception
    {
		Connection conn = null;
		try {
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		List Vendedor_Lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT                                  ");
			buff.append("   Vendedores.OID_Vendedor,             ");
			buff.append("	Vendedores.OID_Pessoa,               ");
			buff.append("	Vendedores.OID_Unidade,              ");
			buff.append("	Vendedores.OID_Ramo_Atividade,       ");
			buff.append("	Vendedores.CD_Vendedor,              ");
			buff.append("	Vendedores.DM_Pagar_Comissao,        ");
			buff.append("	Vendedores.VL_Salario,               ");
			buff.append("	Vendedores.DM_Desc_PIS_Cofins,       ");
			buff.append("	Vendedores.PE_Comissao,              ");
			buff.append("	Vendedores.DM_Desc_Desconto_CTO,     ");
			buff.append("	Vendedores.DM_Desc_Frete_Carreteiro, ");
			buff.append("	Vendedores.DM_Desc_ICMS,             ");
			buff.append("	Vendedores.DM_Situacao,              ");
			buff.append("	Vendedores.NR_Telefone_Comercial,    ");
			buff.append("	Vendedores.NR_Ramal,                 ");
			buff.append("	Vendedores.NR_Telefone_Residencial,  ");
			buff.append("	Vendedores.NR_Celular,               ");
			buff.append("	Vendedores.NM_Email,                 ");
			buff.append("	Pessoas.NR_CNPJ_CPF,                 ");
			buff.append("	Pessoas.NM_Razao_Social,             ");
			buff.append("	Pessoas.NM_Fantasia,                 ");
			buff.append("	Unidades.CD_Unidade,                 ");
			buff.append("	Ramos_Atividades.NM_Ramo_Atividade,  ");
			buff.append("	Ramos_Atividades.CD_Ramo_Atividade,  ");
			buff.append("	Pessoa_Unidade.NM_Fantasia,          ");
			buff.append("	Vendedores.DM_Tipo_Vendedor,  		 ");
			buff.append("	Vendedores.OID_Supervisor,    		 ");
			buff.append("	Vendedores.PE_Acrescimo_Maximo, 	 ");
			buff.append("	Vendedores.PE_Desconto_Maximo,  	 ");
			buff.append("	Vendedores.oid_Tipo_Tabela_Venda,  	 ");
			buff.append("	Vendedores.NR_Telefone_Fax,  ");
            buff.append("   Vendedores.DM_Margem                 ");
			buff.append(" FROM Vendedores, Pessoas, Pessoas Pessoa_Unidade, Unidades, Ramos_Atividades ");
			buff.append(" WHERE Vendedores.OID_Pessoa         = Pessoas.OID_Pessoa                     ");
			buff.append(" AND   Vendedores.OID_Ramo_Atividade = Ramos_Atividades.OID_Ramo_Atividade    ");
			buff.append(" AND   Vendedores.OID_Unidade        = Unidades.OID_Unidade                   ");
			buff.append(" AND   Pessoa_Unidade.OID_Pessoa     = Unidades.OID_Pessoa                    ");
            if (JavaUtil.doValida(dmTipo_Vendedor))
                buff.append(" AND   Vendedores.DM_Tipo_Vendedor IN ('"+dmTipo_Vendedor+"')");
            buff.append(" ORDER BY Vendedores.CD_Vendedor");

			Statement stmt = conn.createStatement();
			ResultSet cursor = stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				VendedorBean p = new VendedorBean();
				p.setOID_Vendedor(cursor.getString(1));
				p.setOID_Pessoa(cursor.getString(2));
				p.setOID_Unidade(cursor.getInt(3));
				p.setOID_Ramo_Atividade(cursor.getInt(4));
				p.setCD_Vendedor(cursor.getString(5));
				p.setDM_Pagar_Comissao(cursor.getString(6));
				p.setVL_Salario(cursor.getDouble(7));
				p.setDM_Desc_PIS_Cofins(cursor.getString(8));
				p.setPE_Comissao(cursor.getDouble(9));
				p.setDM_Desc_Desconto_CTO(cursor.getString(10));
				p.setDM_Desc_Frete_Carreteiro(cursor.getString(11));
				p.setDM_Desc_ICMS(cursor.getString(12));
				p.setDM_Situacao(cursor.getString(13));
				p.setNR_Telefone_Comercial(cursor.getString(14));
				p.setNR_Ramal(cursor.getString(15));
				p.setNR_Telefone_Residencial(cursor.getString(16));
				p.setNR_Celular(cursor.getString(17));
				p.setNM_Email(cursor.getString(18));
				p.setNR_CNPJ_CPF(cursor.getString(19));
				p.setNM_Razao_Social(cursor.getString(20));
				p.setNM_Fantasia(cursor.getString(21));
				p.setCD_Unidade(cursor.getString(22));
				p.setNM_Ramo_Atividade(cursor.getString(23));
				p.setCD_Ramo_Atividade(cursor.getString(24));
				p.setNM_Fantasia_Unidade(cursor.getString(25));
				p.setDM_Tipo_Vendedor(cursor.getString(26));
				p.setOid_Supervisor(cursor.getString(27));
				p.setPE_Acrescimo_Maximo(cursor.getDouble(28));
				p.setPE_Desconto_Maximo(cursor.getDouble(29));
				p.setOid_Tipo_Tabela_Venda(cursor.getInt(30));
				p.setNR_Telefone_Fax(cursor.getString(31));
                p.setDM_Margem(cursor.getString("DM_Margem"));
				
                if (doValida(p.getOid_Supervisor()))
                {
                    VendedorBean v = getByOID_Supervisor(p.getOid_Supervisor());
    			    p.setCD_Supervisor(v.getCD_Supervisor());
    			    p.setNM_Supervisor(v.getNM_Supervisor());
                }
				Vendedor_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return Vendedor_Lista;
	}
	
	
	public static final VendedorBean getByOid_Unidade(int oid_Unidade) throws Exception{
		Connection conn = null;
		try{
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}

		VendedorBean p = new VendedorBean();
		
		try{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT ");
			buff.append("Vendedores.OID_Vendedor, ");
			buff.append("Vendedores.OID_Pessoa, ");
			buff.append("Vendedores.OID_Unidade, ");
			buff.append("Vendedores.OID_Ramo_Atividade, ");
			buff.append("Vendedores.CD_Vendedor, ");
			buff.append("Vendedores.DM_Pagar_Comissao,");
			buff.append("Vendedores.VL_Salario, ");
			buff.append("Vendedores.DM_Desc_PIS_Cofins, ");
			buff.append("Vendedores.PE_Comissao, ");
			buff.append("Vendedores.DM_Desc_Desconto_CTO, ");
			buff.append("Vendedores.DM_Desc_Frete_Carreteiro, ");
			buff.append("Vendedores.DM_Desc_ICMS, ");
			buff.append("Vendedores.DM_Situacao, ");
			buff.append("Vendedores.NR_Telefone_Comercial, ");
			buff.append("Vendedores.NR_Ramal, ");
			buff.append("Vendedores.NR_Telefone_Residencial, ");
			buff.append("Vendedores.NR_Celular, ");
			buff.append("Vendedores.NM_Email, ");
			buff.append("Pessoas.NR_CNPJ_CPF, ");
			buff.append("Pessoas.NM_Razao_Social, ");
			buff.append("Pessoas.NM_Fantasia, ");
			buff.append("Unidades.CD_Unidade, ");
			buff.append("Ramos_Atividades.NM_Ramo_Atividade, ");
			buff.append("Ramos_Atividades.CD_Ramo_Atividade, ");
			buff.append("Pessoa_Unidade.NM_Fantasia, ");
			buff.append("Vendedores.DM_Tipo_Vendedor, ");
			buff.append("Vendedores.OID_Supervisor, ");
			buff.append("Vendedores.PE_Acrescimo_Maximo, ");
			buff.append("Vendedores.PE_Desconto_Maximo, ");
			buff.append("Vendedores.oid_Tipo_Tabela_Venda, ");
			buff.append("Vendedores.nr_telefone_fax, ");
            buff.append("   Vendedores.DM_Margem                 ");
			buff.append("FROM Vendedores, Pessoas, Pessoas Pessoa_Unidade, Unidades, Ramos_Atividades ");
			buff.append("WHERE Vendedores.OID_Pessoa         = Pessoas.OID_Pessoa                     ");
			buff.append("AND   Vendedores.OID_Ramo_Atividade = Ramos_Atividades.OID_Ramo_Atividade    ");
			buff.append("AND   Vendedores.OID_Unidade        = Unidades.OID_Unidade                   ");
			buff.append("AND   Pessoa_Unidade.OID_Pessoa     = Unidades.OID_Pessoa                    ");
			buff.append("AND Vendedores.oid_unidade = '" + oid_Unidade + "'");
			buff.append(" ORDER BY Pessoas.NM_Razao_Social");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				p.setOID_Vendedor(cursor.getString(1));
				p.setOID_Pessoa(cursor.getString(2));
				p.setOID_Unidade(cursor.getInt(3));
				p.setOID_Ramo_Atividade(cursor.getInt(4));
				p.setCD_Vendedor(cursor.getString(5));
				p.setDM_Pagar_Comissao(cursor.getString(6));
				p.setVL_Salario(cursor.getDouble(7));
				p.setDM_Desc_PIS_Cofins(cursor.getString(8));
				p.setPE_Comissao(cursor.getDouble(9));
				p.setDM_Desc_Desconto_CTO(cursor.getString(10));
				p.setDM_Desc_Frete_Carreteiro(cursor.getString(11));
				p.setDM_Desc_ICMS(cursor.getString(12));
				p.setDM_Situacao(cursor.getString(13));
				p.setNR_Telefone_Comercial(cursor.getString(14));
				p.setNR_Ramal(cursor.getString(15));
				p.setNR_Telefone_Residencial(cursor.getString(16));
				p.setNR_Celular(cursor.getString(17));
				p.setNM_Email(cursor.getString(18));
				p.setNR_CNPJ_CPF(cursor.getString(19));
				p.setNM_Razao_Social(cursor.getString(20));
				p.setNM_Fantasia(cursor.getString(21));
				p.setCD_Unidade(cursor.getString(22));
				p.setNM_Ramo_Atividade(cursor.getString(23));
				p.setCD_Ramo_Atividade(cursor.getString(24));
				p.setNM_Fantasia_Unidade(cursor.getString(25));
				p.setDM_Tipo_Vendedor(cursor.getString(26));
				p.setOid_Supervisor(cursor.getString(27));
				p.setPE_Acrescimo_Maximo(cursor.getDouble(28));
				p.setPE_Desconto_Maximo(cursor.getDouble(29));
				p.setOid_Tipo_Tabela_Venda(cursor.getInt(30));
				p.setNR_Telefone_Fax(cursor.getString(31));
                p.setDM_Margem(cursor.getString("DM_Margem"));
				
				VendedorBean s = new VendedorBean();
			    s = getByOID_Supervisor(p.getOid_Supervisor());
			    p.setCD_Supervisor(s.getCD_Supervisor());
			    p.setNM_Supervisor(s.getNM_Supervisor());
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		return p;
	}
	
	
	//*** RELAT�RIOS
	// Vendedor
    public void Rel_Vendedor(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        ArrayList lista = new ArrayList();
        
        String oid_Vendedor = request.getParameter("oid_Vendedor");        
               
        if (doValida(oid_Vendedor)){
            PessoaRelED ed = new PessoaRelED();
            VendedorBean edVolta = getByOID_Vendedor(oid_Vendedor);
            
            ed.setCd_vendedor(edVolta.getCD_Vendedor());
            ed.setNr_cnpj_cpf(edVolta.getNR_CNPJ_CPF());
            ed.setNm_razao_social(edVolta.getNM_Razao_Social());
            
		    PessoaBean edPessoa = PessoaBean.getByEndereco_Completo(ed.getNr_cnpj_cpf());
		    ed.setNm_cidade(edPessoa.getNM_Cidade());
		    ed.setNm_estado(edPessoa.getNM_Estado());
		    ed.setNm_endereco(edPessoa.getNM_Endereco());
		    ed.setNm_bairro(edPessoa.getNM_Bairro());
		    ed.setNr_cep(edPessoa.getNR_CEP());
		    ed.setNr_telefone(edPessoa.getNR_Telefone());
		    ed.setNm_inscricao_estadual(edPessoa.getNM_Inscricao_Estadual());		    
            
            lista.add(ed);            
        } else {
            ArrayList listaVolta = (ArrayList) getByCD_Vendedor1("");
            //*** Converte para fields do relat�rio
            for (int i = 0; i < listaVolta.size(); i++){

                PessoaRelED ed = new PessoaRelED();
                VendedorBean edVolta = (VendedorBean) listaVolta.get(i);
                
                ed.setCd_vendedor(edVolta.getCD_Vendedor());
                ed.setNr_cnpj_cpf(edVolta.getNR_CNPJ_CPF());
                ed.setNm_razao_social(edVolta.getNM_Razao_Social());
                
    		    PessoaBean edPessoa = PessoaBean.getByEndereco_Completo(ed.getNr_cnpj_cpf());
    		    ed.setNm_cidade(edPessoa.getNM_Cidade());
    		    ed.setNm_estado(edPessoa.getNM_Estado());
    		    ed.setNm_endereco(edPessoa.getNM_Endereco());
    		    ed.setNm_bairro(edPessoa.getNM_Bairro());
    		    ed.setNr_cep(edPessoa.getNR_CEP());
    		    ed.setNr_telefone(edPessoa.getNR_Telefone());
    		    ed.setNm_inscricao_estadual(edPessoa.getNM_Inscricao_Estadual());
    		    
    		    lista.add(ed);
            }
        }        
        
        
    }

    public int getOid_Tipo_Tabela_Venda() {
        return oid_Tipo_Tabela_Venda;
    }
    public void setOid_Tipo_Tabela_Venda(int oid_Tipo_Tabela_Venda) {
        this.oid_Tipo_Tabela_Venda = oid_Tipo_Tabela_Venda;
    }
    public String getDM_Margem() {
        return DM_Margem;
    }
    public void setDM_Margem(String margem) {
        DM_Margem = margem;
    }
}
