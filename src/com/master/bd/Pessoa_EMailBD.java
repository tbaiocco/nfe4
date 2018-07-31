package com.master.bd;

import java.sql.*;

import com.master.ed.*;
import com.master.util.*;
import com.master.util.bd.*;
import java.util.ArrayList;
import com.master.util.mail.Mailer;
import com.master.util.ed.Parametro_FixoED;

public class Pessoa_EMailBD {

  private ExecutaSQL executasql;
  Parametro_FixoED parametro_FixoED = new Parametro_FixoED ();

  public Pessoa_EMailBD (ExecutaSQL sql) {
    this.executasql = sql;
  }

  public Pessoa_EMailED inclui (Pessoa_EMailED ed) throws Excecoes {

    // System.out.println ("INCLUI p Emails 1 ");

    String sql = null;
    long valOid = 0;
    ResultSet rs = null;
    Pessoa_EMailED edVolta = (Pessoa_EMailED) ed;

    try {

      sql = "SELECT MAX(pessoas_email.OID_Pessoa_EMail) as oid FROM pessoas_email";
      // System.out.println (sql);
      rs = this.executasql.executarConsulta (sql);

      while (rs.next ()) {
        valOid = rs.getLong ("oid") + 1;
      }

      edVolta.setOid_Pessoa_EMail (valOid);

      sql = "INSERT INTO pessoas_email (oid_evento, oid_Host, nm_email_origem, nm_email_destino, nm_usuario, nm_usuario_destino, oid_pessoa, oid_Pessoa_EMail) " +
          "VALUES (" + ed.getOid_evento () + ", " +
          ed.getOid_Host () + ", '" +
          ed.getNm_Email_Origem () + "', '" +
          ed.getNm_Email_Destino () + "', '" +
          ed.getNm_Usuario () + "', '" +
          ed.getNm_Usuario_Destino () + "', '" +
          ed.getOid_Pessoa () + "'," +
          valOid + ")";

      // System.out.println (sql);

      executasql.executarUpdate (sql);

    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao incluir");
      excecoes.setMetodo ("inclui");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return edVolta;
  }

  public boolean deleta (Pessoa_EMailED ed) throws Excecoes {

    String sql = null;
    boolean ok = true;
    try {

      sql = "DELETE FROM pessoas_email WHERE oid_Pessoa_EMail=" + ed.getOid_Pessoa_EMail ();
      // System.out.println (sql);
      executasql.executarUpdate (sql);
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMetodo ("deleta");
      excecoes.setExc (exc);
      ok = false;
      throw excecoes;
    }
    return ok;
  }

  public Pessoa_EMailED getByRecord (Pessoa_EMailED ed) throws Excecoes {
    Pessoa_EMailED edVolta = new Pessoa_EMailED ();
    try {
      ResultSet res = this.executasql.executarConsulta (montaSql (ed));
      while (res.next ()) {
        edVolta = carregaED (res);
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao selecionar");
      excecoes.setMetodo ("getByRecord");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return edVolta;
  }

  public String montaSql (Pessoa_EMailED ed) throws Excecoes {

    String sql = " SELECT *  " +
        " FROM  pessoas_email, pessoas, eventos_email " +
        " where pessoas_email.oid_pessoa  = pessoas.oid_pessoa " +
        " AND   pessoas_email.oid_evento  = eventos_email.oid_evento ";
    if (ed.getOid_Pessoa_EMail () > 0) {
      sql += " AND   pessoas_email.Oid_Pessoa_EMail = " + ed.getOid_Pessoa_EMail ();
    }
    if (ed.getOid_Pessoa () != null && ed.getOid_Pessoa ().length () > 4) {
      sql += " AND   pessoas_email.Oid_Pessoa = '" + ed.getOid_Pessoa () + "'";
    }

    // System.out.println (sql);
    return sql;
  }

  public Pessoa_EMailED carregaED (ResultSet res) throws Excecoes {

    Pessoa_EMailED edVolta = new Pessoa_EMailED ();

    // System.out.println ("carregaED");
    try {

      // System.out.println ("carregaED=" + res.getString ("oid_pessoa"));
      // System.out.println ("carregaED=" + res.getString ("Nm_evento"));
      // System.out.println ("carregaED=" + res.getString ("nm_email_destino"));

      edVolta.setOid_Pessoa (res.getString ("oid_pessoa"));

      edVolta.setOid_Pessoa_EMail (res.getLong ("oid_Pessoa_EMail"));
      edVolta.setOid_evento (res.getLong ("oid_Evento"));
      edVolta.setOid_Host (res.getLong ("oid_Host"));
      edVolta.setCd_Evento (res.getString ("cd_Evento"));
      edVolta.setNm_Evento (res.getString ("Nm_evento"));

      edVolta.setNm_Razao_Social (res.getString ("nm_razao_social"));

      edVolta.setNm_Usuario (" ");
      if (res.getString ("Nm_usuario") != null && !res.getString ("Nm_usuario").equals ("null")) {
        edVolta.setNm_Usuario (res.getString ("Nm_usuario"));
      }

      edVolta.setNm_Usuario_Destino (" ");
      if (res.getString ("Nm_usuario_destino") != null && !res.getString ("Nm_usuario_destino").equals ("null")) {
        edVolta.setNm_Usuario_Destino (res.getString ("Nm_usuario_destino"));
      }

      if (res.getString ("nm_email_origem") != null && !res.getString ("nm_email_origem").equals ("null")) {
        edVolta.setNm_Email_Origem (res.getString ("nm_email_origem"));
      }

      if (res.getString ("nm_email_destino") != null && !res.getString ("nm_email_destino").equals ("null")) {
        edVolta.setNm_Email_Destino (res.getString ("nm_email_destino"));
      }

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ");
      excecoes.setMetodo ("carregaED");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return edVolta;
  }

  public ArrayList lista (Pessoa_EMailED ed) throws Excecoes {

    ArrayList list = new ArrayList ();
    try {
      ResultSet res = this.executasql.executarConsulta (montaSql (ed));
      while (res.next ()) {
        list.add (carregaED (res));
      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao selecionar");
      excecoes.setMetodo ("getByRecord");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return list;
  }

  public void update (Pessoa_EMailED ed) throws Excecoes {

    String sql = null;

    try {
      sql = " UPDATE pessoas_email SET " +
          " nm_email_origem='" + ed.getNm_Email_Origem () + "', " +
          " nm_email_destino='" + ed.getNm_Email_Destino () + "', " +
          " nm_usuario='" + ed.getNm_Usuario () + "', " +
          " nm_usuario_destino='" + ed.getNm_Usuario_Destino () + "'" +
          "WHERE oid_pessoa_email=" + ed.getOid_Pessoa_EMail ();

      // System.out.println (sql);
      executasql.executarUpdate (sql);

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar");
      excecoes.setMetodo ("update");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }


  public void envia_eMail (String oid_Pessoa , String CD_Evento , String NM_Subject , String TX_Mensagem ) throws Excecoes {
    this.envia_eMail(oid_Pessoa , CD_Evento , NM_Subject , TX_Mensagem , "" , "", "");
  }

  public void envia_eMail (String oid_Pessoa , String CD_Evento , String NM_Subject , String TX_Mensagem , String NM_Path , String NM_File) throws Excecoes {
    this.envia_eMail(oid_Pessoa , CD_Evento , NM_Subject , TX_Mensagem , NM_Path , NM_File, "");
  }


  public void envia_eMail (String oid_Pessoa , String CD_Evento , String NM_Subject , String TX_Mensagem , String NM_Path , String NM_File, String NM_Email_Usuario) throws Excecoes {

    String sql = null;
      // System.out.println ("envia email 1");

    try {
      if ("S".equals(parametro_FixoED.getDM_Envia_Email_Eventos())){

      // System.out.println ("envia email 2");

        sql = " SELECT * FROM Pessoas_Email, Eventos_Email, Host_Email " +
            " WHERE Pessoas_Email.oid_Evento = Eventos_Email.oid_Evento " +
            " AND Pessoas_Email.oid_Host = Host_Email.oid_Host " +
            " AND Eventos_Email.CD_Evento ='" + CD_Evento + "'";
        if ( (JavaUtil.doValida (oid_Pessoa))) {
          sql += " AND Pessoas_Email.oid_Pessoa = '" + oid_Pessoa + "'";
        }

      // System.out.println ("envia email "+sql);

        ResultSet rsMail = executasql.executarConsulta (sql);
        while (rsMail.next ()) {
          Mailer email = new Mailer ();
          EmailED emailED = new EmailED ();

          emailED.setNM_Host (rsMail.getString ("nm_Host"));
          emailED.setNM_Username (rsMail.getString ("nm_Username"));
          emailED.setNM_Senha (rsMail.getString ("nm_Senha"));
          emailED.setNM_Protocolo (rsMail.getString ("nm_Protocolo"));
          emailED.setNR_Porta (rsMail.getString ("nr_Porta"));
          emailED.setDM_Autenticacao (rsMail.getString ("dm_autenticacao"));
          emailED.setNM_Email_Origem (rsMail.getString ("nm_Email_Origem"));
          emailED.setNM_Email_Destino (rsMail.getString ("nm_Email_Destino"));
          if ( (JavaUtil.doValida (NM_Email_Usuario))) {
            emailED.setNM_Email_Destino (NM_Email_Usuario);
          }
          emailED.setNM_Subject (rsMail.getString ("nm_Evento"));
          if ( (JavaUtil.doValida (NM_Subject))) {
            emailED.setNM_Subject (NM_Subject);
          }
          emailED.setTX_Mensagem ("Sr(a) " + rsMail.getString ("nm_usuario_destino") + "\n" + "\n" + TX_Mensagem + "\n" + "\n" + "\n" + " Atenciosamente " + "\n" + rsMail.getString ("nm_usuario"));
          //atach
          emailED.setNM_File (NM_File);
          emailED.setNM_Path (NM_Path);

          email.sendMail (emailED);
        }
      }
    }
    catch (Excecoes exc) {
      throw exc;
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao enviar eMail");
      excecoes.setMetodo ("envia_eMail()");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

}
