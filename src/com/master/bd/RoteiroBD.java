package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.RoteiroED;
import com.master.rl.RoteiroRL;
import com.master.root.FormataDataBean;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;


public class RoteiroBD {
  Calcula_FreteBD Calcula_FreteBD = null;

  private ExecutaSQL executasql;

  public RoteiroBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public RoteiroED inclui(RoteiroED ed) throws Excecoes{

    String sql = null;
    long valOid = 0;
    String chave = null;

    RoteiroED RoteiroED = new RoteiroED();

    try{

      long Nr_Sort = (new Long(String.valueOf(System.currentTimeMillis()).toString().substring(3,12)).longValue());

      chave = String.valueOf(Nr_Sort);

      ed.setCD_Roteiro(chave);

      sql = " insert into Roteiros ("+
      "CD_Roteiro, "+
      "OID_Cidade, "+
      "OID_Cidade_Destino, "+
      "VL_KM_Carreta, " +
      "VL_KM_Truck, " +
      "VL_KM_Outros, " +
      "VL_KM_Carreta_Adto, " +
      "VL_KM_Truck_Adto, " +
      "VL_KM_Outros_Adto, " +
      "NM_Roteiro, "+
      "TX_Observacao, "+
      "NR_Sequencia, "+
      "NM_Sequencia "+
      ") values ";
      sql += "('" + ed.getCD_Roteiro() + "'," +
                ed.getOID_Cidade() + "," +
                ed.getOID_Cidade_Destino() + "," +
                ed.getVL_KM_Carreta() + "," +
                ed.getVL_KM_Truck() + "," +
                ed.getVL_KM_Outros() + "," +
                ed.getVL_KM_Carreta_Adto() + "," +
                ed.getVL_KM_Truck_Adto() + "," +
                ed.getVL_KM_Outros_Adto() + ",'" +
                ed.getNM_Roteiro() + "','" +
                ed.getTX_Observacao() + "'," +
                valOid + ",'" +
		String.valueOf(ed.getOID_Cidade()) + String.valueOf(ed.getOID_Cidade_Destino()) + "')";

// System.out.println(sql);

      int i = executasql.executarUpdate(sql);

      RoteiroED.setCD_Roteiro(ed.getCD_Roteiro());
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir Roteiro");
      excecoes.setMetodo("inclui");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return RoteiroED;
  }

  public void altera(RoteiroED ed) throws Excecoes{

    String sql = null;

    try{
                          
      sql = " update Roteiros set NM_Roteiro = '" + ed.getNM_Roteiro() + "',";
      sql += " OID_Cidade = " + ed.getOID_Cidade() + ",";
      sql += " OID_Cidade_Destino = " + ed.getOID_Cidade_Destino() + ",";
      sql += " VL_KM_Carreta = " + ed.getVL_KM_Carreta()+ ",";
      sql += " VL_KM_Truck = " + ed.getVL_KM_Truck()+ ",";
      sql += " VL_KM_Outros = " + ed.getVL_KM_Outros()+ ",";
      sql += " VL_KM_Carreta_Adto = " + ed.getVL_KM_Carreta_Adto()+ ",";
      sql += " VL_KM_Truck_Adto = " + ed.getVL_KM_Truck_Adto()+ ",";
      sql += " VL_KM_Outros_Adto = " + ed.getVL_KM_Outros_Adto() + ",";
      sql += " TX_Observacao = '" + ed.getTX_Observacao() + "'";
      sql += " where CD_Roteiro = '" + ed.getCD_Roteiro() + "'";

      // System.out.println(sql);

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

  public void deleta(RoteiroED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from Roteiros WHERE CD_Roteiro = ";
      sql += "('" + ed.getCD_Roteiro() + "')";

      int i = executasql.executarUpdate(sql);
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

  public ArrayList lista(RoteiroED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();

    try{

      sql = " select Roteiros.*,"+
      "Cidade_Remetente.NM_Cidade as NM_Cidade_Origem, "+
      "Cidade_Destinatario.NM_Cidade as NM_Cidade_Destino, "+
      "Estado_Remetente.CD_Estado as CD_Estado_Origem, "+
      "Estado_Destinatario.CD_Estado as CD_Estado_Destino "+
      " from Roteiros, "+
      " cidades Cidade_Remetente, "+
      " estados Estado_Remetente, "+
      " regioes_estados Regiao_Estado_Remetente, "+
      " cidades Cidade_Destinatario, "+
      " estados Estado_Destinatario, "+
      " regioes_estados Regiao_Estado_Destinatario "+
      " WHERE "+
      " Roteiros.oid_cidade = Cidade_Remetente.oid_cidade and"+
      " Cidade_Remetente.oid_regiao_Estado = Regiao_Estado_Remetente.oid_regiao_estado and"+
      " Regiao_Estado_Remetente.oid_Estado = Estado_Remetente.oid_Estado and"+
      " Roteiros.oid_cidade_destino = Cidade_Destinatario.oid_cidade and"+
      " Cidade_Destinatario.oid_regiao_Estado = Regiao_Estado_Destinatario.oid_regiao_estado and"+
      " Regiao_Estado_Destinatario.oid_Estado = Estado_Destinatario.oid_Estado ";

      if (String.valueOf(ed.getCD_Roteiro()) != null &&
          !String.valueOf(ed.getCD_Roteiro()).equals("") &&
          !String.valueOf(ed.getCD_Roteiro()).equals("null")){
        sql += " and Roteiros.CD_Roteiro = '" + ed.getCD_Roteiro() + "'";
      }

      if (String.valueOf(ed.getNM_Roteiro()) != null &&
          !String.valueOf(ed.getNM_Roteiro()).equals("") &&
          !String.valueOf(ed.getNM_Roteiro()).equals("null")){
        sql += " and Roteiros.NM_Roteiro = '" + ed.getNM_Roteiro() + "'";
      }

      if (String.valueOf(ed.getOID_Cidade()) != null &&
          !String.valueOf(ed.getOID_Cidade()).equals("0")){
        sql += " and Roteiros.OID_Cidade = " + ed.getOID_Cidade();
      }

      if (String.valueOf(ed.getOID_Cidade_Destino()) != null &&
          !String.valueOf(ed.getOID_Cidade_Destino()).equals("0")){
        sql += " and Roteiros.OID_Cidade_Destino = " + ed.getOID_Cidade_Destino();
      }

      sql += " Order by Cidade_Remetente.NM_Cidade,  Cidade_Destinatario.NM_Cidade";

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);
// System.out.println(sql);
      //popula

      FormataDataBean DataFormatada = new FormataDataBean();
      boolean DM_Tem_Registro = false;
      while (res.next()){
        RoteiroED edVolta = new RoteiroED();
        DM_Tem_Registro = true;
        edVolta.setCD_Roteiro(res.getString("CD_Roteiro"));
        edVolta.setNM_Roteiro(res.getString("NM_Roteiro"));
        edVolta.setTX_Observacao(res.getString("TX_Observacao"));
        edVolta.setNM_Cidade_Origem(res.getString("NM_Cidade_Origem"));
        edVolta.setNM_Cidade_Destino(res.getString("NM_Cidade_Destino"));
        edVolta.setNM_Estado_Origem(res.getString("CD_Estado_Origem"));
        edVolta.setNM_Estado_Destino(res.getString("CD_Estado_Destino"));
        edVolta.setVL_KM_Carreta(res.getDouble("VL_KM_Carreta"));
        edVolta.setVL_KM_Truck(res.getDouble("VL_KM_Truck"));
        edVolta.setVL_KM_Outros(res.getDouble("VL_KM_Outros"));
        edVolta.setVL_KM_Carreta_Adto(res.getDouble("VL_KM_Carreta_Adto"));
        edVolta.setVL_KM_Truck_Adto(res.getDouble("VL_KM_Truck_Adto"));
        edVolta.setVL_KM_Outros_Adto(res.getDouble("VL_KM_Outros_Adto"));

        list.add(edVolta);
      }

      if (!DM_Tem_Registro){
        sql = " select "+
        "CD_Roteiro, " +
        "NM_Roteiro, "+
        "Cidade_Remetente.NM_Cidade as NM_Cidade_Origem, "+
        "Cidade_Destinatario.NM_Cidade as NM_Cidade_Destino, "+
        "Estado_Remetente.CD_Estado as CD_Estado_Origem, "+
        "Estado_Destinatario.CD_Estado as CD_Estado_Destino "+
        " from Roteiros, "+
        " cidades Cidade_Remetente, "+
        " estados Estado_Remetente, "+
        " regioes_estados Regiao_Estado_Remetente, "+
        " cidades Cidade_Destinatario, "+
        " estados Estado_Destinatario, "+
        " regioes_estados Regiao_Estado_Destinatario "+
        " WHERE "+
        " Roteiros.oid_cidade = Cidade_Remetente.oid_cidade and"+
        " Cidade_Remetente.oid_regiao_Estado = Regiao_Estado_Remetente.oid_regiao_estado and"+
        " Regiao_Estado_Remetente.oid_Estado = Estado_Remetente.oid_Estado and"+
        " Roteiros.oid_cidade_destino = Cidade_Destinatario.oid_cidade and"+
        " Cidade_Destinatario.oid_regiao_Estado = Regiao_Estado_Destinatario.oid_regiao_estado and"+
        " Regiao_Estado_Destinatario.oid_Estado = Estado_Destinatario.oid_Estado ";

        if (String.valueOf(ed.getCD_Roteiro()) != null &&
            !String.valueOf(ed.getCD_Roteiro()).equals("") &&
            !String.valueOf(ed.getCD_Roteiro()).equals("null")){
          sql += " and Roteiros.CD_Roteiro = '" + ed.getCD_Roteiro() + "'";
        }

        if (String.valueOf(ed.getNM_Roteiro()) != null &&
            !String.valueOf(ed.getNM_Roteiro()).equals("") &&
            !String.valueOf(ed.getNM_Roteiro()).equals("null")){
          sql += " and Roteiros.NM_Roteiro = '" + ed.getNM_Roteiro() + "'";
        }

        if (String.valueOf(ed.getOID_Cidade()) != null &&
            !String.valueOf(ed.getOID_Cidade()).equals("0")){
          sql += " and Roteiros.OID_Cidade_Destino = " + ed.getOID_Cidade();
        }

        if (String.valueOf(ed.getOID_Cidade_Destino()) != null &&
            !String.valueOf(ed.getOID_Cidade_Destino()).equals("0")){
          sql += " and Roteiros.OID_Cidade = " + ed.getOID_Cidade_Destino();
        }

        sql += " Order by Roteiros.CD_Roteiro ";
// System.out.println("TEM_Registro !! > "+sql);
        res = null;
        res = this.executasql.executarConsulta(sql);

        while (res.next()){
          RoteiroED edVolta = new RoteiroED();
          edVolta.setCD_Roteiro(res.getString("CD_Roteiro"));
          edVolta.setNM_Roteiro(res.getString("NM_Roteiro"));
          edVolta.setNM_Cidade_Origem(res.getString("NM_Cidade_Destino"));
          edVolta.setNM_Cidade_Destino(res.getString("NM_Cidade_Origem"));
          edVolta.setNM_Estado_Origem(res.getString("CD_Estado_Origem"));
          edVolta.setNM_Estado_Destino(res.getString("CD_Estado_Destino"));

          list.add(edVolta);
        }
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao listar");
      excecoes.setMetodo("listar");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return list;
  }

  public ArrayList lista_Dinamica(RoteiroED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();
    boolean DM_Tem_Registro = false;
    long NR_Sequencia_Inicial = 0;
    long NR_Sequencia_Final = 0;

    try{
      sql = "Select "+
        "Roteiros.CD_Roteiro," +
        "Rotas_Roteiros.NR_SEQUENCIA " +
        " from Rotas, Roteiros, Rotas_Roteiros "+
        " WHERE "+
        " Rotas.OID_Rota = Rotas_Roteiros.OID_Rota and "+
        " Roteiros.CD_Roteiro = Rotas_Roteiros.CD_Roteiro ";
      if (String.valueOf(ed.getOID_Cidade()) != null &&
          !String.valueOf(ed.getOID_Cidade()).equals("0")){
        sql += " and Rotas.OID_Cidade = " + ed.getOID_Cidade();
      }

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      while (res.next()){
        NR_Sequencia_Inicial = res.getLong("NR_SEQUENCIA");

        sql = "Select "+
        "Rotas_Roteiros.NR_SEQUENCIA " +
        " from Rotas, Roteiros, Rotas_Roteiros "+
        " WHERE "+
        " Rotas.OID_Rota = Rotas_Roteiros.OID_Rota and "+
        " Roteiros.CD_Roteiro = Rotas_Roteiros.CD_Roteiro and "+
        " Roteiros.CD_Roteiro = '" + res.getString("CD_Roteiro") + "'";

        if (String.valueOf(ed.getOID_Cidade_Destino()) != null &&
            !String.valueOf(ed.getOID_Cidade_Destino()).equals("0")){
          sql += " and Rotas.OID_Cidade_Destino = " + ed.getOID_Cidade_Destino();
        }

        ResultSet resLocal = null;
        resLocal = this.executasql.executarConsulta(sql);

        while (resLocal.next()){
          NR_Sequencia_Final = resLocal.getLong("NR_SEQUENCIA");

          sql = "Select "+
          "Roteiros.CD_Roteiro," +
          "Roteiros.NM_Roteiro," +
          "Rotas_Roteiros.NR_SEQUENCIA," +
          "Cidade_Remetente.NM_Cidade as NM_Cidade_Origem, "+
          "Cidade_Destinatario.NM_Cidade as NM_Cidade_Destino, "+
          "Estado_Remetente.CD_Estado as CD_Estado_Origem, "+
          "Estado_Destinatario.CD_Estado as CD_Estado_Destino "+
          " from Rotas, Roteiros, Rotas_Roteiros, "+
          " cidades Cidade_Remetente, "+
          " estados Estado_Remetente, "+
          " regioes_estados Regiao_Estado_Remetente, "+
          " cidades Cidade_Destinatario, "+
          " estados Estado_Destinatario, "+
          " regioes_estados Regiao_Estado_Destinatario "+
          " WHERE "+
          " Rotas.oid_cidade = Cidade_Remetente.oid_cidade and"+
          " Cidade_Remetente.oid_regiao_Estado = Regiao_Estado_Remetente.oid_regiao_estado and"+
          " Regiao_Estado_Remetente.oid_Estado = Estado_Remetente.oid_Estado and"+
          " Rotas.oid_cidade_destino = Cidade_Destinatario.oid_cidade and"+
          " Cidade_Destinatario.oid_regiao_Estado = Regiao_Estado_Destinatario.oid_regiao_estado and"+
          " Regiao_Estado_Destinatario.oid_Estado = Estado_Destinatario.oid_Estado and "+
          " Rotas.OID_Rota = Rotas_Roteiros.OID_Rota and "+
          " Roteiros.CD_Roteiro = Rotas_Roteiros.CD_Roteiro and "+
          " Rotas_Roteiros.NR_SEQUENCIA >= " + NR_Sequencia_Inicial +
          " and Rotas_Roteiros.NR_SEQUENCIA <= " + NR_Sequencia_Final +
          " and Roteiros.CD_Roteiro = '" + res.getString("CD_Roteiro") + "'";

          sql += " Order by Rotas_Roteiros.NR_Sequencia ";
          ResultSet resLocal2 = null;
          resLocal2 = this.executasql.executarConsulta(sql);

          while (resLocal2.next()){
            RoteiroED edVolta = new RoteiroED();

            DM_Tem_Registro = true;
            edVolta.setCD_Roteiro(resLocal2.getString("CD_Roteiro"));
            edVolta.setNM_Roteiro(resLocal2.getString("NM_Roteiro"));
            edVolta.setNM_Cidade_Origem(resLocal2.getString("NM_Cidade_Origem"));
            edVolta.setNM_Cidade_Destino(resLocal2.getString("NM_Cidade_Destino"));
            edVolta.setNM_Estado_Origem(resLocal2.getString("CD_Estado_Origem"));
            edVolta.setNM_Estado_Destino(resLocal2.getString("CD_Estado_Destino"));
            edVolta.setNR_Sequencia(resLocal2.getLong("NR_SEQUENCIA"));
            list.add(edVolta);
          }
        }
      }

      if (!DM_Tem_Registro){
        sql = "Select "+
          "Roteiros.CD_Roteiro," +
          "Rotas_Roteiros.NR_SEQUENCIA " +
          " from Rotas, Roteiros, Rotas_Roteiros "+
          " WHERE "+
          " Rotas.OID_Rota = Rotas_Roteiros.OID_Rota and "+
          " Roteiros.CD_Roteiro = Rotas_Roteiros.CD_Roteiro ";

        if (String.valueOf(ed.getOID_Cidade()) != null &&
            !String.valueOf(ed.getOID_Cidade()).equals("0")){
          sql += " and Rotas.OID_Cidade_Destino = " + ed.getOID_Cidade();
        }

        res = null;
        res = this.executasql.executarConsulta(sql);

        while (res.next()){
          NR_Sequencia_Inicial = res.getLong("NR_SEQUENCIA");

          sql = "Select "+
          "Rotas_Roteiros.NR_SEQUENCIA " +
          " from Rotas, Roteiros, Rotas_Roteiros "+
          " WHERE "+
          " Rotas.OID_Rota = Rotas_Roteiros.OID_Rota and "+
          " Roteiros.CD_Roteiro = Rotas_Roteiros.CD_Roteiro and "+
          " Roteiros.CD_Roteiro = '" + res.getString("CD_Roteiro") + "'";

          if (String.valueOf(ed.getOID_Cidade_Destino()) != null &&
              !String.valueOf(ed.getOID_Cidade_Destino()).equals("0")){
            sql += " and Rotas.OID_Cidade = " + ed.getOID_Cidade_Destino();
          }

          ResultSet resLocal = null;
          resLocal = this.executasql.executarConsulta(sql);

          while (resLocal.next()){
            NR_Sequencia_Final = resLocal.getLong("NR_SEQUENCIA");
            DM_Tem_Registro = true;

            sql = "Select "+
            "Roteiros.CD_Roteiro," +
            "Roteiros.NM_Roteiro," +
            "Rotas_Roteiros.NR_SEQUENCIA," +
            "Cidade_Remetente.NM_Cidade as NM_Cidade_Origem, "+
            "Cidade_Destinatario.NM_Cidade as NM_Cidade_Destino, "+
            "Estado_Remetente.CD_Estado as CD_Estado_Origem, "+
            "Estado_Destinatario.CD_Estado as CD_Estado_Destino "+
            " from Rotas, Roteiros, Rotas_Roteiros, "+
            " cidades Cidade_Remetente, "+
            " estados Estado_Remetente, "+
            " regioes_estados Regiao_Estado_Remetente, "+
            " cidades Cidade_Destinatario, "+
            " estados Estado_Destinatario, "+
            " regioes_estados Regiao_Estado_Destinatario "+
            " WHERE "+
            " Rotas.oid_cidade = Cidade_Remetente.oid_cidade and"+
            " Cidade_Remetente.oid_regiao_Estado = Regiao_Estado_Remetente.oid_regiao_estado and"+
            " Regiao_Estado_Remetente.oid_Estado = Estado_Remetente.oid_Estado and"+
            " Rotas.oid_cidade_destino = Cidade_Destinatario.oid_cidade and"+
            " Cidade_Destinatario.oid_regiao_Estado = Regiao_Estado_Destinatario.oid_regiao_estado and"+
            " Regiao_Estado_Destinatario.oid_Estado = Estado_Destinatario.oid_Estado and "+
            " Rotas.OID_Rota = Rotas_Roteiros.OID_Rota and "+
            " Roteiros.CD_Roteiro = Rotas_Roteiros.CD_Roteiro and "+
            " Rotas_Roteiros.NR_SEQUENCIA <= " + NR_Sequencia_Inicial +
            " and Rotas_Roteiros.NR_SEQUENCIA >= " + NR_Sequencia_Final +
            " and Roteiros.CD_Roteiro = '" + res.getString("CD_Roteiro") + "'";

            sql += " Order by Rotas_Roteiros.NR_Sequencia desc";
            ResultSet resLocal2 = null;
            resLocal2 = this.executasql.executarConsulta(sql);

            while (resLocal2.next()){
              RoteiroED edVolta = new RoteiroED();

              DM_Tem_Registro = true;
              edVolta.setCD_Roteiro(resLocal2.getString("CD_Roteiro"));
              edVolta.setNM_Roteiro(resLocal2.getString("NM_Roteiro"));
              edVolta.setNM_Cidade_Origem(resLocal2.getString("NM_Cidade_Destino"));
              edVolta.setNM_Cidade_Destino(resLocal2.getString("NM_Cidade_Origem"));
              edVolta.setNM_Estado_Origem(resLocal2.getString("CD_Estado_Origem"));
              edVolta.setNM_Estado_Destino(resLocal2.getString("CD_Estado_Destino"));
              edVolta.setNR_Sequencia(resLocal2.getLong("NR_SEQUENCIA"));
              list.add(edVolta);
            }
          }
        }
      }
    }
      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao listar");
        excecoes.setMetodo("listar");
        excecoes.setExc(exc);
        throw excecoes;
      }

    return list;
  }

  public RoteiroED getByRecord(RoteiroED ed)throws Excecoes{

    String sql = null;

    RoteiroED edVolta = new RoteiroED();

    try{

      sql = " select * from Roteiros where 1=1 ";

      if (String.valueOf(ed.getCD_Roteiro()) != null &&
          !String.valueOf(ed.getCD_Roteiro()).equals("") &&
          !String.valueOf(ed.getCD_Roteiro()).equals("null")){
        sql += " and CD_Roteiro = '" + ed.getCD_Roteiro() + "'";
      }
      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      FormataDataBean DataFormatada = new FormataDataBean();

      while (res.next()){

        edVolta.setCD_Roteiro(res.getString("CD_Roteiro"));
        edVolta.setNM_Roteiro(res.getString("NM_Roteiro"));
        edVolta.setTX_Observacao(res.getString("TX_Observacao"));
        edVolta.setOID_Cidade(res.getLong("OID_Cidade"));
        edVolta.setOID_Cidade_Destino(res.getLong("OID_Cidade_Destino"));
        edVolta.setVL_KM_Carreta(res.getDouble("VL_KM_Carreta"));
        edVolta.setVL_KM_Truck(res.getDouble("VL_KM_Truck"));
        edVolta.setVL_KM_Outros(res.getDouble("VL_KM_Outros"));
        edVolta.setVL_KM_Carreta_Adto(res.getDouble("VL_KM_Carreta_Adto"));
        edVolta.setVL_KM_Truck_Adto(res.getDouble("VL_KM_Truck_Adto"));
        edVolta.setVL_KM_Outros_Adto(res.getDouble("VL_KM_Outros_Adto"));

        // System.out.println("rot 1" );
        sql=" SELECT SUM (nr_km) as tt_km " +
            " FROM  Rotas, Rotas_Roteiros " +
            " WHERE Rotas.oid_rota = Rotas_Roteiros.oid_Rota " +
            " AND   Rotas_Roteiros.cd_Roteiro = '" + res.getString("CD_Roteiro") + "'";


        ResultSet res2 = this.executasql.executarConsulta (sql);
        while (res2.next ()) {
          edVolta.setNR_KM_Viagem(res2.getDouble("tt_km"));
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

  public byte[] geraRelRotas(RoteiroED ed)throws Excecoes{
    String sql = null;
    byte[] b = null;


      sql = "Select  "+
        "Roteiros.CD_Roteiro, " +
        "Roteiros.NM_Roteiro, "+
        "Roteiros.CD_Roteiro, " +
        "Rotas.oid_rota, " +
        "Rotas.NM_Rodovia, " +
        "Rotas.NR_KM, " +
        "Rotas.NR_HR, " +
        "Rotas.NR_Media_Horaria, " +
        "Rota_Cidade_Origem.NM_Cidade as NM_Cidade_Rota_Origem, "+
        "Rota_Cidade_Destino.NM_Cidade as NM_Cidade_Rota_Destino, "+
        "Cidade_Remetente.NM_Cidade as NM_Cidade_Origem, "+
        "Cidade_Destinatario.NM_Cidade as NM_Cidade_Destino, "+
        "Estado_Remetente.CD_Estado as CD_Estado_Origem, "+
        "Estado_Destinatario.CD_Estado as CD_Estado_Destino "+
        " from Rotas, Roteiros, Rotas_Roteiros, "+
        " cidades Cidade_Remetente, "+
        " cidades Rota_Cidade_Origem, "+
        " cidades Rota_Cidade_Destino, "+
        " estados Estado_Remetente, "+
        " regioes_estados Regiao_Estado_Remetente, "+
        " cidades Cidade_Destinatario, "+
        " estados Estado_Destinatario, "+
        " regioes_estados Regiao_Estado_Destinatario "+
        " WHERE "+
        " Roteiros.oid_cidade = Cidade_Remetente.oid_cidade and"+
        " Cidade_Remetente.oid_regiao_Estado = Regiao_Estado_Remetente.oid_regiao_estado and"+
        " Regiao_Estado_Remetente.oid_Estado = Estado_Remetente.oid_Estado and"+
        " Rotas.oid_cidade_destino = Rota_Cidade_Destino.oid_cidade and"+
        " Rotas.oid_cidade = Rota_Cidade_Origem.oid_cidade and"+
        " Roteiros.oid_cidade_destino = Cidade_Destinatario.oid_cidade and"+
        " Cidade_Destinatario.oid_regiao_Estado = Regiao_Estado_Destinatario.oid_regiao_estado and"+
        " Regiao_Estado_Destinatario.oid_Estado = Estado_Destinatario.oid_Estado and "+
        " Rotas.OID_Rota = Rotas_Roteiros.OID_Rota and "+
        " Roteiros.CD_Roteiro = Rotas_Roteiros.CD_Roteiro ";


      if (String.valueOf(ed.getCD_Roteiro()) != null &&
          !String.valueOf(ed.getCD_Roteiro()).equals("") &&
          !String.valueOf(ed.getCD_Roteiro()).equals("null")){
        sql += " and Roteiros.CD_Roteiro = '" + ed.getCD_Roteiro() + "'";
      }

      if (String.valueOf(ed.getNM_Roteiro()) != null &&
          !String.valueOf(ed.getNM_Roteiro()).equals("") &&
          !String.valueOf(ed.getNM_Roteiro()).equals("null")){
        sql += " and Roteiros.NM_Roteiro = '" + ed.getNM_Roteiro() + "'";
      }

      if (String.valueOf(ed.getOID_Cidade()) != null &&
          !String.valueOf(ed.getOID_Cidade()).equals("0")){
        sql += " and Roteiros.OID_Cidade = " + ed.getOID_Cidade();
      }

      if (String.valueOf(ed.getOID_Cidade_Destino()) != null &&
          !String.valueOf(ed.getOID_Cidade_Destino()).equals("0")){
        sql += " and Roteiros.OID_Cidade_Destino = " + ed.getOID_Cidade_Destino();
      }

      sql += " Order by Roteiros.CD_Roteiro, Rotas_Roteiros.NR_Sequencia ";


    RoteiroED edVolta = new RoteiroED();


    try{

      // System.out.println(sql);

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql.toString());

      RoteiroRL conRL = new RoteiroRL();
      b =  conRL.geraRelRotas(res,ed);
    }

    catch (Excecoes e){
      throw e;
    }
    catch(Exception exc){
      Excecoes exce = new Excecoes();
      exce.setExc(exc);
      exce.setMensagem("Erro no método listar");
      exce.setClasse(this.getClass().getName());
      exce.setMetodo("geraRelatorio(ConhecimentoED ed)");
    }
    return b;
  }

}
