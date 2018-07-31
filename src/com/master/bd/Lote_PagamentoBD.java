package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.CompromissoED;
import com.master.ed.Lote_PagamentoED;
import com.master.ed.Lote_PagamentoPesquisaED;
import com.master.ed.Movimento_CompromissoED;
import com.master.ed.Movimento_Conta_CorrenteED;
import com.master.ed.RelatorioED;
import com.master.rl.JasperRL;
import com.master.rl.Lote_PagamentoRL;
import com.master.root.FormataDataBean;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;
import com.master.util.Utilitaria;
import com.master.ed.Lote_CompromissoED;

public class Lote_PagamentoBD  {

  private ExecutaSQL executasql;
  Utilitaria util = new Utilitaria(executasql);
    FormataDataBean DataFormatada = new FormataDataBean();

    public Lote_PagamentoBD(ExecutaSQL sql) {
      this.executasql = sql;
      util = new Utilitaria(this.executasql);
    }

    public Lote_PagamentoED inclui(Lote_PagamentoED ed) throws Excecoes {

        String sql = null;
        long Nr_Lote_Pagamento = 0;
        long Oid_Lote_Pagamento = 0;

        // .println("Lote_PagamentoBD 1" );
        try {

            sql = " SELECT NR_Proximo_Lote_Pagamento ";
            sql += " FROM Parametros_Filiais ";
            sql += " WHERE Parametros_Filiais.OID_Unidade = " + ed.getOid_Unidade();

        // .println("Lote_PagamentoBD 1->>"+sql );

            ResultSet rs = this.executasql.executarConsulta(sql);
            while (rs.next()) {
                Nr_Lote_Pagamento = rs.getInt("NR_Proximo_Lote_Pagamento");
                Oid_Lote_Pagamento = rs.getInt("NR_Proximo_Lote_Pagamento");
            }

        // .println("Lote_PagamentoBD 2->>"+Oid_Lote_Pagamento );

            sql = " UPDATE Parametros_Filiais SET NR_Proximo_Lote_Pagamento= " + (Nr_Lote_Pagamento + 1);
            sql += " WHERE Parametros_Filiais.OID_Unidade = " + ed.getOid_Unidade();
        // .println("Lote_PagamentoBD 1->>"+sql );

            executasql.executarUpdate(sql);

        // .println("Lote_PagamentoBD 3->>"+Oid_Lote_Pagamento );

            ed.setOid_Lote_Pagamento(new Integer(Long.toString(Oid_Lote_Pagamento)));
            sql = " INSERT INTO Lotes_Pagamentos(" +
                  "     OID_Lote_Pagamento" +
                  "     ,NR_DOCUMENTO" +
                  "     ,DT_EMISSAO" +
                  "     ,DT_Programada" +
                  "     ,TX_OBSERVACAO" +
                  "     ,DT_STAMP" +
                  "     ,USUARIO_STAMP" +
                  "     ,OID_Fornecedor" +
                  "     ,NM_Favorecido" +
                  "     ,DM_Copia_Cheque" +
                  "     ,DM_Imprimir" +
                  "     ,DM_STAMP" +
                  "     ,OID_CONTA_Corrente" +
                  "     ,OID_CONTA_Corrente_Destino" +
                  "     ,OID_Unidade" +
                  "     ,OID_Compromisso" +
                  "     ,OID_Tipo_Documento" +
                  "     ,VL_Lote_Pagamento" +
                  "     ,DM_Situacao)" +
                  " VALUES ('"
                    + Oid_Lote_Pagamento + "',";
            sql += ed.getNr_Documento() == null ? "null," : "'" + ed.getNr_Documento() + "',";
            sql += "'" + ed.getDt_Emissao() + "',";
            sql += "'" + ed.getDT_Programada() + "',";
            sql += ed.getTx_Observacao() == null ? "null," : "'" + ed.getTx_Observacao() + "',";
            sql += "'" + ed.getDt_stamp() + "',";
            sql += "'" + ed.getUsuario_Stamp() + "',";
            sql += "'" + ed.getOID_Fornecedor() + "',";
            sql += "'" + ed.getNM_Favorecido() + "',";
            sql += "'" + ed.getDM_Copia_Cheque() + "',";
            sql += "'" + ed.getDM_Imprimir() + "',";
            sql += "'" + ed.getDm_Stamp() + "',";
            sql += "'" + ed.getOID_Conta_Corrente() + "',";
            sql += "'" + ed.getOID_Conta_Corrente_Destino() + "',";
            sql += ed.getOid_Unidade() + ",";
            sql += ed.getOID_Compromisso() + ",";
            sql += ed.getOID_Tipo_Documento() + ",";
            sql += ed.getVl_Lote_Pagamento() + ",";
            sql += "'I'" + ")";

        // .println("Lote_PagamentoBD 4->>"+sql );

            executasql.executarUpdate(sql);

        // .println("Lote_PagamentoBD 99->>" );

            return ed;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "inclui()");
        }
    }

    public Lote_PagamentoED getByRecord(Lote_PagamentoED ed) throws Excecoes {

        Lote_PagamentoED edVolta = new Lote_PagamentoED();
        try {
            String sql = " SELECT Lotes_Pagamentos.DM_Situacao as DM_Situacao_Lote, * " +
                         "       ,Unidades.oid_unidade " +
                         "       ,Unidades.cd_unidade " +
                         "       ,Tipos_Documentos.oid_Tipo_Documento " +
                         "       ,Tipos_Documentos.CD_Tipo_Documento " +
                         "       ,Tipos_Documentos.NM_Tipo_Documento " +
                         "       ,Contas_Correntes.oid_Conta_Corrente " +
                         "       ,Contas_Correntes.CD_Conta_Corrente " +
                         "       ,Pessoa_Conta_Corrente.NM_Razao_Social" +
                         "       ,Pessoa_Unidade.NM_Fantasia as NM_Fantasia_Unidade " +
                         " FROM Lotes_Pagamentos, " +
                         "      Unidades, " +
                         "      Pessoas Pessoa_Unidade, " +
                         "      Tipos_Documentos, " +
                         "      Contas_Correntes, " +
                         "      Pessoas Pessoa_Conta_Corrente " +
                         " WHERE Lotes_Pagamentos.oid_conta_Corrente = contas_correntes.oid_conta_Corrente "+
                         "   and Lotes_Pagamentos.oid_unidade = unidades.oid_unidade " +
                         "   and Lotes_Pagamentos.oid_tipo_documento = tipos_documentos.oid_tipo_documento "+
                         "   and Unidades.oid_Pessoa = Pessoa_Unidade.oid_Pessoa " +
                         "   and contas_correntes.oid_pessoa = Pessoa_Conta_Corrente.oid_pessoa ";

            if (ed.getOid_Lote_Pagamento() != null && ed.getOid_Lote_Pagamento().intValue() > 0)
                sql += " and Lotes_Pagamentos.oid_Lote_Pagamento = " + ed.getOid_Lote_Pagamento();
            if (ed.getNr_Lote_Pagamento() != null && ed.getNr_Lote_Pagamento().intValue() > 0)
                sql += " and Lotes_Pagamentos.OID_Lote_Pagamento = " + ed.getNr_Lote_Pagamento();

            if (util.doValida(ed.getNr_Documento()))
            {
                sql += " and Lotes_Pagamentos.dm_situacao <> 'C' " +
                       " and Lotes_Pagamentos.Nr_Documento = '" + ed.getNr_Documento() + "'";
            }
            if (util.doValida(ed.getOID_Conta_Corrente()))
                sql += " and Lotes_Pagamentos.OID_Conta_Corrente = '" + ed.getOID_Conta_Corrente() + "'";

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next()) {

                edVolta = new Lote_PagamentoED();
                edVolta.setOid_Lote_Pagamento(new Integer(res.getInt("oid_Lote_Pagamento")));

                edVolta.setOID_Fornecedor(res.getString("OID_Fornecedor"));
                edVolta.setOID_Compromisso(res.getLong("OID_Compromisso"));
                edVolta.setNm_Razao_Social(res.getString("NM_Razao_Social"));
                edVolta.setCD_Tipo_Documento(res.getString("CD_Tipo_Documento"));
                edVolta.setNM_Tipo_Documento(res.getString("NM_Tipo_Documento"));
                edVolta.setCD_Conta_Corrente(res.getString("CD_Conta_Corrente"));
                edVolta.setOID_Conta_Corrente(res.getString("oid_Conta_Corrente"));
                edVolta.setOid_Unidade(new Long(res.getInt("oid_unidade")));
                edVolta.setOID_Tipo_Documento(new Integer(res.getInt("oid_Tipo_Documento")));
                edVolta.setCd_Unidade(res.getString("cd_unidade"));
                edVolta.setNm_Fantasia(res.getString("NM_Fantasia_Unidade"));
                edVolta.setNM_Favorecido(res.getString("NM_Favorecido"));
                edVolta.setDM_Copia_Cheque(res.getString("DM_Copia_Cheque"));
                edVolta.setDM_Imprimir(res.getString("DM_Imprimir"));
                edVolta.setDM_Situacao(res.getString("DM_Situacao_Lote"));

                edVolta.setNr_Lote_Pagamento(new Integer(res.getInt("oid_Lote_Pagamento")));

                DataFormatada.setDT_FormataData(res.getString("DT_Programada"));
                edVolta.setDT_Programada(DataFormatada.getDT_FormataData());

                DataFormatada.setDT_FormataData(res.getString("DT_Apresentacao1"));
                edVolta.setDT_Apresentacao1(DataFormatada.getDT_FormataData());

                DataFormatada.setDT_FormataData(res.getString("DT_Apresentacao2"));
                edVolta.setDT_Apresentacao2(DataFormatada.getDT_FormataData());

                DataFormatada.setDT_FormataData(res.getString("DT_Apresentacao3"));
                edVolta.setDT_Apresentacao3(DataFormatada.getDT_FormataData());

                DataFormatada.setDT_FormataData(res.getString("DT_Compensacao"));
                edVolta.setDT_Compensacao(DataFormatada.getDT_FormataData());
                DataFormatada.setDT_FormataData(res.getString("dt_emissao"));
                edVolta.setDt_Emissao(DataFormatada.getDT_FormataData());
                edVolta.setNr_Documento(res.getString("nr_documento"));

                if (edVolta.getNr_Documento() == null) {
                    edVolta.setNr_Documento("");
                }

                String tx_Obs = res.getString("tx_observacao");
                if (tx_Obs != null)
                    edVolta.setTx_Observacao(tx_Obs);

                edVolta.setVl_Lote_Pagamento(new Double(res.getDouble("vl_Lote_Pagamento")));

                edVolta.setCD_Motivo_Retorno("**");

                edVolta.setOid_Motivo_Retorno(res.getInt("Oid_Motivo_Retorno"));
                if (edVolta.getOid_Motivo_Retorno()>0) {
                  sql=" SELECT CD_Motivo_Devolucao, NM_Motivo_Devolucao " +
                      " FROM   Motivos_Devolucoes " +
                      " WHERE  Motivos_Devolucoes.oid_Motivo_Devolucao = " + edVolta.getOid_Motivo_Retorno();
                  ResultSet res2 = this.executasql.executarConsulta (sql);
                  while (res2.next ()) {
                    edVolta.setCD_Motivo_Retorno(res2.getString("CD_Motivo_Devolucao"));
                    edVolta.setNM_Motivo_Retorno(res2.getString("NM_Motivo_Devolucao"));
                  }
                }


                edVolta.setOID_Conta_Corrente_Destino("");
                edVolta.setCD_Conta_Corrente_Destino(" ");
                edVolta.setNm_Razao_Social_Destino(" ");
                if (res.getString("oid_Conta_Corrente_Destino") !=null && !res.getString("oid_Conta_Corrente_Destino").equals("null")) {
                  sql = " SELECT  Contas_Correntes.oid_Conta_Corrente " +
                         "       ,Contas_Correntes.CD_Conta_Corrente " +
                         "       ,Pessoa_Conta_Corrente.NM_Razao_Social" +
                         " FROM Contas_Correntes, " +
                         "      Pessoas Pessoa_Conta_Corrente " +
                         " WHERE contas_correntes.oid_pessoa = Pessoa_Conta_Corrente.oid_pessoa " +
                         " AND   contas_correntes.oid_conta_Corrente ='" + res.getString("oid_Conta_Corrente_Destino") + "'";
                     // .println(sql);
                  ResultSet res2 = this.executasql.executarConsulta (sql);
                  while (res2.next ()) {
                    edVolta.setOID_Conta_Corrente_Destino (res.getString("oid_Conta_Corrente"));
                    edVolta.setCD_Conta_Corrente_Destino (res2.getString("CD_Conta_Corrente"));
                    edVolta.setNm_Razao_Social_Destino (res2.getString("NM_Razao_Social"));
                  }
                }

            }
            return edVolta;

        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "getByRecord()");
        }
    }

    public void altera(Lote_PagamentoED ed) throws Excecoes {

        String sql = null;

        try {
            sql = " UPDATE Lotes_Pagamentos SET ";

            if (ed.getNr_Documento() == null)
                sql += "nr_documento = null,";
            else sql += " nr_documento = '" + ed.getNr_Documento() + "',";

            if (ed.getDt_Emissao() == null)
                sql += "DT_Emissao = null,";
            else sql += "DT_Emissao = '" + ed.getDt_Emissao() + "',";

            if (ed.getDT_Programada() == null || ed.getDT_Programada().equals(""))
                sql += "DT_Programada = null,";
            else sql += "DT_Programada = '" + ed.getDT_Programada() + "',";

            if (ed.getTx_Observacao() == null)
                sql += "tx_observacao = null,";
            else sql += "tx_observacao = '" + ed.getTx_Observacao() + "',";

            if (ed.getDT_Compensacao() == null || ed.getDT_Compensacao().equals(""))
                sql += "DT_Compensacao = null,";
            else sql += "DT_Compensacao = '" + ed.getDT_Compensacao() + "',";

            if (ed.getDt_stamp() == null)
                sql += "DT_Stamp = null,";
            else sql += "DT_Stamp = '" + ed.getDt_stamp() + "',";

            sql += " USUARIO_STAMP = '" + ed.getUsuario_Stamp() + "',";
            sql += " DM_STAMP = '" + ed.getDm_Stamp() + "',";
            sql += " NM_Favorecido = '" + ed.getNM_Favorecido() + "',";
            sql += " DM_Copia_Cheque = '" + ed.getDM_Copia_Cheque() + "',";
            sql += " DM_Imprimir = '" + ed.getDM_Imprimir() + "'";
            sql += " WHERE oid_Lote_Pagamento = " + ed.getOid_Lote_Pagamento();

            executasql.executarUpdate(sql);

        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "altera()");
        }
    }

    public int altera_Compensacao(Lote_PagamentoED ed) throws Excecoes {

        String sql = null;
        int i = 0;
        try {

            sql = " SELECT * FROM Lotes_Pagamentos  " +
                  " WHERE Lotes_Pagamentos.oid_Lote_Pagamento = " + ed.getOid_Lote_Pagamento() +
                  "   AND DT_Compensacao is null ";

            // .println("########### LOTE_PAGAMENTO_ALTERA_COMP = "+ sql);
            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next()) {
                i = this.compensacao(ed, res,"");
            }
            return i;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "altera_Compensacao()");
        }
    }

    public int devolucao(Lote_PagamentoED ed) throws Excecoes {

        String sql = null;
        int i = 0;
        try {

            sql = " SELECT * FROM Lotes_Pagamentos  " +
                  " WHERE Lotes_Pagamentos.oid_Lote_Pagamento = " + ed.getOid_Lote_Pagamento() +
                  "   AND DT_Compensacao is null ";

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next()) {
                i = this.devolucao(ed, res);
            }
            return i;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "devolucao()");
        }
    }

    public int estorna_Compensacao(Lote_PagamentoED ed) throws Excecoes {

        String sql = null;
        String nr_Documento="";
        int i = 0;
        try {

            sql = " SELECT Movimentos_Contas_Correntes.oid_Movimento_Conta_Corrente, " +
                  "        Movimentos_Contas_Correntes.NR_Documento, " +
                  "        Lotes_Pagamentos.oid_Lote_Pagamento " +
                  " FROM  Lotes_Pagamentos, Movimentos_Contas_Correntes  " +
                  " WHERE Lotes_Pagamentos.oid_Lote_Pagamento = Movimentos_Contas_Correntes.oid_Lote_Pagamento  " +
                  " AND   Lotes_Pagamentos.oid_Lote_Pagamento = " + ed.getOid_Lote_Pagamento();

            ResultSet res = this.executasql.executarConsulta(sql);
            if (res.next()) {
              nr_Documento=res.getString("NR_Documento")+"-E";

              sql = " UPDATE Movimentos_Contas_Correntes " +
                    " SET    dm_tipo_lancamento = 'G' " +
                    "        ,dm_debito_credito = '' " +
                    "        ,nr_documento = '"+nr_Documento+"' " +
                    "        ,vl_lancamento = 0 " +
                    "        ,nm_complemento_historico = 'Compensacao Estornada' " +
                    " WHERE  Movimentos_Contas_Correntes.oid_Movimento_Conta_Corrente = " + res.getLong ("oid_Movimento_Conta_Corrente");
                // .println(sql);
                executasql.executarUpdate (sql);

              sql = " UPDATE Lotes_Pagamentos " +
                    " SET    DT_Compensacao = null " +
                    " WHERE  Lotes_Pagamentos.oid_Lote_Pagamento = " + res.getLong ("Oid_Lote_Pagamento");
                // .println(sql);

                executasql.executarUpdate (sql);
              i++;
            }
            return i;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "devolucao()");
        }
    }


    public int compensacao(Lote_PagamentoED ed, ResultSet res, String dm_Tipo_Compensacao) throws Excecoes {

      // .println(" compensacao 1");

        String sql = null;
        String nm_Conta_Origem="", nm_Conta_Destino="" , dm_Contabilizacao="";
        String dm_Tipo_Conta_Origem="", dm_Tipo_Conta_Destino="";
        int oid_Conta_Origem=0, oid_Conta_Destino=0;
        String dm_Transferencia="";
        try {

            sql =" SELECT Contas_Correntes.cd_Conta_Corrente, Contas_Correntes.oid_Conta, Contas_Correntes.dm_Tipo_Conta_Corrente, Contas_Correntes.dm_Contabilizacao" +
                 " FROM  Contas_Correntes " +
                 " WHERE Contas_Correntes.oid_Conta_Corrente ='" + res.getString("OID_Conta_Corrente") + "'";
            //// .println("############# CONTAS_CORRENTES LOTE PGTO = "+sql);
            ResultSet res2 = this.executasql.executarConsulta(sql);
            if (res2.next()){
              nm_Conta_Origem=res2.getString("cd_Conta_Corrente");
              oid_Conta_Origem=res2.getInt("oid_Conta");
              dm_Tipo_Conta_Origem=res2.getString("dm_Tipo_Conta_Corrente");
              dm_Contabilizacao=res2.getString("dm_Contabilizacao");
              //// .println("############# DM_CONTABILIZACAO = "+dm_Contabilizacao);
            }

            sql =" SELECT Contas_Correntes.cd_Conta_Corrente, Contas_Correntes.oid_Conta, Contas_Correntes.dm_Tipo_Conta_Corrente " +
                 " FROM  Contas_Correntes " +
                 " WHERE Contas_Correntes.oid_Conta_Corrente ='" + res.getString("OID_Conta_Corrente_Destino") + "'";
            //// .println("############# CONTAS_CORRENTES LOTE PGTO 222= "+sql);
            res2 = this.executasql.executarConsulta(sql);
            if (res2.next()){
              nm_Conta_Destino=res2.getString("cd_Conta_Corrente");
              oid_Conta_Destino=res2.getInt("oid_Conta");
              dm_Tipo_Conta_Destino=res2.getString("dm_Tipo_Conta_Corrente");
              dm_Transferencia="S";
            }


            // .println(" oid_COnta_Origem="+oid_Conta_Origem);
            // .println(" oid_Conta_Destino="+oid_Conta_Destino);
            // .println(" dm_Transferencia="+dm_Transferencia);

            Movimento_Conta_CorrenteED edMovConta = new Movimento_Conta_CorrenteED();
            edMovConta.setDT_Movimento_Conta_Corrente(ed.getDT_Compensacao());
            edMovConta.setOid_Lote_Pagamento(res.getLong("Oid_Lote_Pagamento"));
            edMovConta.setOid_Conta_Corrente(res.getString("OID_Conta_Corrente"));
            edMovConta.setDM_Debito_Credito("D");
            edMovConta.setOid_Conta (oid_Conta_Origem);
            edMovConta.setNR_Documento(res.getString("NR_Documento"));
            if (edMovConta.getNR_Documento() == null || edMovConta.getNR_Documento().length() <= 0) {
                edMovConta.setNR_Documento(ed.getNr_Documento());
            }
            edMovConta.setDM_Tipo_Lancamento("G");
            edMovConta.setOID_Tipo_Documento(new Integer(res.getString("oid_Tipo_Documento")));
            edMovConta.setOid_Historico(new Integer(Parametro_FixoED.getInstancia().getOID_Historico_Compensacao()));
            edMovConta.setNM_Complemento_Historico("Lote Pgto: " + res.getString("OID_LOTE_PAGAMENTO"));

            if ("S".equals(dm_Transferencia)){
              edMovConta.setNM_Complemento_Historico ("Lote Pgto: " + res.getString ("OID_LOTE_PAGAMENTO") + " / Transf:" + nm_Conta_Origem + "->" + nm_Conta_Destino);
              edMovConta.setOid_Historico (new Integer (Parametro_FixoED.getInstancia ().getOID_Historico_Transferencia_Banco()));
            }

            edMovConta.setVL_Lancamento(new Double(res.getString("VL_Lote_Pagamento")));

            edMovConta.setDM_Debito_Credito ("D");
            if ("U".equals (dm_Tipo_Conta_Origem)) {
              edMovConta.setDM_Debito_Credito ("C"); //caixa
              edMovConta.setOid_Historico (new Integer (Parametro_FixoED.getInstancia ().getOID_Historico_Transferencia_Caixa ()));
            }


            // .println(" compensacao 99");

            edMovConta = new Movimento_Conta_CorrenteBD(executasql).inclui(edMovConta);

            // .println(" dm_Tipo_Compensacao="+dm_Tipo_Compensacao);

            if ("IMPRESSAO".equals(dm_Tipo_Compensacao) &&
                "S".equals(dm_Transferencia)){
              //transferencia

              // .println(" transferencia 1");

                  edMovConta = new Movimento_Conta_CorrenteED ();
                  edMovConta.setDT_Movimento_Conta_Corrente (ed.getDT_Compensacao ());
                  edMovConta.setOid_Lote_Pagamento (res.getLong ("Oid_Lote_Pagamento"));

                  edMovConta.setOid_Conta_Corrente (res.getString ("oid_Conta_Corrente_Destino"));
                  edMovConta.setOid_Conta (oid_Conta_Destino);


                  edMovConta.setNR_Documento (res.getString ("NR_Documento"));
                  if (edMovConta.getNR_Documento () == null || edMovConta.getNR_Documento ().length () <= 0) {
                    edMovConta.setNR_Documento (ed.getNr_Documento ());
                  }
                  edMovConta.setNM_Complemento_Historico ("Lote Pgto: " + res.getString ("OID_LOTE_PAGAMENTO")+ " / Transf:"+nm_Conta_Origem + "->" + nm_Conta_Destino);
                  edMovConta.setDM_Tipo_Lancamento ("G");
                  edMovConta.setOID_Tipo_Documento (new Integer (res.getString ("oid_Tipo_Documento")));
                  edMovConta.setOid_Historico (new Integer (Parametro_FixoED.getInstancia ().getOID_Historico_Transferencia_Banco()));
                  edMovConta.setVL_Lancamento (new Double (res.getString ("VL_Lote_Pagamento")));

                  edMovConta.setDM_Debito_Credito ("C");
                  if ("U__".equals (dm_Tipo_Conta_Destino)) {
                    edMovConta.setDM_Debito_Credito ("D"); //caixa
                    edMovConta.setOid_Historico (new Integer (Parametro_FixoED.getInstancia ().getOID_Historico_Transferencia_Caixa()));
                  }
              // .println(" transferencia 99");

                  edMovConta = new Movimento_Conta_CorrenteBD (executasql).inclui (edMovConta);
            }


            sql = " UPDATE Lotes_Pagamentos SET ";
            sql +=" DT_Compensacao = '" +ed.getDT_Compensacao()+ "'";
            sql +=" WHERE  Lotes_Pagamentos.oid_Lote_Pagamento = " + res.getLong("Oid_Lote_Pagamento");

            return executasql.executarUpdate(sql);

        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "compensacao()");
        }
    }

    public int devolucao(Lote_PagamentoED ed, ResultSet res) throws Excecoes {

        String sql = null;
        try {

            Movimento_Conta_CorrenteED edMovConta = new Movimento_Conta_CorrenteED();
            edMovConta.setDT_Movimento_Conta_Corrente(ed.getDT_Apresentacao());
            edMovConta.setOid_Lote_Pagamento(res.getLong("Oid_Lote_Pagamento"));

            edMovConta.setOid_Conta_Corrente(res.getString("OID_Conta_Corrente"));
            edMovConta.setOid_Conta(util.getTableIntValue("oid_Conta", "Contas_Correntes", "oid_Conta_Corrente = '" + edMovConta.getOid_Conta_Corrente() + "'"));

            edMovConta.setNR_Documento(res.getString("NR_Documento"));
            if (edMovConta.getNR_Documento() == null || edMovConta.getNR_Documento().length() <= 0) {
                edMovConta.setNR_Documento(ed.getNr_Documento());
            }
            edMovConta.setNM_Complemento_Historico("Lote Pgto: " + res.getString("OID_LOTE_PAGAMENTO"));
            edMovConta.setDM_Debito_Credito("D");
            edMovConta.setDM_Tipo_Lancamento("G");
            edMovConta.setOID_Tipo_Documento(new Integer(res.getString("oid_Tipo_Documento")));
            edMovConta.setOid_Historico(new Integer(Parametro_FixoED.getInstancia().getOID_Historico_Compensacao()));

            edMovConta.setVL_Lancamento(new Double(res.getString("VL_Lote_Pagamento")));
            edMovConta = new Movimento_Conta_CorrenteBD(executasql).inclui(edMovConta);

            edMovConta = new Movimento_Conta_CorrenteED();
            edMovConta.setDT_Movimento_Conta_Corrente(ed.getDT_Apresentacao());
            edMovConta.setOid_Lote_Pagamento(res.getLong("Oid_Lote_Pagamento"));

            edMovConta.setOid_Conta_Corrente(res.getString("OID_Conta_Corrente"));
            edMovConta.setOid_Conta(util.getTableIntValue("oid_Conta", "Contas_Correntes", "oid_Conta_Corrente = '" + edMovConta.getOid_Conta_Corrente() + "'"));

            edMovConta.setNR_Documento(res.getString("NR_Documento"));
            if (edMovConta.getNR_Documento() == null || edMovConta.getNR_Documento().length() <= 0) {
                edMovConta.setNR_Documento(ed.getNr_Documento());
            }
            edMovConta.setNM_Complemento_Historico("Devolvido Lote Pgto: " + res.getString("OID_LOTE_PAGAMENTO") + " Motivo->> " + ed.getCD_Motivo_Retorno());
            edMovConta.setDM_Debito_Credito("C");
            edMovConta.setDM_Tipo_Lancamento("G");
            edMovConta.setOID_Tipo_Documento(new Integer(res.getString("oid_Tipo_Documento")));
            edMovConta.setOid_Historico(new Integer(Parametro_FixoED.getInstancia().getOID_Historico_Devolucao_Cheque()));

            edMovConta.setVL_Lancamento(new Double(res.getString("VL_Lote_Pagamento")));
            edMovConta = new Movimento_Conta_CorrenteBD(executasql).inclui(edMovConta);


            sql = " UPDATE Lotes_Pagamentos SET ";
            sql +="  DT_Apresentacao1 = '" +ed.getDT_Apresentacao()+ "'";
            sql +=" ,oid_Motivo_Retorno = " +ed.getOid_Motivo_Retorno();
            sql +=" WHERE  Lotes_Pagamentos.oid_Lote_Pagamento = " + res.getLong("Oid_Lote_Pagamento");

            return executasql.executarUpdate(sql);

        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "devolucao()");
        }
    }

    public void deleta(Lote_PagamentoED ed) throws Excecoes {

        String sql = null;
        ResultSet res = null;
        ResultSet res2 = null;

        try {

          sql = " SELECT DM_Situacao FROM Lotes_Pagamentos WHERE DM_Situacao='I' AND oid_Lote_Pagamento = " + ed.getOid_Lote_Pagamento();
	      res2 = this.executasql.executarConsulta(sql);

	      if (res2.next()) {
            sql = " SELECT Lotes_Compromissos.oid_lote_compromisso " +
                  "       ,Lotes_Compromissos.OID_Compromisso " +
                  " FROM  Lotes_Compromissos " +
                  " WHERE Lotes_Compromissos.oid_Lote_Pagamento = " + ed.getOid_Lote_Pagamento();

            res = this.executasql.executarConsulta(sql);

            while (res.next())
            {
                sql = " DELETE FROM Lotes_Compromissos ";
                sql +=" WHERE  Lotes_Compromissos.oid_lote_compromisso = '" + res.getString("oid_lote_compromisso") + "'";
                executasql.executarUpdate(sql);

                sql = " UPDATE Compromissos SET DM_Lote_Pagamento='N' ";
                sql +=" WHERE OID_Compromisso = '" + res.getString("OID_Compromisso")+ "'";
                executasql.executarUpdate(sql);
            }

            sql = " DELETE FROM Lotes_Pagamentos " +
                  " WHERE oid_Lote_Pagamento = "+ed.getOid_Lote_Pagamento();

            	executasql.executarUpdate(sql);
	      }


        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "deleta()");
        }
    }

    public void cancela(Lote_PagamentoED ed) throws Excecoes {

        String sql = null;
        double vl_principal = 0;
        double vl_saldo_compromisso = 0;

        try {

            sql = " SELECT Lotes_Pagamentos.DT_Emissao" +
                  "     ,Lotes_Pagamentos.NR_Documento" +
                  "     ,Lotes_Pagamentos.VL_Lote_Pagamento" +
                  "     ,Lotes_Pagamentos.oid_Lote_Pagamento" +
                  "     ,Lotes_Pagamentos.oid_Tipo_Documento" +
                  "     ,Lotes_Pagamentos.oid_Conta_Corrente " +
                  " FROM Lotes_Pagamentos "+
                  " WHERE Lotes_Pagamentos.oid_Lote_Pagamento = " + ed.getOid_Lote_Pagamento();

            ResultSet res = this.executasql.executarConsulta(sql);

            while (res.next())
            {
                sql = " UPDATE Lotes_Pagamentos SET " +
                      "     DM_SITUACAO = 'C'" +
                      "     ,DT_Compensacao=null  " +
                      " WHERE Lotes_Pagamentos.oid_Lote_Pagamento = " + res.getString("oid_Lote_Pagamento");
                executasql.executarUpdate(sql);

                sql = " DELETE FROM  Movimentos_Contas_Correntes " +
                      " WHERE Movimentos_Contas_Correntes.oid_Lote_Pagamento = " + res.getString("oid_Lote_Pagamento");
                executasql.executarUpdate(sql);

                sql = " UPDATE Lotes_Compromissos SET DM_Situacao='C' ";
                sql +=" WHERE oid_Lote_Pagamento = " + res.getString("oid_Lote_Pagamento");
                executasql.executarUpdate(sql);

            }
            
            sql = " SELECT Lotes_Compromissos.OID_Compromisso" +
		            "     ,Lotes_Compromissos.VL_Pagamento" +
		            "     ,Lotes_Pagamentos.VL_Lote_Pagamento" +
		            "     ,Lotes_Pagamentos.oid_conta_corrente" +
		            "     ,Lotes_Pagamentos.nr_documento" +
		            "     ,Lotes_Pagamentos.oid_tipo_documento" +
		            "     ,Lotes_Pagamentos.DT_Emissao" +
		            "     ,Lotes_Compromissos.vl_multa_pagamento" +
		            "     ,Lotes_Compromissos.vl_desconto" +
		            "     ,Lotes_Compromissos.vl_outras_despesas" +
                    "     ,Lotes_Compromissos.dt_pagamento" +
		            "     ,Compromissos.VL_Saldo" +
		            "     ,Compromissos.VL_Compromisso" +
		            "     ,Compromissos.nr_Compromisso" +
		            "     ,Lotes_Compromissos.oid_lote_compromisso" +
		            " FROM Lotes_Compromissos" +
		            "     ,Compromissos" +
		            "     ,Lotes_Pagamentos " +
		            " WHERE Lotes_Compromissos.oid_Compromisso = Compromissos.oid_Compromisso " +
		            "   AND Lotes_Compromissos.oid_Lote_Pagamento = Lotes_Pagamentos.oid_Lote_Pagamento " +
		            "   AND Lotes_Compromissos.DM_Situacao <> 'E' " +
	                "   AND Lotes_Pagamentos.oid_Lote_Pagamento = " + ed.getOid_Lote_Pagamento();
            
            res = this.executasql.executarConsulta(sql);

            double vl_saldo = 0;
            double vl_compromisso = 0;
            double vl_pagamento = 0;
            double vl_multa_pagamento = 0;
            double vl_desconto = 0;
            double vl_outras_despesas = 0;

            while (res.next())   
            {
                vl_compromisso = res.getDouble("Vl_Compromisso");
                vl_pagamento = res.getDouble("VL_Pagamento");
                vl_saldo = res.getDouble("VL_Saldo");
                vl_multa_pagamento = res.getDouble("vl_multa_pagamento");
                vl_desconto = res.getDouble("vl_desconto");
                vl_outras_despesas = res.getDouble("vl_outras_despesas");

                sql = " DELETE FROM Movimentos_Compromissos ";
                sql +=" WHERE oid_Compromisso = " + res.getLong("OID_Compromisso");
                sql +="   AND oid_lote_pagamento = '" + ed.getOid_Lote_Pagamento() + "'";
                executasql.executarUpdate(sql);

                vl_saldo=vl_saldo+vl_pagamento-vl_multa_pagamento-vl_outras_despesas+vl_desconto;
                if (vl_saldo < 0) vl_saldo = 0;
                if (vl_saldo > vl_compromisso) vl_saldo=vl_compromisso;
                sql = " UPDATE Compromissos SET " +
                    "     DM_Lote_Pagamento = 'N'" +
                    "     ,Vl_Saldo= " + vl_saldo +
                    " WHERE oid_Compromisso = " + res.getLong("OID_Compromisso");
                executasql.executarUpdate(sql);
                
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "cancela()");
        }
    }


    public Lote_PagamentoED substitui_Lote_Pagamento(Lote_PagamentoED ed) throws Excecoes {

        String sql = null;
        // .println("substitui_Lote_Pagamento");
        int oid_Lote_Pagamento=0;
        Lote_PagamentoED edLote = new Lote_PagamentoED();
        try {

        edLote =  this.getByRecord(ed);

        // .println("substitui_Lote_Pagamento 2");

        edLote.setNr_Documento("");
        edLote.setDt_Emissao(Data.getDataDMY());
        edLote.setTx_Observacao("Substituicao Lote:"+ ed.getOid_Lote_Pagamento().intValue());
        edLote.setDM_Imprimir("S");
        edLote = this.inclui(edLote);


        // .println("substitui_Lote_Pagamento->>" + edLote.getOid_Lote_Pagamento().longValue());

        sql = " SELECT Compromissos.vl_saldo, Compromissos.vl_Compromisso " +
            "     ,Lotes_Compromissos.*" +
            " FROM Lotes_Compromissos" +
            "     ,Compromissos " +
            " WHERE Lotes_Compromissos.oid_compromisso = compromissos.oid_compromisso " +
            "   AND Lotes_Compromissos.oid_Lote_Pagamento = " + ed.getOid_Lote_Pagamento ();

        // .println("substitui_Lote_Pagamento 20-" + sql);

            ResultSet res = this.executasql.executarConsulta(sql);

            while (res.next())
            {
              // .println("substitui_Lote_Pagamento 21Oid_Compromisso->" + res.getString ("Oid_Compromisso"));

              Lote_CompromissoED edLotComp = new Lote_CompromissoED();
              edLotComp.setOid_Compromisso (new Integer(res.getString ("Oid_Compromisso")));
              edLotComp.setOid_Lote_Pagamento (edLote.getOid_Lote_Pagamento().longValue());
              edLotComp.setDt_Pagamento (res.getString ("dt_pagamento"));
              edLotComp.setDt_Emissao (Data.getDataDMY ());
              edLotComp.setVl_Pagamento (new Double(res.getDouble ("vl_pagamento")));
              edLotComp.setVl_Compromisso (new Double(res.getDouble ("vl_Compromisso")));
              edLotComp.setVl_Saldo_Lote_Compromisso (new Double(res.getDouble ("vl_saldo_lote_compromisso")));
              edLotComp.setVl_Multa_Pagamento (new Double(res.getDouble ("vl_saldo_lote_compromisso")));
              edLotComp.setVl_Outras_Despesas (new Double(res.getDouble ("Vl_Outras_Despesas")));
              edLotComp.setVl_Desconto (new Double(res.getDouble ("Vl_Desconto")));
              edLotComp.setTx_Observacao (res.getString ("Tx_Observacao"));

              // .println("substitui_Lote_Pagamento 21Oid_Compromisso 99");

              new Lote_CompromissoBD(executasql).inclui(edLotComp);
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "cancela()");
        }
        return edLote;
    }

    public void cancela_OLD(Lote_PagamentoED ed) throws Excecoes {

        String sql = null;
        double vl_principal = 0;
        double vl_saldo_compromisso = 0;

        CompromissoBD compromissoBD = new CompromissoBD(this.executasql);

        try {

            sql = " SELECT Compromissos.vl_saldo" +
                  "     ,Lotes_Compromissos.OID_Compromisso" +
                  "     ,Lotes_Compromissos.dt_pagamento" +
                  "     ,Lotes_Compromissos.vl_pagamento " +
                  " FROM Lotes_Pagamentos" +
                  "     ,Lotes_Compromissos" +
                  "     ,Compromissos " +
                  " WHERE Lotes_Pagamentos.oid_Lote_Pagamento = Lotes_Compromissos.oid_Lote_Pagamento " +
                  "   AND Lotes_Compromissos.oid_compromisso = compromissos.oid_compromisso " +
                  "   AND Lotes_Pagamentos.oid_Lote_Pagamento = " + ed.getOid_Lote_Pagamento();

            ResultSet res = this.executasql.executarConsulta(sql);

            while (res.next())
            {
                vl_principal = res.getDouble("vl_pagamento");
                vl_saldo_compromisso = res.getDouble("vl_saldo");

                DataFormatada.setDT_FormataData(res.getString("dt_pagamento"));

                sql = " DELETE FROM Movimentos_Compromissos ";
                sql +=" WHERE oid_Compromisso = " + res.getLong("OID_Compromisso");
                sql +="   AND DT_PAGAMENTO = '" + DataFormatada.getDT_FormataData() + "'";
                sql +="   AND VL_PAGAMENTO = '" + res.getString("vl_pagamento") + "'";
                executasql.executarUpdate(sql);

                CompromissoED edComp = new CompromissoED();
                vl_saldo_compromisso = vl_saldo_compromisso + vl_principal;
                edComp.setVl_Saldo(new Double(vl_saldo_compromisso));
                edComp.setOid_Compromisso(new Integer(res.getInt("OID_Compromisso")));

                compromissoBD.subtraiSaldo(edComp);

                sql = " UPDATE Lotes_Pagamentos SET ";
                sql +="     dm_situacao = 'C'";
                sql +=" WHERE oid_Lote_Pagamento = " + ed.getOid_Lote_Pagamento();
                executasql.executarUpdate(sql);
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "cancela_OLD()");
        }
    }

    public ArrayList lista(Lote_PagamentoED edComp) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();
        Lote_PagamentoPesquisaED ed = (Lote_PagamentoPesquisaED) edComp;

        try {
            sql = " SELECT * " +
                  " FROM Lotes_Pagamentos" +
                  "     ,Contas_Correntes" +
                  "     ,Pessoas" +
                  "     ,tipos_documentos " +
                  " WHERE Lotes_Pagamentos.oid_tipo_documento = tipos_Documentos.oid_tipo_documento " +
                  "   and Lotes_Pagamentos.oid_Conta_Corrente = Contas_Correntes.oid_Conta_Corrente " +
                  "   and Contas_Correntes.oid_pessoa = pessoas.oid_pessoa ";

            if (util.doValida(ed.getOid_Pessoa()) || ed.getNR_Compromisso() != null || ed.getOID_Compromisso() > 0)
            {
                sql = " SELECT * " +
                      " FROM Lotes_Pagamentos" +
                      "     ,Lotes_Compromissos" +
                      "     ,compromissos" +
                      "     ,Contas_Correntes" +
                      "     ,Pessoas" +
                      "     ,tipos_documentos " +
                      " WHERE Lotes_Compromissos.oid_compromisso = compromissos.oid_compromisso " +
                      "   and Lotes_Compromissos.oid_Lote_Pagamento = Lotes_Pagamentos.oid_Lote_Pagamento " +
                      "   and Lotes_Pagamentos.oid_tipo_documento = tipos_Documentos.oid_tipo_documento " +
                      "   and Lotes_Pagamentos.oid_Conta_Corrente = Contas_Correntes.oid_Conta_Corrente " +
                      "   and Contas_Correntes.oid_pessoa = pessoas.oid_pessoa ";

                if (util.doValida(ed.getOid_Pessoa()))
                    sql += " and compromissos.oid_pessoa = '" + ed.getOid_Pessoa() + "'";
                if (ed.getNR_Compromisso() != null && ed.getNR_Compromisso().intValue() > 0)
                    sql += " and compromissos.NR_Compromisso = " + ed.getNR_Compromisso();
                if (ed.getOID_Compromisso() > 0)
                    sql += " and compromissos.OID_Compromisso = " + ed.getOID_Compromisso();
            }

            if (ed.getNr_Lote_Pagamento() != null && ed.getNr_Lote_Pagamento().intValue() > 0)
                sql += " and Lotes_Pagamentos.OID_Lote_Pagamento = " + ed.getNr_Lote_Pagamento();
            if (util.doValida(ed.getDT_Programada_Inicial()))
                sql += " and Lotes_Pagamentos.DT_Programada >= '" + ed.getDT_Programada_Inicial() + "'";
            if (util.doValida(ed.getDT_Programada_Final()))
                sql += " and Lotes_Pagamentos.DT_Programada <= '" + ed.getDT_Programada_Final() + "'";
            if (util.doValida(ed.getDT_Emissao_Inicial()))
                sql += " and Lotes_Pagamentos.DT_Emissao >= '" + ed.getDT_Emissao_Inicial() + "'";
            if (util.doValida(ed.getDT_Emissao_Final()))
                sql += " and Lotes_Pagamentos.DT_Emissao <= '" + ed.getDT_Emissao_Final() + "'";
            if (util.doValida(ed.getOID_Conta_Corrente()))
                sql += " and Lotes_Pagamentos.OID_Conta_Corrente = '" + ed.getOID_Conta_Corrente() + "'";
            if (ed.getVl_Lote_Pagamento() != null && ed.getVl_Lote_Pagamento().doubleValue() > 0)
                sql += " and Lotes_Pagamentos.Vl_Lote_Pagamento = '" + ed.getVl_Lote_Pagamento() + "'";
            if (util.doValida(ed.getNM_Favorecido()))
                sql += " and Lotes_Pagamentos.NM_Favorecido = '" + ed.getNM_Favorecido() + "'";
            if (util.doValida(ed.getNR_Documento()))
                sql += " and Lotes_Pagamentos.NR_Documento = '" + ed.getNR_Documento() + "'";
            if (!"T".equals(ed.getDM_Situacao()))
                sql += " and Lotes_Pagamentos.DM_Situacao = '"+ed.getDM_Situacao()+"'";

            sql += " ORDER BY Lotes_Pagamentos.nr_Documento, Lotes_Pagamentos.DT_Programada ";
            // .println("############# LISTA= "+sql);
            ResultSet res = this.executasql.executarConsulta(sql);

            while (res.next())
            {
                Lote_PagamentoED edVolta = new Lote_PagamentoED();
                edVolta.setNM_Favorecido(res.getString("NM_Favorecido"));

                edVolta.setOid_Lote_Pagamento(new Integer(res.getInt("oid_Lote_Pagamento")));
                edVolta.setNr_Lote_Pagamento(new Integer(res.getInt("oid_Lote_Pagamento")));
                edVolta.setNr_Documento(res.getString("nr_Documento"));
                edVolta.setDM_Copia_Cheque(res.getString("DM_Copia_Cheque"));
                edVolta.setDM_Situacao(util.getTableStringValue("DM_Situacao", "Lotes_Pagamentos", "oid_Lote_Pagamento = " + edVolta.getOid_Lote_Pagamento()));
                edVolta.setVl_Lote_Pagamento(new Double(res.getDouble("VL_Lote_Pagamento")));

                DataFormatada.setDT_FormataData(res.getString("DT_Programada"));
                edVolta.setDT_Programada(DataFormatada.getDT_FormataData());

                DataFormatada.setDT_FormataData(res.getString("dt_emissao"));
                edVolta.setDt_Emissao(DataFormatada.getDT_FormataData());
                edVolta.setOID_Conta_Corrente(res.getString("OID_Conta_Corrente"));
                edVolta.setNM_Razao_Social_Banco(res.getString("NM_Razao_Social"));
                edVolta.setCD_Conta_Corrente(res.getString("CD_Conta_Corrente"));

                list.add(edVolta);
            }
            return list;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "lista()");
        }
    }

    public byte[] imprime_Documento_Pagamento (Lote_PagamentoED ed) throws Excecoes {
      try {

                // .println(" imprime_Documento_Pagamento PDF ");

        return new Lote_PagamentoRL ().imprime_Documento_Pagamento (imprime_Documento (ed) , localiza_Formulario (ed));
      }
      catch (Excecoes e) {
        throw e;
      }
      catch (Exception exc) {
        exc.printStackTrace ();
        throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "imprime_Documento_Pagamento()");
      }
    }

    public String imprime_Documento_Pagamento_Matricial (Lote_PagamentoED ed) throws Excecoes {
      try {

                // .println(" imprime_Documento_Pagamento_Matricial ");

        return new Lote_PagamentoRL ().imprime_Documento_Pagamento_Matricial (imprime_Documento (ed) , localiza_Formulario (ed));
      }
      catch (Excecoes e) {
        throw e;
      }
      catch (Exception exc) {
        exc.printStackTrace ();
        throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "imprime_Documento_Pagamento()");
      }
    }


    private ResultSet imprime_Documento(Lote_PagamentoED ed) throws Excecoes {

        String sql = null;
        double vl_principal = 0;
        double vl_saldo_compromisso = 0;
        double vl_aux = 0;
        int Nr_Documento = 0;
        ResultSet res = null;
        ResultSet resComp = null;
        ResultSet resMovComp = null;
        String NR_Documento_Lote = "";
        Movimento_CompromissoBD movimento_CompromissoBD = new Movimento_CompromissoBD(this.executasql);

        try {

            sql = " SELECT *, Contas_Correntes.cd_Conta_Corrente " +
                  " FROM lotes_pagamentos" +
                  "     ,Tipos_Documentos, Contas_Correntes " +
                  " WHERE lotes_pagamentos.VL_LOTE_PAGAMENTO > 0 " +
                  "   AND lotes_pagamentos.oid_Conta_Corrente = Contas_Correntes.oid_Conta_Corrente " +
                  "   AND lotes_pagamentos.oid_Tipo_Documento = Tipos_Documentos.oid_Tipo_Documento " +
                  "   AND Lotes_Pagamentos.DM_Imprimir = 'S' " +
                  "   AND Lotes_Pagamentos.DM_Situacao = 'I' ";

            if (util.doValida(ed.getOID_Conta_Corrente()))
                sql += " and Lotes_Pagamentos.OID_Conta_Corrente = '" + ed.getOID_Conta_Corrente() + "'";
            if (ed.getOID_Tipo_Documento() != null && ed.getOID_Tipo_Documento().intValue() > 0)
                sql += " and Lotes_Pagamentos.OID_Tipo_Documento = " + ed.getOID_Tipo_Documento();
            if (ed.getNr_Lote_Pagamento() != null && ed.getNr_Lote_Pagamento().intValue() > 0)
                sql += " and Lotes_Pagamentos.oid_Lote_Pagamento >= " + ed.getNr_Lote_Pagamento();
            if (ed.getNR_Lote_Pagamento_Final() != null && ed.getNR_Lote_Pagamento_Final().intValue() > 0)
                sql += " and Lotes_Pagamentos.oid_Lote_Pagamento <= " + ed.getNR_Lote_Pagamento_Final();
            sql += " ORDER BY lotes_pagamentos.dt_emissao, lotes_pagamentos.oid_Lote_Pagamento";
            // .println("############# IMPRIME DOC = "+sql);
            res = this.executasql.executarConsulta(sql.toString());
            while (res.next())
            {
                if (Nr_Documento == 0)
                    Nr_Documento = new Integer(ed.getNr_Documento()).intValue();

                NR_Documento_Lote = res.getString("CD_Tipo_Documento") + res.getString("cd_Conta_Corrente") +"-" + String.valueOf(Nr_Documento);

                if ("E".equals(res.getString("DM_Aplicacao")))
                    NR_Documento_Lote = String.valueOf(Nr_Documento); // cheques

                sql = " UPDATE Lotes_Pagamentos SET ";
                sql +="     nr_documento = '" + NR_Documento_Lote + "',";
                sql +="     dm_situacao = 'L'";
                sql +=" WHERE oid_Lote_Pagamento = " + (new Integer(res.getInt("oid_Lote_Pagamento")));
                // .println("############# UPDATE 1212 = "+sql);
                executasql.executarUpdate(sql);

                ed.setNr_Documento(NR_Documento_Lote);
                Nr_Documento++;

                sql = " UPDATE Documentos_Contas_Correntes SET ";
                sql +="     NR_Atual = " + Nr_Documento;
                sql +=" WHERE oid_Documento_Conta_Corrente = " + ed.getOid_Documento_Conta_Corrente();
                executasql.executarUpdate(sql);

                // .println(" DM_Compensacao="+res.getString("DM_Compensacao"));

                if ("I".equals(res.getString("DM_Compensacao")))
                {
                    ed.setDT_Compensacao(res.getString("Dt_Emissao"));
                    this.compensacao(ed, res,"IMPRESSAO");

                }

                sql = " SELECT Compromissos.vl_Compromisso, Compromissos.vl_saldo" +
                      "     ,Lotes_Compromissos.OID_Compromisso" +
                      "     ,Lotes_Compromissos.dt_pagamento" +
                      "     ,Lotes_Compromissos.vl_pagamento" +
                      "     ,Lotes_Compromissos.vl_multa_pagamento" +
                      "     ,Lotes_Compromissos.vl_desconto" +
                      "     ,Lotes_Compromissos.vl_outras_despesas" +
                      "     ,Lotes_Compromissos.vl_Juros_Pagamento" +
                      "     ,Lotes_Compromissos.tx_observacao" +
                      "     ,Lotes_Compromissos.vl_saldo_lote_compromisso " +
                      " FROM Lotes_Compromissos" +
                      "     ,Compromissos " +
                      " WHERE Lotes_Compromissos.oid_compromisso = compromissos.oid_compromisso " +
                      "   AND Lotes_Compromissos.OID_Lote_Pagamento = " + res.getInt("oid_Lote_Pagamento");

                resComp = this.executasql.executarConsulta(sql.toString());
                while (resComp.next())
                {
                    int ok=0;
                    vl_principal = resComp.getDouble("vl_pagamento");
                    vl_saldo_compromisso = resComp.getDouble("vl_saldo");

                    sql=" SELECT oid_mov_compromisso " +
                        " FROM Movimentos_Compromissos " +
                        " WHERE Movimentos_Compromissos.oid_lote_pagamento=" + res.getInt("oid_Lote_Pagamento") +
                        " AND   Movimentos_Compromissos.oid_compromisso = " + resComp.getInt("OID_Compromisso");
                    resMovComp = this.executasql.executarConsulta(sql.toString());
                    while (resMovComp.next()){
                     ok++;
                    }
                    if (ok==0 && vl_principal>0 && vl_saldo_compromisso>0) {
                      Movimento_CompromissoED edMovCompromisso = new Movimento_CompromissoED();

                      edMovCompromisso.setOid_Lote_Pagamento(res.getLong("oid_Lote_Pagamento"));

                      edMovCompromisso.setOid_Compromisso(new Integer(resComp.getInt("OID_Compromisso")));
                      DataFormatada.setDT_FormataData(resComp.getString("dt_pagamento"));
                      edMovCompromisso.setDt_Pagamento(DataFormatada.getDT_FormataData());
                      edMovCompromisso.setVl_Pagamento(new Double(resComp.getDouble("vl_pagamento")));

                      if (resComp.getDouble("vl_Multa_Pagamento") != 0)
                      {
                          edMovCompromisso.setVl_Multa_Pagamento(new Double(resComp.getDouble("vl_Multa_Pagamento")));
                          vl_aux = resComp.getDouble("vl_Multa_Pagamento");
                          vl_principal = vl_principal - vl_aux;
                      }
                      if (resComp.getDouble("vl_Desconto") != 0)
                      {
                          edMovCompromisso.setVl_Desconto(new Double(resComp.getDouble("vl_Desconto")));
                          vl_aux = resComp.getDouble("vl_Desconto");
                          vl_principal = vl_principal + vl_aux;
                      }
                      if (resComp.getDouble("vl_outras_despesas") != 0)
                      {
                          edMovCompromisso.setVl_Outras_Despesas(new Double(resComp.getDouble("vl_outras_despesas")));
                          vl_aux = resComp.getDouble("vl_outras_despesas");
                          vl_principal = vl_principal - vl_aux;
                      }
                      if (resComp.getDouble("vl_Juros_Pagamento") != 0)
                      {
                          edMovCompromisso.setVl_Juros_Pagamento(new Double(resComp.getDouble("vl_Juros_Pagamento")));
                          vl_aux = resComp.getDouble("vl_Juros_Pagamento");
                          vl_principal = vl_principal - vl_aux;
                      }
                      edMovCompromisso.setTx_Observacao(util.getValueDef(resComp.getString("tx_observacao"), ""));
                      edMovCompromisso.setVl_Saldo(new Double(resComp.getString("vl_saldo_lote_compromisso")));
                      movimento_CompromissoBD.inclui(edMovCompromisso);

                      vl_saldo_compromisso = vl_saldo_compromisso - vl_principal;
                      if (vl_saldo_compromisso<0) vl_saldo_compromisso=0;

                      sql = " UPDATE Compromissos SET VL_Saldo= " + vl_saldo_compromisso;
                      sql +="     ,dm_Lote_Pagamento = 'N'";
                      sql +=" WHERE oid_Compromisso = " + resComp.getLong("OID_Compromisso");
                      executasql.executarUpdate(sql);
                    }
                }
            }

            sql = " SELECT * " +
                  " FROM lotes_pagamentos" +
                  "     ,Unidades" +
                  "     ,Pessoas" +
                  "     ,Cidades" +
                  "     ,Tipos_Documentos " +
                  " WHERE lotes_pagamentos.oid_Unidade = Unidades.oid_Unidade " +
                  "   AND lotes_pagamentos.oid_Tipo_Documento = Tipos_Documentos.oid_Tipo_Documento " +
                  "   AND Unidades.oid_Pessoa = Pessoas.oid_Pessoa " +
                  "   AND Cidades.oid_Cidade = Pessoas.oid_Cidade " +
                  "   AND Lotes_Pagamentos.DM_Situacao = 'L' ";

            if (util.doValida(ed.getOID_Conta_Corrente()))
                sql += " and Lotes_Pagamentos.OID_Conta_Corrente = '" + ed.getOID_Conta_Corrente() + "'";
            if (ed.getNr_Lote_Pagamento() != null && ed.getNr_Lote_Pagamento().intValue() > 0)
                sql += " and Lotes_Pagamentos.oid_Lote_Pagamento >= " + ed.getNr_Lote_Pagamento();
            if (ed.getNR_Lote_Pagamento_Final() != null && ed.getNR_Lote_Pagamento_Final().intValue() > 0)
                sql += " and Lotes_Pagamentos.oid_Lote_Pagamento <= " + ed.getNR_Lote_Pagamento_Final();
            sql += " ORDER BY lotes_pagamentos.dt_emissao, lotes_pagamentos.oid_Lote_Pagamento";
            // .println("############# LISTA 3323= "+sql);
            res = this.executasql.executarConsulta(sql.toString());

            return res;

        } catch (Excecoes e) {
            throw e;
        } catch (Exception exc) {
            exc.printStackTrace();
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "imprime_Documento_Pagamento()");
        }
    }

    private String localiza_Formulario (Lote_PagamentoED ed) throws Excecoes {

      String sql = null;
      ResultSet res2 = null;
      try {
        sql = " SELECT DM_Formulario " +
              " FROM Bancos, Contas_Correntes " +
              " WHERE Bancos.oid_Pessoa = Contas_Correntes.oid_Pessoa " +
              "   AND Contas_Correntes.OID_Conta_Corrente = '" + ed.getOID_Conta_Corrente () + "'";
        res2 = this.executasql.executarConsulta (sql.toString ());

        String DM_Formulario = "1";
        while (res2.next ()) {
          if (res2.getString ("DM_Formulario") != null)
            DM_Formulario = res2.getString ("DM_Formulario");
        }
        return DM_Formulario;

      }
      catch (Excecoes e) {
        throw e;
      }
      catch (Exception exc) {
        exc.printStackTrace ();
        throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "imprime_Documento_Pagamento()");
      }
    }



    public byte[] geraLote_Pagamento_Emissao(Lote_PagamentoED ed) throws Excecoes {

        String sql = null;
        sql = " SELECT lotes_pagamentos.oid_lote_pagamento" +
              "     ,lotes_pagamentos.nr_documento as nr_documento_lote" +
              "     ,lotes_pagamentos.dt_emissao" +
              "     ,lotes_pagamentos.dt_programada" +
              "     ,lotes_pagamentos.dt_compensacao" +
              "     ,lotes_pagamentos.nm_favorecido" +
              "     ,lotes_pagamentos.vl_lote_pagamento" +
              "     ,lotes_pagamentos.dm_situacao" +              
              "     ,contas_correntes.nr_conta_corrente" +
              "     ,tipos_documentos_lote.nm_tipo_documento as nm_tipo_documento_lote" +
              "     ,pessoas_conta_corrente.nm_razao_social as nm_razao_social_conta_corrente " +
              " FROM pessoas pessoas_conta_corrente" +
              "     ,tipos_documentos tipos_documentos_lote" +
              "     ,lotes_pagamentos" +
              "     ,contas_correntes " +
              " WHERE Lotes_Pagamentos.oid_tipo_documento = tipos_Documentos_lote.oid_tipo_documento " +
              "   AND Lotes_Pagamentos.oid_conta_corrente = contas_correntes.oid_conta_corrente " +
              "   AND Contas_correntes.oid_pessoa = Pessoas_conta_corrente.oid_pessoa ";

        if ("N".equals(ed.getDM_Compensado()))
            sql += " and Lotes_Pagamentos.DT_Compensacao is null " + "";
        else if ("S".equals(ed.getDM_Compensado()))
            sql += " and Lotes_Pagamentos.DT_Compensacao is not null " + "";

        if (util.doValida(ed.getOID_Conta_Corrente()))
            sql += " and Lotes_Pagamentos.OID_Conta_Corrente = '" + ed.getOID_Conta_Corrente() + "'";
        if (util.doValida(ed.getDt_Inicial()))
            sql += " and Lotes_Pagamentos.DT_Emissao >= '" + ed.getDt_Inicial() + "'";
        if (util.doValida(ed.getDt_Final()))
            sql += " and Lotes_Pagamentos.DT_Emissao <= '" + ed.getDt_Final() + "'";

        if (!"T".equals(ed.getDM_Situacao()))
            sql += " and Lotes_Pagamentos.DM_Situacao = '"+ed.getDM_Situacao()+"'";

        sql += " ORDER BY lotes_pagamentos.dt_emissao, lotes_pagamentos.nr_documento";
        try {

            return new Lote_PagamentoRL().geraLote_Pagamento_Emissao(this.executasql.executarConsulta(sql.toString()));

        } catch (Excecoes e) {
            throw e;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "geraLote_Pagamento_Emissao()");
        }
    }

    public byte[] geraLote_Pagamento_Unidade(Lote_PagamentoED ed) throws Excecoes {

        String sql = null;

        sql = " SELECT lotes_pagamentos.oid_lote_pagamento" +
              "     ,lotes_pagamentos.nr_documento as nr_documento_lote" +
              "     ,lotes_pagamentos.dt_emissao" +
              "     ,lotes_pagamentos.dt_programada" +
              "     ,lotes_pagamentos.dt_compensacao" +
              "     ,lotes_pagamentos.dm_situacao" +              
              "     ,lotes_pagamentos.nm_favorecido" +
              "     ,lotes_pagamentos.dm_situacao" +              
              "     ,lotes_pagamentos.vl_lote_pagamento" +
              "     ,contas_correntes.nr_conta_corrente" +
              "     ,tipos_documentos_lote.nm_tipo_documento as nm_tipo_documento_lote" +
              "     ,pessoas_conta_corrente.nm_razao_social  as nm_razao_social_conta_corrente " +
              " FROM pessoas pessoas_conta_corrente" +
              "     ,tipos_documentos tipos_documentos_lote" +
              "     ,lotes_pagamentos, contas_correntes " +
              " WHERE Lotes_Pagamentos.oid_tipo_documento = tipos_Documentos_lote.oid_tipo_documento " +
              "   and Lotes_Pagamentos.oid_conta_corrente = contas_correntes.oid_conta_corrente " +
              "   and Contas_correntes.oid_pessoa = Pessoas_conta_corrente.oid_pessoa ";

        if ("N".equals(ed.getDM_Compensado()))
            sql += " and Lotes_Pagamentos.DT_Compensacao is null " + "";
        else if ("S".equals(ed.getDM_Compensado()))
            sql += " and Lotes_Pagamentos.DT_Compensacao is not null " + "";

        if (ed.getOid_Unidade() != null && ed.getOid_Unidade().intValue() > 0)
            sql += " and Lotes_Pagamentos.oid_unidade = " + ed.getOid_Unidade() + "";
        if (util.doValida(ed.getDt_Inicial()))
            sql += " and Lotes_Pagamentos.DT_Emissao >= '" + ed.getDt_Inicial() + "'";
        if (util.doValida(ed.getDt_Final()))
            sql += " and Lotes_Pagamentos.DT_Emissao <= '" + ed.getDt_Final() + "'";
        sql += " and Lotes_Pagamentos.DM_Situacao = 'L' ";
        sql += " ORDER BY lotes_pagamentos.dt_emissao, lotes_pagamentos.nr_documento";

        try {

            return new Lote_PagamentoRL().geraLote_Pagamento_Unidade(this.executasql.executarConsulta(sql.toString()));

        } catch (Excecoes e) {
            throw e;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "geraLote_Pagamento_Unidade()");
        }
    }

    public byte[] geraLote_Pagamento_Nao_Compensados(Lote_PagamentoED ed) throws Excecoes {

        String sql = null;

        sql = " SELECT lotes_pagamentos.oid_lote_pagamento" +
              "     ,lotes_pagamentos.nr_documento as nr_documento_lote" +
              "     ,lotes_pagamentos.dt_emissao" +
              "     ,lotes_pagamentos.dt_programada" +
              "     ,lotes_pagamentos.dt_compensacao" +
              "     ,lotes_pagamentos.nm_favorecido" +
              "     ,lotes_pagamentos.dm_situacao" +              
              "     ,lotes_pagamentos.vl_lote_pagamento" +
              "     ,contas_correntes.nr_conta_corrente" +
              "     ,contas_correntes.Cd_conta_corrente" +
              "     ,tipos_documentos_lote.nm_tipo_documento as nm_tipo_documento_lote" +
              "     ,pessoas_conta_corrente.nm_razao_social  as nm_razao_social_conta_corrente " +
              " FROM pessoas pessoas_conta_corrente" +
              "     ,tipos_documentos tipos_documentos_lote" +
              "     ,lotes_pagamentos" +
              "     ,contas_correntes " +
              " WHERE Lotes_Pagamentos.oid_tipo_documento = tipos_Documentos_lote.oid_tipo_documento " +
              "   and Lotes_Pagamentos.oid_conta_corrente = contas_correntes.oid_conta_corrente " +
              "   and Contas_correntes.oid_pessoa = Pessoas_conta_corrente.oid_pessoa " +
              "   and Lotes_Pagamentos.DT_Compensacao is null" +
              "   and Lotes_Pagamentos.DM_Situacao = 'L'";

        if (util.doValida(ed.getOID_Conta_Corrente()))
            sql += " and Lotes_Pagamentos.OID_Conta_Corrente = '" + ed.getOID_Conta_Corrente() + "'";
        if (util.doValida(ed.getDt_Inicial()))
            sql += " and Lotes_Pagamentos.DT_PROGRAMADA >= '" + ed.getDt_Inicial() + "'";
        if (util.doValida(ed.getDt_Final()))
            sql += " and Lotes_Pagamentos.DT_PROGRAMADA <= '" + ed.getDt_Final() + "'";
        sql += " ORDER BY lotes_pagamentos.DT_PROGRAMADA, Lotes_Pagamentos.oid_conta_corrente, lotes_pagamentos.nr_documento";

        try {

            return new Lote_PagamentoRL().geraLote_Pagamento_Nao_Compensados(this.executasql.executarConsulta(sql.toString()));

        } catch (Excecoes e) {
            throw e;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "geraLote_Pagamento_Nao_Compensados()");
        }
    }

    public byte[] geraLote_Pagamento_Devolvido(Lote_PagamentoED ed) throws Excecoes {

        String sql = null;

        sql = " SELECT lotes_pagamentos.oid_lote_pagamento" +
              "     ,lotes_pagamentos.nr_documento as nr_documento_lote" +
              "     ,lotes_pagamentos.dt_emissao" +
              "     ,lotes_pagamentos.dt_programada" +
              "     ,lotes_pagamentos.dt_compensacao" +
              "     ,lotes_pagamentos.nm_favorecido" +
              "     ,lotes_pagamentos.dm_situacao" +              
              "     ,Motivos_Devolucoes.cd_motivo_devolucao" +
              "     ,Motivos_Devolucoes.nm_motivo_devolucao" +
              "     ,lotes_pagamentos.vl_lote_pagamento" +
              "     ,contas_correntes.nr_conta_corrente" +
              "     ,contas_correntes.Cd_conta_corrente" +
              "     ,tipos_documentos_lote.nm_tipo_documento as nm_tipo_documento_lote" +
              "     ,pessoas_conta_corrente.nm_razao_social  as nm_razao_social_conta_corrente " +
              " FROM pessoas pessoas_conta_corrente" +
              "     ,tipos_documentos tipos_documentos_lote" +
              "     ,Motivos_Devolucoes" +
              "     ,lotes_pagamentos" +
              "     ,contas_correntes " +
              " WHERE Lotes_Pagamentos.oid_tipo_documento = tipos_Documentos_lote.oid_tipo_documento " +
              "   and Lotes_Pagamentos.oid_conta_corrente = contas_correntes.oid_conta_corrente " +
              "   and Lotes_Pagamentos.oid_motivo_retorno = Motivos_Devolucoes.oid_Motivo_Devolucao " +
              "   and Contas_correntes.oid_pessoa = Pessoas_conta_corrente.oid_pessoa " +
              "   and Lotes_Pagamentos.DT_Compensacao is null" +
              "   and Lotes_Pagamentos.DM_Situacao = 'L'";

        if (util.doValida(ed.getOID_Conta_Corrente()))
            sql += " and Lotes_Pagamentos.OID_Conta_Corrente = '" + ed.getOID_Conta_Corrente() + "'";
        if (util.doValida(ed.getDt_Inicial()))
            sql += " and Lotes_Pagamentos.DT_PROGRAMADA >= '" + ed.getDt_Inicial() + "'";
        if (util.doValida(ed.getDt_Final()))
            sql += " and Lotes_Pagamentos.DT_PROGRAMADA <= '" + ed.getDt_Final() + "'";
        sql += " ORDER BY lotes_pagamentos.DT_PROGRAMADA, Lotes_Pagamentos.oid_conta_corrente, lotes_pagamentos.nr_documento";

        try {

            return new Lote_PagamentoRL().geraLote_Pagamento_Devolvido(this.executasql.executarConsulta(sql.toString()));

        } catch (Excecoes e) {
            throw e;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "geraLote_Pagamento_Nao_Compensados()");
        }
    }

    public byte[] geraLote_Pagamento_Programacao(Lote_PagamentoED ed) throws Excecoes {

        String sql = null;

        sql = " SELECT lotes_pagamentos.*" +
              "     ,lotes_pagamentos.nr_documento as nr_documento_lote" +
              "     ,contas_correntes.nr_conta_corrente" +
              "     ,tipos_documentos_lote.nm_tipo_documento as nm_tipo_documento_lote" +
              "     ,pessoas_conta_corrente.nm_razao_social  as nm_razao_social_conta_corrente " +
              " FROM pessoas pessoas_conta_corrente" +
              "     ,tipos_documentos tipos_documentos_lote" +
              "     ,lotes_pagamentos, contas_correntes " +
              " WHERE Lotes_Pagamentos.oid_tipo_documento = tipos_Documentos_lote.oid_tipo_documento " +
              "   and Lotes_Pagamentos.oid_conta_corrente = contas_correntes.oid_conta_corrente " +
              "   and Contas_correntes.oid_pessoa = Pessoas_conta_corrente.oid_pessoa " +
              "   and Lotes_Pagamentos.DM_Situacao = 'L'";

        if ("N".equals(ed.getDM_Compensado()))
            sql += " and Lotes_Pagamentos.DT_Compensacao is null " + "";
        else if ("S".equals(ed.getDM_Compensado()))
            sql += " and Lotes_Pagamentos.DT_Compensacao is not null " + "";

        if (util.doValida(ed.getOID_Conta_Corrente()))
            sql += " and Lotes_Pagamentos.OID_Conta_Corrente = '" + ed.getOID_Conta_Corrente() + "'";
        if (util.doValida(ed.getDt_Inicial()))
            sql += " and Lotes_Pagamentos.dt_programada >= '" + ed.getDt_Inicial() + "'";
        if (util.doValida(ed.getDt_Final()))
            sql += " and Lotes_Pagamentos.dt_programada <= '" + ed.getDt_Final() + "'";
        sql += " ORDER BY lotes_pagamentos.dt_programada, lotes_pagamentos.nr_documento";

        // .println("############# LISTA 5546= "+sql);

        try {

            return new Lote_PagamentoRL().geraLote_Pagamento_Programacao(this.executasql.executarConsulta(sql.toString()));

        } catch (Excecoes e) {
            throw e;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "geraLote_Pagamento_Programacao()");
        }
    }

    public byte[] geraLote_Pagamento_Compensacao(Lote_PagamentoED ed) throws Excecoes {

        String sql = null;

        sql = " SELECT lotes_pagamentos.oid_lote_pagamento" +
              "     ,lotes_pagamentos.nr_documento as nr_documento_lote" +
              "     ,lotes_pagamentos.dt_emissao" +
              "     ,lotes_pagamentos.dt_programada" +
              "     ,lotes_pagamentos.dt_compensacao" +
              "     ,lotes_pagamentos.nm_favorecido" +
              "     ,lotes_pagamentos.dm_situacao" +              
              "     ,lotes_pagamentos.vl_lote_pagamento" +
              "     ,contas_correntes.nr_conta_corrente" +
              "     ,tipos_documentos_lote.nm_tipo_documento as nm_tipo_documento_lote" +
              "     ,pessoas_conta_corrente.nm_razao_social  as nm_razao_social_conta_corrente " +
              " FROM pessoas pessoas_conta_corrente" +
              "     ,tipos_documentos tipos_documentos_lote" +
              "     ,lotes_pagamentos, contas_correntes " +
              " WHERE Lotes_Pagamentos.oid_tipo_documento = tipos_Documentos_lote.oid_tipo_documento " +
              "   and Lotes_Pagamentos.oid_conta_corrente = contas_correntes.oid_conta_corrente " +
              "   and Contas_correntes.oid_pessoa = Pessoas_conta_corrente.oid_pessoa " +
              "   and Lotes_Pagamentos.DM_Situacao = 'L'";

        if (util.doValida(ed.getOID_Conta_Corrente()))
            sql += " and Lotes_Pagamentos.OID_Conta_Corrente = '" + ed.getOID_Conta_Corrente() + "'";
        if (util.doValida(ed.getDt_Inicial()))
            sql += " and Lotes_Pagamentos.dt_compensacao >= '" + ed.getDt_Inicial() + "'";
        if (util.doValida(ed.getDt_Final()))
            sql += " and Lotes_Pagamentos.dt_compensacao <= '" + ed.getDt_Final() + "'";
        sql += " ORDER BY lotes_pagamentos.dt_compensacao, lotes_pagamentos.nr_documento";

        // .println(sql);

        try {

            return new Lote_PagamentoRL().geraLote_Pagamento_Compensacao(this.executasql.executarConsulta(sql.toString()));

        } catch (Excecoes e) {
            throw e;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "geraLote_Pagamento_Compensacao()");
        }
    }

    public byte[] geraLote_Pagamento_Fornecedor(Lote_PagamentoED ed) throws Excecoes {

        String sql = null;

        sql = " SELECT Pessoas_Fornecedor.oid_pessoa as oid_fornecedor" +
              "     ,lotes_pagamentos.oid_lote_pagamento" +
              "     ,lotes_pagamentos.nr_documento as nr_documento_lote" +
              "     ,lotes_pagamentos.dt_emissao" +
              "     ,lotes_pagamentos.dt_programada" +
              "     ,lotes_pagamentos.dt_compensacao" +
              "     ,lotes_pagamentos.dm_situacao" +              
              "     ,lotes_pagamentos.nm_favorecido" +
              "     ,lotes_pagamentos.vl_lote_pagamento" +
              "     ,contas_correntes.nr_conta_corrente" +
              "     ,tipos_documentos_lote.nm_tipo_documento as nm_tipo_documento_lote" +
              "     ,pessoas_fornecedor.nm_razao_social as nm_razao_social_fornecedor" +
              "     ,pessoas_conta_corrente.nm_razao_social as nm_razao_social_conta_corrente " +
              " FROM pessoas pessoas_conta_corrente" +
              "     ,pessoas pessoas_fornecedor" +
              "     ,tipos_documentos tipos_documentos_lote" +
              "     ,lotes_pagamentos" +
              "     ,contas_correntes" +
              "     ,Lotes_Compromissos" +
              "     ,Compromissos" +
              " WHERE Lotes_Pagamentos.oid_tipo_documento = tipos_Documentos_lote.oid_tipo_documento " +
              "   and Lotes_Pagamentos.oid_Lote_Pagamento = Lotes_Compromissos.oid_Lote_Pagamento " +
              "   and Lotes_Compromissos.oid_compromisso = compromissos.oid_compromisso " +
              "   and Lotes_Pagamentos.oid_conta_corrente = contas_correntes.oid_conta_corrente " +
              "   and Contas_correntes.oid_pessoa = Pessoas_conta_corrente.oid_pessoa " +
              "   and Compromissos.oid_pessoa = pessoas_fornecedor.oid_pessoa " +
              "   and Lotes_Pagamentos.DM_Situacao = 'L'";

        if ("N".equals(ed.getDM_Compensado()))
            sql += " and Lotes_Pagamentos.DT_Compensacao is null " + "";
        else if ("S".equals(ed.getDM_Compensado()))
            sql += " and Lotes_Pagamentos.DT_Compensacao is not null " + "";

        if (util.doValida(ed.getOid_Pessoa()))
            sql += " and Compromissos.OID_Pessoa = '" + ed.getOid_Pessoa() + "'";
        if (util.doValida(ed.getDt_Inicial()))
            sql += " and Lotes_Pagamentos.dt_programada >= '" + ed.getDt_Inicial() + "'";
        if (util.doValida(ed.getDt_Final()))
            sql += " and Lotes_Pagamentos.dt_programada <= '" + ed.getDt_Final() + "'";
        sql += " ORDER BY Compromissos.OID_Pessoa, Lotes_Pagamentos.dt_programada";

        try {

            return new Lote_PagamentoRL().geraLote_Pagamento_Fornecedor(this.executasql.executarConsulta(sql.toString()));

        } catch (Excecoes e) {
            throw e;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "geraLote_Pagamento_Fornecedor()");
        }
    }

    // /### GM 13062003
    public int altera_Programacao(Lote_PagamentoED ed) throws Excecoes {

        String sql = null;
        try {

            sql = " UPDATE Lotes_Pagamentos SET ";
            sql +="     DT_Programada = '" + ed.getDT_Programada() + "',";
            sql +="     TX_Observacao = '" + ed.getTx_Observacao() + "'";
            sql +=" WHERE oid_Conta_Corrente = " + ed.getOID_Conta_Corrente();
            sql +="   AND NR_Documento = " + ed.getNr_Documento();
            sql +="   AND DT_Compensacao is null ";

            return executasql.executarUpdate(sql);
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "altera_Programacao()");
        }
    }

    public byte[] imprime_Lote_Pagamento(Lote_PagamentoED ed) throws Excecoes {

        String sql = null;
        try {


            sql = " SELECT lotes_pagamentos.oid_lote_pagamento" +
                  "     ,lotes_pagamentos.oid_unidade " +
                  "     ,lotes_pagamentos.nr_documento as nr_documento_lote" +
                  "     ,lotes_pagamentos.vl_lote_pagamento as vl_lote_pagamento" +
                  "     ,lotes_pagamentos.tx_observacao as tx_observacao_lote" +
                  "     ,lotes_pagamentos.dt_Emissao" +
                  "     ,lotes_pagamentos.dt_Programada" +
                  "     ,lotes_pagamentos.dm_situacao" +              
                  "     ,lotes_pagamentos.nm_favorecido" +
                  "     ,lotes_compromissos.dm_Situacao as dm_Situacao_lote_compromisso" +
                  "     ,lotes_compromissos.dt_pagamento" +
                  "     ,lotes_compromissos.vl_pagamento" +
                  "     ,compromissos.nr_documento as nr_documento_compromisso" +
                  "     ,compromissos.nr_parcela" +
                  "     ,compromissos.nr_compromisso" +
                  "     ,contas_correntes.nr_conta_corrente" +
                  "     ,Moedas.CD_Moeda" +
                  "     ,tipos_documentos_lote.nm_tipo_documento as nm_tipo_documento_lote" +
                  "     ,tipos_documentos_compromisso.nm_tipo_documento as nm_tipo_documento_compromisso" +
                  "     ,pessoas_compromisso.nm_razao_social  as nm_razao_social_compromisso" +
                  "     ,pessoas_conta_corrente.nm_razao_social  as nm_razao_social_conta_corrente " +
                  " FROM compromissos" +
                  "     ,pessoas pessoas_compromisso" +
                  "     ,pessoas pessoas_conta_corrente" +
                  "     ,tipos_documentos tipos_documentos_lote" +
                  "     ,tipos_documentos tipos_documentos_compromisso" +
                  "     ,lotes_compromissos" +
                  "     ,lotes_pagamentos" +
                  "     ,contas_correntes " +
                  "     ,moedas " +
                  " WHERE Lotes_Compromissos.oid_compromisso = compromissos.oid_compromisso " +
                  "   and Lotes_Compromissos.oid_Lote_Pagamento = Lotes_Pagamentos.oid_Lote_Pagamento " +
                  "   and Lotes_Pagamentos.oid_tipo_documento = tipos_Documentos_lote.oid_tipo_documento " +
                  "   and Lotes_Pagamentos.oid_conta_corrente = contas_correntes.oid_conta_corrente " +
                  "   and Contas_correntes.oid_pessoa = Pessoas_conta_corrente.oid_pessoa " +
                  "   and Moedas.oid_Moeda = Contas_Correntes.oid_Moeda " +
                  "   and compromissos.oid_pessoa = pessoas_compromisso.oid_pessoa " +
                  "   and Lotes_Compromissos.dm_Situacao <> 'E' " +
                  "   and compromissos.oid_tipo_documento = tipos_documentos_compromisso.oid_tipo_documento";

            if (util.doValida(ed.getOID_Conta_Corrente()))
                sql += " and Lotes_Pagamentos.OID_Conta_Corrente = '" + ed.getOID_Conta_Corrente() + "'";
            if (ed.getOID_Tipo_Documento() != null && ed.getOID_Tipo_Documento().intValue() > 0)
                sql += " and Lotes_Pagamentos.OID_Tipo_Documento = " + ed.getOID_Tipo_Documento();
            if (ed.getNr_Lote_Pagamento() != null && ed.getNr_Lote_Pagamento().intValue() > 0)
                sql += " and Lotes_Pagamentos.oid_Lote_Pagamento >= " + ed.getNr_Lote_Pagamento();
            if (ed.getNR_Lote_Pagamento_Final() != null && ed.getNR_Lote_Pagamento_Final().intValue() > 0)
                sql += " and Lotes_Pagamentos.oid_Lote_Pagamento <= " + ed.getNR_Lote_Pagamento_Final();
            sql += " ORDER BY Lotes_Pagamentos.oid_Lote_Pagamento, Lotes_Compromissos.oid";

            return new Lote_PagamentoRL().imprime_Lote_Pagamento(this.executasql.executarConsulta(sql.toString()), ed);

        } catch (Excecoes e) {
            throw e;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "imprime_Lote_Pagamento()");
        }
    }

    /** ------------ RELATÓRIOS ---------------- */
    //*** Lotes de Pagamento
    public void relLote_Pagamento(RelatorioED ed) throws Excecoes {

        String sql = null;
        ArrayList lista = new ArrayList();
        try {

            sql = " SELECT Lotes_Pagamentos.*" +
                  "       ,Tipos_Documentos.NM_Tipo_Documento" +
                  "       ,PessoaCC.oid_Pessoa" +
                  "       ,PessoaCC.NM_Razao_Social" +
                  "       ,Contas_Correntes.oid_Conta_Corrente" +
                  "       ,Contas_Correntes.CD_Conta_Corrente" +
                  "       ,Contas_Correntes.NR_Conta_Corrente" +
                  " FROM Lotes_Pagamentos" +
                  "     ,Pessoas as PessoaCC" +
                  "     ,Tipos_Documentos" +
                  "     ,Contas_Correntes" +
                  " WHERE Lotes_Pagamentos.oid_Conta_Corrente = Contas_Correntes.oid_Conta_Corrente" +
                  "   AND Contas_Correntes.oid_Pessoa = PessoaCC.oid_Pessoa" +
                  "   AND Lotes_Pagamentos.oid_Tipo_Documento = Tipos_Documentos.oid_Tipo_Documento" +
                  "   AND Lotes_Pagamentos.DM_Situacao = 'L'";

            //*** Emissão
            if (util.doValida(ed.getDt_emissao()) && util.doValida(ed.getDt_emissao_final())) {
                sql +=" AND Lotes_Pagamentos.DT_Emissao BETWEEN '"+ed.getDt_emissao()+"' AND '"+ed.getDt_emissao_final()+"'";
            } else if (util.doValida(ed.getDt_emissao()) || util.doValida(ed.getDt_emissao_final())) {
                sql +=" AND Lotes_Pagamentos.DT_Emissao = '"+(util.doValida(ed.getDt_emissao()) ? ed.getDt_emissao() : ed.getDt_emissao_final())+"'";
            }
            //*** Programada
            if (util.doValida(ed.getDt_programada()) && util.doValida(ed.getDt_programada_final())) {
                sql +=" AND Lotes_Pagamentos.DT_Programada BETWEEN '"+ed.getDt_programada()+"' AND '"+ed.getDt_programada_final()+"'";
            } else if (util.doValida(ed.getDt_programada()) || util.doValida(ed.getDt_programada_final())) {
                sql +=" AND Lotes_Pagamentos.DT_Programada = '"+(util.doValida(ed.getDt_programada()) ? ed.getDt_programada() : ed.getDt_programada_final())+"'";
            }
            //*** Compensação
            if (util.doValida(ed.getDt_compensacao()) && util.doValida(ed.getDt_compensacao_final())) {
                sql +=" AND Lotes_Pagamentos.DT_Compensacao BETWEEN '"+ed.getDt_compensacao()+"' AND '"+ed.getDt_compensacao_final()+"'";
            } else if (util.doValida(ed.getDt_compensacao()) || util.doValida(ed.getDt_compensacao_final())) {
                sql +=" AND Lotes_Pagamentos.DT_Compensacao = '"+(util.doValida(ed.getDt_compensacao()) ? ed.getDt_compensacao() : ed.getDt_compensacao_final())+"'";
            }
            if (util.doValida(ed.getOid_conta_corrente()))
                sql += " AND Lotes_Pagamentos.oid_Conta_Corrente = '"+ed.getOid_conta_corrente()+"'";
            if (ed.getOid_tipo_documento() > 0)
                sql += " AND Lotes_Pagamentos.oid_Tipo_Documento = "+ed.getOid_tipo_documento();

            if ("N".equals(ed.getDm_situacao()))
                sql += " AND Lotes_Pagamentos.DT_Compensacao is null";
            else if ("S".equals(ed.getDm_situacao()))
                sql += " AND Lotes_Pagamentos.DT_Compensacao is not null";

            //*** Quebras
            //EMISSAO
            if (ed.getNomeRelatorio().endsWith("01"))
                sql += " ORDER BY Lotes_Pagamentos.DT_Emissao, Contas_Correntes.CD_Conta_Corrente, Lotes_Pagamentos.NR_Documento";
            //PROGRAMADA
            else if (ed.getNomeRelatorio().endsWith("02"))
                sql += " ORDER BY Lotes_Pagamentos.DT_Programada, Contas_Correntes.CD_Conta_Corrente, Lotes_Pagamentos.NR_Documento";
            //COPENSACAO
            else if (ed.getNomeRelatorio().endsWith("03"))
                sql += " ORDER BY Lotes_Pagamentos.DT_Compensacao, Contas_Correntes.CD_Conta_Corrente, Lotes_Pagamentos.NR_Documento";
            //CONTA_CORRENTE
            else if (ed.getNomeRelatorio().endsWith("04"))
                sql += " ORDER BY Contas_Correntes.CD_Conta_Corrente, Lotes_Pagamentos.DT_Programada, Lotes_Pagamentos.NR_Documento";
            // .println("############# LISTA  0974= "+sql);
            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                RelatorioED edRel = new RelatorioED();

                edRel.setOid_pessoa(res.getString("oid_Pessoa"));
                edRel.setNr_cnpj_cpf(edRel.getOid_pessoa());
                edRel.setNm_razao_social(res.getString("NM_Razao_Social"));
                edRel.setOid_conta_corrente(res.getString("oid_Conta_Corrente"));
                edRel.setCd_conta(res.getString("CD_Conta_Corrente"));
                edRel.setNr_conta(res.getString("NR_Conta_Corrente"));

                edRel.setDt_emissao(FormataDataBean.getFormatDate(res.getString("DT_Emissao")));
                edRel.setNm_emissao(Data.getDiaSemana(edRel.getDt_emissao()));
                edRel.setDt_programada(FormataDataBean.getFormatDate(res.getString("DT_Programada")));
                edRel.setNm_programada(Data.getDiaSemana(edRel.getDt_programada()));
                edRel.setDt_compensacao(util.getValueDef(FormataDataBean.getFormatDate(res.getString("DT_Compensacao")),"-- /-- /----"));
                if (util.doValida(res.getString("DT_Compensacao")))
                    edRel.setNm_compensacao(Data.getDiaSemana(edRel.getDt_compensacao()));

                edRel.setNm_documento(res.getString("NR_Documento"));
                edRel.setNm_tipo_documento(res.getString("NM_Tipo_Documento"));
                edRel.setNm_favorecido(util.getValueDef(res.getString("NM_Favorecido"),""));

                edRel.setNr_lote_pagamento(res.getInt("oid_Lote_Pagamento"));
                edRel.setVl_lote(res.getDouble("VL_Lote_Pagamento"));

                lista.add(edRel);
            }
            ed.setLista(lista);
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "relLote_Pagamento()");
        }
        //*** Chama o Gerador de Relatórios Jasper
        new JasperRL(ed).geraRelatorio();
    }
    
    public void troca_Compromisso_Lote_Pagamento(Lote_PagamentoED ed) throws Excecoes {

        String sql = null;

        try {
            sql = " UPDATE Lotes_Compromissos SET ";
            sql += " oid_Compromisso = " + ed.getOID_Compromisso_Troca();
            sql += " WHERE oid_Lote_Pagamento = " + ed.getOid_Lote_Pagamento();
            sql += " AND oid_Compromisso = " + ed.getOID_Compromisso();

            executasql.executarUpdate(sql);

            sql = " UPDATE Movimentos_Compromissos SET ";
            sql += " oid_Compromisso = " + ed.getOID_Compromisso_Troca();
            sql += " WHERE oid_Compromisso = " + ed.getOID_Compromisso();

            executasql.executarUpdate(sql);

            double vl_Pagamento = 0;
            
            sql =  " select vl_pagamento from Lotes_Compromissos ";
            sql += " WHERE oid_Lote_Pagamento = " + ed.getOid_Lote_Pagamento();
            sql += " AND oid_Compromisso = " + ed.getOID_Compromisso_Troca();
            
            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
            	vl_Pagamento = res.getDouble("vl_pagamento");
            }
            
            sql = " UPDATE Compromissos SET ";
            sql += " vl_Saldo = vl_Saldo - " + vl_Pagamento ;
            sql += " WHERE oid_Compromisso = " + ed.getOID_Compromisso_Troca();

            executasql.executarUpdate(sql);

            CompromissoED compromissoED = new CompromissoED();
            
            String oid_Compromisso = String.valueOf(ed.getOID_Compromisso());
            compromissoED.setOid_Compromisso(new Integer(oid_Compromisso));
            CompromissoBD compromissoBD = new CompromissoBD(this.executasql);
            compromissoBD.deleta(compromissoED);
            
            Lote_PagamentoED lote_PagamentoED = new Lote_PagamentoED ();
            lote_PagamentoED.setOid_Lote_Pagamento (ed.getOid_Lote_Pagamento());
            new Lancamento_ContabilBD (this.executasql).inclui_CTB_Lote_Pagamento (lote_PagamentoED);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "altera()");
        }
    }

}
