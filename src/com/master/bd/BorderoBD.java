package com.master.bd;

import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

import com.master.ed.BorderoED;
import com.master.rl.BorderoRL;
import com.master.root.FormataDataBean;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;
import com.master.util.Utilitaria;

public class BorderoBD  {

    private ExecutaSQL executasql;

    Utilitaria util = new Utilitaria(executasql);


    DecimalFormat dec = new DecimalFormat("000000000000");
    FormataDataBean dataFormatada = new FormataDataBean();
    Parametro_FixoED parametro_FixoED = new Parametro_FixoED();

    public BorderoBD(ExecutaSQL sql) {
      this.executasql = sql;
      util = new Utilitaria(this.executasql);
    }

    public BorderoED inclui(BorderoED ed) throws Excecoes {

        String sql = null;
        long Nr_Bordero = 0;
        
        try {  

            ResultSet rs = executasql.executarConsulta("select max(OID_Bordero) as result from Borderos");

            //pega proximo valor da chave
            while (rs.next()) Nr_Bordero = rs.getInt("result");
            	Nr_Bordero = Nr_Bordero+1;

            ed.setOid_Bordero(new Integer(String.valueOf(Nr_Bordero)));
            
            boolean DM_Chave_Unica = true;
            while (DM_Chave_Unica) { 
                sql = " SELECT Borderos.OID_Bordero " +
                      " FROM Borderos " +
                      " WHERE Borderos.oid_Bordero = " + ed.getOid_Bordero();
                ResultSet res = this.executasql.executarConsulta(sql);

                DM_Chave_Unica = false;
                while (res.next()) {
                    DM_Chave_Unica = true;
                    Nr_Bordero = Nr_Bordero + 1;
                    ed.setOid_Bordero(new Integer(String.valueOf(Nr_Bordero)));
                }
            }

            sql = " INSERT INTO Borderos (" +
                  "      OID_Bordero" +
                  "     ,OID_Carteira" +
                  "     ,NR_BORDERO" +
                  "     ,DT_EMISSAO" +
                  "     ,DM_BORDERO" +
                  "     ,VL_BORDERO" +
                  "     ,DT_Operacao)" +
                  " VALUES (" +
                      ed.getOid_Bordero() + ",";
            sql += ed.getOid_Carteira() + ",";
            sql += Nr_Bordero + ",";
            sql += "'" + ed.getDt_Emissao() + "',";
            sql += "'" + ed.getDM_Bordero() + "',";
            sql += ed.getVl_Bordero() + ",";
            sql += "'" + ed.getDt_Operacao() + "')";

            executasql.executarUpdate(sql);
            return ed;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "inclui()");
        }
    }

    public void altera(BorderoED ed) throws Excecoes {

        String sql = null;
        try {

            sql = " UPDATE Borderos SET ";
            sql +="     Vl_Bordero = " + ed.getVl_Bordero();
            sql +=" WHERE oid_Bordero = " + ed.getOid_Bordero();

            executasql.executarUpdate(sql);
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "altera()");
        }
    }

    public void deleta(BorderoED ed) throws Excecoes {

        String sql = null;
        try {
            sql = " DELETE FROM Borderos" +
                  " WHERE oid_Bordero = "+ed.getOid_Bordero();
            executasql.executarUpdate(sql);
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "deleta()");
        }
    }

    public ArrayList lista(BorderoED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();

        try {
            sql = " SELECT Borderos.OID_Bordero" +
                  "     ,Pessoas_Bancos.nm_razao_Social as NM_Razao_Banco" +
                  "     ,Carteiras.oid_Carteira" +
                  "     ,Carteiras.cd_Carteira" +
                  "     ,Contas_Correntes.NR_conta_corrente" +
                  "     ,Borderos.nr_Bordero" +
                  "     ,Borderos.DM_Bordero" +
                  "     ,Borderos.dt_emissao" +
                  "     ,Borderos.vl_bordero" +
                  "     ,Borderos.dt_operacao " +
                  " FROM Borderos" +
                  "     ,Pessoas Pessoas_Bancos" +
                  "     ,Carteiras" +
                  "     ,Contas_Correntes " +
                  " WHERE Borderos.oid_Carteira = Carteiras.oid_Carteira " +
                  "   AND carteiras.oid_conta_corrente = contas_correntes.oid_conta_corrente " +
                  "   AND contas_correntes.oid_pessoa = pessoas_bancos.oid_pessoa ";

            if (ed.getOid_Unidade() != null && ed.getOid_Unidade().longValue() > 0)
                sql += " AND Borderos.oid_Unidade = " + ed.getOid_Unidade();
            if (ed.getOid_Bordero() != null && ed.getOid_Bordero().intValue() > 0)
                sql += " AND Borderos.oid_Bordero = " + ed.getOid_Bordero();
            if (ed.getOid_Carteira() != null && ed.getOid_Carteira().intValue() > 0)
                sql += " AND Borderos.Oid_Carteira = " + ed.getOid_Carteira();
            if (ed.getNr_Bordero() != null && ed.getNr_Bordero().intValue() > 0)
                sql += " AND Borderos.NR_Bordero = " + ed.getNr_Bordero();
            if (util.doValida(ed.getDT_Emissao_Inicial()))
                sql += " AND Borderos.dt_emissao >= '" + ed.getDT_Emissao_Inicial() + "'";
              if (util.doValida(ed.getDT_Emissao_Final()))
                  sql += " AND Borderos.dt_emissao <= '" + ed.getDT_Emissao_Final() + "'";

            sql += " ORDER BY Borderos.dt_emissao, Borderos.NR_Bordero ";

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next()) {

                BorderoED edVolta = new BorderoED();

                edVolta.setDM_Bordero(res.getString("DM_Bordero"));

                edVolta.setOid_Bordero(new Integer(res.getInt("oid_Bordero")));
                edVolta.setNr_Bordero(new Integer(res.getInt("nr_Bordero")));
                if ("2".equals(res.getString("DM_Bordero"))) {
                  edVolta.setNM_Tipo_Bordero("Liquidação");
                }
                if ("E".equals(res.getString("DM_Bordero"))) {
                  edVolta.setNM_Tipo_Bordero("EDI Banco");
                }


                edVolta.setVl_Bordero(new Double(res.getDouble("vl_Bordero")));

                edVolta.setCD_Conta_Corrente(res.getString("nr_Conta_Corrente"));
                edVolta.setNM_Banco(res.getString("NM_Razao_Banco"));
                edVolta.setOid_Carteira(new Integer(res.getInt("oid_Carteira")));
                edVolta.setCd_Carteira(res.getString("cd_Carteira"));

                dataFormatada.setDT_FormataData(res.getString("dt_emissao"));
                edVolta.setDt_Emissao(dataFormatada.getDT_FormataData());
                dataFormatada.setDT_FormataData(res.getString("dt_operacao"));
                edVolta.setDt_Operacao(dataFormatada.getDT_FormataData());

                edVolta = this.totaliza(edVolta);
                if ("E".equals(res.getString("DM_Bordero")) && edVolta.getVL_Recebimento()==0){
                	this.deleta(edVolta);
                }else {
                	list.add(edVolta);
                }
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "lista()");
        }
        return list;
    }

    public BorderoED getByRecord(BorderoED ed) throws Excecoes {

        try {
            Iterator it = this.lista(ed).iterator();
            if (it.hasNext())
                return (BorderoED) it.next();
            else return new BorderoED();
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "getByRecord()");
        }
    }


    public BorderoED totaliza(BorderoED ed) throws Excecoes {

      String sql = null;
      double vl_movimento = 0;
      double vl_saldo = 0;
      double vl_duplicata = 0;
      double vl_recebimento = 0;
      double vl_juros = 0;
      double vl_desconto = 0;
      double vl_tarifa = 0;
      double vl_reembolso = 0;
      double vl_juros_reembolso = 0;
      double vl_registro_desconto = 0;
      double vl_protesto = 0;
      double vl_cartorio = 0;
      double vl_var_cambial_ativa = 0;
      double vl_var_cambial_passiva = 0;
      double vl_outros = 0;


      sql = " SELECT " +
            "      movimentos_duplicatas.VL_Credito" +
            "     ,movimentos_duplicatas.VL_Debito" +
            "     ,tipos_instrucoes.cd_valor" +
            "     ,Duplicatas.vl_duplicata" +
            "     ,Duplicatas.vl_saldo " +
            " FROM Duplicatas" +
            "     ,Movimentos_Duplicatas" +
            "     ,tipos_instrucoes" +
            " WHERE Movimentos_Duplicatas.oid_duplicata = duplicatas.oid_duplicata " +
            "   AND Movimentos_Duplicatas.oid_tipo_instrucao = tipos_instrucoes.oid_tipo_instrucao " +
            " AND Movimentos_Duplicatas.oid_Bordero = '" + ed.getOid_Bordero() + "'";

        // System.out.println(sql);
          try {
              ResultSet res = this.executasql.executarConsulta(sql.toString());
              while (res.next()) {

                vl_movimento = res.getDouble("VL_Credito") + res.getDouble("VL_Debito");

                if (res.getString("cd_valor")!=null){
                  if (res.getString ("cd_valor").equals ("23"))
                    vl_juros = vl_juros + vl_movimento;
                  if (res.getString ("cd_valor").equals ("24"))
                    vl_tarifa = vl_tarifa + vl_movimento;
                  if (res.getString ("cd_valor").equals ("25"))
                    vl_recebimento = vl_recebimento + vl_movimento;
                  if (res.getString ("cd_valor").equals ("26"))
                    vl_desconto = vl_desconto + vl_movimento;
                  if (res.getString ("cd_valor").equals ("27"))
                    vl_reembolso = vl_reembolso + vl_movimento;
                  if (res.getString ("cd_valor").equals ("28"))
                    vl_juros_reembolso = vl_juros_reembolso + vl_movimento;
                  if (res.getString ("cd_valor").equals ("29"))
                    vl_registro_desconto = vl_registro_desconto + vl_movimento;
                  if (res.getString ("cd_valor").equals ("30"))
                    vl_protesto = vl_protesto + vl_movimento;
                  if (res.getString ("cd_valor").equals ("31"))
                    vl_cartorio = vl_cartorio + vl_movimento;
                  if (res.getString ("cd_valor").equals ("32"))
                    vl_var_cambial_ativa = vl_var_cambial_ativa + vl_movimento;
                  if (res.getString ("cd_valor").equals ("33"))
                    vl_var_cambial_passiva = vl_var_cambial_passiva + vl_movimento;
                }
                  // System.out.println("CD Valor->" + res.getString("cd_valor") + " - vl_recebimento->>" + vl_recebimento);

              }



              ed.setVL_Recebimento(vl_recebimento);

      } catch (Excecoes e) {
          throw e;
      } catch (Exception exc) {
          throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "geraRelMovimentoBordero()");
      }
      return ed;
    }

    public byte[] geraRelMovimentoBordero(BorderoED ed) throws Excecoes {

        String sql = null;
        byte[] b = null;

        sql = " SELECT Duplicatas.OID_Duplicata" +
              "     ,Pessoas.oid_pessoa" +
              "     ,Pessoas.nr_cnpj_cpf" +
              "     ,Pessoas.nm_razao_Social" +
              "     ,Pessoas_Bancos.nm_razao_Social as NM_Razao_Banco" +
              "     ,Carteiras.oid_Carteira" +
              "     ,Carteiras.cd_Carteira" +
              "     ,movimentos_duplicatas.DT_Movimento" +
              "     ,movimentos_duplicatas.DM_Principal" +
              "     ,movimentos_duplicatas.VL_Credito" +
              "     ,movimentos_duplicatas.VL_Debito" +
              "     ,movimentos_duplicatas.oid_movimento_duplicata" +
              "     ,Contas_Correntes.NR_conta_corrente" +
              "     ,moedas.cd_moeda" +
              "     ,Tipos_Documentos.oid_tipo_documento" +
              "     ,Tipos_Documentos.cd_tipo_documento" +
              "     ,Tipos_Documentos.nm_tipo_documento" +
              "     ,Tipos_Instrucoes.NM_Tipo_Instrucao" +
              "     ,tipos_instrucoes.cd_valor" +
              "     ,Duplicatas.nr_Duplicata" +
              "     ,Duplicatas.nr_parcela" +
              "     ,Duplicatas.nr_documento" +
              "     ,Duplicatas.nr_bancario" +
              "     ,Duplicatas.dt_vencimento" +
              "     ,Duplicatas.vl_taxa_cobranca" +
              "     ,Duplicatas.vl_saldo" +
              "     ,Duplicatas.dt_emissao" +
              "     ,Duplicatas.vl_duplicata" +
              "     ,Duplicatas.vl_saldo " +
              " FROM Duplicatas" +
              "     ,pessoas" +
              "     ,Pessoas Pessoas_Bancos" +
              "     ,tipos_documentos" +
              "     ,Carteiras" +
              "     ,Contas_Correntes" +
              "     ,Movimentos_Duplicatas" +
              "     ,tipos_instrucoes" +
              "     ,moedas " +
              " WHERE duplicatas.oid_pessoa = pessoas.oid_pessoa " +
              "   AND Movimentos_Duplicatas.oid_Carteira = Carteiras.oid_Carteira " +
              "   AND duplicatas.oid_tipo_documento = tipos_documentos.oid_tipo_documento " +
              "   AND movimentos_Duplicatas.oid_duplicata = duplicatas.oid_duplicata " +
              "   AND Movimentos_Duplicatas.oid_tipo_instrucao = tipos_instrucoes.oid_tipo_instrucao " +
              "   AND carteiras.oid_conta_corrente = contas_correntes.oid_conta_corrente " +
              "   AND contas_correntes.oid_pessoa = pessoas_bancos.oid_pessoa " +
              "   AND contas_correntes.oid_moeda = moedas.oid_moeda ";

            if (ed.getOid_Bordero() != null && ed.getOid_Bordero().intValue() > 0)
                sql += " AND Movimentos_Duplicatas.oid_Bordero = '" + ed.getOid_Bordero() + "'";

            sql += " ORDER BY duplicatas.nr_duplicata  ";

           //System.out.println(sql);
            try {
                ResultSet res = this.executasql.executarConsulta(sql.toString());
                b = new BorderoRL().geraRelMovimentoBordero(res, ed);

        } catch (Excecoes e) {
            throw e;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "geraRelMovimentoBordero()");
        }
        return b;
    }
}
