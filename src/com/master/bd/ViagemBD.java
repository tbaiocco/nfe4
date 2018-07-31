package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.master.ed.CompromissoED;
import com.master.ed.Ordem_FreteED;
import com.master.ed.ConhecimentoED;
import com.master.ed.Movimento_ConhecimentoED;
import com.master.ed.Ocorrencia_ConhecimentoED;
import com.master.ed.ViagemED;
import com.master.rl.ViagemRL;
import com.master.rn.ConhecimentoRN;
import com.master.rn.Movimento_ConhecimentoRN;
import com.master.rn.Ocorrencia_ConhecimentoRN;
import com.master.root.FormataDataBean;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;
import com.master.util.Utilitaria;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;
import com.master.ed.ManifestoED;

public class ViagemBD {
  private ExecutaSQL executasql;
  Parametro_FixoED edParametro_Fixo = new Parametro_FixoED ();
  protected Movimento_ConhecimentoBD Movimento_ConhecimentoBD = null;

  public ViagemBD (ExecutaSQL sql) {
    this.executasql = sql;
  }

  public void altera (ViagemED ed) throws Excecoes {

    String sql = null;

    try {

      sql = " update Viagens set DT_Previsao_Chegada = '" + ed.getDT_Previsao_Chegada () + "','  HR_Previsao_Chegada = '" + ed.getHR_Previsao_Chegada () + " ',OID_Manifesto= " + ed.getOID_Manifesto () + "'";
      sql += " where oid_Viagem = '" + ed.getOID_Viagem () + "'";

      // //// System.out.println(sql);

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

  public void deleta (ViagemED ed) throws Excecoes {

    String sql = null;
    boolean DM_Lancado_Ordem_Frete = false;

    try {

      if (ed.getHR_Viagem() != null && ed.getNM_Senha() != null && !ed.getHR_Viagem().equals(ed.getNM_Senha())){
          sql = " SELECT Viagens.OID_Manifesto " +
	  		    " FROM  Viagens, Ordens_Manifestos " +
	  		    " WHERE Viagens.oid_Manifesto = Ordens_Manifestos.oid_Manifesto  " +
	  	        " AND   Viagens.OID_Viagem = '" + ed.getOID_Viagem () + "'";

		  ResultSet res = null;
		  res = this.executasql.executarConsulta (sql);
		  if (res.next ()) {
			  DM_Lancado_Ordem_Frete=true;
		      throw new Mensagens ("Manifesto lançado em ordem de frete - exclusão de CTRC não permitida...");
		  }
      }

      sql = "delete from Viagens WHERE oid_Viagem = ";
      sql += "('" + ed.getOID_Viagem () + "')";

      executasql.executarUpdate (sql);

      if (ed.getHR_Viagem() != null && ed.getNM_Senha() != null && !ed.getHR_Viagem().equals(ed.getNM_Senha())){
	      sql = " Update Manifestos SET DM_Lancado_Ordem_Frete='N' WHERE OID_Manifesto ='" + ed.getOID_Manifesto () + "'";
	      executasql.executarUpdate (sql);
      }
      if (edParametro_Fixo.getDM_Gera_Custo_Viagem ().equals ("S")) {

          sql = " SELECT  oid_Conhecimento, oid_Movimento_Conhecimento FROM Movimentos_Conhecimentos WHERE oid_Viagem ='" + ed.getOID_Viagem() + "'";

          ResultSet res2 =this.executasql.executarConsulta (sql);
          while (res2.next ()) {
	          Movimento_ConhecimentoED edMovimento_Conhecimento = new Movimento_ConhecimentoED ();
	          edMovimento_Conhecimento.setOID_Movimento_Conhecimento(res2.getString("oid_Movimento_Conhecimento"));
	          edMovimento_Conhecimento.setOID_Conhecimento(res2.getString("oid_Conhecimento"));
	          Movimento_ConhecimentoBD = new Movimento_ConhecimentoBD (this.executasql);
	          Movimento_ConhecimentoBD.deleta(edMovimento_Conhecimento);
	          Movimento_ConhecimentoBD.calcula_Margem (edMovimento_Conhecimento);
          }
      }
      if (edParametro_Fixo.getDM_Gera_Ocorrencia_Viagem ().equals ("S")) {

        Ocorrencia_ConhecimentoRN Ocorrencia_Conhecimentorn = new Ocorrencia_ConhecimentoRN ();
        Ocorrencia_ConhecimentoED ocorrencia_ConhecimentoED = new Ocorrencia_ConhecimentoED ();

        sql = " SELECT  * from Tipos_Ocorrencias Where DM_Tipo='X' ";

        ResultSet res2 = null;
        res2 = this.executasql.executarConsulta (sql);
        String TX_Observacao = "excluido da viagem";
        while (res2.next ()) {

          ocorrencia_ConhecimentoED.setDT_Ocorrencia_Conhecimento (Data.getDataDMY ());
          ocorrencia_ConhecimentoED.setDT_Emissao (Data.getDataDMY ());
          ocorrencia_ConhecimentoED.setHR_Ocorrencia_Conhecimento (Data.getHoraHM ());
          ocorrencia_ConhecimentoED.setOID_Tipo_Ocorrencia (res2.getLong ("oid_Tipo_Ocorrencia"));
          ocorrencia_ConhecimentoED.setOID_Conhecimento (ed.getOID_Conhecimento ());
          ocorrencia_ConhecimentoED.setTX_Observacao (TX_Observacao);
          ocorrencia_ConhecimentoED.setNM_Pessoa_Entrega ("");
          ocorrencia_ConhecimentoED.setDM_Tipo (res2.getString ("DM_Tipo"));
          ocorrencia_ConhecimentoED.setDM_Tipo (res2.getString ("DM_Acesso"));
          ocorrencia_ConhecimentoED.setDM_Tipo (res2.getString ("DM_Avaria"));
          ocorrencia_ConhecimentoED.setDM_Tipo (res2.getString ("DM_Status"));
          Ocorrencia_Conhecimentorn.inclui (ocorrencia_ConhecimentoED);

        }
      }
      if (ed.getOID_Compromisso () > 0) {
        sql = "delete from Compromissos WHERE oid_Compromisso = ";
        sql += ed.getOID_Compromisso ();

        executasql.executarUpdate (sql);
      }
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      if (DM_Lancado_Ordem_Frete) {
        excecoes.setMensagem ("Manifesto lançado em ordem de frete - exclusão de CTRC não permitida...");
      }
      else {
        excecoes.setMensagem ("Erro ao excluir");
      }
      excecoes.setMetodo ("excluir");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public ArrayList lista (ViagemED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    String DM_Tipo_Manifesto="";
    int registros=0;
    ResultSet res = null;
    FormataDataBean DataFormatada = new FormataDataBean ();



    try {

      if (String.valueOf (ed.getDM_Tipo_Manifesto ()) != null &&
          !String.valueOf (ed.getDM_Tipo_Manifesto ()).equals ("") &&
          !String.valueOf (ed.getDM_Tipo_Manifesto ()).equals ("null")) {

         DM_Tipo_Manifesto = ed.getDM_Tipo_Manifesto();

      }else{

	      sql = " SELECT  Manifestos.DM_Tipo_Manifesto " +
	          " FROM  Manifestos  " +
	          " WHERE Manifestos.OID_Manifesto = '" + ed.getOID_Manifesto () + "'";
	      // System.out.println (sql);
	      res = this.executasql.executarConsulta (sql);
	      if (res.next ()) {
	        DM_Tipo_Manifesto=res.getString("DM_Tipo_Manifesto");
	      }
      }

      if (!"RN".equals(DM_Tipo_Manifesto) && !"RI".equals(DM_Tipo_Manifesto) && !"MS".equals(DM_Tipo_Manifesto)){

        sql = " SELECT Viagens.DM_Tipo_Viagem, Manifestos.DM_Tipo_Manifesto, OID_Viagem, DT_Viagem, HR_Viagem, Viagens.DT_Previsao_Chegada, Viagens.HR_Previsao_Chegada, Conhecimentos.OID_Conhecimento, Conhecimentos.NR_Conhecimento, Conhecimentos.DT_Emissao, Conhecimentos.NR_Peso, Conhecimentos.NR_Volumes, Conhecimentos.VL_Nota_Fiscal, Conhecimentos.VL_Total_Frete, Manifestos.NR_Manifesto, Unidades.CD_Unidade, Pessoa_Remetente.NM_Razao_Social as NM_Razao_Social_Remetente, Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario, Manifestos.OID_Manifesto, Cidades.NM_Cidade " +
              " FROM  Viagens, Conhecimentos, Unidades, Pessoas Pessoa_Remetente, Pessoas Pessoa_Destinatario, Manifestos, Cidades ";
        sql += " WHERE Unidades.oid_Unidade = Conhecimentos.oid_Unidade ";
        sql += " AND Conhecimentos.oid_Pessoa = Pessoa_Remetente.oid_Pessoa ";
        sql += " AND Conhecimentos.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa ";
        sql += " AND Viagens.OID_Conhecimento = Conhecimentos.OID_Conhecimento ";
        sql += " AND Viagens.OID_Manifesto = Manifestos.OID_Manifesto ";
        sql += " AND Conhecimentos.OID_Cidade_Destino = Cidades.OID_Cidade ";

        if (String.valueOf (ed.getNR_Conhecimento ()) != null &&
            !String.valueOf (ed.getNR_Conhecimento ()).equals ("0") &&
            !String.valueOf (ed.getNR_Conhecimento ()).equals ("null")) {
          sql += " and Conhecimentos.NR_Conhecimento = " + ed.getNR_Conhecimento ();
        }
        if (String.valueOf (ed.getOID_Conhecimento ()) != null &&
            !String.valueOf (ed.getOID_Conhecimento ()).equals ("0") &&
            !String.valueOf (ed.getOID_Conhecimento ()).equals ("null")) {
          sql += " and Conhecimentos.oid_Conhecimento = '" + ed.getOID_Conhecimento () + "'";
        }
        if (String.valueOf (ed.getOID_Manifesto ()) != null &&
            !String.valueOf (ed.getOID_Manifesto ()).equals ("0") &&
            !String.valueOf (ed.getOID_Manifesto ()).equals ("null")) {
          sql += " and Manifestos.OID_Manifesto = '" + ed.getOID_Manifesto () + "'";
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
        if (String.valueOf (ed.getDT_Emissao ()) != null &&
            !String.valueOf (ed.getDT_Emissao ()).equals ("") &&
            !String.valueOf (ed.getDT_Emissao ()).equals ("null")) {
          sql += " and Conhecimentos.DT_Emissao = '" + ed.getDT_Emissao () + "'";
        }
        if (String.valueOf (ed.getDT_Viagem ()) != null &&
            !String.valueOf (ed.getDT_Viagem ()).equals ("") &&
            !String.valueOf (ed.getDT_Viagem ()).equals ("null")) {
          sql += " and Viagens.DT_Viagem = '" + ed.getDT_Viagem () + "'";
        }

        sql += " Order by Viagens.Dt_Previsao_Chegada, Viagens.Hr_Previsao_Chegada ";

         // System.out.println (sql);
        res = this.executasql.executarConsulta (sql);


        //popula
        while (res.next ()) {
          ViagemED edVolta = new ViagemED ();
          registros++;
          // System.out.println ("Cto=" + res.getLong ("NR_Conhecimento"));

          edVolta.setOID_Viagem (res.getString ("OID_Viagem"));
          edVolta.setDM_Tipo_Manifesto ("Manifesto");
          if ("R".equals (res.getString ("DM_Tipo_Manifesto"))) {
            edVolta.setDM_Tipo_Manifesto ("Romaneio");
          }
          edVolta.setDM_Tipo_Viagem ("Transf.");
          if ("C".equals (res.getString ("DM_Tipo_Viagem"))) {
            edVolta.setDM_Tipo_Viagem ("Coleta");
          }
          if ("A".equals (res.getString ("DM_Tipo_Viagem"))) {
            edVolta.setDM_Tipo_Viagem ("Col/Transf");
          }
          if ("E".equals (res.getString ("DM_Tipo_Viagem"))) {
            edVolta.setDM_Tipo_Viagem ("Entrega");
          }
          if ("Y".equals (res.getString ("DM_Tipo_Viagem"))) {
            edVolta.setDM_Tipo_Viagem ("Reentrega");
          }
          if ("W".equals (res.getString ("DM_Tipo_Viagem"))) {
            edVolta.setDM_Tipo_Viagem ("Devolucao/Retorno");
          }
          if ("R".equals (res.getString ("DM_Tipo_Viagem"))) {
            edVolta.setDM_Tipo_Viagem ("Transf/Ent.");
          }
          if ("X".equals (res.getString ("DM_Tipo_Viagem"))) {
            edVolta.setDM_Tipo_Viagem ("Col/Transf/Ent");
          }


          // System.out.println ("DM_MANIF=" + res.getString ("DM_Tipo_Manifesto"));

          if ("RG".equals (res.getString ("DM_Tipo_Manifesto"))) {
              edVolta.setDM_Tipo_Manifesto ("Romaneio-Gaiola");
              edVolta.setDM_Tipo_Viagem ("Romaneio-Gaiola");
            }

          edVolta.setDT_Viagem (res.getString ("DT_Viagem"));
          DataFormatada.setDT_FormataData (edVolta.getDT_Viagem ());
          edVolta.setDT_Viagem (DataFormatada.getDT_FormataData ());

          edVolta.setHR_Viagem (res.getString ("HR_Viagem"));
          edVolta.setOID_Conhecimento (res.getString ("OID_Conhecimento"));
          edVolta.setNR_Conhecimento (res.getLong ("NR_Conhecimento"));

          edVolta.setDT_Emissao (res.getString ("DT_Emissao"));
          DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
          edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());

          edVolta.setDT_Previsao_Chegada (res.getString ("DT_Previsao_Chegada"));
          DataFormatada.setDT_FormataData (edVolta.getDT_Previsao_Chegada ());
          edVolta.setDT_Previsao_Chegada (DataFormatada.getDT_FormataData ());
          edVolta.setHR_Previsao_Chegada (res.getString ("HR_Previsao_Chegada"));

          edVolta.setNR_Manifesto (res.getLong ("NR_Manifesto"));
          edVolta.setCD_Unidade (res.getString ("CD_Unidade"));
          edVolta.setNM_Pessoa_Remetente (res.getString ("NM_Razao_Social_Remetente"));
          edVolta.setNM_Pessoa_Destinatario (res.getString ("NM_Razao_Social_Destinatario"));
          edVolta.setNM_Cidade_Destino (res.getString ("NM_Cidade"));
          edVolta.setOID_Manifesto (res.getString ("OID_Manifesto"));

          edVolta.setNR_Peso (res.getDouble ("NR_Peso"));
          edVolta.setNR_Volumes (res.getDouble ("NR_Volumes"));
          edVolta.setVL_Total_Frete (res.getDouble ("VL_Total_Frete"));
          edVolta.setVL_Nota_Fiscal (res.getDouble ("VL_Nota_Fiscal"));

          list.add (edVolta);
        }
      }
      if ("RN".equals(DM_Tipo_Manifesto) || "RI".equals(DM_Tipo_Manifesto)){

        sql = " SELECT Viagens.DM_Tipo_Viagem, Manifestos.DM_Tipo_Manifesto, OID_Viagem, DT_Viagem, HR_Viagem, Viagens.DT_Previsao_Chegada, Viagens.HR_Previsao_Chegada, Notas_Fiscais.OID_Nota_Fiscal, Notas_Fiscais.NR_Nota_Fiscal, Notas_Fiscais.DT_Emissao, Notas_Fiscais.NR_Peso, Notas_Fiscais.NR_Volumes, Notas_Fiscais.VL_Nota_Fiscal, Manifestos.NR_Manifesto, Unidades.CD_Unidade, Pessoa_Remetente.NM_Razao_Social as NM_Razao_Social_Remetente, Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario, Manifestos.OID_Manifesto, Cidades.NM_Cidade " +
            " FROM  Viagens, Notas_Fiscais, Unidades, Pessoas Pessoa_Remetente, Pessoas Pessoa_Destinatario, Manifestos, Cidades ";
        sql += " WHERE Unidades.oid_Unidade = Manifestos.oid_Unidade ";
        sql += " AND Notas_Fiscais.oid_Pessoa = Pessoa_Remetente.oid_Pessoa ";
        sql += " AND Notas_Fiscais.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa ";
        sql += " AND Cidades.oid_Cidade = Pessoa_Destinatario.oid_Cidade ";
        sql += " AND Viagens.OID_Nota_Fiscal = Notas_Fiscais.OID_Nota_Fiscal ";
        sql += " AND Viagens.OID_Manifesto = Manifestos.OID_Manifesto ";

        if (String.valueOf (ed.getNR_Nota_Fiscal ()) != null &&
            !String.valueOf (ed.getNR_Nota_Fiscal ()).equals ("0") &&
            !String.valueOf (ed.getNR_Nota_Fiscal ()).equals ("null")) {
          sql += " and Notas_Fiscais.NR_Nota_Fiscal = " + ed.getNR_Nota_Fiscal ();
        }
        if (String.valueOf (ed.getOID_Nota_Fiscal ()) != null &&
            !String.valueOf (ed.getOID_Nota_Fiscal ()).equals ("0") &&
            !String.valueOf (ed.getOID_Nota_Fiscal ()).equals ("null")) {
          sql += " and Notas_Fiscais.oid_Nota_Fiscal = '" + ed.getOID_Nota_Fiscal () + "'";
        }
        if (String.valueOf (ed.getOID_Manifesto ()) != null &&
            !String.valueOf (ed.getOID_Manifesto ()).equals ("0") &&
            !String.valueOf (ed.getOID_Manifesto ()).equals ("null")) {
          sql += " and Manifestos.OID_Manifesto = '" + ed.getOID_Manifesto () + "'";
        }

        if (String.valueOf (ed.getOID_Unidade ()) != null &&
            !String.valueOf (ed.getOID_Unidade ()).equals ("0") &&
            !String.valueOf (ed.getOID_Unidade ()).equals ("null")) {
          sql += " and Notas_Fiscais.OID_Unidade = " + ed.getOID_Unidade ();
        }
        if (String.valueOf (ed.getOID_Pessoa ()) != null &&
            !String.valueOf (ed.getOID_Pessoa ()).equals ("") &&
            !String.valueOf (ed.getOID_Pessoa ()).equals ("null")) {
          sql += " and Notas_Fiscais.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
        }
        if (String.valueOf (ed.getOID_Pessoa_Destinatario ()) != null &&
            !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("") &&
            !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("null")) {
          sql += " and Notas_Fiscais.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario () + "'";
        }
        if (String.valueOf (ed.getDT_Emissao ()) != null &&
            !String.valueOf (ed.getDT_Emissao ()).equals ("") &&
            !String.valueOf (ed.getDT_Emissao ()).equals ("null")) {
          sql += " and Notas_Fiscais.DT_Emissao = '" + ed.getDT_Emissao () + "'";
        }
        if (String.valueOf (ed.getDT_Viagem ()) != null &&
            !String.valueOf (ed.getDT_Viagem ()).equals ("") &&
            !String.valueOf (ed.getDT_Viagem ()).equals ("null")) {
          sql += " and Viagens.DT_Viagem = '" + ed.getDT_Viagem () + "'";
        }

        sql += " Order by Notas_Fiscais.NR_Nota_Fiscal ";
        // System.out.println (sql);

        // System.out.println (sql);

        res = this.executasql.executarConsulta (sql);
        //popula
        while (res.next ()) {
          registros++;

          ViagemED edVolta = new ViagemED ();

          edVolta.setOID_Viagem (res.getString ("OID_Viagem"));
          edVolta.setDM_Tipo_Manifesto ("Nota_Fiscal");

          edVolta.setDT_Viagem (res.getString ("DT_Viagem"));
          DataFormatada.setDT_FormataData (edVolta.getDT_Viagem ());
          edVolta.setDT_Viagem (DataFormatada.getDT_FormataData ());

          edVolta.setHR_Viagem (res.getString ("HR_Viagem"));

          edVolta.setOID_Nota_Fiscal (res.getString ("OID_Nota_Fiscal"));
          edVolta.setNR_Nota_Fiscal (res.getLong ("NR_Nota_Fiscal"));

          // System.out.println ("NF ok 2");

          edVolta.setDT_Emissao (res.getString ("DT_Emissao"));
          DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
          edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());

          edVolta.setDT_Previsao_Chegada (" ");
          edVolta.setHR_Previsao_Chegada (" ");

          edVolta.setNR_Manifesto (res.getLong ("NR_Manifesto"));
          edVolta.setCD_Unidade (res.getString ("CD_Unidade"));
          edVolta.setNM_Pessoa_Remetente (res.getString ("NM_Razao_Social_Remetente"));
          edVolta.setNM_Pessoa_Destinatario (res.getString ("NM_Razao_Social_Destinatario"));

          // System.out.println ("NF ok 3");

          edVolta.setNM_Cidade_Destino (res.getString ("NM_Cidade"));
          edVolta.setOID_Manifesto (res.getString ("OID_Manifesto"));

          edVolta.setNR_Peso (res.getDouble ("NR_Peso"));
          edVolta.setNR_Volumes (res.getDouble ("NR_Volumes"));

          edVolta.setVL_Nota_Fiscal (res.getDouble ("VL_Nota_Fiscal"));

          // System.out.println ("NF ok 4");

          list.add (edVolta);
        }
      }

      if ("MS".equals(DM_Tipo_Manifesto)){

        sql = " SELECT Viagens.DM_Tipo_Viagem, Manifestos.DM_Tipo_Manifesto, OID_Viagem, DT_Viagem, HR_Viagem, Viagens.DT_Previsao_Chegada, Viagens.HR_Previsao_Chegada, Processos.oid_Processo, Processos.NR_Processo, Processos.dt_abertura, Manifestos.NR_Manifesto, Pessoa_Remetente.NM_Razao_Social as NM_Razao_Social_Remetente, Manifestos.OID_Manifesto, Cidades.NM_Cidade from Viagens, Processos, Pessoas Pessoa_Remetente, Manifestos, Cidades ";
        sql += " WHERE  Processos.oid_Pessoa = Pessoa_Remetente.oid_Pessoa ";
        sql += " AND Viagens.OID_Processo = Processos.OID_Processo ";
        sql += " AND Viagens.OID_Manifesto = Manifestos.OID_Manifesto ";
        sql += " AND Manifestos.OID_Cidade = Cidades.OID_Cidade ";

        if (String.valueOf (ed.getOID_Processo ()) != null &&
            !String.valueOf (ed.getOID_Processo ()).equals ("0") &&
            !String.valueOf (ed.getOID_Processo ()).equals ("null")) {
          sql += " and Processos.oid_Processo = '" + ed.getOID_Processo () + "'";
        }
        if (String.valueOf (ed.getOID_Manifesto ()) != null &&
            !String.valueOf (ed.getOID_Manifesto ()).equals ("0") &&
            !String.valueOf (ed.getOID_Manifesto ()).equals ("null")) {
          sql += " and Manifestos.OID_Manifesto = '" + ed.getOID_Manifesto () + "'";
        }
        if (String.valueOf (ed.getOID_Pessoa ()) != null &&
            !String.valueOf (ed.getOID_Pessoa ()).equals ("") &&
            !String.valueOf (ed.getOID_Pessoa ()).equals ("null")) {
          sql += " and Processos.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
        }
        if (String.valueOf (ed.getDT_Viagem ()) != null &&
            !String.valueOf (ed.getDT_Viagem ()).equals ("") &&
            !String.valueOf (ed.getDT_Viagem ()).equals ("null")) {
          sql += " and Viagens.DT_Viagem = '" + ed.getDT_Viagem () + "'";
        }

        sql += " Order by Viagens.Dt_Previsao_Chegada, Viagens.Hr_Previsao_Chegada ";
        // System.out.println (sql);

        // System.out.println (sql);

        res = this.executasql.executarConsulta (sql);
        //popula
        while (res.next ()) {
          registros++;

          ViagemED edVolta = new ViagemED ();

          // System.out.println ("OS ok");

          // System.out.println ("OS=" + res.getString ("NR_Processo"));

          edVolta.setOID_Viagem (res.getString ("OID_Viagem"));
          edVolta.setDM_Tipo_Manifesto ("Ordem Serviço");
          edVolta.setDM_Tipo_Viagem ("Transf.");
          if ("C".equals (res.getString ("DM_Tipo_Viagem"))) {
            edVolta.setDM_Tipo_Viagem ("Coleta");
          }
          if ("A".equals (res.getString ("DM_Tipo_Viagem"))) {
            edVolta.setDM_Tipo_Viagem ("Col/Transf");
          }
          if ("E".equals (res.getString ("DM_Tipo_Viagem"))) {
            edVolta.setDM_Tipo_Viagem ("Entrega");
          }
          if ("Y".equals (res.getString ("DM_Tipo_Viagem"))) {
            edVolta.setDM_Tipo_Viagem ("Reentrega");
          }
          if ("R".equals (res.getString ("DM_Tipo_Viagem"))) {
            edVolta.setDM_Tipo_Viagem ("Transf/Ent.");
          }
          if ("X".equals (res.getString ("DM_Tipo_Viagem"))) {
            edVolta.setDM_Tipo_Viagem ("Col/Transf/Ent");
          }

          edVolta.setDT_Viagem (res.getString ("DT_Viagem"));
          DataFormatada.setDT_FormataData (edVolta.getDT_Viagem ());
          edVolta.setDT_Viagem (DataFormatada.getDT_FormataData ());

          edVolta.setHR_Viagem (res.getString ("HR_Viagem"));
          edVolta.setOID_Processo (res.getString ("OID_Processo"));
          edVolta.setNR_Processo (res.getString ("NR_Processo"));

          edVolta.setDT_Emissao (res.getString ("dt_abertura"));
          DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
          edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());

          edVolta.setDT_Previsao_Chegada (res.getString ("DT_Previsao_Chegada"));
          DataFormatada.setDT_FormataData (edVolta.getDT_Previsao_Chegada ());
          edVolta.setDT_Previsao_Chegada (DataFormatada.getDT_FormataData ());
          edVolta.setHR_Previsao_Chegada (res.getString ("HR_Previsao_Chegada"));

          edVolta.setNR_Manifesto (res.getLong ("NR_Manifesto"));
          edVolta.setNM_Pessoa_Remetente (res.getString ("NM_Razao_Social_Remetente"));
          edVolta.setNM_Pessoa_Destinatario (" ");
          edVolta.setNM_Cidade_Destino (res.getString ("NM_Cidade"));
          edVolta.setOID_Manifesto (res.getString ("OID_Manifesto"));
          list.add (edVolta);
        }
      }
      // System.out.println ("NF 999");

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

  public ViagemED getByRecord (ViagemED ed) throws Excecoes {

    String sql = null;

    ViagemED edVolta = new ViagemED ();

    try {

      sql = " SELECT OID_Viagem, Viagens.OID_Manifesto, Viagens.OID_Conhecimento, Viagens.OID_Processo, Viagens.OID_Nota_Fiscal, Viagens.DM_Tipo_Viagem, Viagens.OID_Compromisso, Viagens.OID_Manifesto, DT_Viagem, HR_Viagem, Viagens.DT_Previsao_Chegada, Viagens.HR_Previsao_Chegada, Manifestos.NR_Manifesto, Manifestos.DM_Tipo_Manifesto, Manifestos.DM_Lancado_Ordem_Frete " +
          " FROM Viagens, Manifestos " +
          " WHERE Viagens.OID_Manifesto = Manifestos.OID_Manifesto ";

      if (String.valueOf (ed.getOID_Viagem ()) != null &&
          !String.valueOf (ed.getOID_Viagem ()).equals ("0") &&
          !String.valueOf (ed.getOID_Viagem ()).equals ("null")) {
        sql += " and OID_Viagem = '" + ed.getOID_Viagem () + "'";
      } else if (String.valueOf (ed.getOID_Conhecimento ()) != null &&
            !String.valueOf (ed.getOID_Conhecimento ()).equals ("null") &&
            !String.valueOf (ed.getOID_Conhecimento ()).equals ("0")) {
          sql += " and OID_Conhecimento = '" + ed.getOID_Conhecimento () + "'";
      } else if (String.valueOf (ed.getOID_Manifesto ()) != null &&
              !String.valueOf (ed.getOID_Manifesto ()).equals ("null") &&
              !String.valueOf (ed.getOID_Manifesto ()).equals ("0")) {
            sql += " and OID_Manifesto = '" + ed.getOID_Manifesto () + "'";
      }
      // System.out.println (sql);
      FormataDataBean DataFormatada = new FormataDataBean ();

      ResultSet res = null;
      ResultSet res2 = null;
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta.setOID_Viagem (res.getString ("OID_Viagem"));
        edVolta.setOID_Manifesto (res.getString ("OID_Manifesto"));
        edVolta.setOID_Conhecimento (res.getString ("OID_Conhecimento"));
        edVolta.setOID_Nota_Fiscal (res.getString ("OID_Nota_Fiscal"));
        edVolta.setOID_Processo (res.getString ("OID_Processo"));
        edVolta.setNR_Manifesto (res.getLong ("NR_Manifesto"));
        edVolta.setDT_Viagem (res.getString ("DT_Viagem"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Viagem ());
        edVolta.setDT_Viagem (DataFormatada.getDT_FormataData ());
        edVolta.setHR_Viagem (res.getString ("HR_Viagem"));
        edVolta.setDT_Previsao_Chegada (res.getString ("DT_Previsao_Chegada"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Previsao_Chegada ());
        edVolta.setDT_Previsao_Chegada (DataFormatada.getDT_FormataData ());
        edVolta.setHR_Previsao_Chegada (res.getString ("HR_Previsao_Chegada"));
        edVolta.setDM_Tipo_Viagem (res.getString ("DM_Tipo_Viagem"));

        edVolta.setDM_Lancado_Ordem_Frete ("N");

        if (String.valueOf(ed.getOID_Manifesto()) != null &&
            !String.valueOf(ed.getOID_Manifesto()).equals("") &&
            !String.valueOf(ed.getOID_Manifesto()).equals("null")){

        	sql = " SELECT Ordens_Fretes.oid_Ordem_Frete " +
            	" FROM   Ordens_Fretes, Ordens_Manifestos " +
            	" WHERE  Ordens_Fretes.oid_Ordem_Frete = Ordens_Manifestos.oid_Ordem_Frete " +
            	" AND    Ordens_Manifestos.oid_Manifesto = '" + ed.getOID_Manifesto () + "'";
        	// System.out.println (sql);

        	res2 = this.executasql.executarConsulta (sql);
        	if (res2.next ()) {
          		edVolta.setDM_Lancado_Ordem_Frete ("S");
        	}
        }
        edVolta.setOID_Compromisso (res.getLong ("oid_Compromisso"));
        edVolta.setDM_Tipo_Manifesto ("Manifesto");
        if ("R".equals (res.getString ("DM_Tipo_Manifesto"))) {
          edVolta.setDM_Tipo_Manifesto ("Romaneio");
        }

        if (edVolta.getOID_Conhecimento () != null && !edVolta.getOID_Conhecimento ().equals ("") && !edVolta.getOID_Conhecimento ().equals ("null")) {
          sql = " SELECT Conhecimentos.oid_Conhecimento, Conhecimentos.oid_Pessoa, Conhecimentos.VL_Total_Frete, Conhecimentos.oid_Pessoa_Destinatario, Conhecimentos.oid_Pessoa_Entregadora, Conhecimentos.NR_Conhecimento, Conhecimentos.DT_Emissao, Conhecimentos.DT_Previsao_Entrega, Conhecimentos.HR_Previsao_Entrega, Conhecimentos.DT_Entrega, Conhecimentos.HR_Entrega, Conhecimentos.oid_Unidade from Conhecimentos ";
          sql += " WHERE Conhecimentos.OID_Conhecimento ='" + edVolta.getOID_Conhecimento () + "'";
          res2 = this.executasql.executarConsulta (sql);
          while (res2.next ()) {
            edVolta.setOID_Pessoa (res2.getString ("OID_Pessoa"));
            edVolta.setOID_Pessoa_Destinatario (res2.getString ("OID_Pessoa_Destinatario"));
            edVolta.setOID_Pessoa_Entregadora (res2.getString ("OID_Pessoa_Entregadora"));
            edVolta.setNR_Conhecimento (res2.getLong ("NR_Conhecimento"));
            edVolta.setDT_Emissao (res2.getString ("DT_Emissao"));
            DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
            edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());
            edVolta.setOID_Unidade (res2.getLong ("oid_Unidade"));
            edVolta.setVL_Total_Frete (res2.getDouble ("VL_Total_Frete"));
            edVolta.setDT_Previsao_Entrega (res2.getString ("DT_Previsao_Entrega"));
            DataFormatada.setDT_FormataData (edVolta.getDT_Previsao_Entrega ());
            edVolta.setDT_Previsao_Entrega (DataFormatada.getDT_FormataData ());
            edVolta.setHR_Previsao_Entrega (res2.getString ("HR_Previsao_Entrega"));
            edVolta.setDT_Entrega (res2.getString ("DT_Entrega"));
            DataFormatada.setDT_FormataData (edVolta.getDT_Entrega ());
            edVolta.setDT_Entrega (DataFormatada.getDT_FormataData ());
            edVolta.setHR_Entrega (res2.getString ("HR_Entrega"));
          }
        }

        if (edVolta.getOID_Nota_Fiscal () != null && !edVolta.getOID_Nota_Fiscal ().equals ("") && !edVolta.getOID_Nota_Fiscal ().equals ("null")) {
          sql = " SELECT Notas_Fiscais.oid_Nota_Fiscal, Notas_Fiscais.oid_Pessoa, Notas_Fiscais.VL_Nota_Fiscal, Notas_Fiscais.NR_Peso, Notas_Fiscais.oid_Pessoa_Destinatario, Notas_Fiscais.NR_Nota_Fiscal FROM Notas_Fiscais ";
          sql += " WHERE Notas_Fiscais.OID_Nota_Fiscal ='" + edVolta.getOID_Nota_Fiscal () + "'";
          res2 = this.executasql.executarConsulta (sql);
          while (res2.next ()) {
            edVolta.setOID_Pessoa (res2.getString ("OID_Pessoa"));
            edVolta.setOID_Pessoa_Destinatario (res2.getString ("OID_Pessoa_Destinatario"));
            edVolta.setNR_Nota_Fiscal (res2.getLong ("NR_Nota_Fiscal"));
            edVolta.setVL_Nota_Fiscal (res2.getDouble ("VL_Nota_Fiscal"));
            edVolta.setNR_Peso (res2.getDouble ("NR_Peso"));
          }
        }

        if (edVolta.getOID_Processo () != null && !edVolta.getOID_Processo ().equals ("") && !edVolta.getOID_Processo ().equals ("null")) {
          sql = " SELECT Processos.NR_Processo, Processos.oid_Pessoa from Processos ";
          sql += " WHERE Processos.OID_Processo ='" + edVolta.getOID_Processo () + "'";
          res2 = this.executasql.executarConsulta (sql);
          while (res2.next ()) {
            edVolta.setOID_Pessoa (res2.getString ("OID_Pessoa"));
            edVolta.setNR_Processo (res2.getString ("NR_Processo"));
            edVolta.setDM_Tipo_Manifesto ("Ordem Serviço");
          }
        }

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

  public ViagemED consulta_Viagem (ViagemED ed) throws Excecoes {

    String sql = null;

    ViagemED edVolta = new ViagemED ();

    try {

      sql = " SELECT Manifestos.NR_Manifesto, Manifestos.DT_Emissao from Viagens,  Manifestos, Conhecimentos " +
          " WHERE Viagens.OID_Conhecimento = Conhecimentos.OID_Conhecimento " +
          " AND Viagens.OID_Manifesto = Manifestos.OID_Manifesto " +
          " AND Manifestos.oid_Unidade = Conhecimentos.oid_Unidade " +
          " AND Conhecimentos.NR_Conhecimento = " + ed.getNR_Conhecimento () +
          " AND Conhecimentos.oid_Unidade = " + ed.getOID_Unidade ();

      // System.out.println ("->>>>" + sql);

      FormataDataBean DataFormatada = new FormataDataBean ();

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        // System.out.print ("TEM manifesto");
        edVolta.setNR_Manifesto (res.getLong ("NR_Manifesto"));

        edVolta.setDT_Emissao (res.getString ("DT_Emissao"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
        edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());
        // System.out.print ("TEM manifesto 2");
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

  public void inclui (ViagemED ed) throws Excecoes {
    String sql = null;
    String dm_tipo_manifesto = "";
    long valOid = 0 , nr_manifesto = 0;
    String chave = null;
    boolean DM_Tem_Roteiro = false;
    boolean DM_Tem_Conhecimento = false;
    ResultSet res = null;
    ResultSet res2 = null;

    try {

    nr_manifesto = viagemExiste (ed.getOID_Manifesto () , ed.getOID_Conhecimento () , ed.getDM_Tipo_Viagem () , ed.getOID_Processo () , ed.getOID_Nota_Fiscal ());
    if (nr_manifesto != 0) {
      throw new Excecoes ("Documento já Manifestado ->> " + ed.getDM_Tipo_Viagem () + "-" + nr_manifesto);
    }

    if (JavaUtil.doValida (ed.getOID_Conhecimento ())) {
      DM_Tem_Conhecimento = true;
    }

    sql = " SELECT oid_Viagem " +
   	      " FROM  Viagens " +
	      " WHERE Viagens.oid_manifesto = '" + ed.getOID_Manifesto() +"'";

    if (JavaUtil.doValida ( ed.getOID_Conhecimento())) {
    	sql += " AND   Viagens.oid_Conhecimento = '" + ed.getOID_Conhecimento() +"'";
    }else{
      	sql += " AND   viagens.OID_Nota_Fiscal = '" + ed.getOID_Nota_Fiscal() + "'";
    }

    // System.out.println(sql);
	ResultSet resVia = executasql.executarConsulta (sql);
	if (!resVia.next ()) {

      sql = " SELECT oid_Veiculo, oid_Veiculo_Carreta FROM Manifestos ";
      sql += " where oid_Manifesto = '" + ed.getOID_Manifesto () + "'";
      res = this.executasql.executarConsulta (sql);

      if (res.next ()) {
	      sql = " UPDATE Conhecimentos SET oid_Veiculo = '" + res.getString("oid_Veiculo") + "', oid_Carreta = '" + res.getString("oid_Veiculo_Carreta") + "'"  +
    	    	" WHERE  Conhecimentos.oid_Conhecimento = '" + ed.getOID_Conhecimento () + "' ";
	      executasql.executarUpdate (sql);
      }


      if (1==2 && DM_Tem_Conhecimento) {
        sql = " SELECT DM_Lancado_Ordem_Frete, CD_Roteiro, dm_tipo_manifesto from Manifestos ";
        sql += " where oid_Manifesto = '" + ed.getOID_Manifesto () + "'";
        // System.out.println(sql);

        res = this.executasql.executarConsulta (sql);

        while (res.next ()) {
          dm_tipo_manifesto = res.getString ("dm_tipo_manifesto");
          if (res.getString ("CD_Roteiro") != null &&
              !String.valueOf (res.getString ("CD_Roteiro")).equals ("") &&
              !String.valueOf (res.getString ("CD_Roteiro")).equals (null) &&
              !String.valueOf (res.getString ("CD_Roteiro")).equals ("null")) {
            DM_Tem_Roteiro = true;
          }

          if (ed.getDM_Lancado_Ordem_Frete () != null && ed.getDM_Lancado_Ordem_Frete ().equals ("VERIFICAR")) {
            if (res.getString ("DM_Lancado_Ordem_Frete").equals ("S")) {
              throw new Mensagens ("Manifesto lançado em ordem de frete - inclusão de CTRC não permitida!");
            }
          }
        }
      }
      //chave = (ed.getOID_Manifesto() + ed.getOID_Conhecimento());
      chave = String.valueOf (System.currentTimeMillis ()).toString ();

      sql = " insert into Viagens (OID_Viagem, OID_Conhecimento, OID_Nota_Fiscal, OID_Processo,  OID_Manifesto, DT_Viagem, HR_Viagem, DT_Previsao_Chegada, HR_Previsao_Chegada, DM_Tipo_Viagem ) values ";
      sql += "('" + chave + "','" + ed.getOID_Conhecimento () + "','" + ed.getOID_Nota_Fiscal () + "'," + ed.getOID_Processo () + ",'" + ed.getOID_Manifesto () + "','" + ed.getDT_Viagem () + "','" + ed.getHR_Viagem () + "','" + ed.getDT_Previsao_Chegada () + "','" + ed.getHR_Previsao_Chegada () + "','" + ed.getDM_Tipo_Viagem () +
          "')";
      // System.out.println(sql);

      // System.out.print (sql);
      int i = executasql.executarUpdate (sql);


      if (1==2 && DM_Tem_Conhecimento) {
        rateio_Frete_Carreteiro (ed);
      }

      // System.out.print ("ed.getOID_Pessoa_Entregadora ="+ed.getOID_Pessoa_Entregadora ());

      if (JavaUtil.doValida (ed.getOID_Pessoa_Entregadora ())) {
    	  try {

    	      sql = " UPDATE Conhecimentos SET oid_pessoa_entregadora = '" + ed.getOID_Pessoa_Entregadora () + "'" +
    	      	    " WHERE  Conhecimentos.oid_Conhecimento = '" + ed.getOID_Conhecimento () + "' ";
    	      // System.out.println(sql);

    	      executasql.executarUpdate (sql);
    	    }
    	    catch (SQLException e) {
    	      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "altera_Previsao_Entrega(ConhecimentoED ed)");
    	    }
      }


      if (DM_Tem_Conhecimento && edParametro_Fixo.getDM_Gera_Custo_Viagem ().equals ("S")) {

        if (ed.getOID_Pessoa_Entregadora () != null &&
            !ed.getOID_Pessoa_Entregadora ().equals ("") &&
            !ed.getOID_Pessoa_Entregadora ().equals ("null")) {

          Movimento_ConhecimentoED edMovimento_Conhecimento = new Movimento_ConhecimentoED ();

          //DM_Tipo_Viagem->> "T"
          edMovimento_Conhecimento.setOID_Tipo_Movimento (new Long (edParametro_Fixo.getOID_Tipo_Movimento_Custo_Transferencia ()).longValue ());

          if ("C".equals (ed.getDM_Tipo_Viagem ())) {
            edMovimento_Conhecimento.setOID_Tipo_Movimento (new Long (edParametro_Fixo.getOID_Tipo_Movimento_Custo_Coleta ()).longValue ());
          }
          if ("E".equals (ed.getDM_Tipo_Viagem ())) {
            edMovimento_Conhecimento.setOID_Tipo_Movimento (new Long (edParametro_Fixo.getOID_Tipo_Movimento_Custo_Entrega ()).longValue ());
          }
          if ("Y".equals (ed.getDM_Tipo_Viagem ())) {
            edMovimento_Conhecimento.setOID_Tipo_Movimento (new Long (edParametro_Fixo.getOID_Tipo_Movimento_Custo_Reentrega ()).longValue ());
          }
          if ("W".equals (ed.getDM_Tipo_Viagem ())) {
            edMovimento_Conhecimento.setOID_Tipo_Movimento (new Long (edParametro_Fixo.getOID_Tipo_Movimento_Custo_Devolucao ()).longValue ());
          }
          if ("R".equals (ed.getDM_Tipo_Viagem ())) {
            edMovimento_Conhecimento.setOID_Tipo_Movimento (new Long (edParametro_Fixo.getOID_Tipo_Movimento_Custo_Transferencia_Entrega ()).longValue ());
          }
          if ("A".equals (ed.getDM_Tipo_Viagem ())) {
            edMovimento_Conhecimento.setOID_Tipo_Movimento (new Long (edParametro_Fixo.getOID_Tipo_Movimento_Custo_Coleta_Transferencia ()).longValue ());
          }
          if ("X".equals (ed.getDM_Tipo_Viagem ())) {
            edMovimento_Conhecimento.setOID_Tipo_Movimento (new Long (edParametro_Fixo.getOID_Tipo_Movimento_Custo_Coleta_Transferencia_Entrega ()).longValue ());
          }
          edMovimento_Conhecimento.setOID_Viagem(chave);
          edMovimento_Conhecimento.setOID_Fornecedor (ed.getOID_Pessoa_Entregadora ());
          edMovimento_Conhecimento.setOID_Usuario(ed.getOID_Usuario());
          edMovimento_Conhecimento.setVL_Movimento (ed.getVL_Entrega ());
          edMovimento_Conhecimento.setDT_Movimento_Conhecimento (Data.getDataDMY ());
          edMovimento_Conhecimento.setDM_Origem_Lancamento("VIAGEM");
          edMovimento_Conhecimento.setHR_Movimento_Conhecimento (Data.getHoraHM ());
          edMovimento_Conhecimento.setOID_Conhecimento (ed.getOID_Conhecimento ());
          edMovimento_Conhecimento.setDM_Lancado_Gerado ("V");
          edMovimento_Conhecimento.setNM_Pessoa_Entrega ("");
          edMovimento_Conhecimento.setDM_Tipo_Movimento ("D");
          edMovimento_Conhecimento.setOID_Tabela_Frete ("");

          Movimento_ConhecimentoBD = new Movimento_ConhecimentoBD (this.executasql);
          Movimento_ConhecimentoBD.inclui (edMovimento_Conhecimento);
          Movimento_ConhecimentoBD.calcula_Margem (edMovimento_Conhecimento);


          if (ed.getDM_Tipo_Seguro () != null && ed.getDM_Tipo_Seguro ().equals ("P-----")) {
            edMovimento_Conhecimento.setOID_Conhecimento (ed.getOID_Conhecimento ());

            sql = " SELECT  OID_MOVIMENTO_CONHECIMENTO from Tipos_Movimentos, Movimentos_Conhecimentos ";
            sql += " Where Movimentos_Conhecimentos.oid_tipo_Movimento=Tipos_Movimentos.oid_tipo_Movimento ";
            sql += " AND Tipos_Movimentos.CD_Tipo_Movimento='SEG' ";
            sql += " AND Movimentos_Conhecimentos.oid_conhecimento = '" + ed.getOID_Conhecimento () + "'";
            // System.out.println(sql);

            res2 = this.executasql.executarConsulta (sql);

            while (res2.next ()) {
              edMovimento_Conhecimento.setOID_Movimento_Conhecimento (res2.getString ("OID_MOVIMENTO_CONHECIMENTO"));
              Movimento_ConhecimentoBD.deleta (edMovimento_Conhecimento);
            }
          }
        }

        if (DM_Tem_Conhecimento && edParametro_Fixo.getDM_Gera_Compromisso_Viagem ().equals ("S")) {

          if (ed.getVL_Entrega () > 99999999) { // nao gerar compromisso para prev entrega.

            ed.setDT_Vencimento (Data.getDataDMY());
            ed.setNR_Parcela (new Integer ("1"));
            ed.setVL_Compromisso (ed.getVL_Entrega ());

            CompromissoED compromissoED = new CompromissoED ();
            CompromissoBD compromissoBD = new CompromissoBD (this.executasql);

            compromissoED.setDt_Emissao (Data.getDataDMY());
            compromissoED.setDt_Entrada (Data.getDataDMY());
            compromissoED.setDt_Vencimento (Data.getSomaDiaData(Data.getDataDMY(),15));

            compromissoED.setNr_Parcela (new Integer ("1"));
            compromissoED.setVl_Compromisso (new Double (ed.getVL_Entrega ()));
            compromissoED.setVl_Saldo (new Double (ed.getVL_Entrega ()));
            compromissoED.setOid_Pessoa (ed.getOID_Pessoa_Entregadora ());
            compromissoED.setNr_Documento (String.valueOf (ed.getNR_Conhecimento ()));
            compromissoED.setOid_Unidade (new Long (ed.getOID_Unidade ()));
            compromissoED.setOid_Centro_Custo (new Integer (edParametro_Fixo.getOID_Centro_Custo_Entrega ()));
            compromissoED.setOid_Conta (new Integer (edParametro_Fixo.getOID_Conta_Debito_Entrega ()));
            compromissoED.setOid_Conta_Credito (new Integer (edParametro_Fixo.getOID_Conta_Credito_Entrega ()));
            compromissoED.setDM_Tipo_Pagamento ("1");
            compromissoED.setOid_Tipo_Documento (new Integer (edParametro_Fixo.getOID_Tipo_Documento_Entrega ()));

            compromissoED.setOid_Natureza_Operacao (new Integer (edParametro_Fixo.getOID_Natureza_Operacao_Entrega ()));

            compromissoED = compromissoBD.inclui (compromissoED);

            chave = (ed.getOID_Manifesto () + ed.getOID_Conhecimento ());
            sql = " update Viagens set OID_Compromisso = " + compromissoED.getOid_Compromisso ();
            sql += " WHERE Viagens.oid_Viagem = '" + chave + "'";

            executasql.executarUpdate (sql);
          }

        }
      }

      if (1==2 && DM_Tem_Conhecimento && edParametro_Fixo.getDM_Gera_Ocorrencia_Viagem ().equals ("S")) {
        sql = " SELECT  * from Tipos_Ocorrencias Where DM_Tipo='E' ";
        // System.out.println(sql);

        res2 = this.executasql.executarConsulta (sql);

        String TX_Observacao = "Manifesto ->> " + ed.getNR_Manifesto ();

        while (res2.next ()) {
          Ocorrencia_ConhecimentoED ocorrencia_ConhecimentoED = new Ocorrencia_ConhecimentoED ();
          Ocorrencia_ConhecimentoBD Ocorrencia_ConhecimentoBD = new Ocorrencia_ConhecimentoBD (this.executasql);
          ocorrencia_ConhecimentoED.setOID_Usuario(ed.getOID_Usuario());
          ocorrencia_ConhecimentoED.setDT_Ocorrencia_Conhecimento (Data.getDataDMY ());
          ocorrencia_ConhecimentoED.setHR_Ocorrencia_Conhecimento (Data.getHoraHM ());
          ocorrencia_ConhecimentoED.setOID_Tipo_Ocorrencia (res2.getLong ("oid_Tipo_Ocorrencia"));
          ocorrencia_ConhecimentoED.setOID_Conhecimento (ed.getOID_Conhecimento ());
          ocorrencia_ConhecimentoED.setTX_Observacao (TX_Observacao);
          ocorrencia_ConhecimentoED.setNM_Pessoa_Entrega ("");
          ocorrencia_ConhecimentoED.setDM_Tipo (res2.getString ("DM_Tipo"));
          ocorrencia_ConhecimentoED.setDM_Tipo (res2.getString ("DM_Acesso"));
          ocorrencia_ConhecimentoED.setDM_Tipo (res2.getString ("DM_Avaria"));
          ocorrencia_ConhecimentoED.setDM_Tipo (res2.getString ("DM_Status"));

          ocorrencia_ConhecimentoED = Ocorrencia_ConhecimentoBD.inclui (ocorrencia_ConhecimentoED);

        }
      }

      if (1==2 && DM_Tem_Conhecimento) {

        sql = " SELECT Conhecimentos_Notas_Fiscais.OID_Nota_Fiscal from Conhecimentos_Notas_Fiscais, Conhecimentos ";
        sql += " WHERE Conhecimentos_Notas_Fiscais.OID_Conhecimento = Conhecimentos.OID_Conhecimento ";
        sql += " AND Conhecimentos_Notas_Fiscais.OID_Conhecimento = '" + ed.getOID_Conhecimento () + "'";
        // System.out.println(sql);

        res = this.executasql.executarConsulta (sql);

        while (res.next ()) {
          ed.setOID_Nota_Fiscal (res.getString ("OID_Nota_Fiscal"));

          sql = " update Notas_Fiscais set DM_Situacao= 'E' ";
          sql += " where Notas_Fiscais.oid_Nota_Fiscal = '" + ed.getOID_Nota_Fiscal () + "'";
          // System.out.println(sql);

          i = executasql.executarUpdate (sql);

          if (DM_Tem_Roteiro) {
            String OID_Embarque = null;

            sql = " SELECT OID_Embarque from Embarques ";
            sql += " WHERE Embarques.OID_Manifesto = '" + ed.getOID_Manifesto () + "'";

            ResultSet resTP = this.executasql.executarConsulta (sql);

            while (resTP.next ()) {
              OID_Embarque = resTP.getString ("OID_Embarque");
            }

            if (OID_Embarque != null && !OID_Embarque.equals ("")) {
              chave = (OID_Embarque + ed.getOID_Nota_Fiscal ());

              sql = " insert into Notas_Fiscais_Embarques (OID_Nota_Fiscal_Embarque, OID_Embarque, OID_Nota_Fiscal, DT_Nota_Fiscal_Embarque, HR_Nota_Fiscal_Embarque ) values ";
              sql += "('" + chave + "'," + OID_Embarque + ",'" + ed.getOID_Nota_Fiscal () + "','" + Data.getDataDMY () + "','" + Data.getHoraHM () + "')";
              // System.out.println(sql);

              i = executasql.executarUpdate (sql);

              sql = " update Embarques set DM_Situacao = '5'";
              sql += " where oid_Embarque = " + OID_Embarque;

              i = executasql.executarUpdate (sql);

            }
          }
        }
      }
	 }
    }
    catch (SQLException e) {
      // System.out.println (sql);
      e.printStackTrace ();
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "inclui(ViagemED ed)");
    }
  }

  public void inclui_varios_ctos (ViagemED ed) throws Excecoes {

    String sql = null;
    long valOid = 0;
    String chave = null;
    String oid_Conhecimentos="";
    String oid_Romaneio="";
    int qt_man = 0;
    int qt_cto=0;
    ResultSet res = null;
    ResultSet res2 = null;
    try {
      // System.out.println ("Viagem inclui NEWWWWWWWW ");
      // System.out.println ("NR MANIF="+ed.getNR_Romaneio());

      if (ed.getNR_Romaneio()>0){
          sql = "SELECT  Conhecimentos.oid_Conhecimento, Manifestos.oid_Manifesto " +
          " FROM Conhecimentos, Viagens, Manifestos " +
          " WHERE Conhecimentos.oid_Conhecimento = Viagens.oid_Conhecimento " +
          " AND   Viagens.oid_Manifesto = Manifestos.oid_Manifesto " +
          " AND   Manifestos.NR_Manifesto = " + ed.getNR_Romaneio();
          res = this.executasql.executarConsulta (sql);

          while (res.next ()) {
        	  qt_cto++;
        	  if (qt_cto>1) oid_Conhecimentos+=",";
        	  oid_Conhecimentos+="'"+res.getString("oid_Conhecimento")+"'";
        	  oid_Romaneio=res.getString("oid_Manifesto");
          }
      }

      sql = "SELECT  Conhecimentos.oid_Conhecimento, Conhecimentos.NR_Conhecimento, ";
      sql += " Conhecimentos.NR_Peso, ";
      sql += " Conhecimentos.NR_Volumes, ";
      sql += " Conhecimentos.VL_Total_Frete, ";
      sql += " Conhecimentos.VL_Nota_Fiscal ";
      sql += " FROM Conhecimentos ";
      sql += " WHERE  Conhecimentos.VL_Total_Frete >0 " +
          " AND Conhecimentos.DM_Impresso ='S'  " +
          " AND Conhecimentos.DM_Situacao <>'C'  ";

      if (!"".equals(oid_Conhecimentos)) {
    	  sql += "and Conhecimentos.oid_Conhecimento in ("+oid_Conhecimentos+")";
      } else {


	      if (String.valueOf (ed.getOID_Unidade ()) != null &&
	          !String.valueOf (ed.getOID_Unidade ()).equals ("") &&
	          !String.valueOf (ed.getOID_Unidade ()).equals ("null")) {
	        sql += " and Conhecimentos.OID_Unidade = '" + ed.getOID_Unidade () + "'";
	      }

	      if (String.valueOf (ed.getDT_Emissao ()) != null &&
	          !String.valueOf (ed.getDT_Emissao ()).equals ("") &&
	          !String.valueOf (ed.getDT_Emissao ()).equals ("null")) {
	        sql += " and Conhecimentos.DT_Emissao = '" + ed.getDT_Emissao () + "'";
	      }

	      if (String.valueOf (ed.getOID_Pessoa_Entregadora ()) != null &&
	          !String.valueOf (ed.getOID_Pessoa_Entregadora ()).equals ("") &&
	          !String.valueOf (ed.getOID_Pessoa_Entregadora ()).equals ("null")) {
	        sql += " and Conhecimentos.OID_Pessoa_Entregadora = '" + ed.getOID_Pessoa_Entregadora () + "'";
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
	      if (JavaUtil.doValida (ed.getOid_Veiculo ())) {
	        sql += " and Conhecimentos.oid_Veiculo = " + JavaUtil.getSQLString (ed.getOid_Veiculo ());
	      }
      }

       //System.out.println ("filtro Ctos Manif " + sql);

      res = this.executasql.executarConsulta (sql);

      while (res.next ()) {
        qt_man++;
        qt_cto=0;
        if (ed.getNR_Romaneio()==0){
        	sql = " SELECT  count (oid_Conhecimento) as qt_cto " +
        		" FROM Viagens " +
        		" WHERE Viagens.oid_Conhecimento ='" + res.getString ("oid_Conhecimento") + "'" +
        		" AND   Viagens.oid_Manifesto <> '" + ed.getOID_Manifesto()+"'";
        		//System.out.println (sql);

	        res2 = this.executasql.executarConsulta (sql);
	        if (res2.next ()) {
	          qt_cto = res2.getInt ("qt_cto");
	        }
      	}

        // System.out.println (qt_cto);

        if (qt_cto == 0) {
          // System.out.println (res.getString ("NR_Conhecimento"));
          ed.setOID_Conhecimento (res.getString ("oid_Conhecimento"));
          ed.setDM_Tipo_Viagem ("T");
          this.inclui (ed);
        }
      }
      if (qt_man == 0) {
        throw new Exception ("Nenhum CTRC encontrado. Verifique os campos preenchidos.");
      }

    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "inclui_varios_ctos(ViagemED ed)");
    }
    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "inclui_varios_ctos(ViagemED ed)");
    }
  }

  public void inclui_nota_fiscal_filtro (ViagemED ed) throws Excecoes {

	    String sql = null;
	    int qt_man = 0;
	    int qt_cto=0;
	    ResultSet res = null;
	    ResultSet res2 = null;
	    try {
	      // System.out.println ("inclui_nota_fiscal_filtro");

	      sql = " SELECT  Notas_Fiscais.oid_Nota_Fiscal, Notas_Fiscais.NR_Nota_Fiscal  " +
	      	    " FROM   Notas_Fiscais " +
	      	    " WHERE  Notas_Fiscais.oid_Pessoa ='" + ed.getOID_Pessoa() + "'" +
	      	    " AND    Notas_Fiscais.oid_Pessoa_Entregadora='" + ed.getOID_Pessoa_Entregadora() +"'";

		      if (String.valueOf (ed.getDT_Emissao ()) != null &&
		          !String.valueOf (ed.getDT_Emissao ()).equals ("") &&
		          !String.valueOf (ed.getDT_Emissao ()).equals ("null")) {
		        sql += " and Notas_Fiscais.DT_Emissao = '" + ed.getDT_Emissao () + "'";
		      }

	       // System.out.println ("inclui_nota_fiscal_filtro" + sql);

	      res = this.executasql.executarConsulta (sql);

	      while (res.next ()) {
	        	sql = " SELECT  count (oid_Nota_Fiscal) as qt_cto " +
	        		" FROM Viagens " +
	        		" WHERE Viagens.oid_Nota_Fiscal ='" + res.getString ("oid_Nota_Fiscal") + "'" +
	        		" AND   Viagens.oid_Manifesto <> '" + ed.getOID_Manifesto()+"'";
	        		 // System.out.println (sql);

		        res2 = this.executasql.executarConsulta (sql);
		        if (res2.next ()) {
		          qt_cto = res2.getInt ("qt_cto");
		        }
		        // System.out.println (qt_cto);

		        if (qt_cto == 0) {
		           // System.out.println (res.getString ("NR_Nota_Fiscal"));
		          ed.setOID_Nota_Fiscal (res.getString ("oid_Nota_Fiscal"));
		          ed.setDM_Tipo_Viagem ("T");

		          ed.setDT_Viagem (Data.getDataDMY());
		          ed.setHR_Viagem (Data.getHoraHM());
		          ed.setDT_Previsao_Chegada (Data.getDataDMY());
		          ed.setHR_Previsao_Chegada (Data.getHoraHM());

		          this.inclui (ed);
		          qt_man++;
		        }
	      	}

	      if (qt_man == 0) {
	        //throw new Exception ("Nenhuma Nota Fiscal encontrada. Verifique os campos preenchidos.");
	      }

	    }
	    catch (Excecoes exc) {
	      throw exc;
	    }
	    catch (SQLException e) {
	      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "inclui_nota_fiscal_filtro(ViagemED ed)");
	    }
	    catch (Exception e) {
	      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "inclui_nota_fiscal_filtro(ViagemED ed)");
	    }
	  }

  public void inclui_ctos_Rota (ViagemED ed) throws Excecoes {

    String sql = null;
    long valOid = 0;
    String chave = null;
    int qt_man = 0;
    try {

      String cd_rota_entrega = "";
      sql = " Select manifestos.cd_rota_entrega, manifestos.dt_previsao_chegada, manifestos.hr_previsao_chegada " +
          " from manifestos " +
          " where manifestos.oid_manifesto = '" + ed.getOID_Manifesto () + "'";
      ResultSet retorno = this.executasql.executarConsulta (sql);
      while (retorno.next ()) {
        cd_rota_entrega = retorno.getString (1);
        ed.setDT_Previsao_Chegada (retorno.getString (2));
        ed.setHR_Previsao_Chegada (retorno.getString (3));
      }

      if (JavaUtil.doValida (cd_rota_entrega)) {

        sql = "SELECT  Conhecimentos.oid_Conhecimento, Conhecimentos.NR_Conhecimento, ";
        sql += " Conhecimentos.NR_Peso, ";
        sql += " Conhecimentos.NR_Volumes, ";
        sql += " Conhecimentos.VL_Total_Frete, ";
        sql += " Conhecimentos.VL_Nota_Fiscal ";
        sql += " FROM Conhecimentos ";
        sql += " WHERE  Conhecimentos.VL_Total_Frete >0 " +
            " AND Conhecimentos.DM_Impresso ='S'  " +
            " AND Conhecimentos.DM_Situacao <>'C'  ";
        sql += " and Conhecimentos.oid_Pessoa_destinatario in (select oid_pessoa from rotas_entregas where cd_rota_entrega = '" + cd_rota_entrega + "' order by nr_ordem asc)";

        ResultSet res = null;
        ResultSet res2 = null;
        res = this.executasql.executarConsulta (sql);

        while (res.next ()) {
          qt_man++;
          int qt_cto = 0;
          sql = " SELECT  count (oid_Conhecimento) as qt_cto " +
              " FROM Viagens " +
              " WHERE Viagens.oid_Conhecimento ='" + res.getString ("oid_Conhecimento") + "'";
          res2 = this.executasql.executarConsulta (sql);
          while (res2.next ()) {
            qt_cto = res2.getInt ("qt_cto");
          }
          if (qt_cto == 0) {
            ed.setDT_Viagem (Data.getDataDMY ());
            ed.setHR_Viagem (Data.getHoraHM ());
            if (!JavaUtil.doValida (ed.getDT_Previsao_Chegada ())) {
              ed.setDT_Previsao_Chegada (Data.getDataDMY ());
            }
            if (!JavaUtil.doValida (ed.getHR_Previsao_Chegada ())) {
              ed.setHR_Previsao_Chegada (Data.getHoraHM ());
            }

            ed.setOID_Conhecimento (res.getString ("oid_Conhecimento"));
            ed.setDM_Tipo_Viagem ("T");
            this.inclui (ed);
          }
        }
      }
      if (qt_man == 0) {
        throw new Exception ("Nenhum CTRC encontrado. Verifique os campos preenchidos.");
      }
    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "inclui_varios_ctos(ViagemED ed)");
    }
    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "inclui_varios_ctos(ViagemED ed)");
    }
  }

  public long viagemExiste (String oid_Manifesto , String oid_Conhecimento , String DM_Tipo_Viagem , String oid_Processo , String oid_Nota_Fiscal) throws Excecoes {
    long nr_manifesto = 0;
    ResultSet res = null;
    try {

      if (!"D".equals (edParametro_Fixo.getDM_Conhecimento_Varios_Manifestos ())) {

        String sqlBusca =
            " SELECT  nr_manifesto " +
            " FROM   viagens, manifestos " +
            " WHERE viagens.oid_manifesto = manifestos.oid_manifesto " +
        	" AND   manifestos.DM_Tipo_Manifesto ='M' ";

        if (JavaUtil.doValida ( (oid_Conhecimento))) {
          sqlBusca += " AND   viagens.oid_Conhecimento = '" + oid_Conhecimento + "'";
        }
        if (JavaUtil.doValida ( (oid_Nota_Fiscal))) {
          sqlBusca += " AND   viagens.oid_Nota_Fiscal = '" + oid_Nota_Fiscal + "'";
        }
        sqlBusca += " AND   viagens.oid_Manifesto = '" + oid_Manifesto + "'";

        res = executasql.executarConsulta (sqlBusca);
        if (res.next ()) {
          nr_manifesto = res.getLong ("nr_manifesto");
        }
        if (nr_manifesto == 0) {
          if (!"D".equals (edParametro_Fixo.getDM_Conhecimento_Varios_Manifestos ())) {

            sqlBusca = " SELECT oid_Viagem, nr_manifesto " +
                " FROM   viagens, manifestos " +
                " WHERE viagens.oid_manifesto = manifestos.oid_manifesto " +
            	" AND   (manifestos.DM_Tipo_Manifesto ='M' OR manifestos.DM_Tipo_Manifesto ='R' )";

            if (JavaUtil.doValida ( (oid_Conhecimento))) {
              sqlBusca += " AND   viagens.DM_Tipo_Viagem = '" + DM_Tipo_Viagem + "' " +
                  " AND   viagens.oid_Conhecimento = '" + oid_Conhecimento + "'";
            }
            if (JavaUtil.doValida ( (oid_Processo))) {
              sqlBusca += " AND   viagens.oid_Processo = '" + oid_Processo + "'";
            }
            if (JavaUtil.doValida ( (oid_Nota_Fiscal))) {
              sqlBusca += " AND   viagens.oid_Nota_Fiscal = '" + oid_Nota_Fiscal + "'";
            }

            // System.out.println ("DM_Conhecimento_Varios_Manifestos->>" + sqlBusca);
            res = executasql.executarConsulta (sqlBusca);
            while (res.next ()) {
              nr_manifesto = res.getLong ("nr_manifesto");
            }
          }
        }
      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "viagemExiste(String oid_Manifesto, String oid_Conhecimento)");
    }

    return nr_manifesto;
  }

  public ViagemED rateio_Frete_Carreteiro (ViagemED ed) throws Excecoes {

    // System.out.println ("rateio_Frete_Carreteiro : ");

    String sql = null;

    try {
      if (1 == 1) { // "S".equals(edParametro_Fixo.getDM_Gera_Rateio_Custo_Ordem_Frete())){

        sql = " DELETE FROM Movimentos_Conhecimentos " +
            " WHERE  Movimentos_Conhecimentos.oid_Manifesto = '" + ed.getOID_Manifesto () + "'";
        // System.out.println (sql);
        int i = executasql.executarUpdate (sql);

        sql = " SELECT Manifestos.VL_Frete_Carreteiro, Viagens.DM_Tipo_Viagem,  Viagens.DM_Tipo_Viagem, Conhecimentos.OID_Conhecimento, Conhecimentos.NR_Conhecimento, Conhecimentos.NR_Peso as NR_Peso_CTRC  , Conhecimentos.NR_Peso_Cubado as NR_Peso_Cubado_CTRC" +
            " FROM  Viagens, Conhecimentos, Manifestos " +
            " WHERE Viagens.OID_Conhecimento = Conhecimentos.OID_Conhecimento " +
            " AND Viagens.OID_Manifesto =  Manifestos.oid_Manifesto " +
            " AND Manifestos.oid_Manifesto = '" + ed.getOID_Manifesto () + "'";

        // System.out.println ("sql: " + sql);

        ResultSet rs = null;
        rs = this.executasql.executarConsulta (sql);
        String DM_Tipo_Viagem = "";
        double NR_Peso_Total = 0;
        double VL_Movimento = 0;
        double VL_Frete_Carreteiro = 0;
        double NR_Peso = 0;
        double NR_Peso_Cubado = 0;
        int x = 0;
        while (rs.next () && x < 9999) {
          x++;

          // System.out.println ("w1 : ");

          DM_Tipo_Viagem = rs.getString ("DM_Tipo_Viagem");
          VL_Frete_Carreteiro = rs.getDouble ("VL_Frete_Carreteiro");

          NR_Peso = rs.getDouble ("NR_Peso_CTRC");
          NR_Peso_Cubado = rs.getDouble ("NR_Peso_Cubado_CTRC");
          if (NR_Peso_Cubado > NR_Peso) {
            NR_Peso = NR_Peso_Cubado;
          }

          NR_Peso_Total = NR_Peso_Total + NR_Peso;
          // System.out.println ("1 CTO : " + rs.getString ("NR_Conhecimento") + " Peso : " + rs.getString ("NR_Peso_CTRC") + " Peso Calc:" + NR_Peso);
        }

        // System.out.println ("VL_Ordem_Frete: " + VL_Frete_Carreteiro);
        // System.out.println ("NR_Peso_Total: " + NR_Peso_Total);
        // System.out.println ("DM_Tipo_Viagem: " + DM_Tipo_Viagem);

        if (NR_Peso_Total > 0 && VL_Frete_Carreteiro > 0) {
          sql = " SELECT Conhecimentos.OID_Conhecimento, Conhecimentos.NR_Conhecimento, Conhecimentos.NR_Peso as NR_Peso_CTRC , Conhecimentos.NR_Peso_Cubado as NR_Peso_Cubado_CTRC";
          sql += " FROM Viagens, Conhecimentos ";
          sql += " WHERE Viagens.OID_Conhecimento = Conhecimentos.OID_Conhecimento ";
          sql += " AND   Viagens.oid_Manifesto = '" + ed.getOID_Manifesto () + "'";

          // System.out.println ("sql: " + sql);

          rs = this.executasql.executarConsulta (sql);
          while (rs.next ()) {

            // System.out.println ("w2: ");

            NR_Peso = rs.getDouble ("NR_Peso_CTRC");

            NR_Peso_Cubado = rs.getDouble ("NR_Peso_Cubado_CTRC");
            if (NR_Peso_Cubado > NR_Peso) {
              NR_Peso = NR_Peso_Cubado;
            }

            VL_Movimento = (VL_Frete_Carreteiro / NR_Peso_Total * NR_Peso);
            if (VL_Movimento > VL_Frete_Carreteiro) {
              VL_Movimento = VL_Frete_Carreteiro;
            }

            // System.out.println ("2 CTO --: " + rs.getString ("NR_Conhecimento") + " Peso : " + rs.getString ("NR_Peso_CTRC") + " Peso Cub : " + rs.getString ("NR_Peso_Cubado_CTRC") + " Peso Calc:" + NR_Peso + " VL_Movimento:" + VL_Movimento);

            Movimento_ConhecimentoRN Movimento_Conhecimentorn = new Movimento_ConhecimentoRN ();
            Movimento_ConhecimentoED edMovimento_Conhecimento = new Movimento_ConhecimentoED ();

            edMovimento_Conhecimento.setOID_Tipo_Movimento (new Long (edParametro_Fixo.getOID_Tipo_Movimento_Custo_Ordem_Frete ()).longValue ());

            // System.out.println ("2 CTO --DM_Tipo_Viagem OF-> " + DM_Tipo_Viagem);

            DM_Tipo_Viagem = "-------"; //marca p/nao pegar

            //custo coleta
            if ("C".equals (DM_Tipo_Viagem)) {
              edMovimento_Conhecimento.setOID_Tipo_Movimento (new Long (edParametro_Fixo.getOID_Tipo_Movimento_Custo_Coleta ()).longValue ());
              // System.out.println ("2 CTO --Coleta");
            }

            //custo entrega
            if ("E".equals (DM_Tipo_Viagem)) {
              edMovimento_Conhecimento.setOID_Tipo_Movimento (new Long (edParametro_Fixo.getOID_Tipo_Movimento_Custo_Entrega ()).longValue ());
              // System.out.println ("2 CTO --entrega");
            }

            //custo coleta trans entrega
            if ("X".equals (DM_Tipo_Viagem)) {
              edMovimento_Conhecimento.setOID_Tipo_Movimento (new Long (edParametro_Fixo.getOID_Tipo_Movimento_Custo_Coleta_Transferencia_Entrega ()).longValue ());
              // System.out.println ("2 CTO --coleta trans entrega");
            }

            //custo trans entrega
            if ("R".equals (DM_Tipo_Viagem)) {
              edMovimento_Conhecimento.setOID_Tipo_Movimento (new Long (edParametro_Fixo.getOID_Tipo_Movimento_Custo_Transferencia_Entrega ()).longValue ());
              // System.out.println ("2 CTO --transf entrega");
            }

            //custo coleta trans
            if ("A".equals (DM_Tipo_Viagem)) {
              edMovimento_Conhecimento.setOID_Tipo_Movimento (new Long (edParametro_Fixo.getOID_Tipo_Movimento_Custo_Coleta_Transferencia ()).longValue ());
              // System.out.println ("2 CTO --coleta transf");
            }

            // System.out.println ("3 CTO --: " + rs.getString ("NR_Conhecimento") + " Peso : " + rs.getString ("NR_Peso_CTRC") + " Peso Cub : " + rs.getString ("NR_Peso_Cubado_CTRC") + " Peso Calc:" + NR_Peso + " VL_Movimento:" + VL_Movimento);

            edMovimento_Conhecimento.setVL_Movimento (new Double (VL_Movimento).doubleValue ());
            edMovimento_Conhecimento.setDT_Movimento_Conhecimento (Data.getDataDMY ());
            edMovimento_Conhecimento.setHR_Movimento_Conhecimento (Data.getHoraHM ());

            edMovimento_Conhecimento.setOID_Manifesto (ed.getOID_Manifesto ());
            edMovimento_Conhecimento.setOID_Conhecimento (rs.getString ("OID_Conhecimento"));

            // System.out.println ("4 CTO --: " + rs.getString ("NR_Conhecimento") + " Peso : " + rs.getString ("NR_Peso_CTRC") + " Peso Cub : " + rs.getString ("NR_Peso_Cubado_CTRC") + " Peso Calc:" + NR_Peso + " VL_Movimento:" + VL_Movimento);

            edMovimento_Conhecimento.setDM_Tipo_Movimento ("D");
            edMovimento_Conhecimento.setDM_Lancado_Gerado ("R");
            edMovimento_Conhecimento.setNM_Pessoa_Entrega ("");

            // System.out.println ("99 CTO : " + rs.getString ("NR_Conhecimento") + " Peso : " + rs.getString ("NR_Peso_CTRC") + " Peso Cub : " + rs.getString ("NR_Peso_Cubado_CTRC") + " Peso Calc:" + NR_Peso + " VL_Movimento:" + VL_Movimento);

            Movimento_Conhecimentorn.inclui (edMovimento_Conhecimento);

          }
        }
      }

    }
    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro rateio_Frete_Carreteiro");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("rateio_Frete_Carreteiro)");
    }
    return ed;
  }

  public ViagemED geraViagem_CtoAvulso (ViagemED edViagem) throws Excecoes {

    String sql = null;
    int qt_man = 0;
    try {
      // System.out.println ("Viagem inclui_viagem_CtoAvulso ");

      sql = " SELECT Cia_Aereas.oid_Pessoa FROM Pessoas, Cia_Aereas  " +
          " WHERE Pessoas.oid_Pessoa = Cia_Aereas.oid_Pessoa " +
          " AND   Cia_Aereas.cd_cia_aerea = '" + edViagem.getDM_Cia_Aerea () + "'";
      // System.out.println ("filtro Ctos Manif " + sql);
      ResultSet res = this.executasql.executarConsulta (sql);
      if (res.next ()) {

        sql = " SELECT  Conhecimentos.oid_Conhecimento, Conhecimentos.DT_Emissao, Conhecimentos.DT_Previsao_Entrega, Conhecimentos.oid_Unidade, Conhecimentos.NR_Conhecimento, Conhecimentos.oid_Cidade as oid_Cidade_Origem, Conhecimentos.oid_Cidade_Destino, Conhecimentos.NR_Peso, Conhecimentos.VL_Total_Frete " +
            " FROM   Conhecimentos " +
            " WHERE  Conhecimentos.OID_Conhecimento = '" + edViagem.getOID_Conhecimento () + "'";
        // System.out.println ("filtro Ctos Manif " + sql);

        ResultSet resCto = this.executasql.executarConsulta (sql);
        if (resCto.next ()) {

          // System.out.println ("acho cto");

          ManifestoED edManif = new ManifestoED ();
          edManif.setDM_Lancado_Ordem_Frete ("N");
          edManif.setOID_Seguradora (1);
          edManif.setOID_Pessoa (edParametro_Fixo.getOID_Cliente_Complemento_Padrao ());
          edManif.setOID_Veiculo ("AEREO");
          edManif.setOID_Cidade (resCto.getLong ("oid_Cidade_Destino"));
          edManif.setOID_Cidade_Origem (resCto.getLong ("oid_Cidade_Origem"));
          edManif.setOID_Unidade (resCto.getLong ("oid_Unidade"));
          edManif.setDT_Emissao (resCto.getString ("DT_Emissao"));
          edManif.setDT_Previsao_Chegada (resCto.getString ("DT_Previsao_Entrega"));
          edManif.setHR_Previsao_Chegada ("10:00");
          edManif.setNR_Odometro_Inicial (0);
          edManif.setNM_Liberacao_Seguradora ("");
          edManif.setCD_Roteiro ("");
          edManif.setCD_Rota_Entrega ("");
          edManif.setOID_Veiculo_Carreta ("");
          edManif.setOID_Ordem_Frete ("");
          edManif.setOID_Subregiao (0);
          edManif.setOID_Subregiao (0);
          edManif.setOID_Pessoa_Unidade_Destino ("");
          edManif.setOID_Pessoa_Entregadora ("");
          edManif.setOID_Unidade_Destino (0);
          edManif.setNM_Ajudante1 (" ");
          edManif.setNM_Ajudante2 (" ");
          edManif.setNM_Ajudante3 (" ");
          edManif.setTX_Observacao (" ");
          edManif.setHR_Saida (" ");
          edManif.setDM_Expresso ("N");
          edManif.setDM_Manifesto_Romaneio ("M");
          edManif.setVL_Frete_Carreteiro (0);
          // System.out.println ("man 13");

          edManif.setPE_Custo_Entrega (0);
          edManif.setNR_Ajudante (0);
          edManif.setNR_KIT (0);
          edManif.setNR_KM_Viagem (0);

          ManifestoBD manifestoBD = new ManifestoBD (this.executasql);

          // System.out.println ("antes de incluir manif");

          edManif = manifestoBD.inclui (edManif);
          edManif = manifestoBD.getByRecord (edManif);

          // System.out.println (" manif ok =" + edManif.getOID_Manifesto ());

          if (edManif.getOID_Manifesto () != null && edManif.getOID_Manifesto ().length () > 4) {

            ViagemED ed = new ViagemED ();

            // System.out.println ("antes de incluir viagem");

            ed.setDT_Viagem (edManif.getDT_Emissao ());
            ed.setHR_Viagem (Data.getHoraHM ());

            ed.setDT_Previsao_Chegada (edManif.getDT_Previsao_Chegada ());
            ed.setHR_Previsao_Chegada (edManif.getHR_Previsao_Chegada ());

            ed.setDT_Previsao_Entrega (edManif.getDT_Previsao_Chegada ());
            ed.setHR_Previsao_Entrega (edManif.getHR_Previsao_Chegada ());

            ed.setOID_Manifesto (edManif.getOID_Manifesto ());
            ed.setOID_Conhecimento (edViagem.getOID_Conhecimento ());
            ed.setOID_Processo ("0");
            ed.setOID_Unidade (edManif.getOID_Unidade ());
            ed.setDM_Tipo_Seguro ("E");
            ed.setDM_Lancado_Ordem_Frete ("VERIFICAR");
            ed.setDM_Tipo_Viagem ("T");

            // System.out.println (" quase ");

            this.inclui (ed);
            // System.out.println (" viagem ok =" + edManif.getOID_Manifesto ());

            edViagem.setOID_Manifesto (edManif.getOID_Manifesto ());

            Ordem_FreteED edOrdemFrete = new Ordem_FreteED ();

            // System.out.println ("vai incluir ordem_FreteBD");

            edOrdemFrete.setOID_Manifesto (edViagem.getOID_Manifesto ());

            edOrdemFrete.setOID_Pessoa (res.getString ("oid_Pessoa"));

            edOrdemFrete.setDT_Emissao (edManif.getDT_Emissao ());
            edOrdemFrete.setHR_Emissao (Data.getHoraHM ());
            edOrdemFrete.setOID_Unidade (edManif.getOID_Unidade ());

            // System.out.println ("vai incluir ordem_FreteBD 2");

            edOrdemFrete.setDT_Adiantamento1 (Data.getDataDMY ());
            edOrdemFrete.setDT_Adiantamento2 (Data.getDataDMY ());
            edOrdemFrete.setDT_Saldo (Data.getDataDMY ());
            edOrdemFrete.setVL_Ordem_Frete (edViagem.getVL_Ordem_Frete ());
            edOrdemFrete.setVL_ICMS (edViagem.getVL_Ordem_Frete () * 4 / 100);
            edOrdemFrete.setPE_ICMS (4);
            edOrdemFrete.setNR_Peso_Master (resCto.getDouble ("nr_peso"));

            // System.out.println ("vai incluir ordem_FreteBD 3");

            edOrdemFrete.setNR_Ordem_Frete (0);
            edOrdemFrete.setDM_Impresso ("S");
            edOrdemFrete.setVL_Total_Frete_CTRC (resCto.getDouble ("vl_total_frete"));
            edOrdemFrete.setNR_Total_Peso_CTRC (resCto.getDouble ("nr_peso"));
            edOrdemFrete.setNR_Master (edViagem.getNR_Master ());

            edOrdemFrete.setNR_Voo (" ");
            edOrdemFrete.setDT_Voo (resCto.getString ("DT_Emissao"));
            edOrdemFrete.setHR_Voo (" ");

            if (edViagem.getNR_Voo () != null) {
              edOrdemFrete.setNR_Voo (edViagem.getNR_Voo ());
            }
            if (edViagem.getDT_Voo () != null) {
              edOrdemFrete.setDT_Voo (edViagem.getDT_Voo ());
            }
            if (edViagem.getHR_Voo () != null) {
              edOrdemFrete.setHR_Voo (edViagem.getHR_Voo ());
            }
            Ordem_FreteBD ordem_FreteBD = new Ordem_FreteBD (this.executasql);

            // System.out.println ("antes de incluir ordem_FreteBD 4");

            Ordem_FreteED edOrdem_Volta = ordem_FreteBD.Master_inclui (edOrdemFrete);

            // System.out.println ("ORdem Frete incluiui OF=" + edOrdem_Volta.getOID_Ordem_Frete ());

            edOrdemFrete.setOID_Ordem_Frete (edOrdem_Volta.getOID_Ordem_Frete ());

            ordem_FreteBD.Master_rateio (edOrdemFrete);

            // System.out.println ("master rateio ok");
            qt_man++;
          }

        }
      }
      if (qt_man == 0) {
        throw new Exception ("Erro na Inclusao do Master, verifique parametros!");
      }

    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "gera_viagem_CtoAvulso(ViagemED ed)");
    }
    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "gera_viagem_CtoAvulso(ViagemED ed)");
    }
    return edViagem;
  }

}
