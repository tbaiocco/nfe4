package com.master.bd;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.io.File;
import java.io.LineNumberReader;

import com.master.ed.*;
import com.master.rl.*;
import com.master.root.*;
import com.master.util.*;
import com.master.util.bd.*;
import com.master.util.ed.*;
import com.master.bd.Plano_ViagemBD;

public class Localizacao_VeiculoBD extends Transacao{
  private ExecutaSQL executasql;
  FormataDataBean DataFormatada = new FormataDataBean();
  CidadeBean CidadeBean = new CidadeBean();
  SeparaEndereco SeparaEndereco = new SeparaEndereco();

  Plano_ViagemBD Plano_ViagemBD = null;

  Utilitaria util = new Utilitaria(executasql);

  public Localizacao_VeiculoBD(ExecutaSQL sql) {
      this.executasql = sql;
      util = new Utilitaria(this.executasql);
  }

  FormataDataBean dataFormatada = new FormataDataBean ();
  SimpleDateFormat formatter = new SimpleDateFormat ("dd/MM/yyyy");
  Parametro_FixoED parametro_FixoED = new Parametro_FixoED ();
  UnidadeBean Unidades = new UnidadeBean ();

  String Nr_Sort = "";
  String data = "";
  String NM_Localizacao1 = "";
  String NM_Localizacao2 = "";
  String NM_Localizacao3 = "";
  String data1 = "";
  String data2 = "";

  String NM_Origem = "";
  String NM_Destino = "";

  long OID_Coleta = 999999;
  long OID_Tipo_Localizacao = 999999;
  long OID_Ordem_Servico = 999999;

  long OID_Cidade_Origem = 999999;
  long OID_Cidade_Destino = 999999;

  long OID_Unidade_Origem = 999999;
  long OID_Unidade_Destino = 999999;

  String OID_Veiculo = "";

  String DM_Analitico_Sintetico = "S";
  String DM_Analise = "";
  String OID_Localizacao_Veiculo = "";

  int lidos = 0;
  public Localizacao_VeiculoED getByRecord(Localizacao_VeiculoED ed)throws Excecoes{

    String sql = null;

    Localizacao_VeiculoED edVolta = new Localizacao_VeiculoED();

    try{

     sql  = " select * FROM Localizacoes_Veiculos ";
     sql+="  where  Localizacoes_Veiculos.oid_Localizacao_Veiculo = '" + ed.getOID_Localizacao_Veiculo() + "'" ;

     // System.err.println("achou sql  " + sql);

     ResultSet res = null;


      res = this.executasql.executarConsulta(sql);
      while (res.next()){
        edVolta.setOID_Localizacao_Veiculo(res.getString("OID_Localizacao_Veiculo"));
      }

     // System.out.println(" leu edVolta.setOID_Localizacao_Veiculo ->> " + edVolta.getOID_Localizacao_Veiculo());

    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao recuperar registro");
      excecoes.setMetodo("getByRecord(ContaED)");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return edVolta;
  }

  public ArrayList lista(Localizacao_VeiculoED ed)
  throws Excecoes {


          ArrayList list = new ArrayList();
          int nr_ordem=0, nr_total=0;

          String sql =
              " SELECT * " +
              " FROM Localizacoes_Veiculos, Veiculos " +
              " WHERE Localizacoes_Veiculos.oid_Veiculo = Veiculos.oid_Veiculo " +
              " ORDER BY Veiculos.NR_Frota";

              // System.out.println(sql);
              ResultSet res = executasql.executarConsulta(sql);
              ResultSet res2 = null;
              try {
                while (res.next ()) {
                  Localizacao_VeiculoED movVeic = new Localizacao_VeiculoED ();
                  movVeic.setNM_Modelo_Veiculo (" ");
                  movVeic.setNM_Modelo_Carreta (" ");
                  movVeic.setDM_Procedencia("-");
                  movVeic.setNM_Ultima_Ocorrencia (" ");
                  movVeic.setDM_Procedencia_Carreta("-");
                  movVeic.setDT_Previsao_Chegada(" ");
                  movVeic.setHR_Previsao_Chegada(" ");
                  movVeic.setDM_Expresso("----");
                  movVeic.setOID_Veiculo ("-");
                  movVeic.setOID_Carreta ("-");
                  movVeic.setNM_Motorista ("");
                  movVeic.setDM_Tipo_Monitoramento("-");
                  movVeic.setOID_Motorista(res.getString ("OID_Motorista"));
                  movVeic.setOID_Veiculo (res.getString ("OID_Veiculo"));
                  movVeic.setNR_Frota(res.getString ("NR_Frota"));
                  // System.out.println("1");

                  movVeic.setOID_Veiculo (res.getString ("OID_Veiculo"));
                  res2 = executasql.executarConsulta (
                      " SELECT CD_Modelo_Veiculo, Veiculos.DM_Situacao, Veiculos.NR_Frota " +
                      " FROM Modelos_Veiculos, Veiculos " +
                      " WHERE Modelos_Veiculos.oid_Modelo_Veiculo = Veiculos.oid_Modelo_Veiculo " +
                      " AND Veiculos.oid_Veiculo = '" + res.getString ("OID_Veiculo") + "'");
                  while (res2.next ()) {
                    movVeic.setNM_Modelo_Veiculo (res2.getString ("CD_Modelo_Veiculo"));
                    movVeic.setNM_Situacao_Cavalo (res2.getString ("DM_Situacao"));
                  }

                  res2 = executasql.executarConsulta (
                      " SELECT DM_Monitorado " +
                      " FROM Veiculos " +
                      " WHERE Veiculos.oid_Veiculo = '" + res.getString ("OID_Veiculo") + "'");
                  while (res2.next ()) {
                    movVeic.setDM_Tipo_Monitoramento (res2.getString ("DM_Monitorado"));
                  }

                  movVeic.setDM_Procedencia ("T");
                  res2 = executasql.executarConsulta (
                      " SELECT DM_Procedencia " +
                      " FROM Complementos_Veiculos " +
                      " WHERE Complementos_Veiculos.oid_Veiculo = '" + res.getString ("OID_Veiculo") + "'");
                  while (res2.next ()) {
                    movVeic.setDM_Procedencia (res2.getString ("DM_Procedencia"));
                  }


                  sql = " SELECT  Ocorrencias_Veiculos.dt_ocorrencia_veiculo, Ocorrencias_Veiculos.hr_ocorrencia_veiculo, Ocorrencias_Veiculos.dm_situacao, Ocorrencias_Veiculos.tx_descricao " +
                      " FROM Ocorrencias_Veiculos " +
                      " WHERE Ocorrencias_Veiculos.oid_Veiculo ='" + res.getString ("oid_Veiculo") + "'" +
                      " AND   Ocorrencias_Veiculos.dt_ocorrencia_veiculo = (select max (dt_ocorrencia_veiculo) FROM Ocorrencias_Veiculos WHERE Ocorrencias_Veiculos.dt_ocorrencia_veiculo > '01/02/2006' and oid_Veiculo ='" +
                      res.getString ("oid_Veiculo") + "') ";

                  // System.out.println ("OCorrencia->>" + sql);

                  res2 = this.executasql.executarConsulta (sql.toString ());
                  while (res2.next ()) {
                    movVeic.setNM_Ultima_Ocorrencia (res2.getString ("dm_situacao") + "-" + res2.getString ("tx_descricao"));
                  }



                  sql=" SELECT * FROM Coletas, Pessoas, Cidades " +
                      " WHERE Coletas.oid_Pessoa = Pessoas.oid_Pessoa " +
                      " AND Pessoas.oid_Cidade = Cidades.oid_Cidade " +
                      " AND Coletas.oid_Veiculo ='" + res.getString ("oid_Veiculo") + "'" +
                      " AND Coletas.DM_Situacao <>'C' " +
                      " AND Coletas.DT_Coleta = (select max (DT_Coleta) FROM Coletas WHERE Coletas.DT_Coleta > '01/02/2006' and oid_Veiculo ='" + res.getString ("oid_Veiculo") + "') ";
                  res2 = this.executasql.executarConsulta (sql.toString ());
                  while (res2.next ()) {
                    movVeic.setOID_Coleta (res2.getLong ("oid_Coleta"));
                    movVeic.setOID_Motorista (res2.getString ("oid_Motorista"));
                    movVeic.setOID_Carreta (res2.getString ("oid_Carreta"));

                    DataFormatada.setDT_FormataData (res2.getString ("DT_Coleta"));
                    movVeic.setDT_Coleta (DataFormatada.getDT_FormataData ());
                    movVeic.setNM_Status ("EM COLETA");
                    movVeic.setDM_Situacao ("C");

                    movVeic.setNM_Local_Coleta ( (res2.getString ("NM_Razao_Social") + "                      ").substring (0 , 8) + "/" + (res2.getString ("NM_cidade") + "                      ").substring (0 , 8));
                    movVeic.setNM_Destino ( (res2.getString ("NM_Destinatario") + "                      ").substring (0 , 8) + "/" + (res2.getString ("NM_Cidade_Destinatario") + "                           ").substring (0 , 10));
                    DataFormatada.setDT_FormataData (res2.getString ("DT_Previsao_Entrega"));
                    movVeic.setDT_Previsao_Chegada (DataFormatada.getDT_FormataData ());
                    movVeic.setHR_Previsao_Chegada (res2.getString ("HR_Previsao_Entrega"));
                    movVeic.setDM_Expresso ("Col.");
                  }
                  
                  movVeic.setDT_Embarque(" ");
                  movVeic.setNR_Manifesto (" ");


                  movVeic.setNM_Motorista ("");
                  if (util.doValida (movVeic.getOID_Motorista())&&movVeic.getOID_Motorista().length()>4 ) {
                    res2 = executasql.executarConsulta("Select NM_Razao_Social as NM_Motorista FROM Pessoas WHERE oid_Pessoa = '" +movVeic.getOID_Motorista()+ "'" );
                    while (res2.next ()) {
                      movVeic.setNM_Motorista (res2.getString ("NM_Motorista"));
                    }

                  }

                  movVeic.setNM_Status (res.getString ("NM_Status"));
                  movVeic.setNM_Localizacao1 (res.getString ("NM_Localizacao1"));
                  movVeic.setNM_Localizacao2 (res.getString ("NM_Localizacao2"));
                  movVeic.setNM_Localizacao3 (res.getString ("NM_Localizacao3"));
                  DataFormatada.setDT_FormataData(res.getString ("DT_Localizacao1"));
                  movVeic.setDT_Localizacao1(DataFormatada.getDT_FormataData());
                  DataFormatada.setDT_FormataData(res.getString ("DT_Localizacao2"));
                  movVeic.setDT_Localizacao2(DataFormatada.getDT_FormataData());
                  DataFormatada.setDT_FormataData(res.getString ("DT_Localizacao3"));
                  movVeic.setDT_Localizacao3(DataFormatada.getDT_FormataData());
                  movVeic.setHR_Localizacao1 (res.getString ("HR_Localizacao1"));
                  movVeic.setHR_Localizacao2 (res.getString ("HR_Localizacao2"));
                  movVeic.setHR_Localizacao3 (res.getString ("HR_Localizacao3"));

                  nr_total++;
                  movVeic.setNR_Total (nr_total);
                  nr_ordem++;
                  movVeic.setNR_Ordem (nr_ordem);
                  list.add (movVeic);

                }
                // System.out.println("999");

                return list;
              }
              catch (SQLException e) {
                throw new Excecoes (e.getMessage () , e , this.getClass ().getName () ,
                                    "lista(HttpServletRequest request)");
              }
  }



  public Localizacao_VeiculoED altera(Localizacao_VeiculoED ed)throws Excecoes{

    String sql = null;

    Localizacao_VeiculoED edVolta = new Localizacao_VeiculoED();

    try{

     // System.out.println(" alterando OID_Localizacao_Veiculo ->> " + ed.getOID_Localizacao_Veiculo());


//      sql = " update Localizacoes_Veiculos set = " + ed.getVL_Vencido() ;

      sql+= " Where OID_Localizacao_Veiculo = " + ed.getOID_Localizacao_Veiculo();

      // System.out.println("vai alterar" + sql);

      int i = executasql.executarUpdate(sql);

    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao recuperar registro");
      excecoes.setMetodo("getByRecord(ContaED)");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return edVolta;
  }

  public Localizacao_VeiculoED atualiza(Localizacao_VeiculoED ed)throws Excecoes{

    String sql = null;
    ResultSet res = null;
    ResultSet res2 = null;
    try{

      Nr_Sort = String.valueOf(System.currentTimeMillis()).toString();

      // System.out.println(" atualizaVeiculo ->>>>>>>>>>>>>>>>>>>" );


      sql= " DELETE FROM Localizacoes_Veiculos ";
      executasql.executarUpdate(sql);

      // System.out.println(" atualizaVeiculo 2" );

      sql = " SELECT Veiculos.oid_Veiculo " +
            " ,Veiculos.DM_Situacao as DM_Situacao_Veiculo" +
            " ,Modelos_Veiculos.nm_modelo_veiculo " +
            " ,Tipos_Veiculos.DM_Tipo_Implemento " +
            " FROM Veiculos , Modelos_Veiculos, Tipos_Veiculos " +
            " WHERE Veiculos.oid_Modelo_Veiculo        = Modelos_Veiculos.oid_Modelo_Veiculo " +
            "   AND Modelos_Veiculos.oid_Tipo_Veiculo  = Tipos_Veiculos.oid_Tipo_Veiculo " +
            "   AND (Veiculos.DM_Monitorado = 'P' OR Veiculos.DM_Monitorado = 'T' )" +
            "   ORDER BY  Tipos_Veiculos.DM_Tipo_Implemento, Veiculos.oid_Veiculo ";
        

      // System.out.println(" sql " + sql);

      res = this.executasql.executarConsulta(sql.toString());
      while (res.next()){

        Localizacao_VeiculoED edMov = new Localizacao_VeiculoED ();
        edMov.setOID_Veiculo (res.getString ("oid_Veiculo"));

        edMov.setDT_Localizacao (ed.getDT_Localizacao ());
        edMov.setHR_Localizacao (Data.getHoraHM ());
        edMov.setDM_Situacao ("P");
        edMov.setTX_Observacao ("");
        edMov.setNM_Localizacao1 (" ");
        edMov.setNM_Localizacao2 (" ");
        edMov.setNM_Localizacao3 (" ");
        edMov.setDT_Localizacao1 ("01/01/2001");
        edMov.setDT_Localizacao2 ("01/01/2001");
        edMov.setDT_Localizacao3 ("01/01/2001");
        edMov.setOID_Manifesto (" ");
        edMov.setDT_Embarque ("01/01/2001");
        edMov.setOID_Coleta (0);
        edMov.setQT_Entregas (0);
        edMov.setOID_Motorista ("");
        edMov.setDT_Coleta ("01/01/2001");
        edMov.setNM_Status ("---");

        inclui (edMov);

      }
      this.organizaMapa(ed);
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro atualizar Veiculos");
      excecoes.setMetodo("atualizaVeiculo()");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return ed;
  }

  public Localizacao_VeiculoED organizaMapa(Localizacao_VeiculoED ed)throws Excecoes{

    String sql = null;
    ResultSet res = null;

    try{

      // System.out.println("verifica carretas--");

      sql = " SELECT oid_Localizacao_Veiculo, oid_Carreta from Localizacoes_Veiculos where OID_Carreta <>'-' Order BY oid_carreta, dt_embarque DESC" ;
      String oid_Carreta="";
      res = this.executasql.executarConsulta(sql.toString());

      while (res.next()){
        // System.out.println("->> " + res.getString("oid_Carreta"));

        if (oid_Carreta.equals(res.getString("oid_Carreta"))) {
          sql = "Update Localizacoes_Veiculos SET oid_Carreta='-' WHERE OID_Localizacao_Veiculo = ";
          sql += "(" + res.getString("oid_Localizacao_Veiculo") + ")";

          // System.out.println("Apagou " + sql);

          int i = executasql.executarUpdate(sql);
        }
        oid_Carreta=res.getString("oid_Carreta");

      }
      sql = " SELECT oid_Localizacao_Veiculo, oid_Motorista from Localizacoes_Veiculos where oid_Motorista <>''  Order BY oid_Motorista, dt_embarque DESC" ;
      String oid_Motorista="";
      res = this.executasql.executarConsulta(sql.toString());

      while (res.next()){
        // System.out.println("->> " + res.getString("oid_Motorista"));

        if (oid_Motorista.equals(res.getString("oid_Motorista"))) {
          sql = "Update Localizacoes_Veiculos SET oid_Motorista='' WHERE OID_Localizacao_Veiculo = ";
          sql += "(" + res.getString("oid_Localizacao_Veiculo") + ")";

          // System.out.println("Apagou " + sql);

          int i = executasql.executarUpdate(sql);
        }
        oid_Motorista=res.getString("oid_Motorista");

      }


    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro organizaCarretas");
      excecoes.setMetodo("organizaCarretas()");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return ed;
  }



  public byte[] imprime_Localizacao_Veiculo(Localizacao_VeiculoED ed)throws Excecoes{

    byte[] b = null;
    try{
      ArrayList lista = new ArrayList();
      lista = this.lista(ed);

      // System.out.println("Lista =>>>" + lista.size ());

      Localizacao_VeiculoRL conRL = new Localizacao_VeiculoRL ();
      b = conRL.geraAnalise_Localizacao_Veiculo (lista , ed ,  executasql);

    }

    catch (Excecoes e){
      throw e;
    }
    catch(Exception exc){
      Excecoes exce = new Excecoes();
      exce.setExc(exc);
      exce.setMensagem("Erro no método listar");
      exce.setClasse(this.getClass().getName());
      exce.setMetodo("geraRelatorio(Localizacao_VeiculoED ed)");
    }
    return b;
  }


  public void inclui(Localizacao_VeiculoED ed) throws Excecoes{

    // System.out.println(" incluindo   " + ed.getOID_Veiculo());

    if (ed.getOID_Veiculo () == null || ed.getOID_Veiculo ().equals ("") || ed.getOID_Veiculo ().equals ("null") || ed.getOID_Veiculo ().length () < 5) {
      ed.setOID_Veiculo ("-");
    }
    if (ed.getOID_Carreta () == null || ed.getOID_Carreta ().equals ("") || ed.getOID_Carreta ().equals ("null") || ed.getOID_Carreta ().length () < 5) {
      ed.setOID_Carreta ("-");
    }


    String sql = null;

    try{

      String oid= String.valueOf(System.currentTimeMillis()).toString();


      sql = " insert into Localizacoes_Veiculos ( " +
          " OID_Localizacao_Veiculo, "+
          " NM_Status, "+
          " NM_Localizacao1, "+
          " NM_Localizacao2, "+
          " NM_Localizacao3, "+
          " DT_Localizacao1, "+
          " DT_Localizacao2, "+
          " DT_Localizacao3, "+
          " HR_Localizacao1, "+
          " HR_Localizacao2, "+
          " HR_Localizacao3, "+
          " NM_Ultima_Ocorrencia, "+
          " OID_Veiculo, "+
          " OID_Motorista, "+
          " DT_Coleta, "+
          " DT_Embarque, "+
          " OID_Carreta, "+
          " OID_Manifesto, "+
          " OID_Coleta, "+
          " OID_Ordem_Servico, "+
          " QT_Entregas, "+
          " NR_Ajudante, "+
          " NR_KIT "+
          " ) values " +
          "('" + oid + "'" +
           ", '" + ed.getNM_Status() + "'" +
           ", '" + ed.getNM_Localizacao1() + "'" +
           ", '" + ed.getNM_Localizacao2() + "'" +
           ", '" + ed.getNM_Localizacao3() + "'" +
           ", '" + ed.getDT_Localizacao1() + "'" +
           ", '" + ed.getDT_Localizacao2() + "'" +
           ", '" + ed.getDT_Localizacao3() + "'" +
           ", '" + ed.getHR_Localizacao1() + "'" +
           ", '" + ed.getHR_Localizacao2() + "'" +
           ", '" + ed.getHR_Localizacao3() + "'" +
           ", '" + ed.getNM_Ultima_Ocorrencia() + "'" +
           ", '" + ed.getOID_Veiculo() + "'" +
           ", '" + ed.getOID_Motorista() + "'" +
           ", '" + ed.getDT_Coleta() + "'" +
           ", '" + ed.getDT_Embarque() + "'" +
           ", '" + ed.getOID_Carreta() + "'" +
           ", '" + ed.getOID_Manifesto() + "'" +
           ",  " + ed.getOID_Coleta() +
           ",  " + ed.getOID_Ordem_Servico() +
           ",  " + ed.getQT_Entregas() + 
           ",  " + ed.getNR_Ajudante() +  
           ",  " + ed.getNR_KIT() +  ")";

       // System.out.println(" inclui II ");

// System.out.println("inclui" + sql);

      int i = executasql.executarUpdate(sql);
// System.out.println("ok");
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setMensagem("Erro de Localizacoes_Veiculos de dados");
      excecoes.setExc(exc);
    }
  }

  public void deleta(Localizacao_VeiculoED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from Localizacoes_Veiculos WHERE OID_Localizacao_Veiculo = ";
      sql += "(" + ed.getOID_Localizacao_Veiculo() + ")";

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao excluir ");
      excecoes.setMetodo("deletar");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public Localizacao_VeiculoED importaPosicao (Localizacao_VeiculoED ed) throws Excecoes {

    // System.out.println ("importaPosicao BD Duplic 1");
    String NM_Registro = "";
    String Tipo_Registro = "";
    String CD_Banco = "";

    // System.out.println ("importaPosicao BD 1 a");

    try {

      ManipulaArquivo man = new ManipulaArquivo ("");
      // System.out.println ("importaPosicao BD 3");

      String nomeArquivo = ed.getNM_Arquivo (); //ed.getNM_Arquivo().replaceAll("\\", "/");
      // System.out.println ("Arquivo: " + nomeArquivo);

      if (!new File (nomeArquivo).exists ()) {
        throw new Excecoes ("Arquivo [" + nomeArquivo + "] não encontrado!" , getClass ().getName () , "importaRetornoCobranca(EDI_BancoED ed)");
        //throw new Mensagens("Arquivo [" + nomeArquivo + "] não encontrado!");
      }

      LineNumberReader line = man.leLinha (nomeArquivo);

      // System.out.println ("importacao BD 4 ->> " + nomeArquivo);

      int linha = 0;
      int ler = 0;
      String sql="";
      
      if (line.ready ()) {

        // System.out.println ("importacao BD 4 b ");

        // System.out.println ("Line Ready inicio em " + linha);
        while ( ( (NM_Registro = line.readLine ()) != null) && ler < 9000) {

          // System.out.println ("------IMPORTANDO POSICAO -------- ");

          linha++;
          line.setLineNumber (linha);
          ler++;
          Localizacao_VeiculoED mov_veicED = new Localizacao_VeiculoED ();
          mov_veicED.setOid_Seguradora (ed.getOid_Seguradora ());
          mov_veicED.setNM_Arquivo (nomeArquivo);

          if (NM_Registro.length () > 30) {
            //// System.out.println (linha + " ->> " + NM_Registro); 
            
            String semposicao = NM_Registro.substring(15,18) ;
            //// System.out.println (semposicao + " ->> " + semposicao); 
            if (!"SEM".equals(semposicao)){

              String oid_veiculo = NM_Registro.substring (1 , 4) + NM_Registro.substring (5 , 9);
              //// System.out.println (oid_veiculo + " ->> " + oid_veiculo);

              String data = NM_Registro.substring (12 , 18) + "20" + NM_Registro.substring (18 , 20);
              
              //// System.out.println (data + " ->> " + data);

              String hora = NM_Registro.substring (21 , 26);
              //// System.out.println (hora + " ->> " + hora);

              String posicao = "***";
              if (!"06".equals(NM_Registro.substring (18 , 20))){
                data=Data.getDataDMY();
                hora="--.--";
                posicao="  ->>>>>  NAO ATUALIZADO.  ";
              }else {
                int p = 29 + 16;
                String caracter = "";

                while (p < 130) {
                  if (NM_Registro.length () > p) {
                    caracter = NM_Registro.substring (p , p + 1);

                    //// System.out.println ("pos" + " ->> " + p + " ->> " + caracter);

                    if (",".equals (caracter) && p > 35) {
                      break;
                    }
                    posicao += caracter;
                  }
                  p++;
                }
                // System.out.println (posicao + " ->> " + posicao);
                
              }


              posicao = posicao.substring (0 , posicao.length () - 1);
              posicao = SeparaEndereco.corrigeString (posicao);

              //// System.out.println (posicao + " ->> " + posicao);

              if (posicao.length () > 10) {
                sql = " select oid_Localizacao_Veiculo FROM Localizacoes_Veiculos ";
                sql += "  where  Localizacoes_Veiculos.oid_Veiculo = '" + oid_veiculo + "'";

                //// System.err.println ("achou sql  " + sql);
                ResultSet res = null;

                res = this.executasql.executarConsulta (sql);
                while (res.next ()) {
                  sql = " UPDATE Localizacoes_Veiculos SET " +
                      "        dt_localizacao1='" + data + "'" +
                      "       ,hr_localizacao1='" + hora + "'" +
                      "       ,nm_localizacao1='" + posicao + "'" +
                      "   WHERE oid_Localizacao_Veiculo='" + res.getString("oid_Localizacao_Veiculo") + "'";
                  //// System.err.println (" sql =>> " + sql);
                  int i = executasql.executarUpdate (sql);

                }

              }

            }
            
            
          }

          line.setLineNumber (linha);

        }
        line.close ();
      }
    }
    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "importaPosicao(EDI_BancoED ed)");
    }
    return ed;
  }


  private String situacao_Veiculo (String DM_Situacao) throws Excecoes{
  
    String nm_situacao = "";
    if ("I".equals(DM_Situacao)) nm_situacao="INICIO DE VIAGEM";
    if ("P".equals(DM_Situacao)) nm_situacao="PERNOITE";
    if ("B".equals(DM_Situacao)) nm_situacao="ALIMENTACAO";
    if ("A".equals(DM_Situacao)) nm_situacao="ABASTECIMENTO";
    if ("M".equals(DM_Situacao)) nm_situacao="MANUTENCAO";
    if ("V".equals(DM_Situacao)) nm_situacao="EM VIAGEM";
    if ("F".equals(DM_Situacao)) nm_situacao="FISCALIZACAO";
    if ("R".equals(DM_Situacao)) nm_situacao="ADUANA";
    if ("C".equals(DM_Situacao)) nm_situacao="CARREGAMENTO";
    if ("D".equals(DM_Situacao)) nm_situacao="DESCARGA";
    if ("X".equals(DM_Situacao)) nm_situacao="DISPONIVEL";
    if ("N".equals(DM_Situacao)) nm_situacao="NAO DISPONIVEL";
    if ("O".equals(DM_Situacao)) nm_situacao="OUTROS";

        return nm_situacao;
  }
}
