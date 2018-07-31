package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import com.master.ed.Modelo_Nota_FiscalED;
import com.master.ed.WMS_Tipo_EstoqueED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.Utilitaria;
import com.master.util.bd.ExecutaSQL;

/**
 * @author André Valadas
 * - Modelos de Notas Fiscais
 */
public class Modelo_Nota_FiscalBD  {

    private ExecutaSQL executasql;
	Utilitaria util = new Utilitaria(executasql);

    public Modelo_Nota_FiscalBD(ExecutaSQL sql) {
        this.executasql = sql;
    }

    public Modelo_Nota_FiscalED inclui(Modelo_Nota_FiscalED ed) throws Excecoes {

        String sql = null;
        try {
            ed.setOid_Modelo_Nota_Fiscal(util.getAutoIncremento("oid_Modelo_Nota_Fiscal", "Modelos_Notas_Fiscais"));

            sql = " INSERT INTO Modelos_Notas_Fiscais (" +
            	  "		 oid_Modelo_Nota_Fiscal" +
            	  "		,oid_Tipo_Estoque" +
            	  "		,oid_Tipo_Movimento_Produto" +
            	  "		,CD_Modelo_Nota_Fiscal" +
            	  "		,NM_Modelo_Nota_Fiscal" +
            	  "		,DM_Tipo_Nota_Fiscal" +
            	  "		,DM_Gera_Fiscal" +
            	  "		,DM_Gera_Devolucao" +
            	  "		,DM_Nota_Fiscal" +
            	  "		,DM_Movimenta_Estoque" +
            	  "		,DM_Movimenta_Financeiro" +
            	  "		,DM_Exige_Pedido" +
            	  "		,DM_Permite_Servico" +
            	  "		,DM_Aliquota_ICMS" +
            	  "		,TX_Observacao, oid_sugestao_contabil) " +
            	  " VALUES (" +
            	  	ed.getOid_Modelo_Nota_Fiscal() +
            	  	"," + ed.getOid_Tipo_Estoque() +
            	  	"," + ed.getOid_Tipo_Movimento_Produto() +
            	  	",'" + ed.getCD_Modelo_Nota_Fiscal() +"'"+
            	  	",'" + ed.getNM_Modelo_Nota_Fiscal() +"'"+
            	  	",'" + ed.getDM_Tipo_Nota_Fiscal() + "'" +
            	  	",'" + ed.getDM_Gera_Fiscal() + "'" +
            	  	",'" + ed.getDM_Gera_Devolucao() + "'" +
            	  	",'" + ed.getDM_Nota_Fiscal() + "'" +
            	  	",'" + ed.getDM_Movimenta_Estoque() + "'" +
            	  	",'" + ed.getDM_Movimenta_Financeiro() + "'" +
            	  	",'" + ed.getDM_Exige_Pedido() + "'" +
            	  	",'" + ed.getDM_Permite_Servico() + "'" +
            	  	",'" + ed.getDM_Aliquota_ICMS() + "'" +
            	  	",'" + ed.getTX_Observacao() + "'" +
            	  	", " + ed.getOid_Sugestao_Contabil() + ")";

            executasql.executarUpdate(sql);
        	return ed;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "inclui()");
        }
    }

    public void altera(Modelo_Nota_FiscalED ed) throws Excecoes {

        String sql = null;
        try {
            sql =  " UPDATE Modelos_Notas_Fiscais SET ";
            sql += "	 oid_Tipo_Estoque = "+ed.getOid_Tipo_Estoque();
            sql += "	,oid_Tipo_Movimento_Produto = "+ed.getOid_Tipo_Movimento_Produto();
            sql += "	,CD_Modelo_Nota_Fiscal = '"+ed.getCD_Modelo_Nota_Fiscal()+"'";
            sql += "	,NM_Modelo_Nota_Fiscal = '"+ed.getNM_Modelo_Nota_Fiscal()+"'";
            sql += "	,DM_Tipo_Nota_Fiscal = '"+ed.getDM_Tipo_Nota_Fiscal()+"'";
            sql += "	,DM_Gera_Fiscal = '"+ed.getDM_Gera_Fiscal()+"'";
            sql += "	,DM_Gera_Devolucao = '"+ed.getDM_Gera_Devolucao()+"'";
            sql += "	,DM_Nota_Fiscal = '"+ed.getDM_Nota_Fiscal()+"'";
            sql += "	,DM_Movimenta_Estoque = '"+ed.getDM_Movimenta_Estoque()+"'";
            sql += "	,DM_Movimenta_Financeiro = '"+ed.getDM_Movimenta_Financeiro()+"'";
            sql += "	,DM_Exige_Pedido = '"+ed.getDM_Exige_Pedido()+"'";
            sql += "	,DM_Permite_Servico = '"+ed.getDM_Permite_Servico()+"'";
            sql += "	,DM_Aliquota_ICMS = '"+ed.getDM_Aliquota_ICMS()+"'";
            sql += "	,TX_Observacao = '"+ed.getTX_Observacao()+"'";
            sql += "    ,oid_sugestao_contabil = " + ed.getOid_Sugestao_Contabil() + "";
            sql += " WHERE oid_Modelo_Nota_Fiscal = " + ed.getOid_Modelo_Nota_Fiscal();

            executasql.executarUpdate(sql);
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            		"altera()");
        }
    }

    public void deleta(Modelo_Nota_FiscalED ed) throws Excecoes {

        String sql = null;
        try {
            sql = " DELETE FROM Modelos_Notas_Fiscais " +
            	  " WHERE oid_Modelo_Nota_Fiscal = " + ed.getOid_Modelo_Nota_Fiscal();

            executasql.executarUpdate(sql);
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"deleta()");
        }
    }

    public ArrayList lista(Modelo_Nota_FiscalED ed) throws Excecoes {

        String sql = null;
        ResultSet res = null;
        ArrayList lista = new ArrayList();
        try {

            sql = " SELECT Modelos_Notas_Fiscais.* " +
            	  " FROM Modelos_Notas_Fiscais" +
                  (ed.getOid_Tipo_Estoque() > 0 ? ",Tipos_Estoques" : "") +
                  (ed.getOid_Tipo_Movimento_Produto() > 0 ? ",Tipos_Movimentos_Produtos" : "") +
            	  " WHERE 1 = 1";

            if (ed.getOid_Modelo_Nota_Fiscal() > 0)
                sql += "   AND Modelos_Notas_Fiscais.oid_Modelo_Nota_Fiscal = "+ed.getOid_Modelo_Nota_Fiscal();
            if (ed.getOid_Tipo_Estoque() > 0)
                sql += "   AND Modelos_Notas_Fiscais.oid_Tipo_Estoque = Tipos_Estoques.oid_Tipo_Estoque" +
                       "   AND Modelos_Notas_Fiscais.oid_Tipo_Estoque = "+ed.getOid_Tipo_Estoque();
            if (ed.getOid_Tipo_Movimento_Produto() > 0)
                sql += "   AND Modelos_Notas_Fiscais.oid_Tipo_Movimento_Produto = Tipos_Movimentos_Produtos.oid_Tipo_Movimento_Produto" +
                       "   AND Modelos_Notas_Fiscais.oid_Tipo_Movimento_Produto = "+ed.getOid_Tipo_Movimento_Produto();
            if (util.doValida(ed.getCD_Modelo_Nota_Fiscal()))
                sql += "   AND Modelos_Notas_Fiscais.CD_Modelo_Nota_Fiscal = '"+ed.getCD_Modelo_Nota_Fiscal()+"'";
            if (util.doValida(ed.getNM_Modelo_Nota_Fiscal()))
                sql += "   AND Modelos_Notas_Fiscais.NM_Modelo_Nota_Fiscal LIKE '"+ed.getNM_Modelo_Nota_Fiscal()+"%'";
            if (util.doValida(ed.getDM_Tipo_Nota_Fiscal()))
                sql += "   AND (Modelos_Notas_Fiscais.DM_Tipo_Nota_Fiscal = '"+ed.getDM_Tipo_Nota_Fiscal()+"' OR Modelos_Notas_Fiscais.DM_Tipo_Nota_Fiscal = 'X') ";
            if (util.doValida(ed.getDM_Gera_Fiscal()))
                sql += "   AND Modelos_Notas_Fiscais.DM_Gera_Fiscal = '"+ed.getDM_Gera_Fiscal()+"'";
            if (util.doValida(ed.getDM_Gera_Contabilizacao()))
                sql += "   AND Modelos_Notas_Fiscais.DM_Gera_Contabilizacao = '"+ed.getDM_Gera_Contabilizacao()+"'";
            if (util.doValida(ed.getDM_Gera_Devolucao()))
                sql += "   AND Modelos_Notas_Fiscais.DM_Gera_Devolucao = '"+ed.getDM_Gera_Devolucao()+"'";
            if (util.doValida(ed.getDM_Movimenta_Estoque()))
                sql += "   AND Modelos_Notas_Fiscais.DM_Movimenta_Estoque = '"+ed.getDM_Movimenta_Estoque()+"'";
            if (util.doValida(ed.getDM_Movimenta_Financeiro()))
                sql += "   AND Modelos_Notas_Fiscais.DM_Movimenta_Financeiro = '"+ed.getDM_Movimenta_Financeiro()+"'";
            if (util.doValida(ed.getDM_Exige_Pedido()))
                sql += "   AND Modelos_Notas_Fiscais.DM_Exige_Pedido = '"+ed.getDM_Exige_Pedido()+"'";
            if (util.doValida(ed.getDM_Aliquota_ICMS()))
                sql += "   AND Modelos_Notas_Fiscais.DM_Aliquota_ICMS = '"+ed.getDM_Aliquota_ICMS()+"'";
            if (util.doValida(ed.getDM_Permite_Servico()))
                sql += "   AND Modelos_Notas_Fiscais.DM_Permite_Servico = '"+ed.getDM_Permite_Servico()+"'";

            sql += " ORDER BY Modelos_Notas_Fiscais.CD_Modelo_Nota_Fiscal";

            // System.out.println(sql);


            res = this.executasql.executarConsulta(sql);  
            while (res.next())
            {
                Modelo_Nota_FiscalED edVolta = new Modelo_Nota_FiscalED();

                edVolta.setOid_Modelo_Nota_Fiscal(res.getInt("oid_Modelo_Nota_Fiscal"));
                edVolta.setOid_Tipo_Estoque(res.getInt("oid_Tipo_Estoque"));
                edVolta.setOid_Tipo_Movimento_Produto(res.getInt("oid_Tipo_Movimento_Produto"));
                edVolta.setCD_Modelo_Nota_Fiscal(res.getString("CD_Modelo_Nota_Fiscal"));
                edVolta.setNM_Modelo_Nota_Fiscal(res.getString("NM_Modelo_Nota_Fiscal"));
                edVolta.setDM_Tipo_Nota_Fiscal(res.getString("DM_Tipo_Nota_Fiscal"));
                edVolta.setDM_Gera_Fiscal(res.getString("DM_Gera_Fiscal"));
                edVolta.setDM_Gera_Contabilizacao(res.getString("DM_Gera_Contabilizacao"));
                edVolta.setDM_Gera_Devolucao(res.getString("DM_Gera_Devolucao"));
                edVolta.setDM_Nota_Fiscal(res.getString("DM_Nota_Fiscal"));
                edVolta.setDM_Movimenta_Estoque(res.getString("DM_Movimenta_Estoque"));
                edVolta.setDM_Movimenta_Financeiro(res.getString("DM_Movimenta_Financeiro"));
                edVolta.setDM_Exige_Pedido(res.getString("DM_Exige_Pedido"));
                edVolta.setDM_Aliquota_ICMS(res.getString("DM_Aliquota_ICMS"));
                edVolta.setTX_Observacao(res.getString("TX_Observacao"));
                //*** Carrega Dados
                if (edVolta.getOid_Tipo_Estoque() > 0)
                    edVolta.edTipo_Estoque = new WMS_Tipo_EstoqueBD(executasql).getByRecord(new WMS_Tipo_EstoqueED(edVolta.getOid_Tipo_Estoque()));
                if (edVolta.getOid_Tipo_Movimento_Produto() > 0)
                    edVolta.edTipo_Movimento = new WMS_Tipo_MovimentoBD(executasql).getByOid(edVolta.getOid_Tipo_Movimento_Produto());

                edVolta.setOid_Sugestao_Contabil(res.getLong("oid_Sugestao_Contabil"));

                edVolta.setDM_Permite_Servico(util.getValueDef(res.getString("DM_Permite_Servico"),"N"));

                lista.add(edVolta);
            }
            return lista;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    				"lista()");
        }
    }

    public Modelo_Nota_FiscalED getByRecord(Modelo_Nota_FiscalED ed) throws Excecoes {

        Iterator iterator = this.lista(ed).iterator();
        if (iterator.hasNext()) {
            return (Modelo_Nota_FiscalED) iterator.next();
        } else return new Modelo_Nota_FiscalED();
    }
}
