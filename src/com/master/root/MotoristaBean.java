package com.master.root;

import java.sql.*;
import java.util.*;
import javax.servlet.http.*;

import auth.*;
import com.master.rl.*;
import com.master.util.*;
import com.master.util.ed.*;

public class MotoristaBean {
  private String CD_Motorista;
  private String DT_Nascimento;
  private String NR_Registro;
  private String DM_Tipo_Funcao;
  private String NM_Pai;
  private String NM_Mae;
  private int NR_Dependentes;

  private String NM_Apelido;
  private String NR_Registro_Geral;
  private String NM_Orgao_Emissor;
  private String NR_CNH;
  private String DT_Vencimento_CNH;
  private String NM_Categoria;
  private String DM_Carga_Perigosa;
  private String DM_Situacao;
  private String TX_Observacao;
  private String NR_PIS;
  private String NR_INSS;
  private String NR_Telefone;
  private String NR_Celular;
  private String TX_Referencia;
  private String Usuario_Stamp;
  private String Dt_Stamp;
  private String Dm_Stamp;
  private String OID_Pessoa_Vinculo;
  private String NM_Razao_Social_Vinculo;
  private String NR_CNPJ_CPF_Vinculo;
  private String NM_Razao_Social;
  private String OID_Pessoa;
  private String OID_Motorista;
  private String DT_Vencimento_Aval_Med;

  private double PE_Comissao;
  private double PE_Comissao_Media1;
  private double PE_Comissao_Media2;
  private double NR_Media1;
  private double NR_Media2;
  private double PE_Gratificacao_0;
  private double PE_Gratificacao_1;
  private double PE_Gratificacao_2;
  private double PE_Gratificacao_3;
  private String DM_Pagamento_Diaria;
  private double PE_Comissao_Terceiro;
  private String DT_Liberacao_Cadastro;
  private double VL_INSS;
  private double VL_Plano_Saude;
  private double VL_Limite_Credito;
  private double VL_Base_Adto;

  private String NR_Liberacao_Seguradora;
  private String DT_Vencimento_Liberacao_Seguradora;
  private String DM_Qualificacao;
  private String DT_Liberacao_Viagem;

  private String NR_Cartao_CFE;


  public MotoristaBean () {
    OID_Pessoa = "";
    NM_Apelido = " ";
    NM_Mae = " ";
    NM_Pai = " ";
    NR_Registro = " ";
    DM_Tipo_Funcao = " ";
    NR_Registro_Geral = " ";
    NM_Orgao_Emissor = " ";
    NR_CNH = " ";
    NM_Categoria = " ";
    TX_Observacao = " ";
    NR_PIS = " ";
    NR_INSS = " ";
    NR_Telefone = " ";
    NR_Celular = " ";
    TX_Referencia = " ";
    try {
      jbInit ();
    }
    catch (Exception ex) {
      ex.printStackTrace ();
    }
  }

  public String getNM_Pai () {
    return NM_Pai;
  }

  public void setNM_Pai (String NM_Pai) {
    this.NM_Pai = NM_Pai;
  }

  public String getNM_Mae () {
    return NM_Mae;
  }

  public void setNM_Mae (String NM_Mae) {
    this.NM_Mae = NM_Mae;
  }

  public String getNM_Apelido () {
    return NM_Apelido;
  }

  public void setNM_Apelido (String NM_Apelido) {
    this.NM_Apelido = NM_Apelido;
  }

  public String getNR_Registro () {
    return NR_Registro;
  }

  public void setNR_Registro (String NR_Registro) {
    this.NR_Registro = NR_Registro;
  }

  public String getDM_Tipo_Funcao () {
    return DM_Tipo_Funcao;
  }

  public void setDM_Tipo_Funcao (String DM_Tipo_Funcao) {
    this.DM_Tipo_Funcao = DM_Tipo_Funcao;
  }

  public String getDT_Nascimento () {
    FormataDataBean DataFormatada = new FormataDataBean ();
    DataFormatada.setDT_FormataData (DT_Nascimento);
    DT_Nascimento = DataFormatada.getDT_FormataData ();

    return DT_Nascimento;
  }

  public void setDT_Nascimento (String DT_Nascimento) {
    this.DT_Nascimento = DT_Nascimento;
  }

  public String getNR_Registro_Geral () {
    return NR_Registro_Geral;
  }

  public void setNR_Registro_Geral (String NR_Registro_Geral) {
    this.NR_Registro_Geral = NR_Registro_Geral;
  }

  public String getNM_Orgao_Emissor () {
    return NM_Orgao_Emissor;
  }

  public void setNM_Orgao_Emissor (String NM_Orgao_Emissor) {
    this.NM_Orgao_Emissor = NM_Orgao_Emissor;
  }

  public String getOID_Pessoa_Vinculo () {
    return OID_Pessoa_Vinculo;
  }

  public void setOID_Pessoa_Vinculo (String OID_Pessoa_Vinculo) {
    this.OID_Pessoa_Vinculo = OID_Pessoa_Vinculo;
  }

  public String getOID_Pessoa () {
    return OID_Pessoa;
  }

  public void setOID_Pessoa (String OID_Pessoa) {
    this.OID_Pessoa = OID_Pessoa;
  }

  public String getOID_Motorista () {
    return OID_Motorista;
  }

  public void setOID_Motorista (String OID_Motorista) {
    this.OID_Motorista = OID_Motorista;
  }

  public String getNR_CNH () {
    return NR_CNH;
  }

  public void setNR_CNH (String NR_CNH) {
    this.NR_CNH = NR_CNH;
  }

  public String getDT_Vencimento_CNH () {
    FormataDataBean DataFormatada = new FormataDataBean ();
    DataFormatada.setDT_FormataData (DT_Vencimento_CNH);
    DT_Vencimento_CNH = DataFormatada.getDT_FormataData ();

    return DT_Vencimento_CNH;
  }

  public void setDT_Vencimento_CNH (String DT_Vencimento_CNH) {
    this.DT_Vencimento_CNH = DT_Vencimento_CNH;
  }

  public String getDT_Vencimento_Aval_Med () {
    FormataDataBean DataFormatada = new FormataDataBean ();
    DataFormatada.setDT_FormataData (DT_Vencimento_Aval_Med);
    DT_Vencimento_Aval_Med = DataFormatada.getDT_FormataData ();

    return DT_Vencimento_Aval_Med;
  }

  public void setDT_Vencimento_Aval_Med (String DT_Vencimento_Aval_Med) {
    this.DT_Vencimento_Aval_Med = DT_Vencimento_Aval_Med;
  }

  public String getNM_Categoria () {
    return NM_Categoria;
  }

  public void setNM_Categoria (String NM_Categoria) {
    this.NM_Categoria = NM_Categoria;
  }

  public String getDM_Carga_Perigosa () {
    return DM_Carga_Perigosa;
  }

  public void setDM_Carga_Perigosa (String DM_Carga_Perigosa) {
    this.DM_Carga_Perigosa = DM_Carga_Perigosa;
  }

  public String getDM_Situacao () {
    return DM_Situacao;
  }

  public void setDM_Situacao (String DM_Situacao) {
    this.DM_Situacao = DM_Situacao;
  }

  public String getTX_Referencia () {
    return TX_Referencia;
  }

  public void setTX_Referencia (String TX_Referencia) {
    this.TX_Referencia = TX_Referencia;
  }

  public String getTX_Observacao () {
    return TX_Observacao;
  }

  public void setTX_Observacao (String TX_Observacao) {
    this.TX_Observacao = TX_Observacao;
  }

  public String getNR_PIS () {
    return NR_PIS;
  }

  public void setNR_PIS (String NR_PIS) {
    this.NR_PIS = NR_PIS;
  }

  public String getNR_INSS () {
    return NR_INSS;
  }

  public void setNR_INSS (String NR_INSS) {
    this.NR_INSS = NR_INSS;
  }

  public String getNR_Celular () {
    return NR_Celular;
  }

  public void setNR_Celular (String NR_Celular) {
    this.NR_Celular = NR_Celular;
  }

  public String getNR_Telefone () {
    return NR_Telefone;
  }

  public void setNR_Telefone (String NR_Telefone) {
    this.NR_Telefone = NR_Telefone;
  }

  public String getNM_Razao_Social () {
    return NM_Razao_Social;
  }

  public void setNM_Razao_Social (String NM_Razao_Social) {
    this.NM_Razao_Social = NM_Razao_Social;
  }

  public String getNM_Razao_Social_Vinculo () {
    return NM_Razao_Social_Vinculo;
  }

  public void setNM_Razao_Social_Vinculo (String NM_Razao_Social_Vinculo) {
    this.NM_Razao_Social_Vinculo = NM_Razao_Social_Vinculo;
  }

  public String getNR_CNPJ_CPF_Vinculo () {
    return NR_CNPJ_CPF_Vinculo;
  }

  public void setNR_CNPJ_CPF_Vinculo (String NR_CNPJ_CPF_Vinculo) {
    this.NR_CNPJ_CPF_Vinculo = NR_CNPJ_CPF_Vinculo;
  }

  public void setPE_Comissao (double PE_Comissao) {
    this.PE_Comissao = PE_Comissao;
  }

  public double getPE_Comissao () {
    return PE_Comissao;
  }

  public void setPE_Gratificacao_0 (double PE_Gratificacao_0) {
    this.PE_Gratificacao_0 = PE_Gratificacao_0;
  }

  public double getPE_Gratificacao_0 () {
    return PE_Gratificacao_0;
  }

  public void setPE_Gratificacao_1 (double PE_Gratificacao_1) {
    this.PE_Gratificacao_1 = PE_Gratificacao_1;
  }

  public double getPE_Gratificacao_1 () {
    return PE_Gratificacao_1;
  }

  public void setPE_Gratificacao_2 (double PE_Gratificacao_2) {
    this.PE_Gratificacao_2 = PE_Gratificacao_2;
  }

  public double getPE_Gratificacao_2 () {
    return PE_Gratificacao_2;
  }

  public void setPE_Gratificacao_3 (double PE_Gratificacao_3) {
    this.PE_Gratificacao_3 = PE_Gratificacao_3;
  }

  public double getPE_Gratificacao_3 () {
    return PE_Gratificacao_3;
  }

  /*
   *---------------- Bloco Padrão para Todas Classes ------------------
   */
  public String getUsuario_Stamp () {
    return Usuario_Stamp;
  }

  public void setUsuario_Stamp (String Usuario_Stamp) {
    this.Usuario_Stamp = Usuario_Stamp;
  }

  public String getDt_Stamp () {
    return Dt_Stamp;
  }

  public void setDt_Stamp (String Dt_Stamp) {
    this.Dt_Stamp = Dt_Stamp;
  }

  public String getDm_Stamp () {
    return Dm_Stamp;
  }

  public void setDm_Stamp (String Dm_Stamp) {
    this.Dm_Stamp = Dm_Stamp;
  }

  public void insert () throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Orgao_Emissor do DSN
      // o NM_Orgao_Emissor de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    /*
     * Gera um novo código (OID)
     */

    /*
     * Define o insert.
     */
    StringBuffer buff = new StringBuffer ();
    buff.append ("INSERT INTO Motoristas ");
    buff.append (" (OID_Motorista,            ");
    buff.append ("  OID_Pessoa,            ");
    buff.append ("  OID_Pessoa_Vinculo,       ");
    buff.append ("  NM_Apelido,               ");
    buff.append ("  NR_Registro_Geral,             ");
    buff.append ("  NM_Orgao_Emissor,     ");
    buff.append ("  NR_CNH,     ");
    buff.append ("  DT_Vencimento_CNH,     ");
    buff.append ("  NM_Categoria,     ");
    buff.append ("  DM_Carga_Perigosa,     ");
    buff.append ("  DM_Situacao,     ");
    buff.append ("  TX_Observacao,     ");
    buff.append ("  NR_PIS,     ");
    buff.append ("  NR_INSS,     ");
    buff.append ("  NR_Telefone,     ");
    buff.append ("  NR_Celular,     ");
    buff.append ("  TX_Referencia,     ");
    buff.append ("  Dt_Stamp,               ");
    buff.append ("  Usuario_Stamp,          ");
    buff.append ("  Dm_Stamp,               ");
    buff.append ("  NM_Pai,               ");
    buff.append ("  NM_Mae,               ");
    buff.append ("  NR_Registro,             ");
    buff.append ("  DT_Nascimento,     ");
    buff.append ("  PE_Comissao,     ");
    buff.append ("  PE_Gratificacao_0,     ");
    buff.append ("  PE_Gratificacao_1,     ");
    buff.append ("  PE_Gratificacao_2,     ");
    buff.append ("  PE_Gratificacao_3,     ");
    buff.append ("  DM_Tipo_Funcao, ");
    buff.append ("  DM_Pagamento_Diaria, ");
    buff.append ("  DT_Vencimento_Aval_Med,  ");
    buff.append ("  VL_INSS,     ");
    buff.append ("  VL_Limite_Credito,     ");
    buff.append ("  VL_Base_Adto,     ");
    buff.append ("  VL_Plano_Saude,     ");
    buff.append ("  DM_Qualificacao,     ");
    buff.append ("  NR_Dependentes,     ");
    buff.append ("  NR_Cartao_CFE,     ");
    buff.append ("  PE_Comissao_Terceiro    )  ");
    buff.append (
        "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt = conn.prepareStatement (buff.toString ());
      pstmt.setString (1 , getOID_Motorista ());
      pstmt.setString (2 , getOID_Pessoa ());
      pstmt.setString (3 , getOID_Pessoa_Vinculo ());
      pstmt.setString (4 , JavaUtil.trunc (getNM_Apelido () , 28));
      pstmt.setString (5 , getNR_Registro_Geral ());
      pstmt.setString (6 , getNM_Orgao_Emissor ());
      pstmt.setString (7 , getNR_CNH ());
      pstmt.setString (8 , this.DT_Vencimento_CNH);
      pstmt.setString (9 , getNM_Categoria ());
      pstmt.setString (10 , getDM_Carga_Perigosa ());
      pstmt.setString (11 , getDM_Situacao ());
      pstmt.setString (12 , getTX_Observacao ());
      pstmt.setString (13 , getNR_PIS ());
      pstmt.setString (14 , getNR_INSS ());
      pstmt.setString (15 , getNR_Telefone ());
      pstmt.setString (16 , getNR_Celular ());
      pstmt.setString (17 , getTX_Referencia ());
      pstmt.setString (18 , getDt_Stamp ());
      pstmt.setString (19 , getUsuario_Stamp ());
      pstmt.setString (20 , getDm_Stamp ());
      pstmt.setString (21 , getNM_Pai ());
      pstmt.setString (22 , getNM_Mae ());
      pstmt.setString (23 , getNR_Registro ());
      pstmt.setString (24 , this.DT_Nascimento);
      pstmt.setDouble (25 , getPE_Comissao ());

      pstmt.setDouble (26 , getPE_Gratificacao_0 ());
      pstmt.setDouble (27 , getPE_Gratificacao_1 ());
      pstmt.setDouble (28 , getPE_Gratificacao_2 ());
      pstmt.setDouble (29 , getPE_Gratificacao_3 ());

      pstmt.setString (30 , getDM_Tipo_Funcao ());
      pstmt.setString (31 , getDM_Pagamento_Diaria ());
      pstmt.setString (32 , this.DT_Vencimento_Aval_Med);
      pstmt.setDouble (33 , getVL_INSS ());
      pstmt.setDouble (34 , getVL_Limite_Credito ());
      pstmt.setDouble (35 , getVL_Base_Adto ());
      pstmt.setDouble (36 , getVL_Plano_Saude ());
      pstmt.setString (37 , getDM_Qualificacao ());
      pstmt.setDouble (38 , getNR_Dependentes ());
      pstmt.setString(39 , getNR_Cartao_CFE());
      pstmt.setDouble (40 , getPE_Comissao_Terceiro ());

      pstmt.executeUpdate ();
    }
    catch (Exception e) {
      conn.rollback ();
      e.printStackTrace ();
      throw e;
    }
    /*
     * Faz o commit e fecha a conexão.
     */
    try {
      conn.commit ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
  }

  public void update () throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Orgao_Emissor do DSN
      // o NM_Orgao_Emissor de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    /*
     * Define o update.
     */
    StringBuffer buff = new StringBuffer ();
    buff.append ("UPDATE Motoristas SET ");
    buff.append ("  OID_Pessoa_Vinculo=?,       ");
    buff.append ("  NM_Apelido=?,               ");
    buff.append ("  NR_Registro_Geral=?,             ");
    buff.append ("  NM_Orgao_Emissor=?,     ");
    buff.append ("  NR_CNH=?,     ");
    buff.append ("  DT_Vencimento_CNH=?,     ");
    buff.append ("  NM_Categoria=?,     ");
    buff.append ("  DM_Carga_Perigosa=?,     ");
    buff.append ("  DM_Situacao=?,     ");
    buff.append ("  TX_Observacao=?,     ");
    buff.append ("  NR_PIS=?,     ");
    buff.append ("  NR_INSS=?,     ");
    buff.append ("  NR_Telefone=?,     ");
    buff.append ("  NR_Celular=?,     ");
    buff.append ("  TX_Referencia=?,     ");
    buff.append ("  Dt_Stamp=?,               ");
    buff.append ("  Usuario_Stamp=?,          ");
    buff.append ("  Dm_Stamp=?,                ");
    buff.append ("  NM_Pai=?,               ");
    buff.append ("  NM_Mae=?,               ");
    buff.append ("  NR_Registro=?,             ");
    buff.append ("  DT_Nascimento=?,     ");
    buff.append ("  PE_Comissao=?,     ");
    buff.append ("  PE_Gratificacao_0=?,     ");
    buff.append ("  PE_Gratificacao_1=?,     ");
    buff.append ("  PE_Gratificacao_2=?,     ");
    buff.append ("  PE_Gratificacao_3=?,     ");

    buff.append ("  DM_Tipo_Funcao=?,             ");
    buff.append ("  DM_Pagamento_Diaria=?,             ");
    buff.append ("  DT_Vencimento_Aval_Med=?,             ");
    buff.append ("  VL_INSS=?,      ");
    buff.append ("  VL_Plano_Saude=?,      ");
    buff.append ("  DM_Qualificacao=?,     ");
    buff.append ("  PE_Comissao_Terceiro=?,      ");
    buff.append ("  NR_Dependentes=?,      ");
    buff.append ("  PE_Comissao_Media1=?,      ");
    buff.append ("  PE_Comissao_Media2=?,      ");
    buff.append ("  NR_Media1=?,      ");
    buff.append ("  NR_Media2=?,      ");
    buff.append ("  CD_Motorista=?,      ");
    buff.append ("  NR_Cartao_CFE=?,      ");
    buff.append ("  VL_Base_Adto=?      ");
    buff.append (" WHERE OID_Motorista=?");

    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt = conn.prepareStatement (buff.toString ());

      pstmt.setString (1 , getOID_Pessoa_Vinculo ());
      pstmt.setString (2 , JavaUtil.trunc (getNM_Apelido () , 28));
      pstmt.setString (3 , getNR_Registro_Geral ());
      pstmt.setString (4 , getNM_Orgao_Emissor ());
      pstmt.setString (5 , getNR_CNH ());
      pstmt.setString (6 , this.DT_Vencimento_CNH);
      pstmt.setString (7 , getNM_Categoria ());
      pstmt.setString (8 , getDM_Carga_Perigosa ());
      pstmt.setString (9 , getDM_Situacao ());
      pstmt.setString (10 , getTX_Observacao ());
      pstmt.setString (11 , getNR_PIS ());
      pstmt.setString (12 , getNR_INSS ());
      pstmt.setString (13 , getNR_Telefone ());
      pstmt.setString (14 , getNR_Celular ());
      pstmt.setString (15 , getTX_Referencia ());
      pstmt.setString (16 , getDt_Stamp ());
      pstmt.setString (17 , getUsuario_Stamp ());
      pstmt.setString (18 , getDm_Stamp ());
      pstmt.setString (19 , getNM_Pai ());
      pstmt.setString (20 , getNM_Mae ());
      pstmt.setString (21 , getNR_Registro ());
      pstmt.setString (22 , this.DT_Nascimento);
      pstmt.setDouble (23 , getPE_Comissao ());

      pstmt.setDouble (24 , getPE_Gratificacao_0 ());
      pstmt.setDouble (25 , getPE_Gratificacao_1 ());
      pstmt.setDouble (26 , getPE_Gratificacao_2 ());
      pstmt.setDouble (27 , getPE_Gratificacao_3 ());

      pstmt.setString (28 , getDM_Tipo_Funcao ());
      pstmt.setString (29 , getDM_Pagamento_Diaria ());
      pstmt.setString (30 , this.DT_Vencimento_Aval_Med);

      pstmt.setDouble (31 , getVL_INSS ());
      pstmt.setDouble (32 , getVL_Plano_Saude ());
      pstmt.setString (33 , getDM_Qualificacao ());
      pstmt.setDouble (34 , getPE_Comissao_Terceiro ());
      pstmt.setDouble (35 , getNR_Dependentes ());

      pstmt.setDouble (36 , getPE_Comissao_Media1 ());
      pstmt.setDouble (37 , getPE_Comissao_Media2 ());
      pstmt.setDouble (38 , getNR_Media1 ());
      pstmt.setDouble (39 , getNR_Media2 ());

      pstmt.setString (40 , getCD_Motorista ());
      pstmt.setString (41 , getNR_Cartao_CFE());
      pstmt.setDouble (42 , getVL_Base_Adto ());
      pstmt.setString (43 , getOID_Motorista ());


      // //  (pstmt.toString ());

      pstmt.executeUpdate ();
    }
    catch (Exception e) {
      conn.rollback ();
      e.printStackTrace ();
      throw e;
    }
    /*
     * Faz o commit e fecha a conexão.
     */
    try {
      conn.commit ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
  }

  public void updateLimiteCredito () throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Orgao_Emissor do DSN
      // o NM_Orgao_Emissor de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    /*
     * Define o update.
     */
    StringBuffer buff = new StringBuffer ();
    buff.append ("UPDATE Motoristas SET ");
    buff.append ("  VL_Limite_Credito=?      ");
    buff.append (" WHERE OID_Motorista=?");

    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt = conn.prepareStatement (buff.toString ());

      pstmt.setDouble (1 , getVL_Limite_Credito ());
      pstmt.setString (2 , getOID_Motorista ());

      // //  (pstmt.toString ());

      pstmt.executeUpdate ();
    }
    catch (Exception e) {
      conn.rollback ();
      e.printStackTrace ();
      throw e;
    }
    /*
     * Faz o commit e fecha a conexão.
     */
    try {
      conn.commit ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
  }



  public void confirmaCadastro () throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Orgao_Emissor do DSN
      // o NM_Orgao_Emissor de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    /*
     * Define o update.
     */
    StringBuffer buff = new StringBuffer ();
    buff.append ("UPDATE Motoristas SET ");
    buff.append ("  DT_Liberacao_Cadastro=?       ");
    buff.append (" WHERE OID_Motorista=?");

    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt = conn.prepareStatement (buff.toString ());

      pstmt.setString (1 , Data.getDataDMY ());
      pstmt.setString (2 , getOID_Motorista ());
      // //  (pstmt.toString ());

      pstmt.executeUpdate ();
    }
    catch (Exception e) {
      conn.rollback ();
      e.printStackTrace ();
      throw e;
    }
    /*
     * Faz o commit e fecha a conexão.
     */
    try {
      conn.commit ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
  }

  public void libera_Viagem () throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Orgao_Emissor do DSN
      // o NM_Orgao_Emissor de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    /*
     * Define o update.
     */
    StringBuffer buff = new StringBuffer ();
    buff.append ("UPDATE Motoristas SET ");
    buff.append ("  DT_Liberacao_Viagem=?       ");
    buff.append (" WHERE OID_Motorista=?");

    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt = conn.prepareStatement (buff.toString ());

      pstmt.setString (1 , Data.getDataDMY ());
      pstmt.setString (2 , getOID_Motorista ());
      // //  (pstmt.toString ());

      pstmt.executeUpdate ();
    }
    catch (Exception e) {
      conn.rollback ();
      e.printStackTrace ();
      throw e;
    }
    /*
     * Faz o commit e fecha a conexão.
     */
    try {
      conn.commit ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
  }

  public void delete () throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Orgao_Emissor do DSN
      // o NM_Orgao_Emissor de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    /*
     * Define o DELETE.
     */
    StringBuffer buff = new StringBuffer ();
    buff.append ("DELETE FROM Motoristas ");
    buff.append ("WHERE OID_Motorista=?");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setString (1 , getOID_Motorista ());
      pstmt.executeUpdate ();
    }
    catch (Exception e) {
      conn.rollback ();
      e.printStackTrace ();
      throw e;
    }

    /*
     * Faz o commit e fecha a conexão.
     */
    try {
      conn.commit ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
  }

  public static final MotoristaBean getByOID_Pessoa (String OID_Pessoa) throws
      Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Orgao_Emissor do DSN
      // o NM_Orgao_Emissor de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    MotoristaBean p = new MotoristaBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT                             ");
      buff.append (" Motoristas.OID_Motorista,             ");
      buff.append (" Motoristas.OID_Pessoa,             ");
      buff.append (" Motoristas.OID_Pessoa_Vinculo,               ");
      buff.append (" Motoristas.NM_Apelido,                ");
      buff.append (" Motoristas.NR_Registro_Geral,              ");
      buff.append (" Motoristas.NM_Orgao_Emissor,      ");
      buff.append (" Motoristas.NR_CNH,      ");
      buff.append (" Motoristas.DT_Vencimento_CNH,      ");
      buff.append (" Motoristas.NM_Categoria,      ");
      buff.append (" Motoristas.DM_Carga_Perigosa,      ");
      buff.append (" Motoristas.DM_Situacao,      ");
      buff.append (" Motoristas.TX_Observacao,      ");
      buff.append (" Motoristas.NR_PIS,      ");
      buff.append (" Motoristas.NR_INSS,      ");
      buff.append (" Motoristas.NR_Telefone,      ");
      buff.append (" Motoristas.NR_Celular,      ");
      buff.append (" Motoristas.TX_Referencia,      ");
      buff.append (" Pessoas.NM_Razao_Social,              ");
      buff.append (" Pessoa_Vinculo.NM_Razao_Social,    ");
      buff.append (" Pessoa_Vinculo.NR_CNPJ_CPF,    ");
      buff.append (" Motoristas.NM_Pai,                ");
      buff.append (" Motoristas.NM_Mae,                ");
      buff.append (" Motoristas.NR_Registro,              ");
      buff.append (" Motoristas.DT_Nascimento,      ");
      buff.append (" Motoristas.DM_Tipo_Funcao,              ");
      buff.append (" Motoristas.DT_Vencimento_Aval_Med,              ");
      buff.append (" Motoristas.PE_Comissao_Terceiro,       ");
      buff.append (" Motoristas.NR_Dependentes,       ");
      buff.append (" Motoristas.CD_Motorista,       ");
      buff.append (" Motoristas.DT_Liberacao_Viagem       ");
      buff.append (" FROM ");
      buff.append (" Motoristas,                    ");
      buff.append (" Pessoas,                     ");
      buff.append (" Pessoas Pessoa_Vinculo      ");
      buff.append (
          " WHERE Motoristas.OID_Pessoa           = Pessoas.OID_Pessoa             ");
      buff.append (
          " AND   Motoristas.OID_Pessoa_Vinculo   = Pessoa_Vinculo.OID_Pessoa   ");
      buff.append (" AND   Motoristas.OID_Pessoa = '");
      buff.append (OID_Pessoa);
      buff.append ("'");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID_Motorista (cursor.getString (1));
        p.setOID_Pessoa (cursor.getString (2));
        p.setOID_Pessoa_Vinculo (cursor.getString (3));
        p.setNM_Apelido (cursor.getString (4));
        p.setNR_Registro_Geral (cursor.getString (5));
        p.setNM_Orgao_Emissor (cursor.getString (6));
        p.setNR_CNH (cursor.getString (7));
        p.setDT_Vencimento_CNH (cursor.getString (8));
        p.setNM_Categoria (cursor.getString (9));
        p.setDM_Carga_Perigosa (cursor.getString (10));
        p.setDM_Situacao (cursor.getString (11));
        p.setTX_Observacao (cursor.getString (12));
        p.setNR_PIS (cursor.getString (13));
        p.setNR_INSS (cursor.getString (14));
        p.setNR_Telefone (cursor.getString (15));
        p.setNR_Celular (cursor.getString (16));
        p.setTX_Referencia (cursor.getString (17));
        p.setNM_Razao_Social (cursor.getString (18));
        p.setNM_Razao_Social_Vinculo (cursor.getString (19));
        p.setNR_CNPJ_CPF_Vinculo (cursor.getString (20));
        p.setNM_Pai (cursor.getString (21));
        p.setNM_Mae (cursor.getString (22));
        p.setNR_Registro (cursor.getString (23));
        p.setDT_Nascimento (cursor.getString (24));
        p.setDM_Tipo_Funcao (cursor.getString (25));
        p.setDT_Vencimento_Aval_Med (cursor.getString (26));
        p.setPE_Comissao_Terceiro (cursor.getDouble (27));
        p.setNR_Dependentes (cursor.getInt (28));

        p.setCD_Motorista(cursor.getString (29));
        p.setDT_Liberacao_Viagem(cursor.getString (30));

        // // ("Liberacao Viagem=" + cursor.getString (28));

        Seguro_MotoristaBean sm = Seguro_MotoristaBean.getByOID_Motorista(p.getOID_Motorista());
        p.setNR_Liberacao_Seguradora(sm.getNR_Liberacao());
        if(Parametro_FixoED.getInstancia().getDM_Exige_Validade_Lib_Seguradora_Motorista().equals("S")){
        	p.setDT_Vencimento_Liberacao_Seguradora(sm.getDT_Validade());
        	// // ("DATA LIB >"+p.getDT_Vencimento_Liberacao_Seguradora());
        	if(!JavaUtil.doValida(p.getDT_Vencimento_Liberacao_Seguradora())) p.setDT_Vencimento_Liberacao_Seguradora("00/00/0000");
        } else {
        	p.setDT_Vencimento_Liberacao_Seguradora(Data.getDataDMY());
        }

      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return p;
  }

  public static final MotoristaBean getByOID_Motorista (String OID_Motorista) throws
      Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Orgao_Emissor do DSN
      // o NM_Orgao_Emissor de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    MotoristaBean p = new MotoristaBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT                             ");
      buff.append (" Motoristas.OID_Motorista,             ");
      buff.append (" Motoristas.OID_Pessoa,             ");
      buff.append (" Motoristas.OID_Pessoa_Vinculo,               ");
      buff.append (" Motoristas.NM_Apelido,                ");
      buff.append (" Motoristas.NR_Registro_Geral,              ");
      buff.append (" Motoristas.NM_Orgao_Emissor,      ");
      buff.append (" Motoristas.NR_CNH,      ");
      buff.append (" Motoristas.DT_Vencimento_CNH,      ");
      buff.append (" Motoristas.NM_Categoria,      ");
      buff.append (" Motoristas.DM_Carga_Perigosa,      ");
      buff.append (" Motoristas.DM_Situacao,      ");
      buff.append (" Motoristas.TX_Observacao,      ");
      buff.append (" Motoristas.NR_PIS,      ");
      buff.append (" Motoristas.NR_INSS,      ");
      buff.append (" Motoristas.NR_Telefone,      ");
      buff.append (" Motoristas.NR_Celular,      ");
      buff.append (" Motoristas.TX_Referencia,      ");
      buff.append (" Pessoas.NM_Razao_Social,              ");
      buff.append (" Pessoa_Vinculo.NM_Razao_Social,    ");
      buff.append (" Pessoa_Vinculo.NR_CNPJ_CPF,    ");
      buff.append (" Motoristas.NM_Pai,                ");
      buff.append (" Motoristas.NM_Mae,                ");
      buff.append (" Motoristas.NR_Registro,              ");
      buff.append (" Motoristas.DT_Nascimento,      ");
      buff.append (" Motoristas.DM_Tipo_Funcao,              ");
      buff.append (" Motoristas.PE_Comissao,              ");
      buff.append (" Motoristas.PE_Gratificacao_0,              ");
      buff.append (" Motoristas.PE_Gratificacao_1,              ");
      buff.append (" Motoristas.PE_Gratificacao_2,              ");
      buff.append (" Motoristas.PE_Gratificacao_3,              ");
      buff.append (" Motoristas.DM_Pagamento_Diaria,              ");
      buff.append (" Motoristas.DT_Vencimento_Aval_Med,              ");
      buff.append (" Motoristas.DT_Liberacao_Cadastro,              ");
      buff.append (" Motoristas.VL_INSS,              ");
      buff.append (" Motoristas.VL_Limite_Credito,              ");
      buff.append (" Motoristas.VL_Base_Adto,              ");
      buff.append (" Motoristas.VL_Plano_Saude,              ");
      buff.append (" Motoristas.DM_Qualificacao,      ");
      buff.append (" Motoristas.CD_Motorista,      ");
      buff.append (" Motoristas.PE_Comissao_Terceiro,       ");
      buff.append (" Motoristas.PE_Comissao_Media1,              ");
      buff.append (" Motoristas.PE_Comissao_Media2,              ");
      buff.append (" Motoristas.NR_Media1,              ");
      buff.append (" Motoristas.NR_Media2,              ");
      buff.append (" Motoristas.NR_Dependentes       ");
      buff.append (" ,Motoristas.NR_Cartao_CFE       ");

      buff.append ("FROM ");
      buff.append (" Motoristas,                    ");
      buff.append (" Pessoas,                     ");
      buff.append (" Pessoas Pessoa_Vinculo      ");
      buff.append (
          " WHERE Motoristas.OID_Pessoa           = Pessoas.OID_Pessoa             ");
      buff.append (
          " AND   Motoristas.OID_Pessoa_Vinculo   = Pessoa_Vinculo.OID_Pessoa   ");
      buff.append (" AND   Motoristas.OID_Motorista = '");
      buff.append (OID_Motorista);
      buff.append ("'");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());


      while (cursor.next ()) {
        p.setOID_Motorista (cursor.getString (1));
        p.setOID_Pessoa (cursor.getString (2));
        p.setOID_Pessoa_Vinculo (cursor.getString (3));
        p.setNM_Apelido (cursor.getString (4));
        p.setNR_Registro_Geral (cursor.getString (5));
        p.setNM_Orgao_Emissor (cursor.getString (6));
        p.setNR_CNH (cursor.getString (7));
        p.setDT_Vencimento_CNH (cursor.getString (8));
        p.setNM_Categoria (cursor.getString (9));
        p.setDM_Carga_Perigosa (cursor.getString (10));
        p.setDM_Situacao (cursor.getString (11));
        p.setTX_Observacao (cursor.getString (12));
        p.setNR_PIS (cursor.getString (13));
        p.setNR_INSS (cursor.getString (14));
        p.setNR_Telefone (cursor.getString (15));
        p.setNR_Celular (cursor.getString (16));
        p.setTX_Referencia (cursor.getString (17));
        p.setNM_Razao_Social (cursor.getString (18));
        p.setNM_Razao_Social_Vinculo (cursor.getString (19));
        p.setNR_CNPJ_CPF_Vinculo (cursor.getString (20));
        p.setNM_Pai (cursor.getString (21));
        p.setNM_Mae (cursor.getString (22));
        p.setNR_Registro (cursor.getString (23));
        p.setDT_Nascimento (cursor.getString (24));
        p.setDM_Tipo_Funcao (cursor.getString (25));
        p.setPE_Comissao (cursor.getDouble (26));
        p.setPE_Gratificacao_0 (cursor.getDouble (27));
        p.setPE_Gratificacao_1 (cursor.getDouble (28));
        p.setPE_Gratificacao_2 (cursor.getDouble (29));
        p.setPE_Gratificacao_3 (cursor.getDouble (30));
        p.setDM_Pagamento_Diaria (cursor.getString (31));
        p.setDT_Vencimento_Aval_Med (cursor.getString (32));
        p.setDT_Liberacao_Cadastro (cursor.getString (33));
        p.setVL_INSS (cursor.getDouble (34));
        p.setVL_Limite_Credito (cursor.getDouble (35));
        p.setVL_Base_Adto (cursor.getDouble (36));
        p.setVL_Plano_Saude (cursor.getDouble (37));
        p.setDM_Qualificacao (cursor.getString (38));
        p.setCD_Motorista (cursor.getString (39));
        p.setPE_Comissao_Terceiro (cursor.getDouble (40));

        p.setPE_Comissao_Media1 (cursor.getDouble (41));
        p.setPE_Comissao_Media2 (cursor.getDouble (42));
        p.setNR_Media1 (cursor.getDouble (43));
        p.setNR_Media2 (cursor.getDouble (44));

        p.setNR_Dependentes (cursor.getInt (45));

        p.setNR_Cartao_CFE(cursor.getString(46));

        Seguro_MotoristaBean sm = Seguro_MotoristaBean.getByOID_Motorista(p.getOID_Motorista());
        p.setNR_Liberacao_Seguradora(sm.getNR_Liberacao());
        if(Parametro_FixoED.getInstancia().getDM_Exige_Validade_Lib_Seguradora_Motorista().equals("S")){
        	p.setDT_Vencimento_Liberacao_Seguradora(sm.getDT_Validade());
        	// // ("DATA LIB >"+p.getDT_Vencimento_Liberacao_Seguradora());
        	if(!JavaUtil.doValida(p.getDT_Vencimento_Liberacao_Seguradora())) p.setDT_Vencimento_Liberacao_Seguradora("00/00/0000");
        } else {
        	p.setDT_Vencimento_Liberacao_Seguradora(Data.getDataDMY());
        }

      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return p;
  }


  public static final MotoristaBean getByLimite_Credito (String OID_Motorista) throws
      Exception {
    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    MotoristaBean p = new MotoristaBean ();
    Statement stmt = conn.createStatement ();
    ResultSet cursor = null;
    try {
      String sql = " SELECT  Motoristas.VL_Limite_Credito" +
          " FROM  Motoristas " +
          " WHERE   Motoristas.OID_Motorista =  '" + OID_Motorista + "'";

             // // (sql);

      p.setVL_Limite_Credito(0);
      cursor = stmt.executeQuery (sql);
      while (cursor.next ()) {
             // // (cursor.getDouble("VL_Limite_Credito"));
          p.setVL_Limite_Credito (cursor.getDouble("VL_Limite_Credito"));
      }

      sql =
          " SELECT  Motoristas.VL_Limite_Credito, Ordens_Fretes.VL_Adiantamento1   " +
          " FROM    Motoristas, Ordens_Fretes " +
          " WHERE   Motoristas.oid_Motorista = Ordens_Fretes.OID_Motorista" +
          " AND     Ordens_Fretes.DM_Impresso = 'S' " +
          " AND     Ordens_Fretes.DM_Frete = 'A' " +
          " AND     Ordens_Fretes.oid_Acerto is null " +
          " AND     Ordens_Fretes.OID_Motorista =  '" + OID_Motorista + "'";

             // // (sql);
      cursor = stmt.executeQuery (sql);

      while (cursor.next ()) {
        p.setVL_Limite_Credito (p.getVL_Limite_Credito()- cursor.getDouble("VL_Adiantamento1"));
      }

      double vl_debito = 0 , vl_credito = 0, vl_saldo_acerto=0;
      sql = " SELECT sum (vl_lancamento) as vl_debito " +
          " FROM Movimentos_Contas_Correntes, Contas_Correntes" +
          " WHERE  Movimentos_Contas_Correntes.oid_Conta_Corrente=Contas_Correntes.oid_Conta_Corrente" +
          " AND Contas_Correntes.oid_Pessoa='" + OID_Motorista + "'" +
          " AND DM_debito_Credito ='D' " +
          " AND DT_Movimento_Conta_Corrente<'" + Data.getDataDMY () + "'";
             // // (sql);
      cursor = stmt.executeQuery (sql);

      while (cursor.next ()) {
        vl_debito = cursor.getDouble ("vl_debito");
      }
      // //  ("vl_debito" + vl_debito);

      sql = " SELECT sum (vl_lancamento) as vl_credito " +
          " FROM Movimentos_Contas_Correntes, Contas_Correntes" +
          " WHERE  Movimentos_Contas_Correntes.oid_Conta_Corrente=Contas_Correntes.oid_Conta_Corrente" +
          " AND Contas_Correntes.oid_Pessoa='" + OID_Motorista + "'" +
          " AND DM_debito_Credito ='C' " +
          " AND DT_Movimento_Conta_Corrente<'" + Data.getDataDMY () + "'";
      cursor = stmt.executeQuery (sql);

      while (cursor.next ()) {
        vl_credito = cursor.getDouble ("vl_credito");
      }

      sql = " SELECT sum (VL_Saldo) as VL_Saldo FROM Acertos " +
          " WHERE Acertos.oid_Movimento_Conta_Corrente is null " +
          " AND   Acertos.OID_Motorista =  '" + OID_Motorista + "'";
      cursor = stmt.executeQuery (sql);

      while (cursor.next ()) {
        vl_saldo_acerto = cursor.getDouble ("VL_Saldo");
      }
      // //  ("vl_saldo_acerto" + vl_saldo_acerto);


             // // ("vl_credito"+vl_credito);

      p.setVL_Limite_Credito (p.getVL_Limite_Credito()+vl_credito-vl_debito-vl_saldo_acerto);


      // // (p.getVL_Limite_Credito());

      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return p;
  }


  public static final List getByNR_CNPJ_CPF (String NR_CNPJ_CPF) throws
      Exception {
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o nome do DSN
      // o nome de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    List Motorista_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT                             ");
      buff.append (" Motoristas.OID_Motorista,             ");
      buff.append (" Motoristas.OID_Pessoa,             ");
      buff.append (" Motoristas.OID_Pessoa_Vinculo,               ");
      buff.append (" Motoristas.NM_Apelido,                ");
      buff.append (" Motoristas.NR_Registro_Geral,              ");
      buff.append (" Motoristas.NM_Orgao_Emissor,      ");
      buff.append (" Motoristas.NR_CNH,      ");
      buff.append (" Motoristas.DT_Vencimento_CNH,      ");
      buff.append (" Motoristas.NM_Categoria,      ");
      buff.append (" Motoristas.DM_Carga_Perigosa,      ");
      buff.append (" Motoristas.DM_Situacao,      ");
      buff.append (" Motoristas.TX_Observacao,      ");
      buff.append (" Motoristas.NR_PIS,      ");
      buff.append (" Motoristas.NR_INSS,      ");
      buff.append (" Motoristas.NR_Telefone,      ");
      buff.append (" Motoristas.NR_Celular,      ");
      buff.append (" Motoristas.TX_Referencia,      ");
      buff.append (" Pessoas.NM_Razao_Social,              ");
      buff.append (" Pessoa_Vinculo.NM_Razao_Social,    ");
      buff.append (" Pessoa_Vinculo.NR_CNPJ_CPF,    ");
      buff.append (" Motoristas.DT_Vencimento_Aval_Med      ");
      buff.append ("FROM ");
      buff.append (" Motoristas,                    ");
      buff.append (" Pessoas,                     ");
      buff.append (" Pessoas Pessoa_Vinculo      ");
      buff.append (
          " WHERE Motoristas.OID_Pessoa           = Pessoas.OID_Pessoa             ");
      buff.append (
          " AND   Motoristas.OID_Pessoa_Vinculo   = Pessoa_Vinculo.OID_Pessoa   ");
      buff.append (" AND   Pessoas.NR_CNPJ_CPF = '");
      buff.append (NR_CNPJ_CPF);
      buff.append ("'");
      buff.append (" ORDER BY Pessoas.NM_Razao_Social ");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        MotoristaBean p = new MotoristaBean ();
        p.setOID_Motorista (cursor.getString (1));
        p.setOID_Pessoa (cursor.getString (2));
        p.setOID_Pessoa_Vinculo (cursor.getString (3));
        p.setNM_Apelido (cursor.getString (4));
        p.setNR_Registro_Geral (cursor.getString (5));
        p.setNM_Orgao_Emissor (cursor.getString (6));
        p.setNR_CNH (cursor.getString (7));
        p.setDT_Vencimento_CNH (cursor.getString (8));
        p.setNM_Categoria (cursor.getString (9));
        p.setDM_Carga_Perigosa (cursor.getString (10));
        p.setDM_Situacao (cursor.getString (11));
        p.setTX_Observacao (cursor.getString (12));
        p.setNR_PIS (cursor.getString (13));
        p.setNR_INSS (cursor.getString (14));
        p.setNR_Telefone (cursor.getString (15));
        p.setNR_Celular (cursor.getString (16));
        p.setTX_Referencia (cursor.getString (17));
        p.setNM_Razao_Social (cursor.getString (18));
        p.setNM_Razao_Social_Vinculo (cursor.getString (19));
        p.setNR_CNPJ_CPF_Vinculo (cursor.getString (20));
        p.setDT_Vencimento_Aval_Med (cursor.getString (21));

        Motorista_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Motorista_Lista;
  }

  public static final List getByNM_Razao_Social (String NM_Razao_Social) throws
      Exception {
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o nome do DSN
      // o nome de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    List Motorista_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT                             ");
      buff.append (" Motoristas.OID_Motorista,             ");
      buff.append (" Motoristas.OID_Pessoa,             ");
      buff.append (" Motoristas.OID_Pessoa_Vinculo,               ");
      buff.append (" Motoristas.NM_Apelido,                ");
      buff.append (" Motoristas.NR_Registro_Geral,              ");
      buff.append (" Motoristas.NM_Orgao_Emissor,      ");
      buff.append (" Motoristas.NR_CNH,      ");
      buff.append (" Motoristas.DT_Vencimento_CNH,      ");
      buff.append (" Motoristas.NM_Categoria,      ");
      buff.append (" Motoristas.DM_Carga_Perigosa,      ");
      buff.append (" Motoristas.DM_Situacao,      ");
      buff.append (" Motoristas.TX_Observacao,      ");
      buff.append (" Motoristas.NR_PIS,      ");
      buff.append (" Motoristas.NR_INSS,      ");
      buff.append (" Motoristas.NR_Telefone,      ");
      buff.append (" Motoristas.NR_Celular,      ");
      buff.append (" Motoristas.TX_Referencia,      ");
      buff.append (" Pessoas.NM_Razao_Social,              ");
      buff.append (" Pessoa_Vinculo.NM_Razao_Social,    ");
      buff.append (" Pessoa_Vinculo.NR_CNPJ_CPF,    ");
      buff.append (" Motoristas.DT_Vencimento_Aval_Med    ");
      buff.append ("FROM ");
      buff.append (" Motoristas,                    ");
      buff.append (" Pessoas,                     ");
      buff.append (" Pessoas Pessoa_Vinculo      ");
      buff.append (
          " WHERE Motoristas.OID_Pessoa           = Pessoas.OID_Pessoa             ");
      buff.append (
          " AND   Motoristas.OID_Pessoa_Vinculo   = Pessoa_Vinculo.OID_Pessoa   ");
      buff.append (" AND   Pessoas.NM_Razao_Social LIKE '");
      buff.append (NM_Razao_Social);
      buff.append ("%'");
      buff.append (" ORDER BY Pessoas.NM_Razao_Social ");

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        MotoristaBean p = new MotoristaBean ();
        p.setOID_Motorista (cursor.getString (1));
        p.setOID_Pessoa (cursor.getString (2));
        p.setOID_Pessoa_Vinculo (cursor.getString (3));
        p.setNM_Apelido (cursor.getString (4));
        p.setNR_Registro_Geral (cursor.getString (5));
        p.setNM_Orgao_Emissor (cursor.getString (6));
        p.setNR_CNH (cursor.getString (7));
        p.setDT_Vencimento_CNH (cursor.getString (8));
        p.setNM_Categoria (cursor.getString (9));
        p.setDM_Carga_Perigosa (cursor.getString (10));
        p.setDM_Situacao (cursor.getString (11));
        p.setTX_Observacao (cursor.getString (12));
        p.setNR_PIS (cursor.getString (13));
        p.setNR_INSS (cursor.getString (14));
        p.setNR_Telefone (cursor.getString (15));
        p.setNR_Celular (cursor.getString (16));
        p.setTX_Referencia (cursor.getString (17));
        p.setNM_Razao_Social (cursor.getString (18));
        p.setNM_Razao_Social_Vinculo (cursor.getString (19));
        p.setNR_CNPJ_CPF_Vinculo (cursor.getString (20));
        p.setDT_Vencimento_Aval_Med (cursor.getString (21));

        Motorista_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Motorista_Lista;
  }

  public static final List getByNM_Apelido (String NM_Apelido) throws Exception {
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o nome do DSN
      // o nome de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    List Motorista_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT                             ");
      buff.append (" Motoristas.OID_Motorista,             ");
      buff.append (" Motoristas.OID_Pessoa,             ");
      buff.append (" Motoristas.OID_Pessoa_Vinculo,               ");
      buff.append (" Motoristas.NM_Apelido,                ");
      buff.append (" Motoristas.NR_Registro_Geral,              ");
      buff.append (" Motoristas.NM_Orgao_Emissor,      ");
      buff.append (" Motoristas.NR_CNH,      ");
      buff.append (" Motoristas.DT_Vencimento_CNH,      ");
      buff.append (" Motoristas.NM_Categoria,      ");
      buff.append (" Motoristas.DM_Carga_Perigosa,      ");
      buff.append (" Motoristas.DM_Situacao,      ");
      buff.append (" Motoristas.TX_Observacao,      ");
      buff.append (" Motoristas.NR_PIS,      ");
      buff.append (" Motoristas.NR_INSS,      ");
      buff.append (" Motoristas.NR_Telefone,      ");
      buff.append (" Motoristas.NR_Celular,      ");
      buff.append (" Motoristas.TX_Referencia,      ");
      buff.append (" Pessoas.NM_Razao_Social,              ");
      buff.append (" Pessoa_Vinculo.NM_Razao_Social,    ");
      buff.append (" Pessoa_Vinculo.NR_CNPJ_CPF,    ");
      buff.append (" Motoristas.DT_Vencimento_Aval_Med    ");
      buff.append (" FROM ");
      buff.append (" Motoristas,                    ");
      buff.append (" Pessoas,                     ");
      buff.append (" Pessoas Pessoa_Vinculo      ");
      buff.append (
          " WHERE Motoristas.OID_Pessoa           = Pessoas.OID_Pessoa             ");
      buff.append (
          " AND   Motoristas.OID_Pessoa_Vinculo   = Pessoa_Vinculo.OID_Pessoa   ");
      buff.append (" AND   Motoristas.NM_Apelido LIKE '%");
      buff.append (NM_Apelido);
      buff.append ("%'");
      buff.append (" ORDER BY Pessoas.NM_Razao_Social ");

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        MotoristaBean p = new MotoristaBean ();
        p.setOID_Motorista (cursor.getString (1));
        p.setOID_Pessoa (cursor.getString (2));
        p.setOID_Pessoa_Vinculo (cursor.getString (3));
        p.setNM_Apelido (cursor.getString (4));
        p.setNR_Registro_Geral (cursor.getString (5));
        p.setNM_Orgao_Emissor (cursor.getString (6));
        p.setNR_CNH (cursor.getString (7));
        p.setDT_Vencimento_CNH (cursor.getString (8));
        p.setNM_Categoria (cursor.getString (9));
        p.setDM_Carga_Perigosa (cursor.getString (10));
        p.setDM_Situacao (cursor.getString (11));
        p.setTX_Observacao (cursor.getString (12));
        p.setNR_PIS (cursor.getString (13));
        p.setNR_INSS (cursor.getString (14));
        p.setNR_Telefone (cursor.getString (15));
        p.setNR_Celular (cursor.getString (16));
        p.setTX_Referencia (cursor.getString (17));
        p.setNM_Razao_Social (cursor.getString (18));
        p.setNM_Razao_Social_Vinculo (cursor.getString (19));
        p.setNR_CNPJ_CPF_Vinculo (cursor.getString (20));
        p.setDT_Vencimento_Aval_Med (cursor.getString (21));

        Motorista_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Motorista_Lista;
  }

  public static final List getAllMotoristas () throws Exception {

    Connection conn = null;

    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    List Motorista_Lista = new ArrayList ();

    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT                             ");
      buff.append (" Motoristas.OID_Motorista,             ");
      buff.append (" Motoristas.OID_Pessoa,             ");
      buff.append (" Motoristas.OID_Pessoa_Vinculo,               ");
      buff.append (" Motoristas.NM_Apelido,                ");
      buff.append (" Motoristas.NR_Registro_Geral,              ");
      buff.append (" Motoristas.NM_Orgao_Emissor,      ");
      buff.append (" Motoristas.NR_CNH,      ");
      buff.append (" Motoristas.DT_Vencimento_CNH,      ");
      buff.append (" Motoristas.NM_Categoria,      ");
      buff.append (" Motoristas.DM_Carga_Perigosa,      ");
      buff.append (" Motoristas.DM_Situacao,      ");
      buff.append (" Motoristas.TX_Observacao,      ");
      buff.append (" Motoristas.NR_PIS,      ");
      buff.append (" Motoristas.NR_INSS,      ");
      buff.append (" Motoristas.NR_Telefone,      ");
      buff.append (" Motoristas.NR_Celular,      ");
      buff.append (" Motoristas.TX_Referencia,      ");
      buff.append (" Pessoas.NM_Razao_Social,              ");
      buff.append (" Pessoa_Vinculo.NM_Razao_Social,    ");
      buff.append (" Pessoa_Vinculo.NR_CNPJ_CPF,    ");
      buff.append (" Motoristas.DT_Vencimento_Aval_Med    ");
      buff.append ("FROM ");
      buff.append (" Motoristas,                    ");
      buff.append (" Pessoas,                     ");
      buff.append (" Pessoas Pessoa_Vinculo      ");
      buff.append (
          " WHERE Motoristas.OID_Pessoa           = Pessoas.OID_Pessoa             ");
      buff.append (
          " AND   Motoristas.OID_Pessoa_Vinculo   = Pessoa_Vinculo.OID_Pessoa   ");
      buff.append (" ORDER BY Pessoas.NM_Razao_Social ");

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        MotoristaBean p = new MotoristaBean ();
        p.setOID_Motorista (cursor.getString (1));
        p.setOID_Pessoa (cursor.getString (2));
        p.setOID_Pessoa_Vinculo (cursor.getString (3));
        p.setNM_Apelido (cursor.getString (4));
        p.setNR_Registro_Geral (cursor.getString (5));
        p.setNM_Orgao_Emissor (cursor.getString (6));
        p.setNR_CNH (cursor.getString (7));
        p.setDT_Vencimento_CNH (cursor.getString (8));
        p.setNM_Categoria (cursor.getString (9));
        p.setDM_Carga_Perigosa (cursor.getString (10));
        p.setDM_Situacao (cursor.getString (11));
        p.setTX_Observacao (cursor.getString (12));
        p.setNR_PIS (cursor.getString (13));
        p.setNR_INSS (cursor.getString (14));
        p.setNR_Telefone (cursor.getString (15));
        p.setNR_Celular (cursor.getString (16));
        p.setTX_Referencia (cursor.getString (17));
        p.setNM_Razao_Social (cursor.getString (18));
        p.setNM_Razao_Social_Vinculo (cursor.getString (19));
        p.setNR_CNPJ_CPF_Vinculo (cursor.getString (20));
        p.setDT_Vencimento_Aval_Med (cursor.getString (21));

        Motorista_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Motorista_Lista;
  }

  public void imprime_Ficha (javax.servlet.http.HttpServletRequest request ,
                             javax.servlet.http.HttpServletResponse res) throws
      Exception {

    String sql = null;
    sql =
        " SELECT * from Pessoas, Motoristas, Cidades, Regioes_Estados, Estados  ";
    sql += " WHERE Motoristas.OID_Pessoa = Pessoas.oid_Pessoa ";
    sql += " AND Pessoas.OID_CIDADE = Cidades.OID_CIDADE ";
    sql +=
        " AND Cidades.OID_REGIAO_ESTADO = Regioes_Estados.OID_REGIAO_ESTADO ";
    sql += " AND Regioes_Estados.OID_ESTADO = Estados.OID_ESTADO ";

    String oid_Pessoa = request.getParameter ("oid_Pessoa");
    if (String.valueOf (oid_Pessoa) != null &&
        !String.valueOf (oid_Pessoa).equals ("")) {
      sql += " and Pessoas.OID_Pessoa = '" + oid_Pessoa + "'";
    }

    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Tipo_Documento do DSN
      // o NM_Tipo_Documento de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    Statement stmt = conn.createStatement ();
    ResultSet cursor = stmt.executeQuery (sql);

    MotoristaRL MotoristaRL = new MotoristaRL ();
    byte[] b = MotoristaRL.Imprime_Ficha (cursor);

    new EnviaPDF ().enviaBytes (request , res , b);

  }


  public  byte[] geraRelacaoMotoristas(HttpServletRequest request, HttpServletResponse Response)throws Excecoes{

    byte[] b = null;

    try {
      String DM_Situacao = request.getParameter ("FT_DM_Situacao");
      String DM_Qualificacao = request.getParameter ("FT_DM_Qualificacao");
      String DM_Tipo_Funcao = request.getParameter ("FT_DM_Tipo_Funcao");
      String DM_Relatorio = request.getParameter ("FT_DM_Relatorio");
      String DT_Inicial = request.getParameter ("FT_DT_Inicial");
      String DT_Final = request.getParameter ("FT_DT_Final");
      String oid_Motorista = request.getParameter ("oid_Motorista");

      String sql = null;

      sql =
          "Select * from Pessoas, Motoristas, Cidades, Estados, Regioes_Estados " +
          "where Pessoas.OID_Pessoa = Motoristas.OID_Pessoa " +
          "and Pessoas.OID_Cidade = Cidades.OID_Cidade " +
          "and Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado " +
          "and Regioes_Estados.OID_Estado = Estados.OID_Estado ";

      if (oid_Motorista != null && !oid_Motorista.equals ("") &&
          !oid_Motorista.equals ("") && !oid_Motorista.equals ("null")) {
        sql += " and Motoristas.OID_Pessoa = '" + oid_Motorista + "'";
      }else {
        if (DM_Situacao != null && !DM_Situacao.equals ("T") &&
            !DM_Situacao.equals ("") && !DM_Situacao.equals ("null")) {
          sql += " and Motoristas.DM_Situacao = '" + DM_Situacao + "'";
        }
        if (DM_Qualificacao != null && !DM_Qualificacao.equals ("X") &&
            !DM_Qualificacao.equals ("") && !DM_Qualificacao.equals ("null")) {
          sql += " and Motoristas.DM_Qualificacao = '" + DM_Qualificacao + "'";
        }
        if (DM_Tipo_Funcao != null && !DM_Tipo_Funcao.equals ("T") &&
            !DM_Tipo_Funcao.equals ("") && !DM_Tipo_Funcao.equals ("null")) {

          sql += " and Motoristas.DM_Tipo_Funcao = '" + DM_Tipo_Funcao + "'";
        }

        sql += " Order by NM_Razao_Social";
      }

      Connection conn = null;

      try {
        conn = OracleConnection2.getWEB ();
        conn.setAutoCommit (false);
      }
      catch (Exception e) {
        e.printStackTrace ();
      }

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (sql);

      MotoristaRL motRL = new MotoristaRL ();
      b = motRL.geraRelacaoMotoristas (cursor , DM_Situacao , DM_Tipo_Funcao, DM_Relatorio, DT_Inicial, DT_Final);


    }
    catch (Exception exc) {
      exc.printStackTrace ();
    }
    return b;
  }


  public void geraRelOcorrencia_Motoristas (javax.servlet.http.
                                            HttpServletRequest request ,
                                            javax.servlet.http.
                                            HttpServletResponse res) {

    try {

      String DM_Penalizacao = request.getParameter ("FT_DM_Penalizacao");
      String oid_Tipo_Ocorrencia = request.getParameter ("oid_Tipo_Ocorrencia");
      String DT_Ocorrencia_Inicial = request.getParameter (
          "FT_DT_Ocorrencia_Inicial");
      String DT_Ocorrencia_Final = request.getParameter (
          "FT_DT_Ocorrencia_Final");
      String NR_CNPJ_CPF = request.getParameter ("FT_NR_CNPJ_CPF");

      String sql = null;

      sql =
          "Select * from Ocorrencias_Motoristas, Tipos_Ocorrencias, Pessoas, Motoristas " +
          "where Pessoas.OID_Pessoa = Motoristas.OID_Pessoa " +
          "and Pessoas.OID_Pessoa = Ocorrencias_Motoristas.oid_Pessoa " +
          "and Tipos_Ocorrencias.OID_Tipo_Ocorrencia = Ocorrencias_Motoristas.OID_Tipo_Ocorrencia ";

      if (DM_Penalizacao != null && !DM_Penalizacao.equals ("T") &&
          !DM_Penalizacao.equals ("") && !DM_Penalizacao.equals ("null")) {
        sql += " and Ocorrencias_Motoristas.DM_Penalizacao = '" +
            DM_Penalizacao + "'";
      }
      if (NR_CNPJ_CPF != null && !NR_CNPJ_CPF.equals ("") &&
          !NR_CNPJ_CPF.equals ("null")) {
        sql += " and Ocorrencias_Motoristas.oid_Pessoa = '" + NR_CNPJ_CPF + "'";
      }
      if (oid_Tipo_Ocorrencia != null && !oid_Tipo_Ocorrencia.equals ("") &&
          !oid_Tipo_Ocorrencia.equals ("null")) {
        sql += " and Ocorrencias_Motoristas.oid_Tipo_Ocorrencia = " +
            oid_Tipo_Ocorrencia;
      }
      if (DT_Ocorrencia_Inicial != null && !DT_Ocorrencia_Inicial.equals ("") &&
          !DT_Ocorrencia_Inicial.equals ("null")) {
        sql += " and Ocorrencias_Motoristas.DT_Ocorrencia_Motorista >= '" +
            DT_Ocorrencia_Inicial + "'";
      }
      if (DT_Ocorrencia_Final != null && !DT_Ocorrencia_Final.equals ("") &&
          !DT_Ocorrencia_Final.equals ("null")) {
        sql += " and Ocorrencias_Motoristas.DT_Ocorrencia_Motorista <= '" +
            DT_Ocorrencia_Final + "'";
      }

      sql += " Order by NM_Razao_Social, Dt_ocorrencia_motorista";

      Connection conn = null;

      try {
        conn = OracleConnection2.getWEB ();
        conn.setAutoCommit (false);
      }
      catch (Exception e) {
        e.printStackTrace ();
      }

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (sql);

      MotoristaRL motRL = new MotoristaRL ();
      byte[] b = motRL.geraRelacaoOcorrencia_Motoristas (cursor ,
          DM_Penalizacao , DT_Ocorrencia_Inicial , DT_Ocorrencia_Final);

      new EnviaPDF ().enviaBytes (request , res , b);

    }
    catch (Exception exc) {
      exc.printStackTrace ();
    }
  }

  public static void main (String args[]) throws Exception {
    MotoristaBean pp = new MotoristaBean ();
    pp.update ();

  }

  public void setDM_Pagamento_Diaria (String DM_Pagamento_Diaria) {
    this.DM_Pagamento_Diaria = DM_Pagamento_Diaria;
  }

  public String getDM_Pagamento_Diaria () {
    return DM_Pagamento_Diaria;
  }

  public void setPE_Comissao_Terceiro (double PE_Comissao_Terceiro) {
    this.PE_Comissao_Terceiro = PE_Comissao_Terceiro;
  }

  public double getPE_Comissao_Terceiro () {
    return PE_Comissao_Terceiro;
  }

  public void setDT_Liberacao_Cadastro (String DT_Liberacao_Cadastro) {
    this.DT_Liberacao_Cadastro = DT_Liberacao_Cadastro;
  }

  public String getDT_Liberacao_Cadastro () {
    return DT_Liberacao_Cadastro;
  }

  public void setVL_INSS (double VL_INSS) {
    this.VL_INSS = VL_INSS;
  }

  public double getVL_INSS () {
    return VL_INSS;
  }

  public void setVL_Plano_Saude (double VL_Plano_Saude) {
    this.VL_Plano_Saude = VL_Plano_Saude;
  }

  public double getVL_Plano_Saude () {
    return VL_Plano_Saude;
  }

  public void setVL_Limite_Credito (double VL_Limite_Credito) {
    this.VL_Limite_Credito = VL_Limite_Credito;
  }

  public double getVL_Limite_Credito () {
    return VL_Limite_Credito;
  }

	public String getDT_Vencimento_Liberacao_Seguradora() {
		return DT_Vencimento_Liberacao_Seguradora;
	}

	public void setDT_Vencimento_Liberacao_Seguradora(
			String vencimento_Liberacao_Seguradora) {
		DT_Vencimento_Liberacao_Seguradora = vencimento_Liberacao_Seguradora;
	}

	public String getNR_Liberacao_Seguradora() {
		return NR_Liberacao_Seguradora;
	}

	public void setNR_Liberacao_Seguradora(String liberacao_Seguradora) {
		NR_Liberacao_Seguradora = liberacao_Seguradora;
	}

  private void jbInit () throws Exception {
  }

  public void setDM_Qualificacao (String DM_Qualificacao) {
    this.DM_Qualificacao = DM_Qualificacao;
  }

  public String getDM_Qualificacao () {
    return DM_Qualificacao;
  }

  public String getDT_Liberacao_Viagem () {

    return DT_Liberacao_Viagem;
  }

  public void setDT_Liberacao_Viagem (String DT_Liberacao_Viagem) {

    this.DT_Liberacao_Viagem = DT_Liberacao_Viagem;
  }

public String getCD_Motorista() {
	return CD_Motorista;
}

public void setCD_Motorista(String motorista) {
	CD_Motorista = motorista;
}

public int getNR_Dependentes() {
	return NR_Dependentes;
}

public void setNR_Dependentes(int dependentes) {
	NR_Dependentes = dependentes;
}

public double getNR_Media1() {
	return NR_Media1;
}

public void setNR_Media1(double media1) {
	NR_Media1 = media1;
}

public double getNR_Media2() {
	return NR_Media2;
}

public void setNR_Media2(double media2) {
	NR_Media2 = media2;
}

public double getPE_Comissao_Media1() {
	return PE_Comissao_Media1;
}

public void setPE_Comissao_Media1(double comissao_Media1) {
	PE_Comissao_Media1 = comissao_Media1;
}

public double getPE_Comissao_Media2() {
	return PE_Comissao_Media2;
}

public void setPE_Comissao_Media2(double comissao_Media2) {
	PE_Comissao_Media2 = comissao_Media2;
}

public double getVL_Base_Adto() {
	return VL_Base_Adto;
}

public void setVL_Base_Adto(double base_Adto) {
	VL_Base_Adto = base_Adto;
}

public String getNR_Cartao_CFE() {
	return NR_Cartao_CFE;
}

public void setNR_Cartao_CFE(String cartao_CFE) {
	NR_Cartao_CFE = cartao_CFE;
}
}
