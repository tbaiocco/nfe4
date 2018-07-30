package com.master.root;
import java.sql.*;
import java.util.*;

import auth.*;

public class Modelo_VeiculoBean
{
	private String CD_Tipo_Veiculo;
	private String NM_Tipo_Veiculo;
	private String CD_Marca_Veiculo;
	private String NM_Marca_Veiculo;
	private String CD_Modelo_Veiculo;
	private String NM_Modelo_Veiculo;
	private String Usuario_Stamp;
	private String Dt_Stamp;
	private String Dm_Stamp;
	private int oid;
	private int oid_Marca_Veiculo;
	private int oid_Tipo_Veiculo;
    private double PE_Comissao_Motorista;
    private double VL_Faturamento_Dia;
    private double NR_KM_Dia;

    private double NR_Media_KMVazio;
    private double NR_Media_KM20;
    private double NR_Media_KM40;
    private double NR_Media_KM60;
    private double NR_Media_KM80;
    private double NR_Media_KM;

    private long dm_Tipo_Chassis;
	private String nm_Tipo_Chassis;

	public Modelo_VeiculoBean() {
    try {
      jbInit ();
    }
    catch (Exception ex) {
      ex.printStackTrace ();
    }
  }

	public String getCD_Marca_Veiculo()
	{
		return CD_Marca_Veiculo;
	}
	public void setCD_Marca_Veiculo(String CD_Marca_Veiculo)
	{
		this.CD_Marca_Veiculo = CD_Marca_Veiculo;
	}
	public double getPE_Comissao_Motorista() {
		return PE_Comissao_Motorista;
	}

	public void setPE_Comissao_Motorista(double comissao_Motorista) {
		PE_Comissao_Motorista = comissao_Motorista;
	}

	public String getNM_Marca_Veiculo()
	{
		return NM_Marca_Veiculo;
	}
	public void setNM_Marca_Veiculo(String NM_Marca_Veiculo)
	{
		this.NM_Marca_Veiculo = NM_Marca_Veiculo;
	}
	public String getNM_Tipo_Veiculo()
	{
		return NM_Tipo_Veiculo;
	}
	public void setNM_Tipo_Veiculo(String NM_Tipo_Veiculo)
	{
		this.NM_Tipo_Veiculo = NM_Tipo_Veiculo;
	}
	public String getCD_Tipo_Veiculo()
	{
		return CD_Tipo_Veiculo;
	}
	public void setCD_Tipo_Veiculo(String CD_Tipo_Veiculo)
	{
		this.CD_Tipo_Veiculo = CD_Tipo_Veiculo;
	}
	public String getCD_Modelo_Veiculo()
	{
		return CD_Modelo_Veiculo;
	}
	public void setCD_Modelo_Veiculo(String CD_Modelo_Veiculo)
	{
		this.CD_Modelo_Veiculo = CD_Modelo_Veiculo;
	}
	public String getNM_Modelo_Veiculo()
	{
		return NM_Modelo_Veiculo;
	}
	public void setNM_Modelo_Veiculo(String NM_Modelo_Veiculo)
	{
		this.NM_Modelo_Veiculo = NM_Modelo_Veiculo;
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
	public int getOID_Marca_Veiculo()
	{
		return oid_Marca_Veiculo;
	}
	public void setOID_Marca_Veiculo(int n)
	{
		this.oid_Marca_Veiculo = n;
	}
	public int getOID_Tipo_Veiculo()
	{
		return oid_Tipo_Veiculo;
	}
	public void setOID_Tipo_Veiculo(int n)
	{
		this.oid_Tipo_Veiculo = n;
	}



	  private void jbInit () throws Exception {
	  }

	  public double getNR_Media_KM () {

	    return NR_Media_KM;
	  }

	  public void setNR_Media_KM (double NR_Media_KM) {

	    this.NR_Media_KM = NR_Media_KM;
	  }

	public double getVL_Faturamento_Dia() {
		return VL_Faturamento_Dia;
	}

	public void setVL_Faturamento_Dia(double faturamento_Dia) {
		VL_Faturamento_Dia = faturamento_Dia;
	}

	public long getDm_Tipo_Chassis() {
		return dm_Tipo_Chassis;
	}

	public void setDm_Tipo_Chassis(long dm_Tipo_Chassis) {
		this.dm_Tipo_Chassis = dm_Tipo_Chassis;
	}

	public String getNm_Tipo_Chassis() {
		return nm_Tipo_Chassis;
	}

	public void setNm_Tipo_Chassis(String nm_Tipo_Chassis) {
		this.nm_Tipo_Chassis = nm_Tipo_Chassis;
	}

	public double getNR_Media_KM20() {
		return NR_Media_KM20;
	}

	public void setNR_Media_KM20(double media_KM20) {
		NR_Media_KM20 = media_KM20;
	}

	public double getNR_Media_KM40() {
		return NR_Media_KM40;
	}

	public void setNR_Media_KM40(double media_KM40) {
		NR_Media_KM40 = media_KM40;
	}

	public double getNR_Media_KM60() {
		return NR_Media_KM60;
	}

	public void setNR_Media_KM60(double media_KM60) {
		NR_Media_KM60 = media_KM60;
	}

	public double getNR_Media_KM80() {
		return NR_Media_KM80;
	}

	public void setNR_Media_KM80(double media_KM80) {
		NR_Media_KM80 = media_KM80;
	}

	public double getNR_Media_KMVazio() {
		return NR_Media_KMVazio;
	}

	public void setNR_Media_KMVazio(double media_KMVazio) {
		NR_Media_KMVazio = media_KMVazio;
	}


	public double getNR_KM_Dia() {
		return NR_KM_Dia;
	}

	public void setNR_KM_Dia(double dia) {
		NR_KM_Dia = dia;
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
			// passando como parâmetro o NM_Marca_Veiculo do DSN
			// o NM_Marca_Veiculo de usuário e a senha do banco.
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
				"SELECT MAX(OID_Modelo_Veiculo) FROM Modelos_Veiculos");

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
		buff.append(" INSERT INTO Modelos_Veiculos (OID_Modelo_Veiculo, OID_Marca_Veiculo, OID_Tipo_Veiculo, CD_Modelo_Veiculo, NM_Modelo_Veiculo, DM_Tipo_Chassis) ");
		buff.append(" VALUES (?,?,?,?,?,?)");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setInt(1, getOID());
			pstmt.setInt(2, getOID_Marca_Veiculo());
			pstmt.setInt(3, getOID_Tipo_Veiculo());
			pstmt.setString(4, getCD_Modelo_Veiculo());
			pstmt.setString(5, getNM_Modelo_Veiculo());
            pstmt.setLong(6, getDm_Tipo_Chassis());
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
			// passando como parâmetro o NM_Marca_Veiculo do DSN
			// o NM_Marca_Veiculo de usuário e a senha do banco.
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
		buff.append("UPDATE Modelos_Veiculos SET PE_Comissao_Motorista=?, OID_Tipo_Veiculo=?, OID_Marca_Veiculo=?, NM_Modelo_Veiculo=?, CD_Modelo_Veiculo=?, NR_Media_KM=?, VL_Faturamento_Dia=?, DM_Tipo_Chassis=?, NR_Media_KMVazio=?, NR_Media_KM20=?, NR_Media_KM40=?, NR_Media_KM60=?, NR_Media_KM80=?, NR_KM_Dia=? ");
		buff.append("WHERE OID_Modelo_Veiculo=?");

		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setDouble(1, getPE_Comissao_Motorista());
			pstmt.setInt(2, getOID_Tipo_Veiculo());
			pstmt.setInt(3, getOID_Marca_Veiculo());
			pstmt.setString(4, getNM_Modelo_Veiculo());
			pstmt.setString(5, getCD_Modelo_Veiculo());
			pstmt.setDouble(6, getNR_Media_KM());
			pstmt.setDouble(7, getVL_Faturamento_Dia());
            pstmt.setLong(8, getDm_Tipo_Chassis());
			pstmt.setDouble(9, getNR_Media_KMVazio());
			pstmt.setDouble(10, getNR_Media_KM20());
			pstmt.setDouble(11, getNR_Media_KM40());
			pstmt.setDouble(12, getNR_Media_KM60());
			pstmt.setDouble(13, getNR_Media_KM80());
			pstmt.setDouble(14, getNR_KM_Dia());
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
			// passando como parâmetro o NM_Marca_Veiculo do DSN
			// o NM_Marca_Veiculo de usuário e a senha do banco.
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
		buff.append("DELETE FROM Modelos_Veiculos ");
		buff.append("WHERE OID_Modelo_Veiculo=?");
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

	public static final Modelo_VeiculoBean getByOID(int oid_Modelo_Veiculo)
	throws Exception
	{
		return getByRecord(oid_Modelo_Veiculo, "");
	}

	public static final Modelo_VeiculoBean getByCD_Modelo_Veiculo(String CD_Modelo_Veiculo)
	throws Exception
	{
		return getByRecord(0, CD_Modelo_Veiculo);
	}

	public static final Modelo_VeiculoBean getByRecord(int oid_Modelo_Veiculo, String CD_Modelo_Veiculo)
	throws Exception
{
	/*
	 * Abre a conexão com o banco
	 */
	Connection conn = null;
	try
	{
		// Pede uma conexão ao gerenciador do driver
		// passando como parâmetro o NM_Marca_Veiculo do DSN
		// o NM_Marca_Veiculo de usuário e a senha do banco.
		conn = OracleConnection2.getWEB();
		conn.setAutoCommit(false);
	} catch (Exception e)
	{
		e.printStackTrace();
		throw e;
	}

	Modelo_VeiculoBean p = new Modelo_VeiculoBean();
	try
	{
		String sql=" SELECT " +
				" 	Modelos_Veiculos.*, " +
				"	Tipos_Veiculos.CD_Tipo_Veiculo, " +
				"	Tipos_Veiculos.NM_Tipo_Veiculo, " +
				"	Marcas_Veiculos.CD_Marca_Veiculo, " +
				"	Marcas_Veiculos.NM_Marca_Veiculo " +
				"   FROM Modelos_Veiculos, Marcas_Veiculos, Tipos_Veiculos " +
				"   WHERE Modelos_Veiculos.OID_Marca_Veiculo = Marcas_Veiculos.OID_Marca_Veiculo " +
				"	AND Modelos_Veiculos.OID_Tipo_Veiculo = Tipos_Veiculos.OID_Tipo_Veiculo ";
			if (oid_Modelo_Veiculo>0) {
				sql +=" AND Modelos_Veiculos.OID_Modelo_Veiculo = " + oid_Modelo_Veiculo ;
			}
			if (CD_Modelo_Veiculo !=null && !CD_Modelo_Veiculo.equals("") && !CD_Modelo_Veiculo.equals("null")) {
				sql +=" AND Modelos_Veiculos.CD_Modelo_Veiculo = '" + CD_Modelo_Veiculo + "'";
			}
		Statement stmt = conn.createStatement();
		ResultSet cursor =	stmt.executeQuery(sql);

		while (cursor.next())
		{
			p.setOID(cursor.getInt("oid_Modelo_Veiculo"));
			p.setOID_Marca_Veiculo(cursor.getInt("oid_Marca_Veiculo"));
			p.setOID_Tipo_Veiculo(cursor.getInt("oid_Tipo_Veiculo"));
			p.setCD_Tipo_Veiculo(cursor.getString("CD_Tipo_Veiculo"));
			p.setNM_Tipo_Veiculo(cursor.getString("NM_Tipo_Veiculo"));
			p.setCD_Marca_Veiculo(cursor.getString("CD_Marca_Veiculo"));
			p.setNM_Marca_Veiculo(cursor.getString("NM_Marca_Veiculo"));
			p.setCD_Modelo_Veiculo(cursor.getString("CD_Modelo_Veiculo"));
			p.setNM_Modelo_Veiculo(cursor.getString("NM_Modelo_Veiculo"));
            p.setPE_Comissao_Motorista(cursor.getDouble("PE_Comissao_Motorista"));
            p.setVL_Faturamento_Dia(cursor.getDouble("VL_Faturamento_Dia"));
            p.setDm_Tipo_Chassis(cursor.getLong("DM_Tipo_Chassis"));

            p.setNR_Media_KMVazio(cursor.getDouble("NR_Media_KMVazio"));
			p.setNR_Media_KM20(cursor.getDouble("NR_Media_KM20"));
			p.setNR_Media_KM40(cursor.getDouble("NR_Media_KM40"));
			p.setNR_Media_KM60(cursor.getDouble("NR_Media_KM60"));
			p.setNR_Media_KM80(cursor.getDouble("NR_Media_KM80"));
			p.setNR_Media_KM(cursor.getDouble("NR_Media_KM"));
			p.setNR_KM_Dia(cursor.getDouble("NR_KM_Dia"));

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



	public static final List getByOID_Marca_Veiculo(int oid_Marca_Veiculo)
		throws Exception
	{
		return lista(oid_Marca_Veiculo,"","");
	}


	public static final List getAll()
		throws Exception
	{
		return lista(0,"","");
	}


	public static final List getByNM_Modelo_Veiculo(String NM_Modelo_Veiculo)
		throws Exception
	{
		return lista(0,"",NM_Modelo_Veiculo);
	}

	public static final List lista(int oid_Marca_Veiculo, String CD_Modelo_Veiculo, String NM_Modelo_Veiculo)
	throws Exception
{
	/*
	 * Abre a conexão com o banco
	 */
	Connection conn = null;
	try
	{
		// Pede uma conexão ao gerenciador do driver
		// passando como parâmetro o NM_Marca_Veiculo do DSN
		// o NM_Marca_Veiculo de usuário e a senha do banco.
		conn = OracleConnection2.getWEB();
		conn.setAutoCommit(false);
	} catch (Exception e)
	{
		e.printStackTrace();
		throw e;
	}

	List Modelo_Veiculo_Lista = new ArrayList();
	try
	{


		String sql= "   SELECT " +
					" 	Modelos_Veiculos.OID_Modelo_Veiculo, " +
					"	Modelos_Veiculos.OID_Marca_Veiculo, " +
					" 	Modelos_Veiculos.OID_Tipo_Veiculo, " +
					"	Tipos_Veiculos.CD_Tipo_Veiculo, " +
					"	Tipos_Veiculos.NM_Tipo_Veiculo, " +
					"	Marcas_Veiculos.CD_Marca_Veiculo, " +
					"	Marcas_Veiculos.NM_Marca_Veiculo," +
					"	Modelos_Veiculos.CD_Modelo_Veiculo, " +
					"	Modelos_Veiculos.NM_Modelo_Veiculo,  " +
					"	Modelos_Veiculos.NR_Media_KM,  " +
					"	Modelos_Veiculos.PE_Comissao_Motorista, " +
					"	Modelos_Veiculos.VL_Faturamento_Dia,  " +
					"   Modelos_Veiculos.DM_Tipo_Chassis " +
					"   FROM Modelos_Veiculos, Marcas_Veiculos, Tipos_Veiculos " +
					"   WHERE Modelos_Veiculos.OID_Marca_Veiculo = Marcas_Veiculos.OID_Marca_Veiculo " +
					"	AND Modelos_Veiculos.OID_Tipo_Veiculo = Tipos_Veiculos.OID_Tipo_Veiculo ";
			if (oid_Marca_Veiculo>0) {
				sql +=" AND Marcas_Veiculos.Marcas_Veiculos = " + oid_Marca_Veiculo ;
			}
			if (CD_Modelo_Veiculo !=null && !CD_Modelo_Veiculo.equals("") && !CD_Modelo_Veiculo.equals("null")) {
				sql +=" AND Modelos_Veiculos.CD_Modelo_Veiculo = '" + CD_Modelo_Veiculo + "'";
			}
			if (NM_Modelo_Veiculo !=null && !NM_Modelo_Veiculo.equals("") && !NM_Modelo_Veiculo.equals("null")) {
				sql +=" AND Modelos_Veiculos.CD_Modelo_Veiculo LIKE '%" + NM_Modelo_Veiculo + "%'";
			}

			sql +=" ORDER BY Tipos_Veiculos.NM_Tipo_Veiculo, Marcas_Veiculos.NM_Marca_Veiculo, Modelos_Veiculos.NM_Modelo_Veiculo";


		Statement stmt = conn.createStatement();
		ResultSet cursor =	stmt.executeQuery(sql);

		while (cursor.next())
		{
			Modelo_VeiculoBean p = new Modelo_VeiculoBean();
			p.setOID(cursor.getInt("oid_Modelo_Veiculo"));
			p.setOID_Marca_Veiculo(cursor.getInt("oid_Marca_Veiculo"));
			p.setOID_Tipo_Veiculo(cursor.getInt("oid_Tipo_Veiculo"));
			p.setCD_Tipo_Veiculo(cursor.getString("CD_Tipo_Veiculo"));
			p.setNM_Tipo_Veiculo(cursor.getString("NM_Tipo_Veiculo"));
			p.setCD_Marca_Veiculo(cursor.getString("CD_Marca_Veiculo"));
			p.setNM_Marca_Veiculo(cursor.getString("NM_Marca_Veiculo"));
			p.setCD_Modelo_Veiculo(cursor.getString("CD_Modelo_Veiculo"));
			p.setNM_Modelo_Veiculo(cursor.getString("NM_Modelo_Veiculo"));
			p.setNR_Media_KM(cursor.getDouble("NR_Media_KM"));
		    p.setPE_Comissao_Motorista(cursor.getDouble("PE_Comissao_Motorista"));
		    p.setVL_Faturamento_Dia(cursor.getDouble("VL_Faturamento_Dia"));
		    p.setDm_Tipo_Chassis(cursor.getLong("DM_Tipo_Chassis"));
			Modelo_Veiculo_Lista.add(p);

		}
		cursor.close();
		stmt.close();
		conn.close();
	} catch (Exception e)
	{
		e.printStackTrace();
	}
	return Modelo_Veiculo_Lista;
}




}
