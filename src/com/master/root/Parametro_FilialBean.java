package com.master.root;

import java.sql.*;
import java.util.*;

import auth.*;
import com.master.util.*;

public class Parametro_FilialBean {
  private int NR_Proxima_Entrega;
  private int NR_Proxima_Visita;
  private int NR_Dias_Revisao_Cadastral;
  private int NR_Proximo_Lote_Baixa;
  private int NR_Proximo_Acerto;
  private int NR_Proximo_Arquivo_EDI;
  private int NR_Proximo_Lote_Pagamento;
  private int NR_Proximo_Boletim_Ocorrencia;
  private int NR_Proxima_Proposta;
  private int NR_Proximo_Compromisso;
  private int NR_Proximo_Bordero;
  private int NR_Proxima_Viagem;
  private String NR_Proximo_CNPJ;
  private int NR_Proxima_Ordem_Servico;
  private int NR_Proxima_Ocorrencia;
  private int NR_Proximo_Lancamento_Caixa;
  private int NR_Proxima_Remessa_Comprovante;
  private int NR_Proxima_Tabela;
  private int NR_Proxima_Cotacao;
  private double VL_Maximo_Ordem_Frete;
  private double VL_Frete_Minimo;
  private String CD_AIDOF_CTO_Nacional;
  private String CD_AIDOF_CTO_Internacional;
  private String CD_AIDOF_Manifesto;
  private String CD_AIDOF_Fatura;
  private String CD_AIDOF_Coleta;
  private String CD_AIDOF_AWB;
  private String CD_AIDOF_Ordem_Frete;
  private String CD_AIDOF_Romaneio;
  private String CD_AIDOF_Minuta;
  private String NM_Modelo_Conhecimento;
  private String CD_Unidade;
  private String NM_Unidade;
  private String Usuario_Stamp;
  private String Dt_Stamp;
  private String Dm_Stamp;
  private int oid;
  private int oid_AIDOF;
  private int oid_AIDOF_Manifesto;
  private int oid_AIDOF_Fatura;
  private int oid_AIDOF_Coleta;
  private int oid_AIDOF_AWB;
  private int oid_AIDOF_CTO_Internacional;
  private int oid_AIDOF_Ordem_Frete;
  private int oid_AIDOF_Romaneio;
  private int oid_AIDOF_Minuta;
  private int oid_Unidade;
  private String CD_AIDOF_Ordem_Frete_Adto;
  private int OID_AIDOF_Ordem_Frete_Adto;
  private int oid_AIDOF_Ordem_Frete_Adto;
  private int oid_AIDOF_Nota_Fiscal_Servico;
  private String CD_AIDOF_Nota_Fiscal_Servico;
  private int oid_AIDOF_Nota_Fiscal;
  private String CD_AIDOF_Nota_Fiscal;
  private int oid_AIDOF_ACT;
  private String CD_AIDOF_ACT;

  public Parametro_FilialBean () {
    CD_AIDOF_CTO_Nacional = "0";
    CD_AIDOF_CTO_Internacional = "0";
    CD_AIDOF_Manifesto = "0";
    CD_AIDOF_Fatura = "0";
    CD_AIDOF_Coleta = "0";
    CD_AIDOF_AWB = "0";
    CD_AIDOF_AWB = "0";
    CD_AIDOF_Ordem_Frete = "0";
    CD_AIDOF_Romaneio = "0";
    CD_AIDOF_Minuta = "0";
  }

  public String getCD_Unidade () {
    return CD_Unidade;
  }

  public void setCD_Unidade (String CD_Unidade) {
    this.CD_Unidade = CD_Unidade;
  }

  public String getNM_Unidade () {
    return NM_Unidade;
  }

  public void setNM_Unidade (String NM_Unidade) {
    this.NM_Unidade = NM_Unidade;
  }

  public int getNR_Proxima_Entrega () {
    return NR_Proxima_Entrega;
  }

  public void setNR_Proxima_Entrega (int NR_Proxima_Entrega) {
    this.NR_Proxima_Entrega = NR_Proxima_Entrega;
  }

  public int getNR_Proxima_Visita () {
    return NR_Proxima_Visita;
  }

  public void setNR_Proxima_Visita (int NR_Proxima_Visita) {
    this.NR_Proxima_Visita = NR_Proxima_Visita;
  }

  public int getNR_Dias_Revisao_Cadastral () {
    return NR_Dias_Revisao_Cadastral;
  }

  public void setNR_Dias_Revisao_Cadastral (int NR_Dias_Revisao_Cadastral) {
    this.NR_Dias_Revisao_Cadastral = NR_Dias_Revisao_Cadastral;
  }

  public int getNR_Proximo_Lote_Baixa () {
    return NR_Proximo_Lote_Baixa;
  }

  public void setNR_Proximo_Lote_Baixa (int NR_Proximo_Lote_Baixa) {
    this.NR_Proximo_Lote_Baixa = NR_Proximo_Lote_Baixa;
  }

  public String getNM_Modelo_Conhecimento () {
    return NM_Modelo_Conhecimento;
  }

  public void setNM_Modelo_Conhecimento (String NM_Modelo_Conhecimento) {
    this.NM_Modelo_Conhecimento = NM_Modelo_Conhecimento;
  }

  public int getNR_Proximo_Acerto () {
    return NR_Proximo_Acerto;
  }

  public void setNR_Proximo_Acerto (int NR_Proximo_Acerto) {
    this.NR_Proximo_Acerto = NR_Proximo_Acerto;
  }

  public int getNR_Proximo_Arquivo_EDI () {
    return NR_Proximo_Arquivo_EDI;
  }

  public void setNR_Proximo_Arquivo_EDI (int NR_Proximo_Arquivo_EDI) {
    this.NR_Proximo_Arquivo_EDI = NR_Proximo_Arquivo_EDI;
  }

  public int getNR_Proximo_Lote_Pagamento () {
    return NR_Proximo_Lote_Pagamento;
  }

  public void setNR_Proximo_Lote_Pagamento (int NR_Proximo_Lote_Pagamento) {
    this.NR_Proximo_Lote_Pagamento = NR_Proximo_Lote_Pagamento;
  }

  public int getNR_Proximo_Boletim_Ocorrencia () {
    return NR_Proximo_Boletim_Ocorrencia;
  }

  public void setNR_Proximo_Boletim_Ocorrencia (int NR_Proximo_Boletim_Ocorrencia) {
    this.NR_Proximo_Boletim_Ocorrencia = NR_Proximo_Boletim_Ocorrencia;
  }

  public int getNR_Proxima_Proposta () {
    return NR_Proxima_Proposta;
  }

  public void setNR_Proxima_Proposta (int NR_Proxima_Proposta) {
    this.NR_Proxima_Proposta = NR_Proxima_Proposta;
  }

  public int getNR_Proximo_Compromisso () {
    return NR_Proximo_Compromisso;
  }

  public void setNR_Proximo_Compromisso (int NR_Proximo_Compromisso) {
    this.NR_Proximo_Compromisso = NR_Proximo_Compromisso;
  }

  public int getNR_Proximo_Bordero () {
    return NR_Proximo_Bordero;
  }

  public void setNR_Proximo_Bordero (int NR_Proximo_Bordero) {
    this.NR_Proximo_Bordero = NR_Proximo_Bordero;
  }

  public int getNR_Proxima_Viagem () {
    return NR_Proxima_Viagem;
  }

  public void setNR_Proxima_Viagem (int NR_Proxima_Viagem) {
    this.NR_Proxima_Viagem = NR_Proxima_Viagem;
  }

  public String getNR_Proximo_CNPJ () {
    return NR_Proximo_CNPJ;
  }

  public void setNR_Proximo_CNPJ (String NR_Proximo_CNPJ) {
    this.NR_Proximo_CNPJ = NR_Proximo_CNPJ;
  }

  public int getNR_Proxima_Ordem_Servico () {
    return NR_Proxima_Ordem_Servico;
  }

  public void setNR_Proxima_Ordem_Servico (int NR_Proxima_Ordem_Servico) {
    this.NR_Proxima_Ordem_Servico = NR_Proxima_Ordem_Servico;
  }

  public int getNR_Proxima_Ocorrencia () {
    return NR_Proxima_Ocorrencia;
  }

  public void setNR_Proxima_Ocorrencia (int NR_Proxima_Ocorrencia) {
    this.NR_Proxima_Ocorrencia = NR_Proxima_Ocorrencia;
  }

  public int getNR_Proximo_Lancamento_Caixa () {
    return NR_Proximo_Lancamento_Caixa;
  }

  public void setNR_Proximo_Lancamento_Caixa (int NR_Proximo_Lancamento_Caixa) {
    this.NR_Proximo_Lancamento_Caixa = NR_Proximo_Lancamento_Caixa;
  }

  public int getNR_Proxima_Remessa_Comprovante () {
    return NR_Proxima_Remessa_Comprovante;
  }

  public void setNR_Proxima_Remessa_Comprovante (int NR_Proxima_Remessa_Comprovante) {
    this.NR_Proxima_Remessa_Comprovante = NR_Proxima_Remessa_Comprovante;
  }

  public int getNR_Proxima_Tabela () {
    return NR_Proxima_Tabela;
  }

  public void setNR_Proxima_Tabela (int NR_Proxima_Tabela) {
    this.NR_Proxima_Tabela = NR_Proxima_Tabela;
  }

  public int getNR_Proxima_Cotacao () {
    return NR_Proxima_Cotacao;
  }

  public void setNR_Proxima_Cotacao (int NR_Proxima_Cotacao) {
    this.NR_Proxima_Cotacao = NR_Proxima_Cotacao;
  }

  public double getVL_Maximo_Ordem_Frete () {
    return VL_Maximo_Ordem_Frete;
  }

  public void setVL_Maximo_Ordem_Frete (double VL_Maximo_Ordem_Frete) {
    this.VL_Maximo_Ordem_Frete = VL_Maximo_Ordem_Frete;
  }

  public double getVL_Frete_Minimo () {
    return VL_Frete_Minimo;
  }

  public void setVL_Frete_Minimo (double VL_Frete_Minimo) {
    this.VL_Frete_Minimo = VL_Frete_Minimo;
  }

  public String getCD_AIDOF_CTO_Nacional () {
    return CD_AIDOF_CTO_Nacional;
  }

  public void setCD_AIDOF_CTO_Nacional (String CD_AIDOF_CTO_Nacional) {
    this.CD_AIDOF_CTO_Nacional = CD_AIDOF_CTO_Nacional;
  }

  public String getCD_AIDOF_CTO_Internacional () {
    return CD_AIDOF_CTO_Internacional;
  }

  public void setCD_AIDOF_CTO_Internacional (String CD_AIDOF_CTO_Internacional) {
    this.CD_AIDOF_CTO_Internacional = CD_AIDOF_CTO_Internacional;
  }

  public String getCD_AIDOF_Manifesto () {
    return CD_AIDOF_Manifesto;
  }

  public void setCD_AIDOF_Manifesto (String CD_AIDOF_Manifesto) {
    this.CD_AIDOF_Manifesto = CD_AIDOF_Manifesto;
  }

  public String getCD_AIDOF_Fatura () {
    return CD_AIDOF_Fatura;
  }

  public void setCD_AIDOF_Fatura (String CD_AIDOF_Fatura) {
    this.CD_AIDOF_Fatura = CD_AIDOF_Fatura;
  }

  public String getCD_AIDOF_Coleta () {
    return CD_AIDOF_Coleta;
  }

  public void setCD_AIDOF_Coleta (String CD_AIDOF_Coleta) {
    this.CD_AIDOF_Coleta = CD_AIDOF_Coleta;
  }

  public String getCD_AIDOF_Ordem_Frete () {
    return CD_AIDOF_Ordem_Frete;
  }

  public void setCD_AIDOF_Ordem_Frete (String CD_AIDOF_Ordem_Frete) {
    this.CD_AIDOF_Ordem_Frete = CD_AIDOF_Ordem_Frete;
  }

  public String getCD_AIDOF_Minuta () {
    return CD_AIDOF_Minuta;
  }

  public void setCD_AIDOF_Minuta (String CD_AIDOF_Minuta) {
    this.CD_AIDOF_Minuta = CD_AIDOF_Minuta;
  }

  public String getCD_AIDOF_Romaneio () {
    return CD_AIDOF_Romaneio;
  }

  public void setCD_AIDOF_Romaneio (String CD_AIDOF_Romaneio) {
    this.CD_AIDOF_Romaneio = CD_AIDOF_Romaneio;
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

  public int getOID () {
    return oid;
  }

  public void setOID (int n) {
    this.oid = n;
  }

  public int getOID_Unidade () {
    return oid_Unidade;
  }

  public void setOID_Unidade (int n) {
    this.oid_Unidade = n;
  }

  public int getOID_AIDOF () {
    return oid_AIDOF;
  }

  public void setOID_AIDOF (int n) {
    this.oid_AIDOF = n;
  }

  public int getOID_AIDOF_Manifesto () {
    return oid_AIDOF_Manifesto;
  }

  public void setOID_AIDOF_Manifesto (int n) {
    this.oid_AIDOF_Manifesto = n;
  }

  public int getOID_AIDOF_Fatura () {
    return oid_AIDOF_Fatura;
  }

  public void setOID_AIDOF_Fatura (int n) {
    this.oid_AIDOF_Fatura = n;
  }

  public int getOID_AIDOF_Coleta () {
    return oid_AIDOF_Coleta;
  }

  public void setOID_AIDOF_Coleta (int n) {
    this.oid_AIDOF_Coleta = n;
  }

  public String getCD_AIDOF_AWB () {
    return this.CD_AIDOF_AWB;
  }

  public void setCD_AIDOF_AWB (String cd_aidof_awb) {
    this.CD_AIDOF_AWB = cd_aidof_awb;
  }

  public int getOid_AIDOF_AWB () {
    return this.oid_AIDOF_AWB;
  }

  public void setOid_AIDOF_AWB (int oid_AIDOF_AWB) {
    this.oid_AIDOF_AWB = oid_AIDOF_AWB;
  }

  public int getOID_AIDOF_CTO_Internacional () {
    return oid_AIDOF_CTO_Internacional;
  }

  public void setOID_AIDOF_CTO_Internacional (int n) {
    this.oid_AIDOF_CTO_Internacional = n;
  }

  public int getOID_AIDOF_Ordem_Frete () {
    return oid_AIDOF_Ordem_Frete;
  }

  public void setOID_AIDOF_Ordem_Frete (int n) {
    this.oid_AIDOF_Ordem_Frete = n;
  }

  public int getOID_AIDOF_Romaneio () {
    return oid_AIDOF_Romaneio;
  }

  public void setOID_AIDOF_Romaneio (int n) {
    this.oid_AIDOF_Romaneio = n;
  }

  public int getOID_AIDOF_Minuta () {
    return oid_AIDOF_Minuta;
  }

  public void setOID_AIDOF_Minuta (int n) {
    this.oid_AIDOF_Minuta = n;
  }

  public void setCD_AIDOF_Ordem_Frete_Adto (String CD_AIDOF_Ordem_Frete_Adto) {
    this.CD_AIDOF_Ordem_Frete_Adto = CD_AIDOF_Ordem_Frete_Adto;
  }

  public String getCD_AIDOF_Ordem_Frete_Adto () {
    return CD_AIDOF_Ordem_Frete_Adto;
  }

  public void setOID_AIDOF_Ordem_Frete_Adto (int OID_AIDOF_Ordem_Frete_Adto) {
    this.OID_AIDOF_Ordem_Frete_Adto = OID_AIDOF_Ordem_Frete_Adto;
  }

  public int getOID_AIDOF_Ordem_Frete_Adto () {
    return OID_AIDOF_Ordem_Frete_Adto;
  }

  public void setOid_AIDOF_Ordem_Frete_Adto (int oid_AIDOF_Ordem_Frete_Adto) {
    this.oid_AIDOF_Ordem_Frete_Adto = oid_AIDOF_Ordem_Frete_Adto;
  }

  public void setOid_AIDOF_Nota_Fiscal_Servico (int oid_AIDOF_Nota_Fiscal_Servico) {
    this.oid_AIDOF_Nota_Fiscal_Servico = oid_AIDOF_Nota_Fiscal_Servico;
  }

  public void setOid_AIDOF_ACT (int oid_AIDOF_ACT) {
    this.oid_AIDOF_ACT = oid_AIDOF_ACT;
  }

  public void setCD_AIDOF_ACT (String CD_AIDOF_ACT) {
    this.CD_AIDOF_ACT = CD_AIDOF_ACT;
  }

  public void setCD_AIDOF_Nota_Fiscal_Servico (String CD_AIDOF_Nota_Fiscal_Servico) {

    this.CD_AIDOF_Nota_Fiscal_Servico = CD_AIDOF_Nota_Fiscal_Servico;
  }

  public int getOid_AIDOF_Ordem_Frete_Adto () {
    return oid_AIDOF_Ordem_Frete_Adto;
  }

  public int getOid_AIDOF_Nota_Fiscal_Servico () {
    return oid_AIDOF_Nota_Fiscal_Servico;
  }

  public int getOid_AIDOF_ACT () {
    return oid_AIDOF_ACT;
  }

  public String getCD_AIDOF_ACT () {
    return CD_AIDOF_ACT;
  }

  public String getCD_AIDOF_Nota_Fiscal_Servico () {

    return CD_AIDOF_Nota_Fiscal_Servico;
  }

  public void insert () throws Exception {
    /*
     * Abre a conexão com o banco
     */
    int oid = 0;

    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NR_Proximo_Lote_Baixa do DSN
      // o NR_Proximo_Lote_Baixa de usuário e a senha do banco.
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
    try {
      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (
          "SELECT MAX(OID_Parametro_Filial) FROM Parametros_Filiais");

      while (cursor.next ()) {
        oid = cursor.getInt (1);
        setOID (oid + 1);
      }
      cursor.close ();
      stmt.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    String nr_prox_cnpj = "9999999" + String.valueOf (9000000 + (oid * 10000) + 1);


    /*
     * Define o insert.
     */
    StringBuffer buff = new StringBuffer ();
    buff.append ("INSERT INTO Parametros_Filiais    ");
    buff.append (" (OID_Parametro_Filial,           ");
    buff.append ("  OID_Unidade,                    ");
    buff.append ("  OID_AIDOF,                      ");
    buff.append ("  OID_AIDOF_Manifesto,            ");
    buff.append ("  OID_AIDOF_Fatura,               ");
    buff.append ("  OID_AIDOF_Coleta,               ");
    buff.append ("  OID_AIDOF_AWB,                  ");
    buff.append ("  OID_AIDOF_CTO_Internacional,    ");
    buff.append ("  OID_AIDOF_Ordem_Frete,          ");
    buff.append ("  OID_AIDOF_Romaneio,             ");
    buff.append ("  OID_AIDOF_Minuta,               ");
    buff.append ("  NR_Proxima_Entrega,             ");
    buff.append ("  NR_Proxima_Visita,              ");
    buff.append ("  NR_Proximo_Lote_Baixa,          ");
    buff.append ("  NR_Proximo_Acerto,              ");
    buff.append ("  NR_Proximo_Arquivo_EDI,         ");
    buff.append ("  NR_Proximo_Lote_Pagamento,      ");
    buff.append ("  NR_Proximo_Boletim_Ocorrencia,  ");
    buff.append ("  NR_Proxima_Proposta,            ");
    buff.append ("  NR_Proximo_Compromisso,         ");
    buff.append ("  NR_Proximo_Bordero,             ");
    buff.append ("  NR_Proxima_Viagem,              ");
    buff.append ("  NR_Proximo_CNPJ,                ");
    buff.append ("  NR_Proxima_Ordem_Servico,       ");
    buff.append ("  NR_Proxima_Ocorrencia,          ");
    buff.append ("  NR_Proximo_Lancamento_Caixa,    ");
    buff.append ("  NR_Proxima_Remessa_Comprovante, ");
    buff.append ("  NR_Proxima_Tabela,              ");
    buff.append ("  NR_Proxima_Cotacao,             ");
    buff.append ("  NM_Modelo_Conhecimento,         ");
    buff.append ("  NR_Dias_Revisao_Cadastral,      ");
    buff.append ("  VL_Maximo_Ordem_Frete,          ");
    buff.append ("  VL_Frete_Minimo,                ");
    buff.append ("  DT_Stamp,                       ");
    buff.append ("  Usuario_Stamp,                  ");
    buff.append ("  DM_Stamp,                       ");
    buff.append ("  OID_AIDOF_Nota_Fiscal_Servico, ");
    buff.append ("  OID_AIDOF_Nota_Fiscal, ");
    buff.append ("  OID_AIDOF_ACT, ");
    buff.append ("  OID_AIDOF_Ordem_Frete_Adto)     ");
    buff.append ("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
    /*1-2-3-4-5-6-7-8-9-0-1-2-3-4-5-6-7-8-9-0-1-2-3-4-5-6-7-8-9-0-1-2-3-4-5-6-7-8-9-0-
       /*
      * Define os dados do SQL
      * e executa o insert no banco.
      */
     try {
       PreparedStatement pstmt =
           conn.prepareStatement (buff.toString ());
       pstmt.setInt (1 , getOID ());
       pstmt.setInt (2 , getOID_Unidade ());
       pstmt.setInt (3 , getOID_AIDOF ());
       pstmt.setInt (4 , getOID_AIDOF_Manifesto ());
       pstmt.setInt (5 , getOID_AIDOF_Fatura ());
       pstmt.setInt (6 , getOID_AIDOF_Coleta ());
       pstmt.setInt (7 , getOid_AIDOF_AWB ());
       pstmt.setInt (8 , getOID_AIDOF_CTO_Internacional ());
       pstmt.setInt (9 , getOID_AIDOF_Ordem_Frete ());
       pstmt.setInt (10 , getOID_AIDOF_Romaneio ());
       pstmt.setInt (11 , getOID_AIDOF_Minuta ());
       pstmt.setInt (12 , getNR_Proxima_Entrega ());
       pstmt.setInt (13 , getNR_Proxima_Visita ());
       pstmt.setInt (14 , getNR_Proximo_Lote_Baixa ());
       pstmt.setInt (15 , getNR_Proximo_Acerto ());
       pstmt.setInt (16 , getNR_Proximo_Arquivo_EDI ());
       pstmt.setInt (17 , getNR_Proximo_Lote_Pagamento ());
       pstmt.setInt (18 , getNR_Proximo_Boletim_Ocorrencia ());
       pstmt.setInt (19 , getNR_Proxima_Proposta ());
       pstmt.setInt (20 , getNR_Proximo_Compromisso ());
       pstmt.setInt (21 , getNR_Proximo_Bordero ());
       pstmt.setInt (22 , getNR_Proxima_Viagem ());
       pstmt.setString (23 , nr_prox_cnpj);
       pstmt.setInt (24 , getNR_Proxima_Ordem_Servico ());
       pstmt.setInt (25 , getNR_Proxima_Ocorrencia ());
       pstmt.setInt (26 , getNR_Proximo_Lancamento_Caixa ());
       pstmt.setInt (27 , getNR_Proxima_Remessa_Comprovante ());
       pstmt.setInt (28 , getNR_Proxima_Tabela ());
       pstmt.setInt (29 , getNR_Proxima_Cotacao ());
       pstmt.setString (30 , getNM_Modelo_Conhecimento ());
       pstmt.setInt (31 , getNR_Dias_Revisao_Cadastral ());
       pstmt.setDouble (32 , getVL_Maximo_Ordem_Frete ());
       pstmt.setDouble (33 , getVL_Frete_Minimo ());
       pstmt.setString (34 , getDt_Stamp ());
       pstmt.setString (35 , getUsuario_Stamp ());
       pstmt.setString (36 , getDm_Stamp ());
       pstmt.setInt (37 , getOid_AIDOF_Nota_Fiscal_Servico ());
       pstmt.setInt (38 , getOid_AIDOF_Nota_Fiscal());
       pstmt.setInt (39 , getOid_AIDOF_ACT ());
       pstmt.setInt (40 , getOID_AIDOF_Ordem_Frete_Adto ());

       // System.out.println (" inclui param ->>" + pstmt.toString ());

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
      // passando como parâmetro o NR_Proximo_Lote_Baixa do DSN
      // o NR_Proximo_Lote_Baixa de usuário e a senha do banco.
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
    buff.append ("UPDATE Parametros_Filiais SET       ");
    buff.append ("  OID_AIDOF=?,                     ");
    buff.append ("  OID_AIDOF_Manifesto=?,            ");
    buff.append ("  OID_AIDOF_Fatura=?,               ");
    buff.append ("  OID_AIDOF_Coleta=?,               ");
    buff.append ("  OID_AIDOF_AWB=?,                  ");
    buff.append ("  OID_AIDOF_CTO_Internacional=?,    ");
    buff.append ("  OID_AIDOF_Ordem_Frete=?,          ");
    buff.append ("  OID_AIDOF_Romaneio=?,             ");
    buff.append ("  OID_AIDOF_Minuta=?,               ");
    buff.append ("  NR_Proxima_Entrega=?,             ");
    buff.append ("  NR_Proxima_Visita=?,              ");
    buff.append ("  NR_Proximo_Lote_Baixa=?,          ");
    buff.append ("  NR_Proximo_Acerto=?,              ");
    buff.append ("  NR_Proximo_Arquivo_EDI=?,         ");
    buff.append ("  NR_Proximo_Lote_Pagamento=?,      ");
    buff.append ("  NR_Proximo_Boletim_Ocorrencia=?,  ");
    buff.append ("  NR_Proxima_Proposta=?,            ");
    buff.append ("  NR_Proximo_Compromisso=?,         ");
    buff.append ("  NR_Proximo_Bordero=?,             ");
    buff.append ("  NR_Proxima_Viagem=?,              ");
    buff.append ("  OID_AIDOF_Ordem_Frete_Adto=?,     ");
    buff.append ("  NR_Proxima_Ordem_Servico=?,       ");
    buff.append ("  NR_Proxima_Ocorrencia=?,          ");
    buff.append ("  NR_Proximo_Lancamento_Caixa=?,    ");
    buff.append ("  NR_Proxima_Remessa_Comprovante=?, ");
    buff.append ("  NR_Proxima_Tabela=?,              ");
    buff.append ("  NR_Proxima_Cotacao=?,             ");
    buff.append ("  NM_Modelo_Conhecimento=?,         ");
    buff.append ("  NR_Dias_Revisao_Cadastral=?,      ");
    buff.append ("  VL_Maximo_Ordem_Frete=?,          ");
    buff.append ("  VL_Frete_Minimo=?,                ");
    buff.append ("  Dt_Stamp=?,                       ");
    buff.append ("  Usuario_Stamp=?,                  ");
    buff.append ("  OID_AIDOF_Nota_Fiscal_Servico=?,  ");
    buff.append ("  OID_AIDOF_Nota_Fiscal=?,          ");
    buff.append ("  OID_AIDOF_ACT=?,                  ");
    buff.append ("  Dm_Stamp=?                        ");
    buff.append ("WHERE OID_Parametro_Filial=?        ");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setInt (1 , getOID_AIDOF ());
      pstmt.setInt (2 , getOID_AIDOF_Manifesto ());
      pstmt.setInt (3 , getOID_AIDOF_Fatura ());
      pstmt.setInt (4 , getOID_AIDOF_Coleta ());
      if (getOid_AIDOF_AWB () != 0) {
        pstmt.setInt (5 , getOid_AIDOF_AWB ());
      }
      else {
        pstmt.setNull (5 , Types.INTEGER);
      }
      pstmt.setInt (6 , getOID_AIDOF_CTO_Internacional ());
      pstmt.setInt (7 , getOID_AIDOF_Ordem_Frete ());
      pstmt.setInt (8 , getOID_AIDOF_Romaneio ());
      pstmt.setInt (9 , getOID_AIDOF_Minuta ());
      pstmt.setInt (10 , getNR_Proxima_Entrega ());
      pstmt.setInt (11 , getNR_Proxima_Visita ());
      pstmt.setInt (12 , getNR_Proximo_Lote_Baixa ());
      pstmt.setInt (13 , getNR_Proximo_Acerto ());
      pstmt.setInt (14 , getNR_Proximo_Arquivo_EDI ());
      pstmt.setInt (15 , getNR_Proximo_Lote_Pagamento ());
      pstmt.setInt (16 , getNR_Proximo_Boletim_Ocorrencia ());
      pstmt.setInt (17 , getNR_Proxima_Proposta ());
      pstmt.setInt (18 , getNR_Proximo_Compromisso ());
      pstmt.setInt (19 , getNR_Proximo_Bordero ());
      pstmt.setInt (20 , getNR_Proxima_Viagem ());
      pstmt.setInt (21 , getOID_AIDOF_Ordem_Frete_Adto ());
      pstmt.setInt (22 , getNR_Proxima_Ordem_Servico ());
      pstmt.setInt (23 , getNR_Proxima_Ocorrencia ());
      pstmt.setInt (24 , getNR_Proximo_Lancamento_Caixa ());
      pstmt.setInt (25 , getNR_Proxima_Remessa_Comprovante ());
      pstmt.setInt (26 , getNR_Proxima_Tabela ());
      pstmt.setInt (27 , getNR_Proxima_Cotacao ());
      pstmt.setString (28 , getNM_Modelo_Conhecimento ());
      pstmt.setInt (29 , getNR_Dias_Revisao_Cadastral ());
      pstmt.setDouble (30 , getVL_Maximo_Ordem_Frete ());
      pstmt.setDouble (31 , getVL_Frete_Minimo ());
      pstmt.setDate (32 , FormataData.formataDataTB (getDt_Stamp ()));
      pstmt.setString (33 , getUsuario_Stamp ());
      pstmt.setInt (34 , getOid_AIDOF_Nota_Fiscal_Servico ());
      pstmt.setInt (35 , getOid_AIDOF_Nota_Fiscal());
      pstmt.setInt (36 , getOid_AIDOF_ACT ());
      pstmt.setString (37 , getDm_Stamp ());
      pstmt.setInt (38 , getOID ());
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
      // passando como parâmetro o NR_Proximo_Lote_Baixa do DSN
      // o NR_Proximo_Lote_Baixa de usuário e a senha do banco.
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
    buff.append ("DELETE FROM Parametros_Filiais ");
    buff.append ("WHERE OID_Parametro_Filial=?");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setInt (1 , getOID ());
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

  public static final Parametro_FilialBean getByOID (int oid) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NR_Proximo_Lote_Baixa do DSN
      // o NR_Proximo_Lote_Baixa de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    Parametro_FilialBean p = new Parametro_FilialBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT                                                ");
      buff.append ("   Parametros_Filiais.OID_Parametro_Filial,           ");
      buff.append ("	Parametros_Filiais.OID_Unidade,                    ");
      buff.append ("	Parametros_Filiais.OID_AIDOF,                      ");
      buff.append ("	Parametros_Filiais.OID_AIDOF_Manifesto,            ");
      buff.append ("	Parametros_Filiais.OID_AIDOF_Fatura,               ");
      buff.append ("	Parametros_Filiais.OID_AIDOF_Coleta,               ");
      buff.append ("	Parametros_Filiais.OID_AIDOF_AWB,                  ");
      buff.append ("	Parametros_Filiais.OID_AIDOF_CTO_Internacional,    ");
      buff.append ("	Parametros_Filiais.OID_AIDOF_Ordem_Frete,          ");
      buff.append ("	Parametros_Filiais.OID_AIDOF_Romaneio,             ");
      buff.append ("	Parametros_Filiais.OID_AIDOF_Minuta,               ");
      buff.append ("	Parametros_Filiais.NR_Proxima_Entrega,             ");
      buff.append ("	Parametros_Filiais.NR_Proxima_Visita,              ");
      buff.append ("	Parametros_Filiais.NR_Proximo_Lote_Baixa,          ");
      buff.append ("	Parametros_Filiais.NR_Proximo_Acerto,              ");
      buff.append ("	Parametros_Filiais.NR_Proximo_Arquivo_EDI,         ");
      buff.append ("	Parametros_Filiais.NR_Proximo_Lote_Pagamento,      ");
      buff.append ("	Parametros_Filiais.NR_Proximo_Boletim_Ocorrencia,  ");
      buff.append ("	Parametros_Filiais.NR_Proxima_Proposta,            ");
      buff.append ("	Parametros_Filiais.NR_Proximo_Compromisso,         ");
      buff.append ("	Parametros_Filiais.NR_Proximo_Bordero,             ");
      buff.append ("	Parametros_Filiais.NR_Proxima_Viagem,              ");
      buff.append ("	Parametros_Filiais.NR_Proximo_CNPJ,                ");
      buff.append ("	Parametros_Filiais.NR_Proxima_Ordem_Servico,       ");
      buff.append ("	Parametros_Filiais.NR_Proxima_Ocorrencia,          ");
      buff.append ("	Parametros_Filiais.NR_Proximo_Lancamento_Caixa,    ");
      buff.append ("	Parametros_Filiais.NR_Proxima_Remessa_Comprovante, ");
      buff.append ("	Parametros_Filiais.NR_Proxima_Tabela,              ");
      buff.append ("	Parametros_Filiais.NR_Proxima_Cotacao,             ");
      buff.append ("	Parametros_Filiais.NM_Modelo_Conhecimento,         ");
      buff.append ("	Parametros_Filiais.NR_Dias_Revisao_Cadastral,      ");
      buff.append ("	Parametros_Filiais.VL_Maximo_Ordem_Frete,          ");
      buff.append ("	Parametros_Filiais.VL_Frete_Minimo,                ");
      buff.append ("	Parametros_Filiais.DT_Stamp,                       ");
      buff.append ("	Parametros_Filiais.Usuario_Stamp,                  ");
      buff.append ("	Parametros_Filiais.DM_Stamp,                       ");
      buff.append ("	Unidades.CD_Unidade,                               ");
      buff.append ("	Parametros_Filiais.OID_AIDOF_Ordem_Frete_Adto,     ");
      buff.append ("	Parametros_Filiais.OID_AIDOF_ACT      ");
      buff.append ("FROM                                                  ");
      buff.append ("   Parametros_Filiais,                                ");
      buff.append ("   Unidades                                           ");
      buff.append ("WHERE Parametros_Filiais.OID_Unidade = Unidades.OID_Unidade ");
      buff.append ("AND Parametros_Filiais.OID_Parametro_Filial = ");
      buff.append (oid);

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID (cursor.getInt (1));
        p.setOID_Unidade (cursor.getInt (2));
        p.setOID_AIDOF (cursor.getInt (3));
        p.setOID_AIDOF_Manifesto (cursor.getInt (4));
        p.setOID_AIDOF_Fatura (cursor.getInt (5));
        p.setOID_AIDOF_Coleta (cursor.getInt (6));
        p.setOid_AIDOF_AWB (cursor.getInt (7));
        p.setOID_AIDOF_CTO_Internacional (cursor.getInt (8));
        p.setOID_AIDOF_Ordem_Frete (cursor.getInt (9));
        p.setOID_AIDOF_Romaneio (cursor.getInt (10));
        p.setOID_AIDOF_Minuta (cursor.getInt (11));
        p.setNR_Proxima_Entrega (cursor.getInt (12));
        p.setNR_Proxima_Visita (cursor.getInt (13));
        p.setNR_Proximo_Lote_Baixa (cursor.getInt (14));
        p.setNR_Proximo_Acerto (cursor.getInt (15));
        p.setNR_Proximo_Arquivo_EDI (cursor.getInt (16));
        p.setNR_Proximo_Lote_Pagamento (cursor.getInt (17));
        p.setNR_Proximo_Boletim_Ocorrencia (cursor.getInt (18));
        p.setNR_Proxima_Proposta (cursor.getInt (19));
        p.setNR_Proximo_Compromisso (cursor.getInt (20));
        p.setNR_Proximo_Bordero (cursor.getInt (21));
        p.setNR_Proxima_Viagem (cursor.getInt (22));
        p.setNR_Proximo_CNPJ (cursor.getString (23));
        p.setNR_Proxima_Ordem_Servico (cursor.getInt (24));
        p.setNR_Proxima_Ocorrencia (cursor.getInt (25));
        p.setNR_Proximo_Lancamento_Caixa (cursor.getInt (26));
        p.setNR_Proxima_Remessa_Comprovante (cursor.getInt (27));
        p.setNR_Proxima_Tabela (cursor.getInt (28));
        p.setNR_Proxima_Cotacao (cursor.getInt (29));
        p.setNM_Modelo_Conhecimento (cursor.getString (30));
        p.setNR_Dias_Revisao_Cadastral (cursor.getInt (31));
        p.setVL_Maximo_Ordem_Frete (cursor.getDouble (32));
        p.setVL_Frete_Minimo (cursor.getDouble (33));
        p.setDt_Stamp (cursor.getString (34));
        p.setUsuario_Stamp (cursor.getString (35));
        p.setDm_Stamp (cursor.getString (36));
        p.setCD_Unidade (cursor.getString (37));
        p.setOID_AIDOF_Ordem_Frete_Adto (cursor.getInt (38));
        p.setOid_AIDOF_ACT (cursor.getInt (39));
      }

      int v_AIDOF = p.getOID_AIDOF ();

      buff.delete (0 , buff.length ());

      buff.append ("SELECT                 ");
      buff.append (" AIDOF.OID_AIDOF,      ");
      buff.append (" AIDOF.CD_AIDOF        ");
      buff.append ("FROM                   ");
      buff.append (" AIDOF                 ");
      buff.append ("WHERE AIDOF.OID_AIDOF =");
      buff.append (v_AIDOF);

      cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID_AIDOF (cursor.getInt (1));
        p.setCD_AIDOF_CTO_Nacional (cursor.getString (2));
      }

      v_AIDOF = p.getOID_AIDOF_Manifesto ();

      buff.delete (0 , buff.length ());

      buff.append ("SELECT                 ");
      buff.append (" AIDOF.OID_AIDOF,      ");
      buff.append (" AIDOF.CD_AIDOF        ");
      buff.append ("FROM                   ");
      buff.append (" AIDOF                 ");
      buff.append ("WHERE AIDOF.OID_AIDOF =");
      buff.append (v_AIDOF);

      cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID_AIDOF_Manifesto (cursor.getInt (1));
        p.setCD_AIDOF_Manifesto (cursor.getString (2));
      }

      v_AIDOF = p.getOID_AIDOF_Fatura ();

      buff.delete (0 , buff.length ());

      buff.append ("SELECT                 ");
      buff.append (" AIDOF.OID_AIDOF,      ");
      buff.append (" AIDOF.CD_AIDOF        ");
      buff.append ("FROM                   ");
      buff.append (" AIDOF                 ");
      buff.append ("WHERE AIDOF.OID_AIDOF =");
      buff.append (v_AIDOF);

      cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID_AIDOF_Fatura (cursor.getInt (1));
        p.setCD_AIDOF_Fatura (cursor.getString (2));
      }

      v_AIDOF = p.getOID_AIDOF_Coleta ();

      buff.delete (0 , buff.length ());

      buff.append ("SELECT                 ");
      buff.append (" AIDOF.OID_AIDOF,      ");
      buff.append (" AIDOF.CD_AIDOF        ");
      buff.append ("FROM                   ");
      buff.append (" AIDOF                 ");
      buff.append ("WHERE AIDOF.OID_AIDOF =");
      buff.append (v_AIDOF);

      cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID_AIDOF_Coleta (cursor.getInt (1));
        p.setCD_AIDOF_Coleta (cursor.getString (2));
      }

      v_AIDOF = p.getOid_AIDOF_AWB ();
      buff.delete (0 , buff.length ());

      buff.append ("SELECT                 ");
      buff.append (" AIDOF.OID_AIDOF,      ");
      buff.append (" AIDOF.CD_AIDOF        ");
      buff.append ("FROM                   ");
      buff.append (" AIDOF                 ");
      buff.append ("WHERE AIDOF.OID_AIDOF =");
      buff.append (v_AIDOF);
      cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOid_AIDOF_AWB (cursor.getInt (1));
        p.setCD_AIDOF_AWB (cursor.getString (2));
      }

      v_AIDOF = p.getOid_AIDOF_ACT ();
      buff.delete (0 , buff.length ());

      buff.append ("SELECT                 ");
      buff.append (" AIDOF.OID_AIDOF,      ");
      buff.append (" AIDOF.CD_AIDOF        ");
      buff.append ("FROM                   ");
      buff.append (" AIDOF                 ");
      buff.append ("WHERE AIDOF.OID_AIDOF =");
      buff.append (v_AIDOF);
      cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOid_AIDOF_ACT (cursor.getInt (1));
        p.setCD_AIDOF_ACT (cursor.getString (2));
      }

      v_AIDOF = p.getOID_AIDOF_CTO_Internacional ();
      buff.delete (0 , buff.length ());

      buff.append ("SELECT                 ");
      buff.append (" AIDOF.OID_AIDOF,      ");
      buff.append (" AIDOF.CD_AIDOF        ");
      buff.append ("FROM                   ");
      buff.append (" AIDOF                 ");
      buff.append ("WHERE AIDOF.OID_AIDOF =");
      buff.append (v_AIDOF);

      cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID_AIDOF_CTO_Internacional (cursor.getInt (1));
        p.setCD_AIDOF_CTO_Internacional (cursor.getString (2));
      }

      v_AIDOF = p.getOID_AIDOF_Ordem_Frete ();

      buff.delete (0 , buff.length ());

      buff.append ("SELECT                 ");
      buff.append (" AIDOF.OID_AIDOF,      ");
      buff.append (" AIDOF.CD_AIDOF        ");
      buff.append ("FROM                   ");
      buff.append (" AIDOF                 ");
      buff.append ("WHERE AIDOF.OID_AIDOF =");
      buff.append (v_AIDOF);

      cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID_AIDOF_Ordem_Frete (cursor.getInt (1));
        p.setCD_AIDOF_Ordem_Frete (cursor.getString (2));
      }

      v_AIDOF = p.getOID_AIDOF_Romaneio ();

      buff.delete (0 , buff.length ());

      buff.append ("SELECT                 ");
      buff.append (" AIDOF.OID_AIDOF,      ");
      buff.append (" AIDOF.CD_AIDOF        ");
      buff.append ("FROM                   ");
      buff.append (" AIDOF                 ");
      buff.append ("WHERE AIDOF.OID_AIDOF =");
      buff.append (v_AIDOF);

      cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID_AIDOF_Romaneio (cursor.getInt (1));
        p.setCD_AIDOF_Romaneio (cursor.getString (2));
      }

      v_AIDOF = p.getOID_AIDOF_Minuta ();

      buff.delete (0 , buff.length ());

      buff.append ("SELECT                 ");
      buff.append (" AIDOF.OID_AIDOF,      ");
      buff.append (" AIDOF.CD_AIDOF        ");
      buff.append ("FROM                   ");
      buff.append (" AIDOF                 ");
      buff.append ("WHERE AIDOF.OID_AIDOF =");
      buff.append (v_AIDOF);

      cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID_AIDOF_Minuta (cursor.getInt (1));
        p.setCD_AIDOF_Minuta (cursor.getString (2));
      }

      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    return p;
  }

  public static final Parametro_FilialBean getByOID_AIDOF (int oid_AIDOF) throws Exception {
    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    Parametro_FilialBean p = new Parametro_FilialBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT                               ");
      buff.append ("	Parametros_Filiais.OID_AIDOF      ");
      buff.append ("FROM                                 ");
      buff.append ("   Parametros_Filiais                ");
      buff.append ("WHERE Parametros_Filiais.OID_AIDOF = ");
      buff.append (oid_AIDOF);

//			// System.out.println("Param_FilialBean.getByOID_AIDOF() sql = "+buff.toString());

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID_AIDOF (cursor.getInt (1));
      }

      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    return p;
  }

  public static final Parametro_FilialBean getByOID_AIDOF_Manifesto (int oid_AIDOF) throws Exception {
    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    Parametro_FilialBean p = new Parametro_FilialBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT                               ");
      buff.append ("	Parametros_Filiais.OID_AIDOF      ");
      buff.append ("FROM                                 ");
      buff.append ("   Parametros_Filiais                ");
      buff.append ("WHERE Parametros_Filiais.OID_AIDOF_Manifesto = ");
      buff.append (oid_AIDOF);

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID_AIDOF (cursor.getInt (1));
      }

      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    return p;
  }

  public static final Parametro_FilialBean getByOID_AIDOF_Fatura (int oid_AIDOF) throws Exception {
    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    Parametro_FilialBean p = new Parametro_FilialBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT                               ");
      buff.append ("	Parametros_Filiais.OID_AIDOF      ");
      buff.append ("FROM                                 ");
      buff.append ("   Parametros_Filiais                ");
      buff.append ("WHERE Parametros_Filiais.OID_AIDOF_Fatura = ");
      buff.append (oid_AIDOF);

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID_AIDOF (cursor.getInt (1));
      }

      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    return p;
  }

  public static final Parametro_FilialBean getByOID_AIDOF_Coleta (int oid_AIDOF) throws Exception {
    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    Parametro_FilialBean p = new Parametro_FilialBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT                               ");
      buff.append ("	Parametros_Filiais.OID_AIDOF      ");
      buff.append ("FROM                                 ");
      buff.append ("   Parametros_Filiais                ");
      buff.append ("WHERE Parametros_Filiais.OID_AIDOF_Coleta = ");
      buff.append (oid_AIDOF);

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID_AIDOF (cursor.getInt (1));
      }

      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    return p;
  }

  public static final Parametro_FilialBean getByOID_AIDOF_AWB (int oid_AIDOF) throws Exception {
    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    Parametro_FilialBean p = new Parametro_FilialBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT                               ");
      buff.append ("	Parametros_Filiais.OID_AIDOF      ");
      buff.append ("FROM                                 ");
      buff.append ("   Parametros_Filiais                ");
      buff.append ("WHERE Parametros_Filiais.OID_AIDOF_AWB = ");
      buff.append (oid_AIDOF);

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID_AIDOF (cursor.getInt (1));
      }

      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    return p;
  }

  public static final Parametro_FilialBean getByOID_AIDOF_CTO_Internacional (int oid_AIDOF) throws Exception {
    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    Parametro_FilialBean p = new Parametro_FilialBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT                               ");
      buff.append ("	Parametros_Filiais.OID_AIDOF      ");
      buff.append ("FROM                                 ");
      buff.append ("   Parametros_Filiais                ");
      buff.append ("WHERE Parametros_Filiais.OID_AIDOF_CTO_Internacional = ");
      buff.append (oid_AIDOF);

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID_AIDOF (cursor.getInt (1));
      }

      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    return p;
  }

  public static final Parametro_FilialBean getByOID_AIDOF_Ordem_Frete (int oid_AIDOF) throws Exception {
    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    Parametro_FilialBean p = new Parametro_FilialBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT                               ");
      buff.append ("	Parametros_Filiais.OID_AIDOF      ");
      buff.append ("FROM                                 ");
      buff.append ("   Parametros_Filiais                ");
      buff.append ("WHERE Parametros_Filiais.OID_AIDOF_Ordem_Frete = ");
      buff.append (oid_AIDOF);

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID_AIDOF (cursor.getInt (1));
      }

      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    return p;
  }

  public static final Parametro_FilialBean getByOID_AIDOF_Ordem_Frete_Adto (int oid_AIDOF) throws Exception {
    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    Parametro_FilialBean p = new Parametro_FilialBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT                               ");
      buff.append ("	Parametros_Filiais.OID_AIDOF      ");
      buff.append ("FROM                                 ");
      buff.append ("   Parametros_Filiais                ");
      buff.append ("WHERE Parametros_Filiais.OID_AIDOF_Ordem_Frete_Adto = ");
      buff.append (oid_AIDOF);

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID_AIDOF (cursor.getInt (1));
      }

      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    return p;
  }

  public static final Parametro_FilialBean getByOID_AIDOF_Romaneio (int oid_AIDOF) throws Exception {
    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    Parametro_FilialBean p = new Parametro_FilialBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT                               ");
      buff.append ("	Parametros_Filiais.OID_AIDOF      ");
      buff.append ("FROM                                 ");
      buff.append ("   Parametros_Filiais                ");
      buff.append ("WHERE Parametros_Filiais.OID_AIDOF_Romaneio = ");
      buff.append (oid_AIDOF);

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID_AIDOF (cursor.getInt (1));
      }

      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    return p;
  }

  public static final Parametro_FilialBean getByOid_AIDOF_Nota_Fiscal_Servico (int oid_AIDOF) throws Exception {
    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    Parametro_FilialBean p = new Parametro_FilialBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT                               ");
      buff.append ("	Parametros_Filiais.OID_AIDOF      ");
      buff.append ("FROM                                 ");
      buff.append ("   Parametros_Filiais                ");
      buff.append ("WHERE Parametros_Filiais.OID_AIDOF_Nota_Fiscal_Servico = ");
      buff.append (oid_AIDOF);

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID_AIDOF (cursor.getInt (1));
      }

      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    return p;
  }

  public static final Parametro_FilialBean getByOID_AIDOF_Minuta (int oid_AIDOF) throws Exception {
    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    Parametro_FilialBean p = new Parametro_FilialBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT                               ");
      buff.append ("	Parametros_Filiais.OID_AIDOF      ");
      buff.append ("FROM                                 ");
      buff.append ("   Parametros_Filiais                ");
      buff.append ("WHERE Parametros_Filiais.OID_AIDOF_Minuta = ");
      buff.append (oid_AIDOF);

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID_AIDOF (cursor.getInt (1));
      }

      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    return p;
  }

  public static final Parametro_FilialBean getByOID_Unidade_Parametro (int oid_Unidade) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NR_Proximo_Lote_Baixa do DSN
      // o NR_Proximo_Lote_Baixa de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    Parametro_FilialBean p = new Parametro_FilialBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT                                                ");
      buff.append ("   Parametros_Filiais.OID_Parametro_Filial,           ");
      buff.append ("	Parametros_Filiais.OID_Unidade,                    ");
      buff.append ("	Parametros_Filiais.OID_AIDOF,                      ");
      buff.append ("	Parametros_Filiais.OID_AIDOF_Manifesto,            ");
      buff.append ("	Parametros_Filiais.OID_AIDOF_Fatura,               ");
      buff.append ("	Parametros_Filiais.OID_AIDOF_Coleta,               ");
      buff.append ("	Parametros_Filiais.OID_AIDOF_AWB,                  ");
      buff.append ("	Parametros_Filiais.OID_AIDOF_CTO_Internacional,    ");
      buff.append ("	Parametros_Filiais.OID_AIDOF_Ordem_Frete,          ");
      buff.append ("	Parametros_Filiais.OID_AIDOF_Romaneio,             ");
      buff.append ("	Parametros_Filiais.OID_AIDOF_Minuta,               ");
      buff.append ("	Parametros_Filiais.NR_Proxima_Entrega,             ");
      buff.append ("	Parametros_Filiais.NR_Proxima_Visita,              ");
      buff.append ("	Parametros_Filiais.NR_Proximo_Lote_Baixa,          ");
      buff.append ("	Parametros_Filiais.NR_Proximo_Acerto,              ");
      buff.append ("	Parametros_Filiais.NR_Proximo_Arquivo_EDI,         ");
      buff.append ("	Parametros_Filiais.NR_Proximo_Lote_Pagamento,      ");
      buff.append ("	Parametros_Filiais.NR_Proximo_Boletim_Ocorrencia,  ");
      buff.append ("	Parametros_Filiais.NR_Proxima_Proposta,            ");
      buff.append ("	Parametros_Filiais.NR_Proximo_Compromisso,         ");
      buff.append ("	Parametros_Filiais.NR_Proximo_Bordero,             ");
      buff.append ("	Parametros_Filiais.NR_Proxima_Viagem,              ");
      buff.append ("	Parametros_Filiais.NR_Proximo_CNPJ,                ");
      buff.append ("	Parametros_Filiais.NR_Proxima_Ordem_Servico,       ");
      buff.append ("	Parametros_Filiais.NR_Proxima_Ocorrencia,          ");
      buff.append ("	Parametros_Filiais.NR_Proximo_Lancamento_Caixa,    ");
      buff.append ("	Parametros_Filiais.NR_Proxima_Remessa_Comprovante, ");
      buff.append ("	Parametros_Filiais.NR_Proxima_Tabela,              ");
      buff.append ("	Parametros_Filiais.NR_Proxima_Cotacao,             ");
      buff.append ("	Parametros_Filiais.NM_Modelo_Conhecimento,         ");
      buff.append ("	Parametros_Filiais.NR_Dias_Revisao_Cadastral,      ");
      buff.append ("	Parametros_Filiais.VL_Maximo_Ordem_Frete,          ");
      buff.append ("	Parametros_Filiais.VL_Frete_Minimo,                ");
      buff.append ("	Parametros_Filiais.DT_Stamp,                       ");
      buff.append ("	Parametros_Filiais.Usuario_Stamp,                  ");
      buff.append ("	Parametros_Filiais.DM_Stamp,                       ");
      buff.append ("	Unidades.CD_Unidade,                               ");
      buff.append ("	Parametros_Filiais.OID_AIDOF_Nota_Fiscal_Servico,  ");
      buff.append ("	Parametros_Filiais.OID_AIDOF_Nota_Fiscal,          ");
      buff.append ("	Parametros_Filiais.OID_AIDOF_ACT,                  ");
      buff.append ("	Parametros_Filiais.OID_AIDOF_Ordem_Frete_Adto      ");
      buff.append ("FROM                                                   ");
      buff.append ("   Parametros_Filiais,                                 ");
      buff.append ("   Unidades                                            ");
      buff.append ("WHERE Parametros_Filiais.OID_Unidade = Unidades.OID_Unidade ");
      buff.append ("AND Unidades.OID_Unidade = ");
      buff.append (oid_Unidade);

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID (cursor.getInt (1));
        p.setOID_Unidade (cursor.getInt (2));
        p.setOID_AIDOF (cursor.getInt (3));
        p.setOID_AIDOF_Manifesto (cursor.getInt (4));
        p.setOID_AIDOF_Fatura (cursor.getInt (5));
        p.setOID_AIDOF_Coleta (cursor.getInt (6));
        p.setOid_AIDOF_AWB (cursor.getInt (7));
        p.setOID_AIDOF_CTO_Internacional (cursor.getInt (8));
        p.setOID_AIDOF_Ordem_Frete (cursor.getInt (9));
        p.setOID_AIDOF_Romaneio (cursor.getInt (10));
        p.setOID_AIDOF_Minuta (cursor.getInt (11));
        p.setNR_Proxima_Entrega (cursor.getInt (12));
        p.setNR_Proxima_Visita (cursor.getInt (13));
        p.setNR_Proximo_Lote_Baixa (cursor.getInt (14));
        p.setNR_Proximo_Acerto (cursor.getInt (15));
        p.setNR_Proximo_Arquivo_EDI (cursor.getInt (16));
        p.setNR_Proximo_Lote_Pagamento (cursor.getInt (17));
        p.setNR_Proximo_Boletim_Ocorrencia (cursor.getInt (18));
        p.setNR_Proxima_Proposta (cursor.getInt (19));
        p.setNR_Proximo_Compromisso (cursor.getInt (20));
        p.setNR_Proximo_Bordero (cursor.getInt (21));
        p.setNR_Proxima_Viagem (cursor.getInt (22));
        p.setNR_Proximo_CNPJ (cursor.getString (23));
        p.setNR_Proxima_Ordem_Servico (cursor.getInt (24));
        p.setNR_Proxima_Ocorrencia (cursor.getInt (25));
        p.setNR_Proximo_Lancamento_Caixa (cursor.getInt (26));
        p.setNR_Proxima_Remessa_Comprovante (cursor.getInt (27));
        p.setNR_Proxima_Tabela (cursor.getInt (28));
        p.setNR_Proxima_Cotacao (cursor.getInt (29));
        p.setNM_Modelo_Conhecimento (cursor.getString (30));
        p.setNR_Dias_Revisao_Cadastral (cursor.getInt (31));
        p.setVL_Maximo_Ordem_Frete (cursor.getDouble (32));
        p.setVL_Frete_Minimo (cursor.getDouble (33));
        p.setDt_Stamp (cursor.getString (34));
        p.setUsuario_Stamp (cursor.getString (35));
        p.setDm_Stamp (cursor.getString (36));
        p.setCD_Unidade (cursor.getString (37));
        p.setOid_AIDOF_Nota_Fiscal_Servico (cursor.getInt (38));
        p.setOid_AIDOF_Nota_Fiscal(cursor.getInt (39));
        p.setOid_AIDOF_ACT (cursor.getInt (40));
        p.setOID_AIDOF_Ordem_Frete_Adto (cursor.getInt (41));
      }

      int v_AIDOF = p.getOID_AIDOF ();

      buff.delete (0 , buff.length ());

      buff.append ("SELECT                 ");
      buff.append (" AIDOF.OID_AIDOF,      ");
      buff.append (" AIDOF.CD_AIDOF        ");
      buff.append ("FROM                   ");
      buff.append (" AIDOF                 ");
      buff.append ("WHERE AIDOF.OID_AIDOF =");
      buff.append (v_AIDOF);

      cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID_AIDOF (cursor.getInt (1));
        p.setCD_AIDOF_CTO_Nacional (cursor.getString (2));
      }

      v_AIDOF = p.getOID_AIDOF_Manifesto ();

      buff.delete (0 , buff.length ());

      buff.append ("SELECT                 ");
      buff.append (" AIDOF.OID_AIDOF,      ");
      buff.append (" AIDOF.CD_AIDOF        ");
      buff.append ("FROM                   ");
      buff.append (" AIDOF                 ");
      buff.append ("WHERE AIDOF.OID_AIDOF =");
      buff.append (v_AIDOF);

      cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID_AIDOF_Manifesto (cursor.getInt (1));
        p.setCD_AIDOF_Manifesto (cursor.getString (2));
      }

      v_AIDOF = p.getOID_AIDOF_Fatura ();

      buff.delete (0 , buff.length ());

      buff.append ("SELECT                 ");
      buff.append (" AIDOF.OID_AIDOF,      ");
      buff.append (" AIDOF.CD_AIDOF        ");
      buff.append ("FROM                   ");
      buff.append (" AIDOF                 ");
      buff.append ("WHERE AIDOF.OID_AIDOF =");
      buff.append (v_AIDOF);

      cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID_AIDOF_Fatura (cursor.getInt (1));
        p.setCD_AIDOF_Fatura (cursor.getString (2));
      }

      v_AIDOF = p.getOID_AIDOF_Coleta ();

      buff.delete (0 , buff.length ());

      buff.append ("SELECT                 ");
      buff.append (" AIDOF.OID_AIDOF,      ");
      buff.append (" AIDOF.CD_AIDOF        ");
      buff.append ("FROM                   ");
      buff.append (" AIDOF                 ");
      buff.append ("WHERE AIDOF.OID_AIDOF =");
      buff.append (v_AIDOF);

      cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID_AIDOF_Coleta (cursor.getInt (1));
        p.setCD_AIDOF_Coleta (cursor.getString (2));
      }

      v_AIDOF = p.getOid_AIDOF_AWB ();

      buff.delete (0 , buff.length ());

      buff.append ("SELECT                 ");
      buff.append (" AIDOF.OID_AIDOF,      ");
      buff.append (" AIDOF.CD_AIDOF        ");
      buff.append ("FROM                   ");
      buff.append (" AIDOF                 ");
      buff.append ("WHERE AIDOF.OID_AIDOF =");
      buff.append (v_AIDOF);

      cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOid_AIDOF_AWB (cursor.getInt (1));
        p.setCD_AIDOF_AWB (cursor.getString (2));
      }

      v_AIDOF = p.getOid_AIDOF_ACT ();

      buff.delete (0 , buff.length ());

      buff.append ("SELECT                 ");
      buff.append (" AIDOF.OID_AIDOF,      ");
      buff.append (" AIDOF.CD_AIDOF        ");
      buff.append ("FROM                   ");
      buff.append (" AIDOF                 ");
      buff.append ("WHERE AIDOF.OID_AIDOF =");
      buff.append (v_AIDOF);

      cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOid_AIDOF_ACT (cursor.getInt (1));
        p.setCD_AIDOF_ACT (cursor.getString (2));
      }


      v_AIDOF = p.getOID_AIDOF_CTO_Internacional ();

      buff.delete (0 , buff.length ());

      buff.append ("SELECT                 ");
      buff.append (" AIDOF.OID_AIDOF,      ");
      buff.append (" AIDOF.CD_AIDOF        ");
      buff.append ("FROM                   ");
      buff.append (" AIDOF                 ");
      buff.append ("WHERE AIDOF.OID_AIDOF =");
      buff.append (v_AIDOF);

      cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID_AIDOF_CTO_Internacional (cursor.getInt (1));
        p.setCD_AIDOF_CTO_Internacional (cursor.getString (2));
      }

      v_AIDOF = p.getOID_AIDOF_Ordem_Frete ();

      buff.delete (0 , buff.length ());

      buff.append ("SELECT                 ");
      buff.append (" AIDOF.OID_AIDOF,      ");
      buff.append (" AIDOF.CD_AIDOF        ");
      buff.append ("FROM                   ");
      buff.append (" AIDOF                 ");
      buff.append ("WHERE AIDOF.OID_AIDOF =");
      buff.append (v_AIDOF);

      cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID_AIDOF_Ordem_Frete (cursor.getInt (1));
        p.setCD_AIDOF_Ordem_Frete (cursor.getString (2));
      }

      v_AIDOF = p.getOID_AIDOF_Ordem_Frete_Adto ();
// //// System.out.println(v_AIDOF);
      buff.delete (0 , buff.length ());

      buff.append ("SELECT                 ");
      buff.append (" AIDOF.OID_AIDOF,      ");
      buff.append (" AIDOF.CD_AIDOF        ");
      buff.append ("FROM                   ");
      buff.append (" AIDOF                 ");
      buff.append ("WHERE AIDOF.OID_AIDOF =");
      buff.append (v_AIDOF);

      cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID_AIDOF_Ordem_Frete_Adto (cursor.getInt (1));
        p.setCD_AIDOF_Ordem_Frete_Adto (cursor.getString (2));
      }

      v_AIDOF = p.getOID_AIDOF_Romaneio ();

      buff.delete (0 , buff.length ());

      buff.append ("SELECT                 ");
      buff.append (" AIDOF.OID_AIDOF,      ");
      buff.append (" AIDOF.CD_AIDOF        ");
      buff.append ("FROM                   ");
      buff.append (" AIDOF                 ");
      buff.append ("WHERE AIDOF.OID_AIDOF =");
      buff.append (v_AIDOF);

      cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID_AIDOF_Romaneio (cursor.getInt (1));
        p.setCD_AIDOF_Romaneio (cursor.getString (2));
      }

      v_AIDOF = p.getOid_AIDOF_Nota_Fiscal_Servico ();

      buff.delete (0 , buff.length ());

      buff.append ("SELECT                 ");
      buff.append (" AIDOF.OID_AIDOF,      ");
      buff.append (" AIDOF.CD_AIDOF        ");
      buff.append ("FROM                   ");
      buff.append (" AIDOF                 ");
      buff.append ("WHERE AIDOF.OID_AIDOF =");
      buff.append (v_AIDOF);

      cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOid_AIDOF_Nota_Fiscal_Servico (cursor.getInt (1));
        p.setCD_AIDOF_Nota_Fiscal_Servico (cursor.getString (2));
      }

      v_AIDOF = p.getOid_AIDOF_Nota_Fiscal();

      buff.delete (0 , buff.length ());

      buff.append ("SELECT                 ");
      buff.append (" AIDOF.OID_AIDOF,      ");
      buff.append (" AIDOF.CD_AIDOF        ");
      buff.append ("FROM                   ");
      buff.append (" AIDOF                 ");
      buff.append ("WHERE AIDOF.OID_AIDOF =");
      buff.append (v_AIDOF);

      cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOid_AIDOF_Nota_Fiscal(cursor.getInt (1));
        p.setCD_AIDOF_Nota_Fiscal(cursor.getString (2));
      }

      v_AIDOF = p.getOID_AIDOF_Minuta ();

      buff.delete (0 , buff.length ());

      buff.append ("SELECT                 ");
      buff.append (" AIDOF.OID_AIDOF,      ");
      buff.append (" AIDOF.CD_AIDOF        ");
      buff.append ("FROM                   ");
      buff.append (" AIDOF                 ");
      buff.append ("WHERE AIDOF.OID_AIDOF =");
      buff.append (v_AIDOF);

      cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setOID_AIDOF_Minuta (cursor.getInt (1));
        p.setCD_AIDOF_Minuta (cursor.getString (2));
      }

      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    return p;
  }

  public static final List getByOID_Unidade (int OID_Unidade) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NR_Proximo_Lote_Baixa do DSN
      // o NR_Proximo_Lote_Baixa de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    List Parametro_Filial_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT                                                ");
      buff.append ("   Parametros_Filiais.OID_Parametro_Filial,           ");
      buff.append ("	Parametros_Filiais.OID_Unidade,                    ");
      buff.append ("	Parametros_Filiais.OID_AIDOF,                      ");
      buff.append ("	Parametros_Filiais.OID_AIDOF_Manifesto,            ");
      buff.append ("	Parametros_Filiais.OID_AIDOF_Fatura,               ");
      buff.append ("	Parametros_Filiais.OID_AIDOF_Coleta,               ");
      buff.append ("	Parametros_Filiais.OID_AIDOF_AWB,                  ");
      buff.append ("	Parametros_Filiais.OID_AIDOF_CTO_Internacional,    ");
      buff.append ("	Parametros_Filiais.OID_AIDOF_Ordem_Frete,          ");
      buff.append ("	Parametros_Filiais.OID_AIDOF_Romaneio,             ");
      buff.append ("	Parametros_Filiais.OID_AIDOF_Minuta,               ");
      buff.append ("	Parametros_Filiais.NR_Proxima_Entrega,             ");
      buff.append ("	Parametros_Filiais.NR_Proxima_Visita,              ");
      buff.append ("	Parametros_Filiais.NR_Proximo_Lote_Baixa,          ");
      buff.append ("	Parametros_Filiais.NR_Proximo_Acerto,              ");
      buff.append ("	Parametros_Filiais.NR_Proximo_Arquivo_EDI,         ");
      buff.append ("	Parametros_Filiais.NR_Proximo_Lote_Pagamento,      ");
      buff.append ("	Parametros_Filiais.NR_Proximo_Boletim_Ocorrencia,  ");
      buff.append ("	Parametros_Filiais.NR_Proxima_Proposta,            ");
      buff.append ("	Parametros_Filiais.NR_Proximo_Compromisso,         ");
      buff.append ("	Parametros_Filiais.NR_Proximo_Bordero,             ");
      buff.append ("	Parametros_Filiais.NR_Proxima_Viagem,              ");
      buff.append ("	Parametros_Filiais.NR_Proximo_CNPJ,                ");
      buff.append ("	Parametros_Filiais.NR_Proxima_Ordem_Servico,       ");
      buff.append ("	Parametros_Filiais.NR_Proxima_Ocorrencia,          ");
      buff.append ("	Parametros_Filiais.NR_Proximo_Lancamento_Caixa,    ");
      buff.append ("	Parametros_Filiais.NR_Proxima_Remessa_Comprovante, ");
      buff.append ("	Parametros_Filiais.NR_Proxima_Tabela,              ");
      buff.append ("	Parametros_Filiais.NR_Proxima_Cotacao,             ");
      buff.append ("	Parametros_Filiais.NM_Modelo_Conhecimento,         ");
      buff.append ("	Parametros_Filiais.NR_Dias_Revisao_Cadastral,      ");
      buff.append ("	Parametros_Filiais.VL_Maximo_Ordem_Frete,          ");
      buff.append ("	Parametros_Filiais.VL_Frete_Minimo,                ");
      buff.append ("	Parametros_Filiais.DT_Stamp,                       ");
      buff.append ("	Parametros_Filiais.Usuario_Stamp,                  ");
      buff.append ("	Parametros_Filiais.DM_Stamp,                       ");
      buff.append ("	Unidades.CD_Unidade,                               ");
      buff.append ("	Parametros_Filiais.OID_AIDOF_Ordem_Frete           ");
      buff.append ("FROM                                                  ");
      buff.append ("   Parametros_Filiais,                                ");
      buff.append ("   Unidades                                           ");
      buff.append ("WHERE Parametros_Filiais.OID_Unidade = Unidades.OID_Unidade ");
      buff.append ("AND Parametros_Filiais.OID_Unidade =");
      buff.append (OID_Unidade);

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        Parametro_FilialBean p = new Parametro_FilialBean ();
        p.setOID (cursor.getInt (1));
        p.setOID_Unidade (cursor.getInt (2));
        p.setOID_AIDOF (cursor.getInt (3));
        p.setOID_AIDOF_Manifesto (cursor.getInt (4));
        p.setOID_AIDOF_Fatura (cursor.getInt (5));
        p.setOID_AIDOF_Coleta (cursor.getInt (6));
        p.setOid_AIDOF_AWB (cursor.getInt (7));
        p.setOID_AIDOF_CTO_Internacional (cursor.getInt (8));
        p.setOID_AIDOF_Ordem_Frete (cursor.getInt (9));
        p.setOID_AIDOF_Romaneio (cursor.getInt (10));
        p.setOID_AIDOF_Minuta (cursor.getInt (11));
        p.setNR_Proxima_Entrega (cursor.getInt (12));
        p.setNR_Proxima_Visita (cursor.getInt (13));
        p.setNR_Proximo_Lote_Baixa (cursor.getInt (14));
        p.setNR_Proximo_Acerto (cursor.getInt (15));
        p.setNR_Proximo_Arquivo_EDI (cursor.getInt (16));
        p.setNR_Proximo_Lote_Pagamento (cursor.getInt (17));
        p.setNR_Proximo_Boletim_Ocorrencia (cursor.getInt (18));
        p.setNR_Proxima_Proposta (cursor.getInt (19));
        p.setNR_Proximo_Compromisso (cursor.getInt (20));
        p.setNR_Proximo_Bordero (cursor.getInt (21));
        p.setNR_Proxima_Viagem (cursor.getInt (22));
        p.setNR_Proximo_CNPJ (cursor.getString (23));
        p.setNR_Proxima_Ordem_Servico (cursor.getInt (24));
        p.setNR_Proxima_Ocorrencia (cursor.getInt (25));
        p.setNR_Proximo_Lancamento_Caixa (cursor.getInt (26));
        p.setNR_Proxima_Remessa_Comprovante (cursor.getInt (27));
        p.setNR_Proxima_Tabela (cursor.getInt (28));
        p.setNR_Proxima_Cotacao (cursor.getInt (29));
        p.setNM_Modelo_Conhecimento (cursor.getString (30));
        p.setNR_Dias_Revisao_Cadastral (cursor.getInt (31));
        p.setVL_Maximo_Ordem_Frete (cursor.getDouble (32));
        p.setVL_Frete_Minimo (cursor.getDouble (33));
        p.setDt_Stamp (cursor.getString (34));
        p.setUsuario_Stamp (cursor.getString (35));
        p.setDm_Stamp (cursor.getString (36));
        p.setCD_Unidade (cursor.getString (37));
        p.setOID_AIDOF_Ordem_Frete_Adto (cursor.getInt (38));
        Parametro_Filial_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    return Parametro_Filial_Lista;
  }

  public static final List getAll () throws Exception {
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

    List Parametro_Filial_Lista = new ArrayList ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT                                                ");
      buff.append ("   Parametros_Filiais.OID_Parametro_Filial,           ");
      buff.append ("	Parametros_Filiais.OID_Unidade,                    ");
      buff.append ("	Parametros_Filiais.OID_AIDOF,                      ");
      buff.append ("	Parametros_Filiais.OID_AIDOF_Manifesto,            ");
      buff.append ("	Parametros_Filiais.OID_AIDOF_Fatura,               ");
      buff.append ("	Parametros_Filiais.OID_AIDOF_Coleta,               ");
      buff.append ("	Parametros_Filiais.OID_AIDOF_AWB,                  ");
      buff.append ("	Parametros_Filiais.OID_AIDOF_CTO_Internacional,    ");
      buff.append ("	Parametros_Filiais.OID_AIDOF_Ordem_Frete,          ");
      buff.append ("	Parametros_Filiais.OID_AIDOF_Romaneio,             ");
      buff.append ("	Parametros_Filiais.OID_AIDOF_Minuta,               ");
      buff.append ("	Parametros_Filiais.NR_Proxima_Entrega,             ");
      buff.append ("	Parametros_Filiais.NR_Proxima_Visita,              ");
      buff.append ("	Parametros_Filiais.NR_Proximo_Lote_Baixa,          ");
      buff.append ("	Parametros_Filiais.NR_Proximo_Acerto,              ");
      buff.append ("	Parametros_Filiais.NR_Proximo_Arquivo_EDI,         ");
      buff.append ("	Parametros_Filiais.NR_Proximo_Lote_Pagamento,      ");
      buff.append ("	Parametros_Filiais.NR_Proximo_Boletim_Ocorrencia,  ");
      buff.append ("	Parametros_Filiais.NR_Proxima_Proposta,            ");
      buff.append ("	Parametros_Filiais.NR_Proximo_Compromisso,         ");
      buff.append ("	Parametros_Filiais.NR_Proximo_Bordero,             ");
      buff.append ("	Parametros_Filiais.NR_Proxima_Viagem,              ");
      buff.append ("	Parametros_Filiais.NR_Proximo_CNPJ,                ");
      buff.append ("	Parametros_Filiais.NR_Proxima_Ordem_Servico,       ");
      buff.append ("	Parametros_Filiais.NR_Proxima_Ocorrencia,          ");
      buff.append ("	Parametros_Filiais.NR_Proximo_Lancamento_Caixa,    ");
      buff.append ("	Parametros_Filiais.NR_Proxima_Remessa_Comprovante, ");
      buff.append ("	Parametros_Filiais.NR_Proxima_Tabela,              ");
      buff.append ("	Parametros_Filiais.NR_Proxima_Cotacao,             ");
      buff.append ("	Parametros_Filiais.NM_Modelo_Conhecimento,         ");
      buff.append ("	Parametros_Filiais.NR_Dias_Revisao_Cadastral,      ");
      buff.append ("	Parametros_Filiais.VL_Maximo_Ordem_Frete,          ");
      buff.append ("	Parametros_Filiais.VL_Frete_Minimo,                ");
      buff.append ("	Parametros_Filiais.DT_Stamp,                       ");
      buff.append ("	Parametros_Filiais.Usuario_Stamp,                  ");
      buff.append ("	Parametros_Filiais.DM_Stamp,                       ");
      buff.append ("	Unidades.CD_Unidade,                               ");
      buff.append ("	Parametros_Filiais.OID_AIDOF_Ordem_Frete           ");
      buff.append ("FROM                                                  ");
      buff.append ("   Parametros_Filiais,                                ");
      buff.append ("   Unidades                                           ");
      buff.append ("WHERE Parametros_Filiais.OID_Unidade = Unidades.OID_Unidade ");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        Parametro_FilialBean p = new Parametro_FilialBean ();
        p.setOID (cursor.getInt (1));
        p.setOID_Unidade (cursor.getInt (2));
        p.setOID_AIDOF (cursor.getInt (3));
        p.setOID_AIDOF_Manifesto (cursor.getInt (4));
        p.setOID_AIDOF_Fatura (cursor.getInt (5));
        p.setOID_AIDOF_Coleta (cursor.getInt (6));
        p.setOid_AIDOF_AWB (cursor.getInt (7));
        p.setOID_AIDOF_CTO_Internacional (cursor.getInt (8));
        p.setOID_AIDOF_Ordem_Frete (cursor.getInt (9));
        p.setOID_AIDOF_Romaneio (cursor.getInt (10));
        p.setOID_AIDOF_Minuta (cursor.getInt (11));
        p.setNR_Proxima_Entrega (cursor.getInt (12));
        p.setNR_Proxima_Visita (cursor.getInt (13));
        p.setNR_Proximo_Lote_Baixa (cursor.getInt (14));
        p.setNR_Proximo_Acerto (cursor.getInt (15));
        p.setNR_Proximo_Arquivo_EDI (cursor.getInt (16));
        p.setNR_Proximo_Lote_Pagamento (cursor.getInt (17));
        p.setNR_Proximo_Boletim_Ocorrencia (cursor.getInt (18));
        p.setNR_Proxima_Proposta (cursor.getInt (19));
        p.setNR_Proximo_Compromisso (cursor.getInt (20));
        p.setNR_Proximo_Bordero (cursor.getInt (21));
        p.setNR_Proxima_Viagem (cursor.getInt (22));
        p.setNR_Proximo_CNPJ (cursor.getString (23));
        p.setNR_Proxima_Ordem_Servico (cursor.getInt (24));
        p.setNR_Proxima_Ocorrencia (cursor.getInt (25));
        p.setNR_Proximo_Lancamento_Caixa (cursor.getInt (26));
        p.setNR_Proxima_Remessa_Comprovante (cursor.getInt (27));
        p.setNR_Proxima_Tabela (cursor.getInt (28));
        p.setNR_Proxima_Cotacao (cursor.getInt (29));
        p.setNM_Modelo_Conhecimento (cursor.getString (30));
        p.setNR_Dias_Revisao_Cadastral (cursor.getInt (31));
        p.setVL_Maximo_Ordem_Frete (cursor.getDouble (32));
        p.setVL_Frete_Minimo (cursor.getDouble (33));
        p.setDt_Stamp (cursor.getString (34));
        p.setUsuario_Stamp (cursor.getString (35));
        p.setDm_Stamp (cursor.getString (36));
        p.setCD_Unidade (cursor.getString (37));
        p.setOID_AIDOF_Ordem_Frete_Adto (cursor.getInt (38));
        Parametro_Filial_Lista.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    return Parametro_Filial_Lista;
  }

  public static void main (String args[]) throws Exception {
    Parametro_FilialBean pp = new Parametro_FilialBean ();
    pp.setOID (10);
    pp.update ();

    Parametro_FilialBean p = getByOID_Unidade_Parametro (2);
    // //// System.out.println(p.getCD_AIDOF_CTO_Nacional());
    // //// System.out.println(p.getCD_AIDOF_Manifesto());
  }

public String getCD_AIDOF_Nota_Fiscal() {
	return CD_AIDOF_Nota_Fiscal;
}

public void setCD_AIDOF_Nota_Fiscal(String nota_Fiscal) {
	CD_AIDOF_Nota_Fiscal = nota_Fiscal;
}

public int getOid_AIDOF_Nota_Fiscal() {
	return oid_AIDOF_Nota_Fiscal;
}

public void setOid_AIDOF_Nota_Fiscal(int oid_AIDOF_Nota_Fiscal) {
	this.oid_AIDOF_Nota_Fiscal = oid_AIDOF_Nota_Fiscal;
}
}
