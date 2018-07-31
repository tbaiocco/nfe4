package com.master.bd;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import com.master.util.*;
import com.master.util.bd.*;
import com.master.util.ed.Parametro_FixoED;
import com.master.rl.*;
import com.master.root.CidadeBean;
import com.master.root.FormataDataBean;
import com.master.ed.ManifestoED;
import com.master.ed.Manifesto_InternacionalED;
import com.master.ed.Ocorrencia_ConhecimentoED;
import com.master.ed.Ocorrencia_EmbarqueED;
import java.util.*;
import java.sql.*;

public class Ocorrencia_EmbarqueBD{

  private ExecutaSQL executasql;
  Parametro_FixoED  edParametro_Fixo = new Parametro_FixoED();

  public Ocorrencia_EmbarqueBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public Ocorrencia_EmbarqueED inclui(Ocorrencia_EmbarqueED ed) throws Excecoes{

    String sql = null;
    long valOid = 0;
    String chave = null;
    Ocorrencia_EmbarqueED ocorrencia_EmbarqueED = new Ocorrencia_EmbarqueED();

    try{
      ResultSet rs = executasql.executarConsulta("select max(oid_Ocorrencia_Embarque) as result from Ocorrencias_Embarques");

      //pega proximo valor da chave
      while (rs.next()) valOid = rs.getInt("result");

      ed.setOID_Ocorrencia_Embarque(++valOid);

      String TX_Descricao = ed.getNM_Cidade() + "-" + ed.getCD_Estado();

      sql = " insert into Ocorrencias_Embarques (OID_Ocorrencia_Embarque, OID_Embarque, OID_Tipo_Ocorrencia, DT_Ocorrencia_Embarque, HR_Ocorrencia_Embarque, DT_Ocorrencia_Lancada, HR_Ocorrencia_Lancada, TX_Observacao, TX_Descricao, OID_Cidade, nr_odometro_parcial ) values ";
      sql += "(" + ed.getOID_Ocorrencia_Embarque() + "," + ed.getOID_Embarque() + "," + ed.getOID_Tipo_Ocorrencia() + ",'"  + ed.getDT_Ocorrencia_Embarque() + "','" + ed.getHR_Ocorrencia_Embarque() + "','" + ed.getDT_Ocorrencia_Lancada() + "','" + ed.getHR_Ocorrencia_Lancada() + "','" + ed.getTX_Observacao() + "','" + TX_Descricao + "'," + ed.getOID_Cidade() + ", " + ed.getNR_Odometro_Parcial() + ")";

//// System.out.println(sql);

      int i = executasql.executarUpdate(sql);

      if( ed.getDT_Ocorrencia_Embarque() != null && ed.getHR_Ocorrencia_Embarque() != null
          && ed.getTX_Observacao() != null ){
        sql = " update Embarques set OID_CIDADE_APOIO = " + ed.getOID_Cidade() +",";
        sql += " dt_ultimo_contato = '"+ed.getDT_Ocorrencia_Embarque()+"',";
        sql += " hr_ultimo_contato = '"+ed.getHR_Ocorrencia_Embarque()+"',";
        sql += " tx_ultima_observacao = '"+ed.getTX_Observacao()+"'";
        sql += " where oid_Embarque = " + ed.getOID_Embarque();
      }else{
        sql = " update Embarques set OID_CIDADE_APOIO = " + ed.getOID_Cidade();
        sql += " where oid_Embarque = " + ed.getOID_Embarque();
      }

      i = executasql.executarUpdate(sql);
      
      if (edParametro_Fixo.getDM_Gera_Ocorrencia_Viagem().equals("S")) {

          sql =  " SELECT OID_Manifesto, oid_cidade, oid_cidade_destino from Embarques ";
          sql += " WHERE oid_Embarque = '" + ed.getOID_Embarque() + "'";
//// System.out.println(sql);
          ResultSet res2 = this.executasql.executarConsulta(sql);

          FormataDataBean DataFormatada = new FormataDataBean();

          //popula
          while (res2.next()){
              CidadeBean cO = CidadeBean.getByOID(res2.getInt("oid_cidade"));
              CidadeBean cD = CidadeBean.getByOID(res2.getInt("oid_cidade_destino"));
              
              Manifesto_InternacionalBD manIntBD = new Manifesto_InternacionalBD(this.executasql);
              Manifesto_InternacionalED ocorrencia = new Manifesto_InternacionalED();
             
              ocorrencia.setDT_Chegada(ed.getDT_Ocorrencia_Embarque());
              ocorrencia.setHR_Chegada(ed.getHR_Ocorrencia_Embarque());
              ocorrencia.setOID_Tipo_Ocorrencia(ed.getOID_Tipo_Ocorrencia());
              ocorrencia.setOID_Manifesto_Internacional(res2.getString("oid_Manifesto"));
              ocorrencia.setTX_Observacao1(ed.getTX_Observacao());
              ocorrencia.setNM_Pessoa_Entrega("- EMBARQUE -");
              //ocorrencia.setDM_Tipo(ed.getDM_Tipo());
              manIntBD.inclui_Ocorrencia(ocorrencia);
// SEMPRE MIC              
//              if (cO.getOID_Pais() != 1 || cD.getOID_Pais() != 1){
//                  Manifesto_InternacionalBD manIntBD = new Manifesto_InternacionalBD(this.executasql);
//                  Manifesto_InternacionalED ocorrencia = new Manifesto_InternacionalED();
//                  
//                  ocorrencia.setDT_Chegada(ed.getDT_Ocorrencia_Embarque());
//                  ocorrencia.setHR_Chegada(ed.getHR_Ocorrencia_Embarque());
//                  ocorrencia.setOID_Tipo_Ocorrencia(ed.getOID_Tipo_Ocorrencia());
//                  ocorrencia.setOID_Manifesto_Internacional(res2.getString("oid_Manifesto"));
//                  ocorrencia.setTX_Observacao1(ed.getTX_Observacao());
//                  ocorrencia.setNM_Pessoa_Entrega("- EMBARQUE -");
//                  //ocorrencia.setDM_Tipo(ed.getDM_Tipo());
//                  manIntBD.inclui_Ocorrencia(ocorrencia);
//              } else {
//                  ManifestoBD manBD = new ManifestoBD(this.executasql);
//                  ManifestoED ocorrencia = new ManifestoED();
//                  
//                  ocorrencia.setDT_Chegada(ed.getDT_Ocorrencia_Embarque());
//                  ocorrencia.setHR_Chegada(ed.getHR_Ocorrencia_Embarque());
//                  ocorrencia.setOID_Tipo_Ocorrencia(ed.getOID_Tipo_Ocorrencia());
//                  ocorrencia.setOID_Manifesto(res2.getString("oid_Manifesto"));
//                  ocorrencia.setTX_Observacao(ed.getTX_Observacao());
//                  ocorrencia.setNM_Pessoa_Entrega("- EMBARQUE -");
//                  //ocorrencia.setDM_Tipo(ed.getDM_Tipo());
//                  manBD.inclui_Ocorrencia(ocorrencia);
//              }
              
           }
      }

      ocorrencia_EmbarqueED.setOID_Ocorrencia_Embarque(ed.getOID_Ocorrencia_Embarque());

    }
      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao incluir Ocorrência Embarque");
        excecoes.setMetodo("inclui");
        excecoes.setExc(exc);
        throw excecoes;
      }
      return ocorrencia_EmbarqueED;
  }

  public void altera(Ocorrencia_EmbarqueED ed) throws Excecoes{

    String sql = null;

    try{

      sql = " update Ocorrencias_Embarques set OID_Tipo_Ocorrencia= " + ed.getOID_Tipo_Ocorrencia() + ", TX_Observacao = '" + ed.getTX_Observacao() + "'" + ", DT_Ocorrencia_Embarque = '" + ed.getDT_Ocorrencia_Embarque() + "'" + ", HR_Ocorrencia_Embarque = '" + ed.getHR_Ocorrencia_Embarque() + "'" + ", nr_odometro_parcial = " + ed.getNR_Odometro_Parcial();
      sql += " where oid_Ocorrencia_Embarque = " + ed.getOID_Ocorrencia_Embarque();

//////// // System.out.println(sql);

      int i = executasql.executarUpdate(sql);

      if( ed.getDT_Ocorrencia_Embarque() != null && ed.getHR_Ocorrencia_Embarque() != null
          && ed.getTX_Observacao() != null ){
        sql = " update Embarques set OID_CIDADE_APOIO = " + ed.getOID_Cidade() +",";
        sql += " dt_ultimo_contato = '"+ed.getDT_Ocorrencia_Embarque()+"',";
        sql += " hr_ultimo_contato = '"+ed.getHR_Ocorrencia_Embarque()+"',";
        sql += " tx_ultima_observacao = '"+ed.getTX_Observacao()+"'";
        sql += " where oid_Embarque = " + ed.getOID_Embarque();
      }else{
        sql = " update Embarques set OID_CIDADE_APOIO = " + ed.getOID_Cidade();
        sql += " where oid_Embarque = " + ed.getOID_Embarque();
      }

      i = executasql.executarUpdate(sql);

    }

      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao alterar Ocorrência Embarque");
        excecoes.setMetodo("alterar");
        excecoes.setExc(exc);
        throw excecoes;
      }
  }

  public void deleta(Ocorrencia_EmbarqueED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from Ocorrencias_Embarques WHERE oid_Ocorrencia_Embarque = ";
      sql += "(" + ed.getOID_Ocorrencia_Embarque() + ")";

      int i = executasql.executarUpdate(sql);
    }

      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao excluir Ocorrência Embarque");
        excecoes.setMetodo("excluir");
        excecoes.setExc(exc);
        throw excecoes;
      }
  }

  public ArrayList lista(Ocorrencia_EmbarqueED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();

    try{

      sql =  "SELECT "+
      " Embarques.OID_Embarque,  "+
      " Ocorrencias_Embarques.OID_Ocorrencia_Embarque,  "+
      " Ocorrencias_Embarques.DT_Ocorrencia_Embarque,  "+
      " Ocorrencias_Embarques.HR_Ocorrencia_Embarque,  "+
      " Ocorrencias_Embarques.DT_Ocorrencia_Lancada,  "+
      " Ocorrencias_Embarques.HR_Ocorrencia_Lancada,  "+
      " Ocorrencias_Embarques.TX_Descricao,  "+
      " Ocorrencias_Embarques.TX_Observacao,  "+
      " Ocorrencias_Embarques.nr_odometro_parcial, "+ //éeo 16/09
      " Tipos_Ocorrencias.NM_Tipo_Ocorrencia,  "+
      " Embarques.NR_Embarque,  "+
      " Embarques.NR_Placa,  "+
      " Embarques.NM_Motorista, "+
      " Embarques.NR_Celular,  "+
      " Embarques.DM_Expresso,  "+
      " Embarques.DM_Situacao, "+
      " Embarques.DT_Previsao_Chegada, "+
      " Embarques.HR_Previsao_Chegada, "+
      " Embarques.DT_Chegada, "+
      " Embarques.HR_Chegada, "+
      " Embarques.DT_Emissao,  "+
      " Embarques.nr_odometro_inicial, "+ //eeo 16/09
      " Cidade_Origem.NM_Cidade as NM_Cidade_Origem, "+
      " Cidade_Destino.NM_Cidade as NM_Cidade_Destino  "+
      " from  "+
      " Embarques,  "+
      " Ocorrencias_Embarques,  "+
      " Tipos_Ocorrencias, "+
      " Cidades Cidade_Origem,  "+
      " Cidades Cidade_Destino "; 
      sql += " WHERE Embarques.OID_Cidade_Destino = Cidade_Destino.OID_Cidade ";
      sql += " AND Embarques.OID_Cidade = Cidade_Origem.OID_Cidade ";
      sql += " AND Ocorrencias_Embarques.OID_Embarque = Embarques.OID_Embarque ";
      sql += " AND Ocorrencias_Embarques.OID_Tipo_Ocorrencia = Tipos_Ocorrencias.OID_Tipo_Ocorrencia ";

      if (String.valueOf(ed.getNR_Embarque()) != null &&
          !String.valueOf(ed.getNR_Embarque()).equals("0") &&
          !String.valueOf(ed.getNR_Embarque()).equals("null")){
        sql += " and Embarques.NR_Embarque = " + ed.getNR_Embarque();
      }
      
      if (String.valueOf(ed.getOID_Manifesto()) != null &&
            !String.valueOf(ed.getOID_Manifesto()).equals("") &&
            !String.valueOf(ed.getOID_Manifesto()).equals("null")){
          sql += " and Embarques.OID_Manifesto = '" + ed.getOID_Manifesto() + "'";
        }
  
      if (String.valueOf(ed.getOID_Embarque()) != null &&
          !String.valueOf(ed.getOID_Embarque()).equals("0") &&
          !String.valueOf(ed.getOID_Embarque()).equals("null")){
        sql += " and Embarques.oid_Embarque = " + ed.getOID_Embarque();
      }
      if (String.valueOf(ed.getOID_Tipo_Ocorrencia()) != null &&
          !String.valueOf(ed.getOID_Tipo_Ocorrencia()).equals("0") &&
          !String.valueOf(ed.getOID_Tipo_Ocorrencia()).equals("null")){
        sql += " and Tipos_Ocorrencias.OID_Tipo_Ocorrencia = " + ed.getOID_Tipo_Ocorrencia();
      }

      if (String.valueOf(ed.getDT_Emissao()) != null &&
          !String.valueOf(ed.getDT_Emissao()).equals("") &&
          !String.valueOf(ed.getDT_Emissao()).equals("null")){
        sql += " and Embarques.DT_Emissao = '" + ed.getDT_Emissao() + "'";
      }
      if (String.valueOf(ed.getDT_Ocorrencia_Embarque()) != null &&
          !String.valueOf(ed.getDT_Ocorrencia_Embarque()).equals("") &&
          !String.valueOf(ed.getDT_Ocorrencia_Embarque()).equals("null")){
        sql += " and Ocorrencias_Embarques.DT_Ocorrencia_Embarque = '" + ed.getDT_Ocorrencia_Embarque() + "'";
      }

      sql += " Order by Ocorrencias_Embarques.OID_Ocorrencia_Embarque, Ocorrencias_Embarques.DT_Ocorrencia_Embarque, Ocorrencias_Embarques.HR_Ocorrencia_Embarque asc";

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      FormataDataBean DataFormatada = new FormataDataBean();

      //popula
      while (res.next()){
        Ocorrencia_EmbarqueED edVolta = new Ocorrencia_EmbarqueED();
	DataFormatada = new FormataDataBean();

        edVolta.setOID_Ocorrencia_Embarque(res.getLong("OID_Ocorrencia_Embarque"));

        edVolta.setDT_Ocorrencia_Embarque(res.getString("DT_Ocorrencia_Embarque"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Ocorrencia_Embarque());
        edVolta.setDT_Ocorrencia_Embarque(DataFormatada.getDT_FormataData());

        edVolta.setHR_Ocorrencia_Embarque(res.getString("HR_Ocorrencia_Embarque"));
        if (edVolta.getHR_Ocorrencia_Embarque()==null){
          edVolta.setHR_Ocorrencia_Embarque("");
        }

        edVolta.setDT_Ocorrencia_Lancada(res.getString("DT_Ocorrencia_Lancada"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Ocorrencia_Lancada());
        edVolta.setDT_Ocorrencia_Lancada(DataFormatada.getDT_FormataData());

        edVolta.setHR_Ocorrencia_Lancada(res.getString("HR_Ocorrencia_Lancada"));
        if (edVolta.getHR_Ocorrencia_Lancada()==null){
          edVolta.setHR_Ocorrencia_Lancada("");
        }
        edVolta.setNR_Embarque(res.getLong("NR_Embarque"));

        edVolta.setDT_Emissao(res.getString("DT_Emissao"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
        edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());

        edVolta.setNM_Tipo_Ocorrencia(res.getString("TX_Descricao"));
        if (edVolta.getNM_Tipo_Ocorrencia()==null){
          edVolta.setNM_Tipo_Ocorrencia("");
        }

        edVolta.setTX_Observacao(res.getString("TX_Observacao"));
        if (edVolta.getTX_Observacao()==null){
          edVolta.setTX_Observacao("");
        }

	edVolta.setNR_Odometro_Parcial(res.getLong("nr_odometro_parcial")); //eeo 16/09

        list.add(edVolta);
      }
    }
      catch(Exception exc){
    	  exc.printStackTrace();
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao listar Ocorrência Embarque");
        excecoes.setMetodo("listar");
        excecoes.setExc(exc);
        throw excecoes;
      }

    return list;
  }

  public Ocorrencia_EmbarqueED getByRecord(Ocorrencia_EmbarqueED ed)throws Excecoes{

    String sql = null;

    Ocorrencia_EmbarqueED edVolta = new Ocorrencia_EmbarqueED();

    try{

      sql =  " SELECT Ocorrencias_Embarques.OID_Cidade, Ocorrencias_Embarques.TX_Descricao, OID_Ocorrencia_Embarque, OID_Tipo_Ocorrencia, DT_Ocorrencia_Embarque, HR_Ocorrencia_Embarque, DT_Ocorrencia_Lancada, HR_Ocorrencia_Lancada, Ocorrencias_Embarques.TX_Observacao, Ocorrencias_Embarques.nr_odometro_parcial, Embarques.oid_Embarque, Embarques.NR_Embarque, Embarques.DT_Emissao from Ocorrencias_Embarques, Embarques ";
      sql += " WHERE Ocorrencias_Embarques.OID_Embarque = Embarques.OID_Embarque ";

      if (String.valueOf(ed.getOID_Ocorrencia_Embarque()) != null &&
          !String.valueOf(ed.getOID_Ocorrencia_Embarque()).equals("0")){
        sql += " and OID_Ocorrencia_Embarque = " + ed.getOID_Ocorrencia_Embarque();
      }
      FormataDataBean DataFormatada = new FormataDataBean();

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);
      while (res.next()){
        edVolta.setOID_Ocorrencia_Embarque(res.getLong("OID_Ocorrencia_Embarque"));
        edVolta.setOID_Tipo_Ocorrencia(res.getLong("OID_Tipo_Ocorrencia"));
        edVolta.setOID_Embarque(new Long(res.getString("OID_Embarque")).longValue());
        edVolta.setOID_Cidade(new Long(res.getString("OID_Cidade")).longValue());
        edVolta.setNR_Embarque(res.getLong("NR_Embarque"));

        edVolta.setDT_Ocorrencia_Embarque(res.getString("DT_Ocorrencia_Embarque"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Ocorrencia_Embarque());
        edVolta.setDT_Ocorrencia_Embarque(DataFormatada.getDT_FormataData());

        edVolta.setHR_Ocorrencia_Embarque(res.getString("HR_Ocorrencia_Embarque"));
        edVolta.setDT_Ocorrencia_Lancada(res.getString("DT_Ocorrencia_Lancada"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Ocorrencia_Lancada());
        edVolta.setDT_Ocorrencia_Lancada(DataFormatada.getDT_FormataData());

        edVolta.setHR_Ocorrencia_Lancada(res.getString("HR_Ocorrencia_Lancada"));
        edVolta.setTX_Observacao(res.getString("TX_Observacao"));

        edVolta.setNM_Cidade(res.getString("TX_Descricao"));
	edVolta.setNR_Odometro_Parcial(res.getLong("nr_odometro_parcial"));

        edVolta.setDT_Emissao(res.getString("DT_Emissao"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
        edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());

      }
    }
      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao selecionar Ocorrência Embarque");
        excecoes.setMetodo("seleção");
        excecoes.setExc(exc);
        throw excecoes;
      }

    return edVolta;
  }

  public void geraRelatorio(Ocorrencia_EmbarqueED ed)throws Excecoes{

    String sql = null;

    Ocorrencia_EmbarqueED edVolta = new Ocorrencia_EmbarqueED();

    try{

      sql = "select * from Ocorrencia_Embarques where oid_Ocorrencia_Embarque > 0";

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      Ocorrencia_EmbarqueRL Ocorrencia_Embarque_rl = new Ocorrencia_EmbarqueRL();
      Ocorrencia_Embarque_rl.geraRelatEstoque(res);
    }
    catch (Excecoes e){
      throw e;
    }
    catch(Exception exc){
      Excecoes exce = new Excecoes();
      exce.setExc(exc);
      exce.setMensagem("Erro no método listar");
      exce.setClasse(this.getClass().getName());
      exce.setMetodo("geraRelatorio(Ocorrencia_EmbarqueED ed)");
    }

  }

}
