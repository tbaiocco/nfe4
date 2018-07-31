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

import com.master.ed.HistoricoED;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;


public class HistoricoBD {

  private ExecutaSQL executasql;

  public HistoricoBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

/********************************************************
 *
 *******************************************************/
  public HistoricoED inclui(HistoricoED ed) throws Excecoes{

    String sql = null;
    long valOid = 0;
    HistoricoED historicoED = new HistoricoED();

    try{

      ResultSet rs = executasql.executarConsulta("select max(oid_Historico) as result from Historicos");

      //pega proximo valor da chave
      while (rs.next()) valOid = rs.getInt("result");

      sql = " insert into Historicos (OID_Historico, CD_HISTORICO, NM_HISTORICO, DM_LANCA_FORNECEDOR, ";
      sql += " DM_LANCA_DATA, DM_LANCA_COMPLEMENTO,DT_STAMP, USUARIO_STAMP, DM_STAMP) values ";
      sql += "(" + ++valOid + ",'" + ed.getCd_Historico() + "','" + ed.getNm_Historico() + "'," ;
      sql += "'" + ed.getDm_Lanca_Fornecedor() + "','" + ed.getDm_Lanca_Data() + "',";
      sql += "'" + ed.getDm_Lanca_Complemento() +    "','" + ed.getDt_stamp() + "',";
      sql += "'" + ed.getUsuario_Stamp() + "','" + ed.getDm_Stamp() + "')";

      //invoca o metodo executarupdate do objeto
      //executasql. que eh uma referencia ao
      //objeto ExecutSQL, que contem a conexao ativa
      int i = executasql.executarUpdate(sql);
      historicoED.setOid_Historico(new Integer(Long.toString(valOid)));
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir Histórico");
      excecoes.setMetodo("inclui(HistoricoED)");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return historicoED;
  }

/********************************************************
 *
 *******************************************************/
  public void altera(HistoricoED ed) throws Excecoes{

    String sql = null;

    try{

      sql = " update Historicos set ";
      sql += " CD_HISTORICO = '" + ed.getCd_Historico() + "', ";
      sql += " NM_HISTORICO = '" + ed.getNm_Historico() + "', ";

      if (ed.getDm_Lanca_Fornecedor() != null)
        sql += " DM_LANCA_FORNECEDOR = '" + ed.getDm_Lanca_Fornecedor() + "', ";
      else
        sql += " DM_LANCA_FORNECEDOR = null,";

      if (ed.getDm_Lanca_Data() != null)
        sql += " DM_LANCA_DATA = '" + ed.getDm_Lanca_Data() + "', ";
      else
        sql += " DM_LANCA_DATA = null,";

      if (ed.getDm_Lanca_Complemento() != null)
        sql += " DM_LANCA_COMPLEMENTO = '" + ed.getDm_Lanca_Complemento() + "', ";
      else
        sql += " DM_LANCA_COMPLEMENTO = null,";

      sql += " DT_STAMP = '" + ed.getDt_stamp() + "', ";
      sql += " USUARIO_STAMP = '" + ed.getUsuario_Stamp() + "', ";
      sql += " DM_STAMP = '" + ed.getDm_Stamp() + "' ";
      sql += " where oid_Historico = " + ed.getOid_Historico();

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar Históricos de Caixa");
      excecoes.setMetodo("altera(HistoricoED)");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

/********************************************************
 *
 *******************************************************/
  public void deleta(HistoricoED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from Historicos WHERE oid_Historico = ";
      sql += ed.getOid_Historico();

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao excluir Histórico de Caixa");
      excecoes.setMetodo("deleta(HistoricoED)");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

/********************************************************
 *
 *******************************************************/
  public HistoricoED getByRecord(HistoricoED ed)throws Excecoes{

    String sql = null;

    HistoricoED edVolta = new HistoricoED();

    try{

      sql = " select * from Historicos where oid_Historico > 0";

      if (ed.getOid_Historico() != null && !ed.getOid_Historico().equals("") &&
          !String.valueOf(ed.getOid_Historico()).equals("0")){
        sql += " and OID_Historico = " + ed.getOid_Historico();
      }
      if (ed.getCd_Historico() != null &&
          !String.valueOf(ed.getCd_Historico()).equals("0")){
        sql += " and cd_historico = '" + ed.getCd_Historico() + "'";
      }

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);
      while (res.next()){
        edVolta = new HistoricoED();
        edVolta.setOid_Historico(new Integer(res.getString("oid_Historico")));
        edVolta.setCd_Historico(res.getString("cd_historico"));
        edVolta.setNm_Historico(res.getString("nm_historico"));
        edVolta.setDm_Lanca_Complemento(res.getString("dm_lanca_complemento"));
        edVolta.setDm_Lanca_Data(res.getString("dm_lanca_data"));
        edVolta.setDm_Lanca_Fornecedor(res.getString("dm_lanca_fornecedor"));
      }

    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao recuperar registro");
      excecoes.setMetodo("getByRecord(Tipo_InstrucaoED)");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return edVolta;
  }


/********************************************************
 *
 *******************************************************/
  public ArrayList lista(HistoricoED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();

    try{
      sql = "select * from Historicos where oid_Historico > 0";

      if (ed.getCd_Historico() != null && !ed.getCd_Historico().equals("")){
        sql += " and CD_Historico = '" + ed.getCd_Historico() + "'";
      } else {
    	  if (ed.getNm_Historico() != null && !ed.getNm_Historico().equals("")){
    		  sql += " and NM_Historico LIKE '" + ed.getNm_Historico() + "%'";
    	  }
      }
      sql += " ORDER BY CD_Historico";


      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      //popula
      while (res.next()){
        HistoricoED edVolta = new HistoricoED();
        edVolta.setOid_Historico(new Integer(res.getString("oid_Historico")));
        edVolta.setCd_Historico(res.getString("cd_historico"));
        edVolta.setNm_Historico(res.getString("nm_historico"));

        list.add(edVolta);
      }
    }
    catch(Exception exc){
      Excecoes exce = new Excecoes();
      exce.setExc(exc);
      exce.setMensagem("Erro no método listar");
    }

    return list;
  }

/********************************************************
 *
 *******************************************************/
  public void geraRelatorio(HistoricoED ed)throws Excecoes{

//    String sql = null;
//
//    HistoricoED edVolta = new HistoricoED();
//
//    try{
//
//      sql = "select * from Historicos where oid_Historico > 0";
//
//      if (ed.getCD_Historico() != null && !ed.getCD_Historico().equals("")){
//        sql += " and CD_Historico = '" + ed.getCD_Historico() + "'";
//      }
//      if (ed.getCD_Remessa() != null && !ed.getCD_Remessa().equals("")){
//        sql += " and CD_Remessa = '" + ed.getCD_Remessa() + "'";
//      }
//
//      ResultSet res = null;
//      res = this.executasql.executarConsulta(sql);
//
//      HistoricoRL Historico_rl = new HistoricoRL();
//      Historico_rl.geraRelatEstoque(res);
//    }
//    catch (Excecoes e){
//      throw e;
//    }
//    catch(Exception exc){
//      Excecoes exce = new Excecoes();
//      exce.setExc(exc);
//      exce.setMensagem("Erro no método listar");
//      exce.setClasse(this.getClass().getName());
//      exce.setMetodo("geraRelatorio(HistoricoED ed)");
//    }
//
  }

}