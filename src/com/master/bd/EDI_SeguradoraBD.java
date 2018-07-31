package com.master.bd;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import java.sql.*;
import java.util.*;

import com.master.ed.*;
import com.master.root.*;
import com.master.util.*;
import com.master.util.bd.*;


public class EDI_SeguradoraBD extends Transacao {

  private ExecutaSQL executasql;
  public EDI_SeguradoraBD(ExecutaSQL sql) {
    this.executasql = sql;
  }



  public ArrayList gera_EDI_Seguradora(EDI_SeguradoraED edComp)throws Excecoes{

  String sql = null;
  ArrayList list = new ArrayList();
  EDI_SeguradoraED ed = (EDI_SeguradoraED)edComp;
  ResultSet res = null;
  ResultSet res2 = null;

    try{

          if ("E".equals(ed.getDM_Situacao())){  //CTOS Embarcados
           sql =  " SELECT Conhecimentos.NR_Conhecimento, "+
               " Conhecimentos.NM_Serie, "+
               " Conhecimentos.DM_Situacao, "+
               " Conhecimentos.Dt_Emissao, "+
               " Conhecimentos.Vl_Nota_Fiscal,  "+
               " Conhecimentos.Vl_Nota_Fiscal_Seguro,  " +
               " Conhecimentos.OID_Veiculo,  "+
               " Conhecimentos.OID_Carreta, "+
               " Pessoas.NM_Razao_Social, "+
               " Pessoa_Destino.NM_Razao_Social as NM_Razao_Social_Destinatario, "+
               " Clientes.DM_Isencao_Seguro, "+
               " Modal.DM_Tipo_Transporte, "+
               " Unidades.CD_Unidade, "+
               " Clientes.dm_rctrc, "+
               " Clientes.dm_rctr_vi, "+
               " Clientes.dm_rctr_dc, "+
               " Clientes.dm_rcta,  "+
               " Cidade_Destinatario.oid_Cidade as oid_Cidade_Destino, "+
               " Cidades.oid_Cidade as oid_Cidade_Origem, "+
               " Manifestos.DT_Emissao as DT_Embarque, "+
               " Manifestos.NR_Manifesto,  "+
               " Manifestos.oid_Veiculo as NR_Placa, "+
               " Manifestos.oid_Pessoa as oid_Motorista, "+
               " Produtos.NM_Produto " +
               " FROM Conhecimentos, Unidades, Viagens, Modal, Manifestos, Cidades, Cidades Cidade_Destinatario, Produtos, Pessoas, Clientes, Pessoas Pessoa_Destino "+
               " WHERE Conhecimentos.oid_Cidade = Cidades.oid_cidade "+
               " AND Conhecimentos.OID_Unidade = Unidades.OID_Unidade "+
               " AND Conhecimentos.OID_Pessoa_Pagador = Pessoas.oid_Pessoa "+
               " AND Conhecimentos.oid_Pessoa_Destinatario = Pessoa_Destino.oid_Pessoa "+
               " AND Clientes.OID_Cliente = Pessoas.oid_Pessoa "+
               " AND Conhecimentos.OID_Produto = Produtos.OID_Produto "+
               " AND Conhecimentos.OID_Modal = Modal.OID_Modal "+
               " AND Conhecimentos.oid_Cidade_Destino = Cidade_Destinatario.oid_cidade "+
               " AND Viagens.OID_Manifesto = Manifestos.OID_Manifesto "+
               " AND Conhecimentos.DM_Impresso = 'S' "+
               " AND Conhecimentos.DM_Situacao <> 'C' "+
               " AND Conhecimentos.VL_Total_Frete > 0" +
               " AND Viagens.OID_Conhecimento = Conhecimentos.OID_Conhecimento ";
          }
          else {
          sql =  " SELECT Conhecimentos.NR_Conhecimento, "+
               " Conhecimentos.oid_Conhecimento, "+
               " Conhecimentos.NM_Serie, "+
               " Conhecimentos.DM_Situacao, "+
               " Conhecimentos.Dt_Emissao, "+
               " Conhecimentos.Vl_Nota_Fiscal,  "+
               " Conhecimentos.Vl_Nota_Fiscal_Seguro,  " +
               " Conhecimentos.OID_Veiculo,  "+
               " Conhecimentos.OID_Carreta, "+
               " Pessoas.NM_Razao_Social, "+
               " Pessoa_Destino.NM_Razao_Social as NM_Razao_Social_Destinatario, "+
               " Clientes.DM_Isencao_Seguro, "+
               " Modal.DM_Tipo_Transporte, "+
               " Unidades.CD_Unidade, "+
               " Clientes.dm_rctrc, "+
               " Clientes.dm_rctr_vi, "+
               " Clientes.dm_rctr_dc, "+
               " Clientes.dm_rcta,  "+
               " Cidade_Destinatario.oid_Cidade as oid_Cidade_Destino, "+
               " Cidades.oid_Cidade as oid_Cidade_Origem, "+
               " Produtos.NM_Produto " +
               " FROM  Conhecimentos, Unidades, Modal,  Cidades, Cidades Cidade_Destinatario, Produtos, Pessoas, Clientes, Pessoas Pessoa_Destino "+
               " WHERE Conhecimentos.oid_Cidade = Cidades.oid_cidade "+
               " AND Conhecimentos.OID_Unidade = Unidades.OID_Unidade "+
               " AND Conhecimentos.OID_Pessoa_Pagador = Pessoas.oid_Pessoa " +
               " AND Conhecimentos.oid_Pessoa_Destinatario = Pessoa_Destino.oid_Pessoa "+
               " AND Clientes.OID_Cliente = Pessoas.oid_Pessoa "+
               " AND Conhecimentos.OID_Produto = Produtos.OID_Produto "+
               " AND Conhecimentos.OID_Modal = Modal.OID_Modal "+
               " AND Conhecimentos.oid_Cidade_Destino = Cidade_Destinatario.oid_cidade "+
               " AND Conhecimentos.DM_Impresso = 'S' ";
               if ("C".equals(ed.getDM_Situacao())){
                  sql +=" AND Conhecimentos.DM_Situacao = 'C' ";
               }
               if ("T".equals(ed.getDM_Situacao())){
                  sql += " AND Conhecimentos.DM_Situacao <> 'C' "+
                         " AND Conhecimentos.VL_Total_Frete > 0 " ;
               }
          }

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
        if ("C".equals(ed.getDM_Tipo_Documento())) {
             sql += " and Conhecimentos.DM_Tipo_Documento ='C' ";
        }
        if ("M".equals(ed.getDM_Tipo_Documento())) {
            sql += " and Conhecimentos.DM_Tipo_Documento ='M' ";
        }

        sql += " order by conhecimentos.dt_emissao, conhecimentos.nr_conhecimento ";

      res = this.executasql.executarConsulta(sql);

      FormataDataBean dataFormatada = new FormataDataBean();
      while (res.next()){
        EDI_SeguradoraED edVolta = new EDI_SeguradoraED();

          edVolta.setOid_Unidade(ed.getOid_Unidade());
          edVolta.setOid_Empresa(ed.getOid_Empresa());

          if ("E".equals(ed.getDM_Situacao())){  //CTOS Embarcados
            edVolta.setNR_Manifesto (res.getString ("NR_Manifesto"));
            edVolta.setNR_Placa (res.getString ("NR_Placa"));
            edVolta.setOID_Motorista (res.getString ("OID_Motorista"));
            edVolta.setNR_CNPJ_CPF_Motorista(res.getString ("OID_Motorista"));
            dataFormatada.setDT_FormataData(res.getString("DT_Embarque"));
            edVolta.setDT_Embarque(dataFormatada.getDT_FormataData());
          } else {
            edVolta.setNR_Manifesto ("000000");
            edVolta.setOID_Motorista (" ");
            edVolta.setNR_Placa (res.getString ("OID_Veiculo"));
            dataFormatada.setDT_FormataData(res.getString("DT_Emissao"));
            edVolta.setDT_Embarque(dataFormatada.getDT_FormataData());

            sql = " SELECT Manifestos.oid_Pessoa as oid_Motorista, Manifestos.NR_Manifesto, " +
            		" Manifestos.oid_Veiculo, Manifestos.dt_Emissao, Pessoas.NR_CNPJ_CPF " +
                " FROM   Manifestos, Viagens, Pessoas " +
                " WHERE  Manifestos.oid_Manifesto = Viagens.oid_Manifesto " +
                " AND    Manifestos.oid_Pessoa = Pessoas.oid_Pessoa " +
                " AND    Viagens.oid_Conhecimento ='" + res.getString ("oid_Conhecimento") + "'" +
                " ORDER BY Manifestos.DM_Tipo_Manifesto" ;

            res2 = this.executasql.executarConsulta (sql);

            if (res2.next ()) {
              edVolta.setOID_Motorista (res2.getString ("OID_Motorista"));
              edVolta.setNR_CNPJ_CPF_Motorista(res2.getString ("NR_CNPJ_CPF"));
              edVolta.setNR_Manifesto (res2.getString ("NR_Manifesto"));
              edVolta.setNR_Placa (res2.getString ("OID_Veiculo"));
              dataFormatada.setDT_FormataData (res2.getString ("DT_Emissao"));
              edVolta.setDT_Embarque (dataFormatada.getDT_FormataData ());
            }
          }
          edVolta.setDM_Procedencia_Veiculo("N");
          sql = " SELECT dm_procedencia FROM complementos_veiculos " +
                " WHERE  dm_procedencia='P' " +
                " AND    complementos_veiculos.oid_veiculo = '" + edVolta.getNR_Placa () + "'";

          res2 = this.executasql.executarConsulta (sql);

          while (res2.next ()) {
            edVolta.setDM_Procedencia_Veiculo("S");
          }

          String nr_man=edVolta.getNR_Manifesto();
          while (nr_man.length()<6){
            nr_man="0"+nr_man;
          }
          edVolta.setNR_Manifesto(nr_man);

          long nr_cto=1000000+res.getLong("NR_Conhecimento");
          String cto=String.valueOf(nr_cto);
          edVolta.setNR_Conhecimento(cto.substring(1,7));

          dataFormatada.setDT_FormataData(res.getString("DT_Emissao"));
          edVolta.setDT_Emissao_Conhecimento(dataFormatada.getDT_FormataData());
          edVolta.setDM_Tipo_Transporte(res.getString("DM_Tipo_Transporte"));
          edVolta.setNR_Serie_Conhecimento(res.getString("NM_Serie"));
          edVolta.setCD_Unidade(res.getString("CD_Unidade"));
          edVolta.setNM_Produto(res.getString("NM_Produto"));
          edVolta.setDM_Situacao(res.getString("DM_Situacao"));
          edVolta.setNM_Razao_Social(res.getString("NM_Razao_Social"));
          edVolta.setNM_Razao_Social_Destinatario(res.getString("NM_Razao_Social_Destinatario"));
          edVolta.setDM_Isencao_Seguro(res.getString("DM_Isencao_Seguro"));


          edVolta.setDM_RCTRC(res.getString("dm_rctrc"));
          edVolta.setDM_RCTR_VI(res.getString("dm_rctr_vi"));
          edVolta.setDM_RCTR_DC(res.getString("dm_rctr_dc"));
          edVolta.setDM_RCTA(res.getString("dm_rcta"));

          edVolta.setOID_Carreta(res.getString("OID_Carreta"));

          edVolta.setOID_Cidade_Origem(res.getInt("OID_Cidade_Origem"));
          edVolta.setOID_Cidade_Destino(res.getInt("OID_Cidade_Destino"));

          edVolta.setVL_Nota_Fiscal(res.getDouble("Vl_Nota_Fiscal"));
          if (res.getDouble("Vl_Nota_Fiscal_Seguro")>0) {
            edVolta.setVL_Nota_Fiscal(res.getDouble("Vl_Nota_Fiscal_Seguro"));
          }


        list.add(edVolta);
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      exc.printStackTrace();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao gerar REL seguro");
      excecoes.setMetodo("gera_EDI_Seguradora");
      excecoes.setExc(exc);
      throw excecoes;
    }finally{
      res = null;
      res2 = null;
    }

    return list;
  }


  public ArrayList gera_EDI_Seguradora_CRT(EDI_SeguradoraED edComp)throws Excecoes{

      String sql = null;
      String busca = null;
      ArrayList list = new ArrayList();
      EDI_SeguradoraED ed = (EDI_SeguradoraED)edComp;
      ResultSet res = null;
      ResultSet res2 = null;
      ResultSet rs = null;

        try{

            sql = " select * from Conhecimentos_Internacionais, Unidades where 1=1 "+
                   " AND Conhecimentos_Internacionais.OID_Unidade = Unidades.OID_Unidade " +
                   " and Conhecimentos_Internacionais.nr_permisso != '999' ";

                   if ("C".equals(ed.getDM_Situacao())){
                      sql +=" AND Conhecimentos_Internacionais.DM_Situacao = 'C' ";
                   }
                   if ("T".equals(ed.getDM_Situacao())){
                      sql += " AND Conhecimentos_Internacionais.DM_Situacao <> 'C' ";
                   }

              if (String.valueOf(ed.getOid_Unidade()) != null &&
                !String.valueOf(ed.getOid_Unidade()).equals("0")){
                sql += " and Conhecimentos_Internacionais.Oid_Unidade = " + ed.getOid_Unidade();
              }

              if (String.valueOf(ed.getOid_Empresa()) != null &&
                !String.valueOf(ed.getOid_Empresa()).equals("0")){
                sql += " and Unidades.Oid_Empresa = " + ed.getOid_Empresa();
              }


              if (String.valueOf(ed.getDT_Inicial()) != null &&
                !String.valueOf(ed.getDT_Inicial()).equals("") &&
                !String.valueOf(ed.getDT_Inicial()).equals("null")){
                sql += " and Conhecimentos_Internacionais.DT_Emissao >= '" + ed.getDT_Inicial() + "'";
              }
              if (String.valueOf(ed.getDT_Final()) != null &&
              !String.valueOf(ed.getDT_Final()).equals("") &&
              !String.valueOf(ed.getDT_Final()).equals("null")){
                sql += " and Conhecimentos_Internacionais.DT_Emissao <= '" + ed.getDT_Final() + "'";
              }

              sql += " order by Conhecimentos_Internacionais.dt_emissao, Conhecimentos_Internacionais.nr_conhecimento ";

          res = this.executasql.executarConsulta(sql);

          FormataDataBean DataFormatada = new FormataDataBean();
          while (res.next()){
              Conhecimento_InternacionalED edVolta = new Conhecimento_InternacionalED();

              edVolta.setDT_Emissao(res.getString("DT_Emissao"));
              DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
              edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());

              edVolta.setNR_Conhecimento(res.getLong("NR_CONHECIMENTO"));
              edVolta.setOID_Cidade_Destino(res.getLong("OID_CIDADE_DESTINO"));
              edVolta.setOID_Cidade_Embarque(res.getLong("OID_CIDADE_EMBARQUE"));
              edVolta.setOID_Conhecimento(res.getString("OID_CONHECIMENTO"));

              //edVolta.setOID_Pessoa(res.getString("OID_PESSOA"));
              busca = "select nr_CNPJ_CPF from pessoas where oid_pessoa = '"+ res.getString("oid_Pessoa") +"'";
              rs = null;
              rs = this.executasql.executarConsulta(busca);
              while(rs.next()) {
                  edVolta.setNR_CNPJ_Remetente_Complementar(rs.getString("nr_CNPJ_CPF"));
              }
              rs.close();
              edVolta.setNM_Remetente(res.getString("NM_Remetente"));

              //edVolta.setOID_Pessoa_Destinatario(res.getString("OID_PESSOA_DESTINATARIO"));
              busca = "select nr_CNPJ_CPF from pessoas where oid_pessoa = '"+ res.getString("oid_Pessoa_Destinatario") +"'";
              rs = null;
              rs = this.executasql.executarConsulta(busca);
              while(rs.next()) {
                  edVolta.setNR_CNPJ_Destinatario_Complementar(rs.getString("nr_CNPJ_CPF"));
              }
              rs.close();
              rs = null;
              edVolta.setNM_Destinatario(res.getString("NM_Destinatario"));

              edVolta.setCD_Pais(res.getString("CD_Pais"));
              edVolta.setNR_Permisso(res.getString("NR_Permisso"));

              CidadeBean CidadeVolta = CidadeBean.getByOID(res.getInt("OID_CIDADE_EMBARQUE"));
              String NM_Cidade_Origem = CidadeVolta.getNM_Cidade().trim() + ";" + CidadeVolta.getCD_Estado().trim() + ";" + CidadeVolta.getCD_Pais().trim();
      		  if(CidadeVolta.getOID_Pais() != 1){
      		     NM_Cidade_Origem = CidadeVolta.getNM_Cidade().trim() + ";" + CidadeVolta.getCD_Pais().trim() + ";" + CidadeVolta.getCD_Pais().trim();
      		  }
      		  edVolta.setNM_Cidade_Estado_Pais_Embarque(NM_Cidade_Origem);

      		  CidadeBean CidadeVolta2 = CidadeBean.getByOID(res.getInt("OID_CIDADE_DESTINO"));
              String NM_Cidade_Destino = CidadeVolta2.getNM_Cidade().trim() + ";" + CidadeVolta2.getCD_Estado().trim() + ";" + CidadeVolta2.getCD_Pais().trim();
    		  if(CidadeVolta2.getOID_Pais() != 1){
    		      NM_Cidade_Destino = CidadeVolta2.getNM_Cidade().trim() + ";" + CidadeVolta2.getCD_Pais().trim() + ";" + CidadeVolta2.getCD_Pais().trim();
    		  }
              edVolta.setNM_Cidade_Estado_Pais_Entrega(NM_Cidade_Destino);

              edVolta.setDM_Situacao(res.getString("dm_situacao"));

              edVolta.setDM_Veiculo_Novo(res.getString("dm_veiculo_novo"));

              edVolta.setVL_Nota_Fiscal(res.getDouble("vl_nota_fiscal"));

              edVolta.setVL_RCTRC(res.getDouble("vl_rctrc"));
              edVolta.setVL_Desconto_RCTRC(res.getDouble("vl_desconto_rctrc"));
              edVolta.setVL_RCTR_VI(res.getDouble("vl_rctr_vi"));
              edVolta.setVL_RCTR_DC(res.getDouble("vl_rctr_dc"));

              String nr_Conhecimento = res.getString("NR_CONHECIMENTO");
		  	  String nr_CRT_Parcial = res.getString("CD_Pais") + "." +
		  		  					  res.getString("NR_Permisso") + ".";
		  		int completa_Nr_CRT = 13 - nr_CRT_Parcial.length() - nr_Conhecimento.length();
		  		for(int a = 0 ; a < completa_Nr_CRT ; a++){
		  				nr_CRT_Parcial = nr_CRT_Parcial + "0";
		  		}
		  	  edVolta.setNM_Conhecimento(nr_CRT_Parcial+nr_Conhecimento);

		  	busca = "select nm_produto from produtos where oid_produto = '"+ res.getString("oid_Produto") +"'";
            rs = null;
            rs = this.executasql.executarConsulta(busca);
            while(rs.next()) {
                edVolta.setNM_Produto_Custo(rs.getString("nm_produto"));
            }
            rs.close();
            edVolta.setVL_Mercadoria(res.getDouble("vl_mercadoria"));


            list.add(edVolta);
          }
        }
        catch(Exception exc){
          Excecoes excecoes = new Excecoes();
          excecoes.setClasse(this.getClass().getName());
          excecoes.setMensagem("Erro ao gerar REL seguro");
          excecoes.setMetodo("gera_EDI_Seguradora");
          excecoes.setExc(exc);
          throw excecoes;
        }finally{
            res = null;
            res2 = null;
            rs = null;
          }

        return list;
      }
}
