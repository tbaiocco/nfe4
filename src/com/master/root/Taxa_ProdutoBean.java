package com.master.root;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import auth.OracleConnection2;

public class Taxa_ProdutoBean
{
	private double PE_Aliquota_ICMS;
	private double PE_Aliquota_Seguro;
	private double PE_Aliquota_ICMS_Aprov;
	private double PE_Base_Calculo;
	private String TX_Mensagem_Fiscal;
	private String CD_Taxa;
	private String NM_Taxa;
	private String CD_Tipo_Produto;
	private String NM_Tipo_Produto;
	private String Usuario_Stamp;
	private String Dt_Stamp;
	private String Dm_Stamp;
	private int oid;
	private int oid_Taxa;
	private int oid_Tipo_Produto;

	public Taxa_ProdutoBean() {}

	public String getCD_Taxa()
	{
		return CD_Taxa;
	}
	public void setCD_Taxa(String CD_Taxa)
	{
		this.CD_Taxa = CD_Taxa;
	}
	public String getNM_Taxa()
	{
		return NM_Taxa;
	}
	public void setNM_Taxa(String NM_Taxa)
	{
		this.NM_Taxa = NM_Taxa;
	}
	public String getNM_Tipo_Produto()
	{
		return NM_Tipo_Produto;
	}
	public void setNM_Tipo_Produto(String NM_Tipo_Produto)
	{
		this.NM_Tipo_Produto = NM_Tipo_Produto;
	}
	public String getCD_Tipo_Produto()
	{
		return CD_Tipo_Produto;
	}
	public void setCD_Tipo_Produto(String CD_Tipo_Produto)
	{
		this.CD_Tipo_Produto = CD_Tipo_Produto;
	}
	public double getPE_Aliquota_ICMS()
	{
		return PE_Aliquota_ICMS;
	}
	public void setPE_Aliquota_ICMS(double PE_Aliquota_ICMS)
	{
		this.PE_Aliquota_ICMS = PE_Aliquota_ICMS;
	}
	public double getPE_Aliquota_Seguro()
	{
		return PE_Aliquota_Seguro;
	}
	public void setPE_Aliquota_Seguro(double PE_Aliquota_Seguro)
	{
		this.PE_Aliquota_Seguro = PE_Aliquota_Seguro;
	}
	public double getPE_Aliquota_ICMS_Aprov()
	{
		return PE_Aliquota_ICMS_Aprov;
	}
	public void setPE_Aliquota_ICMS_Aprov(double PE_Aliquota_ICMS_Aprov)
	{
		this.PE_Aliquota_ICMS_Aprov = PE_Aliquota_ICMS_Aprov;
	}
	public double getPE_Base_Calculo()
	{
		return PE_Base_Calculo;
	}
	public void setPE_Base_Calculo(double PE_Base_Calculo)
	{
		this.PE_Base_Calculo = PE_Base_Calculo;
	}

	public String getTX_Mensagem_Fiscal()
	{
		return TX_Mensagem_Fiscal;
	}
	public void setTX_Mensagem_Fiscal(String TX_Mensagem_Fiscal)
	{
		this.TX_Mensagem_Fiscal = TX_Mensagem_Fiscal;
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
	public int getOID_Taxa()
	{
		return oid_Taxa;
	}
	public void setOID_Taxa(int n)
	{
		this.oid_Taxa = n;
	}
	public int getOID_Tipo_Produto()
	{
		return oid_Tipo_Produto;
	}
	public void setOID_Tipo_Produto(int n)
	{
		this.oid_Tipo_Produto = n;
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
			// passando como parâmetro o PE_Base_Calculo do DSN
			// o PE_Base_Calculo de usuário e a senha do banco.
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
				"SELECT MAX(OID_Taxa_Produto) FROM Taxas_Produtos");

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
		buff.append("INSERT INTO Taxas_Produtos (OID_Taxa_Produto, OID_Taxa, OID_Tipo_Produto, PE_Aliquota_ICMS, PE_Base_Calculo, PE_Aliquota_ICMS_Aprov, TX_Mensagem_Fiscal, Dt_Stamp, Usuario_Stamp, Dm_Stamp) ");
		buff.append("VALUES (?,?,?,?,?,?,?,?,?,?)");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setInt(1, getOID());
			pstmt.setInt(2, getOID_Taxa());
			pstmt.setInt(3, getOID_Tipo_Produto());
			pstmt.setDouble(4, getPE_Aliquota_ICMS());
			pstmt.setDouble(5, getPE_Base_Calculo());
			pstmt.setDouble(6, getPE_Aliquota_ICMS_Aprov());
			pstmt.setString(7, getTX_Mensagem_Fiscal());
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
			// passando como parâmetro o PE_Base_Calculo do DSN
			// o PE_Base_Calculo de usuário e a senha do banco.
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
		buff.append("UPDATE Taxas_Produtos SET PE_Aliquota_ICMS=?, PE_Base_Calculo=?, PE_Aliquota_ICMS_Aprov=?, TX_Mensagem_Fiscal=?, Dt_Stamp=?, Usuario_Stamp=?, Dm_Stamp=? ");
		buff.append("WHERE OID_Taxa_Produto=?");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setDouble(1, getPE_Aliquota_ICMS());
			pstmt.setDouble(2, getPE_Base_Calculo());
			pstmt.setDouble(3, getPE_Aliquota_ICMS_Aprov());
			pstmt.setString(4, getTX_Mensagem_Fiscal());
			pstmt.setString(5, getDt_Stamp());
			pstmt.setString(6, getUsuario_Stamp());
			pstmt.setString(7, getDm_Stamp());
			pstmt.setInt(8, getOID());
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
			// passando como parâmetro o PE_Base_Calculo do DSN
			// o PE_Base_Calculo de usuário e a senha do banco.
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
		buff.append("DELETE FROM Taxas_Produtos ");
		buff.append("WHERE OID_Taxa_Produto=?");
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

	public static final Taxa_ProdutoBean getByOID(int oid)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o PE_Base_Calculo do DSN
			// o PE_Base_Calculo de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		Taxa_ProdutoBean p = new Taxa_ProdutoBean();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Taxa_Produto, ");
			buff.append("	Taxas_Produtos.OID_Taxa, ");
			buff.append("	Taxas_Produtos.OID_Tipo_Produto, ");
			buff.append("	Taxas_Produtos.PE_Aliquota_ICMS, ");
			buff.append("	Taxas_Produtos.PE_Base_Calculo, ");
			buff.append("	Taxas_Produtos.PE_Aliquota_ICMS_Aprov, ");
			buff.append("	Taxas_Produtos.TX_Mensagem_Fiscal ");
			buff.append("FROM Taxas_Produtos ");
			buff.append("WHERE Taxas_Produtos.OID_Taxa_Produto = ");
			buff.append(oid);

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				p.setOID(cursor.getInt(1));
				p.setOID_Taxa(cursor.getInt(2));
				p.setOID_Tipo_Produto(cursor.getInt(3));
				p.setPE_Aliquota_ICMS(cursor.getDouble(4));
				p.setPE_Base_Calculo(cursor.getDouble(5));
				p.setPE_Aliquota_ICMS_Aprov(cursor.getDouble(6));
				p.setTX_Mensagem_Fiscal(cursor.getString(7));
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

	public static final Taxa_ProdutoBean getByTaxa_Produto(int oid_Estado_Origem, int oid_Estado_Destino, int oid_Tipo_Produto)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o PE_Base_Calculo do DSN
			// o PE_Base_Calculo de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		Taxa_ProdutoBean p = new Taxa_ProdutoBean();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Taxa_Produto, ");
			buff.append("	Taxas_Produtos.OID_Taxa, ");
			buff.append("	Taxas_Produtos.OID_Tipo_Produto, ");
			buff.append("	Taxas_Produtos.PE_Aliquota_ICMS, ");
			buff.append("	Taxas_Produtos.PE_Base_Calculo, ");
			buff.append("	Taxas_Produtos.PE_Aliquota_ICMS_Aprov, ");
			buff.append("	Taxas_Produtos.TX_Mensagem_Fiscal ");
			buff.append("FROM Taxas_Produtos, Taxas ");
			buff.append("WHERE Taxas_Produtos.OID_Taxa  =  Taxas.oid_Taxa");
			buff.append(" AND   Taxas_Produtos.OID_Tipo_Produto = " + oid_Tipo_Produto);
			buff.append(" AND   Taxas.OID_Estado         = " + oid_Estado_Origem);
			buff.append(" AND   Taxas.OID_Estado_Destino = " + oid_Estado_Destino);

			// System.out.println(buff.toString());
			
			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				p.setOID(cursor.getInt(1));
				p.setOID_Taxa(cursor.getInt(2));
				p.setOID_Tipo_Produto(cursor.getInt(3));
				p.setPE_Aliquota_ICMS(cursor.getDouble(4));
				p.setPE_Base_Calculo(cursor.getDouble(5));
				p.setPE_Aliquota_ICMS_Aprov(cursor.getDouble(6));
				p.setTX_Mensagem_Fiscal(cursor.getString(7));
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


	public static final List Lista_Taxa_Produto(int OID_Taxa)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */

		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o PE_Base_Calculo do DSN
			// o PE_Base_Calculo de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		List Taxa_Produto_Lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Taxa_Produto, ");
			buff.append("	Taxas_Produtos.OID_Taxa, ");
			buff.append("	Taxas_Produtos.OID_Tipo_Produto, ");
			buff.append("	Taxas_Produtos.PE_Aliquota_ICMS, ");
			buff.append("	Taxas_Produtos.PE_Base_Calculo, ");
			buff.append("	Taxas_Produtos.PE_Aliquota_ICMS_Aprov, ");
			buff.append("	Tipos_Produtos.NM_Tipo_Produto ");
			buff.append("FROM Taxas_Produtos, Tipos_Produtos ");
			buff.append("WHERE Taxas_Produtos.OID_Tipo_Produto = Tipos_Produtos.oid_Tipo_Produto ");
			buff.append("AND   Taxas_Produtos.OID_Taxa = ");
			buff.append(OID_Taxa);
			buff.append(" ORDER BY Tipos_Produtos.NM_Tipo_Produto");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				Taxa_ProdutoBean p = new Taxa_ProdutoBean();
				p.setOID(cursor.getInt(1));
				p.setOID_Taxa(cursor.getInt(2));
				p.setOID_Tipo_Produto(cursor.getInt(3));
				p.setPE_Aliquota_ICMS(cursor.getDouble(4));
				p.setPE_Base_Calculo(cursor.getDouble(5));
				p.setPE_Aliquota_ICMS_Aprov(cursor.getDouble(6));
                                p.setNM_Tipo_Produto(cursor.getString(7));
		                Taxa_Produto_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return Taxa_Produto_Lista;
	}


	public static void main(String args[])
		throws Exception
	{
		Taxa_ProdutoBean pp = new Taxa_ProdutoBean();
		pp.setOID(9);
		pp.update();

		Taxa_ProdutoBean  p = getByOID(4);
	}

}
