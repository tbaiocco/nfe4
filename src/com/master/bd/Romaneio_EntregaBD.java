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

import com.master.ed.Romaneio_EntregaED;
import com.master.rl.Romaneio_EntregaRL;
import com.master.root.FormataDataBean;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

public class Romaneio_EntregaBD {

  private ExecutaSQL executasql;

  public Romaneio_EntregaBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public Romaneio_EntregaED inclui(Romaneio_EntregaED ed) throws Excecoes{

    String sql = null;
    long valOid = 0;
    String chave = null;

    Romaneio_EntregaED romaneio_EntregaED = new Romaneio_EntregaED();

    try{

      sql =  " SELECT AIDOF.NR_Proximo, AIDOF.OID_AIDOF, AIDOF.NM_Serie ";
      sql += " FROM Parametros_Filiais, AIDOF ";
      sql += " WHERE Parametros_Filiais.OID_AIDOF_Romaneio = AIDOF.OID_AIDOF ";
      sql += " AND Parametros_Filiais.OID_Unidade = " + ed.getOID_Unidade();

      ResultSet rs = null;
      rs = this.executasql.executarConsulta(sql);
      while (rs.next()){
        ed.setNM_Serie(rs.getString("NM_Serie"));
        ed.setNR_Romaneio_Entrega(rs.getLong("NR_Proximo"));
        valOid = rs.getLong("OID_AIDOF");
      }

      sql =  " UPDATE AIDOF SET NR_Proximo= " + (ed.getNR_Romaneio_Entrega() + 1);
      sql += " WHERE OID_AIDOF = " + valOid ;

      int u = executasql.executarUpdate(sql);

      //chave = String.valueOf(ed.getOID_Unidade()) + String.valueOf(ed.getNR_Romaneio_Entrega()) + ed.getNM_Serie();
      chave = String.valueOf(ed.getOID_Unidade()) + String.valueOf(ed.getNR_Romaneio_Entrega());

      ed.setOID_Conhecimento(chave);

      ed.setOID_Romaneio_Entrega(chave);

      sql =  " insert into Romaneios_Entregas (OID_Romaneio_Entrega, NR_Romaneio_Entrega, OID_Pessoa, OID_Veiculo, OID_Unidade, DT_Previsao_Saida, HR_Previsao_Saida, NR_Odometro_Inicial, DT_Emissao, TX_Observacao ) values ";
      sql += "('" + ed.getOID_Romaneio_Entrega() + "'," + ed.getNR_Romaneio_Entrega() + ",'" + ed.getOID_Pessoa() + "','" + ed.getOID_Veiculo() + "'," + ed.getOID_Unidade() + ",'" + ed.getDT_Previsao_Saida() + "','" + ed.getHR_Previsao_Saida() + "'," + ed.getNR_Odometro_Inicial() + ",'" + ed.getDT_Emissao() + "','" + ed.getTX_Observacao() + "')";

      //invoca o metodo executarupdate do objeto
      //executasql. que eh uma referencia ao
      //objeto ExecutSQL, que contem a conexao ativa
      int i = executasql.executarUpdate(sql);
      romaneio_EntregaED.setOID_Romaneio_Entrega(ed.getOID_Romaneio_Entrega());

    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir Romaneio_Entrega");
      excecoes.setMetodo("inclui");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return romaneio_EntregaED;

  }

  public void altera(Romaneio_EntregaED ed) throws Excecoes{

    String sql = null;

    try{

      sql = " update Romaneios_Entregas set DT_Previsao_Chegada = '" + ed.getDT_Previsao_Chegada() + "', DT_Previsao_Saida = '" + ed.getDT_Previsao_Saida() + "', HR_Previsao_Chegada = '" + ed.getHR_Previsao_Chegada() + "', HR_Previsao_Saida = '" + ed.getHR_Previsao_Saida() + "', NR_Odometro_Inicial = " + ed.getNR_Odometro_Inicial() + ", NR_Odometro_Final = " + ed.getNR_Odometro_Final() + ", TX_Observacao = '" + ed.getTX_Observacao() + "'" + ", OID_Pessoa = '" + ed.getOID_Pessoa() + "'" + ", OID_Veiculo = '" + ed.getOID_Veiculo() + "'";
      sql += " where oid_Romaneio_Entrega = '" + ed.getOID_Romaneio_Entrega() + "'";

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar Romaneio_Entrega");
      excecoes.setMetodo("alterar");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void deleta(Romaneio_EntregaED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from Romaneios_Entregas WHERE oid_Romaneio_Entrega = ";
      sql += "('" + ed.getOID_Romaneio_Entrega() + "')";

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao excluir Romaneio_Entrega");
      excecoes.setMetodo("excluir");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public ArrayList lista(Romaneio_EntregaED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();

    try{

      sql =  "SELECT Romaneios_Entregas.OID_Romaneio_Entrega, Romaneios_Entregas.DT_Emissao, Romaneios_Entregas.NR_Romaneio_Entrega, Pessoas.NM_Fantasia, Unidades.CD_Unidade, Veiculos.NR_Placa from Romaneios_Entregas, Unidades, Veiculos, Pessoas ";
      sql += " WHERE Romaneios_Entregas.OID_Unidade = Unidades.OID_Unidade ";
      sql += " AND Pessoas.OID_Pessoa = Unidades.OID_Pessoa ";
      sql += " AND Romaneios_Entregas.OID_Veiculo = Veiculos.OID_Veiculo ";

      if (String.valueOf(ed.getDT_Emissao()) != null && !String.valueOf(ed.getDT_Emissao()).equals("")){
        sql += " and Romaneios_Entregas.DT_Emissao = '" + ed.getDT_Emissao() + "'";
      }

      if (String.valueOf(ed.getNR_Romaneio_Entrega()) != null &&
          !String.valueOf(ed.getNR_Romaneio_Entrega()).equals("0") &&
          !String.valueOf(ed.getNR_Romaneio_Entrega()).equals("null")){
        sql += " and Romaneios_Entregas.NR_Romaneio_Entrega = " + ed.getNR_Romaneio_Entrega();
      }

      if (String.valueOf(ed.getOID_Unidade()) != null &&
          !String.valueOf(ed.getOID_Unidade()).equals("0") &&
          !String.valueOf(ed.getOID_Unidade()).equals("null")){
        sql += " and Romaneios_Entregas.OID_Unidade = " + ed.getOID_Unidade();
      }
      if (String.valueOf(ed.getOID_Pessoa()) != null &&
          !String.valueOf(ed.getOID_Pessoa()).equals("") &&
          !String.valueOf(ed.getOID_Pessoa()).equals("null")){
        sql += " and Romaneios_Entregas.OID_Pessoa = '" + ed.getOID_Pessoa() + "'";
      }
      if (String.valueOf(ed.getOID_Veiculo()) != null &&
          !String.valueOf(ed.getOID_Veiculo()).equals("") &&
          !String.valueOf(ed.getOID_Veiculo()).equals("null")){
        sql += " and Romaneios_Entregas.oid_Veiculo = '" + ed.getOID_Veiculo() + "'";
      }

      sql += " Order by Romaneios_Entregas.NR_Romaneio_Entrega ";

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      FormataDataBean DataFormatada = new FormataDataBean();

      //popula
      while (res.next()){
        Romaneio_EntregaED edVolta = new Romaneio_EntregaED();

        edVolta.setOID_Romaneio_Entrega(res.getString("OID_Romaneio_Entrega"));

        edVolta.setDT_Emissao(res.getString("DT_Emissao"));

        DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
        edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());

        edVolta.setCD_Unidade(res.getString("CD_Unidade"));
        edVolta.setNM_Fantasia(res.getString("NM_Fantasia"));
        edVolta.setNR_Placa(res.getString("NR_Placa"));
        edVolta.setNR_Romaneio_Entrega(res.getLong("NR_Romaneio_Entrega"));

        list.add(edVolta);
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao listar Romaneio_Entrega");
      excecoes.setMetodo("listar");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return list;
  }

  public Romaneio_EntregaED getByRecord(Romaneio_EntregaED ed)throws Excecoes{

    String sql = null;

    Romaneio_EntregaED edVolta = new Romaneio_EntregaED();

    try{

      sql =  " SELECT * from Romaneios_Entregas ";
      sql += " WHERE Romaneios_Entregas.OID_Romaneio_Entrega > 0 ";

      if (String.valueOf(ed.getOID_Romaneio_Entrega()) != null &&
          !String.valueOf(ed.getOID_Romaneio_Entrega()).equals("") &&
          !String.valueOf(ed.getOID_Romaneio_Entrega()).equals("null")){
        sql += " and OID_Romaneio_Entrega = '" + ed.getOID_Romaneio_Entrega() + "'";
      }

      if (String.valueOf(ed.getNR_Romaneio_Entrega()) != null &&
          !String.valueOf(ed.getNR_Romaneio_Entrega()).equals("0") &&
          !String.valueOf(ed.getNR_Romaneio_Entrega()).equals("null")){
        sql += " and NR_Romaneio_Entrega = " + ed.getNR_Romaneio_Entrega();
        sql += " and OID_Unidade = " + ed.getOID_Unidade();
      }

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      FormataDataBean DataFormatada = new FormataDataBean();

      while (res.next()){
//// System.out.println("Romaneio 1 ->> " + res.getString("OID_Romaneio_Entrega"));
        edVolta.setOID_Romaneio_Entrega(res.getString("OID_Romaneio_Entrega"));
        edVolta.setOID_Veiculo(res.getString("OID_Veiculo"));
        edVolta.setOID_Pessoa(res.getString("OID_Pessoa"));
        edVolta.setOID_Unidade(res.getLong("OID_Unidade"));

        edVolta.setDT_Previsao_Chegada(res.getString("DT_Previsao_Chegada"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Previsao_Chegada());
        edVolta.setDT_Previsao_Chegada(DataFormatada.getDT_FormataData());

        edVolta.setDT_Previsao_Saida(res.getString("DT_Previsao_Saida"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Previsao_Saida());
        edVolta.setDT_Previsao_Saida(DataFormatada.getDT_FormataData());
//// System.out.println("Romaneio 5" );

        edVolta.setDT_Emissao(res.getString("DT_Emissao"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
        edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());

        edVolta.setHR_Previsao_Chegada(res.getString("HR_Previsao_Chegada"));
        edVolta.setHR_Previsao_Saida(res.getString("HR_Previsao_Saida"));
        edVolta.setNR_Romaneio_Entrega(res.getLong("NR_Romaneio_Entrega"));
        edVolta.setNR_Odometro_Inicial(res.getLong("NR_Odometro_Inicial"));
        edVolta.setNR_Odometro_Final(res.getLong("NR_Odometro_Final"));
        edVolta.setTX_Observacao(res.getString("TX_Observacao"));
//// System.out.println("Romaneio 99" );

      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao selecionar Romaneio_Entrega");
      excecoes.setMetodo("seleção");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return edVolta;
  }

  public void geraRelatorio(Romaneio_EntregaED ed)throws Excecoes{

    String sql = null;

    Romaneio_EntregaED edVolta = new Romaneio_EntregaED();

    try{

      sql = "select * from Romaneios_Entregas where oid_Romaneio_Entrega > 0";

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      Romaneio_EntregaRL Romaneio_Entrega_rl = new Romaneio_EntregaRL();
      Romaneio_Entrega_rl.geraRelatEstoque(res);
    }
    catch (Excecoes e){
      throw e;
    }
    catch(Exception exc){
      Excecoes exce = new Excecoes();
      exce.setExc(exc);
      exce.setMensagem("Erro no método listar");
      exce.setClasse(this.getClass().getName());
      exce.setMetodo("geraRelatorio(Romaneio_EntregaED ed)");
    }

  }

}