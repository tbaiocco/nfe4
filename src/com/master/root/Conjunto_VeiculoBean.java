package com.master.root;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.master.util.Excecoes;

import auth.OracleConnection2;

public class Conjunto_VeiculoBean
{
	private String Usuario_Stamp;
	private String Dt_Stamp;
	private String Dm_Stamp;
	private String oid;
	private String oid_Veiculo;
	private String Placa_Dolly;
	private String Placa_Carreta;
	private String Placa_Carreta2;
	private double NR_Odometro_Inicial;
	private String DT_Atrelamento;
	
	public Conjunto_VeiculoBean() {
		oid = "";
		Placa_Dolly = " ";
		Placa_Carreta = "";
		Placa_Carreta2 = " ";
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

	public String getOID_Veiculo()
	{
		return oid_Veiculo;
	}
	public void setOID_Veiculo(String n)
	{
		this.oid_Veiculo = n;
	}

	public String getPlaca_Dolly()
	{
		return Placa_Dolly;
	}
	public void setPlaca_Dolly(String n)
	{
		this.Placa_Dolly = n;
	}

	public String getPlaca_Carreta()
	{
		return Placa_Carreta;
	}
	public void setPlaca_Carreta(String n)
	{
		this.Placa_Carreta = n;
	}

	public String getPlaca_Carreta2()
	{
		return Placa_Carreta2;
	}
	public void setPlaca_Carreta2(String n)
	{
		this.Placa_Carreta2 = n;
	}

	public String getDT_Atrelamento() {
		return DT_Atrelamento;
	}
	public void setDT_Atrelamento(String atrelamento) {
		DT_Atrelamento = atrelamento;
	}
	public double getNR_Odometro_Inicial() {
		return NR_Odometro_Inicial;
	}
	public void setNR_Odometro_Inicial(double odometro_Inicial) {
		NR_Odometro_Inicial = odometro_Inicial;
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
			// passando como parâmetro o NR_Frota do DSN
			// o NR_Frota de usuário e a senha do banco.
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
		buff.append("INSERT INTO Conjuntos_Veiculos (OID_Conjunto_Veiculo, OID_Veiculo, Placa_Dolly, Placa_Carreta, Placa_Carreta2, DT_Stamp, Usuario_Stamp, Dm_Stamp, NR_Odometro_Inicial, DT_Atrelamento) ");
		buff.append("VALUES (?,?,?,?,?,?,?,?,?,?)");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try
		{
			PreparedStatement pstmt =
			conn.prepareStatement(buff.toString());
			pstmt.setString(1, getOID());
			pstmt.setString(2, getOID_Veiculo());
			pstmt.setString(3, getPlaca_Dolly());
			pstmt.setString(4, getPlaca_Carreta());
			pstmt.setString(5, getPlaca_Carreta2());
			pstmt.setString(6, getDt_Stamp());
			pstmt.setString(7, getUsuario_Stamp());
			pstmt.setString(8, getDm_Stamp());
			pstmt.setDouble(9, getNR_Odometro_Inicial());
			pstmt.setString(10, getDT_Atrelamento());
			// System.out.println(getDT_Atrelamento());
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
			// passando como parâmetro o NR_Frota do DSN
			// o NR_Frota de usuário e a senha do banco.
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
		buff.append("UPDATE Conjuntos_Veiculos SET Placa_Dolly=?, Placa_Carreta=?, Placa_Carreta2=?, NR_Odometro_Inicial=?, DT_Atrelamento=? ");
		buff.append("WHERE OID_Conjunto_Veiculo=?");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());

			pstmt.setString(1, getPlaca_Dolly());
			pstmt.setString(2, getPlaca_Carreta());
			pstmt.setString(3, getPlaca_Carreta2());
			pstmt.setDouble(4, getNR_Odometro_Inicial());
			pstmt.setString(5, getDT_Atrelamento());
			pstmt.setString(6, getOID());
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
			// passando como parâmetro o NR_Frota do DSN
			// o NR_Frota de usuário e a senha do banco.
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
		buff.append("DELETE FROM Conjuntos_Veiculos ");
		buff.append("WHERE OID_Conjunto_Veiculo=?");
		/*
		 * Define os dados do SQL
		 * e executa o insert no banco.
		 */
		try
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());
			pstmt.setString(1, getOID());
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
	
	public void delete(double odometroDiferenca)
	throws Exception {
		long odometroDifL = Math.round(odometroDiferenca);
		//Atualiza o odometro do cavalo no manifesto
		//updateDiferencaOdometro(odometroDifL, getOID());
		updateDiferencaOdometro(odometroDifL, getPlaca_Carreta());
		updateDiferencaOdometro(odometroDifL, getPlaca_Dolly());
		updateDiferencaOdometro(odometroDifL, getPlaca_Carreta2());
		delete();
	}
	
	private void updateDiferencaOdometro(long odometroDiferenca, String oid_Veiculo)
	throws Exception {
		if (oid_Veiculo != null && !"".equals(oid_Veiculo)) {
			VeiculoBean veiculo = VeiculoBean.getByOID(oid_Veiculo);
			// System.out.println("Veículo: <" + oid_Veiculo + ">-<" + veiculo.getOID() + "> Km: " + veiculo.getNR_Kilometragem_Atual() + odometroDiferenca);
			veiculo.setNR_Kilometragem_Atual(veiculo.getNR_Kilometragem_Atual() + odometroDiferenca);
			veiculo.update();
		}
	}

	public static final Conjunto_VeiculoBean getByOID(String oid)
	throws Excecoes {
		FormataDataBean formataData = new FormataDataBean();
		
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NR_Frota do DSN
			// o NR_Frota de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw new Excecoes(e.getMessage(), e, "Conjunto_VeiculoBean", "getByOID(String oid)");
		}

		Conjunto_VeiculoBean p = new Conjunto_VeiculoBean();
		
		try {
			StringBuffer buff = new StringBuffer();
			buff.append(" SELECT Conjuntos_Veiculos.OID_Conjunto_Veiculo, ");
			buff.append("	Conjuntos_Veiculos.Placa_Dolly, ");
			buff.append("	Conjuntos_Veiculos.Placa_Carreta, ");
			buff.append("	Conjuntos_Veiculos.Placa_Carreta2, ");
			buff.append("	Conjuntos_Veiculos.NR_Odometro_Inicial, ");
			buff.append("	Conjuntos_Veiculos.DT_Atrelamento ");
			buff.append(" FROM Conjuntos_Veiculos ");
			buff.append(" WHERE Conjuntos_Veiculos.OID_Conjunto_Veiculo = '" + oid + "'");
			Statement stmt = conn.createStatement();
			ResultSet cursor = stmt.executeQuery(buff.toString());
			while (cursor.next()) {
				p.setOID(cursor.getString(1));
				p.setPlaca_Dolly(cursor.getString(2));
				p.setPlaca_Carreta(cursor.getString(3));
				p.setPlaca_Carreta2(cursor.getString(4));
				p.setNR_Odometro_Inicial(cursor.getDouble("NR_Odometro_Inicial"));
				p.setDT_Atrelamento(formataData.getDT_FormataData(cursor.getString("DT_Atrelamento")));
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Excecoes(e.getMessage(), e, "Conjunto_VeiculoBean", "getByOID(String oid)");
		}
		return p;
	}

	public static final Conjunto_VeiculoBean getByOIDTodosCampos(String oid)
	throws Exception {
		FormataDataBean formataData = new FormataDataBean();
		
		/*
		 * Abre a conexão com o banco
		 */
		Connection conn = null;
		try {
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NR_Frota do DSN
			// o NR_Frota de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		Conjunto_VeiculoBean p = new Conjunto_VeiculoBean();
		
		try {
			StringBuffer buff = new StringBuffer();
			buff.append(" SELECT Conjuntos_Veiculos.OID_Conjunto_Veiculo, ");
			buff.append("	Conjuntos_Veiculos.Placa_Dolly, ");
			buff.append("	Conjuntos_Veiculos.Placa_Carreta, ");
			buff.append("	Conjuntos_Veiculos.Placa_Carreta2, ");
			buff.append("	Conjuntos_Veiculos.NR_Odometro_Inicial, ");
			buff.append("	Conjuntos_Veiculos.DT_Atrelamento ");
			buff.append(" FROM Conjuntos_Veiculos ");
			buff.append(" WHERE Conjuntos_Veiculos.OID_Veiculo = '" + oid + "'");
			buff.append("    OR Conjuntos_Veiculos.placa_dolly = '" + oid + "'");
			buff.append("    OR Conjuntos_Veiculos.placa_carreta = '" + oid + "'");
			buff.append("    OR Conjuntos_Veiculos.placa_carreta2 = '" + oid + "'");
			Statement stmt = conn.createStatement();
			ResultSet cursor = stmt.executeQuery(buff.toString());
			while (cursor.next()) {
				p.setOID(cursor.getString(1));
				p.setPlaca_Dolly(cursor.getString(2));
				p.setPlaca_Carreta(cursor.getString(3));
				p.setPlaca_Carreta2(cursor.getString(4));
				p.setNR_Odometro_Inicial(cursor.getDouble("NR_Odometro_Inicial"));
				p.setDT_Atrelamento(formataData.getDT_FormataData(cursor.getString("DT_Atrelamento")));
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

	public static final List getAll()
	throws Exception {
		return getByNR_Placa("");
	}

	public static final List getByNR_Placa(String NR_Placa)
	throws Exception {
		FormataDataBean formataData = new FormataDataBean();
		Connection conn = null;
		try {
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o nome do DSN
			// o nome de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		List Conjunto_Veiculos_Lista = new ArrayList();
		try {
			StringBuffer buff = new StringBuffer();

			buff.append(" SELECT Conjuntos_Veiculos.OID_Conjunto_Veiculo, ");
			buff.append("	Conjuntos_Veiculos.Placa_Dolly, ");
			buff.append("	Conjuntos_Veiculos.Placa_Carreta, ");
			buff.append("	Conjuntos_Veiculos.Placa_Carreta2, ");
			buff.append("	Conjuntos_Veiculos.NR_Odometro_Inicial, ");
			buff.append("	Conjuntos_Veiculos.DT_Atrelamento ");
			buff.append(" FROM Conjuntos_Veiculos ");
			buff.append(" WHERE Conjuntos_Veiculos.OID_Veiculo = '" + NR_Placa + "'");
			buff.append("    OR Conjuntos_Veiculos.placa_dolly = '" + NR_Placa + "'");
			buff.append("    OR Conjuntos_Veiculos.placa_carreta = '" + NR_Placa + "'");
			buff.append("    OR Conjuntos_Veiculos.placa_carreta2 = '" + NR_Placa + "'");

			Statement stmt = conn.createStatement();
			ResultSet cursor = stmt.executeQuery(buff.toString());

			while (cursor.next()) {
				Conjunto_VeiculoBean p = new Conjunto_VeiculoBean();
				p.setOID(cursor.getString(1));
				p.setOID_Veiculo(cursor.getString(1));
				p.setPlaca_Dolly(cursor.getString(2));
				p.setPlaca_Carreta(cursor.getString(3));
				p.setPlaca_Carreta2(cursor.getString(4));
				p.setNR_Odometro_Inicial(cursor.getDouble("NR_Odometro_Inicial"));
				p.setDT_Atrelamento(formataData.getDT_FormataData(cursor.getString("DT_Atrelamento")));
				Conjunto_Veiculos_Lista.add(p);
			}
			cursor.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return Conjunto_Veiculos_Lista;
	}


	public static void main(String args[])
	throws Exception {
		Conjunto_VeiculoBean pp = new Conjunto_VeiculoBean();
		pp.setOID("ZZZ1234");
		pp.setOID_Veiculo("ZZZ1234");
		pp.setPlaca_Carreta("ZZZ1234");
		pp.setPlaca_Carreta2("ZZZ1234");
		pp.setPlaca_Dolly("AAA3333");
		pp.insert();

		//// //// System.out.println(p.getOID());

	}
}
