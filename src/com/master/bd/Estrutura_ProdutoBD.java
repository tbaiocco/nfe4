package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import com.master.ed.Estrutura_ProdutoED;
import com.master.ed.Nivel_ProdutoED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.bd.ExecutaSQL;

public class Estrutura_ProdutoBD extends BancoUtil {

    private ExecutaSQL executasql;

    public Estrutura_ProdutoBD(ExecutaSQL sql) {
        super(sql);
        this.executasql = sql;
    }

    public Estrutura_ProdutoED inclui(Estrutura_ProdutoED ed) throws Excecoes {

        String sql = null;
        try {

            ed.setOid_Estrutura_Produto(getAutoIncremento("oid_Estrutura_Produto", "Estruturas_Produtos"));
            if (!doValida(ed.getCd_Estrutura_Produto()))
                ed.setCd_Estrutura_Produto(String.valueOf(getAutoIncremento("CD_Estrutura_Produto", "Estruturas_Produtos")));
            ed.setCd_Estrutura_Produto(JavaUtil.LFill(ed.getCd_Estrutura_Produto(), 3, true));
            
            sql = " INSERT INTO Estruturas_Produtos (" + 
            	  "		OID_Estrutura_Produto" +
            	  "		,NM_Estrutura_Produto" +
            	  "		,CD_Estrutura_Produto" +
            	  "		,DM_Estrutura_Produto" +
            	  "		,Oid_Nivel_Produto1" +
            	  "		,Oid_Nivel_Produto2" +
            	  "		,Oid_Nivel_Produto3" +
            	  "		,Oid_Nivel_Produto4" +
            	  "		,Oid_Nivel_Produto5) " +
            	  " VALUES ("
                    + ed.getOid_Estrutura_Produto() +
                    ",'" + getValueDef(ed.getNm_Estrutura_Produto(), "") + "'" +
                    ",'" + getValueDef(ed.getCd_Estrutura_Produto(), "") + "'" +
                    ",'" + getValueDef(ed.getDm_Estrutura_Produto(), "") + "'" +
                    "," + ed.getOid_Nivel_Produto1()+ 
                    "," + ed.getOid_Nivel_Produto2() + 
                    "," + ed.getOid_Nivel_Produto3() + 
                    "," + ed.getOid_Nivel_Produto4() + 
                    "," + ed.getOid_Nivel_Produto5() + ")";

            executasql.executarUpdate(sql);
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "inclui()");
        }
        return ed;
    }

    public void altera(Estrutura_ProdutoED ed) throws Excecoes {

        String sql = null;

        try {

            sql = " UPDATE Estruturas_Produtos SET ";
            sql += " NM_Estrutura_Produto = '" + getValueDef(ed.getNm_Estrutura_Produto(), "") + "', ";
            sql += " CD_Estrutura_Produto = '" + getValueDef(ed.getCd_Estrutura_Produto(), "") + "', ";
            sql += " DM_Estrutura_Produto = '" + getValueDef(ed.getDm_Estrutura_Produto(),"") + "', ";
            sql += " oid_nivel_produto1   = " + ed.getOid_Nivel_Produto1() + ", ";
            sql += " oid_nivel_produto2   = " + ed.getOid_Nivel_Produto2() + ", ";
            sql += " oid_nivel_produto3   = " + ed.getOid_Nivel_Produto3() + ", ";
            sql += " oid_nivel_produto4   = " + ed.getOid_Nivel_Produto4() + ", ";
            sql += " oid_nivel_produto5   = " + ed.getOid_Nivel_Produto5();
            sql += " WHERE oid_Estrutura_Produto = " + ed.getOid_Estrutura_Produto();

            executasql.executarUpdate(sql);
        }
        catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "altera()");
        }
    }

    public void deleta(Estrutura_ProdutoED ed) throws Excecoes {

        String sql = null;

        try {

            sql = " DELETE FROM Estruturas_Produtos WHERE oid_Estrutura_Produto = ";
            sql += "(" + ed.getOid_Estrutura_Produto() + ")";

            executasql.executarUpdate(sql);
        }
        catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "deleta()");
        }
    }

    public ArrayList lista(Estrutura_ProdutoED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();

        try {

            sql = "SELECT * FROM Estruturas_Produtos WHERE 1=1 ";

            if (ed.getOid_Estrutura_Produto() > 0)
                sql += " AND oid_Estrutura_Produto = " + ed.getOid_Estrutura_Produto();
            if (doValida(ed.getCd_Estrutura_Produto()))
                sql += " AND Cd_Estrutura_Produto = '" + ed.getCd_Estrutura_Produto() + "'";
            if (doValida(ed.getDm_Estrutura_Produto()))
                sql += " AND Dm_Estrutura_Produto = '" + ed.getDm_Estrutura_Produto() + "'";
            if (ed.getOid_Nivel_Produto1() > 0)
                sql += " AND oid_Nivel_Produto1 = " + ed.getOid_Nivel_Produto1();
            if (ed.getOid_Nivel_Produto2() > 0)
                sql += " AND oid_Nivel_Produto2 = " + ed.getOid_Nivel_Produto2();
            if (ed.getOid_Nivel_Produto3() > 0)
                sql += " AND oid_Nivel_Produto3 = " + ed.getOid_Nivel_Produto3();
            if (ed.getOid_Nivel_Produto4() > 0)
                sql += " AND oid_Nivel_Produto4 = " + ed.getOid_Nivel_Produto4();
            
            sql += " ORDER BY cd_Estrutura_Produto";

            ResultSet res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
                Estrutura_ProdutoED edVolta = new Estrutura_ProdutoED();

                edVolta.setOid_Estrutura_Produto(res.getInt("oid_Estrutura_Produto"));
                edVolta.setCd_Estrutura_Produto(res.getString("cd_Estrutura_Produto"));
                edVolta.setNm_Estrutura_Produto(res.getString("nm_Estrutura_Produto"));
                edVolta.setDm_Estrutura_Produto(res.getString("dm_Estrutura_Produto"));

                edVolta.setOid_Nivel_Produto1(res.getInt("Oid_Nivel_Produto1"));
                edVolta.setOid_Nivel_Produto2(res.getInt("Oid_Nivel_Produto2"));
                edVolta.setOid_Nivel_Produto3(res.getInt("Oid_Nivel_Produto3"));
                edVolta.setOid_Nivel_Produto4(res.getInt("Oid_Nivel_Produto4"));
                edVolta.setOid_Nivel_Produto5(res.getInt("Oid_Nivel_Produto5"));

                if (edVolta.getOid_Nivel_Produto1() > 0) {
                    Nivel_ProdutoED edNivel = new Nivel_ProdutoBD(executasql).getByRecord(new Nivel_ProdutoED(edVolta.getOid_Nivel_Produto1()));
                    edVolta.setCd_Nivel_Produto1(edNivel.getCd_Nivel_Produto());
                    edVolta.setNm_Nivel_Produto1(edNivel.getNm_Nivel_Produto());
                }
                if (edVolta.getOid_Nivel_Produto2() > 0) {
                    Nivel_ProdutoED edNivel = new Nivel_ProdutoBD(executasql).getByRecord(new Nivel_ProdutoED(edVolta.getOid_Nivel_Produto2()));
                    edVolta.setCd_Nivel_Produto2(edNivel.getCd_Nivel_Produto());
                    edVolta.setNm_Nivel_Produto2(edNivel.getNm_Nivel_Produto());
                }
                if (edVolta.getOid_Nivel_Produto3() > 0) {
                    Nivel_ProdutoED edNivel = new Nivel_ProdutoBD(executasql).getByRecord(new Nivel_ProdutoED(edVolta.getOid_Nivel_Produto3()));
                    edVolta.setCd_Nivel_Produto3(edNivel.getCd_Nivel_Produto());
                    edVolta.setNm_Nivel_Produto3(edNivel.getNm_Nivel_Produto());
                }
                if (edVolta.getOid_Nivel_Produto4() > 0) {
                    Nivel_ProdutoED edNivel = new Nivel_ProdutoBD(executasql).getByRecord(new Nivel_ProdutoED(edVolta.getOid_Nivel_Produto4()));
                    edVolta.setCd_Nivel_Produto4(edNivel.getCd_Nivel_Produto());
                    edVolta.setNm_Nivel_Produto4(edNivel.getNm_Nivel_Produto());
                }
                if (edVolta.getOid_Nivel_Produto5() > 0) {
                    Nivel_ProdutoED edNivel = new Nivel_ProdutoBD(executasql).getByRecord(new Nivel_ProdutoED(edVolta.getOid_Nivel_Produto5()));
                    edVolta.setCd_Nivel_Produto5(edNivel.getCd_Nivel_Produto());
                    edVolta.setNm_Nivel_Produto5(edNivel.getNm_Nivel_Produto());
                }
                list.add(edVolta);
            }
            return list;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "lista()");
        }
    }

    public Estrutura_ProdutoED getByRecord(Estrutura_ProdutoED ed) throws Excecoes {

        try {
            Iterator it = this.lista(ed).iterator();
            if (it.hasNext())
                return (Estrutura_ProdutoED) it.next();
            else return new Estrutura_ProdutoED();
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "getByRecord()");
        }
    }
}