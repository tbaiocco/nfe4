package com.master.bd;

import java.sql.*;
import java.util.ArrayList;

import com.master.ed.*;
import com.master.rl.*;
import com.master.root.*;
import com.master.util.*;
import com.master.util.bd.*;
import com.master.util.ed.*;

public class SeguroBD extends BancoUtil {

    Parametro_FixoED parametro_FixoED = new Parametro_FixoED();
    FormataDataBean dataFormatada = new FormataDataBean();


    private ExecutaSQL executasql;

    public SeguroBD(ExecutaSQL sql) {
        this.executasql = sql;
    }


    public SeguroED calcula_Seguro (String oid_Conhecimento, double VL_Nota_Fiscal) throws Exception {

    	SeguroED edVolta = new SeguroED ();

    	String sql = null;
        double vl_aliquota_rctrc = 0;
        double vl_aliquota_rctr_dc = 0;
        double vl_aliquota_rctr_vi = 0;
        double vl_aliquota_rcta = 0;

        double pe_aliquota_rctrc = 0;
        double pe_aliquota_rctr_dc = 0;
        double pe_aliquota_rctr_vi = 0;
        double pe_aliquota_rcta = 0;

        ResultSet resCto = null;
        ResultSet resCli = null;
        ResultSet resTaxa = null;
        ResultSet resCid = null;

        try {



        	sql =
                " SELECT Conhecimentos.oid_Cidade, Conhecimentos.oid_Cidade_Destino, Conhecimentos.VL_Nota_Fiscal_Seguro, Conhecimentos.NR_Conhecimento, Conhecimentos.VL_Nota_Fiscal, Conhecimentos.oid_Taxa, Conhecimentos.oid_Pessoa, Modal.DM_Tipo_Transporte,  " +
                "        Conhecimentos.DM_Calculo_Seguro, Conhecimentos.vl_seguro_rctrc, Conhecimentos.vl_seguro_rctr_dc, Conhecimentos.vl_seguro_rctr_vi, Conhecimentos.vl_seguro_rcta " +
                " FROM   Conhecimentos, Modal " +
                " WHERE  Conhecimentos.oid_Modal = Modal.oid_Modal " +
                " AND    Conhecimentos.DM_Tipo_Documento ='C' " +
                " AND    Conhecimentos.oid_Conhecimento = '" + oid_Conhecimento  + "'";

            resCto = this.executasql.executarConsulta(sql);
			if (resCto.next()){
				vl_aliquota_rctrc=resCto.getDouble("vl_seguro_rctrc");
				vl_aliquota_rctr_dc=resCto.getDouble("vl_seguro_rctr_dc");
				vl_aliquota_rctr_vi=resCto.getDouble("vl_seguro_rctr_vi");
				vl_aliquota_rcta=resCto.getDouble("vl_seguro_rcta");

				sql =" SELECT Clientes.dm_rctr_dc, Clientes.dm_rctr_vi , Clientes.dm_rcta, Clientes.dm_rctrc, Clientes.DM_Isencao_Seguro " +
					 " FROM Clientes " +
					 " WHERE  Clientes.DM_Isencao_Seguro ='N' " +
					 " AND    Clientes.oid_Cliente ='" + resCto.getString("oid_Pessoa") +"'";
				resCli = this.executasql.executarConsulta(sql);
				if(resCli.next()){
					if (VL_Nota_Fiscal==0) {
				        VL_Nota_Fiscal = resCto.getDouble("VL_Nota_Fiscal");
				        if (resCto.getDouble("VL_Nota_Fiscal_Seguro")>0) {
				        	VL_Nota_Fiscal = resCto.getDouble("VL_Nota_Fiscal_Seguro");
				        }
					}
			        sql = " SELECT * FROM Taxas WHERE oid_Taxa ="  + resCto.getLong("oid_Taxa");
			        resTaxa = this.executasql.executarConsulta(sql);
					if(resTaxa.next()){
				          if ("A".equals (resCto.getString("DM_Tipo_Transporte"))) { //aereo
					            pe_aliquota_rcta = resTaxa.getDouble("pe_aliquota_rcta");
					            if (VL_Nota_Fiscal > resTaxa.getDouble("VL_Cobertura_Aereo") && resTaxa.getDouble("VL_Cobertura_Aereo")>0) {
						              pe_aliquota_rctr_dc = pe_aliquota_rctr_dc * 2;
						        }
					            if (pe_aliquota_rcta==0) pe_aliquota_rcta=0.14;
					          }
					          else {
					            pe_aliquota_rctrc = resTaxa.getDouble("pe_aliquota_rctrc");
					            pe_aliquota_rctr_dc = resTaxa.getDouble("pe_aliquota_rctr_dc");
					            pe_aliquota_rcta = resTaxa.getDouble("pe_aliquota_rcta");
					            if (VL_Nota_Fiscal > resTaxa.getDouble("VL_Cobertura_Rodoviario") && resTaxa.getDouble("VL_Cobertura_Rodoviario")>0) {
					              pe_aliquota_rctr_dc = pe_aliquota_rctr_dc * 2;
					            }

					            long oid_regiao_origem=0;
						        sql = " SELECT oid_regiao_estado FROM Cidades WHERE oid_Cidade ="  + resCto.getLong("oid_Cidade");
						        resCid = this.executasql.executarConsulta(sql);
								if(resCid.next()){
									oid_regiao_origem=resCid.getLong("oid_regiao_estado");
							        sql = " SELECT oid_regiao_estado FROM Cidades WHERE oid_Cidade =" + resCto.getLong("oid_Cidade_Destino");
							        resCid = this.executasql.executarConsulta(sql);
									if(resCid.next()){
							            if (resCid.getLong("oid_regiao_estado") == oid_regiao_origem) {
								              pe_aliquota_rctrc = resTaxa.getDouble("PE_Aliquota_Metropolitana"); //taxa fixa p/reg.
								              pe_aliquota_rctr_dc = 0;
								        }
									}
								}
					          }
					          if (!"S".equals (resCli.getString("dm_rctrc"))) {
					            pe_aliquota_rctrc = 0;
					          }

					          if (!"S".equals (resCli.getString("dm_rctr_dc"))) {
					            pe_aliquota_rctr_dc = 0;
					          }
					          if (!"S".equals (resCli.getString("dm_rcta"))) {
					            pe_aliquota_rcta = 0;
					          }
					          if (!"S".equals (resCli.getString("dm_rctr_vi"))) {
					            pe_aliquota_rctr_vi = 0;
					          }

					          vl_aliquota_rctrc = VL_Nota_Fiscal * pe_aliquota_rctrc / 100;
					          vl_aliquota_rctr_dc = VL_Nota_Fiscal * pe_aliquota_rctr_dc / 100;
					          vl_aliquota_rctr_vi = VL_Nota_Fiscal * pe_aliquota_rctr_vi / 100;
					          vl_aliquota_rcta = VL_Nota_Fiscal * pe_aliquota_rcta / 100;


					}
				}

				//GRAVA SEGURO TAB CTO
		        sql =" UPDATE Conhecimentos SET DM_Calculo_Seguro='S', vl_seguro_rctrc=" + vl_aliquota_rctrc + ", vl_seguro_rctr_dc=" + vl_aliquota_rctr_dc + ", vl_seguro_rctr_vi=" + vl_aliquota_rctr_vi + ", vl_seguro_rcta=" + vl_aliquota_rcta + " WHERE oid_Conhecimento='" + oid_Conhecimento +"'";

		        executasql.executarUpdate (sql);

			}
			edVolta.setVl_aliquota_rctrc(vl_aliquota_rctrc);
	        edVolta.setVl_aliquota_rctr_dc(vl_aliquota_rctr_dc);
	        edVolta.setVl_aliquota_rctr_vi(vl_aliquota_rctr_vi);
	        edVolta.setVl_aliquota_rcta(vl_aliquota_rcta);
	        edVolta.setPe_aliquota_rcta(pe_aliquota_rcta);
	        edVolta.setPe_aliquota_rctr_dc(pe_aliquota_rctr_dc);
	        edVolta.setPe_aliquota_rctr_vi(pe_aliquota_rctr_vi);
	        edVolta.setPe_aliquota_rctrc(pe_aliquota_rctrc);
	        edVolta.setVL_Seguro1_Total(vl_aliquota_rctrc+vl_aliquota_rctr_dc+vl_aliquota_rctr_vi+vl_aliquota_rcta);
	        edVolta.setVL_Nota_Fiscal(VL_Nota_Fiscal);
		}
		catch (SQLException e) {
          throw new Excecoes (e.getMessage () , e , getClass ().getName () , "calcula_Seguro)");
        }
        return edVolta;
    }

    public SeguroED calcula_Seguro (String oid_Conhecimento) throws Exception {

        return this.calcula_Seguro(oid_Conhecimento, 0);
    }




    public byte[] geraSeguroConhecimento(SeguroED ed)throws Excecoes{

        String sql = null;
        byte[] b = null;

          if ("E".equals(ed.getDM_Situacao())){  //CTOS Embarcados
           sql =  " SELECT Conhecimentos.NR_Conhecimento, "+
               " Conhecimentos.NM_Serie, "+
               " Conhecimentos.DM_Situacao, "+
               " Conhecimentos.Dt_Emissao, "+
               " Conhecimentos.Vl_Nota_Fiscal,  "+
               " Conhecimentos.Vl_Nota_Fiscal_Seguro,  " +
               " Conhecimentos.OID_Veiculo,  "+
               " Conhecimentos.OID_Carreta, " +
               " Pessoas.NM_Razao_Social, "+
               " Clientes.DM_Isencao_Seguro, "+
               " Modal.DM_Tipo_Transporte,  "+
               " Unidades.CD_Unidade,  "+
               " Cidade_Destinatario.oid_Cidade as oid_Cidade_Destino, "+
               " Cidades.oid_Cidade as oid_Cidade_Origem, "+
               " Manifestos.DT_Emissao as DT_Embarque, " +
               " Manifestos.NR_Manifesto,  "+
               " Manifestos.oid_Veiculo as NR_Placa, "+
               " Produtos.NM_Produto " +
               " FROM  Conhecimentos, Unidades, Viagens, Modal, Manifestos, Cidades, Cidades Cidade_Destinatario, Produtos, Pessoas, Clientes "+
               " WHERE Conhecimentos.oid_Cidade = Cidades.oid_cidade "+
               " AND Conhecimentos.OID_Unidade = Unidades.OID_Unidade "+
               " AND Conhecimentos.OID_Pessoa_Pagador = Pessoas.oid_Pessoa "+
               " AND Clientes.OID_Cliente = Pessoas.oid_Pessoa "+
               " AND Conhecimentos.OID_Produto = Produtos.OID_Produto "+
               " AND Conhecimentos.OID_Modal = Modal.OID_Modal "+
               " AND Conhecimentos.oid_Cidade_Destino = Cidade_Destinatario.oid_cidade "+
               " AND Viagens.OID_Manifesto = Manifestos.OID_Manifesto "+
               " AND Conhecimentos.DM_Impresso = 'S' "+
               " AND Conhecimentos.DM_Tipo_Documento = 'C' "+
               " AND Conhecimentos.DM_Situacao <> 'C' "+
               " AND Conhecimentos.VL_Total_Frete > 0" +
               " AND Viagens.OID_Conhecimento = Conhecimentos.OID_Conhecimento ";
          }
          else {
           sql =  " SELECT Conhecimentos.NR_Conhecimento, " +
               " Conhecimentos.oid_Conhecimento, " +
               " Conhecimentos.NM_Serie, " +
               " Conhecimentos.DM_Situacao, " +
               " Conhecimentos.Dt_Emissao, " +
               " Conhecimentos.Vl_Nota_Fiscal,  " +
               " Conhecimentos.Vl_Nota_Fiscal_Seguro,  " +
               " Conhecimentos.OID_Veiculo,  " +
               " Conhecimentos.OID_Carreta, " +
               " Conhecimentos.OID_Pessoa, " +
               " Pessoas.NM_Razao_Social, " +
               " Clientes.DM_Isencao_Seguro, " +
               " Clientes.DM_rctrc, " +
               " Clientes.DM_rctr_vi, " +
               " Clientes.DM_rctr_dc, " +
               " Clientes.DM_rcta, " +
               " Modal.DM_Tipo_Transporte,  " +
               " Unidades.CD_Unidade,  "+
               " Cidade_Destinatario.oid_Cidade as oid_Cidade_Destino, " +
               " Cidades.oid_Cidade as oid_Cidade_Origem, " +
               " Produtos.NM_Produto " +
               " FROM  Conhecimentos, Unidades, Modal,  Cidades, Cidades Cidade_Destinatario, Produtos, Pessoas, Clientes "+
               " WHERE Conhecimentos.oid_Cidade = Cidades.oid_cidade "+
               " AND Conhecimentos.OID_Unidade = Unidades.OID_Unidade "+
               " AND Conhecimentos.OID_Pessoa = Pessoas.oid_Pessoa "+
               " AND Clientes.OID_Cliente = Pessoas.oid_Pessoa "+
               " AND Conhecimentos.OID_Produto = Produtos.OID_Produto "+
               " AND Conhecimentos.OID_Modal = Modal.OID_Modal "+
               " AND Conhecimentos.oid_Cidade_Destino = Cidade_Destinatario.oid_cidade "+
               " AND Conhecimentos.DM_Tipo_Documento = 'C' "+
               " AND Conhecimentos.DM_Impresso = 'S' ";
               if ("C".equals(ed.getDM_Situacao())){
                  sql +=" AND Conhecimentos.DM_Situacao = 'C' ";
               }
               if ("T".equals(ed.getDM_Situacao())){
                  sql += " AND Conhecimentos.DM_Situacao <> 'C' "+
                         " AND Conhecimentos.VL_Total_Frete > 0 " ;
               }
               if ("Y".equals(ed.getDM_Situacao())){
                  sql += " AND Conhecimentos.DM_Situacao <> 'C' "+
                         " AND Conhecimentos.VL_Frete_Valor > 0 " +
                         " AND Conhecimentos.VL_Total_Frete > 0 " ;
               }

          }

            // System.err.println("dt ini ->>>> "  + ed.getDT_Inicial());


          if (String.valueOf(ed.getOid_Unidade()) != null &&
            !String.valueOf(ed.getOid_Unidade()).equals("0")){
            sql += " and Conhecimentos.Oid_Unidade = " + ed.getOid_Unidade();
          }

          if (String.valueOf(ed.getOid_Empresa()) != null &&
            !String.valueOf(ed.getOid_Empresa()).equals("0")){
            sql += " and Unidades.Oid_Empresa = " + ed.getOid_Empresa();
          }


          if (String.valueOf(ed.getDT_Inicial()) != null &&
            !String.valueOf(ed.getDT_Inicial()).equals("") &&
            !String.valueOf(ed.getDT_Inicial()).equals("null")){
            sql += " and Conhecimentos.DT_Emissao >= '" + ed.getDT_Inicial() + "'";
          }
          if (String.valueOf(ed.getDT_Final()) != null &&
          !String.valueOf(ed.getDT_Final()).equals("") &&
          !String.valueOf(ed.getDT_Final()).equals("null")){
            sql += " and Conhecimentos.DT_Emissao <= '" + ed.getDT_Final() + "'";
          }

        if (String.valueOf(ed.getDM_Tipo_Transporte()) != null && !String.valueOf(ed.getDM_Tipo_Transporte()).equals("T")) {
            sql += " AND Modal.DM_Tipo_Transporte = '" + ed.getDM_Tipo_Transporte() + "'";
          }

        if (String.valueOf(ed.getDM_Situacao_Cliente()) != null && !String.valueOf(ed.getDM_Situacao_Cliente()).equals("T")) {
            sql += " AND Clientes.DM_Isencao_Seguro = '" + ed.getDM_Situacao_Cliente() + "'";
          }

        if (ed.getOID_Pessoa() != null && ed.getOID_Pessoa().length()>4) {
              sql += " and Conhecimentos.OID_Pessoa = '" + ed.getOID_Pessoa()+ "'";;
         }

          sql += " order by conhecimentos.dt_emissao, conhecimentos.nr_conhecimento ";

         //System.err.println("->>>> "  + sql);

        try{


          ResultSet res = null;
          res = this.executasql.executarConsulta(sql.toString());

          //// // System.out.println(res);

          SeguroRL SeguroRL = new SeguroRL();
          b =  SeguroRL.geraSeguroConhecimento(res, ed);

        }

        catch (Excecoes e){
          throw e;
        } catch (Exception exc) {
          Excecoes exce = new Excecoes();
          exce.setExc(exc);
          exce.setMensagem("Erro no método listar");
          exce.setClasse(this.getClass().getName());
          exce.setMetodo("geraRelatorio(SeguroED ed)");
        }
        return b;
      }

    public ArrayList geraSeguroConhecimentoJasper(SeguroED ed)throws Excecoes{

        String sql = null;
        ArrayList list = new ArrayList();
        String sqlFiltro="";

        	sqlFiltro  =" AND Conhecimentos.DM_Impresso = 'S' ";
        	sqlFiltro +=" AND Conhecimentos.DM_Tipo_Documento = 'C' ";

        	if ("C".equals(ed.getDM_Situacao())){
        		sqlFiltro +=" AND Conhecimentos.DM_Situacao = 'C' ";
        	}
        	if ("T".equals(ed.getDM_Situacao())){
        		sqlFiltro += " AND Conhecimentos.DM_Situacao <> 'C' "+
                             " AND Conhecimentos.VL_Total_Frete > 0 " ;
        	}
        	if ("Y".equals(ed.getDM_Situacao())){
        		sqlFiltro += " AND Conhecimentos.DM_Situacao <> 'C' "+
                   " AND Conhecimentos.VL_Frete_Valor > 0 " +
                   " AND Conhecimentos.VL_Total_Frete > 0 " ;
        	}
            if ( ed.getOid_Unidade()>0){
            	sqlFiltro += " and Conhecimentos.Oid_Unidade = " + ed.getOid_Unidade();
            }

            if ( ed.getOid_Empresa()>0){
            	sqlFiltro += " and Unidades.Oid_Empresa = " + ed.getOid_Empresa();
            }


            if (Utilitaria.doValida(ed.getDT_Inicial())){
            	sqlFiltro += " and Conhecimentos.DT_Emissao >= '" + ed.getDT_Inicial() + "'";
            }

            if (Utilitaria.doValida(ed.getDT_Final())){
            	sqlFiltro += " and Conhecimentos.DT_Emissao <= '" + ed.getDT_Final() + "'";
            }

            if (String.valueOf(ed.getDM_Tipo_Transporte()) != null && !String.valueOf(ed.getDM_Tipo_Transporte()).equals("T")) {
            	sqlFiltro += " AND Modal.DM_Tipo_Transporte = '" + ed.getDM_Tipo_Transporte() + "'";
            }

            if (String.valueOf(ed.getDM_Situacao_Cliente()) != null && !String.valueOf(ed.getDM_Situacao_Cliente()).equals("T")) {
            	sqlFiltro += " AND Clientes.DM_Isencao_Seguro = '" + ed.getDM_Situacao_Cliente() + "'";
            }

            if (ed.getOID_Pessoa() != null && ed.getOID_Pessoa().length()>4) {
            	sqlFiltro += " and Conhecimentos.OID_Pessoa = '" + ed.getOID_Pessoa()+ "'";;
            }



         if("R1".equals(ed.getDM_Relatorio())){
        	 //SELECT RESUMO POR CIDADE
        	  sql =  " SELECT Cidade_Destino.oid_Cidade as oid_Cidade_Destino," +
        	  		 " 	      Cidade_Origem.oid_Cidade as oid_Cidade_Origem," +
        	  		 "        Cidade_Origem.nm_cidade as nm_Cidade_Origem," +
        	  		 " COUNT (Conhecimentos.oid_conhecimento)"+
		        	 " FROM  Conhecimentos, Unidades, Modal,  Cidades Cidade_Origem, Cidades Cidade_Destino, Produtos, Pessoas, Clientes "+
		             " WHERE Conhecimentos.oid_Cidade = Cidade_Origem.oid_cidade "+
		             " AND Conhecimentos.OID_Unidade = Unidades.OID_Unidade "+
		             " AND Conhecimentos.OID_Pessoa = Pessoas.oid_Pessoa "+
		             " AND Clientes.OID_Cliente = Pessoas.oid_Pessoa "+
		             " AND Conhecimentos.OID_Produto = Produtos.OID_Produto "+
		             " AND Conhecimentos.OID_Modal = Modal.OID_Modal "+
		             " AND Conhecimentos.oid_Cidade_Destino = Cidade_Destino.oid_cidade ";

         }else if("R2".equals(ed.getDM_Relatorio())){
        	 //SELECT RESUMO POR ESTADO


        	  sql =  " SELECT Estado_Origem.oid_Estado  as oid_Estado_Origem, " +
 	  		 		 "        Estado_Origem.CD_Estado   as CD_Estado_Origem, " +
        	  		 "        Estado_Destino.oid_Estado as oid_Estado_Destino, " +
        	  		 "        Estado_Destino.CD_Estado  as CD_Estado_Destino, " +
        	  		 " COUNT (Conhecimentos.oid_conhecimento) "+
		        	 " FROM  Conhecimentos, Unidades, Modal, Produtos, Pessoas, Clientes, "+
		             "  	 Cidades Cidade_Origem, " +
		             "  	 Cidades Cidade_Destino, " +
		             "  	 Regioes_Estados Regiao_Estado_Origem,  " +
		             "  	 Regioes_Estados Regiao_Estado_Destino, " +
		             "  	 Estados Estado_Origem, " +
		             "  	 Estados Estado_Destino " +
		             " WHERE Conhecimentos.OID_Unidade = Unidades.OID_Unidade "+
		             "  AND  Conhecimentos.OID_Pessoa = Pessoas.oid_Pessoa "+
		             "  AND  Clientes.OID_Cliente = Pessoas.oid_Pessoa "+
		             "  AND  Conhecimentos.OID_Produto = Produtos.OID_Produto "+
		             "  AND  Conhecimentos.OID_Modal = Modal.OID_Modal "+
		             "  AND  Conhecimentos.oid_Cidade          = Cidade_Origem.oid_Cidade " +
		             "  AND  Conhecimentos.oid_Cidade_Destino  = Cidade_Destino.oid_Cidade " +
		             "  AND  Cidade_Origem.oid_Regiao_Estado   = Regiao_Estado_Origem.oid_Regiao_Estado " +
		             "  AND  Cidade_Destino.oid_Regiao_Estado  = Regiao_Estado_Destino.oid_Regiao_Estado " +
		             "  AND  Regiao_Estado_Origem.oid_Estado   = Estado_Origem.oid_Estado" +
		             "  AND  Regiao_Estado_Destino.oid_Estado  = Estado_Destino.oid_Estado ";
         }
         else {
           //SELECT ANALITICO
           sql =  " SELECT Conhecimentos.NR_Conhecimento, " +
               " Conhecimentos.oid_Conhecimento, " +
               " Conhecimentos.NM_Serie, " +
               " Conhecimentos.DM_Situacao, " +
               " Conhecimentos.Dt_Emissao, " +
               " Conhecimentos.Vl_Nota_Fiscal,  " +
               " Conhecimentos.Vl_Nota_Fiscal_Seguro,  " +
               " Conhecimentos.OID_Veiculo,  " +
               " Conhecimentos.OID_Carreta, " +
               " Conhecimentos.OID_Pessoa, " +
               " Pessoas.NM_Razao_Social, " +
               " Clientes.DM_Isencao_Seguro, " +
               " Clientes.DM_rctrc, " +
               " Clientes.DM_rctr_vi, " +
               " Clientes.DM_rctr_dc, " +
               " Clientes.DM_rcta, " +
               " Modal.DM_Tipo_Transporte,  " +
               " Unidades.CD_Unidade,  "+
               " Cidade_Destino.oid_Cidade as oid_Cidade_Destino, " +
               " Cidades.oid_Cidade as oid_Cidade_Origem, " +
               " Cidades.nm_cidade as nm_Cidade_Origem, "+
               " Produtos.NM_Produto " +
               " FROM  Conhecimentos, Unidades, Modal,  Cidades, Cidades Cidade_Destino, Produtos, Pessoas, Clientes "+
               " WHERE Conhecimentos.oid_Cidade = Cidades.oid_cidade "+
               " AND Conhecimentos.OID_Unidade = Unidades.OID_Unidade "+
               " AND Conhecimentos.OID_Pessoa = Pessoas.oid_Pessoa "+
               " AND Clientes.OID_Cliente = Pessoas.oid_Pessoa "+
               " AND Conhecimentos.OID_Produto = Produtos.OID_Produto "+
               " AND Conhecimentos.OID_Modal = Modal.OID_Modal "+
               " AND Conhecimentos.oid_Cidade_Destino = Cidade_Destino.oid_cidade ";
        }
   	  	sql+=sqlFiltro;


        if("R1".equals(ed.getDM_Relatorio())){
        	sql+= "group by" +
			 	  " Cidade_Destino.oid_Cidade," +
			 	  " Cidade_Origem.oid_Cidade," +
			 	  " Cidade_Origem.nm_cidade" +
			 	  " ORDER by Cidade_Origem.nm_cidade";
        }
        else if("R2".equals(ed.getDM_Relatorio())){
        	sql+= " group by" +
    			  " Estado_Origem.CD_Estado," +
    			  " Estado_Origem.oid_Estado, " +
    			  " Estado_Destino.CD_Estado, " +
    			  " Estado_Destino.oid_Estado " +
    			  " ORDER by Estado_Origem.CD_Estado, Estado_Destino.CD_Estado ";
        }else
          sql += " ORDER by conhecimentos.dt_emissao, conhecimentos.nr_conhecimento ";

        try{


          ResultSet res = null;
          res = this.executasql.executarConsulta(sql.toString());
          while (res.next()) {

        	  SeguroED edVolta = new  SeguroED();


        	  if("R2".equals(ed.getDM_Relatorio())){

	        	  edVolta.setCD_Estado_CTRC_Origem(res.getString("CD_Estado_Origem"));
	        	  edVolta.setNM_Cidade_CTRC_Origem(res.getString("CD_Estado_Origem"));

	        	  edVolta.setCD_Estado_CTRC_Destino(res.getString("CD_Estado_Destino"));
	        	  edVolta.setNM_Cidade_CTRC_Destino(res.getString("CD_Estado_Destino"));

        	  }else {
	        	  //Cidade Origem --------------------------------
	        	  CidadeBean CidadeBean = new CidadeBean();
	        	  CidadeBean cidade_Origem = CidadeBean.getByOID(res.getInt("oid_Cidade_Origem"));
	        	  edVolta.setCD_Estado_CTRC_Origem(cidade_Origem.getCD_Estado());
	        	  edVolta.setNM_Cidade_CTRC_Origem(cidade_Origem.getNM_Cidade());
	        	  //----------------------------------------------
	        	  //Cidade Destino --------------------------------
	        	  CidadeBean cidade_Destino = CidadeBean.getByOID(res.getInt("oid_Cidade_Destino"));
	        	  edVolta.setNM_Cidade_CTRC_Destino(cidade_Destino.getNM_Cidade());
	        	  edVolta.setCD_Estado_CTRC_Destino(cidade_Destino.getCD_Estado());
	        	  //----------------------------------------------
        	  }

        	  edVolta.setDT_Inicial(ed.getDT_Inicial());
        	  edVolta.setDT_Final(ed.getDT_Final());

        	  if("R1".equals(ed.getDM_Relatorio()) || "R2".equals(ed.getDM_Relatorio())){

        		  sql =   " SELECT  " +
		                  " Conhecimentos.oid_Conhecimento, " +
		                  " Conhecimentos.Vl_Nota_Fiscal,  " +
		                  " Conhecimentos.Vl_Nota_Fiscal_Seguro" +
		                  " FROM  Conhecimentos, Unidades, Modal,  Cidades Cidade_Origem, Cidades Cidade_Destino, Produtos, Pessoas, Clientes, "+
				          "  	  Regioes_Estados Regiao_Estado_Origem,  " +
				           "  	  Regioes_Estados Regiao_Estado_Destino  " +
		                  " WHERE Conhecimentos.oid_Cidade = Cidade_Origem.oid_cidade "+
		                  " AND Conhecimentos.OID_Unidade = Unidades.OID_Unidade "+
		                  " AND Conhecimentos.OID_Pessoa = Pessoas.oid_Pessoa "+
		                  " AND Clientes.OID_Cliente = Pessoas.oid_Pessoa "+
		                  " AND Conhecimentos.OID_Produto = Produtos.OID_Produto "+
		                  " AND Conhecimentos.OID_Modal = Modal.OID_Modal "+
		                  " AND Conhecimentos.oid_Cidade_Destino = Cidade_Destino.oid_cidade " +
				          " AND Cidade_Origem.oid_Regiao_Estado   = Regiao_Estado_Origem.oid_Regiao_Estado " +
				          " AND Cidade_Destino.oid_Regiao_Estado  = Regiao_Estado_Destino.oid_Regiao_Estado ";

        		  		if("R1".equals(ed.getDM_Relatorio())){
        		  			sql += 	" AND Conhecimentos.oid_cidade = "+res.getInt("oid_Cidade_Origem")+
            		  		  		" AND Conhecimentos.oid_Cidade_Destino ="+res.getInt("oid_Cidade_Destino");
        		  		}else {
        		  			sql += 	" AND Regiao_Estado_Origem.oid_Estado = "+res.getInt("oid_Estado_Origem")+
                		  		  	" AND Regiao_Estado_Destino.oid_Estado ="+res.getInt("oid_Estado_Destino");
        		  		}

        		  sql+=sqlFiltro;

        		  ResultSet res2 = null;
        		  res2 = this.executasql.executarConsulta(sql.toString());
        		  double vl_total=0;
        		  double vl_seguro =0;
			      while (res2.next()){

			    	  SeguroED seguro = new SeguroED ();
		        	  seguro = new SeguroBD (executasql).calcula_Seguro(res2.getString("oid_Conhecimento"));
				      edVolta.setPe_aliquota_rctrc(seguro.getPe_aliquota_rctrc());
				      edVolta.setPe_aliquota_rctr_dc(seguro.getPe_aliquota_rctr_dc());

				      vl_seguro+= seguro.getVL_Seguro1_Total();

				      edVolta.setPe_aliquota_rcta(seguro.getPe_aliquota_rcta());
				      if(res2.getDouble("Vl_Nota_Fiscal_Seguro")>0){
				    	  vl_total+= res2.getDouble("Vl_Nota_Fiscal_Seguro");
				      }else
				    	  vl_total+= res2.getDouble("Vl_Nota_Fiscal");

			      }
			      edVolta.setVL_Nota_Fiscal(vl_total);
			      edVolta.setVL_Seguro1_Total(vl_seguro);
        	  }
        	  else{
	        	  SeguroED seguro = new SeguroED ();
	        	  seguro = new SeguroBD (executasql).calcula_Seguro(res.getString("oid_Conhecimento"));
			      edVolta.setPe_aliquota_rctrc(seguro.getPe_aliquota_rctrc());
			      edVolta.setPe_aliquota_rctr_dc(seguro.getPe_aliquota_rctr_dc());
			      edVolta.setVL_Seguro1_Total(seguro.getVL_Seguro1_Total());
			      edVolta.setPe_aliquota_rcta(seguro.getPe_aliquota_rcta());

		          edVolta.setNR_Conhecimento(res.getLong("NR_Conhecimento"));
		          edVolta.setCD_Unidade(res.getString("CD_Unidade"));
		          edVolta.setDT_Emissao(FormataData.formataDataBT(res.getString("DT_Emissao")));


		          edVolta.setVL_Nota_Fiscal(res.getDouble("Vl_Nota_Fiscal"));
        	 }


        	  list.add(edVolta);


          }
        }

        catch (Excecoes e){
          throw e;
        } catch (Exception exc) {
          Excecoes exce = new Excecoes();
          exce.setExc(exc);
          exce.setMensagem("Erro no método listar");
          exce.setClasse(this.getClass().getName());
          exce.setMetodo("geraRelatorio(SeguroED ed)");
        }
        return list;
      }


}
