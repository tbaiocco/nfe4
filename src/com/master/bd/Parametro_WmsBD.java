package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.master.ed.Parametro_WmsED;
import com.master.root.FormataDataBean;
import com.master.root.Parametro_FilialBean;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.bd.ExecutaSQL;

import br.cte.model.Empresa;

public class Parametro_WmsBD extends BancoUtil {

    private ExecutaSQL executasql;
    FormataDataBean dataFormatada = new FormataDataBean();

    public Parametro_WmsBD(ExecutaSQL sql) {
		super(sql);
		this.executasql = sql;
    }

    public Parametro_WmsED getByRecord(Empresa empresa, Parametro_WmsED ed) throws Excecoes {
        String sql = null;
        ResultSet res = null;
        Parametro_WmsED edVolta = new Parametro_WmsED();
        try {
        	sql = " select * from Parametros_Wms ";
            res = this.executasql.executarConsulta(sql);
            while (res.next()){
                //Dados dos parametros wms
                edVolta = new Parametro_WmsED();
                edVolta.setOid_Parametro_Wms(res.getLong("Oid_Parametro_Wms"));
                edVolta.setCd_Modelo_Nota_Fiscal(res.getString("Cd_Modelo_Nota_Fiscal"));
                edVolta.setCd_Cfop_Retorno_Simbolico(res.getString("Cd_Cfop_Retorno_Simbolico"));
                edVolta.setCd_Cfop_Retorno_Simbolico_Interestadual(res.getString("Cd_Cfop_Retorno_Simbolico_Interestadual"));
                edVolta.setNm_Observacao_Devolucao(res.getString("Nm_Observacao_Devolucao"));
                edVolta.setCd_Tipo_Produto(res.getString("Cd_Tipo_Produto"));
                edVolta.setCd_Setor_Produto(res.getString("Cd_setor_produto"));
                edVolta.setCd_Reduzido_Classificacao_Fiscal(res.getString("Cd_reduzido_classificacao_fiscal"));
                edVolta.setCd_Situacao_Tributaria(res.getString("Cd_situacao_tributaria"));
                edVolta.setCd_Localizacao(res.getString("Cd_localizacao"));
                edVolta.setOid_Embalagem(res.getLong("Oid_embalagem"));
                edVolta.setOid_Tipo_Pallet(res.getLong("Oid_tipo_pallet"));
                edVolta.setDm_Rotatividade(res.getString("dm_Rotatividade"));
                edVolta.setOid_Embalagem_TX(res.getString("Oid_embalagem"));
                edVolta.setOid_Tipo_Pallet_TX(res.getString("Oid_tipo_pallet"));
                edVolta.setCd_Cfop_Saida(res.getString("Cd_Cfop_Saida"));
                edVolta.setCd_Modelo_Nota_Fiscal_Saida(res.getString("Cd_Modelo_Nota_Fiscal_Saida"));
                edVolta.setCd_Cfop_Entrada(res.getString("Cd_Cfop_Entrada"));
                edVolta.setCd_Modelo_Nota_Fiscal_Entrada(res.getString("Cd_Modelo_Nota_Fiscal_Entrada"));
                if(ed.getOid_Unidade()>0){
                	edVolta.setCd_Aidof_Nota_Fiscal_Devolucao(Parametro_FilialBean.getByOID_Unidade_Parametro(empresa, ed.getOid_Unidade()).getCD_AIDOF_Nota_Fiscal());
                }
                if(!JavaUtil.doValida(edVolta.getCd_Aidof_Nota_Fiscal_Devolucao())){
                	edVolta.setCd_Aidof_Nota_Fiscal_Devolucao(res.getString("Cd_Aidof_Nota_Fiscal_Devolucao"));
                }
                // System.out.println("AIDOF NF: "+edVolta.getCd_Aidof_Nota_Fiscal_Devolucao());
            }
            return edVolta;
		} catch (Exception exc) {
			exc.printStackTrace();
			throw new Excecoes(exc.getMessage(), exc,
					this.getClass().getName(), "getByRecord(Parametro_WmsED ed)");
		} finally {
			closeResultset(res);
		}
    }
    
    public Parametro_WmsED getByRecord(Parametro_WmsED ed) throws Excecoes {
        String sql = null;
        ResultSet res = null;
        Parametro_WmsED edVolta = new Parametro_WmsED();
        try {
        	sql = " select * from Parametros_Wms ";
            res = this.executasql.executarConsulta(sql);
            while (res.next()){
                //Dados dos parametros wms
                edVolta = new Parametro_WmsED();
                edVolta.setOid_Parametro_Wms(res.getLong("Oid_Parametro_Wms"));
                edVolta.setCd_Modelo_Nota_Fiscal(res.getString("Cd_Modelo_Nota_Fiscal"));
                edVolta.setCd_Cfop_Retorno_Simbolico(res.getString("Cd_Cfop_Retorno_Simbolico"));
                edVolta.setCd_Cfop_Retorno_Simbolico_Interestadual(res.getString("Cd_Cfop_Retorno_Simbolico_Interestadual"));
                edVolta.setNm_Observacao_Devolucao(res.getString("Nm_Observacao_Devolucao"));
                edVolta.setCd_Tipo_Produto(res.getString("Cd_Tipo_Produto"));
                edVolta.setCd_Setor_Produto(res.getString("Cd_setor_produto"));
                edVolta.setCd_Reduzido_Classificacao_Fiscal(res.getString("Cd_reduzido_classificacao_fiscal"));
                edVolta.setCd_Situacao_Tributaria(res.getString("Cd_situacao_tributaria"));
                edVolta.setCd_Localizacao(res.getString("Cd_localizacao"));
                edVolta.setOid_Embalagem(res.getLong("Oid_embalagem"));
                edVolta.setOid_Tipo_Pallet(res.getLong("Oid_tipo_pallet"));
                edVolta.setDm_Rotatividade(res.getString("dm_Rotatividade"));
                edVolta.setOid_Embalagem_TX(res.getString("Oid_embalagem"));
                edVolta.setOid_Tipo_Pallet_TX(res.getString("Oid_tipo_pallet"));
                edVolta.setCd_Cfop_Saida(res.getString("Cd_Cfop_Saida"));
                edVolta.setCd_Modelo_Nota_Fiscal_Saida(res.getString("Cd_Modelo_Nota_Fiscal_Saida"));
                edVolta.setCd_Cfop_Entrada(res.getString("Cd_Cfop_Entrada"));
                edVolta.setCd_Modelo_Nota_Fiscal_Entrada(res.getString("Cd_Modelo_Nota_Fiscal_Entrada"));
                if(ed.getOid_Unidade()>0){
                	edVolta.setCd_Aidof_Nota_Fiscal_Devolucao(Parametro_FilialBean.getByOID_Unidade_Parametro(null, ed.getOid_Unidade()).getCD_AIDOF_Nota_Fiscal());
                }
                if(!JavaUtil.doValida(edVolta.getCd_Aidof_Nota_Fiscal_Devolucao())){
                	edVolta.setCd_Aidof_Nota_Fiscal_Devolucao(res.getString("Cd_Aidof_Nota_Fiscal_Devolucao"));
                }
                // System.out.println("AIDOF NF: "+edVolta.getCd_Aidof_Nota_Fiscal_Devolucao());
            }
            return edVolta;
		} catch (Exception exc) {
			exc.printStackTrace();
			throw new Excecoes(exc.getMessage(), exc,
					this.getClass().getName(), "getByRecord(Parametro_WmsED ed)");
		} finally {
			closeResultset(res);
		}
    }

    public void altera(Parametro_WmsED ed) throws Excecoes {
        String sql = null;
        try {
        	// Se n�o existe , cria
        	Parametro_WmsED edVolta = new Parametro_WmsED();
//        	edVolta = this.getByRecord(ed);
        	if (edVolta.getOid_Parametro_Wms()==0)
        		executasql.executarUpdate("insert into Parametros_Wms (oid_parametro_wms) values(1)");
        	// Atualiza
        	sql = " update Parametros_Wms set ";
            if (!"".equals(ed.getCd_Modelo_Nota_Fiscal()))
                sql += " Cd_Modelo_Nota_Fiscal = '" + ed.getCd_Modelo_Nota_Fiscal() + "', ";
            else sql += " Cd_Modelo_Nota_Fiscal = null, ";

            if (!"".equals(ed.getCd_Cfop_Retorno_Simbolico_Interestadual()))
                sql += " Cd_Cfop_Retorno_Simbolico_Interestadual = '" + ed.getCd_Cfop_Retorno_Simbolico_Interestadual() + "', ";
            else sql += " Cd_Cfop_Retorno_Simbolico_Interestadual = null, ";

            if (!"".equals(ed.getCd_Cfop_Retorno_Simbolico()))
                sql += " Cd_Cfop_Retorno_Simbolico = '" + ed.getCd_Cfop_Retorno_Simbolico() + "', ";
            else sql += " Cd_Cfop_Retorno_Simbolico = null, ";

            if (!"".equals(ed.getNm_Observacao_Devolucao()))
                sql += " Nm_Observacao_Devolucao = '" + ed.getNm_Observacao_Devolucao() + "', ";
            else sql += " Nm_Observacao_Devolucao = null, ";

            if (!"".equals(ed.getCd_Tipo_Produto()))
                sql += " Cd_Tipo_Produto = '" + ed.getCd_Tipo_Produto() + "', ";
            else sql += " Cd_Tipo_Produto = null, ";

            if (!"".equals(ed.getCd_Setor_Produto()))
                sql += " Cd_setor_produto = '" + ed.getCd_Setor_Produto() + "', ";
            else sql += " Cd_setor_produto = null, ";

            if (!"".equals(ed.getCd_Reduzido_Classificacao_Fiscal()))
                sql += " Cd_reduzido_classificacao_fiscal = '" + ed.getCd_Reduzido_Classificacao_Fiscal() + "', ";
            else sql += " Cd_reduzido_classificacao_fiscal = null, ";

            if (!"".equals(ed.getCd_Situacao_Tributaria()))
                sql += " Cd_situacao_tributaria = '" + ed.getCd_Situacao_Tributaria() + "', ";
            else sql += " Cd_situacao_tributaria = null, ";

            if (!"".equals(ed.getDm_Rotatividade()))
                sql += " Dm_Rotatividade = '" + ed.getDm_Rotatividade() + "', ";
            else sql += " Dm_Rotatividade = null, ";

            if (!"".equals(ed.getCd_Localizacao()))
                sql += " Cd_localizacao = '" + ed.getCd_Localizacao() + "', ";
            else sql += " Cd_localizacao = null, ";

            if (!"".equals(ed.getCd_Modelo_Nota_Fiscal_Saida()))
                sql += " Cd_Modelo_Nota_Fiscal_Saida = '" + ed.getCd_Modelo_Nota_Fiscal_Saida() + "', ";
            else sql += " Cd_Modelo_Nota_Fiscal_Saida = null, ";

            if (!"".equals(ed.getCd_Cfop_Saida()))
                sql += " Cd_Cfop_Saida = '" + ed.getCd_Cfop_Saida() + "', ";
            else sql += " Cd_Cfop_Saida = null, ";

            if (!"".equals(ed.getCd_Modelo_Nota_Fiscal_Entrada()))
                sql += " Cd_Modelo_Nota_Fiscal_Entrada = '" + ed.getCd_Modelo_Nota_Fiscal_Entrada() + "', ";
            else sql += " Cd_Modelo_Nota_Fiscal_Entrada = null, ";

            if (!"".equals(ed.getCd_Cfop_Entrada()))
                sql += " Cd_Cfop_Entrada = '" + ed.getCd_Cfop_Entrada() + "', ";
            else sql += " Cd_Cfop_Entrada = null, ";

            if (!"".equals(ed.getCd_Aidof_Nota_Fiscal_Devolucao()))
                sql += " Cd_Aidof_Nota_Fiscal_Devolucao = '" + ed.getCd_Aidof_Nota_Fiscal_Devolucao() + "', ";
            else sql += " Cd_Aidof_Nota_Fiscal_Devolucao = null, ";

            sql += " Oid_embalagem = " + ed.getOid_Embalagem() + ", ";

            sql += " Oid_tipo_pallet = " + ed.getOid_Tipo_Pallet() + " ";

            executasql.executarUpdate(sql);
        } catch(SQLException e) {
            throw new Excecoes(e.getMessage(), e, getClass().getName(), "altera(Parametro_WmsED ed)");
        }
    }
}