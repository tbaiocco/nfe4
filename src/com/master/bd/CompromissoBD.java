package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import com.master.ed.Auxiliar1ED;
import com.master.ed.CompromissoED;
import com.master.ed.CompromissoPesquisaED;
import com.master.ed.RelatorioED;
import com.master.ed.Tipo_EventoED;
import com.master.rl.CompromissoRL;
import com.master.rl.JasperRL;
import com.master.rn.Auxiliar1RN;
import com.master.root.FormataDataBean;
import com.master.root.MoedaBean;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;
import com.master.util.Utilitaria;
import com.master.util.Valor;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;
public class CompromissoBD {

  private ExecutaSQL executasql;

  Utilitaria util = new Utilitaria (executasql);
  private FormataDataBean dataFormatada = new FormataDataBean ();

  public CompromissoBD (ExecutaSQL sql) {
    this.executasql = sql;
    util = new Utilitaria (this.executasql);
  }

  /** VALIDA��O **/
  private void validaInclusao (CompromissoED ed) throws Mensagens , Excecoes {

    if (util.doExiste ("Compromissos" , "NR_DOCUMENTO = " + util.getSQLString (ed.getNr_Documento ()) +
                       " AND NR_PARCELA = " + ed.getNr_Parcela () +
                       " AND OID_TIPO_DOCUMENTO = " + ed.getOid_Tipo_Documento () +
                       " AND OID_PESSOA = " + util.getSQLString (ed.getOid_Pessoa ()) +
                       " AND OID_UNIDADE = " + ed.getOid_Unidade ())) {
      throw new Mensagens ("Compromisso ja existente! Confira o Fornecedor+Tipo Doc+Documento+Parcela!");
    }
  }

  public CompromissoED inclui_parcela (CompromissoED ed) throws Excecoes {


    // System.out.println("inclui_parcela BR 1=>>" + ed.getOid_Compromisso());

    CompromissoED edParcela = this.getByRecord(ed);


    String sql = null;
    try {
      edParcela.setNr_Parcela (ed.getNr_Parcela ());
      edParcela.setDt_Vencimento(ed.getDt_Vencimento());
      edParcela.setDt_Entrada(Data.getDataDMY());
      edParcela.setVl_Compromisso(ed.getVl_Compromisso());
      edParcela.setVl_Saldo(ed.getVl_Compromisso());
      if (edParcela.getNr_Parcela () == null || edParcela.getNr_Parcela ().intValue () < 1) {
        edParcela.setNr_Parcela (new Integer (1));
      }
      edParcela.setTx_Observacao("(Parc.Comp: " + edParcela.getNr_Compromisso() +")" +edParcela.getTx_Observacao());
      this.validaInclusao (edParcela);

    // System.out.println("inclui_parcela BR 99=>>" + ed.getOid_Compromisso());


      edParcela= this.inclui(edParcela);

    // System.out.println("inclui_parcela BR 99=>>" + edParcela.getOid_Compromisso());

      sql = " UPDATE Compromissos SET oid_compromisso_parcela= " + ed.getOid_Compromisso();
      sql += " WHERE Compromissos.OID_Compromisso = " + edParcela.getOid_Compromisso ();

    // System.out.println("inclui_parcela sql=>>" + sql);

      executasql.executarUpdate (sql);

      sql = "select vl_saldo from Compromissos where compromissos.oid_Compromisso = " + ed.getOid_Compromisso ();
      ResultSet res_comp = this.executasql.executarConsulta (sql);
      double vl_saldo = 0;
      while (res_comp.next ()) {
        vl_saldo = res_comp.getDouble ("vl_saldo");
      }

      String vl_saldo_Aux = null;
      vl_saldo_Aux = String.valueOf (ed.getVl_Compromisso ());
      vl_saldo = vl_saldo - new Double (vl_saldo_Aux).doubleValue ();

      sql = " UPDATE Compromissos SET Vl_Saldo= " + vl_saldo;
      sql += " WHERE Compromissos.OID_Compromisso = " + ed.getOid_Compromisso ();

    // System.out.println("inclui_parcela BR 99=>>" + sql);

      executasql.executarUpdate (sql);

      return edParcela;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "inclui_parcela()");
    }
  }

  public CompromissoED inclui_Cartorio (CompromissoED ed) throws Excecoes {

    String sql = null;
    long Oid_Compromisso = 0;
    try {

      sql = " SELECT vl_saldo , DT_Vencimento " +
            " FROM Compromissos  " +
            " WHERE Compromissos.VL_Saldo >0  " +
            " AND   Compromissos.oid_Compromisso = " + ed.getOid_Compromisso ();

      ResultSet res_comp = this.executasql.executarConsulta (sql);

      double vl_saldo = 0;
      if (res_comp.next ()) {
        vl_saldo = res_comp.getDouble ("vl_saldo");
        vl_saldo+=ed.getVL_Custas();
        sql = "  UPDATE Compromissos SET Vl_Saldo= " + vl_saldo +
              " ,DM_Cartorio='S' "   +
              " ,DT_Cartorio ='" + ed.getDT_Cartorio() + "'" +
              " ,DT_Vencimento ='" + ed.getDT_Cartorio() + "'" +
              " ,DT_Vencimento_Original = '" + res_comp.getString("DT_Vencimento") + "'" +
              " ,VL_Custas = " + ed.getVL_Custas();
        sql += " WHERE Compromissos.OID_Compromisso = " + ed.getOid_Compromisso ();

        executasql.executarUpdate (sql);
      }

      return ed;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "inclui_parcela()");
    }
  }

  public CompromissoED exclui_Cartorio (CompromissoED ed) throws Excecoes {

    String sql = null;
    try {

      sql = " SELECT VL_Saldo , VL_Custas, DT_Vencimento_Original " +
            " FROM  Compromissos  " +
            " WHERE Compromissos.VL_Saldo >0  " +
            " AND   Compromissos.oid_Compromisso = " + ed.getOid_Compromisso ();

      ResultSet res_comp = this.executasql.executarConsulta (sql);

      double vl_saldo = 0;
      if (res_comp.next ()) {
        if ( res_comp.getDouble ("vl_saldo")>res_comp.getDouble ("VL_Custas")){
          vl_saldo = res_comp.getDouble ("vl_saldo") - res_comp.getDouble ("VL_Custas");
          sql = "  UPDATE Compromissos SET Vl_Saldo= " + vl_saldo +
                " ,DM_Cartorio=null " +
                " ,DT_Cartorio =null " +
                " ,DT_Vencimento ='" + res_comp.getString ("DT_Vencimento_Original") + "'" +
                " ,VL_Custas = 0 " +
                " WHERE Compromissos.OID_Compromisso = " + ed.getOid_Compromisso ();
        }
        executasql.executarUpdate (sql);
      }

      return ed;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "exclui_Cartorio()");
    }
  }


  public CompromissoED inclui (CompromissoED ed) throws Excecoes {

    String sql = null;
    int Nr_Compromisso = 0;

    try {

      //*** Validate
       if (ed.getOid_Unidade () == null || ed.getOid_Unidade ().intValue () < 1) {
         throw new Excecoes ("Unidade n�o informada!");
       }
      if (ed.getOid_Moeda () < 1) {
        ed.setOid_Moeda (util.getTableIntValue ("oid_Moeda" , "Unidades" , "oid_Unidade = " + ed.getOid_Unidade ()));
        if (ed.getOid_Moeda () < 1) {
          throw new Excecoes ("Moeda n�o informada!");
        }
      }
      if (ed.getOid_Unidade_Pagamento () < 1) {
        ed.setOid_Unidade_Pagamento (ed.getOid_Unidade ().intValue ());
      }
      if (!JavaUtil.doValida (ed.getDT_Cotacao ())) {
        ed.setDT_Cotacao (ed.getDt_Emissao ());
      }
      //*** VL_Cota��o
       ed.setVL_Cotacao (1);
      ed.setVL_Cotacao_Padrao (1);
      if (ed.getOid_Moeda () != Parametro_FixoED.getInstancia ().getOID_Moeda_Padrao ()) {
      }


      //*** Compromisso entra liberado/bloqueado
      ed.setDt_Liberado (Data.getDataDMY ());
      if (ed.getOID_Movimento_Ordem_Servico () != null &&  //bloq comp MAnut
          ed.getOID_Movimento_Ordem_Servico ().intValue () > 0) {
          //  ed.setDt_Liberado (null);
      }

      String dm_libera_compromisso = util.getTableStringValue ("dm_libera_compromisso" , "Contas" , "Contas.OID_Conta = " + ed.getOid_Conta ());
      if ("N".equals (dm_libera_compromisso)) {
        ed.setDt_Liberado (null);
      }

      //*** Busca Pr�ximo Registro
       Nr_Compromisso = util.getTableIntValue ("NR_Proximo_Compromisso" , "Parametros_Filiais" , "Parametros_Filiais.OID_Unidade = " + ed.getOid_Unidade ());

      //*** VALIDA�AO
       if (util.doExiste ("Compromissos" , "NR_COMPROMISSO = " + Nr_Compromisso + " AND OID_UNIDADE = " + ed.getOid_Unidade ())) {
         throw new Excecoes ("NR de Compromisso ja existente. Alterar parametro da Unidade!");
       }
      if (ed.getNr_Parcela () == null || ed.getNr_Parcela ().intValue () < 1) {
        ed.setNr_Parcela (new Integer (1));
      }
      //LINHA COMNETADA PQ DAVA PROBLEMA NA IMPRESSAO DO VALE DINHEIRO NA ORDEM DE FRETE
      //this.validaInclusao (ed);

      String oid = String.valueOf (ed.getOid_Unidade ()) + String.valueOf (Nr_Compromisso);
      ed.setOid_Compromisso (new Integer (oid));
      //ed.setOid_Compromisso(new Integer(ed.getOid_Unidade().intValue() + Nr_Compromisso));
      ed.setNr_Compromisso (new Integer (Nr_Compromisso));

      //*** Atualiza Paramentros
       sql = " UPDATE Parametros_Filiais SET NR_Proximo_Compromisso= " + (Nr_Compromisso + 1);
      sql += " WHERE Parametros_Filiais.OID_Unidade = " + ed.getOid_Unidade ();
      executasql.executarUpdate (sql);

      //CALCULA VCTO - NAO PODE VENCER ANTES DO DIA DA INCLUSAO
      String dt_Vencimento=ed.getDt_Vencimento();
      //String data1 = ed.getDt_Vencimento ();   RALPH
      //String data2 = Data.getDataDMY ();
      //Calendar cal1 = Data.stringToCalendar (data1 , "dd/MM/yyyy");
      //Calendar cal2 = Data.stringToCalendar (data2 , "dd/MM/yyyy");
      //if (cal2.after (cal1)) {
      //  dt_Vencimento = data2;
      //}

      if (ed.getOid_Pessoa().length() == 14 && String.valueOf(ed.getOid_Conta()) == "98" && String.valueOf(ed.getOid_Tipo_Documento()) == "3"){
    	  ed.setOid_Conta(new Integer (99));
      }

      sql = " INSERT INTO Compromissos(" +
          "		OID_COMPROMISSO" +
          "		,NR_DOCUMENTO" +
          "		,NR_PARCELA" +
          "		,DT_EMISSAO" +
          "		,DT_LIBERADO" +
          "		,DT_VENCIMENTO_ORIGINAL" +
          "		,DT_VENCIMENTO" +
          "		,VL_COMPROMISSO" +
          "		,VL_MULTA_APOS_VENCIMENTO" +
          "		,VL_JURO_MORA_DIA" +
          "		,VL_TAXA_BANCO" +
          "		,TX_OBSERVACAO" +
          "		,DM_TIPO_PAGAMENTO" +
          "		,NR_COMPROMISSO" +
          "		,DT_STAMP" +
          "		,USUARIO_STAMP" +
          "		,DM_STAMP" +
          "		,VL_DESCONTO_ATE_VENCIMENTO" +
          "		,VL_SALDO" +
          "		,OID_TIPO_DOCUMENTO" +
          "		,OID_MOVIMENTO_ORDEM_SERVICO" +
          "		,OID_CONTA" +
          "		,OID_CONTA_CREDITO" +
          "		,OID_PESSOA" +
          "		,OID_CENTRO_CUSTO" +
          "		,OID_UNIDADE" +
          "		,OID_NATUREZA_OPERACAO" +
          "		,OID_UNIDADE_PAGAMENTO" +
          "		,OID_MOEDA" +
          "		,OID_USUARIO" +
          "		,VL_COTACAO" +
          "		,VL_COTACAO_PADRAO" +
          "		,DT_COTACAO" +
          "		,DT_ENTRADA" +
          "		,DM_Lote_Pagamento" +
          "		,CD_Barra)" +
          " VALUES ("
          + ed.getOid_Compromisso () + ",";
      sql += util.getSQLString (ed.getNr_Documento ()) + ",";
      sql += ed.getNr_Parcela () == null ? "null," : ed.getNr_Parcela () + ",";
      sql += "'" + ed.getDt_Emissao () + "',";
      sql += ed.getDt_Liberado () == null ? "null," : "'" + ed.getDt_Liberado () + "',";
      sql += "'" + ed.getDt_Vencimento () + "',";
      sql += "'" + dt_Vencimento + "',";
      sql += ed.getVl_Compromisso () + ",";
      sql += ed.getVl_Multa_Apos_Vencimento () == null ? "null," : ed.getVl_Multa_Apos_Vencimento () + ",";
      sql += ed.getVl_Juro_Mora_dia () == null ? "null," : ed.getVl_Juro_Mora_dia () + ",";
      sql += ed.getVl_Taxa_Banco () == null ? "null," : ed.getVl_Taxa_Banco () + ",";
      sql += ed.getTx_Observacao () == null ? "null," : "'" + ed.getTx_Observacao ().trim () + "',";
      sql += ed.getDM_Tipo_Pagamento () == null ? "null," : "'" + ed.getDM_Tipo_Pagamento () + "',";
      sql += Nr_Compromisso + ",";
      sql += "'" + ed.getDt_stamp () + "',";
      sql += "'" + ed.getUsuario_Stamp () + "',";
      sql += "'" + ed.getDm_Stamp () + "',";
      sql += ed.getVl_Desconto_Ate_Vencimento () == null ? "null," : ed.getVl_Desconto_Ate_Vencimento () + ",";
      sql += ed.getVl_Saldo () + ",";
      sql += ed.getOid_Tipo_Documento () + ",";
      if (ed.getOID_Movimento_Ordem_Servico ()!=null) {
          sql += ed.getOID_Movimento_Ordem_Servico ().intValue() + ",";
      }else {
    	  sql +="0,";
      }

      sql += ed.getOid_Conta () + ",";
      sql += ed.getOid_Conta_Credito () + ",";
      sql += "'" + ed.getOid_Pessoa () + "',";
      sql += ed.getOid_Centro_Custo () + ",";
      sql += ed.getOid_Unidade () + ",";
      sql += ed.getOid_Natureza_Operacao () == null ? "null," : ed.getOid_Natureza_Operacao () + ",";
      sql += ed.getOid_Unidade_Pagamento () + ",";
      sql += ed.getOid_Moeda () + ",";
      sql += ed.getOID_Usuario () + ",";
      sql += ed.getVL_Cotacao () + ",";
      sql += ed.getVL_Cotacao_Padrao () + ",";
      sql += "'" + ed.getDT_Cotacao () + "',";
      sql += ed.getDt_Entrada () == null ? "null," : "'" + ed.getDt_Entrada () + "',";
      //      sql += ed.getVl_Imposto() + ",";
      //      sql += ed.getPe_Imposto() + ",";
      sql += "'N'";
      sql += ",'" + ed.getCD_Barra () + "')";

      // System.out.println (sql);

      executasql.executarUpdate (sql);

      if (ed.getOid_Fatura_Compromisso()>0){
        sql = " UPDATE Faturas_Compromissos set OID_Compromisso ="+ed.getOid_Compromisso() +
              " WHERE  Faturas_Compromissos.oid_fatura_compromisso="+ ed.getOid_Fatura_Compromisso();
        executasql.executarUpdate (sql);

        sql = " UPDATE Compromissos set oid_compromisso_vincula="+ed.getOid_Compromisso() +
              " , dt_liberado = null " +
              " , vl_saldo=0 " +
              " WHERE  Compromissos.oid_fatura_compromisso="+ ed.getOid_Fatura_Compromisso();

        executasql.executarUpdate (sql);

      }

    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "inclui()");
    }
    return ed;
  }

  public CompromissoED getByRecord (CompromissoED ed) throws Excecoes {

    String sql = null;

    CompromissoED edVolta = new CompromissoED ();
    FormataDataBean dataFormatada = new FormataDataBean ();
    ResultSet resTP = null;

    try {

      sql = "select *, Compromissos.oid_usuario from Compromissos, pessoas, centros_Custos, tipos_documentos, contas where " +
          " compromissos.oid_pessoa = pessoas.oid_pessoa and" +
          " compromissos.oid_conta = contas.oid_conta and" +
          " compromissos.oid_centro_custo = centros_custos.oid_centro_custo and" +
          " compromissos.oid_tipo_documento = tipos_documentos.oid_tipo_documento";

      if (ed.getOid_Compromisso () != null && ed.getOid_Compromisso ().intValue () > 0) {
        sql += " and compromissos.oid_Compromisso = " + ed.getOid_Compromisso ();
      }else{
        if (ed.getNr_Compromisso () != null && !ed.getNr_Compromisso ().equals ("") && !ed.getNr_Compromisso ().equals ("null")) {
          sql += " and compromissos.NR_Compromisso = " + ed.getNr_Compromisso ();
        }
        else {
          sql += " and compromissos.Nr_Documento = " + util.getSQLString (ed.getNr_Documento ());
          sql += " and compromissos.Nr_Parcela = " + ed.getNr_Parcela ();
          sql += " and compromissos.OID_Pessoa = " + util.getSQLString (ed.getOid_Pessoa ());
        }
      }
          // System.out.println("oid comp->>>>>>>" + sql);

      ResultSet res = this.executasql.executarConsulta (sql);
      try {
        while (res.next ()) {
          // System.out.println("oid comp->>>>>>>" + res.getInt ("oid_compromisso"));

          edVolta = new CompromissoED ();
          edVolta.setOid_Compromisso (new Integer (res.getInt ("oid_compromisso")));

          String OID_Compromisso_Vincula = res.getString ("oid_compromisso_Vincula");
          if (OID_Compromisso_Vincula != null) {
            edVolta.setOid_Compromisso_Vinculado (new Integer (OID_Compromisso_Vincula));
          }

          String OID_Compromisso_Parcela = res.getString ("oid_compromisso_Parcela");
          if (OID_Compromisso_Parcela != null) {
            edVolta.setOid_Compromisso_Parcela (new Integer (OID_Compromisso_Parcela));
          }

          edVolta.setDM_Lote_Pagamento (res.getString ("DM_Lote_Pagamento"));

          if ("S".equals(res.getString ("DM_Lote_Pagamento"))) {
              String query = " SELECT Lotes_Compromissos.oid_Lote_Pagamento " +
                             " FROM   Lotes_Compromissos "  +
                             " WHERE  Lotes_Compromissos.oid_Compromisso=" + res.getString ("oid_compromisso");
              ResultSet resUni2 = this.executasql.executarConsulta (query);
              try {
                if (resUni2.next ()) {
                  edVolta.setOid_Lote_Pagamento(resUni2.getLong ("oid_Lote_Pagamento"));
                }
              }
              finally {
                util.closeResultset (resUni2);
              }
            }


          edVolta.setOid_Pessoa (res.getString ("oid_pessoa"));
          edVolta.setNr_CNPJ_CPF (res.getString ("nr_cnpj_cpf"));
          edVolta.setNm_Razao_Social (res.getString ("nm_razao_Social"));

          edVolta.setOid_Centro_Custo (new Integer (res.getInt ("oid_centro_custo")));
          edVolta.setCd_Centro_Custo (res.getString ("cd_Centro_custo"));
          edVolta.setNm_Centro_Custo (res.getString ("nm_centro_Custo"));
          edVolta.setOid_Fatura_Compromisso(res.getLong("Oid_Fatura_Compromisso"));
          edVolta.setOid_Conta (new Integer (res.getInt ("oid_conta")));
          edVolta.setCd_Conta (res.getString ("cd_conta"));
          edVolta.setNm_Conta (res.getString ("nm_Conta"));
          edVolta.setCD_Barra (res.getString ("CD_Barra"));

          edVolta.setOID_Usuario(new Long (res.getInt ("oid_Usuario")).longValue());

          edVolta.setOID_Movimento_Ordem_Servico (new Integer (res.getInt ("OID_Movimento_Ordem_Servico")));

          edVolta.setOid_Tipo_Documento (new Integer (res.getInt ("oid_tipo_documento")));
          edVolta.setCd_Tipo_Documento (res.getString ("cd_tipo_documento"));
          edVolta.setNm_Tipo_Documento (res.getString ("nm_tipo_documento"));
          edVolta.setDM_Debito_Credito (res.getString ("DM_Debito_Credito"));

          String sql2 = " select cd_Unidade, nm_razao_social from pessoas, unidades where pessoas.oid_pessoa = unidades.oid_pessoa and " +
              " unidades.oid_unidade = " + Integer.parseInt (res.getString ("oid_unidade"));
          ResultSet resUni = this.executasql.executarConsulta (sql2);
          try {
            if (resUni.next ()) {
              edVolta.setCd_Unidade (resUni.getString ("cd_unidade"));
              edVolta.setNm_Fantasia (resUni.getString ("nm_razao_social"));
            }
          }
          finally {
            util.closeResultset (resUni);
          }

          edVolta.setOid_Unidade (new Long (res.getLong ("oid_unidade")));

          edVolta.setNr_Compromisso (new Integer (res.getInt ("nr_Compromisso")));

          FormataDataBean DataFormatada = new FormataDataBean ();
          DataFormatada.setDT_FormataData (res.getString ("dt_vencimento"));
          edVolta.setDt_Vencimento (DataFormatada.getDT_FormataData ());

          DataFormatada.setDT_FormataData (res.getString ("dt_liberado"));
          edVolta.setDt_Liberado (DataFormatada.getDT_FormataData ());
          edVolta.setOID_Usuario_Liberacao(new Long (res.getInt ("OID_Usuario_Liberacao")).longValue());

          DataFormatada.setDT_FormataData (res.getString ("dt_Aprovacao"));
          edVolta.setDT_Aprovacao (DataFormatada.getDT_FormataData ());
          edVolta.setOID_Usuario_Aprovacao(new Long (res.getInt ("OID_Usuario_Aprovacao")).longValue());


          DataFormatada.setDT_FormataData (res.getString ("dt_Vencimento_Original"));
          edVolta.setDT_Vencimento_Original (DataFormatada.getDT_FormataData ());

          DataFormatada.setDT_FormataData (res.getString ("dt_Cartorio"));
          edVolta.setDT_Cartorio (DataFormatada.getDT_FormataData ());

          DataFormatada.setDT_FormataData (res.getString ("dt_entrada"));
          edVolta.setDt_Entrada (DataFormatada.getDT_FormataData ());

          DataFormatada.setDT_FormataData (res.getString ("dt_emissao"));
          edVolta.setDt_Emissao (DataFormatada.getDT_FormataData ());

          DataFormatada.setDT_FormataData (res.getString ("dt_Stamp"));
          edVolta.setDt_stamp (DataFormatada.getDT_FormataData ());

          edVolta.setNr_Compromisso (new Integer (res.getInt ("nr_compromisso")));
          edVolta.setNr_Documento (res.getString ("nr_documento"));
          edVolta.setNr_Parcela (new Integer (res.getInt ("nr_parcela")));
          edVolta.setTx_Observacao (util.getValueDef (res.getString ("tx_observacao") , ""));
          edVolta.setDM_Tipo_Pagamento (res.getString ("DM_Tipo_Pagamento"));
          edVolta.setDM_Cartorio (res.getString ("DM_Cartorio"));
          edVolta.setDM_Agendamento (res.getString ("DM_Agendamento"));
          edVolta.setNM_Favorecido(" ");
          edVolta.setCD_Banco_Favorecido(" ");
          edVolta.setNM_Banco_Favorecido(" ");
          edVolta.setNM_Agencia_Favorecido(" ");
          edVolta.setNR_Identificacao_Favorecido(" ");
          edVolta.setNR_Conta_Corrente_Favorecido(" ");
          if ("S".equals(res.getString ("DM_Agendamento"))){
            edVolta.setNM_Favorecido (res.getString ("NM_Favorecido"));
            edVolta.setCD_Banco_Favorecido (res.getString ("CD_Banco_Favorecido"));
            edVolta.setNM_Banco_Favorecido (res.getString ("NM_Banco_Favorecido"));
            edVolta.setNM_Agencia_Favorecido (res.getString ("NM_Agencia_Favorecido"));
            edVolta.setNR_Identificacao_Favorecido (res.getString ("NR_Identificacao_Favorecido"));
            edVolta.setNR_Conta_Corrente_Favorecido (res.getString ("NR_Conta_Corrente_Favorecido"));
          }

          edVolta.setVl_Compromisso (new Double (res.getDouble ("vl_compromisso")));
          edVolta.setVl_Desconto_Ate_Vencimento (new Double (res.getDouble ("vl_desconto_ate_vencimento")));
          edVolta.setVl_Juro_Mora_dia (new Double (res.getDouble ("vl_juro_mora_dia")));
          edVolta.setVl_Taxa_Banco (new Double (res.getDouble ("vl_Taxa_Banco")));
          edVolta.setVl_Imposto (new Double (res.getDouble ("vl_Imposto")));
          edVolta.setPe_Imposto (new Double (res.getDouble ("pe_Imposto")));
          edVolta.setVl_Multa_Apos_Vencimento (new Double (res.getDouble ("vl_multa_apos_vencimento")));
          edVolta.setVl_Saldo (new Double (res.getDouble ("vl_saldo")));
          edVolta.setVL_Custas (res.getDouble ("VL_Custas"));


          sql = " SELECT MAX(nr_Parcela) as nr_Parcela FROM Compromissos " +
                " WHERE  compromissos.oid_pessoa = '" + res.getString ("oid_pessoa") + "'" +
                " AND    compromissos.nr_documento = '" + res.getString ("nr_documento")+ "'";
          resTP = this.executasql.executarConsulta (sql);
          while (resTP.next ()) {
            edVolta.setNr_Proxima_Parcela(resTP.getInt("nr_Parcela")+1);
          }

          sql = " select SUM(VL_COMPROMISSO) as VL_COMPROMISSO from Compromissos where " +
              " compromissos.oid_Compromisso_vincula = " + edVolta.getOid_Compromisso ();

          edVolta.setVL_Compromissos_Vinculados (new Double (0.0));

          resTP = this.executasql.executarConsulta (sql);
          try {
            while (resTP.next ()) {
              edVolta.setVL_Compromissos_Vinculados (new Double (resTP.getDouble ("VL_COMPROMISSO")));
            }
          }
          finally {
            util.closeResultset (resTP);
          }
          edVolta.setOid_Pessoa (res.getString ("oid_pessoa"));

          edVolta.setOid_Unidade_Pagamento (res.getInt ("Oid_Unidade_Pagamento"));
          edVolta.setOid_Moeda (res.getInt ("Oid_Moeda"));
          if (edVolta.getOid_Moeda () > 0) {
            edVolta.edMoeda = MoedaBean.getByOID (edVolta.getOid_Moeda ());
          }
          edVolta.setVL_Cotacao (res.getDouble ("VL_Cotacao"));
          edVolta.setVL_Cotacao_Padrao (res.getDouble ("VL_Cotacao_Padrao"));
          edVolta.setDT_Cotacao (dataFormatada.getDT_FormataData (res.getString ("DT_Cotacao")));
          //*** VALOR SALDO ATUALIZADO
           edVolta.setVL_Saldo_Atualizado (edVolta.getVl_Saldo ().doubleValue ());
          if (edVolta.getOid_Moeda () > 0 && edVolta.getOid_Moeda () != Parametro_FixoED.getInstancia ().getOID_Moeda_Padrao ()) {
          }

          if (edVolta.getOid_Unidade_Pagamento () > 0) {
            String query = " SELECT cd_Unidade, nm_razao_social FROM pessoas, unidades WHERE pessoas.oid_pessoa = unidades.oid_pessoa AND " +
                " unidades.oid_unidade = " + edVolta.getOid_Unidade_Pagamento ();
            ResultSet resUni2 = this.executasql.executarConsulta (query);
            try {
              if (resUni2.next ()) {
                edVolta.setCD_Unidade_Pagamento (resUni2.getString ("cd_unidade"));
                edVolta.setNM_Unidade_Pagamento (resUni2.getString ("nm_razao_social"));
              }
            }
            finally {
              util.closeResultset (resUni2);
            }
          }
        }
      }
      finally {
        util.closeResultset (res);
      }
    }
    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "getByRecord()");
    }
    return edVolta;
  }

  public CompromissoED getByRecord_Com_Lote (CompromissoED ed) throws Excecoes {

	    String sql = null;

	    CompromissoED edVolta = new CompromissoED ();
	    FormataDataBean dataFormatada = new FormataDataBean ();
	    ResultSet resTP = null;

	    try {

	      sql = "select *, Compromissos.oid_usuario, compromissos.oid_compromisso from Compromissos, lotes_compromissos, pessoas, centros_Custos, tipos_documentos, contas where " +
	          " compromissos.oid_pessoa = pessoas.oid_pessoa and" +
	          " compromissos.oid_conta = contas.oid_conta and" +
	          " compromissos.oid_centro_custo = centros_custos.oid_centro_custo and" +
	          " compromissos.oid_tipo_documento = tipos_documentos.oid_tipo_documento and" +
	          " compromissos.oid_compromisso = lotes_compromissos.oid_compromisso ";


	      if (ed.getOid_Compromisso () != null && ed.getOid_Compromisso ().intValue () > 0) {
		      sql += " and compromissos.oid_Compromisso = " + ed.getOid_Compromisso ();
		      sql += " and Lotes_Compromissos.oid_Lote_Pagamento = " + ed.getOid_Lote_Pagamento();
	      }else{
              sql += " and compromissos.NR_Compromisso = " + ed.getNr_Compromisso ();
		      sql += " and Lotes_Compromissos.oid_Lote_Pagamento = " + ed.getOid_Lote_Pagamento();
	      }

	      ResultSet res = this.executasql.executarConsulta (sql);
	      try {
	        while (res.next ()) {

	          edVolta = new CompromissoED ();
	          edVolta.setOid_Compromisso (new Integer (res.getInt ("oid_compromisso")));

	          String OID_Compromisso_Vincula = res.getString ("oid_compromisso_Vincula");
	          if (OID_Compromisso_Vincula != null) {
	            edVolta.setOid_Compromisso_Vinculado (new Integer (OID_Compromisso_Vincula));
	          }

	          String OID_Compromisso_Parcela = res.getString ("oid_compromisso_Parcela");
	          if (OID_Compromisso_Parcela != null) {
	            edVolta.setOid_Compromisso_Parcela (new Integer (OID_Compromisso_Parcela));
	          }

	          edVolta.setDM_Lote_Pagamento (res.getString ("DM_Lote_Pagamento"));

              edVolta.setOid_Lote_Pagamento(res.getLong ("oid_Lote_Pagamento"));

	          edVolta.setOid_Pessoa (res.getString ("oid_pessoa"));
	          edVolta.setNr_CNPJ_CPF (res.getString ("nr_cnpj_cpf"));
	          edVolta.setNm_Razao_Social (res.getString ("nm_razao_Social"));

	          edVolta.setOid_Centro_Custo (new Integer (res.getInt ("oid_centro_custo")));
	          edVolta.setCd_Centro_Custo (res.getString ("cd_Centro_custo"));
	          edVolta.setNm_Centro_Custo (res.getString ("nm_centro_Custo"));
	          edVolta.setOid_Fatura_Compromisso(res.getLong("Oid_Fatura_Compromisso"));
	          edVolta.setOid_Conta (new Integer (res.getInt ("oid_conta")));
	          edVolta.setCd_Conta (res.getString ("cd_conta"));
	          edVolta.setNm_Conta (res.getString ("nm_Conta"));
	          edVolta.setCD_Barra (res.getString ("CD_Barra"));

	          edVolta.setOID_Usuario(new Long (res.getInt ("oid_Usuario")).longValue());

	          edVolta.setOid_Tipo_Documento (new Integer (res.getInt ("oid_tipo_documento")));
	          edVolta.setCd_Tipo_Documento (res.getString ("cd_tipo_documento"));
	          edVolta.setNm_Tipo_Documento (res.getString ("nm_tipo_documento"));
	          edVolta.setDM_Debito_Credito (res.getString ("DM_Debito_Credito"));

	          edVolta.setOid_Unidade (new Long (res.getLong ("oid_unidade")));

	          edVolta.setNr_Compromisso (new Integer (res.getInt ("nr_Compromisso")));

	          FormataDataBean DataFormatada = new FormataDataBean ();
	          DataFormatada.setDT_FormataData (res.getString ("dt_vencimento"));
	          edVolta.setDt_Vencimento (DataFormatada.getDT_FormataData ());

	          DataFormatada.setDT_FormataData (res.getString ("dt_liberado"));
	          edVolta.setDt_Liberado (DataFormatada.getDT_FormataData ());
	          edVolta.setOID_Usuario_Liberacao(new Long (res.getInt ("OID_Usuario_Liberacao")).longValue());

	          DataFormatada.setDT_FormataData (res.getString ("dt_Aprovacao"));
	          edVolta.setDT_Aprovacao (DataFormatada.getDT_FormataData ());
	          edVolta.setOID_Usuario_Aprovacao(new Long (res.getInt ("OID_Usuario_Aprovacao")).longValue());


	          DataFormatada.setDT_FormataData (res.getString ("dt_Vencimento_Original"));
	          edVolta.setDT_Vencimento_Original (DataFormatada.getDT_FormataData ());

	          DataFormatada.setDT_FormataData (res.getString ("dt_Cartorio"));
	          edVolta.setDT_Cartorio (DataFormatada.getDT_FormataData ());

	          DataFormatada.setDT_FormataData (res.getString ("dt_entrada"));
	          edVolta.setDt_Entrada (DataFormatada.getDT_FormataData ());

	          DataFormatada.setDT_FormataData (res.getString ("dt_emissao"));
	          edVolta.setDt_Emissao (DataFormatada.getDT_FormataData ());

	          DataFormatada.setDT_FormataData (res.getString ("dt_Stamp"));
	          edVolta.setDt_stamp (DataFormatada.getDT_FormataData ());

	          edVolta.setNr_Compromisso (new Integer (res.getInt ("nr_compromisso")));
	          edVolta.setNr_Documento (res.getString ("nr_documento"));
	          edVolta.setNr_Parcela (new Integer (res.getInt ("nr_parcela")));
	          edVolta.setTx_Observacao (util.getValueDef (res.getString ("tx_observacao") , ""));
	          edVolta.setDM_Tipo_Pagamento (res.getString ("DM_Tipo_Pagamento"));
	          edVolta.setDM_Cartorio (res.getString ("DM_Cartorio"));
	          edVolta.setDM_Agendamento (res.getString ("DM_Agendamento"));
	          edVolta.setNM_Favorecido(" ");
	          edVolta.setCD_Banco_Favorecido(" ");
	          edVolta.setNM_Banco_Favorecido(" ");
	          edVolta.setNM_Agencia_Favorecido(" ");
	          edVolta.setNR_Identificacao_Favorecido(" ");
	          edVolta.setNR_Conta_Corrente_Favorecido(" ");
	          if ("S".equals(res.getString ("DM_Agendamento"))){
	            edVolta.setNM_Favorecido (res.getString ("NM_Favorecido"));
	            edVolta.setCD_Banco_Favorecido (res.getString ("CD_Banco_Favorecido"));
	            edVolta.setNM_Banco_Favorecido (res.getString ("NM_Banco_Favorecido"));
	            edVolta.setNM_Agencia_Favorecido (res.getString ("NM_Agencia_Favorecido"));
	            edVolta.setNR_Identificacao_Favorecido (res.getString ("NR_Identificacao_Favorecido"));
	            edVolta.setNR_Conta_Corrente_Favorecido (res.getString ("NR_Conta_Corrente_Favorecido"));
	          }

	          edVolta.setVl_Compromisso (new Double (res.getDouble ("vl_compromisso")));
	          edVolta.setVl_Desconto_Ate_Vencimento (new Double (res.getDouble ("vl_desconto_ate_vencimento")));
	          edVolta.setVl_Juro_Mora_dia (new Double (res.getDouble ("vl_juro_mora_dia")));
	          edVolta.setVl_Taxa_Banco (new Double (res.getDouble ("vl_Taxa_Banco")));
	          edVolta.setVl_Imposto (new Double (res.getDouble ("vl_Imposto")));
	          edVolta.setPe_Imposto (new Double (res.getDouble ("pe_Imposto")));
	          edVolta.setVl_Multa_Apos_Vencimento (new Double (res.getDouble ("vl_multa_apos_vencimento")));
	          edVolta.setVl_Saldo (new Double (res.getDouble ("vl_saldo")));
	          edVolta.setVL_Custas (res.getDouble ("VL_Custas"));

	          edVolta.setOid_Pessoa (res.getString ("oid_pessoa"));

	          edVolta.setOid_Unidade_Pagamento (res.getInt ("Oid_Unidade_Pagamento"));
	          edVolta.setOid_Moeda (res.getInt ("Oid_Moeda"));

	        }
	      }
	      finally {
	        util.closeResultset (res);
	      }
	    }
	    catch (Exception e) {
	      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "getByRecord()");
	    }
	    return edVolta;
	  }

  public CompromissoED getByRecord_Vinculado (CompromissoED ed) throws Excecoes {

    String sql = null;

    CompromissoED edVolta = new CompromissoED ();

    try {

      sql = "select * from Compromissos, pessoas, tipos_documentos where " + " compromissos.oid_pessoa = pessoas.oid_pessoa and"
          + " compromissos.oid_tipo_documento = tipos_documentos.oid_tipo_documento";

      if (ed.getOid_Compromisso () != null && !ed.getOid_Compromisso ().equals ("") && !ed.getOid_Compromisso ().equals ("null")) {
        sql += " and compromissos.oid_Compromisso = " + ed.getOid_Compromisso ();
      }

      if (ed.getNr_Compromisso () != null && !ed.getNr_Compromisso ().equals ("") && !ed.getNr_Compromisso ().equals ("null")) {
        sql += " and compromissos.NR_Compromisso = " + ed.getNr_Compromisso ();
      }
      ResultSet res = null;

      res = this.executasql.executarConsulta (sql);
      FormataDataBean dataFormatada = new FormataDataBean ();

      while (res.next ()) {
        edVolta = new CompromissoED ();
        edVolta.setOid_Compromisso (new Integer (res.getInt ("oid_compromisso")));

        String OID_Compromisso_Vincula = res.getString ("oid_compromisso_Vincula");

        if (OID_Compromisso_Vincula != null) {
          edVolta.setOid_Compromisso_Vinculado (new Integer (OID_Compromisso_Vincula));
        }
        edVolta.setDM_Lote_Pagamento (res.getString ("DM_Lote_Pagamento"));

        edVolta.setOID_Movimento_Ordem_Servico (new Integer (res.getInt ("OID_Movimento_Ordem_Servico")));
        dataFormatada.setDT_FormataData (res.getString ("dt_liberado"));
        edVolta.setDt_Liberado (dataFormatada.getDT_FormataData ());

        String DM_Tipo_Pagamento = res.getString ("DM_Tipo_Pagamento");
        if (DM_Tipo_Pagamento != null) {
          edVolta.setDM_Tipo_Pagamento (DM_Tipo_Pagamento);
        }

        edVolta.setOid_Pessoa (res.getString ("oid_pessoa"));
        edVolta.setNr_CNPJ_CPF (res.getString ("nr_cnpj_cpf"));
        edVolta.setNm_Razao_Social (res.getString ("nm_razao_Social"));

        edVolta.setOid_Tipo_Documento (new Integer (res.getInt ("oid_tipo_documento")));
        edVolta.setCd_Tipo_Documento (res.getString ("cd_tipo_documento"));
        edVolta.setNm_Tipo_Documento (res.getString ("nm_tipo_documento"));

        edVolta.setNr_Compromisso (new Integer (res.getInt ("nr_Compromisso")));

        edVolta.setVl_Saldo (new Double (res.getDouble ("vl_saldo")));
        edVolta.setNr_Compromisso (new Integer (res.getInt ("nr_compromisso")));
        edVolta.setNr_Documento (res.getString ("nr_documento"));

        String nr_Parcela = res.getString ("nr_parcela");
        if (nr_Parcela != null) {
          edVolta.setNr_Parcela (new Integer (nr_Parcela));
        }

        edVolta.setVl_Compromisso (new Double (res.getString ("vl_compromisso")));

        sql = "select * from Compromissos where " + " compromissos.oid_Compromisso_vincula = " + edVolta.getOid_Compromisso ();

        ResultSet resTP = null;

        resTP = this.executasql.executarConsulta (sql);

        edVolta.setDM_Vinculador ("N");

        while (resTP.next ()) {
          edVolta.setDM_Vinculador ("S");
        }
      }
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "getByRecord_Vinculado()");
    }

    return edVolta;
  }

  public CompromissoED getByRecord_Compromisso_Vinculado (CompromissoED ed) throws Excecoes {

    String sql = null;

    CompromissoED edVolta = new CompromissoED ();

    try {

      sql = "select " + " Compromissos_Principal.oid_compromisso         as oid_compromisso_Principal, " + " Compromissos_Principal.oid_compromisso_Vincula as oid_compromisso_Vincula_P, "
          + " Compromissos_Principal.DM_Lote_Pagamento       as DM_Lote_Pagamento_Principal, " + " Compromissos_Principal.nr_parcela              as nr_parcela_Principal, "
          + " Compromissos_Principal.vl_compromisso          as vl_compromisso_Principal, " + " Compromissos_Principal.vl_saldo                as vl_saldo_Principal, "
          + " Compromissos_Principal.nr_documento            as nr_documento_Principal, " + " Compromissos_Principal.nr_Compromisso          as nr_Compromisso_Principal, "
          + " Pessoas_Principal.oid_pessoa                   as oid_pessoa_Principal, " + " Pessoas_Principal.nr_cnpj_cpf                  as nr_cnpj_cpf_Principal, "
          + " Pessoas_Principal.nm_razao_Social              as nm_razao_Social_Principal, " + " tipos_documentos_Principal.oid_tipo_documento  as oid_tipo_documento_Principal, "
          + " tipos_documentos_Principal.cd_tipo_documento   as cd_tipo_documento_Principal, " + " tipos_documentos_Principal.nm_tipo_documento   as nm_tipo_documento_Principal, "
          + " Compromissos_Vinculado.oid_compromisso            as oid_compromisso_Vincula, " + " Compromissos_Vinculado.oid_compromisso_Vincula   as oid_compromisso_Vincula_V, "
          + " Compromissos_Vinculado.DM_Lote_Pagamento         as DM_Lote_Pagamento_Vincula, " + " Compromissos_Vinculado.nr_parcela                as nr_parcela_Vincula, "
          + " Compromissos_Vinculado.vl_compromisso            as vl_compromisso_Vincula, " + " Compromissos_Vinculado.vl_saldo                  as vl_saldo_Vincula, "
          + " Compromissos_Vinculado.nr_documento              as nr_documento_Vincula, " + " Compromissos_Vinculado.nr_Compromisso            as nr_Compromisso_Vincula, "
          + " Pessoas_Vinculado.oid_pessoa                     as oid_pessoa_Vincula, " + " Pessoas_Vinculado.nr_cnpj_cpf                    as nr_cnpj_cpf_Vincula, "
          + " Pessoas_Vinculado.nm_razao_Social                as nm_razao_Social_Vincula, " + " tipos_documentos_Vinculado.oid_tipo_documento    as oid_tipo_documento_Vincula, "
          + " tipos_documentos_Vinculado.cd_tipo_documento     as cd_tipo_documento_Vincula, " + " tipos_documentos_Vinculado.nm_tipo_documento     as nm_tipo_documento_Vincula " + " from "
          + " Compromissos Compromissos_Principal, pessoas Pessoas_Principal, tipos_documentos tipos_documentos_Principal,"
          + " Compromissos Compromissos_Vinculado, pessoas Pessoas_Vinculado, tipos_documentos tipos_documentos_Vinculado " + " where "
          + " Compromissos_Principal.oid_Compromisso = Compromissos_Vinculado.oid_Compromisso_Vincula and" + " Compromissos_Principal.oid_pessoa = Pessoas_Principal.oid_pessoa and"
          + " Compromissos_Vinculado.oid_pessoa = Pessoas_Vinculado.oid_pessoa and" + " Compromissos_Principal.oid_tipo_documento = tipos_documentos_Principal.oid_tipo_documento and"
          + " Compromissos_Vinculado.oid_tipo_documento = tipos_documentos_Vinculado.oid_tipo_documento";

      if (ed.getOid_Compromisso () != null && !ed.getOid_Compromisso ().equals ("") && !ed.getOid_Compromisso ().equals ("null")) {
        sql += " and Compromissos_Vinculado.oid_Compromisso = " + ed.getOid_Compromisso ();
      }

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);

      while (res.next ()) {
        edVolta = new CompromissoED ();
        edVolta.setOid_Compromisso (new Integer (res.getInt ("oid_compromisso_Principal")));

        String OID_Compromisso_Vincula = res.getString ("oid_compromisso_Vincula");

        if (OID_Compromisso_Vincula != null) {
          edVolta.setOid_Compromisso_Vinculado (new Integer (OID_Compromisso_Vincula));
        }
        edVolta.setDM_Lote_Pagamento (res.getString ("DM_Lote_Pagamento_Principal"));

        edVolta.setOid_Pessoa (res.getString ("oid_pessoa_Principal"));
        edVolta.setNr_CNPJ_CPF (res.getString ("nr_cnpj_cpf_Principal"));
        edVolta.setNm_Razao_Social (res.getString ("nm_razao_Social_Principal"));

        edVolta.setOid_Tipo_Documento (new Integer (res.getInt ("oid_tipo_documento_Principal")));
        edVolta.setCd_Tipo_Documento (res.getString ("cd_tipo_documento_Principal"));
        edVolta.setNm_Tipo_Documento (res.getString ("nm_tipo_documento_Principal"));

        edVolta.setNr_Compromisso (new Integer (res.getInt ("nr_Compromisso_Principal")));

        edVolta.setVl_Saldo (new Double (res.getDouble ("vl_saldo_Principal")));
        edVolta.setNr_Documento (res.getString ("nr_documento_Principal"));

        String nr_Parcela = res.getString ("nr_parcela_Principal");
        if (nr_Parcela != null) {
          edVolta.setNr_Parcela (new Integer (nr_Parcela));
        }

        edVolta.setVl_Compromisso (new Double (res.getString ("vl_compromisso_Principal")));
        edVolta.setOid_pessoa_Vinculado (res.getString ("oid_pessoa_Vincula"));
        edVolta.setNr_cnpj_cpf_Vinculado (res.getString ("nr_cnpj_cpf_Vincula"));
        edVolta.setNm_razao_Social_Vinculado (res.getString ("nm_razao_Social_Vincula"));

        edVolta.setCd_tipo_documento_Vinculado (res.getString ("cd_tipo_documento_Vincula"));
        edVolta.setNm_tipo_documento_Vinculado (res.getString ("nm_tipo_documento_Vincula"));

        edVolta.setNr_Compromisso_Vinculado (new Integer (res.getInt ("nr_Compromisso_Vincula")));

        edVolta.setVl_saldo_Vinculado (new Double (res.getDouble ("vl_saldo_Vincula")));
        edVolta.setNr_documento_Vinculado (res.getString ("nr_documento_Vincula"));

        String nr_Parcela_Vinculado = res.getString ("nr_parcela_Vincula");
        if (nr_Parcela_Vinculado != null) {
          edVolta.setNr_parcela_Vinculado (new Integer (nr_Parcela_Vinculado));
        }

        edVolta.setVL_Compromissos_Vinculados (new Double (res.getString ("vl_compromisso_Vincula")));

      }
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "getByRecord_Compromisso_Vinculado()");
    }
    return edVolta;
  }

  public void altera (CompromissoED ed) throws Excecoes {

    String sql = null;

    try {

      sql = " UPDATE Compromissos SET ";
      sql += " nr_documento = " + util.getSQLString (ed.getNr_Documento ()) + ",";
      if (ed.getNr_Parcela () == null) {
        sql += "nr_parcela = null,";
      }
      else {
        sql += "nr_parcela = " + ed.getNr_Parcela () + ",";
      }
      sql += "dt_emissao = '" + ed.getDt_Emissao () + "',";
      sql += "dt_vencimento = '" + ed.getDt_Vencimento () + "',";
      sql += "dt_entrada = '" + ed.getDt_Entrada () + "',";
      sql += "vl_compromisso = " + ed.getVl_Compromisso () + ",";
      if (ed.getVl_Multa_Apos_Vencimento () == null) {
        sql += "vl_multa_apos_vencimento = null,";
      }
      else {
        sql += "vl_multa_apos_vencimento = " + ed.getVl_Multa_Apos_Vencimento () + ",";
      }
      if (ed.getVl_Imposto () == null) {
        sql += "vl_Imposto = null,";
      }
      else {
        sql += "vl_Imposto = " + ed.getVl_Imposto () + ",";
      }
      if (ed.getPe_Imposto () == null) {
        sql += "pe_Imposto = null,";
      }
      else {
        sql += "pe_Imposto = " + ed.getPe_Imposto () + ",";
      }
      if (ed.getVl_Juro_Mora_dia () == null) {
        sql += "vl_juro_mora_dia = null,";
      }
      else {
        sql += "vl_juro_mora_dia = " + ed.getVl_Juro_Mora_dia () + ",";
      }
      if (ed.getVl_Taxa_Banco () == null) {
        sql += "vl_Taxa_Banco = null,";
      }
      else {
        sql += "vl_Taxa_Banco = " + ed.getVl_Taxa_Banco () + ",";
      }
      if (ed.getTx_Observacao () == null) {
        sql += "tx_observacao = null,";
      }
      else {
        sql += "tx_observacao = '" + ed.getTx_Observacao ().trim () + "',";
      }
      if (ed.getDM_Tipo_Pagamento () == null) {
        sql += "DM_Tipo_Pagamento = null,";
      }
      else {
        sql += "DM_Tipo_Pagamento = '" + ed.getDM_Tipo_Pagamento () + "',";
      }
      sql += " DT_STAMP = '" + ed.getDt_stamp () + "', ";
      sql += " USUARIO_STAMP = '" + ed.getUsuario_Stamp () + "', ";
      sql += " DM_STAMP = '" + ed.getDm_Stamp () + "', ";
      sql += " CD_Barra = '" + ed.getCD_Barra () + "', ";
      if (ed.getVl_Desconto_Ate_Vencimento () == null) {
        sql += "null,";
      }
      else {
        sql += "Vl_Desconto_Ate_Vencimento = " + ed.getVl_Desconto_Ate_Vencimento () + ",";
      }
      sql += "vl_saldo = " + ed.getVl_Saldo () + ",";
      sql += "oid_tipo_documento = " + ed.getOid_Tipo_Documento () + ",";
      sql += "oid_conta = " + ed.getOid_Conta () + ",";
      sql += "oid_Conta_Credito = " + ed.getOid_Conta_Credito () + ",";
      sql += "oid_pessoa = '" + ed.getOid_Pessoa () + "',";
      sql += "oid_centro_custo=" + ed.getOid_Centro_Custo () + ",";
      sql += "oid_unidade=" + ed.getOid_Unidade () + ",";
      sql += "oid_unidade_Pagamento=" + ed.getOid_Unidade_Pagamento ();
      sql += " WHERE oid_compromisso = " + ed.getOid_Compromisso ();

      executasql.executarUpdate (sql);
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "altera()");
    }
  }

  public void altera_pago(CompromissoED ed) throws Excecoes {

	    String sql = null;

	    try {

	      sql = " UPDATE Compromissos SET ";
	      sql += " nr_documento = " + util.getSQLString (ed.getNr_Documento ()) + ",";

	      sql += "dt_emissao = '" + ed.getDt_Emissao () + "',";
	      sql += "dt_entrada = '" + ed.getDt_Entrada () + "',";
	      if (ed.getTx_Observacao () == null) {
//	        sql += "tx_observacao = null,";
	      }
	      else {
	        sql += "tx_observacao = '" + ed.getTx_Observacao ().trim () + "',";
	      }

	      sql += " DT_STAMP = '" + ed.getDt_stamp () + "', ";
	      sql += " USUARIO_STAMP = '" + ed.getUsuario_Stamp () + "', ";
	      sql += " DM_STAMP = '" + ed.getDm_Stamp () + "', ";

	      sql += "oid_conta = " + ed.getOid_Conta () + ",";
	      sql += "oid_centro_custo=" + ed.getOid_Centro_Custo () + ",";
	      sql += "oid_unidade=" + ed.getOid_Unidade () + ",";
	      sql += "oid_unidade_Pagamento=" + ed.getOid_Unidade_Pagamento ();

	      sql += " WHERE oid_compromisso = " + ed.getOid_Compromisso ();

	      executasql.executarUpdate (sql);
	    }
	    catch (Exception exc) {
	      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "altera()");
	    }
	  }

  public void vincula (CompromissoED ed) throws Excecoes {

    String sql = null;

    try {

      sql = "  update Compromissos set oid_compromisso_vincula = " + ed.getOid_Compromisso () + ", ";
      sql += "  VL_Saldo = 0 ";
      sql += "  WHERE oid_Compromisso = " + ed.getOid_Compromisso_Vinculado ();

      executasql.executarUpdate (sql);
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "vincula()");
    }
  }

  public void libera_Compromisso (CompromissoED ed) throws Excecoes {

    String sql = null;
    try {

      if (ed.getOid_Compromisso () == null) {
        sql = "  update Compromissos set Dt_Liberado = '" + Data.getDataDMY () + "'";
        sql += "  WHERE oid_Compromisso in ( " +
            " SELECT Compromissos.oid_Compromisso " +
            " FROM   Compromissos, Compromissos_Servicos, Movimentos_Ordens_Servicos, Ordens_Servicos " +
            " WHERE    compromissos.dt_liberado is null " +
            " AND    compromissos.oid_compromisso = Compromissos_Servicos.oid_compromisso " +
            " AND    Movimentos_Ordens_Servicos.oid_Movimento_Ordem_Servico = Compromissos_Servicos.oid_Movimento_Ordem_Servico " +
            " AND    Movimentos_Ordens_Servicos.oid_Ordem_Servico = Ordens_Servicos.oid_Ordem_Servico " +
            " AND    Compromissos_Servicos.dt_compromisso_Servico = '" + ed.getDt_Inicial () + "')";
      }
      else {
        String DT_Liberado = null;
        sql = " select DT_Liberado from Compromissos where " +
            "  compromissos.oid_Compromisso = " + ed.getOid_Compromisso ();

        ResultSet res = this.executasql.executarConsulta (sql);
        while (res.next ()) {
          DT_Liberado = res.getString ("DT_Liberado");
        }
        if (DT_Liberado == null) {
          sql = "  update Compromissos set Dt_Liberado = '" + Data.getDataDMY () + "', OID_Usuario_Liberacao=" + ed.getOID_Usuario_Liberacao ();
        }
        else {
          sql = "  update Compromissos set Dt_Liberado = null ";
        }
        sql += " WHERE oid_Compromisso = " + ed.getOid_Compromisso ();
      }

      executasql.executarUpdate (sql);
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "vincula()");
    }
  }

  public void aprova_Compromisso (CompromissoED ed) throws Excecoes {

    String sql = null;
    try {

      String DT_Aprovacao = null;
      sql = " select DT_Aprovacao from Compromissos where " +
          "  compromissos.oid_Compromisso = " + ed.getOid_Compromisso ();

      ResultSet res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        DT_Aprovacao = res.getString ("DT_Aprovacao");
      }
      if (DT_Aprovacao == null) {
        sql = "  update Compromissos set DT_Aprovacao = '" + Data.getDataDMY () + "', OID_Usuario_Aprovacao=" + ed.getOID_Usuario_Aprovacao() ;
      }
      else {
        sql = "  update Compromissos set DT_Aprovacao = null ";
      }
      sql += " WHERE oid_Compromisso = " + ed.getOid_Compromisso ();

      executasql.executarUpdate (sql);
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "vincula()");
    }
  }

  public void agenda_Pagamento (CompromissoED ed) throws Excecoes {

    String sql = null;
    try {

      sql = "  UPDATE Compromissos set  DM_Tipo_Pagamento='3',  DM_Agendamento='S', DT_Vencimento = '" + ed.getDt_Vencimento () + "', NM_Favorecido ='" + ed.getNM_Favorecido () + "', nr_identificacao_favorecido ='" + ed.getNR_Identificacao_Favorecido () + "', cd_banco_favorecido ='" + ed.getCD_Banco_Favorecido () + "', nm_banco_favorecido ='" + ed.getNM_Banco_Favorecido () + "', nm_agencia_favorecido ='" + ed.getNM_Agencia_Favorecido () + "', nr_conta_corrente_Favorecido ='" + ed.getNR_Conta_Corrente_Favorecido () + "'";
      sql += " WHERE oid_Compromisso = " + ed.getOid_Compromisso ();

      // System.out.println(sql);
      executasql.executarUpdate (sql);
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "vincula()");
    }
  }


  public void deleta (CompromissoED ed) throws Excecoes {

    String sql = null;
    long oid_Compromisso = 0;
    double vl_saldo_compromisso = 0;
    double vl_saldo_parcela = 0;
    ResultSet res = null;

    try {
      sql = " select Compromisso_Atual.vl_saldo as vl_saldo_compromisso, Compromisso_Atual.oid_compromisso,  ";
      sql += "        Compromisso_Parcela.vl_saldo as vl_saldo_parcela ";
      sql += " FROM   Compromissos  Compromisso_Atual, Compromissos Compromisso_Parcela";
      sql += " WHERE  Compromisso_Atual.oid_compromisso =  Compromisso_Parcela.oid_compromisso_Parcela";
      sql += " AND    Compromisso_Parcela.oid_Compromisso = " + ed.getOid_Compromisso ();

      res = this.executasql.executarConsulta (sql);

      while (res.next ()) {
        oid_Compromisso = res.getLong ("oid_compromisso");
        vl_saldo_compromisso = res.getDouble ("vl_saldo_compromisso");
        vl_saldo_parcela = res.getDouble ("vl_saldo_parcela");
      }
      if (vl_saldo_compromisso > 0) {
        vl_saldo_compromisso = vl_saldo_compromisso + vl_saldo_parcela;
        sql = "update Compromissos set vl_saldo = " + vl_saldo_compromisso + "  WHERE oid_Compromisso = ";
        sql += oid_Compromisso;

        executasql.executarUpdate (sql);
      }

      sql = "delete from Movimentos_Ordens WHERE oid_Compromisso = ";
      sql += ed.getOid_Compromisso ();
      executasql.executarUpdate (sql);

      sql = "delete from Lotes_Compromissos WHERE (dm_situacao = 'C'  OR dm_situacao = 'E' ) and oid_Compromisso = ";
      sql += ed.getOid_Compromisso ();
      executasql.executarUpdate (sql);

      sql = "delete from Compromissos_Servicos WHERE oid_Compromisso = ";
      sql += ed.getOid_Compromisso ();
      executasql.executarUpdate (sql);

      sql = "delete from Compromissos WHERE oid_Compromisso = ";
      sql += ed.getOid_Compromisso ();
      executasql.executarUpdate (sql);

    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "deleta()");
    }
  }

  public void deleta_Parcelamento (CompromissoED ed) throws Excecoes {

	    String sql = null;
	    long oid_Compromisso = 0;
	    double vl_saldo_compromisso = 0;
	    double vl_saldo_parcela = 0;
	    ResultSet res = null;

	    try {

	      sql = "delete from Compromissos WHERE nr_Compromisso between ";
	      sql += ed.getNr_Compromisso();
	      sql += " and ";
	      sql += ed.getNr_Compromisso_Final();
	      sql += " and oid_pessoa = '";
	      sql += ed.getOid_Pessoa();
	      sql += "'";
	      sql += " and nr_documento = ";
	      sql += ed.getNr_Documento();

	      executasql.executarUpdate (sql);

	    }
	    catch (Exception exc) {
	      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "deleta()");
	    }
	  }

  public void deleta_Selecao_Compromissos (CompromissoED ed) throws Excecoes {

		ArrayList edDelete = new ArrayList ();

		try {
		  	// Gera a lista de compromissos que ser�o exclu�dos
		   	edDelete = this.lista(ed);
		   	// Pega a lista gerada e manda deletar
		   	this.deleta_Lista(edDelete);
		}
		  catch (Exception exc) {
		  throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "deleta_Selecao_Compromissos(CompromissoED ed)");
		}
	  }

  public void deleta_Lista (ArrayList lista) throws Excecoes {
    try {
      for (int i = 0; i < lista.size (); i++) {
        // System.out.println (" deleta_Lista " + i);
        CompromissoED compromissoED = (CompromissoED) lista.get (i);

        // System.out.println (" deleta Comp= " + compromissoED.getNr_Compromisso());

        this.deleta(compromissoED);
      }
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "deleta()");
    }
  }

  public void deleta_Vinculo (CompromissoED ed) throws Excecoes {

    String sql = null;
    ResultSet res = null;

    try {
      sql = " select * from Compromissos where " + " compromissos.VL_Compromisso = compromissos.VL_Saldo and" + " compromissos.oid_Compromisso = " + ed.getOid_Compromisso ();
      res = this.executasql.executarConsulta (sql);
      String l_Saldo = "N";
      while (res.next ()) {
        l_Saldo = "S";
      }

      if (l_Saldo.equals ("S")) {

        sql = "update Compromissos set oid_compromisso_vincula = null, vl_saldo=vl_compromisso WHERE oid_Compromisso = ";
        sql += ed.getOid_Compromisso_Vinculado ();

        executasql.executarUpdate (sql);
      }
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "deleta_Vinculo()");
    }
  }

  public ArrayList lista (CompromissoED edComp) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    CompromissoPesquisaED ed = (CompromissoPesquisaED) edComp;

    try {
      sql = " SELECT * " +
          " FROM Compromissos" +
          "		,Pessoas" +
          "		,Tipos_Documentos " +
          " WHERE Compromissos.oid_Pessoa = Pessoas.oid_Pessoa " +
          "   AND Compromissos.oid_Tipo_Documento = Tipos_Documentos.oid_Tipo_Documento ";

      if ("P".equals (ed.getDM_Situacao ())) { //pago
        sql = " SELECT * " +
            " FROM Compromissos" +
            "		,Movimentos_Compromissos" +
            "		,pessoas" +
            "		,tipos_documentos " +
            " WHERE compromissos.oid_pessoa = pessoas.oid_pessoa " +
            "   AND compromissos.oid_tipo_documento = tipos_Documentos.oid_tipo_documento " +
            "   AND compromissos.oid_Compromisso = Movimentos_Compromissos.oid_Compromisso ";
        if (JavaUtil.doValida (ed.getData_Pagamento_Inicial ())) {
          sql += "   AND Movimentos_Compromissos.dt_Pagamento >= '" + ed.getData_Pagamento_Inicial () + "'";
        }
        if (JavaUtil.doValida (ed.getData_Pagamento_Final ())) {
          sql += "   AND Movimentos_Compromissos.dt_Pagamento <= '" + ed.getData_Pagamento_Final () + "'";
        }
      }
      if (ed.getOid_Compromisso () != null && ed.getOid_Compromisso ().intValue () > 0) {
        sql += " and compromissos.oid_compromisso = " + ed.getOid_Compromisso ();
      }
      if (ed.getOid_Compromisso_Vinculado () != null && ed.getOid_Compromisso_Vinculado ().intValue () > 0) {
        sql += " and compromissos.oid_compromisso_vincula = " + ed.getOid_Compromisso_Vinculado ();
      }
      if (ed.getNr_Compromisso () != null && ed.getNr_Compromisso ().intValue () > 0) {
        sql += " and compromissos.nr_compromisso = " + ed.getNr_Compromisso ();
      }

      if (ed.getOid_Conta () != null && ed.getOid_Conta ().intValue () > 0) {
        sql += " and compromissos.oid_conta = " + ed.getOid_Conta ();
      }

      if (ed.getOid_Unidade () != null && ed.getOid_Unidade ().intValue () > 0) {
        sql += " and compromissos.oid_unidade = " + ed.getOid_Unidade ();
      }

      if (ed.getOid_Tipo_Documento () != null && ed.getOid_Tipo_Documento ().intValue () > 0) {
        sql += " and compromissos.oid_Tipo_Documento = " + ed.getOid_Tipo_Documento ();
      }

      if (ed.getOid_Unidade_Pagamento () > 0) {
        sql += " and compromissos.oid_unidade_pagamento = " + ed.getOid_Unidade_Pagamento ();
      }

      if (JavaUtil.doValida (ed.getDM_Lote_Pagamento ())) {
        sql += " and compromissos.DM_Lote_Pagamento = '" + ed.getDM_Lote_Pagamento () + "'";
      }
      if (JavaUtil.doValida (ed.getTx_Observacao ())) {
          sql += " and compromissos.tx_observacao LIKE '%" + ed.getTx_Observacao () + "%'";
      }
      if (JavaUtil.doValida (ed.getNr_Documento ())) {
        sql += " and compromissos.NR_Documento = " + JavaUtil.getSQLString (ed.getNr_Documento ());
      }
      if (JavaUtil.doValida (ed.getDt_Entrada_Inicial ())) {
        sql += " and compromissos.DT_Entrada >= '" + ed.getDt_Entrada_Inicial () + "'";
      }
      if (JavaUtil.doValida (ed.getDt_Entrada_Final ())) {
        sql += " and compromissos.DT_Entrada <= '" + ed.getDt_Entrada_Final () + "'";
      }
      if (JavaUtil.doValida (ed.getData_Vencimento_Inicial ())) {
        sql += " and compromissos.dt_vencimento >= '" + ed.getData_Vencimento_Inicial () + "'";
      }
      if (JavaUtil.doValida (ed.getData_Vencimento_Final ())) {
        sql += " and compromissos.dt_vencimento <= '" + ed.getData_Vencimento_Final () + "'";
      }
      if (JavaUtil.doValida (ed.getData_Emissao_Inicial ())) {
        sql += " and compromissos.dt_Emissao >= '" + ed.getData_Emissao_Inicial () + "'";
      }
      if (JavaUtil.doValida (ed.getData_Emissao_Final ())) {
        sql += " and compromissos.dt_Emissao <= '" + ed.getData_Emissao_Final () + "'";
      }

      if (ed.getVL_Previsto_Inicial()>0) {
        sql += " and compromissos.VL_Compromisso >= " + ed.getVL_Previsto_Inicial();
      }
      if (ed.getVL_Previsto_Final()>0) {
        sql += " and compromissos.VL_Compromisso <= " + ed.getVL_Previsto_Final();
      }

      if ("A".equals (ed.getDM_Situacao ())) {
        sql += " and compromissos.vl_saldo > 0 ";
      }
      if ("P".equals (ed.getDM_Situacao ())) {
        sql += " and compromissos.vl_saldo <=0 ";
      }
      if ("N".equals (ed.getDM_Lote_Pagamento ())) {
        sql += " and compromissos.DM_Lote_Pagamento <> 'S' ";
      }

      if (String.valueOf (ed.getOid_Pessoa ()) != null && !String.valueOf (ed.getOid_Pessoa ()).equals ("") && !String.valueOf (ed.getOid_Pessoa ()).equals ("null")) {
        sql += " and compromissos.Oid_Pessoa = '" + ed.getOid_Pessoa () + "'";
      }
      else {
        if (String.valueOf (ed.getNm_Razao_Social ()) != null && !String.valueOf (ed.getNm_Razao_Social ()).equals ("") && !String.valueOf (ed.getNm_Razao_Social ()).equals ("null")) {
          sql += " and pessoas.Nm_Razao_Social LIKE '%" + ed.getNm_Razao_Social () + "%'";
        }
      }
      if ("L".equals(ed.getDM_Liberado())){
          sql += " and compromissos.dt_liberado is not null ";
      }
      if ("N".equals(ed.getDM_Liberado())){
          sql += " and compromissos.dt_liberado is null ";
      }

      if ("A".equals(ed.getDM_Aprovacao())){
          sql += " and compromissos.dt_aprovacao is not null ";
      }
      if ("N".equals(ed.getDM_Aprovacao())){
          sql += " and compromissos.dt_aprovacao is null ";
      }

      if ("VALOR".equals(ed.getDm_Ordenar())){
        sql += " ORDER BY compromissos.vl_compromisso ";
      }else{
        sql += " ORDER BY compromissos.DT_Vencimento, pessoas.nm_razao_social";
      }

      // System.out.println("Lista Compromisso =" + sql);

      ResultSet res = this.executasql.executarConsulta (sql);
      String OID_Compromisso_Atual = "";
      String OID_Compromisso_Anterior = "";
      while (res.next ()) {
        // System.out.println("Lista Compromisso =" + res.getString ("oid_Compromisso"));

        OID_Compromisso_Atual = res.getString ("oid_Compromisso");
        if (!OID_Compromisso_Atual.equals (OID_Compromisso_Anterior)) {
          CompromissoED edVolta = new CompromissoED ();

          edVolta.setDM_Lote_Pagamento (res.getString ("DM_Lote_Pagamento"));
          edVolta.setOid_Compromisso (new Integer (res.getInt ("oid_Compromisso")));
          edVolta.setNm_Razao_Social (res.getString ("nm_razao_Social"));
          edVolta.setNm_Fantasia (res.getString ("Nm_Fantasia"));
          edVolta.setNr_Compromisso (new Integer (res.getInt ("nr_Compromisso")));
          edVolta.setNr_Parcela (new Integer (res.getInt ("nr_parcela")));
          edVolta.setNr_CNPJ_CPF (res.getString ("nr_cnpj_cpf"));
          edVolta.setOid_Pessoa (res.getString ("oid_pessoa"));
          edVolta.setNr_Documento (res.getString ("nr_Documento"));
          edVolta.setNr_Proximo_Numero (res.getString ("nr_proximo_numero"));
          edVolta.setNm_Tipo_Documento (res.getString ("nm_tipo_documento"));
          edVolta.setDm_Numeracao_Automatica (res.getString ("dm_numeracao_automatica"));
          edVolta.setTx_Observacao(" ");
          if (res.getString ("Tx_Observacao") !=null && !res.getString ("Tx_Observacao").equals("null")) {
              edVolta.setTx_Observacao(res.getString ("Tx_Observacao"));
          }

          FormataDataBean DataFormatada = new FormataDataBean ();
          DataFormatada.setDT_FormataData (res.getString ("dt_vencimento"));
          edVolta.setDt_Vencimento (DataFormatada.getDT_FormataData ());


          edVolta.setOid_Conta (new Integer (res.getInt ("oid_conta")));
          sql = "select contas.oid_conta, contas.cd_conta, contas.nm_conta, " +
          		"grupos_contas.oid_grupo_conta, grupos_contas.nm_grupo_conta " +
          		" from contas, grupos_contas " +
          		" where contas.oid_grupo_conta = grupos_contas.oid_grupo_conta " +
          		" and contas.oid_conta = " + res.getInt ("oid_conta");
          ResultSet rs = this.executasql.executarConsulta(sql);
          while(rs.next()){
        	  edVolta.setOid_Conta(new Integer(rs.getInt("oid_conta")));
        	  edVolta.setCd_Conta(rs.getString(2));
              edVolta.setNm_Conta(rs.getString(3));
        	  edVolta.setCd_Centro_Custo(rs.getString(4));
              edVolta.setNm_Centro_Custo(rs.getString(5));
          }

          edVolta.setVl_Saldo (new Double (res.getDouble ("vl_saldo")));
          edVolta.setVl_Compromisso (new Double (res.getDouble ("vl_compromisso")));
          edVolta.setDM_Tipo_Pagamento (res.getString ("DM_Tipo_Pagamento"));

          DataFormatada.setDT_FormataData (res.getString ("dt_emissao"));
          edVolta.setDt_Emissao (DataFormatada.getDT_FormataData ());

          DataFormatada.setDT_FormataData (res.getString ("dt_entrada"));
          edVolta.setDt_Entrada (DataFormatada.getDT_FormataData ());

          DataFormatada.setDT_FormataData (res.getString ("dt_liberado"));
          edVolta.setDt_Liberado (DataFormatada.getDT_FormataData ());

          DataFormatada.setDT_FormataData (res.getString ("dt_Aprovacao"));
          edVolta.setDT_Aprovacao (DataFormatada.getDT_FormataData ());

          list.add (edVolta);
        }
        OID_Compromisso_Anterior = res.getString ("oid_Compromisso");

      }
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "lista()");
    }
    return list;
  }

  public ArrayList lista_Compromisso_Ordem_Servico (CompromissoED edComp) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    CompromissoPesquisaED ed = (CompromissoPesquisaED) edComp;

    try {
      sql = " SELECT * FROM compromissos, pessoas, Ordens_Servicos, Compromissos_Servicos, tipos_documentos, Movimentos_Ordens_Servicos " +
          " WHERE  compromissos.oid_pessoa = pessoas.oid_pessoa  " +
          " AND    compromissos.oid_compromisso = Compromissos_Servicos.oid_compromisso " +
          " AND    Movimentos_Ordens_Servicos.oid_Movimento_Ordem_Servico = Compromissos_Servicos.oid_Movimento_Ordem_Servico " +
          " AND    Movimentos_Ordens_Servicos.oid_Ordem_Servico = Ordens_Servicos.oid_Ordem_Servico " +
          " AND    compromissos.oid_tipo_documento = tipos_documentos.oid_tipo_documento ";

      if (String.valueOf (ed.getDt_Inicial ()) != null && !String.valueOf (ed.getDt_Inicial ()).equals ("") && !String.valueOf (ed.getDt_Inicial ()).equals ("null")) {
        sql += " AND Compromissos_Servicos.dt_compromisso_Servico = '" + ed.getDt_Inicial () + "'";
      }

      sql += " order by Ordens_Servicos.NR_Ordem_Servico, Compromissos.NR_Compromisso ";

      ResultSet res = this.executasql.executarConsulta (sql);
      //popula
      while (res.next ()) {
        CompromissoED edVolta = new CompromissoED ();

        edVolta.setOid_Compromisso (new Integer (res.getInt ("oid_Compromisso")));
        edVolta.setNm_Razao_Social (res.getString ("nm_razao_Social"));
        edVolta.setNm_Fantasia (res.getString ("Nm_Fantasia"));
        edVolta.setNr_Compromisso (new Integer (res.getInt ("nr_Compromisso")));
        edVolta.setNr_Parcela (new Integer (res.getInt ("nr_parcela")));
        edVolta.setNr_CNPJ_CPF (res.getString ("nr_cnpj_cpf"));
        edVolta.setOid_Pessoa (res.getString ("oid_pessoa"));
        edVolta.setNr_Documento (res.getString ("nr_Documento"));

        FormataDataBean DataFormatada = new FormataDataBean ();
        DataFormatada.setDT_FormataData (res.getString ("dt_vencimento"));
        edVolta.setDt_Vencimento (DataFormatada.getDT_FormataData ());

        edVolta.setVl_Saldo (new Double (res.getDouble ("vl_saldo")));
        edVolta.setVl_Compromisso (new Double (res.getDouble ("vl_compromisso")));
        edVolta.setDM_Tipo_Pagamento (res.getString ("DM_Tipo_Pagamento"));

        DataFormatada.setDT_FormataData (res.getString ("dt_emissao"));
        edVolta.setDt_Emissao (DataFormatada.getDT_FormataData ());

        DataFormatada.setDT_FormataData (res.getString ("dt_entrada"));
        edVolta.setDt_Entrada (DataFormatada.getDT_FormataData ());

        DataFormatada.setDT_FormataData (res.getString ("dt_liberado"));
        edVolta.setDt_Liberado (DataFormatada.getDT_FormataData ());

        list.add (edVolta);
      }
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "lista_Compromisso_Ordem_Servico()");
    }
    return list;
  }

  public ArrayList lista_Vinculado (CompromissoED edComp) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    CompromissoPesquisaED ed = (CompromissoPesquisaED) edComp;

    try {
      sql = "select * from Compromissos, pessoas, tipos_documentos where " + " compromissos.oid_pessoa = pessoas.oid_pessoa and"
          + " compromissos.oid_tipo_documento = tipos_Documentos.oid_tipo_documento and " + " compromissos.VL_Saldo = compromissos.VL_Compromisso and"
          + " compromissos.OID_Compromisso_Vincula is null ";

      if (ed.getOid_Compromisso () != null) {
        sql += " and compromissos.oid_compromisso = " + ed.getOid_Compromisso ();
      }

      if (ed.getOid_Compromisso_Vinculado () != null) {
        sql += " and compromissos.oid_compromisso_vincula = " + ed.getOid_Compromisso_Vinculado ();
      }

      if (ed.getNr_Compromisso () != null) {
        sql += " and compromissos.nr_compromisso = " + ed.getNr_Compromisso ();
      }

      if (ed.getOid_Pessoa () != null) {
        sql += " and compromissos.oid_pessoa = '" + ed.getOid_Pessoa () + "'";
      }

      if (ed.getData_Vencimento_Inicial () != null) {
        sql += " and compromissos.dt_vencimento >= '" + ed.getData_Vencimento_Inicial () + "'";
      }

      if (ed.getData_Vencimento_Final () != null) {
        sql += " and compromissos.dt_vencimento <= '" + ed.getData_Vencimento_Final () + "'";
      }

      if (ed.getNm_Razao_Social () != null) {
        sql += " and pessoas.nm_razao_social like '" + ed.getNm_Razao_Social () + "%'";
      }

      sql += " ORDER BY pessoas.nm_razao_social, compromissos.nr_compromisso, compromissos.DT_Vencimento ";
      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);

      //popula
      while (res.next ()) {
        CompromissoED edVolta = new CompromissoED ();
        //popular os dados de compromisso para voltar

        edVolta.setOid_Compromisso (new Integer (res.getInt ("oid_Compromisso")));
        edVolta.setNm_Razao_Social (res.getString ("nm_razao_Social"));
        edVolta.setNr_Compromisso (new Integer (res.getInt ("nr_Compromisso")));
        edVolta.setNr_Parcela (new Integer (res.getInt ("nr_parcela")));
        edVolta.setNr_CNPJ_CPF (res.getString ("nr_cnpj_cpf"));
        edVolta.setOid_Pessoa (res.getString ("oid_pessoa"));
        edVolta.setNr_Documento (res.getString ("nr_Documento"));
        edVolta.setNr_Proximo_Numero (res.getString ("nr_proximo_numero"));
        edVolta.setNm_Tipo_Documento (res.getString ("nm_tipo_documento"));
        edVolta.setDm_Numeracao_Automatica (res.getString ("dm_numeracao_automatica"));

        FormataDataBean DataFormatada = new FormataDataBean ();
        DataFormatada.setDT_FormataData (res.getString ("dt_vencimento"));
        edVolta.setDt_Vencimento (DataFormatada.getDT_FormataData ());

        edVolta.setVl_Saldo (new Double (res.getDouble ("vl_saldo")));
        edVolta.setVl_Compromisso (new Double (res.getDouble ("Vl_Compromisso")));

        DataFormatada.setDT_FormataData (res.getString ("dt_emissao"));
        edVolta.setDt_Emissao (DataFormatada.getDT_FormataData ());

        DataFormatada.setDT_FormataData (res.getString ("dt_entrada"));
        edVolta.setDt_Entrada (DataFormatada.getDT_FormataData ());

        list.add (edVolta);
      }
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "lista_Vinculado()");
    }
    return list;
  }

  public ArrayList lista_Parcela (CompromissoED edComp) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    CompromissoPesquisaED ed = (CompromissoPesquisaED) edComp;

    try {
      sql = "select * from Compromissos where compromissos.oid_compromisso_parcela = " + ed.getOid_Compromisso ();
      sql += " ORDER BY compromissos.DT_Vencimento ";
      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);

      //popula
      while (res.next ()) {
        CompromissoED edVolta = new CompromissoED ();
        //popular os dados de compromisso para voltar

        edVolta.setOid_Compromisso (new Integer (res.getInt ("oid_Compromisso")));
        edVolta.setNr_Compromisso (new Integer (res.getInt ("nr_Compromisso")));
        edVolta.setNr_Parcela (new Integer (res.getInt ("nr_parcela")));
        edVolta.setNr_Documento (res.getString ("nr_Documento"));

        FormataDataBean DataFormatada = new FormataDataBean ();
        DataFormatada.setDT_FormataData (res.getString ("dt_vencimento"));
        edVolta.setDt_Vencimento (DataFormatada.getDT_FormataData ());

        edVolta.setVl_Saldo (new Double (res.getDouble ("vl_saldo")));
        edVolta.setVl_Compromisso (new Double (res.getDouble ("Vl_Compromisso")));

        DataFormatada.setDT_FormataData (res.getString ("dt_emissao"));
        edVolta.setDt_Emissao (DataFormatada.getDT_FormataData ());

        DataFormatada.setDT_FormataData (res.getString ("dt_entrada"));
        edVolta.setDt_Entrada (DataFormatada.getDT_FormataData ());

        list.add (edVolta);
      }
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "lista_Parcela()");
    }
    return list;
  }

  public ArrayList lista_Servico (CompromissoED edComp) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    CompromissoPesquisaED ed = (CompromissoPesquisaED) edComp;

    try {
      sql = "select * from Compromissos, pessoas, tipos_documentos, Compromissos_Servicos where " + " compromissos.oid_pessoa = pessoas.oid_pessoa and"
          + " compromissos.oid_tipo_documento = tipos_Documentos.oid_tipo_documento and" + " compromissos.oid_Compromisso = Compromissos_Servicos.oid_Compromisso ";

      if (ed.getOID_Movimento_Ordem_Servico () != null) {
        sql += " and Compromissos_Servicos.OID_MOVIMENTO_ORDEM_SERVICO = " + ed.getOID_Movimento_Ordem_Servico ();
      }

      sql += " ORDER BY pessoas.nm_razao_social, compromissos.nr_compromisso, compromissos.DT_Vencimento ";

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);

      //popula
      while (res.next ()) {
        CompromissoED edVolta = new CompromissoED ();
        //popular os dados de compromisso para voltar

        edVolta.setOid_Compromisso (new Integer (res.getInt ("oid_Compromisso")));
        edVolta.setNm_Razao_Social (res.getString ("nm_razao_Social"));
        edVolta.setNr_Compromisso (new Integer (res.getInt ("nr_Compromisso")));
        edVolta.setNr_Parcela (new Integer (res.getInt ("nr_parcela")));
        edVolta.setNr_CNPJ_CPF (res.getString ("nr_cnpj_cpf"));
        edVolta.setOid_Pessoa (res.getString ("oid_pessoa"));
        edVolta.setNr_Documento (res.getString ("nr_Documento"));
        edVolta.setNr_Proximo_Numero (res.getString ("nr_proximo_numero"));
        edVolta.setNm_Tipo_Documento (res.getString ("nm_tipo_documento"));
        edVolta.setDm_Numeracao_Automatica (res.getString ("dm_numeracao_automatica"));

        FormataDataBean DataFormatada = new FormataDataBean ();
        DataFormatada.setDT_FormataData (res.getString ("dt_vencimento"));
        edVolta.setDt_Vencimento (DataFormatada.getDT_FormataData ());

        edVolta.setVl_Saldo (new Double (res.getDouble ("vl_saldo")));

        DataFormatada.setDT_FormataData (res.getString ("dt_emissao"));
        edVolta.setDt_Emissao (DataFormatada.getDT_FormataData ());

        DataFormatada.setDT_FormataData (res.getString ("dt_entrada"));
        edVolta.setDt_Entrada (DataFormatada.getDT_FormataData ());

        list.add (edVolta);
      }
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "lista_Servico()");
    }
    return list;
  }

  public ArrayList lista_Ordem (CompromissoED edComp) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    CompromissoPesquisaED ed = (CompromissoPesquisaED) edComp;

    try {
      sql = "select * from Compromissos, pessoas, tipos_documentos, Movimentos_Ordens where " + " compromissos.oid_pessoa = pessoas.oid_pessoa and"
          + " compromissos.oid_tipo_documento = tipos_Documentos.oid_tipo_documento and" + " compromissos.oid_Compromisso = Movimentos_Ordens.oid_Compromisso ";
      if (ed.getOID_Ordem_Frete () != null) {
        sql += " and Movimentos_Ordens.OID_Ordem_Frete = '" + ed.getOID_Ordem_Frete () + "'";
      }

      sql += " ORDER BY pessoas.nm_razao_social, compromissos.nr_compromisso, compromissos.DT_Vencimento ";

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      //popula
      while (res.next ()) {
        CompromissoED edVolta = new CompromissoED ();
        //popular os dados de compromisso para voltar

        edVolta.setOid_Compromisso (new Integer (res.getInt ("oid_Compromisso")));
        edVolta.setNm_Razao_Social (res.getString ("nm_razao_Social"));
        edVolta.setNr_Compromisso (new Integer (res.getInt ("nr_Compromisso")));
        edVolta.setNr_Parcela (new Integer (res.getInt ("nr_parcela")));
        edVolta.setNr_CNPJ_CPF (res.getString ("nr_cnpj_cpf"));

        edVolta.setOid_Pessoa (res.getString ("oid_pessoa"));
        edVolta.setNr_Documento (res.getString ("nr_Documento"));
        edVolta.setNr_Proximo_Numero (res.getString ("nr_proximo_numero"));
        edVolta.setNm_Tipo_Documento (res.getString ("nm_tipo_documento"));
        edVolta.setDm_Numeracao_Automatica (res.getString ("dm_numeracao_automatica"));

        FormataDataBean DataFormatada = new FormataDataBean ();
        DataFormatada.setDT_FormataData (res.getString ("dt_vencimento"));
        edVolta.setDt_Vencimento (DataFormatada.getDT_FormataData ());

        edVolta.setVl_Compromisso (new Double (res.getDouble ("vl_compromisso")));
        edVolta.setVl_Saldo (new Double (res.getDouble ("vl_saldo")));

        DataFormatada.setDT_FormataData (res.getString ("dt_emissao"));
        edVolta.setDt_Emissao (DataFormatada.getDT_FormataData ());

        list.add (edVolta);
      }
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "lista_Ordem()");
    }
    return list;
  }

  public void subtraiSaldo (CompromissoED ed) throws Excecoes {

    String sql = null;

    try {

      sql = "update Compromissos set vl_saldo = " + ed.getVl_Saldo () + " WHERE oid_Compromisso = ";
      sql += ed.getOid_Compromisso ();

      executasql.executarUpdate (sql);
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "subtraiSaldo()");
    }
  }

  public void retemImposto (CompromissoED ed) throws Excecoes {

    String sql = null;

    try {

      sql = " select vl_imposto from Compromissos " + " WHERE oid_Compromisso = " + ed.getOid_Compromisso ();

      double vl_imposto = 0;
      String vl_auxiliar = null;

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      //popula
      while (res.next ()) {
        vl_imposto = res.getDouble ("vl_imposto");
      }
      vl_auxiliar = String.valueOf (ed.getVl_Imposto ());
      vl_imposto = vl_imposto + new Double (vl_auxiliar).doubleValue ();

      sql = "update Compromissos set ";
      sql += " vl_saldo = " + ed.getVl_Saldo () + ",";
      sql += " vl_imposto = " + vl_imposto;
      sql += " WHERE oid_Compromisso = ";
      sql += ed.getOid_Compromisso ();

      executasql.executarUpdate (sql);

    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "retemImposto()");
    }
  }

  public void geraCompromisso_Fornecedor (CompromissoED ed, HttpServletResponse response) throws Excecoes {

	    String sql =
	        " SELECT *, Contas.NM_Conta as Nome_Conta " +
	        " FROM Compromissos " +
	        "      left join Movimentos_Compromissos " +
	        "        on Movimentos_Compromissos.oid_Compromisso = Compromissos.oid_Compromisso " +
	        "      left join Fornecedores " +
	        "        on Fornecedores.oid_Fornecedor = Compromissos.oid_Pessoa " +
	        "      ,Pessoas " +
	        "      ,Contas " +
	        "      ,Centros_Custos " +
	        "      ,Tipos_Documentos ";

	    if(JavaUtil.doValida(String.valueOf(ed.getOid_Empresa())))
	    	sql += " , Unidades ";

	    sql+=" WHERE Compromissos.oid_Compromisso_Vincula is null " +
	        "   AND Compromissos.oid_Pessoa = Pessoas.oid_Pessoa  " +
	        "   AND Compromissos.oid_Tipo_Documento = Tipos_Documentos.oid_Tipo_Documento " +
	        "   AND Compromissos.oid_Conta          = Contas.oid_Conta " +
	        "   AND Compromissos.oid_Centro_Custo   = Centros_Custos.oid_Centro_Custo ";
	    if (JavaUtil.doValida (ed.getDt_Pagamento_Inicial ())) {
	      sql += " AND Movimentos_Compromissos.dt_pagamento >= '" + ed.getDt_Pagamento_Inicial () + "'";
	    }
	    if (JavaUtil.doValida (ed.getDt_Pagamento_Final ())) {
	      sql += " AND  Movimentos_Compromissos.dt_pagamento <= '" + ed.getDt_Pagamento_Final () + "'";
	    }
	    if (JavaUtil.doValida (ed.getOid_Pessoa ())) {
	      sql += " AND compromissos.Oid_Pessoa = '" + ed.getOid_Pessoa () + "'";
	    }
	    if (JavaUtil.doValida (ed.getDt_Entrada_Inicial ())) {
	      sql += " AND Compromissos.dt_Entrada >= '" + ed.getDt_Entrada_Inicial () + "'";
	    }
	    if (JavaUtil.doValida (ed.getDt_Entrada_Final ())) {
	      sql += " AND Compromissos.dt_Entrada <= '" + ed.getDt_Entrada_Final () + "'";
	    }
	    if (JavaUtil.doValida (ed.getDt_Vencimento_Inicial ())) {
	      sql += " AND Compromissos.dt_vencimento >= '" + ed.getDt_Vencimento_Inicial () + "'";
	    }
	    if (JavaUtil.doValida (ed.getDt_Vencimento_Final ())) {
	      sql += " AND Compromissos.dt_vencimento <= '" + ed.getDt_Vencimento_Final () + "'";
	    }
	    if (JavaUtil.doValida (ed.getDt_Inicial ())) {
	      sql += " AND Compromissos.DT_Emissao >= '" + ed.getDt_Inicial () + "'";
	    }
	    if (JavaUtil.doValida (ed.getDt_Final ())) {
	      sql += " AND Compromissos.DT_Emissao <= '" + ed.getDt_Final () + "'";
	    }
	    if ("A".equals(ed.getDM_Situacao ())) {
	      sql += " AND compromissos.vl_saldo > 0 AND Compromissos.dt_liberado is not null ";
	    }
	    if ("P".equals(ed.getDM_Situacao ())) {
	      sql += " AND Movimentos_Compromissos.vl_pagamento > 0 ";
	    }
	    if ("TA".equals(ed.getDM_Situacao ())) {
	      sql += " AND compromissos.vl_saldo > 0 ";
	    }
	    if ("AC".equals(ed.getDM_Situacao ())) {
	      sql += " AND compromissos.vl_saldo > 0 AND Compromissos.DM_Cartorio ='S' ";
	    }
	    if ("NL".equals(ed.getDM_Situacao ())) {
	      sql += " AND compromissos.vl_saldo > 0 AND Compromissos.dt_liberado is null ";
	    }
	    if ("LA".equals(ed.getDM_Situacao ())) {
	      sql += " AND compromissos.vl_saldo > 0 AND Compromissos.dt_liberado is not null AND Compromissos.dt_aprovacao is not null ";
	    }

	    if (JavaUtil.doValida(String.valueOf(ed.getOid_Empresa()))) {
	      sql += " AND compromissos.Oid_Unidade = unidades.oid_unidade ";
	      sql += " AND unidades.Oid_empresa = '" + ed.getOid_Empresa() + "'";
	    }

	    if (String.valueOf (ed.getOid_Unidade ()) != null && !String.valueOf (ed.getOid_Unidade ()).equals ("") && !String.valueOf (ed.getOid_Unidade ()).equals ("null")) {
	        sql += " AND compromissos.Oid_Unidade = '" + ed.getOid_Unidade () + "'";
	      }

	    if (ed.getOid_Unidade_Pagamento () > 0) {
	      sql += " AND compromissos.oid_unidade_pagamento = " + ed.getOid_Unidade_Pagamento ();
	    }

	    if (String.valueOf (ed.getOid_Conta ()) != null && !String.valueOf (ed.getOid_Conta ()).equals ("") && !String.valueOf (ed.getOid_Conta ()).equals ("null")) {
	      sql += " AND compromissos.Oid_Conta = '" + ed.getOid_Conta () + "'";
	    }

	    if (String.valueOf (ed.getOid_Centro_Custo ()) != null && !String.valueOf (ed.getOid_Centro_Custo ()).equals ("") && !String.valueOf (ed.getOid_Centro_Custo ()).equals ("null")) {
	      sql += " AND compromissos.Oid_Centro_Custo = '" + ed.getOid_Centro_Custo () + "'";
	    }

	    if (String.valueOf (ed.getOid_Grupo_Economico ()) != null && !String.valueOf (ed.getOid_Grupo_Economico ()).equals ("") && !String.valueOf (ed.getOid_Grupo_Economico ()).equals ("null")) {
	      sql += " AND Fornecedores.Oid_Grupo_Economico = '" + ed.getOid_Grupo_Economico () + "'";
	    }

	    if (ed.getNr_Parcela().intValue()>0) {
	      sql += " AND compromissos.NR_Parcela = " + ed.getNr_Parcela ().intValue();
	    }

	    if (ed.getDM_Relatorio ().substring (0 , 4).equals ("VCTO")) {
	      if ("V".equals (ed.getDM_Classificar ())) {
	        sql += " order by compromissos.dt_vencimento_original, Pessoas.NM_Razao_Social, compromissos.nr_documento, compromissos.nr_parcela";
	      }
	      else {
	        sql += " order by compromissos.dt_vencimento, Pessoas.NM_Razao_Social, compromissos.nr_documento, compromissos.nr_parcela";
	      }
	    }else if (ed.getDM_Relatorio ().equals ("PAGTO1")) {
	      if ("V".equals (ed.getDM_Classificar ())) {
	        sql += " order by Movimentos_Compromissos.dt_pagamento, compromissos.dt_vencimento_original, Pessoas.NM_Razao_Social, compromissos.nr_documento, compromissos.nr_parcela";
	      }
	      else {
	        sql += " order by Movimentos_Compromissos.dt_pagamento, compromissos.dt_vencimento, Pessoas.NM_Razao_Social, compromissos.nr_documento, compromissos.nr_parcela";
	      }
	    }else if (ed.getDM_Relatorio ().substring (0 , 5).equals ("CONTA")) {
	      if ("V".equals (ed.getDM_Classificar ())) {
	        sql += " order by contas.nm_conta, Contas.cd_conta, compromissos.dt_vencimento_original, Pessoas.NM_Razao_Social, compromissos.nr_documento, compromissos.nr_parcela";
	      }
	      else {
	        sql += " order by contas.nm_conta, Contas.cd_conta, compromissos.dt_vencimento, Pessoas.NM_Razao_Social, compromissos.nr_documento, compromissos.nr_parcela";
	      }
	    }else if (ed.getDM_Relatorio ().substring (0 , 5).equals ("CE_CU")) {
	      if ("V".equals (ed.getDM_Classificar ())) {
	        sql += " order by centros_custos.nm_centro_Custo, compromissos.dt_vencimento_original, Pessoas.NM_Razao_Social, compromissos.nr_documento, compromissos.nr_parcela";
	      }
	      else {
	        sql += " order by centros_custos.nm_centro_Custo, compromissos.dt_vencimento, Pessoas.NM_Razao_Social, compromissos.nr_documento, compromissos.nr_parcela";
	      }
	    }else {
	      if ("V".equals (ed.getDM_Classificar ())) {
	        sql += " order by Pessoas.NM_Razao_Social, compromissos.oid_pessoa, compromissos.dt_vencimento_original, compromissos.nr_documento, compromissos.nr_parcela";
	      }
	      else {
	        sql += " order by Pessoas.NM_Razao_Social, compromissos.oid_pessoa, compromissos.dt_vencimento, compromissos.nr_documento, compromissos.nr_parcela";
	      }
	      //sql += " order by compromissos.oid_pessoa, compromissos.dt_vencimento, compromissos.nr_documento, compromissos.nr_parcela";
	    }

	    ResultSet res = this.executasql.executarConsulta (sql.toString ());
	    try {
	      new CompromissoRL ().geraCompromisso_Fornecedor (res, ed, this.executasql, response);
	    }
	    finally {
	      util.closeResultset (res);
	    }
	  }

  public byte[] geraCompromisso_Unidade (CompromissoED ed) throws Excecoes {

    String sql = null;
    byte[] b = null;

    sql = " SELECT * FROM compromissos,  tipos_documentos " +
        " WHERE compromissos.oid_compromisso_vincula is null " +
        " AND   compromissos.dt_liberado is not null " +
        " AND   compromissos.oid_tipo_documento = tipos_documentos.oid_tipo_documento ";

    if (String.valueOf (ed.getOid_Unidade ()) != null && !String.valueOf (ed.getOid_Unidade ()).equals ("") && !String.valueOf (ed.getOid_Unidade ()).equals ("null")) {
      sql += " AND compromissos.Oid_Unidade = '" + ed.getOid_Unidade () + "'";
    }

    if (ed.getOid_Unidade_Pagamento () > 0) {
      sql += " AND compromissos.oid_unidade_pagamento = " + ed.getOid_Unidade_Pagamento ();
    }

    if (String.valueOf (ed.getOid_Conta ()) != null && !String.valueOf (ed.getOid_Conta ()).equals ("") && !String.valueOf (ed.getOid_Conta ()).equals ("null")) {
      sql += " AND compromissos.Oid_Conta = '" + ed.getOid_Conta () + "'";
    }

    if (String.valueOf (ed.getDt_Vencimento_Inicial ()) != null && !String.valueOf (ed.getDt_Vencimento_Inicial ()).equals ("") && !String.valueOf (ed.getDt_Vencimento_Inicial ()).equals ("null")) {
      sql += " AND Compromissos.dt_vencimento >= '" + ed.getDt_Vencimento_Inicial () + "'";
    }
    if (String.valueOf (ed.getDt_Vencimento_Final ()) != null && !String.valueOf (ed.getDt_Vencimento_Final ()).equals ("") && !String.valueOf (ed.getDt_Vencimento_Final ()).equals ("null")) {
      sql += " AND Compromissos.dt_vencimento <= '" + ed.getDt_Vencimento_Final () + "'";
    }

    if (String.valueOf (ed.getDt_Inicial ()) != null && !String.valueOf (ed.getDt_Inicial ()).equals ("") && !String.valueOf (ed.getDt_Inicial ()).equals ("null")) {
      sql += " AND Compromissos.dt_Emissao >= '" + ed.getDt_Inicial () + "'";
    }
    if (String.valueOf (ed.getDt_Final ()) != null && !String.valueOf (ed.getDt_Final ()).equals ("") && !String.valueOf (ed.getDt_Final ()).equals ("null")) {
      sql += " AND Compromissos.dt_Emissao <= '" + ed.getDt_Final () + "'";
    }

    if (String.valueOf (ed.getDM_Situacao ()) != null && String.valueOf (ed.getDM_Situacao ()).equals ("A") && !String.valueOf (ed.getDM_Situacao ()).equals ("null")) {
      sql += " AND compromissos.vl_saldo > 0 ";
    }
    if (String.valueOf (ed.getDM_Situacao ()) != null && String.valueOf (ed.getDM_Situacao ()).equals ("P") && !String.valueOf (ed.getDM_Situacao ()).equals ("null")) {
      sql += " AND compromissos.vl_saldo = 0 ";
    }
    if (ed.getDM_Relatorio ().equals ("RDEP")) {
      sql += " order by compromissos.oid_Unidade, compromissos.dt_vencimento, compromissos.nr_documento, compromissos.nr_parcela";
    }
    if (ed.getDM_Relatorio ().equals ("RPG")) {
      sql += " order by compromissos.oid_Unidade_Pagamento, compromissos.dt_vencimento, compromissos.nr_documento, compromissos.nr_parcela";
    }
    try {

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql.toString ());

      CompromissoRL conRL = new CompromissoRL ();
      b = conRL.geraCompromisso_Unidade (res , ed);

    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "geraCompromisso_Unidade()");
    }
    return b;
  }

  public byte[] geraCompromisso_Vencimento (CompromissoED ed) throws Excecoes {

    String sql = null;
    sql = " SELECT * FROM compromissos, pessoas, tipos_documentos " +
        " WHERE  compromissos.oid_compromisso_vincula is null " +
        " AND    compromissos.dt_liberado is not null " +
        " AND    compromissos.oid_pessoa = pessoas.oid_pessoa " +
        " AND    compromissos.oid_tipo_documento = tipos_documentos.oid_tipo_documento ";

    if (String.valueOf (ed.getOid_Pessoa ()) != null && !String.valueOf (ed.getOid_Pessoa ()).equals ("") && !String.valueOf (ed.getOid_Pessoa ()).equals ("null")) {
      sql += " AND compromissos.Oid_Pessoa = '" + ed.getOid_Pessoa () + "'";
    }
    if (String.valueOf (ed.getDt_Inicial ()) != null && !String.valueOf (ed.getDt_Inicial ()).equals ("") && !String.valueOf (ed.getDt_Inicial ()).equals ("null")) {
      sql += " AND Compromissos.dt_vencimento >= '" + ed.getDt_Inicial () + "'";
    }
    if (String.valueOf (ed.getDt_Final ()) != null && !String.valueOf (ed.getDt_Final ()).equals ("") && !String.valueOf (ed.getDt_Final ()).equals ("null")) {
      sql += " AND Compromissos.dt_vencimento <= '" + ed.getDt_Final () + "'";
    }
    if (String.valueOf (ed.getDM_Situacao ()) != null && String.valueOf (ed.getDM_Situacao ()).equals ("A") && !String.valueOf (ed.getDM_Situacao ()).equals ("null")) {
      sql += " AND compromissos.vl_saldo > 0 ";
    }
    if (String.valueOf (ed.getDM_Situacao ()) != null && String.valueOf (ed.getDM_Situacao ()).equals ("P") && !String.valueOf (ed.getDM_Situacao ()).equals ("null")) {
      sql += " AND compromissos.vl_saldo = 0 ";
    }

    sql += " order by compromissos.dt_vencimento, compromissos.nr_documento, compromissos.nr_parcela";

    try {
      return new CompromissoRL ().geraCompromisso_Vencimento (this.executasql.executarConsulta (sql) , ed);
    }
    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "geraCompromisso_Vencimento()");
    }
  }

  public byte[] imprime_Agenda_Pagamento (CompromissoED ed) throws Excecoes {

    String sql = null;
    sql = " SELECT * FROM compromissos, pessoas, tipos_documentos " +
        " WHERE  compromissos.oid_pessoa = pessoas.oid_pessoa " +
        " AND    compromissos.oid_tipo_documento = tipos_documentos.oid_tipo_documento " +
        " AND    compromissos.Oid_Compromisso = '" + ed.getOid_Compromisso()+ "'";

    try {
      return new CompromissoRL ().imprime_Agenda_Pagamento (this.executasql.executarConsulta (sql) , ed);
    }
    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "geraCompromisso_Vencimento()");
    }
  }


  public byte[] geraCompromisso_Emissao (CompromissoED ed) throws Excecoes {

    String sql = null;
    byte[] b = null;

    sql = " SELECT * FROM compromissos, pessoas, tipos_documentos " +
        " WHERE  compromissos.oid_compromisso_vincula is null  " +
        " AND    compromissos.dt_liberado is not null " +
        " AND    compromissos.oid_pessoa = pessoas.oid_pessoa  " +
        " AND    compromissos.oid_tipo_documento = tipos_documentos.oid_tipo_documento ";

    if (String.valueOf (ed.getOid_Pessoa ()) != null && !String.valueOf (ed.getOid_Pessoa ()).equals ("") && !String.valueOf (ed.getOid_Pessoa ()).equals ("null")) {
      sql += " AND compromissos.Oid_Pessoa = '" + ed.getOid_Pessoa () + "'";
    }
    if (String.valueOf (ed.getDt_Inicial ()) != null && !String.valueOf (ed.getDt_Inicial ()).equals ("") && !String.valueOf (ed.getDt_Inicial ()).equals ("null")) {
      sql += " AND Compromissos.dt_entrada >= '" + ed.getDt_Inicial () + "'";
    }
    if (String.valueOf (ed.getDt_Final ()) != null && !String.valueOf (ed.getDt_Final ()).equals ("") && !String.valueOf (ed.getDt_Final ()).equals ("null")) {
      sql += " AND Compromissos.dt_entrada <= '" + ed.getDt_Final () + "'";
    }
    if (String.valueOf (ed.getDM_Situacao ()) != null && String.valueOf (ed.getDM_Situacao ()).equals ("A") && !String.valueOf (ed.getDM_Situacao ()).equals ("null")) {
      sql += " AND compromissos.vl_saldo > 0 ";
    }
    if (String.valueOf (ed.getDM_Situacao ()) != null && String.valueOf (ed.getDM_Situacao ()).equals ("P") && !String.valueOf (ed.getDM_Situacao ()).equals ("null")) {
      sql += " AND compromissos.vl_saldo = 0 ";
    }

    sql += " order by compromissos.dt_Emissao, compromissos.nr_documento, compromissos.nr_parcela";

    try {

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql.toString ());

      CompromissoRL conRL = new CompromissoRL ();
      b = conRL.geraCompromisso_Emissao (res);

    }
    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "geraCompromisso_Emissao()");
    }
    return b;
  }

  public byte[] geraCompromisso_Ordem_Servico (CompromissoED ed) throws Excecoes {

    String sql = null;
    byte[] b = null;

    sql = " SELECT * FROM compromissos, pessoas, Ordens_Servicos, Compromissos_Servicos, tipos_documentos, Movimentos_Ordens_Servicos " +
        " WHERE  compromissos.oid_pessoa = pessoas.oid_pessoa  " +
        " AND    compromissos.oid_compromisso = Compromissos_Servicos.oid_compromisso " +
        " AND    Movimentos_Ordens_Servicos.oid_Movimento_Ordem_Servico = Compromissos_Servicos.oid_Movimento_Ordem_Servico " +
        " AND    Movimentos_Ordens_Servicos.oid_Ordem_Servico = Ordens_Servicos.oid_Ordem_Servico " +
        " AND    compromissos.oid_tipo_documento = tipos_documentos.oid_tipo_documento ";

    if (String.valueOf (ed.getDt_Inicial ()) != null && !String.valueOf (ed.getDt_Inicial ()).equals ("") && !String.valueOf (ed.getDt_Inicial ()).equals ("null")) {
      sql += " AND Compromissos_Servicos.dt_compromisso_Servico = '" + ed.getDt_Inicial () + "'";
    }
    if ("Liberados".equals (ed.getDM_Relatorio ())) {
      sql += " AND compromissos.DT_LIBERADO is not NULL ";
    }

    sql += " order by Ordens_Servicos.NR_Ordem_Servico, Compromissos.NR_Compromisso ";

    try {

      ResultSet res = this.executasql.executarConsulta (sql.toString ());

      CompromissoRL conRL = new CompromissoRL ();
      b = conRL.geraCompromisso_Ordem_Servico (res , ed);

    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "geraCompromisso_Emissao()");
    }
    return b;
  }

  public byte[] geraCompromisso_Pagamento (CompromissoED ed) throws Excecoes {

    String sql = null;
    byte[] b = null;
    ResultSet res = null;
    try {
      if ("Lote_Pagamento".equals (Parametro_FixoED.getInstancia ().getDM_Liquida_Compromisso ())) {
        sql = "SELECT lotes_pagamentos.oid_lote_pagamento, "
            + "             lotes_pagamentos.nr_documento as nr_documento_lote, "
            + "             lotes_pagamentos.dt_emissao as dt_pagamento,  "
           // + "             lotes_compromissos.dt_pagamento,  "
            + "             lotes_compromissos.vl_pagamento,  "
            + "             compromissos.nr_documento as nr_documento_compromisso,  "
            + "             compromissos.nr_parcela,  "
            + "             contas_correntes.cd_conta_corrente,  "
            + "             contas_correntes.nr_conta_corrente,  "
            + "             tipos_documentos_lote.cd_tipo_documento as cd_tipo_documento_lote,  "
            + "             tipos_documentos_lote.nm_tipo_documento as nm_tipo_documento_lote,  "
            + "             tipos_documentos_compromisso.cd_tipo_documento as cd_tipo_documento_compromisso ,  "
            + "             tipos_documentos_compromisso.nm_tipo_documento as nm_tipo_documento_compromisso ,  "
            + "             pessoas_compromisso.nm_razao_social  as nm_razao_social_compromisso, "
            + "             pessoas_conta_corrente.nm_razao_social  as nm_razao_social_conta_corrente "
            + " FROM  compromissos, pessoas pessoas_compromisso, pessoas pessoas_conta_corrente, tipos_documentos tipos_documentos_lote, tipos_documentos tipos_documentos_compromisso, lotes_compromissos, lotes_pagamentos, contas_correntes "
            + " WHERE Lotes_Compromissos.oid_compromisso = compromissos.oid_compromisso  "
            + " AND   Lotes_Compromissos.oid_Lote_Pagamento = Lotes_Pagamentos.oid_Lote_Pagamento "
            + " AND   Lotes_Pagamentos.oid_tipo_documento = tipos_Documentos_lote.oid_tipo_documento "
            + " AND   Lotes_Pagamentos.oid_conta_corrente = contas_correntes.oid_conta_corrente "
            + " AND   Contas_correntes.oid_pessoa = Pessoas_conta_corrente.oid_pessoa  "
            + " AND   Compromissos.oid_pessoa = pessoas_compromisso.oid_pessoa "
            + " AND   Compromissos.oid_tipo_documento = tipos_documentos_compromisso.oid_tipo_documento  ";

        if (String.valueOf (ed.getOid_Pessoa ()) != null && !String.valueOf (ed.getOid_Pessoa ()).equals ("") && !String.valueOf (ed.getOid_Pessoa ()).equals ("null")) {
          sql += " and compromissos.Oid_Pessoa = '" + ed.getOid_Pessoa () + "'";
        }

        if (String.valueOf (ed.getDt_Inicial ()) != null && !String.valueOf (ed.getDt_Inicial ()).equals ("") && !String.valueOf (ed.getDt_Inicial ()).equals ("null")) {
          //sql += " and lotes_compromissos.dt_pagamento >= '" + ed.getDt_Inicial () + "'";
          sql += " and lotes_pagamentos.dt_emissao >= '" + ed.getDt_Inicial () + "'";
        }
        if (String.valueOf (ed.getDt_Final ()) != null && !String.valueOf (ed.getDt_Final ()).equals ("") && !String.valueOf (ed.getDt_Final ()).equals ("null")) {
          //sql += " and lotes_compromissos.dt_pagamento <= '" + ed.getDt_Final () + "'";
          sql += " and lotes_pagamentos.dt_emissao <= '" + ed.getDt_Final () + "'";
        }

        //sql += " order by lotes_compromissos.dt_pagamento, lotes_pagamentos.oid_conta_corrente, compromissos.nr_documento, compromissos.nr_parcela";
        sql += " order by lotes_pagamentos.dt_emissao, lotes_pagamentos.oid_conta_corrente, compromissos.nr_documento, compromissos.nr_parcela";
        res = this.executasql.executarConsulta (sql.toString ());

      }
      else {
        sql = "SELECT '0'   as oid_lote_pagamento, "
            + "       '---' as nr_documento_lote, "
            + "       movimentos_compromissos.dt_pagamento,  "
            + "       movimentos_compromissos.vl_pagamento,  "
            + "       compromissos.nr_documento as nr_documento_compromisso,  "
            + "       compromissos.nr_parcela,  "
            + "       '0' as cd_conta_corrente, "
            + "       '0' as nr_conta_corrente, "
            + "       'PG' as cd_tipo_documento_lote, "
            + "       '---' as nm_tipo_documento_lote, "
            + "       '---' as nm_razao_social_conta_corrente, "
            + "       tipos_documentos_compromisso.cd_tipo_documento as cd_tipo_documento_compromisso ,  "
            + "       tipos_documentos_compromisso.nm_tipo_documento as nm_tipo_documento_compromisso ,  "
            + "       pessoas_compromisso.nm_razao_social  as nm_razao_social_compromisso "
            + " FROM  compromissos, pessoas pessoas_compromisso, tipos_documentos tipos_documentos_compromisso, movimentos_compromissos "
            + " WHERE movimentos_compromissos.oid_compromisso = compromissos.oid_compromisso  "
            + " AND   Compromissos.oid_pessoa = pessoas_compromisso.oid_pessoa "
            + " AND   Compromissos.oid_tipo_documento = tipos_documentos_compromisso.oid_tipo_documento  ";

        if (String.valueOf (ed.getOid_Pessoa ()) != null && !String.valueOf (ed.getOid_Pessoa ()).equals ("") && !String.valueOf (ed.getOid_Pessoa ()).equals ("null")) {
          sql += " and compromissos.Oid_Pessoa = '" + ed.getOid_Pessoa () + "'";
        }
        if (String.valueOf (ed.getDt_Inicial ()) != null && !String.valueOf (ed.getDt_Inicial ()).equals ("") && !String.valueOf (ed.getDt_Inicial ()).equals ("null")) {
          sql += " and movimentos_compromissos.dt_pagamento >= '" + ed.getDt_Inicial () + "'";
        }
        if (String.valueOf (ed.getDt_Final ()) != null && !String.valueOf (ed.getDt_Final ()).equals ("") && !String.valueOf (ed.getDt_Final ()).equals ("null")) {
          sql += " and movimentos_compromissos.dt_pagamento <= '" + ed.getDt_Final () + "'";
        }

        sql += " order by movimentos_compromissos.dt_pagamento, compromissos.nr_documento, compromissos.nr_parcela";

        // System.out.println (sql);

        res = this.executasql.executarConsulta (sql.toString ());
      }

    }
    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "geraCompromisso_Pagamento()");
    }

    CompromissoRL conRL = new CompromissoRL ();
    b = conRL.geraCompromisso_Pagamento (res);
    return b;
  }

  public byte[] geraCompromisso_Pagamento_Juros_Multa (CompromissoED ed) throws Excecoes {

    String sql = null;
    byte[] b = null;

    sql = "SELECT       compromissos.nr_documento as nr_documento_compromisso,  "
        + "             compromissos.nr_parcela,  "
        + "             Movimentos_Compromissos.dt_pagamento,  "
        + "             Movimentos_Compromissos.VL_Pagamento,  "
        + "             Movimentos_Compromissos.VL_Multa_Pagamento,  "
        + "             Movimentos_Compromissos.VL_Juros_Pagamento,  "
        + "             Movimentos_Compromissos.VL_Outras_Despesas,  "
        + "             pessoas_compromisso.nm_razao_social  as nm_razao_social_compromisso  "
        + " FROM  Compromissos, Movimentos_Compromissos, pessoas pessoas_compromisso "
        + " WHERE Movimentos_Compromissos.oid_compromisso = compromissos.oid_compromisso  "
        + " AND   Compromissos.oid_pessoa = pessoas_compromisso.oid_pessoa "
        + " AND   Movimentos_Compromissos.vl_pagamento > 0 "
        + " AND   (   Movimentos_Compromissos.VL_Multa_Pagamento > 0 "
        + "        OR Movimentos_Compromissos.VL_Juros_Pagamento > 0 "
        + "        OR Movimentos_Compromissos.VL_Outras_Despesas > 0 ) ";

    if (String.valueOf (ed.getOid_Pessoa ()) != null && !String.valueOf (ed.getOid_Pessoa ()).equals ("") && !String.valueOf (ed.getOid_Pessoa ()).equals ("null")) {
      sql += " and compromissos.Oid_Pessoa = '" + ed.getOid_Pessoa () + "'";
    }
    if (String.valueOf (ed.getDt_Inicial ()) != null && !String.valueOf (ed.getDt_Inicial ()).equals ("") && !String.valueOf (ed.getDt_Inicial ()).equals ("null")) {
      sql += " and Movimentos_Compromissos.dt_pagamento >= '" + ed.getDt_Inicial () + "'";
    }
    if (String.valueOf (ed.getDt_Final ()) != null && !String.valueOf (ed.getDt_Final ()).equals ("") && !String.valueOf (ed.getDt_Final ()).equals ("null")) {
      sql += " and Movimentos_Compromissos.dt_pagamento <= '" + ed.getDt_Final () + "'";
    }

    sql += " order by Movimentos_Compromissos.dt_pagamento ";

    try {

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql.toString ());

      CompromissoRL conRL = new CompromissoRL ();
      b = conRL.geraCompromisso_Pagamento_Juros_Multa (res);

    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "geraCompromisso_Pagamento()");
    }
    return b;
  }

  public byte[] geraDiario_Auxiliar_Fornecedor (CompromissoED ed) throws Excecoes {

    String sql = null;
    byte[] b = null;

    try {

      sql = " SELECT NR_Proximo_Sort ";
      sql += " FROM Parametros_Filiais ";
      sql += " WHERE Parametros_Filiais.OID_Unidade = 2 ";

      ResultSet rs = null;
      long Nr_Sort = 0;

      rs = this.executasql.executarConsulta (sql);
      while (rs.next ()) {
        Nr_Sort = rs.getInt ("NR_Proximo_Sort");
      }
      sql = " select * from compromissos  " +
          " WHERE  compromissos.oid_compromisso_vincula is null  " +
          " AND    compromissos.dt_liberado is not null ";

      if (String.valueOf (ed.getOid_Pessoa ()) != null && !String.valueOf (ed.getOid_Pessoa ()).equals ("") && !String.valueOf (ed.getOid_Pessoa ()).equals ("null")) {
        sql += " and compromissos.Oid_Pessoa = '" + ed.getOid_Pessoa () + "'";
      }
      if (String.valueOf (ed.getDt_Inicial ()) != null && !String.valueOf (ed.getDt_Inicial ()).equals ("") && !String.valueOf (ed.getDt_Inicial ()).equals ("null")) {
        sql += " and Compromissos.dt_Emissao >= '" + ed.getDt_Inicial () + "'";
      }
      if (String.valueOf (ed.getDt_Final ()) != null && !String.valueOf (ed.getDt_Final ()).equals ("") && !String.valueOf (ed.getDt_Final ()).equals ("null")) {
        sql += " and Compromissos.dt_Emissao <= '" + ed.getDt_Final () + "'";
      }

      ResultSet res = this.executasql.executarConsulta (sql.toString ());

      Auxiliar1RN Auxiliar1RN = new Auxiliar1RN ();

      while (res.next ()) {

        Auxiliar1ED edAuxiliar1 = new Auxiliar1ED ();
        edAuxiliar1.setNM_Tabela ("Compromissos");
        edAuxiliar1.setOID_Tabela (res.getString ("oid_compromisso"));
        edAuxiliar1.setNM_Classifica1 (res.getString ("dt_Emissao"));
        edAuxiliar1.setNM_Classifica2 ("Entrada");
        edAuxiliar1.setNR_Sort (Nr_Sort);
        Auxiliar1RN.inclui (edAuxiliar1);
      }

      sql = "select * from Movimentos_Compromissos, compromissos where " + " movimentos_Compromissos.oid_compromisso = compromissos.oid_compromisso ";

      if (String.valueOf (ed.getOid_Pessoa ()) != null && !String.valueOf (ed.getOid_Pessoa ()).equals ("") && !String.valueOf (ed.getOid_Pessoa ()).equals ("null")) {
        sql += " and compromissos.Oid_Pessoa = '" + ed.getOid_Pessoa () + "'";
      }
      if (String.valueOf (ed.getDt_Inicial ()) != null && !String.valueOf (ed.getDt_Inicial ()).equals ("") && !String.valueOf (ed.getDt_Inicial ()).equals ("null")) {
        sql += " and movimentos_compromissos.dt_pagamento >= '" + ed.getDt_Inicial () + "'";
      }
      if (String.valueOf (ed.getDt_Final ()) != null && !String.valueOf (ed.getDt_Final ()).equals ("") && !String.valueOf (ed.getDt_Final ()).equals ("null")) {
        sql += " and movimentos_compromissos.dt_pagamento <= '" + ed.getDt_Final () + "'";
      }

      res = this.executasql.executarConsulta (sql.toString ());

      while (res.next ()) {
        Auxiliar1ED edAuxiliar1 = new Auxiliar1ED ();
        edAuxiliar1.setNM_Tabela ("Movimentos_Compromissos");
        edAuxiliar1.setOID_Tabela (res.getString ("oid_mov_compromisso"));
        edAuxiliar1.setNM_Classifica1 (res.getString ("dt_pagamento"));
        edAuxiliar1.setNM_Classifica2 ("Saida");
        edAuxiliar1.setNR_Sort (Nr_Sort);
        Auxiliar1RN.inclui (edAuxiliar1);
      }

      sql = " select * from auxiliar1 where Auxiliar1.Nr_Sort = " + Nr_Sort;
      sql += " order by auxiliar1.nm_classifica1 , auxiliar1.nm_classifica2  ";

      res = this.executasql.executarConsulta (sql.toString ());

      CompromissoRL conRL = new CompromissoRL ();
      b = conRL.geraDiario_Auxiliar_Fornecedor (res);

      sql = "select oid_auxiliar1 from auxiliar1 where Auxiliar1.Nr_Sort = " + Nr_Sort;

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
    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "geraDiario_Auxiliar_Fornecedor()");
    }
    return b;
  }

  public ArrayList GeraEDI_Compromissos (Tipo_EventoED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    try {

      sql = " SELECT NR_Proximo_Sort ";
      sql += " FROM Parametros_Filiais ";
      sql += " WHERE Parametros_Filiais.OID_Unidade = 2 ";

      ResultSet rs = null;
      ResultSet res = null;
      long Nr_Sort = 0;

      rs = this.executasql.executarConsulta (sql);
      while (rs.next ()) {
        Nr_Sort = rs.getInt ("NR_Proximo_Sort");
      }

      Nr_Sort = Nr_Sort + 1;
      sql = " UPDATE Parametros_Filiais SET NR_Proximo_Sort= " + Nr_Sort;
      sql += " WHERE Parametros_Filiais.OID_Unidade = 2 ";

      executasql.executarUpdate (sql);

      sql = "select Compromissos.DT_ENTRADA, Pessoas.NM_Razao_Social, Tipos_Documentos.CD_Tipo_Documento,  Compromissos.oid_Compromisso, Compromissos.NR_Documento, Compromissos.NR_Parcela, Compromissos.Vl_Compromisso, Compromissos.TX_Observacao,  Conta_Debito.Cd_Conta_Contabil as Cd_Conta_Debito, Conta_Credito.Cd_Conta_Contabil as Cd_Conta_Credito from compromissos, contas conta_debito, Tipos_Documentos, contas conta_credito, Pessoas where "
          + " compromissos.vl_compromisso > 0  and "
          + " compromissos.oid_compromisso_vincula is null and"
          + " Compromissos.oid_pessoa = Pessoas.oid_Pessoa and "
          + " compromissos.oid_tipo_documento = Tipos_Documentos.oid_tipo_documento and "
          + " (Tipos_Documentos.dm_debito_credito = '2' or Tipos_Documentos.dm_debito_credito = '3') and  "
          + " compromissos.oid_conta_credito = conta_credito.oid_conta and " + " compromissos.oid_conta = conta_debito.oid_conta ";

      if (String.valueOf (ed.getDt_Inicial ()) != null && !String.valueOf (ed.getDt_Inicial ()).equals ("") && !String.valueOf (ed.getDt_Inicial ()).equals ("null")) {
        sql += " and Compromissos.dt_Emissao >= '" + ed.getDt_Inicial () + "'";
      }
      if (String.valueOf (ed.getDt_Final ()) != null && !String.valueOf (ed.getDt_Final ()).equals ("") && !String.valueOf (ed.getDt_Final ()).equals ("null")) {
        sql += " and Compromissos.dt_Emissao <= '" + ed.getDt_Final () + "'";
      }

      res = this.executasql.executarConsulta (sql);

      FormataDataBean DataFormatada = new FormataDataBean ();

      res = this.executasql.executarConsulta (sql.toString ());

      Auxiliar1RN Auxiliar1RN = new Auxiliar1RN ();

      String Complemento = null;
      String Brancos = "                                               ";
      String Texto = "";

      while (res.next ()) {

        Auxiliar1ED edAuxiliar1 = new Auxiliar1ED ();
        edAuxiliar1.setNM_Tabela ("Compromissos");
        edAuxiliar1.setOID_Tabela (res.getString ("oid_Compromisso"));
        edAuxiliar1.setNM_Classifica3 ("000");
        edAuxiliar1.setVL_Classifica1 (res.getDouble ("Vl_Compromisso"));
        edAuxiliar1.setNR_Sort (Nr_Sort);

        Texto = " ";
        if (String.valueOf (res.getString ("TX_Observacao")) != null && !String.valueOf (res.getString ("TX_Observacao")).equals ("")
            && !String.valueOf (res.getString ("TX_Observacao")).equals ("null")) {
          Texto = res.getString ("TX_Observacao");
        }
        Complemento = res.getString ("CD_Tipo_Documento") + " Nr " + res.getString ("NR_Documento") + "/" + res.getString ("NR_Parcela") + "  " + res.getString ("NM_Razao_Social") + " " + Texto
            + Brancos + Brancos + Brancos + Brancos + Brancos;
        Complemento = String.valueOf (Complemento.substring (0 , 100));
        Complemento = String.valueOf (Complemento.substring (0 , 100));

        edAuxiliar1.setNM_Classifica1 (res.getString ("Cd_Conta_Debito"));
        edAuxiliar1.setNM_Classifica2 ("   ");
        edAuxiliar1.setNM_Classifica3 ("000");

        edAuxiliar1.setNM_Classifica4 (Complemento);

        DataFormatada.setDT_FormataData (res.getString ("DT_ENTRADA"));
        edAuxiliar1.setNM_Classifica5 (DataFormatada.getDT_FormataData ());

        Auxiliar1RN.inclui (edAuxiliar1);

        edAuxiliar1.setNM_Classifica1 ("   ");
        edAuxiliar1.setNM_Classifica2 (res.getString ("Cd_Conta_Credito"));
        edAuxiliar1.setNM_Classifica4 (Complemento);

        Auxiliar1RN.inclui (edAuxiliar1);
      }

      sql = " select * from auxiliar1 where Auxiliar1.Nr_Sort = " + Nr_Sort;
      sql += " order by auxiliar1.oid_auxiliar1  ";

      res = this.executasql.executarConsulta (sql);

      while (res.next ()) {
        Tipo_EventoED edVolta = new Tipo_EventoED ();

        sql = " select CD_UNIDADE_CONTABIL from Unidades where OID_UNIDADE = " + ed.getOID_Unidade ();

        ResultSet resTP = this.executasql.executarConsulta (sql);
        while (resTP.next ()) {
          edVolta.setCD_Unidade_Contabil (resTP.getString ("CD_UNIDADE_CONTABIL"));
        }
        edVolta.setCd_Conta_Debito (res.getString ("nm_classifica1"));
        edVolta.setCd_Conta_Credito (res.getString ("nm_classifica2"));

        edVolta.setCd_Historico (res.getString ("nm_classifica3"));
        edVolta.setNm_Complemento_Historico (res.getString ("nm_classifica4"));
        edVolta.setNm_Arquivo_Saida (ed.getNm_Arquivo_Saida ());
        //            edVolta.setDt_Lancamento(ed.getDt_Final());
        edVolta.setDt_Lancamento (res.getString ("nm_classifica5"));
        edVolta.setDm_Totalizado ("N");
        edVolta.setVl_Lancamento (res.getDouble ("vl_classifica1"));

        list.add (edVolta);
      }

      sql = "select oid_auxiliar1 from auxiliar1 where Auxiliar1.Nr_Sort = " + Nr_Sort;

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
    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "GeraEDI_Compromissos()");
    }
    return (list);
  }

  public ArrayList GeraEDI_Movimentos_Compromissos (Tipo_EventoED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    try {

      sql = " SELECT NR_Proximo_Sort ";
      sql += " FROM Parametros_Filiais ";
      sql += " WHERE Parametros_Filiais.OID_Unidade = 2 ";

      ResultSet rs = null;
      ResultSet res = null;
      long Nr_Sort = 0;

      rs = this.executasql.executarConsulta (sql);
      while (rs.next ()) {
        Nr_Sort = rs.getInt ("NR_Proximo_Sort");
      }

      Nr_Sort++;

      sql = "select Compromissos.oid_Compromisso, Compromissos.NR_Documento as NR_Doc_Comp, Compromissos.NR_Parcela, Compromissos.Vl_Compromisso, Compromissos.TX_Observacao, Pessoas.NM_Razao_Social, Tipo_Documento_Compromisso.CD_Tipo_Documento as Doc_Comp, Tipo_Documento_Lote.CD_Tipo_Documento as Doc_Lote, Lotes_Pagamentos.Nr_Documento as NR_Doc_Lote, Lotes_Pagamentos.DT_EMISSAO, Lotes_Pagamentos.VL_Lote_Pagamento, Lotes_Compromissos.Vl_Pagamento, Lotes_Compromissos.Vl_Juros_Pagamento, Lotes_Compromissos.Vl_Desconto, Lotes_Compromissos.Vl_Outras_Despesas, Conta_Debito.Cd_Conta_Contabil as Cd_Conta_Debito, Conta_Credito.Cd_Conta_Contabil as Cd_Conta_Credito "
          + " from Tipos_Documentos Tipo_Documento_Lote, Tipos_Documentos Tipo_Documento_Compromisso, compromissos, contas conta_debito, contas conta_credito,  Lotes_Pagamentos, Lotes_Compromissos, Contas_Correntes, Pessoas  where "
          + " Compromissos.oid_compromisso = Lotes_Compromissos.oid_Compromisso and "
          + " Lotes_Compromissos.oid_lote_Pagamento = Lotes_Pagamentos.oid_lote_Pagamento and "
          + " Lotes_Pagamentos.oid_Conta_Corrente = Contas_Correntes.oid_Conta_Corrente and "
          + " Lotes_Pagamentos.oid_Tipo_Documento = Tipo_Documento_Lote.oid_Tipo_Documento and "
          + " Compromissos.oid_Tipo_Documento = Tipo_Documento_Compromisso.oid_Tipo_Documento and "
          + " Compromissos.oid_pessoa = Pessoas.oid_Pessoa and "
          + " Contas_Correntes.oid_Conta = conta_credito.oid_conta and "
          + " Lotes_Pagamentos.Vl_Lote_Pagamento > 0  and "
          + " compromissos.oid_conta_credito = conta_debito.oid_conta and "
          + " Lotes_Pagamentos.dt_emissao >= '"
          + ed.getDt_Inicial ()
          + "'"
          + " and Lotes_Pagamentos.dt_emissao <= '"
          + ed.getDt_Final ()
          + "'"
          + " and Lotes_Pagamentos.DM_SITUACAO <> 'C'"
          + " Order by Lotes_Pagamentos.oid_lote_pagamento, Lotes_Pagamentos.nr_documento";

      res = this.executasql.executarConsulta (sql);

      FormataDataBean DataFormatada = new FormataDataBean ();

      res = this.executasql.executarConsulta (sql.toString ());

      Auxiliar1RN Auxiliar1RN = new Auxiliar1RN ();
      String NR_Anterior = null;
      String Complemento = null;
      String Brancos = "                                               ";
      String Texto = "";
      double VL_Pagamento = 0;

      while (res.next ()) {

        Auxiliar1ED edAuxiliar1 = new Auxiliar1ED ();
        edAuxiliar1.setNM_Tabela ("Lotes_Pagamento");

        if (! (res.getString ("NR_Doc_Lote").equals (NR_Anterior))) {
          edAuxiliar1.setVL_Classifica1 (res.getDouble ("VL_Lote_Pagamento"));
          edAuxiliar1.setNM_Classifica1 (" ");
          edAuxiliar1.setNM_Classifica2 (res.getString ("Cd_Conta_Credito"));
          edAuxiliar1.setNM_Classifica3 ("23");
          edAuxiliar1.setNM_Classifica4 (res.getString ("Doc_Lote") + " " + res.getString ("NR_Doc_Lote"));
          DataFormatada.setDT_FormataData (res.getString ("DT_EMISSAO"));
          edAuxiliar1.setNM_Classifica5 (DataFormatada.getDT_FormataData ());
          edAuxiliar1.setNR_Sort (Nr_Sort);
          Auxiliar1RN.inclui (edAuxiliar1);
        }
        NR_Anterior = res.getString ("NR_Doc_Lote");

        VL_Pagamento = (res.getDouble ("Vl_Pagamento") - res.getDouble ("Vl_Juros_Pagamento") - res.getDouble ("Vl_Outras_Despesas") + res.getDouble ("Vl_Desconto"));

        edAuxiliar1.setNM_Tabela ("Lotes_Pagamento");
        edAuxiliar1.setVL_Classifica1 (VL_Pagamento);
        edAuxiliar1.setNM_Classifica1 (res.getString ("Cd_Conta_Debito"));
        edAuxiliar1.setNM_Classifica2 ("   ");
        edAuxiliar1.setNM_Classifica3 ("23");
        DataFormatada.setDT_FormataData (res.getString ("DT_EMISSAO"));
        edAuxiliar1.setNM_Classifica5 (DataFormatada.getDT_FormataData ());

        Texto = " ";
        if (String.valueOf (res.getString ("TX_Observacao")) != null && !String.valueOf (res.getString ("TX_Observacao")).equals ("")
            && !String.valueOf (res.getString ("TX_Observacao")).equals ("null")) {
          Texto = res.getString ("TX_Observacao");
        }

        Complemento = edAuxiliar1.getNM_Classifica4 () + " PGT " + res.getString ("Doc_Comp") + " Nr " + res.getString ("NR_Doc_Comp") + "/" + res.getString ("NR_Parcela") + "  "
            + res.getString ("NM_Razao_Social") + " " + Texto + Brancos + Brancos + Brancos + Brancos + Brancos;
        Complemento = String.valueOf (Complemento.substring (0 , 100));

        edAuxiliar1.setNM_Classifica4 (Complemento);
        edAuxiliar1.setNR_Sort (Nr_Sort);
        Auxiliar1RN.inclui (edAuxiliar1);

        if (res.getDouble ("Vl_Juros_Pagamento") + res.getDouble ("Vl_Outras_Despesas") > 0) {
          edAuxiliar1.setNM_Tabela ("Lotes_Pagamento");
          edAuxiliar1.setVL_Classifica1 (res.getDouble ("Vl_Juros_Pagamento") + res.getDouble ("Vl_Outras_Despesas"));
          edAuxiliar1.setNM_Classifica1 ("6103"); //cta debito
          edAuxiliar1.setNM_Classifica2 ("   ");
          edAuxiliar1.setNM_Classifica3 ("23");
          Complemento = "Juros " + res.getString ("Doc_Comp") + " Nr " + res.getString ("NR_Doc_Comp") + "/" + res.getString ("NR_Parcela") + "  " + res.getString ("NM_Razao_Social") + " "
              + Brancos + Brancos + Brancos + Brancos + Brancos;
          Complemento = String.valueOf (Complemento.substring (0 , 100));
          edAuxiliar1.setNM_Classifica4 (Complemento);
          DataFormatada.setDT_FormataData (res.getString ("DT_EMISSAO"));
          edAuxiliar1.setNM_Classifica5 (DataFormatada.getDT_FormataData ());
          edAuxiliar1.setNR_Sort (Nr_Sort);
          Auxiliar1RN.inclui (edAuxiliar1);
        }
        if (res.getDouble ("Vl_Desconto") > 0) {
          edAuxiliar1.setNM_Tabela ("Lotes_Pagamento");
          edAuxiliar1.setVL_Classifica1 (res.getDouble ("Vl_Desconto"));
          edAuxiliar1.setNM_Classifica1 ("    "); //cta debito
          edAuxiliar1.setNM_Classifica2 ("6110"); //cta credito
          edAuxiliar1.setNM_Classifica3 ("23");
          Complemento = "Desconto " + res.getString ("Doc_Comp") + " Nr " + res.getString ("NR_Doc_Comp") + "/" + res.getString ("NR_Parcela") + "  " + res.getString ("NM_Razao_Social") + " "
              + Brancos + Brancos + Brancos + Brancos + Brancos;
          Complemento = String.valueOf (Complemento.substring (0 , 100));
          edAuxiliar1.setNM_Classifica4 (Complemento);
          DataFormatada.setDT_FormataData (res.getString ("DT_EMISSAO"));
          edAuxiliar1.setNM_Classifica5 (DataFormatada.getDT_FormataData ());
          edAuxiliar1.setNR_Sort (Nr_Sort);
          Auxiliar1RN.inclui (edAuxiliar1);
        }
      }

      sql = " select * from auxiliar1 where Auxiliar1.Nr_Sort = " + Nr_Sort;
      sql += " order by auxiliar1.oid_auxiliar1  ";

      res = this.executasql.executarConsulta (sql);

      while (res.next ()) {
        Tipo_EventoED edVolta = new Tipo_EventoED ();

        sql = " select CD_UNIDADE_CONTABIL from Unidades where OID_UNIDADE = " + ed.getOID_Unidade ();

        ResultSet resTP = this.executasql.executarConsulta (sql);
        while (resTP.next ()) {
          edVolta.setCD_Unidade_Contabil (resTP.getString ("CD_UNIDADE_CONTABIL"));
        }
        edVolta.setCd_Conta_Debito (res.getString ("nm_classifica1"));
        edVolta.setCd_Conta_Credito (res.getString ("nm_classifica2"));

        edVolta.setCd_Historico (res.getString ("nm_classifica3"));
        edVolta.setNm_Complemento_Historico (res.getString ("nm_classifica4"));
        edVolta.setNm_Arquivo_Saida (ed.getNm_Arquivo_Saida ());
        edVolta.setDt_Lancamento (res.getString ("nm_classifica5"));
        //            edVolta.setDt_Lancamento(ed.getDt_Final());
        edVolta.setDm_Totalizado ("N");
        edVolta.setVl_Lancamento (res.getDouble ("vl_classifica1"));
        list.add (edVolta);
      }

      sql = "select oid_auxiliar1 from auxiliar1 where Auxiliar1.Nr_Sort = " + Nr_Sort;

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
    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "GeraEDI_Movimentos_Compromissos()");
    }
    return (list);
  }

  public CompromissoED inclui_provisao (CompromissoED ed) throws Excecoes {

    int nrQtdeGerar = (ed.getNR_Qtde_Provisao ().intValue ());
    int nrQtdeGerada = 0, nr_maior_parcela=0;
    int dia = ed.getNR_Dia_Provisao ().intValue ();
    int mes = new Integer (ed.getDt_Vencimento ().substring (3 , 5)).intValue ();
    int ano = new Integer (ed.getDt_Vencimento ().substring (6 , 10)).intValue ();
    String data , Dia , Mes , Ano, sql;

    try {

        sql =" SELECT max(NR_Parcela) as nr_maior_parcela FROM Compromissos WHERE oid_Compromisso = " + ed.getOid_Compromisso();
        //System.out.println("nr_maior_parcela=" + sql);

        ResultSet res = this.executasql.executarConsulta (sql);
        if (res.next ()) {
          nr_maior_parcela=res.getInt("nr_maior_parcela");
        }

      ed = this.getByRecord(ed);

      while (nrQtdeGerada < nrQtdeGerar) {
        nrQtdeGerada++;
        mes++;
        if (mes > 12) {
          mes = 1;
          ano++;
        }
        Dia = String.valueOf (dia);
        if (dia < 10) {
          Dia = "0" + Dia;
        }

        Mes = String.valueOf (mes);
        if (mes < 10) {
          Mes = "0" + Mes;
        }
        Ano = String.valueOf (ano);

        //*** Valida setando ultima data v�lida para o mes passado, caso data passada seja superior a ultima data valida do mes
         data = Data.getDataMaximaNoMes (Dia + "/" + Mes + "/" + Ano);

        ed.setNr_Parcela (new Integer (nr_maior_parcela + nrQtdeGerada));
        ed.setDt_Emissao (Data.getDataDMY ());
        ed.setDt_Liberado (Data.getDataDMY ());
        ed.setDt_Entrada (data);
        ed.setDt_Vencimento (data);
        this.inclui (ed);
      }
      return ed;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "inclui_provisao()");
    }
  }

  public CompromissoED gera_parcela (CompromissoED ed) throws Excecoes {

    CompromissoED edComp = (CompromissoED) ed;
    String dia_fixo="";
    String ultimo_mes="";
    int nr_parcelas =ed.getNr_Parcelas();
    int nr_dias_vencimento=ed.getNr_Dias_Vencimento();
    int nr_maior_parcela=1;
    int oid_Compromisso = ed.getOid_Compromisso().intValue();

    if (nr_dias_vencimento==999 && ed.getNR_Dia_Provisao().intValue()>=1 && ed.getNR_Dia_Provisao().intValue()<=31) {
    	nr_dias_vencimento=30;
    	dia_fixo=String.valueOf(ed.getNR_Dia_Provisao());
    	if (dia_fixo.length()==1) dia_fixo="0"+dia_fixo;
    }

    double vl_saldo=0;
    double vl_parcela=0;
    double vl_parcelado=0;
    double vl_saldo_parcelado=0;
    String sql ="";
    int nr_parcela_gerada = 0;
    String data , dt_ultimo_vencimento;

    try {
      ed = this.getByRecord(edComp);
      vl_saldo = ed.getVl_Saldo().doubleValue();

      vl_parcela= (vl_saldo/nr_parcelas);

      double vl_2parcela=new Double(vl_parcela*100).longValue();
      vl_parcela=vl_2parcela/100;

      // vl_saldo_parcelado=vl_parcela;
      dt_ultimo_vencimento=ed.getDt_Vencimento();

//      sql =" SELECT max(NR_Parcela) as nr_maior_parcela FROM Compromissos WHERE oid_Compromisso = " + edComp.getOid_Compromisso();
      //System.out.println("nr_maior_parcela=" + sql);

//      ResultSet res = this.executasql.executarConsulta (sql);
//      if (res.next ()) {
//        nr_maior_parcela=res.getInt("nr_maior_parcela");
//      }
      nr_maior_parcela = 0;

      //System.out.println("nr_maior_parcela=" + nr_maior_parcela);

      sql = " UPDATE Compromissos SET NR_Parcela = 0 " +
      		" WHERE oid_Compromisso = " + oid_Compromisso;

      executasql.executarUpdate (sql);

      while (nr_parcela_gerada < nr_parcelas) {
        nr_parcela_gerada++;
        vl_saldo_parcelado+=vl_parcela;

        if ((nr_parcelas == nr_parcela_gerada)){

        	vl_parcela = vl_parcela + (vl_saldo - vl_saldo_parcelado);

        }

        ed.setOid_Compromisso_Parcela(new Integer(oid_Compromisso));
        ed.setNr_Parcela (new Integer (nr_maior_parcela+nr_parcela_gerada));
        ed.setDt_Emissao (Data.getDataDMY ());
        ed.setDt_Liberado (Data.getDataDMY ());
        data=Data.getSomaDiaData(dt_ultimo_vencimento,nr_dias_vencimento);
        if (!"".equals(dia_fixo)) {
        	int mais1dia=0;
        	while (data.substring(3,5).equals(ultimo_mes) && mais1dia<30) {
        		mais1dia++;
        		data=Data.getSomaDiaData(dt_ultimo_vencimento,mais1dia);
        	}
        	data=dia_fixo+data.substring(2,10);
        	ultimo_mes=data.substring(3,5);
        }

        if (nr_parcela_gerada==1){
            ed.setDt_Vencimento (ed.getDt_Vencimento());
            data = ed.getDt_Vencimento();
        	ultimo_mes=data.substring(3,5);
        }else{
            ed.setDt_Vencimento (data);
        }

        dt_ultimo_vencimento=data;
        ed.setVl_Compromisso(new Double(vl_parcela));
        ed.setVl_Saldo(new Double(vl_parcela));

        vl_parcelado+=vl_parcela;
        this.inclui_parcela(ed);

        ed = this.getByRecord (edComp);

      }
      if (vl_parcelado>0){

        sql = " UPDATE Compromissos SET VL_Saldo = " +(vl_saldo-vl_parcelado) +
              " WHERE oid_Compromisso = " + oid_Compromisso;

        sql = " delete from Compromissos " +
        	  " WHERE oid_Compromisso = " + oid_Compromisso;

          // System.out.println("gera_parcela sql ->" + sql);
          executasql.executarUpdate (sql);
      }


      return ed;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "inclui_provisao()");
    }
  }


  public ArrayList compromisso_Parcela_Lista (CompromissoED edComp) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    CompromissoPesquisaED ed = (CompromissoPesquisaED) edComp;

    try {
      sql = " SELECT * from Compromissos, Parcelas_Compromissos  " +
          " WHERE  compromissos.oid_Compromisso = Parcelas_Compromissos.oid_Compromisso " +
          " AND    Parcelas_Compromissos.OID_Parcelamento = " + ed.getOID_Parcelamento ();

      sql += " ORDER BY compromissos.DT_Vencimento ";
      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      //popula
      while (res.next ()) {
        CompromissoED edVolta = new CompromissoED ();
        //popular os dados de compromisso para voltar

        edVolta.setOid_Compromisso (new Integer (res.getInt ("oid_Compromisso")));
        edVolta.setNr_Compromisso (new Integer (res.getInt ("nr_Compromisso")));
        edVolta.setNr_Parcela (new Integer (res.getInt ("nr_parcela")));
        edVolta.setNr_Documento (res.getString ("nr_Documento"));

        FormataDataBean DataFormatada = new FormataDataBean ();
        DataFormatada.setDT_FormataData (res.getString ("dt_vencimento"));
        edVolta.setDt_Vencimento (DataFormatada.getDT_FormataData ());

        edVolta.setVl_Saldo (new Double (res.getDouble ("vl_saldo")));

        DataFormatada.setDT_FormataData (res.getString ("dt_emissao"));
        edVolta.setDt_Emissao (DataFormatada.getDT_FormataData ());

        list.add (edVolta);
      }
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "compromisso_Parcela_Lista()");
    }
    return list;
  }

  /** ------------ RELAT�RIOS ---------------- */
  //*** Compromissos a Pagar
   public void relCompromisso (RelatorioED ed) throws Excecoes {

     String sql = null;
     ArrayList lista = new ArrayList ();
     try {

       sql = " SELECT Compromissos.*" +
           "       ,Pessoas.NM_Razao_Social" +
           "       ,Tipos_Documentos.NM_Tipo_Documento" +
           "       ,Contas.CD_Conta" +
           "       ,Contas.NM_Conta" +
           " FROM Compromissos " +
           "      left join Movimentos_Compromissos " +
           "        on Movimentos_Compromissos.oid_Compromisso = Compromissos.oid_Compromisso " +
           "      ,Pessoas" +
           "      ,Tipos_Documentos" +
           "      ,Contas " +
           " WHERE Compromissos.oid_Compromisso_Vincula is null " +
           "   AND Compromissos.DT_Liberado is not null " +
           "   AND Compromissos.oid_Pessoa = Pessoas.oid_Pessoa  " +
           "   AND Compromissos.oid_Tipo_Documento = Tipos_Documentos.oid_Tipo_Documento " +
           "   AND Compromissos.oid_Conta = Contas.oid_Conta ";

       //*** Pagamento
        if (JavaUtil.doValida (ed.getDt_pagamento ()) && JavaUtil.doValida (ed.getDt_pagamento_final ())) {
          sql += " AND Movimentos_Compromissos.DT_Pagamento BETWEEN '" + ed.getDt_pagamento () + "' AND '" + ed.getDt_pagamento_final () + "'";
        }
        else if (JavaUtil.doValida (ed.getDt_pagamento ()) || JavaUtil.doValida (ed.getDt_pagamento_final ())) {
          sql += " AND Movimentos_Compromissos.DT_Pagamento = '" + (JavaUtil.doValida (ed.getDt_pagamento ()) ? ed.getDt_pagamento () : ed.getDt_pagamento_final ()) + "'";
        }
       //*** Vencimento
        if (JavaUtil.doValida (ed.getDt_vencimento ()) && JavaUtil.doValida (ed.getDt_vencimento_final ())) {
          sql += " AND Compromissos.DT_Vencimento BETWEEN '" + ed.getDt_vencimento () + "' AND '" + ed.getDt_vencimento_final () + "'";
        }
        else if (JavaUtil.doValida (ed.getDt_vencimento ()) || JavaUtil.doValida (ed.getDt_vencimento_final ())) {
          sql += " AND Compromissos.DT_Vencimento = '" + (JavaUtil.doValida (ed.getDt_vencimento ()) ? ed.getDt_vencimento () : ed.getDt_vencimento_final ()) + "'";
        }
       //*** Entrada
        if (JavaUtil.doValida (ed.getDt_entrada_inicial ()) && JavaUtil.doValida (ed.getDt_entrada_final ())) {
          sql += " AND Compromissos.DT_Entrada BETWEEN '" + ed.getDt_entrada_inicial () + "' AND '" + ed.getDt_entrada_final () + "'";
        }
        else if (JavaUtil.doValida (ed.getDt_entrada_inicial ()) || JavaUtil.doValida (ed.getDt_entrada_final ())) {
          sql += " AND Compromissos.DT_Entrada = '" + (JavaUtil.doValida (ed.getDt_entrada_inicial ()) ? ed.getDt_entrada_inicial () : ed.getDt_entrada_final ()) + "'";
        }
       //*** Emissao
        if (JavaUtil.doValida (ed.getDt_emissao ()) && JavaUtil.doValida (ed.getDt_emissao_final ())) {
          sql += " AND Compromissos.DT_Emissao BETWEEN '" + ed.getDt_emissao () + "' AND '" + ed.getDt_emissao_final () + "'";
        }
        else if (JavaUtil.doValida (ed.getDt_emissao ()) || JavaUtil.doValida (ed.getDt_emissao_final ())) {
          sql += " AND Compromissos.DT_Emissao = '" + (JavaUtil.doValida (ed.getDt_emissao ()) ? ed.getDt_emissao () : ed.getDt_emissao_final ()) + "'";
        }
       if (JavaUtil.doValida (ed.getOid_pessoa ())) {
         sql += " AND Compromissos.oid_Pessoa = '" + ed.getOid_pessoa () + "'";
       }
       if (ed.getOid_centro_custo () > 0) {
         sql += " AND Compromissos.oid_Centro_Custo = " + ed.getOid_centro_custo ();
       }
       if (ed.getOid_conta () > 0) {
         sql += " AND Compromissos.oid_Conta = " + ed.getOid_conta ();
       }
       if (JavaUtil.doValida (ed.getDm_forma_pagamento ())) {
         sql += " AND Compromissos.DM_Tipo_Pagamento = '" + ed.getDm_forma_pagamento () + "'";
       }
       if ("A".equals (ed.getDm_situacao ())) {
         sql += " AND Compromissos.VL_Saldo > 0 ";
       }
       else if ("P".equals (ed.getDm_situacao ())) {
         sql += " AND Compromissos.VL_Saldo = 0 ";
       }

       //*** Quebras
        //FORNECEDOR
        if (ed.getNomeRelatorio ().endsWith ("01")) {
          sql += " ORDER BY Compromissos.oid_Pessoa, Compromissos.DT_Vencimento, Compromissos.NR_Documento, Compromissos.NR_Parcela";
        }
        //VENCIMENTO
        else if (ed.getNomeRelatorio ().endsWith ("02") || ed.getNomeRelatorio ().endsWith ("02b")) {
          sql += " ORDER BY Compromissos.DT_Vencimento, Compromissos.NR_Documento, Compromissos.NR_Parcela";
        }
        //ENTRADA
        else if (ed.getNomeRelatorio ().endsWith ("03") || ed.getNomeRelatorio ().endsWith ("03b")) {
          sql += " ORDER BY Compromissos.DT_Entrada, Compromissos.DT_Vencimento, Compromissos.NR_Documento, Compromissos.NR_Parcela";
        }
        //CONTA
        else if (ed.getNomeRelatorio ().endsWith ("04") || ed.getNomeRelatorio ().endsWith ("04b")) {
          sql += " ORDER BY Contas.CD_Conta, Compromissos.DT_Vencimento, Compromissos.NR_Documento, Compromissos.NR_Parcela";
        }

       ResultSet res = this.executasql.executarConsulta (sql);
       while (res.next ()) {
         RelatorioED edRel = new RelatorioED ();

         edRel.setOid_pessoa (res.getString ("oid_Pessoa"));
         edRel.setNr_cnpj_cpf (edRel.getOid_pessoa ());
         edRel.setNm_razao_social (res.getString ("NM_Razao_Social"));
         edRel.setCd_banco (util.getValueDef (util.getTableStringValue ("CD_Banco" , "Fornecedores" , "oid_Pessoa = '" + edRel.getOid_pessoa () + "'") , ""));
         edRel.setNm_banco (util.getValueDef (util.getTableStringValue ("NM_Banco" , "Fornecedores" , "oid_Pessoa = '" + edRel.getOid_pessoa () + "'") , ""));
         edRel.setNr_agencia (util.getValueDef (util.getTableStringValue ("NM_Agencia" , "Fornecedores" , "oid_Pessoa = '" + edRel.getOid_pessoa () + "'") , ""));
         edRel.setNr_conta (util.getValueDef (util.getTableStringValue ("CD_Conta_Corrente" , "Fornecedores" , "oid_Pessoa = '" + edRel.getOid_pessoa () + "'") , ""));

         edRel.setOid_conta (res.getInt ("oid_Conta"));
         if (edRel.getOid_conta () > 0) {
           edRel.setCd_conta (util.getValueDef (util.getTableStringValue ("CD_Conta" , "Contas" , "oid_Conta = " + edRel.getOid_conta ()) , ""));
           edRel.setNm_conta (util.getValueDef (util.getTableStringValue ("NM_Conta" , "Contas" , "oid_Conta = " + edRel.getOid_conta ()) , ""));
         }

         edRel.setDt_vencimento (FormataDataBean.getFormatDate (res.getString ("DT_Vencimento")));
         edRel.setNm_vencimento (Data.getDiaSemana (edRel.getDt_vencimento ()));

         edRel.setNr_compromisso (res.getInt ("NR_Compromisso"));
         edRel.setNr_documento (res.getInt ("NR_Documento"));
         edRel.setNr_parcela (res.getInt ("NR_Parcela"));

         edRel.setNm_tipo_documento (res.getString ("NM_Tipo_Documento"));
         edRel.setNm_forma_pagamento (CompromissoED.getDescDM_Tipo_Pagamento (res.getString ("DM_Tipo_Pagamento")));
         edRel.setDt_emissao (FormataDataBean.getFormatDate (res.getString ("DT_Emissao")));
         edRel.setDt_entrada (FormataDataBean.getFormatDate (res.getString ("DT_Entrada")));
         edRel.setNm_entrada (Data.getDiaSemana (edRel.getDt_entrada ()));

         edRel.setVl_compromisso (res.getDouble ("VL_Compromisso"));
         edRel.setVl_saldo (res.getDouble ("VL_Saldo"));

         lista.add (edRel);
       }
       ed.setLista (lista);
     }
     catch (Exception exc) {
       throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
                           "relCompromisso()");
     }
     //*** Chama o Gerador de Relat�rios Jasper
      new JasperRL (ed).geraRelatorio ();
   }

  public void geraDiario_Razao_Fornecedores (CompromissoED edComp , HttpServletResponse response) throws
      Excecoes {

    String sql = null , sqlBusca = null;
    ArrayList list = new ArrayList ();
    CompromissoED ed = (CompromissoED) edComp;
    double saldo_anterior = 0;
    double saldo = 0;
    String cliente = "" , clieAux = "";

    try {

      long Nr_Sort = (new Long (String.valueOf (System.currentTimeMillis ()).
                                toString ().substring (6 , 12)).longValue ());

      sql = "select compromissos.dt_emissao, compromissos.oid_compromisso, compromissos.nr_compromisso, compromissos.dt_vencimento," +
          " compromissos.vl_compromisso, compromissos.oid_pessoa, Pessoas.nm_razao_social, compromissos.oid_moeda " +
          " from compromissos, pessoas where " +
          " compromissos.oid_pessoa = Pessoas.oid_pessoa " +
          " AND compromissos.oid_compromisso_vincula is null " +
          " AND compromissos.dt_liberado is not null";

      if (String.valueOf (ed.getOid_Pessoa ()) != null &&
          !String.valueOf (ed.getOid_Pessoa ()).equals ("") &&
          !String.valueOf (ed.getOid_Pessoa ()).equals ("null")) {
        sql += " and compromissos.Oid_Pessoa = '" + ed.getOid_Pessoa () + "'";
      }
      if (String.valueOf (ed.getDt_Inicial ()) != null &&
          !String.valueOf (ed.getDt_Inicial ()).equals ("") &&
          !String.valueOf (ed.getDt_Inicial ()).equals ("null")) {
        sql += " and compromissos.dt_Emissao >= '" + ed.getDt_Inicial () +
            "'";
      }
      if (String.valueOf (ed.getDt_Final ()) != null &&
          !String.valueOf (ed.getDt_Final ()).equals ("") &&
          !String.valueOf (ed.getDt_Final ()).equals ("null")) {
        sql += " and compromissos.dt_Emissao <= '" + ed.getDt_Final () +
            "'";
      }
      if (ed.getDM_Relatorio ().equals ("R")) {
        sql += " order by oid_pessoa ";
      }
      else {
        sql += " order by dt_Emissao ";
      }

      Auxiliar1RN Auxiliar1RN = new Auxiliar1RN ();

      ResultSet res = this.executasql.executarConsulta (sql.toString ());

      while (res.next ()) {
        Auxiliar1ED edAuxiliar1 = new Auxiliar1ED ();

        cliente = res.getString ("oid_pessoa");
        if (ed.getDM_Relatorio ().equals ("R")) {
          sqlBusca = "select vl_saldo as saldo_anterior, oid_moeda from compromissos where oid_pessoa = '" + cliente + "'" +
              " AND dt_Emissao < '" + ed.getDt_Inicial () + "'";
          ResultSet rs = this.executasql.executarConsulta (sqlBusca);
          saldo_anterior = 0;
          while (rs.next ()) {
            if (JavaUtil.doValida (rs.getString ("oid_moeda"))) {
              saldo_anterior += convertToReal (Data.getDataDMY () , rs.getDouble ("saldo_anterior") , rs.getInt ("oid_moeda"));
            }
            else {
              saldo_anterior += rs.getDouble ("saldo_anterior");
            }
          }
          edAuxiliar1.setVL_Classifica3 (saldo_anterior);
        }

        edAuxiliar1.setNM_Tabela ("compromissos");
        edAuxiliar1.setOID_Tabela (res.getString ("oid_compromisso"));
        edAuxiliar1.setNM_Classifica1 (res.getString ("dt_Emissao"));
        edAuxiliar1.setNM_Classifica2 ("ENTRADA");
        edAuxiliar1.setNM_Classifica3 (res.getString ("nm_razao_social"));
        edAuxiliar1.setNM_Classifica4 (res.getString ("nr_compromisso"));
        edAuxiliar1.setNM_Classifica5 (res.getString ("dt_vencimento"));
        if (JavaUtil.doValida (res.getString ("oid_moeda"))) {
          edAuxiliar1.setVL_Classifica2 (convertToReal (res.getString ("dt_Emissao") , res.getDouble ("vl_compromisso") , res.getInt ("oid_moeda")));
        }
        else {
          edAuxiliar1.setVL_Classifica2 (res.getDouble ("vl_compromisso"));
        }
        edAuxiliar1.setVL_Classifica1 (0.0);

        edAuxiliar1.setNR_Sort (Nr_Sort);
        Auxiliar1RN.inclui (edAuxiliar1);

        clieAux = cliente;

      }

      //Movimentos
      sql = "select Movimentos_Compromissos.dt_pagamento, Movimentos_Compromissos.vl_pagamento, Movimentos_Compromissos.vl_multa_pagamento, " +
          " Movimentos_Compromissos.vl_juros_pagamento, Movimentos_Compromissos.vl_desconto, Movimentos_Compromissos.vl_outras_despesas, " +
          " Compromissos.nr_compromisso, Compromissos.oid_compromisso, Compromissos.dt_vencimento, Compromissos.oid_pessoa, Pessoas.nm_razao_social, Compromissos.oid_moeda " +
          " from Movimentos_Compromissos, Compromissos, pessoas " +
          " where movimentos_Compromissos.oid_Compromisso = Compromissos.oid_Compromisso " +
          " AND Compromissos.oid_pessoa = Pessoas.oid_pessoa ";

      if (String.valueOf (ed.getOid_Pessoa ()) != null &&
          !String.valueOf (ed.getOid_Pessoa ()).equals ("") &&
          !String.valueOf (ed.getOid_Pessoa ()).equals ("null")) {
        sql += " and Compromissos.Oid_Pessoa = '" + ed.getOid_Pessoa () + "'";
      }
      if (String.valueOf (ed.getDt_Inicial ()) != null &&
          !String.valueOf (ed.getDt_Inicial ()).equals ("") &&
          !String.valueOf (ed.getDt_Inicial ()).equals ("null")) {
        sql += " and movimentos_Compromissos.dt_pagamento >= '" +
            ed.getDt_Inicial () + "'";
      }
      if (String.valueOf (ed.getDt_Final ()) != null &&
          !String.valueOf (ed.getDt_Final ()).equals ("") &&
          !String.valueOf (ed.getDt_Final ()).equals ("null")) {
        sql += " and movimentos_Compromissos.dt_pagamento <= '" +
            ed.getDt_Final () + "'";
      }
      if (ed.getDM_Relatorio ().equals ("R")) {
        sql += " order by oid_pessoa ";
      }
      else {
        sql += " order by dt_pagamento ";
      }
      res = null;
      res = this.executasql.executarConsulta (sql.toString ());

      while (res.next ()) {

        Auxiliar1ED edAuxiliar1 = new Auxiliar1ED ();
        edAuxiliar1.setNM_Tabela ("Movimentos_Compromissos");
        edAuxiliar1.setOID_Tabela (res.getString ("oid_Compromisso"));
        edAuxiliar1.setNM_Classifica1 (res.getString ("dt_pagamento"));
        edAuxiliar1.setNM_Classifica3 (res.getString ("nm_razao_social"));
        edAuxiliar1.setNM_Classifica4 (res.getString ("nr_Compromisso"));
        edAuxiliar1.setNM_Classifica5 (res.getString ("dt_vencimento"));
        if (JavaUtil.doValida (res.getString ("oid_moeda"))) {
          edAuxiliar1.setVL_Classifica1 (convertToReal (res.getString ("dt_pagamento") , res.getDouble ("vl_pagamento") , res.getInt ("oid_moeda")));
          //edAuxiliar1.setVL_Classifica2(convertToReal(res.getString("dt_pagamento"), res.getDouble("vl_pagamento"), res.getInt("oid_moeda")));
        }
        else {
          edAuxiliar1.setVL_Classifica1 (res.getDouble ("vl_pagamento"));
          //edAuxiliar1.setVL_Classifica2(res.getDouble("vl_pagamento"));
        }
        edAuxiliar1.setNM_Classifica2 ("PAGAMENTO");

        edAuxiliar1.setNR_Sort (Nr_Sort);
        Auxiliar1RN.inclui (edAuxiliar1);

      }
      //Fim Movimentos

      sql = " select * from auxiliar1 where Auxiliar1.Nr_Sort = " + Nr_Sort;
      sql += " order by auxiliar1.nm_classifica3 , auxiliar1.nm_classifica1 , auxiliar1.nm_classifica5  ";
      res = null;
      res = this.executasql.executarConsulta (sql.toString ());

      while (res.next ()) {
        CompromissoED edDiario = new CompromissoED ();

        cliente = res.getString ("nm_classifica3");
        if (ed.getDM_Relatorio ().equals ("R")) {
          if (!cliente.equals (clieAux)) {
            saldo_anterior = res.getDouble ("vl_classifica3");
            edDiario.setVl_saldo_Vinculado (new Double (saldo_anterior));
            saldo = saldo_anterior;
          }
        }

        edDiario.setDt_Emissao (dataFormatada.getDT_FormataData (res.getString ("nm_classifica1")));
        edDiario.setNm_Tipo_Documento (res.getString ("nm_classifica2"));
        edDiario.setNm_Razao_Social (res.getString ("nm_classifica3"));
        edDiario.setNr_Compromisso (new Integer (res.getInt ("nm_classifica4")));
        edDiario.setDt_Vencimento (dataFormatada.getDT_FormataData (res.getString ("nm_classifica5")));
        edDiario.setVL_Debito (res.getDouble ("vl_classifica1"));
        edDiario.setVL_Credito (res.getDouble ("vl_classifica2"));

        saldo = saldo - res.getDouble ("vl_classifica2") + res.getDouble ("vl_classifica1");
        edDiario.setVl_Saldo (new Double (saldo));

        list.add (edDiario);

        clieAux = cliente;

      }

      sql = "select oid_auxiliar1 from auxiliar1 where Auxiliar1.Nr_Sort = " + Nr_Sort;
      res = this.executasql.executarConsulta (sql.toString ());
      Auxiliar1RN AuxRN = new Auxiliar1RN ();
      while (res.next ()) {
        Auxiliar1ED edAuxiliar1 = new Auxiliar1ED ();
        edAuxiliar1.setOID_auxiliar1 (new Integer (res.getInt ("oid_auxiliar1")));
        AuxRN.deleta (edAuxiliar1);
      }

      new CompromissoRL ().geraDiario_Razao_Fornecedores (list , ed , response);

    }
    catch (Exception e) {
      e.printStackTrace ();
      throw new Excecoes (e.getMessage () , e , getClass ().getName () ,
                          "geraDiario_Auxiliar_Fornecedores()");
    }

  }

  private double convertToReal (String data , double vl , int moeda) throws Excecoes {
    double vl_real = 0;

    return Valor.round (vl_real , 2);
  }

    public void troca_Conta(CompromissoED ed) throws Excecoes {

        String sql = null;

        try {

            //Altera Compromissos
            sql = " update Compromissos set ";
            sql += "oid_conta = " + ed.getOid_Conta_Nova() ;
            sql += " where compromissos.Oid_Conta = " + ed.getOid_Conta() ;

            if (String.valueOf(ed.getOid_Unidade()) != null && !String.valueOf(ed.getOid_Unidade()).equals("") && !String.valueOf(ed.getOid_Unidade()).equals("null")) {
                sql += " and compromissos.Oid_Unidade = '" + ed.getOid_Unidade() + "'";
            }

            if (String.valueOf(ed.getOid_Compromisso()) != null && !String.valueOf(ed.getOid_Compromisso()).equals("") && !String.valueOf(ed.getOid_Compromisso()).equals("null")) {
                sql += " and compromissos.Oid_Compromisso = '" + ed.getOid_Compromisso() + "'";
            }

            if (String.valueOf(ed.getDt_Inicial()) != null && !String.valueOf(ed.getDt_Inicial()).equals("") && !String.valueOf(ed.getDt_Inicial()).equals("null")) {
                sql += " and Compromissos.dt_Stamp >= '" + ed.getDt_Inicial() + "'";
            }

            if (String.valueOf(ed.getDt_Final()) != null && !String.valueOf(ed.getDt_Final()).equals("") && !String.valueOf(ed.getDt_Final()).equals("null")) {
                sql += " and Compromissos.dt_Stamp <= '" + ed.getDt_Final() + "'";
            }

            // System.out.println(sql);
            //int i = executasql.executarUpdate(sql);

            executasql.executarUpdate(sql);

        }

        catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao alterar dados de Compromisso ");
            excecoes.setMetodo("altera(CompromissoED)");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }


    public void libera_Aprova_Compromisso (CompromissoED edComp , String dm_Libera_Aprova, String acao) throws Excecoes {

      String sql = null;
      CompromissoPesquisaED ed = (CompromissoPesquisaED) edComp;

      try {
        sql = " SELECT oid_Compromisso " +
            " FROM Compromissos " +
            " WHERE Compromissos.vl_saldo >0 ";

        if (ed.getOid_Unidade_Pagamento () > 0) {
          sql += " and compromissos.oid_unidade_pagamento = " + ed.getOid_Unidade_Pagamento ();
        }

        if (JavaUtil.doValida (ed.getData_Vencimento_Inicial ())) {
          sql += " and compromissos.dt_vencimento >= '" + ed.getData_Vencimento_Inicial () + "'";
        }
        if (JavaUtil.doValida (ed.getData_Vencimento_Final ())) {
          sql += " and compromissos.dt_vencimento <= '" + ed.getData_Vencimento_Final () + "'";
        }

        if (ed.getVL_Previsto_Inicial () > 0) {
          sql += " and compromissos.VL_Compromisso >= " + ed.getVL_Previsto_Inicial ();
        }
        if (ed.getVL_Previsto_Final () > 0) {
          sql += " and compromissos.VL_Compromisso <= " + ed.getVL_Previsto_Final ();
        }

        if (JavaUtil.doValida (ed.getNr_Documento ())) {
          sql += " and compromissos.NR_Documento = " + JavaUtil.getSQLString (ed.getNr_Documento ());
        }

        if (String.valueOf (ed.getOid_Pessoa ()) != null && !String.valueOf (ed.getOid_Pessoa ()).equals ("") && !String.valueOf (ed.getOid_Pessoa ()).equals ("null")) {
          sql += " and compromissos.Oid_Pessoa = '" + ed.getOid_Pessoa () + "'";
        }

        if ("L".equals (ed.getDM_Liberado ())) {
          sql += " and compromissos.dt_liberado is not null ";
        }
        if ("N".equals (ed.getDM_Liberado ())) {
          sql += " and compromissos.dt_liberado is null ";
        }

        if ("A".equals (ed.getDM_Aprovacao ())) {
          sql += " and compromissos.dt_aprovacao is not null ";
        }
        if ("N".equals (ed.getDM_Aprovacao ())) {
          sql += " and compromissos.dt_aprovacao is null ";
        }

        // System.out.println (sql);

        ResultSet res = this.executasql.executarConsulta (sql);
        while (res.next ()) {
          if ("LIBERA".equals (dm_Libera_Aprova)) {
            if ("MARCA".equals (acao)) {
              sql = "  UPDATE Compromissos set Dt_Liberado = '" + Data.getDataDMY () + "', OID_Usuario_Liberacao=" + ed.getOID_Usuario();
            }
            else {
              sql = "  UPDATE Compromissos set Dt_Liberado = null ";
            }
          }
          if ("APROVACAO".equals (dm_Libera_Aprova)) {
            if ("MARCA".equals (acao)) {
              sql = "  UPDATE Compromissos set Dt_Aprovacao = '" + Data.getDataDMY () + "', OID_Usuario_Aprovacao=" + ed.getOID_Usuario ();
            }
            else {
              sql = "  UPDATE Compromissos set Dt_Aprovacao = null ";
            }
          }

          sql += " WHERE oid_Compromisso = " + res.getString("oid_Compromisso");

          // System.out.println(sql);

          executasql.executarUpdate (sql);
        }
      }
      catch (Exception exc) {
        throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "lista()");
      }
    }

}
