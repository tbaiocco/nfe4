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
import java.util.ArrayList;

import com.master.ed.Item_Nota_FiscalED;
import com.master.rl.Item_Nota_FiscalRL;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.bd.ExecutaSQL;

public class Item_Nota_FiscalBD {

  private ExecutaSQL executasql;

  public Item_Nota_FiscalBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public void inclui(Item_Nota_FiscalED ed) throws Excecoes{

    String sql = null;
    long valOid = 0;
    String chave = null;
    String DM_Impresso = null;

    try{

      sql = " SELECT Conhecimentos.DM_Impresso FROM "+
            " Notas_Fiscais, "+
            " Conhecimentos_Notas_Fiscais, Conhecimentos "+
            " WHERE Conhecimentos_Notas_Fiscais.OID_Nota_Fiscal = Notas_Fiscais.OID_Nota_Fiscal "+
            " AND Conhecimentos_Notas_Fiscais.OID_Conhecimento = Conhecimentos.OID_Conhecimento "+
            " AND Notas_Fiscais.OID_Nota_Fiscal = '" + ed.getOID_Nota_Fiscal() + "'";

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);
      while (res.next()){
        DM_Impresso = res.getString("DM_Impresso");
      }

      if (DM_Impresso != null && DM_Impresso.equals("S")){
        Excecoes exc = new Excecoes();
        throw exc;
      }

      ResultSet rs = executasql.executarConsulta("select max(NR_Item_Nota_Fiscal) as result from Itens_Notas_Fiscais where oid_Nota_Fiscal = '" + ed.getOID_Nota_Fiscal() + "'");

      //pega proximo valor da chave
      while (rs.next()){
        valOid = rs.getLong("result");
        ed.setNR_Item_Nota_Fiscal(++valOid);
      }

      chave = (ed.getOID_Nota_Fiscal() + ed.getNR_Item_Nota_Fiscal());

      sql = " insert into Itens_Notas_Fiscais (OID_Item_Nota_Fiscal, CD_Chassis_Serie, CD_Referencia, OID_Nota_Fiscal, NR_Volumes, VL_Produto, NR_Item_Nota_Fiscal, NM_Transporte, DM_Embalagem, VL_Unitario ) values ";
      sql += "('" + chave + "','" + ed.getCD_Chassis_Serie() + "','" + ed.getCD_Referencia() + "','" + ed.getOID_Nota_Fiscal() + "',"  + ed.getNR_Volumes() + "," + ed.getVL_Produto() + "," + ed.getNR_Item_Nota_Fiscal() + ",'" + ed.getNM_Transporte() + "','" + ed.getDM_Embalagem() + "'," + ed.getVL_Unitario() + " )";
      
      // System.out.println(sql);
      
      int i = executasql.executarUpdate(sql);
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      if (DM_Impresso != null && DM_Impresso.equals("S")){
        excecoes.setMensagem("Conhecimento já impresso com essa nota fiscal - inclusão de itens não permitida");
      }else{
        excecoes.setMensagem("Erro ao incluir");
      }
      excecoes.setMetodo("inclui");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void altera(Item_Nota_FiscalED ed) throws Excecoes{

    String sql = null;

    try{

      sql  = " update Itens_Notas_Fiscais set NR_Volumes= " + ed.getNR_Volumes() + ", VL_Produto= " + ed.getVL_Produto()+ ", VL_Unitario= " + ed.getVL_Unitario()  + ", CD_Chassis_Serie= '" + ed.getCD_Chassis_Serie()+ "', NM_Transporte= '" + ed.getNM_Transporte() + "', DM_Embalagem= '" + ed.getDM_Embalagem() + "'";
      sql += " where oid_Item_Nota_Fiscal = '" + ed.getOID_Item_Nota_Fiscal() + "'";

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

  public void deleta(Item_Nota_FiscalED ed) throws Excecoes{

    String sql = null;
    String DM_Impresso = null;

    try{

      sql = " SELECT Conhecimentos.DM_Impresso FROM "+
            " Notas_Fiscais, "+
            " Conhecimentos_Notas_Fiscais, Conhecimentos "+
            " WHERE Conhecimentos_Notas_Fiscais.OID_Nota_Fiscal = Notas_Fiscais.OID_Nota_Fiscal "+
            " AND Conhecimentos_Notas_Fiscais.OID_Conhecimento = Conhecimentos.OID_Conhecimento "+
            " AND Notas_Fiscais.OID_Nota_Fiscal = '" + ed.getOID_Nota_Fiscal() + "'";

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);
      while (res.next()){
        DM_Impresso = res.getString("DM_Impresso");
      }

      if (DM_Impresso != null && DM_Impresso.equals("S")){
        Excecoes exc = new Excecoes();
        throw exc;
      }


      sql = "delete from Itens_Notas_Fiscais WHERE oid_Item_Nota_Fiscal = ";
      sql += "('" + ed.getOID_Item_Nota_Fiscal() + "')";

      int i = executasql.executarUpdate(sql);
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

  public ArrayList lista(Item_Nota_FiscalED ed)
  throws Excecoes{
  	ArrayList list = new ArrayList();
  	
  	String sql =
  		" SELECT Itens_Notas_Fiscais.CD_Chassis_Serie, " +
		"        Itens_Notas_Fiscais.NR_Item_Nota_Fiscal, " +
		"        Itens_Notas_Fiscais.OID_Item_Nota_Fiscal, " +
		"        Itens_Notas_Fiscais.NR_Volumes, " +
		"        Itens_Notas_Fiscais.VL_Produto, " +
		"        Produtos.NM_Produto, " +
		"        Notas_Fiscais.NR_Nota_Fiscal, " +
		"        Notas_Fiscais.OID_Nota_Fiscal, " +
		"        Referencias.CD_Referencia, " +
		"        Referencias.NM_Referencia " +
		" from Itens_Notas_Fiscais, " +
		"      Produtos, " +
		"      Notas_Fiscais, " +
		"      Referencias " +
		" WHERE Notas_Fiscais.oid_Nota_Fiscal = Itens_Notas_Fiscais.oid_Nota_Fiscal " +
		"   AND Itens_Notas_Fiscais.CD_Referencia = Referencias.CD_Referencia " +
		"   AND Referencias.OID_Produto = Produtos.OID_Produto ";
  	if (JavaUtil.doValida(ed.getOID_Nota_Fiscal())) {
  		sql += " and Notas_Fiscais.OID_Nota_Fiscal = '" + ed.getOID_Nota_Fiscal() + "'";
  	}
  	sql += " Order by Itens_Notas_Fiscais.NR_Item_Nota_fiscal ";
  	ResultSet res = executasql.executarConsulta(sql);
  	try {
		//popula
		while (res.next()){
			Item_Nota_FiscalED edVolta = new Item_Nota_FiscalED();
			
			edVolta.setOID_Item_Nota_Fiscal(res.getString("OID_Item_Nota_Fiscal"));
			edVolta.setNR_Volumes(res.getLong("NR_Volumes"));
			edVolta.setVL_Produto(res.getDouble("VL_Produto"));
			edVolta.setNR_Nota_Fiscal(res.getLong("NR_Nota_Fiscal"));
			edVolta.setNM_Produto(res.getString("NM_Referencia") == null ? res.getString("NM_Produto") : res.getString("NM_Referencia"));
			edVolta.setNR_Item_Nota_Fiscal(res.getLong("NR_Item_Nota_Fiscal"));
			edVolta.setOID_Nota_Fiscal(res.getString("OID_Nota_Fiscal"));
			edVolta.setCD_Referencia(res.getString("CD_Referencia"));
			edVolta.setCD_Chassis_Serie(res.getString("CD_Chassis_Serie"));
			
			list.add(edVolta);
		}
		return list;
	} catch (SQLException e) {
		throw new Excecoes(e.getMessage(), e, getClass().getName(), "lista(Item_Nota_FiscalED ed)");
	}
  }

  public Item_Nota_FiscalED getByRecord(Item_Nota_FiscalED ed)
  throws Excecoes{
  	Item_Nota_FiscalED edVolta = new Item_Nota_FiscalED();
  	String sql =
  		" SELECT Itens_Notas_Fiscais.CD_Chassis_Serie, " +
		"        Itens_Notas_Fiscais.NR_Item_Nota_Fiscal, " +
		"        Itens_Notas_Fiscais.OID_Item_Nota_Fiscal, " +
		"        Itens_Notas_Fiscais.NR_Volumes, " +
		"        Itens_Notas_Fiscais.VL_Produto, " +
		"        Itens_Notas_Fiscais.VL_Unitario, " +
		"        Notas_Fiscais.NR_Nota_Fiscal, " +
		"        Notas_Fiscais.OID_Nota_Fiscal, " +
		"        Itens_Notas_Fiscais.NM_Transporte," +
		"        Itens_Notas_Fiscais.DM_Embalagem," +
		"        Notas_Fiscais.OID_Pessoa, " +
		"        Referencias.OID_Produto, " +
		"        Referencias.CD_Referencia, " +
		"        Referencias.NM_Referencia" +
		" from Itens_Notas_Fiscais, " +
		"      Notas_Fiscais, " +
		"      Referencias " +
		" WHERE Notas_Fiscais.oid_Nota_Fiscal = Itens_Notas_Fiscais.oid_Nota_Fiscal " +
		"   AND Itens_Notas_Fiscais.CD_Referencia = Referencias.CD_REFERENCIA ";
  	if (JavaUtil.doValida(ed.getOID_Item_Nota_Fiscal())) {
  		sql += " AND Itens_Notas_Fiscais.OID_Item_Nota_Fiscal = '" + ed.getOID_Item_Nota_Fiscal() + "'";
  	}
  	if (JavaUtil.doValida(ed.getOID_Nota_Fiscal())) {
  		sql += " AND Itens_Notas_Fiscais.OID_Nota_Fiscal = '" + ed.getOID_Nota_Fiscal() + "'";
  	}
  	if (JavaUtil.doValida(ed.getCD_Chassis_Serie())) {
  		sql += " AND Itens_Notas_Fiscais.CD_Chassis_Serie = '" + ed.getCD_Chassis_Serie() + "'";
  	}
  	if (ed.getNR_Nota_Fiscal() > 0) {
  		sql += " and Notas_Fiscais.NR_Nota_Fiscal = " + ed.getNR_Nota_Fiscal();
  		sql += " and Notas_Fiscais.NM_Serie = '" + ed.getNM_Serie() + "'";
  	}
  	ResultSet res = this.executasql.executarConsulta(sql);
  	try {
		while (res.next()){
			edVolta.setOID_Item_Nota_Fiscal(res.getString("OID_Item_Nota_Fiscal"));
			edVolta.setNR_Volumes(res.getLong("NR_Volumes"));
			edVolta.setVL_Produto(res.getDouble("VL_Produto"));
			edVolta.setVL_Unitario(res.getDouble("VL_Unitario"));
			edVolta.setNR_Nota_Fiscal(res.getLong("NR_Nota_Fiscal"));
			edVolta.setNR_Item_Nota_Fiscal(res.getLong("NR_Item_Nota_Fiscal"));
			edVolta.setOID_Nota_Fiscal(res.getString("OID_Nota_Fiscal"));
			edVolta.setCD_Referencia(res.getString("CD_Referencia"));
			edVolta.setCD_Chassis_Serie(res.getString("CD_Chassis_Serie"));
			edVolta.setOID_Produto(res.getLong("OID_Produto"));
			edVolta.setNM_Produto(res.getString("NM_Referencia"));
			edVolta.setNM_Transporte(res.getString("NM_Transporte"));
			edVolta.setDM_Embalagem(res.getString("DM_Embalagem"));
			edVolta.setOID_Pessoa(res.getString("OID_Pessoa"));
		}
		return edVolta;
	} catch (SQLException e) {
		throw new Excecoes(e.getMessage(), e, getClass().getName(), "getByRecord(Item_Nota_FiscalED ed)");
	}
  }

  public void geraRelatorio(Item_Nota_FiscalED ed)throws Excecoes{

    String sql = null;

    Item_Nota_FiscalED edVolta = new Item_Nota_FiscalED();

    try{

      sql = "select * from Item_Notas_Fiscais where 1=1 ";

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      Item_Nota_FiscalRL Item_Nota_Fiscal_rl = new Item_Nota_FiscalRL();
      Item_Nota_Fiscal_rl.geraRelatEstoque(res);
    }
    catch (Excecoes e){
      throw e;
    }
    catch(Exception exc){
      Excecoes exce = new Excecoes();
      exce.setExc(exc);
      exce.setMensagem("Erro no método listar");
      exce.setClasse(this.getClass().getName());
      exce.setMetodo("geraRelatorio(Item_Nota_FiscalED ed)");
    }

  }

}