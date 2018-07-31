/*
 * Created on 12/11/2004
 *
 */
package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import com.master.ed.Medicao_PneuED;
import com.master.ed.PneuED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;
import com.master.util.FormataData;

/**
 * @author Régis
 * @serial Modelos de pneus
 * @serialData 08/2007
 */
public class Medicao_PneuBD  extends BancoUtil {
	
  private ExecutaSQL executaSQL;
  
  String sql = null;
  
  public Medicao_PneuBD (ExecutaSQL executaSQL) {
	  super(executaSQL);  
	  this.executaSQL = executaSQL;
  }

  public Medicao_PneuED inclui (Medicao_PneuED ed) throws Excecoes {
	  try {
		  ed.setOid_Medicao_Pneu(getAutoIncremento ("oid_Medicao_Pneu", "Medicoes_Pneus"));
		  sql = "INSERT INTO Medicoes_Pneus (" +
		  "oid_Medicao_Pneu, " +
		  "oid_Empresa, " +
		  "oid_Pneu, " +
		  "nr_Vida, " +
		  "dt_Medicao_Pneu, " +
		  "nr_Mm_Sulco, " +
		  "nr_Km_Acumulada_Pneu " +
		  ") " +
		  "values (" + 
		  ed.getOid_Medicao_Pneu () +
		  ", " + ed.getOid_Empresa () +
		  ", " + ed.getOid_Pneu () +
		  "," + ed.getNr_Vida() + 
		  ",'" + ed.getDt_Medicao_Pneu () + "'" +
		  "," + ed.getNr_Mm_Sulco() +
		  "," + ed.getNr_Km_Acumulada_Pneu() +
		  ")";
		  executaSQL.executarUpdate (sql);
		  return ed;
	  }
	  catch (SQLException e) {
		  throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "inclui (Medicao_PneuED ed)");
	  }
  }
  
  /**
   * Inclui uma medição a partir de uma troca de pneu - PneuED
   */
  public Medicao_PneuED inclui (PneuED pnED) throws Excecoes {
	  try {
		  Medicao_PneuED ed = new Medicao_PneuED();
		  ed.setOid_Empresa(pnED.getOid_Empresa());
		  ed.setOid_Pneu(pnED.getOid_Pneu());
		  ed.setNr_Vida(pnED.getNr_Vida());
		  ed.setDt_Medicao_Pneu(pnED.getDt_Entrada());
		  ed.setNr_Mm_Sulco(pnED.getMM_Atual());
		  ed.setNr_Km_Acumulada_Pneu(pnED.getNr_Km_Acumulada());
		  ed = this.inclui(ed);
		  return ed;
	  }
	  catch (Excecoes e) {
		  throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "inclui (Medicao_PneuED ed)");
	  }
  }

  public void altera (Medicao_PneuED ed) throws Excecoes {
	  try {
		  sql = "UPDATE Medicoes_Pneus SET " +
		  "nr_Mm_Sulco = " + ed.getNr_Mm_Sulco () +
		  "where " +
		  "oid_Medicao_Pneu = " + ed.getOid_Medicao_Pneu ();
		  executaSQL.executarUpdate (sql);
	  }
	  catch (SQLException e) {
		  throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "altera (Medicao_PneuED ed)");
	  }
  }

  public void delete (Medicao_PneuED ed) throws Excecoes {
	  try {
		  sql = "DELETE FROM Medicoes_Pneus " +
		  "WHERE " +
		  "oid_Medicao_Pneu = " + ed.getOid_Medicao_Pneu ();
		  executaSQL.executarUpdate (sql);
	  }
	  catch (SQLException e) {
		  throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "delete (Medicao_PneuED ed)");
	  }
  }

  public ArrayList lista (Medicao_PneuED ed) throws Excecoes {

	  ArrayList list = new ArrayList ();
	  try {
		  sql = "SELECT * " +
		  "FROM " +
		  "Medicoes_Pneus ," +
		  "Pneus " +
		  "WHERE " + 
		  "Medicoes_Pneus.oid_Pneu = Pneus.oid_Pneu ";
		  if (ed.getOid_Medicao_Pneu () > 0) 
			  sql += " AND oid_Medicao_Pneu = " + ed.getOid_Medicao_Pneu () + " ";
		  else {
			  if (ed.getOid_Pneu() > 0)
				  sql += " AND oid_Pneu = " + ed.getOid_Pneu() + " ";
			  else {
				  sql += " AND medicoes_pneus.oid_Empresa = " + ed.getOid_Empresa() + " ";
				  if (doValida(ed.getDt_Medicao_Pneu()))
					  sql += " AND dt_medicao_pneu = '" + ed.getDt_Medicao_Pneu() +"' ";
				  if (doValida(ed.getDt_Medicao_Pneu_Inicial()))
					  sql += " AND dt_medicao_pneu > '" + ed.getDt_Medicao_Pneu_Inicial() +"' ";
				  if (doValida(ed.getDt_Medicao_Pneu_Final()))
					  sql += " AND dt_medicao_pneu < '" + ed.getDt_Medicao_Pneu_Final() +"' ";
				  if (doValida(ed.getNr_Fogo()))
					  sql += " AND nr_Fogo like '" + ed.getNr_Fogo() +"%'";
			  }
		  }	  
		  sql+= "ORDER BY " +
		  "Pneus.nr_Fogo " +
		  ",dt_Medicao_Pneu";
		  ResultSet rs = executaSQL.executarConsulta (sql);
		  while (rs.next ()) {
			  list.add (populaRegistro(rs));
		  }
	  }
	  catch (SQLException e) {
		  throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "lista (Medicao_PneuED ed)");
	  }
	  return list;
  }

  public Medicao_PneuED getByRecord (Medicao_PneuED ed) throws Excecoes {
    ArrayList lista = lista (ed);
    Iterator iterator = lista.iterator ();
    if (iterator.hasNext ()) {
      return (Medicao_PneuED) iterator.next ();
    }
    else {
      return new Medicao_PneuED ();
    }
    
  }

  /**
   * Última medição do pneu
   * @param ed
   * @return
   * @throws Excecoes
   */
  public Medicao_PneuED getMedicao (Medicao_PneuED ed) throws Excecoes {
	  Medicao_PneuED mpED = new Medicao_PneuED();
	  try {
		  sql = "SELECT * " +
		  "FROM " +
		  "Medicoes_Pneus ," +
		  "Pneus " +
		  "WHERE " + 
		  "Medicoes_Pneus.oid_Pneu = Pneus.oid_Pneu and "+
		  "Medicoes_Pneus.oid_Pneu = " + ed.getOid_Pneu() + " and " + 
		  "Medicoes_Pneus.nr_Vida = " + ed.getNr_Vida() + " " +
		  "order by " +
		  "Medicoes_Pneus.dt_Medicao_Pneu," +
		  "Medicoes_Pneus.oid_Medicao_Pneu";
		  ResultSet rs = executaSQL.executarConsulta (sql);
		  while (rs.next ()) {
			  rs.last(); // Vai pra o último registro
			  mpED = populaRegistro(rs);
		  }
	  }
	  catch (SQLException e) {
		  throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "lista (Medicao_PneuED ed)");
	  }
	  return mpED;
	  
  }
  
  public Medicao_PneuED populaRegistro(ResultSet res) throws SQLException {
	  Medicao_PneuED ed = new Medicao_PneuED();
	  ed.setOid_Medicao_Pneu(res.getInt("oid_Medicao_Pneu"));
	  ed.setOid_Empresa(res.getInt("oid_Empresa"));
	  ed.setOid_Pneu(res.getInt("oid_Pneu"));
	  ed.setNr_Vida(res.getInt("nr_Vida"));
	  ed.setDt_Medicao_Pneu(FormataData.formataDataBT(res.getString("dt_Medicao_Pneu")));
	  ed.setNr_Mm_Sulco(res.getDouble("nr_Mm_Sulco"));
	  ed.setNr_Km_Acumulada_Pneu(res.getDouble("nr_Km_Acumulada_Pneu"));
	  ed.setNr_Fogo(res.getString("nr_Fogo"));
	  return ed;	  
  }
  
}