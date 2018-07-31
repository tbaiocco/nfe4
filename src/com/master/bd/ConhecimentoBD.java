package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.master.ed.Calcula_FreteED;
import com.master.ed.ConhecimentoED;
import com.master.ed.Conhecimento_Nota_FiscalED;
import com.master.ed.Livro_FiscalED;
import com.master.ed.Movimento_ConhecimentoED;
import com.master.ed.Movimento_Contabil_TempED;
import com.master.ed.Nota_FiscalED;
import com.master.ed.Programacao_CargaED;
import com.master.rl.ConhecimentoRL;
import com.master.rl.JasperRL;
import com.master.rn.ConhecimentoRN;
import com.master.rn.Nota_FiscalRN;
import com.master.root.FormataDataBean;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;
import com.master.util.Utilitaria;
import com.master.util.Valor;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;

public class ConhecimentoBD {

  Calcula_FreteBD Calcula_FreteBD = null;
  long Nr_Sort = 0;
  boolean auxiliar = false;
  Parametro_FixoED parametro_FixoED = new Parametro_FixoED ();
  FormataDataBean dataFormatada = new FormataDataBean ();
  protected Movimento_ConhecimentoBD Movimento_ConhecimentoBD = null;
  protected Conhecimento_Nota_FiscalBD Conhecimento_Nota_FiscalBD = null;

  String Situacao_Cliente = "";
  String Tipo_Cobranca = "B";
  String OID_Vendedor = "";

  private ExecutaSQL executasql;
  Utilitaria util = null;

  public ConhecimentoBD (ExecutaSQL sql) {
    /////super(sql);
    util = new Utilitaria (executasql);
    this.executasql = sql;
  }

  public ConhecimentoED inclui_CTRC_Nota_Fiscal_Lote (ConhecimentoED ed) throws Excecoes {

    // System.out.println (" bd 1");

    String sql = null;
    int qt_notas_fiscais = 0;
    ResultSet rs = null;
    ResultSet rs2 = null;
    String DM_Tem_Cliente = "";
    String DM_Nota_Fiscal = "";
    String DM_Tipo_Conhecimento = "";
    String oid_Pessoa = "";
    String oid_Pessoa_Destinatario = "";
    String oid_Ultimo_Conhecimento = "";
    String dm_tipo_pagamento = "";
    int NR_QT_Notas_Fiscais = 0;

    ConhecimentoED conED = new ConhecimentoED ();

//        // System.out.println(" bd 2");

    try {

      sql = " SELECT oid_Conhecimento " +
          " FROM  Conhecimentos " +
          " WHERE Conhecimentos.DM_Impresso = 'N' " +
          " AND   Conhecimentos.OID_Coleta = " + ed.getOID_Coleta ();

      // System.out.println (" sql exclui nao impressos " + sql);

      rs2 = this.executasql.executarConsulta (sql);

      while (rs2.next ()) {

        sql = " DELETE FROM  Conhecimentos_Notas_Fiscais " +
            " WHERE Conhecimentos_Notas_Fiscais.oid_Conhecimento = '" + rs2.getString ("oid_Conhecimento") + "'";

        // System.out.println (" sql exclui nao impressos " + sql);

        executasql.executarUpdate (sql);

        sql = " DELETE FROM  Conhecimentos " +
            " WHERE Conhecimentos.oid_Conhecimento = '" + rs2.getString ("oid_Conhecimento") + "'";

        // System.out.println (" sql exclui nao impressos " + sql);

        executasql.executarUpdate (sql);

        // System.out.println (" excluiu ----------------------- " + rs2.getString ("oid_Conhecimento"));
      }

      sql = " SELECT *, " +
          " Coletas.oid_Modal as oid_Modal_Coleta, " +
          " Coletas.oid_Veiculo as oid_Veiculo_Coleta, " +
          " Coletas.oid_Unidade as oid_Unidade_Coleta " +
          " FROM Notas_Fiscais, Coletas, Unidades " +
          " WHERE   Notas_Fiscais.OID_Coleta = Coletas.oid_Coleta " +
          " AND     Coletas.OID_Unidade = Unidades.oid_Unidade " +
          " AND     Notas_Fiscais.OID_Coleta = " + ed.getOID_Coleta () +
          " ORDER BY Notas_Fiscais.Nr_Sequencia,  Notas_Fiscais.OID_Pessoa, Notas_Fiscais.OID_Pessoa_Destinatario , Notas_Fiscais.dm_tipo_pagamento ";

      // System.out.println (" sql NOTA" + sql);

//            // System.out.println(" bd 4");

      rs = this.executasql.executarConsulta (sql);

      while (rs.next ()) {
//                // System.out.println(" segue 5");
        //util.inicioTransacao();
        //this.executasql = this.sql;
        sql = " DELETE FROM  Conhecimentos_Notas_Fiscais " +
            " WHERE Conhecimentos_Notas_Fiscais.oid_Nota_Fiscal = '" + rs.getString ("oid_Nota_Fiscal") + "'";

        // System.out.println ("  exclui cto_NF nao oid_Nota_Fiscal " + sql);

        executasql.executarUpdate (sql);

        DM_Tipo_Conhecimento = rs.getString ("dm_tipo_Conhecimento");
        DM_Nota_Fiscal = "N";

        /*
                         sql = " SELECT * FROM Conhecimentos_Notas_Fiscais " +
              " WHERE Conhecimentos_Notas_Fiscais.OID_Nota_Fiscal = '" + rs.getString("OID_Nota_Fiscal") + "'";

//                // System.out.println(" verifica nf ja inclusa" + sql);

                         rs2 = this.executasql.executarConsulta(sql);

                         DM_Tem_Cliente = "N";

                         while (rs2.next()) {
            DM_Nota_Fiscal = "S";
            // System.out.println(" JA tem A NF");
                         }
         */
        if (DM_Nota_Fiscal.equals ("N")) {

          sql = " SELECT * FROM Clientes ";
          if (rs.getString ("DM_TIPO_PAGAMENTO").equals ("1")) {
            sql += " WHERE Clientes.oid_Cliente = '" + rs.getString ("OID_PESSOA") + "'";
          }
          else if (rs.getString ("DM_TIPO_PAGAMENTO").equals ("2")) {
            sql += " WHERE Clientes.oid_Cliente = '" + rs.getString ("OID_PESSOA_DESTINATARIO") + "'";
          }
          else if (rs.getString ("DM_TIPO_PAGAMENTO").equals ("3")) {
            sql += " WHERE Clientes.oid_Cliente = '" + rs.getString ("OID_PESSOA_CONSIGNATARIO") + "'";
          }
          else {
            sql += " WHERE Clientes.oid_Cliente = '" + rs.getString ("OID_PESSOA") + "'";
          }

//                    // System.out.println(" sql cliente" + sql);

          rs2 = this.executasql.executarConsulta (sql);
          while (rs2.next ()) {

//                        // System.out.println(" tem cliente");
            ///this.verificaCredito(Pessoa_Pagador);

            DM_Tem_Cliente = "S";
            conED.setDM_Isento_Seguro (rs2.getString ("DM_ISENCAO_SEGURO"));
            conED.setOID_Pessoa_Pagador (rs2.getString ("OID_CLIENTE"));
            conED.setOID_Vendedor (rs2.getString ("OID_VENDEDOR"));
            // System.out.println (" tem cliente 2");

            NR_QT_Notas_Fiscais = 1;
            if (rs2.getString ("NR_QT_Notas_Fiscais") != null && !rs2.getString ("NR_QT_Notas_Fiscais").equals ("null") && !rs2.getString ("NR_QT_Notas_Fiscais").equals ("")) {
              // System.out.println (" tem cliente 3");

              NR_QT_Notas_Fiscais = rs2.getInt ("NR_QT_Notas_Fiscais");
            }
            // System.out.println (" tem cliente 4");

          }

          // System.out.println (" x 2");

          if (NR_QT_Notas_Fiscais <= 0) {
            NR_QT_Notas_Fiscais = 1;
          }

          if (DM_Tem_Cliente.equals ("N")) {
            Nota_FiscalRN Nota_Fiscalrn = new Nota_FiscalRN ();
            Nota_FiscalED nfed = new Nota_FiscalED ();

//                        // System.out.println(" nao tem cliente");

            nfed.setOID_Nota_Fiscal (rs.getString ("OID_Nota_Fiscal"));
            nfed.setNM_Erro_Calculo ("FALTA CLIENTE PAGADOR");
            Nota_Fiscalrn.erroCalculo (nfed);
          }

          if (DM_Tem_Cliente.equals ("S")) {

//                        // System.out.println(" segue ");

            // System.out.println (" ultimo cto " + oid_Ultimo_Conhecimento);
            qt_notas_fiscais++;

            // System.out.println (" ->>>>> 25/05 >>>>>>>>>> Tipo CTO =" + DM_Tipo_Conhecimento + " NF Cliemnte= " + NR_QT_Notas_Fiscais);

            if (oid_Pessoa.equals (rs.getString ("oid_Pessoa")) &&
                oid_Pessoa_Destinatario.equals (rs.getString ("oid_Pessoa_Destinatario")) &&
                dm_tipo_pagamento.equals (rs.getString ("dm_tipo_pagamento")) &&
                qt_notas_fiscais <= NR_QT_Notas_Fiscais &&
                DM_Tipo_Conhecimento.equals ("9")) {

              conED.setDM_Conhecimento_Varias_Notas_Fiscais ("S");
              conED.setOID_Conhecimento (oid_Ultimo_Conhecimento);
              // System.out.println (" inclui -igual");
            }
            else {
              oid_Ultimo_Conhecimento = "";
              conED.setDM_Conhecimento_Varias_Notas_Fiscais ("N");
              conED.setNR_Conhecimento (0);
              qt_notas_fiscais = 0;
              // System.out.println (" inclui -diferente");
            }
            oid_Pessoa = rs.getString ("oid_Pessoa");
            // System.out.println (" inclui xx 1");

            oid_Pessoa_Destinatario = rs.getString ("oid_Pessoa_Destinatario");

            // System.out.println (" inclui xx 2");
            dm_tipo_pagamento = rs.getString ("dm_tipo_pagamento");

            conED.setDM_Impresso ("N");
            conED.setDM_Responsavel_Cobranca ("");
            conED.setDM_Tipo_Pagamento (rs.getString ("DM_TIPO_PAGAMENTO"));
            // System.out.println (" inclui xx 3");

            conED.setDM_Tipo_Conhecimento (rs.getString ("DM_TIPO_CONHECIMENTO"));
            conED.setDT_Emissao (Data.getDataDMY ());
            // System.out.println (" inclui 4");
            conED.setDT_Previsao_Entrega ("");
            conED.setHR_Previsao_Entrega ("");
            conED.setOID_Empresa (rs.getLong ("oid_empresa"));

            // System.out.println (" empresa" + rs.getLong ("oid_empresa"));

            conED.setOID_Cidade (rs.getLong ("OID_CIDADE"));

            if (rs.getString ("OID_Pessoa_Redespacho") != null && !rs.getString ("OID_Pessoa_Redespacho").equals ("") && !rs.getString ("OID_Pessoa_Redespacho").equals ("null")) {

              sql = " select oid_cidade as oid_cidade_destino from Pessoas ";
              sql += " where Pessoas.oid_Pessoa = '" + rs.getString ("OID_Pessoa_Redespacho") + "'";
//                            // System.out.println(" procura cidade destino pelo redespacho" + rs.getString("OID_Pessoa_Redespacho"));
              rs2 = this.executasql.executarConsulta (sql);
              while (rs2.next ()) {
                conED.setOID_Cidade_Destino (rs2.getLong ("oid_cidade_destino"));
//                                // System.out.println(" inclui tem cidade do redesp");
              }
            }
            else {
              sql = " select oid_cidade as oid_cidade_destino from Pessoas ";
              sql += " where Pessoas.oid_Pessoa = '" + rs.getString ("OID_PESSOA_DESTINATARIO") + "'";
              rs2 = this.executasql.executarConsulta (sql);
              while (rs2.next ()) {
                conED.setOID_Cidade_Destino (rs2.getLong ("oid_cidade_destino"));
                // System.out.println (" inclui tem cidadeti");
              }
            }
            // System.out.println (" inclui 4 b MODAL ->> " + rs.getLong ("oid_Modal_Coleta"));
            // System.out.println (" inclui 4 b VEICULO ->> " + rs.getString ("oid_Veiculo_Coleta"));

            conED.setOID_Coleta (rs.getLong ("OID_Coleta"));
            conED.setOID_Veiculo (rs.getString ("oid_Veiculo_Coleta"));

            conED.setOID_Modal (rs.getLong ("oid_Modal_Coleta"));
            conED.setOID_Pessoa (rs.getString ("OID_PESSOA"));
            conED.setOID_Pessoa_Destinatario (rs.getString ("OID_PESSOA_DESTINATARIO"));
            conED.setOID_Pessoa_Consignatario ("");
            conED.setOID_Pessoa_Redespacho ("");
            conED.setOID_Produto (rs.getLong ("OID_Produto"));
            conED.setOID_Unidade (rs.getLong ("oid_Unidade_Coleta"));
            conED.setOID_Nota_Fiscal (rs.getString ("OID_NOTA_FISCAL"));
            // System.out.println (" inclui 4 c");
            conED.setTX_Observacao ("");
            conED.setNM_Natureza ("");
            conED.setNM_Especie ("");
            conED.setOID_Tabela_Frete ("");
            conED.setOID_Taxa (1);
            conED.setVL_FRETE_PESO (0.0);
            conED.setVL_FRETE_VALOR (0.0);
            conED.setVL_SEC_CAT (0.0);
            conED.setVL_PEDAGIO (0.0);
            conED.setVL_DESPACHO (0.0);
            conED.setVL_OUTROS1 (0.0);
            conED.setVL_OUTROS2 (0.0);
            conED.setVL_TOTAL_FRETE (0.0);
            conED.setVL_BASE_CALCULO_ICMS (0.0);
            conED.setVL_ICMS (0.0);

            ConhecimentoRN Conhecimentorn = new ConhecimentoRN ();

            //conED = this.inclui_CTRC(conED);
            Conhecimentorn.inclui (conED);
            Conhecimentorn.getByRecord (conED);

            oid_Ultimo_Conhecimento = conED.getOID_Conhecimento ();

            //edCalcula_Frete =
            // Calcula_Frete_Cto_BD.calcula_frete(edCalcula_Frete);

            // System.out.println ("cto FIMMMMM");
          }
        }
        // System.out.println ("fechou transacao");
        //this.fimTransacao(true);
      }
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao incluir Conhecimento");
      excecoes.setMetodo ("inclui_CTRC_Nota_Fiscal_Lote");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return conED;
  }

  public ConhecimentoED deleta_CTRC_Nota_Fiscal_Lote (ConhecimentoED ed) throws Excecoes {

//        // System.out.println(" bd 1");

    String sql = null;
    long valOid = 0;
    int qt_notas_fiscais = 0;
    String chave = null;
    ResultSet rs = null;
    ResultSet rs2 = null;
    ResultSet rs3 = null;
    String DM_Tem_Cliente = "";
    String DM_Nota_Fiscal = "";
    String DM_Tipo_Conhecimento = "";
    String oid_Pessoa = "";
    String oid_Pessoa_Destinatario = "";
    String oid_Ultimo_Conhecimento = "";
    String dm_tipo_pagamento = "";
    int NR_QT_Notas_Fiscais = 0;

    ConhecimentoED conED = new ConhecimentoED ();

//        // System.out.println(" bd 2");

    try {

      sql = " select oid_Conhecimento from Conhecimentos ";
      sql += " where Conhecimentos.dm_impresso = 'N' ";
      sql += " and   Conhecimentos.OID_Coleta = " + ed.getOID_Coleta ();

      //// System.out.println(" sql exclui nao impressos " + sql);

      rs2 = this.executasql.executarConsulta (sql);

      while (rs2.next ()) {
//                // System.out.println(" bd 3");

        conED.setOID_Conhecimento (rs2.getString ("oid_Conhecimento"));
        //// System.out.println(" excluindo " +
        // rs2.getString("oid_Conhecimento"));
        ConhecimentoRN Conhecimentorn = new ConhecimentoRN ();
        Conhecimentorn.deleta (conED);
        //// System.out.println(" excluiu ----------------------- " +
        // rs2.getString("oid_Conhecimento"));
      }
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao incluir Conhecimento");
      excecoes.setMetodo ("inclui");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return conED;
  }

  public ConhecimentoED recalcula_CTRC_Nota_Fiscal_Lote (ConhecimentoED ed) throws Excecoes {

    //// System.out.println(" recalcula 1");

    String sql = null;
    long valOid = 0;
    String chave = null;
    ResultSet rs = null;
    ResultSet rs2 = null;
    String DM_Tem_Cliente = "";
    String DM_Nota_Fiscal = "";

    ConhecimentoED conED = new ConhecimentoED ();

    //// System.out.println(" bd 2");

    try {

      sql = " select oid_Conhecimento from Conhecimentos ";
      sql += " where Conhecimentos.dm_impresso = 'N' ";
      sql += " and   Conhecimentos.OID_Coleta = " + ed.getOID_Coleta ();

      //// System.out.println(" sql recalcula" + sql);

      rs2 = this.executasql.executarConsulta (sql);

      while (rs2.next ()) {
        conED.setOID_Conhecimento (rs2.getString ("oid_Conhecimento"));
        //// System.out.println(" recalculando " +
        // rs2.getString("oid_Conhecimento"));
        this.getByRecord (conED);
        //// System.out.println(" recalculou ----------------------- " +
        // rs2.getString("oid_Conhecimento"));

      }
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao recalcular Conhecimento");
      excecoes.setMetodo ("inclui");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return conED;
  }

  public ConhecimentoED inclui (ConhecimentoED ed) throws Excecoes {

    String sql = null;
    long valOid = 0;
    String chave = String.valueOf (System.currentTimeMillis ()).toString ();
    String Uni_Key = chave;
    ed.setOID_Conhecimento (Uni_Key);

    long NR_Proximo = 0;
    long NR_Final = 0;
    boolean DM_Nota_Em_Conhecimento = false;
    OID_Vendedor = parametro_FixoED.getOID_Cliente_Complemento_Padrao ();

    ConhecimentoED conED = new ConhecimentoED ();

    String Pessoa_Pagador = ed.getOID_Pessoa_Pagador ();

	System.out.println ("inclui==="+ Pessoa_Pagador);

    if (!util.doValida (ed.getDM_Tipo_Cobranca ())) {
      ed.setDM_Tipo_Cobranca ("B"); // SETA como FATURADO
    }

    if (!util.doValida (Pessoa_Pagador)) {
      // System.out.println ("inclui cto-Pessoa Pagador n�o informada ");
      throw new Mensagens ("Pessoa Pagador n�o informada!");
    }

try {
    if ("S".equals(ed.getDM_Tipo_Conhecimento()) || "R".equals(ed.getDM_Tipo_Conhecimento())){ //suprimentos  // remessas
    if ("S".equals(ed.getDM_Tipo_Conhecimento())){ //suprimentos
      if (!util.doValida (ed.getOID_Pessoa_Entregadora ())) {
        String oid_P = verificaEntregador_Modal (ed.getOID_Modal ());
        if (!"".equals (oid_P)) {
          ed.setOID_Pessoa_Entregadora (oid_P);
        }
      }
    }
    }else {
      ed.setOID_Pessoa_Pagador (verificaCredito (Pessoa_Pagador));
    }
    System.out.println ("inclui==="+ ed.getOID_Pessoa_Pagador());
//    try {
      if (ed.getNR_Conhecimento () == 0) {

        sql = " SELECT AIDOF.NR_Proximo, AIDOF.NR_FINAL, AIDOF.OID_AIDOF, AIDOF.NM_Serie ";
        sql += " FROM AIDOF ";
        sql += " WHERE AIDOF.NM_Serie = 'CN1' ";

        ResultSet rs = this.executasql.executarConsulta (sql);
        try {
          int tCont=0;
          if (rs.next ()) {
            ed.setNM_Serie (rs.getString ("NM_Serie"));
            NR_Proximo =rs.getLong ("NR_Proximo");
            valOid = rs.getLong ("OID_AIDOF");

            while (tCont<100) {
            	tCont++;
                ed.setNR_Conhecimento (NR_Proximo);
	            NR_Proximo += tCont;
	            NR_Final = rs.getLong ("NR_FINAL");

	            if (NR_Proximo > NR_Final) {
	                // System.out.print ("inclui -> AIDOF sem numera��o dispon�vel ");
	                throw new Mensagens ("AIDOF sem numera��o dispon�vel");
	              }

	            chave = String.valueOf (ed.getOID_Unidade ()) + String.valueOf (ed.getNR_Conhecimento ()) + "CN2";

	            ed.setOID_Conhecimento (chave);

	            ConhecimentoED edValida = new ConhecimentoED ();
	            edValida.setOID_Conhecimento (ed.getOID_Conhecimento ());
	            if (!existeConhecimento (edValida)) {
	              // System.out.print ("inclui nr cto J� existe um conhecimento com esta chave->" + ed.getOID_Conhecimento ());
	              tCont=9999;
	              //throw new Mensagens ("J� existe um conhecimento com esta chave, verifique a numera��o tempor�ria na AIDOF!");
	            }
            }

          }
        }
        finally {
          util.closeResultset (rs);
        }


        sql = " UPDATE AIDOF SET NR_Proximo= " + (ed.getNR_Conhecimento () + 1);
        sql += " WHERE OID_AIDOF = " + valOid;
        int u = executasql.executarUpdate (sql);
      }

      // System.out.print ("inclui nr cto->" + ed.getNR_Conhecimento () + 1);

      if (ed.getOID_Taxa () == 0) {
      }


      Uni_Key = chave;

      String dt_coleta = ed.getDT_Emissao () , hr_coleta = null;
      if (ed.getOID_Coleta () > 0) {
        sql = " SELECT Coletas.dt_coleta,  Coletas.hr_coleta  ";
        sql += " FROM Coletas ";
        sql += " WHERE Coletas.oid_Coleta=" + ed.getOID_Coleta ();

        ResultSet rs = this.executasql.executarConsulta (sql);
        if (rs.next ()) {
          dt_coleta = rs.getString ("dt_coleta");
          hr_coleta = rs.getString ("hr_coleta");
        }
      }

      if (ed.getOid_Programacao_Carga() > 0) {
    	  Programacao_CargaED programacao = new Programacao_CargaED();
    	  sql = " SELECT Programacoes_Cargas.oid_motorista  ";
          sql += " FROM Programacoes_Cargas ";
          sql += " WHERE Programacoes_Cargas.Oid_Programacao_Carga=" + ed.getOid_Programacao_Carga();

          ResultSet rs = this.executasql.executarConsulta (sql);
          if (rs.next ()) {
            ed.setOID_Motorista(rs.getString(1));
          }
      }

      // System.out.print ("inclui 20");

      sql =
          " insert into Conhecimentos " +
          "   (OID_Conhecimento,  " +
          "    uni_key, " +
          "    OID_Usuario, CD_Rota_Entrega, " +
          "    OID_Pessoa, oid_Veiculo, " +
          "    oid_Carreta, " +
          "    oid_Carreta2, " +
          "    DT_Coleta, " +
          "    DT_Programada, " +
          "    HR_Coleta, " +
          "    DT_Previsao_Entrega, " +
          "    HR_Previsao_Entrega, " +
          "    OID_Pessoa_Destinatario, " +
          "    OID_Pessoa_Consignatario, " +
          "    OID_Pessoa_Entregadora, " +
          "    OID_Cliente_Redespacho, " +
          "    OID_Pessoa_Redespacho, " +
          "    OID_Modal, " +
          "    OID_Coleta, " +
          "    OID_Centro_Custo, " +
          "    OID_Lote_Faturamento, " +
          "    OID_Participacao_Frete, " +
          "    OID_Redespacho, " +
          "    OID_Unidade, " +
          "    OID_Vendedor, " +
          "    OID_Cidade, " +
          "    OID_Cidade_Destino, " +
          "    OID_Tabela_Frete, " +
          "    OID_Pessoa_Pagador, " +
          "    OID_Produto, " +
          "    NR_Conhecimento, " +
          "    NR_Postagem, " +
          "    NR_Pedido, " +
          "    NM_Serie, " +
          "    NM_Liberacao_Veiculo, " +
          "    CD_Roteiro, " +
          "    TX_Roteiro, " +
          "    DM_Responsavel_Cobranca, " +
          "    DM_Tipo_Pagamento, " +
          "    DM_Tipo_Conhecimento, " +
          "    DM_Tipo_Postagem, " +
          "    DM_Tipo_Cobranca, " +
          "    DM_Tipo_Documento, " +
          "    PE_Aliquota_ICMS, " +
          "    TX_Observacao, " +
          "    DM_Isento_Seguro, " +
          "    NM_Natureza, " +
          "    NR_Lote, " +
          "    NM_Especie, " +
          "    DT_Emissao, " +
          "    DM_Situacao, " +
          "    DM_Impresso, " +
          "    oid_Taxa, " +
          "    VL_FRETE_PESO, " +
          "    VL_FRETE_VALOR, " +
          "    VL_SEC_CAT, " +
          "    VL_PEDAGIO, " +
          "    VL_DESPACHO, " +
          "    VL_OUTROS1, " +
          "    VL_OUTROS2, " +
          "    PE_Carga_Expressa, " +
          "    NR_Peso_Cubado, " +
          "    VL_TOTAL_FRETE, " +
          "    VL_BASE_CALCULO_ICMS, " +
          "    VL_ICMS, " +
          "    DM_QUEBRA_FATURAMENTO, " +
          "    NR_Transacao_Pedagio, " +
          "    NR_Peso, " +
          "    NR_Volumes, " +
          "    VL_Nota_Fiscal, " +
          "    VL_Nota_Fiscal_Seguro, " +
          "    VL_Frete_Redespacho, " +
          "    VL_Frete, " +
          "    DM_Retira_Aeroporto, " +
          "    TX_Local_Retirada, " +
          "    VL_Taxa_Terrestre_Origem, " +
          "    VL_Taxa_Terrestre_Destino, " +
          "    VL_Taxa_Agente, " +
          "    VL_Taxa_Transportador, " +
          "    DM_Tipo_Tarifa, " +
          "    NR_Master, " +
          "    NR_Conhecimento_Redespacho, " +
          "    oid_Unidade_Agente, " +
          "    CD_CFO_Conhecimento, " +
          "    CD_CFO_Conhecimento_Editado," +
          "    oid_Programacao_Carga, oid_Motorista) " +
          " values " +
          " ('" + ed.getOID_Conhecimento () + "', " +
          " '" + Uni_Key + "', " +
          ed.getOID_Usuario () + ", '" + ed.getCD_Rota_Entrega () + "', " +
          " '" + ed.getOID_Pessoa () + "', " +
          " '" + ed.getOID_Veiculo () + "', " +
          " '" + ed.getOID_Carreta () + "', " +
          " '" + ed.getOID_Carreta2 () + "', " +
          JavaUtil.getSQLDate (dt_coleta) + ", " +
          JavaUtil.getSQLDate (ed.getDT_Programada ()) + ", " +
          " '" + hr_coleta + "', " +
          JavaUtil.getSQLDate (ed.getDT_Previsao_Entrega ()) + ", " +
          " '" + ed.getHR_Previsao_Entrega () + "', " +
          " '" + ed.getOID_Pessoa_Destinatario () + "', " +
          " '" + ed.getOID_Pessoa_Consignatario () + "', " +
          " '" + ed.getOID_Pessoa_Entregadora () + "', " +
          " '" + ed.getOID_Cliente_Redespacho () + "', " +
          " '" + ed.getOID_Pessoa_Redespacho () + "', " +
          ed.getOID_Modal () + "," +
          ed.getOID_Coleta () + "," +
          ed.getOID_Centro_Custo () + "," +
          ed.getOID_Lote_Faturamento () + "," +
          ed.getOID_Participacao_Frete () + "," +
          ed.getOID_Redespacho () + "," +
          ed.getOID_Unidade () + ", " +
          " '" + OID_Vendedor + "', " +
          ed.getOID_Cidade () + ", " +
          ed.getOID_Cidade_Destino () + ", " +
          " '" + ed.getOID_Tabela_Frete () + "', " +
          " '" + ed.getOID_Pessoa_Pagador () + "', " +
          ed.getOID_Produto () + "," +
          ed.getNR_Conhecimento () + ", " +
          ed.getNR_Postagem () + ", " +
          " '" + ed.getNR_Pedido () + "', " +
          " '" + ed.getNM_Serie () + "', " +
          " '" + ed.getNM_Liberacao_Veiculo () + "', " +
          " '" + ed.getCD_Roteiro () + "', " +
          " '" + ed.getTX_Roteiro () + "', " +
          " '" + ed.getDM_Responsavel_Cobranca () + "', " +
          " '" + ed.getDM_Tipo_Pagamento () + "', " +
          " '" + ed.getDM_Tipo_Conhecimento () + "', " +
          " '" + ed.getDM_Tipo_Postagem () + "', " +
          " '" + ed.getDM_Tipo_Cobranca () + "', " +
          JavaUtil.getSQLStringDef (ed.getDM_Tipo_Documento () , "C") + ", " +
          ed.getPE_Aliquota_ICMS () + ", " +
          " '" + ed.getTX_Observacao () + "', " +
          " '" + ed.getDM_Isento_Seguro () + "', " +
          " '" + ed.getNM_Natureza () + "', " +
          " '" + ed.getNR_Lote () + "', " +
          " '" + ed.getNM_Especie () + "', " +
          " '" + ed.getDT_Emissao () + "', " +
          " '" + "G" + "', " +
          " '" + ed.getDM_Impresso () + "', " +
          ed.getOID_Taxa () + "," +
          ed.getVL_FRETE_PESO () + "," +
          ed.getVL_FRETE_VALOR () + "," +
          ed.getVL_SEC_CAT () + "," +
          ed.getVL_PEDAGIO () + "," +
          ed.getVL_DESPACHO () + "," +
          ed.getVL_OUTROS1 () + "," +
          ed.getVL_OUTROS2 () + "," +
          ed.getPE_Carga_Expressa () + "," +
          ed.getNR_Peso_Cubado () + "," +
          ed.getVL_TOTAL_FRETE () + "," +
          ed.getVL_BASE_CALCULO_ICMS () + "," +
          ed.getVL_ICMS () + ", " +
          " '0'" + ", " +
          util.getSQLStringDef (ed.getNR_Transacao_Pedagio () , "") + ", " +
          ed.getNR_Peso () + ", " +
          ed.getNR_Volumes () + ", " +
          ed.getVL_Nota_Fiscal () + ", " +
          ed.getVL_Nota_Fiscal_Seguro () + ", " +
          ed.getVL_Frete_Redespacho () + ", " +
          ed.getVL_Frete () + ", " +
          util.getSQLString (ed.getDM_Retira_Aeroporto ()) + ", " +
          util.getSQLString (ed.getTX_Local_Retirada ()) + ", " +
          ed.getVL_Taxa_Terrestre_Origem () + ", " +
          ed.getVL_Taxa_Terrestre_Destino () + ", " +
          ed.getVL_Taxa_Agente () + ", " +
          ed.getVL_Taxa_Transportador () + ", " +
          util.getSQLString (ed.getDM_Tipo_Tarifa ()) + ", " +
          util.getSQLString (ed.getNR_Master ()) + ", " +
          util.getSQLString (ed.getNR_Conhecimento_Redespacho ()) + ", " +
          (ed.getOid_Unidade_Agente () <= 0 ? "null" : String.valueOf (ed.getOid_Unidade_Agente ())) + ", " +
          util.getSQLStringDef (ed.getCD_CFO () , "") + ", " +
          util.getSQLStringDef (ed.getCD_CFO_Conhecimento_Editado () , "") + "," +
          ed.getOid_Programacao_Carga() + ",'" + ed.getOID_Motorista() + "')";

System.out.print (sql);

      int i = executasql.executarUpdate (sql);

      if (ed.getOID_Lote_Faturamento () > 0) {
        chave = (String.valueOf (ed.getOID_Lote_Faturamento ()) + String.valueOf (System.currentTimeMillis ()));
        sql = " insert into Documentos_Lotes_Faturamentos (OID_Documento_Lote_Faturamento, DM_Tipo_Documento, OID_Conhecimento, OID_Lote_Faturamento ) values ";
        sql += "('" + chave + "','C','" + ed.getOID_Conhecimento () + "'," + ed.getOID_Lote_Faturamento () + ")";
        // executasql.executarUpdate (sql);
      }

      // TESTE PARA SETAR SITUACAO/COLETA=REALIZADO
      if (ed.getOID_Coleta() > 0) {
          sql = " UPDATE Coletas SET dm_situacao='3' " +
          		" WHERE oid_coleta="+ed.getOID_Coleta();
          executasql.executarUpdate (sql);
        }

      if (ed.getOID_Nota_Fiscal () != null && !ed.getOID_Nota_Fiscal ().equals ("") && !ed.getOID_Nota_Fiscal ().equals ("null")) {

        sql = " SELECT * from Conhecimentos_Notas_Fiscais";
        sql += " WHERE Conhecimentos_Notas_Fiscais.OID_Nota_Fiscal = '" + ed.getOID_Nota_Fiscal () + "'";
        ResultSet rs = this.executasql.executarConsulta (sql);

        while (rs.next ()) {
          DM_Nota_Em_Conhecimento = true;
        }

        if (DM_Nota_Em_Conhecimento) {
          //  throw new Mensagens("Nota Fiscal Lan�ada em Outro Conhecimento");
        }

// USANDO A INCLUS�O DO CONHECIMENTO_NOTA_FISCAL

        Conhecimento_Nota_FiscalED edCTRC_NF = new Conhecimento_Nota_FiscalED ();
        edCTRC_NF.setOID_Conhecimento (ed.getOID_Conhecimento ());
        edCTRC_NF.setOID_Nota_Fiscal (ed.getOID_Nota_Fiscal ());
        edCTRC_NF.setDT_Conhecimento_Nota_Fiscal (Data.getDataDMY ());
        edCTRC_NF.setHR_Conhecimento_Nota_Fiscal (Data.getHoraHM ());
        edCTRC_NF.setDM_Tipo_Conhecimento ("C");

        new Conhecimento_Nota_FiscalBD (executasql).inclui (edCTRC_NF);

      }

      if ("S".equals (ed.getDM_Tipo_Conhecimento ())) {
          // System.out.println ("envia email ");
          String nm_Email_Usuario="";
          try {
            sql ="SELECT NM_Email FROM Usuarios WHERE oid_Usuario=" +ed.getOID_Usuario();

            // System.out.println (sql);

            ResultSet res = this.executasql.executarConsulta (sql);
            if (res.next ()) {
              nm_Email_Usuario=res.getString("NM_Email");
            }
          }
          catch (SQLException e) {
            throw new Excecoes (e.getMessage () , e , getClass ().getName () , "envia emal inclusao )");
          }

          new Pessoa_EMailBD (this.executasql).envia_eMail (String.valueOf (ed.getOID_Pessoa()) , "COTELE" , "" , " Verificar COTACAO da Solicitacao NR: " + ed.getNR_Conhecimento () + "  NR Pedido:" + ed.getNR_Pedido () , "" , "","");
          new Pessoa_EMailBD (this.executasql).envia_eMail (String.valueOf (ed.getOID_Pessoa_Destinatario ()) , "COTELE" , "" , " Verificar COTACAO da Solicitacao NR: " + ed.getNR_Conhecimento () + "  NR Pedido:" + ed.getNR_Pedido () , "" , "","");
        }


      conED.setOID_Conhecimento (ed.getOID_Conhecimento ());
      conED.setDM_Impresso (ed.getDM_Impresso ());
      conED.setDM_Tipo_Conhecimento (ed.getDM_Tipo_Conhecimento ());
    }

    catch (SQLException e) {
      e.printStackTrace ();
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "inclui(ConhecimentoED ed)");
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "inclui(ConhecimentoED ed)");
    }
    return conED;
  }

  public void altera (ConhecimentoED ed) throws Excecoes {

      String mensagem="erro ao Alterar.";
	  try {
		String sql = " SELECT oid_Conhecimento FROM Conhecimentos " +
				     " WHERE Conhecimentos.DM_Impresso='N' " +
				     " AND   Conhecimentos.OID_Conhecimento = '" + ed.getOID_Conhecimento () + "'";

		ResultSet res = this.executasql.executarConsulta (sql);
		if (res.next ()) {

			String Pessoa_Pagador = ed.getOID_Pessoa_Pagador ();

			ed.setOID_Pessoa_Pagador (verificaCredito (Pessoa_Pagador));

			ed.setOID_Vendedor(OID_Vendedor);

		    if ("S".equals (ed.getDM_Tipo_Conhecimento ())) { //suprimentos
		      String oid_P = verificaEntregador_Modal (ed.getOID_Modal ());
		      if (!"".equals (oid_P)) {
		        ed.setOID_Pessoa_Entregadora (oid_P);
		      }
		    }

		    if (!util.doValida (ed.getDM_Tipo_Cobranca ())) {
		      ed.setDM_Tipo_Cobranca ("B"); // SETA como FATURADO
		    }

		    sql = " UPDATE Conhecimentos SET  " +
	        "  OID_Pessoa= '" + ed.getOID_Pessoa () + "'" +
	        " ,OID_Pessoa_Destinatario= '" + ed.getOID_Pessoa_Destinatario () + "'" +
	        " ,OID_Pessoa_Consignatario= '" + ed.getOID_Pessoa_Consignatario () + "'" +
	        " ,OID_Pessoa_Entregadora= '" + ed.getOID_Pessoa_Entregadora () + "'" +
	        " ,OID_Cliente_Redespacho= '" + ed.getOID_Cliente_Redespacho () + "'" +
	        " ,OID_Motorista= '" + ed.getOID_Motorista () + "'" +
	        " ,OID_Pessoa_Redespacho= '" + ed.getOID_Pessoa_Redespacho () + "'" +
	        " ,OID_Modal= " + ed.getOID_Modal () +
	        //" ,Oid_Programacao_Carga= " + ed.getOid_Programacao_Carga() +
	        " ,OID_Produto= " + ed.getOID_Produto () +
	        " ,OID_Cidade= " + ed.getOID_Cidade () +
	        " ,OID_Centro_Custo= " + ed.getOID_Centro_Custo () +
	        " ,OID_Cidade_Destino= " + ed.getOID_Cidade_Destino () +
	        " ,OID_Pessoa_Pagador= '" + ed.getOID_Pessoa_Pagador () + "'" +
	        " ,OID_Vendedor= '" + ed.getOID_Vendedor() + "'" +
	        " ,DM_Responsavel_Cobranca= " + util.getSQLStringDef (ed.getDM_Responsavel_Cobranca () , "") +
	        " ,DM_Tipo_Pagamento= " + util.getSQLStringDef (ed.getDM_Tipo_Pagamento () , "") +
	        " ,NR_Pedido= " + util.getSQLStringDef (ed.getNR_Pedido () , "") +
	        " ,DM_Tipo_Conhecimento= " + util.getSQLStringDef (ed.getDM_Tipo_Conhecimento () , "") +
	        " ,DM_Retira_Aeroporto= " + util.getSQLStringDef (ed.getDM_Retira_Aeroporto () , "") +
	        " ,TX_Local_Retirada= " + util.getSQLStringDef (ed.getTX_Local_Retirada () , "") +
	        " ,DM_Tipo_Cobranca= '" + ed.getDM_Tipo_Cobranca () + "'" +
	        " ,PE_Aliquota_ICMS= " + ed.getPE_Aliquota_ICMS () +
	        " ,TX_Observacao= '" + ed.getTX_Observacao () + "'" +
	        " ,DM_Isento_Seguro= " + util.getSQLStringDef (ed.getDM_Isento_Seguro () , "") +
	        " ,NM_Atendente= '" + ed.getNM_Atendente () + "'" +
	        " ,NM_Natureza= '" + ed.getNM_Natureza () + "'" +
	        " ,NR_Lote= '" + ed.getNR_Lote () + "'" +
	        " ,NM_Especie= '" + ed.getNM_Especie () + "'" +
	        " ,NM_Pessoa_Entrega= '" + ed.getNM_Pessoa_Entrega () + "'" +
	        " ,PE_Carga_Expressa   = " + ed.getPE_Carga_Expressa () +
	        " ,NR_Transacao_Pedagio = " + JavaUtil.getSQLStringDef (ed.getNR_Transacao_Pedagio () , "") +
	        " ,CD_Roteiro = " + util.getSQLString (ed.getCD_Roteiro ()) +
	        " ,TX_Roteiro = " + util.getSQLString (ed.getTX_Roteiro ()) +
	        " ,DM_Tipo_Tarifa = " + util.getSQLString (ed.getDM_Tipo_Tarifa ()) +
	        " ,DM_Cia_Aerea = " + util.getSQLString (ed.getDM_Cia_Aerea ()) +
	        " ,NR_Master = " + util.getSQLString (ed.getNR_Master ()) +
	        " ,oid_Unidade_Agente = " + (ed.getOid_Unidade_Agente () <= 0 ? "null" : String.valueOf (ed.getOid_Unidade_Agente ())) +
	        " ,CD_CFO_Conhecimento_Editado = " + util.getSQLStringDef (ed.getCD_CFO_Conhecimento_Editado () , "");

	    if ("B".equals (ed.getDM_Tipo_Conhecimento ()) || "S".equals (ed.getDM_Tipo_Conhecimento ())) {
	      sql += ", NR_Peso   = " + ed.getNR_Peso () +
	          ", NR_Volumes   = " + ed.getNR_Volumes () +
	          " ,VL_Total_Frete   = " + ed.getVL_TOTAL_FRETE () +
	          ", VL_Nota_Fiscal   = " + ed.getVL_Nota_Fiscal () + " ";
	    }

	    if ("R".equals (ed.getDM_Tipo_Conhecimento ())) { //redespacho
	        sql += " ,VL_Frete_Redespacho   = " + ed.getVL_Frete_Redespacho();
	    }

	    sql += ", NR_Peso_Cubado = " + ed.getNR_Peso_Cubado ();

	    if (ed.getNR_Postagem () > 0) {
	      sql += ", NR_Postagem= '" + ed.getNR_Postagem () + "'" +
	          ", DM_Tipo_Postagem= " + util.getSQLStringDef (ed.getDM_Tipo_Postagem () , "");
	    }

	    if (JavaUtil.doValida (ed.getNM_Liberacao_Veiculo ())) {
	      sql += ", NM_Liberacao_Veiculo= '" + ed.getNM_Liberacao_Veiculo () + "'";
	    }

	    if (JavaUtil.doValida (ed.getNR_Conhecimento_Redespacho ())) {
		      sql += ", NR_Conhecimento_Redespacho= '" + ed.getNR_Conhecimento_Redespacho () + "'";
		}

	    if (JavaUtil.doValida (ed.getCD_Rota_Entrega ())) {
	      sql += ", CD_Rota_Entrega = '" + ed.getCD_Rota_Entrega () + "'";
	    }

	    sql+=", VL_Nota_Fiscal_Seguro   = " + ed.getVL_Nota_Fiscal_Seguro () + " ";

	    if (JavaUtil.doValida (ed.getOID_Veiculo ())) {
	      sql += ", OID_Veiculo= '" + ed.getOID_Veiculo () + "'";
	    }
	    if (JavaUtil.doValida (ed.getOID_Carreta ())) {
	      sql += ", OID_Carreta= '" + ed.getOID_Carreta () + "'";
	    }
	    if (JavaUtil.doValida (ed.getOID_Carreta2 ())) {
	      sql += ", OID_Carreta2= '" + ed.getOID_Carreta2 () + "'";
	    }
	    if (JavaUtil.doValida (ed.getDT_Coleta ())) {
	      sql += ", DT_Coleta= '" + ed.getDT_Coleta () + "'";
	    }
	    if (JavaUtil.doValida (ed.getDT_Programada ())) {
	      sql += ", DT_Programada= '" + ed.getDT_Programada () + "'";
	    }
	    if (JavaUtil.doValida (ed.getDT_Previsao_Entrega ())) {
	      sql += ", DT_Previsao_Entrega= '" + ed.getDT_Previsao_Entrega () + "'";
	    }
	    if (JavaUtil.doValida (ed.getHR_Previsao_Entrega ())) {
	      sql += ", HR_Previsao_Entrega= '" + ed.getHR_Previsao_Entrega () + "'";
	    }
	    sql += " where oid_Conhecimento = '" + ed.getOID_Conhecimento () + "'";
	      // System.out.println (sql);
	      int i = executasql.executarUpdate (sql);
	  	}else {
          try {
              mensagem="= >>>>>>>>  Conhecimento j� IMPRESSO ! <<<<<<<<<<<";
      	    throw new Exception (mensagem);
          }
          catch (Exception e) {
              throw new Excecoes (mensagem , e , this.getClass ().getName () , "deleta(ConhecimentoED ed)");
          }
      }

    }
    catch (Exception e) {
      throw new Excecoes (mensagem , e , this.getClass ().getName () , "altera(ConhecimentoED ed)");
    }
  }

  public void altera_AWB (ConhecimentoED ed) throws Excecoes {

    // System.out.println ("==================altera_AWB==========================================="); ;

    String sql = " UPDATE Conhecimentos SET  " +
        "  OID_Cidade_Aereo_Origem= " + ed.getOID_Cidade_Aereo_Origem () +
        " ,OID_Cidade_Aereo_Destino= " + ed.getOID_Cidade_Aereo_Destino () +
        " ,TX_Local_Retirada= " + util.getSQLStringDef (ed.getTX_Local_Retirada () , "") +
        " ,TX_Observacao_AWB= '" + ed.getTX_Observacao_AWB () + "'" +
        " ,TX_Observacao_AWB2= '" + ed.getTX_Observacao_AWB2 () + "'" +
        " ,TX_Observacao_AWB3= '" + ed.getTX_Observacao_AWB3 () + "'" +
        " ,TX_Observacao_AWB4= '" + ed.getTX_Observacao_AWB4 () + "'" +
        " ,TX_Observacao_AWB5= '" + ed.getTX_Observacao_AWB5 () + "'" +
        " ,TX_Observacao_AWB6= '" + ed.getTX_Observacao_AWB6 () + "'" +
        " ,NM_Natureza_AWB= '" + ed.getNM_Natureza_AWB () + "'" +
        " ,NM_Especie_AWB= '" + ed.getNM_Especie_AWB () + "'" +
        " ,DM_Cia_Aerea = " + util.getSQLString (ed.getDM_Cia_Aerea ()) +
        " ,DM_Tipo_Tarifa = " + util.getSQLString (ed.getDM_Tipo_Tarifa ()) +
        " ,DM_Tipo_Pagamento = " + util.getSQLString (ed.getDM_Tipo_Pagamento ()) +
        " ,DM_Retira_Aeroporto = " + util.getSQLString (ed.getDM_Retira_Aeroporto ()) +
        " ,DM_Tipo_Embalagem = " + util.getSQLString (ed.getDM_Tipo_Embalagem ()) +
        ", NR_Peso_AWB   = " + ed.getNR_Peso_AWB () +
        ", NR_Volumes_AWB   = " + ed.getNR_Volumes_AWB () +
        ", VL_Nota_Fiscal_Declarado   = " + ed.getVL_Nota_Fiscal_Declarado () + " " +
        ", VL_Frete_Aereo   = " + ed.getVL_Frete_Aereo () + " " +
        ", VL_Frete_Valor_AWB   = " + ed.getVL_Frete_Valor_AWB () + " " +
        ", VL_Taxa_Terrestre_Origem   = " + ed.getVL_Taxa_Terrestre_Origem () + " " +
        ", VL_Taxa_Terrestre_Destino   = " + ed.getVL_Taxa_Terrestre_Destino () + " " +
        ", VL_Taxa_Transportador   = " + ed.getVL_Taxa_Transportador () + " " +
        ", VL_Master   = " + (ed.getVL_Frete_Aereo () + ed.getVL_Taxa_Terrestre_Origem () + ed.getVL_Taxa_Terrestre_Destino () + ed.getVL_Taxa_Transportador ()) + " " +
        ", VL_Tarifa_AWB   = " + ed.getVL_Tarifa_AWB ();

    // System.out.println ("ALTERA AWB->> " + ed.getNR_AWB ());

    if (ed.getNR_AWB () > 0) {
      sql += " ,NR_AWB = " + ed.getNR_AWB ();
    }

    sql += " WHERE oid_Conhecimento = '" + ed.getOID_Conhecimento () + "'";
    // System.out.println (sql);

    try {
      int i = executasql.executarUpdate (sql);
    }
    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "altera_AWB(ConhecimentoED ed)");
    }
  }

  public void altera_Minuta_Aereo (ConhecimentoED ed) throws Excecoes {

    String sql = " UPDATE Conhecimentos SET  " +
        "  OID_Cidade= " + ed.getOID_Cidade () +
        " ,OID_Cidade_Destino= " + ed.getOID_Cidade_Destino () +
        " ,TX_Local_Retirada= " + util.getSQLStringDef (ed.getTX_Local_Retirada () , "") +
        " ,TX_Observacao_AWB= '" + ed.getTX_Observacao_AWB () + "'" +
        " ,NM_Natureza_AWB= '" + ed.getNM_Natureza_AWB () + "'" +
        " ,NM_Especie= '" + ed.getNM_Especie () + "'" +
        " ,DM_Cia_Aerea = " + util.getSQLString (ed.getDM_Cia_Aerea ()) +
        " ,DM_Tipo_Tarifa = " + util.getSQLString (ed.getDM_Tipo_Tarifa ()) +
        " ,DM_Retira_Aeroporto = " + util.getSQLString (ed.getDM_Retira_Aeroporto ()) +
        " ,DM_Tipo_Embalagem = " + util.getSQLString (ed.getDM_Tipo_Embalagem ()) +
        " WHERE oid_Conhecimento = '" + ed.getOID_Conhecimento () + "'";
    // System.out.println (sql);

    try {
      int i = executasql.executarUpdate (sql);
    }
    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "altera_Minuta_Aereo(ConhecimentoED ed)");
    }
  }

  public void altera_Cobranca (ConhecimentoED ed) throws Excecoes {

    if (!util.doValida (ed.getDM_Tipo_Cobranca ())) {
      ed.setDM_Tipo_Cobranca ("B"); // SETA como FATURADO
    }

    String sql = " update Conhecimentos set DM_Tipo_Cobranca= '" + ed.getDM_Tipo_Cobranca () + "'";
    sql += " where oid_Conhecimento = '" + ed.getOID_Conhecimento () + "'";
    try {
      int i = executasql.executarUpdate (sql);
    }
    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "altera(ConhecimentoED ed)");
    }
  }

  public void altera_Pagador (ConhecimentoED ed) throws Excecoes {

    String sql = null;

    String Pessoa_Pagador = ed.getOID_Pessoa_Pagador ();
    this.verificaCredito (Pessoa_Pagador);

    try {

      sql = " update Conhecimentos set OID_Pessoa_Pagador= '" + ed.getOID_Pessoa_Pagador () + "' ";
      sql += " ,DM_Tipo_Pagamento= '" + ed.getDM_Tipo_Pagamento () + "'";
      sql += " ,DM_Tipo_Cobranca= '" + ed.getDM_Tipo_Cobranca () + "'";
      if (String.valueOf (ed.getOID_Pessoa_Redespacho ()) != null &&
          !String.valueOf (ed.getOID_Pessoa_Redespacho ()).equals ("") &&
          !String.valueOf (ed.getOID_Pessoa_Redespacho ()).equals ("null")) {
        sql += " ,OID_Pessoa_Redespacho = '" + ed.getOID_Pessoa_Redespacho () + "'";
      }
      else {
        sql += " ,OID_Pessoa_Redespacho = null";
      }
      sql += " where oid_Conhecimento = '" + ed.getOID_Conhecimento () + "'";

      int i = executasql.executarUpdate (sql);

      if (JavaUtil.doValida (ed.getDM_Tipo_Pagamento ()) && ed.getDM_Tipo_Pagamento ().equals ("3")) {
        sql = " update Conhecimentos set OID_Pessoa_Consignatario= '" + ed.getOID_Pessoa_Pagador () + "' ";
        if (String.valueOf (ed.getOID_Pessoa_Redespacho ()) != null &&
            !String.valueOf (ed.getOID_Pessoa_Redespacho ()).equals ("") &&
            !String.valueOf (ed.getOID_Pessoa_Redespacho ()).equals ("null")) {
          sql += " ,OID_Pessoa_Redespacho = '" + ed.getOID_Pessoa_Redespacho () + "'";
        }
        else {
          sql += " ,OID_Pessoa_Redespacho = null";
        }
        sql += " where oid_Conhecimento = '" + ed.getOID_Conhecimento () + "'";
        i = executasql.executarUpdate (sql);
      }

    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "altera_Pagador(ConhecimentoED ed)");
    }
  }

  public void altera_Entregador (ConhecimentoED ed) throws Excecoes {

    String sql = null;

    try {

      sql = " UPDATE Conhecimentos SET " +
          "      NR_Conhecimento_Entregadora= '" + ed.getNR_Conhecimento_Entregadora () + "' " +
          "     ,DT_Previsao_Entregadora= '" + ed.getDT_Previsao_Entregadora () + "' " +
          "     ,VL_Frete_Entregadora= " + ed.getVL_Frete_Entregadora ();

      if (ed.getOID_Pessoa_Entregadora () != null && ed.getOID_Pessoa_Entregadora ().length () > 4) {
        sql += " ,OID_Pessoa_Entregadora= '" + ed.getOID_Pessoa_Entregadora () + "'";
      }

      sql += " WHERE  Conhecimentos.oid_Conhecimento = '" + ed.getOID_Conhecimento () + "'";
      // System.out.print (sql);
      int i = executasql.executarUpdate (sql);
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "altera_Pagador(ConhecimentoED ed)");
    }
  }

  public void altera_Situacao (ConhecimentoED ed) throws Excecoes {
    String sql = null;

    try {

      sql = " update Conhecimentos set DM_Situacao= '" + ed.getDM_Situacao () + "' ";
      sql += " where oid_Conhecimento = '" + ed.getOID_Conhecimento () + "'";

      int i = executasql.executarUpdate (sql);
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "altera_Situacao(ConhecimentoED ed)");
    }
  }

  public void altera_Rota_Entrega (ConhecimentoED ed) throws Excecoes {
    String sql = null;

    try {

      sql = " update Conhecimentos set CD_Rota_Entrega= '" + ed.getCD_Rota_Entrega () + "' ";
      sql += " where oid_Conhecimento = '" + ed.getOID_Conhecimento () + "'";

      int i = executasql.executarUpdate (sql);
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "altera_Rota_Entrega(ConhecimentoED ed)");
    }
  }

  public void altera_Placas (ConhecimentoED ed) throws Excecoes {
    String sql = null;

    try {

      sql = " update Conhecimentos set oid_Veiculo= '" + ed.getOID_Veiculo () + "', oid_Carreta= '" + ed.getOID_Carreta () + "', NM_Liberacao_Veiculo='" + ed.getNM_Liberacao_Veiculo () + "', DT_Coleta='" + ed.getDT_Coleta () + "' ";
      sql += " where oid_Conhecimento = '" + ed.getOID_Conhecimento () + "'";

      int i = executasql.executarUpdate (sql);
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "altera_Placas(ConhecimentoED ed)");
    }
  }


  public void altera_Previsao_Entrega (ConhecimentoED ed) throws Excecoes {

    String sql = null;

    try {

      sql = " update Conhecimentos set ";
      sql += " DT_PREVISAO_ENTREGA = '" + ed.getDT_Previsao_Entrega () + "'";
      sql += " ,HR_PREVISAO_ENTREGA = '" + ed.getHR_Previsao_Entrega () + "'";

      if ( ed.getOID_Pessoa_Entregadora() !=null &&  ed.getOID_Pessoa_Entregadora().length()>4) {
    	  sql += " ,oid_pessoa_entregadora = '" + ed.getOID_Pessoa_Entregadora () + "'";
      }


      //      sql += " VL_Custo_Entrega = " + ed.getVL_Custo_Entrega();

      sql += " WHERE Conhecimentos.oid_Conhecimento = '" + ed.getOID_Conhecimento () + "' ";

      executasql.executarUpdate (sql);
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "altera_Previsao_Entrega(ConhecimentoED ed)");
    }
  }

  public void solicita_Cotacao (ConhecimentoED ed) throws Excecoes {

    ConhecimentoED edCon = this.getByRecord (ed);
    String nm_Email_Usuario="";
    // System.out.println ("ENVIANDO EMAIL solicita_Cotacao ");

    if ("S".equals (edCon.getDM_Tipo_Conhecimento ())) {
      // System.out.println ("envia email ");

      try {
        String sql ="SELECT NM_Email FROM Usuarios WHERE oid_Usuario=" +edCon.getOID_Usuario();

        // System.out.println (sql);

        ResultSet res = this.executasql.executarConsulta (sql);
        if (res.next ()) {
          nm_Email_Usuario=res.getString("NM_Email");
        }
      }
      catch (SQLException e) {
        throw new Excecoes (e.getMessage () , e , getClass ().getName () , "altera_Situacao(ConhecimentoED ed)");
      }

      new Pessoa_EMailBD (this.executasql).envia_eMail (String.valueOf (edCon.getOID_Pessoa_Pagador ()) , "COTELE" , "" , "  NR Solicitacao:" + edCon.getNR_Conhecimento () + "  NR Pedido:" + edCon.getNR_Pedido () , "" , "",nm_Email_Usuario);
    }
  }

  public void solicita_Coleta_Eletronica (ConhecimentoED ed) throws Excecoes {

	    ConhecimentoED edCon = this.getByRecord (ed);
	    String nm_Email_Usuario="";
	    String sql="";

	    String url="http://nalthus.kieling.com.br:8080/sistema/operacao_nacional/link_coleta.jsp?oid_Conhecimento="+ed.getOID_Conhecimento();


	    // System.out.println ("ENVIANDO EMAIL solicita_Cotacao ");

	      // System.out.println ("envia email ");

	      try {

		    sql ="UPDATE Conhecimentos SET DT_Solicitado_Coleta='"+ Data.getDataDMY() + "' ,HR_Solicitado_Coleta='" + Data.getHoraHM() + "' WHERE oid_Conhecimento='" + ed.getOID_Conhecimento() +"'";
			// System.out.println (sql);

			executasql.executarUpdate (sql);

	    	sql ="SELECT Pessoas.oid_Pessoa, Pessoas.NM_Razao_Social FROM Pessoas, Modal WHERE Pessoas.oid_Pessoa = Modal.oid_Pessoa AND oid_Modal=" +edCon.getOID_Modal();
	        // System.out.println (sql);

	        ResultSet res = this.executasql.executarConsulta (sql);
	        while (res.next ()) {
		      new Pessoa_EMailBD (this.executasql).envia_eMail (String.valueOf (res.getString("oid_Pessoa")) , "SOLCE" , "" , " Transportadora: "+ res.getString("NM_Razao_Social")+ " segue solicita��o de Coleta NR :" + edCon.getNR_Conhecimento () + "  NR Pedido:" + edCon.getNR_Pedido () + " conforme Link:"+url, "" , "","");
	        }

	      }

	      catch (SQLException e) {
	        throw new Excecoes (e.getMessage () , e , getClass ().getName () , "solicita_Coleta_Eletronica(ConhecimentoED ed)");
	      }
  }


  public void confirma_Coleta_Eletronica (ConhecimentoED ed, String recebida_confirmada) throws Excecoes {

	    ConhecimentoED edCon = this.getByRecord (ed);
	    String nm_Email_Usuario="";
	    String sql ="";
	    //System.out.println ("confirma_Coleta_Eletronica ");

	       //System.out.println ("recebida_confirmada=>> "+recebida_confirmada);

	      try {
	    	if ("CONFIRMADA".equals(recebida_confirmada)) {
    	        sql ="UPDATE Conhecimentos SET DT_Confirmacao_Coleta='"+ Data.getDataDMY() + "' ,HR_Confirmacao_Coleta='" + Data.getHoraHM() + "' WHERE oid_Conhecimento='" + ed.getOID_Conhecimento() +"'";
    	        executasql.executarUpdate (sql);

    	        new Pessoa_EMailBD (this.executasql).envia_eMail (String.valueOf (parametro_FixoED.getOID_Cliente_Complemento_Padrao()) , "CONFCE" , "" , " Confirmada a Coleta NR:" + edCon.getNR_Conhecimento () + "  NR Pedido:" + edCon.getNR_Pedido () +" dia '"+ Data.getDataDMY() + "' as '" + Data.getHoraHM() + "'" , "" , "","");

	    	}
	    	if ("REALIZADA".equals(recebida_confirmada)) {
    	        sql ="UPDATE Conhecimentos SET DT_Realizacao_Coleta='"+ ed.getDT_Realizacao_Coleta() + "' ,HR_Realizacao_Coleta='" + ed.getHR_Realizacao_Coleta() + "' WHERE oid_Conhecimento='" + ed.getOID_Conhecimento() +"'";
    	        executasql.executarUpdate (sql);

    	        new Pessoa_EMailBD (this.executasql).envia_eMail (String.valueOf (parametro_FixoED.getOID_Cliente_Complemento_Padrao()) , "REALCE" , "" , " a Coleta NR:" + edCon.getNR_Conhecimento () + "  NR Pedido:" + edCon.getNR_Pedido () +" foi realizada dia '"+ ed.getDT_Realizacao_Coleta() + "' as '" + ed.getHR_Realizacao_Coleta() + "'" , "" , "","");

	    	}
	      }
	      catch (SQLException e) {
	      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "confirma_Coleta_Eletronica(ConhecimentoED ed)");
	    }


}


  public void altera_Custo (ConhecimentoED ed) throws Excecoes {

    String sql = null;

    try {

      sql = " update Conhecimentos set " + " VL_Total_Custo = " + ed.getVL_Total_Custo () + " where oid_Conhecimento = '" + ed.getOID_Conhecimento () + "'";

      int i = executasql.executarUpdate (sql);
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "altera_Custo(ConhecimentoED ed)");
    }
  }

  public void altera_Totais (ConhecimentoED ed) throws Excecoes {

    String sql = " update Conhecimentos set " + "   VL_Total_Frete = 0 " + "  ,VL_ICMS = 0 " + "  ,VL_Base_Calculo_ICMS = 0 " + "  ,VL_Total_Custo = 0 " + " where oid_Conhecimento = '"
        + ed.getOID_Conhecimento () + "'";

    try {
      int i = executasql.executarUpdate (sql);
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "altera_Totais(ConhecimentoED ed)");
    }
  }

  public void altera_Total_Frete (ConhecimentoED ed) throws Excecoes {

    String sql = null;

    try {
      sql = " update Conhecimentos set ";
      sql += "  VL_Total_Frete = " + ed.getVL_TOTAL_FRETE ();
      sql += " ,VL_Frete_Peso = " + ed.getVL_FRETE_PESO ();
      sql += " ,VL_Pedagio = " + ed.getVL_PEDAGIO ();
      sql += " ,VL_ICMS = " + ed.getVL_ICMS ();
      sql += " ,VL_BASE_CALCULO_ICMS = " + ed.getVL_BASE_CALCULO_ICMS ();
      sql += " ,DM_Tipo_Calculo = 'I'";
      sql += " ,DM_Tipo_Desconto_Pedagio = " + util.getSQLStringDef (ed.getDM_Tipo_Desconto_Pedagio () , "");
      sql += " where oid_Conhecimento = '" + ed.getOID_Conhecimento () + "'";

      int i = executasql.executarUpdate (sql);

    }

    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "altera_Total_Frete(ConhecimentoED ed)");
    }
  }


  public void altera_seguro (ConhecimentoED ed) throws Excecoes {

	    String sql = null;

	    try {
	      sql = " update Conhecimentos set ";
	      sql += "  VL_Nota_Fiscal_Seguro = " + ed.getVL_Nota_Fiscal_Seguro();
	      sql += " where oid_Conhecimento = '" + ed.getOID_Conhecimento () + "'";

	      int i = executasql.executarUpdate (sql);

	    }

	    catch (Exception e) {
	      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "altera_seguro(ConhecimentoED ed)");
	    }
	  }

  public void altera_Fiscal (ConhecimentoED ed) throws Excecoes {

    String sql = null;

    try {
      sql = " update Conhecimentos set ";
      sql += "  VL_ICMS = " + ed.getVL_ICMS ();
      sql += " ,VL_BASE_CALCULO_ICMS = " + ed.getVL_BASE_CALCULO_ICMS ();
      sql += " ,PE_ALIQUOTA_ICMS = " + ed.getPE_ALIQUOTA_ICMS ();

      if (JavaUtil.doValida (ed.getDT_Emissao ())) {
          sql += " ,DT_Emissao='" + ed.getDT_Emissao () + "'";
      }

      if (!"".equals (ed.getCD_CFO_Conhecimento_Editado ())) {
        sql += " ,CD_CFO_Conhecimento='" + ed.getCD_CFO_Conhecimento_Editado () + "'";
      }

      sql += " where oid_Conhecimento = '" + ed.getOID_Conhecimento () + "'";
      // System.out.println (sql);
      executasql.executarUpdate (sql);

      if ("S".equals (parametro_FixoED.getDM_Gera_Livro_Fiscal ())) {
	      Livro_FiscalED edLivro = new Livro_FiscalED ();
	      edLivro.setOID_Conhecimento(ed.getOID_Conhecimento ());
	      new Livro_FiscalBD (executasql).geraLivro_Fiscal_CTRC(edLivro);
      }

    }

    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "altera_Total_Frete(ConhecimentoED ed)");
    }
  }

  public void altera_Vendedor (ConhecimentoED ed) throws Excecoes {

    String sql = null;

    try {

      sql = " update Conhecimentos set ";
      sql += " OID_Vendedor= '" + ed.getOID_Vendedor () + "', ";
      sql += " DM_Tipo_Comissao= '" + ed.getDM_Tipo_Comissao () + "' ";
      sql += " where oid_Conhecimento = '" + ed.getOID_Conhecimento () + "'";

      //    // System.out.println("sql alt vend" + sql);

      int i = executasql.executarUpdate (sql);
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "altera_Vendedor(ConhecimentoED ed)");
    }
  }


  public void deleta (ConhecimentoED ed) throws Excecoes {

    String sql = null;
    String mensagem="erro ao excluir!";

    try {

        sql = " SELECT oid_Conhecimento FROM Conhecimentos " +
        	  " WHERE Conhecimentos.DM_Impresso='N' " +
        	  " AND   Conhecimentos.OID_Conhecimento = '" + ed.getOID_Conhecimento () + "'";

        ResultSet res = null;
        res = this.executasql.executarConsulta (sql);

        if (res.next ()) {

		      sql = " SELECT Conhecimentos_Notas_Fiscais.OID_Nota_Fiscal from Conhecimentos_Notas_Fiscais ";
		      sql += " WHERE Conhecimentos_Notas_Fiscais.OID_Conhecimento = '" + ed.getOID_Conhecimento () + "'";

		      res = this.executasql.executarConsulta (sql);

		      while (res.next ()) {

		        sql = " update Notas_Fiscais set DM_Situacao= 'A' ";
		        sql += " where Notas_Fiscais.oid_Nota_Fiscal = '" + res.getString ("OID_Nota_Fiscal") + "'";

		        int i = executasql.executarUpdate (sql);
		      }

		      sql = "delete from Conhecimentos_Notas_Fiscais WHERE oid_Conhecimento = ";
		      sql += "('" + ed.getOID_Conhecimento () + "')";

		      int i = executasql.executarUpdate (sql);

		      sql = "delete from Movimentos_Conhecimentos WHERE oid_Conhecimento = ";
		      sql += "('" + ed.getOID_Conhecimento () + "')";

		      i = executasql.executarUpdate (sql);

		      sql = "delete from Ocorrencias_Conhecimentos WHERE oid_Conhecimento = ";
		      sql += "('" + ed.getOID_Conhecimento () + "')";

		      i = executasql.executarUpdate (sql);

		      sql = "delete from Conhecimentos WHERE oid_Conhecimento = ";
		      sql += "('" + ed.getOID_Conhecimento () + "')";

		      i = executasql.executarUpdate (sql);

        }else {
            try {
                mensagem="= >>>>>>>>  Conhecimento j� IMPRESSO ! <<<<<<<<<<<";
        	    throw new Exception (mensagem);
            }
            catch (Exception e) {
                throw new Excecoes (mensagem , e , this.getClass ().getName () , "deleta(ConhecimentoED ed)");
            }
        }

    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem (mensagem);
      excecoes.setMetodo ("excluir");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  ///### ZERAR O CAMPO TOTAL DO FRETE PARA NAO SAIR EM RELATORIOS
  public String cancela (ConhecimentoED ed) throws Excecoes {

    String sql = null;
    String nm_situacao="";
    try {

      ed = this.getByRecord(ed);

      sql = " SELECT Viagens.OID_Manifesto, Manifestos.NR_Manifesto " +
      		" FROM  Viagens, Manifestos " +
      		" WHERE Viagens.oid_Manifesto = Manifestos.oid_Manifesto  " +
      	    " AND   Viagens.OID_Conhecimento = '" + ed.getOID_Conhecimento () + "'";

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      if (res.next ()) {
    	  nm_situacao=" Conhecimento vinculado ao Manifesto:" + res.getString("NR_Manifesto");
      }

      sql = " SELECT  Duplicatas.NR_Duplicata " +
		" FROM  Origens_Duplicatas, Duplicatas " +
		" WHERE Origens_Duplicatas.oid_Duplicata = Duplicatas.oid_Duplicata  " +
	    " AND   Origens_Duplicatas.DM_Situacao <>'E' " +
	    " AND   Origens_Duplicatas.OID_Conhecimento = '" + ed.getOID_Conhecimento () + "'";
      res = this.executasql.executarConsulta (sql);
      if (res.next ()) {
		  nm_situacao+=" Conhecimento vinculado a Duplicata :" + res.getString("NR_Duplicata");
      }

      sql = " SELECT Documentos_Lotes_Fornecedores.oid_Lote_Fornecedor   " +
		  " FROM   Movimentos_Conhecimentos, Documentos_Lotes_Fornecedores " +
		  " WHERE  Movimentos_Conhecimentos.oid_Conhecimento = Documentos_Lotes_Fornecedores.oid_Conhecimento " +
		  " AND    Documentos_Lotes_Fornecedores.oid_Lote_Fornecedor >0 " +
		  " AND    Movimentos_Conhecimentos.oid_Conhecimento = '" +  ed.getOID_Conhecimento () + "'";

      res = this.executasql.executarConsulta (sql);
      if (res.next ()) {
    	  nm_situacao+=" Conhecimento vinculado na Aprov.Pagamento:" + res.getString("oid_Lote_Fornecedor");
      }


      if ("S".equals (parametro_FixoED.getDM_Gera_Livro_Fiscal ())) {
          sql = " SELECT cd_Natureza_Operacao   " +
		  " FROM   Naturezas_Operacoes, Conhecimentos " +
		  " WHERE  Naturezas_Operacoes.cd_Natureza_Operacao = Conhecimentos.cd_cfo_conhecimento " +
		  " AND    Conhecimentos.oid_Conhecimento ='" +  ed.getOID_Conhecimento () + "'";

		  res = this.executasql.executarConsulta (sql);
	      if (!res.next ()) {
	    	 nm_situacao+=" Cadastrar Natureza Operacao do Conhecimento para ajuste do Livro Fiscal ! " ;
	      }
      }

      if ("S".equals (parametro_FixoED.getDM_Gera_Lancamento_Contabil ())) {
  	    Movimento_Contabil_TempED edMovCtb = new Movimento_Contabil_TempED ();
  	    edMovCtb.setDt_Movimento (ed.getDT_Emissao());
  	    if (!edMovCtb.isPeriodo_Aberto ()) {
  	    	nm_situacao+=" Per�odo Cont�bil Fechado !!! " + ed.getDT_Emissao();
  	    }
      }

      if ("".equals(nm_situacao)) {

	      sql = " update  Conhecimentos  " +
	      		" set DM_Situacao='C' " +
	      		"     ,TX_Observacao='***CTO CANCELADO***' " +
	      		"     ,DT_Cancelamento='" + Data.getDataDMY() + "'" +
	      		"     ,NR_Postagem=0 " +
	      		"     ,oid_Programacao_Carga=0 " +
	      		"     ,oid_Redespacho=0 " +
	      		"     ,VL_Total_Frete_Canc = " + ed.getVL_TOTAL_FRETE() +
	      		"     ,VL_ICMS_Canc = " + ed.getVL_ICMS() +
	      		"     ,vl_base_calculo_icms_Canc = " + ed.getVL_BASE_CALCULO_ICMS() +
	      		"     ,pe_aliquota_icms_Canc = " + ed.getPE_ALIQUOTA_ICMS() +
	      		"     ,VL_Total_Frete = 0.0 " +
	      		"     ,VL_ICMS = 0.0 " +
	      		"     ,vl_base_calculo_icms = 0.0 " +
	      		"     ,pe_aliquota_icms = 0.0 ";
	      sql += " where oid_Conhecimento = '" + ed.getOID_Conhecimento () + "'";
	      executasql.executarUpdate (sql);

	      sql =  " UPDATE Notas_Fiscais set DM_Situacao= 'A' ";
	      sql += " where Notas_Fiscais.oid_Nota_Fiscal in (SELECT OID_Nota_Fiscal " +
	             "                                         from Conhecimentos_Notas_Fiscais " +
	        	 "                                         WHERE OID_Conhecimento = '" + ed.getOID_Conhecimento () + "')";

	      executasql.executarUpdate (sql);

	      sql = "UPDATE Redespachos SET oid_Conhecimento='' WHERE oid_Conhecimento = ";
	      sql += "('" + ed.getOID_Conhecimento () + "')";
	      executasql.executarUpdate (sql);

	      sql = "DELETE from Conhecimentos_Notas_Fiscais WHERE oid_Conhecimento = ";
	      sql += "('" + ed.getOID_Conhecimento () + "')";
	      executasql.executarUpdate (sql);

	      sql = "DELETE from Viagens WHERE oid_Conhecimento = ";
	      sql += "('" + ed.getOID_Conhecimento () + "')";
	      executasql.executarUpdate (sql);

	      sql = "DELETE from Postagens WHERE oid_Conhecimento = ";
	      sql += "('" + ed.getOID_Conhecimento () + "')";
	      executasql.executarUpdate (sql);

	      if ("S".equals (parametro_FixoED.getDM_Gera_Livro_Fiscal ())) {
		      Livro_FiscalED edLivro = new Livro_FiscalED ();
		      edLivro.setOID_Conhecimento(ed.getOID_Conhecimento ());
		      new Livro_FiscalBD (executasql).geraLivro_Fiscal_CTRC(edLivro);
	      }

	      nm_situacao="OK";

	      if ("S".equals (parametro_FixoED.getDM_Gera_Lancamento_Contabil ())) {
    	    Movimento_Contabil_TempED edMovCtb = new Movimento_Contabil_TempED ();
    	    edMovCtb.setDt_Movimento (ed.getDT_Emissao());
    	    if (!edMovCtb.isPeriodo_Aberto ()) {
    	    	throw new Excecoes ("Per�odo Cont�bil fechado !!! " + ed.getDT_Emissao());
    	    }else{
    	    	new Lancamento_ContabilBD (this.executasql).deleta_CTB_Conhecimento (ed);
    	    }
	      }
	    }
    }

	catch (Exception exc) {
      exc.printStackTrace ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao cancelar");
      excecoes.setMetodo ("cancela(ConhecimentoED ed)");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return nm_situacao;
  }

  public String substituiConhecimento (ConhecimentoED ed) throws Excecoes {

	    String sql = null;
	    String nm_situacao="";
	    String nr_novo_conhecimento="";
	    try {
	     ConhecimentoED edNovo = new ConhecimentoED ();
	      edNovo.setOID_Conhecimento(ed.getOID_Novo_Conhecimento ());
	      edNovo = this.getByRecord(edNovo);

		  ConhecimentoED edVelho = new ConhecimentoED ();
		  edVelho.setOID_Conhecimento(ed.getOID_Conhecimento ());
		  edVelho = this.getByRecord(edVelho);

	      //testes no NOVO Conhecimento
	      sql = " SELECT Viagens.OID_Manifesto, Manifestos.NR_Manifesto " +
	      		" FROM  Viagens, Manifestos " +
	      		" WHERE Viagens.oid_Manifesto = Manifestos.oid_Manifesto  " +
	      	    " AND   Viagens.OID_Conhecimento = '" + ed.getOID_Novo_Conhecimento () + "'";

	      ResultSet res = null;
	      res = this.executasql.executarConsulta (sql);
	      if (res.next ()) {
	    	  ////nm_situacao=" O Novo Conhecimento est� vinculado ao Manifesto:" + res.getString("NR_Manifesto");
	      }

	      sql = " SELECT  Duplicatas.NR_Duplicata " +
			" FROM  Origens_Duplicatas, Duplicatas " +
			" WHERE Origens_Duplicatas.oid_Duplicata = Duplicatas.oid_Duplicata  " +
		    " AND   Origens_Duplicatas.DM_Situacao <>'E' " +
		    " AND   Origens_Duplicatas.OID_Conhecimento = '" + ed.getOID_Novo_Conhecimento () + "'";
	      res = this.executasql.executarConsulta (sql);
	      if (res.next ()) {
			  /////nm_situacao+="O Novo Conhecimento vinculado a Duplicata :" + res.getString("NR_Duplicata");
	      }

	      sql = " SELECT Movimentos_Conhecimentos.oid_Lote_Fornecedor   " +
			  " FROM   Movimentos_Conhecimentos, Documentos_Lotes_Fornecedores " +
			  " WHERE  Movimentos_Conhecimentos.oid_Conhecimento = Documentos_Lotes_Fornecedores.oid_Conhecimento " +
			  " AND    Movimentos_Conhecimentos.oid_Conhecimento = '" +  ed.getOID_Novo_Conhecimento () + "'";

	      res = this.executasql.executarConsulta (sql);
	      if (res.next ()) {
	    	//  nm_situacao+="O Novo  Conhecimento vinculado na Aprov.Pagamento:" + res.getString("oid_Lote_Fornecedor");
	      }

	      //testes no Conhecimento ATUAL
	      sql = " SELECT  Duplicatas.NR_Duplicata " +
			" FROM  Origens_Duplicatas, Duplicatas " +
			" WHERE Origens_Duplicatas.oid_Duplicata = Duplicatas.oid_Duplicata  " +
		    " AND   Origens_Duplicatas.DM_Situacao <>'E' " +
		    " AND   Origens_Duplicatas.OID_Conhecimento = '" + ed.getOID_Conhecimento () + "'";
	      res = this.executasql.executarConsulta (sql);
	      if (res.next ()) {
			  ///nm_situacao+="O Conhecimento Atual vinculado a Duplicata :" + res.getString("NR_Duplicata");
	      }

	      sql = " SELECT Movimentos_Conhecimentos.oid_Lote_Fornecedor   " +
			  " FROM   Movimentos_Conhecimentos, Documentos_Lotes_Fornecedores " +
			  " WHERE  Movimentos_Conhecimentos.oid_Conhecimento = Documentos_Lotes_Fornecedores.oid_Conhecimento " +
			  " AND    Movimentos_Conhecimentos.oid_Conhecimento = '" +  ed.getOID_Conhecimento () + "'";

	      res = this.executasql.executarConsulta (sql);
	      if (res.next ()) {
	    	  ///nm_situacao+="O Conhecimento Atual est� vinculado na Aprov.Pagamento:" + res.getString("oid_Lote_Fornecedor");
	      }

	      if ("".equals(nm_situacao)) {

	    	  sql = "UPDATE Viagens SET oid_Conhecimento='" +  ed.getOID_Novo_Conhecimento () + "' WHERE oid_Conhecimento = '" + ed.getOID_Conhecimento () + "'";
	          executasql.executarUpdate (sql);

	    	  sql = "UPDATE Documentos_Lotes_Fornecedores SET oid_Conhecimento='" +  ed.getOID_Novo_Conhecimento () + "', NR_Documento='" + edNovo.getNR_Conhecimento()+  "' WHERE oid_Conhecimento = '" + ed.getOID_Conhecimento () + "'";
	          executasql.executarUpdate (sql);

	    	  sql = "UPDATE Documentos_Lotes_Faturamentos SET oid_Conhecimento='" +  ed.getOID_Novo_Conhecimento () + "', NR_Documento='" + edNovo.getNR_Conhecimento()+  "' WHERE oid_Conhecimento = '" + ed.getOID_Conhecimento () + "'";
	          executasql.executarUpdate (sql);

		      sql = "UPDATE Conhecimentos SET TX_Observacao='CTO CANCELADO SUBSTITU�DO PELO CTO " + edNovo.getNR_Conhecimento()+ "(" + edVelho.getTX_Observacao() + ")" + "', oid_conhecimento_substituto='" + ed.getOID_Novo_Conhecimento () + "' WHERE oid_Conhecimento = '" +  ed.getOID_Conhecimento () + "'";
	          executasql.executarUpdate (sql);


		      sql = "UPDATE Conhecimentos SET TX_Observacao=' CTO SUBSTITUTO DO CTO CANCELADO  " + edVelho.getNR_Conhecimento()  + "', oid_Conhecimento_Original='" + ed.getOID_Conhecimento () + "' WHERE oid_Conhecimento = '" +  ed.getOID_Novo_Conhecimento () + "'";
	          executasql.executarUpdate (sql);

	          int qt_mov=0;
		      sql = " SELECT Movimentos_Conhecimentos.oid_Movimento_Conhecimento, Movimentos_Conhecimentos.oid_Lote_Fornecedor,  Movimentos_Conhecimentos.oid_Ordem_Frete  " +
		      	" FROM   Movimentos_Conhecimentos, Tipos_Movimentos " +
		        " WHERE  Movimentos_Conhecimentos.oid_Tipo_Movimento = Tipos_Movimentos.oid_Tipo_Movimento" +
		      	" AND    Tipos_Movimentos.DM_Tipo_Movimento = 'D' " +
		      	" AND   (Movimentos_Conhecimentos.oid_Lote_Fornecedor >0 OR Movimentos_Conhecimentos.oid_Ordem_Frete <>'null' )  " +
		      	" AND    Movimentos_Conhecimentos.oid_Conhecimento = '" +  ed.getOID_Conhecimento () + "'";

		      res = this.executasql.executarConsulta (sql);
		      while (res.next ()) {
		    	  sql = "UPDATE Movimentos_Conhecimentos SET oid_Conhecimento='" +  ed.getOID_Novo_Conhecimento () + "', oid_Ordem_Frete='" + res.getString("oid_Ordem_Frete") + "', oid_Lote_Fornecedor='" + res.getString("oid_Lote_Fornecedor") + "'  WHERE oid_Movimento_Conhecimento = '" + res.getString("oid_Movimento_Conhecimento") + "'";
		          executasql.executarUpdate (sql);
		          qt_mov++;
		      }
		      sql = " SELECT Movimentos_Conhecimentos.oid_Movimento_Conhecimento, Movimentos_Conhecimentos.oid_Lote_Fornecedor,  Movimentos_Conhecimentos.oid_Ordem_Frete  " +
			      	" FROM   Movimentos_Conhecimentos, Tipos_Movimentos " +
			        " WHERE  Movimentos_Conhecimentos.oid_Tipo_Movimento = Tipos_Movimentos.oid_Tipo_Movimento" +
			      	" AND    Tipos_Movimentos.oid_Tipo_Movimento=86 " +  //ressarcimento
			      	" AND    Movimentos_Conhecimentos.oid_Conhecimento = '" +  ed.getOID_Conhecimento () + "'";
		      res = this.executasql.executarConsulta (sql);
		      while (res.next ()) {
		    	  sql = "UPDATE Movimentos_Conhecimentos SET oid_Conhecimento='" +  ed.getOID_Novo_Conhecimento () + "', oid_Ordem_Frete='" + res.getString("oid_Ordem_Frete") + "', oid_Lote_Fornecedor='" + res.getString("oid_Lote_Fornecedor") + "'  WHERE oid_Movimento_Conhecimento = '" + res.getString("oid_Movimento_Conhecimento") + "'";
		          executasql.executarUpdate (sql);
		          qt_mov++;
		      }


		      if (qt_mov>0) {
			      Movimento_ConhecimentoBD = new Movimento_ConhecimentoBD (this.executasql);
		          Movimento_ConhecimentoED edMovimento_Conhecimento = new Movimento_ConhecimentoED ();
		          edMovimento_Conhecimento.setOID_Conhecimento(ed.getOID_Conhecimento ());
		          Movimento_ConhecimentoBD.calcula_Margem (edMovimento_Conhecimento);

		          edMovimento_Conhecimento.setOID_Conhecimento(ed.getOID_Novo_Conhecimento ());
		          Movimento_ConhecimentoBD.calcula_Margem (edMovimento_Conhecimento);
		      }

		      nm_situacao="OK";
		    }
	    }

		catch (Exception exc) {
	      exc.printStackTrace ();
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      excecoes.setMensagem ("Erro ao substituiConhecimento");
	      excecoes.setMetodo ("substituiConhecimento(ConhecimentoED ed)");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }
	    return nm_situacao;
	  }

  ///### ZERAR O CAMPO TOTAL DO FRETE PARA NAO SAIR EM RELATORIOS
  public void zeraCTRC (ConhecimentoED ed) throws Excecoes {

    String sql = null;
    try {

      ed = this.getByRecord(ed);

      sql = " update Conhecimentos  set TX_Observacao='***CTO ZERADO***', NR_Postagem=0,  VL_Total_Frete=0, vl_base_calculo_icms=0, vl_icms = 0, pe_aliquota_icms = 0 ";
      sql += " where oid_Conhecimento = '" + ed.getOID_Conhecimento () + "'";
      int i = executasql.executarUpdate (sql);

      sql = " SELECT Notas_Fiscais.OID_Nota_Fiscal, " +
      		"        Notas_Fiscais.NR_Peso, " +
      		"        Notas_Fiscais.NR_Cubagem, " +
      		"        Notas_Fiscais.NR_Cubagem_Total, " +
      		"        Notas_Fiscais.NR_Peso_Cubado, " +
      		"        Notas_Fiscais.NR_Volumes, " +
      		"        Notas_Fiscais.VL_Nota_Fiscal," +
      		"        Conhecimentos_Notas_Fiscais.DM_Tipo_Conhecimento " +
      		" from Conhecimentos_Notas_Fiscais, Notas_Fiscais";
      sql += " WHERE Conhecimentos_Notas_Fiscais.OID_Conhecimento = '" + ed.getOID_Conhecimento () + "'" +
      		"  AND Conhecimentos_Notas_Fiscais.OID_Nota_Fiscal = Notas_Fiscais.OID_Nota_Fiscal";
      ResultSet res = this.executasql.executarConsulta (sql);

      String oid_Nota_Fiscal = null;
      String DM_Tipo_Conhecimento = null;
      double NR_Peso = 0;
      double NR_Cubagem = 0;
      double NR_Cubagem_Total = 0;
      double NR_Peso_Cubado = 0;
      double NR_Volumes = 0;
      double VL_Nota_Fiscal = 0;

      while (res.next ()) {
        oid_Nota_Fiscal = res.getString ("OID_Nota_Fiscal");  // Pega s� uma nota pois ser� gerado apenas uma nota de n�mero zero
        DM_Tipo_Conhecimento = res.getString ("DM_Tipo_Conhecimento");
        NR_Peso = NR_Peso + res.getDouble("NR_Peso");
        NR_Cubagem = NR_Cubagem + res.getDouble("NR_Cubagem");
        NR_Cubagem_Total = NR_Cubagem_Total + res.getDouble("NR_Cubagem_Total");
        NR_Peso_Cubado = NR_Peso_Cubado + res.getDouble("NR_Peso_Cubado");
        NR_Volumes = NR_Volumes + res.getDouble("NR_Volumes");
        VL_Nota_Fiscal = VL_Nota_Fiscal + res.getDouble("VL_Nota_Fiscal");
      }

      Nota_FiscalED nota_FiscalED = new Nota_FiscalED();
      nota_FiscalED.setOID_Nota_Fiscal(oid_Nota_Fiscal);
      Nota_FiscalBD nota_FiscalBD = new Nota_FiscalBD(this.executasql);
      nota_FiscalED = nota_FiscalBD.getByRecord(nota_FiscalED);
      nota_FiscalED.setNR_Nota_Fiscal(0);

      nota_FiscalED.setNR_Peso (NR_Peso);
      nota_FiscalED.setNR_Cubagem (NR_Cubagem);
      nota_FiscalED.setNR_Cubagem_Total (NR_Cubagem_Total);
      nota_FiscalED.setNR_Peso_Cubado (NR_Peso_Cubado);
      nota_FiscalED.setNR_Volumes (NR_Volumes);
      nota_FiscalED.setVL_Nota_Fiscal (VL_Nota_Fiscal);

      nota_FiscalED = nota_FiscalBD.inclui(nota_FiscalED);

      sql = "DELETE from Conhecimentos_Notas_Fiscais WHERE oid_Conhecimento = ";
      sql += "('" + ed.getOID_Conhecimento () + "')";
      i = executasql.executarUpdate (sql);

      Conhecimento_Nota_FiscalED conhecimento_nota_FiscalED = new Conhecimento_Nota_FiscalED();
      conhecimento_nota_FiscalED.setOID_Nota_Fiscal(nota_FiscalED.getOID_Nota_Fiscal());
      conhecimento_nota_FiscalED.setOID_Conhecimento(ed.getOID_Conhecimento ());
      conhecimento_nota_FiscalED.setDM_Tipo_Conhecimento(DM_Tipo_Conhecimento);
      conhecimento_nota_FiscalED.setDT_Conhecimento_Nota_Fiscal(Data.getDataDMY());
      conhecimento_nota_FiscalED.setHR_Conhecimento_Nota_Fiscal(Data.getHoraHM());

      Conhecimento_Nota_FiscalBD conhecimento_nota_FiscalBD = new Conhecimento_Nota_FiscalBD(this.executasql);
      conhecimento_nota_FiscalBD.inclui(conhecimento_nota_FiscalED);

      sql = "DELETE from Postagens WHERE oid_Conhecimento = ";
      sql += "('" + ed.getOID_Conhecimento () + "')";
      i = executasql.executarUpdate (sql);

      if ("S".equals (parametro_FixoED.getDM_Gera_Livro_Fiscal ())) {
	      Livro_FiscalED edLivro = new Livro_FiscalED ();
	      edLivro.setOID_Conhecimento(ed.getOID_Conhecimento ());
	      new Livro_FiscalBD (executasql).geraLivro_Fiscal_CTRC(edLivro);
      }

      if ("S".equals (parametro_FixoED.getDM_Gera_Lancamento_Contabil ())) {
        //desfaz os lancamentos contabeis do movimento

  	    Movimento_Contabil_TempED edMovCtb = new Movimento_Contabil_TempED ();
	    edMovCtb.setDt_Movimento (ed.getDT_Emissao());
	    if (!edMovCtb.isPeriodo_Aberto ()) {
	      throw new Excecoes ("Per�odo Cont�bil fechado !!! " + ed.getDT_Emissao());
	    }else{
        new Lancamento_ContabilBD (this.executasql).deleta_CTB_Conhecimento (ed);
      }

    }

    }

    catch (Exception exc) {
      exc.printStackTrace ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao zerar CTRC");
      excecoes.setMetodo ("zeraCTRC(ConhecimentoED ed)");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public String reentrega_Devolucao (ConhecimentoED ed) throws Excecoes {

    String sql = null;
    ResultSet res = null;
    String obs = "";

    ConhecimentoED conED = new ConhecimentoED ();
    try {

      conED = this.getByRecord (ed);
      long nr_Conhecimento=conED.getNR_Conhecimento();

      if ("3".equals (ed.getDM_Tipo_Conhecimento ())) {
        obs = "DEVOLUCAO CONHECIMENTO: " + String.valueOf (conED.getNR_Conhecimento ());
      }
      if ("4".equals (ed.getDM_Tipo_Conhecimento ())) {
        obs = "REENTREGA CONHECIMENTO: " + String.valueOf (conED.getNR_Conhecimento ());
      }

      conED.setDM_Tipo_Conhecimento (ed.getDM_Tipo_Conhecimento ());
      conED.setNR_Conhecimento (0);
      conED.setNR_ACT (0);
      conED.setNR_Duplicata (0);
      conED.setDM_Impresso ("N");
      conED.setDT_Emissao (Data.getDataDMY ());
      conED.setVL_FRETE_PESO (0);
      conED.setVL_FRETE_VALOR (0);
      conED.setVL_ICMS (0);
      conED.setVL_PEDAGIO (0);
      conED.setVL_TOTAL_FRETE (0);
      conED.setVL_BASE_CALCULO_ICMS (0);
      conED.setOID_Nota_Fiscal ("");
      if ("3".equals (conED.getDM_Tipo_Conhecimento ())) {
        String oid_Rem=conED.getOID_Pessoa();
        String oid_Dest=conED.getOID_Pessoa_Destinatario();
        long oid_Cid_Orig=conED.getOID_Cidade();
        long oid_Cid_Dest=conED.getOID_Cidade_Destino();
        conED.setOID_Pessoa_Destinatario(oid_Rem);
        conED.setOID_Pessoa(oid_Dest);
        conED.setOID_Cidade(oid_Cid_Dest);
        conED.setOID_Cidade_Destino(oid_Cid_Orig);
        if ("1".equals(conED.getDM_Tipo_Pagamento())){
          conED.setDM_Tipo_Pagamento("2");
        }else{
          conED.setDM_Tipo_Pagamento("1");
        }
        obs = "DEVOLUCAO CONHECIMENTO: " + String.valueOf (nr_Conhecimento);
      }
      if ("4".equals (conED.getDM_Tipo_Conhecimento ())) {
        obs = "REENTREGA CONHECIMENTO: " + String.valueOf (nr_Conhecimento);
      }
      conED = this.inclui (conED);


      if (conED.getOID_Conhecimento () != null && conED.getOID_Conhecimento ().length () > 4) {

        sql = " SELECT Conhecimentos_Notas_Fiscais.OID_Nota_Fiscal " +
            " FROM   Conhecimentos_Notas_Fiscais " +
            " WHERE  Conhecimentos_Notas_Fiscais.OID_Conhecimento = '" + ed.getOID_Conhecimento () + "'";
        res = this.executasql.executarConsulta (sql);

        while (res.next ()) {
          Conhecimento_Nota_FiscalED edCTRC_NF = new Conhecimento_Nota_FiscalED ();
          edCTRC_NF.setOID_Conhecimento (conED.getOID_Conhecimento ());
          edCTRC_NF.setOID_Nota_Fiscal (res.getString ("OID_Nota_Fiscal"));
          edCTRC_NF.setDT_Conhecimento_Nota_Fiscal (Data.getDataDMY ());
          edCTRC_NF.setHR_Conhecimento_Nota_Fiscal (Data.getHoraHM ());
          edCTRC_NF.setDM_Tipo_Conhecimento ("C");
          new Conhecimento_Nota_FiscalBD (executasql).inclui (edCTRC_NF);
        }

        sql = " update Conhecimentos set TX_Observacao='" + obs + "', oid_Conhecimento_Original='" + ed.getOID_Conhecimento () + "' ";
        sql += " where oid_Conhecimento = '" + conED.getOID_Conhecimento () + "'";
        int i = executasql.executarUpdate (sql);
      }
    }

    catch (Exception exc) {
      exc.printStackTrace ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao cancelar");
      excecoes.setMetodo ("reentrega_Devolucao(ConhecimentoED ed)");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return conED.getOID_Conhecimento ();
  }

  public ArrayList lista (ConhecimentoED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();

    try {

      sql = " SELECT Conhecimentos.oid_Veiculo, " +
          " Conhecimentos.DM_Impresso, " +
          " Conhecimentos.NR_Lote, " +
          " Conhecimentos.DT_Emissao, " +
          " Conhecimentos.DT_Previsao_Entrega, " +
          " Conhecimentos.DT_Entrega, " +
          " Conhecimentos.DT_Solicitado_Coleta, " +
          " Conhecimentos.DT_Confirmacao_Coleta, " +
          " Conhecimentos.DT_Realizacao_Coleta, " +
          " Conhecimentos.DM_Tipo_Documento, " +
          " Conhecimentos.NR_Conhecimento, " +
          " Conhecimentos.NR_Postagem, " +
          " Conhecimentos.NR_Minuta, " +
          " Conhecimentos.NR_Pedido, " +
          " Conhecimentos.VL_Frete_Entregadora, " +
          " Conhecimentos.NR_Conhecimento_Entregadora, " +
          " Conhecimentos.oid_Pessoa_Entregadora, " +
          " Conhecimentos.DT_Previsao_Entregadora, " +
          " Conhecimentos.DT_Programada, " +
          " Conhecimentos.NR_ACT, " +
          " Conhecimentos.NR_AWB, " +
          " Conhecimentos.DM_Cia_Aerea, " +
          " Conhecimentos.NR_Nota_Fiscal_Servico, " +
          " Conhecimentos.VL_Total_Frete, " +
          " Conhecimentos.OID_Conhecimento, " +
          " Unidades.CD_Unidade, " +
          " Pessoa_Remetente.NM_Razao_Social as NM_Razao_Social_Remetente, " +
          " Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario " +
          " FROM Conhecimentos, Unidades, Pessoas Pessoa_Remetente, Pessoas Pessoa_Destinatario " +
          " WHERE Unidades.oid_Unidade = Conhecimentos.oid_Unidade " +
          " AND Conhecimentos.oid_Pessoa = Pessoa_Remetente.oid_Pessoa " +
          " AND Conhecimentos.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa ";

      if (String.valueOf (ed.getOID_Coleta ()) != null && !String.valueOf (ed.getOID_Coleta ()).equals ("") && !String.valueOf (ed.getOID_Coleta ()).equals ("0")) {
        sql += " and Conhecimentos.OID_Coleta = '" + ed.getOID_Coleta () + "'";
      }
      else {

        if (ed.getNR_Conhecimento () > 0) {
          sql += " and Conhecimentos.NR_Conhecimento = " + ed.getNR_Conhecimento ();
        }
        if (ed.getNR_Postagem () > 0) {
          sql += " and Conhecimentos.NR_Postagem = " + ed.getNR_Postagem ();
        }
        if (ed.getNR_Minuta () > 0) {
          sql += " and Conhecimentos.NR_Minuta = " + ed.getNR_Minuta ();
        }
        if (ed.getNR_ACT () > 0) {
          sql += " and Conhecimentos.NR_ACT = " + ed.getNR_ACT ();
        }
        if (ed.getNR_AWB () > 0) {
          sql += " and Conhecimentos.NR_AWB = " + ed.getNR_AWB ();
        }
        if (ed.getNR_Nota_Fiscal_Servico () > 0) {
          sql += " and Conhecimentos.NR_Nota_Fiscal_Servico = " + ed.getNR_Nota_Fiscal_Servico ();
        }
        if (String.valueOf (ed.getOID_Unidade ()) != null && !String.valueOf (ed.getOID_Unidade ()).equals ("0")) {
          sql += " and Conhecimentos.OID_Unidade = " + ed.getOID_Unidade ();
        }
        if (String.valueOf (ed.getOID_Pessoa ()) != null && !String.valueOf (ed.getOID_Pessoa ()).equals ("")
            && !String.valueOf (ed.getOID_Pessoa ()).equals ("null")) {
          sql += " and Conhecimentos.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
        }
        if (String.valueOf (ed.getOID_Pessoa_Destinatario ()) != null && !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("")
            && !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("null")) {
          sql += " and Conhecimentos.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario () + "'";
        }
        if (String.valueOf (ed.getOID_Pessoa_Entregadora ()) != null && !String.valueOf (ed.getOID_Pessoa_Entregadora ()).equals ("")
            && !String.valueOf (ed.getOID_Pessoa_Entregadora ()).equals ("null")) {
          sql += " and Conhecimentos.OID_Pessoa_Entregadora = '" + ed.getOID_Pessoa_Entregadora () + "'";
        }
        if (String.valueOf (ed.getOID_Pessoa_Consignatario ()) != null && !String.valueOf (ed.getOID_Pessoa_Consignatario ()).equals ("")
            && !String.valueOf (ed.getOID_Pessoa_Consignatario ()).equals ("null")) {
          sql += " and Conhecimentos.OID_Pessoa_Consignatario = '" + ed.getOID_Pessoa_Consignatario () + "'";
        }
        if (String.valueOf (ed.getDT_Emissao ()) != null && !String.valueOf (ed.getDT_Emissao ()).equals ("") && !String.valueOf (ed.getDT_Emissao ()).equals ("null")) {
          sql += " and Conhecimentos.DT_Emissao >= '" + ed.getDT_Emissao () + "'";
        }

        if (String.valueOf (ed.getDt_Emissao_Inicial ()) != null && !String.valueOf (ed.getDt_Emissao_Inicial ()).equals ("") && !String.valueOf (ed.getDt_Emissao_Inicial ()).equals ("null")) {
          sql += " and Conhecimentos.DT_Emissao >= '" + ed.getDt_Emissao_Inicial () + "'";
        }

        if (String.valueOf (ed.getDt_Emissao_Final ()) != null && !String.valueOf (ed.getDt_Emissao_Final ()).equals ("") && !String.valueOf (ed.getDt_Emissao_Final ()).equals ("null")) {
          sql += " and Conhecimentos.DT_Emissao <= '" + ed.getDt_Emissao_Final () + "'";
        }

        if (String.valueOf (ed.getDt_Previsao_Entrega_Inicial ()) != null && !String.valueOf (ed.getDt_Previsao_Entrega_Inicial ()).equals ("") && !String.valueOf (ed.getDt_Previsao_Entrega_Inicial ()).equals ("null")) {
          sql += " and Conhecimentos.DT_Previsao_Entrega >= '" + ed.getDt_Previsao_Entrega_Inicial () + "'";
        }

        if (String.valueOf (ed.getDt_Previsao_Entrega_Final ()) != null && !String.valueOf (ed.getDt_Previsao_Entrega_Final ()).equals ("") && !String.valueOf (ed.getDt_Previsao_Entrega_Final ()).equals ("null")) {
          sql += " and Conhecimentos.DT_Previsao_Entrega <= '" + ed.getDt_Previsao_Entrega_Final () + "'";
        }

        if (String.valueOf (ed.getOID_Veiculo ()) != null && !String.valueOf (ed.getOID_Veiculo ()).equals ("") && !String.valueOf (ed.getOID_Veiculo ()).equals ("null")) {
          sql += " and Conhecimentos.OID_Veiculo = '" + ed.getOID_Veiculo () + "'";
        }
        if (String.valueOf (ed.getOID_Carreta ()) != null && !String.valueOf (ed.getOID_Carreta ()).equals ("") && !String.valueOf (ed.getOID_Carreta ()).equals ("null")) {
          sql += " and Conhecimentos.OID_Carreta = '" + ed.getOID_Carreta () + "'";
        }

        if (String.valueOf (ed.getNR_Lote ()) != null && !String.valueOf (ed.getNR_Lote ()).equals ("") && !String.valueOf (ed.getNR_Lote ()).equals ("null")) {
          sql += " and Conhecimentos.NR_Lote = '" + ed.getNR_Lote () + "'";
        }
        if (String.valueOf (ed.getNM_Natureza ()) != null && !String.valueOf (ed.getNM_Natureza ()).equals ("") && !String.valueOf (ed.getNM_Natureza ()).equals ("null")) {
          sql += " and Conhecimentos.NM_Natureza = '" + ed.getNM_Natureza () + "'";
        }
        if (String.valueOf (ed.getDM_Tipo_Conhecimento ()) != null && !String.valueOf (ed.getDM_Tipo_Conhecimento ()).equals ("") && !String.valueOf (ed.getDM_Tipo_Conhecimento ()).equals ("null")) {
          sql += " and Conhecimentos.DM_Tipo_Conhecimento = '" + ed.getDM_Tipo_Conhecimento () + "'";
        }
        if (String.valueOf (ed.getDM_Tipo_Documento ()) != null && !String.valueOf (ed.getDM_Tipo_Documento ()).equals ("") && !String.valueOf (ed.getDM_Tipo_Documento ()).equals ("null")) {
          sql += " and Conhecimentos.DM_Tipo_Documento = '" + ed.getDM_Tipo_Documento () + "'";
        }

        if (String.valueOf (ed.getNR_Pedido ()) != null && !String.valueOf (ed.getNR_Pedido ()).equals ("") && !String.valueOf (ed.getNR_Pedido ()).equals ("null")) {
          sql += " and Conhecimentos.NR_Pedido = '" + ed.getNR_Pedido () + "'";
        }
        if (JavaUtil.doValida (ed.getCD_Rota_Entrega ())) {
          sql += " and Conhecimentos.CD_Rota_Entrega = '" + ed.getCD_Rota_Entrega () + "'";
        }

        if (JavaUtil.doValida (ed.getDM_Situacao_Entrega ())) {
          if ("E".equals (ed.getDM_Situacao_Entrega ())) {
            sql += " AND Conhecimentos.DT_Entrega is not null";
          }
          if ("A".equals (ed.getDM_Situacao_Entrega ())) {
            sql += " AND Conhecimentos.DT_Entrega is not null " +
                " AND Conhecimentos.DM_Tipo_Entrega ='A'";
          }
          if ("P".equals (ed.getDM_Situacao_Entrega ())) {
            sql += " AND Conhecimentos.DT_Entrega is not null " +
                " AND Conhecimentos.DM_Tipo_Entrega ='P'";
          }
          if ("D".equals (ed.getDM_Situacao_Entrega ())) {
            sql += " AND Conhecimentos.DT_Entrega is not null " +
                " AND Conhecimentos.DM_Tipo_Entrega ='D'";
          }
          if ("J".equals (ed.getDM_Situacao_Entrega ())) {
            sql += " AND Conhecimentos.DT_Entrega is not null " +
                " AND Conhecimentos.DM_Tipo_Entrega ='J'";
          }
          if ("N".equals (ed.getDM_Situacao_Entrega ())) {
            sql += " AND Conhecimentos.DT_Entrega is null";
          }
        }

      }
      /*
             if (String.valueOf (ed.getOID_Usuario ()) != null && ed.getOID_Usuario ()>0) {
        sql += " and Conhecimentos.OID_Usuario = " + ed.getOID_Usuario () +
               " and Conhecimentos.DM_Situacao <>'C' " +
               " and Conhecimentos.DM_Impresso ='N' " +
               " and Conhecimentos.DT_Emissao < '" + Data.getDataDMY() + "'";
             }
       */

      sql += " ORDER BY Conhecimentos.DT_Emissao Desc, Conhecimentos.NR_Conhecimento " +
          " LIMIT 500 ";

      // System.out.println (sql);

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);

      //popula

      FormataDataBean DataFormatada = new FormataDataBean ();

      while (res.next ()) {
        ConhecimentoED edVolta = new ConhecimentoED ();

        edVolta.setOID_Veiculo (res.getString ("OID_Veiculo"));
        edVolta.setDM_Impresso (res.getString ("DM_Impresso"));
        edVolta.setDM_Tipo_Documento (res.getString ("DM_Tipo_Documento"));

        edVolta.setDT_Emissao (res.getString ("DT_Emissao"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
        edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());

        edVolta.setDT_Solicitado_Coleta (FormataData.formataDataBT (res.getDate ("DT_Solicitado_Coleta")));
        edVolta.setDT_Confirmacao_Coleta (FormataData.formataDataBT (res.getDate ("DT_Confirmacao_Coleta")));
        edVolta.setDT_Realizacao_Coleta (FormataData.formataDataBT (res.getDate ("DT_Realizacao_Coleta")));

        edVolta.setDT_Programada (FormataData.formataDataBT (res.getDate ("DT_Programada")));

        edVolta.setDT_Previsao_Entrega (res.getString ("DT_Previsao_Entrega"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Previsao_Entrega ());
        edVolta.setDT_Previsao_Entrega (DataFormatada.getDT_FormataData ());

        edVolta.setDT_Entrega (res.getString ("DT_Entrega"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Entrega ());
        edVolta.setDT_Entrega (DataFormatada.getDT_FormataData ());

        if ("S".equals (edVolta.getDM_Impresso ())) {
          if ("C".equals (edVolta.getDM_Tipo_Documento ())) {
            edVolta.setNR_Conhecimento (res.getLong ("NR_Conhecimento"));
          }
        }

        edVolta.setVL_TOTAL_FRETE (res.getDouble ("vl_total_Frete"));

        edVolta.setNM_Tipo_Documento ("CTO");
        if ("M".equals (edVolta.getDM_Tipo_Documento ())) {
          edVolta.setNM_Tipo_Documento ("MIN");
        }
        if ("T".equals (edVolta.getDM_Tipo_Documento ())) {
          edVolta.setNM_Tipo_Documento ("ACT");
        }
        if ("W".equals (edVolta.getDM_Tipo_Documento ())) {
          edVolta.setNM_Tipo_Documento ("AWB");
        }
        if ("N".equals (edVolta.getDM_Tipo_Documento ())) {
          edVolta.setNM_Tipo_Documento ("NFS");
        }
        if ("F".equals (edVolta.getDM_Tipo_Documento ())) {
            edVolta.setNM_Tipo_Documento ("FT");
        }

        edVolta.setDM_Cia_Aerea (res.getString ("DM_Cia_Aerea"));

        if ("C".equals (edVolta.getDM_Tipo_Documento ())) {
          edVolta.setNR_Conhecimento (res.getLong ("NR_Conhecimento"));
        }
        if ("S".equals (edVolta.getDM_Tipo_Conhecimento ())) {
          edVolta.setNR_Conhecimento (res.getLong ("NR_Conhecimento"));
        }
        if (res.getLong ("NR_Minuta") > 0) {
          edVolta.setNR_Minuta (res.getLong ("NR_Minuta"));
        }
        if (res.getLong ("NR_Postagem") > 0) {
          edVolta.setNR_Postagem (res.getLong ("NR_Postagem"));
        }
        if (res.getLong ("NR_ACT") > 0) {
          edVolta.setNR_ACT (res.getLong ("NR_ACT"));
        }
        if (res.getLong ("NR_AWB") > 0) {
          edVolta.setNR_AWB (res.getLong ("NR_AWB"));
        }
        if ("N".equals (edVolta.getDM_Tipo_Documento ()) && res.getLong ("NR_Nota_Fiscal_Servico") > 0) {
          edVolta.setNR_Nota_Fiscal_Servico (res.getLong ("NR_Nota_Fiscal_Servico"));
        }
        if ("F".equals (edVolta.getDM_Tipo_Documento ())) {
           edVolta.setNR_Frete_Terceiro(res.getLong ("NR_Conhecimento"));
        }
        edVolta.setOID_Conhecimento (res.getString ("OID_Conhecimento"));
        edVolta.setCD_Unidade (res.getString ("CD_Unidade"));
        edVolta.setNM_Pessoa_Remetente (res.getString ("NM_Razao_Social_Remetente"));
        edVolta.setNM_Pessoa_Destinatario (res.getString ("NM_Razao_Social_Destinatario"));
        edVolta.setNR_Lote (res.getString ("NR_Lote"));

        //edVolta.setNM_Pessoa_Consignatario(res.getString("NM_Razao_Social_Consignatario"));
        edVolta.setNR_Pedido(" ");
        if (res.getString("NR_Pedido") != null && !res.getString("NR_Pedido").equals("null")) {
          edVolta.setNR_Pedido(res.getString ("NR_Pedido"));
        }

        edVolta.setNM_Pessoa_Entregadora(" ");
        edVolta.setNR_Conhecimento_Entregadora (" ");
        edVolta.setDT_Previsao_Entregadora (" ");

        if (res.getString("oid_Pessoa_Entregadora") != null && res.getString("oid_Pessoa_Entregadora").length()>4) {
          sql =" SELECT NM_Razao_Social FROM Pessoas WHERE oid_Pessoa='" +res.getString("oid_Pessoa_Entregadora") + "'";
          ResultSet res2 = this.executasql.executarConsulta (sql);
          while (res2.next ()) {
            edVolta.setNM_Pessoa_Entregadora(res2.getString("NM_Razao_Social"));
          }
          edVolta.setNR_Conhecimento_Entregadora(res.getString ("NR_Conhecimento_Entregadora"));
          edVolta.setDT_Previsao_Entregadora (res.getString ("DT_Previsao_Entregadora"));
          DataFormatada.setDT_FormataData (edVolta.getDT_Previsao_Entregadora ());
          edVolta.setDT_Previsao_Entregadora (DataFormatada.getDT_FormataData ());
          edVolta.setVL_Frete_Entregadora (res.getDouble ("VL_Frete_Entregadora"));
        }

        edVolta.setVL_Frete (res.getDouble ("VL_Total_Frete"));
        edVolta.setVL_TOTAL_FRETE(res.getDouble ("VL_Total_Frete"));


        list.add (edVolta);
      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "lista(ConhecimentoED ed)");
    }

    return list;
  }

  public ArrayList lista_Entregas_Mobile (ConhecimentoED ed) throws Excecoes {

	    String sql = null;
	    ArrayList list = new ArrayList ();

	    try {

	      sql = " SELECT Manifestos.oid_Veiculo, " +
      	  	  " Manifestos.oid_Manifesto, " +
	      	  " Manifestos.NR_Manifesto, " +
	          " Conhecimentos.DM_Impresso, " +
	          " Conhecimentos.NM_Serie, " +
	          " Conhecimentos.DT_Emissao, " +
	          " Conhecimentos.DT_Previsao_Entrega, " +
	          " Conhecimentos.DT_Entrega, " +
	          " Conhecimentos.DT_Solicitado_Coleta, " +
	          " Conhecimentos.DT_Confirmacao_Coleta, " +
	          " Conhecimentos.DT_Realizacao_Coleta, " +
	          " Conhecimentos.DM_Tipo_Documento, " +
	          " Conhecimentos.NR_Conhecimento, " +
	          " Conhecimentos.DT_Previsao_Entregadora, " +
	          " Conhecimentos.DT_Programada, " +
	          " Conhecimentos.VL_Total_Frete, " +
	          " Conhecimentos.OID_Conhecimento, " +
	          " Unidades.CD_Unidade, " +
	          " Pessoa_Remetente.NM_Razao_Social as NM_Razao_Social_Remetente, " +
	          " Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario " +
	          " FROM Conhecimentos, Unidades, Pessoas Pessoa_Remetente, Pessoas Pessoa_Destinatario, Manifestos, Viagens " +
	          " WHERE Unidades.oid_Unidade = Conhecimentos.oid_Unidade " +
	          " AND Conhecimentos.oid_Pessoa = Pessoa_Remetente.oid_Pessoa " +
	          " AND Conhecimentos.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa " +
	          " AND Conhecimentos.oid_conhecimento = Viagens.oid_Conhecimento " +
	          " AND Viagens.oid_Manifesto = Manifestos.oid_Manifesto " +
	      	  " AND Manifestos.dt_confirmacao_mobile IS NOT NULL ";

	        if (ed.getNR_Conhecimento () > 0) {
	          sql += " and Conhecimentos.NR_Conhecimento = " + ed.getNR_Conhecimento ();
	        }
	        if (String.valueOf (ed.getOID_Pessoa ()) != null && !String.valueOf (ed.getOID_Pessoa ()).equals ("")
	            && !String.valueOf (ed.getOID_Pessoa ()).equals ("null")) {
	          sql += " and Conhecimentos.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
	        }
	        if (String.valueOf (ed.getOID_Pessoa_Destinatario ()) != null && !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("")
	            && !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("null")) {
	          sql += " and Conhecimentos.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario () + "'";
	        }

	        if (String.valueOf (ed.getDt_Emissao_Inicial ()) != null && !String.valueOf (ed.getDt_Emissao_Inicial ()).equals ("") && !String.valueOf (ed.getDt_Emissao_Inicial ()).equals ("null")) {
	          sql += " and Manifestos.DT_Emissao >= '" + ed.getDt_Emissao_Inicial () + "'";
	        }

	        if (String.valueOf (ed.getDt_Emissao_Final ()) != null && !String.valueOf (ed.getDt_Emissao_Final ()).equals ("") && !String.valueOf (ed.getDt_Emissao_Final ()).equals ("null")) {
	          sql += " and Manifestos.DT_Emissao <= '" + ed.getDt_Emissao_Final () + "'";
	        }

	        if (String.valueOf (ed.getOID_Unidade ()) != null && !String.valueOf (ed.getOID_Unidade ()).equals ("0")) {
		      sql += " and Manifestos.OID_Unidade = " + ed.getOID_Unidade ();
		    }
	        if (String.valueOf (ed.getOID_Veiculo ()) != null && !String.valueOf (ed.getOID_Veiculo ()).equals ("") && !String.valueOf (ed.getOID_Veiculo ()).equals ("null")) {
	          sql += " and Manifestos.OID_Veiculo = '" + ed.getOID_Veiculo () + "'";
	        }


	      sql += " ORDER BY Manifestos.OID_Veiculo, Conhecimentos.NR_Conhecimento ";

	      System.out.println (sql);

	      ResultSet res = null;
	      res = this.executasql.executarConsulta (sql);

	      //popula

	      FormataDataBean DataFormatada = new FormataDataBean ();

	      while (res.next ()) {
	        ConhecimentoED edVolta = new ConhecimentoED ();

	        edVolta.setCD_Unidade (res.getString ("CD_Unidade"));
	        edVolta.setNR_Conhecimento (res.getLong ("NR_Conhecimento"));
	        edVolta.setNM_Serie (res.getString ("NM_Serie"));
	        edVolta.setOID_Veiculo(res.getString ("OID_Veiculo"));
	        edVolta.setOID_Conhecimento (res.getString ("OID_Conhecimento"));

	        edVolta.setDT_Emissao (res.getString ("DT_Emissao"));
	        DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
	        edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());

	        edVolta.setDT_Previsao_Entrega (res.getString ("DT_Previsao_Entrega"));
	        DataFormatada.setDT_FormataData (edVolta.getDT_Previsao_Entrega ());
	        edVolta.setDT_Previsao_Entrega (DataFormatada.getDT_FormataData ());

	        edVolta.setDT_Entrega("");
	        if (res.getString ("DT_Entrega")!=null && res.getString ("DT_Entrega").length()>=4){
		        edVolta.setDT_Entrega (res.getString ("DT_Entrega"));
		        DataFormatada.setDT_FormataData (edVolta.getDT_Entrega ());
		        edVolta.setDT_Entrega (DataFormatada.getDT_FormataData ());
		        edVolta.setTX_Observacao("Entregue");
	        }

	        edVolta.setOID_Veiculo (res.getString ("OID_Veiculo"));
	        edVolta.setDM_Impresso (res.getString ("DM_Impresso"));

	        edVolta.setVL_TOTAL_FRETE (res.getDouble ("vl_total_Frete"));

	        edVolta.setNM_Pessoa_Remetente (res.getString ("NM_Razao_Social_Remetente"));
	        edVolta.setNM_Pessoa_Destinatario (res.getString ("NM_Razao_Social_Destinatario"));

	        boolean lista=true;
	        if ("F".equals(ed.getDM_Situacao_Entrega()) && (edVolta.getDT_Entrega() ==null || edVolta.getDT_Entrega().length()<=4)){
	        	lista=false;
	        }
	        if ("E".equals(ed.getDM_Situacao_Entrega()) && (edVolta.getDT_Entrega() !=null && edVolta.getDT_Entrega().length()>4)){
	        	lista=false;
	        }

	        if (lista) list.add (edVolta);

	      }
	    }
	    catch (SQLException e) {
	      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "lista(ConhecimentoED ed)");
	    }

	    return list;
	  }

  public ArrayList lista_Fora_Intervalo (ConhecimentoED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    ResultSet res = null;
    int ok = 0;
    long NR_Conhecimento_Inicial = ed.getNR_Conhecimento_Inicial ();
    if (ed.getNR_Conhecimento_Final () < NR_Conhecimento_Inicial) {
      ed.setNR_Conhecimento_Final (NR_Conhecimento_Inicial);
    }
    try {
      while (NR_Conhecimento_Inicial <= ed.getNR_Conhecimento_Final ()) {
        ok = 0;
        sql = " SELECT oid_conhecimento FROM Conhecimentos  " +
            " WHERE Conhecimentos.oid_unidade = " + ed.getOID_Unidade () +
            " AND   Conhecimentos.NR_Conhecimento = " + NR_Conhecimento_Inicial;

        res = this.executasql.executarConsulta (sql);
        while (res.next ()) {
          ok++;
        }
        if (ok == 0) {
          ConhecimentoED edVolta = new ConhecimentoED ();
          edVolta.setNR_Conhecimento_Inicial (ed.getNR_Conhecimento_Inicial ());
          edVolta.setNR_Conhecimento_Final (ed.getNR_Conhecimento_Final ());
          edVolta.setNR_Conhecimento (NR_Conhecimento_Inicial);
          list.add (edVolta);
        }
        NR_Conhecimento_Inicial++;
      }

    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "lista(ConhecimentoED ed)");
    }

    return list;
  }


  public ConhecimentoED getByRecord (ConhecimentoED ed) throws Excecoes {
	    boolean tem_item_consulta = false;
	    String sql =
	        " select Conhecimentos.* " +
	        "       ,Conhecimentos.DM_Tipo_Documento " +
	        "       ,Conhecimentos.DM_Retira_Aeroporto " +
	        "       ,Conhecimentos.DM_Tipo_Desconto_Pedagio " +
	        "       ,Conhecimentos.TX_Local_Retirada " +
	        "       ,Conhecimentos.DM_Tipo_Tarifa " +
	        "       ,Conhecimentos.NR_Master " +
	        "       ,Conhecimentos.oid_Unidade_Agente " +
	        "       ,Conhecimentos.DM_Tipo_Tarifa " +
	        "       ,Conhecimentos.NR_Pedido as NR_Pedido_Cto " +
	        "       ,Conhecimentos.CD_CFO_Conhecimento_Editado " +
	        "       ,Conhecimentos.VL_Nota_Fiscal as VL_Nota_Fiscal_CTRC " +
	        "       ,Unidades.DM_Calcula_Frete " +
	        "       ,Modal.DM_Tipo_Tabela_Frete as Tipo_Tabela_Frete" +
	        "       ,Modal.DM_Tipo_Transporte " +
	        "       ,Modal.DM_Tipo_Coleta " +
	        "       ,Modal.DM_Tipo_Entrega " +
	        "       ,Unidades.oid_Empresa " +
	        "       ,Unidades.DM_CTe " +
	        " from  Conhecimentos " +
	        "      ,Modal" +
	        "      ,Unidades " +
	        " where Conhecimentos.oid_unidade = Unidades.oid_unidade " +
	        "   and Conhecimentos.oid_Modal   = Modal.oid_Modal ";

	    if (JavaUtil.doValida (ed.getOID_Conhecimento ())) {
	      sql += " and OID_Conhecimento = '" + ed.getOID_Conhecimento () + "'";
	      tem_item_consulta = true;
	    }
	    else {
	      if (ed.getOID_Unidade () > 0) {
	        sql += " and Conhecimentos.OID_Unidade = " + ed.getOID_Unidade ();
	      }
	      if (ed.getNR_Conhecimento () > 0) {
	        sql += " and Conhecimentos.NR_Conhecimento = " + ed.getNR_Conhecimento ();
	        tem_item_consulta = true;
	      }
	      else {
	        if (ed.getNR_Minuta () > 0) {
	          sql += " and Conhecimentos.NR_Minuta = " + ed.getNR_Minuta ();
	          tem_item_consulta = true;
	        }
	      }
	    }
	    if (!tem_item_consulta) {
	      sql += " and Conhecimentos.NR_Conhecimento = 99999999"; //p/nao fazer uma consulta em todo banco
	    }

	    // // System.out.println (" Sql=>>>>" + sql);

	    ConhecimentoED edVolta = new ConhecimentoED ();
	    try {
	      ResultSet res = this.executasql.executarConsulta (sql);
	      String DM_Calcula_Frete = "S";
	      while (res.next ()) {

	        // // System.out.println (" CTO getByRecord NOVINHO !!!");

	        DM_Calcula_Frete = res.getString ("DM_Calcula_Frete");
	        edVolta.setDM_Impresso (res.getString ("DM_Impresso"));
	        edVolta.setDM_Isento_Seguro (res.getString ("DM_Isento_Seguro"));
	        edVolta.setDM_Tipo_Desconto_Pedagio (res.getString ("DM_Tipo_Desconto_Pedagio"));

	        edVolta.setCD_Rota_Entrega (res.getString ("CD_Rota_Entrega"));

	        edVolta.setDM_Responsavel_Cobranca (res.getString ("DM_Responsavel_Cobranca"));
	        edVolta.setDM_Tipo_Pagamento (res.getString ("DM_Tipo_Pagamento"));

	        edVolta.setDM_Tipo_Conhecimento (res.getString ("DM_Tipo_Conhecimento"));
	        edVolta.setNM_Tipo_Documento ("Despacho");
	        if ("S".equals (res.getString ("DM_Impresso"))) {
	          if ("C".equals (res.getString ("DM_Tipo_Documento"))) {
	            edVolta.setNM_Tipo_Documento ("Conhecimento");
	          }
	          if ("M".equals (res.getString ("DM_Tipo_Documento"))) {
	            edVolta.setNM_Tipo_Documento ("Minuta");
	          }
	          if ("A".equals (res.getString ("DM_Tipo_Documento"))) {
	            edVolta.setNM_Tipo_Documento ("AWB");
	          }
	          if ("N".equals (res.getString ("DM_Tipo_Documento"))) {
	            edVolta.setNM_Tipo_Documento ("NF Servico");
	          }
	        }
	        edVolta.setDM_Tipo_Postagem (res.getString ("DM_Tipo_Postagem"));

//	      /CTE
	        edVolta.setDM_CTe(res.getString ("DM_CTe"));
	        edVolta.setNM_DACTE(res.getString ("NM_DACTE"));

	        edVolta.setNM_CH_CTe(res.getString ("NM_CH_CTe"));
	        edVolta.setDM_Status_CTe(res.getString ("DM_Status_CTe"));
	        edVolta.setNM_Status_CTe(res.getString ("NM_Status_CTe"));
	        edVolta.setDT_Envio_CTe(res.getString ("DT_Envio_CTe"));
	        edVolta.setDT_Recebimento_CTe(res.getString ("DT_Recebimento_CTe"));
	        edVolta.setNM_Protocolo_Envio_CTe(res.getString ("NM_Protocolo_Envio_CTe"));
	        edVolta.setNM_Protocolo_Retorno_CTe(res.getString ("NM_Protocolo_Retorno_CTe"));

	        edVolta.setDM_Status_Cancelamento_CTe(res.getString ("DM_Status_Cancelamento_CTe"));
	        edVolta.setNM_Status_Cancelamento_CTe(res.getString ("NM_Status_Cancelamento_CTe"));
	        edVolta.setDT_Envio_Cancelamento_CTe(res.getString ("DT_Envio_Cancelamento_CTe"));
	        edVolta.setDT_Recebimento_Cancelamento_CTe(res.getString ("DT_Recebimento_Cancelamento_CTe"));
	        edVolta.setNM_Protocolo_Cancelamento_CTe(res.getString ("NM_Protocolo_Cancelamento_CTe"));

	        edVolta.setDM_Status_Anulacao_CTe(res.getString ("DM_Status_Anulacao_CTe"));
	        edVolta.setNM_Status_Anulacao_CTe(res.getString ("NM_Status_Anulacao_CTe"));
	        edVolta.setDT_Envio_Anulacao_CTe(res.getString ("DT_Envio_Anulacao_CTe"));
	        edVolta.setDT_Recebimento_Anulacao_CTe(res.getString ("DT_Recebimento_Anulacao_CTe"));
	        edVolta.setNM_Protocolo_Anulacao_CTe(res.getString ("NM_Protocolo_Anulacao_CTe"));

	        edVolta.setDM_Tipo_Documento (res.getString ("DM_Tipo_Documento"));
	        edVolta.setCD_Roteiro (res.getString ("CD_Roteiro"));
	        edVolta.setDM_Situacao (res.getString ("DM_Situacao"));
	        edVolta.setDT_Emissao (FormataData.formataDataBT (res.getDate ("DT_Emissao")));
	        edVolta.setDT_Coleta (FormataData.formataDataBT (res.getDate ("DT_Coleta")));
	        edVolta.setHR_Coleta(res.getString ("HR_Coleta"));

	        edVolta.setDT_Solicitado_Coleta (FormataData.formataDataBT (res.getDate ("DT_Solicitado_Coleta")));
	        edVolta.setHR_Solicitado_Coleta(res.getString ("HR_Solicitado_Coleta"));
	        edVolta.setDT_Confirmacao_Coleta (FormataData.formataDataBT (res.getDate ("DT_Confirmacao_Coleta")));
	        edVolta.setHR_Confirmacao_Coleta(res.getString ("HR_Confirmacao_Coleta"));
	        edVolta.setDT_Realizacao_Coleta (FormataData.formataDataBT (res.getDate ("DT_Realizacao_Coleta")));
	        edVolta.setHR_Realizacao_Coleta(res.getString ("HR_Realizacao_Coleta"));

	        edVolta.setDT_Programada (FormataData.formataDataBT (res.getDate ("DT_Programada")));
	        edVolta.setDT_Entrega (FormataData.formataDataBT (res.getDate ("DT_Entrega")));
	        edVolta.setHR_Entrega (res.getString ("HR_Entrega"));
	        edVolta.setDT_Previsao_Entrega (FormataData.formataDataBT (res.getDate ("DT_Previsao_Entrega")));
	        edVolta.setHR_Previsao_Entrega (res.getString ("HR_Previsao_Entrega"));
	        edVolta.setNM_Pessoa_Entrega (res.getString ("NM_Pessoa_Entrega"));
	        edVolta.setDM_Tipo_Entrega (res.getString ("DM_Tipo_Entrega"));
	        edVolta.setOID_Comprovante_Entrega (res.getString ("oid_Comprovante_Entrega"));
	        edVolta.setOID_Lote_Faturamento (res.getLong ("oid_Lote_Faturamento"));
	        edVolta.setOID_Participacao_Frete (res.getLong ("oid_Participacao_Frete"));
	        edVolta.setOID_Tipo_Ocorrencia (res.getLong ("oid_Tipo_Ocorrencia"));
	        edVolta.setNR_Dias_Entrega_Realizado (res.getLong ("NR_Dias_Entrega_Realizado"));

	        edVolta.setOid_Programacao_Carga(res.getLong ("Oid_Programacao_Carga"));

	    	edVolta.setOID_Motorista (res.getString("OID_Motorista"));

	        edVolta.setNM_Atendente (res.getString ("NM_Atendente"));
	        edVolta.setNM_Natureza (res.getString ("NM_Natureza"));
	        edVolta.setNR_Lote (res.getString ("NR_Lote"));
	        edVolta.setNM_Serie (res.getString ("NM_Serie"));
	        edVolta.setNM_Liberacao_Veiculo (res.getString ("NM_Liberacao_Veiculo"));

	        edVolta.setNR_Conhecimento (res.getLong ("NR_Conhecimento"));

	        edVolta.setNR_Minuta (res.getLong ("NR_Minuta"));
//	        edVolta.setNR_Recibo (res.getLong ("NR_Recibo"));
	        edVolta.setNR_ACT (res.getLong ("NR_ACT"));
	        edVolta.setNR_Postagem (res.getLong ("NR_Postagem"));
	        edVolta.setNR_AWB (res.getLong ("NR_AWB"));
	        edVolta.setNR_Nota_Fiscal_Servico (res.getLong ("NR_Nota_Fiscal_Servico"));

	        edVolta.setOID_Cidade (res.getLong ("OID_Cidade"));

	        edVolta.setOID_Cidade_Aereo_Origem (res.getLong ("OID_Cidade_Aereo_Origem"));
	        edVolta.setOID_Cidade_Aereo_Destino (res.getLong ("OID_Cidade_Aereo_Destino"));

	        edVolta.setOID_Taxa (res.getLong ("OID_Taxa"));
	        edVolta.setOID_Acerto (res.getLong ("OID_Acerto"));
	        edVolta.setOID_Usuario (res.getLong ("OID_Usuario"));

	        edVolta.setOID_Cidade_Destino (res.getLong ("OID_Cidade_Destino"));
	        edVolta.setOID_Coleta (res.getLong ("OID_Coleta"));
	        edVolta.setOID_Conhecimento (res.getString ("OID_Conhecimento"));

	        /*
	                 edVolta.setOID_Nota_Fiscal (util.getTableStringValue ("oid_Nota_Fiscal" , "Conhecimentos_Notas_Fiscais" ,
	                                                              "Conhecimentos_Notas_Fiscais.oid_Conhecimento = '" + edVolta.getOID_Conhecimento () + "'" +
	                                                              " AND Conhecimentos_Notas_Fiscais.oid_Conhecimento = Conhecimentos.oid_Conhecimento"));
	         */
	        edVolta.setOID_Modal (res.getLong ("OID_Modal"));
	        edVolta.setOID_Centro_Custo (res.getLong ("OID_Centro_Custo"));

	        edVolta.setDM_Tipo_Tabela_Frete (res.getString ("Tipo_Tabela_Frete"));

	        edVolta.setDM_Tipo_Transporte (res.getString ("DM_Tipo_Transporte"));
	        edVolta.setDM_Tipo_Coleta (res.getString ("DM_Tipo_Coleta"));
	        edVolta.setDM_Tipo_Entrega (res.getString ("DM_Tipo_Entrega"));
	        edVolta.setDM_Tipo_Embalagem (res.getString ("DM_Tipo_Embalagem"));
	        edVolta.setOID_Motorista (res.getString ("OID_Motorista"));

	        edVolta.setOID_Pessoa (res.getString ("OID_Pessoa"));
	        edVolta.setOID_Pessoa_Consignatario (res.getString ("OID_Pessoa_Consignatario"));
	        edVolta.setOID_Pessoa_Redespacho (res.getString ("OID_Pessoa_Redespacho"));
	        edVolta.setOID_Pessoa_Pagador (res.getString ("OID_Pessoa_Pagador"));
	        edVolta.setOID_Pessoa_Destinatario (res.getString ("OID_Pessoa_Destinatario"));
	        edVolta.setOID_Produto (res.getLong ("OID_Produto"));
	        edVolta.setOID_Tabela_Frete (res.getString ("OID_Tabela_Frete"));
	        edVolta.setOID_Unidade (res.getLong ("OID_Unidade"));
	        edVolta.setOID_Empresa (res.getLong ("OID_Empresa"));
	        edVolta.setOID_Vendedor (res.getString ("OID_Vendedor"));
	        edVolta.setOID_Veiculo (res.getString ("OID_Veiculo"));
	        edVolta.setOID_Carreta (res.getString ("OID_Carreta"));
	        edVolta.setOID_Carreta2 (res.getString ("OID_Carreta2"));
	        edVolta.setPE_Aliquota_ICMS (res.getDouble ("PE_Aliquota_ICMS"));

	        edVolta.setPE_Carga_Expressa (res.getDouble ("PE_Carga_Expressa"));

	        edVolta.setTX_Observacao (res.getString ("TX_Observacao"));
	        edVolta.setTX_Roteiro (res.getString ("TX_Roteiro"));
	        edVolta.setVL_Nota_Fiscal (res.getDouble ("VL_Nota_Fiscal_CTRC"));

	        edVolta.setVL_Nota_Fiscal_Seguro (res.getDouble ("VL_Nota_Fiscal_Seguro"));

	        edVolta.setNR_Volumes (Valor.truncLong (res.getDouble ("NR_Volumes")));
	        edVolta.setNR_Peso (res.getDouble ("NR_Peso"));
	        edVolta.setNR_Cubagem (res.getDouble ("NR_Cubagem"));
	        edVolta.setNR_Peso_Cubado (res.getDouble ("NR_PESO_CUBADO"));

	        edVolta.setVL_TOTAL_FRETE (res.getDouble ("VL_Total_Frete"));
	        edVolta.setVL_BASE_CALCULO_ICMS (res.getDouble ("vl_base_calculo_icms"));
	        edVolta.setPE_ALIQUOTA_ICMS (res.getDouble ("pe_aliquota_icms"));
	        edVolta.setVL_FRETE_PESO (res.getDouble ("VL_Frete_Peso"));
	        edVolta.setVL_PEDAGIO (res.getDouble ("VL_Pedagio"));
	        edVolta.setVL_Frete (res.getDouble ("VL_Total_Frete"));
	        edVolta.setVL_ICMS (res.getDouble ("VL_Icms"));
	        edVolta.setVL_Custo_Entrega (res.getDouble ("VL_Custo_Entrega"));
	        edVolta.setNM_Lote_Faturamento (res.getString ("NM_Lote_Faturamento"));
	        edVolta.setNR_Transacao_Pedagio (res.getString ("NR_Transacao_Pedagio"));
	        edVolta.setDM_Tipo_Cobranca (res.getString ("DM_Tipo_Cobranca"));
	        edVolta.setDM_Tipo_Desconto_Pedagio (res.getString ("DM_Tipo_Desconto_Pedagio"));
	        edVolta.setPE_Comissao_Acerto (res.getDouble ("PE_Comissao_Acerto"));
	        edVolta.setVL_Base_Comissao_Acerto (res.getDouble ("VL_Base_Comissao_Acerto"));
	        edVolta.setDM_Retira_Aeroporto (res.getString ("DM_Retira_Aeroporto"));
	        edVolta.setTX_Local_Retirada (res.getString ("TX_Local_Retirada"));

	        edVolta.setDM_Tipo_Tarifa (res.getString ("DM_Tipo_Tarifa"));
	        edVolta.setDM_Cia_Aerea (res.getString ("DM_Cia_Aerea"));
	        edVolta.setNR_Master (res.getString ("NR_Master"));
	        edVolta.setOid_Unidade_Agente (res.getInt ("oid_Unidade_Agente"));
	        edVolta.setVL_Frete_Aereo (res.getDouble ("VL_Frete_Aereo"));
	        edVolta.setVL_Taxa_Terrestre_Origem (res.getDouble ("VL_Taxa_Terrestre_Origem"));
	        edVolta.setVL_Nota_Fiscal_Declarado (res.getDouble ("VL_Nota_Fiscal_Declarado"));
	        edVolta.setVL_Taxa_Terrestre_Destino (res.getDouble ("VL_Taxa_Terrestre_Destino"));
	        edVolta.setVL_Taxa_Transportador (res.getDouble ("VL_Taxa_Transportador"));
	        edVolta.setVL_Master (res.getDouble ("VL_Master"));
	        edVolta.setDM_Tipo_Tarifa (res.getString ("DM_Tipo_Tarifa"));
	        edVolta.setCD_CFO_Conhecimento_Editado (res.getString ("CD_CFO_Conhecimento_Editado"));
	        // // System.out.println (" CTO getByRecord 1");

	        edVolta.setCD_CFO (res.getString ("CD_CFO_Conhecimento"));
	        edVolta.setPE_Aliquota_ICMS (res.getDouble ("PE_Aliquota_ICMS"));

	        // // System.out.println (" CTO getByRecord NOVO( calcula_frete)");

	        if ("S".equals (DM_Calcula_Frete) && "N".equals (edVolta.getDM_Impresso ())) {
	          // // System.out.println (" CTO getByRecord vai  calcula_frete=>> " + edVolta.getDM_Tipo_Documento ());
	          if ("N".equals (edVolta.getDM_Tipo_Documento ())) {
	          }
	          else {
	          }

	        }

	        edVolta.setNM_Especie (res.getString ("NM_Especie"));
	        edVolta.setNR_Pedido(res.getString ("NR_Pedido_Cto"));

	        edVolta.setNM_Natureza_AWB (res.getString ("NM_Natureza_AWB"));
	        edVolta.setNM_Especie_AWB (res.getString ("NM_Especie_AWB"));
	        edVolta.setNR_Volumes_AWB (Valor.truncLong (res.getDouble ("NR_Volumes_AWB")));
	        edVolta.setNR_Peso_AWB (res.getDouble ("NR_Peso_AWB"));
	        edVolta.setVL_Tarifa_AWB (res.getDouble ("VL_Tarifa_AWB"));
	        edVolta.setTX_Observacao_AWB (res.getString ("TX_Observacao_AWB"));
	        edVolta.setTX_Observacao_AWB2 (res.getString ("TX_Observacao_AWB2"));
	        edVolta.setTX_Observacao_AWB3 (res.getString ("TX_Observacao_AWB3"));
	        edVolta.setTX_Observacao_AWB4 (res.getString ("TX_Observacao_AWB4"));
	        edVolta.setTX_Observacao_AWB5 (res.getString ("TX_Observacao_AWB5"));
	        edVolta.setTX_Observacao_AWB6 (res.getString ("TX_Observacao_AWB6"));
	        edVolta.setVL_Frete_Valor_AWB (res.getDouble ("VL_Frete_Valor_AWB"));
	        // // System.out.println (" CTO getByRecord 10");

	        edVolta = consultaSituacao (edVolta);

	        if (res.getLong("oid_Usuario")>0) {
	          sql = " SELECT NM_Usuario FROM Usuarios WHERE oid_Usuario='" + res.getLong("oid_Usuario")+ "'";
	          ResultSet res2 = this.executasql.executarConsulta (sql);
	          while (res2.next ()) {
	            edVolta.setNM_Usuario (res2.getString ("NM_Usuario"));
	          }
	        }

	        if (res.getString("oid_Pessoa_Entregadora") != null && res.getString("oid_Pessoa_Entregadora").length()>4) {
	          sql =" SELECT NM_Razao_Social FROM Pessoas WHERE oid_Pessoa='" +res.getString("oid_Pessoa_Entregadora") + "'";
	          ResultSet res2 = this.executasql.executarConsulta (sql);
	          if (res2.next ()) {
		          edVolta.setOID_Pessoa_Entregadora (res.getString ("OID_Pessoa_Entregadora"));
	        	  edVolta.setNM_Pessoa_Entregadora(res2.getString("NM_Razao_Social"));
		          edVolta.setNR_Conhecimento_Entregadora(res.getString ("NR_Conhecimento_Entregadora"));
		          edVolta.setDT_Previsao_Entregadora (FormataData.formataDataBT (res.getDate ("DT_Previsao_Entregadora")));
		          edVolta.setVL_Frete_Entregadora (res.getDouble ("VL_Frete_Entregadora"));
	          }
	        }

	        if ("Redespacho".equals (ed.getDM_Calcular ())) {

	            // // System.out.println (" CTO getByRecord calcula redespacho ");
	            if (ed.getOID_Pessoa_Entregadora ()!=null && ed.getOID_Pessoa_Entregadora ().length()>4) {
	            	edVolta.setOID_Pessoa_Entregadora (ed.getOID_Pessoa_Entregadora ());
	            }
	            // // System.out.println (" CTO getByRecord volta redespacho= " + edVolta.getVL_Total_Redespacho ());

	          }



	        // // System.out.println (" CTO RETORNANDO calc");

	      }
	    }
	    catch (SQLException exc) {
	      throw new Excecoes (exc.getMessage () , exc ,
	                          this.getClass ().getName () ,
	                          "getByRecord()");
	    }
	    return edVolta;
	  }

  public ConhecimentoED getByAWB (ConhecimentoED ed) throws Excecoes {
    String sql =
        " SELECT Conhecimentos.oid_Conhecimento " +
        " FROM   Conhecimentos " +
        " WHERE  Conhecimentos.NR_AWB = " + ed.getNR_AWB ();

    // System.out.println (" CTO getByAWB 30-" + sql);

    ConhecimentoED edVolta = new ConhecimentoED ();
    try {
      ResultSet res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta.setOID_Conhecimento (res.getString ("OID_Conhecimento"));

        edVolta = this.getByRecord (edVolta);

        // System.out.println (" CTO getByAWB 30-");

      }
    }
    catch (SQLException exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "getByAWB()");
    }
    return edVolta;
  }

  public ConhecimentoED getByControle_Entrega (ConhecimentoED ed) throws Excecoes {

    // System.out.println (" CTO getByControle_Entrega ->> " + ed.getNR_Controle_Entrega ());

    String cd_Unidade = ed.getNR_Controle_Entrega ().substring (0 , 3);
    String nr_Conhecimento = ed.getNR_Controle_Entrega ().substring (4 , 10);
    String nr_Dv = ed.getNR_Controle_Entrega ().substring (11 , 14);

    // System.out.println (" CTO cd_Unidade ->> " + cd_Unidade);
    // System.out.println (" CTO nr_Conhecimento ->> " + nr_Conhecimento);
    // System.out.println (" CTO nr_Dv ->> " + nr_Dv);

    String sql =
        " SELECT Conhecimentos.oid_Conhecimento " +
        " FROM   Conhecimentos, Unidades " +
        " WHERE  Conhecimentos.oid_Unidade = Unidades.oid_Unidade " +
        " AND    Unidades.cd_Unidade = '" + cd_Unidade + "'" +
        " AND    Conhecimentos.NR_Conhecimento = '" + nr_Conhecimento + "'" +
        " AND    Conhecimentos.nr_Dv = '" + nr_Dv + "'";

    // System.out.println (" CTO getByControle_Entrega 30-" + sql);

    ConhecimentoED edVolta = new ConhecimentoED ();
    try {
      ResultSet res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta.setOID_Conhecimento (res.getString ("OID_Conhecimento"));

        edVolta = this.getByRecord (edVolta);

        // System.out.println (" CTO getByAWB 30-");

      }
    }
    catch (SQLException exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "getByAWB()");
    }
    return edVolta;
  }

  public ConhecimentoED getByPostagem (ConhecimentoED ed) throws Excecoes {
    String sql =
        " SELECT Conhecimentos.oid_Conhecimento " +
        " FROM   Conhecimentos " +
        " WHERE  Conhecimentos.NR_Postagem = " + ed.getNR_Postagem ();

    // System.out.println (" CTO getByPostagem " + sql);

    ConhecimentoED edVolta = new ConhecimentoED ();
    try {
      ResultSet res = this.executasql.executarConsulta (sql);
      if (res.next ()) {
        ed.setOID_Conhecimento (res.getString ("OID_Conhecimento"));

        edVolta = this.getByRecord (ed);

        // System.out.println (" CTO getByPostagem NR CTO>" + edVolta.getNR_Conhecimento ());

      }
    }
    catch (SQLException exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "getByAWB()");
    }
    return edVolta;
  }

  public ConhecimentoED getByRemessa (ConhecimentoED ed) throws Excecoes {
    String sql =
        " SELECT Conhecimentos.oid_Conhecimento " +
        " FROM   Conhecimentos " +
        " WHERE  Conhecimentos.NR_Remessa = '" + ed.getNR_Postagem () + "'";

    // System.out.println (" CTO getByPostagem " + sql);

    ConhecimentoED edVolta = new ConhecimentoED ();
    try {
      ResultSet res = this.executasql.executarConsulta (sql);
      if (res.next ()) {
        ed.setOID_Conhecimento (res.getString ("OID_Conhecimento"));

        edVolta = this.getByRecord (ed);

        // System.out.println (" CTO getByRemessa NR CTO>" + edVolta.getNR_Conhecimento ());

      }
    }
    catch (SQLException exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "getByAWB()");
    }
    return edVolta;
  }

  private ConhecimentoED consultaSituacao (ConhecimentoED ed) {

    ed.setNM_Situacao ("---");
    if ("G".equals (ed.getDM_Situacao ())) {
      ed.setNM_Situacao ("Gerado");
    }
    if ("C".equals (ed.getDM_Situacao ())) {
      ed.setNM_Situacao ("Cancelado");
    }
    if ("Q".equals (ed.getDM_Situacao ())) {
      ed.setNM_Situacao ("Quitado");
    }
    if ("F".equals (ed.getDM_Situacao ())) {
      ed.setNM_Situacao ("Faturado");
    }

    return ed;
  }

  public byte[] geraConhecimentoEntrega (ConhecimentoED ed) throws Excecoes {
    String sql =
        " select Conhecimentos.nm_razao_social_pagador,Conhecimentos.cd_rota_entrega, Conhecimentos.DT_Emissao, Conhecimentos.NR_Conhecimento, Conhecimentos.OID_Conhecimento, Conhecimentos.nr_volumes, Conhecimentos.VL_NOTA_FISCAL, Conhecimentos.dm_tipo_pagamento, Conhecimentos.nr_duplicata, Conhecimentos.vl_total_frete, Conhecimentos.Dt_Entrega, Conhecimentos.Hr_Entrega , Conhecimentos.Dt_Previsao_Entrega, Conhecimentos.Hr_Previsao_Entrega,  Conhecimentos.nm_pessoa_entrega,"
        + " conhecimentos.nr_peso, Conhecimentos.oid_pessoa, Conhecimentos.oid_modal, Conhecimentos.oid_Pessoa_Destinatario,  conhecimentos.nr_peso_cubado, Unidades.CD_Unidade "
        + " from Conhecimentos, Unidades "
        + " where Unidades.oid_Unidade = Conhecimentos.oid_Unidade "
        + " AND Conhecimentos.DM_Situacao <> 'C' "
        + " AND Conhecimentos.DM_Impresso = 'S' "
        + " AND Conhecimentos.VL_Total_Frete > 0";

//        // System.out.println("bd x - " + ed.getOID_Unidade());

    if (String.valueOf (ed.getOID_Unidade ()) != null && !String.valueOf (ed.getOID_Unidade ()).equals ("0")) {
      sql += " and Conhecimentos.OID_Unidade = " + ed.getOID_Unidade ();
    }

//        // System.out.println("bd 0 - " + ed.getDM_Tipo_Remessa());

    if (ed.getDM_Tipo_Remessa ().equals ("E") && String.valueOf (ed.getOID_Pessoa ()) != null && !String.valueOf (ed.getOID_Pessoa ()).equals ("null") && !String.valueOf (ed.getOID_Pessoa ()).equals ("")) {
      sql += " and Conhecimentos.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
    }
    if (ed.getDM_Tipo_Remessa ().equals ("R") && String.valueOf (ed.getOID_Pessoa ()) != null && !String.valueOf (ed.getOID_Pessoa ()).equals ("null") && !String.valueOf (ed.getOID_Pessoa ()).equals ("")) {
      sql += " and Conhecimentos.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa () + "'";
    }
    if (ed.getDM_Tipo_Remessa ().equals ("P") && String.valueOf (ed.getOID_Pessoa ()) != null && !String.valueOf (ed.getOID_Pessoa ()).equals ("null") && !String.valueOf (ed.getOID_Pessoa ()).equals ("")) {
      sql += " and Conhecimentos.OID_Pessoa_Pagador = '" + ed.getOID_Pessoa () + "'";
    }
    if (ed.getDM_Tipo_Remessa ().equals ("T") && String.valueOf (ed.getOID_Pessoa ()) != null && !String.valueOf (ed.getOID_Pessoa ()).equals ("null") && !String.valueOf (ed.getOID_Pessoa ()).equals ("")) {
      sql += " and ( Conhecimentos.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa () + "'";
      sql += "   OR Conhecimentos.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
      sql += "   OR Conhecimentos.OID_Pessoa_Pagador = '" + ed.getOID_Pessoa () + "')";
    }

//        // System.out.println("bd 1 - " + ed.getDM_Frete_Emitido());

//        // System.out.println("bd 2 - " + ed.getDM_Frete_Recebido());

    if (ed.getDM_Frete_Emitido ().equals ("C") && String.valueOf (ed.getOID_Pessoa ()) != null && !String.valueOf (ed.getOID_Pessoa ()).equals ("null") && !String.valueOf (ed.getOID_Pessoa ()).equals ("")) {
      sql += " and Conhecimentos.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
      sql += " and Conhecimentos.DM_TIPO_PAGAMENTO = '1'";
    }

    if (ed.getDM_Frete_Emitido ().equals ("F") && String.valueOf (ed.getOID_Pessoa ()) != null && !String.valueOf (ed.getOID_Pessoa ()).equals ("null") && !String.valueOf (ed.getOID_Pessoa ()).equals ("")) {
      sql += " and Conhecimentos.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
      sql += " and Conhecimentos.DM_TIPO_PAGAMENTO <> '1'";
    }

    if (ed.getDM_Frete_Recebido ().equals ("C") && String.valueOf (ed.getOID_Pessoa ()) != null && !String.valueOf (ed.getOID_Pessoa ()).equals ("null") && !String.valueOf (ed.getOID_Pessoa ()).equals ("")) {
      sql += " and Conhecimentos.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa () + "'";
      sql += " and Conhecimentos.DM_TIPO_PAGAMENTO = '1'";
    }

    if (ed.getDM_Frete_Recebido ().equals ("F") && String.valueOf (ed.getOID_Pessoa ()) != null && !String.valueOf (ed.getOID_Pessoa ()).equals ("null") && !String.valueOf (ed.getOID_Pessoa ()).equals ("")) {
      sql += " and Conhecimentos.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa () + "'";
      sql += " and Conhecimentos.DM_TIPO_PAGAMENTO <> '1'";
    }

    if (String.valueOf (ed.getDt_Emissao_Inicial ()) != null && !String.valueOf (ed.getDt_Emissao_Inicial ()).equals ("") && !String.valueOf (ed.getDt_Emissao_Inicial ()).equals ("null")) {
      sql += " and Conhecimentos.DT_Emissao >= '" + ed.getDt_Emissao_Inicial () + "'";
    }

    if (String.valueOf (ed.getDt_Emissao_Final ()) != null && !String.valueOf (ed.getDt_Emissao_Final ()).equals ("") && !String.valueOf (ed.getDt_Emissao_Final ()).equals ("null")) {
      sql += " and Conhecimentos.DT_Emissao <= '" + ed.getDt_Emissao_Final () + "'";
    }

    if (String.valueOf (ed.getDm_Situacao_Cobranca ()) != null && !String.valueOf (ed.getDm_Situacao_Cobranca ()).equals ("null") && !String.valueOf (ed.getDm_Situacao_Cobranca ()).equals ("T")
        && !String.valueOf (ed.getDm_Situacao_Cobranca ()).equals ("")) {
      sql += " and Conhecimentos.DM_Situacao = '" + ed.getDm_Situacao_Cobranca () + "'";
    }

    if (String.valueOf (ed.getDM_Situacao_Entrega ()) != null && !String.valueOf (ed.getDM_Situacao_Entrega ()).equals ("null") && String.valueOf (ed.getDM_Situacao_Entrega ()).equals ("E")
        && !String.valueOf (ed.getDM_Situacao_Entrega ()).equals ("")) {
      sql += " and Conhecimentos.DT_Entrega is not null";
    }

    if (String.valueOf (ed.getDM_Situacao_Entrega ()) != null && !String.valueOf (ed.getDM_Situacao_Entrega ()).equals ("null") && String.valueOf (ed.getDM_Situacao_Entrega ()).equals ("P")
        && !String.valueOf (ed.getDM_Situacao_Entrega ()).equals ("")) {
      sql += " and Conhecimentos.DT_Entrega is null";
    }

    if (String.valueOf (ed.getDM_Tipo_Embarque ()) != null && !String.valueOf (ed.getDM_Tipo_Embarque ()).equals ("null") && String.valueOf (ed.getDM_Tipo_Embarque ()).equals ("F")) {
      sql += " and Conhecimentos.NR_Peso <= 7500 ";
    }

    if (String.valueOf (ed.getDM_Tipo_Embarque ()) != null && !String.valueOf (ed.getDM_Tipo_Embarque ()).equals ("null") && String.valueOf (ed.getDM_Tipo_Embarque ()).equals ("C")) {
      sql += " and Conhecimentos.NR_Peso > 7500 ";
    }

    sql += " order by  conhecimentos.dt_emissao, conhecimentos.nr_conhecimento ";

//        // System.out.println("ED ->> " + ed.getDM_Tipo_Remessa());
    // System.out.println (sql);
    ConhecimentoED edVolta = new ConhecimentoED ();

    ResultSet res = this.executasql.executarConsulta (sql.toString ());

    return new ConhecimentoRL ().geraConhecimentoEntrega (res , ed);
  }

  public byte[] geraRelConhecimentoProduto (ConhecimentoED ed) throws Excecoes {

    String sql = null;
    byte[] b = null;

    sql = " select Conhecimentos.DT_Emissao, Conhecimentos.NR_Conhecimento, Conhecimentos.OID_Conhecimento, Conhecimentos.nr_volumes, Conhecimentos.dm_tipo_pagamento, Conhecimentos.nr_duplicata, Conhecimentos.vl_total_frete, Conhecimentos.Dt_Entrega, Conhecimentos.Hr_Entrega, "
        + " conhecimentos.nr_peso, Unidades.CD_Unidade, Cidades.NM_Cidade, "
        + " Pessoa_Remetente.NM_Razao_Social    as NM_Razao_Social_Remetente,"
        + " Pessoa_Unidade.NM_Fantasia          as NM_Fantasia            ,"
        + " Pessoa_Pagador.NM_Razao_Social      as NM_Razao_Social_Pagador,"
        + " Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario, "
        + " Itens_Notas_Fiscais.VL_PRODUTO, PRODUTOS.NM_Produto "
        + " from Conhecimentos, Unidades, Cidades, Notas_Fiscais, Conhecimentos_Notas_Fiscais, "
        + " Itens_Notas_Fiscais, Referencias, PRODUTOS, "
        + " Pessoas Pessoa_Remetente, Pessoas Pessoa_Pagador, Pessoas Pessoa_Unidade, Pessoas Pessoa_Destinatario "
        + " where Unidades.oid_Unidade = Conhecimentos.oid_Unidade "
        + " AND Unidades.oid_pessoa = Pessoa_Unidade.oid_Pessoa "
        + " AND Pessoa_Unidade.oid_Cidade = Cidades.oid_cidade "
        + " AND Conhecimentos.DM_Impresso = 'S' "
        + " AND Conhecimentos.VL_Total_Frete > 0"
        + " AND Conhecimentos.oid_Pessoa_Pagador = Pessoa_Pagador.oid_Pessoa "
        + " AND Conhecimentos.oid_Pessoa = Pessoa_Remetente.oid_Pessoa "
        + " AND Conhecimentos.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa "
        + " AND Notas_Fiscais.OID_Nota_Fiscal = Conhecimentos_Notas_Fiscais.OID_Nota_Fiscal "
        + " AND Conhecimentos_Notas_Fiscais.OID_Conhecimento = Conhecimentos.OID_Conhecimento "
        + " AND Notas_Fiscais.OID_Nota_Fiscal = Itens_Notas_Fiscais.OID_Nota_Fiscal "
        + " AND Referencias.CD_REFERENCIA = Itens_Notas_Fiscais.CD_REFERENCIA " + " AND Referencias.OID_PRODUTO = PRODUTOS.OID_PRODUTO ";

    if (String.valueOf (ed.getNR_Conhecimento ()) != null && !String.valueOf (ed.getNR_Conhecimento ()).equals ("0")) {
      sql += " and Conhecimentos.NR_Conhecimento = " + ed.getNR_Conhecimento ();
    }
    if (String.valueOf (ed.getOID_Unidade ()) != null && !String.valueOf (ed.getOID_Unidade ()).equals ("0")) {
      sql += " and Conhecimentos.OID_Unidade = " + ed.getOID_Unidade ();
    }
    if (String.valueOf (ed.getOID_Pessoa ()) != null && !String.valueOf (ed.getOID_Pessoa ()).equals ("")) {
      sql += " and Conhecimentos.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
    }
    if (String.valueOf (ed.getOID_Pessoa_Destinatario ()) != null && !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("")) {
      sql += " and Conhecimentos.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario () + "'";
    }
    if (String.valueOf (ed.getOID_Pessoa_Consignatario ()) != null && !String.valueOf (ed.getOID_Pessoa_Consignatario ()).equals ("")) {
      sql += " and Conhecimentos.OID_Pessoa_Consignatario = '" + ed.getOID_Pessoa_Consignatario () + "'";
    }
    if (String.valueOf (ed.getOID_Pessoa_Pagador ()) != null && !String.valueOf (ed.getOID_Pessoa_Pagador ()).equals ("") && !String.valueOf (ed.getOID_Pessoa_Pagador ()).equals ("null")) {
      sql += " and Conhecimentos.OID_Pessoa_Pagador = '" + ed.getOID_Pessoa_Pagador () + "'";
    }
    if (String.valueOf (ed.getDt_Emissao_Inicial ()) != null && !String.valueOf (ed.getDt_Emissao_Inicial ()).equals ("") && !String.valueOf (ed.getDt_Emissao_Inicial ()).equals ("null")) {
      sql += " and Conhecimentos.DT_Emissao >= '" + ed.getDt_Emissao_Inicial () + "'";
    }
    if (String.valueOf (ed.getDt_Emissao_Final ()) != null && !String.valueOf (ed.getDt_Emissao_Final ()).equals ("") && !String.valueOf (ed.getDt_Emissao_Final ()).equals ("null")) {
      sql += " and Conhecimentos.DT_Emissao <= '" + ed.getDt_Emissao_Final () + "'";
    }

    if (String.valueOf (ed.getDm_Situacao_Cobranca ()) != null && !String.valueOf (ed.getDm_Situacao_Cobranca ()).equals ("null") && !String.valueOf (ed.getDm_Situacao_Cobranca ()).equals ("T")
        && !String.valueOf (ed.getDm_Situacao_Cobranca ()).equals ("")) {
      sql += " and Conhecimentos.DM_Situacao = '" + ed.getDm_Situacao_Cobranca () + "'";
    }

    if (String.valueOf (ed.getDM_Tipo_Produto ()) != null && !String.valueOf (ed.getDM_Tipo_Produto ()).equals ("null") && !String.valueOf (ed.getDM_Tipo_Produto ()).equals ("")) {
      sql += " and Referencias.DM_TIPO_PRODUTO = '" + ed.getDM_Tipo_Produto () + "'";
    }

    sql += " order by unidades.cd_unidade, conhecimentos.dt_emissao, conhecimentos.nr_conhecimento ";

    ConhecimentoED edVolta = new ConhecimentoED ();

    try {

      //// // System.out.println(sql);

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql.toString ());

      ConhecimentoRL conRL = new ConhecimentoRL ();
      b = conRL.geraRelConhecProduto (res);
    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro no m�todo listar");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("geraRelatorio(ConhecimentoED ed)");
    }
    return b;
  }

  public byte[] geraConhecimentoFatura (ConhecimentoED ed) throws Excecoes {

    byte[] b = null;

    String sql =
        " select Conhecimentos.DT_Emissao, " +
        "        Conhecimentos.NR_Conhecimento, " +
        "        Conhecimentos.OID_Conhecimento, " +
        "        Conhecimentos.nr_volumes, " +
        "        Conhecimentos.dm_tipo_pagamento, " +
        "        Conhecimentos.nr_duplicata, " +
        "        Conhecimentos.vl_total_frete, " +
        "        Conhecimentos.nr_peso, " +
        "        Conhecimentos.DM_Tipo_Cobranca, " +
        "        Unidades.CD_Unidade, " +
        "        Cidades.NM_Cidade, " +
        "        Pessoa_Remetente.NM_Razao_Social as NM_Razao_Social_Remetente, " +
        "        Pessoa_Unidade.NM_Fantasia as NM_Fantasia, " +
        "        Pessoa_Pagador.NM_Razao_Social as NM_Razao_Social_Pagador, " +
        "        Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario " +
        " from Conhecimentos, " +
        "      Unidades, " +
        "      Cidades, " +
        "      Pessoas Pessoa_Remetente, " +
        "      Pessoas Pessoa_Pagador, " +
        "      Pessoas Pessoa_Unidade, " +
        "      Pessoas Pessoa_Destinatario " +
        " where Unidades.oid_Unidade = Conhecimentos.oid_Unidade "
        + " AND Unidades.oid_pessoa = Pessoa_Unidade.oid_Pessoa "
        + " AND Pessoa_Unidade.oid_Cidade = Cidades.oid_cidade "
        + " AND Conhecimentos.oid_Pessoa_Pagador = Pessoa_Pagador.oid_Pessoa "
        + " AND (Conhecimentos.DM_Situacao = 'G' "
        + " or Conhecimentos.DM_Situacao = 'F')"
        + " AND Conhecimentos.DM_Impresso = 'S' "
        + " AND Conhecimentos.VL_Total_Frete > 0"
        + " AND Conhecimentos.oid_Pessoa = Pessoa_Remetente.oid_Pessoa "
        + " AND Conhecimentos.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa ";

    if (String.valueOf (ed.getNR_Conhecimento ()) != null && !String.valueOf (ed.getNR_Conhecimento ()).equals ("0")) {
      sql += " and Conhecimentos.NR_Conhecimento = " + ed.getNR_Conhecimento ();
    }
    if (String.valueOf (ed.getOID_Unidade ()) != null && !String.valueOf (ed.getOID_Unidade ()).equals ("0")) {
      sql += " and Conhecimentos.OID_Unidade = " + ed.getOID_Unidade ();
    }
    if (String.valueOf (ed.getOID_Pessoa ()) != null && !String.valueOf (ed.getOID_Pessoa ()).equals ("")) {
      sql += " and Conhecimentos.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
    }
    if (String.valueOf (ed.getOID_Pessoa_Destinatario ()) != null && !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("")) {
      sql += " and Conhecimentos.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario () + "'";
    }
    if (String.valueOf (ed.getOID_Pessoa_Consignatario ()) != null && !String.valueOf (ed.getOID_Pessoa_Consignatario ()).equals ("")) {
      sql += " and Conhecimentos.OID_Pessoa_Consignatario = '" + ed.getOID_Pessoa_Consignatario () + "'";
    }
    if (String.valueOf (ed.getOID_Pessoa_Pagador ()) != null && !String.valueOf (ed.getOID_Pessoa_Pagador ()).equals ("") && !String.valueOf (ed.getOID_Pessoa_Pagador ()).equals ("null")) {
      sql += " and Conhecimentos.OID_Pessoa_Pagador = '" + ed.getOID_Pessoa_Pagador () + "'";
    }
    if (String.valueOf (ed.getDt_Emissao_Inicial ()) != null && !String.valueOf (ed.getDt_Emissao_Inicial ()).equals ("") && !String.valueOf (ed.getDt_Emissao_Inicial ()).equals ("null")) {
      sql += " and Conhecimentos.DT_Emissao >= '" + ed.getDt_Emissao_Inicial () + "'";
    }
    if (String.valueOf (ed.getDt_Emissao_Final ()) != null && !String.valueOf (ed.getDt_Emissao_Final ()).equals ("") && !String.valueOf (ed.getDt_Emissao_Final ()).equals ("null")) {
      sql += " and Conhecimentos.DT_Emissao <= '" + ed.getDt_Emissao_Final () + "'";
    }

    if (String.valueOf (ed.getDm_Situacao_Cobranca ()) != null && !String.valueOf (ed.getDm_Situacao_Cobranca ()).equals ("null") && !String.valueOf (ed.getDm_Situacao_Cobranca ()).equals ("T")
        && !String.valueOf (ed.getDm_Situacao_Cobranca ()).equals ("")) {
      sql += " and Conhecimentos.DM_Situacao = '" + ed.getDm_Situacao_Cobranca () + "'";
    }

    if (String.valueOf (ed.getDM_Tipo_Pagamento ()) != null && !String.valueOf (ed.getDM_Tipo_Pagamento ()).equals ("null") && String.valueOf (ed.getDM_Tipo_Pagamento ()).equals ("C")
        && !String.valueOf (ed.getDM_Tipo_Pagamento ()).equals ("")) {
      sql += " and Conhecimentos.DM_Tipo_Pagamento = '1'";
    }

    if (String.valueOf (ed.getDM_Tipo_Pagamento ()) != null && !String.valueOf (ed.getDM_Tipo_Pagamento ()).equals ("null") && String.valueOf (ed.getDM_Tipo_Pagamento ()).equals ("F")
        && !String.valueOf (ed.getDM_Tipo_Pagamento ()).equals ("")) {
      sql += " and Conhecimentos.DM_Tipo_Pagamento <> '1'";
    }

    if (String.valueOf (ed.getDM_Tipo_Conhecimento ()) != null && !String.valueOf (ed.getDM_Tipo_Conhecimento ()).equals ("null") && !String.valueOf (ed.getDM_Tipo_Conhecimento ()).equals ("T")
        && !String.valueOf (ed.getDM_Tipo_Conhecimento ()).equals ("")) {
      sql += " and Conhecimentos.DM_Tipo_Conhecimento = '" + ed.getDM_Tipo_Conhecimento () + "'";
    }
    if (util.doValida (ed.getDM_Tipo_Cobranca ())) {
      sql += " and Conhecimentos.DM_Tipo_Cobranca = " + util.getSQLString (ed.getDM_Tipo_Cobranca ());
    }

    sql += " order by unidades.cd_unidade, Pessoa_Pagador.NM_Razao_Social, conhecimentos.nr_conhecimento ";

    ConhecimentoED edVolta = new ConhecimentoED ();

    try {

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql.toString ());

      ConhecimentoRL conRL = new ConhecimentoRL ();
      b = conRL.geraConhecimentoFatura (res , ed);

    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro no m�todo listar");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("geraRelatorio(ConhecimentoED ed)");
    }
    return b;
  }

  public byte[] geraRelConhecTransRodCarga2 (ConhecimentoED ed) throws Excecoes {

    String sql = null;
    byte[] b = null;
    long valOid = 0;
    long NR_Proximo = 0;
    long NR_Final = 0;

    try {

      sql = "Select * " + " from " + " Conhecimentos " + " where " + " Conhecimentos.DM_Impresso = 'N' and " + " Conhecimentos.VL_Total_Frete > 0";

      if (String.valueOf (ed.getNR_Conhecimento ()) != null && !String.valueOf (ed.getNR_Conhecimento ()).equals ("0")) {
        sql += " and Conhecimentos.NR_Conhecimento = " + ed.getNR_Conhecimento ();
      }

      if (String.valueOf (ed.getOID_Unidade ()) != null && !String.valueOf (ed.getOID_Unidade ()).equals ("0")) {
        sql += " and Conhecimentos.OID_Unidade = " + ed.getOID_Unidade ();
      }
      if (String.valueOf (ed.getOID_Pessoa ()) != null && !String.valueOf (ed.getOID_Pessoa ()).equals ("") && !String.valueOf (ed.getOID_Pessoa ()).equals ("null")) {
        sql += " and Conhecimentos.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
      }
      if (String.valueOf (ed.getOID_Pessoa_Destinatario ()) != null && !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("")
          && !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("null")) {
        sql += " and Conhecimentos.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario () + "'";
      }
      if (String.valueOf (ed.getOID_Pessoa_Consignatario ()) != null && !String.valueOf (ed.getOID_Pessoa_Consignatario ()).equals ("")
          && !String.valueOf (ed.getOID_Pessoa_Consignatario ()).equals ("null")) {
        sql += " and Conhecimentos.OID_Pessoa_Consignatario = '" + ed.getOID_Pessoa_Consignatario () + "'";
      }
      if (String.valueOf (ed.getDT_Emissao ()) != null && !String.valueOf (ed.getDT_Emissao ()).equals ("") && !String.valueOf (ed.getDT_Emissao ()).equals ("null")) {
        sql += " and Conhecimentos.DT_Emissao = '" + ed.getDT_Emissao () + "'";
      }

      ResultSet resCTRC = null;
      resCTRC = this.executasql.executarConsulta (sql);

      while (resCTRC.next ()) {

        ed.setOID_Unidade (resCTRC.getLong ("OID_Unidade"));
        ed.setOID_Conhecimento (resCTRC.getString ("oid_Conhecimento"));

        sql = " SELECT AIDOF.NR_Proximo, AIDOF.NR_FINAL, AIDOF.OID_AIDOF, AIDOF.NM_Serie ";
        sql += " FROM Parametros_Filiais, AIDOF ";
        sql += " WHERE Parametros_Filiais.OID_AIDOF = AIDOF.OID_AIDOF ";
        sql += " AND Parametros_Filiais.OID_Unidade = " + ed.getOID_Unidade ();

        ResultSet resTP = null;
        resTP = this.executasql.executarConsulta (sql.toString ());

        while (resTP.next ()) {
          ed.setNM_Serie (resTP.getString ("NM_Serie"));
          ed.setNR_Conhecimento (resTP.getLong ("NR_Proximo"));
          valOid = resTP.getLong ("OID_AIDOF");
          NR_Proximo = resTP.getLong ("NR_Proximo") + 1;
          NR_Final = resTP.getLong ("NR_FINAL");
        }

        if (NR_Proximo > NR_Final) {
          Excecoes exc = new Excecoes ();
          throw exc;
        }

        sql = " UPDATE AIDOF SET NR_Proximo= " + (ed.getNR_Conhecimento () + 1);
        sql += " WHERE OID_AIDOF = " + valOid;

        int u = executasql.executarUpdate (sql);

        sql = " update Conhecimentos set " + " NR_Conhecimento = " + ed.getNR_Conhecimento () + ", " + " NM_Serie = '" + ed.getNM_Serie () + "'";
        sql += " where Conhecimentos.oid_Conhecimento = '" + ed.getOID_Conhecimento () + "'";

        u = executasql.executarUpdate (sql);

        Calcula_FreteBD = new Calcula_FreteBD (this.executasql);
        Calcula_FreteED edCalcula_Frete = new Calcula_FreteED ();

        FormataDataBean DataFormatada = new FormataDataBean ();

        edCalcula_Frete.setDM_Tipo_Pagamento (resCTRC.getString ("DM_Tipo_Pagamento"));
        edCalcula_Frete.setDT_Emissao (resCTRC.getString ("DT_Emissao"));
        DataFormatada.setDT_FormataData (edCalcula_Frete.getDT_Emissao ());
        edCalcula_Frete.setDT_Emissao (DataFormatada.getDT_FormataData ());
        edCalcula_Frete.setOID_Modal (resCTRC.getLong ("OID_Modal"));
        edCalcula_Frete.setOID_Pessoa (resCTRC.getString ("OID_Pessoa"));
        edCalcula_Frete.setOID_Pessoa_Consignatario (resCTRC.getString ("OID_Pessoa_Consignatario"));
        edCalcula_Frete.setOID_Pessoa_Pagador (resCTRC.getString ("OID_Pessoa_Pagador"));
        edCalcula_Frete.setOID_Pessoa_Pagador_Tabela (resCTRC.getString ("OID_Pessoa_Pagador"));
        edCalcula_Frete.setOID_Pessoa_Destinatario (resCTRC.getString ("OID_Pessoa_Destinatario"));
        edCalcula_Frete.setOID_Produto (resCTRC.getLong ("OID_Produto"));
        edCalcula_Frete.setOID_Unidade (resCTRC.getLong ("OID_Unidade"));
        edCalcula_Frete.setOID_Conhecimento (resCTRC.getString ("OID_Conhecimento"));
        edCalcula_Frete.setOID_Cidade_Origem (resCTRC.getLong ("OID_Cidade"));
        edCalcula_Frete.setOID_Cidade_Destino (resCTRC.getLong ("OID_Cidade_Destino"));
        edCalcula_Frete.setOID_Cidade_Remetente (resCTRC.getLong ("OID_Cidade"));
        edCalcula_Frete.setOID_Cidade_Destinatario (resCTRC.getLong ("OID_Cidade_Destino"));
        edCalcula_Frete.setVL_Peso_Cubado (resCTRC.getDouble ("NR_PESO_CUBADO"));

        //        edCalcula_Frete.setOID_Cidade_Consignatario("oid_Cidade_Consignatario");

        sql = " SELECT * from Cidades, Regioes_Estados, Estados " + " WHERE cidades.oid_regiao_Estado = regioes_estados.oid_regiao_estado "
            + " AND regioes_estados.oid_Estado = estados.oid_Estado " + " AND cidades.oid_cidade = " + edCalcula_Frete.getOID_Cidade_Origem ();
        ResultSet rs = null;
        rs = this.executasql.executarConsulta (sql);

        while (rs.next ()) {
          edCalcula_Frete.setOID_Estado_Origem (rs.getLong ("oid_Estado"));
          edCalcula_Frete.setOID_Regiao_Origem (rs.getLong ("oid_Regiao_Estado"));
        }

        edCalcula_Frete.setOID_Cidade_Destino (edCalcula_Frete.getOID_Cidade_Destino ());

        sql = " SELECT * from Cidades, Regioes_Estados, Estados " + " WHERE cidades.oid_regiao_Estado = regioes_estados.oid_regiao_estado "
            + " AND regioes_estados.oid_Estado = estados.oid_Estado " + " AND cidades.oid_cidade = " + edCalcula_Frete.getOID_Cidade_Destino ();
        rs = null;
        rs = this.executasql.executarConsulta (sql);

        while (rs.next ()) {
          edCalcula_Frete.setOID_Estado_Origem (rs.getLong ("oid_Estado"));
          edCalcula_Frete.setOID_Regiao_Origem (rs.getLong ("oid_Regiao_Estado"));
        }

        edCalcula_Frete = Calcula_FreteBD.calcula_frete (edCalcula_Frete);
      }

      sql = "Select " + " conhecimentos.dt_emissao, " + " conhecimentos.NR_Conhecimento, " + " conhecimentos.oid_Conhecimento, " + " conhecimentos.VL_Frete, "
          + " Conhecimentos.dt_previsao_entrega," + " conhecimentos.nr_Peso, " + " conhecimentos.nr_Peso_Cubado, " + " conhecimentos.VL_Nota_Fiscal as VL_Nota_Fiscal_CTRC, "
          + " conhecimentos.VL_FRETE_PESO, " + " conhecimentos.VL_FRETE_VALOR, " + " conhecimentos.VL_SEC_CAT, " + " conhecimentos.VL_PEDAGIO, " + " conhecimentos.VL_DESPACHO, "
          + " conhecimentos.VL_OUTROS1, " + " conhecimentos.VL_OUTROS2, " + " conhecimentos.VL_TOTAL_FRETE, " + " conhecimentos.VL_BASE_CALCULO_ICMS, " + " conhecimentos.VL_ICMS, "
          + " conhecimentos.nr_volumes, " + " conhecimentos.dm_tipo_pagamento, " + " conhecimentos.hr_previsao_entrega, " + " Cidade_CTRC_Origem.NM_Cidade as NM_Cidade_CTRC_Origem, "
          + " Estado_CTRC_Origem.CD_Estado as CD_Estado_CTRC_Origem, " + " Cidade_CTRC_Destino.NM_Cidade as NM_Cidade_CTRC_Destino, "
          + " Estado_CTRC_Destino.CD_Estado as CD_Estado_CTRC_Destino, " + " Notas_Fiscais.NR_Nota_Fiscal, " + " Notas_Fiscais.dt_emissao as DT_Emissao_Nota, "
          + " Itens_Notas_Fiscais.NR_VOLUMES as NR_VOLUMES_Itens, " + " Notas_Fiscais.VL_NOTA_FISCAL, " + " Itens_Notas_Fiscais.cd_referencia, " + " Produtos.NM_Produto, "
          + " Taxas.TX_MENSAGEM_FISCAL, " + " Taxas.CD_CFO, " + " Taxas.PE_ALIQUOTA_ICMS, " + " unidades.cd_unidade, " + " unidades.OID_unidade, "
          + " pessoa_remetente.nm_razao_social as nm_razao_social_remetente, " + " pessoa_remetente.NM_Endereco as NM_Endereco_Remetente, "
          + " pessoa_remetente.NR_CNPJ_CPF as NR_CNPJ_CPF_Remetente, " + " pessoa_remetente.NM_INSCRICAO_ESTADUAL as NM_INSCRICAO_Remetente, "
          + " Cidade_Remetente.NM_Cidade as NM_Cidade_Remetente, " + " Estado_Remetente.CD_Estado as CD_Estado_Remetente, "
          + " pessoa_destinatario.nm_razao_social as nm_razao_social_destinatario, " + " pessoa_pagador.nm_razao_social as nm_razao_social_pagador, "
          + " pessoa_Destinatario.NM_Endereco as NM_Endereco_Destinatario, " + " pessoa_Destinatario.NR_CNPJ_CPF as NR_CNPJ_CPF_Destinatario, "
          + " pessoa_Destinatario.NM_INSCRICAO_ESTADUAL as NM_INSCRICAO_Destinatario, " + " Cidade_Destinatario.NM_Cidade as NM_Cidade_Destinatario, "
          + " Estado_Destinatario.CD_Estado as CD_Estado_Destinatario " + " from " + " Conhecimentos, " + " Conhecimentos_Notas_Fiscais, " + " Notas_Fiscais, " + " Itens_Notas_Fiscais, "
          + " Referencias, " + " Produtos, " + " Taxas, " + " unidades, " + " pessoas pessoa_remetente, " + " pessoas pessoa_destinatario, " + " pessoas pessoa_pagador, "
          + " cidades Cidade_CTRC_Origem, " + " estados Estado_CTRC_Origem, " + " regioes_estados Regiao_Estado_CTRC_Origem, " + " cidades Cidade_CTRC_Destino, "
          + " estados Estado_CTRC_Destino, " + " regioes_estados Regiao_Estado_CTRC_Destino, " + " cidades Cidade_Remetente, " + " estados Estado_Remetente, "
          + " regioes_estados Regiao_Estado_Remetente, " + " cidades Cidade_Destinatario, " + " estados Estado_Destinatario, " + " regioes_estados Regiao_Estado_Destinatario " + " where "
          + " unidades.oid_unidade = conhecimentos.oid_unidade and" + " Conhecimentos.oid_Conhecimento = Conhecimentos_Notas_Fiscais.oid_Conhecimento and"
          + " Conhecimentos.oid_Taxa = Taxas.oid_Taxa and" + " Conhecimentos_Notas_Fiscais.oid_Nota_Fiscal = Notas_Fiscais.oid_Nota_Fiscal and"
          + " Itens_Notas_Fiscais.oid_Nota_Fiscal = Notas_Fiscais.oid_Nota_Fiscal and" + " Itens_Notas_Fiscais.CD_Referencia = Referencias.CD_Referencia and"
          + " Produtos.oid_Produto = Referencias.oid_Produto and" + " conhecimentos.oid_pessoa_destinatario = pessoa_destinatario.oid_pessoa and"
          + " conhecimentos.oid_pessoa_pagador = pessoa_pagador.oid_pessoa and" + " conhecimentos.oid_pessoa = pessoa_remetente.oid_pessoa and"
          + " conhecimentos.oid_cidade = Cidade_CTRC_Origem.oid_cidade and" + " Cidade_CTRC_Origem.oid_regiao_Estado = Regiao_Estado_CTRC_Origem.oid_regiao_estado and"
          + " Regiao_Estado_CTRC_Origem.oid_Estado = Estado_CTRC_Origem.oid_Estado and" + " conhecimentos.oid_cidade_Destino = Cidade_CTRC_Destino.oid_cidade and"
          + " Cidade_CTRC_Destino.oid_regiao_Estado = Regiao_Estado_CTRC_Destino.oid_regiao_estado and" + " Regiao_Estado_CTRC_Destino.oid_Estado = Estado_CTRC_Destino.oid_Estado and"
          + " pessoa_remetente.oid_cidade = Cidade_Remetente.oid_cidade and" + " Cidade_Remetente.oid_regiao_Estado = Regiao_Estado_Remetente.oid_regiao_estado and"
          + " Regiao_Estado_Remetente.oid_Estado = Estado_Remetente.oid_Estado and" + " pessoa_Destinatario.oid_cidade = Cidade_Destinatario.oid_cidade and"
          + " Cidade_Destinatario.oid_regiao_Estado = Regiao_Estado_Destinatario.oid_regiao_estado and" + " Regiao_Estado_Destinatario.oid_Estado = Estado_Destinatario.oid_Estado  "
          + " AND (Conhecimentos.DM_Situacao = 'G' " + " or Conhecimentos.DM_Situacao = 'F')" + " AND Conhecimentos.DM_Impresso = 'S' " + " AND Conhecimentos.VL_Total_Frete > 0";

      if (String.valueOf (ed.getNR_Conhecimento ()) != null && !String.valueOf (ed.getNR_Conhecimento ()).equals ("0")) {
        sql += " and Conhecimentos.NR_Conhecimento = " + ed.getNR_Conhecimento ();
      }

      if (String.valueOf (ed.getOID_Unidade ()) != null && !String.valueOf (ed.getOID_Unidade ()).equals ("0")) {
        sql += " and Conhecimentos.OID_Unidade = " + ed.getOID_Unidade ();
      }
      if (String.valueOf (ed.getOID_Pessoa ()) != null && !String.valueOf (ed.getOID_Pessoa ()).equals ("") && !String.valueOf (ed.getOID_Pessoa ()).equals ("null")) {
        sql += " and Conhecimentos.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
      }
      if (String.valueOf (ed.getOID_Pessoa_Destinatario ()) != null && !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("")
          && !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("null")) {
        sql += " and Conhecimentos.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario () + "'";
      }
      if (String.valueOf (ed.getOID_Pessoa_Consignatario ()) != null && !String.valueOf (ed.getOID_Pessoa_Consignatario ()).equals ("")
          && !String.valueOf (ed.getOID_Pessoa_Consignatario ()).equals ("null")) {
        sql += " and Conhecimentos.OID_Pessoa_Consignatario = '" + ed.getOID_Pessoa_Consignatario () + "'";
      }
      if (String.valueOf (ed.getDT_Emissao ()) != null && !String.valueOf (ed.getDT_Emissao ()).equals ("") && !String.valueOf (ed.getDT_Emissao ()).equals ("null")) {
        sql += " and Conhecimentos.DT_Emissao = '" + ed.getDT_Emissao () + "'";
      }
      sql += " order by conhecimentos.oid_conhecimento ";

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql.toString ());

      ConhecimentoRL conRL = new ConhecimentoRL ();
      b = conRL.geraRelConhecTransRodCarga (res);

      sql = "Select * " + " from " + " Conhecimentos " + " where " + " Conhecimentos.DM_Impresso = 'N' and " + " Conhecimentos.VL_Total_Frete > 0";

      if (String.valueOf (ed.getNR_Conhecimento ()) != null && !String.valueOf (ed.getNR_Conhecimento ()).equals ("0")) {
        sql += " and Conhecimentos.NR_Conhecimento = " + ed.getNR_Conhecimento ();
      }

      if (String.valueOf (ed.getOID_Unidade ()) != null && !String.valueOf (ed.getOID_Unidade ()).equals ("0")) {
        sql += " and Conhecimentos.OID_Unidade = " + ed.getOID_Unidade ();
      }
      if (String.valueOf (ed.getOID_Pessoa ()) != null && !String.valueOf (ed.getOID_Pessoa ()).equals ("") && !String.valueOf (ed.getOID_Pessoa ()).equals ("null")) {
        sql += " and Conhecimentos.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
      }
      if (String.valueOf (ed.getOID_Pessoa_Destinatario ()) != null && !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("")
          && !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("null")) {
        sql += " and Conhecimentos.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario () + "'";
      }
      if (String.valueOf (ed.getOID_Pessoa_Consignatario ()) != null && !String.valueOf (ed.getOID_Pessoa_Consignatario ()).equals ("")
          && !String.valueOf (ed.getOID_Pessoa_Consignatario ()).equals ("null")) {
        sql += " and Conhecimentos.OID_Pessoa_Consignatario = '" + ed.getOID_Pessoa_Consignatario () + "'";
      }
      if (String.valueOf (ed.getDT_Emissao ()) != null && !String.valueOf (ed.getDT_Emissao ()).equals ("") && !String.valueOf (ed.getDT_Emissao ()).equals ("null")) {
        sql += " and Conhecimentos.DT_Emissao = '" + ed.getDT_Emissao () + "'";
      }

      resCTRC = null;
      resCTRC = this.executasql.executarConsulta (sql);

      while (resCTRC.next ()) {

        sql = " update Conhecimentos set DM_Impresso = 'S'";
        sql += " where Conhecimentos.oid_Conhecimento = '" + resCTRC.getString ("oid_Conhecimento") + "'";

        int u = executasql.executarUpdate (sql);
      }
    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      if (NR_Proximo > NR_Final) {
        exce.setMensagem ("AIDOF sem numera��o dispon�vel");
      }
      else {
        exce.setMensagem ("Erro no m�todo listar");
      }
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("geraRelatorio(ConhecimentoED ed)");
    }
    return b;
  }

  public byte[] geraRelConhecTransRodCarga (ConhecimentoED ed) throws Excecoes {

    String sql = null;
    byte[] b = null;
    long valOid = 0;
    long NR_Proximo = 0;
    long NR_Final = 0;
    long NR_Conhecimento = 0;

    try {
      Data data = new Data ();

      sql = "Select * " + " from " + " Conhecimentos " + " where " + " Conhecimentos.DM_Impresso = 'N' and " + " Conhecimentos.VL_Total_Frete > 0";

      if (String.valueOf (ed.getNR_Conhecimento ()) != null && !String.valueOf (ed.getNR_Conhecimento ()).equals ("0")) {
        sql += " and Conhecimentos.NR_Conhecimento = " + ed.getNR_Conhecimento ();
      }

      if (String.valueOf (ed.getOID_Unidade ()) != null && !String.valueOf (ed.getOID_Unidade ()).equals ("0")) {
        sql += " and Conhecimentos.OID_Unidade = " + ed.getOID_Unidade ();
      }
      if (String.valueOf (ed.getOID_Pessoa ()) != null && !String.valueOf (ed.getOID_Pessoa ()).equals ("") && !String.valueOf (ed.getOID_Pessoa ()).equals ("null")) {
        sql += " and Conhecimentos.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
      }
      if (String.valueOf (ed.getOID_Pessoa_Destinatario ()) != null && !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("")
          && !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("null")) {
        sql += " and Conhecimentos.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario () + "'";
      }
      if (String.valueOf (ed.getOID_Pessoa_Consignatario ()) != null && !String.valueOf (ed.getOID_Pessoa_Consignatario ()).equals ("")
          && !String.valueOf (ed.getOID_Pessoa_Consignatario ()).equals ("null")) {
        sql += " and Conhecimentos.OID_Pessoa_Consignatario = '" + ed.getOID_Pessoa_Consignatario () + "'";
      }
      if (String.valueOf (ed.getDT_Emissao ()) != null && !String.valueOf (ed.getDT_Emissao ()).equals ("") && !String.valueOf (ed.getDT_Emissao ()).equals ("null")) {
        sql += " and Conhecimentos.DT_Emissao = '" + ed.getDT_Emissao () + "'";
      }

      ResultSet resCTRC = null;
      resCTRC = this.executasql.executarConsulta (sql);

      while (resCTRC.next ()) {

        ed.setOID_Unidade (resCTRC.getLong ("OID_Unidade"));
        ed.setOID_Conhecimento (resCTRC.getString ("oid_Conhecimento"));

        sql = " SELECT AIDOF.NR_Proximo, AIDOF.NR_FINAL, AIDOF.OID_AIDOF, AIDOF.NM_Serie ";
        sql += " FROM Parametros_Filiais, AIDOF ";
        sql += " WHERE Parametros_Filiais.OID_AIDOF = AIDOF.OID_AIDOF ";
        sql += " AND Parametros_Filiais.OID_Unidade = " + ed.getOID_Unidade ();

        ResultSet resTP = null;
        resTP = this.executasql.executarConsulta (sql.toString ());

        while (resTP.next ()) {
          ed.setNM_Serie (resTP.getString ("NM_Serie"));
          NR_Conhecimento = resTP.getLong ("NR_Proximo");
          valOid = resTP.getLong ("OID_AIDOF");
          NR_Proximo = resTP.getLong ("NR_Proximo") + 1;
          NR_Final = resTP.getLong ("NR_FINAL");
        }

        if (NR_Proximo > NR_Final) {
          Excecoes exc = new Excecoes ();
          throw exc;
        }

        sql = " UPDATE AIDOF SET NR_Proximo= " + (NR_Conhecimento + 1);
        sql += " WHERE OID_AIDOF = " + valOid;

        int u = executasql.executarUpdate (sql);

        sql = " update Conhecimentos set " + " NR_Conhecimento = " + NR_Conhecimento + ", " + " NM_Serie = '" + ed.getNM_Serie () + "', " + " DT_Emissao = '" + Data.getDataDMY () + "' ";
        sql += " where Conhecimentos.oid_Conhecimento = '" + ed.getOID_Conhecimento () + "'";

        u = executasql.executarUpdate (sql);

        ed.setDT_Emissao (Data.getDataDMY ());

        if (String.valueOf (ed.getNR_Conhecimento ()) != null && !String.valueOf (ed.getNR_Conhecimento ()).equals ("0")) {
          ed.setNR_Conhecimento (NR_Conhecimento);
        }

        Calcula_FreteBD = new Calcula_FreteBD (this.executasql);
        Calcula_FreteED edCalcula_Frete = new Calcula_FreteED ();

        FormataDataBean DataFormatada = new FormataDataBean ();

        edCalcula_Frete.setDM_Tipo_Pagamento (resCTRC.getString ("DM_Tipo_Pagamento"));
        edCalcula_Frete.setDM_Tipo_Conhecimento (resCTRC.getString ("DM_Tipo_Conhecimento"));
        edCalcula_Frete.setDT_Emissao (resCTRC.getString ("DT_Emissao"));
        DataFormatada.setDT_FormataData (edCalcula_Frete.getDT_Emissao ());
        edCalcula_Frete.setDT_Emissao (DataFormatada.getDT_FormataData ());
        edCalcula_Frete.setOID_Modal (resCTRC.getLong ("OID_Modal"));
        edCalcula_Frete.setOID_Pessoa (resCTRC.getString ("OID_Pessoa"));
        edCalcula_Frete.setOID_Pessoa_Consignatario (resCTRC.getString ("OID_Pessoa_Consignatario"));
        edCalcula_Frete.setOID_Pessoa_Pagador (resCTRC.getString ("OID_Pessoa_Pagador"));
        edCalcula_Frete.setOID_Pessoa_Pagador_Tabela (resCTRC.getString ("OID_Pessoa_Pagador"));
        edCalcula_Frete.setOID_Pessoa_Destinatario (resCTRC.getString ("OID_Pessoa_Destinatario"));
        edCalcula_Frete.setOID_Produto (resCTRC.getLong ("OID_Produto"));
        edCalcula_Frete.setOID_Unidade (resCTRC.getLong ("OID_Unidade"));
        edCalcula_Frete.setOID_Conhecimento (resCTRC.getString ("OID_Conhecimento"));
        edCalcula_Frete.setOID_Cidade_Origem (resCTRC.getLong ("OID_Cidade"));
        edCalcula_Frete.setOID_Cidade_Destino (resCTRC.getLong ("OID_Cidade_Destino"));
        edCalcula_Frete.setOID_Cidade_Remetente (resCTRC.getLong ("OID_Cidade"));
        edCalcula_Frete.setOID_Cidade_Destinatario (resCTRC.getLong ("OID_Cidade_Destino"));
        edCalcula_Frete.setVL_Peso_Cubado (resCTRC.getDouble ("NR_PESO_CUBADO"));

        //        edCalcula_Frete.setOID_Cidade_Consignatario("oid_Cidade_Consignatario");

        edCalcula_Frete.setOID_Cidade_Origem (resCTRC.getLong ("OID_Cidade"));

        sql = " SELECT Estados.oid_Estado, Regioes_Estados.oid_Regiao_Estado from Cidades, Regioes_Estados, Estados " + " WHERE cidades.oid_regiao_Estado = regioes_estados.oid_regiao_estado "
            + " AND regioes_estados.oid_Estado = estados.oid_Estado " + " AND cidades.oid_cidade = " + edCalcula_Frete.getOID_Cidade_Remetente ();
        ResultSet rs = null;
        rs = this.executasql.executarConsulta (sql);

        while (rs.next ()) {
          edCalcula_Frete.setOID_Estado_Origem (rs.getLong ("oid_Estado"));
          edCalcula_Frete.setOID_Regiao_Origem (rs.getLong ("oid_Regiao_Estado"));
        }

        edCalcula_Frete.setOID_Cidade_Destino (resCTRC.getLong ("OID_Cidade_Destino"));

        sql = " SELECT Estados.oid_Estado, Regioes_Estados.oid_Regiao_Estado from Cidades, Regioes_Estados, Estados " + " WHERE cidades.oid_regiao_Estado = regioes_estados.oid_regiao_estado "
            + " AND regioes_estados.oid_Estado = estados.oid_Estado " + " AND cidades.oid_cidade = " + edCalcula_Frete.getOID_Cidade_Destinatario ();
        rs = null;
        rs = this.executasql.executarConsulta (sql);

        while (rs.next ()) {
          edCalcula_Frete.setOID_Estado_Destino (rs.getLong ("oid_Estado"));
          edCalcula_Frete.setOID_Regiao_Destino (rs.getLong ("oid_Regiao_Estado"));
        }

        edCalcula_Frete = Calcula_FreteBD.calcula_frete (edCalcula_Frete);
      }

      sql = "Select " + " conhecimentos.dt_emissao, " + " conhecimentos.NR_Conhecimento, " + " conhecimentos.oid_Pessoa, " + " conhecimentos.oid_pessoa_destinatario, "
          + " conhecimentos.oid_pessoa_Consignatario, " + " conhecimentos.oid_pessoa_Redespacho, " + " conhecimentos.oid_pessoa_pagador, " + " conhecimentos.oid_Conhecimento, "
          + " conhecimentos.oid_Produto as oid_Produto_Conhecimento, " + " conhecimentos.VL_Frete, " + " Conhecimentos.dt_previsao_entrega," + " conhecimentos.nr_Peso, "
          + " conhecimentos.TX_Observacao, " + " conhecimentos.nr_Peso_Cubado, " + " conhecimentos.VL_Nota_Fiscal as VL_Nota_Fiscal_CTRC, " + " conhecimentos.VL_FRETE_PESO, "
          + " conhecimentos.VL_FRETE_VALOR, " + " conhecimentos.VL_SEC_CAT, " + " conhecimentos.VL_PEDAGIO, " + " conhecimentos.VL_DESPACHO, " + " conhecimentos.VL_OUTROS1, "
          + " conhecimentos.VL_OUTROS2, " + " conhecimentos.VL_TOTAL_FRETE, " + " conhecimentos.VL_BASE_CALCULO_ICMS, " + " conhecimentos.VL_ICMS, " + " conhecimentos.nr_volumes, "
          + " conhecimentos.dm_tipo_pagamento, " + " conhecimentos.dm_tipo_conhecimento, " + " conhecimentos.hr_previsao_entrega, " + " conhecimentos.CD_CFO_CONHECIMENTO, "
          + " Cidade_CTRC_Origem.NM_Cidade as NM_Cidade_CTRC_Origem, " + " Estado_CTRC_Origem.CD_Estado as CD_Estado_CTRC_Origem, "
          + " Cidade_CTRC_Destino.NM_Cidade as NM_Cidade_CTRC_Destino, " + " Estado_CTRC_Destino.CD_Estado as CD_Estado_CTRC_Destino, " + " Taxas.TX_MENSAGEM_FISCAL, "
          + " Taxas.PE_ALIQUOTA_ICMS " + " from " + " Conhecimentos, " + " Taxas, " + " cidades Cidade_CTRC_Origem, " + " estados Estado_CTRC_Origem, "
          + " regioes_estados Regiao_Estado_CTRC_Origem, " + " cidades Cidade_CTRC_Destino, " + " estados Estado_CTRC_Destino, " + " regioes_estados Regiao_Estado_CTRC_Destino " + " where "
          + " Conhecimentos.oid_Taxa = Taxas.oid_Taxa and" + " conhecimentos.oid_cidade = Cidade_CTRC_Origem.oid_cidade and"
          + " Cidade_CTRC_Origem.oid_regiao_Estado = Regiao_Estado_CTRC_Origem.oid_regiao_estado and" + " Regiao_Estado_CTRC_Origem.oid_Estado = Estado_CTRC_Origem.oid_Estado and"
          + " conhecimentos.oid_cidade_Destino = Cidade_CTRC_Destino.oid_cidade and" + " Cidade_CTRC_Destino.oid_regiao_Estado = Regiao_Estado_CTRC_Destino.oid_regiao_estado and"
          + " Regiao_Estado_CTRC_Destino.oid_Estado = Estado_CTRC_Destino.oid_Estado and" + " Conhecimentos.DM_Impresso = 'N' and " + " Conhecimentos.VL_Total_Frete > 0";

      if (String.valueOf (ed.getNR_Conhecimento ()) != null && !String.valueOf (ed.getNR_Conhecimento ()).equals ("0")) {
        sql += " and Conhecimentos.NR_Conhecimento = " + ed.getNR_Conhecimento ();
      }

      if (String.valueOf (ed.getOID_Unidade ()) != null && !String.valueOf (ed.getOID_Unidade ()).equals ("0")) {
        sql += " and Conhecimentos.OID_Unidade = " + ed.getOID_Unidade ();
      }
      if (String.valueOf (ed.getOID_Pessoa ()) != null && !String.valueOf (ed.getOID_Pessoa ()).equals ("") && !String.valueOf (ed.getOID_Pessoa ()).equals ("null")) {
        sql += " and Conhecimentos.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
      }
      if (String.valueOf (ed.getOID_Pessoa_Destinatario ()) != null && !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("")
          && !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("null")) {
        sql += " and Conhecimentos.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario () + "'";
      }
      if (String.valueOf (ed.getOID_Pessoa_Consignatario ()) != null && !String.valueOf (ed.getOID_Pessoa_Consignatario ()).equals ("")
          && !String.valueOf (ed.getOID_Pessoa_Consignatario ()).equals ("null")) {
        sql += " and Conhecimentos.OID_Pessoa_Consignatario = '" + ed.getOID_Pessoa_Consignatario () + "'";
      }
      if (String.valueOf (ed.getDT_Emissao ()) != null && !String.valueOf (ed.getDT_Emissao ()).equals ("") && !String.valueOf (ed.getDT_Emissao ()).equals ("null")) {
        sql += " and Conhecimentos.DT_Emissao = '" + ed.getDT_Emissao () + "'";
      }
      sql += " order by conhecimentos.oid_conhecimento ";

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql.toString ());

      ConhecimentoRL conRL = new ConhecimentoRL ();
      b = conRL.geraRelConhecTransRodCarga (res);

      sql = "Select * " + " from " + " Conhecimentos " + " where " + " Conhecimentos.DM_Impresso = 'N' and " + " Conhecimentos.VL_Total_Frete > 0";

      if (String.valueOf (ed.getNR_Conhecimento ()) != null && !String.valueOf (ed.getNR_Conhecimento ()).equals ("0")) {
        sql += " and Conhecimentos.NR_Conhecimento = " + ed.getNR_Conhecimento ();
      }

      if (String.valueOf (ed.getOID_Unidade ()) != null && !String.valueOf (ed.getOID_Unidade ()).equals ("0")) {
        sql += " and Conhecimentos.OID_Unidade = " + ed.getOID_Unidade ();
      }
      if (String.valueOf (ed.getOID_Pessoa ()) != null && !String.valueOf (ed.getOID_Pessoa ()).equals ("") && !String.valueOf (ed.getOID_Pessoa ()).equals ("null")) {
        sql += " and Conhecimentos.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
      }
      if (String.valueOf (ed.getOID_Pessoa_Destinatario ()) != null && !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("")
          && !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("null")) {
        sql += " and Conhecimentos.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario () + "'";
      }
      if (String.valueOf (ed.getOID_Pessoa_Consignatario ()) != null && !String.valueOf (ed.getOID_Pessoa_Consignatario ()).equals ("")
          && !String.valueOf (ed.getOID_Pessoa_Consignatario ()).equals ("null")) {
        sql += " and Conhecimentos.OID_Pessoa_Consignatario = '" + ed.getOID_Pessoa_Consignatario () + "'";
      }
      if (String.valueOf (ed.getDT_Emissao ()) != null && !String.valueOf (ed.getDT_Emissao ()).equals ("") && !String.valueOf (ed.getDT_Emissao ()).equals ("null")) {
        sql += " and Conhecimentos.DT_Emissao = '" + ed.getDT_Emissao () + "'";
      }

      resCTRC = null;
      resCTRC = this.executasql.executarConsulta (sql);

      while (resCTRC.next ()) {

        sql = " update Conhecimentos set DM_Impresso = 'S'";
        sql += " where Conhecimentos.oid_Conhecimento = '" + resCTRC.getString ("oid_Conhecimento") + "'";

        int u = executasql.executarUpdate (sql);
      }
    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      if (NR_Proximo > NR_Final) {
        exce.setMensagem ("AIDOF sem numera��o dispon�vel");
      }
      else {
        exce.setMensagem ("Erro no m�todo listar");
      }
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("geraRelatorio(ConhecimentoED ed)");
    }
    return b;
  }

  public byte[] imprimeRecibo_CTRC (ConhecimentoED ed) throws Excecoes {

    String sql = null;
    byte[] b = null;
    long valOid = 0;
    long NR_Proximo = 0;
    long NR_Final = 0;
    long NR_Conhecimento = 0;

    try {
      Data data = new Data ();
      FormataDataBean DataFormatada = new FormataDataBean ();

      sql = "Select " + " conhecimentos.dt_emissao, " + " conhecimentos.NR_Conhecimento, " + " conhecimentos.oid_Pessoa, " + " conhecimentos.oid_Unidade, "
          + " conhecimentos.oid_pessoa_destinatario, " + " conhecimentos.oid_pessoa_Consignatario, " + " conhecimentos.oid_pessoa_Redespacho, " + " conhecimentos.oid_pessoa_pagador, "
          + " conhecimentos.oid_Conhecimento, " + " conhecimentos.oid_Produto as oid_Produto_Conhecimento, " + " conhecimentos.VL_Frete, " + " Conhecimentos.dt_previsao_entrega,"
          + " conhecimentos.nr_Peso, " + " conhecimentos.TX_Observacao, " + " conhecimentos.nr_Peso_Cubado, " + " conhecimentos.VL_Nota_Fiscal as VL_Nota_Fiscal_CTRC, "
          + " conhecimentos.VL_FRETE_PESO, " + " conhecimentos.VL_FRETE_VALOR, " + " conhecimentos.VL_SEC_CAT, " + " conhecimentos.VL_PEDAGIO, " + " conhecimentos.VL_DESPACHO, "
          + " conhecimentos.VL_OUTROS1, " + " conhecimentos.VL_OUTROS2, " + " conhecimentos.VL_TOTAL_FRETE, " + " conhecimentos.VL_BASE_CALCULO_ICMS, " + " conhecimentos.VL_ICMS, "
          + " conhecimentos.nr_volumes, " + " conhecimentos.dm_tipo_pagamento, " + " conhecimentos.dm_tipo_conhecimento, " + " conhecimentos.hr_previsao_entrega, "
          + " conhecimentos.CD_CFO_CONHECIMENTO, " + " Cidade_CTRC_Origem.NM_Cidade as NM_Cidade_CTRC_Origem, " + " Estado_CTRC_Origem.CD_Estado as CD_Estado_CTRC_Origem, "
          + " Cidade_CTRC_Destino.NM_Cidade as NM_Cidade_CTRC_Destino, " + " Estado_CTRC_Destino.CD_Estado as CD_Estado_CTRC_Destino, " + " Produtos.NM_Produto, "
          + " Taxas.TX_MENSAGEM_FISCAL, " + " Taxas.PE_ALIQUOTA_ICMS " + " from " + " Conhecimentos, " + " Taxas, " + " Produtos, " + " cidades Cidade_CTRC_Origem, "
          + " estados Estado_CTRC_Origem, " + " regioes_estados Regiao_Estado_CTRC_Origem, " + " cidades Cidade_CTRC_Destino, " + " estados Estado_CTRC_Destino, "
          + " regioes_estados Regiao_Estado_CTRC_Destino " + " where " + " Conhecimentos.oid_Taxa = Taxas.oid_Taxa and" + " Conhecimentos.oid_produto = Produtos.oid_produto and"
          + " conhecimentos.oid_cidade = Cidade_CTRC_Origem.oid_cidade and" + " Cidade_CTRC_Origem.oid_regiao_Estado = Regiao_Estado_CTRC_Origem.oid_regiao_estado and"
          + " Regiao_Estado_CTRC_Origem.oid_Estado = Estado_CTRC_Origem.oid_Estado and" + " conhecimentos.oid_cidade_Destino = Cidade_CTRC_Destino.oid_cidade and"
          + " Cidade_CTRC_Destino.oid_regiao_Estado = Regiao_Estado_CTRC_Destino.oid_regiao_estado and" + " Regiao_Estado_CTRC_Destino.oid_Estado = Estado_CTRC_Destino.oid_Estado and"
          + " Conhecimentos.DM_Impresso = 'S' and " + " Conhecimentos.DM_Tipo_Conhecimento = '6' and " + " Conhecimentos.VL_Total_Frete > 0";

      if (String.valueOf (ed.getNR_Conhecimento_Inicial ()) != null && !String.valueOf (ed.getNR_Conhecimento_Inicial ()).equals ("0")) {
        sql += " and Conhecimentos.NR_Conhecimento >= " + ed.getNR_Conhecimento_Inicial ();
      }
      if (String.valueOf (ed.getNR_Conhecimento_Inicial ()) != null && !String.valueOf (ed.getNR_Conhecimento_Inicial ()).equals ("0")) {
        sql += " and Conhecimentos.NR_Conhecimento <= " + ed.getNR_Conhecimento_Final ();
      }

      if (String.valueOf (ed.getOID_Unidade ()) != null && !String.valueOf (ed.getOID_Unidade ()).equals ("0")) {
        sql += " and Conhecimentos.OID_Unidade = " + ed.getOID_Unidade ();
      }
      sql += " order by conhecimentos.oid_conhecimento ";

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql.toString ());

      ConhecimentoRL conRL = new ConhecimentoRL ();
      b = conRL.imprimeRecibo_CTRC (res);

    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      if (NR_Proximo > NR_Final) {
        exce.setMensagem ("AIDOF sem numera��o dispon�vel");
      }
      else {
        exce.setMensagem ("Erro no m�todo listar");
      }
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("geraRelatorio(ConhecimentoED ed)");
    }
    return b;
  }

  private ResultSet imprime_ConhecimentoComum (ConhecimentoED ed, String LPT_PDF) throws Excecoes {
    String condicao_pesquisa = "";
    long Oid_AIDOF = 0;
    long NR_Proximo = 0;
    long NR_Final = 0;
    long NR_Conhecimento = 0;
    long NR_Minuta = 0;
    long NR_Nota_Fiscal_Servico = 0;
    long NR_ACT = 0;
    long NR_AWB = 0;
    long Nr_Sort_Impressao = (new Long (String.valueOf (System.currentTimeMillis ()).toString ()).longValue ());
    String tipo_impressao = "I";
    String DM_Tipo_Conhecimento = "";
    String DM_Tipo_Documento = ed.getDM_Tipo_Documento ();
    String DT_Emissao_Conhecimento = Data.getDataDMY ();
    boolean gera_NR_Documento_pela_AIDOF = true;
    String sql = "";
    String nm_usuario="";

    System.out.println ("Nr_Sort_Impressao->=" + Nr_Sort_Impressao);

    // System.out.println ("DT_Emissao_Conhecimento->=" + DT_Emissao_Conhecimento);

    // System.out.println ("imprime_Conhecimento-> REL=" + ed.getDM_Relatorio ());

    sql = " SELECT Conhecimentos.oid_conhecimento " +
        "      ,Conhecimentos.nr_conhecimento " +
        "      ,Conhecimentos.nr_minuta " +
        "      ,Conhecimentos.oid_Usuario " +
        "      ,Conhecimentos.nr_ACT " +
        "      ,Conhecimentos.nr_AWB " +
        "      ,Conhecimentos.nr_Nota_Fiscal_Servico " +
        "      ,Conhecimentos.oid_unidade " +
        "      ,Conhecimentos.DM_Tipo_Documento " +
        "      ,Conhecimentos.DM_Cia_Aerea " +
        "      ,Conhecimentos.DM_Tipo_Conhecimento " +
        "      ,Conhecimentos.NM_Serie " +
        "      ,Modal.DM_Tipo_Tabela_Frete " +
        "      ,conhecimentos.oid_Pessoa " +
        "      ,conhecimentos.oid_pessoa_destinatario " +
        "      ,conhecimentos.oid_pessoa_pagador " +
        " from Conhecimentos " +
        "      left join Modal " +
        "        on Conhecimentos.oid_Modal = Modal.oid_Modal " +
        " where 1=1";

    if (!"A".equals (DM_Tipo_Documento) && !"REIMPRESSAO".equals (ed.getDM_Relatorio ())) { //A=AWB  -- DM_Rel = R=REIMPRESSAO
      sql += " AND Conhecimentos.DM_Impresso = 'N' ";
    }

    if (util.doValida (ed.getOID_Conhecimento ())) {
      condicao_pesquisa += " and Conhecimentos.OID_Conhecimento = '" + ed.getOID_Conhecimento () + "'";
    }
    else {
      tipo_impressao = "F";
      if (ed.getNR_Conhecimento () > 0) {
        condicao_pesquisa += " and Conhecimentos.NR_Conhecimento = " + ed.getNR_Conhecimento ();
      }
      else {
        if (ed.getNR_Conhecimento_Inicial () > 0) {
          condicao_pesquisa += " and Conhecimentos.NR_Conhecimento >= " + ed.getNR_Conhecimento_Inicial ();
        }
        if (ed.getNR_Conhecimento_Final () > 0) {
          condicao_pesquisa += " and Conhecimentos.NR_Conhecimento <= " + ed.getNR_Conhecimento_Final ();
        }
      }
      if (ed.getOID_Unidade () > 0) {
        condicao_pesquisa += " and Conhecimentos.OID_Unidade = " + ed.getOID_Unidade ();
      }
      if (util.doValida (ed.getOID_Pessoa ())) {
        condicao_pesquisa += " and Conhecimentos.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
      }
      if (util.doValida (ed.getOID_Pessoa_Destinatario ())) {
        condicao_pesquisa += " and Conhecimentos.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario () + "'";
      }
      if (util.doValida (ed.getOID_Pessoa_Entregadora ())) {
        condicao_pesquisa += " and Conhecimentos.OID_Pessoa_Entregadora = '" + ed.getOID_Pessoa_Entregadora () + "'";
      }
      if (util.doValida (ed.getOID_Pessoa_Consignatario ())) {
        condicao_pesquisa += " and Conhecimentos.OID_Pessoa_Consignatario = '" + ed.getOID_Pessoa_Consignatario () + "'";
      }
      if (util.doValida (ed.getDT_Emissao ())) {
        condicao_pesquisa += " and Conhecimentos.DT_Emissao = '" + ed.getDT_Emissao () + "'";
      }

      if (util.doValida (ed.getDt_Emissao_Inicial ())) {
        condicao_pesquisa += " and Conhecimentos.DT_Emissao >= '" + ed.getDt_Emissao_Inicial () + "'";
      }
      if (util.doValida (ed.getDt_Emissao_Final ())) {
        condicao_pesquisa += " and Conhecimentos.DT_Emissao <= '" + ed.getDt_Emissao_Final () + "'";
      }
      if (ed.getOID_Usuario () > 0) {
        condicao_pesquisa += " and Conhecimentos.OID_Usuario = " + ed.getOID_Usuario ();
      }
      if (JavaUtil.doValida (ed.getCD_Rota_Entrega ())) {
        condicao_pesquisa += " and Conhecimentos.CD_Rota_Entrega = '" + ed.getCD_Rota_Entrega () + "'";
      }
    }

    if (!"T".equals (DM_Tipo_Documento) && !"A".equals (DM_Tipo_Documento)) { //T=ACT  A=AWB
      condicao_pesquisa += " AND (Conhecimentos.VL_Total_Frete > 0 OR " +
          "      Conhecimentos.DM_Situacao = 'C'  OR " +
          "      Conhecimentos.DM_Tipo_Documento = 'Z' )  "; // usa o filtro p/ N ctos

    }

    condicao_pesquisa +=" ORDER by conhecimentos.oid_Usuario ";
  	if ("LPT".equals(LPT_PDF)) {
  		condicao_pesquisa +=", conhecimentos.uni_key DESC ";
  	}else {
  		condicao_pesquisa +=", conhecimentos.uni_key ASC ";
  	}

    condicao_pesquisa += " LIMIT 50 ";
    sql += condicao_pesquisa;

    System.out.println ("IMPRIME CONHECIMENTO BD>>>>>>>>>>" + sql);

    ResultSet resCTRC = this.executasql.executarConsulta (sql);
    try {
      try {
        while (resCTRC.next ()) {
          if (!"REIMPRESSAO".equals (ed.getDM_Relatorio ())) {
            // System.out.println ("ACHOU CTO DM_Tipo_Documento->>" + DM_Tipo_Documento);

            ed.setOID_Unidade (resCTRC.getLong ("OID_Unidade"));
            ed.setOID_Conhecimento (resCTRC.getString ("oid_Conhecimento"));

            if (tipo_impressao.equals ("F")) {
	            	//imp por lote
	      	      ConhecimentoRN Conhecimentorn = new ConhecimentoRN ();
	      	      if ("".equals(nm_usuario)){
	      	    	  if (ed.getOID_Usuario()==0){
	      	    		  ed.setOID_Usuario(resCTRC.getLong("oid_Usuario"));
	      	    	  }
	      	    	  sql =" SELECT NM_Usuario FROM USUARIOS WHERE oid_Usuario=" + ed.getOID_Usuario();
	      	          ResultSet resUsu = this.executasql.executarConsulta (sql);
	      	          if (resUsu.next ()) {
	      	        	nm_usuario=resUsu.getString("NM_Usuario");
	      	        }
	      	      }
	    	      ed.setUsuario_Stamp(nm_usuario);
	    	      ed.setOID_Conhecimento (resCTRC.getString ("oid_Conhecimento"));

            }



            NR_Conhecimento = resCTRC.getLong ("NR_Conhecimento");
            NR_Minuta = resCTRC.getLong ("nr_minuta");
            NR_ACT = resCTRC.getLong ("nr_ACT");
            NR_AWB = resCTRC.getLong ("nr_AWB");
            NR_Nota_Fiscal_Servico = resCTRC.getLong ("nr_Nota_Fiscal_Servico");
            DM_Tipo_Conhecimento=resCTRC.getString ("DM_Tipo_Conhecimento");

            sql = " SELECT Unidades.DT_Emissao_Conhecimento FROM Unidades " +
                " WHERE  Unidades.DT_Emissao_Conhecimento is not null " +
                " AND    Unidades.oid_Unidade     = " + ed.getOID_Unidade ();
            ResultSet resTP = this.executasql.executarConsulta (sql);
            if (resTP.next ()) {
              DT_Emissao_Conhecimento = resTP.getString ("DT_Emissao_Conhecimento");
            }

            if ("A".equals (DM_Tipo_Documento) && NR_AWB > 0) {
              gera_NR_Documento_pela_AIDOF = false;
            }

            if (!"B".equals (DM_Tipo_Documento)) {
              if (gera_NR_Documento_pela_AIDOF) {

                if ("A".equals (DM_Tipo_Documento) && "AWB_CIA_AEREA".equals (parametro_FixoED.getDM_Formulario_AWB ())) {
                  sql = " SELECT AIDOF.NR_Proximo, AIDOF.NR_FINAL, AIDOF.OID_AIDOF, AIDOF.NM_Serie, AIDOF.DM_Formulario " +
                      " WHERE AIDOF.CD_AIDOF = '" + resCTRC.getString ("DM_Cia_Aerea") + "'" +
                      " AND   AIDOF.NM_Serie = " + resCTRC.getLong ("OID_Unidade");
                }
                else {
                  sql = " SELECT AIDOF.NR_Proximo, AIDOF.NR_FINAL, AIDOF.OID_AIDOF, AIDOF.NM_Serie, AIDOF.DM_Formulario ";
                  sql += " FROM Parametros_Filiais, AIDOF ";
                  if ("M".equals (DM_Tipo_Documento)) { //minuta
                    sql += " WHERE Parametros_Filiais.OID_AIDOF_MINUTA = AIDOF.OID_AIDOF ";
                  }
                  if ("F".equals (DM_Tipo_Documento)) { //frete terc
                      sql += " WHERE Parametros_Filiais.OID_AIDOF_FRETE_TERCEIRO = AIDOF.OID_AIDOF ";
                    }
                  if ("MD".equals (DM_Tipo_Documento)) { //minuta
                    sql += " WHERE Parametros_Filiais.OID_AIDOF_MINUTA = AIDOF.OID_AIDOF ";
                  }
                  if ("N".equals (DM_Tipo_Documento)) { //nf serv
                    sql += " WHERE Parametros_Filiais.OID_AIDOF_Nota_Fiscal_Servico = AIDOF.OID_AIDOF ";
                  }
                  if ("Z".equals (DM_Tipo_Documento)) { //nf armazem devol
                    sql += " WHERE Parametros_Filiais.oid_aidof_nota_fiscal = AIDOF.OID_AIDOF ";
                  }
                  if ("T".equals (DM_Tipo_Documento)) { //act
                    sql += " WHERE Parametros_Filiais.OID_AIDOF_ACT = AIDOF.OID_AIDOF ";
                  }
                  if ("C".equals (DM_Tipo_Documento)) { //conhecimento
                    sql += " WHERE Parametros_Filiais.OID_AIDOF = AIDOF.OID_AIDOF ";
                  }
                  sql += " AND Parametros_Filiais.OID_Unidade = " + ed.getOID_Unidade ();
                }
                 System.out.println (sql);

                resTP = this.executasql.executarConsulta (sql.toString ());
                try {
                  while (resTP.next ()) {

                    // System.out.println ("ACHOU AIDOF ->>" + resTP.getString ("NR_Proximo"));

                    ed.setNM_Serie (resTP.getString ("NM_Serie"));
                    NR_Conhecimento = resTP.getLong ("NR_Proximo");

                    if ("M".equals (DM_Tipo_Documento)) {
                      NR_Minuta = resTP.getLong ("NR_Proximo");
                    }
                    if ("N".equals (DM_Tipo_Documento)) {
                      NR_Nota_Fiscal_Servico = resTP.getLong ("NR_Proximo");
                    }
                    if ("T".equals (DM_Tipo_Documento)) {
                      NR_ACT = resTP.getLong ("NR_Proximo");
                    }
                    if ("A".equals (DM_Tipo_Documento)) {
                      NR_AWB = resTP.getLong ("NR_Proximo");
                    }

                    Oid_AIDOF = resTP.getLong ("OID_AIDOF");
                    NR_Proximo = resTP.getLong ("NR_Proximo") + 1;
                    NR_Final = resTP.getLong ("NR_FINAL");

                    ///ROTINA PARA TESTAR NR IGUAIS
                     System.out.println ("testar->> " + NR_Conhecimento);

                    if (ed.getOID_Unidade () == 9999) { //nao esta ativa
                      int busca = 1;
                      while (busca < 10) {
                        sql = " SELECT oid_Conhecimento FROM Conhecimentos " +
                            " WHERE  Conhecimentos.oid_Unidade     = " + ed.getOID_Unidade () +
                            " AND    Conhecimentos.NR_Conhecimento = " + NR_Conhecimento;
                        // System.out.println ("Tentativa->> " + busca);
                        // System.out.println (sql);

                        resTP = this.executasql.executarConsulta (sql.toString ());
                        if (resTP.next ()) {
                          // System.out.println ("Acho igual->> " + NR_Conhecimento);
                          NR_Conhecimento++;
                          busca++;
                        }
                        else {
                          busca = 999;
                        }
                        if (busca == 10) {
                          // System.out.println (" testou 10 x->> " + NR_Conhecimento);
                          throw new Excecoes ("Verificar AIDOF Conhecimento mesmo numero!");
                        }
                      }
                    }
                     System.out.println ("FIM teste->> " + NR_Conhecimento);

                  }
                }
                finally {
                  util.closeResultset (resTP);
                }

                if (NR_Proximo > NR_Final) {

                  // System.out.println ("erro1->> " + NR_Conhecimento);

                  throw new Excecoes ("Nr pr�xima AIDOF maior que o n�mero final!");
                }

                sql = " UPDATE AIDOF SET NR_Proximo= " + (NR_Conhecimento + 1);
                sql += " WHERE OID_AIDOF = " + Oid_AIDOF;

                 System.out.println ("erro1->> " + NR_Conhecimento);

                executasql.executarUpdate (sql);
              }
            }
            else {
              NR_Conhecimento = ed.getNR_Conhecimento ();
              ed.setNM_Serie (resCTRC.getString ("NM_Serie"));
            }

            String Uni_Key = String.valueOf (1000 + Oid_AIDOF).substring (1 , 4) + "-" + String.valueOf (9000000 + NR_Conhecimento).substring (1 , 7) + "-" + ed.getNM_Serie ();

            if (!"MD".equals (DM_Tipo_Documento) && !"T".equals (DM_Tipo_Documento) && !"A".equals (DM_Tipo_Documento)) { //T=ACT
              sql = " update Conhecimentos set " +
                  " NR_Conhecimento = " + NR_Conhecimento + ", " +
                  " NR_DV = '" + calculaDV (String.valueOf (NR_Conhecimento)) + "', " +
                  " NR_Minuta = " + NR_Minuta + ", " +
                  " NR_Nota_Fiscal_Servico = " + NR_Nota_Fiscal_Servico + ", " +
                  " NR_ACT = " + NR_ACT + ", " +
                  " NR_Sort_Impressao = " + Nr_Sort_Impressao + ", " +
                  " NM_Serie = '" + ed.getNM_Serie () + "', " +
                  " Uni_key = '" + Uni_Key + "', " +
                  " DM_Impresso = 'S' ";
            }

            if ("MD".equals (DM_Tipo_Documento)) { //minuta despacho
              sql = " update Conhecimentos set " +
                  " DT_Stamp = '" + Data.getDataDMY () + "' ";
            }

            if ("A".equals (DM_Tipo_Documento)) { //A=AWB
              sql = " update Conhecimentos set " +
                  " NR_AWB = " + NR_AWB;
            }

            if ("T".equals (DM_Tipo_Documento)) { //T=ACT
              sql = " update Conhecimentos set " +
                  " NR_ACT = " + NR_ACT + ", " +
                  " DM_Tipo_Documento ='C' ";
            }

            if (!"A".equals (DM_Tipo_Documento)) {
              sql += " ,DT_Emissao = '" + DT_Emissao_Conhecimento + "' ";
            }


            sql += " WHERE Conhecimentos.oid_Conhecimento = '" + ed.getOID_Conhecimento () + "'";

            System.out.println ("update->> " + sql);

            executasql.executarUpdate (sql);

            ed.setDT_Emissao (Data.getDataDMY ());

            if (String.valueOf (ed.getNR_Conhecimento ()) != null &&
                !String.valueOf (ed.getNR_Conhecimento ()).equals ("0")) {
              ed.setNR_Conhecimento (NR_Conhecimento);
            }

            if ("S".equals (DM_Tipo_Conhecimento)) { // COLETA ELETRONICA
        		this.solicita_Coleta_Eletronica(ed);
        	}

            if ("C".equals(DM_Tipo_Documento) && "S".equals (parametro_FixoED.getDM_Gera_Lancamento_Contabil ())) {
              //faz os lancamentos contabeis do movimento
              //Lancamento_ContabilED lc = new Lancamento_ContabilED ();
              //esta dando erro na Bimex. Melhor fazer pela regeracao contabil.
          	    Movimento_Contabil_TempED edMovCtb = new Movimento_Contabil_TempED ();
        	    edMovCtb.setDt_Movimento (ed.getDT_Emissao());
        	    if (!edMovCtb.isPeriodo_Aberto ()) {
        	      throw new Excecoes ("Per�odo Cont�bil fechado !!! " + ed.getDT_Emissao());
        	    }else{
        	        new Lancamento_ContabilBD (this.executasql).deleta_CTB_Conhecimento (ed);
                	new Lancamento_ContabilBD (this.executasql).inclui_CTB_Conhecimento (ed);
            }
            }
            //Livro Fiscal
            if ("C".equals(DM_Tipo_Documento)) {
            	//C=Comum
            	if("S".equals(parametro_FixoED.getDM_Gera_Livro_Fiscal())){
	            	Livro_FiscalED livro = new Livro_FiscalED();
	            	livro.setOID_Conhecimento(ed.getOID_Conhecimento ());
	            	new Livro_FiscalBD(this.executasql).geraLivro_Fiscal_CTRC(livro);
          }
            }
            if (1==1) {
            	//atualiza ultimo movimento calc do cliente
	            String sqlAux = "SELECT oid_conhecimento " +
	            				" FROM movimentos_conhecimentos, tabelas_fretes " +
	            				" WHERE movimentos_conhecimentos.oid_tabela_frete = tabelas_fretes.oid_tabela_frete " +
	            				" AND   tabelas_fretes.oid_pessoa = '" + resCTRC.getString("oid_Pessoa_Pagador") + "'" +
	    						" AND   movimentos_conhecimentos.oid_conhecimento = '" + resCTRC.getString ("oid_Conhecimento") + "'";
	            ResultSet rsBloq = executasql.executarConsulta(sqlAux);
	            if(rsBloq.next()){
	            	executasql.executarUpdate("UPDATE clientes set dt_ultimo_movimento = '" + Data.getDataDMY() + "' where oid_cliente = '" + resCTRC.getString("oid_Pessoa_Pagador") + "'");
	            }
            }
          }
          else {
            //reimpressao
        	  System.out.println("REIMP SF 1");
        	  if (tipo_impressao.equals ("F")) {
	            	//imp por lote
        		  System.out.println("REIMP SF res OID_Usuario()=" + resCTRC.getLong("oid_Usuario"));
        		  if (ed.getOID_Usuario()==0){
      	    		  ed.setOID_Usuario(resCTRC.getLong("oid_Usuario"));
      	    	  }
        		  System.out.println("REIMP SF ed.getOID_Usuario()=" + ed.getOID_Usuario());
	      	      ConhecimentoRN Conhecimentorn = new ConhecimentoRN ();
	      	      if ("".equals(nm_usuario)){
	      	    	  sql =" SELECT NM_Usuario FROM USUARIOS WHERE oid_Usuario=" + ed.getOID_Usuario();
	      	          ResultSet resUsu = this.executasql.executarConsulta (sql);
	      	          if (resUsu.next ()) {
	      	        	nm_usuario=resUsu.getString("NM_Usuario");
	      	        }
	      	      }
	      	      System.out.println("REIMP SF nm_usuario=" + nm_usuario);
	    	      ed.setUsuario_Stamp(nm_usuario);
	    	      ed.setOID_Conhecimento (resCTRC.getString ("oid_Conhecimento"));
          }


            sql = " UPDATE Conhecimentos set " +
                " NR_Sort_Impressao = " + Nr_Sort_Impressao +
                " WHERE Conhecimentos.oid_Conhecimento = '" + resCTRC.getString ("oid_Conhecimento") + "'";
            // System.out.println ("update->> " + sql);

            executasql.executarUpdate (sql);
          }
        }
      }
      catch (SQLException e) {
        e.printStackTrace ();
        throw new Excecoes (e.getMessage () , e , getClass ().getName () , "imprime_Conhecimento(ConhecimentoED ed)");
      }
      catch (Exception e) {
        e.printStackTrace ();
        throw new Excecoes (e.getMessage () , e , getClass ().getName () , "imprime_Conhecimento(ConhecimentoED ed)");
      }
    }
    finally {
      util.closeResultset (resCTRC);
    }

    sql = "Select Conhecimentos.* ," +
        " Conhecimentos.oid_Produto as oid_Produto_Conhecimento, " +
        " Conhecimentos.VL_Nota_Fiscal as VL_Nota_Fiscal_CTRC, " +
        " Unidades.CD_Unidade, Unidades.oid_pessoa as oid_Pessoa_Unidade, " +
        " Cidade_Unidade.NM_Cidade as NM_Cidade_Unidade, " +
        " Cidade_CTRC_Origem.NM_Cidade as NM_Cidade_CTRC_Origem, " +
        " Cidade_CTRC_Origem.CD_Cidade as CD_Cidade_CTRC_Origem, " +
        " Estado_CTRC_Origem.CD_Estado as CD_Estado_CTRC_Origem, " +
        " Cidade_CTRC_Destino.NM_Cidade as NM_Cidade_CTRC_Destino, " +
        " Cidade_CTRC_Destino.CD_Cidade as CD_Cidade_CTRC_Destino, " +
        " Estado_CTRC_Destino.CD_Estado as CD_Estado_CTRC_Destino, " +
        " Produtos.NM_Produto, " +
        " Modal.CD_Modal, " +
        " Modal.NM_Modal, " +
        " Modal.DM_Tipo_Tabela_Frete, " +
        " Taxas.TX_Mensagem_Fiscal, " +
        " Taxas.TX_Mensagem_Fiscal_ICMS, " +
        " Taxas.NM_Campo_ICMS " +
        " FROM " +
        " Conhecimentos, Unidades, Modal, " +
        " Taxas, Pessoas, " +
        " Produtos, " +
        " cidades Cidade_Unidade, " +
        " cidades Cidade_CTRC_Origem, " +
        " estados Estado_CTRC_Origem, " +
        " regioes_estados Regiao_Estado_CTRC_Origem, " +
        " cidades Cidade_CTRC_Destino, " +
        " estados Estado_CTRC_Destino, " +
        " regioes_estados Regiao_Estado_CTRC_Destino " +
        " where " +
        " Conhecimentos.oid_Taxa = Taxas.oid_Taxa and" +
        " Conhecimentos.oid_modal   = Modal.oid_modal and" +
        " Conhecimentos.oid_produto = Produtos.oid_produto and" +
        " conhecimentos.oid_cidade = Cidade_CTRC_Origem.oid_cidade and" +
        " Conhecimentos.oid_unidade = Unidades.oid_unidade and" +
        " Unidades.oid_pessoa = Pessoas.oid_Pessoa and " +
        " Pessoas.oid_cidade = Cidade_Unidade.oid_Cidade and " +
        " Cidade_CTRC_Origem.oid_regiao_Estado = Regiao_Estado_CTRC_Origem.oid_regiao_estado and" +
        " Regiao_Estado_CTRC_Origem.oid_Estado = Estado_CTRC_Origem.oid_Estado and" +
        " conhecimentos.oid_cidade_Destino = Cidade_CTRC_Destino.oid_cidade and" +
        " Cidade_CTRC_Destino.oid_regiao_Estado = Regiao_Estado_CTRC_Destino.oid_regiao_estado and" +
        " Regiao_Estado_CTRC_Destino.oid_Estado = Estado_CTRC_Destino.oid_Estado ";

    if (tipo_impressao.equals ("I")) {
      sql += condicao_pesquisa;
    }
    else {
      sql += " AND (Conhecimentos.VL_Total_Frete > 0 OR " +
          "      Conhecimentos.DM_Situacao = 'C'  OR " + // usa o filtro p/ N ctos
          "      Conhecimentos.DM_Tipo_Documento = 'Z' )  " + // usa o filtro p/ N ctos
          "  AND Conhecimentos.Nr_Sort_Impressao = '" + Nr_Sort_Impressao + "'" +
          " ORDER by conhecimentos.oid_Usuario ";
      	if ("LPT".equals(LPT_PDF)) {
            sql +=", conhecimentos.oid DESC ";
      	}else {
            sql +=", conhecimentos.oid ASC ";
      	}
    }



    // System.out.println ("IMPRESS CTO >> " + sql);
    return this.executasql.executarConsulta (sql);
  }

  public byte[] imprime_Conhecimento (ConhecimentoED ed) throws Excecoes {
    ResultSet res = null;
    try {
    	System.out.println ("IMPRIME CONHECIMENTO BD>>>>>>>>>>");

      res = imprime_ConhecimentoComum (ed, "PDF");
      return new ConhecimentoRL ().imprime_Conhecimento (res , executasql , ed);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "imprime_ConhecimentoMatricial(ResultSet res, ExecutaSQL sql)");
    }
    finally {
      util.closeResultset (res);
    }
  }

  public void imprime_ConhecimentoJasper (ConhecimentoED ed , HttpServletResponse response) throws Excecoes {
    ResultSet res = null;
    try {
      res = imprime_ConhecimentoComum (ed, "PDF");

      new ConhecimentoRL ().imprime_ConhecimentoJasper (res , executasql , ed , response);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "imprime_ConhecimentoMatricial(ResultSet res, ExecutaSQL sql)");
    }
    finally {
      util.closeResultset (res);
    }
  }

  public String imprime_ConhecimentoMatricial (ConhecimentoED ed) throws Excecoes {
    ResultSet res = null;
    try {

      res = imprime_ConhecimentoComum (ed, "LPT");

      return new ConhecimentoRL ().imprime_ConhecimentoMatricial (res , executasql , ed);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "imprime_ConhecimentoMatricial(ResultSet res, ExecutaSQL sql)");
    }
    finally {
      util.closeResultset (res);
    }
  }

  public ConhecimentoED cobranca_Vista (ConhecimentoED ed) throws Excecoes {

    String sql = null;
    ResultSet rs = null;
    ConhecimentoED conED = new ConhecimentoED ();

    try {

      sql = " SELECT DM_TIPO_COBRANCA, DM_CREDITO " +
          " FROM CLIENTES " +
          " WHERE OID_Cliente = '" + ed.getOID_Pessoa_Pagador () + "'";
      rs = null;
      rs = this.executasql.executarConsulta (sql);

      while (rs.next ()) {
        if (rs.getString ("DM_TIPO_COBRANCA") != null && rs.getString ("DM_TIPO_COBRANCA").equals ("V")) {
          ed.setDM_Situacao ("V");
          ed.setDM_Tipo_Conhecimento ("6");
        }
        if (rs.getString ("DM_CREDITO") != null && rs.getString ("DM_CREDITO").equals ("S")) {
          Excecoes exc = new Excecoes ();
          throw exc;
        }
      }

      if (ed.getOID_Pessoa_Pagador ().length () == 11 && !ed.getDM_Situacao ().equals ("V")) {
        ed.setDM_Situacao ("V");
        ed.setDM_Tipo_Conhecimento ("6");
      }

      // Foi utilizado o NR_PROXIMO_BORDERO para n�o ser criado outro
      // campo na tabela
      long NR_Proximo_Recibo = 0;
      sql = " SELECT NR_PROXIMO_BORDERO " +
          " FROM Parametros_Filiais " +
          " WHERE OID_Unidade = " + ed.getOID_Unidade ();
      rs = null;
      rs = this.executasql.executarConsulta (sql);

      while (rs.next ()) {
        NR_Proximo_Recibo = rs.getLong ("NR_PROXIMO_BORDERO");
      }
      NR_Proximo_Recibo = NR_Proximo_Recibo + 1;

      sql = " update Parametros_Filiais set NR_PROXIMO_BORDERO= " + NR_Proximo_Recibo +
          " where OID_Unidade = " + ed.getOID_Unidade ();
      int u = executasql.executarUpdate (sql);
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMetodo ("inclui");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return conED;
  }

  public String verificaCredito (String Pessoa_Pagador) throws Excecoes {
    String OID_Cliente_Pagador = "";
    String sql = " SELECT  OID_VENDEDOR, DM_CREDITO, DM_Tipo_Cobranca, OID_Cliente_Pagador " +
        " FROM CLIENTES " +
        " WHERE OID_Cliente = '" + Pessoa_Pagador + "'";

    ResultSet rs = null;
    ResultSet rs2 = null;
    rs = this.executasql.executarConsulta (sql);
    Situacao_Cliente = "CADASTRO_OK";
    Tipo_Cobranca = "B";
    if (parametro_FixoED.getDM_Exige_Pagador_Cliente ().equals ("S")) {
      Situacao_Cliente = "NAO_CADASTRADO";
    }
    try {
      if (rs.next ()) {
        OID_Vendedor = rs.getString ("OID_Vendedor");
        OID_Cliente_Pagador = rs.getString ("OID_Cliente_Pagador");
        if (OID_Cliente_Pagador != null && !OID_Cliente_Pagador.equals ("null") && !OID_Cliente_Pagador.equals ("")) {
          sql = " SELECT  OID_Cliente From Clientes WHERE OID_Cliente = '" + OID_Cliente_Pagador + "'";
          rs2 = this.executasql.executarConsulta (sql);
          if (rs2.next ()) {
            Pessoa_Pagador = rs2.getString ("OID_Cliente");
          }
        }
        Situacao_Cliente = "CADASTRO_OK";
        if (rs.getString ("DM_CREDITO") != null && rs.getString ("DM_CREDITO").equals ("S")) {
          Situacao_Cliente = "SEM_CREDITO";
          // System.out.println ("inclui cto verif cred-> Cliente pagador com Cr�dito/Cadastro BLOQUEADO ");
          throw new Mensagens ("Cliente pagador com Cr�dito/Cadastro BLOQUEADO");
        }
        if (rs.getString ("DM_Tipo_Cobranca") != null) {
          Tipo_Cobranca = rs.getString ("DM_Tipo_Cobranca");
        }
        if (Pessoa_Pagador.length () == 11) {
          Tipo_Cobranca = "V";
        }
      }
      else if ("S".equals (Parametro_FixoED.getInstancia ().getDM_Exige_Pagador_Cliente ())) {
        // System.out.println ("inclui cto verif cred-> Pagador informado n�o est� cadastrado como CLIENTE ");
        throw new Mensagens ("Pagador informado n�o est� cadastrado como CLIENTE");
      }
      return Pessoa_Pagador;
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "verificaCredito (String Pessoa_Pagador)");
    }
  }

  public String verificaEntregador_Modal (long oid_Modal) throws Excecoes {
    String oid_Pessoa="";
    String sql = " SELECT Pessoas.oid_Pessoa " +
        " FROM  Pessoas, Modal" +
        " WHERE Pessoas.oid_Pessoa = Modal.oid_Pessoa " +
        " AND   Modal.oid_Modal =" + oid_Modal;

    ResultSet rs = this.executasql.executarConsulta (sql);
    try {
      if (rs.next ()) {
        oid_Pessoa = rs.getString ("oid_Pessoa");
      }
      return oid_Pessoa;
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "verificaEntregador_Modal (String Pessoa_Pagador)");
    }
  }

  public void alteraNM_Lote_Faturamento (ConhecimentoED dados) throws Excecoes {
    String sql =
        " update Conhecimentos " +
        " set nm_lote_faturamento = " + JavaUtil.getSQLString (dados.getNM_Lote_Faturamento ()) +
        " where oid_Conhecimento = " + JavaUtil.getSQLString (dados.getOID_Conhecimento ());
    try {
      executasql.executarUpdate (sql);
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "alteraNM_Lote_Faturamento(ConhecimentoED dados)");
    }
  }

  private List listaRelICMSCliente (ConhecimentoED ed) throws Excecoes {
    List toReturn = new ArrayList ();
    String sql =
        " select Conhecimentos.oid_Pessoa_Pagador " +
        "       ,Pessoas.NM_Fantasia as NM_Pessoa_Pagador " +
        "       ,Conhecimentos.VL_Total_Frete " +
        "       ,Conhecimentos.VL_Base_Calculo_ICMS " +
        "       ,Conhecimentos.VL_ICMS " +
        " from Conhecimentos " +
        "      inner join Unidades " +
        "        on Conhecimentos.oid_Unidade = Unidades.oid_Unidade " +
        "      inner join Pessoas " +
        "        on Conhecimentos.oid_Pessoa_Pagador = Pessoas.oid_Pessoa " +
        " where Conhecimentos.DM_Impresso = 'S' " +
        "   and Conhecimentos.DM_Situacao <> 'C' ";
    if (ed.getOID_Empresa () > 0) {
      sql += " and Unidades.oid_Empresa = " + ed.getOID_Empresa ();
    }
    if (ed.getOID_Unidade () > 0) {
      sql += " and Conhecimentos.oid_Unidade = " + ed.getOID_Unidade ();
    }
    if (util.doValida (ed.getOID_Pessoa_Pagador ())) {
      sql += " and Conhecimentos.oid_Pessoa_Pagador = " + util.getSQLString (ed.getOID_Pessoa_Pagador ());
    }
    if (util.doValida (ed.getDt_Emissao_Inicial ())) {
      sql += " and Conhecimentos.DT_Emissao >= " + JavaUtil.getSQLDate (ed.getDt_Emissao_Inicial ());
    }
    if (util.doValida (ed.getDt_Emissao_Final ())) {
      sql += " and Conhecimentos.DT_Emissao <= " + JavaUtil.getSQLDate (ed.getDt_Emissao_Final ());
    }
    sql += " order by Conhecimentos.oid_Pessoa_Pagador, Pessoas.NM_Fantasia ";

    ResultSet res = executasql.executarConsulta (sql);
    try {
      try {
        while (res.next ()) {
          ConhecimentoED edVolta = new ConhecimentoED ();
          edVolta.setOID_Pessoa_Pagador (res.getString ("oid_Pessoa_Pagador"));
          edVolta.setNM_Pessoa_Pagador (res.getString ("NM_Pessoa_Pagador"));
          edVolta.setVL_TOTAL_FRETE (res.getDouble ("VL_Total_Frete"));
          edVolta.setVL_ICMS (res.getDouble ("VL_ICMS"));
          if (edVolta.getVL_ICMS () > 0) {
            edVolta.setVL_BASE_CALCULO_ICMS (res.getDouble ("VL_BASE_CALCULO_ICMS"));
          }
          else {
            edVolta.setVL_BASE_CALCULO_ICMS (0);
          }
          edVolta.setVL_Frete (edVolta.getVL_TOTAL_FRETE () - edVolta.getVL_ICMS ());
          toReturn.add (edVolta);
        }
        return toReturn;
      }
      catch (SQLException e) {
        throw new Excecoes (e.getMessage () , e , getClass ().getName () , "listaImpressaoConhecimentoAereo(Conhecimento_AereoImpressaoFiltroED ed)");
      }
    }
    finally {
      util.closeResultset (res);
    }
  }

  public void relICMSCliente (ConhecimentoED ed) throws Excecoes {
    List lista = listaRelICMSCliente (ed);

    List listaSumarizada = new ArrayList ();
    String clienteAnterior = "";
    ConhecimentoED edTotaliza = new ConhecimentoED ();
    Iterator iterator = lista.iterator ();
    while (iterator.hasNext ()) {
      ConhecimentoED edVolta = (ConhecimentoED) iterator.next ();
      if (util.doValida (clienteAnterior) && (!clienteAnterior.equals (edVolta.getOID_Pessoa_Pagador ()))) {
        listaSumarizada.add (edTotaliza);
        edTotaliza = new ConhecimentoED ();
      }
      edTotaliza.setOID_Pessoa_Pagador (edVolta.getOID_Pessoa_Pagador ());
      edTotaliza.setNM_Pessoa_Pagador (edVolta.getNM_Pessoa_Pagador ());
      edTotaliza.setVL_TOTAL_FRETE (edTotaliza.getVL_TOTAL_FRETE () + edVolta.getVL_TOTAL_FRETE ());
      edTotaliza.setVL_ICMS (edTotaliza.getVL_ICMS () + edVolta.getVL_ICMS ());
      edTotaliza.setVL_BASE_CALCULO_ICMS (edTotaliza.getVL_BASE_CALCULO_ICMS () + edVolta.getVL_BASE_CALCULO_ICMS ());
      edTotaliza.setVL_Frete (edTotaliza.getVL_Frete () + edVolta.getVL_Frete ());
      clienteAnterior = edVolta.getOID_Pessoa_Pagador ();
    }
    if (util.doValida (edTotaliza.getOID_Pessoa_Pagador ())) {
      listaSumarizada.add (edTotaliza);
    }
    Collections.sort (listaSumarizada , new Comparator () {
      public int compare (Object o1 , Object o2) {
        int r = 0;
        ConhecimentoED ed1 = (ConhecimentoED) o1;
        ConhecimentoED ed2 = (ConhecimentoED) o2;
        return (ed1.getOID_Pessoa_Pagador ().compareTo (ed2.getOID_Pessoa_Pagador ()));
      }
    });
    ed.setLista (listaSumarizada);
    new JasperRL (ed).geraRelatorio ();
  }

  public List listaRelConhecimentoEntrega (ConhecimentoED ed) throws Excecoes {
    String sql =
        " select CON.oid_Conhecimento " +
        "       ,CON.NR_Conhecimento " +
        "       ,CON.DT_Emissao " +
        "       ,CON.DT_Previsao_Entrega " +
        "       ,CON.DT_Entrega " +
        "       ,CON.NR_Volumes " +
        "       ,CON.VL_Total_Frete " +
        "       ,CON.VL_Nota_Fiscal " +
        "       ,UNI.oid_Unidade " +
        "       ,UNI.CD_Unidade " +
        "       ,PES_UNI.NM_Fantasia as NM_Unidade " +
        "       ,PES_REM.oid_Pessoa as oid_Pessoa_Remetente " +
        "       ,PES_REM.NM_Fantasia as NM_Pessoa_Remetente " +
        "       ,PES_DES.oid_Pessoa as oid_Pessoa_Destinatario " +
        "       ,PES_DES.NM_Fantasia as NM_Pessoa_Destinatario " +
        "       ,CID_DES.NM_Cidade as NM_Cidade_Destino " +
        " from Conhecimentos CON " +
        "     ,Unidades UNI " +
        "     ,Pessoas PES_REM " +
        "     ,Pessoas PES_DES " +
        "     ,Cidades CID_DES " +
        "     ,Pessoas PES_UNI " +
        " where CON.oid_Unidade = UNI.oid_Unidade " +
        "   and UNI.oid_Pessoa = PES_UNI.oid_Pessoa " +
        "   and CON.oid_Pessoa = PES_REM.oid_Pessoa " +
        "   and CON.oid_Pessoa_Destinatario = PES_DES.oid_Pessoa " +
        "   and CON.oid_Cidade_Destino = CID_DES.oid_Cidade " +
        "   and not CON.DT_Entrega is null ";
    if (ed.getOID_Empresa () > 0) {
      sql += " and UNI.oid_Empresa = " + ed.getOID_Empresa ();
    }
    if (ed.getOID_Unidade () > 0) {
      sql += " and UNI.oid_Unidade = " + ed.getOID_Unidade ();
    }
    if (util.doValida (ed.getDt_Emissao_Inicial ())) {
      sql += " and CON.DT_Emissao >= " + util.getSQLString (ed.getDt_Emissao_Inicial ());
    }
    if (util.doValida (ed.getDt_Emissao_Final ())) {
      sql += " and CON.DT_Emissao <= " + util.getSQLString (ed.getDt_Emissao_Final ());
    }
    sql += " order by UNI.CD_Unidade " +
        "          ,CON.NR_Conhecimento ";
    ///// System.out.println("ConhecimentoBD.listaRelConhecimentoEntrega()#sql: \n" + sql);
    ResultSet res = executasql.executarConsulta (sql);
    try {
      List lista = new ArrayList ();
      try {
        while (res.next ()) {
          ConhecimentoED edVolta = new ConhecimentoED ();
          edVolta.setOID_Conhecimento (res.getString ("oid_Conhecimento"));
          edVolta.setNR_Conhecimento (res.getLong ("NR_Conhecimento"));
          edVolta.setDT_Emissao (FormataData.formataDataBT (res.getDate ("DT_Emissao")));
          edVolta.setDT_Previsao_Entrega (FormataData.formataDataBT (res.getDate ("DT_Previsao_Entrega")));
          edVolta.setDT_Entrega (FormataData.formataDataBT (res.getDate ("DT_Entrega")));
          edVolta.setNR_Volumes (res.getDouble ("NR_Volumes"));
          edVolta.setVL_TOTAL_FRETE (res.getDouble ("VL_Total_Frete"));
          edVolta.setVL_Nota_Fiscal (res.getDouble ("VL_Nota_Fiscal"));
          edVolta.setOID_Unidade (res.getLong ("oid_Unidade"));
          edVolta.setCD_Unidade (res.getString ("CD_Unidade"));
          edVolta.setNM_Fantasia_Unidade (res.getString ("NM_Unidade"));
          edVolta.setOID_Pessoa (res.getString ("oid_Pessoa_Remetente"));
          edVolta.setNM_Pessoa_Remetente (res.getString ("NM_Pessoa_Remetente"));
          edVolta.setOID_Pessoa_Destinatario (res.getString ("oid_Pessoa_Destinatario"));
          edVolta.setNM_Pessoa_Destinatario (res.getString ("NM_Pessoa_Destinatario"));
          edVolta.setNM_Cidade_Destinatario (res.getString ("NM_Cidade_Destino"));

          lista.add (edVolta);
        }
      }
      catch (SQLException e) {
        throw new Excecoes (e.getMessage () , e , getClass ().getName () , "listaRelConhecimentoEntrega(ConhecimentoED ed)");
      }
      return lista;
    }
    finally {
      util.closeResultset (res);
    }
  }

  public void relConhecimentoEntrega (ConhecimentoED ed) throws Excecoes {
    ed.setLista (listaRelConhecimentoEntrega (ed));
    new JasperRL (ed).geraRelatorio ();
  }

  public boolean existeConhecimento (ConhecimentoED ed) throws Excecoes {
    String sql =
        " select count(*) as qtd " +
        " from  Conhecimentos CON " +
        " where 1 = 1 ";
    if (util.doValida (ed.getOID_Conhecimento ())) {
      sql += " and CON.oid_Conhecimento = " + util.getSQLString (ed.getOID_Conhecimento ());
    }
    if (ed.getNR_Conhecimento () > 0) {
      sql += " and CON.NR_Conhecimento = " + ed.getNR_Conhecimento ();
    }
    if (ed.getOID_Unidade () > 0) {
      sql += " and CON.OID_Unidade = " + ed.getOID_Unidade ();
    }
    if (util.doValida (ed.getNM_Serie ())) {
      sql += " and CON.NM_Serie = " + util.getSQLString (ed.getNM_Serie ());
    }
    ResultSet res = executasql.executarConsulta (sql);
    try {
      try {
        if (res.next ()) {
          return res.getInt ("qtd") > 0;
        }
        else {
          return false;
        }
      }
      catch (SQLException e) {
        throw new Excecoes (e.getMessage () , e , getClass ().getName () , "existeConhecimento(ConhecimentoED ed)");
      }
    }
    finally {
      util.closeResultset (res);
    }
  }

  public void geraRelDAC (ConhecimentoED ed , HttpServletResponse response) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();

    try {

      sql = " select count(OID_Conhecimento) as awb, sum(vl_total_Frete) as frete, sum(nr_peso) as peso, oid_Unidade_Agente from conhecimentos " +
          " where Conhecimentos.DM_Tipo_Documento = 'A'";

      if (String.valueOf (ed.getNR_Conhecimento ()) != null && !String.valueOf (ed.getNR_Conhecimento ()).equals ("0")) {
        sql += " and Conhecimentos.NR_Conhecimento = " + ed.getNR_Conhecimento ();
      }

      if (String.valueOf (ed.getOID_Unidade ()) != null && !String.valueOf (ed.getOID_Unidade ()).equals ("0")) {
        sql += " and Conhecimentos.OID_Unidade = " + ed.getOID_Unidade ();
      }
      if (String.valueOf (ed.getOID_Pessoa ()) != null && !String.valueOf (ed.getOID_Pessoa ()).equals ("")
          && !String.valueOf (ed.getOID_Pessoa ()).equals ("null")) {
        sql += " and Conhecimentos.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
      }
      if (String.valueOf (ed.getOID_Pessoa_Destinatario ()) != null && !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("")
          && !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("null")) {
        sql += " and Conhecimentos.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario () + "'";
      }
      if (String.valueOf (ed.getOID_Pessoa_Consignatario ()) != null && !String.valueOf (ed.getOID_Pessoa_Consignatario ()).equals ("")
          && !String.valueOf (ed.getOID_Pessoa_Consignatario ()).equals ("null")) {
        sql += " and Conhecimentos.OID_Pessoa_Consignatario = '" + ed.getOID_Pessoa_Consignatario () + "'";
      }
      if (String.valueOf (ed.getDT_Emissao ()) != null && !String.valueOf (ed.getDT_Emissao ()).equals ("") && !String.valueOf (ed.getDT_Emissao ()).equals ("null")) {
        sql += " and Conhecimentos.DT_Emissao = '" + ed.getDT_Emissao () + "'";
      }

      if (String.valueOf (ed.getDt_Emissao_Inicial ()) != null && !String.valueOf (ed.getDt_Emissao_Inicial ()).equals ("") && !String.valueOf (ed.getDt_Emissao_Inicial ()).equals ("null")) {
        sql += " and Conhecimentos.DT_Emissao >= '" + ed.getDt_Emissao_Inicial () + "'";
      }

      if (String.valueOf (ed.getDt_Emissao_Final ()) != null && !String.valueOf (ed.getDt_Emissao_Final ()).equals ("") && !String.valueOf (ed.getDt_Emissao_Final ()).equals ("null")) {
        sql += " and Conhecimentos.DT_Emissao <= '" + ed.getDt_Emissao_Final () + "'";
      }

      if (String.valueOf (ed.getOID_Veiculo ()) != null && !String.valueOf (ed.getOID_Veiculo ()).equals ("") && !String.valueOf (ed.getOID_Veiculo ()).equals ("null")) {
        sql += " and Conhecimentos.OID_Veiculo = '" + ed.getOID_Veiculo () + "'";
      }

      if (String.valueOf (ed.getOID_Coleta ()) != null && !String.valueOf (ed.getOID_Coleta ()).equals ("") && !String.valueOf (ed.getOID_Coleta ()).equals ("0")) {
        sql += " and Conhecimentos.OID_Coleta = '" + ed.getOID_Coleta () + "'";
      }

      sql += " group by oid_Unidade_Agente ";

      //// System.out.println(sql);
      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);

      //popula

      FormataDataBean DataFormatada = new FormataDataBean ();

      while (res.next ()) {
        ConhecimentoED edVolta = new ConhecimentoED ();

        edVolta.setOid_Unidade_Agente (res.getInt ("oid_unidade_Agente"));
        edVolta.setNR_Peso (res.getDouble ("peso"));
        edVolta.setVL_Frete (res.getDouble ("frete"));
        edVolta.setNR_Conhecimento (res.getLong ("awb"));

        String cd_Agente = "";
        if (edVolta.getOid_Unidade_Agente () == 1) {
          cd_Agente = "G3";
        }
        else if (edVolta.getOid_Unidade_Agente () == 2) {
          cd_Agente = "JJ";
        }
        else if (edVolta.getOid_Unidade_Agente () == 3) {
          cd_Agente = "RG";
        }
        edVolta.setCD_Unidade_Agente (cd_Agente);

        list.add (edVolta);

        new ConhecimentoRL ().geraRelDAC (ed , list , response);
      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "lista(ConhecimentoED ed)");
    }
    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "lista(ConhecimentoED ed)");
    }

  }

  public ConhecimentoED atualiza_Minuta (ConhecimentoED ed) throws Excecoes {

    String sql = null;
    long valOid = 0;
    long NR_Proximo = 0;
    long NR_Final = 0;

    try {
      sql = " SELECT AIDOF.NR_Proximo, AIDOF.NR_FINAL, AIDOF.OID_AIDOF, AIDOF.NM_Serie ";
      sql += " FROM AIDOF ";
      sql += " WHERE AIDOF.NM_Serie = 'CN1' ";

      ResultSet rs = this.executasql.executarConsulta (sql);
      try {
        while (rs.next ()) {
          ed.setNM_Serie (rs.getString ("NM_Serie"));
          ed.setNR_Conhecimento (rs.getLong ("NR_Proximo"));
          valOid = rs.getLong ("OID_AIDOF");
          NR_Proximo = rs.getLong ("NR_Proximo") + 1;
          NR_Final = rs.getLong ("NR_FINAL");
        }
      }
      finally {
        util.closeResultset (rs);
      }

      if (NR_Proximo > NR_Final) {
        throw new Mensagens ("AIDOF sem numera��o dispon�vel");
      }

      sql = " UPDATE AIDOF SET NR_Proximo= " + (ed.getNR_Conhecimento () + 1);
      sql += " WHERE OID_AIDOF = " + valOid;
      executasql.executarUpdate (sql);

      sql = " UPDATE Conhecimentos SET  NR_Conhecimento=" + ed.getNR_Conhecimento () +
          " ,DM_Impresso ='N' " +
          " ,DM_Tipo_Documento='C' " +
          " WHERE oid_Conhecimento ='" + ed.getOID_Conhecimento () + "'";

      // System.out.print (sql);

      executasql.executarUpdate (sql);

    }

    catch (SQLException e) {
      e.printStackTrace ();
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "inclui(ConhecimentoED ed)");
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "inclui(ConhecimentoED ed)");
    }
    return ed;
  }

  public ConhecimentoED copia_Conhecimento (ConhecimentoED ed) throws Excecoes {

    ConhecimentoED conED = new ConhecimentoED ();
    conED = this.getByRecord (ed);
    //conED.setDM_Tipo_Documento(ed.getDM_Tipo_Documento());
    try {
      conED.setNR_Conhecimento (0);
      conED.setNR_ACT (0);
      conED.setNR_Duplicata (0);
      conED.setDM_Impresso ("N");
      conED.setDT_Emissao (Data.getDataDMY ());
      conED.setVL_FRETE_PESO (0);
      conED.setVL_FRETE_VALOR (0);
      conED.setVL_ICMS (0);
      conED.setVL_PEDAGIO (0);
      conED.setVL_TOTAL_FRETE (0);
      conED.setVL_BASE_CALCULO_ICMS (0);
      conED.setNR_Peso(0);
      conED.setNR_Peso_Cubado(0);
      conED.setNR_Volumes(0);
      conED.setVL_Nota_Fiscal(0);
      conED.setOID_Nota_Fiscal ("");
      conED = this.inclui (conED);



    }
    catch (Exception e) {
      e.printStackTrace ();
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "inclui(ConhecimentoED ed)");
    }
    return conED;
  }

  public ConhecimentoED atualiza_MinutaParaConhecimento (ConhecimentoED ed) throws Excecoes {

	    ConhecimentoED minutaED = new ConhecimentoED ();
	    ConhecimentoED ctoED = new ConhecimentoED ();
	    minutaED = this.getByRecord (ed);
	    ctoED = this.getByRecord (ed);

	    long oid_Unidade_Conhecimento=minutaED.getOID_Unidade();
	    String sql=null;

	    try {
	    	ctoED.setNR_Conhecimento (0);
	    	ctoED.setNR_ACT (0);
	    	ctoED.setNR_Duplicata (0);
	    	ctoED.setDM_Impresso ("N");
	    	ctoED.setDT_Emissao (Data.getDataDMY ());
	    	ctoED.setDM_Tipo_Documento("C");
	    	ctoED = this.inclui (ctoED);
	    	if (ctoED.getOID_Conhecimento() != null && ctoED.getOID_Conhecimento().length()>4) {
		      sql =" SELECT oid_Unidade_Conhecimento FROM Unidades WHERE oid_Unidade=" + minutaED.getOID_Unidade();
		      ResultSet rs = this.executasql.executarConsulta (sql);
		      try {
		        if (rs.next ()) {
		        	if (rs.getLong("oid_Unidade_Conhecimento")>0) {
		        		oid_Unidade_Conhecimento=rs.getLong("oid_Unidade_Conhecimento");
		        	}
		        }
		      }
		      finally {
		        util.closeResultset (rs);
		      }

		      sql=" UPDATE Conhecimentos SET oid_Unidade = " + oid_Unidade_Conhecimento + ", NR_Minuta=" + minutaED.getNR_Minuta() + " ,oid_Veiculo='', oid_Carreta='' WHERE oid_Conhecimento='"+ ctoED.getOID_Conhecimento() + "'";
		      executasql.executarUpdate (sql);



	          sql = " SELECT * " +
	          " FROM  Conhecimentos_Notas_Fiscais " +
	          " WHERE OID_Conhecimento =  '" + minutaED.getOID_Conhecimento () + "'";
	          rs = this.executasql.executarConsulta (sql);
		      try {
			        while (rs.next ()) {
					      Conhecimento_Nota_FiscalED conhecimento_Nota_FiscalED = new Conhecimento_Nota_FiscalED ();
					      conhecimento_Nota_FiscalED.setOID_Conhecimento(ctoED.getOID_Conhecimento());
					      conhecimento_Nota_FiscalED.setOID_Nota_Fiscal(rs.getString("OID_Nota_Fiscal"));
					      conhecimento_Nota_FiscalED.setDT_Conhecimento_Nota_Fiscal (Data.getDataDMY());
					      conhecimento_Nota_FiscalED.setHR_Conhecimento_Nota_Fiscal ("");
					      conhecimento_Nota_FiscalED.setDM_Tipo_Conhecimento ("M");
					      conhecimento_Nota_FiscalED.setDm_Stamp("");
					      conhecimento_Nota_FiscalED.setDt_stamp(Data.getDataDMY());
					      conhecimento_Nota_FiscalED.setUsuario_Stamp("");
				          Conhecimento_Nota_FiscalBD = new Conhecimento_Nota_FiscalBD (this.executasql);
				          Conhecimento_Nota_FiscalBD.inclui (conhecimento_Nota_FiscalED);
			      }
			   }
			   finally {
			      util.closeResultset (rs);
			   }


	          sql = " SELECT * " +
	          " FROM  Movimentos_Conhecimentos " +
	          " WHERE OID_Conhecimento =  '" + minutaED.getOID_Conhecimento () + "'";
	          rs = this.executasql.executarConsulta (sql);
		      try {
			        while (rs.next ()) {
			          	  Movimento_ConhecimentoED edMovimento_Conhecimento = new Movimento_ConhecimentoED ();
			          	  edMovimento_Conhecimento.setOID_Conhecimento(ctoED.getOID_Conhecimento());
				          edMovimento_Conhecimento.setOID_Tipo_Movimento (rs.getLong("oid_tipo_Movimento"));
				          edMovimento_Conhecimento.setVL_Movimento (rs.getDouble("vl_movimento"));
				          edMovimento_Conhecimento.setVL_Tarifa (0);
				          edMovimento_Conhecimento.setDT_Movimento_Conhecimento (Data.getDataDMY ());
				          edMovimento_Conhecimento.setHR_Movimento_Conhecimento (Data.getHoraHM ());
				          edMovimento_Conhecimento.setDM_Tipo_Movimento ("G");
				          edMovimento_Conhecimento.setDM_Lancado_Gerado ("L");
				          edMovimento_Conhecimento.setOID_Tabela_Frete (rs.getString("OID_Tabela_Frete"));
				          edMovimento_Conhecimento.setDM_Origem_Lancamento("COPIA MIN CTO");
				          edMovimento_Conhecimento.setNM_Pessoa_Entrega ("");
				          Movimento_ConhecimentoBD = new Movimento_ConhecimentoBD (this.executasql);
				          Movimento_ConhecimentoBD.inclui (edMovimento_Conhecimento);
			        }
			      }
			      finally {
			        util.closeResultset (rs);
			      }

		      }

	    }
	    catch (Exception e) {
	      e.printStackTrace ();
	      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "atualiza_MinutaParaConhecimento(ConhecimentoED ed)");
	    }
	    return ctoED;
	  }

  public static String calculaDV (String nr_Conhecimento) throws Excecoes {
    String dv = "";
    String aux = String.valueOf (System.currentTimeMillis ()).toString ();
    nr_Conhecimento = JavaUtil.LFill (nr_Conhecimento , 6 , true);
    dv = aux.substring (aux.length () - 2 , aux.length () - 1) + nr_Conhecimento.substring (5 , 6) + aux.substring (aux.length () - 1 , aux.length ());
    return dv;
  }

  public ArrayList relConhecimentoDisponivelRota (ConhecimentoED ed) throws Excecoes {

	    String sql = null;
	    long valOid = 0;
	    String chave = null;
	    ArrayList lista = new ArrayList();
	    try {

	        sql = "SELECT  Conhecimentos.oid_Conhecimento, Conhecimentos.NR_Conhecimento, ";
	        sql += " Conhecimentos.NR_Peso, ";
	        sql += " Conhecimentos.NR_Volumes, ";
	        sql += " Conhecimentos.VL_Total_Frete, ";
	        sql += " Conhecimentos.VL_Nota_Fiscal, conhecimentos.dt_emissao, ";
	        sql += " Pessoas.nm_razao_social ";
	        sql += " FROM Conhecimentos, pessoas ";
	        sql += " WHERE  Conhecimentos.VL_Total_Frete >0 " +
	            " AND Conhecimentos.DM_Impresso ='S'  " +
	            " AND Conhecimentos.DM_Situacao <>'C'  " +
	            " AND conhecimentos.oid_pessoa_destinatario = pessoas.oid_pessoa ";

	        if(JavaUtil.doValida(ed.getDt_Emissao_Inicial())){
	        	sql += " AND conhecimentos.dt_emissao >= '" + ed.getDt_Emissao_Inicial() + "'";
	        }
	        if(JavaUtil.doValida(ed.getDt_Emissao_Final())){
	        	sql += " AND conhecimentos.dt_emissao <= '" + ed.getDt_Emissao_Final() + "'";
	        }
	        if(JavaUtil.doValida(ed.getCD_Rota_Entrega())){
	        	sql += " AND conhecimentos.cd_rota_entrega = '" + ed.getCD_Rota_Entrega() + "'";
	        }
	        if(JavaUtil.doValida(ed.getOID_Pessoa())){
	        	sql += " AND conhecimentos.oid_Pessoa = '" + ed.getOID_Pessoa() + "'";
	        }
	        if(JavaUtil.doValida(String.valueOf(ed.getOID_Unidade()))){
	        	sql += " AND conhecimentos.oid_unidade = " + ed.getOID_Unidade();
	        }

	        sql += " order by conhecimentos.nr_conhecimento, conhecimentos.dt_emissao ";
System.out.println(sql);
	        ResultSet res = null;
	        ResultSet res2 = null;
	        res = this.executasql.executarConsulta (sql);

	        while (res.next ()) {
	          int qt_cto = 0;
	          sql = " SELECT  count (oid_Conhecimento) as qt_cto " +
	              " FROM Viagens " +
	              " WHERE Viagens.oid_Conhecimento ='" + res.getString ("oid_Conhecimento") + "'";
	          res2 = this.executasql.executarConsulta (sql);
	          while (res2.next ()) {
	            qt_cto = res2.getInt ("qt_cto");
	          }
	          if (qt_cto == 0) {
	            ConhecimentoED edVolta = new ConhecimentoED();
	            edVolta.setOID_Conhecimento(res.getString("oid_Conhecimento"));
	            edVolta.setNR_Conhecimento(res.getLong("nr_conhecimento"));
	            edVolta.setNR_Peso(res.getDouble("nr_peso"));
	            edVolta.setNR_Volumes(res.getDouble("nr_volumes"));
	            edVolta.setVL_Nota_Fiscal(res.getDouble("vl_nota_fiscal"));
	            edVolta.setNM_Pessoa_Destinatario(res.getString("nm_razao_social"));
	            edVolta.setDT_Emissao(FormataData.formataDataBT(res.getString("dt_emissao")));
	            lista.add(edVolta);
	          }
	        }

	    }
	    catch (Excecoes exc) {
	      throw exc;
	    }
	    catch (SQLException e) {
	      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "relConhecimentoDisponivelRota(ConhecimentoED ed)");
	    }
	    catch (Exception e) {
	      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "relConhecimentoDisponivelRota(ConhecimentoED ed)");
	    }
	    return lista;
	  }

}
