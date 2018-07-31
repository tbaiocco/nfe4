package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.master.ed.Ordem_ManifestoED;
import com.master.rl.Ordem_ManifestoRL;
import com.master.root.FormataDataBean;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

public class Ordem_ManifestoBD {

  private ExecutaSQL executasql;

  public Ordem_ManifestoBD (ExecutaSQL sql) {
    this.executasql = sql;
  }

  public void inclui (Ordem_ManifestoED ed) throws Excecoes {
    String sql;
    String chave;
    try {
      ResultSet res;
      // System.out.println (">>>DM_Frete = " + ed.getDM_Frete ());
      /*
                           if (!"A".equals(ed.getDM_Frete())) {
       sql = " SELECT Ordens_Fretes.DM_Impresso from Ordens_Fretes " +
       " WHERE Ordens_Fretes.OID_Ordem_Frete = '" + ed.getOID_Ordem_Frete() + "'";
       res = this.executasql.executarConsulta(sql);
       if (res.next()){
        if ("S".equals(res.getString("DM_Impresso"))){
         throw new Excecoes("Ordem de frete já impressa ...");
        }
       }
          }
       */
      sql = " SELECT Viagens.OID_Manifesto from Viagens, Conhecimentos, Manifestos " +
          " WHERE Viagens.OID_Conhecimento = Conhecimentos.OID_Conhecimento " +
          " AND Viagens.OID_Manifesto = '" + ed.getOID_Manifesto () + "'";
      res = this.executasql.executarConsulta (sql);
      if (!res.next ()) {
        throw new Excecoes ("Manifesto sem conhecimentos vinculados...");
      }

      chave = (ed.getOID_Manifesto () + ed.getOID_Ordem_Frete ());

      sql = " insert into Ordens_Manifestos (OID_Ordem_Manifesto, OID_Manifesto, OID_Ordem_Frete, DT_Ordem_Manifesto, HR_Ordem_Manifesto ) values ";
      sql += "('" + chave + "','" + ed.getOID_Manifesto () + "','" + ed.getOID_Ordem_Frete () + "','" + ed.getDT_Ordem_Manifesto () + "','" + ed.getHR_Ordem_Manifesto () + "')";

      int i = executasql.executarUpdate (sql);

      sql = " update Manifestos set DM_Lancado_Ordem_Frete= 'S'" +
          " where oid_Manifesto = '" + ed.getOID_Manifesto () + "'";

      i = executasql.executarUpdate (sql);

    }
    catch (Excecoes e) {
      throw e;
    }
    catch (SQLException e) {
      throw new Excecoes (e.getLocalizedMessage () , e , this.getClass ().getName () , "inclui(Ordem_ManifestoED ed)");
    }
  }

  public void altera (Ordem_ManifestoED ed) throws Excecoes {

    String sql = null;

    try {

      sql = " update Ordens_Manifestos set OID_Ordem_Frete= '" + ed.getOID_Ordem_Frete () + "'";
      sql += " where oid_Ordem_Manifesto = '" + ed.getOID_Ordem_Manifesto () + "'";

      int i = executasql.executarUpdate (sql);
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar");
      excecoes.setMetodo ("alterar");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void deleta (Ordem_ManifestoED ed) throws Excecoes {
    String sql;
    ResultSet res;
    try {
      if (!"A".equals (ed.getDM_Frete ())) {
        sql = " SELECT Ordens_Fretes.DM_Impresso from Ordens_Fretes " +
            " WHERE Ordens_Fretes.OID_Ordem_Frete = '" + ed.getOID_Ordem_Frete () + "'";
        res = this.executasql.executarConsulta (sql);
        if (res.next ()) {
          if ("S".equals (res.getString ("DM_Impresso"))) {
            throw new Excecoes ("Ordem de frete já impressa ...");
          }
        }
      }

      sql = "delete from Ordens_Manifestos WHERE oid_Ordem_Manifesto = ";
      sql += "('" + ed.getOID_Ordem_Manifesto () + "')";

      int i = executasql.executarUpdate (sql);

      sql = " update Manifestos set DM_Lancado_Ordem_Frete= 'N'" +
          " where oid_Manifesto = '" + ed.getOID_Manifesto () + "'";

      i = executasql.executarUpdate (sql);
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "deleta(Ordem_ManifestoED ed)");
    }
  }

  public ArrayList lista (Ordem_ManifestoED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();

    try {
      sql = " SELECT OID_Ordem_Manifesto, DT_Ordem_Manifesto, HR_Ordem_Manifesto, Manifestos.OID_Manifesto, Manifestos.NR_Manifesto, Manifestos.DT_Emissao, Ordens_Fretes.NR_Ordem_Frete, Ordens_Fretes.NR_Master, Ordens_Fretes.OID_Ordem_Frete, Unidades.CD_Unidade from Ordens_Manifestos, Manifestos, Unidades, Ordens_Fretes ";
      sql += " WHERE Unidades.oid_Unidade = Manifestos.oid_Unidade ";
      sql += " AND Ordens_Manifestos.OID_Manifesto = Manifestos.OID_Manifesto ";
      sql += " AND Ordens_Manifestos.OID_Ordem_Frete = Ordens_Fretes.OID_Ordem_Frete ";

      if (String.valueOf (ed.getOID_Manifesto ()) != null &&
          !String.valueOf (ed.getOID_Manifesto ()).equals ("") &&
          !String.valueOf (ed.getOID_Manifesto ()).equals ("null")) {
        sql += " and Manifestos.OID_Manifesto = '" + ed.getOID_Manifesto () + "'";
      }
      else {
        if (String.valueOf (ed.getNR_Manifesto ()) != null &&
            !String.valueOf (ed.getNR_Manifesto ()).equals ("0") &&
            !String.valueOf (ed.getNR_Manifesto ()).equals ("null")) {
          sql += " and Manifestos.NR_Manifesto = " + ed.getNR_Manifesto ();
        }
      }

      if (String.valueOf (ed.getOID_Ordem_Frete ()) != null &&
          !String.valueOf (ed.getOID_Ordem_Frete ()).equals ("") &&
          !String.valueOf (ed.getOID_Ordem_Frete ()).equals ("null")) {
        sql += " and Ordens_Fretes.OID_Ordem_Frete = '" + ed.getOID_Ordem_Frete () + "'";
      }

      if (String.valueOf (ed.getOID_Unidade ()) != null &&
          !String.valueOf (ed.getOID_Unidade ()).equals ("0") &&
          !String.valueOf (ed.getOID_Unidade ()).equals ("null")) {
        sql += " and Manifestos.OID_Unidade = " + ed.getOID_Unidade ();
      }
      if (String.valueOf (ed.getDT_Emissao ()) != null &&
          !String.valueOf (ed.getDT_Emissao ()).equals ("") &&
          !String.valueOf (ed.getDT_Emissao ()).equals ("null")) {
        sql += " and Manifestos.DT_Emissao = '" + ed.getDT_Emissao () + "'";
      }
      if (String.valueOf (ed.getDT_Ordem_Manifesto ()) != null &&
          !String.valueOf (ed.getDT_Ordem_Manifesto ()).equals ("") &&
          !String.valueOf (ed.getDT_Ordem_Manifesto ()).equals ("null")) {
        sql += " and Ordens_Manifestos.DT_Ordem_Manifesto = '" + ed.getDT_Ordem_Manifesto () + "'";
      }
      if (!"".equals (ed.getDM_Frete ())) {
        sql += " and Ordens_Fretes.DM_Frete = '" + ed.getDM_Frete () + "'";
      }
      else {
        sql += " and (Ordens_Fretes.DM_Frete <> 'A' ";
        sql += "   or Ordens_Fretes.DM_Frete is null) ";
      }

      sql += " Order by Manifestos.NR_Manifesto ";

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);

      FormataDataBean DataFormatada = new FormataDataBean ();

      //popula
      while (res.next ()) {
        Ordem_ManifestoED edVolta = new Ordem_ManifestoED ();

        edVolta.setOID_Ordem_Manifesto (res.getString ("OID_Ordem_Manifesto"));
        edVolta.setOID_Ordem_Frete (res.getString ("OID_Ordem_Frete"));

        edVolta.setDT_Ordem_Manifesto (res.getString ("DT_Ordem_Manifesto"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Ordem_Manifesto ());
        edVolta.setDT_Ordem_Manifesto (DataFormatada.getDT_FormataData ());

        edVolta.setHR_Ordem_Manifesto (res.getString ("HR_Ordem_Manifesto"));
        edVolta.setNR_Manifesto (res.getLong ("NR_Manifesto"));

        edVolta.setNr_Ordem_Frete (res.getString ("NR_Ordem_Frete"));
        edVolta.setNR_Master ("");

        if (res.getString ("NR_Master") != null &&
            !res.getString ("NR_Master").equals ("") &&
            !res.getString ("NR_Master").equals (null) &&
            !res.getString ("NR_Master").equals ("null")) {
          edVolta.setNR_Master (res.getString ("NR_Master"));
          edVolta.setNr_Ordem_Frete ("");
        }

        edVolta.setOID_Manifesto (res.getString ("OID_Manifesto"));

        edVolta.setDT_Emissao (res.getString ("DT_Emissao"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
        edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());

        edVolta.setCD_Unidade (res.getString ("CD_Unidade"));

        list.add (edVolta);
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao listar");
      excecoes.setMetodo ("listar");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return list;
  }

  public Ordem_ManifestoED getByRecord (Ordem_ManifestoED ed) throws Excecoes {

    String sql = null;

    Ordem_ManifestoED edVolta = new Ordem_ManifestoED ();

    try {

      sql = " SELECT OID_Ordem_Manifesto, Ordens_Manifestos.OID_Ordem_Frete, DT_Ordem_Manifesto, HR_Ordem_Manifesto, Manifestos.oid_Manifesto, Manifestos.NR_Manifesto, Manifestos.DT_Emissao, Ordens_Fretes.NR_Ordem_Frete, Manifestos.oid_Unidade, Manifestos.oid_Cidade, Ordens_Fretes.oid_Veiculo, Ordens_Fretes.DM_Frete from Ordens_Manifestos, Manifestos, Ordens_Fretes ";
      sql += " WHERE Ordens_Manifestos.OID_Manifesto = Manifestos.OID_Manifesto ";
      sql += " AND Ordens_Manifestos.OID_Ordem_Frete = Ordens_Fretes.OID_Ordem_Frete ";

      if (String.valueOf (ed.getOID_Ordem_Manifesto ()) != null &&
          !String.valueOf (ed.getOID_Ordem_Manifesto ()).equals ("")) {
        sql += " and OID_Ordem_Manifesto = '" + ed.getOID_Ordem_Manifesto () + "'";
      }

      FormataDataBean DataFormatada = new FormataDataBean ();

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta.setOID_Ordem_Manifesto (res.getString ("OID_Ordem_Manifesto"));
        edVolta.setOID_Ordem_Frete (res.getString ("OID_Ordem_Frete"));
        edVolta.setOID_Manifesto (res.getString ("OID_Manifesto"));
        edVolta.setNR_Manifesto (res.getLong ("NR_Manifesto"));
        edVolta.setNR_Ordem_Frete (res.getLong ("NR_Ordem_Frete"));

        edVolta.setOID_Veiculo (res.getString ("OID_Veiculo"));

        edVolta.setDT_Ordem_Manifesto (res.getString ("DT_Ordem_Manifesto"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Ordem_Manifesto ());
        edVolta.setDT_Ordem_Manifesto (DataFormatada.getDT_FormataData ());

        edVolta.setHR_Ordem_Manifesto (res.getString ("HR_Ordem_Manifesto"));

        edVolta.setDT_Emissao (res.getString ("DT_Emissao"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
        edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());

        edVolta.setOID_Cidade_Destino (res.getLong ("oid_Cidade"));
        edVolta.setOID_Unidade (res.getLong ("oid_Unidade"));
        edVolta.setDM_Frete (res.getString ("DM_Frete"));
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao selecionar");
      excecoes.setMetodo ("selecionar");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return edVolta;
  }

  public void geraRelatorio (Ordem_ManifestoED ed) throws Excecoes {

    String sql = null;

    Ordem_ManifestoED edVolta = new Ordem_ManifestoED ();

    try {

      sql = "select * from Ordem_Manifestos where 1=1 ";

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);

      Ordem_ManifestoRL Ordem_Manifesto_rl = new Ordem_ManifestoRL ();
      Ordem_Manifesto_rl.geraRelatEstoque (res);
    }
    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro no método listar");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("geraRelatorio(Ordem_ManifestoED ed)");
    }
  }

  public void inclui_MIC (Ordem_ManifestoED ed) throws Excecoes {
    String sql;
    String chave;
    try {
      ResultSet res;

      // System.out.println (">>>DM_Frete = " + ed.getDM_Frete ());
      if (!"A".equals (ed.getDM_Frete ())) {
        sql = " SELECT Ordens_Fretes.DM_Impresso from Ordens_Fretes " +
            " WHERE Ordens_Fretes.OID_Ordem_Frete = '" + ed.getOID_Ordem_Frete () + "'";
        res = this.executasql.executarConsulta (sql);
        if (res.next ()) {
          if (!"S".equals (res.getString ("DM_Impresso"))) {
            throw new Excecoes ("Ordem de frete não impressa ...");
          }
        }
      }

      chave = (ed.getOID_Manifesto () + ed.getOID_Ordem_Frete ());

      sql = " insert into Ordens_MIC (OID_Ordem_MIC, OID_Manifesto_Internacional, OID_Ordem_Frete, DT_Ordem_MIC, HR_Ordem_MIC ) values ";
      sql += "('" + chave + "','" + ed.getOID_Manifesto () + "','" + ed.getOID_Ordem_Frete () + "','" + ed.getDT_Ordem_Manifesto () + "','" + ed.getHR_Ordem_Manifesto () + "')";

      int i = executasql.executarUpdate (sql);

//			sql = " update Manifestos set DM_Lancado_Ordem_Frete= 'S'" +
//			" where oid_Manifesto = '" + ed.getOID_Manifesto() + "'";
//			
//			i = executasql.executarUpdate(sql);

    }
    catch (Excecoes e) {
      throw e;
    }
    catch (SQLException e) {
      throw new Excecoes (e.getLocalizedMessage () , e , this.getClass ().getName () , "inclui_MIC(Ordem_ManifestoED ed)");
    }
  }

  public void altera_MIC (Ordem_ManifestoED ed) throws Excecoes {

    String sql = null;

    try {

      sql = " update Ordens_MIC set OID_Ordem_Frete= '" + ed.getOID_Ordem_Frete () + "'";
      sql += " where oid_Ordem_MIC = '" + ed.getOID_Ordem_Manifesto () + "'";

      int i = executasql.executarUpdate (sql);
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar");
      excecoes.setMetodo ("alterar");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void deleta_MIC (Ordem_ManifestoED ed) throws Excecoes {
    String sql;
    ResultSet res;
    try {
      if (!"A".equals (ed.getDM_Frete ())) {
        sql = " SELECT Ordens_Fretes.DM_Impresso from Ordens_Fretes " +
            " WHERE Ordens_Fretes.OID_Ordem_Frete = '" + ed.getOID_Ordem_Frete () + "'";
        res = this.executasql.executarConsulta (sql);
        if (res.next ()) {
          if ("S".equals (res.getString ("DM_Impresso"))) {
            throw new Excecoes ("Ordem de frete já impressa ...");
          }
        }
      }

      sql = "delete from Ordens_MIC WHERE oid_Ordem_MIC = ";
      sql += "('" + ed.getOID_Ordem_Manifesto () + "')";

      int i = executasql.executarUpdate (sql);

//			sql = " update Manifestos set DM_Lancado_Ordem_Frete= 'N'" +
//			" where oid_Manifesto = '" + ed.getOID_Manifesto() + "'";
//			
//			i = executasql.executarUpdate(sql);
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "deleta(Ordem_ManifestoED ed)");
    }
  }

  public ArrayList lista_MIC (Ordem_ManifestoED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();

    try {
      sql = " SELECT Ordens_MIC.OID_Ordem_MIC, Ordens_MIC.DT_Ordem_MIC, Ordens_MIC.HR_Ordem_MIC, " +
          " Manifestos_Internacionais.OID_Manifesto_Internacional, " +
          " Manifestos_Internacionais.NR_Manifesto_Internacional, " +
          " Manifestos_Internacionais.DT_Emissao, " +
          " Ordens_Fretes.NR_Ordem_Frete, Ordens_Fretes.NR_Master, " +
          " Ordens_Fretes.OID_Ordem_Frete, Unidades.CD_Unidade, Ordens_Fretes.DM_Frete " +
          " from Ordens_MIC, Manifestos_Internacionais, Unidades, Ordens_Fretes ";
      sql += " WHERE Unidades.oid_Unidade = Manifestos_Internacionais.oid_Unidade ";
      sql += " AND Ordens_MIC.OID_Manifesto_Internacional = Manifestos_Internacionais.OID_Manifesto_Internacional ";
      sql += " AND Ordens_MIC.OID_Ordem_Frete = Ordens_Fretes.OID_Ordem_Frete ";

      if (String.valueOf (ed.getNR_Manifesto ()) != null &&
          !String.valueOf (ed.getNR_Manifesto ()).equals ("0") &&
          !String.valueOf (ed.getNR_Manifesto ()).equals ("null")) {
        sql += " and Manifestos_Internacionais.NR_Manifesto_Internacional = " + ed.getNR_Manifesto ();
      }
      if (String.valueOf (ed.getOID_Manifesto ()) != null &&
          !String.valueOf (ed.getOID_Manifesto ()).equals ("0") &&
          !String.valueOf (ed.getOID_Manifesto ()).equals ("null")) {
        sql += " and Manifestos_Internacionais.oid_Manifesto_Internacional = '" + ed.getOID_Manifesto () + "'";
      }
      if (String.valueOf (ed.getOID_Ordem_Frete ()) != null &&
          !String.valueOf (ed.getOID_Ordem_Frete ()).equals ("") &&
          !String.valueOf (ed.getOID_Ordem_Frete ()).equals ("null")) {
        sql += " and Ordens_Fretes.OID_Ordem_Frete = '" + ed.getOID_Ordem_Frete () + "'";
      }

      if (String.valueOf (ed.getOID_Unidade ()) != null &&
          !String.valueOf (ed.getOID_Unidade ()).equals ("0") &&
          !String.valueOf (ed.getOID_Unidade ()).equals ("null")) {
        sql += " and Manifestos_Internacionais.OID_Unidade = " + ed.getOID_Unidade ();
      }
      if (String.valueOf (ed.getDT_Emissao ()) != null &&
          !String.valueOf (ed.getDT_Emissao ()).equals ("") &&
          !String.valueOf (ed.getDT_Emissao ()).equals ("null")) {
        sql += " and Manifestos_Internacionais.DT_Emissao = '" + ed.getDT_Emissao () + "'";
      }
      if (String.valueOf (ed.getDT_Ordem_Manifesto ()) != null &&
          !String.valueOf (ed.getDT_Ordem_Manifesto ()).equals ("") &&
          !String.valueOf (ed.getDT_Ordem_Manifesto ()).equals ("null")) {
        sql += " and Ordens_Manifestos.DT_Ordem_Manifesto = '" + ed.getDT_Ordem_Manifesto () + "'";
      }
      if (!"".equals (ed.getDM_Frete ())) {
        sql += " and Ordens_Fretes.DM_Frete = '" + ed.getDM_Frete () + "'";
      }
      else {
//				sql += " and (Ordens_Fretes.DM_Frete <> 'A' ";
//				sql += "   or Ordens_Fretes.DM_Frete is null) ";
      }

      sql += " Order by Manifestos_Internacionais.NR_Manifesto_Internacional ";

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);

      FormataDataBean DataFormatada = new FormataDataBean ();

      //popula
      while (res.next ()) {
        Ordem_ManifestoED edVolta = new Ordem_ManifestoED ();

        edVolta.setOID_Ordem_Manifesto (res.getString ("OID_Ordem_MIC"));
        edVolta.setOID_Ordem_Frete (res.getString ("OID_Ordem_Frete"));

        edVolta.setDT_Ordem_Manifesto (res.getString ("DT_Ordem_MIC"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Ordem_Manifesto ());
        edVolta.setDT_Ordem_Manifesto (DataFormatada.getDT_FormataData ());

        edVolta.setHR_Ordem_Manifesto (res.getString ("HR_Ordem_MIC"));
        edVolta.setNR_Manifesto (res.getLong ("NR_Manifesto_Internacional"));

        edVolta.setNr_Ordem_Frete (res.getString ("NR_Ordem_Frete"));
        edVolta.setNR_Master ("");

        if (res.getString ("NR_Master") != null &&
            !res.getString ("NR_Master").equals ("") &&
            !res.getString ("NR_Master").equals (null) &&
            !res.getString ("NR_Master").equals ("null")) {
          edVolta.setNR_Master (res.getString ("NR_Master"));
          edVolta.setNr_Ordem_Frete ("");
        }

        edVolta.setOID_Manifesto (res.getString ("OID_Manifesto_Internacional"));

        edVolta.setDT_Emissao (res.getString ("DT_Emissao"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
        edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());

        edVolta.setCD_Unidade (res.getString ("CD_Unidade"));

        edVolta.setDM_Frete (res.getString ("DM_Frete"));

        list.add (edVolta);
      }
    }
    catch (Exception exc) {
      exc.printStackTrace ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao listar");
      excecoes.setMetodo ("listar");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return list;
  }

  public Ordem_ManifestoED getByRecord_MIC (Ordem_ManifestoED ed) throws Excecoes {

    String sql = null;

    Ordem_ManifestoED edVolta = new Ordem_ManifestoED ();

    try {

      sql = " SELECT Ordens_MIC.OID_Ordem_MIC, Ordens_MIC.OID_Ordem_Frete, Ordens_MIC.DT_Ordem_MIC, Ordens_MIC.HR_Ordem_MIC, " +
          "Manifestos_Internacionais.oid_Manifesto_Internacional, " +
          "Manifestos_Internacionais.NR_Manifesto_Internacional, " +
          "Manifestos_Internacionais.DT_Emissao, " +
          "Ordens_Fretes.NR_Ordem_Frete, Manifestos_Internacionais.oid_Unidade, " +
          "Manifestos_Internacionais.oid_Cidade, " +
          "Ordens_Fretes.oid_Veiculo, Ordens_Fretes.DM_Frete " +
          "from Ordens_MIC, Manifestos_Internacionais, Ordens_Fretes ";
      sql += " WHERE Ordens_MIC.OID_Manifesto_Internacional = Manifestos_Internacionais.OID_Manifesto_Internacional ";
      sql += " AND Ordens_MIC.OID_Ordem_Frete = Ordens_Fretes.OID_Ordem_Frete ";

      if (String.valueOf (ed.getOID_Ordem_Manifesto ()) != null &&
          !String.valueOf (ed.getOID_Ordem_Manifesto ()).equals ("")) {
        sql += " and OID_Ordem_MIC = '" + ed.getOID_Ordem_Manifesto () + "'";
      }

      FormataDataBean DataFormatada = new FormataDataBean ();

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta.setOID_Ordem_Manifesto (res.getString ("OID_Ordem_MIC"));
        edVolta.setOID_Ordem_Frete (res.getString ("OID_Ordem_Frete"));
        edVolta.setOID_Manifesto (res.getString ("OID_Manifesto_Internacional"));
        edVolta.setNR_Manifesto (res.getLong ("NR_Manifesto_Internacional"));
        edVolta.setNR_Ordem_Frete (res.getLong ("NR_Ordem_Frete"));

        edVolta.setOID_Veiculo (res.getString ("OID_Veiculo"));

        edVolta.setDT_Ordem_Manifesto (res.getString ("DT_Ordem_MIC"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Ordem_Manifesto ());
        edVolta.setDT_Ordem_Manifesto (DataFormatada.getDT_FormataData ());

        edVolta.setHR_Ordem_Manifesto (res.getString ("HR_Ordem_MIC"));

        edVolta.setDT_Emissao (res.getString ("DT_Emissao"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
        edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());

        edVolta.setOID_Cidade_Destino (res.getLong ("oid_Cidade"));
        edVolta.setOID_Unidade (res.getLong ("oid_Unidade"));
        edVolta.setDM_Frete (res.getString ("DM_Frete"));
      }
    }
    catch (Exception exc) {
      exc.printStackTrace ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao selecionar");
      excecoes.setMetodo ("selecionar");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return edVolta;
  }

}