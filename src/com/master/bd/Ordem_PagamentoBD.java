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

import com.master.ed.Ordem_PagamentoED;
import com.master.rl.Ordem_PagamentoRL;
import com.master.root.FormataDataBean;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

public class Ordem_PagamentoBD {

  private ExecutaSQL executasql;

  public Ordem_PagamentoBD(ExecutaSQL sql) {
    this.executasql = sql;
  }




  public byte[] Relatorio_Ordem_Pagamento(Ordem_PagamentoED ed)throws Excecoes{

    String sql = null;
    ResultSet res = null;
    ResultSet rs = null;
    int Nr_Sort=0;

    sql = null;
    byte[] b = null;

      sql = " SELECT Ordens_Pagamentos.OID_Ordem_Pagamento,	Ordens_Pagamentos.OID_Unidade, Ordens_Pagamentos.OID_Veiculo, Ordens_Pagamentos.OID_Tipo_Pagamento, Ordens_Pagamentos.NR_Ordem_Pagamento, Ordens_Pagamentos.NR_Kilometragem, Ordens_Pagamentos.DT_Ordem_Pagamento, Ordens_Pagamentos.DT_Encerramento, Ordens_Pagamentos.NR_Meses_Amortizacao, Ordens_Pagamentos.VL_Previsto, Ordens_Pagamentos.TX_Observacao, Tipos_Pagamentos.CD_Tipo_Pagamento,  Tipos_Pagamentos.NM_Tipo_Pagamento, Tipos_Pagamentos.DM_Medida, Tipos_Pagamentos.CD_Tipo_Pagamento, Unidades.CD_Unidade, Ordens_Pagamentos.OID_Veiculo, Pessoa_Fornecedor.NM_Razao_Social as NM_Fornecedor,  " +
            " Movimentos_Ordens_Pagamentos.DT_Movimento_Ordem_Pagamento, Movimentos_Ordens_Pagamentos.NR_Documento, Movimentos_Ordens_Pagamentos.NR_Quantidade, Movimentos_Ordens_Pagamentos.NR_Kilometragem as Odometro, Movimentos_Ordens_Pagamentos.DM_Faturado_Pago, Movimentos_Ordens_Pagamentos.VL_Movimento " +
            " FROM Ordens_Pagamentos, Unidades, Veiculos, Tipos_Pagamentos, Pessoas Pessoa_Fornecedor, Movimentos_Ordens_Pagamentos " +
            " WHERE Ordens_Pagamentos.OID_Unidade = Unidades.OID_Unidade " +
            "  AND Movimentos_Ordens_Pagamentos.OID_Ordem_Pagamento = Ordens_Pagamentos.oid_ordem_Pagamento "+
            "  AND Movimentos_Ordens_Pagamentos.oid_Tipo_Pagamento = Tipos_Pagamentos.OID_Tipo_Pagamento "+
            "  AND Movimentos_Ordens_Pagamentos.oid_Pessoa = Pessoa_Fornecedor.OID_Pessoa "+
            "  AND Ordens_Pagamentos.OID_Veiculo = Veiculos.OID_Veiculo " ;

            if (String.valueOf(ed.getOID_Ordem_Pagamento()) != null &&
                !String.valueOf(ed.getOID_Ordem_Pagamento()).equals("0") &&
                !String.valueOf(ed.getOID_Ordem_Pagamento()).equals("null")){
              sql += " and Ordem_Pagamentos.OID_Ordem_Pagamento = " + ed.getOID_Ordem_Pagamento();
            }
            if (String.valueOf(ed.getOID_Empresa()) != null && !String.valueOf(ed.getOID_Empresa()).equals("0")){
              sql += " and Unidades.OID_Empresa = " + ed.getOID_Empresa();
            }

            if (String.valueOf(ed.getOID_Unidade()) != null && !String.valueOf(ed.getOID_Unidade()).equals("0")){
              sql += " and Ordens_Pagamentos.OID_Unidade = " + ed.getOID_Unidade();
            }
            if (String.valueOf(ed.getOID_Tipo_Pagamento()) != null && !String.valueOf(ed.getOID_Tipo_Pagamento()).equals("0")){
              sql += " and Movimentos_Ordens_Pagamentos.OID_Tipo_Pagamento = " + ed.getOID_Tipo_Pagamento();
            }
            if (String.valueOf(ed.getOID_Pessoa()) != null && !String.valueOf(ed.getOID_Pessoa()).equals("") && !String.valueOf(ed.getOID_Pessoa()).equals("null")){
              sql += " and Movimentos_Ordens_Pagamentos.OID_Pessoa = '" + ed.getOID_Pessoa() + "'";
            }
            if (String.valueOf(ed.getDT_Emissao_Inicial()) != null &&
              !String.valueOf(ed.getDT_Emissao_Inicial()).equals("") &&
              !String.valueOf(ed.getDT_Emissao_Inicial()).equals("null")){
              sql += " and Ordens_Pagamentos.DT_Ordem_Pagamento >= '" + ed.getDT_Emissao_Inicial() + "'";
            }
            if (String.valueOf(ed.getDT_Emissao_Final()) != null &&
            !String.valueOf(ed.getDT_Emissao_Final()).equals("") &&
            !String.valueOf(ed.getDT_Emissao_Final()).equals("null")){
              sql += " and Ordens_Pagamentos.DT_Ordem_Pagamento <= '" + ed.getDT_Emissao_Final() + "'";
            }
            if (String.valueOf(ed.getNR_Placa()) != null &&
            !String.valueOf(ed.getNR_Placa()).equals("null") &&
            !String.valueOf(ed.getNR_Placa()).equals("A") &&
            !String.valueOf(ed.getNR_Placa()).equals("")){
              sql += " and Ordens_Pagamentos.OID_Veiculo = '" + ed.getNR_Placa() + "'";
            }
            if (String.valueOf(ed.getDM_Tipo_Pagamento()) != null &&
            !String.valueOf(ed.getDM_Tipo_Pagamento()).equals("null") &&
            !String.valueOf(ed.getDM_Tipo_Pagamento()).equals("T") &&
            !String.valueOf(ed.getDM_Tipo_Pagamento()).equals("")){
              sql += " and Ordens_Pagamentos.DM_Tipo_Pagamento = '" + ed.getDM_Tipo_Pagamento() + "'";
            }

            sql += "  ORDER BY Ordens_Pagamentos.oid_veiculo, Movimentos_Ordens_Pagamentos.DT_Movimento_Ordem_Pagamento ";

      //// System.out.println("sql " + sql);

    Ordem_PagamentoED edVolta = new Ordem_PagamentoED();


    try{

      res = null;
      Ordem_PagamentoRL conRL = new Ordem_PagamentoRL();
      res = this.executasql.executarConsulta(sql.toString());


      if (ed.getDM_Relatorio().equals("1")) {
            //// System.out.println(" rel 1");

                  b =  conRL.Relatorio_Ordem_Pagamento(ed,res);
      }

    }

    catch (Excecoes e){
      throw e;
    }
    catch(Exception exc){
      Excecoes exce = new Excecoes();
      exce.setExc(exc);
      exce.setMensagem("Erro no método listar");
      exce.setClasse(this.getClass().getName());
      exce.setMetodo("Relatorio_Ordem_Pagamento");
    }
    return b;
  }

  public byte[] imprime_Requisicao(Ordem_PagamentoED ed)throws Excecoes{

    String sql = null;
    ResultSet res = null;
    ResultSet rs = null;
    int Nr_Sort=0;

    sql = null;
    byte[] b = null;

      sql = " SELECT Ordens_Pagamentos.*, Tipos_Servicos.CD_Tipo_Servico,  Tipos_Servicos.NM_Tipo_Servico, Unidades.CD_Unidade " +
	    " FROM Ordens_Pagamentos, Unidades, Tipos_Servicos " + //, Pessoas Pessoa_Fornecedor " +
            " WHERE Ordens_Pagamentos.OID_Unidade = Unidades.OID_Unidade " +
            "  AND Ordens_Pagamentos.oid_Tipo_Pagamento = Tipos_Servicos.OID_Tipo_Servico ";

            if (String.valueOf(ed.getOID_Ordem_Pagamento()) != null &&
                !String.valueOf(ed.getOID_Ordem_Pagamento()).equals("0") &&
                !String.valueOf(ed.getOID_Ordem_Pagamento()).equals("null")){
              sql += " and Ordens_Pagamentos.OID_Ordem_Pagamento = " + ed.getOID_Ordem_Pagamento();
            }

      // System.out.println("sql " + sql);


    try{

      res = null;
      Ordem_PagamentoRL conRL = new Ordem_PagamentoRL();
      res = this.executasql.executarConsulta(sql.toString());

       b =  conRL.imprime_Requisicao(ed,res);

    }

    catch (Excecoes e){
      throw e;
    }
    catch(Exception exc){
      Excecoes exce = new Excecoes();
      exce.setExc(exc);
      exce.setMensagem("Erro no método listar");
      exce.setClasse(this.getClass().getName());
      exce.setMetodo("Relatorio_Ordem_Pagamento");
    }
    return b;
  }

  public void deleta_Movimento(Ordem_PagamentoED ed) throws Excecoes{

    String sql = null;
    String erro ="";
    ResultSet res = null;

    int pagamentos=0, i=0, compromisso=0;

    try{

        sql =" Select vl_pagamento from Compromissos_Pagamentos, Movimentos_Compromissos " +
             " WHERE Compromissos_Pagamentos.oid_Compromisso = Movimentos_Compromissos.oid_Compromisso " +
             " AND   Compromissos_Pagamentos.oid_Movimento_Ordem_Pagamento  = " + ed.getOID_Movimento_Ordem_Pagamento() ;
        // System.out.println("PAsso 1 " + sql);
        res = this.executasql.executarConsulta(sql);
        while (res.next()){
          erro="Há pagamentos nos compromissos ";
          pagamentos++;
        }


        sql =" Select vl_pagamento from Compromissos_Pagamentos, Lotes_Compromissos " +
             " WHERE Compromissos_Pagamentos.oid_Compromisso = Lotes_Compromissos.oid_Compromisso " +
             " AND   Compromissos_Pagamentos.oid_Movimento_Ordem_Pagamento  = " + ed.getOID_Movimento_Ordem_Pagamento() ;
        // System.out.println("PAsso 1 " + sql);
        res = this.executasql.executarConsulta(sql);
        while (res.next()){
          erro="Há Documento para Pagamento para este Compromisso";
          pagamentos++;
        }

        if (pagamentos>0) {
            Excecoes exc = new Excecoes();
            throw exc;
        }
        else {
            sql =" select oid_Compromisso, oid_Movimento_Ordem_Pagamento from Compromissos_Pagamentos " +
                 "  WHERE Compromissos_Pagamentos.oid_Movimento_Ordem_Pagamento  = " + ed.getOID_Movimento_Ordem_Pagamento() ;
          // System.out.println("PAsso 2 " + sql);
            res = this.executasql.executarConsulta(sql);
            while (res.next()){
                compromisso++;
                sql = "delete from Compromissos_Pagamentos WHERE oid_Movimento_Ordem_Pagamento = ";
                sql += "(" + res.getString("oid_Movimento_Ordem_Pagamento") + ")";
                // System.out.println("PAsso 4 " + sql);
                i = executasql.executarUpdate(sql);

                sql = "delete from Compromissos WHERE oid_Compromisso= " + res.getString("oid_Compromisso");
                // System.out.println("PAsso 5 " + sql);
                i = executasql.executarUpdate(sql);
            }
       }
       sql = "delete from Movimentos_Ordens_Pagamentos WHERE oid_Movimento_Ordem_Pagamento = ";
       sql += "(" + ed.getOID_Movimento_Ordem_Pagamento() + ")";
       // System.out.println("PAsso 7 " + sql);
       i = executasql.executarUpdate(sql);

    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem(erro);
      excecoes.setMetodo("deletar");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }



}