/*
 * Created on 09/03/2005
 *
 */
package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.master.ed.Permisso_PaisED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.Mensagens;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Tiago Sauter Lauxen
 *
 */
public class Permisso_PaisBD extends BancoUtil {
    private ExecutaSQL executaSQL;
    
    public Permisso_PaisBD(ExecutaSQL executaSQL) {
        this.executaSQL = executaSQL;        
    }
    
	public Permisso_PaisED inclui(Permisso_PaisED ed)
	throws Excecoes {
		ed.setOid_Permisso_Pais(getAutoIncremento("oid_Permisso_Pais", "Permissos_Paises"));
		String sql =
			" insert into Permissos_Paises " +
			"   (oid_Permisso_Pais " +
			"   ,oid_pais_origem " +
			"   ,oid_pais_destino " +
			"   ,oid_aidof " +
			"   ,NR_permisso) " +
			" values (" + ed.getOid_Permisso_Pais() + ", " +
			"         " + ed.getOid_Pais_Origem() + ", " +
			"         " + ed.getOid_Pais_Destino() + ", " +
			"         " + ed.getOid_aidof() + ", " +
			"         " + ed.getNR_Permisso() + ")";
        try {
            executaSQL.executarUpdate(sql);
            return getByRecord(ed.getOid_Permisso_Pais());
        } catch (SQLException e) {
            throw new Excecoes(e.getMessage(), e, this.getClass().getName(), "inclui(Permisso_PaisED ed)");
        }
	}
    
	public void altera(Permisso_PaisED ed)
	throws Excecoes {
		String sql =
			" update Permissos_Paises " +
			" set oid_pais_origem = " + ed.getOid_Pais_Origem() +
			"    ,oid_pais_destino = " + ed.getOid_Pais_Destino() +
			"    ,oid_aidof = " + ed.getOid_aidof() +
			"    ,NR_permisso = '" + ed.getNR_Permisso() + "' " +
			" where oid_Permisso_Pais = " + ed.getOid_Permisso_Pais();
        try {
            executaSQL.executarUpdate(sql);
        } catch (SQLException e) {
            throw new Excecoes(e.getMessage(), e, this.getClass().getName(), "altera(Permisso_PaisED ed)");
        }
    }
    
	public void delete(Permisso_PaisED ed)
	throws Excecoes {
		String sql =
			" delete from Permissos_Paises " +
			" where oid_Permisso_Pais = " + ed.getOid_Permisso_Pais();
        try {
        	
        	// System.out.println("Permisso_PaisBD.delete() sql = "+sql);
        	
            executaSQL.executarUpdate(sql);
        } catch (SQLException e) {
            throw new Excecoes(e.getMessage(), e, this.getClass().getName(), "delete(Permisso_PaisED ed)");
        }
    }
    
	public List lista(Permisso_PaisED ed) throws Excecoes {
	    List list = new ArrayList();
	    String sql =
	        " select Permissos_Paises.oid_Permisso_Pais " +
			"       ,Permissos_Paises.oid_pais_origem " +
			"       ,Permissos_Paises.oid_pais_destino " +
			"       ,Permissos_Paises.oid_aidof " +
			"       ,Permissos_Paises.NR_permisso " +
			"       ,Pais_Origem.CD_Pais as CD_Pais_Origem " +
			"       ,Pais_Origem.NM_Pais as NM_Pais_Origem " +
			"       ,Pais_Destino.CD_Pais as CD_Pais_Destino " +
			"       ,Pais_Destino.NM_Pais as NM_Pais_Destino " +
			"       ,AIDOF.CD_AIDOF " +
	        " from Permissos_Paises," +
	        "      Paises Pais_Origem, " +
	        "      Paises Pais_Destino," +
	        "      AIDOF " +
	        " where Permissos_Paises.oid_Pais_Origem = Pais_Origem.oid_Pais " +
	        "   and Permissos_Paises.oid_Pais_Destino = Pais_Destino.oid_Pais " +
	        "   and Permissos_Paises.oid_aidof = AIDOF.oid_aidof ";
	    if (ed.getOid_Permisso_Pais() > 0) {
	        sql += " and Permissos_Paises.oid_Permisso_Pais = " + ed.getOid_Permisso_Pais();
	    }
	    if (ed.getOid_Pais_Origem() > 0) {
	        sql += " and Permissos_Paises.oid_Pais_Origem = " + ed.getOid_Pais_Origem();
	    }
	    if (ed.getOid_Pais_Destino() > 0) {
	        sql += " and Permissos_Paises.oid_Pais_Destino = " + ed.getOid_Pais_Destino();
	    }
	    if (ed.getOid_aidof() > 0) {
	        sql += " and Permissos_Paises.oid_aidof = " + ed.getOid_aidof();
	    }
	    if (doValida(ed.getNR_Permisso())) {
	        sql += " and Permissos_Paises.NR_Permisso = " + ed.getNR_Permisso();
	    }
        ResultSet res = executaSQL.executarConsulta(sql);
        try {
            while (res.next()) {
                list.add(new Permisso_PaisED(res.getInt("oid_Permisso_Pais"), 
                        res.getInt("oid_pais_origem"), 
                        res.getString("CD_Pais_Origem"), 
                        res.getString("NM_Pais_Origem"), 
                        res.getInt("oid_Pais_Destino"), 
                        res.getString("CD_Pais_Destino"), 
                        res.getString("NM_Pais_Destino"), 
                        res.getInt("oid_aidof"), 
                        res.getString("CD_AIDOF"),
                        res.getString("NR_Permisso")));
            }
        } catch (SQLException e) {
            throw new Excecoes(e.getMessage(), e, this.getClass().getName(), "lista");
        }
        return list;
    }
    
    public Permisso_PaisED getByRecord(int oid) throws Excecoes {
        return this.doGetByRecord(new Permisso_PaisED(oid, 0, 0, 0, ""));
    }
    private Permisso_PaisED doGetByRecord(Permisso_PaisED ed) throws Excecoes {
        List lista = this.lista(ed);
        Iterator iterator = lista.iterator();
        if (iterator.hasNext()) {
            return (Permisso_PaisED)iterator.next();
        } else throw new Mensagens("Registro não encontrado!");
    }
}
