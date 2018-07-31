
package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import com.master.ed.ColetaED;
import com.master.rl.ColetaRL;
import com.master.root.CidadeBean;
import com.master.root.PessoaBean;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.JavaUtil;
import com.master.util.Utilitaria;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;

public class ColetaBD {

  private ExecutaSQL executasql;
  Utilitaria util = new Utilitaria ();
  Parametro_FixoED parametro_FixoED = new Parametro_FixoED ();

  public ColetaBD (ExecutaSQL sql) {
    this.executasql = sql;
  }

  public ColetaED inclui (ColetaED ed) throws Excecoes {

//  // .println(" bd  1");

    String sql = null;

    try {

      ed.setOID_Coleta(String.valueOf (System.currentTimeMillis ()).substring(7,13));

      if ("X".equals (ed.getDM_Tipo_Coleta ()) && ed.getOID_Pessoa_Usuario() !=null && ed.getOID_Pessoa_Usuario().length()>4) {
        ed.setOID_Pessoa_Pagador(ed.getOID_Pessoa_Usuario());
      }

      // .println("NOVO OID COLETA="+ed.getOID_Pessoa_Pagador());


      sql = " INSERT INTO Coletas (" +
          "OID_Coleta, " +
          "OID_Unidade, " +
          "OID_Pessoa, " +
          "OID_Pessoa_Usuario, " +
          "OID_Pessoa_Pagador, " +
          "OID_Motorista, " +
          "OID_Veiculo, " +
          "OID_Carreta, " +
          "OID_Carreta2, " +
          "NR_Cartao, " +
          "NR_Liberacao, " +
          "OID_Pessoa_Destinatario, " +
          "OID_Cidade, " +
          "OID_Produto, " +
          "OID_Usuario, " +
          "OID_Cidade_Destinatario, " +
          "NM_Documento, " +
          "NM_Especie, " +
          "NM_Contato, " +
          "NR_Ramal_Contato, " +          
          "NM_Solicitante, " +
          "NR_Ramal_Solicitante, " +          
          "NR_Pedido, " +
          "DT_Coleta, " +
          "HR_Coleta, " +
          "DT_Programada, " +
          "HR_Programada, " +
          "DT_Previsao_Entrega, " +
          "HR_Previsao_Entrega, " +
          "HR_Coleta_Minima, " +
          "HR_Coleta_Maxima, " +
          "NR_Volumes, " +
          "NR_Peso, " +
          "NR_Peso_Cubado, " +
          "NR_Cubagem, " +
          "VL_Mercadoria, " +
          "NM_Endereco_Coleta, " +
          "NM_Bairro_Coleta, " +
          "TX_Observacao, " +
          "NR_Telefone, " +
          "NM_E_Mail, " +
          "DM_Tipo, " +
          "DM_Tipo_Veiculo, " +
          "DM_Situacao_Cotacao, " +
          "DM_Tipo_Coleta, " +
          "DM_Situacao, " +
          "oid_modal, " +
          "oid_programacao_carga, " +
          "oid_Cotacao, " +
          "DT_EMISSAO, " +
          "HR_EMISSAO) values ";

      sql += "(" + ed.getOID_Coleta () +
          "," + ed.getOID_Unidade () +
          ",'" + ed.getOID_Pessoa () + "'" +
          ",'" + ed.getOID_Pessoa_Usuario () + "'" +
          ",'" + ed.getOID_Pessoa_Pagador () + "'" +
          ",'" + ed.getOID_Motorista () + "'" +
          ",'" + ed.getOID_Veiculo () + "'" +
          ",'" + ed.getOID_Carreta () + "'" +
          ",'" + ed.getOID_Carreta2 () + "'" +
          ",'" + ed.getNR_Cartao () + "'" +
          ",'" + ed.getNR_Liberacao () + "'" +
          ",'" + ed.getOID_Pessoa_Destinatario () + "'" +
          "," + ed.getOID_Cidade () +
          "," + ed.getOID_Produto () +
          "," + ed.getOid_Usuario () +
          "," + ed.getOID_Cidade_Destinatario () +
          ",'" + ed.getNM_Documento () + "'" +
          ",'" + ed.getNM_Especie () + "'" +
          ",'" + ed.getNM_Pessoa_Contato () + "'" +          
          "," + ed.getNR_Ramal_Contato() +          
          ",'" + ed.getNM_Pessoa_Solicitante () + "'" +
          "," + ed.getNR_Ramal_Solicitante() +          
          ",'" + ed.getNR_Pedido () + "'" +
          ",'" + ed.getDT_Coleta () + "'" +
          ",'" + ed.getHR_Coleta () + "'" +
          ",'" + ed.getDT_Programada () + "'" +
          ",'" + ed.getHR_Programada () + "'" +
          ",'" + ed.getDT_Previsao_Entrega () + "'" +
          ",'" + ed.getHR_Previsao_Entrega () + "'" +
          ",'" + ed.getHR_Coleta_Minima () + "'" +
          ",'" + ed.getHR_Coleta_Maxima () + "'" +
          "," + ed.getNR_Volumes () +
          "," + ed.getNR_Peso () +
          "," + ed.getNR_Peso_Cubado () +
          "," + ed.getNR_Cubagem () +
          "," + ed.getVL_Mercadoria () +
          ",'" + ed.getNM_Endereco_Coleta () + "'" +
          ",'" + ed.getNM_Bairro_Coleta () + "'" +
          ",'" + ed.getTX_Observacao () + "'" +
          ",'" + ed.getNR_Telefone () + "'" +
          ",'" + ed.getNM_E_Mail () + "'" +
          ",'" + ed.getDM_Tipo () + "'" +
          ",'" + ed.getDM_Tipo_Veiculo () + "'" +
          ",'N'" +
          ",'" + ed.getDM_Tipo_Coleta () + "'" +
          ",'1'" +
          "," + ed.getOID_Modal () +
          "," + ed.getOid_Programacao_Carga() +
          ",'" + ed.getOID_Cotacao () + "'" +
          ",'" + Data.getDataDMY () + "'" +
          ",'" + Data.getHoraHM () + "')";
      executasql.executarUpdate (sql);

      if (ed.getOID_Cotacao () != null && ed.getOID_Cotacao ().length () > 4) {
        // .println ("Coleta 90 ");
        sql = " UPDATE Cotacoes SET oid_Coleta= " + ed.getOID_Coleta ();
        sql += " WHERE OID_Cotacao = '" + ed.getOID_Cotacao () + "'";
        // .println (sql);
        executasql.executarUpdate (sql);
      }
      long nr_Coleta = 0;
      if ("I".equals (parametro_FixoED.getDM_Numera_Coleta ())) {
        nr_Coleta = numeraColeta (ed);
      }
      if ("X".equals (ed.getDM_Tipo_Coleta ())) {
        nr_Coleta = numeraColeta (ed);
        // .println("ENVIANDO EMAIL");
        new Pessoa_EMailBD (this.executasql).envia_eMail (ed.getOID_Pessoa_Usuario () , "APFSOL" , "" , "  NR Solicitacao:" + ed.getOID_Coleta () , "" , "");
      }

      new Pessoa_EMailBD (this.executasql).envia_eMail (ed.getOID_Pessoa () , "INCCOL" , "" , " Inclusao da Coleta NR:" + nr_Coleta , "" , "");

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao incluir Coleta");
      excecoes.setMetodo ("inclui");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return ed;
  }

  public ColetaED getByRecord (ColetaED ed) throws Excecoes {
    ColetaED edVolta = new ColetaED ();

    try {
      ResultSet res = this.executasql.executarConsulta (montaSql (ed));
      while (res.next ()) {
        edVolta = carregaED (res);
      }

    }
    catch (SQLException exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "getByRecord()");
    }
    return edVolta;
  }

  private ColetaED carregaED (ResultSet res) throws Excecoes {
    ColetaED edVolta = new ColetaED ();
    ResultSet resLocal = null;

    // .println ("carregaED 1 ");

    try {
      // .println ("carregaED 2 ");

      edVolta.setOID_Coleta (res.getString ("OID_Coleta"));
      edVolta.setNR_Coleta ("------");

      if (JavaUtil.doValida (res.getString ("NR_Coleta"))) {
        edVolta.setNR_Coleta (res.getString ("NR_Coleta"));
      }
      edVolta.setDM_Impresso(res.getString ("DM_Impresso"));
      edVolta.setDM_Finalizado(res.getString ("DM_Finalizado"));

      edVolta.setNR_Pedido (res.getString ("NR_Pedido"));
      edVolta.setOID_Modal (res.getLong ("OID_Modal"));
      edVolta.setOID_Produto (res.getLong ("OID_Produto"));
      edVolta.setOid_Usuario (res.getLong ("OID_Usuario"));
      edVolta.setOID_Pessoa (res.getString ("OID_Pessoa"));
      edVolta.setOID_Pessoa_Usuario (res.getString ("OID_Pessoa_Usuario"));
      edVolta.setOID_Pessoa_Pagador (res.getString ("OID_Pessoa_Pagador"));
      edVolta.setOID_Pessoa_Destinatario (res.getString ("OID_Pessoa_Destinatario"));
      edVolta.setOID_Cidade (res.getLong ("OID_Cidade"));
      edVolta.setOID_Cidade_Destinatario (res.getLong ("OID_Cidade_Destinatario"));
      int c = 0;
      if (JavaUtil.doValida (res.getString ("OID_Cidade_Destinatario"))) {
        c = new Long (edVolta.getOID_Cidade_Destinatario ()).intValue ();
        edVolta.setNM_Cidade_Destinatario (CidadeBean.getByOID (c).getNM_Cidade ());
        edVolta.setCD_Cidade_Destinatario (CidadeBean.getByOID (c).getCD_Cidade ());
      }
      else {
        edVolta.setNM_Cidade_Destinatario ("");
        edVolta.setCD_Cidade_Destinatario ("");
      }
      edVolta.setNM_Documento (res.getString ("NM_Documento"));
      edVolta.setNM_Especie (res.getString ("NM_Especie"));
      edVolta.setNM_Pessoa_Contato (res.getString ("NM_Contato"));
      edVolta.setNM_Pessoa_Solicitante (res.getString ("NM_Solicitante"));
      edVolta.setNR_Ramal_Contato(res.getLong ("NR_Ramal_Contato"));
      edVolta.setNR_Ramal_Solicitante (res.getLong ("NR_Ramal_Solicitante"));
      edVolta.setDT_Coleta (FormataData.formataDataBT (res.getDate ("DT_Coleta")));
      edVolta.setDT_Coletado (FormataData.formataDataBT (res.getDate ("DT_Coletado")));
      edVolta.setHR_Coletado (res.getString ("HR_Coletado"));
      edVolta.setHR_Coleta_Minima (res.getString ("HR_Coleta_Minima"));
      edVolta.setHR_Coleta_Maxima (res.getString ("HR_Coleta_Maxima"));
      edVolta.setNR_Volumes (res.getLong ("NR_Volumes"));
      edVolta.setNR_Peso (res.getDouble ("NR_Peso"));
      edVolta.setNR_Peso_Cubado (res.getDouble ("NR_Peso_Cubado"));
      edVolta.setNR_Cubagem (res.getDouble ("NR_Cubagem"));
      edVolta.setVL_Mercadoria (res.getDouble ("VL_Mercadoria"));
      edVolta.setVL_Cotacao (res.getDouble ("VL_Cotacao"));
      edVolta.setNM_Endereco_Coleta (res.getString ("NM_Endereco_Coleta"));
      edVolta.setNM_Bairro_Coleta (res.getString ("NM_Bairro_Coleta"));
      edVolta.setTX_Observacao (res.getString ("TX_Observacao"));
      edVolta.setNR_Telefone (res.getString ("NR_Telefone"));
      edVolta.setDM_Tipo_Veiculo (res.getString ("DM_Tipo_Veiculo"));
      edVolta.setNR_Liberacao (res.getString ("NR_Liberacao"));
      edVolta.setNR_Cartao (res.getString ("NR_Cartao"));
      edVolta.setNM_E_Mail (res.getString ("NM_E_Mail"));

      edVolta.setNM_Cidade (res.getString ("NM_Cidade"));
      edVolta.setCD_Cidade (res.getString ("CD_Cidade"));
      edVolta.setNM_Fantasia (res.getString ("NM_Fantasia"));
      edVolta.setCD_Unidade (res.getString ("CD_Unidade"));
      edVolta.setOID_Unidade (res.getLong ("OID_Unidade"));

      edVolta.setDT_Emissao (FormataData.formataDataBT (res.getString ("DT_Emissao")));
      edVolta.setHR_Emissao (res.getString ("HR_Emissao"));

      edVolta.setDT_Programada (FormataData.formataDataBT (res.getString ("DT_Programada")));
      edVolta.setDT_Previsao_Entrega (FormataData.formataDataBT (res.getString ("DT_Previsao_Entrega")));
      edVolta.setHR_Previsao_Entrega (res.getString ("HR_Previsao_Entrega"));

      edVolta.setDT_Coleta (FormataData.formataDataBT (res.getString ("DT_Coleta")));

      edVolta.setNM_Pessoa_Remetente (res.getString ("NM_Razao_Social"));
      edVolta.setNM_Destinatario ( (res.getString ("NM_Cidade") + "              ").substring (0 , 15));

      if (util.doValida (res.getString ("OID_Pessoa_Destinatario"))) {
        PessoaBean pb = PessoaBean.getByOID (edVolta.getOID_Pessoa_Destinatario ());
        edVolta.setNM_Pessoa_Destinatario (pb.getNM_Razao_Social ());
        edVolta.setNM_Destinatario ( (pb.getNM_Razao_Social () + "              ").substring (0 , 15) + "/" + (res.getString ("NM_Cidade") + "              ").substring (0 , 15));
      }


      edVolta.setOID_Motorista ("");
      edVolta.setOID_Motorista (" ");
      edVolta.setNM_Motorista (" ");
      if (util.doValida (res.getString ("OID_Motorista"))) {
          edVolta.setOID_Motorista (res.getString ("OID_Motorista"));
          PessoaBean pb = PessoaBean.getByOID (res.getString ("OID_Motorista"));
          edVolta.setNM_Motorista (pb.getNM_Razao_Social ());
        }

      edVolta.setDM_Procedencia ("T");

      // .println ("carregaED 3 ");

      edVolta.setOID_Veiculo (res.getString ("OID_Veiculo"));
      edVolta.setOID_Carreta (res.getString ("OID_Carreta"));
      edVolta.setOID_Carreta2 (res.getString ("OID_Carreta2"));
      edVolta.setNR_Placa ("");
      if (util.doValida (res.getString ("OID_Veiculo"))) {
        String sql = " SELECT DM_Procedencia " +
            " FROM   Complementos_Veiculos " +
            " WHERE Complementos_Veiculos.oid_Veiculo= '" + res.getString ("OID_Veiculo") + "'";
        resLocal = this.executasql.executarConsulta (sql);
        while (resLocal.next ()) {
          edVolta.setDM_Procedencia (resLocal.getString ("DM_Procedencia"));
        }
        edVolta.setNR_Placa (res.getString ("OID_Veiculo"));
        edVolta.setNR_Placa_Carreta (res.getString ("OID_Carreta"));
      }

      edVolta.setDM_Tipo (res.getString ("DM_Tipo"));
      edVolta.setDM_Tipo_Coleta (res.getString ("DM_Tipo_Coleta"));
      edVolta.setNM_Tipo_Coleta ("Coleta");
      edVolta.setNM_Situacao_Cotacao("---");
      edVolta.setDM_Situacao (res.getString ("DM_Situacao"));

      if ("C".equals(res.getString("DM_Situacao"))) {
          edVolta.setNM_Situacao ("**CANCELADA**");
      }else {

          if ("1".equals(res.getString("DM_Situacao"))) {
              edVolta.setNM_Situacao("� REALIZAR");
          }
          if ("2".equals(res.getString("DM_Situacao"))) {
              edVolta.setNM_Situacao("ALOCADA");
          }
          if ("3".equals(res.getString("DM_Situacao"))) {
              edVolta.setNM_Situacao("REALIZADA");
          }
          if ("4".equals(res.getString("DM_Situacao"))) {
              edVolta.setNM_Situacao("REMARCADA");
          }

	      if ("P".equals (res.getString ("DM_Tipo_Coleta"))) {
	        edVolta.setNM_Tipo_Coleta ("Prog.Carga");
	      }
	      if ("A".equals (res.getString ("DM_Tipo_Coleta"))) {
	        edVolta.setNM_Tipo_Coleta ("Col.Aut.");
	      }

	      if ("X".equals (res.getString ("DM_Tipo_Coleta"))) {
	        edVolta.setNM_Tipo_Coleta ("Aprov.Frete");
	        edVolta.setDM_Situacao_Cotacao (res.getString ("DM_Situacao_Cotacao"));
	        edVolta.setNM_Situacao_Cotacao ("Aberta");
	        if ("S".equals (edVolta.getDM_Situacao_Cotacao ())) {
	          edVolta.setNM_Situacao_Cotacao ("Cotada");
	        }
	        if ("A".equals (edVolta.getDM_Situacao_Cotacao ())) {
	          edVolta.setNM_Situacao_Cotacao ("Confirmada");
	        }
	        if ("C".equals (edVolta.getDM_Situacao_Cotacao ())) {
	          edVolta.setNM_Situacao_Cotacao ("Reprovada");
	        }
	        if ("X".equals (edVolta.getDM_Situacao_Cotacao ())) {
	          edVolta.setNM_Situacao_Cotacao ("Substituida");
	        }
	        if ("R".equals (edVolta.getDM_Situacao_Cotacao ())) {
	          edVolta.setNM_Situacao_Cotacao ("Revisar Custo");
	        }
	      }

	      String sql = " SELECT oid_Conhecimento, NR_Conhecimento  " +
	          " FROM   Conhecimentos " +
	          " WHERE  Conhecimentos.oid_Coleta= '" + res.getString ("oid_Coleta") + "'";
	      resLocal = this.executasql.executarConsulta (sql);
	      while (resLocal.next ()) {
	        edVolta.setOID_Conhecimento(resLocal.getString ("oid_Conhecimento"));
	        edVolta.setNR_Conhecimento(resLocal.getLong ("NR_Conhecimento"));
	        edVolta.setDM_Situacao_Cotacao ("E");
	        edVolta.setNM_Situacao_Cotacao ("Embarcadas");
	      }
      }
    }
    catch (SQLException exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "carregaED()");
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "carregaED()");
    }
    return edVolta;
  }

  private String montaSql (ColetaED ed) throws Excecoes {
    int limit=200;
    String sql = " SELECT Coletas.*, " +
        "        Pessoa_Unidade.NM_Fantasia, " +
        "        Pessoas.NM_Razao_Social, " +
        "        Cidades.NM_Cidade, " +
        "        Modal.NM_Modal, " +
        "        Cidades.CD_Cidade, " +
        "        Modal.CD_Modal, " +
        "        Unidades.CD_Unidade " +
        " FROM Coletas, Unidades, Pessoas, Modal, Cidades, Pessoas Pessoa_Unidade " +
        " WHERE Coletas.OID_Unidade = Unidades.OID_Unidade " +
        " AND Coletas.OID_Modal = Modal.OID_Modal " +
        " AND Coletas.OID_Pessoa = Pessoas.OID_Pessoa " +
        " AND Coletas.OID_Cidade = Cidades.OID_Cidade " +
        " AND Pessoa_Unidade.OID_Pessoa = Unidades.OID_Pessoa ";

    if (JavaUtil.doValida (ed.getOID_Coleta ())) {
      sql += " and Coletas.OID_Coleta = " + ed.getOID_Coleta ();
    }
    else {
      if (JavaUtil.doValida (ed.getNR_Coleta ())) {
        sql += " and Coletas.NR_Coleta = " + ed.getNR_Coleta ();
      }
      if (JavaUtil.doValida (ed.getOID_Veiculo ())) {
        sql += " and Coletas.oid_Veiculo = '" + ed.getOID_Veiculo () + "'";
        limit=9999;
      }
      if (JavaUtil.doValida (String.valueOf (ed.getOID_Unidade ()))) {
        sql += " and Coletas.OID_Unidade = " + ed.getOID_Unidade ();
        limit=9999;
      }
      if (JavaUtil.doValida (ed.getOID_Carreta ())) {
        sql += " and Coletas.oid_Carreta = '" + ed.getOID_Carreta () + "'";
      }
      if (JavaUtil.doValida (ed.getNR_Pedido ())) {
        sql += " and Coletas.NR_Pedido = '" + ed.getNR_Pedido () + "'";
      }
      if (JavaUtil.doValida (ed.getDM_Situacao ()) &&
          !String.valueOf (ed.getDM_Situacao ()).equals ("T")) {
        sql += " and Coletas.DM_Situacao = '" + ed.getDM_Situacao () + "'";
      }
      if (JavaUtil.doValida (ed.getDM_Situacao_Cotacao ()) &&
          !String.valueOf (ed.getDM_Situacao_Cotacao ()).equals ("T")) {
        sql += " and Coletas.DM_Situacao_Cotacao = '" + ed.getDM_Situacao_Cotacao () + "'";
      }
      if (JavaUtil.doValida (ed.getDM_Tipo ()) &&
          !String.valueOf (ed.getDM_Tipo ()).equals ("")) {
        sql += " and Coletas.DM_Tipo = '" + ed.getDM_Tipo () + "'";
      }
      if (JavaUtil.doValida (ed.getDM_Tipo_Coleta ()) &&
          !String.valueOf (ed.getDM_Tipo_Coleta ()).equals ("T")) {
        sql += " and Coletas.DM_Tipo_Coleta = '" + ed.getDM_Tipo_Coleta () + "'";
      }

      if (JavaUtil.doValida (ed.getOID_Pessoa_Usuario ())) {
    	  sql += " and Coletas.OID_Pessoa_Usuario = '" + ed.getOID_Pessoa_Usuario () + "'";
    	  limit=9999;
      }else {
    	  if (JavaUtil.doValida (ed.getOID_Pessoa ())) {
    		  sql += " and Coletas.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
    		  limit=9999;
    	  }
    	  if (JavaUtil.doValida (ed.getOID_Pessoa_Destinatario ())) {
    		  sql += " and Coletas.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario () + "'";
    		  limit=9999;
    	  }
    	  if (JavaUtil.doValida (ed.getOID_Pessoa_Pagador ())) {
    		  sql += " and Coletas.OID_Pessoa_Pagador = '" + ed.getOID_Pessoa_Pagador () + "'";
    		  limit=9999;
    	  }
      }
      if (JavaUtil.doValida (ed.getOID_Motorista ())) {
    	  sql += " and Coletas.OID_Motorista = '" + ed.getOID_Motorista () + "'";
    	  limit=9999;
      }

      if (JavaUtil.doValida (ed.getDT_Emissao ())) {
        sql += " and Coletas.DT_Coleta >= '" + ed.getDT_Emissao () + "'";
        limit=9999;
      }
      if (JavaUtil.doValida (ed.getDT_Emissao_Final ())) {
        sql += " and Coletas.DT_Coleta <= '" + ed.getDT_Emissao_Final () + "'";
        limit=9999;
      }

      if ("P".equals (ed.getDM_Tipo_Coleta ())) {
        if (JavaUtil.doValida (ed.getDT_Programada_Inicial ())) {
          sql += " and Coletas.DT_Programada >= '" + ed.getDT_Programada_Inicial () + "'";
          limit=9999;
        }
        if (JavaUtil.doValida (ed.getDT_Programada_Final ())) {
          sql += " and Coletas.DT_Programada <= '" + ed.getDT_Programada_Final () + "'";
          limit=9999;
        }
      }
      if (JavaUtil.doValida (ed.getDM_Situacao ()) &&
          !String.valueOf (ed.getDM_Situacao ()).equals ("T")) {
        sql += " and Coletas.DM_Situacao = '" + ed.getDM_Situacao () + "'";
      }
    }
    sql += " ORDER BY Coletas.DT_Coleta desc, Coletas.NR_Coleta  DESC LIMIT "+limit;

    return sql;
  }
   
  public ArrayList Relatorio_Lista_Filtro (ColetaED ed) throws Excecoes {
	    String sql = null;
	    ArrayList list = new ArrayList ();
	    try {
	      int limit=200;
	        sql = " SELECT Coletas.*, " +
	        "        Pessoa_Unidade.NM_Fantasia as NM_Unidade, " +
	        "        Pessoa_Remetente.NM_Razao_Social as NM_Remetente, " +
	        "        Pessoa_Destinatario.NM_Razao_Social as NM_Destinatario_1, " +
	        "        Pessoa_Motorista.NM_Razao_Social as NM_Motorista, " +	        
	        "        Pessoas.NM_Razao_Social, " +
	        "        Cidades.NM_Cidade, " +
	        "        Modal.NM_Modal, " +
	        "        Cidades.CD_Cidade, " +
	        "        Modal.CD_Modal, " +
	        "        Unidades.CD_Unidade " +
	        " FROM " +
	        "	Unidades, " +
	        "	Pessoas, " +
	        "	Modal, " +
	        "	Cidades, " +
	        "	Pessoas Pessoa_Unidade, " +
	        "	Pessoas Pessoa_Remetente, " +
	        "	Coletas " +
	        " LEFT JOIN Pessoas Pessoa_Destinatario" +
	        " 	ON Pessoa_Destinatario.OID_Pessoa = Coletas.OID_Pessoa_Destinatario " +
	        " LEFT JOIN Pessoas Pessoa_Motorista" +
	        "	ON 	Pessoa_Motorista.OID_Pessoa = Coletas.OID_Motorista" +	        
	        " WHERE Coletas.OID_Unidade = Unidades.OID_Unidade " +
	        " AND Coletas.OID_Modal = Modal.OID_Modal " +
	        " AND Coletas.OID_Pessoa = Pessoas.OID_Pessoa " +
	        " AND Coletas.OID_Cidade = Cidades.OID_Cidade " +
	        " AND Pessoa_Unidade.OID_Pessoa = Unidades.OID_Pessoa "+
	        " AND Pessoa_Remetente.OID_Pessoa = Coletas.OID_Pessoa ";	        
	        

	    if (JavaUtil.doValida (ed.getOID_Coleta ())) {
	      sql += " and Coletas.OID_Coleta = " + ed.getOID_Coleta ();
	    }
	    else {
	      if (JavaUtil.doValida (ed.getNR_Coleta ())) {
	        sql += " and Coletas.NR_Coleta = " + ed.getNR_Coleta ();
	      }
	      if (JavaUtil.doValida (ed.getOID_Veiculo ())) {
	        sql += " and Coletas.oid_Veiculo = '" + ed.getOID_Veiculo () + "'";
	        limit=9999;
	      }
	      if (JavaUtil.doValida (String.valueOf (ed.getOID_Unidade ()))) {
	        sql += " and Coletas.OID_Unidade = " + ed.getOID_Unidade ();
	        limit=9999;
	      }
	      if (JavaUtil.doValida (ed.getOID_Carreta ())) {
	        sql += " and Coletas.oid_Carreta = '" + ed.getOID_Carreta () + "'";
	      }
	      if (JavaUtil.doValida (ed.getNR_Pedido ())) {
	        sql += " and Coletas.NR_Pedido = '" + ed.getNR_Pedido () + "'";
	      }
	      if (JavaUtil.doValida (ed.getDM_Situacao ()) &&
	          !String.valueOf (ed.getDM_Situacao ()).equals ("T")) {
	        sql += " and Coletas.DM_Situacao = '" + ed.getDM_Situacao () + "'";
	      }
	      if (JavaUtil.doValida (ed.getDM_Situacao_Cotacao ()) &&
	          !String.valueOf (ed.getDM_Situacao_Cotacao ()).equals ("T")) {
	        sql += " and Coletas.DM_Situacao_Cotacao = '" + ed.getDM_Situacao_Cotacao () + "'";
	      }
	      if (JavaUtil.doValida (ed.getDM_Tipo ()) &&
	          !String.valueOf (ed.getDM_Tipo ()).equals ("")) {
	        sql += " and Coletas.DM_Tipo = '" + ed.getDM_Tipo () + "'";
	      }
	      if (JavaUtil.doValida (ed.getDM_Tipo_Coleta ()) &&
	          !String.valueOf (ed.getDM_Tipo_Coleta ()).equals ("T")) {
	        sql += " and Coletas.DM_Tipo_Coleta = '" + ed.getDM_Tipo_Coleta () + "'";
	      }
    	  if (JavaUtil.doValida (ed.getOID_Pessoa ())) {
    		  sql += " and Pessoa_Remetente.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
    		  limit=9999;
    	  }
    	  if (JavaUtil.doValida (ed.getOID_Pessoa_Destinatario ())) {
    		  sql += " and Coletas.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario () + "'";
    		  limit=9999;
    	  }
    	  if (JavaUtil.doValida (ed.getOID_Pessoa_Pagador ())) {
    		  sql += " and Coletas.OID_Pessoa_Pagador = '" + ed.getOID_Pessoa_Pagador () + "'";
    		  limit=9999;
    	  }
	      if (JavaUtil.doValida (ed.getOID_Motorista ())) {
	    	  sql += " and Coletas.OID_Motorista = '" + ed.getOID_Motorista () + "'";
	    	  limit=9999;
	      }
	      if (JavaUtil.doValida (ed.getNR_Placa_Carreta())) {
	    	  sql += " and Coletas.NR_Placa_Carreta = '" + ed.getNR_Placa_Carreta() + "'";
	    	  limit=9999;
	      }
	      if (JavaUtil.doValida (ed.getDT_Emissao ())) {
	        sql += " and Coletas.DT_Coleta >= '" + ed.getDT_Emissao () + "'";
	        limit=9999;
	      }
	      if (JavaUtil.doValida (ed.getDT_Emissao_Final ())) {
	        sql += " and Coletas.DT_Coleta <= '" + ed.getDT_Emissao_Final () + "'";
	        limit=9999;
	      }

	      if ("P".equals (ed.getDM_Tipo_Coleta ())) {
	        if (JavaUtil.doValida (ed.getDT_Programada_Inicial ())) {
	          sql += " and Coletas.DT_Programada >= '" + ed.getDT_Programada_Inicial () + "'";
	          limit=9999;
	        }
	        if (JavaUtil.doValida (ed.getDT_Programada_Final ())) {
	          sql += " and Coletas.DT_Programada <= '" + ed.getDT_Programada_Final () + "'";
	          limit=9999;
	        }
	      }
	      if (JavaUtil.doValida (ed.getDM_Situacao ()) &&
	          !String.valueOf (ed.getDM_Situacao ()).equals ("T")) {
	        sql += " and Coletas.DM_Situacao = '" + ed.getDM_Situacao () + "'";
	      }
	    }
	    sql += " ORDER BY Coletas.DT_Emissao desc, Coletas.NR_Coleta DESC LIMIT "+limit;

	    ResultSet res = this.executasql.executarConsulta (sql);

	      while (res.next ()) {
	        ColetaED edVolta = new ColetaED ();
	        edVolta = populaRegistro (res , edVolta);
	        list.add (edVolta);
	      }
	      return list;
	    }
	    catch (Exception exc) {
	      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,"lista(ColetaED ed)");
	    }
	  }
  

  public ArrayList lista (ColetaED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();

    try {
      sql = " SELECT OID_Coleta, " +
          " DT_Coleta, " +
          " DT_Coletado, " +
          " DM_Finalizado, " +          
          " HR_Coletado, " +          
          " DT_Programada, " +
          " HR_Coleta, " +
          " Viagens.DT_Previsao_Chegada, " +
          " Viagens.HR_Previsao_Chegada, " +
          " Conhecimentos.OID_Conhecimento, " +
          " Conhecimentos.NR_Conhecimento, " +
          " Conhecimentos.DT_Emissao, " +
          " Coletas.NR_Coleta, " +
          " Unidades.CD_Unidade, " +
          " Pessoa_Remetente.NM_Razao_Social as NM_Razao_Social_Remetente, " +
          " Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario, " +
          " Coletas.OID_Coleta, Cidades.NM_Cidade" +
          " FROM Viagens, Conhecimentos, Unidades, Pessoas Pessoa_Remetente, Pessoas Pessoa_Destinatario, Coletas, Cidades " +
          " WHERE Unidades.oid_Unidade = Conhecimentos.oid_Unidade " +
          " AND Conhecimentos.oid_Pessoa = Pessoa_Remetente.oid_Pessoa " +
          " AND Conhecimentos.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa " +
          " AND Viagens.OID_Conhecimento = Conhecimentos.OID_Conhecimento " +
          " AND Viagens.OID_Coleta = Coletas.OID_Coleta " +
          " AND Conhecimentos.OID_Cidade_Destino = Cidades.OID_Cidade ";

      if (String.valueOf (ed.getOID_Coleta ()) != null &&
          !String.valueOf (ed.getOID_Coleta ()).equals ("0") &&
          !String.valueOf (ed.getOID_Coleta ()).equals ("null")) {
        sql += " and Coletas.OID_Coleta = '" + ed.getOID_Coleta () + "'";
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
        sql += " and Conhecimentos.OID_Pessoa_Destinatario = '" +
            ed.getOID_Pessoa_Destinatario () + "'";
      }
      if (String.valueOf (ed.getDT_Emissao ()) != null &&
          !String.valueOf (ed.getDT_Emissao ()).equals ("") &&
          !String.valueOf (ed.getDT_Emissao ()).equals ("null")) {
        sql += " and Conhecimentos.DT_Emissao = '" + ed.getDT_Emissao () + "'";
      }

      if (String.valueOf (ed.getDT_Emissao_Inicial ()) != null &&
          !String.valueOf (ed.getDT_Emissao_Inicial ()).equals ("") &&
          !String.valueOf (ed.getDT_Emissao_Inicial ()).equals ("null")) {
        sql += " and Coletas.DT_Coleta >= '" + ed.getDT_Emissao_Inicial () + "'";
      }

      if (String.valueOf (ed.getDT_Emissao_Final ()) != null &&
          !String.valueOf (ed.getDT_Emissao_Final ()).equals ("") &&
          !String.valueOf (ed.getDT_Emissao_Final ()).equals ("null")) {
        sql += " and Coletas.DT_Coleta <= '" + ed.getDT_Emissao_Final () + "'";
      }

      sql +=
          " Order by Viagens.Dt_Previsao_Chegada, Viagens.Hr_Previsao_Chegada ";

      // .println (sql);

      ResultSet res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        list.add (carregaED (res));
      }
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

  public ArrayList Lista_Coleta (ColetaED ed) throws Excecoes {

    ArrayList list = new ArrayList ();
    try {
      ResultSet res = this.executasql.executarConsulta (montaSql (ed));
      while (res.next ()) {
        // .println (res.getString ("oid_coleta"));

        list.add (carregaED (res));

      }
    }
    catch (SQLException exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
                          "getByRecord()");
    }
    return list;
  }

  public byte[] Imprime_Coleta (ColetaED ed) throws Excecoes {

    long nr_Coleta = 0;
    if ("E".equals (parametro_FixoED.getDM_Numera_Coleta ())) {
      nr_Coleta = numeraColeta (ed);
    }

    try {

    	String sql = "UPDATE Coletas " +
    	" SET  DM_Impresso='S'" +
    	" WHERE OID_Coleta ='" + ed.getOID_Coleta () + "'";
    	executasql.executarUpdate (sql);


    	situacao_Veiculo (ed);

    	sql = " SELECT Coletas.*, " +
	        "        Pessoa_Unidade.NM_Fantasia, " +
	        "        Pessoas.NM_Razao_Social, " +
	        //"        Pessoa_Destinatario.NM_Razao_Social, " +
	        "        Cidades.NM_Cidade, " +
	        "        Modal.NM_Modal, " +
	        "        Cidades.CD_Cidade, " +
	        "        Modal.CD_Modal, " +
	        "        Unidades.CD_Unidade " +
	        " FROM Coletas, Unidades, Pessoas, Modal, Cidades, Pessoas Pessoa_Unidade ";
	    //" , Pessoas Pessoa_Destinatario ";
	    sql += " WHERE Coletas.OID_Unidade = Unidades.OID_Unidade AND Coletas.OID_Modal = Modal.OID_Modal AND Coletas.OID_Pessoa = Pessoas.OID_Pessoa  " +
	        //" AND Coletas.OID_Pessoa_Destinatario = Pessoa_Destinatario.OID_Pessoa " +
	        " AND Coletas.OID_Cidade = Cidades.OID_Cidade " +
	        " AND Pessoa_Unidade.OID_Pessoa = Unidades.OID_Pessoa ";

	    if (JavaUtil.doValida (ed.getOID_Coleta ())) {
	      sql += " and Coletas.OID_Coleta = " + ed.getOID_Coleta ();
	    }
	    // .println (sql);
	    ColetaED edVolta = new ColetaED ();

	    ResultSet res = null;

	    res = this.executasql.executarConsulta (sql);
	    return new ColetaRL ().Imprime_Coleta (res);
    }

    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao Imprimir Coleta");
      excecoes.setMetodo ("Imprime_Coleta()");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }


  public String Imprime_Coleta_Matricial (ColetaED ed) throws Excecoes {

	    // .println ("Imprime_Coleta_Matricial BD");

	    long nr_Coleta = 0;
	    if ("E".equals (parametro_FixoED.getDM_Numera_Coleta ())) {
	      nr_Coleta = numeraColeta (ed);
	    }
	    try {


	    String sql = "UPDATE Coletas " +
    	" SET  DM_Impresso='S'" +
    	" WHERE OID_Coleta ='" + ed.getOID_Coleta () + "'";
    	executasql.executarUpdate (sql);

	    situacao_Veiculo (ed);

	    sql =
	        " SELECT Coletas.*, " +
	        "        Pessoa_Unidade.NM_Fantasia, " +
	        "        Pessoas.NM_Razao_Social, " +
	        //"        Pessoa_Destinatario.NM_Razao_Social, " +
	        "        Cidades.NM_Cidade, " +
	        "        Modal.NM_Modal, " +
	        "        Cidades.CD_Cidade, " +
	        "        Modal.CD_Modal, " +
	        "        Unidades.CD_Unidade " +
	        " FROM Coletas, Unidades, Pessoas, Modal, Cidades, Pessoas Pessoa_Unidade ";
	    //" , Pessoas Pessoa_Destinatario ";
	    sql += " WHERE Coletas.OID_Unidade = Unidades.OID_Unidade AND Coletas.OID_Modal = Modal.OID_Modal AND Coletas.OID_Pessoa = Pessoas.OID_Pessoa  " +
	        //" AND Coletas.OID_Pessoa_Destinatario = Pessoa_Destinatario.OID_Pessoa " +
	        " AND Coletas.OID_Cidade = Cidades.OID_Cidade " +
	        " AND Pessoa_Unidade.OID_Pessoa = Unidades.OID_Pessoa ";

	    if (JavaUtil.doValida (ed.getOID_Coleta ())) {
	      sql += " and Coletas.OID_Coleta = " + ed.getOID_Coleta ();
	    }
	    // .println (sql);
	    ResultSet res = this.executasql.executarConsulta (sql);
	    return new ColetaRL ().Imprime_Coleta_Matricial(res);
	    }

	    catch (Excecoes exc) {
	      throw exc;
	    }
	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      excecoes.setMensagem ("Erro ao Imprimir Coleta");
	      excecoes.setMetodo ("Imprime_Coleta()");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }
	  }

  public byte[] Relatorio_Coleta_Notas_Fiscais (ColetaED ed) throws Excecoes {

    String sql = null;
    byte[] b = null;

    sql = " SELECT Coletas.OID_Coleta, Coletas.OID_Unidade, Coletas.OID_Modal, Coletas.OID_Pessoa, Coletas.OID_Pessoa_Destinatario, Coletas.OID_Cidade, NR_Coleta, NM_Contato, NM_Solicitante, NM_Ramal_Contato, NM_Ramal_Solicitante, DT_Coleta, HR_Coleta, DT_Coletado, DM_Finalizado, HR_Coletado, DM_Intervalo_Almoco, DM_Tipo_Veiculo, DM_Munck, DM_KIT, QT_Volumes, NR_Peso, NR_Peso_Cubado,  VL_Mercadoria, TX_Observacao, NM_Endereco_Coleta, NM_Bairro_Coleta, Coletas.Dt_Stamp, Coletas.Usuario_Stamp, Coletas.Dm_Stamp, Pessoa_Unidade.NM_Fantasia, Pessoas.NM_Razao_Social, Pessoa_Destinatario.NM_Razao_Social, Cidades.NM_Cidade, Modal.NM_Modal , Cidades.CD_Cidade, Modal.CD_Modal, Unidades.CD_Unidade, NM_Destinatario, NM_Cidade_Destinatario, NM_Documento, VL_Custo, OID_Veiculo, DM_Tipo_Conhecimento, VL_Total_Frete, Coletas.DM_Tipo ";
    sql += " FROM Coletas, Unidades, Pessoas, Modal, Cidades, Pessoas Pessoa_Unidade, Pessoas Pessoa_Destinatario ";
    sql += " WHERE Coletas.OID_Unidade = Unidades.OID_Unidade AND Coletas.OID_Modal = Modal.OID_Modal AND Coletas.OID_Pessoa = Pessoas.OID_Pessoa  AND Coletas.OID_Pessoa_Destinatario = Pessoa_Destinatario.OID_Pessoa AND Coletas.OID_Cidade = Cidades.OID_Cidade AND Pessoa_Unidade.OID_Pessoa = Unidades.OID_Pessoa ";

    if (String.valueOf (ed.getOID_Coleta ()) != null &&
        !String.valueOf (ed.getOID_Coleta ()).equals ("0") &&
        !String.valueOf (ed.getOID_Coleta ()).equals ("null")) {
      sql += " and Coletas.OID_Coleta = " + ed.getOID_Coleta ();
    }

    //// .println(sql);


    ColetaED edVolta = new ColetaED ();

    try {

      ResultSet res = null;

      res = this.executasql.executarConsulta (sql.toString ());

      ColetaRL conRL = new ColetaRL ();
      b = conRL.Relatorio_Coleta_Notas_Fiscais (res);

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

  public byte[] Relatorio_Coleta (ColetaED ed) throws Excecoes {

    String sql = null;
    byte[] b = null;

    sql = " SELECT Coletas.*, Coletas.DT_Previsao_Entrega, Coletas.OID_Coleta, Coletas.OID_Unidade, Coletas.OID_Modal, Coletas.OID_Pessoa, Coletas.DM_Situacao, Coletas.OID_Pessoa_Destinatario, Coletas.OID_Cidade, NR_Coleta, NM_Contato, NM_Solicitante, NM_Ramal_Contato, NR_Ramal_Solicitante, DT_Coleta, HR_Coleta, DT_Coletado, DM_Finalizado, HR_Coletado, DM_Intervalo_Almoco, DM_Tipo_Veiculo, DM_Munck, DM_KIT, QT_Volumes, NR_Peso, NR_Peso_Cubado, NR_Peso2, NR_Peso3, NR_Peso4, VL_Mercadoria, TX_Observacao, NM_Endereco_Coleta, NM_Bairro_Coleta, Coletas.Dt_Stamp, Coletas.Usuario_Stamp, Coletas.Dm_Stamp, Pessoa_Unidade.NM_Fantasia, Pessoas.NM_Razao_Social, Pessoa_Destinatario.NM_Razao_Social, Cidades.NM_Cidade, Modal.NM_Modal , Cidades.CD_Cidade, Modal.CD_Modal, Unidades.CD_Unidade, NM_Destinatario, NM_Cidade_Destinatario, NM_Documento, VL_Custo, OID_Veiculo, DM_Tipo_Conhecimento, VL_Total_Frete, Coletas.NM_Destinatario, Coletas.NM_Cidade_Destinatario , Coletas.NM_Destinatario2, Coletas.NM_Cidade_Destinatario2 , Coletas.NM_Destinatario3, Coletas.NM_Cidade_Destinatario3 , Coletas.NM_Destinatario4, Coletas.NM_Cidade_Destinatario4, Coletas.oid_Carreta, Coletas.oid_Motorista "
        + " FROM Coletas, Unidades, Pessoas, Modal, Cidades, Pessoas Pessoa_Unidade, Pessoas Pessoa_Destinatario " +
        " WHERE Coletas.OID_Unidade = Unidades.OID_Unidade " +
        " AND   Coletas.OID_Modal = Modal.OID_Modal " +
        " AND Coletas.OID_Pessoa = Pessoas.OID_Pessoa  " +
        " AND Coletas.OID_Pessoa_Destinatario = Pessoa_Destinatario.OID_Pessoa " +
        " AND Coletas.OID_Cidade = Cidades.OID_Cidade " +
        " AND Pessoa_Unidade.OID_Pessoa = Unidades.OID_Pessoa ";

    if (String.valueOf (ed.getOID_Unidade ()) != null &&
        !String.valueOf (ed.getOID_Unidade ()).equals ("0") &&
        !String.valueOf (ed.getOID_Unidade ()).equals ("null")) {
      sql += " and Coletas.OID_Unidade = " + ed.getOID_Unidade ();
    }
    if (String.valueOf (ed.getOID_Pessoa ()) != null &&
        !String.valueOf (ed.getOID_Pessoa ()).equals ("") &&
        !String.valueOf (ed.getOID_Pessoa ()).equals ("null")) {
      sql += " and Coletas.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
    }
    if (String.valueOf (ed.getOID_Motorista ()) != null &&
        !String.valueOf (ed.getOID_Motorista ()).equals ("") &&
        !String.valueOf (ed.getOID_Motorista ()).equals ("null")) {
      sql += " and Coletas.OID_Motorista = '" + ed.getOID_Motorista () + "'";
    }

    if (String.valueOf (ed.getDT_Emissao ()) != null &&
        !String.valueOf (ed.getDT_Emissao ()).equals ("") &&
        !String.valueOf (ed.getDT_Emissao ()).equals ("null")) {
      sql += " and Conhecimentos.DT_Emissao = '" + ed.getDT_Emissao () + "'";
    }

    if (String.valueOf (ed.getDT_Emissao_Inicial ()) != null &&
        !String.valueOf (ed.getDT_Emissao_Inicial ()).equals ("") &&
        !String.valueOf (ed.getDT_Emissao_Inicial ()).equals ("null")) {
      sql += " and Coletas.DT_Coleta >= '" + ed.getDT_Emissao_Inicial () + "'";
    }

    if (String.valueOf (ed.getDT_Emissao_Final ()) != null &&
        !String.valueOf (ed.getDT_Emissao_Final ()).equals ("") &&
        !String.valueOf (ed.getDT_Emissao_Final ()).equals ("null")) {
      sql += " and Coletas.DT_Coleta <= '" + ed.getDT_Emissao_Final () + "'";
    }
    if (String.valueOf (ed.getNR_Placa ()) != null &&
        !String.valueOf (ed.getNR_Placa ()).equals ("0") &&
        !String.valueOf (ed.getNR_Placa ()).equals ("null")) {
      sql += " and Coletas.oid_Veiculo = '" + ed.getNR_Placa () + "'";
    }

    if (JavaUtil.doValida (ed.getDM_Situacao ()) &&
        !String.valueOf (ed.getDM_Situacao ()).equals ("T")) {
      sql += " and Coletas.DM_Situacao = '" + ed.getDM_Situacao () + "'";
    }

    if (JavaUtil.doValida (ed.getDT_Programada_Inicial ())) {
      sql += " and Coletas.DT_Programada >= '" + ed.getDT_Programada_Inicial () + "'";
    }
    if (JavaUtil.doValida (ed.getDT_Programada_Final ())) {
      sql += " and Coletas.DT_Programada <= '" + ed.getDT_Programada_Final () + "'";
    }

    if ("M1".equals (ed.getDM_Relatorio ())) {
      sql += " order By Coletas.DT_Coleta, Coletas.nr_coleta ";
    }
    if ("M2".equals (ed.getDM_Relatorio ())) {
      sql += " AND Coletas.DM_Situacao <> 'C' order By Coletas.oid_Veiculo ";
    }

    // .out.println (sql);

    // .out.println (sql);

    ColetaED edVolta = new ColetaED ();

    try {

      ResultSet res = null;

      res = this.executasql.executarConsulta (sql.toString ());

      ColetaRL conRL = new ColetaRL ();
      b = conRL.Relatorio_Coleta (res , ed);

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

  public void altera_Veiculo (ColetaED ed) throws Excecoes {

    String sql = null;

    try {

//Verifica se veiculo tem algum embarque sem data de chegada
//Somente se nao for Meio proprio e III000
      if (!ed.getOID_Veiculo ().equals ("M.PROPRIO") && !ed.getOID_Veiculo ().equals ("III0000")) {
        sql = "select dt_chegada, nr_embarque from Embarques where oid_veiculo = '" + ed.getOID_Veiculo () + "'";
        ResultSet rsVeiculo = executasql.executarConsulta (sql);

        while (rsVeiculo.next ()) {
          if (!JavaUtil.doValida (rsVeiculo.getString ("dt_chegada"))) {
            throw new Excecoes ("Este Ve�culo n�o est� dispon�vel.\nVeiculo ainda possui Embarque (nr. " + rsVeiculo.getString ("nr_embarque") + ") sem data de Chegada." , getClass ().getName () , "inclui(EmbarqueED ed)");
          }
        }
      }
      sql = "update Coletas " +
          "set oid_Veiculo = '" + ed.getOID_Veiculo () +
          "', dm_situacao ='2'" +
          "Where OID_Coleta ='" + ed.getOID_Coleta () + "'";
      // .println (sql);
      int i = executasql.executarUpdate (sql);

    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar");
      excecoes.setMetodo ("altera_VeiculoxColeta()");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public ColetaED confirma_Reprova_Cotacao (ColetaED ed) throws Excecoes {

    String sql = null;
    // .println (ed.getAcao());
    ColetaED edVolta = new ColetaED ();
    ColetaED edCol = this.getByRecord (ed);

    try {
      if ("A".equals (ed.getAcao())) {
        sql = " SELECT Movimentos_Cotacoes.oid_Modal, Movimentos_Cotacoes.oid_Cotacao, Movimentos_Cotacoes.vl_total_Frete " +
            " FROM   Cotacoes, Movimentos_Cotacoes " +
            " WHERE  Movimentos_Cotacoes.oid_Cotacao  = Cotacoes.oid_Cotacao " +
            " AND    Movimentos_Cotacoes.vl_total_Frete  = " + ed.getVL_Total_Frete () +
            " AND    Cotacoes.oid_Coleta ='" + ed.getOID_Coleta () + "'";
        // .println (sql);

        ResultSet res = this.executasql.executarConsulta (sql);
        if (res.next ()) {
          sql = " UPDATE Coletas " +
              " SET oid_Modal = " + res.getLong ("oid_Modal") +
              ",DM_Situacao_Cotacao ='A' " +
              ",VL_Cotacao =" + ed.getVL_Total_Frete () +
              " WHERE OID_Coleta ='" + ed.getOID_Coleta () + "'";
          // .println (sql);
          int i = executasql.executarUpdate (sql);
        }

        new Pessoa_EMailBD (this.executasql).envia_eMail (edCol.getOID_Pessoa_Usuario () , "APFAPROV" , "" , " Aprovada Solicitacao NR:" + edCol.getOID_Coleta () + " Coleta:" + edCol.getNR_Coleta () , "" , "");

      }
      if ("C".equals (ed.getAcao())) {
        sql = " UPDATE Coletas " +
            " SET DM_Situacao_Cotacao ='C' " +
            ",TX_Observacao = '"+ ed.getTX_Motivo_Perda()+"'"+
            ",VL_Cotacao = 0 "+
            " WHERE OID_Coleta ='" + ed.getOID_Coleta () + "'";
        // .println (sql);
        int i = executasql.executarUpdate (sql);
        new Pessoa_EMailBD (this.executasql).envia_eMail (edCol.getOID_Pessoa_Usuario () , "APFCANC" , "" , " Cancelada Solicitacao NR:" + edCol.getOID_Coleta () + " Coleta:" + edCol.getNR_Coleta () +" Motivo:" + ed.getTX_Motivo_Perda() , "" , "");

      }

      if ("R".equals (ed.getAcao())) {
        sql = " UPDATE Coletas " +
            " SET DM_Situacao_Cotacao ='R' " +
            " WHERE OID_Coleta ='" + ed.getOID_Coleta () + "'";
        // .println (sql);
        int i = executasql.executarUpdate (sql);

        new Pessoa_EMailBD (this.executasql).envia_eMail (edCol.getOID_Pessoa_Usuario () , "APFRC" , "" , " Revisar Solicitacao NR:" + edCol.getOID_Coleta () + " Coleta:" + edCol.getNR_Coleta () , "" , "");

      }

      if ("X".equals (ed.getAcao())) {
        edVolta = this.getByRecord (ed);
        edVolta.setTX_Observacao("Nova Operacao:"+edVolta.getOID_Coleta());
        edVolta.setDT_Coleta (Data.getDataDMY ());
        edVolta.setDT_Previsao_Entrega (Data.getDataDMY ());
        edVolta.setHR_Coleta (Data.getHoraHM ());
        edVolta.setDM_Tipo_Coleta ("X");
        edVolta = this.inclui (edVolta);
        sql = " UPDATE Coletas " +
            " SET DM_Situacao_Cotacao ='X' " +
            ",TX_Observacao = 'Substuida p/:"+ edVolta.getOID_Coleta()+"'"+
            " WHERE OID_Coleta ='" + ed.getOID_Coleta () + "'";
        // .println (sql);
        int i = executasql.executarUpdate (sql);

        new Pessoa_EMailBD (this.executasql).envia_eMail (edCol.getOID_Pessoa_Usuario () , "APFSUB" , "" , " Substituida Solicitacao NR:" + edCol.getOID_Coleta () + " Coleta:" + edCol.getNR_Coleta ()+ " pela:"+edVolta.getOID_Coleta() , "" , "");

      }
    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao confirma_Reprova_Cotacao");
      excecoes.setMetodo ("confirma_Reprova_Cotacao()");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return edVolta;
  }

  public void altera (ColetaED ed) throws Excecoes {

    try {
      String sql = " UPDATE Coletas  SET  DT_Stamp = '" + Data.getDataDMY () + "'";

      if (ed.getOID_Modal () > 0) {
        sql += " ,OID_Modal = " + ed.getOID_Modal ();
      }
      if (ed.getOID_Produto () > 0) {
          sql += " ,OID_Produto = " + ed.getOID_Produto ();
        }
      if (ed.getOID_Cidade () > 0) {
        sql += " ,OID_Cidade = " + ed.getOID_Cidade ();
      }
      if (util.doValida (ed.getNM_Pessoa_Contato ())) {
          sql += " ,NM_Contato = '" + ed.getNM_Pessoa_Contato () + "'";
        }
      if (util.doValida (ed.getNM_Pessoa_Solicitante ())) {
        sql += " ,NM_Solicitante = '" + ed.getNM_Pessoa_Solicitante () + "'";
      }
      if (util.doValida (ed.getDM_Intervalo_Almoco ())) {
        sql += " ,DM_Intervalo_Almoco = '" + ed.getDM_Intervalo_Almoco () + "'";
      }
      if (util.doValida (ed.getDM_Tipo_Veiculo ())) {
        sql += " ,DM_Tipo_Veiculo = '" + ed.getDM_Tipo_Veiculo () + "'";
      }
      if (util.doValida (ed.getDM_Munck ())) {
        sql += " ,DM_Munk = '" + ed.getDM_Munck () + "'";
      }
      if (util.doValida (ed.getNR_Pedido ())) {
        sql += " ,NR_Pedido = '" + ed.getNR_Pedido () + "'";
      }
      if (util.doValida (ed.getDM_Situacao ())) {
        sql += " ,DM_Situacao = '" + ed.getDM_Situacao () + "'";
      }
      if (util.doValida (ed.getDM_KIT ())) {
        sql += " ,DM_KIT = '" + ed.getDM_KIT () + "'";
      }
      if (ed.getNR_Volumes () > 0) {
        sql += " ,QT_Volumes = " + ed.getNR_Volumes ();
      }
      if (util.doValida (ed.getTX_Observacao ())) {
        sql += " ,TX_Observacao = '" + ed.getTX_Observacao () + "'";
      }
      if (util.doValida (ed.getNM_Endereco_Coleta ())) {
        sql += " ,NM_Endereco_Coleta = '" + ed.getNM_Endereco_Coleta () + "'";
      }
      if (util.doValida (ed.getNM_Bairro_Coleta ())) {
        sql += " ,NM_Bairro_Coleta = '" + ed.getNM_Bairro_Coleta () + "'";
      }
      if (util.doValida (ed.getDT_Coleta ())) {
        sql += " ,DT_Coleta = '" + ed.getDT_Coleta () + "'";
      }
      if (util.doValida (ed.getHR_Coleta ())) {
        sql += " ,HR_Coleta = '" + ed.getHR_Coleta () + "'";
      }
      if (util.doValida (ed.getHR_Coleta_Minima ())) {
        sql += " ,HR_Coleta_Minima = '" + ed.getHR_Coleta_Minima () + "'";
      }
      if (util.doValida (ed.getHR_Coleta_Maxima ())) {
        sql += " ,HR_Coleta_Maxima = '" + ed.getHR_Coleta_Maxima () + "'";
      }
      if (util.doValida (ed.getOID_Pessoa ())) {
          sql += " ,OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
        }
      if (util.doValida (ed.getOID_Pessoa_Destinatario ())) {
        sql += " ,OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario () + "'";
      }
      if (ed.getOID_Cidade_Destinatario () > 0) {
        sql += " ,OID_Cidade_Destinatario = " + ed.getOID_Cidade_Destinatario ();
      }

      if (ed.getVL_Custo () >= 0) {
        sql += " ,VL_Custo = " + ed.getVL_Custo ();
      }
      if (ed.getVL_Total_Frete () >= 0) {
        sql += " ,VL_Total_Frete = " + ed.getVL_Total_Frete ();
      }

      if (util.doValida (ed.getOID_Veiculo ())) {
        sql += " ,OID_Veiculo = '" + ed.getOID_Veiculo () + "'";
      }
      if (util.doValida (ed.getOID_Carreta ())) {
        sql += " ,OID_Carreta = '" + ed.getOID_Carreta () + "'";
      }
      if (util.doValida (ed.getOID_Carreta2 ())) {
          sql += " ,OID_Carreta2 = '" + ed.getOID_Carreta2 () + "'";
        }
      if (util.doValida (ed.getDM_Tipo_Conhecimento ())) {
        sql += " ,DM_Tipo_Conhecimento = '" + ed.getDM_Tipo_Conhecimento () + "'";
      }
      if (util.doValida (ed.getDT_Previsao_Entrega ())) {
        sql += " ,DT_Previsao_Entrega = '" + ed.getDT_Previsao_Entrega () + "'";
      }
      if (util.doValida (ed.getDT_Previsao_Entrega ())) {
        sql += " ,HR_Previsao_Entrega = '" + ed.getHR_Previsao_Entrega () + "'";
      }
      if (util.doValida (ed.getNR_Telefone ())) {
        sql += " ,NR_Telefone = '" + ed.getNR_Telefone () + "'";
      }
      if (util.doValida (ed.getNR_Cartao ())) {
        sql += " ,NR_Cartao = '" + ed.getNR_Cartao () + "'";
      }
      if (util.doValida (ed.getNR_Liberacao ())) {
        sql += " ,NR_Liberacao = '" + ed.getNR_Liberacao () + "'";
      }
      if (util.doValida (ed.getNM_Rota ())) {
        sql += " ,NM_Rota = '" + ed.getNM_Rota () + "'";
      }
      if (util.doValida (ed.getDM_Tipo ())) {
        sql += " ,DM_Tipo = '" + ed.getDM_Tipo () + "'";
      }
      if (util.doValida (ed.getOID_Motorista ())) {
        sql += " ,oid_Motorista = '" + ed.getOID_Motorista () + "'";
      }

      //1
      if (util.doValida (ed.getNM_Pessoa_Destinatario ())) {
        sql += " ,NM_Destinatario = '" + ed.getNM_Pessoa_Destinatario () + "'";
      }
      if (util.doValida (ed.getNM_Cidade_Destinatario ())) {
        sql += " ,NM_Cidade_Destinatario = '" + ed.getNM_Cidade_Destinatario () + "'";
      }
      if (util.doValida (ed.getNM_Documento ())) {
        sql += " ,NM_Documento = '" + ed.getNM_Documento () + "'";
      }
      if (ed.getNR_Peso () >= 0) {
        sql += " ,NR_Peso = " + ed.getNR_Peso ();
      }
      if (ed.getNR_Peso_Cubado () >= 0) {
        sql += " ,NR_Peso_Cubado = " + ed.getNR_Peso_Cubado ();
      }
      if (util.doValida (ed.getNM_Especie ())) {
        sql += " ,NM_Especie = '" + ed.getNM_Especie () + "'";
      }
      if (util.doValida (ed.getDT_Coleta_Destino ())) {
        sql += " ,DT_Coleta_Destino = '" + ed.getDT_Coleta_Destino () + "'";
      }
      if (ed.getNR_Volumes () >= 0) {
        sql += " ,NR_Volumes = " + ed.getNR_Volumes ();
      }
      if (ed.getNR_Cubagem () >= 0) {
        sql += " ,NR_Cubagem = " + ed.getNR_Cubagem ();
      }
      if (ed.getVL_Mercadoria () >= 0) {
        sql += " ,VL_Mercadoria = " + ed.getVL_Mercadoria ();
      }
      if (ed.getNR_Conhecimento () > 0) {
        sql += " ,NR_Conhecimento = '" + ed.getNR_Conhecimento () + "'";
      }
      //2
      if (util.doValida (ed.getNM_Pessoa_Destinatario2 ())) {
        sql += " ,NM_Destinatario2 = '" + ed.getNM_Pessoa_Destinatario2 () + "'";
      }
      if (util.doValida (ed.getNM_Cidade_Destinatario2 ())) {
        sql += " ,NM_Cidade_Destinatario2 = '" + ed.getNM_Cidade_Destinatario2 () + "'";
      }
      if (util.doValida (ed.getNM_Documento2 ())) {
        sql += " ,NM_Documento2 = '" + ed.getNM_Documento2 () + "'";
      }
      if (util.doValida (ed.getDM_Tipo_Coleta ())) {
          sql += " ,DM_Tipo_Coleta = '" + ed.getDM_Tipo_Coleta () + "'";
        }
      if (ed.getNR_Peso2 () >= 0) {
        sql += " ,NR_Peso2 = " + ed.getNR_Peso2 ();
      }
      if (util.doValida (ed.getNM_Especie2 ())) {
        sql += " ,NM_Especie2 = '" + ed.getNM_Especie2 () + "'";
      }
      if (util.doValida (ed.getDT_Coleta_Destino2 ())) {
        sql += " ,DT_Coleta_Destino2 = '" + ed.getDT_Coleta_Destino2 () + "'";
      }
      if (ed.getNR_Volumes2 () >= 0) {
        sql += " ,NR_Volumes2 = " + ed.getNR_Volumes2 ();
      }
      if (ed.getNR_Conhecimento2 () > 0) {
        sql += " ,NR_Conhecimento = '" + ed.getNR_Conhecimento2 () + "'";
      }
      //3
      if (util.doValida (ed.getNM_Pessoa_Destinatario3 ())) {
        sql += " ,NM_Destinatario3 = '" + ed.getNM_Pessoa_Destinatario3 () + "'";
      }
      if (util.doValida (ed.getNM_Cidade_Destinatario3 ())) {
        sql += " ,NM_Cidade_Destinatario3 = '" + ed.getNM_Cidade_Destinatario3 () + "'";
      }
      if (util.doValida (ed.getNM_Documento3 ())) {
        sql += " ,NM_Documento3 = '" + ed.getNM_Documento3 () + "'";
      }
      if (ed.getNR_Peso3 () >= 0) {
        sql += " ,NR_Peso3 = " + ed.getNR_Peso3 ();
      }
      if (util.doValida (ed.getNM_Especie3 ())) {
        sql += " ,NM_Especie3 = '" + ed.getNM_Especie3 () + "'";
      }
      if (util.doValida (ed.getDT_Coleta_Destino3 ())) {
        sql += " ,DT_Coleta_Destino3 = '" + ed.getDT_Coleta_Destino3 () + "'";
      }
      if (ed.getNR_Volumes3 () >= 0) {
        sql += " ,NR_Volumes3 = " + ed.getNR_Volumes3 ();
      }
      if (ed.getNR_Conhecimento3 () > 0) {
        sql += " ,NR_Conhecimento = '" + ed.getNR_Conhecimento3 () + "'";
      }
      //4
      if (util.doValida (ed.getNM_Pessoa_Destinatario4 ())) {
        sql += " ,NM_Destinatario4 = '" + ed.getNM_Pessoa_Destinatario4 () + "'";
      }
      if (util.doValida (ed.getNM_Cidade_Destinatario4 ())) {
        sql += " ,NM_Cidade_Destinatario4 = '" + ed.getNM_Cidade_Destinatario4 () + "'";
      }
      if (util.doValida (ed.getNM_Documento4 ())) {
        sql += " ,NM_Documento4 = '" + ed.getNM_Documento4 () + "'";
      }
      if (ed.getNR_Peso4 () >= 0) {
        sql += " ,NR_Peso4 = " + ed.getNR_Peso4 ();
      }
      if (util.doValida (ed.getNM_Especie4 ())) {
        sql += " ,NM_Especie4 = '" + ed.getNM_Especie4 () + "'";
      }
      if (util.doValida (ed.getDT_Coleta_Destino4 ())) {
        sql += " ,DT_Coleta_Destino4 = '" + ed.getDT_Coleta_Destino4 () + "'";
      }
      if (ed.getNR_Volumes4 () >= 0) {
        sql += " ,NR_Volumes4 = " + ed.getNR_Volumes4 ();
      }
      if (ed.getNR_Conhecimento4 () > 0) {
        sql += " ,NR_Conhecimento = '" + ed.getNR_Conhecimento4 () + "'";
      }
      if (ed.getNR_Ramal_Contato () > 0) {
          sql += " ,NR_Ramal_Contato = " + ed.getNR_Ramal_Contato();
      }
      if (ed.getNR_Ramal_Solicitante () > 0) {
          sql += " ,NR_Ramal_Solicitante = " + ed.getNR_Ramal_Solicitante();
      }

      sql += " WHERE  OID_Coleta ='" + ed.getOID_Coleta () + "'";

      // .println (sql);

      int i = executasql.executarUpdate (sql);


      ColetaED edCol = this.getByRecord (ed);
      if ("X".equals (edCol.getDM_Tipo_Coleta ())) {
        new Pessoa_EMailBD (this.executasql).envia_eMail (ed.getOID_Pessoa_Usuario() , "APFALT" , "" , "Foi alterado a Solicitacao NR:" + edCol.getOID_Coleta () + " Coleta:"+ edCol.getNR_Coleta (), "" , "");
      }


    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar");
      excecoes.setMetodo ("altera_VeiculoxColeta()");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }


  public void finaliza_coleta(ColetaED ed) throws Excecoes{

	    String sql = null;

	    try{

	      if (String.valueOf(ed.getOID_Coleta()) != null &&
	          !String.valueOf(ed.getOID_Coleta()).equals("") &&
	          !String.valueOf(ed.getOID_Coleta()).equals("null")){

	    	  
		      sql = " UPDATE Coletas " +
		      	    " SET  DT_Coletado= '" + ed.getDT_Coletado() + "', " +
		      	    "      HR_Coletado= '" + ed.getHR_Coletado() + "', "+
	      	    	"      DM_Finalizado= 'S' ";		      
		     sql += " WHERE oid_Coleta = '" + ed.getOID_Coleta() + "'";

		      executasql.executarUpdate(sql);
		      
	      }else{
	          throw new Excecoes("Coleta n�o informado para altera��o !!!");
	      }

	    }

	    catch(Exception exc){
	      Excecoes excecoes = new Excecoes();
	      excecoes.setClasse(this.getClass().getName());
          excecoes.setMensagem("Erro ao Finalizar Coleta!");
	      excecoes.setMetodo("alterar");
	      excecoes.setExc(exc);
	      throw excecoes;
	    }
	  }
  
  public void vincula_CTRC (ColetaED ed) throws Excecoes {

    String sql = null;

    try {
      sql = "update Coletas " +
          "set dm_situacao ='3'" +
          "Where OID_Coleta ='" + ed.getOID_Coleta () + "'";
      // .println (sql);

      int i = executasql.executarUpdate (sql);
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar");
      excecoes.setMetodo ("vincula_CTRC()");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  private long numeraColeta (ColetaED ed) throws Excecoes {

    // .out.println (" numeraColeta 1");

    String sql = null;
    long valOid = 0;
    long NR_Coleta = 0;
    long NR_Proximo = 0;
    long NR_Final = 0;
    String OID = ed.getOID_Coleta();

    ResultSet rs = null;

    try {

    	
      sql = " SELECT Coletas.NR_Coleta, Coletas.oid_Unidade " +
            " FROM   Coletas " +
            " WHERE  Coletas.oid_Coleta = '" + ed.getOID_Coleta () + "'";

      rs = this.executasql.executarConsulta (sql);
      if (rs.next ()) {

        NR_Coleta = rs.getLong ("NR_Coleta");
        if (NR_Coleta == 0) {
          sql = " SELECT AIDOF.NR_Proximo, AIDOF.OID_AIDOF, AIDOF.NR_FINAL ";
          sql += " FROM Parametros_Filiais, AIDOF ";
          sql += " WHERE Parametros_Filiais.OID_AIDOF_Coleta = AIDOF.OID_AIDOF ";
          sql += " AND Parametros_Filiais.OID_Unidade = " + rs.getLong ("oid_Unidade");

          rs = this.executasql.executarConsulta (sql);

          while (rs.next ()) {
            ed.setNR_Coleta (rs.getString ("NR_Proximo"));
            valOid = rs.getLong ("OID_AIDOF");
            NR_Proximo = rs.getLong ("NR_Proximo") + 1;
            NR_Final = rs.getLong ("NR_FINAL");
          }
          if (NR_Proximo > NR_Final) {
            Excecoes exc = new Excecoes ();
            throw exc;
          }

          sql = " UPDATE AIDOF SET NR_Proximo= " + NR_Proximo;
          sql += " WHERE OID_AIDOF = " + valOid;
          executasql.executarUpdate (sql);

          sql = " UPDATE Coletas SET NR_Coleta= " + NR_Proximo;
          sql += " WHERE OID_Coleta = '" + ed.getOID_Coleta () + "'";
          NR_Coleta = NR_Proximo;
          executasql.executarUpdate (sql);
        }
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro metodo.");
      excecoes.setMetodo ("proximoNumero");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return NR_Coleta;
  }

  private void situacao_Veiculo (ColetaED ed) throws Excecoes {

    // .println (" situacao_Veiculo 1");

    String sql = null;

    ResultSet rs = null;
    ResultSet rs2 = null;

    try {

      if (1==2){
        sql = " SELECT Coletas.OID_Veiculo, Coletas.NR_Coleta " +
            " FROM   Coletas, Veiculos " +
            " WHERE  Coletas.oid_Veiculo = Veiculos.oid_Veiculo " +
            " AND    Coletas.oid_Coleta = '" + ed.getOID_Coleta () + "'";
        // .println (sql);

        rs = this.executasql.executarConsulta (sql);
        if (rs.next ()) {

          // .println (" situacao_Veiculo 2");

        }
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro metodo.");
      excecoes.setMetodo ("situacao_Veiculo");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void remarca (ColetaED ed) throws Excecoes {

    String sql = null;

    try {
      sql = "update Coletas set " +
          " dm_situacao ='4'" +
          "Where OID_Coleta ='" + ed.getOID_Coleta () + "'";
      // .println (sql);

      int i = executasql.executarUpdate (sql);
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar");
      excecoes.setMetodo ("remarca_Coleta()");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void cancela (ColetaED ed) throws Excecoes {

	    String sql = null;

	    try {
	      sql = "UPDATE Coletas SET " +
	          " DM_Situacao ='C' " +
	          "WHERE OID_Coleta ='" + ed.getOID_Coleta () + "'";
	      // .println (sql);

	      int i = executasql.executarUpdate (sql);
	    }

	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      excecoes.setMensagem ("Erro ao cancelar");
	      excecoes.setMetodo ("remarca_Coleta()");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }
	  }

  public void Imprime_ColetaJasper (ColetaED ed , HttpServletResponse resp) throws Excecoes {
    ColetaED edVolta = new ColetaED ();

    if ("E".equals (parametro_FixoED.getDM_Numera_Coleta ())) {
      this.numeraColeta (edVolta);
    }

    ArrayList list = new ArrayList ();
    try {
      ResultSet res = this.executasql.executarConsulta (montaSql (ed));
      while (res.next ()) {
        //edVolta = carregaED(res);
        list.add (carregaED (res));
      }
      new ColetaRL ().Imprime_ColetaJasper (list , ed , resp);
    }
    catch (SQLException exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "getByRecord()");
    }
  }

  
  public void deleta (ColetaED ed) throws Excecoes {

    String sql = null;

    try {

      ColetaED edVolta = this.getByRecord(ed);
      if ("X".equals (edVolta.getDM_Tipo_Coleta ())) { //exclusao coleta
        new Pessoa_EMailBD (this.executasql).envia_eMail (edVolta.getOID_Pessoa_Usuario () , "APFEXC" , "" , "  NR Solicitacao:" + edVolta.getOID_Coleta () + " Coleta:"+ edVolta.getNR_Coleta (), "" , "");
      }

      sql = "delete from Coletas " +
          "Where OID_Coleta ='" + ed.getOID_Coleta () + "'";
      // .println (sql);
      int i = executasql.executarUpdate (sql);
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao excluir");
      excecoes.setMetodo ("deleta(ColetaED ed)");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public ColetaED copia_Coleta (ColetaED ed) throws Excecoes {
    ColetaED edVolta = new ColetaED ();

    try {

      edVolta = this.getByRecord (ed);
      edVolta.setDT_Coleta (Data.getDataDMY ());
      edVolta.setDT_Previsao_Entrega (Data.getDataDMY ());
      edVolta.setHR_Previsao_Entrega ("");
      edVolta.setHR_Coleta (Data.getHoraHM ());
      edVolta.setDM_Tipo_Coleta ("C");
      edVolta = this.inclui (edVolta);

    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao Gerar Copia da Coleta");
      excecoes.setMetodo ("altera_VeiculoxColeta()");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return edVolta;
  }

  private ColetaED populaRegistro (ResultSet res , ColetaED edVolta) throws SQLException {
	    edVolta.setNR_Coleta(res.getString ("NR_Coleta"));
	    edVolta.setCD_Modal(res.getString ("OID_Pessoa"));	    
	    edVolta.setCD_Unidade(res.getString ("CD_Unidade"));
	    edVolta.setNM_Razao_Social_Unidade(res.getString ("NM_Unidade"));	    
	    edVolta.setDT_Emissao(res.getString ("DT_Emissao"));	
	    edVolta.setNR_Peso(res.getDouble("NR_Peso"));
	    edVolta.setOID_Veiculo(res.getString("OID_Veiculo"));
	    edVolta.setDM_Situacao(res.getString("DM_Situacao"));
	    edVolta.setNM_Razao_Social(res.getString("NM_Remetente"));
	    edVolta.setNM_Razao_Social_Destinatario(res.getString("NM_Destinatario_1"));
	    edVolta.setNM_Motorista(res.getString("NM_Motorista"));
	    edVolta.setOID_Carreta(res.getString("OID_Carreta"));	    
	    return edVolta;
	  }
  
}
