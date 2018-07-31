package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.master.ed.DepreciacaoED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.Valor;
import com.master.util.bd.ExecutaSQL;
import com.master.util.Utilitaria;
import com.master.util.Data;
public class DepreciacaoBD extends BancoUtil {


    private ExecutaSQL executasql;
    Utilitaria util = new Utilitaria(executasql);

    public DepreciacaoBD(ExecutaSQL sql) {
        this.executasql = sql;
    }

    public DepreciacaoED inclui(DepreciacaoED ed) throws Excecoes {

        String sql = null;
        try {

        	ed.setOid_Depreciacao(String.valueOf((getAutoIncremento ("OID_Depreciacao" , "Depreciacoes"))));

        	if (ed.getOid_Depreciacao() != null && "0".equals(ed.getOid_Depreciacao())){
        		ed.setOid_Depreciacao("1");
        	}

            sql = " INSERT INTO Depreciacoes (" +
                  "     OID_Depreciacao" +
                  "     ,DT_STAMP" +
                  "     ,USUARIO_STAMP" +
                  "     ,DM_STAMP" +
                  "     ,DM_Situacao" +
                  "     ,oid_pessoa_fornecedor" +
                  "     ,DT_Aquisicao" +
                  "     ,DT_Inicio_Depreciacao" +
                  "     ,TX_Observacao_Depreciacao" +
                  "     ,NR_Nota_Fiscal" +
                  "     ,NR_Quantidade" +
                  "     ,VL_Original" +
                  "     ,OID_Unidade " +
                  "     ,oid_Conta_Devedora " +
                  "     ,OID_Produto_Patrimonio) " +
                  " VALUES (" +
                         ed.getOid_Depreciacao() +
                  ",'" + ed.getDt_stamp() + "'" +
                  ",'" + ed.getUsuario_Stamp() + "'" +
                  ",'" + ed.getDm_Stamp() + "'" +
                  ",'L'" +
                  ",'" + ed.getOid_pessoa() + "'" +
                  ",'" + ed.getDt_aquisicao() + "'" +
                  ",'" + ed.getDt_inicio_depreciacao() + "'" +
                  ",'" + ed.getTx_observacao_depreciacao() + "'" +
                  "," + ed.getNr_nota_fiscal()+
                  "," + ed.getNr_quantidade() +
                  "," + ed.getVl_original()+ ",";
                sql += ed.getOid_Unidade() + ",";
                sql += ed.getOid_conta_devedora() + ",";
                sql += ed.getOid_Produto_Patrimonio() + ")";

                executasql.executarUpdate(sql);

                ed.setOid_Depreciacao(ed.getOid_Depreciacao());
                return ed;

        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "inclui()");
        }
    }

    public void altera(DepreciacaoED ed) throws Excecoes {

        String sql = null;
        try {

            sql = " UPDATE Depreciacoes SET ";
            sql +="     DT_STAMP = '" + ed.getDt_stamp() + "', ";
            sql +="     USUARIO_STAMP = '" + ed.getUsuario_Stamp() + "', ";
            sql +="     DM_STAMP = '" + ed.getDm_Stamp() + "', ";
            sql +="     oid_pessoa_fornecedor = '" + ed.getOid_pessoa() + "', ";
            sql +="     DT_Aquisicao = '" + ed.getDt_aquisicao() + "', ";
            sql +="     DT_Inicio_Depreciacao = '" + ed.getDt_inicio_depreciacao() + "', ";
            sql +="     TX_Observacao_Depreciacao = '" + ed.getTx_observacao_depreciacao() + "', ";
            sql +="     NR_Nota_Fiscal = " + ed.getNr_nota_fiscal() + ", ";
            sql +="     NR_Quantidade = " + ed.getNr_quantidade() + ", ";
            sql +="     VL_Original = " + ed.getVl_original() + ", ";
            sql +="     OID_Produto_Patrimonio = " + ed.getOid_Produto_Patrimonio() + ", ";
            sql +="     oid_Conta_Devedora = " + ed.getOid_conta_devedora() + ", ";
            sql +="     OID_Unidade = " + ed.getOid_Unidade() ;
            sql +=" WHERE oid_Depreciacao = " + ed.getOid_Depreciacao();

            executasql.executarUpdate(sql);
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "alterar()");
        }
    }

    public void vender(DepreciacaoED ed) throws Excecoes {

        String sql = null;
        try {

            sql = " UPDATE Depreciacoes SET ";
            sql +="     DT_STAMP = '" + Data.getDataDMY() + "', ";
            sql +="     USUARIO_STAMP = '" + ed.getUsuario_Stamp() + "', ";
            sql +="     DM_STAMP = '" + ed.getDm_Stamp() + "', ";
            sql +="     VL_Total_Depreciacao = " + ed.getVl_total_depreciacao() + ", ";
            sql +="     VL_Venda = " + ed.getVl_venda() + ", ";
            sql +="     VL_Ganho_Perda = " + ed.getVl_ganho_perda() + ", ";
            sql +="     DT_Venda = '" + ed.getDt_venda() + "', ";
            sql +="     TX_Observacao = '" + ed.getTx_observacao() + "', ";
            sql +="     DM_Situacao = 'V'" ; // Vendido
            sql +=" WHERE oid_Depreciacao = " + ed.getOid_Depreciacao();
            executasql.executarUpdate(sql);

            sql = " UPDATE Depreciacoes_Meses SET ";
            sql +="     DM_Situacao = 'V'" ; // Vendido
            sql +=" WHERE oid_Depreciacao = " + ed.getOid_Depreciacao();
            sql +=" AND dt_depreciacao >= '" + ed.getDt_venda() + "'";
            executasql.executarUpdate(sql);

        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "alterar()");
        }
    }

    public void gerarDepreciacao(DepreciacaoED ed) throws Excecoes {

        String sql = null;
        try {

            DepreciacaoED edVolta = new DepreciacaoED();

            edVolta = this.getByRecord(ed);

            boolean DM_Gera = true;
            int NR_Parcela = 0;
            String DT_Drepreciacao_Mes = edVolta.getDt_inicio_depreciacao();
            int nr_dias_vencimento=30;
            long oid_Depreciacao_Mes = getAutoIncremento ("OID_Depreciacao_Mes" , "Depreciacoes_Meses") + 1;

            while (DM_Gera){

            	if (NR_Parcela < edVolta.getNr_Meses()){

            		NR_Parcela = NR_Parcela + 1;

                	edVolta.setOid_Depreciacao_Mes(String.valueOf(oid_Depreciacao_Mes));

                	if (NR_Parcela > 1){
                		DT_Drepreciacao_Mes=Data.getSomaMesesData(DT_Drepreciacao_Mes,1);
                	}

                	if (NR_Parcela == edVolta.getNr_Meses()){
                		sql = "select sum(Vl_depreciacao_mes) as Vl_depreciacao_mes " +
                			  " from Depreciacoes_Meses " +
                			  " WHERE Depreciacoes_Meses.Oid_Depreciacao = " + edVolta.getOid_Depreciacao() ;
                        ResultSet res = this.executasql.executarConsulta(sql);
                        double Vl_depreciacao_mes = 0;
                        while (res.next())
                        {
                        	Vl_depreciacao_mes = res.getDouble("Vl_depreciacao_mes");
                        }

                        double vl_diferenca = Valor.round(edVolta.getVl_original() - Valor.round(Vl_depreciacao_mes,2),2);

                        if (vl_diferenca > 0){
                        	edVolta.setVl_depreciacao_mes(vl_diferenca);
                        }

                	}

                    sql = " INSERT INTO Depreciacoes_Meses (" +
	                    "     OID_Depreciacao_Mes" +
	                    "     ,OID_Depreciacao" +
	                    "     ,DT_STAMP" +
	                    "     ,USUARIO_STAMP" +
	                    "     ,DM_STAMP" +
	                    "     ,DM_SITUACAO" +
	                    "     ,DT_Depreciacao" +
	                    "     ,NR_Parcela" +
	                    "     ,Vl_depreciacao_mes)" +
	                    " VALUES (" +
	                           edVolta.getOid_Depreciacao_Mes() +
	                    "," + edVolta.getOid_Depreciacao() +
	                    ",'" + Data.getDataDMY() + "'" +
	                    ",'" + edVolta.getUsuario_Stamp() + "'" +
	                    ",'" + edVolta.getDm_Stamp() + "'" +
	                    ",'L'" +
	                    ",'" + DT_Drepreciacao_Mes + "'" +
	                    "," + NR_Parcela +
	                    "," + edVolta.getVl_depreciacao_mes()+ ")";

                    executasql.executarUpdate(sql);

            		oid_Depreciacao_Mes = oid_Depreciacao_Mes + 1;

            	}else{
            		DM_Gera = false;
            	}
            }

        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "alterar()");
        }
    }

    public void deleta(DepreciacaoED ed) throws Excecoes {

        String sql = null;
        try {

            sql = " DELETE FROM Depreciacoes_Meses WHERE oid_Depreciacao = " + ed.getOid_Depreciacao() ;

            executasql.executarUpdate(sql);

        	sql = " DELETE FROM Depreciacoes WHERE oid_Depreciacao = " + ed.getOid_Depreciacao() ;

            executasql.executarUpdate(sql);

        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "deleta()");
        }
    }

    public ArrayList lista(DepreciacaoED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();
        try {

            sql = " SELECT Depre.* " +
	              "       ,Pro_Pat.oid_Produto_Patrimonio " +
	              "       ,Pro_Pat.nm_Produto_Patrimonio " +
	              "       ,Cat.oid_Categoria " +
	              "       ,Cat.nm_Categoria " +
	              "       ,Cat.Pe_Fator_Anual " +
	              "       ,Cat.Nr_Anos " +
	              "       ,uni.oid_Unidade " +
	              "       ,uni.cd_Unidade " +
	              "       ,Pessoa_Uni.nm_razao_social as nm_Unidade " +
	              "       ,Pessoa_fornec.nm_razao_social as nm_razao_social_fornecedor " +
	              "       ,Pessoa_fornec.oid_Pessoa as oid_pessoa_fornecedor " +
	              "       ,Pessoa_fornec.nr_cnpj_cpf as nr_cnpj_cpf_fornecedor " +
            	  " FROM Depreciacoes Depre, Produtos_Patrimonios Pro_Pat, Categorias Cat, Unidades uni, pessoas Pessoa_Uni, pessoas Pessoa_fornec " +
  	              " WHERE Depre.oid_Produto_Patrimonio = Pro_Pat.oid_Produto_Patrimonio " +
                  " AND Depre.oid_Unidade = uni.oid_Unidade " +
  	              " AND Pro_Pat.oid_Categoria = Cat.oid_Categoria " +
                  " AND uni.oid_Pessoa = Pessoa_Uni.oid_Pessoa " +
                  " AND Depre.oid_pessoa_fornecedor = Pessoa_fornec.oid_Pessoa " ;
            if (util.doValida(ed.getOid_Depreciacao()))
                sql += " and Depre.oid_Depreciacao = " + ed.getOid_Depreciacao();
            else {
                if (util.doValida(ed.getOid_pessoa()))
                    sql += " and Depre.oid_pessoa_fornecedor = '" + ed.getOid_pessoa() + "'";
	            if (util.doValida(String.valueOf(ed.getOid_Produto_Patrimonio())))
	                sql += " and Depre.Oid_Produto_Patrimonio = " + ed.getOid_Produto_Patrimonio()  ;
	            if (util.doValida(String.valueOf(ed.getOid_Unidade())))
	                sql += " and Depre.Oid_Unidade = " + ed.getOid_Unidade()  ;
	            if (util.doValida(String.valueOf(ed.getNr_nota_fiscal())))
	                sql += " and Depre.Nr_nota_fiscal = " + ed.getNr_nota_fiscal()  ;
                if (util.doValida(ed.getDt_aquisicao_inicial()))
                    sql += " and Depre.Dt_aquisicao >= '" + ed.getDt_aquisicao_inicial() + "'";
                if (util.doValida(ed.getDt_aquisicao_final()))
                    sql += " and Depre.Dt_aquisicao <= '" + ed.getDt_aquisicao_final() + "'";
                if (util.doValida(ed.getDt_inicio_depreciacao_inicial()))
                    sql += " and Depre.Dt_inicio_depreciacao >= '" + ed.getDt_inicio_depreciacao_inicial() + "'";
                if (util.doValida(ed.getDt_inicio_depreciacao_final()))
                    sql += " and Depre.Dt_inicio_depreciacao <= '" + ed.getDt_inicio_depreciacao_final() + "'";

            }
            sql +=" ORDER BY oid_Depreciacao ";

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                DepreciacaoED edVolta = new DepreciacaoED();

                edVolta.setOid_Depreciacao(res.getString("oid_Depreciacao"));
                edVolta.setOid_Produto_Patrimonio(new Integer(res.getInt("Oid_Produto_Patrimonio")));
                edVolta.setNm_Produto_Patrimonio(res.getString("Nm_Produto_Patrimonio"));
                edVolta.setOid_Unidade(new Integer(res.getInt("Oid_Unidade")));
                edVolta.setNm_Unidade(res.getString("Nm_Unidade"));
                edVolta.setCd_Unidade(res.getString("Cd_Unidade"));
                edVolta.setOid_pessoa(res.getString("oid_pessoa_fornecedor"));
                edVolta.setNm_razao_social(res.getString("nm_razao_social_fornecedor"));
                edVolta.setNr_cnpj_cpf(res.getString("nr_cnpj_cpf_fornecedor"));
                edVolta.setDt_aquisicao (FormataData.formataDataBT (res.getDate ("DT_Aquisicao")));
                edVolta.setDt_inicio_depreciacao (FormataData.formataDataBT (res.getDate ("DT_Inicio_Depreciacao")));
                edVolta.setNr_nota_fiscal(res.getLong("NR_Nota_Fiscal"));
                edVolta.setNr_quantidade(res.getLong("NR_Quantidade"));
                edVolta.setVl_original(res.getDouble("VL_Original"));

                edVolta.setOid_Categoria(res.getString("oid_Categoria"));
                edVolta.setNm_Categoria(res.getString("Nm_Categoria"));

                edVolta.setPe_Fator_Anual(new Double(res.getDouble("Pe_Fator_Anual")));
                edVolta.setNr_Anos(res.getLong("Nr_Anos"));
                edVolta.setNr_Meses(edVolta.getNr_Anos()*12);

                double vl_Original = edVolta.getVl_original();
                double nr_Meses = new Double(edVolta.getNr_Anos()*12).doubleValue();
                double vl_Resultado = vl_Original / nr_Meses;

                edVolta.setVl_depreciacao_mes(Valor.round(vl_Resultado));

                boolean DM_Leva_Para_lista = true;

                if(util.doValida(ed.getDt_emissao_inicial()) && util.doValida(ed.getDt_emissao_final()) ){
                   sql = "select count('oid_depreciacao') as nr_quantidade_parcelas, sum(Vl_depreciacao_mes) as Vl_depreciacao_mes " +
	      			  " from Depreciacoes_Meses " +
	      			  " WHERE Depreciacoes_Meses.Oid_Depreciacao = " + edVolta.getOid_Depreciacao() +
	      			  " AND Depreciacoes_Meses.dt_depreciacao between '" + ed.getDt_emissao_inicial() + "' and '" + ed.getDt_emissao_final() + "'" +
                   	  " AND Depreciacoes_Meses.DM_Situacao = 'L'";

	               ResultSet resLocal = this.executasql.executarConsulta(sql);
	               double Vl_depreciacao_mes = 0;
	               while (resLocal.next())
	               {
	              	 edVolta.setVl_depreciacao_contabilizada(Valor.round(resLocal.getDouble("Vl_depreciacao_mes"),2));
	              	 edVolta.setVl_depreciacao_pendente(Valor.round(edVolta.getVl_original()-resLocal.getDouble("Vl_depreciacao_mes"),2));
	              	 edVolta.setNr_parcela_contabilizada(resLocal.getLong("nr_quantidade_parcelas"));
	              	 edVolta.setNr_parcela_pendente(edVolta.getNr_Meses()-resLocal.getLong("nr_quantidade_parcelas"));
	               }
	               if (resLocal.getLong("nr_quantidade_parcelas")==0){
	            	  DM_Leva_Para_lista = false;
	               }
                   sql = "select count('oid_depreciacao') as nr_quantidade_parcelas, sum(Vl_depreciacao_mes) as Vl_depreciacao_mes " +
	      			  " from Depreciacoes_Meses " +
	      			  " WHERE Depreciacoes_Meses.Oid_Depreciacao = " + edVolta.getOid_Depreciacao() +
	      			  " AND Depreciacoes_Meses.dt_depreciacao >= '" + ed.getDt_emissao_inicial() + "'" +
                	  " AND Depreciacoes_Meses.DM_Situacao = 'V'";

	               resLocal = this.executasql.executarConsulta(sql);
	               Vl_depreciacao_mes = 0;
	               while (resLocal.next())
	               {
	              	 edVolta.setNr_meses_vendido(resLocal.getLong("nr_quantidade_parcelas"));
	            	 edVolta.setVl_depreciacao_vendida(Valor.round(resLocal.getDouble("Vl_depreciacao_mes"),2));
	            	 edVolta.setVl_depreciacao_pendente(edVolta.getVl_depreciacao_pendente() - edVolta.getVl_depreciacao_vendida());
	              	 edVolta.setNr_parcela_pendente(edVolta.getNr_parcela_pendente() - edVolta.getNr_meses_vendido());
	               }
                }

                 if (DM_Leva_Para_lista){
                    list.add(edVolta);
                 }
            }
            return list;

        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "lista()");
        }
    }

    public ArrayList Depreciacao_Mes_Lista(DepreciacaoED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();
        try {

            sql = " SELECT Depre_Mes.* " +
            	  " FROM depreciacoes_meses Depre_Mes" +
  	              " WHERE 1=1 " ;
            if (util.doValida(ed.getOid_Depreciacao_Mes()))
                sql += " and Depre_Mes.oid_Depreciacao_Mes = " + ed.getOid_Depreciacao_Mes();
            else {
	            if (util.doValida(String.valueOf(ed.getOid_Depreciacao()))){
	                sql += " and Depre_Mes.oid_Depreciacao = " + ed.getOid_Depreciacao()  ;
	            }
            }
            sql +=" ORDER BY NR_Parcela ";

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                DepreciacaoED edVolta = new DepreciacaoED();

                edVolta.setOid_Depreciacao_Mes(res.getString("oid_Depreciacao_Mes"));
                edVolta.setOid_Depreciacao(res.getString("oid_Depreciacao"));
                edVolta.setDt_depreciacao_mes(FormataData.formataDataBT (res.getDate ("dt_depreciacao")));
                edVolta.setNr_parcela(res.getLong("nr_parcela"));
                edVolta.setVl_depreciacao_mes(res.getDouble("vl_depreciacao_mes"));
                edVolta.setDm_situacao(res.getString("Dm_situacao"));

                list.add(edVolta);
            }
            return list;

        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "lista()");
        }
    }

    public ArrayList Depreciacao_Sintetico_Lista(DepreciacaoED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();
        try {

            sql = " select sum(Vl_depreciacao_mes) as Vl_depreciacao_mes, " +
            		" contas.cd_conta as cd_conta_devedora, " +
            		" contas.nm_conta as nm_conta_devedora, " +
            		" con_cat.cd_conta as cd_conta_credora, " +
            		" con_cat.nm_conta as nm_conta_credora, " +
            		" categorias.oid_categoria, " +
            		" categorias.nm_categoria  " +
            		" from Depreciacoes_Meses, Depreciacoes, produtos_patrimonios, categorias, contas, contas con_cat  " +
            		" WHERE Depreciacoes_Meses.oid_depreciacao = Depreciacoes.oid_depreciacao " +
            		" and depreciacoes.oid_produto_patrimonio = produtos_patrimonios.oid_produto_patrimonio " +
            		" and produtos_patrimonios.oid_categoria = categorias.oid_categoria " +
            		" and produtos_patrimonios.oid_conta = contas.oid_conta " +
            		" and categorias.oid_conta = con_cat.oid_conta " +
        			" AND Depreciacoes_Meses.dt_depreciacao between '" + ed.getDt_emissao_inicial() + "' and '" + ed.getDt_emissao_final() + "'"+
        			" AND Depreciacoes_Meses.DM_Situacao = 'L'";

            if (util.doValida(ed.getOid_Depreciacao_Mes()))
                sql += " and Depre_Mes.oid_Depreciacao_Mes = " + ed.getOid_Depreciacao_Mes();
            else {
	            if (util.doValida(String.valueOf(ed.getOid_Depreciacao()))){
	                sql += " and Depre_Mes.oid_Depreciacao = " + ed.getOid_Depreciacao()  ;
	            }
            }
            sql +=" group by produtos_patrimonios.oid_conta, categorias.oid_categoria, categorias.nm_categoria, contas.cd_conta, contas.nm_conta, con_cat.cd_conta, con_cat.nm_conta ";
            sql +=" ORDER BY categorias.nm_categoria ";

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                DepreciacaoED edVolta = new DepreciacaoED();

                edVolta.setVl_depreciacao_mes(res.getDouble("Vl_depreciacao_mes"));

                edVolta.setCd_conta_credora(res.getString("cd_conta_credora"));
                edVolta.setNm_conta_credora(res.getString("nm_conta_credora"));
                edVolta.setCd_conta_devedora(res.getString("cd_conta_devedora"));
                edVolta.setNm_conta_devedora(res.getString("nm_conta_devedora"));
                edVolta.setOid_Categoria(res.getString("oid_categoria"));
                edVolta.setNm_Categoria(res.getString("nm_categoria"));


                list.add(edVolta);
            }
            return list;

        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "lista()");
        }
    }

    public DepreciacaoED getByRecord(DepreciacaoED ed) throws Excecoes {

        DepreciacaoED edVolta = new DepreciacaoED();
        try {

            String sql = " SELECT Depre.* " +
		            "       ,Pro_Pat.oid_Produto_Patrimonio " +
		            "       ,Pro_Pat.nm_Produto_Patrimonio " +
		            "       ,Cat.oid_Categoria " +
		            "       ,Cat.nm_Categoria " +
		            "       ,Cat.Pe_Fator_Anual " +
		            "       ,Cat.Nr_Anos " +
		            "       ,uni.oid_Unidade " +
		            "       ,uni.cd_Unidade " +
		            "       ,Pessoa_Uni.nm_razao_social as nm_Unidade " +
		            "       ,Pessoa_fornec.nm_razao_social as nm_razao_social_fornecedor " +
		            "       ,Pessoa_fornec.oid_Pessoa as oid_pessoa_fornecedor " +
		            "       ,Pessoa_fornec.nr_cnpj_cpf as nr_cnpj_cpf_fornecedor " +
		      	    " FROM Depreciacoes Depre, Produtos_Patrimonios Pro_Pat, Categorias Cat, Unidades uni, pessoas Pessoa_Uni, pessoas Pessoa_fornec " +
		            " WHERE Depre.oid_Produto_Patrimonio = Pro_Pat.oid_Produto_Patrimonio " +
		            " AND Depre.oid_Unidade = uni.oid_Unidade " +
		            " AND Pro_Pat.oid_Categoria = Cat.oid_Categoria " +
		            " AND uni.oid_Pessoa = Pessoa_Uni.oid_Pessoa " +
		            " AND Depre.oid_pessoa_fornecedor = Pessoa_fornec.oid_Pessoa " +
		            " AND Depre.oid_Depreciacao = " + ed.getOid_Depreciacao() ;

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                edVolta = new DepreciacaoED();
                edVolta.setOid_Depreciacao(res.getString("oid_Depreciacao"));
                edVolta.setOid_Produto_Patrimonio(new Integer(res.getInt("Oid_Produto_Patrimonio")));
                edVolta.setNm_Produto_Patrimonio(res.getString("Nm_Produto_Patrimonio"));
                edVolta.setOid_Unidade(new Integer(res.getInt("Oid_Unidade")));
                edVolta.setNm_Unidade(res.getString("Nm_Unidade"));
                edVolta.setCd_Unidade(res.getString("Cd_Unidade"));
                edVolta.setOid_pessoa(res.getString("oid_pessoa_fornecedor"));
                edVolta.setNm_razao_social(res.getString("nm_razao_social_fornecedor"));
                edVolta.setNr_cnpj_cpf(res.getString("nr_cnpj_cpf_fornecedor"));
                edVolta.setDt_aquisicao (FormataData.formataDataBT (res.getDate ("DT_Aquisicao")));
                edVolta.setDt_inicio_depreciacao (FormataData.formataDataBT (res.getDate ("DT_Inicio_Depreciacao")));
                edVolta.setNr_nota_fiscal(res.getLong("NR_Nota_Fiscal"));
                edVolta.setNr_quantidade(res.getLong("NR_Quantidade"));
                edVolta.setVl_original(res.getDouble("VL_Original"));
                edVolta.setTx_observacao_depreciacao(res.getString("TX_Observacao_Depreciacao"));

                edVolta.setOid_Categoria(res.getString("oid_Categoria"));
                edVolta.setNm_Categoria(res.getString("Nm_Categoria"));
                edVolta.setDm_situacao(res.getString("DM_Situacao"));

                edVolta.setPe_Fator_Anual(new Double(res.getDouble("Pe_Fator_Anual")));
                edVolta.setNr_Anos(res.getLong("Nr_Anos"));
                edVolta.setNr_Meses(edVolta.getNr_Anos()*12);

                double vl_Original = edVolta.getVl_original();
                double nr_Meses = new Double(edVolta.getNr_Anos()*12).doubleValue();
                double vl_Resultado = vl_Original / nr_Meses;

                edVolta.setVl_depreciacao_mes(Valor.round(vl_Resultado));
	            if (util.doValida(String.valueOf(res.getString("oid_conta_Devedora")))){
                    sql = " select * " +
            		" from contas " +
            		" WHERE oid_Conta = " + res.getString("oid_conta_Devedora");

                    ResultSet resLocal = this.executasql.executarConsulta(sql);
                    while (resLocal.next())
                    {
                        edVolta.setCd_conta_devedora(resLocal.getString("CD_Conta"));
                        edVolta.setNm_conta_devedora(resLocal.getString("NM_Conta"));
                        edVolta.setOid_conta_devedora(res.getLong("oid_Conta_Devedora"));
                    }
                }

            }
            return edVolta;
        } catch (SQLException e) {
            throw new Excecoes(e.getMessage(), e, getClass().getName(), "getByRecord()");
        }
    }
    public DepreciacaoED getByRecordVenda(DepreciacaoED ed) throws Excecoes {

        DepreciacaoED edVolta = new DepreciacaoED();
        ResultSet res = null;
        ResultSet resLocal = null;
        try {

            String sql = " SELECT Depre.* " +
		            "       ,Pro_Pat.oid_Produto_Patrimonio " +
		            "       ,Pro_Pat.nm_Produto_Patrimonio " +
		            "       ,Cat.oid_Categoria " +
		            "       ,Cat.nm_Categoria " +
		            "       ,Cat.Pe_Fator_Anual " +
		            "       ,Cat.Nr_Anos " +
		            "       ,uni.oid_Unidade " +
		            "       ,uni.cd_Unidade " +
		            "       ,Pessoa_Uni.nm_razao_social as nm_Unidade " +
		            "       ,Pessoa_fornec.nm_razao_social as nm_razao_social_fornecedor " +
		            "       ,Pessoa_fornec.oid_Pessoa as oid_pessoa_fornecedor " +
		            "       ,Pessoa_fornec.nr_cnpj_cpf as nr_cnpj_cpf_fornecedor " +
		      	    " FROM Depreciacoes Depre, Produtos_Patrimonios Pro_Pat, Categorias Cat, " +
		      	    " Unidades uni, pessoas Pessoa_Uni, pessoas Pessoa_fornec " +
		            " WHERE Depre.oid_Produto_Patrimonio = Pro_Pat.oid_Produto_Patrimonio " +
		            " AND Depre.oid_Unidade = uni.oid_Unidade " +
		            " AND Pro_Pat.oid_Categoria = Cat.oid_Categoria " +
		            " AND uni.oid_Pessoa = Pessoa_Uni.oid_Pessoa " +
		            " AND Depre.oid_pessoa_fornecedor = Pessoa_fornec.oid_Pessoa " +
		            " AND Depre.oid_Depreciacao = " + ed.getOid_Depreciacao() ;

            res = this.executasql.executarConsulta(sql);

            while (res.next())
            {
                edVolta = new DepreciacaoED();
                edVolta.setOid_Depreciacao(res.getString("oid_Depreciacao"));
                edVolta.setOid_Produto_Patrimonio(new Integer(res.getInt("Oid_Produto_Patrimonio")));
                edVolta.setNm_Produto_Patrimonio(res.getString("Nm_Produto_Patrimonio"));
                edVolta.setOid_Unidade(new Integer(res.getInt("Oid_Unidade")));
                edVolta.setNm_Unidade(res.getString("Nm_Unidade"));
                edVolta.setCd_Unidade(res.getString("Cd_Unidade"));
                edVolta.setOid_pessoa(res.getString("oid_pessoa_fornecedor"));
                edVolta.setNm_razao_social(res.getString("nm_razao_social_fornecedor"));
                edVolta.setNr_cnpj_cpf(res.getString("nr_cnpj_cpf_fornecedor"));
                edVolta.setDt_aquisicao (FormataData.formataDataBT (res.getDate ("DT_Aquisicao")));
                edVolta.setDt_inicio_depreciacao (FormataData.formataDataBT (res.getDate ("DT_Inicio_Depreciacao")));
                edVolta.setNr_nota_fiscal(res.getLong("NR_Nota_Fiscal"));
                edVolta.setNr_quantidade(res.getLong("NR_Quantidade"));
                edVolta.setVl_original(res.getDouble("VL_Original"));

                edVolta.setOid_Categoria(res.getString("oid_Categoria"));
                edVolta.setNm_Categoria(res.getString("Nm_Categoria"));

                edVolta.setDm_situacao(res.getString("DM_Situacao"));

                edVolta.setPe_Fator_Anual(new Double(res.getDouble("Pe_Fator_Anual")));
                edVolta.setNr_Anos(res.getLong("Nr_Anos"));
                edVolta.setNr_Meses(edVolta.getNr_Anos()*12);

                double vl_Original = edVolta.getVl_original();
                double nr_Meses = new Double(edVolta.getNr_Anos()*12).doubleValue();
                double vl_Resultado = vl_Original / nr_Meses;

                edVolta.setVl_depreciacao_mes(Valor.round(vl_Resultado));

                edVolta.setVl_venda(res.getDouble("VL_Venda"));
                edVolta.setVl_ganho_perda(res.getDouble("VL_Ganho_Perda"));
                edVolta.setDt_venda (FormataData.formataDataBT (res.getDate ("DT_Venda")));
                edVolta.setTx_observacao(res.getString("TX_Observacao"));

                if (res.getDouble("vl_total_depreciacao") == 0){
                    sql = " select sum(Vl_depreciacao_mes) as Vl_depreciacao_mes " +
            		" from Depreciacoes_Meses  Depre_Mes " +
            		" WHERE Depre_Mes.dt_depreciacao <= '" + Data.getDataDMY() + "'";
                    sql += " and Depre_Mes.oid_Depreciacao = " + ed.getOid_Depreciacao()  ;

                    resLocal = this.executasql.executarConsulta(sql);
                    while (resLocal.next())
                    {
                        edVolta.setVl_total_depreciacao(resLocal.getDouble("Vl_depreciacao_mes"));
                    }
                }else{
                    edVolta.setVl_total_depreciacao(res.getDouble("vl_total_depreciacao"));
                }

            }
            return edVolta;
        } catch (SQLException e) {
            throw new Excecoes(e.getMessage(), e, getClass().getName(), "getByRecord()");
        }
    }

}
