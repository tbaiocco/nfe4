package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.Nota_Fiscal_EmbarqueED;
import com.master.root.FormataDataBean;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

public class Nota_Fiscal_EmbarqueBD {

  private ExecutaSQL executasql;

  public Nota_Fiscal_EmbarqueBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public Nota_Fiscal_EmbarqueED inclui(Nota_Fiscal_EmbarqueED ed) throws Excecoes{

    String sql = null;
    long valOid = 0;
    String chave = null;
    Nota_Fiscal_EmbarqueED nota_Fiscal_EmbarqueED = new Nota_Fiscal_EmbarqueED();

    try{

      chave = (ed.getOID_Embarque() + ed.getOID_Nota_Fiscal());

      ed.setOID_Nota_Fiscal_Embarque(chave);

      sql = " insert into Notas_Fiscais_Embarques (OID_Nota_Fiscal_Embarque, OID_Embarque, OID_Nota_Fiscal, DT_Nota_Fiscal_Embarque, HR_Nota_Fiscal_Embarque ) values ";
      sql += "('" + ed.getOID_Nota_Fiscal_Embarque() + "'," + ed.getOID_Embarque() + ",'" + ed.getOID_Nota_Fiscal() +  "','"  + ed.getDT_Nota_Fiscal_Embarque() + "','" + ed.getHR_Nota_Fiscal_Embarque() + "')";
// System.out.println(sql);
      int i = executasql.executarUpdate(sql);

      sql = " update Notas_Fiscais set DM_Situacao= 'E'";
      sql += " where oid_Nota_Fiscal = '" + ed.getOID_Nota_Fiscal() + "'" ;
      // System.out.println(sql);

      int u = executasql.executarUpdate(sql);

      sql = " update Embarques set DM_Situacao = '5'" ;
      sql += " where oid_Embarque = " + ed.getOID_Embarque();
      // System.out.println(sql);

      u = executasql.executarUpdate(sql);

      nota_Fiscal_EmbarqueED.setOID_Nota_Fiscal_Embarque(ed.getOID_Nota_Fiscal_Embarque());
      nota_Fiscal_EmbarqueED.setOID_Embarque(ed.getOID_Embarque());

    }
      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao incluir");
        excecoes.setMetodo("inclui");
        excecoes.setExc(exc);
        throw excecoes;
      }
      return nota_Fiscal_EmbarqueED;
  }

  public void altera(Nota_Fiscal_EmbarqueED ed) throws Excecoes{

    String sql = null;

    try{

      sql = " update Notas_Fiscais_Embarques set ";
      sql += " where oid_Nota_Fiscal_Embarque = " + ed.getOID_Nota_Fiscal_Embarque();

      int i = executasql.executarUpdate(sql);
    }

      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao alterar");
        excecoes.setMetodo("alterar");
        excecoes.setExc(exc);
        throw excecoes;
      }
  }

  public void deleta(Nota_Fiscal_EmbarqueED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from Notas_Fiscais_Embarques WHERE oid_Nota_Fiscal_Embarque = ";
      sql += "('" + ed.getOID_Nota_Fiscal_Embarque() + "')";

      int i = executasql.executarUpdate(sql);

      sql = " update Notas_Fiscais set DM_Situacao= 'A'";
      sql += " where oid_Nota_Fiscal = '" + ed.getOID_Nota_Fiscal() + "'" ;

      int u = executasql.executarUpdate(sql);

    }

      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao excluir");
        excecoes.setMetodo("excluir");
        excecoes.setExc(exc);
        throw excecoes;
      }
  }

  public ArrayList lista(Nota_Fiscal_EmbarqueED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();

    try{
      sql =  " SELECT Notas_Fiscais_Embarques.OID_Nota_Fiscal_Embarque, Notas_Fiscais_Embarques.OID_Embarque, Embarques.NR_Embarque, Notas_Fiscais_Embarques.OID_Embarque, Notas_Fiscais_Embarques.DT_Nota_Fiscal_Embarque, Notas_Fiscais_Embarques.HR_Nota_Fiscal_Embarque, Notas_Fiscais_Embarques.DT_Previsao_Chegada, Notas_Fiscais_Embarques.HR_Previsao_Chegada, Notas_Fiscais.NR_Nota_Fiscal, Notas_Fiscais.DM_Situacao, Notas_Fiscais.DT_Entrada, Notas_Fiscais.DT_Emissao, Notas_Fiscais.NR_Volumes, Notas_Fiscais.NR_Peso, Notas_Fiscais.VL_Nota_Fiscal, Pessoa_Remetente.NM_Razao_Social as NM_Razao_Social_Remetente, Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario from Notas_Fiscais_Embarques, Notas_Fiscais, Pessoas Pessoa_Remetente, Pessoas Pessoa_Destinatario, Embarques ";
      sql += " WHERE Notas_Fiscais.oid_Pessoa = Pessoa_Remetente.oid_Pessoa ";
      sql += " AND Notas_Fiscais.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa ";
      sql += " AND Notas_Fiscais_Embarques.OID_Nota_Fiscal = Notas_Fiscais.OID_Nota_Fiscal ";
      sql += " AND Notas_Fiscais_Embarques.OID_Embarque = Embarques.OID_Embarque ";

      if (String.valueOf(ed.getNR_Nota_Fiscal()) != null &&
          !String.valueOf(ed.getNR_Nota_Fiscal()).equals("0") &&
          !String.valueOf(ed.getNR_Nota_Fiscal()).equals("null")){
        sql += " and Notas_Fiscais.NR_Nota_Fiscal = " + ed.getNR_Nota_Fiscal();
      }

      if (String.valueOf(ed.getOID_Embarque()) != null &&
          !String.valueOf(ed.getOID_Embarque()).equals("") &&
          !String.valueOf(ed.getOID_Embarque()).equals("null")){
        sql += " and Notas_Fiscais_Embarques.OID_Embarque = '" + ed.getOID_Embarque() + "'";
      }

      if (String.valueOf(ed.getOID_Manifesto()) != null &&
            !String.valueOf(ed.getOID_Manifesto()).equals("") &&
            !String.valueOf(ed.getOID_Manifesto()).equals("null")){
          sql += " and Embarques.OID_Manifesto = '" + ed.getOID_Manifesto() + "'";
        }

      if (String.valueOf(ed.getOid_Pessoa_Senha()) != null &&
          !String.valueOf(ed.getOid_Pessoa_Senha()).equals("") &&
          !String.valueOf(ed.getOid_Pessoa_Senha()).equals("null")){
	  sql += " AND (Notas_Fiscais.OID_PESSOA = '" + ed.getOid_Pessoa_Senha() + "'";
          sql += " OR Notas_Fiscais.OID_PESSOA_DESTINATARIO = '" + ed.getOid_Pessoa_Senha() + "'";
	  sql += " OR Notas_Fiscais.OID_PESSOA_CONSIGNATARIO = '" + ed.getOid_Pessoa_Senha() + "')";
      }

      sql += " Order by Notas_Fiscais.DM_Situacao, Notas_Fiscais.DT_Entrada asc, Notas_Fiscais.NR_Nota_Fiscal, Notas_Fiscais.OID_Pessoa desc";
      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      FormataDataBean DataFormatada = new FormataDataBean();

      //popula
      while (res.next()){
        Nota_Fiscal_EmbarqueED edVolta = new Nota_Fiscal_EmbarqueED();
        edVolta.setOID_Embarque(res.getString("OID_Embarque"));
        edVolta.setNR_Embarque(res.getLong("NR_Embarque"));
        edVolta.setDM_Situacao(res.getString("DM_Situacao"));

        edVolta.setOID_Nota_Fiscal_Embarque(res.getString("OID_Nota_Fiscal_Embarque"));
        edVolta.setDT_Nota_Fiscal_Embarque(res.getString("DT_Nota_Fiscal_Embarque"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Nota_Fiscal_Embarque());
        edVolta.setDT_Nota_Fiscal_Embarque(DataFormatada.getDT_FormataData());

        edVolta.setHR_Nota_Fiscal_Embarque(res.getString("HR_Nota_Fiscal_Embarque"));
        edVolta.setNR_Nota_Fiscal(res.getLong("NR_Nota_Fiscal"));

        edVolta.setDT_Emissao(res.getString("DT_Emissao"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
        edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());

        edVolta.setDT_Entrada(res.getString("DT_Entrada"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Entrada());
        edVolta.setDT_Entrada(DataFormatada.getDT_FormataData());

        edVolta.setNM_Pessoa_Remetente(res.getString("NM_Razao_Social_Remetente"));
        edVolta.setNM_Pessoa_Destinatario(res.getString("NM_Razao_Social_Destinatario"));
        edVolta.setNR_Peso(res.getDouble("NR_Peso"));
        edVolta.setNR_Volumes(res.getLong("NR_Volumes"));
        edVolta.setVL_Nota_Fiscal(res.getDouble("VL_Nota_Fiscal"));

	edVolta.setDT_Previsao_Chegada(res.getString("DT_Previsao_Chegada"));
	DataFormatada.setDT_FormataData(edVolta.getDT_Previsao_Chegada());
        edVolta.setDT_Previsao_Chegada(DataFormatada.getDT_FormataData());
	edVolta.setHR_Previsao_Chegada(res.getString("HR_Previsao_Chegada"));

        list.add(edVolta);
      }
    }
      catch(Exception exc){
    	  exc.printStackTrace();
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao listar");
        excecoes.setMetodo("listar");
        excecoes.setExc(exc);
        throw excecoes;
      }

    return list;
  }

  public Nota_Fiscal_EmbarqueED getByRecord(Nota_Fiscal_EmbarqueED ed)throws Excecoes{

    String sql = null;

    Nota_Fiscal_EmbarqueED edVolta = new Nota_Fiscal_EmbarqueED();

    try{

      sql =  " SELECT Notas_Fiscais.DM_Situacao, Notas_Fiscais.DM_Transferencia, Notas_Fiscais.OID_Nota_Fiscal, Notas_Fiscais_Embarques.DT_Nota_Fiscal_Embarque, Notas_Fiscais_Embarques.HR_Nota_Fiscal_Embarque, Notas_Fiscais_Embarques.DT_Previsao_Chegada, Notas_Fiscais_Embarques.HR_Previsao_Chegada, Notas_Fiscais.OID_Pessoa, Notas_Fiscais.OID_Pessoa_Destinatario, Notas_Fiscais.OID_Pessoa_Consignatario, Notas_Fiscais.DT_Emissao, Notas_Fiscais.NR_Nota_Fiscal, Notas_Fiscais.NR_Fatura, Pessoa_Remetente.NM_Razao_Social as NM_Razao_Social_Remetente, Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario, Embarques.NR_Embarque, Embarques.OID_Embarque, Embarques.DM_Situacao as DM_Situacao_Embarque, Notas_Fiscais_Embarques.OID_Nota_Fiscal_Embarque, Notas_Fiscais.NR_Peso, Notas_Fiscais.NR_Volumes, Notas_Fiscais.VL_Nota_Fiscal, Embarques.DT_Emissao as DT_Emissao_Embarque, Notas_Fiscais.NM_Serie, Notas_Fiscais.NR_Pedido, Notas_Fiscais.DT_Entrada, Notas_Fiscais.HR_Entrada from Notas_Fiscais_Embarques, Notas_Fiscais, Pessoas Pessoa_Remetente, Pessoas Pessoa_Destinatario, Embarques ";
      sql += " WHERE Notas_Fiscais.oid_Pessoa = Pessoa_Remetente.oid_Pessoa ";
      sql += " AND Notas_Fiscais.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa ";
      sql += " AND Notas_Fiscais_Embarques.OID_Nota_Fiscal = Notas_Fiscais.OID_Nota_Fiscal ";
      sql += " AND Notas_Fiscais_Embarques.OID_Embarque = Embarques.OID_Embarque ";

      if (String.valueOf(ed.getOID_Nota_Fiscal_Embarque()) != null &&
          !String.valueOf(ed.getOID_Nota_Fiscal_Embarque()).equals("")){
        sql += " AND Notas_Fiscais_Embarques.OID_Nota_Fiscal_Embarque = '" + ed.getOID_Nota_Fiscal_Embarque() + "'";
      }
      FormataDataBean DataFormatada = new FormataDataBean();

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);
      while (res.next()){

        edVolta.setDM_Transferencia(res.getString("DM_Transferencia"));
        edVolta.setDM_Situacao_Embarque(res.getString("DM_Situacao_Embarque"));
        edVolta.setDM_Situacao(res.getString("DM_Situacao"));
        edVolta.setOID_Nota_Fiscal(res.getString("OID_Nota_Fiscal"));

        edVolta.setDT_Nota_Fiscal_Embarque(res.getString("DT_Nota_Fiscal_Embarque"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Nota_Fiscal_Embarque());
        edVolta.setDT_Nota_Fiscal_Embarque(DataFormatada.getDT_FormataData());

        edVolta.setHR_Nota_Fiscal_Embarque(res.getString("HR_Nota_Fiscal_Embarque"));
        edVolta.setOID_Pessoa(res.getString("OID_Pessoa"));
        edVolta.setOID_Pessoa_Destinatario(res.getString("OID_Pessoa_Destinatario"));
        edVolta.setOID_Pessoa_Consignatario(res.getString("OID_Pessoa_Consignatario"));

        edVolta.setDT_Emissao(res.getString("DT_Emissao"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
        edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());

        edVolta.setNR_Nota_Fiscal(res.getLong("NR_Nota_Fiscal"));
        edVolta.setNM_Pessoa_Remetente(res.getString("NM_Razao_Social_Remetente"));
        edVolta.setNM_Pessoa_Destinatario(res.getString("NM_Razao_Social_Destinatario"));
        edVolta.setOID_Embarque(res.getString("OID_Embarque"));
        edVolta.setNR_Embarque(res.getLong("NR_Embarque"));
        edVolta.setOID_Nota_Fiscal_Embarque(res.getString("OID_Nota_Fiscal_Embarque"));
        edVolta.setNR_Peso(res.getDouble("NR_Peso"));
        edVolta.setNR_Volumes(res.getLong("NR_Volumes"));
        edVolta.setVL_Nota_Fiscal(res.getDouble("VL_Nota_Fiscal"));

        edVolta.setDT_Emissao_Embarque(res.getString("DT_Emissao_Embarque"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Emissao_Embarque());
        edVolta.setDT_Emissao_Embarque(DataFormatada.getDT_FormataData());

        edVolta.setNM_Serie(res.getString("NM_Serie"));
        edVolta.setNR_Pedido(res.getString("NR_Pedido"));
        edVolta.setDT_Entrada(res.getString("DT_Entrada"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Entrada());
        edVolta.setDT_Entrada(DataFormatada.getDT_FormataData());

        edVolta.setHR_Entrada(res.getString("HR_Entrada"));

	edVolta.setDT_Previsao_Chegada(res.getString("DT_Previsao_Chegada"));
	DataFormatada.setDT_FormataData(edVolta.getDT_Previsao_Chegada());
        edVolta.setDT_Previsao_Chegada(DataFormatada.getDT_FormataData());
	edVolta.setHR_Previsao_Chegada(res.getString("HR_Previsao_Chegada"));
//fatura
	edVolta.setNR_Fatura(res.getString("NR_Fatura"));

      }
    }
      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao selecionar");
        excecoes.setMetodo("sele��o");
        excecoes.setExc(exc);
        throw excecoes;
      }

    return edVolta;
  }

  public void geraRelatorio(Nota_Fiscal_EmbarqueED ed)throws Excecoes{

    String sql = null;

    Nota_Fiscal_EmbarqueED edVolta = new Nota_Fiscal_EmbarqueED();

    try{

      sql = "select * from Nota_Fiscal_Embarques where oid_Nota_Fiscal_Embarque > 0";

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

    }
    catch (Excecoes e){
      throw e;
    }
    catch(Exception exc){
      Excecoes exce = new Excecoes();
      exce.setExc(exc);
      exce.setMensagem("Erro no m�todo listar");
      exce.setClasse(this.getClass().getName());
      exce.setMetodo("geraRelatorio(Nota_Fiscal_EmbarqueED ed)");
    }

  }

}
