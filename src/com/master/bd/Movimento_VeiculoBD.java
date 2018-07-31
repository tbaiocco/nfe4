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

public class Movimento_VeiculoBD extends Transacao{
  private ExecutaSQL executasql;
  FormataDataBean DataFormatada = new FormataDataBean();
  CidadeBean CidadeBean = new CidadeBean();
  SeparaEndereco SeparaEndereco = new SeparaEndereco();

  Plano_ViagemBD Plano_ViagemBD = null;

  Utilitaria util = new Utilitaria(executasql);

  public Movimento_VeiculoBD(ExecutaSQL sql) {
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
  long OID_Tipo_Movimento = 999999;
  long OID_Ordem_Servico = 999999;

  long OID_Cidade_Origem = 999999;
  long OID_Cidade_Destino = 999999;

  long OID_Unidade_Origem = 999999;
  long OID_Unidade_Destino = 999999;

  String OID_Veiculo = "";

  String DM_Analitico_Sintetico = "S";
  String DM_Analise = "";
  String OID_Movimento_Veiculo = "";

  int lidos = 0;
  public Movimento_VeiculoED getByRecord(Movimento_VeiculoED ed)throws Excecoes{

    String sql = null;

    Movimento_VeiculoED edVolta = new Movimento_VeiculoED();

    try{

     sql  = " select * FROM Movimentos_Veiculos ";
     sql+="  where  Movimentos_Veiculos.oid_Movimento_Veiculo = '" + ed.getOID_Movimento_Veiculo() + "'" ;

     // System.err.println("achou sql  " + sql);

     ResultSet res = null;


      res = this.executasql.executarConsulta(sql);
      while (res.next()){
        edVolta.setOID_Movimento_Veiculo(res.getString("OID_Movimento_Veiculo"));
      }

     // System.out.println(" leu edVolta.setOID_Movimento_Veiculo ->> " + edVolta.getOID_Movimento_Veiculo());

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

  public ArrayList lista(Movimento_VeiculoED ed)
  throws Excecoes {


          ArrayList list = new ArrayList();
          int nr_ordem=0, nr_total=0;

          String sql =
              " SELECT * " +
              " FROM Movimentos_Veiculos " +
              " WHERE oid_Veiculo <> oid_Carreta " ;

              if (util.doValida (ed.getOID_Veiculo ())) {
                sql += "   AND Movimentos_Veiculos.OID_Veiculo = '" + ed.getOID_Veiculo () + "' ";
              }
              sql += "ORDER BY Movimentos_Veiculos.NM_Status, Movimentos_Veiculos.oid_Veiculo , Movimentos_Veiculos.oid_Carreta";

              // System.out.println(sql);
              ResultSet res = executasql.executarConsulta(sql);
              ResultSet res2 = null;
              try {
                while (res.next ()) {
                  Movimento_VeiculoED movVeic = new Movimento_VeiculoED ();
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
                  // System.out.println("1");

                  if (!"-".equals(res.getString ("OID_Veiculo"))) {
                    movVeic.setOID_Veiculo (res.getString ("OID_Veiculo"));
                    res2 = executasql.executarConsulta (
                        " SELECT CD_Modelo_Veiculo, Veiculos.DM_Situacao, Veiculos.NR_Frota " +
                        " FROM Modelos_Veiculos, Veiculos "+
                        " WHERE Modelos_Veiculos.oid_Modelo_Veiculo = Veiculos.oid_Modelo_Veiculo " +
                        " AND Veiculos.oid_Veiculo = '" + res.getString ("OID_Veiculo") + "'");
                    while (res2.next ()) {
                      movVeic.setNM_Modelo_Veiculo (res2.getString ("CD_Modelo_Veiculo"));
                      movVeic.setNM_Situacao_Cavalo(res2.getString ("DM_Situacao"));
                      movVeic.setNR_Frota(res2.getString ("NR_Frota"));
                    }
                    res2 = executasql.executarConsulta (
                        " SELECT DM_Monitorado " +
                        " FROM Veiculos "+
                        " WHERE Veiculos.oid_Veiculo = '" + res.getString ("OID_Veiculo") + "'");
                    while (res2.next ()) {
                      movVeic.setDM_Tipo_Monitoramento (res2.getString ("DM_Monitorado"));
                    }

                    movVeic.setDM_Procedencia("T");
                    res2 = executasql.executarConsulta (
                        " SELECT DM_Procedencia " +
                        " FROM Complementos_Veiculos "+
                        " WHERE Complementos_Veiculos.oid_Veiculo = '" + res.getString ("OID_Veiculo") + "'");
                    while (res2.next ()) {
                      movVeic.setDM_Procedencia(res2.getString ("DM_Procedencia"));
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

                  }

                  if (!"-".equals(res.getString ("OID_Carreta"))) {
                    movVeic.setOID_Carreta (res.getString ("OID_Carreta"));
                    res2 = executasql.executarConsulta (
                        " SELECT CD_Modelo_Veiculo " +
                        " FROM Modelos_Veiculos, Veiculos "+
                        " WHERE Modelos_Veiculos.oid_Modelo_Veiculo = Veiculos.oid_Modelo_Veiculo " +
                        " AND Veiculos.oid_Veiculo = '" + res.getString ("OID_Carreta") + "'");
                    while (res2.next ()) {
                      movVeic.setNM_Modelo_Carreta (res2.getString ("CD_Modelo_Veiculo"));
                    }

                    movVeic.setDM_Procedencia_Carreta("T");
                    res2 = executasql.executarConsulta (
                        " SELECT DM_Procedencia " +
                        " FROM Complementos_Veiculos "+
                        " WHERE Complementos_Veiculos.oid_Veiculo = '" + res.getString ("OID_Carreta") + "'");
                    while (res2.next ()) {
                      movVeic.setDM_Procedencia_Carreta(res2.getString ("DM_Procedencia"));
                    }

                  }


                  if (res.getLong ("OID_Coleta")>0) {
                    movVeic.setOID_Coleta(res.getLong ("OID_Coleta"));
                    res2 = executasql.executarConsulta("Select * FROM Coletas, Pessoas, Cidades WHERE Coletas.oid_Pessoa = Pessoas.oid_Pessoa AND Pessoas.oid_Cidade = Cidades.oid_Cidade AND oid_Coleta = " +res.getLong ("OID_Coleta") );
                    while (res2.next ()) {
                      DataFormatada.setDT_FormataData(res2.getString ("DT_Coleta"));
                      movVeic.setDT_Coleta(DataFormatada.getDT_FormataData());
                      movVeic.setNM_Local_Coleta((res2.getString ("NM_Razao_Social")+ "                      ").substring(0,8)+"/"+(res2.getString ("NM_cidade")+ "                      ").substring(0,8));
                      movVeic.setNM_Destino((res2.getString ("NM_Destinatario")+ "                      ").substring(0,8) + "/" + (res2.getString ("NM_Cidade_Destinatario")+ "                           ").substring(0,10));
                      DataFormatada.setDT_FormataData(res2.getString ("DT_Previsao_Entrega"));
                      movVeic.setDT_Previsao_Chegada(DataFormatada.getDT_FormataData());
                      movVeic.setHR_Previsao_Chegada(res2.getString ("HR_Previsao_Entrega"));
                      movVeic.setDM_Expresso("Col.");


                    }
                  }
                  movVeic.setQT_Entregas(res.getInt ("QT_Entregas"));
                  movVeic.setNR_Ajudante(res.getInt ("NR_Ajudante"));
                  movVeic.setNR_KIT(res.getInt ("NR_KIT"));
                  
                  movVeic.setDT_Embarque(" ");
                  movVeic.setNR_Manifesto (" ");

                  if (!" ".equals(res.getString ("OID_Manifesto"))) {
                    movVeic.setOID_Manifesto (res.getString ("OID_Manifesto"));
                    res2 = executasql.executarConsulta("Select * FROM Manifestos WHERE oid_Manifesto = '" +res.getString ("oid_Manifesto") + "'");
                    while (res2.next ()) {
                      //movVeic.setOID_Motorista(res2.getString ("OID_Pessoa"));

                      DataFormatada.setDT_FormataData(res2.getString ("DT_Emissao"));
                      movVeic.setDT_Embarque(DataFormatada.getDT_FormataData());
                      movVeic.setNR_Manifesto (res2.getString ("NR_Manifesto"));
                      DataFormatada.setDT_FormataData(res2.getString ("DT_Previsao_Chegada"));
                      movVeic.setDT_Previsao_Chegada(DataFormatada.getDT_FormataData());
                      movVeic.setHR_Previsao_Chegada(res2.getString ("HR_Previsao_Chegada"));
                      movVeic.setDM_Expresso("Normal");
                      
                      if ("V".equals(res2.getString("DM_Expresso"))){
                        movVeic.setDM_Expresso("Vazio");
                      }
                      if ("S".equals(res2.getString("DM_Expresso"))){
                        movVeic.setDM_Expresso("Expresso");
                      }
                      if ("T".equals(res2.getString("DM_Expresso"))){
                        movVeic.setDM_Expresso("=Fr.Terc.");
                        movVeic.setDT_Coleta(movVeic.getDT_Embarque());
                      }
                      if (movVeic.getQT_Entregas()==0){
                        sql = " SELECT * FROM Planos_Viagens " +
                            " WHERE oid_Manifesto ='" + res.getString ("OID_Manifesto") + "'" +
                            " ORDER by Planos_Viagens.Dt_Plano_Viagem desc, Planos_Viagens.Hr_Plano_Viagem desc " +
                            " LIMIT 20 ";

                        // System.out.println (" sql " + sql);
                        movVeic.setDT_Coleta (movVeic.getDT_Embarque ());
                        movVeic.setNM_Local_Coleta ("---");
                        res2 = this.executasql.executarConsulta (sql);
                        while (res2.next ()) {
                          DataFormatada.setDT_FormataData (res2.getString ("DT_Plano_Viagem"));
                          movVeic.setDT_Coleta (DataFormatada.getDT_FormataData ());
                          if (res2.getString ("NM_Origem") != null &&
                              res2.getString ("NM_Origem").length () > 4) {
                            movVeic.setNM_Local_Coleta ("*" +res2.getString ("NM_Origem"));
                          }
                          if (res2.getString ("NM_Destino") != null &&
                              res2.getString ("NM_Destino").length () > 4) {
                            movVeic.setNM_Destino ( (res2.getString ("NM_Destino")));
                          }
                        }
                      }
                      else {

                        sql =  "SELECT Manifestos.DT_Emissao, " +
                            " Pessoa_Remetente.NM_Razao_Social as NM_Pessoa_Origem, " +
                            " Pessoa_Destinatario.NM_Razao_Social as NM_Pessoa_Destino, "+
                            " Cidade_Remetente.NM_Cidade as NM_Origem, " +
                            " Cidade_Destinatario.NM_Cidade as NM_Destino "+
                            " FROM Manifestos,  Viagens, Pessoas Pessoa_Remetente, Pessoas Pessoa_Destinatario, Conhecimentos, Cidades Cidade_Remetente, Cidades Cidade_Destinatario "+
                            " WHERE  Viagens.OID_Manifesto = Manifestos.OID_Manifesto "+
                            " AND Viagens.OID_Conhecimento = Conhecimentos.OID_Conhecimento "+
                            " AND Conhecimentos.OID_Pessoa = Pessoa_Remetente.oid_Pessoa " +
                            " AND Pessoa_Remetente.OID_Cidade = Cidade_Remetente.OID_Cidade " +
                            " AND Conhecimentos.OID_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa " +
                            " AND Pessoa_Destinatario.OID_Cidade = Cidade_Destinatario.OID_Cidade " +
                            " AND Manifestos.oid_manifesto = '" +res.getString ("OID_Manifesto") + "'";
                        res2 = this.executasql.executarConsulta (sql);
                        while (res2.next ()) {
                          DataFormatada.setDT_FormataData (res2.getString ("DT_Emissao"));
                          movVeic.setDT_Coleta (DataFormatada.getDT_FormataData ());
                            movVeic.setNM_Local_Coleta ("-" + (res2.getString ("NM_Pessoa_Origem")+"                  ").subSequence(0,8) + "/" + (res2.getString ("NM_Origem")+"                  ").subSequence(0,12));
                            movVeic.setNM_Destino ("" + (res2.getString ("NM_Pessoa_Destino")+"                  ").subSequence(0,8)+ "/" + (res2.getString ("NM_Destino")+"                  ").subSequence(0,12));
                        }


                      }

                    }
                    
                    
                    
                  }

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

                  String listar="S";
                  if (!"-".equals(ed.getDM_Procedencia()) && !"-".equals(movVeic.getDM_Procedencia())){
                    listar="N";
                    if (ed.getDM_Procedencia().equals(movVeic.getDM_Procedencia()))
                       listar="S";
                    else {
                      if ("PA".equals (ed.getDM_Procedencia ())) {
                        if ("P".equals (movVeic.getDM_Procedencia ()) ||
                           ("A".equals (movVeic.getDM_Procedencia ())))
                          listar = "S";
                      }
                      if ("TA".equals (ed.getDM_Procedencia ())) {
                        if ("T".equals (movVeic.getDM_Procedencia ()) ||
                           ("A".equals (movVeic.getDM_Procedencia ())))
                          listar = "S";
                      }
                    }
                  }
                  if ("S".equals(listar)){
                    if (!"-".equals(ed.getDM_Procedencia_Carreta()) && !"-".equals(movVeic.getDM_Procedencia_Carreta())){
                      listar = "N";
                      if (ed.getDM_Procedencia_Carreta ().equals (movVeic.getDM_Procedencia_Carreta ()))
                        listar = "S";
                      else {
                        if ("PA".equals (ed.getDM_Procedencia_Carreta ())) {
                          if ("P".equals (movVeic.getDM_Procedencia_Carreta ()) ||
                              ("A".equals (movVeic.getDM_Procedencia_Carreta ())))
                            listar = "S";
                        }
                        if ("TA".equals (ed.getDM_Procedencia_Carreta ())) {
                          if ("T".equals (movVeic.getDM_Procedencia_Carreta ()) ||
                              ("A".equals (movVeic.getDM_Procedencia_Carreta ())))
                            listar = "S";
                        }
                      }
                    }
                  }

                  // System.out.println("ed.getDM_Tipo_Monitoramento()->>" + ed.getDM_Tipo_Monitoramento());
                  // System.out.println("movVeic.getDM_Tipo_Monitoramento()->>" + movVeic.getDM_Tipo_Monitoramento());
                  
                  if ("S".equals(listar)){
                  
                    if (!"-".equals (ed.getDM_Tipo_Monitoramento ())) {
                      listar = "N";
                      if (ed.getDM_Tipo_Monitoramento ().equals (movVeic.getDM_Tipo_Monitoramento ())) {
                        listar = "S";
                      }
                    }
                    
                  }
                  nr_total++;
                  movVeic.setNR_Total(nr_total);
                  
                  if ("S".equals(listar)){
                    nr_ordem++;
                    movVeic.setNR_Ordem(nr_ordem);
                    list.add (movVeic);
                  }

                }
                // System.out.println("999");

                return list;
              }
              catch (SQLException e) {
                throw new Excecoes (e.getMessage () , e , this.getClass ().getName () ,
                                    "lista(HttpServletRequest request)");
              }
  }


  public ArrayList listaOLLLD2 (Movimento_VeiculoED ed) throws Excecoes {

    ArrayList list = new ArrayList ();
    int nr_ordem = 0 , nr_total = 0;

    String sql = " SELECT Veiculos.oid_Veiculo " +
        " ,Veiculos.DM_Situacao as DM_Situacao_Veiculo" +
        " ,Veiculos.DM_Monitorado " +
        " ,Veiculos.NR_Frota " +
        " ,Veiculos.oid_pessoa  as oid_Motorista" +
        " ,Modelos_Veiculos.CD_Modelo_Veiculo " +
        " ,Modelos_Veiculos.nm_modelo_veiculo " +
        " ,Tipos_Veiculos.DM_Tipo_Implemento " +
        " FROM Veiculos , Modelos_Veiculos, Tipos_Veiculos " +
        " WHERE Veiculos.oid_Modelo_Veiculo        = Modelos_Veiculos.oid_Modelo_Veiculo " +
        "   AND Modelos_Veiculos.oid_Tipo_Veiculo  = Tipos_Veiculos.oid_Tipo_Veiculo " +
        "   AND (Veiculos.DM_Monitorado = 'P' OR Veiculos.DM_Monitorado = 'T' )";

    if (util.doValida (ed.getOID_Veiculo ())) {
      sql += "   AND Veiculos.OID_Veiculo = '" + ed.getOID_Veiculo () + "' ";
    }
    sql += " ORDER BY  Veiculos.NR_Frota ";

    // System.out.println (sql);
    ResultSet res = executasql.executarConsulta (sql);
    ResultSet res2 = null;
    try {
      while (res.next ()) {
        Movimento_VeiculoED movVeic = new Movimento_VeiculoED ();
        movVeic.setNM_Modelo_Veiculo (" ");
        movVeic.setNM_Modelo_Carreta (" ");
        movVeic.setDM_Procedencia ("-");
        movVeic.setDM_Procedencia_Carreta ("-");
        movVeic.setDT_Previsao_Chegada (" ");
        movVeic.setHR_Previsao_Chegada (" ");
        movVeic.setDM_Expresso ("----");
        movVeic.setOID_Veiculo (res.getString ("oid_Veiculo"));
        movVeic.setOID_Carreta ("-");
        movVeic.setNM_Motorista ("");
        movVeic.setDM_Tipo_Monitoramento ("-");
        movVeic.setOID_Motorista (res.getString ("OID_Motorista"));
        // System.out.println ("1");

        movVeic.setNM_Modelo_Veiculo (res.getString ("CD_Modelo_Veiculo"));
        movVeic.setNM_Situacao_Cavalo (situacao_Veiculo (res.getString ("DM_Situacao_Veiculo")));
        movVeic.setNR_Frota (res.getString ("NR_Frota"));

        movVeic.setDM_Procedencia ("T");
        res2 = executasql.executarConsulta (
            " SELECT DM_Procedencia " +
            " FROM Complementos_Veiculos " +
            " WHERE Complementos_Veiculos.oid_Veiculo = '" + res.getString ("OID_Veiculo") + "'");
        while (res2.next ()) {
          movVeic.setDM_Procedencia (res2.getString ("DM_Procedencia"));
        }

        sql = " Select * " +
            " FROM Coletas, Pessoas, Cidades " +
            " WHERE Coletas.oid_Pessoa = Pessoas.oid_Pessoa " +
            " AND Pessoas.oid_Cidade   = Cidades.oid_Cidade  " +
            " AND   Coletas.oid_Veiculo ='" + res.getString ("oid_Veiculo") + "'" +
            " AND   Coletas.DM_Situacao <>'C' " +
            " AND   Coletas.DT_Coleta = (select max (DT_Coleta) FROM Coletas WHERE Coletas.DT_Coleta > '01/02/2006' and oid_Veiculo ='" +
            res.getString ("oid_Veiculo") + "') ";

    // System.out.println (sql);

        res2 = this.executasql.executarConsulta (sql);
        if (res2.next ()) {

    // System.out.println ("Achoi col");

            movVeic.setOID_Coleta (res2.getLong ("OID_Coleta"));
            DataFormatada.setDT_FormataData (res2.getString ("DT_Coleta"));
            movVeic.setDT_Coleta (DataFormatada.getDT_FormataData ());
            movVeic.setNM_Local_Coleta ( (res2.getString ("NM_Razao_Social") + "                      ").substring (0 , 12) + "/" + (res2.getString ("NM_cidade") + "                      ").substring (0 , 8));
            movVeic.setNM_Destino ( (res2.getString ("NM_Destinatario") + "                      ").substring (0 , 8) + "/" + (res2.getString ("NM_Cidade_Destinatario") + "                           ").substring (0 , 10));
            DataFormatada.setDT_FormataData (res2.getString ("DT_Previsao_Entrega"));
            movVeic.setDT_Previsao_Chegada (DataFormatada.getDT_FormataData ());
            movVeic.setHR_Previsao_Chegada (res2.getString ("HR_Previsao_Entrega"));
            movVeic.setDM_Expresso ("Col.");

            if (util.doValida (res2.getString ("oid_Pessoa_Destinatario")) && res2.getString ("oid_Pessoa_Destinatario").length () > 4) {
              ResultSet res3 = null;

              // System.out.println ("oid_Moto222" + movVeic.getOID_Motorista ());
              res3 = executasql.executarConsulta ("Select NM_Razao_Social, NM_Cidade " +
                                                  " FROM Pessoas, Cidades " + 
                                                  " WHERE Pessoas.oid_Cidade = Cidades.oid_Cidade " +
                                                  " AND   Pessoas.oid_Pessoa = '" + res2.getString ("oid_Pessoa_Destinatario") + "'");
              while (res3.next ()) {
                movVeic.setNM_Destino ( (res3.getString ("NM_Razao_Social") + "                      ").substring (0 , 12) + "/" + (res2.getString ("NM_cidade") + "                      ").substring (0 , 8));
              }
          }
        }

        // System.out.println ("oid_Moto" + movVeic.getOID_Motorista ());
        movVeic.setNM_Motorista ("");
        if (util.doValida (movVeic.getOID_Motorista ()) && movVeic.getOID_Motorista ().length () > 4) {

          // System.out.println ("oid_Moto222" + movVeic.getOID_Motorista ());
          res2 = executasql.executarConsulta ("Select NM_Razao_Social as NM_Motorista FROM Pessoas WHERE oid_Pessoa = '" + movVeic.getOID_Motorista () + "'");
          while (res2.next ()) {
            movVeic.setNM_Motorista (res2.getString ("NM_Motorista"));
          }

        }

        String listar = "S";
        if (!"-".equals (ed.getDM_Procedencia ()) && !"-".equals (movVeic.getDM_Procedencia ())) {
          listar = "N";
          if (ed.getDM_Procedencia ().equals (movVeic.getDM_Procedencia ()))
            listar = "S";
          else {
            if ("PA".equals (ed.getDM_Procedencia ())) {
              if ("P".equals (movVeic.getDM_Procedencia ()) ||
                  ("A".equals (movVeic.getDM_Procedencia ())))
                listar = "S";
            }
            if ("TA".equals (ed.getDM_Procedencia ())) {
              if ("T".equals (movVeic.getDM_Procedencia ()) ||
                  ("A".equals (movVeic.getDM_Procedencia ())))
                listar = "S";
            }
          }
        }
        if ("S".equals (listar)) {
          if (!"-".equals (ed.getDM_Procedencia_Carreta ()) && !"-".equals (movVeic.getDM_Procedencia_Carreta ())) {
            listar = "N";
            if (ed.getDM_Procedencia_Carreta ().equals (movVeic.getDM_Procedencia_Carreta ()))
              listar = "S";
            else {
              if ("PA".equals (ed.getDM_Procedencia_Carreta ())) {
                if ("P".equals (movVeic.getDM_Procedencia_Carreta ()) ||
                    ("A".equals (movVeic.getDM_Procedencia_Carreta ())))
                  listar = "S";
              }
              if ("TA".equals (ed.getDM_Procedencia_Carreta ())) {
                if ("T".equals (movVeic.getDM_Procedencia_Carreta ()) ||
                    ("A".equals (movVeic.getDM_Procedencia_Carreta ())))
                  listar = "S";
              }
            }
          }
        }
        // System.out.println ("ed.getDM_Tipo_Monitoramento()->>" + ed.getDM_Tipo_Monitoramento ());
        // System.out.println ("movVeic.getDM_Tipo_Monitoramento()->>" + movVeic.getDM_Tipo_Monitoramento ());

        if ("S".equals (listar)) {

          if (!"-".equals (ed.getDM_Tipo_Monitoramento ())) {
            listar = "N";
            if (ed.getDM_Tipo_Monitoramento ().equals (movVeic.getDM_Tipo_Monitoramento ())) {
              listar = "S";
            }
          }

        }
        nr_total++;
        movVeic.setNR_Total (nr_total);

        if ("S".equals (listar)) {
          nr_ordem++;
          movVeic.setNR_Ordem (nr_ordem);
          list.add (movVeic);
        }

      }
      // System.out.println ("999");

      return list;
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () ,
                          "lista(HttpServletRequest request)");
    }
  }



  public Movimento_VeiculoED altera(Movimento_VeiculoED ed)throws Excecoes{

    String sql = null;

    Movimento_VeiculoED edVolta = new Movimento_VeiculoED();

    try{

     // System.out.println(" alterando OID_Movimento_Veiculo ->> " + ed.getOID_Movimento_Veiculo());


//      sql = " update Movimentos_Veiculos set = " + ed.getVL_Vencido() ;

      sql+= " Where OID_Movimento_Veiculo = " + ed.getOID_Movimento_Veiculo();

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

  public Movimento_VeiculoED atualiza(Movimento_VeiculoED ed)throws Excecoes{

    String sql = null;
    ResultSet res = null;
    ResultSet res2 = null;
    ResultSet res3 = null;
    String inclui="N";
    String carregado="N";

    try{

      Nr_Sort = String.valueOf(System.currentTimeMillis()).toString();

      // System.out.println(" atualizaVeiculo ->>>>>>>>>>>>>>>>>>>" );


      sql= " DELETE FROM Movimentos_Veiculos ";
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
            //"   and  Veiculos.oid_Veiculo = 'IBT4403' " +
            "   ORDER BY  Tipos_Veiculos.DM_Tipo_Implemento, Veiculos.oid_Veiculo ";
        

      // System.out.println(" sql " + sql);

      res = this.executasql.executarConsulta(sql.toString());
      String carretas="";
      String placa="";
      while (res.next()){

        Movimento_VeiculoED edMov = new Movimento_VeiculoED();

        inclui="S";
        carregado="N";
        // System.out.println(" atualizaVeiculo wl a-> >>>>" +res.getString("oid_Veiculo"));

        if (!"2".equals(res.getString("DM_Tipo_Implemento"))&&
            !"3".equals(res.getString("DM_Tipo_Implemento"))&&
            !"4".equals(res.getString("DM_Tipo_Implemento"))){

          edMov.setOID_Veiculo(res.getString("oid_Veiculo"));
          edMov.setOID_Carreta("-");

          // System.out.println(" atualiza  CAVALO->>");

          sql= " SELECT Conjuntos_Veiculos.Placa_Carreta " +
               " FROM Conjuntos_Veiculos " +
               " WHERE Conjuntos_Veiculos.oid_Veiculo ='" + res.getString("oid_Veiculo") + "'" ;
           res2 = this.executasql.executarConsulta(sql.toString());
           if (res2.next()){
             edMov.setOID_Carreta(res2.getString("Placa_Carreta"));
           }

        }else{
          edMov.setOID_Veiculo("-");
          edMov.setOID_Carreta(res.getString("oid_Veiculo"));

          // System.out.println(" atualiza  CARRETA->>"+ res.getString("oid_Veiculo"));

          }

        // System.out.println(" atualizaVeiculo  inclui->>" + inclui);

        if ("S".equals(inclui)){
          // System.out.println(" atualizaVeiculo w5->>");


          edMov.setDT_Movimento(ed.getDT_Movimento());
          edMov.setHR_Movimento(Data.getHoraHM());
          edMov.setDM_Situacao("P");
          edMov.setTX_Observacao("");
          edMov.setNM_Localizacao1(" ");
          edMov.setNM_Localizacao2(" ");
          edMov.setNM_Localizacao3(" ");
          edMov.setDT_Localizacao1("01/01/2001");
          edMov.setDT_Localizacao2("01/01/2001");
          edMov.setDT_Localizacao3("01/01/2001");
          edMov.setOID_Manifesto(" ");
          edMov.setDT_Embarque("01/01/2001");
          edMov.setOID_Coleta(0);
          edMov.setQT_Entregas(0);
          edMov.setOID_Motorista("");
          edMov.setDT_Coleta("01/01/2001");
          edMov.setNM_Status ("PARADO");

          if (!"-".equals(edMov.getOID_Veiculo())) {
            sql = " SELECT Coletas.oid_Coleta, Coletas.oid_Motorista, Coletas.oid_Carreta, Coletas.DT_Coleta, Coletas.NR_Peso, Coletas.NR_Peso2, Coletas.NR_Peso3, Coletas.NR_Peso4   " +
                " FROM Coletas " +
                " WHERE Coletas.oid_Veiculo ='" + res.getString ("oid_Veiculo") + "'" +
                " AND   Coletas.DM_Situacao <>'C' " +
                " AND   Coletas.DT_Coleta = (select max (DT_Coleta) FROM Coletas WHERE Coletas.DT_Coleta > '01/02/2006' and oid_Veiculo ='" +
                res.getString ("oid_Veiculo") + "') ";
            res2 = this.executasql.executarConsulta (sql.toString ());
            while (res2.next ()) {
              edMov.setOID_Coleta (res2.getLong ("oid_Coleta"));
              edMov.setOID_Motorista (res2.getString ("oid_Motorista"));
              edMov.setOID_Carreta (res2.getString ("oid_Carreta"));

              DataFormatada.setDT_FormataData (res2.getString ("DT_Coleta"));
              edMov.setDT_Coleta (DataFormatada.getDT_FormataData ());
              edMov.setNM_Status ("EM COLETA");
              edMov.setQT_Entregas (1);
              carregado="S";
              if (res2.getDouble ("NR_Peso2") > 0) {
                edMov.setQT_Entregas (2);
              }
              if (res2.getDouble ("NR_Peso3") > 0) {
                edMov.setQT_Entregas (3);
              }
              if (res2.getDouble ("NR_Peso4") > 0) {
                edMov.setQT_Entregas (4);
              }
              edMov.setDM_Situacao ("C");
            }

            // System.out.println (" atualizaVeiculo w10->>");


            sql = " SELECT Manifestos.oid_Manifesto, Manifestos.NR_KIT, Manifestos.NR_Ajudante,  Manifestos.oid_Veiculo_Carreta, Manifestos.oid_Pessoa as oid_Motorista, Manifestos.DT_Emissao  " +
                  " FROM Manifestos " +
                  " WHERE Manifestos.oid_Veiculo ='" + res.getString ("oid_Veiculo") + "'";
            if (edMov.getOID_Coleta () == 0) {
              sql += " AND   Manifestos.DT_Emissao = (select max (DT_Emissao) FROM Manifestos WHERE oid_Veiculo ='" + res.getString ("oid_Veiculo") + "') ";
            }
            else {
              sql += " AND   Manifestos.DT_Emissao >= '" + edMov.getDT_Coleta () + "'";
            }

            // System.out.println (" sql manif ->>" + sql);

              res2 = this.executasql.executarConsulta (sql.toString ());
              while (res2.next ()) {

            // System.out.println (" Achou ->>" + res2.getString ("oid_Manifesto"));

                edMov.setOID_Manifesto (res2.getString ("oid_Manifesto"));
                edMov.setOID_Carreta (res2.getString ("oid_Veiculo_Carreta"));
                edMov.setOID_Motorista (res2.getString ("oid_Motorista"));

                DataFormatada.setDT_FormataData (res2.getString ("DT_Emissao"));
                edMov.setDT_Embarque (DataFormatada.getDT_FormataData());

                edMov.setNR_Ajudante(res2.getInt("NR_Ajudante"));
                edMov.setNR_KIT(res2.getInt("NR_KIT"));
                edMov.setDM_Situacao ("M");
                carregado="S";
                sql = " SELECT count (oid_Conhecimento) as qt_Entregas  " +
                    " FROM  Viagens " +
                    " WHERE Viagens.oid_Manifesto ='" + res2.getString ("oid_Manifesto") +
                    "'";
                res3 = this.executasql.executarConsulta (sql.toString ());
                while (res3.next ()) {
                  edMov.setQT_Entregas (res3.getInt ("qt_Entregas"));
                }
              }
              if (edMov.getQT_Entregas()==0) {
                edMov.setOID_Coleta(0);
              }

              // System.out.println (" atualizaVeiculo w30->>");

              sql = " SELECT * FROM Planos_Viagens " +
                  " WHERE oid_Veiculo ='" + res.getString ("oid_Veiculo") + "'" +
                  " ORDER by Planos_Viagens.Dt_Plano_Viagem desc, Planos_Viagens.Hr_Plano_Viagem desc " +
                  " LIMIT 4 ";

              // System.out.println (" plano via sql " + sql);

              res2 = this.executasql.executarConsulta (sql);
              int local = 0;
              while (res2.next ()) {
                // System.out.println (" 1 ");
                Plano_ViagemBD = new Plano_ViagemBD (this.sql);
                String nm_plano = Plano_ViagemBD.getNM_Plano (res2.getString ( "DM_Plano_Viagem"));
                
                edMov.setNM_Status(nm_plano.toUpperCase ());

                DataFormatada.setDT_FormataData (res2.getString ("DT_Plano_Viagem"));

                local++;
                // System.out.println (DataFormatada.getDT_FormataData ()+ " == " + local + " ->> " + nm_plano);

                if (local == 1) {
                  edMov.setDT_Localizacao1 (DataFormatada.getDT_FormataData ());
                  edMov.setHR_Localizacao1 (res2.getString ("HR_Plano_Viagem"));
                  edMov.setNM_Localizacao1 ( (res2.getString ("TX_Observacao")).toUpperCase ());
                }
                if (local == 2) {
                  edMov.setDT_Localizacao2 (DataFormatada.getDT_FormataData ());
                  edMov.setHR_Localizacao2 (res2.getString ("HR_Plano_Viagem"));
                  edMov.setNM_Localizacao2 ( (res2.getString ("TX_Observacao")).toUpperCase ());
                }
                if (local == 3) {
                  edMov.setDT_Localizacao3 (DataFormatada.getDT_FormataData ());
                  edMov.setHR_Localizacao3 (res2.getString ("HR_Plano_Viagem"));
                  edMov.setNM_Localizacao3 ( (res2.getString ("TX_Observacao")).toUpperCase ());
                }
              }


            if ("N".equals(carregado)) {
              edMov.setOID_Carreta("-");
            }

          }

          /*

          sql= " SELECT  Ordens_Servicos.oid_Ordem_Servico " +
               "        ,Ordens_Servicos.DT_Ordem_Servico " +
               "        ,Ordens_Servicos.NM_Servico1 " +
               "        ,Pessoas.NM_Razao_Social " +
               " FROM Ordens_Servicos, Pessoas " +
               " WHERE Ordens_Servicos.oid_pessoa_fornecedor = Pessoas.oid_pessoa_fornecedor "+
               " AND   Ordens_Servicos.oid_Veiculo ='" + res.getString("oid_Veiculo") + "'" +
               " AND   Ordens_Servicos.dt_encerramento ='' " ;
               " AND   Ordens_Servicos.oid_tipo_servico ='' " ;

           res2 = this.executasql.executarConsulta(sql.toString());
           while (res2.next()){
             edMov.setOID_Ordem_Servico(res2.getLong("oid_Ordem_Servico"));
             edMov.setDM_Situacao("M");
             edMov.setTX_Observacao("Entrada:" + res2.getString("DT_Ordem_Servico") + "-" + res2.getString("NM_Servico1"));
           }

           sql= " SELECT  Conhecimentos.DT_Previsao_Entrega  " +
                "        ,Conhecimentos.HR_Previsao_Entrega " +
                "        ,Pessoas.NM_Razao_Social " +
                "        ,Cidades.NM_Cidade " +
                "        ,Estados.CD_Estado " +
                " FROM Conhecimentos, Pessoas, Cidades, Regioes_Estados, Estados" +
                " WHERE Conhecimentos.oid_Pessoa_Destinatario = Pessoas.oid_Pessoa " +
                " AND   Pessoas.oid_Cidade = Cidades.oid_Cidade " +
                " AND   Cidades.oid_Regiao_Estado = Regioes_Estados.oid_Regiao_Estado " +
                " AND   Regioes_Estados.oid_Estado = Estados.oid_Estado " +
                " AND   Conhecimentos.oid_Veiculo ='" + res.getString("oid_Veiculo") + "'" +
                " AND   Conhecimentos.DT_Emissao ='" + ed.getDT_Movimento() + "'" +
                " ORDER BY Conhecimentos.DT_Previsao_Entrega, Conhecimentos.HR_Previsao_Entrega " ;

            // System.out.println(sql);

            res2 = this.executasql.executarConsulta(sql.toString());
            while (res2.next()){
              edMov.setNM_Carregamento((res2.getString("oid_Motorista")+ "               ").substring(0,15)+(res2.getString("NM_Razao_Social")+ "               ").substring(0,15) +(res2.getString("NM_Cidade")+ "               ").substring(0,10)+ "/" + (res2.getString("CD_Estado")+ "  ").substring(0,2));
            }
           */
          
          carretas+=ed.getOID_Carreta();


          if ("-".equals(ed.getOID_Veiculo()) &&
              "-".equals(ed.getOID_Carreta())) {

          }else inclui (edMov);

        }

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

  public Movimento_VeiculoED organizaMapa(Movimento_VeiculoED ed)throws Excecoes{

    String sql = null;
    ResultSet res = null;

    try{

      // System.out.println("verifica carretas--");

      sql = " SELECT oid_Movimento_Veiculo, oid_Carreta from Movimentos_Veiculos where OID_Carreta <>'-' Order BY oid_carreta, dt_embarque DESC" ;
      String oid_Carreta="";
      res = this.executasql.executarConsulta(sql.toString());

      while (res.next()){
        // System.out.println("->> " + res.getString("oid_Carreta"));

        if (oid_Carreta.equals(res.getString("oid_Carreta"))) {
          sql = "Update Movimentos_Veiculos SET oid_Carreta='-' WHERE OID_Movimento_Veiculo = ";
          sql += "(" + res.getString("oid_Movimento_Veiculo") + ")";

          // System.out.println("Apagou " + sql);

          int i = executasql.executarUpdate(sql);
        }
        oid_Carreta=res.getString("oid_Carreta");

      }
      sql = " SELECT oid_Movimento_Veiculo, oid_Motorista from Movimentos_Veiculos where oid_Motorista <>''  Order BY oid_Motorista, dt_embarque DESC" ;
      String oid_Motorista="";
      res = this.executasql.executarConsulta(sql.toString());

      while (res.next()){
        // System.out.println("->> " + res.getString("oid_Motorista"));

        if (oid_Motorista.equals(res.getString("oid_Motorista"))) {
          sql = "Update Movimentos_Veiculos SET oid_Motorista='' WHERE OID_Movimento_Veiculo = ";
          sql += "(" + res.getString("oid_Movimento_Veiculo") + ")";

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



  public byte[] imprime_Movimento_Veiculo(Movimento_VeiculoED ed)throws Excecoes{

    byte[] b = null;
    try{
      ArrayList lista = new ArrayList();
      lista = this.lista(ed);

      // System.out.println("Lista =>>>" + lista.size ());

      Movimento_VeiculoRL conRL = new Movimento_VeiculoRL ();
      b = conRL.geraAnalise_Movimento_Veiculo (lista , ed ,  executasql);

    }

    catch (Excecoes e){
      throw e;
    }
    catch(Exception exc){
      Excecoes exce = new Excecoes();
      exce.setExc(exc);
      exce.setMensagem("Erro no método listar");
      exce.setClasse(this.getClass().getName());
      exce.setMetodo("geraRelatorio(Movimento_VeiculoED ed)");
    }
    return b;
  }


  public void inclui(Movimento_VeiculoED ed) throws Excecoes{

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


      sql = " insert into Movimentos_Veiculos ( " +
          " OID_Movimento_Veiculo, "+
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
      excecoes.setMensagem("Erro de Movimentos_Veiculos de dados");
      excecoes.setExc(exc);
    }
  }

  public void deleta(Movimento_VeiculoED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from Movimentos_Veiculos WHERE OID_Movimento_Veiculo = ";
      sql += "(" + ed.getOID_Movimento_Veiculo() + ")";

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

  public Movimento_VeiculoED importaPosicao (Movimento_VeiculoED ed) throws Excecoes {

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
          Movimento_VeiculoED mov_veicED = new Movimento_VeiculoED ();
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
                sql = " select oid_Movimento_Veiculo FROM Movimentos_Veiculos ";
                sql += "  where  Movimentos_Veiculos.oid_Veiculo = '" + oid_veiculo + "'";

                //// System.err.println ("achou sql  " + sql);
                ResultSet res = null;

                res = this.executasql.executarConsulta (sql);
                while (res.next ()) {
                  sql = " UPDATE Movimentos_Veiculos SET " +
                      "        dt_localizacao1='" + data + "'" +
                      "       ,hr_localizacao1='" + hora + "'" +
                      "       ,nm_localizacao1='" + posicao + "'" +
                      "   WHERE oid_Movimento_Veiculo='" + res.getString("oid_Movimento_Veiculo") + "'";
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
