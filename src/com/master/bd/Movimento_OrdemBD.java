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

import com.master.ed.Movimento_OrdemED;
import com.master.root.FormataDataBean;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

public class Movimento_OrdemBD {

  private ExecutaSQL executasql;

  public Movimento_OrdemBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public void inclui(Movimento_OrdemED ed) throws Excecoes{

    String sql = null;
    long valOid = 0;
    String chave = null;

    try{

      chave = (ed.getOID_Compromisso() + ed.getOID_Ordem_Frete());

      sql = " insert into Ordens_Compromissos (OID_Movimento_Ordem, OID_Compromisso, OID_Ordem_Frete, DT_Movimento_Ordem, HR_Movimento_Ordem ) values ";
      sql += "('" + chave + "','" + ed.getOID_Compromisso() + "','" + ed.getOID_Ordem_Frete() + "','"  + ed.getDT_Movimento_Ordem() + "','" + ed.getHR_Movimento_Ordem() + "')";

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

  public void altera(Movimento_OrdemED ed) throws Excecoes{

    String sql = null;

    try{

      sql = " update Ordens_Compromissos set OID_Ordem_Frete= '" + ed.getOID_Ordem_Frete() + "'";
      sql += " where oid_Movimento_Ordem = '" + ed.getOID_Movimento_Ordem() + "'";

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

  public void deleta(Movimento_OrdemED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from Ordens_Compromissos WHERE oid_Movimento_Ordem = ";
      sql += "('" + ed.getOID_Movimento_Ordem() + "')";

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

  public ArrayList lista(Movimento_OrdemED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();

    try{
      sql =  " SELECT OID_Movimento_Ordem, DT_Movimento_Ordem, HR_Movimento_Ordem, Compromissos.NR_Compromisso, Compromissos.DT_Emissao, Ordens_Fretes.NR_Ordem_Frete, Ordens_Fretes.OID_Ordem_Frete, Unidades.CD_Unidade from Ordens_Compromissos, Compromissos, Unidades, Ordens_Fretes ";
      sql += " WHERE Unidades.oid_Unidade = Compromissos.oid_Unidade ";
      sql += " AND Ordens_Compromissos.OID_Compromisso = Compromissos.OID_Compromisso ";
      sql += " AND Ordens_Compromissos.OID_Ordem_Frete = Ordens_Fretes.OID_Ordem_Frete ";

      if (String.valueOf(ed.getNR_Compromisso()) != null &&
          !String.valueOf(ed.getNR_Compromisso()).equals("0") &&
          !String.valueOf(ed.getNR_Compromisso()).equals("null")){
        sql += " and Compromissos.NR_Compromisso = " + ed.getNR_Compromisso();
      }
      if (String.valueOf(ed.getOID_Ordem_Frete()) != null &&
          !String.valueOf(ed.getOID_Ordem_Frete()).equals("") &&
          !String.valueOf(ed.getOID_Ordem_Frete()).equals("null")){
        sql += " and Ordens_Fretes.OID_Ordem_Frete = '" + ed.getOID_Ordem_Frete() + "'";
      }

      if (String.valueOf(ed.getOID_Unidade()) != null &&
          !String.valueOf(ed.getOID_Unidade()).equals("0") &&
          !String.valueOf(ed.getOID_Unidade()).equals("null")){
        sql += " and Compromissos.OID_Unidade = " + ed.getOID_Unidade();
      }
      if (String.valueOf(ed.getDT_Emissao()) != null &&
          !String.valueOf(ed.getDT_Emissao()).equals("") &&
          !String.valueOf(ed.getDT_Emissao()).equals("null")){
        sql += " and Compromissos.DT_Emissao = '" + ed.getDT_Emissao() + "'";
      }
      if (String.valueOf(ed.getDT_Movimento_Ordem()) != null &&
          !String.valueOf(ed.getDT_Movimento_Ordem()).equals("") &&
          !String.valueOf(ed.getDT_Movimento_Ordem()).equals("null")){
        sql += " and Ordens_Compromissos.DT_Movimento_Ordem = '" + ed.getDT_Movimento_Ordem() + "'";
      }

      sql += " Order by Compromissos.NR_Compromisso ";

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      FormataDataBean DataFormatada = new FormataDataBean();

      //popula
      while (res.next()){
        Movimento_OrdemED edVolta = new Movimento_OrdemED();

        edVolta.setOID_Movimento_Ordem(res.getString("OID_Movimento_Ordem"));
        edVolta.setOID_Ordem_Frete(res.getString("OID_Ordem_Frete"));

        edVolta.setDT_Movimento_Ordem(res.getString("DT_Movimento_Ordem"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Movimento_Ordem());
        edVolta.setDT_Movimento_Ordem(DataFormatada.getDT_FormataData());

        edVolta.setHR_Movimento_Ordem(res.getString("HR_Movimento_Ordem"));
        edVolta.setNR_Compromisso(res.getLong("NR_Compromisso"));

        edVolta.setDT_Emissao(res.getString("DT_Emissao"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
        edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());

        edVolta.setNR_Ordem_Frete(res.getLong("NR_Ordem_Frete"));
        edVolta.setCD_Unidade(res.getString("CD_Unidade"));

        list.add(edVolta);
      }
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

  public Movimento_OrdemED getByRecord(Movimento_OrdemED ed)throws Excecoes{

    String sql = null;

    Movimento_OrdemED edVolta = new Movimento_OrdemED();

    try{

      sql =  " SELECT OID_Movimento_Ordem, Ordens_Compromissos.OID_Ordem_Frete, DT_Movimento_Ordem, HR_Movimento_Ordem, Compromissos.oid_Compromisso, Compromissos.NR_Compromisso, Compromissos.DT_Emissao, Ordens_Fretes.NR_Ordem_Frete, Compromissos.oid_Unidade, Compromissos.oid_Cidade, Ordens_Fretes.oid_Veiculo from Ordens_Compromissos, Compromissos, Ordens_Fretes ";
      sql += " WHERE Ordens_Compromissos.OID_Compromisso = Compromissos.OID_Compromisso ";
      sql += " AND Ordens_Compromissos.OID_Ordem_Frete = Ordens_Fretes.OID_Ordem_Frete ";

      if (String.valueOf(ed.getOID_Movimento_Ordem()) != null &&
          !String.valueOf(ed.getOID_Movimento_Ordem()).equals("")){
        sql += " and OID_Movimento_Ordem = '" + ed.getOID_Movimento_Ordem() + "'";
      }

      FormataDataBean DataFormatada = new FormataDataBean();

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);
      while (res.next()){
        edVolta.setOID_Movimento_Ordem(res.getString("OID_Movimento_Ordem"));
        edVolta.setOID_Ordem_Frete(res.getString("OID_Ordem_Frete"));
        edVolta.setOID_Compromisso(new Integer(res.getInt("OID_Compromisso")));
        edVolta.setNR_Compromisso(res.getLong("NR_Compromisso"));
        edVolta.setNR_Ordem_Frete(res.getLong("NR_Ordem_Frete"));

        edVolta.setOID_Veiculo(res.getString("OID_Veiculo"));

        edVolta.setDT_Movimento_Ordem(res.getString("DT_Movimento_Ordem"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Movimento_Ordem());
        edVolta.setDT_Movimento_Ordem(DataFormatada.getDT_FormataData());

        edVolta.setHR_Movimento_Ordem(res.getString("HR_Movimento_Ordem"));

        edVolta.setDT_Emissao(res.getString("DT_Emissao"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
        edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());

        edVolta.setOID_Cidade_Destino(res.getLong("oid_Cidade"));
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
}