package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.master.ed.CategoriaED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;
import com.master.util.Utilitaria;

public class CategoriaBD extends BancoUtil {


    private ExecutaSQL executasql;
    Utilitaria util = new Utilitaria(executasql);

    public CategoriaBD(ExecutaSQL sql) {
        this.executasql = sql;
    }

    public CategoriaED inclui(CategoriaED ed) throws Excecoes {

        String sql = null;
        try {

        	ed.setOid_Categoria(String.valueOf((getAutoIncremento ("OID_Categoria" , "Categorias"))));

        	if (ed.getOid_Categoria() != null && "0".equals(ed.getOid_Categoria())){
        		ed.setOid_Categoria("1");
        	}

            sql = " INSERT INTO Categorias (" +
                  "     OID_Categoria" +
                  "     ,NM_Categoria" +
                  "     ,DT_STAMP" +
                  "     ,USUARIO_STAMP" +
                  "     ,DM_STAMP" +
                  "     ,Pe_Fator_Anual" +
                  "     ,NR_Anos" +
                  "     ,OID_CONTA" +
                  "     ,OID_Conta_Credora_Venda" +
                  "     ,OID_Conta_Devedora_Venda" +
                  "     ,OID_Conta_Credora_Baixa_Depreciacao" +
                  "     ,OID_Conta_Devedora_Baixa_Depreciacao" +
                  "     ,OID_Conta_Credora_Ganho_Venda" +
                  "     ,OID_Conta_Devedora_Ganho_Venda" +
                  "     ,OID_Conta_Credora_Perda_Venda" +
                  "     ,OID_Conta_Devedora_Perda_Venda" +
                  " ) " +
                  " VALUES (" +
                         ed.getOid_Categoria() +
                  ",'" + ed.getNm_Categoria() + "'" +
                  ",'" + ed.getDt_stamp() + "'" +
                  ",'" + ed.getUsuario_Stamp() + "'" +
                  ",'" + ed.getDm_Stamp() + "',";
                sql += ed.getPe_Fator_Anual() + ",";
                sql += ed.getNr_Anos() + ",";
                sql += ed.getOid_Conta() + ",";

                sql += ed.getOID_Conta_Credora_Venda() + ",";
                sql += ed.getOID_Conta_Devedora_Venda() + ",";
                sql += ed.getOID_Conta_Credora_Baixa_Depreciacao() + ",";
                sql += ed.getOID_Conta_Devedora_Baixa_Depreciacao() + ",";
                sql += ed.getOID_Conta_Credora_Ganho_Venda() + ",";
                sql += ed.getOID_Conta_Devedora_Ganho_Venda() + ",";
                sql += ed.getOID_Conta_Credora_Perda_Venda() + ",";
                sql += ed.getOID_Conta_Devedora_Perda_Venda();

                sql += ")";

                System.out.println(sql);

                executasql.executarUpdate(sql);

                ed.setOid_Categoria(ed.getOid_Categoria());
                return ed;

        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "inclui()");
        }
    }

    public void altera(CategoriaED ed) throws Excecoes {

        String sql = null;
        try {

            sql = " UPDATE Categorias SET ";
            sql +="     Nm_Categoria = '" + ed.getNm_Categoria() + "',";
            sql +="     DT_STAMP = '" + ed.getDt_stamp() + "', ";
            sql +="     USUARIO_STAMP = '" + ed.getUsuario_Stamp() + "', ";
            sql +="     DM_STAMP = '" + ed.getDm_Stamp() + "', ";
            sql +="     OID_CONTA = " + ed.getOid_Conta() + ",";

            sql +="     OID_Conta_Credora_Venda = " + ed.getOID_Conta_Credora_Venda() + ",";
            sql +="     OID_Conta_Devedora_Venda = " + ed.getOID_Conta_Devedora_Venda() + ",";
            sql +="     OID_Conta_Credora_Baixa_Depreciacao = " + ed.getOID_Conta_Credora_Baixa_Depreciacao() + ",";
            sql +="     OID_Conta_Devedora_Baixa_Depreciacao = " + ed.getOID_Conta_Devedora_Baixa_Depreciacao() + ",";
            sql +="     OID_Conta_Credora_Ganho_Venda = " + ed.getOID_Conta_Credora_Ganho_Venda() + ",";
            sql +="     OID_Conta_Devedora_Ganho_Venda = " + ed.getOID_Conta_Devedora_Ganho_Venda() + ",";
            sql +="     OID_Conta_Credora_Perda_Venda = " + ed.getOID_Conta_Credora_Perda_Venda() + ",";
            sql +="     OID_Conta_Devedora_Perda_Venda = " + ed.getOID_Conta_Devedora_Perda_Venda() + ",";

            sql +="     PE_Fator_Anual = " + ed.getPe_Fator_Anual() + ",";
            sql +="     NR_Anos = " + ed.getNr_Anos();
            sql +=" WHERE oid_Categoria = " + ed.getOid_Categoria();

            executasql.executarUpdate(sql);
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "alterar()");
        }
    }

    public void deleta(CategoriaED ed) throws Excecoes {

        String sql = null;
        try {

            sql = " DELETE FROM Categorias WHERE oid_Categoria = " + ed.getOid_Categoria() ;

            executasql.executarUpdate(sql);
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "deleta()");
        }
    }

    public ArrayList lista(CategoriaED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();
        try {

            sql = " SELECT cat.* " +
	              "       ,con.oid_conta " +
	              "       ,con.cd_conta " +
	              "       ,con.nm_conta " +
            	  " FROM Categorias cat, contas con " +
                  " WHERE cat.oid_conta = con.oid_conta " ;
            if (util.doValida(ed.getOid_Categoria()))
                sql += " and cat.oid_Categoria = " + ed.getOid_Categoria();
            else {
                if (util.doValida(ed.getNm_Categoria()))
                    sql += " and cat.Nm_Categoria = '" + ed.getNm_Categoria() + "'";
	            if (util.doValida(String.valueOf(ed.getOid_Conta()))){
	                sql += " and cat.Oid_Conta = " + ed.getOid_Conta()  ;
	            }
	            if (util.doValida(String.valueOf(ed.getPe_Fator_Anual()))){
	                sql += " and cat.Pe_Fator_Anual = " + ed.getPe_Fator_Anual()  ;
	            }
	            if (util.doValida(String.valueOf(ed.getNr_Anos()))){
	                sql += " and cat.Nr_Anos = " + ed.getNr_Anos()  ;
	            }
            }
            sql +=" ORDER BY oid_Categoria ";

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                CategoriaED edVolta = new CategoriaED();

                edVolta.setOid_Categoria(res.getString("oid_Categoria"));
                edVolta.setNm_Categoria(res.getString("Nm_Categoria"));
                edVolta.setOid_Conta(new Integer(res.getInt("Oid_Conta")));
                edVolta.setCd_Conta(res.getString("Cd_Conta"));
                edVolta.setNm_Conta(res.getString("Nm_Conta"));
                edVolta.setPe_Fator_Anual(new Double(res.getDouble("Pe_Fator_Anual")));
                edVolta.setNr_Anos(res.getLong("Nr_Anos"));

                list.add(edVolta);
            }
            return list;

        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "lista()");
        }
    }

    public CategoriaED getByRecord(CategoriaED ed) throws Excecoes {

        CategoriaED edVolta = new CategoriaED();
        try {

            String sql = " SELECT cat.* " +
                "       ,con.oid_conta " +
                "       ,con.cd_conta " +
                "       ,con.nm_conta " +
                " FROM Categorias cat" +
                "      ,Contas con" +
                " WHERE cat.oid_conta = con.oid_conta " +
                " and cat.oid_Categoria = " + ed.getOid_Categoria() ;

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                edVolta = new CategoriaED();
                edVolta.setOid_Categoria(res.getString("oid_Categoria"));
                edVolta.setNm_Categoria(res.getString("Nm_Categoria"));
                edVolta.setOid_Conta(new Integer(res.getInt("Oid_Conta")));
                edVolta.setCd_Conta(res.getString("Cd_Conta"));
                edVolta.setNm_Conta(res.getString("Nm_Conta"));
                edVolta.setPe_Fator_Anual(new Double(res.getDouble("Pe_Fator_Anual")));
                edVolta.setNr_Anos(res.getLong("Nr_Anos"));

	            if (util.doValida(String.valueOf(res.getString("oid_Conta_Credora_Venda")))){
                    sql = " select * " +
            		" from Contas " +
            		" WHERE oid_Conta = " + res.getString("oid_Conta_Credora_Venda");

                    ResultSet resLocal = this.executasql.executarConsulta(sql);
                    while (resLocal.next())
                    {
                        edVolta.setCD_Conta_Credora_Venda(resLocal.getString("CD_Conta"));
                        edVolta.setNM_Conta_Credora_Venda(resLocal.getString("NM_Conta"));
                        edVolta.setOID_Conta_Credora_Venda(new Integer(resLocal.getInt("oid_Conta")));
                    }
                }
	            if (util.doValida(String.valueOf(res.getString("oid_Conta_Devedora_Venda")))){
                    sql = " select * " +
            		" from Contas " +
            		" WHERE oid_Conta = " + res.getString("oid_Conta_Devedora_Venda");

                    ResultSet resLocal = this.executasql.executarConsulta(sql);
                    while (resLocal.next())
                    {
                        edVolta.setCD_Conta_Devedora_Venda(resLocal.getString("CD_Conta"));
                        edVolta.setNM_Conta_Devedora_Venda(resLocal.getString("NM_Conta"));
                        edVolta.setOID_Conta_Devedora_Venda(new Integer(resLocal.getInt("oid_Conta")));
                    }
                }

	            if (util.doValida(String.valueOf(res.getString("oid_Conta_Credora_Baixa_Depreciacao")))){
                    sql = " select * " +
            		" from Contas " +
            		" WHERE oid_Conta = " + res.getString("oid_Conta_Credora_Baixa_Depreciacao");

                    ResultSet resLocal = this.executasql.executarConsulta(sql);
                    while (resLocal.next())
                    {
                        edVolta.setCD_Conta_Credora_Baixa_Depreciacao(resLocal.getString("CD_Conta"));
                        edVolta.setNM_Conta_Credora_Baixa_Depreciacao(resLocal.getString("NM_Conta"));
                        edVolta.setOID_Conta_Credora_Baixa_Depreciacao(new Integer(resLocal.getInt("oid_Conta")));
                    }
                }
	            if (util.doValida(String.valueOf(res.getString("oid_Conta_Devedora_Baixa_Depreciacao")))){
                    sql = " select * " +
            		" from Contas " +
            		" WHERE oid_Conta = " + res.getString("oid_Conta_Devedora_Baixa_Depreciacao");

                    ResultSet resLocal = this.executasql.executarConsulta(sql);
                    while (resLocal.next())
                    {
                        edVolta.setCD_Conta_Devedora_Baixa_Depreciacao(resLocal.getString("CD_Conta"));
                        edVolta.setNM_Conta_Devedora_Baixa_Depreciacao(resLocal.getString("NM_Conta"));
                        edVolta.setOID_Conta_Devedora_Baixa_Depreciacao(new Integer(resLocal.getInt("oid_Conta")));
                    }
                }
	            if (util.doValida(String.valueOf(res.getString("oid_Conta_Credora_Ganho_Venda")))){
                    sql = " select * " +
            		" from Contas " +
            		" WHERE oid_Conta = " + res.getString("oid_Conta_Credora_Ganho_Venda");

                    ResultSet resLocal = this.executasql.executarConsulta(sql);
                    while (resLocal.next())
                    {
                        edVolta.setCD_Conta_Credora_Ganho_Venda(resLocal.getString("CD_Conta"));
                        edVolta.setNM_Conta_Credora_Ganho_Venda(resLocal.getString("NM_Conta"));
                        edVolta.setOID_Conta_Credora_Ganho_Venda(new Integer(resLocal.getInt("oid_Conta")));
                    }
                }
	            if (util.doValida(String.valueOf(res.getString("oid_Conta_Devedora_Ganho_Venda")))){
                    sql = " select * " +
            		" from Contas " +
            		" WHERE oid_Conta = " + res.getString("oid_Conta_Devedora_Ganho_Venda");

                    ResultSet resLocal = this.executasql.executarConsulta(sql);
                    while (resLocal.next())
                    {
                        edVolta.setCD_Conta_Devedora_Ganho_Venda(resLocal.getString("CD_Conta"));
                        edVolta.setNM_Conta_Devedora_Ganho_Venda(resLocal.getString("NM_Conta"));
                        edVolta.setOID_Conta_Devedora_Ganho_Venda(new Integer(resLocal.getInt("oid_Conta")));
                    }
                }
	            if (util.doValida(String.valueOf(res.getString("oid_Conta_Credora_Perda_Venda")))){
                    sql = " select * " +
            		" from Contas " +
            		" WHERE oid_Conta = " + res.getString("oid_Conta_Credora_Perda_Venda");

                    ResultSet resLocal = this.executasql.executarConsulta(sql);
                    while (resLocal.next())
                    {
                        edVolta.setCD_Conta_Credora_Perda_Venda(resLocal.getString("CD_Conta"));
                        edVolta.setNM_Conta_Credora_Perda_Venda(resLocal.getString("NM_Conta"));
                        edVolta.setOID_Conta_Credora_Perda_Venda(new Integer(resLocal.getInt("oid_Conta")));
                    }
                }
	            if (util.doValida(String.valueOf(res.getString("oid_Conta_Devedora_Perda_Venda")))){
                    sql = " select * " +
            		" from Contas " +
            		" WHERE oid_Conta = " + res.getString("oid_Conta_Devedora_Perda_Venda");

                    ResultSet resLocal = this.executasql.executarConsulta(sql);
                    while (resLocal.next())
                    {
                        edVolta.setCD_Conta_Devedora_Perda_Venda(resLocal.getString("CD_Conta"));
                        edVolta.setNM_Conta_Devedora_Perda_Venda(resLocal.getString("NM_Conta"));
                        edVolta.setOID_Conta_Devedora_Perda_Venda(new Integer(resLocal.getInt("oid_Conta")));
                    }
                }

            }
            return edVolta;
        } catch (SQLException e) {
            throw new Excecoes(e.getMessage(), e, getClass().getName(), "getByRecord()");
        }
    }
}
