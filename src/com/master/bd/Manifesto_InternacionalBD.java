package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import com.master.ed.Manifesto_InternacionalED;
import com.master.ed.Ocorrencia_ConhecimentoED;
import com.master.rl.Manifesto_InternacionalRL;
import com.master.root.FormataDataBean;
import com.master.root.PessoaBean;
import com.master.root.VeiculoBean;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Utilitaria;
import com.master.util.Valor;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;

public class Manifesto_InternacionalBD {

  private ExecutaSQL executasql;
  Utilitaria util = new Utilitaria();

  public Manifesto_InternacionalBD(ExecutaSQL sql) {
    this.executasql = sql;
    //Logger l = new Logger(true);
  }

  public Manifesto_InternacionalED inclui(Manifesto_InternacionalED ed) throws Excecoes{

    String sql = null;
    long valOid = 0;
    String chave = null;
    boolean semPermisso = false;
    boolean semAidof = false;

    Manifesto_InternacionalED manED = new Manifesto_InternacionalED();
    try{
        
		
		sql = "Select CD_Pais FROM Paises, Cidades, Regioes_Estados, Estados, Regioes_Paises "+
	            " where "+
	            " cidades.oid_regiao_Estado = regioes_estados.oid_regiao_estado and"+
	            " regioes_estados.oid_Estado = estados.oid_Estado and"+
	            " Regioes_Estados.OID_Estado = Estados.OID_Estado and" +
	            " Estados.OID_Regiao_Pais = Regioes_Paises.OID_Regiao_Pais and" +
	            " Regioes_Paises.OID_Pais = Paises.OID_Pais and "+
	            " Cidades.oid_Cidade = '" + ed.getOID_Cidade_Origem()+ "'";

		ResultSet rs = this.executasql.executarConsulta(sql);
		String CD_Pais = null;
		while (rs.next()){
		    CD_Pais = rs.getString("CD_Pais");
		}

		if (CD_Pais != null && CD_Pais.equals("BR")){
		    ed.setDM_Exportacao_Importacao("E");
		}else{
		    ed.setDM_Exportacao_Importacao("I");
		}
		ed.setCD_Pais(CD_Pais);
		
//		sql =  " SELECT AIDOF.NR_Proximo, AIDOF.OID_AIDOF, AIDOF.NM_Serie ";
//		sql += " FROM AIDOF ";
//		if (ed.getDM_Exportacao_Importacao().equals("E"))  sql += " WHERE AIDOF.NM_Serie = 'MIE' ";
//		if (ed.getDM_Exportacao_Importacao().equals("I"))  sql += " WHERE AIDOF.NM_Serie = 'MII' ";
//		rs = null;
//		rs = this.executasql.executarConsulta(sql);
		
		
        rs = null;
		rs = this.executasql.executarConsulta(sql);
      
		String nr_Proximo = null;
		String nm_Serie = null;
      
		while (rs.next()){
		    nr_Proximo = rs.getString("nr_proximo");
		    nm_Serie = rs.getString("nm_serie");
		}
		ed.setNM_Serie(nm_Serie);
		ed.setNR_Manifesto_Internacional(new Long(nr_Proximo).longValue());
      
      chave = String.valueOf(ed.getOID_Unidade_Origem()) + String.valueOf(ed.getNR_Manifesto_Internacional()) + ed.getNM_Serie() + String.valueOf(ed.getOID_Unidade_Destino());

      ed.setOID_Manifesto_Internacional(chave);

      sql =  " insert into Manifestos_Internacionais (OID_Manifesto_Internacional, NR_Manifesto_Internacional, OID_Seguradora, " +
      		 " OID_Pessoa, OID_Veiculo, OID_Veiculo_Carreta, OID_Cidade, OID_Cidade_Destino, OID_Cidade_Alfandega, OID_Aduana, OID_Aduana_Destino, " +
      		 " OID_Unidade, OID_Unidade_Destino, OID_Unidade_Fronteira, DM_Expresso, DM_Exportacao_Importacao, DT_Previsao_Chegada, " +
      		 " HR_Previsao_Chegada, NM_Liberacao_Seguradora, NR_Odometro_Inicial, DT_Emissao, DM_LANCADO_ORDEM_FRETE, DM_Transito_Aduaneiro, " +
      		 " NM_Lacre, TX_Rota1, TX_Rota2, TX_Rota3, TX_Rota4, TX_Rota5, TX_Rota6, TX_Rota7, TX_Rota8, TX_Rota9, " +
      		 " TX_Observacao1, TX_Observacao2, TX_Observacao3, TX_Observacao4, TX_Observacao5, TX_Observacao6, TX_Observacao7, TX_Observacao8, TX_Observacao9, TX_Observacao10, TX_Observacao11, TX_Observacao12, TX_Observacao13, TX_Observacao14, TX_Observacao15, TX_Observacao16, TX_Observacao17, TX_Observacao18, "+
      		 " CD_Pais, NR_Permisso, nm_cidade_destino, nm_pais, nm_proprietario, nm_end_proprietario, nm_cid_uf_pais_proprietario, dm_tipo_operacao, CD_Roteiro ) values ";
      sql += "('" + ed.getOID_Manifesto_Internacional() + "'," + ed.getNR_Manifesto_Internacional() + "," + ed.getOID_Seguradora() + ",'" + 
             ed.getOID_Pessoa() + "','" + ed.getOID_Veiculo() +  "','" + ed.getOID_Veiculo_Carreta() + "'," + ed.getOID_Cidade() + "," + 
             ed.getOID_Cidade_Destino() + "," + ed.getOID_Cidade_Alfandega() + "," + ed.getOID_Aduana() + "," + ed.getOID_Aduana_Destino() + "," + 
             ed.getOID_Unidade_Origem() + "," + ed.getOID_Unidade_Destino() + "," + ed.getOID_Unidade_Fronteira() + ",'" + ed.getDM_Expresso()+ "','" + 
             ed.getDM_Exportacao_Importacao() + "','" + ed.getDT_Previsao_Chegada() + "','" + ed.getHR_Previsao_Chegada() + "','" + 
             ed.getNM_Liberacao_Seguradora() + "'," + ed.getNR_Odometro_Inicial() + ",'" + ed.getDT_Emissao() + "','" + ed.getDM_Lancado_Ordem_Frete() + "','" + 
             ed.getDM_Transito_Aduaneiro() + "','"  + ed.getNM_Lacre() + "','" + ed.getTX_Rota1() + "','" + ed.getTX_Rota2() + "','" + ed.getTX_Rota3() + "','" + ed.getTX_Rota4() + "','" + ed.getTX_Rota5() + "','" + ed.getTX_Rota6() + "','" + ed.getTX_Rota7() + "','" + ed.getTX_Rota8() + "','" + ed.getTX_Rota9() + "','" +
             
             ed.getTX_Observacao1() +  "','" +
             ed.getTX_Observacao2() + "','" +
             ed.getTX_Observacao3() + "','" +
             ed.getTX_Observacao4() + "','" +
             ed.getTX_Observacao5() + "','" +
             ed.getTX_Observacao6() + "','" +
     		 ed.getTX_Observacao7() + "','" +
     		 ed.getTX_Observacao8() + "','" +
     		 ed.getTX_Observacao9() + "','" +
     		 ed.getTX_Observacao10() + "','" +
     		 ed.getTX_Observacao11() + "','" +
     		 ed.getTX_Observacao12() + "','" +
     		 ed.getTX_Observacao13() + "','" +
     		 ed.getTX_Observacao14() + "','" +
     		 ed.getTX_Observacao15() + "','" +
     		 ed.getTX_Observacao16() + "','" +
     		 ed.getTX_Observacao17() + "','" +
     		 ed.getTX_Observacao18() + "','" +
             
     		 ed.getCD_Pais() + "','" + 
             ed.getNR_Permisso()+ "'";
      sql += ",'" + ed.getNM_Cidade_Destino() + "','" + ed.getNM_Pais() + "','" + ed.getNM_Pessoa_Proprietario() + "','" + ed.getNM_End_Proprietario() + "','" + ed.getNM_Cid_UF_Pais_Proprietario() + "','" + ed.getDM_Tipo_Operacao() + "'";
      sql += ed.getCD_Roteiro() == null ? ",'')" : ",'" + ed.getCD_Roteiro() + "')";

// System.out.println(sql);

      int j = executasql.executarUpdate(sql);

      // System.out.println("E M B A R Q U E !!!!!!!!!!!!!!!!!!!!!!!!!!!!");
      // System.out.println("ed.getCD_Roteiro() =" + ed.getCD_Roteiro());

      if (ed.getCD_Roteiro() != null &&
          !String.valueOf(ed.getCD_Roteiro()).equals("") &&
          !String.valueOf(ed.getCD_Roteiro()).equals(null) &&
          !String.valueOf(ed.getCD_Roteiro()).equals("null")){
          
//        Verifica se veiculo tem algum embarque sem data de chegada
//        Somente se nao for Meio proprio e III000
          if(!ed.getOID_Veiculo().equals("M.PROPRIO") && !ed.getOID_Veiculo().equals("III0000")){
	          sql = "select dt_chegada, nr_embarque from Embarques where oid_veiculo = '" + ed.getOID_Veiculo() + "'" +
	          		" order by dt_chegada ";
	          ResultSet rsVeiculo = executasql.executarConsulta(sql);
	
	          while (rsVeiculo.next()){
	              if(!JavaUtil.doValida(rsVeiculo.getString("dt_chegada"))){
	                  //throw new Excecoes("Veiculo ainda possui Embarque (nr. "+rsVeiculo.getString("nr_embarque")+ ") sem data de Chegada.", getClass().getName(), "inclui(EmbarqueED ed)");
	              }
	          }
          }

          sql =  " SELECT NM_RAZAO_SOCIAL from Pessoas ";
          sql += " where oid_Pessoa = '" + ed.getOID_Pessoa() + "'";

          ResultSet res = null;
          res = this.executasql.executarConsulta(sql);

// System.out.println(sql);

          String NM_Motorista = null;

          while (res.next()){
            NM_Motorista = res.getString("NM_RAZAO_SOCIAL");
          }

          sql =  " SELECT NR_CELULAR from Motoristas ";
          sql += " where oid_Pessoa = '" + ed.getOID_Pessoa() + "'";

          res = null;
          res = this.executasql.executarConsulta(sql);
          // System.out.println(sql);

          String NR_Celular = null;

          while (res.next()){
            NR_Celular = res.getString("NR_CELULAR");
          }

          rs = executasql.executarConsulta("select max(oid_Embarque) as result from Embarques");

          while (rs.next()) valOid = rs.getInt("result");
          valOid = valOid+1;

          ed.setNR_Embarque(valOid);
          ed.setOID_Embarque(valOid);

          sql =  " insert into Embarques (OID_Embarque, NR_Embarque, NR_Placa, NR_Celular, " +
          		 " OID_Cidade, OID_Cidade_Destino, DM_Expresso, NM_Motorista, DM_Situacao, DT_Emissao, OID_Veiculo, OID_Veiculo_Carreta, " +
          		 " CD_Roteiro, OID_Manifesto, DM_Carga_Veiculo, DT_Previsao_Chegada, HR_Previsao_Chegada, oid_cidade_Apoio ) values ";
          sql += "(" + ed.getOID_Embarque() + "," + ed.getNR_Embarque() + ",'" + ed.getOID_Veiculo() + "','" + NR_Celular + "'," + ed.getOID_Cidade_Origem() + "," + ed.getOID_Cidade_Destino() + ",'" + "N" + "','" + NM_Motorista + "','" + "9" + "','" + ed.getDT_Emissao()  + "','" + ed.getOID_Veiculo() + "','" + ed.getOID_Veiculo_Carreta() + "','" + ed.getCD_Roteiro() + "','" + ed.getOID_Manifesto_Internacional() + "','C'" + 
                 ",'" + ed.getDT_Previsao_Chegada() + "','" + ed.getHR_Previsao_Chegada() + "'," + ed.getOID_Cidade_Origem() + ")";

          // System.out.println(sql);

          int i = executasql.executarUpdate(sql);

//          sql = " update Veiculos set OID_Embarque = " + ed.getOID_Embarque() + " where oid_veiculo = '" + ed.getOID_Veiculo() + "'";
//          i = executasql.executarUpdate(sql);

// Gelson Novo
          VeiculoBean v = VeiculoBean.getByOID(ed.getOID_Veiculo());
          if (v.getDM_Procedencia() == null || v.getDM_Procedencia().equals("null")){
              v.setDM_Procedencia("");
          }

   		  if (!v.getDM_Procedencia().equals("O")){
	          sql = " update Veiculos_Embarques set DM_Situacao = 'E'" +
	           " where oid_veiculo = '" + ed.getOID_Veiculo() + "'" + " and DM_Situacao = 'A'";
	          // System.out.println(sql);
	          i = executasql.executarUpdate(sql);
   		  }

          sql =  " insert into Veiculos_Embarques (OID_Embarque, OID_Veiculo, DM_Situacao) values ";
          sql += "(" + ed.getOID_Embarque() + ",'" + ed.getOID_Veiculo() + "','A'"+ ")";
          // System.out.println(sql);

          i = executasql.executarUpdate(sql);
          
//        Tracking das carretas
  		if(JavaUtil.doValida(ed.getOID_Veiculo_Carreta())){
  			v = VeiculoBean.getByOID(ed.getOID_Veiculo_Carreta());
  			if (!ed.getOID_Veiculo_Carreta().equals("M.PROPRIO") && !ed.getOID_Veiculo_Carreta().equals("III0000")){
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

          sql =  " SELECT NM_Cidade, CD_Estado, Cidades.OID_Cidade "+
              " from Rotas, Roteiros, Rotas_Roteiros, Apoios, "+
              " cidades, "+
              " estados, "+
              " regioes_estados "+
              " WHERE "+
              " Apoios.oid_cidade = Cidades.oid_cidade and"+
              " Cidades.oid_regiao_Estado = regioes_estados.oid_regiao_estado and"+
              " regioes_estados.oid_Estado = Estados.oid_Estado and "+
              " Rotas.OID_Rota = Apoios.OID_ROTA and "+
              " Rotas.OID_Rota = Rotas_Roteiros.OID_Rota and "+
              " Roteiros.CD_Roteiro = Rotas_Roteiros.CD_Roteiro and "+
              " Apoios.DM_APOIO_RASTREADO = 'S' and "+
              " Roteiros.CD_Roteiro = '" + ed.getCD_Roteiro() + "'";
          res = null;
          // System.out.println(sql);
          res = this.executasql.executarConsulta(sql);

          while (res.next()){
            rs = executasql.executarConsulta("select max(oid_Ocorrencia_Embarque) as result from Ocorrencias_Embarques");

            while (rs.next()) valOid = rs.getInt("result");

            long OID_Ocorrencia_Embarque = ++valOid;

            Data data = new Data();
            String TX_Descricao = res.getString("NM_Cidade") + "-" + res.getString("CD_Estado");

            sql = " insert into Ocorrencias_Embarques (OID_Ocorrencia_Embarque, OID_Embarque, OID_Tipo_Ocorrencia, DT_Ocorrencia_Lancada, HR_Ocorrencia_Lancada, TX_Descricao, OID_Cidade ) values ";
            sql += "(" + OID_Ocorrencia_Embarque + "," + ed.getOID_Embarque() + "," + 14 + ",'"  + data.getDataDMY() + "','" + data.getHoraHM() + "','" + TX_Descricao + "'," + res.getLong("OID_Cidade") + ")";
            // System.out.println(sql);

            i = executasql.executarUpdate(sql);
          }
      }
      
      manED.setOID_Manifesto_Internacional(ed.getOID_Manifesto_Internacional());

    }
    catch(Excecoes e){
        throw e;
      }
    catch(Exception exc){
        exc.printStackTrace();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir Manifesto_Internacional");
      if (semPermisso){
          excecoes.setMensagem("N�o existe n�mero de permisso para as unidades digitadas! Retorne e altere estes dados.");
        }
      if (semAidof){
          excecoes.setMensagem("N�o existe n�mero de Aidof 'MIC' para as unidades digitadas! Retorne e altere estes dados.");
        }
      excecoes.setMetodo("inclui");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return manED;

  }

  public void altera(Manifesto_InternacionalED ed) throws Excecoes{

    String sql = null;

    try{
      sql =  " SELECT DM_Lancado_Ordem_Frete from Manifestos_Internacionais ";
      sql += " where oid_Manifesto_Internacional = '" + ed.getOID_Manifesto_Internacional() + "'";
      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      while (res.next()){
        ed.setDM_Lancado_Ordem_Frete(res.getString("DM_Lancado_Ordem_Frete"));
      }

      if (ed.getDM_Lancado_Ordem_Frete() != null && ed.getDM_Lancado_Ordem_Frete().equals("S")){
        Excecoes exc = new Excecoes();
        throw exc;
      }

      sql = " update Manifestos_Internacionais set oid_pessoa='" + ed.getOID_Pessoa() + "', oid_pessoa_permisso='" + ed.getOID_Pessoa_Permisso() + "', oid_pessoa_permisso2='" + ed.getOID_Pessoa_Permisso2() + "', oid_aduana=" + ed.getOID_Aduana() + ", oid_Aduana_Destino=" + ed.getOID_Aduana_Destino() + ", Oid_Veiculo= '" + ed.getOID_Veiculo() + "', Oid_Veiculo_Carreta= '" + ed.getOID_Veiculo_Carreta() + "',  DM_Expresso= '" + ed.getDM_Expresso() + "', DT_Previsao_Chegada = '" + ed.getDT_Previsao_Chegada()  + "', DM_Transito_Aduaneiro = '" + ed.getDM_Transito_Aduaneiro() + "', HR_Previsao_Chegada = '" + ed.getHR_Previsao_Chegada() + "', NM_Liberacao_Seguradora = '" + ed.getNM_Liberacao_Seguradora() + "', NR_Odometro_Inicial = " + ed.getNR_Odometro_Inicial() + ", NR_Odometro_Troca_Motorista = " + ed.getNR_Odometro_Troca_Motorista() + ", oid_cidade_destino = " + ed.getOID_Cidade_Destino();

      sql += " , nm_cidade_destino = '" + ed.getNM_Cidade_Destino() + "', nm_pais = '" + ed.getNM_Pais() + "', DT_Emissao = '" + ed.getDT_Emissao() + "', nm_proprietario = '" + ed.getNM_Pessoa_Proprietario() + "' " +
      		 ", nm_end_proprietario = '" + ed.getNM_End_Proprietario() + "', nm_cid_uf_pais_proprietario = '" + ed.getNM_Cid_UF_Pais_Proprietario() + "' ";
      
      if (ed.getCD_Roteiro() != null)
        sql += " ,CD_Roteiro = '" + ed.getCD_Roteiro() + "' ";
      else
        sql += " ,CD_Roteiro = null ";

      sql += " ,TX_Observacao1 = '" + ed.getTX_Observacao1() + "',"+
      		 " TX_Observacao2 = '" + ed.getTX_Observacao2() + "',"+
      		 " TX_Observacao3 = '" + ed.getTX_Observacao3() + "',"+
		     " TX_Observacao4 = '" + ed.getTX_Observacao4() + "',"+
		     " TX_Observacao5 = '" + ed.getTX_Observacao5() + "',"+
		     " TX_Observacao6 = '" + ed.getTX_Observacao6() + "',"+
		     " TX_Observacao7 = '" + ed.getTX_Observacao7() + "',"+
		     " TX_Observacao8 = '" + ed.getTX_Observacao8() + "',"+
		     " TX_Observacao9 = '" + ed.getTX_Observacao9() + "',"+
		     " TX_Observacao10 = '" + ed.getTX_Observacao10() + "',"+
		     " TX_Observacao11 = '" + ed.getTX_Observacao11() + "',"+
		     " TX_Observacao12 = '" + ed.getTX_Observacao12() + "',"+
		     " TX_Observacao13 = '" + ed.getTX_Observacao13() + "',"+
		     " TX_Observacao14 = '" + ed.getTX_Observacao14() + "',"+
		     " TX_Observacao15 = '" + ed.getTX_Observacao15() + "',"+
		     " TX_Observacao16 = '" + ed.getTX_Observacao16() + "',"+
		     " TX_Observacao17 = '" + ed.getTX_Observacao17() + "',"+
		     " TX_Observacao18 = '" + ed.getTX_Observacao18() + "'";

      if (ed.getTX_Rota1() != null)
        sql += " ,TX_Rota1 = '" + ed.getTX_Rota1() + "' ";
      else
        sql += " ,TX_Rota1 = null ";

      if (ed.getTX_Rota2() != null)
        sql += " ,TX_Rota2 = '" + ed.getTX_Rota2() + "' ";
      else
        sql += " ,TX_Rota2 = null ";

      if (ed.getTX_Rota3() != null)
        sql += " ,TX_Rota3 = '" + ed.getTX_Rota3() + "' ";
      else
        sql += " ,TX_Rota3 = null ";
      
      if (ed.getTX_Rota4() != null)
          sql += " ,TX_Rota4 = '" + ed.getTX_Rota4() + "' ";
        else
          sql += " ,TX_Rota4 = null ";
      if (ed.getTX_Rota5() != null)
          sql += " ,TX_Rota5 = '" + ed.getTX_Rota5() + "' ";
        else
          sql += " ,TX_Rota5 = null ";
      if (ed.getTX_Rota6() != null)
          sql += " ,TX_Rota6 = '" + ed.getTX_Rota6() + "' ";
        else
          sql += " ,TX_Rota6 = null ";
      if (ed.getTX_Rota7() != null)
          sql += " ,TX_Rota7 = '" + ed.getTX_Rota7() + "' ";
        else
          sql += " ,TX_Rota7 = null ";
      if (ed.getTX_Rota8() != null)
          sql += " ,TX_Rota8 = '" + ed.getTX_Rota8() + "' ";
        else
          sql += " ,TX_Rota8 = null ";
      if (ed.getTX_Rota9() != null)
          sql += " ,TX_Rota9 = '" + ed.getTX_Rota9() + "' ";
        else
          sql += " ,TX_Rota9 = null ";

      if (ed.getNM_Lacre() != null)
        sql += " ,NM_Lacre = '" + ed.getNM_Lacre() + "' ";
      else
        sql += " ,NM_Lacre = null ";

      sql += " where oid_Manifesto_Internacional = '" + ed.getOID_Manifesto_Internacional() + "'";

      int i = executasql.executarUpdate(sql);


      // System.out.println("E M B A R Q U E !!!!!!!!!!!!!!!!!!!!!!!!!!!!");
      // System.out.println("ed.getCD_Roteiro() =" + ed.getCD_Roteiro());

      if (ed.getCD_Roteiro() != null &&
          !String.valueOf(ed.getCD_Roteiro()).equals("") &&
          !String.valueOf(ed.getCD_Roteiro()).equals(null) &&
          !String.valueOf(ed.getCD_Roteiro()).equals("null")){
          
          sql =  " SELECT NM_RAZAO_SOCIAL from Pessoas ";
          sql += " where oid_Pessoa = '" + ed.getOID_Pessoa() + "'";

          ResultSet rs = null;
          rs = this.executasql.executarConsulta(sql);

// System.out.println(sql);

          String NM_Motorista = null;

          while (rs.next()){
            NM_Motorista = rs.getString("NM_RAZAO_SOCIAL");
          }
          
          sql =  " update Embarques set ";
          sql += "oid_veiculo = '" + ed.getOID_Veiculo() + "', " +
          		 "nm_motorista = '" + NM_Motorista + "', " +
          		 "oid_veiculo_carreta = '" + ed.getOID_Veiculo_Carreta() + "' " +
          		 "where oid_manifesto = '" + ed.getOID_Manifesto_Internacional() + "'";

// System.out.println(sql);

          int t = executasql.executarUpdate(sql);

      }

    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      if (ed.getDM_Lancado_Ordem_Frete() != null && ed.getDM_Lancado_Ordem_Frete().equals("S")){
        excecoes.setMensagem("Manifesto_Internacional lan�ado em ordem de frete");
      }else{
        excecoes.setMensagem("Erro ao alterar Manifesto_Internacional");
      }
      excecoes.setMetodo("alterar");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void deleta(Manifesto_InternacionalED ed) throws Excecoes{

    String sql = null;

    try{

      /*sql =  " SELECT OID_Embarque from Embarques ";
      sql += " where oid_Manifesto_Internacional = '" + ed.getOID_Manifesto_Internacional() + "'";

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      String OID_Embarque = null;

      while (res.next()){
        OID_Embarque = res.getString("OID_Embarque");

        sql = "delete from Ocorrencias_Embarques WHERE OID_Embarque = ";
        sql += "(" + OID_Embarque + ")";

        int i = executasql.executarUpdate(sql);

        sql = "delete from Notas_Fiscais_Embarques WHERE OID_Embarque = ";
        sql += "(" + OID_Embarque + ")";

        i = executasql.executarUpdate(sql);
      }

      sql = "delete from Embarques WHERE OID_Embarque = ";
      sql += "(" + OID_Embarque + ")";

      int i = executasql.executarUpdate(sql);*/

    	sql =  " SELECT * from viagens_internacionais ";
    	sql += " where oid_Manifesto_Internacional = '" + ed.getOID_Manifesto_Internacional() + "'";

    	ResultSet res = null;
    	res = this.executasql.executarConsulta(sql);

    	boolean tem_viagem = false;

    	while (res.next()){
    		tem_viagem = true;
    	}

    	if (tem_viagem){
    		Excecoes e = new Excecoes();
    		e.setMensagem("Este MIC possui Viagem. Exclua as viagens antes de excluir o MIC!");
    		e.setClasse("com.master.bd.Manifesto_InternacionalBD()");
    		e.setMetodo("public void deleta(Manifesto_InternacionalED ed)");
    		throw e;
    	}

      sql = "delete from Manifestos_Internacionais WHERE oid_Manifesto_Internacional = ";
      sql += "('" + ed.getOID_Manifesto_Internacional() + "')";

      int i = executasql.executarUpdate(sql);
    }

    catch (Excecoes e){
    	throw e;
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      if (ed.getDM_Lancado_Ordem_Frete() != null && ed.getDM_Lancado_Ordem_Frete().equals("S")){
        excecoes.setMensagem("Manifesto_Internacional lan�o em ordem de frete");
      }else{
        excecoes.setMensagem("Erro ao excluir Manifesto_Internacional");
      }
      excecoes.setMetodo("excluir");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public ArrayList lista(Manifesto_InternacionalED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();

    try{

      sql =  "SELECT Manifestos_Internacionais.OID_Manifesto_Internacional, Manifestos_Internacionais.DM_Expresso, Manifestos_Internacionais.CD_Pais, Manifestos_Internacionais.NR_Permisso, " +
      		 " Manifestos_Internacionais.DT_Emissao, Manifestos_Internacionais.NR_Manifesto_Internacional, Unidades.CD_Unidade, Veiculos.NR_Placa, " +
      		 " Cidades.NM_Cidade, Cidade_Unidade.NM_Cidade as NM_Cidade_Unidade " +
      		 " from Manifestos_Internacionais, Unidades, Veiculos, Cidades, Cidades Cidade_Unidade, Pessoas ";
      sql += " WHERE Manifestos_Internacionais.OID_Unidade = Unidades.OID_Unidade ";
      sql += " AND Pessoas.OID_Pessoa = Unidades.OID_Pessoa ";
      sql += " AND Pessoas.OID_Cidade = Cidade_Unidade.OID_Cidade ";
      sql += " AND Manifestos_Internacionais.OID_Veiculo = Veiculos.OID_Veiculo ";
      sql += " AND Manifestos_Internacionais.OID_Cidade_Destino = Cidades.OID_Cidade ";

      if (String.valueOf(ed.getDT_Emissao()) != null && !String.valueOf(ed.getDT_Emissao()).equals("") && !String.valueOf(ed.getDT_Emissao()).equals("null")){
        sql += " and Manifestos_Internacionais.DT_Emissao >= '" + ed.getDT_Emissao() + "'";
      }
      if (String.valueOf(ed.getDT_Fim()) != null && !String.valueOf(ed.getDT_Fim()).equals("") && !String.valueOf(ed.getDT_Fim()).equals("null")){
          sql += " and Manifestos_Internacionais.DT_Emissao <= '" + ed.getDT_Fim() + "'";
        }

      if (String.valueOf(ed.getNR_Manifesto_Internacional()) != null &&
          !String.valueOf(ed.getNR_Manifesto_Internacional()).equals("0") &&
          !String.valueOf(ed.getNR_Manifesto_Internacional()).equals("null")){
        sql += " and Manifestos_Internacionais.NR_Manifesto_Internacional = " + ed.getNR_Manifesto_Internacional();
      }

      if (String.valueOf(ed.getOID_Unidade_Origem()) != null &&
          !String.valueOf(ed.getOID_Unidade_Origem()).equals("0") &&
          !String.valueOf(ed.getOID_Unidade_Origem()).equals("null")){
        sql += " and Manifestos_Internacionais.OID_Unidade = " + ed.getOID_Unidade_Origem();
      }
      if (String.valueOf(ed.getOID_Unidade_Destino()) != null &&
              !String.valueOf(ed.getOID_Unidade_Destino()).equals("0") &&
              !String.valueOf(ed.getOID_Unidade_Destino()).equals("null")){
            sql += " and Manifestos_Internacionais.OID_Unidade_Destino = " + ed.getOID_Unidade_Destino();
      }
      if (String.valueOf(ed.getOID_Unidade_Fronteira()) != null &&
              !String.valueOf(ed.getOID_Unidade_Fronteira()).equals("0") &&
              !String.valueOf(ed.getOID_Unidade_Fronteira()).equals("null")){
            sql += " and Manifestos_Internacionais.OID_Unidade_Fronteira = " + ed.getOID_Unidade_Fronteira();
      }
      if (String.valueOf(ed.getOID_Pessoa()) != null &&
          !String.valueOf(ed.getOID_Pessoa()).equals("") &&
          !String.valueOf(ed.getOID_Pessoa()).equals("null")){
        sql += " and Manifestos_Internacionais.OID_Pessoa = '" + ed.getOID_Pessoa() + "'";
      }
      if (String.valueOf(ed.getOID_Veiculo()) != null &&
          !String.valueOf(ed.getOID_Veiculo()).equals("") &&
          !String.valueOf(ed.getOID_Veiculo()).equals("null")){
        sql += " and Manifestos_Internacionais.oid_Veiculo = '" + ed.getOID_Veiculo() + "'";
      }
      if (String.valueOf(ed.getOID_Veiculo_Carreta()) != null &&
              !String.valueOf(ed.getOID_Veiculo_Carreta()).equals("") &&
              !String.valueOf(ed.getOID_Veiculo_Carreta()).equals("null")){
            sql += " and Manifestos_Internacionais.oid_Veiculo_Carreta = '" + ed.getOID_Veiculo_Carreta() + "'";
      }
      if (String.valueOf(ed.getNR_Conhecimento()) != null &&
              !String.valueOf(ed.getNR_Conhecimento()).equals("0") &&
              !String.valueOf(ed.getNR_Conhecimento()).equals("null")){
            sql += " and Viagens_Internacionais.OID_Manifesto_Internacional = Manifestos_Internacionais.OID_Manifesto_Internacional" +
            	   " and Conhecimentos_Internacionais.OID_Conhecimento = Viagens_Internacionais.OID_Conhecimento" +
            	   " and Conhecimentos_Internacionais.NR_Conhecimento = '" + ed.getNR_Conhecimento() + "'";
          }

      sql += " Order by Manifestos_Internacionais.oid_unidade, Manifestos_Internacionais.NR_Manifesto_Internacional ";

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      FormataDataBean DataFormatada = new FormataDataBean();

      //popula
      while (res.next()){
        Manifesto_InternacionalED edVolta = new Manifesto_InternacionalED();

        edVolta.setOID_Manifesto_Internacional(res.getString("OID_Manifesto_Internacional"));

        edVolta.setDT_Emissao(res.getString("DT_Emissao"));

        DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
        edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());

        edVolta.setCD_Unidade(res.getString("CD_Unidade"));
        edVolta.setNR_Placa(res.getString("NR_Placa"));
        edVolta.setNM_Cidade_Unidade(res.getString("NM_Cidade_Unidade"));
        edVolta.setNM_Cidade_Destino(res.getString("NM_Cidade"));
        edVolta.setDM_Expresso(res.getString("DM_Expresso"));
        edVolta.setNR_Manifesto_Internacional(res.getLong("NR_Manifesto_Internacional"));
        
        String nr_MIC = res.getString("NR_MANIFESTO_INTERNACIONAL");
		String nr_MIC_Parcial = res.getString("CD_PAIS") + "." + res.getString("NR_PERMISSO") + ".";
		int completa_Nr = 13 - nr_MIC_Parcial.length() - nr_MIC.length();
		for(int a = 0 ; a < completa_Nr ; a++){
		    nr_MIC_Parcial = nr_MIC_Parcial + "0";
		}
		edVolta.setNM_Manifesto_Internacional(nr_MIC_Parcial+nr_MIC);

        list.add(edVolta);
      }
    }
    catch(Exception exc){
        exc.printStackTrace();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao listar Manifesto_Internacional");
      excecoes.setMetodo("lista");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return list;
  }

  public ArrayList Manifesto_Internacional_Lista_Acerto(Manifesto_InternacionalED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();

    try{

      sql =  "SELECT Acertos.oid_Acerto, Acertos.NR_Acerto, Manifestos_Internacionais.OID_Manifesto_Internacional, Manifestos_Internacionais.DT_Emissao, Manifestos_Internacionais.NR_Manifesto_Internacional, Unidades.CD_Unidade, Veiculos.NR_Placa, Cidades.NM_Cidade, Cidade_Unidade.NM_Cidade as NM_Cidade_Unidade from Manifestos_Internacionais, Unidades, Veiculos, Cidades, Cidades Cidade_Unidade, Pessoas, Acertos ";
      sql += " WHERE Manifestos_Internacionais.OID_Unidade = Unidades.OID_Unidade ";
      sql += " AND Pessoas.OID_Pessoa = Unidades.OID_Pessoa ";
      sql += " AND Pessoas.OID_Cidade = Cidade_Unidade.OID_Cidade ";
      sql += " AND Manifestos_Internacionais.OID_Veiculo = Veiculos.OID_Veiculo ";
      sql += " AND Manifestos_Internacionais.OID_Cidade_Destino = Cidades.OID_Cidade ";
      sql += " AND Manifestos_Internacionais.OID_Acerto = Acertos.OID_Acerto ";


      if (String.valueOf(ed.getOID_Acerto()) != null &&
          !String.valueOf(ed.getOID_Acerto()).equals("0") &&
          !String.valueOf(ed.getOID_Acerto()).equals("null")){
        sql += " and Manifestos_Internacionais.OID_Acerto = " + ed.getOID_Acerto();
      }

      sql += " Order by Manifestos_Internacionais.NR_Manifesto_Internacional ";

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      FormataDataBean DataFormatada = new FormataDataBean();

      //popula
      while (res.next()){
        Manifesto_InternacionalED edVolta = new Manifesto_InternacionalED();

        edVolta.setOID_Manifesto_Internacional(res.getString("OID_Manifesto_Internacional"));

        edVolta.setDT_Emissao(res.getString("DT_Emissao"));

        DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
        edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());

        edVolta.setCD_Unidade(res.getString("CD_Unidade"));
        edVolta.setNR_Placa(res.getString("NR_Placa"));
        edVolta.setNM_Cidade_Unidade(res.getString("NM_Cidade_Unidade"));
        edVolta.setNM_Cidade_Destino(res.getString("NM_Cidade"));
        edVolta.setNR_Manifesto_Internacional(res.getLong("NR_Manifesto_Internacional"));
        edVolta.setOID_Acerto(res.getLong("oid_Acerto"));
        edVolta.setNR_Acerto(res.getLong("NR_Acerto"));

        list.add(edVolta);
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao listar Manifesto_Internacional");
      excecoes.setMetodo("lista");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return list;
  }


  public void geraRelatorio(Manifesto_InternacionalED ed)throws Excecoes{

    String sql = null;

    Manifesto_InternacionalED edVolta = new Manifesto_InternacionalED();

    try{

      sql = "select * from Manifestos_Internacionais where 1=1 ";

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      Manifesto_InternacionalRL Manifesto_Internacional_rl = new Manifesto_InternacionalRL();
      Manifesto_Internacional_rl.geraRelatEstoque(res);
    }
    catch (Excecoes e){
      throw e;
    }
    catch(Exception exc){
      Excecoes exce = new Excecoes();
      exce.setExc(exc);
      exce.setMensagem("Erro no m�todo listar");
      exce.setClasse(this.getClass().getName());
      exce.setMetodo("geraRelatorio(Manifesto_InternacionalED ed)");
    }

  }

  public Manifesto_InternacionalED getByRecord(Manifesto_InternacionalED ed)throws Excecoes{

    String sql = null;

    Manifesto_InternacionalED edVolta = new Manifesto_InternacionalED();

    try{

      sql =  " SELECT * from Manifestos_Internacionais, unidades, pessoas "+
             " WHERE Manifestos_Internacionais.oid_unidade = Unidades.oid_unidade  "+
             " and   Unidades.oid_Pessoa = Pessoas.oid_pessoa  ";


      if (String.valueOf(ed.getOID_Manifesto_Internacional()) != null &&
          !String.valueOf(ed.getOID_Manifesto_Internacional()).equals("") &&
          !String.valueOf(ed.getOID_Manifesto_Internacional()).equals("null")){
        sql += " and Manifestos_Internacionais.OID_Manifesto_Internacional = '" + ed.getOID_Manifesto_Internacional() + "'";
      }

      if (String.valueOf(ed.getNR_Manifesto_Internacional()) != null &&
          !String.valueOf(ed.getNR_Manifesto_Internacional()).equals("0") &&
          !String.valueOf(ed.getNR_Manifesto_Internacional()).equals("null")){
        sql += " and Manifestos_Internacionais.NR_Manifesto_Internacional = " + ed.getNR_Manifesto_Internacional();
      }

      if (String.valueOf(ed.getOID_Unidade()) != null &&
          !String.valueOf(ed.getOID_Unidade()).equals("0") &&
          !String.valueOf(ed.getOID_Unidade()).equals("null")){
        sql += " and Manifestos_Internacionais.OID_Unidade = " + ed.getOID_Unidade();
      }
// System.out.println("getByRecord MIC >>> "+sql);
// System.err.println("err Tbem >>> "+sql);

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      FormataDataBean DataFormatada = new FormataDataBean();

      while (res.next()){

        edVolta.setOID_Manifesto_Internacional(res.getString("OID_Manifesto_Internacional"));
        edVolta.setOID_Aduana(res.getLong("OID_Aduana"));
        edVolta.setOID_Cidade(res.getLong("OID_Cidade"));
        edVolta.setOID_Cidade_Destino(res.getLong("OID_Cidade_Destino"));
        edVolta.setOID_Cidade_Alfandega(res.getLong("OID_Cidade_Alfandega"));

        edVolta.setOID_Aduana(res.getLong("OID_Aduana"));
        edVolta.setOID_Aduana_Destino(res.getLong("OID_Aduana_Destino"));

        edVolta.setOID_Veiculo(res.getString("OID_Veiculo"));
        edVolta.setOID_Veiculo_Carreta(res.getString("OID_Veiculo_Carreta"));
        edVolta.setNR_Placa(res.getString("OID_Veiculo"));

        edVolta.setOID_Pessoa(res.getString("OID_Pessoa"));
        edVolta.setOID_Pessoa_Permisso(res.getString("OID_Pessoa_Permisso"));
        edVolta.setOID_Pessoa_Permisso2(res.getString("OID_Pessoa_Permisso2"));
        edVolta.setOID_Pessoa_Reveza(res.getString("OID_Pessoa_Reveza"));
        edVolta.setOID_Seguradora(res.getLong("OID_Seguradora"));
        edVolta.setDM_Expresso(res.getString("DM_Expresso"));
        edVolta.setDM_Transito_Aduaneiro(res.getString("DM_Transito_Aduaneiro"));
        edVolta.setDM_Lancado_Ordem_Frete(res.getString("DM_Lancado_Ordem_Frete"));

        edVolta.setDM_Tipo_Operacao(res.getString("DM_Tipo_Operacao"));

        edVolta.setDT_Previsao_Chegada(res.getString("DT_Previsao_Chegada"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Previsao_Chegada());
        edVolta.setDT_Previsao_Chegada(DataFormatada.getDT_FormataData());

        edVolta.setDT_Emissao(res.getString("DT_Emissao"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
        edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());

        edVolta.setDT_Troca_Motorista(res.getString("DT_Troca_Motorista"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Troca_Motorista());
        edVolta.setDT_Troca_Motorista(DataFormatada.getDT_FormataData());

        edVolta.setHR_Previsao_Chegada(res.getString("HR_Previsao_Chegada"));
        edVolta.setNM_Liberacao_Seguradora(res.getString("NM_Liberacao_Seguradora"));
        edVolta.setNR_Manifesto_Internacional(res.getLong("NR_Manifesto_Internacional"));
        edVolta.setNR_Odometro_Inicial(res.getLong("NR_Odometro_Inicial"));
        edVolta.setNR_Odometro_Troca_Motorista(res.getLong("NR_Odometro_Troca_Motorista"));
        
        edVolta.setOID_Unidade_Origem(res.getInt("OID_Unidade"));
        edVolta.setOID_Unidade_Destino(res.getInt("OID_Unidade_Destino"));
        edVolta.setOID_Unidade_Fronteira(res.getInt("OID_Unidade_Fronteira"));
        
//        edVolta.setOID_Unidade(res.getLong("OID_Unidade"));
//        edVolta.setCD_Unidade(res.getString("CD_Unidade"));
//        edVolta.setNM_Cidade_Unidade(res.getString("NM_Fantasia"));

        edVolta.setTX_Observacao1(res.getString("TX_Observacao1"));
        edVolta.setTX_Observacao2(res.getString("TX_Observacao2"));
        edVolta.setTX_Observacao3(res.getString("TX_Observacao3"));
        edVolta.setTX_Observacao4(res.getString("TX_Observacao4"));
        edVolta.setTX_Observacao5(res.getString("TX_Observacao5"));
        edVolta.setTX_Observacao6(res.getString("TX_Observacao6"));
		edVolta.setTX_Observacao7(res.getString("TX_Observacao7"));
		edVolta.setTX_Observacao8(res.getString("TX_Observacao8"));
		edVolta.setTX_Observacao9(res.getString("TX_Observacao9"));
		edVolta.setTX_Observacao10(res.getString("TX_Observacao10"));
		edVolta.setTX_Observacao11(res.getString("TX_Observacao11"));
		edVolta.setTX_Observacao12(res.getString("TX_Observacao12"));
		edVolta.setTX_Observacao13(res.getString("TX_Observacao13"));
		edVolta.setTX_Observacao14(res.getString("TX_Observacao14"));
		edVolta.setTX_Observacao15(res.getString("TX_Observacao15"));
		edVolta.setTX_Observacao16(res.getString("TX_Observacao16"));
		edVolta.setTX_Observacao17(res.getString("TX_Observacao17"));
		edVolta.setTX_Observacao18(res.getString("TX_Observacao18"));

        edVolta.setTX_Rota1(res.getString("TX_Rota1"));
        edVolta.setTX_Rota2(res.getString("TX_Rota2"));
        edVolta.setTX_Rota3(res.getString("TX_Rota3"));
        edVolta.setTX_Rota4(res.getString("TX_Rota4"));
        edVolta.setTX_Rota5(res.getString("TX_Rota5"));
        edVolta.setTX_Rota6(res.getString("TX_Rota6"));
        edVolta.setTX_Rota7(res.getString("TX_Rota7"));
        edVolta.setTX_Rota8(res.getString("TX_Rota8"));
        edVolta.setTX_Rota9(res.getString("TX_Rota9"));
        
        edVolta.setNM_Lacre(res.getString("NM_Lacre"));
        
        edVolta.setHR_Chegada(res.getString("HR_Chegada"));
        edVolta.setDT_Chegada(res.getString("DT_Chegada"));
        edVolta.setDM_Situacao(res.getString("DM_Situacao"));
        
        edVolta.setNM_Cidade_Destino(res.getString("nm_cidade_destino"));
        edVolta.setNM_Pais(res.getString("nm_pais"));
        
        edVolta.setNM_Pessoa_Proprietario(res.getString("nm_proprietario"));
        edVolta.setNM_End_Proprietario(res.getString("nm_end_proprietario"));
        edVolta.setNM_Cid_UF_Pais_Proprietario(res.getString("nm_cid_uf_pais_proprietario"));

        edVolta.setCD_Pais(res.getString("CD_Pais"));

        edVolta.setNR_Permisso(res.getString("NR_Permisso"));
// System.out.println(res.getString("CD_Roteiro")+" roteiro");
        if (res.getString("CD_Roteiro") != null &&
          !String.valueOf(res.getString("CD_Roteiro")).equals("") &&
          !String.valueOf(res.getString("CD_Roteiro")).equals("null")){

          sql =  " SELECT * from Roteiros WHERE ";
          sql += " CD_Roteiro = '" + res.getString("CD_Roteiro") + "'";
// System.out.println(sql);
          ResultSet resTP = null;
          resTP = this.executasql.executarConsulta(sql);

          while (resTP.next()){
            edVolta.setCD_Roteiro(resTP.getString("CD_Roteiro"));
            edVolta.setNM_Roteiro(resTP.getString("NM_ROTEIRO"));

          }
         }
        }

        sql= " SELECT (Conhecimentos.vl_gasto_remetente1 + Conhecimentos.vl_gasto_remetente2 + Conhecimentos.vl_gasto_remetente3 + Conhecimentos.vl_gasto_remetente4 + " +
      		 " Conhecimentos.vl_gasto_destinatario1 + Conhecimentos.vl_gasto_destinatario2 + Conhecimentos.vl_gasto_destinatario3 + Conhecimentos.vl_gasto_destinatario4) as VL_Total_Frete, " +
        	 " Conhecimentos.vl_Peso, Conhecimentos.dt_emissao "+
        " FROM Manifestos_Internacionais, Viagens_Internacionais, Conhecimentos_Internacionais conhecimentos" +
        " WHERE Manifestos_Internacionais.OID_Manifesto_Internacional = Viagens_Internacionais.OID_Manifesto_Internacional "+
        " AND Conhecimentos.OID_Conhecimento = Viagens_Internacionais.OID_Conhecimento "+
        " AND Manifestos_Internacionais.oid_Manifesto_Internacional = '" + ed.getOID_Manifesto_Internacional() + "'";
//// System.out.println("ORDEM > MIC : "+sql);
        ResultSet resCTRC = null;
        resCTRC = this.executasql.executarConsulta(sql);
        double VL_Total_Frete_CTRC=0;
        double NR_Total_Peso_CTRC=0;

        while (resCTRC.next()){
            //VL_Total_Frete_CTRC += resCTRC.getDouble("VL_Total_Frete");
            VL_Total_Frete_CTRC += convertToReal(resCTRC.getString("DT_Emissao"), resCTRC.getDouble("VL_Total_Frete") , Parametro_FixoED.getInstancia().getOID_Moeda_Dollar());
            NR_Total_Peso_CTRC += resCTRC.getDouble("vl_Peso");
        }
        edVolta.setVL_Total_Frete_CTRC(VL_Total_Frete_CTRC);
        edVolta.setNR_Total_Peso_CTRC(NR_Total_Peso_CTRC);

    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao selecionar Manifesto_Internacional");
      excecoes.setMetodo("Sele��o");
      excecoes.setExc(exc);
      throw excecoes;
    }
 
    return edVolta;
  } 
  
  public void inclui_Ocorrencia(Manifesto_InternacionalED ed) throws Excecoes{

      String sql = null;
      Parametro_FixoED  edParametro_Fixo = new Parametro_FixoED();

      try{

      	sql =  " SELECT oid_Pessoa, NR_Manifesto_internacional from Manifestos_Internacionais ";
        sql += " where oid_Manifesto_Internacional = '" + ed.getOID_Manifesto_Internacional() + "'";

        ResultSet res = null;
        ResultSet res2 = null;
        res = this.executasql.executarConsulta(sql);

        while (res.next()){
          ed.setOID_Pessoa(res.getString("OID_Pessoa"));
          ed.setNR_Manifesto_Internacional(res.getLong("NR_Manifesto_Internacional"));
        }

		if (ed.getDM_Tipo() != null &&
            !ed.getDM_Tipo().equals("") &&
            !ed.getDM_Tipo().equals("null") &&
            (ed.getDM_Tipo().equals("W") || ed.getDM_Tipo().equals("Y"))){
                //// System.out.println(" Confirmando chegada manifesto " );
//            ed.setDT_Chegada(Data.getDataDMY());
//            ed.setHR_Chegada(Data.getHoraHM());

            sql = " update Manifestos_Internacionais set DT_Chegada= '" + ed.getDT_Chegada() + "', HR_Chegada= '" + ed.getHR_Chegada() + "',  DM_Situacao='S' ";
            sql += " where oid_Manifesto_Internacional = '" + ed.getOID_Manifesto_Internacional() + "'";
            //// System.out.println("bd sql " + sql);
            ed.setNM_Pessoa_Entrega("Unidade Destino");
            int i = executasql.executarUpdate(sql);
        }
		if (ed.getOID_Tipo_Ocorrencia() == 155 ){
	          sql = " update Manifestos_Internacionais set DT_Cruze = '" + ed.getDT_Chegada() + "'," +
	                " HR_Cruze = '" + ed.getHR_Chegada()  + "'";
	         sql += " where oid_Manifesto_Internacional = '" + ed.getOID_Manifesto_Internacional() + "'";
	         int i = executasql.executarUpdate(sql);
	      }

        sql =  " SELECT DM_Aplicacao, oid_tipo_Ocorrencia, DM_Tipo From Tipos_Ocorrencias ";
        sql += " WHERE Tipos_Ocorrencias.OID_Tipo_Ocorrencia = " + ed.getOID_Tipo_Ocorrencia();
        res2 = this.executasql.executarConsulta(sql);
        String DM_Aplicacao="";
        String DM_Tipo="";
        int oid_tipo_Ocorrencia=0;
        while (res2.next()){
              DM_Tipo=res2.getString("DM_Tipo");
              DM_Aplicacao=res2.getString("DM_Aplicacao");
              oid_tipo_Ocorrencia=res2.getInt("oid_tipo_Ocorrencia");
        }

        if (edParametro_Fixo.getDM_Gera_Ocorrencia_Viagem().equals("S")) {

            sql =  " SELECT OID_Conhecimento from Viagens_Internacionais ";
            sql += " WHERE Viagens_Internacionais.OID_Manifesto_Internacional = '" + ed.getOID_Manifesto_Internacional() + "'";

            res2 = this.executasql.executarConsulta(sql);

            FormataDataBean DataFormatada = new FormataDataBean();

            //popula
            while (res2.next()){
              //// System.out.println("bd sql ctc ent " + sql);

                Ocorrencia_Conhecimento_InternacionalBD Ocorrencia_Conhecimento_IntBD = new Ocorrencia_Conhecimento_InternacionalBD(this.executasql);
                Ocorrencia_ConhecimentoED ocorrencia_ConhecimentoED = new Ocorrencia_ConhecimentoED();

                ocorrencia_ConhecimentoED.setDT_Ocorrencia_Conhecimento(ed.getDT_Chegada());
                ocorrencia_ConhecimentoED.setHR_Ocorrencia_Conhecimento(ed.getHR_Chegada());
                ocorrencia_ConhecimentoED.setOID_Tipo_Ocorrencia(ed.getOID_Tipo_Ocorrencia());
                ocorrencia_ConhecimentoED.setOID_Conhecimento(res2.getString("oid_Conhecimento"));
                ocorrencia_ConhecimentoED.setTX_Observacao(ed.getTX_Observacao1());
                ocorrencia_ConhecimentoED.setNM_Pessoa_Entrega(ed.getNM_Pessoa_Entrega());
                ocorrencia_ConhecimentoED.setDM_Tipo(ed.getDM_Tipo());
                Ocorrencia_Conhecimento_IntBD.inclui(ocorrencia_ConhecimentoED);
             }
        }
      }

      catch(Exception exc){
          exc.printStackTrace();
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao alterar Manifesto_Internacional");
        excecoes.setMetodo("inclui_Ocorrencia()");
        excecoes.setExc(exc);
        throw excecoes;
      }
    }
  
  public void geraRelMICEmitido(Manifesto_InternacionalED ed, HttpServletResponse response)throws Excecoes{

      String sql, mic = null;
      ArrayList lista = new ArrayList();
      long valOid = 0;
      ResultSet res = null;
      
      try{
          

	      sql = " select Viagens_Internacionais.VL_Total_Frete, "+
	      		" Viagens_Internacionais.VL_Nota_Fiscal, "+
	      		" Viagens_Internacionais.NR_Volumes as M3, "+
	      		" Viagens_Internacionais.VL_Peso, "+
	      		" Viagens_Internacionais.VL_Peso_Cubado, "+
	      		" Conhecimentos_Internacionais.OID_Conhecimento, "+
	      		" Conhecimentos_Internacionais.oid_Pessoa, "+
	      		" Conhecimentos_Internacionais.oid_Pessoa_Destinatario, "+
	      		" Conhecimentos_Internacionais.NR_Conhecimento, Manifestos_Internacionais.DT_Emissao, Manifestos_Internacionais.oid_Manifesto_Internacional, "+
	      		" Manifestos_Internacionais.NR_Manifesto_Internacional, Manifestos_Internacionais.oid_Pessoa as oid_Motorista, "+
	      		" Manifestos_Internacionais.CD_Pais, Manifestos_Internacionais.NR_Permisso, Veiculos.NR_Placa" +
	      		" from " +
	      		" Viagens_Internacionais, Conhecimentos_Internacionais, Manifestos_Internacionais, Veiculos ";
	      sql += " WHERE Viagens_Internacionais.OID_Conhecimento = Conhecimentos_Internacionais.OID_Conhecimento ";
	      sql += " AND Viagens_Internacionais.OID_Manifesto_Internacional = Manifestos_Internacionais.OID_Manifesto_Internacional ";
	      sql += " AND Manifestos_Internacionais.OID_Veiculo = Veiculos.OID_Veiculo ";
	
	      if (String.valueOf(ed.getOID_Unidade_Origem()) != null && !String.valueOf(ed.getOID_Unidade_Origem()).equals("0")){
	            sql += " and Manifestos_Internacionais.OID_Unidade = " + ed.getOID_Unidade_Origem();
	      }
	      if (String.valueOf(ed.getOID_Unidade_Destino()) != null && !String.valueOf(ed.getOID_Unidade_Destino()).equals("0")){
	            sql += " and Manifestos_Internacionais.OID_Unidade_Destino = " + ed.getOID_Unidade_Destino();
	      }
	      if (String.valueOf(ed.getOID_Pessoa()) != null && !String.valueOf(ed.getOID_Pessoa()).equals("")){
	            sql += " and Conhecimentos_Internacionais.OID_Pessoa = '" + ed.getOID_Pessoa() + "'";
	      }
	      if (String.valueOf(ed.getOID_Pessoa_Destinatario()) != null && !String.valueOf(ed.getOID_Pessoa_Destinatario()).equals("")){
	            sql += " and Conhecimentos_Internacionais.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario() + "'";
	      }
	      if (String.valueOf(ed.getDT_Emissao_Inicial()) != null &&
	          !String.valueOf(ed.getDT_Emissao_Inicial()).equals("") &&
			  !String.valueOf(ed.getDT_Emissao_Inicial()).equals("null")){
	    		sql += " and Manifestos_Internacionais.DT_Emissao >= '" + ed.getDT_Emissao_Inicial() + "'";
	      }
	      if (String.valueOf(ed.getDT_Emissao_Final()) != null &&
	    			!String.valueOf(ed.getDT_Emissao_Final()).equals("") &&
					!String.valueOf(ed.getDT_Emissao_Final()).equals("null")){
	    		sql += " and Manifestos_Internacionais.DT_Emissao <= '" + ed.getDT_Emissao_Final() + "'";
	      }
	      if (JavaUtil.doValida(String.valueOf(ed.getOID_Veiculo()))){
	    	    sql += " and Manifestos_Internacionais.OID_Veiculo = " + ed.getOID_Veiculo() + "";
	      }
	    	
	      sql += "order by Manifestos_Internacionais.NR_Manifesto_Internacional, Manifestos_Internacionais.dt_emissao ";
// System.out.println(sql.toString());
	      res = this.executasql.executarConsulta(sql.toString());
	      
	      FormataDataBean DataFormatada = new FormataDataBean();
	      mic = "";
	      
	      while (res.next()){
	            Manifesto_InternacionalED edVolta = new Manifesto_InternacionalED();
	            
	            edVolta.setNR_Manifesto_Internacional(res.getLong("NR_Manifesto_Internacional"));
	            String nr_MIC = res.getString("NR_MANIFESTO_INTERNACIONAL");
	    		String nr_MIC_Parcial = res.getString("CD_PAIS") + "." + res.getString("NR_PERMISSO") + ".";
	    		int completa_Nr = 13 - nr_MIC_Parcial.length() - nr_MIC.length();
	    		for(int a = 0 ; a < completa_Nr ; a++){
	    		    nr_MIC_Parcial = nr_MIC_Parcial + "0";
	    		}
	    		edVolta.setNM_Manifesto_Internacional(nr_MIC_Parcial+nr_MIC);
	            
	            edVolta.setDT_Emissao(res.getString("DT_Emissao"));
	            DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
	            edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());
	            
	            edVolta.setNR_Placa(res.getString("NR_Placa"));
	            PessoaBean pM = PessoaBean.getByOID(res.getString("oid_Motorista"));
	            edVolta.setNM_Pessoa_Entrega(pM.getNM_Razao_Social());
	            
	            edVolta.setNR_Conhecimento(res.getLong("NR_Conhecimento"));
	            edVolta.setOID_Conhecimento(res.getString("OID_Conhecimento"));
	            String nr_Conhecimento = res.getString("NR_CONHECIMENTO");
	  			String nr_CRT_Parcial = res.getString("CD_Pais") + "." + 
	  								res.getString("NR_Permisso") + ".";
	  			int completa_Nr_CRT = 13 - nr_CRT_Parcial.length() - nr_Conhecimento.length();
	  			for(int a = 0 ; a < completa_Nr_CRT ; a++){
	  				nr_CRT_Parcial = nr_CRT_Parcial + "0";
	  			}
	  		    edVolta.setNM_Conhecimento(nr_CRT_Parcial+nr_Conhecimento);
	            
	            PessoaBean pE = PessoaBean.getByOID(res.getString("oid_pessoa"));
	            edVolta.setNM_Pessoa_Remetente(pE.getNM_Razao_Social());
	            PessoaBean pI = PessoaBean.getByOID(res.getString("oid_pessoa_destinatario"));
	            edVolta.setNM_Pessoa_Destinatario(pI.getNM_Razao_Social());
	            
	            edVolta.setVL_Peso(res.getDouble("VL_Peso"));
	            edVolta.setNR_Volumes(res.getDouble("M3"));
	            
	            edVolta.setVL_Total_Frete(res.getDouble("VL_Total_Frete"));
	            
	            edVolta.setVL_Nota_Fiscal(res.getDouble("VL_Nota_Fiscal"));
	            	            	  		    
	  		    // System.out.println("add...");
	  		    if(!mic.equals(res.getString("NR_Manifesto_Internacional"))) edVolta.setOID_Manifesto_Internacional(res.getString("oid_Manifesto_Internacional"));
	  		    mic = res.getString("NR_Manifesto_Internacional");
	  		    lista.add(edVolta);
	        }
	      util.closeResultset(res);
          Manifesto_InternacionalRL Manifesto_InternacionalRL = new Manifesto_InternacionalRL();
          Manifesto_InternacionalRL.geraRelMICEmitido(ed, lista, response);
        
        
      } catch(Exception exc) {
      	util.closeResultset(res);
        exc.printStackTrace();
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao listar");
        excecoes.setMetodo("listar");
        excecoes.setExc(exc);
        throw excecoes;
  	  }
    }
  
private double convertToReal(String data, double vl, int moeda) throws Excecoes {
      
      double vl_real = 0;
      return Valor.round(vl_real, 2);
  }

}
