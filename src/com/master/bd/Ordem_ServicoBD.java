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
import java.util.ArrayList;

import com.master.ed.Ordem_ServicoED;
import com.master.rl.Ordem_ServicoRL;
import com.master.root.FormataDataBean;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

public class Ordem_ServicoBD {

  private ExecutaSQL executasql;
  double tt_custo_totalizado=0;
  public Ordem_ServicoBD (ExecutaSQL sql) {
    this.executasql = sql;
  }

  public ArrayList lista (Ordem_ServicoED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();

    try {
      sql = " SELECT OID_Ordem_Servico, DT_Ordem_Servico, HR_Ordem_Servico, Viagens.DT_Previsao_Chegada, Viagens.HR_Previsao_Chegada, Conhecimentos.OID_Conhecimento, Conhecimentos.NR_Conhecimento, Conhecimentos.DT_Emissao, Ordem_Servicos.NR_Ordem_Servico, Unidades.CD_Unidade, Pessoa_Remetente.NM_Razao_Social as NM_Razao_Social_Remetente, Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario, Ordem_Servicos.OID_Ordem_Servico, Cidades.NM_Cidade from Viagens, Conhecimentos, Unidades, Pessoas Pessoa_Remetente, Pessoas Pessoa_Destinatario, Ordem_Servicos, Cidades ";
      sql += " WHERE Unidades.oid_Unidade = Conhecimentos.oid_Unidade ";
      sql += " AND Conhecimentos.oid_Pessoa = Pessoa_Remetente.oid_Pessoa ";
      sql +=
          " AND Conhecimentos.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa ";
      sql += " AND Viagens.OID_Conhecimento = Conhecimentos.OID_Conhecimento ";
      sql +=
          " AND Viagens.OID_Ordem_Servico = Ordem_Servicos.OID_Ordem_Servico ";
      sql += " AND Conhecimentos.OID_Cidade_Destino = Cidades.OID_Cidade ";

      if (String.valueOf (ed.getOID_Ordem_Servico ()) != null &&
          !String.valueOf (ed.getOID_Ordem_Servico ()).equals ("0") &&
          !String.valueOf (ed.getOID_Ordem_Servico ()).equals ("null")) {
        sql += " and Ordem_Servicos.OID_Ordem_Servico = '" +
            ed.getOID_Ordem_Servico () + "'";
      }

      if (String.valueOf (ed.getOID_Unidade ()) != null &&
          !String.valueOf (ed.getOID_Unidade ()).equals ("0") &&
          !String.valueOf (ed.getOID_Unidade ()).equals ("null")) {
        sql += " and Conhecimentos.OID_Unidade = " + ed.getOID_Unidade ();
      }
      if (String.valueOf (ed.getOID_Pessoa ()) != null &&
          !String.valueOf (ed.getOID_Pessoa ()).equals ("") &&
          !String.valueOf (ed.getOID_Pessoa ()).equals ("null")) {
        sql += " and Conhecimentos.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
      }
      if (String.valueOf (ed.getDT_Emissao ()) != null &&
          !String.valueOf (ed.getDT_Emissao ()).equals ("") &&
          !String.valueOf (ed.getDT_Emissao ()).equals ("null")) {
        sql += " and Conhecimentos.DT_Emissao = '" + ed.getDT_Emissao () + "'";
      }
      if (String.valueOf (ed.getDT_Emissao_Inicial ()) != null &&
          !String.valueOf (ed.getDT_Emissao_Inicial ()).equals ("") &&
          !String.valueOf (ed.getDT_Emissao_Inicial ()).equals ("null")) {
        sql += " and Viagens.DT_Ordem_Servico >= '" + ed.getDT_Emissao_Inicial () +
            "'";
      }

      sql +=
          " Order by Viagens.Dt_Previsao_Chegada, Viagens.Hr_Previsao_Chegada ";

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);

      FormataDataBean DataFormatada = new FormataDataBean ();

      //popula
      while (res.next ()) {
        Ordem_ServicoED edVolta = new Ordem_ServicoED ();

        edVolta.setOID_Ordem_Servico (res.getLong ("OID_Ordem_Servico"));

        edVolta.setDT_Emissao (res.getString ("DT_Emissao"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
        edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());

        edVolta.setNR_Ordem_Servico (res.getLong ("NR_Ordem_Servico"));
        edVolta.setCD_Unidade (res.getString ("CD_Unidade"));
        edVolta.setOID_Ordem_Servico (res.getLong ("OID_Ordem_Servico"));
        list.add (edVolta);
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao listar");
      excecoes.setMetodo ("listar");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return list;
  }

  public byte[] Relatorio_Ordem_Servico (Ordem_ServicoED ed) throws Excecoes {

    String sql = null;
    ResultSet res = null;
    ResultSet rs = null;
    int Nr_Sort = 0;

    sql = null;
    byte[] b = null;

    if (ed.getDM_Relatorio ().equals ("1") || ed.getDM_Relatorio ().equals ("4")) {
      sql = " SELECT Ordens_Servicos.OID_Ordem_Servico, Movimentos_Ordens_Servicos.oid_Pessoa, Movimentos_Ordens_Servicos.TX_Observacao, Ordens_Servicos.OID_Unidade, Ordens_Servicos.OID_Veiculo, Ordens_Servicos.OID_Tipo_Servico, Ordens_Servicos.NR_Ordem_Servico, Ordens_Servicos.NR_Kilometragem, Ordens_Servicos.DT_Ordem_Servico, Ordens_Servicos.DT_Encerramento, Ordens_Servicos.NR_Meses_Amortizacao, Ordens_Servicos.VL_Previsto, Ordens_Servicos.TX_Observacao, Tipos_Servicos.CD_Tipo_Servico,  Tipos_Servicos.NM_Tipo_Servico, Tipos_Servicos.DM_Medida, Tipos_Servicos.CD_Tipo_Servico, Unidades.CD_Unidade, Ordens_Servicos.OID_Veiculo, Pessoa_Fornecedor.NM_Razao_Social as NM_Fornecedor,  " +
          " Movimentos_Ordens_Servicos.DT_Movimento_Ordem_Servico, Movimentos_Ordens_Servicos.NR_Documento, Movimentos_Ordens_Servicos.NR_Quantidade, Movimentos_Ordens_Servicos.NR_Kilometragem as Odometro, Movimentos_Ordens_Servicos.DM_Faturado_Pago, Movimentos_Ordens_Servicos.VL_Movimento " +
          " FROM Ordens_Servicos, Unidades, Veiculos, Tipos_Servicos, Pessoas Pessoa_Fornecedor, Movimentos_Ordens_Servicos " +
          " WHERE Ordens_Servicos.OID_Unidade = Unidades.OID_Unidade " +
          "  AND Movimentos_Ordens_Servicos.OID_Ordem_Servico = Ordens_Servicos.oid_ordem_servico " +
          "  AND Movimentos_Ordens_Servicos.oid_Tipo_Servico = Tipos_Servicos.OID_Tipo_Servico " +
          "  AND Movimentos_Ordens_Servicos.oid_Pessoa = Pessoa_Fornecedor.OID_Pessoa " +
          "  AND Ordens_Servicos.OID_Veiculo = Veiculos.OID_Veiculo ";
    }
    if (ed.getDM_Relatorio ().equals ("2")) {
      sql = " SELECT Ordens_Servicos.OID_Veiculo, Tipos_Servicos.CD_Tipo_Servico,  Tipos_Servicos.NM_Tipo_Servico, Tipos_Servicos.CD_Tipo_Servico,  " +
          " Movimentos_Ordens_Servicos.NR_Quantidade, Movimentos_Ordens_Servicos.VL_Movimento " +
          " FROM Ordens_Servicos, Unidades, Tipos_Servicos, Movimentos_Ordens_Servicos " +
          " WHERE Ordens_Servicos.OID_Unidade = Unidades.OID_Unidade " +
          "  AND Movimentos_Ordens_Servicos.OID_Ordem_Servico = Ordens_Servicos.oid_ordem_servico " +
          "  AND Movimentos_Ordens_Servicos.oid_Tipo_Servico = Tipos_Servicos.OID_Tipo_Servico ";
    }
    if (ed.getDM_Relatorio ().equals ("3")) {
      sql = " SELECT Ordens_Servicos.OID_Veiculo,  " +
          " Movimentos_Ordens_Servicos.VL_Movimento " +
          " FROM Ordens_Servicos, Unidades, Movimentos_Ordens_Servicos, Tipos_Servicos " +
          " WHERE Ordens_Servicos.OID_Unidade = Unidades.OID_Unidade " +
          "  AND Movimentos_Ordens_Servicos.oid_Tipo_Servico = Tipos_Servicos.OID_Tipo_Servico " +
          "  AND Movimentos_Ordens_Servicos.OID_Ordem_Servico = Ordens_Servicos.oid_ordem_servico ";
    }

    if (String.valueOf (ed.getOID_Ordem_Servico ()) != null &&
        !String.valueOf (ed.getOID_Ordem_Servico ()).equals ("0") &&
        !String.valueOf (ed.getOID_Ordem_Servico ()).equals ("null")) {
      sql += " and Ordem_Servicos.OID_Ordem_Servico = " +
          ed.getOID_Ordem_Servico ();
    }
    if (String.valueOf (ed.getOID_Empresa ()) != null &&
        !String.valueOf (ed.getOID_Empresa ()).equals ("0")) {
      sql += " and Unidades.OID_Empresa = " + ed.getOID_Empresa ();
    }

    if (String.valueOf (ed.getOID_Unidade ()) != null &&
        !String.valueOf (ed.getOID_Unidade ()).equals ("0")) {
      sql += " and Ordens_Servicos.OID_Unidade = " + ed.getOID_Unidade ();
    }
    if (ed.getOID_Tipo_Servico () > 0) {
      sql += " and Movimentos_Ordens_Servicos.OID_Tipo_Servico = " +
          ed.getOID_Tipo_Servico ();
    }
    if (String.valueOf (ed.getOID_Pessoa ()) != null &&
        !String.valueOf (ed.getOID_Pessoa ()).equals ("") &&
        !String.valueOf (ed.getOID_Pessoa ()).equals ("null")) {
      sql += " and Movimentos_Ordens_Servicos.OID_Pessoa = '" +
          ed.getOID_Pessoa () + "'";
    }
    if (String.valueOf (ed.getDT_Emissao_Inicial ()) != null &&
        !String.valueOf (ed.getDT_Emissao_Inicial ()).equals ("") &&
        !String.valueOf (ed.getDT_Emissao_Inicial ()).equals ("null")) {
      sql += " and Movimentos_Ordens_Servicos.dt_movimento_ordem_servico >= '" +
          ed.getDT_Emissao_Inicial () + "'";
    }
    if (String.valueOf (ed.getDT_Emissao_Final ()) != null &&
        !String.valueOf (ed.getDT_Emissao_Final ()).equals ("") &&
        !String.valueOf (ed.getDT_Emissao_Final ()).equals ("null")) {
      sql += " and Movimentos_Ordens_Servicos.dt_movimento_ordem_servico <= '" +
          ed.getDT_Emissao_Final () + "'";
    }
    if (String.valueOf (ed.getNR_Placa ()) != null &&
        !String.valueOf (ed.getNR_Placa ()).equals ("null") &&
        !String.valueOf (ed.getNR_Placa ()).equals ("A") &&
        !String.valueOf (ed.getNR_Placa ()).equals ("")) {
      sql += " and Ordens_Servicos.OID_Veiculo = '" + ed.getNR_Placa () + "'";
    }
    if (String.valueOf (ed.getDM_Tipo_Pagamento ()) != null &&
        !String.valueOf (ed.getDM_Tipo_Pagamento ()).equals ("null") &&
        !String.valueOf (ed.getDM_Tipo_Pagamento ()).equals ("T") &&
        !String.valueOf (ed.getDM_Tipo_Pagamento ()).equals ("")) {
      sql += " and Ordens_Servicos.DM_Tipo_Pagamento = '" +
          ed.getDM_Tipo_Pagamento () + "'";
    }

    if (String.valueOf (ed.getDM_Tipo_Despesa ()) != null &&
        !String.valueOf (ed.getDM_Tipo_Despesa ()).equals ("null") &&
        !String.valueOf (ed.getDM_Tipo_Despesa ()).equals ("T") &&
        !String.valueOf (ed.getDM_Tipo_Despesa ()).equals ("")) {
      sql += " and Tipos_Servicos.DM_Tipo_Despesa = '" +
          ed.getDM_Tipo_Despesa () + "'";
    }

    if (ed.getDM_Relatorio ().equals ("1")) {
      sql += "  ORDER BY  Ordens_Servicos.OID_Veiculo, Movimentos_Ordens_Servicos.OID_Pessoa, Movimentos_Ordens_Servicos.DT_Movimento_Ordem_Servico ";
    }

    if (ed.getDM_Relatorio ().equals ("2")) {
      sql += "  ORDER BY Tipos_Servicos.CD_Tipo_Servico, Ordens_Servicos.OID_Veiculo ";
    }
    if (ed.getDM_Relatorio ().equals ("3")) {
      sql += "  ORDER BY  Ordens_Servicos.OID_Veiculo ";
    }
    if (ed.getDM_Relatorio ().equals ("4")) {
      sql += "  ORDER BY  Movimentos_Ordens_Servicos.OID_Pessoa, Ordens_Servicos.OID_Veiculo,  Tipos_Servicos.CD_Tipo_Servico ";
    }

    // System.out.println ("sql " + sql);

    Ordem_ServicoED edVolta = new Ordem_ServicoED ();

    try {

      res = null;
      Ordem_ServicoRL conRL = new Ordem_ServicoRL ();
      res = this.executasql.executarConsulta (sql.toString ());

      if (ed.getDM_Relatorio ().equals ("1")) {
        b = conRL.servico_Realizado_Veiculo (ed , res);
      }
      if (ed.getDM_Relatorio ().equals ("2")) {
        b = conRL.resumo_Tipo_Servico (ed , res);
      }
      if (ed.getDM_Relatorio ().equals ("3")) {
        b = conRL.resumo_Veiculo (ed , res);
      }
      if (ed.getDM_Relatorio ().equals ("4")) {
        b = conRL.servico_Realizado_Fornecedor (ed , res);
      }

    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro no m�todo listar");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("Relatorio_Ordem_Servico");
    }
    return b;
  }


  public byte[] resumo_Custo_Faturamento_Veiculo (Ordem_ServicoED ed) throws Excecoes {

    String sql = null;
    ResultSet res = null;
    double tt_KM=0;
    byte[] b = null;

    tt_custo_totalizado=0;
    try {
      sql =" DELETE FROM Custos_Veiculos " ;
      executasql.executarUpdate (sql);

      sql =" SELECT Veiculos.oid_Veiculo " +
           " FROM  Veiculos, Modelos_Veiculos, Tipos_Veiculos, Complementos_Veiculos " +
           " WHERE Veiculos.oid_Modelo_Veiculo =  Modelos_Veiculos.oid_Modelo_Veiculo " +
           " AND   Veiculos.oid_Veiculo =  Complementos_Veiculos.oid_Veiculo " +
           " AND   Tipos_Veiculos.oid_Tipo_Veiculo =  Modelos_Veiculos.oid_Tipo_Veiculo " +
           " AND   Complementos_Veiculos.DM_Procedencia='P'" +
           " AND   Tipos_Veiculos.DM_Tipo_Implemento='1'";

      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {

        double tt_custo_fixo= totaliza_Custo_Veiculo(ed,res.getString("oid_Veiculo"),"","F","");
        double tt_custo_variavel= totaliza_Custo_Veiculo(ed,res.getString("oid_Veiculo"),"","V","");

        // System.out.println("tt_custo_fixo=" + tt_custo_fixo);
        // System.out.println("tt_custo_variavel=" + tt_custo_variavel);


       sql = " INSERT INTO Custos_Veiculos (" +
          "	oid_Veiculo" +
          "	,dt_inicial" +
          "	,dt_final" +
          "	,tt_faturamento" +
          "	,tt_km" +
          "	,tt_peso" +
          "	,tt_embarques" +
          "	,tt_abastecimento" +
          "	,tt_lubrificante" +
          "	,tt_mecanica" +
          "	,tt_eletrica" +
          "	,tt_pneus" +
          "	,tt_funilaria" +
          "	,tt_pedagio" +
          "	,tt_lavagem" +
          "	,tt_vapor" +
          "	,tt_viagem" +
          "	,tt_rastreamento" +
          "	,tt_depreciacao" +
          "	,tt_ipva" +
          "	,tt_seguro " +
          "	,tt_custo_fixo" +
          "	,tt_custo_variavel" +
          "	,tt_outros )" +

          "   VALUES (" +
          "'" + res.getString("oid_Veiculo") + "'" +
          ",'" + ed.getDT_Emissao_Inicial () + "'" +
          ",'" + ed.getDT_Emissao_Final () + "'"  +
          ","  + totaliza_Receita_Veiculo(ed,res.getString("oid_Veiculo"),"") +
          ","  + tt_KM +
          ","  + totaliza_Receita_Veiculo(ed,res.getString("oid_Veiculo"),"P") +
          ","  + totaliza_Receita_Veiculo(ed,res.getString("oid_Veiculo"),"E") +
          ","  + totaliza_Custo_Veiculo(ed,res.getString("oid_Veiculo"),"C","V","") + //comb
          ","  + totaliza_Custo_Veiculo(ed,res.getString("oid_Veiculo"),"U","V","") + //lubrif
          ","  + totaliza_Custo_Veiculo(ed,res.getString("oid_Veiculo"),"N","V","") + //mecanica
          ","  + totaliza_Custo_Veiculo(ed,res.getString("oid_Veiculo"),"Q","V","") + //eletrica
          ","  + totaliza_Custo_Veiculo(ed,res.getString("oid_Veiculo"),"F","V","") + //funilaria
          ","  + totaliza_Custo_Veiculo(ed,res.getString("oid_Veiculo"),"E","V","") + //pneus
          ","  + totaliza_Custo_Veiculo(ed,res.getString("oid_Veiculo"),"L","V","") + //lavagem
          ","  + totaliza_Custo_Veiculo(ed,res.getString("oid_Veiculo"),"B","V","") + //vapor
          ","  + totaliza_Custo_Veiculo(ed,res.getString("oid_Veiculo"),"A","V","") + //viagem
          ","  + totaliza_Custo_Veiculo(ed,res.getString("oid_Veiculo"),"U","V","") + //lubrif
          ","  + totaliza_Custo_Veiculo(ed,res.getString("oid_Veiculo"),"R","V","") + //rastream
          ","  + totaliza_Custo_Veiculo(ed,res.getString("oid_Veiculo"),"X","V","") + //deprec
          ","  + totaliza_Custo_Veiculo(ed,res.getString("oid_Veiculo"),"Y","V","") + //ipva
          ","  + totaliza_Custo_Veiculo(ed,res.getString("oid_Veiculo"),"Z","V","") + //seguro

          ","  + tt_custo_fixo +
          ","  + tt_custo_variavel +
          ","  + (tt_custo_fixo+tt_custo_variavel-tt_custo_totalizado) + //outros

          ")";

      // System.out.print(sql);

      executasql.executarUpdate (sql);

     }
      sql =" SELECT *  " +
           " FROM  Veiculos, Custos_Veiculos " +
           " WHERE Veiculos.oid_Veiculo =  Custos_Veiculos.oid_Veiculo " +
           " ORDER BY Veiculos.NR_Frota " ;

      // System.out.print(sql);

      Ordem_ServicoRL conRL = new Ordem_ServicoRL ();
      res = this.executasql.executarConsulta (sql.toString ());

      b = conRL.resumo_Custo_Faturamento_Veiculo (ed , res);

    }

    catch (Excecoes e) {
    	e.printStackTrace();
      throw e;
    }
    catch (Exception exc) {
    	exc.printStackTrace();
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro no m�todo listar");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("Relatorio_Ordem_Servico");
    }
    return b;
  }


  public double totaliza_Custo_Veiculo (Ordem_ServicoED ed, String oid_Veiculo, String DM_Tipo_Despesa, String DM_Custo, String DM_Retorno) throws Excecoes {

    String sql = null;
    ResultSet res = null;
    double tt_Retorno=0;

      sql = " SELECT SUM (Movimentos_Ordens_Servicos.NR_Quantidade) as tt_Quantidade, SUM (Movimentos_Ordens_Servicos.VL_Movimento) as tt_Movimento " +
          " FROM Ordens_Servicos, Unidades, Tipos_Servicos, Movimentos_Ordens_Servicos " +
          " WHERE Ordens_Servicos.OID_Unidade = Unidades.OID_Unidade " +
          "  AND Movimentos_Ordens_Servicos.OID_Ordem_Servico = Ordens_Servicos.oid_ordem_servico " +
          "  AND Movimentos_Ordens_Servicos.oid_Tipo_Servico = Tipos_Servicos.OID_Tipo_Servico " +
          "  AND Ordens_Servicos.OID_Veiculo = '" + oid_Veiculo + "'";
      if (!DM_Tipo_Despesa.equals("")){
        sql +="  AND Tipos_Servicos.DM_Tipo_Despesa = '" + DM_Tipo_Despesa + "'";
      }
      if (!DM_Custo.equals("")){
        sql +="  AND Tipos_Servicos.DM_Custo = '" + DM_Custo + "'";
      }

    if (String.valueOf (ed.getOID_Empresa ()) != null &&
        !String.valueOf (ed.getOID_Empresa ()).equals ("0")) {
      sql += " and Unidades.OID_Empresa = " + ed.getOID_Empresa ();
    }

    if (String.valueOf (ed.getOID_Unidade ()) != null &&
        !String.valueOf (ed.getOID_Unidade ()).equals ("0")) {
      sql += " and Ordens_Servicos.OID_Unidade = " + ed.getOID_Unidade ();
    }

    if (ed.getOID_Tipo_Servico () > 0) {
      sql += " and Movimentos_Ordens_Servicos.OID_Tipo_Servico = " + ed.getOID_Tipo_Servico ();
    }
    if (String.valueOf (ed.getOID_Pessoa ()) != null &&
        !String.valueOf (ed.getOID_Pessoa ()).equals ("") &&
        !String.valueOf (ed.getOID_Pessoa ()).equals ("null")) {
      sql += " and Movimentos_Ordens_Servicos.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
    }
    if (String.valueOf (ed.getDT_Emissao_Inicial ()) != null &&
        !String.valueOf (ed.getDT_Emissao_Inicial ()).equals ("") &&
        !String.valueOf (ed.getDT_Emissao_Inicial ()).equals ("null")) {
      sql += " and Movimentos_Ordens_Servicos.dt_movimento_ordem_servico >= '" + ed.getDT_Emissao_Inicial () + "'";
    }
    if (String.valueOf (ed.getDT_Emissao_Final ()) != null &&
        !String.valueOf (ed.getDT_Emissao_Final ()).equals ("") &&
        !String.valueOf (ed.getDT_Emissao_Final ()).equals ("null")) {
      sql += " and Movimentos_Ordens_Servicos.dt_movimento_ordem_servico <= '" + ed.getDT_Emissao_Final () + "'";
    }
    try {

      res = this.executasql.executarConsulta (sql.toString ());
      while (res.next ()) {
        if (DM_Retorno.equals("Q")){
          tt_Retorno = res.getDouble ("tt_Quantidade");
        }else {
          tt_Retorno = res.getDouble ("tt_Movimento");
          if (DM_Custo.equals("")){
            tt_custo_totalizado += res.getDouble ("tt_Movimento");
          }
        }
      }

      // System.out.println ("tt_Retorno=> " + tt_Retorno);
      // System.out.println ("tt_custo_totalizado=> " + tt_custo_totalizado);

    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro no m�todo listar");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("Relatorio_Ordem_Servico");
    }
    return tt_Retorno;
  }


  public double totaliza_Receita_Veiculo (Ordem_ServicoED ed, String oid_Veiculo, String DM_Retorno) throws Excecoes {

    String sql = null;
    ResultSet res = null;
    double tt_Retorno=0;

      sql = " SELECT COUNT (Conhecimentos.oid_Conhecimento) as tt_Embarque, SUM (Conhecimentos.NR_Peso) as tt_Peso, SUM (Conhecimentos.VL_Total_Frete) as tt_Frete " +
          " FROM  Conhecimentos, Unidades " +
          " WHERE Conhecimentos.OID_Unidade = Unidades.OID_Unidade " +
          "  AND Conhecimentos.DM_Situacao <>'C' " +
          "  AND Conhecimentos.DM_Impresso ='S' " +
          "  AND  Conhecimentos.OID_Veiculo = '" + oid_Veiculo + "'";


    if (String.valueOf (ed.getOID_Empresa ()) != null &&
        !String.valueOf (ed.getOID_Empresa ()).equals ("0")) {
      sql += " and Unidades.OID_Empresa = " + ed.getOID_Empresa ();
    }

    if (String.valueOf (ed.getOID_Unidade ()) != null &&
        !String.valueOf (ed.getOID_Unidade ()).equals ("0")) {
      sql += " and Conhecimentos.OID_Unidade = " + ed.getOID_Unidade ();
    }

    if (String.valueOf (ed.getDT_Emissao_Inicial ()) != null &&
        !String.valueOf (ed.getDT_Emissao_Inicial ()).equals ("") &&
        !String.valueOf (ed.getDT_Emissao_Inicial ()).equals ("null")) {
      sql += " and Conhecimentos.dt_Emissao >= '" + ed.getDT_Emissao_Inicial () + "'";
    }
    if (String.valueOf (ed.getDT_Emissao_Final ()) != null &&
        !String.valueOf (ed.getDT_Emissao_Final ()).equals ("") &&
        !String.valueOf (ed.getDT_Emissao_Final ()).equals ("null")) {
      sql += " and Conhecimentos.dt_Emissao <= '" + ed.getDT_Emissao_Final () + "'";
    }

    // System.out.println ("sql " + sql);

    try {

      res = this.executasql.executarConsulta (sql.toString ());
      while (res.next ()) {
        tt_Retorno=res.getDouble("tt_Frete");
        if (DM_Retorno.equals("P")){
          tt_Retorno = res.getDouble ("tt_Peso");
        }
        if (DM_Retorno.equals("E")){
          tt_Retorno = res.getDouble ("tt_Embarque");
        }
      }


    }


    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro no m�todo listar");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("Relatorio_Ordem_Servico");
    }
    return tt_Retorno;
  }


  public byte[] ficha_Manutencao (Ordem_ServicoED ed) throws Excecoes {

    String sql = null;
    ResultSet res = null;
    ResultSet rs = null;
    int Nr_Sort = 0;

    sql = null;
    byte[] b = null;

    if (ed.getDM_Relatorio ().equals ("1")) {
      sql = " SELECT 	" +
          " Movimentos_Ordens_Servicos.oid_Pessoa, " +
          " Movimentos_Ordens_Servicos.OID_Veiculo, " +
          " Movimentos_Ordens_Servicos.OID_Tipo_Servico, " +
          " Movimentos_Ordens_Servicos.NR_Kilometragem, " +
          " Tipos_Servicos.CD_Tipo_Servico,  " +
          " Tipos_Servicos.NM_Tipo_Servico, " +
          " Tipos_Servicos.DM_Medida, " +
          " Tipos_Servicos.CD_Tipo_Servico, " +
          " Pessoa_Fornecedor.NM_Razao_Social as NM_Fornecedor,  " +
          " Movimentos_Ordens_Servicos.DT_Movimento_Ordem_Servico, " +
          " Movimentos_Ordens_Servicos.TX_Observacao, " +
          " Movimentos_Ordens_Servicos.TX_Servico, " +
          " Movimentos_Ordens_Servicos.NR_Documento, " +
          " Movimentos_Ordens_Servicos.NR_Quantidade, " +
          " Movimentos_Ordens_Servicos.NR_Kilometragem as Odometro, " +
          " Movimentos_Ordens_Servicos.DM_Faturado_Pago, " +
          " Movimentos_Ordens_Servicos.VL_Movimento " +
          " FROM Veiculos, Tipos_Servicos, Pessoas Pessoa_Fornecedor, Movimentos_Ordens_Servicos, Modelos_Veiculos, Tipos_Veiculos " +
          " WHERE Movimentos_Ordens_Servicos.oid_Pessoa = Pessoa_Fornecedor.oid_Pessoa " +
          "  AND Movimentos_Ordens_Servicos.oid_Tipo_Servico = Tipos_Servicos.OID_Tipo_Servico " +
          "  AND Movimentos_Ordens_Servicos.OID_Veiculo = Veiculos.OID_Veiculo " +
          "  AND Veiculos.oid_Modelo_Veiculo = Modelos_Veiculos.oid_Modelo_Veiculo " +
          "  AND Modelos_Veiculos.oid_Tipo_Veiculo = Tipos_Veiculos.oid_Tipo_Veiculo ";

    }

    if (ed.getOID_Tipo_Servico () > 0) {
      sql += " and Movimentos_Ordens_Servicos.OID_Tipo_Servico = " + ed.getOID_Tipo_Servico ();
    }

    if (String.valueOf (ed.getOID_Pessoa ()) != null &&
        !String.valueOf (ed.getOID_Pessoa ()).equals ("") &&
        !String.valueOf (ed.getOID_Pessoa ()).equals ("null")) {
      sql += " and Movimentos_Ordens_Servicos.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
    }

    if (String.valueOf (ed.getDT_Emissao_Inicial ()) != null &&
        !String.valueOf (ed.getDT_Emissao_Inicial ()).equals ("") &&
        !String.valueOf (ed.getDT_Emissao_Inicial ()).equals ("null")) {
      sql += " and Movimentos_Ordens_Servicos.dt_movimento_ordem_servico >= '" + ed.getDT_Emissao_Inicial () + "'";
    }
    if (String.valueOf (ed.getDT_Emissao_Final ()) != null &&
        !String.valueOf (ed.getDT_Emissao_Final ()).equals ("") &&
        !String.valueOf (ed.getDT_Emissao_Final ()).equals ("null")) {
      sql += " and Movimentos_Ordens_Servicos.dt_movimento_ordem_servico <= '" + ed.getDT_Emissao_Final () + "'";
    }
    if (String.valueOf (ed.getNR_Placa ()) != null &&
        !String.valueOf (ed.getNR_Placa ()).equals ("null") &&
        !String.valueOf (ed.getNR_Placa ()).equals ("A") &&
        !String.valueOf (ed.getNR_Placa ()).equals ("")) {
      sql += " and Movimentos_Ordens_Servicos.OID_Veiculo = '" + ed.getNR_Placa () + "'";
    }

    if (String.valueOf (ed.getTX_Observacao ()) != null &&
        !String.valueOf (ed.getTX_Observacao ()).equals ("null") &&
        !String.valueOf (ed.getTX_Observacao ()).equals ("")) {
      sql += " and Movimentos_Ordens_Servicos.TX_Observacao LIKE '%" + ed.getTX_Observacao () + "%'";
    }

    if (String.valueOf (ed.getDM_Tipo_Despesa ()) != null &&
        !String.valueOf (ed.getDM_Tipo_Despesa ()).equals ("null") &&
        !String.valueOf (ed.getDM_Tipo_Despesa ()).equals ("T") &&
        !String.valueOf (ed.getDM_Tipo_Despesa ()).equals ("")) {
      sql += " and Tipos_Servicos.DM_Tipo_Despesa = '" + ed.getDM_Tipo_Despesa () + "'";
    }

    if (String.valueOf (ed.getDM_Tipo_Implemento ()) != null &&
        !String.valueOf (ed.getDM_Tipo_Implemento ()).equals ("null") &&
        !String.valueOf (ed.getDM_Tipo_Implemento ()).equals ("T") &&
        !String.valueOf (ed.getDM_Tipo_Implemento ()).equals ("")) {
      sql += " and Tipos_Veiculos.DM_Tipo_Implemento = '" + ed.getDM_Tipo_Implemento () + "'";
    }

    if (ed.getDM_Relatorio ().equals ("1")) {
      sql += "  ORDER BY  Movimentos_Ordens_Servicos.OID_Veiculo, Movimentos_Ordens_Servicos.oid_Pessoa, Movimentos_Ordens_Servicos.DT_Movimento_Ordem_Servico,  Tipos_Servicos.CD_Tipo_Servico ";
    }

    // System.out.println ("sql " + sql);

    Ordem_ServicoED edVolta = new Ordem_ServicoED ();

    try {

      res = null;
      Ordem_ServicoRL conRL = new Ordem_ServicoRL ();
      res = this.executasql.executarConsulta (sql.toString ());

      if (ed.getDM_Relatorio ().equals ("1")) {
        b = conRL.ficha_Manutencao (ed , res);
      }

    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro no m�todo listar");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("Relatorio_Ordem_Servico");
    }
    return b;
  }

  public byte[] imprime_Requisicao (Ordem_ServicoED ed) throws Excecoes {

    String sql = null;
    ResultSet res = null;
    ResultSet rs = null;
    int Nr_Sort = 0;

    sql = null;
    byte[] b = null;

    sql = " SELECT Ordens_Servicos.OID_Ordem_Servico,	Ordens_Servicos.OID_Unidade, Ordens_Servicos.OID_Veiculo, Ordens_Servicos.OID_Tipo_Servico, Ordens_Servicos.NR_Ordem_Servico, Ordens_Servicos.NR_Kilometragem, Ordens_Servicos.DT_Ordem_Servico, Ordens_Servicos.DT_Encerramento, Ordens_Servicos.NR_Meses_Amortizacao, Ordens_Servicos.VL_Previsto, Ordens_Servicos.TX_Observacao, Tipos_Servicos.CD_Tipo_Servico,  Tipos_Servicos.NM_Tipo_Servico, Tipos_Servicos.DM_Medida, Tipos_Servicos.CD_Tipo_Servico, Unidades.CD_Unidade, Ordens_Servicos.OID_Veiculo , Ordens_Servicos.oid_Pessoa_Fornecedor, " +
        " Ordens_Servicos.NM_Servico1, Ordens_Servicos.NM_Servico2, Ordens_Servicos.NM_Servico3, Ordens_Servicos.NM_Servico4, Ordens_Servicos.NM_Servico5, Ordens_Servicos.NM_Servico6, Ordens_Servicos.NM_Servico7, Ordens_Servicos.NM_Servico8, Ordens_Servicos.NM_Servico9, Ordens_Servicos.NM_Servico10, Ordens_Servicos.NM_Condicao_Pagamento  " +
        " FROM Ordens_Servicos, Unidades, Veiculos, Tipos_Servicos " + //, Pessoas Pessoa_Fornecedor " +
        " WHERE Ordens_Servicos.OID_Unidade = Unidades.OID_Unidade " +
        "  AND Ordens_Servicos.oid_Tipo_Servico = Tipos_Servicos.OID_Tipo_Servico " +
        "  AND Ordens_Servicos.OID_Veiculo = Veiculos.OID_Veiculo ";

    if (String.valueOf (ed.getOID_Ordem_Servico ()) != null &&
        !String.valueOf (ed.getOID_Ordem_Servico ()).equals ("0") &&
        !String.valueOf (ed.getOID_Ordem_Servico ()).equals ("null")) {
      sql += " and Ordens_Servicos.OID_Ordem_Servico = " +
          ed.getOID_Ordem_Servico ();
    }

    // System.out.println ("sql " + sql);

    try {

      Ordem_ServicoRL conRL = new Ordem_ServicoRL ();
      // System.out.println ("imprime_Requisicao -1");

      res = this.executasql.executarConsulta (sql);

      b = conRL.imprime_Requisicao (ed , res);

    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro no m�todo listar");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("Relatorio_Ordem_Servico");
    }
    return b;
  }

  public void deleta_Movimento (Ordem_ServicoED ed) throws Excecoes {

    String sql = null;
    String erro = "";
    ResultSet res = null;

    int pagamentos = 0 , i = 0 , compromisso = 0;

    try {

      sql =
          " Select vl_pagamento from Compromissos_Servicos, Movimentos_Compromissos " +
          " WHERE Compromissos_Servicos.oid_Compromisso = Movimentos_Compromissos.oid_Compromisso " +
          " AND   Compromissos_Servicos.oid_Movimento_Ordem_Servico  = " +
          ed.getOID_Movimento_Ordem_Servico ();
      // System.out.println ("PAsso 1 " + sql);
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        erro = "H� pagamentos nos compromissos ";
        pagamentos++;
      }

      sql =
          " Select vl_pagamento from Compromissos_Servicos, Lotes_Compromissos " +
          " WHERE Compromissos_Servicos.oid_Compromisso = Lotes_Compromissos.oid_Compromisso " +
          " AND   Compromissos_Servicos.oid_Movimento_Ordem_Servico  = " +
          ed.getOID_Movimento_Ordem_Servico ();
      // System.out.println ("PAsso 1 " + sql);
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        erro = "H� Documento para Pagamento para este Compromisso";
        pagamentos++;
      }

      if (pagamentos > 0) {
        Excecoes exc = new Excecoes ();
        throw exc;
      }
      else {
        sql = " select oid_Compromisso, oid_Movimento_Ordem_Servico from Compromissos_Servicos " +
            "  WHERE Compromissos_Servicos.oid_Movimento_Ordem_Servico  = " +
            ed.getOID_Movimento_Ordem_Servico ();
        // System.out.println ("PAsso 2 " + sql);
        res = this.executasql.executarConsulta (sql);
        while (res.next ()) {
          compromisso++;
          sql =
              "delete from Compromissos_Servicos WHERE oid_Movimento_Ordem_Servico = ";
          sql += "(" + res.getString ("oid_Movimento_Ordem_Servico") + ")";
          // System.out.println ("PAsso 4 " + sql);
          i = executasql.executarUpdate (sql);

          sql = "delete from Compromissos WHERE oid_Compromisso= " +
              res.getString ("oid_Compromisso");
          // System.out.println ("PAsso 5 " + sql);
          i = executasql.executarUpdate (sql);
        }
      }
      sql =
          "delete from Movimentos_Ordens_Servicos WHERE oid_Movimento_Ordem_Servico = ";
      sql += "(" + ed.getOID_Movimento_Ordem_Servico () + ")";
      // System.out.println ("PAsso 7 " + sql);
      i = executasql.executarUpdate (sql);

    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem (erro);
      excecoes.setMetodo ("deletar");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void inclui_Rateio (Ordem_ServicoED ed) throws Excecoes {

    String sql = null;
    ResultSet res = null;

  }

  public void inclui_Custo_Fixo (Ordem_ServicoED ed) throws Excecoes {

    String sql = null;
    ResultSet res = null;
    ResultSet res2 = null;

    String dt_inicial = "01/" + ed.getDT_Emissao ().substring (3 , 10);

    // System.out.println ("dt_inicial " + dt_inicial);

    String dt_final = Data.getDataMaximaNoMes ("31" + "/" + ed.getDT_Emissao ().substring (3 , 10));

    // System.out.println ("dt_final " + dt_final);

    try {

      sql = " Select Veiculos.OID_Veiculo, " +
          " Custos_Fixos_Veiculos.oid_Tipo_Servico, " +
          " Custos_Fixos_Veiculos.VL_Custo_Fixo " +
          " FROM Veiculos, Custos_Fixos_Veiculos " +
          " WHERE Veiculos.OID_Veiculo = Custos_Fixos_Veiculos.OID_Veiculo ";
      if (ed.getOID_Tipo_Servico () > 0) {
        sql += " AND   Custos_Fixos_Veiculos.oid_Tipo_Servico =" + ed.getOID_Tipo_Servico ();
      }
      // System.out.println ("PAsso 1 " + sql);
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("erro _Custo_Fixo");
      excecoes.setMetodo ("inclui_Custo_Fixo");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

}
