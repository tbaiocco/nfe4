package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.Tipo_EventoED;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

public class Tipo_EventoBD {

  private ExecutaSQL executasql;

  public Tipo_EventoBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public Tipo_EventoED inclui(Tipo_EventoED ed) throws Excecoes{

    String sql = null;
    long valOid = 0;
    Tipo_EventoED Tipo_EventoED = new Tipo_EventoED();
    Data data = new Data();

    try{


      ResultSet rs = executasql.executarConsulta("select max(oid_Tipo_Evento) as result from Tipos_Eventos");

      //pega proximo valor da chave
      while (rs.next()) valOid = rs.getInt("result");

      sql = "insert into Tipos_Eventos ( ";
      sql+= " OID_Tipo_Evento,";
      sql+= " OID_Historico, " ;
      sql+= " OID_Conta, " ;
      sql+= " OID_Conta_Debito, " ;
      sql+= " OID_Centro_Custo, " ;
      sql+= " OID_Natureza_Operacao, " ;
      sql+= " OID_Pessoa, " ;
      sql+= " CD_Tipo_Evento, ";
      sql+= " NM_Tipo_Evento, ";
      sql+= " CD_Conta_Credito, ";
      sql+= " CD_Conta_Debito, ";
      sql+= " NM_Arquivo_Saida, ";
      sql+= " DT_STAMP, ";
      sql+= " USUARIO_STAMP, ";
      sql+= " DM_STAMP ) ";
      sql+= " values (";
      sql+= ++valOid + ",";
      sql+= ed.getOid_Historico() + ",";
      sql+= ed.getOid_Conta() + ",";
      sql+= ed.getOid_Conta_Credito() + ",";
      sql+= ed.getOid_Centro_Custo() + ",";
      sql+= ed.getOid_Natureza_Operacao() + ",'";
      sql+= ed.getOid_Pessoa() + "','";
      sql+= ed.getCd_Tipo_Evento() + "','" ;
      sql+= ed.getNm_Tipo_Evento() + "','" ;
      sql+= ed.getCd_Conta_Credito() + "','" ;
      sql+= ed.getCd_Conta_Debito() + "','" ;
      sql+= ed.getNm_Arquivo_Saida()+ "','" ;
      sql+= data.getDataDMY() + "','" ;
      sql+= ed.getUsuario_Stamp() + "','" ;
      sql+= ed.getDm_Stamp() + "')";

     // System.err.println(sql);

      int i = executasql.executarUpdate(sql);
      Tipo_EventoED.setOid_Tipo_Evento(new Integer(Long.toString(valOid)));
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir Tipo_Evento");
      excecoes.setMetodo("inclui");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return Tipo_EventoED;

  }

  public void altera(Tipo_EventoED ed) throws Excecoes{

    String sql = null;

    try{

      sql = " update Tipos_Eventos set ";
      sql += " NM_Tipo_Evento = '" + ed.getNm_Tipo_Evento() + "', ";
      sql += " CD_Tipo_Evento = '" + ed.getCd_Tipo_Evento() + "', ";
      sql += " CD_Conta_Debito = '" + ed.getCd_Conta_Debito() + "', ";
      sql += " CD_Conta_Credito = '" + ed.getCd_Conta_Credito() + "', ";
      sql += " NM_Arquivo_Saida = '" + ed.getNm_Arquivo_Saida() + "', ";
      sql += " oid_Conta = " + ed.getOid_Conta_Credito() + " , ";
      sql += " oid_Conta_debito = " + ed.getOid_Conta() + " , ";
      sql += " oid_Centro_Custo = " + ed.getCd_Conta_Debito() + " , ";
      sql += " oid_Natureza_Operacao = " + ed.getOid_Natureza_Operacao() + " , ";
      sql += " oid_Pessoa = '" + ed.getOid_Pessoa() + "', ";
      sql += " DT_STAMP = '" + ed.getDt_stamp() + "', ";
      sql += " USUARIO_STAMP = '" + ed.getUsuario_Stamp() + "', ";
      sql += " DM_STAMP = '" + ed.getDm_Stamp() + "', ";
      sql += " OID_Historico = " + ed.getOid_Historico() + " ";
      sql += " where oid_Tipo_Evento = " + ed.getOid_Tipo_Evento();

      // System.err.println(sql);

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar dados de Tipo_Evento");
      excecoes.setMetodo("altera(Tipo_EventoED)");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void deleta(Tipo_EventoED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from Tipos_Eventos WHERE oid_Tipo_Evento = ";
      sql += "(" + ed.getOid_Tipo_Evento() + ")";

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao deletar Tipo_Evento");
      excecoes.setMetodo("deleta(Tipo_EventoED)");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public ArrayList lista(Tipo_EventoED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();

    try{
      sql = " select * from Tipos_Eventos, historicos_caixas where "+
            " Tipos_Eventos.oid_Historico = historicos_caixas.oid_Historico  ";

      if (ed.getOid_Tipo_Evento() != null && !ed.getOid_Tipo_Evento().equals("")){
        sql += " and Tipos_Eventos.oid_Tipo_Evento = " + ed.getOid_Tipo_Evento();
      }
      if (ed.getNm_Tipo_Evento() != null && !ed.getNm_Tipo_Evento().equals("")){
        sql += " and Tipos_Eventos.nm_Tipo_Evento = '" + ed.getNm_Tipo_Evento() + "'";
      }

      //// System.err.println(sql);

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      //popula
      while (res.next()){
        Tipo_EventoED edVolta = new Tipo_EventoED();
        edVolta.setOid_Tipo_Evento(new Integer(res.getString("oid_Tipo_Evento")));
        edVolta.setCd_Tipo_Evento(res.getString("cd_Tipo_Evento"));
        edVolta.setNm_Tipo_Evento(res.getString("nm_Tipo_Evento"));
        edVolta.setCd_Conta_Debito(res.getString("cd_conta_debito"));
        edVolta.setCd_Conta_Credito(res.getString("cd_conta_credito"));
        edVolta.setNm_Arquivo_Saida(res.getString("nm_Arquivo_Saida"));
        edVolta.setCd_Historico(res.getString("cd_historico"));
        edVolta.setNm_Historico(res.getString("nm_historico"));
        edVolta.setOid_Historico(new Integer(res.getString("oid_Historico")));

        list.add(edVolta);
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao listar Tipo_Eventos - SQL="+sql);
      excecoes.setMetodo("lista(Tipo_EventosED)");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return list;
  }

  public Tipo_EventoED getByRecord(Tipo_EventoED ed)throws Excecoes{

    String sql = null;

    Tipo_EventoED edVolta = new Tipo_EventoED();

    try{

      sql = " select * from Tipos_Eventos, historicos_caixas where "+
            " Tipos_Eventos.oid_Historico = historicos_caixas.oid_Historico  ";

      if (ed.getOid_Tipo_Evento() != null &&
          !String.valueOf(ed.getOid_Tipo_Evento()).equals("0")){
        sql += " and OID_Tipo_Evento = " + ed.getOid_Tipo_Evento();
      }

      if (ed.getCd_Tipo_Evento() != null &&
          !String.valueOf(ed.getCd_Tipo_Evento()).equals("")){
        sql += " and CD_Tipo_Evento = '" + ed.getCd_Tipo_Evento() + "'";
      }


      // System.err.println(sql);

      ResultSet res = null;

      //// System.err.println(sql);

      res = this.executasql.executarConsulta(sql);
      while (res.next()){
        edVolta = new Tipo_EventoED();
        edVolta.setOid_Tipo_Evento(new Integer(res.getString("oid_Tipo_Evento")));
        edVolta.setCd_Tipo_Evento(res.getString("cd_Tipo_Evento"));
        edVolta.setNm_Tipo_Evento(res.getString("nm_Tipo_Evento"));
        edVolta.setCd_Conta_Debito(res.getString("cd_conta_debito"));
        edVolta.setCd_Conta_Credito(res.getString("cd_conta_credito"));
        edVolta.setNm_Arquivo_Saida(res.getString("nm_Arquivo_Saida"));
        edVolta.setCd_Historico(res.getString("cd_historico"));
        edVolta.setNm_Historico(res.getString("nm_historico"));
        edVolta.setOid_Historico(new Integer(res.getString("oid_Historico")));

        edVolta.setOid_Conta(new Integer(res.getString("oid_conta")));
        edVolta.setOid_Conta_Credito(new Integer(res.getString("oid_conta_debito")));
        edVolta.setOid_Centro_Custo(new Integer(res.getString("oid_centro_custo")));
        edVolta.setOid_Natureza_Operacao(new Integer(res.getString("oid_natureza_operacao")));
        edVolta.setOid_Pessoa(res.getString("oid_pessoa"));

      }

    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao recuperar registro");
      excecoes.setMetodo("getByRecord(Tipo_EventoED)");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return edVolta;
  }

  public void geraRelatorio(Tipo_EventoED ed)throws Excecoes{

//    String sql = null;
//
//    Tipo_EventoED edVolta = new Tipo_EventoED();
//
//    try{
//
//      sql = "select * from Tipo_Eventos where oid_Tipo_Evento > 0";
//
//      if (ed.getCD_Tipo_Evento() != null && !ed.getCD_Tipo_Evento().equals("")){
//        sql += " and CD_Tipo_Evento = '" + ed.getCD_Tipo_Evento() + "'";
//      }
//      if (ed.getCD_Remessa() != null && !ed.getCD_Remessa().equals("")){
//        sql += " and CD_Remessa = '" + ed.getCD_Remessa() + "'";
//      }
//
//      ResultSet res = null;
//      res = this.executasql.executarConsulta(sql);
//
//      Tipo_EventoRL Tipo_Evento_rl = new Tipo_EventoRL();
//      Tipo_Evento_rl.geraRelatEstoque(res);
//    }
//    catch (Excecoes e){
//      throw e;
//    }
//    catch(Exception exc){
//      Excecoes exce = new Excecoes();
//      exce.setExc(exc);
//      exce.setMensagem("Erro no método listar");
//      exce.setClasse(this.getClass().getName());
//      exce.setMetodo("geraRelatorio(Tipo_EventoED ed)");
//    }
//
  }

}