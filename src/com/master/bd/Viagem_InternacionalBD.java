package com.master.bd;

import com.master.util.*;
import com.master.util.bd.*;
import com.master.rl.*;
import com.master.ed.Viagem_InternacionalED;
import java.util.*;
import java.sql.*;

import javax.servlet.http.HttpServletResponse;
import com.master.util.Data;
import com.master.bd.CompromissoBD;
import com.master.root.FormataDataBean;
import com.master.ed.CompromissoED;
import com.master.util.ed.Parametro_FixoED;

public class Viagem_InternacionalBD{

  private ExecutaSQL executasql;
  Utilitaria util = new Utilitaria();

  public Viagem_InternacionalBD(ExecutaSQL sql) {
    this.executasql = sql;
  }


  public void altera(Viagem_InternacionalED ed) throws Excecoes{

    String sql = null;

    try{

      sql = " update Viagens_Internacionais set "+
//      " DT_Previsao_Chegada = '" + ed.getDT_Previsao_Chegada() + "',"+
//      " HR_Previsao_Chegada = '" + ed.getHR_Previsao_Chegada() + "',"+
      " TX_Observacao = '" + ed.getTX_Observacao() + "',"+
      " TX_Observacao2 = '" + ed.getTX_Observacao2() + "',"+
      " TX_Observacao3 = '" + ed.getTX_Observacao3() + "',"+
      " TX_Observacao4 = '" + ed.getTX_Observacao4() + "',"+
      " TX_Observacao5 = '" + ed.getTX_Observacao5() + "',"+
      " TX_Observacao6 = '" + ed.getTX_Observacao6() + "',"+
      " TX_Observacao7 = '" + ed.getTX_Observacao7() + "',"+
      " TX_Observacao8 = '" + ed.getTX_Observacao8() + "',"+
      " TX_Observacao9 = '" + ed.getTX_Observacao9() + "',"+
      " TX_Observacao10 = '" + ed.getTX_Observacao10() + "',"+
      " TX_Observacao11 = '" + ed.getTX_Observacao11() + "',"+
      " TX_Observacao12 = '" + ed.getTX_Observacao12() + "',"+
      " TX_Observacao13 = '" + ed.getTX_Observacao13() + "',"+
      " TX_Observacao14 = '" + ed.getTX_Observacao14() + "',"+
      " TX_Observacao15 = '" + ed.getTX_Observacao15() + "',"+
      " TX_Observacao16 = '" + ed.getTX_Observacao16() + "',"+
      " TX_Observacao17 = '" + ed.getTX_Observacao17() + "',"+
      " TX_Observacao18 = '" + ed.getTX_Observacao18() + "',"+
      " NM_Documento1 = '" + ed.getNM_Documento1() + "',"+
      " NM_Documento2 = '" + ed.getNM_Documento2() + "',"+
      " NM_Documento3 = '" + ed.getNM_Documento3() + "',"+
      " NM_Documento4 = '" + ed.getNM_Documento4() + "',"+
      " NM_Documento5 = '" + ed.getNM_Documento5() + "',"+
      " NM_Documento6 = '" + ed.getNM_Documento6() + "',"+
      " DM_Capa_Mic = '" + ed.getDM_Capa_Mic() + "',"+
      " ncm = '" + ed.getNCM() + "',"+
      " NM_Embalagem = '" + ed.getNM_Embalagem() + "',"+
      " VL_Total_Frete = " + ed.getVL_Total_Frete() + ","+
      " VL_Nota_Fiscal = " + ed.getVL_Nota_Fiscal() + ","+
      " VL_Seguro = " + ed.getVL_Seguro() + ","+
      " VL_Peso = " + ed.getVL_Peso() + ","+
      " VL_Peso_Cubado = " + ed.getVL_Peso_Cubado() + ","+
      " NR_Volumes = " + ed.getNR_Volumes();

      sql += " where oid_Viagem_Internacional = '" + ed.getOID_Viagem_Internacional() + "'";

// // System.out.println(sql);

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

  public void deleta(Viagem_InternacionalED ed) throws Excecoes{

    String sql = null;
    boolean DM_Lancado_Ordem_Frete = false;

    try{

      sql =  " SELECT DM_Lancado_Ordem_Frete from Manifestos_Internacionais ";
      sql += " where oid_Manifesto_Internacional = '" + ed.getOID_Manifesto_Internacional() + "'";

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      while (res.next()){
        if (res.getString("DM_Lancado_Ordem_Frete").equals("S")){
          DM_Lancado_Ordem_Frete = true;
        }
      }

      if (DM_Lancado_Ordem_Frete){
        Excecoes exc = new Excecoes();
        throw exc;
      }

      sql = "delete from Viagens_Internacionais WHERE oid_Viagem_Internacional = ";
      sql += "('" + ed.getOID_Viagem_Internacional() + "')";

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      if (DM_Lancado_Ordem_Frete){
        excecoes.setMensagem("Manifesto_Internacional lançado em ordem de frete - exclusão de CTRC não permitida...");
      }else{
        excecoes.setMensagem("Erro ao excluir");
      }
      excecoes.setMetodo("excluir");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public ArrayList lista(Viagem_InternacionalED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();

    try{
      sql =  " SELECT OID_Viagem_Internacional, DT_Viagem_Internacional, "+
      " HR_Viagem_Internacional, Viagens_Internacionais.DT_Previsao_Chegada, "+
      " Viagens_Internacionais.HR_Previsao_Chegada, "+
      " Conhecimentos_Internacionais.OID_Conhecimento, "+
      " Conhecimentos_Internacionais.NR_Conhecimento, "+
      " Conhecimentos_Internacionais.DT_Emissao, "+
      " Manifestos_Internacionais.NR_Manifesto_Internacional, "+
      " Unidades.CD_Unidade, Pessoa_Remetente.NM_Razao_Social as NM_Razao_Social_Remetente, "+
      " Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario, "+
      " Manifestos_Internacionais.OID_Manifesto_Internacional, "+
      " Cidades.NM_Cidade "+
      " from Viagens_Internacionais, Conhecimentos_Internacionais, "+
      " Unidades, Pessoas Pessoa_Remetente, Pessoas Pessoa_Destinatario, "+
      " Manifestos_Internacionais, Cidades ";
      sql += " WHERE Unidades.oid_Unidade = Conhecimentos_Internacionais.oid_Unidade ";
      sql += " AND Conhecimentos_Internacionais.oid_Pessoa = Pessoa_Remetente.oid_Pessoa ";
      sql += " AND Conhecimentos_Internacionais.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa ";
      sql += " AND Viagens_Internacionais.OID_Conhecimento = Conhecimentos_Internacionais.OID_Conhecimento ";
      sql += " AND Viagens_Internacionais.OID_Manifesto_Internacional = Manifestos_Internacionais.OID_Manifesto_Internacional ";
      sql += " AND Conhecimentos_Internacionais.OID_Cidade_Destino = Cidades.OID_Cidade ";

      if (String.valueOf(ed.getNR_Conhecimento_Internacional()) != null &&
          !String.valueOf(ed.getNR_Conhecimento_Internacional()).equals("0") &&
          !String.valueOf(ed.getNR_Conhecimento_Internacional()).equals("null")){
        sql += " and Conhecimentos_Internacionais.NR_Conhecimento = " + ed.getNR_Conhecimento_Internacional();
      }
      if (String.valueOf(ed.getOID_Manifesto_Internacional()) != null &&
          !String.valueOf(ed.getOID_Manifesto_Internacional()).equals("0") &&
          !String.valueOf(ed.getOID_Manifesto_Internacional()).equals("null")){
        sql += " and Manifestos_Internacionais.OID_Manifesto_Internacional = '" + ed.getOID_Manifesto_Internacional() + "'";
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
      if (String.valueOf(ed.getDT_Emissao()) != null &&
          !String.valueOf(ed.getDT_Emissao()).equals("") &&
          !String.valueOf(ed.getDT_Emissao()).equals("null")){
        sql += " and Conhecimentos_Internacionais.DT_Emissao = '" + ed.getDT_Emissao() + "'";
      }
      if (String.valueOf(ed.getDT_Viagem_Internacional()) != null &&
          !String.valueOf(ed.getDT_Viagem_Internacional()).equals("") &&
          !String.valueOf(ed.getDT_Viagem_Internacional()).equals("null")){
        sql += " and Viagens_Internacionais.DT_Viagem_Internacional = '" + ed.getDT_Viagem_Internacional() + "'";
      }

      sql += " Order by Viagens_Internacionais.Dt_Previsao_Chegada, Viagens_Internacionais.Hr_Previsao_Chegada ";
      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      FormataDataBean DataFormatada = new FormataDataBean();

      //popula
      while (res.next()){
        Viagem_InternacionalED edVolta = new Viagem_InternacionalED();

        edVolta.setOID_Viagem_Internacional(res.getString("OID_Viagem_Internacional"));

        edVolta.setDT_Viagem_Internacional(res.getString("DT_Viagem_Internacional"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Viagem_Internacional());
        edVolta.setDT_Viagem_Internacional(DataFormatada.getDT_FormataData());

        edVolta.setHR_Viagem_Internacional(res.getString("HR_Viagem_Internacional"));
        edVolta.setOID_Conhecimento_Internacional(res.getString("OID_Conhecimento"));
        edVolta.setNR_Conhecimento_Internacional(res.getLong("NR_Conhecimento"));

        edVolta.setDT_Emissao(res.getString("DT_Emissao"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
        edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());

        edVolta.setDT_Previsao_Chegada(res.getString("DT_Previsao_Chegada"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Previsao_Chegada());
        edVolta.setDT_Previsao_Chegada(DataFormatada.getDT_FormataData());
        edVolta.setHR_Previsao_Chegada(res.getString("HR_Previsao_Chegada"));



        edVolta.setNR_Manifesto_Internacional(res.getLong("NR_Manifesto_Internacional"));
        edVolta.setCD_Unidade(res.getString("CD_Unidade"));
        edVolta.setNM_Pessoa_Remetente(res.getString("NM_Razao_Social_Remetente"));
        edVolta.setNM_Pessoa_Destinatario(res.getString("NM_Razao_Social_Destinatario"));
        edVolta.setNM_Cidade_Destino(res.getString("NM_Cidade"));
        edVolta.setOID_Manifesto_Internacional(res.getString("OID_Manifesto_Internacional"));
        list.add(edVolta);
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      exc.printStackTrace();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao listar");
      excecoes.setMetodo("listar");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return list;
  }

  public Viagem_InternacionalED getByRecord(Viagem_InternacionalED ed)throws Excecoes{

    String sql = null;

    Viagem_InternacionalED edVolta = new Viagem_InternacionalED();

    try{

      sql =  " SELECT OID_Viagem_Internacional, "+
        " Viagens_Internacionais.OID_Manifesto_Internacional, "+
        " DT_Viagem_Internacional, HR_Viagem_Internacional, "+
        " Viagens_Internacionais.DT_Previsao_Chegada, "+
        " Viagens_Internacionais.HR_Previsao_Chegada, "+
        " Viagens_Internacionais.TX_Observacao, "+
        " Viagens_Internacionais.TX_Observacao2, "+
        " Viagens_Internacionais.TX_Observacao3, "+
        " Viagens_Internacionais.TX_Observacao4, "+
        " Viagens_Internacionais.TX_Observacao5, "+
        " Viagens_Internacionais.TX_Observacao6, "+
		" Viagens_Internacionais.TX_Observacao7, "+
		" Viagens_Internacionais.TX_Observacao8, "+
		" Viagens_Internacionais.TX_Observacao9, "+
		" Viagens_Internacionais.TX_Observacao10, "+
		" Viagens_Internacionais.TX_Observacao11, "+
		" Viagens_Internacionais.TX_Observacao12, "+
		" Viagens_Internacionais.TX_Observacao13, "+
		" Viagens_Internacionais.TX_Observacao14, "+
		" Viagens_Internacionais.TX_Observacao15, "+
		" Viagens_Internacionais.TX_Observacao16, "+
		" Viagens_Internacionais.TX_Observacao17, "+
		" Viagens_Internacionais.TX_Observacao18, "+
        " Viagens_Internacionais.NM_Documento1, "+
        " Viagens_Internacionais.NM_Documento2, "+
        " Viagens_Internacionais.NM_Documento3, "+
        " Viagens_Internacionais.NM_Documento4, "+
        " Viagens_Internacionais.NM_Documento5, "+
        " Viagens_Internacionais.NM_Documento6, "+
        " Viagens_Internacionais.NCM, "+
        " Viagens_Internacionais.DM_Capa_Mic, "+
        " Viagens_Internacionais.VL_Total_Frete, "+
        " Viagens_Internacionais.VL_Nota_Fiscal, "+
        " Viagens_Internacionais.VL_Seguro, "+
        " Viagens_Internacionais.NM_Embalagem, "+
        " Viagens_Internacionais.NR_Volumes, "+
        " Viagens_Internacionais.VL_Peso, "+
        " Viagens_Internacionais.VL_Peso_Cubado, "+
        " Conhecimentos_Internacionais.OID_Conhecimento, "+
        " Conhecimentos_Internacionais.oid_Pessoa, "+
        " Conhecimentos_Internacionais.oid_Pessoa_Destinatario, "+
        " Conhecimentos_Internacionais.NR_Conhecimento, Conhecimentos_Internacionais.DT_Emissao, "+
        " Manifestos_Internacionais.NR_Manifesto_Internacional, "+
        " Conhecimentos_Internacionais.oid_Unidade from Viagens_Internacionais, "+
        " Conhecimentos_Internacionais, Manifestos_Internacionais ";
      sql += " WHERE Viagens_Internacionais.OID_Conhecimento = Conhecimentos_Internacionais.OID_Conhecimento ";
      sql += " AND Viagens_Internacionais.OID_Manifesto_Internacional = Manifestos_Internacionais.OID_Manifesto_Internacional ";

      if (String.valueOf(ed.getOID_Viagem_Internacional()) != null &&
          !String.valueOf(ed.getOID_Viagem_Internacional()).equals("0")){
        sql += " and OID_Viagem_Internacional = '" + ed.getOID_Viagem_Internacional() + "'";
      }
      FormataDataBean DataFormatada = new FormataDataBean();

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);
      while (res.next()){
        edVolta.setOID_Viagem_Internacional(res.getString("OID_Viagem_Internacional"));
        edVolta.setOID_Manifesto_Internacional(res.getString("OID_Manifesto_Internacional"));
        edVolta.setOID_Conhecimento_Internacional(res.getString("OID_Conhecimento"));
        edVolta.setOID_Pessoa(res.getString("OID_Pessoa"));
        edVolta.setOID_Pessoa_Destinatario(res.getString("OID_Pessoa_Destinatario"));
        edVolta.setNR_Conhecimento_Internacional(res.getLong("NR_Conhecimento"));
        edVolta.setNR_Manifesto_Internacional(res.getLong("NR_Manifesto_Internacional"));

        edVolta.setDT_Viagem_Internacional(res.getString("DT_Viagem_Internacional"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Viagem_Internacional());
        edVolta.setDT_Viagem_Internacional(DataFormatada.getDT_FormataData());
        edVolta.setHR_Viagem_Internacional(res.getString("HR_Viagem_Internacional"));

        edVolta.setDT_Previsao_Chegada(res.getString("DT_Previsao_Chegada"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Previsao_Chegada());
        edVolta.setDT_Previsao_Chegada(DataFormatada.getDT_FormataData());
        edVolta.setHR_Previsao_Chegada(res.getString("HR_Previsao_Chegada"));

        edVolta.setTX_Observacao(res.getString("TX_Observacao"));
        edVolta.setTX_Observacao2(res.getString("TX_Observacao2"));
        edVolta.setTX_Observacao3(res.getString("TX_Observacao3"));
        edVolta.setTX_Observacao4(res.getString("TX_Observacao4"));
        edVolta.setTX_Observacao5(res.getString("TX_Observacao5"));
        edVolta.setTX_Observacao6(res.getString("TX_Observacao6"));
		edVolta.setTX_Observacao7(res.getString("TX_Observacao7"));
		edVolta.setTX_Observacao8(res.getString("TX_Observacao8"));
		edVolta.setTX_Observacao9(res.getString("TX_Observacao9"));
		edVolta.setTX_Observacao10(res.getString("TX_Observacao10"));
		edVolta.setTX_Observacao11(res.getString("TX_Observacao11"));
		edVolta.setTX_Observacao12(res.getString("TX_Observacao12"));
		edVolta.setTX_Observacao13(res.getString("TX_Observacao13"));
		edVolta.setTX_Observacao14(res.getString("TX_Observacao14"));
		edVolta.setTX_Observacao15(res.getString("TX_Observacao15"));
		edVolta.setTX_Observacao16(res.getString("TX_Observacao16"));
		edVolta.setTX_Observacao17(res.getString("TX_Observacao17"));
		edVolta.setTX_Observacao18(res.getString("TX_Observacao18"));

        edVolta.setNM_Documento1(res.getString("NM_Documento1"));
        edVolta.setNM_Documento2(res.getString("NM_Documento2"));
        edVolta.setNM_Documento3(res.getString("NM_Documento3"));
        edVolta.setNM_Documento4(res.getString("NM_Documento4"));
        edVolta.setNM_Documento5(res.getString("NM_Documento5"));
        edVolta.setNM_Documento6(res.getString("NM_Documento6"));
        
        edVolta.setNCM(res.getString("NCM"));
        edVolta.setDM_Capa_Mic(res.getString("DM_Capa_Mic"));

        edVolta.setNM_Embalagem(res.getString("NM_Embalagem"));

        edVolta.setVL_Total_Frete(res.getDouble("VL_Total_Frete"));
        edVolta.setVL_Nota_Fiscal(res.getDouble("VL_Nota_Fiscal"));
        edVolta.setVL_Seguro(res.getDouble("VL_Seguro"));
        edVolta.setNR_Volumes(res.getLong("NR_Volumes"));
        edVolta.setVL_Peso(res.getDouble("VL_Peso"));
        edVolta.setVL_Peso_Cubado(res.getDouble("VL_Peso_Cubado"));

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

  public void inclui(Viagem_InternacionalED ed) throws Excecoes{

    String sql = null;
    long valOid = 0;
    String chave = null;
    boolean DM_Lancado_Ordem_Frete = false;
    boolean DM_Tem_Roteiro= false;
    boolean DM_Tem_viagem= false;
    try{


      sql =  " SELECT CD_Roteiro from Manifestos_Internacionais ";
      sql += " where oid_Manifesto_Internacional = '" + ed.getOID_Manifesto_Internacional() + "'";

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      while (res.next()){
        if (res.getString("CD_Roteiro") != null &&
          !String.valueOf(res.getString("CD_Roteiro")).equals("") &&
          !String.valueOf(res.getString("CD_Roteiro")).equals(null) &&
          !String.valueOf(res.getString("CD_Roteiro")).equals("null")){
          DM_Tem_Roteiro = true;
        }
      }
      
      chave = (ed.getOID_Manifesto_Internacional() + ed.getOID_Conhecimento_Internacional());

      String sqlBusca = "select * from viagens_internacionais where oid_viagem_internacional = '" + chave + "'";
      res = null;
      res = this.executasql.executarConsulta(sqlBusca);
      while (res.next()){
          DM_Tem_viagem = true;
        }
      
      sql = " insert into Viagens_Internacionais (OID_Viagem_Internacional, OID_Conhecimento, OID_Manifesto_Internacional, DT_Viagem_Internacional, HR_Viagem_Internacional, DT_Previsao_Chegada, HR_Previsao_Chegada, "+
        " TX_Observacao, TX_Observacao2, TX_Observacao3, TX_Observacao4, TX_Observacao5, TX_Observacao6, TX_Observacao7, TX_Observacao8, TX_Observacao9, TX_Observacao10, TX_Observacao11, TX_Observacao12, TX_Observacao13, TX_Observacao14, TX_Observacao15, TX_Observacao16, TX_Observacao17, TX_Observacao18, "+
        " NM_Documento1, NM_Documento2, NM_Documento3, NM_Documento4, NM_Documento5, NM_Documento6, "+
        " NM_Embalagem, VL_Total_Frete, VL_Nota_Fiscal, VL_Seguro, NR_Volumes, VL_Peso, VL_Peso_Cubado, ncm, dm_capa_mic ) values ";
      sql += "('" + chave + "','" + ed.getOID_Conhecimento_Internacional() + "','" + ed.getOID_Manifesto_Internacional() + "','"  + ed.getDT_Viagem_Internacional() + "','" + ed.getHR_Viagem_Internacional() + "','" + ed.getDT_Previsao_Chegada() + "','" +
        ed.getHR_Previsao_Chegada() + "','" +
        ed.getTX_Observacao() +  "','" +
        ed.getTX_Observacao2() + "','" +
        ed.getTX_Observacao3() + "','" +
        ed.getTX_Observacao4() + "','" +
        ed.getTX_Observacao5() + "','" +
        ed.getTX_Observacao6() + "','" +
		ed.getTX_Observacao7() + "','" +
		ed.getTX_Observacao8() + "','" +
		ed.getTX_Observacao9() + "','" +
		ed.getTX_Observacao10() + "','" +
		ed.getTX_Observacao11() + "','" +
		ed.getTX_Observacao12() + "','" +
		ed.getTX_Observacao13() + "','" +
		ed.getTX_Observacao14() + "','" +
		ed.getTX_Observacao15() + "','" +
		ed.getTX_Observacao16() + "','" +
		ed.getTX_Observacao17() + "','" +
		ed.getTX_Observacao18() + "','" +
        ed.getNM_Documento1() +  "','" +
        ed.getNM_Documento2() + "','" +
        ed.getNM_Documento3() + "','" +
        ed.getNM_Documento4() + "','" +
        ed.getNM_Documento5() + "','" +
        ed.getNM_Documento6() + "','" +
        ed.getNM_Embalagem() + "'," +
        ed.getVL_Total_Frete() + "," +
        ed.getVL_Nota_Fiscal() + "," +
        ed.getVL_Seguro() + "," +
        ed.getNR_Volumes() + "," +
        ed.getVL_Peso() + "," +
        ed.getVL_Peso_Cubado() + ",'" +
        ed.getNCM() + "','" +
        ed.getDM_Capa_Mic() + "')";

// // System.out.println(sql);

      int i = executasql.executarUpdate(sql);

      sql =  " SELECT Conhecimentos_Internacionais_Notas_Fiscais.OID_Nota_Fiscal from Conhecimentos_Internacionais_Notas_Fiscais, Conhecimentos_Internacionais ";
      sql += " WHERE Conhecimentos_Internacionais_Notas_Fiscais.OID_Conhecimento = Conhecimentos_Internacionais.OID_Conhecimento ";
      sql += " AND Conhecimentos_Internacionais_Notas_Fiscais.OID_Conhecimento = '" + ed.getOID_Conhecimento_Internacional() + "'";

      res = null;
      res = this.executasql.executarConsulta(sql);
      while (res.next()){
        ed.setOID_Nota_Fiscal(res.getString("OID_Nota_Fiscal"));
        sql =  " update Notas_Fiscais set DM_Situacao= 'E' " ;
        sql += " where Notas_Fiscais.oid_Nota_Fiscal = '" + ed.getOID_Nota_Fiscal() + "'";

        i = executasql.executarUpdate(sql);
        Data data = new Data();
        if (DM_Tem_Roteiro){
          String OID_Embarque = null;
          sql =  " SELECT OID_Embarque from Embarques ";
          sql += " WHERE Embarques.OID_Manifesto = '" + ed.getOID_Manifesto_Internacional() + "'";

          ResultSet resTP = null;
          resTP = this.executasql.executarConsulta(sql);

          while (resTP.next()){
           OID_Embarque = resTP.getString("OID_Embarque");
         }

          boolean NF_ja_embarq = false;

          if (OID_Embarque != null && !OID_Embarque.equals("")){
            chave = (OID_Embarque + ed.getOID_Nota_Fiscal());

    	    sql = "select * from  Notas_Fiscais_Embarques where OID_Nota_Fiscal_Embarque =";
	        sql += "'"+chave + "'";
            resTP = this.executasql.executarConsulta(sql);

            while (resTP.next()){
                  NF_ja_embarq = true;
            }

            if(!NF_ja_embarq){
               sql = " insert into Notas_Fiscais_Embarques (OID_Nota_Fiscal_Embarque, OID_Embarque, OID_Nota_Fiscal, DT_Nota_Fiscal_Embarque, HR_Nota_Fiscal_Embarque ) values ";
               sql += "('" + chave + "'," + OID_Embarque + ",'" + ed.getOID_Nota_Fiscal() +  "','"  + data.getDataDMY() + "','" + data.getHoraHM() + "')";

               i = executasql.executarUpdate(sql);

               sql = " update Embarques set DM_Situacao = '5'" ;
               sql += " where oid_Embarque = " + OID_Embarque;

               i = executasql.executarUpdate(sql);
            }
          }
        }
      }
 

    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
exc.printStackTrace();
      excecoes.setClasse(this.getClass().getName());
      if (DM_Lancado_Ordem_Frete){
        excecoes.setMensagem("Manifesto_Internacional lançado em ordem de frete - inclusão de CTRC não permitida...");
      }else if (DM_Tem_viagem){
          excecoes.setMensagem("CRT já Lançado neste MIC!");
      }else{
        excecoes.setMensagem("Erro ao incluir");
      }
      excecoes.setMetodo("inclui");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }


  public byte[] geraViagem_Internacional(Viagem_InternacionalED ed, HttpServletResponse response)throws Excecoes{

    String sql = null;
    byte[] b = null;
    ResultSet resL = null;

    sql =  " SELECT ";
    sql += "  Manifestos_Internacionais.OID_MANIFESTO_INTERNACIONAL, ";
    sql += "  Manifestos_Internacionais.NR_MANIFESTO_INTERNACIONAL, ";
    sql += "  Manifestos_Internacionais.DT_EMISSAO            AS DT_EMISSAO_MANIFESTO,  ";
    sql += "  Manifestos_Internacionais.OID_UNIDADE           AS OID_UNIDADE_MANIFESTO, ";
    sql += "  Manifestos_Internacionais.OID_UNIDADE_destino           AS OID_UNIDADE_destino, ";
    sql += "  Manifestos_Internacionais.OID_UNIDADE_fronteira           AS OID_UNIDADE_fronteira, ";
    sql += "  Manifestos_Internacionais.OID_PESSOA            AS OID_PESSOA_MOTORISTA, ";
    sql += "  Manifestos_Internacionais.OID_PESSOA_Permisso   AS OID_PESSOA_Permisso, ";
    sql += "  Manifestos_Internacionais.OID_PESSOA_Permisso2   AS OID_PESSOA_Permisso2, ";
    sql += "  Manifestos_Internacionais.OID_ADUANA            AS OID_ADUANA_ORIGEM, ";
    sql += "  Manifestos_Internacionais.OID_ADUANA_DESTINO    AS OID_ADUANA_DESTINO, ";
    sql += "  Manifestos_Internacionais.OID_CIDADE            AS OID_CIDADE_ORIGEM, ";
    sql += "  Manifestos_Internacionais.OID_CIDADE_DESTINO    AS OID_CIDADE_DESTINO, ";
    sql += "  Manifestos_Internacionais.NM_CIDADE_DESTINO    AS NM_CIDADE_DESTINO, ";
    sql += "  Manifestos_Internacionais.NM_PAIS              AS NM_PAIS, ";
    sql += "  Manifestos_Internacionais.NM_PROPRIETARIO, ";
    sql += "  Manifestos_Internacionais.NM_End_PROPRIETARIO, ";
    sql += "  Manifestos_Internacionais.NM_Cid_uf_pais_PROPRIETARIO, ";
    sql += "  Manifestos_Internacionais.CD_ROTEIRO, ";
    sql += "  Manifestos_Internacionais.OID_CIDADE_ALFANDEGA  AS OID_CIDADE_ALFANDEGA, ";
    sql += "  Manifestos_Internacionais.NM_LACRE        AS NM_LACRE, ";
    sql += "  Manifestos_Internacionais.TX_OBSERVACAO1  AS TX_OBSERVACAO1_MANIF, ";
    sql += "  Manifestos_Internacionais.TX_OBSERVACAO2  AS TX_OBSERVACAO2_MANIF, ";
    sql += "  Manifestos_Internacionais.TX_OBSERVACAO3  AS TX_OBSERVACAO3_MANIF, ";
    sql += "  Manifestos_Internacionais.TX_ROTA1 , ";
    sql += "  Manifestos_Internacionais.TX_ROTA2 , ";
    sql += "  Manifestos_Internacionais.TX_ROTA3 , ";
    sql += "  Manifestos_Internacionais.TX_ROTA4 , ";
    sql += "  Manifestos_Internacionais.TX_ROTA5 , ";
    sql += "  Manifestos_Internacionais.TX_ROTA6 , ";
    sql += "  Manifestos_Internacionais.TX_ROTA7 , ";
    sql += "  Manifestos_Internacionais.TX_ROTA8 , ";
    sql += "  Manifestos_Internacionais.TX_ROTA9 , ";
    sql += "  Manifestos_Internacionais.CD_PAIS                   AS CD_PAIS_MANIF, ";
    sql += "  Manifestos_Internacionais.NR_PERMISSO               AS NR_PERMISSO_MANIF, ";
    sql += "  Manifestos_Internacionais.DM_EXPORTACAO_IMPORTACAO  AS DM_EXPORTACAO_IMPORTACAO_MANIF, ";
    sql += "  Manifestos_Internacionais.DM_TRANSITO_ADUANEIRO  , ";
    sql += "  Manifestos_Internacionais.oid_veiculo_carreta  as nr_placa_carreta, ";

                    sql += "  Conhecimentos_Internacionais.NR_CONHECIMENTO , ";
                    sql += "  Conhecimentos_Internacionais.oid_CONHECIMENTO as oid_crt , ";
                    sql += "  Conhecimentos_Internacionais.NM_SERIE, ";
                    sql += "  Conhecimentos_Internacionais.OID_PESSOA                AS OID_PESSOA_REMETENTE , ";
                    sql += "  Conhecimentos_Internacionais.OID_PESSOA_DESTINATARIO   AS OID_PESSOA_DESTINATARIO , ";
                    sql += "  Conhecimentos_Internacionais.OID_PESSOA_CONSIGNATARIO  AS OID_PESSOA_CONSIGNATARIO , ";
                    sql += "  Conhecimentos_Internacionais.CD_PAIS                   AS CD_PAIS_CTO, ";
                    sql += "  Conhecimentos_Internacionais.NR_PERMISSO               AS NR_PERMISSO_CTO, ";
                    sql += "  Conhecimentos_Internacionais.DM_EXPORTACAO_IMPORTACAO  AS DM_EXPORTACAO_IMPORTACAO_CTO, ";
                    sql += "  Conhecimentos_Internacionais.VL_SEGURO                 AS VL_SEGURO_CTO, ";
                    sql += "  Conhecimentos_Internacionais.VL_FRETE                  AS VL_FRETE_CTO, ";
                    sql += "  Conhecimentos_Internacionais.VL_NOTA_FISCAL            AS VL_NOTA_FISCAL_CTO, ";
                    sql += "  Conhecimentos_Internacionais.NR_VOLUMES                AS NR_VOLUMES_CTO, ";
                    sql += "  Conhecimentos_Internacionais.CD_PAIS                   AS CD_PAIS_CTO, ";
                    sql += "  Conhecimentos_Internacionais.NR_PERMISSO               AS NR_PERMISSO_CTO, ";
                    sql += "  Conhecimentos_Internacionais.DM_EXPORTACAO_IMPORTACAO  AS DM_EXPORTACAO_IMPORTACAO_CTO, ";
                    sql += "  Conhecimentos_Internacionais.VL_PESO                   AS VL_PESO_CTO, ";
                    sql += "  Conhecimentos_Internacionais.VL_PESO_CUBADO            AS VL_PESO_CUBADO_CTO , ";
                    sql += "  Conhecimentos_Internacionais.VL_NOTA_FISCAL            AS VL_NOTA_FISCAL_CTO, ";
                    sql += "  Conhecimentos_Internacionais.NM_ENDERECO_REMETENTE , ";
                    sql += "  Conhecimentos_Internacionais.NM_REMETENTE , ";
                    sql += "  Conhecimentos_Internacionais.NM_cidade_estado_pais_REMETENTE , ";
                    sql += "  Conhecimentos_Internacionais.NM_DESTINATARIO , ";
                    sql += "  Conhecimentos_Internacionais.NM_ENDERECO_DESTINATARIO , ";
                    sql += "  Conhecimentos_Internacionais.NM_cidade_estado_pais_DESTINATARIO , ";
                    sql += "  Conhecimentos_Internacionais.NM_NOTIFICAR , ";
                    sql += "  Conhecimentos_Internacionais.NM_ENDERECO_NOTIFICAR , ";
                    sql += "  Conhecimentos_Internacionais.NM_cidade_estado_pais_NOTIFICAR , ";
                    sql += "  Conhecimentos_Internacionais.NM_CONSIGNATARIO , ";
                    sql += "  Conhecimentos_Internacionais.NM_ENDERECO_CONSIGNATARIO , ";
                    sql += "  Conhecimentos_Internacionais.NM_cidade_estado_pais_CONSIGNATARIO , ";
                    sql += "  Conhecimentos_Internacionais.nr_cnpj_remetente_complementar , ";
                    sql += "  Conhecimentos_Internacionais.nr_cnpj_destinatario_complementar , ";
                    sql += "  Conhecimentos_Internacionais.nr_cnpj_consignatario_complementar , ";


//    sql += "  Conhecimentos_Internacionais.NR_CONHECIMENTO , ";
//    sql += "  Conhecimentos_Internacionais.VL_FRETE AS VL_FRETE_CTO, ";
//    sql += "  Conhecimentos_Internacionais.VL_NOTA_FISCAL AS VL_NOTA_FISCAL_CTO, ";
//    sql += "  Conhecimentos_Internacionais.NR_VOLUMES AS VL_VOLUMES_CTO, ";
//    sql += "  Conhecimentos_Internacionais.VL_PESO AS VL_PESO_CTO, ";

    sql += "  Viagens_Internacionais.HR_VIAGEM_INTERNACIONAL , ";
    sql += "  Viagens_Internacionais.DT_PREVISAO_CHEGADA , ";
    sql += "  Viagens_Internacionais.HR_PREVISAO_CHEGADA , ";
    sql += "  Viagens_Internacionais.TX_OBSERVACAO , ";
    sql += "  Viagens_Internacionais.TX_OBSERVACAO2 , ";
    sql += "  Viagens_Internacionais.TX_OBSERVACAO3 , ";
    sql += "  Viagens_Internacionais.TX_OBSERVACAO4 , ";
    sql += "  Viagens_Internacionais.TX_OBSERVACAO5 , ";
    sql += "  Viagens_Internacionais.TX_OBSERVACAO6 , ";
    sql += "  Viagens_Internacionais.TX_OBSERVACAO7 , ";
    sql += "  Viagens_Internacionais.TX_OBSERVACAO8 , ";
    sql += "  Viagens_Internacionais.TX_OBSERVACAO9 , ";
    sql += "  Viagens_Internacionais.TX_OBSERVACAO10 , ";
    sql += "  Viagens_Internacionais.TX_OBSERVACAO11 , ";
    sql += "  Viagens_Internacionais.TX_OBSERVACAO12 , ";
    sql += "  Viagens_Internacionais.TX_OBSERVACAO13 , ";
    sql += "  Viagens_Internacionais.TX_OBSERVACAO14 , ";
    sql += "  Viagens_Internacionais.TX_OBSERVACAO15 , ";
    sql += "  Viagens_Internacionais.TX_OBSERVACAO16 , ";
    sql += "  Viagens_Internacionais.TX_OBSERVACAO17 , ";
    sql += "  Viagens_Internacionais.TX_OBSERVACAO18 , ";
    sql += "  Viagens_Internacionais.NM_DOCUMENTO1 , ";
    sql += "  Viagens_Internacionais.NM_DOCUMENTO2 , ";
    sql += "  Viagens_Internacionais.NM_DOCUMENTO3 , ";
    sql += "  Viagens_Internacionais.NM_DOCUMENTO4 , ";
    sql += "  Viagens_Internacionais.NM_DOCUMENTO5 , ";
    sql += "  Viagens_Internacionais.NM_DOCUMENTO6 , ";
    sql += "  Viagens_Internacionais.NCM , ";
    sql += "  Viagens_Internacionais.DM_Capa_Mic , ";
    sql += "  Viagens_Internacionais.VL_NOTA_FISCAL , ";
    sql += "  Viagens_Internacionais.VL_SEGURO , ";
    sql += "  Viagens_Internacionais.VL_PESO , ";
    sql += "  Viagens_Internacionais.VL_PESO_CUBADO , ";
    sql += "  Viagens_Internacionais.NR_VOLUMES , ";
    sql += "  Viagens_Internacionais.NM_EMBALAGEM , ";
    sql += "  Viagens_Internacionais.VL_TOTAL_FRETE , ";
    sql += "  Veiculos.NR_PLACA, ";
    sql += "  Veiculos.OID_Pessoa_proprietario as OID_PESSOA_PROPRIETARIO , ";
    sql += "  Modelos_Veiculos.CD_MODELO_VEICULO, ";
    sql += "  Modelos_Veiculos.NM_MODELO_VEICULO  ";
    sql += "  FROM Manifestos_Internacionais, Conhecimentos_Internacionais, Viagens_Internacionais , Modelos_Veiculos, Veiculos  ";
    sql += "  WHERE  Viagens_Internacionais.OID_Manifesto_Internacional = Manifestos_Internacionais.OID_Manifesto_Internacional ";
    sql += "  AND Manifestos_Internacionais.OID_Veiculo   = Veiculos.OID_Veiculo ";
    sql += "  AND Veiculos.OID_MODELO_VEICULO = Modelos_Veiculos.OID_MODELO_VEICULO ";
    sql += "  AND Viagens_Internacionais.OID_Conhecimento = Conhecimentos_Internacionais.OID_Conhecimento ";

      if (String.valueOf(ed.getOID_Manifesto_Internacional()) != null &&
          !String.valueOf(ed.getOID_Manifesto_Internacional()).equals("") &&
          !String.valueOf(ed.getOID_Manifesto_Internacional()).equals("null")){
        sql += " and Manifestos_Internacionais.oid_Manifesto_Internacional = '" + ed.getOID_Manifesto_Internacional() + "'";
      }
      sql += " Order by Viagens_Internacionais.DM_Capa_Mic desc, Conhecimentos_Internacionais.NR_Conhecimento asc";

// System.out.println("TEO + MIC " + sql);

    Viagem_InternacionalED edVolta = new Viagem_InternacionalED();
    ResultSet res = null;

    try{

      res = this.executasql.executarConsulta(sql.toString());

      if(res.next()){
          res.previous();
	      Viagem_InternacionalRL conRL = new Viagem_InternacionalRL(executasql);
	      if (ed.getNR_Imprimir()==1){
	         conRL.geraViagem_Internacional(ed, res,response);
	      } else {
	          if (ed.getNR_Imprimir()==2){
	              //conRL.geraViagem_Internacional_Cont(ed, res,response);
	              conRL.geraViagem_Internacional_CRT(ed, res,response);
	          }else{
	              conRL.geraViagem_Internacional_Costas(ed, res,response);
	          }
	      }
      } else {
          sql =  " SELECT ";
          sql += "  Manifestos_Internacionais.OID_MANIFESTO_INTERNACIONAL, ";
          sql += "  Manifestos_Internacionais.NR_MANIFESTO_INTERNACIONAL, ";
          sql += "  Manifestos_Internacionais.DT_EMISSAO            AS DT_EMISSAO_MANIFESTO,  ";
          sql += "  Manifestos_Internacionais.OID_UNIDADE           AS OID_UNIDADE_MANIFESTO, ";
          sql += "  Manifestos_Internacionais.OID_PESSOA            AS OID_PESSOA_MOTORISTA, ";
          sql += "  Manifestos_Internacionais.OID_PESSOA_Permisso   AS OID_PESSOA_Permisso, ";
          sql += "  Manifestos_Internacionais.OID_PESSOA_Permisso2   AS OID_PESSOA_Permisso2, ";
          sql += "  Manifestos_Internacionais.OID_ADUANA            AS OID_ADUANA_ORIGEM, ";
          sql += "  Manifestos_Internacionais.OID_ADUANA_DESTINO    AS OID_ADUANA_DESTINO, ";
          sql += "  Manifestos_Internacionais.OID_CIDADE            AS OID_CIDADE_ORIGEM, ";
          sql += "  Manifestos_Internacionais.OID_CIDADE_DESTINO    AS OID_CIDADE_DESTINO, ";
          sql += "  Manifestos_Internacionais.NM_CIDADE_DESTINO    AS NM_CIDADE_DESTINO, ";
          sql += "  Manifestos_Internacionais.NM_PAIS              AS NM_PAIS, ";
          sql += "  Manifestos_Internacionais.NM_PROPRIETARIO, ";
          sql += "  Manifestos_Internacionais.NM_End_PROPRIETARIO, ";
          sql += "  Manifestos_Internacionais.NM_Cid_uf_pais_PROPRIETARIO, ";
          sql += "  Manifestos_Internacionais.CD_ROTEIRO, ";
          sql += "  Manifestos_Internacionais.OID_CIDADE_ALFANDEGA  AS OID_CIDADE_ALFANDEGA, ";
          sql += "  Manifestos_Internacionais.NM_LACRE        AS NM_LACRE, ";
          sql += "  Manifestos_Internacionais.TX_OBSERVACAO1  AS TX_OBSERVACAO1_MANIF, ";
          sql += "  Manifestos_Internacionais.TX_OBSERVACAO2  AS TX_OBSERVACAO2_MANIF, ";
          sql += "  Manifestos_Internacionais.TX_OBSERVACAO3  AS TX_OBSERVACAO3_MANIF, ";
          sql += "  Manifestos_Internacionais.TX_ROTA1 , ";
          sql += "  Manifestos_Internacionais.TX_ROTA2 , ";
          sql += "  Manifestos_Internacionais.TX_ROTA3 , ";
          sql += "  Manifestos_Internacionais.TX_ROTA4 , ";
          sql += "  Manifestos_Internacionais.TX_ROTA5 , ";
          sql += "  Manifestos_Internacionais.TX_ROTA6 , ";
          sql += "  Manifestos_Internacionais.TX_ROTA7 , ";
          sql += "  Manifestos_Internacionais.TX_ROTA8 , ";
          sql += "  Manifestos_Internacionais.TX_ROTA9 , ";
          sql += "  Manifestos_Internacionais.TX_OBSERVACAO1 , ";
          sql += "  Manifestos_Internacionais.TX_OBSERVACAO2 , ";
          sql += "  Manifestos_Internacionais.TX_OBSERVACAO3 , ";
          sql += "  Manifestos_Internacionais.TX_OBSERVACAO4 , ";
          sql += "  Manifestos_Internacionais.TX_OBSERVACAO5 , ";
          sql += "  Manifestos_Internacionais.TX_OBSERVACAO6 , ";
          sql += "  Manifestos_Internacionais.TX_OBSERVACAO7 , ";
          sql += "  Manifestos_Internacionais.TX_OBSERVACAO8 , ";
          sql += "  Manifestos_Internacionais.TX_OBSERVACAO9 , ";
          sql += "  Manifestos_Internacionais.TX_OBSERVACAO10 , ";
          sql += "  Manifestos_Internacionais.TX_OBSERVACAO11 , ";
          sql += "  Manifestos_Internacionais.TX_OBSERVACAO12 , ";
          sql += "  Manifestos_Internacionais.TX_OBSERVACAO13 , ";
          sql += "  Manifestos_Internacionais.TX_OBSERVACAO14 , ";
          sql += "  Manifestos_Internacionais.TX_OBSERVACAO15 , ";
          sql += "  Manifestos_Internacionais.TX_OBSERVACAO16 , ";
          sql += "  Manifestos_Internacionais.TX_OBSERVACAO17 , ";
          sql += "  Manifestos_Internacionais.TX_OBSERVACAO18 , ";
          sql += "  Manifestos_Internacionais.CD_PAIS                   AS CD_PAIS_MANIF, ";
          sql += "  Manifestos_Internacionais.NR_PERMISSO               AS NR_PERMISSO_MANIF, ";
          sql += "  Manifestos_Internacionais.DM_EXPORTACAO_IMPORTACAO  AS DM_EXPORTACAO_IMPORTACAO_MANIF, ";
          sql += "  Manifestos_Internacionais.DM_TRANSITO_ADUANEIRO  , ";
          sql += "  Manifestos_Internacionais.oid_veiculo_carreta  as nr_placa_carreta, ";
          sql += "  Veiculos.NR_PLACA, ";
          sql += "  Veiculos.OID_Pessoa_proprietario as OID_PESSOA_PROPRIETARIO , ";
          sql += "  Modelos_Veiculos.CD_MODELO_VEICULO, ";
          sql += "  Modelos_Veiculos.NM_MODELO_VEICULO  ";
          sql += "  FROM Manifestos_Internacionais, Modelos_Veiculos, Veiculos  ";
          sql += "  WHERE Manifestos_Internacionais.OID_Veiculo   = Veiculos.OID_Veiculo ";
          sql += "  AND Veiculos.OID_MODELO_VEICULO = Modelos_Veiculos.OID_MODELO_VEICULO ";
            if (String.valueOf(ed.getOID_Manifesto_Internacional()) != null &&
                !String.valueOf(ed.getOID_Manifesto_Internacional()).equals("") &&
                !String.valueOf(ed.getOID_Manifesto_Internacional()).equals("null")){
              sql += " and Manifestos_Internacionais.oid_Manifesto_Internacional = '" + ed.getOID_Manifesto_Internacional() + "'";
            }
            sql += " Order by Manifestos_Internacionais.NR_Manifesto_Internacional ";
          
            resL = null;
// System.out.println("TEO + ENLASTRE " + sql);
            resL = this.executasql.executarConsulta(sql.toString());
            
          Viagem_InternacionalRL conRL = new Viagem_InternacionalRL(executasql);
          if (ed.getNR_Imprimir()!=3){
              conRL.geraViagem_Internacional_EN_LASTRE(ed, resL, response);
          } else{
              conRL.geraViagem_Internacional_Costas(ed, res,response);
          }
//          Excecoes ex = new Excecoes();
//          ex.setMensagem("Manifesto sem Viagem(s)!");
//          ex.setClasse(this.getClass().getName());
//          ex.setMetodo("geraViagem_Internacional(ConhecimentoED ed)");
//          throw ex;
          util.closeResultset(resL);
      }

      util.closeResultset(res);
    }

    catch (Excecoes e){
      throw e;
    }
    catch(Exception exc){
      Excecoes exce = new Excecoes();
      exce.setExc(exc);
      exce.setMensagem("Erro no método listar");
      exce.setClasse(this.getClass().getName());
      exce.setMetodo("geraViagem_Internacional(ConhecimentoED ed)");
    }
    finally {
    	util.closeResultset(res);
	}
    return b;
  }

  public byte[] geraViagem_Internacional_Antigo(Viagem_InternacionalED ed)throws Excecoes{

      String sql = null;
      byte[] b = null;

        sql =  " SELECT ";

      sql += "  Manifestos_Internacionais.OID_MANIFESTO_INTERNACIONAL, ";
      sql += "  Manifestos_Internacionais.NR_MANIFESTO_INTERNACIONAL, ";
      sql += "  Manifestos_Internacionais.DT_EMISSAO            AS DT_EMISSAO_MANIFESTO,  ";
      sql += "  Manifestos_Internacionais.OID_UNIDADE           AS OID_UNIDADE_MANIFESTO, ";
      sql += "  Manifestos_Internacionais.OID_PESSOA            AS OID_PESSOA_MOTORISTA, ";
      sql += "  Manifestos_Internacionais.OID_PESSOA_Permisso   AS OID_PESSOA_Permisso, ";
      sql += "  Manifestos_Internacionais.OID_PESSOA_Permisso2   AS OID_PESSOA_Permisso2, ";
      sql += "  Manifestos_Internacionais.OID_ADUANA            AS OID_ADUANA_ORIGEM, ";
      sql += "  Manifestos_Internacionais.OID_ADUANA_DESTINO    AS OID_ADUANA_DESTINO, ";
      sql += "  Manifestos_Internacionais.OID_CIDADE            AS OID_CIDADE_ORIGEM, ";
      sql += "  Manifestos_Internacionais.OID_CIDADE_DESTINO    AS OID_CIDADE_DESTINO, ";
      sql += "  Manifestos_Internacionais.OID_CIDADE_ALFANDEGA  AS OID_CIDADE_ALFANDEGA, ";
      sql += "  Manifestos_Internacionais.NM_LACRE        AS NM_LACRE, ";
      sql += "  Manifestos_Internacionais.TX_OBSERVACAO1  AS TX_OBSERVACAO1_MANIF, ";
      sql += "  Manifestos_Internacionais.TX_OBSERVACAO2  AS TX_OBSERVACAO2_MANIF, ";
      sql += "  Manifestos_Internacionais.TX_OBSERVACAO3  AS TX_OBSERVACAO3_MANIF, ";
      sql += "  Manifestos_Internacionais.TX_ROTA1 , ";
      sql += "  Manifestos_Internacionais.TX_ROTA2 , ";
      sql += "  Manifestos_Internacionais.TX_ROTA3 , ";
      sql += "  Manifestos_Internacionais.CD_PAIS                   AS CD_PAIS_MANIF, ";
      sql += "  Manifestos_Internacionais.NR_PERMISSO               AS NR_PERMISSO_MANIF, ";
      sql += "  Manifestos_Internacionais.DM_EXPORTACAO_IMPORTACAO  AS DM_EXPORTACAO_IMPORTACAO_MANIF, ";
      sql += "  Manifestos_Internacionais.DM_TRANSITO_ADUANEIRO  , ";
      sql += "  Manifestos_Internacionais.oid_veiculo_carreta  as nr_placa_carreta, ";

                      sql += "  Conhecimentos_Internacionais.NR_CONHECIMENTO , ";
                      sql += "  Conhecimentos_Internacionais.NM_SERIE, ";
                      sql += "  Conhecimentos_Internacionais.OID_PESSOA                AS OID_PESSOA_REMETENTE , ";
                      sql += "  Conhecimentos_Internacionais.OID_PESSOA_DESTINATARIO   AS OID_PESSOA_DESTINATARIO , ";
                      sql += "  Conhecimentos_Internacionais.OID_PESSOA_CONSIGNATARIO  AS OID_PESSOA_CONSIGNATARIO , ";
                      sql += "  Conhecimentos_Internacionais.CD_PAIS                   AS CD_PAIS_CTO, ";
                      sql += "  Conhecimentos_Internacionais.NR_PERMISSO               AS NR_PERMISSO_CTO, ";
                      sql += "  Conhecimentos_Internacionais.DM_EXPORTACAO_IMPORTACAO  AS DM_EXPORTACAO_IMPORTACAO_CTO, ";
                      sql += "  Conhecimentos_Internacionais.VL_SEGURO                 AS VL_SEGURO_CTO, ";
                      sql += "  Conhecimentos_Internacionais.VL_FRETE                  AS VL_FRETE_CTO, ";
                      sql += "  Conhecimentos_Internacionais.VL_NOTA_FISCAL            AS VL_NOTA_FISCAL_CTO, ";
                      sql += "  Conhecimentos_Internacionais.NR_VOLUMES                AS NR_VOLUMES_CTO, ";
                      sql += "  Conhecimentos_Internacionais.CD_PAIS                   AS CD_PAIS_CTO, ";
                      sql += "  Conhecimentos_Internacionais.NR_PERMISSO               AS NR_PERMISSO_CTO, ";
                      sql += "  Conhecimentos_Internacionais.DM_EXPORTACAO_IMPORTACAO  AS DM_EXPORTACAO_IMPORTACAO_CTO, ";
                      sql += "  Conhecimentos_Internacionais.VL_PESO                   AS VL_PESO_CTO, ";
                      sql += "  Conhecimentos_Internacionais.VL_PESO_CUBADO            AS VL_PESO_CUBADO_CTO , ";
                      sql += "  Conhecimentos_Internacionais.VL_NOTA_FISCAL            AS VL_NOTA_FISCAL_CTO, ";
                      sql += "  Conhecimentos_Internacionais.NM_ENDERECO_REMETENTE , ";
                      sql += "  Conhecimentos_Internacionais.NM_REMETENTE , ";
                      sql += "  Conhecimentos_Internacionais.NM_DESTINATARIO , ";
                      sql += "  Conhecimentos_Internacionais.NM_ENDERECO_DESTINATARIO , ";
                      sql += "  Conhecimentos_Internacionais.NM_NOTIFICAR , ";
                      sql += "  Conhecimentos_Internacionais.NM_ENDERECO_NOTIFICAR , ";


//      sql += "  Conhecimentos_Internacionais.NR_CONHECIMENTO , ";
//      sql += "  Conhecimentos_Internacionais.VL_FRETE AS VL_FRETE_CTO, ";
//      sql += "  Conhecimentos_Internacionais.VL_NOTA_FISCAL AS VL_NOTA_FISCAL_CTO, ";
//      sql += "  Conhecimentos_Internacionais.NR_VOLUMES AS VL_VOLUMES_CTO, ";
//      sql += "  Conhecimentos_Internacionais.VL_PESO AS VL_PESO_CTO, ";

      sql += "  Viagens_Internacionais.HR_VIAGEM_INTERNACIONAL , ";
      sql += "  Viagens_Internacionais.DT_PREVISAO_CHEGADA , ";
      sql += "  Viagens_Internacionais.HR_PREVISAO_CHEGADA , ";
      sql += "  Viagens_Internacionais.TX_OBSERVACAO , ";
      sql += "  Viagens_Internacionais.TX_OBSERVACAO2 , ";
      sql += "  Viagens_Internacionais.TX_OBSERVACAO3 , ";
      sql += "  Viagens_Internacionais.TX_OBSERVACAO4 , ";
      sql += "  Viagens_Internacionais.TX_OBSERVACAO5 , ";
      sql += "  Viagens_Internacionais.TX_OBSERVACAO6 , ";
      sql += "  Viagens_Internacionais.TX_OBSERVACAO7 , ";
      sql += "  Viagens_Internacionais.TX_OBSERVACAO8 , ";
      sql += "  Viagens_Internacionais.NM_DOCUMENTO1 , ";
      sql += "  Viagens_Internacionais.NM_DOCUMENTO2 , ";
      sql += "  Viagens_Internacionais.NM_DOCUMENTO3 , ";
      sql += "  Viagens_Internacionais.NM_DOCUMENTO4 , ";
      sql += "  Viagens_Internacionais.NM_DOCUMENTO5 , ";
      sql += "  Viagens_Internacionais.VL_NOTA_FISCAL , ";
      sql += "  Viagens_Internacionais.VL_SEGURO , ";
      sql += "  Viagens_Internacionais.VL_PESO , ";
      sql += "  Viagens_Internacionais.VL_PESO_CUBADO , ";
      sql += "  Viagens_Internacionais.NR_VOLUMES , ";
      sql += "  Viagens_Internacionais.NM_EMBALAGEM , ";
      sql += "  Viagens_Internacionais.VL_TOTAL_FRETE , ";
      sql += "  Veiculos.NR_PLACA, ";
      sql += "  Veiculos.OID_Pessoa as OID_PESSOA_PROPRIETARIO , ";
      sql += "  Modelos_Veiculos.CD_MODELO_VEICULO, ";
      sql += "  Modelos_Veiculos.NM_MODELO_VEICULO  ";
      sql += "  FROM Manifestos_Internacionais, Conhecimentos_Internacionais, Viagens_Internacionais , Modelos_Veiculos, Veiculos  ";
      sql += "  WHERE  Viagens_Internacionais.OID_Manifesto_Internacional = Manifestos_Internacionais.OID_Manifesto_Internacional ";
      sql += "  AND Manifestos_Internacionais.OID_Veiculo   = Veiculos.OID_Veiculo ";
      sql += "  AND Veiculos.OID_MODELO_VEICULO = Modelos_Veiculos.OID_MODELO_VEICULO ";
      sql += "  AND Viagens_Internacionais.OID_Conhecimento = Conhecimentos_Internacionais.OID_Conhecimento ";

        if (String.valueOf(ed.getOID_Manifesto_Internacional()) != null &&
            !String.valueOf(ed.getOID_Manifesto_Internacional()).equals("") &&
            !String.valueOf(ed.getOID_Manifesto_Internacional()).equals("null")){
          sql += " and Manifestos_Internacionais.oid_Manifesto_Internacional = '" + ed.getOID_Manifesto_Internacional() + "'";
        }

        sql += " Order by  Conhecimentos_Internacionais.NR_Conhecimento ";




      Viagem_InternacionalED edVolta = new Viagem_InternacionalED();

      try{

        ResultSet res = null;

        res = this.executasql.executarConsulta(sql.toString());

        Viagem_InternacionalRL conRL = new Viagem_InternacionalRL(executasql);

        if (ed.getNR_Imprimir()==2){
           return  conRL.geraViagem_Internacional_Cont_Antigo(ed, res);
        } else {
           return conRL.geraViagem_Internacional_Antigo(ed, res);
        }

//        b =  conRL.geraViagem_Internacional(ed, res);

      }

      catch (Excecoes e){
        throw e;
      }
      catch(Exception exc){
        Excecoes exce = new Excecoes();
        exce.setExc(exc);
        exce.setMensagem("Erro no método listar");
        exce.setClasse(this.getClass().getName());
        exce.setMetodo("geraRelatorio(ConhecimentoED ed)");
      }
      return b;
    }
  
  public Viagem_InternacionalED getQtdeEmbarcada(Viagem_InternacionalED ed)throws Excecoes{

      String sql = null;
      String sqlBusca = null;

      Viagem_InternacionalED edVolta = new Viagem_InternacionalED();

      try{
          
          sql =  " SELECT Manifestos_Internacionais.oid_Unidade " +
   	   			 " from Manifestos_Internacionais ";
          sql += " WHERE Manifestos_Internacionais.OID_Manifesto_Internacional = '" + ed.getOID_Manifesto_Internacional() + "'";
          
          ResultSet rs = null;
          rs = this.executasql.executarConsulta(sql);
          while(rs.next()) ed.setOID_Unidade(rs.getLong("oid_unidade"));
          

        sql =  " SELECT sum(Viagens_Internacionais.NR_Volumes) as NR_Volumes, "+
        	   " sum(Viagens_Internacionais.VL_Peso) as VL_Peso_Embarcado, "+
        	   " Conhecimentos_Internacionais.OID_Conhecimento, "+
        	   " Conhecimentos_Internacionais.NR_Volumes_Observacao, "+
        	   " Conhecimentos_Internacionais.VL_Nota_Fiscal, "+
        	   " Conhecimentos_Internacionais.VL_Peso, "+
        	   " Conhecimentos_Internacionais.VL_Peso_Cubado, "+
        	   " Manifestos_Internacionais.oid_Unidade " +
        	   " from Viagens_Internacionais, Conhecimentos_Internacionais, Manifestos_Internacionais ";
        sql += " WHERE Viagens_Internacionais.OID_Conhecimento = Conhecimentos_Internacionais.OID_Conhecimento ";
        sql += " AND Viagens_Internacionais.OID_Manifesto_Internacional = Manifestos_Internacionais.OID_Manifesto_Internacional ";

        if (String.valueOf(ed.getOID_Conhecimento_Internacional()) != null &&
            !String.valueOf(ed.getOID_Conhecimento_Internacional()).equals("0")){
           sql += " and Viagens_Internacionais.OID_Conhecimento = '" + ed.getOID_Conhecimento_Internacional() + "'";
        }
//        if (String.valueOf(ed.getOID_Manifesto_Internacional()) != null &&
//            !String.valueOf(ed.getOID_Manifesto_Internacional()).equals("0")){
//           //sql += " and Viagens_Internacionais.OID_Manifesto_Internacional = '" + ed.getOID_Manifesto_Internacional() + "'";
//        }
        if (String.valueOf(ed.getOID_Unidade()) != null &&
                !String.valueOf(ed.getOID_Unidade()).equals("0")){
           sql += " and Manifestos_Internacionais.oid_Unidade = '" + ed.getOID_Unidade() + "'";
        }
        sql += " group by Conhecimentos_Internacionais.OID_Conhecimento, Conhecimentos_Internacionais.NR_Volumes_Observacao, "+
        	   " Conhecimentos_Internacionais.VL_Nota_Fiscal, "+
        	   " Conhecimentos_Internacionais.VL_Peso, "+
        	   " Conhecimentos_Internacionais.VL_Peso_Cubado, "+
        	   " Manifestos_Internacionais.oid_Unidade ";
        
        FormataDataBean DataFormatada = new FormataDataBean();
        
// System.out.println(sql);
        
		ResultSet res = null;
        res = this.executasql.executarConsulta(sql);
        while (res.next()){

            edVolta.setVL_Nota_Fiscal(res.getDouble("VL_Nota_Fiscal"));
            edVolta.setNR_Volumes(res.getLong("NR_Volumes_Observacao"));
            edVolta.setVL_Peso(res.getDouble("VL_Peso_Embarcado"));
            edVolta.setVL_Peso_Cubado(res.getDouble("VL_Peso_Cubado"));
            
            edVolta.setNR_Volumes_Embarcados(res.getLong("NR_Volumes"));

            edVolta.setOID_Unidade(res.getLong("oid_Unidade"));
            
            sqlBusca = "select * from conhecimentos_internacionais where OID_Conhecimento = '" + res.getString("oid_conhecimento") + "'";
            ResultSet rs1 = null;
            rs1 = this.executasql.executarConsulta(sqlBusca);
            while (rs1.next()){
                edVolta.setVL_Total_Frete(rs1.getDouble("VL_Gasto_Remetente1") + 
                        rs1.getDouble("VL_Gasto_Remetente2") + 
                        rs1.getDouble("VL_Gasto_Remetente3") + 
                        rs1.getDouble("VL_Gasto_Remetente4") + 
                        rs1.getDouble("VL_Gasto_Destinatario1") + 
                        rs1.getDouble("VL_Gasto_Destinatario2") + 
                        rs1.getDouble("VL_Gasto_Destinatario3") + 
                        rs1.getDouble("VL_Gasto_Destinatario4"));
            }

        }
      }
      catch(Exception exc){
          exc.printStackTrace();
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao selecionar");
        excecoes.setMetodo("selecionar");
        excecoes.setExc(exc);
        throw excecoes;
      }

      return edVolta;
    }
  
  public byte[] geraViagem_Nacional(Viagem_InternacionalED ed, HttpServletResponse response)throws Excecoes{

      String sql =
          "SELECT Manifestos.OID_Manifesto_Internacional as OID_Manifesto, " +
          "       Manifestos.OID_Unidade, ";
      sql +=  " 'N' as DM_Expresso, ";
      sql +=  " Manifestos.DT_Emissao, ";
      sql +=  " Manifestos.DT_Previsao_Chegada, ";
      sql +=  " Manifestos.HR_Previsao_Chegada, ";
      sql +=  " Manifestos.NR_Manifesto_Internacional as NR_Manifesto, ";
      sql +=  " Manifestos.CD_Roteiro, ";
      sql +=  " 'M' as DM_Tipo_Manifesto, ";
      sql +=  " '' as TX_Observacao, ";
      sql +=  " Conhecimentos.NR_Conhecimento, ";
      sql +=  " Pessoa_Destinatario.NM_Razao_Social as NM_Pessoa_Entrega, ";
      sql +=  " Conhecimentos.DT_Entrega, ";
      sql +=  " Conhecimentos.VL_Peso as NR_Peso, ";
      sql +=  " Conhecimentos.NR_Volumes_Observacao as NR_Volumes, ";
      sql +=  "(Conhecimentos.vl_gasto_remetente1 + Conhecimentos.vl_gasto_remetente2 + Conhecimentos.vl_gasto_remetente3 + Conhecimentos.vl_gasto_remetente4 + " +
      		  "Conhecimentos.vl_gasto_destinatario1 + Conhecimentos.vl_gasto_destinatario2 + Conhecimentos.vl_gasto_destinatario3 + Conhecimentos.vl_gasto_destinatario4) as VL_Total_Frete, ";
      sql +=  " Conhecimentos.VL_Nota_Fiscal, ";
      sql +=  " Produtos.NM_Produto, ";
      sql +=  " Produtos.DM_Tipo, ";
      sql +=  " '' as DM_Tipo_Viagem, ";
      sql +=  " Pessoa_Remetente.NM_Razao_Social as NM_Remetente, ";
      sql +=  " Pessoa_Destinatario.NM_Razao_Social as NM_Destinatario, ";
      sql +=  " Pessoa_Proprietario.NM_Razao_Social as NM_Proprietario, ";
      sql +=  " Pessoa_Motorista.NM_Razao_Social as NM_Motorista, ";
      sql +=  " Veiculos.NR_Placa, ";
      sql +=  " Cidade_Conhecimento.NM_Cidade as NM_Cidade_Destino, ";
      sql +=  " Cidade_Manifesto.NM_Cidade as NM_Cidade_Manifesto";
      sql +=  " from Manifestos_Internacionais Manifestos, Produtos, Veiculos, Cidades Cidade_Manifesto, Cidades Cidade_Conhecimento,  Viagens_Internacionais Viagens," +
      		  " Pessoas Pessoa_Remetente, Pessoas Pessoa_Destinatario, Pessoas Pessoa_Proprietario, Pessoas Pessoa_Motorista, Conhecimentos_Internacionais Conhecimentos ";
      sql += " WHERE  Manifestos.OID_Veiculo = Veiculos.OID_Veiculo ";
      sql += " AND Manifestos.OID_Cidade_Destino = Cidade_Manifesto.OID_Cidade ";
      sql += " AND Manifestos.OID_Pessoa = Pessoa_Motorista.Oid_Pessoa ";
      sql += " AND Viagens.OID_Manifesto_Internacional = Manifestos.OID_Manifesto_Internacional ";
      sql += " AND Viagens.OID_Conhecimento = Conhecimentos.OID_Conhecimento ";
      sql += " AND Conhecimentos.OID_Cidade_Destino = Cidade_Conhecimento.OID_Cidade ";
      sql += " AND Conhecimentos.OID_Produto = Produtos.oid_Produto ";
      sql += " AND Conhecimentos.OID_Pessoa = Pessoa_Remetente.oid_Pessoa ";
      sql += " AND Conhecimentos.OID_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa ";
      sql += " AND Veiculos.OID_Pessoa_Proprietario = Pessoa_Proprietario.oid_Pessoa ";
      if (JavaUtil.doValida(ed.getOID_Manifesto_Internacional())){
 	     sql += " and Manifestos.oid_Manifesto_Internacional = '" + ed.getOID_Manifesto_Internacional() + "'";
 	  }
 	  if (JavaUtil.doValida(String.valueOf(ed.getOID_Unidade()))){
         sql += " and Manifestos.OID_Unidade = " + ed.getOID_Unidade();
       }
 	  if (JavaUtil.doValida(ed.getDT_Emissao_Inicial())){
 	 	 sql += " and Manifestos.dt_emissao >= '" + ed.getDT_Emissao_Inicial() + "'";
 	  }
 	  if (JavaUtil.doValida(ed.getDT_Emissao_Final())){
 		 sql += " and Manifestos.dt_emissao <= '" + ed.getDT_Emissao_Final() + "'";
 	  }
 	  if (JavaUtil.doValida(String.valueOf(ed.getNR_Manifesto_Internacional()))){
 	 	 sql += " and Manifestos.nr_Manifesto_Internacional >= " + ed.getNR_Manifesto_Internacional();
 	  }
 	  if (JavaUtil.doValida(String.valueOf(ed.getNR_Manifesto_Internacional_Final()))){
 	 	 sql += " and Manifestos.nr_Manifesto_Internacional <= " + ed.getNR_Manifesto_Internacional_Final();
 	  }
      sql += " Order by  Manifestos.oid_manifesto_Internacional, Conhecimentos.NR_Conhecimento ";
// System.out.println(sql);
      ResultSet res = executasql.executarConsulta(sql);
      try {
          return new Viagem_InternacionalRL(executasql).imprime_Manifesto(res);
      } finally {
          try {
            res.close();
            res.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
      }
  }
  
  public void geraViagem_Internacional_Multiplo(Viagem_InternacionalED ed, HttpServletResponse response)throws Excecoes{

      String sql = null;
      byte[] b = null;
      ResultSet resL = null;

      sql =  " SELECT ";
      sql += "  Manifestos_Internacionais.OID_MANIFESTO_INTERNACIONAL, ";
      sql += "  Manifestos_Internacionais.NR_MANIFESTO_INTERNACIONAL, ";
      sql += "  Manifestos_Internacionais.DT_EMISSAO            AS DT_EMISSAO_MANIFESTO,  ";
      sql += "  Manifestos_Internacionais.OID_UNIDADE           AS OID_UNIDADE_MANIFESTO, ";
      sql += "  Manifestos_Internacionais.OID_UNIDADE_destino           AS OID_UNIDADE_destino, ";
      sql += "  Manifestos_Internacionais.OID_UNIDADE_fronteira           AS OID_UNIDADE_fronteira, ";
      sql += "  Manifestos_Internacionais.OID_PESSOA            AS OID_PESSOA_MOTORISTA, ";
      sql += "  Manifestos_Internacionais.OID_PESSOA_Permisso   AS OID_PESSOA_Permisso, ";
      sql += "  Manifestos_Internacionais.OID_PESSOA_Permisso2   AS OID_PESSOA_Permisso2, ";
      sql += "  Manifestos_Internacionais.OID_ADUANA            AS OID_ADUANA_ORIGEM, ";
      sql += "  Manifestos_Internacionais.OID_ADUANA_DESTINO    AS OID_ADUANA_DESTINO, ";
      sql += "  Manifestos_Internacionais.OID_CIDADE            AS OID_CIDADE_ORIGEM, ";
      sql += "  Manifestos_Internacionais.OID_CIDADE_DESTINO    AS OID_CIDADE_DESTINO, ";
      sql += "  Manifestos_Internacionais.NM_CIDADE_DESTINO    AS NM_CIDADE_DESTINO, ";
      sql += "  Manifestos_Internacionais.NM_PAIS              AS NM_PAIS, ";
      sql += "  Manifestos_Internacionais.NM_PROPRIETARIO, ";
      sql += "  Manifestos_Internacionais.NM_End_PROPRIETARIO, ";
      sql += "  Manifestos_Internacionais.NM_Cid_uf_pais_PROPRIETARIO, ";
      sql += "  Manifestos_Internacionais.CD_ROTEIRO, ";
      sql += "  Manifestos_Internacionais.OID_CIDADE_ALFANDEGA  AS OID_CIDADE_ALFANDEGA, ";
      sql += "  Manifestos_Internacionais.NM_LACRE        AS NM_LACRE, ";
      sql += "  Manifestos_Internacionais.TX_OBSERVACAO1  AS TX_OBSERVACAO1_MANIF, ";
      sql += "  Manifestos_Internacionais.TX_OBSERVACAO2  AS TX_OBSERVACAO2_MANIF, ";
      sql += "  Manifestos_Internacionais.TX_OBSERVACAO3  AS TX_OBSERVACAO3_MANIF, ";
      sql += "  Manifestos_Internacionais.TX_ROTA1 , ";
      sql += "  Manifestos_Internacionais.TX_ROTA2 , ";
      sql += "  Manifestos_Internacionais.TX_ROTA3 , ";
      sql += "  Manifestos_Internacionais.TX_ROTA4 , ";
      sql += "  Manifestos_Internacionais.TX_ROTA5 , ";
      sql += "  Manifestos_Internacionais.TX_ROTA6 , ";
      sql += "  Manifestos_Internacionais.TX_ROTA7 , ";
      sql += "  Manifestos_Internacionais.TX_ROTA8 , ";
      sql += "  Manifestos_Internacionais.TX_ROTA9 , ";
      sql += "  Manifestos_Internacionais.CD_PAIS                   AS CD_PAIS_MANIF, ";
      sql += "  Manifestos_Internacionais.NR_PERMISSO               AS NR_PERMISSO_MANIF, ";
      sql += "  Manifestos_Internacionais.DM_EXPORTACAO_IMPORTACAO  AS DM_EXPORTACAO_IMPORTACAO_MANIF, ";
      sql += "  Manifestos_Internacionais.DM_TRANSITO_ADUANEIRO  , ";
      sql += "  Manifestos_Internacionais.oid_veiculo_carreta  as nr_placa_carreta, ";
      
      sql += "  Veiculos.NR_PLACA, ";
      sql += "  Veiculos.OID_Pessoa_proprietario as OID_PESSOA_PROPRIETARIO , ";
      sql += "  Modelos_Veiculos.CD_MODELO_VEICULO, ";
      sql += "  Modelos_Veiculos.NM_MODELO_VEICULO  ";
      sql += "  FROM Manifestos_Internacionais, Modelos_Veiculos, Veiculos  ";
      sql += "  WHERE Manifestos_Internacionais.OID_Veiculo   = Veiculos.OID_Veiculo ";
      sql += "  AND Veiculos.OID_MODELO_VEICULO = Modelos_Veiculos.OID_MODELO_VEICULO ";

	  if (JavaUtil.doValida(ed.getOID_Manifesto_Internacional())){
	     sql += " and Manifestos_Internacionais.oid_Manifesto_Internacional = '" + ed.getOID_Manifesto_Internacional() + "'";
	  }
	  if (JavaUtil.doValida(String.valueOf(ed.getOID_Unidade()))){
        sql += " and Manifestos_Internacionais.OID_Unidade = " + ed.getOID_Unidade();
      }
	  if (JavaUtil.doValida(ed.getDT_Emissao_Inicial())){
	 	 sql += " and Manifestos_Internacionais.dt_emissao >= '" + ed.getDT_Emissao_Inicial() + "'";
	  }
	  if (JavaUtil.doValida(ed.getDT_Emissao_Final())){
		 sql += " and Manifestos_Internacionais.dt_emissao <= '" + ed.getDT_Emissao_Final() + "'";
	  }
	  if (JavaUtil.doValida(String.valueOf(ed.getNR_Manifesto_Internacional()))){
	 	 sql += " and Manifestos_Internacionais.nr_Manifesto_Internacional >= " + ed.getNR_Manifesto_Internacional();
	  }
	  if (JavaUtil.doValida(String.valueOf(ed.getNR_Manifesto_Internacional_Final()))){
	 	 sql += " and Manifestos_Internacionais.nr_Manifesto_Internacional <= " + ed.getNR_Manifesto_Internacional_Final();
	  }

	  sql += " Order by Manifestos_Internacionais.NR_Manifesto_Internacional asc";

// System.out.println("TEO MIC Multiplo " + sql);

      Viagem_InternacionalED edVolta = new Viagem_InternacionalED();
      ResultSet res = null;

      try{
          Viagem_InternacionalRL conRL = new Viagem_InternacionalRL(executasql);
          res = this.executasql.executarConsulta(sql.toString());
	      
	      if (ed.getNR_Imprimir()==1){
	         conRL.geraViagem_Internacional_Multiplo(ed, res,response);
	      }else if (ed.getNR_Imprimir()==2){
	              conRL.geraViagem_Internacional_Multiplo(ed, res,response);
          }else{
              conRL.geraViagem_Internacional_Costas(ed, res,response);
          }
      }
      catch (Excecoes e){
        throw e;
      }
      catch(Exception exc){
        Excecoes exce = new Excecoes();
        exce.setExc(exc);
        exce.setMensagem("Erro no método listar múltiplos MIC's.");
        exce.setClasse(this.getClass().getName());
        exce.setMetodo("geraViagem_Internacional_Multiplo(ConhecimentoED ed)");
      }
      
    }

}
