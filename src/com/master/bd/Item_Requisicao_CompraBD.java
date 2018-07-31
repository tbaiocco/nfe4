package com.master.bd;

/**
 * Título: Item_Requisicao_CompraBD
 * Descrição: Itens da Requisicao de Materiais - BD
 * Data da criação: 11/2004
 * Atualizado em: 11/2004
 * Empresa: ÊxitoLogística Mastercom
 * Autor: Teofilo Poletto Baiocco
*/

import com.master.util.*;
import com.master.util.bd.*;
import com.master.rn.Requisicao_CompraRN;
import com.master.ed.Requisicao_CompraED;
import java.util.*;
import java.sql.*;
import com.master.root.FormataDataBean;

public class Item_Requisicao_CompraBD {

  private ExecutaSQL executasql;

  public Item_Requisicao_CompraBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public Requisicao_CompraED inclui(Requisicao_CompraED ed) throws Excecoes{

    String sql = null;
    long valOid = 0;
    Requisicao_CompraED Requisicao_CompraED = new Requisicao_CompraED();
    Requisicao_CompraRN Requisicao_CompraRN = new Requisicao_CompraRN();

    try{

      String oid = String.valueOf(System.currentTimeMillis()).toString() + String.valueOf(ed.getOid_requisicao_compra());

      valOid = new Long(oid.substring(oid.length() - 11, oid.length())).longValue();

      sql = "INSERT INTO itens_Requisicoes_Compras (" +
      		" oid_item_requisicao_compra,"+
      		" oid_requisicao_compra,"+
			" oid_estoque,"+
			" vl_quantidade,"+
			" nm_observacoes,"+
			" dt_stamp,"+
			" usuario_stamp,"+
			" dm_stamp,"+
			" nr_prazo_entrega,"+
			" nr_contrato,"+
			" dm_status"+
      		")";
      sql+= " values ( ";
      sql+= valOid + "," +
      		ed.getOid_requisicao_compra() + ","+
      		ed.getOid_estoque() + ","+
      		ed.getVl_quantidade() + ",'" +
      		ed.getNm_observacoes() + "','" +
      		ed.getDt_stamp() + "','','S'," +
      		ed.getNr_prazo_entrega() + ",'" +
      		ed.getNr_contrato() + "','" +
      		ed.getDm_status_item() + "')";

      int i = executasql.executarUpdate(sql);
      Requisicao_CompraED.setOid_item_requisicao_compra(valOid);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      exc.printStackTrace();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir!");
      excecoes.setMetodo("inclui()");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return Requisicao_CompraED;

  }

  public void altera(Requisicao_CompraED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "UPDATE itens_Requisicoes_Compras set " +
			" oid_estoque="+ ed.getOid_estoque() +
			", vl_quantidade="+ ed.getVl_quantidade() +
			", nm_observacoes='"+ ed.getNm_observacoes() +
			"', dt_stamp='"+ Data.getDataDMY()+
			"', nr_prazo_entrega="+ ed.getNr_prazo_entrega() +
			", nr_contrato='"+ ed.getNr_contrato() +
			"', dt_entrega='"+ ed.getDt_entrega() +"'";
      sql += " WHERE oid_item_requisicao_compra = " + ed.getOid_item_requisicao_compra();

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar dados do Ítem");
      excecoes.setMetodo("altera()");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void deleta(Requisicao_CompraED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from itens_requisicoes_compras WHERE Oid_item_Requisicao_Compra = ";
      sql += "" + ed.getOid_item_requisicao_compra() + "";

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao deletar Ítem");
      excecoes.setMetodo("deleta()");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void finaliza(Requisicao_CompraED ed) throws Excecoes{

      String sql = null;
      try{

        sql = "update itens_requisicoes_compras set dm_status='F' WHERE Oid_item_Requisicao_Compra = ";
        sql += "" + ed.getOid_item_requisicao_compra() + "";

        int i = executasql.executarUpdate(sql);
      }

      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao Finalizar Ítem");
        excecoes.setMetodo("finaliza()");
        excecoes.setExc(exc);
        throw excecoes;
      }
    }

  public void entrega(Requisicao_CompraED ed) throws Excecoes{

      String sql = null;
      try{

        sql = "update itens_requisicoes_compras set dt_entrega='" + ed.getDt_entrega() + "' WHERE Oid_item_Requisicao_Compra = ";
        sql += "" + ed.getOid_item_requisicao_compra() + "";

        int i = executasql.executarUpdate(sql);
      }

      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao Entregar Ítem");
        excecoes.setMetodo("entrega()");
        excecoes.setExc(exc);
        throw excecoes;
      }
    }

  public Requisicao_CompraED getByRecord(Requisicao_CompraED ed)throws Excecoes{

    String sql = null;
    FormataDataBean dataFormatada = new FormataDataBean();
    Requisicao_CompraED edVolta = new Requisicao_CompraED();

    try{

      sql = " select * from itens_requisicoes_compras";
      sql += " where 1 = 1 ";
      if (String.valueOf(ed.getOid_item_requisicao_compra()) != null &&
         !String.valueOf(ed.getOid_item_requisicao_compra()).equals("null") &&
         !String.valueOf(ed.getOid_item_requisicao_compra()).equals("0"))
          sql += " and oid_item_requisicao_compra = " + ed.getOid_item_requisicao_compra();

      ResultSet res = null;

      res = this.executasql.executarConsulta(sql);
      while (res.next()){
        edVolta = new Requisicao_CompraED();
        edVolta.setOid_requisicao_compra(res.getLong("Oid_requisicao_compra"));
        edVolta.setOid_item_requisicao_compra(res.getLong("Oid_item_requisicao_compra"));
        edVolta.setOid_estoque(res.getLong("Oid_estoque"));
  		edVolta.setVl_quantidade(res.getDouble("vl_quantidade"));
  		edVolta.setVl_quantidade_entrega(res.getDouble("vl_quantidade_entrega"));
  		edVolta.setNm_observacoes(res.getString("nm_observacoes"));
  		edVolta.setDt_stamp(res.getString("Dt_stamp"));
  		edVolta.setNr_prazo_entrega(res.getLong("Nr_prazo_entrega"));
  		edVolta.setNr_contrato(res.getString("nr_contrato"));
  		edVolta.setDt_entrega(res.getString("Dt_entrega"));
  		dataFormatada.setDT_FormataData(edVolta.getDt_entrega());
        edVolta.setDt_entrega(dataFormatada.getDT_FormataData());
  		edVolta.setDm_status_item(res.getString("Dm_status"));
  		if (edVolta.getDm_status_item() != null &&
  		    edVolta.getDm_status_item().equals("P")) edVolta.setDm_status_item("Pendente");
  		if (edVolta.getDm_status_item() != null &&
  	  		edVolta.getDm_status_item().equals("C")) edVolta.setDm_status_item("Cancelado");
  		if (edVolta.getDm_status_item() != null &&
  	  		edVolta.getDm_status_item().equals("F")) edVolta.setDm_status_item("Finalizado");
  		if (edVolta.getDm_status_item() != null &&
  	  	  		edVolta.getDm_status_item().equals("E")) edVolta.setDm_status_item("Entregue");
      }

    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao recuperar registro");
      excecoes.setMetodo("getByRecord()");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return edVolta;
  }

    public ArrayList Lista(Requisicao_CompraED ed)throws Excecoes{

    String sql = null;
    FormataDataBean dataFormatada = new FormataDataBean();
    ArrayList list = new ArrayList();
    Requisicao_CompraED edVolta = new Requisicao_CompraED();

    try{

        sql = " select * from itens_requisicoes_compras";
        sql += " where 1 = 1 ";
        if (String.valueOf(ed.getOid_item_requisicao_compra()) != null &&
           !String.valueOf(ed.getOid_item_requisicao_compra()).equals("null") &&
           !String.valueOf(ed.getOid_item_requisicao_compra()).equals("0"))
            sql += " and oid_item_requisicao_compra = " + ed.getOid_item_requisicao_compra();

        if (String.valueOf(ed.getOid_unidade()) != null &&
           !String.valueOf(ed.getOid_unidade()).equals("null") &&
           !String.valueOf(ed.getOid_unidade()).equals("0"))
           sql += " and oid_unidade = " + ed.getOid_unidade();
        if (String.valueOf(ed.getOid_usuario()) != null &&
           !String.valueOf(ed.getOid_usuario()).equals("null") &&
           !String.valueOf(ed.getOid_usuario()).equals("0"))
           sql += " and oid_usuario = " + ed.getOid_usuario();
        if (String.valueOf(ed.getOid_autorizador()) != null &&
           !String.valueOf(ed.getOid_autorizador()).equals("null") &&
           !String.valueOf(ed.getOid_autorizador()).equals("0"))
           sql += " and oid_autorizador = " + ed.getOid_autorizador();
        if (String.valueOf(ed.getDt_requisicao()) != null &&
           !String.valueOf(ed.getDt_requisicao()).equals("null") &&
           !String.valueOf(ed.getDt_requisicao()).equals(""))
           sql += " and Dt_requisicao = " + ed.getDt_requisicao();
        if (String.valueOf(ed.getDm_status_requisicao()) != null &&
           !String.valueOf(ed.getDm_status_requisicao()).equals("null") &&
           !String.valueOf(ed.getDm_status_requisicao()).equals(""))
           sql += " and Dm_status_requisicao = " + ed.getDm_status_requisicao();

        ResultSet res = null;

        res = this.executasql.executarConsulta(sql);
        while (res.next()){
            edVolta = new Requisicao_CompraED();
            edVolta.setOid_requisicao_compra(res.getLong("Oid_requisicao_compra"));
            edVolta.setOid_item_requisicao_compra(res.getLong("Oid_item_requisicao_compra"));
            edVolta.setOid_estoque(res.getLong("Oid_estoque"));
      		edVolta.setVl_quantidade(res.getDouble("vl_quantidade"));
      		edVolta.setVl_quantidade_entrega(res.getDouble("vl_quantidade_entrega"));
      		edVolta.setNm_observacoes(res.getString("nm_observacoes"));
      		edVolta.setDt_stamp(res.getString("Dt_stamp"));
      		edVolta.setNr_prazo_entrega(res.getLong("Nr_prazo_entrega"));
      		edVolta.setNr_contrato(res.getString("nr_contrato"));
      		edVolta.setDt_entrega(res.getString("Dt_entrega"));
      		dataFormatada.setDT_FormataData(edVolta.getDt_entrega());
            edVolta.setDt_entrega(dataFormatada.getDT_FormataData());
            edVolta.setDm_status_item(res.getString("Dm_status"));
      		if (edVolta.getDm_status_item() != null &&
      		    edVolta.getDm_status_item().equals("P")) edVolta.setDm_status_item("Pendente");
      		if (edVolta.getDm_status_item() != null &&
      	  		edVolta.getDm_status_item().equals("C")) edVolta.setDm_status_item("Cancelado");
      		if (edVolta.getDm_status_item() != null &&
      	  		edVolta.getDm_status_item().equals("F")) edVolta.setDm_status_item("Finalizado");
      		if (edVolta.getDm_status_item() != null &&
      	  	  		edVolta.getDm_status_item().equals("E")) edVolta.setDm_status_item("Entregue");
    		list.add(edVolta);
      }

    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao recuperar registro");
      excecoes.setMetodo("getByRecord()");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return list;
  }

    public void altera_Qtde_Entrega(Requisicao_CompraED ed) throws Excecoes{

        String sql = null;

        try{

          sql = "UPDATE itens_Requisicoes_Compras set " +
    			"  vl_quantidade="+ ed.getVl_quantidade_entrega() +
                " ,vl_quantidade_entrega="+ ed.getVl_quantidade_entrega() +
                " WHERE oid_item_requisicao_compra = " + ed.getOid_item_requisicao_compra();

          int i = executasql.executarUpdate(sql);
        }

        catch(Exception exc){
          Excecoes excecoes = new Excecoes();
          excecoes.setClasse(this.getClass().getName());
          excecoes.setMensagem("Erro ao alterar dados do Ítem");
          excecoes.setMetodo("altera_Qtde_Entrega()");
          excecoes.setExc(exc);
          throw excecoes;
        }
      }

}
