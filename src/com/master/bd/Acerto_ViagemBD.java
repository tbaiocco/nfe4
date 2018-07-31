package com.master.bd;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.master.ed.Acerto_ViagemED;
import com.master.ed.Lancamento_ContabilED;
import com.master.ed.Movimento_Conta_CorrenteED;
import com.master.ed.Ordem_FreteED;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.JavaUtil;
import com.master.util.Utilitaria;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;


public class Acerto_ViagemBD  {

  private ExecutaSQL executasql;
  Parametro_FixoED parametro_FixoED = new Parametro_FixoED();
  Utilitaria util = new Utilitaria(executasql);

  public Acerto_ViagemBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public ArrayList lista(Acerto_ViagemED ed) throws Excecoes {

      String sql = null;
      ArrayList list = new ArrayList();

      try {
          sql = " SELECT Ordens_Servicos.OID_Ordem_Servico, Ordens_Servicos.NR_Ordem_Servico, " +
          		"Ordens_Servicos.DT_Ordem_Servico, Unidades.CD_Unidade, Ordens_Servicos.OID_Veiculo, " +
          		" Ordens_Servicos.OID_Carreta, Ordens_Servicos.OID_Veiculo2, Ordens_Servicos.OID_Veiculo3 " +
                " FROM Ordens_Servicos, Unidades "+
                " WHERE Ordens_Servicos.OID_Unidade = Unidades.OID_Unidade " +
                " AND Ordens_Servicos.oid_Tipo_Servico = " + parametro_FixoED.getOID_Tipo_Servico_Acerto_Contas();

            if (ed.getNR_Acerto()>0 || util.doValida(ed.getOID_Motorista()))  {
              sql = " SELECT Ordens_Servicos.OID_Ordem_Servico, Ordens_Servicos.NR_Ordem_Servico, " +
              		" Ordens_Servicos.DT_Ordem_Servico, Unidades.CD_Unidade, Ordens_Servicos.OID_Veiculo, " +
              		" Ordens_Servicos.OID_Carreta, Ordens_Servicos.OID_Veiculo2, Ordens_Servicos.OID_Veiculo3 " +
                    " FROM   Ordens_Servicos, Unidades, Acertos "+
                    " WHERE  Ordens_Servicos.OID_Unidade = Unidades.OID_Unidade " +
                    " AND    Ordens_Servicos.oid_ordem_Servico = Acertos.oid_Ordem_Servico ";
                if (ed.getNR_Acerto()>0)  {
                  sql+=" AND Acertos.NR_Acerto =" + ed.getNR_Acerto();
                }
                else{
                  sql += " AND Acertos.oid_Motorista ='" + ed.getOID_Motorista() + "'";
                }
            }else {
                if (ed.getNR_Ordem_Servico()>0)  {
                  sql+=" AND Ordens_Servicos.NR_Ordem_Servico =" + ed.getNR_Ordem_Servico();
                }
                if (ed.getOID_Ordem_Servico()>0)  {
                    sql+=" AND Ordens_Servicos.oid_Ordem_Servico =" + ed.getOID_Ordem_Servico();
                  }
            }

            if (ed.getOid_Veiculo() != null && !ed.getOid_Veiculo().equals("") && !ed.getOid_Veiculo().equals("null")) {
                sql += " AND Ordens_Servicos.OID_VEICULO ='" + ed.getOid_Veiculo() + "'";
            }
            if (JavaUtil.doValida(ed.getOid_Veiculo2())) {
                sql += " AND Ordens_Servicos.OID_VEICULO2 ='" + ed.getOid_Veiculo2() + "'";
            }
            if (JavaUtil.doValida(ed.getOid_Veiculo3())) {
                sql += " AND Ordens_Servicos.OID_VEICULO3 ='" + ed.getOid_Veiculo3() + "'";
            }

            if (ed.getOid_Carreta() != null && !ed.getOid_Carreta().equals("") && !ed.getOid_Carreta().equals("null")) {
                sql += " AND Ordens_Servicos.OID_Carreta ='" + ed.getOid_Carreta() + "'";
            }

            if (ed.getOid_Unidade() >0) {
                sql += " AND Ordens_Servicos.oid_Unidade ='" + ed.getOid_Unidade() + "'";
            }

            if (ed.getDT_Inicial() != null && !ed.getDT_Inicial().equals("") && !ed.getDT_Inicial().equals("null")) {
                sql += " AND Ordens_Servicos.DT_ORDEM_SERVICO >='" + ed.getDT_Inicial() + "'";
            }
            if (ed.getDT_Final() != null && !ed.getDT_Final().equals("") && !ed.getDT_Final().equals("null")) {
                sql += " AND Ordens_Servicos.DT_ORDEM_SERVICO >='" + ed.getDT_Final() + "'";
            }

            sql +=" ORDER BY Ordens_Servicos.NR_Ordem_Servico DESC";

            // System.out.println(sql);


          ResultSet res = this.executasql.executarConsulta(sql);

          //popula
          while (res.next()) {
              Acerto_ViagemED edVolta = new Acerto_ViagemED();
              int opcoes_acerto=0;
              edVolta.setNR_Acerto(0);
              edVolta.setNM_Razao_Social(" ");
              edVolta.setDT_Emissao (" ");
              edVolta.setDT_Saida (" ");
              edVolta.setDT_Chegada (" ");


              sql = " SELECT oid_Acerto, NR_Acerto, NM_Razao_Social, DT_Saida, DT_Chegada, DT_Emissao " +
                    " FROM  Acertos, Pessoas " +
                    " WHERE Acertos.oid_Motorista = Pessoas.oid_Pessoa "+
                    " AND   Acertos.oid_ORdem_Servico =" + res.getLong("OID_Ordem_Servico");

                    if (ed.getOid_Motorista() != null && !ed.getOid_Motorista().equals("") && !ed.getOid_Motorista().equals("null")) {
                      sql += " AND    Acertos.oid_Motorista ='" + ed.getOid_Motorista() + "'";
                      opcoes_acerto++;
                    }
                    if (ed.getDT_Saida() != null && !ed.getDT_Saida().equals("") && !ed.getDT_Saida().equals("null")) {
                      sql += " AND    Acertos.DT_Saida >='" + ed.getDT_Saida() + "'" ;
                      opcoes_acerto++;
                    }
                    if (ed.getDT_Chegada() != null && !ed.getDT_Chegada().equals("") && !ed.getDT_Chegada().equals("null")) {
                      sql += " AND    Acertos.DT_Chegada <='" + ed.getDT_Chegada() + "'" ;
                      opcoes_acerto++;
                    }
                    if (ed.getDT_Emissao_Inicial() != null && !ed.getDT_Emissao_Inicial().equals("") && !ed.getDT_Emissao_Inicial().equals("null")) {
                      sql += " AND    Acertos.DT_Emissao >='" + ed.getDT_Emissao_Inicial() + "'" ;
                      opcoes_acerto++;
                    }
                    if (ed.getDT_Emissao_Final() != null && !ed.getDT_Emissao_Final().equals("") && !ed.getDT_Emissao_Final().equals("null")) {
                      sql += " AND    Acertos.DT_Emissao <='" + ed.getDT_Emissao_Final() + "'" ;
                      opcoes_acerto++;
                    }

                    // System.out.println(sql);
                    ResultSet res2 = this.executasql.executarConsulta(sql);
                    while (res2.next()) {
                      // System.out.println("ACV LISTA 1");

                      edVolta.setOID_Acerto(res2.getInt("oid_Acerto"));
                      edVolta.setNR_Acerto(res2.getInt("NR_Acerto"));
                      edVolta.setNM_Razao_Social(res2.getString("NM_Razao_Social"));
                      edVolta.setDT_Emissao (FormataData.formataDataBT (res2.getDate ("DT_Emissao")));
                      edVolta.setDT_Saida (FormataData.formataDataBT (res2.getDate ("DT_Saida")));
                      edVolta.setDT_Chegada (FormataData.formataDataBT (res2.getDate ("DT_Chegada")));
                      opcoes_acerto=0;
                      // System.out.println("ACV LISTA 99");

                    }

                      // System.out.println("ACV LISTA 100");

              if (opcoes_acerto ==0) {

                      // System.out.println("ACV LISTA 200");

                edVolta.setOid_Veiculo (res.getString ("OID_Veiculo"));
                edVolta.setOid_Veiculo2(res.getString ("OID_Veiculo2"));
                edVolta.setOid_Veiculo3(res.getString ("OID_Veiculo3"));
                edVolta.setOid_Carreta (" ");
                if (res.getString ("OID_Carreta") != null && res.getString ("OID_Carreta").length()>4)
                  edVolta.setOid_Carreta (res.getString ("OID_Carreta"));

                edVolta.setNM_Unidade (res.getString ("CD_Unidade"));
                edVolta.setNR_Ordem_Servico (res.getLong ("NR_Ordem_Servico"));
                edVolta.setOID_Ordem_Servico (res.getLong ("OID_Ordem_Servico"));
                edVolta.setDT_Ordem_Servico (FormataData.formataDataBT (res.getDate ("DT_Ordem_Servico")));

                      // System.out.println("ACV LISTA 300");

                list.add (edVolta);
                      // System.out.println("ACV LISTA 999");

              }
          }
      } catch (Exception exc) {
          Excecoes excecoes = new Excecoes();
          excecoes.setClasse(this.getClass().getName());
          excecoes.setMensagem("Erro no m�todo listar");
          excecoes.setMetodo("lista(Usuario_ED");
          excecoes.setExc(exc);
          throw excecoes;
      }
      return list;
  }


  public Acerto_ViagemED inclui(Acerto_ViagemED ed) throws Excecoes {


      String sql ="";

      try {

           ed.setOID_Acerto(util.getAutoIncremento("oid_Acerto", "Acertos", true));


          //Busca pr�ximo nr de acerto nos parametros da filial
          sql =" SELECT NR_Proximo_Acerto " +
               " FROM Parametros_Filiais " +
               " WHERE OID_Unidade = " + ed.getOid_Unidade();
              ResultSet res = executasql.executarConsulta(sql);
              ResultSet res2 = null;
              try {
                      while (res.next()) {
                              ed.setNR_Acerto(res.getLong("NR_Proximo_Acerto"));
                      }
              } finally {
                util.closeResultset(res);
              }

              sql =
                  "INSERT INTO Acertos " +
                  "  (OID_Acerto " +
                  "  ,OID_Ordem_Servico " +
                  "  ,OID_Usuario " +
                  "  ,OID_Motorista " +
                  "  ,NR_Acerto " +
                  "  ,DT_Emissao " +
                  "  ,DT_Saida " +
                  "  ,DT_Chegada " +
                  "  ,NR_Kilometragem_Chegada " +
                  "  ,VL_Total_Frete " +
                  "  ,VL_Total_Despesas_Pagas " +
                  "  ,VL_Total_Despesas_Faturadas " +
                  "  ,TX_Observacao " +
                  "  ,Dt_Stamp " +
                  "  ,Usuario_Stamp " +
                  "  ,Dm_Stamp " +
                  "  ,VL_Adiantamento_Viagem " +
                  "  ,VL_Cotacao " +
                  "  ,VL_Comissao " +
                  "  ,PE_Comissao " +
                  "  ,VL_Comissao_Terceiro " +
                  "  ,PE_Comissao_Terceiro " +
                  "  ,PE_Base_Comissao " +
                  "  ,VL_Base_Comissao " +
                  "  ,VL_Recebido " +
                  "  ,VL_Devolvido) " +
                  " VALUES " +
                  "  (" + ed.getOID_Acerto() +
                  "  ," + ed.getOID_Ordem_Servico() +
                  "  ," + ed.getOID_Usuario() +
                  "  ," + JavaUtil.getSQLString(ed.getOID_Motorista()) +
                  "  ," + ed.getNR_Acerto() +
                  "  ," + JavaUtil.getSQLDate(ed.getDT_Emissao()) +
                  "  ," + JavaUtil.getSQLDate(ed.getDT_Saida()) +
                  "  ," + JavaUtil.getSQLDate(ed.getDT_Chegada()) +
                  "  ," + ed.getNR_Kilometragem_Chegada() +
                  "  ," + ed.getVL_Total_Frete() +
                  "  ," + ed.getVL_Total_Despesas_Pagas() +
                  "  ," + ed.getVL_Total_Despesas_Faturadas() +
                  "  ," + JavaUtil.getSQLString(ed.getTX_Observacao()) +
                  "  ," + JavaUtil.getSQLDate(ed.getDt_Stamp()) +
                  "  ," + JavaUtil.getSQLString(ed.getUsuario_Stamp()) +
                  "  ," + JavaUtil.getSQLString(ed.getDm_Stamp()) +
                  "  ," + ed.getVL_Adiantamento_Viagem() +
                  "  ," + ed.getVL_Cotacao() +
                  "  ," + ed.getVL_Comissao() +
                  "  ," + ed.getPE_Comissao() +
                  "  ," + ed.getVL_Comissao_Terceiro() +
                  "  ," + ed.getPE_Comissao_Terceiro() +
                  "  ," + ed.getPE_Base_Comissao() +
                  "  ," + ed.getVL_Base_Comissao() +
                  "  ," + ed.getVL_Recebido() +
                  "  ," + ed.getVL_Devolvido() + ")";


              int i = executasql.executarUpdate(sql);

            sql=  " UPDATE Parametros_Filiais SET NR_Proximo_Acerto = " + (ed.getNR_Acerto() + 1) +
                  " WHERE oid_Unidade = " + ed.getOid_Unidade();
              executasql.executarUpdate(sql);

            sql=  " UPDATE Ordens_Servicos SET DT_Encerramento = " + JavaUtil.getSQLDate(ed.getDT_Emissao()) +
                  " WHERE OID_Ordem_Servico = " + ed.getOID_Ordem_Servico();
              executasql.executarUpdate(sql);

            sql= " UPDATE Ordens_Fretes SET OID_Acerto = " + ed.getOID_Acerto() +
                 " WHERE OID_Acerto is null " +
                 "   AND ORDENS_FRETES.DM_Impresso='S' " +
                 "   AND (ORDENS_FRETES.DM_Situacao<>'C' OR ORDENS_FRETES.DM_Situacao is null) " +
                 "   AND ORDENS_FRETES.DM_Tipo_Adiantamento<>'O' " + //O=Ordem Pagto
                 "   AND ORDENS_FRETES.DM_Frete='A' " +
                 "   and (VL_Ordem_Frete > 0 or VL_Adiantamento1 > 0 or VL_Adiantamento2 > 0 or VL_ADIANTAMENTO_TERCEIRO > 0)" +
                 "   AND Ordens_Fretes.DT_Emissao <= '" + Data.getDataDMY()  + "'" +   //+ ed.getDT_Chegada()
                 "   AND NR_Ordem_Frete > 0 " +
                 "   AND OID_Motorista = '" + ed.getOID_Motorista() + "'";
             // System.out.println(" OF adto " + sql);

             executasql.executarUpdate(sql);


            sql= " UPDATE Ordens_Fretes SET OID_Acerto = " + ed.getOID_Acerto() +
                 " WHERE OID_Acerto is null " +
                 "   AND ORDENS_FRETES.DM_Impresso='S' " +
                 "   AND (ORDENS_FRETES.DM_Situacao<>'C' OR ORDENS_FRETES.DM_Situacao is null) " +
                 "   AND ORDENS_FRETES.DM_Frete='T' " +
                 "   and (VL_Ordem_Frete > 0 or VL_Adiantamento1 > 0 or VL_Adiantamento2 > 0 or VL_ADIANTAMENTO_TERCEIRO > 0)" +
                 //"   AND Ordens_Fretes.DT_Emissao <= '" + Data.getDataDMY()  + "'" +
                 "   AND OID_Motorista = '" + ed.getOID_Motorista() + "'";

             // System.out.println("OF TERCE->> " + sql);

             executasql.executarUpdate(sql);

             //busca placas dois veiculo 2 e 3 da OS...
             sql = "SELECT oid_veiculo2, oid_veiculo3 from ordens_servicos where oid_ordem_servico = " + ed.getOID_Ordem_Servico();
             ResultSet resVeiculos = executasql.executarConsulta(sql);
             while(resVeiculos.next()){
            	 ed.setOid_Veiculo2(resVeiculos.getString("oid_veiculo2"));
            	 ed.setOid_Veiculo3(resVeiculos.getString("oid_veiculo3"));
             }

             double vl_base_comissao_acerto=0, pe_comissao_acerto=0, pe_base_comissao_motorista=0, vl_comissao=0;

             sql = " SELECT sum(vl_total_frete_ctrc) as vl_total_frete_ctrc " +
                   " FROM  Ordens_Fretes " +
                   " WHERE OID_Acerto = " + ed.getOID_Acerto();

             res = this.executasql.executarConsulta (sql);
             while (res.next ()) {

               vl_base_comissao_acerto=res.getDouble("vl_total_frete_ctrc");
               pe_comissao_acerto=parametro_FixoED.getPE_Base_Comissao_Motorista();
               pe_base_comissao_motorista=parametro_FixoED.getPE_Base_Comissao_Motorista();

               if (pe_base_comissao_motorista==0){
                 pe_base_comissao_motorista=100;
               }

                 if ("TF".equals(parametro_FixoED.getDM_Base_Comissao_Motorista())){
                	 vl_comissao=res.getDouble("vl_total_frete_ctrc")*pe_base_comissao_motorista/100;
                 }

                 if ("FP".equals(parametro_FixoED.getDM_Base_Comissao_Motorista())){
                	 vl_comissao=res.getDouble("vl_total_frete_ctrc")*pe_base_comissao_motorista/100;
                 }
                 sql=  " UPDATE Acertos SET vl_base_comissao = " + vl_base_comissao_acerto +
                 	   ", vl_comissao= " + vl_comissao +
                 	   ", pe_base_comissao= " + pe_base_comissao_motorista +
                       " WHERE OID_Acerto = " + ed.getOID_Acerto();
                 executasql.executarUpdate(sql);

             }

      	     sql = " select sum(vl_total_frete_ctrc) as vl_tot, dm_tipo_documento " +
	    	       " from ordens_fretes, ordens_manifestos, manifestos, viagens, conhecimentos  " +
	    		   " where ordens_fretes.oid_ordem_frete = ordens_manifestos.oid_ordem_frete " +
	    		   " and ordens_manifestos.oid_manifesto = manifestos.oid_manifesto " +
	    		   " and manifestos.oid_manifesto = viagens.oid_manifesto" +
	    		   " and viagens.oid_conhecimento = conhecimentos.oid_conhecimento " +
	    		   " and ordens_fretes.vl_total_frete_ctrc > 0 " +
	    		   " and ordens_fretes.oid_acerto = " + ed.getOID_Acerto() +
	    		   " group by conhecimentos.dm_tipo_documento";

      	    res = this.executasql.executarConsulta(sql);

	        double VL_Frete_Proprio = 0;
	        double VL_Frete_Terceiro = 0;

	        while (res.next()) {
	    	  if (res.getString("dm_tipo_documento").equals("C")){
	 	    	VL_Frete_Proprio = res.getDouble("vl_tot");
	    	  }else{
				VL_Frete_Terceiro = res.getDouble("vl_tot");
	    	  }
	        }

			sql = " UPDATE Acertos " +
			   " SET   vl_frete_proprio =  " + VL_Frete_Proprio +
			   "       ,vl_frete_terceiro =  " + VL_Frete_Terceiro +
			   " WHERE OID_Acerto = " + ed.getOID_Acerto();
			i = executasql.executarUpdate(sql);
      }

      catch (SQLException e) {
          throw new Excecoes(e.getMessage(), e, getClass().getName(), "inclui(Acerto_ViagemED ed)");
      }
      return ed;
  }

  public Acerto_ViagemED inclui_Despesas_Custo_Fixo (Acerto_ViagemED ed) throws Excecoes {

             // System.out.println("inclui_Despesas_Custo_Fixo *** ");

    String sql = null;
    ResultSet res = null;

    
    return ed;
  }


  public void deleta(Acerto_ViagemED ed) throws Excecoes {



      try {
        String sql ="";
        ResultSet res = null;

            // System.out.println("Delete Exclui ");

        if ("S".equals(parametro_FixoED.getDM_Gera_Lancamento_Conta_Corrente_Acerto_Contas())) {

          Movimento_Conta_CorrenteED edMCC = new Movimento_Conta_CorrenteED();

          sql = " SELECT Contas.oid_tipo_documento, Contas.oid_historico, Contas.oid_Conta  " +
          " FROM   Contas " +
          " WHERE  Contas.oid_Conta =" + parametro_FixoED.getOID_Conta_Acerto_Contas();

            // System.out.println(sql);

          res = this.executasql.executarConsulta(sql);
          while (res.next()) {
            edMCC.setOid_Conta(res.getInt("oid_Conta"));
            edMCC.setOID_Tipo_Documento(new Integer(res.getInt("oid_tipo_documento")));
            edMCC.setOid_Historico(new Integer(res.getInt("oid_historico")));
          }

          sql = " SELECT Movimentos_Contas_Correntes.oid_Conta_Corrente, Acertos.oid_Acerto, Acertos.OID_Movimento_Conta_Corrente,  Acertos.DT_Saida, Acertos.DT_Chegada, Acertos.NR_Acerto, Acertos.VL_Saldo, Acertos.PE_Comissao, Acertos.VL_Comissao " +
                " FROM   Acertos, Movimentos_Contas_Correntes " +
                " WHERE  Acertos.oid_Movimento_Conta_Corrente =  Movimentos_Contas_Correntes.oid_Movimento_Conta_Corrente " +
                " AND    Acertos.oid_Acerto =" + ed.getOID_Acerto ();

            // System.out.println(sql);

          res = this.executasql.executarConsulta(sql);
          while (res.next()) {
            // System.out.println("Delete Exclui no W");

            edMCC.setOid_Acerto_Contas(res.getInt("oid_Acerto"));
            edMCC.setOid_Conta_Corrente(res.getString("oid_Conta_Corrente"));

            // System.out.println("Delete Exclui no W 2");
            edMCC.setDT_Movimento_Conta_Corrente(Data.getDataDMY()); //.getString("DT_Chegada"));
            edMCC.setOid_Lote_Pagamento(0);
            edMCC.setNR_Documento(res.getString("NR_Acerto"));
            edMCC.setDM_Tipo_Lancamento("G");
            edMCC.setDM_Debito_Credito("C");
            // System.out.println("Delete Exclui no W 9");

            edMCC.setVL_Lancamento(new Double(res.getDouble("VL_Saldo")));

            if (edMCC.getVL_Lancamento().doubleValue() < 0) {
                edMCC.setDM_Debito_Credito("D");
                edMCC.setVL_Lancamento(new Double(edMCC.getVL_Lancamento().doubleValue() * -1));
            }
            edMCC.setNM_Complemento_Historico("CANCELADO ACERTO" +  res.getString("NR_Acerto"));

            // System.out.println("Delete Exclui no W99");

            Movimento_Conta_CorrenteED edVolta = new Movimento_Conta_CorrenteED();
            edVolta = new Movimento_Conta_CorrenteBD(this.executasql).inclui(edMCC);


          }
        }

        // System.out.println("Delete Exclui 8");

        sql = " Update Conhecimentos set oid_Acerto = null " +
              " WHERE OID_Acerto = " + ed.getOID_Acerto();
        executasql.executarUpdate(sql);

        // System.out.println("Delete Exclui 9");

        sql = " Update Ordens_Fretes set oid_Acerto = null " +
              " WHERE OID_Acerto = " + ed.getOID_Acerto();
        executasql.executarUpdate(sql);

        sql = " UPDATE Ordens_Servicos SET DT_Encerramento = null " +
              " WHERE OID_Ordem_Servico = " + ed.getOID_Ordem_Servico();
        executasql.executarUpdate(sql);

        sql =" DELETE FROM  Acertos  " +
             " WHERE OID_Acerto = " + ed.getOID_Acerto();
        executasql.executarUpdate(sql);

        sql =" DELETE FROM  Movimentos_Ordens_Servicos " +
              " WHERE DM_Custo_Fixo ='S' "  +
              " AND   OID_Ordem_Servico = " + ed.getOID_Ordem_Servico();
        executasql.executarUpdate(sql);

        //sql =" UPDATE Movimentos_Ordens_Servicos " +
        //      " SET   DM_Acerto ='N' "  +
        //      " WHERE OID_Ordem_Servico = " + ed.getOID_Ordem_Servico();
        //executasql.executarUpdate(sql);


      } catch (Exception e) {
              throw new Excecoes(e.getMessage(), e, this.getClass().getName(), "deleta(Acerto_ViagemED ed)");
      }
  }

  public void altera(Acerto_ViagemED ed) throws Excecoes {

    //Acerto_ViagemED edVolta = new Acerto_ViagemED();

    //edVolta = this.somaMovimento(ed);
    //ed.setVL_Saldo(edVolta.getVL_Saldo());

    ed.setVL_Saldo (totaliza_Saldo (ed));

    String sql =
        " UPDATE Acertos " +
        " SET OID_Motorista = " + JavaUtil.getSQLString(ed.getOID_Motorista()) +
        "    ,DT_Saida = " + JavaUtil.getSQLDate(ed.getDT_Saida()) +
        "    ,DT_Chegada = " + JavaUtil.getSQLDate(ed.getDT_Chegada()) +
        "    ,NR_Kilometragem_Chegada = " + ed.getNR_Kilometragem_Chegada() +
        "    ,TX_Observacao = " + JavaUtil.getSQLString(ed.getTX_Observacao()) +
        "    ,VL_Total_Frete = " + ed.getVL_Total_Frete() +
        "    ,VL_Total_Despesas_Pagas = " + ed.getVL_Total_Despesas_Pagas() +
        "    ,VL_Total_Despesas_Faturadas = " + ed.getVL_Total_Despesas_Faturadas() +
        "    ,VL_Adiantamento_Viagem = " + ed.getVL_Adiantamento_Viagem() +
        "    ,VL_Frete_Proprio = " + ed.getVL_Frete_Proprio() +
        "    ,VL_Frete_Terceiro = " + ed.getVL_Frete_Terceiro() +
        "    ,VL_Saldo = " + ed.getVL_Saldo() +
        "    ,DM_Situacao = 'S' " +
        "    ,VL_Recebido = " + ed.getVL_Recebido() +
        "    ,VL_Devolvido = " + ed.getVL_Devolvido();

       sql += "  ,VL_Comissao = " + ed.getVL_Comissao() +
              "  ,PE_Comissao = " + ed.getPE_Comissao();
     if (ed.getPE_Comissao_Terceiro()>0) {
        sql += "  ,VL_Comissao_Terceiro = " + ed.getVL_Comissao_Terceiro() +
               "  ,PE_Comissao_Terceiro = " + ed.getPE_Comissao_Terceiro();
     }


     sql+=   " WHERE OID_Acerto = " + ed.getOID_Acerto();

     try {
       int i = executasql.executarUpdate (sql);
     }
     catch (Exception e) {
       throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "altera(Acerto_ViagemED ed)");
     }

    sql = " UPDATE Veiculos SET nr_kilometragem_atual= " + ed.getNR_Kilometragem_Chegada() +
          " WHERE oid_Veiculo ='" + ed.getOid_Veiculo() + "'";
     try {
       int i = executasql.executarUpdate (sql);
     }
     catch (Exception e) {
       throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "altera(Acerto_ViagemED ed)");
     }

     this.getByRecord(ed);

  }

  public void finaliza(Acerto_ViagemED ed) throws Excecoes {
    try {
      Movimento_Conta_CorrenteED edVolta = new Movimento_Conta_CorrenteED();

      if ("S".equals(parametro_FixoED.getDM_Gera_Lancamento_Contabil_Acerto()))
      {
          //faz os lancamentos contabeis do movimento
          Lancamento_ContabilED lc = new Lancamento_ContabilED();
          lc.setOID_Acerto(String.valueOf(ed.getOID_Acerto()));
          new Lancamento_ContabilBD(this.executasql).inclui_CTB_Acerto(lc);
      }


      if ("S".equals(parametro_FixoED.getDM_Gera_Lancamento_Conta_Corrente_Acerto_Contas())) {
        Movimento_Conta_CorrenteED edMCC = new Movimento_Conta_CorrenteED();

        String
        sql = " SELECT Contas.oid_tipo_documento, Contas.oid_historico, Contas.oid_Conta  " +
        " FROM   Contas " +
        " WHERE  Contas.oid_Conta =" + parametro_FixoED.getOID_Conta_Acerto_Contas();

        ResultSet res = this.executasql.executarConsulta(sql);
        while (res.next()) {
          edMCC.setOid_Conta(res.getInt("oid_Conta"));
          edMCC.setOID_Tipo_Documento(new Integer(res.getInt("oid_tipo_documento")));
          edMCC.setOid_Historico(new Integer(res.getInt("oid_historico")));
        }

        sql = " SELECT Acertos.oid_Acerto, Acertos.DT_Saida, Acertos.DT_Chegada, Acertos.NR_Acerto, Acertos.VL_Saldo, Acertos.PE_Comissao, Acertos.VL_Comissao " +
        " FROM   Acertos " +
        " WHERE  Acertos.oid_Acerto =" + ed.getOID_Acerto();

        res = this.executasql.executarConsulta(sql);
        while (res.next()) {
          edMCC.setOid_Acerto_Contas(res.getInt("oid_Acerto"));
          edMCC.setOid_Conta_Corrente(ed.getOID_Conta_Corrente());
          edMCC.setDT_Movimento_Conta_Corrente(Data.getDataDMY()); //.getString("DT_Chegada"));
          edMCC.setOid_Lote_Pagamento(0);
          edMCC.setNR_Documento(res.getString("NR_Acerto"));
          edMCC.setDM_Tipo_Lancamento("G");
          edMCC.setDM_Debito_Credito("D");
          edMCC.setVL_Lancamento(new Double(res.getString("VL_Saldo")));

          if (edMCC.getVL_Lancamento().doubleValue() < 0) {
              edMCC.setDM_Debito_Credito("C");
              edMCC.setVL_Lancamento(new Double(edMCC.getVL_Lancamento().doubleValue() * -1));
          }
          edMCC.setNM_Complemento_Historico("Viagem de " + FormataData.formataDataBT(res.getDate("DT_Saida")) + " a "+ FormataData.formataDataBT(res.getDate("DT_Chegada")) );
          edVolta = new Movimento_Conta_CorrenteBD(this.executasql).inclui(edMCC);
        }
        if (edVolta.getOid_Movimento_Conta_Corrente()>0 ) {
           sql = " UPDATE Acertos " +
                 " SET OID_Movimento_Conta_Corrente = " + edVolta.getOid_Movimento_Conta_Corrente() +
                 "    ,DM_Situacao = 'F' " +
                 " WHERE OID_Acerto = " + ed.getOID_Acerto();
              int i = executasql.executarUpdate(sql);
        }
      }
      else {

 	    String sql = " select sum(vl_total_frete_ctrc) as vl_tot, dm_tipo_documento " +
 	    		" from ordens_fretes, ordens_manifestos, manifestos, viagens, conhecimentos  " +
 	    		" where ordens_fretes.oid_ordem_frete = ordens_manifestos.oid_ordem_frete " +
 	    		" and ordens_manifestos.oid_manifesto = manifestos.oid_manifesto " +
 	    		" and manifestos.oid_manifesto = viagens.oid_manifesto" +
 	    		" and viagens.oid_conhecimento = conhecimentos.oid_conhecimento " +
 	    		" and ordens_fretes.vl_total_frete_ctrc > 0 " +
 	  			" and ordens_fretes.oid_acerto = " + ed.getOID_Acerto() +
 	    		" group by conhecimentos.dm_tipo_documento";

 	    ResultSet res = null;
 	    res = this.executasql.executarConsulta(sql);

 	    double VL_Frete_Proprio = 0;
 	    double VL_Frete_Terceiro = 0;

 	    while (res.next()) {
 	    	if (res.getString("dm_tipo_documento").equals("C")){
 	 	    	VL_Frete_Proprio = res.getDouble("vl_tot");
 	    	}else{
 				VL_Frete_Terceiro = res.getDouble("vl_tot");
 	    	}
		}
        sql = " UPDATE Acertos " +
            " SET   DM_Situacao = 'F'" +
            "       ,vl_frete_proprio =  " + VL_Frete_Proprio +
            "       ,vl_frete_terceiro =  " + VL_Frete_Terceiro +
            " WHERE OID_Acerto = " + ed.getOID_Acerto();
        int i = executasql.executarUpdate(sql);
      }

    } catch (Exception e) {
            throw new Excecoes(e.getMessage(), e, this.getClass().getName(), "finaliza(Acerto_ViagemED ed)");
    }
}

  public void reabre(Acerto_ViagemED ed) throws Excecoes {
    String sql = null;
    ResultSet res = null;

  	try {
        if ("S".equals(parametro_FixoED.getDM_Gera_Lancamento_Contabil()))
        {
            //faz os lancamentos contabeis do movimento
            Lancamento_ContabilED lc = new Lancamento_ContabilED();
            lc.setOID_Acerto(String.valueOf(ed.getOID_Acerto()));
            new Lancamento_ContabilBD(this.executasql).deleta_CTB_Acerto(lc);
        }

        if ("S".equals(parametro_FixoED.getDM_Gera_Lancamento_Conta_Corrente_Acerto_Contas())) {
            Movimento_Conta_CorrenteED edMCC = new Movimento_Conta_CorrenteED();

            sql = " SELECT Acertos.OID_Movimento_Conta_Corrente " +
            " FROM   Acertos " +
            " WHERE  Acertos.oid_Acerto =" + ed.getOID_Acerto();

            res = this.executasql.executarConsulta(sql);
            while (res.next()) {
            	edMCC.setOid_Movimento_Conta_Corrente(res.getLong("OID_Movimento_Conta_Corrente"));
            	if (edMCC.getOid_Movimento_Conta_Corrente() > 0){
                	new Movimento_Conta_CorrenteBD(this.executasql).deleta(edMCC);
            	}
            }
          }
        sql = " UPDATE Acertos " +
        " SET   DM_Situacao = 'S', OID_Movimento_Conta_Corrente = null " +
        " WHERE OID_Acerto = " + ed.getOID_Acerto();
        int i = executasql.executarUpdate(sql);

    } catch (Exception e) {
            throw new Excecoes(e.getMessage(), e, this.getClass().getName(), "finaliza(Acerto_ViagemED ed)");
    }
}

  public Acerto_ViagemED getByRecord(Acerto_ViagemED ed)throws Excecoes {

      Ordem_FreteED edOf = new Ordem_FreteED();
      BancoUtil bancoUtil = new BancoUtil();

      edOf.setOID_Acerto(ed.getOID_Acerto());

	  String DM_Situacao = bancoUtil.getTableStringValue("DM_Situacao", "acertos", "oid_Acerto = "	+ ed.getOID_Acerto());

      Ordem_FreteBD ofBD = new Ordem_FreteBD(this.executasql);

      if (!"F".equals(DM_Situacao) && !"S".equals(DM_Situacao)){
          ofBD.ajusta_acerto(edOf);
      }

      String sql =" SELECT acertos.vl_base_comissao, Acertos.VL_Frete_Proprio, Acertos.VL_Frete_Terceiro, Acertos.pe_comissao, Acertos.vl_comissao, Acertos.pe_comissao_terceiro, Acertos.vl_comissao_terceiro, Unidades.CD_Unidade, Ordens_Servicos.NR_Kilometragem, Ordens_Servicos.NR_Ordem_Servico, Ordens_Servicos.oid_Ordem_Servico, Ordens_Servicos.oid_Veiculo, Acertos.OID_Acerto, Acertos.OID_Motorista, Acertos.NR_Acerto, Acertos.DT_Emissao, Acertos.DT_Saida, Acertos.oid_Usuario, Acertos.DT_Chegada, Acertos.NR_Kilometragem_Chegada, Acertos.Dt_Stamp, Acertos.Usuario_Stamp, Acertos.Dm_Stamp, Acertos.VL_Total_Frete, Acertos.VL_Total_Despesas_Pagas, Acertos.VL_Total_Despesas_Faturadas, Acertos.TX_Observacao, Acertos.VL_Saldo, Acertos.VL_Retido, Acertos.VL_Devolvido, Acertos.VL_Recebido, Acertos.VL_Adiantamento_Viagem, Acertos.DM_Impresso, Acertos.oid_Movimento_Conta_Corrente, Acertos.DM_Situacao " +
      			  " FROM   Acertos, Ordens_Servicos, Unidades " +
                  " WHERE  Acertos.oid_Ordem_Servico =  Ordens_Servicos.oid_Ordem_Servico " +
                  " AND    Ordens_Servicos.OID_Unidade = Unidades.OID_Unidade " +
                  " AND    Acertos.oid_Acerto = " + ed.getOID_Acerto();

      Acerto_ViagemED edVolta = new Acerto_ViagemED();
      try {
          ResultSet res = this.executasql.executarConsulta(sql);
          while (res.next()){

            edVolta.setOID_Acerto(res.getLong("oid_Acerto"));
            edVolta.setOid_Movimento_Conta_Corrente(res.getLong("OID_Movimento_Conta_Corrente"));
            edVolta.setNR_Acerto(res.getInt("NR_Acerto"));
            edVolta.setNM_Unidade (res.getString ("CD_Unidade"));
            edVolta.setNR_Ordem_Servico (res.getLong ("NR_Ordem_Servico"));

            edVolta.setOID_Ordem_Servico(res.getLong("OID_Ordem_Servico"));
            edVolta.setOID_Usuario(res.getLong("OID_Usuario"));
            edVolta.setOID_Motorista(res.getString("OID_Motorista"));
            edVolta.setOid_Veiculo(res.getString("Oid_Veiculo"));
            edVolta.setNR_Acerto(res.getLong("NR_Acerto"));
            edVolta.setDT_Emissao(FormataData.formataDataBT(res.getDate("DT_Emissao")));
            edVolta.setDT_Saida(FormataData.formataDataBT(res.getDate("DT_Saida")));
            edVolta.setDT_Chegada(FormataData.formataDataBT(res.getDate("DT_Chegada")));
            edVolta.setNR_Kilometragem_Saida(res.getLong("NR_Kilometragem"));
            edVolta.setNR_Kilometragem_Chegada(res.getLong("NR_Kilometragem_Chegada"));

            edVolta.setDt_Stamp(FormataData.formataDataBT(res.getDate("Dt_Stamp")));
            edVolta.setUsuario_Stamp(res.getString("Usuario_Stamp"));
            edVolta.setVL_Retido(res.getDouble("VL_Retido"));
            edVolta.setVL_Devolvido(res.getDouble("VL_Devolvido"));
            edVolta.setVL_Recebido(res.getDouble("VL_Recebido"));

            edVolta.setVL_Total_Frete(res.getDouble("VL_Total_Frete"));
            edVolta.setVL_Total_Despesas_Pagas(res.getDouble("VL_Total_Despesas_Pagas"));
            edVolta.setVL_Total_Despesas_Faturadas(res.getDouble("VL_Total_Despesas_Faturadas"));
            edVolta.setTX_Observacao(res.getString("TX_Observacao"));
            edVolta.setVL_Adiantamento_Viagem(res.getDouble("VL_Adiantamento_Viagem"));
            edVolta.setDM_Impresso(res.getString("DM_Impresso"));
            edVolta.setDM_Situacao(res.getString("DM_Situacao"));
            edVolta.setVL_Frete_Proprio(res.getDouble("VL_Frete_Proprio"));
            edVolta.setVL_Frete_Terceiro(res.getDouble("VL_Frete_Terceiro"));
            edVolta.setVL_Total_Frete(res.getDouble("VL_Frete_Proprio")+res.getDouble("VL_Frete_Terceiro"));

            if ("F".equals(res.getString("DM_Situacao")) || "S".equals(res.getString("DM_Situacao"))){
              edVolta.setVL_Saldo(res.getDouble("VL_Saldo"));
              edVolta.setPE_Comissao(res.getDouble("pe_comissao"));
              edVolta.setVL_Comissao(res.getDouble("vl_comissao"));
              edVolta.setPE_Comissao_Terceiro(res.getDouble("pe_comissao_terceiro"));
              edVolta.setVL_Comissao_Terceiro(res.getDouble("vl_comissao_terceiro"));
              edVolta.setVL_Base_Comissao(res.getDouble("vl_base_comissao"));
              edVolta = pegaMedia(edVolta);
            }else {
              edVolta = somaMovimento(edVolta);
            }

            edVolta = this.consultaSituacao(edVolta);

          }
      } catch(SQLException exc){
          throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "getByRecord()");
      }
      return edVolta;
  }

  public Acerto_ViagemED  consultaAcertoNaoFinalizado(Acerto_ViagemED ed)throws Excecoes {
    Acerto_ViagemED edVolta = new Acerto_ViagemED();

                // System.out.println("consultaAcertoNaoFinalizado");


    try {
      ResultSet res = null;
      if (ed.getOid_Veiculo().length()>0) {
        String sql =" SELECT Ordens_Servicos.oid_Ordem_Servico, " +
                    "        Ordens_Servicos.NR_Ordem_Servico " +
                    " FROM   Ordens_Servicos " +
                    " WHERE  Ordens_Servicos.DT_Encerramento is null " +
                    " AND    Ordens_Servicos.oid_Tipo_Servico = " + parametro_FixoED.getOID_Tipo_Servico_Acerto_Contas() +
                    " AND    Ordens_Servicos.oid_Veiculo = '" + ed.getOid_Veiculo() + "'";
                // System.out.println(sql);

                res = this.executasql.executarConsulta(sql);
            while (res.next()) {
               edVolta.setOID_Ordem_Servico(res.getLong("oid_Ordem_Servico"));
               edVolta.setNR_Ordem_Servico(res.getLong("NR_Ordem_Servico"));
            }
      }
      if (ed.getOid_Motorista().length()>0) {
        String sql =" SELECT Acertos.NR_Acerto, " +
                    "        Ordens_Servicos.oid_Ordem_Servico, " +
                    "        Ordens_Servicos.NR_Ordem_Servico " +
                    " FROM   Ordens_Servicos, Acertos " +
                    " WHERE  Ordens_Servicos.oid_Ordem_Servico = Acertos.oid_Ordem_Servico " +
                    " AND    Acertos.DM_Situacao <>'F' " +
                    " AND    Acertos.oid_Motorista = '" + ed.getOid_Motorista() + "'";

                // System.out.println(sql);

            res = this.executasql.executarConsulta(sql);
            while (res.next()) {
               edVolta.setOID_Ordem_Servico(res.getLong("oid_Ordem_Servico"));
               edVolta.setNR_Ordem_Servico(res.getLong("NR_Ordem_Servico"));
               edVolta.setNR_Acerto(res.getLong("NR_Acerto"));
            }
      }



      } catch(SQLException exc){
          throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "getByRecord()");
      }
      return edVolta;
  }


  public Acerto_ViagemED somaMovimento(Acerto_ViagemED edVolta)throws Excecoes {

    //Acerto_ViagemED edVolta = new Acerto_ViagemED();

    String sql="";
    String dm_pagamento_diaria="F";
    int moeda_padrao = parametro_FixoED.getOID_Moeda_Padrao();
    double vl_cotacao=1;
    double vl_movimento_pago=0;
    double vl_movimento_faturado=0;
    double vl_movimento_extra=0;
    double pe_comissao=0;
    double pe_comissao_terceiro=0;
    double vl_base_comissao = 0;
    double tvl_comissao = 0;
    double vl_comissao_acerto = 0;
    double tpe_comissao = 0;
    double tvl_base_comissao = 0;
    double tt_abastecimento=0;
    double tt_carga_descarga=0;

    double litros=0;
    double nr_media_km=0;
    double nr_media1=0;
    double nr_media2=0;
    double pe_comissao_media1=0;
    double pe_comissao_media2=0;
    ResultSet res = null;

    try {


     sql =" SELECT Acertos.VL_Cotacao, Acertos.vl_comissao, Motoristas.dm_pagamento_diaria ,Motoristas.PE_Comissao ,Motoristas.PE_Comissao_Media1, Motoristas.PE_Comissao_Media2 ,Motoristas.NR_Media1 ,Motoristas.NR_Media2, Motoristas.PE_Comissao_Terceiro, Modelos_Veiculos.NR_Media_KM  " +
          " FROM Acertos, Motoristas, Veiculos, Modelos_Veiculos, Ordens_Servicos " +
              " WHERE Acertos.oid_Motorista = Motoristas.oid_Motorista" +
              " AND   Acertos.oid_Ordem_Servico =  Ordens_Servicos.oid_Ordem_Servico " +
              " AND   Ordens_Servicos.oid_Veiculo = Veiculos.oid_Veiculo " +
              " AND   Veiculos.oid_Modelo_Veiculo = Modelos_Veiculos.oid_Modelo_Veiculo " +
              " AND   Acertos.oid_Acerto= " + edVolta.getOID_Acerto();

      res = this.executasql.executarConsulta(sql);
      while (res.next()) {
              vl_cotacao=res.getDouble("VL_Cotacao");
              dm_pagamento_diaria=res.getString("dm_pagamento_diaria");
              pe_comissao=res.getDouble("pe_comissao");
              vl_comissao_acerto=res.getDouble("vl_comissao");
              pe_comissao_terceiro=res.getDouble("pe_comissao_terceiro");
              nr_media_km=res.getDouble("NR_Media_KM");

              pe_comissao_media1=res.getDouble("pe_comissao_media1");
              pe_comissao_media2=res.getDouble("pe_comissao_media2");

              nr_media1=res.getDouble("nr_media1");
              nr_media2=res.getDouble("nr_media2");

      }


      sql ="SELECT Movimentos_Ordens_Servicos.VL_Movimento, oid_moeda, VL_Documento, DM_FATURADO_PAGO, DM_Tipo_Despesa, NM_Tipo_Servico, Tipos_Servicos.DM_Medida,  Movimentos_Ordens_Servicos.NR_Quantidade " +
          " FROM Movimentos_Ordens_Servicos, Tipos_Servicos " +
          " WHERE Movimentos_Ordens_Servicos.oid_Tipo_Servico = Tipos_Servicos.oid_Tipo_Servico "+
          " AND Movimentos_Ordens_Servicos.oid_Ordem_Servico = " + edVolta.getOID_Ordem_Servico();

      // System.out.println("->> " +sql);

      res = this.executasql.executarConsulta(sql);
      while (res.next()){

        if ("L".equals(res.getString("DM_Medida"))) {
          litros += res.getDouble("NR_Quantidade");
          tt_abastecimento += res.getDouble("VL_Movimento");
        }
        if ("E".equals(res.getString("DM_Faturado_Pago"))) {
          litros += res.getDouble("NR_Quantidade");
        }

        // System.out.println("Tipo DM_Tipo_Despesa : " +res.getString("DM_Tipo_Despesa"));

        if ("D".equals (res.getString ("DM_Tipo_Despesa"))) { //CARGA/DESCARGA
          tt_carga_descarga+=res.getDouble ("VL_Movimento") * vl_cotacao;
        }

        if ("S".equals (res.getString ("DM_Tipo_Despesa"))) { //DIARIAS
          if ("F".equals (dm_pagamento_diaria)) {
            if (res.getLong ("oid_Moeda") != moeda_padrao) //moeda =1 padraoreal
              vl_movimento_extra += res.getDouble ("VL_Movimento") * vl_cotacao;
            else vl_movimento_extra += res.getDouble ("VL_Documento");
          }
          else {
            if (res.getLong ("oid_Moeda") != moeda_padrao) //moeda =1 padraoreal
              vl_movimento_pago += res.getDouble ("VL_Movimento") * vl_cotacao;
            else vl_movimento_pago += res.getDouble ("VL_Documento");
          }
        }
        else {
          if ("P".equals (res.getString ("DM_FATURADO_PAGO"))) {
            if (res.getLong ("oid_Moeda") != moeda_padrao) //moeda =1 padraoreal
              vl_movimento_pago += res.getDouble ("VL_Movimento") * vl_cotacao;
            else vl_movimento_pago += res.getDouble ("VL_Documento");
          }
          if ("F".equals (res.getString ("DM_FATURADO_PAGO"))) {
            if (res.getLong ("oid_Moeda") != moeda_padrao) //moeda =1 padraoreal
              vl_movimento_faturado += res.getDouble ("VL_Movimento") *
                  vl_cotacao;
            else vl_movimento_faturado += res.getDouble ("VL_Documento");
          }

        }
      }

      edVolta.setVL_Total_Carga_Descarga(tt_carga_descarga);
      edVolta.setNR_Litros(litros);
      edVolta.setNR_Kilometragem_Total(edVolta.getNR_Kilometragem_Chegada()-edVolta.getNR_Kilometragem_Saida());
      edVolta.setNR_Media(edVolta.getNR_Kilometragem_Total()/edVolta.getNR_Litros());

      edVolta.setVL_Total_Despesas_Pagas(vl_movimento_pago);
      edVolta.setVL_Total_Despesas_Faturadas(vl_movimento_faturado);
      edVolta.setVL_Total_Extras(vl_movimento_extra);


      sql =" SELECT SUM(Ordens_Fretes.VL_Adiantamento1), SUM(Ordens_Fretes.VL_Adiantamento2), SUM(Ordens_Fretes.VL_Adiantamento_Terceiro), SUM(Ordens_Fretes.VL_Descontos) " +
           " FROM Ordens_Fretes "+
           " WHERE Ordens_Fretes.OID_Acerto = " + edVolta.getOID_Acerto();

      res = this.executasql.executarConsulta(sql);
      while (res.next()){
              edVolta.setVL_Adiantamento_Viagem( res.getDouble(1) + res.getDouble(2) + res.getDouble(3) - res.getDouble(4));
      }

      // System.out.println(" Soma =" + edVolta.getVL_Adiantamento_Viagem());

      sql = " SELECT SUM(Ordens_Fretes.VL_Ordem_Frete) " +
            " FROM Ordens_Fretes " +
            " WHERE Ordens_Fretes.OID_Acerto = " + edVolta.getOID_Acerto() +
            " AND   Ordens_Fretes.DM_Frete ='T'";

      res = this.executasql.executarConsulta(sql);
      while (res.next()){
              edVolta.setVL_Frete_Terceiro(res.getDouble(1));
      }

      if (pe_comissao_media1>0 && nr_media1>=nr_media_km){
    	  pe_comissao+=pe_comissao_media1;
      }
      if (pe_comissao_media2>0 && nr_media2>=nr_media_km){
    	  pe_comissao+=pe_comissao_media2;
      }
      tpe_comissao = pe_comissao;


      double vl_base_comissao_acerto=0, pe_comissao_acerto=0, pe_base_comissao_motorista=0, vl_comissao=0;

      sql = " SELECT sum(vl_total_frete_ctrc) as vl_total_frete_ctrc " +
            " FROM  Ordens_Fretes " +
            " WHERE OID_Acerto = " + edVolta.getOID_Acerto();

      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {

        vl_base_comissao_acerto=res.getDouble("vl_total_frete_ctrc");
        pe_comissao_acerto=parametro_FixoED.getPE_Base_Comissao_Motorista();
        pe_base_comissao_motorista=parametro_FixoED.getPE_Base_Comissao_Motorista();

        if (pe_base_comissao_motorista==0){
          pe_base_comissao_motorista=100;
        }

          if ("TF".equals(parametro_FixoED.getDM_Base_Comissao_Motorista())){
         	 vl_comissao=res.getDouble("vl_total_frete_ctrc")*pe_base_comissao_motorista/100;
          }

          if ("FP".equals(parametro_FixoED.getDM_Base_Comissao_Motorista())){
         	 vl_comissao=res.getDouble("vl_total_frete_ctrc")*pe_base_comissao_motorista/100;
          }
          sql=  " UPDATE Acertos SET vl_base_comissao = " + vl_base_comissao_acerto +
          	   ", vl_comissao= " + vl_comissao +
          	   ", pe_base_comissao= " + pe_base_comissao_motorista +
                " WHERE OID_Acerto = " + edVolta.getOID_Acerto();
          executasql.executarUpdate(sql);

      }

      sql = " select sum(vl_total_frete_ctrc) as vl_tot, dm_tipo_documento " +
 		    " from ordens_fretes, ordens_manifestos, manifestos, viagens, conhecimentos  " +
 		    " where ordens_fretes.oid_ordem_frete = ordens_manifestos.oid_ordem_frete " +
 		    " and ordens_manifestos.oid_manifesto = manifestos.oid_manifesto " +
 		    " and manifestos.oid_manifesto = viagens.oid_manifesto" +
 		    " and viagens.oid_conhecimento = conhecimentos.oid_conhecimento " +
 		    " and ordens_fretes.vl_total_frete_ctrc > 0 " +
			" and ordens_fretes.oid_acerto = " + edVolta.getOID_Acerto() +
			" group by conhecimentos.dm_tipo_documento";

      res = this.executasql.executarConsulta(sql);

      double VL_Frete_Proprio = 0;
      double VL_Frete_Terceiro = 0;

      while (res.next()) {
    	  if (res.getString("dm_tipo_documento").equals("C")){
    		  VL_Frete_Proprio = res.getDouble("vl_tot");
    	  }else{
			  VL_Frete_Terceiro = res.getDouble("vl_tot");
    	  }
      }

	  sql = " UPDATE Acertos " +
		    " SET   vl_frete_proprio =  " + VL_Frete_Proprio +
		    "       ,vl_frete_terceiro =  " + VL_Frete_Terceiro +
		    " WHERE OID_Acerto = " + edVolta.getOID_Acerto();
	  executasql.executarUpdate(sql);

      edVolta.setVL_Total_Frete(edVolta.getVL_Frete_Proprio()+edVolta.getVL_Frete_Terceiro());

      sql = " update conhecimentos set oid_acerto = " + edVolta.getOID_Acerto() + " where oid_conhecimento in (" +
      		" select viagens.oid_conhecimento " +
	        " from ordens_fretes, ordens_manifestos, manifestos, viagens " +
	        " where ordens_fretes.oid_ordem_frete = ordens_manifestos.oid_ordem_frete " +
	        " and ordens_manifestos.oid_manifesto = manifestos.oid_manifesto " +
	        " and manifestos.oid_manifesto = viagens.oid_manifesto" +
	        " and ordens_fretes.vl_total_frete_ctrc > 0 " +
		    " and ordens_fretes.oid_acerto = " + edVolta.getOID_Acerto() + ")";

	  executasql.executarUpdate(sql);

      //comissao terceiros

      if (pe_comissao_media1>0 && nr_media1>=nr_media_km){
    	  pe_comissao_terceiro+=pe_comissao_media1;
      }
      if (pe_comissao_media2>0 && nr_media2>=nr_media_km){
    	  pe_comissao_terceiro+=pe_comissao_media2;
      }

      edVolta.setPE_Comissao_Terceiro(pe_comissao_terceiro);

      if (parametro_FixoED.getPE_Comissao_Motorista_Media()>0 && nr_media_km<= edVolta.getNR_Media()){
        edVolta.setPE_Comissao(pe_comissao + parametro_FixoED.getPE_Comissao_Motorista_Media());
        edVolta.setPE_Comissao_Terceiro(pe_comissao_terceiro + parametro_FixoED.getPE_Comissao_Motorista_Media());
      }

      edVolta.setVL_Comissao_Terceiro(edVolta.getVL_Frete_Terceiro()*edVolta.getPE_Comissao_Terceiro()/100);

      if ("FR_BRUTO_MENOS_COMBUST".equals (parametro_FixoED.getDM_Base_Comissao_Motorista ())) {
        tvl_base_comissao = edVolta.getVL_Total_Frete () - tt_abastecimento;
        tpe_comissao = pe_comissao;
        tvl_comissao = tvl_base_comissao * tpe_comissao / 100;
        edVolta.setVL_Comissao_Terceiro(0);
        edVolta.setPE_Comissao_Terceiro(0);
      }

      edVolta.setPE_Comissao(tpe_comissao);
      edVolta.setVL_Base_Comissao(vl_base_comissao_acerto);

      if ("VALOR_INFORMADO".equals (parametro_FixoED.getDM_Comissao_Informada ())) {
          edVolta.setVL_Comissao(vl_comissao_acerto);
      }else{
          edVolta.setVL_Comissao(tvl_comissao);
      }

      edVolta.setVL_Saldo(totaliza_Saldo(edVolta));

      } catch(SQLException exc){
          throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "somaMovimento()");
      }
      return edVolta;
  }


  public Acerto_ViagemED pegaMedia(Acerto_ViagemED edVolta)throws Excecoes {

	    //Acerto_ViagemED edVolta = new Acerto_ViagemED();

	    String sql="";
	    String dm_pagamento_diaria="F";
	    int moeda_padrao = parametro_FixoED.getOID_Moeda_Padrao();
	    double vl_cotacao=1;
	    double vl_movimento_pago=0;
	    double vl_movimento_faturado=0;
	    double vl_movimento_extra=0;
	    double tt_abastecimento=0;
	    double tt_carga_descarga=0;
	    double litros=0;
	    ResultSet res = null;

	    try {


	      sql ="SELECT Movimentos_Ordens_Servicos.VL_Movimento, oid_moeda, VL_Documento, DM_FATURADO_PAGO, DM_Tipo_Despesa, NM_Tipo_Servico, Tipos_Servicos.DM_Medida,  Movimentos_Ordens_Servicos.NR_Quantidade " +
	          " FROM Movimentos_Ordens_Servicos, Tipos_Servicos " +
	          " WHERE Movimentos_Ordens_Servicos.oid_Tipo_Servico = Tipos_Servicos.oid_Tipo_Servico "+
	          " AND Movimentos_Ordens_Servicos.oid_Ordem_Servico = " + edVolta.getOID_Ordem_Servico();

	      // System.out.println("->> " +sql);

	      res = this.executasql.executarConsulta(sql);
	      while (res.next()){

	        if ("L".equals(res.getString("DM_Medida"))) {
	          litros += res.getDouble("NR_Quantidade");
	          tt_abastecimento += res.getDouble("VL_Movimento");
	        }
	        if ("E".equals(res.getString("DM_Faturado_Pago"))) {
	          litros += res.getDouble("NR_Quantidade");
	        }

	        // System.out.println("Tipo DM_Tipo_Despesa : " +res.getString("DM_Tipo_Despesa"));

	        if ("D".equals (res.getString ("DM_Tipo_Despesa"))) { //CARGA/DESCARGA
	          tt_carga_descarga+=res.getDouble ("VL_Movimento") * vl_cotacao;
	        }

	        if ("S".equals (res.getString ("DM_Tipo_Despesa"))) { //DIARIAS
	          if ("F".equals (dm_pagamento_diaria)) {
	            if (res.getLong ("oid_Moeda") != moeda_padrao) //moeda =1 padraoreal
	              vl_movimento_extra += res.getDouble ("VL_Movimento") * vl_cotacao;
	            else vl_movimento_extra += res.getDouble ("VL_Documento");
	          }
	          else {
	            if (res.getLong ("oid_Moeda") != moeda_padrao) //moeda =1 padraoreal
	              vl_movimento_pago += res.getDouble ("VL_Movimento") * vl_cotacao;
	            else vl_movimento_pago += res.getDouble ("VL_Documento");
	          }
	        }
	        else {
	          if ("P".equals (res.getString ("DM_FATURADO_PAGO"))) {
	            if (res.getLong ("oid_Moeda") != moeda_padrao) //moeda =1 padraoreal
	              vl_movimento_pago += res.getDouble ("VL_Movimento") * vl_cotacao;
	            else vl_movimento_pago += res.getDouble ("VL_Documento");
	          }
	          if ("F".equals (res.getString ("DM_FATURADO_PAGO"))) {
	            if (res.getLong ("oid_Moeda") != moeda_padrao) //moeda =1 padraoreal
	              vl_movimento_faturado += res.getDouble ("VL_Movimento") *
	                  vl_cotacao;
	            else vl_movimento_faturado += res.getDouble ("VL_Documento");
	          }

	        }
	      }

	      edVolta.setVL_Total_Carga_Descarga(tt_carga_descarga);
	      edVolta.setNR_Litros(litros);
	      edVolta.setNR_Kilometragem_Total(edVolta.getNR_Kilometragem_Chegada()-edVolta.getNR_Kilometragem_Saida());
	      edVolta.setNR_Media(edVolta.getNR_Kilometragem_Total()/edVolta.getNR_Litros());


	      } catch(SQLException exc){
	          throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "somaMovimento()");
	      }
	      return edVolta;
	  }

  private double totaliza_Saldo(Acerto_ViagemED ed)throws Excecoes {

    double vl_Saldo=(ed.getVL_Adiantamento_Viagem() + ed.getVL_Retido() - ed.getVL_Devolvido()  - ed.getVL_Total_Despesas_Pagas() );
    // System.out.println("vl_Saldo 1: " + vl_Saldo);
    // System.out.println("DM_Saldo_Acerto_Comissao_Motorista: " + parametro_FixoED.getDM_Saldo_Acerto_Comissao_Motorista ());
    // System.out.println("Comissao FP: " + ed.getVL_Comissao ());
    // System.out.println("Comissao FT: " + ed.getVL_Comissao_Terceiro ());

      if ("DESCONTA".equals (parametro_FixoED.getDM_Saldo_Acerto_Comissao_Motorista ())) {
        vl_Saldo=vl_Saldo - ed.getVL_Comissao () - ed.getVL_Comissao_Terceiro ();
      }
    // System.out.println("vl_Saldo 2: " + vl_Saldo);

      return vl_Saldo;
  }


  public Acerto_ViagemED converteAdiantamento(Acerto_ViagemED ed)throws Excecoes {

    Acerto_ViagemED edVolta = new Acerto_ViagemED();

    String sql="";
    int moeda_padrao = parametro_FixoED.getOID_Moeda_Padrao();
    double VL_Ordem_Frete_Convertida=0;

    try {

        sql =" SELECT Ordens_Fretes.VL_Ordem_Frete, Ordens_Fretes.VL_Cotacao, Ordens_Fretes.oid_Ordem_Frete " +
             " FROM Ordens_Fretes " +
             " WHERE Ordens_Fretes.VL_Cotacao > 0" +
             " AND Ordens_Fretes.VL_Ordem_Frete_Convertida = 0 " +
             " AND Ordens_Fretes.OID_Acerto is null " +
             " AND Ordens_Fretes.DM_Impresso='S' " +
             " AND NR_Ordem_Frete >0 " +
             " AND OID_Motorista= '" + ed.getOID_Motorista() + "'";


        ResultSet res = this.executasql.executarConsulta(sql);
        while (res.next()) {
            VL_Ordem_Frete_Convertida = (res.getDouble (1) / res.getDouble (2) *
                                         ed.getVL_Cotacao ());
            sql= "  UPDATE Ordens_Fretes SET  " +
                 "   VL_Ordem_Frete_Convertida= " + VL_Ordem_Frete_Convertida +
                 "  ,VL_Adiantamento1= " + VL_Ordem_Frete_Convertida;

             executasql.executarUpdate(sql);

        }
      } catch(SQLException exc){
          throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "converteAdiantamento()");
      }
      return edVolta;
  }


    private Acerto_ViagemED consultaSituacao(Acerto_ViagemED ed){

      ed.setNM_Situacao("---");
      if ("S".equals(ed.getDM_Impresso())){
        ed.setNM_Situacao("Impressa");
      }
      if ("C".equals(ed.getDM_Situacao())){
        ed.setNM_Situacao("Cancelado");
      }
      if ("F".equals(ed.getDM_Situacao())){
        ed.setNM_Situacao("Finalizado");
      }

      if ("S".equals(ed.getDM_Situacao())){
          ed.setNM_Situacao("Confirmado");
      }

      return ed;
    }


}
