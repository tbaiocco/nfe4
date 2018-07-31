/*
 * Created on 29/06/2005
 *
 */
package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.master.ed.Conhecimento_RCEED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.Mensagens;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Tiago
 *
 */
public class Conhecimento_RCEBD extends BancoUtil {
    private ExecutaSQL executaSQL;
    
    public Conhecimento_RCEBD(ExecutaSQL executaSQL) {
        this.executaSQL = executaSQL;        
    }
    
    public Conhecimento_RCEED inclui(Conhecimento_RCEED ed)
    throws Excecoes {
        ed.setOid_Conhecimento_RCE(getAutoIncremento("oid_Conhecimento_RCE", "Conhecimentos_RCE"));
        String sql =
            " insert into Conhecimentos_RCE " +
            "   (oid_Conhecimento_RCE " +
            "   ,oid_RCE " +
            "   ,oid_Conhecimento) " +
            " values " +
            "   (" + ed.getOid_Conhecimento_RCE() +
            "   ," + ed.getOid_RCE() +
            "   ," + getSQLString(ed.getOid_Conhecimento()) + ")";

        try {
            executaSQL.executarUpdate(sql);
            return ed;
        } catch (SQLException e) {
            throw new Excecoes(e.getMessage(), e, getClass().getName(), "inclui(ed)");
        }
    }
    
    public Conhecimento_RCEED altera(Conhecimento_RCEED ed)
    throws Excecoes {
        String sql =
            " update Conhecimentos_RCE " +
            " set oid_RCE = " + ed.getOid_RCE() +
            "    ,oid_Conhecimento = " + getSQLString(ed.getOid_Conhecimento()) +
            " where oid_Conhecimento_RCE = " + ed.getOid_Conhecimento_RCE();
        try {
            executaSQL.executarUpdate(sql);
            return ed;
        } catch (SQLException e) {
            throw new Excecoes(e.getMessage(), e, getClass().getName(), "altera(ed)");
        }
    }
    
    public void delete(Conhecimento_RCEED ed)
    throws Excecoes {
        String sql =
            " delete from Conhecimentos_RCE " +
            " where 1 = 1 ";
        if (ed.getOid_Conhecimento_RCE() > 0) {
            sql += " and oid_Conhecimento_RCE = " + ed.getOid_Conhecimento_RCE();
        } else if (ed.getOid_RCE() > 0) {
            sql += " and oid_RCE = " + ed.getOid_RCE();
        } else throw new Mensagens("Nenhum filtro para exclusão!");
        try {
            executaSQL.executarUpdate(sql);
        } catch (SQLException e) {
            throw new Excecoes(e.getMessage(), e, getClass().getName(), "delete(ed)");
        }
    }
    
    public List lista(Conhecimento_RCEED ed)
    throws Excecoes {
        List toReturn = new ArrayList();
        String sql =
            " select CRCE.oid_Conhecimento_RCE " +
            "       ,CRCE.oid_RCE " +
            "       ,CRCE.oid_Conhecimento " +
            "       ,RCE.NR_RCE " +
            "       ,CON.NR_Conhecimento " +
            " from Conhecimentos_RCE CRCE " +
            "     ,RCE " +
            "     ,Conhecimentos CON " +
            " where CRCE.oid_RCE = RCE.oid_RCE " +
            "   and CRCE.oid_Conhecimento = CON.oid_Conhecimento ";
        if (ed.getOid_Conhecimento_RCE() > 0) {
            sql += " and CRCE.oid_Conhecimento_RCE = " + ed.getOid_Conhecimento_RCE();
        }
        if (ed.getOid_RCE() > 0) {
            sql += " and RCE.oid_RCE = " + ed.getOid_RCE();
        }
        if (doValida(ed.getOid_Conhecimento())) {
            sql += " and CRCE.oid_Conhecimento = " + getSQLString(ed.getOid_Conhecimento());
        }
        ResultSet res = executaSQL.executarConsulta(sql);
        try {
            try {
                while (res.next()) {
                    Conhecimento_RCEED edVolta = new Conhecimento_RCEED();
                    edVolta.setOid_Conhecimento_RCE(res.getInt("oid_Conhecimento_RCE"));
                    edVolta.setOid_RCE(res.getInt("oid_RCE"));
                    edVolta.setOid_Conhecimento(res.getString("oid_Conhecimento"));
                    edVolta.setNR_RCE(res.getInt("NR_RCE"));
                    edVolta.setNR_Conhecimento(res.getLong("NR_Conhecimento"));
                    toReturn.add(edVolta);
                }
                return toReturn;
            } catch (SQLException e) {
                throw new Excecoes(e.getMessage(), e, getClass().getName(), "lista(ed)");
            }
        } finally {
            closeResultset(res);
        }
    }

    public Conhecimento_RCEED getByOid(int oid) throws Excecoes {
        Conhecimento_RCEED ed = new Conhecimento_RCEED();
        ed.setOid_Conhecimento_RCE(oid);
        return doGetByRecord(ed);
    }

    public List getByOid_RCE(int oid_RCE) throws Excecoes {
        Conhecimento_RCEED ed = new Conhecimento_RCEED();
        ed.setOid_RCE(oid_RCE);
        return lista(ed);
    }

    public List getByOid_Conhecimento(String oid_Conhecimento) throws Excecoes {
        Conhecimento_RCEED ed = new Conhecimento_RCEED();
        ed.setOid_Conhecimento(oid_Conhecimento);
        return lista(ed);
    }

    private Conhecimento_RCEED doGetByRecord(Conhecimento_RCEED ed) throws Excecoes {
        List lista = lista(ed);
        Iterator iterator = lista.iterator();
        if (iterator.hasNext()) {
            return (Conhecimento_RCEED)iterator.next();
        } else throw new Mensagens("Registro não encontrado!");
    }
    
    public boolean exists(Conhecimento_RCEED ed) throws Excecoes {
        Conhecimento_RCEED filtro = new Conhecimento_RCEED();
        filtro.setOid_RCE(ed.getOid_RCE());
        filtro.setOid_Conhecimento(ed.getOid_Conhecimento());
        try {
            Conhecimento_RCEED retorno = doGetByRecord(filtro);
            return (retorno != null && retorno.getOid_Conhecimento_RCE() > 0);
        } catch (Mensagens e) {
            return false;
        }
    }
}
