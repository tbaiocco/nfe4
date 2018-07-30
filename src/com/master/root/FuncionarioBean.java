package com.master.root;

import java.sql.*;
import java.util.*;
import javax.servlet.http.*;

import auth.*;
import com.master.rl.*;
import com.master.util.*;

public class FuncionarioBean {
  private String DT_Nascimento;
  private String NR_Registro;
  private String DM_Tipo_Funcao;
  private String NM_Pai;
  private String NM_Mae;
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
  private String NR_CNPJ_CPF;
  private String NM_Razao_Social;
  private String OID_Pessoa;
  private String OID_Funcionario;
  private String DT_Vencimento_Aval_Med;
  private String DT_Aval_Med;
  private double PE_Comissao;
  private String NR_CTPS;
  private String NM_Serie_CTPS;
  private String DT_Admissao;
  private String DT_Demissao;
  private double VL_Salario;
  private String HR_Entrada_Manha;
  private String HR_Entrada_Tarde;
  private String HR_Saida_Manha;
  private String HR_Saida_Tarde;
  private String DM_Estabilidade;
  private String NM_Tipo_Estabilidade;
  private String DM_Estado_Civil;
  private String NM_Nacionalidade;
  private String NM_Conjuge;
  private int NR_Dependentes;
  private String DM_Dependentes;
  private String NR_Banco;
  private String NR_Agencia;
  private String NR_Conta_Bancaria;
  private String DM_Instrucao;
  private double VL_Limite_Abastecimento;

  public FuncionarioBean () {
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
    NR_CTPS = " ";
    NM_Serie_CTPS = " ";
    DT_Admissao = " ";
    DT_Demissao = " ";
    VL_Salario = 0.0;
    HR_Entrada_Manha = " ";
    HR_Entrada_Tarde = " ";
    HR_Saida_Manha = " ";
    HR_Saida_Tarde = " ";
    DM_Estabilidade = " ";
    NM_Tipo_Estabilidade = " ";
    DM_Estado_Civil = " ";
    NM_Nacionalidade = " ";
    NM_Conjuge = " ";
    NR_Dependentes = 0;
    DM_Dependentes = " ";
    NR_Banco = " ";
    NR_Agencia = " ";
    NR_Conta_Bancaria = " ";
    DM_Instrucao = " ";

  }

  public String getDM_Carga_Perigosa () {
    return DM_Carga_Perigosa;
  }

  public void setDM_Carga_Perigosa (String carga_Perigosa) {
    DM_Carga_Perigosa = carga_Perigosa;
  }

  public String getDM_Dependentes () {
    return DM_Dependentes;
  }

  public void setDM_Dependentes (String dependentes) {
    DM_Dependentes = dependentes;
  }

  public String getDM_Estabilidade () {
    return DM_Estabilidade;
  }

  public void setDM_Estabilidade (String estabilidade) {
    DM_Estabilidade = estabilidade;
  }

  public String getDM_Estado_Civil () {
    return DM_Estado_Civil;
  }

  public void setDM_Estado_Civil (String estado_Civil) {
    DM_Estado_Civil = estado_Civil;
  }

  public String getDM_Situacao () {
    return DM_Situacao;
  }

  public void setDM_Situacao (String situacao) {
    DM_Situacao = situacao;
  }

  public String getDm_Stamp () {
    return Dm_Stamp;
  }

  public void setDm_Stamp (String dm_Stamp) {
    Dm_Stamp = dm_Stamp;
  }

  public String getDM_Tipo_Funcao () {
    return DM_Tipo_Funcao;
  }

  public void setDM_Tipo_Funcao (String tipo_Funcao) {
    DM_Tipo_Funcao = tipo_Funcao;
  }

  public String getDT_Admissao () {
    FormataDataBean DataFormatada = new FormataDataBean ();
    DataFormatada.setDT_FormataData (DT_Admissao);
    DT_Admissao = DataFormatada.getDT_FormataData ();
    return DT_Admissao;
  }

  public void setDT_Admissao (String admissao) {
    DT_Admissao = admissao;
  }

  public String getDT_Aval_Med () {
    FormataDataBean DataFormatada = new FormataDataBean ();
    DataFormatada.setDT_FormataData (DT_Aval_Med);
    DT_Aval_Med = DataFormatada.getDT_FormataData ();
    return DT_Aval_Med;
  }

  public void setDT_Aval_Med (String aval_Med) {
    DT_Aval_Med = aval_Med;
  }

  public String getDT_Demissao () {
    FormataDataBean DataFormatada = new FormataDataBean ();
    DataFormatada.setDT_FormataData (DT_Demissao);
    DT_Demissao = DataFormatada.getDT_FormataData ();
    return DT_Demissao;
  }

  public void setDT_Demissao (String demissao) {
    DT_Demissao = demissao;
  }

  public String getDT_Nascimento () {
    FormataDataBean DataFormatada = new FormataDataBean ();
    DataFormatada.setDT_FormataData (DT_Nascimento);
    DT_Nascimento = DataFormatada.getDT_FormataData ();
    return DT_Nascimento;
  }

  public void setDT_Nascimento (String nascimento) {
    DT_Nascimento = nascimento;
  }

  public String getDt_Stamp () {
    return Dt_Stamp;
  }

  public void setDt_Stamp (String dt_Stamp) {
    Dt_Stamp = dt_Stamp;
  }

  public String getDT_Vencimento_Aval_Med () {
    FormataDataBean DataFormatada = new FormataDataBean ();
    DataFormatada.setDT_FormataData (DT_Vencimento_Aval_Med);
    DT_Vencimento_Aval_Med = DataFormatada.getDT_FormataData ();
    return DT_Vencimento_Aval_Med;
  }

  public void setDT_Vencimento_Aval_Med (String vencimento_Aval_Med) {
    DT_Vencimento_Aval_Med = vencimento_Aval_Med;
  }

  public String getDT_Vencimento_CNH () {
    FormataDataBean DataFormatada = new FormataDataBean ();
    DataFormatada.setDT_FormataData (DT_Vencimento_CNH);
    DT_Vencimento_CNH = DataFormatada.getDT_FormataData ();
    return DT_Vencimento_CNH;
  }

  public void setDT_Vencimento_CNH (String vencimento_CNH) {
    DT_Vencimento_CNH = vencimento_CNH;
  }

  public String getHR_Entrada_Manha () {
    return HR_Entrada_Manha;
  }

  public void setHR_Entrada_Manha (String entrada_Manha) {
    HR_Entrada_Manha = entrada_Manha;
  }

  public String getHR_Entrada_Tarde () {
    return HR_Entrada_Tarde;
  }

  public void setHR_Entrada_Tarde (String entrada_Tarde) {
    HR_Entrada_Tarde = entrada_Tarde;
  }

  public String getHR_Saida_Manha () {
    return HR_Saida_Manha;
  }

  public void setHR_Saida_Manha (String saida_Manha) {
    HR_Saida_Manha = saida_Manha;
  }

  public String getHR_Saida_Tarde () {
    return HR_Saida_Tarde;
  }

  public void setHR_Saida_Tarde (String saida_Tarde) {
    HR_Saida_Tarde = saida_Tarde;
  }

  public String getNM_Apelido () {
    return NM_Apelido;
  }

  public void setNM_Apelido (String apelido) {
    NM_Apelido = apelido;
  }

  public String getNM_Categoria () {
    return NM_Categoria;
  }

  public void setNM_Categoria (String categoria) {
    NM_Categoria = categoria;
  }

  public String getNM_Conjuge () {
    return NM_Conjuge;
  }

  public void setNM_Conjuge (String conjuge) {
    NM_Conjuge = conjuge;
  }

  public String getNM_Mae () {
    return NM_Mae;
  }

  public void setNM_Mae (String mae) {
    NM_Mae = mae;
  }

  public String getNM_Nacionalidade () {
    return NM_Nacionalidade;
  }

  public void setNM_Nacionalidade (String nacionalidade) {
    NM_Nacionalidade = nacionalidade;
  }

  public String getNM_Orgao_Emissor () {
    return NM_Orgao_Emissor;
  }

  public void setNM_Orgao_Emissor (String orgao_Emissor) {
    NM_Orgao_Emissor = orgao_Emissor;
  }

  public String getNM_Pai () {
    return NM_Pai;
  }

  public void setNM_Pai (String pai) {
    NM_Pai = pai;
  }

  public String getNM_Razao_Social () {
    return NM_Razao_Social;
  }

  public void setNM_Razao_Social (String razao_Social) {
    NM_Razao_Social = razao_Social;
  }

  public String getNM_Serie_CTPS () {
    return NM_Serie_CTPS;
  }

  public void setNM_Serie_CTPS (String serie_CTPS) {
    NM_Serie_CTPS = serie_CTPS;
  }

  public String getNM_Tipo_Estabilidade () {
    return NM_Tipo_Estabilidade;
  }

  public void setNM_Tipo_Estabilidade (String tipo_Estabilidade) {
    NM_Tipo_Estabilidade = tipo_Estabilidade;
  }

  public String getNR_Agencia () {
    return NR_Agencia;
  }

  public void setNR_Agencia (String agencia) {
    NR_Agencia = agencia;
  }

  public String getNR_Banco () {
    return NR_Banco;
  }

  public void setNR_Banco (String banco) {
    NR_Banco = banco;
  }

  public String getNR_Celular () {
    return NR_Celular;
  }

  public void setNR_Celular (String celular) {
    NR_Celular = celular;
  }

  public String getNR_CNH () {
    return NR_CNH;
  }

  public void setNR_CNH (String nr_cnh) {
    NR_CNH = nr_cnh;
  }

  public String getNR_CNPJ_CPF () {
    return NR_CNPJ_CPF;
  }

  public void setNR_CNPJ_CPF (String nr_cnpj_cpf) {
    NR_CNPJ_CPF = nr_cnpj_cpf;
  }

  public String getNR_Conta_Bancaria () {
    return NR_Conta_Bancaria;
  }

  public void setNR_Conta_Bancaria (String conta_Bancaria) {
    NR_Conta_Bancaria = conta_Bancaria;
  }

  public String getNR_CTPS () {
    return NR_CTPS;
  }

  public void setNR_CTPS (String nr_ctps) {
    NR_CTPS = nr_ctps;
  }

  public int getNR_Dependentes () {
    return NR_Dependentes;
  }

  public void setNR_Dependentes (int dependentes) {
    NR_Dependentes = dependentes;
  }

  public String getNR_INSS () {
    return NR_INSS;
  }

  public void setNR_INSS (String nr_inss) {
    NR_INSS = nr_inss;
  }

  public String getNR_PIS () {
    return NR_PIS;
  }

  public void setNR_PIS (String nr_pis) {
    NR_PIS = nr_pis;
  }

  public String getNR_Registro () {
    return NR_Registro;
  }

  public void setNR_Registro (String registro) {
    NR_Registro = registro;
  }

  public String getNR_Registro_Geral () {
    return NR_Registro_Geral;
  }

  public void setNR_Registro_Geral (String registro_Geral) {
    NR_Registro_Geral = registro_Geral;
  }

  public String getNR_Telefone () {
    return NR_Telefone;
  }

  public void setNR_Telefone (String telefone) {
    NR_Telefone = telefone;
  }

  public String getOID_Funcionario () {
    return OID_Funcionario;
  }

  public void setOID_Funcionario (String funcionario) {
    OID_Funcionario = funcionario;
  }

  public String getOID_Pessoa () {
    return OID_Pessoa;
  }

  public void setOID_Pessoa (String pessoa) {
    OID_Pessoa = pessoa;
  }

  public double getPE_Comissao () {
    return PE_Comissao;
  }

  public void setPE_Comissao (double comissao) {
    PE_Comissao = comissao;
  }

  public String getTX_Observacao () {
    return TX_Observacao;
  }

  public void setTX_Observacao (String observacao) {
    TX_Observacao = observacao;
  }

  public String getTX_Referencia () {
    return TX_Referencia;
  }

  public void setTX_Referencia (String referencia) {
    TX_Referencia = referencia;
  }

  public String getUsuario_Stamp () {
    return Usuario_Stamp;
  }

  public void setUsuario_Stamp (String usuario_Stamp) {
    Usuario_Stamp = usuario_Stamp;
  }

  public double getVL_Salario () {
    return VL_Salario;
  }

  public void setVL_Salario (double salario) {
    VL_Salario = salario;
  }

  public String getDM_Instrucao () {
    return DM_Instrucao;
  }

  public void setDM_Instrucao (String instrucao) {
    DM_Instrucao = instrucao;
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
    buff.append ("INSERT INTO funcionarios ( ");
    buff.append ("  OID_Funcionario,            ");
    buff.append ("  OID_Pessoa,            ");
    buff.append ("  NM_Apelido,               ");
    buff.append ("  NR_Registro_Geral,             ");
    buff.append ("  NM_Orgao_Emissor,     ");
    buff.append ("  NR_CNH,     ");
    buff.append ("  DT_Vencimento_CNH,     ");
    buff.append ("  NM_Categoria,     ");
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
    buff.append ("  DM_Tipo_Funcao, ");
    buff.append ("  DT_Vencimento_Aval_Med,  ");
    buff.append ("  dt_aval_med,               ");
    buff.append ("  dt_admissao,               ");
    buff.append ("  dt_demissao,             ");
    buff.append ("  nr_ctps,     ");
    buff.append ("  nm_serie_ctps,     ");
    buff.append ("  vl_salario, ");
    buff.append ("  hr_entrada_manha,  ");
    buff.append ("  hr_saida_manha,               ");
    buff.append ("  hr_entrada_tarde,               ");
    buff.append ("  hr_saida_tarde,             ");
    buff.append ("  dm_estabilidade,     ");
    buff.append ("  nm_tipo_estabilidade,     ");
    buff.append ("  nm_nacionalidade, ");
    buff.append ("  dm_estado_civil,  ");
    buff.append ("  nm_conjuge,               ");
    buff.append ("  dm_dependentes,               ");
    buff.append ("  nr_dependentes,             ");
    buff.append ("  dm_instrucao,     ");
    buff.append ("  nr_banco,     ");
    buff.append ("  nr_agencia, ");
    buff.append ("  vl_limite_abastecimento, ");
    buff.append ("  nr_conta_bancaria  )");

    buff.append ("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt = conn.prepareStatement (buff.toString ());
      pstmt.setString (1 , getOID_Funcionario ());
      pstmt.setString (2 , getOID_Pessoa ());
      pstmt.setString (3 , JavaUtil.trunc (getNM_Apelido () , 30));
      pstmt.setString (4 , getNR_Registro_Geral ());
      pstmt.setString (5 , getNM_Orgao_Emissor ());
      pstmt.setString (6 , getNR_CNH ());
      pstmt.setString (7 , this.DT_Vencimento_CNH == " " ? null : this.DT_Vencimento_CNH);
      pstmt.setString (8 , getNM_Categoria ());
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
      pstmt.setString (22 , this.DT_Nascimento == " " ? null : this.DT_Nascimento);
      pstmt.setDouble (23 , getPE_Comissao ());
      pstmt.setString (24 , getDM_Tipo_Funcao ());
      pstmt.setString (25 , this.DT_Vencimento_Aval_Med == " " ? null : this.DT_Vencimento_Aval_Med);
      pstmt.setString (26 , this.DT_Aval_Med == " " ? null : this.DT_Aval_Med);
      pstmt.setString (27 , this.DT_Admissao == " " ? null : this.DT_Admissao);
      pstmt.setString (28 , this.DT_Demissao == " " ? null : this.DT_Demissao);
      pstmt.setString (29 , getNR_CTPS ());
      pstmt.setString (30 , getNM_Serie_CTPS ());
      pstmt.setDouble (31 , getVL_Salario ());
      pstmt.setString (32 , getHR_Entrada_Manha ());
      pstmt.setString (33 , getHR_Saida_Manha ());
      pstmt.setString (34 , getHR_Entrada_Tarde ());
      pstmt.setString (35 , getHR_Saida_Tarde ());
      pstmt.setString (36 , getDM_Estabilidade ());
      pstmt.setString (37 , getNM_Tipo_Estabilidade ());
      pstmt.setString (38 , getNM_Nacionalidade ());
      pstmt.setString (39 , getDM_Estado_Civil ());
      pstmt.setString (40 , getNM_Conjuge ());
      pstmt.setString (41 , getDM_Dependentes ());
      pstmt.setInt (42 , getNR_Dependentes ());
      pstmt.setString (43 , getDM_Instrucao ());
      pstmt.setString (44 , getNR_Banco ());
      pstmt.setString (45 , getNR_Agencia ());
      pstmt.setDouble (46 , getVL_Limite_Abastecimento ());
      pstmt.setString (47 , getNR_Conta_Bancaria ());

      // System.out.println (pstmt.toString ());

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
    buff.append ("UPDATE Funcionarios SET ");
    buff.append ("  NM_Apelido=?,               ");
    buff.append ("  NR_Registro_Geral=?,             ");
    buff.append ("  NM_Orgao_Emissor=?,     ");
    buff.append ("  NR_CNH=?,     ");
    buff.append ("  DT_Vencimento_CNH=?,     ");
    buff.append ("  NM_Categoria=?,     ");
    buff.append ("  DM_Situacao=?,     ");
    buff.append ("  TX_Observacao=?,     ");
    buff.append ("  NR_PIS=?,     ");
    buff.append ("  NR_INSS=?,     ");
    buff.append ("  NR_Telefone=?,     ");
    buff.append ("  NR_Celular=?,     ");
    buff.append ("  TX_Referencia=?,     ");
    buff.append ("  NM_Pai=?,               ");
    buff.append ("  NM_Mae=?,               ");
    buff.append ("  NR_Registro=?,             ");
    buff.append ("  DT_Nascimento=?,     ");
    buff.append ("  PE_Comissao=?,     ");
    buff.append ("  DM_Tipo_Funcao=?,             ");
    buff.append ("  DT_Vencimento_Aval_Med=?,             ");
    buff.append ("  nr_ctps =?,               ");
    buff.append ("  nm_serie_ctps =?,             ");
    buff.append ("  dt_aval_med =?,     ");
    buff.append ("  dt_admissao =?,     ");
    buff.append ("  dt_demissao =?,     ");
    buff.append ("  vl_salario =?,     ");
    buff.append ("  hr_entrada_manha =?,     ");
    buff.append ("  hr_saida_manha =?,     ");
    buff.append ("  hr_entrada_tarde =?,     ");
    buff.append ("  hr_saida_tarde =?,     ");
    buff.append ("  dm_estabilidade =?,     ");
    buff.append ("  nm_tipo_estabilidade =?,     ");
    buff.append ("  dm_estado_civil =?,     ");
    buff.append ("  nm_nacionalidade =?,               ");
    buff.append ("  nm_conjuge =?,               ");
    buff.append ("  dm_dependentes =?,             ");
    buff.append ("  nr_dependentes =?,     ");
    buff.append ("  dm_instrucao =?,     ");
    buff.append ("  nr_banco =?,             ");
    buff.append ("  nr_agencia =?,             ");
    buff.append ("  vl_limite_abastecimento =?,             ");
    buff.append ("  nr_conta_bancaria =?              ");
    buff.append (" WHERE OID_Funcionario=?");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt = conn.prepareStatement (buff.toString ());

      pstmt.setString (1 , JavaUtil.trunc (getNM_Apelido () , 30));
      pstmt.setString (2 , getNR_Registro_Geral ());
      pstmt.setString (3 , getNM_Orgao_Emissor ());
      pstmt.setString (4 , getNR_CNH ());
      pstmt.setString (5 , this.DT_Vencimento_CNH == " " ? null : this.DT_Vencimento_CNH);
      pstmt.setString (6 , getNM_Categoria ());
      pstmt.setString (7 , getDM_Situacao ());
      pstmt.setString (8 , getTX_Observacao ());
      pstmt.setString (9 , getNR_PIS ());
      pstmt.setString (10 , getNR_INSS ());
      pstmt.setString (11 , getNR_Telefone ());
      pstmt.setString (12 , getNR_Celular ());
      pstmt.setString (13 , getTX_Referencia ());
      pstmt.setString (14 , getNM_Pai ());
      pstmt.setString (15 , getNM_Mae ());
      pstmt.setString (16 , getNR_Registro ());
      pstmt.setString (17 , this.DT_Nascimento == " " ? null : this.DT_Nascimento);
      pstmt.setDouble (18 , getPE_Comissao ());
      pstmt.setString (19 , getDM_Tipo_Funcao ());
      pstmt.setString (20 , this.DT_Vencimento_Aval_Med == " " ? null : this.DT_Vencimento_Aval_Med);
      pstmt.setString (21 , getNR_CTPS ());
      pstmt.setString (22 , getNM_Serie_CTPS ());
      pstmt.setString (23 , this.DT_Aval_Med == " " ? null : this.DT_Aval_Med);
      pstmt.setString (24 , this.DT_Admissao == " " ? null : this.DT_Admissao);
      pstmt.setString (25 , this.DT_Demissao == " " ? null : this.DT_Demissao);
      pstmt.setDouble (26 , getVL_Salario ());
      pstmt.setString (27 , getHR_Entrada_Manha ());
      pstmt.setString (28 , getHR_Saida_Manha ());
      pstmt.setString (29 , getHR_Entrada_Tarde ());
      pstmt.setString (30 , getHR_Saida_Tarde ());
      pstmt.setString (31 , getDM_Estabilidade ());
      pstmt.setString (32 , getNM_Tipo_Estabilidade ());
      pstmt.setString (33 , getDM_Estado_Civil ());
      pstmt.setString (34 , getNM_Nacionalidade ());
      pstmt.setString (35 , getNM_Conjuge ());
      pstmt.setString (36 , getDM_Dependentes ());
      pstmt.setInt (37 , getNR_Dependentes ());
      pstmt.setString (38 , getDM_Instrucao ());
      pstmt.setString (39 , getNR_Banco ());
      pstmt.setString (40 , getNR_Agencia ());
      pstmt.setDouble (41 , getVL_Limite_Abastecimento ());
      pstmt.setString (42 , getNR_Conta_Bancaria ());
      pstmt.setString (43 , getOID_Funcionario ());

      // System.out.println (pstmt.toString ());

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
    buff.append ("DELETE FROM Funcionarios ");
    buff.append ("WHERE OID_Funcionario=?");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setString (1 , getOID_Funcionario ());
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

  public static final FuncionarioBean getByOID_Funcionario (String oid_Pessoa) throws Exception {
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

    FuncionarioBean p = new FuncionarioBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT funcionarios.*,      ");
      buff.append (" Pessoas.NM_Razao_Social,   ");
      buff.append (" Pessoas.NR_CNPJ_CPF        ");
      buff.append (" FROM                       ");
      buff.append (" Funcionarios,              ");
      buff.append (" Pessoas                    ");
      buff.append (" WHERE Funcionarios.OID_Pessoa = Pessoas.OID_Pessoa ");
      buff.append (" AND   Funcionarios.OID_Pessoa = '");
      buff.append (oid_Pessoa);
      buff.append ("'");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID_Funcionario (cursor.getString ("OID_Funcionario"));
        p.setOID_Pessoa (cursor.getString ("OID_Pessoa"));
        p.setNM_Razao_Social (cursor.getString ("NM_Razao_Social"));
        p.setNR_CNPJ_CPF (cursor.getString ("NR_CNPJ_CPF"));
        p.setNM_Apelido (cursor.getString ("NM_Apelido"));
        p.setNR_Registro_Geral (cursor.getString ("NR_Registro_Geral"));
        p.setNM_Orgao_Emissor (cursor.getString ("NM_Orgao_Emissor"));
        p.setNR_CNH (cursor.getString ("NR_CNH"));
        p.setDT_Vencimento_CNH (cursor.getString ("DT_Vencimento_CNH"));
        p.setNM_Categoria (cursor.getString ("NM_Categoria"));
        p.setDM_Situacao (cursor.getString ("DM_Situacao"));
        p.setTX_Observacao (cursor.getString ("TX_Observacao"));
        p.setNR_PIS (cursor.getString ("NR_PIS"));
        p.setNR_INSS (cursor.getString ("NR_INSS"));
        p.setNR_Telefone (cursor.getString ("NR_Telefone"));
        p.setNR_Celular (cursor.getString ("NR_Celular"));
        p.setTX_Referencia (cursor.getString ("TX_Referencia"));
        p.setNM_Pai (cursor.getString ("NM_Pai"));
        p.setNM_Mae (cursor.getString ("NM_Mae"));
        p.setNR_Registro (cursor.getString ("NR_Registro"));
        p.setDT_Nascimento (cursor.getString ("DT_Nascimento"));
        p.setDM_Tipo_Funcao (cursor.getString ("DM_Tipo_Funcao"));
        p.setDT_Vencimento_Aval_Med (cursor.getString ("DT_Vencimento_Aval_Med"));
        p.setPE_Comissao (cursor.getDouble ("PE_Comissao"));
        p.setNR_CTPS (cursor.getString ("NR_CTPS"));
        p.setNM_Serie_CTPS (cursor.getString ("NM_Serie_CTPS"));
        p.setDT_Aval_Med (cursor.getString ("DT_Aval_Med"));
        p.setDT_Admissao (cursor.getString ("DT_Admissao"));
        p.setDT_Demissao (cursor.getString ("DT_Demissao"));
        p.setVL_Salario (cursor.getDouble ("VL_Salario"));
        p.setVL_Limite_Abastecimento (cursor.getDouble ("VL_Limite_Abastecimento"));
        p.setHR_Entrada_Manha (cursor.getString ("HR_Entrada_Manha"));
        p.setHR_Saida_Manha (cursor.getString ("HR_Saida_Manha"));
        p.setHR_Entrada_Tarde (cursor.getString ("HR_Entrada_Tarde"));
        p.setHR_Saida_Tarde (cursor.getString ("HR_Saida_Tarde"));
        p.setDM_Estabilidade (cursor.getString ("DM_Estabilidade"));
        p.setNM_Tipo_Estabilidade (cursor.getString ("NM_Tipo_Estabilidade"));
        p.setDM_Estado_Civil (cursor.getString ("DM_Estado_Civil"));
        p.setNM_Nacionalidade (cursor.getString ("NM_Nacionalidade"));
        p.setNM_Conjuge (cursor.getString ("NM_Conjuge"));
        p.setNR_Dependentes (cursor.getInt ("NR_Dependentes"));
        p.setDM_Dependentes (cursor.getString ("DM_Dependentes"));
        p.setNR_Banco (cursor.getString ("NR_Banco"));
        p.setNR_Agencia (cursor.getString ("NR_Agencia"));
        p.setNR_Conta_Bancaria (cursor.getString ("NR_Conta_Bancaria"));
        p.setDM_Instrucao (cursor.getString ("DM_Instrucao"));

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

  public static double  getVL_Limite_Abastecimento (String oid_Pessoa) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    double vl_Limite_Abastecimento=0;
    double vl_Abastecimento_Mes=0;

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

    try {
      String sql=" SELECT funcionarios.vl_Limite_Abastecimento " +
                 " FROM Funcionarios  " +
                 " WHERE  Funcionarios.OID_Pessoa = '" +oid_Pessoa +"'";

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (sql);

      while (cursor.next ()) {
        vl_Limite_Abastecimento=cursor.getDouble ("VL_Limite_Abastecimento");
      }
      if (vl_Limite_Abastecimento>0){
        String dia = Data.getDataDMY()+"     ";
        String data_ini="01/" + dia.substring(3,10);
        // System.out.println("data_ini->>"+data_ini);
        sql= " SELECT SUM (vl_previsto) as vl_abastecimento "  +
             " FROM   Ordens_Compras " +
             " WHERE  Ordens_Compras.OID_Pessoa_Funcionario = '" +oid_Pessoa +"'"  +
             " AND    Ordens_Compras.dt_ordem_compra >= '" +data_ini +"'";

        // System.out.println("sql->>"+sql);

         cursor = stmt.executeQuery (sql);
         while (cursor.next ()) {
           vl_Abastecimento_Mes=cursor.getDouble ("vl_abastecimento");
         }
        // System.out.println("vl_Abastecimento_Mes->>"+vl_Abastecimento_Mes);
        vl_Limite_Abastecimento=vl_Limite_Abastecimento-vl_Abastecimento_Mes;
      }

      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return vl_Limite_Abastecimento;
  }


  public static final FuncionarioBean getByRecord (HttpServletRequest request) throws Exception {
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

    FuncionarioBean p = new FuncionarioBean ();
    try {
      String oid_Pessoa = request.getParameter ("oid_Pessoa");
      String oid_Funcionario = request.getParameter ("oid_Funcionario");
      String NR_CNPJ_CPF = request.getParameter ("FT_NR_CNPJ_CPF");

      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT funcionarios.*,      ");
      buff.append (" Pessoas.NM_Razao_Social,   ");
      buff.append (" Pessoas.NR_CNPJ_CPF        ");
      buff.append (" FROM                       ");
      buff.append (" Funcionarios,              ");
      buff.append (" Pessoas                    ");
      buff.append (" WHERE Funcionarios.OID_Pessoa = Pessoas.OID_Pessoa ");
      if (JavaUtil.doValida (oid_Pessoa)) {
        buff.append (" AND   Funcionarios.OID_Pessoa = '");
        buff.append (oid_Pessoa);
        buff.append ("'");
      }
      if (JavaUtil.doValida (oid_Funcionario)) {
        buff.append (" AND   Funcionarios.OID_Funcionario = '");
        buff.append (oid_Funcionario);
        buff.append ("'");
      }
      if (JavaUtil.doValida (NR_CNPJ_CPF)) {
        buff.append (" AND   Pessoas.NR_CNPJ_CPF = '");
        buff.append (NR_CNPJ_CPF);
        buff.append ("'");
      }

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID_Funcionario (cursor.getString ("OID_Funcionario"));
        p.setOID_Pessoa (cursor.getString ("OID_Pessoa"));
        p.setNM_Razao_Social (cursor.getString ("NM_Razao_Social"));
        p.setNR_CNPJ_CPF (cursor.getString ("NR_CNPJ_CPF"));
        p.setNM_Apelido (cursor.getString ("NM_Apelido"));
        p.setNR_Registro_Geral (cursor.getString ("NR_Registro_Geral"));
        p.setNM_Orgao_Emissor (cursor.getString ("NM_Orgao_Emissor"));
        p.setNR_CNH (cursor.getString ("NR_CNH"));
        p.setDT_Vencimento_CNH (cursor.getString ("DT_Vencimento_CNH"));
        p.setNM_Categoria (cursor.getString ("NM_Categoria"));
        p.setDM_Situacao (cursor.getString ("DM_Situacao"));
        p.setTX_Observacao (cursor.getString ("TX_Observacao"));
        p.setNR_PIS (cursor.getString ("NR_PIS"));
        p.setNR_INSS (cursor.getString ("NR_INSS"));
        p.setNR_Telefone (cursor.getString ("NR_Telefone"));
        p.setNR_Celular (cursor.getString ("NR_Celular"));
        p.setTX_Referencia (cursor.getString ("TX_Referencia"));
        p.setNM_Pai (cursor.getString ("NM_Pai"));
        p.setNM_Mae (cursor.getString ("NM_Mae"));
        p.setNR_Registro (cursor.getString ("NR_Registro"));
        p.setDT_Nascimento (cursor.getString ("DT_Nascimento"));
        p.setDM_Tipo_Funcao (cursor.getString ("DM_Tipo_Funcao"));
        p.setDT_Vencimento_Aval_Med (cursor.getString ("DT_Vencimento_Aval_Med"));
        p.setPE_Comissao (cursor.getDouble ("PE_Comissao"));
        p.setNR_CTPS (cursor.getString ("NR_CTPS"));
        p.setNM_Serie_CTPS (cursor.getString ("NM_Serie_CTPS"));
        p.setDT_Aval_Med (cursor.getString ("DT_Aval_Med"));
        p.setDT_Admissao (cursor.getString ("DT_Admissao"));
        p.setDT_Demissao (cursor.getString ("DT_Demissao"));
        p.setVL_Salario (cursor.getDouble ("VL_Salario"));
        p.setVL_Limite_Abastecimento (cursor.getDouble ("VL_Limite_Abastecimento"));
        p.setHR_Entrada_Manha (cursor.getString ("HR_Entrada_Manha"));
        p.setHR_Saida_Manha (cursor.getString ("HR_Saida_Manha"));
        p.setHR_Entrada_Tarde (cursor.getString ("HR_Entrada_Tarde"));
        p.setHR_Saida_Tarde (cursor.getString ("HR_Saida_Tarde"));
        p.setDM_Estabilidade (cursor.getString ("DM_Estabilidade"));
        p.setNM_Tipo_Estabilidade (cursor.getString ("NM_Tipo_Estabilidade"));
        p.setDM_Estado_Civil (cursor.getString ("DM_Estado_Civil"));
        p.setNM_Nacionalidade (cursor.getString ("NM_Nacionalidade"));
        p.setNM_Conjuge (cursor.getString ("NM_Conjuge"));
        p.setNR_Dependentes (cursor.getInt ("NR_Dependentes"));
        p.setDM_Dependentes (cursor.getString ("DM_Dependentes"));
        p.setNR_Banco (cursor.getString ("NR_Banco"));
        p.setNR_Agencia (cursor.getString ("NR_Agencia"));
        p.setNR_Conta_Bancaria (cursor.getString ("NR_Conta_Bancaria"));
        p.setDM_Instrucao (cursor.getString ("DM_Instrucao"));

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

  public static final List Lista (HttpServletRequest request) throws Exception {
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

    List Funcionario_Lista = new ArrayList ();
    try {
      String NM_Razao_Social = request.getParameter ("FT_NM_Razao_Social");
      String NM_Apelido = request.getParameter ("FT_NM_Apelido");
      String NR_CNPJ_CPF = request.getParameter ("FT_NR_CNPJ_CPF");

      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT funcionarios.*,      ");
      buff.append (" Pessoas.NM_Razao_Social,   ");
      buff.append (" Pessoas.NR_CNPJ_CPF        ");
      buff.append (" FROM                       ");
      buff.append (" Funcionarios,              ");
      buff.append (" Pessoas                    ");
      buff.append (" WHERE Funcionarios.OID_Pessoa = Pessoas.OID_Pessoa ");

      if (JavaUtil.doValida (NM_Razao_Social)) {
        buff.append (" AND   Pessoas.NM_Razao_Social LIKE '");
        buff.append (NM_Razao_Social);
        buff.append ("%'");
      }
      if (JavaUtil.doValida (NM_Apelido)) {
        buff.append (" AND   Funcionarios.NM_Apelido LIKE '%");
        buff.append (NM_Apelido);
        buff.append ("'");
      }
      if (JavaUtil.doValida (NR_CNPJ_CPF)) {
        buff.append (" AND   Pessoas.NR_CNPJ_CPF = '");
        buff.append (NR_CNPJ_CPF);
        buff.append ("'");
      }
      if (JavaUtil.doValida (NM_Apelido)) {
        buff.append (" ORDER BY Funcionarios.NM_Apelido, Pessoas.NM_Razao_Social ");
      }
      else {
        buff.append (" ORDER BY Pessoas.NM_Razao_Social, Funcionarios.NM_Apelido ");
      }

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        FuncionarioBean p = new FuncionarioBean ();
        p.setOID_Funcionario (cursor.getString ("OID_Funcionario"));
        p.setOID_Pessoa (cursor.getString ("OID_Pessoa"));
        p.setNM_Razao_Social (cursor.getString ("NM_Razao_Social"));
        p.setNR_CNPJ_CPF (cursor.getString ("NR_CNPJ_CPF"));
        p.setNM_Apelido (cursor.getString ("NM_Apelido"));
        p.setNR_Registro_Geral (cursor.getString ("NR_Registro_Geral"));
        p.setNM_Orgao_Emissor (cursor.getString ("NM_Orgao_Emissor"));
        p.setNR_CNH (cursor.getString ("NR_CNH"));
        p.setDT_Vencimento_CNH (cursor.getString ("DT_Vencimento_CNH"));
        p.setNM_Categoria (cursor.getString ("NM_Categoria"));
        p.setDM_Situacao (cursor.getString ("DM_Situacao"));
        p.setTX_Observacao (cursor.getString ("TX_Observacao"));
        p.setNR_PIS (cursor.getString ("NR_PIS"));
        p.setNR_INSS (cursor.getString ("NR_INSS"));
        p.setNR_Telefone (cursor.getString ("NR_Telefone"));
        p.setNR_Celular (cursor.getString ("NR_Celular"));
        p.setTX_Referencia (cursor.getString ("TX_Referencia"));
        p.setNM_Pai (cursor.getString ("NM_Pai"));
        p.setNM_Mae (cursor.getString ("NM_Mae"));
        p.setNR_Registro (cursor.getString ("NR_Registro"));
        p.setDT_Nascimento (cursor.getString ("DT_Nascimento"));
        p.setDM_Tipo_Funcao (cursor.getString ("DM_Tipo_Funcao"));
        p.setDT_Vencimento_Aval_Med (cursor.getString ("DT_Vencimento_Aval_Med"));
        p.setPE_Comissao (cursor.getDouble ("PE_Comissao"));
        p.setNR_CTPS (cursor.getString ("NR_CTPS"));
        p.setNM_Serie_CTPS (cursor.getString ("NM_Serie_CTPS"));
        p.setDT_Aval_Med (cursor.getString ("DT_Aval_Med"));
        p.setDT_Admissao (cursor.getString ("DT_Admissao"));
        p.setDT_Demissao (cursor.getString ("DT_Demissao"));
        p.setVL_Salario (cursor.getDouble ("VL_Salario"));
        p.setHR_Entrada_Manha (cursor.getString ("HR_Entrada_Manha"));
        p.setHR_Saida_Manha (cursor.getString ("HR_Saida_Manha"));
        p.setHR_Entrada_Tarde (cursor.getString ("HR_Entrada_Tarde"));
        p.setHR_Saida_Tarde (cursor.getString ("HR_Saida_Tarde"));
        p.setDM_Estabilidade (cursor.getString ("DM_Estabilidade"));
        p.setNM_Tipo_Estabilidade (cursor.getString ("NM_Tipo_Estabilidade"));
        p.setDM_Estado_Civil (cursor.getString ("DM_Estado_Civil"));
        p.setNM_Nacionalidade (cursor.getString ("NM_Nacionalidade"));
        p.setNM_Conjuge (cursor.getString ("NM_Conjuge"));
        p.setNR_Dependentes (cursor.getInt ("NR_Dependentes"));
        p.setDM_Dependentes (cursor.getString ("DM_Dependentes"));
        p.setNR_Banco (cursor.getString ("NR_Banco"));
        p.setNR_Agencia (cursor.getString ("NR_Agencia"));
        p.setNR_Conta_Bancaria (cursor.getString ("NR_Conta_Bancaria"));
        p.setDM_Instrucao (cursor.getString ("DM_Instrucao"));
        Funcionario_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Funcionario_Lista;
  }

  //Metodos do MotoristaBean...
  //Ficaram para ajudar no futuro, impressoes e etc.


  public  byte[] geraRelacaoFuncionarios(HttpServletRequest request, HttpServletResponse Response)throws Excecoes{

    byte[] b = null;

    try {
      String DM_Situacao = request.getParameter ("FT_DM_Situacao");
      String DM_Relatorio = request.getParameter ("FT_DM_Relatorio");
      String DT_Inicial = request.getParameter ("FT_DT_Inicial");
      String DT_Final = request.getParameter ("FT_DT_Final");
      String oid_Funcionario = request.getParameter ("oid_Funcionario");

      String sql = null;

      sql =
          "Select * from Pessoas, Funcionarios, Cidades, Estados, Regioes_Estados " +
          "where Pessoas.OID_Pessoa = Funcionarios.OID_Pessoa " +
          "and Pessoas.OID_Cidade = Cidades.OID_Cidade " +
          "and Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado " +
          "and Regioes_Estados.OID_Estado = Estados.OID_Estado ";

      if (oid_Funcionario != null && !oid_Funcionario.equals ("") &&
          !oid_Funcionario.equals ("") && !oid_Funcionario.equals ("null")) {
        sql += " and Funcionarios.OID_Pessoa = '" + oid_Funcionario + "'";
      }else {
        if (DM_Situacao != null && !DM_Situacao.equals ("T") &&
            !DM_Situacao.equals ("") && !DM_Situacao.equals ("null")) {
          sql += " and Funcionarios.DM_Situacao = '" + DM_Situacao + "'";
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

      FuncionarioRL motRL = new FuncionarioRL ();
      b = motRL.geraRelacaoFuncionarios (cursor , DM_Situacao, DM_Relatorio, DT_Inicial, DT_Final);

  
    }
    catch (Exception exc) {
      exc.printStackTrace ();
    }
    return b;
  }

  public void setVL_Limite_Abastecimento (double VL_Limite_Abastecimento) {
    this.VL_Limite_Abastecimento = VL_Limite_Abastecimento;
  }

  public double getVL_Limite_Abastecimento () {
    return VL_Limite_Abastecimento;
  }

}
