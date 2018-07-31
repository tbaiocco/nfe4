package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import com.master.ed.ContaED;
import com.master.bd.ContaBD;
import com.master.ed.Lancamento_ContabilED;
import com.master.ed.Movimento_Conta_CorrenteED;
import com.master.rl.Movimento_Conta_CorrenteRL;
import com.master.root.FormataDataBean;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Valor;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;
import com.master.util.Utilitaria;

public class Movimento_Conta_CorrenteBD {

  private ExecutaSQL executasql;
  Utilitaria util = new Utilitaria (executasql);
  Parametro_FixoED parametro_FixoED = Parametro_FixoED.getInstancia ();
  long lote = 0;

  public Movimento_Conta_CorrenteBD (ExecutaSQL sql) {
    this.executasql = sql;
    util = new Utilitaria (this.executasql);
  }

  public Movimento_Conta_CorrenteED transfere (Movimento_Conta_CorrenteED ed) throws Excecoes {

    int oid_Conta_Origem=0;
    int oid_Conta_Destino=0;
    String dm_Tipo_Conta_Corrente_Origem="";
    String dm_Tipo_Conta_Corrente_Destino="";
    String cd_Conta_Corrente_Origem="";
    String cd_Conta_Corrente_Destino="";
    String nm_Complemento="";
    String dm_Contabilizacao="N";

    try {

      String sql = " Select dm_Contabilizacao, CD_Conta_Corrente, oid_Conta, dm_tipo_conta_corrente " +
          " FROM Contas_Correntes " +
          " WHERE oid_Conta_Corrente = '" + ed.getOid_Conta_Corrente () + "'";

      ResultSet res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        oid_Conta_Origem=res.getInt ("oid_Conta");
        cd_Conta_Corrente_Origem=res.getString("CD_Conta_Corrente");
        dm_Tipo_Conta_Corrente_Origem=res.getString("dm_tipo_conta_corrente");
        dm_Contabilizacao=res.getString("dm_Contabilizacao");
      }

      sql = " Select CD_Conta_Corrente, oid_Conta, dm_tipo_conta_corrente " +
          " FROM Contas_Correntes " +
          " WHERE oid_Conta_Corrente = '" + ed.getOid_Conta_Corrente_Destino () + "'";

      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        oid_Conta_Destino=res.getInt ("oid_Conta");
        cd_Conta_Corrente_Destino=res.getString("CD_Conta_Corrente");
        dm_Tipo_Conta_Corrente_Destino=res.getString("dm_tipo_conta_corrente");
      }
      nm_Complemento="(Cta:"+cd_Conta_Corrente_Origem+ "p/" + cd_Conta_Corrente_Destino + ")";
      Movimento_Conta_CorrenteED edMovConta = new Movimento_Conta_CorrenteED ();
      edMovConta.setDT_Movimento_Conta_Corrente (ed.getDT_Movimento_Conta_Corrente ());
      if (ed.getDT_Movimento_Conta_Corrente_Origem() !=null && ed.getDT_Movimento_Conta_Corrente_Origem() .length()>4){
        edMovConta.setDT_Movimento_Conta_Corrente (ed.getDT_Movimento_Conta_Corrente_Origem ());
      }

      edMovConta.setOid_Conta_Corrente (ed.getOid_Conta_Corrente ());
      edMovConta.setNR_Documento (ed.getNR_Documento ());
      edMovConta.setDM_Debito_Credito ("D");
      edMovConta.setDM_Tipo_Lancamento ("G");
      edMovConta.setOID_Tipo_Documento (new Integer (parametro_FixoED.getOID_Tipo_Documento_Transferencia_Conta_Corrente ()));
      edMovConta.setVL_Lancamento (ed.getVL_Lancamento ());

      edMovConta.setOid_Conta (oid_Conta_Destino);

      edMovConta.setOid_Historico (new Integer (parametro_FixoED.getOID_Historico_Transferencia_Banco ()));
      edMovConta.setNM_Complemento_Historico (ed.getNM_Complemento_Historico ()+nm_Complemento);

      if ("U".equals (dm_Tipo_Conta_Corrente_Origem)) {
        edMovConta.setDM_Debito_Credito ("C");
        edMovConta.setOid_Historico (new Integer (parametro_FixoED.getOID_Historico_Transferencia_Caixa ()));
      }


      this.inclui (edMovConta);
      if ("S---".equals (parametro_FixoED.getDM_Gera_Lancamento_Contabil () ) && !"N".equals (dm_Contabilizacao)){
        // System.out.println ("vai contabilizar ");
        if (!this.inclui_Lancamento_Transferencia (edMovConta)) {
          throw new Excecoes ("Imposs�vel incluir lan�amentos cont�beis!" , this.getClass ().getName () , "inclui()");
        }
      }

      edMovConta = new Movimento_Conta_CorrenteED ();
      edMovConta.setDT_Movimento_Conta_Corrente (ed.getDT_Movimento_Conta_Corrente ());
      edMovConta.setOid_Conta_Corrente (ed.getOid_Conta_Corrente_Destino ());
      edMovConta.setNM_Complemento_Historico (ed.getNM_Complemento_Historico ()+nm_Complemento);
      edMovConta.setDM_Debito_Credito ("C");
      edMovConta.setNR_Documento (ed.getNR_Documento ());
      edMovConta.setDM_Tipo_Lancamento ("G");
      edMovConta.setOID_Tipo_Documento (new Integer (parametro_FixoED.getOID_Tipo_Documento_Transferencia_Conta_Corrente ()));
      edMovConta.setOid_Historico (new Integer (parametro_FixoED.getOID_Historico_Transferencia_Banco ()));
      edMovConta.setVL_Lancamento (ed.getVL_Transferencia ());

      edMovConta.setOid_Conta (oid_Conta_Origem);

      if ("U".equals (dm_Tipo_Conta_Corrente_Destino)) {
        edMovConta.setDM_Debito_Credito ("D");
        edMovConta.setOid_Historico (new Integer (parametro_FixoED.getOID_Historico_Transferencia_Caixa ()));
      }

      this.inclui (edMovConta);

      if ("S".equals (parametro_FixoED.getDM_Gera_Lancamento_Contabil () ) && !"N".equals (dm_Contabilizacao)){
        if (!this.inclui_Lancamento_Transferencia (edMovConta)) {
          throw new Excecoes ("Imposs�vel incluir lan�amentos cont�beis!" , this.getClass ().getName () , "inclui()");
        }
      }
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "transfere()");
    }
    return ed;
  }

  public Movimento_Conta_CorrenteED inclui (Movimento_Conta_CorrenteED ed) throws Excecoes {

    String sql = null;
    ResultSet res = null;
    String DM_Situacao = "A";
    String dm_Contabilizacao="N";
    String dm_Controle_Saldo="N";

    try {
      //*** Busca OID
       ed.setOid_Movimento_Conta_Corrente (util.getAutoIncremento ("oid_Movimento_Conta_Corrente" ,
                                                                   "Movimentos_Contas_Correntes"));
      sql = " SELECT * " +
            " FROM   Contas_Correntes " +
            " WHERE  oid_Conta_Corrente = '" + ed.getOid_Conta_Corrente () + "'";
      // System.out.println (sql);

      res = this.executasql.executarConsulta (sql);
      if (res.next ()) {
        if (ed.getOid_Conta()==0) {
          ed.setOid_Conta (res.getInt ("oid_Conta"));
        }
        dm_Contabilizacao=res.getString("dm_Contabilizacao");
        dm_Controle_Saldo=res.getString("dm_Controle_Saldo");
      }
      if (ed.getOid_Conta () == 0) {
        ed.setOid_Conta (parametro_FixoED.getOID_Conta_Despesa_Bancaria ());
      }

      if (ed.getVL_Lancamento ().doubleValue () == 0 || "N".equals (dm_Controle_Saldo)) {
        DM_Situacao = "X";
      }

      sql = "SELECT OID_Movimento_Conta_Corrente " +
          " FROM    Movimentos_Contas_Correntes " +
          " WHERE   oid_Conta_Corrente = '" + ed.getOid_Conta_Corrente () + "'" +
          "     AND DT_Movimento_Conta_Corrente = '" + ed.getDT_Movimento_Conta_Corrente () + "'" +
          "     AND DM_Debito_Credito = '" + ed.getDM_Debito_Credito () + "'" +
          "     AND DM_Situacao = '" + DM_Situacao + "'" +
          "     AND oid_Historico = " + ed.getOid_Historico () +
          "     AND oid_Lote_Pagamento = " + ed.getOid_Lote_Pagamento () +
          "     AND DM_Tipo_Lancamento = '" + ed.getDM_Tipo_Lancamento () + "'" +
          "     AND oid_Conta = " + ed.getOid_Conta () +
          "     AND oid_Lote_Recebimento = " + ed.getOid_Lote_Recebimento () +
          "     AND OID_Tipo_Documento = " + ed.getOID_Tipo_Documento () +
          "     AND OID_Movimento_Conta_Corrente = " + (ed.getOid_Movimento_Conta_Corrente () - 1) +
          "     AND VL_Lancamento = " + ed.getVL_Lancamento ();

      if (util.doValida (ed.getNR_Documento ())) {
        sql += " AND NR_Documento = '" + ed.getNR_Documento () + "'";
      }
      else {
        sql += " AND NR_Documento is null ";
      }
      if (util.doValida (ed.getNM_Complemento_Historico ())) {
        sql += " AND NM_Complemento_Historico = '" + ed.getNM_Complemento_Historico () + "'";
      }
      else {
        sql += " AND NM_Complemento_Historico is null";
      }
      if (util.doValida (ed.getOid_Movimento_Duplicata ())) {
        sql += " AND oid_Movimento_Duplicata = '" + ed.getOid_Movimento_Duplicata () + "'";
      }
      else {
        sql += " AND oid_Movimento_Duplicata is null";
      }

      // System.out.println (" sql 2=>" + sql);


      res = this.executasql.executarConsulta (sql);
      if (!res.next ()) {
        sql = " INSERT INTO Movimentos_Contas_Correntes(" +
            "      OID_Movimento_Conta_Corrente" +
            "     ,oid_Conta_Corrente" +
            "     ,NR_Documento" +
            "     ,DT_Movimento_Conta_Corrente" +
            "     ,NM_Complemento_Historico" +
            "     ,DT_STAMP" +
            "     ,USUARIO_STAMP" +
            "     ,DM_Debito_Credito" +
            "     ,DM_Situacao" +
            "     ,DM_Tipo_Lancamento" +
            "     ,DM_STAMP" +
            "     ,oid_Historico" +
            "     ,oid_Conta" +
            "     ,oid_Lote_Pagamento" +
            "     ,oid_Lote_Recebimento" +
            "     ,OID_Tipo_Documento" +
            "     ,VL_Lancamento" +
            "     ,OID_Multa" +
            "     ,oid_Movimento_Duplicata)" +
            " VALUES(" +
            ed.getOid_Movimento_Conta_Corrente () + ",";
        sql += "'" + ed.getOid_Conta_Corrente () + "',";
        sql += ed.getNR_Documento () == null ? "null," : "'" + ed.getNR_Documento () + "',";
        sql += "'" + ed.getDT_Movimento_Conta_Corrente () + "',";
        sql += ed.getNM_Complemento_Historico () == null ? "'null'," : "'" + ed.getNM_Complemento_Historico () + "',";
        sql += "'" + ed.getDt_stamp () + "',";
        sql += "'" + ed.getUsuario_Stamp () + "',";
        sql += "'" + ed.getDM_Debito_Credito () + "',";
        sql += "'" + DM_Situacao + "',";
        sql += "'" + ed.getDM_Tipo_Lancamento () + "',";
        sql += "'" + ed.getDm_Stamp () + "',";
        sql += ed.getOid_Historico () + ",";
        sql += ed.getOid_Conta () + ",";
        sql += ed.getOid_Lote_Pagamento () + ",";
        sql += ed.getOid_Lote_Recebimento () + ",";
        sql += ed.getOID_Tipo_Documento () + ",";
        sql += ed.getVL_Lancamento () + ", ";
        sql += ed.getOid_Multa () == null ? "null," : "'" + ed.getOid_Multa () + "',";
        sql += ed.getOid_Movimento_Duplicata () == null ? "null" : "'" + ed.getOid_Movimento_Duplicata () + "'";
        sql += ")";

      // System.out.println (" sql inclui MCC=>" + sql);

        executasql.executarUpdate (sql);
      }

      // System.out.println ("dm_Contabilizacao->>>>" + dm_Contabilizacao);

//      if("TRANF001".equals(ed.getNR_Documento()) && ed.getOID_Tipo_Documento().intValue() == parametro_FixoED.getOID_Tipo_Documento_Transferencia_Conta_Corrente())
//    	  ed.setDM_Tipo_Lancamento("L");

      if ("L".equals(ed.getDM_Tipo_Lancamento()) && ("S".equals (parametro_FixoED.getDM_Gera_Lancamento_Contabil ()) &&
          !"N".equals (dm_Contabilizacao))) {

      // System.out.println ("vai contabilizar ");


        if (!this.inclui_Lancamento_Movimento (ed)) {
          // System.out.println ("erro contabilizar ");

          throw new Excecoes ("Imposs�vel incluir lan�amentos cont�beis!" , this.getClass ().getName () , "inclui()");
        }
      }

      // System.out.println ("retorno");

      return ed;

    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "inclui()");
    }
  }

  public Movimento_Conta_CorrenteED getByRecord (Movimento_Conta_CorrenteED ed) throws Excecoes {

    String sql = null;
    Movimento_Conta_CorrenteED edVolta = new Movimento_Conta_CorrenteED ();

    try {

      sql = "SELECT Movimentos_Contas_Correntes.oid_Movimento_Conta_Corrente,  " +
          "       Movimentos_Contas_Correntes.oid_Movimento_Conta_Corrente,  " +
          "       Movimentos_Contas_Correntes.Oid_Conta_Corrente,  " +
          "       Movimentos_Contas_Correntes.oid_Historico,  " +
          "       Movimentos_Contas_Correntes.oid_Conta,  " +
          "       Movimentos_Contas_Correntes.oid_Lote_Pagamento,  " +
          "       Movimentos_Contas_Correntes.oid_Lote_Recebimento,  " +
          "       Movimentos_Contas_Correntes.OID_Tipo_Documento,  " +
          "       Movimentos_Contas_Correntes.DM_Debito_Credito,  " +
          "       Movimentos_Contas_Correntes.DM_Tipo_Lancamento,  " +
          "       Movimentos_Contas_Correntes.DM_Situacao,  " +
          "       Movimentos_Contas_Correntes.DT_Movimento_Conta_Corrente,  " +
          "       Movimentos_Contas_Correntes.NR_Documento,  " +
          "       Movimentos_Contas_Correntes.NM_Complemento_Historico,  " +
          "       Movimentos_Contas_Correntes.VL_Lancamento,  " +
          "       Contas_Correntes.CD_Conta_Corrente,  " +
          "       Contas_Correntes.NR_Conta_Corrente,  " +
          "       tipos_documentos.CD_Tipo_Documento,  " +
          "       tipos_documentos.NM_Tipo_Documento,  " +
          "       Contas.CD_Conta,  " +
          "       Contas.NM_Conta,  " +
          "       Historicos.CD_Historico,  " +
          "       Historicos.NM_Historico,  " +
          "       pessoas.NM_Razao_Social,  " +
          "       pessoas.NM_fantasia   " +
          " FROM  Movimentos_Contas_Correntes, Contas,  Contas_Correntes, pessoas, tipos_documentos, Historicos " +
          " WHERE Movimentos_Contas_Correntes.oid_Conta_Corrente = Contas_Correntes.oid_Conta_Corrente " +
          " AND   Contas_Correntes.oid_pessoa = Pessoas.oid_Pessoa " +
          " AND   Movimentos_Contas_Correntes.oid_Historico = Historicos.oid_Historico " +
          " AND   Movimentos_Contas_Correntes.oid_Conta = Contas.oid_Conta " +
          " AND   Movimentos_Contas_Correntes.oid_tipo_documento = tipos_documentos.oid_tipo_documento" +
          " AND   Movimentos_Contas_Correntes.oid_Movimento_Conta_Corrente = " + ed.getOid_Movimento_Conta_Corrente ();

      ResultSet res = this.executasql.executarConsulta (sql);
      while (res.next ()) {

        edVolta = new Movimento_Conta_CorrenteED ();
        edVolta.setOid_Movimento_Conta_Corrente (res.getLong ("oid_Movimento_Conta_Corrente"));

        edVolta.setCD_Tipo_Documento (res.getString ("CD_Tipo_Documento"));
        edVolta.setNM_Tipo_Documento (res.getString ("NM_Tipo_Documento"));
        edVolta.setOid_Conta_Corrente (res.getString ("Oid_Conta_Corrente"));
        edVolta.setNR_Conta (res.getString ("NR_Conta_Corrente"));
        edVolta.setCD_Conta_Corrente (res.getString ("CD_Conta_Corrente"));
        edVolta.setNM_Razao_Social (res.getString ("NM_Razao_Social"));
        edVolta.setCD_Conta (res.getString ("CD_Conta"));
        edVolta.setNM_Conta (res.getString ("NM_Conta"));

        edVolta.setCD_Historico (res.getString ("CD_Historico"));
        edVolta.setNM_Historico (res.getString ("NM_Historico"));
        edVolta.setOid_Historico (new Integer (res.getInt ("oid_Historico")));
        edVolta.setOid_Conta (res.getInt ("oid_Conta"));
        edVolta.setOID_Tipo_Documento (new Integer (res.getInt ("OID_Tipo_Documento")));
        edVolta.setOid_Lote_Pagamento (res.getInt ("oid_Lote_Pagamento"));
        edVolta.setOid_Lote_Recebimento (res.getInt ("oid_Lote_Recebimento"));

        edVolta.setNM_Fantasia (res.getString ("NM_fantasia"));
        edVolta.setDM_Debito_Credito (res.getString ("DM_Debito_Credito"));
        edVolta.setDM_Tipo_Lancamento (res.getString ("DM_Tipo_Lancamento"));

        edVolta.setDM_Situacao (res.getString ("DM_Situacao"));

        FormataDataBean DataFormatada = new FormataDataBean ();
        DataFormatada.setDT_FormataData (res.getString ("DT_Movimento_Conta_Corrente"));
        edVolta.setDT_Movimento_Conta_Corrente (DataFormatada.getDT_FormataData ());
        edVolta.setNR_Documento (res.getString ("NR_Documento"));

        if (edVolta.getNR_Documento () == null) {
          edVolta.setNR_Documento ("");
        }

        String tx_Obs = res.getString ("NM_Complemento_Historico");
        if (tx_Obs != null) {
          edVolta.setNM_Complemento_Historico (tx_Obs);
        }

        edVolta.setVL_Lancamento (new Double (res.getDouble ("VL_Lancamento")));
      }
      return edVolta;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "getByRecord()");
    }
  }

  public Movimento_Conta_CorrenteED consultaSaldo (Movimento_Conta_CorrenteED ed) throws Excecoes {

    String sql = null;
    Movimento_Conta_CorrenteED edVolta = new Movimento_Conta_CorrenteED ();
    try {

      sql = " SELECT DT_Saldo" +
          "       ,vl_saldo_Inicial" +
          "       ,vl_saldo_Final" +
          "       ,DM_Situacao" +
          " FROM Saldos" +
          " WHERE oid_Conta_Corrente='" + ed.getOid_Conta_Corrente () + "'" +
          "   AND DT_Saldo=(Select max(DT_Saldo) from Saldos where oid_Conta_Corrente='" + ed.getOid_Conta_Corrente () + "')";

      FormataDataBean DataFormatada = new FormataDataBean ();

      ResultSet res = this.executasql.executarConsulta (sql);
      edVolta.setDM_Saldo_Inicial ("");
      edVolta.setDT_Movimento_Conta_Corrente ("01/01/2001");
      edVolta.setVL_Saldo_Inicial (0);
      edVolta.setVL_Saldo_Final (0);
      edVolta.setDM_Situacao ("A");

      while (res.next ()) {
        edVolta = new Movimento_Conta_CorrenteED ();
        edVolta.setDM_Situacao (res.getString ("DM_Situacao"));
        edVolta.setDM_Saldo_Inicial ("OK");
        DataFormatada.setDT_FormataData (res.getString ("DT_Saldo"));
        edVolta.setDT_Movimento_Conta_Corrente (DataFormatada.getDT_FormataData ());
        edVolta.setVL_Saldo_Inicial (res.getDouble ("VL_Saldo_Inicial"));
        edVolta.setVL_Saldo_Final (res.getDouble ("VL_Saldo_Final"));
      }

      return edVolta;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "consultaSaldo()");
    }
  }

  public double consultaSaldoInicial (String oid_Conta_Corrente , String DT_Inicial) throws Excecoes {

    double vl_saldo = 0;
    String tem_saldo = "N";
    double vl_debito = 0 , vl_credito = 0;
    double vl_entrada = 0 , vl_saida = 0;
    try {

      String sql = " SELECT vl_saldo_Inicial" +
          " FROM Saldos" +
          " WHERE oid_Conta_Corrente='" + oid_Conta_Corrente + "'" +
          "   AND DT_Saldo='" + DT_Inicial + "'";

      ResultSet res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        tem_saldo = "S";
        vl_saldo = res.getDouble ("vl_saldo_Inicial");
      }

      if ("N".equals (tem_saldo)) {
        sql = " SELECT sum (vl_lancamento) as vl_debito " +
            " FROM Movimentos_Contas_Correntes" +
            " WHERE oid_Conta_Corrente='" + oid_Conta_Corrente + "'" +
            " AND DM_debito_Credito ='D' " +
            " AND DT_Movimento_Conta_Corrente<'" + DT_Inicial + "'";

        res = this.executasql.executarConsulta (sql);
        while (res.next ()) {
          vl_debito = res.getDouble ("vl_debito");
          vl_entrada = res.getDouble ("vl_debito");
        }
        sql = " SELECT sum (vl_lancamento) as vl_credito " +
            " FROM Movimentos_Contas_Correntes" +
            " WHERE oid_Conta_Corrente='" + oid_Conta_Corrente + "'" +
            " AND DM_debito_Credito ='C' " +
            " AND DT_Movimento_Conta_Corrente<'" + DT_Inicial + "'";
        res = this.executasql.executarConsulta (sql);
        while (res.next ()) {
          vl_credito = res.getDouble ("vl_credito");
          vl_saida = res.getDouble ("vl_credito");
        }

        vl_saldo = vl_credito - vl_debito;

        sql = " SELECT DM_Tipo_Conta_Corrente " +
            " FROM Contas_Correntes" +
            " WHERE oid_Conta_Corrente='" + oid_Conta_Corrente + "'";
        res = this.executasql.executarConsulta (sql);
        if (res.next ()) {
          if ("U".equals(res.getString("DM_Tipo_Conta_Corrente"))){
            vl_saldo = vl_entrada-vl_saida;
          }
        }

      }
      return vl_saldo;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "consultaSaldo()");
    }
  }

  public void altera (Movimento_Conta_CorrenteED ed) throws Excecoes {

    String sql = null;
    try {
      sql = " UPDATE Movimentos_Contas_Correntes SET ";
      sql += " NR_Documento = '" + ed.getNR_Documento () + "',";
      sql += " DT_Movimento_Conta_Corrente = '" + ed.getDT_Movimento_Conta_Corrente () + "',";
      sql += " NM_Complemento_Historico = '" + ed.getNM_Complemento_Historico () + "',";
      sql += " DT_STAMP = '" + ed.getDt_stamp () + "',";
      sql += " USUARIO_STAMP = '" + ed.getUsuario_Stamp () + "',";
      sql += " DM_STAMP = '" + ed.getDm_Stamp () + "',";
      sql += " DM_Debito_Credito = '" + ed.getDM_Debito_Credito () + "',";
      sql += " DM_Tipo_Lancamento = '" + ed.getDM_Tipo_Lancamento () + "',";
      sql += " oid_Historico = " + ed.getOid_Historico () + ",";
      sql += " oid_Conta = " + ed.getOid_Conta () + ",";
      sql += " VL_Lancamento = " + ed.getVL_Lancamento () + "";
      sql += " WHERE oid_Movimento_Conta_Corrente = " + ed.getOid_Movimento_Conta_Corrente ();

      executasql.executarUpdate (sql);
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "alterar()");
    }
  }
  public void alteraConta (Movimento_Conta_CorrenteED ed) throws Excecoes {

	    String sql = null;
	    try {
	      sql = " UPDATE Movimentos_Contas_Correntes SET ";
	      sql += " oid_Conta = " + ed.getOid_Conta ();
	      sql += " WHERE oid_Movimento_Conta_Corrente = " + ed.getOid_Movimento_Conta_Corrente ();

	      executasql.executarUpdate (sql);
	    }
	    catch (Exception exc) {
	      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "alterar()");
	    }
	  }

  public Movimento_Conta_CorrenteED geraSaldoInicial (Movimento_Conta_CorrenteED ed) throws Excecoes {

    Movimento_Conta_CorrenteED edVolta = new Movimento_Conta_CorrenteED ();
    String sql = null;
    double saldoInicial = 0;
    edVolta.setDM_Situacao ("OK");
    String dataUltimoMovimento = "";
    boolean existeSaldo = false;

    try {
      //*** Se existe Data de Movimento
       if (JavaUtil.doValida (util.getMaximoData ("DT_Saldo" , "Saldos" , "oid_Conta_Corrente='" + ed.getOid_Conta_Corrente () + "'"))) {
         existeSaldo = util.doExiste ("Saldos" , "DT_Saldo = '" + ed.getDT_Movimento_Conta_Corrente () + "'" +
                                      " AND oid_Conta_Corrente = '" + ed.getOid_Conta_Corrente () + "'");
         if (existeSaldo) {
           edVolta.setDM_Situacao ("J� foi gerado o saldo inicial para esta data!");
         }

         dataUltimoMovimento = util.getTableStringValue ("DT_Saldo" ,
                                                         "Saldos" ,
                                                         " DM_Situacao = 'A' " +
                                                         " AND oid_Conta_Corrente = '" + ed.getOid_Conta_Corrente () + "'");
         if (JavaUtil.doValida (dataUltimoMovimento)) {
           existeSaldo = true;
           edVolta.setDM_Situacao ("Finalize o movimento do dia " + new FormataDataBean ().getDT_FormataData (dataUltimoMovimento));
         }
         if (!existeSaldo) {
           sql = " SELECT DT_Saldo" +
               "       ,VL_Saldo_Inicial " +
               " FROM Saldos " +
               " WHERE oid_Conta_Corrente='" + ed.getOid_Conta_Corrente () + "'" +
               "  AND DT_Saldo=(Select max(DT_Saldo) from Saldos where oid_Conta_Corrente='" + ed.getOid_Conta_Corrente () + "' and DT_Saldo<'" + ed.getDT_Movimento_Conta_Corrente () + "')";

           ResultSet rs = this.executasql.executarConsulta (sql);
           while (rs.next ()) {
             dataUltimoMovimento = new FormataDataBean ().getDT_FormataData (rs.getString ("DT_Saldo"));
             saldoInicial = rs.getDouble ("VL_Saldo_Inicial");
           }

           sql = " SELECT DM_Debito_Credito, DM_Tipo_Conta_Corrente" +
               "          ,VL_Lancamento" +
               " FROM  Movimentos_Contas_Correntes, Contas_Correntes " +
               " WHERE Movimentos_Contas_Correntes.oid_Conta_Corrente =  Contas_Correntes.oid_Conta_Corrente " +
               "   AND Movimentos_Contas_Correntes.DM_Situacao='F'" +
               "   AND Movimentos_Contas_Correntes.DT_Movimento_Conta_Corrente = '" + dataUltimoMovimento + "'" +
               "   AND Movimentos_Contas_Correntes.oid_Conta_Corrente = '" + ed.getOid_Conta_Corrente () + "'";
           rs = this.executasql.executarConsulta (sql);
           while (rs.next ()) {
             if ("U".equals(rs.getString ("DM_Tipo_Conta_Corrente"))){//caixa
               if ("C".equals (rs.getString ("DM_Debito_Credito"))) {
                 saldoInicial -= rs.getDouble ("VL_Lancamento");
               }
               else {
                 saldoInicial += rs.getDouble ("VL_Lancamento");
               }
             }else{
               if ("C".equals (rs.getString ("DM_Debito_Credito"))) {
                 saldoInicial += rs.getDouble ("VL_Lancamento");
               }
               else {
                 saldoInicial -= rs.getDouble ("VL_Lancamento");
               }
             }
           }
         }
       }
      if (!existeSaldo) {
        long aux = System.currentTimeMillis ();
        sql = " INSERT INTO Saldos (" +
            "      oid_saldo" +
            "     ,DT_Saldo" +
            "     ,vl_saldo_inicial" +
            "     ,vl_saldo_final" +
            "     ,dm_situacao" +
            "     ,oid_conta_corrente) " +
            " VALUES (" +
            aux +
            ",'" + ed.getDT_Movimento_Conta_Corrente () + "'" +
            "," + saldoInicial +
            ",0.0,'A'" +
            ",'" + ed.getOid_Conta_Corrente () + "')";

        executasql.executarUpdate (sql);
        edVolta.setDM_Situacao ("Saldo Inicial Gerado com Sucesso!");
      }
      return edVolta;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "geraSaldoInicial()");
    }
  }

  public Movimento_Conta_CorrenteED acertaSaldo (Movimento_Conta_CorrenteED ed) throws Excecoes {

    Movimento_Conta_CorrenteED edVolta = new Movimento_Conta_CorrenteED ();
    String sql = null;
    double saldoIni = 0;
    double saldoFinal = 0;
    String dt_saldo = "";

    if (ed.getDT_Final()==null || ed.getDT_Final().length()<5){
      ed.setDT_Final (Data.getDataDMY ());
    }
    // System.out.println (" acertaSaldo >> ");

    try {

      sql = " SELECT DT_Saldo" +
          "       ,vl_saldo_Inicial" +
          "       ,vl_saldo_Final" +
          "       ,DM_Situacao" +
          " FROM Saldos" +
          " WHERE oid_Conta_Corrente='" + ed.getOid_Conta_Corrente () + "'" +
          "   AND DT_Saldo=(Select max(DT_Saldo) from Saldos where oid_Conta_Corrente='" + ed.getOid_Conta_Corrente () + "' AND DT_Saldo<'" + ed.getDT_Inicial () + "')";
      // System.out.println (sql);

      ResultSet res = this.executasql.executarConsulta (sql);
      // System.out.println (sql);
      while (res.next ()) {
        // System.out.println ("0k");
        saldoIni = res.getDouble ("vl_saldo_Final");
      }

      // System.out.println ("saldoAnterior->" + saldoIni);

      sql = " DELETE FROM Saldos " +
          " WHERE oid_Conta_Corrente='" + ed.getOid_Conta_Corrente () + "'" +
          " AND   DT_Saldo>='" + ed.getDT_Inicial () + "'";
      // System.out.println (" sql 1 >> " + sql);

      executasql.executarUpdate (sql);

      sql = " SELECT Movimentos_contas_correntes.DM_Debito_Credito" +
          "       ,Contas_Correntes.DM_Tipo_Conta_Corrente " +
          "       ,Movimentos_contas_correntes.DT_Movimento_Conta_Corrente" +
          "       ,Movimentos_contas_correntes.VL_Lancamento" +
          "  FROM 	  Movimentos_contas_correntes, contas_correntes, tipos_documentos, contas, Historicos, Pessoas " +
          "  WHERE    movimentos_contas_correntes.oid_conta_corrente = contas_correntes.oid_conta_corrente " +
          "  AND      movimentos_contas_correntes.oid_tipo_documento=tipos_documentos.oid_tipo_documento " +
          "  AND      contas_correntes.oid_pessoa = pessoas.oid_pessoa " +
          "  AND      movimentos_contas_correntes.oid_conta = contas.oid_conta " +
          "  AND      movimentos_contas_correntes.oid_Historico=Historicos.oid_Historico " +
          "  AND      movimentos_contas_correntes.oid_Conta_Corrente = '" + ed.getOid_Conta_Corrente () + "'" +
          "  AND      movimentos_contas_correntes.DT_Movimento_Conta_Corrente>='" + ed.getDT_Inicial () + "'" +
          "  AND      movimentos_contas_correntes.DT_Movimento_Conta_Corrente<='" + ed.getDT_Final()  + "'" +
          "  ORDER BY DT_Movimento_Conta_Corrente";

      // System.out.println (" acertaSaldo >> " + sql);

      ResultSet rs = this.executasql.executarConsulta (sql);
      while (rs.next ()) {

        // System.out.println (" acertaSaldo >> " + rs.getString ("DT_Movimento_Conta_Corrente"));

        if (dt_saldo.equals (rs.getString ("DT_Movimento_Conta_Corrente"))) {
          if ("U".equals (rs.getString ("DM_Tipo_Conta_Corrente"))) { //caixa
            if ("C".equals (rs.getString ("DM_Debito_Credito"))) {
              saldoFinal -= rs.getDouble ("VL_Lancamento");
            }
            else {
              saldoFinal += rs.getDouble ("VL_Lancamento");
            }
          }else {
            if ("C".equals (rs.getString ("DM_Debito_Credito"))) {
              saldoFinal += rs.getDouble ("VL_Lancamento");
            }
            else {
              saldoFinal -= rs.getDouble ("VL_Lancamento");
            }
          }
        }
        else {
          if (!dt_saldo.equals ("")) {
            long aux = System.currentTimeMillis ();
            sql = " INSERT INTO Saldos (" +
                "      oid_saldo" +
                "     ,DT_Saldo" +
                "     ,vl_saldo_inicial" +
                "     ,vl_saldo_final" +
                "     ,dm_situacao" +
                "     ,dm_tipo_finalizacao" +
                "     ,oid_conta_corrente) " +
                " VALUES (" +
                aux +
                ",'" + dt_saldo + "'" +
                "," + saldoIni +
                "," + saldoFinal +
                ",'F','AC'" +
                ",'" + ed.getOid_Conta_Corrente () + "')";

            executasql.executarUpdate (sql);

            saldoIni = saldoFinal;
          }
          if ("U".equals (rs.getString ("DM_Tipo_Conta_Corrente"))) { //caixa
            if ("C".equals (rs.getString ("DM_Debito_Credito"))) {
              saldoFinal = saldoIni - rs.getDouble ("VL_Lancamento");
            }
            else {
              saldoFinal = saldoIni + rs.getDouble ("VL_Lancamento");
            }
          }else{
            if ("C".equals (rs.getString ("DM_Debito_Credito"))) {
              saldoFinal = saldoIni + rs.getDouble ("VL_Lancamento");
            }
            else {
              saldoFinal = saldoIni - rs.getDouble ("VL_Lancamento");
            }
          }
        }
        dt_saldo = rs.getString ("DT_Movimento_Conta_Corrente");

      }
      if (saldoFinal != 0) {
        long aux = System.currentTimeMillis ();
        sql = " INSERT INTO Saldos (" +
            "      oid_saldo" +
            "     ,DT_Saldo" +
            "     ,vl_saldo_inicial" +
            "     ,vl_saldo_final" +
            "     ,dm_situacao" +
            "     ,dm_tipo_finalizacao" +
            "     ,oid_conta_corrente) " +
            " VALUES (" +
            aux +
            ",'" + dt_saldo + "'" +
            "," + saldoIni +
            "," + saldoFinal +
            ",'F','AC'" +
            ",'" + ed.getOid_Conta_Corrente () + "')";

        executasql.executarUpdate (sql);

        sql = " UPDATE Movimentos_Contas_Correntes SET" +
            "   DM_Situacao = 'F'" +
            " WHERE oid_Conta_Corrente = '" + ed.getOid_Conta_Corrente () + "'" +
            "  AND      movimentos_contas_correntes.DT_Movimento_Conta_Corrente<='" + ed.getDT_Final()  + "'" +
            "  AND DT_Movimento_Conta_Corrente >= '" + ed.getDT_Inicial () + "'";
        executasql.executarUpdate (sql);
      }
      edVolta.setDM_Situacao ("Saldo Gerado com Sucesso!");

      return edVolta;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "acertaSaldo()");
    }
  }

  public Movimento_Conta_CorrenteED geraLancamentoMotoristas (Movimento_Conta_CorrenteED ed) throws Excecoes {

    String sql = null;
    // System.out.println (" geraLancamentoMotoristas >> ");
    try {
      sql = " SELECT Motoristas.oid_Pessoa, " +
          "        Pessoas.NM_Razao_Social, " +
          "        Contas_Correntes.oid_Conta_Corrente, " +
          "        Motoristas.VL_INSS, " +
          "        Motoristas.VL_Plano_Saude " +
          " FROM Motoristas, Contas_Correntes, Pessoas" +
          " WHERE Motoristas.oid_Pessoa = Contas_Correntes.oid_Pessoa " +
          "   AND Motoristas.oid_Pessoa = Pessoas.oid_Pessoa " +
          "   AND Motoristas.DM_Situacao = 'A' " +
          "   AND (Motoristas.VL_INSS >0 OR Motoristas.VL_Plano_Saude>0) " +
          " ORDER BY Pessoas.NM_Razao_Social ";

      // System.out.println (sql);

      ResultSet res = this.executasql.executarConsulta (sql);
      while (res.next ()) {

        // System.out.println ("w1 ");
        if (res.getDouble ("VL_INSS") > 0) {
          ContaED edConta = new ContaED ();
          edConta.setOid_Conta (new Integer (297)); /// colocar futuramente em param fixo. Valor setado para GM
          edConta = new ContaBD (executasql).getByRecord (edConta);

          Movimento_Conta_CorrenteED edMov = new Movimento_Conta_CorrenteED ();
          edMov.setDT_Movimento_Conta_Corrente (ed.getDT_Movimento_Conta_Corrente ());
          edMov.setNR_Documento (String.valueOf (System.currentTimeMillis ()).toString ().substring (7));
          edMov.setNM_Complemento_Historico ("VL_Plano_Saude");
          edMov.setOid_Conta_Corrente (res.getString ("oid_Conta_Corrente"));
          edMov.setDM_Debito_Credito ("D");
          edMov.setDM_Tipo_Lancamento ("L");
          edMov.setOid_Lote_Pagamento (0);
          edMov.setVL_Lancamento (new Double (res.getDouble ("VL_INSS")));
          edMov.setOID_Tipo_Documento (edConta.getOid_Tipo_Documento ());
          edMov.setOid_Historico (edConta.getOid_Historico ());
          edMov.setOid_Conta (edConta.getOid_Conta ().intValue ());
          inclui (edMov);
        }
        if (res.getDouble ("VL_Plano_Saude") > 0) {
          ContaED edConta = new ContaED ();
          edConta.setOid_Conta (new Integer (299)); /// colocar futuramente em param fixo. Valor setado para GM
          edConta = new ContaBD (executasql).getByRecord (edConta);

          Movimento_Conta_CorrenteED edMov = new Movimento_Conta_CorrenteED ();
          edMov.setDT_Movimento_Conta_Corrente (ed.getDT_Movimento_Conta_Corrente ());
          edMov.setNR_Documento (String.valueOf (System.currentTimeMillis ()).toString ().substring (7));
          edMov.setNM_Complemento_Historico ("Plano Sa�de");
          edMov.setOid_Conta_Corrente (res.getString ("oid_Conta_Corrente"));
          edMov.setDM_Debito_Credito ("D");
          edMov.setDM_Tipo_Lancamento ("L");
          edMov.setOid_Lote_Pagamento (0);
          edMov.setVL_Lancamento (new Double (res.getDouble ("VL_Plano_Saude")));
          edMov.setOID_Tipo_Documento (edConta.getOid_Tipo_Documento ());
          edMov.setOid_Historico (edConta.getOid_Historico ());
          edMov.setOid_Conta (edConta.getOid_Conta ().intValue ());
          inclui (edMov);
        }
        // System.out.println ("0k");
      }

      return ed;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "acertaSaldo()");
    }
  }

  public Movimento_Conta_CorrenteED finalizaMovimento (Movimento_Conta_CorrenteED ed) throws Excecoes {

    Movimento_Conta_CorrenteED edVolta = new Movimento_Conta_CorrenteED ();
    String sql = null;
    double saldoInicial = ed.getVL_Saldo_Inicial ();
    double saldoFinal = 0;
    String dm_tipo_conta_corrente="";

    try {
      edVolta.setDM_Situacao ("");
      sql = " Select dm_tipo_conta_corrente " +
          " FROM Contas_Correntes " +
          " WHERE oid_Conta_Corrente = '" + ed.getOid_Conta_Corrente () + "'";

      ResultSet res = this.executasql.executarConsulta (sql);
      if (res.next ()) {
        dm_tipo_conta_corrente=res.getString("dm_tipo_conta_corrente");
      }




      if (util.doExiste ("Saldos" ,
                         "DM_Situacao = 'F'" +
                         " AND DT_Saldo = '" + ed.getDT_Movimento_Conta_Corrente () + "'" +
                         " AND oid_Conta_Corrente = '" + ed.getOid_Conta_Corrente () + "'")) {
        edVolta.setDM_Situacao ("Saldo j� finalizado neste dia!");
      }
      else {

        sql = "  SELECT SUM (VL_Lancamento) AS VL_Lancamento " +
            "  FROM 	Movimentos_contas_correntes, contas_correntes, tipos_documentos, contas, Historicos, Pessoas " +
            "  WHERE    movimentos_contas_correntes.oid_conta_corrente = contas_correntes.oid_conta_corrente " +
            "  AND      movimentos_contas_correntes.oid_tipo_documento=tipos_documentos.oid_tipo_documento " +
            "  AND      contas_correntes.oid_pessoa = pessoas.oid_pessoa " +
            "  AND      movimentos_contas_correntes.oid_conta = contas.oid_conta " +
            "  AND      movimentos_contas_correntes.oid_Historico=Historicos.oid_Historico " +
            "  AND      Movimentos_contas_correntes.DM_Debito_Credito = 'C' " +
            "  AND      Movimentos_contas_correntes.DT_Movimento_Conta_Corrente = '" + ed.getDT_Movimento_Conta_Corrente () + "'" +
            "  AND      Movimentos_contas_correntes.oid_Conta_Corrente = '" + ed.getOid_Conta_Corrente () + "'";
        res = this.executasql.executarConsulta (sql);
        while (res.next ()) {
          if ("U".equals(dm_tipo_conta_corrente)){
            saldoInicial -= res.getDouble ("VL_Lancamento");
          }else {
            saldoInicial += res.getDouble ("VL_Lancamento");
          }
        }

        sql = "  SELECT SUM (VL_Lancamento) AS VL_Lancamento " +
            "  FROM 	Movimentos_contas_correntes, contas_correntes, tipos_documentos, contas, Historicos, Pessoas " +
            "  WHERE    movimentos_contas_correntes.oid_conta_corrente = contas_correntes.oid_conta_corrente " +
            "  AND      movimentos_contas_correntes.oid_tipo_documento=tipos_documentos.oid_tipo_documento " +
            "  AND      contas_correntes.oid_pessoa = pessoas.oid_pessoa " +
            "  AND      movimentos_contas_correntes.oid_conta = contas.oid_conta " +
            "  AND      movimentos_contas_correntes.oid_Historico=Historicos.oid_Historico " +
            "  AND      Movimentos_contas_correntes.DM_Debito_Credito = 'D' " +
            "  AND      Movimentos_contas_correntes.DT_Movimento_Conta_Corrente = '" + ed.getDT_Movimento_Conta_Corrente () + "'" +
            "  AND      Movimentos_contas_correntes.oid_Conta_Corrente = '" + ed.getOid_Conta_Corrente () + "'";
        res = this.executasql.executarConsulta (sql);
        while (res.next ()) {
          if ("U".equals(dm_tipo_conta_corrente)){
            saldoInicial += res.getDouble ("VL_Lancamento");
          }else {
            saldoInicial -= res.getDouble ("VL_Lancamento");
          }
        }

        saldoFinal = saldoInicial;
        if (saldoInicial < 0) {
          saldoInicial = saldoInicial * -1;
        }

        DecimalFormat dec = new DecimalFormat ("########0.00");
        if ((dec.format(ed.getVL_Saldo_Final()).equals(dec.format(saldoInicial)))){
          sql = " UPDATE Movimentos_Contas_Correntes SET " +
              "        DM_Situacao = 'F' " +
              " WHERE DT_Movimento_Conta_Corrente = '" + ed.getDT_Movimento_Conta_Corrente () + "'" +
              "   AND oid_Conta_Corrente = '" + ed.getOid_Conta_Corrente () + "'";

          executasql.executarUpdate (sql);

          sql = " UPDATE Saldos SET " +
              "      DM_Situacao = 'F', DM_Tipo_Finalizacao='OK' " +
              "     ,VL_Saldo_Final = " + saldoFinal +
              " WHERE DT_Saldo = '" + ed.getDT_Movimento_Conta_Corrente () + "'" +
              "   AND oid_Conta_Corrente = '" + ed.getOid_Conta_Corrente () + "'";

          executasql.executarUpdate (sql);
          edVolta.setDM_Situacao ("Finalizado com Sucesso!");
        }
        else {
          edVolta.setDM_Situacao ("Valor do Saldo Final n�o confere com os Lan�amentos!");
        }
      }
      if (!JavaUtil.doValida (edVolta.getDM_Situacao ())) {
        edVolta.setDM_Situacao ("N�o h� Movimento neste dia!");
      }

      return edVolta;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "finalizaMovimento()");
    }
  }

  public void deleta (Movimento_Conta_CorrenteED ed) throws Excecoes {

    String sql = null;

    try {
      sql = " DELETE FROM Movimentos_Contas_Correntes" +
            " WHERE oid_Movimento_Conta_Corrente = " + ed.getOid_Movimento_Conta_Corrente ();

      executasql.executarUpdate (sql);

      if ("S".equals (parametro_FixoED.getDM_Gera_Lancamento_Contabil ())) {

        Lancamento_ContabilBD geralan = new Lancamento_ContabilBD (this.executasql);
        geralan.deleta_CTB_Movimento_Conta_Corrente(ed);

      }



    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "deleta()");
    }
  }

  public ArrayList lista (Movimento_Conta_CorrenteED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();

    double vl_saldo = 0;
    int nr_lancamentos = 0;
    ResultSet res = null;
    String dm_tipo_conta_corrente="";

    Movimento_Conta_CorrenteED edSaldo = new Movimento_Conta_CorrenteED ();
    edSaldo.setNM_Historico ("Saldo Inicial");
    edSaldo.setVL_Saldo_Final (vl_saldo);

    //list.add (edSaldo);

    try {

      sql = " Select CD_Moeda, NM_Moeda, DM_Tipo_conta_corrente FROM Moedas, Contas_Correntes " +
          " WHERE Moedas.oid_Moeda = Contas_Correntes.oid_Moeda " +
          " AND   Contas_Correntes.oid_conta_Corrente = '" + ed.getOid_Conta_Corrente () + "'";
      res = this.executasql.executarConsulta (sql);
      try {
        while (res.next ()) {
          ed.setCD_Moeda (res.getString ("CD_Moeda"));
          ed.setNM_Moeda (res.getString ("NM_Moeda"));
          dm_tipo_conta_corrente=res.getString ("dm_tipo_conta_corrente");
        }
      }
      finally {
        util.closeResultset (res);
      }


      sql = "SELECT Movimentos_Contas_Correntes.oid_Movimento_Conta_Corrente,  " +
          "       Movimentos_Contas_Correntes.oid_Movimento_Conta_Corrente,  " +
          "       Movimentos_Contas_Correntes.Oid_Conta_Corrente,  " +
          "       Movimentos_Contas_Correntes.oid_Historico,  " +
          "       Movimentos_Contas_Correntes.oid_Conta,  " +
          "       Movimentos_Contas_Correntes.oid_Lote_Pagamento,  " +
          "       Movimentos_Contas_Correntes.oid_Lote_Recebimento,  " +
          "       Movimentos_Contas_Correntes.OID_Tipo_Documento,  " +
          "       Movimentos_Contas_Correntes.DM_Debito_Credito,  " +
          "       Movimentos_Contas_Correntes.DM_Tipo_Lancamento,  " +
          "       Movimentos_Contas_Correntes.DM_Situacao,  " +
          "       Movimentos_Contas_Correntes.DT_Movimento_Conta_Corrente,  " +
          "       Movimentos_Contas_Correntes.NR_Documento,  " +
          "       Movimentos_Contas_Correntes.NM_Complemento_Historico,  " +
          "       Movimentos_Contas_Correntes.VL_Lancamento,  " +
          "       Contas_Correntes.CD_Conta_Corrente,  " +
          "       Contas_Correntes.DM_Tipo_Conta_Corrente,  " +
          "       Contas_Correntes.NR_Conta_Corrente,  " +
          "       tipos_documentos.CD_Tipo_Documento,  " +
          "       tipos_documentos.NM_Tipo_Documento,  " +
          "       Contas.CD_Conta,  " +
          "       Contas.NM_Conta,  " +
          "       Historicos.CD_Historico,  " +
          "       Historicos.NM_Historico,  " +
          "       pessoas.NM_Razao_Social,  " +
          "       pessoas.NM_fantasia   " +
          " FROM  Movimentos_Contas_Correntes, Contas,  Contas_Correntes, pessoas, tipos_documentos, Historicos " +
          " WHERE Movimentos_Contas_Correntes.oid_Conta_Corrente = Contas_Correntes.oid_Conta_Corrente " +
          " AND   Contas_Correntes.oid_pessoa = Pessoas.oid_Pessoa " +
          " AND   Movimentos_Contas_Correntes.oid_Historico = Historicos.oid_Historico " +
          " AND   Movimentos_Contas_Correntes.oid_Conta = Contas.oid_Conta " +
          " AND   Movimentos_Contas_Correntes.oid_tipo_documento = tipos_documentos.oid_tipo_documento" +
          " AND   Movimentos_Contas_Correntes.DT_Movimento_Conta_Corrente >= '" + ed.getDT_Inicial () + "'" +
          " AND   Movimentos_Contas_Correntes.DT_Movimento_Conta_Corrente <= '" + ed.getDT_Final () + "'" +
          " AND   Movimentos_Contas_Correntes.oid_Conta_Corrente = '" + ed.getOid_Conta_Corrente () + "'";

      if (ed.getNM_Complemento_Historico() != null && !ed.getNM_Complemento_Historico ().equals ("") && !ed.getNM_Complemento_Historico ().equals ("null")) {
          sql += " and Movimentos_Contas_Correntes.nm_complemento_historico like '%" + ed.getNM_Complemento_Historico () + "%'";
      }
      if (ed.getNR_Documento() != null && !ed.getNR_Documento ().equals ("") && !ed.getNR_Documento ().equals ("null")) {
          sql += " and Movimentos_Contas_Correntes.NR_Documento like '%" + ed.getNR_Documento () + "%'";
      }

      if (ed.getVL_1() > 0) {
          sql += " and Movimentos_Contas_Correntes.vl_lancamento >= " + ed.getVL_1 ();
      }
      if (ed.getVL_2() > 0) {
          sql += " and Movimentos_Contas_Correntes.vl_lancamento <= " + ed.getVL_2 ();
      }

      if ("U".equals (dm_tipo_conta_corrente)) {
        sql += " ORDER BY  Movimentos_Contas_Correntes.DT_Movimento_Conta_Corrente, Movimentos_Contas_Correntes.DM_Debito_Credito desc ";
      }else{
        sql += " ORDER BY Movimentos_Contas_Correntes.DT_Movimento_Conta_Corrente, Movimentos_Contas_Correntes.oid_Movimento_Conta_Corrente ";
      }
      //// System.out.println(sql);

      res = this.executasql.executarConsulta (sql);
      FormataDataBean DataFormatada = new FormataDataBean ();
      while (res.next ()) {
        nr_lancamentos++;

        if (nr_lancamentos==1){
          Movimento_Conta_CorrenteED edVolta = new Movimento_Conta_CorrenteED ();
          DataFormatada.setDT_FormataData (res.getString ("DT_Movimento_Conta_Corrente"));
          edVolta.setDT_Movimento_Conta_Corrente (DataFormatada.getDT_FormataData ());
          edVolta.setOid_Movimento_Conta_Corrente (0);
          edVolta.setCD_Tipo_Documento ("");
          edVolta.setOid_Conta_Corrente (ed.getOid_Conta_Corrente ());
          edVolta.setVL_Lancamento (new Double (0));
          edVolta.setDM_Debito_Credito (" ");

          edVolta.setNM_Historico ("SALDO INICIAL");
          vl_saldo = consultaSaldoInicial (ed.getOid_Conta_Corrente () , edVolta.getDT_Movimento_Conta_Corrente ());
          edVolta.setVL_Saldo_Final (vl_saldo);
          list.add (edVolta);
        }

        Movimento_Conta_CorrenteED edVolta = new Movimento_Conta_CorrenteED ();

        DataFormatada.setDT_FormataData (res.getString ("DT_Movimento_Conta_Corrente"));
        edVolta.setDT_Movimento_Conta_Corrente (DataFormatada.getDT_FormataData ());

        edVolta.setOid_Movimento_Conta_Corrente (res.getLong ("oid_Movimento_Conta_Corrente"));

        edVolta.setCD_Tipo_Documento (res.getString ("CD_Tipo_Documento"));
        edVolta.setNM_Tipo_Documento (res.getString ("NM_Tipo_Documento"));
        edVolta.setOid_Conta_Corrente (res.getString ("Oid_Conta_Corrente"));
        edVolta.setNR_Conta (res.getString ("NR_Conta_Corrente"));
        edVolta.setCD_Conta_Corrente (res.getString ("CD_Conta_Corrente"));
        edVolta.setNM_Razao_Social (res.getString ("NM_Razao_Social"));
        edVolta.setCD_Conta (res.getString ("CD_Conta"));
        edVolta.setNM_Conta (res.getString ("NM_Conta"));

        edVolta.setCD_Historico (res.getString ("CD_Historico"));
        edVolta.setNM_Historico (res.getString ("NM_Historico"));
        edVolta.setOid_Historico (new Integer (res.getInt ("oid_Historico")));
        edVolta.setOid_Conta (res.getInt ("oid_Conta"));
        edVolta.setOID_Tipo_Documento (new Integer (res.getInt ("OID_Tipo_Documento")));
        edVolta.setOid_Lote_Pagamento (res.getInt ("oid_Lote_Pagamento"));
        edVolta.setOid_Lote_Recebimento (res.getInt ("oid_Lote_Recebimento"));

        edVolta.setNM_Fantasia (res.getString ("NM_fantasia"));
        edVolta.setDM_Debito_Credito (res.getString ("DM_Debito_Credito"));
        edVolta.setDM_Tipo_Lancamento (res.getString ("DM_Tipo_Lancamento"));

        edVolta.setDM_Situacao (res.getString ("DM_Situacao"));

        edVolta.setNR_Documento (res.getString ("NR_Documento"));

        if (edVolta.getNR_Documento () == null) {
          edVolta.setNR_Documento ("");
        }
        String tx_Obs = res.getString ("NM_Complemento_Historico");
        if (tx_Obs != null) {
          edVolta.setNM_Complemento_Historico (tx_Obs);
        }

        edVolta.setVL_Lancamento (new Double (res.getDouble ("VL_Lancamento")));
        if ("U".equals(res.getString ("DM_Tipo_Conta_Corrente"))){
          if (res.getString ("DM_Debito_Credito").equals ("D")) {
            vl_saldo += res.getDouble ("VL_Lancamento");
          }
          else {
            vl_saldo -= res.getDouble ("VL_Lancamento");
          }
        }else{
          if (res.getString ("DM_Debito_Credito").equals ("D")) {
            vl_saldo -= res.getDouble ("VL_Lancamento");
          }
          else {
            vl_saldo += res.getDouble ("VL_Lancamento");
          }
        }
        edVolta.setVL_Saldo_Final (vl_saldo);
        list.add (edVolta);
      }
      return list;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "lista()");
    }
  }

  public ArrayList listaSaldo (Movimento_Conta_CorrenteED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    try {
      sql = "SELECT *   " +
          " FROM  Saldos " +
          " WHERE Saldos.oid_Conta_Corrente = '" + ed.getOid_Conta_Corrente () + "'";
      sql += " ORDER BY Saldos.DT_Saldo desc";

      ResultSet res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        Movimento_Conta_CorrenteED edVolta = new Movimento_Conta_CorrenteED ();

        FormataDataBean DataFormatada = new FormataDataBean ();
        DataFormatada.setDT_FormataData (res.getString ("DT_Saldo"));
        edVolta.setDT_Movimento_Conta_Corrente (DataFormatada.getDT_FormataData ());
        edVolta.setVL_Saldo_Inicial (res.getDouble ("VL_Saldo_Inicial"));
        edVolta.setVL_Saldo_Final (res.getDouble ("VL_Saldo_Final"));

        edVolta.setDM_Situacao ("Aberto");
        edVolta.setDM_Tipo_Finalizacao ("Pendente");
        if ("F".equals (res.getString ("DM_Situacao"))) {
          edVolta.setDM_Situacao ("Finalizado");
          edVolta.setDM_Tipo_Finalizacao ("Normal");
          if ("AC".equals (res.getString ("DM_Tipo_Finalizacao"))) {
            edVolta.setDM_Tipo_Finalizacao ("Acerto");
          }
        }

        list.add (edVolta);
      }
      return list;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "listaSaldo()");
    }
  }

  public byte[] gera_Rel_Movimento_Conta_Corrente (Movimento_Conta_CorrenteED ed) throws Excecoes {

    String sql = null;
    byte[] b = null;
    ResultSet res = null;
    ResultSet resLotePg = null;

    try {
      sql = " Select CD_Moeda, NM_Moeda FROM Moedas, Contas_Correntes " +
          " WHERE Moedas.oid_Moeda = Contas_Correntes.oid_Moeda " +
          " AND   Contas_Correntes.oid_conta_Corrente = '" + ed.getOid_Conta_Corrente () + "'";
      res = this.executasql.executarConsulta (sql);
      try {
        while (res.next ()) {
          ed.setCD_Moeda (res.getString ("CD_Moeda"));
          ed.setNM_Moeda (res.getString ("NM_Moeda"));
        }
      }
      finally {
        util.closeResultset (res);
      }

      sql = " SELECT *, " +
          " Pessoas.NM_Razao_Social, " +
          " Movimentos_Contas_Correntes.oid_Conta_Corrente " +
          " FROM Movimentos_Contas_Correntes, contas_correntes, tipos_documentos, Contas, Pessoas ";
      if ("M".equals(ed.getDM_Tipo_Conta_Corrente())){
        sql +=" ,Motoristas";
      }

     sql+=" WHERE Movimentos_Contas_Correntes.oid_conta_corrente = contas_correntes.oid_conta_corrente " +
          " AND   Movimentos_Contas_Correntes.oid_tipo_documento=tipos_documentos.oid_tipo_documento " +
          " AND   Movimentos_Contas_Correntes.oid_Conta=Contas.oid_Conta " +
          " AND   Movimentos_Contas_Correntes.VL_Lancamento >  0  " +
          " AND   Contas_correntes.oid_Pessoa=Pessoas.oid_Pessoa " +
          " AND   Movimentos_Contas_Correntes.DT_Movimento_Conta_Corrente >= '" + ed.getDT_Inicial () + "'" +
          " AND   Movimentos_Contas_Correntes.DT_Movimento_Conta_Corrente <= '" + ed.getDT_Final () + "'";
      if ("M".equals(ed.getDM_Tipo_Conta_Corrente())){
        sql +=" AND Pessoas.oid_Pessoa = Motoristas.oid_motorista ";
        if (!"T".equals(ed.getDM_Situacao()) && !"null".equals(ed.getDM_Situacao()) && !"".equals(ed.getDM_Situacao())){
          sql +=" AND Motoristas.DM_Situacao ='" + ed.getDM_Situacao() + "'";
        }
      }

      if ("R1".equals (ed.getDM_Relatorio ())) {
        sql += " AND Movimentos_Contas_Correntes.DM_Debito_Credito = 'D' " + " AND Contas.DM_Despesa = 'S' ";
      }

      if (ed.getOid_Conta_Corrente () != null && !ed.getOid_Conta_Corrente ().equals ("")) {
        sql += " and Movimentos_Contas_Correntes.oid_Conta_Corrente = '" + ed.getOid_Conta_Corrente () + "'";
      }

      if (ed.getOid_Conta () > 0) {
        sql += " and Movimentos_Contas_Correntes.oid_Conta = '" + ed.getOid_Conta () + "'";
      }
      if (!"T".equals (ed.getDM_Tipo_Conta_Corrente ())) {
        sql += " and Contas_Correntes.DM_Tipo_Conta_Corrente = '" + ed.getDM_Tipo_Conta_Corrente () + "'";
      }

      if ("R1".equals (ed.getDM_Relatorio ())) {
        sql += " ORDER BY Movimentos_Contas_Correntes.oid_Conta  ";
      }else{
        sql += " ORDER BY Movimentos_Contas_Correntes.oid_Conta_Corrente, Movimentos_Contas_Correntes.DT_Movimento_Conta_Corrente  ";
      }
      // System.out.println ("sql->> " + sql);

      res = this.executasql.executarConsulta (sql.toString ());
      try {
        try {
          Movimento_Conta_CorrenteRL conRL = new Movimento_Conta_CorrenteRL ();
          b = conRL.gera_Rel_Movimento_Conta_Corrente (res , ed , executasql);
        }
        finally {
          util.closeResultset (resLotePg);
        }
      }
      finally {
        util.closeResultset (res);
      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "gera_Rel_Movimento_Conta_Corrente()");
    }
    return b;
  }

  public byte[] imprime_Movimento_Conta_Corrente (Movimento_Conta_CorrenteED ed) throws Excecoes {

    String sql = null;
    byte[] b = null;
    ResultSet res = null;
    ResultSet resLotePg = null;
    String dm_tipo_conta_corrente="";

    try {
      sql = " Select CD_Moeda, NM_Moeda, DM_Tipo_conta_corrente FROM Moedas, Contas_Correntes " +
          " WHERE Moedas.oid_Moeda = Contas_Correntes.oid_Moeda " +
          " AND   Contas_Correntes.oid_conta_Corrente = '" + ed.getOid_Conta_Corrente () + "'";
      res = this.executasql.executarConsulta (sql);
      try {
        while (res.next ()) {
          ed.setCD_Moeda (res.getString ("CD_Moeda"));
          ed.setNM_Moeda (res.getString ("NM_Moeda"));
          dm_tipo_conta_corrente=res.getString ("dm_tipo_conta_corrente");
        }
      }
      finally {
        util.closeResultset (res);
      }

      sql = " select * from movimentos_contas_correntes, contas_correntes, tipos_documentos, pessoas, Historicos, Contas " +
          " where movimentos_contas_correntes.oid_conta_corrente = contas_correntes.oid_conta_corrente " +
          " and   movimentos_contas_correntes.oid_tipo_documento=tipos_documentos.oid_tipo_documento " +
          " and   contas_correntes.oid_pessoa = pessoas.oid_pessoa " +
          " and   movimentos_contas_correntes.oid_conta = contas.oid_conta " +
          " and   movimentos_contas_correntes.oid_Historico=Historicos.oid_Historico " +
          " AND   Movimentos_Contas_Correntes.DT_Movimento_Conta_Corrente >= '" + ed.getDT_Inicial () + "'" +
          " AND   Movimentos_Contas_Correntes.DT_Movimento_Conta_Corrente <= '" + ed.getDT_Final () + "'" +
          " AND   Movimentos_Contas_Correntes.oid_Conta_Corrente = '" + ed.getOid_Conta_Corrente () + "'";

      if ("M".equals(dm_tipo_conta_corrente)){
        sql += " ORDER BY pessoas.nm_razao_social, Movimentos_Contas_Correntes.DT_Movimento_Conta_Corrente ";
      }
      if ("B".equals (dm_tipo_conta_corrente)) {
        sql += " ORDER BY Movimentos_Contas_Correntes.DT_Movimento_Conta_Corrente ";
      }
      if ("U".equals (dm_tipo_conta_corrente)) {
        sql += " ORDER BY  Movimentos_Contas_Correntes.DT_Movimento_Conta_Corrente, Movimentos_Contas_Correntes.DM_Debito_Credito desc ";
      }


      res = this.executasql.executarConsulta (sql.toString ());
      try {
        sql = "select lotes_pagamentos.oid_lote_pagamento, " +
            "       lotes_pagamentos.nr_documento as nr_documento_lote, " +
            "       lotes_pagamentos.dt_emissao,  " +
            "       lotes_pagamentos.dt_programada,  " +
            "       lotes_pagamentos.dt_compensacao,  " +
            "       lotes_pagamentos.nm_favorecido,  " +
            "       lotes_pagamentos.vl_lote_pagamento,  " +
            "       contas_correntes.nr_conta_corrente,  " +
            "       tipos_documentos_lote.nm_tipo_documento as nm_tipo_documento_lote,  " +
            "       pessoas_conta_corrente.nm_razao_social  as nm_razao_social_conta_corrente " +
            " from  pessoas pessoas_conta_corrente, tipos_documentos tipos_documentos_lote, lotes_pagamentos, contas_correntes " +
            " where Lotes_Pagamentos.oid_tipo_documento = tipos_Documentos_lote.oid_tipo_documento and " +
            " Lotes_Pagamentos.oid_conta_corrente = contas_correntes.oid_conta_corrente and " +
            " Contas_correntes.oid_pessoa = Pessoas_conta_corrente.oid_pessoa " +
            " and Lotes_Pagamentos.DT_Compensacao is null " +
            " and Lotes_Pagamentos.DM_Situacao = 'L' " +
            " and Lotes_Pagamentos.VL_Lote_Pagamento > 0" +
            " and Lotes_Pagamentos.OID_Conta_Corrente = '" + ed.getOid_Conta_Corrente () + "'" +
            " and Lotes_Pagamentos.dt_emissao <= '" + ed.getDT_Final () + "'";

        sql += " order by lotes_pagamentos.DT_PROGRAMADA, lotes_pagamentos.nr_documento";
        resLotePg = this.executasql.executarConsulta (sql.toString ());
        try {
          Movimento_Conta_CorrenteRL conRL = new Movimento_Conta_CorrenteRL ();
          b = conRL.imprime_Movimento_Conta_Corrente (res , resLotePg , ed , executasql);
        }
        finally {
          util.closeResultset (resLotePg);
        }
      }
      finally {
        util.closeResultset (res);
      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "imprime_Movimento_Conta_Corrente()");
    }
    return b;
  }

  public boolean inclui_Lancamento_Movimento (Movimento_Conta_CorrenteED ed) throws Excecoes {
    String sql = null;
    boolean ok = false;
    String dm_Contabilizacao="N";

      // System.out.println ("inclui_Lancamento_Movimento");

    Lancamento_ContabilED lan = new Lancamento_ContabilED ();
    Lancamento_ContabilBD geralan = new Lancamento_ContabilBD (this.executasql);

    double vl_lancamento = 0;
    long oid_conta_contra = 0;
    int oid_moeda_cc = 0;

    try {
      lan.setOID_Unidade_Contabil (parametro_FixoED.getOID_Unidade_Padrao());

      sql = " SELECT oid_conta, oid_unidade, oid_moeda, dm_contabilizacao  " +
            " FROM  Contas_Correntes " +
            " WHERE oid_Conta_Corrente= '" + ed.getOid_Conta_Corrente () + "'";
      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);

      while (res.next ()) {
        dm_Contabilizacao=res.getString("dm_Contabilizacao");
        if ("C".equals(dm_Contabilizacao)){//caixa
          //lan.setOID_Unidade_Contabil (res.getLong ("oid_unidade"));
        }
        oid_conta_contra = res.getLong ("oid_conta");
        oid_moeda_cc = res.getInt ("oid_moeda");
      }

      lan.setCD_Historico (ed.getOid_Historico ().longValue ());
      vl_lancamento = ed.getVL_Lancamento ().doubleValue ();

      lan.setVL_Lancamento (vl_lancamento);
      lan.setOID_Movimento_Conta_Corrente (ed.getOid_Movimento_Conta_Corrente ());
      lan.setTx_Chave_Origem (String.valueOf (ed.getOid_Movimento_Conta_Corrente ()));
      lan.setOID_Origem (10);
      lan.setNM_Complementar (ed.getNM_Complemento_Historico ());
      lan.setDM_Stamp ("X");
      lan.setDT_Stamp (Data.getDataDMY ());
      lan.setDT_Lancamento (ed.getDT_Movimento_Conta_Corrente ());

      if (ed.getDM_Debito_Credito ().equals ("C")  || ed.getOid_Conta_Corrente().equals ("CAIXA") ) {
          lan.setDM_Acao ("C");
          if ("C---".equals(dm_Contabilizacao)){//caixa inverte
            lan.setDM_Acao ("D");
          }
          lan.setOID_Conta (ed.getOid_Conta ());
          lan.setNR_Lote (0);
          lan.setNR_Lote (geralan.inclui_Lancamento_Contabil (lan));

          lan.setDM_Acao ("D");
          if ("C---".equals(dm_Contabilizacao)){//caixa inverte
            lan.setDM_Acao ("C");
          }

          lan.setOID_Conta (oid_conta_contra);
          lan.setNR_Lote (geralan.inclui_Lancamento_Contabil (lan));
        }
        else {
          lan.setDM_Acao ("D");
          if ("C---".equals(dm_Contabilizacao)){//caixa inverte
            lan.setDM_Acao ("C");
          }
          lan.setOID_Conta (ed.getOid_Conta ());
          lan.setNR_Lote (0);
          lan.setNR_Lote (geralan.inclui_Lancamento_Contabil (lan));

          lan.setDM_Acao ("C");
          if ("C---".equals(dm_Contabilizacao)){//caixa inverte
            lan.setDM_Acao ("D");
          }
          lan.setOID_Conta (oid_conta_contra);
          lan.setNR_Lote (geralan.inclui_Lancamento_Contabil (lan));
        }
      ok = true;

    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "inclui_Lancamento()");
    }
    return ok;
  }

  public boolean inclui_Lancamento_Transferencia (Movimento_Conta_CorrenteED ed) throws Excecoes {
    String sql = null;
    boolean ok = false;
    Lancamento_ContabilED lan = new Lancamento_ContabilED ();
    Lancamento_ContabilBD geralan = new Lancamento_ContabilBD (this.executasql);

    /// ********  R  E  V  I  S  A  R    T  U  D  O************ /////////////
    /// ********  R  E  V  I  S  A  R    T  U  D  O************ /////////////
    /// ********  R  E  V  I  S  A  R    T  U  D  O************ /////////////
    /// ********  R  E  V  I  S  A  R    T  U  D  O************ /////////////
    /// ********  R  E  V  I  S  A  R    T  U  D  O************ /////////////
    /// ********  R  E  V  I  S  A  R    T  U  D  O************ /////////////
    /// ********  R  E  V  I  S  A  R    T  U  D  O************ /////////////

    double quantia = 0;
    long conta_contra = 0;

    try {

      sql = " SELECT oid_conta, oid_unidade from Contas_Correntes " +
            " WHERE oid_Conta_Corrente= '" + ed.getOid_Conta_Corrente () + "'";
      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);

      while (res.next ()) {
        conta_contra = res.getLong ("oid_conta");
        lan.setOID_Unidade_Contabil (res.getLong ("oid_unidade"));
      }
      if (lan.getOID_Unidade_Contabil () == 0) {
        lan.setOID_Unidade_Contabil (parametro_FixoED.getOID_Unidade_Padrao());
      }

      lan.setCD_Historico (ed.getOid_Historico ().longValue ());
      quantia = ed.getVL_Lancamento ().doubleValue ();

      lan.setVL_Lancamento (quantia);
      lan.setOID_Movimento_Conta_Corrente (ed.getOid_Movimento_Conta_Corrente ());
      lan.setTx_Chave_Origem (String.valueOf (ed.getOid_Movimento_Conta_Corrente ()));
      lan.setOID_Origem (9);
      lan.setNM_Complementar (ed.getNM_Complemento_Historico ());
      lan.setDM_Stamp ("X");
      lan.setDT_Stamp (Data.getDataDMY ());
      lan.setDT_Lancamento (ed.getDT_Movimento_Conta_Corrente ());
      if(ed.getDM_Debito_Credito ().equals("C"))
    	  lan.setDM_Acao ("D");
      else lan.setDM_Acao ("C");
      lan.setOID_Conta(ed.getOid_Conta());

      if (lote <= 0) {
        lote = geralan.inclui_Lancamento_Contabil (lan);
        lan.setNR_Lote (lote);
      }
      else {
        lan.setNR_Lote (lote);
        lote = geralan.inclui_Lancamento_Contabil (lan);
      }
      ok = true;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "inclui_Lancamento()");
    }
    return ok;
  }

}
