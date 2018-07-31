package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.CompromissoED;
import com.master.ed.DespesaED;
import com.master.ed.DespesaPesquisaED;
import com.master.rl.DespesaRL;
import com.master.root.FormataDataBean;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

public class DespesaBD {

    private ExecutaSQL executasql;

    public DespesaBD(ExecutaSQL sql) {
        this.executasql = sql;
    }


    public DespesaED inclui(DespesaED ed) throws Excecoes {

        String sql = null;
        int Nr_Despesa = 0;
        int Oid_Despesa = 0;
        int Oid_Compromisso = 0;




        try {

            if (ed.getVl_Compromisso().doubleValue()>0 ){
                CompromissoBD compromissoBD = new CompromissoBD(this.executasql);
                CompromissoED edComp = new CompromissoED();

                edComp.setDt_Emissao(ed.getDt_Emissao());
                edComp.setDt_Vencimento(ed.getDt_Vencimento());
                edComp.setDt_Entrada(ed.getDt_Entrada());
                edComp.setNr_Documento(ed.getNr_Documento());
                edComp.setDt_Entrada(ed.getDt_Emissao());
                edComp.setNr_Parcela(new Integer(1));

                edComp.setOid_Centro_Custo(new Integer(ed.getOid_Centro_Custo().intValue()));
                edComp.setOid_Conta(new Integer(ed.getOid_Conta().intValue()));
                edComp.setOid_Pessoa(ed.getOid_Pessoa());
                edComp.setOid_Tipo_Documento(new Integer(ed.getOid_Tipo_Documento().intValue()));
                edComp.setOid_Natureza_Operacao(new Integer(ed.getOid_Natureza_Operacao().intValue()));
                edComp.setOid_Unidade(new Long(ed.getOid_Unidade().longValue()));
                edComp.setTx_Observacao(ed.getTx_Observacao());
                edComp.setDM_Tipo_Pagamento(ed.getDM_Tipo_Pagamento());
                edComp.setVl_Compromisso(new Double(ed.getVl_Compromisso().doubleValue()));
                edComp.setCD_Barra("");
                edComp.setVl_Saldo(new Double(ed.getVl_Compromisso().doubleValue()));

                // System.out.println("INC 10");
                edComp = compromissoBD.inclui(edComp);
                Oid_Compromisso=(edComp.getOid_Compromisso().intValue());
            }


            ResultSet rs = executasql.executarConsulta("select max(Oid_Despesa) as result from Despesas");

      //pega proximo valor da chave
            while (rs.next()) Oid_Despesa = rs.getInt("result");
             Oid_Despesa = Oid_Despesa+1;


      sql = " INSERT INTO Despesas(" +
      		"		OID_Despesa" +
      		"		,NR_DOCUMENTO" +
      		"		,NR_PARCELA" +
      		"		,DT_EMISSAO" +
      		"		,DT_VENCIMENTO" +
      		"		,VL_Despesa" +
      		"		,TX_OBSERVACAO" +
      		"		,DM_TIPO_PAGAMENTO" +
      		"		,NR_Despesa" +
      		"		,DT_STAMP" +
      		"		,USUARIO_STAMP" +
      		"		,DM_STAMP" +
      		"		,VL_Compromisso" +
      		"		,OID_TIPO_DOCUMENTO" +
      		"		,OID_CONTA" +
      		"		,OID_PESSOA" +
      		"		,OID_CENTRO_CUSTO" +
      		"		,OID_UNIDADE" +
      		"		,OID_COMPROMISSO" +
      		"		,OID_NATUREZA_OPERACAO)" +
      		" VALUES ("
      		+ Oid_Despesa+ ",";
            sql += ed.getNr_Documento() + ",";
            sql += ed.getNr_Parcela() == null ? "null," : ed.getNr_Parcela() + ",";
            sql += "'" + ed.getDt_Emissao() + "',";
            sql += "'" + ed.getDt_Vencimento() + "',";
            sql += ed.getVl_Despesa() + ",";
            sql += ed.getTx_Observacao() == null ? "null," : "'" + ed.getTx_Observacao() + "',";
            sql += ed.getDM_Tipo_Pagamento() == null ? "null," : "'" + ed.getDM_Tipo_Pagamento() + "',";
            sql += Nr_Despesa + ",";
            sql += "'" + ed.getDt_stamp() + "',";
            sql += "'" + ed.getUsuario_Stamp() + "',";
            sql += "'" + ed.getDm_Stamp() + "',";
            sql += ed.getVl_Compromisso() + ",";
            sql += ed.getOid_Tipo_Documento() + ",";
            sql += ed.getOid_Conta() + ",";
            sql += "'" + ed.getOid_Pessoa() + "',";
            sql += ed.getOid_Centro_Custo() + ",";
            sql += ed.getOid_Unidade() + ",";
            sql += Oid_Compromisso + ",";
            sql += ed.getOid_Natureza_Operacao() == null ? "null )" : ed.getOid_Natureza_Operacao() + ")";

            // System.out.println(sql);

            executasql.executarUpdate(sql);



        } catch (Exception exc) {
        	exc.printStackTrace();
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem(exc.getMessage());
            excecoes.setMetodo("Inclui(DespesaED)");
            excecoes.setExc(exc);
            throw excecoes;
        }
        return ed;
    }

    public DespesaED getByRecord(DespesaED ed) throws Excecoes {

        String sql = null;

        DespesaED edVolta = new DespesaED();
        FormataDataBean dataFormatada = new FormataDataBean();
        ResultSet resTP = null;

        try {

      sql = "select * from Despesas, pessoas, centros_Custos, tipos_documentos, contas, naturezas_operacoes where "+
        	" Despesas.oid_pessoa = pessoas.oid_pessoa and"+
        	" Despesas.oid_conta = contas.oid_conta and"+
        	" Despesas.oid_natureza_operacao = naturezas_operacoes.oid_natureza_operacao and"+
        	" Despesas.oid_centro_custo = centros_custos.oid_centro_custo and"+
        	" Despesas.oid_tipo_documento = tipos_documentos.oid_tipo_documento";

            if (ed.getOid_Despesa() != null && !ed.getOid_Despesa().equals("") && !ed.getOid_Despesa().equals("null")) {
                sql += " and Despesas.oid_Despesa = " + ed.getOid_Despesa();
            }

            ResultSet res = null;

            res = this.executasql.executarConsulta(sql);

            while (res.next()) {
                edVolta = new DespesaED();
                edVolta.setOid_Despesa(new Integer(res.getString("oid_Despesa")));
                edVolta.setOid_Compromisso(new Integer(res.getString("Oid_Compromisso")));
                edVolta.setOid_Pessoa(res.getString("oid_pessoa"));
                edVolta.setNr_CNPJ_CPF(res.getString("nr_cnpj_cpf"));
                edVolta.setNm_Razao_Social(res.getString("nm_razao_Social"));

                edVolta.setOid_Centro_Custo(new Integer(res.getString("oid_centro_custo")));
                edVolta.setCd_Centro_Custo(res.getString("cd_Centro_custo"));
                edVolta.setNm_Centro_Custo(res.getString("nm_centro_Custo"));

                edVolta.setOid_Conta(new Integer(res.getString("oid_conta")));
                edVolta.setCd_Conta(res.getString("cd_conta"));
                edVolta.setNm_Conta(res.getString("nm_Conta"));

                edVolta.setOid_Tipo_Documento(new Integer(res.getString("oid_tipo_documento")));
                edVolta.setCd_Tipo_Documento(res.getString("cd_tipo_documento"));
                edVolta.setNm_Tipo_Documento(res.getString("nm_tipo_documento"));

                edVolta.setOid_Natureza_Operacao(new Integer(res.getString("oid_Natureza_Operacao")));
                edVolta.setCd_Natureza_Operacao(res.getString("cd_Natureza_Operacao"));
                edVolta.setNm_Natureza_Operacao(res.getString("nm_Natureza_Operacao"));

                String sql2 = "select cd_Unidade, nm_fantasia from pessoas, unidades where pessoas.oid_pessoa = unidades.oid_pessoa and " + " unidades.oid_unidade = "
                        + Integer.parseInt(res.getString("oid_unidade"));

                ResultSet resUni = this.executasql.executarConsulta(sql2);
                if (resUni != null)
                    resUni.next();

                edVolta.setOid_Unidade(new Long(res.getString("oid_unidade")));

                edVolta.setCd_Unidade(resUni.getString("cd_unidade"));
                edVolta.setNm_Fantasia(resUni.getString("nm_fantasia"));

                edVolta.setNr_Despesa(new Integer(res.getString("nr_Despesa")));

                FormataDataBean DataFormatada = new FormataDataBean();
                DataFormatada.setDT_FormataData(res.getString("dt_vencimento"));
                edVolta.setDt_Vencimento(DataFormatada.getDT_FormataData());


                DataFormatada.setDT_FormataData(res.getString("dt_emissao"));
                edVolta.setDt_Emissao(DataFormatada.getDT_FormataData());


                edVolta.setNr_Despesa(new Integer(res.getString("nr_Despesa")));
                edVolta.setNr_Documento(res.getString("nr_documento"));


                String tx_observacao = res.getString("tx_observacao");
                if (tx_observacao != null)
                    edVolta.setTx_Observacao(tx_observacao);

                String DM_Tipo_Pagamento = res.getString("DM_Tipo_Pagamento");
                if (DM_Tipo_Pagamento != null)
                    edVolta.setDM_Tipo_Pagamento(DM_Tipo_Pagamento);

                edVolta.setVl_Despesa(new Double(res.getString("vl_Despesa")));


                edVolta.setVl_Compromisso(new Double(res.getString("vl_Compromisso")));


                edVolta.setOid_Pessoa(res.getString("oid_pessoa"));
            }
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao acessar Despesa");
            excecoes.setMetodo("getByRecord(DespesaED)");
            excecoes.setExc(exc);
            throw excecoes;
        }

        return edVolta;
    }


    public void altera(DespesaED ed) throws Excecoes {

        String sql = null;

        try {

            sql = " update Despesas set ";

            if (ed.getTx_Observacao() == null)
                sql += "tx_observacao = null,";
            else
                sql += "tx_observacao = '" + ed.getTx_Observacao() + "',";

            sql += "oid_conta = " + ed.getOid_Conta() + ",";
            sql += "vl_Despesa = " + ed.getVl_Despesa() ;
            sql += " where oid_Despesa = " + ed.getOid_Despesa();

            // System.out.println(sql);

            int i = executasql.executarUpdate(sql);
        }

        catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao alterar dados de Despesa ");
            excecoes.setMetodo("altera(DespesaED)");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }


    public void deleta(DespesaED ed) throws Excecoes {

        String sql = null;
        ResultSet res = null;

        try {

            sql = "delete from Despesas WHERE oid_Despesa = ";
            sql += ed.getOid_Despesa();
            int i = executasql.executarUpdate(sql);

        }

        catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMetodo("deleta(DespesaED)");
            excecoes.setExc(exc);
            throw excecoes;

        }
    }


    public ArrayList lista(DespesaED edComp) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();
        DespesaPesquisaED ed = (DespesaPesquisaED) edComp;

        try {
            sql = "select * from Despesas, pessoas, tipos_documentos where  " + " Despesas.oid_pessoa = pessoas.oid_pessoa and"
                    + " Despesas.oid_tipo_documento = tipos_Documentos.oid_tipo_documento ";

            if (ed.getOid_Despesa() != null)
                sql += " and Despesas.oid_Despesa = " + ed.getOid_Despesa();


            if (ed.getNr_Despesa() != null)
                sql += " and Despesas.nr_Despesa = " + ed.getNr_Despesa();

            if (ed.getNr_Documento() != null)
                sql += " and Despesas.NR_Documento = " + ed.getNr_Documento();

            if (ed.getOid_Pessoa() != null)
                sql += " and Despesas.oid_pessoa = '" + ed.getOid_Pessoa() + "'";


            if (ed.getData_Emissao_Inicial() != null)
                sql += " and Despesas.dt_Emissao >= '" + ed.getData_Emissao_Inicial() + "'";

            if (ed.getData_Emissao_Final() != null)
                sql += " and Despesas.dt_Emissao <= '" + ed.getData_Emissao_Final() + "'";


            if (ed.getNm_Razao_Social() != null)
                sql += " and pessoas.nm_razao_social like '" + ed.getNm_Razao_Social() + "%'";

            sql += " ORDER BY Despesas.DT_Emissao ";


            // System.out.println(sql);

            ResultSet res = null;
            res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
                DespesaED edVolta = new DespesaED();
                //popular os dados de Despesa para voltar

            // System.out.println("1");


                edVolta.setOid_Despesa(new Integer(res.getString("oid_Despesa")));
                edVolta.setNm_Razao_Social(res.getString("nm_razao_Social"));
                edVolta.setNr_Despesa(new Integer(res.getString("nr_Despesa")));
                edVolta.setNr_CNPJ_CPF(res.getString("nr_cnpj_cpf"));
                edVolta.setOid_Pessoa(res.getString("oid_pessoa"));
            // System.out.println("2");
                edVolta.setNr_Documento(res.getString("nr_Documento"));
                edVolta.setNm_Tipo_Documento(res.getString("nm_tipo_documento"));

                FormataDataBean DataFormatada = new FormataDataBean();
                DataFormatada.setDT_FormataData(res.getString("dt_vencimento"));
                edVolta.setDt_Vencimento(DataFormatada.getDT_FormataData());

            // System.out.println("3");

                edVolta.setVl_Compromisso(new Double(res.getDouble("vl_Compromisso")));
                edVolta.setVl_Despesa(new Double(res.getDouble("vl_Despesa")));

                DataFormatada.setDT_FormataData(res.getString("dt_emissao"));
                edVolta.setDt_Emissao(DataFormatada.getDT_FormataData());


                list.add(edVolta);
            }
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao listar dados de Despesa");
            excecoes.setMetodo("lista(DespesaED)");
            excecoes.setExc(exc);
            throw excecoes;
        }

        return list;
    }



    public void geraRelatorio(DespesaED ed) throws Excecoes {

        //    String sql = null;
        //
        //    DespesaED edVolta = new DespesaED();
        //
        //    try{
        //
        //      sql = "select * from Despesas where oid_Despesa > 0";
        //
        //      if (ed.getCD_Despesa() != null &&
        // !ed.getCD_Despesa().equals("")){
        //        sql += " and CD_Despesa = '" + ed.getCD_Despesa() + "'";
        //      }
        //      if (ed.getCD_Remessa() != null && !ed.getCD_Remessa().equals("")){
        //        sql += " and CD_Remessa = '" + ed.getCD_Remessa() + "'";
        //      }
        //
        //      ResultSet res = null;
        //      res = this.executasql.executarConsulta(sql);
        //
        //      DespesaRL Despesa_rl = new DespesaRL();
        //      Despesa_rl.geraRelatEstoque(res);
        //    }
        //    catch (Excecoes e){
        //      throw e;
        //    }
        //    catch(Exception exc){
        //      Excecoes exce = new Excecoes();
        //      exce.setExc(exc);
        //      exce.setMensagem("Erro no método listar");
        //      exce.setClasse(this.getClass().getName());
        //      exce.setMetodo("geraRelatorio(DespesaED ed)");
        //    }
        //
    }



    public byte[] geraDespesa_Emissao(DespesaED ed) throws Excecoes {

        String sql = null;
        byte[] b = null;

        sql = "select * from Despesas, pessoas, naturezas_operacoes, tipos_documentos where Despesas.oid_Despesa_vincula is null and "
                + " Despesas.oid_pessoa = pessoas.oid_pessoa and " + " Despesas.oid_natureza_operacao = naturezas_operacoes.oid_natureza_operacao and"
                + " Despesas.oid_tipo_documento = tipos_documentos.oid_tipo_documento ";

        if (String.valueOf(ed.getOid_Pessoa()) != null && !String.valueOf(ed.getOid_Pessoa()).equals("") && !String.valueOf(ed.getOid_Pessoa()).equals("null")) {
            sql += " and Despesas.Oid_Pessoa = '" + ed.getOid_Pessoa() + "'";
        }
        if (String.valueOf(ed.getDt_Inicial()) != null && !String.valueOf(ed.getDt_Inicial()).equals("") && !String.valueOf(ed.getDt_Inicial()).equals("null")) {
            sql += " and Despesas.dt_Emissao >= '" + ed.getDt_Inicial() + "'";
        }
        if (String.valueOf(ed.getDt_Final()) != null && !String.valueOf(ed.getDt_Final()).equals("") && !String.valueOf(ed.getDt_Final()).equals("null")) {
            sql += " and Despesas.dt_Emissao <= '" + ed.getDt_Final() + "'";
        }

        sql += " order by Despesas.dt_Emissao, Despesas.nr_documento, Despesas.nr_parcela";

        DespesaED edVolta = new DespesaED();

        try {

            ResultSet res = null;
            res = this.executasql.executarConsulta(sql.toString());

            DespesaRL conRL = new DespesaRL();
            b = conRL.geraDespesa_Emissao(res);

        }

        catch (Excecoes e) {
            throw e;
        } catch (Exception exc) {
            Excecoes exce = new Excecoes();
            exce.setExc(exc);
            exce.setMensagem("Erro no método listar");
            exce.setClasse(this.getClass().getName());
            exce.setMetodo("geraRelatorio(DespesaED ed)");
        }
        return b;
    }


}
