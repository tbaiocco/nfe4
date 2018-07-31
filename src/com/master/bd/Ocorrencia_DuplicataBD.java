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

import com.master.ed.Ocorrencia_DuplicataED;
import com.master.rl.Ocorrencia_DuplicataRL;
import com.master.root.FormataDataBean;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

public class Ocorrencia_DuplicataBD {

  private ExecutaSQL executasql;

  public Ocorrencia_DuplicataBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public Ocorrencia_DuplicataED inclui(Ocorrencia_DuplicataED ed) throws Excecoes{

    String sql = null;
    long valOid = 0;
    String chave = null;
    Ocorrencia_DuplicataED ocorrencia_DuplicataED = new Ocorrencia_DuplicataED();

    try{

      ResultSet rs = executasql.executarConsulta("select max(oid_Ocorrencia_Duplicata) as result from Ocorrencias_Duplicatas");

      //pega proximo valor da chave
      while (rs.next()) valOid = rs.getInt("result");

      ed.setOID_Ocorrencia_Duplicata(++valOid);

      sql = " insert into Ocorrencias_Duplicatas (OID_OCORRENCIA_DUPLICATA, OID_DUPLICATA, OID_TIPO_OCORRENCIA, DT_OCORRENCIA_DUPLICATA, HR_OCORRENCIA_DUPLICATA, TX_OCORRENCIA_DUPLICATA ) values ";

      sql += "(" + ed.getOID_Ocorrencia_Duplicata() + "," + ed.getOID_Duplicata() + "," + ed.getOID_Tipo_Ocorrencia() + ",'"  + ed.getDT_Ocorrencia_Duplicata() + "','" + ed.getHR_Ocorrencia_Duplicata() + "','" + ed.getTX_Observacao() + "')";

      //invoca o metodo executarupdate do objeto
      //executasql. que eh uma referencia ao
      //objeto ExecutSQL, que contem a conexao ativa
      int i = executasql.executarUpdate(sql);
      ocorrencia_DuplicataED.setOID_Ocorrencia_Duplicata(ed.getOID_Ocorrencia_Duplicata());

    }
      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao incluir Ocorrência Duplicata");
        excecoes.setMetodo("inclui");
        excecoes.setExc(exc);
        throw excecoes;
      }
      return ocorrencia_DuplicataED;
  }

  public void altera(Ocorrencia_DuplicataED ed) throws Excecoes{

    String sql = null;

    try{

      sql = " update Ocorrencias_Duplicatas set OID_Tipo_Ocorrencia= " + ed.getOID_Tipo_Ocorrencia() + ", TX_Observacao = '" + ed.getTX_Observacao() + "'";
      sql += " where oid_Ocorrencia_Duplicata = " + ed.getOID_Ocorrencia_Duplicata();

      int i = executasql.executarUpdate(sql);
    }

      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao alterar Ocorrência Duplicata");
        excecoes.setMetodo("alterar");
        excecoes.setExc(exc);
        throw excecoes;
      }
  }

  public void deleta(Ocorrencia_DuplicataED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from Ocorrencias_Duplicatas WHERE oid_Ocorrencia_Duplicata = ";
      sql += "(" + ed.getOID_Ocorrencia_Duplicata() + ")";

      int i = executasql.executarUpdate(sql);
    }

      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao excluir Ocorrência Duplicata");
        excecoes.setMetodo("excluir");
        excecoes.setExc(exc);
        throw excecoes;
      }
  }

  public ArrayList lista(Ocorrencia_DuplicataED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();

    try{
      sql =  " SELECT OID_Ocorrencia_Duplicata, DT_Ocorrencia_Duplicata, HR_Ocorrencia_Duplicata, Duplicatas.NR_Duplicata, Duplicatas.DT_Emissao, Tipos_Ocorrencias.NM_Tipo_Ocorrencia, Unidades.CD_Unidade, Pessoa_Cliente.NM_Razao_Social as NM_Razao_Social_Cliente, Ocorrencias_Duplicatas.tx_ocorrencia_duplicata " + 
             " FROM  Ocorrencias_Duplicatas, Duplicatas, Unidades, Pessoas Pessoa_Cliente, Tipos_Ocorrencias ";
      sql += " WHERE Unidades.oid_Unidade = Duplicatas.oid_Unidade ";
      sql += " AND Duplicatas.oid_Pessoa = Pessoa_Cliente.oid_Pessoa ";
      sql += " AND Ocorrencias_Duplicatas.OID_Duplicata = Duplicatas.OID_Duplicata ";
      sql += " AND Ocorrencias_Duplicatas.OID_Tipo_Ocorrencia = Tipos_Ocorrencias.OID_Tipo_Ocorrencia ";

      if (String.valueOf(ed.getNR_Duplicata()) != null &&
          !String.valueOf(ed.getNR_Duplicata()).equals("0") &&
          !String.valueOf(ed.getNR_Duplicata()).equals("null")){
        sql += " and Duplicatas.NR_Duplicata = " + ed.getNR_Duplicata();
      }
      if (String.valueOf(ed.getOID_Tipo_Ocorrencia()) != null &&
          !String.valueOf(ed.getOID_Tipo_Ocorrencia()).equals("0") &&
          !String.valueOf(ed.getOID_Tipo_Ocorrencia()).equals("null")){
        sql += " and Tipos_Ocorrencias.OID_Tipo_Ocorrencia = " + ed.getOID_Tipo_Ocorrencia();
      }

      if (String.valueOf(ed.getOID_Unidade()) != null &&
          !String.valueOf(ed.getOID_Unidade()).equals("0") &&
          !String.valueOf(ed.getOID_Unidade()).equals("null")){
        sql += " and Duplicatas.OID_Unidade = " + ed.getOID_Unidade();
      }
      if (String.valueOf(ed.getOID_Pessoa()) != null &&
          !String.valueOf(ed.getOID_Pessoa()).equals("") &&
          !String.valueOf(ed.getOID_Pessoa()).equals("null")){
        sql += " and Duplicatas.OID_Pessoa = '" + ed.getOID_Pessoa() + "'";
      }
      if (String.valueOf(ed.getDT_Emissao()) != null &&
          !String.valueOf(ed.getDT_Emissao()).equals("") &&
          !String.valueOf(ed.getDT_Emissao()).equals("null")){
        sql += " and Duplicatas.DT_Emissao = '" + ed.getDT_Emissao() + "'";
      }
      if (String.valueOf(ed.getDT_Ocorrencia_Duplicata()) != null &&
          !String.valueOf(ed.getDT_Ocorrencia_Duplicata()).equals("") &&
          !String.valueOf(ed.getDT_Ocorrencia_Duplicata()).equals("null")){
        sql += " and Ocorrencias_Duplicatas.DT_Ocorrencia_Duplicata = '" + ed.getDT_Ocorrencia_Duplicata() + "'";
      }

      sql += " Order by Duplicatas.NR_Duplicata ";

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      FormataDataBean DataFormatada = new FormataDataBean();

      //popula
      while (res.next()){
        Ocorrencia_DuplicataED edVolta = new Ocorrencia_DuplicataED();

        edVolta.setOID_Ocorrencia_Duplicata(res.getLong("OID_Ocorrencia_Duplicata"));

        edVolta.setDT_Ocorrencia_Duplicata(res.getString("DT_Ocorrencia_Duplicata"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Ocorrencia_Duplicata());
        edVolta.setDT_Ocorrencia_Duplicata(DataFormatada.getDT_FormataData());

        edVolta.setHR_Ocorrencia_Duplicata(res.getString("HR_Ocorrencia_Duplicata"));
        edVolta.setNR_Duplicata(res.getLong("NR_Duplicata"));

        edVolta.setDT_Emissao(res.getString("DT_Emissao"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
        edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());

        edVolta.setNM_Tipo_Ocorrencia(res.getString("NM_Tipo_Ocorrencia"));
        edVolta.setCD_Unidade(res.getString("CD_Unidade"));
        edVolta.setNM_Pessoa_Cliente(res.getString("NM_Razao_Social_Cliente"));
        edVolta.setTX_Observacao(" ");
        if (res.getString("tx_ocorrencia_duplicata")!=null && !res.getString("tx_ocorrencia_duplicata").equals("null")){
          edVolta.setTX_Observacao(res.getString("tx_ocorrencia_duplicata"));
        }
          

        list.add(edVolta);
      }
    }
      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao listar Ocorrência Duplicata");
        excecoes.setMetodo("listar");
        excecoes.setExc(exc);
        throw excecoes;
      }

    return list;
  }

  public Ocorrencia_DuplicataED getByRecord(Ocorrencia_DuplicataED ed)throws Excecoes{

    String sql = null;

    Ocorrencia_DuplicataED edVolta = new Ocorrencia_DuplicataED();

    try{

      sql =  " SELECT OID_Ocorrencia_Duplicata, OID_Tipo_Ocorrencia, DT_Ocorrencia_Duplicata, HR_Ocorrencia_Duplicata, Ocorrencias_Duplicatas.TX_OCORRENCIA_DUPLICATA, Duplicatas.oid_Duplicata, Duplicatas.oid_Pessoa, Duplicatas.NR_Duplicata, Duplicatas.DT_Emissao, Duplicatas.oid_Unidade from Ocorrencias_Duplicatas, Duplicatas ";
      sql += " WHERE Ocorrencias_Duplicatas.OID_Duplicata = Duplicatas.OID_Duplicata ";

      if (String.valueOf(ed.getOID_Ocorrencia_Duplicata()) != null &&
          !String.valueOf(ed.getOID_Ocorrencia_Duplicata()).equals("0")){
        sql += " and OID_Ocorrencia_Duplicata = " + ed.getOID_Ocorrencia_Duplicata();
      }

      FormataDataBean DataFormatada = new FormataDataBean();

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);
      while (res.next()){
        edVolta.setOID_Ocorrencia_Duplicata(res.getLong("OID_Ocorrencia_Duplicata"));
        edVolta.setOID_Tipo_Ocorrencia(res.getLong("OID_Tipo_Ocorrencia"));
        edVolta.setOID_Duplicata(new Long(res.getString("OID_Duplicata")).longValue());
        edVolta.setOID_Pessoa(res.getString("OID_Pessoa"));
        edVolta.setNR_Duplicata(res.getLong("NR_Duplicata"));

        edVolta.setDT_Ocorrencia_Duplicata(res.getString("DT_Ocorrencia_Duplicata"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Ocorrencia_Duplicata());
        edVolta.setDT_Ocorrencia_Duplicata(DataFormatada.getDT_FormataData());

        edVolta.setHR_Ocorrencia_Duplicata(res.getString("HR_Ocorrencia_Duplicata"));
        edVolta.setTX_Observacao(res.getString("TX_OCORRENCIA_DUPLICATA"));

        edVolta.setDT_Emissao(res.getString("DT_Emissao"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
        edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());

        edVolta.setOID_Unidade(res.getLong("oid_Unidade"));
      }
    }
      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao selecionar Ocorrência Duplicata");
        excecoes.setMetodo("seleção");
        excecoes.setExc(exc);
        throw excecoes;
      }

    return edVolta;
  }

  public void geraRelatorio(Ocorrencia_DuplicataED ed)throws Excecoes{

    String sql = null;

    Ocorrencia_DuplicataED edVolta = new Ocorrencia_DuplicataED();

    try{

      sql = "select * from Ocorrencia_Duplicatas where oid_Ocorrencia_Duplicata > 0";

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      Ocorrencia_DuplicataRL Ocorrencia_Duplicata_rl = new Ocorrencia_DuplicataRL();
      Ocorrencia_Duplicata_rl.geraRelatEstoque(res);
    }
    catch (Excecoes e){
      throw e;
    }
    catch(Exception exc){
      Excecoes exce = new Excecoes();
      exce.setExc(exc);
      exce.setMensagem("Erro no método listar");
      exce.setClasse(this.getClass().getName());
      exce.setMetodo("geraRelatorio(Ocorrencia_DuplicataED ed)");
    }

  }

}