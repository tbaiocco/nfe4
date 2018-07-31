package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.CompromissoED;
import com.master.ed.Posto_CompromissoED;
import com.master.ed.Posto_CompromissoPesquisaED;
import com.master.root.FormataDataBean;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;

public class Posto_CompromissoBD {

  private ExecutaSQL executasql;

  public Posto_CompromissoBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public Posto_CompromissoED inclui(Posto_CompromissoED ed) throws Excecoes{

    String sql = null;
    long valOid = 0;
    String chave = null;
    Posto_CompromissoED Posto_CompromissoED = new Posto_CompromissoED();

    try{

      ResultSet rs = executasql.executarConsulta("select max(NR_Sequencia) as result from Postos_Compromissos where Oid_Lote_Posto ="  + ed.getOid_Lote_Posto());

      //pega proximo valor da chave
      while (rs.next()){
        valOid = rs.getLong("result") + 1;
      }


      chave = (String.valueOf(ed.getOid_Lote_Posto()) + String.valueOf(ed.getOid_Compromisso()));

      ed.setOid_Posto_Compromisso(chave);

      sql = " insert into Postos_Compromissos "+
      "(Oid_Posto_Compromisso, DT_PAGAMENTO, VL_PAGAMENTO, "+
      "VL_MULTA_PAGAMENTO, VL_JUROS_PAGAMENTO, VL_DESCONTO, VL_Saldo_Posto_Compromisso, "+
      "VL_Pedagio_Pagar, VL_Pedagio_Receber, "+
      "VL_OUTRAS_DESPESAS, TX_OBSERVACAO, "+
      "DT_STAMP, USUARIO_STAMP, DM_STAMP, "+
      "DM_TIPO_PAGAMENTO, NR_Lote_Posto, OID_COMPROMISSO, OID_Lote_Posto, DM_Situacao, NR_Sequencia) values "+
      "('" + ed.getOid_Posto_Compromisso() + "',";
      sql += "'" + ed.getDt_Pagamento() + "',";
      sql += ed.getVl_Pagamento() + ",";
      sql +=  ed.getVl_Multa_Pagamento() == null ? "0," : ed.getVl_Multa_Pagamento() + ",";
      sql += ed.getVl_Juros_Pagamento() == null ? "0," : ed.getVl_Juros_Pagamento() + ",";
      sql += ed.getVl_Desconto() == null ? "0," : ed.getVl_Desconto() + ",";
      sql += ed.getVl_Saldo_Posto_Compromisso() == null ? "null," : ed.getVl_Saldo_Posto_Compromisso() + ",";
      sql += ed.getVL_Pedagio_Pagar() == null ? "0," : ed.getVL_Pedagio_Pagar() + ",";
      sql += ed.getVL_Pedagio_Receber() == null ? "0," : ed.getVL_Pedagio_Receber() + ",";
      sql += ed.getVl_Outras_Despesas() == null ? "0," : ed.getVl_Outras_Despesas() + ",";
      sql += ed.getTx_Observacao() == null ? "null," : "'" + ed.getTx_Observacao() + "',";
      sql += "'" + ed.getDt_stamp() + "',";
      sql += "'" + ed.getUsuario_Stamp() + "',";
      sql += "'" + ed.getDm_Stamp() + "',";
      sql += ed.getDm_Tipo_Pagamento() == null ? "null," : "'" + ed.getDm_Tipo_Pagamento() + "',";
      sql += ed.getNr_Lote_Posto() == null ? "null," : ed.getNr_Lote_Posto() + ",";
      sql += ed.getOid_Compromisso() + ",";
      sql += ed.getOid_Lote_Posto() + ",";
      sql += "'" + "A" + "',";
      sql += valOid +")";

// System.out.println(sql);

      int i = executasql.executarUpdate(sql);

      sql = "select * from Lotes_Postos "+
            " where oid_Lote_Posto = " + ed.getOid_Lote_Posto();
// System.out.println(sql);
      ResultSet res = null;

      res = this.executasql.executarConsulta(sql);
      while (res.next()){
        ed.setVl_Lote_Posto(new Double(res.getString("vl_Lote_Posto")));
      }

      double dValSub = ed.getVl_Pagamento().doubleValue();
      double dValComp= ed.getVl_Lote_Posto().doubleValue();

      ed.setVl_Lote_Posto(new Double(dValComp + dValSub));

      sql = " update Lotes_Postos set ";

      sql += " VL_Lote_Posto = VL_Lote_Posto +'" + ed.getVl_Saldo_Posto_Compromisso()+"'";

  //    sql += " VL_Lote_Posto = " + ed.getVl_Lote_Posto();
      sql += " where oid_Lote_Posto = " + ed.getOid_Lote_Posto();

// System.out.println(sql);
      int u = executasql.executarUpdate(sql);

      sql = " update Compromissos set ";
      sql += " DM_Lote_Posto = 'S'";
      sql += " where Compromissos.oid_Compromisso = " + ed.getOid_Compromisso();
// System.out.println(sql);
      u = executasql.executarUpdate(sql);

      sql = "select * from lancamentos_contabeis, compromissos "+
            " where lancamentos_contabeis.oid_compromisso = Compromissos.oid_compromisso "+
            " AND Compromissos.oid_tipo_documento = 31 "+
            " AND Compromissos.oid_Compromisso = " + ed.getOid_Compromisso();

      res = null;

      res = this.executasql.executarConsulta(sql);
      while (res.next()){
        sql = " update Lancamentos_Contabeis set ";
        sql += " oid_Lote_Posto = "+ed.getOid_Lote_Posto();
        sql += " where oid_lancamento = " + res.getLong("OID_Lancamento");
// System.out.println(sql);
        u = executasql.executarUpdate(sql);
      }

      Posto_CompromissoED.setOid_Posto_Compromisso(ed.getOid_Posto_Compromisso());
      Posto_CompromissoED.setOid_Lote_Posto(ed.getOid_Lote_Posto());
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir Posto_Compromisso");
      excecoes.setMetodo("Inclui(Posto_CompromissoED)");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return Posto_CompromissoED;

  }

  public Posto_CompromissoED getByRecord(Posto_CompromissoED ed)throws Excecoes{

    String sql = null;
    Posto_CompromissoED edVolta = new Posto_CompromissoED();

    try{

      sql = "select * from Postos_Compromissos, compromissos, pessoas, tipos_documentos where "+
      " Postos_Compromissos.oid_compromisso = compromissos.oid_compromisso and "+
      " compromissos.oid_tipo_documento = tipos_documentos.oid_tipo_documento and "+
      " compromissos.oid_pessoa = pessoas.oid_pessoa ";

      if (ed.getOid_Posto_Compromisso() != null)
        sql += " and Postos_Compromissos.Oid_Posto_Compromisso = " + ed.getOid_Posto_Compromisso();
      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      while (res.next()){

        edVolta.setNr_Lote_Posto(new Integer(res.getString("OID_Lote_Posto")));
        edVolta.setOid_Lote_Posto(new Integer(res.getString("OID_Lote_Posto")));

        edVolta.setOid_Compromisso(new Integer(res.getString("OID_Compromisso")));

        sql = "select * from Lancamentos_Contabeis"+
        " WHERE Lancamentos_Contabeis.oid_compromisso = " + edVolta.getOid_Compromisso();

        ResultSet resLocal = null;
        resLocal = this.executasql.executarConsulta(sql);
        boolean DM_Tem_Lancamento_Contabil = false;
        while (resLocal.next()){
          DM_Tem_Lancamento_Contabil = true;
        }

        edVolta.setDM_Tem_Lancamento_Contabil(DM_Tem_Lancamento_Contabil);



////////// System.out.println("Aqui Lote 1");

        edVolta.setVL_Pedagio_Pagar(new Double(res.getString("VL_Pedagio_Pagar")));
////////// System.out.println("Aqui Lote 2");
        edVolta.setVL_Pedagio_Receber(new Double(res.getString("VL_Pedagio_Receber")));
////////// System.out.println("Aqui Lote 3");
        edVolta.setVL_Juro_Pagamento(new Double(res.getString("VL_Juros_Pagamento")));
////////// System.out.println("Aqui Lote 4");
        long aux = res.getLong("oid_unidade");
        edVolta.setOID_Unidade(aux);

        edVolta.setNm_Razao_Social(res.getString("nm_razao_social"));
        edVolta.setNr_Parcela(new Integer(res.getString("nr_parcela")));
        edVolta.setNm_Tipo_Documento(res.getString("nm_tipo_documento"));

        FormataDataBean DataFormatada = new FormataDataBean();
        DataFormatada.setDT_FormataData(res.getString("dt_pagamento"));
        edVolta.setDt_Pagamento(DataFormatada.getDT_FormataData());

        edVolta.setVl_Pagamento(new Double(res.getString("vl_pagamento")));
        edVolta.setNr_Compromisso(new Integer(res.getString("nr_compromisso")));
        edVolta.setVl_Saldo(new Double(res.getString("vl_saldo_Posto_Compromisso")));
        edVolta.setVl_Saldo_Posto_Compromisso(new Double(res.getString("vl_saldo_Posto_Compromisso")));
	//////// System.out.println("Aqui Lote 4"+res.getString("vl_saldo_Posto_Compromisso"));

      edVolta.setNr_Documento(res.getString("nr_documento"));
        edVolta.setDM_Situacao(res.getString("DM_Situacao"));

        String vl_Multa_Pagamento = res.getString("vl_multa_pagamento");
        if (vl_Multa_Pagamento != null)
          edVolta.setVl_Multa_Pagamento(new Double(vl_Multa_Pagamento));

        String vl_Outras_Despesas = res.getString("vl_outras_despesas");
        if (vl_Outras_Despesas != null)
          edVolta.setVl_Outras_Despesas(new Double(vl_Outras_Despesas));

        String vl_Desconto = res.getString("vl_desconto");
        if (vl_Desconto != null)
          edVolta.setVl_Desconto(new Double(vl_Desconto));

        String obs = res.getString("tx_observacao");
        if (obs != null)
          edVolta.setTx_Observacao(obs);

        edVolta.setOid_Posto_Compromisso(res.getString("Oid_Posto_Compromisso"));

//        sql = " select DM_Situacao from Lotes_Postos " +
//              " WHERE Lotes_Postos.oid_Lote_Posto = " + edVolta.getOid_Lote_Posto();
//
//        ResultSet resTP = null;
//
//        resTP = this.executasql.executarConsulta(sql);
//        while (resTP.next()){
//          edVolta.setDM_Situacao(resTP.getString("DM_Situacao"));
//        }
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao acessar Posto_Compromisso");
      excecoes.setMetodo("getByRecord(Posto_CompromissoED)");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return edVolta;
  }

  public void altera(Posto_CompromissoED ed) throws Excecoes{

    String sql = null;

    try{

      sql = " update Postos_Compromissos set ";

      if (ed.getDt_Pagamento() != null)
        sql += " DT_PAGAMENTO = '" + ed.getDt_Pagamento() + "', ";
      else
        sql += " DT_PAGAMENTO = null,";

      if (ed.getVl_Multa_Pagamento() != null)
        sql += " VL_MULTA_PAGAMENTO = " + ed.getVl_Multa_Pagamento() + ", ";
      else
        sql += " VL_MULTA_PAGAMENTO = null,";

      if (ed.getVl_Juros_Pagamento() != null)
        sql += " VL_JUROS_PAGAMENTO = " + ed.getVl_Juros_Pagamento() + ", ";
      else
        sql += " VL_JUROS_PAGAMENTO = null,";

      if (ed.getVL_Pedagio_Pagar() != null)
        sql += " VL_Pedagio_Pagar = " + ed.getVL_Pedagio_Pagar() + ", ";
      else
        sql += " VL_Pedagio_Pagar = null,";

      if (ed.getVL_Pedagio_Receber() != null)
        sql += " VL_Pedagio_Receber = " + ed.getVL_Pedagio_Receber() + ", ";
      else
        sql += " VL_Pedagio_Receber = null,";

      if (ed.getVl_Desconto() != null)
        sql += " VL_DESCONTO = " + ed.getVl_Desconto() + ", ";
      else
        sql += " VL_DESCONTO = null,";

      if (ed.getVl_Outras_Despesas() != null)
        sql += " VL_OUTRAS_DESPESAS = " + ed.getVl_Outras_Despesas() + ", ";
      else
        sql += " VL_OUTRAS_DESPESAS = null,";

      sql += " DT_STAMP = '" + ed.getDt_stamp() + "', ";
      sql += " USUARIO_STAMP = '" + ed.getUsuario_Stamp() + "',";
      sql += " DM_STAMP = '" + ed.getDm_Stamp() + "' ";
      sql += " where Oid_Posto_Compromisso = " + ed.getOid_Posto_Compromisso();


      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar dados de Posto_Compromisso ");
      excecoes.setMetodo("altera(Posto_CompromissoED)");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void deleta(Posto_CompromissoED ed) throws Excecoes{

    String sql = null;
    double VL_Lote_Posto = 0;
    double VL_Pagamento = 0;

    try{


      sql = "select * from Lotes_Postos where ";
      sql += " Lotes_Postos.oid_Lote_Posto = " + ed.getOid_Lote_Posto();

      ResultSet res = null;

      res = this.executasql.executarConsulta(sql);
      while (res.next()){

        VL_Lote_Posto = new Double(res.getString("VL_Lote_Posto")).doubleValue();
      }

      sql = "select * from Postos_Compromissos where ";
      sql += " Postos_Compromissos.oid_Posto_Compromisso = " + ed.getOid_Posto_Compromisso();

      res = null;

      res = this.executasql.executarConsulta(sql);
      while (res.next()){
        ed.setOid_Compromisso(new Integer(res.getString("OID_Compromisso")));
//      VL_Pagamento = new Double(res.getString("VL_Pagamento")).doubleValue();
        VL_Pagamento = new Double(res.getString("VL_Saldo_Posto_Compromisso")).doubleValue();

      }

      VL_Lote_Posto = (VL_Lote_Posto - VL_Pagamento);

      sql = " update Lotes_Postos set ";
      sql += " VL_Lote_Posto = " + VL_Lote_Posto ;
      sql += " where oid_Lote_Posto = " + ed.getOid_Lote_Posto();

      int u = executasql.executarUpdate(sql);

      sql = " update Compromissos set ";
      sql += " DM_Lote_Posto = 'N'";
      sql += " where oid_compromisso = " + ed.getOid_Compromisso();

      u = executasql.executarUpdate(sql);

//Cascata : Os lancamentos
      sql = "update lancamentos_contabeis set oid_Lote_Posto = null"+
            " where oid_compromisso = ";
      sql += ed.getOid_Compromisso()+
            " and oid_Lote_Posto ="+ ed.getOid_Lote_Posto();

// System.out.println(sql);

      u = executasql.executarUpdate(sql);

//Cascata : Dai o compromisso
      sql = "delete from Postos_Compromissos WHERE Oid_Posto_Compromisso = ";
      sql += ed.getOid_Posto_Compromisso();

      u = executasql.executarUpdate(sql);

    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao excluir Posto_Compromisso");
      excecoes.setMetodo("deleta(Posto_CompromissoED)");
      excecoes.setExc(exc);
      throw excecoes;

    }
  }

  public void estorna_Pagamento(Posto_CompromissoED ed) throws Excecoes{

    String sql = null;
    double VL_Lote_Posto = 0;
    double VL_Pagamento = 0;
    String DT_Pagamento = null;
    long OID_Mov_Compromisso = 0;

    CompromissoED compromissoED = new CompromissoED();
    CompromissoED compromissoVoltaED = new CompromissoED();
    CompromissoBD compromissoBD = new CompromissoBD(this.executasql);
    FormataDataBean DataFormatada = new FormataDataBean();
    Parametro_FixoED parametro_FixoED = new Parametro_FixoED();

    try{

      sql = "select DT_PAGAMENTO, OID_Compromisso, VL_Pagamento from Postos_Compromissos where ";
      sql += " Postos_Compromissos.oid_Posto_Compromisso = " + ed.getOid_Posto_Compromisso();

      ResultSet res = null;

      res = this.executasql.executarConsulta(sql);
      while (res.next()){
        ed.setOid_Compromisso(new Integer(res.getString("OID_Compromisso")));
        VL_Pagamento = new Double(res.getString("VL_Pagamento")).doubleValue();
        DT_Pagamento = res.getString("DT_Pagamento");
      }

      DataFormatada.setDT_FormataData(DT_Pagamento);
      DT_Pagamento = DataFormatada.getDT_FormataData();

      sql = "select OID_MOV_COMPROMISSO from Movimentos_Compromissos "+
        " WHERE oid_Compromisso = " + ed.getOid_Compromisso()+
        " AND VL_Pagamento = " + VL_Pagamento+
        " AND DT_Pagamento = '" + DT_Pagamento + "'";

      ResultSet res_MOV_Compromisso = null;

      res_MOV_Compromisso = this.executasql.executarConsulta(sql);

      while (res_MOV_Compromisso.next()){
        OID_Mov_Compromisso = res_MOV_Compromisso.getLong("OID_MOV_COMPROMISSO");

        sql = "select * from Compromissos where compromissos.oid_Compromisso = " + ed.getOid_Compromisso();

        ResultSet res_comp = null;

        res_comp = this.executasql.executarConsulta(sql);

        double vl_saldo = 0;
        while (res_comp.next()){
           vl_saldo = res_comp.getDouble("vl_saldo");

          sql = " select OID_Pessoa from Unidades "+
                " WHERE OID_Unidade = " + res_comp.getLong("OID_Unidade");

          ResultSet res_Unidade = null;

          res_Unidade = this.executasql.executarConsulta(sql);

           while (res_Unidade.next()){
             compromissoED.setOid_Pessoa(res_Unidade.getString("OID_Pessoa"));
           }

           DataFormatada.setDT_FormataData(res_comp.getString("DT_VENCIMENTO"));
           compromissoED.setDt_Vencimento(DataFormatada.getDT_FormataData());

           DataFormatada.setDT_FormataData(res_comp.getString("DT_ENTRADA"));
           compromissoED.setDt_Entrada(DataFormatada.getDT_FormataData());

           DataFormatada.setDT_FormataData(res_comp.getString("DT_EMISSAO"));
           compromissoED.setDt_Emissao(DataFormatada.getDT_FormataData());

           compromissoED.setNr_Parcela(new Integer("1"));

           compromissoED.setVl_Compromisso(new Double(VL_Pagamento));
           compromissoED.setVl_Saldo(new Double(VL_Pagamento));

           compromissoED.setNr_Documento(res_comp.getString("NR_DOCUMENTO"));

           compromissoED.setOid_Unidade(new Long(res_comp.getLong("OID_UNIDADE")));

           compromissoED.setOid_Centro_Custo(new Integer(res_comp.getInt("OID_CENTRO_CUSTO")));

           if (compromissoED.getOid_Pessoa().length() > 11){
             compromissoED.setOid_Conta(new Integer(parametro_FixoED.getOID_Conta_Juridica_Ordem_Frete()));
           }else{
             compromissoED.setOid_Conta(new Integer(parametro_FixoED.getOID_Conta_Ordem_Frete()));
           }

           compromissoED.setOid_Conta_Credito(new Integer(res_comp.getInt("OID_CONTA_CREDITO")));

           compromissoED.setOid_Natureza_Operacao(new Integer(res_comp.getInt("OID_NATUREZA_OPERACAO")));

           compromissoED.setDM_Tipo_Pagamento("1");

           compromissoED.setOid_Tipo_Documento(new Integer(res_comp.getInt("OID_TIPO_DOCUMENTO")));

           compromissoVoltaED = compromissoBD.inclui(compromissoED);

           compromissoED.setOid_Compromisso(compromissoVoltaED.getOid_Compromisso());

          sql = " update Movimentos_Compromissos set oid_Compromisso = " + compromissoED.getOid_Compromisso();
          sql += " WHERE OID_MOV_COMPROMISSO = " + OID_Mov_Compromisso;

          int u = executasql.executarUpdate(sql);

        }

        String vl_saldo_Aux = null;
        vl_saldo_Aux = String.valueOf(VL_Pagamento);
        vl_saldo = vl_saldo + new Double(vl_saldo_Aux).doubleValue();

        sql =  " UPDATE Compromissos SET Vl_Saldo= " + vl_saldo;
        sql += " WHERE Compromissos.OID_Compromisso = " + ed.getOid_Compromisso();

        int u = executasql.executarUpdate(sql);

        sql =  " UPDATE Postos_Compromissos SET DM_SITUACAO = 'E',"+
               " OID_Compromisso = " + compromissoED.getOid_Compromisso() +
               " WHERE Postos_Compromissos.OID_Posto_Compromisso = " + ed.getOid_Posto_Compromisso();

        u = executasql.executarUpdate(sql);

      }
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao estornar");
      excecoes.setMetodo("estorna_Pagamento(Posto_CompromissoED ed)");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }
  public ArrayList lista(Posto_CompromissoED edComp)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();
    Posto_CompromissoPesquisaED ed = (Posto_CompromissoPesquisaED)edComp;

    try{
      sql = "select Lotes_Postos.OID_Lote_Posto, Postos_Compromissos.dt_pagamento, Postos_Compromissos.vl_pagamento, Postos_Compromissos.Oid_Posto_Compromisso, compromissos.nr_parcela, compromissos.nr_compromisso, compromissos.NR_Documento, compromissos.vl_saldo, Postos_Compromissos.vl_saldo_Posto_Compromisso ,pessoas.nm_razao_social from Lotes_Postos, Postos_Compromissos, compromissos, pessoas where "+
      " Postos_Compromissos.oid_compromisso = compromissos.oid_compromisso and "+
      " Postos_Compromissos.oid_Lote_Posto = Lotes_Postos.oid_Lote_Posto and "+
      " compromissos.oid_pessoa = pessoas.oid_pessoa ";

      if (ed.getOid_Pessoa() != null)
        sql += " and compromissos.oid_pessoa = '" + ed.getOid_Pessoa() + "'";

      if (ed.getNr_Documento() != null)
        sql += " and compromissos.Nr_Documento = " + ed.getNr_Documento();

      if (ed.getOid_Lote_Posto() != null)
        sql += " and Postos_Compromissos.oid_Lote_Posto = " + ed.getOid_Lote_Posto();

      if (ed.getOid_Compromisso() != null)
        sql += " and Postos_Compromissos.oid_compromisso = '" + ed.getOid_Compromisso() + "'";

      if (ed.getDt_Pgto_Inicial() != null)
        sql += " and Postos_Compromissos.dt_pagamento >= '" + ed.getDt_Pgto_Inicial() + "'";

      if (ed.getDt_Pgto_Final() != null)
        sql += " and Postos_Compromissos.dt_pagamento <= '" + ed.getDt_Pgto_Final() + "'";

      sql += " ORDER BY Postos_Compromissos.nr_sequencia ";
//////// System.out.println("ALO"+sql);
      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      while (res.next()){
        Posto_CompromissoPesquisaED edVolta = new Posto_CompromissoPesquisaED();

        edVolta.setNr_Lote_Posto(new Integer(res.getString("OID_Lote_Posto")));
        edVolta.setOid_Lote_Posto(new Integer(res.getString("OID_Lote_Posto")));

        edVolta.setNm_Razao_Social(res.getString("nm_razao_social"));
        edVolta.setNr_Parcela(new Integer(res.getString("nr_parcela")));

        FormataDataBean DataFormatada = new FormataDataBean();
        DataFormatada.setDT_FormataData(res.getString("dt_pagamento"));
        edVolta.setDt_Pagamento(DataFormatada.getDT_FormataData());

        edVolta.setVl_Pagamento(new Double(res.getString("vl_pagamento")));
        edVolta.setNr_Compromisso(new Integer(res.getString("nr_compromisso")));
        edVolta.setNr_Documento(res.getString("NR_Documento"));
        edVolta.setVl_Saldo(new Double(res.getString("vl_saldo_Posto_Compromisso")));
        edVolta.setOid_Posto_Compromisso(res.getString("Oid_Posto_Compromisso"));
        list.add(edVolta);
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao listar dados de Posto_Compromisso");
      excecoes.setMetodo("lista(Posto_CompromissoED)");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return list;
  }


}
