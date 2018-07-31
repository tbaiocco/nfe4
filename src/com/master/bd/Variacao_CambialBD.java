package com.master.bd;
import java.sql.*;
import java.util.*;

import com.master.ed.*;
import com.master.root.*;
import com.master.util.*;
import com.master.util.bd.*;
import com.master.util.ed.*;

public class Variacao_CambialBD extends Transacao{

  private ExecutaSQL executasql;
     String Nr_Sort = "";
     long OID_Variacao_Cambial=0;
     private FormataDataBean dataFormatada = new FormataDataBean();
     Parametro_FixoED parametro_FixoED = new Parametro_FixoED();

     Utilitaria util = new Utilitaria();
     Data data = new Data();

  public Variacao_CambialBD(ExecutaSQL sql) {
    this.executasql = sql;
  }



  public Variacao_CambialED geraVariacao_Cambial(Variacao_CambialED ed)throws Excecoes{


    try{

      //this.analisa_Conhecimentos_Liquidados(ed);
      analisa_Conhecimentos_Emitidos(ed);
    }

    catch (Excecoes e){
      throw e;
    }
    catch(Exception exc){
      Excecoes exce = new Excecoes();
      exce.setExc(exc);
      exce.setMensagem("Erro no m�todo geraVariacao_Cambial");
      exce.setClasse(this.getClass().getName());
      exce.setMetodo("geraVariacao_Cambial(Variacao_CambialED ed)");
    }
    return ed;
  }

  public Variacao_CambialED analisa_Conhecimentos_Liquidados(Variacao_CambialED ed)throws Excecoes{

     String sql = null;
     ResultSet res = null;

    try{

      sql  = " SELECT Conhecimentos_Internacionais.oid_Conhecimento, " +
             "        Conhecimentos_Internacionais.DT_Emissao, " +
             "        Conhecimentos_Internacionais.VL_Frete, " +
             "        Movimentos_Duplicatas.oid_Movimento_Duplicata, " +
             "        Movimentos_Duplicatas.dt_Movimento, " +
             "        Movimentos_Duplicatas.vl_Cotacao_Emissao, " +
             "        Movimentos_Duplicatas.vl_Cotacao_Pagamento  " +
             " FROM  " +
             "        Conhecimentos_Internacionais, " +
             "        Origens_Duplicatas, " +
             "        Movimentos_Duplicatas " +
             " WHERE  Conhecimentos_Internacionais.VL_Frete >0" +
             " AND    Conhecimentos_Internacionais.oid_Conhecimento = Origens_Duplicatas.oid_Conhecimento " +
             " AND    Movimentos_Duplicatas.oid_Duplicata = Origens_Duplicatas.oid_Duplicata " +
             " AND    Movimentos_Duplicatas.DM_Principal ='S' " +
             " AND    Movimentos_Duplicatas.DT_Movimento >= '" + ed.getDT_Periodo_Inicial() + "'" +
             " AND    Movimentos_Duplicatas.DT_Movimento <= '" + ed.getDT_Periodo_Final() + "'" ;



      // System.out.println(" sql " + sql);

      res = this.executasql.executarConsulta(sql.toString());

      while (res.next()){
        Variacao_CambialED edVC = new Variacao_CambialED();
        edVC= ed;

        edVC.setOid_Conhecimento(res.getString("oid_Conhecimento"));
        edVC.setOid_Movimento_Duplicata(res.getString("oid_Movimento_Duplicata"));
        edVC.setDT_Cotacao_Calculo_Inicial(ed.getDT_Periodo_Inicial());
        edVC.setDT_Cotacao_Calculo_Final(ed.getDT_Periodo_Final());
        edVC.setNM_Periodo( ed.getDT_Periodo_Inicial().substring(3,10));

        if (data.comparaData(dataFormatada.getDT_FormataData(res.getString("DT_Emissao")),ed.getDT_Periodo_Inicial())){
          edVC.setDT_Cotacao_Calculo_Inicial(dataFormatada.getDT_FormataData(res.getString("DT_Emissao")));
        }

        if (data.comparaData(ed.getDT_Periodo_Final(),dataFormatada.getDT_FormataData(res.getString("dt_Movimento")))){
          edVC.setDT_Cotacao_Calculo_Final(dataFormatada.getDT_FormataData(res.getString("dt_Movimento")));
        }

        // System.out.println(" geraVariacao_Cambial 1=> " );

        edVC.setDT_Liquidacao(dataFormatada.getDT_FormataData(res.getString("dt_Movimento")));
        edVC.setVL_Cotacao_Pagamento(res.getDouble("vl_Cotacao_Pagamento"));

        edVC = this.calcula_Variacao(edVC);

        // System.out.println(" geraVariacao_Cambial 100=> " );

        this.inclui(edVC);
      }
    }

    catch (Excecoes e){
      throw e;
    }
    catch(Exception exc){
      Excecoes exce = new Excecoes();
      exce.setExc(exc);
      exce.setMensagem("Erro no m�todo analisa_Conhecimentos_Liquidados");
      exce.setClasse(this.getClass().getName());
      exce.setMetodo("analisa_Conhecimentos_Liquidados(Variacao_CambialED ed)");
    }
    return ed;
  }

  public Variacao_CambialED analisa_Conhecimentos_Emitidos(Variacao_CambialED ed)throws Excecoes{

     String sql = null;
     ResultSet res = null;
    try{


      sql  = " SELECT Conhecimentos_Internacionais.oid_Conhecimento, " +
             "        Conhecimentos_Internacionais.DT_Emissao, " +
             "        Conhecimentos_Internacionais.VL_Frete " +
             " FROM  " +
             "        Conhecimentos_Internacionais " +
             " WHERE  Conhecimentos_Internacionais.VL_Frete >0" +
             " AND    Conhecimentos_Internacionais.DT_Emissao >= '" + ed.getDT_Periodo_Inicial() + "'" +
             " AND    Conhecimentos_Internacionais.DT_Emissao <= '" + ed.getDT_Periodo_Final() + "'" ;



      // System.out.println(" sql " + sql);

      res = this.executasql.executarConsulta(sql.toString());

      while (res.next()){
        Variacao_CambialED edVC = new Variacao_CambialED();
        // System.out.println(" geraVariacao_Cambial 1=> " );
        // System.out.println(" geraVariacao_Cambial 2 => "+  ed.getDT_Periodo_Inicial()) ;
        // System.out.println(" geraVariacao_Cambial 2 => "+  ed.getDT_Periodo_Final()) ;

        edVC.setOid_Conhecimento(res.getString("oid_Conhecimento"));
        edVC.setOid_Movimento_Duplicata("");
        edVC.setDT_Liquidacao(ed.getDT_Periodo_Inicial());
        edVC.setDT_Cotacao_Calculo_Inicial(ed.getDT_Periodo_Inicial());
        edVC.setDT_Cotacao_Calculo_Final(ed.getDT_Periodo_Final());
        edVC.setNM_Periodo( ed.getDT_Periodo_Inicial().substring(3,10));
        // System.out.println(" geraVariacao_Cambial 2=> " );
        edVC.setDT_Periodo_Inicial(ed.getDT_Periodo_Inicial());
        edVC.setDT_Periodo_Final(ed.getDT_Periodo_Final());
        edVC.setVL_Frete(res.getDouble("VL_Frete"));

        edVC = this.calcula_Variacao(edVC);

        // System.out.println(" geraVariacao_Cambial 2 antes=> " );

        this.inclui(edVC);
      }
       //// System.out.println("bd 1");


    }

    catch (Excecoes e){
      throw e;
    }
    catch(Exception exc){
      Excecoes exce = new Excecoes();
      exce.setExc(exc);
      exce.setMensagem("Erro no m�todo analisa_Conhecimentos_Emitidos");
      exce.setClasse(this.getClass().getName());
      exce.setMetodo("analisa_Conhecimentos_Emitidos(Variacao_CambialED ed)");
    }
    return ed;
  }

  public Variacao_CambialED calcula_Variacao(Variacao_CambialED edVC)throws Excecoes{

    try{

      edVC.setVL_Cotacao_Calculo_Inicial(edVC.getVL_Cotacao_Inicial());
      edVC.setVL_Cotacao_Calculo_Final(edVC.getVL_Cotacao_Final());

      if (edVC.getVL_Cotacao_Pagamento()>0 ) {
        edVC.setVL_Cotacao_Calculo_Final(edVC.getVL_Cotacao_Pagamento());
      } else {
      }
      if (edVC.getVL_Cotacao_Calculo_Inicial()<=0) edVC.setVL_Cotacao_Calculo_Inicial(edVC.getVL_Cotacao_Inicial());
      if (edVC.getVL_Cotacao_Calculo_Final()<=0) edVC.setVL_Cotacao_Calculo_Final(edVC.getVL_Cotacao_Final());

      edVC.setVL_Variacao_Cambial((edVC.getVL_Frete()/edVC.getVL_Cotacao_Calculo_Inicial()*edVC.getVL_Cotacao_Calculo_Final())-edVC.getVL_Frete());
    }

    catch(Exception exc){
      Excecoes exce = new Excecoes();
      exce.setExc(exc);
      exce.setMensagem("Erro no m�todo calcula_Variacao");
      exce.setClasse(this.getClass().getName());
      exce.setMetodo("calcula_Variacao(Variacao_CambialED ed)");
    }
    return edVC;
  }


  public void inclui(Variacao_CambialED ed) throws Excecoes{

    String sql = null;
    long valOid =System.currentTimeMillis ();
    ResultSet rs = null;
    String OID_Variacao_Cambial="";

    // System.out.println(" geraVariacao_Cambial inclui 1 --" );

    try{

      sql=" SELECT Variacoes_Cambiais.OID_Variacao_Cambial " +
          " FROM  Variacoes_Cambiais " +
          " WHERE Variacoes_Cambiais.oid_Conhecimento = '" + ed.getOid_Conhecimento() + "'" +
          " AND   Variacoes_Cambiais.NM_Periodo ='" + ed.getNM_Periodo() + "'";

      // System.out.println(" geraVariacao_Cambial inclui 2 -- " +sql);

          rs = this.executasql.executarConsulta(sql);
          while (rs.next()){
            // System.out.println(" geraVariacao_Cambial inclui OID_Variacao_Cambial -- " +OID_Variacao_Cambial);
            OID_Variacao_Cambial=rs.getString("OID_Variacao_Cambial");
          }

          // System.out.println(" geraVariacao_Cambial inclui 3 --" );

      if ("".equals(OID_Variacao_Cambial)) {

        // System.out.println(" geraVariacao_Cambial inclui 4 " );

        sql = " insert into Variacoes_Cambiais (OID_Variacao_Cambial," +
            " OID_Conhecimento, " +
            " OID_Movimento_Duplicata, " +
            " dt_periodo_inicial," +
            " dt_periodo_final, " +
            " dt_cotacao_calculo_inicial," +
            " dt_cotacao_calculo_final," +
            " nm_Periodo, " +
            " dt_liquidacao, " +
            " vl_cotacao_inicial," +
            " vl_cotacao_final, " +
            " vl_frete, " +
            " vl_cotacao_pagamento," +
            " vl_cotacao_calculo_inicial," +
            " vl_cotacao_calculo_final, " +
            " vl_variacao_cambial )" +
            " values (" +
            "'" + valOid + "'" +
            ",'" + ed.getOid_Conhecimento () + "'" +
            ",'" + ed.getOid_Movimento_Duplicata () + "'" +
            ",'" + ed.getDT_Periodo_Inicial () + "'" +
            ",'" + ed.getDT_Periodo_Final () + "'" +
            ",'" + ed.getDT_Cotacao_Calculo_Inicial () + "'" +
            ",'" + ed.getDT_Cotacao_Calculo_Final () + "'" +
            ",'" + ed.getNM_Periodo () + "'" +
            ",'" + ed.getDT_Liquidacao () + "'" +
            ", " + ed.getVL_Cotacao_Inicial () +
            ", " + ed.getVL_Cotacao_Final () +
            ", " + ed.getVL_Frete () +
            ", " + ed.getVL_Cotacao_Pagamento () +
            ", " + ed.getVL_Cotacao_Calculo_Inicial () +
            ", " + ed.getVL_Cotacao_Calculo_Final () +
            ", " + ed.getVL_Variacao_Cambial () +
            ")";

        // System.out.println(" geraVariacao_Cambial inclui 4=> " );

        // System.out.println ("inclui" + sql);

        int i = executasql.executarUpdate (sql);
      // //// System.out.println("ok");
      }
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setMensagem("Erro de Variacao_Cambial de dados");
      excecoes.setExc(exc);
    }
  }


  public Variacao_CambialED getByRecord(Variacao_CambialED ed)throws Excecoes {
      String sql =
      " SELECT Variacoes_Cambiais.oid_Conhecimento,  " +
      "        Variacoes_Cambiais.oid_Variacao_Cambial  " +
      " FROM  Variacoes_Cambiais, Conhecimentos_Internacionais " +
      " WHERE Variacoes_Cambiais.oid_Conhecimento = Conhecimentos_Internacionais.oid_Conhecimento";

      if (ed.getOid_Variacao_Cambial()>0) {
        sql +=" AND   Variacoes_Cambiais.oid_Variacao_Cambial = " + ed.getOid_Variacao_Cambial();
      }
      else {
        sql +=" AND Variacoes_Cambiais.oid_Conhecimento = '" + ed.getOid_Conhecimento() + "'" +
              " AND Variacoes_Cambiais.NM_Periodo '" + ed.getNM_Periodo() + "'";
      }

      Variacao_CambialED edVolta = new Variacao_CambialED();
      try {
          ResultSet res = this.executasql.executarConsulta(sql);
          while (res.next()){
              edVolta.setOid_Variacao_Cambial(res.getLong("oid_Variacao_Cambial"));
          }
      } catch(SQLException exc){
          throw new Excecoes(exc.getMessage(), exc,
                                 this.getClass().getName(),
                                 "getByRecord()");
      }
      return edVolta;
  }

  public void deleta(Variacao_CambialED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from Variacao_Cambial WHERE oid_Variacao_Cambial = ";
      sql += "(" + ed.getOid_Variacao_Cambial() + ")";

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao excluir ");
      excecoes.setMetodo("deletar");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public ArrayList lista(Variacao_CambialED ed) throws Excecoes {

      String sql = null;
      ArrayList list = new ArrayList();

      try {

        sql=
        " SELECT * " +
        " FROM  Variacoes_Cambiais " +
        " WHERE Variacoes_Cambiais.oid_Variacao_Cambial = " + ed.getOid_Variacao_Cambial();


          ResultSet res = null;
          res = this.executasql.executarConsulta(sql);

          //popula

          while (res.next()) {
              Variacao_CambialED edVolta = new Variacao_CambialED();
              edVolta.setDT_Periodo_Inicial(FormataData.formataDataBT(res.getDate("DT_Periodo_Inicial")));
              edVolta.setOid_Unidade(res.getLong("Oid_Unidade"));
              edVolta.setVL_Cotacao_Inicial(res.getDouble("VL_Cotacao_Inicial"));

              list.add(edVolta);
          }
      } catch (SQLException e) {
          throw new Excecoes(e.getMessage(), e, getClass().getName(), "Variacao_Cambial_ListaAcertoContas(Variacao_CambialED ed)");
      }

      return list;
  }


}
