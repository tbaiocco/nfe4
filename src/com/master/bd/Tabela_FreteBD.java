package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import com.master.ed.Tabela_FreteED;
import com.master.rl.JasperRL;
import com.master.rl.Tabela_FreteRL;
import com.master.root.CidadeBean;
import com.master.root.EstadoBean;
import com.master.root.FormataDataBean;
import com.master.root.PaisBean;
import com.master.root.PessoaBean;
import com.master.root.Regiao_EstadoBean;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Utilitaria;
import com.master.util.bd.ExecutaSQL;

public class Tabela_FreteBD {
  Calcula_FreteBD Calcula_FreteBD = null;

  private ExecutaSQL executasql;
  Utilitaria util = new Utilitaria ();

  public Tabela_FreteBD (ExecutaSQL sql) {
    this.executasql = sql;
    util = new Utilitaria (sql);
  }

  public byte[] imprimeTabela (Tabela_FreteED ed) throws Excecoes {

    String sql = null;
    byte[] b = null;

    sql = "SELECT Tabelas_Tratores.OID_Tabela_Frete, Tabelas_Tratores.OID_Cidade_Origem, Tabelas_Tratores.OID_Cidade, Tabelas_Tratores.OID_Pessoa, QT_KM, VL_Retro, VL_Linha600, VL_Trator_G, VL_Trator_M, VL_Trator_P, VL_Empilhadeira, VL_Colheitadeira_G, VL_Colheitadeira_M, VL_Colheitadeira_P, VL_Plataforma_G,  VL_Plataforma_M,  VL_Plataforma_P,  VL_Esteira, Tabelas_Tratores.Dt_Stamp, Tabelas_Tratores.Usuario_Stamp, Tabelas_Tratores.Dm_Stamp, Pessoas.NM_Razao_Social, Cidade_Origem.CD_Cidade, Cidade_Origem.NM_Cidade as NM_Cidade_Origem, Cidade_Destino.CD_Cidade, Cidade_Destino.NM_Cidade as NM_Cidade_Destino, Taxas.PE_ALIQUOTA_ICMS ";
    sql += " FROM Tabelas_Tratores, Pessoas, Cidades Cidade_Origem , Cidades Cidade_Destino, Regioes_Estados Regiao_Origem, Regioes_Estados Regiao_Destino, Taxas  ";
    sql += " WHERE Tabelas_Tratores.OID_Cidade_Origem = Cidade_Origem.OID_Cidade AND Tabelas_Tratores.OID_Cidade = Cidade_Destino.OID_Cidade AND Tabelas_Tratores.OID_Pessoa = Pessoas.OID_Pessoa  ";
    sql += " AND Cidade_Origem.OID_Regiao_Estado = Regiao_Origem.OID_Regiao_Estado ";
    sql += " AND Cidade_Destino.OID_Regiao_Estado = Regiao_Destino.OID_Regiao_Estado ";
    sql += " AND Regiao_Origem.OID_Estado = Taxas.oid_estado  ";
    sql += " AND Regiao_Destino.OID_Estado = Taxas.oid_estado_Destino  ";
    sql += " AND Tabelas_Tratores.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";

    if (ed.getOID_Cidade_Destino () > 0) {
      sql += " AND Tabelas_Tratores.OID_Cidade = " + ed.getOID_Cidade_Destino ();
    }
    sql += " ORDER BY  Cidade_Origem.NM_Cidade, Cidade_Destino.NM_Cidade";
    try {

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql.toString ());

      Tabela_FreteRL Tabela_FreteRL = new Tabela_FreteRL ();
      b = Tabela_FreteRL.imprimeTabela (res);

    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro no m�todo listar");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("imprimeTabela()");
    }
    return b;
  }

  public void reajustaTabelaKieling (Tabela_FreteED ed) throws Excecoes {

    String sql = null;
    long valOid = 0;
    String chave = null;
    Data data_Hoje = new Data ();

    try {

      sql = " SELECT * FROM Tabelas_Fretes, Pessoas ";
      sql += " WHERE Tabelas_Fretes.OID_Pessoa = Pessoas.oid_pessoa ";
      sql += " AND   Tabelas_Fretes.PE_Ultimo_Reajuste = 0 ";
      if (ed.getOID_Pessoa () != null && ed.getOID_Pessoa ().length () > 4) {
        sql += " AND Tabelas_Fretes.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
      }
      if (ed.getOid_Produto () > 0) {
        sql += " AND Tabelas_Fretes.OID_Produto = " + ed.getOid_Produto ();
      }
      if (ed.getOID_Cidade_Origem () > 0) {
        sql += " AND Tabelas_Fretes.OID_Cidade_Origem = " + ed.getOID_Cidade_Origem ();
      }
      if (ed.getOID_Cidade_Destino () > 0) {
        sql += " AND Tabelas_Fretes.OID_Cidade_Destino = " + ed.getOID_Cidade_Destino ();
      }

      sql += " ORDER BY Pessoas.NM_Razao_Social ";


      ResultSet rs = null;
      rs = this.executasql.executarConsulta (sql);
      Data data = new Data ();
      FormataDataBean DataFormatada = new FormataDataBean ();
      String NM_Razao_Social = "";
      double PE_Ajuste = 0;
      int q = 0;
      while (rs.next ()) {
        q++;
        if (!NM_Razao_Social.equals (rs.getString ("NM_Razao_Social"))) {
          NM_Razao_Social = rs.getString ("NM_Razao_Social");
        }

        PE_Ajuste = ed.getPE_Reajuste ();

        //PE_Ajuste=rs.getDouble("pe_reajuste_tabela");==>> DO CLIENTE

        if (PE_Ajuste > 0) {
          PE_Ajuste = (1 + (PE_Ajuste / 100));

          sql = " update Tabelas_Fretes set ";
          sql += "  DT_VIGENCIA = '17/09/2007' ";
          sql += " ,DT_VALIDADE = '01/12/2009' ";
          sql += " ,PE_Ultimo_Reajuste = " + PE_Ajuste;
//            sql += " ,VL_C_Ate25 = " + rs.getDouble("VL_C_Ate25")*PE_Ajuste;
//            sql += " ,VL_C_Ate50 = " + rs.getDouble("VL_C_Ate50")*PE_Ajuste;
//            sql += " ,VL_C_Ate300 = " + rs.getDouble("VL_C_Ate300")*PE_Ajuste;
//            sql += " ,VL_C_Ate500 = " + rs.getDouble("VL_C_Ate500")*PE_Ajuste;
//            sql += " ,VL_C_Ate1000 = " + rs.getDouble("VL_C_Ate1000")*PE_Ajuste;
//            sql += " ,VL_C_Acima1000 = " + rs.getDouble("VL_C_Acima1000")*PE_Ajuste;
//            sql += " ,VL_C_Taxa_Minima = " + rs.getDouble("VL_C_Taxa_Minima")*PE_Ajuste;


//            sql += " ,VL_TX_Coleta = " + rs.getDouble("VL_TX_Coleta")*PE_Ajuste;
//            sql += " ,VL_TX_Entrega = " + rs.getDouble("VL_TX_Entrega")*PE_Ajuste;
//            sql += " ,VL_TX_Col_Urg_200 = " + rs.getDouble("VL_TX_Col_Urg_200")*PE_Ajuste;
//            sql += " ,VL_TX_Col_Urg_1000 = " + rs.getDouble("VL_TX_Col_Urg_1000")*PE_Ajuste;
//            sql += " ,VL_TX_Col_Urg_Ton = " + rs.getDouble("VL_TX_Col_Urg_Ton")*PE_Ajuste;
//            sql += " ,VL_TX_Exc_Coleta = " + rs.getDouble("VL_TX_Exc_Coleta")*PE_Ajuste;
//            sql += " ,VL_TX_Ent_Urg_200 = " + rs.getDouble("VL_TX_Ent_Urg_200")*PE_Ajuste;
//            sql += " ,VL_TX_Ent_Urg_1000 = " + rs.getDouble("VL_TX_Ent_Urg_1000")*PE_Ajuste;
//            sql += " ,VL_TX_Ent_Urg_Ton = " + rs.getDouble("VL_TX_Ent_Urg_Ton")*PE_Ajuste;
//            sql += " ,VL_TX_Exc_Entrega = " + rs.getDouble("VL_TX_Exc_Entrega")*PE_Ajuste;


//            sql += " ,VL_D_Ate1C = " + rs.getDouble("VL_D_Ate1C")*PE_Ajuste;
//            sql += " ,VL_D_Ate2C = " + rs.getDouble("VL_D_Ate2C")*PE_Ajuste;
//            sql += " ,VL_D_Ate3C = " + rs.getDouble("VL_D_Ate3C")*PE_Ajuste;
//            sql += " ,VL_D_Ate4C = " + rs.getDouble("VL_D_Ate4C")*PE_Ajuste;
//            sql += " ,VL_D_Ate5C = " + rs.getDouble("VL_D_Ate5C")*PE_Ajuste;
//            sql += " ,VL_D_Ate6C = " + rs.getDouble("VL_D_Ate6C")*PE_Ajuste;
//            sql += " ,VL_D_Ate7C = " + rs.getDouble("VL_D_Ate7C")*PE_Ajuste;
//            sql += " ,VL_D_Ate8C = " + rs.getDouble("VL_D_Ate8C")*PE_Ajuste;
//            sql += " ,VL_D_Ate9C = " + rs.getDouble("VL_D_Ate9C")*PE_Ajuste;
//            sql += " ,VL_D_Ate10C = " + rs.getDouble("VL_D_Ate10C")*PE_Ajuste;
//
//            sql += " ,VL_D_Ate1D = " + rs.getDouble("VL_D_Ate1D")*PE_Ajuste;
//            sql += " ,VL_D_Ate2D = " + rs.getDouble("VL_D_Ate2D")*PE_Ajuste;
//            sql += " ,VL_D_Ate3D = " + rs.getDouble("VL_D_Ate3D")*PE_Ajuste;
//            sql += " ,VL_D_Ate4D = " + rs.getDouble("VL_D_Ate4D")*PE_Ajuste;
//            sql += " ,VL_D_Ate5D = " + rs.getDouble("VL_D_Ate5D")*PE_Ajuste;
//            sql += " ,VL_D_Ate6D = " + rs.getDouble("VL_D_Ate6D")*PE_Ajuste;
//            sql += " ,VL_D_Ate7D = " + rs.getDouble("VL_D_Ate7D")*PE_Ajuste;
//            sql += " ,VL_D_Ate8D = " + rs.getDouble("VL_D_Ate8D")*PE_Ajuste;
//            sql += " ,VL_D_Ate9D = " + rs.getDouble("VL_D_Ate9D")*PE_Ajuste;
//            sql += " ,VL_D_Ate10D = " + rs.getDouble("VL_D_Ate10D")*PE_Ajuste;
//            sql += " ,VL_D_ExcedenteC = " + rs.getDouble("VL_D_ExcedenteC")*PE_Ajuste;
//            sql += " ,VL_D_ExcedenteD = " + rs.getDouble("VL_D_ExcedenteD")*PE_Ajuste;

          sql += " ,VL_R_Ate10 = round (" + rs.getDouble ("VL_R_Ate10") * PE_Ajuste + ",2)";
          sql += " ,VL_R_Ate20 = round (" + rs.getDouble ("VL_R_Ate20") * PE_Ajuste + ",2)";
          sql += " ,VL_R_Ate30 = round (" + rs.getDouble ("VL_R_Ate30") * PE_Ajuste + ",2)";
          sql += " ,VL_R_Ate50 = round (" + rs.getDouble ("VL_R_Ate50") * PE_Ajuste + ",2)";
          sql += " ,VL_R_Ate70 = round (" + rs.getDouble ("VL_R_Ate70") * PE_Ajuste + ",2)";
          sql += " ,VL_R_Ate100 = round (" + rs.getDouble ("VL_R_Ate100") * PE_Ajuste + ",2)";
          sql += " ,VL_R_Ate150 = round (" + rs.getDouble ("VL_R_Ate150") * PE_Ajuste + ",2)";
          sql += " ,VL_R_Ate200 = round (" + rs.getDouble ("VL_R_Ate200") * PE_Ajuste + ",2)";
          sql += " ,VL_R_Acima200 = round (" + rs.getDouble ("VL_R_Acima200") * PE_Ajuste + ",2)";
          sql += " ,VL_Pedagio = 2.8";

//            sql += " ,VL_Ademe_Minimo = " + rs.getDouble("VL_Ademe_Minimo")*PE_Ajuste;
//            sql += " ,VL_Suframa_Minimo = " + rs.getDouble("VL_Suframa_Minimo")*PE_Ajuste;
//            sql += " ,VL_Fluvial_Minimo = " + rs.getDouble("VL_Fluvial_Minimo")*PE_Ajuste;
//            sql += " ,VL_TX_KM_Rodado = " + rs.getDouble("VL_TX_KM_Rodado")*PE_Ajuste;

          sql += " where Tabelas_Fretes.oid_tabela_frete = '" + rs.getString ("oid_tabela_frete") + "'";

          int i = executasql.executarUpdate (sql);

        }
      }

    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao reajustar valores da tabela de frete");
      excecoes.setMetodo ("inclui");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void reajustaTabela (Tabela_FreteED ed) throws Excecoes {

    String sql = null;
    long valOid = 0;
    String chave = null;
    Data data_Hoje = new Data ();

    try {

      sql = " SELECT * FROM Tabelas_Fretes, Pessoas ";
      sql += " WHERE Tabelas_Fretes.OID_Pessoa = Pessoas.oid_pessoa ";
      sql += " AND   (Tabelas_Fretes.PE_Ultimo_Reajuste = 0 OR Tabelas_Fretes.PE_Ultimo_Reajuste is null) ";
      if (ed.getOID_Pessoa () != null && ed.getOID_Pessoa ().length () > 4) {
        sql += " AND Tabelas_Fretes.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
      }
      if (ed.getOid_Produto () > 0) {
        sql += " AND Tabelas_Fretes.OID_Produto = " + ed.getOid_Produto ();
      }

      sql += " ORDER BY Pessoas.NM_Razao_Social ";


      ResultSet rs = null;
      rs = this.executasql.executarConsulta (sql);
      Data data = new Data ();
      FormataDataBean DataFormatada = new FormataDataBean ();
      String NM_Razao_Social = "";
      double PE_Ajuste = 0;
      int q = 0;
      while (rs.next ()) {
        q++;
        if (!NM_Razao_Social.equals (rs.getString ("NM_Razao_Social"))) {
          NM_Razao_Social = rs.getString ("NM_Razao_Social");
        }

        PE_Ajuste = ed.getPE_Reajuste ();

        //PE_Ajuste=rs.getDouble("pe_reajuste_tabela");==>> DO CLIENTE

        if (PE_Ajuste > 0) {
          PE_Ajuste = (1 + (PE_Ajuste / 100));

          sql = " update Tabelas_Fretes set ";
          sql += "  PE_Ultimo_Reajuste = " + PE_Ajuste;
          sql += " ,VL_C_Ate25 = " + rs.getDouble ("VL_C_Ate25") * PE_Ajuste;
          sql += " ,VL_C_Ate50 = " + rs.getDouble ("VL_C_Ate50") * PE_Ajuste;
          sql += " ,VL_C_Ate300 = " + rs.getDouble ("VL_C_Ate300") * PE_Ajuste;
          sql += " ,VL_C_Ate500 = " + rs.getDouble ("VL_C_Ate500") * PE_Ajuste;
          sql += " ,VL_C_Ate1000 = " + rs.getDouble ("VL_C_Ate1000") * PE_Ajuste;
          sql += " ,VL_C_Acima1000 = " + rs.getDouble ("VL_C_Acima1000") * PE_Ajuste;
          sql += " ,VL_C_Taxa_Minima = " + rs.getDouble ("VL_C_Taxa_Minima") * PE_Ajuste;

          sql += " ,VL_TX_Coleta = " + rs.getDouble ("VL_TX_Coleta") * PE_Ajuste;
          sql += " ,VL_TX_Entrega = " + rs.getDouble ("VL_TX_Entrega") * PE_Ajuste;
          sql += " ,VL_TX_Col_Urg_200 = " + rs.getDouble ("VL_TX_Col_Urg_200") * PE_Ajuste;
          sql += " ,VL_TX_Col_Urg_1000 = " + rs.getDouble ("VL_TX_Col_Urg_1000") * PE_Ajuste;
          sql += " ,VL_TX_Col_Urg_Ton = " + rs.getDouble ("VL_TX_Col_Urg_Ton") * PE_Ajuste;
          sql += " ,VL_TX_Exc_Coleta = " + rs.getDouble ("VL_TX_Exc_Coleta") * PE_Ajuste;
          sql += " ,VL_TX_Ent_Urg_200 = " + rs.getDouble ("VL_TX_Ent_Urg_200") * PE_Ajuste;
          sql += " ,VL_TX_Ent_Urg_1000 = " + rs.getDouble ("VL_TX_Ent_Urg_1000") * PE_Ajuste;
          sql += " ,VL_TX_Ent_Urg_Ton = " + rs.getDouble ("VL_TX_Ent_Urg_Ton") * PE_Ajuste;
          sql += " ,VL_TX_Exc_Entrega = " + rs.getDouble ("VL_TX_Exc_Entrega") * PE_Ajuste;

          sql += " ,VL_D_Ate001C = " + rs.getDouble ("VL_D_Ate001C") * PE_Ajuste;
          sql += " ,VL_D_Ate025C = " + rs.getDouble ("VL_D_Ate025C") * PE_Ajuste;
          sql += " ,VL_D_Ate030C = " + rs.getDouble ("VL_D_Ate030C") * PE_Ajuste;
          sql += " ,VL_D_Ate050C = " + rs.getDouble ("VL_D_Ate050C") * PE_Ajuste;
          sql += " ,VL_D_Ate075C = " + rs.getDouble ("VL_D_Ate075C") * PE_Ajuste;
          sql += " ,VL_D_Ate1C = " + rs.getDouble ("VL_D_Ate1C") * PE_Ajuste;
          sql += " ,VL_D_Ate2C = " + rs.getDouble ("VL_D_Ate2C") * PE_Ajuste;
          sql += " ,VL_D_Ate3C = " + rs.getDouble ("VL_D_Ate3C") * PE_Ajuste;
          sql += " ,VL_D_Ate4C = " + rs.getDouble ("VL_D_Ate4C") * PE_Ajuste;
          sql += " ,VL_D_Ate5C = " + rs.getDouble ("VL_D_Ate5C") * PE_Ajuste;
          sql += " ,VL_D_Ate6C = " + rs.getDouble ("VL_D_Ate6C") * PE_Ajuste;
          sql += " ,VL_D_Ate7C = " + rs.getDouble ("VL_D_Ate7C") * PE_Ajuste;
          sql += " ,VL_D_Ate8C = " + rs.getDouble ("VL_D_Ate8C") * PE_Ajuste;
          sql += " ,VL_D_Ate9C = " + rs.getDouble ("VL_D_Ate9C") * PE_Ajuste;
          sql += " ,VL_D_Ate10C = " + rs.getDouble ("VL_D_Ate10C") * PE_Ajuste;
          sql += " ,VL_D_Ate11C = " + rs.getDouble ("VL_D_Ate11C") * PE_Ajuste;
          sql += " ,VL_D_Ate12C = " + rs.getDouble ("VL_D_Ate12C") * PE_Ajuste;
          sql += " ,VL_D_Ate13C = " + rs.getDouble ("VL_D_Ate13C") * PE_Ajuste;
          sql += " ,VL_D_Ate14C = " + rs.getDouble ("VL_D_Ate14C") * PE_Ajuste;
          sql += " ,VL_D_Ate15C = " + rs.getDouble ("VL_D_Ate15C") * PE_Ajuste;
          sql += " ,VL_D_Ate16C = " + rs.getDouble ("VL_D_Ate16C") * PE_Ajuste;
          sql += " ,VL_D_Ate17C = " + rs.getDouble ("VL_D_Ate17C") * PE_Ajuste;
          sql += " ,VL_D_Ate18C = " + rs.getDouble ("VL_D_Ate18C") * PE_Ajuste;
          sql += " ,VL_D_Ate19C = " + rs.getDouble ("VL_D_Ate19C") * PE_Ajuste;
          sql += " ,VL_D_Ate20C = " + rs.getDouble ("VL_D_Ate20C") * PE_Ajuste;
          sql += " ,VL_D_Ate21C = " + rs.getDouble ("VL_D_Ate21C") * PE_Ajuste;
          sql += " ,VL_D_Ate22C = " + rs.getDouble ("VL_D_Ate22C") * PE_Ajuste;
          sql += " ,VL_D_Ate23C = " + rs.getDouble ("VL_D_Ate23C") * PE_Ajuste;
          sql += " ,VL_D_Ate24C = " + rs.getDouble ("VL_D_Ate24C") * PE_Ajuste;
          sql += " ,VL_D_Ate25C = " + rs.getDouble ("VL_D_Ate25C") * PE_Ajuste;
          sql += " ,VL_D_Ate26C = " + rs.getDouble ("VL_D_Ate26C") * PE_Ajuste;
          sql += " ,VL_D_Ate27C = " + rs.getDouble ("VL_D_Ate27C") * PE_Ajuste;
          sql += " ,VL_D_Ate28C = " + rs.getDouble ("VL_D_Ate28C") * PE_Ajuste;
          sql += " ,VL_D_Ate29C = " + rs.getDouble ("VL_D_Ate29C") * PE_Ajuste;
          sql += " ,VL_D_Ate30C = " + rs.getDouble ("VL_D_Ate30C") * PE_Ajuste;
          sql += " ,VL_D_Ate31C = " + rs.getDouble ("VL_D_Ate31C") * PE_Ajuste;
          sql += " ,VL_D_Ate32C = " + rs.getDouble ("VL_D_Ate32C") * PE_Ajuste;
          sql += " ,VL_D_Ate33C = " + rs.getDouble ("VL_D_Ate33C") * PE_Ajuste;
          sql += " ,VL_D_Ate34C = " + rs.getDouble ("VL_D_Ate34C") * PE_Ajuste;
          sql += " ,VL_D_Ate35C = " + rs.getDouble ("VL_D_Ate35C") * PE_Ajuste;
          sql += " ,VL_D_Ate50C = " + rs.getDouble ("VL_D_Ate50C") * PE_Ajuste;
          sql += " ,VL_D_Ate100C = " + rs.getDouble ("VL_D_Ate100C") * PE_Ajuste;

          sql += " ,VL_D_Ate1D = " + rs.getDouble ("VL_D_Ate1D") * PE_Ajuste;
          sql += " ,VL_D_Ate2D = " + rs.getDouble ("VL_D_Ate2D") * PE_Ajuste;
          sql += " ,VL_D_Ate3D = " + rs.getDouble ("VL_D_Ate3D") * PE_Ajuste;
          sql += " ,VL_D_Ate4D = " + rs.getDouble ("VL_D_Ate4D") * PE_Ajuste;
          sql += " ,VL_D_Ate5D = " + rs.getDouble ("VL_D_Ate5D") * PE_Ajuste;
          sql += " ,VL_D_Ate6D = " + rs.getDouble ("VL_D_Ate6D") * PE_Ajuste;
          sql += " ,VL_D_Ate7D = " + rs.getDouble ("VL_D_Ate7D") * PE_Ajuste;
          sql += " ,VL_D_Ate8D = " + rs.getDouble ("VL_D_Ate8D") * PE_Ajuste;
          sql += " ,VL_D_Ate9D = " + rs.getDouble ("VL_D_Ate9D") * PE_Ajuste;
          sql += " ,VL_D_Ate10D = " + rs.getDouble ("VL_D_Ate10D") * PE_Ajuste;
          sql += " ,VL_D_Ate11D = " + rs.getDouble ("VL_D_Ate11D") * PE_Ajuste;
          sql += " ,VL_D_Ate12D = " + rs.getDouble ("VL_D_Ate12D") * PE_Ajuste;
          sql += " ,VL_D_Ate13D = " + rs.getDouble ("VL_D_Ate13D") * PE_Ajuste;
          sql += " ,VL_D_Ate14D = " + rs.getDouble ("VL_D_Ate14D") * PE_Ajuste;
          sql += " ,VL_D_Ate15D = " + rs.getDouble ("VL_D_Ate15D") * PE_Ajuste;
          sql += " ,VL_D_Ate16D = " + rs.getDouble ("VL_D_Ate16D") * PE_Ajuste;
          sql += " ,VL_D_Ate17D = " + rs.getDouble ("VL_D_Ate17D") * PE_Ajuste;
          sql += " ,VL_D_Ate18D = " + rs.getDouble ("VL_D_Ate18D") * PE_Ajuste;
          sql += " ,VL_D_Ate19D = " + rs.getDouble ("VL_D_Ate19D") * PE_Ajuste;
          sql += " ,VL_D_Ate20D = " + rs.getDouble ("VL_D_Ate20D") * PE_Ajuste;
          sql += " ,VL_D_Ate21D = " + rs.getDouble ("VL_D_Ate21D") * PE_Ajuste;
          sql += " ,VL_D_Ate22D = " + rs.getDouble ("VL_D_Ate22D") * PE_Ajuste;
          sql += " ,VL_D_Ate23D = " + rs.getDouble ("VL_D_Ate23D") * PE_Ajuste;
          sql += " ,VL_D_Ate24D = " + rs.getDouble ("VL_D_Ate24D") * PE_Ajuste;
          sql += " ,VL_D_Ate25D = " + rs.getDouble ("VL_D_Ate25D") * PE_Ajuste;
          sql += " ,VL_D_Ate26D = " + rs.getDouble ("VL_D_Ate26D") * PE_Ajuste;
          sql += " ,VL_D_Ate27D = " + rs.getDouble ("VL_D_Ate27D") * PE_Ajuste;
          sql += " ,VL_D_Ate28D = " + rs.getDouble ("VL_D_Ate28D") * PE_Ajuste;
          sql += " ,VL_D_Ate29D = " + rs.getDouble ("VL_D_Ate29D") * PE_Ajuste;
          sql += " ,VL_D_Ate30D = " + rs.getDouble ("VL_D_Ate30D") * PE_Ajuste;
          sql += " ,VL_D_Ate50D = " + rs.getDouble ("VL_D_Ate50D") * PE_Ajuste;
          sql += " ,VL_D_Ate100D = " + rs.getDouble ("VL_D_Ate100D") * PE_Ajuste;
          sql += " ,VL_D_ExcedenteC = " + rs.getDouble ("VL_D_ExcedenteC") * PE_Ajuste;
          sql += " ,VL_D_Excedente2C = " + rs.getDouble ("VL_D_Excedente2C") * PE_Ajuste;
          sql += " ,VL_D_Excedente3C = " + rs.getDouble ("VL_D_Excedente3C") * PE_Ajuste;
          sql += " ,VL_D_ExcedenteD = " + rs.getDouble ("VL_D_ExcedenteD") * PE_Ajuste;
          sql += " ,VL_D_Excedente2D = " + rs.getDouble ("VL_D_Excedente2D") * PE_Ajuste;
          sql += " ,VL_D_Excedente3D = " + rs.getDouble ("VL_D_Excedente3D") * PE_Ajuste;
          sql += " ,VL_R_Ate10 = round (" + rs.getDouble ("VL_R_Ate10") * PE_Ajuste + ",2)";
          sql += " ,VL_R_Ate20 = round (" + rs.getDouble ("VL_R_Ate20") * PE_Ajuste + ",2)";
          sql += " ,VL_R_Ate30 = round (" + rs.getDouble ("VL_R_Ate30") * PE_Ajuste + ",2)";
          sql += " ,VL_R_Ate50 = round (" + rs.getDouble ("VL_R_Ate50") * PE_Ajuste + ",2)";
          sql += " ,VL_R_Ate70 = round (" + rs.getDouble ("VL_R_Ate70") * PE_Ajuste + ",2)";
          sql += " ,VL_R_Ate100 = round (" + rs.getDouble ("VL_R_Ate100") * PE_Ajuste + ",2)";
          sql += " ,VL_R_Ate150 = round (" + rs.getDouble ("VL_R_Ate150") * PE_Ajuste + ",2)";
          sql += " ,VL_R_Ate200 = round (" + rs.getDouble ("VL_R_Ate200") * PE_Ajuste + ",2)";
          sql += " ,VL_R_Acima200 = round (" + rs.getDouble ("VL_R_Acima200") * PE_Ajuste + ",2)";
          sql += " ,VL_Ademe_Minimo = " + rs.getDouble ("VL_Ademe_Minimo") * PE_Ajuste;
          sql += " ,VL_Suframa_Minimo = " + rs.getDouble ("VL_Suframa_Minimo") * PE_Ajuste;
          sql += " ,VL_Fluvial_Minimo = " + rs.getDouble ("VL_Fluvial_Minimo") * PE_Ajuste;
          sql += " ,VL_TX_KM_Rodado = " + rs.getDouble ("VL_TX_KM_Rodado") * PE_Ajuste;

          sql += " where Tabelas_Fretes.oid_tabela_frete = '" + rs.getString ("oid_tabela_frete") + "'";

          int i = executasql.executarUpdate (sql);

        }
      }

    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao reajustar valores da tabela de frete");
      excecoes.setMetodo ("inclui");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public boolean tabelaExiste (Tabela_FreteED tabela) throws Excecoes {
    String sql =
        " select Tabelas_Fretes.NR_Peso_Inicial, " +
        "        Tabelas_Fretes.NR_Peso_Final " +
        " from Tabelas_Fretes " +
        " where Tabelas_Fretes.oid_Pessoa = '" + tabela.getOID_Pessoa () + "' " +
        "   and Tabelas_Fretes.oid_Produto = " + tabela.getOID_Produto_Origem () +
        "   and Tabelas_Fretes.oid_Modal = " + tabela.getOID_Modal () +
        "   and Tabelas_Fretes.oid_Origem = " + tabela.getOID_Cidade_Origem () +
        "   and Tabelas_Fretes.oid_Destino = " + tabela.getOID_Cidade_Destino ();
    if (tabela.getDM_Origem () != null && !tabela.getDM_Origem ().equals ("")) {
      sql += " and Tabelas_Fretes.DM_Origem = '" + tabela.getDM_Origem () + "'";
    }
    if (tabela.getDM_Destino () != null && !tabela.getDM_Destino ().equals ("")) {
      sql += " and Tabelas_Fretes.DM_Destino = '" + tabela.getDM_Destino () + "'";
    }


    ResultSet res = executasql.executarConsulta (sql);
    try {
      while (res.next ()) {
        double NR_Peso_Inicial = res.getDouble ("NR_Peso_Inicial");
        double NR_Peso_Final = res.getDouble ("NR_Peso_Final");
        if (tabela.getNR_Peso_Inicial () >= NR_Peso_Inicial && tabela.getNR_Peso_Inicial () < NR_Peso_Final) {
          return true;
        }
        else if (tabela.getNR_Peso_Final () > NR_Peso_Inicial && tabela.getNR_Peso_Final () <= NR_Peso_Final) {
          return true;
        }
      }
      return false;
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "tabelaExiste(Tabela_FreteED tabela)");
    }
  }

    
	public ArrayList lista(Tabela_FreteED ed) throws Excecoes {

		ArrayList list = new ArrayList();
		try {
	      String sql =" SELECT  " +
			          "		   Tabelas_Fretes.OID_Tabela_Frete " +
			          "       ,Tabelas_Fretes.OID_Produto " +
			          "       ,Tabelas_Fretes.OID_Modal " +
			          "       ,Tabelas_Fretes.OID_Pessoa " +
			          "       ,Tabelas_Fretes.NR_Peso_Inicial " +
			          "       ,Tabelas_Fretes.NR_Peso_Final " +
			          "       ,Tabelas_Fretes.VL_Frete " +
			          "       ,Tabelas_Fretes.VL_Frete_Kg " +
			          "       ,Tabelas_Fretes.VL_Taxas " +
			          "       ,Tabelas_Fretes.PE_Ad_Valorem " +
			          "       ,Tabelas_Fretes.VL_Outros1 " +
			          "       ,Tabelas_Fretes.VL_Outros2 " +
			          "       ,Tabelas_Fretes.VL_Outros3 " +
			          "       ,Tabelas_Fretes.VL_Outros4 " +
			          "       ,Tabelas_Fretes.VL_Outros5 " +
			          "       ,Tabelas_Fretes.VL_Outros6 " +
			          "       ,Tabelas_Fretes.VL_Outros7 " +
			          "       ,Tabelas_Fretes.VL_Outros8 " +
			          "       ,Tabelas_Fretes.VL_Outros9 " +
			          "       ,Tabelas_Fretes.Dt_Stamp " +
			          "       ,Tabelas_Fretes.Usuario_Stamp " +
			          "       ,Tabelas_Fretes.Dm_Stamp " +
			          "       ,Tabelas_Fretes.OID_Origem " +
			          "       ,Tabelas_Fretes.OID_Destino " +
			          "       ,Tabelas_Fretes.NM_Origem " +
			          "       ,Tabelas_Fretes.NM_Destino " +
			          "       ,Tabelas_Fretes.DM_Origem " +
			          "       ,Tabelas_Fretes.DM_Destino " +
			          "       ,Tabelas_Fretes.DT_Vigencia " +
			          "       ,Tabelas_Fretes.DT_Validade " +
			          "       ,Tabelas_Fretes.DM_Tipo_Pedagio " +
			          "       ,Tabelas_Fretes.VL_Pedagio " +
			          "       ,Tabelas_Fretes.VL_Frete_Minimo " +
			          "       ,Tabelas_Fretes.OID_Empresa " +
			          "       ,Tabelas_Fretes.OID_Pessoa_Redespacho " +
			          "       ,Tabelas_Fretes.NR_Prazo_Entrega " +
			          "       ,Tabelas_Fretes.NR_Prazo_Faturamento " +
			          "       ,Tabelas_Fretes.NR_Peso_Minimo " +
			          "       ,Tabelas_Fretes.DM_Tipo_Tabela " +
			          "       ,Tabelas_Fretes.DM_Tipo_Peso " +
			          "       ,Tabelas_Fretes.VL_Ademe_Minimo " +
			          "       ,Tabelas_Fretes.VL_Ad_Valorem_Minimo " +
			          "       ,Tabelas_Fretes.VL_Maximo_Nota_Fiscal " +
			          "       ,Tabelas_Fretes.VL_Minimo_Nota_Fiscal " +
			          "       ,Tabelas_Fretes.VL_Despacho " +
			          "       ,Tabelas_Fretes.PE_Devolucao " +
			          "       ,Tabelas_Fretes.PE_Refaturamento " +
			          "       ,Tabelas_Fretes.PE_Reentrega " +
			          "       ,Tabelas_Fretes.PE_Ademe " +
			          "       ,Tabelas_Fretes.VL_TX_Coleta " +
			          "       ,Tabelas_Fretes.VL_TX_Entrega " +
			          "       ,Tabelas_Fretes.VL_TX_Exc_Coleta " +
			          "       ,Tabelas_Fretes.VL_TX_Exc_Entrega " +
			          "       ,Tabelas_Fretes.PE_C_Ad_Valorem " +
			          "       ,Tabelas_Fretes.VL_C_Taxa_Minima " +
			          "       ,Tabelas_Fretes.PE_D_Ad_Valorem " +
			          "       ,Tabelas_Fretes.PE_Reembolso " +
			          "       ,Tabelas_Fretes.PE_Reembolso_Minimo " +
			          "       ,Tabelas_Fretes.VL_Reembolso " +
			          "       ,Tabelas_Fretes.PE_AD_Valorem2 " +
			          "       ,Tabelas_Fretes.VL_Suframa_Minimo " +
			          "       ,Tabelas_Fretes.PE_Suframa " +
			          "       ,Tabelas_Fretes.PE_Fluvial " +
			          "       ,Tabelas_Fretes.VL_Fluvial_Minimo " +
			          "       ,Tabelas_Fretes.VL_E_Excedente " +
			          "       ,Tabelas_Fretes.VL_E_Ad_Valorem " +
			          "       ,Tabelas_Fretes.PE_E_Ad_Valorem " +
			          "       ,Tabelas_Fretes.PE_Frete_Entrega " +
			          "       ,Tabelas_Fretes.vl_tx_redespacho  " +
			          "       ,Tabelas_Fretes.vl_excesso_tx_redespacho  " +
			          "       ,Tabelas_Fretes.dm_aplicacao " +
			          "       ,Pessoas.nm_razao_social" +
			          "       ,Produtos.cd_produto " +
			          "       ,Produtos.dm_unidade " +
			          "       ,Produtos.nm_produto " +
			          "       ,Modal.cd_modal " +
			          "       ,Modal.nm_modal " +
			          " FROM " +
			          "		Tabelas_Fretes, Pessoas, Modal, Produtos " +
			          " WHERE " +
			          "		Tabelas_Fretes.oid_produto = Produtos.oid_produto " +
			          " AND " +
			          "		Tabelas_Fretes.oid_modal = Modal.oid_modal " +
			          " AND " +
			          "		Tabelas_Fretes.oid_pessoa = Pessoas.oid_pessoa ";
	      if (JavaUtil.doValida(ed.getOID_Pessoa())) {
	           sql += " AND Tabelas_Fretes.oid_pessoa = '" + ed.getOID_Pessoa() + "'";
	        }
			ResultSet res = this.executasql.executarConsulta(sql);
			while (res.next()) {
				  Tabela_FreteED popula = new Tabela_FreteED();
				  popula.setOID_Pessoa(res.getString("oid_pessoa"));
				  popula.setNM_Razao_Social(res.getString("nm_razao_social"));
				  popula.setNM_Produto(res.getString("nm_produto"));
				  popula.setNM_Modal(res.getString("nm_modal"));
				  popula.setNM_Origem(res.getString("nm_origem"));
				  popula.setNM_Destino(res.getString("nm_destino"));
				  popula.setNR_Peso_1(res.getDouble("nr_peso_inicial"));
				  popula.setNR_Peso_2(res.getDouble("nr_peso_final"));
				  popula.setVL_Frete(res.getDouble("vl_frete"));
				  popula.setVL_Frete_Minimo(res.getDouble("vl_frete_minimo"));
				  popula.setPE_Ad_Valorem(res.getDouble("pe_ad_valorem"));
				  popula.setVL_Pedagio(res.getDouble("vl_pedagio"));
				  popula.setDM_Tipo_Pedagio(res.getString("dm_tipo_pedagio"));
				  popula.setVL_Taxas(res.getDouble("vl_taxas"));
				  popula.setDM_Tipo_Peso(res.getString("dm_tipo_peso"));
				  popula.setVL_Outros2(res.getDouble("vl_outros2"));
				  popula.setVL_Ad_Valorem_Minimo(res.getDouble("vl_ad_valorem_minimo"));
				  popula.setPE_Ademe(res.getDouble("pe_ademe"));
				  popula.setVL_Ademe_Minimo(res.getDouble("vl_ademe_minimo"));
				  popula.setVL_Despacho(res.getDouble("vl_despacho"));
				  popula.setVL_Outros1(res.getDouble("vl_outros1"));
				  popula.setVL_Reembolso(res.getDouble("vl_reembolso"));
				  popula.setPE_Reentrega(res.getDouble("pe_reentrega"));
				  popula.setPE_Devolucao(res.getDouble("pe_devolucao"));
				  list.add(popula); 
			}
			return list;
			
		} catch (Exception exc) {
			throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),"lista()");
		}
	}
  

	/** ------------ RELAT�RIOS ---------------- */
	  //*** Relat�rio de Manuten��es Preventivas por ve�culo
	  public void relTabela_Frete(Tabela_FreteED ed)
	  throws Excecoes {
	      ed.setLista(this.lista(ed));
	      //*** Chama o Gerador de Relat�rios Jasper
	      
	      new JasperRL(ed).geraRelatorio();
	  }
  
	
  public void geraRelCotacaoEmitida (Tabela_FreteED ed , HttpServletResponse response) throws Excecoes {

    String sql = null;
    ArrayList lista = new ArrayList ();
    long valOid = 0;
    ResultSet res = null;
    boolean inclui = true;
    String ci = "";

    try {

      sql = " select tf.* " +
          " from tabelas_fretes tf " +
          " where tf.DM_aplicacao = 'CI' ";

      if (JavaUtil.doValida (ed.getDM_CRT ()) && ed.getDM_CRT ().equals ("S")) {
        sql = " select tf.*, ci.nr_conhecimento, ci.cd_pais, ci.nr_permisso " +
            " from tabelas_fretes tf, conhecimentos_internacionais ci " +
            " where ci.oid_tabela_frete = tf.oid_tabela_frete and tf.DM_aplicacao = 'CI' ";
      }

      if (String.valueOf (ed.getOid_Unidade ()) != null && !String.valueOf (ed.getOid_Unidade ()).equals ("0")) {
        sql += " and tf.OID_Unidade = " + ed.getOid_Unidade ();
      }
      if (String.valueOf (ed.getOID_Pessoa ()) != null && !String.valueOf (ed.getOID_Pessoa ()).equals ("")) {
        sql += " and tf.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
      }

      if (String.valueOf (ed.getDT_Emissao_Inicial ()) != null &&
          !String.valueOf (ed.getDT_Emissao_Inicial ()).equals ("") &&
          !String.valueOf (ed.getDT_Emissao_Inicial ()).equals ("null")) {
        sql += " and tf.DT_cotacao >= '" + ed.getDT_Emissao_Inicial () + "'";
      }

      if (String.valueOf (ed.getDT_Emissao_Final ()) != null &&
          !String.valueOf (ed.getDT_Emissao_Final ()).equals ("") &&
          !String.valueOf (ed.getDT_Emissao_Final ()).equals ("null")) {
        sql += " and tf.DT_cotacao <= '" + ed.getDT_Emissao_Final () + "'";
      }
      if (JavaUtil.doValida (ed.getDM_CRT ()) && ed.getDM_CRT ().equals ("S")) {
        sql += "order by ci.nr_conhecimento, tf.nr_cotacao ";
      }
      else {
        sql += "order by tf.nr_cotacao, tf.DT_cotacao ";
      }
      res = this.executasql.executarConsulta (sql.toString ());

      FormataDataBean DataFormatada = new FormataDataBean ();

      while (res.next ()) {
        inclui = true;
        Tabela_FreteED edVolta = new Tabela_FreteED ();

        edVolta.setNr_Cotacao (res.getLong ("nr_cotacao"));
        edVolta.setOid (res.getLong ("oid_tabela_frete"));

        edVolta.setDT_Vigencia (res.getString ("DT_Cotacao"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Vigencia ());
        edVolta.setDT_Vigencia (DataFormatada.getDT_FormataData ());

        PessoaBean pE = PessoaBean.getByOID (res.getString ("oid_pessoa"));
        edVolta.setNM_Razao_Social (pE.getNM_Razao_Social ());

        edVolta.setNM_Origem (res.getString ("nm_Origem_Editado").trim () + " - " + res.getString ("nm_Destino_Editado").trim ());

        edVolta.setVL_Frete (res.getDouble ("VL_Frete"));

        edVolta.setVL_Taxas (res.getDouble ("VL_Taxas"));
        edVolta.setVL_Outros1 (res.getDouble ("VL_Outros1"));
        edVolta.setPE_Ad_Valorem (res.getDouble ("pe_Ad_Valorem"));

        edVolta.setVL_Ad_Valorem_Minimo ( (edVolta.getVL_Frete () * edVolta.getPE_Ad_Valorem ()) / 100);
        edVolta.setVL_Frete_Minimo (edVolta.getVL_Frete () +
                                    edVolta.getVL_Taxas () +
                                    edVolta.getVL_Outros1 () +
                                    edVolta.getVL_Ad_Valorem_Minimo ());

        if (JavaUtil.doValida (ed.getDM_CRT ()) && ed.getDM_CRT ().equals ("S")) {
          String nr_Conhecimento = res.getString ("NR_CONHECIMENTO");
          String nr_CRT_Parcial = res.getString ("CD_Pais") + "." +
              res.getString ("NR_Permisso") + ".";
          int completa_Nr_CRT = 13 - nr_CRT_Parcial.length () - nr_Conhecimento.length ();
          for (int a = 0; a < completa_Nr_CRT; a++) {
            nr_CRT_Parcial = nr_CRT_Parcial + "0";
          }
          edVolta.setNM_Conhecimento (nr_CRT_Parcial + nr_Conhecimento);

        }

        String origem = "";
        String destino = "";

        edVolta.setDM_Origem (res.getString ("dm_Origem"));
        edVolta.setDM_Destino (res.getString ("dm_Destino"));
        if (edVolta.getDM_Origem ().equals ("C")) {
          CidadeBean cO = CidadeBean.getByOID (res.getInt ("oid_origem"));
          origem = String.valueOf (cO.getOID_Pais ());
        }
        if (edVolta.getDM_Origem ().equals ("R")) {
          Regiao_EstadoBean rO = Regiao_EstadoBean.getByOID (res.getInt ("oid_origem"));
          origem = String.valueOf (rO.getOID_Pais ());
        }
        if (edVolta.getDM_Origem ().equals ("E")) {
          EstadoBean eO = EstadoBean.getByOID (res.getInt ("oid_origem"));
          origem = String.valueOf (eO.getOID_Pais ());
        }
        if (edVolta.getDM_Origem ().equals ("P")) {
          PaisBean pO = PaisBean.getByOID (res.getInt ("oid_origem"));
          origem = String.valueOf (pO.getOID ());
        }

        if (edVolta.getDM_Destino ().equals ("C")) {
          CidadeBean cD = CidadeBean.getByOID (res.getInt ("oid_destino"));
          destino = String.valueOf (cD.getOID_Pais ());
        }
        if (edVolta.getDM_Destino ().equals ("R")) {
          Regiao_EstadoBean rD = Regiao_EstadoBean.getByOID (res.getInt ("oid_destino"));
          destino = String.valueOf (rD.getOID_Pais ());
        }
        if (edVolta.getDM_Destino ().equals ("E")) {
          EstadoBean eD = EstadoBean.getByOID (res.getInt ("oid_destino"));
          destino = String.valueOf (eD.getOID_Pais ());
        }
        if (edVolta.getDM_Destino ().equals ("P")) {
          PaisBean pD = PaisBean.getByOID (res.getInt ("oid_destino"));
          destino = String.valueOf (pD.getOID ());
        }
        if (JavaUtil.doValida (String.valueOf (ed.getOid_Origem ())) || JavaUtil.doValida (String.valueOf (ed.getOid_Destino ()))) {

          if (JavaUtil.doValida (String.valueOf (ed.getOid_Origem ())) && !String.valueOf (ed.getOid_Origem ()).equals (origem)) {
            inclui = false;
          }
          if (JavaUtil.doValida (String.valueOf (ed.getOid_Destino ())) && !String.valueOf (ed.getOid_Destino ()).equals (destino)) {
            inclui = false;
          }
        }

        if (inclui) {
          lista.add (edVolta);
        }
        ci = res.getString ("nr_cotacao");

      }
      util.closeResultset (res);
      Tabela_FreteRL tabRL = new Tabela_FreteRL ();
      tabRL.geraRelCotacaoEmitida (ed , lista , response);

    }
    catch (Exception exc) {
      util.closeResultset (res);
      exc.printStackTrace ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao listar");
      excecoes.setMetodo ("listar");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

}
