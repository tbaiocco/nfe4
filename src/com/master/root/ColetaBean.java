
package com.master.root;
import java.sql.*;
import java.util.*;

import auth.*;
import com.master.util.*;

public class ColetaBean {
	private int NR_Coleta;

	private String NM_Solicitante;

	private String DT_Coleta;

	private String HR_Coleta;

	private String DT_Coletado;

	private String HR_Coletado;

	private String DM_Intervalo_Almoco;

	private String DM_Tipo_Veiculo;

	private String DM_Munck;

	private String DM_KIT;

	private int QT_Volumes;

	private double VL_Mercadoria;

	private String TX_Observacao;

	private String NM_Endereco_Coleta;

	private String NM_Bairro_Coleta;

	private String Usuario_Stamp;

	private String Dt_Stamp;

	private String Dm_Stamp;

	private int oid;

	private int oid_Modal;

	private int oid_Unidade;

	private int oid_Cidade;

	private int oid_AIDOF;

	private String oid_Pessoa;

	private String oid_Pessoa_Destinatario;

	private String NM_Fantasia;

	private String NM_Razao_Social;

	private String NM_Razao_Social_Destinatario;

	private String NM_Cidade;

	private String NM_Modal;

	private String CD_Cidade;

	private String CD_Modal;

	private String CD_Unidade;

	private String NM_Destinatario;

	private String NM_Cidade_Destinatario;

	private String NM_Documento;

	private String OID_Veiculo;

	private double VL_Custo;

	private String DM_Tipo_Conhecimento;

	private double VL_Total_Frete;

	private String DT_Previsao_Entrega;

	private String HR_Previsao_Entrega;

	private int NR_Peso2;

	private int NR_Peso3;

	private int NR_Peso4;

	private String NM_Destinatario2;

	private String NM_Destinatario3;

	private String NM_Destinatario4;

	private String NM_Cidade_Destinatario2;

	private String NM_Cidade_Destinatario3;

	private String NM_Cidade_Destinatario4;

	private String NM_Documento2;

	private String NM_Documento3;

	private String NM_Documento4;

	private String OID_Pessoa_Pagador;

	private double VL_Custo_Notas_Fiscais;

	private double VL_Total_Frete_Notas_Fiscais;

	private double VL_Mercadoria_Notas_Fiscais;

	private double NR_Peso_Notas_Fiscais;

	private double QT_Volumes_Notas_Fiscais;

	private int QT_Conhecimentos_Gerados;

	private int QT_Conhecimentos_OK;

	private int QT_Conhecimentos_Impressos;

	private double NR_Peso;

	private int QT_Notas_Fiscais;

	private String NR_Telefone;

	private int QT_Notas_Notas_Fiscais;
	private String DT_Programada;
	private String HR_Programada;
	private String DM_Situacao;

	private String NM_Especie;
	private String NM_Especie2;
	private String NM_Especie3;
	private String NM_Especie4;

	private String DT_Coleta_Destino;
	private String DT_Coleta_Destino2;
	private String DT_Coleta_Destino3;
	private String DT_Coleta_Destino4;

	private long NR_Volumes;
	private long NR_Volumes2;
	private long NR_Volumes3;
	private long NR_Volumes4;

	private long NR_Conhecimento;
	private long NR_Conhecimento2;
	private long NR_Conhecimento3;
	private long NR_Conhecimento4;
  private String NR_Cartao;
  private String NR_Liberacao;
  private String NM_Rota;
  private String OID_Carreta;
  private String OID_Motorista;
  private String DM_Tipo;
  private String NR_Pedido;
  private String OID_Cotacao;

  public String getDT_Coleta_Destino() {
		return DT_Coleta_Destino;
	}
	public void setDT_Coleta_Destino(String coleta_Destino) {
		DT_Coleta_Destino = coleta_Destino;
	}
	public String getDT_Coleta_Destino2() {
		return DT_Coleta_Destino2;
	}
	public void setDT_Coleta_Destino2(String coleta_Destino2) {
		DT_Coleta_Destino2 = coleta_Destino2;
	}
	public String getDT_Coleta_Destino3() {
		return DT_Coleta_Destino3;
	}
	public void setDT_Coleta_Destino3(String coleta_Destino3) {
		DT_Coleta_Destino3 = coleta_Destino3;
	}
	public String getDT_Coleta_Destino4() {
		return DT_Coleta_Destino4;
	}
	public void setDT_Coleta_Destino4(String coleta_Destino4) {
		DT_Coleta_Destino4 = coleta_Destino4;
	}
	public String getNM_Especie() {
		return NM_Especie;
	}
	public void setNM_Especie(String especie) {
		NM_Especie = especie;
	}
	public String getNM_Especie2() {
		return NM_Especie2;
	}
	public void setNM_Especie2(String especie2) {
		NM_Especie2 = especie2;
	}
	public String getNM_Especie3() {
		return NM_Especie3;
	}
	public void setNM_Especie3(String especie3) {
		NM_Especie3 = especie3;
	}
	public String getNM_Especie4() {
		return NM_Especie4;
	}
	public void setNM_Especie4(String especie4) {
		NM_Especie4 = especie4;
	}
	public long getNR_Conhecimento() {
		return NR_Conhecimento;
	}
	public void setNR_Conhecimento(long conhecimento) {
		NR_Conhecimento = conhecimento;
	}
	public long getNR_Conhecimento2() {
		return NR_Conhecimento2;
	}
	public void setNR_Conhecimento2(long conhecimento2) {
		NR_Conhecimento2 = conhecimento2;
	}
	public long getNR_Conhecimento3() {
		return NR_Conhecimento3;
	}
	public void setNR_Conhecimento3(long conhecimento3) {
		NR_Conhecimento3 = conhecimento3;
	}
	public long getNR_Conhecimento4() {
		return NR_Conhecimento4;
	}
	public void setNR_Conhecimento4(long conhecimento4) {
		NR_Conhecimento4 = conhecimento4;
	}
	public long getNR_Volumes() {
		return NR_Volumes;
	}
	public void setNR_Volumes(long volumes) {
		NR_Volumes = volumes;
	}
	public long getNR_Volumes2() {
		return NR_Volumes2;
	}
	public void setNR_Volumes2(long volumes2) {
		NR_Volumes2 = volumes2;
	}
	public long getNR_Volumes3() {
		return NR_Volumes3;
	}
	public void setNR_Volumes3(long volumes3) {
		NR_Volumes3 = volumes3;
	}
	public long getNR_Volumes4() {
		return NR_Volumes4;
	}
	public void setNR_Volumes4(long volumes4) {
		NR_Volumes4 = volumes4;
	}
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public int getOid_AIDOF() {
		return oid_AIDOF;
	}
	public void setOid_AIDOF(int oid_AIDOF) {
		this.oid_AIDOF = oid_AIDOF;
	}
	public int getOid_Cidade() {
		return oid_Cidade;
	}
	public void setOid_Cidade(int oid_Cidade) {
		this.oid_Cidade = oid_Cidade;
	}
	public int getOid_Modal() {
		return oid_Modal;
	}
	public void setOid_Modal(int oid_Modal) {
		this.oid_Modal = oid_Modal;
	}
	public String getOid_Pessoa() {
		return oid_Pessoa;
	}
	public void setOid_Pessoa(String oid_Pessoa) {
		this.oid_Pessoa = oid_Pessoa;
	}
	public String getOid_Pessoa_Destinatario() {
		return oid_Pessoa_Destinatario;
	}
	public void setOid_Pessoa_Destinatario(String oid_Pessoa_Destinatario) {
		this.oid_Pessoa_Destinatario = oid_Pessoa_Destinatario;
	}
	public int getOid_Unidade() {
		return oid_Unidade;
	}

  public String getOID_Cotacao () {
    return OID_Cotacao;
  }

  public String getNR_Pedido () {
    return NR_Pedido;
  }

  public String getDM_Tipo () {
    return DM_Tipo;
  }

  public String getOID_Motorista () {
    return OID_Motorista;
  }

  public String getOID_Carreta () {
    return OID_Carreta;
  }

  public String getNM_Rota () {
    return NM_Rota;
  }

  public String getNR_Liberacao () {
    return NR_Liberacao;
  }

  public String getNR_Cartao () {
    return NR_Cartao;
  }

  public void setOid_Unidade(int oid_Unidade) {
		this.oid_Unidade = oid_Unidade;
	}

  public void setOID_Cotacao (String OID_Cotacao) {
    this.OID_Cotacao = OID_Cotacao;
  }

  public void setNR_Pedido (String NR_Pedido) {
    this.NR_Pedido = NR_Pedido;
  }

  public void setDM_Tipo (String DM_Tipo) {
    this.DM_Tipo = DM_Tipo;
  }

  public void setOID_Motorista (String OID_Motorista) {
    this.OID_Motorista = OID_Motorista;
  }

  public void setOID_Carreta (String OID_Carreta) {
    this.OID_Carreta = OID_Carreta;
  }

  public void setNM_Rota (String NM_Rota) {
    this.NM_Rota = NM_Rota;
  }

  public void setNR_Liberacao (String NR_Liberacao) {
    this.NR_Liberacao = NR_Liberacao;
  }

  public void setNR_Cartao (String NR_Cartao) {
    this.NR_Cartao = NR_Cartao;
  }

  public ColetaBean() {
        NM_Solicitante = "";
        HR_Coleta = "";
        HR_Coletado = "";
        DM_Intervalo_Almoco = "";
        DM_Tipo_Veiculo = "";
        DM_Munck = "";
        DM_KIT = "";
        QT_Volumes = 0;
        NR_Peso = 0;
        VL_Mercadoria = 0;
        TX_Observacao = " ";
        NM_Endereco_Coleta = "";
        NM_Bairro_Coleta = "";
        Usuario_Stamp = "";
        Dm_Stamp = "";
        oid = 0;
        oid_Modal = 0;
        oid_Unidade = 0;
        oid_Cidade = 0;
        oid_AIDOF = 0;
        oid_Pessoa = "";
        oid_Pessoa_Destinatario = "";
        NM_Fantasia = "";
        NM_Razao_Social = "";
        NM_Razao_Social_Destinatario = "";
        NM_Cidade = "";
        NM_Modal = "";
        CD_Cidade = "";
        CD_Modal = "";
        CD_Unidade = "";
    }

    public int getNR_Coleta() {
        return NR_Coleta;
    }

    public void setNR_Coleta(int NR_Coleta) {
        this.NR_Coleta = NR_Coleta;
    }

    public String getNM_Solicitante() {
        return NM_Solicitante;
    }

    public void setNM_Solicitante(String NM_Solicitante) {
        this.NM_Solicitante = NM_Solicitante;
    }

    public String getDT_Coleta() {
        FormataDataBean DataFormatada = new FormataDataBean();
        DataFormatada.setDT_FormataData(DT_Coleta);
        DT_Coleta = DataFormatada.getDT_FormataData();

        return DT_Coleta;
    }

    public void setDT_Coleta(String DT_Coleta) {
        this.DT_Coleta = DT_Coleta;
    }

    public String getHR_Coleta() {
        return HR_Coleta;
    }

    public void setHR_Coleta(String HR_Coleta) {
        this.HR_Coleta = HR_Coleta;
    }

    public String getDT_Coletado() {
        return DT_Coletado;
    }

    public void setDT_Coletado(String DT_Coletado) {
        this.DT_Coletado = DT_Coletado;
    }

    public String getHR_Coletado() {
        return HR_Coletado;
    }

    public void setHR_Coletado(String HR_Coletado) {
        this.HR_Coletado = HR_Coletado;
    }

    public String getDM_Intervalo_Almoco() {
        return DM_Intervalo_Almoco;
    }

    public void setDM_Intervalo_Almoco(String DM_Intervalo_Almoco) {
        this.DM_Intervalo_Almoco = DM_Intervalo_Almoco;
    }

    public String getDM_Tipo_Veiculo() {
        return DM_Tipo_Veiculo;
    }

    public void setDM_Tipo_Veiculo(String DM_Tipo_Veiculo) {
        this.DM_Tipo_Veiculo = DM_Tipo_Veiculo;
    }

    public String getDM_Munck() {
        return DM_Munck;
    }

    public void setDM_Munck(String DM_Munck) {
        this.DM_Munck = DM_Munck;
    }

    public String getDM_KIT() {
        return DM_KIT;
    }

    public void setDM_KIT(String DM_KIT) {
        this.DM_KIT = DM_KIT;
    }

    public int getQT_Volumes() {
        return QT_Volumes;
    }

    public void setQT_Volumes(int QT_Volumes) {
        this.QT_Volumes = QT_Volumes;
    }

    public double getNR_Peso() {
        return NR_Peso;
    }

    public void setNR_Peso(double NR_Peso) {
        this.NR_Peso = NR_Peso;
    }

    public double getVL_Mercadoria() {
        return VL_Mercadoria;
    }

    public void setVL_Mercadoria(double VL_Mercadoria) {
        this.VL_Mercadoria = VL_Mercadoria;
    }

    public String getTX_Observacao() {
        return TX_Observacao;
    }

    public void setTX_Observacao(String TX_Observacao) {
        this.TX_Observacao = TX_Observacao;
    }

    public String getNM_Endereco_Coleta() {
        return NM_Endereco_Coleta;
    }

    public void setNM_Endereco_Coleta(String NM_Endereco_Coleta) {
        this.NM_Endereco_Coleta = NM_Endereco_Coleta;
    }

    public String getNM_Bairro_Coleta() {
        return NM_Bairro_Coleta;
    }

    public void setNM_Bairro_Coleta(String NM_Bairro_Coleta) {
        this.NM_Bairro_Coleta = NM_Bairro_Coleta;
    }

    public int getOID_Modal() {
        return oid_Modal;
    }

    public void setOID_Modal(int n) {
        this.oid_Modal = n;
    }

    public String getNM_Modal() {
        return NM_Modal;
    }

    public void setNM_Modal(String NM_Modal) {
        this.NM_Modal = NM_Modal;
    }

    public String getCD_Modal() {
        return CD_Modal;
    }

    public void setCD_Modal(String CD_Modal) {
        this.CD_Modal = CD_Modal;
    }

    public int getOID_Unidade() {
        return oid_Unidade;
    }

    public void setOID_Unidade(int n) {
        this.oid_Unidade = n;
    }

    public String getNM_Fantasia() {
        return NM_Fantasia;
    }

    public void setNM_Fantasia(String NM_Fantasia) {
        this.NM_Fantasia = NM_Fantasia;
    }

    public String getCD_Unidade() {
        return CD_Unidade;
    }

    public void setCD_Unidade(String CD_Unidade) {
        this.CD_Unidade = CD_Unidade;
    }

    public String getOID_Pessoa_Destinatario() {
        return oid_Pessoa_Destinatario;
    }

    public void setOID_Pessoa_Destinatario(String n) {
        this.oid_Pessoa_Destinatario = n;
    }

    public String getOID_Pessoa() {
        return oid_Pessoa;
    }

    public void setOID_Pessoa(String n) {
        this.oid_Pessoa = n;
    }

    public String getNM_Razao_Social() {
        return NM_Razao_Social;
    }

    public void setNM_Razao_Social(String NM_Razao_Social) {
        this.NM_Razao_Social = NM_Razao_Social;
    }

    public String getNM_Razao_Social_Destinatario() {
        return NM_Razao_Social_Destinatario;
    }

    public void setNM_Razao_Social_Destinatario(
            String NM_Razao_Social_Destinatario) {
        this.NM_Razao_Social_Destinatario = NM_Razao_Social_Destinatario;
    }

    public int getOID_Cidade() {
        return oid_Cidade;
    }

    public void setOID_Cidade(int n) {
        this.oid_Cidade = n;
    }

    public String getNM_Cidade() {
        return NM_Cidade;
    }

    public void setNM_Cidade(String NM_Cidade) {
        this.NM_Cidade = NM_Cidade;
    }

    public String getCD_Cidade() {
        return CD_Cidade;
    }

    public void setCD_Cidade(String CD_Cidade) {
        this.CD_Cidade = CD_Cidade;
    }

    public int getOID_AIDOF() {
        return oid_AIDOF;
    }

    public void setOID_AIDOF(int n) {
        this.oid_AIDOF = n;
    }

    /*
     * ---------------- Bloco Padrão para Todas Classes ------------------
     */
    public String getUsuario_Stamp() {
        return Usuario_Stamp;
    }

    public void setUsuario_Stamp(String Usuario_Stamp) {
        this.Usuario_Stamp = Usuario_Stamp;
    }

    public String getDt_Stamp() {
        return Dt_Stamp;
    }

    public void setDt_Stamp(String Dt_Stamp) {
        this.Dt_Stamp = Dt_Stamp;
    }

    public String getDm_Stamp() {
        return Dm_Stamp;
    }

    public void setDm_Stamp(String Dm_Stamp) {
        this.Dm_Stamp = Dm_Stamp;
    }

    public int getOID() {
        return oid;
    }

    public void setOID(int n) {
        this.oid = n;
    }

    public void insert() throws Exception {

        // System.out.println("Coleta entrou no insert ");

        /*
         * Abre a conexão com o banco
         */
        Connection conn = null;
        String soma_aidof="N";
        ResultSet cursor2 = null;

        try {
            // Pede uma conexão ao gerenciador do driver
            // passando como parâmetro o NM_Coleta do DSN
            // o NM_Coleta de usuário e a senha do banco.
            conn = OracleConnection2.getWEB();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        /*
         * Gera um novo código (OID)
         */
        try {
            Statement stmt = conn.createStatement();
            ResultSet cursor = null;

		  // System.out.println("Coleta 1 ");

	    if (getDM_Situacao() != null && getDM_Situacao().equals("P")) {

		  // System.out.println("Coleta 2 ");

	    			cursor = stmt.executeQuery(
            				"SELECT MAX(OID_Coleta) FROM Coletas");

            			while (cursor.next())
            			{
		  // System.out.println("Coleta 3 ");

            				int oid = cursor.getInt(1);
            				setNR_Coleta(oid + 1);
            			}
	    }
	    else {
		  // System.out.println("Coleta 4 ");

		  StringBuffer buff = new StringBuffer();
		  buff.append(" SELECT AIDOF.NR_Proximo, AIDOF.OID_AIDOF ");
		  buff.append(" FROM Parametros_Filiais, AIDOF ");
		  buff.append(" WHERE Parametros_Filiais.OID_AIDOF_Coleta = AIDOF.OID_AIDOF ");
		  buff.append(" AND Parametros_Filiais.OID_Unidade = ");
		  buff.append(getOID_Unidade());

		  cursor2 = stmt.executeQuery(buff.toString());

		  // System.out.println("Coleta achando aidof ");

		  while (cursor2.next()) {
		  // System.out.println("Coleta 5 ");

		      setNR_Coleta(cursor2.getInt(1));
		      setOID_AIDOF(cursor2.getInt(2));
		      // System.out.println("Coleta aidof  ok---->>>" + getNR_Coleta());
		  }
		  soma_aidof="S";

		  // System.out.println("Coleta 6 ");
	    }

		  // System.out.println("Coleta 7 ");

            // System.out.println("Coleta antes ");

            //			cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        /*
         * Define o insert.
         */
        // System.out.println("Coleta antes do insert");

        StringBuffer buff = new StringBuffer();

        // System.out.println("Coleta inclui a");

        buff.append(" INSERT INTO Coletas (OID_Coleta, OID_Unidade, OID_Modal, OID_Pessoa, OID_Cidade, ");
        buff.append(" NR_Coleta, NM_Solicitante, DT_Coleta, HR_Coleta, DT_Coletado, HR_Coletado, ");
        buff.append(" DM_Intervalo_Almoco, DM_Tipo_Veiculo, DM_Munck, DM_KIT, QT_Volumes, NR_Peso, ");
        buff.append(" VL_Mercadoria, TX_Observacao, NM_Endereco_Coleta, NM_Bairro_Coleta, Dt_Stamp, ");
        buff.append(" Usuario_Stamp, Dm_Stamp, OID_Pessoa_Destinatario, NM_Destinatario, ");
        buff.append(" NM_Cidade_Destinatario, NM_Documento, VL_Custo, OID_Veiculo , VL_Total_Frete, ");
        buff.append(" DM_Tipo_Conhecimento, DT_Previsao_Entrega, HR_Previsao_Entrega, OID_Pessoa_Pagador, ");
        buff.append(" NM_Documento2, NR_Peso2, QT_Notas_Fiscais, NR_Telefone, DT_Programada, HR_Programada, ");
        buff.append(" DM_Situacao, NM_Especie, DT_Coleta_Destino, NR_Volumes, NR_Conhecimento, NM_Rota, NR_Liberacao, NR_Cartao, OID_Motorista, DM_Tipo, OID_Carreta, NR_Pedido, DM_Tipo_Coleta, OID_Cotacao) ");
        buff.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

        /*
         * Define os dados do SQL e executa o insert no banco.
         */
        try {
            PreparedStatement pstmt = conn.prepareStatement(buff.toString());
            pstmt.setInt(1, getNR_Coleta()); //getOID
            pstmt.setInt(2, getOID_Unidade());
            pstmt.setInt(3, getOID_Modal());

            // System.out.println("Coleta inclui 2");
            pstmt.setString(4, getOID_Pessoa());
            pstmt.setInt(5, getOID_Cidade());
            pstmt.setInt(6, getNR_Coleta());
            pstmt.setString(7, getNM_Solicitante());
            pstmt.setString(8, this.DT_Coleta);
            // System.out.println("Coleta inclui 3");
            pstmt.setString(9, getHR_Coleta());
            pstmt.setString(10, getDT_Coletado());
            pstmt.setString(11, getHR_Coletado());
            pstmt.setString(12, getDM_Intervalo_Almoco());
            pstmt.setString(13, getDM_Tipo_Veiculo());
            pstmt.setString(14, getDM_Munck());
            pstmt.setString(15, getDM_KIT());
            // System.out.println("Coleta inclui 4");
            pstmt.setInt(16, getQT_Volumes());
            pstmt.setDouble(17, getNR_Peso());
            pstmt.setDouble(18, getVL_Mercadoria());
            pstmt.setString(19, getTX_Observacao());
            pstmt.setString(20, getNM_Endereco_Coleta());
            pstmt.setString(21, getNM_Bairro_Coleta());
            pstmt.setString(22, getDt_Stamp());
            pstmt.setString(23, getUsuario_Stamp());
            pstmt.setString(24, getDm_Stamp());
            pstmt.setString(25, getOID_Pessoa_Destinatario());
            // System.out.println("Coleta inclui 5");
            pstmt.setString(26, getNM_Destinatario());
            pstmt.setString(27, getNM_Cidade_Destinatario());
            pstmt.setString(28, getNM_Documento());
            pstmt.setDouble(29, getVL_Custo());
            pstmt.setString(30, getOID_Veiculo());
            pstmt.setDouble(31, getVL_Total_Frete());
            pstmt.setString(32, getDM_Tipo_Conhecimento());
            // System.out.println("Coleta inclui 1");

            pstmt.setString(33, getDT_Previsao_Entrega());
            pstmt.setString(34, getHR_Previsao_Entrega());
            pstmt.setString(35, getOID_Pessoa_Pagador());
            pstmt.setString(36, getNM_Documento2());
            // System.out.println("Coleta inclui 2");
            pstmt.setInt(37, getNR_Peso2());
            pstmt.setInt(38, getQT_Notas_Fiscais());
            pstmt.setString(39, getNR_Telefone());

            pstmt.setString(40, this.DT_Programada);
            pstmt.setString(41, getHR_Programada());
            pstmt.setString(42, getDM_Situacao());

            pstmt.setString(43, getNM_Especie());
            pstmt.setString(44, getDT_Coleta_Destino());
            pstmt.setLong(45, getNR_Volumes());
            pstmt.setLong(46, getNR_Conhecimento());

            pstmt.setString(47, getNR_Cartao());
            pstmt.setString(48, getNR_Liberacao());
            pstmt.setString(49, getNM_Rota());

            pstmt.setString(50, getOID_Motorista());
            pstmt.setString(51, getDM_Tipo());
            pstmt.setString(52, getOID_Carreta());
            pstmt.setString(53, getNR_Pedido());
            pstmt.setString(54, "C");
            pstmt.setString(55, getOID_Cotacao());

            // System.out.println("Coleta inclui");

            // System.out.println(pstmt.toString());

            pstmt.executeUpdate();

            /*
             * Define o update.
            */

	    if (soma_aidof.equals("S")) {
		  // System.out.println("Coleta 90 ");

		  setNR_Coleta(getNR_Coleta() + 1);
		  buff.delete(0, buff.length());

		  // System.out.println("Coleta anstes do Update da AIDOF");

		  buff.append("UPDATE AIDOF SET NR_Proximo=? ");
		  buff.append("WHERE OID_AIDOF =?");

		  PreparedStatement pstmt1 = conn.prepareStatement(buff.toString());

		  pstmt1.setInt(1, getNR_Coleta());
		  pstmt1.setInt(2, getOID_AIDOF());

		  pstmt1.executeUpdate();
                  setNR_Coleta(getNR_Coleta() - 1);
		  // System.out.println("Coleta 98 ");

	    }

	    if (getOID_Cotacao() != null && getOID_Cotacao().length()>4) {
		  // System.out.println("Coleta 90 ");

		  buff.delete(0, buff.length());
		  // System.out.println("Coleta Cotacoes");

		  buff.append("UPDATE Cotacoes SET oid_Coleta=? ");
		  buff.append("WHERE OID_Cotacao =?");

		  PreparedStatement pstmt2 = conn.prepareStatement(buff.toString());

		  pstmt2.setInt(1, getNR_Coleta());
		  pstmt2.setString(2, getOID_Cotacao());
		  pstmt2.executeUpdate();

	    }


            // System.out.println("Coleta depois do Update da AIDOF");

            setOID(getNR_Coleta());

        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
            throw e;
        }
        /*
         * Faz o commit e fecha a conexão.
         */
        try {
            conn.commit();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void update() throws Exception {
        /*
         * Abre a conexão com o banco
         */
        Connection conn = null;
        try {
            // Pede uma conexão ao gerenciador do driver
            // passando como parâmetro o NM_Coleta do DSN
            // o NM_Coleta de usuário e a senha do banco.
            conn = OracleConnection2.getWEB();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        /*
         * Define o update.
         */
        StringBuffer buff = new StringBuffer();
        buff.append(" UPDATE Coletas SET OID_Modal=?, ");
        buff.append(" OID_Cidade=?, ");
        buff.append(" NM_Solicitante=?, ");
        buff.append(" DM_Intervalo_Almoco=?, ");
        buff.append(" DM_Tipo_Veiculo=?, ");
        buff.append(" DM_Munck=?, ");
        buff.append(" DM_KIT=?, ");
        buff.append(" QT_Volumes=?, ");
        buff.append(" NR_Peso=?, ");
        buff.append(" VL_Mercadoria=?, ");
        buff.append(" TX_Observacao=?, ");
        buff.append(" NM_Endereco_Coleta=?, ");
        buff.append(" NM_Bairro_Coleta=?, ");
        buff.append(" DT_Coleta=?, ");
        buff.append(" HR_Coleta=?, ");
        buff.append(" OID_Pessoa_Destinatario=?, ");
        buff.append(" NM_Destinatario=?, ");
        buff.append(" NM_Cidade_Destinatario=?, ");
        buff.append(" NM_Documento=?, ");
        buff.append(" VL_Custo=?, ");
        buff.append(" OID_Veiculo=?, ");
        buff.append(" VL_Total_Frete=?, ");
        buff.append(" DM_Tipo_Conhecimento=?, ");
        buff.append(" DT_Previsao_Entrega=?, ");
        buff.append(" HR_Previsao_Entrega=?, ");
        buff.append(" NR_Peso2=?, ");
        buff.append(" NM_Destinatario2=?, ");
        buff.append(" NM_Cidade_Destinatario2=?, ");
        buff.append(" NM_Documento2=?, ");
        buff.append(" NR_Peso3=?, ");
        buff.append(" NM_Destinatario3=?, ");
        buff.append(" NM_Cidade_Destinatario3=?, ");
        buff.append(" NM_Documento3=?, ");
        buff.append(" NR_Peso4=?, ");
        buff.append(" NM_Destinatario4=?, ");
        buff.append(" NM_Cidade_Destinatario4=?, ");
        buff.append(" NM_Documento4=?, ");
        buff.append(" QT_Notas_Fiscais=?, ");
        buff.append(" NR_Telefone=?, ");
        buff.append(" nm_especie=?, ");
        buff.append(" nm_especie2=?, ");
        buff.append(" nm_especie3=?, ");
        buff.append(" nm_especie4=?, ");
        buff.append(" dt_coleta_destino=?, ");
        buff.append(" dt_coleta_destino2=?, ");
        buff.append(" dt_coleta_destino3=?, ");
        buff.append(" dt_coleta_destino4=?, ");
        buff.append(" nr_volumes=?, ");
        buff.append(" nr_volumes2=?, ");
        buff.append(" nr_volumes3=?, ");
        buff.append(" nr_volumes4=?, ");
        buff.append(" nr_conhecimento=?, ");
        buff.append(" nr_conhecimento2=?, ");
        buff.append(" nr_conhecimento3=?, ");
        buff.append(" nr_conhecimento4=?, ");
        buff.append(" NR_Cartao=?, ");
        buff.append(" NR_Pedido=?, ");
        buff.append(" NR_Liberacao=?, ");
        buff.append(" NM_Rota=?, ");
        buff.append(" oid_Carreta=?, ");
        buff.append(" DM_Tipo=?, ");
        buff.append(" oid_Motorista=?, ");
        buff.append(" oid_Pessoa_Pagador=? ");
        buff.append(" WHERE OID_Coleta=?");
        /*
         * Define os dados do SQL e executa o insert no banco.
         */
        try {
            PreparedStatement pstmt = conn.prepareStatement(buff.toString());

            pstmt.setInt(1, getOID_Modal());
            pstmt.setInt(2, getOID_Cidade());
            pstmt.setString(3, getNM_Solicitante());
            pstmt.setString(4, getDM_Intervalo_Almoco());
            pstmt.setString(5, getDM_Tipo_Veiculo());
            pstmt.setString(6, getDM_Munck());
            pstmt.setString(7, getDM_KIT());
            pstmt.setInt(8, getQT_Volumes());
            pstmt.setDouble(9, getNR_Peso());
            pstmt.setDouble(10, getVL_Mercadoria());
            pstmt.setString(11, getTX_Observacao());
            pstmt.setString(12, getNM_Endereco_Coleta());
            pstmt.setString(13, getNM_Bairro_Coleta());
            pstmt.setString(14, this.DT_Coleta);
            pstmt.setString(15, getHR_Coleta());
            pstmt.setString(16, getOID_Pessoa_Destinatario());
            pstmt.setString(17, getNM_Destinatario());
            pstmt.setString(18, getNM_Cidade_Destinatario());
            pstmt.setString(19, getNM_Documento());
            pstmt.setDouble(20, getVL_Custo());
            pstmt.setString(21, getOID_Veiculo());
            pstmt.setDouble(22, getVL_Total_Frete());
            pstmt.setString(23, getDM_Tipo_Conhecimento());
            pstmt.setString(24, this.DT_Previsao_Entrega);
            pstmt.setString(25, getHR_Previsao_Entrega());

            pstmt.setInt(26, getNR_Peso2());
            pstmt.setString(27, getNM_Destinatario2());
            pstmt.setString(28, getNM_Cidade_Destinatario2());
            pstmt.setString(29, getNM_Documento2());

            pstmt.setInt(30, getNR_Peso3());
            pstmt.setString(31, getNM_Destinatario3());
            pstmt.setString(32, getNM_Cidade_Destinatario3());
            pstmt.setString(33, getNM_Documento3());

            pstmt.setInt(34, getNR_Peso4());
            pstmt.setString(35, getNM_Destinatario4());
            pstmt.setString(36, getNM_Cidade_Destinatario4());
            pstmt.setString(37, getNM_Documento4());
            pstmt.setInt(38, getQT_Notas_Fiscais());
            pstmt.setString(39, getNR_Telefone());

            pstmt.setString(40, getNM_Especie());
            pstmt.setString(41, getNM_Especie2());
            pstmt.setString(42, getNM_Especie3());
            pstmt.setString(43, getNM_Especie4());

            pstmt.setString(44, getDT_Coleta_Destino());
            pstmt.setString(45, getDT_Coleta_Destino2());
            pstmt.setString(46, getDT_Coleta_Destino3());
            pstmt.setString(47, getDT_Coleta_Destino4());

            pstmt.setLong(48, getNR_Volumes());
            pstmt.setLong(49, getNR_Volumes2());
            pstmt.setLong(50, getNR_Volumes3());
            pstmt.setLong(51, getNR_Volumes4());

            pstmt.setLong(52, getNR_Conhecimento());
            pstmt.setLong(53, getNR_Conhecimento2());
            pstmt.setLong(54, getNR_Conhecimento3());
            pstmt.setLong(55, getNR_Conhecimento4());

            pstmt.setString(56, getNR_Cartao());
            pstmt.setString(57, getNR_Pedido());
            pstmt.setString(58, getNR_Liberacao());
            pstmt.setString(59, getNM_Rota());

            pstmt.setString(60, getOID_Carreta());
            pstmt.setString(61, getDM_Tipo());
            pstmt.setString(62, getOID_Motorista());
            pstmt.setString(63, getOID_Pessoa_Pagador());
            pstmt.setInt(64, getOID());

            // System.out.println("update col->> " + pstmt.toString());

            pstmt.executeUpdate();
        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
            throw e;
        }
        /*
         * Faz o commit e fecha a conexão.
         */
        try {
            conn.commit();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    public void atualiza_programacao() throws Exception {
        /*
         * Abre a conexão com o banco


         */

		  // System.out.println("Atualiza 1 ");

        Connection conn = null;
		  // System.out.println("Atualiza 2 ");


        StringBuffer buff = new StringBuffer();
		  // System.out.println("Atualiza 3 ");

        ResultSet cursor2 = null;

		  // System.out.println("Atualiza 4 ");

        try {
            // Pede uma conexão ao gerenciador do driver
            // passando como parâmetro o NM_Coleta do DSN
            // o NM_Coleta de usuário e a senha do banco.
            conn = OracleConnection2.getWEB();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        /*
         * Define o update.
         */
	          Statement stmt = conn.createStatement();

		  // System.out.println("Atualiza 2 ");

 		  buff.delete(0, buff.length());
		  buff.append(" SELECT AIDOF.NR_Proximo, AIDOF.OID_AIDOF ");
		  buff.append(" FROM Parametros_Filiais, AIDOF ");
		  buff.append(" WHERE Parametros_Filiais.OID_AIDOF_Coleta = AIDOF.OID_AIDOF ");
		  buff.append(" AND Parametros_Filiais.OID_Unidade = ");
		  buff.append(getOID_Unidade());

		  cursor2 = stmt.executeQuery(buff.toString());

		  // System.out.println("Coleta achando aidof ");

		  while (cursor2.next()) {
		  // System.out.println("Atualiza 3 ");

		      setNR_Coleta(cursor2.getInt(1));
		      setOID_AIDOF(cursor2.getInt(2));
		      // System.out.println("Coleta aidof  ok---->>>" + getNR_Coleta());
		  }


		  // System.out.println("Atualiza 4 " );



        buff.delete(0, buff.length());
        buff.append("UPDATE Coletas SET DM_Situacao=?, OID_Veiculo=?, DT_Coleta=?, HR_Coleta=?, NR_Coleta=? , OID_Motorista=?, OID_Carreta=?, DT_Previsao_Entrega=?" );
        buff.append("  WHERE OID_Coleta=?");
		  // System.out.println("Atualiza 8 ");

        /*
         * Define os dados do SQL e executa o insert no banco.
         */
        try {
            PreparedStatement pstmt = conn.prepareStatement(buff.toString());

		  // System.out.println("Atualiza 9 ");

            pstmt.setString(1, "A");
            pstmt.setString(2, getOID_Veiculo());
            pstmt.setString(3, Data.getDataDMY());
            pstmt.setString(4, Data.getHoraHM());
            pstmt.setInt(5, getNR_Coleta());
            pstmt.setString(6, getOID_Motorista());
            pstmt.setString(7, getOID_Carreta());
            pstmt.setString(8, getDT_Previsao_Entrega());
            pstmt.setInt(9, getOID());

            // System.out.println("atualiza programacao ->> " + pstmt.toString());

            pstmt.executeUpdate();

		  buff.delete(0, buff.length());

		  // System.out.println("Coleta anstes do Update da AIDOF");

		  buff.append("UPDATE AIDOF SET NR_Proximo=? ");
		  buff.append("WHERE OID_AIDOF =?");

		  PreparedStatement pstmt1 = conn.prepareStatement(buff.toString());

                  setNR_Coleta(getNR_Coleta() +1);

		  pstmt1.setInt(1, getNR_Coleta());
		  pstmt1.setInt(2, getOID_AIDOF());

		  pstmt1.executeUpdate();

	    // System.out.println("Atualiza 71 ");

        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
            throw e;
        }
        /*
         * Faz o commit e fecha a conexão.
         */
        try {
            conn.commit();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void cancela_programacao() throws Exception {
        /*
         * Abre a conexão com o banco
         */
        Connection conn = null;
        try {
            // Pede uma conexão ao gerenciador do driver
            // passando como parâmetro o NM_Coleta do DSN
            // o NM_Coleta de usuário e a senha do banco.
            conn = OracleConnection2.getWEB();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        /*
         * Define o update.
         */
        StringBuffer buff = new StringBuffer();
        buff.append("UPDATE Coletas SET DM_Situacao=?, OID_Veiculo=?, DT_Coleta=?, HR_Coleta=? " );
        buff.append("  WHERE OID_Coleta=?");
        /*
         * Define os dados do SQL e executa o insert no banco.
         */
        try {
            PreparedStatement pstmt = conn.prepareStatement(buff.toString());

            pstmt.setString(1, "C");
            pstmt.setString(2, null);
            pstmt.setString(3, "");
            pstmt.setString(4, "");
            pstmt.setInt(5, getOID());

            // System.out.println("cancela programacao ->> " + pstmt.toString());

            pstmt.executeUpdate();
        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
            throw e;
        }
        /*
         * Faz o commit e fecha a conexão.
         */
        try {
            conn.commit();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void cancela_coleta() throws Exception {
        /*
         * Abre a conexão com o banco
         */
        Connection conn = null;
        try {
            // Pede uma conexão ao gerenciador do driver
            // passando como parâmetro o NM_Coleta do DSN
            // o NM_Coleta de usuário e a senha do banco.
            conn = OracleConnection2.getWEB();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        /*
         * Define o update.
         */
        StringBuffer buff = new StringBuffer();
        buff.append("UPDATE Coletas SET DM_Situacao=? " );
        buff.append("  WHERE OID_Coleta=?");
        /*
         * Define os dados do SQL e executa o insert no banco.
         */
        try {
            PreparedStatement pstmt = conn.prepareStatement(buff.toString());

            pstmt.setString(1, "C");
            pstmt.setInt(2, getOID());

            // System.out.println("cancela cancela_coleta ->> " + pstmt.toString());

            pstmt.executeUpdate();
        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
            throw e;
        }
        /*
         * Faz o commit e fecha a conexão.
         */
        try {
            conn.commit();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }





    public void delete(int oid) throws Exception {
        /*
         * Abre a conexão com o banco
         */
        Connection conn = null;
        try {
            // Pede uma conexão ao gerenciador do driver
            // passando como parâmetro o NM_Coleta do DSN
            // o NM_Coleta de usuário e a senha do banco.
            conn = OracleConnection2.getWEB();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        /*
         * Define o DELETE.
         */
        StringBuffer buff = new StringBuffer();
        buff.append("DELETE FROM Coletas ");
        buff.append(" where Coletas.OID_Coleta =");
        buff.append(oid);
        /*
         * Define os dados do SQL e executa o insert no banco.
         */
        try {
            PreparedStatement pstmt = conn.prepareStatement(buff.toString());
            pstmt.executeUpdate();
        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
            throw e;
        }
        /*
         * Faz o commit e fecha a conexão.
         */
        try {
            conn.commit();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static final ColetaBean getByOID(int oid) throws Exception {
        /*
         * Abre a conexão com o banco
         */
        Connection conn = null;
        try {
            // Pede uma conexão ao gerenciador do driver
            // passando como parâmetro o NM_Coleta do DSN
            // o NM_Coleta de usuário e a senha do banco.
            conn = OracleConnection2.getWEB();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        ColetaBean p = new ColetaBean();
        try {
            StringBuffer buff = new StringBuffer();
            buff.append("SELECT Coletas.OID_Coleta,	Coletas.OID_Unidade, Coletas.OID_Modal, ");
            buff.append(" Coletas.OID_Pessoa, Coletas.OID_Pessoa_Destinatario, Coletas.OID_Cidade, ");
            buff.append(" Coletas.NR_Coleta, Coletas.NM_Solicitante, Coletas.DT_Coleta, Coletas.HR_Coleta, ");
            buff.append(" Coletas.DT_Coletado, Coletas.HR_Coletado, Coletas.DM_Intervalo_Almoco, ");
            buff.append(" Coletas.DM_Tipo_Veiculo, Coletas.DM_Munck, Coletas.DM_KIT, Coletas.QT_Volumes, ");
            buff.append(" Coletas.NR_Peso, Coletas.VL_Mercadoria, Coletas.TX_Observacao, ");
            buff.append(" Coletas.NM_Endereco_Coleta, Coletas.NM_Bairro_Coleta, Coletas.Dt_Stamp, ");
            buff.append(" Coletas.Usuario_Stamp, Coletas.Dm_Stamp, Pessoa_Unidade.NM_Fantasia, ");
            buff.append(" Pessoas.NM_Razao_Social, Pessoa_Destinatario.NM_Razao_Social, Cidades.NM_Cidade, ");
            buff.append(" Modal.NM_Modal , Cidades.CD_Cidade, Modal.CD_Modal, Unidades.CD_Unidade, ");
            buff.append(" Coletas.NM_Destinatario, Coletas.NM_Cidade_Destinatario, Coletas.NM_Documento, ");
            buff.append(" Coletas.VL_Custo, Coletas.OID_Veiculo, Coletas.VL_Total_Frete, ");
            buff.append(" Coletas.DM_Tipo_Conhecimento, Coletas.VL_Mercadoria , Coletas.DT_Previsao_Entrega, ");
            buff.append(" Coletas.HR_Previsao_Entrega, ");
            buff.append(" NR_Peso2, NM_Destinatario2, NM_Cidade_Destinatario2, NM_Documento2, ");
            buff.append(" NR_Peso3, NM_Destinatario3, NM_Cidade_Destinatario3, NM_Documento3, ");
            buff.append(" NR_Peso4, NM_Destinatario4, NM_Cidade_Destinatario4, NM_Documento4, ");
            buff.append(" Coletas.OID_Pessoa_Pagador, Coletas.QT_Notas_Fiscais,  Coletas.NR_Telefone, ");
            buff.append(" Coletas.DT_Programada, Coletas.HR_Programada, Coletas.DM_Situacao, ");
            buff.append(" Coletas.NM_Especie, Coletas.NM_Especie2, Coletas.NM_Especie3, Coletas.NM_Especie4, ");
            buff.append(" Coletas.dt_coleta_destino, Coletas.dt_coleta_destino2, Coletas.dt_coleta_destino3, Coletas.dt_coleta_destino4, ");
            buff.append(" Coletas.nr_volumes, Coletas.nr_volumes2, Coletas.nr_volumes3, Coletas.nr_volumes4, ");
            buff.append(" Coletas.nr_conhecimento, Coletas.nr_conhecimento2, Coletas.nr_conhecimento3, Coletas.nr_conhecimento4, Coletas.NM_Rota, Coletas.NR_Cartao, Coletas.NR_Liberacao, Coletas.OID_Motorista, Coletas.oid_Carreta, Coletas.DM_Tipo, Coletas.NR_Pedido ");
            buff.append("FROM Coletas, Unidades, Pessoas, Modal, Cidades, Pessoas Pessoa_Unidade, Pessoas Pessoa_Destinatario ");
            buff.append("WHERE Coletas.OID_Unidade = Unidades.OID_Unidade AND Coletas.OID_Modal = Modal.OID_Modal AND Coletas.OID_Pessoa = Pessoas.OID_Pessoa  AND Coletas.OID_Pessoa_Destinatario = Pessoa_Destinatario.OID_Pessoa AND Coletas.OID_Cidade = Cidades.OID_Cidade AND Pessoa_Unidade.OID_Pessoa = Unidades.OID_Pessoa ");
            buff.append(" AND Coletas.OID_Coleta =");
            buff.append(oid);
            Statement stmt = conn.createStatement();
            ResultSet cursor = stmt.executeQuery(buff.toString());

            // System.out.println(buff.toString());

            while (cursor.next()) {
                p.setOID(cursor.getInt(1));
                p.setOID_Unidade(cursor.getInt(2));
                p.setOID_Modal(cursor.getInt(3));
                p.setOID_Pessoa(cursor.getString(4));
                p.setOID_Pessoa_Destinatario(cursor.getString(5));
                p.setOID_Cidade(cursor.getInt(6));
                p.setNR_Coleta(cursor.getInt(7));
                p.setNM_Solicitante(cursor.getString(8));
                p.setDT_Coleta(cursor.getString(9));
                p.setHR_Coleta(cursor.getString(10));
                // System.out.println("passou a ");


                p.setDT_Coletado(cursor.getString(11));
                p.setHR_Coletado(cursor.getString(12));
                p.setDM_Intervalo_Almoco(cursor.getString(13));
                p.setDM_Tipo_Veiculo(cursor.getString(14));
                p.setDM_Munck(cursor.getString(15));
                p.setDM_KIT(cursor.getString(16));

                // System.out.println("passou b ");

                p.setQT_Volumes(cursor.getInt(17));
                p.setNR_Peso(cursor.getDouble(18));
                p.setVL_Mercadoria(cursor.getDouble(19));
                p.setTX_Observacao(cursor.getString(20));
                p.setNM_Endereco_Coleta(cursor.getString(21));
                p.setNM_Bairro_Coleta(cursor.getString(22));
                p.setDt_Stamp(cursor.getString(23));
                p.setUsuario_Stamp(cursor.getString(24));
                p.setDm_Stamp(cursor.getString(25));
                p.setNM_Fantasia(cursor.getString(26));
                p.setNM_Razao_Social(cursor.getString(27));
                p.setNM_Razao_Social_Destinatario(cursor.getString(28));
                p.setNM_Cidade(cursor.getString(29));
                p.setNM_Modal(cursor.getString(30));
                p.setCD_Cidade(cursor.getString(31));
                p.setCD_Modal(cursor.getString(32));
                p.setCD_Unidade(cursor.getString(33));
                p.setNM_Destinatario(cursor.getString(34));
                p.setNM_Cidade_Destinatario(cursor.getString(35));
                p.setNM_Documento(cursor.getString(36));
                p.setVL_Custo(cursor.getDouble(37));
                p.setOID_Veiculo(cursor.getString(38));
                p.setVL_Total_Frete(cursor.getDouble(39));
                p.setDM_Tipo_Conhecimento(cursor.getString(40));
                p.setVL_Mercadoria(cursor.getDouble(41));
                p.setDT_Previsao_Entrega(cursor.getString(42));
                p.setHR_Previsao_Entrega(cursor.getString(43));

                p.setNR_Peso2(cursor.getInt(44));
                p.setNM_Destinatario2(cursor.getString(45));
                p.setNM_Cidade_Destinatario2(cursor.getString(46));
                p.setNM_Documento2(cursor.getString(47));

                p.setNR_Peso3(cursor.getInt(48));
                p.setNM_Destinatario3(cursor.getString(49));
                p.setNM_Cidade_Destinatario3(cursor.getString(50));
                p.setNM_Documento3(cursor.getString(51));

                p.setNR_Peso4(cursor.getInt(52));
                p.setNM_Destinatario4(cursor.getString(53));
                p.setNM_Cidade_Destinatario4(cursor.getString(54));
                p.setNM_Documento4(cursor.getString(55));
                p.setOID_Pessoa_Pagador(cursor.getString(56));
                p.setQT_Notas_Fiscais(cursor.getInt(57));

                p.setNR_Telefone(cursor.getString(58));

                p.setDT_Programada(cursor.getString(59));
                p.setHR_Programada(cursor.getString(60));
                p.setDM_Situacao(cursor.getString(61));

                p.setNM_Especie(cursor.getString("nm_especie"));
                p.setNM_Especie2(cursor.getString("nm_especie2"));
                p.setNM_Especie3(cursor.getString("nm_especie3"));
                p.setNM_Especie4(cursor.getString("nm_especie4"));

                p.setDT_Coleta_Destino(cursor.getString("dt_coleta_destino"));
                p.setDT_Coleta_Destino2(cursor.getString("dt_coleta_destino2"));
                p.setDT_Coleta_Destino3(cursor.getString("dt_coleta_destino3"));
                p.setDT_Coleta_Destino4(cursor.getString("dt_coleta_destino4"));

                p.setNR_Volumes(cursor.getLong("nr_volumes"));
                p.setNR_Volumes2(cursor.getLong("nr_volumes2"));
                p.setNR_Volumes3(cursor.getLong("nr_volumes3"));
                p.setNR_Volumes4(cursor.getLong("nr_volumes4"));

                p.setNR_Conhecimento(cursor.getLong("nr_conhecimento"));
                p.setNR_Conhecimento2(cursor.getLong("nr_conhecimento2"));
                p.setNR_Conhecimento3(cursor.getLong("nr_conhecimento3"));
                p.setNR_Conhecimento4(cursor.getLong("nr_conhecimento4"));

                p.setNM_Rota(cursor.getString("NM_Rota"));
                p.setNR_Liberacao(cursor.getString("NR_Liberacao"));
                p.setNR_Cartao(cursor.getString("NR_Cartao"));

                p.setNR_Pedido(cursor.getString("NR_Pedido"));

                p.setOID_Motorista(cursor.getString("OID_Motorista"));
                p.setOID_Carreta(cursor.getString("OID_Carreta"));
                p.setDM_Tipo(cursor.getString("DM_Tipo"));

                // System.out.println("passou 0 ");

                double NR_Peso = 0, NR_Volumes = 0, VL_Nota_Fiscal = 0, VL_Total_Frete;
                int QT_Conhecimentos_Gerados = 0, QT_Conhecimentos_OK = 0, QT_Conhecimentos_Impressos = 0, QT_Notas_Fiscais = 0;
                /*
                buff = new StringBuffer();
                buff.append("select  NR_Peso, NR_Volumes, VL_Nota_Fiscal ");
                buff.append("FROM Notas_Fiscais ");
                buff.append("WHERE Notas_Fiscais.oid_Coleta =");
                buff.append(oid);
                stmt = conn.createStatement();
                ResultSet cursor2 = stmt.executeQuery(buff.toString());

                while (cursor2.next()) {
                    //// System.out.println("nota fiscal coleta ->> ");
                    NR_Peso = NR_Peso + cursor2.getDouble(1);
                    NR_Volumes = NR_Volumes + cursor2.getDouble(2);
                    VL_Nota_Fiscal = VL_Nota_Fiscal + cursor2.getDouble(3);
                    QT_Notas_Fiscais = QT_Notas_Fiscais + 1;

                    //  // System.out.println("nota fiscal coleta peso ->> "
                    // +NR_Peso );

                }
                if (QT_Notas_Fiscais == 0) {
                  buff = new StringBuffer ();
                  buff.append ("select  NR_Peso, NR_Volumes, VL_Nota_Fiscal ");
                  buff.append ("FROM Conhecimentos ");
                  buff.append ("WHERE Conhecimentos.oid_Coleta =");
                  buff.append (oid);
                  stmt = conn.createStatement ();
                  cursor2 = stmt.executeQuery (buff.toString ());
              
                  while (cursor2.next ()) {
                    //// System.out.println("nota fiscal coleta ->> ");
                    NR_Peso = NR_Peso + cursor2.getDouble (1);
                    NR_Volumes = NR_Volumes + cursor2.getDouble (2);
                    VL_Nota_Fiscal = VL_Nota_Fiscal + cursor2.getDouble (3);
                    QT_Notas_Fiscais = QT_Notas_Fiscais + 1;
                    //  // System.out.println("nota fiscal coleta peso ->> "
                    // +NR_Peso );
              
                  }
                }

                // System.out.println("passou 1 ");
                p.setQT_Notas_Notas_Fiscais(QT_Notas_Fiscais);
                p.setNR_Peso_Notas_Fiscais(NR_Peso);
                p.setQT_Volumes_Notas_Fiscais(NR_Volumes);
                p.setVL_Mercadoria_Notas_Fiscais(VL_Nota_Fiscal);
                // System.out.println("passou 2 ");

                buff = new StringBuffer();
                buff.append("select   Conhecimentos.VL_Total_Frete , Conhecimentos.DM_Impresso ");
                buff.append(" FROM Notas_Fiscais, Conhecimentos_Notas_Fiscais, Conhecimentos ");
                buff.append(" WHERE Notas_Fiscais.oid_Nota_Fiscal= Conhecimentos_Notas_Fiscais.oid_Nota_Fiscal ");
                buff.append(" AND  Conhecimentos_Notas_Fiscais.oid_Conhecimento = Conhecimentos.oid_Conhecimento ");
                buff.append(" AND  Notas_Fiscais.oid_Coleta =");
                buff.append(oid);
                stmt = conn.createStatement();
                cursor2 = stmt.executeQuery(buff.toString());

                while (cursor2.next()) {
                    QT_Conhecimentos_Gerados++;

                    VL_Total_Frete = cursor2.getDouble(1);
                    if (VL_Total_Frete > 0)
                        QT_Conhecimentos_OK++;

                    if (cursor2.getString(2).equals("S"))
                        QT_Conhecimentos_Impressos++;

                    //// System.out.println("conhecimento coleta frete = ->> "
                    // +VL_Total_Frete );
                }
                //// System.out.println("passou 3 ");
                */

                p.setQT_Conhecimentos_Gerados(QT_Conhecimentos_Gerados);
                p.setQT_Conhecimentos_OK(QT_Conhecimentos_OK);
                p.setQT_Conhecimentos_Impressos(QT_Conhecimentos_Impressos);

                // System.out.println("passou 99 ");

                //// System.out.println("passou 4 Gerados "
                // +QT_Conhecimentos_Gerados );
                //// System.out.println("passou 4 QT_Conhecimentos_OK " +
                // QT_Conhecimentos_OK);
                //// System.out.println("passou 4 QT_Conhecimentos_Impressos " +
                // QT_Conhecimentos_Impressos);

            }
            cursor.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            throw new Excecoes(e.getMessage(), e, "ColetaBean", "getByOID(int oid)");
        }
        return p;
    }

    public static final ColetaBean getByNR_Coleta(int NR_Coleta)
            throws Exception {
        /*
         * Abre a conexão com o banco
         */
        Connection conn = null;
        try {
            // Pede uma conexão ao gerenciador do driver
            // passando como parâmetro o NM_Coleta do DSN
            // o NM_Coleta de usuário e a senha do banco.
            conn = OracleConnection2.getWEB();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        ColetaBean p = new ColetaBean();
        try {
            StringBuffer buff = new StringBuffer();
            buff
                    .append("SELECT Coletas.OID_Coleta,	Coletas.OID_Unidade, Coletas.OID_Modal, Coletas.OID_Pessoa, Coletas.OID_Cidade, NR_Coleta, NM_Solicitante, DT_Coleta, HR_Coleta, DT_Coletado, HR_Coletado, DM_Intervalo_Almoco, DM_Tipo_Veiculo, DM_Munck, DM_KIT, QT_Volumes, NR_Peso, VL_Mercadoria, TX_Observacao, NM_Endereco_Coleta, NM_Bairro_Coleta, Coletas.Dt_Stamp, Coletas.Usuario_Stamp, Coletas.Dm_Stamp, Pessoa_Unidade.NM_Fantasia, Pessoas.NM_Razao_Social, Cidades.NM_Cidade, Modal.NM_Modal , Cidades.CD_Cidade, Modal.CD_Modal, Unidades.CD_Unidade ");
            buff
                    .append("FROM Coletas, Unidades, Pessoas, Modal, Cidades, Pessoas Pessoa_Unidade ");
            buff
                    .append("WHERE Coletas.OID_Unidade = Unidades.OID_Unidade AND Coletas.OID_Modal = Modal.OID_Modal AND Coletas.OID_Pessoa = Pessoas.OID_Pessoa AND Coletas.OID_Cidade = Cidades.OID_Cidade AND Pessoa_Unidade.OID_Pessoa = Unidades.OID_Pessoa ");
            buff.append(" AND Coletas.NR_Coleta =");
            buff.append(NR_Coleta);

            Statement stmt = conn.createStatement();
            ResultSet cursor = stmt.executeQuery(buff.toString());

            while (cursor.next()) {
                p.setOID(cursor.getInt(1));
                p.setOID_Unidade(cursor.getInt(2));
                p.setOID_Modal(cursor.getInt(3));
                p.setOID_Pessoa(cursor.getString(4));
                p.setOID_Cidade(cursor.getInt(5));
                p.setNR_Coleta(cursor.getInt(6));
                p.setNM_Solicitante(cursor.getString(7));
                p.setDT_Coleta(cursor.getString(8));
                p.setHR_Coleta(cursor.getString(9));
                p.setDT_Coletado(cursor.getString(10));
                p.setHR_Coletado(cursor.getString(11));
                p.setDM_Intervalo_Almoco(cursor.getString(12));
                p.setDM_Tipo_Veiculo(cursor.getString(13));
                p.setDM_Munck(cursor.getString(14));
                p.setDM_KIT(cursor.getString(15));
                p.setQT_Volumes(cursor.getInt(16));
                p.setNR_Peso(cursor.getDouble(17));
                p.setVL_Mercadoria(cursor.getDouble(18));
                p.setTX_Observacao(cursor.getString(19));
                p.setNM_Endereco_Coleta(cursor.getString(20));
                p.setNM_Bairro_Coleta(cursor.getString(21));
                p.setDt_Stamp(cursor.getString(22));
                p.setUsuario_Stamp(cursor.getString(23));
                p.setDm_Stamp(cursor.getString(24));
                p.setNM_Fantasia(cursor.getString(25));
                p.setNM_Razao_Social(cursor.getString(26));
                p.setNM_Cidade(cursor.getString(27));
                p.setNM_Modal(cursor.getString(28));
                p.setCD_Cidade(cursor.getString(29));
                p.setCD_Modal(cursor.getString(30));
                p.setCD_Unidade(cursor.getString(31));

            }
            cursor.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }

    public static final List getByNR_Coleta_Lista(int NR_Coleta)
            throws Exception {
        /*
         * Abre a conexão com o banco
         */
        Connection conn = null;
        try {
            // Pede uma conexão ao gerenciador do driver
            // passando como parâmetro o NM_Coleta do DSN
            // o NM_Coleta de usuário e a senha do banco.
            conn = OracleConnection2.getWEB();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        List Coletas_Lista = new ArrayList();
        try {
            StringBuffer buff = new StringBuffer();
            buff
                    .append("SELECT Coletas.OID_Coleta,	Coletas.OID_Unidade, Coletas.OID_Modal, Coletas.OID_Pessoa, Coletas.OID_Cidade, NR_Coleta, NM_Solicitante, DT_Coleta, HR_Coleta, DT_Coletado, HR_Coletado, DM_Intervalo_Almoco, DM_Tipo_Veiculo, DM_Munck, DM_KIT, QT_Volumes, NR_Peso, VL_Mercadoria, TX_Observacao, NM_Endereco_Coleta, NM_Bairro_Coleta, Coletas.Dt_Stamp, Coletas.Usuario_Stamp, Coletas.Dm_Stamp, Pessoa_Unidade.NM_Fantasia, Pessoas.NM_Razao_Social, Cidades.NM_Cidade, Modal.NM_Modal , Cidades.CD_Cidade, Modal.CD_Modal, Unidades.CD_Unidade ");
            buff
                    .append("FROM Coletas, Unidades, Pessoas, Modal, Cidades, Pessoas Pessoa_Unidade ");
            buff
                    .append("WHERE Coletas.OID_Unidade = Unidades.OID_Unidade AND Coletas.OID_Modal = Modal.OID_Modal AND Coletas.OID_Pessoa = Pessoas.OID_Pessoa AND Coletas.OID_Cidade = Cidades.OID_Cidade AND Pessoa_Unidade.OID_Pessoa = Unidades.OID_Pessoa ");
            buff.append(" AND Coletas.NR_Coleta =");
            buff.append(NR_Coleta);

            Statement stmt = conn.createStatement();
            ResultSet cursor = stmt.executeQuery(buff.toString());

            while (cursor.next()) {
                ColetaBean p = new ColetaBean();
                p.setOID(cursor.getInt(1));
                p.setOID_Unidade(cursor.getInt(2));
                p.setOID_Modal(cursor.getInt(3));
                p.setOID_Pessoa(cursor.getString(4));
                p.setOID_Cidade(cursor.getInt(5));
                p.setNR_Coleta(cursor.getInt(6));
                p.setNM_Solicitante(cursor.getString(7));
                p.setDT_Coleta(cursor.getString(8));
                p.setHR_Coleta(cursor.getString(9));
                p.setDT_Coletado(cursor.getString(10));
                p.setHR_Coletado(cursor.getString(11));
                p.setDM_Intervalo_Almoco(cursor.getString(12));
                p.setDM_Tipo_Veiculo(cursor.getString(13));
                p.setDM_Munck(cursor.getString(14));
                p.setDM_KIT(cursor.getString(15));
                p.setQT_Volumes(cursor.getInt(16));
                p.setNR_Peso(cursor.getDouble(17));
                p.setVL_Mercadoria(cursor.getDouble(18));
                p.setTX_Observacao(cursor.getString(19));
                p.setNM_Endereco_Coleta(cursor.getString(20));
                p.setNM_Bairro_Coleta(cursor.getString(21));
                p.setDt_Stamp(cursor.getString(22));
                p.setUsuario_Stamp(cursor.getString(23));
                p.setDm_Stamp(cursor.getString(24));
                p.setNM_Fantasia(cursor.getString(25));
                p.setNM_Razao_Social(cursor.getString(26));
                p.setNM_Cidade(cursor.getString(27));
                p.setNM_Modal(cursor.getString(28));
                p.setCD_Cidade(cursor.getString(29));
                p.setCD_Modal(cursor.getString(30));
                p.setCD_Unidade(cursor.getString(31));
                Coletas_Lista.add(p);
            }
            cursor.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Coletas_Lista;
    }

    public static final List getAll(String oid_Unidade, String NR_Coleta,
            String oid_Pessoa_Remetente, String DT_Emissao)

    throws Exception {
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

        List Coletas_Lista = new ArrayList();
        try {
            StringBuffer buff = new StringBuffer();
            buff
                    .append("SELECT Coletas.OID_Coleta,	Coletas.OID_Unidade, Coletas.OID_Modal, Coletas.OID_Pessoa, Coletas.OID_Cidade, NR_Coleta, NM_Solicitante, DT_Coleta, HR_Coleta, DT_Coletado, HR_Coletado, DM_Intervalo_Almoco, DM_Tipo_Veiculo, DM_Munck, DM_KIT, QT_Volumes, NR_Peso, VL_Mercadoria, TX_Observacao, NM_Endereco_Coleta, NM_Bairro_Coleta, Coletas.Dt_Stamp, Coletas.Usuario_Stamp, Coletas.Dm_Stamp, Pessoa_Unidade.NM_Fantasia, Pessoas.NM_Razao_Social, Cidades.NM_Cidade, Modal.NM_Modal , Cidades.CD_Cidade, Modal.CD_Modal, Unidades.CD_Unidade ");
            buff
                    .append("FROM Coletas, Unidades, Pessoas, Modal, Cidades, Pessoas Pessoa_Unidade ");
            buff
                    .append("WHERE Coletas.OID_Unidade = Unidades.OID_Unidade AND Coletas.OID_Modal = Modal.OID_Modal AND Coletas.OID_Pessoa = Pessoas.OID_Pessoa AND Coletas.OID_Cidade = Cidades.OID_Cidade AND Pessoa_Unidade.OID_Pessoa = Unidades.OID_Pessoa ");

            if (oid_Unidade != null && !oid_Unidade.equals("")
                    && !oid_Unidade.equals("null")) {
                buff.append(" AND Coletas.oid_Unidade =");
                buff.append(oid_Unidade);
            }
            if (NR_Coleta != null && !NR_Coleta.equals("")
                    && !NR_Coleta.equals("null")) {
                buff.append(" AND Coletas.NR_Coleta =");
                buff.append(NR_Coleta);
            }
            if (DT_Emissao != null && !DT_Emissao.equals("")
                    && !DT_Emissao.equals("null")) {
                buff.append(" AND Coletas.DT_Coleta ='");
                buff.append(DT_Emissao + "'");
            }
            if (oid_Pessoa_Remetente != null
                    && !oid_Pessoa_Remetente.equals("")
                    && !oid_Pessoa_Remetente.equals("null")) {
                buff.append(" AND Coletas.oid_Pessoa ='");
                buff.append(oid_Pessoa_Remetente + "'");
            }

            buff.append(" ORDER BY Coletas.NR_Coleta ");

            Statement stmt = conn.createStatement();
            ResultSet cursor = stmt.executeQuery(buff.toString());

            while (cursor.next()) {
                ColetaBean p = new ColetaBean();
                p.setOID(cursor.getInt(1));
                p.setOID_Unidade(cursor.getInt(2));
                p.setOID_Modal(cursor.getInt(3));
                p.setOID_Pessoa(cursor.getString(4));
                p.setOID_Cidade(cursor.getInt(5));
                p.setNR_Coleta(cursor.getInt(6));
                p.setNM_Solicitante(cursor.getString(7));
                p.setDT_Coleta(cursor.getString(8));
                p.setHR_Coleta(cursor.getString(9));
                p.setDT_Coletado(cursor.getString(10));
                p.setHR_Coletado(cursor.getString(11));
                p.setDM_Intervalo_Almoco(cursor.getString(12));
                p.setDM_Tipo_Veiculo(cursor.getString(13));
                p.setDM_Munck(cursor.getString(14));
                p.setDM_KIT(cursor.getString(15));
                p.setQT_Volumes(cursor.getInt(16));
                p.setNR_Peso(cursor.getDouble(17));
                p.setVL_Mercadoria(cursor.getDouble(18));
                p.setTX_Observacao(cursor.getString(19));
                p.setNM_Endereco_Coleta(cursor.getString(20));
                p.setNM_Bairro_Coleta(cursor.getString(21));
                p.setDt_Stamp(cursor.getString(22));
                p.setUsuario_Stamp(cursor.getString(23));
                p.setDm_Stamp(cursor.getString(24));
                p.setNM_Fantasia(cursor.getString(25));
                p.setNM_Razao_Social(cursor.getString(26));
                p.setNM_Cidade(cursor.getString(27));
                p.setNM_Modal(cursor.getString(28));
                p.setCD_Cidade(cursor.getString(29));
                p.setCD_Modal(cursor.getString(30));
                p.setCD_Unidade(cursor.getString(31));
                Coletas_Lista.add(p);
            }
            cursor.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Coletas_Lista;
    }

    public static void main(String args[]) throws Exception {
        ColetaBean pp = new ColetaBean();
        pp.setOID(11);
        //pp.setOID_Pessoa("11111111111");
        ColetaBean.getByOID(11);
        //pp.setOID_Unidade(1);
        //pp.setOID_Modal(1);
        //pp.setOID_Cidade(1);
        pp.setVL_Mercadoria(99999999999.88);
        pp.update();

    }

    public void setNM_Destinatario(String NM_Destinatario) {
        this.NM_Destinatario = NM_Destinatario;
    }

    public String getNM_Destinatario() {
        return NM_Destinatario;
    }

    public void setNM_Cidade_Destinatario(String NM_Cidade_Destinatario) {
        this.NM_Cidade_Destinatario = NM_Cidade_Destinatario;
    }

    public String getNM_Cidade_Destinatario() {
        return NM_Cidade_Destinatario;
    }

    public void setNM_Documento(String NM_Documento) {
        this.NM_Documento = NM_Documento;
    }

    public String getNM_Documento() {
        return NM_Documento;
    }

    public void setOID_Veiculo(String OID_Veiculo) {
        this.OID_Veiculo = OID_Veiculo;
    }

    public String getOID_Veiculo() {
        return OID_Veiculo;
    }

    public void setVL_Custo(double VL_Custo) {
        this.VL_Custo = VL_Custo;
    }

    public double getVL_Custo() {
        return VL_Custo;
    }

    public void setDM_Tipo_Conhecimento(String DM_Tipo_Conhecimento) {
        this.DM_Tipo_Conhecimento = DM_Tipo_Conhecimento;
    }

    public String getDM_Tipo_Conhecimento() {
        return DM_Tipo_Conhecimento;
    }

    public void setVL_Total_Frete(double VL_Total_Frete) {
        this.VL_Total_Frete = VL_Total_Frete;
    }

    public double getVL_Total_Frete() {
        return VL_Total_Frete;
    }

    public void setDT_Previsao_Entrega(String DT_Previsao_Entrega) {
        this.DT_Previsao_Entrega = DT_Previsao_Entrega;
    }

    public String getDT_Previsao_Entrega() {
        FormataDataBean DataFormatada = new FormataDataBean();
        DataFormatada.setDT_FormataData(DT_Previsao_Entrega);
        DT_Previsao_Entrega = DataFormatada.getDT_FormataData();

        return DT_Previsao_Entrega;
    }

    public void setHR_Previsao_Entrega(String HR_Previsao_Entrega) {
        this.HR_Previsao_Entrega = HR_Previsao_Entrega;
    }

    public String getHR_Previsao_Entrega() {
        return HR_Previsao_Entrega;
    }

    public void setNR_Peso2(int NR_Peso2) {
        this.NR_Peso2 = NR_Peso2;
    }

    public int getNR_Peso2() {
        return NR_Peso2;
    }

    public void setNR_Peso3(int NR_Peso3) {
        this.NR_Peso3 = NR_Peso3;
    }

    public int getNR_Peso3() {
        return NR_Peso3;
    }

    public void setNR_Peso4(int NR_Peso4) {
        this.NR_Peso4 = NR_Peso4;
    }

    public int getNR_Peso4() {
        return NR_Peso4;
    }

    public void setNM_Destinatario2(String NM_Destinatario2) {
        this.NM_Destinatario2 = NM_Destinatario2;
    }

    public String getNM_Destinatario2() {
        return NM_Destinatario2;
    }

    public void setNM_Destinatario3(String NM_Destinatario3) {
        this.NM_Destinatario3 = NM_Destinatario3;
    }

    public String getNM_Destinatario3() {
        return NM_Destinatario3;
    }

    public void setNM_Destinatario4(String NM_Destinatario4) {
        this.NM_Destinatario4 = NM_Destinatario4;
    }

    public String getNM_Destinatario4() {
        return NM_Destinatario4;
    }

    public void setNM_Cidade_Destinatario2(String NM_Cidade_Destinatario2) {
        this.NM_Cidade_Destinatario2 = NM_Cidade_Destinatario2;
    }

    public String getNM_Cidade_Destinatario2() {
        return NM_Cidade_Destinatario2;
    }

    public void setNM_Cidade_Destinatario3(String NM_Cidade_Destinatario3) {
        this.NM_Cidade_Destinatario3 = NM_Cidade_Destinatario3;
    }

    public String getNM_Cidade_Destinatario3() {
        return NM_Cidade_Destinatario3;
    }

    public void setNM_Cidade_Destinatario4(String NM_Cidade_Destinatario4) {
        this.NM_Cidade_Destinatario4 = NM_Cidade_Destinatario4;
    }

    public String getNM_Cidade_Destinatario4() {
        return NM_Cidade_Destinatario4;
    }

    public void setNM_Documento2(String NM_Documento2) {
        this.NM_Documento2 = NM_Documento2;
    }

    public String getNM_Documento2() {
        return NM_Documento2;
    }

    public void setNM_Documento3(String NM_Documento3) {
        this.NM_Documento3 = NM_Documento3;
    }

    public String getNM_Documento3() {
        return NM_Documento3;
    }

    public void setNM_Documento4(String NM_Documento4) {
        this.NM_Documento4 = NM_Documento4;
    }

    public String getNM_Documento4() {
        return NM_Documento4;
    }

    public void setOID_Pessoa_Pagador(String OID_Pessoa_Pagador) {
        this.OID_Pessoa_Pagador = OID_Pessoa_Pagador;
    }

    public String getOID_Pessoa_Pagador() {
        return OID_Pessoa_Pagador;
    }

    public void setNR_Peso_Notas_Fiscais(double NR_Peso_Notas_Fiscais) {
        this.NR_Peso_Notas_Fiscais = NR_Peso_Notas_Fiscais;
    }

    public double getNR_Peso_Notas_Fiscais() {
        return NR_Peso_Notas_Fiscais;
    }

    public void setQT_Volumes_Notas_Fiscais(double QT_Volumes_Notas_Fiscais) {
        this.QT_Volumes_Notas_Fiscais = QT_Volumes_Notas_Fiscais;
    }

    public double getQT_Volumes_Notas_Fiscais() {
        return QT_Volumes_Notas_Fiscais;
    }

    public void setVL_Custo_Notas_Fiscais(double VL_Custo_Notas_Fiscais) {
        this.VL_Custo_Notas_Fiscais = VL_Custo_Notas_Fiscais;
    }

    public double getVL_Custo_Notas_Fiscais() {
        return VL_Custo_Notas_Fiscais;
    }

    public void setVL_Total_Frete_Notas_Fiscais(
            double VL_Total_Frete_Notas_Fiscais) {
        this.VL_Total_Frete_Notas_Fiscais = VL_Total_Frete_Notas_Fiscais;
    }

    public double getVL_Total_Frete_Notas_Fiscais() {
        return VL_Total_Frete_Notas_Fiscais;
    }

    public void setVL_Mercadoria_Notas_Fiscais(
            double VL_Mercadoria_Notas_Fiscais) {
        this.VL_Mercadoria_Notas_Fiscais = VL_Mercadoria_Notas_Fiscais;
    }

    public double getVL_Mercadoria_Notas_Fiscais() {
        return VL_Mercadoria_Notas_Fiscais;
    }

    public void setQT_Conhecimentos_Gerados(int QT_Conhecimentos_Gerados) {
        this.QT_Conhecimentos_Gerados = QT_Conhecimentos_Gerados;
    }

    public int getQT_Conhecimentos_Gerados() {
        return QT_Conhecimentos_Gerados;
    }

    public void setQT_Conhecimentos_OK(int QT_Conhecimentos_OK) {
        this.QT_Conhecimentos_OK = QT_Conhecimentos_OK;
    }

    public int getQT_Conhecimentos_OK() {
        return QT_Conhecimentos_OK;
    }

    public void setQT_Conhecimentos_Impressos(int QT_Conhecimentos_Impressos) {
        this.QT_Conhecimentos_Impressos = QT_Conhecimentos_Impressos;
    }

    public int getQT_Conhecimentos_Impressos() {
        return QT_Conhecimentos_Impressos;
    }

    public void setQT_Notas_Fiscais(int QT_Notas_Fiscais) {
        this.QT_Notas_Fiscais = QT_Notas_Fiscais;
    }

    public int getQT_Notas_Fiscais() {
        return QT_Notas_Fiscais;
    }

    public void setNR_Telefone(String NR_Telefone) {
        this.NR_Telefone = NR_Telefone;
    }

    public String getNR_Telefone() {
        return NR_Telefone;
    }

    public void setQT_Notas_Notas_Fiscais(int QT_Notas_Notas_Fiscais) {
        this.QT_Notas_Notas_Fiscais = QT_Notas_Notas_Fiscais;
    }

    public int getQT_Notas_Notas_Fiscais() {
        return QT_Notas_Notas_Fiscais;
    }
  public void setDT_Programada(String DT_Programada) {

        FormataDataBean DataFormatada = new FormataDataBean();
        DataFormatada.setDT_FormataData(DT_Programada);
        DT_Programada = DataFormatada.getDT_FormataData();

    this.DT_Programada = DT_Programada;
  }
  public String getDT_Programada() {
    return DT_Programada;
  }
  public void setHR_Programada(String HR_Programada) {
    this.HR_Programada = HR_Programada;
  }
  public String getHR_Programada() {
    return HR_Programada;
  }
  public void setDM_Situacao(String DM_Situacao) {
    this.DM_Situacao = DM_Situacao;
  }
  public String getDM_Situacao() {
    return DM_Situacao;
  }
}
