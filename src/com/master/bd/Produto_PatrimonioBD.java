package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.master.ed.Produto_PatrimonioED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;
import com.master.util.Utilitaria;

public class Produto_PatrimonioBD extends BancoUtil {


    private ExecutaSQL executasql;
    Utilitaria util = new Utilitaria(executasql);

    public Produto_PatrimonioBD(ExecutaSQL sql) {
        this.executasql = sql;
    }

    public Produto_PatrimonioED inclui(Produto_PatrimonioED ed) throws Excecoes {

        String sql = null;
        try {

        	ed.setOid_Produto_Patrimonio(String.valueOf((getAutoIncremento ("OID_Produto_Patrimonio" , "Produtos_Patrimonios"))));

        	if (ed.getOid_Produto_Patrimonio() != null && "0".equals(ed.getOid_Produto_Patrimonio())){
        		ed.setOid_Produto_Patrimonio("1");
        	}

            sql = " INSERT INTO Produtos_Patrimonios (" +
                  "     OID_Produto_Patrimonio" +
                  "     ,NM_Produto_Patrimonio" +
                  "     ,DT_STAMP" +
                  "     ,USUARIO_STAMP" +
                  "     ,DM_STAMP" +
                  "     ,OID_Conta " +
                  "     ,OID_Categoria) " +
                  " VALUES (" +
                         ed.getOid_Produto_Patrimonio() +
                  ",'" + ed.getNm_Produto_Patrimonio() + "'" +
                  ",'" + ed.getDt_stamp() + "'" +
                  ",'" + ed.getUsuario_Stamp() + "'" +
                  ",'" + ed.getDm_Stamp() + "',";
                sql += ed.getOid_Conta() + ",";
                sql += ed.getOid_Categoria() + ")";

                executasql.executarUpdate(sql);

                ed.setOid_Produto_Patrimonio(ed.getOid_Produto_Patrimonio());
                return ed;

        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "inclui()");
        }
    }

    public void altera(Produto_PatrimonioED ed) throws Excecoes {

        String sql = null;
        try {

            sql = " UPDATE Produtos_Patrimonios SET ";
            sql +="     Nm_Produto_Patrimonio = '" + ed.getNm_Produto_Patrimonio() + "',";
            sql +="     DT_STAMP = '" + ed.getDt_stamp() + "', ";
            sql +="     USUARIO_STAMP = '" + ed.getUsuario_Stamp() + "', ";
            sql +="     DM_STAMP = '" + ed.getDm_Stamp() + "', ";
            sql +="     OID_Categoria = " + ed.getOid_Categoria() + ", ";
            sql +="     OID_Conta = " + ed.getOid_Conta() ;
            sql +=" WHERE oid_Produto_Patrimonio = " + ed.getOid_Produto_Patrimonio();

            executasql.executarUpdate(sql);
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "alterar()");
        }
    }

    public void deleta(Produto_PatrimonioED ed) throws Excecoes {

        String sql = null;
        try {

            sql = " DELETE FROM Produtos_Patrimonios WHERE oid_Produto_Patrimonio = " + ed.getOid_Produto_Patrimonio() ;

            executasql.executarUpdate(sql);
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "deleta()");
        }
    }

    public ArrayList lista(Produto_PatrimonioED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();
        try {

            sql = " SELECT Prod_Pat.* " +
	              "       ,Cat.oid_Categoria " +
	              "       ,Cat.nm_Categoria " +
	              "       ,con.oid_Conta " +
	              "       ,con.cd_Conta " +
	              "       ,con.nm_Conta " +
            	  " FROM Produtos_Patrimonios Prod_Pat, Categorias Cat, Contas con " +
                  " WHERE Prod_Pat.oid_Categoria = Cat.oid_Categoria " +
                  " AND Prod_Pat.oid_Conta = con.oid_Conta " ;
            if (util.doValida(ed.getOid_Produto_Patrimonio()))
                sql += " and Prod_Pat.oid_Produto_Patrimonio = " + ed.getOid_Produto_Patrimonio();
            else {
                if (util.doValida(ed.getNm_Produto_Patrimonio()))
                    sql += " and Prod_Pat.Nm_Produto_Patrimonio = '" + ed.getNm_Produto_Patrimonio() + "'";
	            if (util.doValida(String.valueOf(ed.getOid_Categoria()))){
	                sql += " and Prod_Pat.Oid_Categoria = " + ed.getOid_Categoria()  ;
	            }
	            if (util.doValida(String.valueOf(ed.getOid_Conta()))){
	                sql += " and Prod_Pat.Oid_Conta = " + ed.getOid_Conta()  ;
	            }
            }
            sql +=" ORDER BY oid_Produto_Patrimonio ";

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                Produto_PatrimonioED edVolta = new Produto_PatrimonioED();

                edVolta.setOid_Produto_Patrimonio(res.getString("oid_Produto_Patrimonio"));
                edVolta.setNm_Produto_Patrimonio(res.getString("Nm_Produto_Patrimonio"));
                edVolta.setOid_Categoria(new Integer(res.getInt("Oid_Categoria")));
                edVolta.setNm_Categoria(res.getString("Nm_Categoria"));
                edVolta.setOid_Conta(new Integer(res.getInt("Oid_Conta")));
                edVolta.setNm_Conta(res.getString("Nm_Conta"));
                edVolta.setCd_Conta(res.getString("Cd_Conta"));

                list.add(edVolta);
            }
            return list;

        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "lista()");
        }
    }

    public Produto_PatrimonioED getByRecord(Produto_PatrimonioED ed) throws Excecoes {

        Produto_PatrimonioED edVolta = new Produto_PatrimonioED();
        try {

            String sql = " SELECT Prod_Pat.* " +
                "       ,Cat.oid_Categoria " +
                "       ,Cat.nm_Categoria " +
                "       ,con.oid_Conta " +
                "       ,con.cd_Conta " +
                "       ,con.nm_Conta " +
                " FROM Produtos_Patrimonios Prod_Pat" +
                "      ,Categorias Cat, Contas con" +
                " WHERE Prod_Pat.oid_Categoria = Cat.oid_Categoria " +
                " AND Prod_Pat.oid_Conta = con.oid_Conta " +
                " and Prod_Pat.oid_Produto_Patrimonio = " + ed.getOid_Produto_Patrimonio() ;

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                edVolta = new Produto_PatrimonioED();
                edVolta.setOid_Produto_Patrimonio(res.getString("oid_Produto_Patrimonio"));
                edVolta.setNm_Produto_Patrimonio(res.getString("Nm_Produto_Patrimonio"));
                edVolta.setOid_Categoria(new Integer(res.getInt("Oid_Categoria")));
                edVolta.setNm_Categoria(res.getString("Nm_Categoria"));
                edVolta.setOid_Conta(new Integer(res.getInt("Oid_Conta")));
                edVolta.setNm_Conta(res.getString("Nm_Conta"));
                edVolta.setCd_Conta(res.getString("Cd_Conta"));
            }
            return edVolta;
        } catch (SQLException e) {
            throw new Excecoes(e.getMessage(), e, getClass().getName(), "getByRecord()");
        }
    }
}
