package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.Nivel_ProdutoED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

public class Nivel_ProdutoBD extends BancoUtil {

    private ExecutaSQL executasql;

    public Nivel_ProdutoBD(ExecutaSQL sql) {
        super(sql);
        this.executasql = sql;
    }

    public Nivel_ProdutoED inclui(Nivel_ProdutoED ed) throws Excecoes {

        String sql = null;
        int valOid = 0;
        Nivel_ProdutoED Nivel_ProdutoED = new Nivel_ProdutoED();

        try {

            ResultSet rs = executasql.executarConsulta("select max(oid_Nivel_Produto) as result from Niveis_Produtos");

            while (rs.next())
                valOid = rs.getInt("result");

            sql = "insert into Niveis_Produtos (" + "OID_Nivel_Produto,NM_Nivel_Produto,CD_Nivel_Produto,DM_Nivel_Produto) values (" + "" + ++valOid + ",'" + ed.getNm_Nivel_Produto() + "','"
                    + ed.getCd_Nivel_Produto() + "','" + ed.getDm_Nivel_Produto() + "')";

            executasql.executarUpdate(sql);
            Nivel_ProdutoED.setOid_Nivel_Produto(valOid);
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao incluir Nivel_Produto");
            excecoes.setMetodo("inclui");
            excecoes.setExc(exc);
            throw excecoes;
        }
        return Nivel_ProdutoED;

    }

    public void altera(Nivel_ProdutoED ed) throws Excecoes {

        String sql = null;

        try {

            sql = " update Niveis_Produtos set ";
            sql += " NM_Nivel_Produto = '" + ed.getNm_Nivel_Produto() + "', ";
            sql += " CD_Nivel_Produto = '" + ed.getCd_Nivel_Produto() + "', ";
            sql += " DM_Nivel_Produto      = '" + ed.getDm_Nivel_Produto() + "' ";
            sql += " where oid_Nivel_Produto = " + ed.getOid_Nivel_Produto();

            executasql.executarUpdate(sql);
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao alterar dados de Nivel_Produto");
            excecoes.setMetodo("altera(Nivel_ProdutoED)");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

    public void deleta(Nivel_ProdutoED ed) throws Excecoes {

        String sql = null;

        try {

            sql = "delete from Niveis_Produtos WHERE oid_Nivel_Produto = ";
            sql += "(" + ed.getOid_Nivel_Produto() + ")";

            executasql.executarUpdate(sql);
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao deletar Nivel_Produto");
            excecoes.setMetodo("deleta(Nivel_ProdutoED)");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

    public ArrayList lista(Nivel_ProdutoED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();
        try {

            sql = "SELECT * FROM Niveis_Produtos WHERE 1=1 ";

            if (ed.getOid_Nivel_Produto() > 0)
                sql += " AND OID_Nivel_Produto = " + ed.getOid_Nivel_Produto();
            if (doValida(ed.getCd_Nivel_Produto()))
                sql += " AND CD_Nivel_Produto = '" + ed.getCd_Nivel_Produto() + "'";
            if (doValida(ed.getNm_Nivel_Produto()))
                sql += " AND NM_Nivel_Produto LIKE '%" + ed.getNm_Nivel_Produto() + "%'";
            if (doValida(ed.getDm_Nivel_Produto()) && !ed.getDm_Nivel_Produto().equals("T"))
                sql += " AND Dm_Nivel_Produto = '" + ed.getDm_Nivel_Produto() + "'";
            sql += " ORDER BY cd_nivel_produto ";

            ResultSet res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
                Nivel_ProdutoED edVolta = new Nivel_ProdutoED();

                edVolta.setOid_Nivel_Produto(new Integer(res.getString("oid_Nivel_Produto")).intValue());
                edVolta.setCd_Nivel_Produto(res.getString("cd_Nivel_Produto"));
                edVolta.setNm_Nivel_Produto(res.getString("nm_Nivel_Produto"));
                edVolta.setDm_Nivel_Produto(res.getString("dm_Nivel_Produto"));

                list.add(edVolta);
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
            	"lista()");
        }
        return list;
    }

    public Nivel_ProdutoED getByRecord(Nivel_ProdutoED ed) throws Excecoes {

        String sql = null;
        Nivel_ProdutoED edVolta = new Nivel_ProdutoED();
        
        try {

            sql = " SELECT * FROM Niveis_Produtos WHERE 1=1 ";

            if (ed.getOid_Nivel_Produto() > 0)
                sql += " AND OID_Nivel_Produto = " + ed.getOid_Nivel_Produto();
            if (doValida(ed.getCd_Nivel_Produto()))
                sql += " AND Niveis_Produtos.cd_Nivel_Produto = '" + ed.getCd_Nivel_Produto() + "'";
            if (doValida(ed.getDm_Nivel_Produto()) && !ed.getDm_Nivel_Produto().equals("T"))
                sql += " AND Niveis_Produtos.DM_Nivel_Produto = '" + ed.getDm_Nivel_Produto() + "'";

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next()) {
                edVolta = new Nivel_ProdutoED();
                edVolta.setOid_Nivel_Produto(res.getInt("oid_Nivel_Produto"));
                edVolta.setCd_Nivel_Produto(res.getString("cd_Nivel_Produto"));
                edVolta.setNm_Nivel_Produto(res.getString("nm_Nivel_Produto"));
                edVolta.setDm_Nivel_Produto(res.getString("dm_Nivel_Produto"));
            }
            return edVolta;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
                    "getByRecord()");
        }
    }

    public void geraRelatorio(Nivel_ProdutoED ed) throws Excecoes {

        //    String sql = null;
        //
        //    Nivel_ProdutoED edVolta = new Nivel_ProdutoED();
        //
        //    try{
        //
        //      sql = "select * from Niveis_Produtos where oid_Nivel_Produto > 0";
        //
        //      if (ed.getCD_Nivel_Produto() != null &&
        // !ed.getCD_Nivel_Produto().equals("")){
        //        sql += " and CD_Nivel_Produto = '" + ed.getCD_Nivel_Produto() + "'";
        //      }
        //      if (ed.getCD_Remessa() != null && !ed.getCD_Remessa().equals("")){
        //        sql += " and CD_Remessa = '" + ed.getCD_Remessa() + "'";
        //      }
        //
        //      ResultSet res = null;
        //      res = this.executasql.executarConsulta(sql);
        //
        //      Nivel_ProdutoRL Nivel_Produto_rl = new Nivel_ProdutoRL();
        //      Nivel_Produto_rl.geraRelatProduto(res);
        //    }
        //    catch (Excecoes e){
        //      throw e;
        //    }
        //    catch(Exception exc){
        //      Excecoes exce = new Excecoes();
        //      exce.setExc(exc);
        //      exce.setMensagem("Erro no método listar");
        //      exce.setClasse(this.getClass().getName());
        //      exce.setMetodo("geraRelatorio(Nivel_ProdutoED ed)");
        //    }
        //
    }

}