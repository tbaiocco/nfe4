package com.master.root;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.master.util.JavaUtil;

import auth.OracleConnection2;

public class FornecedorBean  {

    private String NM_Agencia;
    private String CD_Conta_Corrente;
    private String NM_Banco;
    private String CD_Banco;
    private String NM_Conta;
    private String NM_Prazo;
    private String NM_Conta_Contabil;
    private String NM_Representante;
    private String NM_Entrega;
    private String DM_Gera_Livro_Fiscal;
    private String TX_Observacao1;
    private String NR_CNPJ_CPF;
    private String NM_Razao_Social;
    private String NM_Fantasia;
    private String TX_Observacao2;
    private String TX_Observacao3;
    private String DM_Impostos;
    private double NR_Dependentes;
    private double VL_Taxa_Cobranca;
    private String Usuario_Stamp;
    private String Dt_Stamp;
    private String Dm_Stamp;
    private String oid_Fornecedor;
    private String oid_Pessoa;
    private int oid_Ramo_Atividade;
    private String NM_Ramo_Atividade;
    private String CD_Ramo_Atividade;

    private int oid_Tipo_Servico;
    private String NM_Tipo_Servico;
    private String CD_Tipo_Servico;

    private int oid_Conta;
    private String CD_Conta;
    private int oid_Conta_Debito;
    private String CD_Conta_Debito;
    private String NM_Mae;
    private String NR_PIS;
    private String NR_INSS;
    private String DT_Nascimento;
    private String DT_Qualificacao;
    private String DM_Qualificacao;
    private int NR_Vencto1;
    private int NR_Vencto2;
    private int NR_Vencto3;
    private int NR_Vencto4;
    private String DM_Tipo_Faturamento;
    private long NR_Leadtime;
    private String DT_Vcto;
    private String NR_Permiso_Originario;
    private String CD_Fornecedor;
  private int oid_Grupo_Economico;

  public FornecedorBean() {
        CD_Banco = "";
        NM_Banco = "";
        CD_Conta_Corrente = "";
        NM_Agencia = "";
        NM_Conta = "";
        NM_Prazo = "";
        NM_Conta_Contabil = "";
        NM_Representante = "";
        NM_Entrega = "";
        DM_Gera_Livro_Fiscal = "";
        NR_Dependentes = 0;
        TX_Observacao1 = "";
        NR_CNPJ_CPF = "";
        NM_Razao_Social = "";
        NM_Fantasia = "";
        TX_Observacao2 = "";
        TX_Observacao3 = "";
        DM_Impostos = "";
        VL_Taxa_Cobranca = 0;
        Usuario_Stamp = "";
        Dm_Stamp = "";
        oid_Fornecedor = "";
        oid_Pessoa = "";
        oid_Ramo_Atividade = 0;
        NM_Ramo_Atividade = "";
        CD_Ramo_Atividade = "";
    }

    public String getNM_Agencia() {
        return NM_Agencia;
    }

    public void setNM_Agencia(String NM_Agencia) {
        this.NM_Agencia = NM_Agencia;
    }

    public String getCD_Conta_Corrente() {
        return CD_Conta_Corrente;
    }

    public void setCD_Conta_Corrente(String CD_Conta_Corrente) {
        this.CD_Conta_Corrente = CD_Conta_Corrente;
    }

    public String getNM_Banco() {
        return NM_Banco;
    }

    public void setNM_Banco(String NM_Banco) {
        this.NM_Banco = NM_Banco;
    }

    public String getCD_Banco() {
        return CD_Banco;
    }

    public void setCD_Banco(String CD_Banco) {
        this.CD_Banco = CD_Banco;
    }

    public String getNM_Conta() {
        return NM_Conta;
    }

    public void setNM_Conta(String NM_Conta) {
        this.NM_Conta = NM_Conta;
    }

    public String getNM_Representante() {
        return NM_Representante;
    }

    public void setNM_Representante(String NM_Representante) {
        this.NM_Representante = NM_Representante;
    }

    public String getNM_Prazo() {
        return NM_Prazo;
    }

    public void setNM_Prazo(String NM_Prazo) {
        this.NM_Prazo = NM_Prazo;
    }

    public String getTX_Observacao1() {
        return TX_Observacao1;
    }

    public void setTX_Observacao1(String TX_Observacao1) {
        this.TX_Observacao1 = TX_Observacao1;
    }

    public String getNM_Conta_Contabil() {
        return NM_Conta_Contabil;
    }

    public void setNM_Conta_Contabil(String NM_Conta_Contabil) {
        this.NM_Conta_Contabil = NM_Conta_Contabil;
    }

    public String getNM_Entrega() {
        return NM_Entrega;
    }

    public void setNM_Entrega(String NM_Entrega) {
        this.NM_Entrega = NM_Entrega;
    }

    public String getDM_Gera_Livro_Fiscal() {
        return DM_Gera_Livro_Fiscal;
    }

    public void setDM_Gera_Livro_Fiscal(String DM_Gera_Livro_Fiscal) {
        this.DM_Gera_Livro_Fiscal = DM_Gera_Livro_Fiscal;
    }

    public String getNR_CNPJ_CPF() {
        return NR_CNPJ_CPF;
    }

    public void setNR_CNPJ_CPF(String NR_CNPJ_CPF) {
        this.NR_CNPJ_CPF = NR_CNPJ_CPF;
    }

    public String getNM_Razao_Social() {
        return NM_Razao_Social;
    }

    public void setNM_Razao_Social(String NM_Razao_Social) {
        this.NM_Razao_Social = NM_Razao_Social;
    }

    public String getNM_Fantasia() {
        return NM_Fantasia;
    }

    public void setNM_Fantasia(String NM_Fantasia) {
        this.NM_Fantasia = NM_Fantasia;
    }

    public String getNM_Ramo_Atividade() {
        return NM_Ramo_Atividade;
    }

    public void setNM_Ramo_Atividade(String NM_Ramo_Atividade) {
        this.NM_Ramo_Atividade = NM_Ramo_Atividade;
    }

    public String getCD_Ramo_Atividade() {
        return CD_Ramo_Atividade;
    }

    public void setCD_Ramo_Atividade(String CD_Ramo_Atividade) {
        this.CD_Ramo_Atividade = CD_Ramo_Atividade;
    }

    public String getTX_Observacao2() {
        return TX_Observacao2;
    }

    public void setTX_Observacao2(String TX_Observacao2) {
        this.TX_Observacao2 = TX_Observacao2;
    }

    public String getTX_Observacao3() {
        return TX_Observacao3;
    }

    public void setTX_Observacao3(String TX_Observacao3) {
        this.TX_Observacao3 = TX_Observacao3;
    }

    public String getDM_Impostos() {
        return DM_Impostos;
    }

    public void setDM_Impostos(String DM_Impostos) {
        this.DM_Impostos = DM_Impostos;
    }

    public double getVL_Taxa_Cobranca() {
        return VL_Taxa_Cobranca;
    }

    public void setVL_Taxa_Cobranca(double VL_Taxa_Cobranca) {
        this.VL_Taxa_Cobranca = VL_Taxa_Cobranca;
    }

    public double getNR_Dependentes() {
        return NR_Dependentes;
    }

    public void setNR_Dependentes(double NR_Dependentes) {
        this.NR_Dependentes = NR_Dependentes;
    }

    public String getDM_Tipo_Faturamento() {
        return DM_Tipo_Faturamento;
    }

    public void setDM_Tipo_Faturamento(String DM_Tipo_Faturamento) {
        this.DM_Tipo_Faturamento = DM_Tipo_Faturamento;
    }

    public int getNR_Vencto1() {
        return NR_Vencto1;
    }

    public void setNR_Vencto1(int NR_Vencto1) {
        this.NR_Vencto1 = NR_Vencto1;
    }

    public int getNR_Vencto2() {
        return NR_Vencto2;
    }

    public void setNR_Vencto2(int NR_Vencto2) {
        this.NR_Vencto2 = NR_Vencto2;
    }

    public int getNR_Vencto3() {
        return NR_Vencto3;
    }

    public void setNR_Vencto3(int NR_Vencto3) {
        this.NR_Vencto3 = NR_Vencto3;
    }

    public int getNR_Vencto4() {
        return NR_Vencto4;
    }

    public void setNR_Vencto4(int NR_Vencto4) {
        this.NR_Vencto4 = NR_Vencto4;
    }
    public long getNR_Leadtime() {
        return NR_Leadtime;
    }

    public void setNR_Leadtime(long leadtime) {
        NR_Leadtime = leadtime;
    }

    public String getCD_Conta() {
        return CD_Conta;
    }

    public void setCD_Conta(String CD_Conta) {
        this.CD_Conta = CD_Conta;
    }

    public int getOid_Conta() {
        return oid_Conta;
    }

  public int getOid_Tipo_Servico() {
    return oid_Tipo_Servico;
  }

  public String getNM_Tipo_Servico() {
    return NM_Tipo_Servico;
  }

  public String getCD_Tipo_Servico() {
    return CD_Tipo_Servico;
  }

  public void setOid_Conta(int oid_Conta) {
        this.oid_Conta = oid_Conta;
    }

  public void setOid_Tipo_Servico(int oid_Tipo_Servico) {
    this.oid_Tipo_Servico = oid_Tipo_Servico;
  }

  public void setNM_Tipo_Servico(String NM_Tipo_Servico) {
    this.NM_Tipo_Servico = NM_Tipo_Servico;
  }

  public void setCD_Tipo_Servico(String CD_Tipo_Servico) {
    this.CD_Tipo_Servico = CD_Tipo_Servico;
  }
    public String getCD_Conta_Debito() {
        return CD_Conta_Debito;
    }
    public void setCD_Conta_Debito(String conta_Debito) {
        CD_Conta_Debito = conta_Debito;
    }
    public int getOid_Conta_Debito() {
        return oid_Conta_Debito;
    }

  public int getOid_Grupo_Economico () {
    return oid_Grupo_Economico;
  }

  public String getCD_Fornecedor () {
    return CD_Fornecedor;
  }

  public void setOid_Conta_Debito(int oid_Conta_Debito) {
        this.oid_Conta_Debito = oid_Conta_Debito;
    }

  public void setOid_Grupo_Economico (int oid_Grupo_Economico) {
    this.oid_Grupo_Economico = oid_Grupo_Economico;
  }

  public void setCD_Fornecedor (String CD_Fornecedor) {
    this.CD_Fornecedor = CD_Fornecedor;
  }
  public void setNM_Mae(String NM_Mae) {
      this.NM_Mae = NM_Mae;
  }

  public String getNM_Mae() {
      return NM_Mae;
  }

  public void setNR_PIS(String NR_PIS) {
      this.NR_PIS = NR_PIS;
  }

  public String getNR_PIS() {
      return NR_PIS;
  }

  public void setNR_INSS(String NR_INSS) {
      this.NR_INSS = NR_INSS;
  }

  public String getNR_INSS() {
      return NR_INSS;
  }

  public String getDT_Nascimento() {
      FormataDataBean DataFormatada = new FormataDataBean();
      DataFormatada.setDT_FormataData(DT_Nascimento);
      DT_Nascimento = DataFormatada.getDT_FormataData();
      if (DT_Nascimento.length() < 3)
          DT_Nascimento = null;
      return DT_Nascimento;
  }

  public void setDT_Nascimento(String DT_Nascimento) {
      this.DT_Nascimento = DT_Nascimento;
  }

  public void setDT_Qualificacao(String DT_Qualificacao) {
      this.DT_Qualificacao = DT_Qualificacao;
  }

  public String getDT_Qualificacao() {

      FormataDataBean DataFormatada = new FormataDataBean();
      DataFormatada.setDT_FormataData(DT_Qualificacao);
      DT_Qualificacao = DataFormatada.getDT_FormataData();
      if (DT_Qualificacao.length() < 3)
          DT_Qualificacao = null;

      return DT_Qualificacao;
  }

  public void setDM_Qualificacao(String DM_Qualificacao) {
      this.DM_Qualificacao = DM_Qualificacao;
  }

  public String getDM_Qualificacao() {
      return DM_Qualificacao;
  }


    /*
     * ---------------- Bloco Padr�o para Todas Classes ------------------
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

    public String getOID_Fornecedor() {
        return oid_Fornecedor;
    }

    public void setOID_Fornecedor(String OID_Fornecedor) {
        this.oid_Fornecedor = OID_Fornecedor;
    }

    public String getOID_Pessoa() {
        return oid_Pessoa;
    }

    public void setOID_Pessoa(String OID_Pessoa) {
        this.oid_Pessoa = OID_Pessoa;
    }

    public int getOID_Ramo_Atividade() {
        return oid_Ramo_Atividade;
    }

    public void setOID_Ramo_Atividade(int OID_Ramo_Atividade) {
        this.oid_Ramo_Atividade = OID_Ramo_Atividade;
    }

    public String getDT_Vcto() {
        FormataDataBean DataFormatada = new FormataDataBean();
        DataFormatada.setDT_FormataData(DT_Vcto);
        DT_Vcto = DataFormatada.getDT_FormataData();
        if (DT_Vcto.length() < 3)
            DT_Vcto = null;
        return DT_Vcto;
    }

    public void setDT_Vcto(String vcto) {
        DT_Vcto = vcto;
    }

    public String getNR_Permiso_Originario() {
        return NR_Permiso_Originario;
    }

    public void setNR_Permiso_Originario(String permiso_Originario) {
        NR_Permiso_Originario = permiso_Originario;
    }

    public void insert() throws Exception {
        /*
         * Abre a conex�o com o banco
         */
        Connection conn = null;
        try {
            // Pede uma conex�o ao gerenciador do driver
            // passando como par�metro o NM_Tipo_Documento do DSN
            // o NM_Tipo_Documento de usu�rio e a senha do banco.
            conn = OracleConnection2.getWEB();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        
        String sqlBusca="SELECT count (oid_Fornecedor) as proximo FROM Fornecedores";
        String cd_fornecedor="";
        Statement stmt = conn.createStatement ();
        ResultSet cursor = stmt.executeQuery (sqlBusca);
      
        if (cursor.next ()) {
          cd_fornecedor=String.valueOf(cursor.getLong("proximo")+1);
        }

        /*
         * Define o insert.
         */
        StringBuffer buff = new StringBuffer();
        buff.append(" INSERT INTO Fornecedores (  ");
        buff.append(" OID_Fornecedor,      ");
        buff.append(" OID_Pessoa,          ");
        buff.append(" OID_Ramo_Atividade,  ");
        buff.append(" NM_Conta,       	   ");
        buff.append(" NM_Prazo, 		   ");
        buff.append(" NM_Conta_Contabil,   ");
        buff.append(" NM_Representante,    ");
        buff.append(" NM_Entrega,          ");
        buff.append(" DM_Gera_Livro_Fiscal,");
        buff.append(" TX_Observacao1,      ");
        buff.append(" TX_Observacao2,  	   ");
        buff.append(" TX_Observacao3,      ");
        buff.append(" DM_Impostos,   	   ");
        buff.append(" VL_Taxa_Cobranca,    ");
        buff.append(" NR_Dependentes,      ");
        buff.append(" DT_Stamp,            ");
        buff.append(" DM_Stamp,            ");
        buff.append(" Usuario_Stamp,       ");
        buff.append(" NM_Banco,       	   ");
        buff.append(" NM_Agencia,          ");
        buff.append(" CD_Conta_Corrente,   ");
        buff.append(" NM_MAE,      		   ");
        buff.append(" CD_BANCO,    		   ");
        buff.append(" NR_PIS,      		   ");
        buff.append(" NR_INSS,      	   ");
        buff.append(" DT_NASCIMENTO,   	   ");
        buff.append(" DT_QUALIFICACAO, 	   ");
        buff.append(" DM_QUALIFICACAO, 	   ");
        buff.append(" NR_Vencto1, ");
        buff.append(" NR_Vencto2, ");
        buff.append(" NR_Vencto3, ");
        buff.append(" NR_Vencto4, ");
        buff.append(" DM_Tipo_Faturamento, ");
        buff.append(" DT_Vcto_Permiso, ");
        buff.append(" oid_Conta, ");
        buff.append(" oid_Conta_Debito, ");
        buff.append(" NR_Permiso_originario, ");
        buff.append(" oid_Grupo_Economico, ");
        buff.append(" CD_Fornecedor, ");
        buff.append(" oid_Tipo_Servico  )");
        buff.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

        /*
         * Define os dados do SQL e executa o insert no banco.
         */
        try {
            PreparedStatement pstmt = conn.prepareStatement(buff.toString());
            pstmt.setString(1, getOID_Fornecedor());
            pstmt.setString(2, getOID_Pessoa());
            pstmt.setInt(3, getOID_Ramo_Atividade());
            pstmt.setString(4, getNM_Conta());
            pstmt.setString(5, getNM_Prazo());
            pstmt.setString(6, getNM_Conta_Contabil());
            pstmt.setString(7, getNM_Representante());
            pstmt.setString(8, getNM_Entrega());
            pstmt.setString(9, getDM_Gera_Livro_Fiscal());
            pstmt.setString(10, getTX_Observacao1());
            pstmt.setString(11, getTX_Observacao2());
            pstmt.setString(12, getTX_Observacao3());
            pstmt.setString(13, getDM_Impostos());
            pstmt.setDouble(14, getVL_Taxa_Cobranca());
            pstmt.setDouble(15, getNR_Dependentes());
            pstmt.setString(16, getDt_Stamp());
            pstmt.setString(17, getUsuario_Stamp());
            pstmt.setString(18, getDm_Stamp());
            pstmt.setString(19, getNM_Banco());
            pstmt.setString(20, getNM_Agencia());
            pstmt.setString(21, getCD_Conta_Corrente());
            pstmt.setString(22, getNM_Mae());
            pstmt.setString(23, getCD_Banco());
            pstmt.setString(24, getNR_PIS());
            pstmt.setString(25, getNR_INSS());
            pstmt.setString(26, getDT_Nascimento());
            pstmt.setString(27, getDT_Qualificacao());
            pstmt.setString(28, getDM_Qualificacao());
            pstmt.setInt(29, getNR_Vencto1());
            pstmt.setInt(30, getNR_Vencto2());
            pstmt.setInt(31, getNR_Vencto3());
            pstmt.setInt(32, getNR_Vencto4());
            pstmt.setString(33, getDM_Tipo_Faturamento());
            pstmt.setString(34, getDT_Vcto());
            pstmt.setInt(35, getOid_Conta());
            pstmt.setInt(36, getOid_Conta_Debito());
            pstmt.setString(37, getNR_Permiso_Originario());
            pstmt.setInt(38, getOid_Grupo_Economico());
            pstmt.setString(39, cd_fornecedor);
            pstmt.setInt(40, getOid_Tipo_Servico());

            pstmt.executeUpdate();
        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
            throw e;
        }
        /*
         * Faz o commit e fecha a conex�o.
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
         * Abre a conex�o com o banco
         */
        Connection conn = null;
        try {
            // Pede uma conex�o ao gerenciador do driver
            // passando como par�metro o NM_Tipo_Documento do DSN
            // o NM_Tipo_Documento de usu�rio e a senha do banco.
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
        buff.append("UPDATE Fornecedores SET ");
        buff.append(" OID_Ramo_Atividade=?,     ");
        buff.append(" NM_Conta=?,       ");
        buff.append(" NM_Prazo=?, ");
        buff.append(" NM_Conta_Contabil=?,      ");
        buff.append(" NM_Representante=?,        ");
        buff.append(" NM_Entrega=?,       ");
        buff.append(" DM_Gera_Livro_Fiscal=?,       ");
        buff.append(" TX_Observacao1=?,        ");
        buff.append(" TX_Observacao2=?,  ");
        buff.append(" TX_Observacao3=?,    ");
        buff.append(" DM_Impostos=?,   ");
        buff.append(" VL_Taxa_Cobranca=?,       ");
        buff.append(" NR_Dependentes=?,       ");
        buff.append(" DT_Stamp=?,               ");
        buff.append(" DM_Stamp=?,               ");
        buff.append(" Usuario_Stamp=?,           ");
        buff.append(" NM_Banco=?,       ");
        buff.append(" NM_Agencia=?,       ");
        buff.append(" CD_Conta_Corrente=?,       ");
        buff.append(" CD_Banco=?,      ");
        buff.append(" NM_Mae=?,       ");
        buff.append(" NR_PIS=?,       ");
        buff.append(" NR_INSS=?,       ");
        buff.append(" DT_Nascimento=?,       ");
        buff.append(" DT_Qualificacao=?,       ");
        buff.append(" DM_Qualificacao=?,       ");
        buff.append(" NR_Vencto1=?,       ");
        buff.append(" NR_Vencto2=?,       ");
        buff.append(" NR_Vencto3=?,       ");
        buff.append(" NR_Vencto4=?,       ");
        buff.append(" DM_Tipo_Faturamento=?,       ");
        buff.append(" DT_Vcto_Permiso=?,       ");
        buff.append(" NR_Permiso_Originario=?,       ");
        buff.append(" oid_Conta=?,       ");
        buff.append(" oid_Conta_Debito=?,       ");
        buff.append(" CD_Fornecedor=?,       ");
        buff.append(" oid_Tipo_Servico=?,       ");
        buff.append(" oid_Grupo_Economico=?       ");
        buff.append(" WHERE OID_Fornecedor=?");
        /*
         * Define os dados do SQL e executa o insert no banco.
         */
        try {
            PreparedStatement pstmt = conn.prepareStatement(buff.toString());
            pstmt.setInt(1, getOID_Ramo_Atividade());
            pstmt.setString(2, getNM_Conta());
            pstmt.setString(3, getNM_Prazo());
            pstmt.setString(4, getNM_Conta_Contabil());
            pstmt.setString(5, getNM_Representante());
            pstmt.setString(6, getNM_Entrega());
            pstmt.setString(7, getDM_Gera_Livro_Fiscal());
            pstmt.setString(8, getTX_Observacao1());
            pstmt.setString(9, getTX_Observacao2());
            pstmt.setString(10, getTX_Observacao3());
            pstmt.setString(11, getDM_Impostos());
            pstmt.setDouble(12, getVL_Taxa_Cobranca());
            pstmt.setDouble(13, getNR_Dependentes());
            pstmt.setString(14, getDt_Stamp());
            pstmt.setString(15, getUsuario_Stamp());
            pstmt.setString(16, getDm_Stamp());
            pstmt.setString(17, getNM_Banco());
            pstmt.setString(18, getNM_Agencia());
            pstmt.setString(19, getCD_Conta_Corrente());
            pstmt.setString(20, getCD_Banco());
            pstmt.setString(21, getNM_Mae());
            pstmt.setString(22, getNR_PIS());
            pstmt.setString(23, getNR_INSS());
            pstmt.setString(24, getDT_Nascimento());
            pstmt.setString(25, getDT_Qualificacao());
            pstmt.setString(26, getDM_Qualificacao());
            pstmt.setInt(27, getNR_Vencto1());
            pstmt.setInt(28, getNR_Vencto2());
            pstmt.setInt(29, getNR_Vencto3());
            pstmt.setInt(30, getNR_Vencto4());
            pstmt.setString(31, getDM_Tipo_Faturamento());
            pstmt.setString(32, getDT_Vcto());
            pstmt.setString(33, getNR_Permiso_Originario());
            pstmt.setInt(34, getOid_Conta());
            pstmt.setInt(35, getOid_Conta_Debito());
            pstmt.setString(36, getCD_Fornecedor());
            pstmt.setInt(37, getOid_Tipo_Servico());
            pstmt.setInt(38, getOid_Grupo_Economico());
            pstmt.setString(39, getOID_Fornecedor());
            
            pstmt.executeUpdate();
        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
            throw e;
        }
        /*
         * Faz o commit e fecha a conex�o.
         */
        try {
            conn.commit();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void delete() throws Exception {
        /*
         * Abre a conex�o com o banco
         */
        Connection conn = null;
        try {
            // Pede uma conex�o ao gerenciador do driver
            // passando como par�metro o NM_Tipo_Documento do DSN
            // o NM_Tipo_Documento de usu�rio e a senha do banco.
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
        buff.append(" DELETE FROM Fornecedores ");
        buff.append(" WHERE OID_Fornecedor =?");
        /*
         * Define os dados do SQL e executa o insert no banco.
         */
        try {
            PreparedStatement pstmt = conn.prepareStatement(buff.toString());
            pstmt.setString(1, getOID_Fornecedor());
            pstmt.executeUpdate();
        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
            throw e;
        }
        /*
         * Faz o commit e fecha a conex�o.
         */
        try {
            StringBuffer buff_Pessoa = new StringBuffer();
            buff_Pessoa.append("DELETE FROM Grupos_Pessoas_Cargas ");
            buff_Pessoa.append("WHERE OID_Pessoa =?");
            PreparedStatement pstmt = conn.prepareStatement(buff_Pessoa.toString());
            pstmt.setString(1, getOID_Pessoa());
            pstmt.executeUpdate();

            buff_Pessoa.delete(0, buff_Pessoa.length());

            buff_Pessoa.append("DELETE FROM Pessoas ");
            buff_Pessoa.append("WHERE OID_Pessoa =?");
            pstmt = conn.prepareStatement(buff_Pessoa.toString());
            pstmt.setString(1, getOID_Pessoa());
            pstmt.executeUpdate();

        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
            throw e;
        }
        try {
            conn.commit();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static final FornecedorBean getByOID_Fornecedor(String oid) throws Exception {
        /*
         * Abre a conex�o com o banco
         */
        Connection conn = null;
        try {
            // Pede uma conex�o ao gerenciador do driver
            // passando como par�metro o NM_Tipo_Documento do DSN
            // o NM_Tipo_Documento de usu�rio e a senha do banco.
            conn = OracleConnection2.getWEB();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        FornecedorBean p = new FornecedorBean();
        try {
            StringBuffer buff = new StringBuffer();
            buff.append("SELECT                               ");
            buff.append("   Fornecedores.OID_Fornecedor,      ");
            buff.append("	Fornecedores.OID_Pessoa,          ");
            buff.append("	Fornecedores.OID_Ramo_Atividade,  ");
            buff.append("	Fornecedores.NM_Conta,            ");
            buff.append("	Fornecedores.NM_Prazo,      	  ");
            buff.append("	Fornecedores.NM_Conta_Contabil,   ");
            buff.append("	Fornecedores.NM_Representante,    ");
            buff.append("	Fornecedores.NM_Entrega,          ");
            buff.append("	Fornecedores.DM_Gera_Livro_Fiscal,");
            buff.append("	Fornecedores.TX_Observacao1,      ");
            buff.append("	Fornecedores.TX_Observacao2,      ");
            buff.append("	Fornecedores.TX_Observacao3,      ");
            buff.append("	Fornecedores.DM_Impostos,         ");
            buff.append("	Fornecedores.VL_Taxa_Cobranca,    ");
            buff.append("	Fornecedores.NR_Dependentes,      ");
            buff.append("	Pessoas.NR_CNPJ_CPF,              ");
            buff.append("	Pessoas.NM_Razao_Social,          ");
            buff.append("	Pessoas.NM_Fantasia,              ");
            buff.append("	Ramos_Atividades.CD_Ramo_Atividade,");
            buff.append("	Ramos_Atividades.NM_Ramo_Atividade,");
            buff.append("	Fornecedores.NM_Banco,            ");
            buff.append("	Fornecedores.NM_Agencia,          ");
            buff.append("	Fornecedores.CD_Conta_Corrente,   ");
            buff.append("	Fornecedores.CD_Banco,            ");
            buff.append("	Fornecedores.NM_MAE,              ");
            buff.append("	Fornecedores.NR_PIS,              ");
            buff.append("	Fornecedores.NR_INSS,             ");
            buff.append("	Fornecedores.DT_NASCIMENTO,       ");
            buff.append("	Fornecedores.DT_Qualificacao,     ");
            buff.append("	Fornecedores.DM_Qualificacao,     ");
            buff.append("	Fornecedores.NR_Vencto1,       	  ");
            buff.append("	Fornecedores.NR_Vencto2,       	  ");
            buff.append("	Fornecedores.NR_Vencto3,       	  ");
            buff.append("	Fornecedores.NR_Vencto4,       	  ");
            buff.append("	Fornecedores.DM_Tipo_Faturamento,  ");
            buff.append("	Fornecedores.DT_Vcto_Permiso,  	   ");
            buff.append("	Fornecedores.OID_Conta,  	   ");
            buff.append("	Fornecedores.OID_Conta_Debito,  	   ");
            buff.append("	Fornecedores.NR_Permiso_Originario, ");
            buff.append("	Fornecedores.CD_Fornecedor,  	   ");
            buff.append("	Fornecedores.OID_Grupo_Economico,  ");
            buff.append("	Fornecedores.OID_Tipo_Servico  	   ");
            buff.append(" FROM Fornecedores, Pessoas, Ramos_Atividades ");
            buff.append(" WHERE Fornecedores.OID_Pessoa          = Pessoas.OID_Pessoa                    ");
            buff.append(" AND   Fornecedores.OID_Ramo_Atividade  = Ramos_Atividades.OID_Ramo_Atividade   ");
            buff.append(" AND Fornecedores.OID_Fornecedor = '");
            buff.append(oid);
            buff.append("'");
            buff.append(" ORDER BY Pessoas.NM_RAZAO_SOCIAL ");

            Statement stmt = conn.createStatement();
            ResultSet cursor = stmt.executeQuery(buff.toString());

            while (cursor.next()) {
                p.setOID_Fornecedor(cursor.getString(1));
                p.setOID_Pessoa(cursor.getString(2));
                p.setOID_Ramo_Atividade(cursor.getInt(3));
                p.setNM_Conta(cursor.getString(4));
                p.setNM_Prazo(cursor.getString(5));
                p.setNM_Conta_Contabil(cursor.getString(6));
                p.setNM_Representante(cursor.getString(7));
                p.setNM_Entrega(cursor.getString(8));
                p.setDM_Gera_Livro_Fiscal(cursor.getString(9));
                p.setTX_Observacao1(cursor.getString(10));
                p.setTX_Observacao2(cursor.getString(11));
                p.setTX_Observacao3(cursor.getString(12));
                p.setDM_Impostos(cursor.getString(13));
                p.setVL_Taxa_Cobranca(cursor.getDouble(14));
                p.setNR_Dependentes(cursor.getDouble(15));
                p.setNR_CNPJ_CPF(cursor.getString(16));
                p.setNM_Razao_Social(cursor.getString(17));
                p.setNM_Fantasia(cursor.getString(18));
                p.setCD_Ramo_Atividade(cursor.getString(19));
                p.setNM_Ramo_Atividade(cursor.getString(20));
                p.setNM_Banco(cursor.getString(21));
                p.setNM_Agencia(cursor.getString(22));
                p.setCD_Conta_Corrente(cursor.getString(23));
                p.setCD_Banco(cursor.getString(24));
                p.setNM_Mae(cursor.getString(25));
                p.setNR_PIS(cursor.getString(26));
                p.setNR_INSS(cursor.getString(27));
                p.setDT_Nascimento(cursor.getString(28));
                p.setDT_Qualificacao(cursor.getString(29));
                p.setDM_Qualificacao(cursor.getString(30));
                p.setNR_Vencto1(cursor.getInt(31));
                p.setNR_Vencto2(cursor.getInt(32));
                p.setNR_Vencto3(cursor.getInt(33));
                p.setNR_Vencto4(cursor.getInt(34));
                p.setDM_Tipo_Faturamento(cursor.getString(35));
                p.setDT_Vcto(cursor.getString(36));
                p.setOid_Conta(cursor.getInt(37));
                p.setOid_Conta_Debito(cursor.getInt(38));
                p.setNR_Permiso_Originario(cursor.getString(39));
                p.setCD_Fornecedor(cursor.getString(40));
                p.setOid_Grupo_Economico(cursor.getInt(41));
                p.setOid_Tipo_Servico(cursor.getInt(42));

                if (p.getDT_Qualificacao() == null)
                    p.setDT_Qualificacao("");
                if (p.getNM_Mae() == null)
                    p.setNM_Mae("");
                if (p.getNR_PIS() == null)
                    p.setNR_PIS("");
                if (p.getNR_INSS() == null)
                    p.setNR_INSS("");
                if (p.getDT_Qualificacao() == null)
                    p.setDT_Qualificacao("");
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

    public static final FornecedorBean getByCD_Fornecedor(String NR_CNPJ_CPF) throws Exception {
        /*
         * Abre a conex�o com o banco
         */
        Connection conn = null;
        try {
            // Pede uma conex�o ao gerenciador do driver
            // passando como par�metro o NM_Tipo_Documento do DSN
            // o NM_Tipo_Documento de usu�rio e a senha do banco.
            conn = OracleConnection2.getWEB();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        FornecedorBean p = new FornecedorBean();
        try {
            StringBuffer buff = new StringBuffer();
            buff.append("SELECT                                 ");
            buff.append("   Fornecedores.OID_Fornecedor,        ");
            buff.append("	Fornecedores.OID_Pessoa,            ");
            buff.append("	Fornecedores.OID_Ramo_Atividade,    ");
            buff.append("	Fornecedores.NM_Conta,            	");
            buff.append("	Fornecedores.NM_Prazo,      		");
            buff.append("	Fornecedores.NM_Conta_Contabil,     ");
            buff.append("	Fornecedores.NM_Representante,      ");
            buff.append("	Fornecedores.NM_Entrega,            ");
            buff.append("	Fornecedores.DM_Gera_Livro_Fiscal,  ");
            buff.append("	Fornecedores.TX_Observacao1,        ");
            buff.append("	Fornecedores.TX_Observacao2,        ");
            buff.append("	Fornecedores.TX_Observacao3,        ");
            buff.append("	Fornecedores.DM_Impostos,           ");
            buff.append("	Fornecedores.VL_Taxa_Cobranca,      ");
            buff.append("	Fornecedores.NR_Dependentes,        ");
            buff.append("	Pessoas.NR_CNPJ_CPF,                ");
            buff.append("	Pessoas.NM_Razao_Social,            ");
            buff.append("	Pessoas.NM_Fantasia,                ");
            buff.append("	Ramos_Atividades.CD_Ramo_Atividade, ");
            buff.append("	Ramos_Atividades.NM_Ramo_Atividade, ");
            buff.append("	Fornecedores.NM_Banco,              ");
            buff.append("	Fornecedores.NM_Agencia,            ");
            buff.append("	Fornecedores.CD_Conta_Corrente,     ");
            buff.append("	Fornecedores.CD_Banco,           	");
            buff.append("	Fornecedores.NM_MAE,             	");
            buff.append("	Fornecedores.NR_PIS,             	");
            buff.append("	Fornecedores.NR_INSS,            	");
            buff.append("	Fornecedores.DT_NASCIMENTO,       	");
            buff.append("	Fornecedores.DT_Qualificacao,       ");
            buff.append("	Fornecedores.DM_Qualificacao,       ");
            buff.append("	Fornecedores.DT_Vencto1,       ");
            buff.append("	Fornecedores.DT_Vencto2,       ");
            buff.append("	Fornecedores.DT_Vencto3,       ");
            buff.append("	Fornecedores.DT_Vencto4,       ");
            buff.append("	Fornecedores.DM_Tipo_Faturamento    ");
            buff.append(" FROM Fornecedores, Pessoas, Ramos_Atividades ");
            buff.append(" WHERE Fornecedores.OID_Pessoa          = Pessoas.OID_Pessoa                    ");
            buff.append(" AND   Fornecedores.OID_Ramo_Atividade  = Ramos_Atividades.OID_Ramo_Atividade   ");
            buff.append(" AND   Pessoas.NR_CNPJ_CPF = '");
            buff.append(NR_CNPJ_CPF);
            buff.append("'");
            buff.append(" ORDER BY Pessoas.NM_RAZAO_SOCIAL ");

            Statement stmt = conn.createStatement();
            ResultSet cursor = stmt.executeQuery(buff.toString());

            while (cursor.next()) {
                p.setOID_Fornecedor(cursor.getString(1));
                p.setOID_Pessoa(cursor.getString(2));
                p.setOID_Ramo_Atividade(cursor.getInt(3));
                p.setNM_Conta(cursor.getString(4));
                p.setNM_Prazo(cursor.getString(5));
                p.setNM_Conta_Contabil(cursor.getString(6));
                p.setNM_Representante(cursor.getString(7));
                p.setNM_Entrega(cursor.getString(8));
                p.setDM_Gera_Livro_Fiscal(cursor.getString(9));
                p.setTX_Observacao1(cursor.getString(10));
                p.setTX_Observacao2(cursor.getString(11));
                p.setTX_Observacao3(cursor.getString(12));
                p.setDM_Impostos(cursor.getString(13));
                p.setVL_Taxa_Cobranca(cursor.getDouble(14));
                p.setNR_Dependentes(cursor.getDouble(15));
                p.setNR_CNPJ_CPF(cursor.getString(16));
                p.setNM_Razao_Social(cursor.getString(17));
                p.setNM_Fantasia(cursor.getString(18));
                p.setCD_Ramo_Atividade(cursor.getString(19));
                p.setNM_Ramo_Atividade(cursor.getString(20));
                p.setNM_Banco(cursor.getString(21));
                p.setNM_Agencia(cursor.getString(22));
                p.setCD_Conta_Corrente(cursor.getString(23));
                p.setCD_Banco(cursor.getString(24));
                p.setNM_Mae(cursor.getString(25));
                p.setNR_PIS(cursor.getString(26));
                p.setNR_INSS(cursor.getString(27));
                p.setDT_Nascimento(cursor.getString(28));
                p.setDT_Qualificacao(cursor.getString(29));
                p.setDM_Qualificacao(cursor.getString(30));
                p.setNR_Vencto1(cursor.getInt(31));
                p.setNR_Vencto2(cursor.getInt(32));
                p.setNR_Vencto3(cursor.getInt(33));
                p.setNR_Vencto4(cursor.getInt(34));
                p.setDM_Tipo_Faturamento(cursor.getString(35));

                if (p.getDT_Qualificacao() == null)
                    p.setDT_Qualificacao("");
                if (p.getNM_Mae() == null)
                    p.setNM_Mae("");
                if (p.getNR_PIS() == null)
                    p.setNR_PIS("");
                if (p.getNR_INSS() == null)
                    p.setNR_INSS("");
                if (p.getDT_Qualificacao() == null)
                    p.setDT_Qualificacao("");
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

    public static final FornecedorBean getByCD_Fornec(String CD_Fornecedor) throws Exception {
        /*
         * Abre a conex�o com o banco
         */
        Connection conn = null;
        try {
            // Pede uma conex�o ao gerenciador do driver
            // passando como par�metro o NM_Tipo_Documento do DSN
            // o NM_Tipo_Documento de usu�rio e a senha do banco.
            conn = OracleConnection2.getWEB();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        FornecedorBean p = new FornecedorBean();
        try {
            StringBuffer buff = new StringBuffer();
            buff.append("SELECT                                 ");
            buff.append("   Fornecedores.OID_Fornecedor,        ");
            buff.append("	Fornecedores.OID_Pessoa,            ");
            buff.append("	Fornecedores.OID_Ramo_Atividade,    ");
            buff.append("	Fornecedores.NM_Conta,            	");
            buff.append("	Fornecedores.NM_Prazo,      		");
            buff.append("	Fornecedores.NM_Conta_Contabil,     ");
            buff.append("	Fornecedores.NM_Representante,      ");
            buff.append("	Fornecedores.NM_Entrega,            ");
            buff.append("	Fornecedores.DM_Gera_Livro_Fiscal,  ");
            buff.append("	Fornecedores.TX_Observacao1,        ");
            buff.append("	Fornecedores.TX_Observacao2,        ");
            buff.append("	Fornecedores.TX_Observacao3,        ");
            buff.append("	Fornecedores.DM_Impostos,           ");
            buff.append("	Fornecedores.VL_Taxa_Cobranca,      ");
            buff.append("	Fornecedores.NR_Dependentes,        ");
            buff.append("	Pessoas.NR_CNPJ_CPF,                ");
            buff.append("	Pessoas.NM_Razao_Social,            ");
            buff.append("	Pessoas.NM_Fantasia,                ");
            buff.append("	Ramos_Atividades.CD_Ramo_Atividade, ");
            buff.append("	Ramos_Atividades.NM_Ramo_Atividade, ");
            buff.append("	Fornecedores.NM_Banco,              ");
            buff.append("	Fornecedores.NM_Agencia,            ");
            buff.append("	Fornecedores.CD_Conta_Corrente,     ");
            buff.append("	Fornecedores.CD_Banco,           	");
            buff.append("	Fornecedores.NM_MAE,             	");
            buff.append("	Fornecedores.NR_PIS,             	");
            buff.append("	Fornecedores.NR_INSS,            	");
            buff.append("	Fornecedores.DT_NASCIMENTO,       	");
            buff.append("	Fornecedores.DT_Qualificacao,       ");
            buff.append("	Fornecedores.DM_Qualificacao,       ");
            buff.append("	Fornecedores.DT_Vencto1,       ");
            buff.append("	Fornecedores.DT_Vencto2,       ");
            buff.append("	Fornecedores.DT_Vencto3,       ");
            buff.append("	Fornecedores.DT_Vencto4,       ");
            buff.append("	Fornecedores.DM_Tipo_Faturamento    ");
            buff.append(" FROM Fornecedores, Pessoas, Ramos_Atividades ");
            buff.append(" WHERE Fornecedores.OID_Pessoa          = Pessoas.OID_Pessoa                    ");
            buff.append(" AND   Fornecedores.OID_Ramo_Atividade  = Ramos_Atividades.OID_Ramo_Atividade   ");
            buff.append(" AND   Fornecedores.CD_Fornecedor = '");
            buff.append(CD_Fornecedor);
            buff.append("'");
            buff.append(" ORDER BY Pessoas.NM_RAZAO_SOCIAL ");

            Statement stmt = conn.createStatement();
            ResultSet cursor = stmt.executeQuery(buff.toString());

            while (cursor.next()) {
                p.setOID_Fornecedor(cursor.getString(1));
                p.setOID_Pessoa(cursor.getString(2));
                p.setOID_Ramo_Atividade(cursor.getInt(3));
                p.setNM_Conta(cursor.getString(4));
                p.setNM_Prazo(cursor.getString(5));
                p.setNM_Conta_Contabil(cursor.getString(6));
                p.setNM_Representante(cursor.getString(7));
                p.setNM_Entrega(cursor.getString(8));
                p.setDM_Gera_Livro_Fiscal(cursor.getString(9));
                p.setTX_Observacao1(cursor.getString(10));
                p.setTX_Observacao2(cursor.getString(11));
                p.setTX_Observacao3(cursor.getString(12));
                p.setDM_Impostos(cursor.getString(13));
                p.setVL_Taxa_Cobranca(cursor.getDouble(14));
                p.setNR_Dependentes(cursor.getDouble(15));
                p.setNR_CNPJ_CPF(cursor.getString(16));
                p.setNM_Razao_Social(cursor.getString(17));
                p.setNM_Fantasia(cursor.getString(18));
                p.setCD_Ramo_Atividade(cursor.getString(19));
                p.setNM_Ramo_Atividade(cursor.getString(20));
                p.setNM_Banco(cursor.getString(21));
                p.setNM_Agencia(cursor.getString(22));
                p.setCD_Conta_Corrente(cursor.getString(23));
                p.setCD_Banco(cursor.getString(24));
                p.setNM_Mae(cursor.getString(25));
                p.setNR_PIS(cursor.getString(26));
                p.setNR_INSS(cursor.getString(27));
                p.setDT_Nascimento(cursor.getString(28));
                p.setDT_Qualificacao(cursor.getString(29));
                p.setDM_Qualificacao(cursor.getString(30));
                p.setNR_Vencto1(cursor.getInt(31));
                p.setNR_Vencto2(cursor.getInt(32));
                p.setNR_Vencto3(cursor.getInt(33));
                p.setNR_Vencto4(cursor.getInt(34));
                p.setDM_Tipo_Faturamento(cursor.getString(35));

                if (p.getDT_Qualificacao() == null)
                    p.setDT_Qualificacao("");
                if (p.getNM_Mae() == null)
                    p.setNM_Mae("");
                if (p.getNR_PIS() == null)
                    p.setNR_PIS("");
                if (p.getNR_INSS() == null)
                    p.setNR_INSS("");
                if (p.getDT_Qualificacao() == null)
                    p.setDT_Qualificacao("");
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

    public static final List getByNM_Razao_Social(String NM_Razao_Social) throws Exception {
        /*
         * Abre a conex�o com o banco
         */
        Connection conn = null;

        try {
            // Pede uma conex�o ao gerenciador do driver
            // passando como par�metro o NM_Tipo_Documento do DSN
            // o NM_Tipo_Documento de usu�rio e a senha do banco.
            conn = OracleConnection2.getWEB();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }


        List Fornecedor_Lista = new ArrayList();
        try {
            StringBuffer buff = new StringBuffer();
            buff.append("SELECT                                ");
            buff.append("   Fornecedores.OID_Fornecedor,       ");
            buff.append("	Fornecedores.OID_Pessoa,           ");
            buff.append("	Fornecedores.OID_Ramo_Atividade,   ");
            buff.append("	Fornecedores.NM_Conta,             ");
            buff.append("	Fornecedores.NM_Prazo,      	   ");
            buff.append("	Fornecedores.NM_Conta_Contabil,    ");
            buff.append("	Fornecedores.NM_Representante,     ");
            buff.append("	Fornecedores.NM_Entrega,           ");
            buff.append("	Fornecedores.DM_Gera_Livro_Fiscal, ");
            buff.append("	Fornecedores.TX_Observacao1,       ");
            buff.append("	Fornecedores.TX_Observacao2,       ");
            buff.append("	Fornecedores.TX_Observacao3,       ");
            buff.append("	Fornecedores.DM_Impostos,          ");
            buff.append("	Fornecedores.VL_Taxa_Cobranca,     ");
            buff.append("	Fornecedores.NR_Dependentes,       ");
            buff.append("	Pessoas.NR_CNPJ_CPF,               ");
            buff.append("	Pessoas.NM_Razao_Social,           ");
            buff.append("	Pessoas.NM_Fantasia,               ");
            buff.append("	Ramos_Atividades.CD_Ramo_Atividade,");
            buff.append("	Ramos_Atividades.NM_Ramo_Atividade,    ");
            buff.append("	Fornecedores.CD_Fornecedor    ");
            buff.append(" FROM Fornecedores, Pessoas, Ramos_Atividades ");
            buff.append(" WHERE Fornecedores.OID_Pessoa          = Pessoas.OID_Pessoa ");
            buff.append(" AND   Fornecedores.OID_Ramo_Atividade  = Ramos_Atividades.OID_Ramo_Atividade ");
            buff.append(" AND Pessoas.NM_Razao_Social LIKE'");
            buff.append(NM_Razao_Social);
            buff.append("%'");
            buff.append(" ORDER BY Pessoas.NM_RAZAO_SOCIAL ");

            Statement stmt = conn.createStatement();
            ResultSet cursor = stmt.executeQuery(buff.toString());

            while (cursor.next()) {
                FornecedorBean p = new FornecedorBean();
                p.setOID_Fornecedor(cursor.getString(1));
                p.setOID_Pessoa(cursor.getString(2));
                p.setOID_Ramo_Atividade(cursor.getInt(3));
                p.setNM_Conta(cursor.getString(4));
                p.setNM_Prazo(cursor.getString(5));
                p.setNM_Conta_Contabil(cursor.getString(6));
                p.setNM_Representante(cursor.getString(7));
                p.setNM_Entrega(cursor.getString(8));
                p.setDM_Gera_Livro_Fiscal(cursor.getString(9));
                p.setTX_Observacao1(cursor.getString(10));
                p.setTX_Observacao2(cursor.getString(11));
                p.setTX_Observacao3(cursor.getString(12));
                p.setDM_Impostos(cursor.getString(13));
                p.setVL_Taxa_Cobranca(cursor.getDouble(14));
                p.setNR_Dependentes(cursor.getDouble(15));
                p.setNR_CNPJ_CPF(cursor.getString(16));
                p.setNM_Razao_Social(cursor.getString(17));
                p.setNM_Fantasia(cursor.getString(18));

                if (!JavaUtil.doValida(p.getNM_Fantasia()))
                    p.setNM_Fantasia(p.getNM_Razao_Social());

                p.setCD_Ramo_Atividade(cursor.getString(19));
                p.setNM_Ramo_Atividade(cursor.getString(20));
                p.setCD_Fornecedor(cursor.getString(21));
                p.setCD_Fornecedor("---");
                if(cursor.getString(21)!=null && !cursor.getString(21).equals("null") ){
                  p.setCD_Fornecedor(cursor.getString(21));
                }
                
                Fornecedor_Lista.add(p);
            }

            cursor.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return Fornecedor_Lista;
    }

    public static final List getByNM_Fantasia(String NM_Fantasia) throws Exception {
        /*
         * Abre a conex�o com o banco
         */
        Connection conn = null;

        try {
            // Pede uma conex�o ao gerenciador do driver
            // passando como par�metro o NM_Tipo_Documento do DSN
            // o NM_Tipo_Documento de usu�rio e a senha do banco.
            conn = OracleConnection2.getWEB();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }


        List Fornecedor_Lista = new ArrayList();
        try {
            StringBuffer buff = new StringBuffer();
            buff.append("SELECT                                ");
            buff.append("   Fornecedores.OID_Fornecedor,       ");
            buff.append("	Fornecedores.OID_Pessoa,           ");
            buff.append("	Fornecedores.OID_Ramo_Atividade,   ");
            buff.append("	Fornecedores.NM_Conta,             ");
            buff.append("	Fornecedores.NM_Prazo,      	   ");
            buff.append("	Fornecedores.NM_Conta_Contabil,    ");
            buff.append("	Fornecedores.NM_Representante,     ");
            buff.append("	Fornecedores.NM_Entrega,           ");
            buff.append("	Fornecedores.DM_Gera_Livro_Fiscal, ");
            buff.append("	Fornecedores.TX_Observacao1,       ");
            buff.append("	Fornecedores.TX_Observacao2,       ");
            buff.append("	Fornecedores.TX_Observacao3,       ");
            buff.append("	Fornecedores.DM_Impostos,          ");
            buff.append("	Fornecedores.VL_Taxa_Cobranca,     ");
            buff.append("	Fornecedores.NR_Dependentes,       ");
            buff.append("	Pessoas.NR_CNPJ_CPF,               ");
            buff.append("	Pessoas.NM_Razao_Social,           ");
            buff.append("	Pessoas.NM_Fantasia,               ");
            buff.append("	Ramos_Atividades.CD_Ramo_Atividade,");
            buff.append("	Ramos_Atividades.NM_Ramo_Atividade ");
            buff.append(" FROM Fornecedores, Pessoas, Ramos_Atividades ");
            buff.append(" WHERE Fornecedores.OID_Pessoa          = Pessoas.OID_Pessoa ");
            buff.append(" AND   Fornecedores.OID_Ramo_Atividade  = Ramos_Atividades.OID_Ramo_Atividade ");
            buff.append(" AND Pessoas.NM_Fantasia LIKE'%");
            buff.append(NM_Fantasia);
            buff.append("%'");
            buff.append(" ORDER BY Pessoas.NM_Fantasia ");

            Statement stmt = conn.createStatement();
            ResultSet cursor = stmt.executeQuery(buff.toString());

            while (cursor.next()) {
                FornecedorBean p = new FornecedorBean();
                p.setOID_Fornecedor(cursor.getString(1));
                p.setOID_Pessoa(cursor.getString(2));
                p.setOID_Ramo_Atividade(cursor.getInt(3));
                p.setNM_Conta(cursor.getString(4));
                p.setNM_Prazo(cursor.getString(5));
                p.setNM_Conta_Contabil(cursor.getString(6));
                p.setNM_Representante(cursor.getString(7));
                p.setNM_Entrega(cursor.getString(8));
                p.setDM_Gera_Livro_Fiscal(cursor.getString(9));
                p.setTX_Observacao1(cursor.getString(10));
                p.setTX_Observacao2(cursor.getString(11));
                p.setTX_Observacao3(cursor.getString(12));
                p.setDM_Impostos(cursor.getString(13));
                p.setVL_Taxa_Cobranca(cursor.getDouble(14));
                p.setNR_Dependentes(cursor.getDouble(15));
                p.setNR_CNPJ_CPF(cursor.getString(16));
                p.setNM_Razao_Social(cursor.getString(17));
                p.setNM_Fantasia(cursor.getString(18));

                if (!JavaUtil.doValida(p.getNM_Fantasia()))
                    p.setNM_Fantasia(p.getNM_Razao_Social());

                p.setCD_Ramo_Atividade(cursor.getString(19));
                p.setNM_Ramo_Atividade(cursor.getString(20));
                Fornecedor_Lista.add(p);
            }

            cursor.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return Fornecedor_Lista;
    }

    public static final List getByNR_CNPJ_CPF(String NR_CNPJ_CPF) throws Exception {
        /*
         * Abre a conex�o com o banco
         */
        Connection conn = null;
        try {
            // Pede uma conex�o ao gerenciador do driver
            // passando como par�metro o NM_Tipo_Documento do DSN
            // o NM_Tipo_Documento de usu�rio e a senha do banco.
            conn = OracleConnection2.getWEB();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        List Fornecedor_Lista = new ArrayList();
        try {
            StringBuffer buff = new StringBuffer();
            buff.append("SELECT                               ");
            buff.append("   Fornecedores.OID_Fornecedor,      ");
            buff.append("	Fornecedores.OID_Pessoa,          ");
            buff.append("	Fornecedores.OID_Ramo_Atividade,  ");
            buff.append("	Fornecedores.NM_Conta,            ");
            buff.append("	Fornecedores.NM_Prazo,      	  ");
            buff.append("	Fornecedores.NM_Conta_Contabil,   ");
            buff.append("	Fornecedores.NM_Representante,    ");
            buff.append("	Fornecedores.NM_Entrega,          ");
            buff.append("	Fornecedores.DM_Gera_Livro_Fiscal,");
            buff.append("	Fornecedores.TX_Observacao1,      ");
            buff.append("	Fornecedores.TX_Observacao2,      ");
            buff.append("	Fornecedores.TX_Observacao3,      ");
            buff.append("	Fornecedores.DM_Impostos,         ");
            buff.append("	Fornecedores.VL_Taxa_Cobranca,    ");
            buff.append("	Fornecedores.NR_Dependentes,      ");
            buff.append("	Pessoas.NR_CNPJ_CPF,              ");
            buff.append("	Pessoas.NM_Razao_Social,          ");
            buff.append("	Pessoas.NM_Fantasia,              ");
            buff.append("	Ramos_Atividades.CD_Ramo_Atividade,");
            buff.append("	Ramos_Atividades.NM_Ramo_Atividade ");
            buff.append(" FROM Fornecedores, Pessoas, Ramos_Atividades ");
            buff.append(" WHERE Fornecedores.OID_Pessoa          = Pessoas.OID_Pessoa                    ");
            buff.append(" AND   Fornecedores.OID_Ramo_Atividade  = Ramos_Atividades.OID_Ramo_Atividade   ");
            buff.append(" AND 	Pessoas.NR_CNPJ_CPF = '");
            buff.append(NR_CNPJ_CPF);
            buff.append("'");
            buff.append(" ORDER BY Pessoas.NM_RAZAO_SOCIAL ");

            // System.out.println("Fornecedor getByNR_CNPJ_CPF->> "+ buff.toString());


            Statement stmt = conn.createStatement();
            ResultSet cursor = stmt.executeQuery(buff.toString());

            while (cursor.next()) {
                FornecedorBean p = new FornecedorBean();
                p.setOID_Fornecedor(cursor.getString(1));
                p.setOID_Pessoa(cursor.getString(2));
                p.setOID_Ramo_Atividade(cursor.getInt(3));
                p.setNM_Conta(cursor.getString(4));
                p.setNM_Prazo(cursor.getString(5));
                p.setNM_Conta_Contabil(cursor.getString(6));
                p.setNM_Representante(cursor.getString(7));
                p.setNM_Entrega(cursor.getString(8));
                p.setDM_Gera_Livro_Fiscal(cursor.getString(9));
                p.setTX_Observacao1(cursor.getString(10));
                p.setTX_Observacao2(cursor.getString(11));
                p.setTX_Observacao3(cursor.getString(12));
                p.setDM_Impostos(cursor.getString(13));
                p.setVL_Taxa_Cobranca(cursor.getDouble(14));
                p.setNR_Dependentes(cursor.getDouble(15));
                p.setNR_CNPJ_CPF(cursor.getString(16));
                p.setNM_Razao_Social(cursor.getString(17));
                p.setNM_Fantasia(cursor.getString(18));
                if (!JavaUtil.doValida(p.getNM_Fantasia()))
                    p.setNM_Fantasia(p.getNM_Razao_Social());
                p.setCD_Ramo_Atividade(cursor.getString(19));
                p.setNM_Ramo_Atividade(cursor.getString(20));
                Fornecedor_Lista.add(p);
            }
            cursor.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return Fornecedor_Lista;
    }

    public static final List getAll() throws Exception {
        Connection conn = null;
        try {
            // Pede uma conex�o ao gerenciador do driver
            // passando como par�metro o NM_Tipo_Documento do DSN
            // o NM_Tipo_Documento de usu�rio e a senha do banco.
            conn = OracleConnection2.getWEB();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        List Fornecedor_Lista = new ArrayList();
        try {
            StringBuffer buff = new StringBuffer();
            buff.append("SELECT                                   ");
            buff.append("   Fornecedores.OID_Fornecedor,                 ");
            buff.append("	Fornecedores.OID_Pessoa,                  ");
            buff.append("	Fornecedores.OID_Ramo_Atividade,          ");
            buff.append("	Fornecedores.NM_Conta,            ");
            buff.append("	Fornecedores.NM_Prazo,      ");
            buff.append("	Fornecedores.NM_Conta_Contabil,           ");
            buff.append("	Fornecedores.NM_Representante,             ");
            buff.append("	Fornecedores.NM_Entrega,            ");
            buff.append("	Fornecedores.DM_Gera_Livro_Fiscal,            ");
            buff.append("	Fornecedores.TX_Observacao1,             ");
            buff.append("	Fornecedores.TX_Observacao2,       ");
            buff.append("	Fornecedores.TX_Observacao3,         ");
            buff.append("	Fornecedores.DM_Impostos,        ");
            buff.append("	Fornecedores.VL_Taxa_Cobranca,            ");
            buff.append("	Fornecedores.NR_Dependentes,            ");
            buff.append("	Pessoas.NR_CNPJ_CPF,                  ");
            buff.append("	Pessoas.NM_Razao_Social,              ");
            buff.append("	Pessoas.NM_Fantasia,                  ");
            buff.append("	Ramos_Atividades.CD_Ramo_Atividade,    ");
            buff.append("	Ramos_Atividades.NM_Ramo_Atividade,    ");
            buff.append("	Fornecedores.CD_Fornecedor    ");
            buff.append(" FROM Fornecedores, Pessoas, Ramos_Atividades ");
            buff.append(" WHERE Fornecedores.OID_Pessoa          = Pessoas.OID_Pessoa                    ");
            buff.append(" AND   Fornecedores.OID_Ramo_Atividade  = Ramos_Atividades.OID_Ramo_Atividade   ");
            buff.append(" ORDER BY Pessoas.NM_RAZAO_SOCIAL ");

            // System.out.println("Fornecedor getAll->> "+ buff.toString());

            Statement stmt = conn.createStatement();
            ResultSet cursor = stmt.executeQuery(buff.toString());

            while (cursor.next()) {
                FornecedorBean p = new FornecedorBean();
                p.setOID_Fornecedor(cursor.getString(1));
                p.setOID_Pessoa(cursor.getString(2));
                p.setOID_Ramo_Atividade(cursor.getInt(3));
                p.setNM_Conta(cursor.getString(4));
                p.setNM_Prazo(cursor.getString(5));
                p.setNM_Conta_Contabil(cursor.getString(6));
                p.setNM_Representante(cursor.getString(7));
                p.setNM_Entrega(cursor.getString(8));
                p.setDM_Gera_Livro_Fiscal(cursor.getString(9));
                p.setTX_Observacao1(cursor.getString(10));
                p.setTX_Observacao2(cursor.getString(11));
                p.setTX_Observacao3(cursor.getString(12));
                p.setDM_Impostos(cursor.getString(13));
                p.setVL_Taxa_Cobranca(cursor.getDouble(14));
                p.setNR_Dependentes(cursor.getDouble(15));
                p.setNR_CNPJ_CPF(cursor.getString(16));
                p.setNM_Razao_Social(cursor.getString(17));
                p.setNM_Fantasia(cursor.getString(18));
                p.setCD_Ramo_Atividade(cursor.getString(19));
                p.setNM_Ramo_Atividade(cursor.getString(20));
                p.setCD_Fornecedor("---");
                if(cursor.getString(21)!=null && !cursor.getString(21).equals("null") ){
                  p.setCD_Fornecedor(cursor.getString(21));
                }
                
                Fornecedor_Lista.add(p);
            }
            cursor.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return Fornecedor_Lista;
    }


    public static final List getAll(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws Exception {
        Connection conn = null;
        try {
            // Pede uma conex�o ao gerenciador do driver
            // passando como par�metro o NM_Tipo_Documento do DSN
            // o NM_Tipo_Documento de usu�rio e a senha do banco.
            conn = OracleConnection2.getWEB();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        List Fornecedor_Lista = new ArrayList();

        String oid_Ramo_Atividade = req.getParameter ("oid_Ramo_Atividade");
        String oid_Cidade = req.getParameter ("oid_Cidade");
        String oid_Grupo_Economico = req.getParameter ("oid_Grupo_Economico");
        String NM_Razao_Social = req.getParameter ("FT_NM_Razao_Social");
        String CD_Fornecedor = req.getParameter ("FT_CD_Fornecedor");

        try {
            StringBuffer buff = new StringBuffer();
            buff.append("SELECT                                   ");
            buff.append("   Fornecedores.OID_Fornecedor,                 ");
            buff.append("	Fornecedores.OID_Pessoa,                  ");
            buff.append("	Fornecedores.OID_Ramo_Atividade,          ");
            buff.append("	Fornecedores.NM_Conta,            ");
            buff.append("	Fornecedores.NM_Prazo,      ");
            buff.append("	Fornecedores.NM_Conta_Contabil,           ");
            buff.append("	Fornecedores.NM_Representante,             ");
            buff.append("	Fornecedores.NM_Entrega,            ");
            buff.append("	Fornecedores.DM_Gera_Livro_Fiscal,            ");
            buff.append("	Fornecedores.TX_Observacao1,             ");
            buff.append("	Fornecedores.TX_Observacao2,       ");
            buff.append("	Fornecedores.TX_Observacao3,         ");
            buff.append("	Fornecedores.DM_Impostos,        ");
            buff.append("	Fornecedores.VL_Taxa_Cobranca,            ");
            buff.append("	Fornecedores.NR_Dependentes,            ");
            buff.append("	Pessoas.NR_CNPJ_CPF,                  ");
            buff.append("	Pessoas.NM_Razao_Social,              ");
            buff.append("	Pessoas.NM_Fantasia,                  ");
            buff.append("	Ramos_Atividades.CD_Ramo_Atividade,    ");
            buff.append("	Ramos_Atividades.NM_Ramo_Atividade,    ");
            buff.append("	Fornecedores.CD_Fornecedor    ");
            buff.append(" FROM Fornecedores, Pessoas, Ramos_Atividades ");
            buff.append(" WHERE Fornecedores.OID_Pessoa          = Pessoas.OID_Pessoa                    ");
            buff.append(" AND   Fornecedores.OID_Ramo_Atividade  = Ramos_Atividades.OID_Ramo_Atividade   ");

            if (NM_Razao_Social != null && !NM_Razao_Social.equals("null") && !NM_Razao_Social.equals("")) {
              buff.append (" AND Pessoas.NM_Razao_Social LIKE'");
              buff.append (NM_Razao_Social);
              buff.append ("%'");
            }
            if (oid_Cidade != null && !oid_Cidade.equals("null") && !oid_Cidade.equals("")) {
                buff.append( " and Pessoas.oid_Cidade = " + oid_Cidade );
            }
            if (CD_Fornecedor != null && !CD_Fornecedor.equals("null") && !CD_Fornecedor.equals("")) {
                buff.append( " and Fornecedores.CD_Fornecedor = '" + CD_Fornecedor +"'");
            }
            if (oid_Ramo_Atividade != null && !oid_Ramo_Atividade.equals("null") && !oid_Ramo_Atividade.equals("")) {
                buff.append( " and Fornecedores.oid_Ramo_Atividade = " + oid_Ramo_Atividade );
            }
            if (oid_Ramo_Atividade != null && !oid_Ramo_Atividade.equals("null") && !oid_Ramo_Atividade.equals("")) {
                buff.append( " and Fornecedores.oid_Ramo_Atividade = " + oid_Ramo_Atividade );
            }
            if (oid_Grupo_Economico != null && !oid_Grupo_Economico.equals("null") && !oid_Grupo_Economico.equals("")) {
                buff.append( " and Fornecedores.oid_Grupo_Economico = " + oid_Grupo_Economico);
            }

            buff.append(" ORDER BY Pessoas.NM_RAZAO_SOCIAL ");

            // System.out.println("Fornecedor getAll req->> "+ buff.toString());

            Statement stmt = conn.createStatement();
            ResultSet cursor = stmt.executeQuery(buff.toString());

            while (cursor.next()) {
                FornecedorBean p = new FornecedorBean();
                p.setOID_Fornecedor(cursor.getString(1));
                p.setOID_Pessoa(cursor.getString(2));
                p.setOID_Ramo_Atividade(cursor.getInt(3));
                p.setNM_Conta(cursor.getString(4));
                p.setNM_Prazo(cursor.getString(5));
                p.setNM_Conta_Contabil(cursor.getString(6));
                p.setNM_Representante(cursor.getString(7));
                p.setNM_Entrega(cursor.getString(8));
                p.setDM_Gera_Livro_Fiscal(cursor.getString(9));
                p.setTX_Observacao1(cursor.getString(10));
                p.setTX_Observacao2(cursor.getString(11));
                p.setTX_Observacao3(cursor.getString(12));
                p.setDM_Impostos(cursor.getString(13));
                p.setVL_Taxa_Cobranca(cursor.getDouble(14));
                p.setNR_Dependentes(cursor.getDouble(15));
                p.setNR_CNPJ_CPF(cursor.getString(16));
                p.setNM_Razao_Social(cursor.getString(17));
                p.setNM_Fantasia (cursor.getString (17));
                if (cursor.getString(18)!=null && !cursor.getString(18).equals("null")){
                  p.setNM_Fantasia (cursor.getString (18));
                }  
  

                p.setCD_Ramo_Atividade(cursor.getString(19));
                p.setNM_Ramo_Atividade(cursor.getString(20));
                p.setCD_Fornecedor(cursor.getString(21));
                p.setCD_Fornecedor("---");
                if(cursor.getString(21)!=null && !cursor.getString(21).equals("null") ){
                  p.setCD_Fornecedor(cursor.getString(21));
                }
                Fornecedor_Lista.add(p);
            }
            cursor.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return Fornecedor_Lista;
    }

    public void geraRelFornecedores(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) {

        try {
            String oid_Ramo_Atividade = req.getParameter("oid_Ramo_Atividade");
            String oid_Grupo_Economico = req.getParameter("oid_Grupo_Economico");

            String sql = null;

            sql = "Select * from Pessoas, Fornecedores, Cidades, Estados, Regioes_Estados, Ramos_Atividades " + "where Pessoas.OID_Pessoa = Fornecedores.OID_Pessoa "
                    + "and Pessoas.OID_Cidade = Cidades.OID_Cidade " + "and Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado " + "and Regioes_Estados.OID_Estado = Estados.OID_Estado "
                    + "and Fornecedores.OID_Ramo_Atividade = Ramos_Atividades.OID_Ramo_Atividade " + "and Fornecedores.OID_Ramo_Atividade = Ramos_Atividades.OID_Ramo_Atividade ";

            if (oid_Ramo_Atividade != null && !oid_Ramo_Atividade.equals("null") && !oid_Ramo_Atividade.equals("")) {
                sql += " and Fornecedores.oid_Ramo_Atividade = " + oid_Ramo_Atividade;
            }
            if (oid_Grupo_Economico != null && !oid_Grupo_Economico.equals("null") && !oid_Grupo_Economico.equals("")) {
                sql += " and Fornecedores.oid_Grupo_Economico = " + oid_Grupo_Economico;
            }

            sql += " Order by NM_Razao_Social ";

            Connection conn = null;

            try {
                // Pede uma conex�o ao gerenciador do driver
                // passando como par�metro o NM_Tipo_Documento do DSN
                // o NM_Tipo_Documento de usu�rio e a senha do banco.
                conn = OracleConnection2.getWEB();
                conn.setAutoCommit(false);
            } catch (Exception e) {
                e.printStackTrace();
            }

            Statement stmt = conn.createStatement();
            ResultSet cursor = stmt.executeQuery(sql);


        } catch (Exception exc) {
            exc.printStackTrace();
        }

    }

    public void geraRelFornecedores_Qualificados(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) {

        try {

            String oid_Ramo_Atividade = req.getParameter("oid_Ramo_Atividade");
            String oid_Grupo_Economico = req.getParameter("oid_Grupo_Economico");

            String sql = null;

            sql = "Select * from Pessoas, Fornecedores, Cidades, Estados, Regioes_Estados, Ramos_Atividades " + "where Pessoas.OID_Pessoa = Fornecedores.OID_Pessoa "
                    + "and Pessoas.OID_Cidade = Cidades.OID_Cidade " + "and Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado " + "and Regioes_Estados.OID_Estado = Estados.OID_Estado "
                    + "and Fornecedores.OID_Ramo_Atividade = Ramos_Atividades.OID_Ramo_Atividade " + "and Fornecedores.DM_Qualificacao ='S' ";
            if (oid_Ramo_Atividade != null && !oid_Ramo_Atividade.equals("null") && !oid_Ramo_Atividade.equals("")) {
                sql += " and Fornecedores.oid_Ramo_Atividade = " + oid_Ramo_Atividade;
            }
            if (oid_Grupo_Economico != null && !oid_Grupo_Economico.equals("null") && !oid_Grupo_Economico.equals("")) {
                sql += " and Fornecedores.oid_Grupo_Economico = " + oid_Grupo_Economico;
            }

            sql += " Order by Pessoas.NM_Razao_Social ";

            Connection conn = null;

            try {
                // Pede uma conex�o ao gerenciador do driver
                // passando como par�metro o NM_Tipo_Documento do DSN
                // o NM_Tipo_Documento de usu�rio e a senha do banco.
                conn = OracleConnection2.getWEB();
                conn.setAutoCommit(false);
            } catch (Exception e) {
                e.printStackTrace();
            }

            Statement stmt = conn.createStatement();
            ResultSet cursor = stmt.executeQuery(sql);


        } catch (Exception exc) {
            exc.printStackTrace();
        }

    }

    public static final FornecedorBean getByRecord(String NR_CNPJ_CPF) throws Exception {
        /*
         * Abre a conex�o com o banco
         */
        Connection conn = null;

        FornecedorBean p = new FornecedorBean();

        try {
            // Pede uma conex�o ao gerenciador do driver
            // passando como par�metro o NM_Tipo_Documento do DSN
            // o NM_Tipo_Documento de usu�rio e a senha do banco.
            conn = OracleConnection2.getWEB();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        try {
            StringBuffer buff = new StringBuffer();
            buff.append("SELECT                                   ");
            buff.append("   Fornecedores.OID_Fornecedor,                 ");
            buff.append("	Fornecedores.OID_Pessoa,                  ");
            buff.append("	Fornecedores.OID_Ramo_Atividade,          ");
            buff.append("	Fornecedores.NM_Conta,            ");
            buff.append("	Fornecedores.NM_Prazo,      ");
            buff.append("	Fornecedores.NM_Conta_Contabil,           ");
            buff.append("	Fornecedores.NM_Representante,             ");
            buff.append("	Fornecedores.NM_Entrega,            ");
            buff.append("	Fornecedores.DM_Gera_Livro_Fiscal,            ");
            buff.append("	Fornecedores.TX_Observacao1,             ");
            buff.append("	Fornecedores.TX_Observacao2,       ");
            buff.append("	Fornecedores.TX_Observacao3,         ");
            buff.append("	Fornecedores.DM_Impostos,        ");
            buff.append("	Fornecedores.VL_Taxa_Cobranca,            ");
            buff.append("	Fornecedores.NR_Dependentes,            ");
            buff.append("	Pessoas.NR_CNPJ_CPF,                  ");
            buff.append("	Pessoas.NM_Razao_Social,              ");
            buff.append("	Pessoas.NM_Fantasia,                  ");
            buff.append("	Ramos_Atividades.CD_Ramo_Atividade,    ");
            buff.append("	Ramos_Atividades.NM_Ramo_Atividade,    ");
            buff.append("	Fornecedores.DM_Qualificacao     ");
            buff.append(" FROM Fornecedores, Pessoas, Ramos_Atividades ");
            buff.append(" WHERE Fornecedores.OID_Pessoa          = Pessoas.OID_Pessoa                    ");
            buff.append(" AND   Fornecedores.OID_Ramo_Atividade  = Ramos_Atividades.OID_Ramo_Atividade   ");
            buff.append(" AND Pessoas.NR_CNPJ_CPF = '");
            buff.append(NR_CNPJ_CPF);
            buff.append("'");
            buff.append(" ORDER BY Pessoas.NM_RAZAO_SOCIAL ");

            Statement stmt = conn.createStatement();
            ResultSet cursor = stmt.executeQuery(buff.toString());

            while (cursor.next()) {
                p.setOID_Fornecedor(cursor.getString(1));
                p.setOID_Pessoa(cursor.getString(2));
                p.setOID_Ramo_Atividade(cursor.getInt(3));
                p.setNM_Conta(cursor.getString(4));
                p.setNM_Prazo(cursor.getString(5));
                p.setNM_Conta_Contabil(cursor.getString(6));
                p.setNM_Representante(cursor.getString(7));
                p.setNM_Entrega(cursor.getString(8));
                p.setDM_Gera_Livro_Fiscal(cursor.getString(9));
                p.setTX_Observacao1(cursor.getString(10));
                p.setTX_Observacao2(cursor.getString(11));
                p.setTX_Observacao3(cursor.getString(12));
                p.setDM_Impostos(cursor.getString(13));
                p.setVL_Taxa_Cobranca(cursor.getDouble(14));
                p.setNR_Dependentes(cursor.getDouble(15));
                p.setNR_CNPJ_CPF(cursor.getString(16));
                p.setNM_Razao_Social(cursor.getString(17));
                p.setNM_Fantasia(cursor.getString(18));
                p.setCD_Ramo_Atividade(cursor.getString(19));
                p.setNM_Ramo_Atividade(cursor.getString(20));
                p.setDM_Qualificacao(cursor.getString(21));
                
            }
            cursor.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            conn.close();
            e.printStackTrace();
            throw e;
        }
        return p;
    }

    public static void main(String args[]) throws Exception {
        FornecedorBean pp = new FornecedorBean();
        pp.setOID_Fornecedor("98765821568");
        pp.setOID_Pessoa("98765821568");
        pp.update();

        FornecedorBean p = getByOID_Fornecedor("98765821568");

    }


}
