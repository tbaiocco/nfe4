package com.master.root;
import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import auth.OracleConnection2;

import com.master.util.ManipulaArquivo;

public class ProdutoBean_old
{
	private String CD_Produto;
	private String NM_Produto;
	private String Usuario_Stamp;
	private String Dt_Stamp;
	private String Dm_Stamp;
	private int oid;

	public ProdutoBean_old() {}

	public String getCD_Produto()
	{
		return CD_Produto;
	}
	public void setCD_Produto(String CD_Produto)
	{
		this.CD_Produto = CD_Produto;
	}
	public String getNM_Produto()
	{
		return NM_Produto;
	}
	public void setNM_Produto(String NM_Produto)
	{
		this.NM_Produto = NM_Produto;
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
			// passando como parâmetro o NM_Produto do DSN
			// o NM_Produto de usuário e a senha do banco.
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
				"SELECT MAX(OID_Produto) FROM Produtos");

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
		buff.append("INSERT INTO Produtos (OID_Produto, CD_Produto, NM_Produto, Dt_Stamp, Usuario_Stamp, Dm_Stamp) ");
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
			pstmt.setString(2, getCD_Produto());
			pstmt.setString(3, getNM_Produto());
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
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Produto do DSN
			// o NM_Produto de usuário e a senha do banco.
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
		buff.append("UPDATE Produtos SET NM_Produto=?, Dt_Stamp=?, Usuario_Stamp=?, Dm_Stamp=? ");
		buff.append("WHERE OID_Produto=?");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setString(1, getNM_Produto());
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
			// passando como parâmetro o NM_Produto do DSN
			// o NM_Produto de usuário e a senha do banco.
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
		buff.append("DELETE FROM Produtos ");
		buff.append("WHERE OID_Produto=?");
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

	public static final ProdutoBean getByOID(int oid)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Produto do DSN
			// o NM_Produto de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		ProdutoBean p = new ProdutoBean();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Produto, ");
			buff.append("	CD_Produto, ");
			buff.append("	NM_Produto ");
			buff.append("FROM Produtos ");
			buff.append("WHERE OID_Produto= ");
			buff.append(oid);

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				p.setOID(cursor.getInt(1));
				p.setCD_Produto(cursor.getString(2));
				p.setNM_Produto(cursor.getString(3));
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

	public static final ProdutoBean getByCD_Produto(String CD_Produto)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Produto do DSN
			// o NM_Produto de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		ProdutoBean p = new ProdutoBean();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Produto, ");
			buff.append("	CD_Produto, ");
			buff.append("	NM_Produto ");
			buff.append("FROM Produtos ");
			buff.append("WHERE CD_Produto= '");
			buff.append(CD_Produto);
			buff.append("'");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				p.setOID(cursor.getInt(1));
				p.setCD_Produto(cursor.getString(2));
				p.setNM_Produto(cursor.getString(3));
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

	public static final List getByNM_Produto(String NM_Produto)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Produto do DSN
			// o NM_Produto de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		List Produtos_Lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Produto, ");
			buff.append("	CD_Produto, ");
			buff.append("	NM_Produto ");
			buff.append("FROM Produtos ");
			buff.append("WHERE NM_Produto LIKE'");
			buff.append(NM_Produto);
			buff.append("%'");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				ProdutoBean p = new ProdutoBean();
				p.setOID(cursor.getInt(1));
				p.setCD_Produto(cursor.getString(2));
				p.setNM_Produto(cursor.getString(3));
				Produtos_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return Produtos_Lista;
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

		List Produtos_Lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Produto, ");
			buff.append("	CD_Produto, ");
			buff.append("	NM_Produto ");
			buff.append("FROM Produtos");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				ProdutoBean p = new ProdutoBean();
				p.setOID(cursor.getInt(1));
				p.setCD_Produto(cursor.getString(2));
				p.setNM_Produto(cursor.getString(3));
				Produtos_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return Produtos_Lista;
	}


	public void insert_Puras() throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Produto do DSN
			// o NM_Produto de usuário e a senha do banco.
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
		buff.append("INSERT INTO produtosedi (CD_Produto, NM_Produto, nm_unidade_produto) ");
		buff.append("VALUES (?,?,?)");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setString(1, getCD_Produto());
			pstmt.setString(2, getNM_Produto().toUpperCase());
			pstmt.setString(3, getDm_Stamp().toUpperCase());
			pstmt.executeUpdate();
		} catch (Exception e)
		{
			conn.rollback();
			conn.close();
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
	
	
	
	public void importa_Produto() throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Produto do DSN
			// o NM_Produto de usuário e a senha do banco.
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

                  ManipulaArquivo man = new ManipulaArquivo(";");

                  BufferedReader buff = man.leArquivo("//data//edi//puras//arq.txt");

                    //Tod a esta rotina terá de ser feita para ler uma linha
                    //e depois ler cada elemento
                    //separado pelo delimitador
                  StringTokenizer st = null;
                  String a = null;
                  while ((a = buff.readLine()) != null){

                    st = new StringTokenizer(a, ";");
                    while (st.hasMoreTokens()) {

                        this.CD_Produto = st.nextToken();
                        this.NM_Produto = st.nextToken();
                        this.Dm_Stamp = st.nextToken();

                        this.insert_Puras();
                    }
                  }

		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
	}  
	
	
	
	
	

	public void importa_CT_Basf() throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Produto do DSN
			// o NM_Produto de usuário e a senha do banco.
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

          ManipulaArquivo man = new ManipulaArquivo(";");

          BufferedReader buff = man.leArquivo("//data//edi//basf//arquivo.txt");

            //Tod a esta rotina terá de ser feita para ler uma linha
            //e depois ler cada elemento
            //separado pelo delimitador
          StringTokenizer st = null;
          String a = null;
          while ((a = buff.readLine()) != null){

            st = new StringTokenizer(a, ";");
            while (st.hasMoreTokens()) {

	            this.CD_Produto = st.nextToken();
	            this.NM_Produto = st.nextToken();
	
	    		StringBuffer buff2 = new StringBuffer();
	    		buff2.append(" UPDATE Notas_Fiscais SET nr_pedido=? ");
	    		buff2.append(" WHERE nr_pedido is null ");
	    		buff2.append(" AND nr_nota_fiscal=?");
	    		/*
	    		 * Define os dados do SQL
	    		 * e executa o insert no banco.
	    		 */
	    		try
	    		{
	    			PreparedStatement pstmt =
	    				conn.prepareStatement(buff2.toString());
	    			pstmt.setString(1, getNM_Produto());
	    			pstmt.setString(2, getCD_Produto());
	    			pstmt.executeUpdate();
	    		} catch (Exception e)
	    		{
	    			conn.rollback();
	    			e.printStackTrace();
	    			throw e;
	    		}
            }
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

		  } catch (Exception e)
		  {
			e.printStackTrace();
			throw e;
		  }
	}
	
	
	public static void main(String args[])
		throws Exception
	{
		ProdutoBean_old pp = new ProdutoBean_old();
		pp.importa_Produto();

	}
}