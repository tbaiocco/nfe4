package com.master.bd;

import java.sql.ResultSet;

import com.master.ed.Relatorio_GeralED;
import com.master.root.FormataDataBean;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;


public class Relatorio_GeralBD extends BancoUtil {

    Calcula_FreteBD Calcula_FreteBD = null;

    Parametro_FixoED parametro_FixoED = new Parametro_FixoED();
    FormataDataBean dataFormatada = new FormataDataBean();

    String Situacao_Cliente = "";
    String Tipo_Cobranca = "F";
    String OID_Vendedor = "";

    private ExecutaSQL executasql;

    public Relatorio_GeralBD(ExecutaSQL sql) {
        super(sql);
        this.executasql = sql;
    }

    public byte[] geraPre_Faturamento_Pellenz(Relatorio_GeralED ed) throws Excecoes {

        String sql = null;
        byte[] b = null;

        sql = " select Conhecimentos.DT_Emissao, Conhecimentos.DM_Situacao, " +
        		"Conhecimentos.NR_Conhecimento, Conhecimentos.OID_Conhecimento, " +
        		"Conhecimentos.nr_volumes, Conhecimentos.dm_tipo_pagamento, " +
        		"Conhecimentos.nr_duplicata, Conhecimentos.vl_total_frete, " +
        		"Conhecimentos.OID_Pessoa_Pagador,  "
                + " conhecimentos.nr_peso, Unidades.CD_Unidade, Cidades.NM_Cidade,  " +
                		"Cidade_Pagador.NM_Cidade as NM_Cidade_Pagador, "
                + " Pessoa_Remetente.NM_Razao_Social    as NM_Razao_Social_Remetente,"
                + " Pessoa_Unidade.NM_Fantasia          as NM_Fantasia            ,"
                + " Pessoa_Pagador.NM_Razao_Social      as NM_Razao_Social_Pagador,"
                + " Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario "
                + " from Conhecimentos, Clientes, Unidades, Cidades, Cidades Cidade_Pagador, "
                + " Pessoas Pessoa_Remetente, Pessoas Pessoa_Pagador, Pessoas Pessoa_Unidade, Pessoas Pessoa_Destinatario "
                + " where Unidades.oid_Unidade = Conhecimentos.oid_Unidade "
                + " AND Unidades.oid_pessoa = Pessoa_Unidade.oid_Pessoa "
                + " AND Pessoa_Unidade.oid_Cidade = Cidades.oid_cidade "
                + " AND Conhecimentos.oid_Pessoa_Pagador = Pessoa_Pagador.oid_Pessoa "
                + " AND Conhecimentos.oid_Pessoa_Pagador = Clientes.oid_Cliente "
                + " AND Pessoa_Pagador.oid_cidade = Cidade_Pagador.oid_Cidade "
                + " AND Conhecimentos.DM_Impresso = 'S' "
                + " AND Conhecimentos.VL_Total_Frete > 0"
                + " AND Conhecimentos.oid_Pessoa = Pessoa_Remetente.oid_Pessoa "
                + " AND Conhecimentos.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa ";

        if (String.valueOf(ed.getNR_Conhecimento()) != null && !String.valueOf(ed.getNR_Conhecimento()).equals("0")) {
            sql += " and Conhecimentos.NR_Conhecimento = " + ed.getNR_Conhecimento();
        }
        if (String.valueOf(ed.getOID_Unidade()) != null && !String.valueOf(ed.getOID_Unidade()).equals("0")) {
            sql += " and Conhecimentos.OID_Unidade = " + ed.getOID_Unidade();
        }
        if (String.valueOf(ed.getOID_Pessoa()) != null && !String.valueOf(ed.getOID_Pessoa()).equals("")) {
            sql += " and Conhecimentos.OID_Pessoa = '" + ed.getOID_Pessoa() + "'";
        }
        if (String.valueOf(ed.getOID_Pessoa_Destinatario()) != null && !String.valueOf(ed.getOID_Pessoa_Destinatario()).equals("")) {
            sql += " and Conhecimentos.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario() + "'";
        }
        if (String.valueOf(ed.getOID_Pessoa_Consignatario()) != null && !String.valueOf(ed.getOID_Pessoa_Consignatario()).equals("")) {
            sql += " and Conhecimentos.OID_Pessoa_Consignatario = '" + ed.getOID_Pessoa_Consignatario() + "'";
        }
        if (String.valueOf(ed.getOID_Pessoa_Pagador()) != null && !String.valueOf(ed.getOID_Pessoa_Pagador()).equals("") && !String.valueOf(ed.getOID_Pessoa_Pagador()).equals("null")) {
            sql += " and Conhecimentos.OID_Pessoa_Pagador = '" + ed.getOID_Pessoa_Pagador() + "'";
        }
        if (String.valueOf(ed.getDt_Emissao_Inicial()) != null && !String.valueOf(ed.getDt_Emissao_Inicial()).equals("") && !String.valueOf(ed.getDt_Emissao_Inicial()).equals("null")) {
            sql += " and Conhecimentos.DT_Emissao >= '" + ed.getDt_Emissao_Inicial() + "'";
        }
        if (String.valueOf(ed.getDt_Emissao_Final()) != null && !String.valueOf(ed.getDt_Emissao_Final()).equals("") && !String.valueOf(ed.getDt_Emissao_Final()).equals("null")) {
            sql += " and Conhecimentos.DT_Emissao <= '" + ed.getDt_Emissao_Final() + "'";
        }

        if (String.valueOf(ed.getDM_Tipo_Cobranca()) != null && !String.valueOf(ed.getDM_Tipo_Cobranca()).equals("null") && !String.valueOf(ed.getDM_Tipo_Cobranca()).equals("T")
                && !String.valueOf(ed.getDM_Tipo_Cobranca()).equals("")) {
            sql += " and Conhecimentos.DM_Tipo_Cobranca = '" + ed.getDM_Tipo_Cobranca() + "'";
        }
        if (String.valueOf(ed.getDM_Tipo_Faturamento()) != null && !String.valueOf(ed.getDM_Tipo_Faturamento()).equals("null") && !String.valueOf(ed.getDM_Tipo_Faturamento()).equals("T")
                && !String.valueOf(ed.getDM_Tipo_Faturamento()).equals("")) {
            sql += " and Clientes.dm_tipo_faturamento = '" + ed.getDM_Tipo_Faturamento() + "'";
        }

        sql += " and (Conhecimentos.DM_Situacao = 'G' or Conhecimentos.DM_Situacao = 'B') ";

        sql += " order by Conhecimentos.DM_Tipo_Cobranca, Pessoa_Pagador.NM_Razao_Social, conhecimentos.nr_conhecimento ";

        Relatorio_GeralED edVolta = new Relatorio_GeralED();

        try {

            ResultSet res = null;

            // System.out.println("Relatorio_GeralBD.geraPre_Faturamento() sql = "+sql);

            res = this.executasql.executarConsulta(sql.toString());


        }

        catch (Excecoes e) {
            throw e;
        } catch (Exception exc) {
            Excecoes exce = new Excecoes();
            exce.setExc(exc);
            exce.setMensagem("Erro no m�todo listar");
            exce.setClasse(this.getClass().getName());
            exce.setMetodo("geraRelatorio(Relatorio_GeralED ed)");
        }
        return b;
    }

}
