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
import java.text.DecimalFormat;
import java.util.ArrayList;

import com.master.ed.Lancamento_Centro_CustoED;
import com.master.ed.Lancamento_ContabilED;
import com.master.ed.Movimento_ProcessoED;
import com.master.rl.Movimento_ProcessoRL;
import com.master.root.FormataDataBean;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;

public class Movimento_ProcessoBD {
  public Movimento_ProcessoBD () {
    try {
      jbInit ();
    }
    catch (Exception ex) {
      ex.printStackTrace ();
    }
  }

  private ExecutaSQL executasql;

  public Movimento_ProcessoBD(ExecutaSQL sql) {
    this.executasql = sql;
  }


  public Movimento_ProcessoED inclui(Movimento_ProcessoED ed) throws Excecoes{

    String sql = null;
    String chave = null;
    long Nr_Movimento_Processo = 0;
    long valOid = 1;

    Movimento_ProcessoED Movimento_ProcessoED = new Movimento_ProcessoED();

    try{

      ResultSet rs = executasql.executarConsulta("select max(oid_Movimento_Processo) as result from Movimentos_Processos");
      while (rs.next()){
        valOid = rs.getLong("result") + 1;
      }

      sql = " insert into Movimentos_Processos "+
      "(OID_Movimento_Processo, "+
      "oid_Processo, "+
      "NR_Documento, "+
      "DT_Movimento_Processo, " +
      "NM_Complemento_Historico, "+
      "DT_STAMP, " +
      "USUARIO_STAMP, "+
      "DM_Debito_Credito, "+
      "DM_Situacao, "+
      "DM_Tipo_Lancamento, "+
      "DM_STAMP, "+
      "oid_Historico, "+
      "OID_Tipo_Documento, "+
      "VL_Lancamento "+
      ") values "+
      "(" + valOid + ",";
      sql += "'" + ed.getOid_Processo()+ "',";
      sql += ed.getNR_Documento() == null ? "null," : "'" + ed.getNR_Documento() + "',";
      sql += "'" + ed.getDT_Movimento_Processo() + "',";
      sql += ed.getNM_Complemento_Historico() == null ? "'null'," : "'" + ed.getNM_Complemento_Historico() + "',";
      sql += "'" + ed.getDt_stamp() + "',";
      sql += "'" + ed.getUsuario_Stamp() + "',";
      sql += "'" + ed.getDM_Debito_Credito() + "',";
      sql += "'A',";
      sql += "'" + ed.getDM_Tipo_Lancamento() + "',";
      sql += "'" + ed.getDm_Stamp() + "',";
      sql += ed.getOid_Historico() + ",";
      sql += ed.getOID_Tipo_Documento() + ",";
      sql += ed.getVL_Lancamento() + ")";

     // System.out.println(sql);

      int i = executasql.executarUpdate(sql);
      Movimento_ProcessoED.setOid_Movimento_Processo(valOid);

    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir Movimento_Processo");
      excecoes.setMetodo("Inclui(Movimento_ProcessoED)");
      excecoes.setExc(exc);
      throw excecoes;
    }

       //////// System.out.println("Passei 3");
    return Movimento_ProcessoED;

  }


  public Movimento_ProcessoED getByRecord(Movimento_ProcessoED ed)throws Excecoes{

    String sql = null;

    Movimento_ProcessoED edVolta = new Movimento_ProcessoED();

    try{

      sql = "select Movimentos_Processos.oid_Movimento_Processo,  " +
        "       Movimentos_Processos.oid_Movimento_Processo,  " +
        "       Movimentos_Processos.Oid_Processo,  " +
        "       Movimentos_Processos.oid_Historico,  " +
        "       Movimentos_Processos.OID_Tipo_Documento,  " +
        "       Movimentos_Processos.DM_Debito_Credito,  " +
        "       Movimentos_Processos.DM_Tipo_Lancamento,  " +
        "       Movimentos_Processos.DT_Movimento_Processo,  " +
        "       Movimentos_Processos.NR_Documento,  " +
        "       Movimentos_Processos.NM_Complemento_Historico,  " +
        "       Movimentos_Processos.VL_Lancamento,  " +
        "       Processos.NR_Processo,  " +
        "       Processos.DM_Situacao,  " +
        "       tipos_documentos.CD_Tipo_Documento,  " +
        "       tipos_documentos.NM_Tipo_Documento,  " +
        "       Historicos.CD_Historico,  " +
        "       Historicos.NM_Historico,  " +
        "       pessoas.NM_Razao_Social,  " +
        "       pessoas.NM_fantasia   " +
        " FROM  Movimentos_Processos, Processos, pessoas, tipos_documentos, Historicos " +
        " WHERE Movimentos_Processos.oid_Processo = Processos.oid_Processo " +
        " AND   Processos.oid_pessoa = Pessoas.oid_Pessoa " +
        " AND   Movimentos_Processos.oid_Historico = Historicos.oid_Historico "+
        " AND   Movimentos_Processos.oid_tipo_documento = tipos_documentos.oid_tipo_documento" +
        " AND   Movimentos_Processos.oid_Movimento_Processo = " + ed.getOid_Movimento_Processo();

       // System.out.println(sql);

      ResultSet res = null;

      FormataDataBean dataFormatada = new FormataDataBean();

      res = this.executasql.executarConsulta(sql);
      while (res.next()){
       // System.out.println("r1");

        edVolta = new Movimento_ProcessoED();
        edVolta.setOid_Movimento_Processo(new Long(res.getString("oid_Movimento_Processo")).longValue());



        edVolta.setCD_Tipo_Documento(res.getString("CD_Tipo_Documento"));
       //////// System.out.println("r2");
        edVolta.setNM_Tipo_Documento(res.getString("NM_Tipo_Documento"));
        edVolta.setOid_Processo(res.getString("Oid_Processo"));
        edVolta.setNR_Conta(res.getString("NR_Processo"));
        edVolta.setNM_Razao_Social(res.getString("NM_Razao_Social"));

        edVolta.setCD_Historico(res.getString("CD_Historico"));
        edVolta.setNM_Historico(res.getString("NM_Historico"));
        edVolta.setOid_Historico(new Integer(res.getString("oid_Historico")));
        edVolta.setOID_Tipo_Documento(new Integer(res.getString("OID_Tipo_Documento")));

        edVolta.setNM_Fantasia(res.getString("NM_fantasia"));
        edVolta.setDM_Debito_Credito(res.getString("DM_Debito_Credito"));
        edVolta.setDM_Tipo_Lancamento(res.getString("DM_Tipo_Lancamento"));

        edVolta.setDM_Situacao(res.getString("DM_Situacao"));


       //////// System.out.println("r8");

        FormataDataBean DataFormatada = new FormataDataBean();
        DataFormatada.setDT_FormataData(res.getString("DT_Movimento_Processo"));
        edVolta.setDT_Movimento_Processo(DataFormatada.getDT_FormataData());
        edVolta.setNR_Documento(res.getString("NR_Documento"));

        if (edVolta.getNR_Documento() == null ){
          edVolta.setNR_Documento("");
        }

        String tx_Obs = res.getString("NM_Complemento_Historico");
        if (tx_Obs != null)
          edVolta.setNM_Complemento_Historico(tx_Obs);

        edVolta.setVL_Lancamento(new Double(res.getString("VL_Lancamento")));

   //////// System.out.println("r11");

      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao acessar Movimento_Processo");
      excecoes.setMetodo("getByRecord(Movimento_ProcessoED)");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return edVolta;
  }

  public Movimento_ProcessoED consultaSaldo(Movimento_ProcessoED ed)throws Excecoes{

    String sql = null;

    Movimento_ProcessoED edVolta = new Movimento_ProcessoED();

    try{

      sql = "Select DT_Saldo, vl_saldo_Inicial, vl_saldo_Final, DM_Situacao from Saldos where oid_Processo='"+ed.getOid_Processo()+"' and DT_Saldo=(Select max(DT_Saldo) from Saldos where oid_Processo='"+ed.getOid_Processo() + "')";

      // System.out.println("aqui " + sql);

      ResultSet res = null;

      FormataDataBean DataFormatada = new FormataDataBean();

      res = this.executasql.executarConsulta(sql);
      edVolta.setDM_Saldo_Inicial("");
      edVolta.setDT_Movimento_Processo("01/01/2001");
      edVolta.setVL_Saldo_Inicial(0);
      edVolta.setVL_Saldo_Final(0);
      edVolta.setDM_Situacao("A");

      while (res.next()){
        edVolta = new Movimento_ProcessoED();
        // System.out.println("entrou " );
        edVolta.setDM_Situacao(res.getString("DM_Situacao"));
        // System.out.println("setDM_Situacao= " + res.getString("DM_Situacao"));

        edVolta.setDM_Saldo_Inicial("OK");
        DataFormatada.setDT_FormataData(res.getString("DT_Saldo"));
        edVolta.setDT_Movimento_Processo(DataFormatada.getDT_FormataData());
        // System.out.println("entrou 2" );
        edVolta.setVL_Saldo_Inicial(new Double(res.getString("VL_Saldo_Inicial")).doubleValue());
        edVolta.setVL_Saldo_Final(new Double(res.getString("VL_Saldo_Final")).doubleValue());
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao consultar saldo inicial");
      excecoes.setMetodo("getByRecord(Movimento_ProcessoED)");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return edVolta;
  }


  public void altera(Movimento_ProcessoED ed) throws Excecoes{

    String sql = null;
    ResultSet res = null;
    ResultSet rs = null;
    double valor = 0;
    int cont = 0, i;

    try{
      sql = " update Movimentos_Processos set ";
      sql += " NR_Documento = '" + ed.getNR_Documento() + "',";
      sql += "DT_Movimento_Processo = '" + ed.getDT_Movimento_Processo() + "',";
      sql += "NM_Complemento_Historico = '" + ed.getNM_Complemento_Historico() + "',";
      sql += " DT_STAMP = '" + ed.getDt_stamp() + "',";
      sql += " USUARIO_STAMP = '" + ed.getUsuario_Stamp() + "',";
      sql += " DM_STAMP = '" + ed.getDm_Stamp() + "',";
      sql += " DM_Debito_Credito = '" + ed.getDM_Debito_Credito() + "',";
      sql += " DM_Tipo_Lancamento = '" + ed.getDM_Tipo_Lancamento() + "',";
      sql += " oid_Historico = " + ed.getOid_Historico() + ",";
      sql += " VL_Lancamento = " + ed.getVL_Lancamento() + "";
      sql += " where oid_Movimento_Processo = " + ed.getOid_Movimento_Processo();


      // System.out.println(sql);

      i = executasql.executarUpdate(sql);

    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar dados de Movimento_Processo ");
      excecoes.setMetodo("altera(Movimento_ProcessoED)");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }


  public Movimento_ProcessoED geraSaldoInicial(Movimento_ProcessoED ed)throws Excecoes{

    Movimento_ProcessoED edVolta = new Movimento_ProcessoED();


    String sql = null;
    ResultSet rs = null;
    long aux=0;
    long i=0;
    double saldoInicial = 0;
    edVolta.setDM_Situacao("OK");
    String dataUltimoMovimento="";

    try{

      // System.out.println("geraSaldoInicial 8 z");

      sql = "Select DT_Saldo from Saldos where oid_Processo='"+ed.getOid_Processo()+ "'" ;

      // System.out.println("geraSaldoInicial 10 Y" +sql );

          rs = this.executasql.executarConsulta(sql);
       while(rs.next()){
      // System.out.println("geraSaldoInicial 10 Y " +dataUltimoMovimento );
           dataUltimoMovimento = "TEM";
      }
      // System.out.println("geraSaldoInicial 11x");

      if (dataUltimoMovimento.equals("TEM")){
      // System.out.println("geraSaldoInicial 13");

          sql = "Select oid_saldo from Saldos where "+
                " DT_Saldo = '" + ed.getDT_Movimento_Processo() + "'"+
                " and oid_Processo = '"+ed.getOid_Processo() + "'";

          rs = this.executasql.executarConsulta(sql);
          while(rs.next()){
            i++;
            edVolta.setDM_Situacao("Já foi gerado o saldo inicial para esta data.");
          }

          sql = "Select * from Movimentos_Processos where "+
                " DT_Movimento_Processo = '" + ed.getDT_Movimento_Processo() + "'"+
                " and oid_Processo = '"+ed.getOid_Processo() +"'" ;

          rs = this.executasql.executarConsulta(sql);
          while(rs.next()){
            i++;
            edVolta.setDM_Situacao("Já tem lançamentos incluídos nesta data.");
          }

          sql = "Select * from Movimentos_Processos where "+
                " DM_Situacao = 'A'"+
                " and oid_Processo = '"+ed.getOid_Processo()+ "'" ;

          rs = this.executasql.executarConsulta(sql);
          while(rs.next()){
            i++;
            FormataDataBean DataFormatada = new FormataDataBean();
            DataFormatada.setDT_FormataData(rs.getString("DT_Movimento_Processo"));
            edVolta.setDM_Situacao("Finalizar o movimento do dia " + DataFormatada.getDT_FormataData());
          }

          if (i==0){
          // System.out.println("geraSaldoInicial 20");

              sql = "Select DT_Saldo, vl_saldo_Inicial from Saldos where oid_Processo='"+ed.getOid_Processo()+"' and DT_Saldo=(Select max(DT_Saldo) from Saldos where oid_Processo='"+ed.getOid_Processo()+"' and DT_Saldo<'" + ed.getDT_Movimento_Processo() + "')";
              rs = this.executasql.executarConsulta(sql);
              while(rs.next()){
                  FormataDataBean DataFormatada = new FormataDataBean();
                  DataFormatada.setDT_FormataData(rs.getString("DT_Saldo"));
                  dataUltimoMovimento = DataFormatada.getDT_FormataData();

                  saldoInicial = rs.getDouble("vl_saldo_Inicial");
              }

          // System.out.println("geraSaldoInicial 21");

              sql = "select * from Movimentos_Processos where  DM_Situacao='F' and DT_Movimento_Processo = '"+ dataUltimoMovimento + "' and oid_Processo = '"+ed.getOid_Processo()+"'";
          // System.out.println("geraSaldoInicial 21 s" + sql);

              rs = this.executasql.executarConsulta(sql);

              while( rs.next() ){
                  if( rs.getString( "dm_debito_credito" ).equals( "C" ) )
                        saldoInicial += rs.getDouble( "vl_lancamento" );
                  else  saldoInicial -= rs.getDouble( "vl_lancamento" );
              }
          }
      }
      if (i==0){

          aux = System.currentTimeMillis();
          sql = "Insert into Saldos (oid_saldo, DT_Saldo, vl_saldo_inicial, vl_saldo_final, dm_situacao,oid_Processo) values ("+aux+",'"+ed.getDT_Movimento_Processo()+"', "+saldoInicial+ ",0.0,'A','"+ed.getOid_Processo()+"')";
          // System.out.println(sql);

          i = executasql.executarUpdate(sql);
          edVolta.setDM_Situacao("Saldo Inicial Gerado com Sucesso.");
      }


    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar dados de Movimento_Processo ");
      excecoes.setMetodo("altera(Movimento_ProcessoED)");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return edVolta;
  }



  public void deleta(Movimento_ProcessoED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from Movimentos_Processos WHERE oid_Movimento_Processo = ";
      sql += ed.getOid_Movimento_Processo();

      int i = executasql.executarUpdate(sql);

    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao excluir Movimento_Processo");
      excecoes.setMetodo("deleta(Movimento_ProcessoED)");
      excecoes.setExc(exc);
      throw excecoes;

    }
  }

  public ArrayList lista(Movimento_ProcessoED edComp)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();
    Movimento_ProcessoED ed = (Movimento_ProcessoED)edComp;

    try{

    sql =" select * from movimentos_Processos, Processos, tipos_documentos, pessoas, Historicos " +
         " where movimentos_Processos.oid_Processo = Processos.oid_Processo " +
         " and   movimentos_Processos.oid_tipo_documento=tipos_documentos.oid_tipo_documento " +
         " and   Processos.oid_pessoa = pessoas.oid_pessoa " +
         " and   movimentos_Processos.oid_Historico=Historicos.oid_Historico " +
         //" AND   Movimentos_Processos.DT_Movimento_Processo >= '" + ed.getDT_Inicial() + "'"+
         //" AND   Movimentos_Processos.DT_Movimento_Processo <= '" + ed.getDT_Final() + "'" +
         " AND   Movimentos_Processos.oid_Processo = '" + ed.getOid_Processo() + "'" ;

      sql += " ORDER BY Movimentos_Processos.DT_Movimento_Processo, Movimentos_Processos.oid_Movimento_Processo ";

      // System.out.println(sql);


      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      while (res.next()){
      // System.out.println("lista mov movimento conta A");

        Movimento_ProcessoED edVolta = new Movimento_ProcessoED();

        edVolta.setOid_Movimento_Processo(new Long(res.getString("oid_Movimento_Processo")).longValue());
        edVolta.setNR_Documento(res.getString("NR_Documento"));
        edVolta.setOid_Processo(res.getString("Oid_Processo"));
        edVolta.setNM_Razao_Social(res.getString("NM_Razao_Social"));

        edVolta.setDM_Debito_Credito(res.getString("DM_Debito_Credito"));
        edVolta.setVL_Lancamento(new Double(res.getString("VL_Lancamento")));
      // System.out.println("lista mov movimento conta B");

        FormataDataBean DataFormatada = new FormataDataBean();

        DataFormatada.setDT_FormataData(res.getString("DT_Movimento_Processo"));
        edVolta.setDT_Movimento_Processo(DataFormatada.getDT_FormataData());
      // System.out.println("lista mov movimento conta C");

        edVolta.setNM_Historico(res.getString("NM_Historico"));

        list.add(edVolta);
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao listar dados de Movimento_Processo");
      excecoes.setMetodo("lista(Movimento_ProcessoED)");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return list;
  }

  public byte[] imprime_Movimento_Processo(Movimento_ProcessoED ed)throws Excecoes{

    String sql = null;
    byte[] b = null;
    ResultSet res = null;
    try{
    sql = " Select CD_Moeda, NM_Moeda FROM Moedas, Processos " +
          " WHERE Moedas.oid_Moeda = Processos.oid_Moeda " +
          " AND   Processos.oid_Processo = '" + ed.getOid_Processo() + "'";
           res = this.executasql.executarConsulta(sql);
           while (res.next()){
             ed.setCD_Moeda(res.getString("CD_Moeda"));
             ed.setNM_Moeda(res.getString("NM_Moeda"));
           }

    sql =" select * from movimentos_Processos, Processos, tipos_documentos, pessoas, Historicos, Cidades " +
         " where movimentos_Processos.oid_Processo = Processos.oid_Processo " +
         " AND   movimentos_Processos.oid_tipo_documento=tipos_documentos.oid_tipo_documento " +
         " AND   Processos.oid_pessoa = pessoas.oid_pessoa " +
         " AND   Cidades.oid_Cidade = pessoas.oid_Cidade " +
         " AND   movimentos_Processos.oid_Historico=Historicos.oid_Historico " +
         " AND   Movimentos_Processos.oid_Processo = '" + ed.getOid_Processo() + "'" ;

      sql += " ORDER BY Movimentos_Processos.DT_Movimento_Processo, Movimentos_Processos.oid_Movimento_Processo ";
      // System.out.println(" BD " + sql);

      res = this.executasql.executarConsulta(sql.toString());

      Movimento_ProcessoRL conRL = new Movimento_ProcessoRL();
      b =  conRL.imprime_Movimento_Processo(res, ed, executasql);

    }

    catch (Excecoes e){
      throw e;
    }
    catch(Exception exc){
      Excecoes exce = new Excecoes();
      exce.setExc(exc);
      exce.setMensagem("Erro no método listar");
      exce.setClasse(this.getClass().getName());
      exce.setMetodo("geraRelatorio(CompromissoED ed)");
    }
    return b;
  }

  private void jbInit () throws Exception {
  }

}
