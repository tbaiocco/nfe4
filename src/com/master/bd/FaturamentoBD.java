package com.master.bd;

import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import com.master.ed.DuplicataED;
import com.master.ed.FaturamentoED;
import com.master.rl.FaturamentoRL;
import com.master.root.DateFormatter;
import com.master.root.FormataDataBean;
import com.master.util.*;
import com.master.util.bd.ExecutaSQL;
import com.master.util.bd.Transacao;
import com.master.util.ed.Parametro_FixoED;

public class FaturamentoBD
    extends Transacao {

  private ExecutaSQL executasql;
  Utilitaria util = new Utilitaria (executasql);
  private  long oid_AIDOF = 0;
  private  long nr_Primeira_Fatura_Gerada = 0;
  FormataValor formataValor = new FormataValor();
  FormataDataBean DataFormatadata = new FormataDataBean ();

  public FaturamentoBD (ExecutaSQL sql) {
    this.executasql = sql;
    util = new Utilitaria (this.executasql);
  }


  public FaturamentoED geraFatura (FaturamentoED ed) throws Excecoes {

    // .println ("Faturamento BD geraFatura ");

    String sql = null;
    String sql_Filtro = null;
    String chave = null;
    long NR_Duplicata = 999999999;
    ResultSet rs = null;
    boolean DM_Valor_Limite_Fatura = true;
    double VL_Faturamento_Geral = 0;
    double PE_Desconto_Vencimento=0;
    double VL_Desconto_Vencimento=0;
    int nr_maximo_cto_fatura = 0;
    int nr_cto_fatura = 0;
    String vOID_Pagador_Inicial = "";
    String vOID_Pagador_Final = "";
    String DM_Faturamento = "FILTRO";
    double PC_Juro = 0;
    String vDM_Quebra_Faturamento_Inicial = "NAO_QUEBRA";
    String vDM_Quebra_Faturamento_Final = "";
    double VL_Fatura_Acumulado = 0;
    double VL_Fatura_Acumulado_Geral = 0;
    double Vl_Maximo_Fatura = 0;

    String SHORT_DATE = "dd/MM/yyyy";
    Parametro_FixoED parametro_FixoED = new Parametro_FixoED ();

    // .println ("Faturamento BD 1  ");

    try {
      ResultSet rsTP = null;

      if (ed.getOID_Unidade () <= 0) {
        ed.setOID_Unidade (parametro_FixoED.getOID_Unidade_Faturamento ());
      }

      // .println ("Faturamento BD 1 b ");

      ed.setOid_Tipo_Documento (parametro_FixoED.getOID_Tipo_Documento_Faturamento ());

      // .println ("Faturamento BD 1 c ");

      if ("M".equals (ed.getDM_Tipo_Documento ())) { // MINUTAS
        // .println ("Passei Faturamento 12 ..... ");
        ed.setOid_Tipo_Documento (parametro_FixoED.getOID_Tipo_Documento_Faturamento_Nota_Fiscal_Servico ());
        // .println ("Passei Faturamento 13 ..... ");
        if (parametro_FixoED.getOID_Unidade_Faturamento_Minuta () > 0) {
          ed.setOID_Unidade (parametro_FixoED.getOID_Unidade_Faturamento_Minuta ()); // ver comportamento, joga param fixo oid unidade minuta
        }
        // .println ("Passei Faturamento 14 ..... ");
      }

      ed.setOid_Carteira (ed.getOID_Carteira_Informada ());

      // .println ("Faturamento BD 3 ..... ");

      sql =
          " SELECT Clientes.OID_Carteira " +
          "       ,Clientes.NR_Dias_Vencimento " +
          "       ,Clientes.VL_Taxa_Cobranca " +
          "       ,Clientes.PE_Desconto_Vencimento " +
          "       ,Clientes.DM_Compr_Entrega_Fatura " +
          "       ,Clientes.nr_maximo_cto_fatura " +
          " FROM Unidades, Clientes, Carteiras " +
          " WHERE Unidades.OID_Unidade = " + ed.getOID_Unidade () +
          "   AND Unidades.oid_Pessoa = Clientes.oid_Cliente " +
          "   AND Clientes.oid_Carteira = Carteiras.oid_Carteira";

      rs = null;
      rsTP = null;
      rsTP = this.executasql.executarConsulta (sql);
      while (rsTP.next ()) {
        if (ed.getOID_Carteira_Informada () <= 0) {
          ed.setOid_Carteira (rsTP.getLong ("OID_Carteira"));
        }
        ed.setNR_Dias_Vencimento (rsTP.getInt ("NR_Dias_Vencimento"));
        ed.setVL_Taxa_Cobranca (rsTP.getDouble ("VL_Taxa_Cobranca"));
        PE_Desconto_Vencimento=rsTP.getDouble ("PE_Desconto_Vencimento");
        nr_maximo_cto_fatura = rsTP.getInt ("nr_maximo_cto_fatura");
      }

      // .println ("Faturamento BD 5 ..... ");

      sql =
          " SELECT Carteiras.OID_Carteira " +
          "       ,Carteiras.PE_Juros " +
          "       ,Carteiras.PE_Multa " +
          " FROM Carteiras " +
          " WHERE Carteiras.oid_Carteira = " + ed.getOid_Carteira ();

      rsTP = this.executasql.executarConsulta (sql);
      while (rsTP.next ()) {
        ed.setVL_Juro_Mora_Dia (new Double (rsTP.getDouble ("PE_Juros")).doubleValue ());
        PC_Juro = rsTP.getDouble ("PE_Juros");
        ed.setVL_Multa (new Double (rsTP.getDouble ("PE_Multa")).doubleValue ());
      }

      // .println ("Faturamento BD 8 ..... ");

      sql = " SELECT * FROM Conhecimentos, Clientes, Unidades " +
          " WHERE Conhecimentos.OID_Unidade        = Unidades.OID_Unidade " +
          " AND   Conhecimentos.OID_Pessoa_Pagador = Clientes.OID_Cliente ";

      if (util.doValida (ed.getNM_Lote_Faturamento ()) && !"T".equals (ed.getNM_Lote_Faturamento ())) {
        sql_Filtro = " AND Conhecimentos.NM_Lote_Faturamento = '" + ed.getNM_Lote_Faturamento () + "'";
        DM_Faturamento = "LOTE";
      }
      else {
        sql_Filtro = " AND Conhecimentos.VL_Total_Frete > 0 " +
            " AND Unidades.DM_Faturar = 'S' " +
            " AND Conhecimentos.DM_Situacao = 'G' " +
            " AND Conhecimentos.DM_Impresso = 'S' ";

        if (String.valueOf (ed.getOID_Unidade_CTRC ()) != null &&
            !String.valueOf (ed.getOID_Unidade_CTRC ()).equals ("0")) {
          sql_Filtro += " and Conhecimentos.OID_Unidade = " + ed.getOID_Unidade_CTRC ();
        }
        if (String.valueOf (ed.getOID_Produto ()) != null &&
            !String.valueOf (ed.getOID_Produto ()).equals ("0") &&
            !String.valueOf (ed.getOID_Produto ()).equals ("null")) {
          sql_Filtro += " and Conhecimentos.OID_Produto = " + ed.getOID_Produto ();
        }
        if (String.valueOf (ed.getOID_Centro_Custo ()) != null &&
            !String.valueOf (ed.getOID_Centro_Custo ()).equals ("0") &&
            !String.valueOf (ed.getOID_Centro_Custo ()).equals ("null")) {
          sql_Filtro += " and Conhecimentos.OID_Centro_Custo = " + ed.getOID_Centro_Custo ();
        }
        if (String.valueOf (ed.getDM_Tipo_Conhecimento ()) != null &&
            !String.valueOf (ed.getDM_Tipo_Conhecimento ()).equals ("T") &&
            !String.valueOf (ed.getDM_Tipo_Conhecimento ()).equals ("null")) {
          sql_Filtro += " and Conhecimentos.DM_Tipo_Conhecimento = '" + ed.getDM_Tipo_Conhecimento () + "'";
        }
        if (String.valueOf (ed.getDM_Tipo_Documento ()) != null &&
            !String.valueOf (ed.getDM_Tipo_Documento ()).equals ("T") &&
            !String.valueOf (ed.getDM_Tipo_Documento ()).equals ("null")) {
          sql_Filtro += " and Conhecimentos.DM_Tipo_Documento = '" + ed.getDM_Tipo_Documento () + "'";
        }
        if (String.valueOf (ed.getDM_Tipo_Faturamento ()) != null &&
            !String.valueOf (ed.getDM_Tipo_Faturamento ()).equals ("T") &&
            !String.valueOf (ed.getDM_Tipo_Faturamento ()).equals ("null")) {
          sql_Filtro += " and Clientes.DM_Tipo_Faturamento = '" + ed.getDM_Tipo_Faturamento () + "'";
        }

        if (String.valueOf (ed.getOID_Pessoa_Pagador ()) != null &&
            !String.valueOf (ed.getOID_Pessoa_Pagador ()).equals ("") &&
            !String.valueOf (ed.getOID_Pessoa_Pagador ()).equals ("null")) {
          sql_Filtro += " and Conhecimentos.OID_Pessoa_Pagador = '" + ed.getOID_Pessoa_Pagador () + "'";
        }

        if (String.valueOf (ed.getOID_Pessoa_Remetente ()) != null &&
            !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("") &&
            !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("null")) {
          sql_Filtro += " and Conhecimentos.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario () + "'";
        }
        if (String.valueOf (ed.getOID_Pessoa_Remetente ()) != null &&
                !String.valueOf (ed.getOID_Pessoa_Remetente ()).equals ("") &&
                !String.valueOf (ed.getOID_Pessoa_Remetente ()).equals ("null")) {
              sql_Filtro += " and Conhecimentos.OID_Pessoa = '" + ed.getOID_Pessoa_Remetente () + "'";
            }

        if (String.valueOf (ed.getDM_Tipo_Pagador ()) != null &&
            !String.valueOf (ed.getDM_Tipo_Pagador ()).equals ("") &&
            !String.valueOf (ed.getDM_Tipo_Pagador ()).equals ("T") &&
            !String.valueOf (ed.getDM_Tipo_Pagador ()).equals ("null") &&
            String.valueOf (ed.getOID_Pessoa_Pagador ()) != null &&
            !String.valueOf (ed.getOID_Pessoa_Pagador ()).equals ("") &&
            !String.valueOf (ed.getOID_Pessoa_Pagador ()).equals ("null")) {

          if (String.valueOf (ed.getDM_Tipo_Pagador ()).equals ("R")) {
            sql_Filtro += " and Conhecimentos.OID_Pessoa = '" + ed.getOID_Pessoa_Pagador () + "'";
          }
          else if (String.valueOf (ed.getDM_Tipo_Pagador ()).equals ("D")) {
            sql_Filtro += " and Conhecimentos.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Pagador () + "'";
          }
          else if (String.valueOf (ed.getDM_Tipo_Pagador ()).equals ("C")) {
            sql_Filtro += " and Conhecimentos.OID_Pessoa_Consignatario = '" + ed.getOID_Pessoa_Pagador () + "'";
          }
        }

        // .println ("Faturamento BD 20 ..... ");

        if (String.valueOf (ed.getDT_Emissao_Inicial ()) != null &&
            !String.valueOf (ed.getDT_Emissao_Inicial ()).equals ("") &&
            String.valueOf (ed.getDT_Emissao_Final ()) != null &&
            !String.valueOf (ed.getDT_Emissao_Final ()).equals ("")) {
          sql_Filtro += " AND Conhecimentos.DT_Emissao >= '" + ed.getDT_Emissao_Inicial () + "'";
          sql_Filtro += " AND Conhecimentos.DT_Emissao <= '" + ed.getDT_Emissao_Final () + "'";
        }

        if (String.valueOf (ed.getNR_Conhecimento_Inicial ()) != null &&
            !String.valueOf (ed.getNR_Conhecimento_Inicial ()).equals ("0") &&
            String.valueOf (ed.getNR_Conhecimento_Final ()) != null &&
            !String.valueOf (ed.getNR_Conhecimento_Final ()).equals ("0")) {
          sql_Filtro += " AND Conhecimentos.NR_Conhecimento >= " + ed.getNR_Conhecimento_Inicial () + "";
          sql_Filtro += " AND Conhecimentos.NR_Conhecimento <= " + ed.getNR_Conhecimento_Final () + "";
        }
        else if (JavaUtil.doValida (ed.getNR_Conhecimento_Sequencia ())) {
          sql_Filtro += " AND Conhecimentos.NR_Conhecimento in (" + ed.getNR_Conhecimento_Sequencia () + ")";
        }
        if (util.doValida (ed.getDM_Tipo_Cobrancao ())) {
          sql_Filtro += " AND Conhecimentos.DM_Tipo_Cobranca = '" + ed.getDM_Tipo_Cobrancao () + "'";
        }

      }

      sql += sql_Filtro + " Order by Conhecimentos.OID_Pessoa_Pagador, Conhecimentos.DM_Tipo_Conhecimento, Conhecimentos.DT_Emissao DESC, Conhecimentos.NR_Conhecimento ";
      // .println ("fat mt SQL " + sql);
      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);

      while (res.next ()) {

        ed.setOID_Conhecimento (res.getString ("OID_Conhecimento"));

        sql = " select oid_Vendedor from Clientes Where oid_Cliente='" + res.getString ("OID_Pessoa_Pagador") + "'";

        rs = this.executasql.executarConsulta (sql);
        if (rs.next ()) {
          ed.setOID_Vendedor (rs.getString ("OID_Vendedor"));
        }
        int nr_dias_vencimento=res.getInt ("NR_Dias_Vencimento");
        int nr_dias_vencimento2=0;
        int nr_dias_vencimento3=0;
        int nr_dias_vencimento4=0;


        if (res.getLong("oid_Programacao_Carga")>0) {
            sql = " SELECT  Pedidos_Cargas.DM_Pagamento, Pedidos_Cargas.DM_Pagamento2, Pedidos_Cargas.DM_Pagamento3, Pedidos_Cargas.DM_Pagamento4  " +
            	  " FROM  Pedidos_Cargas, Programacoes_Cargas " +
            	  " WHERE Pedidos_Cargas.oid_Pedido_Carga = Programacoes_Cargas.oid_Pedido_Carga " +
            	  " AND   Programacoes_Cargas.oid_Programacao_Carga=" + res.getLong("oid_Programacao_Carga");
            //.println (sql);

            rs = this.executasql.executarConsulta (sql);
            if (rs.next ()) {
            	if (rs.getString("DM_Pagamento")!=null && !rs.getString("DM_Pagamento").equals("null")) {
            		nr_dias_vencimento=rs.getInt ("DM_Pagamento");
            	}
            	if (rs.getString("DM_Pagamento2")!=null && !rs.getString("DM_Pagamento2").equals("null")) {
            		nr_dias_vencimento2=rs.getInt ("DM_Pagamento2");
            	}
            	if (rs.getString("DM_Pagamento3")!=null && !rs.getString("DM_Pagamento3").equals("null")) {
            		nr_dias_vencimento3=rs.getInt ("DM_Pagamento3");
            	}
            	if (rs.getString("DM_Pagamento4")!=null && !rs.getString("DM_Pagamento4").equals("null")) {
            		nr_dias_vencimento4=rs.getInt ("DM_Pagamento4");
            	}
            }
        }
        //.println ("nr_dias_vencimento dEPOIS ->>" + nr_dias_vencimento);

        ed.setNR_Dias_Vencimento (nr_dias_vencimento);

        ed.setOID_Pessoa_Pagador (res.getString ("OID_Pessoa_Pagador"));
        ed.setVL_Taxa_Cobranca (res.getDouble ("VL_Taxa_Cobranca"));
        PE_Desconto_Vencimento=res.getDouble ("PE_Desconto_Vencimento");

        if (ed.getOID_Carteira_Informada () <= 0) {
          ed.setOid_Carteira (res.getLong ("OID_Carteira"));
        }

        // .println ("fat mNR_Dias_Vencimento ->>" + res.getLong ("NR_Dias_Vencimento"));

        vOID_Pagador_Inicial = ed.getOID_Pessoa_Pagador ();

        if ("FILTRO".equals (DM_Faturamento) && parametro_FixoED.getDM_Quebra_Faturamento_Tipo_Conhecimento ().equals ("S")) {
          vDM_Quebra_Faturamento_Inicial = res.getString ("DM_Tipo_Conhecimento");
        }

        Vl_Maximo_Fatura = res.getDouble ("Vl_Maximo_Fatura");
        nr_maximo_cto_fatura = res.getInt ("nr_maximo_cto_fatura");

        if ("LOTE".equals (DM_Faturamento)) {
          nr_maximo_cto_fatura = 999;
          Vl_Maximo_Fatura = 9999999;
        }

        if (!vOID_Pagador_Inicial.equals (vOID_Pagador_Final) || (!vDM_Quebra_Faturamento_Inicial.equals (vDM_Quebra_Faturamento_Final) && !vDM_Quebra_Faturamento_Inicial.equals ("NAO_QUEBRA"))) {

          vOID_Pagador_Final = ed.getOID_Pessoa_Pagador ();

          if (parametro_FixoED.getDM_Quebra_Faturamento_Tipo_Conhecimento ().equals ("S")) {
            vDM_Quebra_Faturamento_Final = res.getString ("DM_Tipo_Conhecimento");
          }

          if (ed.getVL_Fatura () > 0 || Vl_Maximo_Fatura > 0 || nr_maximo_cto_fatura > 0) {

            if (ed.getVL_Fatura () > 0 || Vl_Maximo_Fatura == 0) {
              Vl_Maximo_Fatura = 999999999;
            }
            if (ed.getVL_Fatura () == 0 || Vl_Maximo_Fatura > 0) {
              ed.setVL_Fatura (999999999);
            }

            DM_Valor_Limite_Fatura = true;
            while (DM_Valor_Limite_Fatura) {

              ed.setVL_Duplicata (0);
              ed.setDT_Emissao_Ctrc(res.getString ("DT_Emissao"));
    		  ed.setNR_Parcela(1);

              ed = incluiFatura (ed, null);
              if (nr_Primeira_Fatura_Gerada==0) nr_Primeira_Fatura_Gerada = ed.getNR_Fatura();

              sql = " SELECT OID_Conhecimento, VL_Total_Frete, conhecimentos.dm_tipo_cobranca " +
                  " FROM  Conhecimentos, Clientes, Unidades " +
                  " WHERE Conhecimentos.OID_Unidade = Unidades.OID_Unidade " +
                  " AND   Conhecimentos.OID_Pessoa_Pagador = Clientes.OID_Cliente " +
                  " AND   Conhecimentos.OID_Pessoa_Pagador = '" + ed.getOID_Pessoa_Pagador () + "'";
              //sql += sql_Filtro + " ORDER BY VL_Total_Frete";

              // .println ("fat mt 29 ->>>" + res.getString ("DM_Compr_Entrega_Fatura"));

              if ("S".equals (res.getString ("DM_Compr_Entrega_Fatura"))) {
                sql += " AND   Conhecimentos.DT_Entrega is not null ";
              }

              sql += sql_Filtro + " ORDER BY NR_Conhecimento ";

              // .println ("fat mt 30 ->>>" + sql);

              VL_Fatura_Acumulado = 0;

              rs = null;
              rs = this.executasql.executarConsulta (sql);
              DM_Valor_Limite_Fatura = false;
              VL_Fatura_Acumulado_Geral = VL_Faturamento_Geral;
              nr_cto_fatura = 0;
              if (nr_maximo_cto_fatura == 0) {
                nr_maximo_cto_fatura = 1;
              }

              while (rs.next ()) {
                // .println ("fat mt 35 Vl_Maximo_Fatura->>" + Vl_Maximo_Fatura);
                // .println ("fat mt 35 nr_maximo_cto_fatura->>" + nr_maximo_cto_fatura);

                VL_Fatura_Acumulado = VL_Fatura_Acumulado + rs.getDouble ("VL_Total_Frete");
                VL_Fatura_Acumulado_Geral = VL_Fatura_Acumulado_Geral + rs.getDouble ("VL_Total_Frete");
                if (ed.getVL_Fatura () >= VL_Fatura_Acumulado_Geral) {
                  if (VL_Fatura_Acumulado <= Vl_Maximo_Fatura) {
                    if (nr_cto_fatura < nr_maximo_cto_fatura) {

                      DM_Valor_Limite_Fatura = true;
                      ed.setVL_Duplicata (VL_Fatura_Acumulado);

                      VL_Faturamento_Geral = VL_Faturamento_Geral + VL_Fatura_Acumulado;

                      nr_cto_fatura = nr_cto_fatura + 1;

                      //JUROS DIA
                      ed.setVL_Juro_Mora_Dia (ed.getVL_Duplicata () * (PC_Juro / 100 / 30)); //informar na carteira o % ao MES
                      VL_Desconto_Vencimento = ed.getVL_Duplicata ()* PE_Desconto_Vencimento /100;
                      sql = " UPDATE Duplicatas SET VL_Duplicata = " + ed.getVL_Duplicata () + ", VL_Saldo=" + ed.getVL_Duplicata () + ", VL_Desconto_Vencimento=" + VL_Desconto_Vencimento +
                          ", dm_tipo_cobranca='" + rs.getString ("dm_tipo_cobranca") + "', vl_juro_mora_dia = " + ed.getVL_Juro_Mora_Dia ();
                      sql += " WHERE OID_Duplicata = '" + ed.getOID_Fatura () + "'";
                      // .println ("fat novo 45.0 - " + sql);
                      executasql.executarUpdate (sql);

                      // .println ("fat novo 45");

                      chave = (ed.getOID_Fatura () + rs.getString ("OID_Conhecimento"));

                      sql = " insert into Origens_Duplicatas (OID_Origem_Duplicata, OID_Conhecimento, OID_Duplicata, DT_Origem_Duplicata, HR_Origem_Duplicata, DM_Situacao ) values ";
                      sql += "('" + chave + "','" + rs.getString ("OID_Conhecimento") + "'," + ed.getOID_Fatura () + ",'" + ed.getDt_stamp () + "','" + ed.getHr_stamp () + "','" + "A" + "')";
                      // .println ("fat Origens_Duplicatas 46 - " + sql);

                      executasql.executarUpdate (sql);

                      sql = " UPDATE Conhecimentos SET DM_Situacao = 'F', " +
                            " NR_Duplicata = " + ed.getNR_Fatura ();
                      sql += " WHERE Conhecimentos.oid_Conhecimento = '" + rs.getString ("OID_Conhecimento") + "'";

                      // .println ("fat Conhecimentos 47 - " + sql);
                      executasql.executarUpdate (sql);

                      if (oid_AIDOF > 0) {
                        sql = " UPDATE AIDOF SET NR_Proximo= " + (ed.getNR_Fatura () + 1);
                        sql += " WHERE OID_AIDOF = " + oid_AIDOF;

                        executasql.executarUpdate (sql);
                        // .println ("fat novo fat ok");
                        oid_AIDOF = 0;
                      }
                    }
                  }
                }

              }
              //nr_dias_vencimento2=60;
              //nr_dias_vencimento3=90;
              //nr_dias_vencimento4=120;

              if (VL_Fatura_Acumulado>0) {
            	  double vl_parcela=0;
            	  double tt_parcelado=0;
            	  double vl_saldo=VL_Fatura_Acumulado;
            	  String oid_Duplicata_Principal=ed.getOID_Fatura();
            	  int qt_parcela=1;
            	  int nr_parcela=1;
            	  if (nr_dias_vencimento2>0)  qt_parcela++;
            	  if (nr_dias_vencimento3>0)  qt_parcela++;
            	  if (nr_dias_vencimento4>0)  qt_parcela++;
            	  if (qt_parcela>1) {
            		  vl_parcela = Valor.getValorArredondado((VL_Fatura_Acumulado/qt_parcela), 2);
            		  if (vl_parcela>0) {

            			  if (nr_dias_vencimento2>0) {
            				  nr_parcela++;
	                		  ed.setNR_Dias_Vencimento(nr_dias_vencimento2);
	                		  ed.setVL_Duplicata(vl_parcela);
	                		  ed.setNR_Parcela(nr_parcela);
	                		  ed.setDT_Vencimento("");
	                		  ed = this.incluiFatura(ed, oid_Duplicata_Principal);

	                	      executasql.executarUpdate (" UPDATE Duplicatas SET " +
	                                                      "  oid_Duplicata_Parcela = " + oid_Duplicata_Principal +
	                                                      " WHERE oid_Duplicata = " + ed.getOID_Fatura ());
	                		  tt_parcelado+=vl_parcela;
            			  }
            			  if (nr_dias_vencimento3>0) {
            				  nr_parcela++;
	                		  ed.setNR_Dias_Vencimento(nr_dias_vencimento3);
	                		  ed.setVL_Duplicata(vl_parcela);
	                		  ed.setNR_Parcela(nr_parcela);
	                		  ed.setDT_Vencimento("");
	                		  ed = this.incluiFatura(ed, oid_Duplicata_Principal);

	                	      executasql.executarUpdate (" UPDATE Duplicatas SET " +
	                                                      "  oid_Duplicata_Parcela = " + oid_Duplicata_Principal +
	                                                      " WHERE oid_Duplicata = " + ed.getOID_Fatura ());
	                		  tt_parcelado+=vl_parcela;
            			  }
            			  if (nr_dias_vencimento4>0) {
            				  nr_parcela++;
	                		  ed.setNR_Dias_Vencimento(nr_dias_vencimento4);
	                		  ed.setVL_Duplicata(vl_parcela);
	                		  ed.setNR_Parcela(nr_parcela);
	                		  ed.setDT_Vencimento("");
	                		  ed = this.incluiFatura(ed, oid_Duplicata_Principal);

	                	      executasql.executarUpdate (" UPDATE Duplicatas SET " +
	                                                      "  oid_Duplicata_Parcela = " + oid_Duplicata_Principal +
	                                                      " WHERE oid_Duplicata = " + ed.getOID_Fatura ());
	                		  tt_parcelado+=vl_parcela;
            			  }
            			  if (tt_parcelado>0) {
	            			  vl_saldo=VL_Fatura_Acumulado-tt_parcelado;


	                          ed.setVL_Juro_Mora_Dia (vl_saldo * (PC_Juro / 100 / 30));
	                          VL_Desconto_Vencimento = vl_saldo * PE_Desconto_Vencimento /100;
	                          sql = " UPDATE Duplicatas SET VL_Duplicata = " + vl_saldo + ", VL_Saldo=" + vl_saldo + ", VL_Desconto_Vencimento=" + VL_Desconto_Vencimento  + ", vl_juro_mora_dia = " + ed.getVL_Juro_Mora_Dia ();
	                          sql += " WHERE OID_Duplicata = '" + oid_Duplicata_Principal + "'";
	                          executasql.executarUpdate (sql);
            			  }
            		  }
            	  }



              }


              if ("LOTE".equals (DM_Faturamento)) {
                DM_Valor_Limite_Fatura = false;
              }

            }
            // .println ("fat mt 40 delete ");

            sql = " DELETE FROM Duplicatas ";
            sql += " WHERE OID_Duplicata = '" + ed.getOID_Fatura () + "'";
            sql += " AND VL_Duplicata = 0";

            executasql.executarUpdate (sql);

          }
          vOID_Pagador_Final = ed.getOID_Pessoa_Pagador ();
          vDM_Quebra_Faturamento_Final = res.getString ("DM_Tipo_Conhecimento");
        }

      }
      // .println ("fat mt 40 qt_faturas COSTARICA->> " + qt_faturas);
      // .println ("fat mt 40 NR_Duplicata COSTARICA->> " + NR_Duplicata);

      ed.setNR_Fatura (nr_Primeira_Fatura_Gerada);

      // .println ("fat mt 40 ed.getNR_Fatura()->> " + ed.getNR_Fatura ());

      return ed;
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "inclui(FaturamentoED ed)");
    }
  }

  public FaturamentoED incluiFatura (FaturamentoED ed, String oid_Duplicata_Principal) throws Excecoes {

	    String sql = null;
	    oid_AIDOF = 0;
	    String chave = null;
	    long NR_Duplicata = 0;
	    ResultSet rs = null;
	    String vDM_Quebra_Faturamento_Inicial = "NAO_QUEBRA";

	    try {

	    	if (ed.getNR_Parcela()==1) {
	              sql = " SELECT AIDOF.NR_Proximo, AIDOF.OID_AIDOF, AIDOF.NM_Serie ";
	              sql += " FROM Parametros_Filiais, AIDOF ";
	              sql += " WHERE Parametros_Filiais.OID_AIDOF_Fatura = AIDOF.OID_AIDOF ";
	              sql += " AND Parametros_Filiais.OID_Unidade = " + ed.getOID_Unidade ();

	              rs = this.executasql.executarConsulta (sql);
	              while (rs.next ()) {
	                ed.setNM_Serie (rs.getString ("NM_Serie"));
	                ed.setNR_Fatura (rs.getLong ("NR_Proximo"));
	                oid_AIDOF = rs.getLong ("OID_AIDOF");
	              }
	    	}
	    	NR_Duplicata = (ed.getNR_Fatura ());  //nnnnnnpp 123401 nnn=nr dup1234 + 01 parcela 1


	        chave = String.valueOf (String.valueOf (NR_Duplicata) + ed.getNM_Serie ());
	        if ("M".equals (ed.getDM_Tipo_Documento ())) { // MINUTAS
	          chave = String.valueOf ("99" + String.valueOf (ed.getNR_Fatura ()) + ed.getNM_Serie ());
	        }

	        ed.setOID_Fatura (chave);

	        if (ed.getDT_Vencimento_Informado () == null || ed.getDT_Vencimento_Informado ().equals ("") || ed.getDT_Vencimento_Informado ().equals ("null")) {
	            DataFormatadata.setDT_FormataData (ed.getDT_Emissao_Ctrc());
	            String DT_Emissao_Ctrc = DataFormatadata.getDT_FormataData ();
	            String DT_Emissao_Fatura = ed.getDT_Emissao ();
	            ed.setDT_Vencimento (this.calcula_Vencimento (ed.getOID_Pessoa_Pagador () , DT_Emissao_Fatura , DT_Emissao_Ctrc, ed.getNR_Dias_Vencimento()));
	        }else {
	            ed.setDT_Vencimento (ed.getDT_Vencimento_Informado ());
	        }

	        if (ed.getDT_Vencimento_Minimo () != null && !ed.getDT_Vencimento_Minimo ().equals ("") && !ed.getDT_Vencimento_Minimo ().equals ("null")) {
	            String data1 = ed.getDT_Vencimento ();
	            String data2 = ed.getDT_Vencimento_Minimo (); // Data.getDataDMY();
	            Calendar cal1 = Data.stringToCalendar (data1 , "dd/MM/yyyy");
	            Calendar cal2 = Data.stringToCalendar (data2 , "dd/MM/yyyy");
	            Data data = new Data ();
	                if (cal2.after (cal1)) {
	                  ed.setDT_Vencimento (ed.getDT_Vencimento_Minimo ());
	                }
	        }

	        if (ed.getDT_Vencimento () == null || ed.getDT_Vencimento ().equals ("") || ed.getDT_Vencimento ().equals ("null")) {
	           ed.setDT_Vencimento (Data.getDataDMY ());
	        }

	        ed.setVL_Desconto_Faturamento (0.0);
	        ed.setVL_Taxa_Refaturamento (0.0);

	        // .println ("fat mt 20 *******************");

	        sql = " insert into Duplicatas " +
	                  "(OID_Duplicata, NR_Documento, NR_Parcela, " +
	                  "DT_Emissao, DT_Vencimento, DT_Credito, DT_Vencimento_Faturamento, VL_Duplicata, " +
	                  "VL_Taxa_Cobranca, " +
	                  "VL_Multa, " +
	                  "VL_Juro_Mora_Dia, " +
	                  "NR_Duplicata, DT_STAMP, USUARIO_STAMP, " +
	                  "DM_STAMP, VL_Desconto_Faturamento, VL_SALDO, " +
	                  "OID_TIPO_DOCUMENTO, OID_Carteira, OID_PESSOA, DM_Quebra_Faturamento, " +
	                  "OID_Vendedor, OID_UNIDADE, NR_REMESSA, DM_Tipo_Documento, DM_Tipo_Inclusao, DM_Situacao, oid_Duplicata_Principal) values " +
	                  "(" + ed.getOID_Fatura () + ",'";
	              sql += ed.getNR_Fatura () + "',";
	              sql += ed.getNR_Parcela() + ",";
	              sql += "'" + ed.getDT_Emissao () + "',";
	              sql += "'" + ed.getDT_Vencimento () + "',";
	              sql += "'" + ed.getDT_Vencimento () + "',";
	              sql += "'" + ed.getDT_Vencimento () + "',";
	              sql += ed.getVL_Duplicata () + ",";
	              sql += ed.getVL_Taxa_Cobranca () + ",";
	              sql += ed.getVL_Multa () + ",";
	              sql += ed.getVL_Juro_Mora_Dia () + ",";
	              sql += NR_Duplicata + ",";
	              sql += "'" + Data.getDataDMY() + "',";
	              sql += "'" + ed.getUsuario_Stamp () + "',";
	              sql += "'" + ed.getDm_Stamp () + "',";
	              sql += ed.getVL_Desconto_Faturamento () + ",";
	              sql += ed.getVL_Duplicata () + ",";
	              sql += ed.getOid_Tipo_Documento () + ",";
	              sql += ed.getOid_Carteira () + ",";
	              sql += "'" + ed.getOID_Pessoa_Pagador () + "','";
	              sql += vDM_Quebra_Faturamento_Inicial + "','";
	              sql += ed.getOID_Vendedor () + "',";
	              sql += ed.getOID_Unidade () + ",0,'1','FATURAMENTO', 'A', " + oid_Duplicata_Principal + ")";

	             executasql.executarUpdate (sql);

	      return ed;
	    }
	    catch (Exception e) {
	      e.printStackTrace ();
	      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "inclui(FaturamentoED ed)");
	    }
}


  public String calcula_Vencimento (String OID_Pessoa_Pagador , String DT_Emissao_Fatura , String DT_Emissao_Ctrc, int nr_dias_vencimento) throws Excecoes {

    String DT_Vencimento = null;
    String SHORT_DATE = "dd/MM/yyyy";
    DateFormatter dateFormatter = new DateFormatter ();
    String dm_Vencimento_Segunda = null;
    String dm_Vencimento_Terca = null;
    String dm_Vencimento_Quarta = null;
    String dm_Vencimento_Quinta = null;
    String dm_Vencimento_Sexta = null;
    String dm_Tipo_Faturamento = null;
    //String NR_Dias =  String.valueOf (nr_dias_vencimento);

    String Dm_Condicao_Vencimento = null;
    String sql = null;
    int nr_dias=0;

    try {

      sql = " SELECT * FROM Clientes WHERE Clientes.OID_Cliente = '" + OID_Pessoa_Pagador + "'";
      //.println ("Vencimento 3 sql = " + sql);

      ResultSet rsTP = this.executasql.executarConsulta (sql);
      if (rsTP.next ()) {
        dm_Vencimento_Segunda = rsTP.getString ("dm_Vencimento_Segunda");
        dm_Vencimento_Terca = rsTP.getString ("dm_Vencimento_Terca");
        dm_Vencimento_Quarta = rsTP.getString ("dm_Vencimento_Quarta");
        dm_Vencimento_Quinta = rsTP.getString ("dm_Vencimento_Quinta");
        dm_Vencimento_Sexta = rsTP.getString ("dm_Vencimento_Sexta");
        Dm_Condicao_Vencimento = rsTP.getString ("Dm_Condicao_Vencimento");
        dm_Tipo_Faturamento = rsTP.getString ("Dm_Tipo_Faturamento");
      }

      //.println ("dm_Vencimento_Segunda->>" + dm_Vencimento_Segunda);
      //.println ("dm_Vencimento_Terca->>" + dm_Vencimento_Terca);
      //.println ("dm_Vencimento_Quarta->>" + dm_Vencimento_Quarta);
      //.println ("dm_Vencimento_Quinta->>" + dm_Vencimento_Quinta);
      //.println ("dm_Vencimento_Sexta->>" + dm_Vencimento_Sexta);

      if ("6".equals (dm_Tipo_Faturamento)) { /// quinzena vcto fixo dia 15 ou 30

        String dt_1 = DT_Emissao_Ctrc;
        // .println ("dt_1->>" + dt_1);
        if ("F".equals (Dm_Condicao_Vencimento)) {
          dt_1 = DT_Emissao_Fatura;
        }

        int d1 = new Integer (dt_1.substring (0 , 2)).intValue ();

        // .println ("d1->>" + d1);

        if (d1 < 16) {
          //DT_Vencimento="30" + dt_1.charAt(3);
          DT_Vencimento = Data.getUltimoDiaDoMes (dt_1);
          // .println ("DT_Vencimento <16->>" + DT_Vencimento);
        }
        else {
          int cont = 1;
          while (cont < 40) {
            // .println ("while >16->>" + cont);

            DT_Vencimento = Data.manipulaDias (dt_1 , cont);

            // .println ("while DT_Vencimento->>" + DT_Vencimento);

            if ("15".equals (DT_Vencimento.substring (0 , 2))) {
              break;
            }
            cont++;
          }
        }

      }
      else {

        Calendar cal = Data.stringToCalendar (DT_Emissao_Fatura , "dd/MM/yyyy");
        if ("F".equals (Dm_Condicao_Vencimento)) {
          cal = Data.stringToCalendar (DT_Emissao_Fatura , "dd/MM/yyyy");
        }
        else {
          cal = Data.stringToCalendar (DT_Emissao_Ctrc , "dd/MM/yyyy");
        }

        int diasAtuais = cal.get (Calendar.DAY_OF_MONTH);
        //int NR_Dias_Vencimento = new Integer (NR_Dias).intValue ();

        cal.set (Calendar.DAY_OF_MONTH , diasAtuais + nr_dias_vencimento);

        DT_Vencimento = dateFormatter.calendarToString (cal , SHORT_DATE);
        //.println ("CALCULA VENCTO NR_Dias_Vencimento ->>" + nr_dias_vencimento);
        //.println ("CALCULA VENCTO NR_Dias_Vencimento ->>" + nr_dias_vencimento);
        //.println ("cal.get (Calendar.DAY_OF_WEEK ->>" + cal.get (Calendar.DAY_OF_WEEK));
        //.println ("DT_Vencimento Original->>" + DT_Vencimento);

        //Domingo = 1, Segunda=2, terça=3 ... sábado=7
        switch (cal.get (Calendar.DAY_OF_WEEK)) {
          case 1:
            if (dm_Vencimento_Segunda != null && dm_Vencimento_Segunda.equals ("S")) {
            	nr_dias = 1;
            }
            else if (dm_Vencimento_Terca != null && dm_Vencimento_Terca.equals ("S")) {
            	nr_dias = 2;
            }
            else if (dm_Vencimento_Quarta != null && dm_Vencimento_Quarta.equals ("S")) {
            	nr_dias = 3;
            }
            else if (dm_Vencimento_Quinta != null && dm_Vencimento_Quinta.equals ("S")) {
            	nr_dias = 4;
            }
            else if (dm_Vencimento_Sexta != null && dm_Vencimento_Sexta.equals ("S")) {
            	nr_dias = 5;
            }
            break;
          case 2:
            if (dm_Vencimento_Segunda != null && dm_Vencimento_Segunda.equals ("S")) {
            	nr_dias = 0;
            }
            else if (dm_Vencimento_Terca != null && dm_Vencimento_Terca.equals ("S")) {
            	nr_dias = 1;
            }
            else if (dm_Vencimento_Quarta != null && dm_Vencimento_Quarta.equals ("S")) {
            	nr_dias = 2;
            }
            else if (dm_Vencimento_Quinta != null && dm_Vencimento_Quinta.equals ("S")) {
            	nr_dias = 3;
            }
            else if (dm_Vencimento_Sexta != null && dm_Vencimento_Sexta.equals ("S")) {
            	nr_dias = 4;
            }
            break;
          case 3:
            if (dm_Vencimento_Terca != null && dm_Vencimento_Terca.equals ("S")) {
            	nr_dias = 0;
            }
            else if (dm_Vencimento_Quarta != null && dm_Vencimento_Quarta.equals ("S")) {
            	nr_dias = 1;
            }
            else if (dm_Vencimento_Quinta != null && dm_Vencimento_Quinta.equals ("S")) {
            	nr_dias = 2;
            }
            else if (dm_Vencimento_Sexta != null && dm_Vencimento_Sexta.equals ("S")) {
            	nr_dias = 3;
            }
            else if (dm_Vencimento_Segunda != null && dm_Vencimento_Segunda.equals ("S")) {
            	nr_dias = 6;
            }
            break;
          case 4:
            if (dm_Vencimento_Quarta != null && dm_Vencimento_Quarta.equals ("S")) {
            	nr_dias = 0;
            }
            else if (dm_Vencimento_Quinta != null && dm_Vencimento_Quinta.equals ("S")) {
            	nr_dias = 1;
            }
            else if (dm_Vencimento_Sexta != null && dm_Vencimento_Sexta.equals ("S")) {
            	nr_dias = 2;
            }
            else if (dm_Vencimento_Segunda != null && dm_Vencimento_Segunda.equals ("S")) {
            	nr_dias = 5;
            }
            else if (dm_Vencimento_Terca != null && dm_Vencimento_Terca.equals ("S")) {
            	nr_dias = 6;
            }
            break;
          case 5:
            if (dm_Vencimento_Quinta != null && dm_Vencimento_Quinta.equals ("S")) {
            	nr_dias = 0;
            }
            else if (dm_Vencimento_Sexta != null && dm_Vencimento_Sexta.equals ("S")) {
            	nr_dias = 1;
            }
            else if (dm_Vencimento_Segunda != null && dm_Vencimento_Segunda.equals ("S")) {
            	nr_dias = 4;
            }
            else if (dm_Vencimento_Terca != null && dm_Vencimento_Terca.equals ("S")) {
            	nr_dias = 5;
            }
            else if (dm_Vencimento_Quarta != null && dm_Vencimento_Quarta.equals ("S")) {
            	nr_dias = 6;
            }
            break;
          case 6:
            if (dm_Vencimento_Sexta != null && dm_Vencimento_Sexta.equals ("S")) {
            	nr_dias = 0;
            }
            else if (dm_Vencimento_Segunda != null && dm_Vencimento_Segunda.equals ("S")) {
            	nr_dias = 3;
            }
            else if (dm_Vencimento_Terca != null && dm_Vencimento_Terca.equals ("S")) {
            	nr_dias = 4;
            }
            else if (dm_Vencimento_Quarta != null && dm_Vencimento_Quarta.equals ("S")) {
            	nr_dias = 5;
            }
            else if (dm_Vencimento_Quinta != null && dm_Vencimento_Quinta.equals ("S")) {
            	nr_dias = 6;
            }
            break;
          case 7:
            if (dm_Vencimento_Segunda != null && dm_Vencimento_Segunda.equals ("S")) {
            	nr_dias = 2;
            }
            else if (dm_Vencimento_Terca != null && dm_Vencimento_Terca.equals ("S")) {
            	nr_dias = 3;
            }
            else if (dm_Vencimento_Quarta != null && dm_Vencimento_Quarta.equals ("S")) {
            	nr_dias = 4;
            }
            else if (dm_Vencimento_Quinta != null && dm_Vencimento_Quinta.equals ("S")) {
            	nr_dias = 5;
            }
            else if (dm_Vencimento_Sexta != null && dm_Vencimento_Sexta.equals ("S")) {
            	nr_dias = 6;
            }
            break;
        }
        //.println ("fat calcula_Vencimento nr_dias ->>" + nr_dias);

        if (nr_dias>0) {
          diasAtuais = cal.get (Calendar.DAY_OF_MONTH);
          cal.set (Calendar.DAY_OF_MONTH , diasAtuais + nr_dias);
        }
        DT_Vencimento = dateFormatter.calendarToString (cal , SHORT_DATE);
      }

      return DT_Vencimento;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar");
      excecoes.setMetodo ("alterar");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void altera (FaturamentoED ed) throws Excecoes {

    String sql = null;

    try {

      int i = executasql.executarUpdate (sql);
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar");
      excecoes.setMetodo ("alterar");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void deleta (FaturamentoED ed) throws Excecoes {

    String sql = null;

    try {

      sql = "delete from Conhecimentos WHERE oid_Conhecimento = ";
      sql += "('" + ed.getOID_Conhecimento () + "')";

      int i = executasql.executarUpdate (sql);
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao excluir");
      excecoes.setMetodo ("excluir");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public ArrayList listaFatura (long nr_Duplicata , String DT_Emissao) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    try {

      this.inicioTransacao ();
      this.executasql = this.sql;

      sql = " SELECT Duplicatas.OID_Duplicata, Pessoas.oid_pessoa, Pessoas.nr_cnpj_cpf, Pessoas.nm_razao_Social, Pessoas_Bancos.nm_razao_Social as NM_Razao_Banco, Carteiras.oid_Carteira, Carteiras.cd_Carteira, Contas_Correntes.NR_conta_corrente, Tipos_Documentos.oid_tipo_documento, Tipos_Documentos.cd_tipo_documento, Tipos_Documentos.nm_tipo_documento, Duplicatas.nr_Duplicata, Duplicatas.nr_parcela, Duplicatas.nr_documento, Duplicatas.dt_vencimento, Duplicatas.vl_saldo, Duplicatas.dt_emissao, Duplicatas.vl_duplicata, Duplicatas.vl_saldo from Duplicatas, pessoas, Pessoas Pessoas_Bancos, tipos_documentos, Carteiras, Contas_Correntes  " +
          " WHERE duplicatas.oid_pessoa = pessoas.oid_pessoa " +
          " AND duplicatas.oid_Carteira = Carteiras.oid_Carteira " +
          " AND duplicatas.oid_tipo_documento = tipos_documentos.oid_tipo_documento " +
          " AND carteiras.oid_conta_corrente = contas_correntes.oid_conta_corrente  " +
          " AND contas_correntes.oid_pessoa = pessoas_bancos.oid_pessoa " +
          " AND duplicatas.nr_Documento >= " + nr_Duplicata +
          " AND duplicatas.DT_EMISSAO = '" + DT_Emissao  + "'" +
          //" ORDER BY pessoas.nm_razao_social, duplicatas.nr_duplicata, duplicatas.DT_Vencimento ";
          " ORDER BY duplicatas.nr_duplicata, duplicatas.nr_Parcela ";

      // .println ("fat mt ->>" + sql);

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);

      //popula
      while (res.next ()) {
        // .println ("fat mt 78");

        DuplicataED edVolta = new DuplicataED ();
        //popular os dados de duplicata para voltar

        edVolta.setOid_Duplicata (new Long (res.getString ("oid_duplicata")).longValue());

        edVolta.setOid_Pessoa (res.getString ("oid_pessoa"));
        edVolta.setNr_CNPJ_CPF (res.getString ("nr_cnpj_cpf"));
        edVolta.setNm_Razao_Social (res.getString ("nm_razao_Social"));

        edVolta.setCD_Conta_Corrente (res.getString ("nr_Conta_Corrente"));
        edVolta.setNM_Banco (res.getString ("NM_Razao_Banco"));

        edVolta.setOid_Carteira (new Integer (res.getString ("oid_Carteira")));
        edVolta.setCd_Carteira (res.getString ("cd_Carteira"));

        edVolta.setOid_Tipo_Documento (new Integer (res.getString ("oid_tipo_documento")));
        edVolta.setCd_Tipo_Documento (res.getString ("cd_tipo_documento"));
        edVolta.setNm_Tipo_Documento (res.getString ("nm_tipo_documento"));

        edVolta.setNr_Duplicata (new Integer (res.getString ("nr_Duplicata")));
        edVolta.setNr_Parcela (new Integer (res.getString ("nr_parcela")));

        FormataDataBean DataFormatada = new FormataDataBean ();
        DataFormatada.setDT_FormataData (res.getString ("dt_vencimento"));
        edVolta.setDt_Vencimento (DataFormatada.getDT_FormataData ());
        DataFormatada.setDT_FormataData (res.getString ("dt_emissao"));
        edVolta.setDt_Emissao (DataFormatada.getDT_FormataData ());

        edVolta.setNr_Documento (res.getString ("nr_documento"));

        String nr_Parcela = res.getString ("nr_parcela");
        if (nr_Parcela != null) {
          edVolta.setNr_Parcela (new Integer (nr_Parcela));
        }

        edVolta.setVl_Duplicata (new Double (res.getString ("vl_duplicata")));
        edVolta.setVl_Saldo (new Double (res.getString ("vl_saldo")));

        // .println ("fat mt 80");

        list.add (edVolta);
      }
      this.fimTransacao (true);

      return list;
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "inclui(FaturamentoED ed)");
    }

  }

  public ArrayList listaFaturaPre_Fatura (FaturamentoED ed) throws Excecoes {

	    String sql = null;
	    String sql_Filtro = null;

	    ArrayList list = new ArrayList ();
	    try {

	        sql = " select Modal.DM_Tipo_Transporte, Conhecimentos.DT_Emissao, Conhecimentos.DM_Situacao, Conhecimentos.NR_Conhecimento, Conhecimentos.OID_Conhecimento, Conhecimentos.nr_volumes, Conhecimentos.dm_tipo_pagamento, Conhecimentos.nr_duplicata, Conhecimentos.vl_total_frete, Conhecimentos.OID_Pessoa_Pagador,  "
	            + " conhecimentos.nr_peso, Conhecimentos.DM_Tipo_Documento, Unidades.CD_Unidade, Cidades.NM_Cidade,  Cidade_Pagador.NM_Cidade as NM_Cidade_Pagador, "
	            + " Pessoa_Remetente.NM_Razao_Social    as NM_Razao_Social_Remetente,"
	            + " Pessoa_Unidade.NM_Fantasia          as NM_Fantasia            ,"
	            + " Pessoa_Pagador.nr_cnpj_cpf          as nr_cnpj_cpf_Pagador,"
	            + " Pessoa_Pagador.NM_Razao_Social      as NM_Razao_Social_Pagador,"
	            + " Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario "
	            + " FROM Conhecimentos, Modal, Unidades, Cidades, Cidades Cidade_Pagador, "
	            + " Pessoas Pessoa_Remetente, Pessoas Pessoa_Pagador, Pessoas Pessoa_Unidade, Pessoas Pessoa_Destinatario, clientes "
	            + " where Unidades.oid_Unidade = Conhecimentos.oid_Unidade "
	            + " AND Unidades.oid_pessoa = Pessoa_Unidade.oid_Pessoa "
	            + " AND Pessoa_Unidade.oid_Cidade = Cidades.oid_cidade "
	            + " AND Conhecimentos.oid_Pessoa_Pagador = Pessoa_Pagador.oid_Pessoa "
	            + " AND Pessoa_Pagador.oid_Pessoa = Clientes.oid_pessoa "
	            + " AND Conhecimentos.oid_Modal = Modal.oid_Modal "
	            + " AND Pessoa_Pagador.oid_cidade = Cidade_Pagador.oid_Cidade "
	            + " AND Conhecimentos.DM_Impresso = 'S' "
	            + " AND Conhecimentos.VL_Total_Frete > 0"
	            + " AND Unidades.DM_Faturar = 'S' "
	            + " AND Conhecimentos.oid_Pessoa = Pessoa_Remetente.oid_Pessoa "
	            + " AND Conhecimentos.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa ";

	        if ("CTO".equals (ed.getDM_Tipo_Documento ())) {
	          sql += " and Conhecimentos.DM_Tipo_Documento = 'C'";
	        }
	        if ("MIN".equals (ed.getDM_Tipo_Documento ())) {
	          sql += " and Conhecimentos.DM_Tipo_Documento = 'M'";
	        }
	        if (String.valueOf (ed.getOID_Centro_Custo ()) != null &&
	            !String.valueOf (ed.getOID_Centro_Custo ()).equals ("0") &&
	            !String.valueOf (ed.getOID_Centro_Custo ()).equals ("null")) {
	          sql += " and Conhecimentos.OID_Centro_Custo = " + ed.getOID_Centro_Custo ();
	        }
	        if (String.valueOf (ed.getOID_Unidade ()) != null && !String.valueOf (ed.getOID_Unidade ()).equals ("0")) {
	          sql += " and Conhecimentos.OID_Unidade = " + ed.getOID_Unidade ();
	        }

	        if (String.valueOf (ed.getOID_Pessoa_Pagador ()) != null && !String.valueOf (ed.getOID_Pessoa_Pagador ()).equals ("") && !String.valueOf (ed.getOID_Pessoa_Pagador ()).equals ("null")) {
	          sql += " and Conhecimentos.OID_Pessoa_Pagador = '" + ed.getOID_Pessoa_Pagador () + "'";
	        }
	        if (ed.getOID_Grupo_Economico()>0){
	          sql += " AND   Conhecimentos.oid_Pessoa_Pagador in " + getByGrupo_Economico (ed);
	        }

	        if (String.valueOf (ed.getDT_Emissao_Inicial ()) != null && !String.valueOf (ed.getDT_Emissao_Inicial ()).equals ("") && !String.valueOf (ed.getDT_Emissao_Inicial ()).equals ("null")) {
	          sql += " and Conhecimentos.DT_Emissao >= '" + ed.getDT_Emissao_Inicial () + "'";
	        }
	        if (String.valueOf (ed.getDT_Emissao_Final ()) != null && !String.valueOf (ed.getDT_Emissao_Final ()).equals ("") && !String.valueOf (ed.getDT_Emissao_Final ()).equals ("null")) {
	          sql += " and Conhecimentos.DT_Emissao <= '" + ed.getDT_Emissao_Final () + "'";
	        }

	        if ("T2".equals (ed.getDM_Tipo_Conhecimento ())) {
	          sql += " and Conhecimentos.VL_Total_Frete> 1 ";
	        }
	        else {
	          if ("2".equals (ed.getDM_Tipo_Conhecimento ())) {
	            sql += " and Conhecimentos.VL_Total_Frete<=1 ";
	          }
	          else {
	            if (String.valueOf (ed.getDM_Tipo_Conhecimento ()) != null && !String.valueOf (ed.getDM_Tipo_Conhecimento ()).equals ("null") && !String.valueOf (ed.getDM_Tipo_Conhecimento ()).equals ("T")
	                && !String.valueOf (ed.getDM_Tipo_Conhecimento ()).equals ("")) {
	              sql += " and Conhecimentos.DM_Tipo_Conhecimento = '" + ed.getDM_Tipo_Conhecimento () + "'";
	            }
	          }
	        }
	        if (String.valueOf (ed.getDM_Tipo_Cobranca ()) != null && !String.valueOf (ed.getDM_Tipo_Cobranca ()).equals ("null") && !String.valueOf (ed.getDM_Tipo_Cobranca ()).equals ("T")
	            && !String.valueOf (ed.getDM_Tipo_Cobranca ()).equals ("")) {
	          sql += " and Conhecimentos.DM_Tipo_Cobranca = '" + ed.getDM_Tipo_Cobranca () + "'";
	        }

	        if (String.valueOf (ed.getDM_Tipo_Transporte ()) != null && !String.valueOf (ed.getDM_Tipo_Transporte ()).equals ("null") && !String.valueOf (ed.getDM_Tipo_Transporte ()).equals ("T")
	            && !String.valueOf (ed.getDM_Tipo_Transporte ()).equals ("")) {
	          sql += " and Modal.DM_Tipo_Transporte = '" + ed.getDM_Tipo_Transporte () + "'";
	        }

	        if (String.valueOf (ed.getDM_Compr_Entrega_Fatura ()) != null && "S".equals (ed.getDM_Compr_Entrega_Fatura ())) {
	          sql += " and Clientes.DM_Compr_Entrega_Fatura = 'S'" +
	              "  and Conhecimentos.dt_entrega is not null ";
	        }

	        if (String.valueOf (ed.getDM_Tipo_Faturamento ()) != null &&
	            !String.valueOf (ed.getDM_Tipo_Faturamento ()).equals ("T") &&
	            !String.valueOf (ed.getDM_Tipo_Faturamento ()).equals ("null") &&
	            !String.valueOf (ed.getDM_Tipo_Faturamento ()).equals ("")) {
	          sql += " and Clientes.DM_Tipo_Faturamento = '" + ed.getDM_Tipo_Faturamento () + "'";
	        }

	        sql += " and (Conhecimentos.DM_Situacao = 'G' or Conhecimentos.DM_Situacao = 'B') ";

	        if ("F2".equals(ed.getDM_Relatorio())){
	          sql += " order by unidades.cd_unidade, conhecimentos.nr_conhecimento ";
	        }
	        else {
	          sql += " order by Conhecimentos.OID_Pessoa_Pagador, Pessoa_Pagador.NM_Razao_Social, conhecimentos.nr_conhecimento ";
	        }

	        System.out.println ("fat mt SQL " + sql);

	      ResultSet res = null;
	      res = this.executasql.executarConsulta (sql);

	      while (res.next ()) {

	        DuplicataED edVolta = new DuplicataED ();

	        edVolta.setNr_Documento (res.getString ("nr_conhecimento"));

	        FormataDataBean DataFormatada = new FormataDataBean ();
	        DataFormatada.setDT_FormataData (res.getString ("dt_emissao"));
	        edVolta.setDt_Emissao (DataFormatada.getDT_FormataData ());

	        edVolta.setNr_CNPJ_CPF (res.getString ("nr_cnpj_cpf_Pagador"));
	        edVolta.setNm_Razao_Social (res.getString ("NM_Razao_Social_Pagador"));

	        edVolta.setVl_Duplicata (new Double (res.getString ("vl_total_frete")));

	        list.add (edVolta);
	      }

	      return list;
	    }
	    catch (Exception e) {
	      e.printStackTrace ();
	      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "inclui(FaturamentoED ed)");
	    }

	  }


  public FaturamentoED getByRecord (FaturamentoED ed) throws Excecoes {

    String sql = null;

    FaturamentoED edVolta = new FaturamentoED ();

    try {

      sql = " select * from Conhecimentos where 1=1 ";

      if (String.valueOf (ed.getOID_Conhecimento ()) != null &&
          !String.valueOf (ed.getOID_Conhecimento ()).equals ("")) {
        sql += " and OID_Conhecimento = '" + ed.getOID_Conhecimento () + "'";
      }
      if (String.valueOf (ed.getNR_Conhecimento ()) != null && !String.valueOf (ed.getNR_Conhecimento ()).equals ("0")) {
        sql += " and NR_Conhecimento = " + ed.getNR_Conhecimento ();
        sql += " and OID_Unidade = " + ed.getOID_Unidade ();
      }

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);

      FormataDataBean DataFormatada = new FormataDataBean ();

      while (res.next ()) {
      }

      sql = " SELECT SUM(Notas_Fiscais.VL_Nota_Fiscal) AS VL_Notas, SUM(Notas_Fiscais.NR_Volumes) AS NR_Volume, SUM(Notas_Fiscais.NR_Peso) AS NR_Peso FROM Notas_Fiscais, Conhecimentos_Notas_Fiscais ";
      sql += " WHERE Notas_Fiscais.OID_Nota_Fiscal = Conhecimentos_Notas_Fiscais.OID_Nota_Fiscal ";
      sql += " AND Conhecimentos_Notas_Fiscais.OID_Conhecimento = '" + edVolta.getOID_Conhecimento () + "'";

      ResultSet rs = null;
      rs = this.executasql.executarConsulta (sql);

      while (rs.next ()) {

      }

      sql = " SELECT * from Movimentos_Conhecimentos, Tipos_Movimentos ";
      sql += " WHERE oid_Conhecimento ='" + edVolta.getOID_Conhecimento () + "'";
      sql += " AND Tipos_Movimentos.CD_Tipo_Movimento = 'TFC' ";
      sql += " AND Tipos_Movimentos.OID_Tipo_Movimento = Movimentos_Conhecimentos.OID_Tipo_Movimento ";

      rs = null;
      rs = this.executasql.executarConsulta (sql);

      while (rs.next ()) {
        edVolta.setVL_Total_Frete (rs.getDouble ("VL_Movimento"));
      }

      sql = " SELECT * from Movimentos_Conhecimentos, Tipos_Movimentos ";
      sql += " WHERE oid_Conhecimento ='" + edVolta.getOID_Conhecimento () + "'";
      sql += " AND Tipos_Movimentos.CD_Tipo_Movimento = 'ICM' ";
      sql += " AND Tipos_Movimentos.OID_Tipo_Movimento = Movimentos_Conhecimentos.OID_Tipo_Movimento ";

      rs = null;
      rs = this.executasql.executarConsulta (sql);

      while (rs.next ()) {
        edVolta.setVL_ICMS (rs.getDouble ("VL_Movimento"));
      }

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao selecionar");
      excecoes.setMetodo ("selecionar");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return edVolta;
  }

  public FaturamentoED geraFaturaOrdem_Frete_Terceiro (FaturamentoED ed) throws Excecoes {

    // .println ("Faturamento BD geraFaturaOrdem_Frete_Terceiro ");

    String sql = null;
    long valOid = 0;
    String chave = null;
    long NR_Duplicata = 999999999;
    int qt_faturas = 0;

    String SHORT_DATE = "dd/MM/yyyy";
    Parametro_FixoED parametro_FixoED = new Parametro_FixoED ();

    // .println ("Faturamento BD 1  ");

    try {
      ResultSet rsTP = null;

      sql = " select * " +
          " FROM  Lotes_Faturamentos, Clientes" +
          " WHERE Lotes_Faturamentos.oid_pessoa = Clientes.OID_Cliente " +
          " AND   Lotes_Faturamentos.oid_Lote_Faturamento =  " + ed.getOid_Lote_Faturamento ();

      // .println ("fat mt SQL " + sql);
      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);

      while (res.next ()) {

        sql = " select oid_Vendedor from Clientes Where oid_Cliente='" + res.getString ("oid_pessoa") + "'";
        rsTP = this.executasql.executarConsulta (sql);
        while (rsTP.next ()) {
          ed.setOID_Vendedor (rsTP.getString ("OID_Vendedor"));
        }

        ed.setOID_Pessoa_Pagador (res.getString ("oid_pessoa"));
        ed.setVL_Taxa_Cobranca (res.getDouble ("VL_Taxa_Cobranca"));
        ed.setVL_Duplicata (res.getDouble ("vl_cobranca"));
        ed.setDT_Emissao (res.getString ("DT_Emissao"));
        ed.setDT_Vencimento (res.getString ("dt_vencimento"));
        ed.setOID_Unidade (res.getLong ("OID_Unidade"));
      }

      ed.setOid_Tipo_Documento (parametro_FixoED.getOID_Tipo_Documento_Faturamento ());
      // .println ("Faturamento BD 3 ..... ");

      sql =
          " SELECT Clientes.OID_Carteira " +
          "       ,Clientes.NR_Dias_Vencimento " +
          "       ,Clientes.VL_Taxa_Cobranca " +
          " FROM Unidades, Clientes, Carteiras " +
          " WHERE Unidades.OID_Unidade = " + ed.getOID_Unidade () +
          "   AND Unidades.oid_Pessoa = Clientes.oid_Cliente " +
          "   AND Clientes.oid_Carteira = Carteiras.oid_Carteira";

      rsTP = this.executasql.executarConsulta (sql);
      while (rsTP.next ()) {
        if (ed.getOID_Carteira_Informada () <= 0) {
          ed.setOid_Carteira (rsTP.getLong ("OID_Carteira"));
        }
      }

      // .println ("Faturamento BD 5 ..... ");

      sql =
          " SELECT Carteiras.OID_Carteira " +
          "       ,Carteiras.PE_Juros " +
          "       ,Carteiras.PE_Multa " +
          " FROM Carteiras " +
          " WHERE Carteiras.oid_Carteira = " + ed.getOid_Carteira ();

      rsTP = this.executasql.executarConsulta (sql);
      while (rsTP.next ()) {
        ed.setVL_Juro_Mora_Dia (new Double (rsTP.getDouble ("PE_Juros")).doubleValue ());
        ed.setVL_Multa (new Double (rsTP.getDouble ("PE_Multa")).doubleValue ());
      }

      // .println ("Faturamento BD 8 ..... ");

      sql = " select * " +
          " FROM  Documentos_Lotes_Faturamentos " +
          " WHERE Documentos_Lotes_Faturamentos.oid_Lote_Faturamento = " + ed.getOid_Lote_Faturamento ();

      // .println ("fat mt SQL " + sql);
      res = this.executasql.executarConsulta (sql);
      qt_faturas = 0;
      while (res.next ()) {

        if (qt_faturas == 0) {

          sql = " SELECT AIDOF.NR_Proximo, AIDOF.OID_AIDOF, AIDOF.NM_Serie ";
          sql += " FROM Parametros_Filiais, AIDOF ";
          sql += " WHERE Parametros_Filiais.OID_AIDOF_Fatura = AIDOF.OID_AIDOF ";
          sql += " AND Parametros_Filiais.OID_Unidade = " + ed.getOID_Unidade ();

          rsTP = this.executasql.executarConsulta (sql);
          while (rsTP.next ()) {
            ed.setNM_Serie (rsTP.getString ("NM_Serie"));
            ed.setNR_Fatura (rsTP.getLong ("NR_Proximo"));
            valOid = rsTP.getLong ("OID_AIDOF");
          }

          chave = String.valueOf (ed.getOID_Unidade ()) + String.valueOf (ed.getNR_Fatura ()) + ed.getNM_Serie ();

          ed.setOID_Fatura (chave);

          ed.setVL_Desconto_Faturamento (0.0);
          ed.setVL_Taxa_Refaturamento (0.0);

          // .println ("fat mt 20 *******************");

          sql = " insert into Duplicatas " +
              "(OID_Duplicata, NR_DOCUMENTO, NR_PARCELA, " +
              "DT_EMISSAO, DT_VENCIMENTO, DT_CREDITO, VL_Duplicata, " +
              "VL_Taxa_Cobranca, " +
              "VL_Multa, " +
              "VL_Juro_Mora_Dia, " +
              "NR_Duplicata, DT_STAMP, dm_tipo_cobranca, USUARIO_STAMP, " +
              "DM_STAMP, VL_Desconto_Faturamento, VL_SALDO, " +
              "OID_TIPO_DOCUMENTO, OID_Carteira, OID_PESSOA, DM_Quebra_Faturamento, " +
              "OID_Vendedor, OID_UNIDADE, NR_REMESSA, DM_Tipo_Documento) values " +
              "(" + ed.getOID_Fatura () + ",'";
          sql += ed.getNR_Fatura () + "',";
          sql += "'1',";
          sql += "'" + ed.getDT_Emissao () + "',";
          sql += "'" + ed.getDT_Vencimento () + "',";
          sql += "'" + ed.getDT_Vencimento () + "',";
          sql += ed.getVL_Duplicata () + ",";
          sql += ed.getVL_Taxa_Cobranca () + ",";
          sql += ed.getVL_Multa () + ",";
          sql += ed.getVL_Juro_Mora_Dia () + ",";
          sql += ed.getNR_Fatura () + ",";
          sql += "'" + ed.getDt_stamp () + "',";
          sql += "'" + "B" + "',";
          sql += "'" + ed.getUsuario_Stamp () + "',";
          sql += "'" + ed.getDm_Stamp () + "',";
          sql += ed.getVL_Desconto_Faturamento () + ",";
          sql += ed.getVL_Duplicata () + ",";
          sql += ed.getOid_Tipo_Documento () + ",";
          sql += ed.getOid_Carteira () + ",";
          sql += "'" + ed.getOID_Pessoa_Pagador () + "','";
          sql += ' ' + "','";
          sql += ed.getOID_Vendedor () + "',";
          sql += ed.getOID_Unidade () + ",0,'1')";

          // .println ("fat mt 21" + sql);

          int u = executasql.executarUpdate (sql);

          sql = " UPDATE AIDOF SET NR_Proximo= " + (ed.getNR_Fatura () + 1);
          sql += " WHERE OID_AIDOF = " + valOid;

          executasql.executarUpdate (sql);

          qt_faturas++;
        }
        chave = (ed.getOID_Fatura () + res.getString ("oid_ordem_frete_terceiro"));

        sql = " insert into Origens_Duplicatas (OID_Origem_Duplicata, oid_ordem_frete_terceiro, OID_Duplicata, DT_Origem_Duplicata, HR_Origem_Duplicata, DM_Situacao ) values ";
        sql += "('" + chave + "','" + res.getString ("oid_ordem_frete_terceiro") + "'," + ed.getOID_Fatura () + ",'" + ed.getDt_stamp () + "','" + ed.getHr_stamp () + "','" + "A" + "')";
        // .println ("fat Origens_Duplicatas 46 - " + sql);

        executasql.executarUpdate (sql);
      }
      // .println ("fat mt 40 qt_faturas->> " + qt_faturas);

      if (qt_faturas > 0) {
        ed.setNR_Fatura (NR_Duplicata);
      }
      else {
        ed.setNR_Fatura (0);
      }

      // .println ("fat mt 40 ed.getNR_Fatura()->> " + ed.getNR_Fatura ());

      return ed;
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "inclui(FaturamentoED ed)");
    }
  }

  public byte[] geraPre_Faturamento (FaturamentoED ed) throws Excecoes {

    String sql = null;
    byte[] b = null;
    ResultSet res = null;
    ResultSet resOP = null;
    try {

      if ("T".equals (ed.getDM_Tipo_Documento ()) ||
          "CTO".equals (ed.getDM_Tipo_Documento ()) ||
          "MIN".equals (ed.getDM_Tipo_Documento ())) {
        //Conhecimentos
        sql = " select Modal.DM_Tipo_Transporte, Conhecimentos.DT_Emissao, Conhecimentos.DM_Situacao, Conhecimentos.NR_Conhecimento, Conhecimentos.OID_Conhecimento, Conhecimentos.nr_volumes, Conhecimentos.dm_tipo_pagamento, Conhecimentos.nr_duplicata, Conhecimentos.vl_total_frete, Conhecimentos.OID_Pessoa_Pagador,  "
            + " conhecimentos.nr_peso, Conhecimentos.DM_Tipo_Documento, Unidades.CD_Unidade, Cidades.NM_Cidade,  Cidade_Pagador.NM_Cidade as NM_Cidade_Pagador, "
            + " Pessoa_Remetente.NM_Razao_Social    as NM_Razao_Social_Remetente,"
            + " Pessoa_Unidade.NM_Fantasia          as NM_Fantasia            ,"
            + " Pessoa_Pagador.NM_Razao_Social      as NM_Razao_Social_Pagador,"
            + " Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario" +
            		", Conhecimentos.dt_entrega "
            + " FROM Conhecimentos, Modal, Unidades, Cidades, Cidades Cidade_Pagador, "
            + " Pessoas Pessoa_Remetente, Pessoas Pessoa_Pagador, Pessoas Pessoa_Unidade, Pessoas Pessoa_Destinatario, clientes "
            + " where Unidades.oid_Unidade = Conhecimentos.oid_Unidade "
            + " AND Unidades.oid_pessoa = Pessoa_Unidade.oid_Pessoa "
            + " AND Pessoa_Unidade.oid_Cidade = Cidades.oid_cidade "
            + " AND Conhecimentos.oid_Pessoa_Pagador = Pessoa_Pagador.oid_Pessoa "
            + " AND Pessoa_Pagador.oid_Pessoa = Clientes.oid_pessoa "
            + " AND Conhecimentos.oid_Modal = Modal.oid_Modal "
            + " AND Pessoa_Pagador.oid_cidade = Cidade_Pagador.oid_Cidade "
            + " AND Conhecimentos.DM_Impresso = 'S' "
            + " AND Conhecimentos.VL_Total_Frete > 0"
            + " AND Unidades.DM_Faturar = 'S' "
            + " AND Conhecimentos.oid_Pessoa = Pessoa_Remetente.oid_Pessoa "
            + " AND Conhecimentos.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa ";

        if ("CTO".equals (ed.getDM_Tipo_Documento ())) {
          sql += " and Conhecimentos.DM_Tipo_Documento = 'C'";
        }
        if ("MIN".equals (ed.getDM_Tipo_Documento ())) {
          sql += " and Conhecimentos.DM_Tipo_Documento = 'M'";
        }
        if (String.valueOf (ed.getOID_Centro_Custo ()) != null &&
            !String.valueOf (ed.getOID_Centro_Custo ()).equals ("0") &&
            !String.valueOf (ed.getOID_Centro_Custo ()).equals ("null")) {
          sql += " and Conhecimentos.OID_Centro_Custo = " + ed.getOID_Centro_Custo ();
        }
        if (String.valueOf (ed.getOID_Unidade ()) != null && !String.valueOf (ed.getOID_Unidade ()).equals ("0")) {
          sql += " and Conhecimentos.OID_Unidade = " + ed.getOID_Unidade ();
        }

        if (String.valueOf (ed.getOID_Pessoa_Pagador ()) != null && !String.valueOf (ed.getOID_Pessoa_Pagador ()).equals ("") && !String.valueOf (ed.getOID_Pessoa_Pagador ()).equals ("null")) {
          sql += " and Conhecimentos.OID_Pessoa_Pagador = '" + ed.getOID_Pessoa_Pagador () + "'";
        }
        if (ed.getOID_Grupo_Economico()>0){
          sql += " AND   Conhecimentos.oid_Pessoa_Pagador in " + getByGrupo_Economico (ed);
        }

        if (String.valueOf (ed.getDT_Emissao_Inicial ()) != null && !String.valueOf (ed.getDT_Emissao_Inicial ()).equals ("") && !String.valueOf (ed.getDT_Emissao_Inicial ()).equals ("null")) {
          sql += " and Conhecimentos.DT_Emissao >= '" + ed.getDT_Emissao_Inicial () + "'";
        }
        if (String.valueOf (ed.getDT_Emissao_Final ()) != null && !String.valueOf (ed.getDT_Emissao_Final ()).equals ("") && !String.valueOf (ed.getDT_Emissao_Final ()).equals ("null")) {
          sql += " and Conhecimentos.DT_Emissao <= '" + ed.getDT_Emissao_Final () + "'";
        }

        if ("T2".equals (ed.getDM_Tipo_Conhecimento ())) {
          sql += " and Conhecimentos.VL_Total_Frete> 1 ";
        }
        else {
          if ("2".equals (ed.getDM_Tipo_Conhecimento ())) {
            sql += " and Conhecimentos.VL_Total_Frete<=1 ";
          }
          else {
            if (String.valueOf (ed.getDM_Tipo_Conhecimento ()) != null && !String.valueOf (ed.getDM_Tipo_Conhecimento ()).equals ("null") && !String.valueOf (ed.getDM_Tipo_Conhecimento ()).equals ("T")
                && !String.valueOf (ed.getDM_Tipo_Conhecimento ()).equals ("")) {
              sql += " and Conhecimentos.DM_Tipo_Conhecimento = '" + ed.getDM_Tipo_Conhecimento () + "'";
            }
          }
        }
        if (String.valueOf (ed.getDM_Tipo_Cobranca ()) != null && !String.valueOf (ed.getDM_Tipo_Cobranca ()).equals ("null") && !String.valueOf (ed.getDM_Tipo_Cobranca ()).equals ("T")
            && !String.valueOf (ed.getDM_Tipo_Cobranca ()).equals ("")) {
          sql += " and Conhecimentos.DM_Tipo_Cobranca = '" + ed.getDM_Tipo_Cobranca () + "'";
        }

        if (String.valueOf (ed.getDM_Tipo_Transporte ()) != null && !String.valueOf (ed.getDM_Tipo_Transporte ()).equals ("null") && !String.valueOf (ed.getDM_Tipo_Transporte ()).equals ("T")
            && !String.valueOf (ed.getDM_Tipo_Transporte ()).equals ("")) {
          sql += " and Modal.DM_Tipo_Transporte = '" + ed.getDM_Tipo_Transporte () + "'";
        }

        if (String.valueOf (ed.getDM_Compr_Entrega_Fatura ()) != null && "S".equals (ed.getDM_Compr_Entrega_Fatura ())) {
          sql += " and Clientes.DM_Compr_Entrega_Fatura = 'S'" +
              "  and Conhecimentos.dt_entrega is not null ";
        }

        if (String.valueOf (ed.getDM_Tipo_Faturamento ()) != null &&
            !String.valueOf (ed.getDM_Tipo_Faturamento ()).equals ("T") &&
            !String.valueOf (ed.getDM_Tipo_Faturamento ()).equals ("null") &&
            !String.valueOf (ed.getDM_Tipo_Faturamento ()).equals ("")) {
          sql += " and Clientes.DM_Tipo_Faturamento = '" + ed.getDM_Tipo_Faturamento () + "'";
        }

        if ("B".equals(String.valueOf (ed.getDM_Baixado ()))) {
           sql += " and Conhecimentos.dt_entrega is not null " ;
        }else if ("P".equals(String.valueOf (ed.getDM_Baixado ()))) {
           sql += " and Conhecimentos.dt_entrega is null " ;
        }

        sql += " and (Conhecimentos.DM_Situacao = 'G' or Conhecimentos.DM_Situacao = 'B') ";

        if ("F2".equals(ed.getDM_Relatorio())){
          sql += " order by unidades.cd_unidade, conhecimentos.nr_conhecimento ";
        }
        else {
          sql += " order by Conhecimentos.OID_Pessoa_Pagador, Pessoa_Pagador.NM_Razao_Social, conhecimentos.nr_conhecimento ";
        }

        // .println(sql);
        res = this.executasql.executarConsulta (sql.toString ());

      }

      if ("T".equals (ed.getDM_Tipo_Documento ()) ||
          "OFT".equals (ed.getDM_Tipo_Documento ())) {

        //Ordens Fretes
        sql = " SELECT *, " +
            " Ordens_Fretes.oid_fornecedor as OID_Pessoa_Pagador, " +
            " Ordens_Fretes.vl_ordem_frete as vl_total_frete, " +
            " Unidades.CD_Unidade, " +
            " Cidade_Pagador.NM_Cidade as NM_Cidade_Pagador, " +
            " Pessoas.NM_Fantasia, " +
            " Pessoa_Fornecedor.NM_Razao_Social as NM_Razao_Social_Pagador " +
            " ,'' as dt_entrega" +
            " FROM  Ordens_Fretes, Unidades, Pessoas, Pessoas Pessoa_Fornecedor, Cidades Cidade_Pagador  ";
        sql += " WHERE Ordens_Fretes.OID_Unidade = Unidades.OID_Unidade ";
        sql += " AND Ordens_Fretes.OID_Fornecedor = Pessoa_Fornecedor.OID_Pessoa ";
        sql += " AND Unidades.OID_Pessoa = Pessoas.OID_Pessoa ";
        sql += " AND Pessoa_Fornecedor.oid_cidade = Cidade_Pagador.oid_Cidade ";
        sql += " AND Ordens_Fretes.DM_Impresso = 'S' ";
        sql += " AND (Ordens_Fretes.oid_lote_faturamento is null  OR Ordens_Fretes.oid_lote_faturamento =0) ";
        sql += " AND Ordens_Fretes.DM_Frete = 'T'";

        if (String.valueOf (ed.getOID_Empresa ()) != null &&
            !String.valueOf (ed.getOID_Empresa ()).equals ("0")) {
          // sql += " and Unidades.OID_Empresa = " + ed.getOID_Empresa ();
        }

        if (String.valueOf (ed.getOID_Unidade ()) != null &&
            !String.valueOf (ed.getOID_Unidade ()).equals ("0")) {
          sql += " and Ordens_Fretes.OID_Unidade = " + ed.getOID_Unidade ();
        }

        if (String.valueOf (ed.getOID_Pessoa_Pagador ()) != null && !String.valueOf (ed.getOID_Pessoa_Pagador ()).equals ("") && !String.valueOf (ed.getOID_Pessoa_Pagador ()).equals ("null")) {
          sql += " and Ordens_Fretes.oid_fornecedor = '" + ed.getOID_Pessoa_Pagador () + "'";
        }

        if (String.valueOf (ed.getDT_Emissao_Inicial ()) != null &&
            !String.valueOf (ed.getDT_Emissao_Inicial ()).equals ("") &&
            !String.valueOf (ed.getDT_Emissao_Inicial ()).equals ("null")) {
          sql += " and Ordens_Fretes.DT_Emissao >= '" + ed.getDT_Emissao_Inicial () +
              "'";
        }
        if (String.valueOf (ed.getDT_Emissao_Final ()) != null &&
            !String.valueOf (ed.getDT_Emissao_Final ()).equals ("") &&
            !String.valueOf (ed.getDT_Emissao_Final ()).equals ("null")) {
          sql += " and Ordens_Fretes.DT_Emissao <= '" + ed.getDT_Emissao_Final () +
              "'";
        }
        if (ed.getOID_Unidade () > 0) {
          sql += " order by unidades.cd_unidade, Ordens_Fretes.oid_fornecedor, Ordens_Fretes.NR_Recibo ";
        }
        else {
          sql += " order by Ordens_Fretes.oid_fornecedor, Ordens_Fretes.NR_Recibo ";
        }

        // .println (sql);
        resOP = this.executasql.executarConsulta (sql.toString ());
      }
      FaturamentoRL conRL = new FaturamentoRL ();
      b = conRL.geraPre_Faturamento (res , resOP , ed);

    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro no método listar");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("geraRelatorio(ConhecimentoED ed)");
    }
    return b;
  }

  private String getByGrupo_Economico (FaturamentoED ed) throws Excecoes {

    // .println ("getByGrupo_Economico");
    String grupo = null;
    int qt_cli = 1;
    try {

      grupo = "('" + "'";

      String sql = " SELECT Clientes.oid_Cliente " +
          " FROM   Clientes " +
          " WHERE  Clientes.oid_Grupo_Economico=" + ed.getOID_Grupo_Economico();

      ResultSet res2 = this.executasql.executarConsulta (sql);

      while (res2.next ()) {
        if (qt_cli > 0) grupo += ",";
        grupo += "'" + res2.getString ("oid_Cliente") + "'";
        qt_cli++;
      }
      grupo += ")";

      // .println ("grupo->>" + grupo);

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("getByGrupo_Economico");
      excecoes.setMetodo ("getByGrupo_Economico");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return grupo;
  }

}
