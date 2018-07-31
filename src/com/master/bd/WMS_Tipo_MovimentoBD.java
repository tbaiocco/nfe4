package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.WMS_Tipo_MovimentoED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.Utilitaria;
import com.master.util.bd.ExecutaSQL;

public class WMS_Tipo_MovimentoBD  {

    private ExecutaSQL executasql;
	Utilitaria util = new Utilitaria(executasql);

    public WMS_Tipo_MovimentoBD(ExecutaSQL sql) {
        this.executasql = sql;
    }

    public WMS_Tipo_MovimentoED inclui(WMS_Tipo_MovimentoED ed) throws Excecoes {

        String sql = null;

        try {

            ed.setOid_Tipo_Movimento_Produto(util.getAutoIncremento("oid_Tipo_Movimento_Produto", "Tipos_Movimentos_Produtos"));

            sql = " INSERT INTO Tipos_Movimentos_Produtos (" +
            		"	Oid_Tipo_Movimento_Produto" +
            		"	,Nm_Descricao" +
            		"	,Dm_Origem" +
            		"	,Dm_Destino" +
            		"	,Dm_Situacao" +
            		"	,Dm_NF" +
            		"	,Dm_Tipo_Movimento) " +
            		" values (" + ed.getOid_Tipo_Movimento_Produto() + 
            		",'" + ed.getNm_Descricao() + "'" +
            		", '" + ed.getDm_Origem() + "'" +
            		",'" + ed.getDm_Destino() + "'" +
            		",'" + ed.getDm_Situacao() + "'" +
            		",'" + ed.getDm_NF() + "'" +
            		",'" + ed.getDm_tipo_movimento() + "')";

            executasql.executarUpdate(sql);
            return ed;
        }
        catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
	    		"inclui()");
        }
    }

    public void altera(WMS_Tipo_MovimentoED ed) throws Excecoes {

        String sql = null;

        try {

            sql = " UPDATE Tipos_Movimentos_Produtos SET ";
            sql += " nm_descricao = '" + ed.getNm_Descricao() + "', ";
            sql += " dm_origem = '" + ed.getDm_Origem() + "', ";
            sql += " dm_destino = '" + ed.getDm_Destino() + "', ";
            sql += " dm_situacao = '" + ed.getDm_Situacao() + "', ";
            sql += " dm_nf = '" + ed.getDm_NF() + "', ";
            sql += " dm_tipo_movimento = '" + ed.getDm_tipo_movimento() + "' ";
            sql += " WHERE Oid_Tipo_Movimento_Produto = " + ed.getOid_Tipo_Movimento_Produto();

            int i = executasql.executarUpdate(sql);
        }

        catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
	    		"altera()");
        }
    }

    public void deleta(WMS_Tipo_MovimentoED ed) throws Excecoes {

        String sql = null;

        try {

            sql = " DELETE FROM Tipos_Movimentos_Produtos " +
            	  " WHERE Oid_Tipo_Movimento_Produto = "+ ed.getOid_Tipo_Movimento_Produto();

            executasql.executarUpdate(sql);
        }
        catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
	    		"deleta()");
        }
    }

    public WMS_Tipo_MovimentoED getByRecord(WMS_Tipo_MovimentoED ed) throws Excecoes {

        String sql = null;

        WMS_Tipo_MovimentoED edVolta = new WMS_Tipo_MovimentoED();

        try {

            sql = " SELECT * FROM Tipos_Movimentos_Produtos";

            if (ed.getOid_Tipo_Movimento_Produto() > 0)
                sql += " WHERE Oid_Tipo_Movimento_Produto = " + ed.getOid_Tipo_Movimento_Produto();

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next()) {
                edVolta = new WMS_Tipo_MovimentoED();
                edVolta.setOid_Tipo_Movimento_Produto(new Integer(res.getString("Oid_Tipo_Movimento_Produto")).intValue());
                edVolta.setNm_Descricao(res.getString("Nm_Descricao"));
                edVolta.setDm_Origem(res.getString("Dm_Origem"));
                edVolta.setDm_Destino(res.getString("Dm_Destino"));
                edVolta.setDm_Situacao(res.getString("Dm_Situacao"));
                edVolta.setDm_NF(res.getString("Dm_NF"));
                edVolta.setDm_tipo_movimento(res.getString("Dm_tipo_movimento"));
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
	    		"getByRecord()");
        }
        return edVolta;
    }

    public WMS_Tipo_MovimentoED getByOid(int Oid) throws Excecoes {

        String sql = null;

        WMS_Tipo_MovimentoED edVolta = new WMS_Tipo_MovimentoED();

        try {

            sql = " SELECT * FROM Tipos_Movimentos_Produtos " +
            	  " WHERE Oid_Tipo_Movimento_Produto = " + Oid;

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next()) {
                edVolta = new WMS_Tipo_MovimentoED();
                edVolta.setOid_Tipo_Movimento_Produto(new Integer(res.getString("Oid_Tipo_Movimento_Produto")).intValue());
                edVolta.setNm_Descricao(res.getString("Nm_Descricao"));
                edVolta.setDm_Origem(res.getString("Dm_Origem"));
                edVolta.setDm_Destino(res.getString("Dm_Destino"));
                edVolta.setDm_Situacao(res.getString("Dm_Situacao"));
                edVolta.setDm_NF(res.getString("Dm_NF"));
                edVolta.setDm_tipo_movimento(res.getString("Dm_tipo_movimento"));
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
	    		"getByOID()");
        }
        return edVolta;
    }

    public ArrayList getAll() throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();

        WMS_Tipo_MovimentoED edVolta = new WMS_Tipo_MovimentoED();

        try {

            sql = " SELECT * FROM Tipos_Movimentos_Produtos";
            sql +=" WHERE 1=1 ";

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next()) {
                edVolta = new WMS_Tipo_MovimentoED();
                edVolta.setOid_Tipo_Movimento_Produto(new Integer(res.getString("Oid_Tipo_Movimento_Produto")).intValue());
                edVolta.setNm_Descricao(res.getString("Nm_Descricao"));
                edVolta.setDm_Origem(res.getString("Dm_Origem"));
                edVolta.setDm_Destino(res.getString("Dm_Destino"));
                edVolta.setDm_Situacao(res.getString("Dm_Situacao"));
                edVolta.setDm_NF(res.getString("Dm_NF"));
                edVolta.setDm_tipo_movimento(res.getString("Dm_tipo_movimento"));

                list.add(edVolta);
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
	    		"getAll()");
        }
        return list;
    }

    public ArrayList lista(WMS_Tipo_MovimentoED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();

        WMS_Tipo_MovimentoED edVolta = new WMS_Tipo_MovimentoED();

        try {

            sql = " SELECT * FROM Tipos_Movimentos_Produtos";
            sql +=" WHERE 1=1 ";

            if (ed.getOid_Tipo_Movimento_Produto() > 0) {
                sql += " and Oid_Tipo_Movimento_Produto = " + ed.getOid_Tipo_Movimento_Produto();
            } else {
                if (util.doValida(ed.getNm_Descricao()))
                    sql += " and nm_descricao = '" + ed.getNm_Descricao() + "'";
                if (util.doValida(ed.getDm_tipo_movimento()))
                    sql += " and dm_tipo_movimento = '" + ed.getDm_tipo_movimento() + "'";
                if (util.doValida(ed.getDm_Situacao()))
                    sql += " and dm_situacao = '" + ed.getDm_Situacao() + "'";
                if (util.doValida(ed.getDm_Destino()))
                    sql += " and dm_destino = '" + ed.getDm_Destino() + "'";
                if (util.doValida(ed.getDm_NF()))
                    sql += " and dm_nf = '" + ed.getDm_NF() + "'";
                if (util.doValida(ed.getDm_Origem()))
                    sql += " and dm_origem = '" + ed.getDm_Origem() + "'";
            }

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next()) {
                edVolta = new WMS_Tipo_MovimentoED();
                edVolta.setOid_Tipo_Movimento_Produto(res.getInt("Oid_Tipo_Movimento_Produto"));
                edVolta.setNm_Descricao(res.getString("Nm_Descricao"));
                edVolta.setDm_Origem(res.getString("Dm_Origem"));
                edVolta.setDm_Destino(res.getString("Dm_Destino"));
                edVolta.setDm_Situacao(res.getString("Dm_Situacao"));
                edVolta.setDm_NF(res.getString("Dm_NF"));
                edVolta.setDm_tipo_movimento(res.getString("Dm_tipo_movimento"));

                list.add(edVolta);
            }
            return list;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
	    		"lista()");
        }
    }

    public void geraRelatorio(WMS_Tipo_MovimentoED ed) throws Excecoes {

        //    String sql = null;
        //
        //    WMS_Tipo_MovimentoED edVolta = new WMS_Tipo_MovimentoED();
        //
        //    try{
        //
        //      sql = "select * from Tipo_Movimentoes where
        // Oid_Tipo_Movimento_Produto > 0";
        //
        //      if (ed.getCd_Tipo_Movimento() != null &&
        // !ed.getCd_Tipo_Movimento().equals("")){
        //        sql += " and Cd_Tipo_Movimento = '" + ed.getCd_Tipo_Movimento() +
        // "'";
        //      }
        //      if (ed.getCd_Remessa() != null && !ed.getCd_Remessa().equals("")){
        //        sql += " and Cd_Remessa = '" + ed.getCd_Remessa() + "'";
        //      }
        //
        //      ResultSet res = null;
        //      res = this.executasql.executarConsulta(sql);
        //
        //      WMS_Tipo_MovimentoRL WMS_Tipo_Movimento_rl = new
        // WMS_Tipo_MovimentoRL();
        //      WMS_Tipo_Movimento_rl.geraRelatEstoque(res);
        //    }
        //    catch (Excecoes e){
        //      throw e;
        //    }
        //    catch(Exception exc){
        //      Excecoes exce = new Excecoes();
        //      exce.setExc(exc);
        //      exce.setMensagem("Erro no método listar");
        //      exce.setClasse(this.getClass().getName());
        //      exce.setMetodo("geraRelatorio(WMS_Tipo_MovimentoED ed)");
        //    }
        //
    }

}