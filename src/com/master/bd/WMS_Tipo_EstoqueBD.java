package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.WMS_Tipo_EstoqueED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.Utilitaria;
import com.master.util.bd.ExecutaSQL;

public class WMS_Tipo_EstoqueBD  {

    private ExecutaSQL executasql;
	Utilitaria util = new Utilitaria(executasql);

    public WMS_Tipo_EstoqueBD(ExecutaSQL sql) {
        this.executasql = sql;
    }

    public WMS_Tipo_EstoqueED inclui(WMS_Tipo_EstoqueED ed) throws Excecoes {

        String sql = null;
        try {
            ed.setOid_Tipo_Estoque(util.getAutoIncremento("oid_Tipo_Estoque", "Tipos_Estoques"));

            sql = " INSERT INTO Tipos_Estoques (" +
            	  "		 Oid_Tipo_Estoque" +
            	  "		,Nm_Tipo_Estoque" +
            	  "		,cd_tipo_estoque" +
            	  "		,DM_Situacao) " +
            	  " VALUES (" + ed.getOid_Tipo_Estoque() +
            	  ",'" + ed.getNm_Tipo_Estoque() + "'" +
            	  ",'"+ ed.getCd_Tipo_Estoque() + "'" +
            	  ",'" + ed.getDM_Situacao() + "')";

            executasql.executarUpdate(sql);
            return ed;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "inclui()");
        }
    }

    public void altera(WMS_Tipo_EstoqueED ed) throws Excecoes {

        String sql = null;
        try {

            sql = " UPDATE Tipos_Estoques SET ";
            sql += " nm_Tipo_Estoque = '" + ed.getNm_Tipo_Estoque() + "', ";
            sql += " DM_Situacao = '" + ed.getDM_Situacao() + "', ";
            sql += " cd_Tipo_Estoque = '" + ed.getCd_Tipo_Estoque() + "' ";
            sql += " WHERE oid_Tipo_Estoque = " + ed.getOid_Tipo_Estoque();

            executasql.executarUpdate(sql);
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "altera()");
        }
    }

    public void deleta(WMS_Tipo_EstoqueED ed) throws Excecoes {

        String sql = null;
        try {

            sql = " DELETE FROM Tipos_Estoques " +
            	  " WHERE Oid_Tipo_Estoque = " + ed.getOid_Tipo_Estoque();

            executasql.executarUpdate(sql);
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "deleta()");
        }
    }

    public WMS_Tipo_EstoqueED getByRecord(WMS_Tipo_EstoqueED ed) throws Excecoes {

        String sql = null;
        WMS_Tipo_EstoqueED edVolta = new WMS_Tipo_EstoqueED();
        try {

            sql = " SELECT * FROM Tipos_Estoques" +
            	  " WHERE 1 = 1";

            if (ed.getOid_Tipo_Estoque() > 0)
                sql += " AND oid_Tipo_Estoque = " + ed.getOid_Tipo_Estoque();
            if (util.doValida(ed.getCd_Tipo_Estoque()))
                sql += " AND CD_Tipo_Estoque = '" + ed.getCd_Tipo_Estoque() +"'";

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next()) {
                edVolta = new WMS_Tipo_EstoqueED();
                edVolta.setOid_Tipo_Estoque(res.getInt("Oid_Tipo_Estoque"));
                edVolta.setCd_Tipo_Estoque(res.getString("Cd_Tipo_Estoque"));
                edVolta.setNm_Tipo_Estoque(res.getString("Nm_Tipo_Estoque"));
                edVolta.setDM_Situacao(res.getString("DM_Situacao"));
            }
            return edVolta;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "getByRecord()");
        }
    }

    public WMS_Tipo_EstoqueED getByCD_Tipo_Estoque(WMS_Tipo_EstoqueED ed) throws Excecoes {
        return this.getByRecord(ed);
    }

    public WMS_Tipo_EstoqueED getByOid(int oid) throws Excecoes {
    	WMS_Tipo_EstoqueED ed = new WMS_Tipo_EstoqueED();
    	ed.setOid_Tipo_Estoque(oid);
        return this.getByRecord(ed);
    }

    public ArrayList lista(WMS_Tipo_EstoqueED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();

        try {

            sql = " SELECT * FROM Tipos_Estoques";
            sql += " WHERE 1=1 ";

            if (util.doValida(ed.getNm_Tipo_Estoque()))
                sql += " and nm_Tipo_Estoque LIKE '" + ed.getNm_Tipo_Estoque() + "%'";
            if (util.doValida(ed.getCd_Tipo_Estoque()))
                sql += " and cd_Tipo_Estoque LIKE '" + ed.getCd_Tipo_Estoque() + "%'";
            sql += " ORDER BY NM_Tipo_Estoque";

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next()) {
                WMS_Tipo_EstoqueED edVolta = new WMS_Tipo_EstoqueED();

                edVolta.setOid_Tipo_Estoque(res.getInt("Oid_Tipo_Estoque"));
                edVolta.setNm_Tipo_Estoque(res.getString("Nm_Tipo_Estoque"));
                edVolta.setCd_Tipo_Estoque(res.getString("Cd_Tipo_Estoque"));
                edVolta.setDM_Situacao(res.getString("DM_Situacao"));

                list.add(edVolta);
            }
            return list;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "lista()");
        }
    }

    public void geraRelatorio(WMS_Tipo_EstoqueED ed) throws Excecoes {

        //    String sql = null;
        //
        //    WMS_Tipo_EstoqueED edVolta = new WMS_Tipo_EstoqueED();
        //
        //    try{
        //
        //      sql = "select * from Tipo_Estoques where Oid_Tipo_Estoque > 0";
        //
        //      if (ed.getCd_Tipo_Estoque() != null &&
        // !ed.getCd_Tipo_Estoque().equals("")){
        //        sql += " and Cd_Tipo_Estoque = '" + ed.getCd_Tipo_Estoque() + "'";
        //      }
        //      if (ed.getCd_Remessa() != null && !ed.getCd_Remessa().equals("")){
        //        sql += " and Cd_Remessa = '" + ed.getCd_Remessa() + "'";
        //      }
        //
        //      ResultSet res = null;
        //      res = this.executasql.executarConsulta(sql);
        //
        //      WMS_Tipo_EstoqueRL WMS_Tipo_Estoque_rl = new WMS_Tipo_EstoqueRL();
        //      WMS_Tipo_Estoque_rl.geraRelatEstoque(res);
        //    }
        //    catch (Excecoes e){
        //      throw e;
        //    }
        //    catch(Exception exc){
        //      Excecoes exce = new Excecoes();
        //      exce.setExc(exc);
        //      exce.setMensagem("Erro no método listar");
        //      exce.setClasse(this.getClass().getName());
        //      exce.setMetodo("geraRelatorio(WMS_Tipo_EstoqueED ed)");
        //    }
        //
    }
}