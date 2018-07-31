package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.Natureza_OperacaoED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.Utilitaria;
import com.master.util.bd.ExecutaSQL;

public class Natureza_OperacaoBD {

    private ExecutaSQL executasql;
    Utilitaria util = new Utilitaria(executasql);

    public Natureza_OperacaoBD(ExecutaSQL sql) {
        this.executasql = sql;
    }

    public Natureza_OperacaoED inclui(Natureza_OperacaoED ed) throws Excecoes{

        String sql = null;

    	try{

    	    ed.setOid_Natureza_Operacao(new Integer(util.getAutoIncremento("oid_Natureza_Operacao", "Naturezas_Operacoes")));

    	    sql = " INSERT INTO Naturezas_Operacoes ("+
    	    	  "		OID_Natureza_Operacao," +
    	    	  " 	NM_Natureza_Operacao," +
    	    	  " 	CD_Natureza_Operacao," +
    	    	  " 	DM_Tipo_Operacao," +
    	    	  " 	DM_Tipo_Imposto," +
    	    	  " 	CD_CFO_CONHECIMENTO) " +
    	    	  " VALUES ("+
    	    	  		ed.getOid_Natureza_Operacao() + "" +
    	    	  		",'" + ed.getNm_Natureza_Operacao() + "'" +
    	    	  		",'" + ed.getCd_Natureza_Operacao() + "'" +
    	    	  		",'" + ed.getDM_Tipo_Operacao() + "'" +
    	    	  		",'" + ed.getDm_Tipo_Imposto() + "'" +
    	    	  		",'" + ed.getCd_CFO_Conhecimento() + "')";

    	    executasql.executarUpdate(sql);
    	}
    	catch(Exception exc){
    	    throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            		"inclui()");
    	}
    	return ed;
    }

    public void altera(Natureza_OperacaoED ed) throws Excecoes{

        String sql = null;
        try{

            sql = " update Naturezas_Operacoes set ";
            sql += " NM_Natureza_Operacao = '" + ed.getNm_Natureza_Operacao() + "', ";
            sql += " CD_Natureza_Operacao = '" + ed.getCd_Natureza_Operacao() + "', ";
            sql += " Cd_CFO_Conhecimento  = '" + ed.getCd_CFO_Conhecimento() + "', ";
            sql += " DM_Tipo_Operacao     = '" + ed.getDM_Tipo_Operacao() + "',";
            sql += " DM_Tipo_Imposto      = '" + ed.getDm_Tipo_Imposto() + "' ";
            sql += " where oid_Natureza_Operacao = " + ed.getOid_Natureza_Operacao();

            executasql.executarUpdate(sql);
        }
        catch(Exception exc){
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            		"altera()");
        }
    }

    public void deleta(Natureza_OperacaoED ed) throws Excecoes{

        String sql = null;
        try{

            sql = "DELETE FROM Naturezas_Operacoes WHERE oid_Natureza_Operacao = ";
            sql += "(" + ed.getOid_Natureza_Operacao() + ")";

            executasql.executarUpdate(sql);
        }
        catch(Exception exc){
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            		"deleta()");
        }
    }

    public ArrayList lista(Natureza_OperacaoED ed)throws Excecoes{

        String sql = null;
        ArrayList list = new ArrayList();
        try{

            sql = " SELECT * FROM Naturezas_Operacoes " +
            	  " WHERE 1=1";

            if (ed.getOid_Natureza_Operacao() != null && ed.getOid_Natureza_Operacao().intValue() > 0)
                sql +=" AND oid_Natureza_Operacao = "+ed.getOid_Natureza_Operacao().intValue();
            if (util.doValida(ed.getNm_Natureza_Operacao()))
                sql +=" AND NM_Natureza_Operacao LIKE '"+ed.getNm_Natureza_Operacao()+"%'";
            if (util.doValida(ed.getDM_Tipo_Operacao()))
                sql +=" AND DM_Tipo_Operacao ='"+ed.getDM_Tipo_Operacao()+"'";
            sql +=" ORDER BY CD_Natureza_Operacao";

            ResultSet res = this.executasql.executarConsulta(sql);
            //popula
            while (res.next()){
                Natureza_OperacaoED edVolta = new Natureza_OperacaoED();

                edVolta.setOid_Natureza_Operacao(new Integer(res.getInt("oid_Natureza_Operacao")));
                edVolta.setCd_Natureza_Operacao(res.getString("cd_Natureza_Operacao"));
                edVolta.setCd_CFO_Conhecimento(res.getString("Cd_CFO_Conhecimento"));
                edVolta.setNm_Natureza_Operacao(res.getString("nm_Natureza_Operacao"));
                edVolta.setDm_Tipo_Imposto(res.getString("dm_Tipo_Imposto"));
                edVolta.setDM_Tipo_Operacao(res.getString("DM_Tipo_Operacao"));

                list.add(edVolta);
            }
        }
        catch(Exception exc){
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            		"lista()");
        }
        return list;
    }

    public Natureza_OperacaoED getByRecord(Natureza_OperacaoED ed)throws Excecoes{

        String sql = null;
        Natureza_OperacaoED edVolta = new Natureza_OperacaoED();

        try{
            sql = " SELECT * FROM Naturezas_Operacoes WHERE 1=1 ";

            if (util.doValida(ed.getCd_Natureza_Operacao())){
                sql += " AND Naturezas_Operacoes.cd_Natureza_Operacao = '" + ed.getCd_Natureza_Operacao() + "'";
            } else if (util.doValida(String.valueOf(ed.getOid_Natureza_Operacao()))){
                sql += " and OID_Natureza_Operacao = " + ed.getOid_Natureza_Operacao();
            }
            if (util.doValida(ed.getDM_Tipo_Operacao()))
                sql +=" AND DM_Tipo_Operacao IN ('D','"+ed.getDM_Tipo_Operacao()+"')";
            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next()){
                edVolta = new Natureza_OperacaoED();

                edVolta.setOid_Natureza_Operacao(new Integer(res.getInt("oid_Natureza_Operacao")));
                edVolta.setCd_Natureza_Operacao(res.getString("cd_Natureza_Operacao"));
                edVolta.setNm_Natureza_Operacao(res.getString("nm_Natureza_Operacao"));
                edVolta.setDm_Tipo_Imposto(res.getString("dm_Tipo_Imposto"));
                edVolta.setDM_Tipo_Operacao(res.getString("DM_Tipo_Operacao"));
                edVolta.setCd_CFO_Conhecimento(res.getString("Cd_CFO_Conhecimento"));
            }
        }
        catch(Exception exc){
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "getByRecord()");
        }
        return edVolta;
    }

    public void geraRelatorio(Natureza_OperacaoED ed)throws Excecoes{

//    String sql = null;
//
//    Natureza_OperacaoED edVolta = new Natureza_OperacaoED();
//
//    try{
//
//      sql = "select * from Naturezas_Operacoes where oid_Natureza_Operacao > 0";
//
//      if (ed.getCD_Natureza_Operacao() != null && !ed.getCD_Natureza_Operacao().equals("")){
//        sql += " and CD_Natureza_Operacao = '" + ed.getCD_Natureza_Operacao() + "'";
//      }
//      if (ed.getCD_Remessa() != null && !ed.getCD_Remessa().equals("")){
//        sql += " and CD_Remessa = '" + ed.getCD_Remessa() + "'";
//      }
//
//      ResultSet res = null;
//      res = this.executasql.executarConsulta(sql);
//
//      Natureza_OperacaoRL Natureza_Operacao_rl = new Natureza_OperacaoRL();
//      Natureza_Operacao_rl.geraRelatEstoque(res);
//    }
//    catch (Excecoes e){
//      throw e;
//    }
//    catch(Exception exc){
//      Excecoes exce = new Excecoes();
//      exce.setExc(exc);
//      exce.setMensagem("Erro no método listar");
//      exce.setClasse(this.getClass().getName());
//      exce.setMetodo("geraRelatorio(Natureza_OperacaoED ed)");
//    }
//
    }
}