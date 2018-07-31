package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.Abastecimento_ViagemED;
import com.master.root.FormataDataBean;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;
 
public class Abastecimento_ViagemBD {

  private ExecutaSQL executasql;

  public Abastecimento_ViagemBD(ExecutaSQL sql) {
    this.executasql = sql;
  }
 
  
  public void altera(Abastecimento_ViagemED ed) throws Excecoes{

    String sql = null;

    try{

      sql = " update Abastecimentos_Viagens set DT_Abastecimento_Viagem = '" + ed.getDT_Abastecimento_Viagem() + "',  HR_Abastecimento_Viagem = '" + ed.getHR_Abastecimento_Viagem() + "',  DM_Abastecimento_Viagem = '" + ed.getDM_Abastecimento_Viagem() + "' ,TX_Observacao= '" + ed.getTX_Observacao() + "', NR_Litros=" + ed.getNR_Litros();
      sql += " where oid_Abastecimento_Viagem = '" + ed.getOID_Abastecimento_Viagem() + "'";

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

  public void deleta(Abastecimento_ViagemED ed) throws Excecoes{

          //// System.out.println("delete Abastecimento_Viagem ->> " );


    String sql = null;
    boolean DM_Lancado_Ordem_Frete = false;

    try{


      sql = "delete from Abastecimentos_Viagens WHERE oid_Abastecimento_Viagem = ";
      sql += "('" + ed.getOID_Abastecimento_Viagem() + "')";

      int i = executasql.executarUpdate(sql);

          //// System.out.println("delete Abastecimento_Viagem ->> 2 " );

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

  public ArrayList lista(Abastecimento_ViagemED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();

    try{
      sql =  " SELECT * From Abastecimentos_Viagens, Manifestos, Postos, Pessoas ";
      sql += " WHERE  Abastecimentos_Viagens.oid_Manifesto = Manifestos.oid_Manifesto";
      sql += " AND    Abastecimentos_Viagens.oid_Posto = Postos.oid_Posto";
      sql += " AND    Postos.oid_Pessoa = Pessoas.oid_Pessoa";

      if (String.valueOf(ed.getOID_Manifesto()) != null &&
          !String.valueOf(ed.getOID_Manifesto()).equals("0") &&
          !String.valueOf(ed.getOID_Manifesto()).equals("null")){
        sql += " and Abastecimentos_Viagens.OID_Manifesto = '" + ed.getOID_Manifesto() + "'";
      }
      if (String.valueOf(ed.getOID_Posto()) != null &&
          !String.valueOf(ed.getOID_Posto()).equals("0") &&
          !String.valueOf(ed.getOID_Posto()).equals("null")){
        sql += " and Abastecimentos_Viagens.OID_Posto = '" + ed.getOID_Posto() + "'";
      }

      sql += " Order by Abastecimentos_Viagens.Dt_Abastecimento_Viagem, Abastecimentos_Viagens.Hr_Abastecimento_Viagem ";


      // System.out.println(sql);

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      FormataDataBean DataFormatada = new FormataDataBean();

      //popula
      while (res.next()){
        Abastecimento_ViagemED edVolta = new Abastecimento_ViagemED();

      // System.out.println("ok 1");
        edVolta.setOID_Manifesto(res.getString("OID_Manifesto"));
        edVolta.setNR_Manifesto(res.getLong("NR_Manifesto"));

        edVolta.setOID_Abastecimento_Viagem(res.getString("OID_Abastecimento_Viagem"));
        edVolta.setDT_Abastecimento_Viagem(res.getString("DT_Abastecimento_Viagem"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Abastecimento_Viagem());
        edVolta.setDT_Abastecimento_Viagem(DataFormatada.getDT_FormataData());

      // System.out.println("ok 2");

        edVolta.setHR_Abastecimento_Viagem(res.getString("HR_Abastecimento_Viagem"));
        edVolta.setTX_Observacao(res.getString("TX_Observacao"));
        edVolta.setNM_Origem(res.getString("NM_Origem"));
        edVolta.setNM_Destino(res.getString("NM_Destino"));
      // System.out.println("ok 3");

        edVolta.setNR_Autorizacao(res.getString("NR_Autorizacao"));

        edVolta.setOID_Posto(res.getString("OID_Posto"));
        edVolta.setNM_Posto(res.getString("NM_Razao_Social"));
        if (res.getString("NM_Apelido") != null && !res.getString("NM_Apelido").equals("null") && !res.getString("NM_Apelido").equals("")){
          edVolta.setNM_Posto(res.getString("NM_Apelido"));
        }

      // System.out.println("ok 4");

        edVolta.setNR_Litros(res.getDouble("NR_Litros"));
        edVolta.setDM_Abastecimento_Viagem(res.getString("DM_Abastecimento_Viagem"));

        list.add(edVolta);
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

  public Abastecimento_ViagemED getByRecord(Abastecimento_ViagemED ed)throws Excecoes{

    String sql = null;

    Abastecimento_ViagemED edVolta = new Abastecimento_ViagemED();

    try{

      sql =  " SELECT * From Abastecimentos_Viagens, Manifestos, Postos, Pessoas ";
      sql += " WHERE  Abastecimentos_Viagens.oid_Manifesto = Manifestos.oid_Manifesto";
      sql += " AND    Abastecimentos_Viagens.oid_Posto = Postos.oid_Posto";
      sql += " AND    Postos.oid_Pessoa = Pessoas.oid_Pessoa";
      sql += " AND    Abastecimentos_Viagens.OID_Abastecimento_Viagem = '" + ed.getOID_Abastecimento_Viagem() + "'";

      FormataDataBean DataFormatada = new FormataDataBean();

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);
      while (res.next()){
        edVolta.setOID_Abastecimento_Viagem(res.getString("OID_Abastecimento_Viagem"));
        edVolta.setOID_Manifesto(res.getString("OID_Manifesto"));
        edVolta.setNR_Manifesto(res.getLong("NR_Manifesto"));
        edVolta.setNR_Manifesto(res.getLong("NR_Manifesto"));
        edVolta.setOID_Manifesto(res.getString("OID_Manifesto"));
        edVolta.setDT_Abastecimento_Viagem(res.getString("DT_Abastecimento_Viagem"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Abastecimento_Viagem());
        edVolta.setDT_Abastecimento_Viagem(DataFormatada.getDT_FormataData());
        edVolta.setHR_Abastecimento_Viagem(res.getString("HR_Abastecimento_Viagem"));
        edVolta.setTX_Observacao(res.getString("TX_Observacao"));

        edVolta.setNM_Origem(res.getString("NM_Origem"));
        edVolta.setNM_Destino(res.getString("NM_Destino"));

        edVolta.setNR_Autorizacao(res.getString("NR_Autorizacao"));

        edVolta.setOID_Posto(res.getString("OID_Posto"));
        edVolta.setNM_Posto(res.getString("NM_Razao_Social"));
        if (res.getString("NM_Apelido") != null && !res.getString("NM_Apelido").equals("null") && !res.getString("NM_Apelido").equals("")){
          edVolta.setNM_Posto(res.getString("NM_Apelido"));
        }
        edVolta.setNR_Litros(res.getDouble("NR_Litros"));
        edVolta.setDM_Abastecimento_Viagem(res.getString("DM_Abastecimento_Viagem"));

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


  public double getByLitrosAbastecimento(String oid_Manifesto)throws Excecoes{

    String sql = null;
    double tt_litros=0;

    try{

      sql =  " SELECT SUM (nr_litros) as tt_litros From Abastecimentos_Viagens ";
      sql += " WHERE  Abastecimentos_Viagens.OID_Manifesto = '" + oid_Manifesto + "'";

      FormataDataBean DataFormatada = new FormataDataBean();

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);
      if (res.next()){
        tt_litros=res.getDouble("tt_litros");
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

    return tt_litros;
  }



  public void inclui(Abastecimento_ViagemED ed) throws Excecoes{

    String sql = null;
    long valOid = 0;
    String chave = null;
    ResultSet res = null;
    String nr_Autorizacao = "";
  
    try {
  
      // System.out.println ("Abastecimento_Viagem inclui 4 ");
      sql = " SELECT NR_Manifesto, NR_Autorizacao FROM Manifestos WHERE oid_Manifesto='" + ed.getOID_Manifesto () + "'";

      // System.out.println ("Abastecimento_Viagem = "+sql);

      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        long nr_aut=res.getLong("NR_Autorizacao");
        nr_aut++;
        sql = " UPDATE Manifestos SET NR_Autorizacao=" +nr_aut +" WHERE oid_Manifesto='" + ed.getOID_Manifesto () + "'";

      // System.out.println ("Abastecimento_Viagem = "+sql);

        executasql.executarUpdate(sql);
        
        nr_Autorizacao=String.valueOf(nr_aut);
        if (nr_aut<10) nr_Autorizacao="0" + String.valueOf(nr_aut);
        nr_Autorizacao=res.getString("NR_Manifesto")+nr_Autorizacao;
      }


      chave = String.valueOf(System.currentTimeMillis()).toString();

      sql = " insert into Abastecimentos_Viagens (OID_Abastecimento_Viagem, OID_Manifesto, DM_Situacao, DT_Abastecimento_Viagem, HR_Abastecimento_Viagem, DM_Abastecimento_Viagem, NM_Origem, NM_Destino, TX_Observacao, oid_Veiculo, oid_Posto, NR_Litros, NR_Autorizacao ) values ";
      sql += "('" + chave + "','" + ed.getOID_Manifesto() + "','A','"  + ed.getDT_Abastecimento_Viagem() + "','" + ed.getHR_Abastecimento_Viagem() + "','N','" + ed.getNM_Origem()+ "','" + ed.getNM_Destino() + "','" + ed.getTX_Observacao()+ "','" + ed.getOID_Veiculo() + "','" + ed.getOID_Posto() +"'," + ed.getNR_Litros() +",'" +nr_Autorizacao +"')";

                // System.out.println( "Abastecimento viagem " + sql);

      int i = executasql.executarUpdate(sql);

    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir");
      excecoes.setMetodo("inclui");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }



}
