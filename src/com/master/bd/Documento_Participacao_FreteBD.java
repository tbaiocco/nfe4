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

import com.master.ed.Documento_Participacao_FreteED;
import com.master.root.FormataDataBean;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;
import com.master.util.bd.Transacao;
import com.master.util.ed.Parametro_FixoED;

public class Documento_Participacao_FreteBD
    extends Transacao {

  private ExecutaSQL executasql;
  Parametro_FixoED edParametro_Fixo = new Parametro_FixoED ();

  public Documento_Participacao_FreteBD (ExecutaSQL sql) {
    this.executasql = sql;
  }

  public void altera (Documento_Participacao_FreteED ed) throws Excecoes {

    String sql = null;

    try {

      sql = " update Documentos_Participacoes_Fretes set ... ";
      sql += " where oid_Documento_Participacao_Frete = '" + ed.getOID_Documento_Participacao_Frete () + "'";

      // System.out.println (sql);

      int i = executasql.executarUpdate (sql);

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

  public void deleta (Documento_Participacao_FreteED ed) throws Excecoes {

    // System.out.println("delete Documento_Participacao_Frete ->> " );


    String sql = null;
    String oid_Processo = null;
    String oid_Conhecimento = null;
    String dm_Tipo_Documento="";
    try {

      sql = " SELECT DM_Tipo_Documento, oid_processo, oid_Conhecimento " +
            " FROM Documentos_Participacoes_Fretes WHERE oid_Documento_Participacao_Frete = '" +  ed.getOID_Documento_Participacao_Frete () + "'";

      ResultSet res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
    	  oid_Processo = res.getString ("oid_processo");
    	  oid_Conhecimento = res.getString ("oid_Conhecimento");
    	  dm_Tipo_Documento = res.getString ("DM_Tipo_Documento");
      }

      sql = "DELETE FROM Documentos_Participacoes_Fretes WHERE oid_Documento_Participacao_Frete = ";
      sql += "('" + ed.getOID_Documento_Participacao_Frete () + "')";

      executasql.executarUpdate (sql);
      
      if ("CTO".equals(dm_Tipo_Documento)) {
          sql = " UPDATE Conhecimentos set oid_Participacao_Frete = null " +
          " WHERE oid_Conhecimento = '" + oid_Conhecimento + "'";
          // System.out.println ("UPDATE  " + sql);
          executasql.executarUpdate (sql);
      }


    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMetodo ("excluir");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public ArrayList lista (Documento_Participacao_FreteED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    double vl_total_frete = 0, vl_total_comissao=0;
    try {

      sql = " SELECT * FROM Documentos_Participacoes_Fretes " +
            " WHERE  oid_Participacao_Frete = '" + ed.getOID_Participacao_Frete () + "'" +
            " Order by NR_Documento ";
      // System.out.println (" lista->> " + sql);

      ResultSet res = null;
      ResultSet res2 = null;

      res = this.executasql.executarConsulta (sql);

      FormataDataBean DataFormatada = new FormataDataBean ();

      //popula
      while (res.next ()) {
        // System.out.println (" lista->> 2");

        Documento_Participacao_FreteED edVolta = new Documento_Participacao_FreteED ();

        edVolta.setOID_Participacao_Frete (res.getLong ("OID_Participacao_Frete"));
        edVolta.setOID_Documento_Participacao_Frete (res.getString ("OID_Documento_Participacao_Frete"));

        edVolta.setOID_Conhecimento (res.getString ("oid_Conhecimento"));
        edVolta.setNR_Documento (res.getString ("NR_Documento"));
        edVolta.setVL_Comissao(res.getDouble ("VL_Comissao"));
        edVolta.setPE_Comissao(res.getDouble ("PE_Comissao"));
        edVolta.setVL_Frete_Minimo(res.getDouble ("VL_Frete_Minimo"));
        vl_total_comissao+=res.getDouble ("VL_Comissao");
        if (res.getString ("oid_Conhecimento") != null ) {
          sql = " SELECT Pessoa_Remet.NM_Razao_Social as NM_Remetente, " +
          		"        Pessoa_Dest.NM_Razao_Social as NM_Destinatario , " + 
          		"        Conhecimentos.VL_Total_Frete, DT_Emissao " +
                " FROM   Conhecimentos , Pessoas Pessoa_Remet, Pessoas Pessoa_Dest " +
                " WHERE  Conhecimentos.oid_Pessoa = Pessoa_Remet.oid_Pessoa  " +
                " AND    Conhecimentos.oid_Pessoa_Destinatario = Pessoa_Dest.oid_Pessoa  " +
                " AND    Conhecimentos.oid_Conhecimento = '" + res.getString ("oid_Conhecimento") + "'";
      
          // System.out.println (" lista cto->> " + sql);
      
          res2 = this.executasql.executarConsulta (sql);
          while (res2.next ()) {
            // System.out.println (" lista->> 5");
            
            DataFormatada.setDT_FormataData (res2.getString("DT_Emissao"));
            edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());
            
            vl_total_frete += res2.getDouble ("VL_Total_Frete");
            edVolta.setNM_Pessoa_Remetente(res2.getString("NM_Remetente"));
            edVolta.setNM_Pessoa_Destinatario(res2.getString("NM_Destinatario"));
            edVolta.setVL_Total_Frete (res2.getDouble ("VL_Total_Frete"));
          }
          // System.out.println (" lista->> 6");
      
        }
        // System.out.println (" lista->> 7");

        list.add (edVolta);
      }
      Documento_Participacao_FreteED edVolta = new Documento_Participacao_FreteED ();
      edVolta.setOID_Documento_Participacao_Frete ("");
      edVolta.setOID_Conhecimento ("");
      edVolta.setNR_Documento ("Total---->>");
      edVolta.setVL_Total_Frete (vl_total_frete);
      edVolta.setVL_Comissao (vl_total_comissao);
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

  public Documento_Participacao_FreteED getByRecord (Documento_Participacao_FreteED ed) throws Excecoes {

    String sql = null;

    Documento_Participacao_FreteED edVolta = new Documento_Participacao_FreteED ();

    try {

      sql = " SELECT " +
          " Documentos_Participacoes_Fretes.OID_Participacao_Frete, " +
          " Documentos_Participacoes_Fretes.OID_Documento_Participacao_Frete, " +
          " Documentos_Participacoes_Fretes.oid_Conhecimento, " +
          " Documentos_Participacoes_Fretes.NR_Documento, " +
          " Documentos_Participacoes_Fretes.vl_faturado, " +
          " Documentos_Participacoes_Fretes.DM_Tipo_Documento, " +
          " Participacoes_Fretes.oid_unidade, " +
          " Participacoes_Fretes.dt_emissao, " +
          " Participacoes_Fretes.oid_pessoa " +
          " from Documentos_Participacoes_Fretes, Participacoes_Fretes " +
          " WHERE Documentos_Participacoes_Fretes.OID_Participacao_Frete = Participacoes_Fretes.OID_Participacao_Frete" ;
          if (ed.getOID_Documento_Participacao_Frete () != null && ed.getOID_Documento_Participacao_Frete () != "" && ed.getOID_Documento_Participacao_Frete () != "null"){
            sql +=" AND Documentos_Participacoes_Fretes.OID_Documento_Participacao_Frete = '" + ed.getOID_Documento_Participacao_Frete () + "'";
          }else {
            sql +=" AND Documentos_Participacoes_Fretes.OID_Participacao_Frete = '" + ed.getOID_Participacao_Frete () + "'" +
                  " AND Documentos_Participacoes_Fretes.OID_Conhecimento = '" + ed.getOID_Conhecimento () + "'" ;
          }


      // System.out.println (" lista->> " + sql);

      FormataDataBean DataFormatada = new FormataDataBean ();

      ResultSet res = null;
      ResultSet res2 = null;
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta.setOID_Participacao_Frete (res.getLong ("OID_Participacao_Frete"));
        edVolta.setOID_Documento_Participacao_Frete (res.getString ("OID_Documento_Participacao_Frete"));
        edVolta.setOID_Conhecimento (res.getString ("oid_Conhecimento"));
        edVolta.setNR_Documento (res.getString ("NR_Documento"));
        edVolta.setDM_Tipo_Documento (res.getString ("DM_Tipo_Documento"));
        edVolta.setVL_Faturado (res.getDouble ("vl_faturado"));
        edVolta.setOID_Unidade (res.getLong ("oid_unidade"));
        edVolta.setOID_Pessoa (res.getString ("oid_pessoa"));
        edVolta.setDT_Emissao (res.getString ("DT_Emissao"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
        edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());

        // System.out.println (" lista->> 4");

        if (res.getString ("oid_Conhecimento") != null &&
            (res.getString ("DM_Tipo_Documento").equals ("C") ||
             res.getString ("DM_Tipo_Documento").equals ("CTO") ||
             res.getString ("DM_Tipo_Documento").equals ("MIN"))) {
          sql = " SELECT VL_Total_Frete from Conhecimentos " +
              " WHERE Conhecimentos.oid_Conhecimento = '" + res.getString ("oid_Conhecimento") + "'";

          // System.out.println (" lista cto->> " + sql);

          res2 = this.executasql.executarConsulta (sql);
          while (res2.next ()) {
            // System.out.println (" lista->> 5");

            edVolta.setVL_Total_Frete (res2.getDouble ("VL_Total_Frete"));
          }
          // System.out.println (" lista->> 6");

        }
        else {
          sql = " SELECT " +
              " Documentos_Participacoes_Fretes.oid_Conhecimento " +
              " from Documentos_Participacoes_Fretes " +
              " WHERE Documentos_Participacoes_Fretes.dm_tipo_documento = 'C'" +
              " AND Documentos_Participacoes_Fretes.OID_Participacao_Frete = " + edVolta.getOID_Participacao_Frete ();
          ResultSet resLocal = null;
          resLocal = this.executasql.executarConsulta (sql);
          while (resLocal.next ()) {
            edVolta.setOID_Conhecimento (resLocal.getString ("oid_Conhecimento"));
          }
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

  public Documento_Participacao_FreteED inclui (Documento_Participacao_FreteED ed) throws Excecoes {

    String sql = null;
    String chave = null;
    Documento_Participacao_FreteED edVolta = new Documento_Participacao_FreteED ();
    ResultSet res = null;
    double pe_comissao=0;
    double vl_frete_minimo=0;
    double vl_comissao=0;

    try {
    	
    	sql = "SELECT Tabelas_Fretes.PE_Frete_Entrega, Tabelas_Fretes.VL_Frete_Minimo, Tabelas_Fretes.VL_Frete, Tabelas_Fretes.DM_Tipo_Frete, Tabelas_Fretes.VL_Taxas, Tabelas_Fretes.VL_Outros2,  Conhecimentos.VL_Total_Frete,  Conhecimentos.NR_Peso " +
        	" FROM  Tabelas_Fretes, Unidades, Conhecimentos " +
        	" WHERE Tabelas_Fretes.oid_Pessoa = Unidades.oid_Pessoa " +
        	" AND   Unidades.oid_Unidade = Conhecimentos.oid_Unidade " +
    		" AND   Conhecimentos.oid_Conhecimento = '" + ed.getOID_Conhecimento() + "'";
    	// System.out.println ("->>"+sql);

	    res = this.executasql.executarConsulta (sql);
	    while (res.next ()) {
	    	pe_comissao=res.getDouble("PE_Frete_Entrega");
	    	vl_frete_minimo=res.getDouble("VL_Frete_Minimo");
	        vl_comissao = (res.getDouble ("vl_total_frete") * pe_comissao / 100);

	        ///TESTAR ROTINA - Rp	
	        if (1==2) {
		        if (res.getDouble("VL_Frete")>0) {
		        	if ("K".equals(res.getString("DM_Tipo_Frete"))) {
		        		vl_comissao+=res.getDouble("VL_Frete") * res.getDouble("NR_Peso");
		        	}
		        	if ("T".equals(res.getString("DM_Tipo_Frete"))) {
		        		vl_comissao+=res.getDouble("VL_Frete") * res.getDouble("NR_Peso")/1000;
		        	}
		        	if ("E".equals(res.getString("DM_Tipo_Frete"))) {
		        		//vl_comissao+=res.getDouble("VL_Frete") * res.getDouble("NR_Peso")/1000;
		                //VL_Movimento_Frete = tabelaFreteED.getVL_Outros2 () + (tabelaFreteED.getVL_Frete () * (ed.getNR_Peso_Tabela () - (tabelaFreteED.getNR_Peso_Inicial () - 1)));
		        	}
		        }
		        if (res.getDouble("VL_Taxas")>0) {
		        	vl_comissao+=res.getDouble("VL_Taxas");
		        }
	        }    
	        
	        if (vl_comissao<vl_frete_minimo) vl_comissao=vl_frete_minimo;
	    }
	    
      chave = (String.valueOf (ed.getOID_Participacao_Frete ()) + String.valueOf (System.currentTimeMillis ()));

      // System.out.println (" inclui->> " + chave);

      sql = " INSERT into Documentos_Participacoes_Fretes (OID_Documento_Participacao_Frete, NR_Documento, DM_Tipo_Documento, OID_Conhecimento, OID_Participacao_Frete, OID_Processo, VL_Faturado, PE_Comissao, VL_Comissao, vl_frete_minimo) values ";
      sql += "('" + chave + "','" + ed.getNR_Documento () + "','" + ed.getDM_Tipo_Documento () + "','" + ed.getOID_Conhecimento () + "'," + ed.getOID_Participacao_Frete () + "," + ed.getOID_Processo () + "," + ed.getVL_Faturado () + "," + pe_comissao + "," + vl_comissao + "," + vl_frete_minimo + ")";

      // System.out.println ("inclui 1 " + sql);

      executasql.executarUpdate (sql);

      //Cto
      if (ed.getOID_Conhecimento () != null && ed.getOID_Conhecimento ().length () > 4) {
        sql = " UPDATE Conhecimentos SET oid_Participacao_Frete = " + ed.getOID_Participacao_Frete () +
              " WHERE oid_Conhecimento = '" + ed.getOID_Conhecimento () + "'";
        // System.out.println ("UPDATE  " + sql);
        executasql.executarUpdate (sql);
      }
      
      //Processos
      if (ed.getOID_Processo () != null && !ed.getOID_Processo ().equals ("null")) {

        sql = " UPDATE Processos SET oid_Participacao_Frete = " + ed.getOID_Participacao_Frete ()  +
              " WHERE oid_Processo = '" + ed.getOID_Processo () + "'";
        // System.out.println ("UPDATE  " + sql);
        executasql.executarUpdate (sql);
      }


      edVolta.setOID_Documento_Participacao_Frete (chave);

      // System.out.println ("kieli 2 incluiu Documento_Participacao_Frete");

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

  public Documento_Participacao_FreteED inclui_Cto_Filtro (Documento_Participacao_FreteED ed) throws Excecoes {

    String sql = null;
    ResultSet res = null;
    long oid_Unidade=0;

    try {

      sql = " SELECT  oid_Unidade " + 
            " FROM  Participacoes_Fretes " +
            " WHERE oid_Participacao_Frete="+ ed.getOID_Participacao_Frete();
      res = this.executasql.executarConsulta(sql);

      if (res.next ()) {
    	  oid_Unidade=res.getLong("oid_Unidade");
      }
    	
    	
      sql = "SELECT  Conhecimentos.oid_Conhecimento, Conhecimentos.NR_Conhecimento, Conhecimentos.VL_Total_Frete " +
          " FROM   Conhecimentos " +
          " WHERE  Conhecimentos.VL_Total_Frete >0 " +
          " AND    Conhecimentos.DM_Situacao <>'C'  " +
          " AND    Conhecimentos.DM_Impresso = 'S'  " +
          " AND   (Conhecimentos.oid_Participacao_Frete is null OR Conhecimentos.oid_Participacao_Frete =0) " +
          " AND    Conhecimentos.OID_Unidade = " + oid_Unidade ;

      if (String.valueOf (ed.getOID_Modal ()) != null &&
          !String.valueOf (ed.getOID_Modal ()).equals ("") &&
          !String.valueOf (ed.getOID_Modal ()).equals ("0") &&
          !String.valueOf (ed.getOID_Modal ()).equals ("null")) {
        sql += " and Conhecimentos.OID_Modal = '" + ed.getOID_Modal () + "'";
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

      // System.out.println("filtro Ctos Manif " + sql);

      int qt_cto=0;
      res = this.executasql.executarConsulta(sql);

      while (res.next () ) {
        qt_cto++;
    
        // System.out.println ("Lendo Cto => " + qt_cto + "  " + res.getString ("oid_Conhecimento"));

        Documento_Participacao_FreteED edLote = new Documento_Participacao_FreteED ();
        edLote.setOID_Participacao_Frete (ed.getOID_Participacao_Frete ());
        edLote.setOID_Unidade(oid_Unidade);
        edLote.setOID_Conhecimento (res.getString ("oid_Conhecimento"));
        edLote.setNR_Documento (res.getString ("NR_Conhecimento"));
        edLote.setDM_Tipo_Documento ("CTO");
        edLote.setVL_Faturado (res.getDouble ("vl_total_frete"));
        
        inclui(edLote);
        
      }

      // System.out.println ("incluiu Documento_Participacao_Frete");

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

}
