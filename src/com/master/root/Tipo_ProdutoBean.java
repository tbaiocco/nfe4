package com.master.root;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.master.util.BancoUtil;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;

import auth.OracleConnection2;

public class Tipo_ProdutoBean
{
	private String CD_Tipo_Produto;
	private String NM_Tipo_Produto;
	private String Usuario_Stamp;
	private String Dt_Stamp;
	private String Dm_Stamp;
	private int oid;

	public Tipo_ProdutoBean() {}

	public String getCD_Tipo_Produto()
	{
		return CD_Tipo_Produto;
	}
	public void setCD_Tipo_Produto(String CD_Tipo_Produto)
	{
		this.CD_Tipo_Produto = CD_Tipo_Produto;
	}
	public String getNM_Tipo_Produto()
	{
		return NM_Tipo_Produto;
	}
	public void setNM_Tipo_Produto(String NM_Tipo_Produto)
	{
		this.NM_Tipo_Produto = NM_Tipo_Produto;
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
	    
	    //*** validações
	    if (!JavaUtil.doValida(CD_Tipo_Produto))
	        throw new Mensagens("Código do Tipo de Produto não informado!");
	    if (!JavaUtil.doValida(NM_Tipo_Produto))
	        throw new Mensagens("Descrição do Tipo de Produto não informada!");
	    BancoUtil dbUtil = new BancoUtil();
	    if (dbUtil.doExiste("Tipos_Produtos","CD_Tipo_Produto = '"+CD_Tipo_Produto.trim()+"'"))
	        throw new Mensagens("Já Existe um Tipo de Produto com o Código informado!");
	    if (dbUtil.doExiste("Tipos_Produtos","NM_Tipo_Produto = '"+NM_Tipo_Produto.trim()+"'"))
	        throw new Mensagens("Já Existe um Tipo de Produto com a Descrição informada!");
	    
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Tipo_Produto do DSN
			// o NM_Tipo_Produto de usuário e a senha do banco.
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
				"SELECT MAX(OID_Tipo_Produto) FROM Tipos_Produtos");

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
		buff.append("INSERT INTO Tipos_Produtos (OID_Tipo_Produto, CD_Tipo_Produto, NM_Tipo_Produto, Dt_Stamp, Usuario_Stamp, Dm_Stamp) ");
		buff.append("VALUES (?,?,?,?,?,?)");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setInt(1, getOID());
			pstmt.setString(2, getCD_Tipo_Produto());
			pstmt.setString(3, getNM_Tipo_Produto());
			pstmt.setString(4, getDt_Stamp());
			pstmt.setString(5, getUsuario_Stamp());
			pstmt.setString(6, getDm_Stamp());
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
	    //*** validações
	    if (oid < 1)
	        throw new Mensagens("ID Tipo de Produto não informado!");
	    if (!JavaUtil.doValida(NM_Tipo_Produto))
	        throw new Mensagens("Descrição do Tipo de Produto não informada!");
	    BancoUtil dbUtil = new BancoUtil();
	    if (dbUtil.doExiste("Tipos_Produtos","NM_Tipo_Produto = '"+NM_Tipo_Produto.trim()+"'" +
	            							 " AND oid_Tipo_Produto <> "+oid))
	        throw new Mensagens("Já Existe outro Tipo de Produto com a Descrição informada!");
	    
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Tipo_Produto do DSN
			// o NM_Tipo_Produto de usuário e a senha do banco.
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
		buff.append("UPDATE Tipos_Produtos SET NM_Tipo_Produto=?, Dt_Stamp=?, Usuario_Stamp=?, Dm_Stamp=? ");
		buff.append("WHERE OID_Tipo_Produto=?");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setString(1, getNM_Tipo_Produto());
			pstmt.setString(2, getDt_Stamp());
			pstmt.setString(3, getUsuario_Stamp());
			pstmt.setString(4, getDm_Stamp());
			pstmt.setInt(5, getOID());
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
			// passando como parâmetro o NM_Tipo_Produto do DSN
			// o NM_Tipo_Produto de usuário e a senha do banco.
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
		buff.append("DELETE FROM Tipos_Produtos ");
		buff.append("WHERE OID_Tipo_Produto=?");
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

	public static final Tipo_ProdutoBean getByOID(int oid)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Tipo_Produto do DSN
			// o NM_Tipo_Produto de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		Tipo_ProdutoBean p = new Tipo_ProdutoBean();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Tipo_Produto, ");
			buff.append("	CD_Tipo_Produto, ");
			buff.append("	NM_Tipo_Produto ");
			buff.append("FROM Tipos_Produtos ");
			buff.append("WHERE OID_Tipo_Produto= ");
			buff.append(oid);

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				p.setOID(cursor.getInt(1));
				p.setCD_Tipo_Produto(cursor.getString(2));
				p.setNM_Tipo_Produto(cursor.getString(3));
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

	public static final Tipo_ProdutoBean getByCD_Tipo_Produto(String CD_Tipo_Produto)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Tipo_Produto do DSN
			// o NM_Tipo_Produto de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		Tipo_ProdutoBean p = new Tipo_ProdutoBean();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Tipo_Produto, ");
			buff.append("	CD_Tipo_Produto, ");
			buff.append("	NM_Tipo_Produto ");
			buff.append("FROM Tipos_Produtos ");
			buff.append("WHERE CD_Tipo_Produto= '");
			buff.append(CD_Tipo_Produto);
			buff.append("'");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				p.setOID(cursor.getInt(1));
				p.setCD_Tipo_Produto(cursor.getString(2));
				p.setNM_Tipo_Produto(cursor.getString(3));
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

	public static final List getByNM_Tipo_Produto(String NM_Tipo_Produto)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Tipo_Produto do DSN
			// o NM_Tipo_Produto de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		List Tipos_Produtos_Lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Tipo_Produto, ");
			buff.append("	CD_Tipo_Produto, ");
			buff.append("	NM_Tipo_Produto ");
			buff.append("FROM Tipos_Produtos ");
			buff.append("WHERE NM_Tipo_Produto LIKE'");
			buff.append(NM_Tipo_Produto);
			buff.append("%'");
			buff.append(" ORDER BY CD_Tipo_Produto");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				Tipo_ProdutoBean p = new Tipo_ProdutoBean();
				p.setOID(cursor.getInt(1));
				p.setCD_Tipo_Produto(cursor.getString(2));
				p.setNM_Tipo_Produto(cursor.getString(3));
				Tipos_Produtos_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return Tipos_Produtos_Lista;
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

		List Tipos_Produtos_Lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Tipo_Produto, ");
			buff.append("	CD_Tipo_Produto, ");
			buff.append("	NM_Tipo_Produto ");
			buff.append("FROM Tipos_Produtos");
			buff.append(" ORDER BY CD_Tipo_Produto");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				Tipo_ProdutoBean p = new Tipo_ProdutoBean();
				p.setOID(cursor.getInt(1));
				p.setCD_Tipo_Produto(cursor.getString(2));
				p.setNM_Tipo_Produto(cursor.getString(3));
				Tipos_Produtos_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return Tipos_Produtos_Lista;
	}

	public static void main(String args[])
		throws Exception
	{
		Tipo_ProdutoBean pp = new Tipo_ProdutoBean();
		pp.setOID(2);
		pp.setCD_Tipo_Produto("LLL");
		pp.setNM_Tipo_Produto("Litro");
		pp.insert();

		Tipo_ProdutoBean p = getByOID(2);
		// //// System.out.println(p.getOID());
		// //// System.out.println(p.getCD_Tipo_Produto());
		// //// System.out.println(p.getNM_Tipo_Produto());



	}
}
