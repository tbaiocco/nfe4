package com.master.root;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.http.*;

import auth.*;
import br.cte.model.Empresa;

import com.master.util.*;

public class UnidadeBean
    implements Serializable {

  private String NM_Sigla;
  private String CD_Unidade;
  private String CD_Unidade_Contabil;
  private String CD_Unidade_Empresa;
  private String NM_Sigla_Unidade_Cobranca;
  private String CD_Unidade_Cobranca;
  private String NM_Sigla_Unidade_Fiscal;
  private String CD_Unidade_Fiscal;
  private String Usuario_Stamp;
  private String Dt_Stamp;
  private String Dm_Stamp;
  private int OID_Empresa;
  private String CD_Empresa;
  private String NM_Empresa;
  private int OID_Moeda;
  private String CD_Moeda;
  private String NM_Moeda;
  private String NM_Fantasia;
  private String NM_Razao_Social;
  private String NM_Fantasia_Unidade_Fiscal;
  private String NM_Fantasia_Unidade_Cobranca;
  private String NR_CNPJ_CPF;
  private String OID_Pessoa;
  private String OID_Pessoa_Fiscal;
  private int OID_Unidade;
  private int OID_Unidade_Cobranca;
  private int OID_Unidade_Fiscal;
  private String DM_Situacao;
  private int OID_Subregiao;
  private String DM_Calcula_Frete;
  private String DM_Tipo_Seguro;
  private String DM_Tipo_ICMS_Unidade;
  private String OID_Conta_Corrente;
  private int OID_Carteira;
  private int OID_Deposito;

  private String DT_Lancamento_Custo;
  private String NR_Permiso_Complementar;
  private String NR_Permiso_Originario;
  private String NR_Poliza_Seguro;
  private String NM_Cidade;
  private String NM_Estado;
  private String NM_Regiao_Estado;
  private String NM_Regiao_Pais;
  private String NM_Pais;
  private String NR_Regime_ICMS_Especial;
  private String OID_Emissor_Padrao;
  private String DM_Tipo_Documento_Padrao;
  private String DM_Faturar;

  private double PE_ISSQN;

  public UnidadeBean () {
    OID_Pessoa = "";
  }

  public String getNR_CNPJ_CPF() {
	return NR_CNPJ_CPF;
}

public void setNR_CNPJ_CPF(String nr_cnpj_cpf) {
	NR_CNPJ_CPF = nr_cnpj_cpf;
}

public String getNM_Sigla () {
    return NM_Sigla;
  }

  public void setNM_Sigla (String NM_Sigla) {
    this.NM_Sigla = NM_Sigla;
  }

  public String getCD_Unidade () {
    return CD_Unidade;
  }

  public void setCD_Unidade (String CD_Unidade) {
    this.CD_Unidade = CD_Unidade;
  }

  public String getCD_Unidade_Contabil () {
    return CD_Unidade_Contabil;
  }

  public void setCD_Unidade_Contabil (String CD_Unidade_Contabil) {
    this.CD_Unidade_Contabil = CD_Unidade_Contabil;
  }

  public int getOID_Empresa () {
    return OID_Empresa;
  }

  public void setOID_Empresa (int OID_Empresa) {
    this.OID_Empresa = OID_Empresa;
  }

  public String getNM_Empresa () {
    return NM_Empresa;
  }

  public void setNM_Empresa (String NM_Empresa) {
    this.NM_Empresa = NM_Empresa;
  }

  public String getCD_Empresa () {
    return CD_Empresa;
  }

  public void setCD_Empresa (String CD_Empresa) {
    this.CD_Empresa = CD_Empresa;
  }

  public String getCD_Unidade_Empresa () {
    return CD_Unidade_Empresa;
  }

  public void setCD_Unidade_Empresa (String CD_Unidade_Empresa) {
    this.CD_Unidade_Empresa = CD_Unidade_Empresa;
  }

  public int getOID_Moeda () {
    return OID_Moeda;
  }

  public void setOID_Moeda (int OID_Moeda) {
    this.OID_Moeda = OID_Moeda;
  }

  public String getNM_Moeda () {
    return NM_Moeda;
  }

  public void setNM_Moeda (String NM_Moeda) {
    this.NM_Moeda = NM_Moeda;
  }

  public String getCD_Moeda () {
    return CD_Moeda;
  }

  public void setCD_Moeda (String CD_Moeda) {
    this.CD_Moeda = CD_Moeda;
  }

  public String getOID_Pessoa () {
    return OID_Pessoa;
  }

  public void setOID_Pessoa (String OID_Pessoa) {
    this.OID_Pessoa = OID_Pessoa;
  }

  public int getOID_Unidade () {
    return OID_Unidade;
  }

  public void setOID_Unidade (int OID_Unidade) {
    this.OID_Unidade = OID_Unidade;
  }

  public int getOID_Unidade_Cobranca () {
    return OID_Unidade_Cobranca;
  }

  public void setOID_Unidade_Cobranca (int OID_Unidade_Cobranca) {
    this.OID_Unidade_Cobranca = OID_Unidade_Cobranca;
  }

  public String getNM_Sigla_Unidade_Cobranca () {
    return NM_Sigla_Unidade_Cobranca;
  }

  public void setNM_Sigla_Unidade_Cobranca (String NM_Sigla_Unidade_Cobranca) {
    this.NM_Sigla_Unidade_Cobranca = NM_Sigla_Unidade_Cobranca;
  }

  public String getCD_Unidade_Cobranca () {
    return CD_Unidade_Cobranca;
  }

  public void setCD_Unidade_Cobranca (String CD_Unidade_Cobranca) {
    this.CD_Unidade_Cobranca = CD_Unidade_Cobranca;
  }

  public int getOID_Unidade_Fiscal () {
    return OID_Unidade_Fiscal;
  }

  public void setOID_Unidade_Fiscal (int OID_Unidade_Fiscal) {
    this.OID_Unidade_Fiscal = OID_Unidade_Fiscal;
  }

  public String getNM_Sigla_Unidade_Fiscal () {
    return NM_Sigla_Unidade_Fiscal;
  }

  public void setNM_Sigla_Unidade_Fiscal (String NM_Sigla_Unidade_Fiscal) {
    this.NM_Sigla_Unidade_Fiscal = NM_Sigla_Unidade_Fiscal;
  }

  public String getCD_Unidade_Fiscal () {
    return CD_Unidade_Fiscal;
  }

  public void setCD_Unidade_Fiscal (String CD_Unidade_Fiscal) {
    this.CD_Unidade_Fiscal = CD_Unidade_Fiscal;
  }

  public String getNM_Fantasia () {
    return NM_Fantasia;
  }

  public void setNM_Fantasia (String NM_Fantasia) {
    this.NM_Fantasia = NM_Fantasia;
  }

  public String getNM_Fantasia_Unidade_Cobranca () {
    return NM_Fantasia_Unidade_Cobranca;
  }

  public void setNM_Fantasia_Unidade_Cobranca (String NM_Fantasia_Unidade_Cobranca) {
    this.NM_Fantasia_Unidade_Cobranca = NM_Fantasia_Unidade_Cobranca;
  }

  public String getNM_Fantasia_Unidade_Fiscal () {
    return NM_Fantasia_Unidade_Fiscal;
  }

  public void setNM_Fantasia_Unidade_Fiscal (String NM_Fantasia_Unidade_Fiscal) {
    this.NM_Fantasia_Unidade_Fiscal = NM_Fantasia_Unidade_Fiscal;
  }

  public void setDM_Situacao (String DM_Situacao) {
	    this.DM_Situacao = DM_Situacao;
	  }

	  public String getDM_Situacao () {
	    return DM_Situacao;
	  }

	  public void setOID_Subregiao (int OID_Subregiao) {
	    this.OID_Subregiao = OID_Subregiao;
	  }

	  public int getOID_Subregiao () {
	    return OID_Subregiao;
	  }

	  public void setDM_Calcula_Frete (String DM_Calcula_Frete) {
	    this.DM_Calcula_Frete = DM_Calcula_Frete;
	  }

	  public String getDM_Calcula_Frete () {
	    return DM_Calcula_Frete;
	  }

	  public void setDM_Tipo_Seguro (String DM_Tipo_Seguro) {
	    this.DM_Tipo_Seguro = DM_Tipo_Seguro;
	  }

	  public String getDM_Tipo_Seguro () {
	    return DM_Tipo_Seguro;
	  }

	  public String getDT_Lancamento_Custo () {
	    FormataDataBean DataFormatada = new FormataDataBean ();
	    DataFormatada.setDT_FormataData (DT_Lancamento_Custo);
	    DT_Lancamento_Custo = DataFormatada.getDT_FormataData ();
	    if (DT_Lancamento_Custo.length () < 3) {
	      DT_Lancamento_Custo = null;
	    }
	    return DT_Lancamento_Custo;
	  }

	  public void setDT_Lancamento_Custo (String vcto) {
	    DT_Lancamento_Custo = vcto;
	  }

	  public String getNR_Permiso_Complementar () {
	    return NR_Permiso_Complementar;
	  }

	  public void setNR_Permiso_Complementar (String permiso_Complementar) {
	    NR_Permiso_Complementar = permiso_Complementar;
	  }

	  public String getNR_Permiso_Originario () {
	    return NR_Permiso_Originario;
	  }

	  public void setNR_Permiso_Originario (String permiso_Originario) {
	    NR_Permiso_Originario = permiso_Originario;
	  }

	  public String getNR_Poliza_Seguro () {
	    return NR_Poliza_Seguro;
	  }

	  public void setNR_Poliza_Seguro (String poliza_Seguro) {
	    NR_Poliza_Seguro = poliza_Seguro;
	  }

	  public String getNM_Razao_Social () {
	    return NM_Razao_Social;
	  }

	  public void setNM_Razao_Social (String razao_Social) {
	    NM_Razao_Social = razao_Social;
	  }

	  public String getOID_Conta_Corrente () {
	    return OID_Conta_Corrente;
	  }

	  public void setOID_Conta_Corrente (String conta_Corrente) {
	    OID_Conta_Corrente = conta_Corrente;
	  }

	  public void setNM_Cidade (String NM_Cidade) {
	    this.NM_Cidade = NM_Cidade;
	  }

	  public String getNM_Cidade () {
	    return NM_Cidade;
	  }

	  public void setNM_Estado (String NM_Estado) {
	    this.NM_Estado = NM_Estado;
	  }

	  public String getNM_Estado () {
	    return NM_Estado;
	  }

	  public void setNM_Regiao_Estado (String NM_Regiao_Estado) {
	    this.NM_Regiao_Estado = NM_Regiao_Estado;
	  }

	  public String getNM_Regiao_Estado () {
	    return NM_Regiao_Estado;
	  }

	  public void setNM_Regiao_Pais (String NM_Regiao_Pais) {
	    this.NM_Regiao_Pais = NM_Regiao_Pais;
	  }

	  public String getNM_Regiao_Pais () {
	    return NM_Regiao_Pais;
	  }

	  public void setNM_Pais (String NM_Pais) {
	    this.NM_Pais = NM_Pais;
	  }

	  public String getNM_Pais () {
	    return NM_Pais;
	  }

	  public int getOID_Carteira () {
	    return OID_Carteira;
	  }

	  public void setOID_Carteira (int carteira) {
	    OID_Carteira = carteira;
	  }

	  public void setNR_Regime_ICMS_Especial (String NR_Regime_ICMS_Especial) {
	    this.NR_Regime_ICMS_Especial = NR_Regime_ICMS_Especial;
	  }

	  public String getNR_Regime_ICMS_Especial () {
	    return NR_Regime_ICMS_Especial;
	  }

	  public int getOID_Deposito () {
	    return OID_Deposito;
	  }

	  public void setOID_Deposito (int OID_Deposito) {
	    this.OID_Deposito = OID_Deposito;
	  }

	  public void setOID_Emissor_Padrao (String OID_Emissor_Padrao) {

	    this.OID_Emissor_Padrao = OID_Emissor_Padrao;
	  }

	  public String getOID_Emissor_Padrao () {

	    return OID_Emissor_Padrao;
	  }

	  public void setDM_Tipo_Documento_Padrao (String DM_Tipo_Documento_Padrao) {
	    this.DM_Tipo_Documento_Padrao = DM_Tipo_Documento_Padrao;
	  }

	  public String getDM_Tipo_Documento_Padrao () {
	    return DM_Tipo_Documento_Padrao;
	  }

  /*
   * ---------------- Bloco Padr�o para Todas Classes ------------------
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
    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    /*
     * Gera um novo c�digo (OID)
     */
    try {
      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery ("SELECT MAX(OID_Unidade) FROM Unidades");

      while (cursor.next ()) {
        int OID_Unidade = cursor.getInt (1);
        setOID_Unidade (OID_Unidade + 1);
      }
      cursor.close ();
      stmt.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    String OID_Unidade_Cobranca = String.valueOf (getOID_Unidade_Cobranca ());

    if (OID_Unidade_Cobranca.equals ("0")) {
      setOID_Unidade_Cobranca (OID_Unidade);
    }

    String OID_Unidade_Fiscal = String.valueOf (getOID_Unidade_Fiscal ());

    if (OID_Unidade_Fiscal.equals ("0")) {
      setOID_Unidade_Fiscal (OID_Unidade);
    }
    /*
     * Define o insert.
     */

    StringBuffer buff = new StringBuffer ();
    buff.append ("INSERT INTO Unidades ");
    buff.append (" (OID_Unidade,            ");
    buff.append ("  OID_Empresa,            ");
    buff.append ("  OID_Moeda,              ");
    buff.append ("  OID_Pessoa,             ");
    buff.append ("  OID_Conta_Corrente,     ");
    buff.append ("  OID_Unidade_Cobranca,   ");
    buff.append ("  OID_Unidade_Fiscal, ");
    buff.append ("  NM_Sigla,               ");
    buff.append ("  CD_Unidade,             ");
    buff.append ("  CD_Unidade_Empresa,     ");
    buff.append ("  Dt_Stamp,               ");
    buff.append ("  Usuario_Stamp,          ");
    buff.append ("  Dm_Stamp,               ");
    buff.append ("  CD_Unidade_Contabil,    ");
    buff.append ("  DM_Situacao,            ");
    buff.append ("  DM_Calcula_Frete,       ");
    buff.append ("  DM_Tipo_Seguro,         ");
    buff.append (" dt_lancamento_custo,         ");
    buff.append (" NR_Permiso_complementar, ");
    buff.append (" NR_Poliza_seguro,        ");
    buff.append (" NR_Permiso_originario,   ");
    buff.append (" OID_Deposito,            ");
    buff.append (" OID_Emissor_Padrao,      ");
    buff.append (" pe_issqn_padrao,                ");
    buff.append (" OID_Carteira )           ");
    buff.append ("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
    /*
     * Define os dados do SQL e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt = conn.prepareStatement (buff.toString ());
      pstmt.setInt (1 , getOID_Unidade ());
      pstmt.setInt (2 , getOID_Empresa ());
      pstmt.setInt (3 , getOID_Moeda ());
      pstmt.setString (4 , getOID_Pessoa ());
      pstmt.setString (5 , getOID_Conta_Corrente ());
      pstmt.setInt (6 , getOID_Unidade_Cobranca ());
      pstmt.setInt (7 , getOID_Unidade_Fiscal ());
      pstmt.setString (8 , getNM_Sigla ());
      pstmt.setString (9 , getCD_Unidade ());
      pstmt.setString (10 , getCD_Unidade_Empresa ());
      pstmt.setString (11 , getDt_Stamp ());
      pstmt.setString (12 , getUsuario_Stamp ());
      pstmt.setString (13 , getDm_Stamp ());
      pstmt.setString (14 , getCD_Unidade_Contabil ());
      pstmt.setString (15 , "F");
      pstmt.setString (16 , "N");
      pstmt.setString (17 , "E");
      pstmt.setString (18 , getDT_Lancamento_Custo ());
      pstmt.setString (19 , getNR_Permiso_Complementar ());
      pstmt.setString (20 , getNR_Poliza_Seguro ());
      pstmt.setString (21 , getNR_Permiso_Originario ());
      pstmt.setInt (22 , getOID_Deposito ());
      pstmt.setString (23 , getOID_Emissor_Padrao ());
      pstmt.setDouble(24, getPE_ISSQN());
      pstmt.setInt (25 , getOID_Carteira ());
      pstmt.executeUpdate ();
    }
    catch (Exception e) {
      conn.rollback ();
      e.printStackTrace ();
      throw e;
    }
    /*
     * Faz o commit e fecha a conex�o.
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
    Connection conn = null;
    try {
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
    buff.append ("UPDATE Unidades SET         ");
    buff.append ("  OID_Moeda=?,              ");
    buff.append ("  OID_Unidade_Cobranca=?,   ");
    buff.append ("  OID_Unidade_Fiscal=?, ");
    buff.append ("  Dt_Stamp=?,               ");
    buff.append ("  Usuario_Stamp=?,          ");
    buff.append ("  Dm_Stamp=?,               ");
    buff.append ("  CD_Unidade_Contabil=?,    ");
    buff.append ("  oid_subregiao=?,          ");
    buff.append ("  DM_Situacao=?,            ");
    buff.append ("  DM_Calcula_Frete=?,       ");
    buff.append ("  DM_Tipo_Seguro=?,         ");
    buff.append ("  NM_Sigla=?,               ");
    buff.append (" dt_lancamento_custo=?,         ");
    buff.append (" NR_Permiso_Complementar=?, ");
    buff.append (" NR_Poliza_seguro=?,        ");
    buff.append (" NR_Permiso_Originario=?,   ");
    buff.append (" OID_Conta_Corrente=?,       ");
    buff.append (" NR_Regime_ICMS_Especial=?,   ");
    buff.append (" OID_Carteira=?,       ");
    buff.append (" OID_Emissor_Padrao=?,       ");
    buff.append (" DM_Faturar=?,         ");
    buff.append (" DM_Tipo_Documento_Padrao=?,         ");
    buff.append (" DM_Tipo_Icms_Unidade=?,         ");
    buff.append (" pe_issqn_padrao=?,         ");
    buff.append (" OID_Deposito=?       ");
    buff.append ("WHERE OID_Unidade=?");

    /*
     * Define os dados do SQL e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt = conn.prepareStatement (buff.toString ());

      pstmt.setInt (1 , getOID_Moeda ());
      pstmt.setInt (2 , getOID_Unidade_Cobranca ());
      pstmt.setInt (3 , getOID_Unidade_Fiscal ());
      pstmt.setString (4 , getDt_Stamp ());
      pstmt.setString (5 , getUsuario_Stamp ());
      pstmt.setString (6 , getDm_Stamp ());
      pstmt.setString (7 , getCD_Unidade_Contabil ());
      pstmt.setInt (8 , getOID_Subregiao ());
      pstmt.setString (9 , getDM_Situacao ());
      pstmt.setString (10 , getDM_Calcula_Frete ());
      pstmt.setString (11 , getDM_Tipo_Seguro ());
      pstmt.setString (12 , getNM_Sigla ());
      pstmt.setString (13 , getDT_Lancamento_Custo ());
      pstmt.setString (14 , getNR_Permiso_Complementar ());
      pstmt.setString (15 , getNR_Poliza_Seguro ());
      pstmt.setString (16 , getNR_Permiso_Originario ());
      pstmt.setString (17 , getOID_Conta_Corrente ());
      pstmt.setString (18 , getNR_Regime_ICMS_Especial ());
      pstmt.setInt (19 , getOID_Carteira ());
      pstmt.setString (20 , getOID_Emissor_Padrao ());
      pstmt.setString (21 , getDM_Faturar ());
      pstmt.setString (22 , getDM_Tipo_Documento_Padrao ());
      pstmt.setString (23 , getDM_Tipo_ICMS_Unidade ());
      pstmt.setDouble(24 , getPE_ISSQN());
      pstmt.setInt (25 , getOID_Deposito ());
      pstmt.setInt (26 , getOID_Unidade ());
      pstmt.executeUpdate ();
    }
    catch (Exception e) {
      conn.rollback ();
      e.printStackTrace ();
      throw e;
    }
    /*
     * Faz o commit e fecha a conex�o.
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
    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("DELETE FROM Unidades ");
      buff.append ("WHERE OID_Unidade=?");

      PreparedStatement pstmt = conn.prepareStatement (buff.toString ());
      pstmt.setInt (1 , getOID_Unidade ());
      pstmt.executeUpdate ();

      StringBuffer buff2 = new StringBuffer ();
      buff2.append ("DELETE FROM Parametros_Filiais ");
      buff2.append ("WHERE OID_Unidade=?");

      PreparedStatement pstmt2 = conn.prepareStatement (buff2.toString ());
      pstmt2.setInt (1 , getOID_Unidade ());
      pstmt2.executeUpdate ();

      StringBuffer buff3 = new StringBuffer ();
      buff3.append ("DELETE FROM Pessoas ");
      buff3.append ("WHERE OID_Pessoa=?");

      PreparedStatement pstmt3 = conn.prepareStatement (buff3.toString ());
      pstmt3.setString (1 , getOID_Pessoa ());
      //pstmt3.executeUpdate();

    }
    catch (Exception e) {
      conn.rollback ();
      e.printStackTrace ();
      throw e;
    }

    /*
     * Faz o commit e fecha a conex�o.
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

  public void dssselete () throws Exception {

    Connection conn = null;

    try {
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
    try {

      StringBuffer buff = new StringBuffer ();
      PreparedStatement pstmt = conn.prepareStatement (buff.toString ());

      buff.delete (0 , buff.length ());
      buff.append ("DELETE FROM Parametros_Filiais ");
      buff.append ("WHERE OID_Unidade=?");
      pstmt.setInt (1 , getOID_Unidade ());
      pstmt.executeUpdate ();

      buff.delete (0 , buff.length ());
      buff.append ("DELETE FROM Unidades ");
      buff.append ("WHERE OID_Unidade=?");
      pstmt.setInt (1 , getOID_Unidade ());
      pstmt.executeUpdate ();

      buff.delete (0 , buff.length ());
      buff.append ("DELETE FROM Pessoas ");
      buff.append ("WHERE OID_Pessoa=?");
      pstmt.setString (1 , getOID_Pessoa ());
      pstmt.executeUpdate ();

    }
    catch (Exception e) {
      conn.rollback ();
      e.printStackTrace ();
      throw e;
    }
    try {
      conn.commit ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
  }

  public static final UnidadeBean getByOID_Unidade (Empresa empresa, int OID_Unidade) throws Exception {
    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB (empresa);
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    UnidadeBean p = new UnidadeBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT                             ");
      buff.append (" Unidades.OID_Unidade,             ");
      buff.append (" Unidades.OID_Empresa,             ");
      buff.append (" Unidades.OID_Moeda,               ");
      buff.append (" Unidades.OID_Pessoa,              ");
      buff.append (" Unidades.OID_Conta_Corrente,      ");
      buff.append (" Unidades.OID_Unidade_Cobranca,    ");
      buff.append (" Unidades.OID_Unidade_Fiscal,  ");
      buff.append (" Unidades.NM_Sigla,                ");
      buff.append (" Unidades.CD_Unidade,              ");
      buff.append (" Unidades.CD_Unidade_Empresa,      ");
      buff.append (" Unidade_Fiscal.NM_Sigla,      ");
      buff.append (" Unidade_Fiscal.CD_Unidade,    ");
      buff.append (" Unidade_Cobranca.NM_Sigla,        ");
      buff.append (" Unidade_Cobranca.CD_Unidade,      ");
      buff.append (" Empresas.NM_Empresa,              ");
      buff.append (" Empresas.CD_Empresa,              ");
      buff.append (" Moedas.NM_Moeda,                  ");
      buff.append (" Moedas.CD_Moeda,                  ");
      buff.append (" Pessoas.NM_Fantasia,              ");
      buff.append (" Pessoas.NM_Razao_Social,          ");
      buff.append (" Pessoas.NR_CNPJ_CPF,          ");
      buff.append (" Pessoa_Fiscal.NM_Fantasia,    ");
      buff.append (" Pessoa_Cobranca.NM_Fantasia,      ");
      buff.append (" Unidades.CD_UNIDADE_CONTABIL,     ");
      buff.append (" Unidades.oid_subregiao,        	");
      buff.append (" Unidades.DM_Situacao,             ");
      buff.append (" Unidades.DM_Tipo_Seguro,          ");
      buff.append (" Unidades.DM_Tipo_Documento_Padrao,          ");
      buff.append (" Unidades.DM_Calcula_Frete,        ");
      buff.append (" Unidades.OID_Deposito,        ");
      buff.append (" Unidades.OID_Emissor_Padrao,        ");
      buff.append (" Unidades.OID_Carteira,         ");
      buff.append (" Unidades.pe_issqn_padrao    ");
      buff.append (" ,Unidade_Fiscal.oid_pessoa as oid_pessoa_fiscal    ");
      buff.append ("FROM ");
      buff.append (" Unidades,                    ");
      buff.append (" Unidades Unidade_Fiscal, ");
      buff.append (" Unidades Unidade_Cobranca,   ");
      buff.append (" Empresas,                    ");
      buff.append (" Moedas,                      ");
      buff.append (" Pessoas,                     ");
      buff.append (" Pessoas Pessoa_Fiscal,   ");
      buff.append (" Pessoas Pessoa_Cobranca      ");
      buff.append (" WHERE Unidades.OID_Unidade_Fiscal = Unidade_Fiscal.OID_Unidade ");
      buff.append (" AND   Unidades.OID_Unidade_Cobranca   = Unidade_Cobranca.OID_Unidade   ");
      buff.append (" AND   Unidades.OID_Empresa            = Empresas.OID_Empresa           ");
      buff.append (" AND   Unidades.OID_Moeda              = Moedas.OID_Moeda               ");
      buff.append (" AND   Unidades.OID_Pessoa             = Pessoas.OID_Pessoa             ");
      buff.append (" AND   Unidade_Fiscal.OID_Pessoa   = Pessoa_Fiscal.OID_Pessoa   ");
      buff.append (" AND   Unidade_Cobranca.OID_Pessoa      = Pessoa_Cobranca.OID_Pessoa     ");
      buff.append (" AND   Unidades.OID_Unidade = ");
      buff.append (OID_Unidade);

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID_Unidade (cursor.getInt (1));
        p.setOID_Empresa (cursor.getInt (2));
        p.setOID_Moeda (cursor.getInt (3));
        p.setOID_Pessoa (cursor.getString (4));
        p.setOID_Conta_Corrente (cursor.getString (5));
        p.setOID_Unidade_Cobranca (cursor.getInt (6));
        p.setOID_Unidade_Fiscal (cursor.getInt (7));
        p.setNM_Sigla (cursor.getString (8));
        p.setCD_Unidade (cursor.getString (9));
        p.setCD_Unidade_Empresa (cursor.getString (10));
        p.setNM_Sigla_Unidade_Fiscal (cursor.getString (11));
        p.setCD_Unidade_Fiscal (cursor.getString (12));
        p.setNM_Sigla_Unidade_Cobranca (cursor.getString (13));
        p.setCD_Unidade_Cobranca (cursor.getString (14));
        p.setNM_Empresa (cursor.getString (15));
        p.setCD_Empresa (cursor.getString (16));
        p.setNM_Moeda (cursor.getString (17));
        p.setCD_Moeda (cursor.getString (18));
        p.setNM_Fantasia (JavaUtil.getValueDef (cursor.getString (19) , cursor.getString (20)));
        p.setNM_Razao_Social (cursor.getString (20));
        p.setNR_CNPJ_CPF (cursor.getString (21));
        p.setNM_Fantasia_Unidade_Fiscal (cursor.getString (22));
        p.setNM_Fantasia_Unidade_Cobranca (cursor.getString (23));
        p.setCD_Unidade_Contabil (cursor.getString (24));
        p.setOID_Subregiao (cursor.getInt (25));
        p.setDM_Situacao (cursor.getString (26));
        p.setDM_Tipo_Seguro (cursor.getString (27));
        p.setDM_Tipo_Documento_Padrao (cursor.getString ("DM_Tipo_Documento_Padrao"));
        p.setDM_Calcula_Frete (cursor.getString ("DM_Calcula_Frete"));
        p.setOID_Deposito (cursor.getInt ("OID_Deposito"));
        p.setOID_Emissor_Padrao (cursor.getString ("OID_Emissor_Padrao"));
        p.setOID_Carteira (cursor.getInt ("OID_Carteira"));
        p.setPE_ISSQN(cursor.getDouble("pe_issqn_padrao"));

        p.setOID_Pessoa_Fiscal(cursor.getString ("oid_pessoa_fiscal"));

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

  public static final UnidadeBean getByOID_Subregiao (int OID_Subregiao) throws Exception {
    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    UnidadeBean p = new UnidadeBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT                             ");
      buff.append (" Unidades.OID_Unidade,             ");
      buff.append (" Unidades.OID_Empresa,             ");
      buff.append (" Unidades.OID_Moeda,               ");
      buff.append (" Unidades.OID_Pessoa,              ");
      buff.append (" Unidades.OID_Conta_Corrente,      ");
      buff.append (" Unidades.OID_Unidade_Cobranca,    ");
      buff.append (" Unidades.OID_Unidade_Fiscal,  ");
      buff.append (" Unidades.NM_Sigla,                ");
      buff.append (" Unidades.CD_Unidade,              ");
      buff.append (" Unidades.CD_Unidade_Empresa,      ");
      buff.append (" Unidade_Fiscal.NM_Sigla,      ");
      buff.append (" Unidade_Fiscal.CD_Unidade,    ");
      buff.append (" Unidade_Cobranca.NM_Sigla,        ");
      buff.append (" Unidade_Cobranca.CD_Unidade,      ");
      buff.append (" Empresas.NM_Empresa,              ");
      buff.append (" Empresas.CD_Empresa,              ");
      buff.append (" Moedas.NM_Moeda,                  ");
      buff.append (" Moedas.CD_Moeda,                  ");
      buff.append (" Pessoas.NM_Fantasia,              ");
      buff.append (" Pessoas.NM_Razao_Social,          ");
      buff.append (" Pessoas.NR_CNPJ_CPF,          ");
      buff.append (" Pessoa_Fiscal.NM_Fantasia,    ");
      buff.append (" Pessoa_Cobranca.NM_Fantasia,      ");
      buff.append (" Unidades.CD_UNIDADE_CONTABIL,     ");
      buff.append (" Unidades.oid_subregiao,        	");
      buff.append (" Unidades.DM_Situacao,             ");
      buff.append (" Unidades.DM_Tipo_Seguro,          ");
      buff.append (" Unidades.DM_Tipo_Documento_Padrao,          ");
      buff.append (" Unidades.DM_Calcula_Frete,        ");
      buff.append (" Unidades.OID_Deposito,        ");
      buff.append (" Unidades.OID_Emissor_Padrao,        ");
      buff.append (" Unidades.OID_Carteira         ");
      buff.append ("FROM ");
      buff.append (" Unidades,                    ");
      buff.append (" Unidades Unidade_Fiscal, ");
      buff.append (" Unidades Unidade_Cobranca,   ");
      buff.append (" Empresas,                    ");
      buff.append (" Moedas,                      ");
      buff.append (" Pessoas,                     ");
      buff.append (" Pessoas Pessoa_Fiscal,   ");
      buff.append (" Pessoas Pessoa_Cobranca      ");
      buff.append (" WHERE Unidades.OID_Unidade_Fiscal = Unidade_Fiscal.OID_Unidade ");
      buff.append (" AND   Unidades.OID_Unidade_Cobranca   = Unidade_Cobranca.OID_Unidade   ");
      buff.append (" AND   Unidades.OID_Empresa            = Empresas.OID_Empresa           ");
      buff.append (" AND   Unidades.OID_Moeda              = Moedas.OID_Moeda               ");
      buff.append (" AND   Unidades.OID_Pessoa             = Pessoas.OID_Pessoa             ");
      buff.append (" AND   Unidade_Fiscal.OID_Pessoa   = Pessoa_Fiscal.OID_Pessoa   ");
      buff.append (" AND   Unidade_Cobranca.OID_Pessoa      = Pessoa_Cobranca.OID_Pessoa     ");
      buff.append (" AND   Unidades.OID_Subregiao = ");
      buff.append (OID_Subregiao);

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID_Unidade (cursor.getInt (1));
        p.setOID_Empresa (cursor.getInt (2));
        p.setOID_Moeda (cursor.getInt (3));
        p.setOID_Pessoa (cursor.getString (4));
        p.setOID_Conta_Corrente (cursor.getString (5));
        p.setOID_Unidade_Cobranca (cursor.getInt (6));
        p.setOID_Unidade_Fiscal (cursor.getInt (7));
        p.setNM_Sigla (cursor.getString (8));
        p.setCD_Unidade (cursor.getString (9));
        p.setCD_Unidade_Empresa (cursor.getString (10));
        p.setNM_Sigla_Unidade_Fiscal (cursor.getString (11));
        p.setCD_Unidade_Fiscal (cursor.getString (12));
        p.setNM_Sigla_Unidade_Cobranca (cursor.getString (13));
        p.setCD_Unidade_Cobranca (cursor.getString (14));
        p.setNM_Empresa (cursor.getString (15));
        p.setCD_Empresa (cursor.getString (16));
        p.setNM_Moeda (cursor.getString (17));
        p.setCD_Moeda (cursor.getString (18));
        p.setNM_Fantasia (JavaUtil.getValueDef (cursor.getString (19) , cursor.getString (20)));
        p.setNM_Razao_Social (cursor.getString (20));
        p.setNR_CNPJ_CPF (cursor.getString (21));
        p.setNM_Fantasia_Unidade_Fiscal (cursor.getString (22));
        p.setNM_Fantasia_Unidade_Cobranca (cursor.getString (23));
        p.setCD_Unidade_Contabil (cursor.getString (24));
        p.setOID_Subregiao (cursor.getInt (25));
        p.setDM_Situacao (cursor.getString (26));
        p.setDM_Tipo_Seguro (cursor.getString (27));
        p.setDM_Tipo_Documento_Padrao (cursor.getString ("DM_Tipo_Documento_Padrao"));
        p.setDM_Calcula_Frete (cursor.getString ("DM_Calcula_Frete"));
        p.setOID_Deposito (cursor.getInt ("OID_Deposito"));
        p.setOID_Emissor_Padrao (cursor.getString ("OID_Emissor_Padrao"));
        p.setOID_Carteira (cursor.getInt ("OID_Carteira"));
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

  public static final UnidadeBean getByOID_Pessoa (String OID_Pessoa) throws Exception {
    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    UnidadeBean p = new UnidadeBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT                             ");
      buff.append (" Unidades.OID_Unidade,             ");
      buff.append (" Unidades.OID_Empresa,             ");
      buff.append (" Unidades.OID_Moeda,               ");
      buff.append (" Unidades.OID_Pessoa,              ");
      buff.append (" Unidades.OID_Conta_Corrente,      ");
      buff.append (" Unidades.OID_Unidade_Cobranca,    ");
      buff.append (" Unidades.OID_Unidade_Fiscal,  ");
      buff.append (" Unidades.NM_Sigla,                ");
      buff.append (" Unidades.CD_Unidade,              ");
      buff.append (" Unidades.CD_Unidade_Empresa,      ");
      buff.append (" Unidade_Fiscal.NM_Sigla,      ");
      buff.append (" Unidade_Fiscal.CD_Unidade,    ");
      buff.append (" Unidade_Cobranca.NM_Sigla,        ");
      buff.append (" Unidade_Cobranca.CD_Unidade,      ");
      buff.append (" Empresas.NM_Empresa,              ");
      buff.append (" Empresas.CD_Empresa,              ");
      buff.append (" Moedas.NM_Moeda,                  ");
      buff.append (" Moedas.CD_Moeda,                  ");
      buff.append (" Pessoas.NM_Fantasia,              ");
      buff.append (" Pessoas.NM_Razao_Social,          ");
      buff.append (" Pessoas.NR_CNPJ_CPF,          ");
      buff.append (" Pessoa_Fiscal.NM_Fantasia,    ");
      buff.append (" Pessoa_Cobranca.NM_Fantasia,       ");
      buff.append (" Unidades.CD_UNIDADE_CONTABIL,         ");
      buff.append (" Unidades.DM_Situacao,                  ");
      buff.append (" Unidades.oid_subregiao,        ");
      buff.append (" Unidades.DM_Calcula_Frete,      ");
      buff.append (" Unidades.DM_Tipo_Seguro,      ");
      buff.append (" Unidades.dt_lancamento_custo,  	    ");
      buff.append (" Unidades.NR_Permiso_Complementar, ");
      buff.append (" Unidades.NR_Poliza_Seguro,        ");
      buff.append (" Unidades.NR_Permiso_Originario,    ");
      buff.append (" Unidades.OID_Carteira,         ");
      buff.append (" Unidades.OID_Emissor_Padrao,         ");
      buff.append (" Unidades.OID_Deposito,         ");
      buff.append (" Unidades.DM_Faturar,      ");
      buff.append (" Unidades.DM_Tipo_Documento_Padrao,      ");
      buff.append (" Unidades.DM_Tipo_ICMS_Unidade,      ");
      buff.append (" Unidades.NR_Regime_ICMS_Especial,    ");
      buff.append (" Unidades.pe_issqn_padrao    ");
      buff.append ("FROM ");
      buff.append (" Unidades,                    ");
      buff.append (" Unidades Unidade_Fiscal, ");
      buff.append (" Unidades Unidade_Cobranca,   ");
      buff.append (" Empresas,                    ");
      buff.append (" Moedas,                      ");
      buff.append (" Pessoas,                     ");
      buff.append (" Pessoas Pessoa_Fiscal,   ");
      buff.append (" Pessoas Pessoa_Cobranca      ");
      buff.append (" WHERE Unidades.OID_Unidade_Fiscal = Unidade_Fiscal.OID_Unidade ");
      buff.append (" AND   Unidades.OID_Unidade_Cobranca   = Unidade_Cobranca.OID_Unidade   ");
      buff.append (" AND   Unidades.OID_Empresa            = Empresas.OID_Empresa           ");
      buff.append (" AND   Unidades.OID_Moeda              = Moedas.OID_Moeda               ");
      buff.append (" AND   Unidades.OID_Pessoa             = Pessoas.OID_Pessoa             ");
      buff.append (" AND   Unidade_Fiscal.OID_Pessoa   = Pessoa_Fiscal.OID_Pessoa   ");
      buff.append (" AND   Unidade_Cobranca.OID_Pessoa      = Pessoa_Cobranca.OID_Pessoa     ");
      buff.append (" AND   Unidades.OID_Pessoa = '");
      buff.append (OID_Pessoa);
      buff.append ("'");

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID_Unidade (cursor.getInt (1));
        p.setOID_Empresa (cursor.getInt (2));
        p.setOID_Moeda (cursor.getInt (3));
        p.setOID_Pessoa (cursor.getString (4));
        p.setOID_Conta_Corrente (cursor.getString (5));
        p.setOID_Unidade_Cobranca (cursor.getInt (6));
        p.setOID_Unidade_Fiscal (cursor.getInt (7));
        p.setNM_Sigla (cursor.getString (8));
        p.setCD_Unidade (cursor.getString (9));
        p.setCD_Unidade_Empresa (cursor.getString (10));
        p.setNM_Sigla_Unidade_Fiscal (cursor.getString (11));
        p.setCD_Unidade_Fiscal (cursor.getString (12));
        p.setNM_Sigla_Unidade_Cobranca (cursor.getString (13));
        p.setCD_Unidade_Cobranca (cursor.getString (14));
        p.setNM_Empresa (cursor.getString (15));
        p.setCD_Empresa (cursor.getString (16));
        p.setNM_Moeda (cursor.getString (17));
        p.setCD_Moeda (cursor.getString (18));
        p.setNM_Fantasia (JavaUtil.getValueDef (cursor.getString (19) , cursor.getString (20)));
        p.setNM_Razao_Social (cursor.getString (20));
        p.setNR_CNPJ_CPF (cursor.getString (21));
        p.setNM_Fantasia_Unidade_Fiscal (cursor.getString (22));
        p.setNM_Fantasia_Unidade_Cobranca (cursor.getString (23));
        p.setCD_Unidade_Contabil (cursor.getString (24));
        p.setDM_Situacao (cursor.getString (25));
        p.setOID_Subregiao (cursor.getInt (26));
        p.setDM_Calcula_Frete (cursor.getString (27));
        p.setDM_Tipo_Seguro (cursor.getString (28));
        p.setDT_Lancamento_Custo (cursor.getString (29));
        p.setNR_Permiso_Complementar (cursor.getString (30));
        p.setNR_Poliza_Seguro (cursor.getString (31));
        p.setNR_Permiso_Originario (cursor.getString (32));
        p.setOID_Deposito (cursor.getInt ("OID_Deposito"));
        p.setOID_Emissor_Padrao (cursor.getString ("OID_Emissor_Padrao"));
        p.setOID_Carteira (cursor.getInt ("OID_Carteira"));
        p.setDM_Faturar (cursor.getString ("DM_Faturar"));
        p.setDM_Tipo_ICMS_Unidade (cursor.getString ("DM_Tipo_ICMS_Unidade"));
        p.setDM_Tipo_Documento_Padrao (cursor.getString ("DM_Tipo_Documento_Padrao"));
        p.setNR_Regime_ICMS_Especial (cursor.getString ("NR_Regime_ICMS_Especial"));
        p.setPE_ISSQN(cursor.getDouble("pe_issqn_padrao"));

      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      throw e;
    }
    return p;
  }

  public void getByCD_Unidade (UnidadeBean ed , HttpServletRequest request , String nmObj) throws Excecoes {
    try {
      request.setAttribute (nmObj , UnidadeBean.getByCD_Unidade (ed.CD_Unidade));
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
  }

  public void getByOID_Unidade (UnidadeBean ed , HttpServletRequest request , String nmObj) throws Excecoes {
    try {
      request.setAttribute (nmObj , UnidadeBean.getByOID_Unidade (null, ed.OID_Unidade));
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
  }

  public static final UnidadeBean getByLocalizacao (long oid_Unidade) throws Exception {
    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    UnidadeBean p = new UnidadeBean ();
    try {
      String
          sql = "SELECT NM_Cidade, NM_Pais, NM_Estado, NM_Regiao_Pais, NM_Regiao_Estado FROM CIDADES, REGIOES_ESTADOS, ESTADOS, Regioes_Paises, Paises, Unidades, Pessoas   WHERE 1=1";
      sql += " AND CIDADES.OID_REGIAO_ESTADO  = REGIOES_ESTADOS.OID_REGIAO_ESTADO ";
      sql += " AND REGIOES_ESTADOS.OID_ESTADO = ESTADOS.OID_ESTADO  ";
      sql += " AND ESTADOS.OID_Regiao_Pais = Regioes_Paises.OID_Regiao_Pais  ";
      sql += " AND Regioes_Paises.OID_Pais = Paises.OID_Pais  ";
      sql += " AND Unidades.OID_Pessoa = Pessoas.OID_Pessoa  ";
      sql += " AND Pessoas.OID_Cidade = CIDADES.OID_Cidade  ";
      sql += " AND Unidades.OID_Unidade = " + oid_Unidade;

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (sql);

      while (cursor.next ()) {
        p.setNM_Cidade (cursor.getString ("NM_Cidade"));
        p.setNM_Pais (cursor.getString ("NM_Pais"));
        p.setNM_Estado (cursor.getString ("NM_Estado"));
        p.setNM_Regiao_Pais (cursor.getString ("NM_Regiao_Pais"));
        p.setNM_Regiao_Estado (cursor.getString ("NM_Regiao_Estado"));
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      throw e;
    }
    return p;
  }

  public static final UnidadeBean getByCD_Unidade (String CD_Unidade) throws Exception {
    UnidadeBean p = new UnidadeBean ();
    //*** Valida CD_Unidade...
     if (!JavaUtil.doValida (CD_Unidade)) {
       return p;
     }

    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT                             ");
      buff.append (" Unidades.OID_Unidade,             ");
      buff.append (" Unidades.OID_Empresa,             ");
      buff.append (" Unidades.OID_Moeda,               ");
      buff.append (" Unidades.OID_Pessoa,              ");
      buff.append (" Unidades.OID_Conta_Corrente,      ");
      buff.append (" Unidades.OID_Unidade_Cobranca,    ");
      buff.append (" Unidades.OID_Unidade_Fiscal,  ");
      buff.append (" Unidades.NM_Sigla,                ");
      buff.append (" Unidades.CD_Unidade,              ");
      buff.append (" Unidades.CD_Unidade_Empresa,      ");
      buff.append (" Unidade_Fiscal.NM_Sigla,      ");
      buff.append (" Unidade_Fiscal.CD_Unidade,    ");
      buff.append (" Unidade_Cobranca.NM_Sigla,        ");
      buff.append (" Unidade_Cobranca.CD_Unidade,      ");
      buff.append (" Empresas.NM_Empresa,              ");
      buff.append (" Empresas.CD_Empresa,              ");
      buff.append (" Moedas.NM_Moeda,                  ");
      buff.append (" Moedas.CD_Moeda,                  ");
      buff.append (" Pessoas.NM_Fantasia,              ");
      buff.append (" Pessoas.NM_Razao_Social,          ");
      buff.append (" Pessoas.NR_CNPJ_CPF,          ");
      buff.append (" Pessoa_Fiscal.NM_Fantasia,    ");
      buff.append (" Pessoa_Cobranca.NM_Fantasia,       ");
      buff.append (" Unidades.CD_UNIDADE_CONTABIL,         ");
      buff.append (" Unidades.oid_subregiao,        ");
      buff.append (" Unidades.DM_Calcula_Frete,     ");
      buff.append (" Unidades.DM_Tipo_Seguro,        ");
      buff.append (" Unidades.oid_Emissor_Padrao,        ");
      buff.append (" Unidades.OID_Deposito,        ");
      buff.append (" Unidades.DM_Tipo_Documento_Padrao,          ");
      buff.append (" Unidades.OID_Carteira         ");
      buff.append ("FROM ");
      buff.append (" Unidades,                    ");
      buff.append (" Unidades Unidade_Fiscal, ");
      buff.append (" Unidades Unidade_Cobranca,   ");
      buff.append (" Empresas,                    ");
      buff.append (" Moedas,                      ");
      buff.append (" Pessoas,                     ");
      buff.append (" Pessoas Pessoa_Fiscal,   ");
      buff.append (" Pessoas Pessoa_Cobranca      ");
      buff.append (" WHERE Unidades.OID_Unidade_Fiscal = Unidade_Fiscal.OID_Unidade ");
      buff.append (" AND   Unidades.OID_Unidade_Cobranca   = Unidade_Cobranca.OID_Unidade   ");
      buff.append (" AND   Unidades.OID_Empresa            = Empresas.OID_Empresa           ");
      buff.append (" AND   Unidades.OID_Moeda              = Moedas.OID_Moeda               ");
      buff.append (" AND   Unidades.OID_Pessoa             = Pessoas.OID_Pessoa             ");
      buff.append (" AND   Unidade_Fiscal.OID_Pessoa   = Pessoa_Fiscal.OID_Pessoa   ");
      buff.append (" AND   Unidade_Cobranca.OID_Pessoa      = Pessoa_Cobranca.OID_Pessoa     ");
      buff.append (" AND   Unidades.CD_Unidade = '");
      buff.append (CD_Unidade);
      buff.append ("'");

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID_Unidade (cursor.getInt (1));
        p.setOID_Empresa (cursor.getInt (2));
        p.setOID_Moeda (cursor.getInt (3));
        p.setOID_Pessoa (cursor.getString (4));
        p.setOID_Conta_Corrente (cursor.getString (5));
        p.setOID_Unidade_Cobranca (cursor.getInt (6));
        p.setOID_Unidade_Fiscal (cursor.getInt (7));
        p.setNM_Sigla (cursor.getString (8));
        p.setCD_Unidade (cursor.getString (9));
        p.setCD_Unidade_Empresa (cursor.getString (10));
        p.setNM_Sigla_Unidade_Fiscal (cursor.getString (11));
        p.setCD_Unidade_Fiscal (cursor.getString (12));
        p.setNM_Sigla_Unidade_Cobranca (cursor.getString (13));
        p.setCD_Unidade_Cobranca (cursor.getString (14));
        p.setNM_Empresa (cursor.getString (15));
        p.setCD_Empresa (cursor.getString (16));
        p.setNM_Moeda (cursor.getString (17));
        p.setCD_Moeda (cursor.getString (18));
        p.setNM_Fantasia (JavaUtil.getValueDef (cursor.getString (19) , cursor.getString (20)));
        p.setNM_Razao_Social (cursor.getString (20));
        p.setNR_CNPJ_CPF (cursor.getString (21));
        p.setNM_Fantasia_Unidade_Fiscal (cursor.getString (22));
        p.setNM_Fantasia_Unidade_Cobranca (cursor.getString (23));
        p.setCD_Unidade_Contabil (cursor.getString (24));
        p.setOID_Subregiao (cursor.getInt (25));
        p.setDM_Calcula_Frete (cursor.getString (26));
        p.setDM_Tipo_Seguro (cursor.getString (27));
        p.setOID_Carteira (cursor.getInt ("OID_Carteira"));
        p.setOID_Deposito (cursor.getInt ("OID_Deposito"));
        p.setOID_Emissor_Padrao (cursor.getString ("OID_Emissor_Padrao"));
        p.setDM_Tipo_Documento_Padrao (cursor.getString ("DM_Tipo_Documento_Padrao"));
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

  public static final List getByCD_Unidade_Lista (String CD_Unidade) throws Exception {
    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    List Unidade_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT                             ");
      buff.append (" Unidades.OID_Unidade,             ");
      buff.append (" Unidades.OID_Empresa,             ");
      buff.append (" Unidades.OID_Moeda,               ");
      buff.append (" Unidades.OID_Pessoa,              ");
      buff.append (" Unidades.OID_Conta_Corrente,      ");
      buff.append (" Unidades.OID_Unidade_Cobranca,    ");
      buff.append (" Unidades.OID_Unidade_Fiscal,  ");
      buff.append (" Unidades.NM_Sigla,                ");
      buff.append (" Unidades.CD_Unidade,              ");
      buff.append (" Unidades.CD_Unidade_Empresa,      ");
      buff.append (" Unidade_Fiscal.NM_Sigla,      ");
      buff.append (" Unidade_Fiscal.CD_Unidade,    ");
      buff.append (" Unidade_Cobranca.NM_Sigla,        ");
      buff.append (" Unidade_Cobranca.CD_Unidade,      ");
      buff.append (" Empresas.NM_Empresa,              ");
      buff.append (" Empresas.CD_Empresa,              ");
      buff.append (" Moedas.NM_Moeda,                  ");
      buff.append (" Moedas.CD_Moeda,                  ");
      buff.append (" Pessoas.NM_Fantasia,              ");
      buff.append (" Pessoas.NM_Razao_Social,          ");
      buff.append (" Unidades.DM_Tipo_Documento_Padrao,          ");
      buff.append (" Unidades.OID_Carteira         ");
      buff.append ("FROM ");
      buff.append (" Unidades,                    ");
      buff.append (" Unidades Unidade_Fiscal, ");
      buff.append (" Unidades Unidade_Cobranca,   ");
      buff.append (" Empresas,                    ");
      buff.append (" Moedas,                      ");
      buff.append (" Pessoas                      ");
      buff.append (" WHERE Unidades.OID_Unidade_Fiscal = Unidade_Fiscal.OID_Unidade ");
      buff.append (" AND   Unidades.OID_Unidade_Cobranca   = Unidade_Cobranca.OID_Unidade   ");
      buff.append (" AND   Unidades.OID_Empresa            = Empresas.OID_Empresa           ");
      buff.append (" AND   Unidades.OID_Moeda              = Moedas.OID_Moeda               ");
      buff.append (" AND   Unidades.OID_Pessoa             = Pessoas.OID_Pessoa             ");
      buff.append (" AND   Unidades.CD_Unidade LIKE'");
      buff.append (CD_Unidade);
      buff.append ("%'");

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        UnidadeBean p = new UnidadeBean ();
        p.setOID_Unidade (cursor.getInt (1));
        p.setOID_Empresa (cursor.getInt (2));
        p.setOID_Moeda (cursor.getInt (3));
        p.setOID_Pessoa (cursor.getString (4));
        p.setOID_Conta_Corrente (cursor.getString (5));
        p.setOID_Unidade_Cobranca (cursor.getInt (6));
        p.setOID_Unidade_Fiscal (cursor.getInt (7));
        p.setNM_Sigla (cursor.getString (8));
        p.setCD_Unidade (cursor.getString (9));
        p.setCD_Unidade_Empresa (cursor.getString (10));
        p.setNM_Sigla_Unidade_Fiscal (cursor.getString (11));
        p.setCD_Unidade_Fiscal (cursor.getString (12));
        p.setNM_Sigla_Unidade_Cobranca (cursor.getString (13));
        p.setCD_Unidade_Cobranca (cursor.getString (14));
        p.setNM_Empresa (cursor.getString (15));
        p.setCD_Empresa (cursor.getString (16));
        p.setNM_Moeda (cursor.getString (17));
        p.setCD_Moeda (cursor.getString (18));
        p.setNM_Fantasia (JavaUtil.getValueDef (cursor.getString (19) , cursor.getString (20)));
        p.setNM_Razao_Social (cursor.getString (20));
        p.setDM_Tipo_Documento_Padrao ("DM_Tipo_Documento_Padrao");
        p.setOID_Carteira (cursor.getInt ("OID_Carteira"));
        Unidade_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Unidade_Lista;
  }

  public static final List getByCD_Unidade_Lista_Entregadora (String CD_Unidade) throws Exception {
    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    List Unidade_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT                             ");
      buff.append (" Unidades.OID_Unidade,             ");
      buff.append (" Unidades.OID_Empresa,             ");
      buff.append (" Unidades.OID_Moeda,               ");
      buff.append (" Unidades.OID_Pessoa,              ");
      buff.append (" Unidades.OID_Conta_Corrente,      ");
      buff.append (" Unidades.OID_Unidade_Cobranca,    ");
      buff.append (" Unidades.OID_Unidade_Fiscal,  ");
      buff.append (" Unidades.NM_Sigla,                ");
      buff.append (" Unidades.CD_Unidade,              ");
      buff.append (" Unidades.CD_Unidade_Empresa,      ");
      buff.append (" Unidade_Fiscal.NM_Sigla,      ");
      buff.append (" Unidade_Fiscal.CD_Unidade,    ");
      buff.append (" Unidade_Cobranca.NM_Sigla,        ");
      buff.append (" Unidade_Cobranca.CD_Unidade,      ");
      buff.append (" Empresas.NM_Empresa,              ");
      buff.append (" Empresas.CD_Empresa,              ");
      buff.append (" Moedas.NM_Moeda,                  ");
      buff.append (" Moedas.CD_Moeda,                  ");
      buff.append (" Pessoas.NM_Fantasia,              ");
      buff.append (" Pessoas.NM_Razao_Social,          ");
      buff.append (" Unidades.OID_Carteira         ");
      buff.append ("FROM ");
      buff.append (" Unidades,                    ");
      buff.append (" Unidades Unidade_Fiscal, ");
      buff.append (" Unidades Unidade_Cobranca,   ");
      buff.append (" Empresas,                    ");
      buff.append (" Moedas,                      ");
      buff.append (" Pessoas                      ");
      buff.append (" WHERE Unidades.OID_Unidade_Fiscal = Unidade_Fiscal.OID_Unidade ");
      buff.append (" AND   Unidades.OID_Unidade_Cobranca   = Unidade_Cobranca.OID_Unidade   ");
      buff.append (" AND   Unidades.OID_Empresa            = Empresas.OID_Empresa           ");
      buff.append (" AND   Unidades.OID_Moeda              = Moedas.OID_Moeda               ");
      buff.append (" AND   Unidades.OID_Pessoa             = Pessoas.OID_Pessoa             ");
      buff.append (" AND   (Unidades.DM_Situacao            = 'E'  OR  Unidades.DM_Situacao            = 'A' )    ");
      buff.append (" AND   Unidades.CD_Unidade LIKE'");
      buff.append (CD_Unidade);
      buff.append ("%'");

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        UnidadeBean p = new UnidadeBean ();
        p.setOID_Unidade (cursor.getInt (1));
        p.setOID_Empresa (cursor.getInt (2));
        p.setOID_Moeda (cursor.getInt (3));
        p.setOID_Pessoa (cursor.getString (4));
        p.setOID_Conta_Corrente (cursor.getString (5));
        p.setOID_Unidade_Cobranca (cursor.getInt (6));
        p.setOID_Unidade_Fiscal (cursor.getInt (7));
        p.setNM_Sigla (cursor.getString (8));
        p.setCD_Unidade (cursor.getString (9));
        p.setCD_Unidade_Empresa (cursor.getString (10));
        p.setNM_Sigla_Unidade_Fiscal (cursor.getString (11));
        p.setCD_Unidade_Fiscal (cursor.getString (12));
        p.setNM_Sigla_Unidade_Cobranca (cursor.getString (13));
        p.setCD_Unidade_Cobranca (cursor.getString (14));
        p.setNM_Empresa (cursor.getString (15));
        p.setCD_Empresa (cursor.getString (16));
        p.setNM_Moeda (cursor.getString (17));
        p.setCD_Moeda (cursor.getString (18));
        p.setNM_Fantasia (JavaUtil.getValueDef (cursor.getString (19) , cursor.getString (20)));
        p.setNM_Razao_Social (cursor.getString (20));
        p.setOID_Carteira (cursor.getInt ("OID_Carteira"));
        Unidade_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Unidade_Lista;
  }

  public static final List getAll_Unidade () throws Exception {
    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    List Unidade_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT                             ");
      buff.append (" Unidades.OID_Unidade,             ");
      buff.append (" Unidades.OID_Empresa,             ");
      buff.append (" Unidades.OID_Moeda,               ");
      buff.append (" Unidades.OID_Pessoa,              ");
      buff.append (" Unidades.OID_Conta_Corrente,      ");
      buff.append (" Unidades.OID_Unidade_Cobranca,    ");
      buff.append (" Unidades.OID_Unidade_Fiscal,  ");
      buff.append (" Unidades.NM_Sigla,                ");
      buff.append (" Unidades.CD_Unidade,              ");
      buff.append (" Unidades.CD_Unidade_Empresa,      ");
      buff.append (" Unidade_Fiscal.NM_Sigla,      ");
      buff.append (" Unidade_Fiscal.CD_Unidade,    ");
      buff.append (" Unidade_Cobranca.NM_Sigla,        ");
      buff.append (" Unidade_Cobranca.CD_Unidade,      ");
      buff.append (" Empresas.NM_Empresa,              ");
      buff.append (" Empresas.CD_Empresa,              ");
      buff.append (" Moedas.NM_Moeda,                  ");
      buff.append (" Moedas.CD_Moeda,                  ");
      buff.append (" Pessoas.NM_Fantasia,              ");
      buff.append (" Pessoas.NM_Razao_Social,          ");
      buff.append (" Pessoa_Fiscal.NM_Fantasia,    ");
      buff.append (" Pessoa_Cobranca.NM_Fantasia,       ");
      buff.append (" Unidades.OID_Carteira         ");
      buff.append (" ,Unidades.DM_NFE         ");
      buff.append ("FROM ");
      buff.append (" Unidades,                    ");
      buff.append (" Unidades Unidade_Fiscal, ");
      buff.append (" Unidades Unidade_Cobranca,   ");
      buff.append (" Empresas,                    ");
      buff.append (" Moedas,                      ");
      buff.append (" Pessoas,                     ");
      buff.append (" Pessoas Pessoa_Fiscal,   ");
      buff.append (" Pessoas Pessoa_Cobranca      ");
      buff.append (" WHERE Unidades.OID_Unidade_Fiscal = Unidade_Fiscal.OID_Unidade ");
      buff.append (" AND   Unidades.OID_Unidade_Cobranca   = Unidade_Cobranca.OID_Unidade   ");
      buff.append (" AND   Unidades.OID_Empresa            = Empresas.OID_Empresa           ");
      buff.append (" AND   Unidades.OID_Moeda              = Moedas.OID_Moeda               ");
      buff.append (" AND   Unidades.OID_Pessoa             = Pessoas.OID_Pessoa             ");
      buff.append (" AND   Unidade_Fiscal.OID_Pessoa   = Pessoa_Fiscal.OID_Pessoa   ");
      buff.append (" AND   Unidade_Cobranca.OID_Pessoa      = Pessoa_Cobranca.OID_Pessoa     ");

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        UnidadeBean p = new UnidadeBean ();
        p.setOID_Unidade (cursor.getInt (1));
        p.setOID_Empresa (cursor.getInt (2));
        p.setOID_Moeda (cursor.getInt (3));
        p.setOID_Pessoa (cursor.getString (4));
        p.setOID_Conta_Corrente (cursor.getString (5));
        p.setOID_Unidade_Cobranca (cursor.getInt (6));
        p.setOID_Unidade_Fiscal (cursor.getInt (7));
        p.setNM_Sigla (cursor.getString (8));
        p.setCD_Unidade (cursor.getString (9));
        p.setCD_Unidade_Empresa (cursor.getString (10));
        p.setNM_Sigla_Unidade_Fiscal (cursor.getString (11));
        p.setCD_Unidade_Fiscal (cursor.getString (12));
        p.setNM_Sigla_Unidade_Cobranca (cursor.getString (13));
        p.setCD_Unidade_Cobranca (cursor.getString (14));
        p.setNM_Empresa (cursor.getString (15));
        p.setCD_Empresa (cursor.getString (16));
        p.setNM_Moeda (cursor.getString (17));
        p.setCD_Moeda (cursor.getString (18));
        p.setNM_Fantasia (JavaUtil.getValueDef (cursor.getString (19) , cursor.getString (20)));
        p.setNM_Razao_Social (cursor.getString (20));
        p.setNM_Fantasia_Unidade_Fiscal (cursor.getString (21));
        p.setNM_Fantasia_Unidade_Cobranca (cursor.getString (22));
        p.setOID_Carteira (cursor.getInt ("OID_Carteira"));
        p.setDm_Stamp(JavaUtil.getValueDef (cursor.getString("DM_NFE"),"N"));
        Unidade_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Unidade_Lista;
  }

  public static final List getAll_Unidade (String cd_Unidade, String nm_Unidade, String dm_Ordem) throws Exception {
    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    List Unidade_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT                             ");
      buff.append (" Unidades.OID_Unidade,             ");
      buff.append (" Unidades.OID_Empresa,             ");
      buff.append (" Unidades.OID_Moeda,               ");
      buff.append (" Unidades.OID_Pessoa,              ");
      buff.append (" Unidades.OID_Conta_Corrente,      ");
      buff.append (" Unidades.OID_Unidade_Cobranca,    ");
      buff.append (" Unidades.OID_Unidade_Fiscal,  ");
      buff.append (" Unidades.NM_Sigla,                ");
      buff.append (" Unidades.CD_Unidade,              ");
      buff.append (" Unidades.CD_Unidade_Empresa,      ");
      buff.append (" Unidade_Fiscal.NM_Sigla,      ");
      buff.append (" Unidade_Fiscal.CD_Unidade,    ");
      buff.append (" Unidade_Cobranca.NM_Sigla,        ");
      buff.append (" Unidade_Cobranca.CD_Unidade,      ");
      buff.append (" Empresas.NM_Empresa,              ");
      buff.append (" Empresas.CD_Empresa,              ");
      buff.append (" Moedas.NM_Moeda,                  ");
      buff.append (" Moedas.CD_Moeda,                  ");
      buff.append (" Pessoas.NM_Fantasia,              ");
      buff.append (" Pessoas.NM_Razao_Social,          ");
      buff.append (" Pessoa_Fiscal.NM_Fantasia,    ");
      buff.append (" Pessoa_Cobranca.NM_Fantasia,       ");
      buff.append (" Unidades.OID_Carteira         ");
      buff.append ("FROM ");
      buff.append (" Unidades,                    ");
      buff.append (" Unidades Unidade_Fiscal, ");
      buff.append (" Unidades Unidade_Cobranca,   ");
      buff.append (" Empresas,                    ");
      buff.append (" Moedas,                      ");
      buff.append (" Pessoas,                     ");
      buff.append (" Pessoas Pessoa_Fiscal,   ");
      buff.append (" Pessoas Pessoa_Cobranca      ");
      buff.append (" WHERE Unidades.OID_Unidade_Fiscal = Unidade_Fiscal.OID_Unidade ");
      buff.append (" AND   Unidades.OID_Unidade_Cobranca   = Unidade_Cobranca.OID_Unidade   ");
      buff.append (" AND   Unidades.OID_Empresa            = Empresas.OID_Empresa           ");
      buff.append (" AND   Unidades.OID_Moeda              = Moedas.OID_Moeda               ");
      buff.append (" AND   Unidades.OID_Pessoa             = Pessoas.OID_Pessoa             ");
      buff.append (" AND   Unidade_Fiscal.OID_Pessoa   = Pessoa_Fiscal.OID_Pessoa   ");
      buff.append (" AND   Unidade_Cobranca.OID_Pessoa      = Pessoa_Cobranca.OID_Pessoa     ");

      if (cd_Unidade != null && !cd_Unidade.equals("null") && !cd_Unidade.equals("")){
        buff.append (" AND   Unidades.CD_Unidade LIKE'");
        buff.append (cd_Unidade);
        buff.append ("%'");
      }
      if (nm_Unidade != null && !nm_Unidade.equals("null") && !nm_Unidade.equals("")){
        buff.append (" AND   Pessoas.NM_Fantasia LIKE '%");
        buff.append (nm_Unidade);
        buff.append ("%'");
      }
      if ("A".equals(dm_Ordem)){
        buff.append (" ORDER BY Pessoas.NM_Razao_Social ");
      }else{
        buff.append (" ORDER BY Unidades.CD_Unidade ");
      }

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        UnidadeBean p = new UnidadeBean ();
        p.setOID_Unidade (cursor.getInt (1));
        p.setOID_Empresa (cursor.getInt (2));
        p.setOID_Moeda (cursor.getInt (3));
        p.setOID_Pessoa (cursor.getString (4));
        p.setOID_Conta_Corrente (cursor.getString (5));
        p.setOID_Unidade_Cobranca (cursor.getInt (6));
        p.setOID_Unidade_Fiscal (cursor.getInt (7));
        p.setNM_Sigla (cursor.getString (8));
        p.setCD_Unidade (cursor.getString (9));
        p.setCD_Unidade_Empresa (cursor.getString (10));
        p.setNM_Sigla_Unidade_Fiscal (cursor.getString (11));
        p.setCD_Unidade_Fiscal (cursor.getString (12));
        p.setNM_Sigla_Unidade_Cobranca (cursor.getString (13));
        p.setCD_Unidade_Cobranca (cursor.getString (14));
        p.setNM_Empresa (cursor.getString (15));
        p.setCD_Empresa (cursor.getString (16));
        p.setNM_Moeda (cursor.getString (17));
        p.setCD_Moeda (cursor.getString (18));
        p.setNM_Fantasia (JavaUtil.getValueDef (cursor.getString (19) , cursor.getString (20)));
        p.setNM_Razao_Social (cursor.getString (20));
        p.setNM_Fantasia_Unidade_Fiscal (cursor.getString (21));
        p.setNM_Fantasia_Unidade_Cobranca (cursor.getString (22));
        p.setOID_Carteira (cursor.getInt ("OID_Carteira"));
        Unidade_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return Unidade_Lista;
  }

  public static final List getUnidadeR0007(int oid_unidade_padrao) throws Exception {
	    Connection conn = null;
	    try {
	      conn = OracleConnection2.getWEB ();
	      conn.setAutoCommit (false);
	    }
	    catch (Exception e) {
	      e.printStackTrace ();
	      throw e;
	    }

	    List Unidade_Lista = new ArrayList ();
	    try {
	      StringBuffer buff = new StringBuffer ();
	      buff.append ("SELECT                             ");
	      buff.append (" Pessoas.NR_CNPJ_CPF,              ");
	      buff.append (" Pessoas.OID_CIDADE,               ");
	      buff.append (" Estados.CD_Estado                 ");
	      buff.append ("FROM ");
	      buff.append (" Unidades,                    ");
	      buff.append (" Pessoas,                     ");
	      buff.append (" Cidades,                     ");
	      buff.append (" Regioes_Estados,             ");
	      buff.append (" Estados                      ");
	      buff.append (" WHERE Unidades.OID_Pessoa       = Pessoas.OID_Pessoa   ");
	      buff.append (" AND   Pessoas.OID_Cidade        = Cidades.OID_Cidade   ");
	      buff.append (" AND   Cidades.OID_Regiao_estado = Regioes_estados.OID_Regiao_estado     ");
	      buff.append (" AND   Regioes_estados.OID_Estado = estados.OID_Estado");

	      buff.append (" AND   Unidades.oid_unidade != ");
	      buff.append (oid_unidade_padrao);

	      buff.append (" ORDER BY Estados.CD_Estado ");

	      Statement stmt = conn.createStatement ();
	      ResultSet cursor = stmt.executeQuery (buff.toString ());

	      while (cursor.next ()) {
	        PessoaBean p = new PessoaBean ();
	        p.setNR_CNPJ_CPF(cursor.getString (1));
	        p.setOID_Cidade(cursor.getInt (2));
	        p.setCD_Estado(cursor.getString (3));
	        Unidade_Lista.add (p);
	      }
	      cursor.close ();
	      stmt.close ();
	      conn.close ();
	    }
	    catch (Exception e) {
	      e.printStackTrace ();
	    }
	    return Unidade_Lista;
	  }

  public void setDM_Faturar (String DM_Faturar) {
    this.DM_Faturar = DM_Faturar;
  }

  public String getDM_Faturar () {
    return DM_Faturar;
  }

public String getDM_Tipo_ICMS_Unidade() {
	return DM_Tipo_ICMS_Unidade;
}

public void setDM_Tipo_ICMS_Unidade(String tipo_ICMS_Unidade) {
	DM_Tipo_ICMS_Unidade = tipo_ICMS_Unidade;
}

public double getPE_ISSQN() {
	return PE_ISSQN;
}

public void setPE_ISSQN(double pe_issqn) {
	PE_ISSQN = pe_issqn;
}

public String getOID_Pessoa_Fiscal() {
	return OID_Pessoa_Fiscal;
}

public void setOID_Pessoa_Fiscal(String pessoa_Fiscal) {
	OID_Pessoa_Fiscal = pessoa_Fiscal;
}

}
