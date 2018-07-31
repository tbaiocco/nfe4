package com.master.bd;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import com.master.util.*;
import com.master.util.bd.*;
import com.master.rl.*;
import com.master.ed.Item_Nota_Fiscal_CompraED;
import java.util.*;
import java.sql.*;
import com.master.root.FormataDataBean;

public class Item_Nota_Fiscal_CompraBD {

  private ExecutaSQL executasql;

  public Item_Nota_Fiscal_CompraBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public void inclui(Item_Nota_Fiscal_CompraED ed) throws Excecoes{

    String sql = null;
    long valOid = 0;
    String chave = null;
    String DM_Impresso = null;

    try{

sql = "select max(NR_Item_Nota_Fiscal) as result from Itens_Notas_Fiscais_Transacoes where oid_Nota_Fiscal = '" + ed.getOID_Nota_Fiscal() + "'";
////////// // System.out.println(sql);
      ResultSet rs = executasql.executarConsulta(sql);

      //pega proximo valor da chave
      while (rs.next()){
        valOid = rs.getLong("result");
        ed.setNR_Item_Nota_Fiscal(++valOid);
      }

      chave = (String.valueOf(ed.getOID_Nota_Fiscal()) + String.valueOf(ed.getNR_Item_Nota_Fiscal()));

      sql = " insert into Itens_Notas_Fiscais_Transacoes (OID_Item_Nota_Fiscal, CD_Imobiliz, oid_estoque, OID_Nota_Fiscal, NR_Volumes, VL_Produto, NR_Item_Nota_Fiscal, VL_IPI, VL_Liquido, VL_Desconto, oid_Unidade_Produto, oid_Ordem_Servico, oid_solicitacao_compra ) values ";
      sql += "('" + chave + "','" + ed.getCD_Imobiliz() + "','" + ed.getOID_Produto() + "','" + ed.getOID_Nota_Fiscal() + "',"  + ed.getVl_quantidade() + "," + ed.getVL_Produto() + "," + ed.getNR_Item_Nota_Fiscal() + "," + ed.getVL_IPI() + "," + ed.getVL_Liquido() + "," + ed.getVL_Desconto() + "," + ed.getOID_Unidade_Produto() + "," + ed.getOid_Ordem_Servico() + "," + ed.getOid_Solicitacao_compra() + ")";
System.out.println(sql);
      int i = executasql.executarUpdate(sql);
    }
    catch (Exception exc) {
      exc.printStackTrace ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao incluir. " + exc.getMessage ());
      excecoes.setMetodo ("inclui");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void altera(Item_Nota_Fiscal_CompraED ed) throws Excecoes{

    String sql = null;

    try{
      sql  = " update Itens_Notas_Fiscais_Transacoes set NR_Volumes= " + ed.getVl_quantidade() +
      ", VL_Produto= " + ed.getVL_Produto() +
      ", VL_Desconto= " + ed.getVL_Desconto() +
      ", VL_Liquido= " + ed.getVL_Liquido() +
      ", VL_IPI= " + ed.getVL_IPI() +
      ", oid_Estoque = '" + ed.getOID_Produto() +"'"+
      ", NR_Item_Nota_Fiscal = " + ed.getNR_Item_Nota_Fiscal() +
      ", Oid_Unidade_Produto = " + ed.getOID_Unidade_Produto() +
      ", Oid_Ordem_Servico = " + ed.getOid_Ordem_Servico() +
      ", CD_Imobiliz= '" + ed.getCD_Imobiliz() + "'";
      sql += " where oid_Item_Nota_Fiscal = '" + ed.getOID_Item_Nota_Fiscal() + "'";
////////// // System.out.println(sql);
      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar");
      excecoes.setMetodo("alterar");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void deleta(Item_Nota_Fiscal_CompraED ed) throws Excecoes{

    String sql = null;
    String DM_Impresso = null;

    try{


      sql =" SELECT oid_Nota_Fiscal, oid_Estoque FROM Itens_Notas_Fiscais_Transacoes WHERE oid_Item_Nota_Fiscal = ";
      sql += "('" + ed.getOID_Item_Nota_Fiscal() + "')";
      System.out.println(sql);
      ResultSet res = this.executasql.executarConsulta(sql);
      if (res.next()){


          sql = "DELETE from Movimentos_Estoques WHERE oid_Nota_Fiscal = '" +  res.getString("oid_Nota_Fiscal") + "'" +
          		"                                AND   oid_Estoque='" + res.getString("oid_Estoque") + "'";
          System.out.println(sql);

          executasql.executarUpdate(sql);

    	  sql = "DELETE from Itens_Notas_Fiscais_Transacoes WHERE oid_Item_Nota_Fiscal = ";
          sql += "('" + ed.getOID_Item_Nota_Fiscal() + "')";
          System.out.println(sql);

          executasql.executarUpdate(sql);

      }




    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      if (DM_Impresso != null && DM_Impresso.equals("S")){
        excecoes.setMensagem("Conhecimento já impresso com essa nota fiscal - exclusão de itens não permitida");
      }else{
        excecoes.setMensagem("Erro ao incluir");
      }
      excecoes.setMetodo("excluir");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public ArrayList lista(Item_Nota_Fiscal_CompraED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();

    try{
        sql = "SELECT  Itens_Notas_Fiscais_Transacoes.OID_Nota_Fiscal, " +
  		"        Itens_Notas_Fiscais_Transacoes.Oid_Solicitacao_Compra, " +
  		"        Itens_Notas_Fiscais_Transacoes.VL_IPI, " +
  		"        Itens_Notas_Fiscais_Transacoes.VL_Liquido, " +
  		"        Itens_Notas_Fiscais_Transacoes.cd_imobiliz, " +
  		"        Itens_Notas_Fiscais_Transacoes.NR_Item_Nota_Fiscal, " +
  		"        Itens_Notas_Fiscais_Transacoes.OID_Item_Nota_Fiscal, " +
  		"        Itens_Notas_Fiscais_Transacoes.VL_Desconto, " +
  		"        Itens_Notas_Fiscais_Transacoes.Oid_Ordem_Servico,  "+
        "        Itens_Notas_Fiscais_Transacoes.NR_Volumes, " +
        "        Itens_Notas_Fiscais_Transacoes.VL_Produto, " +
        "        est.oid_estoque, " +
        "        est.cd_estoque, " +
        "        est.nm_estoque, " +
        "        est.DM_Tipo_Produto, " +
        "        un.cd_unidade_produto "+
        "FROM  Itens_Notas_Fiscais_Transacoes, estoques est, unidades_produtos un "+
        "WHERE Itens_Notas_Fiscais_Transacoes.oid_estoque = est.oid_estoque "+
        " AND  est.oid_unidade_produto = un.oid_unidade_produto";

      if (String.valueOf(ed.getOID_Nota_Fiscal()) != null &&
          !String.valueOf(ed.getOID_Nota_Fiscal()).equals("") &&
          !String.valueOf(ed.getOID_Nota_Fiscal()).equals("null")){
        sql += " and Itens_Notas_Fiscais_Transacoes.OID_Nota_Fiscal = '" + ed.getOID_Nota_Fiscal() + "'";
      }
      if (String.valueOf(ed.getOID_Item_Nota_Fiscal()) != null &&
          !String.valueOf(ed.getOID_Item_Nota_Fiscal()).equals("") &&
          !String.valueOf(ed.getOID_Item_Nota_Fiscal()).equals("null")){
        sql += " and Itens_Notas_Fiscais_Transacoes.OID_Item_Nota_Fiscal = '" + ed.getOID_Item_Nota_Fiscal() + "'";
      }

      sql += " Order by Itens_Notas_Fiscais_Transacoes.NR_Item_Nota_fiscal ";

//System.out.println(sql);
      ResultSet res = this.executasql.executarConsulta(sql);
      double valor = 0;
      //popula
      while (res.next()){
        Item_Nota_Fiscal_CompraED edVolta = new Item_Nota_Fiscal_CompraED();

        edVolta.setOID_Item_Nota_Fiscal(res.getString("oid_item_nota_fiscal"));
        //edVolta.setNR_Volumes(res.getLong("nr_volumes"));
        edVolta.setOid_Solicitacao_compra(res.getLong("Oid_Solicitacao_Compra"));
        edVolta.setVl_quantidade(res.getDouble("nr_volumes"));
        edVolta.setDM_Tipo_Produto(res.getString("DM_Tipo_Produto"));
        edVolta.setVL_Produto(res.getDouble("vl_produto"));
        edVolta.setVL_Liquido(res.getDouble("vl_liquido"));
        edVolta.setVL_IPI(res.getDouble("vl_ipi"));
        edVolta.setNM_Produto(res.getString("nm_estoque"));
        edVolta.setOID_Produto(res.getString("oid_estoque"));
        edVolta.setNR_Item_Nota_Fiscal(res.getLong("nr_item_nota_fiscal"));
        edVolta.setOID_Nota_Fiscal(res.getString("oid_nota_fiscal"));
        edVolta.setCD_Referencia(res.getString("cd_estoque"));
        edVolta.setCD_Imobiliz(res.getString("cd_imobiliz"));
        edVolta.setNM_Unidade(res.getString("cd_unidade_produto"));
        if(res.getString("vl_desconto")!=null)
          valor = Double.parseDouble(res.getString("vl_desconto"));
        else valor = 0;
        edVolta.setVL_Desconto(valor);

        edVolta.setNR_Ordem_Servico(" ");
        edVolta.setNR_Placa(" ");
        if (res.getLong("oid_ordem_servico")>0) {
        	sql =" SELECT oid_ordem_servico, NR_Ordem_Servico, oid_Veiculo FROM Ordens_Servicos WHERE oid_Ordem_Servico=" + res.getLong("oid_ordem_servico");
            ResultSet resOS = this.executasql.executarConsulta(sql);
        	System.out.println(sql);
            if (resOS.next()){
            	edVolta.setOid_Ordem_Servico(resOS.getLong("oid_ordem_servico"));
            	edVolta.setNR_Ordem_Servico(resOS.getString("NR_Ordem_Servico"));
            	edVolta.setNR_Placa(resOS.getString("oid_Veiculo"));
            }
        }


        list.add(edVolta);
      }
    }
    catch(Exception exc){
        exc.printStackTrace();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao listar");
      excecoes.setMetodo("listar");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return list;
  }

  public Item_Nota_Fiscal_CompraED getByRecord(Item_Nota_Fiscal_CompraED ed)throws Excecoes{

    String sql = null;

    Item_Nota_Fiscal_CompraED edVolta = new Item_Nota_Fiscal_CompraED();

    try{
      sql =  " SELECT Itens_Notas_Fiscais_Transacoes.CD_Imobiliz, Itens_Notas_Fiscais_Transacoes.VL_IPI, Itens_Notas_Fiscais_Transacoes.VL_Liquido, Itens_Notas_Fiscais_Transacoes.OID_Unidade_Produto, Itens_Notas_Fiscais_Transacoes.NR_Item_Nota_Fiscal, Itens_Notas_Fiscais_Transacoes.OID_Item_Nota_Fiscal, Itens_Notas_Fiscais_Transacoes.NR_Volumes, Itens_Notas_Fiscais_Transacoes.oid_ordem_servico, ";
      sql += "Itens_Notas_Fiscais_Transacoes.VL_Produto, Itens_Notas_Fiscais_Transacoes.oid_estoque, Itens_Notas_Fiscais_Transacoes.VL_Desconto, Notas_Fiscais_Transacoes.NR_Nota_Fiscal, Notas_Fiscais_Transacoes.OID_Nota_Fiscal from Itens_Notas_Fiscais_Transacoes, Notas_Fiscais_Transacoes ";
      sql += " WHERE Notas_Fiscais_Transacoes.oid_nota_fiscal= Itens_Notas_Fiscais_Transacoes.OID_Nota_Fiscal ";

      if (String.valueOf(ed.getOID_Item_Nota_Fiscal()) != null &&
          !String.valueOf(ed.getOID_Item_Nota_Fiscal()).equals("") &&
          !String.valueOf(ed.getOID_Item_Nota_Fiscal()).equals("null")){
        sql += " AND Itens_Notas_Fiscais_Transacoes.OID_Item_Nota_Fiscal = '" + ed.getOID_Item_Nota_Fiscal() + "'";
      }

      if (String.valueOf(ed.getOID_Nota_Fiscal()) != null &&
          !String.valueOf(ed.getOID_Nota_Fiscal()).equals("") &&
          !String.valueOf(ed.getOID_Nota_Fiscal()).equals("null")){
        sql += " AND Itens_Notas_Fiscais_Transacoes.OID_Nota_Fiscal = '" + ed.getOID_Nota_Fiscal() + "'";
      }

      if (String.valueOf(ed.getCD_Imobiliz()) != null &&
          !String.valueOf(ed.getCD_Imobiliz()).equals("") &&
          !String.valueOf(ed.getCD_Imobiliz()).equals("null")){
        sql += " AND Itens_Notas_Fiscais_Transacoes.CD_Imobiliz = '" + ed.getCD_Imobiliz() + "'";
      }

      if (String.valueOf(ed.getNR_Nota_Fiscal()) != null &&
          !String.valueOf(ed.getNR_Nota_Fiscal()).equals("") &&
          !String.valueOf(ed.getNR_Nota_Fiscal()).equals("0")){
        sql += " and Notas_Fiscais_Transacoes.NR_Nota_Fiscal = " + ed.getNR_Nota_Fiscal();
        sql += " and Notas_Fiscais_Transacoes.NM_Serie = '" + ed.getNM_Serie() + "'";
      }
 System.out.println("SQL:"+sql);
      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);
      while (res.next()){
        edVolta.setOID_Item_Nota_Fiscal(res.getString("oid_item_nota_fiscal"));
        //edVolta.setNR_Volumes(res.getLong("nr_volumes"));
        edVolta.setVl_quantidade(res.getDouble("nr_volumes"));
        edVolta.setVL_Produto(res.getDouble("vl_produto"));
        edVolta.setVL_Desconto(res.getDouble("vl_desconto"));
        edVolta.setNR_Nota_Fiscal(res.getLong("nr_nota_fiscal"));
        edVolta.setVL_Liquido(res.getDouble("vl_liquido"));
        edVolta.setVL_IPI(res.getDouble("vl_ipi"));
        edVolta.setNR_Item_Nota_Fiscal(res.getLong("nr_item_nota_fiscal"));
        edVolta.setOID_Nota_Fiscal(res.getString("OID_Nota_Fiscal"));
        edVolta.setOID_Produto(res.getString("oid_estoque"));
        edVolta.setCD_Imobiliz(res.getString("cd_imobiliz"));
        edVolta.setOID_Unidade_Produto(res.getString("oid_unidade_produto"));

        edVolta.setNR_Ordem_Servico(" ");
        edVolta.setNR_Placa(" ");
        if (res.getLong("oid_ordem_servico")>0) {
        	sql =" SELECT oid_ordem_servico, NR_Ordem_Servico, oid_Veiculo FROM Ordens_Servicos WHERE oid_Ordem_Servico=" + res.getLong("oid_ordem_servico");
            ResultSet resOS = this.executasql.executarConsulta(sql);
        	System.out.println(sql);
            if (resOS.next()){
            	edVolta.setOid_Ordem_Servico(resOS.getLong("oid_ordem_servico"));
            	edVolta.setNR_Ordem_Servico(resOS.getString("NR_Ordem_Servico"));
            	edVolta.setNR_Placa(resOS.getString("oid_Veiculo"));
            }
        }


//        sql =  " SELECT NM_estoque, cd_estoque from estoques ";
//        sql += " WHERE estoques.OID_estoque = '" + res.getString("oid_estoque")+ "'";
//
//        ResultSet resTP = null;
//        resTP = this.executasql.executarConsulta(sql);
//        while (resTP.next()){
//          edVolta.setNM_Produto(resTP.getString("NM_estoque"));
//        }

      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao selecionar");
      excecoes.setMetodo("selecionar");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return edVolta;
  }


}