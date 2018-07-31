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
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.master.ed.Ocorrencia_ConhecimentoED;
import com.master.ed.Ocorrencia_Nota_FiscalED;
import com.master.ed.PostagemED;
import com.master.rl.Ocorrencia_ConhecimentoRL;
import com.master.root.FormataDataBean;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.bd.ExecutaSQL;

public class Ocorrencia_ConhecimentoBD {

  private ExecutaSQL executasql;
  FormataDataBean dataFormatada = new FormataDataBean ();
  SimpleDateFormat formatter = new SimpleDateFormat ("dd/MM/yyyy");

  public Ocorrencia_ConhecimentoBD (ExecutaSQL sql) {
    this.executasql = sql;
  }

  public Ocorrencia_ConhecimentoED inclui (Ocorrencia_ConhecimentoED ed) throws Excecoes {
    String sql = null;
    long valOid = 0;
    String chave = null;
    String dm_tipo = "";
    String dm_acesso = "";
    String dm_avaria = "";
    Ocorrencia_ConhecimentoED ocorrencia_ConhecimentoED = new Ocorrencia_ConhecimentoED ();

    try {

        //System.out.println("Cheguei  ==== ");


	        // .println ("ed.getOID_Comprovante_Entrega ()=>>" + ed.getOID_Comprovante_Entrega ());

		    if (ed.getOID_Comprovante_Entrega () != null && !ed.getOID_Comprovante_Entrega ().equals ("0") && !ed.getOID_Comprovante_Entrega ().equals ("null") && !ed.getOID_Comprovante_Entrega ().equals ("")) {
				  // .println ("Passo 2");
				  ResultSet rsComp = executasql.executarConsulta ("SELECT oid_Comprovante_Entrega FROM  Conhecimentos WHERE oid_Conhecimento='" + ed.getOID_Conhecimento()+  "'");
		  	      if (rsComp.next ()) {
				      // .println ("Passo 3 achoui oid_Comprovante_Entrega=>> "+ rsComp.getString ("oid_Comprovante_Entrega"));
		  	    	  if (rsComp.getString ("oid_Comprovante_Entrega")==null || "0".equals(rsComp.getString ("oid_Comprovante_Entrega")) || "".equals(rsComp.getString ("oid_Comprovante_Entrega")) || "null".equals(rsComp.getString ("oid_Comprovante_Entrega"))) {
			    		  sql = " UPDATE Conhecimentos SET oid_Comprovante_Entrega = '" + ed.getOID_Comprovante_Entrega () + "'" +
			        	  " WHERE oid_Conhecimento = '" + ed.getOID_Conhecimento () + "'";
			    		  // .println (" sql cto entrega ->> " + sql);
			    		  executasql.executarUpdate (sql);
			    	  }
			      }
		   }


	      sql =   " SELECT  OID_Ocorrencia_Conhecimento FROM  Ocorrencias_Conhecimentos " +
	      		  " WHERE   Ocorrencias_Conhecimentos.OID_Tipo_Ocorrencia = '" + ed.getOID_Tipo_Ocorrencia () + "'" +
	        	  " AND     Ocorrencias_Conhecimentos.OID_Conhecimento = '" + ed.getOID_Conhecimento () + "'" +
	        	  " AND     Ocorrencias_Conhecimentos.DT_Ocorrencia_Conhecimento = '" + ed.getDT_Ocorrencia_Conhecimento () + "'" +
	        	  " AND     Ocorrencias_Conhecimentos.HR_Ocorrencia_Conhecimento = '" + ed.getHR_Ocorrencia_Conhecimento () + "'" +
	        	  " AND     Ocorrencias_Conhecimentos.TX_Observacao = '" + ed.getTX_Observacao () + "'";
          //System.out.println("sql 1"+ sql);

	      ResultSet res = this.executasql.executarConsulta (sql);
	      if (!res.next ()) {

		      sql = " SELECT  DM_Tipo, DM_Acesso, DM_Avaria FROM  Tipos_Ocorrencias ";
		      sql += " WHERE   Tipos_Ocorrencias.OID_Tipo_Ocorrencia = '" + ed.getOID_Tipo_Ocorrencia () + "'";
	          //System.out.println("sql 2"+ sql);
		      res = this.executasql.executarConsulta (sql);
		      while (res.next ()) {
		        dm_tipo = res.getString ("DM_Tipo");
		        dm_acesso = res.getString ("DM_Acesso");
		        dm_avaria = res.getString ("DM_Avaria");
		      }

		      // .println ("entrega dm_tipo->> " + dm_tipo);
		      // .println ("entrega dm_acesso->> " + dm_acesso);
		      // .println ("entrega dm_avaria->> " + dm_avaria);

		      if ("6908093CN1".equals (ed.getOID_Conhecimento ())) { //115918
		        this.baca_inclui (ed);
		      }

			  if ("8911145CN1".equals (ed.getOID_Conhecimento ())) { //26
			          this.acertaDiaEntrega(ed);
			  }

			  //System.out.println(" MAX ");

		      ResultSet rs = executasql.executarConsulta ("select max(oid_Ocorrencia_Conhecimento) as result from Ocorrencias_Conhecimentos");

		      //pega proximo valor da chave
		      while (rs.next ()) {
		        valOid = rs.getInt ("result");
		      }

		      ed.setOID_Ocorrencia_Conhecimento (++valOid);

		      sql = " insert into Ocorrencias_Conhecimentos (OID_Ocorrencia_Conhecimento, OID_Conhecimento, OID_Tipo_Ocorrencia, OID_Usuario, DT_Ocorrencia_Conhecimento, HR_Ocorrencia_Conhecimento, TX_Observacao, DT_Ocorrencia_Lancada, HR_Ocorrencia_Lancada ) values ";
		      sql += "(" + ed.getOID_Ocorrencia_Conhecimento () + ",'" + ed.getOID_Conhecimento () + "'," + ed.getOID_Tipo_Ocorrencia () + "," + ed.getOID_Usuario () + ",'" + ed.getDT_Ocorrencia_Conhecimento () + "','" + ed.getHR_Ocorrencia_Conhecimento () + "','" + ed.getTX_Observacao () + "','" + Data.getDataDMY () + "','" + Data.getHoraHM () + "')";

		      // .println ("inclui oco->> " + sql);

	          //System.out.println("sql 3"+ sql);

		      int i = executasql.executarUpdate (sql);

		      if (ed.getOID_Pessoa_Redespacho () != null && ed.getOID_Pessoa_Redespacho ().length () > 4) {
		        sql = " UPDATE Conhecimentos SET OID_Pessoa_Redespacho = '" + ed.getOID_Pessoa_Redespacho () + "'";
		        sql += " where oid_Conhecimento = '" + ed.getOID_Conhecimento () + "'";
		        executasql.executarUpdate (sql);
		      }

		      if (ed.getOID_Pessoa_Entregadora () != null && ed.getOID_Pessoa_Entregadora ().length () > 4) {
		          sql = " UPDATE Conhecimentos SET OID_Pessoa_Entregadora = '" + ed.getOID_Pessoa_Entregadora () + "'";
		          sql += " where oid_Conhecimento = '" + ed.getOID_Conhecimento () + "'";
		          executasql.executarUpdate (sql);
		      }

		      if ("P".equals (dm_tipo) || "J".equals (dm_tipo) || "A".equals (dm_tipo) || "D".equals (dm_tipo) || "W".equals (dm_tipo)) {

		        // .println ("Tipo que faz entrega=>>" + dm_tipo);
		        // .println ("Tipo que faz ed.getOID_Conhecimento ()=>>" + ed.getOID_Conhecimento ());
		        // .println ("Tipo que faz ed.getDT_Ocorrencia_Conhecimento ()=>>" + ed.getDT_Ocorrencia_Conhecimento ());

			        //System.out.println("Dias fui");

		        //long nr_dias_entrega_realizado = this.calcula_Dias_Entrega (ed.getOID_Conhecimento () , ed.getDT_Ocorrencia_Conhecimento ());

		        long nr_dias_entrega_realizado = 0;

		        //System.out.println("Dias cheguei");
		        // .println ("nr_dias_entrega_realizado=>>" + nr_dias_entrega_realizado);

		        sql = " UPDATE Conhecimentos set OID_Tipo_Ocorrencia = " + ed.getOID_Tipo_Ocorrencia ();
		        sql += " ,DT_Entrega = '" + ed.getDT_Ocorrencia_Conhecimento () + "'," +
		            " HR_Entrega = '" + ed.getHR_Ocorrencia_Conhecimento () + "'," +
		            " DM_Tipo_Entrega = '" + dm_tipo + "'," +
		            " NR_Dias_Entrega_Realizado = " + nr_dias_entrega_realizado + "," +
		            " oid_Ocorrencia_Conhecimento = " + ed.getOID_Ocorrencia_Conhecimento () + "," +
		            " NM_Pessoa_Entrega = '" + ed.getNM_Pessoa_Entrega () + "'" +
		            " WHERE oid_Conhecimento = '" + ed.getOID_Conhecimento () + "'";
		        // .println (" sql cto entrega ->> " + sql);

		        //System.out.println("sql 4"+ sql);

		        executasql.executarUpdate (sql);
		      }

		      if ("Q".equals (dm_tipo) || "J".equals (dm_tipo)) { //entrega atrazo justificadas
		          sql =  " UPDATE Conhecimentos  SET DM_Justifica_Atrazo = 'S' " +
		          	     " WHERE  oid_Conhecimento = '" + ed.getOID_Conhecimento () + "'";
		          // .println (" sql cto entrega ->> " + sql);
		          executasql.executarUpdate (sql);
		       }

		      if (ed.getVL_Avaria() > 0) { //valor de avaria
		          sql =  " UPDATE Conhecimentos  SET vl_avaria = " + ed.getVL_Avaria() +
		          	     " WHERE  oid_Conhecimento = '" + ed.getOID_Conhecimento () + "'";
		          executasql.executarUpdate (sql);
		       }


		      // .println ("NR Postagem:" + ed.getNR_Postagem ());

		      String postagem = "";
		      if (ed.getNR_Postagem () > 0) {

		        this.incluiPostagem (ed);

		      }

		      if ("Z".equals (dm_tipo)) {
		        sql = " UPDATE Conhecimentos set OID_Tipo_Ocorrencia = " + ed.getOID_Tipo_Ocorrencia () +
		        	" ,DT_Entrega = null," +
		            " HR_Entrega = ''," +
		            " DM_Tipo_Entrega = ''," +
		            " NR_Dias_Entrega_Realizado = 0," +
		            " oid_Ocorrencia_Conhecimento = null," +
		            " NM_Pessoa_Entrega = '' " +
		            " WHERE oid_Conhecimento = '" + ed.getOID_Conhecimento () + "'";
		        // .println (" sql estorno cto entrega ->> " + sql);
		        executasql.executarUpdate (sql);
		      }

		      ocorrencia_ConhecimentoED.setOID_Ocorrencia_Conhecimento (ed.getOID_Ocorrencia_Conhecimento ());
	      }


	      sql =  " SELECT  Conhecimentos_Notas_Fiscais.Oid_Nota_Fiscal from Conhecimentos_Notas_Fiscais " +
	      		 " WHERE   Conhecimentos_Notas_Fiscais.OID_Conhecimento = '" + ed.getOID_Conhecimento() + "'";

	      res = this.executasql.executarConsulta(sql);

	             FormataDataBean DataFormatada = new FormataDataBean();

	             //popula
	      Ocorrencia_Nota_FiscalBD Ocorrencia_Nota_FiscalBD = new Ocorrencia_Nota_FiscalBD(this.executasql);

	      while (res.next()){
	          Ocorrencia_Nota_FiscalED ocorrencia_Nota_FiscalED = new Ocorrencia_Nota_FiscalED();
	          ocorrencia_Nota_FiscalED.setDM_Origem_Ocorrencia("C");
	          ocorrencia_Nota_FiscalED.setDT_Ocorrencia_Nota_Fiscal(ed.getDT_Ocorrencia_Conhecimento());
	          ocorrencia_Nota_FiscalED.setHR_Ocorrencia_Nota_Fiscal(ed.getHR_Ocorrencia_Conhecimento());
	          ocorrencia_Nota_FiscalED.setOID_Tipo_Ocorrencia(new Long(ed.getOID_Tipo_Ocorrencia()).longValue());
	          ocorrencia_Nota_FiscalED.setOID_Nota_Fiscal(res.getString("oid_Nota_Fiscal"));
	          ocorrencia_Nota_FiscalED.setTX_Observacao(ed.getTX_Observacao());
	          ocorrencia_Nota_FiscalED.setNM_Pessoa(ed.getNM_Pessoa_Entrega());
	          ocorrencia_Nota_FiscalED.setTX_Observacao_Cliente(ed.getTX_Observacao());

	          ocorrencia_Nota_FiscalED = Ocorrencia_Nota_FiscalBD.inclui(ocorrencia_Nota_FiscalED);
	      }

    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "inclui(Ocorrencia_ConhecimentoED ed)");
    }
    return ocorrencia_ConhecimentoED;
  }

  public Ocorrencia_ConhecimentoED inclui_Lote (Ocorrencia_ConhecimentoED ed) throws Excecoes {
	    String sql = null;
	    long valOid = 0;
	    String chave = null;
	    String dm_tipo = "";
	    String dm_acesso = "";
	    String dm_avaria = "";
	    Ocorrencia_ConhecimentoED ocorrencia_ConhecimentoED = new Ocorrencia_ConhecimentoED ();

	    try {

	    	sql = " Select oid_Conhecimento, dt_previsao_entrega, hr_previsao_entrega " +
	    		  " from conhecimentos " +
	    		  " where dt_emissao between '" + ed.getDT_Emissao() + "' and '" + ed.getDT_Emissao_Final() + "'" +
	    		  " and dt_entrega is null " +
	    		  " and dt_previsao_entrega is not null ";
		    ResultSet  resCTRC = this.executasql.executarConsulta (sql);

		    FormataDataBean DataFormatada = new FormataDataBean();

		    while (resCTRC.next ()) {
		    	ed.setOID_Conhecimento(resCTRC.getString("oid_Conhecimento"));
		    	ed.setDT_Ocorrencia_Conhecimento(resCTRC.getString("dt_previsao_entrega"));
		        DataFormatada.setDT_FormataData (ed.getDT_Ocorrencia_Conhecimento ());
		        ed.setDT_Ocorrencia_Conhecimento (DataFormatada.getDT_FormataData ());

		    	ed.setHR_Ocorrencia_Conhecimento(resCTRC.getString("hr_previsao_entrega"));
		    	ed.setTX_Observacao(" ");

		        sql =   " SELECT  OID_Ocorrencia_Conhecimento FROM  Ocorrencias_Conhecimentos " +
		      		    " WHERE   Ocorrencias_Conhecimentos.OID_Tipo_Ocorrencia = '" + ed.getOID_Tipo_Ocorrencia () + "'" +
		        	    " AND     Ocorrencias_Conhecimentos.OID_Conhecimento = '" + ed.getOID_Conhecimento () + "'" +
		        	    " AND     Ocorrencias_Conhecimentos.DT_Ocorrencia_Conhecimento = '" + ed.getDT_Ocorrencia_Conhecimento () + "'" +
		        	    " AND     Ocorrencias_Conhecimentos.HR_Ocorrencia_Conhecimento = '" + ed.getHR_Ocorrencia_Conhecimento () + "'" +
		        	    " AND     Ocorrencias_Conhecimentos.TX_Observacao = '" + ed.getTX_Observacao () + "'";
		        ResultSet res = this.executasql.executarConsulta (sql);
		        if (!res.next ()) {

			      sql = " SELECT  DM_Tipo, DM_Acesso, DM_Avaria FROM  Tipos_Ocorrencias ";
			      sql += " WHERE   Tipos_Ocorrencias.OID_Tipo_Ocorrencia = '" + ed.getOID_Tipo_Ocorrencia () + "'";
			      res = this.executasql.executarConsulta (sql);
			      while (res.next ()) {
			        dm_tipo = res.getString ("DM_Tipo");
			        dm_acesso = res.getString ("DM_Acesso");
			        dm_avaria = res.getString ("DM_Avaria");
			      }

			      ResultSet rs = executasql.executarConsulta ("select max(oid_Ocorrencia_Conhecimento) as result from Ocorrencias_Conhecimentos");

			      //pega proximo valor da chave
			      while (rs.next ()) {
			        valOid = rs.getInt ("result");
			      }

			      ed.setOID_Ocorrencia_Conhecimento (++valOid);

			      sql = " insert into Ocorrencias_Conhecimentos (OID_Ocorrencia_Conhecimento, OID_Conhecimento, OID_Tipo_Ocorrencia, OID_Usuario, DT_Ocorrencia_Conhecimento, HR_Ocorrencia_Conhecimento, TX_Observacao, DT_Ocorrencia_Lancada, HR_Ocorrencia_Lancada ) values ";
			      sql += "(" + ed.getOID_Ocorrencia_Conhecimento () + ",'" + ed.getOID_Conhecimento () + "'," + ed.getOID_Tipo_Ocorrencia () + "," + ed.getOID_Usuario () + ",'" + ed.getDT_Ocorrencia_Conhecimento () + "','" + ed.getHR_Ocorrencia_Conhecimento () + "','" + ed.getTX_Observacao () + "','" + Data.getDataDMY () + "','" + Data.getHoraHM () + "')";

			      int i = executasql.executarUpdate (sql);


			      if ("P".equals (dm_tipo) || "J".equals (dm_tipo) || "A".equals (dm_tipo) || "D".equals (dm_tipo) || "W".equals (dm_tipo)) {

			        long nr_dias_entrega_realizado = this.calcula_Dias_Entrega (ed.getOID_Conhecimento () , ed.getDT_Ocorrencia_Conhecimento ());

			        sql = " UPDATE Conhecimentos set OID_Tipo_Ocorrencia = " + ed.getOID_Tipo_Ocorrencia ();
			        sql += " ,DT_Entrega = '" + ed.getDT_Ocorrencia_Conhecimento () + "'," +
			            " HR_Entrega = '" + ed.getHR_Ocorrencia_Conhecimento () + "'," +
			            " DM_Tipo_Entrega = '" + dm_tipo + "'," +
			            " NR_Dias_Entrega_Realizado = " + nr_dias_entrega_realizado + "," +
			            " oid_Ocorrencia_Conhecimento = " + ed.getOID_Ocorrencia_Conhecimento () + "," +
			            " NM_Pessoa_Entrega = '" + ed.getNM_Pessoa_Entrega () + "'" +
			            " WHERE oid_Conhecimento = '" + ed.getOID_Conhecimento () + "'";

			        executasql.executarUpdate (sql);
			      }

			      if ("Z".equals (dm_tipo)) {
			        sql = " UPDATE Conhecimentos set OID_Tipo_Ocorrencia = " + ed.getOID_Tipo_Ocorrencia () +
			        	" ,DT_Entrega = null," +
			            " HR_Entrega = ''," +
			            " DM_Tipo_Entrega = ''," +
			            " NR_Dias_Entrega_Realizado = 0," +
			            " oid_Ocorrencia_Conhecimento = null," +
			            " NM_Pessoa_Entrega = '' " +
			            " WHERE oid_Conhecimento = '" + ed.getOID_Conhecimento () + "'";
			        // .println (" sql estorno cto entrega ->> " + sql);
			        executasql.executarUpdate (sql);
			      }

			      ocorrencia_ConhecimentoED.setOID_Ocorrencia_Conhecimento (ed.getOID_Ocorrencia_Conhecimento ());
		        }

		        sql =  " SELECT  Conhecimentos_Notas_Fiscais.Oid_Nota_Fiscal from Conhecimentos_Notas_Fiscais " +
		      		 " WHERE   Conhecimentos_Notas_Fiscais.OID_Conhecimento = '" + ed.getOID_Conhecimento() + "'";

		        res = this.executasql.executarConsulta(sql);

		        Ocorrencia_Nota_FiscalBD Ocorrencia_Nota_FiscalBD = new Ocorrencia_Nota_FiscalBD(this.executasql);

		        while (res.next()){
		          Ocorrencia_Nota_FiscalED ocorrencia_Nota_FiscalED = new Ocorrencia_Nota_FiscalED();
		          ocorrencia_Nota_FiscalED.setDM_Origem_Ocorrencia("C");
		          ocorrencia_Nota_FiscalED.setDT_Ocorrencia_Nota_Fiscal(ed.getDT_Ocorrencia_Conhecimento());
		          ocorrencia_Nota_FiscalED.setHR_Ocorrencia_Nota_Fiscal(ed.getHR_Ocorrencia_Conhecimento());
		          ocorrencia_Nota_FiscalED.setOID_Tipo_Ocorrencia(new Long(ed.getOID_Tipo_Ocorrencia()).longValue());
		          ocorrencia_Nota_FiscalED.setOID_Nota_Fiscal(res.getString("oid_Nota_Fiscal"));
		          ocorrencia_Nota_FiscalED.setTX_Observacao(ed.getTX_Observacao());
		          ocorrencia_Nota_FiscalED.setNM_Pessoa(ed.getNM_Pessoa_Entrega());
		          ocorrencia_Nota_FiscalED.setTX_Observacao_Cliente(ed.getTX_Observacao());

		          ocorrencia_Nota_FiscalED = Ocorrencia_Nota_FiscalBD.inclui(ocorrencia_Nota_FiscalED);
		        }
		      }
	    }
	    catch (SQLException e) {
	      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "inclui(Ocorrencia_ConhecimentoED ed)");
	    }
	    return ocorrencia_ConhecimentoED;
	  }

  private void incluiPostagem (Ocorrencia_ConhecimentoED ed) throws Excecoes {
    String sql = null;
    long valOid = 0;
    String postagem = "";
    // .println ("incluiPostagem 1");
    ResultSet resAux = null;
    try {

      sql = " SELECT  Conhecimentos.oid_Pessoa, Conhecimentos.OID_Pessoa_Destinatario,  Conhecimentos.nr_peso, Conhecimentos.nr_peso_cubado, Conhecimentos.oid_Modal, Conhecimentos.NR_Postagem " +
          " FROM    Conhecimentos  " +
          " WHERE   Conhecimentos.OID_Conhecimento = '" + ed.getOID_Conhecimento () + "'";

      // .println ("incluiPostagem " + sql);

      ResultSet res = this.executasql.executarConsulta (sql);
      if (res.next ()) {

        // .println ("incluiPostagem 10");

        postagem = String.valueOf (ed.getNR_Postagem ());
        while (postagem.length () < 9) {
          postagem = "0" + postagem;
        }
        postagem = ed.getDM_Tipo_Postagem () + postagem + "BR";
        // .println ("NR Postagem do CTO =>>" + res.getLong ("NR_Postagem"));

        if (res.getLong ("NR_Postagem") == 0) {
          sql = " update Conhecimentos set NR_Postagem = " + ed.getNR_Postagem () + ", DM_Tipo_Postagem='" + ed.getDM_Tipo_Postagem () + "', NM_Pessoa_Entrega='" + "Postagem:" + postagem + "'";
          sql += " where oid_Conhecimento = '" + ed.getOID_Conhecimento () + "'";
          // .println ("NR Postagem:" + sql);
          executasql.executarUpdate (sql);
        }
        sql = " SELECT Postagens.oid_Conhecimento, Postagens.oid_Postagem FROM Postagens WHERE NR_Postagem=" + ed.getNR_Postagem ();
        resAux = this.executasql.executarConsulta (sql);
        if (resAux.next ()) { //existe
          if (resAux.getString ("oid_Conhecimento") == null || resAux.getString ("oid_Conhecimento").length () <= 4) {
            sql = " update Postagens set oid_Conhecimento = '" + ed.getOID_Conhecimento () + "'";
            sql += " where Postagens.oid_Postagem = '" + resAux.getLong ("oid_Postagem") + "'";
            // .println ("setando postagem com cto:" + sql);
            executasql.executarUpdate (sql);
          }
        }
        else { //nao existe
          PostagemED edPostagem = new PostagemED ();
          edPostagem.setOID_Conhecimento (ed.getOID_Conhecimento ());
          edPostagem.setNR_Postagem (ed.getNR_Postagem ());
          edPostagem.setCD_Destino ("");
          edPostagem.setNM_Servico ("");
          edPostagem.setNM_Unidade_Postagem ("");
          // .println ("incluiPostagem 20");
          edPostagem.setOID_Pessoa (res.getString ("OID_Pessoa"));
          edPostagem.setOID_Pessoa_Destinatario (res.getString ("OID_Pessoa_Destinatario"));
          edPostagem.setDT_Postagem (Data.getDataDMY ());
          edPostagem.setNR_Fatura ("");
          edPostagem.setDM_Situacao ("I");
          edPostagem.setNR_Peso (res.getDouble ("nr_peso"));
          // .println ("incluiPostagem 30");
          edPostagem.setNR_Peso_Cubado (res.getDouble ("nr_peso_cubado"));
          edPostagem.setNR_Lote ("0");
          edPostagem.setNR_Volumes (1);
          edPostagem.setVL_Postagem (0);
          edPostagem.setOID_Modal (res.getLong ("oid_Modal"));

          PostagemBD postagemBD = new PostagemBD (this.executasql);
          edPostagem = postagemBD.inclui (edPostagem);

          // .println ("incluiPostagem ");
        }
        // .println ("incluiPostagem 99");
      }

    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "inclui(Ocorrencia_ConhecimentoED ed)");
    }
    return ;
  }

  public void altera (Ocorrencia_ConhecimentoED ed) throws Excecoes {

    String sql = null;

    try {

      sql = " update Ocorrencias_Conhecimentos set OID_Tipo_Ocorrencia= " + ed.getOID_Tipo_Ocorrencia () + ", TX_Observacao = '" + ed.getTX_Observacao () + "'";
      sql += " where oid_Ocorrencia_Conhecimento = " + ed.getOID_Ocorrencia_Conhecimento ();

      int i = executasql.executarUpdate (sql);
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar Ocorr�ncia Conhecimento");
      excecoes.setMetodo ("alterar");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void deleta (Ocorrencia_ConhecimentoED ed) throws Excecoes {

    String sql = null;
    String dm_tipo = "";
    ResultSet res = null;

    try {

      sql = " SELECT  DM_Tipo, DM_Acesso, DM_Avaria FROM  Tipos_Ocorrencias ";
      sql += " WHERE   Tipos_Ocorrencias.OID_Tipo_Ocorrencia = '" + ed.getOID_Tipo_Ocorrencia () + "'";
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        dm_tipo = res.getString ("DM_Tipo");
      }

      sql = "delete from Ocorrencias_Conhecimentos WHERE oid_Ocorrencia_Conhecimento = ";
      sql += "(" + ed.getOID_Ocorrencia_Conhecimento () + ")";

      executasql.executarUpdate (sql);

      if ("P".equals (dm_tipo) || "J".equals (dm_tipo) || "A".equals (dm_tipo) || "D".equals (dm_tipo) || "W".equals (dm_tipo)) {

        sql = " UPDATE Conhecimentos set OID_Tipo_Ocorrencia = null" ;
        sql += " ,DT_Entrega = null," +
            " HR_Entrega = ''," +
            " DM_Tipo_Entrega = ''," +
            " NR_Dias_Entrega_Realizado = 0," +
            " oid_Ocorrencia_Conhecimento = null," +
            " NM_Pessoa_Entrega = ''" +
            " WHERE oid_Conhecimento = '" + ed.getOID_Conhecimento () + "'";

        executasql.executarUpdate (sql);

      }

      if ("Z".equals (dm_tipo)) {
        sql = " UPDATE Conhecimentos set OID_Tipo_Ocorrencia = " + ed.getOID_Tipo_Ocorrencia () +
        	" ,DT_Entrega = null," +
            " HR_Entrega = ''," +
            " DM_Tipo_Entrega = ''," +
            " NR_Dias_Entrega_Realizado = 0," +
            " oid_Ocorrencia_Conhecimento = null," +
            " NM_Pessoa_Entrega = '' " +
            " WHERE oid_Conhecimento = '" + ed.getOID_Conhecimento () + "'";
        executasql.executarUpdate (sql);
      }
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao excluir Ocorr�ncia Conhecimento");
      excecoes.setMetodo ("excluir");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public ArrayList lista (Ocorrencia_ConhecimentoED ed) throws Excecoes {
    ArrayList list = new ArrayList ();
    try {
      String sql =
          " SELECT Ocorrencias_Conhecimentos.OID_Ocorrencia_Conhecimento, Ocorrencias_Conhecimentos.OID_Tipo_Ocorrencia, DT_Ocorrencia_Conhecimento, HR_Ocorrencia_Conhecimento, Ocorrencias_Conhecimentos.TX_Observacao, Conhecimentos.oid_Conhecimento, Conhecimentos.oid_Pessoa, Conhecimentos.oid_Pessoa_Destinatario, Conhecimentos.NR_Conhecimento, Conhecimentos.DT_Emissao, Conhecimentos.oid_Unidade, Conhecimentos.NM_Pessoa_Entrega, Tipos_Ocorrencias.NM_Tipo_Ocorrencia, Tipos_Ocorrencias.DM_Tipo, Tipos_Ocorrencias.DM_Acesso , Tipos_Ocorrencias.DM_Avaria, Tipos_Ocorrencias.DM_Status from Ocorrencias_Conhecimentos, Conhecimentos, Tipos_Ocorrencias ";
      sql += " WHERE Ocorrencias_Conhecimentos.OID_Conhecimento    = Conhecimentos.OID_Conhecimento ";
      sql += " AND   Ocorrencias_Conhecimentos.OID_Tipo_Ocorrencia = Tipos_Ocorrencias.OID_Tipo_Ocorrencia ";

      if (String.valueOf (ed.getNR_Nota_Fiscal ()) != null &&
          !String.valueOf (ed.getNR_Nota_Fiscal ()).equals ("0") &&
          !String.valueOf (ed.getNR_Nota_Fiscal ()).equals ("null")) {
          sql = " SELECT Ocorrencias_Conhecimentos.OID_Ocorrencia_Conhecimento, Ocorrencias_Conhecimentos.OID_Tipo_Ocorrencia, DT_Ocorrencia_Conhecimento, " +
          		"        HR_Ocorrencia_Conhecimento, Ocorrencias_Conhecimentos.TX_Observacao, Conhecimentos.oid_Conhecimento, Conhecimentos.oid_Pessoa, " +
          		"        Conhecimentos.oid_Pessoa_Destinatario, Conhecimentos.NR_Conhecimento, Conhecimentos.DT_Emissao, Conhecimentos.oid_Unidade, " +
          		"        Conhecimentos.NM_Pessoa_Entrega, Tipos_Ocorrencias.NM_Tipo_Ocorrencia, Tipos_Ocorrencias.DM_Tipo, Tipos_Ocorrencias.DM_Acesso , " +
          		"        Tipos_Ocorrencias.DM_Avaria, Tipos_Ocorrencias.DM_Status " +
          		" from Ocorrencias_Conhecimentos, Conhecimentos, Tipos_Ocorrencias, Notas_Fiscais, Conhecimentos_Notas_Fiscais ";
          sql += " WHERE Ocorrencias_Conhecimentos.OID_Conhecimento    = Conhecimentos.OID_Conhecimento ";
          sql += " AND   Ocorrencias_Conhecimentos.OID_Tipo_Ocorrencia = Tipos_Ocorrencias.OID_Tipo_Ocorrencia " +
          		"  AND   Conhecimentos.oid_Conhecimento = Conhecimentos_Notas_Fiscais.OID_Conhecimento " +
          		"  AND   Conhecimentos_Notas_Fiscais.OID_Nota_Fiscal = Notas_Fiscais.OID_Nota_Fiscal ";

    	  sql += " and Notas_Fiscais.NR_Nota_Fiscal = " + ed.getNR_Nota_Fiscal ();
      }


      if (String.valueOf (ed.getOID_Conhecimento ()) != null &&
          !String.valueOf (ed.getOID_Conhecimento ()).equals ("0") &&
          !String.valueOf (ed.getOID_Conhecimento ()).equals ("null")) {
        sql += " and Conhecimentos.OID_Conhecimento = '" + ed.getOID_Conhecimento () +"'";
      }

      if (String.valueOf (ed.getNR_Conhecimento ()) != null &&
          !String.valueOf (ed.getNR_Conhecimento ()).equals ("0") &&
          !String.valueOf (ed.getNR_Conhecimento ()).equals ("null")) {
        sql += " and Conhecimentos.NR_Conhecimento = " + ed.getNR_Conhecimento ();
      }
      if (String.valueOf (ed.getOID_Tipo_Ocorrencia ()) != null &&
          !String.valueOf (ed.getOID_Tipo_Ocorrencia ()).equals ("0") &&
          !String.valueOf (ed.getOID_Tipo_Ocorrencia ()).equals ("null")) {
        sql += " and Tipos_Ocorrencias.OID_Tipo_Ocorrencia = " + ed.getOID_Tipo_Ocorrencia ();
      }
      if (String.valueOf (ed.getOID_Unidade ()) != null &&
          !String.valueOf (ed.getOID_Unidade ()).equals ("0") &&
          !String.valueOf (ed.getOID_Unidade ()).equals ("null")) {
        sql += " and Conhecimentos.OID_Unidade = " + ed.getOID_Unidade ();
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
      if (String.valueOf (ed.getOID_Pessoa_Consignatario ()) != null &&
          !String.valueOf (ed.getOID_Pessoa_Consignatario ()).equals ("") &&
          !String.valueOf (ed.getOID_Pessoa_Consignatario ()).equals ("null")) {
        sql += " and Conhecimentos.OID_Pessoa_Consignatario = '" + ed.getOID_Pessoa_Consignatario () + "'";
      }
      if (String.valueOf (ed.getDT_Emissao ()) != null &&
          !String.valueOf (ed.getDT_Emissao ()).equals ("") &&
          !String.valueOf (ed.getDT_Emissao ()).equals ("null")) {
        sql += " and Conhecimentos.DT_Emissao = '" + ed.getDT_Emissao () + "'";
      }
      if (String.valueOf (ed.getDT_Ocorrencia_Conhecimento ()) != null &&
          !String.valueOf (ed.getDT_Ocorrencia_Conhecimento ()).equals ("") &&
          !String.valueOf (ed.getDT_Ocorrencia_Conhecimento ()).equals ("null")) {
        sql += " and Ocorrencias_Conhecimentos.DT_Ocorrencia_Conhecimento = '" + ed.getDT_Ocorrencia_Conhecimento () + "'";
      }
      sql += " Order by  Ocorrencias_Conhecimentos.DT_Ocorrencia_Lancada desc, Ocorrencias_Conhecimentos.HR_Ocorrencia_Lancada desc ";

      ResultSet res = this.executasql.executarConsulta (sql);

      FormataDataBean DataFormatada = new FormataDataBean ();

      //popula
      while (res.next ()) {
        Ocorrencia_ConhecimentoED edVolta = new Ocorrencia_ConhecimentoED ();

        edVolta.setOID_Ocorrencia_Conhecimento (res.getLong ("OID_Ocorrencia_Conhecimento"));
        edVolta.setOID_Tipo_Ocorrencia (res.getLong ("OID_Tipo_Ocorrencia"));
        edVolta.setOID_Conhecimento (res.getString ("OID_Conhecimento"));

        edVolta.setOID_Pessoa (res.getString ("OID_Pessoa"));
        edVolta.setOID_Pessoa_Destinatario (res.getString ("OID_Pessoa_Destinatario"));
        edVolta.setNR_Conhecimento (res.getLong ("NR_Conhecimento"));

        edVolta.setDT_Ocorrencia_Conhecimento (res.getString ("DT_Ocorrencia_Conhecimento"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Ocorrencia_Conhecimento ());
        edVolta.setDT_Ocorrencia_Conhecimento (DataFormatada.getDT_FormataData ());

        edVolta.setHR_Ocorrencia_Conhecimento (res.getString ("HR_Ocorrencia_Conhecimento"));
        edVolta.setTX_Observacao (res.getString ("TX_Observacao"));

        edVolta.setNM_Tipo_Ocorrencia (res.getString ("NM_Tipo_Ocorrencia"));
        edVolta.setNM_Pessoa_Entrega (res.getString ("NM_Pessoa_Entrega"));

        edVolta.setDM_Tipo (res.getString ("DM_Tipo"));
        edVolta.setDM_Acesso (res.getString ("DM_Acesso"));
        edVolta.setDM_Avaria (res.getString ("DM_Avaria"));
        edVolta.setDM_Status (res.getString ("DM_Status"));

        edVolta.setDT_Emissao (res.getString ("DT_Emissao"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
        edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());

        edVolta.setOID_Unidade (res.getLong ("oid_Unidade"));

        edVolta.setNM_Observacao (res.getString ("TX_Observacao"));

        if ("P".equals (res.getString ("DM_Tipo")) || "A".equals (res.getString ("DM_Tipo")) || "D".equals (res.getString ("DM_Tipo")) || "W".equals (res.getString ("DM_Tipo"))) {
          edVolta.setNM_Observacao ("Entregue p/" + res.getString ("NM_Pessoa_Entrega"));
        }
        list.add (edVolta);
      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "lista(Ocorrencia_ConhecimentoED ed)");
    }

    return list;
  }

  public Ocorrencia_ConhecimentoED getByRecord (Ocorrencia_ConhecimentoED ed) throws Excecoes {

    String sql = null;

    Ocorrencia_ConhecimentoED edVolta = new Ocorrencia_ConhecimentoED ();

    try {

      sql = " SELECT Ocorrencias_Conhecimentos.OID_Ocorrencia_Conhecimento, Ocorrencias_Conhecimentos.OID_Tipo_Ocorrencia, DT_Ocorrencia_Conhecimento, HR_Ocorrencia_Conhecimento, Ocorrencias_Conhecimentos.TX_Observacao, Ocorrencias_Conhecimentos.oid_Conhecimento, Ocorrencias_Conhecimentos.oid_Usuario, Tipos_Ocorrencias.NM_Tipo_Ocorrencia, Tipos_Ocorrencias.DM_Tipo, Tipos_Ocorrencias.DM_Acesso , Tipos_Ocorrencias.DM_Avaria, Tipos_Ocorrencias.DM_Status from Ocorrencias_Conhecimentos, Tipos_Ocorrencias ";
      sql += " WHERE   Ocorrencias_Conhecimentos.OID_Tipo_Ocorrencia = Tipos_Ocorrencias.OID_Tipo_Ocorrencia ";

      if (String.valueOf (ed.getOID_Ocorrencia_Conhecimento ()) != null &&
          !String.valueOf (ed.getOID_Ocorrencia_Conhecimento ()).equals ("0")) {
        sql += " AND Ocorrencias_Conhecimentos.OID_Ocorrencia_Conhecimento = " + ed.getOID_Ocorrencia_Conhecimento ();
      }
      // .println (" sql cto  ret by >> " + sql);

      FormataDataBean DataFormatada = new FormataDataBean ();

      ResultSet res = null;
      ResultSet res2 = null;
      res = this.executasql.executarConsulta (sql);
      if (res.next ()) {

        // .println (" sql cto  ret 1>> ");

        edVolta.setOID_Ocorrencia_Conhecimento (res.getLong ("OID_Ocorrencia_Conhecimento"));
        edVolta.setOID_Tipo_Ocorrencia (res.getLong ("OID_Tipo_Ocorrencia"));
        edVolta.setOID_Usuario (res.getLong ("OID_Usuario"));
        edVolta.setOID_Conhecimento (res.getString ("OID_Conhecimento"));

        edVolta.setDT_Ocorrencia_Conhecimento (res.getString ("DT_Ocorrencia_Conhecimento"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Ocorrencia_Conhecimento ());
        edVolta.setDT_Ocorrencia_Conhecimento (DataFormatada.getDT_FormataData ());

        edVolta.setHR_Ocorrencia_Conhecimento (res.getString ("HR_Ocorrencia_Conhecimento"));
        edVolta.setTX_Observacao (res.getString ("TX_Observacao"));

        edVolta.setDM_Tipo (res.getString ("DM_Tipo"));
        edVolta.setDM_Acesso (res.getString ("DM_Acesso"));
        edVolta.setDM_Avaria (res.getString ("DM_Avaria"));
        edVolta.setDM_Status (res.getString ("DM_Status"));

        sql = " SELECT  Conhecimentos.oid_Pessoa, Conhecimentos.NR_Postagem, Conhecimentos.VL_Avaria, Conhecimentos.oid_Pessoa_Destinatario, Conhecimentos.NR_Conhecimento, Conhecimentos.DT_Emissao, Conhecimentos.oid_Unidade, Conhecimentos.NM_Pessoa_Entrega " +
            " FROM    Conhecimentos " +
            " WHERE   Conhecimentos.oid_Conhecimento = '" + res.getString ("OID_Conhecimento") + "'";
        res2 = this.executasql.executarConsulta (sql);
        if (res2.next ()) {

          // .println (" sql cto  ret 2>> ");

          edVolta.setDT_Emissao (res2.getString ("DT_Emissao"));
          DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
          edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());

          edVolta.setOID_Unidade (res2.getLong ("oid_Unidade"));
          edVolta.setOID_Pessoa (res2.getString ("OID_Pessoa"));
          edVolta.setOID_Pessoa_Destinatario (res2.getString ("OID_Pessoa_Destinatario"));
          edVolta.setNR_Conhecimento (res2.getLong ("NR_Conhecimento"));
          edVolta.setNM_Pessoa_Entrega (res2.getString ("NM_Pessoa_Entrega"));
          edVolta.setNR_Postagem (res2.getLong ("NR_Postagem"));
          edVolta.setVL_Avaria(res2.getDouble("VL_Avaria"));

        }

      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao selecionar Ocorr�ncia Conhecimento");
      excecoes.setMetodo ("sele��o");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return edVolta;
  }

  public void geraRelatorio (Ocorrencia_ConhecimentoED ed) throws Excecoes {

    String sql = null;

    Ocorrencia_ConhecimentoED edVolta = new Ocorrencia_ConhecimentoED ();

    try {

      sql = "select * from Ocorrencia_Conhecimentos where oid_Ocorrencia_Conhecimento > 0";

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);

      Ocorrencia_ConhecimentoRL Ocorrencia_Conhecimento_rl = new Ocorrencia_ConhecimentoRL ();
      Ocorrencia_Conhecimento_rl.geraRelatEstoque (res);
    }
    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro no m�todo listar");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("geraRelatorio(Ocorrencia_ConhecimentoED ed)");
    }

  }

  private Ocorrencia_ConhecimentoED baca_inclui (Ocorrencia_ConhecimentoED ed) throws Excecoes {
    Ocorrencia_ConhecimentoED ocorrencia_ConhecimentoED = new Ocorrencia_ConhecimentoED ();

    try {
      String sql = " SELECT Conhecimentos.oid_Conhecimento, DT_Ocorrencia_Conhecimento, HR_Ocorrencia_Conhecimento " +
          " FROM Ocorrencias_Conhecimentos, Conhecimentos, tipos_ocorrencias " +
          " WHERE Ocorrencias_Conhecimentos.oid_Conhecimento =  Conhecimentos.oid_Conhecimento AND Conhecimentos.DT_Entrega is NULL " +
          " AND Ocorrencias_Conhecimentos.oid_Tipo_Ocorrencia = Tipos_Ocorrencias.oid_Tipo_Ocorrencia " +
          " AND Tipos_Ocorrencias.dm_tipo in ('P', 'W', 'A', 'D', 'J') " + //tipos que sao entrega
          " Order by  Ocorrencias_Conhecimentos.DT_Ocorrencia_Lancada asc, Ocorrencias_Conhecimentos.HR_Ocorrencia_Lancada asc ";

      ResultSet res = this.executasql.executarConsulta (sql);

      while (res.next ()) {
        sql = " update Conhecimentos set DT_Entrega = '" + res.getString ("DT_Ocorrencia_Conhecimento") + "'," +
            " HR_Entrega = '" + res.getString ("HR_Ocorrencia_Conhecimento") + "'," +
            " NM_Pessoa_Entrega = ' '";
        sql += " where oid_Conhecimento = '" + res.getString ("oid_Conhecimento") + "'";
        // .println (" sql cto entrega ->> " + sql);
        executasql.executarUpdate (sql);

      }

    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "inclui(Ocorrencia_ConhecimentoED ed)");
    }
    return ocorrencia_ConhecimentoED;
  }


  private Ocorrencia_ConhecimentoED acertaDiaEntrega (Ocorrencia_ConhecimentoED ed) throws Excecoes {
	    Ocorrencia_ConhecimentoED ocorrencia_ConhecimentoED = new Ocorrencia_ConhecimentoED ();
	    long nr_dias_entrega_realizado=0;
	    long nr_dias_entrega_realizado_recalc=0;
	    String dt_Previsao_Entrega="";
	    String dt_Previsao_Entrega_atual="";
	    int qt_cto=0;
	    try {
	      String sql = " SELECT Conhecimentos.oid_Conhecimento, DT_Emissao, DT_Previsao_Entrega, DT_Entrega, nr_dias_entrega_realizado " +
	          " FROM  Conhecimentos " +
	          " WHERE Conhecimentos.DT_Emissao >='01/01/2008' AND dt_emissao <='10/01/2009'  AND DM_Impresso='S' and DM_Situacao<>'C' ";

	      ResultSet res = this.executasql.executarConsulta (sql);

	      while (res.next () ) {
	    	  qt_cto++;

	    	  nr_dias_entrega_realizado=res.getLong("nr_dias_entrega_realizado");
	    	  dt_Previsao_Entrega_atual=FormataData.formataDataBT (res.getString ("DT_Previsao_Entrega"));

	    	  //if (dt_Previsao_Entrega == null || dt_Previsao_Entrega.length()<5) {
	    		  dt_Previsao_Entrega=Data.getSomaDiaData(FormataData.formataDataBT (res.getDate ("DT_Emissao")),1);
	    	  //}

	    		  int acertadia=0;
    		  dt_Previsao_Entrega=Data.getSomaDiaData(dt_Previsao_Entrega,acertadia);
    		  if (!dt_Previsao_Entrega_atual.equals(dt_Previsao_Entrega)) {
		    	  sql = " update Conhecimentos set nr_prazo_entrega=1, tx_local_retirada='REC3', dt_Previsao_Entrega = '"+ dt_Previsao_Entrega  + "'" +
		    	  		" where oid_Conhecimento = '" + res.getString ("oid_Conhecimento") + "'";

		    	  executasql.executarUpdate (sql);
    		  }
			  String dt_entrega = res.getString ("DT_Entrega");

		   	  if (1==1) {
		    	  if (res.getString ("DT_Entrega")!=null && res.getString ("DT_Entrega").length()>4) {
	    			  //.println ("dt_entrega 1=>>>"+dt_entrega);
		    		  nr_dias_entrega_realizado_recalc=this.calcula_Dias_Entrega(res.getString ("oid_Conhecimento"), FormataData.formataDataBT (res.getString ("DT_Entrega")));
		    		  if (nr_dias_entrega_realizado_recalc<-3) {
		    			  nr_dias_entrega_realizado_recalc=0;
		    			  nr_dias_entrega_realizado=999;
		    			  dt_entrega = res.getString ("DT_Emissao");
		    		  }
		    		  if (nr_dias_entrega_realizado_recalc>10) {
		    			  nr_dias_entrega_realizado_recalc=1;
		    			  nr_dias_entrega_realizado=999;
		    			  dt_entrega = res.getString ("DT_Previsao_Entrega");
		    		  }

			    	  if (nr_dias_entrega_realizado_recalc!=nr_dias_entrega_realizado)	{
				    	  sql = " update Conhecimentos set dt_entrega='" + dt_entrega + "' ,nr_dias_entrega_realizado = "+ nr_dias_entrega_realizado_recalc  +
				    	  	   " where oid_Conhecimento = '" + res.getString ("oid_Conhecimento") + "'";
				    	  executasql.executarUpdate (sql);
			    	  }
		    	  }	else {
			    	  sql = " update Conhecimentos set  nr_dias_entrega_realizado = 0 where oid_Conhecimento = '" + res.getString ("oid_Conhecimento") + "'";
				    executasql.executarUpdate (sql);
		    	  }

		   	  }

	      }

	    }
	    catch (SQLException e) {
	      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "acertaDiaEntrega");
	    }
	    return ocorrencia_ConhecimentoED;
	  }

  private Ocorrencia_ConhecimentoED acertaPessoaEntrega (Ocorrencia_ConhecimentoED ed) throws Excecoes {
	    Ocorrencia_ConhecimentoED ocorrencia_ConhecimentoED = new Ocorrencia_ConhecimentoED ();
	    long nr_dias_entrega_realizado=0;
	    long nr_dias_entrega_realizado_recalc=0;
	    int qt_cto=0;
	    try {
	      String sql = " SELECT Conhecimentos.oid_Conhecimento, DT_Emissao, DT_Previsao_Entrega, DT_Entrega, nr_dias_entrega_realizado " +
	          " FROM  Conhecimentos " +
	          " WHERE Conhecimentos.DT_Emissao >='01/01/2008'  AND DM_Impresso='S' and DM_Situacao<>'C' ";

	      ResultSet res = this.executasql.executarConsulta (sql);

	      while (res.next () ) {
	    	  qt_cto++;

	    	  nr_dias_entrega_realizado=res.getLong("nr_dias_entrega_realizado");

	    	  if (res.getString ("DT_Entrega")!=null && res.getString ("DT_Entrega").length()>4) {
	    		  nr_dias_entrega_realizado_recalc=this.calcula_Dias_Entrega(res.getString ("oid_Conhecimento"), FormataData.formataDataBT (res.getString ("DT_Entrega")));
	    	  }
	    	  if (nr_dias_entrega_realizado_recalc!=nr_dias_entrega_realizado)	{
		    	  sql = " update Conhecimentos set nr_dias_entrega_realizado = "+ nr_dias_entrega_realizado_recalc  +
		    	  	   " where oid_Conhecimento = '" + res.getString ("oid_Conhecimento") + "'";
		    	  //// .println (" sql cto entrega ->> " + sql);
		    	  executasql.executarUpdate (sql);
	    	  }

	    	  // .println (" CALCULANDO ENTRGA->> " + res.getString ("oid_Conhecimento")+"  "+ res.getString ("DT_Emissao")+ " Dias=" + nr_dias_entrega_realizado + " Dias Recalc=" + nr_dias_entrega_realizado_recalc);

	      }

	    }
	    catch (SQLException e) {
	      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "acertaDiaEntrega");
	    }
	    return ocorrencia_ConhecimentoED;
	  }




  private long calcula_Dias_Entrega (String oid_Conhecimento , String dt_Entrega) throws Excecoes {
	    long dias_Entrega = 0;
	    String dt_previsao_entrega="";
	    return dias_Entrega;
	  }

}
