package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import com.master.ed.Periodo_AbertoED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.bd.ExecutaSQL;

/**
 * @authorRegis Steigleder
 * @serial Periodos_Abertos
 * @serialData 02/12/2005
 */
public class Periodo_AbertoBD extends BancoUtil {

    private ExecutaSQL executasql;

    public Periodo_AbertoBD(ExecutaSQL sql) {
        super(sql);
        this.executasql = sql;
    }

    public Periodo_AbertoED inclui(Periodo_AbertoED ed) throws Excecoes {

        String sql = null;
        try {
            ed.setOid_Periodo_Aberto(getAutoIncremento("oid_Periodo_aberto", "Periodos_Abertos"));

            if (ed.getOid_Periodo_Aberto()==0){
            	ed.setOid_Periodo_Aberto(1);
            }
            sql = " INSERT INTO Periodos_Abertos (" +
            	  "	oid_Periodo_aberto," +
            	  "	dt_Inicial," +
            	  "	dt_Final," +
            	  "	nm_Periodo_Aberto)" +
            	  " VALUES (" +
            	  ed.getOid_Periodo_Aberto() +
            	  ",'" + ed.getDt_Inicial() + "'" +
            	  ",'" + ed.getDt_Final() + "'" +
            	  ",'" + ed.getNm_Periodo_Aberto() + "')";
            executasql.executarUpdate(sql);
        	return ed;

        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "inclui(Periodo_AbertoED ed)");
        }
    }

    public void altera(Periodo_AbertoED ed) throws Excecoes {
        String sql = null;
        try {
            sql =  " UPDATE Periodos_Abertos SET ";
            sql += "oid_Periodo_Aberto = oid_Periodo_Aberto , ";
            if (doValida(ed.getDt_Inicial()))
                sql += " dt_Inicial = '" + ed.getDt_Inicial() +"', ";
            if (doValida(ed.getDt_Final()))
                sql += " dt_Final = '" + ed.getDt_Final() +"', ";
            if (doValida(ed.getNm_Periodo_Aberto()))
                sql += " nm_Periodo_Aberto = '" + ed.getNm_Periodo_Aberto()+"', ";
            sql += " dt_Stamp = '" + ed.getDt_stamp() + "', ";
            sql += " usuario_Stamp = '" + ed.getUsuario_Stamp() + "', ";
            sql += " dm_Stamp = '" + ed.getDm_Stamp() + "' ";
            sql += " WHERE oid_Periodo_Aberto = " + ed.getOid_Periodo_Aberto();
            executasql.executarUpdate(sql);
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            		"altera(Periodo_AbertoED ed)");
        }
    }

    public void deleta(Periodo_AbertoED ed) throws Excecoes {
        String sql = null;
        try {
            sql = " DELETE FROM Periodos_Abertos " +
            	  " WHERE oid_Periodo_Aberto = " + ed.getOid_Periodo_Aberto();
            executasql.executarUpdate(sql);

        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"deleta(Periodo_AbertoED ed)");
        }
    }

    public ArrayList lista(Periodo_AbertoED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();
        try {
            sql = " SELECT * " +
            	  " FROM Periodos_Abertos " +
            	  " WHERE 1=1";
            if (ed.getOid_Periodo_Aberto() > 0)
                sql += "   AND oid_Periodo_Aberto = "+ed.getOid_Periodo_Aberto();
            else {
	            if (doValida(ed.getDt_Inicial()))
	                sql += "   AND dt_Inicial >= '"+ed.getDt_Inicial()+"'";
	            if (doValida(ed.getDt_Final()))
	                sql += "   AND dt_Final <= '"+ed.getDt_Final()+"'";
	            if (doValida(ed.getNm_Periodo_Aberto()))
	                sql += "   AND nm_Periodo_Aberto LIKE '"+ed.getNm_Periodo_Aberto()+"%'";
            }
            sql += " ORDER BY dt_Inicial";
            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                Periodo_AbertoED edVolta = new Periodo_AbertoED();

                edVolta.setOid_Periodo_Aberto(res.getInt("Oid_Periodo_Aberto"));
                edVolta.setDt_Inicial(FormataData.formataDataBT(res.getString("dt_Inicial")));
                edVolta.setDt_Final(FormataData.formataDataBT(res.getString("dt_Final")));
                edVolta.setNm_Periodo_Aberto(res.getString("nm_Periodo_Aberto"));
                list.add(edVolta);
            }
            return list;

        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"lista(Periodo_AbertoED ed)");
        }
    }

    public Periodo_AbertoED getByRecord(Periodo_AbertoED ed) throws Excecoes {
	    String sql = null;
	    Periodo_AbertoED edQBR = new Periodo_AbertoED();
	    try{
			sql = "SELECT " +
			"oid_Periodo_Aberto, " +
			"dt_Inicial, " +
			"dt_Final, " +
			"nm_Periodo_Aberto " +
		    "FROM " +
			"Periodos_Abertos " +
			"where 1=1 ";
			if (ed.getOid_Periodo_Aberto() > 0)
			    sql += "   AND oid_Periodo_Aberto = " + ed.getOid_Periodo_Aberto();
			else {
				if (doValida(ed.getDt_Inicial()))
					sql += "   AND dt_Inicial = '" + ed.getDt_Inicial()+"'";
			    if (doValida(ed.getDt_Inicial()))
			        sql += "   AND dt_Final = '" + ed.getDt_Final()+"'";
			}
			ResultSet res = null;
			res = this.executasql.executarConsulta(sql);
			while (res.next()){
				edQBR.setOid_Periodo_Aberto(res.getLong("Oid_Periodo_Aberto"));
				edQBR.setDt_Inicial(FormataData.formataDataBT(res.getString("dt_Inicial")));
				edQBR.setDt_Final(FormataData.formataDataBT(res.getString("dt_Final")));
				edQBR.setNm_Periodo_Aberto(res.getString("nm_Periodo_Aberto"));
			}
	    }
	    catch(Exception e){
	      throw new Excecoes(e.getMessage(), e, getClass().getName(), "getByRecord(SistemaED ed)");
	    }
	    return edQBR;
    }
    public boolean isAberto(String dt) throws Excecoes {
	    String sql = null;
	    Periodo_AbertoED edQBR = new Periodo_AbertoED();
	    try{

			sql = "SELECT " +
			" oid_Periodo_Aberto " +
		    " FROM " +
			" Periodos_Abertos " +
			" WHERE " ;
			if (doValida(dt))
				sql += " '" + dt +"' ";
			sql +=" BETWEEN dt_Inicial AND dt_Final " ;

			ResultSet res = null;
			res = this.executasql.executarConsulta(sql);
			while (res.next()){
				edQBR.setOid_Periodo_Aberto(res.getLong("Oid_Periodo_Aberto"));
			}
	    }
	    catch(Exception e){
	      throw new Excecoes(e.getMessage(), e, getClass().getName(), "isAberto(String dt)");
	    }
	    return edQBR.getOid_Periodo_Aberto() > 0 ? true : false ;
    }
}