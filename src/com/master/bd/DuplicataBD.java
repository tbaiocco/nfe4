package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.master.ed.Auxiliar1ED;
import com.master.ed.ConhecimentoED;
import com.master.ed.DuplicataED;
import com.master.ed.Tipo_EventoED;
import com.master.rn.Auxiliar1RN;
import com.master.root.FormataDataBean;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;
import com.master.util.Utilitaria;
import com.master.util.Valor;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;

public class DuplicataBD {

  private ExecutaSQL executasql;
  DecimalFormat dec = new DecimalFormat ("000000000000");
  Parametro_FixoED parametro_FixoED = Parametro_FixoED.getInstancia ();
  BloquetoBD bloquetoBD = new BloquetoBD (executasql);
  private FormataDataBean dataFormatada = new FormataDataBean ();
  Utilitaria util = new Utilitaria (executasql);

  public DuplicataBD (ExecutaSQL sql) {
    this.executasql = sql;
    util = new Utilitaria (this.executasql);
  }

  public DuplicataED inclui (DuplicataED ed) throws Excecoes {

    String sql = null;
    long NR_Fatura = 0, valOid=0, NR_Duplicata=0;
    String oid_Moeda = "";
    String DM_Tipo_Documento = "1";
    ResultSet rsTP=null;
    try {

	  if(JavaUtil.doValida (ed.getNr_Documento())){
		  NR_Fatura = new Long( ed.getNr_Documento()).longValue();
	  }

      if (ed.getOid_Duplicata_Principal()==0 && !JavaUtil.doValida (ed.getNr_Documento())) { //quando o documento � informado ou vem de parcelamento
	        sql = " SELECT AIDOF.NR_Proximo, AIDOF.OID_AIDOF, AIDOF.NM_Serie ";
	        sql += " FROM Parametros_Filiais, AIDOF ";
	        sql += " WHERE Parametros_Filiais.OID_AIDOF_Fatura = AIDOF.OID_AIDOF ";
	        sql += " AND Parametros_Filiais.OID_Unidade = " + ed.getOid_Unidade ();
//System.out.println(sql);
	        rsTP = this.executasql.executarConsulta (sql);
	        while (rsTP.next ()) {
	        	NR_Fatura= (rsTP.getLong ("NR_Proximo"));
	        	valOid = rsTP.getLong ("OID_AIDOF");
	        }
      }

      NR_Duplicata = (NR_Fatura);  //nnnnnnpp 123401 nnn=nr dup1234 + 01 parcela 1


      oid_Moeda = util.getTableStringValue ("Contas_Correntes.oid_Moeda" ,
                                            "Carteiras, Contas_Correntes" ,
                                            "Carteiras.oid_Conta_Corrente = Contas_Correntes.oid_Conta_Corrente" +
                                            " AND Carteiras.oid_Carteira =" + ed.getOid_Carteira ());
      if (JavaUtil.doValida (oid_Moeda) && "1".equals (oid_Moeda)) {
        DM_Tipo_Documento = "1";
      }
      else {
        DM_Tipo_Documento = "2";
      }

      //*** Monta OID
      String chave = String.valueOf (String.valueOf (NR_Duplicata) + "0");  
      ed.setOid_Duplicata(new Long(chave).longValue());

      sql = " SELECT Duplicatas.OID_Duplicata " +
            " FROM Duplicatas " +
            " WHERE Duplicatas.oid_Duplicata = " + ed.getOid_Duplicata ();

        ResultSet rs = this.executasql.executarConsulta (sql);
        if (!rs.next ()) {

	      sql = " INSERT INTO Duplicatas (" +
	          "		OID_Duplicata" +
	          "		,NR_DOCUMENTO" +
	          "		,NR_PARCELA" +
	          "		,DT_EMISSAO" +
	          "		,NR_Bancario" +
	          "		,DT_VENCIMENTO" +
	          "		,VL_Duplicata" +
	          "		,VL_Taxa_Cobranca" +
	          "		,VL_Juro_Mora_Dia" +
	          "		,VL_Multa" +
	          "		,NR_Duplicata" +
	          "		,DT_STAMP" +
	          "		,USUARIO_STAMP" +
	          "		,DM_STAMP" +
	          "		,VL_Desconto_Faturamento" +
	          "		,VL_SALDO" +
	          "		,OID_TIPO_DOCUMENTO" +
	          "		,OID_MOEDA" +
	          "		,OID_Carteira" +
	          "		,OID_PESSOA" +
	          "		,OID_Vendedor" +
	          "		,OID_UNIDADE" +
	          "		,NR_Remessa" +
	          "		,DM_Tipo_Documento" +
	          "     ,DM_Tipo_Cobranca" +
	          "     ,DM_Tipo_Inclusao" +
	          "     ,DM_Situacao" +
	          "		,TX_Observacao, oid_Duplicata_Principal)" +
	          " VALUES (";
	      sql += ed.getOid_Duplicata () + ",";
	      sql += NR_Fatura + ",";
	      sql += ed.getNr_Parcela () == null ? "null," : ed.getNr_Parcela () + ",";
	      sql += "'" + ed.getDt_Emissao () + "',";
	      sql += "'" + ed.getNR_Bancario () + "',";
	      sql += "'" + ed.getDt_Vencimento () + "',";
	      sql += ed.getVl_Duplicata () + ",";
	      sql += ed.getVl_Taxa_Cobranca () == null ? "null," : ed.getVl_Taxa_Cobranca () + ",";
	      sql += ed.getVL_Juro_Mora_Dia () == null ? "null," : ed.getVL_Juro_Mora_Dia () + ",";
	      sql += ed.getVL_Multa () == null ? "null," : ed.getVL_Multa () + ",";
	      sql += NR_Duplicata + ",";
	      sql += "'" + ed.getDt_stamp () + "',";
	      sql += "'" + ed.getUsuario_Stamp () + "',";
	      sql += "'" + ed.getDm_Stamp () + "',";
	      sql += ed.getVl_Desconto_Faturamento () == null ? "null," : ed.getVl_Desconto_Faturamento () + ",";
	      sql += ed.getVl_Saldo () + ",";
	      sql += ed.getOid_Tipo_Documento () + ",";
	      sql += oid_Moeda + ",";
	      sql += ed.getOid_Carteira () + ",";
	      sql += "'" + ed.getOid_Pessoa () + "','";
	      sql += ed.getOid_Vendedor () + "',";
	      sql += ed.getOid_Unidade () + ",0,";
	      sql += "'" + DM_Tipo_Documento + "',";
	      sql += ed.getDM_Tipo_Cobranca () == null ? "'F'," : "'" + ed.getDM_Tipo_Cobranca () + "',";
	      sql += "'AVULSA',"; //tipo inclusao
	      sql += "'A',"; //sit
	      sql += "'" + ed.getTX_Observacao () + "', "  + ed.getOid_Duplicata_Principal()+ ")";

	      // System.out.println (sql);

	      executasql.executarUpdate (sql);

	      ed.setNr_Duplicata(new Integer(String.valueOf(NR_Fatura)));

	      if (valOid>0) {
		      sql = " UPDATE AIDOF SET NR_Proximo= " + (NR_Fatura + 1);
		      sql += " WHERE OID_AIDOF = " + valOid;
		      executasql.executarUpdate (sql);
	      }


        }

    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () ,
                          "inclui()");
    }
    return ed;
  }

  public DuplicataED getByRecord (DuplicataED ed) throws Excecoes {

    String sql = null;
    DuplicataED edVolta = new DuplicataED ();

    try {

      sql = " SELECT Duplicatas.*" +
      	  "	   	   ,Duplicatas.oid_Moeda " +
          "	   	   ,Pessoas.oid_pessoa" +
          "		   ,Pessoas.nr_cnpj_cpf" +
          "		   ,Pessoas.nm_razao_Social" +
          "	       ,Vendedores.oid_Vendedor" +
          "		   ,Vendedores.cd_Vendedor" +
          "		   ,Pessoas_Vendedores.nm_razao_Social as NM_Razao_Vendedor" +
          "		   ,Pessoas_Bancos.nm_razao_Social as NM_Razao_Banco" +
          "		   ,Pessoas_Bancos.oid_pessoa as banco" +
          "		   ,Carteiras.oid_Carteira" +
          "		   ,Carteiras.DM_Remete_EDI" +
          "		   ,Carteiras.NR_Dias_Liberacao_Cobranca" +
          "		   ,Carteiras.cd_Carteira" +
          " 	   ,Contas_Correntes.NR_conta_corrente" +
          " 	   ,Tipos_Documentos.oid_tipo_documento" +
          "		   ,Tipos_Documentos.cd_tipo_documento" +
          " 	   ,Tipos_Documentos.nm_tipo_documento" +
          " 	   ,Unidades.oid_unidade" +
          "		   ,Unidades.cd_unidade" +
          "		   ,Moedas.CD_Moeda" +
          "		   ,Moedas.oid_Moeda as oid_Moeda_CC" +
          " FROM Duplicatas" +
          "		 ,pessoas" +
          "		 ,Vendedores" +
          "		 ,Pessoas Pessoas_Vendedores" +
          "		 ,Pessoas Pessoas_Bancos" +
          "		 ,unidades" +
          "		 ,tipos_documentos" +
          "		 ,Carteiras" +
          "		 ,Contas_Correntes" +
          "		 ,Moedas " +
          " WHERE duplicatas.oid_pessoa = pessoas.oid_pessoa and" +
          " 	  duplicatas.oid_Carteira = Carteiras.oid_Carteira and" +
          " 	  duplicatas.oid_Vendedor = Vendedores.oid_Vendedor and" +
          " 	  Pessoas_Vendedores.oid_Pessoa = Vendedores.oid_Vendedor and" +
          " 	  duplicatas.oid_unidade = unidades.oid_unidade and" +
          " 	  duplicatas.oid_tipo_documento = tipos_documentos.oid_tipo_documento and" +
          " 	  carteiras.oid_conta_corrente = contas_correntes.oid_conta_corrente and " +
          " 	  contas_correntes.oid_pessoa = pessoas_bancos.oid_pessoa and" +
          " 	  contas_correntes.oid_Moeda = Moedas.oid_Moeda ";

      if (JavaUtil.doValida(String.valueOf(ed.getOid_Duplicata()))) {
        sql += " AND Duplicatas.oid_Duplicata = " + ed.getOid_Duplicata ();
      }
      if (ed.getNr_Duplicata () != null &&
          ed.getNr_Duplicata ().intValue () > 0) {
        sql += " AND Duplicatas.NR_Duplicata = " + ed.getNr_Duplicata ();
      }

      if (JavaUtil.doValida (ed.getNr_Documento ())) {
          sql += " and Duplicatas.NR_Documento = '" + ed.getNr_Documento () + "'";
      }


      ResultSet res = this.executasql.executarConsulta (sql);

      while (res.next ()) {
        edVolta = new DuplicataED ();

        edVolta.setDM_Tipo_Documento (res.getString ("DM_Tipo_Documento"));

        edVolta.setOid_Duplicata (res.getLong("oid_duplicata"));
        edVolta.setOid_Duplicata_Principal (res.getLong("oid_duplicata_principal"));
        edVolta.setOid_Pessoa (res.getString ("oid_pessoa"));
        edVolta.setCD_Moeda (res.getString ("CD_MOEDA"));
        edVolta.setOID_Moeda (new Integer (res.getInt ("oid_Moeda_CC")));
        edVolta.setNr_CNPJ_CPF (res.getString ("nr_cnpj_cpf"));
        edVolta.setNm_Razao_Social (res.getString ("nm_razao_Social"));

        edVolta.setOid_Protocolo_Entrega(res.getString ("Oid_Protocolo_Entrega"));

        edVolta.setOid_Vendedor (res.getString ("oid_Vendedor"));
        edVolta.setCd_Vendedor (res.getString ("cd_Vendedor"));
        edVolta.setNm_Vendedor (res.getString ("NM_Razao_Vendedor"));

        edVolta.setCD_Conta_Corrente (res.getString ("nr_Conta_Corrente"));
        edVolta.setCD_Banco(util.getTableStringValue("CD_Banco", "Bancos", "oid_Pessoa = '"+res.getString("banco")+"'"));
        edVolta.setNM_Banco (res.getString ("NM_Razao_Banco"));
        edVolta.setDM_Tipo_Inclusao (res.getString ("DM_Tipo_Inclusao"));
        edVolta.setDM_Quebra_Faturamento (res.getString ("DM_Quebra_Faturamento"));

        edVolta.setOid_Carteira (new Integer (res.getInt ("oid_Carteira")));
        edVolta.setCd_Carteira (res.getString ("cd_Carteira"));

        edVolta.setOid_Tipo_Documento (new Integer (res.getInt ("oid_tipo_documento")));
        edVolta.setCd_Tipo_Documento (res.getString ("cd_tipo_documento"));
        edVolta.setNm_Tipo_Documento (res.getString ("nm_tipo_documento"));

        String sql2 = "select NM_Razao_Social from pessoas, unidades where pessoas.oid_pessoa = unidades.oid_pessoa and " +
            " unidades.oid_unidade = "
            + Integer.parseInt (res.getString ("oid_unidade"));

        ResultSet resUni = this.executasql.executarConsulta (sql2);
        if (resUni != null) {
          resUni.next ();
        }

        edVolta.setOid_Unidade (new Long (res.getLong ("oid_unidade")));
        edVolta.setCd_Unidade (res.getString ("cd_unidade"));
        edVolta.setNm_Fantasia (resUni.getString ("NM_Razao_Social"));

        edVolta.setNr_Duplicata (new Integer (res.getInt ("nr_Duplicata")));

        edVolta.setNr_Parcela (new Integer (res.getInt ("nr_parcela")));

        FormataDataBean DataFormatada = new FormataDataBean ();
        DataFormatada.setDT_FormataData (res.getString ("dt_vencimento"));
        edVolta.setDt_Vencimento (DataFormatada.getDT_FormataData ());

        DataFormatada.setDT_FormataData (res.getString ("dt_credito"));
        edVolta.setDT_Credito (DataFormatada.getDT_FormataData ());

        if(!JavaUtil.doValida(res.getString ("dt_credito")))
        	edVolta.setDT_Credito(edVolta.getDt_Vencimento());

        edVolta.setDt_Fluxo(this.calculaDataFluxo(edVolta.getDT_Credito(), res.getInt("NR_Dias_Liberacao_Cobranca")));

        DataFormatada.setDT_FormataData (res.getString ("dt_emissao"));
        edVolta.setDt_Emissao (DataFormatada.getDT_FormataData ());
        edVolta.setNr_Duplicata (new Integer (res.getInt ("nr_duplicata")));
        edVolta.setNr_Documento (res.getString ("nr_documento"));
        //edVolta.setNr_Documento (res.getLong("nr_documento"));
        edVolta.setNR_Bancario (res.getString ("NR_Bancario"));

        edVolta.setNr_Parcela (new Integer (res.getInt ("nr_parcela")));

        edVolta.setTX_Observacao (res.getString ("TX_Observacao"));
        edVolta.setNM_Praca_Pagamento (res.getString ("NM_Praca_Pagamento"));

        String sqlLocal = "select oid_duplicata from origens_duplicatas where oid_duplicata = '" + edVolta.getOid_Duplicata () + "'";

        ResultSet resLocal = this.executasql.executarConsulta (sqlLocal);
        edVolta.setDM_Duplicata_Com_Origem ("N");
        while (resLocal.next ()) {
          edVolta.setDM_Duplicata_Com_Origem ("S");
        }

        edVolta.setDM_Remete_EDI (res.getString ("DM_Remete_EDI"));
        if ("N".equals (edVolta.getDM_Remete_EDI ())) {
          sqlLocal =
              "select oid_duplicata from instrucoes_duplicatas where oid_duplicata = '" + edVolta.getOid_Duplicata () + "'";
          resLocal = this.executasql.executarConsulta (sqlLocal);
          while (resLocal.next ()) {
            edVolta.setDM_Remete_EDI ("S");
          }
        }

        edVolta.setVl_Duplicata (new Double (res.getDouble ("vl_duplicata")));
        edVolta.setVl_Desconto_Faturamento (new Double (res.getDouble ("vl_Desconto_Faturamento")));
        edVolta.setVl_Desconto_Vencimento (new Double (res.getDouble ("vl_Desconto_Vencimento")));
        edVolta.setVl_Taxa_Cobranca (new Double (res.getDouble ("vl_Taxa_Cobranca")));
        edVolta.setVL_Juro_Mora_Dia (new Double (res.getDouble ("vl_Juro_Mora_Dia")));
        edVolta.setVL_Multa (new Double (res.getDouble ("vl_Multa")));
        edVolta.setVl_Saldo (new Double (res.getDouble ("vl_saldo")));

        edVolta.setDM_Atualiza_Saldo ("S");
        edVolta = this.correcaoMonetaria (edVolta);

        edVolta.setDM_Situacao (res.getString ("DM_Situacao"));

        edVolta.setNM_Situacao (consulta_Situacao (edVolta));

      }

      return edVolta;
    }
    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () ,
                          "getByRecord()");
    }
  }

  public DuplicataED correcaoMonetaria (DuplicataED ed) throws Excecoes {

    try {
      if (ed.getVl_Saldo ().doubleValue () > 0) {
        String data1 = ed.getDt_Vencimento ();
        String data2 = Data.getDataDMY ();
        Calendar cal1 = Data.stringToCalendar (data1 , "dd/MM/yyyy");
        Calendar cal2 = Data.stringToCalendar (data2 , "dd/MM/yyyy");
        double VL_Cotacao = 1;
        ed.setVl_Saldo_Atualizado (ed.getVl_Saldo ().doubleValue () + ed.getVl_Taxa_Cobranca ().doubleValue ());

        // System.out.println ("correcaoMonetaria 1");

        int NR_Dias_Atraso = 0;

        if (cal2.after (cal1)) {
          // System.out.println ("correcaoMonetaria 2");

          NR_Dias_Atraso = Data.diferencaDias (cal1 , cal2);
          double vl_Juro_Mora_Dia_Calculo = ed.getVL_Juro_Mora_Dia ().doubleValue ();

          // System.out.println ("correcaoMonetaria getVl_Saldo->" + ed.getVl_Saldo ().doubleValue ());
          // System.out.println ("correcaoMonetaria getVl_Duplicata->" + ed.getVl_Duplicata ().doubleValue ());
          // System.out.println ("correcaoMonetaria getVL_Juro_Mora_Dia->" + ed.getVL_Juro_Mora_Dia ().doubleValue ());

          if (ed.getVl_Saldo ().doubleValue () < ed.getVl_Duplicata ().doubleValue ()) {
            vl_Juro_Mora_Dia_Calculo = (ed.getVl_Saldo ().doubleValue () * ed.getVL_Juro_Mora_Dia ().doubleValue ()) / ed.getVl_Duplicata ().doubleValue ();
          }

          // System.out.println ("correcaoMonetaria 4");

          double vl_Multa = ed.getVL_Multa ().doubleValue ();
          ed.setVL_Juros (vl_Multa + ( ( (vl_Juro_Mora_Dia_Calculo) * NR_Dias_Atraso)));
          if (ed.getVL_Juros () >= 0) {
            ed.setVl_Saldo_Atualizado (ed.getVl_Saldo ().doubleValue () + ed.getVL_Juros ());
          }
        }

        // System.out.println ("correcaoMonetaria 5");

        ed.setNR_Dias_Atraso (NR_Dias_Atraso);

        if ("S".equals (parametro_FixoED.getDM_Multi_Moeda ())) {
          if ("S".equals (ed.getDM_Atualiza_Saldo ()) && parametro_FixoED.getOID_Moeda_Padrao () != ed.getOID_Moeda ().intValue ()) {
            if (ed.getVL_Cotacao_Informada () > 0) {
              VL_Cotacao = ed.getVL_Cotacao_Informada ();
            }
            else {
            }
            if (VL_Cotacao <= 0) {
              VL_Cotacao = 1;
            }
            ed.setVL_Cotacao_Emissao (VL_Cotacao);
            ed.setVl_Saldo_Atualizado (ed.getVl_Saldo_Atualizado () * VL_Cotacao);
          }
        }
      }
      return ed;
    }
    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "calculaJuros()");
    }
  }

  public String calculaDataFluxo (String dt_credito, int nr_dias_liberacao_cobranca) throws Exception {
		  String dt_fluxo="";
		  int soma_dia=0;
		  dt_credito = Data.getSomaDiaData (dt_credito , soma_dia);
		  soma_dia=0;
		  int dias_lidos=0;
		  while ( dias_lidos<nr_dias_liberacao_cobranca) {
			  dias_lidos++;
			  dt_credito = Data.getSomaDiaData (dt_credito , 1);
		  }
		  dt_fluxo = Data.getSomaDiaData (dt_credito , soma_dia);
		  return dt_fluxo;
  }

  public DuplicataED calculaSaldo (DuplicataED ed) throws Excecoes {

    try {

      double vl_emissao = 0;
      double vl_pagamento = 0;
      double vl_saldo_calculado = (ed.getVL_Saldo_Atual () - ed.getVL_Pago ());
      double vl_liquido = (ed.getVL_Pago () - ed.getVL_Tarifa () - ed.getVL_Desconto () - ed.getVL_Imposto_Retido1 () - ed.getVL_Imposto_Retido2 () + ed.getVL_Juros ());
      double vl_variacao_cambial = 0;

      ed.setVL_Saldo_Calculado (vl_saldo_calculado);
      ed.setVL_Liquido (vl_liquido);
      ed.setVL_Variacao_Cambial_Ativa (0);
      ed.setVL_Variacao_Cambial_Passiva (0);
      ed.setVL_Variacao_Cambial (0);
      ed.setVL_Pago (ed.getVL_Pago ());

      if (ed.getOID_Moeda ().intValue () != parametro_FixoED.getOID_Moeda_Padrao ()) {
        if (ed.getVL_Cotacao_Emissao () == 0) {
        }
        vl_emissao = ed.getVL_Pago () * ed.getVL_Cotacao_Emissao ();

        if (ed.getVL_Cotacao_Pagamento () == 0) {
        }
        vl_pagamento = ed.getVL_Pago () * ed.getVL_Cotacao_Pagamento ();
        vl_variacao_cambial = vl_pagamento - vl_emissao;
        ed.setVL_Variacao_Cambial (vl_variacao_cambial);

        if (vl_variacao_cambial > 0) {
          ed.setVL_Variacao_Cambial_Ativa (vl_variacao_cambial);
        }
        if (vl_variacao_cambial < 0) {
          ed.setVL_Variacao_Cambial_Passiva (vl_variacao_cambial * -1);
        }
      }
      return ed;
    }
    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "calculaSaldo()");
    }
  }

  public DuplicataED getByRecordByMoeda (DuplicataED ed) throws Excecoes {

    String sql = null;
    DuplicataED edVolta = new DuplicataED ();

    try {

      sql = " SELECT Duplicatas.OID_Duplicata" +
          "	   	   ,Pessoas.oid_pessoa" +
          "		   ,Pessoas.nr_cnpj_cpf" +
          "		   ,Pessoas.nm_razao_Social" +
          "	       ,Vendedores.oid_Vendedor" +
          "		   ,Vendedores.cd_Vendedor" +
          "		   ,Pessoas_Vendedores.nm_razao_Social as NM_Razao_Vendedor" +
          "		   ,Pessoas_Bancos.nm_razao_Social as NM_Razao_Banco" +
          "		   ,Carteiras.oid_Carteira" +
          "		   ,Carteiras.cd_Carteira" +
          " 	   ,Contas_Correntes.NR_conta_corrente" +
          " 	   ,Tipos_Documentos.oid_tipo_documento" +
          "		   ,Tipos_Documentos.cd_tipo_documento" +
          " 	   ,Tipos_Documentos.nm_tipo_documento" +
          " 	   ,Unidades.oid_unidade" +
          "		   ,Unidades.cd_unidade" +
          " 	   ,Duplicatas.nr_Duplicata" +
          "		   ,Duplicatas.nr_parcela" +
          "		   ,Duplicatas.DM_Quebra_Faturamento" +
          " 	   ,Duplicatas.nr_documento" +
          "		   ,Duplicatas.dt_vencimento" +
          " 	   ,Duplicatas.vl_saldo" +
          "		   ,Duplicatas.dt_emissao" +
          " 	   ,Duplicatas.vl_duplicata" +
          "		   ,Duplicatas.vl_Desconto_Faturamento" +
          "	 	   ,Duplicatas.vl_Taxa_Cobranca" +
          "		   ,Duplicatas.vl_Multa" +
          " 	   ,Duplicatas.vl_Juro_Mora_Dia" +
          "		   ,Duplicatas.NR_Bancario" +
          "		   ,Duplicatas.TX_Observacao" +
          "		   ,Moedas.CD_Moeda" +
          "		   ,Moedas.oid_Moeda " +
          " FROM Duplicatas" +
          "		 ,pessoas" +
          "		 ,Vendedores" +
          "		 ,Pessoas Pessoas_Vendedores" +
          "		 ,Pessoas Pessoas_Bancos" +
          "		 ,unidades" +
          "		 ,tipos_documentos" +
          "		 ,Carteiras" +
          "		 ,Contas_Correntes" +
          "		 ,Moedas " +
          "      ,Borderos " +
          " WHERE duplicatas.oid_pessoa = pessoas.oid_pessoa and" +
          " 	  duplicatas.oid_Carteira = Carteiras.oid_Carteira and" +
          " 	  duplicatas.oid_Vendedor = Vendedores.oid_Vendedor and" +
          " 	  Pessoas_Vendedores.oid_Pessoa = Vendedores.oid_Vendedor and" +
          " 	  duplicatas.oid_unidade = unidades.oid_unidade and" +
          " 	  duplicatas.oid_tipo_documento = tipos_documentos.oid_tipo_documento and" +
          " 	  carteiras.oid_conta_corrente = contas_correntes.oid_conta_corrente and " +
          " 	  contas_correntes.oid_pessoa = pessoas_bancos.oid_pessoa and" +
          " 	  contas_correntes.oid_Moeda = Moedas.oid_Moeda and" +
          "       borderos.oid_carteira = duplicatas.oid_carteira ";

      if (ed.getOidBordero () > 0) {
        sql += " and borderos.oid_bordero = " + ed.getOidBordero ();
      }
      if (JavaUtil.doValida(String.valueOf(ed.getOid_Duplicata()))) {
        sql += " and duplicatas.oid_Duplicata = " + ed.getOid_Duplicata ();
      }
      if (ed.getNr_Duplicata () != null &&
          ed.getNr_Duplicata ().intValue () > 0) {
        sql += " and duplicatas.NR_Duplicata = " + ed.getNr_Duplicata ();
      }

      ResultSet res = this.executasql.executarConsulta (sql);

      while (res.next ()) {
        edVolta = new DuplicataED ();
        edVolta.setOid_Duplicata (res.getLong("oid_duplicata"));
        edVolta.setOid_Pessoa (res.getString ("oid_pessoa"));
        edVolta.setCD_Moeda (res.getString ("CD_MOEDA"));
        edVolta.setOID_Moeda (new Integer (res.getInt ("OID_Moeda")));
        edVolta.setNr_CNPJ_CPF (res.getString ("nr_cnpj_cpf"));
        edVolta.setNm_Razao_Social (res.getString ("nm_razao_Social"));

        edVolta.setOid_Vendedor (res.getString ("oid_Vendedor"));
        edVolta.setCd_Vendedor (res.getString ("cd_Vendedor"));
        edVolta.setNm_Vendedor (res.getString ("NM_Razao_Vendedor"));

        edVolta.setCD_Conta_Corrente (res.getString ("nr_Conta_Corrente"));
        edVolta.setNM_Banco (res.getString ("NM_Razao_Banco"));
        edVolta.setDM_Quebra_Faturamento (res.getString (
            "DM_Quebra_Faturamento"));

        edVolta.setOid_Carteira (new Integer (res.getInt ("oid_Carteira")));
        edVolta.setCd_Carteira (res.getString ("cd_Carteira"));

        edVolta.setTX_Observacao (res.getString ("TX_Observacao"));

        edVolta.setOid_Tipo_Documento (new Integer (res.getInt (
            "oid_tipo_documento")));
        edVolta.setCd_Tipo_Documento (res.getString ("cd_tipo_documento"));
        edVolta.setNm_Tipo_Documento (res.getString ("nm_tipo_documento"));

        String sql2 = "select nm_fantasia from pessoas, unidades where pessoas.oid_pessoa = unidades.oid_pessoa and " +
            " unidades.oid_unidade = "
            + Integer.parseInt (res.getString ("oid_unidade"));

        ResultSet resUni = this.executasql.executarConsulta (sql2);
        if (resUni != null) {
          resUni.next ();
        }

        edVolta.setOid_Unidade (new Long (res.getLong ("oid_unidade")));
        edVolta.setCd_Unidade (res.getString ("cd_unidade"));
        edVolta.setNm_Fantasia (resUni.getString ("nm_fantasia"));

        edVolta.setNr_Duplicata (new Integer (res.getInt ("nr_Duplicata")));
        edVolta.setNr_Parcela (new Integer (res.getInt ("nr_parcela")));

        FormataDataBean DataFormatada = new FormataDataBean ();
        DataFormatada.setDT_FormataData (res.getString ("dt_vencimento"));
        edVolta.setDt_Vencimento (DataFormatada.getDT_FormataData ());
        DataFormatada.setDT_FormataData (res.getString ("dt_emissao"));
        edVolta.setDt_Emissao (DataFormatada.getDT_FormataData ());
        edVolta.setNr_Duplicata (new Integer (res.getInt ("nr_duplicata")));
        edVolta.setNr_Documento (res.getString ("nr_documento"));
        //edVolta.setNr_Documento (res.getLong("nr_documento"));
        edVolta.setNR_Bancario (res.getString ("NR_Bancario"));

        edVolta.setNr_Parcela (new Integer (res.getInt ("nr_parcela")));

        edVolta.setVl_Duplicata (new Double (res.getDouble ("vl_duplicata")));
        edVolta.setVl_Desconto_Faturamento (new Double (res.getDouble (
            "vl_Desconto_Faturamento")));
        edVolta.setVl_Taxa_Cobranca (new Double (res.getDouble (
            "vl_Taxa_Cobranca")));
        edVolta.setVL_Juro_Mora_Dia (new Double (res.getDouble (
            "vl_Juro_Mora_Dia")));
        edVolta.setVL_Multa (new Double (res.getDouble ("vl_Multa")));
        edVolta.setVl_Saldo (new Double (res.getDouble ("vl_saldo")));
        edVolta.setVl_Saldo_Atualizado (0.0);

        String data1 = edVolta.getDt_Vencimento ();
        String data2 = Data.getDataDMY ();
        Calendar cal1 = Data.stringToCalendar (data1 , "dd/MM/yyyy");
        Calendar cal2 = Data.stringToCalendar (data2 , "dd/MM/yyyy");
        if (cal2.after (cal1)) {
          int iDifDias = Data.diferencaDias (cal1 , cal2);
          double vl_Juro_Mora_Dia_Calculo = edVolta.getVL_Juro_Mora_Dia ().
              doubleValue ();
          double vl_Saldo_Calculado = ( ( ( (vl_Juro_Mora_Dia_Calculo / 30) /
                                           100) * iDifDias) + 1);
          if (vl_Saldo_Calculado >= 0) {
            edVolta.setVl_Saldo_Atualizado (edVolta.getVl_Saldo ().doubleValue () *
                                            vl_Saldo_Calculado);
          }
        }
      }
      return edVolta;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
                          "getByRecordByMoeda()");
    }
  }

  public void altera (DuplicataED ed) throws Excecoes {

    String sql = null;

    try {

      sql = " UPDATE Duplicatas SET ";
      sql += "dt_vencimento = '" + ed.getDt_Vencimento () + "',";

      if (ed.getDT_Credito () == null) {
        sql += "dt_credito = null,";
      }
      else {
        sql += "dt_credito = '" + ed.getDT_Credito () + "',";
      }

      sql += "TX_Observacao = '" + ed.getTX_Observacao () + "',";

      if (ed.getVl_Taxa_Cobranca () == null) {
        sql += "vl_Taxa_Cobranca = null,";
      }
      else {
        sql += "vl_Taxa_Cobranca = " + ed.getVl_Taxa_Cobranca () + ",";
      }

      if (ed.getVL_Juro_Mora_Dia () == null) {
        sql += "vl_Juro_Mora_Dia = null,";
      }
      else {
        sql += "vl_Juro_Mora_Dia = " + ed.getVL_Juro_Mora_Dia () + ",";
      }

      if (ed.getVL_Multa () == null) {
        sql += "vl_Multa = null,";
      }
      else {
        sql += "vl_Multa = " + ed.getVL_Multa () + ",";
      }

      if (ed.getNR_Bancario () == null) {
        sql += "NR_Bancario = null,";
      }
      else {
        sql += "NR_Bancario = '" + ed.getNR_Bancario () + "',";
      }

      sql += " DT_STAMP = '" + ed.getDt_stamp () + "', ";
      sql += " USUARIO_STAMP = '" + ed.getUsuario_Stamp () + "', ";
      sql += " DM_STAMP = '" + ed.getDm_Stamp () + "', ";

      if (ed.getVl_Desconto_Faturamento () == null) {
        sql += "null,";
      }
      else {
        sql += "Vl_Desconto_Faturamento = " + ed.getVl_Desconto_Faturamento () +
            ",";
      }

      if (JavaUtil.doValida (ed.getNM_Praca_Pagamento ())) {
        sql += "nm_praca_pagamento = '" + ed.getNM_Praca_Pagamento () + "',";
      }

      sql += "oid_tipo_documento = " + ed.getOid_Tipo_Documento () + ",";
      sql += "oid_Carteira = " + ed.getOid_Carteira () + ",";
      sql += "oid_Vendedor='" + ed.getOid_Vendedor () + "',";
      sql += "oid_unidade=" + ed.getOid_Unidade ();

      ///sql += "vl_saldo=" + ed.getVl_Saldo ();

      sql += " WHERE oid_duplicata = " + ed.getOid_Duplicata ();

      // System.out.println (sql);
      executasql.executarUpdate (sql);
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () ,
                          "altera()");
    }
  }

  public void deleta (DuplicataED ed) throws Excecoes {

    String sql = null;
    double vl_duplicata = 0;

    try {
      sql = " SELECT * FROM movimentos_duplicatas, tipos_instrucoes " +
          " WHERE  movimentos_duplicatas.oid_tipo_instrucao = tipos_instrucoes.oid_tipo_instrucao " +
          " AND    movimentos_duplicatas.oid_duplicata = " + ed.getOid_Duplicata () +
          " AND    (tipos_instrucoes.dm_altera_titulo = 'LTO' or tipos_instrucoes.dm_altera_titulo = 'LCA' or tipos_instrucoes.dm_altera_titulo = 'LPA') ";
      ResultSet res = this.executasql.executarConsulta (sql);
      if (res.next ()) {
        throw new Excecoes ("T�tulo com Movimento de Liquida��o!" , getClass ().getName () , "deleta()");
      }
      sql = " SELECT nr_remessa, vl_duplicata from duplicatas " +
          " WHERE  oid_duplicata = " + ed.getOid_Duplicata ();
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        vl_duplicata = res.getDouble ("vl_duplicata");
        if (JavaUtil.doValida (res.getString ("nr_remessa"))) {
          throw new Excecoes ("T�tulo j� Remetido via EDI-Banco!" , getClass ().getName () , "deleta()");
        }
      }

      sql = "DELETE FROM Ocorrencias_Duplicatas WHERE oid_Duplicata = ";
      sql += ed.getOid_Duplicata ();
      executasql.executarUpdate (sql);

      sql = "DELETE FROM Movimentos_Duplicatas WHERE oid_Duplicata = ";
      sql += ed.getOid_Duplicata ();
      executasql.executarUpdate (sql);

      sql = "DELETE FROM Duplicatas WHERE oid_Duplicata = ";
      sql += ed.getOid_Duplicata ();
      executasql.executarUpdate (sql);
    }
    catch (Excecoes e) {
      throw e;
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "deleta()");
    }
    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "deleta()");
    }
  }

  public DuplicataED incluiParcela (DuplicataED ed) throws Excecoes {

    try {
      //*** Referencia da PARCELA

      DuplicataED edPrincipal = new DuplicataED ();
      edPrincipal.setOid_Duplicata(ed.getOid_Duplicata());
      edPrincipal = this.getByRecord(edPrincipal);
      ResultSet res = this.executasql.executarConsulta (" SELECT MAX (NR_Parcela) as NR_Parcela FROM Duplicatas WHERE NR_Documento=" + edPrincipal.getNr_Documento());
      if (res.next ()) {
    	  int nr_parcela = res.getInt("NR_Parcela")+1;
    	  ed.setNr_Parcela (new Integer(nr_parcela));
	      ed.setDm_Numeracao_Automatica ("N");
	      ed.setOid_Duplicata_Principal(edPrincipal.getOid_Duplicata());
          double vl_saldo=edPrincipal.getVl_Saldo().doubleValue() - ed.getVl_Saldo().doubleValue();
          if (vl_saldo>0) {
    	      ed = this.inclui (ed);

        	  //*** Vincula Duplicata Origem na Nova Parcela
        	  executasql.executarUpdate (" UPDATE Duplicatas SET" +
	                                  "       oid_Duplicata_Parcela = " + edPrincipal.getOid_Duplicata() +
	                                  " WHERE oid_Duplicata = " + ed.getOid_Duplicata ());

        	  //*** Calcula Saldo da Duplicata Origem
        	  executasql.executarUpdate (" UPDATE Duplicatas SET VL_Saldo = " + vl_saldo + " WHERE oid_Duplicata = " + edPrincipal.getOid_Duplicata());
          }

      }

       return ed;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "incluiParcela()");
    }
  }

  public DuplicataED cancelaParcela (DuplicataED ed) throws Excecoes {

	    try {
	      //*** Referencia da PARCELA
	      ed = this.getByRecord(ed);
	      if (ed.getVl_Saldo().doubleValue()>0) {
	          DuplicataED edPrincipal = new DuplicataED ();
	          edPrincipal.setOid_Duplicata(ed.getOid_Duplicata_Principal());
	          edPrincipal = this.getByRecord(edPrincipal);
	          double vl_saldo=edPrincipal.getVl_Saldo().doubleValue() + ed.getVl_Saldo().doubleValue();
		       executasql.executarUpdate (" UPDATE Duplicatas SET" +
                       "     DM_Situacao='C', VL_Saldo=0, TX_Observacao='Parcela Cancelada' "+
                       " WHERE oid_Duplicata = " + ed.getOid_Duplicata ());

		       executasql.executarUpdate (" UPDATE Duplicatas SET VL_Saldo = " + vl_saldo + " WHERE oid_Duplicata = " + edPrincipal.getOid_Duplicata());


	      }

	      return ed;
	    }
	    catch (Exception exc) {
	      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "incluiParcela()");
	    }
	  }

  public ArrayList listaParcela (DuplicataED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    try {
      sql = " SELECT * FROM Duplicatas " +
          " WHERE oid_Duplicata_Parcela = " + ed.getOid_Duplicata () +
          " ORDER BY NR_Parcela, DT_Vencimento ";
      ResultSet res = this.executasql.executarConsulta (sql);

      while (res.next ()) {
        DuplicataED edVolta = new DuplicataED ();

        edVolta.setOid_Duplicata (res.getLong("oid_Duplicata"));
        edVolta.setNr_Duplicata (new Integer (res.getInt ("nr_Duplicata")));
        edVolta.setNr_Documento (res.getString ("nr_documento"));
        //edVolta.setNr_Documento (res.getLong("Nr_Documento"));
        edVolta.setNr_Parcela (new Integer (res.getInt ("nr_parcela")));

        edVolta.setDt_Vencimento (new FormataDataBean ().getDT_FormataData (res.getString ("dt_vencimento")));
        edVolta.setVl_Saldo (new Double (res.getDouble ("vl_saldo")));
        edVolta.setVl_Duplicata (new Double (res.getDouble ("Vl_Duplicata")));
        edVolta.setDt_Emissao (new FormataDataBean ().getDT_FormataData (res.getString ("dt_emissao")));
        list.add (edVolta);
      }
      return list;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "listaParcela()");
    }
  }

  public DuplicataED excluiParcela (DuplicataED ed) throws Excecoes {

    try {

      String sql = "";
      double VL_Duplicata = 0;
      double VL_Saldo = 0;

      sql = " SELECT Duplicatas.oid_Duplicata_Parcela,  Duplicatas.VL_Duplicata " +
          " FROM   Duplicatas " +
          " WHERE  Duplicatas.oid_Duplicata = " + ed.getOid_Duplicata ();
      // System.out.println (sql);

      ResultSet res = this.executasql.executarConsulta (sql);
      if (res.next ()) {
        VL_Duplicata = res.getDouble ("VL_Duplicata");

        sql = " SELECT Duplicatas.VL_Saldo " +
            " FROM   Duplicatas " +
            " WHERE  Duplicatas.oid_Duplicata = " + res.getLong ("oid_Duplicata_Parcela");
        // System.out.println (sql);

        res = this.executasql.executarConsulta (sql);
        if (res.next ()) {
          VL_Saldo = res.getDouble ("VL_Saldo") + VL_Duplicata;
          sql = " UPDATE Duplicatas SET VL_Saldo = " + VL_Saldo +
              " WHERE oid_Duplicata = " + res.getLong ("oid_Duplicata_Parcela");

          // System.out.println (sql);
          executasql.executarUpdate (sql);

          sql = " DELETE FROM Duplicatas WHERE oid_Duplicata = " + ed.getOid_Duplicata ();
          // System.out.println (sql);
          executasql.executarUpdate (sql);
        }
      }
      return ed;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "incluiParcela()");
    }
  }

  public ArrayList lista (DuplicataED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();

    try {
      sql = " SELECT " +
          " 	 Duplicatas.*, " +
          "		 Pessoas.oid_pessoa, " +
          "		 Pessoas.nr_cnpj_cpf, " +
          "		 Pessoas.nm_razao_Social, " +
          "		 Pessoas_Bancos.nm_razao_Social as NM_Razao_Banco, " +
          "		 Carteiras.oid_Carteira, " +
          "		 Carteiras.cd_Carteira, " +
          "		 Unidades.cd_Unidade, " +
          "		 Contas_Correntes.NR_conta_corrente, " +
          "		 Tipos_Documentos.oid_tipo_documento, " +
          "		 Tipos_Documentos.cd_tipo_documento, " +
          "		 Tipos_Documentos.nm_tipo_documento  " +
          " FROM Duplicatas, pessoas, Pessoas Pessoas_Bancos, Unidades, tipos_documentos, Carteiras, " +
          "      Contas_Correntes ";
      if (JavaUtil.doValida (ed.getNR_Conhecimento ())) {
          sql += ", Origens_Duplicatas, Conhecimentos ";
      }
      sql += " WHERE Duplicatas.oid_pessoa = pessoas.oid_pessoa " +
          " AND   Duplicatas.oid_Carteira = Carteiras.oid_Carteira " +
          " AND   Duplicatas.oid_Unidade = Unidades.oid_Unidade " +
          " AND   Duplicatas.oid_tipo_documento = tipos_documentos.oid_tipo_documento " +
          " AND   carteiras.oid_conta_corrente = contas_correntes.oid_conta_corrente " +
          " AND   contas_correntes.oid_pessoa = pessoas_bancos.oid_pessoa ";

      if (ed.getNr_Duplicata () != null &&
          ed.getNr_Duplicata ().intValue () > 0) {
        sql += " and Duplicatas.nr_duplicata >= " + ed.getNr_Duplicata ();
      }
      if (ed.getNr_Duplicata_Final () == null ||
          ed.getNr_Duplicata_Final ().intValue () <= 0) {
        ed.setNr_Duplicata_Final (ed.getNr_Duplicata ());
      }
      if (ed.getNr_Duplicata_Final () != null &&
          ed.getNr_Duplicata_Final ().intValue () > 0) {
        sql += " and Duplicatas.nr_duplicata <= " + ed.getNr_Duplicata_Final ();
      }
      if (JavaUtil.doValida (ed.getNr_Documento ())) {
        sql += " and Duplicatas.NR_Documento = '" + ed.getNr_Documento () + "'";
      }
      if (JavaUtil.doValida (ed.getOid_Pessoa ())) {
        sql += " and Duplicatas.oid_pessoa = '" + ed.getOid_Pessoa () + "'";
      }

      if (ed.getOid_Carteira () != null &&
          ed.getOid_Carteira ().intValue () > 0) {
        sql += " and Duplicatas.oid_carteira = '" + ed.getOid_Carteira () + "'";
      }
      if (JavaUtil.doValida (ed.getDT_Emissao_Inicial ())) {
        sql += " and Duplicatas.dt_emissao >= '" + ed.getDT_Emissao_Inicial () +
            "'";
      }
      if (JavaUtil.doValida (ed.getDT_Emissao_Final ())) {
        sql += " and Duplicatas.dt_emissao <= '" + ed.getDT_Emissao_Final () +
            "'";
      }

      if (ed.getVL_Titulo_Inicial () > 0) {
        sql += " and Duplicatas.VL_Saldo >=" + ed.getVL_Titulo_Inicial ();
      }
      if (ed.getVL_Titulo_Final () > 0) {
        sql += " and Duplicatas.VL_Saldo <=" + ed.getVL_Titulo_Final ();
      }

      if (JavaUtil.doValida (ed.getDT_Vencimento_Inicial ())) {
        sql += " and Duplicatas.dt_vencimento >= '" +
            ed.getDT_Vencimento_Inicial () + "'";
      }
      if (JavaUtil.doValida (ed.getDT_Vencimento_Final ())) {
        sql += " and Duplicatas.dt_vencimento <= '" + ed.getDT_Vencimento_Final () +
            "'";
      }
      if (JavaUtil.doValida (ed.getNm_Razao_Social ())) {
        sql += " and pessoas.nm_razao_social like '" + ed.getNm_Razao_Social () +
            "%'";
      }

      if (JavaUtil.doValida (ed.getNR_Conhecimento ())) {
        sql += " AND Duplicatas.oid_duplicata = origens_Duplicatas.oid_duplicata ";
        sql += " AND Conhecimentos.oid_Conhecimento = origens_Duplicatas.oid_Conhecimento ";
        sql += " AND Conhecimentos.NR_Conhecimento = " + ed.getNR_Conhecimento ();
      }

      if ("C".equals (ed.getDM_Situacao ())) { //cancelados
        sql += " AND Duplicatas.DM_Situacao='C' ";
      }
      else {
        sql += " AND Duplicatas.DM_Situacao<>'C' ";

        //*** Duplicatas Liquidadas ou em Aberto
         if ("P".equals (ed.getDM_Situacao ())) {
           sql += " AND Duplicatas.VL_Saldo <= 0";
         }
         else if ("A".equals (ed.getDM_Situacao ())) {
           sql += " AND Duplicatas.VL_Saldo > 0";
         }
      }

      sql += " ORDER BY Duplicatas.nr_duplicata, Duplicatas.DT_Vencimento ";
      // System.out.println (sql);
      ResultSet res = this.executasql.executarConsulta (sql);

      //popula
      while (res.next ()) {
        DuplicataED edVolta = new DuplicataED ();

        edVolta.setOid_Duplicata(res.getLong("oid_duplicata"));
        edVolta.setOid_Pessoa (res.getString ("oid_pessoa"));
        edVolta.setNr_CNPJ_CPF (res.getString ("nr_cnpj_cpf"));
        edVolta.setNm_Razao_Social (res.getString ("nm_razao_Social"));

        edVolta.setCD_Conta_Corrente (res.getString ("nr_Conta_Corrente"));
        edVolta.setNM_Banco (res.getString ("NM_Razao_Banco"));
        edVolta.setCd_Unidade(res.getString ("Cd_Unidade"));

        edVolta.setOID_Moeda (new Integer (res.getInt ("oid_Moeda")));

        edVolta.setOid_Carteira (new Integer (res.getInt ("oid_Carteira")));
        edVolta.setCd_Carteira (res.getString ("cd_Carteira"));

        edVolta.setOid_Tipo_Documento (new Integer (res.getInt (
            "oid_tipo_documento")));
        edVolta.setCd_Tipo_Documento (res.getString ("cd_tipo_documento"));
        edVolta.setNm_Tipo_Documento (res.getString ("nm_tipo_documento"));

        edVolta.setNr_Duplicata (new Integer (res.getInt ("nr_Duplicata")));
        edVolta.setNr_Parcela (new Integer (res.getInt ("nr_parcela")));

        FormataDataBean DataFormatada = new FormataDataBean ();
        DataFormatada.setDT_FormataData (res.getString ("dt_vencimento"));
        edVolta.setDt_Vencimento (DataFormatada.getDT_FormataData ());

        DataFormatada.setDT_FormataData (res.getString ("dt_emissao"));
        edVolta.setDt_Emissao (DataFormatada.getDT_FormataData ());

        edVolta.setNr_Documento (res.getString ("nr_documento"));
        //edVolta.setNr_Documento (res.getLong("nr_documento"));
        edVolta.setNr_Parcela (new Integer (res.getInt ("nr_parcela")));
        edVolta.setNR_Bancario (res.getString ("nr_bancario"));

        edVolta.setVl_Duplicata (new Double (res.getDouble ("vl_duplicata")));
        edVolta.setVl_Desconto_Faturamento (new Double (res.getDouble ("vl_Desconto_Faturamento")));
        edVolta.setVl_Taxa_Cobranca (new Double (res.getDouble ("vl_Taxa_Cobranca")));
        edVolta.setVL_Juro_Mora_Dia (new Double (res.getDouble ("vl_Juro_Mora_Dia")));
        edVolta.setVL_Multa (new Double (res.getDouble ("vl_Multa")));
        edVolta.setVl_Saldo (new Double (res.getDouble ("vl_saldo")));

        edVolta.setDM_Atualiza_Saldo ("S");
        edVolta = this.correcaoMonetaria (edVolta);

        edVolta.setDM_Situacao (res.getString ("DM_Situacao"));

        edVolta.setNM_Situacao (consulta_Situacao (edVolta));

        list.add (edVolta);
      }
      return list;
    }
    catch (Exception exc) {
      exc.printStackTrace ();
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "lista()");
    }
  }

  //*** Duplicatas Relacionadas a Nota Fiscal
   public ArrayList listaByNota (DuplicataED ed) throws Excecoes {

     String sql = null;
     ArrayList list = new ArrayList ();

     try {

       sql = " SELECT Duplicatas.OID_Duplicata, " +
           "      Pessoas.oid_pessoa, " +
           "      Pessoas.nr_cnpj_cpf, " +
           "      Pessoas.nm_razao_Social, " +
           "      Pessoas_Bancos.nm_razao_Social as NM_Razao_Banco, " +
           "      Carteiras.oid_Carteira, " +
           "      Carteiras.cd_Carteira, " +
           "      Contas_Correntes.NR_conta_corrente, " +
           "      Tipos_Documentos.oid_tipo_documento, " +
           "      Tipos_Documentos.cd_tipo_documento, " +
           "      Tipos_Documentos.nm_tipo_documento, " +
           "      Duplicatas.nr_Duplicata, " +
           "      Duplicatas.nr_parcela, " +
           "      Duplicatas.nr_documento, " +
           "      Duplicatas.dt_vencimento, " +
           "      Duplicatas.vl_saldo, " +
           "      Duplicatas.dt_emissao, " +
           "      Duplicatas.vl_duplicata, " +
           "      Duplicatas.vl_Desconto_Faturamento, " +
           "      Duplicatas.vl_Taxa_Cobranca, " +
           "      Duplicatas.vl_Multa, " +
           "      Duplicatas.vl_Juro_Mora_Dia, " +
           "      Duplicatas.vl_duplicata " +
           " FROM Duplicatas, pessoas, Pessoas Pessoas_Bancos, tipos_documentos, Carteiras, " +
           "      Contas_Correntes, Origens_Duplicatas " +
           " WHERE Duplicatas.oid_pessoa = pessoas.oid_pessoa " +
           "   AND Duplicatas.oid_Carteira = Carteiras.oid_Carteira " +
           "   AND Duplicatas.oid_tipo_documento = tipos_documentos.oid_tipo_documento " +
           "   AND carteiras.oid_conta_corrente = contas_correntes.oid_conta_corrente " +
           "   AND contas_correntes.oid_pessoa = pessoas_bancos.oid_pessoa " +
           "   AND (Duplicatas.oid_Duplicata = Origens_Duplicatas.oid_Duplicata OR Origens_Duplicatas.oid_Duplicata = Duplicatas.oid_Duplicata_Parcela)" +
           "   AND Origens_Duplicatas.oid_Nota_Fiscal = '" + ed.getOid_Nota_Fiscal () + "'" +
           "   AND Duplicatas.VL_Saldo > 0";
       sql += " ORDER BY Duplicatas.nr_duplicata, Duplicatas.DT_Vencimento ";

       ResultSet res = this.executasql.executarConsulta (sql);
       while (res.next ()) {
         DuplicataED edVolta = new DuplicataED ();

         edVolta.setOid_Duplicata(res.getLong("oid_duplicata"));
         edVolta.setOid_Pessoa (res.getString ("oid_pessoa"));
         edVolta.setNr_CNPJ_CPF (res.getString ("nr_cnpj_cpf"));
         edVolta.setNm_Razao_Social (res.getString ("nm_razao_Social"));
         edVolta.setCD_Conta_Corrente (res.getString ("nr_Conta_Corrente"));
         edVolta.setNM_Banco (res.getString ("NM_Razao_Banco"));
         edVolta.setOid_Carteira (new Integer (res.getInt ("oid_Carteira")));
         edVolta.setCd_Carteira (res.getString ("cd_Carteira"));
         edVolta.setOid_Tipo_Documento (new Integer (res.getInt ("oid_tipo_documento")));
         edVolta.setCd_Tipo_Documento (res.getString ("cd_tipo_documento"));
         edVolta.setNm_Tipo_Documento (res.getString ("nm_tipo_documento"));
         edVolta.setNr_Duplicata (new Integer (res.getInt ("nr_Duplicata")));
         edVolta.setNr_Parcela (new Integer (res.getInt ("nr_parcela")));
         edVolta.setDt_Vencimento (dataFormatada.getDT_FormataData (res.getString ("dt_vencimento")));
         edVolta.setDt_Emissao (dataFormatada.getDT_FormataData (res.getString ("dt_emissao")));
         edVolta.setNr_Documento (res.getString ("nr_documento"));
         //edVolta.setNr_Documento (res.getLong("nr_documento"));
         edVolta.setNr_Parcela (new Integer (res.getInt ("nr_parcela")));
         edVolta.setVl_Duplicata (new Double (res.getDouble ("vl_duplicata")));
         edVolta.setVl_Desconto_Faturamento (new Double (res.getDouble ("vl_Desconto_Faturamento")));
         edVolta.setVl_Taxa_Cobranca (new Double (res.getDouble ("vl_Taxa_Cobranca")));
         edVolta.setVL_Juro_Mora_Dia (new Double (res.getDouble ("vl_Juro_Mora_Dia")));
         edVolta.setVL_Multa (new Double (res.getDouble ("vl_Multa")));
         edVolta.setVl_Saldo (new Double (res.getDouble ("vl_saldo")));
         edVolta.setVl_Saldo_Atualizado (0.0);

         if (Data.comparaData (Data.getDataDMY () , edVolta.getDt_Vencimento ())) {
           int iDifDias = Data.diferencaDias (Data.getDataDMY () ,
                                              edVolta.getDt_Vencimento ());
           double vlJuro_Mora_Dia_Calculo = edVolta.getVL_Juro_Mora_Dia ().
               doubleValue ();
           double vlSaldo_Calculado = ( ( ( (vlJuro_Mora_Dia_Calculo / 30) /
                                           100) * iDifDias) + 1);
           ed.setVL_Juros (iDifDias * vlJuro_Mora_Dia_Calculo);
           if (vlSaldo_Calculado >= 0) {
             edVolta.setVl_Saldo_Atualizado (edVolta.getVl_Saldo ().doubleValue () *
                                             vlSaldo_Calculado);
           }
         }
         list.add (edVolta);
       }
       return list;
     }
     catch (Exception exc) {
       throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
                           "listaByNota()");
     }
   }

  public ArrayList duplicata_ListaByMoeda (DuplicataED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();

    try {
      sql =
          " SELECT Duplicatas.OID_Duplicata, Pessoas.oid_pessoa, Pessoas.nr_cnpj_cpf, " +
          "    Pessoas.nm_razao_Social, Pessoas_Bancos.nm_razao_Social as NM_Razao_Banco, " +
          "    Carteiras.oid_Carteira, Carteiras.cd_Carteira, Contas_Correntes.NR_conta_corrente, " +
          "    Tipos_Documentos.oid_tipo_documento, Tipos_Documentos.cd_tipo_documento, " +
          "    Tipos_Documentos.nm_tipo_documento, Duplicatas.nr_Duplicata, Duplicatas.nr_parcela, " +
          "    Duplicatas.nr_documento, Duplicatas.dt_vencimento, Duplicatas.vl_saldo, " +
          "    Duplicatas.dt_emissao, Duplicatas.vl_duplicata, Duplicatas.vl_Desconto_Faturamento, " +
          "    Duplicatas.vl_Taxa_Cobranca, Duplicatas.vl_Multa, Duplicatas.vl_Juro_Mora_Dia, " +
          "    Duplicatas.vl_duplicata " +
          " FROM Duplicatas, pessoas, Pessoas Pessoas_Bancos, tipos_documentos, Carteiras, " +
          "    Contas_Correntes " +
          " WHERE duplicatas.oid_pessoa = pessoas.oid_pessoa " +
          "    AND duplicatas.oid_Carteira = Carteiras.oid_Carteira " +
          "    AND duplicatas.oid_tipo_documento = tipos_documentos.oid_tipo_documento " +
          "    AND carteiras.oid_conta_corrente = contas_correntes.oid_conta_corrente " +
          "    AND contas_correntes.oid_pessoa = pessoas_bancos.oid_pessoa ";

      if (ed.getOID_Moeda () != null && ed.getOID_Moeda ().intValue () > 0) {
        sql += " AND contas_correntes.oid_moeda = " + ed.getOID_Moeda ();
      }

      if (ed.getNr_Duplicata () != null &&
          ed.getNr_Duplicata ().intValue () > 0) {
        sql += " AND duplicatas.nr_duplicata = " + ed.getNr_Duplicata ();
      }
      sql += " and Duplicatas.NR_Documento = " + ed.getNr_Documento ();
      if (JavaUtil.doValida (ed.getOid_Pessoa ())) {
        sql += " and duplicatas.oid_pessoa = '" + ed.getOid_Pessoa () + "'";
      }
      if (ed.getOid_Carteira () != null &&
          ed.getOid_Carteira ().intValue () > 0) {
        sql += " and duplicatas.oid_carteira = '" + ed.getOid_Carteira () + "'";
      }
      if (JavaUtil.doValida (ed.getDT_Emissao_Inicial ())) {
        sql += " and duplicatas.dt_emissao >= '" + ed.getDT_Emissao_Inicial () +
            "'";
      }
      if (JavaUtil.doValida (ed.getDT_Emissao_Final ())) {
        sql += " and duplicatas.dt_emissao <= '" + ed.getDT_Emissao_Final () +
            "'";
      }
      if (JavaUtil.doValida (ed.getDT_Vencimento_Inicial ())) {
        sql += " and duplicatas.dt_vencimento >= '" +
            ed.getDT_Vencimento_Inicial () + "'";
      }
      if (JavaUtil.doValida (ed.getDT_Vencimento_Final ())) {
        sql += " and duplicatas.dt_vencimento <= '" + ed.getDT_Vencimento_Final () +
            "'";
      }
      if (JavaUtil.doValida (ed.getNm_Razao_Social ())) {
        sql += " and pessoas.nm_razao_social like '" + ed.getNm_Razao_Social () +
            "%'";
      }

      sql += " ORDER BY duplicatas.nr_duplicata, duplicatas.DT_Vencimento ";

      ResultSet res = this.executasql.executarConsulta (sql);

      while (res.next ()) {
        DuplicataED edVolta = new DuplicataED ();

        edVolta.setOid_Duplicata(res.getLong("oid_duplicata"));
        edVolta.setOid_Pessoa (res.getString ("oid_pessoa"));
        edVolta.setNr_CNPJ_CPF (res.getString ("nr_cnpj_cpf"));
        edVolta.setNm_Razao_Social (res.getString ("nm_razao_Social"));

        edVolta.setCD_Conta_Corrente (res.getString ("nr_Conta_Corrente"));
        edVolta.setNM_Banco (res.getString ("NM_Razao_Banco"));

        edVolta.setOid_Carteira (new Integer (res.getInt ("oid_Carteira")));
        edVolta.setCd_Carteira (res.getString ("cd_Carteira"));

        edVolta.setOid_Tipo_Documento (new Integer (res.getInt (
            "oid_tipo_documento")));
        edVolta.setCd_Tipo_Documento (res.getString ("cd_tipo_documento"));
        edVolta.setNm_Tipo_Documento (res.getString ("nm_tipo_documento"));

        edVolta.setNr_Duplicata (new Integer (res.getInt ("nr_Duplicata")));
        edVolta.setNr_Parcela (new Integer (res.getInt ("nr_parcela")));

        FormataDataBean DataFormatada = new FormataDataBean ();
        DataFormatada.setDT_FormataData (res.getString ("dt_vencimento"));
        edVolta.setDt_Vencimento (DataFormatada.getDT_FormataData ());

        DataFormatada.setDT_FormataData (res.getString ("dt_emissao"));
        edVolta.setDt_Emissao (DataFormatada.getDT_FormataData ());

        edVolta.setNr_Documento (res.getString ("nr_documento"));
        //edVolta.setNr_Documento (res.getLong("nr_documento"));
        edVolta.setNr_Parcela (new Integer (res.getInt ("nr_parcela")));

        edVolta.setVl_Duplicata (new Double (res.getDouble ("vl_duplicata")));
        edVolta.setVl_Desconto_Faturamento (new Double (res.getDouble (
            "vl_Desconto_Faturamento")));
        edVolta.setVl_Taxa_Cobranca (new Double (res.getDouble (
            "vl_Taxa_Cobranca")));
        edVolta.setVL_Juro_Mora_Dia (new Double (res.getDouble (
            "vl_Juro_Mora_Dia")));
        edVolta.setVL_Multa (new Double (res.getDouble ("vl_Multa")));
        edVolta.setVl_Saldo (new Double (res.getDouble ("vl_saldo")));
        edVolta.setVl_Saldo_Atualizado (0.0);

        String data1 = edVolta.getDt_Vencimento ();
        String data2 = Data.getDataDMY ();
        Calendar cal1 = Data.stringToCalendar (data1 , "dd/MM/yyyy");
        Calendar cal2 = Data.stringToCalendar (data2 , "dd/MM/yyyy");
        if (cal2.after (cal1)) {
          int iDifDias = Data.diferencaDias (cal1 , cal2);
          double vl_Juro_Mora_Dia_Calculo = edVolta.getVL_Juro_Mora_Dia ().
              doubleValue ();
          double vl_Saldo_Calculado = ( ( ( (vl_Juro_Mora_Dia_Calculo / 30) /
                                           100) * iDifDias) + 1);
          if (vl_Saldo_Calculado >= 0) {
            edVolta.setVl_Saldo_Atualizado (edVolta.getVl_Saldo ().doubleValue () *
                                            vl_Saldo_Calculado);
          }
        }
        list.add (edVolta);
      }
      return list;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
                          "duplicata_ListaByMoeda()");
    }
  }

  public void subtraiSaldo (DuplicataED ed , ExecutaSQL executasql) throws Excecoes {

    String sql = null;
    try {
      sql = " UPDATE Duplicatas SET " +
          "     vl_saldo = " + ed.getVl_Saldo () +
          " WHERE oid_Duplicata = " + ed.getOid_Duplicata ();
      executasql.executarUpdate (sql);
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "subtraiSaldo()");
    }
  }

  public String consulta_Situacao (DuplicataED ed) throws Excecoes {
    String nm_situacao = "";
    nm_situacao = "ABERTA";
    if (ed.getVl_Saldo_Atualizado () <= 0) {
      if ("C".equals (ed.getDM_Situacao ())) {
        nm_situacao = "CANCELADA";
      }
      else {
        nm_situacao = "LIQUIDADA";
      }
    }
    return nm_situacao;
  }

  public ArrayList GeraEDI_Cobranca (Tipo_EventoED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    FormataDataBean DataFormatada = new FormataDataBean ();

    try {

      sql = " SELECT NR_Proximo_Sort ";
      sql += " FROM Parametros_Filiais ";
      sql += " WHERE Parametros_Filiais.OID_Unidade = 2 ";

      ResultSet rs = null;
      long Nr_Sort = 0;
      double vl_movimento = 0;

      rs = this.executasql.executarConsulta (sql);
      while (rs.next ()) {
        Nr_Sort = rs.getInt ("NR_Proximo_Sort");
      }

      sql = "select * from Duplicatas, Tipos_Documentos " +
          " where Tipos_Documentos.OID_TIPO_DOCUMENTO = Duplicatas.OID_TIPO_DOCUMENTO " +
          " AND (Tipos_Documentos.CD_TIPO_DOCUMENTO = 'DUP' "
          + " OR Tipos_Documentos.CD_TIPO_DOCUMENTO = 'NS') ";
      if (ed.getDt_Inicial () != null) {
        sql += " and duplicatas.dt_emissao >= '" + ed.getDt_Inicial () + "'";
      }

      if (ed.getDt_Final () != null) {
        sql += " and duplicatas.dt_emissao <= '" + ed.getDt_Final () + "'";
      }
      ResultSet res = null;
      res = this.executasql.executarConsulta (sql.toString ());

      Auxiliar1RN Auxiliar1RN = new Auxiliar1RN ();

      while (res.next ()) {
        Auxiliar1ED edAuxiliar1 = new Auxiliar1ED ();
        edAuxiliar1.setNM_Tabela ("Duplicatas");
        edAuxiliar1.setOID_Tabela (res.getString ("oid_duplicata"));
        edAuxiliar1.setNM_Classifica1 ("1050");
        edAuxiliar1.setNM_Classifica2 (" ");
        edAuxiliar1.setNM_Classifica3 ("43");
        edAuxiliar1.setNM_Classifica4 (" ");
        DataFormatada.setDT_FormataData (res.getString ("DT_EMISSAO"));
        edAuxiliar1.setNM_Classifica5 (DataFormatada.getDT_FormataData ());
        edAuxiliar1.setVL_Classifica1 (res.getDouble ("vl_duplicata"));
        edAuxiliar1.setNR_Sort (Nr_Sort);
        Auxiliar1RN.inclui (edAuxiliar1);

        edAuxiliar1.setNM_Tabela ("Duplicatas");
        edAuxiliar1.setOID_Tabela (res.getString ("oid_duplicata"));
        edAuxiliar1.setNM_Classifica1 (" ");
        edAuxiliar1.setNM_Classifica2 ("1051");
        edAuxiliar1.setNM_Classifica3 ("43");
        edAuxiliar1.setNM_Classifica4 (" ");
        DataFormatada.setDT_FormataData (res.getString ("DT_EMISSAO"));
        edAuxiliar1.setNM_Classifica5 (DataFormatada.getDT_FormataData ());
        edAuxiliar1.setVL_Classifica1 (res.getDouble ("vl_duplicata"));
        edAuxiliar1.setNR_Sort (Nr_Sort);
        Auxiliar1RN.inclui (edAuxiliar1);

      }

      sql =
          "select Movimentos_Duplicatas.DT_MOVIMENTO, Movimentos_Duplicatas.oid_movimento_duplicata, Movimentos_Duplicatas.vl_debito, Movimentos_Duplicatas.vl_credito,  Contas.CD_Conta_Contabil as Cd_Conta_Contabil_Conta, Tipos_Instrucoes.CD_Conta_Contabil as CD_Conta_Contabil_Instrucao, Historicos.CD_Historico as CD_Historico_Instrucao "
          + " from Movimentos_Duplicatas, Carteiras, Contas_Correntes, Contas, Duplicatas, Tipos_Documentos, Tipos_Instrucoes, Historicos where "
          + " movimentos_Duplicatas.oid_Duplicata = Duplicatas.oid_Duplicata "
          + " and duplicatas.oid_Carteira = Carteiras.oid_Carteira "
          +
          " and carteiras.oid_conta_corrente = contas_correntes.oid_conta_corrente "
          + " and contas_correntes.oid_conta = contas.oid_conta "
          + " and movimentos_Duplicatas.oid_tipo_instrucao = Tipos_Instrucoes.oid_Tipo_Instrucao "
          + " and Tipos_Instrucoes.oid_Historico = Historicos.oid_Historico "
          +
          " and Tipos_Documentos.OID_TIPO_DOCUMENTO = Duplicatas.OID_TIPO_DOCUMENTO "
          + " AND (Tipos_Documentos.CD_TIPO_DOCUMENTO = 'DUP' "
          + " OR Tipos_Documentos.CD_TIPO_DOCUMENTO = 'NS') ";

      if (String.valueOf (ed.getDt_Inicial ()) != null &&
          !String.valueOf (ed.getDt_Inicial ()).equals ("") &&
          !String.valueOf (ed.getDt_Inicial ()).equals ("null")) {
        sql += " and movimentos_Duplicatas.dt_movimento >= '" +
            ed.getDt_Inicial () + "'";
      }
      if (String.valueOf (ed.getDt_Final ()) != null &&
          !String.valueOf (ed.getDt_Final ()).equals ("") &&
          !String.valueOf (ed.getDt_Final ()).equals ("null")) {
        sql += " and movimentos_Duplicatas.dt_movimento <= '" + ed.getDt_Final () +
            "'";
      }
      res = this.executasql.executarConsulta (sql.toString ());

      while (res.next ()) {

        Auxiliar1ED edAuxiliar1 = new Auxiliar1ED ();
        edAuxiliar1.setNM_Tabela ("Movimentos_Duplicatas");
        edAuxiliar1.setOID_Tabela (res.getString ("oid_movimento_duplicata"));
        edAuxiliar1.setNM_Classifica1 (res.getString ("Cd_Conta_Contabil_Conta"));
        edAuxiliar1.setNM_Classifica2 (" ");
        edAuxiliar1.setNM_Classifica3 (res.getString ("CD_Historico_Instrucao"));
        edAuxiliar1.setNM_Classifica4 (" ");

        DataFormatada.setDT_FormataData (res.getString ("DT_MOVIMENTO"));
        edAuxiliar1.setNM_Classifica5 (DataFormatada.getDT_FormataData ());

        vl_movimento = (res.getDouble ("vl_debito") +
                        res.getDouble ("vl_credito"));
        edAuxiliar1.setVL_Classifica1 (vl_movimento);

        edAuxiliar1.setNR_Sort (Nr_Sort);
        Auxiliar1RN.inclui (edAuxiliar1);

        edAuxiliar1.setNM_Tabela ("Movimentos_Duplicatas");
        edAuxiliar1.setOID_Tabela (res.getString ("oid_movimento_duplicata"));
        edAuxiliar1.setNM_Classifica1 (" ");
        edAuxiliar1.setNM_Classifica2 (res.getString (
            "Cd_Conta_Contabil_Instrucao"));
        edAuxiliar1.setNM_Classifica3 (res.getString ("CD_Historico_Instrucao"));
        edAuxiliar1.setNM_Classifica4 (" ");

        DataFormatada.setDT_FormataData (res.getString ("DT_MOVIMENTO"));
        edAuxiliar1.setNM_Classifica5 (DataFormatada.getDT_FormataData ());

        vl_movimento = (res.getDouble ("vl_debito") +
                        res.getDouble ("vl_credito"));
        edAuxiliar1.setVL_Classifica1 (vl_movimento);

        edAuxiliar1.setNR_Sort (Nr_Sort);
        Auxiliar1RN.inclui (edAuxiliar1);
      }

      sql = " select * from auxiliar1 where Auxiliar1.Nr_Sort = " + Nr_Sort;
      sql += " order by auxiliar1.nm_classifica1, nm_classifica2  ";
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {

        Tipo_EventoED edVolta = new Tipo_EventoED ();

        sql = " select CD_UNIDADE_CONTABIL from Unidades where OID_UNIDADE = " +
            ed.getOID_Unidade ();
        ResultSet resTP = this.executasql.executarConsulta (sql);
        while (resTP.next ()) {
          edVolta.setCD_Unidade_Contabil (resTP.getString (
              "CD_UNIDADE_CONTABIL"));
        }
        edVolta.setCd_Conta_Debito (res.getString ("nm_classifica1"));
        edVolta.setCd_Conta_Credito (res.getString ("nm_classifica2"));

        edVolta.setCd_Historico (res.getString ("nm_classifica3"));
        edVolta.setNm_Arquivo_Saida (ed.getNm_Arquivo_Saida ());
        edVolta.setDt_Lancamento (res.getString ("nm_classifica5"));
        edVolta.setNm_Complemento_Historico (res.getString ("nm_classifica4"));
        edVolta.setDm_Totalizado ("S");
        edVolta.setVl_Lancamento (res.getDouble ("vl_classifica1"));

        list.add (edVolta);
      }

      sql = "select oid_auxiliar1 from auxiliar1 where Auxiliar1.Nr_Sort = " +
          Nr_Sort;
      res = this.executasql.executarConsulta (sql.toString ());

      Auxiliar1RN AuxRN = new Auxiliar1RN ();

      while (res.next ()) {
        Auxiliar1ED edAuxiliar1 = new Auxiliar1ED ();
        edAuxiliar1.setOID_auxiliar1 (new Integer (res.getInt ("oid_auxiliar1")));
        AuxRN.deleta (edAuxiliar1);
      }
      sql = " UPDATE Parametros_Filiais SET NR_Proximo_Sort= " + (Nr_Sort + 1);
      sql += " WHERE Parametros_Filiais.OID_Unidade = 2 ";
      executasql.executarUpdate (sql);

    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () ,
                          "GeraEDI_Cobranca()");
    }
    return (list);
  }

  public ArrayList GeraEDI_Cobranca_Nota_Servico (Tipo_EventoED ed) throws
      Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    FormataDataBean DataFormatada = new FormataDataBean ();

    try {

      sql = " SELECT NR_Proximo_Sort ";
      sql += " FROM Parametros_Filiais ";
      sql += " WHERE Parametros_Filiais.OID_Unidade = 2 ";

      ResultSet rs = null;
      long Nr_Sort = 0;
      double vl_movimento = 0;

      rs = this.executasql.executarConsulta (sql);
      while (rs.next ()) {
        Nr_Sort = rs.getInt ("NR_Proximo_Sort");
      }

      sql = "select * from Duplicatas, Tipos_Documentos " +
          " where Tipos_Documentos.OID_TIPO_DOCUMENTO = Duplicatas.OID_TIPO_DOCUMENTO " +
          " AND Tipos_Documentos.CD_TIPO_DOCUMENTO = 'REC' ";
      if (ed.getDt_Inicial () != null) {
        sql += " and duplicatas.dt_emissao >= '" + ed.getDt_Inicial () + "'";
      }

      if (ed.getDt_Final () != null) {
        sql += " and duplicatas.dt_emissao <= '" + ed.getDt_Final () + "'";
      }
      ResultSet res = null;
      res = this.executasql.executarConsulta (sql.toString ());

      Auxiliar1RN Auxiliar1RN = new Auxiliar1RN ();

      while (res.next ()) {
        Auxiliar1ED edAuxiliar1 = new Auxiliar1ED ();
        edAuxiliar1.setNM_Tabela ("Duplicatas");
        edAuxiliar1.setOID_Tabela (res.getString ("oid_duplicata"));
        edAuxiliar1.setNM_Classifica1 ("1050");
        edAuxiliar1.setNM_Classifica2 (" ");
        edAuxiliar1.setNM_Classifica3 ("43");
        edAuxiliar1.setNM_Classifica4 (" ");
        DataFormatada.setDT_FormataData (res.getString ("DT_EMISSAO"));
        edAuxiliar1.setNM_Classifica5 (DataFormatada.getDT_FormataData ());
        edAuxiliar1.setVL_Classifica1 (res.getDouble ("vl_duplicata"));
        edAuxiliar1.setNR_Sort (Nr_Sort);
        Auxiliar1RN.inclui (edAuxiliar1);

        edAuxiliar1.setNM_Tabela ("Duplicatas");
        edAuxiliar1.setOID_Tabela (res.getString ("oid_duplicata"));
        edAuxiliar1.setNM_Classifica1 (" ");
        edAuxiliar1.setNM_Classifica2 ("1051");
        edAuxiliar1.setNM_Classifica3 ("43");
        edAuxiliar1.setNM_Classifica4 (" ");
        DataFormatada.setDT_FormataData (res.getString ("DT_EMISSAO"));
        edAuxiliar1.setNM_Classifica5 (DataFormatada.getDT_FormataData ());
        edAuxiliar1.setVL_Classifica1 (res.getDouble ("vl_duplicata"));
        edAuxiliar1.setNR_Sort (Nr_Sort);
        Auxiliar1RN.inclui (edAuxiliar1);

      }

      sql =
          "select Movimentos_Duplicatas.DT_MOVIMENTO, Movimentos_Duplicatas.oid_movimento_duplicata, Movimentos_Duplicatas.vl_debito, Movimentos_Duplicatas.vl_credito,  Contas.CD_Conta_Contabil as Cd_Conta_Contabil_Conta, Tipos_Instrucoes.CD_Conta_Contabil as CD_Conta_Contabil_Instrucao, Historicos.CD_Historico as CD_Historico_Instrucao "
          + " from Movimentos_Duplicatas, Carteiras, Contas_Correntes, Contas, Duplicatas, Tipos_Documentos, Tipos_Instrucoes, Historicos where "
          + " movimentos_Duplicatas.oid_Duplicata = Duplicatas.oid_Duplicata "
          + " and duplicatas.oid_Carteira = Carteiras.oid_Carteira "
          +
          " and carteiras.oid_conta_corrente = contas_correntes.oid_conta_corrente "
          + " and contas_correntes.oid_conta = contas.oid_conta "
          + " and movimentos_Duplicatas.oid_tipo_instrucao = Tipos_Instrucoes.oid_Tipo_Instrucao "
          + " and Tipos_Instrucoes.oid_Historico = Historicos.oid_Historico"
          +
          " and Tipos_Documentos.OID_TIPO_DOCUMENTO = Duplicatas.OID_TIPO_DOCUMENTO " +
          " AND Tipos_Documentos.CD_TIPO_DOCUMENTO = 'REC' ";
      if (String.valueOf (ed.getDt_Inicial ()) != null &&
          !String.valueOf (ed.getDt_Inicial ()).equals ("") &&
          !String.valueOf (ed.getDt_Inicial ()).equals ("null")) {
        sql += " and movimentos_Duplicatas.dt_movimento >= '" +
            ed.getDt_Inicial () + "'";
      }
      if (String.valueOf (ed.getDt_Final ()) != null &&
          !String.valueOf (ed.getDt_Final ()).equals ("") &&
          !String.valueOf (ed.getDt_Final ()).equals ("null")) {
        sql += " and movimentos_Duplicatas.dt_movimento <= '" + ed.getDt_Final () +
            "'";
      }

      res = this.executasql.executarConsulta (sql.toString ());

      while (res.next ()) {

        Auxiliar1ED edAuxiliar1 = new Auxiliar1ED ();
        edAuxiliar1.setNM_Tabela ("Movimentos_Duplicatas");
        edAuxiliar1.setOID_Tabela (res.getString ("oid_movimento_duplicata"));
        edAuxiliar1.setNM_Classifica1 (res.getString ("Cd_Conta_Contabil_Conta"));
        edAuxiliar1.setNM_Classifica2 (" ");
        edAuxiliar1.setNM_Classifica3 (res.getString ("CD_Historico_Instrucao"));
        edAuxiliar1.setNM_Classifica4 (" ");

        DataFormatada.setDT_FormataData (res.getString ("DT_MOVIMENTO"));
        edAuxiliar1.setNM_Classifica5 (DataFormatada.getDT_FormataData ());

        vl_movimento = (res.getDouble ("vl_debito") +
                        res.getDouble ("vl_credito"));
        edAuxiliar1.setVL_Classifica1 (vl_movimento);

        edAuxiliar1.setNR_Sort (Nr_Sort);
        Auxiliar1RN.inclui (edAuxiliar1);

        edAuxiliar1.setNM_Tabela ("Movimentos_Duplicatas");
        edAuxiliar1.setOID_Tabela (res.getString ("oid_movimento_duplicata"));
        edAuxiliar1.setNM_Classifica1 (" ");
        edAuxiliar1.setNM_Classifica2 (res.getString (
            "Cd_Conta_Contabil_Instrucao"));
        edAuxiliar1.setNM_Classifica3 (res.getString ("CD_Historico_Instrucao"));
        edAuxiliar1.setNM_Classifica4 (" ");

        DataFormatada.setDT_FormataData (res.getString ("DT_MOVIMENTO"));
        edAuxiliar1.setNM_Classifica5 (DataFormatada.getDT_FormataData ());

        vl_movimento = (res.getDouble ("vl_debito") +
                        res.getDouble ("vl_credito"));
        edAuxiliar1.setVL_Classifica1 (vl_movimento);

        edAuxiliar1.setNR_Sort (Nr_Sort);
        Auxiliar1RN.inclui (edAuxiliar1);
      }

      sql = " select * from auxiliar1 where Auxiliar1.Nr_Sort = " + Nr_Sort;
      sql += " order by auxiliar1.nm_classifica1, nm_classifica2  ";
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {

        Tipo_EventoED edVolta = new Tipo_EventoED ();

        sql = " select CD_UNIDADE_CONTABIL from Unidades where OID_UNIDADE = " +
            ed.getOID_Unidade ();
        ResultSet resTP = this.executasql.executarConsulta (sql);
        while (resTP.next ()) {
          edVolta.setCD_Unidade_Contabil (resTP.getString (
              "CD_UNIDADE_CONTABIL"));
        }
        edVolta.setCd_Conta_Debito (res.getString ("nm_classifica1"));
        edVolta.setCd_Conta_Credito (res.getString ("nm_classifica2"));

        edVolta.setCd_Historico (res.getString ("nm_classifica3"));
        edVolta.setNm_Arquivo_Saida (ed.getNm_Arquivo_Saida ());
        edVolta.setDt_Lancamento (res.getString ("nm_classifica5"));
        edVolta.setNm_Complemento_Historico (res.getString ("nm_classifica4"));
        edVolta.setDm_Totalizado ("S");
        edVolta.setVl_Lancamento (res.getDouble ("vl_classifica1"));

        list.add (edVolta);
      }

      sql = "select oid_auxiliar1 from auxiliar1 where Auxiliar1.Nr_Sort = " +
          Nr_Sort;
      res = this.executasql.executarConsulta (sql.toString ());

      Auxiliar1RN AuxRN = new Auxiliar1RN ();

      while (res.next ()) {
        Auxiliar1ED edAuxiliar1 = new Auxiliar1ED ();
        edAuxiliar1.setOID_auxiliar1 (new Integer (res.getInt ("oid_auxiliar1")));
        AuxRN.deleta (edAuxiliar1);
      }
      sql = " UPDATE Parametros_Filiais SET NR_Proximo_Sort= " + (Nr_Sort + 1);
      sql += " WHERE Parametros_Filiais.OID_Unidade = 2 ";
      executasql.executarUpdate (sql);

    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () ,
                          "GeraEDI_Cobranca_Nota_Servico(Tipo_EventoED ed)");
    }
    return (list);
  }

  public DuplicataED getByRecordCTRC (DuplicataED ed) throws Excecoes {

    String sql = null;
    DuplicataED edVolta = new DuplicataED ();
    try {

      sql = " SELECT Duplicatas.OID_Duplicata, Duplicatas.nr_Duplicata, Duplicatas.VL_Saldo, conhecimentos.nr_conhecimento, conhecimentos.oid_Conhecimento, Conhecimentos.VL_Total_Frete, Conhecimentos.VL_Desconto " +
          " FROM   Duplicatas, origens_duplicatas, conhecimentos " +
          " WHERE  duplicatas.oid_duplicata = origens_duplicatas.oid_duplicata " +
          " AND    origens_duplicatas.oid_conhecimento = conhecimentos.oid_conhecimento " +
          " AND    origens_duplicatas.dm_situacao = 'A'";

      if (ed.getOid_Unidade () != null && !ed.getOid_Unidade ().equals ("") &&
          !ed.getOid_Unidade ().equals ("null")) {
        sql += " and conhecimentos.oid_unidade = " + ed.getOid_Unidade ();
      }

      if (!ed.getNr_Documento ().equals ("0")) {
        sql += " and conhecimentos.nr_conhecimento = " + ed.getNr_Documento ();
      }
      ResultSet res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta = new DuplicataED ();
        edVolta.setOid_Duplicata(res.getLong("oid_duplicata"));
        edVolta = this.getByRecord (edVolta);
        edVolta.setVL_Total_Frete_Conhecimento (res.getDouble ("VL_Total_Frete") - res.getDouble ("VL_Desconto"));
        edVolta.setOID_Conhecimento (res.getString ("OID_Conhecimento"));
      }
      return edVolta;
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "getByRecordCTRC()");
    }
  }

  public double getLimiteCredito (String oid_Pessoa , String dtInicial , String dtFinal) throws Excecoes {

    /** % ADICIONAL **/
    double peAdicional = 10 , vl_Limite = 0;

    try {
      if (!JavaUtil.doValida (oid_Pessoa)) {
        throw new Mensagens ("Pessoa n�o informada!");
      }
      if (!JavaUtil.doValida (dtInicial)) {
        throw new Mensagens ("Data inicial n�o informada!");
      }
      if (!JavaUtil.doValida (dtFinal)) {
        throw new Mensagens ("Data final n�o informada!");
      }
      double limiteCredito = util.getTableDoubleValue ("sum(VL_Duplicata)" , "Duplicatas" , "oid_Pessoa = '" + oid_Pessoa + "'" + "AND DT_Emissao BETWEEN '" + dtInicial + "' AND '" + dtFinal + "'");
      limiteCredito += Valor.calcPercentual (limiteCredito , peAdicional);
      // *** Saldo DEVEDOR
      vl_Limite = Valor.round (limiteCredito - this.getSaldoDevedor (oid_Pessoa , dtFinal));

    }
    catch (Excecoes e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "getLimiteCredito()");
    }
    return vl_Limite;
  }

  public double getSaldoDevedor (String oid_Pessoa) throws Excecoes {
    return this.getSaldoDevedor (oid_Pessoa , null);
  }

  public double getSaldoDevedor (String oid_Pessoa , String dtReferencia) throws Excecoes {

    try {
      if (!JavaUtil.doValida (oid_Pessoa)) {
        throw new Mensagens ("Pessoa n�o informada!");
      }

      String query = "oid_Pessoa = '" + oid_Pessoa + "'";
      if (JavaUtil.doValida (dtReferencia)) {
        query += " AND DT_Vencimento < '" + dtReferencia + "'";
      }

      return util.getTableDoubleValue ("sum(VL_Saldo)" , "Duplicatas" , query);
    }
    catch (Excecoes e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "getSaldoDevedor()");
    }
  }

  public ArrayList GeraMinuta_Protocolo_Entrega (DuplicataED ed) throws Excecoes {

	    String sql = null;
	    ArrayList list = new ArrayList ();
	    ResultSet res = null;
	    ResultSet res2 = null;
	    String carteiras="('C','I','R')";
	    int qt_cto=0;

	    try {

	      sql = "SELECT *, Unidades.oid_Pessoa as oid_Pessoa_Unidade, Unidades.oid_Unidade as oid_Uni " +
	          " FROM  Duplicatas, Carteiras, Unidades, Pessoas " +
	          " WHERE Duplicatas.oid_Unidade = Unidades.oid_Unidade " +
	          " AND   Duplicatas.oid_Carteira = Carteiras.oid_Carteira " +
	          " AND   Duplicatas.oid_Pessoa = Pessoas.oid_Pessoa " +
	      	  " AND   Duplicatas.oid_protocolo_entrega is null ";
              //" AND   Carteiras.DM_Carteira in " +carteiras;

	      if (JavaUtil.doValida (ed.getDT_Emissao_Inicial())) {
	    	  sql += " AND Duplicatas.dt_emissao >= '" + ed.getDT_Emissao_Inicial () + "'";
	      }

	      if (JavaUtil.doValida (ed.getDT_Emissao_Final())) {
	        sql += " AND Duplicatas.dt_emissao <= '" + ed.getDT_Emissao_Final () + "'";
	      }

	      if (ed.getNR_Duplicata_Inicial()>0) {
		        sql += " AND Duplicatas.NR_Duplicata >=" + ed.getNR_Duplicata_Inicial ();
	      }
	      if (ed.getNR_Duplicata_Final()>0) {
		        sql += " AND Duplicatas.NR_Duplicata <=" + ed.getNR_Duplicata_Final ();
	      }

	      sql += " ORDER BY Duplicatas.NR_Duplicata ";
	      // System.out.println(sql);

	      res = this.executasql.executarConsulta (sql.toString ());


	      while (res.next () && qt_cto<100 ) {
	        ConhecimentoED conED = new ConhecimentoED ();

            conED.setDM_Tipo_Pagamento ("1");
            conED.setOID_Pessoa (res.getString ("oid_Pessoa_Unidade"));
            conED.setOID_Pessoa_Destinatario (res.getString ("OID_Pessoa"));

            conED.setOID_Pessoa_Pagador (res.getString("oid_Pessoa_Unidade"));
            conED.setOID_Vendedor (res.getString("oid_Pessoa_Unidade"));
            conED.setOID_Produto (parametro_FixoED.getOID_Produto());


            conED.setOID_Unidade (255);

            conED.setOID_Empresa (res.getLong("oid_Empresa"));
            conED.setOID_Modal (parametro_FixoED.getOID_Modal());
            conED.setDM_Tipo_Documento ("M");
            conED.setDM_Isento_Seguro ("S");
            conED.setDM_Tipo_Conhecimento ("D");
            conED.setNR_Remessa (res.getString ("NR_Duplicata"));
            conED.setDM_Conhecimento_Varias_Notas_Fiscais ("N");
            conED.setNR_Conhecimento (0);
            conED.setDM_Impresso ("N");
            conED.setDM_Responsavel_Cobranca ("");
            conED.setDT_Emissao (Data.getDataDMY ());

            conED.setDT_Previsao_Entrega ("");
            conED.setHR_Previsao_Entrega ("");
            conED.setOID_Cidade (res.getLong("oid_Cidade"));
            conED.setOID_Cidade_Destino (res.getLong("oid_Cidade"));

            conED.setOID_Coleta (0);
            conED.setOID_Veiculo ("");

            conED.setTX_Observacao ("Protocolo Entrega Duplicata.: " + res.getString ("NR_Duplicata"));

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
            qt_cto++;
            conED = new ConhecimentoBD (executasql).inclui (conED);

    	    sql = " SELECT oid_Conhecimento, NR_Conhecimento  " +
  	         " FROM  Conhecimentos " +
             " WHERE Conhecimentos.oid_Conhecimento ='"+conED.getOID_Conhecimento()+"'";
    	    // System.out.println(sql);
	  	      res2 = this.executasql.executarConsulta (sql.toString ());
	  	      if (res2.next ()) {
	  	    	  sql =" UPDATE Conhecimentos SET DM_Impresso ='S', VL_Total_Frete=0.01  WHERE Conhecimentos.oid_Conhecimento ='"+conED.getOID_Conhecimento()+"'";
	      	    // System.out.println(sql);
	  	    	  executasql.executarUpdate (sql);

	  	    	  sql =" UPDATE Duplicatas SET oid_protocolo_entrega ='"+conED.getOID_Conhecimento()+"' WHERE oid_Duplicata = " + res.getString ("oid_Duplicata");
	      	    // System.out.println(sql);
	  	    	  executasql.executarUpdate (sql);

	  	        DuplicataED edVolta = new DuplicataED ();
	  	        edVolta.setOid_Duplicata(res.getLong("oid_duplicata"));
	  	        edVolta.setOid_Pessoa (res.getString ("oid_pessoa"));
	  	        edVolta.setNm_Razao_Social (res.getString ("nm_razao_Social"));
	  	        edVolta.setNr_Duplicata (new Integer (res.getInt ("nr_Duplicata")));
	  	        edVolta.setNR_Conhecimento(res2.getString("NR_Conhecimento"));
	  	        FormataDataBean DataFormatada = new FormataDataBean ();
	  	        DataFormatada.setDT_FormataData (res.getString ("dt_vencimento"));
	  	        edVolta.setDt_Vencimento (DataFormatada.getDT_FormataData ());
	  	        edVolta.setOid_Protocolo_Entrega(conED.getOID_Conhecimento());
	  	        DataFormatada.setDT_FormataData (res.getString ("dt_emissao"));
	  	        edVolta.setDt_Emissao (DataFormatada.getDT_FormataData ());
	  	        edVolta.setVl_Duplicata (new Double (res.getDouble ("vl_duplicata")));
	  	        list.add (edVolta);
	  	      }

	      }
	    }
	    catch (SQLException e) {
	      throw new Excecoes (e.getMessage () , e , getClass ().getName () ,  "GeraMinuta_Entrega_Duplicata)");
	    }
	    return (list);
	  }

}
