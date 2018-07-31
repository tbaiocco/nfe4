package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.master.ed.Nota_FiscalED;
import com.master.rl.MinutaRL;
import com.master.root.FormataDataBean;
import com.master.util.Excecoes;
import com.master.util.Utilitaria;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;
import com.master.util.Data;


public class MinutaBD  {

    private ExecutaSQL executasql;
    Utilitaria util = new Utilitaria(executasql);

    public MinutaBD(ExecutaSQL sql) {
        this.executasql = sql;
        util = new Utilitaria(this.executasql);
    }


  public Nota_FiscalED inclui(Nota_FiscalED ed)
  throws Excecoes{

    String sql = null;
    long valOid = 0;
    long NR_Despacho=0;
    String chave = null;

    // System.out.println(" INCLUI BD 1" );


    Nota_FiscalED conED = new Nota_FiscalED();
    Parametro_FixoED paramED = new Parametro_FixoED();

    try{
      chave = String.valueOf(ed.getOID_Pessoa()) + String.valueOf(ed.getNR_Nota_Fiscal()) + ed.getNM_Serie() + ed.getDM_Tipo_Conhecimento();

      if (!paramED.getDM_Despacho().equals("N") || ed.getDM_Tipo_Conhecimento().equals("4")) {  //4=reentrega
        ResultSet rs = null;


        sql =  " SELECT NR_PROXIMO_DESPACHO ";
        sql += " FROM Parametros_Filiais ";
        sql += " WHERE Parametros_Filiais.OID_Unidade = " + paramED.getOID_Unidade_Padrao();
        rs = this.executasql.executarConsulta(sql);
        while (rs.next()){
              NR_Despacho = rs.getLong("NR_PROXIMO_DESPACHO");
        }
        NR_Despacho ++;
        sql =  " UPDATE Parametros_Filiais SET NR_PROXIMO_DESPACHO= " + (NR_Despacho);
        sql += " WHERE Parametros_Filiais.OID_Unidade = " + paramED.getOID_Unidade_Padrao();

        int u = executasql.executarUpdate(sql);


        // System.out.println(" INCLUI BD 2" );

        if (ed.getNR_Nota_Fiscal()<=0 || paramED.getDM_Despacho().equals("T")){
           chave = String.valueOf(ed.getOID_Pessoa()) + String.valueOf(NR_Despacho) + "999";
        }
      }
      if (ed.getDM_Tipo_Conhecimento().equals("4")){
        chave = chave + String.valueOf(NR_Despacho);
      }

      long nr_sequencia = System.currentTimeMillis();

      ed.setOID_Nota_Fiscal(chave);


      sql = " INSERT into Notas_Fiscais (" +
          " OID_Nota_Fiscal, " +
          " Nr_Sequencia, "+
          " OID_Natureza_Operacao, "+
          " OID_Pessoa, "+
          " OID_Pessoa_Destinatario, "+
          " OID_Pessoa_Redespacho, "+
          " OID_Pessoa_Consignatario, "+
          " NR_Nota_Fiscal, "+
          " NR_Despacho, "+
          " NR_Volumes, "+
          " NM_Serie, " +
          " DT_Entrada, " +
          " HR_Entrada, " +
          " VL_Nota_Fiscal, "+
          " DM_Situacao, "+
          " NR_Pedido, "+
          " DT_Emissao, "+
          " NR_Peso, "+
          " NR_Itens, "+
          " NR_Peso_Cubado, "+
          " DM_Transferencia, " +
          " DM_Exportacao, "+
          " TX_Observacao, "+
          " DM_Tipo_Pagamento, "+
          " OID_Modal, "+
          " OID_Produto, "+
          " OID_Coleta , "+
          " DM_Tipo_Conhecimento, "+
          " NR_Lote, "+
          " NR_Cubagem, "+
          " NR_Cubagem_Total," +
          " VL_Total_Frete, "+
          " DM_Tabela, "+
          "NM_Especie "+
          ") values ";

      sql += "('" + 
          ed.getOID_Nota_Fiscal () + "'," +
          nr_sequencia + "," +
          ed.getOID_Natureza_Operacao () + ",'" +
          ed.getOID_Pessoa () + "','" +
          ed.getOID_Pessoa_Destinatario () + "','" +
          ed.getOID_Pessoa_Redespacho () + "','" +
          ed.getOID_Pessoa_Consignatario () + "'," +
          ed.getNR_Nota_Fiscal () + "," +
          NR_Despacho + "," +
          ed.getNR_Volumes () + ",'" +
          ed.getNM_Serie () + "','" +
          ed.getDT_Entrada () + "','" +
          ed.getHR_Entrada () + "'," +
          ed.getVL_Nota_Fiscal () + ",'" +
          ed.getDM_Situacao () + "','" +
          ed.getNR_Pedido () + "','" +
          ed.getDT_Emissao () + "'," +
          ed.getNR_Peso () + "," +
          ed.getNR_Itens () + "," +
          ed.getNR_Peso_Cubado () + ",'" +
          ed.getDM_Transferencia () + "','" +
          ed.getDM_Exportacao () + "','" +
          ed.getTX_Observacao () + "','" +
          ed.getDM_Tipo_Pagamento () + "'," +
          ed.getOID_Modal () + "," +
          ed.getOID_Produto () + "," +
          ed.getOID_Coleta () + ",'" +
          ed.getDM_Tipo_Conhecimento () + "','" +
          ed.getNR_Lote () + "'," +
          ed.getNR_Cubagem () + "," +
          ed.getNR_Cubagem_Total () + "," +
          ed.getVL_Total_Frete () + ",'" +
          ed.getDM_Tabela () + "', " +
          util.getSQLStringDef (ed.getNM_Especie () , "") +
          ")";

       // System.out.println(sql);


      int i = executasql.executarUpdate(sql);

      conED.setOID_Nota_Fiscal(chave);
    } catch(SQLException e){
        e.printStackTrace();
      throw new Excecoes(e.getMessage(), e, getClass().getName(), "inclui(Nota_FiscalED ed)");
    } catch (Exception ex){
        ex.printStackTrace();
        throw new Excecoes(ex.getMessage(), ex, null, "");
    }
    return conED;
  }

  public void altera(Nota_FiscalED ed) throws Excecoes{

    String sql = null;

    try{

      sql = " update Notas_Fiscais set OID_Pessoa= '" + ed.getOID_Pessoa() + "', OID_Pessoa_Destinatario= '" + ed.getOID_Pessoa_Destinatario() + "', OID_Natureza_Operacao= " + ed.getOID_Natureza_Operacao() +
            "  ,OID_Pessoa_Consignatario = '" + ed.getOID_Pessoa_Consignatario() + "', OID_Pessoa_Redespacho = '" + ed.getOID_Pessoa_Redespacho() + "'" +
            "  ,NR_Nota_Fiscal= " + ed.getNR_Nota_Fiscal() +  ", NR_Lote= '" + ed.getNR_Lote() + "', NR_Volumes= " + ed.getNR_Volumes() + ", DT_Entrada= '" + ed.getDT_Entrada() + "', HR_Entrada= '" + ed.getHR_Entrada() + "', VL_Nota_Fiscal= " + ed.getVL_Nota_Fiscal() + ", DM_Situacao= '" + ed.getDM_Situacao() + "', NR_Pedido= '" + ed.getNR_Pedido() + "', DT_Emissao= '" + ed.getDT_Emissao() + "', NR_Peso= " + ed.getNR_Peso() + ", NR_Peso_Cubado= " + ed.getNR_Peso_Cubado()+ ", NR_Itens= " + ed.getNR_Itens() + " , DM_Transferencia= '" + ed.getDM_Transferencia() + "'" + " , DM_Exportacao= '" + ed.getDM_Exportacao() + "' , DM_Tipo_Pagamento= '" + ed.getDM_Tipo_Pagamento()+ "' , TX_Observacao= '" + ed.getTX_Observacao() + "'" +
            "  ,OID_Modal= " + ed.getOID_Modal() +
            "  ,OID_Produto= " + ed.getOID_Produto() +
            "  ,NR_Cubagem= " + ed.getNR_Cubagem() +
            "  ,NR_Cubagem_Total= " + ed.getNR_Cubagem_Total() +
            "  ,VL_Total_Frete = " + ed.getVL_Total_Frete() +
            "  ,NM_Especie = " + util.getSQLStringDef(ed.getNM_Especie(), "");
      sql += " where oid_Nota_Fiscal = '" + ed.getOID_Nota_Fiscal() + "'" ;


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


  public void deleta(Nota_FiscalED ed) throws Excecoes{

    String sql = null;
    String DM_Impresso = null;

    try{

      sql = "delete from Notas_Fiscais WHERE oid_Nota_Fiscal = ";
      sql += "('" + ed.getOID_Nota_Fiscal() + "')";

       executasql.executarUpdate(sql);
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




  public ArrayList lista(Nota_FiscalED ed)
  throws Excecoes{
      ArrayList list = new ArrayList();

      try{
          String sql =
              	" SELECT  *, " +
                " Pessoa_Remetente.NM_Razao_Social as NM_Razao_Social_Remetente, " +
                " Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario " +
                " FROM   Notas_Fiscais, Pessoas Pessoa_Remetente, Pessoas Pessoa_Destinatario, Clientes " +
                " WHERE  Notas_Fiscais.oid_Pessoa = Pessoa_Remetente.oid_Pessoa " +
                " AND    Notas_Fiscais.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa " +
                " AND    Notas_Fiscais.oid_Pessoa = Clientes.oid_Pessoa " +
                " AND    Clientes.CD_Acesso = '" + ed.getCD_Acesso() + "'";


          if (String.valueOf(ed.getNR_Nota_Fiscal()) != null &&
                  !String.valueOf(ed.getNR_Nota_Fiscal()).equals("0") &&
                  !String.valueOf(ed.getNR_Nota_Fiscal()).equals("null")){
              sql += " and Notas_Fiscais.NR_Nota_Fiscal = " + ed.getNR_Nota_Fiscal();
          }

          if (ed.getNR_Despacho()>0){
              sql += " and Notas_Fiscais.NR_Despacho = " + ed.getNR_Despacho();
          }

          if (String.valueOf(ed.getOID_Produto()) != null &&
                  !String.valueOf(ed.getOID_Produto()).equals("0")  &&
                  !String.valueOf(ed.getOID_Produto()).equals("null")){
              sql += " and Notas_Fiscais.OID_Produto = " + ed.getOID_Produto();
          }

          if (String.valueOf(ed.getNR_Pedido()) != null &&
                  !String.valueOf(ed.getNR_Pedido()).equals("")  &&
                  !String.valueOf(ed.getNR_Pedido()).equals("null")){
              sql += " and Notas_Fiscais.NR_Pedido = '" + ed.getNR_Pedido() + "'";
          }

          if (String.valueOf(ed.getNR_Lote()) != null &&
                  !String.valueOf(ed.getNR_Lote()).equals("")  &&
                  !String.valueOf(ed.getNR_Lote()).equals("null")){
              sql += " and Notas_Fiscais.NR_Lote = '" + ed.getNR_Lote() + "'";
          }


          if (String.valueOf(ed.getOID_Pessoa()) != null &&
                  !String.valueOf(ed.getOID_Pessoa()).equals("")  &&
                  !String.valueOf(ed.getOID_Pessoa()).equals("null")){
              sql += " and Notas_Fiscais.OID_Pessoa = '" + ed.getOID_Pessoa() + "'";
          }
          if (String.valueOf(ed.getOID_Pessoa_Destinatario()) != null &&
                  !String.valueOf(ed.getOID_Pessoa_Destinatario()).equals("")  &&
                  !String.valueOf(ed.getOID_Pessoa_Destinatario()).equals("null")){
              sql += " and Notas_Fiscais.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario() + "'";
          }
          if (String.valueOf(ed.getOID_Pessoa_Consignatario()) != null &&
                  !String.valueOf(ed.getOID_Pessoa_Consignatario()).equals("")  &&
                  !String.valueOf(ed.getOID_Pessoa_Consignatario()).equals("null")){
              sql += " and Notas_Fiscais.OID_Pessoa_Consignatario = '" + ed.getOID_Pessoa_Consignatario() + "'";
          }
          if (String.valueOf(ed.getDT_Emissao_Inicial()) != null &&
                  !String.valueOf(ed.getDT_Emissao_Inicial()).equals("")  &&
                  !String.valueOf(ed.getDT_Emissao_Inicial()).equals("null")){
              sql += " and Notas_Fiscais.DT_Emissao >= '" + ed.getDT_Emissao_Inicial() + "'";
          }
          if (String.valueOf(ed.getDT_Emissao_Final()) != null &&
                  !String.valueOf(ed.getDT_Emissao_Final()).equals("")  &&
                  !String.valueOf(ed.getDT_Emissao_Final()).equals("null")){
              sql += " and Notas_Fiscais.DT_Emissao <= '" + ed.getDT_Emissao_Final() + "'";
          }

          sql += " Order by Notas_Fiscais.NR_Nota_Fiscal";

          ResultSet res = null;
          res = this.executasql.executarConsulta(sql);

          //popula

          FormataDataBean DataFormatada = new FormataDataBean();

          while (res.next()){
              Nota_FiscalED edVolta = new Nota_FiscalED();

              edVolta.setNR_Peso(res.getDouble("NR_Peso"));
              edVolta.setNR_Volumes(res.getDouble("NR_Volumes"));
              edVolta.setVL_Nota_Fiscal(res.getDouble("VL_Nota_Fiscal"));


              edVolta.setNR_Nota_Fiscal(res.getLong("NR_Nota_Fiscal"));

              double NR_Despacho = res.getDouble("NR_Despacho");
              if (NR_Despacho> 0) {
                  edVolta.setNR_Despacho(res.getLong("NR_Despacho"));
              }

              String OID_Coleta = res.getString("oid_Coleta");
              if (String.valueOf(OID_Coleta) != null && !String.valueOf(OID_Coleta).equals("") && !String.valueOf(OID_Coleta).equals("0") && !String.valueOf(OID_Coleta).equals(null)
                      && !String.valueOf(OID_Coleta).equals("null")){
                  edVolta.setOID_Coleta(res.getLong("oid_Coleta"));
              }

              edVolta.setOID_Nota_Fiscal(res.getString("OID_Nota_Fiscal"));
              edVolta.setNM_Pessoa_Remetente(res.getString("NM_Razao_Social_Remetente"));
              edVolta.setNM_Pessoa_Destinatario(res.getString("NM_Razao_Social_Destinatario"));

              edVolta.setDT_Emissao(res.getString("DT_Emissao"));
              DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
              edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());

              edVolta.setDM_Situacao(res.getString("DM_Situacao"));
              edVolta.setDT_Entrada(res.getString("DT_Entrada"));
              DataFormatada.setDT_FormataData(edVolta.getDT_Entrada());
              edVolta.setDT_Entrada(DataFormatada.getDT_FormataData());
              edVolta.setNM_Erro_Calculo(res.getString("NM_Erro_Calculo"));


              list.add(edVolta);
          }
      } catch (SQLException e){
          throw new Excecoes(e.getMessage(), e, getClass().getName(), "lista(Nota_FiscalED ed)");
      }

      return list;
  }


  public Nota_FiscalED getByRecord(Nota_FiscalED ed)throws Excecoes{

    String sql = null;
    Nota_FiscalED edVolta = new Nota_FiscalED();


          // System.out.println("Nota_FiscalED getByRecord");


    try{

      sql = " select * from Notas_Fiscais where 1=1 ";

      if (String.valueOf(ed.getOID_Nota_Fiscal()) != null &&
          !String.valueOf(ed.getOID_Nota_Fiscal()).equals("")){
        sql += " and Notas_Fiscais.OID_Nota_Fiscal = '" + ed.getOID_Nota_Fiscal() + "'";
      }
      if (String.valueOf(ed.getOID_Pessoa()) != null &&
        !String.valueOf(ed.getOID_Pessoa()).equals("") &&
        !String.valueOf(ed.getOID_Pessoa()).equals("null")){
        sql += " and Notas_Fiscais.oid_Pessoa = '" + ed.getOID_Pessoa() + "'";
      }

      if (String.valueOf(ed.getNR_Nota_Fiscal()) != null &&
        !String.valueOf(ed.getNR_Nota_Fiscal()).equals("0")){
        sql += " and Notas_Fiscais.NR_Nota_Fiscal = " + ed.getNR_Nota_Fiscal();
      }
      if (String.valueOf(ed.getNR_Despacho()) != null &&
        !String.valueOf(ed.getNR_Despacho()).equals("0")){
        sql += " and Notas_Fiscais.NR_Despacho = " + ed.getNR_Despacho();
      }

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      FormataDataBean DataFormatada = new FormataDataBean();

      while (res.next()){
        edVolta.setDM_Situacao(res.getString("DM_Situacao"));
        edVolta.setHR_Entrada(res.getString("HR_Entrada"));
        edVolta.setNR_Pedido(res.getString("NR_Pedido"));
        edVolta.setNR_Lote(res.getString("NR_Lote"));
        edVolta.setNM_Serie(res.getString("NM_Serie"));
        edVolta.setTX_Observacao(res.getString("TX_Observacao"));
        edVolta.setDM_Tipo_Pagamento(res.getString("DM_Tipo_Pagamento"));
        edVolta.setNR_Peso(res.getDouble("NR_Peso"));
        edVolta.setNR_Cubagem(res.getDouble("NR_Cubagem"));
        edVolta.setNR_Cubagem_Total(res.getDouble("NR_Cubagem_Total"));
        edVolta.setNR_Nota_Fiscal(res.getLong("NR_Nota_Fiscal"));

        double NR_Despacho = res.getDouble("NR_Despacho");
        if (NR_Despacho> 0) {
          edVolta.setNR_Despacho(res.getLong("NR_Despacho"));
        }

        edVolta.setNR_Volumes(res.getDouble("NR_Volumes"));
        edVolta.setOID_Nota_Fiscal(res.getString("OID_Nota_Fiscal"));
        edVolta.setOID_Pessoa(res.getString("OID_Pessoa"));
        edVolta.setOID_Modal(res.getLong("OID_Modal"));
        edVolta.setOID_Produto(res.getLong("OID_Produto"));
        edVolta.setOID_Coleta(res.getLong("OID_Coleta"));
        edVolta.setOID_Pessoa_Destinatario(res.getString("OID_Pessoa_Destinatario"));
        edVolta.setOID_Pessoa_Consignatario(res.getString("OID_Pessoa_Consignatario"));
        edVolta.setOID_Pessoa_Redespacho(res.getString("OID_Pessoa_Redespacho"));
        edVolta.setOID_Natureza_Operacao(res.getLong("OID_NATUREZA_OPERACAO"));
        edVolta.setVL_Nota_Fiscal(res.getDouble("VL_Nota_Fiscal"));

        edVolta.setDT_Emissao(res.getString("DT_Emissao"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
        edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());

        edVolta.setDT_Entrada(res.getString("DT_Entrada"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Entrada());
        edVolta.setDT_Entrada(DataFormatada.getDT_FormataData());

        edVolta.setNM_Pessoa_Entrega(" ");
        edVolta.setDT_Previsao_Entrega (" ");
        edVolta.setHR_Previsao_Entrega (" ");
        edVolta.setDT_Entrega (" ");
        edVolta.setHR_Entrega (" ");


        sql =" Select  Conhecimentos.DT_Emissao, Conhecimentos.DT_Previsao_Entrega, Conhecimentos.HR_Previsao_Entrega, Conhecimentos.DT_Entrega, Conhecimentos.HR_Entrega, Conhecimentos.NM_Pessoa_Entrega "  +
              " FROM   Conhecimentos, Conhecimentos_Notas_Fiscais " +
              " WHERE Conhecimentos.oid_Conhecimento = Conhecimentos_Notas_Fiscais.oid_Conhecimento " +
              " AND   Conhecimentos_Notas_Fiscais.oid_Nota_Fiscal = '" + res.getString("OID_Nota_Fiscal") + "'" ;

          // System.out.println(sql);

        ResultSet res2 = this.executasql.executarConsulta(sql);
        while (res2.next()){

          // System.out.println("DT_Previsao_Entrega->" + res2.getString ("DT_Previsao_Entrega") );
          // System.out.println("DT_Entrega->" + res2.getString ("DT_Entrega") );

          if (res2.getString ("NM_Pessoa_Entrega") != null && !res2.getString ("NM_Pessoa_Entrega").equals("null")) {
            edVolta.setNM_Pessoa_Entrega(res2.getString ("NM_Pessoa_Entrega"));
          }

          int nr_Dias_Entrega = 0;
          int nr_Dias_Previsto_Entrega = 0;
          int nr_Dias_Realizado_Entrega = 0;
          edVolta.setNR_Dias_Previsto_Entrega (" ");
          edVolta.setNR_Dias_Entrega (" ");
          edVolta.setNR_Dias_Realizado_Entrega ("Pend.");

          if (res2.getString ("DT_Previsao_Entrega") == null || res2.getString ("DT_Previsao_Entrega").length () < 5) {
            edVolta.setDT_Previsao_Entrega (" ");
            edVolta.setHR_Previsao_Entrega (" ");
          }
          else {
            edVolta.setDT_Previsao_Entrega (res2.getString ("DT_Previsao_Entrega"));
            DataFormatada.setDT_FormataData (edVolta.getDT_Previsao_Entrega ());
            edVolta.setDT_Previsao_Entrega (DataFormatada.getDT_FormataData ());
            edVolta.setHR_Previsao_Entrega (res2.getString ("HR_Previsao_Entrega"));

            try {
              nr_Dias_Previsto_Entrega = Data.diferencaDias (DataFormatada.getDT_FormataData (res2.getString ("DT_Previsao_Entrega")) , DataFormatada.getDT_FormataData (res2.getString ("DT_Emissao")));
              edVolta.setNR_Dias_Previsto_Entrega (String.valueOf (nr_Dias_Previsto_Entrega));
            }
            catch (Exception e) {
              throw new Excecoes (e.getClass () + ": " + e.getMessage () , e , this.getClass ().getName () , "calc prazo");
            }

            if (res2.getString ("DT_Entrega") == null || res2.getString ("DT_Entrega").length () < 5) {
              if (Data.comparaData (DataFormatada.getDT_FormataData (res2.getString ("DT_Previsao_Entrega")) , Data.getDataDMY ())) {
                edVolta.setDT_Entrega (" ");
                edVolta.setHR_Entrega (" ");
              }
              else {
                edVolta.setDT_Entrega (DataFormatada.getDT_FormataData (res2.getString ("DT_Previsao_Entrega")));
                edVolta.setHR_Entrega (" ");
              }
            }
            else {
              edVolta.setDT_Entrega (res2.getString ("DT_Entrega"));
              DataFormatada.setDT_FormataData (edVolta.getDT_Entrega ());
              edVolta.setDT_Entrega (DataFormatada.getDT_FormataData ());
              edVolta.setHR_Entrega (res2.getString ("HR_Entrega"));
            }
            if (edVolta.getDT_Entrega () != null && edVolta.getDT_Entrega ().length () > 4) {
              try {
                nr_Dias_Entrega = Data.diferencaDias (edVolta.getDT_Entrega () , DataFormatada.getDT_FormataData (res2.getString ("DT_Emissao")));
                edVolta.setNR_Dias_Entrega (String.valueOf (nr_Dias_Entrega));
                nr_Dias_Realizado_Entrega = nr_Dias_Entrega - nr_Dias_Previsto_Entrega;
                if (nr_Dias_Realizado_Entrega > 0) {
                  edVolta.setNR_Dias_Realizado_Entrega (String.valueOf (nr_Dias_Realizado_Entrega));
                }
                else {
                  edVolta.setNR_Dias_Realizado_Entrega ("OK");
                }
              }
              catch (Exception e) {
                throw new Excecoes (e.getClass () + ": " + e.getMessage () , e , this.getClass ().getName () , "calc prazo");
              }
            }
          }
          // System.out.println("edVolta->" + edVolta.getDT_Entrega() );

        }
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


  public byte[] imprime_Minuta(Nota_FiscalED ed)throws Excecoes{

    String sql = null;
    byte[] b = null;

    try{

      sql = " select  *, Pessoa_Remetente.oid_Pessoa as oid_Pessoa_Remetente, Pessoa_Destinatario.oid_Pessoa as oid_Pessoa_Destinatario , Modal.cd_modal, Modal.NM_Modal, Produtos.NM_Produto from Notas_Fiscais, Pessoas Pessoa_Remetente, Pessoas Pessoa_Destinatario, Modal, Produtos ";
      sql += " where Notas_Fiscais.oid_Pessoa = Pessoa_Remetente.oid_Pessoa ";
      sql += " AND Notas_Fiscais.oid_Modal = Modal.oid_Modal ";
      sql += " AND Notas_Fiscais.oid_Produto = Produtos.oid_produto ";
      sql += " AND Notas_Fiscais.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa ";

      if (String.valueOf(ed.getOID_Nota_Fiscal()) != null &&
        !String.valueOf(ed.getOID_Nota_Fiscal()).equals("0")  &&
        !String.valueOf(ed.getOID_Nota_Fiscal()).equals("null")){
        sql += " and Notas_Fiscais.OID_Nota_Fiscal = '" + ed.getOID_Nota_Fiscal() + "'";
      }

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql.toString());

      MinutaRL MinutaRL = new MinutaRL();
      b =  MinutaRL.imprime_Minuta(res);

    }

    catch (Excecoes e){
      throw e;
    }
    catch(Exception exc){
      Excecoes exce = new Excecoes();
      exce.setExc(exc);
      exce.setMensagem("Erro ao Imprimir Minuta");
      exce.setClasse(this.getClass().getName());
      exce.setMetodo("imprime_Nota_Fiscal_MT_Contrato(Nota_FiscalED ed)");
    }
    return b;
  }


}
