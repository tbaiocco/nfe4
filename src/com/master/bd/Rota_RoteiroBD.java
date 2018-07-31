package com.master.bd;

import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;

import com.master.ed.Rota_RoteiroED;
import com.master.rl.Rota_RoteiroRL;
import com.master.root.FormataDataBean;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

public class Rota_RoteiroBD {

  private ExecutaSQL executasql;

  public Rota_RoteiroBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public Rota_RoteiroED inclui(Rota_RoteiroED ed) throws Excecoes{

    String sql = null;
    long valOid = 0;
    String chave = null;
    Rota_RoteiroED Rota_RoteiroED = new Rota_RoteiroED();

    try{

//      ResultSet rs = executasql.executarConsulta("select max(oid_Rota_Roteiro) as result from Rotas_Roteiros");
//
//      while (rs.next()) valOid = rs.getInt("result");
        long Nr_Sort = (new Long(String.valueOf(System.currentTimeMillis()).toString().substring(3,12)).longValue());


      chave = String.valueOf(Nr_Sort);

      ed.setOID_Rota_Roteiro(chave);

      sql = " insert into Rotas_Roteiros (OID_Rota_Roteiro, CD_Roteiro, OID_Rota, NR_Sequencia ) values ";
      sql += "('" + ed.getOID_Rota_Roteiro() + "','" + ed.getCD_Roteiro() + "'," + ed.getOID_Rota() + "," + ed.getNR_Sequencia() + ")";

      // System.out.println(sql);

      int i = executasql.executarUpdate(sql);
      Rota_RoteiroED.setOID_Rota_Roteiro(ed.getOID_Rota_Roteiro());
      Rota_RoteiroED.setCD_Roteiro(ed.getCD_Roteiro());

    }
      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao incluir");
        excecoes.setMetodo("inclui");
        excecoes.setExc(exc);
        throw excecoes;
      }
      return Rota_RoteiroED;
  }

  public void altera(Rota_RoteiroED ed) throws Excecoes{

    String sql = null;

    try{

      sql = " update Rotas_Roteiros set NR_Sequencia = " + ed.getNR_Sequencia();
      sql += " where oid_Rota_Roteiro = '" + ed.getOID_Rota_Roteiro() + "'";

      // System.out.println("rota roteiro ->>>>> " + sql);


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

  public void deleta(Rota_RoteiroED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from Rotas_Roteiros WHERE oid_Rota_Roteiro = ";
      sql += "'" + ed.getOID_Rota_Roteiro() + "'";

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

  public ArrayList lista(Rota_RoteiroED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();
    double km_total=0;
    String hora1 = "00:00";
    String hora2 = "00:00";

    try{
      sql = "Select "+
      "Rotas.OID_Rota, " +
      "Rotas.NR_KM, " +
      "Rotas.NR_HR, " +
      "Rotas_Roteiros.OID_Rota_Roteiro, " +
      "Rotas_Roteiros.NR_SEQUENCIA, " +
      "Roteiros.CD_Roteiro," +
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
      " Roteiros.CD_Roteiro = Rotas_Roteiros.CD_Roteiro ";

      if (String.valueOf(ed.getCD_Roteiro()) != null &&
          !String.valueOf(ed.getCD_Roteiro()).equals("") &&
          !String.valueOf(ed.getCD_Roteiro()).equals("null")){
        sql += " and Roteiros.CD_Roteiro = '" + ed.getCD_Roteiro() + "'";
      }

      if (String.valueOf(ed.getOID_Rota()) != null &&
          !String.valueOf(ed.getOID_Rota()).equals("0") &&
          !String.valueOf(ed.getOID_Rota()).equals("null")){
        sql += " and Rotas.OID_Rota = " + ed.getOID_Rota();
      }

      sql += " Order by Rotas_Roteiros.NR_SEQUENCIA, Rotas_Roteiros.OID_Rota ";
// System.out.println(sql);

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      FormataDataBean DataFormatada = new FormataDataBean();
      DecimalFormat dec = new DecimalFormat("###,###,##0.00");

      while (res.next()){
// System.out.println("1");

        Rota_RoteiroED edVolta = new Rota_RoteiroED();
        edVolta.setOID_Rota_Roteiro(res.getString("OID_Rota_Roteiro"));
        edVolta.setOID_Rota(res.getLong("OID_Rota"));
        edVolta.setNM_Cidade_Origem(res.getString("NM_Cidade_Origem"));
        edVolta.setNM_Cidade_Destino(res.getString("NM_Cidade_Destino"));
        edVolta.setCD_Estado_Origem(res.getString("CD_Estado_Origem"));
        edVolta.setCD_Estado_Destino(res.getString("CD_Estado_Destino"));
        edVolta.setCD_Roteiro(res.getString("CD_Roteiro"));
// System.out.println("2");

        edVolta.setNR_Sequencia(res.getLong("NR_Sequencia"));

// System.out.println("3");

	String sqlBusca = "select dm_apoio_rastreado from apoios where 1 = 1 ";
	if (String.valueOf(edVolta.getOID_Rota()) != null &&
          !String.valueOf(edVolta.getOID_Rota()).equals("0") &&
          !String.valueOf(edVolta.getOID_Rota()).equals("null")){
        sqlBusca += " and apoios.OID_Rota = " + edVolta.getOID_Rota();
        }
// System.out.println("5");

	ResultSet resLocal = this.executasql.executarConsulta(sqlBusca);
	edVolta.setDM_Rastreado("NÃO");
// System.out.println("5 2");


	while (resLocal.next()){
// System.out.println("8");

	      if (resLocal.getString("dm_apoio_rastreado").equals("S"))
// System.out.println("9");


		  edVolta.setDM_Rastreado("SIM");
	      }

// System.out.println("15");

        km_total = km_total + res.getDouble("NR_KM");
        edVolta.setNR_KM(dec.format(km_total));
        hora1 = res.getString("NR_HR");

	int fim1 = hora1.length()-3;
	int fim2 = hora2.length()-3;
	int inicio1 = hora1.length()-2;
	int inicio2 = hora2.length()-2;

        int horaTotal = Integer.parseInt(hora1.substring(0,fim1)) + Integer.parseInt(hora2.substring(0,fim2));
        int minutoTotal = Integer.parseInt(hora1.substring(inicio1,hora1.length())) + Integer.parseInt(hora2.substring(inicio2,hora2.length()));

        boolean quebra = true;
        while(quebra){
          if (minutoTotal >= 60){
            horaTotal = horaTotal + 1;
            minutoTotal = minutoTotal - 60;
          }else{
            quebra = false;
          }
        }
        if (horaTotal < 10){
          hora2 = "0" + horaTotal + ":";
          if (minutoTotal < 10){
            hora2 = hora2 + "0" + minutoTotal;
          }else{
            hora2 = hora2 + minutoTotal;
          }
        }else{
          hora2 = horaTotal + ":";
          if (minutoTotal < 10){
            hora2 = hora2 + "0" + minutoTotal;
          }else{
            hora2 = hora2 + minutoTotal;
          }
        }

        edVolta.setNR_HR(hora2);

        list.add(edVolta);
      }
    }
      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
exc.printStackTrace();
        excecoes.setMensagem("Erro ao listar");
        excecoes.setMetodo("listar");
        excecoes.setExc(exc);
        throw excecoes;
      }

    return list;
  }

  public Rota_RoteiroED getByRecord(Rota_RoteiroED ed)throws Excecoes{

    String sql = null;

    Rota_RoteiroED edVolta = new Rota_RoteiroED();

    try{

      sql = "Select "+
      "Rotas.OID_Rota, " +
      "Rotas_Roteiros.OID_Rota_Roteiro, " +
      "Rotas_Roteiros.NR_Sequencia, " +
      "Roteiros.CD_Roteiro," +
      "Cidade_Remetente.CD_Cidade as CD_Cidade_Origem, "+
      "Cidade_Destinatario.CD_Cidade as CD_Cidade_Destino, "+
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
      " Roteiros.CD_Roteiro = Rotas_Roteiros.CD_Roteiro ";

      if (String.valueOf(ed.getOID_Rota_Roteiro()) != null &&
          !String.valueOf(ed.getOID_Rota_Roteiro()).equals("0")){
        sql += " and Rotas_Roteiros.OID_Rota_Roteiro = '" + ed.getOID_Rota_Roteiro() + "'";
      }

      FormataDataBean DataFormatada = new FormataDataBean();

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);
      while (res.next()){

        edVolta.setOID_Rota_Roteiro(res.getString("OID_Rota_Roteiro"));
        edVolta.setNR_Sequencia(res.getLong("NR_Sequencia"));
        edVolta.setOID_Rota(res.getLong("OID_Rota"));
        edVolta.setNM_Cidade_Origem(res.getString("NM_Cidade_Origem"));
        edVolta.setNM_Cidade_Destino(res.getString("NM_Cidade_Destino"));
        edVolta.setCD_Cidade_Origem(res.getString("CD_Cidade_Origem"));
        edVolta.setCD_Cidade_Destino(res.getString("CD_Cidade_Destino"));
        edVolta.setCD_Roteiro(res.getString("CD_Roteiro"));
        edVolta.setCD_Estado_Origem(res.getString("CD_Estado_Origem"));
        edVolta.setCD_Estado_Destino(res.getString("CD_Estado_Destino"));
      }
    }
      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao selecionar");
        excecoes.setMetodo("seleção");
        excecoes.setExc(exc);
        throw excecoes;
      }

    return edVolta;
  }

  public void geraRelatorio(Rota_RoteiroED ed)throws Excecoes{

    String sql = null;

    Rota_RoteiroED edVolta = new Rota_RoteiroED();

    try{

      sql = "select * from Rota_Roteiros where oid_Rota_Roteiro > 0";

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      Rota_RoteiroRL Rota_Roteiro_rl = new Rota_RoteiroRL();
      Rota_Roteiro_rl.geraRelatEstoque(res);
    }
    catch (Excecoes e){
      throw e;
    }
    catch(Exception exc){
      Excecoes exce = new Excecoes();
      exce.setExc(exc);
      exce.setMensagem("Erro no método listar");
      exce.setClasse(this.getClass().getName());
      exce.setMetodo("geraRelatorio(Rota_RoteiroED ed)");
    }
  }
}
