package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.Centro_CustoED;
import com.master.util.Excecoes;
import com.master.util.Utilitaria;
import com.master.util.bd.ExecutaSQL;

public class Centro_CustoBD {

    private ExecutaSQL executasql;
    Utilitaria util = new Utilitaria();

    public Centro_CustoBD(ExecutaSQL sql) {
        util = new Utilitaria(sql);
        this.executasql = sql;
    }

    public Centro_CustoED inclui(Centro_CustoED ed) throws Excecoes {

        String sql = null;

        try {

            ed.setOid_Centro_Custo(new Integer(util.getAutoIncremento("oid_Centro_Custo", "Centros_Custos")));

            sql = " insert into Centros_Custos (OID_Centro_Custo, cd_centro_custo, NM_Centro_Custo, DT_STAMP, USUARIO_STAMP, DM_Aplicacao) values ";
            sql += "(" + ed.getOid_Centro_Custo() + ",'" + ed.getCd_Centro_Custo() + "','" + ed.getNm_Centro_Custo() + "','" + ed.getDt_stamp() + "',";
            sql += "'" + ed.getUsuario_Stamp() + "','" + ed.getDM_Aplicacao() + "')";

            executasql.executarUpdate(sql);

        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(),  this.getClass().getName(),
                    "inclui()");
        }
        return ed;
    }

    public void altera(Centro_CustoED ed) throws Excecoes {

        String sql = null;

        try {

            sql = " update Centros_Custos set ";
            sql += " cd_Centro_Custo = '" + ed.getCd_Centro_Custo() + "', ";
            sql += " NM_Centro_Custo = '" + ed.getNm_Centro_Custo() + "', ";
            sql += " DT_STAMP = '" + ed.getDt_stamp() + "', ";
            sql += " USUARIO_STAMP = '" + ed.getUsuario_Stamp() + "', ";
            sql += " DM_Aplicacao = '" + ed.getDM_Aplicacao() + "' ";
            sql += " where oid_Centro_Custo = " + ed.getOid_Centro_Custo();

            executasql.executarUpdate(sql);
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(),  this.getClass().getName(),
            	"altera()");
        }
    }

    public void deleta(Centro_CustoED ed) throws Excecoes {

        String sql = null;

        try {

            sql = "delete from Centros_Custos WHERE oid_Centro_Custo = ";
            sql += ed.getOid_Centro_Custo();

            executasql.executarUpdate(sql);
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(),  this.getClass().getName(),
            	"deleta()");
        }
    }

    public ArrayList lista(Centro_CustoED ed) throws Excecoes {


        String sql = null;
        ArrayList list = new ArrayList();

        try {
            sql = "select * from Centros_Custos where oid_centro_custo > 0";

            if (ed.getOid_Centro_Custo() != null && ed.getOid_Centro_Custo().intValue() > 0)
                sql += " and oid_Centro_Custo = " + ed.getOid_Centro_Custo();
            if (util.doValida(ed.getCd_Centro_Custo()))
                sql += " and cd_Centro_Custo = '" + ed.getCd_Centro_Custo() + "'";
            if (util.doValida(ed.getNm_Centro_Custo()))
                sql += " and NM_Centro_Custo LIKE '%" + ed.getNm_Centro_Custo()+ "%'";

            if (util.doValida(ed.getDM_Aplicacao()))
                sql += " and DM_Aplicacao = '" + ed.getDM_Aplicacao() + "'";

            sql += " ORDER BY cd_Centro_Custo";

            ResultSet res = this.executasql.executarConsulta(sql);
            //popula
            while (res.next())
            {
                Centro_CustoED edVolta = new Centro_CustoED();
                edVolta.setOid_Centro_Custo(new Integer(res.getString("oid_centro_custo")));
                edVolta.setNm_Centro_Custo(res.getString("nm_centro_Custo"));
                edVolta.setCd_Centro_Custo(res.getString("cd_centro_custo"));

                list.add(edVolta);
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(),  this.getClass().getName(),
            	"lista()");
        }

        return list;
    }

    public Centro_CustoED getByRecord(Centro_CustoED ed) throws Excecoes {

        String sql = null;

        Centro_CustoED edVolta = new Centro_CustoED();

        try {

            sql = " select * from centros_custos where oid_centro_custo > 0";

            if (ed.getOid_Centro_Custo() != null && ed.getOid_Centro_Custo().intValue() > 0) {
                sql += " and centros_custos.OID_Centro_Custo = " + ed.getOid_Centro_Custo();
            }else{
              sql += " and centros_custos.cd_Centro_Custo = '" + ed.getCd_Centro_Custo () + "'";
            }
            
            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next()) {
                edVolta = new Centro_CustoED();
                edVolta.setOid_Centro_Custo(new Integer(res.getInt("oid_centro_custo")));
                edVolta.setNm_Centro_Custo(res.getString("nm_centro_custo"));
                edVolta.setCd_Centro_Custo(res.getString("cd_centro_custo"));
                edVolta.setDM_Aplicacao(res.getString("DM_Aplicacao"));
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(),  this.getClass().getName(),
            	"getByRecord()");
        }
        return edVolta;
    }

    public void geraRelatorio(Centro_CustoED ed) throws Excecoes {

        //    String sql = null;
        //
        //    Centro_CustoED edVolta = new Centro_CustoED();
        //
        //    try{
        //
        //      sql = "select * from Centro_Custos where oid_Centro_Custo > 0";
        //
        //      if (ed.getDM_Aplicacao() != null &&
        // !ed.getDM_Aplicacao().equals("")){
        //        sql += " and DM_Aplicacao = '" + ed.getDM_Aplicacao() + "'";
        //      }
        //      if (ed.getCD_Remessa() != null && !ed.getCD_Remessa().equals("")){
        //        sql += " and CD_Remessa = '" + ed.getCD_Remessa() + "'";
        //      }
        //
        //      ResultSet res = null;
        //      res = this.executasql.executarConsulta(sql);
        //
        //      Centro_CustoRL Centro_Custo_rl = new Centro_CustoRL();
        //      Centro_Custo_rl.geraRelatEstoque(res);
        //    }
        //    catch (Excecoes e){
        //      throw e;
        //    }
        //    catch(Exception exc){
        //      Excecoes exce = new Excecoes();
        //      exce.setExc(exc);
        //      exce.setMensagem("Erro no método listar");
        //      exce.setClasse(this.getClass().getName());
        //      exce.setMetodo("geraRelatorio(Centro_CustoED ed)");
        //    }
        //
    }

}
