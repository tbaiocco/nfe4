package com.master.bd;

/**
 * @author André Valadas
 */

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.CompromissoED;
import com.master.ed.Nota_Fiscal_EletronicaED;
import com.master.ed.Parcelamento_FinanceiroED;
import com.master.root.FormataDataBean;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;
import com.master.util.Valor;
import com.master.util.bd.ExecutaSQL;

public class Parcelamento_FinanceiroBD
    extends BancoUtil {

  private ExecutaSQL executasql;
  FormataDataBean dataFormatada = new FormataDataBean ();

  public Parcelamento_FinanceiroBD (ExecutaSQL sql) {
    super (sql);
    this.executasql = sql;
  }

  private void desdobramento (Parcelamento_FinanceiroED ed) throws Excecoes {

    double valor = 0 ,
        valorRestante = 0 ,
        nrParcelas = 0 ,
        resto = 0;
    try {
      //*** Busca a Lista de Parcelar anterior
       //    para dividir o valor restante da parcela entre elas
       ArrayList lista = lista (ed);
      if (lista.size () > 0) {
        nrParcelas = lista.size ();
        Nota_Fiscal_EletronicaED edNF = new Nota_Fiscal_EletronicaBD (this.executasql).getByRecord (new Nota_Fiscal_EletronicaED (ed.getOID_Nota_Fiscal ()));
        valor = (edNF.getVl_liquido_nota_fiscal () - ed.getVL_Parcela ());
        valorRestante = Valor.getValorArredondado (valor / nrParcelas);

        //*** Atualiza o valor das outras parcelas com o valor restante
         for (int i = 0; i < lista.size (); i++) {
           Parcelamento_FinanceiroED edParcela = (Parcelamento_FinanceiroED) lista.get (i);
           //*** Resto
            if (i == 0) {
              resto = Valor.getValorArredondado (valor - (valorRestante * nrParcelas));
              edParcela.setVL_Parcela (valorRestante + resto);
            }
            else {
              edParcela.setVL_Parcela (valorRestante);
            }
           altera (edParcela);
         }
      }
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "desdobramento()");
    }
  }

  public void inclui (Parcelamento_FinanceiroED ed) throws Excecoes {

    String sql = null;
    try {
      //*** Calcula Desdobramento
       this.desdobramento (ed);

      //*** Incrementa oid
       ed.setOID_Parcelamento (getAutoIncremento ("oid_parcelamento" , "parcelamentos_financeiros"));

      dataFormatada.setDT_FormataData (ed.getDT_Pagamento ());
      ed.setDT_Pagamento (dataFormatada.getDT_FormataData ());

      sql = " INSERT INTO parcelamentos_financeiros (" +
          "		OID_Parcelamento, " +
          "		OID_Nota_Fiscal, " +
          "		nr_parcelamento, " +
          "		vl_parcela, " +
          "		vl_desconto, " +
          "		dt_pagamento, " +
          "		dt_stamp, " +
          "		usuario_stamp, " +
          "		dm_stamp, " +
          "		DM_Tipo_Pagamento) " +
          " VALUES ";
      sql += "(" + ed.getOID_Parcelamento () +
          ",'" + ed.getOID_Nota_Fiscal () +
          "'," + ed.getNR_Parcelamento () +
          "," + ed.getVL_Parcela () +
          "," + ed.getVL_Desconto () +
          ",'" + ed.getDT_Pagamento () +
          "','" + ed.getDt_stamp () +
          "','" + ed.getUsuario_Stamp () +
          "','" + ed.getDm_Stamp () +
          "','" + (doValida(ed.getDM_Tipo_Pagamento()) ? ed.getDM_Tipo_Pagamento() : "B" )+ "')";
      executasql.executarUpdate (sql);

    }
    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "inclui()");
    }
  }

  public String  altera (Parcelamento_FinanceiroED ed) throws Excecoes {

    String sql = null;
    String retorno="";
    try {

      Parcelamento_FinanceiroED edVolta = this.getByRecord_Compra(ed);
      dataFormatada.setDT_FormataData (ed.getDT_Pagamento ());
      ed.setDT_Pagamento (dataFormatada.getDT_FormataData ());

      if (edVolta.getOID_Compromisso() > 0) {
          sql = " SELECT Lotes_Compromissos.oid_Lote_Pagamento, Lotes_Pagamentos.DM_Situacao " +
			    " FROM   Lotes_Compromissos, Lotes_Pagamentos "  +
			    " WHERE  Lotes_Compromissos.oid_Lote_Pagamento = Lotes_Pagamentos.oid_Lote_Pagamento "  +
			     " AND   Lotes_Compromissos.oid_Compromisso=" + edVolta.getOID_Compromisso();
				ResultSet res = this.executasql.executarConsulta (sql);
				if (res.next ()) {
					retorno=" Parcelamento com Compromisso já Pago!";
				}
      }

      if (edVolta.getDT_Vencimento_Minimo()!=null && edVolta.getDT_Vencimento_Minimo().length()>4){
    	  if (Data.comparaData(edVolta.getDT_Vencimento_Minimo(), ed.getDT_Pagamento ())){
				retorno=" Data INFERIOR a Data Mínima de Vencimento!";
    	  }
      }


      if ("".equals(retorno)){
          sql = " UPDATE parcelamentos_financeiros SET " +
          "      vl_parcela = " + ed.getVL_Parcela () +
          "	    ,vl_desconto = " + ed.getVL_Desconto () +
          "	    ,dt_pagamento = '" + ed.getDT_Pagamento () + "'" +
          "	    ,dt_stamp = '" + Data.getDataDMY () + "'";
	      if (JavaUtil.doValida (ed.getDM_Tipo_Pagamento ())) {
	        sql += "    ,dm_tipo_pagamento = '" + ed.getDM_Tipo_Pagamento () + "'";
	      }
	      sql += " WHERE OID_Parcelamento = " + ed.getOID_Parcelamento ();
	      executasql.executarUpdate (sql);
      }

    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "altera()");
    }
    return retorno;
  }

  public void deleta (Parcelamento_FinanceiroED ed) throws Excecoes {

    String sql = null;
    try {
      //*** Primeiro verifico se existem ligaçs
       /*sql = " Select OID_Compromisso, OID_Parcelamento from parcelas_compromissos "+
          " Where OID_Parcelamento ="+ed.getOID_Parcelamento();
                 ResultSet res = executasql.executarConsulta(sql);

                while(res.next()){
          //*** Agora verifica se o compromisso vinculado nãestám lote de pagamento
            sql = "select * from compromissos c, parcelas_compromissos p ";
            sql +="where c.vl_saldo = c.vl_compromisso ";
            sql +="and c.nr_documento = '"+ed.getNR_Nota_Fiscal()+"' ";
            sql +="and c.oid_compromisso=p.oid_compromisso ";
            sql +="and p.OID_Parcelamento  ="+ res.getLong("OID_Parcelamento");
            sql +=" AND c.oid_compromisso = " + res.getLong("OID_Compromisso");

            ResultSet rs = executasql.executarConsulta(sql);
            while(rs.next()){
                sql = " DELETE from parcelas_compromissos "+
                    " WHERE oid_parcelamento = " + res.getLong("OID_Parcelamento")+
                    " AND oid_compromisso = " + res.getLong("OID_Compromisso");
                executasql.executarUpdate(sql);

                sql = "delete from Compromissos WHERE oid_Compromisso = ";
                sql +=res.getLong("OID_Compromisso");
                executasql.executarUpdate(sql);
            }
                  }*/
        sql = " DELETE from parcelamentos_financeiros " +
            " WHERE oid_parcelamento = " + ed.getOID_Parcelamento ();
      executasql.executarUpdate (sql);

      //*** Calcula Desdobramento
       this.desdobramento (ed);

    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "deleta()");
    }
  }

  public double checkSoma (String nota) throws Excecoes {

    String sql = null;
    double total = 0;

    try {
      sql = "Select sum(vl_porcentagem_parcela) as total from parcelamentos_financeiros Where OID_Nota_Fiscal ='" + nota + "'";
      ResultSet res = executasql.executarConsulta (sql);
      while (res.first ()) {
        total = res.getDouble ("total");
        break;
      }
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "checkSoma()");
    }
    return total;
  }

  //*** Valor TOTAL das parcelas da Nota Fiscal
   public double vlTotalParcelas (String oid_Nota_Fiscal) throws Excecoes {

     String sql = null;
     double total = 0;

     try {
       sql = " Select sum(vl_parcela) as total " +
           " from parcelamentos_financeiros " +
           " Where oid_Nota_Fiscal ='" + oid_Nota_Fiscal + "'";
       ResultSet res = executasql.executarConsulta (sql);
       while (res.next ()) {
         total = res.getDouble ("total");
       }
     }
     catch (Exception exc) {
       throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "vlTotalParcelas()");
     }
     return total;
   }

  public ArrayList lista (Parcelamento_FinanceiroED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    try {
      sql = " SELECT * " +
          " FROM Parcelamentos_Financeiros  " +
          " WHERE OID_Nota_Fiscal = '" + ed.getOID_Nota_Fiscal () + "'";
      sql += " ORDER BY nr_parcelamento ";

      ResultSet res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        Parcelamento_FinanceiroED edVolta = new Parcelamento_FinanceiroED ();
        edVolta.setOID_Parcelamento (res.getLong ("oid_parcelamento"));
        edVolta.setOID_Nota_Fiscal (res.getString ("oid_nota_fiscal"));
        edVolta.setDT_Pagamento (res.getString ("dt_pagamento"));
        dataFormatada.setDT_FormataData (edVolta.getDT_Pagamento ());
        edVolta.setDT_Pagamento (dataFormatada.getDT_FormataData ());
        edVolta.setNR_Parcelamento (res.getLong ("nr_parcelamento"));
        edVolta.setVL_Parcela (res.getDouble ("vl_parcela"));
        list.add (edVolta);
      }
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "lista()");
    }
    return list;
  }

  public Parcelamento_FinanceiroED getByRecord (Parcelamento_FinanceiroED ed) throws Excecoes {

    String sql = null;
    Parcelamento_FinanceiroED edVolta = new Parcelamento_FinanceiroED ();
    try {
      sql = " Select * " +
          " From  parcelamentos_financeiros, Notas_Fiscais " +
          " Where Parcelamentos_Financeiros.oid_nota_fiscal = Notas_Fiscais.oid_nota_fiscal ";

      if (ed.getOID_Parcelamento () > 0) {
        sql += " AND oid_parcelamento = '" + ed.getOID_Parcelamento () + "'";
      }
      ResultSet res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta.setOID_Parcelamento (res.getLong ("oid_parcelamento"));
        edVolta.setOID_Nota_Fiscal (res.getString ("oid_nota_fiscal"));
        edVolta.setDT_Pagamento (res.getString ("dt_pagamento"));
        dataFormatada.setDT_FormataData (edVolta.getDT_Pagamento ());
        edVolta.setDT_Pagamento (dataFormatada.getDT_FormataData ());

        edVolta.setDT_Vencimento_Minimo(res.getString ("dt_vencimento_minimo"));

        edVolta.setNR_Parcelamento (res.getLong ("nr_parcelamento"));
        edVolta.setNR_Nota_Fiscal (res.getString ("nr_nota_fiscal"));
        edVolta.setDM_Tipo_Pagamento (res.getString ("DM_Tipo_Pagamento"));
        edVolta.setDM_Situacao (res.getString ("DM_Situacao"));
        edVolta.setDM_Vencimento_Conta(res.getString ("dm_vencimento_conta"));
        edVolta.setVL_Bruto (res.getDouble ("vl_nota_fiscal"));
        edVolta.setVL_Parcela (res.getDouble ("vl_parcela"));
        edVolta.setVL_Desconto (res.getDouble ("vl_Desconto"));

        edVolta.setOID_Compromisso (0);
        sql =" SELECT oid_Compromisso " +
        	 " FROM   Parcelas_Compromissos " +
        	 " WHERE  oid_Parcelamento='" + ed.getOID_Parcelamento () + "'";
        ResultSet resCom = this.executasql.executarConsulta (sql);
        if (resCom.next ()) {
            edVolta.setOID_Compromisso (resCom.getLong ("oid_Compromisso"));
        }

      }
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "getByRecord()");
    }
    return edVolta;
  }

  public Parcelamento_FinanceiroED getByRecord_Compra (Parcelamento_FinanceiroED ed) throws Excecoes {

    String sql = null;
    Parcelamento_FinanceiroED edVolta = new Parcelamento_FinanceiroED ();

    try {
      sql = " Select * " +
          " From  parcelamentos_financeiros, Notas_Fiscais_transacoes " +
          " Where Parcelamentos_Financeiros.oid_nota_fiscal = Notas_Fiscais_transacoes.oid_nota_fiscal ";
      if (ed.getOID_Parcelamento () > 0) {
        sql += " AND oid_parcelamento = '" + ed.getOID_Parcelamento () + "'";
      }
// System.out.println(sql);
      ResultSet res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta.setOID_Parcelamento (res.getLong ("oid_parcelamento"));
        edVolta.setOID_Nota_Fiscal (res.getString ("oid_nota_fiscal"));
        edVolta.setDT_Pagamento (res.getString ("dt_pagamento"));
        dataFormatada.setDT_FormataData (edVolta.getDT_Pagamento ());
        edVolta.setDT_Pagamento (dataFormatada.getDT_FormataData ());

        edVolta.setDT_Vencimento_Minimo(res.getString ("dt_vencimento_minimo"));

        edVolta.setNR_Parcelamento (res.getLong ("nr_parcelamento"));
        edVolta.setNR_Nota_Fiscal (res.getString ("nr_nota_fiscal"));
        edVolta.setDM_Tipo_Pagamento (res.getString ("DM_Tipo_Pagamento"));
        edVolta.setDM_Situacao (res.getString ("DM_Situacao"));
        edVolta.setDM_Vencimento_Conta(res.getString ("dm_vencimento_conta"));

        edVolta.setVL_Bruto (res.getDouble ("vl_nota_fiscal"));
        edVolta.setVL_Parcela (res.getDouble ("vl_parcela"));
        edVolta.setVL_Desconto (res.getDouble ("vl_Desconto"));

        edVolta.setOID_Compromisso (0);
        sql =" SELECT oid_Compromisso " +
        	 " FROM   Parcelas_Compromissos " +
        	 " WHERE  oid_Parcelamento='" + ed.getOID_Parcelamento () + "'";
        ResultSet resCom = this.executasql.executarConsulta (sql);
        if (resCom.next ()) {
            edVolta.setOID_Compromisso (resCom.getLong ("oid_Compromisso"));
        }

      }
    }
    catch (Exception exc) {
    	exc.printStackTrace();
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "getByRecord_Compra()");
    }
    return edVolta;
  }
}