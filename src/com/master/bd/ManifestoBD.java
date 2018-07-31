package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.master.ed.ConhecimentoED;
import com.master.ed.Conhecimento_Nota_FiscalED;
import com.master.ed.ManifestoED;
import com.master.ed.Movimento_ConhecimentoED;
import com.master.ed.Nota_FiscalED;
import com.master.ed.Ocorrencia_ConhecimentoED;
import com.master.ed.Rota_EntregaED;
import com.master.ed.ViagemED;
import com.master.rl.JasperRL;
import com.master.rl.ManifestoRL;
import com.master.rn.Movimento_ConhecimentoRN;
import com.master.rn.ViagemRN;
import com.master.root.FormataDataBean;
import com.master.root.PessoaBean;
import com.master.root.UnidadeBean;
import com.master.root.VeiculoBean;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;
import com.master.util.Utilitaria;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;

public class ManifestoBD {

  private ExecutaSQL executasql;
  Utilitaria util = new Utilitaria(executasql);
  Parametro_FixoED parametro_FixoED = new Parametro_FixoED ();
  FormataDataBean DataFormatada = new FormataDataBean ();

  Parametro_FixoED  edParametro_Fixo = new Parametro_FixoED();

  public ManifestoBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public ManifestoED inclui(ManifestoED ed)
  throws Excecoes{

    String sql = null;
    long valOid = 0;
    String chave = null;


    Parametro_FixoED parametroFixoED = new Parametro_FixoED();

    ManifestoED manED = new ManifestoED();
    try{

          // System.out.println("man bd 1 " );

      sql =  " SELECT AIDOF.NR_Proximo, AIDOF.OID_AIDOF, AIDOF.NM_Serie ";
      sql += " FROM Parametros_Filiais, AIDOF ";

      if ("R".equals(ed.getDM_Manifesto_Romaneio())){
        sql +=" WHERE Parametros_Filiais.OID_AIDOF_Romaneio = AIDOF.OID_AIDOF ";
      } else {
        sql +=" WHERE Parametros_Filiais.OID_AIDOF_Manifesto = AIDOF.OID_AIDOF ";
      }
      sql += " AND Parametros_Filiais.OID_Unidade = " + ed.getOID_Unidade();

          // System.out.println("man bd ->> " + sql );

      ResultSet rs = null;
      rs = this.executasql.executarConsulta(sql);
      if (rs.next()){
        ed.setNM_Serie(rs.getString("NM_Serie"));
        ed.setNR_Manifesto(rs.getLong("NR_Proximo"));
        valOid = rs.getLong("OID_AIDOF");
      } else throw new Mensagens("AIDOF n�o encontrada!");


      sql =  " UPDATE AIDOF SET NR_Proximo= " + (ed.getNR_Manifesto() + 1);
      sql += " WHERE OID_AIDOF = " + valOid ;

      int u = executasql.executarUpdate(sql);

      if (ed.getOID_Seguradora()==0) {
          sql =  " SELECT * from seguros_motoristas ";
          sql += " where oid_pessoa = '" + ed.getOID_Pessoa() + "'";
          sql += " and dt_validade >= '" + ed.getDT_Emissao() + "'";

          ResultSet res = null;
          res = this.executasql.executarConsulta(sql);

          String NM_Motorista = null;

          while (res.next()){
            ed.setOID_Seguradora(res.getLong("OID_SEGURADORA"));
            ed.setNM_Liberacao_Seguradora(res.getString("NR_LIBERACAO"));
          }
      }


      chave = String.valueOf(ed.getOID_Unidade()) + String.valueOf(ed.getNR_Manifesto()) + ed.getNM_Serie() + ed.getDM_Manifesto_Romaneio();
      ed.setOID_Conhecimento(chave);

      ed.setOID_Manifesto(chave);

      sql =  " insert into Manifestos (OID_Manifesto, NR_Manifesto, OID_Seguradora,  OID_Pessoa, OID_Pessoa_Entregadora, OID_Veiculo, OID_Veiculo_Carreta, OID_Cidade_Origem, OID_Cidade, NR_KM_Viagem, OID_Unidade, DM_Expresso, DM_Tipo_Manifesto, DT_Previsao_Chegada, HR_Previsao_Chegada, NM_Liberacao_Seguradora, NR_Odometro_Inicial, DT_Emissao, DM_LANCADO_ORDEM_FRETE , TX_Observacao, NM_Ajudante1, NM_Ajudante2, NM_Ajudante3, HR_Saida, VL_Frete_Carreteiro, PE_Custo_Entrega, NR_Ajudante, NR_KIT, oid_unidade_destino, cd_Rota_Entrega, CD_Roteiro, oid_usuario, oid_Gaiola, oid_tipo_servico_aereo, oid_cia_aerea) values ";
      sql += "('" + ed.getOID_Manifesto() + "'," + ed.getNR_Manifesto() + "," + ed.getOID_Seguradora() + ",'" + ed.getOID_Pessoa() + "','" + ed.getOID_Pessoa_Entregadora()+ "'";

      sql += ed.getOID_Veiculo() == null ? ",null" : ",'" + ed.getOID_Veiculo() +"'";
      sql += ed.getOID_Veiculo_Carreta() == null ? ",null" : ",'" + ed.getOID_Veiculo_Carreta() +"'";
      sql += "," + ed.getOID_Cidade_Origem() + "," + ed.getOID_Cidade()+ "," + ed.getNR_KM_Viagem() + "," + ed.getOID_Unidade() + ",'" + ed.getDM_Expresso()+ "','" + ed.getDM_Manifesto_Romaneio() + "','" + ed.getDT_Previsao_Chegada() + "','" + ed.getHR_Previsao_Chegada() + "','" + ed.getNM_Liberacao_Seguradora() + "'," + ed.getNR_Odometro_Inicial() + ",'" + ed.getDT_Emissao() + "','" + ed.getDM_Lancado_Ordem_Frete() + "','" + ed.getTX_Observacao() + "','" + ed.getNM_Ajudante1() + "','" + ed.getNM_Ajudante2() + "','" + ed.getNM_Ajudante3() + "','" + ed.getHR_Saida() + "', " + ed.getVL_Frete_Carreteiro() + ", " + ed.getPE_Custo_Entrega()+ "," + ed.getNR_Ajudante() + "," + ed.getNR_KIT();
      sql += ed.getOID_Unidade_Destino() == 0 ? ",null" : "," + ed.getOID_Unidade_Destino() ;
      sql += ", '" + ed.getCD_Rota_Entrega() + "'";
      sql += ed.getCD_Roteiro() == null ? ",'null'" : ",'" + ed.getCD_Roteiro() + "'";
      sql += ", " + ed.getUser()+ "," + ed.getOID_Gaiola();
      sql += ", " + ed.getOID_Tipo_Servico_Aereo()+ "," + ed.getOID_Cia_Aerea() + ")";

          // System.out.println("man bd inclui ->>>>>>>>>>>>>>>.. " + sql);


      int i = executasql.executarUpdate(sql);

          // System.out.println("man bd inclui ->> OK ");


      if (ed.getOID_Ordem_Frete() != null &&
          !String.valueOf(ed.getOID_Ordem_Frete()).equals("") &&
          !String.valueOf(ed.getOID_Ordem_Frete()).equals(null) &&
          !String.valueOf(ed.getOID_Ordem_Frete()).equals("null")){

          ed.setDT_Ordem_Manifesto(Data.getDataDMY());
          ed.setHR_Ordem_Manifesto(Data.getHoraHM());

          chave = (ed.getOID_Manifesto() + ed.getOID_Ordem_Frete());

          sql = " insert into Ordens_Manifestos (OID_Ordem_Manifesto, OID_Manifesto, OID_Ordem_Frete, DT_Ordem_Manifesto, HR_Ordem_Manifesto ) values ";
          sql += "('" + chave + "','" + ed.getOID_Manifesto() + "','" + ed.getOID_Ordem_Frete() + "','"  + ed.getDT_Ordem_Manifesto() + "','" + ed.getHR_Ordem_Manifesto() + "')";

          // System.out.println("OF" + sql);

          i = executasql.executarUpdate(sql);

          sql = " update Manifestos set DM_Lancado_Ordem_Frete= 'S'" +
                " where oid_Manifesto = '" + ed.getOID_Manifesto() + "'";

          i = executasql.executarUpdate(sql);

          // System.out.println("UPDATE OK " + sql);

      }

      //Atualizar odometro do ve�culo
      if ("ROFLAN".equals(parametroFixoED.getNM_Empresa())&&ed.getNR_Odometro_Inicial()>0 ) {
      	new VeiculoBean().atualizaKMAtual(ed.getOID_Veiculo(), ed.getNR_Odometro_Inicial(), executasql);
      }

      if ("S".equals(parametroFixoED.getDM_Gera_Embarque_Viagem())){
        this.inclui_Embarque(ed);
      }

      manED.setOID_Manifesto(ed.getOID_Manifesto());


    }
    catch(Excecoes e){
        throw e;
      }
    catch(SQLException e){
      throw new Excecoes(e.getMessage(), e, getClass().getName(), "inclui(ManifestoED ed)");
    }
    catch(Exception e){
        throw new Excecoes(e.getMessage(), e, getClass().getName(), "inclui(ManifestoED ed)");
     }
    return manED;
  }

  public ManifestoED geraManifesto(ManifestoED ed)
  throws Excecoes{

    String sql = null;

    ManifestoED manED = new ManifestoED();
    try{
    	manED = this.getByRecord(ed);

        manED.setDM_Tipo_Manifesto("M");
        manED.setDM_Lancado_Ordem_Frete("N");
        manED.setDM_Manifesto_Romaneio("M");
        manED.setDT_Emissao(Data.getDataDMY());

        manED = this.inclui(manED);

        sql= " SELECT distinct(Conhecimentos.OID_Conhecimento) " +
	         " FROM Viagens, Conhecimentos, Conhecimentos_Notas_Fiscais, Notas_Fiscais " +
             " WHERE Notas_Fiscais.OID_Nota_Fiscal = Viagens.OID_Nota_Fiscal " +
             " AND   Notas_Fiscais.OID_Nota_Fiscal = Conhecimentos_Notas_Fiscais.OID_Nota_Fiscal " +
             " AND   Conhecimentos.OID_Conhecimento = Conhecimentos_Notas_Fiscais.OID_Conhecimento " +
	         " AND   Conhecimentos.VL_Total_Frete > 0 " +
	         " AND   Conhecimentos.DM_Impresso ='S' " +
	         " AND   Conhecimentos.DM_Situacao <>'C' " +
	         " AND   Viagens.oid_manifesto = '" + ed.getOID_Manifesto() + "'" +
	         " Order by Conhecimentos.OID_Conhecimento ";

	      ResultSet resCTRC = this.executasql.executarConsulta(sql);
	      while (resCTRC.next()){
	         ViagemED viagemED = new ViagemED();

	         viagemED.setOID_Conhecimento (resCTRC.getString("OID_Conhecimento"));
	         viagemED.setOID_Nota_Fiscal (null);
	         viagemED.setOID_Processo (null);
	         viagemED.setOID_Manifesto (manED.getOID_Manifesto());
	         viagemED.setDT_Viagem (Data.getDataDMY());
	         viagemED.setHR_Viagem (Data.getHoraHM());
	         viagemED.setDT_Previsao_Chegada (Data.getDataDMY());
	         viagemED.setHR_Previsao_Chegada (Data.getHoraHM());
	         viagemED.setDM_Tipo_Viagem ("T");

	         ViagemBD viagemBD = new ViagemBD (this.executasql);
	         viagemBD.inclui(viagemED);

	      }

    }
    catch(Excecoes e){
        throw e;
      }
    catch(Exception e){
        throw new Excecoes(e.getMessage(), e, getClass().getName(), "geraManifesto(ManifestoED ed)");
     }
    return manED;
  }


  public ManifestoED inclui_Embarque(ManifestoED ed)
  throws Excecoes{

    String sql = null;
    long valOid = 0;
    String chave = null;
    ResultSet rs = null;

    try{


      // System.out.println("E M B A R Q U E !!!!!!!!!!!!!!!!!!!!!!!!!!!!");

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
	                  throw new Excecoes("Veiculo ainda possui Embarque (nr. "+rsVeiculo.getString("nr_embarque")+ ") sem data de Chegada.", getClass().getName(), "inclui(EmbarqueED ed)");
	              }
	          }
          }

          sql =  " SELECT NM_RAZAO_SOCIAL from Pessoas ";
          sql += " where oid_Pessoa = '" + ed.getOID_Pessoa() + "'";

          ResultSet res = null;
          res = this.executasql.executarConsulta(sql);

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

          sql =  " SELECT cidades.oid_Cidade "+
          " from "+
          " cidades, "+
          " pessoas, "+
          " unidades "+
          " WHERE "+
          " Pessoas.oid_cidade = Cidades.oid_cidade and"+
          " Pessoas.oid_Pessoa = unidades.oid_Pessoa and"+
          " unidades.oid_unidade = " + ed.getOID_Unidade();

          ResultSet resUnidade = null;
          resUnidade = this.executasql.executarConsulta (sql);
          String oid_Cidade_Origem = null;
          while (resUnidade.next ()) {
            oid_Cidade_Origem = resUnidade.getString ("oid_Cidade");
          }


          sql =  " insert into Embarques (OID_Embarque, NR_Embarque, NR_Placa, NR_Celular, OID_Cidade, OID_Cidade_Destino, OID_Cidade_Apoio, DM_Expresso, NM_Motorista, DM_Situacao, DT_Emissao, OID_Veiculo, OID_Veiculo_Carreta, CD_Roteiro, OID_Manifesto, DM_Carga_Veiculo ) values ";
          sql += "(" + ed.getOID_Embarque() + "," + ed.getNR_Embarque() + ",'" + ed.getOID_Veiculo() + "','" + NR_Celular + "'," + oid_Cidade_Origem + "," + ed.getOID_Cidade() + "," + oid_Cidade_Origem + ",'" + "N" + "','" + NM_Motorista + "','" + "9" + "','" + ed.getDT_Emissao()  + "','" + ed.getOID_Veiculo() + "','" + ed.getOID_Veiculo_Carreta() + "','" + ed.getCD_Roteiro() + "','" + ed.getOID_Manifesto() + "','C'" + ")";

          // System.out.println(sql);

          executasql.executarUpdate(sql);

          VeiculoBean v = VeiculoBean.getByOID(ed.getOID_Veiculo());
          if (v.getDM_Procedencia() == null || v.getDM_Procedencia().equals("null")){
              v.setDM_Procedencia("");
          }
          if (!v.getDM_Procedencia().equals("O")){
	          sql = " update Veiculos_Embarques set DM_Situacao = 'E'" +
	           " where oid_veiculo = '" + ed.getOID_Veiculo() + "'" + " and DM_Situacao = 'A'";
	          // System.out.println(sql);
	          executasql.executarUpdate(sql);
          }
          sql =  " insert into Veiculos_Embarques (OID_Embarque, OID_Veiculo, DM_Situacao) values ";
          sql += "(" + ed.getOID_Embarque() + ",'" + ed.getOID_Veiculo() + "','A'"+ ")";

          executasql.executarUpdate(sql);

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
  		            executasql.executarUpdate(sql);
  		        }
  	 		   	sql = " update Veiculos_Embarques set DM_Situacao = 'E'" + " where oid_veiculo = '" + ed.getOID_Veiculo_Carreta() + "'" + " and DM_Situacao = 'A'";
  	 		   	executasql.executarUpdate(sql);
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

            executasql.executarUpdate(sql);
          }
      }

    }
    catch(Excecoes e){
        throw e;
      }
    catch(SQLException e){
      throw new Excecoes(e.getMessage(), e, getClass().getName(), "inclui_Embarque(ManifestoED ed)");
    }
    catch(Exception e){
        throw new Excecoes(e.getMessage(), e, getClass().getName(), "inclui_Embarque(ManifestoED ed)");
     }
    return ed;
  }


  public void altera(ManifestoED ed) throws Excecoes{

    String sql = null;

    try{

      if (String.valueOf(ed.getOID_Manifesto()) != null &&
          !String.valueOf(ed.getOID_Manifesto()).equals("") &&
          !String.valueOf(ed.getOID_Manifesto()).equals("null")){

	      ed.setDM_Lancado_Ordem_Frete("N");
	      sql = " SELECT Ordens_Fretes.oid_Ordem_Frete " +
	          " FROM   Ordens_Fretes, Ordens_Manifestos " +
	          " WHERE  Ordens_Fretes.oid_Ordem_Frete = Ordens_Manifestos.oid_Ordem_Frete " +
	          " AND    Ordens_Manifestos.oid_Manifesto = '" + ed.getOID_Manifesto () + "'";
	      // System.out.println (sql);
	      ResultSet resTP = this.executasql.executarConsulta (sql);
	      if (resTP.next ()) {
	        ed.setDM_Lancado_Ordem_Frete("S");
	        Excecoes exc = new Excecoes ();
	        throw exc;
	      }

	      sql =
	      	" update Manifestos " +
	      	" set  Oid_Veiculo= '" + ed.getOID_Veiculo() + "', " +
	      	"      Oid_Veiculo_Carreta= '" + ed.getOID_Veiculo_Carreta() + "', " +
	      	"      oid_Pessoa = '" + ed.getOID_Pessoa() + "', " +
	      	"      DM_Expresso= '" + ed.getDM_Expresso() + "', " +
	      	"      NM_Liberacao_Seguradora = '" + ed.getNM_Liberacao_Seguradora() + "', " +
	      	"      NR_Odometro_Inicial = " + ed.getNR_Odometro_Inicial()  + ", " +
	      	"      NR_Odometro_Final = " + ed.getNR_Odometro_Final()  + ", " +
	      	"      NR_Odometro_Troca_Motorista = " + ed.getNR_Odometro_Troca_Motorista() + ", " +
	      	"      TX_Observacao = '" + ed.getTX_Observacao() + "', " +
	      	"      NM_Ajudante1 = '" + ed.getNM_Ajudante1() + "', " +
	      	"      NM_Ajudante2 = '" + ed.getNM_Ajudante2() + "', " +
	      	"      NM_Ajudante3 = '" + ed.getNM_Ajudante3() + "', " +
	      	"      VL_Frete_Carreteiro = " + ed.getVL_Frete_Carreteiro() + ", " +
	      	"      NR_Ajudante = " + ed.getNR_Ajudante() + ", " +
	      	"      NR_KIT = " + ed.getNR_KIT() + ", " +
	      	"      NR_KM_Viagem = " + ed.getNR_KM_Viagem() + ", " +
	      	"      PE_Custo_Entrega = " + ed.getPE_Custo_Entrega();

	        if (ed.getDT_Previsao_Chegada() !=null && !ed.getDT_Previsao_Chegada().equals("") && !ed.getDT_Previsao_Chegada().equals("null")){
	          sql += ", DT_Previsao_Chegada = '" + ed.getDT_Previsao_Chegada() + "'";
	        }

	        if (ed.getHR_Previsao_Chegada() !=null && !ed.getHR_Previsao_Chegada().equals("") && !ed.getHR_Previsao_Chegada().equals("null")){
	          sql += ", HR_Previsao_Chegada = '" + ed.getHR_Previsao_Chegada() + "'";
	        }

	        if (ed.getHR_Saida() !=null && !ed.getHR_Saida().equals("") && !ed.getHR_Saida().equals("null")){
	          sql += ", HR_Saida = '" + ed.getHR_Saida() + "'";
	        }
	        if (ed.getHR_Chegada() !=null && !ed.getHR_Chegada().equals("") && !ed.getHR_Chegada().equals("null")){
	          sql += ", HR_Chegada = '" + ed.getHR_Chegada() + "'";
	        }
	        if (ed.getOID_Cidade()>0){
	          sql += ", oid_Cidade = " + ed.getOID_Cidade();
	        }
	        if (ed.getOID_Gaiola()>0){
	            sql += ", oid_Gaiola = " + ed.getOID_Gaiola();
	          }
	        if (ed.getOID_Cidade_Origem()>0){
	          sql += ", oid_Cidade_Origem = " + ed.getOID_Cidade_Origem();
	        }

	        if (ed.getOID_Tipo_Servico_Aereo()>0){
		          sql += ", oid_tipo_servico_aereo = " + ed.getOID_Tipo_Servico_Aereo();
		        }

	        if (ed.getOID_Cia_Aerea()>0){
		          sql += ", oid_cia_aerea = " + ed.getOID_Cia_Aerea();
		        }

	        // System.out.println("man bd 1");

	      sql += ed.getOID_Unidade_Destino() == 0 ? ",oid_unidade_destino = null " : ", oid_unidade_destino = " + ed.getOID_Unidade_Destino() ;

	        // System.out.println("man bd 2");

	      if (ed.getDT_Troca_Motorista() != null && !ed.getDT_Troca_Motorista().equals("") && !ed.getDT_Troca_Motorista().equals("null"))
	        sql += " ,DT_Troca_Motorista = '" + ed.getDT_Troca_Motorista() + "' ";
	      else
	        sql += " ,DT_Troca_Motorista = null ";

	        // System.out.println("man bd 3");

	      if (ed.getOID_Pessoa_Reveza() != null && !ed.getOID_Pessoa_Reveza().equals("") && !ed.getOID_Pessoa_Reveza().equals("null"))
	        sql += " ,oid_Pessoa_Reveza = '" + ed.getOID_Pessoa_Reveza() + "' ";
	      else
	        sql += " ,oid_Pessoa_Reveza = null ";


	      if (ed.getCD_Roteiro() != null && !ed.getCD_Roteiro().equals("") && !ed.getCD_Roteiro().equals("null"))
	        sql += " ,CD_Roteiro = '" + ed.getCD_Roteiro() + "' ";
	      else
	        sql += " ,CD_Roteiro = null ";


	      sql += " where oid_Manifesto = '" + ed.getOID_Manifesto() + "'";


	      int i = executasql.executarUpdate(sql);
      }else{
          throw new Excecoes("Manifesto N�o Informado para altera��o !!!");
      }

    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      if (ed.getDM_Lancado_Ordem_Frete() != null && ed.getDM_Lancado_Ordem_Frete().equals("S")){
        excecoes.setMensagem("Manifesto lan�ado em Ordem de Frete");
      }else{
        excecoes.setMensagem("Erro ao alterar Manifesto");
      }
      excecoes.setMetodo("alterar");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }


  public void finaliza_manifesto(ManifestoED ed) throws Excecoes{

	    String sql = null;

	    try{

	      if (String.valueOf(ed.getOID_Manifesto()) != null &&
	          !String.valueOf(ed.getOID_Manifesto()).equals("") &&
	          !String.valueOf(ed.getOID_Manifesto()).equals("null")){


		      sql = " UPDATE Manifestos " +
		      	    " SET  NR_Odometro_Inicial= " + ed.getNR_Odometro_Inicial() + ", " +
		      	    "      NR_Odometro_Final= " + ed.getNR_Odometro_Final() + ", " +
		      	    "      HR_Saida = '" + ed.getHR_Saida() + "', " +
		      	    "      HR_Chegada= '" + ed.getHR_Chegada() + "', " +
	      	    	"      DT_Chegada = '" + ed.getDT_Chegada() + "' " ;
		     sql += " WHERE oid_Manifesto = '" + ed.getOID_Manifesto() + "'";

		      executasql.executarUpdate(sql);

		      // atualiza a kilometragem atual do veiculo
		      if (String.valueOf(ed.getOID_Veiculo()) != null &&
			          !String.valueOf(ed.getOID_Veiculo()).equals("") &&
			          !String.valueOf(ed.getOID_Veiculo()).equals("null")){

			      sql = " UPDATE Veiculos " +
					    " SET  NR_Kilometragem_Atual= " + ed.getNR_Odometro_Final() + " " ;
				 sql += " WHERE oid_Veiculo = '" + ed.getOID_Veiculo() + "'";

				  executasql.executarUpdate(sql);
		      }

	      }else{
	          throw new Excecoes("Manifesto N�o Informado para altera��o !!!");
	      }

	    }

	    catch(Exception exc){
	      Excecoes excecoes = new Excecoes();
	      excecoes.setClasse(this.getClass().getName());
	      if (ed.getDM_Lancado_Ordem_Frete() != null && ed.getDM_Lancado_Ordem_Frete().equals("S")){
	        excecoes.setMensagem("Manifesto lan�ado em Ordem de Frete");
	      }else{
	        excecoes.setMensagem("Erro ao Finalizar Manifesto");
	      }
	      excecoes.setMetodo("alterar");
	      excecoes.setExc(exc);
	      throw excecoes;
	    }
	  }

  public void inclui_Ocorrencia(ManifestoED ed) throws Excecoes{

    String sql = null;

    try{
      sql =  " SELECT DM_Situacao, oid_Pessoa, NR_Manifesto from Manifestos ";
      sql += " where oid_Manifesto = '" + ed.getOID_Manifesto() + "'";


      ResultSet res = null;
      ResultSet res2 = null;
      res = this.executasql.executarConsulta(sql);

      while (res.next()){
        ed.setOID_Pessoa(res.getString("OID_Pessoa"));
        ed.setNR_Manifesto(res.getLong("NR_Manifesto"));
        ed.setDM_Situacao(res.getString("DM_Situacao"));
      }


      if (ed.getDM_Situacao() != null &&
          !ed.getDM_Situacao().equals("") &&
          !ed.getDM_Situacao().equals("null") &&
          ed.getDM_Situacao().equals("S")){
          Excecoes exc = new Excecoes();
          throw exc;
      }
      ed.setNM_Pessoa_Entrega("--");

      if (ed.getDM_Tipo() != null &&
          !ed.getDM_Tipo().equals("") &&
          !ed.getDM_Tipo().equals("null") &&
          (ed.getDM_Tipo().equals("W") || ed.getDM_Tipo().equals("Y"))){

              //// System.out.println(" Confirmando chegada manifesto " );

          ed.setDT_Chegada(Data.getDataDMY());
          ed.setHR_Chegada(Data.getHoraHM());

          sql = " update Manifestos set DT_Chegada= '" + ed.getDT_Chegada() + "', HR_Chegada= '" + ed.getHR_Chegada() + "',  DM_Situacao='S' ";
          sql += " where oid_Manifesto = '" + ed.getOID_Manifesto() + "'";

          //// System.out.println("bd sql " + sql);

          ed.setNM_Pessoa_Entrega("Unidade Destino");

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

          sql =  " SELECT OID_Conhecimento from Viagens ";
          sql += " WHERE Viagens.OID_Manifesto = '" + ed.getOID_Manifesto() + "'";

          res2 = this.executasql.executarConsulta(sql);

          FormataDataBean DataFormatada = new FormataDataBean();

          //popula
          while (res2.next()){
            //// System.out.println("bd sql ctc ent " + sql);


              Ocorrencia_ConhecimentoBD Ocorrencia_ConhecimentoBD = new Ocorrencia_ConhecimentoBD(this.executasql);
              Ocorrencia_ConhecimentoED ocorrencia_ConhecimentoED = new Ocorrencia_ConhecimentoED();

              ocorrencia_ConhecimentoED.setDT_Ocorrencia_Conhecimento(Data.getDataDMY());
              ocorrencia_ConhecimentoED.setHR_Ocorrencia_Conhecimento(Data.getHoraHM());
              ocorrencia_ConhecimentoED.setOID_Tipo_Ocorrencia(ed.getOID_Tipo_Ocorrencia());
              ocorrencia_ConhecimentoED.setOID_Conhecimento(res2.getString("oid_Conhecimento"));
              ocorrencia_ConhecimentoED.setTX_Observacao(ed.getTX_Observacao());
              ocorrencia_ConhecimentoED.setNM_Pessoa_Entrega(ed.getNM_Pessoa_Entrega());
              ocorrencia_ConhecimentoED.setDM_Tipo(ed.getDM_Tipo());
              Ocorrencia_ConhecimentoBD.inclui(ocorrencia_ConhecimentoED);
           }
      }

    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      if (ed.getDM_Lancado_Ordem_Frete() != null && ed.getDM_Lancado_Ordem_Frete().equals("S")){
        excecoes.setMensagem("J� confirmada a Chegada do Manifesto");
      }else{
        excecoes.setMensagem("Erro ao alterar Manifesto");
      }
      excecoes.setMetodo("alterar");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public ManifestoED inclui_AWBOLLLD (ManifestoED edMan) throws Excecoes {

    // System.out.println("inclui_AWB 1");

    edMan = this.getByRecord(edMan);

    String sql = null;
    String oid_Pessoa_Destinatario="";
    boolean tem_origem = false , tem_destino = false , tem_ctos = false;
    try {
      ResultSet res = null;
      Nota_FiscalED ed = new Nota_FiscalED ();

      sql = " SELECT OID_Pessoa FROM Unidades WHERE oid_Unidade = " + edMan.getOID_Unidade ();

    // System.out.println("inclui_AWB ->"+sql);

      res = this.executasql.executarConsulta (sql);
      if (res.next ()) {
        ed.setOID_Pessoa (res.getString ("OID_Pessoa"));
        tem_origem = true;
    // System.out.println("inclui_AWB 3");
      }

      sql = " SELECT OID_Pessoa FROM Unidades WHERE oid_Unidade = " + edMan.getOID_Unidade_Destino ();
    // System.out.println("inclui_AWB ->"+sql);

      res = this.executasql.executarConsulta (sql);
      if (res.next ()) {
        ed.setOID_Pessoa_Destinatario (res.getString ("OID_Pessoa"));
        oid_Pessoa_Destinatario=res.getString ("OID_Pessoa");
        tem_destino = true;
    // System.out.println("inclui_AWB oid_Pessoa_Destinatario="+oid_Pessoa_Destinatario);
      }

      sql = " SELECT SUM (Conhecimentos.NR_Peso)  as NR_Peso " +
          " ,SUM (Conhecimentos.NR_Peso_Cubado) as NR_Peso_Cubado " +
          " ,SUM (Conhecimentos.NR_Volumes)     as NR_Volumes " +
          " ,SUM (Conhecimentos.VL_Nota_Fiscal) as VL_Nota_Fiscal " +
          " FROM Viagens, Conhecimentos " +
          " WHERE Conhecimentos.OID_Conhecimento = Viagens.OID_Conhecimento " +
          " AND   Viagens.oid_manifesto = '" + edMan.getOID_Manifesto () + "'";

    // System.out.println("inclui_AWB ->"+sql);
      res = this.executasql.executarConsulta (sql);

      while (res.next ()) {
        ed.setNR_Peso (res.getDouble ("NR_Peso"));
        ed.setNR_Cubagem (0);
        ed.setNR_Peso_Cubado (res.getDouble ("NR_Peso_Cubado"));
        ed.setNR_Volumes (res.getDouble ("NR_Volumes"));
        ed.setVL_Nota_Fiscal (res.getDouble ("VL_Nota_Fiscal"));
    // System.out.println("inclui_AWB 10");

      }

      sql = " SELECT Conhecimentos.oid_Produto " +
          " ,Conhecimentos.oid_Modal " +
          " ,Conhecimentos.oid_Unidade " +
          " FROM Viagens, Conhecimentos " +
          " WHERE Conhecimentos.OID_Conhecimento = Viagens.OID_Conhecimento " +
          " AND   Viagens.oid_manifesto = '" + edMan.getOID_Manifesto () + "'";
    // System.out.println("inclui_AWB ->"+sql);

      res = this.executasql.executarConsulta (sql);

      if (res.next ()) {
        ed.setOID_Produto (res.getLong ("oid_Produto"));
        ed.setOID_Unidade (res.getLong ("oid_Unidade"));
        ed.setOID_Modal (res.getLong ("oid_Modal"));
    // System.out.println("inclui_AWB tem_ctos 16");
        tem_ctos = true;
      }
      ed.setNR_Nota_Fiscal (0);
      ed.setOid_Deposito (0);

      ed.setNM_Serie ("");
      ed.setDM_Transferencia ("N");
      ed.setDM_Exportacao ("N");
      ed.setDT_Emissao (Data.getDataDMY ());
      ed.setDT_Entrada (Data.getDataDMY ());
      ed.setHR_Entrada ("");
      ed.setNR_Pedido ("");
      ed.setDM_Situacao ("");
      ed.setDM_Tabela ("N");
      // System.out.println ("nf4");
      ed.setDM_Tipo_Conhecimento ("1");
      ed.setNR_Itens (0);
      ed.setNR_Lote (edMan.getOID_Manifesto ());
      ed.setDM_Tipo_Pagamento ("");
      ed.setTX_Observacao ("");

      if (tem_ctos && tem_origem && tem_destino) {

    // System.out.println("inclui_AWB tem_tudo");

        Nota_FiscalBD nota_fiscalBD = new Nota_FiscalBD (this.executasql);
        ed = nota_fiscalBD.inclui (ed);
    // System.out.println("inclui_AWB nova ->" + ed.getOID_Nota_Fiscal());

        if (ed.getOID_Nota_Fiscal () != null && ed.getOID_Nota_Fiscal ().length () > 4) {
          ConhecimentoED conED = new ConhecimentoED ();
          ConhecimentoBD conhecimentoBD = new ConhecimentoBD (this.executasql);
          conED.setOID_Conhecimento ("4900108CN1");
          conED = conhecimentoBD.getByRecord (conED);

    // System.out.println("inclui_AWB oid_Pessoa_Destinatario->>" +oid_Pessoa_Destinatario );

          conED.setOID_Pessoa_Destinatario(oid_Pessoa_Destinatario);
          conED.setDM_Tipo_Documento ("M");
    // System.out.println("inclui_AWB quase");

          try {
            conED.setNR_Conhecimento (0);
            conED.setNR_ACT (0);
            conED.setNR_Duplicata (0);
            conED.setDM_Impresso ("N");
            conED.setDT_Emissao (Data.getDataDMY ());
            conED.setVL_FRETE_PESO (0);
            conED.setVL_FRETE_VALOR (0);
            conED.setVL_ICMS (0);
            conED.setVL_PEDAGIO (0);
            conED.setVL_TOTAL_FRETE (0);
            conED.setVL_BASE_CALCULO_ICMS (0);
            conED.setOID_Nota_Fiscal (ed.getOID_Nota_Fiscal ());

    // System.out.println("inclui_AWB quase...");

            conED = conhecimentoBD.inclui (conED);
            edMan.setOID_Conhecimento(conED.getOID_Conhecimento());

    // System.out.println("inclui_AWB foi " + conED.getOID_Conhecimento());

          }
          catch (Exception e) {
            e.printStackTrace ();
            throw new Excecoes (e.getMessage () , e , getClass ().getName () , "inclui(ConhecimentoED ed)");
          }

        }
      }
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMetodo ("inclui_AWB");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return edMan;
  }


  public ManifestoED inclui_AWB (ManifestoED edMan) throws Excecoes {

    // System.out.println ("inclui_AWB 1");

    edMan = this.getByRecord (edMan);

    String sql = null;
    String oid_Pessoa_Destinatario = "";
    try {
      ResultSet res = null;

      sql = " SELECT OID_Pessoa FROM Unidades WHERE oid_Unidade = " + edMan.getOID_Unidade_Destino ();
      // System.out.println ("inclui_AWB ->" + sql);

      res = this.executasql.executarConsulta (sql);
      if (res.next ()) {
        oid_Pessoa_Destinatario = res.getString ("OID_Pessoa");
        // System.out.println ("inclui_AWB oid_Pessoa_Destinatario=" + oid_Pessoa_Destinatario);
        ConhecimentoED conED = new ConhecimentoED ();
        ConhecimentoBD conhecimentoBD = new ConhecimentoBD (this.executasql);

        conED.setOID_Conhecimento ("1900248CN1");
        conED = conhecimentoBD.getByRecord (conED);

        // System.out.println ("inclui_AWB oid_Pessoa_Destinatario->>" + oid_Pessoa_Destinatario);

        conED.setOID_Pessoa_Destinatario (oid_Pessoa_Destinatario);
        conED.setDM_Tipo_Documento ("M");
        // System.out.println ("inclui_AWB quase");

        try {
          conED.setNR_Conhecimento (0);
          conED.setNR_ACT (0);
          conED.setNR_Duplicata (0);
          conED.setDM_Impresso ("N");
          conED.setDT_Emissao (Data.getDataDMY ());
          conED.setVL_FRETE_PESO (0);
          conED.setVL_FRETE_VALOR (0);
          conED.setVL_ICMS (0);
          conED.setVL_PEDAGIO (0);
          conED.setVL_TOTAL_FRETE (0);
          conED.setVL_BASE_CALCULO_ICMS (0);
          conED.setOID_Nota_Fiscal ("");
          // System.out.println ("inclui_AWB quase...");

          conED = conhecimentoBD.inclui (conED);
          edMan.setOID_Conhecimento (conED.getOID_Conhecimento ());
        }
        catch (Exception e) {
          e.printStackTrace ();
          throw new Excecoes (e.getMessage () , e , getClass ().getName () , "inclui(ConhecimentoED ed)");
        }
      }
      // System.out.println ("inclui_AWB foi " + edMan.getOID_Conhecimento ());

      if (edMan.getOID_Conhecimento () != null && edMan.getOID_Conhecimento ().length () > 4) {

        sql = " UPDATE Manifestos SET oid_AWB = '" + edMan.getOID_Conhecimento () + "'" +
            "  WHERE Manifestos.oid_Manifesto ='" + edMan.getOID_Manifesto () + "'";
        executasql.executarUpdate (sql);

        Conhecimento_Nota_FiscalBD ctoNota_FiscalBD = new Conhecimento_Nota_FiscalBD (this.executasql);

        sql = " SELECT Notas_Fiscais.OID_Nota_Fiscal " +
            " FROM  Viagens, Conhecimentos, Conhecimentos_Notas_Fiscais, Notas_Fiscais  " +
            " WHERE Conhecimentos.OID_Conhecimento = Viagens.OID_Conhecimento " +
            " AND   Conhecimentos.OID_Conhecimento = Conhecimentos_Notas_Fiscais.OID_Conhecimento " +
            " AND   Conhecimentos_Notas_Fiscais.OID_Nota_Fiscal = Notas_Fiscais.OID_Nota_Fiscal " +
            " AND   Viagens.oid_manifesto = '" + edMan.getOID_Manifesto () + "'";
        // System.out.println ("inclui_AWB ->" + sql);

        res = this.executasql.executarConsulta (sql);

        while (res.next ()) {

        // System.out.println ("inclui_AWB Notas_Fiscais ->" + res.getString ("OID_Nota_Fiscal"));

          try {
            Conhecimento_Nota_FiscalED ctoNFed = new Conhecimento_Nota_FiscalED ();

            ctoNFed.setDT_Conhecimento_Nota_Fiscal (Data.getDataDMY ());
            ctoNFed.setHR_Conhecimento_Nota_Fiscal (Data.getHoraHM ());
            ctoNFed.setOID_Nota_Fiscal (res.getString ("OID_Nota_Fiscal"));
            ctoNFed.setNR_Peso (0);
            ctoNFed.setNR_Peso_Cubado (0);
            ctoNFed.setNR_Volumes (0);
            ctoNFed.setOID_Conhecimento (edMan.getOID_Conhecimento ());
            ctoNFed.setOID_Unidade (edMan.getOID_Unidade ());
            ctoNFed.setDM_Tipo_Conhecimento ("M");
            ctoNota_FiscalBD.inclui (ctoNFed);

          }
          catch (Exception e) {
            e.printStackTrace ();
            throw new Excecoes (e.getMessage () , e , getClass ().getName () , "inclui(ConhecimentoED ed)");
          }
        }
      }
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMetodo ("inclui_AWB");
      excecoes.setExc (exc);
      throw excecoes;
    }
    return edMan;
  }


  public void deleta(ManifestoED ed) throws Excecoes{
    String sql = null;
    ResultSet res = null;
    try{

     //// System.out.println("exclui " + ed.getDM_Lancado_Ordem_Frete());

      sql = " SELECT Ordens_Fretes.oid_Ordem_Frete " +
          " FROM   Ordens_Fretes, Ordens_Manifestos " +
          " WHERE  Ordens_Fretes.oid_Ordem_Frete = Ordens_Manifestos.oid_Ordem_Frete " +
          " AND    Ordens_Manifestos.oid_Manifesto = '" + ed.getOID_Manifesto () + "'";
       // System.out.println (sql);
      ResultSet resTP = this.executasql.executarConsulta (sql);
      if (resTP.next ()) {
            throw new Excecoes("Manifesto lan�ado em Ordem de Frete!");
      }


      sql =  " SELECT OID_Embarque from Embarques ";
      sql += " where oid_Manifesto = '" + ed.getOID_Manifesto() + "'";

           //// System.out.println("exclui antes embarque " + sql);
      // System.out.println (sql);

      res = null;
      res = this.executasql.executarConsulta(sql);

      String OID_Embarque = null;

      while (res.next()){

           //// System.out.println("tem embarque " );

        OID_Embarque = res.getString("OID_Embarque");
           //// System.out.println("exclui antes embarque  while");

        sql = "delete from Ocorrencias_Embarques WHERE OID_Embarque = ";
        sql += "(" + OID_Embarque + ")";
           //// System.out.println("exclui ocorre embarque " + sql);
        // System.out.println (sql);

        int i = executasql.executarUpdate(sql);

        sql = "delete from Notas_Fiscais_Embarques WHERE OID_Embarque = ";
        sql += "(" + OID_Embarque + ")";
        // System.out.println (sql);

        i = executasql.executarUpdate(sql);
      }

      sql = "delete from Embarques WHERE OID_Embarque = ";
      sql += "(" + OID_Embarque + ")";
      // System.out.println (sql);

      int i = executasql.executarUpdate(sql);

           //// System.out.println("exclui embarque ");


      sql = "delete from Ordens_Manifestos WHERE oid_Manifesto = ";
      sql += "('" + ed.getOID_Manifesto() + "')";
      // System.out.println (sql);
           //// System.out.println("exclui ordem manifesto " + sql);

      i = executasql.executarUpdate(sql);

           //// System.out.println("exclui ordem manifesto ");


//      sql = "delete from Viagens WHERE oid_Manifesto = ";
//      sql += "('" + ed.getOID_Manifesto() + "')";
//           //// System.out.println("exclui  Viagens " + sql);
//
//      i = executasql.executarUpdate(sql);


      sql =  " SELECT OID_Viagem, Oid_Conhecimento from Viagens ";
      sql += " where oid_Manifesto = '" + ed.getOID_Manifesto() + "'";

      // System.out.println (sql);
      //// System.out.println("sql viagem " + sql);

      res = null;
      res = this.executasql.executarConsulta(sql);

     ViagemED viagemED = new ViagemED();
     ViagemRN viagemRN = new ViagemRN();

      while (res.next()){
          //// System.out.println("exclui viagem ->> " + res.getString("OID_Viagem"));
          viagemED.setDM_Lancado_Ordem_Frete("NAO_VERIFICAR");
          viagemED.setOID_Viagem(res.getString("OID_Viagem"));
          viagemED.setOID_Conhecimento(res.getString("Oid_Conhecimento"));
          viagemRN.deleta(viagemED);

      }





      sql = "delete from Manifestos WHERE oid_Manifesto = ";
      sql += "('" + ed.getOID_Manifesto() + "')";
      // System.out.println (sql);

      i = executasql.executarUpdate(sql);
           //// System.out.println("exclui manifesto ");


    }

    catch(Exception exc){
    	throw new Excecoes("Erro ao excluir Manifesto: " + exc.getMessage(),
    			exc, this.getClass().getName(), "deleta(ManifestoED ed)");
    }
  }

  public ArrayList lista(ManifestoED ed)throws Excecoes{


    String sql = null;
    ArrayList list = new ArrayList();

    try{

      sql =  " SELECT Manifestos.DM_Tipo_Manifesto, Manifestos.OID_Manifesto, Manifestos.DM_Expresso, " +
      		 " Manifestos.OID_Pessoa_Entregadora, Manifestos.DT_Emissao, Manifestos.oid_Unidade_Destino,  " +
      		 " Manifestos.NR_Manifesto, Unidades.CD_Unidade, Veiculos.NR_Placa, " +
      		 " Cidades.NM_Cidade, Cidade_Unidade.NM_Cidade as NM_Cidade_Unidade, " +
      		 " Manifestos.VL_Frete_Carreteiro, Manifestos.OID_Veiculo_Carreta, " +
      		 " Manifestos.OID_Tipo_Servico_Aereo, Manifestos.OID_Cia_Aerea " +
      		 " FROM Manifestos, Unidades, Veiculos, Cidades, Cidades Cidade_Unidade, Pessoas " +
      		 " WHERE Manifestos.OID_Unidade = Unidades.OID_Unidade " +
      		 " AND Pessoas.OID_Pessoa = Unidades.OID_Pessoa " +
      		 " AND Pessoas.OID_Cidade = Cidade_Unidade.OID_Cidade " +
      		 " AND Manifestos.OID_Veiculo = Veiculos.OID_Veiculo " +
      		 " AND Manifestos.OID_Cidade = Cidades.OID_Cidade ";


      // System.out.println(" manif roman" + ed.getDM_Manifesto_Romaneio());


      if (!"".equals (ed.getDM_Manifesto_Romaneio ()) && !"T".equals (ed.getDM_Manifesto_Romaneio ())) { // manifestos
        sql += " AND Manifestos.DM_Tipo_Manifesto = '" + ed.getDM_Manifesto_Romaneio () + "'";
      }

      if (String.valueOf(ed.getDT_Emissao()) != null && !String.valueOf(ed.getDT_Emissao()).equals("") && !String.valueOf(ed.getDT_Emissao()).equals("null")){
        sql += " and Manifestos.DT_Emissao = '" + ed.getDT_Emissao() + "'";
      }
      if (String.valueOf(ed.getDT_Emissao_Inicial()) != null && !String.valueOf(ed.getDT_Emissao_Inicial()).equals("") && !String.valueOf(ed.getDT_Emissao_Inicial()).equals("null")){
        sql += " and Manifestos.DT_Emissao >= '" + ed.getDT_Emissao_Inicial() + "'";
      }
      if (String.valueOf(ed.getDT_Emissao_Final()) != null && !String.valueOf(ed.getDT_Emissao_Final()).equals("") && !String.valueOf(ed.getDT_Emissao_Final()).equals("null")){
        sql += " and Manifestos.DT_Emissao <= '" + ed.getDT_Emissao_Final() + "'";
      }


      if (String.valueOf(ed.getNR_Manifesto()) != null &&
          !String.valueOf(ed.getNR_Manifesto()).equals("0") &&
          !String.valueOf(ed.getNR_Manifesto()).equals("null")){
        sql += " and Manifestos.NR_Manifesto = " + ed.getNR_Manifesto();
      }

      if (String.valueOf(ed.getOID_Unidade()) != null &&
          !String.valueOf(ed.getOID_Unidade()).equals("0") &&
          !String.valueOf(ed.getOID_Unidade()).equals("null")){
        sql += " and Manifestos.OID_Unidade = " + ed.getOID_Unidade();
      }
      if (String.valueOf(ed.getOID_Pessoa()) != null &&
          !String.valueOf(ed.getOID_Pessoa()).equals("") &&
          !String.valueOf(ed.getOID_Pessoa()).equals("null")){
        sql += " and Manifestos.OID_Pessoa = '" + ed.getOID_Pessoa() + "'";
      }
      if (String.valueOf(ed.getOID_Veiculo()) != null &&
            !String.valueOf(ed.getOID_Veiculo()).equals("") &&
            !String.valueOf(ed.getOID_Veiculo()).equals("null")){
          sql += " and Manifestos.oid_Veiculo = '" + ed.getOID_Veiculo() + "'";
        }
      if (String.valueOf(ed.getOID_Veiculo_Carreta()) != null &&
            !String.valueOf(ed.getOID_Veiculo_Carreta()).equals("") &&
            !String.valueOf(ed.getOID_Veiculo_Carreta()).equals("null")){
          sql += " and Manifestos.OID_Veiculo_Carreta = '" + ed.getOID_Veiculo_Carreta() + "'";
        }

//// System.out.println("6 ");

      sql += " Order by Manifestos.DT_Emissao Desc, Manifestos.NR_Manifesto LIMIT 100 ";

// System.out.println("6 " + sql) ;

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      FormataDataBean DataFormatada = new FormataDataBean();

      //popula
      while (res.next()){
        ManifestoED edVolta = new ManifestoED();

        edVolta.setOID_Tipo_Servico_Aereo(res.getLong("OID_Tipo_Servico_Aereo"));
        edVolta.setOID_Cia_Aerea(res.getLong("OID_Cia_Aerea"));

        edVolta.setOID_Manifesto(res.getString("OID_Manifesto"));

        edVolta.setDT_Emissao(res.getString("DT_Emissao"));

        DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
        edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());
        edVolta.setOID_Pessoa_Entregadora(res.getString("OID_Pessoa_Entregadora"));

        edVolta.setCD_Unidade(res.getString("CD_Unidade"));
        edVolta.setNR_Placa(res.getString("NR_Placa"));
        edVolta.setOID_Veiculo_Carreta(res.getString("OID_Veiculo_Carreta"));

        edVolta.setNM_Cidade_Unidade(res.getString("NM_Cidade_Unidade"));
        edVolta.setDM_Expresso(res.getString("DM_Expresso"));
        edVolta.setNR_Manifesto(res.getLong("NR_Manifesto"));
        edVolta.setVL_Frete_Carreteiro(res.getDouble("VL_Frete_Carreteiro"));

        edVolta.setDM_Manifesto_Romaneio("Manifesto");
        if ("A".equals(res.getString("DM_Tipo_Manifesto")))
          edVolta.setDM_Manifesto_Romaneio("Aereo");
        if ("R".equals(res.getString("DM_Tipo_Manifesto")))
          edVolta.setDM_Manifesto_Romaneio("Rom.Col/Ent");
        if ("MR".equals(res.getString("DM_Tipo_Manifesto")))
          edVolta.setDM_Manifesto_Romaneio("Man.Redespacho");
        if ("MR".equals(res.getString("DM_Tipo_Manifesto")))
          edVolta.setDM_Manifesto_Romaneio("Man.Servi�o");
        if ("RN".equals(res.getString("DM_Tipo_Manifesto")))
          edVolta.setDM_Manifesto_Romaneio("Rom.N.Fiscais");
        if ("RI".equals(res.getString("DM_Tipo_Manifesto")))
            edVolta.setDM_Manifesto_Romaneio("Rom.Itens Notas");
        if ("RG".equals(res.getString("DM_Tipo_Manifesto")))
            edVolta.setDM_Manifesto_Romaneio("Rom.Gaiola");

        if (util.doValida(res.getString("OID_Pessoa_Entregadora"))){
          sql =  " SELECT NM_Razao_Social FROM Pessoas WHERE ";
          sql += " OID_Pessoa = '" + res.getString("OID_Pessoa_Entregadora") + "'";
          // System.out.println(sql);
          ResultSet resTP = this.executasql.executarConsulta(sql);
          while (resTP.next()){
            edVolta.setNM_Pessoa_Entregadora(resTP.getString("NM_Razao_Social"));
            // System.out.println("entregadora ok");
          }
         }

         edVolta.setNM_Cidade_Destino(res.getString("NM_Cidade"));
         if (res.getLong("oid_Unidade_Destino")>0){
             sql =  " SELECT CD_Unidade, NM_Razao_Social FROM Unidades, Pessoas WHERE  Unidades.oid_Pessoa = Pessoas.oid_Pessoa ";
             sql += "        AND Unidades.oid_Unidade = " + res.getString("oid_Unidade_Destino");
             // System.out.println(sql);
             ResultSet resTP = this.executasql.executarConsulta(sql);
             if (resTP.next()){
                 edVolta.setNM_Cidade_Destino(res.getString("NM_Cidade")+"("+resTP.getString("CD_Unidade")+"-"+(resTP.getString("NM_Razao_Social")+"                         ").substring(0,20) +")" );
             }
         }

         edVolta.setDM_Lancado_Ordem_Frete ("N");
         sql = " SELECT Ordens_Fretes.oid_Ordem_Frete " +
             " FROM   Ordens_Fretes, Ordens_Manifestos " +
             " WHERE  Ordens_Fretes.oid_Ordem_Frete = Ordens_Manifestos.oid_Ordem_Frete " +
             " AND    Ordens_Manifestos.oid_Manifesto = '" + edVolta.getOID_Manifesto () + "'";
         // System.out.println (sql);
         ResultSet resTP = this.executasql.executarConsulta (sql);
         if (resTP.next ()) {
           edVolta.setDM_Lancado_Ordem_Frete ("S");
         }

         edVolta.setOID_Tipo_Servico_Aereo(res.getLong("oid_tipo_servico_aereo"));
         if (edVolta.getOID_Tipo_Servico_Aereo() > 0){

        	 sql = " SELECT Tipos_Servicos_Aereos.cd_tipo_servico_aereo, Tipos_Servicos_Aereos.nm_tipo_servico_aereo " +
	             " FROM   Tipos_Servicos_Aereos " +
	             " WHERE  Tipos_Servicos_Aereos.oid_tipo_servico_aereo = '" + edVolta.getOID_Tipo_Servico_Aereo() + "'";
	         ResultSet resTSA = this.executasql.executarConsulta (sql);

	         if (resTSA.next ()) {
	           edVolta.setCD_Tipo_Servico_Aereo(resTSA.getString("cd_tipo_servico_aereo"));
	           edVolta.setNM_Tipo_Servico_Aereo(resTSA.getString("nm_tipo_servico_aereo"));
	         }
         }

         edVolta.setOID_Cia_Aerea(res.getLong("oid_cia_aerea"));
         if (edVolta.getOID_Cia_Aerea() > 0){

        	 sql = " SELECT Cia_Aereas.cd_cia_aerea " +
	             " FROM   Cia_Aereas " +
	             " WHERE  Cia_Aereas.oid_cia_aerea = '" + edVolta.getOID_Cia_Aerea() + "'";
	         ResultSet resCA = this.executasql.executarConsulta (sql);

	         if (resCA.next ()) {
		           edVolta.setCD_Cia_Aerea(resCA.getString("cd_cia_aerea"));
	         }
         }

         sql = " SELECT SUM (Conhecimentos.VL_Total_Frete) as tt_Frete " +
             " FROM   Conhecimentos, Viagens " +
             " WHERE  Conhecimentos.oid_Conhecimento = Viagens.oid_Conhecimento " +
             " AND    Viagens.oid_Manifesto = '" + edVolta.getOID_Manifesto () + "'";
         resTP = this.executasql.executarConsulta (sql);
         if (resTP.next ()) {
           edVolta.setVL_Total_Frete_CTRC(resTP.getDouble("tt_Frete"));
           if (edVolta.getVL_Total_Frete_CTRC() > 0){
           }
         }
         list.add(edVolta);
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao listar Manifesto");
      excecoes.setMetodo("lista");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return list;
  }

  public ArrayList Manifesto_Lista_Acerto(ManifestoED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();

    try{

      sql =  "SELECT Acertos.oid_Acerto, Acertos.NR_Acerto, Manifestos.OID_Manifesto, Manifestos.DT_Emissao, Manifestos.NR_Manifesto, Unidades.CD_Unidade, Veiculos.NR_Placa, Cidades.NM_Cidade, Cidade_Unidade.NM_Cidade as NM_Cidade_Unidade from Manifestos, Unidades, Veiculos, Cidades, Cidades Cidade_Unidade, Pessoas, Acertos ";
      sql += " WHERE Manifestos.OID_Unidade = Unidades.OID_Unidade ";
      sql += " AND Pessoas.OID_Pessoa = Unidades.OID_Pessoa ";
      sql += " AND Pessoas.OID_Cidade = Cidade_Unidade.OID_Cidade ";
      sql += " AND Manifestos.OID_Veiculo = Veiculos.OID_Veiculo ";
      sql += " AND Manifestos.OID_Cidade = Cidades.OID_Cidade ";
      sql += " AND Manifestos.OID_Acerto = Acertos.OID_Acerto ";


      if (String.valueOf(ed.getOID_Acerto()) != null &&
          !String.valueOf(ed.getOID_Acerto()).equals("0") &&
          !String.valueOf(ed.getOID_Acerto()).equals("null")){
        sql += " and Manifestos.OID_Acerto = " + ed.getOID_Acerto();
      }

      sql += " Order by Manifestos.NR_Manifesto ";

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      FormataDataBean DataFormatada = new FormataDataBean();

      //popula
      while (res.next()){
        ManifestoED edVolta = new ManifestoED();

        edVolta.setOID_Manifesto(res.getString("OID_Manifesto"));

        edVolta.setDT_Emissao(res.getString("DT_Emissao"));

        DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
        edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());

        edVolta.setCD_Unidade(res.getString("CD_Unidade"));
        edVolta.setNR_Placa(res.getString("NR_Placa"));
        edVolta.setNM_Cidade_Unidade(res.getString("NM_Cidade_Unidade"));
        edVolta.setNM_Cidade_Destino(res.getString("NM_Cidade"));
        edVolta.setNR_Manifesto(res.getLong("NR_Manifesto"));
        edVolta.setOID_Acerto(res.getLong("oid_Acerto"));
        edVolta.setNR_Acerto(res.getLong("NR_Acerto"));

        list.add(edVolta);
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao listar Manifesto");
      excecoes.setMetodo("lista");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return list;
  }


  public ManifestoED getByRecord(ManifestoED ed)
  throws Excecoes{
      ManifestoED edVolta = new ManifestoED();
      try{
          String sql =
              " SELECT *, " +
              "        Manifestos.oid_Pessoa , " +
              "        Manifestos.oid_cidade as dest_man ," +
              "        Manifestos.oid_cia_aerea , " +
              "        Manifestos.oid_tipo_servico_aereo , " +
              "        Unidades.CD_Unidade, " +
              "        Pessoas.NM_Fantasia " +
              " FROM Manifestos, " +
              "      Unidades, " +
              "      Pessoas " +
              " WHERE Manifestos.oid_unidade = Unidades.oid_unidade  "+
              " and   Unidades.oid_Pessoa = Pessoas.oid_pessoa  ";
          if (util.doValida(ed.getOID_Manifesto())) {
              sql += " and Manifestos.OID_Manifesto = '" + ed.getOID_Manifesto() + "'";
          }
          if (ed.getNR_Manifesto() > 0) {
              sql += " and Manifestos.NR_Manifesto = " + ed.getNR_Manifesto();
          }
          if (ed.getOID_Unidade() > 0){
              sql += " and Manifestos.OID_Unidade = " + ed.getOID_Unidade();
          }
//System.out.println(sql);
          ResultSet res = this.executasql.executarConsulta(sql);

          while (res.next()){
              edVolta.setOID_Manifesto(res.getString("OID_Manifesto"));
              edVolta.setOID_Cidade_Origem(res.getLong("OID_Cidade_Origem"));
//              edVolta.setOID_Cidade(res.getLong("OID_Cidade"));
              edVolta.setOID_Cidade(res.getLong("dest_man"));
              edVolta.setOID_Conhecimento(res.getString("OID_AWB"));

              edVolta.setOID_Cia_Aerea(res.getLong("oid_cia_aerea"));
              edVolta.setOID_Tipo_Servico_Aereo(res.getLong("oid_tipo_servico_aereo"));

              edVolta.setOID_Veiculo(res.getString("OID_Veiculo"));
              edVolta.setOID_Veiculo_Carreta(res.getString("OID_Veiculo_Carreta"));
              edVolta.setNR_Placa(res.getString("OID_Veiculo"));

              edVolta.setOID_Pessoa(res.getString("OID_Pessoa"));
              edVolta.setOID_Pessoa_Entregadora(res.getString("OID_Pessoa_Entregadora"));
              edVolta.setOID_Pessoa_Reveza(res.getString("OID_Pessoa_Reveza"));
              edVolta.setOID_Seguradora(res.getLong("OID_Seguradora"));
              edVolta.setOID_Gaiola(res.getLong("OID_Gaiola"));
              edVolta.setDM_Expresso(res.getString("DM_Expresso"));
              edVolta.setDM_Manifesto_Romaneio(res.getString("DM_Tipo_Manifesto"));

              edVolta.setDT_Previsao_Chegada(FormataData.formataDataBT(res.getDate("DT_Previsao_Chegada")));
              edVolta.setDT_Chegada(FormataData.formataDataBT(res.getDate("DT_Chegada")));
              edVolta.setHR_Chegada(res.getString("HR_Chegada"));

              edVolta.setDM_Situacao(res.getString("DM_Situacao"));
              edVolta.setDT_Emissao(FormataData.formataDataBT(res.getString("DT_Emissao")));
              edVolta.setDT_Troca_Motorista(FormataData.formataDataBT(res.getDate("DT_Troca_Motorista")));
              edVolta.setHR_Saida(res.getString("HR_Saida"));
              edVolta.setTX_Observacao(res.getString("TX_Observacao"));
              edVolta.setNM_Ajudante1(res.getString("NM_Ajudante1"));
              edVolta.setNM_Ajudante2(res.getString("NM_Ajudante2"));
              edVolta.setNM_Ajudante3(res.getString("NM_Ajudante3"));

              edVolta.setHR_Previsao_Chegada(res.getString("HR_Previsao_Chegada"));
              edVolta.setNM_Liberacao_Seguradora(res.getString("NM_Liberacao_Seguradora"));
              edVolta.setNR_Manifesto(res.getLong("NR_Manifesto"));
              edVolta.setNR_Odometro_Inicial(res.getLong("NR_Odometro_Inicial"));
              edVolta.setNR_Odometro_Final(res.getLong("NR_Odometro_Final"));
              edVolta.setNR_Odometro_Troca_Motorista(res.getLong("NR_Odometro_Troca_Motorista"));
              edVolta.setOID_Unidade(res.getLong("OID_Unidade"));
              edVolta.setOID_Unidade_Destino(res.getLong("OID_Unidade_Destino"));

              edVolta.setCD_Unidade(res.getString("CD_Unidade"));
              edVolta.setNM_Cidade_Unidade(res.getString("NM_Fantasia"));

              edVolta.setCD_Rota_Entrega(res.getString("cd_Rota_Entrega"));

              edVolta.setNR_Ajudante(res.getInt("NR_Ajudante"));
              edVolta.setNR_KIT(res.getInt("NR_KIT"));
              edVolta.setNR_KM_Viagem(res.getLong("NR_KM_Viagem"));

              edVolta.setPE_Custo_Entrega(res.getDouble("PE_Custo_Entrega"));

              if (edVolta.getOID_Tipo_Servico_Aereo() > 0){
             	 sql = " SELECT Tipos_Servicos_Aereos.cd_tipo_servico_aereo, Tipos_Servicos_Aereos.nm_tipo_servico_aereo " +
     	             " FROM   Tipos_Servicos_Aereos " +
     	             " WHERE  Tipos_Servicos_Aereos.oid_tipo_servico_aereo = '" + edVolta.getOID_Tipo_Servico_Aereo() + "'";
     	         // System.out.println (sql);
     	         ResultSet resTSA = this.executasql.executarConsulta (sql);

     	         if (resTSA.next ()) {
     	           edVolta.setCD_Tipo_Servico_Aereo(resTSA.getString("cd_tipo_servico_aereo"));
     	           edVolta.setNM_Tipo_Servico_Aereo(resTSA.getString("nm_tipo_servico_aereo"));
     	         }
              }

              if (edVolta.getOID_Cia_Aerea() > 0){

             	 sql = " SELECT Cia_Aereas.cd_cia_aerea " +
     	             " FROM   Cia_Aereas " +
     	             " WHERE  Cia_Aereas.oid_cia_aerea = '" + edVolta.getOID_Cia_Aerea() + "'";
     	         // System.out.println (sql);
     	         ResultSet resCA = this.executasql.executarConsulta (sql);

     	         if (resCA.next ()) {
     		           edVolta.setCD_Cia_Aerea(resCA.getString("cd_cia_aerea"));
     	         }
              }

              if (res.getString("CD_Roteiro") != null &&
                      !String.valueOf(res.getString("CD_Roteiro")).equals("") &&
                      !String.valueOf(res.getString("CD_Roteiro")).equals("null")){

                  sql =  " SELECT Roteiros.CD_Roteiro, Roteiros.NM_ROTEIRO from Roteiros WHERE ";
                  sql += " CD_Roteiro = '" + res.getString("CD_Roteiro") + "'";
                  // System.out.println(sql);
                  ResultSet resTP = this.executasql.executarConsulta(sql);

                  while (resTP.next()){
                      edVolta.setCD_Roteiro(resTP.getString("CD_Roteiro"));
                      edVolta.setNM_Roteiro(resTP.getString("NM_ROTEIRO"));
                      // System.out.println("rot ok");

                  }
              }

              if (util.doValida(res.getString("OID_Pessoa_Entregadora"))){
                  sql =  " SELECT NM_Razao_Social from Pessoas WHERE ";
                  sql += " OID_Pessoa = '" + res.getString("OID_Pessoa_Entregadora") + "'";
                  ResultSet resTP = this.executasql.executarConsulta(sql);
                  while (resTP.next()){
                      edVolta.setNM_Pessoa_Entregadora(resTP.getString("NM_Razao_Social"));
                  }
              }

              sql= " SELECT Conhecimentos.VL_Total_Frete, Conhecimentos.VL_Frete_Peso, Conhecimentos.VL_Pedagio, " +
              		" Conhecimentos.NR_Peso as NR_Peso_CTRC, Conhecimentos.NR_Peso_Cubado as NR_Peso_Cubado, Conhecimentos.oid_Programacao_Carga "+
              " FROM Manifestos, Viagens, Conhecimentos " +
              " WHERE Manifestos.OID_Manifesto = Viagens.OID_Manifesto "+
              " AND Conhecimentos.OID_Conhecimento = Viagens.OID_Conhecimento "+
              " AND Conhecimentos.VL_Total_Frete > 0 "+
              " AND Manifestos.oid_manifesto = '" + ed.getOID_Manifesto() + "'";
              ResultSet resCTRC = this.executasql.executarConsulta(sql);
              double VL_Total_Frete_CTRC=0;
              double VL_Total_Frete_Peso_CTRC=0;
              double NR_Total_Peso_CTRC=0;
              String oid_Programacao_Carga = "0";
              while (resCTRC.next()){
                double NR_Peso = resCTRC.getDouble ("NR_Peso_CTRC");
                if (resCTRC.getDouble ("NR_Peso_CTRC")<resCTRC.getDouble ("NR_Peso_Cubado")) {
                  NR_Peso = resCTRC.getDouble ("NR_Peso_Cubado");
                }
                VL_Total_Frete_Peso_CTRC += resCTRC.getDouble("VL_Frete_Peso")+resCTRC.getDouble("VL_Pedagio");
                VL_Total_Frete_CTRC += resCTRC.getDouble("VL_Total_Frete");
                NR_Total_Peso_CTRC=NR_Total_Peso_CTRC + NR_Peso;
                oid_Programacao_Carga = resCTRC.getString("oid_Programacao_Carga");
              }

              double VL_Total_Frete_Ordem_Servico=0;
              sql = " SELECT SUM (vl_lancamento) as vl_lancamento " +
                  " FROM   Movimentos_Processos, Viagens " +
                  " WHERE  Movimentos_Processos.dm_debito_credito ='C' " +
                  " AND    Movimentos_Processos.oid_Processo = Viagens.oid_Processo " +
                  " AND    Viagens.oid_manifesto = '" + ed.getOID_Manifesto() + "'";
              resCTRC = this.executasql.executarConsulta(sql);
              while (resCTRC.next()){
                VL_Total_Frete_Ordem_Servico= (resCTRC.getDouble ("vl_lancamento"));
              }


              edVolta.setVL_Total_Frete_Peso_CTRC(VL_Total_Frete_Peso_CTRC);
              edVolta.setVL_Total_Frete_CTRC(VL_Total_Frete_CTRC+VL_Total_Frete_Ordem_Servico);
              edVolta.setNR_Total_Peso_CTRC(NR_Total_Peso_CTRC);

              edVolta.setDM_Lancado_Ordem_Frete("N");

              edVolta.setOid_Programacao_Carga(oid_Programacao_Carga);

              double VL_Frete_Carreteiro=0;
              sql = " SELECT SUM (vl_ordem_frete) as VL_Frete_Carreteiro " +
                  " FROM   Ordens_Fretes, Ordens_Manifestos " +
                  " WHERE  Ordens_Fretes.DM_Frete = 'P' " +
                  " AND    Ordens_Fretes.VL_Ordem_Frete >0 " +
                  " AND    Ordens_Fretes.oid_Ordem_Frete = Ordens_Manifestos.oid_Ordem_Frete " +
                  " AND    Ordens_Manifestos.oid_Manifesto = '" + ed.getOID_Manifesto () + "'";

              // System.out.println(sql);

              resCTRC = this.executasql.executarConsulta(sql);
              while (resCTRC.next()){
                VL_Frete_Carreteiro= (resCTRC.getDouble ("VL_Frete_Carreteiro"));
                if (VL_Frete_Carreteiro>0) {
                	edVolta.setDM_Lancado_Ordem_Frete("S");
                }
              }

              if (VL_Frete_Carreteiro<=0){
                VL_Frete_Carreteiro=res.getDouble("VL_Frete_Carreteiro");
              }
              edVolta.setVL_Frete_Carreteiro(VL_Frete_Carreteiro);

              edVolta.setDM_Viagem("N");
              sql= " SELECT COUNT (Viagens.oid_Viagem) as qtVia "+
	                   " FROM Viagens " +
	                   " WHERE Viagens.oid_manifesto = '" + ed.getOID_Manifesto() + "'";
	          resCTRC = this.executasql.executarConsulta(sql);
	          if (resCTRC.next()){
	        	  if (resCTRC.getLong("qtVia")>0) edVolta.setDM_Viagem("S");
              }
              if ("N".equals(edVolta.getDM_Viagem())) {
                  sql= " SELECT COUNT (Coletas_RCE.oid_manifesto) as qtVia "+
                  " FROM Coletas_RCE " +
                  " WHERE Coletas_RCE.oid_manifesto = '" + ed.getOID_Manifesto() + "'";
		         resCTRC = this.executasql.executarConsulta(sql);
		         if (resCTRC.next()){
		       	  if (resCTRC.getLong("qtVia")>0) edVolta.setDM_Viagem("S");
		         }
              }

              if ("N".equals(edVolta.getDM_Lancado_Ordem_Frete())) {
                  sql = " SELECT Ordens_Fretes.oid_Ordem_Frete " +
                  " FROM   Ordens_Fretes, Ordens_Manifestos " +
                  " WHERE  Ordens_Fretes.oid_Ordem_Frete = Ordens_Manifestos.oid_Ordem_Frete " +
                  " AND    Ordens_Manifestos.oid_Manifesto = '" + ed.getOID_Manifesto () + "'";
                  resCTRC = this.executasql.executarConsulta(sql);
	              if (resCTRC.next()){
	                edVolta.setDM_Lancado_Ordem_Frete("S");
	              }
              }


          }
      } catch (SQLException e) {
          throw new Excecoes(e.getMessage(), e, getClass().getName(), "getByRecord(ManifestoED ed)");
      }
      return edVolta;
  }

  public byte[] imprime_Manifesto(ManifestoED filtro)
  throws Excecoes {

// System.out.println("imprime_Manifesto 1");


     this.rateio_Frete_Carreteiro(filtro);
     this.ordena_Roteiro(filtro);

     String sql =
         "SELECT *, " +
      " Manifestos.oid_Pessoa as oid_Pessoa_Motorista, "+
      " Pessoa_Proprietario.NM_Razao_Social as NM_Proprietario, "+
      " Pessoa_Motorista.NM_Razao_Social as NM_Motorista, "+
      " Veiculos.NR_Placa, "+
      " Cidade_Manifesto.NM_Cidade as NM_Cidade_Manifesto"+
      " FROM Manifestos, Veiculos, Cidades Cidade_Manifesto, Pessoas Pessoa_Proprietario, Pessoas Pessoa_Motorista "+
      " WHERE  Manifestos.OID_Veiculo = Veiculos.OID_Veiculo "+
      " AND Manifestos.OID_Cidade = Cidade_Manifesto.OID_Cidade "+
      " AND Manifestos.OID_Pessoa = Pessoa_Motorista.Oid_Pessoa "+
      " AND Veiculos.OID_Pessoa_Proprietario = Pessoa_Proprietario.oid_Pessoa "+
      " AND Manifestos.oid_manifesto = '" + filtro.getOID_Manifesto() + "'";

     //System.out.println(" ################################# SELECT IMPRIME MANIFESTO = " + sql);

	      ResultSet res = executasql.executarConsulta(sql);
	          return new ManifestoRL().imprime_Manifesto(filtro, res, executasql);
  }

  public void relComissaoManifesto(ManifestoED ed)
  throws Excecoes {
      String sql =
          " select MAN.oid_Manifesto " +
          "       ,MAN.NR_Manifesto " +
          "       ,MAN.DT_Emissao " +
          "       ,sum(CON.NR_Peso) as NR_Total_Peso_CTRC " +
          "       ,sum(CON.VL_Total_Frete) as VL_Total_Frete_CTRC " +
          "       ,MAN.VL_Frete_Carreteiro " +
          "       ,MOT.PE_Comissao as PE_Comissao_Motorista " +
          "       ,MAN.oid_Pessoa " +
          "       ,PES.NR_CNPJ_CPF " +
          "       ,PES.NM_Razao_Social " +
          "       ,PES.NM_Fantasia " +
          " from Manifestos MAN " +
          "     ,Unidades UNI " +
          "     ,Viagens VIA " +
          "     ,Conhecimentos CON " +
          "     ,Motoristas MOT " +
          "     ,Pessoas PES " +
          " where MAN.oid_Unidade = UNI.oid_Unidade " +
          "   and MAN.oid_Manifesto = VIA.oid_Manifesto " +
          "   and VIA.oid_Conhecimento = CON.oid_Conhecimento " +
          "   and MAN.oid_Pessoa = MOT.oid_Pessoa " +
          "   and MOT.oid_Pessoa = PES.oid_Pessoa ";
      if (ed.getOid_Empresa() > 0) {
          sql += " and Unidades.oid_Empresa = " + ed.getOid_Empresa();
      }
      if (ed.getOID_Unidade() > 0) {
          sql += " and MAN.oid_Unidade = " + ed.getOID_Unidade();
      }
      if (util.doValida(ed.getOID_Manifesto())) {
          sql += " and MAN.oid_Manifesto = " + util.getSQLString(ed.getOID_Manifesto());
      }
      if (util.doValida(ed.getOID_Pessoa())) {
          sql += " and MAN.oid_Pessoa = " + util.getSQLString(ed.getOID_Pessoa());
      }
      if (util.doValida(ed.getDT_Emissao_Inicial())) {
          sql += " and MAN.DT_Emissao >= " + util.getSQLDate(ed.getDT_Emissao_Inicial());
      }
      if (util.doValida(ed.getDT_Emissao_Final())) {
          sql += " and MAN.DT_Emissao <= " + util.getSQLDate(ed.getDT_Emissao_Final());
      }
      sql +=
          " group by MAN.oid_Manifesto " +
          "         ,MAN.NR_Manifesto " +
          "         ,MAN.DT_Emissao " +
          "         ,MAN.VL_Frete_Carreteiro " +
          "         ,MOT.PE_Comissao " +
          "         ,MAN.oid_Pessoa " +
          "         ,PES.NR_CNPJ_CPF " +
          "         ,PES.NM_Razao_Social " +
          "         ,PES.NM_Fantasia " +
          " order by MAN.oid_Pessoa " +
          "         ,MAN.DT_Emissao ";
      // System.out.println(sql);
      ResultSet res = executasql.executarConsulta(sql);
      try {
          try {
              List lista = new ArrayList();
              while (res.next()) {
                  ManifestoED edVolta = new ManifestoED();
                  edVolta.setOID_Manifesto(res.getString("oid_Manifesto"));
                  edVolta.setNR_Manifesto(res.getLong("NR_Manifesto"));
                  edVolta.setDT_Emissao(FormataData.formataDataBT(res.getDate("DT_Emissao")));
                  edVolta.setNR_Total_Peso_CTRC(res.getDouble("NR_Total_Peso_CTRC"));
                  edVolta.setVL_Total_Frete_CTRC(res.getDouble("VL_Total_Frete_CTRC"));
                  edVolta.setVL_Frete_Carreteiro(res.getDouble("VL_Frete_Carreteiro"));
                  edVolta.setPE_Comissao_Motorista(res.getDouble("PE_Comissao_Motorista"));
                  edVolta.setOID_Pessoa(res.getString("oid_Pessoa"));
                  edVolta.setNR_CNPJ_CPF_Pessoa(res.getString("NR_CNPJ_CPF"));
                  edVolta.setNM_Razao_Social_Pessoa(res.getString("NM_Razao_Social"));
                  edVolta.setNM_Fantasia_Pessoa(res.getString("NM_Fantasia"));
                  edVolta.setVL_Comissao_Motorista(edVolta.getVL_Frete_Carreteiro() * edVolta.getPE_Comissao_Motorista() / 100);
                  lista.add(edVolta);
              }
              ed.setLista(lista);
              new JasperRL(ed).geraRelatorio();
        } catch (SQLException e) {
            throw new Excecoes(e.getMessage(), e, getClass().getName(), "relComissaoManifesto(ManifestoED ed)");
        }
      } finally {
          util.closeResultset(res);
      }
  }

  public byte[] gera_Relatorio_Manifesto(ManifestoED ed)
  throws Excecoes{
      String sql =
          " SELECT Manifestos.OID_Manifesto, " +
          "        Manifestos.DM_Expresso, " +
          "        Manifestos.CD_Rota_Entrega, " +
          "        Manifestos.DM_Tipo_Manifesto, " +
          "        Manifestos.DT_Emissao, " +
          "        Manifestos.DT_Previsao_Chegada, " +
          "        Manifestos.HR_Previsao_Chegada, " +
          "        Manifestos.NR_Manifesto, " +
          "        Pessoa_Proprietario.NM_Razao_Social as NM_Proprietario, " +
          "        Pessoa_Motorista.NM_Razao_Social as NM_Motorista, " +
          "        Veiculos.NR_Placa,  Veiculos.NR_Rastreador, " +
          "        Cidade_Manifesto.NM_Cidade as NM_Cidade_Manifesto, " +
          "        Cidade_Unidade.NM_Cidade as NM_Cidade_Unidade " +
          " from Manifestos, " +
          "      Unidades, " +
          "      Veiculos, " +
          "      Cidades Cidade_Manifesto, " +
          "      Pessoas Pessoa_Proprietario, " +
          "      Pessoas Pessoa_Motorista, " +
          "      Pessoas Pessoa_Unidade, " +
          "      Cidades Cidade_Unidade " +
          " WHERE Manifestos.OID_Veiculo = Veiculos.OID_Veiculo " +
          "   AND Manifestos.OID_Unidade = Unidades.OID_Unidade " +
          "   AND Manifestos.OID_Cidade = Cidade_Manifesto.OID_Cidade " +
          "   AND Manifestos.OID_Pessoa = Pessoa_Motorista.Oid_Pessoa " +
          "   AND Veiculos.OID_Pessoa_Proprietario = Pessoa_Proprietario.oid_Pessoa " +
          "   AND Unidades.OID_Pessoa = Pessoa_Unidade.OID_Pessoa " +
          "   AND Pessoa_Unidade.OID_Cidade = Cidade_Unidade.OID_Cidade ";

//          "   AND (Manifestos.OID_Pessoa_Entregadora is null or Manifestos.OID_Pessoa_Entregadora = '' ) ";

      if (ed.getNR_Manifesto() > 0) {
          sql += " and Manifestos.NR_Manifesto = " + ed.getNR_Manifesto();
      }
      if (ed.getOID_Cidade() > 0) {
          sql += " and Manifestos.OID_Cidade = " + ed.getOID_Cidade();
      }
      if (ed.getOID_Empresa() > 0){
          sql += " and Unidades.OID_Empresa = " + ed.getOID_Empresa();
      }
      if (ed.getOID_Unidade() > 0) {
          sql += " and Manifestos.OID_Unidade = " + ed.getOID_Unidade();
      }
      if (util.doValida(ed.getDT_Emissao_Inicial())){
          sql += " and Manifestos.DT_Emissao >= '" + ed.getDT_Emissao_Inicial() + "'";
      }
      if (util.doValida(ed.getCD_Rota_Entrega())) {
          sql += " and Manifestos.CD_Rota_Entrega = '" + ed.getCD_Rota_Entrega() + "'";
      }

      if (util.doValida(ed.getDT_Emissao_Final())) {
          sql += " and Manifestos.DT_Emissao <= '" + ed.getDT_Emissao_Final() + "'";
      }
      if ("R".equals(ed.getDM_Tipo_Veiculo())) {
          sql += " and Veiculos.NR_Rastreador is not null ";
      }
      if ("N".equals(ed.getDM_Tipo_Veiculo())) {
          sql += " and Veiculos.NR_Rastreador is null ";
      }
      if (util.doValida(ed.getOID_Pessoa())) {
          sql += " and Manifestos.OID_Pessoa = '" + ed.getOID_Pessoa() + "' ";
      }
      if (util.doValida(ed.getOID_Veiculo())) {
          sql += " and Manifestos.OID_Veiculo = '" + ed.getOID_Veiculo() + "' ";
      }
      if (util.doValida(ed.getDM_Tipo_ICMS())) {
          if ("D".equals(ed.getDM_Tipo_ICMS())) { //ICMS Destacado
              sql += " and (select count(*) " +
                        "       from viagens, conhecimentos " +
                        "       where manifestos.oid_manifesto = viagens.oid_manifesto " +
                        "   and viagens.oid_conhecimento = conhecimentos.oid_conhecimento " +
                        "   and Conhecimentos.VL_ICMS > 0) > 0";
          }
      }
      if (util.doValida(ed.getOID_Pessoa_Destinatario())) {
              sql += " and (select count(*) " +
                        "       from viagens, conhecimentos " +
                        "       where manifestos.oid_manifesto = viagens.oid_manifesto " +
                        "   and viagens.oid_conhecimento = conhecimentos.oid_conhecimento " +
                        "   and Conhecimentos.oid_Pessoa_Destinatario='" +ed.getOID_Pessoa_Destinatario()+ "') > 0";
      }


      sql += " Order by Manifestos.Dt_Emissao, Manifestos.NR_Manifesto ";

      ResultSet res = this.executasql.executarConsulta(sql.toString());
      return new ManifestoRL().gera_Relatorio_Manifesto(res, ed);
  }


  public ManifestoED rateio_Frete_Carreteiro (ManifestoED ed) throws Excecoes {

    String sql = null;
    ResultSet rs = null;
    double NR_Peso_Total = 0;
    double VL_Movimento = 0 , VL_Frete_Carreteiro = 0;
    double NR_Peso = 0 , NR_Peso_Cubado = 0;
    int x = 0;

    try {

      sql = " SELECT Manifestos.VL_Frete_Carreteiro ";
      sql += " FROM  Manifestos ";
      sql += " WHERE oid_Manifesto = '" + ed.getOID_Manifesto () + "'";

      // System.out.println ("sql: " + sql);

      rs = this.executasql.executarConsulta (sql);
      if (rs.next ()) {
        VL_Frete_Carreteiro = rs.getDouble ("VL_Frete_Carreteiro");

        if (VL_Frete_Carreteiro > 0) {

          sql = " SELECT Movimentos_Conhecimentos.oid_Conhecimento, Movimentos_Conhecimentos.oid_Movimento_Conhecimento " +
              " FROM   Movimentos_Conhecimentos, Viagens " +
              " WHERE  Movimentos_Conhecimentos.oid_Conhecimento = Viagens.oid_Conhecimento " +
              " AND    Viagens.oid_Manifesto = '" + ed.getOID_Manifesto () + "'" +
              " AND    Movimentos_Conhecimentos.oid_Tipo_Movimento = " + parametro_FixoED.getOID_Tipo_Movimento_Custo_Ordem_Frete ();

          // System.out.println ("sql: " + sql);

          rs = this.executasql.executarConsulta (sql);
          while (rs.next ()) {
            // System.out.println ("Cancela rateio_Frete_Carreteiro     cto->>" + rs.getString ("OID_Conhecimento"));
            // System.out.println ("Cancela rateio_Frete_Carreteiro mov cto->>" + rs.getString ("oid_Movimento_Conhecimento"));
            Movimento_ConhecimentoRN Movimento_Conhecimentorn = new Movimento_ConhecimentoRN ();
            Movimento_ConhecimentoED edMovimento_Conhecimento = new Movimento_ConhecimentoED ();
            edMovimento_Conhecimento.setOID_Conhecimento (rs.getString ("OID_Conhecimento"));
            edMovimento_Conhecimento.setOID_Movimento_Conhecimento (rs.getString ("oid_Movimento_Conhecimento"));
            Movimento_Conhecimentorn.deleta (edMovimento_Conhecimento);
          }

          sql = " SELECT *, Viagens.DM_Tipo_Viagem, Conhecimentos.OID_Conhecimento, Conhecimentos.NR_Conhecimento, Conhecimentos.NR_Peso as NR_Peso_CTRC  , Conhecimentos.NR_Peso_Cubado as NR_Peso_Cubado_CTRC";
          sql += " FROM Viagens, Conhecimentos ";
          sql += " WHERE Viagens.OID_Conhecimento = Conhecimentos.OID_Conhecimento ";
          sql += " AND   Viagens.oid_Manifesto = '" + ed.getOID_Manifesto () + "'";

          // System.out.println ("sql: " + sql);

          rs = this.executasql.executarConsulta (sql);
          String DM_Tipo_Viagem = "";
          while (rs.next () && x < 9999) {
            x++;

            DM_Tipo_Viagem = rs.getString ("DM_Tipo_Viagem");

            NR_Peso = rs.getDouble ("NR_Peso_CTRC");
            NR_Peso_Cubado = rs.getDouble ("NR_Peso_Cubado_CTRC");
            if (NR_Peso_Cubado > NR_Peso) {
              NR_Peso = NR_Peso_Cubado;
            }

            NR_Peso_Total = NR_Peso_Total + NR_Peso;
            // System.out.println ("1 CTO : " + rs.getString ("NR_Conhecimento") + " Peso : " + rs.getString ("NR_Peso_CTRC") + " Peso Calc:" + NR_Peso);

          }

          // System.out.println ("VL_Ordem_Frete: " + VL_Frete_Carreteiro);
          // System.out.println ("NR_Peso_Total: " + NR_Peso_Total);

          if (NR_Peso_Total > 0 && VL_Frete_Carreteiro > 0) {
            sql = " SELECT Conhecimentos.OID_Conhecimento, Conhecimentos.NR_Conhecimento, Conhecimentos.NR_Peso as NR_Peso_CTRC , Conhecimentos.NR_Peso_Cubado as NR_Peso_Cubado_CTRC, Viagens.DM_Tipo_Viagem ";
            sql += " FROM Viagens, Conhecimentos ";
            sql += " WHERE Viagens.OID_Conhecimento = Conhecimentos.OID_Conhecimento ";
            sql += " AND   Viagens.oid_Manifesto = '" + ed.getOID_Manifesto () + "'";

            rs = this.executasql.executarConsulta (sql);
            while (rs.next ()) {

              NR_Peso = rs.getDouble ("NR_Peso_CTRC");

              NR_Peso_Cubado = rs.getDouble ("NR_Peso_Cubado_CTRC");
              if (NR_Peso_Cubado > NR_Peso) {
                NR_Peso = NR_Peso_Cubado;
              }

              VL_Movimento = (VL_Frete_Carreteiro / NR_Peso_Total * NR_Peso);
              if (VL_Movimento > VL_Frete_Carreteiro) VL_Movimento = VL_Frete_Carreteiro;

              // System.out.println ("2 CTO --: " + rs.getString ("NR_Conhecimento") + " Peso : " + rs.getString ("NR_Peso_CTRC") + " Peso Cub : " + rs.getString ("NR_Peso_Cubado_CTRC") + " Peso Calc:" + NR_Peso + " VL_Movimento:" + VL_Movimento);

              Movimento_ConhecimentoRN Movimento_Conhecimentorn = new Movimento_ConhecimentoRN ();
              Movimento_ConhecimentoED edMovimento_Conhecimento = new Movimento_ConhecimentoED ();

              //SETA INICIAL custo transf
              edMovimento_Conhecimento.setOID_Tipo_Movimento (new Long (parametro_FixoED.getOID_Tipo_Movimento_Custo_Ordem_Frete ()).longValue ());

              DM_Tipo_Viagem = rs.getString ("DM_Tipo_Viagem");

              // System.out.println ("3 CTO --: " + rs.getString ("NR_Conhecimento") + " Peso : " + rs.getString ("NR_Peso_CTRC") + " Peso Cub : " + rs.getString ("NR_Peso_Cubado_CTRC") + " Peso Calc:" + NR_Peso + " VL_Movimento:" + VL_Movimento);

              edMovimento_Conhecimento.setVL_Movimento (new Double (VL_Movimento).doubleValue ());
              edMovimento_Conhecimento.setDT_Movimento_Conhecimento (Data.getDataDMY ());
              edMovimento_Conhecimento.setHR_Movimento_Conhecimento (Data.getHoraHM ());

              edMovimento_Conhecimento.setOID_Conhecimento (rs.getString ("OID_Conhecimento"));

              // System.out.println ("4 CTO --: " + rs.getString ("NR_Conhecimento") + " Peso : " + rs.getString ("NR_Peso_CTRC") + " Peso Cub : " + rs.getString ("NR_Peso_Cubado_CTRC") + " Peso Calc:" + NR_Peso + " VL_Movimento:" + VL_Movimento);

              edMovimento_Conhecimento.setDM_Tipo_Movimento ("D");
              edMovimento_Conhecimento.setDM_Lancado_Gerado ("R");
              edMovimento_Conhecimento.setNM_Pessoa_Entrega ("");

              // System.out.println ("99 CTO : " + rs.getString ("NR_Conhecimento") + " Peso : " + rs.getString ("NR_Peso_CTRC") + " Peso Cub : " + rs.getString ("NR_Peso_Cubado_CTRC") + " Peso Calc:" + NR_Peso + " VL_Movimento:" + VL_Movimento);

              Movimento_Conhecimentorn.inclui (edMovimento_Conhecimento);

            }
          }
        }
      }
    }
    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro rateio_Frete_Carreteiro");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("rateio_Frete_Carreteiro(Ordem_FreteED ed)");
    }
    return ed;
  }

  public ManifestoED ordena_Roteiro (ManifestoED ed) throws Excecoes {

    String sql = null;
    ResultSet rs = null;
    int qt_cto = 0;

    try {

      sql = " SELECT Viagens.oid_Viagem, Manifestos.CD_Rota_Entrega, Rotas_Entregas.NR_Ordem , Conhecimentos.NR_Conhecimento " +
          "  FROM  Manifestos, Rotas_Entregas, Viagens , Conhecimentos " +
          "  WHERE Manifestos.oid_Manifesto = Viagens.oid_Manifesto " +
          "  AND   Conhecimentos.oid_Conhecimento = Viagens.oid_Conhecimento  " +
          "  AND   Conhecimentos.oid_Pessoa_Destinatario = Rotas_Entregas.oid_Pessoa " +
          "  AND   Manifestos.CD_Rota_Entrega = Rotas_Entregas.CD_Rota_Entrega  " +
          "  AND   Manifestos.oid_Manifesto = '" + ed.getOID_Manifesto () + "'" +
          "  ORDER BY Rotas_Entregas.NR_Ordem ";

      // System.out.println ("sql: " + sql);

      rs = this.executasql.executarConsulta (sql);

      while (rs.next ()) {
        qt_cto++;
        if (qt_cto == 1) {
          sql = " UPDATE Viagens SET NR_Sequencia = 0 " +
                " WHERE  Viagens.oid_Manifesto = '" + ed.getOID_Manifesto () + "'";
          // System.out.println ("man bd >>>>>>>> UPDATE 999" + sql);
          executasql.executarUpdate (sql);
        }

        // System.out.println ("ordena_Roteiro Cto->>" + rs.getLong("NR_Conhecimento") );

        sql = " UPDATE Viagens SET NR_Sequencia =" + rs.getDouble("NR_Ordem") +
              " WHERE  Viagens.oid_Viagem = '" + rs.getString ("oid_Viagem") + "'";
        // System.out.println ("man bd >>>>>>>> UPDATE 999" + sql);
        executasql.executarUpdate (sql);
      }
    }
    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro rateio_Frete_Carreteiro");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("rateio_Frete_Carreteiro(Ordem_FreteED ed)");
    }
    return ed;
  }


  public void rel_Resumo_Entrega_Rota (ManifestoED ed , HttpServletResponse response) throws Excecoes {

	    String sql = null;
	    ResultSet res = null;
	    int counter = 0;
	    ArrayList lista = new ArrayList ();

	    try {
	      sql = " SELECT conhecimentos.cd_rota_entrega, conhecimentos.oid_pessoa_destinatario, conhecimentos.dt_emissao, " +
	          " sum(conhecimentos.nr_peso) as nr_peso, sum(conhecimentos.nr_volumes) as nr_volumes, " +
	          " sum(conhecimentos.vl_nota_fiscal) as vl_nota_fiscal " +
	          " FROM  conhecimentos, viagens, manifestos " +
	          " WHERE conhecimentos.oid_conhecimento = viagens.oid_conhecimento " +
	          " AND   viagens.oid_manifesto = manifestos.oid_manifesto " +
//	          " AND   conhecimentos_notas_fiscais.oid_conhecimento = conhecimentos.oid_conhecimento" +
	          " AND   Conhecimentos.DM_Impresso    = 'S'  " +
	          " AND Conhecimentos.VL_Total_Frete >  0   " +
	          " AND Conhecimentos.DM_Situacao   <> 'C'";

	      if (JavaUtil.doValida (String.valueOf (ed.getOID_Unidade ()))) {
	        sql += " AND   conhecimentos.oid_unidade = " + ed.getOID_Unidade ();
	        UnidadeBean un = UnidadeBean.getByOID_Unidade (new Long (ed.getOID_Unidade ()).intValue ());
	        ed.setNM_Fantasia_Pessoa (un.getCD_Unidade () + " - " + un.getNM_Fantasia ());
	      }
	      else ed.setNM_Fantasia_Pessoa (" - T O D A S - ");

	      if (JavaUtil.doValida (ed.getOID_Pessoa())) {
		        sql += " AND   conhecimentos.oid_Pessoa = '" + ed.getOID_Pessoa () + "'";
		        PessoaBean pessoa = PessoaBean.getByOID(ed.getOID_Pessoa());
		        ed.setNM_Fantasia_Pessoa(ed.getNM_Fantasia_Pessoa()+"\nREM.: " + pessoa.getNM_Razao_Social());
		  }

	      if (JavaUtil.doValida (ed.getDT_Emissao_Inicial ())) {
	        sql += " AND   conhecimentos.dt_emissao >= '" + ed.getDT_Emissao_Inicial () + "'";
	      }
	      if (JavaUtil.doValida (ed.getDT_Emissao_Final ())) {
	        sql += " AND   conhecimentos.dt_emissao <= '" + ed.getDT_Emissao_Final () + "'";
	      }
	      if (JavaUtil.doValida (ed.getCD_Rota_Entrega ())) {
	        sql += " AND   conhecimentos.cd_rota_Entrega = '" + ed.getCD_Rota_Entrega () + "'";
	      }
	      sql += " GROUP BY conhecimentos.cd_rota_entrega, conhecimentos.oid_pessoa_destinatario, conhecimentos.dt_emissao " +
	          " ORDER BY conhecimentos.cd_rota_entrega, conhecimentos.oid_pessoa_destinatario, conhecimentos.dt_emissao ";
//System.out.println ("resumo ROTA: " +sql);

	      res = this.executasql.executarConsulta (sql);

      String dt_aux = "";
      String rota_aux = "";
      String pessoa_aux = "";
      double qt_nf = 0;
      double qt_nf_total = 0;

      while (res.next ()) {
//			  if(!rota_aux.equals(res.getString("CD_Rota_Entrega"))){
//				  counter = 1;
//			  }
        ManifestoED edVolta = new ManifestoED ();

        edVolta.setNM_Fantasia_Pessoa (ed.getNM_Fantasia_Pessoa ());
        edVolta.setDT_Emissao_Inicial (ed.getDT_Emissao_Inicial ());
        edVolta.setDT_Emissao_Final (ed.getDT_Emissao_Final ());

        Rota_EntregaED rotaED = new Rota_EntregaED ();
        rotaED.setCD_Rota_Entrega (res.getString ("CD_Rota_Entrega"));
        rotaED = new Rota_EntregaBD (this.executasql).getByRecord (rotaED);
        edVolta.setCD_Rota_Entrega (res.getString ("CD_Rota_Entrega") + " - " + rotaED.getNM_Rota_Entrega ());
        edVolta.setNr_peso (res.getDouble ("NR_Peso"));
        edVolta.setNr_volumes (res.getDouble ("NR_Volumes"));
        edVolta.setVl_nota_fiscal (res.getDouble ("VL_Nota_Fiscal"));

        if(!rota_aux.equals(res.getString ("CD_Rota_Entrega"))){
        	String aux = "SELECT count(conhecimentos_notas_fiscais.oid_nota_fiscal) as qtde_nf " +
		     "FROM conhecimentos_notas_fiscais , conhecimentos " +
		     "WHERE conhecimentos_notas_fiscais.oid_conhecimento = conhecimentos.oid_conhecimento " +
		     " AND Conhecimentos.DM_Impresso = 'S'  " +
		     " AND Conhecimentos.VL_Total_Frete >  0   " +
		     " AND Conhecimentos.DM_Situacao <> 'C' " +
		     " AND conhecimentos.cd_rota_Entrega = '"+res.getString("CD_Rota_Entrega")+"'";
			//aux += " AND   conhecimentos.dt_emissao = '" + res.getString("dt_emissao") + "'";
			//aux += " AND   conhecimentos.oid_pessoa_destinatario = '" + res.getString("oid_pessoa_destinatario") + "'";
			if (JavaUtil.doValida (String.valueOf (ed.getOID_Unidade ()))) {
				aux += " AND   conhecimentos.oid_unidade = " + ed.getOID_Unidade ();
			}
			if (JavaUtil.doValida (ed.getOID_Pessoa())) {
		        aux += " AND   conhecimentos.oid_Pessoa = '" + ed.getOID_Pessoa () + "'";
			}
			if (JavaUtil.doValida (ed.getDT_Emissao_Inicial ())) {
			   aux += " AND   conhecimentos.dt_emissao >= '" + ed.getDT_Emissao_Inicial () + "'";
			 }
			 if (JavaUtil.doValida (ed.getDT_Emissao_Final ())) {
			   aux += " AND   conhecimentos.dt_emissao <= '" + ed.getDT_Emissao_Final () + "'";
			 }

//			System.out.println ("qtde NF: " +aux);
			ResultSet rs = this.executasql.executarConsulta(aux);

			if(rs.next()){
				qt_nf = rs.getDouble("qtde_nf");
				qt_nf_total += qt_nf;
			}
        }
//        System.out.println ("ROTA:"+res.getString("CD_Rota_Entrega")+"| qtde NF: " +qt_nf);

        edVolta.setNr_quantidade_nota_fiscal(qt_nf);

        edVolta.setNr_quantidade_visitas (1);
        lista.add (edVolta);
//			  dt_aux = res.getString("dt_emissao");
			  rota_aux = res.getString("CD_Rota_Entrega");
//			  pessoa_aux = res.getString("oid_pessoa_destinatario");
//			  counter++;

      }
      ed.setVl_nota_fiscal(qt_nf_total);
      new ManifestoRL ().rel_Resumo_Entrega_Rota (lista , ed , response);

    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro rel_Resumo_Entrega_Rota");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("rel_Resumo_Entrega_Rota(Ordem_FreteED ed)");
    }
  }

}
