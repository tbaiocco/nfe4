package com.master.root;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.master.ed.Forma_PagamentoED;
import com.master.rn.Forma_PagamentoRN;
import com.master.util.BancoUtil;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;

import auth.OracleConnection2;

public class Condicao_PagamentoBean 
{
    
    private ArrayList listaFormasPag = new ArrayList(); 
    
	private String CD_Condicao_Pagamento;
	private String NM_Condicao_Pagamento;
	private String Usuario_Stamp;
	private String Dt_Stamp;
	private String Dm_Stamp;
	private int oid;
	private long NR_Dias_Vencimento1;
	private long NR_Dias_Vencimento2;
	private long NR_Dias_Vencimento3;
	private long NR_Dias_Vencimento4;
	private long NR_Dias_Vencimento5;

	public Condicao_PagamentoBean() {}

	public String getCD_Condicao_Pagamento()
	{
		return CD_Condicao_Pagamento;
	}
	public void setCD_Condicao_Pagamento(String CD_Condicao_Pagamento)
	{
		this.CD_Condicao_Pagamento = CD_Condicao_Pagamento;
	}
	public String getNM_Condicao_Pagamento()
	{
		return NM_Condicao_Pagamento;
	}
	public void setNM_Condicao_Pagamento(String NM_Condicao_Pagamento)
	{
		this.NM_Condicao_Pagamento = NM_Condicao_Pagamento;
	}
	public long getNR_Dias_Vencimento1()
	{
		return NR_Dias_Vencimento1;
	}
	public void setNR_Dias_Vencimento1(long NR_Dias_Vencimento1)
	{
		this.NR_Dias_Vencimento1 = NR_Dias_Vencimento1;
	}
	public long getNR_Dias_Vencimento2()
	{
		return NR_Dias_Vencimento2;
	}
	public void setNR_Dias_Vencimento2(long NR_Dias_Vencimento2)
	{
		this.NR_Dias_Vencimento2 = NR_Dias_Vencimento2;
	}
	public long getNR_Dias_Vencimento3()
	{
		return NR_Dias_Vencimento3;
	}
	public void setNR_Dias_Vencimento3(long NR_Dias_Vencimento3)
	{
		this.NR_Dias_Vencimento3 = NR_Dias_Vencimento3;
	}
	public long getNR_Dias_Vencimento4()
	{
		return NR_Dias_Vencimento4;
	}
	public void setNR_Dias_Vencimento4(long NR_Dias_Vencimento4)
	{
		this.NR_Dias_Vencimento4 = NR_Dias_Vencimento4;
	}
	public long getNR_Dias_Vencimento5()
	{
		return NR_Dias_Vencimento5;
	}
	public void setNR_Dias_Vencimento5(long NR_Dias_Vencimento5)
	{
		this.NR_Dias_Vencimento5 = NR_Dias_Vencimento5;
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
        if (new BancoUtil().doExiste("Condicoes_Pagamentos", "CD_Condicao_Pagamento = '"+getCD_Condicao_Pagamento()+"'"))
        {
            throw new Mensagens("Ja existe uma Condição de Pagamento com o Código informado!");
        }
		/*
		 * Abre a conexão com o banco
		 */
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
		 * Gera um novo código (OID)
		 */
		try
		{
			Statement stmt = conn.createStatement();
			ResultSet cursor = stmt.executeQuery(
				"SELECT MAX(OID_Condicao_Pagamento) FROM Condicoes_Pagamentos");

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
		buff.append("INSERT INTO Condicoes_Pagamentos (OID_Condicao_Pagamento, CD_Condicao_Pagamento, NM_Condicao_Pagamento, NR_Dias_Vencimento1, NR_Dias_Vencimento2, NR_Dias_Vencimento5, NR_Dias_Vencimento3, NR_Dias_Vencimento4, Dt_Stamp, Usuario_Stamp, Dm_Stamp) ");
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
			pstmt.setString(2, getCD_Condicao_Pagamento());
			pstmt.setString(3, getNM_Condicao_Pagamento());
			pstmt.setLong(4, getNR_Dias_Vencimento1());
			pstmt.setLong(5, getNR_Dias_Vencimento2());
			pstmt.setLong(6, getNR_Dias_Vencimento5());
			pstmt.setLong(7, getNR_Dias_Vencimento3());
			pstmt.setLong(8, getNR_Dias_Vencimento4());
			pstmt.setString(9, getDt_Stamp());
			pstmt.setString(10, getUsuario_Stamp());
			pstmt.setString(11, getDm_Stamp());
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
		buff.append("UPDATE Condicoes_Pagamentos SET NM_Condicao_Pagamento=?, NR_Dias_Vencimento1=?, NR_Dias_Vencimento2=?, NR_Dias_Vencimento5=?, NR_Dias_Vencimento3=?, NR_Dias_Vencimento4=?, Dt_Stamp=?, Usuario_Stamp=?, Dm_Stamp=? ");
		buff.append("WHERE OID_Condicao_Pagamento=?");
		/*
		 * Define os dados do SQL
		* e executa o insert no banco.
		*/
		try
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setString(1, getNM_Condicao_Pagamento());
			pstmt.setLong(2, getNR_Dias_Vencimento1());
			pstmt.setLong(3, getNR_Dias_Vencimento2());
			pstmt.setLong(4, getNR_Dias_Vencimento5());
			pstmt.setLong(5, getNR_Dias_Vencimento3());
			pstmt.setLong(6, getNR_Dias_Vencimento4());
			pstmt.setString(7, getDt_Stamp());
			pstmt.setString(8, getUsuario_Stamp());
			pstmt.setString(9, getDm_Stamp());
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

	public void update_NR_Dias_Vencimento5() throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
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
		 * Define o update.
		 */
		StringBuffer buff = new StringBuffer();
		buff.append("UPDATE Condicoes_Pagamentos SET NR_Dias_Vencimento5=?, Dt_Stamp=?, Usuario_Stamp=?, Dm_Stamp=? ");
		buff.append("WHERE OID_Condicao_Pagamento=?");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setLong(  1, getNR_Dias_Vencimento5());
			pstmt.setString(2, getDt_Stamp());
			pstmt.setString(3, getUsuario_Stamp());
			pstmt.setString(4, getDm_Stamp());
			pstmt.setInt(   5, getOID());
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
		buff.append("DELETE FROM Condicoes_Pagamentos ");
		buff.append("WHERE OID_Condicao_Pagamento=?");
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
	
	public static final Condicao_PagamentoBean getByOID(String oid) throws Exception
	{
	    if (JavaUtil.doValida(oid)) {
	        return Condicao_PagamentoBean.getByOID(Integer.parseInt(oid));
        } else return new Condicao_PagamentoBean();
	}
	public static final Condicao_PagamentoBean getByOID(int oid)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
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

		Condicao_PagamentoBean p = new Condicao_PagamentoBean();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Condicao_Pagamento, ");
			buff.append("	CD_Condicao_Pagamento, ");
			buff.append("	NM_Condicao_Pagamento, ");
			buff.append("	NR_Dias_Vencimento1, ");
			buff.append("	NR_Dias_Vencimento2, ");
			buff.append("	NR_Dias_Vencimento5, ");
			buff.append("	NR_Dias_Vencimento3, ");
			buff.append("	NR_Dias_Vencimento4 ");
			buff.append("FROM Condicoes_Pagamentos ");
			buff.append("WHERE OID_Condicao_Pagamento= ");
			buff.append(oid);

			Statement stmt = conn.createStatement();
			ResultSet cursor = stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				p.setOID(cursor.getInt(1));
				p.setCD_Condicao_Pagamento(cursor.getString(2));
				p.setNM_Condicao_Pagamento(cursor.getString(3));
				p.setNR_Dias_Vencimento1(cursor.getLong(4));
				p.setNR_Dias_Vencimento2(cursor.getLong(5));
				p.setNR_Dias_Vencimento5(cursor.getLong(6));
				p.setNR_Dias_Vencimento3(cursor.getLong(7));
				p.setNR_Dias_Vencimento4(cursor.getLong(8));
				//*** Carrega lista com Formas de Pagamento
				Forma_PagamentoED ed = new Forma_PagamentoED();
				ed.setOid_Condicao_Pagamento(p.getOID());
				p.setListaFormasPag(new Forma_PagamentoRN().lista(ed)); 
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

	public static final Condicao_PagamentoBean getByCD_Condicao_Pagamento(String CD_Condicao_Pagamento) throws Exception
	{
	    Condicao_PagamentoBean p = new Condicao_PagamentoBean();
		/*
		 * Abre a conexão com o banco
		 */
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

		
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Condicao_Pagamento, ");
			buff.append("	CD_Condicao_Pagamento, ");
			buff.append("	NM_Condicao_Pagamento, ");
			buff.append("	NR_Dias_Vencimento1, ");
			buff.append("	NR_Dias_Vencimento2, ");
			buff.append("	NR_Dias_Vencimento5, ");
			buff.append("	NR_Dias_Vencimento3, ");
			buff.append("	NR_Dias_Vencimento4 ");
			buff.append("FROM Condicoes_Pagamentos ");
			buff.append("WHERE CD_Condicao_Pagamento= '");
			buff.append(CD_Condicao_Pagamento);
			buff.append("'");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				p.setOID(cursor.getInt(1));
				p.setCD_Condicao_Pagamento(cursor.getString(2));
				p.setNM_Condicao_Pagamento(cursor.getString(3));
				p.setNR_Dias_Vencimento1(cursor.getLong(4));
				p.setNR_Dias_Vencimento2(cursor.getLong(5));
				p.setNR_Dias_Vencimento5(cursor.getLong(6));
				p.setNR_Dias_Vencimento3(cursor.getLong(7));
				p.setNR_Dias_Vencimento4(cursor.getLong(8));
				//*** Carrega lista com Formas de Pagamento
				Forma_PagamentoED ed = new Forma_PagamentoED();
				ed.setOid_Condicao_Pagamento(p.getOID());
				p.setListaFormasPag(new Forma_PagamentoRN().lista(ed));
				
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

	public static final List getByNM_Condicao_Pagamento(String NM_Condicao_Pagamento)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
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

		List lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Condicao_Pagamento, ");
			buff.append("	CD_Condicao_Pagamento, ");
			buff.append("	NM_Condicao_Pagamento, ");
			buff.append("	NR_Dias_Vencimento1, ");
			buff.append("	NR_Dias_Vencimento2, ");
			buff.append("	NR_Dias_Vencimento5, ");
			buff.append("	NR_Dias_Vencimento3, ");
			buff.append("	NR_Dias_Vencimento4 ");
			buff.append("FROM Condicoes_Pagamentos ");
			buff.append("WHERE NM_Condicao_Pagamento LIKE'");
			buff.append(NM_Condicao_Pagamento);
			buff.append("%'");

			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				Condicao_PagamentoBean p = new Condicao_PagamentoBean();
				p.setOID(cursor.getInt(1));
				p.setCD_Condicao_Pagamento(cursor.getString(2));
				p.setNM_Condicao_Pagamento(cursor.getString(3));
				p.setNR_Dias_Vencimento1(cursor.getLong(4));
				p.setNR_Dias_Vencimento2(cursor.getLong(5));
				p.setNR_Dias_Vencimento5(cursor.getLong(6));
				p.setNR_Dias_Vencimento3(cursor.getLong(7));
				p.setNR_Dias_Vencimento4(cursor.getLong(8));
				//*** Carrega lista com Formas de Pagamento
				Forma_PagamentoED ed = new Forma_PagamentoED();
				ed.setOid_Condicao_Pagamento(p.getOID());
				p.setListaFormasPag(new Forma_PagamentoRN().lista(ed));
				
				lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return lista;
	}

	public static final List getByOID_Condicao_Pagamento(String OID_Condicao_Pagamento)
	throws Exception
	{
		/*
		 * Abre a conexão com o banco
		 */
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
	
		List lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Condicao_Pagamento, ");
			buff.append("	CD_Condicao_Pagamento, ");
			buff.append("	NM_Condicao_Pagamento, ");
			buff.append("	NR_Dias_Vencimento1, ");
			buff.append("	NR_Dias_Vencimento2, ");
			buff.append("	NR_Dias_Vencimento5, ");
			buff.append("	NR_Dias_Vencimento3, ");
			buff.append("	NR_Dias_Vencimento4 ");
			buff.append("FROM Condicoes_Pagamentos ");
			buff.append("WHERE oid_Condicao_Pagamento = ");
			buff.append(OID_Condicao_Pagamento);
	
			Statement stmt = conn.createStatement();
			ResultSet cursor = stmt.executeQuery(buff.toString());
	
			while (cursor.next())
			{
				Condicao_PagamentoBean p = new Condicao_PagamentoBean();
				p.setOID(cursor.getInt(1));
				p.setCD_Condicao_Pagamento(cursor.getString(2));
				p.setNM_Condicao_Pagamento(cursor.getString(3));
				p.setNR_Dias_Vencimento1(cursor.getLong(4));
				p.setNR_Dias_Vencimento2(cursor.getLong(5));
				p.setNR_Dias_Vencimento5(cursor.getLong(6));
				p.setNR_Dias_Vencimento3(cursor.getLong(7));
				p.setNR_Dias_Vencimento4(cursor.getLong(8));
				//*** Carrega lista com Formas de Pagamento
				Forma_PagamentoED ed = new Forma_PagamentoED();
				ed.setOid_Condicao_Pagamento(p.getOID());
				p.setListaFormasPag(new Forma_PagamentoRN().lista(ed));
				lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return lista;
	}
	
	public static final List getAll()
		throws Exception
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

		List lista = new ArrayList();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Condicao_Pagamento, ");
			buff.append("	CD_Condicao_Pagamento, ");
			buff.append("	NM_Condicao_Pagamento, ");
			buff.append("	NR_Dias_Vencimento1, ");
			buff.append("	NR_Dias_Vencimento2, ");
			buff.append("	NR_Dias_Vencimento5, ");
			buff.append("	NR_Dias_Vencimento3, ");
			buff.append("	NR_Dias_Vencimento4 ");
			buff.append("FROM Condicoes_Pagamentos ");
            buff.append("ORDER BY CD_Condicao_Pagamento");

			Statement stmt = conn.createStatement();
			ResultSet cursor = stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				Condicao_PagamentoBean p = new Condicao_PagamentoBean();
				p.setOID(cursor.getInt(1));
				p.setCD_Condicao_Pagamento(cursor.getString(2));
				p.setNM_Condicao_Pagamento(cursor.getString(3));
				p.setNR_Dias_Vencimento1(cursor.getLong(4));
				p.setNR_Dias_Vencimento2(cursor.getLong(5));
				p.setNR_Dias_Vencimento5(cursor.getLong(6));
				p.setNR_Dias_Vencimento3(cursor.getLong(7));
				p.setNR_Dias_Vencimento4(cursor.getLong(8));
				//*** Carrega lista com Formas de Pagamento
				Forma_PagamentoED ed = new Forma_PagamentoED();
				ed.setOid_Condicao_Pagamento(p.getOID());
				p.setListaFormasPag(new Forma_PagamentoRN().lista(ed));
				lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return lista;
	}
    public ArrayList getListaFormasPag() {
        return listaFormasPag;
    }
    public void setListaFormasPag(ArrayList listaFormasPag) {
        this.listaFormasPag = listaFormasPag;
    }
}