/*
 * Created on 12/11/2004
 *
 */
package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import com.master.ed.Movimento_Pneu_EstoqueED;
import com.master.ed.PneuED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.bd.ExecutaSQL;

/**
 * @author PRS
 * @serial Movimentos Pneus Estoques
 * @serialData 08/2007
 */
public class Movimento_Pneu_EstoqueBD  extends BancoUtil {
	
  private ExecutaSQL executaSQL;
  
  String sql = null;
  
  public Movimento_Pneu_EstoqueBD (ExecutaSQL executaSQL) {
	  super(executaSQL);  
	  this.executaSQL = executaSQL;
  }

  public Movimento_Pneu_EstoqueED inclui (Movimento_Pneu_EstoqueED ed) throws Excecoes {
	  try {
		  ed.setOid_Movimento_Pneu_Estoque(getAutoIncremento ("oid_Movimento_Pneu_Estoque", "Movimentos_Pneus_Estoques"));
		  sql = "INSERT INTO Movimentos_Pneus_Estoques (" +
		  "oid_Movimento_Pneu_Estoque, " +
		  "oid_Empresa, " +
		  "oid_Pneu, " +
		  "dt_Movimento_Pneu_Estoque, " +
		  "oid_Local_Estoque_Origem, " +
		  "oid_Local_Estoque_Destino " +
		  ") " +
		  "values (" + 
		  ed.getOid_Movimento_Pneu_Estoque () +
		  ", " + ed.getOid_Empresa () +
		  ", " + ed.getOid_Pneu () +
		  ",'" + ed.getDt_Movimento_Pneu_Estoque () + "'" +
		  "," + ed.getOid_Local_Estoque_Origem() +
		  "," + ed.getOid_Local_Estoque_Destino() +
		  ")";
		  executaSQL.executarUpdate (sql);
		  return ed;
	  }
	  catch (SQLException e) {
		  throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "inclui (Movimento_Pneu_EstoqueED ed)");
	  }
  }
  
  public void deleta (Movimento_Pneu_EstoqueED ed) throws Excecoes {
	  try {
		  sql = "DELETE FROM Movimentos_Pneus_Estoques " +
		  "WHERE " +
		  "oid_Movimento_Pneu_Estoque = " + ed.getOid_Movimento_Pneu_Estoque ();
		  executaSQL.executarUpdate (sql);
	  }
	  catch (SQLException e) {
		  throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "deleta (Movimento_Pneu_EstoqueED ed)");
	  }
  }

  public ArrayList lista (Movimento_Pneu_EstoqueED ed) throws Excecoes {

	  ArrayList list = new ArrayList ();
	  try {
		  sql = "SELECT * " +
		  ",locOrig.nm_Local_Estoque as nm_Local_Estoque_Origem "+
		  ",locDest.nm_Local_Estoque as nm_Local_Estoque_Destino "+
		  "FROM " +
		  "Movimentos_Pneus_Estoques ," +
		  "Pneus, " +
		  "Locais_Estoques as locOrig, " +
		  "Locais_Estoques as locDest "+
		  "WHERE " + 
		  "Movimentos_Pneus_Estoques.oid_Pneu = Pneus.oid_Pneu and " +
		  "Movimentos_Pneus_Estoques.oid_Local_Estoque_Origem = locOrig.oid_Local_Estoque and " +
		  "Movimentos_Pneus_Estoques.oid_Local_Estoque_Destino = locDest.oid_Local_Estoque "		  ;
		  if (ed.getOid_Movimento_Pneu_Estoque () > 0) 
			  sql += " AND oid_Movimento_Pneu_Estoque = " + ed.getOid_Movimento_Pneu_Estoque () + " ";
		  else {
			  sql += " AND Movimentos_Pneus_Estoques.oid_Empresa = " + ed.getOid_Empresa() + " ";
			  if (ed.getOid_Pneu() > 0)
				  sql += " AND oid_Pneu = " + ed.getOid_Pneu() + " ";
			  if (doValida(ed.getDt_Movimento_Pneu_Estoque()))
				  sql += " AND dt_Movimento_Pneu_Estoque = '" + ed.getDt_Movimento_Pneu_Estoque() +"' ";
			  if (doValida(ed.getDt_Movimento_Pneu_Estoque_Inicial()))
				  sql += " AND dt_Movimento_Pneu_Estoque > '" + ed.getDt_Movimento_Pneu_Estoque_Inicial() +"' ";
			  if (doValida(ed.getDt_Movimento_Pneu_Estoque_Final()))
				  sql += " AND dt_Movimento_Pneu_Estoque < '" + ed.getDt_Movimento_Pneu_Estoque_Final() +"' ";
			  if (ed.getOid_Local_Estoque_Origem() > 0)
				  sql += " AND oid_Local_Estoque_Origem = " + ed.getOid_Local_Estoque_Origem() + " ";
			  if (ed.getOid_Local_Estoque_Destino() > 0)
				  sql += " AND oid_Local_Estoque_Destino = " + ed.getOid_Local_Estoque_Destino() + " ";
		  }	  
		  sql+= "ORDER BY " +
		  	    "dt_Movimento_Pneu_Estoque";
		  ResultSet rs = executaSQL.executarConsulta (sql);
		  while (rs.next ()) {
			  list.add (populaRegistro(rs));
		  }
	  }
	  catch (SQLException e) {
		  throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "lista (Movimento_Pneu_EstoqueED ed)");
	  }
	  return list;
  }

  public Movimento_Pneu_EstoqueED getByRecord (Movimento_Pneu_EstoqueED ed) throws Excecoes {
    ArrayList lista = lista (ed);
    Iterator iterator = lista.iterator ();
    if (iterator.hasNext ()) {
      return (Movimento_Pneu_EstoqueED) iterator.next ();
    }
    else {
      return new Movimento_Pneu_EstoqueED ();
    }
  }
  
  public Movimento_Pneu_EstoqueED populaRegistro(ResultSet res) throws SQLException {
	  Movimento_Pneu_EstoqueED ed = new Movimento_Pneu_EstoqueED();
	  ed.setOid_Movimento_Pneu_Estoque(res.getInt("oid_Movimento_Pneu_Estoque"));
	  ed.setOid_Empresa(res.getInt("oid_Empresa"));
	  ed.setOid_Pneu(res.getInt("oid_Pneu"));
	  ed.setDt_Movimento_Pneu_Estoque(FormataData.formataDataBT(res.getString("dt_Movimento_Pneu_Estoque")));
	  ed.setOid_Local_Estoque_Origem(res.getLong("oid_Local_Estoque_Origem"));
	  ed.setOid_Local_Estoque_Destino(res.getLong("oid_Local_Estoque_Destino"));
	  ed.setNm_Local_Estoque_Origem(res.getString("nm_Local_Estoque_Origem"));
	  ed.setNm_Local_Estoque_Destino(res.getString("nm_Local_Estoque_Destino"));
	  return ed;	  
  }
  
}