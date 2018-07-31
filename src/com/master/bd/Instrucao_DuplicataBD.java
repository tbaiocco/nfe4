package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.Instrucao_DuplicataED;
import com.master.root.FormataDataBean;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;


public class Instrucao_DuplicataBD {

  private ExecutaSQL executasql;
  FormataDataBean dataFormatada = new FormataDataBean();
  Parametro_FixoED parametro_FixoED = new Parametro_FixoED();


  public Instrucao_DuplicataBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public Instrucao_DuplicataED inclui(Instrucao_DuplicataED ed) throws Excecoes{


    String sql = null;
    long valOid = 0;
    Instrucao_DuplicataED Instrucao_DuplicataED = new Instrucao_DuplicataED();
    Data data = new Data();

    try{


      ResultSet rs = executasql.executarConsulta("select max(oid_Instrucao) as result from Instrucoes_Duplicatas");

      //pega proximo valor da chave
      while (rs.next()) valOid = rs.getInt("result");


      sql = "insert into Instrucoes_Duplicatas ( ";
      sql+= " OID_Instrucao,";
      sql+= " OID_Tipo_Instrucao, " ;
      sql+= " OID_Duplicata, " ;
      sql+= " OID_Carteira, " ;
      sql+= " VL_Instrucao, " ;
      sql+= " NR_Remessa, " ;
      sql+= " DT_Instrucao, " ;
      sql+= " HR_Instrucao, " ;
      sql+= " DM_Situacao, " ;
      sql+= " DT_Stamp, ";
      sql+= " Usuario_Stamp, ";
      sql+= " DM_STAMP ) ";
      sql+= " values (";
      sql+= ++valOid + ",";
      sql+= ed.getOid_Tipo_Instrucao() + ",";
      sql+= ed.getOid_Duplicata() + ",";
      sql+= ed.getOid_Carteira() + ",";
      sql+= ed.getVl_Instrucao() + ",";
      sql+= ed.getNr_Remessa() + ",'";
      sql+= ed.getDt_Instrucao() + "','";
      sql+= ed.getHr_Instrucao() + "','";
      sql+= ed.getDm_Situacao() + "','";
      sql+= ed.getDt_Stamp() + "','";
      sql+= ed.getUsuario_Stamp() + "','";
      sql+= ed.getDm_Stamp() + "')";

//     // System.err.println(sql);

      int i = executasql.executarUpdate(sql);
      Instrucao_DuplicataED.setOid_Instrucao(valOid);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir Instrucao_Duplicata");
      excecoes.setMetodo("inclui");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return Instrucao_DuplicataED;

  }


  public void deleta(Instrucao_DuplicataED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from Instrucoes_Duplicatas WHERE oid_Instrucao_Duplicata = ";
      sql += "(" + ed.getOid_Instrucao() + ")";

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao deletar Instrucao_Duplicata");
      excecoes.setMetodo("deleta(Instrucao_DuplicataED)");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public ArrayList lista(Instrucao_DuplicataED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();

    try{
      sql = " select * from Instrucoes_Duplicatas, Tipos_Instrucoes, Carteiras "+
            " WHERE Instrucoes_Duplicatas.oid_Tipo_Instrucao = Tipos_Instrucoes.oid_Tipo_Instrucao  " +
            " AND Instrucoes_Duplicatas.oid_Carteira = Carteiras.oid_Carteira  ";

      if (ed.getOid_Duplicata() >0 ){
        sql += " and Instrucoes_Duplicatas.oid_Duplicata = " + ed.getOid_Duplicata();
      }

   // System.err.println(sql);

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      //popula
      while (res.next()){
        Instrucao_DuplicataED edVolta = new Instrucao_DuplicataED();
        edVolta.setOid_Instrucao(res.getLong("oid_Instrucao"));
        edVolta.setOid_Tipo_Instrucao(res.getLong("oid_Tipo_Instrucao"));
        edVolta.setOid_Carteira(new Integer(res.getInt("oid_carteira")));
        edVolta.setNr_Remessa(res.getLong("nr_remessa"));
        edVolta.setDt_Instrucao(res.getString("dt_instrucao"));
        dataFormatada.setDT_FormataData(edVolta.getDt_Instrucao());
        edVolta.setDt_Instrucao(dataFormatada.getDT_FormataData());

        edVolta.setHr_Instrucao(res.getString("hr_instrucao"));
        edVolta.setDt_Retorno(res.getString("dt_Retorno"));
        dataFormatada.setDT_FormataData(edVolta.getDt_Retorno());
        edVolta.setDt_Retorno(dataFormatada.getDT_FormataData());


        edVolta.setDt_Novo_Vencimento(res.getString("dt_Novo_Vencimento"));
        dataFormatada.setDT_FormataData(edVolta.getDt_Novo_Vencimento());
        edVolta.setDt_Novo_Vencimento(dataFormatada.getDT_FormataData());

        edVolta.setDt_Remessa(res.getString("dt_Remessa"));
        dataFormatada.setDT_FormataData(edVolta.getDt_Remessa());
        edVolta.setDt_Remessa(dataFormatada.getDT_FormataData());

        edVolta.setVl_Instrucao(res.getDouble("vl_instrucao"));

        edVolta.setCd_Tipo_Instrucao(res.getString("CD_Tipo_Instrucao"));
        edVolta.setNm_Tipo_Instrucao(res.getString("Nm_Tipo_Instrucao"));
        edVolta.setCd_Carteira(res.getString("CD_Carteira"));
        edVolta.setDm_Situacao(res.getString("DM_Situacao"));
        if ("I".equals(res.getString("DM_Situacao"))){
                  edVolta.setDm_Situacao("Não Confirmado.");
        }
        if ("L".equals(res.getString("DM_Situacao"))){
                  edVolta.setDm_Situacao("Confirmado/Liquidado.");
        }
        if ("C".equals(res.getString("DM_Situacao"))){
                  edVolta.setDm_Situacao("Confirmado em :" + edVolta.getDt_Retorno());
        }
        if ("R".equals(res.getString("DM_Situacao"))){
                  edVolta.setDm_Situacao("Rejeitado em :" + edVolta.getDt_Retorno());
        }
        

        list.add(edVolta);
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao listar Instrucao_Duplicatas - SQL="+sql);
      excecoes.setMetodo("lista(Instrucao_DuplicatasED)");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return list;
  }

  public Instrucao_DuplicataED getByRecord(Instrucao_DuplicataED ed)throws Excecoes{

    String sql = null;

    Instrucao_DuplicataED edVolta = new Instrucao_DuplicataED();

    try{

      sql = " SELECT * from Instrucoes_Duplicatas, Tipos_Instrucoes, Carteiras, Duplicatas, Clientes "+
            " WHERE Instrucoes_Duplicatas.oid_Tipo_Instrucao = Tipos_Instrucoes.oid_Tipo_Instrucao  " +
            " AND Instrucoes_Duplicatas.oid_Carteira = Carteiras.oid_Carteira  " +
            " AND Instrucoes_Duplicatas.oid_Duplicata = Duplicatas.oid_Duplicata  " +
            " AND Instrucoes_Duplicatas.oid_Instrucao = " + ed.getOid_Instrucao();

    // System.err.println(sql);

      ResultSet res = null;

      //// System.err.println(sql);

      res = this.executasql.executarConsulta(sql);
      while (res.next()){
        edVolta = new Instrucao_DuplicataED();
        edVolta.setOid_Instrucao(res.getLong("oid_Instrucao"));
        edVolta.setOid_Duplicata(res.getLong("Oid_Duplicata"));
        edVolta.setOid_Tipo_Instrucao(res.getLong("oid_Tipo_Instrucao"));
        edVolta.setOid_Carteira(new Integer(res.getInt("oid_carteira")));
        edVolta.setNr_Remessa(res.getLong("nr_remessa"));
        edVolta.setDt_Instrucao(res.getString("dt_instrucao"));
        dataFormatada.setDT_FormataData(edVolta.getDt_Instrucao());
        edVolta.setDt_Instrucao(dataFormatada.getDT_FormataData());

        edVolta.setHr_Instrucao(res.getString("hr_instrucao"));
        edVolta.setDt_Retorno(res.getString("dt_Retorno"));
        dataFormatada.setDT_FormataData(edVolta.getDt_Retorno());
        edVolta.setDt_Retorno(dataFormatada.getDT_FormataData());


        edVolta.setDt_Novo_Vencimento(res.getString("dt_Novo_Vencimento"));
        dataFormatada.setDT_FormataData(edVolta.getDt_Novo_Vencimento());
        edVolta.setDt_Novo_Vencimento(dataFormatada.getDT_FormataData());

        edVolta.setDt_Remessa(res.getString("dt_Remessa"));
        dataFormatada.setDT_FormataData(edVolta.getDt_Remessa());
        edVolta.setDt_Remessa(dataFormatada.getDT_FormataData());

        edVolta.setVl_Instrucao(res.getDouble("vl_instrucao"));

        edVolta.setCd_Tipo_Instrucao(res.getString("CD_Tipo_Instrucao"));
        edVolta.setNm_Tipo_Instrucao(res.getString("Nm_Tipo_Instrucao"));
        edVolta.setCd_Carteira(res.getString("CD_Carteira"));
        edVolta.setDm_Situacao(res.getString("DM_Situacao"));
//        edVolta.setTx_Observacao(res.getString("Tx_Observacao"));
        edVolta.setNR_Duplicata(res.getLong("nr_duplicata"));

      }

    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao recuperar registro");
      excecoes.setMetodo("getByRecord(Instrucao_DuplicataED)");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return edVolta;
  }

  public void geraRelatorio(Instrucao_DuplicataED ed)throws Excecoes{

//    String sql = null;
//
//    Instrucao_DuplicataED edVolta = new Instrucao_DuplicataED();
//
//    try{
//
//      sql = "select * from Instrucao_Duplicatas where oid_Instrucao_Duplicata > 0";
//
//      if (ed.getCD_Instrucao_Duplicata() != null && !ed.getCD_Instrucao_Duplicata().equals("")){
//        sql += " and CD_Instrucao_Duplicata = '" + ed.getCD_Instrucao_Duplicata() + "'";
//      }
//      if (ed.getCD_Remessa() != null && !ed.getCD_Remessa().equals("")){
//        sql += " and CD_Remessa = '" + ed.getCD_Remessa() + "'";
//      }
//
//      ResultSet res = null;
//      res = this.executasql.executarConsulta(sql);
//
//      Instrucao_DuplicataRL Instrucao_Duplicata_rl = new Instrucao_DuplicataRL();
//      Instrucao_Duplicata_rl.geraRelatEstoque(res);
//    }
//    catch (Excecoes e){
//      throw e;
//    }
//    catch(Exception exc){
//      Excecoes exce = new Excecoes();
//      exce.setExc(exc);
//      exce.setMensagem("Erro no método listar");
//      exce.setClasse(this.getClass().getName());
//      exce.setMetodo("geraRelatorio(Instrucao_DuplicataED ed)");
//    }
//
  }

}