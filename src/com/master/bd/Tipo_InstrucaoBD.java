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

import com.master.ed.Tipo_InstrucaoED;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;
import com.master.util.SeparaEndereco;

public class Tipo_InstrucaoBD {

  private ExecutaSQL executasql;

  public Tipo_InstrucaoBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

/********************************************************
 *
 *******************************************************/
  public Tipo_InstrucaoED inclui(Tipo_InstrucaoED ed) throws Excecoes{

    String sql = null;
    long valOid = 0;

    Tipo_InstrucaoED tipo_InstrucaoED = new Tipo_InstrucaoED();
    SeparaEndereco SeparaEndereco = new SeparaEndereco();

    try{

      ResultSet rs = executasql.executarConsulta("select max(oid_Tipo_Instrucao) as result from Tipos_Instrucoes");

      //pega proximo valor da chave
      while (rs.next()) valOid = rs.getInt("result");

      sql = " insert into Tipos_Instrucoes (OID_TIPO_INSTRUCAO, CD_TIPO_INSTRUCAO, CD_CONTA_CONTABIL, NM_TIPO_INSTRUCAO, DM_ATUALIZA_SALDO, ";
      sql += " DM_ALTERA_TITULO,DT_STAMP,USUARIO_STAMP,DM_STAMP,DM_GERA_MOVIMENTO,DM_GERA_OCORRENCIA,  CD_VALOR, DM_Diario_Razao, DM_GERA_INSTRUCAO) values ";
      sql += "(" + ++valOid + ",'" + ed.getCd_Tipo_Instrucao() + "','" + ed.getCd_Conta_Contabil() + "','" + ed.getNm_Tipo_Instrucao() + "','" + ed.getDM_Atualiza_Saldo() + "'," ;
      sql += "'" + ed.getDm_Altera_Titulo() + "','" + ed.getDt_stamp()+ "',";
      sql += "'" + ed.getUsuario_Stamp() +    "','" + ed.getDm_Stamp() + "',";
      sql += "'" + ed.getDm_Gera_Movimento() + "','" + ed.getDm_Gera_Ocorrencia() + "',";
      sql += "'" + ed.getCd_Valor() + "',";
      sql += "'" + ed.getDM_Diario_Razao() + "',";
      sql += "'" + ed.getDm_Gera_Instrucao() + "')";

      // System.out.println(sql);

      //invoca o metodo executarupdate do objeto
      //executasql. que eh uma referencia ao
      //objeto ExecutSQL, que contem a conexao ativa
      int i = executasql.executarUpdate(sql);
      tipo_InstrucaoED.setOid_Tipo_Instrucao(new Integer(Long.toString(valOid)));
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir Tipo Instrução");
      excecoes.setMetodo("inclui(Tipo_InstrucaoED)");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return tipo_InstrucaoED;

  }

/********************************************************
 *
 *******************************************************/
  public void altera(Tipo_InstrucaoED ed) throws Excecoes{
    String sql = null;

    try{
      sql = " update Tipos_Instrucoes set ";

      if (ed.getCd_Tipo_Instrucao() != null)
        sql += " CD_TIPO_INSTRUCAO = '" + ed.getCd_Tipo_Instrucao() + "', ";

      if (ed.getCd_Conta_Contabil() != null)
        sql += " CD_CONTA_CONTABIL = '" + ed.getCd_Conta_Contabil() + "', ";


      if (ed.getNm_Tipo_Instrucao() != null)
        sql += " NM_TIPO_INSTRUCAO = '" + ed.getNm_Tipo_Instrucao() + "', ";

      if (ed.getDM_Atualiza_Saldo() != null)
        sql += " DM_Atualiza_Saldo = '" + ed.getDM_Atualiza_Saldo() + "', ";

      if (ed.getDm_Altera_Titulo() != null)
        sql += " DM_ALTERA_TITULO = '" + ed.getDm_Altera_Titulo() + "', ";

      if (ed.getDm_Gera_Movimento() != null)
        sql += " DM_GERA_MOVIMENTO = '" + ed.getDm_Gera_Movimento() + "', ";

      if (ed.getCd_Valor() != null)
        sql += " Cd_Valor = '" + ed.getCd_Valor() + "', ";

      if (ed.getDm_Gera_Ocorrencia() != null)
        sql += " DM_GERA_OCORRENCIA = '" + ed.getDm_Gera_Ocorrencia() + "', ";

      if (ed.getDm_Gera_Instrucao() != null)
        sql += " DM_GERA_INSTRUCAO = '" + ed.getDm_Gera_Instrucao() + "', ";

      if (ed.getDM_Diario_Razao() != null)
          sql += " DM_Diario_Razao = '" + ed.getDM_Diario_Razao() + "', ";

      sql += " DT_STAMP = '" + ed.getDt_stamp() + "', ";
      sql += " USUARIO_STAMP = '" + ed.getUsuario_Stamp() + "', ";
      sql += " DM_STAMP = '" + ed.getDm_Stamp() + "' ";
      sql += " where oid_tipo_instrucao = " + ed.getOid_Tipo_Instrucao();


      int i = executasql.executarUpdate(sql);

    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar Tipo Instrução");
      excecoes.setMetodo("altera(Tipo_InstrucaoED");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

/********************************************************
 *
 *******************************************************/
  public Tipo_InstrucaoED getByRecord(Tipo_InstrucaoED ed)throws Excecoes{

    String sql = null;

    Tipo_InstrucaoED edVolta = new Tipo_InstrucaoED();

    try{



      sql = " select * from tipos_instrucoes where oid_tipo_instrucao > 0";

      if (ed.getOid_Tipo_Instrucao() != null &&
          !String.valueOf(ed.getOid_Tipo_Instrucao()).equals("0")){
        sql += " and OID_Tipo_Instrucao = " + ed.getOid_Tipo_Instrucao();
      }
      if (ed.getCd_Tipo_Instrucao() != null && !ed.getCd_Tipo_Instrucao().equals("") &&
          !String.valueOf(ed.getOid_Tipo_Instrucao()).equals("0")){
        sql += " and cd_Tipo_Instrucao = '" + ed.getCd_Tipo_Instrucao() + "'";
      }

//    // //// System.out.println(sql);


      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);
      while (res.next()){

        edVolta = new Tipo_InstrucaoED();

        edVolta.setOid_Tipo_Instrucao(new Integer(res.getString("oid_Tipo_Instrucao")));
        edVolta.setNm_Tipo_Instrucao(res.getString("nm_tipo_instrucao"));
        edVolta.setDM_Atualiza_Saldo(res.getString("DM_Atualiza_Saldo"));
        edVolta.setCd_Tipo_Instrucao(res.getString("cd_tipo_instrucao"));
        edVolta.setCd_Conta_Contabil("");
        if (res.getString("cd_Conta_Contabil")!=null)
        	edVolta.setCd_Conta_Contabil(SeparaEndereco.corrigeNumero(res.getString("cd_Conta_Contabil")));
        edVolta.setDm_Altera_Titulo(res.getString("dm_altera_titulo"));
        edVolta.setDm_Gera_Instrucao(res.getString("dm_gera_instrucao"));
        edVolta.setDm_Gera_Movimento(res.getString("dm_gera_movimento"));
        edVolta.setDm_Gera_Ocorrencia(res.getString("dm_gera_ocorrencia"));
        edVolta.setCd_Valor(res.getString("Cd_Valor"));

        edVolta.setDM_Diario_Razao(res.getString("DM_Diario_Razao"));
// System.out.println(res.getString("Cd_Valor"));

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
  public void deleta(Tipo_InstrucaoED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from Tipos_Instrucoes WHERE oid_Tipo_Instrucao = ";
      sql +=  ed.getOid_Tipo_Instrucao() ;

      int i = executasql.executarUpdate(sql);

    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao excluir Tipo de Instrução");
      excecoes.setMetodo("deleta(Tipo_InstrucaoED)");
      excecoes.setExc(exc);
      throw excecoes;
    }

  }

/********************************************************
 *
 *******************************************************/
  public ArrayList lista(Tipo_InstrucaoED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();

    try{
      sql = "select * from Tipos_Instrucoes where oid_tipo_instrucao > 0";

      if (ed.getNm_Tipo_Instrucao() != null && !ed.getNm_Tipo_Instrucao().equals("")){
        sql += " and nm_tipo_instrucao LIKE '" + ed.getNm_Tipo_Instrucao() + "%'";
      }
      if (ed.getCd_Tipo_Instrucao() != null && !ed.getCd_Tipo_Instrucao().equals("")){
        sql += " and cd_Tipo_Instrucao = '" + ed.getCd_Tipo_Instrucao() + "'";
      }
      if (ed.getDM_Diario_Razao() != null && !ed.getDM_Diario_Razao().equals("") && !ed.getDM_Diario_Razao().equals("T")){
          sql += " and dm_diario_razao = '" + ed.getDM_Diario_Razao() + "'";
        }
      sql += " ORDER BY nm_tipo_instrucao ";
      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      //popula
      while (res.next()){
        Tipo_InstrucaoED edVolta = new Tipo_InstrucaoED();
        edVolta.setOid_Tipo_Instrucao(new Integer(res.getString("oid_tipo_instrucao")));
        edVolta.setCd_Tipo_Instrucao(res.getString("cd_tipo_instrucao"));
        edVolta.setNm_Tipo_Instrucao(res.getString("nm_tipo_instrucao"));
        edVolta.setDm_Gera_Movimento(res.getString("DM_Gera_Movimento"));
        edVolta.setDM_Diario_Razao(res.getString("DM_Diario_Razao"));
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
  public void geraRelatorio(Tipo_InstrucaoED ed)throws Excecoes{

//    String sql = null;
//
//    Tipo_InstrucaoED edVolta = new Tipo_InstrucaoED();
//
//    try{
//
//      sql = "select * from Tipo_Instrucaos where oid_Tipo_Instrucao > 0";
//
//      if (ed.getCD_Tipo_Instrucao() != null && !ed.getCD_Tipo_Instrucao().equals("")){
//        sql += " and CD_Tipo_Instrucao = '" + ed.getCD_Tipo_Instrucao() + "'";
//      }
//      if (ed.getCD_Remessa() != null && !ed.getCD_Remessa().equals("")){
//        sql += " and CD_Remessa = '" + ed.getCD_Remessa() + "'";
//      }
//
//      ResultSet res = null;
//      res = this.executasql.executarConsulta(sql);
//
//      Tipo_InstrucaoRL Tipo_Instrucao_rl = new Tipo_InstrucaoRL();
//      Tipo_Instrucao_rl.geraRelatEstoque(res);
//    }
//    catch (Excecoes e){
//      throw e;
//    }
//    catch(Exception exc){
//      Excecoes exce = new Excecoes();
//      exce.setExc(exc);
//      exce.setMensagem("Erro no método listar");
//      exce.setClasse(this.getClass().getName());
//      exce.setMetodo("geraRelatorio(Tipo_InstrucaoED ed)");
//    }
//
  }

}