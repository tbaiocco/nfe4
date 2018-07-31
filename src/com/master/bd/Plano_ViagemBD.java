package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.Plano_ViagemED;
import com.master.root.FormataDataBean;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

public class Plano_ViagemBD {

  private ExecutaSQL executasql;

  public Plano_ViagemBD(ExecutaSQL sql) {
    this.executasql = sql;
  }


  public void altera(Plano_ViagemED ed) throws Excecoes{

    String sql = null;

    try{

      sql = " update Planos_Viagens set DT_Plano_Viagem = '" + ed.getDT_Plano_Viagem() + "',  HR_Plano_Viagem = '" + ed.getHR_Plano_Viagem() + "',  DM_Plano_Viagem = '" + ed.getDM_Plano_Viagem() + "' ,TX_Observacao= '" + ed.getTX_Observacao() + "'";
      sql += " where oid_Plano_Viagem = '" + ed.getOID_Plano_Viagem() + "'";

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

  public void deleta(Plano_ViagemED ed) throws Excecoes{

          //// System.out.println("delete Plano_Viagem ->> " );


    String sql = null;
    boolean DM_Lancado_Ordem_Frete = false;

    try{


      sql = "delete from Planos_Viagens WHERE oid_Plano_Viagem = ";
      sql += "('" + ed.getOID_Plano_Viagem() + "')";

      int i = executasql.executarUpdate(sql);

          //// System.out.println("delete Plano_Viagem ->> 2 " );

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

  public ArrayList lista(Plano_ViagemED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();

    try{
      sql =  " SELECT * From Planos_Viagens ";
      sql += " WHERE  1=1 ";

      if (String.valueOf(ed.getOID_Manifesto()) != null &&
          !String.valueOf(ed.getOID_Manifesto()).equals("0") &&
          !String.valueOf(ed.getOID_Manifesto()).equals("null")){
        sql += " and Planos_Viagens.OID_Manifesto = '" + ed.getOID_Manifesto() + "'";
      }
      if (String.valueOf(ed.getOID_Coleta()) != null &&
          !String.valueOf(ed.getOID_Coleta()).equals("0") &&
          !String.valueOf(ed.getOID_Coleta()).equals("null")){
        sql += " and Planos_Viagens.OID_Coleta = '" + ed.getOID_Coleta() + "'";
      }

      sql += " Order by Planos_Viagens.Dt_Plano_Viagem, Planos_Viagens.Hr_Plano_Viagem ";


      // System.out.println(sql);

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      FormataDataBean DataFormatada = new FormataDataBean();

      //popula
      while (res.next()){
        Plano_ViagemED edVolta = new Plano_ViagemED();

      // System.out.println("ok");

        edVolta.setOID_Plano_Viagem(res.getString("OID_Plano_Viagem"));
        edVolta.setOID_Plano_Viagem(res.getString("OID_Plano_Viagem"));
        edVolta.setDT_Plano_Viagem(res.getString("DT_Plano_Viagem"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Plano_Viagem());
        edVolta.setDT_Plano_Viagem(DataFormatada.getDT_FormataData());
        edVolta.setHR_Plano_Viagem(res.getString("HR_Plano_Viagem"));
        edVolta.setTX_Observacao(res.getString("TX_Observacao"));
        edVolta.setNM_Origem(res.getString("NM_Origem"));
        edVolta.setNM_Destino(res.getString("NM_Destino"));

        edVolta.setDM_Plano_Viagem(res.getString("DM_Plano_Viagem"));
        edVolta.setNM_Plano_Viagem(this.getNM_Plano(res.getString("DM_Plano_Viagem")));

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

  public Plano_ViagemED getByRecord(Plano_ViagemED ed)throws Excecoes{

    String sql = null;

    Plano_ViagemED edVolta = new Plano_ViagemED();

    try{

      sql =  " SELECT * From Planos_Viagens, Manifestos ";
      sql += " WHERE  Planos_Viagens.oid_Manifesto = Manifestos.oid_Manifesto";
      sql += " AND    OID_Plano_Viagem = '" + ed.getOID_Plano_Viagem() + "'";

      FormataDataBean DataFormatada = new FormataDataBean();

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);
      while (res.next()){
        edVolta.setOID_Plano_Viagem(res.getString("OID_Plano_Viagem"));
        edVolta.setOID_Plano_Viagem(res.getString("OID_Plano_Viagem"));
        edVolta.setNR_Manifesto(res.getLong("NR_Manifesto"));
        edVolta.setOID_Manifesto(res.getString("OID_Manifesto"));
        edVolta.setDT_Plano_Viagem(res.getString("DT_Plano_Viagem"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Plano_Viagem());
        edVolta.setDT_Plano_Viagem(DataFormatada.getDT_FormataData());
        edVolta.setHR_Plano_Viagem(res.getString("HR_Plano_Viagem"));
        edVolta.setTX_Observacao(res.getString("TX_Observacao"));

        edVolta.setNM_Origem(res.getString("NM_Origem"));
        edVolta.setNM_Destino(res.getString("NM_Destino"));

        edVolta.setDM_Plano_Viagem(res.getString("DM_Plano_Viagem"));
        edVolta.setNM_Plano_Viagem(this.getNM_Plano(res.getString("DM_Plano_Viagem")));

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


  public void inclui(Plano_ViagemED ed) throws Excecoes{

    String sql = null;
    long valOid = 0;
    String chave = null;
    ResultSet res2 = null;

    try{

      // System.out.println( "Plano_Viagem inclui 4 " );

      chave = String.valueOf(System.currentTimeMillis()).toString();

      sql = " insert into Planos_Viagens (OID_Plano_Viagem, OID_Manifesto, OID_Ordem_Frete, DT_Plano_Viagem, HR_Plano_Viagem, DM_Plano_Viagem, NM_Origem, NM_Destino, TX_Observacao, oid_Veiculo, oid_Coleta ) values ";
      sql += "('" + chave + "','" + ed.getOID_Manifesto() + "','" + ed.getOID_Ordem_Frete() + "','"  + ed.getDT_Plano_Viagem() + "','" + ed.getHR_Plano_Viagem() + "','" + ed.getDM_Plano_Viagem()+ "','" + ed.getNM_Origem()+ "','" + ed.getNM_Destino() + "','" + ed.getTX_Observacao()+ "','" + ed.getOID_Veiculo() + "'," + ed.getOID_Coleta() +")";

                // System.out.println( "plano viagem " + sql);

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

  public String getNM_Plano(String dm_plano)throws Excecoes{

    // System.out.println("getNM_Plano w4 " );
    String nm_plano="";
    if ("I".equals(dm_plano)) nm_plano="Iníc.Viagem";
    if ("P".equals(dm_plano)) nm_plano="Pernoite";
    if ("A".equals(dm_plano)) nm_plano="Abastecimento";
    if ("B".equals(dm_plano)) nm_plano="Alimentação";
    if ("V".equals(dm_plano)) nm_plano="Em Viagem";
    if ("M".equals(dm_plano)) nm_plano="Manutenção";
    if ("F".equals(dm_plano)) nm_plano="Fiscalização";
    if ("R".equals(dm_plano)) nm_plano="Aduana";
    if ("C".equals(dm_plano)) nm_plano="Carregamento";
    if ("D".equals(dm_plano)) nm_plano="Descarga";
    if ("O".equals(dm_plano)) nm_plano="Outros";
    if ("X".equals(dm_plano)) nm_plano="Disponível";
    return nm_plano;
  }


}
