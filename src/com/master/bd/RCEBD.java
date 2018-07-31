/*
 * Created on 28/06/2005
 *
 */
package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.master.ed.RCEED;
import com.master.ed.RCERelED;
import com.master.rl.JasperRL;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Tiago
 *
 */
public class RCEBD extends BancoUtil {
    private ExecutaSQL executaSQL;

    public RCEBD(ExecutaSQL executaSQL) {
        this.executaSQL = executaSQL;
    }

    public RCEED inclui(RCEED ed)
    throws Excecoes {
        ed.setOid_RCE(getAutoIncremento("oid_Rce", "RCE"));

        String sql=" SELECT oid_parametro_filial , nr_proximo_rce FROM Parametros_Filiais WHERE oid_Unidade=" + ed.getOid_Unidade();
        ResultSet res = executaSQL.executarConsulta(sql);
        try {
            while (res.next()) {
              ed.setNR_RCE(res.getInt("nr_proximo_rce")+1);
              sql=" UPDATE Parametros_Filiais SET nr_proximo_rce = " + ed.getNR_RCE() + " WHERE oid_parametro_filial=" + res.getInt("oid_parametro_filial");
              executaSQL.executarUpdate(sql);
            }


        } catch (SQLException e) {
            throw new Excecoes(" Erro ao localizar Número da RCE.");
        }


        if (ed.getNR_RCE() <= 0) {
            ed.setNR_RCE(getAutoIncremento("NR_RCE", "RCE"));
        }
        ed.setDT_Lancamento(Data.getDataDMY());
        sql =
            " insert into RCE " +
            "   (oid_RCE " +
            "   ,NR_RCE " +
            "   ,oid_Unidade " +
            "   ,NM_Destinatario " +
            "   ,DT_Lancamento " +
            "   ,DT_Referencia) " +
            " values " +
            "   (" + ed.getOid_RCE() +
            "   ," + ed.getNR_RCE() +
            "   ," + ed.getOid_Unidade() +
            "   ," + getSQLString(ed.getNM_Destinatario()) +
            "   ," + getSQLDate(ed.getDT_Lancamento()) +
            "   ," + getSQLDate(ed.getDT_Referencia()) + ")";

        try {
            executaSQL.executarUpdate(sql);
            return ed;
        } catch (SQLException e) {
            throw new Excecoes(e.getMessage(), e, getClass().getName(), "inclui(ed)");
        }
    }

    public RCEED altera(RCEED ed)
    throws Excecoes {
        String sql =
            " update RCE " +
            " set NR_RCE = " + ed.getNR_RCE() +
            "    ,oid_Unidade = " + ed.getOid_Unidade() +
            "    ,NM_Destinatario = " + getSQLString(ed.getNM_Destinatario()) +
            "    ,DT_Lancamento = " + getSQLDate(ed.getDT_Lancamento()) +
            "    ,DT_Referencia = " + getSQLDate(ed.getDT_Referencia()) +
            " where oid_RCE = " + ed.getOid_RCE();
        try {
            executaSQL.executarUpdate(sql);
            return ed;
        } catch (SQLException e) {
            throw new Excecoes(e.getMessage(), e, getClass().getName(), "altera(ed)");
        }
    }

    public void delete(RCEED ed)
    throws Excecoes {
        String sql =
            " delete from RCE " +
            " where oid_RCE = " + ed.getOid_RCE();
        try {
            executaSQL.executarUpdate(sql);
        } catch (SQLException e) {
            throw new Excecoes(e.getMessage(), e, getClass().getName(), "delete(ed)");
        }
    }

    public List lista(RCEED ed)
    throws Excecoes {
        List toReturn = new ArrayList();
        String sql =
            " select RCE.oid_RCE " +
            "       ,RCE.NR_RCE " +
            "       ,RCE.NM_Destinatario " +
            "       ,RCE.DT_Lancamento " +
            "       ,RCE.DT_Referencia " +
            "       ,UNI.oid_Unidade " +
            "       ,UNI.CD_Unidade " +
            "       ,PESUNI.NM_Fantasia as NM_Unidade " +
            " from RCE " +
            "     ,Unidades UNI " +
            "     ,Pessoas PESUNI " +
            " where RCE.oid_Unidade = UNI.oid_Unidade " +
            "   and UNI.oid_Pessoa = PESUNI.oid_Pessoa ";
        if (ed.getOid_RCE() > 0) {
            sql += " and RCE.oid_RCE = " + ed.getOid_RCE();
        }
        if (ed.getNR_RCE() > 0) {
            sql += " and RCE.NR_RCE = " + ed.getNR_RCE();
        }
        if (ed.getOid_Unidade() > 0) {
            sql += " and RCE.oid_Unidade = " + ed.getOid_Unidade();
        }
        if (doValida(ed.getNM_Destinatario())) {
            sql += " and RCE.NM_Destinatario like " + getSQLStringLike(ed.getNM_Destinatario());
        }
        if (doValida(ed.getDT_Lancamento())) {
            sql += " and RCE.DT_Lancamento = " + getSQLDate(ed.getDT_Lancamento());
        }
        if (doValida(ed.getDT_Referencia())) {
            sql += " and RCE.DT_Referencia = " + getSQLDate(ed.getDT_Referencia());
        }
        ResultSet res = executaSQL.executarConsulta(sql);
        try {
            try {
                while (res.next()) {
                    RCEED edVolta = new RCEED();
                    edVolta.setOid_RCE(res.getInt("oid_RCE"));
                    edVolta.setNR_RCE(res.getInt("NR_RCE"));
                    edVolta.setNM_Destinatario(res.getString("NM_Destinatario"));
                    edVolta.setDT_Lancamento(FormataData.formataDataBT(res.getDate("DT_Lancamento")));
                    edVolta.setDT_Referencia(FormataData.formataDataBT(res.getDate("DT_Referencia")));
                    edVolta.setOid_Unidade(res.getInt("oid_Unidade"));
                    edVolta.setCD_Unidade(res.getString("CD_Unidade"));
                    edVolta.setNM_Unidade(res.getString("NM_Unidade"));
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

    public RCEED getByRecord(RCEED ed) throws Excecoes {
        return doGetByRecord(ed);
    }

    public RCEED getByOid(int oid) throws Excecoes {
        RCEED ed = new RCEED();
        ed.setOid_RCE(oid);
        return getByRecord(ed);
    }

    public RCEED getByNumero(int oid_Unidade, int numero) throws Excecoes {
        RCEED ed = new RCEED();
        ed.setOid_Unidade(oid_Unidade);
        ed.setNR_RCE(numero);
        return getByRecord(ed);
    }

    private RCEED doGetByRecord(RCEED ed) throws Excecoes {
        List lista = lista(ed);
        Iterator iterator = lista.iterator();
        if (iterator.hasNext()) {
            return (RCEED)iterator.next();
        } else throw new Mensagens("Registro não encontrado!");
    }

    private List listaRelRCE(RCEED filtro) throws Excecoes {
        List toReturn = new ArrayList();
        int cta = 0;
        String sql =
            " select  " +
            "      RCE.NR_RCE " +
            "     ,PES.NM_Razao_Social as NM_Unidade " +
            "     ,RCE.NM_Destinatario " +
            "     ,CON.NR_Conhecimento " +
            "     ,RCE.DT_Referencia " +
            " from RCE " +
            "     ,Conhecimentos_RCE CRCE " +
            "     ,Conhecimentos CON " +
            "     ,Unidades UNI " +
            "     ,Pessoas PES " +
            " where RCE.oid_RCE = CRCE.oid_RCE " +
            "   and CRCE.oid_Conhecimento = CON.oid_Conhecimento " +
            "   and RCE.oid_Unidade = UNI.oid_Unidade " +
            "   and UNI.oid_Pessoa = PES.oid_Pessoa " ;
        if (filtro.getOid_RCE() > 0) {
            sql += " and RCE.oid_RCE = " + filtro.getOid_RCE();
        }
        sql += " order by RCE.oid_RCE " +
        		"        ,CON.oid_Conhecimento ";
//// System.out.println("RCE ROFL > "+sql);
        ResultSet res = executaSQL.executarConsulta(sql);
        try {
            try {
                RCERelED edVolta = new RCERelED();
                edVolta.setTX_Notas_Fiscais("");
                cta = 0;
                while (res.next()) {
                	if (cta > 11){
                		cta=0;
                        toReturn.add(edVolta);
                        edVolta = new RCERelED();
                        edVolta.setTX_Notas_Fiscais("");
                	}
                	cta++;
                	edVolta.setNM_Unidade(res.getString("NM_Unidade"));
                	edVolta.setNM_Destinatario(res.getString("NM_Destinatario"));
                	edVolta.setNR_RCE(res.getInt("NR_RCE"));
                	edVolta.setDT_Referencia(FormataData.formataDataBT(res.getDate("DT_Referencia")));
                    edVolta.setTX_Notas_Fiscais(edVolta.getTX_Notas_Fiscais()  + String.valueOf(res.getLong("NR_Conhecimento"))+ " "  );
                }
                toReturn.add(edVolta);
            } catch (SQLException e) {
                throw new Excecoes(e.getMessage(), e, getClass().getName(), "listaRelRCE(RCEED ed)");
            }
        } finally {
            closeResultset(res);
        }
        return toReturn;
    }

    public void relRCE(RCEED filtro) throws Excecoes {
        filtro.setLista(listaRelRCE(filtro));
        new JasperRL(filtro).geraRelatorio();
    }
}
