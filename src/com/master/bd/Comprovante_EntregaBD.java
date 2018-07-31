package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.master.ed.Comprovante_EntregaED;
import com.master.rl.Comprovante_EntregaRL;
import com.master.root.FormataDataBean;
import com.master.util.Excecoes;
import com.master.util.Data;
import com.master.util.FormataData;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;
import com.master.util.Utilitaria;

public class Comprovante_EntregaBD {

  private ExecutaSQL executasql;
  Utilitaria util = new Utilitaria (executasql);

  Parametro_FixoED edParametro_Fixo = new Parametro_FixoED ();

  public Comprovante_EntregaBD (ExecutaSQL sql) {
    this.executasql = sql;
  }

  public Comprovante_EntregaED inclui (Comprovante_EntregaED ed) throws Excecoes {

    String sql = null;

    Comprovante_EntregaED comp_entED = new Comprovante_EntregaED ();
    try {

      // System.out.println ("man bd 1 ");
      ResultSet rs = executasql.executarConsulta("select max(NR_Comprovante_Entrega) as result from Comprovantes_Entregas");

      //pega proximo valor da chave
      while (rs.next()){
        ed.setNR_Comprovante_Entrega (rs.getLong("result")+1);
      }

      ed.setOID_Comprovante_Entrega ("9"+String.valueOf (System.currentTimeMillis ()).toString ().substring (4 , 12));

      sql = " insert into Comprovantes_Entregas (OID_Comprovante_Entrega, NR_Comprovante_Entrega, OID_Unidade,  DT_Emissao,  TX_Observacao) values ";
      sql += "('" + ed.getOID_Comprovante_Entrega () + "'," + ed.getNR_Comprovante_Entrega () + "," +  ed.getOID_Unidade() + ",'" + Data.getDataDMY() + "','" + ed.getTX_Observacao()+ "')";

      // System.out.println ("man bd inclui ->>>>>>>>>>>>>>>.. " + sql);

      int i = executasql.executarUpdate (sql);

      // System.out.println ("man bd inclui ->> OK ");

      comp_entED.setOID_Comprovante_Entrega (ed.getOID_Comprovante_Entrega ());

    }
    catch (Excecoes e) {
      throw e;
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "inclui(Comprovante_EntregaED ed)");
    }
    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "inclui(Comprovante_EntregaED ed)");
    }
    return comp_entED;
  }

  public void altera (Comprovante_EntregaED ed) throws Excecoes {

    String sql = null;

    try {

      sql = " update Comprovantes_Entregas SET DM_Situacao='F', DT_Arquivo ='"+ Data.getDataDMY() +"'";

      // System.out.println ("man bd 1");

      sql += " where oid_Comprovante_Entrega = '" + ed.getOID_Comprovante_Entrega () + "'";

      // System.out.println ("man bd >>>>>>>> UPDATE 999" + sql);

      int i = executasql.executarUpdate (sql);
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMetodo ("alterar");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void deleta (Comprovante_EntregaED ed) throws Excecoes {
    String sql = null;
    ResultSet res = null;
    try {

      sql = "delete from Comprovantes_Entregas WHERE oid_Comprovante_Entrega = ";
      sql += "('" + ed.getOID_Comprovante_Entrega () + "')";

      executasql.executarUpdate (sql);
      //// System.out.println("exclui Comprovante_Entrega ");


    }

    catch (Exception exc) {
      throw new Excecoes ("Erro ao excluir Comprovante_Entrega: " + exc.getMessage () ,
                          exc , this.getClass ().getName () , "deleta(Comprovante_EntregaED ed)");
    }
  }

  public ArrayList lista_Conhecimento_Entregue (Comprovante_EntregaED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();

    try {

      sql = " SELECT Conhecimentos.NR_Conhecimento,  " +
          "          Conhecimentos.DT_Entrega, " +
          "          Conhecimentos.oid_Conhecimento, " +
          "          Pessoa_Remetente.NM_Razao_Social as NM_Razao_Social_Remetente, " +
          "          Unidades.CD_Unidade  " +
          " FROM  Conhecimentos, Unidades, Pessoas Pessoa_Remetente " +
          " WHERE Conhecimentos.OID_Unidade = Unidades.OID_Unidade " +
          " AND   Conhecimentos.oid_Pessoa = Pessoa_Remetente.oid_Pessoa " +
          " AND   Conhecimentos.OID_Comprovante_Entrega = '" + ed.getOID_Comprovante_Entrega()+ "'";

      // System.out.println("->> " + sql);

      sql += " Order by Conhecimentos.NR_Conhecimento ";

      // System.out.println ("6 " + sql);

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);

      FormataDataBean DataFormatada = new FormataDataBean ();

      //popula
      while (res.next ()) {
        Comprovante_EntregaED edVolta = new Comprovante_EntregaED ();

// System.out.println("7 ");

        edVolta.setDT_Entrega (res.getString ("DT_Entrega"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Entrega ());
        edVolta.setDT_Entrega (DataFormatada.getDT_FormataData ());
        edVolta.setNM_Pessoa_Remetente(res.getString ("NM_Razao_Social_Remetente"));

        edVolta.setCD_Unidade (res.getString ("CD_Unidade"));

        edVolta.setOID_Conhecimento (res.getString ("OID_Conhecimento"));

        edVolta.setNR_Conhecimento (res.getLong ("NR_Conhecimento"));

// System.out.println("9 ");

        list.add (edVolta);
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao listar Comprovante_Entrega");
      excecoes.setMetodo ("lista");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return list;
  }

  public Comprovante_EntregaED getByRecord (Comprovante_EntregaED ed) throws Excecoes {
    Comprovante_EntregaED edVolta = new Comprovante_EntregaED ();
    try {
      String sql =
          " SELECT *, " +
          "        Unidades.CD_Unidade, " +
          "        Pessoas.NM_Fantasia " +
          " FROM Comprovantes_Entregas, Pessoas,  Unidades  " +
          " WHERE Comprovantes_Entregas.oid_unidade = Unidades.oid_unidade  " +
          " AND   Unidades.oid_Pessoa = Pessoas.oid_pessoa  ";
      if (util.doValida (ed.getOID_Comprovante_Entrega ())) {
        sql += " and Comprovantes_Entregas.OID_Comprovante_Entrega = '" + ed.getOID_Comprovante_Entrega () + "'";
      }
      if (ed.getNR_Comprovante_Entrega () > 0) {
        sql += " and Comprovantes_Entregas.NR_Comprovante_Entrega = " + ed.getNR_Comprovante_Entrega ();
      }
      if (ed.getOID_Unidade () > 0) {
        sql += " and Comprovantes_Entregas.OID_Unidade = " + ed.getOID_Unidade ();
      }
      // System.out.println(sql);

      ResultSet res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta.setOID_Comprovante_Entrega (res.getString ("OID_Comprovante_Entrega"));
        edVolta.setNR_Comprovante_Entrega(res.getLong("NR_Comprovante_Entrega"));
        edVolta.setDT_Chegada (FormataData.formataDataBT (res.getDate ("DT_Chegada")));
        edVolta.setDM_Situacao (res.getString ("DM_Situacao"));
        edVolta.setDT_Emissao (FormataData.formataDataBT (res.getString ("DT_Emissao")));
        edVolta.setDT_Arquivo (FormataData.formataDataBT (res.getString ("DT_Arquivo")));
        edVolta.setOID_Unidade (res.getLong ("OID_Unidade"));
        edVolta.setCD_Unidade (res.getString ("CD_Unidade"));
        edVolta.setNM_Cidade_Unidade (res.getString ("CD_Unidade") + " - " +res.getString ("NM_Fantasia"));

        sql =" SELECT COUNT (oid_Conhecimento) as cont FROM Conhecimentos WHERE oid_Comprovante_Entrega ='" + res.getString ("OID_Comprovante_Entrega") + "'";
        ResultSet res2 = this.executasql.executarConsulta (sql);
        if (res2.next ()) {
          edVolta.setNR_Conhecimento(res2.getLong("cont"));
        }

      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "getByRecord(Comprovante_EntregaED ed)");
    }
    return edVolta;
  }

  public byte[] imprime_Comprovante_Entrega (Comprovante_EntregaED ed) throws Excecoes {
    String sql =
       // " SELECT Comprovantes_Entregas.*, Conhecimentos.oid_Conhecimento " +
       // " FROM   Comprovantes_Entregas, Conhecimentos" +
       // " WHERE  Comprovantes_Entregas.OID_Comprovante_Entrega = Conhecimentos.OID_Comprovante_Entrega "+
       // " AND    Comprovantes_Entregas.oid_Comprovante_Entrega = '" + ed.getOID_Comprovante_Entrega () + "'" +
       // " Order by  Conhecimentos.NR_Conhecimento ";

        " SELECT Conhecimentos.oid_Conhecimento, Conhecimentos.oid_Comprovante_Entrega " +
        " FROM   Conhecimentos" +
        " WHERE  Conhecimentos.oid_Comprovante_Entrega = '" + ed.getOID_Comprovante_Entrega () + "'" +
        " Order by  Conhecimentos.NR_Conhecimento ";


    ResultSet res = executasql.executarConsulta (sql);
    try {
      return new Comprovante_EntregaRL ().imprime_Comprovante_Entrega (res);
    }
    finally {
      try {
        res.close ();
        res.getStatement ().close ();
      }
      catch (SQLException e) {
        e.printStackTrace ();
      }
    }
  }

  public Comprovante_EntregaED inclui_Exclui_Documento (Comprovante_EntregaED ed) throws Excecoes {

    String sql = null;

    Comprovante_EntregaED comp_entED = new Comprovante_EntregaED ();
    try {
      long oid_Comprovante_Entrega=0;

      // System.out.println ("inclui_Exclui_Documento 1 ");
      
      if ("I".equals (ed.getDM_Acao ())) {
        oid_Comprovante_Entrega=new Long (ed.getOID_Comprovante_Entrega ()).longValue();
      }

      // System.out.println ("inclui_Exclui_Documento 2 ");
      
      if ("CTO".equals(ed.getDM_Tipo_Documento())){
          sql = " UPDATE Conhecimentos set OID_Comprovante_Entrega = '" + oid_Comprovante_Entrega + "'" +
                " WHERE  Conhecimentos.oid_Conhecimento = '" + ed.getOID_Conhecimento() + "'";
      }

      // System.out.println ("->>>>>>>>>>>>>>>.. " + sql);

      executasql.executarUpdate (sql);

      // System.out.println ("->> OK ");

    }
    catch (Excecoes e) {
      throw e;
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "inclui_Exclui_Documento(Comprovante_EntregaED ed)");
    }
    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "inclui_Exclui_Documento(Comprovante_EntregaED ed)");
    }
    return comp_entED;
  }

  public ArrayList lista (Comprovante_EntregaED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();

    try {

      sql = " SELECT Comprovantes_Entregas.OID_Comprovante_Entrega,  " +
          "          Comprovantes_Entregas.DT_Emissao, " +
          "          Comprovantes_Entregas.NR_Comprovante_Entrega, " +
          "          Unidades.CD_Unidade  " +
          " FROM  Comprovantes_Entregas, Unidades " +
          " WHERE Comprovantes_Entregas.OID_Unidade = Unidades.OID_Unidade ";

      if (String.valueOf (ed.getDT_Emissao ()) != null && !String.valueOf (ed.getDT_Emissao ()).equals ("") && !String.valueOf (ed.getDT_Emissao ()).equals ("null")) {
        sql += " and Comprovantes_Entregas.DT_Emissao = '" + ed.getDT_Emissao () + "'";
      }

      if (String.valueOf (ed.getNR_Comprovante_Entrega ()) != null &&
          !String.valueOf (ed.getNR_Comprovante_Entrega ()).equals ("0") &&
          !String.valueOf (ed.getNR_Comprovante_Entrega ()).equals ("null")) {
        sql += " and Comprovantes_Entregas.NR_Comprovante_Entrega = " + ed.getNR_Comprovante_Entrega ();
      }

      if (String.valueOf (ed.getOID_Unidade ()) != null &&
          !String.valueOf (ed.getOID_Unidade ()).equals ("0") &&
          !String.valueOf (ed.getOID_Unidade ()).equals ("null")) {
        sql += " and Comprovantes_Entregas.OID_Unidade = " + ed.getOID_Unidade ();
      }

//// System.out.println("6 ");

      sql += " Order by Comprovantes_Entregas.NR_Comprovante_Entrega ";

      // System.out.println ("6 " + sql);

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);

      FormataDataBean DataFormatada = new FormataDataBean ();

      //popula
      while (res.next ()) {
        Comprovante_EntregaED edVolta = new Comprovante_EntregaED ();

//// System.out.println("7 ");

        edVolta.setOID_Comprovante_Entrega (res.getString ("OID_Comprovante_Entrega"));

        edVolta.setDT_Emissao (res.getString ("DT_Emissao"));

        DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
        edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());

        edVolta.setCD_Unidade (res.getString ("CD_Unidade"));

        edVolta.setNR_Comprovante_Entrega (res.getLong ("NR_Comprovante_Entrega"));

        list.add (edVolta);
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao listar Comprovante_Entrega");
      excecoes.setMetodo ("lista");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return list;
  }



}
