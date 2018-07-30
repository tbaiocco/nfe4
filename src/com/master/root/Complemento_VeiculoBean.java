package com.master.root;
import java.sql.*;

import auth.*;

public class Complemento_VeiculoBean
{
	private String NR_Chassis;
	private String NR_Renavan;
	private String NM_Cor;
	private String DT_Compra;
	private String DT_Validade_Licenciamento;
	private double VL_Compra;
	private double PE_Depreciacao;
	private String NR_Ano_Veiculo;
	private String NR_Ano_Modelo;
	private String NR_Placa_Anterior;
	private String NM_Tipo_Contrato;
	private double NR_Comprimento;
	private double NR_Largura;
	private double NR_Altura;
	private long NR_Peso_Limite;
	private String NR_Licenca;
	private String NM_Alienacao;
	private String DM_Procedencia;
	private String Usuario_Stamp;
	private String Dt_Stamp;
	private String Dm_Stamp;
	private String oid;
	private String oid_Veiculo;

	private double VL_Tara;
	private double VL_Peso_Liquido;
        private String NR_Eixos;

  public Complemento_VeiculoBean()
	{
		oid="";
		oid_Veiculo="";
		NR_Chassis="";
		NR_Renavan="";
		NM_Cor="";
		VL_Compra=0;
		PE_Depreciacao=0;
		NR_Ano_Veiculo="";
		NR_Placa_Anterior="";
		NM_Tipo_Contrato="";
		NR_Comprimento=0;
		NR_Largura=0;
		NR_Altura=0;
		NR_Peso_Limite=0;
		NM_Alienacao="";
		NM_Tipo_Contrato="";
		NR_Licenca="";
		Usuario_Stamp="";
		Dm_Stamp="";
	}

	public String getNR_Chassis()
	{
		return NR_Chassis;
	}
	public void setNR_Chassis(String NR_Chassis)
	{
		this.NR_Chassis = NR_Chassis;
	}
	public String getNM_Cor()
	{
		return NM_Cor;
	}
	public void setNM_Cor(String NM_Cor)
	{
		this.NM_Cor = NM_Cor;
	}


	public String getDT_Compra()
	{
		FormataDataBean DataFormatada = new FormataDataBean();
		DataFormatada.setDT_FormataData(DT_Compra);
		DT_Compra = DataFormatada.getDT_FormataData();

		return DT_Compra;
	}
	public void setDT_Compra(String DT_Compra)
	{
		this.DT_Compra = DT_Compra;
	}

	public double getVL_Compra()
	{
		return VL_Compra;
	}
	public void setVL_Compra(double VL_Compra)
	{
		this.VL_Compra = VL_Compra;
	}
	public double getPE_Depreciacao()
	{
		return PE_Depreciacao;
	}
	public void setPE_Depreciacao(double PE_Depreciacao)
	{
		this.PE_Depreciacao = PE_Depreciacao;
	}
	public String getNR_Ano_Veiculo()
	{
		return NR_Ano_Veiculo;
	}
	public void setNR_Ano_Veiculo(String NR_Ano_Veiculo)
	{
		this.NR_Ano_Veiculo = NR_Ano_Veiculo;
	}
	public String getNR_Placa_Anterior()
	{
		return NR_Placa_Anterior;
	}
	public void setNR_Placa_Anterior(String NR_Placa_Anterior)
	{
		this.NR_Placa_Anterior = NR_Placa_Anterior;
	}

	public String getDM_Procedencia()
	{
		return DM_Procedencia;
	}
	public void setDM_Procedencia(String DM_Procedencia)
	{
		this.DM_Procedencia = DM_Procedencia;
	}

	public double getNR_Comprimento()
	{
		return NR_Comprimento;
	}
	public void setNR_Comprimento(double NR_Comprimento)
	{
		this.NR_Comprimento = NR_Comprimento;
	}
	public double getNR_Largura()
	{
		return NR_Largura;
	}
	public void setNR_Largura(double NR_Largura)
	{
		this.NR_Largura = NR_Largura;
	}

	public double getNR_Altura()
	{
		return NR_Altura;
	}
	public void setNR_Altura(double NR_Altura)
	{
		this.NR_Altura = NR_Altura;
	}

	public long getNR_Peso_Limite()
	{
		return NR_Peso_Limite;
	}
	public void setNR_Peso_Limite(long NR_Peso_Limite)
	{
		this.NR_Peso_Limite = NR_Peso_Limite;
	}

	public String getNR_Licenca()
	{
		return NR_Licenca;
	}
	public void setNR_Licenca(String NR_Licenca)
	{
		this.NR_Licenca = NR_Licenca;
	}

	public String getNM_Alienacao()
	{
		return NM_Alienacao;
	}
	public void setNM_Alienacao(String NM_Alienacao)
	{
		this.NM_Alienacao = NM_Alienacao;
	}

	public String getNM_Tipo_Contrato()
	{
		return NM_Tipo_Contrato;
	}
	public void setNM_Tipo_Contrato(String NM_Tipo_Contrato)
	{
		this.NM_Tipo_Contrato = NM_Tipo_Contrato;
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


    public double getVL_Peso_Liquido() {
        return VL_Peso_Liquido;
    }
    public void setVL_Peso_Liquido(double peso_Liquido) {
        VL_Peso_Liquido = peso_Liquido;
    }
    public double getVL_Tara() {
        return VL_Tara;
    }
    public void setVL_Tara(double tara) {
        VL_Tara = tara;
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
			// passando como parâmetro o NR_Chassis do DSN
			// o NR_Chassis de usuário e a senha do banco.
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
		buff.append("INSERT INTO Complementos_Veiculos (OID_Complemento_Veiculo, OID_Veiculo, NR_Chassis, DT_Compra, VL_Compra,	PE_Depreciacao, NR_Ano_Veiculo,	NR_Placa_Anterior,	DM_Procedencia,	NM_Tipo_Contrato, NR_Comprimento, NR_Largura, NR_Altura, NR_Peso_Limite, NR_Licenca, NM_Alienacao, DT_Stamp, Usuario_Stamp, Dm_Stamp, NM_Cor, NR_Ano_Modelo, VL_Tara, VL_Peso_Liquido, NR_Eixos, NR_Renavan, DT_Validade_Licenciamento) ");
		buff.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
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
			pstmt.setString(3, getNR_Chassis());
			pstmt.setString(4, this.DT_Compra);
			pstmt.setDouble(5, getVL_Compra());
			pstmt.setDouble(6, getPE_Depreciacao());
			pstmt.setString(7, getNR_Ano_Veiculo());
			pstmt.setString(8, getNR_Placa_Anterior());
			pstmt.setString(9, getDM_Procedencia());
			pstmt.setString(10, getNM_Tipo_Contrato());
			pstmt.setDouble(11, getNR_Comprimento());
			pstmt.setDouble(12, getNR_Largura());
			pstmt.setDouble(13, getNR_Altura());
			pstmt.setLong(14, getNR_Peso_Limite());
			pstmt.setString(15, getNR_Licenca());
			pstmt.setString(16, getNM_Alienacao());
			pstmt.setString(17, getDt_Stamp());
			pstmt.setString(18, getUsuario_Stamp());
			pstmt.setString(19, getDm_Stamp());
			pstmt.setString(20, getNM_Cor());
			pstmt.setString(21, getNR_Ano_Modelo());
			pstmt.setDouble(22, getVL_Tara());
			pstmt.setDouble(23, getVL_Peso_Liquido());
			pstmt.setString(24, getNR_Eixos());
			pstmt.setString(25, getNR_Renavan());
			pstmt.setString(26, getDT_Validade_Licenciamento());



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
			// passando como parâmetro o NR_Chassis do DSN
			// o NR_Chassis de usuário e a senha do banco.
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
		buff.append("UPDATE Complementos_Veiculos SET  NR_Chassis=?, DT_Compra=?, VL_Compra=?, PE_Depreciacao=?, NR_Ano_Veiculo=?, NR_Placa_Anterior=?,	DM_Procedencia=?, NM_Tipo_Contrato=?, NR_Comprimento=?, NR_Largura=?, NR_Altura=?, NR_Peso_Limite=?, NR_Licenca=?, NM_Alienacao=?, NM_Cor=?, NR_Ano_Modelo=?, VL_Tara=?, NR_Eixos=?, VL_Peso_Liquido=?, NR_Renavan=?, DT_Validade_Licenciamento=? ");
		buff.append("WHERE OID_Complemento_Veiculo=?" );
		/*
		 * Define os dados do SQL
		* e executa o insert no banco.
		*/
		try
		{
			PreparedStatement pstmt =
				conn.prepareStatement(buff.toString());

			pstmt.setString(1, getNR_Chassis());
			pstmt.setString(2, this.DT_Compra);

                        // System.out.println(pstmt.toString());

			pstmt.setDouble(3, getVL_Compra());
			pstmt.setDouble(4, getPE_Depreciacao());
			pstmt.setString(5, getNR_Ano_Veiculo());
			pstmt.setString(6, getNR_Placa_Anterior());
			pstmt.setString(7, getDM_Procedencia());
			pstmt.setString(8, getNM_Tipo_Contrato());
			pstmt.setDouble(9, getNR_Comprimento());
			pstmt.setDouble(10, getNR_Largura());
			pstmt.setDouble(11, getNR_Altura());
			pstmt.setLong(12, getNR_Peso_Limite());
			pstmt.setString(13, getNR_Licenca());
			pstmt.setString(14, getNM_Alienacao());
			pstmt.setString(15, getNM_Cor());
			pstmt.setString(16, getNR_Ano_Modelo());
			pstmt.setDouble(17, getVL_Tara());
			pstmt.setString(18, getNR_Eixos());
			pstmt.setDouble(19, getVL_Peso_Liquido());
			pstmt.setString(20, getNR_Renavan());
			pstmt.setString(21, this.DT_Validade_Licenciamento);
			pstmt.setString(22, getOID());

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
			// passando como parâmetro o NR_Chassis do DSN
			// o NR_Chassis de usuário e a senha do banco.
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
		buff.append("DELETE FROM Complementos_Veiculos ");
		buff.append("WHERE OID_Complemento_Veiculo=?");
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

	public static final Complemento_VeiculoBean getByOID(String oid)
		throws Exception
	{
		/*
		 * Abre a conexão com o banco
		*/
		Connection conn = null;
		try
		{
			// Pede uma conexão ao gerenciador do driver
			// passando como parâmetro o NR_Chassis do DSN
			// o NR_Chassis de usuário e a senha do banco.
			conn = OracleConnection2.getWEB();
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		Complemento_VeiculoBean p = new Complemento_VeiculoBean();
		try
		{
			StringBuffer buff = new StringBuffer();
			buff.append("SELECT OID_Complemento_Veiculo, ");
			buff.append("	OID_Veiculo, ");
			buff.append("	NR_Chassis, ");
			buff.append("	DT_Compra, ");
			buff.append("	VL_Compra, ");
			buff.append("	PE_Depreciacao, ");
			buff.append("	NR_Ano_Veiculo,  ");
			buff.append("	NR_Placa_Anterior,  ");
			buff.append("	DM_Procedencia, ");
			buff.append("	NM_Tipo_Contrato,        ");
			buff.append("	NR_Comprimento,        ");
			buff.append("	NR_Largura,        ");
			buff.append("	NR_Altura,        ");
			buff.append("	NR_Peso_Limite,        ");
			buff.append("	NR_Licenca,        ");
			buff.append("	NM_Alienacao,        ");
			buff.append("	NR_Ano_Modelo,       ");
			buff.append("	NM_Cor, ");
			buff.append("	VL_Tara, ");
			buff.append("	NR_Eixos, ");
			buff.append("	VL_Peso_Liquido, ");
			buff.append("	NR_Renavan, ");
			buff.append("	DT_Validade_Licenciamento ");
			buff.append("FROM Complementos_Veiculos ");
			buff.append("WHERE OID_Complemento_Veiculo  =  '");
			buff.append(oid);
			buff.append("'");


			Statement stmt = conn.createStatement();
			ResultSet cursor =
				stmt.executeQuery(buff.toString());

			while (cursor.next())
			{
				p.setOID(cursor.getString(1));
				p.setOID_Veiculo(cursor.getString(2));
				p.setNR_Chassis(cursor.getString(3));
				p.setDT_Compra(cursor.getString(4));
				p.setVL_Compra(cursor.getDouble(5));
				p.setPE_Depreciacao(cursor.getDouble(6));
				p.setNR_Ano_Veiculo(cursor.getString(7));
				p.setNR_Placa_Anterior(cursor.getString(8));
				p.setDM_Procedencia(cursor.getString(9));
				p.setNM_Tipo_Contrato(cursor.getString(10));
				p.setNR_Comprimento(cursor.getDouble(11));
				p.setNR_Largura(cursor.getDouble(12));
				p.setNR_Altura(cursor.getDouble(13));
				p.setNR_Peso_Limite(cursor.getLong(14));
				p.setNR_Licenca(cursor.getString(15));
				p.setNM_Alienacao(cursor.getString(16));
				p.setNR_Ano_Modelo(cursor.getString(17));
				p.setNM_Cor(cursor.getString(18));
				p.setVL_Tara(cursor.getDouble(19));
				p.setNR_Eixos(cursor.getString(20));
				p.setVL_Peso_Liquido(cursor.getDouble(21));
				p.setNR_Renavan(cursor.getString(22));
				p.setDT_Validade_Licenciamento(cursor.getString(23));

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

	public static void main(String args[])
		throws Exception
	{

	}
  public String getNR_Ano_Modelo() {
    return NR_Ano_Modelo;
  }
  public void setNR_Ano_Modelo(String NR_Ano_Modelo) {
    this.NR_Ano_Modelo = NR_Ano_Modelo;
  }

  public void setNR_Eixos (String NR_Eixos) {
    this.NR_Eixos = NR_Eixos;
  }

  public String getNR_Eixos () {
    return NR_Eixos;
  }

public String getNR_Renavan() {
	return NR_Renavan;
}

public void setNR_Renavan(String renavan) {
	NR_Renavan = renavan;
}

public String getDT_Validade_Licenciamento() {
	FormataDataBean DataFormatada = new FormataDataBean();
	DataFormatada.setDT_FormataData(DT_Validade_Licenciamento);
	DT_Validade_Licenciamento = DataFormatada.getDT_FormataData();

	return DT_Validade_Licenciamento;
}

public void setDT_Validade_Licenciamento(String validade_Licenciamento) {
	DT_Validade_Licenciamento = validade_Licenciamento;
}
}
