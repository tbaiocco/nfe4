package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.EventoED;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

public class EventoBD {

  private ExecutaSQL executasql;

  public EventoBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public EventoED inclui(EventoED ed) throws Excecoes{

    String sql = null;
    long valOid = 0;
    String chave = null;

    EventoED manED = new EventoED();
    try{


      ResultSet rs = executasql.executarConsulta("select max(oid_Evento_contabil) as result from EVENTOS_CONTABEIS");

      //pega proximo valor da chave
      while (rs.next()) valOid = rs.getInt("result");
      valOid = valOid+1;

      ed.setOid_evento_contabil(valOid);

      sql =  " insert into EVENTOS_CONTABEIS (oid_evento_contabil, nm_evento_contabil, oid_conta, dm_tipo_contabilizacao, cd_valor, dm_acumula, oid_sugestao_contabil, oid_Historico) values ";
      sql += "(" + ed.getOid_evento_contabil() + ",'" + ed.getNm_evento_contabil() +"',"+ed.getOid_conta()+",'"+ed.getDm_tipo_contabilizacao()+"','"+ed.getCd_valor()+"','"+ed.getDm_acumula()+"',"+ed.getOid_sugestao_contabil()+","+ed.getOid_Historico()+")";
// System.out.println(sql);
      int i = executasql.executarUpdate(sql);
      manED.setOid_evento_contabil(ed.getOid_evento_contabil());

    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir Evento");
      excecoes.setMetodo("inclui");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return manED;

  }

  public void altera(EventoED ed) throws Excecoes{

    String sql = null;

    try{
      sql = "UPDATE EVENTOS_CONTABEIS SET "+
            "nm_evento_contabil='"+ed.getNm_evento_contabil()+"', "+
            "oid_conta="+ed.getOid_conta()+", "+
            "dm_tipo_contabilizacao='"+ed.getDm_tipo_contabilizacao()+"', "+
            "cd_valor='"+ed.getCd_valor()+"', "+
            "dm_acumula='"+ed.getDm_acumula()+"',"+
            "oid_Historico="+ed.getOid_Historico()+" "+
            "where oid_sugestao_contabil = " + ed.getOid_sugestao_contabil() +
            "and oid_evento_contabil = " + ed.getOid_evento_contabil();
      //////// System.out.println(sql);
      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar Evento");
      excecoes.setMetodo("alterar");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void deleta(EventoED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from EVENTOS_CONTABEIS WHERE oid_sugestao_contabil = " + ed.getOid_sugestao_contabil()+" and oid_evento_contabil ="+ed.getOid_evento_contabil();
//////// System.out.println(sql);
      int i = executasql.executarUpdate(sql);

    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao excluir Evento");
      excecoes.setMetodo("excluir");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }



  public ArrayList lista(EventoED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();

    try{

      sql =  "SELECT * FROM EVENTOS_CONTABEIS, HISTORICOS WHERE EVENTOS_CONTABEIS.OID_HISTORICO = HISTORICOS.OID_HISTORICO AND oid_sugestao_contabil = " + ed.getOid_sugestao_contabil();
      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      //popula
      while (res.next()){
        EventoED edVolta = new EventoED();
        edVolta.setOid_evento_contabil(res.getLong("oid_evento_contabil"));
        edVolta.setNm_evento_contabil(res.getString("nm_evento_contabil"));
        edVolta.setNm_historico(res.getString("nm_historico"));
        edVolta.setCd_historico(res.getString("cd_historico"));
        edVolta.setOid_conta(res.getLong("oid_conta"));
        edVolta.setDm_tipo_contabilizacao(res.getString("dm_tipo_contabilizacao"));
        edVolta.setCd_valor(res.getString("cd_valor"));
        edVolta.setDm_acumula(res.getString("dm_acumula"));
        edVolta.setOid_Historico(res.getLong("oid_Historico"));
        edVolta.setOid_sugestao_contabil(res.getLong("oid_sugestao_contabil"));
        list.add(edVolta);
      }
    }
    catch(Exception exc){
        exc.printStackTrace();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao listar Evento");
      excecoes.setMetodo("lista");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return list;
  }

  public EventoED getByRecord(EventoED ed)throws Excecoes{

    String sql = null;
    EventoED edVolta = new EventoED();
    try{
      //////// System.out.println(ed.getOid_evento_contabil());
      sql = "SELECT * FROM EVENTOS_CONTABEIS, HISTORICOS WHERE EVENTOS_CONTABEIS.OID_HISTORICO = HISTORICOS.OID_HISTORICO AND oid_sugestao_contabil = " + ed.getOid_sugestao_contabil();
//////// System.out.println(sql);
      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      //popula
      while (res.next()){
        edVolta.setNm_historico(res.getString("nm_historico"));
        edVolta.setCd_historico(res.getString("cd_historico"));

        edVolta.setOid_evento_contabil(res.getLong("oid_evento_contabil"));
        edVolta.setNm_evento_contabil(res.getString("nm_evento_contabil"));
        edVolta.setOid_conta(res.getLong("oid_conta"));
        edVolta.setDm_tipo_contabilizacao(res.getString("dm_tipo_contabilizacao"));
        edVolta.setCd_valor(res.getString("cd_valor"));
        edVolta.setDm_acumula(res.getString("dm_acumula"));
        edVolta.setOid_Historico(res.getLong("oid_Historico"));
        edVolta.setOid_sugestao_contabil(res.getLong("oid_sugestao_contabil"));

      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao selecionar Evento");
      excecoes.setMetodo("Seleção");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return edVolta;
  }

    public EventoED getByOid(String Oid)throws Excecoes{

    String sql = null;
    EventoED edVolta = new EventoED();
    try{
      sql = "SELECT * FROM EVENTOS_CONTABEIS, HISTORICOS WHERE EVENTOS_CONTABEIS.OID_HISTORICO = HISTORICOS.OID_HISTORICO AND oid_evento_contabil = " + Oid;
      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      //popula
      while (res.next()){

        edVolta.setNm_historico(res.getString("nm_historico"));
        edVolta.setCd_historico(res.getString("cd_historico"));

        edVolta.setOid_evento_contabil(res.getLong("oid_evento_contabil"));
        edVolta.setNm_evento_contabil(res.getString("nm_evento_contabil"));
        edVolta.setOid_conta(res.getLong("oid_conta"));
        edVolta.setDm_tipo_contabilizacao(res.getString("dm_tipo_contabilizacao"));
        edVolta.setCd_valor(res.getString("cd_valor"));
        edVolta.setDm_acumula(res.getString("dm_acumula"));
        edVolta.setOid_Historico(res.getLong("oid_Historico"));
        edVolta.setOid_sugestao_contabil(res.getLong("oid_sugestao_contabil"));
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao selecionar Evento");
      excecoes.setMetodo("Seleção");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return edVolta;
  }



  public ArrayList getByNM_Evento(EventoED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();

    try{
      ResultSet res = null;
      //////// System.out.println(sql);
      res = this.executasql.executarConsulta(sql);

      //popula
      while (res.next()){
        EventoED edVolta = new EventoED();
        edVolta.setOid_evento_contabil(res.getLong("oid_evento_contabil"));
        edVolta.setNm_evento_contabil(res.getString("nm_evento_contabil"));
        edVolta.setOid_conta(res.getLong("oid_conta"));
        edVolta.setDm_tipo_contabilizacao(res.getString("dm_tipo_contabilizacao"));
        edVolta.setCd_valor(res.getString("cd_valor"));
        edVolta.setDm_acumula(res.getString("dm_acumula"));
        edVolta.setOid_Historico(res.getLong("oid_Historico"));
        edVolta.setOid_sugestao_contabil(res.getLong("oid_sugestao_contabil"));
        list.add(edVolta);
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao selecionar Evento");
      excecoes.setMetodo("Seleção");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return list;
  }




}