package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import com.master.ed.Lote_FaturamentoED;
import com.master.rl.Lote_FaturamentoRL;
import com.master.root.FormataDataBean;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;
import com.master.ed.Documento_Lote_FaturamentoED;
import com.master.ed.EDI_ImportacaoED;
import com.master.ed.ConhecimentoED;
import com.master.util.Data;

public class Lote_FaturamentoBD {

  private ExecutaSQL executasql;
  Parametro_FixoED edParametro_Fixo = new Parametro_FixoED ();

  public Lote_FaturamentoBD (ExecutaSQL sql) {
    this.executasql = sql;
  }

  public Lote_FaturamentoED inclui (Lote_FaturamentoED ed) throws Excecoes {

    String sql = null;
    long valOid = 0;
    String chave = null;

    Lote_FaturamentoED edVolta = new Lote_FaturamentoED ();
    try {

      // System.out.println ("lote Fat bd 1 ");
    	if(!JavaUtil.doValida(ed.getDM_Tipo_Lote())){
    		ed.setDM_Tipo_Lote("N");
    	}

      //pega proximo valor da chave
      ResultSet rs = executasql.executarConsulta (
          "select max(OID_Lote_Faturamento) as result from Lotes_Faturamentos");
      while (rs.next ()) {
        valOid = rs.getLong ("result") + 1;
      }

      ed.setOID_Lote_Faturamento (valOid);

      sql = " insert into Lotes_Faturamentos (OID_Lote_Faturamento, VL_Cobranca, NR_Documento_Cobranca, OID_Pessoa, DM_Situacao, DM_Tipo_Documento, OID_Unidade,  DT_Vencimento, DT_Emissao, TX_Observacao, DM_Tipo_Lote, vl_pre_fatura, nr_pre_fatura  ) values ";
      sql += "(" + ed.getOID_Lote_Faturamento () + "," + ed.getVL_Cobranca () +
          ",'" + ed.getNR_Documento_Cobranca () + "','" + ed.getOID_Pessoa () +
          "','" + ed.getDM_Situacao () + "','" + ed.getDM_Tipo_Documento () +
          "'," + ed.getOID_Unidade () + ",'" + ed.getDT_Vencimento () + "','" +
          ed.getDT_Emissao () + "','" + ed.getTX_Observacao() + "','" + ed.getDM_Tipo_Lote() +
          "'," + ed.getVL_Pre_Fatura() + ",'" + ed.getNR_Pre_Fatura() + "')";

//      System.out.println ("LOTE FAT >> " + sql);

      int i = executasql.executarUpdate (sql);

      edVolta.setOID_Lote_Faturamento (ed.getOID_Lote_Faturamento ());

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao incluir Lote_Faturamento");
      excecoes.setMetodo ("inclui");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return edVolta;

  }

  public void altera (Lote_FaturamentoED ed) throws Excecoes {

    String sql = null;

    try {

      sql = " update Lotes_Faturamentos set  DT_Vencimento = '" +
          ed.getDT_Vencimento () + "' ";
      sql += " where oid_Lote_Faturamento = " + ed.getOID_Lote_Faturamento ();

      // System.out.println ("altera " + sql);

      int i = executasql.executarUpdate (sql);
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMetodo ("alterar");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void deleta (Lote_FaturamentoED ed) throws Excecoes {

    // System.out.println ("exclui Lote_Faturamento ");

    String sql = null;
    ResultSet res = null;

    try {

      sql = " SELECT * from Documentos_Lotes_Faturamentos " +
          " WHERE  oid_Lote_Faturamento = ('" + ed.getOID_Lote_Faturamento () + "')";
      // System.out.println (" deleta1->> " + sql);

      res = this.executasql.executarConsulta (sql);

      //popula
      while (res.next ()) {
        sql = "delete from Documentos_Lotes_Faturamentos WHERE oid_Documento_Lote_Faturamento = " +
            res.getString ("oid_Documento_Lote_Faturamento");
        // System.out.println (" deleta2->> " + sql);

        executasql.executarUpdate (sql);

        if (res.getString ("oid_Conhecimento") != null &&
            res.getString ("DM_Tipo_Documento").equals ("C")) {
          sql =
              " UPDATE Conhecimentos set oid_Lote_Faturamento = 0, NM_Lote_Faturamento = '' " +
              " WHERE oid_Conhecimento = '" + res.getString ("oid_Conhecimento") + "'";
          // System.out.println (" deleta3->> " + sql);
          executasql.executarUpdate (sql);
        }

        if (res.getString ("oid_processo") != null &&
            res.getString ("DM_Tipo_Documento").equals ("P")) {
          sql =
              " UPDATE processos set oid_Lote_Faturamento = 0, vl_saldo = vl_faturado, vl_faturado = 0.0, dm_situacao = 'L' " +
              " WHERE oid_processo = '" + res.getString ("oid_processo") + "'";
          // System.out.println (" deleta OS->> " + sql);
          executasql.executarUpdate (sql);
        }

        if (res.getString ("oid_ordem_frete_terceiro") != null &&
            res.getString ("DM_Tipo_Documento").equals ("O")) {
          sql =
              " UPDATE Ordens_Fretes set oid_Lote_Faturamento = 0 " +
              " WHERE oid_ordem_frete = '" + res.getString ("oid_ordem_frete_terceiro") + "'";
          // System.out.println (" deleta3->> " + sql);
          executasql.executarUpdate (sql);
        }

      }

      sql = "delete from Lotes_Faturamentos WHERE oid_Lote_Faturamento = ";
      sql += "(" + ed.getOID_Lote_Faturamento () + ")";

      // System.out.println ("exclui Lote_Faturamento " + sql);

      executasql.executarUpdate (sql);

    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMetodo ("excluir");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public ArrayList lista (Lote_FaturamentoED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();

    try {

      sql = " SELECT * from  Lotes_Faturamentos,  pessoas, Unidades " +
          " WHERE Lotes_Faturamentos.oid_unidade = Unidades.oid_unidade  " +
          " AND   Lotes_Faturamentos.oid_Pessoa = pessoas.oid_Pessoa  ";

      if (String.valueOf (ed.getOID_Lote_Faturamento ()) != null &&
          !String.valueOf (ed.getOID_Lote_Faturamento ()).equals ("") &&
          !String.valueOf (ed.getOID_Lote_Faturamento ()).equals ("0") &&
          !String.valueOf (ed.getOID_Lote_Faturamento ()).equals ("null")) {
        sql += " and Lotes_Faturamentos.OID_Lote_Faturamento = " +
            ed.getOID_Lote_Faturamento ();
      }

      if (String.valueOf (ed.getNR_Documento_Cobranca ()) != null &&
          !String.valueOf (ed.getNR_Documento_Cobranca ()).equals ("") &&
          !String.valueOf (ed.getNR_Documento_Cobranca ()).equals ("0") &&
          !String.valueOf (ed.getNR_Documento_Cobranca ()).equals ("null")) {
        sql += " and Lotes_Faturamentos.NR_Documento_Cobranca = " +
            ed.getNR_Documento_Cobranca ();
      }

      if (String.valueOf (ed.getDT_Emissao_Inicial ()) != null &&
          !String.valueOf (ed.getDT_Emissao_Inicial ()).equals ("") &&
          !String.valueOf (ed.getDT_Emissao_Inicial ()).equals ("null")) {
        sql += " and Lotes_Faturamentos.DT_Emissao >= '" +
            ed.getDT_Emissao_Inicial () + "'";
      }

      if (String.valueOf (ed.getDT_Emissao_Final ()) != null &&
          !String.valueOf (ed.getDT_Emissao_Final ()).equals ("") &&
          !String.valueOf (ed.getDT_Emissao_Final ()).equals ("null")) {
        sql += " and Lotes_Faturamentos.DT_Emissao <= '" +
            ed.getDT_Emissao_Final () + "'";
      }

      if (String.valueOf (ed.getOID_Unidade ()) != null &&
          !String.valueOf (ed.getOID_Unidade ()).equals ("0") &&
          !String.valueOf (ed.getOID_Unidade ()).equals ("null")) {
        sql += " and Lotes_Faturamentos.OID_Unidade = " + ed.getOID_Unidade ();
      }

      if (String.valueOf (ed.getOID_Pessoa ()) != null &&
          !String.valueOf (ed.getOID_Pessoa ()).equals ("") &&
          !String.valueOf (ed.getOID_Pessoa ()).equals ("0") &&
          !String.valueOf (ed.getOID_Pessoa ()).equals ("null")) {
        sql += " and Lotes_Faturamentos.OID_Pessoa = '" + ed.getOID_Pessoa () +
            "'";
      }

      if (String.valueOf (ed.getDM_Situacao ()) != null &&
          !String.valueOf (ed.getDM_Situacao ()).equals ("") &&
          !String.valueOf (ed.getDM_Situacao ()).equals ("null")) {
        sql += " and Lotes_Faturamentos.DM_Situacao = '" + ed.getDM_Situacao () +
            "'";
      }

      if (String.valueOf (ed.getNR_Pre_Fatura()) != null &&
              !String.valueOf (ed.getNR_Pre_Fatura ()).equals ("") &&
              !String.valueOf (ed.getNR_Pre_Fatura ()).equals ("0") &&
              !String.valueOf (ed.getNR_Pre_Fatura ()).equals ("null")) {
            sql += " and Lotes_Faturamentos.NR_Pre_fatura = '" +
                ed.getNR_Pre_Fatura () + "'";
          }

      sql += " Order by Lotes_Faturamentos.OID_Lote_Faturamento ";

      // System.out.println (">>>>>>>>>>>>>>> " + sql);

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);

      FormataDataBean DataFormatada = new FormataDataBean ();

      //popula
      while (res.next ()) {
        Lote_FaturamentoED edVolta = new Lote_FaturamentoED ();

        // System.out.println ("ok ");

        edVolta.setOID_Lote_Faturamento (res.getLong ("OID_Lote_Faturamento"));
        // System.out.println ("ok 2");

        edVolta.setDT_Emissao (res.getString ("DT_Emissao"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
        edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());
        // System.out.println ("ok 3");

        edVolta.setNR_Documento_Cobranca (res.getLong ("NR_Documento_Cobranca"));

        // System.out.println ("ok 4");
        edVolta.setDM_Tipo_Lote(res.getString("dm_tipo_lote"));

        // System.out.println ("8 ");
        edVolta.setNM_Razao_Social (res.getString ("NM_Razao_Social"));

        edVolta.setCD_Unidade (res.getString ("CD_Unidade"));
        // System.out.println ("9 ");
        edVolta.setVL_Cobranca (res.getDouble ("VL_Cobranca"));
        // System.out.println ("10 ");

        edVolta.setNR_Pre_Fatura(res.getString ("NR_Pre_Fatura"));
        edVolta.setVL_Pre_Fatura(res.getDouble ("VL_Pre_Fatura"));

        list.add (edVolta);
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao listar Lote_Faturamento");
      excecoes.setMetodo ("lista");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return list;
  }

  public Lote_FaturamentoED getByRecord (Lote_FaturamentoED ed) throws Excecoes {

    String sql = null;

    Lote_FaturamentoED edVolta = new Lote_FaturamentoED ();

    try {

      sql = " SELECT *, Lotes_Faturamentos.oid_Pessoa as oid_Pessoa_Lote " +
          " FROM  Lotes_Faturamentos, pessoas, Unidades " +
          " WHERE Lotes_Faturamentos.oid_unidade = Unidades.oid_unidade  " +
          " and   Unidades.oid_Pessoa = Pessoas.oid_pessoa  ";

      if (String.valueOf (ed.getOID_Lote_Faturamento ()) != null &&
          !String.valueOf (ed.getOID_Lote_Faturamento ()).equals ("") &&
          !String.valueOf (ed.getOID_Lote_Faturamento ()).equals ("null")) {
        sql += " and Lotes_Faturamentos.OID_Lote_Faturamento = " +
            ed.getOID_Lote_Faturamento ();
      }

      // System.out.println ("lote Fat get by  1 ->>" + sql);

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      ResultSet resCTRC = null;

      FormataDataBean DataFormatada = new FormataDataBean ();

      while (res.next ()) {

        // System.out.println ("lote Fat get by  2 ->>");

        edVolta.setOID_Lote_Faturamento (res.getLong ("OID_Lote_Faturamento"));
        edVolta.setDM_Situacao (res.getString ("DM_Situacao"));
        edVolta.setDM_Tipo_Documento (res.getString ("DM_Tipo_Documento"));
        edVolta.setDM_Tipo_Lote(res.getString("dm_tipo_lote"));
        edVolta.setOID_Pessoa (res.getString ("oid_Pessoa_Lote"));
        edVolta.setNR_Documento_Cobranca (res.getLong ("NR_Documento_Cobranca"));

        // System.out.println ("lote Fat get by  3 ->>");
        edVolta.setDT_Vencimento (res.getString ("DT_Vencimento"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Vencimento ());
        edVolta.setDT_Vencimento (DataFormatada.getDT_FormataData ());

        edVolta.setDT_Emissao (res.getString ("DT_Emissao"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
        edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());

        // System.out.println ("lote Fat get by  4 ->>");

        edVolta.setTX_Observacao (res.getString ("TX_Observacao"));
        edVolta.setVL_Cobranca (res.getDouble ("VL_Cobranca"));
        edVolta.setOID_Unidade (res.getLong ("OID_Unidade"));

        // System.out.println ("lote Fat get by  5 ->>");

        edVolta.setCD_Unidade (res.getString ("CD_Unidade"));
        edVolta.setOID_Duplicata (0);

        edVolta.setVL_Total_Faturado(0);
        edVolta.setVL_Total_Frete(0);

        edVolta.setNR_Pre_Fatura(res.getString ("NR_Pre_Fatura"));
        edVolta.setVL_Pre_Fatura(res.getDouble ("VL_Pre_Fatura"));

        if ("CTO".equals (res.getString ("DM_Tipo_Documento"))) {
          sql = "SELECT Duplicatas.OID_Duplicata " +
              " FROM Documentos_Lotes_Faturamentos, Origens_Duplicatas, Duplicatas " +
              " WHERE Documentos_Lotes_Faturamentos.oid_Conhecimento = Origens_Duplicatas.oid_Conhecimento " +
              "  AND  Origens_Duplicatas.DM_Situacao = 'A' " +
              "  AND  Origens_Duplicatas.oid_Duplicata = Duplicatas.oid_Duplicata " +
              "  AND  Documentos_Lotes_Faturamentos.oid_Lote_Faturamento = " + ed.getOID_Lote_Faturamento ();

          // System.out.println ("lot ->>" + sql);

          resCTRC = this.executasql.executarConsulta (sql);
          while (resCTRC.next () && edVolta.getOID_Duplicata () == 0) {
            edVolta.setOID_Duplicata (resCTRC.getLong ("OID_Duplicata"));
          }
          //soma os totais para verificar diferencas
          double total_frete = 0;
          double total_faturado = 0;
          Documento_Lote_FaturamentoED doc = new Documento_Lote_FaturamentoED();
          doc.setOID_Lote_Faturamento(ed.getOID_Lote_Faturamento());
          Iterator it = new Documento_Lote_FaturamentoBD(this.executasql).lista(doc).iterator();
          while(it.hasNext()){
        	  doc = (Documento_Lote_FaturamentoED)it.next();
        	  total_frete += doc.getVL_Total_Frete();
        	  total_faturado += doc.getVL_Faturado();
          }
          edVolta.setVL_Total_Faturado(total_faturado);
          edVolta.setVL_Total_Frete(total_frete);

        }
        if ("OFT".equals (res.getString ("DM_Tipo_Documento"))) {
          sql = "SELECT Duplicatas.OID_Duplicata " +
              " FROM Documentos_Lotes_Faturamentos, Origens_Duplicatas, Duplicatas " +
              " WHERE Documentos_Lotes_Faturamentos.oid_Ordem_Frete_Terceiro = Origens_Duplicatas.oid_Ordem_Frete_Terceiro " +
              "  AND  Origens_Duplicatas.DM_Situacao = 'A' " +
              "  AND  Origens_Duplicatas.oid_Duplicata = Duplicatas.oid_Duplicata " +
              "  AND  Documentos_Lotes_Faturamentos.oid_Lote_Faturamento = " + ed.getOID_Lote_Faturamento ();

          // System.out.println ("lot ->>" + sql);

          resCTRC = this.executasql.executarConsulta (sql);
          while (resCTRC.next () && edVolta.getOID_Duplicata () == 0) {
            edVolta.setOID_Duplicata (resCTRC.getLong ("OID_Duplicata"));
          }
        }

        edVolta.setOID_Conhecimento ("");
        if ("OS".equals (res.getString ("DM_Tipo_Documento"))) {
          sql = " SELECT Documentos_Lotes_Faturamentos.oid_Conhecimento, " +
              "        Documentos_Lotes_Faturamentos.nr_documento " +
              " FROM  Documentos_Lotes_Faturamentos " +
              " WHERE Documentos_Lotes_Faturamentos.oid_Lote_Faturamento = " + ed.getOID_Lote_Faturamento ();

          // System.out.println ("lot ->>" + sql);

          resCTRC = this.executasql.executarConsulta (sql);
          while (resCTRC.next ()) {
            edVolta.setOID_Conhecimento (resCTRC.getString ("oid_Conhecimento"));
          }
        }

      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao selecionar Lote_Faturamento");
      excecoes.setMetodo ("Seleção");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return edVolta;
  }

  public byte[] imprime_Lote_Faturamento (Lote_FaturamentoED ed) throws
      Excecoes {

    String sql = null;
    byte[] b = null;

    sql = " SELECT *, Lotes_Faturamentos.oid_Pessoa " +
        " FROM  Lotes_Faturamentos, Documentos_Lotes_Faturamentos " +
        " WHERE Lotes_Faturamentos.oid_Lote_Faturamento = Documentos_Lotes_Faturamentos.oid_Lote_Faturamento " +
        " AND   Lotes_Faturamentos.oid_Lote_Faturamento = '" + ed.getOID_Lote_Faturamento () + "'" +
        " Order by NR_Documento ";

    // System.out.println (" lista->> " + sql);

    try {

      ResultSet res = null;

      res = this.executasql.executarConsulta (sql.toString ());

      Lote_FaturamentoRL conRL = new Lote_FaturamentoRL ();
      b = conRL.imprime_Lote_Faturamento (res);

    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro no método listar");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("geraRelatorio(ConhecimentoED ed)");
    }
    return b;
  }

  public void Altera_Situacao_Processo (long oid_Lote_Faturamento) throws Excecoes {

    // System.out.println ("Altera_Situacao_Processo ");

    String sql = null;
    ResultSet res = null , rs = null;
    double vl_faturado = 0;

    try {
      sql = " SELECT * from Documentos_Lotes_Faturamentos " +
          " WHERE  oid_Lote_Faturamento = ('" + oid_Lote_Faturamento + "')";
      // System.out.println (" Altera_Situacao_Processo1->> " + sql);
      res = this.executasql.executarConsulta (sql);

      //popula
      while (res.next ()) {
        if (res.getString ("oid_processo") != null &&
            res.getString ("DM_Tipo_Documento").equals ("P")) {

          sql = " SELECT sum(vl_lancamento) as soma from movimentos_processos " +
              " WHERE dm_debito_credito = 'C' " +
              " AND oid_processo = '" + res.getString ("oid_processo") + "'";
          // System.out.println (" Altera_Situacao_Processo2->> " + sql);
          rs = this.executasql.executarConsulta (sql);
          while (rs.next ()) {
            vl_faturado = rs.getDouble ("soma");
          }

          if (vl_faturado > 0) {
            sql = " UPDATE processos set dm_situacao = 'F' " +
                " WHERE vl_faturado >= " + vl_faturado +
                " AND oid_processo = '" + res.getString ("oid_processo") + "'";
            // System.out.println (" Altera_Situacao_Processo3->> " + sql);
            executasql.executarUpdate (sql);
          }
        }
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMetodo ("Altera_Situacao_Processo");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

}
