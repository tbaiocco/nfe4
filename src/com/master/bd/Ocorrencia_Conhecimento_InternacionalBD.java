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

import com.master.ed.Ocorrencia_ConhecimentoED;
import com.master.ed.Ocorrencia_Nota_FiscalED;
import com.master.rl.Ocorrencia_ConhecimentoRL;
import com.master.root.FormataDataBean;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

public class Ocorrencia_Conhecimento_InternacionalBD {

  private ExecutaSQL executasql;

  public Ocorrencia_Conhecimento_InternacionalBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public Ocorrencia_ConhecimentoED inclui(Ocorrencia_ConhecimentoED ed)
  throws Excecoes{
    String sql = null;
    String DM_Tipo = "";
    long valOid = 0;
    String chave = null;
    Ocorrencia_ConhecimentoED ocorrencia_ConhecimentoED = new Ocorrencia_ConhecimentoED();

    try{

    	ResultSet rs = executasql.executarConsulta("select max(oid_Ocorrencia_Conhecimento) as result from Ocorrencias_Conhecimentos_Internacionais");

      //pega proximo valor da chave
      while (rs.next()) valOid = rs.getInt("result");

      ed.setOID_Ocorrencia_Conhecimento(++valOid);

      sql = " insert into Ocorrencias_Conhecimentos_Internacionais (OID_Ocorrencia_Conhecimento, OID_Conhecimento, OID_Tipo_Ocorrencia, DT_Ocorrencia_Conhecimento, HR_Ocorrencia_Conhecimento, TX_Observacao, DT_Ocorrencia_Lancada, HR_Ocorrencia_Lancada ) values ";
      sql += "(" + ed.getOID_Ocorrencia_Conhecimento() + ",'" + ed.getOID_Conhecimento() + "'," + ed.getOID_Tipo_Ocorrencia() + ",'"  + ed.getDT_Ocorrencia_Conhecimento() + "','" + ed.getHR_Ocorrencia_Conhecimento() + "','" + ed.getTX_Observacao() + "','" + Data.getDataDMY() + "','" + Data.getHoraHM() + "')";

      int i = executasql.executarUpdate(sql);
      
      sql = "select dm_tipo from tipos_ocorrencias where oid_tipo_ocorrencia = " + ed.getOID_Tipo_Ocorrencia();

      ResultSet resOCO = null;
      resOCO = this.executasql.executarConsulta(sql);
      while (resOCO.next()) DM_Tipo = resOCO.getString("dm_Tipo");
      
      if (DM_Tipo.equals("P")|| DM_Tipo.equals("J")|| DM_Tipo.equals("A") || DM_Tipo.equals("D")|| DM_Tipo.equals("W") ){
          sql = " update Conhecimentos_Internacionais set DT_Entrega = '" + ed.getDT_Ocorrencia_Conhecimento() + "'," +
                " HR_Entrega = '" + ed.getHR_Ocorrencia_Conhecimento()  + "'";
         sql += " where oid_Conhecimento = '" + ed.getOID_Conhecimento() + "'";
         i = executasql.executarUpdate(sql);
      }

      if (ed.getOID_Tipo_Ocorrencia() == 155 ){
          sql = " update Conhecimentos_Internacionais set DT_Cruze = '" + ed.getDT_Ocorrencia_Conhecimento() + "'," +
                " HR_Cruze = '" + ed.getHR_Ocorrencia_Conhecimento()  + "'";
         sql += " where oid_Conhecimento = '" + ed.getOID_Conhecimento() + "'";
         i = executasql.executarUpdate(sql);
      }

      ocorrencia_ConhecimentoED.setOID_Ocorrencia_Conhecimento(ed.getOID_Ocorrencia_Conhecimento());

      sql =  " SELECT  Conhecimentos_Internacionais_Notas_Fiscais.Oid_Nota_Fiscal from Conhecimentos_Internacionais_Notas_Fiscais ";
      sql += " WHERE   Conhecimentos_Internacionais_Notas_Fiscais.OID_Conhecimento = '" + ed.getOID_Conhecimento() + "'";

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      FormataDataBean DataFormatada = new FormataDataBean();

      //popula
      Ocorrencia_Nota_FiscalBD Ocorrencia_Nota_FiscalBD = new Ocorrencia_Nota_FiscalBD(this.executasql);

      while (res.next()){
          Ocorrencia_Nota_FiscalED ocorrencia_Nota_FiscalED = new Ocorrencia_Nota_FiscalED();


          ocorrencia_Nota_FiscalED.setDM_Origem_Ocorrencia("C");
          ocorrencia_Nota_FiscalED.setDT_Ocorrencia_Nota_Fiscal(ed.getDT_Ocorrencia_Conhecimento());
          ocorrencia_Nota_FiscalED.setHR_Ocorrencia_Nota_Fiscal(ed.getHR_Ocorrencia_Conhecimento());
          ocorrencia_Nota_FiscalED.setOID_Tipo_Ocorrencia(new Long(ed.getOID_Tipo_Ocorrencia()).longValue());
          ocorrencia_Nota_FiscalED.setOID_Nota_Fiscal(res.getString("oid_Nota_Fiscal"));
          ocorrencia_Nota_FiscalED.setTX_Observacao(ed.getTX_Observacao());
          ocorrencia_Nota_FiscalED.setNM_Pessoa(ed.getNM_Pessoa_Entrega());
          ocorrencia_Nota_FiscalED.setTX_Observacao_Cliente(ed.getTX_Observacao());
          ocorrencia_Nota_FiscalED = Ocorrencia_Nota_FiscalBD.inclui(ocorrencia_Nota_FiscalED);
      }


    } catch(SQLException e){
        throw new Excecoes(e.getMessage(), e, getClass().getName(), "inclui(Ocorrencia_ConhecimentoED ed)");
      }
      return ocorrencia_ConhecimentoED;
  }

  public void altera(Ocorrencia_ConhecimentoED ed) throws Excecoes{

    String sql = null;

    try{

      sql = " update Ocorrencias_Conhecimentos_Internacionais set OID_Tipo_Ocorrencia= " + ed.getOID_Tipo_Ocorrencia() + ", TX_Observacao = '" + ed.getTX_Observacao() + "'";
      sql += " where oid_Ocorrencia_Conhecimento = " + ed.getOID_Ocorrencia_Conhecimento();

      int i = executasql.executarUpdate(sql);
    }

      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao alterar Ocorrência Conhecimento");
        excecoes.setMetodo("alterar");
        excecoes.setExc(exc);
        throw excecoes;
      }
  }

  public void deleta(Ocorrencia_ConhecimentoED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from Ocorrencias_Conhecimentos_Internacionais WHERE oid_Ocorrencia_Conhecimento = ";
      sql += "(" + ed.getOID_Ocorrencia_Conhecimento() + ")";

      int i = executasql.executarUpdate(sql);
    }

      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao excluir Ocorrência Conhecimento");
        excecoes.setMetodo("excluir");
        excecoes.setExc(exc);
        throw excecoes;
      }
  }

  public ArrayList lista(Ocorrencia_ConhecimentoED ed)
  throws Excecoes{
      ArrayList list = new ArrayList();
      try{
          String sql =
              " SELECT Ocorrencias_Conhecimentos_Internacionais.OID_Ocorrencia_Conhecimento, Ocorrencias_Conhecimentos_Internacionais.OID_Tipo_Ocorrencia, " +
              " DT_Ocorrencia_Conhecimento, HR_Ocorrencia_Conhecimento, Ocorrencias_Conhecimentos_Internacionais.TX_Observacao, " +
              " Conhecimentos_Internacionais.oid_Conhecimento, Conhecimentos_Internacionais.oid_Pessoa, Conhecimentos_Internacionais.oid_Pessoa_Destinatario, " +
              " Conhecimentos_Internacionais.NR_Conhecimento, Conhecimentos_Internacionais.DT_Emissao, Conhecimentos_Internacionais.oid_Unidade, " +
              " Tipos_Ocorrencias.NM_Tipo_Ocorrencia, Tipos_Ocorrencias.DM_Tipo, Tipos_Ocorrencias.DM_Acesso , " +
              " Tipos_Ocorrencias.DM_Avaria, Tipos_Ocorrencias.DM_Status " +
              " from Ocorrencias_Conhecimentos_Internacionais, Conhecimentos_Internacionais, Tipos_Ocorrencias ";
          sql += " WHERE Ocorrencias_Conhecimentos_Internacionais.OID_Conhecimento = Conhecimentos_Internacionais.OID_Conhecimento ";
          sql += " AND   Ocorrencias_Conhecimentos_Internacionais.OID_Tipo_Ocorrencia = Tipos_Ocorrencias.OID_Tipo_Ocorrencia ";
          if (String.valueOf(ed.getNR_Conhecimento()) != null &&
                  !String.valueOf(ed.getNR_Conhecimento()).equals("0") &&
                  !String.valueOf(ed.getNR_Conhecimento()).equals("null")){
              sql += " and Conhecimentos_Internacionais.NR_Conhecimento = " + ed.getNR_Conhecimento();
          }
          if (String.valueOf(ed.getOID_Tipo_Ocorrencia()) != null &&
                  !String.valueOf(ed.getOID_Tipo_Ocorrencia()).equals("0") &&
                  !String.valueOf(ed.getOID_Tipo_Ocorrencia()).equals("null")){
              sql += " and Tipos_Ocorrencias.OID_Tipo_Ocorrencia = " + ed.getOID_Tipo_Ocorrencia();
          }
          if (String.valueOf(ed.getOID_Unidade()) != null &&
                  !String.valueOf(ed.getOID_Unidade()).equals("0") &&
                  !String.valueOf(ed.getOID_Unidade()).equals("null")){
              sql += " and Conhecimentos_Internacionais.OID_Unidade = " + ed.getOID_Unidade();
          }
          if (String.valueOf(ed.getOID_Pessoa()) != null &&
                  !String.valueOf(ed.getOID_Pessoa()).equals("") &&
                  !String.valueOf(ed.getOID_Pessoa()).equals("null")){
              sql += " and Conhecimentos_Internacionais.OID_Pessoa = '" + ed.getOID_Pessoa() + "'";
          }
          if (String.valueOf(ed.getOID_Pessoa_Destinatario()) != null &&
                  !String.valueOf(ed.getOID_Pessoa_Destinatario()).equals("") &&
                  !String.valueOf(ed.getOID_Pessoa_Destinatario()).equals("null")){
              sql += " and Conhecimentos_Internacionais.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario() + "'";
          }
          if (String.valueOf(ed.getOID_Pessoa_Consignatario()) != null &&
                  !String.valueOf(ed.getOID_Pessoa_Consignatario()).equals("") &&
                  !String.valueOf(ed.getOID_Pessoa_Consignatario()).equals("null")){
              sql += " and Conhecimentos.OID_Pessoa_Consignatario = '" + ed.getOID_Pessoa_Consignatario() + "'";
          }
          if (String.valueOf(ed.getDT_Emissao()) != null &&
                  !String.valueOf(ed.getDT_Emissao()).equals("") &&
                  !String.valueOf(ed.getDT_Emissao()).equals("null")){
              sql += " and Conhecimentos_Internacionais.DT_Emissao = '" + ed.getDT_Emissao() + "'";
          }
          if (String.valueOf(ed.getDT_Ocorrencia_Conhecimento()) != null &&
                  !String.valueOf(ed.getDT_Ocorrencia_Conhecimento()).equals("") &&
                  !String.valueOf(ed.getDT_Ocorrencia_Conhecimento()).equals("null")){
              sql += " and Ocorrencias_Conhecimentos_Internacionais.DT_Ocorrencia_Conhecimento = '" + ed.getDT_Ocorrencia_Conhecimento() + "'";
          }
          sql += " Order by Ocorrencias_Conhecimentos_Internacionais.DT_Ocorrencia_Conhecimento, Ocorrencias_Conhecimentos_Internacionais.HR_Ocorrencia_Conhecimento ";
          
// System.out.println(" sql cto  ret list >> " + sql);

ResultSet res = this.executasql.executarConsulta(sql);
          
          FormataDataBean DataFormatada = new FormataDataBean();
          
          //popula
          while (res.next()){
              Ocorrencia_ConhecimentoED edVolta = new Ocorrencia_ConhecimentoED();
              
              edVolta.setOID_Ocorrencia_Conhecimento(res.getLong("OID_Ocorrencia_Conhecimento"));
              edVolta.setOID_Tipo_Ocorrencia(res.getLong("OID_Tipo_Ocorrencia"));
              edVolta.setOID_Conhecimento(res.getString("OID_Conhecimento"));
              
              edVolta.setOID_Pessoa(res.getString("OID_Pessoa"));
              edVolta.setOID_Pessoa_Destinatario(res.getString("OID_Pessoa_Destinatario"));
              edVolta.setNR_Conhecimento(res.getLong("NR_Conhecimento"));
              
              edVolta.setDT_Ocorrencia_Conhecimento(res.getString("DT_Ocorrencia_Conhecimento"));
              DataFormatada.setDT_FormataData(edVolta.getDT_Ocorrencia_Conhecimento());
              edVolta.setDT_Ocorrencia_Conhecimento(DataFormatada.getDT_FormataData());
              
              edVolta.setHR_Ocorrencia_Conhecimento(res.getString("HR_Ocorrencia_Conhecimento"));
              edVolta.setTX_Observacao(res.getString("TX_Observacao"));
              
              edVolta.setNM_Tipo_Ocorrencia(res.getString("NM_Tipo_Ocorrencia"));
              
              edVolta.setDM_Tipo(res.getString("DM_Tipo"));
              edVolta.setDM_Acesso(res.getString("DM_Acesso"));
              edVolta.setDM_Avaria(res.getString("DM_Avaria"));
              edVolta.setDM_Status(res.getString("DM_Status"));
              
              edVolta.setDT_Emissao(res.getString("DT_Emissao"));
              DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
              edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());
              
              edVolta.setOID_Unidade(res.getLong("oid_Unidade"));
              
              edVolta.setNM_Observacao(res.getString("TX_Observacao"));
              
              list.add(edVolta);
          }
      }
      catch(SQLException e){
          throw new Excecoes(e.getMessage(), e, getClass().getName(), "lista(Ocorrencia_ConhecimentoED ed)");
      }
      
      return list;
  }

  public Ocorrencia_ConhecimentoED getByRecord(Ocorrencia_ConhecimentoED ed)throws Excecoes{

    String sql = null;

    Ocorrencia_ConhecimentoED edVolta = new Ocorrencia_ConhecimentoED();

    try{

      sql =  " SELECT Ocorrencias_Conhecimentos_Internacionais.OID_Ocorrencia_Conhecimento, Ocorrencias_Conhecimentos_Internacionais.OID_Tipo_Ocorrencia, " +
      		 " DT_Ocorrencia_Conhecimento, HR_Ocorrencia_Conhecimento, Ocorrencias_Conhecimentos_Internacionais.TX_Observacao, Conhecimentos_Internacionais.oid_Conhecimento, " +
      		 " Conhecimentos_Internacionais.oid_Pessoa, Conhecimentos_Internacionais.oid_Pessoa_Destinatario, Conhecimentos_Internacionais.NR_Conhecimento, " +
      		 " Conhecimentos_Internacionais.DT_Emissao, Conhecimentos_Internacionais.oid_Unidade, Tipos_Ocorrencias.NM_Tipo_Ocorrencia, Tipos_Ocorrencias.DM_Tipo, " +
      		 " Tipos_Ocorrencias.DM_Acesso , Tipos_Ocorrencias.DM_Avaria, Tipos_Ocorrencias.DM_Status " +
      		 " from Ocorrencias_Conhecimentos_Internacionais, Conhecimentos_Internacionais, Tipos_Ocorrencias ";
      sql += " WHERE Ocorrencias_Conhecimentos_Internacionais.OID_Conhecimento    = Conhecimentos_Internacionais.OID_Conhecimento ";
      sql += " AND   Ocorrencias_Conhecimentos_Internacionais.OID_Tipo_Ocorrencia = Tipos_Ocorrencias.OID_Tipo_Ocorrencia ";

      if (String.valueOf(ed.getOID_Ocorrencia_Conhecimento()) != null &&
          !String.valueOf(ed.getOID_Ocorrencia_Conhecimento()).equals("0")){
        sql += " and OID_Ocorrencia_Conhecimento = " + ed.getOID_Ocorrencia_Conhecimento();
      }
// System.out.println(" sql cto  ret by >> " + sql);


      FormataDataBean DataFormatada = new FormataDataBean();

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);
      while (res.next()){

        edVolta.setOID_Ocorrencia_Conhecimento(res.getLong("OID_Ocorrencia_Conhecimento"));
        edVolta.setOID_Tipo_Ocorrencia(res.getLong("OID_Tipo_Ocorrencia"));
        edVolta.setOID_Conhecimento(res.getString("OID_Conhecimento"));

        edVolta.setOID_Pessoa(res.getString("OID_Pessoa"));
        edVolta.setOID_Pessoa_Destinatario(res.getString("OID_Pessoa_Destinatario"));
        edVolta.setNR_Conhecimento(res.getLong("NR_Conhecimento"));

        edVolta.setDT_Ocorrencia_Conhecimento(res.getString("DT_Ocorrencia_Conhecimento"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Ocorrencia_Conhecimento());
        edVolta.setDT_Ocorrencia_Conhecimento(DataFormatada.getDT_FormataData());

        edVolta.setHR_Ocorrencia_Conhecimento(res.getString("HR_Ocorrencia_Conhecimento"));
        edVolta.setTX_Observacao(res.getString("TX_Observacao"));

        edVolta.setDM_Tipo(res.getString("DM_Tipo"));
        edVolta.setDM_Acesso(res.getString("DM_Acesso"));
        edVolta.setDM_Avaria(res.getString("DM_Avaria"));
        edVolta.setDM_Status(res.getString("DM_Status"));

        edVolta.setDT_Emissao(res.getString("DT_Emissao"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
        edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());

        edVolta.setOID_Unidade(res.getLong("oid_Unidade"));
      }
    }
      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao selecionar Ocorrência Conhecimento");
        excecoes.setMetodo("seleção");
        excecoes.setExc(exc);
        throw excecoes;
      }

    return edVolta;
  }

/**  public void geraRelatorio(Ocorrencia_ConhecimentoED ed)throws Excecoes{

    String sql = null;

    Ocorrencia_ConhecimentoED edVolta = new Ocorrencia_ConhecimentoED();

    try{

      sql = "select * from Ocorrencia_Conhecimentos where oid_Ocorrencia_Conhecimento > 0";

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      Ocorrencia_ConhecimentoRL Ocorrencia_Conhecimento_rl = new Ocorrencia_ConhecimentoRL();
      Ocorrencia_Conhecimento_rl.geraRelatEstoque(res);
    }
    catch (Excecoes e){
      throw e;
    }
    catch(Exception exc){
      Excecoes exce = new Excecoes();
      exce.setExc(exc);
      exce.setMensagem("Erro no método listar");
      exce.setClasse(this.getClass().getName());
      exce.setMetodo("geraRelatorio(Ocorrencia_ConhecimentoED ed)");
    }

  }*/

}
