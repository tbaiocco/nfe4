/*
 * Created on 12/11/2004
 *
 */
package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import com.master.ed.FornecedorED;
import com.master.ed.Local_EstoqueED;
import com.master.ed.Medicao_PneuED;
import com.master.ed.Motivo_SucateamentoED;
import com.master.ed.Movimento_PneuED;
import com.master.ed.PneuED;
import com.master.ed.RecapagemED;
import com.master.ed.VeiculoED;
import com.master.ed.Vida_PneuED;
import com.master.rl.PneuRL;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.JavaUtil;
import com.master.util.Utilitaria;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Tiago Sauter Lauxen
 * @autor Ralph - Alterado 07/2007
 */
public class PneuBD extends BancoUtil{

  private ExecutaSQL executaSQL;
  String sql = null;

  public PneuBD (ExecutaSQL sql) {
	  super(sql);
	  this.executaSQL = sql;
  }

  public PneuED inclui (PneuED ed) throws Excecoes {
	  // Gera código automatico caso não informado!
	  //if (!util.doValida (ed.getCD_Pneu ())) {
	  // ed.setCD_Pneu (String.valueOf (util.getAutoIncremento ("cd_pneu" , "pneus")));
	  //}
	  try {
		  ed.setOid_Pneu (getAutoIncremento ("oid_pneu" , "pneus"));
//		  ed.setCD_Pneu(String.valueOf(ed.getOid_Pneu()));
		  if (!doValida(ed.getCD_Pneu())) {
//			  ed.setCD_Pneu(String.valueOf(getAutoIncremento("cd_pneu" , "pneus")));
			  if (doValida(ed.getNr_Fogo())) {
				  ed.setCD_Pneu(ed.getNr_Fogo());
			  } else
				  ed.setCD_Pneu(String.valueOf(ed.getOid_Pneu()));
		  }
		  if (!doValida(ed.getNr_Fogo())) {
			  ed.setNr_Fogo(ed.getCD_Pneu());
		  }
		  sql = " INSERT INTO "+
		  "pneus ("+
		  "oid_pneu"+
		  ",cd_pneu"+
		  ",nr_fabrica"+
		  ",dm_localizacao"+
		  ",dm_situacao"+
		  ",dm_Controle_Parcial"+
		  ",km_atual"+
		  ",MM_atual"+
		  ",oid_fabricante_pneu"+
		  ",oid_modelo_pneu"+
		  ",oid_veiculo"+
		  ",oid_tipo_pneu"+
		  ",oid_fornecedor"+
		  ",oid_dimensao_pneu"+
		  ",oid_local_estoque"+
		  ",nr_km_acumulada"+
		  ",tx_lonas"+
		  ",nr_nota_fiscal"+
		  ",nr_perimetro"+
		  ",tx_dot"+
		  ",nr_serie"+
		  ",dt_nota_fiscal"+
		  ",dt_estoque"+
		  ",nr_Fogo"+
		  ",cd_item_estoque"+
		  ",oid_empresa"+
		  ",vl_preco"+
		  ",nr_vida"+
		  ",oid_Estoque" +
		  ",nr_km_inicial" +
		  ",nr_mm_inicial" +
		  ",dm_Stamp" +
	  	  ",dt_Stamp" +
	  	  ",usuario_Stamp"+
	  	  ",oid_usuario"+
	  	  ",time_millis"+
		  ") "+
		  " VALUES ("+
		  ed.getOid_Pneu ()+
		  ",'" + (doValida(ed.getCD_Pneu ()) ? ed.getCD_Pneu () : "" )+"'" +
		  ",'" + (doValida(ed.getNR_Fabrica ()) ? ed.getNR_Fabrica () : "" )+"'" +
		  ",'" + (doValida(ed.getDM_Localizacao()) ? ed.getDM_Localizacao (): "" )+ "'" +
		  ",'" + (doValida(ed.getDM_Situacao()) ? ed.getDM_Situacao (): "" )+ "'" +
		  ",'" + ed.getDm_Controle_Parcial() + "'" +
		  ","  + ed.getKM_Atual ()+
		  ","  + ed.getMM_Atual ()+
		  ","  + ed.getOid_Fabricante_Pneu ()+
		  ","  + ed.getOid_Modelo_Pneu ()+
		  ",'" + (doValida(ed.getOid_Veiculo()) ? ed.getOid_Veiculo ():"") + "'" +
		  ","  + ed.getOid_Tipo_Pneu()+
		  ",'" + ed.getOid_Fornecedor()+ "'" +
		  ","  + ed.getOid_Dimensao_Pneu()+
		  ","  + ed.getOid_Empresa()+
		  ","  + ed.getNr_Km_Acumulada()+
		  ",'" + (doValida(ed.getTx_Lonas())? ed.getTx_Lonas() : "") + "'" +
		  ","  + ed.getNr_Nota_Fiscal()+
		  ","  + ed.getNr_Perimetro()+
		  ",'" + (doValida(ed.getTx_Dot())? ed.getTx_Dot() : "") + "'" +
		  ",'" + (doValida(ed.getNr_Serie())? ed.getNr_Serie() : "") + "'" +

		  ",'" + (doValida(ed.getDt_Nota_Fiscal())? ed.getDt_Nota_Fiscal() : ed.getDt_stamp()) + "'" +
		  ",'" + (doValida(ed.getDt_Nota_Fiscal())? ed.getDt_Nota_Fiscal() : ed.getDt_stamp()) + "'" +

		  ",'" + ed.getNr_Fogo()+ "'" +
		  ",'" + ed.getCd_Item_Estoque()+ "'" +
		  ","  + ed.getOid_Empresa()+
		  ","  + ed.getVl_Preco()+
		  ","  + ed.getNr_Vida()+
		  ","  + ed.getOid_estoque ()+
		  ","  + ed.getNr_Km_Acumulada()+
		  ","  + ed.getMM_Atual()+
		  ",'I'" +
	  	  ",'" + ed.getDt_stamp() + "'" +
	  	  ",'" + ed.getUsuario_Stamp() + "'" +
	  	  "," + ed.getUser() +
	  	  "," + ed.getTime_millis() +
		  ")" ;
		  executaSQL.executarUpdate (sql);
		  System.out.println("PNEU INCLUI>> "+sql);
		  return ed;
	  } catch (Exception e) {
		  throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "inclui(PneuED ed)");
	  }
  }

  public PneuED cargaStgp (PneuED ed) throws Excecoes {
	  try {
		  ed.setOid_Pneu (getAutoIncremento ("oid_pneu" , "pneus"));
		  sql = " INSERT INTO "+
		  "pneus ("+
		  "oid_pneu"+
		  ",cd_pneu"+
		  ",nr_fabrica"+
		  ",dm_localizacao"+
		  ",dm_situacao"+
		  ",dm_Controle_Parcial"+
		  ",km_atual"+
		  ",MM_atual"+
		  ",oid_fabricante_pneu"+
		  ",oid_modelo_pneu"+
		  ",oid_tipo_pneu"+
		  ",oid_fornecedor"+
		  ",oid_dimensao_pneu"+
		  ",nr_km_acumulada"+
		  ",tx_lonas"+
		  ",nr_nota_fiscal"+
		  ",nr_perimetro"+
		  ",tx_dot"+
		  ",nr_serie"+
		  ",dt_nota_fiscal"+
		  ",nr_Fogo"+
		  ",cd_item_estoque"+
		  ",oid_empresa"+
		  ",vl_preco"+
		  ",nr_vida"+
		  ",nr_km_inicial" +
		  ",nr_mm_inicial" ;

  		// Estoque
  		if ( doValida(ed.getDt_Estoque()) ) {
  			sql+=",dt_Estoque" +
  				 ",oid_Local_Estoque";
  		}
  		// Recape
  		if ( doValida(ed.getDt_Remessa_Recapagem()) ) {
  			sql+=",dt_Remessa_Recapagem" +
  				 ",oid_Fornecedor_Recapagem" +
  				 ",dt_Promessa_Retorno_Recapagem" +
  				 ",nm_Contato_Recapagem" +
  				 ",nr_Os_Recapagem" +
  				 ",vl_Negociado_Recapagem";
  		}
  		// Sucata
  		if ( doValida(ed.getDt_Sucateamento()) ) {
  			sql+=",dt_Sucateamento" +
				 ",oid_Motivo_Sucateamento";
  		}
  		// Frota
  		if ( doValida(ed.getDt_Entrada()) ) {
  			sql+=",dt_Entrada" +
				 ",oid_Veiculo" +
				 ",dm_Posicao" +
				 ",dm_Eixo" +
				 ",nr_Hodometro_Veiculo" +
				 ",nr_Km_Acumulada_Veiculo";
  		}

		  sql +=") "+
		  " VALUES ("+
		  ed.getOid_Pneu ()+
		  ",'" + (doValida(ed.getCD_Pneu ()) ? ed.getCD_Pneu () : "" )+"'" +
		  ",'" + (doValida(ed.getNR_Fabrica ()) ? ed.getNR_Fabrica () : "" )+"'" +
		  ",'" + (doValida(ed.getDM_Localizacao()) ? ed.getDM_Localizacao (): "" )+ "'" +
		  ",'" + (doValida(ed.getDM_Situacao()) ? ed.getDM_Situacao (): "" )+ "'" +
		  ",'" + ed.getDm_Controle_Parcial() + "'" +
		  ","  + ed.getKM_Atual ()+
		  ","  + ed.getMM_Atual ()+
		  ","  + ed.getOid_Fabricante_Pneu ()+
		  ","  + ed.getOid_Modelo_Pneu ()+
		  ","  + ed.getOid_Tipo_Pneu()+
		  ",'" + ed.getOid_Fornecedor()+ "'" +
		  ","  + ed.getOid_Dimensao_Pneu()+
		  ","  + ed.getNr_Km_Acumulada()+
		  ",'" + (doValida(ed.getTx_Lonas())? ed.getTx_Lonas() : "") + "'" +
		  ","  + ed.getNr_Nota_Fiscal()+
		  ","  + ed.getNr_Perimetro()+
		  ",'" + (doValida(ed.getTx_Dot())? ed.getTx_Dot() : "") + "'" +
		  ",'" + (doValida(ed.getNr_Serie())? ed.getNr_Serie() : "") + "'" +
		  ",'" + ed.getDt_Nota_Fiscal()+ "'" +
		  ",'" + ed.getNr_Fogo()+ "'" +
		  ",'" + ed.getCd_Item_Estoque()+"'" +
		  ","  + ed.getOid_Empresa()+
		  ","  + ed.getVl_Preco()+
		  ","  + ed.getNr_Vida()+
		  ","  + ed.getNr_Km_Acumulada()+
		  ","  + ed.getMM_Atual();

  		// Estoque
  		if ( doValida(ed.getDt_Estoque()) ) {
  			sql+=",'" + ed.getDt_Estoque() + "'" +
  				 "," + ed.getOid_Local_Estoque() ;
  		}
  		// Recape
  		if ( doValida(ed.getDt_Remessa_Recapagem()) ) {
  			sql+=",'"+ed.getDt_Remessa_Recapagem()+"'" +
  			",'"+ed.getOid_Fornecedor_Recapagem()+"'" +
  			",'"+ed.getDt_Promessa_Retorno_Recapagem()+"'" +
  			",'"+ed.getNm_Contato_Recapagem()+"'" +
  			",'"+ed.getNr_Os_Recapagem()+"'" +
  			","+ed.getVl_Negociado_Recapagem() ;
  		}

  		// Sucata
  		if ( doValida(ed.getDt_Sucateamento()) ) {
  			sql+=",'"+ed.getDt_Sucateamento()+"'" +
  			","+ed.getOid_Motivo_Sucateamento();
  		}

		// Atualiza frota
		if ( doValida(ed.getDt_Entrada()) )
			sql+=",'"+ed.getDt_Entrada()+"'" +
			",'"+ed.getOid_Veiculo()+"'" +
			",'"+ed.getDM_Posicao()+"'" +
			",'"+ed.getDm_Eixo()+"'" +
			","+ed.getNr_Hodometro_Veiculo() +
			","+ed.getNr_Km_Acumulada_Veiculo();

		  sql+=")" ;
		  executaSQL.executarUpdate (sql);
		  return ed;
	  } catch (Exception e) {
		  throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "inclui(PneuED ed)");
	  }
  }

  public void altera (PneuED ed) throws Excecoes {

	  try {
		  sql = "UPDATE " +
		  "pneus " +
		  "SET " +
		  "cd_pneu = '" + (doValida(ed.getCD_Pneu ())?ed.getCD_Pneu ():"") + "'" +
		  ",nr_fabrica ='" + (doValida(ed.getNR_Fabrica ()) ? ed.getNR_Fabrica () : "" )+"'" +
		  ",dt_stamp = '" + ed.getDt_stamp() + "'" +
		  ",usuario_stamp = '" + ed.getUsuario_Stamp() + "'" +
		  ",dm_stamp = '" + ed.getDm_Stamp() + "'" +
		  ",oid_usuario = " + ed.getUser() +
		  ",time_millis = " + ed.getTime_millis() +
		  ",dm_Controle_Parcial = '" + ed.getDm_Controle_Parcial() + "' " +
		  ",oid_fabricante_pneu =" + ed.getOid_Fabricante_Pneu ()+
		  ",oid_tipo_pneu =" + ed.getOid_Tipo_Pneu() +
		  ",oid_modelo_pneu =" + ed.getOid_Modelo_Pneu () +
		  ",oid_dimensao_Pneu =" + ed.getOid_Dimensao_Pneu() +
		  ",dm_localizacao ='" + (doValida(ed.getDM_Localizacao()) ? ed.getDM_Localizacao (): "" )+ "'" +
		  ",dm_situacao ='" + (doValida(ed.getDM_Situacao()) ? ed.getDM_Situacao (): "" )+ "'" +
		  ",nr_km_acumulada =" + ed.getNr_Km_Acumulada() +
		  ",tx_lonas ='" + (doValida(ed.getTx_Lonas())? ed.getTx_Lonas() : "" ) + "'" +
		  ",nr_nota_fiscal =" + ed.getNr_Nota_Fiscal() +
		  ",nr_perimetro =" + ed.getNr_Perimetro() +
		  ",tx_dot ='" + (doValida(ed.getTx_Dot()) ? ed.getTx_Dot() : "" ) + "'" +
		  ",nr_serie ='" + (doValida(ed.getNr_Serie()) ? ed.getNr_Serie() : "" ) + "'" +
		  ",nr_Fogo ='" + ed.getNr_Fogo() + "'"     +
		  ",cd_item_estoque ='" + (doValida(ed.getCd_Item_Estoque()) ? ed.getCd_Item_Estoque (): "" )+ "'" +
		  ",vl_preco =" + ed.getVl_Preco() +
		  ",km_atual =" + ed.getKM_Atual () +
		  ",mm_atual =" + ed.getMM_Atual () ;
		  if (  ed.getOid_estoque () > 0 )
			  sql += ",oid_Estoque = " + ed.getOid_estoque () ;
		  if (  ed.getNr_Vida() > 0 )
			  sql += ",nr_vida =" + ed.getNr_Vida() ;

		  if (doValida(ed.getOid_Fornecedor())){
			  sql +=",oid_fornecedor ='" + ed.getOid_Fornecedor () +"'";
		  }
		  if (doValida(ed.getDt_Nota_Fiscal())){
			  sql +=",dt_nota_fiscal ='" + ed.getDt_Nota_Fiscal () +"'";
		  }


	  sql += " WHERE " +
		  "oid_pneu = " + ed.getOid_Pneu ();

		  executaSQL.executarUpdate (sql);
	  }
	  catch (SQLException e) {
		  throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "altera(PneuED ed)");
	  }
  }

  public void alteraUltima_Recapagem (PneuED ed) throws Excecoes {
	  try {
		  sql = "UPDATE Pneus SET " +
		  " oid_Fornecedor_Ultima_Recapagem = " + ed.getOid_Fornecedor_Ultima_Recapagem() +
		  ",oid_Fabricante_Ultima_Recapagem = " + ed.getOid_Fabricante_Ultima_Recapagem() +
		  ",oid_Modelo_Pneu_Ultima_Recapagem = " + ed.getOid_Modelo_Pneu_Ultima_Recapagem() +
		  ",dt_stamp = '" + ed.getDt_stamp() + "'" +
		  ",usuario_stamp = '" + ed.getUsuario_Stamp() + "'" +
		  ",dm_stamp = '" + ed.getDm_Stamp() + "'" +
		  ",vl_Ultima_Recapagem = " + ed.getVl_Ultima_Recapagem() +
		  ",dt_Ultima_Recapagem = '" + ed.getDt_Ultima_Recapagem() + "' " +
		  ",nr_Os_Ultima_Recapagem = '" + ed.getNr_Os_Ultima_Recapagem() + "' " +
		  ",nr_Nota_Fiscal_Ultima_Recapagem = " + ed.getNr_Nota_Fiscal_Ultima_Recapagem() +
		  ",dm_Garantia_Ultima_Recapagem = " + ed.getDm_Garantia_Ultima_Recapagem() +
		  ",nr_MM_Sulco_Ultima_Recapagem = " + ed.getNr_MM_Sulco_Ultima_Recapagem() +
		  " where oid_Pneu = " + ed.getOid_Pneu();
		  executaSQL.executarUpdate (sql);

		  if (ed.getOid_Empresa()>0) { // Se oid_empresa = 0, não atualiza medição faz somnte o registro da ultima recapagem nesse caso o registro da medição é feito em atualizaSituacao...
			  Medicao_PneuED mp = new Medicao_PneuED();
			  mp.setOid_Empresa(ed.getOid_Empresa());
			  mp.setOid_Pneu(ed.getOid_Pneu());
			  mp.setNr_Vida(ed.getNr_Vida());
			  mp.setDt_Medicao_Pneu(ed.getDt_Ultima_Recapagem());
			  mp.setNr_Mm_Sulco(ed.getNr_MM_Sulco_Ultima_Recapagem());
			  mp.setNr_Km_Acumulada_Pneu(ed.getNr_Km_Acumulada());
			  new Medicao_PneuBD(this.executaSQL).inclui(mp);
		  }
	  }
	  catch (SQLException e) {
		  throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "alteraUltima_Recapagem (PneuED ed)");
	  }
  }

  public void alteraVenda (PneuED ed) throws Excecoes {
	  try {
		  sql = "UPDATE Pneus SET " +
		  " vl_Venda = " + ed.getVl_Venda() +
		  ",dt_stamp = '" + ed.getDt_stamp() + "'" +
		  ",usuario_stamp = '" + ed.getUsuario_Stamp() + "'" +
		  ",dm_stamp = '" + ed.getDm_Stamp() + "'" +
		  ",dt_Venda = '" + ed.getDt_Venda() + "' " +
		  ",tx_Comentario_Venda = '" + ed.getTx_Comentario_Venda() + "' " +
		  " where oid_Pneu = " + ed.getOid_Pneu();
		  executaSQL.executarUpdate (sql);
	  }
	  catch (SQLException e) {
		  throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "alteraVenda (PneuED ed)");
	  }
  }

  public void alteraVida (PneuED ed) throws Excecoes {
	  try {
		  sql = "UPDATE Pneus SET " +
		  " nr_vida = " + ed.getNr_Vida() +
		  ",dt_stamp = '" + ed.getDt_stamp() + "'" +
		  ",usuario_stamp = '" + ed.getUsuario_Stamp() + "'" +
		  ",dm_stamp = '" + ed.getDm_Stamp() + "'" +
		  ",oid_usuario = " + ed.getUser() +
		  ",time_millis = " + ed.getTime_millis() +
		  " where oid_Pneu = " + ed.getOid_Pneu();
		  executaSQL.executarUpdate (sql);
	  }
	  catch (SQLException e) {
		  throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "alteraVida (PneuED ed)");
	  }
  }

  public void deleteUltima_Recapagem (PneuED ed) throws Excecoes {
	  try {
		  sql = "UPDATE Pneus SET " +
		  " oid_Fornecedor_Ultima_Recapagem = null " +
		  ",oid_Fabricante_Ultima_Recapagem = null " +
		  ",oid_Modelo_Pneu_Ultima_Recapagem = null " +
		  ",vl_Ultima_Recapagem = null " +
		  ",dt_Ultima_Recapagem = null " +
		  ",nr_Os_Ultima_Recapagem = null " +
		  ",nr_Nota_Fiscal_Ultima_Recapagem = null " +
		  ",dm_Garantia_Ultima_Recapagem = null " +
		  ",nr_MM_Sulco_Ultima_Recapagem = null " +
		  "where " +
		  "oid_Pneu = " + ed.getOid_Pneu();
		  executaSQL.executarUpdate (sql);
	  }
	  catch (SQLException e) {
		  throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "deleteUltima_Recapagem (PneuED ed)");
	  }
  }

  public void deleteVenda (PneuED ed) throws Excecoes {
	  try {
		  sql = "UPDATE Pneus SET " +
		  " vl_Venda = null " +
		  ",dt_Venda = null " +
		  ",tx_Comentario_Venda = null " +
		  "where " +
		  "oid_Pneu = " + ed.getOid_Pneu();
		  executaSQL.executarUpdate (sql);
	  }
	  catch (SQLException e) {
		  throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "deleteVenda (PneuED ed)");
	  }
  }

  public void alteraSituacaoPneu (PneuED ed) throws Excecoes {

	  if (ed.getOid_Empresa () > 0){
		  sql = "UPDATE " +
			  "pneus " +
			  "SET " +
			  "dm_localizacao ='" + (doValida(ed.getDM_Localizacao()) ? ed.getDM_Localizacao (): "" )+ "'" +
			  ",dm_situacao ='" + (doValida(ed.getDM_Situacao()) ? ed.getDM_Situacao (): "" )+ "'";
	      sql += "WHERE " +
		  	  "oid_pneu = " + ed.getOid_Pneu ();
	  }
	  if (ed.getOid_Empresa () <= 0 && "03".equals(ed.getDM_Localizacao())){
		 sql = "UPDATE " +
			  "pneus " +
			  "SET " +
			  "dm_localizacao ='" + (doValida(ed.getDM_Localizacao()) ? ed.getDM_Localizacao (): "" )+ "'" +
			  ",dm_situacao ='" + (doValida(ed.getDM_Situacao()) ? ed.getDM_Situacao (): "" )+ "'"+
			  ",dm_posicao ='" + (doValida(ed.getDM_Posicao()) ? ed.getDM_Posicao(): "" )+ "'"+
			  ",km_atual =" + ed.getKM_Atual()+
		  	  ",oid_veiculo ='" + (doValida(ed.getOid_Veiculo()) ? ed.getOid_Veiculo(): "" )+ "'";
	      sql += "WHERE " +
		  	  "oid_pneu = " + ed.getOid_Pneu ();
	  }else if (ed.getOid_Empresa () <= 0 && "01".equals(ed.getDM_Localizacao())){
			sql = "UPDATE " +
			  "pneus " +
			  "SET " +
			  "dm_localizacao ='" + (doValida(ed.getDM_Localizacao()) ? ed.getDM_Localizacao (): "" )+ "' " +
			  ",dm_situacao ='" + (doValida(ed.getDM_Situacao()) ? ed.getDM_Situacao (): "" )+ "'"+
			  ",km_atual =" + ed.getKM_Atual()+
			  ",dm_posicao =' '"+
		  	  ",oid_veiculo =' ' ";
	      sql += "WHERE " +
		  	  "oid_pneu = " + ed.getOid_Pneu ();
	  }else{
			sql = "UPDATE " +
			  "pneus " +
			  "SET " +
			  "dm_localizacao ='" + (doValida(ed.getDM_Localizacao()) ? ed.getDM_Localizacao (): "" )+ "' " +
			  ",dm_situacao ='" + (doValida(ed.getDM_Situacao()) ? ed.getDM_Situacao (): "" )+ "'"+
			  ",km_atual =" + ed.getKM_Atual()+
			  ",dm_posicao =' '"+
		  	  ",oid_veiculo =' ' ";
	      sql += "WHERE " +
		  	  "oid_pneu = " + ed.getOid_Pneu ();
	  }

    try {
      executaSQL.executarUpdate (sql);
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "alteraPneuVeiculo");
    }
  }

  public void delete (PneuED ed) throws Excecoes {
	    try {
			           sql = " DELETE FROM pneus "+
			        		 " WHERE " 							     +
			        		 "oid_empresa = "  + ed.getOid_Empresa() +
			        		 " and "								     +
			        		 "oid_pneu = "  + ed.getOid_Pneu();
      executaSQL.executarUpdate (sql);
	    	}
	    	catch (SQLException e) {
	    		throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "deleta(PneuED ed)");
    }
  }

  /**
   * Sql para buscar somatórios de pneus agrupados por dimensao/marca/modelo.
   * @param ed
   * @return
   * @throws Excecoes
   */
  public ArrayList listaGroupByDFM (PneuED ed) throws Excecoes {
	  ArrayList list = new ArrayList ();
	  sql= "SELECT " +
	  "nm_Dimensao_Pneu," ;
	  if ("COMPRADO".equals(ed.getDt_Nota_Fiscal())) { // se a busca for para pneus comprados acrescenta fabricante e modelo
		  sql += "nm_Fabricante_Pneu," +
		  		 "nm_modelo_pneu,"	;
	  }
	  sql += "COUNT(oid_Pneu) as nr_Pneus," +
	  "SUM(vl_Venda) as vl_Soma_Venda, " +
	  "SUM(vl_Preco) as vl_Soma_Preco " +
	  "FROM " +
	  "pneus p, " +
	  "dimensoes_pneus d " ;
	  if ("COMPRADO".equals(ed.getDt_Nota_Fiscal())) {
		  sql +=",fabricantes_pneus f " +
		  		",modelos_pneus m " ;
	  }
	  sql+="WHERE " +
	  "p.oid_dimensao_pneu = d.oid_dimensao_pneu " +
	  "and p.oid_empresa = " + ed.getOid_Empresa() +" ";
	  if ("COMPRADO".equals(ed.getDt_Nota_Fiscal())) {
		  sql +="and p.oid_fabricante_pneu = f.oid_fabricante_pneu " +
		  		"and p.oid_modelo_pneu = m.oid_modelo_pneu " ;
		  if (doValida(ed.getDt_Inicial_Compra()))
			  sql += " and p.dt_Nota_Fiscal > '" + ed.getDt_Inicial_Compra()+ "' ";
		  if (doValida(ed.getDt_Final_Compra()))
			  sql += " and p.dt_Nota_Fiscal < '" + ed.getDt_Final_Compra()+ "' ";
		  if (doValida(ed.getOid_Fornecedor ()) )
			  sql += " and p.oid_Fornecedor = '" + ed.getOid_Fornecedor() + "' ";
		  if (ed.getOid_Fabricante_Pneu () > 0)
			  sql += " and p.oid_fabricante_pneu = " + ed.getOid_Fabricante_Pneu ();
		  if (ed.getOid_Dimensao_Pneu () > 0)
			  sql += " and p.oid_Dimensao_Pneu = " + ed.getOid_Dimensao_Pneu ();
	  }
	  if ("VENDIDO".equals(ed.getDt_Venda())) { // Busca somente pneus vendidos
		  sql+= "and dt_venda is not null " ;
		  if (doValida(ed.getDt_Inicial_Sucateamento()))
			  sql += " and p.dt_Venda >= '" + ed.getDt_Inicial_Sucateamento()+ "' ";
		  if (doValida(ed.getDt_Final_Sucateamento()))
			  sql += " and p.dt_Venda <= '" + ed.getDt_Final_Sucateamento()+ "' ";
	  }
	  sql+= "GROUP BY " +
	  "nm_dimensao_pneu ";
	  if ("COMPRADO".equals(ed.getDt_Nota_Fiscal())) {
		  sql +=",nm_Fabricante_Pneu "+
		  		",nm_modelo_pneu ";
	  }
	  sql+= "ORDER BY " +
	  "nm_dimensao_pneu ";
	  if ("COMPRADO".equals(ed.getDt_Nota_Fiscal())) {
		  sql +=",nm_Fabricante_Pneu "+
		  		",nm_modelo_pneu ";
	  }

	  ResultSet rs = this.executaSQL.executarConsulta(sql);
	  try {
		  while (rs.next ()) {
			  PneuED popED = new PneuED();
			  popED.setNm_Dimensao_Pneu(rs.getString("nm_Dimensao_Pneu"));
			  if ("COMPRADO".equals(ed.getDt_Nota_Fiscal())) {
				  popED.setNm_Fabricante_Pneu(rs.getString("nm_Fabricante_Pneu"));
				  popED.setNm_Modelo_Pneu(rs.getString("nm_Modelo_Pneu"));
			  }
			  popED.setOid_Dimensao_Pneu(rs.getInt("nr_Pneus"));
			  popED.setVl_Venda(rs.getDouble("vl_Soma_Venda"));
			  popED.setVl_Preco(rs.getDouble("vl_Soma_Preco"));
			  list.add(popED);
		  }
		  return list;
	  }

	  catch (Exception e) {
		  throw new Excecoes (e.getMessage () , e , this.getClass().getName(), "listaByDimensao ()");
	  }
  }

	public ArrayList Busca_Eixo_Veiculo(PneuED ed) throws Excecoes {
		  ArrayList list = new ArrayList ();
			sql = "SELECT DISTINCT " +
			"dm_eixo " +
			"FROM " +
			"Pneus " +
			"WHERE " +
			"oid_Veiculo = '" + ed.getOid_Veiculo() + "' " ;
			sql+= " and dm_Posicao not in ('DD','DE','TD','TE','STP1','STP2')" ;
	 	  ResultSet pns = this.executaSQL.executarConsulta(sql);
	 	  try {
			  while (pns.next ()) {
				  PneuED pneu_eixo_veic = new PneuED ();
				  pneu_eixo_veic.setDm_Eixo(pns.getString("dm_eixo"));
				  list.add(pneu_eixo_veic);
			  }
			  return list;
		  }
		  catch (Exception e) {
			  throw new Excecoes (e.getMessage () , e , this.getClass().getName(), "lista ()");
		  }
	}

	  public ArrayList Busca_Pneu (PneuED ed) throws Excecoes {
		  ArrayList list = new ArrayList ();
		  sql = "SELECT DISTINCT 'A'" ;
		  if ("true".equals(ed.getDm_Tipo_Pneu())){
			  sql += " ,oid_tipo_pneu" ;
		  }else
		  if ("true".equals(ed.getDm_Dimensao_Pneu())){
		      sql +=" ,oid_dimensao_pneu " ;
		  }else
		  if ("true".equals(ed.getDm_Fabricante_Pneu())){
		      sql +=" ,oid_fabricante_pneu" ;
		  }else
		  if ("true".equals(ed.getDm_Modelo_Pneu())){
		      sql +=" ,oid_modelo_pneu " ;
		  }else
		  if ("true".equals(ed.getDm_Vida_Pneu())){
			  sql +=" ,nr_vida" ;
		  }else
		  if ("true".equals(ed.getDm_Perimetro())){
			  sql +=" ,nr_perimetro" ;
		  }else
		  if ("true".equals(ed.getDm_MM_Atual())){
		      sql +=" ,mm_atual" ;
		  }
		  sql += " FROM " +
		  " pneus " +
		  "WHERE " ;
		  if (doValida (ed.getOid_Veiculo ())) {
			  sql += " oid_veiculo = " + ed.getOid_Veiculo () ;
		  }
		  if (doValida (ed.getDm_Eixo())) {
			  sql += " AND dm_eixo = '" + ed.getDm_Eixo()+"'";
		  }

	 	  ResultSet rs = this.executaSQL.executarConsulta(sql);
	 	  try {
			  while (rs.next ()) {
				  PneuED pneu_busca = new PneuED ();
				  list.add(pneu_busca);
			  }
			  return list;
		  }
		  catch (Exception e) {
			  throw new Excecoes (e.getMessage () , e , this.getClass().getName(), "lista ()");
		  }
	  }

	  // Lista para a Tela Pneus por Marca
	  public ArrayList listaPorMarca (PneuED ed) throws Excecoes {
		  ArrayList list = new ArrayList ();
		  sql = "SELECT " +
		  " f.nm_fabricante_pneu," +
		  " d.nm_dimensao_pneu," +
		  " m.nm_modelo_pneu," +
		  "COUNT (p.oid_fabricante_pneu) AS quantidade," +
		  " CASE " +
		  "    when dt_entrada is not null then " +
		  "        'FROTA - ' || dm_posicao " +
		  "    when dt_estoque is not null then " +
		  "        'ESTOQUE' " +
		  "    when dt_remessa_recapagem is not null then " +
		  "        'RECAPAGEM' " +
		  "    when dt_Sucateamento is not null then " +
		  "        'SUCATEADO' " +
		  "    when dt_Venda is not null then " +
		  "        'VENDIDO' " +
		  "    else 'Não Localizado' " +
		  " END as desc_localizacao_pneu" +
		  " FROM " +
		  "pneus p " +
		  "left join veiculos v " +
		  " on v.oid_veiculo = p.oid_veiculo, " +
		  "fabricantes_pneus f, " +
		  "modelos_pneus m, " +
		  "dimensoes_pneus d, " +
		  "tipos_pneus t " +
		  "WHERE " +
		  "p.oid_fabricante_pneu = f.oid_fabricante_pneu " +
		  "AND " +
		  "p.oid_modelo_pneu = m.oid_modelo_pneu " +
		  "AND " +
		  "p.oid_dimensao_pneu = d.oid_dimensao_pneu " +
		  "AND " +
		  "p.oid_tipo_pneu = t.oid_tipo_pneu " ;
		  if (ed.getOid_Empresa() > 0) {
			  sql += " AND p.oid_Empresa = " + ed.getOid_Empresa();
		  }
		  if (ed.getOid_Dimensao_Pneu() > 0) {
			  sql += " AND p.oid_dimensao_Pneu = " + ed.getOid_Dimensao_Pneu();
		  }
		  if (ed.getOid_Fabricante_Pneu () > 0) {
			  sql += " AND p.oid_fabricante_pneu = " + ed.getOid_Fabricante_Pneu ();
		  }
		  if (ed.getOid_Modelo_Pneu () > 0) {
			  sql += " AND p.oid_modelo_pneu = " + ed.getOid_Modelo_Pneu ();
		  }
		  if ("true".equals(ed.getDm_Considera_Sucateados())){
			  sql += " and ( dt_sucateamento is null or dt_sucateamento is not null ) ";
		  }	else {
			  sql += " and dt_sucateamento is null  ";
		  }
		  sql += " GROUP BY " +
		  		"f.nm_fabricante_pneu, " +
		  		"d.nm_dimensao_pneu, " +
		  		"m.nm_modelo_pneu," +
		  		"desc_localizacao_pneu" +
		  		" ORDER BY f.nm_fabricante_pneu";
	 	  ResultSet rs = this.executaSQL.executarConsulta(sql);
	 	  try {
			  while (rs.next ()) {
				  PneuED popula = new PneuED ();
				  popula.setNr_Perimetro(rs.getLong("quantidade"));
				  popula.setNm_Fabricante_Pneu(rs.getString("nm_fabricante_pneu"));
				  popula.setNm_Dimensao_Pneu(rs.getString("nm_dimensao_pneu"));
				  popula.setNm_Modelo_Pneu(rs.getString("nm_modelo_pneu"));
				  popula.setDesc_Localizacao_Pneu(rs.getString("desc_localizacao_pneu"));
				  list.add(popula);
			  }
			  return list;
		  }
		  catch (Exception e) {
			  throw new Excecoes (e.getMessage () , e , this.getClass().getName(), "lista ()");
		  }
	  }

	  public ArrayList listaRecusasRecapagens (PneuED ed) throws Excecoes {
		  ArrayList list = new ArrayList ();
		  sql = "SELECT "+
		  "p.*, " +
		  "fo.nm_razao_social " +
		  "FROM " +
		  "pneus p ," +
		  "pessoas fo " +
		  "WHERE " +
		  "fo.oid_pessoa = p.oid_fornecedor_recusa_recapagem " +
		  "AND " +
		  "dt_Recusa_Recapagem is not null " ;
			  if (ed.getOid_Empresa() > 0) {
				  sql += " AND p.oid_Empresa = " + ed.getOid_Empresa();
  		  	  }
			  if (ed.getOid_Pneu() > 0) {
				  sql += " AND p.oid_Pneu = " + ed.getOid_Pneu();
  		  	  }
			  if (doValida(ed.getDt_Inicial_Compra())) {
				  sql += " AND p.dt_Recusa_Recapagem > '" + ed.getDt_Inicial_Compra()+ "' ";
			  }
			  if (doValida(ed.getDt_Final_Compra())) {
				  sql += " AND p.dt_Recusa_Recapagem < '" + ed.getDt_Final_Compra()+ "' ";
			  }
			  if (doValida(ed.getOid_Fornecedor())) {
				  sql += " AND p.oid_fornecedor_recusa_recapagem = '" + ed.getOid_Fornecedor() +"' ";
			  }
		  sql += " ORDER BY " +
		  		 " dt_Recusa_Recapagem," +
		  		 " tx_Motivo_Recusa_Recapagem," +
		  		 " p.nr_Fogo";
	 	  ResultSet rs = this.executaSQL.executarConsulta(sql);
	 	  try {
			  while (rs.next ()) {
				  PneuED popula = new PneuED ();
				  popula.setNr_Fogo(rs.getString("nr_Fogo"));
				  popula.setCD_Pneu(rs.getString("CD_Pneu"));
				  popula.setDt_Recusa_Recapagem(FormataData.formataDataBT(rs.getString("dt_Recusa_Recapagem")));
				  popula.setTx_Motivo_Recusa_Recapagem(rs.getString("tx_Motivo_Recusa_Recapagem"));
				  popula.setNr_Nota_Fiscal_Recusa_Recapagem(rs.getLong("nr_Nota_Fiscal_Recusa_Recapagem"));
				  popula.setNm_Responsavel_Recusa_Recapagem(rs.getString("nm_Responsavel_Recusa_Recapagem"));
				  popula.setNm_Fornecedor_Recapagem(rs.getString("nm_Razao_Social"));
				  list.add(popula);
			  }
			  return list;
		  }
		  catch (Exception e) {
			  throw new Excecoes (e.getMessage () , e , this.getClass().getName(), "lista ()");
		  }
	  }


  public ArrayList lista (PneuED ed) throws Excecoes {
	  ArrayList list = new ArrayList ();
	  sql = "SELECT "+
	  "p.*, " +
	  "p.usuario_Stamp as usu_Stmp " +
	  ",p.dt_Stamp as dt_Stmp " +
	  ",p.dm_Stamp as dm_Stmp, " +
	  "f.cd_fabricante_pneu, " +
	  "m.cd_modelo_pneu, " +
	  "v.nr_placa, " +
	  "v.nr_frota, " +
	  "f.nm_fabricante_pneu, " +
	  "m.nm_modelo_pneu, " +
	  "d.nm_dimensao_pneu, " +
	  "t.nm_tipo_pneu," +
	  "fo.nm_razao_social " +
	  "FROM " +
	  "pneus p " +
	  "left join veiculos v " +
	  " on v.oid_veiculo = p.oid_veiculo, " +
	  "fabricantes_pneus f, " +
	  "modelos_pneus m, " +
	  "dimensoes_pneus d, " +
	  "tipos_pneus t, " +
	  "fornecedores fo " +
	  "WHERE " +
	  "p.oid_fornecedor = fo.oid_fornecedor " +
	  "AND " +
	  "p.oid_fabricante_pneu = f.oid_fabricante_pneu " +
	  "AND " +
	  "p.oid_modelo_pneu = m.oid_modelo_pneu " +
	  "AND " +
	  "p.oid_dimensao_pneu = d.oid_dimensao_pneu " +
	  "AND " +
	  "p.oid_tipo_pneu = t.oid_tipo_pneu " ;
	  if (ed.getOid_Pneu () > 0) { // Se informado o oid_Pneu busca somente por isso....
		  sql += " AND p.oid_pneu = " + ed.getOid_Pneu () + " ";
	  } else {
		  if (ed.getOid_Empresa() > 0) {
			  sql += " AND p.oid_Empresa = " + ed.getOid_Empresa();
		  }
		  if ("ESTOQUE".equals(ed.getDt_Estoque())) {
			  sql += " AND dt_estoque is not null ";
		  }
		  if ("RECAPAGEM".equals(ed.getDt_Remessa_Recapagem())) { // Busca somente pneus em recapagem
			  sql += " AND dt_Remessa_Recapagem is not null ";
			  if (doValida(ed.getOid_Fornecedor_Recapagem())) {
				  sql += " AND oid_Fornecedor_Recapagem = '" + ed.getOid_Fornecedor_Recapagem() +"' ";
			  }
		  }
		  if ("SUCATEAMENTO".equals(ed.getDt_Sucateamento())) { // Busca somente pneus sucateados
			  sql += " and dt_Sucateamento is not null ";
			  if (ed.getOid_Motivo_Sucateamento()>0)
				  sql += " and p.oid_motivo_sucateamento = " + ed.getOid_Motivo_Sucateamento()+ " ";
			  if (ed.getOid_Dimensao_Pneu()>0)
				  sql += " and p.oid_dimensao_pneu = " + ed.getOid_Dimensao_Pneu()+ " ";
			  if (ed.getOid_Fabricante_Pneu()>0)
				  sql += " and p.oid_fabricante_pneu = " + ed.getOid_Fabricante_Pneu()+ " ";
			  if (ed.getNr_Mm_Sulco_Maior()>0)
				  sql += " and p.mm_Atual >= " + ed.getNr_Mm_Sulco_Maior()+ " ";
			  if (ed.getNr_Mm_Sulco_Menor()>0)
				  sql += " and p.mm_Atual <= " + ed.getNr_Mm_Sulco_Menor()+ " ";
			  if (doValida(ed.getDt_Inicial_Sucateamento())) {
				  sql += " and p.dt_Sucateamento >= '" + ed.getDt_Inicial_Sucateamento()+ "' ";
			  }
			  if (doValida(ed.getDt_Final_Sucateamento())) {
				  sql += " and p.dt_Sucateamento <= '" + ed.getDt_Final_Sucateamento()+ "' ";
			  }
			  if (doValida(ed.getDt_Venda())) {
				  sql += " and p.dt_Venda = '" + ed.getDt_Venda()+ "' ";
			  }
			  if (doValida (ed.getNr_Fogo())) {
				  sql += " AND p.nr_Fogo like '" + ed.getNr_Fogo() + "%'";
			  }
			  if (doValida (ed.getTx_Comentario_Venda())) {
				  sql += " AND p.tx_Comentario_Venda like '" + ed.getTx_Comentario_Venda() + "%'";
			  }
			  if ("true".equals(ed.getDm_Virada())){
				  sql += " and p.vl_Venda is not null " ;
			  }
		  }
		  if ("VENDIDO".equals(ed.getDt_Venda())) { // Busca somente pneus vendidos
			  sql += " and dt_Venda is not null ";
			  if (doValida(ed.getDt_Inicial_Sucateamento())) {
				  sql += " and p.dt_Venda >= '" + ed.getDt_Inicial_Sucateamento()+ "' ";
			  }
			  if (doValida(ed.getDt_Final_Sucateamento())) {
				  sql += " and p.dt_Venda <= '" + ed.getDt_Final_Sucateamento()+ "' ";
			  }
		  }
		  if ("1".equals(ed.getDm_CP_Selecao())) {
			  sql += " AND p.dm_Controle_Parcial = 'true' " ;
		  }	else
		  if ("2".equals(ed.getDm_CP_Selecao())) {
			  sql += " AND p.dm_Controle_Parcial = 'false' " ;
		  }
		  if (ed.getOid_Dimensao_Pneu() > 0) {
			  sql += " AND p.oid_dimensao_Pneu = " + ed.getOid_Dimensao_Pneu();
		  }
		  if (ed.getOid_Tipo_Pneu() > 0) {
			  sql += " AND p.oid_Tipo_Pneu = " + ed.getOid_Tipo_Pneu();
		  }
		  if (ed.getOid_Local_Estoque() > 0) {
			  sql += " AND p.oid_Local_Estoque = " + ed.getOid_Local_Estoque();
		  }
		  if (ed.getOid_Pneu () > 0) {
			  sql += " AND p.oid_pneu = " + ed.getOid_Pneu ();
		  }
		  if (doValida(ed.getCd_Item_Estoque()) ) {
			  sql += " AND p.cd_item_estoque like '" + ed.getCd_Item_Estoque() + "%'";
		  }
		  if (ed.getNr_Nota_Fiscal() > 0) {
			  sql += " AND p.nr_nota_fiscal = '" + ed.getNr_Nota_Fiscal() + "'";
		  }
		  if (doValida (ed.getCD_Pneu ())) {
			  sql += " AND p.cd_pneu like '" + ed.getCD_Pneu () + "%'";
		  }
		  if (doValida (ed.getNm_Modelo_Pneu())) {
			  sql += " AND m.nm_modelo_pneu like '" + ed.getNm_Modelo_Pneu() + "%'";
		  }
		  if (doValida (ed.getNm_Dimensao_Pneu())) {
			  sql += " AND d.nm_dimensao_pneu like '" + ed.getNm_Dimensao_Pneu() + "%'";
		  }
		  if (doValida (ed.getNm_Fabricante_Pneu())) {
			  sql += " AND f.nm_fabricante_pneu like '" + ed.getNm_Fabricante_Pneu() + "%'";
		  }
		  if (doValida (ed.getNm_Tipo_Pneu())) {
			  sql += " AND t.nm_tipo_pneu like '" + ed.getNm_Tipo_Pneu() + "%'";
		  }
		  if (doValida (ed.getNR_Fabrica ())) {
			  sql += " AND p.nr_fabrica like '" + ed.getNR_Fabrica () + "%'";
		  }
		  if (ed.getOid_Fabricante_Pneu () > 0) {
			  sql += " AND p.oid_fabricante_pneu = " + ed.getOid_Fabricante_Pneu ();
		  }
		  if (ed.getOid_Modelo_Pneu () > 0) {
			  sql += " AND m.oid_modelo_pneu = " + ed.getOid_Modelo_Pneu ();
		  }
		  if (ed.getOid_Modelo_Pneu () > 0) {
			  sql += " AND p.oid_modelo_pneu = " + ed.getOid_Modelo_Pneu ();
		  }
		  if (doValida (ed.getDM_Localizacao ())) {
			  sql += " AND p.dm_localizacao = '" + ed.getDM_Localizacao () + "'";
		  }
		  if (doValida (ed.getTx_Dot())) {
			  sql += " AND p.tx_dot like '" + ed.getTx_Dot() + "%'";
		  }
		  if (doValida (ed.getDM_Situacao ())) {
			  sql += " AND p.dm_situacao = '" + ed.getDM_Situacao () + "'";
		  }
		  if (ed.getKM_Atual () > 0) {
			  sql += " AND p.km_atual = " + ed.getKM_Atual ();
		  }
		  if (doValida (ed.getOid_Veiculo ())) {
			  sql += " AND v.oid_veiculo = '" + ed.getOid_Veiculo () + "'";
		  }
		  if (doValida (ed.getNr_Serie())) {
			  sql += " AND p.nr_serie like '" + ed.getNr_Serie() + "%'";
		  }
		  if (doValida (ed.getNotINTO_Loc ())) {
			  sql += " AND p.DM_Localizacao NOT IN ('" + ed.getNotINTO_Loc () + "')";
		  }
		  if (doValida (ed.getNotINTO_Sit ())) {
			  sql += " AND p.DM_Situacao NOT IN ('" + ed.getNotINTO_Sit () + "')";
		  }
		  if (doValida(ed.getNr_Fogo())){
			  sql += " AND p.nr_Fogo = '" + ed.getNr_Fogo() + "' ";
		  }
		  if (doValida(ed.getDm_Eixo())){
			  sql += " AND p.dm_Eixo = '" + ed.getDm_Eixo() + "' ";
		  }
		  if ("true".equals(ed.getDm_Considera_Sucateados())){
			  sql += " and dt_Sucateamento is not null ";
		  }
		  if (doValida(ed.getDt_Inicial_Compra())) {
			  sql += " and p.dt_Nota_Fiscal > '" + ed.getDt_Inicial_Compra()+ "' ";
		  }
		  if (doValida(ed.getDt_Final_Compra())) {
			  sql += " and p.dt_Nota_Fiscal < '" + ed.getDt_Final_Compra()+ "' ";
		  }
		  if (doValida(ed.getOid_Fornecedor())) {
			  sql += " AND p.oid_Fornecedor = '" + ed.getOid_Fornecedor() +"' ";
		  }
		  if (doValida(ed.getDt_Entrada())) {
			  sql += " AND p.dt_entrada is not null " ;
		  }

		  //select para a tela "pneus em estoque", para
		  //buscar com radiobutton as vidas dos pneus
		  if (ed.getNr_Vida() > 9 && ed.getNr_Vida()<20) {
			  sql += " AND p.nr_Vida = " + (ed.getNr_Vida()-10);
		  }else if (ed.getNr_Vida() == 20) {
			  sql += " AND p.nr_Vida >= " + 0;
		  }
	  }
	  sql += " ORDER BY " ;
	  if ("DFM".equals(ed.getOrdernaPor())) { // Ordenação do sql
		  sql +="d.nm_dimensao_pneu," +
		  		"f.nm_fabricante_pneu," +
		  		"m.nm_modelo_pneu, ";
	  }
	  sql += " p.nr_Fogo";

 	  ResultSet rs = this.executaSQL.executarConsulta(sql);
 	  try {
		  while (rs.next ()) {
		    	  list.add(populaRegistro(rs));
		  }
		  return list;
	  }
	  catch (Exception e) {
		  throw new Excecoes (e.getMessage () , e , this.getClass().getName(), "lista ()");
	  }
  }

  public ArrayList listaJSTL (PneuED ed) throws Excecoes {
	  ArrayList list = new ArrayList ();
	  sql = "SELECT "+
	  "p.*, " +
	  "p.usuario_Stamp as usu_Stmp " +
	  ",p.dt_Stamp as dt_Stmp " +
	  ",p.dm_Stamp as dm_Stmp, " +
	  "f.cd_marca_pneu as cd_fabricante_pneu, " +
	  "m.cd_modelo_pneu, " +
	  "v.nr_placa, " +
	  "v.nr_frota, " +
	  "f.nm_marca_pneu as nm_fabricante_pneu, " +
	  "m.nm_modelo_pneu, " +
	  "d.nm_dimensao_pneu, " +
	  "t.nm_tipo_pneu," +
	  "pe.nm_razao_social " +
	  "FROM " +
	  "pneus p " +
	  "left join veiculos v " +
	  " on v.oid_veiculo = p.oid_veiculo " +
	  "left join fornecedores fo " +
	  "	left join pessoas pe " +
	  " on pe.oid_pessoa = fo.oid_pessoa " +
	  " on fo.oid_fornecedor = p.oid_fornecedor, " +
	  "marcas_pneus f, " +
	  "modelos_pneus m, " +
	  "dimensoes_pneus d, " +
	  "tipos_pneus t " +
	  "WHERE " +
//	  "p.oid_fornecedor = fo.oid_fornecedor " +
//	  "AND " +
//	  "fo.oid_pessoa = pe.oid_pessoa " +
//	  "AND " +
	  "p.oid_fabricante_pneu = f.oid_marca_pneu " +
	  "AND " +
	  "p.oid_modelo_pneu = m.oid_modelo_pneu " +
	  "AND " +
	  "p.oid_dimensao_pneu = d.oid_dimensao_pneu " +
	  "AND " +
	  "p.oid_tipo_pneu = t.oid_tipo_pneu " ;
	  if (ed.getOid_Pneu () > 0) { // Se informado o oid_Pneu busca somente por isso....
		  sql += " AND p.oid_pneu = " + ed.getOid_Pneu () + " ";
	  } else {
		  if (ed.getOid_Empresa() > 0) {
			  sql += " AND p.oid_Empresa = " + ed.getOid_Empresa();
		  }
		  if ("ESTOQUE".equals(ed.getDt_Estoque())) {
			  sql += " AND dt_estoque > '01/01/2001' ";
			  if (doValida(ed.getDt_Final_Compra())) {
				  sql += " and p.dt_estoque <= '" + ed.getDt_Final_Compra()+ "' ";
			  }
			  if (doValida(ed.getOid_Fornecedor_Recapagem())) {
				  sql += " AND p.dt_estoque = '" + ed.getOid_Fornecedor_Recapagem() +"' ";
			  }
		  }
		  if ("RECAPAGEM".equals(ed.getDt_Remessa_Recapagem())) { // Busca somente pneus em recapagem
			  sql += " AND dt_Remessa_Recapagem > '01/01/2001' ";
			  if (doValida(ed.getDt_Inicial_Compra())) {
				  sql += " and p.dt_Remessa_Recapagem >= '" + ed.getDt_Inicial_Compra()+ "' ";
			  }
			  if (doValida(ed.getDt_Final_Compra())) {
				  sql += " and p.dt_Remessa_Recapagem <= '" + ed.getDt_Final_Compra()+ "' ";
			  }
			  if (doValida(ed.getOid_Fornecedor_Recapagem())) {
				  sql += " AND oid_Fornecedor_Recapagem = '" + ed.getOid_Fornecedor_Recapagem() +"' ";
			  }
		  }
		  if ("SUCATEAMENTO".equals(ed.getDt_Sucateamento())) { // Busca somente pneus sucateados
			  sql += " and dt_Sucateamento > '01/01/2001' ";
			  if (ed.getOid_Motivo_Sucateamento()>0)
				  sql += " and p.oid_motivo_sucateamento = " + ed.getOid_Motivo_Sucateamento()+ " ";
			  if (ed.getOid_Dimensao_Pneu()>0)
				  sql += " and p.oid_dimensao_pneu = " + ed.getOid_Dimensao_Pneu()+ " ";
			  if (ed.getOid_Fabricante_Pneu()>0)
				  sql += " and p.oid_fabricante_pneu = " + ed.getOid_Fabricante_Pneu()+ " ";
			  if (ed.getNr_Mm_Sulco_Maior()>0)
				  sql += " and p.mm_Atual >= " + ed.getNr_Mm_Sulco_Maior()+ " ";
			  if (ed.getNr_Mm_Sulco_Menor()>0)
				  sql += " and p.mm_Atual <= " + ed.getNr_Mm_Sulco_Menor()+ " ";
			  if (doValida(ed.getDt_Inicial_Sucateamento())) {
				  sql += " and p.dt_Sucateamento >= '" + ed.getDt_Inicial_Sucateamento()+ "' ";
			  }
			  if (doValida(ed.getDt_Final_Sucateamento())) {
				  sql += " and p.dt_Sucateamento <= '" + ed.getDt_Final_Sucateamento()+ "' ";
			  }
			  if (doValida(ed.getDt_Inicial_Compra())) {
				  sql += " and p.dt_Sucateamento >= '" + ed.getDt_Inicial_Compra()+ "' ";
			  }
			  if (doValida(ed.getDt_Final_Compra())) {
				  sql += " and p.dt_Sucateamento <= '" + ed.getDt_Final_Compra()+ "' ";
			  }
			  if (doValida(ed.getDt_Venda())) {
				  sql += " and p.dt_Venda = '" + ed.getDt_Venda()+ "' ";
			  }
			  if (doValida (ed.getNr_Fogo())) {
				  sql += " AND p.nr_Fogo like '" + ed.getNr_Fogo() + "%'";
			  }
			  if (doValida (ed.getTx_Comentario_Venda())) {
				  sql += " AND p.tx_Comentario_Venda like '" + ed.getTx_Comentario_Venda() + "%'";
			  }
			  if ("true".equals(ed.getDm_Virada())){
				  sql += " and p.vl_Venda is not null " ;
			  }
		  }
		  if ("VENDIDO".equals(ed.getDt_Venda())) { // Busca somente pneus vendidos
			  sql += " and dt_Venda > '01/01/2001' ";
			  if (doValida(ed.getDt_Inicial_Sucateamento())) {
				  sql += " and p.dt_Venda >= '" + ed.getDt_Inicial_Sucateamento()+ "' ";
			  }
			  if (doValida(ed.getDt_Final_Sucateamento())) {
				  sql += " and p.dt_Venda <= '" + ed.getDt_Final_Sucateamento()+ "' ";
			  }
		  }
		  if ("1".equals(ed.getDm_CP_Selecao())) {
			  sql += " AND p.dm_Controle_Parcial = 'true' " ;
		  }	else
		  if ("2".equals(ed.getDm_CP_Selecao())) {
			  sql += " AND p.dm_Controle_Parcial = 'false' " ;
		  }
		  if (ed.getOid_Dimensao_Pneu() > 0) {
			  sql += " AND p.oid_dimensao_Pneu = " + ed.getOid_Dimensao_Pneu();
		  }
		  if (ed.getOid_Tipo_Pneu() > 0) {
			  sql += " AND p.oid_Tipo_Pneu = " + ed.getOid_Tipo_Pneu();
		  }
		  if (ed.getOid_Local_Estoque() > 0) {
			  sql += " AND p.oid_Local_Estoque = " + ed.getOid_Local_Estoque();
		  }
		  if (ed.getOid_Pneu () > 0) {
			  sql += " AND p.oid_pneu = " + ed.getOid_Pneu ();
		  }
		  if (doValida(ed.getCd_Item_Estoque()) ) {
			  sql += " AND p.cd_item_estoque like '" + ed.getCd_Item_Estoque() + "%'";
		  }
		  if (doValida(String.valueOf(ed.getNr_Nota_Fiscal()))) {
			  sql += " AND p.nr_nota_fiscal = '" + ed.getNr_Nota_Fiscal() + "'";
		  }
		  if (doValida (ed.getCD_Pneu ())) {
			  sql += " AND p.cd_pneu like '" + ed.getCD_Pneu () + "%'";
		  }
		  if (doValida (ed.getNm_Modelo_Pneu())) {
			  sql += " AND m.nm_modelo_pneu like '" + ed.getNm_Modelo_Pneu() + "%'";
		  }
		  if (doValida (ed.getNm_Dimensao_Pneu())) {
			  sql += " AND d.nm_dimensao_pneu like '" + ed.getNm_Dimensao_Pneu() + "%'";
		  }
		  if (doValida (ed.getNm_Fabricante_Pneu())) {
			  sql += " AND f.nm_marca_pneu like '" + ed.getNm_Fabricante_Pneu() + "%'";
		  }
		  if (doValida (ed.getNm_Tipo_Pneu())) {
			  sql += " AND t.nm_tipo_pneu like '" + ed.getNm_Tipo_Pneu() + "%'";
		  }
		  if (doValida (ed.getNR_Fabrica ())) {
			  sql += " AND p.nr_fabrica like '" + ed.getNR_Fabrica () + "%'";
		  }
		  if (ed.getOid_Fabricante_Pneu () > 0) {
			  sql += " AND p.oid_fabricante_pneu = " + ed.getOid_Fabricante_Pneu ();
		  }
		  if (ed.getOid_Modelo_Pneu () > 0) {
			  sql += " AND m.oid_modelo_pneu = " + ed.getOid_Modelo_Pneu ();
		  }
		  if (ed.getOid_Modelo_Pneu () > 0) {
			  sql += " AND p.oid_modelo_pneu = " + ed.getOid_Modelo_Pneu ();
		  }
		  if (doValida (ed.getDM_Localizacao ())) {
			  sql += " AND p.dm_localizacao = '" + ed.getDM_Localizacao () + "'";
		  }
		  if (doValida (ed.getTx_Dot())) {
			  sql += " AND p.tx_dot like '" + ed.getTx_Dot() + "%'";
		  }
		  if (doValida (ed.getDM_Situacao ())) {
			  sql += " AND p.dm_situacao = '" + ed.getDM_Situacao () + "'";
		  }
		  if (ed.getKM_Atual () > 0) {
			  sql += " AND p.km_atual = " + ed.getKM_Atual ();
		  }
		  if (doValida (ed.getOid_Veiculo ())) {
			  sql += " AND v.oid_veiculo = '" + ed.getOid_Veiculo () + "'";
		  }
		  if (doValida (ed.getNr_Serie())) {
			  sql += " AND p.nr_serie like '" + ed.getNr_Serie() + "%'";
		  }
		  if (doValida (ed.getNotINTO_Loc ())) {
			  sql += " AND p.DM_Localizacao NOT IN ('" + ed.getNotINTO_Loc () + "')";
		  }
		  if (doValida (ed.getNotINTO_Sit ())) {
			  sql += " AND p.DM_Situacao NOT IN ('" + ed.getNotINTO_Sit () + "')";
		  }
		  if (doValida(ed.getNr_Fogo())){
			  sql += " AND p.nr_Fogo = '" + ed.getNr_Fogo() + "' ";
		  }
		  if (doValida(ed.getDm_Eixo())){
			  sql += " AND p.dm_Eixo = '" + ed.getDm_Eixo() + "' ";
		  }
		  if ("true".equals(ed.getDm_Considera_Sucateados())){
			  sql += " and dt_Sucateamento > '01/01/2001' ";
		  }
		  if (doValida(ed.getDt_Inicial_Compra())) {
			  sql += " and p.dt_Nota_Fiscal > '" + ed.getDt_Inicial_Compra()+ "' ";
		  }
		  if (doValida(ed.getDt_Final_Compra())) {
			  sql += " and p.dt_Nota_Fiscal < '" + ed.getDt_Final_Compra()+ "' ";
		  }
		  if (doValida(ed.getOid_Fornecedor())) {
			  sql += " AND p.oid_Fornecedor = '" + ed.getOid_Fornecedor() +"' ";
		  }
		  if (doValida(ed.getDt_Entrada())) {
			  sql += " AND p.dt_entrada > '01/01/2001' " ;
		  }

		  //select para a tela "pneus em estoque", para
		  //buscar com radiobutton as vidas dos pneus
		  if (ed.getNr_Vida() > 9 && ed.getNr_Vida()<20) {
			  sql += " AND p.nr_Vida = " + (ed.getNr_Vida()-10);
		  }else if (ed.getNr_Vida() == 20) {
			  sql += " AND p.nr_Vida >= " + 0;
		  } else {
			  if (ed.getNr_Vida() >= 0 && ed.getNr_Vida() != 99) {
				  sql += " AND p.nr_Vida = " + ed.getNr_Vida();
			  }
		  }
	  }
	  sql += " ORDER BY " ;
	  if ("DFM".equals(ed.getOrdernaPor())) { // Ordenação do sql
		  sql +="d.nm_dimensao_pneu," +
		  		"f.nm_marca_pneu," +
		  		"m.nm_modelo_pneu, ";
	  }
	  if (doValida (ed.getOid_Veiculo ())) {
		  sql += " p.dm_eixo, p.dm_posicao, ";
	  }
	  sql += " p.nr_Fogo";
System.out.println(sql);

 	  ResultSet rs = this.executaSQL.executarConsulta(sql);
 	  try {
		  while (rs.next ()) {
		    	  list.add(populaRegistro(rs));
		  }
		  return list;
	  }
	  catch (Exception e) {
		  throw new Excecoes (e.getMessage () , e , this.getClass().getName(), "lista ()");
	  }
  }


  public ArrayList listaPar (PneuED ed) throws Excecoes {
	  ArrayList list = new ArrayList ();
	  sql = "SELECT "+
	  "p.*, " +
	  "f.cd_fabricante_pneu, " +
	  "m.cd_modelo_pneu, " +
	  "f.nm_fabricante_pneu, " +
	  "m.nm_modelo_pneu, " +
	  "d.nm_dimensao_pneu, " +
	  "t.nm_tipo_pneu," +
	  "fo.nm_razao_social " +
	  "FROM " +
	  "pneus p, " +
	  "fabricantes_pneus f, " +
	  "modelos_pneus m, " +
	  "dimensoes_pneus d, " +
	  "tipos_pneus t, " +
	  "fornecedores fo " +
	  "WHERE " +
	  "p.oid_fornecedor = fo.oid_fornecedor " +
	  "AND " +
	  "p.oid_fabricante_pneu = f.oid_fabricante_pneu " +
	  "AND " +
	  "p.oid_modelo_pneu = m.oid_modelo_pneu " +
	  "AND " +
	  "p.oid_dimensao_pneu = d.oid_dimensao_pneu " +
	  "AND " +
	  "(p.dt_estoque is not null or p.dm_posicao like 'STP%')" +
	  " AND p.oid_pneu != " + ed.getOid_Pneu() +
	  "AND " +
	  "p.oid_tipo_pneu = t.oid_tipo_pneu " ;

	  if (ed.getOid_Empresa() > 0) {
		  sql += " AND p.oid_Empresa = " + ed.getOid_Empresa();
	  }
  	  if (doValida(ed.getNr_Fogo())){
		  sql += " AND p.nr_Fogo = '" + ed.getNr_Fogo() + "' ";
	  }
  	  if (doValida (ed.getNm_Dimensao_Pneu())) {
		  sql += " AND d.nm_dimensao_pneu like '" + ed.getNm_Dimensao_Pneu() + "%'";
	  }
	  if (doValida (ed.getNm_Fabricante_Pneu())) {
		  sql += " AND f.nm_fabricante_pneu like '" + ed.getNm_Fabricante_Pneu() + "%'";
	  }
	  if ("true".equals(ed.getOp_Vida())){
		  sql += " AND p.nr_vida = " + ed.getNr_Vida();
	  }
	  if ("true".equals(ed.getOp_Perimetro())){
		  if (ed.getNr_Diferenca_Perimetro() > 0){
			  sql += " AND p.nr_perimetro  >= " + (ed.getNr_Perimetro() - ed.getNr_Diferenca_Perimetro());
			  sql += " AND p.nr_perimetro  <= " + (ed.getNr_Perimetro() + ed.getNr_Diferenca_Perimetro());
		  }else{
			  sql += " AND p.nr_perimetro = " + ed.getNr_Perimetro();
		  }
	  }
	  if ("true".equals(ed.getOp_MM())){
		  if (ed.getNr_Diferenca_MM() > 0){
			  sql += " AND p.mm_atual  >= " + (ed.getMM_Atual() - ed.getNr_Diferenca_MM());
			  sql += " AND p.mm_atual  <= " + (ed.getMM_Atual() + ed.getNr_Diferenca_MM());
		  }else{
			  sql += " AND p.mm_atual = " + ed.getMM_Atual();
		  }
	  }
	  sql += " ORDER BY " ;
	  sql += " p.nr_Fogo";
	  ResultSet rs = this.executaSQL.executarConsulta(sql);
	  boolean inclui_lista = true;
 	  try {
		  while (rs.next ()) {
			  PneuED pneuED = new PneuED();
			  pneuED.setOid_Pneu(rs.getInt("oid_Pneu"));
			  pneuED.setNr_Fogo(rs.getString("Nr_Fogo"));
			  pneuED.setNm_Dimensao_Pneu(rs.getString("nm_Dimensao_Pneu"));
			  pneuED.setNm_Fabricante_Pneu(rs.getString("Nm_Fabricante_Pneu"));
			  pneuED.setNr_Vida(rs.getLong("nr_Vida"));
			  pneuED.setOid_Modelo_Pneu(rs.getInt("oid_Modelo_Pneu"));
			  pneuED.setNr_Perimetro(rs.getLong("Nr_Perimetro"));
			  pneuED.setMM_Atual(rs.getDouble("MM_Atual"));
			  inclui_lista = true;

			  if (doValida(rs.getString("dt_Estoque"))){
				  pneuED.setOid_Local_Estoque(rs.getLong("Oid_Local_Estoque"));
				  Local_EstoqueED loc_estED = new Local_EstoqueED();
				  loc_estED.setOid_Local_Estoque(pneuED.getOid_Local_Estoque());
				  loc_estED = new Local_EstoqueBD(this.executaSQL).getByRecord(loc_estED);
				  if (doValida(loc_estED.getNm_Local_Estoque())){
					  pneuED.setNm_Local_Estoque(loc_estED.getNm_Local_Estoque());
				  }
			  } else
			  if ( ("STP1".equals(rs.getString("dm_Posicao")))  || ("STP2".equals(rs.getString("dm_Posicao"))) ) {
				  pneuED.setOid_Veiculo(rs.getString("Oid_Veiculo"));
				  VeiculoED veicED = new VeiculoED();
				  veicED.setOid_Veiculo(pneuED.getOid_Veiculo());
				  veicED = new VeiculoBD(this.executaSQL).getByRecord(veicED);
				  if (doValida(veicED.getNr_Frota())){
					  pneuED.setNm_Local_Estoque(veicED.getNr_Frota() + " - " + rs.getString("dm_Posicao"));
				  }
			  }
			  if ("trueL".equals(ed.getDm_Modelo_Pneu())){
				  if (pneuED.getNr_Vida()> 0){
					  RecapagemED recED = new RecapagemED();
					  recED.setOid_Pneu(pneuED.getOid_Pneu());
					  recED = new RecapagemBD(this.executaSQL).getLastRecord(recED);
					  if (recED.getOid_Banda() == ed.getOid_Modelo_Pneu()){ // Recapado, compara o modelo do pneu lido no últiomo recape co o modelo do pneu na tela
						  pneuED.setNm_Modelo_Pneu(recED.getNm_Banda());
						  pneuED.setOid_Modelo_Pneu((int)recED.getOid_Banda());
					  }else{
						  if (("trueL".equals(ed.getDm_Modelo_Pneu())) && (recED.getOid_Banda() != ed.getOid_Modelo_Pneu()))  {
							  inclui_lista = false;
						  }else{
							  if (pneuED.getOid_Modelo_Pneu() == ed.getOid_Modelo_Pneu()){ // novo, modelo do pneu lido no banco com o modelo do pneu Dna tela
								  pneuED.setNm_Modelo_Pneu(rs.getString("nm_Modelo_Pneu"));
								  pneuED.setOid_Modelo_Pneu(rs.getInt("oid_Modelo_Pneu"));
							  }else{
								  if (("trueL".equals(ed.getDm_Modelo_Pneu())) && (pneuED.getOid_Modelo_Pneu() != ed.getOid_Modelo_Pneu()))  {
									  inclui_lista = false;
								  }
							  }
						  }
					  }
				  }
			  }
			  if ("trueC".equals(ed.getDm_Modelo_Pneu())){
				  if (pneuED.getNr_Vida()> 0){
					  RecapagemED recapED = new RecapagemED();
					  recapED.setOid_Pneu(pneuED.getOid_Pneu());
						recapED = new RecapagemBD(this.executaSQL).getLastRecord(recapED);
						pneuED.setNm_Modelo_Pneu(recapED.getNm_Banda());
						pneuED.setOid_Modelo_Pneu((int)recapED.getOid_Banda());
			  	} else {
			  		if (pneuED.getNr_Vida() == 0){
						pneuED.setNm_Modelo_Pneu(rs.getString("nm_Modelo_Pneu"));
						pneuED.setOid_Modelo_Pneu(rs.getInt("oid_Modelo_Pneu"));
			  		}
			  	}
			  }
			  if (inclui_lista == true){
				list.add(pneuED);
			  }
		  }
		  return list;
	  }
	  catch (Exception e) {
		  throw new Excecoes (e.getMessage () , e , this.getClass().getName(), "lista ()");
	  }
  }


	public ArrayList listaUltima_Recapagem(PneuED ed) throws Excecoes {

		ArrayList list = new ArrayList();
		try {
			sql = "SELECT " +
			"* " +
	  		",Pneus.usuario_Stamp as usu_Stmp " +
	  		",Pneus.dt_Stamp as dt_Stmp " +
	  		",Pneus.dm_Stamp as dm_Stmp " +
			"FROM " +
			"Pneus  " +
			"left join fabricantes_pneus on pneus.oid_fabricante_ultima_recapagem = fabricantes_pneus.oid_fabricante_pneu "  +
			"left join modelos_pneus on pneus.oid_modelo_pneu_ultima_recapagem = modelos_pneus.oid_modelo_pneu "  +
			"left join fornecedores on pneus.oid_fornecedor_ultima_recapagem = fornecedores.oid_fornecedor "  +
			"WHERE " +
			" Pneus.oid_Empresa = " + ed.getOid_Empresa() + " " ;
			 if ("true".equals(ed.getDm_Virada())){
				  sql += " and Pneus.vl_Ultima_Recapagem is not null " ;
			  }
			 if (doValida(ed.getOid_Fornecedor_Ultima_Recapagem ())) {
				  sql += " and pneus.oid_Fornecedor_Ultima_Recapagem = " + ed.getOid_Fornecedor_Ultima_Recapagem();
			  }
			  if (ed.getOid_Fabricante_Ultima_Recapagem () > 0) {
				  sql += " and pneus.oid_Fabricante_Ultima_Recapagem = " + ed.getOid_Fabricante_Ultima_Recapagem();
			  }
			  if (ed.getOid_Modelo_Pneu_Ultima_Recapagem () > 0) {
				  sql += " and pneus.oid_Modelo_Pneu_Ultima_Recapagem = " + ed.getOid_Modelo_Pneu_Ultima_Recapagem();
			  }
			  if (doValida(ed.getNr_Fogo())){
				  sql += " and pneus.nr_Fogo like '" + ed.getNr_Fogo() + "%' ";
			  }
			sql += " ORDER BY " +
			"Pneus.nr_fogo";
			ResultSet res = this.executaSQL.executarConsulta(sql);
			while (res.next()) {
				list.add(populaRegistroUltima_Recapagem(res));
			}
			return list;
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"lista()");
		}
	}

  public PneuED getByRecord (PneuED ed) throws Excecoes {
	  return this.doGetByRecord (ed);
  }

  public PneuED getByRecord (int oid) throws Excecoes {
    return this.doGetByRecord (new PneuED (oid , "" , "" , 0 , "" , "" , 0 , "" , "" , "" , "" , "" , 0 , 0 , "" , "" , "", 0));
  }

  public PneuED getByRecord2 (int oid) throws Excecoes {
	    return this.doGetByRecord2 (new PneuED (oid , "" , "" , 0 , "" , "" , 0 , "" , "" , "" , "" , "" , 0 , 0 , "" , "" , "", 0));
	  }

  public PneuED getByCodigo (String codigo) throws Excecoes {
    return this.doGetByRecord (new PneuED (0 , codigo , "" , 0 , "" , "" , 0 , "" , "" , "" , "" , "" , 0 , 0 , "" , "" , "", 0));
  }

  public PneuED getByNrFabrica (String nrFabrica) throws Excecoes {
    return this.doGetByRecord (new PneuED (0 , "" , nrFabrica , 0 , "" , "" , 0 , "" , "" , "" , "" , "" , 0 , 0 , "" , "" , "", 0));
  }

  private PneuED doGetByRecord (PneuED ed) throws Excecoes {
    ArrayList lista = this.listaJSTL(ed);
    Iterator iterator = lista.iterator ();
    if (iterator.hasNext ()) {
      return (PneuED) iterator.next ();
    }
    else {
      return new PneuED ();
    }
  }

  private PneuED doGetByRecord2 (PneuED ed) throws Excecoes {
	    ArrayList lista = this.lista_Consulta2 (ed);
	    Iterator iterator = lista.iterator ();
	    if (iterator.hasNext ()) {
	      return (PneuED) iterator.next ();
	    }
	    else {
	      return new PneuED ();
	    }
	  }

  public PneuED getByRecordOL (PneuED ed) throws Excecoes {
	ArrayList lista = this.listaJSTL(ed);
	Iterator iterator = lista.iterator ();
	if (iterator.hasNext ()) {
		return (PneuED) iterator.next ();
	}
	else {
		return new PneuED ();
	}
  }

  public PneuED getByRecordJSTL (PneuED ed) throws Excecoes {
		ArrayList lista = this.listaJSTL(ed);
		Iterator iterator = lista.iterator ();
		if (iterator.hasNext ()) {
			return (PneuED) iterator.next ();
		}
		else {
			return new PneuED ();
		}
	  }

  public ArrayList lista_Consulta (PneuED ed) throws Excecoes {
	    ArrayList list = new ArrayList ();
	    String sql = "select p.oid_pneu, " +
	        "   p.cd_pneu, " +
	        "   p.nr_fabrica, " +
	        "   p.oid_fabricante_pneu, " +
	        "   f.cd_fabricante_pneu, " +
	        "   f.nm_fabricante_pneu, " +
	        "   p.oid_modelo_pneu, " +
	        "   m.cd_modelo_pneu, " +
	        "   m.nm_modelo_pneu, " +
	        "   p.dm_localizacao, " +
	        "   p.dm_situacao, " +
	        "   p.dm_posicao, " +
	        "   p.km_atual, " +
	        "   p.mm_atual, " +
	        "   p.oid_veiculo, " +
	        "   p.oid_estoque, " +
	        "   v.nr_placa, " +
	        "   v.nr_frota" +
	        " from pneus p " +
	        "      left join veiculos v" +
	        "        on v.oid_veiculo = p.oid_veiculo," +
	        "      fabricantes_pneus f, " +
	        "      modelos_pneus m " +
	        " where p.oid_fabricante_pneu = f.oid_fabricante_pneu" +
	        "   and p.oid_modelo_pneu = m.oid_modelo_pneu"; //+
	    if (ed.getOid_Pneu () > 0) {
	      sql += " and p.oid_pneu = " + ed.getOid_Pneu ();
	    }
	    if (doValida (ed.getCD_Pneu ())) {
	      sql += " and p.cd_pneu = '" + ed.getCD_Pneu () + "'";
	    }
	    if (doValida (ed.getNR_Fabrica ())) {
	      sql += " and p.nr_fabrica = '" + ed.getNR_Fabrica () + "'";
	    }
	    if (ed.getOid_Fabricante_Pneu () > 0) {
	      sql += " and f.oid_fabricante_pneu = " + ed.getOid_Fabricante_Pneu ();
	    }
	    if (ed.getOid_Modelo_Pneu () > 0) {
	      sql += " and m.oid_modelo_pneu = " + ed.getOid_Modelo_Pneu ();
	    }
	    if (doValida (ed.getDM_Localizacao ())) {
	      sql += " and p.dm_localizacao = '" + ed.getDM_Localizacao () + "'";
	    }
	    if (doValida (ed.getDM_Situacao ())) {
	      sql += " and p.dm_situacao = '" + ed.getDM_Situacao () + "'";
	    }
	    if (ed.getKM_Atual () > 0) {
	      sql += " and p.km_atual = " + ed.getKM_Atual ();
	    }
	    if (doValida (ed.getOid_Veiculo ())) {
	      sql += " and v.oid_veiculo = '" + ed.getOid_Veiculo () + "'";
	    }
	    if (doValida (ed.getNotINTO_Loc ())) {
	      sql += " AND p.DM_Localizacao NOT IN ('" + ed.getNotINTO_Loc () + "')";
	    }
	    if (doValida (ed.getNotINTO_Sit ())) {
	      sql += " AND p.DM_Situacao NOT IN ('" + ed.getNotINTO_Sit () + "')";
	    }

	    ResultSet rs = executaSQL.executarConsulta (sql);
	    try {
	      while (rs.next ()) {
	        list.add (new PneuED (rs.getInt ("oid_pneu") ,
	                              rs.getString ("cd_pneu") ,
	                              rs.getString ("nr_fabrica") ,
	                              rs.getInt ("oid_fabricante_pneu") ,
	                              rs.getString ("cd_fabricante_pneu") ,
	                              rs.getString ("nm_fabricante_pneu") ,
	                              rs.getInt ("oid_modelo_pneu") ,
	                              rs.getString ("cd_modelo_pneu") ,
	                              rs.getString ("nm_modelo_pneu") ,
	                              rs.getString ("dm_localizacao") ,
	                              rs.getString ("dm_situacao") ,
	                              rs.getString ("DM_Posicao") ,
	                              rs.getInt ("km_atual") ,
	                              rs.getDouble ("MM_atual") ,
	                              rs.getString ("oid_veiculo") ,
	                              rs.getString ("nr_placa") ,
	                              rs.getString ("nr_frota") ,
	                              rs.getLong ("oid_estoque")));
	      }
	      return list;
	    }
	    catch (SQLException e) {
	      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "lista");
	    }
	    finally {
	      try {
	        rs.close ();
	      }
	      catch (SQLException e) {
	        e.printStackTrace ();
	      }
	      try {
	        rs.getStatement ().close ();
	      }
	      catch (SQLException e) {
	        e.printStackTrace ();
	      }
	    }
	  }

  public ArrayList lista_Consulta2 (PneuED ed) throws Excecoes {
	    ArrayList list = new ArrayList ();
	    String sql = "select p.oid_pneu, " +
	        "   p.cd_pneu, " +
	        "   p.nr_fabrica, " +
	        "   p.oid_fabricante_pneu, " +
	        "   fp.cd_fabricante_pneu, " +
	        "   fp.nm_fabricante_pneu, " +
	        "   p.oid_modelo_pneu, " +
	        "   mo.cd_modelo_pneu, " +
	        "   mo.nm_modelo_pneu, " +
	        "   p.dm_localizacao, " +
	        "   p.dm_situacao, " +
	        "   p.dm_posicao, " +
	        "   p.km_atual, " +
	        "   p.mm_atual, " +
	        "   p.oid_veiculo, " +
	        "   p.oid_estoque, " +
	        "   v.nr_placa, " +
	        "   v.nr_frota" +
	        " from movimentos_pneus mp," +
	        " pneus p " +
	        "      left join veiculos v" +
	        "        on v.oid_veiculo = p.oid_veiculo," +
	        "      fabricantes_pneus fp, " +
	        "      modelos_pneus mo " +
	        " where 	mo.oid_modelo_pneu=p.oid_modelo_pneu and" +
	        "   fp.oid_fabricante_pneu=p.oid_fabricante_pneu and" +
	        "	mp.oid_veiculo=v.oid_veiculo and" +
	        "	mp.oid_pneu=p.oid_pneu and" +
	        "	mp.dt_remocao <= 0 and" +
	        "	mp.dt_inclusao is not null"; //+
	    if (ed.getOid_Pneu () > 0) {
	      sql += " and p.oid_pneu = " + ed.getOid_Pneu ();
	    }
	    if (doValida (ed.getOid_Veiculo ())) {
	      sql += " and v.oid_veiculo = '" + ed.getOid_Veiculo () + "'";
	    }

	    ResultSet rs = executaSQL.executarConsulta (sql);
	    try {
	      while (rs.next ()) {
	        list.add (new PneuED (rs.getInt ("oid_pneu") ,
	                              rs.getString ("cd_pneu") ,
	                              rs.getString ("nr_fabrica") ,
	                              rs.getInt ("oid_fabricante_pneu") ,
	                              rs.getString ("cd_fabricante_pneu") ,
	                              rs.getString ("nm_fabricante_pneu") ,
	                              rs.getInt ("oid_modelo_pneu") ,
	                              rs.getString ("cd_modelo_pneu") ,
	                              rs.getString ("nm_modelo_pneu") ,
	                              rs.getString ("dm_localizacao") ,
	                              rs.getString ("dm_situacao") ,
	                              rs.getString ("DM_Posicao") ,
	                              rs.getInt ("km_atual") ,
	                              rs.getDouble ("MM_atual") ,
	                              rs.getString ("oid_veiculo") ,
	                              rs.getString ("nr_placa") ,
	                              rs.getString ("nr_frota") ,
	                              rs.getLong ("oid_estoque")));
	      }
	      return list;
	    }
	    catch (SQLException e) {
	      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "lista");
	    }
	    finally {
	      try {
	        rs.close ();
	      }
	      catch (SQLException e) {
	        e.printStackTrace ();
	      }
	      try {
	        rs.getStatement ().close ();
	      }
	      catch (SQLException e) {
	        e.printStackTrace ();
	      }
	    }
	  }

  public ArrayList listaDescrita (PneuED ed) throws Excecoes {
    ArrayList list = new ArrayList ();
			  sql = "SELECT " +
					"p.*, " +
				    "f.cd_fabricante_pneu, " +
				    "m.cd_modelo_pneu, " +
				    "v.nr_placa, " +
				    "v.nr_frota" +
				    "FROM " +
				    "pneus p " +
				    "left join veiculos v " +
				    "on v.oid_veiculo = p.oid_veiculo," +
				    "fabricantes_pneus f," +
				    "modelos_pneus m " +
				    "WHERE " +
				    "p.oid_fabricante_pneu = f.oid_fabricante_pneu" +
				    "AND " +
				    "p.oid_modelo_pneu = m.oid_modelo_pneu";
    if (ed.getOid_Pneu () > 0) {
      sql += " and p.oid_pneu = " + ed.getOid_Pneu ();
    }
    if (doValida (ed.getCD_Pneu ())) {
      sql += " and p.cd_pneu = '" + ed.getCD_Pneu () + "'";
    }
    if (doValida (ed.getNR_Fabrica ())) {
      sql += " and p.nr_fabrica = '" + ed.getNR_Fabrica () + "'";
    }
    if (ed.getOid_Fabricante_Pneu () > 0) {
      sql += " and f.oid_fabricante_pneu = " + ed.getOid_Fabricante_Pneu ();
    }
    if (ed.getOid_Modelo_Pneu () > 0) {
      sql += " and m.oid_modelo_pneu = " + ed.getOid_Modelo_Pneu ();
    }
    if (doValida (ed.getDM_Localizacao ())) {
      sql += " and p.dm_localizacao = '" + ed.getDM_Localizacao () + "'";
    }
    if (doValida (ed.getDM_Situacao ())) {
      sql += " and p.dm_situacao = '" + ed.getDM_Situacao () + "'";
    }
    if (ed.getKM_Atual () > 0) {
      sql += " and p.km_atual = " + ed.getKM_Atual ();
    }
    if (doValida (ed.getOid_Veiculo ())) {
      sql += " and v.oid_veiculo = '" + ed.getOid_Veiculo () + "'";
    }
    if (doValida (ed.getNotINTO_Loc ())) {
      sql += " AND p.DM_Localizacao NOT IN ('" + ed.getNotINTO_Loc () + "')";
    }
    if (doValida (ed.getNotINTO_Sit ())) {
      sql += " AND p.DM_Situacao NOT IN ('" + ed.getNotINTO_Sit () + "')";
    }
    sql += "ORDER BY " +
	"Pneus.nr_Fogo";

    ResultSet rs = executaSQL.executarConsulta (sql);
    try {
      while (rs.next ()) {
        list.add (new PneuED (rs.getInt("oid_pneu"),
                              rs.getString("cd_pneu"),
                              rs.getString("nr_fabrica"),
                              rs.getInt("oid_fabricante_pneu"),
                              rs.getString("cd_fabricante_pneu"),
                              rs.getString("nm_fabricante_pneu"),
                              rs.getInt("oid_modelo_pneu"),
                              rs.getString("cd_modelo_pneu"),
                              rs.getString("nm_modelo_pneu"),
                              this.getDesc_Localizacao (rs.getString("dm_localizacao")),
                              this.getDesc_Situacao (rs.getString("dm_situacao")),
                              this.getDesc_Posicao (rs.getString("DM_Posicao")),
                              rs.getInt("km_atual"),
                              rs.getDouble("mm_atual"),
                              rs.getString("oid_veiculo"),
                              rs.getString("nr_placa"),
                              rs.getString("nr_frota"),
                              rs.getLong("oid_estoque")));
      }
      return list;
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "lista");
    }
    finally {
      try {
        rs.close ();
      }
      catch (SQLException e) {
        e.printStackTrace ();
      }
      try {
        rs.getStatement ().close ();
      }
      catch (SQLException e) {
        e.printStackTrace ();
      }
    }
  }

  public void geraRelPneus (PneuED ed , HttpServletResponse res) throws Excecoes {
    ArrayList lista = this.listaDescrita (ed);
    new PneuRL ().relPneus (lista , res , ed);
  }

  private String getDesc_Posicao (String DM_Posicao) {
    if ("1".equals (DM_Posicao)) {
      return "Direcao Lado Esquerdo";
    }
    else if ("2".equals (DM_Posicao)) {
      return "Direcao Lado Direito";
    }
    else if ("3".equals (DM_Posicao)) {
      return "Tracao Lado Esquerdo Externo";
    }
    else if ("4".equals (DM_Posicao)) {
      return "Tracao Lado Esquerdo Interno";
    }
    else if ("5".equals (DM_Posicao)) {
      return "Tracao Lado Direito Externo";
    }
    else if ("6".equals (DM_Posicao)) {
      return "Tracao Lado Direito Interno";
    }
    else if ("7".equals (DM_Posicao)) {
      return "Truck Lado Esquerdo Externo";
    }
    else if ("8".equals (DM_Posicao)) {
      return "Truck Lado Esquerdo Interno";
    }
    else if ("9".equals (DM_Posicao)) {
      return "Truck Lado Direito Externo";
    }
    else if ("10".equals (DM_Posicao)) {
      return "Truck Lado Direito Interno";
    }
    else if ("11".equals (DM_Posicao)) {
      return "Estepe 1";
    }
    else if ("12".equals (DM_Posicao)) {
      return "Estepe 2";
    }
    else if ("20".equals (DM_Posicao)) {
      return "Carreta Eixo 1 Esquerdo Externo";
    }
    else if ("21".equals (DM_Posicao)) {
      return "Carreta Eixo 1 Esquerdo Interno";
    }
    else if ("22".equals (DM_Posicao)) {
      return "Carreta Eixo 1 Direito Externo";
    }
    else if ("23".equals (DM_Posicao)) {
      return "Carreta Eixo 1 Direito Interno";
    }
    else if ("30".equals (DM_Posicao)) {
      return "Carreta Eixo 2 Esquerdo Externo";
    }
    else if ("31".equals (DM_Posicao)) {
      return "Carreta Eixo 2 Esquerdo Interno";
    }
    else if ("32".equals (DM_Posicao)) {
      return "Carreta Eixo 2 Direito Externo";
    }
    else if ("33".equals (DM_Posicao)) {
      return "Carreta Eixo 2 Direito Interno";
    }
    else if ("40".equals (DM_Posicao)) {
      return "Carreta Eixo 3 Esquerdo Externo";
    }
    else if ("41".equals (DM_Posicao)) {
      return "Carreta Eixo 3 Esquerdo Interno";
    }
    else if ("42".equals (DM_Posicao)) {
      return "Carreta Eixo 3 Direito Externo";
    }
    else if ("43".equals (DM_Posicao)) {
      return "Carreta Eixo 3 Direito Interno";
    }
    else if ("51".equals (DM_Posicao)) {
      return "Carreta Estepe 1";
    }
    else if ("52".equals (DM_Posicao)) {
      return "Carreta Estepe 2";
    }
    else {
      return "Situação inválida";
    }
  }

  private String getDesc_Localizacao (String DM_Localizacao) {
    if (DM_Localizacao.equals ("01")) {
      return "Estoque";
    }
    else if (DM_Localizacao.equals ("02")) {
      return "Manutenção";
    }
    else if (DM_Localizacao.equals ("03")) {
      return "Veículo";
    }
    else {
      return "Localização inválida";
    }
  }

  private String getDesc_Situacao (String DM_Situacao) {
    if (DM_Situacao.equals ("1")) {
      return "Novo";
    }
    else if (DM_Situacao.equals ("2")) {
      return "Primeira recapagem";
    }
    else if (DM_Situacao.equals ("3")) {
      return "Segunda recapagem";
    }
    else if (DM_Situacao.equals ("4")) {
      return "Terceira recapagem";
    }
    else if (DM_Situacao.equals ("8")) {
      return "Sucata";
    }
    else if (DM_Situacao.equals ("9")) {
      return "Descarte";
    }
    else {
      return "Situação inválida";
    }
   }

	private PneuED populaRegistroUltima_Recapagem(ResultSet rs) throws SQLException {
		PneuED ed = new PneuED();
		ed.setOid_Pneu(rs.getInt("oid_Pneu"));
		ed.setNr_Fogo(rs.getString("Nr_Fogo"));
		ed.setNm_Fabricante_Pneu(rs.getString("Nm_Fabricante_Pneu"));
		ed.setNm_Modelo_Pneu(rs.getString("Nm_Modelo_Pneu"));
		ed.setOid_Fornecedor_Ultima_Recapagem(rs.getString("oid_Fornecedor_Ultima_Recapagem"));
		ed.setOid_Fabricante_Ultima_Recapagem(rs.getLong("oid_Fabricante_Ultima_Recapagem"));
		ed.setOid_Modelo_Pneu_Ultima_Recapagem(rs.getLong("oid_Modelo_Pneu_Ultima_Recapagem"));
		ed.setVl_Ultima_Recapagem(rs.getDouble("vl_Ultima_Recapagem"));
		ed.setDt_Ultima_Recapagem(FormataData.formataDataBT(rs.getString("dt_Ultima_Recapagem")));
		ed.setNr_Os_Ultima_Recapagem(rs.getString("nr_Os_Ultima_Recapagem"));
		ed.setNr_Nota_Fiscal_Ultima_Recapagem(rs.getLong("nr_Nota_Fiscal_Ultima_Recapagem"));
		ed.setDm_Garantia_Ultima_Recapagem(rs.getBoolean("dm_Garantia_Ultima_Recapagem"));
		ed.setNr_MM_Sulco_Ultima_Recapagem(rs.getDouble("nr_MM_Sulco_Ultima_Recapagem"));
		return ed;
	}

    private PneuED populaRegistro(ResultSet rs) throws SQLException, Excecoes {
		PneuED ed = new PneuED();
		ed.setOid_Empresa(rs.getLong("oid_Empresa"));
		ed.setOid_Pneu(rs.getInt("oid_Pneu"));
		ed.setCD_Pneu(rs.getString("CD_Pneu"));
		ed.setNr_Fogo(rs.getString("Nr_Fogo"));
		ed.setNm_Fabricante_Pneu(rs.getString("Nm_Fabricante_Pneu"));
		ed.setNm_Tipo_Pneu(rs.getString("Nm_Tipo_Pneu"));
		ed.setNm_Modelo_Pneu(rs.getString("Nm_Modelo_Pneu"));
		ed.setNM_Modelo_Pneu(rs.getString("Nm_Modelo_Pneu"));
		ed.setNm_Dimensao_Pneu(rs.getString("Nm_Dimensao_Pneu"));
		ed.setOid_Fabricante_Pneu(rs.getInt("oid_Fabricante_Pneu"));
		ed.setOid_Dimensao_Pneu(rs.getLong("oid_Dimensao_Pneu"));
		ed.setOid_Tipo_Pneu(rs.getLong("oid_Tipo_Pneu"));
		ed.setOid_Modelo_Pneu(rs.getInt("oid_Modelo_Pneu"));
		ed.setNr_Km_Acumulada(rs.getDouble("nr_Km_Acumulada"));
		ed.setTx_Lonas(rs.getString("tx_Lonas"));
		ed.setTx_Dot(rs.getString("tx_Dot"));
		ed.setNr_Nota_Fiscal(rs.getLong("nr_Nota_Fiscal"));
		ed.setNR_Fabrica(rs.getString("NR_Fabrica"));
		ed.setNr_Perimetro(rs.getLong("nr_Perimetro"));
		ed.setNr_Serie(rs.getString("Nr_Serie"));
		ed.setCd_Item_Estoque(rs.getString("cd_Item_Estoque"));
		if(!doValida(ed.getCd_Item_Estoque())){
			ed.setCd_Item_Estoque(rs.getString("Nr_Fogo"));
		}
		ed.setCD_Modelo_Pneu(rs.getString("CD_Modelo_Pneu"));
		ed.setOid_Fornecedor(rs.getString("oid_Fornecedor"));
		ed.setNr_Cnpj_Cpf(rs.getString("oid_Fornecedor"));
		ed.setVl_Preco(rs.getDouble("vl_Preco"));
		ed.setDt_Nota_Fiscal(FormataData.formataDataBT(rs.getString("dt_Nota_Fiscal")));
		ed.setDM_Localizacao(rs.getString("DM_Localizacao"));
		ed.setDM_Situacao(rs.getString("DM_Situacao"));
		ed.setDM_Posicao(rs.getString("DM_Posicao"));
		ed.setNm_Razao_Social(rs.getString("nm_Razao_Social"));
		ed.setNm_Fornecedor(rs.getString("nm_Razao_Social"));
		ed.setKM_Atual(rs.getInt("KM_Atual"));
		ed.setNr_Vida(rs.getLong("nr_Vida"));
		ed.setNm_Vida(ed.getDescVida());
		ed.setMM_Atual(rs.getDouble("MM_Atual"));
		ed.setNr_Mm_Saida(rs.getDouble("Nr_Mm_Saida"));
		ed.setOid_Veiculo(rs.getString("oid_Veiculo"));
		ed.setDm_Eixo(rs.getString("dm_Eixo"));
		ed.setNR_Frota(doValida(rs.getString("NR_Frota"))?rs.getString("NR_Frota"):rs.getString("NR_Placa"));
		ed.setNR_Placa(rs.getString("NR_Placa"));
		ed.setOid_estoque(rs.getLong("oid_estoque"));
		ed.setDm_Controle_Parcial(rs.getString("dm_Controle_Parcial"));
		ed.setOid_Fornecedor_Ultima_Recapagem(rs.getString("oid_Fornecedor_Ultima_Recapagem"));
		ed.setOid_Fabricante_Ultima_Recapagem(rs.getLong("oid_Fabricante_Ultima_Recapagem"));
		ed.setOid_Modelo_Pneu_Ultima_Recapagem(rs.getLong("oid_Modelo_Pneu_Ultima_Recapagem"));
		ed.setVl_Ultima_Recapagem(rs.getDouble("vl_Ultima_Recapagem"));
		ed.setDt_Ultima_Recapagem(FormataData.formataDataBT(rs.getString("dt_Ultima_Recapagem")));
		ed.setNr_Os_Ultima_Recapagem(rs.getString("nr_Os_Ultima_Recapagem"));
		ed.setNr_Nota_Fiscal_Ultima_Recapagem(rs.getLong("nr_Nota_Fiscal_Ultima_Recapagem"));
		ed.setDm_Garantia_Ultima_Recapagem(rs.getBoolean("dm_Garantia_Ultima_Recapagem"));
		ed.setNr_MM_Sulco_Ultima_Recapagem(rs.getDouble("nr_Mm_Sulco_Ultima_Recapagem"));
		ed.setNr_Km_Inicial(rs.getDouble("nr_Km_Inicial"));
		ed.setNr_Mm_Inicial(rs.getDouble("nr_Mm_Inicial"));
		// Situacoes especiais - acessar outras tabelas
		// Pegando informaçoes do estoque
		String detalhe = "";
		if (doValida(rs.getString("dt_Estoque"))) { // Verifica se a data de entrada é valida
			ed.setDM_Localizacao("em Estoque");
			ed.setDt_Estoque(FormataData.formataDataBT(rs.getString("dt_Estoque")));
			// Calcula dias em estoque (dt dia - dt estoque)
			Calendar dtStk = Data.stringToCalendar(ed.getDt_Estoque(),"dd/MM/yyyy");
			Calendar dtDia = Data.stringToCalendar(Data.getDataDMY(),"dd/MM/yyyy");
			ed.setDiasEmEstoque(Data.diferencaDias(dtStk,dtDia));
			ed.setOid_Local_Estoque(rs.getLong("oid_Local_Estoque"));
			detalhe = "<TABLE border=\"0\">";
			detalhe += "<TR><TD>Data:             </TD><TD><FONT color=\"#9999FF\">"+ed.getDt_Estoque()+"</FONT></TD></TR>";
			detalhe += "<TR><TD>Dias no estoque:  </TD><TD><FONT color=\"#9999FF\">"+ed.getDiasEmEstoque()+"</FONT></TD></TR>";
			//Acessando local de estoque para pegar o nome e colocar no ed do pneu de retorno
			Local_EstoqueED leED = new Local_EstoqueED();
			leED.setOid_Empresa(ed.getOid_Empresa());
			leED.setOid_Local_Estoque(ed.getOid_Local_Estoque());
			leED = new Local_EstoqueBD(this.executaSQL).getByRecord(leED);
			//Monta o nome do local de estoque no ed do pneu + nome da unidade
			if(doValida(String.valueOf(leED.getOid_Local_Estoque()))){
				ed.setNm_Local_Estoque(leED.getNm_Local_Estoque().trim() + " - " + leED.getNm_Unidade());
				detalhe += "<TR><TD>Local de estoque: </TD><TD><FONT color=\"#9999FF\">"+ed.getNm_Local_Estoque()+"</FONT></TD></TR>";
			}
			Movimento_PneuED movSED = new Movimento_PneuED();
			movSED.setOid_Pneu(ed.getOid_Pneu());
			movSED = new Movimento_PneuBD(this.executaSQL).getUltimoMovimento(movSED);
			ed.setTx_Motivo_Troca(movSED.getTx_Motivo());
			detalhe += "<TR><TD>Motivo da Troca: </TD><TD><FONT color=\"#9999FF\">"+ed.getTx_Motivo_Troca()+"</FONT></TD></TR>";

			detalhe += "</TABLE>";
		}
		// Pegando informaçoes da frota

		if (doValida(rs.getString("dt_Entrada"))) { // Verifica se a data de entrada é valida
			ed.setDM_Localizacao("Montado na Frota");
			ed.setDt_Entrada(FormataData.formataDataBT(rs.getString("dt_Entrada")));
			ed.setNr_Km_Acumulada_Veiculo(rs.getDouble("nr_Km_Acumulada_Veiculo"));
			ed.setNr_Hodometro_Veiculo(rs.getDouble("nr_Hodometro_Veiculo"));
			ed.setDm_Eixo(rs.getString("dm_Eixo"));
			ed.setDM_Posicao(rs.getString("dm_Posicao"));
			ed.setOid_Veiculo(rs.getString("oid_Veiculo"));
			ed.setNR_Frota(rs.getString("nr_Frota"));
			detalhe = "<TABLE border=\"0\">";
			detalhe += "<TR><TD>Data:          </TD><TD><FONT color=\"#9999FF\">"+ed.getDt_Entrada()+"</FONT></TD></TR>";
			detalhe += "<TR><TD>Veículo/Frota: </TD><TD><FONT color=\"#9999FF\">"+ed.getOid_Veiculo()+" / "+ed.getNR_Frota()+"</FONT></TD></TR>";
			detalhe += "<TR><TD>Eixo/Posição:  </TD><TD><FONT color=\"#9999FF\">"+ed.getDm_Eixo()+" / "+ed.getDM_Posicao()+"</FONT></TD></TR></TABLE>";
		}

		// pegando informacoes da recapagem
		if (doValida(rs.getString("dt_Remessa_Recapagem"))) { // Verifica se a data de entrada é valida
			ed.setDM_Localizacao("na Recapagem");
			ed.setDt_Remessa_Recapagem(FormataData.formataDataBT(rs.getString("dt_Remessa_Recapagem")));
			Calendar dtRem = Data.stringToCalendar(ed.getDt_Remessa_Recapagem(),"dd/MM/yyyy");
			Calendar dtDia = Data.stringToCalendar(Data.getDataDMY(),"dd/MM/yyyy");
			ed.setDiasEmEstoque(Data.diferencaDias(dtRem,dtDia));
			ed.setOid_Fornecedor_Recapagem(rs.getString("oid_Fornecedor_Recapagem"));
			ed.setDt_Promessa_Retorno_Recapagem(FormataData.formataDataBT(rs.getString("dt_Promessa_Retorno_Recapagem")));
			ed.setNm_Contato_Recapagem(rs.getString("nm_Contato_Recapagem"));
			ed.setVl_Negociado_Recapagem(rs.getDouble("vl_Negociado_Recapagem"));
			ed.setNr_Os_Recapagem(rs.getString("nr_Os_Recapagem"));
			//Acessando o fornecedor para pegar o nome
			FornecedorED fornED = new FornecedorED();
			fornED.setOid_Fornecedor(ed.getOid_Fornecedor_Recapagem());
			fornED = new FornecedorBD(this.executaSQL).getByRecord(fornED);
			//Mont nome do fornecedor no ed do pneu
			ed.setNm_Fornecedor_Recapagem(fornED.getNm_Razao_Social());
			detalhe = "<TABLE border=\"0\">";
			detalhe += "<TR><TD>Data:               </TD><TD><FONT color=\"#9999FF\">"+ed.getDt_Remessa_Recapagem()+"</FONT></TD></TR>";
			detalhe += "<TR><TD>Dias no fornecedor: </TD><TD><FONT color=\"#9999FF\">"+ed.getDiasEmEstoque()+"</FONT></TD></TR>";
			detalhe += "<TR><TD>Fornecedor:         </TD><TD><FONT color=\"#9999FF\">"+ed.getNm_Fornecedor_Recapagem()+"</FONT></TD></TR>";
			detalhe += "<TR><TD>Ordem de Serviço:   </TD><TD><FONT color=\"#9999FF\">"+ed.getNr_Os_Recapagem()+"</FONT></TD></TR></TABLE>";
		}
		//pegando informacoes da sucata
		if (doValida(rs.getString("dt_Sucateamento"))) { // Verifica se a data de entrada é valida
			ed.setDM_Localizacao("Sucateado");
			ed.setDt_Sucateamento(FormataData.formataDataBT(rs.getString("dt_Sucateamento")));
			ed.setOid_Motivo_Sucateamento(rs.getInt("oid_Motivo_Sucateamento"));
			//Acessando motivo da sucata
			Motivo_SucateamentoED mot_sucED = new Motivo_SucateamentoED();
			mot_sucED.setOid_Motivo_Sucateamento(ed.getOid_Motivo_Sucateamento());
			mot_sucED = new Motivo_SucateamentoBD(this.executaSQL).getByRecord(mot_sucED);
			//Monta a descricao do motivo no ed do pneu
			ed.setNm_Motivo_Sucateamento(mot_sucED.getNm_Motivo_Sucateamento());
			//fornecedor
			FornecedorED fornED = new FornecedorED();
			//Mont nome do fornecedor no ed do pneu
			if (doValida(ed.getOid_Fornecedor())){
				fornED.setOid_Fornecedor(ed.getOid_Fornecedor());
			}else{
				fornED.setOid_Fornecedor(ed.getOid_Fornecedor_Ultima_Recapagem());
			}
			fornED = new FornecedorBD(this.executaSQL).getByRecord(fornED);
			ed.setNm_Fornecedor(fornED.getNm_Razao_Social());
			detalhe = "<TABLE border=\"0\">";
			detalhe += "<TR><TD>Data:   </TD><TD><FONT color=\"#9999FF\">"+ed.getDt_Sucateamento()+"</FONT></TD></TR>";
			detalhe += "<TR><TD>Motivo: </TD><TD><FONT color=\"#9999FF\">"+ed.getNm_Motivo_Sucateamento()+"</FONT></TD></TR></TABLE>";
		}
		//pegando informaçoes da venda.
		if (doValida(rs.getString("dt_Venda"))) { // Verifica se a data de entrada é valida
			ed.setDM_Localizacao("Vendido");
			ed.setDt_Venda(FormataData.formataDataBT(rs.getString("dt_Venda")));
			ed.setVl_Venda(rs.getDouble("vl_Venda"));
			ed.setTx_Comentario_Venda(rs.getString("tx_Comentario_Venda"));
			detalhe = "<TABLE border=\"0\">";
			detalhe += "<TR><TD>Data:       </TD><TD><FONT color=\"#9999FF\">"+ed.getDt_Venda()+"</FONT></TD></TR>";
			detalhe += "<TR><TD>Valor:      </TD><TD><FONT color=\"#9999FF\">"+ed.getVl_Venda()+"</FONT></TD></TR>";
			detalhe += "<TR><TD>Comentário: </TD><TD><FONT color=\"#9999FF\">"+ed.getTx_Comentario_Venda()+"</FONT></TD></TR></TABLE>";
		}
		ed.setUsuario_Stamp(rs.getString("usu_Stmp"));
		ed.setDescFiltro(detalhe);
		//Padrao
		if(!"31/12/1969 21:00:00".equals(FormataData.formataDataHoraTB(new Date(rs.getLong("time_millis"))))
				&& JavaUtil.doValida(rs.getString("usuario_Stamp"))){
			ed.setMsg_Stamp(("I".equals(rs.getString("dm_Stamp"))? "Incluído":"Alterado") + " por " + rs.getString("usuario_Stamp")+ " em " + FormataData.formataDataHoraTB(new Date(rs.getLong("time_millis"))));
		}
		return ed;
  }
    /**
     * Atualiza a situação do pneu colocando ele em
     * 	Estoque, Recpagem, Sucata ou na frota dependendo da chamado do laszlo - pns102
     * @param ed
     * @throws Excecoes
     */
    public void atualizaSituacao (PneuED ed) throws Excecoes {
    	try {
    		long oidPneu = 0 ;

    		// Pega se o pneu é colocado ou retirado
    		if ( ed.getOid_Pneu_Retirado() > 0 )
    			oidPneu = ed.getOid_Pneu_Retirado();
    		else
			if ( ed.getOid_Pneu() > 0 )
				oidPneu = ed.getOid_Pneu ();

    		PneuED edBusca = new PneuED();
    		edBusca.setOid_Pneu((int)oidPneu); // ed excluzivo para buscar o pneu ( só tem o oid_pneu preenchido )

    		// Pega o pneu antes da atualizacao para o registro da medicao
    		PneuED pneuAntesED = this.getByRecordJSTL(edBusca);

    		// Inicia a atualizacao do pneu
    		sql ="Update " +
    		"Pneus " +
    		"set " ;

    		// Atualiza estoque
    		if ( doValida(ed.getDt_Estoque()) )
    			sql+="dt_Estoque='"+ed.getDt_Estoque()+"'" +
    			",oid_Local_Estoque="+ed.getOid_Local_Estoque() ;
    		else
    			sql+="dt_Estoque=null" +
    			",oid_Local_Estoque=null";

    		// Atualiza recape
    		if ( doValida(ed.getDt_Remessa_Recapagem()) )
    			sql+=",dt_Remessa_Recapagem='"+ed.getDt_Remessa_Recapagem()+"'" +
    			",oid_Fornecedor_Recapagem='"+ed.getOid_Fornecedor_Recapagem()+"'" +
    			",dt_Promessa_Retorno_Recapagem='"+ed.getDt_Promessa_Retorno_Recapagem()+"'" +
    			",nm_Contato_Recapagem='"+ed.getNm_Contato_Recapagem()+"'" +
    			",nr_Os_Recapagem='"+ed.getNr_Os_Recapagem()+"'" +
    			",vl_Negociado_Recapagem="+ed.getVl_Negociado_Recapagem() ;
    		else
    			sql+=",dt_Remessa_Recapagem=null" +
    			",oid_Fornecedor_Recapagem=null" +
    			",dt_Promessa_Retorno_Recapagem=null" +
    			",nm_Contato_Recapagem=null" +
    			",nr_Os_Recapagem=null" +
    			",vl_Negociado_Recapagem=null" ;

    		// Atualiza sucata
    		if ( doValida(ed.getDt_Sucateamento()) )
    			sql+=",dt_Sucateamento='"+ed.getDt_Sucateamento()+"'" +
    			",oid_Motivo_Sucateamento="+ed.getOid_Motivo_Sucateamento();
    		else
    			sql+=",dt_Sucateamento=null" +
    			",oid_Motivo_Sucateamento=null";

    		// Atualiza frota
    		if ( doValida(ed.getDt_Entrada()) )
    			sql+=",dt_Entrada='"+ed.getDt_Entrada()+"'" +
    			",oid_Veiculo='"+ed.getOid_Veiculo()+"'" +
    			",dm_Posicao='"+ed.getDM_Posicao()+"'" +
    			",dm_Eixo='"+ed.getDm_Eixo()+"'" +
    			",nr_Hodometro_Veiculo="+ed.getNr_Hodometro_Veiculo() +
    			",nr_Km_Acumulada_Veiculo="+ed.getNr_Km_Acumulada_Veiculo();
			else
    			sql+=",dt_Entrada=null" +
    			",oid_Veiculo=null" +
    			",dm_Posicao=null" +
    			",dm_Eixo=null" +
    			",nr_Hodometro_Veiculo=null" +
    			",nr_Km_Acumulada_Veiculo=null";

			// Se informado codigo do estoque ...
    		if ( doValida(ed.getCd_Item_Estoque()) ) {
    			sql+=",cd_Item_Estoque='"+ed.getCd_Item_Estoque()+"'";
    		}

			// Atualiza mm
    		if ( ed.getMM_Atual() > 0 ) {
    			sql+=",MM_Atual="+ed.getMM_Atual();
    		}

    		// Se for informado vida atualiza ... ( somente quando do retorno da recapagem ... )
    		if ( ed.getNr_Vida() > 0 ) {
    			sql+=",nr_Vida="+ed.getNr_Vida();
    		}

    		// Se pneu estiver saindo da frota por troca, calcula a km acumulada do pneu
    		// O cálculo é o seguinte : km acumulada do pneu + ( km acumulada do veic na saída - km acumulada do veic na entrada )
    		if ( ed.getOid_Pneu_Retirado() > 0 ) {
				sql+=",nr_Km_Acumulada = nr_Km_Acumulada + (" + ed.getNr_Km_Acumulada_Veiculo() + " - nr_km_acumulada_veiculo )"  ;
    		}

    		sql += ",dt_stamp = '" + ed.getDt_stamp() + "'"
					+ ",usuario_stamp = '" + ed.getUsuario_Stamp() + "'"
					+ ",dm_stamp = '" + ed.getDm_Stamp() + "'"
					+ ",oid_usuario = " + ed.getUser()
					+ ",time_millis = "	+ ed.getTime_millis();

    		sql+= " where oid_pneu = " + oidPneu;
//System.out.println(sql);
    		executaSQL.executarUpdate (sql);

    		// Pega o pneu depois da atualizacao para o registro da medicao
    		PneuED pneuDepoisED = this.getByRecordJSTL(edBusca);

    		// Registro da medição
    		// Se for pneu retirado obrigatoriamente faz a medição se for pneu colocado então depende se já tinha medicao anterior
    		if ( ed.getOid_Pneu_Retirado()>0 || ( ed.getOid_Pneu()>0 && pneuAntesED.getMM_Atual()!=pneuDepoisED.getMM_Atual()) ) {
    			Medicao_PneuED mp = new Medicao_PneuED();
    			mp.setOid_Empresa(pneuDepoisED.getOid_Empresa());
    			mp.setOid_Pneu(pneuDepoisED.getOid_Pneu());
    			mp.setNr_Vida(pneuDepoisED.getNr_Vida());
    			mp.setNr_Mm_Sulco(pneuDepoisED.getMM_Atual());
    			mp.setNr_Km_Acumulada_Pneu(pneuDepoisED.getNr_Km_Acumulada());
    			// Pega a data
    			if ( doValida(ed.getDt_Estoque()) )
    				mp.setDt_Medicao_Pneu(ed.getDt_Estoque());
    			else if ( doValida(ed.getDt_Remessa_Recapagem()) )
    				mp.setDt_Medicao_Pneu(ed.getDt_Remessa_Recapagem());
    			else if ( doValida(ed.getDt_Sucateamento()) )
    				mp.setDt_Medicao_Pneu(ed.getDt_Sucateamento());
    			if ( doValida(ed.getDt_Entrada()) )
    				mp.setDt_Medicao_Pneu(ed.getDt_Entrada());
    			new Medicao_PneuBD(this.executaSQL).inclui(mp);
    		}
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    		throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "atualizaSituacao(PneuED ed)");
    	}
    }

    public void registraRecusaRecapagem (PneuED ed) throws Excecoes {
  	  try {
  		  sql = "UPDATE Pneus SET " +
  		  " oid_Fornecedor_Recusa_Recapagem='"+ed.getOid_Fornecedor_Recusa_Recapagem()+"' "+
  		  ",dt_Recusa_Recapagem='"+ed.getDt_Recusa_Recapagem()+"' "+
  		  ",tx_Motivo_Recusa_Recapagem='"+ed.getTx_Motivo_Recusa_Recapagem()+"' "+
  		  ",nr_Nota_Fiscal_Recusa_Recapagem="+ed.getNr_Nota_Fiscal_Recusa_Recapagem()+
  		  ",nm_Responsavel_Recusa_Recapagem='"+ed.getNm_Responsavel_Recusa_Recapagem()+"' "+
  		  " where oid_Pneu="+ed.getOid_Pneu();
  		  executaSQL.executarUpdate (sql);
 	  }
  	  catch (SQLException e) {
  		  throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "alteraUltima_Recapagem (PneuED ed)");
  	  }
    }

    /**
     * Lista de sucateados para a análise de sucata.
     * @param ed
     * @return
     * @throws Excecoes
     */
    public ArrayList analiseSucata (PneuED ed,String pWhere) throws Excecoes {
    	ArrayList list = new ArrayList ();
    	sql = "SELECT " +
    	"p.oid_Pneu, " +
    	"p.nr_Fogo, " +
    	"m.nm_Modelo_Pneu, " +
    	"p.tx_Dot, " +
    	"p.dt_Nota_Fiscal, " +
    	"p.dt_Sucateamento, " +
    	"p.nr_Km_Acumulada, " +
    	"p.mm_Atual, " +
    	"m.nm_modelo_pneu, " +
    	"o.nm_motivo_sucateamento, " +
    	"u.nm_unidade " +
    	"FROM " +
    	"pneus p, " +
    	"modelos_pneus m, " +
    	"motivos_sucateamentos o, " +
    	"unidades u " +
    	"WHERE " +
    	"p.oid_modelo_pneu = m.oid_modelo_pneu " +
    	"and " +
    	"p.oid_motivo_sucateamento = o.oid_motivo_sucateamento " +
    	"and " +
    	"p.oid_unidade_sucateamento = u.oid_unidade " +
    	"and " +
    	"p.oid_Empresa = " + ed.getOid_Empresa() + " " +
    	"and " +
    	"p.dt_Sucateamento is not null ";

    	sql += pWhere;

    	sql += "ORDER BY " +
    	"u.nm_unidade, " +
    	"o.nm_motivo_sucateamento, " +
    	"m.nm_modelo_pneu, " +
    	"p.nr_Fogo";
    	try {
    		ResultSet rs = this.executaSQL.executarConsulta(sql);
    		double nr_Km_Vida = 0;
    		while (rs.next ()) {
    			PneuED pneuED = new PneuED();
    			pneuED.setNr_Fogo(rs.getString("Nr_Fogo"));
    			pneuED.setTx_Dot(rs.getString("Tx_Dot"));
    			pneuED.setNm_Modelo_Pneu(rs.getString("Nm_Modelo_Pneu"));
    			pneuED.setDt_Nota_Fiscal(FormataData.formataDataBT(rs.getString("dt_Nota_Fiscal")));
    			pneuED.setDt_Sucateamento(FormataData.formataDataBT(rs.getString("dt_Sucateamento")));

    			//pneuED.setDiasEmEstoque(Data.diferencaDias(pneuED.getDt_Sucateamento(), pneuED.getDt_Nota_Fiscal()));

    			Calendar dtSuc = Data.stringToCalendar(pneuED.getDt_Sucateamento(),"dd/MM/yyyy");
    			Calendar dtNF = Data.stringToCalendar(pneuED.getDt_Nota_Fiscal(),"dd/MM/yyyy");
    			pneuED.setDiasEmEstoque(Data.diferencaMeses(dtNF,dtSuc));


    			pneuED.setNr_Km_Acumulada(rs.getDouble("nr_Km_Acumulada"));
    			pneuED.setMM_Atual(rs.getDouble("MM_Atual"));
    			pneuED.setNm_Motivo_Sucateamento(rs.getString("nm_Motivo_Sucateamento"));
    			pneuED.setNm_Unidade(rs.getString("nm_Unidade"));
    			Vida_PneuED vidaED = new Vida_PneuED();
    			vidaED.setOid_Pneu(rs.getInt("oid_Pneu"));
    			ArrayList lst = new Vida_PneuBD(this.executaSQL).lista(vidaED);
    			for (int i=0; i<lst.size(); i++){
    				vidaED = (Vida_PneuED)lst.get(i);
    				nr_Km_Vida = vidaED.getNr_Km_Final() - vidaED.getNr_Km_Inicial();
    				if (vidaED.getNr_Vida()==0) {
    					pneuED.setNr_Km_Vida_N0(nr_Km_Vida);
    					pneuED.setNm_Desenho_N0(pneuED.getNm_Modelo_Pneu());
    					pneuED.setNm_Fornecedor(pneuED.getNm_Modelo_Pneu());
    				}
    				if (vidaED.getNr_Vida()==1) {
    					pneuED.setNr_Km_Vida_R1(nr_Km_Vida);
    					pneuED.setNm_Desenho_R1(vidaED.getNm_Banda());
    					pneuED.setNm_Fornecedor(vidaED.getNm_Banda());
    				}
    				if (vidaED.getNr_Vida()==2) {
    					pneuED.setNr_Km_Vida_R2(nr_Km_Vida);
    					pneuED.setNm_Desenho_R2(vidaED.getNm_Banda());
    					pneuED.setNm_Fornecedor(vidaED.getNm_Banda());
    				}
    				if (vidaED.getNr_Vida()==3) {
    					pneuED.setNr_Km_Vida_R3(nr_Km_Vida);
    					pneuED.setNm_Desenho_R3(vidaED.getNm_Banda());
    					pneuED.setNm_Fornecedor(vidaED.getNm_Banda());
    				}
    				if (vidaED.getNr_Vida()==4) {
    					pneuED.setNr_Km_Vida_R4(nr_Km_Vida);
    					pneuED.setNm_Desenho_R4(vidaED.getNm_Banda());
    					pneuED.setNm_Fornecedor(vidaED.getNm_Banda());
    				}
    				if (vidaED.getNr_Vida()==5) {
    					pneuED.setNr_Km_Vida_R5(nr_Km_Vida);
    					pneuED.setNm_Desenho_R5(vidaED.getNm_Banda());
    					pneuED.setNm_Fornecedor(vidaED.getNm_Banda());
    				}
    				if (vidaED.getNr_Vida()==6) {
    					pneuED.setNr_Km_Vida_R6(nr_Km_Vida);
    					pneuED.setNm_Desenho_R6(vidaED.getNm_Banda());
    					pneuED.setNm_Fornecedor(vidaED.getNm_Banda());
    				}

    			}
    			list.add(pneuED);
    		}
    		return list;
    	} catch (Exception e) {
    		throw new Excecoes (e.getMessage () , e , this.getClass().getName(), "analiseSucata ()");
    	}
    }
    // Primeiro resumo da analise de sucata - Por Local
    public ArrayList analiseSucataResumo1 (PneuED ed,String pWhere) throws Excecoes {
    	ArrayList list = new ArrayList ();
    	//Retorna quantos pneus foram sucateados em cada unidade
    	sql = "SELECT " +
    	"p.oid_unidade_sucateamento, " +
    	"u.nm_unidade, " +
    	"count (p.oid_Pneu) as cta " +
    	"FROM " +
    	"pneus p, " +
    	"modelos_pneus m, " +
    	"motivos_sucateamentos o, " +
    	"unidades u " +
    	"WHERE " +
    	"p.oid_modelo_pneu = m.oid_modelo_pneu and " +
    	"p.oid_motivo_sucateamento = o.oid_motivo_sucateamento and " +
    	"p.oid_unidade_sucateamento = u.oid_unidade and " +
    	"p.oid_Empresa = " + ed.getOid_Empresa() + " and " +
    	"p.dt_Sucateamento is not null ";
    	sql += pWhere;
    	sql += "GROUP BY " +
    	"p.oid_unidade_sucateamento, " +
    	"u.nm_unidade " ;
    	sql += "ORDER BY " +
    	"u.nm_unidade";
    	try {
    		ResultSet rs1 = this.executaSQL.executarConsulta(sql);
    		while (rs1.next ()) {
    			PneuED pneuED1 = new PneuED();
    			pneuED1.setNm_Unidade(rs1.getString("nm_Unidade"));
    			pneuED1.setNr_Vida(rs1.getLong("cta"));
    			list.add(pneuED1);
    			// Para cada unidade Retorna quantos pneus foram sucateados em cada dimensao
    			sql = "SELECT " +
    			"p.oid_dimensao_pneu, " +
    			"d.nm_dimensao_pneu, " +
    			"count (p.oid_Pneu) as cta " +
    			"FROM " +
    			"pneus p, " +
    			"dimensoes_pneus d, " +
    			"modelos_pneus m, " +
    			"motivos_sucateamentos o, " +
    			"unidades u " +
    			"WHERE " +
    			"p.oid_dimensao_pneu = d.oid_dimensao_pneu and " +
    			"p.oid_modelo_pneu = m.oid_modelo_pneu and " +
    			"p.oid_motivo_sucateamento = o.oid_motivo_sucateamento and " +
    			"p.oid_unidade_sucateamento = u.oid_unidade and " +
    			"p.oid_Empresa = " + ed.getOid_Empresa() + " and " +
    			"p.dt_Sucateamento is not null and ";
    			sql += "p.oid_unidade_sucateamento = " + rs1.getLong("oid_Unidade_Sucateamento");
    			sql += pWhere;
    			sql += "GROUP BY " +
    			"p.oid_dimensao_pneu, " +
    			"d.nm_dimensao_pneu " ;
    			sql += "ORDER BY " +
    			"d.nm_dimensao_pneu";
    			ResultSet rs2 = this.executaSQL.executarConsulta(sql);
        		while (rs2.next ()) {
        			PneuED pneuED2 = new PneuED();
        			pneuED2.setNm_Dimensao_Pneu(rs2.getString("nm_dimensao_pneu"));
        			pneuED2.setNr_Vida(rs2.getLong("cta"));
        			// Retorna quantos pneus tem rodando na frota com esta dimensao
        			sql = "SELECT " +
        			"count (p.oid_Pneu) as cta " +
        			"FROM " +
        			"pneus p " +
        			"WHERE " +
        			"p.oid_Empresa = " + ed.getOid_Empresa() + " and " +
        			"p.dt_Entrada is not null and " +
        			"p.oid_dimensao_pneu = " + rs2.getLong("oid_Dimensao_Pneu");
        			ResultSet rs3 = this.executaSQL.executarConsulta(sql);
        			if (rs3.next ()) {
        				// Calcula percentual de pneus sucateados em relação aos que estão rodando na frota com a mesma dimensao
        				pneuED2.setNr_Km_Vida_N0(rs3.getLong("cta"));
        			}
        			// Retorna uma lista com a contagem de pneus sucateados por vida na dimensão
        			sql = "SELECT " +
        			"p.nr_vida, " +
        			"count (p.oid_Pneu) as cta " +
        			"FROM " +
        			"pneus p, " +
        			"dimensoes_pneus d, " +
        			"modelos_pneus m, " +
        			"motivos_sucateamentos o, " +
        			"unidades u " +
        			"WHERE " +
        			"p.oid_dimensao_pneu = d.oid_dimensao_pneu and " +
        			"p.oid_modelo_pneu = m.oid_modelo_pneu and " +
        			"p.oid_motivo_sucateamento = o.oid_motivo_sucateamento and " +
        			"p.oid_unidade_sucateamento = u.oid_unidade and " +
        			"p.oid_Empresa = " + ed.getOid_Empresa() + " and " +
        			"p.dt_Sucateamento is not null and " +
        			"p.oid_unidade_sucateamento = " + rs1.getLong("oid_Unidade_Sucateamento") + " and " +
        			"p.oid_dimensao_pneu = " + rs2.getLong("oid_Dimensao_Pneu");
        			sql += pWhere;
        			sql += "GROUP BY " +
        			"p.nr_Vida " ;
        			sql += "ORDER BY " +
        			"p.nr_vida";
        			ResultSet rs4 = this.executaSQL.executarConsulta(sql);
        			ArrayList lstCtaVida = new ArrayList();
        			while  (rs4.next ()) {
        				// Calcula percentual de pneus sucateados em relação aos que estão rodando na frota com a mesma dimensao
        				PneuED pnED = new PneuED();
        				pnED.setNr_Vida(rs4.getInt("nr_vida"));
        				pnED.setNr_Km_Vida_N0(rs4.getLong("cta"));
        				lstCtaVida.add(pnED);
        			}
        			pneuED2.setSublista(lstCtaVida);
        			list.add(pneuED2);
        		}
    		}
    		return list;
    	} catch (Exception e) {
    		throw new Excecoes (e.getMessage () , e , this.getClass().getName(), "analiseSucataResumo1 ()");
    	}
    }
    // Segundo resumo da analise de sucata - Por Motivo
    public ArrayList analiseSucataResumo2 (PneuED ed,String pWhere) throws Excecoes {
    	ArrayList list = new ArrayList ();

    	try {
	    	sql = "SELECT " +
	    	"count (p.oid_Pneu) as cta " +
	    	"FROM " +
	    	"pneus p, " +
	    	"modelos_pneus m, " +
	    	"motivos_sucateamentos o, " +
	    	"unidades u " +
	    	"WHERE " +
	    	"p.oid_modelo_pneu = m.oid_modelo_pneu and " +
	    	"p.oid_motivo_sucateamento = o.oid_motivo_sucateamento and " +
	    	"p.oid_unidade_sucateamento = u.oid_unidade and " +
	    	"p.oid_Empresa = " + ed.getOid_Empresa() + " and " +
	    	"p.dt_Sucateamento is not null ";
	    	sql += pWhere;
    		ResultSet rs1 = this.executaSQL.executarConsulta(sql);
    		rs1.next ();

	    	//Retorna quantos pneus foram sucateados em cada motivo
	    	sql = "SELECT " +
	    	"o.nm_motivo_sucateamento, " +
	    	"count (p.oid_Pneu) as cta " +
	    	"FROM " +
	    	"pneus p, " +
	    	"modelos_pneus m, " +
	    	"motivos_sucateamentos o, " +
	    	"unidades u " +
	    	"WHERE " +
	    	"p.oid_modelo_pneu = m.oid_modelo_pneu and " +
	    	"p.oid_motivo_sucateamento = o.oid_motivo_sucateamento and " +
	    	"p.oid_unidade_sucateamento = u.oid_unidade and " +
	    	"p.oid_Empresa = " + ed.getOid_Empresa() + " and " +
	    	"p.dt_Sucateamento is not null ";
	    	sql += pWhere;
	    	sql += "GROUP BY " +
	    	"o.nm_motivo_sucateamento " ;
	    	sql += "ORDER BY " +
	    	"o.nm_motivo_sucateamento ";
    		ResultSet rs2 = this.executaSQL.executarConsulta(sql);
    		while (rs2.next ()) {
    			PneuED pneuED = new PneuED();
    			pneuED.setNm_Motivo_Sucateamento(rs2.getString("nm_motivo_sucateamento"));
    			pneuED.setNr_Vida(rs2.getLong("cta"));
    			pneuED.setNr_Km_Vida_N0(rs1.getLong("cta"));
    			pneuED.setNr_Km_Vida_N0(pneuED.getNr_Vida()/pneuED.getNr_Km_Vida_N0()*100);
    			list.add(pneuED);
    		}
    		return list;
    	} catch (Exception e) {
    		throw new Excecoes (e.getMessage () , e , this.getClass().getName(), "analiseSucataResumo2 ()");
    	}
    }
    // Terceiro resumo da analise de sucata - Por Dimensão
    public ArrayList analiseSucataResumo3 (PneuED ed,String pWhere) throws Excecoes {
    	ArrayList list = new ArrayList ();
    	try {
			// Retorna quantos pneus foram sucateados em cada dimensao
			sql = "SELECT " +
			"p.oid_dimensao_pneu, " +
			"d.nm_dimensao_pneu, " +
			"count (p.oid_Pneu) as cta " +
			"FROM " +
			"pneus p, " +
			"dimensoes_pneus d, " +
			"modelos_pneus m, " +
			"motivos_sucateamentos o, " +
			"unidades u " +
			"WHERE " +
			"p.oid_dimensao_pneu = d.oid_dimensao_pneu and " +
			"p.oid_modelo_pneu = m.oid_modelo_pneu and " +
			"p.oid_motivo_sucateamento = o.oid_motivo_sucateamento and " +
			"p.oid_unidade_sucateamento = u.oid_unidade and " +
			"p.oid_Empresa = " + ed.getOid_Empresa() + " and " +
			"p.dt_Sucateamento is not null ";
			sql += pWhere;
			sql += "GROUP BY " +
			"p.oid_dimensao_pneu, " +
			"d.nm_dimensao_pneu " ;
			sql += "ORDER BY " +
			"d.nm_dimensao_pneu";
			ResultSet rs1 = this.executaSQL.executarConsulta(sql);
    		while (rs1.next ()) {
    			PneuED pneuED1 = new PneuED();
    			pneuED1.setNm_Dimensao_Pneu(rs1.getString("nm_dimensao_pneu"));
    			pneuED1.setNr_Vida(rs1.getLong("cta"));
    			// Retorna quantos pneus tem rodando na frota com esta dimensao
    			sql = "SELECT " +
    			"count (p.oid_Pneu) as cta " +
    			"FROM " +
    			"pneus p " +
    			"WHERE " +
    			"p.oid_Empresa = " + ed.getOid_Empresa() + " and " +
    			"p.dt_Entrada is not null and " +
    			"p.oid_dimensao_pneu = " + rs1.getLong("oid_Dimensao_Pneu");
    			ResultSet rs2 = this.executaSQL.executarConsulta(sql);
    			if (rs2.next ()) {
    				// Calcula percentual de pneus sucateados em relação aos que estão rodando na frota com a mesma dimensao
    				pneuED1.setNr_Km_Vida_N0(rs2.getLong("cta"));
    			}
    			// Retorna uma lista com a contagem de pneus sucateados por vida na dimensão
    			sql = "SELECT " +
    			"p.nr_vida, " +
    			"count (p.oid_Pneu) as cta " +
    			"FROM " +
    			"pneus p, " +
    			"dimensoes_pneus d, " +
    			"modelos_pneus m, " +
    			"motivos_sucateamentos o, " +
    			"unidades u " +
    			"WHERE " +
    			"p.oid_dimensao_pneu = d.oid_dimensao_pneu and " +
    			"p.oid_modelo_pneu = m.oid_modelo_pneu and " +
    			"p.oid_motivo_sucateamento = o.oid_motivo_sucateamento and " +
    			"p.oid_unidade_sucateamento = u.oid_unidade and " +
    			"p.oid_Empresa = " + ed.getOid_Empresa() + " and " +
    			"p.dt_Sucateamento is not null and " +
    			"p.oid_dimensao_pneu = " + rs1.getLong("oid_Dimensao_Pneu");
    			sql += pWhere;
    			sql += "GROUP BY " +
    			"p.nr_Vida " ;
    			sql += "ORDER BY " +
    			"p.nr_vida";
    			ResultSet rs3 = this.executaSQL.executarConsulta(sql);
    			ArrayList lstCtaVida = new ArrayList();
    			while  (rs3.next ()) {
    				// Calcula percentual de pneus sucateados em relação aos que estão rodando na frota com a mesma dimensao
    				PneuED pnED = new PneuED();
    				pnED.setNr_Vida(rs3.getInt("nr_vida"));
    				pnED.setNr_Km_Vida_N0(rs3.getLong("cta"));
    				lstCtaVida.add(pnED);
    			}
    			pneuED1.setSublista(lstCtaVida);
    			list.add(pneuED1);
    		}
    		return list;
    	} catch (Exception e) {
    		throw new Excecoes (e.getMessage () , e , this.getClass().getName(), "analiseSucataResumo3 ()");
    	}
    }
    // Quarto resumo da analise de sucata - Por Marca
    public ArrayList analiseSucataResumo4 (PneuED ed,String pWhere) throws Excecoes {
    	ArrayList list = new ArrayList ();
    	try {
	    	//Retorna quantos pneus foram sucateados em cada marca
	    	sql = "SELECT " +
	    	"f.nm_Fabricante_Pneu, " +
	    	"count (p.oid_Pneu) as cta " +
	    	"FROM " +
	    	"pneus p, " +
	    	"fabricantes_pneus f " +
	    	"WHERE " +
	    	"p.oid_fabricante_pneu = f.oid_fabricante_pneu and " +
	    	"p.oid_Empresa = " + ed.getOid_Empresa() + " and " +
	    	"p.dt_Sucateamento is not null ";
	    	sql += pWhere;
	    	sql += "GROUP BY " +
	    	"f.nm_Fabricante_Pneu " ;
	    	sql += "ORDER BY " +
	    	"f.nm_Fabricante_Pneu ";
    		ResultSet rs1 = this.executaSQL.executarConsulta(sql);
    		while (rs1.next ()) {
    			PneuED pneuED = new PneuED();
    			pneuED.setNm_Fabricante_Pneu(rs1.getString("nm_Fabricante_Pneu"));
    			pneuED.setNr_Vida(rs1.getLong("cta"));
    			list.add(pneuED);
    		}
    		return list;
    	} catch (Exception e) {
    		throw new Excecoes (e.getMessage () , e , this.getClass().getName(), "analiseSucataResumo4 ()");
    	}
    }
    // Quinto resumo da analise de sucata - Por Modelo
    public ArrayList analiseSucataResumo5 (PneuED ed,String pWhere) throws Excecoes {
    	ArrayList list = new ArrayList ();
    	try {
	    	//Retorna quantos pneus foram sucateados em cada marca
	    	sql = "SELECT " +
	    	"m.nm_Modelo_Pneu, " +
	    	"count (p.oid_Pneu) as cta " +
	    	"FROM " +
	    	"pneus p, " +
	    	"modelos_pneus m " +
	    	"WHERE " +
	    	"p.oid_Modelo_Pneu = m.oid_Modelo_Pneu and " +
	    	"p.oid_Empresa = " + ed.getOid_Empresa() + " and " +
	    	"p.dt_Sucateamento is not null ";
	    	sql += pWhere;
	    	sql += "GROUP BY " +
	    	"m.nm_Modelo_Pneu " ;
	    	sql += "ORDER BY " +
	    	"m.nm_Modelo_Pneu ";
    		ResultSet rs1 = this.executaSQL.executarConsulta(sql);
    		while (rs1.next ()) {
    			PneuED pneuED = new PneuED();
    			pneuED.setNm_Modelo_Pneu(rs1.getString("nm_Modelo_Pneu"));
    			pneuED.setNr_Vida(rs1.getLong("cta"));
    			list.add(pneuED);
    		}
    		return list;
    	} catch (Exception e) {
    		throw new Excecoes (e.getMessage () , e , this.getClass().getName(), "analiseSucataResumo5 ()");
    	}
    }

}
