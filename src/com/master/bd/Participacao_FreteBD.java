package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.Documento_Participacao_FreteED;
import com.master.ed.Participacao_FreteED;
import com.master.rl.Participacao_FreteRL;
import com.master.root.FormataDataBean;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;


public class Participacao_FreteBD extends BancoUtil {

  private ExecutaSQL executasql;
  Parametro_FixoED edParametro_Fixo = new Parametro_FixoED ();

  public Participacao_FreteBD (ExecutaSQL sql) {
    this.executasql = sql;
  }

  public Participacao_FreteED inclui (Participacao_FreteED ed) throws Excecoes {

    String sql = null;
    long valOid = 0;
    String chave = null;

    Participacao_FreteED edVolta = new Participacao_FreteED ();
    try {

      // System.out.println ("Participacao_FreteED bd 1 ");

      //pega proximo valor da chave
      ResultSet rs = executasql.executarConsulta (
          "select max(OID_Participacao_Frete) as result from Participacoes_Fretes");
      while (rs.next ()) {
        valOid = rs.getLong ("result") + 1;
      }

      ed.setOID_Participacao_Frete (valOid);

      sql = " insert into Participacoes_Fretes (OID_Participacao_Frete, VL_Cobranca, OID_Pessoa, DM_Situacao, OID_Unidade,  DT_Vencimento, DT_Emissao, TX_Observacao ) values ";
      sql += "(" + ed.getOID_Participacao_Frete () + "," + ed.getVL_Cobranca () + ",'" + ed.getOID_Pessoa () + "','" + ed.getDM_Situacao () + "'," + ed.getOID_Unidade () + ",'" + ed.getDT_Vencimento () + "','" +
          ed.getDT_Emissao () + "','" + ed.getTX_Observacao () + "')";

      // System.out.println ("inclui ============>> " + sql);

      int i = executasql.executarUpdate (sql);

      edVolta.setOID_Participacao_Frete (ed.getOID_Participacao_Frete ());

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao incluir Participacao_Frete");
      excecoes.setMetodo ("inclui");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return edVolta;

  }

  public void altera (Participacao_FreteED ed) throws Excecoes {

    String sql = null;

    try {

      sql = " update Participacoes_Fretes set  DT_Vencimento = '" +
          ed.getDT_Vencimento () + "' ";
      sql += " where oid_Participacao_Frete = " + ed.getOID_Participacao_Frete ();

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

  public void deleta (Participacao_FreteED ed) throws Excecoes {

    // System.out.println ("exclui Participacao_Frete ");

    String sql = null;
    ResultSet res = null;

    try {

      sql = " SELECT oid_Documento_Participacao_Frete FROM Documentos_Participacoes_Fretes " +
            " WHERE  oid_Participacao_Frete = ('" + ed.getOID_Participacao_Frete () + "')";
      // System.out.println (" deleta1->> " + sql);

      res = this.executasql.executarConsulta (sql);

      while (res.next ()) {
    	  Documento_Participacao_FreteED edDoc = new Documento_Participacao_FreteED ();
    	  edDoc.setOID_Documento_Participacao_Frete(res.getString("oid_Documento_Participacao_Frete"));
    	  new Documento_Participacao_FreteBD (this.executasql).deleta(edDoc);
      }   

      sql = "delete from Participacoes_Fretes WHERE oid_Participacao_Frete = ";
      sql += "(" + ed.getOID_Participacao_Frete () + ")";
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

  public ArrayList lista (Participacao_FreteED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();

    try {

      sql = " SELECT * from  Participacoes_Fretes,  pessoas, Unidades " +
          " WHERE Participacoes_Fretes.oid_unidade = Unidades.oid_unidade  " +
          " AND   Participacoes_Fretes.oid_Pessoa = pessoas.oid_Pessoa  ";

      if (String.valueOf (ed.getOID_Participacao_Frete ()) != null &&
          !String.valueOf (ed.getOID_Participacao_Frete ()).equals ("") &&
          !String.valueOf (ed.getOID_Participacao_Frete ()).equals ("0") &&
          !String.valueOf (ed.getOID_Participacao_Frete ()).equals ("null")) {
        sql += " and Participacoes_Fretes.OID_Participacao_Frete = " + ed.getOID_Participacao_Frete ();
      }

      if (String.valueOf (ed.getNR_Duplicata ()) != null &&
          !String.valueOf (ed.getNR_Duplicata ()).equals ("") &&
          !String.valueOf (ed.getNR_Duplicata ()).equals ("0") &&
          !String.valueOf (ed.getNR_Duplicata ()).equals ("null")) {
        //sql += " and Participacoes_Fretes.NR_Duplicata = " + ed.getNR_Duplicata ();
      }

      if (String.valueOf (ed.getDT_Emissao_Inicial ()) != null &&
          !String.valueOf (ed.getDT_Emissao_Inicial ()).equals ("") &&
          !String.valueOf (ed.getDT_Emissao_Inicial ()).equals ("null")) {
        sql += " and Participacoes_Fretes.DT_Emissao >= '" + ed.getDT_Emissao_Inicial () + "'";
      }

      if (String.valueOf (ed.getDT_Emissao_Final ()) != null &&
          !String.valueOf (ed.getDT_Emissao_Final ()).equals ("") &&
          !String.valueOf (ed.getDT_Emissao_Final ()).equals ("null")) {
        sql += " and Participacoes_Fretes.DT_Emissao <= '" + ed.getDT_Emissao_Final () + "'";
      }

      if (String.valueOf (ed.getOID_Unidade ()) != null &&
          !String.valueOf (ed.getOID_Unidade ()).equals ("0") &&
          !String.valueOf (ed.getOID_Unidade ()).equals ("null")) {
        sql += " and Participacoes_Fretes.OID_Unidade = " + ed.getOID_Unidade ();
      }

      if (String.valueOf (ed.getOID_Pessoa ()) != null &&
          !String.valueOf (ed.getOID_Pessoa ()).equals ("") &&
          !String.valueOf (ed.getOID_Pessoa ()).equals ("0") &&
          !String.valueOf (ed.getOID_Pessoa ()).equals ("null")) {
        sql += " and Participacoes_Fretes.OID_Pessoa = '" + ed.getOID_Pessoa () +
            "'";
      }

      if (String.valueOf (ed.getDM_Situacao ()) != null &&
          !String.valueOf (ed.getDM_Situacao ()).equals ("") &&
          !String.valueOf (ed.getDM_Situacao ()).equals ("null")) {
        sql += " and Participacoes_Fretes.DM_Situacao = '" + ed.getDM_Situacao () +
            "'";
      }

      sql += " Order by Participacoes_Fretes.OID_Participacao_Frete ";

      // System.out.println (">>>>>>>>>>>>>>> " + sql);

      ResultSet res = null;
      ResultSet resCTRC = null;
      
      res = this.executasql.executarConsulta (sql);

      FormataDataBean DataFormatada = new FormataDataBean ();

      //popula
      while (res.next ()) {
        Participacao_FreteED edVolta = new Participacao_FreteED ();

        // System.out.println ("ok ");

        edVolta.setOID_Participacao_Frete (res.getLong ("OID_Participacao_Frete"));
        // System.out.println ("ok 2");

        edVolta.setDT_Emissao (res.getString ("DT_Emissao"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
        edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());
        // System.out.println ("ok 3");

        edVolta.setOID_Duplicata (res.getLong ("oid_Duplicata"));
        edVolta.setOID_Conhecimento ("");
        if (doValida(res.getString("oid_Conhecimento"))) {
            edVolta.setOID_Conhecimento (res.getString ("oid_Conhecimento"));

        	sql = "SELECT Conhecimentos.NR_Conhecimento " +
	        " FROM  Conhecimentos " +
	        " WHERE oid_Conhecimento = '" + res.getString("oid_Conhecimento") + "'";
	
		    resCTRC = this.executasql.executarConsulta (sql);
		    if (resCTRC.next ()) {
		      edVolta.setNR_Duplicata (resCTRC.getLong ("NR_Conhecimento"));
		    }
        }    

        // System.out.println ("ok 4");

        // System.out.println ("8 ");
        edVolta.setNM_Razao_Social (res.getString ("NM_Razao_Social"));

        edVolta.setCD_Unidade (res.getString ("CD_Unidade"));
        // System.out.println ("9 ");
        edVolta.setVL_Total_Frete (0);
        
        sql = "SELECT SUM (Conhecimentos.vl_Total_Frete) as tt_Frete, SUM (Documentos_Participacoes_Fretes.vl_Comissao) as tt_Comissao " +
        " FROM  Conhecimentos, Documentos_Participacoes_Fretes " +
        " WHERE Conhecimentos.oid_Conhecimento = Documentos_Participacoes_Fretes.oid_Conhecimento " +
        " AND   Documentos_Participacoes_Fretes.oid_Participacao_Frete=" + edVolta.getOID_Participacao_Frete ();
        // System.out.println ("lot ->>" + sql);
	    resCTRC = this.executasql.executarConsulta (sql);
	    if (resCTRC.next ()) {
	        edVolta.setVL_Total_Frete (resCTRC.getDouble("tt_Frete"));
	        edVolta.setVL_Comissao (resCTRC.getDouble("tt_Comissao"));
	    }
        edVolta.setVL_Cobranca (edVolta.getVL_Comissao()-edVolta.getVL_Descontos());

        list.add (edVolta);
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao listar Participacao_Frete");
      excecoes.setMetodo ("lista");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return list;
  }

  public Participacao_FreteED getByRecord (Participacao_FreteED ed) throws Excecoes {

    String sql = null;

    Participacao_FreteED edVolta = new Participacao_FreteED ();

    try {

      sql = " SELECT *, Participacoes_Fretes.oid_Pessoa as oid_Pessoa_Lote " +
          " FROM  Participacoes_Fretes, pessoas, Unidades " +
          " WHERE Participacoes_Fretes.oid_unidade = Unidades.oid_unidade  " +
          " and   Unidades.oid_Pessoa = Pessoas.oid_pessoa  ";

      if (String.valueOf (ed.getOID_Participacao_Frete ()) != null &&
          !String.valueOf (ed.getOID_Participacao_Frete ()).equals ("") &&
          !String.valueOf (ed.getOID_Participacao_Frete ()).equals ("null")) {
        sql += " and Participacoes_Fretes.OID_Participacao_Frete = " +
            ed.getOID_Participacao_Frete ();
      }

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      ResultSet resCTRC = null;

      FormataDataBean DataFormatada = new FormataDataBean ();

      while (res.next ()) {
        edVolta.setOID_Participacao_Frete (res.getLong ("OID_Participacao_Frete"));
        edVolta.setDM_Situacao (res.getString ("DM_Situacao"));
        edVolta.setOID_Pessoa (res.getString ("oid_Pessoa_Lote"));
        edVolta.setOID_Duplicata (res.getLong ("oid_Duplicata"));
        edVolta.setOID_Conhecimento ("");

        sql = "SELECT Conhecimentos.NR_Conhecimento, Conhecimentos.oid_Conhecimento " +
	        " FROM  Conhecimentos " +
	        " WHERE Conhecimentos.DM_Tipo_Documento='C'" +
	        " AND   Conhecimentos.OID_Participacao_Frete = '" + res.getString("OID_Participacao_Frete") + "'";
	
		    resCTRC = this.executasql.executarConsulta (sql);
		    if (resCTRC.next ()) {
		      edVolta.setNR_Conhecimento (resCTRC.getLong ("NR_Conhecimento"));
		      edVolta.setOID_Conhecimento(resCTRC.getString("oid_Conhecimento"));
		    }  

		edVolta.setDT_Vencimento (res.getString ("DT_Vencimento"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Vencimento ());
        edVolta.setDT_Vencimento (DataFormatada.getDT_FormataData ());

        edVolta.setDT_Emissao (res.getString ("DT_Emissao"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
        edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());
        edVolta.setTX_Observacao (res.getString ("TX_Observacao"));
        edVolta.setOID_Unidade (res.getLong ("OID_Unidade"));
        edVolta.setCD_Unidade (res.getString ("CD_Unidade"));

        
        edVolta.setVL_Total_Frete (0);
        
        sql = "SELECT SUM (Conhecimentos.vl_Total_Frete) as tt_Frete, SUM (Documentos_Participacoes_Fretes.vl_Comissao) as tt_Comissao " +
        " FROM  Conhecimentos, Documentos_Participacoes_Fretes " +
        " WHERE Conhecimentos.oid_Conhecimento = Documentos_Participacoes_Fretes.oid_Conhecimento " +
        " AND   Documentos_Participacoes_Fretes.oid_Participacao_Frete=" + ed.getOID_Participacao_Frete ();
        // System.out.println ("lot ->>" + sql);
	    resCTRC = this.executasql.executarConsulta (sql);
	    if (resCTRC.next ()) {
	        edVolta.setVL_Total_Frete (resCTRC.getDouble("tt_Frete"));
	        edVolta.setVL_Comissao (resCTRC.getDouble("tt_Comissao"));
	    }
        edVolta.setVL_Cobranca (edVolta.getVL_Comissao()-edVolta.getVL_Descontos());

        // System.out.println ("lote Fat get by  1 ->>" + sql);
        
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao selecionar Participacao_Frete");
      excecoes.setMetodo ("Sele��o");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return edVolta;
  }

  public byte[] imprime_Participacao_Frete (Participacao_FreteED ed) throws
      Excecoes {

	  String sql = null;
    byte[] b = null;

    sql = " SELECT *, Participacoes_Fretes.oid_Pessoa, Documentos_Participacoes_Fretes.OID_Conhecimento as oid_Conhecimento_documento " +
        " FROM  Participacoes_Fretes, Documentos_Participacoes_Fretes " +
        " WHERE Participacoes_Fretes.oid_Participacao_Frete = Documentos_Participacoes_Fretes.oid_Participacao_Frete " +
        " AND   Participacoes_Fretes.oid_Participacao_Frete = '" + ed.getOID_Participacao_Frete () + "'" +
        " Order by NR_Documento ";

    // System.out.println (" imprime_Participacao_Frete->> " + sql);

    try {

      ResultSet res = null;

      res = this.executasql.executarConsulta (sql.toString ());

      Participacao_FreteRL conRL = new Participacao_FreteRL ();
      b = conRL.imprime_Participacao_Frete (res);

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

  public void Altera_Situacao_Processo (long oid_Participacao_Frete) throws Excecoes {

    // System.out.println ("Altera_Situacao_Processo ");

    String sql = null;
    ResultSet res = null , rs = null;
    double vl_faturado = 0;

    try {
      sql = " SELECT * from Documentos_Participacoes_Fretes " +
          " WHERE  oid_Participacao_Frete = ('" + oid_Participacao_Frete + "')";
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

  public byte[] rel_Ctos_Participacao_Frete (Participacao_FreteED ed) throws Excecoes {

	    // System.out.println ("gera_Comissao_Agenciamento 1");

	    String sql = null;
	    byte[] b = null;

	    try {

	      sql = "  SELECT Conhecimentos.OID_Participacao_Frete, Conhecimentos.oid_conhecimento, Conhecimentos.oid_Pessoa, Conhecimentos.oid_Pessoa_Destinatario, Conhecimentos.NR_conhecimento, Conhecimentos.VL_Frete_Peso, Conhecimentos.VL_Total_Frete, Conhecimentos.oid_Unidade, Conhecimentos.DT_Emissao, Conhecimentos.NR_Peso, Conhecimentos.TX_Observacao " +
	          "  FROM Conhecimentos " +
	          " WHERE Conhecimentos.DM_Impresso = 'S' " +
	          " AND   Conhecimentos.DM_Impresso = 'S' " +
	          " AND   Conhecimentos.DM_Situacao <> 'C' " +
	          " AND   Conhecimentos.VL_Total_Frete > 0" +
	          " AND   Conhecimentos.OID_Unidade = '" + ed.getOID_Unidade() + "'";

	      if ("0".equals(String.valueOf (ed.getOID_Participacao_Frete ()))) {
		     sql += " AND  (Conhecimentos.OID_Participacao_Frete is null OR Conhecimentos.OID_Participacao_Frete = 0)" +
		     		" AND   Conhecimentos.DT_Emissao >= '" + ed.getDT_Emissao_Inicial () + "'" +
		     		" AND   Conhecimentos.DT_Emissao <= '" + ed.getDT_Emissao_Final () + "'" ;
	      }else {
	         sql += " and Conhecimentos.OID_Participacao_Frete = " + ed.getOID_Participacao_Frete ();
	      }
	      
	      // System.out.println (sql);

	      ResultSet res = this.executasql.executarConsulta (sql.toString ());

	      Participacao_FreteRL conRL = new Participacao_FreteRL ();
	      b = conRL.rel_Ctos_Participacao_Frete(res, ed);
	    }

	    catch (Excecoes e) {
	      throw e;
	    }
	    catch (Exception exc) {
	      Excecoes exce = new Excecoes ();
	      exce.setExc (exc);
	      exce.setMensagem ("Erro no m�todo listar");
	      exce.setClasse (this.getClass ().getName ());
	      exce.setMetodo ("gera_Comissao_Agenciamento(ConhecimentoED ed)");
	    }
	    return b;
	  }
  
}
