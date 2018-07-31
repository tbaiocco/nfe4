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

import com.master.ed.Conhecimento_RomaneioED;
import com.master.rl.Conhecimento_RomaneioRL;
import com.master.root.FormataDataBean;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

public class Conhecimento_RomaneioBD {

  private ExecutaSQL executasql;

  public Conhecimento_RomaneioBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public void inclui(Conhecimento_RomaneioED ed) throws Excecoes{

    String sql = null;
    long valOid = 0;
    String chave = null;

    try{

      chave = (ed.getOID_Conhecimento() + ed.getOID_Romaneio_Entrega());

      sql = " insert into Conhecimentos_Romaneios (OID_Conhecimento_Romaneio, OID_Conhecimento, OID_Romaneio_Entrega, DT_Conhecimento_Romaneio, HR_Conhecimento_Romaneio ) values ";
      sql += "('" + chave + "','" + ed.getOID_Conhecimento() + "','" + ed.getOID_Romaneio_Entrega() + "','"  + ed.getDT_Conhecimento_Romaneio() + "','" + ed.getHR_Conhecimento_Romaneio() + "')";

      //invoca o metodo executarupdate do objeto
      //executasql. que eh uma referencia ao
      //objeto ExecutSQL, que contem a conexao ativa
      int i = executasql.executarUpdate(sql);
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir");
      excecoes.setMetodo("inclui");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void altera(Conhecimento_RomaneioED ed) throws Excecoes{

    String sql = null;

    try{

      sql = " update Conhecimentos_Romaneios set OID_Romaneio_Entrega= " + ed.getOID_Romaneio_Entrega() + "'";
      sql += " where oid_Conhecimento_Romaneio = '" + ed.getOID_Conhecimento_Romaneio()+ "'";

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

  public void deleta(Conhecimento_RomaneioED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from Conhecimentos_Romaneios WHERE oid_Conhecimento_Romaneio = ";
      sql += "('" + ed.getOID_Conhecimento_Romaneio() + "')";

      int i = executasql.executarUpdate(sql);
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

  public ArrayList lista(Conhecimento_RomaneioED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();

    try{
      sql =  " SELECT OID_Conhecimento_Romaneio, DT_Conhecimento_Romaneio, HR_Conhecimento_Romaneio, Conhecimentos.NR_Conhecimento, Conhecimentos.DT_Emissao, Romaneios_Entregas.NR_Romaneio_Entrega, Unidades.CD_Unidade, Pessoa_Remetente.NM_Razao_Social as NM_Razao_Social_Remetente, Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario from Conhecimentos_Romaneios, Conhecimentos, Unidades, Pessoas Pessoa_Remetente, Pessoas Pessoa_Destinatario, Romaneios_Entregas ";
      sql += " WHERE Unidades.oid_Unidade = Conhecimentos.oid_Unidade ";
      sql += " AND Conhecimentos.oid_Pessoa = Pessoa_Remetente.oid_Pessoa ";
      sql += " AND Conhecimentos.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa ";
      sql += " AND Conhecimentos_Romaneios.OID_Conhecimento = Conhecimentos.OID_Conhecimento ";
      sql += " AND Conhecimentos_Romaneios.OID_Romaneio_Entrega = Romaneios_Entregas.OID_Romaneio_Entrega ";

      if (String.valueOf(ed.getNR_Conhecimento()) != null &&
          !String.valueOf(ed.getNR_Conhecimento()).equals("0") &&
          !String.valueOf(ed.getNR_Conhecimento()).equals("null")){
        sql += " and Conhecimentos.NR_Conhecimento = " + ed.getNR_Conhecimento();
      }
      if (String.valueOf(ed.getOID_Romaneio_Entrega()) != null &&
          !String.valueOf(ed.getOID_Romaneio_Entrega()).equals("0") &&
          !String.valueOf(ed.getOID_Romaneio_Entrega()).equals("null")){
        sql += " and Romaneios_Entregas.OID_Romaneio_Entrega = '" + ed.getOID_Romaneio_Entrega() + "'";
      }

      if (String.valueOf(ed.getOID_Unidade()) != null &&
          !String.valueOf(ed.getOID_Unidade()).equals("0") &&
          !String.valueOf(ed.getOID_Unidade()).equals("null")){
        sql += " and Conhecimentos.OID_Unidade = " + ed.getOID_Unidade();
      }
      if (String.valueOf(ed.getOID_Pessoa()) != null &&
          !String.valueOf(ed.getOID_Pessoa()).equals("") &&
          !String.valueOf(ed.getOID_Pessoa()).equals("null")){
        sql += " and Conhecimentos.OID_Pessoa = '" + ed.getOID_Pessoa() + "'";
      }
      if (String.valueOf(ed.getOID_Pessoa_Destinatario()) != null &&
          !String.valueOf(ed.getOID_Pessoa_Destinatario()).equals("") &&
          !String.valueOf(ed.getOID_Pessoa_Destinatario()).equals("null")){
        sql += " and Conhecimentos.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario() + "'";
      }
      if (String.valueOf(ed.getDT_Emissao()) != null &&
          !String.valueOf(ed.getDT_Emissao()).equals("") &&
          !String.valueOf(ed.getDT_Emissao()).equals("null")){
        sql += " and Conhecimentos.DT_Emissao = '" + ed.getDT_Emissao() + "'";
      }
      if (String.valueOf(ed.getDT_Conhecimento_Romaneio()) != null &&
          !String.valueOf(ed.getDT_Conhecimento_Romaneio()).equals("") &&
          !String.valueOf(ed.getDT_Conhecimento_Romaneio()).equals("null")){
        sql += " and Conhecimentos_Romaneios.DT_Conhecimento_Romaneio = '" + ed.getDT_Conhecimento_Romaneio() + "'";
      }

      sql += " Order by Conhecimentos.NR_Conhecimento ";


      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      FormataDataBean DataFormatada = new FormataDataBean();

      //popula
      while (res.next()){
        Conhecimento_RomaneioED edVolta = new Conhecimento_RomaneioED();

        edVolta.setOID_Conhecimento_Romaneio(res.getString("OID_Conhecimento_Romaneio"));
        edVolta.setDT_Conhecimento_Romaneio(res.getString("DT_Conhecimento_Romaneio"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Conhecimento_Romaneio());
        edVolta.setDT_Conhecimento_Romaneio(DataFormatada.getDT_FormataData());

        edVolta.setHR_Conhecimento_Romaneio(res.getString("HR_Conhecimento_Romaneio"));
        edVolta.setNR_Conhecimento(res.getLong("NR_Conhecimento"));

        edVolta.setDT_Emissao(res.getString("DT_Emissao"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
        edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());

        edVolta.setNR_Romaneio_Entrega(res.getLong("NR_Romaneio_Entrega"));
        edVolta.setCD_Unidade(res.getString("CD_Unidade"));
        edVolta.setNM_Pessoa_Remetente(res.getString("NM_Razao_Social_Remetente"));
        edVolta.setNM_Pessoa_Destinatario(res.getString("NM_Razao_Social_Destinatario"));

        list.add(edVolta);
      }
      //// System.out.println("retorn");

    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao listar");
      excecoes.setMetodo("listar");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return list;
  }

  public Conhecimento_RomaneioED getByRecord(Conhecimento_RomaneioED ed)throws Excecoes{

    String sql = null;

    Conhecimento_RomaneioED edVolta = new Conhecimento_RomaneioED();

    try{

      sql =  " SELECT OID_Conhecimento_Romaneio, Conhecimentos_Romaneios.OID_Romaneio_Entrega, DT_Conhecimento_Romaneio, HR_Conhecimento_Romaneio, Conhecimentos.oid_Conhecimento, Conhecimentos.oid_Pessoa, Conhecimentos.oid_Pessoa_Destinatario, Conhecimentos.NR_Conhecimento, Conhecimentos.DT_Emissao, Romaneios_Entregas.NR_Romaneio_Entrega, Conhecimentos.oid_Unidade from Conhecimentos_Romaneios, Conhecimentos, Romaneios_Entregas ";
      sql += " WHERE Conhecimentos_Romaneios.OID_Conhecimento = Conhecimentos.OID_Conhecimento ";
      sql += " AND Conhecimentos_Romaneios.OID_Romaneio_Entrega = Romaneios_Entregas.OID_Romaneio_Entrega ";

      if (String.valueOf(ed.getOID_Conhecimento_Romaneio()) != null &&
          !String.valueOf(ed.getOID_Conhecimento_Romaneio()).equals("0")){
        sql += " and OID_Conhecimento_Romaneio = '" + ed.getOID_Conhecimento_Romaneio() + "'";
      }

      FormataDataBean DataFormatada = new FormataDataBean();
      //// System.out.println(sql);


      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);
      while (res.next()){
      //// System.out.println("tem");

        edVolta.setOID_Conhecimento_Romaneio(res.getString("OID_Conhecimento_Romaneio"));
        edVolta.setOID_Romaneio_Entrega(res.getString("OID_Romaneio_Entrega"));
        edVolta.setOID_Conhecimento(res.getString("OID_Conhecimento"));
        edVolta.setOID_Pessoa(res.getString("OID_Pessoa"));
        edVolta.setOID_Pessoa_Destinatario(res.getString("OID_Pessoa_Destinatario"));
        edVolta.setNR_Conhecimento(res.getLong("NR_Conhecimento"));
        edVolta.setNR_Romaneio_Entrega(res.getLong("NR_Romaneio_Entrega"));

        edVolta.setDT_Conhecimento_Romaneio(res.getString("DT_Conhecimento_Romaneio"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Conhecimento_Romaneio());
        edVolta.setDT_Conhecimento_Romaneio(DataFormatada.getDT_FormataData());

        edVolta.setHR_Conhecimento_Romaneio(res.getString("HR_Conhecimento_Romaneio"));

        edVolta.setDT_Emissao(res.getString("DT_Emissao"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
        edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());

        edVolta.setOID_Unidade(res.getLong("oid_Unidade"));
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao selecionar");
      excecoes.setMetodo("selecionar");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return edVolta;
  }

  public void geraRelatorio(Conhecimento_RomaneioED ed)throws Excecoes{

    String sql = null;

    Conhecimento_RomaneioED edVolta = new Conhecimento_RomaneioED();

    try{

      sql = "select * from Conhecimento_Romaneios where oid_Conhecimento_Romaneio > 0";

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      Conhecimento_RomaneioRL Conhecimento_Romaneio_rl = new Conhecimento_RomaneioRL();
      Conhecimento_Romaneio_rl.geraRelatEstoque(res);
    }
    catch (Excecoes e){
      throw e;
    }
    catch(Exception exc){
      Excecoes exce = new Excecoes();
      exce.setExc(exc);
      exce.setMensagem("Erro no método listar");
      exce.setClasse(this.getClass().getName());
      exce.setMetodo("geraRelatorio(Conhecimento_RomaneioED ed)");
    }

  }

}