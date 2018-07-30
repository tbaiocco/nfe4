package com.master.root;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.rl.Vencimento_VeiculoRL;
import com.master.util.JavaUtil;

import auth.OracleConnection2;

public class Vencimento_VeiculoBean
{
	private String DT_Vencimento;
	private String NM_Complemento;
	private double VL_Previsto;
	private String Usuario_Stamp;
	private String Dt_Stamp;
	private String Dm_Stamp; 
	private int oid;
	private int oid_Tipo_Vencimento;
	private int oid_Pais;	
	private String NM_Tipo_Vencimento;
	private String NM_Pais;
	private String oid_Veiculo;	
	
	
	
	public Vencimento_VeiculoBean() 
	{
		NM_Complemento=" ";
	} 
	
	public String getDT_Vencimento() 
	{
		FormataDataBean DataFormatada = new FormataDataBean();
		DataFormatada.setDT_FormataData(DT_Vencimento);
		DT_Vencimento = DataFormatada.getDT_FormataData();
		
		return DT_Vencimento;
	}
	public void setDT_Vencimento(String DT_Vencimento) 
	{
		this.DT_Vencimento = DT_Vencimento;
	}
	public String getNM_Complemento() 
	{
		return NM_Complemento;
	}
	public void setNM_Complemento(String NM_Complemento) 
	{
		this.NM_Complemento = NM_Complemento;
	}
	
	public String getOID_Veiculo() 
	{
		return oid_Veiculo;
	}
	public void setOID_Veiculo(String oid_Veiculo) 
	{
		this.oid_Veiculo = oid_Veiculo;
	}
	
	
	public double getVL_Previsto() 
	{
		return VL_Previsto;
	}
	public void setVL_Previsto(double VL_Previsto) 
	{
		this.VL_Previsto = VL_Previsto;
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
	public int getOID_Tipo_Vencimento() 
	{
		return oid_Tipo_Vencimento;
	}
	public void setOID_Tipo_Vencimento(int n) 
	{
		this.oid_Tipo_Vencimento = n;
	}	
	public int getOID_Pais() 
	{
		return oid_Pais;
	}
	public void setOID_Pais(int n) 
	{
		this.oid_Pais = n;
	}
	public String getNM_Pais() 
	{
		return NM_Pais;
	}
	public void setNM_Pais(String NM_Pais) 
	{
		this.NM_Pais = NM_Pais;
	}
	public String getNM_Tipo_Vencimento() 
	{
		return NM_Tipo_Vencimento;
	}
	public void setNM_Tipo_Vencimento(String NM_Tipo_Vencimento) 
	{
		this.NM_Tipo_Vencimento = NM_Tipo_Vencimento;
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
			// passando como parâmetro o NM_Usuario do DSN 
			// o NM_Usuario de usuário e a URL do banco.
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
				"SELECT MAX(OID_Vencimento_Veiculo) FROM Vencimentos_Veiculos");
			
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
		
		StringBuffer buff = new StringBuffer();
		buff.append("INSERT INTO Vencimentos_Veiculos (OID_Vencimento_Veiculo, OID_Veiculo, OID_Pais, OID_Tipo_Vencimento, DT_Vencimento, NM_Complemento, VL_Previsto, DT_Stamp, Usuario_Stamp, Dm_Stamp) ");
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
			pstmt.setString(2, getOID_Veiculo());			
			pstmt.setInt(3, getOID_Pais());
			pstmt.setInt(4, getOID_Tipo_Vencimento());
			pstmt.setString(5, this.DT_Vencimento);
			pstmt.setString(6, getNM_Complemento());
			pstmt.setDouble(7, getVL_Previsto());			
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
			// passando como parâmetro o NM_Complemento do DSN 
			// o NM_Complemento de usuário e a senha do banco.
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
		buff.append("UPDATE Vencimentos_Veiculos SET OID_Pais=?, OID_Tipo_Vencimento=?, NM_Complemento=?, VL_Previsto=?, DT_Vencimento=? ");
		buff.append("WHERE OID_Vencimento_Veiculo=?");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try 
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			
			pstmt.setInt(1, getOID_Pais());
			pstmt.setInt(2, getOID_Tipo_Vencimento());
			pstmt.setString(3, getNM_Complemento());
			pstmt.setDouble(4, getVL_Previsto());	
			pstmt.setString(5, this.DT_Vencimento);
			pstmt.setInt(6, getOID());			
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
			// passando como parâmetro o NM_Complemento do DSN 
			// o NM_Complemento de usuário e a senha do banco.
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
		buff.append("DELETE FROM Vencimentos_Veiculos ");
		buff.append("WHERE OID_Vencimento_Veiculo=?");
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
	
	
	public static final List getByNR_Placa(String NR_Placa)
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
		
		List Vencimento_Veiculos_Lista = new ArrayList();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT Vencimentos_Veiculos.OID_Vencimento_Veiculo, ");
			buff.append("	Vencimentos_Veiculos.OID_Pais, ");			
			buff.append("	Vencimentos_Veiculos.OID_Veiculo, ");						
			buff.append("	Vencimentos_Veiculos.OID_Tipo_Vencimento, ");			
			buff.append("	Vencimentos_Veiculos.DT_Vencimento, ");
			buff.append("	Vencimentos_Veiculos.NM_Complemento, ");
			buff.append("	Vencimentos_Veiculos.VL_Previsto,  ");										
			buff.append("	Paises.NM_Pais,  ");			
			buff.append("	Tipos_Vencimentos.NM_Tipo_Vencimento  ");
			buff.append(" FROM Vencimentos_Veiculos, ");
			buff.append("      Paises, ");									
			buff.append("      Tipos_Vencimentos ");									
			buff.append(" WHERE Vencimentos_Veiculos.oid_Tipo_Vencimento = Tipos_Vencimentos.oid_Tipo_Vencimento ");			
			buff.append("  AND  Vencimentos_Veiculos.oid_Pais 		     = Paises.oid_Pais ");						
			buff.append("  AND  Vencimentos_Veiculos.OID_Veiculo = '");
			buff.append(NR_Placa);
			buff.append("'");
			
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = 
				stmt.executeQuery(buff.toString()); 
			
			while (cursor.next()) 
			{
				Vencimento_VeiculoBean p = new Vencimento_VeiculoBean(); 
				p.setOID(cursor.getInt(1));
				p.setOID_Pais(cursor.getInt(2));
				p.setOID_Veiculo(cursor.getString(3));				
				p.setOID_Tipo_Vencimento(cursor.getInt(4));
				p.setDT_Vencimento(cursor.getString(5));				
				p.setNM_Complemento(cursor.getString(6));				
				p.setVL_Previsto(cursor.getDouble(7));
				p.setNM_Pais(cursor.getString(8));																
				p.setNM_Tipo_Vencimento(cursor.getString(9));												
				Vencimento_Veiculos_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)  
		{
			e.printStackTrace();
		}
		return Vencimento_Veiculos_Lista;
	}	
	
	
	
	public static final Vencimento_VeiculoBean getByOID(String oid) 
		throws Exception 
	{
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try 
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NM_Complemento do DSN 
			// o NM_Complemento de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
		
		Vencimento_VeiculoBean p = new Vencimento_VeiculoBean();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT Vencimentos_Veiculos.OID_Vencimento_Veiculo, ");
			buff.append("	Vencimentos_Veiculos.OID_Pais, ");			
			buff.append("	Vencimentos_Veiculos.OID_Veiculo, ");						
			buff.append("	Vencimentos_Veiculos.OID_Tipo_Vencimento, ");			
			buff.append("	Vencimentos_Veiculos.DT_Vencimento, ");
			buff.append("	Vencimentos_Veiculos.NM_Complemento, ");
			buff.append("	Vencimentos_Veiculos.VL_Previsto,  ");										
			buff.append("	Paises.NM_Pais,  ");			
			buff.append("	Tipos_Vencimentos.NM_Tipo_Vencimento  ");
			buff.append(" FROM Vencimentos_Veiculos, ");
			buff.append("      Paises, ");									
			buff.append("      Tipos_Vencimentos ");									
			buff.append(" WHERE (Vencimentos_Veiculos.oid_Tipo_Vencimento = Tipos_Vencimentos.oid_Tipo_Vencimento ");			
			buff.append(" AND    Vencimentos_Veiculos.oid_Pais 		     = Paises.oid_Pais )");						
			buff.append("AND OID_Vencimento_Veiculo= '");			
			buff.append(oid);
			buff.append("'");
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = 
				stmt.executeQuery(buff.toString());
			
			while (cursor.next()) 
			{
				p.setOID(cursor.getInt(1));
				p.setOID_Pais(cursor.getInt(2));
				p.setOID_Veiculo(cursor.getString(3));				
				p.setOID_Tipo_Vencimento(cursor.getInt(4));
				p.setDT_Vencimento(cursor.getString(5));				
				p.setNM_Complemento(cursor.getString(6));				
				p.setVL_Previsto(cursor.getDouble(7));
				p.setNM_Pais(cursor.getString(8));																
				p.setNM_Tipo_Vencimento(cursor.getString(9));												
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
		
		List Vencimento_Veiculos_Lista = new ArrayList();
		try 
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT Vencimentos_Veiculos.OID_Vencimento_Veiculo, ");
			buff.append("	Vencimentos_Veiculos.OID_Pais, ");			
			buff.append("	Vencimentos_Veiculos.OID_Veiculo, ");						
			buff.append("	Vencimentos_Veiculos.OID_Tipo_Vencimento, ");			
			buff.append("	Vencimentos_Veiculos.DT_Vencimento, ");
			buff.append("	Vencimentos_Veiculos.NM_Complemento, ");
			buff.append("	Vencimentos_Veiculos.VL_Previsto,  ");										
			buff.append("	Paises.NM_Pais,  ");			
			buff.append("	Tipos_Vencimentos.NM_Tipo_Vencimento  ");
			buff.append(" FROM Vencimentos_Veiculos, ");
			buff.append("      Paises, ");									
			buff.append("      Tipos_Vencimentos ");									
			buff.append(" WHERE Vencimentos_Veiculos.oid_Tipo_Vencimento = Tipos_Vencimentos.oid_Tipo_Vencimento ");			
			buff.append(" AND    Vencimentos_Veiculos.oid_Pais 		     = Paises.oid_Pais ");						
			
			
			Statement stmt = conn.createStatement();
			ResultSet cursor = 
				stmt.executeQuery(buff.toString()); 
			
			while (cursor.next()) 
			{
				Vencimento_VeiculoBean p = new Vencimento_VeiculoBean(); 
				p.setOID(cursor.getInt(1));
				p.setOID_Pais(cursor.getInt(2));
				p.setOID_Veiculo(cursor.getString(3));				
				p.setOID_Tipo_Vencimento(cursor.getInt(4));
				p.setDT_Vencimento(cursor.getString(5));				
				p.setNM_Complemento(cursor.getString(6));				
				p.setVL_Previsto(cursor.getDouble(7));
				p.setNM_Pais(cursor.getString(8));																
				p.setNM_Tipo_Vencimento(cursor.getString(9));												
				Vencimento_Veiculos_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e)  
		{
			e.printStackTrace();
		}
		return Vencimento_Veiculos_Lista;
	}	
	
	
	
	public static void main(String args[]) 
		throws Exception 
	{
		
		
	}
	
	public void geraRelVCTO(HttpServletRequest request, HttpServletResponse response)
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
	
	ArrayList Vencimento_Veiculos_Lista = new ArrayList();
	try 
	{
		String NR_Placa = request.getParameter("oid_Veiculo");
		String oid = request.getParameter("oid_Tipo_Vencimento");
		String DT_Inicio = request.getParameter("FT_DT_Vencimento");
		String DT_Fim = request.getParameter("FT_DT_Vencimento");
		
		Vencimento_VeiculoBean ed = new Vencimento_VeiculoBean();
		
		StringBuffer buff = new StringBuffer();
		buff.append("SELECT Vencimentos_Veiculos.OID_Vencimento_Veiculo, ");
		buff.append("	Vencimentos_Veiculos.OID_Pais, ");			
		buff.append("	Vencimentos_Veiculos.OID_Veiculo, ");						
		buff.append("	Vencimentos_Veiculos.OID_Tipo_Vencimento, ");			
		buff.append("	Vencimentos_Veiculos.DT_Vencimento, ");
		buff.append("	Vencimentos_Veiculos.NM_Complemento, ");
		buff.append("	Vencimentos_Veiculos.VL_Previsto,  ");										
		buff.append("	Paises.NM_Pais,  ");			
		buff.append("	Tipos_Vencimentos.NM_Tipo_Vencimento  ");
		buff.append(" FROM Vencimentos_Veiculos, ");
		buff.append("      Paises, ");									
		buff.append("      Tipos_Vencimentos ");									
		buff.append(" WHERE Vencimentos_Veiculos.oid_Tipo_Vencimento = Tipos_Vencimentos.oid_Tipo_Vencimento ");			
		buff.append("  AND  Vencimentos_Veiculos.oid_Pais 		     = Paises.oid_Pais ");	
		
		if(JavaUtil.doValida(NR_Placa)){
			buff.append("  AND  Vencimentos_Veiculos.OID_Veiculo = '");
			buff.append(NR_Placa);
			buff.append("'");
			ed.setOID_Veiculo(NR_Placa);
		}
		if(JavaUtil.doValida(DT_Inicio)){
			buff.append("  AND  Vencimentos_Veiculos.DT_Vencimento >= '");
			buff.append(DT_Inicio);
			buff.append("'");
		}
		if(JavaUtil.doValida(DT_Fim)){
			buff.append("  AND  Vencimentos_Veiculos.DT_Vencimento <= '");
			buff.append(DT_Fim);
			buff.append("'");
		}
		if(JavaUtil.doValida(String.valueOf(oid))){
			buff.append("  AND  Vencimentos_Veiculos.OID_Tipo_Vencimento = '");
			buff.append(oid);
			buff.append("'");
			ed.setOID_Tipo_Vencimento(new Integer(oid).intValue());
		}
		
		buff.append(" ORDER BY Vencimentos_Veiculos.OID_Veiculo, Vencimentos_Veiculos.DT_Vencimento ");
// System.out.println("BUFF >"+buff.toString());
		Statement stmt = conn.createStatement();
		ResultSet cursor = 
			stmt.executeQuery(buff.toString()); 
		
		while (cursor.next()) 
		{
			Vencimento_VeiculoBean p = new Vencimento_VeiculoBean(); 
			p.setOID(cursor.getInt(1));
			p.setOID_Pais(cursor.getInt(2));
			p.setOID_Veiculo(cursor.getString(3));				
			p.setOID_Tipo_Vencimento(cursor.getInt(4));
			p.setDT_Vencimento(cursor.getString(5));				
			p.setNM_Complemento(cursor.getString(6));				
			p.setVL_Previsto(cursor.getDouble(7));
			p.setNM_Pais(cursor.getString(8));																
			p.setNM_Tipo_Vencimento(cursor.getString(9));												
			Vencimento_Veiculos_Lista.add(p);
		}
		cursor.close();
		stmt.close();
		conn.close();
		
		new Vencimento_VeiculoRL().geraRelVCTO(Vencimento_Veiculos_Lista, response, ed);
		
	} catch (Exception e)  
	{
		e.printStackTrace();
	}
	
}
	
	
}
