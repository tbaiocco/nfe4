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

import com.master.ed.Ocorrencia_BancariaED;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;


public class Ocorrencia_BancariaBD {

  private ExecutaSQL executasql;

  public Ocorrencia_BancariaBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public Ocorrencia_BancariaED inclui(Ocorrencia_BancariaED ed) throws Excecoes{

    String sql = null;
    long valOid = 0;
    Ocorrencia_BancariaED ocorrenciaBancED = new Ocorrencia_BancariaED();

    try{

      ResultSet rs = executasql.executarConsulta("select max(oid_Ocorrencia_Bancaria) as result from Ocorrencias_Bancarias");

      //pega proximo valor da chave
      while (rs.next()) valOid = rs.getInt("result");

      sql = " insert into Ocorrencias_Bancarias ";
      sql += "(OID_OCORRENCIA_BANCARIA,DM_TIPO_OCORRENCIA,CD_OCORRENCIA,TX_DESCRICAO,";
      sql += "DT_STAMP,USUARIO_STAMP,DM_STAMP,VL_MOVIMENTO,OID_TIPO_INSTRUCAO,OID_PESSOA) values";
      sql += "(" + ++valOid + "," + ed.getDm_Tipo_Ocorrencia() + ",'" + ed.getCd_Ocorrencia() + "'," ;

      if (ed.getTx_Descricao() != null && !ed.getTx_Descricao().equals(""))
        sql += "'" + ed.getTx_Descricao() + "',";
      else
        sql += "null,";

      sql += "'" + ed.getDt_stamp() + "','" + ed.getUsuario_Stamp() + "',";
      sql += "'" + ed.getDm_Stamp() + "',";

      if (ed.getVl_Movimento() != null && !ed.getVl_Movimento().equals(""))
        sql += ed.getVl_Movimento() + ",";
      else
        sql += "null,";

      sql += ed.getOid_Tipo_Instrucao() + ",'" + ed.getOid_Pessoa() +  "')";

      //invoca o metodo executarupdate do objeto
      //executasql. que eh uma referencia ao
      //objeto ExecutSQL, que contem a conexao ativa
//      // System.err.println(sql);
      int i = executasql.executarUpdate(sql);
      ocorrenciaBancED.setOid_Ocorrencia_Bancaria(new Integer(Long.toString(valOid)));
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir Ocorrência Bancária");
      excecoes.setMetodo("inclui(Ocorrencia_BancariaED)");
      excecoes.setExc(exc);
      // System.err.println(exc.getLocalizedMessage());
      throw excecoes;
    }

    return ocorrenciaBancED;

  }

  public void altera(Ocorrencia_BancariaED ed) throws Excecoes{

    String sql = null;

    try{

      sql = " update Ocorrencias_Bancarias set ";
      sql += " CD_Ocorrencia = '" + ed.getCd_Ocorrencia() + "', ";
      sql += " oid_pessoa = '" + ed.getOid_Pessoa() + "', ";
      sql += " oid_Tipo_Instrucao = " + ed.getOid_Tipo_Instrucao() + ", ";

      if (ed.getTx_Descricao() != null)
        sql += " tx_descricao = '" + ed.getTx_Descricao() + "', ";

      if (ed.getVl_Movimento() != null)
        sql += " vl_movimento = " + ed.getVl_Movimento().doubleValue() + ", ";

      sql += " dm_tipo_ocorrencia = '" + ed.getDm_Tipo_Ocorrencia() + "', ";
      sql += " DT_STAMP = '" + ed.getDt_stamp() + "', ";
      sql += " USUARIO_STAMP = '" + ed.getUsuario_Stamp() + "', ";
      sql += " DM_STAMP = '" + ed.getDm_Stamp() + "' ";
      sql += " where oid_Ocorrencia_Bancaria = " + ed.getOid_Ocorrencia_Bancaria();

//      // System.err.println(sql);
      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar ocorrência bancária");
      excecoes.setMetodo(" ");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void deleta(Ocorrencia_BancariaED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from Ocorrencias_Bancarias WHERE oid_Ocorrencia_Bancaria = ";
      sql += ed.getOid_Ocorrencia_Bancaria() ;

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
    Excecoes excecoes = new Excecoes();
    excecoes.setClasse(this.getClass().getName());
    excecoes.setMensagem("Erro ao excluir Ocorrência Bancária");
    excecoes.setMetodo("deleta(Ocorrencia_BancariaED)");
    excecoes.setExc(exc);
    throw excecoes;
    }
  }

  public ArrayList lista(Ocorrencia_BancariaED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();

    try{
      sql = "select * from Ocorrencias_Bancarias, Pessoas, tipos_instrucoes where "+
      " ocorrencias_bancarias.oid_pessoa = pessoas.oid_pessoa and "+
      " ocorrencias_bancarias.oid_tipo_instrucao = tipos_instrucoes.oid_tipo_instrucao ";

      if (ed.getOid_Ocorrencia_Bancaria() != null && !ed.getOid_Ocorrencia_Bancaria().equals("")){
        sql += " and ocorrencias_bancarias.oid_ocorrencia_bancaria = " + ed.getOid_Ocorrencia_Bancaria();
      }
      if (ed.getOid_Pessoa() != null && !ed.getOid_Pessoa().equals("")){
        sql += " and ocorrencias_bancarias.oid_pessoa = " + ed.getOid_Pessoa();
      }

      ResultSet res = null;
//      // System.err.println(sql);
      res = this.executasql.executarConsulta(sql);

      //popula
      while (res.next()){
        Ocorrencia_BancariaED edVolta = new Ocorrencia_BancariaED();

        edVolta.setOid_Ocorrencia_Bancaria(new Integer(res.getString("oid_ocorrencia_bancaria")));
        edVolta.setCd_Ocorrencia(res.getString("cd_ocorrencia"));
        edVolta.setDm_Tipo_Ocorrencia(res.getString("dm_tipo_ocorrencia"));
        edVolta.setNm_Razao_Social(res.getString("nm_razao_social"));
        edVolta.setTx_Descricao(res.getString("tx_descricao"));
        edVolta.setNm_Tipo_Instrucao(res.getString("nm_tipo_instrucao")+"-");

        list.add(edVolta);
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao listar Ocorrências Bancárias");
      excecoes.setMetodo("lista(Ocorrencia_BancariaED");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return list;
  }

/********************************************************
 *
 *******************************************************/
  public Ocorrencia_BancariaED getByRecord(Ocorrencia_BancariaED ed)throws Excecoes{

    String sql = null;

    Ocorrencia_BancariaED edVolta = new Ocorrencia_BancariaED();

    try{

      sql = "select * from Ocorrencias_Bancarias, Pessoas, tipos_instrucoes where "+
      " ocorrencias_bancarias.oid_pessoa = pessoas.oid_pessoa and"+
      " ocorrencias_bancarias.oid_tipo_instrucao = tipos_instrucoes.oid_tipo_instrucao ";

      if (ed.getOid_Ocorrencia_Bancaria() != null && !ed.getOid_Ocorrencia_Bancaria().equals("")){
        sql += " and ocorrencias_bancarias.oid_ocorrencia_bancaria = " + ed.getOid_Ocorrencia_Bancaria();
      }

      if (ed.getCd_Ocorrencia() != null && !ed.getCd_Ocorrencia().equals("")){
        sql += " and ocorrencias_bancarias.cd_ocorrencia = '" + ed.getCd_Ocorrencia() + "'";
      }
      ResultSet res = null;

      // System.err.println(sql);

      res = this.executasql.executarConsulta(sql);
      while (res.next()){
        edVolta = new Ocorrencia_BancariaED();

        edVolta.setOid_Ocorrencia_Bancaria(new Integer(res.getString("oid_ocorrencia_bancaria")));
        edVolta.setCd_Ocorrencia(res.getString("cd_ocorrencia"));
        edVolta.setTx_Descricao(res.getString("tx_descricao"));
        edVolta.setOid_Pessoa(res.getString("oid_pessoa"));
        edVolta.setNm_Razao_Social(res.getString("nm_razao_social"));
        edVolta.setNr_CNPJ_CPF(res.getString("nr_cnpj_cpf"));
        edVolta.setCd_Tipo_Instrucao(res.getString("cd_tipo_instrucao"));
        edVolta.setOid_Tipo_Instrucao(new Integer(res.getString("oid_tipo_instrucao")));
        edVolta.setNm_Tipo_Instrucao(res.getString("nm_tipo_instrucao"));

        double vl_movimento = res.getDouble("vl_movimento");
        if (vl_movimento != 0.0)
          edVolta.setVl_Movimento(new Double(vl_movimento));

        edVolta.setDm_Tipo_Ocorrencia(res.getString("dm_tipo_ocorrencia"));


      }

    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao recuperar registro");
      excecoes.setMetodo("getByRecord(Ocorrencia_BancariaED)");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return edVolta;
  }


  public void geraRelatorio(Ocorrencia_BancariaED ed)throws Excecoes{

//    String sql = null;
//
//    Ocorrencia_BancariaED edVolta = new Ocorrencia_BancariaED();
//
//    try{
//
//      sql = "select * from Ocorrencia_Bancarias where oid_Ocorrencia_Bancaria > 0";
//
//      if (ed.getCD_Ocorrencia_Bancaria() != null && !ed.getCD_Ocorrencia_Bancaria().equals("")){
//        sql += " and CD_Ocorrencia_Bancaria = '" + ed.getCD_Ocorrencia_Bancaria() + "'";
//      }
//      if (ed.getCD_Remessa() != null && !ed.getCD_Remessa().equals("")){
//        sql += " and CD_Remessa = '" + ed.getCD_Remessa() + "'";
//      }
//
//      ResultSet res = null;
//      res = this.executasql.executarConsulta(sql);
//
//      Ocorrencia_BancariaRL Ocorrencia_Bancaria_rl = new Ocorrencia_BancariaRL();
//      Ocorrencia_Bancaria_rl.geraRelatEstoque(res);
//    }
//    catch (Excecoes e){
//      throw e;
//    }
//    catch(Exception exc){
//      Excecoes exce = new Excecoes();
//      exce.setExc(exc);
//      exce.setMensagem("Erro no método listar");
//      exce.setClasse(this.getClass().getName());
//      exce.setMetodo("geraRelatorio(Ocorrencia_BancariaED ed)");
//    }
//
  }

}