package com.master.bd;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */


import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.ConhecimentoED;
import com.master.ed.Documento_Lote_FornecedorED;
import com.master.root.FormataDataBean;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;
import com.master.util.bd.Transacao;
import com.master.util.ed.Parametro_FixoED;

public class Documento_Lote_FornecedorBD
    extends Transacao {

  private ExecutaSQL executasql;
  Parametro_FixoED edParametro_Fixo = new Parametro_FixoED ();
  Calcula_FreteBD Calcula_FreteBD = null;

  public Documento_Lote_FornecedorBD (ExecutaSQL sql) {
    this.executasql = sql;
  }

  public void altera (Documento_Lote_FornecedorED ed) throws Excecoes {

    String sql = null;

    try {

      sql = " update Documentos_Lotes_Fornecedores set  OID_Tipo_Movimento= " + ed.getOID_Tipo_Movimento () + ", VL_Cobrado = " + ed.getVL_Cobrado () + " , TX_Observacao = '" + ed.getTX_Observacao () + "'";
      sql += " where oid_Documento_Lote_Fornecedor = '" + ed.getOID_Documento_Lote_Fornecedor () + "'";

      // System.out.println (sql);

      int i = executasql.executarUpdate (sql);

      sql = " Update Movimentos_Conhecimentos set VL_Movimento= " + ed.getVL_Cobrado () +
          " WHERE oid_Conhecimento ='" + ed.getOID_Conhecimento () + "'" +
          " AND   oid_lote_fornecedor ='" + ed.getOID_Lote_Fornecedor () + "'";

      // System.out.println (sql);

      executasql.executarUpdate (sql);

    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar");
      excecoes.setMetodo ("alterar");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void deleta (Documento_Lote_FornecedorED ed) throws Excecoes {

    // System.out.println ("delete Documento_Lote_Fornecedor ->> ");

    String sql = null;

    try {

      sql = "delete from Documentos_Lotes_Fornecedores WHERE oid_Documento_Lote_Fornecedor = ";
      sql += "('" + ed.getOID_Documento_Lote_Fornecedor () + "')";

      int i = executasql.executarUpdate (sql);

      /*
             sql = " SELECT VL_Previsto FROM Movimentos_Conhecimentos "  +
            " WHERE oid_Conhecimento ='" + ed.getOID_Conhecimento () + "'" +
            " AND   oid_lote_fornecedor ='" + ed.getOID_Lote_Fornecedor () + "'";

             // System.out.println(sql);


             ResultSet res = null;
             res = this.executasql.executarConsulta(sql);
             double VL_Previsto=0;
             while (res.next()){
        VL_Previsto=res.getDouble("VL_Previsto");
             }

             // System.out.println("VL_Previsto ->> "  + VL_Previsto);

             if (VL_Previsto>0) {
        sql = " UPDATE Movimentos_Conhecimentos set VL_Movimento= " + VL_Previsto + ", oid_Lote_Fornecedor = 0 , vl_Realizado=0"  +
            " WHERE oid_Conhecimento ='" + ed.getOID_Conhecimento () + "'" +
            " AND   oid_lote_fornecedor ='" + ed.getOID_Lote_Fornecedor () + "'";
             }else {
        sql = " DELETE FROM Movimentos_Conhecimentos "  +
            " WHERE oid_Conhecimento ='" + ed.getOID_Conhecimento () + "'" +
            " AND   oid_lote_fornecedor ='" + ed.getOID_Lote_Fornecedor () + "'";
             }
             // System.out.println(sql);
             executasql.executarUpdate (sql);
       */


    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMetodo ("excluir");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public ArrayList lista (Documento_Lote_FornecedorED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    String nr_documento = "";
    String NR_Master="";
    int qt_master=0;
    double tt_consolidado=0;

    try {
      sql = " SELECT * from Documentos_Lotes_Fornecedores, Tipos_Movimentos " +
          " WHERE Documentos_Lotes_Fornecedores.oid_tipo_Movimento = Tipos_Movimentos.oid_tipo_Movimento " +
          " AND   oid_Lote_Fornecedor = ('" + ed.getOID_Lote_Fornecedor () + "')" +
          " Order by CD_Tipo_Movimento, Documentos_Lotes_Fornecedores.NR_Master, Documentos_Lotes_Fornecedores.NR_Documento ";
      // System.out.println (" lista->> " + sql);

      ResultSet res = null;
      ResultSet res2 = null;

      res = this.executasql.executarConsulta (sql);

      FormataDataBean DataFormatada = new FormataDataBean ();
      double tt_cobrado = 0 , tt_previsto = 0 , tt_movimento = 0;
      //popula
      while (res.next ()) {
        // System.out.println (" lista->> 2");

          Documento_Lote_FornecedorED edVolta = new Documento_Lote_FornecedorED ();
          Documento_Lote_FornecedorED edCons = new Documento_Lote_FornecedorED ();


          // System.out.println (" NR_Master->>" + res.getString ("NR_Master"));

          if (res.getString ("NR_Master")!=null ){

          // System.out.println (" NR_Master 2->>" + res.getString ("NR_Master"));

            if (res.getString ("NR_Master").equals(NR_Master)){
              // System.out.println (" NR_Master 3->>" );

              tt_consolidado+=res.getDouble ("VL_Cobrado");
              qt_master++;
            }else {

              // System.out.println (" NR_Master 10->>" );

              if (qt_master>1){
                edCons.setCD_Tipo_Movimento("");
                edCons.setCD_Unidade("");
                edCons.setDM_Atualiza1("");
                edCons.setDM_Atualiza2("");
                edCons.setDT_Emissao("");
                edCons.setOID_Lote_Fornecedor (0);
                edCons.setOID_Documento_Lote_Fornecedor ("");
                edCons.setNR_Postagem (0);
                edCons.setOID_Conhecimento ("");
                edCons.setNR_Documento ("");
                edCons.setNR_Master (" ");
                edCons.setNR_Ordem_Frete (" ");
                edCons.setOID_Tipo_Movimento (0);
                edCons.setVL_Total_Frete(0);
                edCons.setVL_Previsto(0);
                edCons.setVL_Movimento(0);
                
                edCons.setNM_Tipo_Movimento ("TOTAL CONSOLIDADO->>>");
                edCons.setVL_Cobrado (tt_consolidado);
                list.add (edCons);
              }
            // System.out.println (" NR_Master 20->>" );

              qt_master=1;
              NR_Master=res.getString ("NR_Master");
              tt_consolidado=res.getDouble ("VL_Cobrado");

              // System.out.println (" NR_Master 30->>" );

            }
          }

          edVolta.setOID_Lote_Fornecedor (res.getLong ("OID_Lote_Fornecedor"));
          edVolta.setOID_Documento_Lote_Fornecedor (res.getString ("OID_Documento_Lote_Fornecedor"));
          edVolta.setNR_Postagem(res.getLong("NR_Postagem"));
          edVolta.setOID_Conhecimento (res.getString ("oid_Conhecimento"));
          edVolta.setNR_Documento (res.getString ("NR_Documento"));
          edVolta.setNR_Master (" ");
          edVolta.setNR_Ordem_Frete (" ");
          if (res.getString ("OID_Ordem_Frete") != null && res.getString ("OID_Ordem_Frete").length () > 4) {
            edVolta.setOID_Ordem_Frete (res.getString ("OID_Ordem_Frete"));
            edVolta.setNR_Ordem_Frete (res.getString ("NR_Ordem_Frete"));
          }
          if (res.getString ("NR_Master") != null && res.getString ("NR_Master").length () > 4) {
            edVolta.setNR_Ordem_Frete (" ");
            edVolta.setNR_Master (res.getString ("NR_Master"));
          }
          edVolta.setVL_Cobrado (res.getDouble ("VL_Cobrado"));
          edVolta.setVL_Tabela (res.getDouble ("VL_Tabela"));
          edVolta.setOID_Tabela_Frete (res.getString ("OID_Tabela_Frete"));
          edVolta.setVL_Previsto (res.getDouble ("VL_Previsto"));
          edVolta.setTX_Observacao (res.getString ("TX_Observacao"));
          // System.out.println (" lista->> 3");

          tt_cobrado += res.getDouble ("VL_Cobrado");
          tt_previsto += res.getDouble ("VL_Previsto");

          edVolta.setOID_Tipo_Movimento (res.getLong ("oid_Tipo_Movimento"));
          edVolta.setCD_Tipo_Movimento (res.getString ("CD_Tipo_Movimento"));
          edVolta.setNM_Tipo_Movimento (res.getString ("NM_Tipo_Movimento"));
          edVolta.setNR_Postagem(res.getLong("nr_Postagem"));
          // System.out.println (" lista NR_Postagem->>" + res.getLong("NR_Postagem"));


          edVolta.setDM_Atualiza1(" ");
          edVolta.setDM_Atualiza2(" ");
          edVolta.setOid_Movimento_Conhecimento(" ");
          if ("S".equals(res.getString("DM_Atualiza1")))
            edVolta.setDM_Atualiza1("S");
          if ("S".equals(res.getString("DM_Atualiza2")))
            edVolta.setDM_Atualiza2("S");
          if (res.getString ("Oid_Movimento_Conhecimento") != null && res.getString ("Oid_Movimento_Conhecimento").length () > 4) {
            edVolta.setOid_Movimento_Conhecimento (res.getString ("Oid_Movimento_Conhecimento"));
            sql = " SELECT VL_Movimento from Movimentos_Conhecimentos " +
                " WHERE Movimentos_Conhecimentos.Oid_Movimento_Conhecimento = '" + res.getString ("Oid_Movimento_Conhecimento") + "'";

            // System.out.println (" lista cto->> " + sql);

            res2 = this.executasql.executarConsulta (sql);
            while (res2.next ()) {
              // System.out.println (" lista->> 5");

              edVolta.setVL_Movimento (res2.getDouble ("VL_Movimento"));
              tt_movimento+=res2.getDouble ("VL_Movimento");

            // System.out.println (" lista tt_movimento->> " + tt_movimento);


            }
          }
          if (res.getString ("oid_Conhecimento") != null && res.getString ("DM_Tipo_Documento").equals ("C")) {
            sql = " SELECT VL_Total_Frete, VL_Total_Custo from Conhecimentos " +
                " WHERE Conhecimentos.oid_Conhecimento = '" + res.getString ("oid_Conhecimento") + "'";

            // System.out.println (" lista cto->> " + sql);

            res2 = this.executasql.executarConsulta (sql);
            while (res2.next ()) {
              // System.out.println (" lista->> 5");

              edVolta.setVL_Total_Frete (res2.getDouble ("VL_Total_Frete"));
            }



            // System.out.println (" lista->> 6");

          }
          // System.out.println (" lista->> 7");

          list.add (edVolta);
          nr_documento = res.getString ("NR_Documento")+res.getString ("CD_Tipo_Movimento")+res.getString ("NR_Postagem");

      }
      Documento_Lote_FornecedorED edVolta = new Documento_Lote_FornecedorED ();
      edVolta.setOID_Ordem_Frete (" ");
      edVolta.setNR_Documento (" ");
      edVolta.setNR_Master (" ");
      edVolta.setDM_Atualiza1 (" ");
      edVolta.setDM_Atualiza2 (" ");
      edVolta.setOid_Movimento_Conhecimento (" ");
      edVolta.setNR_Ordem_Frete (" ");
      edVolta.setNM_Tipo_Movimento (" Totais ->>> ");
      edVolta.setVL_Cobrado (tt_cobrado);
      edVolta.setVL_Previsto (tt_previsto);
      edVolta.setVL_Movimento (tt_movimento);
      list.add (edVolta);

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao listar");
      excecoes.setMetodo ("listar");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return list;
  }

  public Documento_Lote_FornecedorED getByRecord (Documento_Lote_FornecedorED ed) throws Excecoes {

    String sql = null;

    Documento_Lote_FornecedorED edVolta = new Documento_Lote_FornecedorED ();

    try {

      sql = " SELECT *, Lotes_Fornecedores.oid_lote_fornecedor , Lotes_Fornecedores.DM_Situacao as DM_Situacao_Lote" +
          " FROM Lotes_Fornecedores, Documentos_Lotes_Fornecedores, Tipos_Movimentos " +
          " WHERE Documentos_Lotes_Fornecedores.oid_tipo_Movimento = Tipos_Movimentos.oid_tipo_Movimento " +
          " AND   Documentos_Lotes_Fornecedores.oid_Lote_Fornecedor = Lotes_Fornecedores.oid_Lote_Fornecedor ";

      if (String.valueOf (ed.getOID_Documento_Lote_Fornecedor ()) != null &&
          !String.valueOf (ed.getOID_Documento_Lote_Fornecedor ()).equals ("0") &&
          !String.valueOf (ed.getOID_Documento_Lote_Fornecedor ()).equals ("null")) {
        sql += " and OID_Documento_Lote_Fornecedor = '" + ed.getOID_Documento_Lote_Fornecedor () + "'";
      }
      else {
        if (ed.getOID_Conhecimento () != null &&
            ed.getOID_Conhecimento ().length () > 4) {
          sql += " and OID_Conhecimento = '" + ed.getOID_Conhecimento () + "'";
        }
      }
      // System.out.println (" lista->> " + sql);

      FormataDataBean DataFormatada = new FormataDataBean ();

      ResultSet res = null;
      ResultSet res2 = null;
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta.setOID_Lote_Fornecedor (res.getLong ("OID_Lote_Fornecedor"));
        edVolta.setOID_Documento_Lote_Fornecedor (res.getString ("OID_Documento_Lote_Fornecedor"));

        edVolta.setOID_Conhecimento (res.getString ("oid_Conhecimento"));
        edVolta.setNR_Documento (res.getString ("NR_Documento"));
        edVolta.setNR_Lote_Fornecedor (res.getLong ("oid_lote_fornecedor"));
        edVolta.setVL_Cobrado (res.getDouble ("VL_Cobrado"));
        edVolta.setVL_Previsto (res.getDouble ("VL_Previsto"));
        edVolta.setTX_Observacao (res.getString ("TX_Observacao"));
        // System.out.println (" lista->> 3");

        edVolta.setVL_Tabela (res.getDouble ("VL_Tabela"));
        edVolta.setOID_Tabela_Frete (res.getString ("OID_Tabela_Frete"));
        
        edVolta.setOID_Tipo_Movimento (res.getLong ("oid_Tipo_Movimento"));
        edVolta.setCD_Tipo_Movimento (res.getString ("CD_Tipo_Movimento"));
        edVolta.setNM_Tipo_Movimento (res.getString ("NM_Tipo_Movimento"));
        edVolta.setDM_Situacao (res.getString ("DM_Situacao_Lote"));
        // System.out.println (" lista->> 4");

        if (res.getString ("oid_Conhecimento") != null && res.getString ("DM_Tipo_Documento").equals ("C")) {
          sql = " SELECT VL_Total_Frete, VL_Total_Custo from Conhecimentos " +
              " WHERE Conhecimentos.oid_Conhecimento = '" + res.getString ("oid_Conhecimento") + "'";

          // System.out.println (" lista cto->> " + sql);

          res2 = this.executasql.executarConsulta (sql);
          while (res2.next ()) {
            // System.out.println (" lista->> 5");

            edVolta.setVL_Total_Frete (res2.getDouble ("VL_Total_Frete"));
          }
          // System.out.println (" lista->> 6");

        }
        // System.out.println (" lista->> 7");
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao selecionar");
      excecoes.setMetodo ("selecionar");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return edVolta;
  }

  public Documento_Lote_FornecedorED inclui (Documento_Lote_FornecedorED ed) throws Excecoes {

    String sql = null;
    long valOid = 0;
    String chave = null;
    Documento_Lote_FornecedorED edVolta = new Documento_Lote_FornecedorED ();

    try {
      sql = " SELECT OID_Documento_Lote_Fornecedor from Documentos_Lotes_Fornecedores " +
          " WHERE oid_Lote_Fornecedor = '" + ed.getOID_Lote_Fornecedor () + "'" +
          " AND   oid_Conhecimento = '" + ed.getOID_Conhecimento () + "'" +
          " AND   oid_Tipo_Movimento = '" + ed.getOID_Tipo_Movimento () + "'";
      if (ed.getNR_Postagem ()>0){
        sql+=" AND   NR_Postagem = '" + ed.getNR_Postagem () + "'";
      }
      if (ed.getNR_Master () !=null && !ed.getNR_Master ().equals("null")){
        sql+=" AND   NR_Master = '" + ed.getNR_Master () + "'";
      }

      // System.out.println (" lista->> " + sql);

      ResultSet res = this.executasql.executarConsulta (sql);
      if (!res.next ()) {
        chave = (String.valueOf (ed.getOID_Lote_Fornecedor ()) + String.valueOf (System.currentTimeMillis ()));

        // System.out.println (" inclui->> " + chave);
        // System.out.println (" ed.getOID_Conhecimento()->> " + ed.getOID_Conhecimento());
        
        if (ed.getOID_Conhecimento()!=null && ed.getOID_Conhecimento().length()>4 ) { //&& "1980671CN2".equals(ed.getOID_Conhecimento()

            sql = " SELECT Lotes_Fornecedores.oid_Pessoa FROM Lotes_Fornecedores, Tabelas_Fretes " +
                  " WHERE Lotes_Fornecedores.oid_Pessoa = Tabelas_Fretes.oid_Pessoa " +
                  " AND   Tabelas_Fretes.DM_Tipo_Tabela ='F' " +
                  " AND   oid_Lote_Fornecedor = '" + ed.getOID_Lote_Fornecedor () + "'" +
                  " LIMIT 1 ";
            ResultSet resLote = this.executasql.executarConsulta (sql);
            if (resLote.next ()) {        	
	        	ConhecimentoED edCto = new ConhecimentoED ();
		        edCto.setOID_Conhecimento(ed.getOID_Conhecimento());
		        edCto = new ConhecimentoBD (this.executasql).getByRecord(edCto);

		        edCto.setOID_Pessoa_Entregadora (resLote.getString("oid_Pessoa"));
		        

		        // System.out.println (" ed.getOID_Tabela_Frete() RETORNO->> " + edCto.getOID_Tabela_Frete());
		        
		        ed.setVL_Tabela(edCto.getVL_Total_Redespacho());
		        ed.setOID_Tabela_Frete(edCto.getOID_Tabela_Frete());
		        // System.out.println (" edCto->> " + edCto.getOID_Tabela_Frete());

            }    
        }    

        
        sql = " insert into Documentos_Lotes_Fornecedores (OID_Documento_Lote_Fornecedor, NR_Documento, NR_Master, Oid_Ordem_Frete, NR_Ordem_Frete, DM_Tipo_Documento, OID_Conhecimento, OID_Lote_Fornecedor, OID_Tipo_Movimento, VL_Cobrado, VL_Previsto, VL_Tabela, NR_Postagem, oid_Tabela_Frete ) values ";
        sql += "('" + chave + "','" + ed.getNR_Documento () + "','" + ed.getNR_Master () + "','" + ed.getOID_Ordem_Frete ()+ "','" + ed.getNR_Ordem_Frete () + "','" + ed.getDM_Tipo_Documento () + "','" + ed.getOID_Conhecimento () + "'," + ed.getOID_Lote_Fornecedor () + "," + ed.getOID_Tipo_Movimento () + "," + ed.getVL_Cobrado () + "," + ed.getVL_Previsto () + "," + ed.getVL_Tabela ()  + "," + ed.getNR_Postagem() + ",'" + ed.getOID_Tabela_Frete() +  "')";

        // System.out.println ("inclui 1 " + sql);

        int i = executasql.executarUpdate (sql);

        edVolta.setOID_Documento_Lote_Fornecedor (chave);

        // System.out.println ("kieli 2 incluiu Documento_Lote_Fornecedor");

      }
      else {
        // System.out.println ("nao inclui pq ja existe");
        edVolta.setOID_Documento_Lote_Fornecedor ("JA EXISTE");
      }

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMetodo ("inclui");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return edVolta;
  }

  public Documento_Lote_FornecedorED inclui_Ordem_Frete (Documento_Lote_FornecedorED ed) throws Excecoes {

    // System.out.println (" inclui_Ordem_Frete _aprovacao_pagamento ");

    String sql = null;
    ResultSet rs = null;
    ResultSet rsMov = null;
    Parametro_FixoED edParametro_Fixo = new Parametro_FixoED ();
    Documento_Lote_FornecedorED documento_Lote_FornecedorED = new Documento_Lote_FornecedorED ();
    int qt_ctos=0;
    double tt_movimento=0;
    try {


      sql = " SELECT Conhecimentos.OID_Conhecimento, Conhecimentos.NR_Conhecimento " +
	      " FROM Viagens, Conhecimentos, Ordens_Manifestos " +
	      " WHERE Viagens.OID_Conhecimento = Conhecimentos.OID_Conhecimento " +
	      " AND Viagens.OID_Manifesto = Ordens_Manifestos.OID_Manifesto " +
	      " AND Ordens_Manifestos.OID_Ordem_Frete = '" + ed.getOID_Ordem_Frete () + "'";
      
      
      // System.out.println (" ->> " + sql);

      rs = this.executasql.executarConsulta (sql);
      while (rs.next ()) {

    	Documento_Lote_FornecedorED docLoteED = new Documento_Lote_FornecedorED ();

        docLoteED.setOID_Lote_Fornecedor (ed.getOID_Lote_Fornecedor ());
        docLoteED.setOID_Pessoa (ed.getOID_Pessoa ());
        docLoteED.setOID_Conhecimento (rs.getString ("oid_Conhecimento"));
        docLoteED.setOID_Ordem_Frete (ed.getOID_Ordem_Frete ());
        docLoteED.setNR_Documento (rs.getString ("NR_Conhecimento"));
        docLoteED.setNR_Ordem_Frete (ed.getNR_Ordem_Frete ());

        // System.out.println ("bd inclui OF : " + ed.getNR_Ordem_Frete ());
        docLoteED.setDM_Tipo_Documento ("C");

        docLoteED.setOID_Tipo_Movimento (new Long (edParametro_Fixo.getOID_Tipo_Movimento_Custo_Ordem_Frete ()).longValue ());

        sql = " SELECT Movimentos_Conhecimentos.oid_Tipo_Movimento, VL_Movimento " +
        	  " FROM   Movimentos_Conhecimentos " +
        	  " WHERE  Movimentos_Conhecimentos.OID_Conhecimento ='"+ rs.getString ("oid_Conhecimento") +"'" +
        	  " AND    Movimentos_Conhecimentos.OID_Lote_Fornecedor  = 0 " +
        	  " AND    Movimentos_Conhecimentos.OID_Ordem_Frete = '" + ed.getOID_Ordem_Frete () + "'";
        rsMov = this.executasql.executarConsulta (sql);
        if (rsMov.next ()) {
           docLoteED.setOID_Tipo_Movimento (new Long (rsMov.getLong ("oid_Tipo_Movimento")).longValue ());
           docLoteED.setVL_Cobrado (rsMov.getDouble ("VL_Movimento"));
           tt_movimento+=rsMov.getDouble ("VL_Movimento");
        }

        docLoteED.setTX_Observacao ("OF::" + ed.getNR_Ordem_Frete ());
        qt_ctos++;

        // System.out.println ("INCLUI OP CTO: " + rs.getString ("NR_Conhecimento") +" qt:" + qt_ctos + "  total=" + tt_movimento );

        
        inclui (docLoteED);

      }

      sql = " UPDATE Ordens_Fretes set  OID_Lote_Fornecedor= " + ed.getOID_Lote_Fornecedor () +
            " WHERE  oid_Ordem_Frete = '" + ed.getOID_Ordem_Frete () + "'";

      // System.out.println (sql);
      int i = executasql.executarUpdate (sql);

    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMetodo ("inclui");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return documento_Lote_FornecedorED;

  }

  public Documento_Lote_FornecedorED inclui_Master (Documento_Lote_FornecedorED ed) throws Excecoes {

    // System.out.println (" Master_ra Master_aprovacao_pagamento ");

    String sql = null;
    ResultSet rs = null;
    Parametro_FixoED edParametro_Fixo = new Parametro_FixoED ();
    Documento_Lote_FornecedorED documento_Lote_FornecedorED = new Documento_Lote_FornecedorED ();
    double NR_Peso = 0 , NR_Total_Peso_CTRC = 0 , VL_Movimento = 0;

    try {

      sql = " UPDATE Ordens_Fretes set  OID_Lote_Fornecedor= " + ed.getOID_Lote_Fornecedor () +
          " WHERE  oid_Ordem_Frete = '" + ed.getOID_Ordem_Frete () + "'";

      // System.out.println (sql);
      int i = executasql.executarUpdate (sql);

      sql = " SELECT Conhecimentos.OID_Conhecimento, Conhecimentos.NR_Conhecimento, Conhecimentos.NR_Peso as NR_Peso_CTRC, Conhecimentos.NR_Peso_Cubado ";
      sql += " FROM Viagens, Conhecimentos, Ordens_Manifestos ";
      sql += " WHERE Viagens.OID_Conhecimento = Conhecimentos.OID_Conhecimento ";
      sql += " AND Viagens.OID_Manifesto = Ordens_Manifestos.OID_Manifesto ";
      sql += " AND Ordens_Manifestos.OID_Ordem_Frete = '" + ed.getOID_Ordem_Frete () + "'";

      // System.out.println (" ->> " + sql);

      rs = this.executasql.executarConsulta (sql);

      while (rs.next ()) {
        // System.out.println (" a 1 ");
        if (rs.getDouble ("NR_Peso_CTRC") > rs.getDouble ("NR_Peso_Cubado")) {
          NR_Total_Peso_CTRC += rs.getDouble ("NR_Peso_CTRC");
        }
        else {
          NR_Total_Peso_CTRC += rs.getDouble ("NR_Peso_Cubado");
        }

      }

      // System.out.println (" peso =>> " + NR_Total_Peso_CTRC);
      // System.out.println (" ed.getVL_Previsto() =>> " + ed.getVL_Cobrado ());
      if (NR_Total_Peso_CTRC <= 0) {
        NR_Total_Peso_CTRC = 1;
      }

      sql = " SELECT Conhecimentos.OID_Conhecimento, Conhecimentos.NR_Conhecimento, Conhecimentos.NR_Peso as NR_Peso_CTRC, Conhecimentos.NR_Peso_Cubado";
      sql += " FROM Viagens, Conhecimentos, Ordens_Manifestos ";
      sql += " WHERE Viagens.OID_Conhecimento = Conhecimentos.OID_Conhecimento ";
      sql += " AND Viagens.OID_Manifesto = Ordens_Manifestos.OID_Manifesto ";
      sql += " AND Ordens_Manifestos.OID_Ordem_Frete = '" + ed.getOID_Ordem_Frete () + "'";

      // System.out.println (" ->> " + sql);

      rs = this.executasql.executarConsulta (sql);
      while (rs.next ()) {
        //this.inicioTransacao ();
        //this.executasql = this.sql;
        double peso_cto = rs.getDouble ("NR_Peso_Cubado");
        if (rs.getDouble ("NR_Peso_CTRC") > rs.getDouble ("NR_Peso_Cubado")) {
          peso_cto = rs.getDouble ("NR_Peso_CTRC");
        }
        if (peso_cto <= 0) {
          peso_cto = 1;
        }

        VL_Movimento = (ed.getVL_Cobrado () / NR_Total_Peso_CTRC * peso_cto);

        Documento_Lote_FornecedorED docLoteED = new Documento_Lote_FornecedorED ();

        // System.out.println ("aplicando " + rs.getString ("NR_Conhecimento"));

        docLoteED.setOID_Lote_Fornecedor (ed.getOID_Lote_Fornecedor ());
        docLoteED.setOID_Pessoa (ed.getOID_Pessoa ());
        docLoteED.setOID_Conhecimento (rs.getString ("oid_Conhecimento"));
        docLoteED.setNR_Documento (rs.getString ("NR_Conhecimento"));
        docLoteED.setNR_Master (ed.getNR_Master ());
        docLoteED.setOID_Ordem_Frete (ed.getOID_Ordem_Frete ());

        // System.out.println ("bd inclui master : " + ed.getNR_Master ());
        docLoteED.setDM_Tipo_Documento ("C");
        docLoteED.setOID_Tipo_Movimento (new Long (edParametro_Fixo.getOID_Tipo_Movimento_Custo_Master ()).longValue ());
        docLoteED.setTX_Observacao ("master:" + ed.getNR_Master ());
        docLoteED.setVL_Cobrado (new Double (VL_Movimento).doubleValue ());
        inclui (docLoteED);
        //this.fimTransacao (true);

      }

    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMetodo ("inclui");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return documento_Lote_FornecedorED;

  }

  public Documento_Lote_FornecedorED inclui_Master_PROV (Documento_Lote_FornecedorED ed) throws Excecoes {

    // System.out.println (" Master_ra inclui_Master_PROV ");

    String sql = null;
    ResultSet rs = null;
    Parametro_FixoED edParametro_Fixo = new Parametro_FixoED ();
    Documento_Lote_FornecedorED documento_Lote_FornecedorED = new Documento_Lote_FornecedorED ();
    double NR_Peso = 0 , NR_Total_Peso_CTRC = 0 , VL_Movimento = 0;

    try {

      sql = " UPDATE Ordens_Fretes set  OID_Lote_Fornecedor= " + ed.getOID_Lote_Fornecedor () +
          " WHERE  oid_Ordem_Frete = '" + ed.getOID_Ordem_Frete () + "'";

      // System.out.println (sql);

      //int i = executasql.executarUpdate (sql);

      sql = " SELECT Conhecimentos.OID_Conhecimento, Conhecimentos.NR_Conhecimento, Conhecimentos.NR_Peso as NR_Peso_CTRC, Conhecimentos.NR_Peso_Cubado ";
      sql += " FROM Viagens, Conhecimentos, Ordens_Manifestos ";
      sql += " WHERE Viagens.OID_Conhecimento = Conhecimentos.OID_Conhecimento ";
      sql += " AND Viagens.OID_Manifesto = Ordens_Manifestos.OID_Manifesto ";
      sql += " AND Ordens_Manifestos.OID_Ordem_Frete = '" + ed.getOID_Ordem_Frete () + "'";

      // System.out.println (" ->> " + sql);

      rs = this.executasql.executarConsulta (sql);

      while (rs.next ()) {
        // System.out.println (" a 1 ");
        if (rs.getDouble ("NR_Peso_CTRC") > rs.getDouble ("NR_Peso_Cubado")) {
          NR_Total_Peso_CTRC += rs.getDouble ("NR_Peso_CTRC");
        }
        else {
          NR_Total_Peso_CTRC += rs.getDouble ("NR_Peso_Cubado");
        }

      }

      // System.out.println (" peso =>> " + NR_Total_Peso_CTRC);
      // System.out.println (" ed.getVL_Previsto() =>> " + ed.getVL_Cobrado ());
      if (NR_Total_Peso_CTRC <= 0) {
        NR_Total_Peso_CTRC = 1;
      }

      sql = " SELECT Conhecimentos.OID_Conhecimento, Conhecimentos.NR_Conhecimento, Conhecimentos.NR_Peso as NR_Peso_CTRC, Conhecimentos.NR_Peso_Cubado";
      sql += " FROM Viagens, Conhecimentos, Ordens_Manifestos ";
      sql += " WHERE Viagens.OID_Conhecimento = Conhecimentos.OID_Conhecimento ";
      sql += " AND Viagens.OID_Manifesto = Ordens_Manifestos.OID_Manifesto ";
      sql += " AND Ordens_Manifestos.OID_Ordem_Frete = '" + ed.getOID_Ordem_Frete () + "'";

      // System.out.println (" ->> " + sql);

      rs = this.executasql.executarConsulta (sql);
      while (rs.next ()) {
        //this.inicioTransacao ();
        //this.executasql = this.sql;
        double peso_cto = rs.getDouble ("NR_Peso_Cubado");
        if (rs.getDouble ("NR_Peso_CTRC") > rs.getDouble ("NR_Peso_Cubado")) {
          peso_cto = rs.getDouble ("NR_Peso_CTRC");
        }
        if (peso_cto <= 0) {
          peso_cto = 1;
        }

        VL_Movimento = (ed.getVL_Cobrado () / NR_Total_Peso_CTRC * peso_cto);

        Documento_Lote_FornecedorED docLoteED = new Documento_Lote_FornecedorED ();

        // System.out.println ("aplicando " + rs.getString ("NR_Conhecimento"));

        docLoteED.setOID_Lote_Fornecedor (ed.getOID_Lote_Fornecedor ());
        docLoteED.setOID_Pessoa (ed.getOID_Pessoa ());
        docLoteED.setOID_Conhecimento (rs.getString ("oid_Conhecimento"));
        docLoteED.setNR_Documento (rs.getString ("NR_Conhecimento"));
        docLoteED.setNR_Master (ed.getNR_Master ());
        docLoteED.setOID_Ordem_Frete (ed.getOID_Ordem_Frete ());

        // System.out.println ("bd inclui master : " + ed.getNR_Master ());
        docLoteED.setDM_Tipo_Documento ("C");
        docLoteED.setOID_Tipo_Movimento (new Long (edParametro_Fixo.getOID_Tipo_Movimento_Custo_Master ()).longValue ());
        docLoteED.setTX_Observacao ("master:" + ed.getNR_Master ());
        docLoteED.setVL_Cobrado (new Double (VL_Movimento).doubleValue ());


        inclui (docLoteED);
        //this.fimTransacao (true);

      }

    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMetodo ("inclui");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return documento_Lote_FornecedorED;

  }

  public Documento_Lote_FornecedorED inclui_Cto_Filtro (Documento_Lote_FornecedorED ed) throws Excecoes {

    // System.out.println ("inclui_Cto_Filtro BD 1");

    String sql = null;
    ResultSet res = null;

    try {

      sql = "SELECT  Conhecimentos.oid_Conhecimento, Conhecimentos.NR_Conhecimento, Conhecimentos.VL_Total_Frete " +
          " FROM   Conhecimentos " +
          " WHERE  Conhecimentos.VL_Total_Frete >0 " +
          " AND    Conhecimentos.DM_Situacao <>'C'  " +
          " AND    Conhecimentos.DM_Tipo_Documento = 'C'  " +
          " AND    Conhecimentos.DM_Impresso = 'S'  ";

      if (String.valueOf (ed.getOID_Unidade ()) != null &&
          !String.valueOf (ed.getOID_Unidade ()).equals ("") &&
          !String.valueOf (ed.getOID_Unidade ()).equals ("0") &&
          !String.valueOf (ed.getOID_Unidade ()).equals ("null")) {
        sql += " and Conhecimentos.OID_Unidade = '" + ed.getOID_Unidade () + "'";
      }

      if (String.valueOf (ed.getOID_Pessoa ()) != null &&
          !String.valueOf (ed.getOID_Pessoa ()).equals ("") &&
          !String.valueOf (ed.getOID_Pessoa ()).equals ("null")) {
        sql += " and Conhecimentos.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
      }

      if (String.valueOf (ed.getOID_Pessoa_Destinatario ()) != null &&
          !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("") &&
          !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("null")) {
        sql += " and Conhecimentos.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario () + "'";
      }

      if (String.valueOf (ed.getOID_Pessoa_Entregadora ()) != null &&
          !String.valueOf (ed.getOID_Pessoa_Entregadora ()).equals ("") &&
          !String.valueOf (ed.getOID_Pessoa_Entregadora ()).equals ("null")) {
        sql += " and Conhecimentos.OID_Pessoa_Entregadora = '" + ed.getOID_Pessoa_Entregadora () + "'";
      }

      if (String.valueOf (ed.getOID_Modal ()) != null &&
          !String.valueOf (ed.getOID_Modal ()).equals ("") &&
          !String.valueOf (ed.getOID_Modal ()).equals ("0") &&
          !String.valueOf (ed.getOID_Modal ()).equals ("null")) {
        sql += " and Conhecimentos.OID_Modal = '" + ed.getOID_Modal () + "'";
      }

      if (String.valueOf (ed.getOID_Cidade_Destino ()) != null &&
          !String.valueOf (ed.getOID_Cidade_Destino ()).equals ("") &&
          !String.valueOf (ed.getOID_Cidade_Destino ()).equals ("0") &&
          !String.valueOf (ed.getOID_Cidade_Destino ()).equals ("null")) {
        sql += " and Conhecimentos.OID_Cidade_Destino = '" + ed.getOID_Cidade_Destino () + "'";
      }

      if (String.valueOf (ed.getDT_Emissao_Inicial ()) != null &&
          !String.valueOf (ed.getDT_Emissao_Inicial ()).equals ("") &&
          !String.valueOf (ed.getDT_Emissao_Inicial ()).equals ("null")) {
        sql += " and Conhecimentos.DT_Emissao >= '" + ed.getDT_Emissao_Inicial () + "'";
      }

      if (String.valueOf (ed.getDT_Emissao_Final ()) != null &&
          !String.valueOf (ed.getDT_Emissao_Final ()).equals ("") &&
          !String.valueOf (ed.getDT_Emissao_Final ()).equals ("null")) {
        sql += " and Conhecimentos.DT_Emissao <= '" + ed.getDT_Emissao_Final () + "'";
      }

      if (String.valueOf (ed.getNR_Conhecimento_Inicial ()) != null &&
          !String.valueOf (ed.getNR_Conhecimento_Inicial ()).equals ("0") &&
          !String.valueOf (ed.getNR_Conhecimento_Inicial ()).equals ("") &&
          !String.valueOf (ed.getNR_Conhecimento_Inicial ()).equals ("null")) {
        sql += " and Conhecimentos.NR_Conhecimento >= " + ed.getNR_Conhecimento_Inicial ();
      }

      if (String.valueOf (ed.getNR_Conhecimento_Final ()) != null &&
          !String.valueOf (ed.getNR_Conhecimento_Inicial ()).equals ("0") &&
          !String.valueOf (ed.getNR_Conhecimento_Final ()).equals ("") &&
          !String.valueOf (ed.getNR_Conhecimento_Final ()).equals ("null")) {
        sql += " and Conhecimentos.NR_Conhecimento <= " + ed.getNR_Conhecimento_Final ();
      }

      if (String.valueOf (ed.getNR_Postagem_Inicial ()) != null &&
          !String.valueOf (ed.getNR_Postagem_Inicial ()).equals ("0") &&
          !String.valueOf (ed.getNR_Postagem_Inicial ()).equals ("") &&
          !String.valueOf (ed.getNR_Postagem_Inicial ()).equals ("null")) {
        sql += " and Postagems.NR_Postagem >= " + ed.getNR_Postagem_Inicial ();
      }

      if (String.valueOf (ed.getNR_Postagem_Final ()) != null &&
          !String.valueOf (ed.getNR_Postagem_Inicial ()).equals ("0") &&
          !String.valueOf (ed.getNR_Postagem_Final ()).equals ("") &&
          !String.valueOf (ed.getNR_Postagem_Final ()).equals ("null")) {
        sql += " and Postagems.NR_Postagem <= " + ed.getNR_Postagem_Final ();
      }

      // System.out.println ("filtro Aprov-> " + sql);

      int qt_cto = 0;
      res = this.executasql.executarConsulta (sql);

      while (res.next ()) {
        qt_cto++;

        // System.out.println ("Lendo Cto => " + qt_cto + "  " + res.getString ("oid_Conhecimento"));

        Documento_Lote_FornecedorED edLote = new Documento_Lote_FornecedorED ();
        edLote.setOID_Lote_Fornecedor (ed.getOID_Lote_Fornecedor ());
        edLote.setOID_Tipo_Movimento (ed.getOID_Tipo_Movimento ());
        edLote.setVL_Cobrado (ed.getVL_Cobrado ());
        edLote.setOID_Conhecimento (res.getString ("oid_Conhecimento"));
        edLote.setNR_Documento (res.getString ("NR_Conhecimento"));
        edLote.setDM_Tipo_Documento ("C");

        inclui (edLote);

      }

      // System.out.println ("incluiu Documento_Lote_Faturamento");

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMetodo ("inclui_Cto_Filtro");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return ed;
  }


  public Documento_Lote_FornecedorED inclui_Manifesto (Documento_Lote_FornecedorED ed) throws Excecoes {

    // System.out.println ("OID_Manifesto BD 1");

    String sql = null;
    ResultSet res = null;
    double nr_Peso=0;
    try {

      sql = "SELECT  SUM (NR_Peso) as NR_Peso " +
          " FROM   Conhecimentos, Viagens " +
          " WHERE  Conhecimentos.oid_Conhecimento = Viagens.oid_Conhecimento " +
          " AND    Conhecimentos.VL_Total_Frete >0 " +
          " AND    Conhecimentos.DM_Situacao <>'C'  " +
          " AND    Conhecimentos.DM_Tipo_Documento = 'C'  " +
          " AND    Conhecimentos.DM_Impresso = 'S'  " +
          " AND    Viagens.OID_Manifesto = '" + ed.getOID_Manifesto() + "'";

      // System.out.println ("filtro Aprov-> " + sql);

      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        nr_Peso=res.getDouble("NR_Peso");
      }

      sql = "SELECT  Conhecimentos.oid_Conhecimento, Conhecimentos.NR_Conhecimento, Conhecimentos.VL_Total_Frete, Conhecimentos.NR_Peso " +
          " FROM   Conhecimentos, Viagens " +
          " WHERE  Conhecimentos.oid_Conhecimento = Viagens.oid_Conhecimento " +
          " AND    Conhecimentos.VL_Total_Frete >0 " +
          " AND    Conhecimentos.DM_Situacao <>'C'  " +
          " AND    Conhecimentos.DM_Tipo_Documento = 'C'  " +
          " AND    Conhecimentos.DM_Impresso = 'S'  " +
          " AND    Viagens.OID_Manifesto = '" + ed.getOID_Manifesto() + "'";

      // System.out.println ("filtro Aprov-> " + sql);

      int qt_cto = 0;
      res = this.executasql.executarConsulta (sql);

      while (res.next ()) {
        qt_cto++;

        // System.out.println ("Lendo OID_Manifesto Cto => " + qt_cto + "  " + res.getString ("oid_Conhecimento"));

        Documento_Lote_FornecedorED edLote = new Documento_Lote_FornecedorED ();
        edLote.setOID_Lote_Fornecedor (ed.getOID_Lote_Fornecedor ());
        edLote.setOID_Tipo_Movimento (ed.getOID_Tipo_Movimento ());
        edLote.setOID_Manifesto(ed.getOID_Manifesto());
        edLote.setVL_Cobrado ((ed.getVL_Cobrado ()/nr_Peso)*res.getDouble("NR_Peso"));
        edLote.setOID_Conhecimento (res.getString ("oid_Conhecimento"));
        edLote.setNR_Documento (res.getString ("NR_Conhecimento"));
        edLote.setDM_Tipo_Documento ("C");

        inclui (edLote);

      }

      // System.out.println ("incluiu OID_Manifesto");

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMetodo ("OID_Manifesto");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return ed;
  }

  public Documento_Lote_FornecedorED inclui_Fatura_Master (Documento_Lote_FornecedorED ed) throws Excecoes {

    // System.out.println ("inclui_Fatura_Master BD 1");

    String sql = null;
    ResultSet res = null;
    ResultSet resOP = null;
    String nr_Master="";
    String dm_Cia_Aerea="";
    try {

      sql = " SELECT  * FROM Faturas_Master " +
            " WHERE   Faturas_Master.NR_Fatura = '" + ed.getNR_Fatura_Master() + "'";
            //" AND   Faturas_Master.oid_Lote_Fornecedor is null " +

      // System.out.println ("filtro Aprov-> " + sql);

      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        nr_Master = res.getString ("nr_Master");

        if (nr_Master!=null && nr_Master.length()>4){
          // System.out.println ("incluiu nr_Master=>" + nr_Master);
          nr_Master+="                          ";
          dm_Cia_Aerea = res.getString ("dm_Cia_Aerea");
          if ("TAM".equals (dm_Cia_Aerea)) {
            //957-0016.0458595
            nr_Master=nr_Master.substring(10,16);
          }
          // System.out.println ("nr_Master 2=>" + nr_Master);

          sql = " SELECT * FROM Ordens_Fretes " +
              " WHERE Ordens_Fretes.NR_Master = '" + nr_Master + "'"+
              " ORDER BY Ordens_Fretes.DT_Emissao DESC ";

          // System.out.println (sql);

          resOP = this.executasql.executarConsulta (sql);
          if (resOP.next ()) {
            // System.out.println ("Achou master");

            ed.setNR_Master (nr_Master);
            ed.setVL_Cobrado (res.getDouble ("vl_master"));

            ed.setOID_Ordem_Frete (resOP.getString ("oid_Ordem_Frete"));
            ed.setOID_Pessoa (resOP.getString ("oid_Pessoa"));

            this.inclui_Master_PROV(ed);

            sql = " UPDATE Faturas_Master set  OID_Lote_Fornecedor= " + ed.getOID_Lote_Fornecedor () +
                  " WHERE  oid_fatura_master = '" + res.getString("oid_fatura_master") + "'";

            // System.out.println (sql);
            int i = executasql.executarUpdate (sql);


          }

          // System.out.println ("incluiu nr_Master=>" + nr_Master);
        }
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMetodo ("inclui_Master_Fatura");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return ed;
  }

  public Documento_Lote_FornecedorED inclui_Fatura_Postagem (Documento_Lote_FornecedorED ed) throws Excecoes {

    // System.out.println ("inclui_Fatura_Postagem BD 1");

    String sql = null;
    ResultSet res = null;

    try {
      //select antigo, ficar at� todos ctos estarem relac p.oid
      sql = "SELECT  Conhecimentos.oid_Conhecimento, Conhecimentos.NR_Conhecimento, Conhecimentos.VL_Total_Frete, Postagens.VL_Postagem, Postagens.NR_Postagem " +
          " FROM   Conhecimentos, Postagens " +
          " WHERE  Conhecimentos.nr_Postagem = Postagens.nr_Postagem " +
         // " AND    Postagens.oid_Conhecimento is null " +
          " AND    Conhecimentos.VL_Total_Frete >0 " +
          " AND    Conhecimentos.DM_Situacao <>'C'  " +
          " AND    Conhecimentos.DM_Impresso = 'S'  " +
          " AND    Postagens.NR_Fatura = '" + ed.getNR_Fatura_Postagem () + "'";

      // System.out.println ("filtro Aprov-> " + sql);

      int qt_cto = 0;
      res = this.executasql.executarConsulta (sql);

      while (res.next ()) {
        qt_cto++;

        sql = " SELECT OID_Documento_Lote_Fornecedor from Documentos_Lotes_Fornecedores " +
              " WHERE oid_Lote_Fornecedor = '" + ed.getOID_Lote_Fornecedor () + "'" +
              " AND   NR_Postagem = '" + res.getLong("NR_Postagem") + "'";
        // System.out.println (" lista->> " + sql);
      
        ResultSet res2 = this.executasql.executarConsulta (sql);
        if (!res2.next ()) {
          // System.out.println ("Lendo Cto => " + qt_cto + "  " + res.getString ("oid_Conhecimento"));

          Documento_Lote_FornecedorED edLote = new Documento_Lote_FornecedorED ();
          edLote.setOID_Lote_Fornecedor (ed.getOID_Lote_Fornecedor ());
          edLote.setOID_Tipo_Movimento (ed.getOID_Tipo_Movimento ());
          edLote.setOID_Conhecimento (res.getString ("oid_Conhecimento"));
          edLote.setNR_Documento (res.getString ("NR_Conhecimento"));
          edLote.setDM_Tipo_Documento ("C");
          edLote.setVL_Cobrado (res.getDouble ("VL_Postagem"));
          edLote.setNR_Postagem (res.getLong ("NR_Postagem"));

          inclui (edLote);
        }
      }
      sql = "SELECT  Conhecimentos.oid_Conhecimento, Conhecimentos.NR_Conhecimento, Conhecimentos.VL_Total_Frete, Postagens.VL_Postagem, Postagens.NR_Postagem " +
          " FROM   Conhecimentos, Postagens " +
          " WHERE  Conhecimentos.oid_Conhecimento = Postagens.oid_Conhecimento " +
          " AND    Conhecimentos.VL_Total_Frete >0 " +
          " AND    Conhecimentos.DM_Situacao <>'C'  " +
          " AND    Conhecimentos.DM_Impresso = 'S'  " +
          " AND    Postagens.NR_Fatura = '" + ed.getNR_Fatura_Postagem () + "'";

      // System.out.println ("filtro Aprov-> " + sql);
      res = this.executasql.executarConsulta (sql);

      while (res.next ()) {
        qt_cto++;

        // System.out.println ("Lendo Cto => " + qt_cto + "  " + res.getString ("oid_Conhecimento"));

        sql = " SELECT OID_Documento_Lote_Fornecedor from Documentos_Lotes_Fornecedores " +
              " WHERE oid_Lote_Fornecedor = '" + ed.getOID_Lote_Fornecedor () + "'" +
              " AND   NR_Postagem = '" + res.getLong("NR_Postagem") + "'";
        // System.out.println (" lista->> " + sql);
      
        ResultSet res2 = this.executasql.executarConsulta (sql);
        if (!res2.next ()) {

          Documento_Lote_FornecedorED edLote = new Documento_Lote_FornecedorED ();
          edLote.setOID_Lote_Fornecedor (ed.getOID_Lote_Fornecedor ());
          edLote.setOID_Tipo_Movimento (ed.getOID_Tipo_Movimento ());
          edLote.setOID_Conhecimento (res.getString ("oid_Conhecimento"));
          edLote.setNR_Documento (res.getString ("NR_Conhecimento"));
          edLote.setDM_Tipo_Documento ("C");
          edLote.setVL_Cobrado (res.getDouble ("VL_Postagem"));
          edLote.setNR_Postagem (res.getLong ("NR_Postagem"));

          inclui (edLote);
        }
      }




      // System.out.println ("incluiu Documento_Lote_Faturamento");

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMetodo ("inclui_Fatura_Postagem");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return ed;
  }

  public Documento_Lote_FornecedorED deleta_Cto_Filtro (Documento_Lote_FornecedorED ed) throws Excecoes {

    // System.out.println ("deleta_Cto_Filtro BD 1");

    String sql = null;
    ResultSet res = null;

    try {
      sql = " SELECT Documentos_Lotes_Fornecedores.oid_Documento_Lote_Fornecedor " +
          " FROM   Documentos_Lotes_Fornecedores, Tipos_Movimentos, Conhecimentos " +
          " WHERE Documentos_Lotes_Fornecedores.oid_tipo_Movimento = Tipos_Movimentos.oid_tipo_Movimento " +
          " AND   Documentos_Lotes_Fornecedores.oid_Conhecimento = Conhecimentos.oid_Conhecimento " +
          " AND   oid_Lote_Fornecedor = ('" + ed.getOID_Lote_Fornecedor () + "')";

      if (String.valueOf (ed.getOID_Unidade ()) != null &&
          !String.valueOf (ed.getOID_Unidade ()).equals ("") &&
          !String.valueOf (ed.getOID_Unidade ()).equals ("0") &&
          !String.valueOf (ed.getOID_Unidade ()).equals ("null")) {
        sql += " and Conhecimentos.OID_Unidade = '" + ed.getOID_Unidade () + "'";
      }

      if (String.valueOf (ed.getOID_Tipo_Movimento ()) != null &&
          !String.valueOf (ed.getOID_Tipo_Movimento ()).equals ("") &&
          !String.valueOf (ed.getOID_Tipo_Movimento ()).equals ("0") &&
          !String.valueOf (ed.getOID_Tipo_Movimento ()).equals ("null")) {
        sql += " and Tipos_Movimentos.OID_Tipo_Movimento = '" + ed.getOID_Tipo_Movimento () + "'";
      }

      if (ed.getVL_Cobrado () > 0) {
        sql += " and Documentos_Lotes_Fornecedores.VL_Cobrado = " + ed.getVL_Cobrado ();
      }

      if (String.valueOf (ed.getOID_Pessoa ()) != null &&
          !String.valueOf (ed.getOID_Pessoa ()).equals ("") &&
          !String.valueOf (ed.getOID_Pessoa ()).equals ("null")) {
        sql += " and Conhecimentos.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
      }

      if (String.valueOf (ed.getOID_Pessoa_Destinatario ()) != null &&
          !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("") &&
          !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("null")) {
        sql += " and Conhecimentos.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario () + "'";
      }

      if (String.valueOf (ed.getNR_Conhecimento_Inicial ()) != null &&
          !String.valueOf (ed.getNR_Conhecimento_Inicial ()).equals ("0") &&
          !String.valueOf (ed.getNR_Conhecimento_Inicial ()).equals ("") &&
          !String.valueOf (ed.getNR_Conhecimento_Inicial ()).equals ("null")) {
        sql += " and Conhecimentos.NR_Conhecimento >= " + ed.getNR_Conhecimento_Inicial ();
      }

      if (String.valueOf (ed.getNR_Conhecimento_Final ()) != null &&
          !String.valueOf (ed.getNR_Conhecimento_Inicial ()).equals ("0") &&
          !String.valueOf (ed.getNR_Conhecimento_Final ()).equals ("") &&
          !String.valueOf (ed.getNR_Conhecimento_Final ()).equals ("null")) {
        sql += " and Conhecimentos.NR_Conhecimento <= " + ed.getNR_Conhecimento_Final ();
      }

      // System.out.println ("filtro delete Aprov-> " + sql);

      int qt_cto = 0;
      res = this.executasql.executarConsulta (sql);

      while (res.next ()) {
        qt_cto++;

        // System.out.println ("Lendo Cto => " + qt_cto + "  " + res.getString ("oid_Documento_Lote_Fornecedor"));

        Documento_Lote_FornecedorED edLote = new Documento_Lote_FornecedorED ();
        edLote.setOID_Documento_Lote_Fornecedor (res.getString ("oid_Documento_Lote_Fornecedor"));

        this.deleta (edLote);

      }

      // System.out.println ("exclui Documento_Lote_Faturamento");

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMetodo ("deleta_Cto_Filtro");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return ed;
  }

  public Documento_Lote_FornecedorED rateio_Cto_Filtro (Documento_Lote_FornecedorED ed) throws Excecoes {

    // System.out.println ("rateio_Cto_Filtro BD novo");

    String sql = null;
    String selecao = "";
    ResultSet res = null;

    try {
      sql = " SELECT Conhecimentos.NR_Conhecimento, Conhecimentos.VL_Total_Frete, Conhecimentos.NR_Peso, Conhecimentos.NR_Peso_Cubado " +
          " FROM   Documentos_Lotes_Fornecedores, Conhecimentos " +
          " WHERE  Documentos_Lotes_Fornecedores.oid_Conhecimento = Conhecimentos.oid_Conhecimento " +
          " AND    Documentos_Lotes_Fornecedores.oid_Lote_Fornecedor = ('" + ed.getOID_Lote_Fornecedor () + "')";

          if ("R".equals(ed.getDM_Rateio())){
            sql +=" AND    Documentos_Lotes_Fornecedores.OID_Tipo_Movimento = '" + ed.getOID_Tipo_Movimento () + "'";
          }else{
            sql +=" AND    Documentos_Lotes_Fornecedores.OID_Tipo_Movimento <> '" + ed.getOID_Tipo_Movimento () + "'";
          }

      if (String.valueOf (ed.getOID_Unidade ()) != null &&
          !String.valueOf (ed.getOID_Unidade ()).equals ("") &&
          !String.valueOf (ed.getOID_Unidade ()).equals ("0") &&
          !String.valueOf (ed.getOID_Unidade ()).equals ("null")) {
        selecao += " and Conhecimentos.OID_Unidade = '" + ed.getOID_Unidade () + "'";
      }

      if (String.valueOf (ed.getOID_Pessoa ()) != null &&
          !String.valueOf (ed.getOID_Pessoa ()).equals ("") &&
          !String.valueOf (ed.getOID_Pessoa ()).equals ("null")) {
        selecao += " and Conhecimentos.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
      }

      if (String.valueOf (ed.getOID_Pessoa_Destinatario ()) != null &&
          !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("") &&
          !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("null")) {
        selecao += " and Conhecimentos.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario () + "'";
      }

      if (String.valueOf (ed.getNR_Conhecimento_Inicial ()) != null &&
          !String.valueOf (ed.getNR_Conhecimento_Inicial ()).equals ("0") &&
          !String.valueOf (ed.getNR_Conhecimento_Inicial ()).equals ("") &&
          !String.valueOf (ed.getNR_Conhecimento_Inicial ()).equals ("null")) {
        selecao += " and Conhecimentos.NR_Conhecimento >= " + ed.getNR_Conhecimento_Inicial ();
      }

      if (String.valueOf (ed.getNR_Conhecimento_Final ()) != null &&
          !String.valueOf (ed.getNR_Conhecimento_Inicial ()).equals ("0") &&
          !String.valueOf (ed.getNR_Conhecimento_Final ()).equals ("") &&
          !String.valueOf (ed.getNR_Conhecimento_Final ()).equals ("null")) {
        selecao += " and Conhecimentos.NR_Conhecimento <= " + ed.getNR_Conhecimento_Final ();
      }

      // System.out.println ("filtro rateio_Cto_Filtro-> " + selecao);

      int qt_cto = 0;
      double vl_Rateio = ed.getVL_Cobrado ();
      double vl_Movimento = 0;
      double NR_Peso_Total = 0;
      double VL_Total_Frete = 0;

      sql += selecao;
      // System.out.println ("filtro delete Aprov-> " + sql);

      res = this.executasql.executarConsulta (sql);

      while (res.next ()) {
        qt_cto++;

        if (res.getDouble ("NR_Peso_Cubado") > res.getDouble ("NR_Peso")) {
          NR_Peso_Total += res.getDouble ("NR_Peso_Cubado");
        }
        else {
          NR_Peso_Total += res.getDouble ("NR_Peso");
        }

        VL_Total_Frete += res.getDouble ("VL_Total_Frete");
      }

      // System.out.println ("NR_Peso_Total-> " + NR_Peso_Total);

      if (NR_Peso_Total + VL_Total_Frete > 0) {
        sql = " SELECT Documentos_Lotes_Fornecedores.oid_documento_lote_fornecedor, Conhecimentos.OID_Conhecimento, Conhecimentos.VL_Total_Frete, Conhecimentos.NR_Peso, Conhecimentos.NR_Peso_Cubado, Conhecimentos.NR_Conhecimento " +
            " FROM   Documentos_Lotes_Fornecedores, Conhecimentos " +
            " WHERE  Documentos_Lotes_Fornecedores.oid_Conhecimento = Conhecimentos.oid_Conhecimento " +
            " AND    Documentos_Lotes_Fornecedores.oid_Lote_Fornecedor = ('" + ed.getOID_Lote_Fornecedor () + "')" ;

          if ("R".equals(ed.getDM_Rateio())){
            sql +=" AND    Documentos_Lotes_Fornecedores.OID_Tipo_Movimento = '" + ed.getOID_Tipo_Movimento () + "'";
          }else{
            sql +=" AND    Documentos_Lotes_Fornecedores.OID_Tipo_Movimento <> '" + ed.getOID_Tipo_Movimento () + "'";
          }

        sql += selecao;
        // System.out.println ("filtro rateio_Cto_Filtro-> " + sql);

        res = this.executasql.executarConsulta (sql);

        while (res.next ()) {

          if ("P".equals (ed.getDM_Criterio())) {
            if (res.getDouble ("NR_Peso_Cubado") > res.getDouble ("NR_Peso")) {
              vl_Movimento = (vl_Rateio / NR_Peso_Total * res.getDouble ("NR_Peso_Cubado"));
            }
            else {
              vl_Movimento = (vl_Rateio / NR_Peso_Total * res.getDouble ("NR_Peso"));
            }
          }else{
            vl_Movimento = (vl_Rateio / VL_Total_Frete * res.getDouble ("VL_Total_Frete"));
          }

          if (vl_Movimento > vl_Rateio) {
            vl_Movimento = vl_Rateio;
          }


        // System.out.println ("CTO :"+ res.getString ("NR_Conhecimento")+ " Tipo_Movimento" + ed.getOID_Tipo_Movimento () + " VALOR" + vl_Movimento);

          if (vl_Movimento > 0) {
            if ("R".equals(ed.getDM_Rateio())){
              
              sql = " update Documentos_Lotes_Fornecedores set  VL_Cobrado = " + vl_Movimento;
              sql += " where oid_Documento_Lote_Fornecedor = '" + res.getString("oid_documento_lote_fornecedor") + "'";

              // System.out.println ("altera CTO :"+ sql);

              executasql.executarUpdate (sql);

            }else{
              Documento_Lote_FornecedorED edLote = new Documento_Lote_FornecedorED ();
              edLote.setOID_Lote_Fornecedor (ed.getOID_Lote_Fornecedor ());
              edLote.setOID_Tipo_Movimento (ed.getOID_Tipo_Movimento ());
              edLote.setVL_Cobrado (vl_Movimento);
              edLote.setOID_Conhecimento (res.getString ("oid_Conhecimento"));
              edLote.setNR_Documento (res.getString ("NR_Conhecimento"));
              edLote.setDM_Tipo_Documento ("C");
              inclui (edLote);
            }
          }
        }
      }

      // System.out.println ("rateio_Cto_Filtro");

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMetodo ("rateio_Cto_Filtro");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return ed;
  }




}
