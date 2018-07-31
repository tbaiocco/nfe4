package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.master.ed.Lancamento_ContabilED;
import com.master.bd.Movimento_Conta_CorrenteBD;
import com.master.ed.Movimento_Conta_CorrenteED;
import com.master.ed.Movimento_DuplicataED;
import com.master.ed.Movimento_DuplicataPesquisaED;
import com.master.root.FormataDataBean;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Data;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;
import com.master.util.Utilitaria;

public class Movimento_DuplicataBD   {

    public ExecutaSQL executasql;
    Utilitaria util = new Utilitaria(executasql);

    Parametro_FixoED parametro_FixoED = new Parametro_FixoED();

    public Movimento_DuplicataBD(ExecutaSQL sql) {
      this.executasql = sql;
      util = new Utilitaria(this.executasql);
    }

    public Movimento_DuplicataED inclui(Movimento_DuplicataED ed, ExecutaSQL executasql) throws Excecoes {


        String sql="";
        String dm_Carteira="";
        int oid_Tipo_Documento_Duplicata=0;
        double vl_movimento = 0;
        double vl_duplicata = 0;
        ResultSet rsLC = null;

        try {

           sql = " SELECT oid_Tipo_Documento, vl_duplicata, vl_saldo, dm_Carteira " +
                 " FROM   Duplicatas, Carteiras  " +
                 " WHERE  Duplicatas.oid_Carteira = Carteiras.oid_Carteira " +
                 " AND    oid_Duplicata = " + ed.getOid_Duplicata();

            ResultSet rs = executasql.executarConsulta(sql);
            while (rs.next())
            {
                dm_Carteira=rs.getString("dm_Carteira");
                oid_Tipo_Documento_Duplicata=rs.getInt("oid_Tipo_Documento");
                vl_movimento = ed.getVL_Credito().doubleValue() + ed.getVL_Debito().doubleValue();
                vl_duplicata = rs.getDouble("vl_duplicata");
                if (vl_movimento > vl_duplicata * 2)
                    throw new Excecoes("Erro - valor do movimento superior ao valor do titulo");
            }

            //*** Verifica se já Existe Movimento
             /* KIELING 04/07/2006 juju

            if (util.doExiste("Movimentos_Duplicatas", "oid_Duplicata = "+ed.getOid_Duplicata()+ " AND oid_tipo_instrucao= "+ed.getOID_Tipo_Instrucao()+ " AND Dt_Movimento='"+ed.getDT_Movimento()+"'"))
                throw new Excecoes("Erro - já existe este movimento!");
            */

            String temp = String.valueOf(ed.getOid_Duplicata());
            if (temp.length() > 6)
                temp = temp.substring(1, 6);
            ed.setOid_Movimento_Duplicata(temp + String.valueOf(ed.getNR_Sequencia_Duplicata()).toString() + String.valueOf(System.currentTimeMillis()).toString());

            double VL_Variacao_Cambial = 0;
            double VL_Cotacao_Emissao = 0;
            double VL_Cotacao_Pagamento = 0;
            if ("S".equals(ed.getDM_Principal())){
              VL_Variacao_Cambial = ed.getVL_Variacao_Cambial();
              VL_Cotacao_Emissao = ed.getVL_Cotacao_Emissao();
              VL_Cotacao_Pagamento = ed.getVL_Cotacao_Pagamento();
            }

            sql = " INSERT INTO Movimentos_Duplicatas (" +
                  "      OID_Movimento_Duplicata" +
                  "     ,DT_Movimento" +
                  "     ,NR_Sequencia_Duplicata" +
                  "     ,VL_Credito" +
                  "     ,VL_Debito" +
                  "     ,VL_Variacao_Cambial" +
                  "     ,VL_Cotacao_Emissao" +
                  "     ,VL_Cotacao_Pagamento" +
                  "     ,DT_STAMP" +
                  "     ,USUARIO_STAMP" +
                  "     ,DM_Principal" +
                  "     ,DM_STAMP" +
                  "     ,OID_Duplicata" +
                  "     ,OID_Tipo_Instrucao" +
                  "     ,OID_Conta" +
                  "     ,OID_Bordero" +
                  "     ,OID_Conhecimento" +
                  "     ,OID_Carteira" +
                  "     ,OID_Entrega" +
                  "     ,dm_exportado)" +
                  " VALUES ('" + ed.getOid_Movimento_Duplicata() + "',";
            sql += "'" + ed.getDT_Movimento() + "',";
            sql += ed.getNR_Sequencia_Duplicata() + ",";
            sql += ed.getVL_Credito() + ",";
            sql += ed.getVL_Debito() + ",";
            sql += VL_Variacao_Cambial + ",";
            sql += VL_Cotacao_Emissao + ",";
            sql += VL_Cotacao_Pagamento + ",";
            sql += "'" + ed.getDt_stamp() + "',";
            sql += "'" + ed.getUsuario_Stamp() + "',";
            sql += "'" + ed.getDM_Principal() + "',";
            sql += "'" + ed.getDm_Stamp() + "',";
            sql += String.valueOf(JavaUtil.doValida(String.valueOf(ed.getOid_Duplicata())) ? ed.getOid_Duplicata() : new Long(0).longValue()) + ",";
            sql += String.valueOf(ed.getOID_Tipo_Instrucao() != null ? ed.getOID_Tipo_Instrucao() : new Integer(0)) + ",";
            sql += String.valueOf(ed.getOid_Conta() != null ? ed.getOid_Conta() : new Integer(0)) + ",";
            sql += String.valueOf(ed.getOid_Bordero() != null ? ed.getOid_Bordero() : new Integer(0)) + ",";
            sql += "'" + ed.getOid_Conhecimento() + "',";
            sql += String.valueOf(ed.getOID_Carteira() != null ? ed.getOID_Carteira() : new Integer(0)) + ",";
            sql += ed.getOid_Entrega() +",";
            sql += "'N')";


            // System.out.print(sql);

            executasql.executarUpdate(sql);

            if ("_______S".equals(parametro_FixoED.getDM_Gera_Lancamento_Contabil()))
            {
                //faz os lancamentos contabeis do movimento
                Lancamento_ContabilED lc = new Lancamento_ContabilED();
            	new Lancamento_ContabilBD(this.executasql).inclui_CTB_Movimento_Duplicata(ed);
            }

            // System.out.println(">>>>>>>>>> " + ed.getOid_Conhecimento ());

            if (ed.getOid_Conhecimento () != null && ed.getOid_Conhecimento ().length () > 4) {
              sql = " UPDATE Origens_Duplicatas SET DM_Situacao='P', oid_movimento_duplicata='" + ed.getOid_Movimento_Duplicata () + "'" +
                    " WHERE  Origens_Duplicatas.oid_Duplicata = '" + ed.getOid_Duplicata () + "'" +
                    " AND    Origens_Duplicatas.oid_Conhecimento = '" + ed.getOid_Conhecimento () + "'";
            // System.out.print(sql);

              executasql.executarUpdate (sql);
            }
            // System.out.println(">>>>>>>>>> 2");


            String dm_controle_cobranca="N";
            String dm_tipo_conta_corrente="B";

            if (!"D".equals(dm_Carteira)&& "S".equals(ed.getDM_Principal()))
            {
                sql = "select dm_controle_cobranca, dm_tipo_conta_corrente FROM Contas_Correntes WHERE oid_Conta_Corrente = '" + ed.getOid_Conta_Corrente() + "'";
                rsLC = this.executasql.executarConsulta(sql);
                while (rsLC.next()) {
                    dm_controle_cobranca = rsLC.getString("dm_controle_cobranca");
                    dm_tipo_conta_corrente = rsLC.getString("dm_tipo_conta_corrente");
                }
            }

            //*** Se Devolução de Mercadorias não inclui em Movimento_Conta_Corrente
            if (ed.getVL_Devolucao_Nota_Fiscal() <= 0 && "S".equals(dm_controle_cobranca) )
            {
                Movimento_Conta_CorrenteED movCCED = new Movimento_Conta_CorrenteED();
	            movCCED.setOid_Conta_Corrente(ed.getOid_Conta_Corrente());
	            movCCED.setOid_Conta(util.getTableIntValue("oid_Conta", "Contas_Correntes", "oid_Conta_Corrente = '"+ed.getOid_Conta_Corrente()+"'"));
	            movCCED.setNR_Documento(ed.getNR_Documento());
	            movCCED.setDT_Movimento_Conta_Corrente(ed.getDT_Movimento_Conta_Corrente());
	            movCCED.setNM_Complemento_Historico(ed.getNM_Complemento_Historico());
                    movCCED.setDM_Debito_Credito ("C");
                    if ("U".equals(dm_tipo_conta_corrente)){ //caixa entra a debito
                      movCCED.setDM_Debito_Credito ("D");
                    }
	            movCCED.setDM_Tipo_Lancamento("G");
	            movCCED.setOid_Historico(new Integer(parametro_FixoED.getOID_Historico_Liquidacao_Cobranca()));
	            movCCED.setOid_Movimento_Duplicata(ed.getOid_Movimento_Duplicata());
	            movCCED.setOID_Tipo_Documento(new Integer(oid_Tipo_Documento_Duplicata));
	            movCCED.setVL_Lancamento(new Double(ed.getVL_Liquido()));

	            new Movimento_Conta_CorrenteBD(executasql).inclui(movCCED);
            }
            return ed;

        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "inclui()");
        }
    }

    public void altera(Movimento_DuplicataED ed) throws Excecoes {

        String sql = null;
        try {

            sql = " UPDATE Movimentos_Duplicatas SET ";

            if (ed.getDT_Movimento() != null)
                sql += " DT_Movimento = '" + ed.getDT_Movimento() + "', ";
            else sql += " DT_Movimento = null,";

            if (ed.getVL_Debito() != null)
                sql += " VL_Debito = " + ed.getVL_Debito() + ", ";
            else sql += " VL_Debito = null,";
            sql += " DT_STAMP = '" + ed.getDt_stamp() + "', ";
            sql += " USUARIO_STAMP = '" + ed.getUsuario_Stamp() + "',";
            sql += " DM_STAMP = '" + ed.getDm_Stamp() + "' ";
            sql += " where oid_Movimento_Duplicata = '" + ed.getOid_Movimento_Duplicata() + "'";

            executasql.executarUpdate(sql);
        } catch (SQLException e) {
            throw new Excecoes(e.getMessage(), e, getClass().getName(), "altera(Movimento_DuplicataED ed)");
        }
    }

    public void confere(Movimento_DuplicataPesquisaED ed)
    throws Excecoes {

        String sql = null;

        try {

            sql = "select Movimentos_Duplicatas.oid_duplicata, Movimentos_Duplicatas.vl_credito from Movimentos_Duplicatas, duplicatas, tipos_instrucoes where "
                    + " movimentos_Duplicatas.oid_duplicata = duplicatas.oid_duplicata and " + " Movimentos_Duplicatas.oid_tipo_instrucao = tipos_instrucoes.oid_tipo_instrucao ";

            if (ed.getDt_Pgto_Inicial() != null)
                sql += " and movimentos_duplicatas.DT_Movimento >= '" + ed.getDt_Pgto_Inicial() + "'";

            if (ed.getDt_Pgto_Final() != null)
                sql += " and movimentos_duplicatas.DT_Movimento <= '" + ed.getDt_Pgto_Final() + "'";

            sql += " and movimentos_duplicatas.oid_tipo_instrucao in (6,20,43) ";
            sql += " ORDER BY movimentos_duplicatas.oid_duplicata,  movimentos_duplicatas.DT_Movimento, movimentos_duplicatas.vl_credito ";

            ResultSet res = this.executasql.executarConsulta(sql);

            long oid_duplicata = 999999;
            double vl_credito = 0;
            //popula
            while (res.next()) {
                // res.getLong("oid_duplicata"));


                if (oid_duplicata != res.getLong("oid_duplicata")) {

                    if (oid_duplicata != 999999 && vl_credito > 0) {
                        sql = " update Duplicatas set vl_pago= " + vl_credito;
                        sql += " where oid_Duplicata = " + oid_duplicata;
                        executasql.executarUpdate(sql);
                    }
                    oid_duplicata = res.getLong("oid_duplicata");
                    vl_credito = res.getDouble("vl_credito");
                } else {
                    vl_credito += res.getDouble("vl_credito");
                }
            }
        } catch (SQLException e) {
            throw new Excecoes(e.getMessage(), e, getClass().getName(), "confere(Movimento_DuplicataPesquisaED ed)");
        }
    }

    public void deleta(Movimento_DuplicataED ed) throws Excecoes {

        String sql = null;
        try {

            sql = "DELETE FROM Movimentos_Duplicatas  " +
                  " WHERE oid_Movimento_Duplicata = '" + ed.getOid_Movimento_Duplicata() + "'";

            executasql.executarUpdate(sql);


        } catch (SQLException e) {
            throw new Excecoes(e.getMessage(), e, getClass().getName(), "deleta()");
        }
    }

    public void estorna_Movimento (Movimento_DuplicataED ed) throws Excecoes {

      String sql = null;
      double VL_Credito = 0 , VL_Debito = 0 , VL_Saldo = 0;
      try {

        sql = " SELECT movimentos_Duplicatas.*, Duplicatas.VL_Saldo  " +
            " FROM   movimentos_Duplicatas, Duplicatas " +
            " WHERE  movimentos_Duplicatas.oid_Duplicata = Duplicatas.oid_Duplicata " +
            " AND    movimentos_Duplicatas.oid_Movimento_Duplicata = '" + ed.getOid_Movimento_Duplicata () + "'";

        // System.out.println (sql);

        ResultSet res = null;
        res = this.executasql.executarConsulta (sql);

        //popula
        if (res.next ()) {
          VL_Saldo = res.getDouble ("VL_Saldo");
          VL_Credito = res.getDouble ("VL_Credito");
          VL_Debito = res.getDouble ("VL_Debito");

        // System.out.println ("Vai estornar");

          sql = " INSERT INTO Movimentos_Duplicatas (" +
              "      OID_Movimento_Duplicata" +
              "     ,DT_Movimento" +
              "     ,NR_Sequencia_Duplicata" +
              "     ,VL_Credito" +
              "     ,VL_Debito" +
              "     ,OID_Duplicata" +
              "     ,OID_Tipo_Instrucao" +
              "     ,OID_Conta" +
              "     ,OID_Carteira" +
              "     ,dm_estornado)" +
              " VALUES ('" + String.valueOf (System.currentTimeMillis ()).toString () + "',";
          sql += "'" + Data.getDataDMY () + "',";
          sql += "99,";
          sql += VL_Debito + ",";
          sql += VL_Credito + ",";
          sql += res.getString ("oid_Duplicata") + ",";
          sql += parametro_FixoED.getOID_Tipo_Instrucao_Estorno () + ",";
          sql += res.getString ("OID_Conta") + ",";
          sql += res.getString ("OID_Carteira") + ",";
          sql += "'S')";

          // System.out.println (sql);
          executasql.executarUpdate (sql);

          VL_Saldo = VL_Saldo + VL_Credito - VL_Debito;

          sql = " UPDATE Duplicatas  " +
              " SET VL_Saldo = " + VL_Saldo +
              " WHERE oid_Duplicata = '" + res.getString ("oid_Duplicata") + "'";
          // System.out.println (sql);
          executasql.executarUpdate (sql);

          sql = " UPDATE Movimentos_Duplicatas  " +
                " SET DM_Estornado = 'S' " +
                " WHERE oid_Duplicata = '" + res.getString ("oid_Duplicata") + "'" +
                " AND   oid_Bordero = " + res.getInt ("oid_Bordero") ;
          // System.out.println (sql);
          executasql.executarUpdate (sql);

        }

      }
      catch (SQLException e) {
        throw new Excecoes (e.getMessage () , e , getClass ().getName () , "deleta()");
      }
    }


    public Movimento_DuplicataED getByRecord(Movimento_DuplicataED ed) throws Excecoes {

        String sql = null;
        Movimento_DuplicataED edVolta = new Movimento_DuplicataED();

        try {

            sql = "select movimentos_Duplicatas.DM_Estornado as DM_Est_Mov,  pessoas.nm_razao_social, duplicatas.nr_parcela, duplicatas.oid_duplicata, tipos_documentos.nm_tipo_documento, tipos_instrucoes.CD_Tipo_Instrucao, tipos_instrucoes.NM_Tipo_Instrucao, tipos_instrucoes.DM_Gera_Movimento, Carteiras.CD_Carteira, Contas_Correntes.nr_Conta_Corrente, Movimentos_Duplicatas.DT_Movimento, Movimentos_Duplicatas.VL_Credito, duplicatas.nr_duplicata, duplicatas.vl_saldo, duplicatas.nr_documento, Movimentos_Duplicatas.VL_Debito, Movimentos_Duplicatas.oid_Movimento_Duplicata, Movimentos_Duplicatas.oid_Bordero , Pessoas_Bancos.nm_razao_Social as NM_Razao_Banco from Movimentos_Duplicatas, duplicatas, pessoas, tipos_documentos, tipos_instrucoes, Pessoas Pessoas_Bancos, Carteiras, Contas_Correntes where "
                    + " movimentos_Duplicatas.oid_duplicata = duplicatas.oid_duplicata and "
                    + " Movimentos_Duplicatas.oid_tipo_instrucao = tipos_instrucoes.oid_tipo_instrucao and "
                    + " Movimentos_Duplicatas.oid_carteira = carteiras.oid_carteira and "
                    + " duplicatas.oid_tipo_documento = tipos_documentos.oid_tipo_documento and "
                    + " duplicatas.oid_pessoa = pessoas.oid_pessoa and"
                    + " carteiras.oid_conta_corrente = contas_correntes.oid_conta_corrente and "
                    + " contas_correntes.oid_pessoa = pessoas_bancos.oid_pessoa ";

            if (ed.getOid_Movimento_Duplicata() != null)
                sql += " and movimentos_Duplicatas.oid_Movimento_Duplicata = '" + ed.getOid_Movimento_Duplicata() + "'";

            ResultSet res = null;
            res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
                //popular os dados de movimento_Duplicata para voltar

                edVolta.setOID_Duplicata(res.getString("OID_Duplicata"));
                edVolta.setNm_Razao_Social(res.getString("nm_razao_social"));
                edVolta.setNr_Parcela(new Integer(res.getInt("nr_parcela")));
                edVolta.setNm_Tipo_Documento(res.getString("nm_tipo_documento"));

                edVolta.setCD_Tipo_Instrucao(res.getString("CD_Tipo_Instrucao"));
                edVolta.setNM_Tipo_Instrucao(res.getString("NM_Tipo_Instrucao"));
                edVolta.setDM_Gera_Movimento(res.getString("DM_Gera_Movimento"));

                edVolta.setDM_Estornado(res.getString("DM_Est_Mov"));

                edVolta.setCd_Carteira(res.getString("CD_Carteira"));
                edVolta.setCD_Conta_Corrente(res.getString("nr_Conta_Corrente"));
                edVolta.setNM_Banco(res.getString("NM_Razao_Banco"));

                FormataDataBean DataFormatada = new FormataDataBean();
                DataFormatada.setDT_FormataData(res.getString("DT_Movimento"));
                edVolta.setDT_Movimento(DataFormatada.getDT_FormataData());

                edVolta.setVL_Credito(new Double(res.getDouble("VL_Credito")));
                edVolta.setNr_Duplicata(new Integer(res.getInt("nr_duplicata")));
                edVolta.setVl_Saldo(new Double(res.getDouble("vl_saldo")));
                edVolta.setNr_Documento(res.getString("nr_documento"));

                String VL_Debito = res.getString("VL_Debito");
                if (VL_Debito != null)
                    edVolta.setVL_Debito(new Double(VL_Debito));

                edVolta.setOid_Movimento_Duplicata(res.getString("oid_Movimento_Duplicata"));
                edVolta.setOid_Bordero(new Integer(res.getInt("oid_Bordero")));

            }
        } catch (SQLException e) {
            throw new Excecoes(e.getMessage(), e, getClass().getName(), "getByRecord()");
        }
        return edVolta;
    }

    public ArrayList lista(Movimento_DuplicataED edComp) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();
        Movimento_DuplicataPesquisaED ed = (Movimento_DuplicataPesquisaED) edComp;

        try {
            sql = "select * from Movimentos_Duplicatas, duplicatas, pessoas, tipos_instrucoes where " + " movimentos_Duplicatas.oid_duplicata = duplicatas.oid_duplicata and "
                    + " Movimentos_Duplicatas.oid_tipo_instrucao = tipos_instrucoes.oid_tipo_instrucao and " + " duplicatas.oid_pessoa = pessoas.oid_pessoa ";

            if (JavaUtil.doValida(ed.getOid_Pessoa()))
                sql += " and duplicatas.oid_pessoa = '" + ed.getOid_Pessoa() + "'";

            if (ed.getNr_Documento() != null)
                sql += " and duplicatas.Nr_Documento = " + JavaUtil.getSQLString(ed.getNr_Documento());

            if (JavaUtil.doValida(String.valueOf(ed.getOid_Duplicata())))
                sql += " and movimentos_duplicatas.oid_duplicata = '" + ed.getOid_Duplicata() + "'";

            if (ed.getOid_Bordero() != null)
                sql += " and movimentos_duplicatas.oid_Bordero = " + ed.getOid_Bordero();

            if (JavaUtil.doValida(ed.getDt_Pgto_Inicial()))
                sql += " and movimentos_duplicatas.DT_Movimento >= '" + ed.getDt_Pgto_Inicial() + "'";

            if (JavaUtil.doValida(ed.getDt_Pgto_Final()))
                sql += " and movimentos_duplicatas.DT_Movimento <= '" + ed.getDt_Pgto_Final() + "'";

            sql += " ORDER BY movimentos_duplicatas.DT_Movimento, movimentos_duplicatas.oid_movimento_duplicata ";

            ResultSet res = null;
            res = this.executasql.executarConsulta(sql);


            //popula
            while (res.next()) {
                Movimento_DuplicataPesquisaED edVolta = new Movimento_DuplicataPesquisaED();

                edVolta.setNm_Razao_Social(res.getString("nm_razao_social"));
                edVolta.setNr_Parcela(new Integer(res.getInt("nr_parcela")));
                edVolta.setNM_Tipo_Instrucao(res.getString("NM_Tipo_Instrucao"));

                FormataDataBean DataFormatada = new FormataDataBean();
                DataFormatada.setDT_FormataData(res.getString("DT_Movimento"));
                edVolta.setDT_Movimento(DataFormatada.getDT_FormataData());

                edVolta.setVL_Credito(new Double(res.getDouble("VL_Credito")));
                edVolta.setVL_Debito(new Double(res.getDouble("VL_Debito")));
                edVolta.setVL_Variacao_Cambial(res.getDouble("VL_Variacao_Cambial"));

                edVolta.setNr_Duplicata(new Integer(res.getInt("nr_duplicata")));
                edVolta.setNr_Documento(res.getString("NR_Documento"));
                edVolta.setVl_Saldo(new Double(res.getDouble("vl_saldo")));
                edVolta.setOid_Entrega(res.getInt("oid_Entrega"));
                edVolta.setOid_Movimento_Duplicata(res.getString("oid_Movimento_Duplicata"));
                if (res.getString("oid_Bordero") != null && !res.getString("oid_Bordero").equals("null")) {
                    edVolta.setOid_Bordero(new Integer(res.getInt("oid_Bordero")));
                }

                list.add(edVolta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Excecoes(e.getMessage(), e, getClass().getName(), "lista()");
        }
        return list;
    }

    public Movimento_DuplicataED consultaMovimento (long oid_Duplicata) throws Excecoes {

      String sql = null;
      Movimento_DuplicataED edVolta = new Movimento_DuplicataED ();
        // System.out.println("consultaMovimento");

      try {

        sql = " SELECT  Movimentos_Duplicatas.DM_Principal, Tipos_Instrucoes.DM_Gera_Movimento, Movimentos_Duplicatas.VL_Credito, Movimentos_Duplicatas.VL_Debito " +
            "  FROM  Movimentos_Duplicatas, Tipos_instrucoes " +
            "  WHERE Movimentos_Duplicatas.oid_tipo_instrucao = Tipos_Instrucoes.oid_tipo_instrucao  " +
            "  AND   Movimentos_Duplicatas.oid_Duplicata = '" + oid_Duplicata + "'";

        // System.out.println(sql);

        ResultSet res = this.executasql.executarConsulta (sql);
        double VL_Credito=0;
        double VL_Pago=0;
        double VL_Debito=0;
        //popula
        while (res.next ()) {
          // System.out.println("consultaMovimento w1");

          VL_Credito+=res.getDouble ("VL_Credito");
          VL_Debito+=res.getDouble ("VL_Debito");
          if ("S".equals(res.getString("DM_Principal"))){
            VL_Pago+=res.getDouble ("VL_Credito");
          }
        }

          // System.out.println("consultaMovimento w99");

        edVolta.setVL_Credito (new Double (VL_Credito));
        edVolta.setVL_Debito (new Double (VL_Debito));
        edVolta.setVL_Pago (VL_Pago);

      }
      catch (SQLException e) {
        throw new Excecoes (e.getMessage () , e , getClass ().getName () , "getByRecord()");
      }
      return edVolta;
    }


    public void geraRelatorio(Movimento_DuplicataED ed) throws Excecoes {

        //    String sql = null;
        //
        //    Movimento_DuplicataED edVolta = new Movimento_DuplicataED();
        //
        //    try{
        //
        //      sql = "select * from Movimento_Duplicatas where
        // oid_Movimento_Duplicata > 0";
        //
        //      if (ed.getCD_Movimento_Duplicata() != null &&
        // !ed.getCD_Movimento_Duplicata().equals("")){
        //        sql += " and CD_Movimento_Duplicata = '" +
        // ed.getCD_Movimento_Duplicata() + "'";
        //      }
        //      if (ed.getCD_Remessa() != null && !ed.getCD_Remessa().equals("")){
        //        sql += " and CD_Remessa = '" + ed.getCD_Remessa() + "'";
        //      }
        //
        //      ResultSet res = null;
        //      res = this.executasql.executarConsulta(sql);
        //
        //      Movimento_DuplicataRL Movimento_Duplicata_rl = new
        // Movimento_DuplicataRL();
        //      Movimento_Duplicata_rl.geraRelatEstoque(res);
        //    }
        //    catch (Excecoes e){
        //      throw e;
        //    }
        //    catch(Exception exc){
        //      Excecoes exce = new Excecoes();
        //      exce.setExc(exc);
        //      exce.setMensagem("Erro no método listar");
        //      exce.setClasse(this.getClass().getName());
        //      exce.setMetodo("geraRelatorio(Movimento_DuplicataED ed)");
        //    }
        //
    }
}
