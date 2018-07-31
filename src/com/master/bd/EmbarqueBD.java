package com.master.bd;

import java.awt.image.RescaleOp;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.*;

import com.master.ed.*;
import com.master.rl.*;
import com.master.root.*;
import com.master.util.*;
import com.master.util.bd.*;

public class EmbarqueBD {

  private ExecutaSQL executasql;
  Utilitaria util = new Utilitaria();

  public EmbarqueBD(ExecutaSQL sql) {
    this.executasql = sql;
  }
 
  public EmbarqueED inclui(EmbarqueED ed) throws Excecoes{

    String sql = null;
    long valOid = 0;
    String chave = null;

    EmbarqueED manED = new EmbarqueED();
    try{

        //Verifica se veiculo tem algum embarque sem data de chegada
        //Somente se nao for Meio proprio e III000
        if(!ed.getOID_Veiculo().equals("M.PROPRIO") && !ed.getOID_Veiculo().equals("III0000")){
	        sql = "select dt_chegada, nr_embarque from Embarques where oid_veiculo = '" + ed.getOID_Veiculo() + "'";
	        ResultSet rsVeiculo = executasql.executarConsulta(sql);

	        while (rsVeiculo.next()){
	            if(!JavaUtil.doValida(rsVeiculo.getString("dt_chegada"))){
	                throw new Excecoes("Veiculo ainda possui Embarque (nr. "+rsVeiculo.getString("nr_embarque")+ ") sem data de Chegada.", getClass().getName(), "inclui(EmbarqueED ed)");
	            }
	        }
        }

      ResultSet rs = executasql.executarConsulta("select max(oid_Embarque) as result from Embarques");

      //pega proximo valor da chave
      while (rs.next()) valOid = rs.getInt("result");
      valOid = valOid+1;

      ed.setNR_Embarque(valOid);
      ed.setOID_Embarque(valOid);

      sql =  " insert into Embarques (OID_Embarque, NR_Embarque, NR_Placa, NR_Celular, OID_Cidade, OID_Cidade_Destino, OID_Cidade_Apoio, DM_Expresso, NM_Motorista, DM_Situacao, DT_Emissao, OID_Veiculo, OID_Veiculo_Carreta, DM_Carga_Veiculo, CD_Roteiro, nr_odometro_inicial, DM_Envio_EDI, nr_chassi, nr_carro ) values ";
      sql += "(" + ed.getOID_Embarque() + "," + ed.getNR_Embarque() + ",'" + ed.getNR_Placa() + "','" + ed.getNR_Celular() + "'," + ed.getOID_Cidade_Origem() + "," + ed.getOID_Cidade_Destino() + "," + ed.getOID_Cidade_Origem() + ",'" + ed.getDM_Expresso() + "','" + ed.getNM_Motorista() + "','" + ed.getDM_Situacao() + "','" + ed.getDT_Emissao()  + "','" + ed.getOID_Veiculo() + "','" + ed.getOID_Veiculo_Carreta() + "','" + ed.getDM_Carga_Veiculo() + "','" + ed.getCD_Roteiro() + "', " + ed.getNR_Odometro_Inicial() + ",'N', '" + ed.getNR_Chassi() + "', '" + ed.getNR_Carro() + "'" + ")";

// System.out.println("embarque: "+sql);

      int i = executasql.executarUpdate(sql);
      manED.setOID_Embarque(ed.getOID_Embarque());
 // System.out.println("ok");

 		VeiculoBean v = VeiculoBean.getByOID(ed.getOID_Veiculo());

//      sql = " update Veiculos set OID_Embarque = " + ed.getOID_Embarque() + " where oid_veiculo = '" + ed.getOID_Veiculo() + "'";
//      i = executasql.executarUpdate(sql);
 		if (JavaUtil.doValida(v.getDM_Procedencia()) && !v.getDM_Procedencia().equals("O")){
 		   sql = " update Veiculos_Embarques set DM_Situacao = 'E'" + " where oid_veiculo = '" + ed.getOID_Veiculo() + "'" + " and DM_Situacao = 'A'";
 		   i = executasql.executarUpdate(sql);
 		}

		sql =  " insert into Veiculos_Embarques (OID_Embarque, OID_Veiculo, DM_Situacao) values ";
		sql += "(" + ed.getOID_Embarque() + ",'" + ed.getOID_Veiculo() + "','A'"+ ")";

		i = executasql.executarUpdate(sql);

		// Tracking das carretas
		if(JavaUtil.doValida(ed.getOID_Veiculo_Carreta())){
			v = VeiculoBean.getByOID(ed.getOID_Veiculo_Carreta());
			if (JavaUtil.doValida(v.getDM_Procedencia()) && (!v.getDM_Procedencia().equals("O") && (!ed.getOID_Veiculo_Carreta().equals("M.PROPRIO") && !ed.getOID_Veiculo_Carreta().equals("III0000")))){
			    sql = "select * from Veiculos_Embarques where oid_veiculo = '" + ed.getOID_Veiculo_Carreta() + "'  and DM_Situacao = 'A'";
		        ResultSet rsCarreta = executasql.executarConsulta(sql);
		        while (rsCarreta.next()){
		            sql = " update Embarques set DM_situacao= '8',";
		            sql += " DT_Entrega = '" + Data.getDataDMY() + "'," +
		                   " HR_Entrega = '" + Data.getHoraHM() + "' " +
		                   " where oid_embarque = " + rsCarreta.getString("oid_embarque");
		            i = executasql.executarUpdate(sql);
		        }
	 		   	sql = " update Veiculos_Embarques set DM_Situacao = 'E'" + " where oid_veiculo = '" + ed.getOID_Veiculo_Carreta() + "'" + " and DM_Situacao = 'A'";
	 		   	i = executasql.executarUpdate(sql);
	 		}
		}

    }
    catch(Excecoes e){
        e.printStackTrace();
        throw e;
      }
    catch(Exception exc){
        exc.printStackTrace();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir Embarque");
      excecoes.setMetodo("inclui");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return manED;

  }

  public void altera(EmbarqueED ed) throws Excecoes{

    String sql = null;

    try{

	  if (String.valueOf(ed.getDT_Saida()) != null &&
        !String.valueOf(ed.getDT_Saida()).equals("") &&
        !String.valueOf(ed.getDT_Saida()).equals("null") &&
		String.valueOf(ed.getDT_Previsao_Chegada()) != null &&
        !String.valueOf(ed.getDT_Previsao_Chegada()).equals("") &&
        !String.valueOf(ed.getDT_Previsao_Chegada()).equals("null")){

        ed.setDM_Situacao("3");
      }

      sql = " update Embarques set DM_Expresso= '" + ed.getDM_Expresso() + "',";

      if (String.valueOf(ed.getDT_Saida()) != null &&
        !String.valueOf(ed.getDT_Saida()).equals("") &&
        !String.valueOf(ed.getDT_Saida()).equals("null") &&
        String.valueOf(ed.getDT_Saida_Anterior()) != null &&
        String.valueOf(ed.getDT_Saida_Anterior()).equals("")){
        sql += " DT_Saida = '" + ed.getDT_Saida() + "'," +
               " HR_Saida = '" + ed.getHR_Saida() + "',";
      }else if (
        String.valueOf(ed.getDT_Chegada()) != null &&
        !String.valueOf(ed.getDT_Chegada()).equals("") &&
        !String.valueOf(ed.getDT_Chegada()).equals("null") &&
        String.valueOf(ed.getDT_Chegada_Anterior()) != null &&
        String.valueOf(ed.getDT_Chegada_Anterior()).equals("")){
        ed.setDM_Situacao("7");
        sql += " DT_Chegada = '" + ed.getDT_Chegada() + "'," +
               " HR_Chegada = '" + ed.getHR_Chegada() + "',";
      }else if (
        String.valueOf(ed.getDT_Entrega()) != null &&
        !String.valueOf(ed.getDT_Entrega()).equals("") &&
        !String.valueOf(ed.getDT_Entrega()).equals("null") &&
        String.valueOf(ed.getDT_Entrega_Anterior()) != null &&
        String.valueOf(ed.getDT_Entrega_Anterior()).equals("")){
        ed.setDM_Situacao("8");
        sql += " DT_Entrega = '" + ed.getDT_Entrega() + "'," +
               " HR_Entrega = '" + ed.getHR_Entrega() + "',";
      }

      if (String.valueOf(ed.getDT_Previsao_Chegada()) != null &&
        !String.valueOf(ed.getDT_Previsao_Chegada()).equals("") &&
        !String.valueOf(ed.getDT_Previsao_Chegada()).equals("null") &&
        String.valueOf(ed.getDT_Previsao_Chegada_Anterior()) != null &&
        String.valueOf(ed.getDT_Previsao_Chegada_Anterior()).equals("")){

        ed.setDM_Situacao("3");
        sql +=  " DT_Previsao_Chegada = '" + ed.getDT_Previsao_Chegada() + "'," +
                " HR_Previsao_Chegada = '" + ed.getHR_Previsao_Chegada() + "',";
      }

      if (String.valueOf(ed.getDT_Nova_Previsao_Chegada()) != null &&
        !String.valueOf(ed.getDT_Nova_Previsao_Chegada()).equals("") &&
        !String.valueOf(ed.getDT_Nova_Previsao_Chegada()).equals("null")){
        sql +=  " DT_Nova_Previsao_Chegada = '" + ed.getDT_Nova_Previsao_Chegada() + "'," +
                " HR_Nova_Previsao_Chegada = '" + ed.getHR_Nova_Previsao_Chegada() + "',";
      }

      if (ed.getOID_Veiculo_Carreta() != null)
          sql += " Oid_Veiculo_Carreta= '" + ed.getOID_Veiculo_Carreta() + "',";
      else
        sql += " Oid_Veiculo_Carreta = null,";

      if (JavaUtil.doValida(ed.getOID_Manifesto()))
          sql += " Oid_Manifesto= '" + ed.getOID_Manifesto() + "',";

      if (ed.getCD_Roteiro() != null)
        sql += " CD_Roteiro = '" + ed.getCD_Roteiro() + "', ";
      else
        sql += " CD_Roteiro = null, ";

      sql += " NM_Motorista = '" + ed.getNM_Motorista() + "'," +
             " Oid_Veiculo= '" + ed.getOID_Veiculo() + "'," +
             " NR_Celular = '" + ed.getNR_Celular() + "'," +
             " NR_Placa = '" + ed.getNR_Placa() + "'," +
             " DM_Situacao = '" + ed.getDM_Situacao() + "'," +
             " DM_Carga_Veiculo = '" + ed.getDM_Carga_Veiculo() + "'";

      sql += ", nr_odometro_inicial = " + ed.getNR_Odometro_Inicial();

      sql += ", nr_chassi = '" + ed.getNR_Chassi() + "' ";
      sql += ", nr_carro = '" + ed.getNR_Carro() + "' ";

      sql += " where oid_Embarque = " + ed.getOID_Embarque();

// System.out.println( " ===== " + sql);

      int i = executasql.executarUpdate(sql);

      if (JavaUtil.doValida(ed.getOID_Manifesto())){
          if (ed.getCD_Roteiro() != null){
              this.verificaMIC(ed);
          }
      }

    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar Embarque");
      excecoes.setMetodo("alterar");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void deleta(EmbarqueED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from Embarques WHERE oid_Embarque = ";
      sql += "(" + ed.getOID_Embarque() + ")";

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao excluir Embarque");
      excecoes.setMetodo("excluir");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public ArrayList lista(EmbarqueED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();

    try{

      sql =  "SELECT "+
      " distinct(Embarques.OID_Embarque),  "+
      " Embarques.NR_Embarque,  "+
      " Embarques.DM_Carga_Veiculo,  "+
      " Embarques.NR_Placa,  "+
      " Embarques.OID_Veiculo_Carreta,  "+
      " Embarques.NM_Motorista, "+
      " Embarques.NR_Celular,  "+
      " Embarques.DM_Expresso,  "+
      " Embarques.DM_Situacao, "+
      " Embarques.DT_Previsao_Chegada, "+
      " Embarques.HR_Previsao_Chegada, "+
      " Embarques.DT_Nova_Previsao_Chegada, "+
      " Embarques.HR_Nova_Previsao_Chegada, "+
      " Embarques.DT_Chegada, "+
      " Embarques.HR_Chegada, "+
      " Embarques.DT_Emissao,  "+
      " Embarques.nr_odometro_inicial, "+ //Teo 16/09
      " Tipos_Veiculos.NM_TIPO_VEICULO,  "+
      " Cidade_Origem.NM_Cidade as NM_Cidade_Origem, "+
      " Cidade_Destino.NM_Cidade as NM_Cidade_Destino,  "+
      " estados.cd_estado, estados.nm_estado "+
      " from  "+
      " Embarques,  "+
      " Veiculos,  "+
      " Modelos_Veiculos,  "+
      " Tipos_Veiculos,  "+
      " Complementos_Veiculos,  "+
      " Estados,  "+
      " Regioes_Estados,  "+
      " Cidades Cidade_Origem,  "+
      " Cidades Cidade_Destino ";
      sql += " WHERE Embarques.OID_Cidade_Destino = Cidade_Destino.OID_Cidade ";
      sql += " AND Embarques.OID_VEICULO = Veiculos.OID_VEICULO ";
      sql += " AND Veiculos.OID_MODELO_VEICULO = Modelos_Veiculos.OID_MODELO_VEICULO ";
      sql += " AND Tipos_Veiculos.OID_TIPO_VEICULO = Modelos_Veiculos.OID_TIPO_VEICULO ";
      sql += " AND Veiculos.OID_VEICULO = Complementos_Veiculos.OID_VEICULO ";
      sql += " AND Embarques.OID_Cidade = Cidade_Origem.OID_Cidade ";
      sql += " and cidade_destino.oid_regiao_estado = regioes_estados.oid_regiao_estado  ";
      sql += " and regioes_estados.oid_estado = estados.oid_estado ";

      if ((String.valueOf(ed.getOID_Cidade_Apoio()) != null &&
          !String.valueOf(ed.getOID_Cidade_Apoio()).equals("null") &&
          !String.valueOf(ed.getOID_Cidade_Apoio()).equals("0")) ||
          (String.valueOf(ed.getOID_Rota()) != null &&
          !String.valueOf(ed.getOID_Rota()).equals("null") &&
          !String.valueOf(ed.getOID_Rota()).equals("0"))){
        sql =  "SELECT "+
        " Embarques.OID_Embarque,  "+
        " Embarques.NR_Embarque,  "+
        " Embarques.DM_Carga_Veiculo,  "+
        " Embarques.NR_Placa,  "+
        " Embarques.OID_Veiculo_Carreta,  "+
        " Embarques.NM_Motorista, "+
        " Embarques.NR_Celular,  "+
        " Embarques.DM_Expresso,  "+
        " Embarques.DM_Situacao, "+
        " Embarques.DT_Previsao_Chegada, "+
        " Embarques.HR_Previsao_Chegada, "+
        " Embarques.DT_Nova_Previsao_Chegada, "+
        " Embarques.HR_Nova_Previsao_Chegada, "+
        " Embarques.DT_Chegada, "+
        " Embarques.HR_Chegada, "+
        " Embarques.DT_Emissao,  "+
	" Embarques.nr_odometro_inicial, "+ //Teo 16/09
        " Tipos_Veiculos.NM_TIPO_VEICULO,  "+
        " Cidade_Origem.NM_Cidade as NM_Cidade_Origem, "+
        " Cidade_Destino.NM_Cidade as NM_Cidade_Destino  "+
        " from  "+
        " Embarques,  "+
        " Roteiros,  "+
        " Rotas_Roteiros,  "+
        " Rotas,  "+
        " Veiculos,  "+
        " Modelos_Veiculos,  "+
        " Tipos_Veiculos,  "+
        " Complementos_Veiculos,  "+
        " Estados,  "+
        " Regioes_Estados,  "+
        " Cidades Cidade_Origem,  "+
        " Cidades Cidade_Destino ";
        sql += " WHERE Embarques.OID_Cidade_Destino = Cidade_Destino.OID_Cidade ";
        sql += " AND Embarques.OID_VEICULO = Veiculos.OID_VEICULO ";
        sql += " AND Veiculos.OID_MODELO_VEICULO = Modelos_Veiculos.OID_MODELO_VEICULO ";
        sql += " AND Tipos_Veiculos.OID_TIPO_VEICULO = Modelos_Veiculos.OID_TIPO_VEICULO ";
        sql += " AND Veiculos.OID_VEICULO = Complementos_Veiculos.OID_VEICULO ";
        sql += " AND Embarques.OID_Cidade = Cidade_Origem.OID_Cidade ";
        sql += " AND Embarques.CD_ROTEIRO = Roteiros.CD_ROTEIRO ";
        sql += " AND Rotas.OID_Rota = Rotas_Roteiros.OID_Rota ";
        sql += " AND Roteiros.CD_Roteiro = Rotas_Roteiros.CD_Roteiro ";
      sql += " and cidade_destino.oid_regiao_estado = regioes_estados.oid_regiao_estado  ";
      sql += " and regioes_estados.oid_estado = estados.oid_estado ";
// System.out.println("opcao 3");

      }

      if ((String.valueOf(ed.getOID_Tipo_Veiculo_Carreta()) != null &&
          !String.valueOf(ed.getOID_Tipo_Veiculo_Carreta()).equals("null") &&
          !String.valueOf(ed.getOID_Tipo_Veiculo_Carreta()).equals("0"))){
        sql =  "SELECT "+
        " Embarques.OID_Embarque,  "+
        " Embarques.NR_Embarque,  "+
        " Embarques.DM_Carga_Veiculo,  "+
        " Embarques.NR_Placa,  "+
        " Embarques.OID_Veiculo_Carreta,  "+
        " Embarques.NM_Motorista, "+
        " Embarques.NR_Celular,  "+
        " Embarques.DM_Expresso,  "+
        " Embarques.DM_Situacao, "+
        " Embarques.DT_Previsao_Chegada, "+
        " Embarques.HR_Previsao_Chegada, "+
        " Embarques.DT_Nova_Previsao_Chegada, "+
        " Embarques.HR_Nova_Previsao_Chegada, "+
        " Embarques.DT_Chegada, "+
        " Embarques.HR_Chegada, "+
        " Embarques.DT_Emissao,  "+
	" Embarques.nr_odometro_inicial, "+ //Teo 16/09
        " Tipos_Veiculos.NM_TIPO_VEICULO,  "+
        " Cidade_Origem.NM_Cidade as NM_Cidade_Origem, "+
        " Cidade_Destino.NM_Cidade as NM_Cidade_Destino  "+
        " from  "+
        " Embarques,  "+
        " Veiculos,  "+
        " Modelos_Veiculos,  "+
        " Tipos_Veiculos,  "+
        " Estados,  "+
        " Regioes_Estados,  "+
        " Complementos_Veiculos,  "+
        " Cidades Cidade_Origem,  "+
        " Cidades Cidade_Destino ";
        sql += " WHERE Embarques.OID_Cidade_Destino = Cidade_Destino.OID_Cidade ";
        sql += " AND Embarques.OID_VEICULO_Carreta = Veiculos.OID_VEICULO ";
        sql += " AND Veiculos.OID_MODELO_VEICULO = Modelos_Veiculos.OID_MODELO_VEICULO ";
        sql += " AND Tipos_Veiculos.OID_TIPO_VEICULO = Modelos_Veiculos.OID_TIPO_VEICULO ";
        sql += " AND Veiculos.OID_VEICULO = Complementos_Veiculos.OID_VEICULO ";
        sql += " AND Embarques.OID_Cidade = Cidade_Origem.OID_Cidade ";
      sql += " and cidade_destino.oid_regiao_estado = regioes_estados.oid_regiao_estado  ";
      sql += " and regioes_estados.oid_estado = estados.oid_estado ";
// System.out.println("opcao 4");

      }

      if  (String.valueOf(ed.getOID_Rota()) != null &&
          !String.valueOf(ed.getOID_Rota()).equals("null") &&
          !String.valueOf(ed.getOID_Rota()).equals("0")){
        sql += " and Rotas.OID_Rota = " + ed.getOID_Rota();
      }

      if (String.valueOf(ed.getOID_Cidade_Apoio()) != null &&
          !String.valueOf(ed.getOID_Cidade_Apoio()).equals("null") &&
          !String.valueOf(ed.getOID_Cidade_Apoio()).equals("0")){
        sql += " and Embarques.OID_Cidade_Apoio = " + ed.getOID_Cidade_Apoio();
      }

      if (String.valueOf(ed.getDM_Carga_Veiculo()) != null &&
        !String.valueOf(ed.getDM_Carga_Veiculo()).equals("") &&
        !String.valueOf(ed.getDM_Carga_Veiculo()).equals("T") &&
        !String.valueOf(ed.getDM_Carga_Veiculo()).equals("null")){
        sql += " and Embarques.DM_Carga_Veiculo = '" + ed.getDM_Carga_Veiculo() + "'";
      }

      if (String.valueOf(ed.getDM_Tipo_Frota()) != null &&
        !String.valueOf(ed.getDM_Tipo_Frota()).equals("") &&
        !String.valueOf(ed.getDM_Tipo_Frota()).equals("D") &&
        !String.valueOf(ed.getDM_Tipo_Frota()).equals("null")){
        sql += " and Complementos_Veiculos.DM_PROCEDENCIA = '" + ed.getDM_Tipo_Frota() + "'";
      }

      if (String.valueOf(ed.getNM_Motorista()) != null &&
        !String.valueOf(ed.getNM_Motorista()).equals("") &&
        !String.valueOf(ed.getNM_Motorista()).equals("null")){
        sql += " and Embarques.NM_Motorista = '" + ed.getNM_Motorista() + "'";
      }
      if (String.valueOf(ed.getDM_Situacao()) != null &&
        !String.valueOf(ed.getDM_Situacao()).equals("") &&
        !String.valueOf(ed.getDM_Situacao()).equals("null") &&
        !String.valueOf(ed.getDM_Situacao()).equals("T")){
        sql += " and Embarques.DM_Situacao = '" + ed.getDM_Situacao() + "'";
      }
      if (String.valueOf(ed.getDT_Emissao()) != null &&
        !String.valueOf(ed.getDT_Emissao()).equals("") &&
        !String.valueOf(ed.getDT_Emissao()).equals("null")){
        sql += " and Embarques.DT_Emissao >= '" + ed.getDT_Emissao() + "'";
      }
      if (String.valueOf(ed.getDT_Emissao_Final()) != null &&
        !String.valueOf(ed.getDT_Emissao_Final()).equals("") &&
        !String.valueOf(ed.getDT_Emissao_Final()).equals("null")){
        sql += " and Embarques.DT_Emissao <= '" + ed.getDT_Emissao_Final() + "'";
      }
      if (String.valueOf(ed.getDT_Previsao_Chegada()) != null &&
        !String.valueOf(ed.getDT_Previsao_Chegada()).equals("") &&
        !String.valueOf(ed.getDT_Previsao_Chegada()).equals("null")){
        sql += " and Embarques.DT_Previsao_Chegada >= '" + ed.getDT_Previsao_Chegada() + "'";
      }
      if (String.valueOf(ed.getDT_Previsao_Chegada_Final()) != null &&
        !String.valueOf(ed.getDT_Previsao_Chegada_Final()).equals("") &&
        !String.valueOf(ed.getDT_Previsao_Chegada_Final()).equals("null")){
        sql += " and Embarques.DT_Previsao_Chegada <= '" + ed.getDT_Previsao_Chegada_Final() + "'";
      }
      if (String.valueOf(ed.getDT_Entrega()) != null &&
        !String.valueOf(ed.getDT_Entrega()).equals("") &&
        !String.valueOf(ed.getDT_Entrega()).equals("null")){
        sql += " and Embarques.DT_Entrega >= '" + ed.getDT_Entrega() + "'";
      }
      if (String.valueOf(ed.getDT_Entrega_Final()) != null &&
        !String.valueOf(ed.getDT_Entrega_Final()).equals("") &&
        !String.valueOf(ed.getDT_Entrega_Final()).equals("null")){
        sql += " and Embarques.DT_Entrega <= '" + ed.getDT_Entrega_Final() + "'";
      }

      if (String.valueOf(ed.getNR_Embarque()) != null &&
          !String.valueOf(ed.getNR_Embarque()).equals("0") &&
          !String.valueOf(ed.getNR_Embarque()).equals("null")){
        sql += " and Embarques.NR_Embarque = " + ed.getNR_Embarque();
      }

      if (String.valueOf(ed.getOID_Cidade_Destino()) != null &&
          !String.valueOf(ed.getOID_Cidade_Destino()).equals("0") &&
          !String.valueOf(ed.getOID_Cidade_Destino()).equals("null")){
        sql += " and Embarques.OID_Cidade_Destino = " + ed.getOID_Cidade_Destino();
      }

      if (String.valueOf(ed.getOID_Cidade_Origem()) != null &&
          !String.valueOf(ed.getOID_Cidade_Origem()).equals("0") &&
          !String.valueOf(ed.getOID_Cidade_Origem()).equals("null")){
        sql += " and Embarques.OID_Cidade = " + ed.getOID_Cidade_Origem();
      }
      if (String.valueOf(ed.getOID_Tipo_Veiculo()) != null &&
          !String.valueOf(ed.getOID_Tipo_Veiculo()).equals("0") &&
          !String.valueOf(ed.getOID_Tipo_Veiculo()).equals("null")){
        sql += " and Tipos_Veiculos.OID_Tipo_Veiculo = " + ed.getOID_Tipo_Veiculo();
      }

      if (String.valueOf(ed.getNR_Placa()) != null &&
          !String.valueOf(ed.getNR_Placa()).equals("") &&
          !String.valueOf(ed.getNR_Placa()).equals("null")){
        sql += " and Embarques.OID_Veiculo = '" + ed.getNR_Placa() + "'";
      }

      if (String.valueOf(ed.getNR_Placa_Carreta()) != null &&
          !String.valueOf(ed.getNR_Placa_Carreta()).equals("") &&
          !String.valueOf(ed.getNR_Placa_Carreta()).equals("null")){
        sql += " and Embarques.OID_Veiculo_Carreta = '" + ed.getNR_Placa_Carreta() + "'";
      }

      if (String.valueOf(ed.getNR_Celular()) != null &&
          !String.valueOf(ed.getNR_Celular()).equals("") &&
          !String.valueOf(ed.getNR_Celular()).equals("null")){
        sql += " and Embarques.NR_Celular = '" + ed.getNR_Celular() + "'";
      }
      if ((String.valueOf(ed.getOID_Tipo_Veiculo_Carreta()) != null &&
          !String.valueOf(ed.getOID_Tipo_Veiculo_Carreta()).equals("null") &&
          !String.valueOf(ed.getOID_Tipo_Veiculo_Carreta()).equals("0"))){
	  sql += " and Tipos_Veiculos.OID_Tipo_Veiculo = " + ed.getOID_Tipo_Veiculo_Carreta();
      }

      if ((String.valueOf(ed.getOID_Estado_Destino()) != null &&
          !String.valueOf(ed.getOID_Estado_Destino()).equals("null") &&
          !String.valueOf(ed.getOID_Estado_Destino()).equals("0"))){
	  sql += " and estados.oid_estado = " + ed.getOID_Estado_Destino();
      }
//MIC e Manifesto
      if (String.valueOf(ed.getNR_MIC()) != null &&
          !String.valueOf(ed.getNR_MIC()).equals("") &&
          !String.valueOf(ed.getNR_MIC()).equals("null")){
            sql += " and embarques.oid_manifesto = Manifestos_Internacionais.oid_Manifesto_Internacional";
            sql += " and Manifestos_Internacionais.NR_Manifesto_Internacional = '" + ed.getNR_MIC() + "'";
      }
      if (String.valueOf(ed.getNR_Manifesto()) != null &&
          !String.valueOf(ed.getNR_Manifesto()).equals("") &&
          !String.valueOf(ed.getNR_Manifesto()).equals("null")){
            sql += " and embarques.oid_manifesto = Manifestos.oid_Manifesto";
            sql += " and Manifestos.NR_Manifesto = '" + ed.getNR_Manifesto() + "'";
      }

//    CRT e Conhecimento
      if (String.valueOf(ed.getNR_CRT()) != null &&
          !String.valueOf(ed.getNR_CRT()).equals("") &&
          !String.valueOf(ed.getNR_CRT()).equals("null")){
            sql += " and embarques.oid_manifesto = Manifestos_Internacionais.oid_Manifesto_Internacional";
            sql += " and Manifestos_Internacionais.oid_Manifesto_Internacional = Viagens_Internacionais.oid_Manifesto_Internacional";
            sql += " and Viagens_Internacionais.oid_Conhecimento = Conhecimentos_Internacionais.oid_Conhecimento ";
            sql += " and Conhecimentos_Internacionais.NR_Conhecimento = '" + ed.getNR_CRT() + "'";
      }
      if (String.valueOf(ed.getNR_Conhecimento()) != null &&
          !String.valueOf(ed.getNR_Conhecimento()).equals("") &&
          !String.valueOf(ed.getNR_Conhecimento()).equals("null")){
            sql += " and embarques.oid_manifesto = Manifestos.oid_Manifesto";
            sql += " and viagens.oid_manifesto = Manifestos.oid_Manifesto";
            sql += " and viagens.oid_conhecimento = Conhecimentos.oid_conhecimento";
            sql += " and Conhecimentos.NR_Conhecimento = '" + ed.getNR_Conhecimento() + "'";
      }

//    Remetente de CRT
      if (JavaUtil.doValida(ed.getOID_Pessoa()) && ed.getDM_Tipo_Embarque().equals("I")){
            sql += " and embarques.oid_manifesto = Manifestos_Internacionais.oid_Manifesto_Internacional";
            sql += " and Manifestos_Internacionais.oid_Manifesto_Internacional = Viagens_Internacionais.oid_Manifesto_Internacional";
            sql += " and Viagens_Internacionais.oid_Conhecimento = Conhecimentos_Internacionais.oid_Conhecimento ";
            sql += " and Conhecimentos_Internacionais.oid_pessoa = '" + ed.getOID_Pessoa() + "'";
      }
//    Remetente de Conhecimento
      if (JavaUtil.doValida(ed.getOID_Pessoa()) && ed.getDM_Tipo_Embarque().equals("N")){
            sql += " and embarques.oid_manifesto = Manifestos.oid_Manifesto";
            sql += " and viagens.oid_manifesto = Manifestos.oid_Manifesto";
            sql += " and viagens.oid_conhecimento = Conhecimentos.oid_conhecimento";
            sql += " and Conhecimentos.oid_pessoa = '" + ed.getOID_Pessoa() + "'";
      }
//    Destinatario de CRT
      if (JavaUtil.doValida(ed.getOID_Pessoa_Destinatario()) && ed.getDM_Tipo_Embarque().equals("I")){
            sql += " and embarques.oid_manifesto = Manifestos_Internacionais.oid_Manifesto_Internacional";
            sql += " and Manifestos_Internacionais.oid_Manifesto_Internacional = Viagens_Internacionais.oid_Manifesto_Internacional";
            sql += " and Viagens_Internacionais.oid_Conhecimento = Conhecimentos_Internacionais.oid_Conhecimento ";
            sql += " and Conhecimentos_Internacionais.oid_pessoa_destinatario = '" + ed.getOID_Pessoa_Destinatario() + "'";
      }
//    Destinatario de Conhecimento
      if (JavaUtil.doValida(ed.getOID_Pessoa_Destinatario()) && ed.getDM_Tipo_Embarque().equals("N")){
            sql += " and embarques.oid_manifesto = Manifestos.oid_Manifesto";
            sql += " and viagens.oid_manifesto = Manifestos.oid_Manifesto";
            sql += " and viagens.oid_conhecimento = Conhecimentos.oid_conhecimento";
            sql += " and Conhecimentos.oid_pessoa_destinatario = '" + ed.getOID_Pessoa_Destinatario() + "'";
          }

      if (String.valueOf(ed.getNR_Chassi()) != null &&
              !String.valueOf(ed.getNR_Chassi()).equals("") &&
              !String.valueOf(ed.getNR_Chassi()).equals("null")){
            sql += " and Embarques.NR_Chassi = '" + ed.getNR_Chassi() + "'";
      }
      if (String.valueOf(ed.getNR_Carro()) != null &&
              !String.valueOf(ed.getNR_Carro()).equals("") &&
              !String.valueOf(ed.getNR_Carro()).equals("null")){
            sql += " and Embarques.NR_Carro = '" + ed.getNR_Carro() + "'";
      }


      sql += " Order by Embarques.DT_Previsao_Chegada, Embarques.DT_Nova_Previsao_Chegada ";
      ResultSet res = null;
      
// System.out.println("LISTA EMBARQUE : "+sql);

      res = this.executasql.executarConsulta(sql);
      FormataDataBean DataFormatada = new FormataDataBean();

      //popula
      while (res.next()){

// System.out.println("1");

        EmbarqueED edVolta = new EmbarqueED();

        edVolta.setOID_Embarque(res.getLong("OID_Embarque"));

        edVolta.setNR_Embarque(res.getLong("NR_Embarque"));
        edVolta.setNR_Placa(res.getString("NR_Placa"));
        edVolta.setNR_Placa_Carreta(res.getString("OID_Veiculo_Carreta"));
        edVolta.setDM_Expresso(res.getString("DM_Expresso"));
        edVolta.setDT_Emissao(res.getString("DT_Emissao"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
        edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());
        edVolta.setNM_Cidade_Origem(res.getString("NM_Cidade_Origem"));
        edVolta.setNM_Cidade_Destino(res.getString("NM_Cidade_Destino"));

	edVolta.setNR_Odometro_Inicial(res.getLong("nr_odometro_inicial")); //Teo 16/09

// System.out.println("2");

        if (res.getString("OID_Veiculo_Carreta") != null && !res.getString("OID_Veiculo_Carreta").equals("")){
          sql = " Select Tipos_Veiculos.NM_Tipo_Veiculo from Tipos_Veiculos, Modelos_Veiculos, Veiculos ";
          sql += " WHERE Veiculos.OID_MODELO_VEICULO = Modelos_Veiculos.OID_MODELO_VEICULO ";
          sql += " AND Tipos_Veiculos.OID_TIPO_VEICULO = Modelos_Veiculos.OID_TIPO_VEICULO ";
          sql += " AND Veiculos.OID_VEICULO = '" + res.getString("OID_Veiculo_Carreta") + "'";

// System.out.println("3");

          ResultSet resTP = null;
          resTP = this.executasql.executarConsulta(sql);
          while (resTP.next()){
// System.out.println("3 a ");
            edVolta.setNM_Tipo_Veiculo(resTP.getString("NM_Tipo_Veiculo"));
          }
        }else{
          edVolta.setNM_Tipo_Veiculo(res.getString("NM_Tipo_Veiculo"));
        }
        edVolta.setDT_Previsao_Chegada(res.getString("DT_Nova_Previsao_Chegada"));

        if (edVolta.getDT_Previsao_Chegada() != null ){
          DataFormatada.setDT_FormataData(edVolta.getDT_Previsao_Chegada());
          edVolta.setDT_Previsao_Chegada(DataFormatada.getDT_FormataData());

          edVolta.setHR_Previsao_Chegada(res.getString("HR_Nova_Previsao_Chegada"));
        }else{
          edVolta.setDT_Previsao_Chegada(res.getString("DT_Previsao_Chegada"));
          DataFormatada.setDT_FormataData(edVolta.getDT_Previsao_Chegada());
          edVolta.setDT_Previsao_Chegada(DataFormatada.getDT_FormataData());

          edVolta.setHR_Previsao_Chegada(res.getString("HR_Previsao_Chegada"));
        }

// System.out.println("20");

        edVolta.setDT_Chegada(res.getString("DT_Chegada"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Chegada());
        edVolta.setDT_Chegada(DataFormatada.getDT_FormataData());

        edVolta.setHR_Chegada(res.getString("HR_Chegada"));

        edVolta.setNM_Motorista(res.getString("NM_Motorista"));
        edVolta.setNR_Celular(res.getString("NR_Celular"));
        edVolta.setDM_Situacao(res.getString("DM_Situacao"));

        sql =  " SELECT Tipos_Ocorrencias.NM_Tipo_Ocorrencia, Ocorrencias_Embarques.TX_DESCRICAO, Ocorrencias_Embarques.HR_Ocorrencia_Embarque, Ocorrencias_Embarques.DT_Ocorrencia_Embarque from Ocorrencias_Embarques, Tipos_Ocorrencias ";
        sql += " WHERE Ocorrencias_Embarques.OID_Tipo_Ocorrencia = Tipos_Ocorrencias.OID_Tipo_Ocorrencia ";
        sql += " AND Ocorrencias_Embarques.OID_Embarque = " + edVolta.getOID_Embarque();
        sql += " AND Ocorrencias_Embarques.HR_OCORRENCIA_EMBARQUE is not null";
        sql += " ORDER BY Ocorrencias_Embarques.DT_OCORRENCIA_EMBARQUE, HR_OCORRENCIA_EMBARQUE asc";

// System.out.println("40");

        ResultSet resTB = null;
        resTB = this.executasql.executarConsulta(sql);

        edVolta.setNM_Ocorrencia("*");
        edVolta.setTX_Descricao("*");
        edVolta.setDT_Ocorrencia_Embarque("*");
        edVolta.setHR_Ocorrencia_Embarque("*");

        while (resTB.next()){
// System.out.println("44");

          edVolta.setTX_Descricao(resTB.getString("TX_DESCRICAO"));
          edVolta.setNM_Ocorrencia(resTB.getString("NM_Tipo_Ocorrencia"));
          edVolta.setDT_Ocorrencia_Embarque(resTB.getString("DT_Ocorrencia_Embarque"));
          DataFormatada.setDT_FormataData(edVolta.getDT_Ocorrencia_Embarque());
          edVolta.setDT_Ocorrencia_Embarque(DataFormatada.getDT_FormataData());

          edVolta.setHR_Ocorrencia_Embarque(resTB.getString("HR_Ocorrencia_Embarque"));
        }
        list.add(edVolta);
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao listar Embarque");
      excecoes.setMetodo("lista");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return list;
  }

  public ArrayList lista_Traking(EmbarqueED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();

    try{

      sql =  "SELECT "+
      " Embarques.OID_Embarque,  "+
      " Embarques.NR_Embarque,  "+
      " Embarques.NR_Placa,  "+
      " Embarques.OID_Veiculo_Carreta,  "+
      " Embarques.DM_Carga_Veiculo,  "+
      " Embarques.NM_Motorista, "+
      " Embarques.NR_Celular,  "+
      " Embarques.DM_Expresso,  "+
      " Embarques.DM_Situacao, "+
      " Embarques.DT_Previsao_Chegada, "+
      " Embarques.HR_Previsao_Chegada, "+
      " Embarques.DT_Nova_Previsao_Chegada, "+
      " Embarques.HR_Nova_Previsao_Chegada, "+
      " Embarques.DT_Chegada, "+
      " Embarques.HR_Chegada, "+
      " Embarques.DT_Emissao,  "+
      " Embarques.nr_odometro_inicial, "+ //Teo 16/09
      " Tipos_Veiculos.NM_TIPO_VEICULO,  "+
      " Cidade_Origem.NM_Cidade as NM_Cidade_Origem, "+
      " Cidade_Destino.NM_Cidade as NM_Cidade_Destino,  "+
      " estados.cd_estado, estados.nm_estado "+
      " from  "+
      " Embarques,  "+
      " Veiculos,  "+
      " Complementos_Veiculos,  "+
      " Tipos_Veiculos,  "+
      " Modelos_Veiculos,  "+
      " Notas_Fiscais_Embarques,  "+
      " Notas_Fiscais,  "+
      " Cidades Cidade_Origem,  "+
      " Regioes_Estados,  "+
      " Estados,  "+
      " Regioes_Paises,  "+
      " Paises,  "+
      " Cidades Cidade_Destino ";
      sql += " WHERE Embarques.OID_Cidade_Destino = Cidade_Destino.OID_Cidade ";
      sql += " AND Embarques.OID_Cidade = Cidade_Origem.OID_Cidade ";
      sql += " AND Embarques.OID_VEICULO = Veiculos.OID_VEICULO ";
      sql += " AND Veiculos.OID_MODELO_VEICULO = Modelos_Veiculos.OID_MODELO_VEICULO ";
      sql += " AND Veiculos.OID_VEICULO = Complementos_Veiculos.OID_VEICULO ";
      sql += " AND Tipos_Veiculos.OID_TIPO_VEICULO = Modelos_Veiculos.OID_TIPO_VEICULO ";
      sql += " AND Embarques.OID_Embarque = Notas_Fiscais_Embarques.OID_Embarque ";
      sql += " AND Notas_Fiscais.OID_Nota_Fiscal = Notas_Fiscais_Embarques.OID_Nota_Fiscal ";
      sql += " and cidade_destino.oid_regiao_estado = regioes_estados.oid_regiao_estado  ";
      sql += " and regioes_estados.oid_estado = estados.oid_estado ";
      sql += " and Regioes_Paises.oid_regiao_pais = estados.oid_regiao_pais ";
      sql += " and Regioes_Paises.oid_pais = paises.oid_pais ";
      sql += " AND (Notas_Fiscais.OID_PESSOA = '" + ed.getOID_Pessoa_Senha() + "'";
      sql += " OR Notas_Fiscais.OID_PESSOA_DESTINATARIO = '" + ed.getOID_Pessoa_Senha() + "'";
      sql += " OR Notas_Fiscais.OID_PESSOA_CONSIGNATARIO = '" + ed.getOID_Pessoa_Senha() + "')";

      if (String.valueOf(ed.getOID_Nota_Fiscal()) != null &&
          !String.valueOf(ed.getOID_Nota_Fiscal()).equals("null") &&
          !String.valueOf(ed.getOID_Nota_Fiscal()).equals("")){
        sql += " AND Notas_Fiscais.OID_Nota_Fiscal = '" + ed.getOID_Nota_Fiscal() + "'";
      }

      if (String.valueOf(ed.getNR_Nota_Fiscal()) != null &&
          !String.valueOf(ed.getNR_Nota_Fiscal()).equals("null") &&
          !String.valueOf(ed.getNR_Nota_Fiscal()).equals("")){
        sql += " AND Notas_Fiscais.NR_Nota_Fiscal = '" + ed.getNR_Nota_Fiscal() + "'";
      }

      if (String.valueOf(ed.getNR_Pedido()) != null &&
          !String.valueOf(ed.getNR_Pedido()).equals("null") &&
          !String.valueOf(ed.getNR_Pedido()).equals("")){
        sql += " AND Notas_Fiscais.NR_Pedido = '" + ed.getNR_Pedido() + "'";
      }

      if (String.valueOf(ed.getOID_Cidade_Apoio()) != null &&
          !String.valueOf(ed.getOID_Cidade_Apoio()).equals("null") &&
          !String.valueOf(ed.getOID_Cidade_Apoio()).equals("0")){
        sql += " and Embarques.OID_Cidade_Apoio = " + ed.getOID_Cidade_Apoio();
      }

      if (String.valueOf(ed.getDM_Carga_Veiculo()) != null &&
        !String.valueOf(ed.getDM_Carga_Veiculo()).equals("") &&
        !String.valueOf(ed.getDM_Carga_Veiculo()).equals("T") &&
        !String.valueOf(ed.getDM_Carga_Veiculo()).equals("null")){
        sql += " and Embarques.DM_Carga_Veiculo = '" + ed.getDM_Carga_Veiculo() + "'";
      }

      if (String.valueOf(ed.getDM_Tipo_Frota()) != null &&
        !String.valueOf(ed.getDM_Tipo_Frota()).equals("") &&
        !String.valueOf(ed.getDM_Tipo_Frota()).equals("D") &&
        !String.valueOf(ed.getDM_Tipo_Frota()).equals("null")){
        sql += " and Complementos_Veiculos.DM_PROCEDENCIA = '" + ed.getDM_Tipo_Frota() + "'";
      }

      if (String.valueOf(ed.getNM_Motorista()) != null &&
        !String.valueOf(ed.getNM_Motorista()).equals("") &&
        !String.valueOf(ed.getNM_Motorista()).equals("null")){
        sql += " and Embarques.NM_Motorista = '" + ed.getNM_Motorista() + "'";
      }
      if (String.valueOf(ed.getDM_Situacao()) != null &&
        !String.valueOf(ed.getDM_Situacao()).equals("") &&
        !String.valueOf(ed.getDM_Situacao()).equals("null") &&
        !String.valueOf(ed.getDM_Situacao()).equals("T")){
        sql += " and Embarques.DM_Situacao = '" + ed.getDM_Situacao() + "'";
      }
      if (String.valueOf(ed.getDT_Emissao()) != null &&
        !String.valueOf(ed.getDT_Emissao()).equals("") &&
        !String.valueOf(ed.getDT_Emissao()).equals("null")){
        sql += " and Embarques.DT_Emissao >= '" + ed.getDT_Emissao() + "'";
      }
      if (String.valueOf(ed.getDT_Emissao_Final()) != null &&
        !String.valueOf(ed.getDT_Emissao_Final()).equals("") &&
        !String.valueOf(ed.getDT_Emissao_Final()).equals("null")){
        sql += " and Embarques.DT_Emissao <= '" + ed.getDT_Emissao_Final() + "'";
      }
      if (String.valueOf(ed.getDT_Previsao_Chegada()) != null &&
        !String.valueOf(ed.getDT_Previsao_Chegada()).equals("") &&
        !String.valueOf(ed.getDT_Previsao_Chegada()).equals("null")){
        sql += " and Embarques.DT_Previsao_Chegada >= '" + ed.getDT_Previsao_Chegada() + "'";
      }
      if (String.valueOf(ed.getDT_Previsao_Chegada_Final()) != null &&
        !String.valueOf(ed.getDT_Previsao_Chegada_Final()).equals("") &&
        !String.valueOf(ed.getDT_Previsao_Chegada_Final()).equals("null")){
        sql += " and Embarques.DT_Previsao_Chegada <= '" + ed.getDT_Previsao_Chegada_Final() + "'";
      }
      if (String.valueOf(ed.getDT_Entrega()) != null &&
        !String.valueOf(ed.getDT_Entrega()).equals("") &&
        !String.valueOf(ed.getDT_Entrega()).equals("null")){
        sql += " and Embarques.DT_Entrega >= '" + ed.getDT_Entrega() + "'";
      }
      if (String.valueOf(ed.getDT_Entrega_Final()) != null &&
        !String.valueOf(ed.getDT_Entrega_Final()).equals("") &&
        !String.valueOf(ed.getDT_Entrega_Final()).equals("null")){
        sql += " and Embarques.DT_Entrega <= '" + ed.getDT_Entrega_Final() + "'";
      }

      if (String.valueOf(ed.getNR_Embarque()) != null &&
          !String.valueOf(ed.getNR_Embarque()).equals("0") &&
          !String.valueOf(ed.getNR_Embarque()).equals("null")){
        sql += " and Embarques.NR_Embarque = " + ed.getNR_Embarque();
      }

      if (String.valueOf(ed.getOID_Cidade_Destino()) != null &&
          !String.valueOf(ed.getOID_Cidade_Destino()).equals("0") &&
          !String.valueOf(ed.getOID_Cidade_Destino()).equals("null")){
        sql += " and Embarques.OID_Cidade_Destino = " + ed.getOID_Cidade_Destino();
      }

      if (String.valueOf(ed.getOID_Cidade_Origem()) != null &&
          !String.valueOf(ed.getOID_Cidade_Origem()).equals("0") &&
          !String.valueOf(ed.getOID_Cidade_Origem()).equals("null")){
        sql += " and Embarques.OID_Cidade = " + ed.getOID_Cidade_Origem();
      }
      if (String.valueOf(ed.getOID_Tipo_Veiculo()) != null &&
          !String.valueOf(ed.getOID_Tipo_Veiculo()).equals("0") &&
          !String.valueOf(ed.getOID_Tipo_Veiculo()).equals("null")){
        sql += " and Tipos_Veiculos.OID_Tipo_Veiculo = " + ed.getOID_Tipo_Veiculo();
      }

      if (String.valueOf(ed.getNR_Placa()) != null &&
          !String.valueOf(ed.getNR_Placa()).equals("") &&
          !String.valueOf(ed.getNR_Placa()).equals("null")){
        sql += " and Embarques.OID_Veiculo = '" + ed.getNR_Placa() + "'";
      }

      if (String.valueOf(ed.getNR_Placa_Carreta()) != null &&
          !String.valueOf(ed.getNR_Placa_Carreta()).equals("") &&
          !String.valueOf(ed.getNR_Placa_Carreta()).equals("null")){
        sql += " and Embarques.OID_Veiculo_Carreta = '" + ed.getNR_Placa_Carreta() + "'";
      }

      if (String.valueOf(ed.getNR_Celular()) != null &&
          !String.valueOf(ed.getNR_Celular()).equals("") &&
          !String.valueOf(ed.getNR_Celular()).equals("null")){
        sql += " and Embarques.NR_Celular = '" + ed.getNR_Celular() + "'";
      }

//////// // System.out.println("here "+ String.valueOf(ed.getOID_Estado_Destino()));
      if ((String.valueOf(ed.getOID_Estado_Destino()) != null &&
            !String.valueOf(ed.getOID_Estado_Destino()).equals("null") &&
            !String.valueOf(ed.getOID_Estado_Destino()).equals("0"))){
  	  sql += " and estados.oid_estado = " + ed.getOID_Estado_Destino();
        }

      if ((String.valueOf(ed.getOID_Pais()) != null &&
            !String.valueOf(ed.getOID_Pais()).equals("null") &&
            !String.valueOf(ed.getOID_Pais()).equals("0"))){
  	  sql += " and Paises.OID_Pais = " + ed.getOID_Pais();
        }

//fatura
	if (String.valueOf(ed.getNR_Fatura()) != null &&
            !String.valueOf(ed.getNR_Fatura()).equals("null") &&
            !String.valueOf(ed.getNR_Fatura()).equals("")){
          sql += " AND Notas_Fiscais.NR_Fatura = '" + ed.getNR_Fatura() + "'";
        }

      sql += " Order by Embarques.DT_Previsao_Chegada, Embarques.DT_Nova_Previsao_Chegada ";
      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);
      FormataDataBean DataFormatada = new FormataDataBean();

      String OID_Embarque = " ";

      while (res.next()){
        EmbarqueED edVolta = new EmbarqueED();

        if (OID_Embarque.equals(" ") || !OID_Embarque.equals(res.getString("OID_Embarque"))){

          edVolta.setOID_Embarque(res.getLong("OID_Embarque"));
          edVolta.setNR_Embarque(res.getLong("NR_Embarque"));
          edVolta.setNR_Placa(res.getString("NR_Placa"));
          edVolta.setNR_Placa_Carreta(res.getString("OID_Veiculo_Carreta"));
          edVolta.setDM_Expresso(res.getString("DM_Expresso"));
          edVolta.setDT_Emissao(res.getString("DT_Emissao"));
          DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
          edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());
          edVolta.setNM_Cidade_Origem(res.getString("NM_Cidade_Origem"));
          edVolta.setNM_Cidade_Destino(res.getString("NM_Cidade_Destino"));

          edVolta.setNR_Odometro_Inicial(res.getLong("nr_odometro_inicial")); //Teo 16/09

          if (res.getString("OID_Veiculo_Carreta") != null && !res.getString("OID_Veiculo_Carreta").equals("")){
            sql = " Select Tipos_Veiculos.NM_Tipo_Veiculo from Tipos_Veiculos, Modelos_Veiculos, Veiculos ";
            sql += " WHERE Veiculos.OID_MODELO_VEICULO = Modelos_Veiculos.OID_MODELO_VEICULO ";
            sql += " AND Tipos_Veiculos.OID_TIPO_VEICULO = Modelos_Veiculos.OID_TIPO_VEICULO ";
            sql += " AND Veiculos.OID_VEICULO = '" + res.getString("OID_Veiculo_Carreta") + "'";

            ResultSet resTP = null;
            resTP = this.executasql.executarConsulta(sql);
            while (resTP.next()){
              edVolta.setNM_Tipo_Veiculo(resTP.getString("NM_Tipo_Veiculo"));
            }
          }else{
            edVolta.setNM_Tipo_Veiculo(res.getString("NM_Tipo_Veiculo"));
          }
          edVolta.setDT_Previsao_Chegada(res.getString("DT_Nova_Previsao_Chegada"));

          if (edVolta.getDT_Previsao_Chegada() != null ){
            DataFormatada.setDT_FormataData(edVolta.getDT_Previsao_Chegada());
            edVolta.setDT_Previsao_Chegada(DataFormatada.getDT_FormataData());

            edVolta.setHR_Previsao_Chegada(res.getString("HR_Nova_Previsao_Chegada"));
          }else{
            edVolta.setDT_Previsao_Chegada(res.getString("DT_Previsao_Chegada"));
            DataFormatada.setDT_FormataData(edVolta.getDT_Previsao_Chegada());
            edVolta.setDT_Previsao_Chegada(DataFormatada.getDT_FormataData());

            edVolta.setHR_Previsao_Chegada(res.getString("HR_Previsao_Chegada"));
          }

          edVolta.setDT_Chegada(res.getString("DT_Chegada"));
          DataFormatada.setDT_FormataData(edVolta.getDT_Chegada());
          edVolta.setDT_Chegada(DataFormatada.getDT_FormataData());

          edVolta.setHR_Chegada(res.getString("HR_Chegada"));

          edVolta.setNM_Motorista(res.getString("NM_Motorista"));
          edVolta.setNR_Celular(res.getString("NR_Celular"));
          edVolta.setDM_Situacao(res.getString("DM_Situacao"));

          sql =  " SELECT Tipos_Ocorrencias.NM_Tipo_Ocorrencia, Ocorrencias_Embarques.TX_DESCRICAO, Ocorrencias_Embarques.HR_Ocorrencia_Embarque, Ocorrencias_Embarques.DT_Ocorrencia_Embarque from Ocorrencias_Embarques, Tipos_Ocorrencias ";
          sql += " WHERE Ocorrencias_Embarques.OID_Tipo_Ocorrencia = Tipos_Ocorrencias.OID_Tipo_Ocorrencia ";
          sql += " AND Ocorrencias_Embarques.OID_Embarque = " + edVolta.getOID_Embarque();
          sql += " AND Ocorrencias_Embarques.HR_OCORRENCIA_EMBARQUE is not null";
          sql += " ORDER BY Ocorrencias_Embarques.DT_OCORRENCIA_EMBARQUE, HR_OCORRENCIA_EMBARQUE desc";

          ResultSet resTB = null;
          resTB = this.executasql.executarConsulta(sql);

          edVolta.setNM_Ocorrencia("*");
          edVolta.setTX_Descricao("*");
          edVolta.setDT_Ocorrencia_Embarque("*");
          edVolta.setHR_Ocorrencia_Embarque("*");

          while (resTB.next()){

            edVolta.setTX_Descricao(resTB.getString("TX_DESCRICAO"));
            edVolta.setNM_Ocorrencia(resTB.getString("NM_Tipo_Ocorrencia"));
            edVolta.setDT_Ocorrencia_Embarque(resTB.getString("DT_Ocorrencia_Embarque"));
            DataFormatada.setDT_FormataData(edVolta.getDT_Ocorrencia_Embarque());
            edVolta.setDT_Ocorrencia_Embarque(DataFormatada.getDT_FormataData());

            edVolta.setHR_Ocorrencia_Embarque(resTB.getString("HR_Ocorrencia_Embarque"));

          }
          list.add(edVolta);
        }
        OID_Embarque = res.getString("OID_Embarque");
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao listar Embarque");
      excecoes.setMetodo("lista");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return list;
  }






  public EmbarqueED getByRecord(EmbarqueED ed)throws Excecoes{

    String sql = null;

    EmbarqueED edVolta = new EmbarqueED();

    try{

      sql =  "SELECT "+
      " Embarques.OID_Embarque,  "+
      " Embarques.CD_Roteiro, "+
      " Embarques.OID_Cidade,  "+
      " Embarques.OID_Cidade_Destino,  "+
      " Embarques.OID_Veiculo,  "+
      " Embarques.OID_Manifesto,  "+
      " Embarques.OID_Veiculo_Carreta,  "+
      " Embarques.NR_Embarque,  "+
      " Embarques.NR_Placa,  "+
      " Embarques.NM_Motorista, "+
      " Embarques.NR_Celular,  "+
      " Embarques.NR_Chassi,  "+
      " Embarques.NR_Carro,  "+
      " Embarques.DM_Expresso,  "+
      " Embarques.DM_Carga_Veiculo,  "+
      " Embarques.DM_Situacao, "+
      " Embarques.DT_Previsao_Chegada, "+
      " Embarques.HR_Previsao_Chegada, "+
      " Embarques.DT_Nova_Previsao_Chegada, "+
      " Embarques.HR_Nova_Previsao_Chegada, "+
      " Embarques.DT_Entrega, "+
      " Embarques.HR_Entrega, "+
      " Embarques.DT_Saida, "+
      " Embarques.HR_Saida, "+
      " Embarques.DT_Chegada, "+
      " Embarques.HR_Chegada, "+
      " Embarques.DT_Emissao,  "+
      " Embarques.nr_odometro_inicial, "+ //Teo 16/09
      " Cidade_Origem.NM_Cidade as NM_Cidade_Origem, "+
      " Cidade_Destino.NM_Cidade as NM_Cidade_Destino  "+
      " from  "+
      " Embarques,  "+
      " Cidades Cidade_Origem,  "+
      " Cidades Cidade_Destino ";
      sql += " WHERE Embarques.OID_Cidade_Destino = Cidade_Destino.OID_Cidade ";
      sql += " AND Embarques.OID_Cidade = Cidade_Origem.OID_Cidade ";

      if (String.valueOf(ed.getOID_Embarque()) != null &&
          !String.valueOf(ed.getOID_Embarque()).equals("0") &&
          !String.valueOf(ed.getOID_Embarque()).equals("null")){
        sql += " and OID_Embarque = " + ed.getOID_Embarque();
      }

      if (String.valueOf(ed.getNR_Embarque()) != null &&
            !String.valueOf(ed.getNR_Embarque()).equals("0") &&
            !String.valueOf(ed.getNR_Embarque()).equals("null")){
          sql += " and NR_Embarque = " + ed.getNR_Embarque();
        }

      if (String.valueOf(ed.getOID_Manifesto()) != null &&
            !String.valueOf(ed.getOID_Manifesto()).equals("") &&
            !String.valueOf(ed.getOID_Manifesto()).equals("null")){
          sql += " and OID_Manifesto = '" + ed.getOID_Manifesto() + "'";
        }

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      FormataDataBean DataFormatada = new FormataDataBean();

      while (res.next()){
        edVolta.setDM_Carga_Veiculo(res.getString("DM_Carga_Veiculo"));
        edVolta.setOID_Embarque(res.getLong("OID_Embarque"));
        edVolta.setOID_Veiculo(res.getString("OID_Veiculo"));
        edVolta.setOID_Veiculo_Carreta(res.getString("OID_Veiculo_Carreta"));
        edVolta.setOID_Cidade_Origem(res.getLong("OID_Cidade"));
        edVolta.setOID_Cidade_Destino(res.getLong("OID_Cidade_Destino"));
        edVolta.setNR_Embarque(res.getLong("NR_Embarque"));
        edVolta.setNR_Placa(res.getString("NR_Placa"));
        edVolta.setNR_Placa_Carreta(res.getString("OID_Veiculo_Carreta"));
        edVolta.setCD_Roteiro(res.getString("CD_Roteiro"));
        edVolta.setDM_Expresso(res.getString("DM_Expresso"));
        edVolta.setDT_Emissao(res.getString("DT_Emissao"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
        edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());
        edVolta.setNM_Cidade_Origem(res.getString("NM_Cidade_Origem"));
        edVolta.setNM_Cidade_Destino(res.getString("NM_Cidade_Destino"));

        edVolta.setDT_Previsao_Chegada(res.getString("DT_Previsao_Chegada"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Previsao_Chegada());
        edVolta.setDT_Previsao_Chegada(DataFormatada.getDT_FormataData());

        edVolta.setDT_Nova_Previsao_Chegada(res.getString("DT_Nova_Previsao_Chegada"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Nova_Previsao_Chegada());
        edVolta.setDT_Nova_Previsao_Chegada(DataFormatada.getDT_FormataData());

        edVolta.setDT_Entrega(res.getString("DT_Entrega"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Entrega());
        edVolta.setDT_Entrega(DataFormatada.getDT_FormataData());

        edVolta.setDT_Saida(res.getString("DT_Saida"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Saida());
        edVolta.setDT_Saida(DataFormatada.getDT_FormataData());

        edVolta.setDT_Chegada(res.getString("DT_Chegada"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Chegada());
        edVolta.setDT_Chegada(DataFormatada.getDT_FormataData());

        edVolta.setHR_Previsao_Chegada(res.getString("HR_Previsao_Chegada"));
        edVolta.setHR_Nova_Previsao_Chegada(res.getString("HR_Nova_Previsao_Chegada"));
        edVolta.setHR_Entrega(res.getString("HR_Entrega"));
        edVolta.setHR_Saida(res.getString("HR_Saida"));
        edVolta.setHR_Chegada(res.getString("HR_Chegada"));

        edVolta.setNM_Motorista(res.getString("NM_Motorista"));
        edVolta.setNR_Celular(res.getString("NR_Celular"));

        edVolta.setNR_Chassi(res.getString("NR_chassi"));
        edVolta.setNR_Carro(res.getString("NR_carro"));

        edVolta.setDM_Situacao(res.getString("DM_Situacao"));
        edVolta.setDM_Nota_Lancada("N");

	edVolta.setNR_Odometro_Inicial(res.getLong("nr_odometro_inicial")); //Teo 16/09

        ResultSet resDT = null;

        if (res.getString("CD_Roteiro") != null &&
          !String.valueOf(res.getString("CD_Roteiro")).equals("") &&
          !String.valueOf(res.getString("CD_Roteiro")).equals("null")){

          sql =  " SELECT * from Roteiros WHERE ";
          sql += " CD_Roteiro = '" + res.getString("CD_Roteiro") + "'";

          resDT = this.executasql.executarConsulta(sql);

          while (resDT.next()){
            edVolta.setCD_Roteiro(resDT.getString("CD_Roteiro"));
            edVolta.setNM_Roteiro(resDT.getString("NM_ROTEIRO"));

          }
        }

        sql = " SELECT Notas_Fiscais_Embarques.OID_Nota_Fiscal_Embarque from Notas_Fiscais_Embarques "+
              " WHERE Notas_Fiscais_Embarques.OID_Embarque = '" + edVolta.getOID_Embarque() + "'";

        resDT = null;
        resDT = this.executasql.executarConsulta(sql);

        while (resDT.next()){
          edVolta.setDM_Nota_Lancada("S");
        }
// System.out.println(res.getString("oid_manifesto"));
		edVolta.setOID_Manifesto(res.getString("oid_manifesto"));
        if (JavaUtil.doValida(res.getString("oid_manifesto"))){

            sql =  " SELECT nr_manifesto_internacional from manifestos_internacionais WHERE ";
            sql += " oid_manifesto_internacional = '" + res.getString("oid_manifesto") + "'";

            resDT = this.executasql.executarConsulta(sql);
            while (resDT.next()){
              edVolta.setNR_Manifesto(resDT.getString("nr_manifesto_internacional"));
            }
// System.out.println(edVolta.getNR_Manifesto());
            if (!JavaUtil.doValida(edVolta.getNR_Manifesto())){
                sql =  " SELECT nr_manifesto from manifestos WHERE ";
                sql += " oid_manifesto = '" + res.getString("oid_manifesto") + "'";

                resDT = this.executasql.executarConsulta(sql);
                while (resDT.next()){
                  edVolta.setNR_Manifesto(resDT.getString("nr_manifesto"));
                }
            }
         }
// System.out.println(edVolta.getNR_Manifesto());

      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      exc.printStackTrace();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao selecionar Embarque");
      excecoes.setMetodo("Seleção");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return edVolta;
  }

  public void geraRelatorio(EmbarqueED ed)throws Excecoes{

    String sql = null;

    EmbarqueED edVolta = new EmbarqueED();

    try{

      sql = "select * from Embarques where 1=1 ";

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      EmbarqueRL Embarque_rl = new EmbarqueRL();
      Embarque_rl.geraRelatEstoque(res);
    }
    catch (Excecoes e){
      throw e;
    }
    catch(Exception exc){
      Excecoes exce = new Excecoes();
      exce.setExc(exc);
      exce.setMensagem("Erro no método listar");
      exce.setClasse(this.getClass().getName());
      exce.setMetodo("geraRelatorio(EmbarqueED ed)");
    }

  }

  public ArrayList lista_Localizacao_Veiculos(String orderby, String dm_procedencia, String placa)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();
    String DM_Situacao = "";

    try{
// System.out.println("chegou!!!!!!!!!!!!!!");
      verificaTerceiros();

      sql = " SELECT embarques.oid_veiculo, ";
      sql+= " embarques.oid_cidade, ";
      sql+= " embarques.oid_cidade_apoio, ";
      sql+= " embarques.oid_embarque, ";
      sql+= " embarques.oid_cidade_destino, ";
      sql+= " embarques.dt_saida, ";
      sql+= " embarques.hr_saida, ";
      sql+= " embarques.dm_carga_veiculo, ";
      sql+= " embarques.dt_previsao_chegada, ";
      sql+= " embarques.hr_previsao_chegada, ";
      sql+= " embarques.dt_nova_previsao_chegada, ";
      sql+= " embarques.hr_nova_previsao_chegada, ";
      sql+= " embarques.dt_chegada, ";
      sql+= " embarques.hr_chegada, ";
      sql+= " embarques.dt_ultimo_contato, ";
      sql+= " embarques.hr_ultimo_contato, ";
      sql+= " embarques.dm_situacao, ";
      sql+= " embarques.nm_motorista, ";
      sql+= " embarques.tx_ultima_observacao, ";
      sql+= " veiculos.nr_frota, ";
      sql+= " complementos_veiculos.dm_procedencia";
      sql+= " FROM embarques, veiculos, complementos_veiculos ";
      sql+= " WHERE complementos_veiculos.dm_procedencia = '"+dm_procedencia+"'";
      sql+= " AND complementos_veiculos.oid_veiculo = veiculos.oid_veiculo";
      sql+= " AND embarques.oid_veiculo = complementos_veiculos.oid_veiculo ";
      sql+= " AND embarques.oid_embarque = veiculos.oid_embarque ";
      sql+= " AND veiculos.oid_embarque = embarques.oid_embarque ";

      if( placa != null ) sql+= " AND embarques.oid_veiculo = '"+placa+"'";

      sql+= orderby;

// System.out.println( "===== " + sql );

      ResultSet res = null, res2 = null;
      res = this.executasql.executarConsulta(sql);
      FormataDataBean DataFormatada = new FormataDataBean();

      while (res.next()){
        EmbarqueED edVolta = new EmbarqueED();
        edVolta.setOID_Veiculo( res.getString("OID_Veiculo") );
        edVolta.setDM_Procedencia( res.getString("dm_procedencia") );

        sql = " select NM_Cidade from cidades where oid_cidade = " + res.getString("OID_Cidade");
        res2 = this.executasql.executarConsulta(sql);
        while( res2.next() ){
          edVolta.setNM_Cidade_Origem( res2.getString( "NM_Cidade" ) );
        }

        sql = " select NM_Cidade from cidades where oid_cidade = " + res.getString("OID_Cidade_Destino");
        res2 = this.executasql.executarConsulta(sql);
        while( res2.next() ){
          edVolta.setNM_Cidade_Destino( res2.getString( "NM_Cidade" ) );
        }

        sql = " select NM_Cidade from cidades where oid_cidade = " + res.getString("OID_Cidade_Apoio");
        res2 = this.executasql.executarConsulta(sql);
        while( res2.next() ){
          edVolta.setNM_Cidade_Apoio( res2.getString( "NM_Cidade" ) );
        }
        if( edVolta.getNM_Cidade_Apoio() == null ) edVolta.setNM_Cidade_Apoio( "" );

        edVolta.setDT_Saida(res.getString("DT_Saida"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Saida());
        edVolta.setDT_Saida(DataFormatada.getDT_FormataData());

        edVolta.setHR_Saida( res.getString("HR_Saida") );
        if( edVolta.getHR_Saida() == null ) edVolta.setHR_Saida( "" );

        if( res.getString( "DT_Nova_Previsao_Chegada" ) == null || res.getString( "DT_Nova_Previsao_Chegada" ).equals( "" ) ){
          edVolta.setDT_Previsao_Chegada(res.getString("DT_Previsao_Chegada"));
          DataFormatada.setDT_FormataData(edVolta.getDT_Previsao_Chegada());
          edVolta.setDT_Previsao_Chegada(DataFormatada.getDT_FormataData());
        }else{
          edVolta.setDT_Previsao_Chegada(res.getString("DT_Nova_Previsao_Chegada"));
          DataFormatada.setDT_FormataData(edVolta.getDT_Previsao_Chegada());
          edVolta.setDT_Previsao_Chegada(DataFormatada.getDT_FormataData());
        }

        if( res.getString( "HR_Nova_Previsao_Chegada" ) == null || res.getString( "HR_Nova_Previsao_Chegada" ).equals( "" ) ){
          edVolta.setHR_Previsao_Chegada( res.getString("HR_Previsao_Chegada") );
          if( edVolta.getHR_Previsao_Chegada() == null ) edVolta.setHR_Previsao_Chegada( "" );
        }else{
          edVolta.setHR_Previsao_Chegada( res.getString("HR_Nova_Previsao_Chegada") );
          if( edVolta.getHR_Previsao_Chegada() == null ) edVolta.setHR_Previsao_Chegada( "" );
        }

        edVolta.setDT_Chegada(res.getString("DT_Chegada"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Chegada());
        edVolta.setDT_Chegada(DataFormatada.getDT_FormataData());

        edVolta.setHR_Chegada( res.getString("HR_Chegada") );
        if( edVolta.getHR_Chegada() == null ) edVolta.setHR_Chegada( "" );

        edVolta.setDT_Ultimo_Contato(res.getString("DT_Ultimo_Contato"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Ultimo_Contato());
        edVolta.setDT_Ultimo_Contato(DataFormatada.getDT_FormataData());

        edVolta.setHR_Ultimo_Contato( res.getString("HR_Ultimo_Contato") );
        if( edVolta.getHR_Ultimo_Contato() == null ) edVolta.setHR_Ultimo_Contato( "" );

        edVolta.setDT_Proximo_Contato( "X" );
        edVolta.setHR_Proximo_Contato( "X" );

        switch( Integer.valueOf( res.getString("DM_Situacao") ).intValue() ){
          case 0:
            DM_Situacao = "Atrasado";
            break;
          case 3:
            DM_Situacao = "Em Trânsito";
            break;
          case 5:
            DM_Situacao = "Embarcando";
            break;
          case 7:
            DM_Situacao = "Chegou";
            break;
          case 8:
            DM_Situacao = "Entregue";
            break;
          case 9:
            DM_Situacao = "Não Embarcado";
            break;
          case 10:
            DM_Situacao = "Parada Autorizada";
        }

        edVolta.setDM_Situacao( DM_Situacao );

        if( res.getString("DM_Carga_Veiculo").equals( "C" ) )
            edVolta.setDM_Carga_Veiculo( "Carregado" );
        else if( res.getString("DM_Carga_Veiculo").equals( "V" ) )
            edVolta.setDM_Carga_Veiculo( "Vazio" );
        else if( res.getString("DM_Carga_Veiculo").equals( "D" ) )
            edVolta.setDM_Carga_Veiculo( "Vazio Deslocado" );

        edVolta.setTX_Ultima_Observacao( res.getString("TX_Ultima_Observacao") );
        if( edVolta.getTX_Ultima_Observacao() == null ) edVolta.setTX_Ultima_Observacao( "" );

        edVolta.setNM_Motorista( res.getString( "nm_motorista" ) );

        edVolta.setNR_Frota( res.getString( "NR_Frota" ) );
        if( edVolta.getNR_Frota() == null ) edVolta.setNR_Frota( "" );

        edVolta.setOID_Embarque( res.getInt( "OID_Embarque" ) );

        list.add(edVolta);
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao listar Embarque");
      excecoes.setMetodo("lista_Localizacao_Veiculos(EmbarqueED ed)");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return list;
  }

  public ArrayList lista_Localizacao_Veiculos_Placar(String orderby, String dm_procedencia, String placa)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();
    String DM_Situacao = "";

    // System.out.println(" lista_Localizacao_Veiculos_Placar chegou!!!!!!!!!!!!!!");


    Hashtable hashtable = new Hashtable();

    try{
// System.out.println("chegou!!!!!!!!!!!!!!");
        if (dm_procedencia != null && dm_procedencia.equals("O")){
            //verificaOnibus_Placar();
        } else {
            verificaTerceiros_Placar();
        }
        
//        //testes Teo
//        sql = "select count(embarques.oid_embarque) as result ";
//        sql+= " FROM embarques, veiculos, complementos_veiculos, veiculos_embarques, ";
//        sql+= " Cidades, Cidades Cidade_Destino, Cidades Cidade_Apoio ";
//        sql+= " WHERE complementos_veiculos.dm_procedencia = '"+dm_procedencia+"'";
//        sql+= " AND complementos_veiculos.oid_veiculo = veiculos.oid_veiculo";
//        sql+= " AND veiculos_embarques.oid_veiculo = veiculos.oid_veiculo ";
//        sql+= " AND veiculos_embarques.oid_embarque = embarques.oid_embarque ";
//        sql+= " AND embarques.oid_cidade = Cidades.oid_cidade ";
//        sql+= " AND embarques.oid_cidade_destino = Cidade_Destino.oid_cidade ";
//        sql+= " AND embarques.oid_cidade_apoio = Cidade_Apoio.oid_cidade ";
//        sql+= " AND veiculos_embarques.DM_Situacao = 'A' ";
//        ResultSet teo = this.executasql.executarConsulta(sql);
//        // System.out.println(sql);
//        // System.out.println(teo.next());
//        teo.beforeFirst();
//        while(teo.next()){
//            // System.out.println(teo.getString("result"));
//        }
//        //fim

      sql = " SELECT embarques.oid_veiculo, ";
      sql+= " embarques.oid_cidade, ";
      sql+= " embarques.oid_cidade_apoio, ";
      sql+= " embarques.oid_embarque, ";
      sql+= " embarques.oid_cidade_destino, ";
      sql+= " embarques.dt_saida, ";
      sql+= " embarques.hr_saida, ";
      sql+= " embarques.dm_carga_veiculo, ";
      sql+= " embarques.dt_previsao_chegada, ";
      sql+= " embarques.hr_previsao_chegada, ";
      sql+= " embarques.dt_nova_previsao_chegada, ";
      sql+= " embarques.hr_nova_previsao_chegada, ";
      sql+= " embarques.dt_chegada, ";
      sql+= " embarques.hr_chegada, ";
      sql+= " embarques.dt_ultimo_contato, ";
      sql+= " embarques.hr_ultimo_contato, ";
      sql+= " embarques.dm_situacao, ";
      sql+= " embarques.nm_motorista, ";
      sql+= " embarques.nr_chassi, ";
      sql+= " embarques.nr_carro, ";
      sql+= " embarques.tx_ultima_observacao, ";
      sql+= " veiculos.nr_frota, ";
      sql+= " Cidades.nm_cidade, ";
      sql+= " Cidade_Destino.nm_cidade as nm_cidade_destino, ";
      sql+= " Cidade_Apoio.nm_cidade as nm_cidade_apoio, ";
      sql+= " complementos_veiculos.dm_procedencia";
      sql+= " FROM embarques, veiculos, complementos_veiculos, veiculos_embarques, ";
      sql+= " Cidades, Cidades Cidade_Destino, Cidades Cidade_Apoio ";
      sql+= " WHERE complementos_veiculos.dm_procedencia = '"+dm_procedencia+"'";
      sql+= " AND complementos_veiculos.oid_veiculo = veiculos.oid_veiculo";
      sql+= " AND veiculos_embarques.oid_veiculo = veiculos.oid_veiculo ";
      sql+= " AND veiculos_embarques.oid_embarque = embarques.oid_embarque ";
      sql+= " AND embarques.oid_cidade = Cidades.oid_cidade ";
      sql+= " AND embarques.oid_cidade_destino = Cidade_Destino.oid_cidade ";
      sql+= " AND embarques.oid_cidade_apoio = Cidade_Apoio.oid_cidade ";
      if (dm_procedencia != null && dm_procedencia.equals("O")){
          sql+= " AND embarques.dt_entrega is null ";
          sql+= " AND embarques.dm_situacao != '8' ";
//          Calendar dataHOJE = Calendar.getInstance();
//          int mes = 0;
//          if(dataHOJE.get(Calendar.MONTH)==1){
//              dataHOJE.set(Calendar.MONTH, 12);
//              dataHOJE.set(Calendar.YEAR, dataHOJE.get(Calendar.YEAR) - 1);
//          } else {
//	          mes = dataHOJE.get(Calendar.MONTH) - 1;
//	          dataHOJE.set(Calendar.MONTH, mes);
//          }
//          java.util.Date d = new java.util.Date();
//          d = dataHOJE.getTime();
//          SimpleDateFormat formatter_date = new SimpleDateFormat("dd/MM/yyyy");
//          sql+= " AND embarques.dt_previsao_chegada >= '" + formatter_date.format(d) + "' ";
          
      } else {
          sql+= " AND veiculos_embarques.DM_Situacao = 'A' ";
      }
      
      if( placa != null ) sql+= " AND veiculos.oid_veiculo = '"+placa+"'";

      sql+= orderby + ", dt_previsao_chegada desc, hr_previsao_chegada desc ";
      
// System.out.println("PLACAR MUDOU!!! ");
// System.out.println("PLACAR select = "+sql );

	   ResultSet res2 = null;
       ResultSet res = this.executasql.executarConsulta(sql);
      FormataDataBean DataFormatada = new FormataDataBean();
      
int c1 = 0, c2 = 0;
      
      while (res.next()){
// System.out.println("while entrou...");
c1++;
        EmbarqueED edVolta = new EmbarqueED();
        edVolta.setOID_Veiculo( res.getString("OID_Veiculo") );
        edVolta.setDM_Procedencia( res.getString("dm_procedencia") );
        edVolta.setNM_Cidade_Origem( res.getString( "nm_cidade" ) );
        edVolta.setNM_Cidade_Destino( res.getString( "nm_cidade_destino" ) );
        edVolta.setNM_Cidade_Apoio( res.getString( "nm_cidade_apoio" ) );

        if( edVolta.getNM_Cidade_Apoio() == null ) edVolta.setNM_Cidade_Apoio( "" );

        edVolta.setDT_Saida(res.getString("DT_Saida"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Saida());
        edVolta.setDT_Saida(DataFormatada.getDT_FormataData());

        edVolta.setHR_Saida( res.getString("HR_Saida") );
        if( edVolta.getHR_Saida() == null ) edVolta.setHR_Saida( "" );

        if( res.getString( "DT_Nova_Previsao_Chegada" ) == null || res.getString( "DT_Nova_Previsao_Chegada" ).equals( "" ) ){
          edVolta.setDT_Previsao_Chegada(res.getString("DT_Previsao_Chegada"));
          DataFormatada.setDT_FormataData(edVolta.getDT_Previsao_Chegada());
          edVolta.setDT_Previsao_Chegada(DataFormatada.getDT_FormataData());
        }else{
          edVolta.setDT_Previsao_Chegada(res.getString("DT_Nova_Previsao_Chegada"));
          DataFormatada.setDT_FormataData(edVolta.getDT_Previsao_Chegada());
          edVolta.setDT_Previsao_Chegada(DataFormatada.getDT_FormataData());
        }

        if( res.getString( "HR_Nova_Previsao_Chegada" ) == null || res.getString( "HR_Nova_Previsao_Chegada" ).equals( "" ) ){
          edVolta.setHR_Previsao_Chegada( res.getString("HR_Previsao_Chegada") );
          if( edVolta.getHR_Previsao_Chegada() == null ) edVolta.setHR_Previsao_Chegada( "" );
        }else{
          edVolta.setHR_Previsao_Chegada( res.getString("HR_Nova_Previsao_Chegada") );
          if( edVolta.getHR_Previsao_Chegada() == null ) edVolta.setHR_Previsao_Chegada( "" );
        }

        edVolta.setDT_Chegada(res.getString("DT_Chegada"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Chegada());
        edVolta.setDT_Chegada(DataFormatada.getDT_FormataData());

        edVolta.setHR_Chegada( res.getString("HR_Chegada") );
        if( edVolta.getHR_Chegada() == null ) edVolta.setHR_Chegada( "" );

        edVolta.setDT_Ultimo_Contato(res.getString("DT_Ultimo_Contato"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Ultimo_Contato());
        edVolta.setDT_Ultimo_Contato(DataFormatada.getDT_FormataData());

        edVolta.setHR_Ultimo_Contato( res.getString("HR_Ultimo_Contato") );
        if( edVolta.getHR_Ultimo_Contato() == null ) edVolta.setHR_Ultimo_Contato( "" );

        edVolta.setDT_Proximo_Contato( "X" );
        edVolta.setHR_Proximo_Contato( "X" );

        switch( Integer.valueOf( res.getString("DM_Situacao") ).intValue() ){
          case 0:
            DM_Situacao = "Atrasado";
            break;
          case 3:
            DM_Situacao = "Em Trânsito";
            break;
          case 5:
            DM_Situacao = "Embarcando";
            break;
          case 7:
            DM_Situacao = "Chegou";
            break;
          case 8:
            DM_Situacao = "Entregue";
            break;
          case 9:
            DM_Situacao = "Não Embarcado";
            break;
          case 10:
            DM_Situacao = "Parada Autorizada";
        }

        edVolta.setDM_Situacao( DM_Situacao );

        if( res.getString("DM_Carga_Veiculo").equals( "C" ) )
            edVolta.setDM_Carga_Veiculo( "Carregado" );
        else if( res.getString("DM_Carga_Veiculo").equals( "V" ) )
            edVolta.setDM_Carga_Veiculo( "Vazio" );
        else if( res.getString("DM_Carga_Veiculo").equals( "D" ) )
            edVolta.setDM_Carga_Veiculo( "Vazio Deslocado" );

        edVolta.setTX_Ultima_Observacao( res.getString("TX_Ultima_Observacao") );
        if( edVolta.getTX_Ultima_Observacao() == null ) edVolta.setTX_Ultima_Observacao( "" );

        edVolta.setNM_Motorista( res.getString( "nm_motorista" ) );

        edVolta.setNR_Frota( res.getString( "NR_Frota" ) );
        if( edVolta.getNR_Frota() == null ) edVolta.setNR_Frota( "" );

        edVolta.setNR_Chassi( res.getString( "NR_chassi" ) );

        if( edVolta.getNR_Chassi() == null ) edVolta.setNR_Chassi( "" );
        edVolta.setNR_Carro( res.getString( "NR_carro" ) );
//// System.out.println("NR_carro " + res.getString( "NR_carro" ));
        if( edVolta.getNR_Carro() == null ) edVolta.setNR_Carro( "" );

        // System.out.println("while");

        edVolta.setOID_Embarque( res.getInt( "OID_Embarque" ) );

        //se ja existir o objeto na hash e não for Onibus
        if (hashtable.containsKey(edVolta.getOID_Veiculo()) && !dm_procedencia.equals("O")) {
            //pega o embarque que está na hash para comparar a data
            EmbarqueED embarqueEDHash = null;
            embarqueEDHash = (EmbarqueED)hashtable.get(edVolta.getOID_Veiculo());

            //data do bean que está no hash
            Calendar dt_saida_hash = Calendar.getInstance();
            String dt_saida_hash_str = embarqueEDHash.getDT_Saida();
            String hr_saida_hash_str = embarqueEDHash.getHR_Saida();

            //// System.out.println("data que esta no hash = "+dt_saida_hash_str);

            if (dt_saida_hash_str != null && !dt_saida_hash_str.equals("")) {
                int anoHash = Integer.parseInt(dt_saida_hash_str.substring(6,dt_saida_hash_str.length()));
                dt_saida_hash.set(1,anoHash);

                int mesHash = Integer.parseInt(dt_saida_hash_str.substring(3,5));
                dt_saida_hash.set(2,mesHash-1);

                int diaHash = Integer.parseInt(dt_saida_hash_str.substring(0,2));
                dt_saida_hash.set(5,diaHash);
            }
            if (hr_saida_hash_str != null && !hr_saida_hash_str.equals("")) {
                int horaHash = Integer.parseInt(hr_saida_hash_str.substring(0,2));
                dt_saida_hash.set(11,horaHash);

                int minutoHash = Integer.parseInt(hr_saida_hash_str.substring(3,5));
                dt_saida_hash.set(12,minutoHash);
            }

            //// System.out.println("data que esta no hash = "+Data.getDataDMY(dt_saida_hash));

            //data corrente do bean do result set
            Calendar dt_saida_volta = Calendar.getInstance();
            String dt_saida_volta_str = edVolta.getDT_Saida();
            String hr_saida_volta_str = edVolta.getHR_Saida();

            //// System.out.println("data que esta no result set = "+dt_saida_volta_str);

            if (dt_saida_volta_str != null && !dt_saida_volta_str.equals("")) {
	            int anoVolta = Integer.parseInt(dt_saida_volta_str.substring(6,dt_saida_volta_str.length()));
	            dt_saida_volta.set(1,anoVolta);

	            int mesVolta = Integer.parseInt(dt_saida_volta_str.substring(3,5));
	            dt_saida_volta.set(2,mesVolta-1);

	            int diaVolta = Integer.parseInt(dt_saida_volta_str.substring(0,2));
	            dt_saida_volta.set(5,diaVolta);
            }
            if (hr_saida_volta_str != null && !hr_saida_volta_str.equals("")) {
                int horaVolta = Integer.parseInt(dt_saida_volta_str.substring(0,2));
                dt_saida_hash.set(11,horaVolta);

                int minutoVolta = Integer.parseInt(hr_saida_volta_str.substring(3,5));
                dt_saida_hash.set(12,horaVolta);
            }

            //// System.out.println("data que esta no result set = "+Data.getDataDMY(dt_saida_volta));

            long diferencaDias = Data.diferencaDatasDias(dt_saida_hash,dt_saida_volta);

            //// System.out.println("Diferenca = "+diferencaDias);
            //se a data que está no hash for maior que está no bean do result set, permanece igual, caso
            //contrario substitui
            if (diferencaDias == 1) {
                hashtable.remove(edVolta.getOID_Veiculo());
                hashtable.put(edVolta.getOID_Veiculo(),edVolta);
            }
        }
        //caso não exista na hash simplesmente adiciona
        else {

// System.out.println("else : "+dm_procedencia);

            //se for terceiros, exibe somente as viagens de no maximo 3 dias atras
            if (dm_procedencia != null && dm_procedencia.equals("T")) {
                Calendar dataAtual = Calendar.getInstance();

                Calendar dataSaida = Calendar.getInstance();
                String dataSaidaStr = edVolta.getDT_Chegada();
                //String dataSaidaStr = edVolta.getDT_Saida();
                if (dataSaidaStr != null && !dataSaidaStr.equals("")) {
    	            int anoVolta = Integer.parseInt(dataSaidaStr.substring(6,dataSaidaStr.length()));
    	            dataSaida.set(1,anoVolta);

    	            int mesVolta = Integer.parseInt(dataSaidaStr.substring(3,5));
    	            dataSaida.set(2,mesVolta-1);

    	            int diaVolta = Integer.parseInt(dataSaidaStr.substring(0,2));
    	            dataSaida.set(5,diaVolta);
                }

                //diferenca de dias
                long diferencaDias = Data.diferencaDatasDias(dataAtual,dataSaida);
                //3 dias de diferenca
// System.out.println("diferencaDias : "+diferencaDias);
                if (diferencaDias >= -3) {
                    hashtable.put(edVolta.getOID_Veiculo(),edVolta);
                }
            }
            else {
                if(dm_procedencia.equals("O")){
                    
                    Calendar dataAtual = Calendar.getInstance();
                    //// System.out.println(edVolta.getDT_Saida() + " >> " + edVolta.getDT_Chegada());
                    Calendar dataSaida = Data.stringToCalendar(edVolta.getDT_Previsao_Chegada(), "dd/MM/yyyy");
                    
                    long diferencaDias = Data.diferencaDatasDias(dataSaida, dataAtual);
// System.out.println("NR_chassi >" + edVolta.getNR_Chassi()+"<");
                    if(JavaUtil.doValida(edVolta.getNR_Chassi())){
                        c2++;
                        // System.out.println("dentro >" + edVolta.getNR_Chassi()+"<");
                        // System.out.println("diferencaDias >" + diferencaDias+"<");
                        if(diferencaDias <= 30) hashtable.put(edVolta.getNR_Chassi(),edVolta);
                    } else{
                        if(diferencaDias <= 30) {
                            c2++;
	                        edVolta.setNR_Chassi(String.valueOf(c2));
	                        hashtable.put(edVolta.getNR_Chassi(),edVolta);
                        }
                    }
                } else {
                    hashtable.put(edVolta.getOID_Veiculo(),edVolta);
                }
            }
        }
      }
// System.out.println("tam: "+hashtable.size());
// System.out.println("while: "+c1);
// System.out.println("hashtable: "+c2);
      if (hashtable.size() > 0) {
          Enumeration enumeration = hashtable.elements();
          while (enumeration.hasMoreElements()) {
              list.add(enumeration.nextElement());
          }
      }

      //ordenação do array
      //Collections.sort(list);
	      Collections.sort(list, new Comparator() { 
	          public int compare(Object o1, Object o2) { 
	              int r = 0;
	              try{
		              EmbarqueED ed1 = (EmbarqueED)o1; 
		              EmbarqueED ed2 = (EmbarqueED)o2; 
		              Calendar dSaida1 = Data.stringToCalendar(ed1.getDT_Previsao_Chegada(), "dd/MM/yyyy");
		              Calendar dSaida2 = Data.stringToCalendar(ed2.getDT_Previsao_Chegada(), "dd/MM/yyyy");
		              if(dSaida1 != null && dSaida2 != null && dSaida1.after(dSaida2)) r = 1;
		              else r = -1;
	              }catch(Excecoes e){ e.printStackTrace();}
	              return r;
	          } 
	      });

    }
    catch(Exception exc){
      exc.printStackTrace();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao listar Embarque");
      excecoes.setMetodo("lista_Localizacao_Veiculos(EmbarqueED ed)");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return list;
  }

  public void verificaTerceiros_Placar() throws Excecoes{
    int dia = 0,mes = 0,ano = 0, hora = 0, diaAtual = 0,mesAtual = 0;
    int anoAtual = 0, horaAtual = 0, minuto = 0, minutoAtual = 0;
    String sql = null;
    int i;
    StringTokenizer st;
    boolean ok = false;
    try{
      sql = " SELECT embarques.oid_veiculo, ";
      sql+= " embarques.dt_chegada, ";
      sql+= " embarques.hr_chegada ";
      sql+= " FROM embarques, veiculos, complementos_veiculos ";
      sql+= " WHERE complementos_veiculos.dm_procedencia = 'T'";
      sql+= " AND complementos_veiculos.oid_veiculo = veiculos.oid_veiculo";
      sql+= " AND embarques.oid_veiculo = complementos_veiculos.oid_veiculo ";
      sql+= " AND embarques.oid_embarque = veiculos.oid_embarque ";
      sql+= " AND veiculos.oid_embarque = embarques.oid_embarque ";

      ResultSet res = null;
//// System.out.println("chegou!!!!!! "+sql);
      res = this.executasql.executarConsulta(sql);
      FormataDataBean DataFormatada = new FormataDataBean();

      while (res.next()){
        ok = false;

        if( res.getString("dt_chegada") != null ){

                st = new StringTokenizer( Data.getHoraHM(), ":" );
                horaAtual = Integer.valueOf( st.nextToken() ).intValue();
                minutoAtual = Integer.valueOf( st.nextToken() ).intValue();

                st = new StringTokenizer( res.getString("hr_chegada"), ":" );
                hora = Integer.valueOf( st.nextToken() ).intValue();
                minuto = Integer.valueOf( st.nextToken() ).intValue();

                st = new StringTokenizer( Data.getDataDMY(), "/" );
                diaAtual = Integer.valueOf( st.nextToken() ).intValue();
                mesAtual = Integer.valueOf( st.nextToken() ).intValue();
                anoAtual = Integer.valueOf( st.nextToken() ).intValue();

                st = new StringTokenizer( res.getString("dt_chegada"), "-" );
                ano = Integer.valueOf( st.nextToken() ).intValue();
                mes = Integer.valueOf( st.nextToken() ).intValue();
                dia = Integer.valueOf( st.nextToken() ).intValue();

          if( anoAtual > ano ){
            ok = true;
          }else if( anoAtual == ano ){
            if( mesAtual > mes ){
              ok = true;
            }else if( mesAtual == mes ){
              if( diaAtual > ( dia + 2 ) ){
                ok = true;
              }else if( diaAtual == ( dia + 2 ) ){
                if( horaAtual > hora ){
                  ok = true;
                }else if( horaAtual == hora ){
                  if( minutoAtual > minuto ){
                    ok = true;
                  }
                }
              }
            }
          }

          if( ok ){
            sql = "update Veiculos_Embarques set DM_Situacao = 'E'";
            sql += " where OID_Veiculo = '"+ res.getString("oid_veiculo") + "'" + " and DM_Situacao = 'A'";
            i = executasql.executarUpdate(sql);
          }

        }
      }

    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao Verificar Terceiros");
      excecoes.setMetodo("verificaTerceiros(EmbarqueED ed)");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void verificaTerceiros() throws Excecoes{
    int dia = 0,mes = 0,ano = 0, hora = 0, diaAtual = 0,mesAtual = 0;
    int anoAtual = 0, horaAtual = 0, minuto = 0, minutoAtual = 0;
    String sql = null;
    int i;
    StringTokenizer st;
    boolean ok = false;
    try{
      sql = " SELECT embarques.oid_veiculo, ";
      sql+= " embarques.dt_chegada, ";
      sql+= " embarques.hr_chegada ";
      sql+= " FROM embarques, veiculos, complementos_veiculos ";
      sql+= " WHERE complementos_veiculos.dm_procedencia = 'T'";
      sql+= " AND complementos_veiculos.oid_veiculo = veiculos.oid_veiculo";
      sql+= " AND embarques.oid_veiculo = complementos_veiculos.oid_veiculo ";
      sql+= " AND embarques.oid_embarque = veiculos.oid_embarque ";
      sql+= " AND veiculos.oid_embarque = embarques.oid_embarque ";

      ResultSet res = null;
// System.out.println("chegou!!!!!! "+sql);
      res = this.executasql.executarConsulta(sql);
      FormataDataBean DataFormatada = new FormataDataBean();

      while (res.next()){
        ok = false;

        if( res.getString("dt_chegada") != null ){

                st = new StringTokenizer( Data.getHoraHM(), ":" );
                horaAtual = Integer.valueOf( st.nextToken() ).intValue();
                minutoAtual = Integer.valueOf( st.nextToken() ).intValue();

                st = new StringTokenizer( res.getString("hr_chegada"), ":" );
                hora = Integer.valueOf( st.nextToken() ).intValue();
                minuto = Integer.valueOf( st.nextToken() ).intValue();

                st = new StringTokenizer( Data.getDataDMY(), "/" );
                diaAtual = Integer.valueOf( st.nextToken() ).intValue();
                mesAtual = Integer.valueOf( st.nextToken() ).intValue();
                anoAtual = Integer.valueOf( st.nextToken() ).intValue();

                st = new StringTokenizer( res.getString("dt_chegada"), "-" );
                ano = Integer.valueOf( st.nextToken() ).intValue();
                mes = Integer.valueOf( st.nextToken() ).intValue();
                dia = Integer.valueOf( st.nextToken() ).intValue();

          if( anoAtual > ano ){
            ok = true;
          }else if( anoAtual == ano ){
            if( mesAtual > mes ){
              ok = true;
            }else if( mesAtual == mes ){
              if( diaAtual > ( dia + 2 ) ){
                ok = true;
              }else if( diaAtual == ( dia + 2 ) ){
                if( horaAtual > hora ){
                  ok = true;
                }else if( horaAtual == hora ){
                  if( minutoAtual > minuto ){
                    ok = true;
                  }
                }
              }
            }
          }

          if( ok ){
            sql = "update Veiculos set OID_Embarque = null";
            sql += " where OID_Veiculo = '"+ res.getString("oid_veiculo") +"'";
            i = executasql.executarUpdate(sql);
          }

        }
      }

    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao Verificar Terceiros");
      excecoes.setMetodo("verificaTerceiros(EmbarqueED ed)");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void verificaOnibus_Placar() throws Excecoes{
      int dia = 0,mes = 0,ano = 0, hora = 0, diaAtual = 0,mesAtual = 0;
      int anoAtual = 0, horaAtual = 0, minuto = 0, minutoAtual = 0;
      String sql = null;
      int i;
      StringTokenizer st;
      boolean ok = false;
      try{
        sql = " SELECT embarques.oid_veiculo, ";
        sql+= " embarques.dt_entrega, ";
        sql+= " embarques.hr_entrega ";
        sql+= " FROM embarques, veiculos, complementos_veiculos ";
        sql+= " WHERE complementos_veiculos.dm_procedencia = 'O'";
        sql+= " AND complementos_veiculos.oid_veiculo = veiculos.oid_veiculo";
        sql+= " AND embarques.oid_veiculo = complementos_veiculos.oid_veiculo ";

        ResultSet res = null;
// System.out.println("chegou! Onibus! "+sql);
        res = this.executasql.executarConsulta(sql);
        FormataDataBean DataFormatada = new FormataDataBean();

        while (res.next()){
          ok = false;

          if( res.getString("dt_entrega") != null ){

              if( res.getString("hr_entrega") != null ){
              	  st = new StringTokenizer( Data.getHoraHM(), ":" );
                  horaAtual = Integer.valueOf( st.nextToken() ).intValue();
                  minutoAtual = Integer.valueOf( st.nextToken() ).intValue();

                  st = new StringTokenizer( res.getString("hr_entrega"), ":" );
                  hora = Integer.valueOf( st.nextToken() ).intValue();
                  minuto = Integer.valueOf( st.nextToken() ).intValue();
              }

                  st = new StringTokenizer( Data.getDataDMY(), "/" );
                  diaAtual = Integer.valueOf( st.nextToken() ).intValue();
                  mesAtual = Integer.valueOf( st.nextToken() ).intValue();
                  anoAtual = Integer.valueOf( st.nextToken() ).intValue();

                  st = new StringTokenizer( res.getString("dt_entrega"), "-" );
                  ano = Integer.valueOf( st.nextToken() ).intValue();
                  mes = Integer.valueOf( st.nextToken() ).intValue();
                  dia = Integer.valueOf( st.nextToken() ).intValue();

            if( anoAtual > ano ){
              ok = true;
            }else if( anoAtual == ano ){
              if( mesAtual > mes ){
                ok = true;
              }else if( mesAtual == mes ){
                if( diaAtual > ( dia ) ){
                  ok = true;
                }else if( diaAtual == ( dia ) ){
                  if( horaAtual > (hora + 2) ){
                    ok = true;
                  }else if( horaAtual == (hora + 2) ){
                    if( minutoAtual > minuto ){
                      ok = true;
                    }
                  }
                }
              }
            }

            if( ok ){
              sql = "update Veiculos_Embarques set DM_Situacao = 'E'";
              sql += " where OID_Veiculo = '"+ res.getString("oid_veiculo") + "'" + " and DM_Situacao = 'A'";
              i = executasql.executarUpdate(sql);
            }

          }
        }

      }

      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao Verificar Terceiros");
        excecoes.setMetodo("verificaTerceiros(EmbarqueED ed)");
        excecoes.setExc(exc);
        throw excecoes;
      }
    }

  public void verificaMIC(EmbarqueED ed) throws Excecoes{

      String sql = null;
      String cd_Roteiro = "";

      try{
        sql = "select cd_roteiro from manifestos_internacionais WHERE" +
        	  " Oid_Manifesto_internacional= '" + ed.getOID_Manifesto() + "'";

        ResultSet res = null;
// System.out.println("MIC! "+sql);
        res = this.executasql.executarConsulta(sql);
        while(res.next()){
            cd_Roteiro = res.getString("cd_roteiro");
        }
        if(JavaUtil.doValida(cd_Roteiro)){
// System.out.println("Bom, tudo certo! "+sql);
        } else {
            sql = "update manifestos_internacionais set cd_roteiro = '"+ ed.getCD_Roteiro() +"'";
            sql += " where Oid_Manifesto_internacional= '" + ed.getOID_Manifesto() + "'";
// System.out.println("update! "+sql);
            int i = executasql.executarUpdate(sql);
        }
      }
      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao slterar MIC");
        excecoes.setMetodo("verificaMIC");
        excecoes.setExc(exc);
        throw excecoes;
      }
    }

  public void geraInformeEmbarque(EmbarqueED ed, HttpServletResponse response)throws Excecoes{

    	String sql = null;
    	ArrayList list1 = new ArrayList();
     	String sqlBusca = "";

    	EmbarqueED edVolta = new EmbarqueED();

    	try{

    		sql =  "SELECT "+
			  		" Embarques.OID_Embarque,  "+
			  		" Embarques.nr_Embarque,  "+
			  		" Embarques.CD_Roteiro, "+
			  		" Embarques.OID_Cidade,  "+
			  		" Embarques.OID_Manifesto,  "+
			  		" Embarques.OID_Cidade_Destino,  "+
			  		" Embarques.OID_Veiculo,  "+
			  		" Embarques.OID_Veiculo_Carreta,  "+
			  		" Embarques.NR_Placa,  "+
			  		" Embarques.DM_Expresso,  "+
			  		" Embarques.NM_Motorista, "+
			  		" Embarques.NR_Celular,  "+
			  		" Embarques.DT_Saida, "+
			  		" Embarques.HR_Saida, "+
			  		" Embarques.nr_odometro_inicial, "+
			  		" Estado_origem.cd_estado as cd_estado_origem, "+
			  		" Estado_destino.cd_estado as cd_estado_destino, "+
			  		" Cidade_Origem.NM_Cidade as NM_Cidade_Origem, "+
			  		" Cidade_Destino.NM_Cidade as NM_Cidade_Destino  "+
			  		" from  "+
			  		" Embarques,  "+
			  		" Cidades Cidade_Origem,  "+
			  		" Cidades Cidade_Destino, "+
			  		" regioes_estados regioes_estados_d,"+
			  		" regioes_estados regioes_estados_o,"+
		    		" Estados Estado_Origem,  "+
		    		" Estados Estado_Destino ";
    		sql += " WHERE Embarques.OID_Cidade_Destino = Cidade_Destino.OID_Cidade ";
    		sql += " AND Cidade_destino.oid_regiao_estado = regioes_estados_d.oid_regiao_estado";
    		sql += " AND estado_destino.oid_estado = regioes_estados_D.oid_estado";
    		sql += " AND Embarques.OID_Cidade = Cidade_Origem.OID_Cidade ";
    		sql += " AND Cidade_origem.oid_regiao_estado = regioes_estados_o.oid_regiao_estado";
    		sql += " AND estado_origem.oid_estado = regioes_estados_o.oid_estado";

    		if (String.valueOf(ed.getOID_Embarque()) != null &&
    				!String.valueOf(ed.getOID_Embarque()).equals("0") &&
  				!String.valueOf(ed.getOID_Embarque()).equals("null")){
    			sql += " and OID_Embarque = " + ed.getOID_Embarque();
    		}

    		if (String.valueOf(ed.getNR_Embarque()) != null &&
    				!String.valueOf(ed.getNR_Embarque()).equals("0") &&
  				!String.valueOf(ed.getNR_Embarque()).equals("null")){
    			sql += " and NR_Embarque = " + ed.getNR_Embarque();
    		}

    		ResultSet res = null;
    		res = this.executasql.executarConsulta(sql);

    		FormataDataBean DataFormatada = new FormataDataBean();

    		while (res.next()){
    			edVolta.setOID_Embarque(res.getLong("OID_Embarque"));
    			edVolta.setOID_Veiculo(res.getString("OID_Veiculo"));
    			edVolta.setOID_Veiculo_Carreta(res.getString("OID_Veiculo_Carreta"));
    			edVolta.setNR_Embarque(res.getLong("NR_Embarque"));
    			edVolta.setNR_Placa(res.getString("NR_Placa"));
    			edVolta.setNR_Placa_Carreta(res.getString("OID_Veiculo_Carreta"));
    			edVolta.setCD_Roteiro(res.getString("CD_Roteiro"));
    			edVolta.setNM_Cidade_Origem(res.getString("NM_Cidade_Origem"));
    			edVolta.setCD_Estado_Origem(res.getString("cd_estado_Origem"));
    			edVolta.setNM_Cidade_Destino(res.getString("NM_Cidade_Destino")+" / "+res.getString("cd_estado_Destino"));

    			edVolta.setDT_Saida(res.getString("DT_Saida"));
    			DataFormatada.setDT_FormataData(edVolta.getDT_Saida());
    			edVolta.setDT_Saida(DataFormatada.getDT_FormataData());
    			edVolta.setHR_Saida(res.getString("HR_Saida"));

	   			edVolta.setDM_Expresso("Não");
	   			if (res.getString("DM_Expresso").equals("S")) edVolta.setDM_Expresso("Sim");

    			edVolta.setNM_Motorista(res.getString("NM_Motorista"));
    			edVolta.setNR_Celular(res.getString("NR_Celular"));
    			sql = "select nm_razao_social, nr_cnpj_cpf from pessoas, " +
    				  " veiculos where veiculos.oid_pessoa_proprietario = pessoas.oid_pessoa " +
    				  " and veiculos.oid_veiculo = '" + edVolta.getOID_Veiculo() +"'";
    			ResultSet rs = null;
    			rs = this.executasql.executarConsulta(sql);
    			while(rs.next()){
    				edVolta.setNM_Proprietario(rs.getString("nm_razao_social"));
    				edVolta.setNR_CNPJ_CPF(rs.getString("nr_cnpj_cpf"));
    			}
    			edVolta.setNR_Odometro_Inicial(res.getLong("nr_odometro_inicial")); //Teo 16/09


// System.out.println(res.getString("oid_manifesto"));
    			edVolta.setOID_Manifesto(res.getString("oid_manifesto"));
    	        if (JavaUtil.doValida(res.getString("oid_manifesto"))){
    	            rs = null;
    	            sql =  " SELECT nr_manifesto_internacional from manifestos_internacionais WHERE ";
    	            sql += " oid_manifesto_internacional = '" + res.getString("oid_manifesto") + "'";

    	            rs = this.executasql.executarConsulta(sql);
    	            while (rs.next()){
    	              edVolta.setTX_Ultima_Observacao(rs.getString("nr_manifesto_internacional"));
    	            }

    	            if (!JavaUtil.doValida(edVolta.getNR_Manifesto())){
    	                sql =  " SELECT nr_manifesto from manifestos WHERE ";
    	                sql += " oid_manifesto = '" + res.getString("oid_manifesto") + "'";

    	                rs = this.executasql.executarConsulta(sql);
    	                while (rs.next()){
    	                  edVolta.setTX_Ultima_Observacao(rs.getString("nr_manifesto"));
    	                }
    	            }
    	         }
// System.out.println(edVolta.getTX_Ultima_Observacao());

//    			rs = null;
//    			sqlBusca = "select nm_liberacao_seguradora from manifestos where" +
//    					   " oid_manifesto = '"+res.getString("oid_manifesto")+"'";
//    			rs = this.executasql.executarConsulta(sqlBusca);
//    			while(rs.next()) edVolta.setNR_Liberacao(rs.getString("nm_liberacao_seguradora"));

    			ResultSet resDT = null;
// System.out.println("rot: "+res.getString("CD_Roteiro"));

    			if (res.getString("CD_Roteiro") != null &&
    					!String.valueOf(res.getString("CD_Roteiro")).equals("") &&
  					!String.valueOf(res.getString("CD_Roteiro")).equals("null")){

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

    				if (String.valueOf(edVolta.getCD_Roteiro()) != null &&
    						!String.valueOf(edVolta.getCD_Roteiro()).equals("") &&
  						!String.valueOf(edVolta.getCD_Roteiro()).equals("null")){
    					sql += " and Roteiros.CD_Roteiro = '" + edVolta.getCD_Roteiro() + "'";
    				}
    				sql += " Order by Roteiros.CD_Roteiro, Rotas_Roteiros.NR_Sequencia ";
// System.out.println("ROT sql : "+sql);

    				resDT = this.executasql.executarConsulta(sql);

    				while (resDT.next()){

    					sqlBusca =  " SELECT Apoios.NR_KM, CD_Estado, Cidades.NM_Cidade , cidade_rota.NM_Cidade as orig, " +
    								" cidade_rota_destino.NM_Cidade as dest, NM_Referencia, DM_Tipo_Apoio, Apoios.OID_Apoio, " +
    								" NM_Apoio, NM_Contato, NM_Telefone, Rotas.OID_Rota, Rotas.NM_Rodovia, " +
    							    " cidade_rota.nm_cidade, cidade_rota_destino.nm_cidade, Apoios.DM_APOIO_RASTREADO " +
    							    " from Apoios, Rotas, " +
    							    " Cidades cidades, Cidades cidade_rota, Cidades cidade_rota_destino," +
    							    " Regioes_Estados, Estados ";
    					sqlBusca += " WHERE Apoios.OID_Rota = Rotas.OID_Rota ";
    					sqlBusca += " AND Apoios.OID_Cidade = Cidades.OID_Cidade " +
			    					" AND rotas.OID_Cidade = cidade_rota.OID_Cidade " +
			    					" AND rotas.OID_Cidade_destino = cidade_rota_destino.OID_Cidade " +
				  					" AND Cidades.OID_REGIAO_ESTADO = Regioes_Estados.OID_REGIAO_ESTADO "+
				  					" AND Regioes_Estados.OID_ESTADO = Estados.OID_ESTADO ";
				  					//" AND Apoios.DM_APOIO_RASTREADO = 'S' ";
    					sqlBusca += " AND Apoios.OID_Rota = '" + resDT.getString("oid_rota") + "'";
    					sqlBusca += " Order by Apoios.NR_KM ";
// System.out.println("APOIO sql : "+sqlBusca);

    					ResultSet resCTRC = null;
    					resCTRC = this.executasql.executarConsulta(sqlBusca);
    					if (resCTRC.next()){
    						ApoioED apED = new ApoioED();
    						// System.out.println("aq " +resCTRC.getString("NM_Telefone"));
    						apED.setNM_Apoio(resCTRC.getString("NM_Apoio") + " / " + resCTRC.getString("NM_Cidade"));
    						apED.setNM_Telefone(resCTRC.getString("NM_Telefone"));
    						apED.setNM_Referencia(resCTRC.getString("NM_Referencia"));
    						apED.setNM_Rodovia(resCTRC.getString("NM_Rodovia"));
    						apED.setNM_Trecho(resCTRC.getString("oid_rota"));
    						apED.setNM_Origem(resCTRC.getString("orig"));
    						apED.setNM_Destino(resCTRC.getString("dest"));
    						apED.setDM_Apoio_Rastreado(resCTRC.getString("DM_APOIO_RASTREADO"));
    						list1.add(apED);
    					} else {
    					    ApoioED apED = new ApoioED();
    						// System.out.println("else  sem tel. ");
    						//apED.setNM_Apoio(resDT.getString("nm_cidade_rota_destino"));
    						apED.setNM_Apoio("SEM APOIO");
    						apED.setNM_Telefone("");
    						apED.setNM_Referencia(resDT.getString("nr_km")+ " Km");
    						apED.setNM_Rodovia(resDT.getString("nm_rodovia"));
    						apED.setNM_Trecho(resDT.getString("oid_rota"));
    						apED.setNM_Origem(resDT.getString("nm_cidade_rota_origem"));
    						apED.setNM_Destino(resDT.getString("nm_cidade_rota_destino"));
    						apED.setDM_Apoio_Rastreado("N");
    						list1.add(apED);
    					}

    				edVolta.setTrechos(list1);
    				util.closeResultset(resCTRC);
    				}
    			}

    			sql = " SELECT complementos_veiculos.nm_cor, complementos_veiculos.dm_procedencia, "+
    			      " marcas_veiculos.nm_marca_veiculo, complementos_veiculos.nr_ano_veiculo, modelos_veiculos.nm_modelo_veiculo, complementos_veiculos.nr_chassis "+
    			      " from veiculos, complementos_veiculos, modelos_veiculos, marcas_veiculos"+
    			      " WHERE veiculos.oID_veiculo = '" + edVolta.getOID_Veiculo() + "'";
    			sql += " AND complementos_veiculos.oid_veiculo = veiculos.oid_veiculo "+
  			       	   " AND veiculos.oid_modelo_veiculo = modelos_veiculos.oid_modelo_veiculo" +
  			       	   " AND modelos_veiculos.oid_marca_veiculo = marcas_veiculos.oid_marca_veiculo";

    			resDT = null;
    			resDT = this.executasql.executarConsulta(sql);

    			while (resDT.next()){
    				if (resDT.getString("dm_procedencia") != null &&resDT.getString("dm_procedencia").equals("P")) edVolta.setDm_tipoF("XX");
    				if (resDT.getString("dm_procedencia") != null &&resDT.getString("dm_procedencia").equals("T")) edVolta.setDm_tipoC("XX");
    				if (resDT.getString("dm_procedencia") != null &&resDT.getString("dm_procedencia").equals("A")) edVolta.setDm_tipoA("XX");
    				edVolta.setTX_Detalhes(resDT.getString("nm_cor")+" / "+resDT.getString("nm_marca_veiculo")+" / "+resDT.getString("nr_ano_veiculo"));
    				edVolta.setTX_Detalhes1(resDT.getString("nm_modelo_veiculo")+" / "+resDT.getString("nr_chassis"));
    			}

// NÃO VAI CONSTAR NO INFORME
//    			sql = " SELECT Notas_Fiscais_Embarques.OID_Nota_Fiscal_Embarque, notas_fiscais.nr_peso, notas_fiscais.nr_nota_fiscal, notas_fiscais.vl_nota_fiscal" +
//    				  " from Notas_Fiscais_Embarques, notas_fiscais "+
//    				  " WHERE Notas_Fiscais_Embarques.OID_Embarque = '" + edVolta.getOID_Embarque() + "'" +
//    				  " AND notas_fiscais.oid_nota_fiscal = Notas_Fiscais_Embarques.OID_Nota_Fiscal";
//
//    			resDT = null;
//    			resDT = this.executasql.executarConsulta(sql);
//    			String nfs = "";
//    			double total = 0;
//    			double peso = 0;
//    			while (resDT.next()){
//    				// System.out.println("aqui " +resDT.getString("nr_nota_fiscal"));
//    				nfs += resDT.getString("nr_nota_fiscal")+" - ";
//    				total += resDT.getDouble("vl_nota_fiscal");
//    				peso += resDT.getDouble("nr_peso");
//    			}
//    			edVolta.setTX_Ultima_Observacao(nfs);
//    			edVolta.setVL_Total_Embarque(total);
//    			edVolta.setNR_Peso(peso);

// SOMENTE NACIONAL - NÃO VAI CONSTAR NO INFORME
//    			sql = " SELECT Notas_Fiscais_Embarques.OID_Nota_Fiscal" +
//		  			  " from Notas_Fiscais_Embarques "+
//		  			  " WHERE Notas_Fiscais_Embarques.OID_Embarque = '" + edVolta.getOID_Embarque() + "'";
//    			resDT = null;
//    			resDT = this.executasql.executarConsulta(sql);
//    			while (resDT.next()){
//    				sqlBusca = "select conhecimentos.nr_conhecimento from conhecimentos, " +
//    						   " conhecimentos_notas_fiscais where conhecimentos.oid_conhecimento = conhecimentos_notas_fiscais.oid_conhecimento " +
//    						   " and conhecimentos_notas_fiscais.oid_nota_fiscal = '"+resDT.getString("oid_nota_fiscal")+"'";
//    				rs = null;
//    				rs = this.executasql.executarConsulta(sqlBusca);
//    				while(rs.next()) edVolta.setNR_Conhecimento(rs.getString("nr_conhecimento"));
//    			}
    			util.closeResultset(rs);
    			util.closeResultset(resDT);

    		}

    		util.closeResultset(res);

    		EmbarqueRL Embarque_rl = new EmbarqueRL();
    		Embarque_rl.geraInformeEmbarque(edVolta, response);
    	}
    	catch (Excecoes e){
    		throw e;
    	}
    	catch(Exception exc){
    		Excecoes exce = new Excecoes();
    		exc.printStackTrace();
    		exce.setExc(exc);
    		exce.setMensagem("Erro no médo listar");
    		exce.setClasse(this.getClass().getName());
    		exce.setMetodo("geraRelatorio(EmbarqueED ed)");
    	}

    }

  public void relEmbarque(EmbarqueED ed, HttpServletResponse response)throws Excecoes{

      String sql = null;
      ArrayList list = new ArrayList();

      try{

        sql =  "SELECT "+
        " distinct(Embarques.OID_Embarque),  "+
        " Embarques.NR_Embarque,  "+
        " Embarques.DM_Carga_Veiculo,  "+
        " Embarques.NR_Placa,  "+
        " Embarques.OID_Veiculo_Carreta,  "+
        " Embarques.NM_Motorista, "+
        " Embarques.NR_Celular,  "+
        " Embarques.DM_Expresso,  "+
        " Embarques.DM_Situacao, "+
        " Embarques.DT_Previsao_Chegada, "+
        " Embarques.HR_Previsao_Chegada, "+
        " Embarques.DT_Nova_Previsao_Chegada, "+
        " Embarques.HR_Nova_Previsao_Chegada, "+
        " Embarques.DT_Chegada, "+
        " Embarques.HR_Chegada, "+
        " Embarques.DT_Emissao,  "+
        " Embarques.nr_odometro_inicial, "+ //Teo 16/09
        " Tipos_Veiculos.NM_TIPO_VEICULO,  "+
        " Cidade_Origem.NM_Cidade as NM_Cidade_Origem, "+
        " Cidade_Destino.NM_Cidade as NM_Cidade_Destino,  "+
        " estados.cd_estado, estados.nm_estado ";

        if (ed.getDM_Tipo_Embarque().equals("I")){
            sql += ", Conhecimentos_Internacionais.oid_pessoa ";
            sql += ", Conhecimentos_Internacionais.oid_pessoa_destinatario ";
        }
        if (ed.getDM_Tipo_Embarque().equals("N")){
            sql += ", Conhecimentos.oid_pessoa ";
            sql += ", Conhecimentos.oid_pessoa_destinatario ";
        }

        sql +=" from  "+
        " Embarques,  "+
        " Veiculos,  "+
        " Modelos_Veiculos,  "+
        " Tipos_Veiculos,  "+
        " Complementos_Veiculos,  "+
        " Estados,  "+
        " Regioes_Estados,  "+
        " Cidades Cidade_Origem,  "+
        " Cidades Cidade_Destino ";
        sql += " WHERE Embarques.OID_Cidade_Destino = Cidade_Destino.OID_Cidade ";
        sql += " AND Embarques.OID_VEICULO = Veiculos.OID_VEICULO ";
        sql += " AND Veiculos.OID_MODELO_VEICULO = Modelos_Veiculos.OID_MODELO_VEICULO ";
        sql += " AND Tipos_Veiculos.OID_TIPO_VEICULO = Modelos_Veiculos.OID_TIPO_VEICULO ";
        sql += " AND Veiculos.OID_VEICULO = Complementos_Veiculos.OID_VEICULO ";
        sql += " AND Embarques.OID_Cidade = Cidade_Origem.OID_Cidade ";
        sql += " and cidade_destino.oid_regiao_estado = regioes_estados.oid_regiao_estado  ";
        sql += " and regioes_estados.oid_estado = estados.oid_estado ";


        if ((String.valueOf(ed.getOID_Cidade_Apoio()) != null &&
            !String.valueOf(ed.getOID_Cidade_Apoio()).equals("null") &&
            !String.valueOf(ed.getOID_Cidade_Apoio()).equals("0")) ||
            (String.valueOf(ed.getOID_Rota()) != null &&
            !String.valueOf(ed.getOID_Rota()).equals("null") &&
            !String.valueOf(ed.getOID_Rota()).equals("0"))){
	          sql =  "SELECT "+
	          " Embarques.OID_Embarque,  "+
	          " Embarques.NR_Embarque,  "+
	          " Embarques.DM_Carga_Veiculo,  "+
	          " Embarques.NR_Placa,  "+
	          " Embarques.OID_Veiculo_Carreta,  "+
	          " Embarques.NM_Motorista, "+
	          " Embarques.NR_Celular,  "+
	          " Embarques.DM_Expresso,  "+
	          " Embarques.DM_Situacao, "+
	          " Embarques.DT_Previsao_Chegada, "+
	          " Embarques.HR_Previsao_Chegada, "+
	          " Embarques.DT_Nova_Previsao_Chegada, "+
	          " Embarques.HR_Nova_Previsao_Chegada, "+
	          " Embarques.DT_Chegada, "+
	          " Embarques.HR_Chegada, "+
	          " Embarques.DT_Emissao,  "+
	          " Embarques.nr_odometro_inicial, "+ //Teo 16/09
	          " Tipos_Veiculos.NM_TIPO_VEICULO,  "+
	          " Cidade_Origem.NM_Cidade as NM_Cidade_Origem, "+
	          " Cidade_Destino.NM_Cidade as NM_Cidade_Destino  ";

	          if (ed.getDM_Tipo_Embarque().equals("I")){
	              sql += ", Conhecimentos_Internacionais.oid_pessoa ";
	              sql += ", Conhecimentos_Internacionais.oid_pessoa_destinatario ";
	          }
	          if (ed.getDM_Tipo_Embarque().equals("N")){
	              sql += ", Conhecimentos.oid_pessoa ";
	              sql += ", Conhecimentos.oid_pessoa_destinatario ";
	          }

	          sql += " from  "+
	          " Embarques,  "+
	          " Roteiros,  "+
	          " Rotas_Roteiros,  "+
	          " Rotas,  "+
	          " Veiculos,  "+
	          " Modelos_Veiculos,  "+
	          " Tipos_Veiculos,  "+
	          " Complementos_Veiculos,  "+
	          " Estados,  "+
	          " Regioes_Estados,  "+
	          " Cidades Cidade_Origem,  "+
	          " Cidades Cidade_Destino ";
	          sql += " WHERE Embarques.OID_Cidade_Destino = Cidade_Destino.OID_Cidade ";
	          sql += " AND Embarques.OID_VEICULO = Veiculos.OID_VEICULO ";
	          sql += " AND Veiculos.OID_MODELO_VEICULO = Modelos_Veiculos.OID_MODELO_VEICULO ";
	          sql += " AND Tipos_Veiculos.OID_TIPO_VEICULO = Modelos_Veiculos.OID_TIPO_VEICULO ";
	          sql += " AND Veiculos.OID_VEICULO = Complementos_Veiculos.OID_VEICULO ";
	          sql += " AND Embarques.OID_Cidade = Cidade_Origem.OID_Cidade ";
	          sql += " AND Embarques.CD_ROTEIRO = Roteiros.CD_ROTEIRO ";
	          sql += " AND Rotas.OID_Rota = Rotas_Roteiros.OID_Rota ";
	          sql += " AND Roteiros.CD_Roteiro = Rotas_Roteiros.CD_Roteiro ";
	          sql += " and cidade_destino.oid_regiao_estado = regioes_estados.oid_regiao_estado  ";
	          sql += " and regioes_estados.oid_estado = estados.oid_estado ";
// System.out.println("opcao 3");

        }

        if ((String.valueOf(ed.getOID_Tipo_Veiculo_Carreta()) != null &&
            !String.valueOf(ed.getOID_Tipo_Veiculo_Carreta()).equals("null") &&
            !String.valueOf(ed.getOID_Tipo_Veiculo_Carreta()).equals("0"))){
	          sql =  "SELECT "+
	          " Embarques.OID_Embarque,  "+
	          " Embarques.NR_Embarque,  "+
	          " Embarques.DM_Carga_Veiculo,  "+
	          " Embarques.NR_Placa,  "+
	          " Embarques.OID_Veiculo_Carreta,  "+
	          " Embarques.NM_Motorista, "+
	          " Embarques.NR_Celular,  "+
	          " Embarques.DM_Expresso,  "+
	          " Embarques.DM_Situacao, "+
	          " Embarques.DT_Previsao_Chegada, "+
	          " Embarques.HR_Previsao_Chegada, "+
	          " Embarques.DT_Nova_Previsao_Chegada, "+
	          " Embarques.HR_Nova_Previsao_Chegada, "+
	          " Embarques.DT_Chegada, "+
	          " Embarques.HR_Chegada, "+
	          " Embarques.DT_Emissao,  "+
	          " Embarques.nr_odometro_inicial, "+ //Teo 16/09
	          " Tipos_Veiculos.NM_TIPO_VEICULO,  "+
	          " Cidade_Origem.NM_Cidade as NM_Cidade_Origem, "+
	          " Cidade_Destino.NM_Cidade as NM_Cidade_Destino  ";
	          if (ed.getDM_Tipo_Embarque().equals("I")){
	              sql += ", Conhecimentos_Internacionais.oid_pessoa ";
	              sql += ", Conhecimentos_Internacionais.oid_pessoa_destinatario ";
	          }
	          if (ed.getDM_Tipo_Embarque().equals("N")){
	              sql += ", Conhecimentos.oid_pessoa ";
	              sql += ", Conhecimentos.oid_pessoa_destinatario ";
	          }
	          sql += " from  "+
	          " Embarques,  "+
	          " Veiculos,  "+
	          " Modelos_Veiculos,  "+
	          " Tipos_Veiculos,  "+
	          " Estados,  "+
	          " Regioes_Estados,  "+
	          " Complementos_Veiculos,  "+
	          " Cidades Cidade_Origem,  "+
	          " Cidades Cidade_Destino ";
	          sql += " WHERE Embarques.OID_Cidade_Destino = Cidade_Destino.OID_Cidade ";
	          sql += " AND Embarques.OID_VEICULO_Carreta = Veiculos.OID_VEICULO ";
	          sql += " AND Veiculos.OID_MODELO_VEICULO = Modelos_Veiculos.OID_MODELO_VEICULO ";
	          sql += " AND Tipos_Veiculos.OID_TIPO_VEICULO = Modelos_Veiculos.OID_TIPO_VEICULO ";
	          sql += " AND Veiculos.OID_VEICULO = Complementos_Veiculos.OID_VEICULO ";
	          sql += " AND Embarques.OID_Cidade = Cidade_Origem.OID_Cidade ";
	          sql += " and cidade_destino.oid_regiao_estado = regioes_estados.oid_regiao_estado  ";
	          sql += " and regioes_estados.oid_estado = estados.oid_estado ";
// System.out.println("opcao 4");

        }

        if  (String.valueOf(ed.getOID_Rota()) != null &&
            !String.valueOf(ed.getOID_Rota()).equals("null") &&
            !String.valueOf(ed.getOID_Rota()).equals("0")){
          sql += " and Rotas.OID_Rota = " + ed.getOID_Rota();
        }

        if (String.valueOf(ed.getOID_Cidade_Apoio()) != null &&
            !String.valueOf(ed.getOID_Cidade_Apoio()).equals("null") &&
            !String.valueOf(ed.getOID_Cidade_Apoio()).equals("0")){
          sql += " and Embarques.OID_Cidade_Apoio = " + ed.getOID_Cidade_Apoio();
        }

        if (String.valueOf(ed.getDM_Carga_Veiculo()) != null &&
          !String.valueOf(ed.getDM_Carga_Veiculo()).equals("") &&
          !String.valueOf(ed.getDM_Carga_Veiculo()).equals("T") &&
          !String.valueOf(ed.getDM_Carga_Veiculo()).equals("null")){
          sql += " and Embarques.DM_Carga_Veiculo = '" + ed.getDM_Carga_Veiculo() + "'";
        }

        if (String.valueOf(ed.getDM_Tipo_Frota()) != null &&
          !String.valueOf(ed.getDM_Tipo_Frota()).equals("") &&
          !String.valueOf(ed.getDM_Tipo_Frota()).equals("D") &&
          !String.valueOf(ed.getDM_Tipo_Frota()).equals("null")){
          sql += " and Complementos_Veiculos.DM_PROCEDENCIA = '" + ed.getDM_Tipo_Frota() + "'";
        }

        if (String.valueOf(ed.getNM_Motorista()) != null &&
          !String.valueOf(ed.getNM_Motorista()).equals("") &&
          !String.valueOf(ed.getNM_Motorista()).equals("null")){
          sql += " and Embarques.NM_Motorista = '" + ed.getNM_Motorista() + "'";
        }
        if (String.valueOf(ed.getDM_Situacao()) != null &&
          !String.valueOf(ed.getDM_Situacao()).equals("") &&
          !String.valueOf(ed.getDM_Situacao()).equals("null") &&
          !String.valueOf(ed.getDM_Situacao()).equals("T")){
          sql += " and Embarques.DM_Situacao = '" + ed.getDM_Situacao() + "'";
        }
        if (String.valueOf(ed.getDT_Emissao()) != null &&
          !String.valueOf(ed.getDT_Emissao()).equals("") &&
          !String.valueOf(ed.getDT_Emissao()).equals("null")){
          sql += " and Embarques.DT_Emissao >= '" + ed.getDT_Emissao() + "'";
        }
        if (String.valueOf(ed.getDT_Emissao_Final()) != null &&
          !String.valueOf(ed.getDT_Emissao_Final()).equals("") &&
          !String.valueOf(ed.getDT_Emissao_Final()).equals("null")){
          sql += " and Embarques.DT_Emissao <= '" + ed.getDT_Emissao_Final() + "'";
        }
        if (String.valueOf(ed.getDT_Previsao_Chegada()) != null &&
          !String.valueOf(ed.getDT_Previsao_Chegada()).equals("") &&
          !String.valueOf(ed.getDT_Previsao_Chegada()).equals("null")){
          sql += " and Embarques.DT_Previsao_Chegada >= '" + ed.getDT_Previsao_Chegada() + "'";
        }
        if (String.valueOf(ed.getDT_Previsao_Chegada_Final()) != null &&
          !String.valueOf(ed.getDT_Previsao_Chegada_Final()).equals("") &&
          !String.valueOf(ed.getDT_Previsao_Chegada_Final()).equals("null")){
          sql += " and Embarques.DT_Previsao_Chegada <= '" + ed.getDT_Previsao_Chegada_Final() + "'";
        }
        if (String.valueOf(ed.getDT_Entrega()) != null &&
          !String.valueOf(ed.getDT_Entrega()).equals("") &&
          !String.valueOf(ed.getDT_Entrega()).equals("null")){
          sql += " and Embarques.DT_Entrega >= '" + ed.getDT_Entrega() + "'";
        }
        if (String.valueOf(ed.getDT_Entrega_Final()) != null &&
          !String.valueOf(ed.getDT_Entrega_Final()).equals("") &&
          !String.valueOf(ed.getDT_Entrega_Final()).equals("null")){
          sql += " and Embarques.DT_Entrega <= '" + ed.getDT_Entrega_Final() + "'";
        }

        if (String.valueOf(ed.getNR_Embarque()) != null &&
            !String.valueOf(ed.getNR_Embarque()).equals("0") &&
            !String.valueOf(ed.getNR_Embarque()).equals("null")){
          sql += " and Embarques.NR_Embarque = " + ed.getNR_Embarque();
        }

        if (String.valueOf(ed.getOID_Cidade_Destino()) != null &&
            !String.valueOf(ed.getOID_Cidade_Destino()).equals("0") &&
            !String.valueOf(ed.getOID_Cidade_Destino()).equals("null")){
          sql += " and Embarques.OID_Cidade_Destino = " + ed.getOID_Cidade_Destino();
        }

        if (String.valueOf(ed.getOID_Cidade_Origem()) != null &&
            !String.valueOf(ed.getOID_Cidade_Origem()).equals("0") &&
            !String.valueOf(ed.getOID_Cidade_Origem()).equals("null")){
          sql += " and Embarques.OID_Cidade = " + ed.getOID_Cidade_Origem();
        }
        if (String.valueOf(ed.getOID_Tipo_Veiculo()) != null &&
            !String.valueOf(ed.getOID_Tipo_Veiculo()).equals("0") &&
            !String.valueOf(ed.getOID_Tipo_Veiculo()).equals("null")){
          sql += " and Tipos_Veiculos.OID_Tipo_Veiculo = " + ed.getOID_Tipo_Veiculo();
        }

        if (String.valueOf(ed.getNR_Placa()) != null &&
            !String.valueOf(ed.getNR_Placa()).equals("") &&
            !String.valueOf(ed.getNR_Placa()).equals("null")){
          sql += " and Embarques.OID_Veiculo = '" + ed.getNR_Placa() + "'";
        }

        if (String.valueOf(ed.getNR_Placa_Carreta()) != null &&
            !String.valueOf(ed.getNR_Placa_Carreta()).equals("") &&
            !String.valueOf(ed.getNR_Placa_Carreta()).equals("null")){
          sql += " and Embarques.OID_Veiculo_Carreta = '" + ed.getNR_Placa_Carreta() + "'";
        }

        if (String.valueOf(ed.getNR_Celular()) != null &&
            !String.valueOf(ed.getNR_Celular()).equals("") &&
            !String.valueOf(ed.getNR_Celular()).equals("null")){
          sql += " and Embarques.NR_Celular = '" + ed.getNR_Celular() + "'";
        }
        if ((String.valueOf(ed.getOID_Tipo_Veiculo_Carreta()) != null &&
            !String.valueOf(ed.getOID_Tipo_Veiculo_Carreta()).equals("null") &&
            !String.valueOf(ed.getOID_Tipo_Veiculo_Carreta()).equals("0"))){
  	  sql += " and Tipos_Veiculos.OID_Tipo_Veiculo = " + ed.getOID_Tipo_Veiculo_Carreta();
        }

        if ((String.valueOf(ed.getOID_Estado_Destino()) != null &&
            !String.valueOf(ed.getOID_Estado_Destino()).equals("null") &&
            !String.valueOf(ed.getOID_Estado_Destino()).equals("0"))){
  	  sql += " and estados.oid_estado = " + ed.getOID_Estado_Destino();
        }

        if (ed.getDM_Tipo_Embarque().equals("I")){
            sql += " and embarques.oid_manifesto = Manifestos_Internacionais.oid_Manifesto_Internacional";
            sql += " and Manifestos_Internacionais.oid_Manifesto_Internacional = Viagens_Internacionais.oid_Manifesto_Internacional";
            sql += " and Viagens_Internacionais.oid_Conhecimento = Conhecimentos_Internacionais.oid_Conhecimento ";

            if (String.valueOf(ed.getNR_MIC()) != null &&
                !String.valueOf(ed.getNR_MIC()).equals("") &&
                !String.valueOf(ed.getNR_MIC()).equals("null")){
                  sql += " and Manifestos_Internacionais.NR_Manifesto_Internacional = '" + ed.getNR_MIC() + "'";
            }
            if (String.valueOf(ed.getNR_CRT()) != null &&
                !String.valueOf(ed.getNR_CRT()).equals("") &&
                !String.valueOf(ed.getNR_CRT()).equals("null")){
                  sql += " and Conhecimentos_Internacionais.NR_Conhecimento = '" + ed.getNR_CRT() + "'";
            }
            if (JavaUtil.doValida(ed.getOID_Pessoa())){
                sql += " and Conhecimentos_Internacionais.oid_pessoa = '" + ed.getOID_Pessoa() + "'";
            }
            if (JavaUtil.doValida(ed.getOID_Pessoa_Destinatario())){
                sql += " and Conhecimentos_Internacionais.oid_pessoa_destinatario = '" + ed.getOID_Pessoa_Destinatario() + "'";
            }
        }
        if (ed.getDM_Tipo_Embarque().equals("N")){
            sql += " and embarques.oid_manifesto = Manifestos.oid_Manifesto";
            sql += " and viagens.oid_manifesto = Manifestos.oid_Manifesto";
            sql += " and viagens.oid_conhecimento = Conhecimentos.oid_conhecimento";

            if (String.valueOf(ed.getNR_Manifesto()) != null &&
                !String.valueOf(ed.getNR_Manifesto()).equals("") &&
                !String.valueOf(ed.getNR_Manifesto()).equals("null")){
                  sql += " and Manifestos.NR_Manifesto = '" + ed.getNR_Manifesto() + "'";
            }
            if (String.valueOf(ed.getNR_Conhecimento()) != null &&
                !String.valueOf(ed.getNR_Conhecimento()).equals("") &&
                !String.valueOf(ed.getNR_Conhecimento()).equals("null")){
                  sql += " and Conhecimentos.NR_Conhecimento = '" + ed.getNR_Conhecimento() + "'";
            }
            if (JavaUtil.doValida(ed.getOID_Pessoa())){
                sql += " and Conhecimentos.oid_pessoa = '" + ed.getOID_Pessoa() + "'";
            }
            if (JavaUtil.doValida(ed.getOID_Pessoa_Destinatario()) && ed.getDM_Tipo_Embarque().equals("N")){
                sql += " and Conhecimentos.oid_pessoa_destinatario = '" + ed.getOID_Pessoa_Destinatario() + "'";
            }
        }

        if (String.valueOf(ed.getNR_Chassi()) != null &&
                !String.valueOf(ed.getNR_Chassi()).equals("") &&
                !String.valueOf(ed.getNR_Chassi()).equals("null")){
              sql += " and Embarques.NR_Chassi = '" + ed.getNR_Chassi() + "'";
        }
        if (String.valueOf(ed.getNR_Carro()) != null &&
                !String.valueOf(ed.getNR_Carro()).equals("") &&
                !String.valueOf(ed.getNR_Carro()).equals("null")){
              sql += " and Embarques.NR_Carro = '" + ed.getNR_Carro() + "'";
        }

        if (ed.getDM_Tipo_Embarque().equals("I")){
            if (JavaUtil.doValida(ed.getOID_Pessoa_Destinatario())){
                sql += " Order by Conhecimentos_Internacionais.oid_pessoa_destinatario, Conhecimentos_Internacionais.oid_pessoa, Embarques.DT_Previsao_Chegada, Embarques.DT_Nova_Previsao_Chegada ";
            } else {
                sql += " Order by Conhecimentos_Internacionais.oid_pessoa, Conhecimentos_Internacionais.oid_pessoa_destinatario, Embarques.DT_Previsao_Chegada, Embarques.DT_Nova_Previsao_Chegada ";
            }
        } else if (ed.getDM_Tipo_Embarque().equals("N")){
            if (JavaUtil.doValida(ed.getOID_Pessoa_Destinatario())){
                sql += " Order by Conhecimentos.oid_pessoa_destinatario, Conhecimentos.oid_pessoa, Embarques.DT_Previsao_Chegada, Embarques.DT_Nova_Previsao_Chegada ";
            } else {
                sql += " Order by Conhecimentos.oid_pessoa, Conhecimentos.oid_pessoa_destinatario, Embarques.DT_Previsao_Chegada, Embarques.DT_Nova_Previsao_Chegada ";
            }
        } else {
            sql += " Order by Embarques.DT_Previsao_Chegada, Embarques.DT_Nova_Previsao_Chegada ";
        }
        ResultSet res = null;
// System.out.println(sql);
        res = this.executasql.executarConsulta(sql);
        FormataDataBean DataFormatada = new FormataDataBean();

        //popula
        while (res.next()){

// System.out.println("1");

          EmbarqueED edVolta = new EmbarqueED();

          edVolta.setOID_Embarque(res.getLong("OID_Embarque"));

          edVolta.setNR_Embarque(res.getLong("NR_Embarque"));
          edVolta.setNR_Placa(res.getString("NR_Placa"));
          edVolta.setNR_Placa_Carreta(res.getString("OID_Veiculo_Carreta"));
          edVolta.setDT_Emissao(res.getString("DT_Emissao"));
          DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
          edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());
          edVolta.setNM_Cidade_Origem(res.getString("NM_Cidade_Origem"));
          edVolta.setNM_Cidade_Destino(res.getString("NM_Cidade_Destino"));

          edVolta.setDT_Previsao_Chegada(res.getString("DT_Nova_Previsao_Chegada"));

          if (edVolta.getDT_Previsao_Chegada() != null ){
            DataFormatada.setDT_FormataData(edVolta.getDT_Previsao_Chegada());
            edVolta.setDT_Previsao_Chegada(DataFormatada.getDT_FormataData()+" - "+res.getString("HR_Nova_Previsao_Chegada"));
          }else{
            edVolta.setDT_Previsao_Chegada(res.getString("DT_Previsao_Chegada"));
            DataFormatada.setDT_FormataData(edVolta.getDT_Previsao_Chegada());
            edVolta.setDT_Previsao_Chegada(DataFormatada.getDT_FormataData()+" - "+res.getString("HR_Previsao_Chegada"));
          }

// System.out.println("20");
		  String RD = "";
		  if ( (ed.getDM_Tipo_Embarque().equals("I") || ed.getDM_Tipo_Embarque().equals("N"))
		          && (JavaUtil.doValida(ed.getOID_Pessoa()) || (JavaUtil.doValida(ed.getOID_Pessoa_Destinatario()))) )  {
		      RD = PessoaBean.getByOID(res.getString("oid_Pessoa")).getNM_Razao_Social();
		      RD += " - ";
		      RD += PessoaBean.getByOID(res.getString("oid_Pessoa_Destinatario")).getNM_Razao_Social();
		  }
		  edVolta.setREM_DEST(RD);

          edVolta.setDT_Chegada(res.getString("DT_Chegada"));
          DataFormatada.setDT_FormataData(edVolta.getDT_Chegada());
          edVolta.setDT_Chegada(DataFormatada.getDT_FormataData());

          edVolta.setHR_Chegada(res.getString("HR_Chegada"));

          edVolta.setDM_Situacao(res.getString("DM_Situacao"));
          switch(new Integer(edVolta.getDM_Situacao()).intValue()){
          case 0:
              edVolta.setDM_Situacao("Atrasado");
            break;
          case 3:
              edVolta.setDM_Situacao("Em Trânsito");
            break;
          case 5:
              edVolta.setDM_Situacao("Embarcando");
            break;
          case 7:
              edVolta.setDM_Situacao("Chegou");
            break;
          case 8:
              edVolta.setDM_Situacao("Entregue");
            break;
          case 9:
              edVolta.setDM_Situacao("Não Embarcado");
            break;
          case 10:
              edVolta.setDM_Situacao("Parada Autorizada");
            break;
        }

          sql =  " SELECT Tipos_Ocorrencias.NM_Tipo_Ocorrencia, Ocorrencias_Embarques.TX_DESCRICAO, Ocorrencias_Embarques.HR_Ocorrencia_Embarque, Ocorrencias_Embarques.DT_Ocorrencia_Embarque from Ocorrencias_Embarques, Tipos_Ocorrencias ";
          sql += " WHERE Ocorrencias_Embarques.OID_Tipo_Ocorrencia = Tipos_Ocorrencias.OID_Tipo_Ocorrencia ";
          sql += " AND Ocorrencias_Embarques.OID_Embarque = " + edVolta.getOID_Embarque();
          sql += " AND Ocorrencias_Embarques.HR_OCORRENCIA_EMBARQUE is not null";
          sql += " ORDER BY Ocorrencias_Embarques.DT_OCORRENCIA_EMBARQUE, HR_OCORRENCIA_EMBARQUE asc";

// System.out.println("40");

          ResultSet resTB = null;
          resTB = this.executasql.executarConsulta(sql);

          edVolta.setTX_Descricao("* - * - *");
          edVolta.setDT_Ocorrencia_Embarque("*");

          while (resTB.next()){
// System.out.println("44");
            edVolta.setDT_Ocorrencia_Embarque(resTB.getString("DT_Ocorrencia_Embarque"));
            DataFormatada.setDT_FormataData(edVolta.getDT_Ocorrencia_Embarque());
            edVolta.setDT_Ocorrencia_Embarque(DataFormatada.getDT_FormataData());
            edVolta.setTX_Descricao(edVolta.getDT_Ocorrencia_Embarque()+" - "+resTB.getString("HR_Ocorrencia_Embarque")+" - "+resTB.getString("TX_DESCRICAO"));
          }
          list.add(edVolta);
        }

        new EmbarqueRL().relEmbarque(list, ed, response);

      }
      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao listar Embarque");
        excecoes.setMetodo("lista");
        excecoes.setExc(exc);
        throw excecoes;
      }
    }


  public void relChegadaEntrega(EmbarqueED ed, HttpServletResponse response)throws Excecoes{

      String sql = null;
      ArrayList list = new ArrayList();

      try{

        if (ed.getDM_Tipo_Embarque().equals("I")){
            sql =  "SELECT "+
            	   " Conhecimentos_Internacionais.nr_conhecimento, Conhecimentos_Internacionais.cd_pais, Conhecimentos_Internacionais.nr_permisso, " +
            	   " Conhecimentos_Internacionais.dt_emissao, Embarques.DT_Chegada, Embarques.DT_Entrega, " +
            	   " Conhecimentos_Internacionais.oid_pessoa, Conhecimentos_Internacionais.oid_pessoa_destinatario, " +
            	   " pessoaR.nm_razao_social as nm_remetente, pessoaD.nm_razao_social as nm_destinatario";
            sql += " from  "+
            	   " Embarques,  " +
            	   " Conhecimentos_Internacionais, "+
            	   " Pessoas pessoaR,  "+
            	   " Pessoas pessoaD ";
            sql += " WHERE embarques.oid_manifesto = Manifestos_Internacionais.oid_Manifesto_Internacional" +
            	   " and Manifestos_Internacionais.oid_Manifesto_Internacional = Viagens_Internacionais.oid_Manifesto_Internacional" +
            	   " and Viagens_Internacionais.oid_Conhecimento = Conhecimentos_Internacionais.oid_Conhecimento " +
            	   " and Conhecimentos_Internacionais.oid_pessoa = pessoaR.oid_pessoa " +
            	   " and Conhecimentos_Internacionais.oid_pessoa_destinatario = pessoaD.oid_pessoa ";
        }
        if (ed.getDM_Tipo_Embarque().equals("N")){

            sql =  "SELECT "+
		     	   " Conhecimentos.nr_conhecimento, " +
		     	   " Conhecimentos.dt_emissao, Embarques.DT_Chegada, Embarques.DT_Entrega, " +
		     	   " Conhecimentos.oid_pessoa, Conhecimentos.oid_pessoa_destinatario, " +
		     	   " pessoaR.nm_razao_social as nm_remetente, pessoaD.nm_razao_social as nm_destinatario";
		    sql += " from  "+
		     	   " Embarques,  " +
		     	   " Conhecimentos, "+
		     	   " Pessoas pessoaR,  "+
		     	   " Pessoas pessoaD ";
		    sql += " WHERE embarques.oid_manifesto = Manifestos.oid_Manifesto " +
		     	   " and viagens.oid_manifesto = Manifestos.oid_Manifesto " +
		     	   " and viagens.oid_conhecimento = Conhecimentos.oid_conhecimento " +
		     	   " and Conhecimentos.oid_pessoa = pessoaR.oid_pessoa " +
		     	   " and Conhecimentos.oid_pessoa_destinatario = pessoaD.oid_pessoa ";
        }

        sql += " and Embarques.DT_Chegada is not null ";

        if (String.valueOf(ed.getDT_Emissao()) != null &&
          !String.valueOf(ed.getDT_Emissao()).equals("") &&
          !String.valueOf(ed.getDT_Emissao()).equals("null")){
          sql += " and Embarques.DT_Emissao >= '" + ed.getDT_Emissao() + "'";
        }
        if (String.valueOf(ed.getDT_Emissao_Final()) != null &&
          !String.valueOf(ed.getDT_Emissao_Final()).equals("") &&
          !String.valueOf(ed.getDT_Emissao_Final()).equals("null")){
          sql += " and Embarques.DT_Emissao <= '" + ed.getDT_Emissao_Final() + "'";
        }
        if (String.valueOf(ed.getDT_Previsao_Chegada()) != null &&
          !String.valueOf(ed.getDT_Previsao_Chegada()).equals("") &&
          !String.valueOf(ed.getDT_Previsao_Chegada()).equals("null")){
          sql += " and Embarques.DT_Chegada >= '" + ed.getDT_Previsao_Chegada() + "'";
        }
        if (String.valueOf(ed.getDT_Previsao_Chegada_Final()) != null &&
          !String.valueOf(ed.getDT_Previsao_Chegada_Final()).equals("") &&
          !String.valueOf(ed.getDT_Previsao_Chegada_Final()).equals("null")){
          sql += " and Embarques.DT_Chegada <= '" + ed.getDT_Previsao_Chegada_Final() + "'";
        }
        if (String.valueOf(ed.getDT_Entrega()) != null &&
          !String.valueOf(ed.getDT_Entrega()).equals("") &&
          !String.valueOf(ed.getDT_Entrega()).equals("null")){
          sql += " and Embarques.DT_Entrega >= '" + ed.getDT_Entrega() + "'";
        }
        if (String.valueOf(ed.getDT_Entrega_Final()) != null &&
          !String.valueOf(ed.getDT_Entrega_Final()).equals("") &&
          !String.valueOf(ed.getDT_Entrega_Final()).equals("null")){
          sql += " and Embarques.DT_Entrega <= '" + ed.getDT_Entrega_Final() + "'";
        }

        if (String.valueOf(ed.getNR_Embarque()) != null &&
            !String.valueOf(ed.getNR_Embarque()).equals("0") &&
            !String.valueOf(ed.getNR_Embarque()).equals("null")){
          sql += " and Embarques.NR_Embarque = " + ed.getNR_Embarque();
        }

        if (String.valueOf(ed.getOID_Cidade_Destino()) != null &&
            !String.valueOf(ed.getOID_Cidade_Destino()).equals("0") &&
            !String.valueOf(ed.getOID_Cidade_Destino()).equals("null")){
          sql += " and Embarques.OID_Cidade_Destino = " + ed.getOID_Cidade_Destino();
        }

        if (String.valueOf(ed.getOID_Cidade_Origem()) != null &&
            !String.valueOf(ed.getOID_Cidade_Origem()).equals("0") &&
            !String.valueOf(ed.getOID_Cidade_Origem()).equals("null")){
          sql += " and Embarques.OID_Cidade = " + ed.getOID_Cidade_Origem();
        }

        if (String.valueOf(ed.getNR_Placa()) != null &&
            !String.valueOf(ed.getNR_Placa()).equals("") &&
            !String.valueOf(ed.getNR_Placa()).equals("null")){
          sql += " and Embarques.OID_Veiculo = '" + ed.getNR_Placa() + "'";
        }

        if (String.valueOf(ed.getNR_Placa_Carreta()) != null &&
            !String.valueOf(ed.getNR_Placa_Carreta()).equals("") &&
            !String.valueOf(ed.getNR_Placa_Carreta()).equals("null")){
          sql += " and Embarques.OID_Veiculo_Carreta = '" + ed.getNR_Placa_Carreta() + "'";
        }

        if (ed.getDM_Tipo_Embarque().equals("I")){
            if (String.valueOf(ed.getNR_MIC()) != null &&
                !String.valueOf(ed.getNR_MIC()).equals("") &&
                !String.valueOf(ed.getNR_MIC()).equals("null")){
                  sql += " and Manifestos_Internacionais.NR_Manifesto_Internacional = '" + ed.getNR_MIC() + "'";
            }
            if (String.valueOf(ed.getNR_CRT()) != null &&
                !String.valueOf(ed.getNR_CRT()).equals("") &&
                !String.valueOf(ed.getNR_CRT()).equals("null")){
                  sql += " and Conhecimentos_Internacionais.NR_Conhecimento = '" + ed.getNR_CRT() + "'";
            }
            if (JavaUtil.doValida(ed.getOID_Pessoa())){
                sql += " and Conhecimentos_Internacionais.oid_pessoa = '" + ed.getOID_Pessoa() + "'";
            }
            if (JavaUtil.doValida(ed.getOID_Pessoa_Destinatario())){
                sql += " and Conhecimentos_Internacionais.oid_pessoa_destinatario = '" + ed.getOID_Pessoa_Destinatario() + "'";
            }
        }
        if (ed.getDM_Tipo_Embarque().equals("N")){
            if (String.valueOf(ed.getNR_Manifesto()) != null &&
                !String.valueOf(ed.getNR_Manifesto()).equals("") &&
                !String.valueOf(ed.getNR_Manifesto()).equals("null")){
                  sql += " and Manifestos.NR_Manifesto = '" + ed.getNR_Manifesto() + "'";
            }
            if (String.valueOf(ed.getNR_Conhecimento()) != null &&
                !String.valueOf(ed.getNR_Conhecimento()).equals("") &&
                !String.valueOf(ed.getNR_Conhecimento()).equals("null")){
                  sql += " and Conhecimentos.NR_Conhecimento = '" + ed.getNR_Conhecimento() + "'";
            }
            if (JavaUtil.doValida(ed.getOID_Pessoa())){
                sql += " and Conhecimentos.oid_pessoa = '" + ed.getOID_Pessoa() + "'";
            }
            if (JavaUtil.doValida(ed.getOID_Pessoa_Destinatario()) && ed.getDM_Tipo_Embarque().equals("N")){
                sql += " and Conhecimentos.oid_pessoa_destinatario = '" + ed.getOID_Pessoa_Destinatario() + "'";
            }
        }

        if (String.valueOf(ed.getNR_Chassi()) != null &&
                !String.valueOf(ed.getNR_Chassi()).equals("") &&
                !String.valueOf(ed.getNR_Chassi()).equals("null")){
              sql += " and Embarques.NR_Chassi = '" + ed.getNR_Chassi() + "'";
        }
        if (String.valueOf(ed.getNR_Carro()) != null &&
                !String.valueOf(ed.getNR_Carro()).equals("") &&
                !String.valueOf(ed.getNR_Carro()).equals("null")){
              sql += " and Embarques.NR_Carro = '" + ed.getNR_Carro() + "'";
        }

        if (ed.getDM_Tipo_Embarque().equals("I")){
            if (JavaUtil.doValida(ed.getOID_Pessoa_Destinatario())){
                sql += " Order by Conhecimentos_Internacionais.oid_pessoa_destinatario, Conhecimentos_Internacionais.oid_pessoa, Embarques.DT_Chegada, Embarques.DT_Entrega ";
            } else {
                sql += " Order by Conhecimentos_Internacionais.oid_pessoa, Conhecimentos_Internacionais.oid_pessoa_destinatario, Embarques.DT_Chegada, Embarques.DT_Entrega ";
            }
        } else if (ed.getDM_Tipo_Embarque().equals("N")){
            if (JavaUtil.doValida(ed.getOID_Pessoa_Destinatario()) && ed.getDM_Tipo_Embarque().equals("N")){
                sql += " Order by Conhecimentos.oid_pessoa_destinatario, Conhecimentos.oid_pessoa, Embarques.DT_Chegada, Embarques.DT_Entrega ";
            } else {
                sql += " Order by Conhecimentos.oid_pessoa, Conhecimentos.oid_pessoa_destinatario, Embarques.DT_Chegada, Embarques.DT_Entrega ";
            }
        } else {
            sql += " Order by Embarques.DT_Chegada, Embarques.DT_Entrega ";
        }
        ResultSet res = null;
// System.out.println(sql);
        res = this.executasql.executarConsulta(sql);
        FormataDataBean DataFormatada = new FormataDataBean();

        //popula
        while (res.next()){

// System.out.println("1");

          EmbarqueED edVolta = new EmbarqueED();

          if (ed.getDM_Tipo_Embarque().equals("I")){
	          String nr_Conhecimento = res.getString("NR_CONHECIMENTO");
	          String nr_CRT_Parcial = res.getString("CD_Pais") + "." +
								      res.getString("NR_Permisso") + ".";
	          int completa_Nr_CRT = 13 - nr_CRT_Parcial.length() - nr_Conhecimento.length();
	          for(int a = 0 ; a < completa_Nr_CRT ; a++){
	              nr_CRT_Parcial = nr_CRT_Parcial + "0";
	          }
	          edVolta.setNR_Conhecimento(nr_CRT_Parcial+nr_Conhecimento);
          } else
              edVolta.setNR_Conhecimento(res.getString("NR_CONHECIMENTO"));

          edVolta.setDT_Emissao(res.getString("DT_Emissao"));
          DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
          edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());

          edVolta.setNM_Remetente(res.getString("NM_Remetente"));
          edVolta.setNM_Destinatario(res.getString("NM_Destinatario"));

          edVolta.setDT_Chegada(res.getString("DT_Chegada"));
          DataFormatada.setDT_FormataData(edVolta.getDT_Chegada());
          edVolta.setDT_Chegada(DataFormatada.getDT_FormataData());

          edVolta.setDT_Entrega(res.getString("DT_Entrega"));
          DataFormatada.setDT_FormataData(edVolta.getDT_Entrega());
          edVolta.setDT_Entrega(DataFormatada.getDT_FormataData());

          String data1 = edVolta.getDT_Chegada();
          String data2 = edVolta.getDT_Entrega();
          if(!JavaUtil.doValida(data2))
              data2 = Data.getDataDMY();

          edVolta.setNR_Dias(Data.diferencaDias(data1, data2));

// System.out.println("20");
		  String RD = "";
		  if ( (ed.getDM_Tipo_Embarque().equals("I") || ed.getDM_Tipo_Embarque().equals("N"))
		          && (JavaUtil.doValida(ed.getOID_Pessoa()) || (JavaUtil.doValida(ed.getOID_Pessoa_Destinatario()))) )  {
		      RD = PessoaBean.getByOID(res.getString("oid_Pessoa")).getNM_Razao_Social();
		      RD += " - ";
		      RD += PessoaBean.getByOID(res.getString("oid_Pessoa_Destinatario")).getNM_Razao_Social();
		  }
		  edVolta.setREM_DEST(RD);

          list.add(edVolta);
        }

        new EmbarqueRL().relChegadaEntrega(list, ed, response);

      }
      catch(Exception exc){
          exc.printStackTrace();
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao listar Embarque");
        excecoes.setMetodo("lista");
        excecoes.setExc(exc);
        throw excecoes;
      }
    }

  public void relTransitTime(EmbarqueED ed, HttpServletResponse response)throws Excecoes{

      String sql = null;
      ArrayList list = new ArrayList();

      try{

        if (ed.getDM_Tipo_Embarque().equals("I")){
            sql =  "SELECT "+
            	   " Conhecimentos_Internacionais.nr_conhecimento, Conhecimentos_Internacionais.cd_pais, Conhecimentos_Internacionais.nr_permisso, " +
            	   " Conhecimentos_Internacionais.dt_emissao, Embarques.DT_Chegada, Embarques.DT_Entrega, " +
            	   " Conhecimentos_Internacionais.oid_pessoa, Conhecimentos_Internacionais.oid_pessoa_destinatario, Conhecimentos_Internacionais.oid_coleta, " +
            	   " pessoaR.nm_razao_social as nm_remetente, pessoaD.nm_razao_social as nm_destinatario";
            sql += " from  "+
            	   " Embarques,  " +
            	   " Conhecimentos_Internacionais, "+
            	   " Pessoas pessoaR,  "+
            	   " Pessoas pessoaD ";
            sql += " WHERE embarques.oid_manifesto = Manifestos_Internacionais.oid_Manifesto_Internacional" +
            	   " and Manifestos_Internacionais.oid_Manifesto_Internacional = Viagens_Internacionais.oid_Manifesto_Internacional" +
            	   " and Viagens_Internacionais.oid_Conhecimento = Conhecimentos_Internacionais.oid_Conhecimento " +
            	   " and Conhecimentos_Internacionais.oid_pessoa = pessoaR.oid_pessoa " +
            	   " and Conhecimentos_Internacionais.oid_pessoa_destinatario = pessoaD.oid_pessoa ";
        }
        if (ed.getDM_Tipo_Embarque().equals("N")){

            sql =  "SELECT "+
		     	   " Conhecimentos.nr_conhecimento, " +
		     	   " Conhecimentos.dt_emissao, Embarques.DT_Chegada, Embarques.DT_Entrega, " +
		     	   " Conhecimentos.oid_pessoa, Conhecimentos.oid_pessoa_destinatario, Conhecimentos.oid_coleta, " +
		     	   " pessoaR.nm_razao_social as nm_remetente, pessoaD.nm_razao_social as nm_destinatario";
		    sql += " from  "+
		     	   " Embarques,  " +
		     	   " Conhecimentos, "+
		     	   " Pessoas pessoaR,  "+
		     	   " Pessoas pessoaD ";
		    sql += " WHERE embarques.oid_manifesto = Manifestos.oid_Manifesto " +
		     	   " and viagens.oid_manifesto = Manifestos.oid_Manifesto " +
		     	   " and viagens.oid_conhecimento = Conhecimentos.oid_conhecimento " +
		     	   " and Conhecimentos.oid_pessoa = pessoaR.oid_pessoa " +
		     	   " and Conhecimentos.oid_pessoa_destinatario = pessoaD.oid_pessoa ";
        }

        if (String.valueOf(ed.getDT_Emissao()) != null &&
          !String.valueOf(ed.getDT_Emissao()).equals("") &&
          !String.valueOf(ed.getDT_Emissao()).equals("null")){
          sql += " and Embarques.DT_Emissao >= '" + ed.getDT_Emissao() + "'";
        }
        if (String.valueOf(ed.getDT_Emissao_Final()) != null &&
          !String.valueOf(ed.getDT_Emissao_Final()).equals("") &&
          !String.valueOf(ed.getDT_Emissao_Final()).equals("null")){
          sql += " and Embarques.DT_Emissao <= '" + ed.getDT_Emissao_Final() + "'";
        }
        if (String.valueOf(ed.getDT_Previsao_Chegada()) != null &&
          !String.valueOf(ed.getDT_Previsao_Chegada()).equals("") &&
          !String.valueOf(ed.getDT_Previsao_Chegada()).equals("null")){
          sql += " and Embarques.DT_Chegada >= '" + ed.getDT_Previsao_Chegada() + "'";
        }
        if (String.valueOf(ed.getDT_Previsao_Chegada_Final()) != null &&
          !String.valueOf(ed.getDT_Previsao_Chegada_Final()).equals("") &&
          !String.valueOf(ed.getDT_Previsao_Chegada_Final()).equals("null")){
          sql += " and Embarques.DT_Chegada <= '" + ed.getDT_Previsao_Chegada_Final() + "'";
        }
        if (String.valueOf(ed.getDT_Entrega()) != null &&
          !String.valueOf(ed.getDT_Entrega()).equals("") &&
          !String.valueOf(ed.getDT_Entrega()).equals("null")){
          sql += " and Embarques.DT_Entrega >= '" + ed.getDT_Entrega() + "'";
        }
        if (String.valueOf(ed.getDT_Entrega_Final()) != null &&
          !String.valueOf(ed.getDT_Entrega_Final()).equals("") &&
          !String.valueOf(ed.getDT_Entrega_Final()).equals("null")){
          sql += " and Embarques.DT_Entrega <= '" + ed.getDT_Entrega_Final() + "'";
        }

        if (String.valueOf(ed.getNR_Embarque()) != null &&
            !String.valueOf(ed.getNR_Embarque()).equals("0") &&
            !String.valueOf(ed.getNR_Embarque()).equals("null")){
          sql += " and Embarques.NR_Embarque = " + ed.getNR_Embarque();
        }

        if (String.valueOf(ed.getOID_Cidade_Destino()) != null &&
            !String.valueOf(ed.getOID_Cidade_Destino()).equals("0") &&
            !String.valueOf(ed.getOID_Cidade_Destino()).equals("null")){
          sql += " and Embarques.OID_Cidade_Destino = " + ed.getOID_Cidade_Destino();
        }

        if (String.valueOf(ed.getOID_Cidade_Origem()) != null &&
            !String.valueOf(ed.getOID_Cidade_Origem()).equals("0") &&
            !String.valueOf(ed.getOID_Cidade_Origem()).equals("null")){
          sql += " and Embarques.OID_Cidade = " + ed.getOID_Cidade_Origem();
        }

        if (String.valueOf(ed.getNR_Placa()) != null &&
            !String.valueOf(ed.getNR_Placa()).equals("") &&
            !String.valueOf(ed.getNR_Placa()).equals("null")){
          sql += " and Embarques.OID_Veiculo = '" + ed.getNR_Placa() + "'";
        }

        if (String.valueOf(ed.getNR_Placa_Carreta()) != null &&
            !String.valueOf(ed.getNR_Placa_Carreta()).equals("") &&
            !String.valueOf(ed.getNR_Placa_Carreta()).equals("null")){
          sql += " and Embarques.OID_Veiculo_Carreta = '" + ed.getNR_Placa_Carreta() + "'";
        }

        if (ed.getDM_Tipo_Embarque().equals("I")){
            if (String.valueOf(ed.getNR_MIC()) != null &&
                !String.valueOf(ed.getNR_MIC()).equals("") &&
                !String.valueOf(ed.getNR_MIC()).equals("null")){
                  sql += " and Manifestos_Internacionais.NR_Manifesto_Internacional = '" + ed.getNR_MIC() + "'";
            }
            if (String.valueOf(ed.getNR_CRT()) != null &&
                !String.valueOf(ed.getNR_CRT()).equals("") &&
                !String.valueOf(ed.getNR_CRT()).equals("null")){
                  sql += " and Conhecimentos_Internacionais.NR_Conhecimento = '" + ed.getNR_CRT() + "'";
            }
            if (JavaUtil.doValida(ed.getOID_Pessoa())){
                sql += " and Conhecimentos_Internacionais.oid_pessoa = '" + ed.getOID_Pessoa() + "'";
            }
            if (JavaUtil.doValida(ed.getOID_Pessoa_Destinatario())){
                sql += " and Conhecimentos_Internacionais.oid_pessoa_destinatario = '" + ed.getOID_Pessoa_Destinatario() + "'";
            }
        }
        if (ed.getDM_Tipo_Embarque().equals("N")){
            if (String.valueOf(ed.getNR_Manifesto()) != null &&
                !String.valueOf(ed.getNR_Manifesto()).equals("") &&
                !String.valueOf(ed.getNR_Manifesto()).equals("null")){
                  sql += " and Manifestos.NR_Manifesto = '" + ed.getNR_Manifesto() + "'";
            }
            if (String.valueOf(ed.getNR_Conhecimento()) != null &&
                !String.valueOf(ed.getNR_Conhecimento()).equals("") &&
                !String.valueOf(ed.getNR_Conhecimento()).equals("null")){
                  sql += " and Conhecimentos.NR_Conhecimento = '" + ed.getNR_Conhecimento() + "'";
            }
            if (JavaUtil.doValida(ed.getOID_Pessoa())){
                sql += " and Conhecimentos.oid_pessoa = '" + ed.getOID_Pessoa() + "'";
            }
            if (JavaUtil.doValida(ed.getOID_Pessoa_Destinatario()) && ed.getDM_Tipo_Embarque().equals("N")){
                sql += " and Conhecimentos.oid_pessoa_destinatario = '" + ed.getOID_Pessoa_Destinatario() + "'";
            }
        }

        if (String.valueOf(ed.getNR_Chassi()) != null &&
                !String.valueOf(ed.getNR_Chassi()).equals("") &&
                !String.valueOf(ed.getNR_Chassi()).equals("null")){
              sql += " and Embarques.NR_Chassi = '" + ed.getNR_Chassi() + "'";
        }
        if (String.valueOf(ed.getNR_Carro()) != null &&
                !String.valueOf(ed.getNR_Carro()).equals("") &&
                !String.valueOf(ed.getNR_Carro()).equals("null")){
              sql += " and Embarques.NR_Carro = '" + ed.getNR_Carro() + "'";
        }

        if (ed.getDM_Tipo_Embarque().equals("I")){
            if (JavaUtil.doValida(ed.getOID_Pessoa_Destinatario())){
                sql += " Order by Conhecimentos_Internacionais.oid_pessoa_destinatario, Conhecimentos_Internacionais.oid_pessoa, Embarques.DT_Chegada, Embarques.DT_Entrega ";
            } else {
                sql += " Order by Conhecimentos_Internacionais.oid_pessoa, Conhecimentos_Internacionais.oid_pessoa_destinatario, Embarques.DT_Chegada, Embarques.DT_Entrega ";
            }
        } else if (ed.getDM_Tipo_Embarque().equals("N")){
            if (JavaUtil.doValida(ed.getOID_Pessoa_Destinatario()) && ed.getDM_Tipo_Embarque().equals("N")){
                sql += " Order by Conhecimentos.oid_pessoa_destinatario, Conhecimentos.oid_pessoa, Embarques.DT_Previsao_Chegada, Embarques.DT_Chegada, Embarques.DT_Entrega ";
            } else {
                sql += " Order by Conhecimentos.oid_pessoa, Conhecimentos.oid_pessoa_destinatario, Embarques.DT_Previsao_Chegada, Embarques.DT_Chegada, Embarques.DT_Entrega ";
            }
        } else {
            sql += " Order by Embarques.DT_Chegada, Embarques.DT_Entrega ";
        }
        ResultSet res = null;
// System.out.println(sql);
        res = this.executasql.executarConsulta(sql);
        FormataDataBean DataFormatada = new FormataDataBean();

        //popula
        while (res.next()){

// System.out.println("1");

          EmbarqueED edVolta = new EmbarqueED();

          if (ed.getDM_Tipo_Embarque().equals("I")){
	          String nr_Conhecimento = res.getString("NR_CONHECIMENTO");
	          String nr_CRT_Parcial = res.getString("CD_Pais") + "." +
								      res.getString("NR_Permisso") + ".";
	          int completa_Nr_CRT = 13 - nr_CRT_Parcial.length() - nr_Conhecimento.length();
	          for(int a = 0 ; a < completa_Nr_CRT ; a++){
	              nr_CRT_Parcial = nr_CRT_Parcial + "0";
	          }
	          edVolta.setNR_Conhecimento(nr_CRT_Parcial+nr_Conhecimento);
          } else
              edVolta.setNR_Conhecimento(res.getString("NR_CONHECIMENTO"));

          edVolta.setDT_Emissao(res.getString("DT_Emissao"));
          DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
          edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());

          edVolta.setNM_Remetente(res.getString("NM_Remetente"));
          edVolta.setNM_Destinatario(res.getString("NM_Destinatario"));

          String sqlColeta = "SELECT dt_coleta from coletas where oid_coleta = " + res.getString("oid_coleta");
          ResultSet rs = null;
          rs = this.executasql.executarConsulta(sqlColeta);
          while (rs.next()){
              edVolta.setDT_Coleta(rs.getString("DT_Coleta"));
              DataFormatada.setDT_FormataData(edVolta.getDT_Coleta());
              edVolta.setDT_Coleta(DataFormatada.getDT_FormataData());
          }

          edVolta.setDT_Chegada(res.getString("DT_Chegada"));
          DataFormatada.setDT_FormataData(edVolta.getDT_Chegada());
          edVolta.setDT_Chegada(DataFormatada.getDT_FormataData());

          edVolta.setDT_Entrega(res.getString("DT_Entrega"));
          DataFormatada.setDT_FormataData(edVolta.getDT_Entrega());
          edVolta.setDT_Entrega(DataFormatada.getDT_FormataData());

          String data1 = edVolta.getDT_Coleta();
          String data2 = edVolta.getDT_Entrega();
          if(!JavaUtil.doValida(data1))
              data1 = edVolta.getDT_Emissao();
          if(!JavaUtil.doValida(data2))
              data2 = Data.getDataDMY();

          edVolta.setNR_Dias(Data.diferencaDias(data1, data2));

// System.out.println("20");
		  String RD = "";
		  if ( (ed.getDM_Tipo_Embarque().equals("I") || ed.getDM_Tipo_Embarque().equals("N"))
		          && (JavaUtil.doValida(ed.getOID_Pessoa()) || (JavaUtil.doValida(ed.getOID_Pessoa_Destinatario()))) )  {
		      RD = PessoaBean.getByOID(res.getString("oid_Pessoa")).getNM_Razao_Social();
		      RD += " - ";
		      RD += PessoaBean.getByOID(res.getString("oid_Pessoa_Destinatario")).getNM_Razao_Social();
		  }
		  edVolta.setREM_DEST(RD);

          list.add(edVolta);
        }

        new EmbarqueRL().relTransitTime(list, ed, response);

      }
      catch(Exception exc){
          exc.printStackTrace();
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao listar Embarque");
        excecoes.setMetodo("lista");
        excecoes.setExc(exc);
        throw excecoes;
      }
    }

  public void relCRT_Armazem(EmbarqueED ed, HttpServletResponse response)throws Excecoes{

      String sql = null;
      ArrayList list = new ArrayList();
      boolean inclui = false;

      try{

        if (ed.getDM_Tipo_Embarque().equals("I")){
            sql =  "SELECT "+
            	   " Conhecimentos.nr_conhecimento, Conhecimentos.cd_pais, Conhecimentos.nr_permisso, " +
            	   " Conhecimentos.dt_emissao, Conhecimentos.oid_conhecimento, " +
            	   " Conhecimentos.oid_pessoa, Conhecimentos.oid_pessoa_destinatario, Conhecimentos.oid_coleta, " +
            	   " pessoaR.nm_razao_social as nm_remetente, pessoaD.nm_razao_social as nm_destinatario";
            sql += " from  "+
            	   " Conhecimentos_Internacionais Conhecimentos, "+
            	   " Pessoas pessoaR,  "+
            	   " Pessoas pessoaD ";
            sql += " WHERE Conhecimentos.oid_pessoa = pessoaR.oid_pessoa " +
            	   " and Conhecimentos.oid_pessoa_destinatario = pessoaD.oid_pessoa ";
        }
        if (ed.getDM_Tipo_Embarque().equals("N")){

            sql =  "SELECT "+
		     	   " Conhecimentos.nr_conhecimento, " +
		     	   " Conhecimentos.dt_emissao, Conhecimentos.oid_conhecimento, " +
		     	   " Conhecimentos.oid_pessoa, Conhecimentos.oid_pessoa_destinatario, Conhecimentos.oid_coleta, " +
		     	   " pessoaR.nm_razao_social as nm_remetente, pessoaD.nm_razao_social as nm_destinatario";
		    sql += " from  "+
		     	   " Conhecimentos, "+
		     	   " Pessoas pessoaR,  "+
		     	   " Pessoas pessoaD ";
		    sql += " WHERE Conhecimentos.oid_pessoa = pessoaR.oid_pessoa " +
		     	   " and Conhecimentos.oid_pessoa_destinatario = pessoaD.oid_pessoa ";
        }

        if (String.valueOf(ed.getDT_Emissao()) != null &&
          !String.valueOf(ed.getDT_Emissao()).equals("") &&
          !String.valueOf(ed.getDT_Emissao()).equals("null")){
          sql += " and Conhecimentos.DT_Emissao >= '" + ed.getDT_Emissao() + "'";
        }
        if (String.valueOf(ed.getDT_Emissao_Final()) != null &&
          !String.valueOf(ed.getDT_Emissao_Final()).equals("") &&
          !String.valueOf(ed.getDT_Emissao_Final()).equals("null")){
          sql += " and Conhecimentos.DT_Emissao <= '" + ed.getDT_Emissao_Final() + "'";
        }

        if (ed.getDM_Tipo_Embarque().equals("I")){

            if (String.valueOf(ed.getNR_CRT()) != null &&
                !String.valueOf(ed.getNR_CRT()).equals("") &&
                !String.valueOf(ed.getNR_CRT()).equals("null")){
                  sql += " and Conhecimentos.NR_Conhecimento = '" + ed.getNR_CRT() + "'";
            }
            if (JavaUtil.doValida(ed.getOID_Pessoa())){
                sql += " and Conhecimentos.oid_pessoa = '" + ed.getOID_Pessoa() + "'";
            }
            if (JavaUtil.doValida(ed.getOID_Pessoa_Destinatario())){
                sql += " and Conhecimentos.oid_pessoa_destinatario = '" + ed.getOID_Pessoa_Destinatario() + "'";
            }
        }
        if (ed.getDM_Tipo_Embarque().equals("N")){

            if (String.valueOf(ed.getNR_Conhecimento()) != null &&
                !String.valueOf(ed.getNR_Conhecimento()).equals("") &&
                !String.valueOf(ed.getNR_Conhecimento()).equals("null")){
                  sql += " and Conhecimentos.NR_Conhecimento = '" + ed.getNR_Conhecimento() + "'";
            }
            if (JavaUtil.doValida(ed.getOID_Pessoa())){
                sql += " and Conhecimentos.oid_pessoa = '" + ed.getOID_Pessoa() + "'";
            }
            if (JavaUtil.doValida(ed.getOID_Pessoa_Destinatario()) && ed.getDM_Tipo_Embarque().equals("N")){
                sql += " and Conhecimentos.oid_pessoa_destinatario = '" + ed.getOID_Pessoa_Destinatario() + "'";
            }
        }

       if (ed.getDM_Tipo_Embarque().equals("I")){
            if (JavaUtil.doValida(ed.getOID_Pessoa_Destinatario())){
                sql += " Order by Conhecimentos.oid_pessoa_destinatario, Conhecimentos.oid_pessoa, Conhecimentos.NR_Conhecimento ";
            } else {
                sql += " Order by Conhecimentos.oid_pessoa, Conhecimentos.oid_pessoa_destinatario, Conhecimentos.NR_Conhecimento ";
            }
        } else if (ed.getDM_Tipo_Embarque().equals("N")){
            if (JavaUtil.doValida(ed.getOID_Pessoa_Destinatario()) && ed.getDM_Tipo_Embarque().equals("N")){
                sql += " Order by Conhecimentos.oid_pessoa_destinatario, Conhecimentos.oid_pessoa, Conhecimentos.NR_Conhecimento ";
            } else {
                sql += " Order by Conhecimentos.oid_pessoa, Conhecimentos.oid_pessoa_destinatario, Conhecimentos.NR_Conhecimento ";
            }
        } else {
            sql += " Order by Conhecimentos.NR_Conhecimento ";
        }
        ResultSet res = null;
// System.out.println(sql);
        res = this.executasql.executarConsulta(sql);
        FormataDataBean DataFormatada = new FormataDataBean();

        //popula
        while (res.next()){

// System.out.println("1");
		  inclui = true;

          EmbarqueED edVolta = new EmbarqueED();

          if (ed.getDM_Tipo_Embarque().equals("I")){
	          String nr_Conhecimento = res.getString("NR_CONHECIMENTO");
	          String nr_CRT_Parcial = res.getString("CD_Pais") + "." +
								      res.getString("NR_Permisso") + ".";
	          int completa_Nr_CRT = 13 - nr_CRT_Parcial.length() - nr_Conhecimento.length();
	          for(int a = 0 ; a < completa_Nr_CRT ; a++){
	              nr_CRT_Parcial = nr_CRT_Parcial + "0";
	          }
	          edVolta.setNR_Conhecimento(nr_CRT_Parcial+nr_Conhecimento);
          } else
              edVolta.setNR_Conhecimento(res.getString("NR_CONHECIMENTO"));

          edVolta.setDT_Emissao(res.getString("DT_Emissao"));
          DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
          edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());

          edVolta.setNM_Remetente(res.getString("NM_Remetente"));
          edVolta.setNM_Destinatario(res.getString("NM_Destinatario"));

          String sqlColeta = "SELECT dt_coleta from coletas where oid_coleta = " + res.getString("oid_coleta");
          ResultSet rs = null;
          rs = this.executasql.executarConsulta(sqlColeta);
          while (rs.next()){
              edVolta.setDT_Coleta(rs.getString("DT_Coleta"));
              DataFormatada.setDT_FormataData(edVolta.getDT_Coleta());
              edVolta.setDT_Coleta(DataFormatada.getDT_FormataData());
          }

          String sqlEmbarque = "SELECT embarques.dt_saida as dt_embarque, embarques.dm_situacao " +
          		               " from embarques, viagens_internacionais, manifestos_internacionais, conhecimentos_internacionais" +
          		               " where conhecimentos_internacionais.oid_conhecimento = viagens_internacionais.oid_conhecimento " +
          		               " and viagens_internacionais.oid_manifesto_internacional = manifestos_internacionais.oid_manifesto_internacional " +
          		               " and manifestos_internacionais.oid_manifesto_internacional = embarques.oid_manifesto " +
          		               " and conhecimentos_internacionais.oid_conhecimento = '" + res.getString("oid_conhecimento") + "'";
          if (ed.getDM_Tipo_Embarque().equals("N")){
              sqlEmbarque = "SELECT embarques.dt_saida as dt_embarque, embarques.dm_situacao " +
	               		    " from embarques, viagens, manifestos, conhecimentos " +
	               		    " where conhecimentos.oid_conhecimento = viagens.oid_conhecimento " +
	               		    " and viagens.oid_manifesto = manifestos.oid_manifesto " +
	               		    " and manifestos.oid_manifesto = embarques.oid_manifesto " +
	               		    " and conhecimentos_internacionais.oid_conhecimento = '" + res.getString("oid_conhecimento") + "'";
          }

          rs = null;
          rs = this.executasql.executarConsulta(sqlEmbarque);
          while (rs.next()){
              edVolta.setDT_Entrega(rs.getString("DT_Embarque"));
              DataFormatada.setDT_FormataData(edVolta.getDT_Entrega());
              edVolta.setDT_Entrega(DataFormatada.getDT_FormataData());
              if(JavaUtil.doValida(rs.getString("dm_situacao")) &&
                     (!rs.getString("dm_situacao").equals("5") ||
                      !rs.getString("dm_situacao").equals("7") ||
                      !rs.getString("dm_situacao").equals("9"))){
                  inclui = false;
              }
          }

          String data1 = edVolta.getDT_Coleta();
          String data2 = edVolta.getDT_Entrega();
          if(!JavaUtil.doValida(data1))
              data1 = edVolta.getDT_Emissao();
          if(!JavaUtil.doValida(data2))
              data2 = Data.getDataDMY();

          edVolta.setNR_Dias(Data.diferencaDias(data1, data2));

// System.out.println("20");
		  String RD = "";
		  if ( (ed.getDM_Tipo_Embarque().equals("I") || ed.getDM_Tipo_Embarque().equals("N"))
		          && (JavaUtil.doValida(ed.getOID_Pessoa()) || (JavaUtil.doValida(ed.getOID_Pessoa_Destinatario()))) )  {
		      RD = PessoaBean.getByOID(res.getString("oid_Pessoa")).getNM_Razao_Social();
		      RD += " - ";
		      RD += PessoaBean.getByOID(res.getString("oid_Pessoa_Destinatario")).getNM_Razao_Social();
		  }
		  edVolta.setREM_DEST(RD);

          if(inclui) list.add(edVolta);
        }

        new EmbarqueRL().relCRT_Armazem(list, ed, response);

      }
      catch(Exception exc){
          exc.printStackTrace();
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao listar Embarque");
        excecoes.setMetodo("lista");
        excecoes.setExc(exc);
        throw excecoes;
      }
    }

  public void relArmazenagem(EmbarqueED ed, HttpServletResponse response)throws Excecoes{

      String sql = null;
      ArrayList list = new ArrayList();

      try{

        if (ed.getDM_Tipo_Embarque().equals("I")){
            sql =  "SELECT "+
            	   " Conhecimentos.nr_conhecimento, Conhecimentos.cd_pais, Conhecimentos.nr_permisso, " +
            	   " Conhecimentos.dt_emissao, Conhecimentos.oid_conhecimento, " +
            	   " Conhecimentos.oid_pessoa, Conhecimentos.oid_pessoa_destinatario, Conhecimentos.oid_coleta, " +
            	   " pessoaR.nm_razao_social as nm_remetente, pessoaD.nm_razao_social as nm_destinatario";
            sql += " from  "+
            	   " Conhecimentos_Internacionais Conhecimentos, "+
            	   " Pessoas pessoaR,  "+
            	   " Pessoas pessoaD ";
            sql += " WHERE Conhecimentos.oid_pessoa = pessoaR.oid_pessoa " +
            	   " and Conhecimentos.oid_pessoa_destinatario = pessoaD.oid_pessoa ";
        }
        if (ed.getDM_Tipo_Embarque().equals("N")){

            sql =  "SELECT "+
		     	   " Conhecimentos.nr_conhecimento, " +
		     	   " Conhecimentos.dt_emissao, Conhecimentos.oid_conhecimento, " +
		     	   " Conhecimentos.oid_pessoa, Conhecimentos.oid_pessoa_destinatario, Conhecimentos.oid_coleta, " +
		     	   " pessoaR.nm_razao_social as nm_remetente, pessoaD.nm_razao_social as nm_destinatario";
		    sql += " from  "+
		     	   " Conhecimentos, "+
		     	   " Pessoas pessoaR,  "+
		     	   " Pessoas pessoaD ";
		    sql += " WHERE Conhecimentos.oid_pessoa = pessoaR.oid_pessoa " +
		     	   " and Conhecimentos.oid_pessoa_destinatario = pessoaD.oid_pessoa ";
        }

        if (String.valueOf(ed.getDT_Emissao()) != null &&
          !String.valueOf(ed.getDT_Emissao()).equals("") &&
          !String.valueOf(ed.getDT_Emissao()).equals("null")){
          sql += " and Conhecimentos.DT_Emissao >= '" + ed.getDT_Emissao() + "'";
        }
        if (String.valueOf(ed.getDT_Emissao_Final()) != null &&
          !String.valueOf(ed.getDT_Emissao_Final()).equals("") &&
          !String.valueOf(ed.getDT_Emissao_Final()).equals("null")){
          sql += " and Conhecimentos.DT_Emissao <= '" + ed.getDT_Emissao_Final() + "'";
        }

        if (ed.getDM_Tipo_Embarque().equals("I")){

            if (String.valueOf(ed.getNR_CRT()) != null &&
                !String.valueOf(ed.getNR_CRT()).equals("") &&
                !String.valueOf(ed.getNR_CRT()).equals("null")){
                  sql += " and Conhecimentos.NR_Conhecimento = '" + ed.getNR_CRT() + "'";
            }
            if (JavaUtil.doValida(ed.getOID_Pessoa())){
                sql += " and Conhecimentos.oid_pessoa = '" + ed.getOID_Pessoa() + "'";
            }
            if (JavaUtil.doValida(ed.getOID_Pessoa_Destinatario())){
                sql += " and Conhecimentos.oid_pessoa_destinatario = '" + ed.getOID_Pessoa_Destinatario() + "'";
            }
        }
        if (ed.getDM_Tipo_Embarque().equals("N")){

            if (String.valueOf(ed.getNR_Conhecimento()) != null &&
                !String.valueOf(ed.getNR_Conhecimento()).equals("") &&
                !String.valueOf(ed.getNR_Conhecimento()).equals("null")){
                  sql += " and Conhecimentos.NR_Conhecimento = '" + ed.getNR_Conhecimento() + "'";
            }
            if (JavaUtil.doValida(ed.getOID_Pessoa())){
                sql += " and Conhecimentos.oid_pessoa = '" + ed.getOID_Pessoa() + "'";
            }
            if (JavaUtil.doValida(ed.getOID_Pessoa_Destinatario()) && ed.getDM_Tipo_Embarque().equals("N")){
                sql += " and Conhecimentos.oid_pessoa_destinatario = '" + ed.getOID_Pessoa_Destinatario() + "'";
            }
        }

       if (ed.getDM_Tipo_Embarque().equals("I")){
            if (JavaUtil.doValida(ed.getOID_Pessoa_Destinatario())){
                sql += " Order by Conhecimentos.oid_pessoa_destinatario, Conhecimentos.oid_pessoa, Conhecimentos.NR_Conhecimento ";
            } else {
                sql += " Order by Conhecimentos.oid_pessoa, Conhecimentos.oid_pessoa_destinatario, Conhecimentos.NR_Conhecimento ";
            }
        } else if (ed.getDM_Tipo_Embarque().equals("N")){
            if (JavaUtil.doValida(ed.getOID_Pessoa_Destinatario()) && ed.getDM_Tipo_Embarque().equals("N")){
                sql += " Order by Conhecimentos.oid_pessoa_destinatario, Conhecimentos.oid_pessoa, Conhecimentos.NR_Conhecimento ";
            } else {
                sql += " Order by Conhecimentos.oid_pessoa, Conhecimentos.oid_pessoa_destinatario, Conhecimentos.NR_Conhecimento ";
            }
        } else {
            sql += " Order by Conhecimentos.NR_Conhecimento ";
        }
        ResultSet res = null;
// System.out.println(sql);
        res = this.executasql.executarConsulta(sql);
        FormataDataBean DataFormatada = new FormataDataBean();

        //popula
        while (res.next()){
// System.out.println("1");

          EmbarqueED edVolta = new EmbarqueED();

          if (ed.getDM_Tipo_Embarque().equals("I")){
	          String nr_Conhecimento = res.getString("NR_CONHECIMENTO");
	          String nr_CRT_Parcial = res.getString("CD_Pais") + "." +
								      res.getString("NR_Permisso") + ".";
	          int completa_Nr_CRT = 13 - nr_CRT_Parcial.length() - nr_Conhecimento.length();
	          for(int a = 0 ; a < completa_Nr_CRT ; a++){
	              nr_CRT_Parcial = nr_CRT_Parcial + "0";
	          }
	          edVolta.setNR_Conhecimento(nr_CRT_Parcial+nr_Conhecimento);
          } else
              edVolta.setNR_Conhecimento(res.getString("NR_CONHECIMENTO"));

          edVolta.setDT_Emissao(res.getString("DT_Emissao"));
          DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
          edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());

          edVolta.setNM_Remetente(res.getString("NM_Remetente"));
          edVolta.setNM_Destinatario(res.getString("NM_Destinatario"));

          String sqlColeta = "SELECT dt_coleta from coletas where oid_coleta = " + res.getString("oid_coleta");
          ResultSet rs = null;
          rs = this.executasql.executarConsulta(sqlColeta);
          while (rs.next()){
              edVolta.setDT_Coleta(rs.getString("DT_Coleta"));
              DataFormatada.setDT_FormataData(edVolta.getDT_Coleta());
              edVolta.setDT_Coleta(DataFormatada.getDT_FormataData());
          }

          String sqlEmbarque = "SELECT embarques.dt_saida as dt_embarque " +
          		               " from embarques, viagens_internacionais, manifestos_internacionais, conhecimentos_internacionais" +
          		               " where conhecimentos_internacionais.oid_conhecimento = viagens_internacionais.oid_conhecimento " +
          		               " and viagens_internacionais.oid_manifesto_internacional = manifestos_internacionais.oid_manifesto_internacional " +
          		               " and manifestos_internacionais.oid_manifesto_internacional = embarques.oid_manifesto " +
          		               " and conhecimentos_internacionais.oid_conhecimento = '" + res.getString("oid_conhecimento") + "'";
          if (ed.getDM_Tipo_Embarque().equals("N")){
              sqlEmbarque = "SELECT embarques.dt_saida as dt_embarque " +
	               		    " from embarques, viagens, manifestos, conhecimentos " +
	               		    " where conhecimentos.oid_conhecimento = viagens.oid_conhecimento " +
	               		    " and viagens.oid_manifesto = manifestos.oid_manifesto " +
	               		    " and manifestos.oid_manifesto = embarques.oid_manifesto " +
	               		    " and conhecimentos_internacionais.oid_conhecimento = '" + res.getString("oid_conhecimento") + "'";
          }

          rs = null;
          rs = this.executasql.executarConsulta(sqlEmbarque);
          while (rs.next()){
              edVolta.setDT_Entrega(rs.getString("DT_Embarque"));
              DataFormatada.setDT_FormataData(edVolta.getDT_Entrega());
              edVolta.setDT_Entrega(DataFormatada.getDT_FormataData());
          }

          String data1 = edVolta.getDT_Coleta();
          String data2 = edVolta.getDT_Entrega();
          if(!JavaUtil.doValida(data1))
              data1 = edVolta.getDT_Emissao();
          if(!JavaUtil.doValida(data2))
              data2 = Data.getDataDMY();

          edVolta.setNR_Dias(Data.diferencaDias(data1, data2));

// System.out.println("20");
		  String RD = "";
		  if ( (ed.getDM_Tipo_Embarque().equals("I") || ed.getDM_Tipo_Embarque().equals("N"))
		          && (JavaUtil.doValida(ed.getOID_Pessoa()) || (JavaUtil.doValida(ed.getOID_Pessoa_Destinatario()))) )  {
		      RD = PessoaBean.getByOID(res.getString("oid_Pessoa")).getNM_Razao_Social();
		      RD += " - ";
		      RD += PessoaBean.getByOID(res.getString("oid_Pessoa_Destinatario")).getNM_Razao_Social();
		  }
		  edVolta.setREM_DEST(RD);

          list.add(edVolta);
        }

        new EmbarqueRL().relCRT_Armazem(list, ed, response);

      }
      catch(Exception exc){
          exc.printStackTrace();
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao listar Embarque");
        excecoes.setMetodo("lista");
        excecoes.setExc(exc);
        throw excecoes;
      }
    }
  
  
  public void geraControleViagem(EmbarqueED ed, HttpServletResponse response)throws Excecoes{

  	String sql = null;
  	ArrayList list1 = new ArrayList();
   	String sqlBusca = "";

  	EmbarqueED edVolta = new EmbarqueED();

  	try{

  		sql =  "SELECT "+
			  		" Embarques.OID_Embarque,  "+
			  		" Embarques.nr_Embarque,  "+
			  		" Embarques.CD_Roteiro, "+
			  		" Embarques.OID_Cidade,  "+
			  		" Embarques.OID_Manifesto,  "+
			  		" Embarques.OID_Cidade_Destino,  "+
			  		" Embarques.OID_Veiculo,  "+
			  		" Embarques.OID_Veiculo_Carreta,  "+
			  		" Embarques.NR_Placa,  "+
			  		" Embarques.DM_Expresso,  "+
			  		" Embarques.NM_Motorista, "+
			  		" Embarques.NR_Celular,  "+
			  		" Embarques.DT_Saida, "+
			  		" Embarques.HR_Saida, "+
			  		" Embarques.nr_odometro_inicial, "+
			  		" embarques.nr_carro, "+
			  		" Estado_origem.cd_estado as cd_estado_origem, "+
			  		" Estado_destino.cd_estado as cd_estado_destino, "+
			  		" Cidade_Origem.NM_Cidade as NM_Cidade_Origem, "+
			  		" Cidade_Destino.NM_Cidade as NM_Cidade_Destino  "+
			  		" from  "+
			  		" Embarques,  "+
			  		" Cidades Cidade_Origem,  "+
			  		" Cidades Cidade_Destino, "+
			  		" regioes_estados regioes_estados_d,"+
			  		" regioes_estados regioes_estados_o,"+
		    		" Estados Estado_Origem,  "+
		    		" Estados Estado_Destino ";
  		sql += " WHERE Embarques.OID_Cidade_Destino = Cidade_Destino.OID_Cidade ";
  		sql += " AND Cidade_destino.oid_regiao_estado = regioes_estados_d.oid_regiao_estado";
  		sql += " AND estado_destino.oid_estado = regioes_estados_D.oid_estado";
  		sql += " AND Embarques.OID_Cidade = Cidade_Origem.OID_Cidade ";
  		sql += " AND Cidade_origem.oid_regiao_estado = regioes_estados_o.oid_regiao_estado";
  		sql += " AND estado_origem.oid_estado = regioes_estados_o.oid_estado";

  		if (String.valueOf(ed.getOID_Embarque()) != null &&
  				!String.valueOf(ed.getOID_Embarque()).equals("0") &&
				!String.valueOf(ed.getOID_Embarque()).equals("null")){
  			sql += " and OID_Embarque = " + ed.getOID_Embarque();
  		}
  		
  		if (String.valueOf(ed.getOID_Manifesto()) != null &&
  				!String.valueOf(ed.getOID_Manifesto()).equals("0") &&
				!String.valueOf(ed.getOID_Manifesto()).equals("null")){
  			sql += " and OID_Manifesto = '" + ed.getOID_Manifesto() + "'";
  		}

  		if (String.valueOf(ed.getNR_Embarque()) != null &&
  				!String.valueOf(ed.getNR_Embarque()).equals("0") &&
				!String.valueOf(ed.getNR_Embarque()).equals("null")){
  			sql += " and NR_Embarque = " + ed.getNR_Embarque();
  		}
// System.out.println(sql);
  		ResultSet res = null;
  		res = this.executasql.executarConsulta(sql);

  		FormataDataBean DataFormatada = new FormataDataBean();

  		while (res.next()){
  			edVolta.setOID_Embarque(res.getLong("OID_Embarque"));
  			edVolta.setOID_Veiculo(res.getString("OID_Veiculo"));
  			edVolta.setOID_Veiculo_Carreta(res.getString("OID_Veiculo_Carreta"));
  			edVolta.setNR_Embarque(res.getLong("NR_Embarque"));
  			edVolta.setNR_Placa(res.getString("NR_Placa"));
  			edVolta.setNR_Carro(res.getString("NR_Carro"));
  			edVolta.setNR_Placa_Carreta(res.getString("OID_Veiculo_Carreta"));
  			edVolta.setCD_Roteiro(res.getString("CD_Roteiro"));
  			edVolta.setNM_Cidade_Origem(res.getString("NM_Cidade_Origem"));
  			edVolta.setCD_Estado_Origem(res.getString("cd_estado_Origem"));
  			edVolta.setNM_Cidade_Destino(res.getString("NM_Cidade_Destino"));
  			        //+" / "+res.getString("cd_estado_Destino"));

  			edVolta.setDT_Saida(res.getString("DT_Saida"));
  			DataFormatada.setDT_FormataData(edVolta.getDT_Saida());
  			edVolta.setDT_Saida(DataFormatada.getDT_FormataData());
  			edVolta.setHR_Saida(res.getString("HR_Saida"));

	   			edVolta.setDM_Expresso("Não");
	   			if (res.getString("DM_Expresso").equals("S")) edVolta.setDM_Expresso("Sim");

  			edVolta.setNM_Motorista(res.getString("NM_Motorista"));
  			edVolta.setNR_Celular(res.getString("NR_Celular"));
  			sql = "select nm_razao_social, nr_cnpj_cpf from pessoas, " +
  				  " veiculos where veiculos.oid_pessoa_proprietario = pessoas.oid_pessoa " +
  				  " and veiculos.oid_veiculo = '" + edVolta.getOID_Veiculo() +"'";
  			ResultSet rs = null;
  			rs = this.executasql.executarConsulta(sql);
  			while(rs.next()){
  				edVolta.setNM_Proprietario(rs.getString("nm_razao_social"));
  				edVolta.setNR_CNPJ_CPF(rs.getString("nr_cnpj_cpf"));
  			}
  			edVolta.setNR_Odometro_Inicial(res.getLong("nr_odometro_inicial")); //Teo 16/09


//// System.out.println(res.getString("oid_manifesto"));
  			edVolta.setOID_Manifesto(res.getString("oid_manifesto"));
  	        if (JavaUtil.doValida(res.getString("oid_manifesto"))){
  	            rs = null;
  	            sql =  " SELECT nr_manifesto_internacional, oid_cidade_alfandega from manifestos_internacionais WHERE ";
  	            sql += " oid_manifesto_internacional = '" + res.getString("oid_manifesto") + "'";

  	            rs = this.executasql.executarConsulta(sql);
  	            while (rs.next()){
  	              edVolta.setTX_Ultima_Observacao(rs.getString("nr_manifesto_internacional"));
  	              edVolta.setNM_Cidade_Cruze(CidadeBean.getByOID(rs.getInt("oid_cidade_alfandega")).getNM_Cidade());
  	              
	  	            ResultSet rsCRT = null;
	  	            sql =  "SELECT "+
		            	   " Conhecimentos_Internacionais.nr_conhecimento, Conhecimentos_Internacionais.cd_pais, Conhecimentos_Internacionais.nr_permisso ";
		            sql += " from  "+
		            	   " Conhecimentos_Internacionais ";
		            sql += " WHERE Manifestos_Internacionais.oid_Manifesto_Internacional = Viagens_Internacionais.oid_Manifesto_Internacional" +
		            	   " and Viagens_Internacionais.oid_Conhecimento = Conhecimentos_Internacionais.oid_Conhecimento ";
	  	            sql += " and Manifestos_Internacionais.oid_Manifesto_Internacional = '" + res.getString("oid_manifesto") + "'";
	
	  	            rsCRT = this.executasql.executarConsulta(sql);
	  	            while (rsCRT.next()){
	  	              String nr_Conhecimento = rsCRT.getString("NR_CONHECIMENTO");
	  		          String nr_CRT_Parcial = rsCRT.getString("CD_Pais") + "." +
	  									      rsCRT.getString("NR_Permisso") + ".";
	  		          int completa_Nr_CRT = 13 - nr_CRT_Parcial.length() - nr_Conhecimento.length();
	  		          for(int a = 0 ; a < completa_Nr_CRT ; a++){
	  		              nr_CRT_Parcial = nr_CRT_Parcial + "0";
	  		          }
	  		          //edVolta.setNR_Conhecimento(nr_CRT_Parcial+nr_Conhecimento);
	  		          edVolta.setTX_Ultima_Observacao(edVolta.getTX_Ultima_Observacao()+"/"+nr_CRT_Parcial+nr_Conhecimento);
	  	            }
  	              
  	            }

  	            if (!JavaUtil.doValida(edVolta.getNR_Manifesto())){
  	                sql =  " SELECT nr_manifesto from manifestos WHERE ";
  	                sql += " oid_manifesto = '" + res.getString("oid_manifesto") + "'";

  	                rs = this.executasql.executarConsulta(sql);
  	                while (rs.next()){
  	                  edVolta.setTX_Ultima_Observacao(rs.getString("nr_manifesto"));
  	                }
  	            }
  	         }
//// System.out.println(edVolta.getTX_Ultima_Observacao());

//  			rs = null;
//  			sqlBusca = "select nm_liberacao_seguradora from manifestos where" +
//  					   " oid_manifesto = '"+res.getString("oid_manifesto")+"'";
//  			rs = this.executasql.executarConsulta(sqlBusca);
//  			while(rs.next()) edVolta.setNR_Liberacao(rs.getString("nm_liberacao_seguradora"));

  			ResultSet resDT = null;
  			
  			double vl_truck = 0;
  			double vl_carreta = 0;
  			double vl_outros = 0;
  			
// System.out.println("rot: "+res.getString("CD_Roteiro"));

  			if (res.getString("CD_Roteiro") != null &&
  					!String.valueOf(res.getString("CD_Roteiro")).equals("") &&
					!String.valueOf(res.getString("CD_Roteiro")).equals("null")){

  				sql = "Select  "+
			  				"Roteiros.CD_Roteiro, " +
			  				"Roteiros.NM_Roteiro, "+
			  				"Roteiros.CD_Roteiro, " +
			  				"Roteiros.vl_km_truck, " +
			  				"Roteiros.vl_km_carreta, " +
			  				"Roteiros.vl_km_outros, " +
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

  				if (String.valueOf(edVolta.getCD_Roteiro()) != null &&
  						!String.valueOf(edVolta.getCD_Roteiro()).equals("") &&
						!String.valueOf(edVolta.getCD_Roteiro()).equals("null")){
  					sql += " and Roteiros.CD_Roteiro = '" + edVolta.getCD_Roteiro() + "'";
  				}
  				sql += " Order by Roteiros.CD_Roteiro, Rotas_Roteiros.NR_Sequencia ";
// System.out.println("ROT sql : "+sql);

  				resDT = this.executasql.executarConsulta(sql);

  				while (resDT.next()){
  				    
  				    vl_truck = resDT.getDouble("vl_km_truck");
  				    vl_carreta = resDT.getDouble("vl_km_carreta");
  				    vl_outros = resDT.getDouble("vl_km_outros");

  					sqlBusca =  " SELECT Apoios.NR_KM, CD_Estado, Cidades.NM_Cidade , cidade_rota.NM_Cidade as orig, " +
  								" cidade_rota_destino.NM_Cidade as dest, NM_Referencia, DM_Tipo_Apoio, Apoios.OID_Apoio, " +
  								" NM_Apoio, NM_Contato, NM_Telefone, Rotas.OID_Rota, Rotas.NM_Rodovia, " +
  							    " cidade_rota.nm_cidade, cidade_rota_destino.nm_cidade, Apoios.DM_APOIO_RASTREADO " +
  							    " from Apoios, Rotas, " +
  							    " Cidades cidades, Cidades cidade_rota, Cidades cidade_rota_destino," +
  							    " Regioes_Estados, Estados ";
  					sqlBusca += " WHERE Apoios.OID_Rota = Rotas.OID_Rota ";
  					sqlBusca += " AND Apoios.OID_Cidade = Cidades.OID_Cidade " +
			    					" AND rotas.OID_Cidade = cidade_rota.OID_Cidade " +
			    					" AND rotas.OID_Cidade_destino = cidade_rota_destino.OID_Cidade " +
				  					" AND Cidades.OID_REGIAO_ESTADO = Regioes_Estados.OID_REGIAO_ESTADO "+
				  					" AND Regioes_Estados.OID_ESTADO = Estados.OID_ESTADO ";
				  					//" AND Apoios.DM_APOIO_RASTREADO = 'S' ";
  					sqlBusca += " AND Apoios.OID_Rota = '" + resDT.getString("oid_rota") + "'";
  					sqlBusca += " Order by Apoios.NR_KM ";
// System.out.println("APOIO sql : "+sqlBusca);

  					ResultSet resCTRC = null;
  					resCTRC = this.executasql.executarConsulta(sqlBusca);
  					if (resCTRC.next()){
  						ApoioED apED = new ApoioED();
  						// System.out.println("aq " +resCTRC.getString("NM_Telefone"));
  						apED.setNM_Apoio(resCTRC.getString("NM_Apoio") + " / " + resCTRC.getString("NM_Cidade"));
  						apED.setNM_Telefone(resCTRC.getString("NM_Telefone"));
  						apED.setNM_Referencia(resCTRC.getString("NM_Referencia"));
  						apED.setNM_Rodovia(resCTRC.getString("NM_Rodovia"));
  						apED.setNM_Trecho(resCTRC.getString("oid_rota"));
  						apED.setNM_Origem(resCTRC.getString("orig"));
  						apED.setNM_Destino(resCTRC.getString("dest"));
  						apED.setDM_Apoio_Rastreado(resCTRC.getString("DM_APOIO_RASTREADO"));
  						list1.add(apED);
  					} else {
  					    ApoioED apED = new ApoioED();
  						// System.out.println("else  sem tel. ");
  						//apED.setNM_Apoio(resDT.getString("nm_cidade_rota_destino"));
  						apED.setNM_Apoio("SEM APOIO");
  						apED.setNM_Telefone("");
  						apED.setNM_Referencia(resDT.getString("nr_km")+ " Km");
  						apED.setNM_Rodovia(resDT.getString("nm_rodovia"));
  						apED.setNM_Trecho(resDT.getString("oid_rota"));
  						apED.setNM_Origem(resDT.getString("nm_cidade_rota_origem"));
  						apED.setNM_Destino(resDT.getString("nm_cidade_rota_destino"));
  						apED.setDM_Apoio_Rastreado("N");
  						list1.add(apED);
  					}

	  				edVolta.setTrechos(list1);
	  				util.closeResultset(resCTRC);
  				}
  			}
  			
  			VeiculoBean v = VeiculoBean.getByOID(edVolta.getOID_Veiculo());
  			if(JavaUtil.doValida(v.getDM_Tipo_Implemento())){
	  			if(v.getDM_Tipo_Implemento().equals("5") || v.getDM_Tipo_Implemento().equals("6") || v.getDM_Tipo_Implemento().equals("7")) 
	  			    edVolta.setVL_Rota(vl_truck);
	  			if(v.getDM_Tipo_Implemento().equals("1")) 
	  			    edVolta.setVL_Rota(vl_carreta);
	  			if(v.getDM_Tipo_Implemento().equals("9") || v.getDM_Tipo_Implemento().equals("8")) 
	  			    edVolta.setVL_Rota(vl_outros);
  			} else edVolta.setVL_Rota(0.0);

  			edVolta.setDM_Procedencia(v.getDM_Tipo_Implemento());
  			
  			sql = " SELECT complementos_veiculos.nm_cor, complementos_veiculos.dm_procedencia, "+
  			      " marcas_veiculos.nm_marca_veiculo, complementos_veiculos.nr_ano_veiculo, modelos_veiculos.nm_modelo_veiculo, complementos_veiculos.nr_chassis "+
  			      " from veiculos, complementos_veiculos, modelos_veiculos, marcas_veiculos"+
  			      " WHERE veiculos.oID_veiculo = '" + edVolta.getOID_Veiculo() + "'";
  			sql += " AND complementos_veiculos.oid_veiculo = veiculos.oid_veiculo "+
			       	   " AND veiculos.oid_modelo_veiculo = modelos_veiculos.oid_modelo_veiculo" +
			       	   " AND modelos_veiculos.oid_marca_veiculo = marcas_veiculos.oid_marca_veiculo";

  			resDT = null;
  			resDT = this.executasql.executarConsulta(sql);

  			while (resDT.next()){
  				if (resDT.getString("dm_procedencia") != null &&resDT.getString("dm_procedencia").equals("P")) edVolta.setDm_tipoF("XX");
  				if (resDT.getString("dm_procedencia") != null &&resDT.getString("dm_procedencia").equals("T")) edVolta.setDm_tipoC("XX");
  				if (resDT.getString("dm_procedencia") != null &&resDT.getString("dm_procedencia").equals("A")) edVolta.setDm_tipoA("XX");
  				edVolta.setTX_Detalhes(resDT.getString("nm_cor")+" / "+resDT.getString("nm_marca_veiculo")+" / "+resDT.getString("nr_ano_veiculo"));
  				edVolta.setTX_Detalhes1(resDT.getString("nm_modelo_veiculo")+" / "+resDT.getString("nr_chassis"));
  			}


  			sql = " SELECT ordens_fretes.vl_ordem_frete, ordens_fretes.vl_ordem_frete_convertida, ordens_fretes.vl_cotacao, ordens_fretes.vl_cotacao_padrao" +
  				  " from ordens_fretes "+
  				  " WHERE ordens_mic.oid_manifesto_internacional = '" + res.getString("oid_manifesto") + "'" +
  				  " and ordens_fretes.dm_frete = 'A' and ordens_fretes.dm_impresso != 'C' " +
  				  " AND ordens_fretes.oid_ordem_frete = ordens_mic.oid_ordem_frete";
  			double total = 0;
  			resDT = null;
  			resDT = this.executasql.executarConsulta(sql);
  			while (resDT.next()){
  			  total += resDT.getDouble("vl_ordem_frete_convertida");
  			}
  			//NACIONAIS
  			sql = " SELECT ordens_fretes.vl_ordem_frete, ordens_fretes.vl_ordem_frete_convertida, ordens_fretes.vl_cotacao, ordens_fretes.vl_cotacao_padrao" +
				  " from ordens_fretes "+
				  " WHERE ordens_manifestos.oid_manifesto = '" + res.getString("oid_manifesto") + "'" +
				  " and ordens_fretes.dm_frete = 'A' and ordens_fretes.dm_impresso != 'C' " +
				  " AND ordens_fretes.oid_ordem_frete = ordens_manifestos.oid_ordem_frete";
			resDT = null;
			resDT = this.executasql.executarConsulta(sql);
			while (resDT.next()){
			  total += resDT.getDouble("vl_ordem_frete_convertida");
			}
			
			edVolta.setVL_Adto_Real(total);
  			
  			util.closeResultset(rs);
  			util.closeResultset(resDT);
  		}
  		util.closeResultset(res);

  		EmbarqueRL Embarque_rl = new EmbarqueRL();
  		Embarque_rl.geraControleViagem(edVolta, response);
  	}
  	catch (Excecoes e){
  		throw e;
  	}
  	catch(Exception exc){
  		Excecoes exce = new Excecoes();
  		exc.printStackTrace();
  		exce.setExc(exc);
  		exce.setMensagem("Erro no médo listar");
  		exce.setClasse(this.getClass().getName());
  		exce.setMetodo("geraRelatorio(EmbarqueED ed)");
  	}

  }


}
