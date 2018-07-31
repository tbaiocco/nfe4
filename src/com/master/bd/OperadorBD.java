/*
 * Created on 24/08/2004
 */
package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.OperadorED;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Andre Valadas
 */
public class OperadorBD {

    private ExecutaSQL executasql;

    public OperadorBD(ExecutaSQL sql) {
        this.executasql = sql;
    }

    public OperadorED inclui(OperadorED ed) throws Excecoes {

        String sql = null;
        long valOid = 0;
        OperadorED OperadorED = new OperadorED();

        try {

            ResultSet rs = executasql.executarConsulta("select oid_operador as result from Operadores ORDER BY oid_operador desc limit 1");

            while (rs.next())
                valOid = rs.getInt("result");

            sql = "insert into Operadores ("
                    + "oid_operador, cd_operador, nm_operador, dm_situacao) values ("
                    + "" + ++valOid + ",'" + ed.getCd_Operador() + "','"
                    + ed.getNm_Operador() + "','" + ed.getDm_Situacao() + "')";

// System.err.println(sql);

            executasql.executarUpdate(sql);
            OperadorED.setOid_Operador(new Integer(Long.toString(valOid)));
        }

        catch (Exception exc) {
        	exc.printStackTrace();
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao incluir Operador");
            excecoes.setMetodo("inclui");
            excecoes.setExc(exc);
            throw excecoes;
        }
        return OperadorED;

    }

    public void altera(OperadorED ed) throws Excecoes {

        String sql = null;

        try {

            sql = " update Operadores set ";
            sql += " cd_operador = '" + ed.getCd_Operador() + "', ";
            sql += " nm_operador = '" + ed.getNm_Operador() + "', ";
            sql += " dm_situacao      = '" + ed.getDm_Situacao() + "' ";
            sql += " where oid_operador = " + ed.getOid_Operador();

            executasql.executarUpdate(sql);
        }

        catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao alterar dados de Operadore");
            excecoes.setMetodo("altera(OperadorED)");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

    public void deleta(OperadorED ed) throws Excecoes {

        String sql = null;

        try {

            sql = "delete from Operadores WHERE oid_operador = ";
            sql += "(" + ed.getOid_Operador() + ")";

            executasql.executarUpdate(sql);
        }

        catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao deletar Operador");
            excecoes.setMetodo("deleta(OperadorED)");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

    public ArrayList lista(OperadorED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();

        try {

            // System.out.println("getCd_Operador"+ed.getCd_Operador());


            sql = " select * from Operadores where 1=1 ";

            if (ed.getCd_Operador() != null && !ed.getCd_Operador().equals("0") && !ed.getCd_Operador().equals("")) {
                sql += " and Operadores.cd_Operador = '" + ed.getCd_Operador()+ "'";
            } else if (ed.getNm_Operador() != null && !String.valueOf(ed.getNm_Operador()).equals("0")) {
                sql += " and nm_Operador like " + ed.getNm_Operador() + "%";
            }
            // System.out.println("lista"+sql);


            ResultSet res = null;
            res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
                OperadorED edVolta = new OperadorED();

                edVolta.setOid_Operador(new Integer(res.getString("oid_Operador")));
                edVolta.setCd_Operador(res.getString("cd_Operador"));
                edVolta.setNm_Operador(res.getString("nm_Operador"));
                edVolta.setDm_Situacao(res.getString("dm_Situacao"));

                // System.out.println("Passou na lista"+edVolta.getCd_Operador());
                list.add(edVolta);
            }
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao listar Operador - SQL=" + sql);
            excecoes.setMetodo("lista(OperadorED)");
            excecoes.setExc(exc);
            throw excecoes;
        }

        return list;
    }

    public OperadorED getByRecord(OperadorED ed) throws Excecoes {

        String sql = null;

        OperadorED edVolta = new OperadorED();

        try {

            sql = " select * from Operadores where 1=1 ";

            if (ed.getCd_Operador() != null && !ed.getCd_Operador().equals("0") && !ed.getCd_Operador().equals("")) {
                sql += " and Operadores.cd_Operador = '" + ed.getCd_Operador()+ "'";
            } else if (ed.getOid_Operador() != null && !String.valueOf(ed.getOid_Operador()).equals("0")) {
                sql += " and oid_Operador = " + ed.getOid_Operador();
            }

            ResultSet res = null;


            // System.err.println("GetByRecord"+ sql);

            res = this.executasql.executarConsulta(sql);
            while (res.next()) {
                edVolta = new OperadorED();
                // System.err.println(sql);
                edVolta.setOid_Operador(new Integer(res.getString("oid_Operador")));
                edVolta.setCd_Operador(res.getString("cd_Operador"));
                edVolta.setNm_Operador(res.getString("nm_Operador"));
                edVolta.setDm_Situacao(res.getString("dm_Situacao"));
            }

        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao recuperar registro");
            excecoes.setMetodo("getByRecord(OperadorED)");
            excecoes.setExc(exc);
            throw excecoes;
        }

        return edVolta;
    }

}