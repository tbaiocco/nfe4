/*
 * Created on 12/11/2004
 *
 */
package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import com.master.ed.Modelo_PneuED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.JavaUtil;
import com.master.util.bd.ExecutaSQL;
import com.master.util.Data;

/**
 * @author Régis
 * @serial Modelos de pneus
 * @serialData 06/2007
 */
public class Modelo_PneuBD  extends BancoUtil {

  private ExecutaSQL executaSQL;

  String sql = null;

  public Modelo_PneuBD (ExecutaSQL executaSQL) {
	  super(executaSQL);
	  this.executaSQL = executaSQL;
  }

    public Modelo_PneuED inclui (Modelo_PneuED ed) throws Excecoes {
	  try {
		  ed.setDt_stamp(Data.getDataDMY());
		  ed.setOid_Modelo_Pneu(getAutoIncremento ("oid_Modelo_Pneu", "Modelos_Pneus"));
		  sql = "INSERT INTO Modelos_Pneus (" +
		  "oid_Modelo_Pneu, " +
		  "oid_Empresa, " +
		  "cd_Modelo_Pneu, " +
		  "nm_Modelo_Pneu" ;
		  if (ed.getOid_Empresa() > 0){
			  sql+=",oid_Fabricante_Pneu ";
		  }
		  sql+=",dm_Stamp" +
		  	   ",dt_Stamp" +
		  	   ",usuario_Stamp"+
		  	   ",oid_usuario"+
		  	   ",time_millis"+
			  ") " +
		  "values (" +
		  ed.getOid_Modelo_Pneu () +
		  " , " + ed.getOid_Empresa () +
		  " ,'" + (doValida(ed.getCD_Modelo_Pneu()) ? ed.getCD_Modelo_Pneu() : "") +
		  "','" + ed.getNM_Modelo_Pneu () + "'";

		  if (ed.getOid_Empresa() > 0){
			  sql+="," + ed.getOid_Fabricante_Pneu();
		  }
		  sql+= ",'I'" +
		  		",'" + ed.getDt_stamp() + "'" +
		  		",'" + ed.getUsuario_Stamp() + "'" +
		  		"," + ed.getUser() +
			  	"," + ed.getTime_millis() +
			  ")";
		  executaSQL.executarUpdate (sql);
		  return ed;
	  }
	  catch (SQLException e) {
		  throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "inclui (Modelo_PneuED ed)");
	  }
  }

  public void altera (Modelo_PneuED ed) throws Excecoes {
	  try {
		  sql = "UPDATE Modelos_Pneus SET " ;
		  sql+= "cd_modelo_pneu = '" + ed.getCD_Modelo_Pneu () + "'";
		  sql+= ",nm_modelo_pneu = '" + ed.getNM_Modelo_Pneu () + "' " ;
		  sql+= ",dm_Stamp = 'A'" +
	  	   		",dt_Stamp = '" + Data.getDataDMY() + "' " +
	  	   		",usuario_Stamp = '"+ed.getUsuario_Stamp() + "' "+
	  	   		",oid_usuario = " + ed.getUser() +
	  	   		",time_millis = " + ed.getTime_millis() ;
		  if (ed.getOid_Empresa()>0)
			  sql+= ",oid_Fabricante_Pneu = " + ed.getOid_Fabricante_Pneu() +" "  ;
		  sql+= "where " ;
		  sql+= "oid_Modelo_Pneu = " + ed.getOid_Modelo_Pneu ();

		  executaSQL.executarUpdate (sql);
	  }
	  catch (SQLException e) {
		  throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "altera (Modelo_PneuED ed)");
	  }
  }

  public void delete (Modelo_PneuED ed) throws Excecoes {
	  try {
		  sql = "DELETE FROM Modelos_Pneus " +
		  "WHERE " +
		  "oid_Modelo_Pneu = " + ed.getOid_Modelo_Pneu ();
		  executaSQL.executarUpdate (sql);
	  }
	  catch (SQLException e) {
		  throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "delete (Modelo_PneuED ed)");
	  }
  }

  public ArrayList lista (Modelo_PneuED ed) throws Excecoes {

	  ArrayList list = new ArrayList ();
	  try {
		  sql = "SELECT * " +
		  		",Modelos_Pneus.usuario_Stamp as usu_Stmp " +
		  		",Modelos_Pneus.dt_Stamp as dt_Stmp " +
		  		",Modelos_Pneus.dm_Stamp as dm_Stmp " +
		  "FROM " +
		  "Modelos_Pneus, Marcas_Pneus ";

		  sql +="WHERE 1=1 " ;
		  sql += " and Modelos_Pneus.oid_Fabricante_Pneu = Marcas_Pneus.oid_Marca_Pneu ";

		  if (ed.getOid_Modelo_Pneu () > 0)
			  sql += " and oid_Modelo_Pneu = " + ed.getOid_Modelo_Pneu () + " ";
		  else{
			  if (ed.getOid_Empresa() > 0) {
				  sql += " and Modelos_Pneus.oid_Empresa = " + ed.getOid_Empresa() + " ";
				  if (ed.getOid_Fabricante_Pneu() > 0)
					  sql += " and Modelos_Pneus.oid_Fabricante_Pneu = " + ed.getOid_Fabricante_Pneu() + " ";

			  }
			  if (doValida(ed.getCD_Modelo_Pneu()))
				  sql += " and cd_Modelo_Pneu = '" + ed.getCD_Modelo_Pneu () + "' ";
			  if (doValida(ed.getNM_Modelo_Pneu()))
				  sql += " and nm_Modelo_Pneu like '%" + ed.getNM_Modelo_Pneu () + "%' ";
		  	}
		  sql+= " ORDER BY " +
		  "nm_Modelo_Pneu";
		  ResultSet rs = executaSQL.executarConsulta (sql);
		  while (rs.next ()) {
			  list.add (populaRegistro(rs));
		  }
	  }
	  catch (SQLException e) {
		  throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "lista (Modelo_PneuED ed)");
	  }
	  return list;
  }

  public Modelo_PneuED getByRecord (Modelo_PneuED ed , int oid) throws Excecoes {
    ArrayList lista = lista (new Modelo_PneuED (oid , "" , ""));
    Iterator iterator = lista.iterator ();
    if (iterator.hasNext ()) {
      return (Modelo_PneuED) iterator.next ();
    }
    else {
      return new Modelo_PneuED ();
    }
  }

  public Modelo_PneuED getByRecord (Modelo_PneuED ed ) throws Excecoes {
	  Modelo_PneuED pnED = new Modelo_PneuED();
	  try {
		  sql = "SELECT * " +
	  		",Modelos_Pneus.usuario_Stamp as usu_Stmp " +
	  		",Modelos_Pneus.dt_Stamp as dt_Stmp " +
	  		",Modelos_Pneus.dm_Stamp as dm_Stmp " +
	  		"FROM " +
	  		"Modelos_Pneus " ;
		  if (ed.getOid_Empresa() > 0) {
			  sql +=",Marcas_Pneus ";
		  }
		  sql +="WHERE 1=1 " ;

		  if (ed.getOid_Modelo_Pneu () > 0)
			  sql += " and oid_Modelo_Pneu = " + ed.getOid_Modelo_Pneu () + " ";
		  else{
			  if (ed.getOid_Empresa() > 0) {
				  sql += " and Modelos_Pneus.oid_Fabricante_Pneu = Marcas_Pneus.oid_Marca_Pneu ";
				  sql += " and Modelos_Pneus.oid_Empresa = " + ed.getOid_Empresa() + " ";
				  if (ed.getOid_Fabricante_Pneu() > 0)
					  sql += " and Modelos_Pneus.oid_Fabricante_Pneu = " + ed.getOid_Fabricante_Pneu() + " ";

			  }
			  if (doValida(ed.getCD_Modelo_Pneu()))
				  sql += " and cd_Modelo_Pneu = '" + ed.getCD_Modelo_Pneu () + "' ";
			  if (doValida(ed.getNM_Modelo_Pneu()))
				  sql += " and nm_Modelo_Pneu = '" + ed.getNM_Modelo_Pneu () + "' ";
		  	}
		  sql+= " ORDER BY " +
		  "nm_Modelo_Pneu";
		  ResultSet rs = executaSQL.executarConsulta (sql);
		  while (rs.next ()) {
			  pnED = populaRegistro(rs);
		  }
	  }
	  catch (SQLException e) {
		  throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "lista (Modelo_PneuED ed)");
	  }
	  return pnED;
  }



  public Modelo_PneuED getByRecord (int oid) throws Excecoes {
	    ArrayList lista = lista (new Modelo_PneuED (oid , "" , ""));
	    Iterator iterator = lista.iterator ();
	    if (iterator.hasNext ()) {
	      return (Modelo_PneuED) iterator.next ();
	    }
	    else {
	      return new Modelo_PneuED ();
	    }
	  }
  public Modelo_PneuED getByCodigo (String codigo) throws Excecoes {
    ArrayList lista = lista (new Modelo_PneuED (0 , codigo , ""));
    Iterator iterator = lista.iterator ();
    if (iterator.hasNext ()) {
      return (Modelo_PneuED) iterator.next ();
    }
    else {
      return new Modelo_PneuED ();
    }
  }

  public Modelo_PneuED populaRegistro(ResultSet res) throws SQLException {
	  Modelo_PneuED ed = new Modelo_PneuED();
	  ed.setOid_Modelo_Pneu(res.getInt("oid_Modelo_Pneu"));
	  ed.setOid_Empresa(res.getInt("oid_Empresa"));
	  ed.setCD_Modelo_Pneu(res.getString("cd_Modelo_Pneu"));
	  ed.setNM_Modelo_Pneu(res.getString("nm_Modelo_Pneu"));
	  if (ed.getOid_Empresa() > 0) {
		  ed.setOid_Fabricante_Pneu(res.getInt("oid_Marca_Pneu"));
		  ed.setNm_Fabricante_Pneu(res.getString("nm_Marca_Pneu"));
	  }
	  ed.setUsuario_Stamp(res.getString("usu_Stmp"));
	  //Padrao
		if(!"31/12/1969 21:00:00".equals(FormataData.formataDataHoraTB(new Date(res.getLong("time_millis"))))
				&& JavaUtil.doValida(res.getString("usuario_Stamp"))){
			ed.setMsg_Stamp(("I".equals(res.getString("dm_Stamp"))? "Incluído":"Alterado") + " por " + res.getString("usuario_Stamp")+ " em " + FormataData.formataDataHoraTB(new Date(res.getLong("time_millis"))));
		}
	  return ed;
  }

}