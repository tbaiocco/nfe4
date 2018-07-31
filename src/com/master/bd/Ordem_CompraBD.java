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

import com.master.ed.Ordem_CompraED;
import com.master.rl.Ordem_CompraRL;
import com.master.root.FormataDataBean;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

public class Ordem_CompraBD {

  private ExecutaSQL executasql;

  public Ordem_CompraBD(ExecutaSQL sql) {
    this.executasql = sql;
  }



  public ArrayList lista(Ordem_CompraED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();

    try{
      sql =  " SELECT OID_Ordem_Compra, DT_Ordem_Compra, HR_Ordem_Compra, Viagens.DT_Previsao_Chegada, Viagens.HR_Previsao_Chegada, Conhecimentos.OID_Conhecimento, Conhecimentos.NR_Conhecimento, Conhecimentos.DT_Emissao, Ordem_Compras.NR_Ordem_Compra, Unidades.CD_Unidade, Pessoa_Remetente.NM_Razao_Social as NM_Razao_Social_Remetente, Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario, Ordem_Compras.OID_Ordem_Compra, Cidades.NM_Cidade from Viagens, Conhecimentos, Unidades, Pessoas Pessoa_Remetente, Pessoas Pessoa_Destinatario, Ordem_Compras, Cidades ";
      sql += " WHERE Unidades.oid_Unidade = Conhecimentos.oid_Unidade ";
      sql += " AND Conhecimentos.oid_Pessoa = Pessoa_Remetente.oid_Pessoa ";
      sql += " AND Conhecimentos.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa ";
      sql += " AND Viagens.OID_Conhecimento = Conhecimentos.OID_Conhecimento ";
      sql += " AND Viagens.OID_Ordem_Compra = Ordem_Compras.OID_Ordem_Compra ";
      sql += " AND Conhecimentos.OID_Cidade_Destino = Cidades.OID_Cidade ";

      if (String.valueOf(ed.getOID_Ordem_Compra()) != null &&
          !String.valueOf(ed.getOID_Ordem_Compra()).equals("0") &&
          !String.valueOf(ed.getOID_Ordem_Compra()).equals("null")){
        sql += " and Ordem_Compras.OID_Ordem_Compra = '" + ed.getOID_Ordem_Compra() + "'";
      }

      if (String.valueOf(ed.getOID_Unidade()) != null &&
          !String.valueOf(ed.getOID_Unidade()).equals("0") &&
          !String.valueOf(ed.getOID_Unidade()).equals("null")){
        sql += " and Conhecimentos.OID_Unidade = " + ed.getOID_Unidade();
      }
      if (String.valueOf(ed.getOID_Pessoa()) != null &&
          !String.valueOf(ed.getOID_Pessoa()).equals("") &&
          !String.valueOf(ed.getOID_Pessoa()).equals("null")){
        sql += " and Conhecimentos.OID_Pessoa = '" + ed.getOID_Pessoa() + "'";
      }
      if (String.valueOf(ed.getDT_Emissao()) != null &&
          !String.valueOf(ed.getDT_Emissao()).equals("") &&
          !String.valueOf(ed.getDT_Emissao()).equals("null")){
        sql += " and Conhecimentos.DT_Emissao = '" + ed.getDT_Emissao() + "'";
      }
      if (String.valueOf(ed.getDT_Emissao_Inicial()) != null &&
          !String.valueOf(ed.getDT_Emissao_Inicial()).equals("") &&
          !String.valueOf(ed.getDT_Emissao_Inicial()).equals("null")){
        sql += " and Viagens.DT_Ordem_Compra >= '" + ed.getDT_Emissao_Inicial() + "'";
      }

      sql += " Order by Viagens.Dt_Previsao_Chegada, Viagens.Hr_Previsao_Chegada ";

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      FormataDataBean DataFormatada = new FormataDataBean();

      //popula
      while (res.next()){
        Ordem_CompraED edVolta = new Ordem_CompraED();

        edVolta.setOID_Ordem_Compra(res.getLong("OID_Ordem_Compra"));

        edVolta.setDT_Emissao(res.getString("DT_Emissao"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
        edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());

        edVolta.setNR_Ordem_Compra(res.getLong("NR_Ordem_Compra"));
        edVolta.setCD_Unidade(res.getString("CD_Unidade"));
        edVolta.setOID_Ordem_Compra(res.getLong("OID_Ordem_Compra"));
        list.add(edVolta);
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao listar");
      excecoes.setMetodo("listar");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return list;
  }


  public byte[] Relatorio_Ordem_Compra(Ordem_CompraED ed)throws Excecoes{

    String sql = null;
    ResultSet res = null;
    ResultSet rs = null;
    int Nr_Sort=0;

    sql = null;
    byte[] b = null;

      sql = " SELECT Ordens_Compras.*, " +
            " Tipos_Servicos.*,  " + 
            " Unidades.CD_Unidade, "  + 
            " Pessoa_Fornecedor.NM_Razao_Social as NM_Fornecedor  " +
            " FROM Ordens_Compras, Unidades, Tipos_Servicos, Pessoas Pessoa_Fornecedor " +
            " WHERE Ordens_Compras.OID_Unidade = Unidades.OID_Unidade " +
            "  AND Ordens_Compras.oid_pessoa_fornecedor    = Pessoa_Fornecedor.oid_Pessoa " +
            "  AND Ordens_Compras.oid_Tipo_Compra = Tipos_Servicos.OID_Tipo_Servico ";

            if (String.valueOf(ed.getOID_Empresa()) != null && !String.valueOf(ed.getOID_Empresa()).equals("0")){
              sql += " and Unidades.OID_Empresa = " + ed.getOID_Empresa();
            }

            if (String.valueOf(ed.getOID_Unidade()) != null && !String.valueOf(ed.getOID_Unidade()).equals("0")){
              sql += " and Ordens_Compras.OID_Unidade = " + ed.getOID_Unidade();
            }

            if (String.valueOf(ed.getDT_Emissao_Inicial()) != null &&
              !String.valueOf(ed.getDT_Emissao_Inicial()).equals("") &&
              !String.valueOf(ed.getDT_Emissao_Inicial()).equals("null")){
              sql += " and Ordens_Compras.dt_ordem_compra >= '" + ed.getDT_Emissao_Inicial() + "'";
            }

            if (String.valueOf(ed.getDT_Emissao_Final()) != null &&
            !String.valueOf(ed.getDT_Emissao_Final()).equals("") &&
            !String.valueOf(ed.getDT_Emissao_Final()).equals("null")){
              sql += " and Ordens_Compras.dt_ordem_compra <= '" + ed.getDT_Emissao_Final() + "'";
            }

            if (String.valueOf(ed.getOID_Pessoa()) != null &&
            !String.valueOf(ed.getOID_Pessoa()).equals("null") &&
            !String.valueOf(ed.getOID_Pessoa()).equals("")){
              sql += " and Ordens_Compras.oid_pessoa = '" + ed.getOID_Pessoa() + "'";
            }

            if (String.valueOf(ed.getOID_Pessoa_Funcionario()) != null &&
            !String.valueOf(ed.getOID_Pessoa_Funcionario()).equals("null") &&
            !String.valueOf(ed.getOID_Pessoa_Funcionario()).equals("")){
              sql += " and Ordens_Compras.oid_pessoa_funcionario = '" + ed.getOID_Pessoa_Funcionario() + "'";
            }

            if (String.valueOf(ed.getNR_Placa()) != null &&
            !String.valueOf(ed.getNR_Placa()).equals("null") &&
            !String.valueOf(ed.getNR_Placa()).equals("A") &&
            !String.valueOf(ed.getNR_Placa()).equals("")){
              sql += " and Ordens_Compras.OID_Veiculo = '" + ed.getNR_Placa() + "'";
            }
            if ("A".equals(ed.getDM_Relatorio())){
              sql += " and Ordens_Compras.oid_pessoa_funcionario is not null ";
            }

            sql += "  ORDER BY Ordens_Compras.nr_ordem_compra ";

      // System.out.println("sql " + sql);

    try{

      res = null;
      Ordem_CompraRL conRL = new Ordem_CompraRL();
      res = this.executasql.executarConsulta(sql.toString());

      b = conRL.Relatorio_Ordem_Compra (ed , res);

    }

    catch (Excecoes e){
      throw e;
    }
    catch(Exception exc){
      Excecoes exce = new Excecoes();
      exce.setExc(exc);
      exce.setMensagem("Erro no método listar");
      exce.setClasse(this.getClass().getName());
      exce.setMetodo("Relatorio_Ordem_Compra");
    }
    return b;
  }

  public byte[] imprime_Requisicao(Ordem_CompraED ed)throws Excecoes{

    String sql = null;
    ResultSet res = null;
    ResultSet rs = null;
    int Nr_Sort=0;

    sql = null;
    byte[] b = null;

      sql = " SELECT Ordens_Compras.*, Tipos_Servicos.CD_Tipo_Servico,  Tipos_Servicos.NM_Tipo_Servico, Unidades.CD_Unidade " +
	    " FROM Ordens_Compras, Unidades, Tipos_Servicos " + //, Pessoas Pessoa_Fornecedor " +
            " WHERE Ordens_Compras.OID_Unidade = Unidades.OID_Unidade " +
            "  AND Ordens_Compras.oid_Tipo_Compra = Tipos_Servicos.OID_Tipo_Servico ";

            if (String.valueOf(ed.getOID_Ordem_Compra()) != null &&
                !String.valueOf(ed.getOID_Ordem_Compra()).equals("0") &&
                !String.valueOf(ed.getOID_Ordem_Compra()).equals("null")){
              sql += " and Ordens_Compras.OID_Ordem_Compra = " + ed.getOID_Ordem_Compra();
            }

      // System.out.println("sql " + sql);


    try{

      res = null;
      Ordem_CompraRL conRL = new Ordem_CompraRL();
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
      exce.setMetodo("Relatorio_Ordem_Compra");
    }
    return b;
  }

  public void deleta_Movimento(Ordem_CompraED ed) throws Excecoes{

    String sql = null;
    String erro ="";
    ResultSet res = null;

    int pagamentos=0, i=0, compromisso=0;

    try{

        sql =" Select vl_pagamento from Compromissos_Compras, Movimentos_Compromissos " +
             " WHERE Compromissos_Compras.oid_Compromisso = Movimentos_Compromissos.oid_Compromisso " +
             " AND   Compromissos_Compras.oid_Movimento_Ordem_Compra  = " + ed.getOID_Movimento_Ordem_Compra() ;
        // System.out.println("PAsso 1 " + sql);
        res = this.executasql.executarConsulta(sql);
        while (res.next()){
          erro="Há pagamentos nos compromissos ";
          pagamentos++;
        }


        sql =" Select vl_pagamento from Compromissos_Compras, Lotes_Compromissos " +
             " WHERE Compromissos_Compras.oid_Compromisso = Lotes_Compromissos.oid_Compromisso " +
             " AND   Compromissos_Compras.oid_Movimento_Ordem_Compra  = " + ed.getOID_Movimento_Ordem_Compra() ;
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
            sql =" select oid_Compromisso, oid_Movimento_Ordem_Compra from Compromissos_Compras " +
                 "  WHERE Compromissos_Compras.oid_Movimento_Ordem_Compra  = " + ed.getOID_Movimento_Ordem_Compra() ;
          // System.out.println("PAsso 2 " + sql);
            res = this.executasql.executarConsulta(sql);
            while (res.next()){
                compromisso++;
                sql = "delete from Compromissos_Compras WHERE oid_Movimento_Ordem_Compra = ";
                sql += "(" + res.getString("oid_Movimento_Ordem_Compra") + ")";
                // System.out.println("PAsso 4 " + sql);
                i = executasql.executarUpdate(sql);

                sql = "delete from Compromissos WHERE oid_Compromisso= " + res.getString("oid_Compromisso");
                // System.out.println("PAsso 5 " + sql);
                i = executasql.executarUpdate(sql);
            }
       }
       sql = "delete from Movimentos_Ordens_Compras WHERE oid_Movimento_Ordem_Compra = ";
       sql += "(" + ed.getOID_Movimento_Ordem_Compra() + ")";
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