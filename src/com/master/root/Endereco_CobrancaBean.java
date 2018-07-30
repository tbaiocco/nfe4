package com.master.root;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.master.util.JavaUtil;

import auth.OracleConnection2;

public class Endereco_CobrancaBean
{
	private String NR_CNPJ_CPF;
	private String NM_Endereco;
	private String NM_Bairro;
	private String NR_CEP;
	private String NR_Telefone;
	private String NM_Cidade;
	private String Usuario_Stamp;
	private String Dt_Stamp;
	private String Dm_Stamp;
	private String oid;
	private String oid_Cliente;
	private int oid_Cidade;

	public Endereco_CobrancaBean()
	{
	}

	public String getNM_Cidade()
	{
		return NM_Cidade;
	}
	public void setNM_Cidade(String NM_Cidade)
	{
		this.NM_Cidade = NM_Cidade;
	}
	public String getNM_Endereco()
	{
		return NM_Endereco;
	}
	public void setNM_Endereco(String NM_Endereco)
	{
		this.NM_Endereco = NM_Endereco;
	}
	public String getNM_Bairro()
	{
		return NM_Bairro;
	}
	public void setNM_Bairro(String NM_Bairro)
	{
		this.NM_Bairro = NM_Bairro;
	}
	public String getNR_CEP()
	{
		return NR_CEP;
	}
	public void setNR_CEP(String NR_CEP)
	{
		this.NR_CEP = NR_CEP;
	}
	public String getNR_Telefone()
	{
		return NR_Telefone;
	}
	public void setNR_Telefone(String NR_Telefone)
	{
		this.NR_Telefone = NR_Telefone;
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
	public String getOID()
	{
		return oid;
	}
	public void setOID(String n)
	{
		this.oid = n;
	}
	public String getOID_Cliente()
	{
		return oid_Cliente;
	}
	public void setOID_Cliente(String n)
	{
		this.oid_Cliente = n;
	}
	public int getOID_Cidade()
	{
		return oid_Cidade;
	}
	public void setOID_Cidade(int n)
	{
		this.oid_Cidade = n;
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
			// passando como parâmetro o NM_Endereco do DSN
			// o NM_Endereco de usuário e a senha do banco.
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
		buff.append("INSERT INTO Enderecos_Cobrancas (OID_Endereco_Cobranca, OID_Cliente, OID_Cidade, NM_Endereco, NM_Bairro, NR_Telefone, NR_CEP, Dt_Stamp, Usuario_Stamp, Dm_Stamp) ");
		buff.append("VALUES (?,?,?,?,?,?,?,?,?,?)");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try
		{
			PreparedStatement pstmt = conn.prepareStatement(buff.toString());
			pstmt.setString(1, getOID());
			pstmt.setString(2, getOID_Cliente());
			pstmt.setInt(3, getOID_Cidade());
			pstmt.setString(4, getNM_Endereco());
			pstmt.setString(5, getNM_Bairro());
			pstmt.setString(6, getNR_Telefone());
			pstmt.setString(7, getNR_CEP());
			pstmt.setString(8, getDt_Stamp());
			pstmt.setString(9, getUsuario_Stamp());
			pstmt.setString(10, getDm_Stamp());
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
			// passando como parâmetro o NM_Endereco do DSN
			// o NM_Endereco de usuário e a senha do banco.
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
		buff.append("UPDATE Enderecos_Cobrancas SET OID_Cidade=?, NM_Endereco=?, NM_Bairro=?, NR_CEP=?, NR_Telefone=?, Dt_Stamp=?, Usuario_Stamp=?, Dm_Stamp=? ");
		buff.append("WHERE OID_Endereco_Cobranca=?");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setInt(1, getOID_Cidade());
			pstmt.setString(2, getNM_Endereco());
			pstmt.setString(3, getNM_Bairro());
			pstmt.setString(4, getNR_CEP());
			pstmt.setString(5, getNR_Telefone());
			pstmt.setString(6, getDt_Stamp());
			pstmt.setString(7, getUsuario_Stamp());
			pstmt.setString(8, getDm_Stamp());
			pstmt.setString(9, getOID());
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

	public static final Endereco_CobrancaBean getByOID(String oid)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Endereco do DSN
			// o NM_Endereco de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		Endereco_CobrancaBean p = new Endereco_CobrancaBean();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Endereco_Cobranca, ");
			buff.append("   OID_Cliente, ");
			buff.append("	OID_Cidade, ");
			buff.append("	NM_Endereco, ");
			buff.append("	NM_Bairro, ");
			buff.append("	NR_CEP,  ");
			buff.append("	NR_Telefone  ");
			buff.append("FROM Enderecos_Cobrancas ");
			buff.append("WHERE OID_Cliente= '");
			buff.append(oid);
			buff.append("'");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
                          p.setOID(cursor.getString(1));
                          p.setOID_Cliente(cursor.getString(2));
                          p.setOID_Cidade(cursor.getInt(3));
                          p.setNM_Endereco(cursor.getString(4));
                          p.setNM_Bairro(cursor.getString(5));
                          p.setNR_CEP(cursor.getString(6));
                          p.setNR_Telefone(cursor.getString(7));
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

    //*** Preenche Todos endereços de cobranças com o Endereços dos Clientes
	public static void main(String args[]) throws Exception
	{
        
        Endereco_CobrancaBean p = new Endereco_CobrancaBean();
        Connection conn = null;
        try
        {
            // Pede uma conexão ao gerenciador do driver
            // passando como parâmetro o NM_Endereco do DSN
            // o NM_Endereco de usuário e a senha do banco.
            conn = OracleConnection2.getWEB();
            conn.setAutoCommit(false);
        } catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        }
        StringBuffer buff = new StringBuffer();
        buff.append("SELECT * FROM Pessoas ");
        buff.append("WHERE Pessoas.oid_Pessoa = Clientes.oid_Pessoa");
        
        Statement stmt = conn.createStatement();
        ResultSet cursor = stmt.executeQuery(buff.toString());
        while (cursor.next())
        {
            p.setOID(cursor.getString("oid_Pessoa"));
            p.setOID_Cliente(cursor.getString("oid_Pessoa"));
            p.setOID_Cidade(cursor.getInt("oid_Cidade"));
            p.setNM_Endereco(cursor.getString("NM_Endereco"));
            p.setNM_Bairro(cursor.getString("NM_Bairro"));
            p.setNR_CEP(cursor.getString("NR_CEP"));
            p.setNR_Telefone(cursor.getString("NR_Telefone"));
            if (p.getNR_Telefone() != null && p.getNR_Telefone().trim().length() > 20)
                p.setNR_Telefone(p.getNR_Telefone().substring(0, 20));
            
            p.insert2(conn);
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
    private void insert2(Connection conn) throws Exception
    {
        /*
         * Define o insert.
         */
        StringBuffer buff = new StringBuffer();
        buff.append("INSERT INTO Enderecos_Cobrancas (OID_Endereco_Cobranca, OID_Cliente, OID_Cidade, NM_Endereco, NM_Bairro, NR_Telefone, NR_CEP, Dt_Stamp, Usuario_Stamp, Dm_Stamp) ");
        buff.append("VALUES (?,?,?,?,?,?,?,?,?,?)");
        /*
         * Define os dados do SQL
         * e executa o insert no banco.
         */
        try
        {
            PreparedStatement pstmt = conn.prepareStatement(buff.toString());
            pstmt.setString(1, getOID());
            pstmt.setString(2, getOID_Cliente());
            pstmt.setInt(3, getOID_Cidade());
            pstmt.setString(4, getNM_Endereco());
            pstmt.setString(5, getNM_Bairro());
            pstmt.setString(6, getNR_Telefone());
            pstmt.setString(7, getNR_CEP());
            pstmt.setString(8, getDt_Stamp());
            pstmt.setString(9, getUsuario_Stamp());
            pstmt.setString(10, getDm_Stamp());
            pstmt.executeUpdate();
        } catch (Exception e)
        {
            conn.rollback();
            e.printStackTrace();
            throw e;
        }
    }
}
