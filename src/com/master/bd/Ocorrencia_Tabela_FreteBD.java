package com.master.bd;

/**
 * <p>Title: Ocorrencia_CRT</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.master.ed.Ocorrencia_Tabela_FreteED;
import com.master.ed.Ocorrencia_Nota_FiscalED;
import com.master.root.FormataDataBean;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

public class Ocorrencia_Tabela_FreteBD {

  private ExecutaSQL executasql;

  public Ocorrencia_Tabela_FreteBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public Ocorrencia_Tabela_FreteED inclui(Ocorrencia_Tabela_FreteED ed)
  throws Excecoes{
    String sql = null;
    String DM_Tipo = "";
    long valOid = 0;
    String chave = null;
    Ocorrencia_Tabela_FreteED ocorrencia_Tabela_FreteED = new Ocorrencia_Tabela_FreteED();

    try{

    	ResultSet rs = executasql.executarConsulta("select max(oid_ocorrencia_tabela_frete) as result from Ocorrencias_Tabelas_Fretes");

      //pega proximo valor da chave
      while (rs.next()) valOid = rs.getInt("result");

      ed.setOID_Ocorrencia_Tabela_Frete(++valOid);

      sql = " insert into Ocorrencias_Tabelas_Fretes (OID_Ocorrencia_Tabela_Frete, OID_Tabela_Frete, OID_Tipo_Ocorrencia, DT_Ocorrencia_Tabela_Frete, HR_Ocorrencia_Tabela_Frete, TX_Observacao, DT_Ocorrencia_Lancada, HR_Ocorrencia_Lancada ) values ";
      sql += "(" + ed.getOID_Ocorrencia_Tabela_Frete() + ",'" + ed.getOID_Tabela_Frete() + "'," + ed.getOID_Tipo_Ocorrencia() + ",'"  + ed.getDT_Ocorrencia_Tabela_Frete() + "','" + ed.getHR_Ocorrencia_Tabela_Frete() + "','" + ed.getTX_Observacao() + "','" + Data.getDataDMY() + "','" + Data.getHoraHM() + "')";

      int i = executasql.executarUpdate(sql);
      
      sql = "select dm_tipo from tipos_ocorrencias where oid_tipo_ocorrencia = " + ed.getOID_Tipo_Ocorrencia();

      ResultSet resOCO = null;
      resOCO = this.executasql.executarConsulta(sql);
      while (resOCO.next()) DM_Tipo = resOCO.getString("dm_Tipo");
      
      

    } catch(SQLException e){
        throw new Excecoes(e.getMessage(), e, getClass().getName(), "inclui(Ocorrencia_Tabela_FreteED ed)");
      }
      return ocorrencia_Tabela_FreteED;
  }

  public void altera(Ocorrencia_Tabela_FreteED ed) throws Excecoes{

    String sql = null;

    try{

      sql = " update Ocorrencias_Tabelas_Fretes set OID_Tipo_Ocorrencia= " + ed.getOID_Tipo_Ocorrencia() + ", TX_Observacao = '" + ed.getTX_Observacao() + "'";
      sql += " where oid_Ocorrencia_Tabela_Frete = " + ed.getOID_Ocorrencia_Tabela_Frete();

      int i = executasql.executarUpdate(sql);
    }

      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao alterar Ocorrência Tabela_Frete");
        excecoes.setMetodo("alterar");
        excecoes.setExc(exc);
        throw excecoes;
      }
  }

  public void deleta(Ocorrencia_Tabela_FreteED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from Ocorrencias_Tabelas_Fretes WHERE oid_Ocorrencia_Tabela_Frete = ";
      sql += "(" + ed.getOID_Ocorrencia_Tabela_Frete() + ")";

      int i = executasql.executarUpdate(sql);
    }

      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao excluir Ocorrência Tabela_Frete");
        excecoes.setMetodo("excluir");
        excecoes.setExc(exc);
        throw excecoes;
      }
  }

  public ArrayList lista(Ocorrencia_Tabela_FreteED ed)
  throws Excecoes{
      ArrayList list = new ArrayList();
      try{
          String sql =
              " SELECT Ocorrencias_Tabelas_Fretes.OID_Ocorrencia_Tabela_Frete, Ocorrencias_Tabelas_Fretes.OID_Tipo_Ocorrencia, " +
              " DT_Ocorrencia_Tabela_Frete, HR_Ocorrencia_Tabela_Frete, Ocorrencias_Tabelas_Fretes.TX_Observacao, " +
              " Tabelas_Fretes.oid_Tabela_Frete, Tabelas_Fretes.oid_Pessoa,  " +
              " Tabelas_Fretes.NR_Cotacao, Tabelas_Fretes.dt_cotacao, Tabelas_Fretes.oid_Unidade, " +
              " Tipos_Ocorrencias.NM_Tipo_Ocorrencia, Tipos_Ocorrencias.DM_Tipo, Tipos_Ocorrencias.DM_Acesso , " +
              " Tipos_Ocorrencias.DM_Avaria, Tipos_Ocorrencias.DM_Status " +
              " from Ocorrencias_Tabelas_Fretes, Tabelas_Fretes, Tipos_Ocorrencias ";
          sql += " WHERE Ocorrencias_Tabelas_Fretes.OID_Tabela_Frete = Tabelas_Fretes.OID_Tabela_Frete ";
          sql += " AND   Ocorrencias_Tabelas_Fretes.OID_Tipo_Ocorrencia = Tipos_Ocorrencias.OID_Tipo_Ocorrencia ";
          if (String.valueOf(ed.getNR_Cotacao()) != null &&
                  !String.valueOf(ed.getNR_Cotacao()).equals("0") &&
                  !String.valueOf(ed.getNR_Cotacao()).equals("null")){
              sql += " and Tabelas_Fretes.NR_Cotacao = " + ed.getNR_Cotacao();
          }
          if (String.valueOf(ed.getOID_Tipo_Ocorrencia()) != null &&
                  !String.valueOf(ed.getOID_Tipo_Ocorrencia()).equals("0") &&
                  !String.valueOf(ed.getOID_Tipo_Ocorrencia()).equals("null")){
              sql += " and Tipos_Ocorrencias.OID_Tipo_Ocorrencia = " + ed.getOID_Tipo_Ocorrencia();
          }
          if (String.valueOf(ed.getOID_Unidade()) != null &&
                  !String.valueOf(ed.getOID_Unidade()).equals("0") &&
                  !String.valueOf(ed.getOID_Unidade()).equals("null")){
              sql += " and Tabelas_Fretes.OID_Unidade = " + ed.getOID_Unidade();
          }
          if (String.valueOf(ed.getOID_Pessoa()) != null &&
                  !String.valueOf(ed.getOID_Pessoa()).equals("") &&
                  !String.valueOf(ed.getOID_Pessoa()).equals("null")){
              sql += " and Tabelas_Fretes.OID_Pessoa = '" + ed.getOID_Pessoa() + "'";
          }
          if (String.valueOf(ed.getDT_Emissao()) != null &&
                  !String.valueOf(ed.getDT_Emissao()).equals("") &&
                  !String.valueOf(ed.getDT_Emissao()).equals("null")){
              sql += " and Tabelas_Fretes.dt_cotacao = '" + ed.getDT_Emissao() + "'";
          }
          if (String.valueOf(ed.getDT_Ocorrencia_Tabela_Frete()) != null &&
                  !String.valueOf(ed.getDT_Ocorrencia_Tabela_Frete()).equals("") &&
                  !String.valueOf(ed.getDT_Ocorrencia_Tabela_Frete()).equals("null")){
              sql += " and Ocorrencias_Tabelas_Fretes.DT_Ocorrencia_Tabela_Frete = '" + ed.getDT_Ocorrencia_Tabela_Frete() + "'";
          }
          sql += " Order by Ocorrencias_Tabelas_Fretes.DT_Ocorrencia_Tabela_Frete, Ocorrencias_Tabelas_Fretes.HR_Ocorrencia_Tabela_Frete ";
          
// System.out.println(" sql cto  ret list >> " + sql);

ResultSet res = this.executasql.executarConsulta(sql);
          
          FormataDataBean DataFormatada = new FormataDataBean();
          
          //popula
          while (res.next()){
              Ocorrencia_Tabela_FreteED edVolta = new Ocorrencia_Tabela_FreteED();
              
              edVolta.setOID_Ocorrencia_Tabela_Frete(res.getLong("OID_Ocorrencia_Tabela_Frete"));
              edVolta.setOID_Tipo_Ocorrencia(res.getLong("OID_Tipo_Ocorrencia"));
              edVolta.setOID_Tabela_Frete(res.getLong("OID_Tabela_Frete"));
              
              edVolta.setOID_Pessoa(res.getString("OID_Pessoa"));
              edVolta.setNR_Cotacao(res.getLong("NR_Cotacao"));
              
              edVolta.setDT_Ocorrencia_Tabela_Frete(res.getString("DT_Ocorrencia_Tabela_Frete"));
              DataFormatada.setDT_FormataData(edVolta.getDT_Ocorrencia_Tabela_Frete());
              edVolta.setDT_Ocorrencia_Tabela_Frete(DataFormatada.getDT_FormataData());
              
              edVolta.setHR_Ocorrencia_Tabela_Frete(res.getString("HR_Ocorrencia_Tabela_Frete"));
              edVolta.setTX_Observacao(res.getString("TX_Observacao"));
              
              edVolta.setNM_Tipo_Ocorrencia(res.getString("NM_Tipo_Ocorrencia"));
              
              edVolta.setDM_Tipo(res.getString("DM_Tipo"));
              edVolta.setDM_Acesso(res.getString("DM_Acesso"));
              edVolta.setDM_Avaria(res.getString("DM_Avaria"));
              edVolta.setDM_Status(res.getString("DM_Status"));
              
              edVolta.setDT_Emissao(res.getString("dt_cotacao"));
              DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
              edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());
              
              edVolta.setOID_Unidade(res.getLong("oid_Unidade"));
              
              edVolta.setNM_Observacao(res.getString("TX_Observacao"));
              
              list.add(edVolta);
          }
      }
      catch(SQLException e){
          throw new Excecoes(e.getMessage(), e, getClass().getName(), "lista(Ocorrencia_Tabela_FreteED ed)");
      }
      
      return list;
  }

  public Ocorrencia_Tabela_FreteED getByRecord(Ocorrencia_Tabela_FreteED ed)throws Excecoes{

    String sql = null;

    Ocorrencia_Tabela_FreteED edVolta = new Ocorrencia_Tabela_FreteED();

    try{

      sql =  " SELECT Ocorrencias_Tabelas_Fretes.OID_Ocorrencia_Tabela_Frete, Ocorrencias_Tabelas_Fretes.OID_Tipo_Ocorrencia, " +
      		 " DT_Ocorrencia_Tabela_Frete, HR_Ocorrencia_Tabela_Frete, Ocorrencias_Tabelas_Fretes.TX_Observacao, Tabelas_Fretes.oid_Tabela_Frete, " +
      		 " Tabelas_Fretes.oid_Pessoa, Tabelas_Fretes.NR_Cotacao, " +
      		 " Tabelas_Fretes.dt_cotacao, Tabelas_Fretes.oid_Unidade, Tipos_Ocorrencias.NM_Tipo_Ocorrencia, Tipos_Ocorrencias.DM_Tipo, " +
      		 " Tipos_Ocorrencias.DM_Acesso , Tipos_Ocorrencias.DM_Avaria, Tipos_Ocorrencias.DM_Status " +
      		 " from Ocorrencias_Tabelas_Fretes, Tabelas_Fretes, Tipos_Ocorrencias ";
      sql += " WHERE Ocorrencias_Tabelas_Fretes.OID_Tabela_Frete    = Tabelas_Fretes.OID_Tabela_Frete ";
      sql += " AND   Ocorrencias_Tabelas_Fretes.OID_Tipo_Ocorrencia = Tipos_Ocorrencias.OID_Tipo_Ocorrencia ";

      if (String.valueOf(ed.getOID_Ocorrencia_Tabela_Frete()) != null &&
          !String.valueOf(ed.getOID_Ocorrencia_Tabela_Frete()).equals("0")){
        sql += " and OID_Ocorrencia_Tabela_Frete = " + ed.getOID_Ocorrencia_Tabela_Frete();
      }
// System.out.println(" sql cto  ret by >> " + sql);


      FormataDataBean DataFormatada = new FormataDataBean();

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);
      while (res.next()){

        edVolta.setOID_Ocorrencia_Tabela_Frete(res.getLong("OID_Ocorrencia_Tabela_Frete"));
        edVolta.setOID_Tipo_Ocorrencia(res.getLong("OID_Tipo_Ocorrencia"));
        edVolta.setOID_Tabela_Frete(res.getLong("OID_Tabela_Frete"));

        edVolta.setOID_Pessoa(res.getString("OID_Pessoa"));
        edVolta.setNR_Cotacao(res.getLong("NR_Cotacao"));

        edVolta.setDT_Ocorrencia_Tabela_Frete(res.getString("DT_Ocorrencia_Tabela_Frete"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Ocorrencia_Tabela_Frete());
        edVolta.setDT_Ocorrencia_Tabela_Frete(DataFormatada.getDT_FormataData());

        edVolta.setHR_Ocorrencia_Tabela_Frete(res.getString("HR_Ocorrencia_Tabela_Frete"));
        edVolta.setTX_Observacao(res.getString("TX_Observacao"));

        edVolta.setDM_Tipo(res.getString("DM_Tipo"));
        edVolta.setDM_Acesso(res.getString("DM_Acesso"));
        edVolta.setDM_Avaria(res.getString("DM_Avaria"));
        edVolta.setDM_Status(res.getString("DM_Status"));

        edVolta.setDT_Emissao(res.getString("dt_cotacao"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
        edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());

        edVolta.setOID_Unidade(res.getLong("oid_Unidade"));
      }
    }
      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao selecionar Ocorrência Tabela_Frete");
        excecoes.setMetodo("seleção");
        excecoes.setExc(exc);
        throw excecoes;
      }

    return edVolta;
  }


}
