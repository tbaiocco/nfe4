package com.master.bd;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.Auxiliar1ED;
import com.master.ed.ContaED;
import com.master.ed.EDI_ContabilED;
import com.master.ed.Movimento_ContabilED;
import com.master.root.FormataDataBean;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.SeparaEndereco;
import com.master.util.Valor;
import com.master.util.bd.ExecutaSQL;
import com.master.util.bd.Transacao;
import com.master.ed.Tipo_EventoED;


public class EDI_ContabilBD extends Transacao {

  private ExecutaSQL executasql;

  public EDI_ContabilBD(ExecutaSQL sql) {
    this.executasql = sql;
  }
  FormataDataBean dataFormatada = new FormataDataBean();


  public ArrayList gera_EDI_Faturamento(EDI_ContabilED edComp)throws Excecoes{

  String sql = null;
  ArrayList list = new ArrayList();
  EDI_ContabilED ed = (EDI_ContabilED)edComp;
  int i1=0;
    try{

      sql = "select Duplicatas.OID_Duplicata, Duplicatas.DT_Emissao, Duplicatas.VL_Duplicata, Pessoas.oid_pessoa, Pessoas.nr_cnpj_cpf, Clientes.NR_Conta_Contabil, "+
      "Pessoas.nm_razao_Social, Pessoas_Bancos.nm_razao_Social as "+
      "NM_Razao_Banco, Carteiras.oid_Carteira, Carteiras.cd_Carteira, "+
      "Contas_Correntes.NR_conta_corrente, Tipos_Documentos.oid_tipo_documento, "+
      "Tipos_Documentos.cd_tipo_documento, Tipos_Documentos.nm_tipo_documento, "+
      "Duplicatas.nr_Duplicata, Duplicatas.nr_parcela, Duplicatas.nr_documento, Duplicatas.nr_bancario, "+
      "Duplicatas.dt_vencimento, Duplicatas.vl_desconto_faturamento, "+
      "Duplicatas.vl_saldo from Unidades, Duplicatas, pessoas, Pessoas Pessoas_Bancos, "+
      " tipos_documentos, Clientes, Carteiras, Contas_Correntes where "+
      " duplicatas.oid_pessoa = pessoas.oid_pessoa and Pessoas.oid_Pessoa = Clientes.oid_Cliente and "+
      " Duplicatas.oid_Unidade = Unidades.oid_Unidade and"+
      " Duplicatas.oid_Carteira = Carteiras.oid_Carteira and"+
      " duplicatas.oid_tipo_documento = tipos_documentos.oid_tipo_documento and"+
      " carteiras.oid_conta_corrente = contas_correntes.oid_conta_corrente and "+
      " contas_correntes.oid_pessoa = pessoas_bancos.oid_pessoa ";

      if (ed.getOid_Empresa () > 0) {
        sql += " and Unidades.OID_Empresa = " + ed.getOid_Empresa ();
      }


      if (ed.getDT_Inicial() != null)
        sql += " and Duplicatas.DT_Emissao >= '" + ed.getDT_Inicial() + "'";

      if (ed.getDT_Final() != null)
        sql += " and Duplicatas.DT_Emissao <= '" + ed.getDT_Final() + "'";


//            // System.err.println(sql);

      ResultSet res = null;
      ResultSet res2 = null;

      res = this.executasql.executarConsulta(sql);

      FormataDataBean dataFormatada = new FormataDataBean();
      double vl_lancamento=0;
      double vl_total_ctrc=0;

      String cta_cliente="";

      while (res.next()){


    	sql = "select  conhecimentos.VL_Total_Frete "+
	      " from Duplicatas, origens_duplicatas, conhecimentos "+
              " where duplicatas.oid_duplicata = origens_duplicatas.oid_duplicata "+
              " AND origens_duplicatas.oid_conhecimento = conhecimentos.oid_conhecimento"+
//              " AND origens_duplicatas.dm_situacao = 'A'" +
              " and duplicatas.oid_duplicata = " + res.getString("OID_Duplicata");
//            // System.err.println(sql);

        res2 = this.executasql.executarConsulta(sql);
        vl_total_ctrc=0;

        while (res2.next()){
               vl_total_ctrc += res2.getDouble("VL_Total_Frete");
        }
        if (vl_total_ctrc>0) {
            // System.err.println("OK");

              cta_cliente="00401";

              EDI_ContabilED edVolta = new EDI_ContabilED();
	      edVolta.setDM_Tipo_Lancamento("LC1");
	      edVolta.setDM_Modo_Lancamento("1");


              edVolta.setNR_Documento(res.getString("NR_Duplicata"));
              edVolta.setNR_Bordero("      ");
              dataFormatada.setDT_FormataData(res.getString("DT_Emissao"));
              edVolta.setDT_Movimento(dataFormatada.getDT_FormataData());
              vl_lancamento=vl_total_ctrc; //(res.getDouble("vl_duplicata") - res.getDouble("vl_desconto_faturamento"));
              String OK=" -";
              if (vl_lancamento != vl_total_ctrc) OK=String.valueOf("vl_titulo" + vl_lancamento + " / vl_total_ctrc" + vl_total_ctrc );

              if ( res.getString("NR_Conta_Contabil") != null &&
                  !res.getString("NR_Conta_Contabil").equals("null") &&
                  !res.getString("NR_Conta_Contabil").equals("") )
                  cta_cliente=res.getString("NR_Conta_Contabil");

              edVolta.setNM_Conta_Credito("00415");
              edVolta.setNM_Conta_Debito(cta_cliente);
              edVolta.setNM_Historico("Emissao Titulo:"  + res.getString("NR_Duplicata")+ "/" + res.getString("nm_razao_Social") + OK );
              edVolta.setVL_Lancamento(vl_lancamento);
              i1++;
	      edVolta.setNR_Sequencia1(i1);

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


  public ArrayList gera_EDI_Conhecimento(EDI_ContabilED edComp)throws Excecoes{

   String sql = null;
   ArrayList list = new ArrayList();
   EDI_ContabilED ed = (EDI_ContabilED)edComp;

     try{

       sql = " select " +
             " Empresas.NR_CNPJ_CPF     as NR_CNPJ_CPF_Empresa, " +
             " Empresas.NM_Empresa      as NM_Razao_Social_Empresa, " +
             " Conhecimentos.NM_Serie as NM_Serie_Conhecimento, " +
             " Conhecimentos.oid_Conhecimento, " +
             " Conhecimentos.oid_Pessoa, " +
             " Conhecimentos.oid_Pessoa_Pagador, " +
             " Conhecimentos.oid_Pessoa_Destinatario, " +
             " Conhecimentos.oid_Pessoa_Consignatario, " +
             " Conhecimentos.oid_Pessoa_Redespacho, " +
             " Conhecimentos.oid_Pessoa_Entregadora, " +
             " Conhecimentos.DM_Tipo_Conhecimento, " +
             " Conhecimentos.DT_Previsao_Entrega, " +
             " Conhecimentos.HR_Previsao_Entrega, " +
             " Conhecimentos.NM_Natureza, " +
             " Conhecimentos.DM_Situacao, " +
             " Conhecimentos.TX_Observacao, " +
             " Conhecimentos.NR_Duplicata, " +
             " Conhecimentos.NR_Conhecimento, " +
             " Conhecimentos.DT_Emissao, " +
             " Conhecimentos.NR_Peso, " +
             " Conhecimentos.NR_Peso_Cubado, " +
             " Conhecimentos.VL_Total_Frete, " +
             " Conhecimentos.VL_Base_Calculo_ICMS, " +
             " Conhecimentos.PE_ALIQUOTA_ICMS, " +
             " Conhecimentos.VL_ICMS, " +
             " Conhecimentos.VL_Frete_Peso, " +
             " Conhecimentos.VL_Frete_Valor, " +
             " Conhecimentos.VL_Sec_Cat, " +
             " Conhecimentos.VL_Despacho, " +
             " Conhecimentos.VL_Pedagio, " +
             " Conhecimentos.VL_Outros1, " +
             " Conhecimentos.VL_Outros2, " +
             " Conhecimentos.CD_CFO_Conhecimento, " +
             " Unidade_Conhecimento.CD_Unidade as CD_Unidade_Conhecimento, " +
             " Unidade_Conhecimento.CD_Unidade_Contabil as CD_Unidade_Contabil, " +
             " Unidade_Conhecimento.oid_Pessoa as oid_Pessoa_Unidade " +
             " FROM  Conhecimentos, Unidades Unidade_Conhecimento, Unidades Unidade_Fiscal, Empresas " +
             " WHERE Conhecimentos.oid_unidade = Unidade_Conhecimento.oid_Unidade " +
             " AND   Unidade_Conhecimento.oid_Unidade_Fiscal = Unidade_Fiscal.oid_Unidade " +
             " AND   Unidade_Conhecimento.oid_empresa = Empresas.oid_empresa " +
             " AND   Conhecimentos.DM_Impresso = 'S' "+
             //" AND   Conhecimentos.VL_Total_Frete > 0" +
             " AND   Conhecimentos.DT_Emissao >= '" + ed.getDT_Inicial() + "'" +
             " AND   Conhecimentos.DT_Emissao <= '" + ed.getDT_Final() + "'" ;

             if (ed.getOid_Unidade() >0)
               sql += " and Unidade_Fiscal.oid_Unidade = " + ed.getOid_Unidade() ;

             if (ed.getOid_Empresa() >0)
               sql += " and Unidade_Fiscal.oid_Empresa = " + ed.getOid_Empresa() ;

             sql += " Order by  Conhecimentos.NR_Conhecimento ";


             // System.err.println(sql);

       ResultSet res = null;
       ResultSet res2 = null;

       res = this.executasql.executarConsulta(sql);

       while (res.next()){
           EDI_ContabilED edVolta = new EDI_ContabilED();
           edVolta.setDT_Inicial(ed.getDT_Inicial());
           edVolta.setDT_Final(ed.getDT_Final());
           edVolta.setNR_CNPJ_CPF_Empresa(res.getString("NR_CNPJ_CPF_Empresa"));
           edVolta.setNM_Razao_Social_Empresa(res.getString("NM_Razao_Social_Empresa"));

           edVolta.setDM_Tipo_Pagamento("C");
           if (!res.getString("oid_Pessoa").equals(res.getString("oid_Pessoa_Pagador"))){
             edVolta.setDM_Tipo_Pagamento("F");
           }
           edVolta.setDM_Substituicao_Tributaria("N");
           if (res.getDouble("VL_ICMS")<=0) edVolta.setDM_Substituicao_Tributaria("S");

           edVolta.setOid_Pessoa(res.getString("oid_Pessoa"));

           edVolta.setOid_Pessoa_Destinatario(res.getString("oid_Pessoa_Destinatario"));
           edVolta.setOid_Pessoa_Consignatario(res.getString("oid_Pessoa_Consignatario"));
           edVolta.setOid_Pessoa_Redespacho(res.getString("oid_Pessoa_Redespacho"));
           edVolta.setOid_Pessoa_Entregadora(res.getString("oid_Pessoa_Entregadora"));

           edVolta.setOid_Pessoa_Pagador(res.getString("oid_Pessoa_Pagador"));

           edVolta.setOid_Pessoa_Unidade(res.getString("oid_Pessoa_Unidade"));

           edVolta.setNM_Filial_Conhecimento(res.getString("CD_Unidade_Conhecimento"));
           edVolta.setCD_Unidade_Contabil(res.getString("CD_Unidade_Contabil"));
           edVolta.setNR_Serie_Conhecimento(res.getString("NM_Serie_Conhecimento"));

           long nr_cto=1000000+res.getLong("NR_Conhecimento");
           String cto=String.valueOf(nr_cto);

           edVolta.setNR_Conhecimento(cto.substring(1,7));


           dataFormatada.setDT_FormataData(res.getString("DT_Emissao"));
           edVolta.setDT_Emissao_Conhecimento(dataFormatada.getDT_FormataData());

           dataFormatada.setDT_FormataData(res.getString("DT_Previsao_Entrega"));
           edVolta.setDT_Previsao_Entrega(dataFormatada.getDT_FormataData());

           edVolta.setHR_Previsao_Entrega(res.getString("HR_Previsao_Entrega"));
           edVolta.setNM_Natureza(res.getString("NM_Natureza"));
           edVolta.setTX_Observacao(res.getString("TX_Observacao"));
           edVolta.setNR_Duplicata(res.getString("NR_Duplicata"));

           edVolta.setDM_Tipo_Conhecimento(res.getString("DM_Tipo_Conhecimento"));

           edVolta.setCD_CFO_Conhecimento(res.getString("CD_CFO_Conhecimento"));

 // System.err.println("3 ok ");

           edVolta.setDM_Situacao(res.getString("DM_Situacao"));


           edVolta.setNR_Nota_Fiscal ("                               ");
           edVolta.setNR_Pedido ("                               ");
           edVolta.setNR_Serie_Nota_Fiscal ("                               ");
           edVolta.setDT_Emissao_Nota_Fiscal ("                               ");
           edVolta.setNR_Peso_Nota_Fiscal (0);
           edVolta.setVL_Nota_Fiscal (0);
           edVolta.setNR_Volume_Nota_Fiscal (0);

           if (!"C".equals(res.getString("DM_Situacao"))) {
             edVolta.setNR_Peso_Conhecimento (res.getDouble ("NR_Peso_Cubado"));
             if (edVolta.getNR_Peso_Conhecimento () <= 0) {
               edVolta.setNR_Peso_Conhecimento (res.getDouble ("NR_Peso"));
             }

             edVolta.setVL_Total_Frete (res.getDouble ("VL_Total_Frete"));
             edVolta.setVL_Base_Calculo_ICMS (res.getDouble ("VL_Base_Calculo_ICMS"));
             edVolta.setPE_Aliquota_ICMS (res.getDouble ("PE_ALIQUOTA_ICMS"));
             edVolta.setVL_ICMS (res.getDouble ("VL_ICMS"));

             edVolta.setVL_Frete_Peso (res.getDouble ("VL_Frete_Peso"));
             edVolta.setVL_Frete_Valor (res.getDouble ("VL_Frete_Valor"));
             // System.err.println ("4 ICMS " + res.getDouble ("VL_ICMS"));
             edVolta.setVL_Despacho (res.getDouble ("VL_Despacho"));
             edVolta.setVL_Pedagio (res.getDouble ("VL_Pedagio"));
             // System.err.println ("4 b");
             edVolta.setVL_Outros1 (res.getDouble ("VL_Outros1"));
             edVolta.setVL_Ademe (res.getDouble ("VL_Outros2"));
             // System.err.println ("4 c");

             sql = " SELECT Notas_Fiscais.NM_Serie as NM_Serie_Nota_Fiscal, " +
                 " Notas_Fiscais.NR_Nota_Fiscal, " +
                 " Notas_Fiscais.NR_Pedido, " +
                 " Notas_Fiscais.DT_Emissao as DT_Emissao_Nota_Fiscal, " +
                 " Notas_Fiscais.NR_Peso as NR_Peso_Nota_Fiscal,  " +
                 " Notas_Fiscais.NR_Volumes as NR_Volumes_Nota_Fiscal,  " +
                 " Notas_Fiscais.NR_Peso_Cubado as NR_Peso_Cubado_Nota_Fiscal,  " +
                 " Notas_Fiscais.VL_Nota_Fiscal as VL_Nota_Fiscal_Nota_Fiscal " +
                 " FROM   Conhecimentos_Notas_Fiscais, Notas_Fiscais " +
                 " WHERE  Conhecimentos_Notas_Fiscais.oid_Nota_Fiscal = Notas_Fiscais.oid_Nota_Fiscal " +
                 " AND    Conhecimentos_Notas_Fiscais.oid_Conhecimento ='" + res.getString ("oid_Conhecimento") + "'";

             res2 = this.executasql.executarConsulta (sql);
             while (res2.next ()) {
               edVolta.setNR_Nota_Fiscal (res2.getString ("NR_Nota_Fiscal"));
               // System.err.println ("4 e");
               edVolta.setNR_Pedido (res2.getString ("NR_Pedido"));
               edVolta.setNR_Serie_Nota_Fiscal (res2.getString ("NM_Serie_Nota_Fiscal"));
               // System.err.println ("5");

               dataFormatada.setDT_FormataData (res2.getString ("DT_Emissao_Nota_Fiscal"));
               edVolta.setDT_Emissao_Nota_Fiscal (dataFormatada.getDT_FormataData ());
               edVolta.setNR_Peso_Nota_Fiscal (res2.getDouble ("NR_Peso_Cubado_Nota_Fiscal"));
               if (edVolta.getNR_Peso_Nota_Fiscal () <= 0) {
                 edVolta.setNR_Peso_Nota_Fiscal (res2.getDouble ("NR_Peso_Nota_Fiscal"));
               }
               edVolta.setVL_Nota_Fiscal (res2.getDouble ("VL_Nota_Fiscal_Nota_Fiscal"));
               edVolta.setNR_Volume_Nota_Fiscal (res2.getDouble ("NR_Volumes_Nota_Fiscal"));
             }

           }

               // System.err.println("99");


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

  public ArrayList gera_EDI_Ordem_Frete(EDI_ContabilED ed) throws Excecoes {

      String sql = null;
      ArrayList list = new ArrayList();
      try {



              sql = " SELECT Ordens_Fretes.* " +
                    " FROM  Ordens_Fretes, Unidades, Pessoas, Empresas, Pessoas Pessoa_Motorista " +
                    " WHERE Ordens_Fretes.OID_Unidade = Unidades.OID_Unidade " +
                    " AND Unidades.OID_Empresa = Empresas.OID_Empresa " +
                    " AND Ordens_Fretes.OID_Pessoa = Pessoas.OID_Pessoa " +
                    " AND Ordens_Fretes.OID_Motorista = Pessoa_Motorista.OID_Pessoa " +
                    " AND Ordens_Fretes.VL_Ordem_Frete > 0" +
                    " AND Ordens_Fretes.Dm_Impresso = 'S' "+
                    " AND Ordens_Fretes.DT_Emissao >= '" + ed.getDT_Inicial() + "'" +
                    " AND Ordens_Fretes.DT_Emissao <= '" + ed.getDT_Final() + "'" ;

                if (ed.getOid_Unidade () > 0)
                  sql += " and Ordens_Fretes.oid_Unidade = " + ed.getOid_Unidade ();

                if (ed.getOid_Empresa () > 0)
                  sql += " and Unidades.oid_Empresa = " + ed.getOid_Empresa ();

                if ("A".equals(ed.getDM_Frete())){
                  sql += " and Ordens_Fretes.DM_Frete = 'A'";
                }
                if ("P".equals(ed.getDM_Frete())){
                  sql += " and Ordens_Fretes.DM_Frete = 'P'";
                }

                if (!"T".equals (ed.getDM_Tipo_Pessoa ())) {
                  if ("FJ".equals (ed.getDM_Tipo_Pessoa ())) {
                    sql += " and Ordens_Fretes.DM_Tipo_Pessoa <>'FE'";//funcionario empresa
                  }
                  else {
                    sql += " and Ordens_Fretes.DM_Tipo_Pessoa ='" + ed.getDM_Tipo_Pessoa () + "'";
                  }
                }

                sql += " order by Ordens_Fretes.OID_Motorista ";
                // System.err.println("sql sql" + sql);


              ResultSet res = null;
              res = this.executasql.executarConsulta(sql);

              FormataDataBean FormataData = new FormataDataBean();

              //popula
              String listar="";
				String OID_Pessoa = "PRIMEIRO";
				String OID_Proprietario_Motorista = "";
				double vl_ordem_frete = 0;
				double vl_ordem_total = 0;
				double vl_adto_pendente = 0;
				double vl_set_senat = 0;
				double vl_irrf = 0;
				double vl_inss_prestador = 0;
				double vl_inss_empresa = 0;
				double vl_total_ordem_frete = 0;
				double vl_total_adto_pendente = 0;
				double vl_total_set_senat = 0;
				double vl_total_irrf = 0;
				double vl_total_inss_prestador = 0;
				double vl_total_inss_empresa = 0;
				long   nr_ordem_frete = 0;
				long   nr_ordem_frete_motorista = 0;

				String dt_emissao = "";

				while (res.next ()) {
						OID_Proprietario_Motorista = res.getString ("OID_Motorista");

						vl_ordem_total = res.getDouble ("VL_Ordem_Frete") + res.getDouble ("VL_Coleta") +
						res.getDouble ("VL_Premio") + res.getDouble ("VL_Outros") +
						res.getDouble ("VL_Pedagio") - res.getDouble ("VL_Descontos");
						vl_ordem_frete = vl_ordem_total;
						vl_ordem_frete = res.getDouble ("VL_Ordem_Frete");
						vl_adto_pendente = 0;
						if (res.getLong ("oid_acerto") <= 0) {
							vl_adto_pendente = vl_ordem_total;
						}
						vl_set_senat = res.getDouble ("vl_set_senat");
						vl_irrf = res.getDouble ("vl_irrf");
						vl_inss_prestador = res.getDouble ("vl_inss_prestador");
						vl_inss_empresa = res.getDouble ("vl_inss_empresa");
						nr_ordem_frete = res.getLong ("NR_Ordem_Frete");
						dt_emissao = res.getString ("dt_emissao");

						if (OID_Pessoa.equals ("PRIMEIRO") || OID_Proprietario_Motorista.equals (OID_Pessoa)) {
							OID_Pessoa = OID_Proprietario_Motorista;
							vl_total_ordem_frete = vl_total_ordem_frete + vl_ordem_frete;
							vl_total_adto_pendente = vl_total_adto_pendente + vl_adto_pendente;
							vl_total_set_senat = vl_total_set_senat + vl_set_senat;
							vl_total_irrf = vl_total_irrf + vl_irrf;
							vl_total_inss_prestador = vl_total_inss_prestador + vl_inss_prestador;
							vl_total_inss_empresa = vl_total_inss_empresa + vl_inss_empresa;
							nr_ordem_frete_motorista = nr_ordem_frete;
						}
						else {
							if (vl_total_ordem_frete > 0) {
				                EDI_ContabilED edVolta = new EDI_ContabilED();
				                edVolta.setOID_Motorista (OID_Pessoa);
				                edVolta.setVL_Ordem_Frete (vl_total_ordem_frete);
				                edVolta.setVL_Set_Senat (vl_total_set_senat);
								edVolta.setVL_IRRF (vl_total_irrf);
								edVolta.setVL_INSS_Prestador (vl_total_inss_prestador);
								edVolta.setVL_INSS_Empresa (vl_total_inss_empresa);
                                edVolta.setNR_Ordem_Frete(nr_ordem_frete_motorista);
                                dataFormatada.setDT_FormataData(dt_emissao);
                                edVolta.setDT_Emissao(dataFormatada.getDT_FormataData());

                                list.add (edVolta);
							}
							OID_Pessoa = OID_Proprietario_Motorista;
							vl_total_ordem_frete = vl_ordem_frete;
							vl_total_adto_pendente = vl_adto_pendente;
							vl_total_set_senat = vl_set_senat;
							vl_total_irrf = vl_irrf;
							vl_total_inss_prestador = vl_inss_prestador;
							vl_total_inss_empresa = vl_inss_empresa;
							nr_ordem_frete_motorista = nr_ordem_frete;
						}
					}
					if (vl_total_ordem_frete > 0) {
		                EDI_ContabilED edVolta = new EDI_ContabilED();
		                edVolta.setOID_Motorista (OID_Pessoa);
		                edVolta.setVL_Ordem_Frete (vl_total_ordem_frete);
		                edVolta.setVL_Set_Senat (vl_total_set_senat);
						edVolta.setVL_IRRF (vl_total_irrf);
						edVolta.setVL_INSS_Prestador (vl_total_inss_prestador);
						edVolta.setVL_INSS_Empresa (vl_total_inss_empresa);
                        edVolta.setNR_Ordem_Frete(nr_ordem_frete_motorista);
                        dataFormatada.setDT_FormataData(dt_emissao);
                        edVolta.setDT_Emissao(dataFormatada.getDT_FormataData());

                        list.add (edVolta);
					}


      } catch (Exception exc) {
          Excecoes excecoes = new Excecoes();
          excecoes.setClasse(this.getClass().getName());
          excecoes.setMensagem("Erro ao listar Ordem Frete");
          excecoes.setMetodo("lista");
          excecoes.setExc(exc);
          throw excecoes;
      }

      return (list);
  }

  public ArrayList gera_EDI_Ordem_Frete_Motorista(EDI_ContabilED ed) throws Excecoes {

      String sql = null;
      ArrayList list = new ArrayList();
      try {

              sql = " SELECT distinct(Pessoa_Motorista.oid_pessoa), " +
                    " Pessoa_Motorista.oid_pessoa as oid_Motorista, " +
                    " Pessoa_Motorista.NM_Razao_Social as NM_Motorista , " +
                    " Pessoa_Motorista.NM_Endereco as NM_Endereco_Motorista , " +
                    " Pessoa_Motorista.NM_Bairro as NM_Bairro_Motorista , " +
                    " Pessoa_Motorista.NR_CEP as NR_CEP_Motorista , " +
                    " Cidade_Motorista.NM_Cidade as NM_Cidade_Motorista , " +
                    " Estado_Motorista.CD_Estado as CD_Estado_Motorista " +
                    " FROM  Ordens_Fretes, Unidades, Empresas, Pessoas Pessoa_Motorista, Cidades Cidade_Motorista," +
                    "       Regioes_Estados, Estados Estado_Motorista" +
                    " WHERE Ordens_Fretes.OID_Unidade = Unidades.OID_Unidade " +
                    " AND Unidades.OID_Empresa = Empresas.OID_Empresa " +
                    " AND Ordens_Fretes.OID_Motorista = Pessoa_Motorista.OID_Pessoa " +
                    " AND Pessoa_Motorista.oid_Cidade = Cidade_Motorista.oid_Cidade " +
                    " AND Cidade_Motorista.oid_Regiao_Estado = Regioes_Estados.oid_Regiao_Estado " +
                    " AND Regioes_Estados.oid_Estado = Estado_Motorista.oid_Estado " +
                    " AND Ordens_Fretes.VL_Ordem_Frete > 0" +
                    " AND Ordens_Fretes.Dm_Impresso = 'S' "+
                    " AND Ordens_Fretes.DT_Emissao >= '" + ed.getDT_Inicial() + "'" +
                    " AND Ordens_Fretes.DT_Emissao <= '" + ed.getDT_Final() + "'" ;

                if (ed.getOid_Unidade () > 0)
                  sql += " and Ordens_Fretes.oid_Unidade = " + ed.getOid_Unidade ();

                if (ed.getOid_Empresa () > 0)
                  sql += " and Unidades.oid_Empresa = " + ed.getOid_Empresa ();

                if ("A".equals(ed.getDM_Frete())){
                  sql += " and Ordens_Fretes.DM_Frete = 'A'";
                }
                if ("P".equals(ed.getDM_Frete())){
                  sql += " and Ordens_Fretes.DM_Frete = 'P'";
                }

                if (!"T".equals (ed.getDM_Tipo_Pessoa ())) {
                  if ("FJ".equals (ed.getDM_Tipo_Pessoa ())) {
                    sql += " and Ordens_Fretes.DM_Tipo_Pessoa <>'FE'";//funcionario empresa
                  }
                  else {
                    sql += " and Ordens_Fretes.DM_Tipo_Pessoa ='" + ed.getDM_Tipo_Pessoa () + "'";
                  }
                }

                sql += " group by Pessoa_Motorista.oid_pessoa, " +
                       " Pessoa_Motorista.NM_Razao_Social, " +
                       " Pessoa_Motorista.NM_Endereco, " +
                       " Pessoa_Motorista.NM_Bairro, " +
                       " Pessoa_Motorista.NR_CEP, " +
                       " Cidade_Motorista.NM_Cidade, " +
                       " Estado_Motorista.CD_Estado ";

                sql += " Order by Pessoa_Motorista.oid_pessoa ";
                // System.err.println("sql sql" + sql);

              ResultSet res = null;
              res = this.executasql.executarConsulta(sql);

              FormataDataBean FormataData = new FormataDataBean();

              //popula
              while (res.next()) {

                // System.out.println(" OF 1" );
                EDI_ContabilED edVolta = new EDI_ContabilED();

                edVolta.setOID_Pessoa (res.getString ("OID_Motorista"));
                edVolta.setOID_Motorista (res.getString ("OID_Motorista"));
                edVolta.setNM_Razao_Social (res.getString ("NM_Motorista"));
                edVolta.setNM_Motorista (res.getString ("NM_Motorista"));
                edVolta.setNM_Endereco (res.getString ("NM_Endereco_Motorista"));
                edVolta.setNM_Bairro(res.getString ("NM_Bairro_Motorista"));
                edVolta.setNR_CEP(res.getString ("NR_CEP_Motorista"));

                edVolta.setNM_Cidade(res.getString("NM_Cidade_Motorista"));
                edVolta.setCD_Estado(res.getString("CD_Estado_Motorista"));
                list.add (edVolta);
              }

      } catch (Exception exc) {
          Excecoes excecoes = new Excecoes();
          excecoes.setClasse(this.getClass().getName());
          excecoes.setMensagem("Erro ao listar Ordem Frete");
          excecoes.setMetodo("lista");
          excecoes.setExc(exc);
          throw excecoes;
      }

      return (list);
  }

    public ArrayList gera_EDI_Clientes(EDI_ContabilED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();
        String cd_unidade_contabil="000";
        try {

          if (ed.getOid_Unidade()>0){
            sql = " SELECT CD_Unidade_Contabil  " +
                  " FROM Unidades " +
                  " WHERE Unidades.oid_Unidade = " + ed.getOid_Unidade ();
                ResultSet res2 = null;
                res2 = this.executasql.executarConsulta(sql);
                while (res2.next()) {
                  cd_unidade_contabil=res2.getString("CD_Unidade_Contabil");
                }
          }



          sql = " SELECT * FROM Pessoas, Cidades, Regioes_Estados, Estados, Clientes  " +
                " WHERE  Pessoas.oid_Cidade = Cidades.oid_Cidade " +
                " AND    Pessoas.OID_Pessoa = Clientes.OID_Pessoa " +
                " AND    Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado " +
                " AND    Regioes_Estados.OID_Estado = Estados.OID_Estado  ";
                  // System.err.println("sql sql" + sql);


                ResultSet res = null;
                res = this.executasql.executarConsulta(sql);

                FormataDataBean FormataData = new FormataDataBean();

                //popula
                while (res.next()) {
                  // System.out.println(" OF 1" );
                  EDI_ContabilED edVolta = new EDI_ContabilED();
                  edVolta.setCD_Unidade_Contabil(cd_unidade_contabil);
                  edVolta.setDT_Inicial(ed.getDT_Inicial());
                  edVolta.setDT_Final(ed.getDT_Final());

                  edVolta.setNR_CNPJ_CPF(res.getString ("NR_CNPJ_CPF"));
                  edVolta.setNM_Razao_Social(res.getString ("NM_Razao_Social"));
                  edVolta.setNM_Fantasia(res.getString ("NM_Fantasia"));
                  if (edVolta.getNM_Fantasia() == null || edVolta.getNM_Fantasia().equals("null")  || edVolta.getNM_Fantasia().equals("")){
                    edVolta.setNM_Fantasia(res.getString ("NM_Razao_Social"));
                  }


                  edVolta.setNM_Endereco(res.getString ("NM_Endereco"));
                  edVolta.setNM_Inscricao_Estadual(res.getString ("NM_Inscricao_Estadual"));
                  edVolta.setNR_CEP(res.getString ("NR_CEP"));
                  edVolta.setCD_Estado(res.getString ("CD_Estado"));
                  edVolta.setNM_Cidade(res.getString ("NM_Cidade"));
                  edVolta.setNM_Codigo_IBGE(res.getString ("NM_Codigo_IBGE"));

                  edVolta.setNR_Telefone(res.getString ("NR_Telefone"));
                  if (edVolta.getNR_Telefone() == null || edVolta.getNR_Telefone().equals("null")  || edVolta.getNR_Telefone().equals("")){
                      edVolta.setNR_Telefone("  ");
                  }

                  edVolta.setDT_Cadastro(FormataData.getDT_FormataData(res.getString("dt_cadastro")));
                  edVolta.setNM_Email(res.getString("email"));
                  edVolta.setNM_Site(res.getString("nm_site"));

                  // System.out.println(" OF 4" );


                  list.add(edVolta);

                }

        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao listar Cliente");
            excecoes.setMetodo("lista");
            excecoes.setExc(exc);
            throw excecoes;
        }

        return (list);
    }


    public ArrayList gera_EDI_Pessoas_Embarques (EDI_ContabilED ed) throws Excecoes {

      String sql = null;
      ArrayList list = new ArrayList ();
      String oid_Pessoa = "";
      ResultSet res = null;
      FormataDataBean FormataData = new FormataDataBean ();
      int i = 0;
      try {

        sql = " SELECT * FROM Pessoas, Cidades, Regioes_Estados, Estados, Conhecimentos  " +
            " WHERE  Pessoas.oid_Cidade = Cidades.oid_Cidade " +
            " AND    Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado " +
            " AND    Regioes_Estados.OID_Estado = Estados.OID_Estado " +
            " AND    Pessoas.OID_Pessoa = Conhecimentos.OID_Pessoa " +
            " AND   Conhecimentos.DT_Emissao >='" + ed.getDT_Inicial () + "'" +
            " AND   Conhecimentos.DT_Emissao <='" + ed.getDT_Final () + "'" +
            " ORDER BY Pessoas.OID_Pessoa ";
        // System.err.println ("sql sql" + sql);

        res = this.executasql.executarConsulta (sql);
        while (res.next () && i < 10000) {
          if (!oid_Pessoa.equals (res.getString ("oid_Pessoa"))) {
            oid_Pessoa = res.getString ("oid_Pessoa");
            EDI_ContabilED edVolta = new EDI_ContabilED ();
            edVolta.setNR_CNPJ_CPF (res.getString ("NR_CNPJ_CPF"));
            edVolta.setNM_Razao_Social (res.getString ("NM_Razao_Social"));

            // System.out.println (i + " ->>> " + res.getString ("NM_Razao_Social"));

            edVolta.setNM_Fantasia (res.getString ("NM_Fantasia"));
            if (edVolta.getNM_Fantasia () == null || edVolta.getNM_Fantasia ().equals ("null") || edVolta.getNM_Fantasia ().equals ("")) {
              edVolta.setNM_Fantasia (res.getString ("NM_Razao_Social"));
            }
            edVolta.setNM_Endereco (res.getString ("NM_Endereco"));
            edVolta.setNM_Inscricao_Estadual (res.getString ("NM_Inscricao_Estadual"));
            edVolta.setNR_CEP (res.getString ("NR_CEP"));
            edVolta.setCD_Estado (res.getString ("CD_Estado"));
            edVolta.setNM_Cidade (res.getString ("NM_Cidade"));
            edVolta.setNR_Telefone (res.getString ("NR_Telefone"));
            list.add (edVolta);
            i++;
          }
        }

        sql = " SELECT * FROM Pessoas, Cidades, Regioes_Estados, Estados, Conhecimentos  " +
            " WHERE  Pessoas.oid_Cidade = Cidades.oid_Cidade " +
            " AND    Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado " +
            " AND    Regioes_Estados.OID_Estado = Estados.OID_Estado " +
            " AND    Pessoas.OID_Pessoa = Conhecimentos.OID_Pessoa_Destinatario " +
            " AND   Conhecimentos.DT_Emissao >='" + ed.getDT_Inicial () + "'" +
            " AND   Conhecimentos.DT_Emissao <='" + ed.getDT_Final () + "'" +
            " ORDER BY Pessoas.OID_Pessoa ";
        // System.err.println ("sql sql" + sql);
        res = this.executasql.executarConsulta (sql);
        //popula
        i = 0;
        while (res.next () && i < 10000) {
          if (!oid_Pessoa.equals (res.getString ("oid_Pessoa"))) {
            oid_Pessoa = res.getString ("oid_Pessoa");
            EDI_ContabilED edVolta = new EDI_ContabilED ();
            edVolta.setNR_CNPJ_CPF (res.getString ("NR_CNPJ_CPF"));
            edVolta.setNM_Razao_Social (res.getString ("NM_Razao_Social"));

            // System.out.println (i + " ->>> " + res.getString ("NM_Razao_Social"));

            edVolta.setNM_Fantasia (res.getString ("NM_Fantasia"));
            if (edVolta.getNM_Fantasia () == null || edVolta.getNM_Fantasia ().equals ("null") || edVolta.getNM_Fantasia ().equals ("")) {
              edVolta.setNM_Fantasia (res.getString ("NM_Razao_Social"));
            }
            edVolta.setNM_Endereco (res.getString ("NM_Endereco"));
            edVolta.setNM_Inscricao_Estadual (res.getString ("NM_Inscricao_Estadual"));
            edVolta.setNR_CEP (res.getString ("NR_CEP"));
            edVolta.setCD_Estado (res.getString ("CD_Estado"));
            edVolta.setNM_Cidade (res.getString ("NM_Cidade"));
            edVolta.setNR_Telefone (res.getString ("NR_Telefone"));
            list.add (edVolta);
            i++;
          }
        }

      }
      catch (Exception exc) {
        Excecoes excecoes = new Excecoes ();
        excecoes.setClasse (this.getClass ().getName ());
        excecoes.setMensagem ("Erro ao listar Cliente");
        excecoes.setMetodo ("lista");
        excecoes.setExc (exc);
        throw excecoes;
      }

      return (list);
    }

  public ArrayList gera_EDI_Compromissos(EDI_ContabilED edComp)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();
    EDI_ContabilED ed = (EDI_ContabilED)edComp;
    String  cta_fornecedor="";
    String  nm_razao_social="";
    ResultSet res2 = null;
    FormataDataBean dataFormatada = new FormataDataBean();
    double vl_lancamento=0;
    int i1=0;

    try{
      sql = "SELECT * FROM Compromissos, tipos_documentos, Contas  "+
      " WHERE compromissos.oid_tipo_documento = tipos_Documentos.oid_tipo_documento "+
      " AND   compromissos.oid_Conta = Contas.oid_Conta " +
      " AND   Compromissos.NR_Parcela <=1 " +
      " AND   Contas.DM_Despesa = 'S'";

      if (ed.getOid_Unidade() >0)
        sql += " and compromissos.oid_Unidade = " + ed.getOid_Unidade() ;

      if (ed.getDT_Inicial() != null)
        sql += " and compromissos.dt_Entrada >= '" + ed.getDT_Inicial() + "'";

      if (ed.getDT_Final() != null)
        sql += " and compromissos.dt_Entrada <= '" + ed.getDT_Final() + "'";

 // System.out.println(sql);

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      //popula
      while (res.next()){

            // System.err.println("achou comp ");

	      sql = "select  NM_Conta_Contabil  FROM Fornecedores  WHERE Fornecedores.oid_Pessoa = '" + res.getString("oid_Pessoa") + "'";
      // System.err.println(sql);

	      res2 = this.executasql.executarConsulta(sql);
              cta_fornecedor="02409";
	      while (res2.next()){
		  if ( res2.getString("NM_Conta_Contabil") != null &&
		      !res2.getString("NM_Conta_Contabil").equals("null") &&
		      !res2.getString("NM_Conta_Contabil").equals("") )
                      cta_fornecedor=res2.getString("NM_Conta_Contabil");
	      }
	      sql = "select  NM_Razao_Social  FROM Pessoas  WHERE Pessoas.oid_Pessoa = '" + res.getString("oid_Pessoa") + "'";
      // System.err.println(sql);

	      res2 = this.executasql.executarConsulta(sql);
              nm_razao_social="";
	      while (res2.next()){
                 nm_razao_social=res2.getString("NM_Razao_Social");
	      }

              EDI_ContabilED edVolta = new EDI_ContabilED();
	      edVolta.setDM_Tipo_Lancamento("LC1");
	      edVolta.setDM_Modo_Lancamento("1");


              edVolta.setNR_Documento(res.getString("NR_Documento"));
              dataFormatada.setDT_FormataData(res.getString("dt_Entrada"));
              edVolta.setDT_Movimento(dataFormatada.getDT_FormataData());
              vl_lancamento=(res.getDouble("vl_Compromisso"));
              String OK=" ";


              edVolta.setNM_Conta_Credito(cta_fornecedor);
              edVolta.setNM_Conta_Debito("0" + res.getString("CD_Conta_Contabil"));
              edVolta.setNM_Historico("Vlr Provisao:"+ res.getString("NM_Tipo_Documento") +" " + res.getString("NR_Documento") +"/"  + res.getString("NR_Parcela")+ "-" + nm_razao_social + OK );
              edVolta.setVL_Lancamento(vl_lancamento);
              i1++;
	      edVolta.setNR_Sequencia1(i1);

              list.add(edVolta);

      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao listar dados de compromisso");
      excecoes.setMetodo("lista(CompromissoED)");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return list;
  }

  public ArrayList gera_EDI_Pagamentos_Compromissos(EDI_ContabilED edComp)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();
    EDI_ContabilED ed = (EDI_ContabilED)edComp;
    String  cta_fornecedor="";
    String  nm_razao_social="";
    ResultSet res2 = null;
    ResultSet resComp = null;
      FormataDataBean dataFormatada = new FormataDataBean();
      double vl_lancamento=0;
      double vl_pagamento=0;
      double vl_juros=0;
      double vl_desconto=0;


      int i=0;
      int i1=0;
      int i2=0;
      long NR_QT_Contas=0;

    try{

      sql = "select lotes_pagamentos.nr_documento, " +
      "             lotes_pagamentos.oid_lote_pagamento,  " +
      "             lotes_pagamentos.NM_Favorecido,  " +
      "             lotes_pagamentos.DT_Emissao,  " +
      "             lotes_pagamentos.vl_lote_pagamento,  " +
      "             tipos_documentos.nm_tipo_documento,  " +
      "             contas.cd_conta_contabil  " +
      " FROM  Tipos_documentos, lotes_pagamentos, contas_correntes, contas "+
      " WHERE Lotes_Pagamentos.oid_tipo_documento = tipos_Documentos.oid_tipo_documento "+
      " AND   Lotes_Pagamentos.oid_conta_corrente = contas_correntes.oid_conta_corrente "+
      " AND   Contas_correntes.oid_Conta = Contas.oid_Conta " +
      " AND   Lotes_Pagamentos.DM_Situacao <> 'C' ";

      if (ed.getOid_Unidade() >0)
        sql += " and Lotes_Pagamentos.oid_Unidade = " + ed.getOid_Unidade() ;


      if (String.valueOf(ed.getDT_Inicial()) != null &&
        !String.valueOf(ed.getDT_Inicial()).equals("") &&
        !String.valueOf(ed.getDT_Inicial()).equals("null")){
        sql += " and lotes_pagamentos.DT_Emissao >= '" + ed.getDT_Inicial() + "'";
      }
      if (String.valueOf(ed.getDT_Final()) != null &&
      !String.valueOf(ed.getDT_Final()).equals("") &&
      !String.valueOf(ed.getDT_Final()).equals("null")){
        sql += " and lotes_pagamentos.DT_Emissao <= '" + ed.getDT_Final() + "'";
      }

 // System.out.println(sql);

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      //popula
      while (res.next()){

            // System.err.println("achou comp ");


              EDI_ContabilED edVolta = new EDI_ContabilED();
	      edVolta.setDM_Tipo_Lancamento("LC1");
	      edVolta.setDM_Modo_Lancamento("2");

              edVolta.setNR_Documento(" ");
              dataFormatada.setDT_FormataData(res.getString("DT_Emissao"));
              edVolta.setDT_Movimento(dataFormatada.getDT_FormataData());

              edVolta.setNM_Conta_Debito(" ");
              edVolta.setNM_Conta_Credito(" ");
              edVolta.setNM_Historico(" ");
              edVolta.setVL_Lancamento(0.0);

              NR_QT_Contas=1;
	      sql = "select vl_pagamento, vl_multa_pagamento, vl_desconto, vl_juros_pagamento, vl_outras_despesas from Compromissos, Lotes_Compromissos  "+
	      " WHERE  Lotes_Compromissos.oid_compromisso = compromissos.oid_compromisso "+
	      " AND   Lotes_Compromissos.oid_Lote_Pagamento = " + res.getString("oid_lote_pagamento");
	      resComp = this.executasql.executarConsulta(sql);
	      while (resComp.next()){
                NR_QT_Contas++;

                if ((resComp.getDouble("vl_multa_pagamento")+resComp.getDouble("vl_juros_pagamento")+resComp.getDouble("vl_outras_despesas"))>0){
                  NR_QT_Contas++;
                }
                if (resComp.getDouble("vl_desconto")>0){
                  NR_QT_Contas++;
                }
	      }
              edVolta.setNR_QT_Contas(NR_QT_Contas);

	      /// LC2
              EDI_ContabilED edVolta2 = new EDI_ContabilED();
      	      edVolta2.setDM_Tipo_Lancamento("LC2");
	      edVolta2.setNR_Documento(res.getString("NR_Documento"));
              dataFormatada.setDT_FormataData(res.getString("DT_Emissao"));
              edVolta2.setDT_Movimento(dataFormatada.getDT_FormataData());
              vl_lancamento=(res.getDouble("vl_lote_pagamento"));
	      edVolta2.setDM_Debito_Credito("C");
	      edVolta2.setCD_Acesso("0" + res.getString("CD_Conta_Contabil"));
	      edVolta2.setNM_Historico(res.getString("NM_Tipo_Documento") +" " + res.getString("NR_Documento"));
	      edVolta2.setVL_Lancamento(vl_lancamento);
	      i2=1;
	      edVolta2.setNR_Sequencia2(i2);


	      if (edVolta.getNR_QT_Contas()>0) {
	         i++;
	         i1++;
		 edVolta.setNR_Sequencia1(i1);
	         list.add(edVolta);
	         list.add(edVolta2);


	      // System.out.println("add ->>" + i + "-" + edVolta.getDM_Tipo_Lancamento());

		  sql = "select * from Compromissos, tipos_documentos, Lotes_Compromissos  "+
		  " WHERE compromissos.oid_tipo_documento = tipos_Documentos.oid_tipo_documento "+
		  " AND   Lotes_Compromissos.oid_compromisso = compromissos.oid_compromisso "+
		  " AND   Lotes_Compromissos.oid_Lote_Pagamento = " + res.getString("oid_lote_pagamento");
		  resComp = this.executasql.executarConsulta(sql);
		  while (resComp.next()){
			  i2++;
			  sql = "select  NM_Conta_Contabil  FROM Fornecedores  WHERE Fornecedores.oid_Pessoa = '" + resComp.getString("oid_Pessoa") + "'";
		 // // System.err.println(sql);

			  res2 = this.executasql.executarConsulta(sql);
			  cta_fornecedor="02409";
			  while (res2.next()){
			      if ( res2.getString("NM_Conta_Contabil") != null &&
				  !res2.getString("NM_Conta_Contabil").equals("null") &&
				  !res2.getString("NM_Conta_Contabil").equals("") )
				  cta_fornecedor=res2.getString("NM_Conta_Contabil");
			  }
			  sql = "select  NM_Razao_Social  FROM Pessoas  WHERE Pessoas.oid_Pessoa = '" + resComp.getString("oid_Pessoa") + "'";
		  //// System.err.println(sql);

			  res2 = this.executasql.executarConsulta(sql);
			  nm_razao_social="";
			  while (res2.next()){
			     nm_razao_social=res2.getString("NM_Razao_Social");
			  }


                          /// 1 - pagamento
                          EDI_ContabilED edVolta3 = new EDI_ContabilED();
			  edVolta3.setDM_Tipo_Lancamento("LC2");
			  edVolta3.setNR_Documento("");
			  edVolta3.setDT_Movimento("");
		          edVolta3.setDM_Debito_Credito("D");

			  vl_lancamento=(resComp.getDouble("vl_pagamento")+resComp.getDouble("vl_desconto") - (resComp.getDouble("vl_multa_pagamento")+resComp.getDouble("vl_juros_pagamento")+resComp.getDouble("vl_outras_despesas")));

			  edVolta3.setVL_Lancamento(vl_lancamento);
			  edVolta3.setNR_Sequencia2(i2);
			  edVolta3.setCD_Acesso(cta_fornecedor);
			  edVolta3.setNM_Historico("Pgto :"+ resComp.getString("NM_Tipo_Documento") +" " + resComp.getString("NR_Documento") +"/"  + resComp.getString("NR_Parcela")+ "-" + nm_razao_social );
                          if ("E".equals(resComp.getString("DM_Situacao"))) {
	                     edVolta3.setCD_Acesso("0" + res.getString("CD_Conta_Contabil"));
			     edVolta3.setNM_Historico("Estorno :"+ resComp.getString("NM_Tipo_Documento") +" " + resComp.getString("NR_Documento") +"/"  + resComp.getString("NR_Parcela")+ "-" + nm_razao_social );
                          }

			  list.add(edVolta3);
                          //////////////


                          /// 2 - juros e multa
                          vl_lancamento=resComp.getDouble("vl_multa_pagamento")+resComp.getDouble("vl_juros_pagamento")+resComp.getDouble("vl_outras_despesas");
                          if (vl_lancamento>0) {
                            i2++;
                            edVolta3 = new EDI_ContabilED();
                            edVolta3.setDM_Tipo_Lancamento("LC2");
                            edVolta3.setNR_Documento("");
                            edVolta3.setDT_Movimento("");
                            edVolta3.setDM_Debito_Credito("D");
                            edVolta3.setVL_Lancamento(vl_lancamento);
                            edVolta3.setNR_Sequencia2(i2);
                            edVolta3.setCD_Acesso("05311");
                            edVolta3.setNM_Historico("Juros :"+ resComp.getString("NM_Tipo_Documento") +" " + resComp.getString("NR_Documento") +"/"  + resComp.getString("NR_Parcela")+ "-" + nm_razao_social );
                            list.add(edVolta3);
                          }
                          //////////////



                          /// 3 - desconto
                          vl_lancamento=resComp.getDouble("vl_desconto");
                          if (vl_lancamento>0) {
                            i2++;
                            edVolta3 = new EDI_ContabilED();
                            edVolta3.setDM_Tipo_Lancamento("LC2");
                            edVolta3.setNR_Documento("");
                            edVolta3.setDT_Movimento("");
                            edVolta3.setDM_Debito_Credito("C");
                            edVolta3.setVL_Lancamento(vl_lancamento);
                            edVolta3.setNR_Sequencia2(i2);
                            edVolta3.setCD_Acesso("05353");
                            edVolta3.setNM_Historico("Desconto :"+ resComp.getString("NM_Tipo_Documento") +" " + resComp.getString("NR_Documento") +"/"  + resComp.getString("NR_Parcela")+ "-" + nm_razao_social );
                            list.add(edVolta3);
                          }
                          //////////////


	      i++;
	      // System.out.println("add ->>" + i + "-" + edVolta2.getDM_Tipo_Lancamento());

		  }
	      }

      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao gerar arquivo");
      excecoes.setMetodo("lista(CompromissoED)");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return list;
  }


  public ArrayList gera_EDI_Liquidacoes(EDI_ContabilED edComp)throws Excecoes{

  String sql = null;
  ArrayList list = new ArrayList();
  EDI_ContabilED ed = (EDI_ContabilED)edComp;
  int i1=0;
    try{

      sql = "select Duplicatas.OID_Duplicata, Pessoas.oid_pessoa, Pessoas.nr_cnpj_cpf, Clientes.NR_Conta_Contabil, "+
      "Pessoas.nm_razao_Social, Pessoas_Bancos.nm_razao_Social as "+
      "NM_Razao_Banco, Carteiras.oid_Carteira, Carteiras.cd_Carteira, movimentos_duplicatas.DT_Movimento, movimentos_duplicatas.VL_Credito, movimentos_duplicatas.VL_Debito, movimentos_duplicatas.oid_movimento_duplicata,  movimentos_duplicatas.oid_Bordero, "+
      "Contas_Correntes.NR_conta_corrente, Tipos_Documentos.oid_tipo_documento, "+
      "Tipos_Documentos.cd_tipo_documento, Tipos_Documentos.nm_tipo_documento, Tipos_Instrucoes.NM_Tipo_Instrucao, Tipos_Instrucoes.DM_Altera_Titulo, tipos_instrucoes.cd_valor, "+
      "Duplicatas.nr_Duplicata, Duplicatas.nr_parcela, Duplicatas.nr_documento, Duplicatas.nr_bancario, "+
      "Duplicatas.dt_vencimento, Duplicatas.vl_saldo, Duplicatas.dt_emissao, Duplicatas.vl_duplicata, Duplicatas.vl_desconto_faturamento, "+
      "Duplicatas.vl_saldo from Duplicatas, pessoas, Pessoas Pessoas_Bancos, "+
      " tipos_documentos, Clientes, Carteiras, Contas_Correntes, Movimentos_Duplicatas, tipos_instrucoes, Unidades where "+
      " duplicatas.oid_pessoa = pessoas.oid_pessoa and Pessoas.oid_Pessoa = Clientes.oid_Cliente and "+
      " Movimentos_Duplicatas.oid_Carteira = Carteiras.oid_Carteira and"+
      " duplicatas.oid_tipo_documento = tipos_documentos.oid_tipo_documento and"+
      " Duplicatas.oid_Unidade = Unidades.oid_Unidade and"+
      " movimentos_Duplicatas.oid_duplicata = duplicatas.oid_duplicata and "+
      " Movimentos_Duplicatas.oid_tipo_instrucao = tipos_instrucoes.oid_tipo_instrucao and "+
      " carteiras.oid_conta_corrente = contas_correntes.oid_conta_corrente and "+
      " contas_correntes.oid_pessoa = pessoas_bancos.oid_pessoa and"+
      " movimentos_duplicatas.vl_Credito > 0 " ;
//      " and movimentos_duplicatas.oid_Bordero > 0 ";

      if (ed.getOid_Empresa () > 0) {
        sql += " and Unidades.OID_Empresa = " + ed.getOid_Empresa ();
      }


      if (ed.getDT_Inicial() != null)
        sql += " and movimentos_duplicatas.DT_Movimento >= '" + ed.getDT_Inicial() + "'";

      if (ed.getDT_Final() != null)
        sql += " and movimentos_duplicatas.DT_Movimento <= '" + ed.getDT_Final() + "'";


            // System.err.println(sql);

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      FormataDataBean dataFormatada = new FormataDataBean();
      double vl_lancamento=0;
      String cta_cliente="";
      while (res.next()){
          cta_cliente="00401";
          EDI_ContabilED edVolta = new EDI_ContabilED();
          edVolta.setDM_Tipo_Lancamento("LC1");
          edVolta.setDM_Modo_Lancamento("1");


          edVolta.setNR_Documento(res.getString("NR_Duplicata"));
          edVolta.setNR_Bordero(res.getString("oid_Bordero"));
          dataFormatada.setDT_FormataData(res.getString("DT_Movimento"));
          edVolta.setDT_Movimento(dataFormatada.getDT_FormataData());
          vl_lancamento=(res.getDouble("vl_duplicata") - res.getDouble("vl_desconto_faturamento"));

          if ( res.getString("NR_Conta_Contabil") != null &&
              !res.getString("NR_Conta_Contabil").equals("null") &&
              !res.getString("NR_Conta_Contabil").equals("") )
              cta_cliente=res.getString("NR_Conta_Contabil");

          if (res.getString("DM_Altera_Titulo").equals("LTO") ||
              res.getString("DM_Altera_Titulo").equals("LCA")
          ){
              edVolta.setNM_Conta_Debito("00310");
              edVolta.setNM_Conta_Credito(cta_cliente);
              edVolta.setNM_Historico("Liquidacao Titulo:"  + res.getString("NR_Duplicata")+ "/" + res.getString("nm_razao_Social") );
              edVolta.setVL_Lancamento(res.getDouble("VL_Credito"));
          }
          if (res.getString("DM_Altera_Titulo").equals("LPA")
          ){
              edVolta.setNM_Conta_Debito("00310");
              edVolta.setNM_Conta_Credito(cta_cliente);
              edVolta.setNM_Historico("Liquidacao Parcial Titulo:"  + res.getString("NR_Duplicata")+ "/" + res.getString("nm_razao_Social") );
              edVolta.setVL_Lancamento(res.getDouble("VL_Credito"));
          }


          if (res.getString("DM_Altera_Titulo").equals("RMJ")) {//JUROS /MULTA
              edVolta.setNM_Conta_Credito("05351");
              edVolta.setNM_Conta_Debito("00310");
              edVolta.setNM_Historico("Juros Recebidos Titulo: " + res.getString("NR_Duplicata")+ "/" + res.getString("nm_razao_Social") );
              edVolta.setVL_Lancamento(res.getDouble("VL_Credito"));
          }

          if (res.getString("DM_Altera_Titulo").equals("RIF") && res.getDouble("VL_Credito")>0) {//RETENCAO IMPOSTO FEDERAL
              edVolta.setNM_Conta_Credito("00613");
              edVolta.setNM_Conta_Debito("00310");
              edVolta.setNM_Historico("Retenção Imposto Federal: " + res.getString("NR_Duplicata")+ "/" + res.getString("nm_razao_Social") );
              edVolta.setVL_Lancamento(res.getDouble("VL_Credito"));
          }

          if (res.getString("DM_Altera_Titulo").equals("RIM") && res.getDouble("VL_Credito")>0) {//RETENCAO IMPOSTO MINIC
              edVolta.setNM_Conta_Credito("00614");
              edVolta.setNM_Conta_Debito("00310");
              edVolta.setNM_Historico("Retenção Imposto Municipal: " + res.getString("NR_Duplicata")+ "/" + res.getString("nm_razao_Social") );
              edVolta.setVL_Lancamento(res.getDouble("VL_Credito"));
          }


          if (res.getString("DM_Altera_Titulo").equals("SDA")||res.getString("DM_Altera_Titulo").equals("CDA")) {//Desconto
              edVolta.setNM_Conta_Debito("05314");
              edVolta.setNM_Conta_Credito(cta_cliente);
              edVolta.setNM_Historico("Desconto Concedido Titulo: " + res.getString("NR_Duplicata")+ "/" + res.getString("nm_razao_Social") );
              edVolta.setVL_Lancamento(res.getDouble("VL_Credito"));
          }
          if (res.getString("DM_Altera_Titulo").equals("SET")) {//Estorno
              edVolta.setNM_Conta_Debito("00415");
              edVolta.setNM_Conta_Credito(cta_cliente);
              edVolta.setNM_Historico("Estorno Conhecimento: " + res.getString("NR_Duplicata")+ "/" + res.getString("nm_razao_Social") );
              edVolta.setVL_Lancamento(res.getDouble("VL_Credito"));
          }

          if (edVolta.getVL_Lancamento()>0)
              i1++;
	      edVolta.setNR_Sequencia1(i1);
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

 public ArrayList gera_EDI_Movimentos_Contas_Correntes(EDI_ContabilED edComp)throws Excecoes{

   String sql = null;
   ArrayList list = new ArrayList ();
   EDI_ContabilED ed = (EDI_ContabilED) edComp;
   String cta_conta_corrente = "";
   FormataDataBean dataFormatada = new FormataDataBean ();
   double vl_lancamento = 0;

   long NR_QT_Contas = 0;
   int i1=0, i2=0;

    try{

      sql = "select Movimentos_Contas_Correntes.*, " +
      "             Tipos_documentos.*,            " +
      "             Historicos.nm_historico,            " +
      "             contas_correntes.cd_conta_corrente ," +
      "             contas.cd_conta_contabil           as cd_conta_contabil_cc ," +
      "             conta_movimento.cd_conta_contabil  as cd_conta_contabil_movimento" +
      " FROM  Historicos, Tipos_documentos, Movimentos_Contas_Correntes , contas_correntes, contas, contas conta_movimento "+
      " WHERE Movimentos_Contas_Correntes.oid_tipo_documento = tipos_Documentos.oid_tipo_documento "+
      " AND   Movimentos_Contas_Correntes.oid_historico = Historicos.oid_historico "+
      " AND   Movimentos_Contas_Correntes.oid_conta_corrente = contas_correntes.oid_conta_corrente "+
      " AND   contas_correntes.oid_Conta = contas.oid_Conta " +
      " AND   Movimentos_Contas_Correntes.oid_Conta = conta_movimento.oid_Conta " +
      " AND   conta_movimento.oid_conta <> contas.oid_Conta " +
      " AND   Movimentos_Contas_Correntes.vl_lancamento >0  " ;
//      " AND   conta_movimento.dm_exporta <> 'N' ";

      if (ed.getCD_Conta_Corrente() != null &&
          !ed.getCD_Conta_Corrente().equals("null") &&
          !ed.getCD_Conta_Corrente().equals("")){
        sql += " and contas_correntes.cd_conta_corrente = '" + ed.getCD_Conta_Corrente() + "'";
      }


      if (ed.getOid_Empresa() >0)
        sql += " and contas_correntes.oid_Empresa = " + ed.getOid_Empresa() ;


      if (String.valueOf(ed.getDT_Inicial()) != null &&
        !String.valueOf(ed.getDT_Inicial()).equals("") &&
        !String.valueOf(ed.getDT_Inicial()).equals("null")){
        sql += " and Movimentos_Contas_Correntes.DT_Movimento_Conta_Corrente >= '" + ed.getDT_Inicial() + "'";
      }
      if (String.valueOf(ed.getDT_Final()) != null &&
      !String.valueOf(ed.getDT_Final()).equals("") &&
      !String.valueOf(ed.getDT_Final()).equals("null")){
        sql += " and Movimentos_Contas_Correntes.DT_Movimento_Conta_Corrente <= '" + ed.getDT_Final() + "'";
      }
      sql += " ORDER BY Movimentos_Contas_Correntes.DT_Movimento_Conta_Corrente" ;
 // System.out.println(sql);

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      //popula
      while (res.next()){
        String gera="S";
        if (res.getInt("oid_tipo_Documento") ==46 && "C".equals(res.getString("dm_debito_credito"))){
          //transferencia a credito
          gera="N";
        }
        if ("S".equals(gera)){
          // System.err.println ("achou comp ");

          EDI_ContabilED edVolta = new EDI_ContabilED ();
          edVolta.setDM_Tipo_Lancamento ("LC1");
          edVolta.setDM_Modo_Lancamento ("2");

          edVolta.setNR_Documento (" ");
          dataFormatada.setDT_FormataData (res.getString ("DT_Movimento_Conta_Corrente"));
          edVolta.setDT_Movimento (dataFormatada.getDT_FormataData ());

          edVolta.setNM_Conta_Debito (" ");
          edVolta.setNM_Conta_Credito (" ");
          edVolta.setNM_Historico (" ");
          edVolta.setVL_Lancamento (0.0);

          NR_QT_Contas = 2;

          edVolta.setNR_QT_Contas (NR_QT_Contas);
          i1++;
          i2=0;
          edVolta.setNR_Sequencia1 (i1);


          list.add (edVolta);

          /// LC2
          EDI_ContabilED edVolta2 = new EDI_ContabilED ();
          edVolta2.setDM_Tipo_Lancamento ("LC2");
          edVolta2.setNR_Documento (" ");
          dataFormatada.setDT_FormataData (res.getString ("DT_Movimento_Conta_Corrente"));
          edVolta2.setDT_Movimento (dataFormatada.getDT_FormataData ());
          vl_lancamento = (res.getDouble ("vl_lancamento"));
          edVolta2.setDM_Debito_Credito ("C");
          if ("C".equals (res.getString ("dm_debito_credito"))) {
            edVolta2.setCD_Acesso ("00310");
          }
          else {
            edVolta2.setCD_Acesso ("0" + res.getString ("cd_conta_contabil_cc"));
          }
          edVolta2.setNM_Historico (res.getString ("nm_historico") + "-" + res.getString ("nm_complemento_historico"));
          edVolta2.setVL_Lancamento (vl_lancamento);
          i2++;
          edVolta2.setNR_Sequencia2 (i2);

          list.add (edVolta2);

          /// LC2
          EDI_ContabilED edVolta3 = new EDI_ContabilED ();
          edVolta3.setDM_Tipo_Lancamento ("LC2");
          edVolta3.setNR_Documento (res.getString ("NR_Documento"));
          dataFormatada.setDT_FormataData (res.getString ("DT_Movimento_Conta_Corrente"));
          edVolta3.setDT_Movimento (dataFormatada.getDT_FormataData ());
          vl_lancamento = (res.getDouble ("vl_lancamento"));
          edVolta3.setDM_Debito_Credito ("D");

          if ("C".equals (res.getString ("dm_debito_credito"))) {
            edVolta3.setCD_Acesso ("0" + res.getString ("cd_conta_contabil_cc"));
          }
          else {
            edVolta3.setCD_Acesso ("0" + res.getString ("cd_conta_contabil_movimento"));
          }

          edVolta3.setNM_Historico (res.getString ("nm_historico") + "-" + res.getString ("nm_complemento_historico"));
          edVolta3.setVL_Lancamento (vl_lancamento);
          i2++;
          edVolta3.setNR_Sequencia2 (i2);

          list.add (edVolta3);
        }
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao gerar arquivo");
      excecoes.setMetodo("lista(CompromissoED)");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return list;
  }

 public ArrayList gera_EDI_Lancamentos_Contabeis(EDI_ContabilED edComp)throws Excecoes{

	ArrayList list = new ArrayList(); // Array para listar as contas
	ArrayList listMovctbRel = new ArrayList(); // Array para enviar para o relatório
	Movimento_ContabilED edMovCtbRel = new Movimento_ContabilED();
	try {

		// Busca a lista das contas
		ContaED edListaConta = new ContaED();
			edListaConta.setDM_Relatorio("3"); // Ordenado por codigo estrutural
		list = new ContaBD(this.executasql).lista(edListaConta); // Chama o método que retorna a lista
		// Itera a lista das contas ordenadas pelo código estrutural.
		for (int i=0;i<list.size();i++) {
			ContaED contaLst = (ContaED)list.get(i); // Pega a conta da lista
			// Monta parte do ed para o relatório com as informações básicas da conta
			edMovCtbRel = new Movimento_ContabilED();
			edMovCtbRel.setOid_Conta(contaLst.getOid_Conta().longValue());
			edMovCtbRel.setCd_Estrutural(contaLst.getCd_Estrutural());
			edMovCtbRel.setCd_Conta(contaLst.getCd_Conta());
			edMovCtbRel.setNm_Conta(JavaUtil.LFill(contaLst.getNm_Conta(), contaLst.getNm_Conta().length() + (contaLst.getNr_Grau().intValue() * 2), ' ', false));
			edMovCtbRel.setDt_Movimento_Inicial(edComp.getDT_Inicial());
			edMovCtbRel.setDt_Movimento_Final(edComp.getDT_Final());
			edMovCtbRel.setDt_Emissao(edComp.getDT_Emissao());
			edMovCtbRel.setDm_Tipo_Conta(contaLst.getDm_Tipo_Conta());
			edMovCtbRel.setNr_Grau(contaLst.getNr_Grau());
			if ("S".equals(contaLst.getDm_Tipo_Conta())) {
				//Conta sintetica inicia com saldo e debito e credito zerados
				edMovCtbRel.setVl_Saldo(0);
				edMovCtbRel.setVl_Debito(0);
				edMovCtbRel.setVl_Credito(0);
				edMovCtbRel.setVl_Saldo_Atual(0);
			}else{
				// Busca o saldo da conta e a soma do débito e crédito no período
				Movimento_ContabilED edBuscaSaldo = new Movimento_ContabilED();
				edBuscaSaldo.setOid_Conta(contaLst.getOid_Conta().longValue()); // Monta o oid da conta no ed
				edBuscaSaldo.setDt_Movimento_Inicial(edComp.getDT_Inicial()); // Monta a data inicial de busca no ed
				edBuscaSaldo.setDt_Movimento_Final(edComp.getDT_Final()); // Monta a data final de busca no ed
				edBuscaSaldo.setDm_Lista_Encerramento(edComp.getDm_Lista_Encerramento());

				Movimento_ContabilED edSaldo = new Movimento_ContabilED();
				Movimento_ContabilBD edSaldobd =  new Movimento_ContabilBD(this.executasql); // Chama o método que retorna o saldo
				edSaldo = edSaldobd.getSaldoConta(edBuscaSaldo); // Chama o método que retorna o saldo
				// Monta o saldo inicial do balancete no ed
				edMovCtbRel.setVl_Saldo(edSaldo.getVl_Saldo());
				// Busca a soma dos débitos e créditos dos lancamentos para o periodo informado com o ed da tela de filtro ctbF011.jsp
				Movimento_ContabilED  edSoma =  new Movimento_ContabilBD(this.executasql).getSomaConta(edBuscaSaldo); // Chama o método que retorna o saldo
				edMovCtbRel.setVl_Debito(edSoma.getVl_Debito());
				edMovCtbRel.setVl_Credito(edSoma.getVl_Credito());
				edMovCtbRel.setVl_Saldo_Atual(0);
			}
			listMovctbRel.add(edMovCtbRel); // Dá o add na lista de eds dos detalhes
		}

		// Varre o array para somar nas contas mãe
		for (int i=0;i<listMovctbRel.size();i++) {
			Movimento_ContabilED movLst = (Movimento_ContabilED)listMovctbRel.get(i);
			if ("A".equals(movLst.getDm_Tipo_Conta())){
				somaParaCima(listMovctbRel, movLst.getCd_Estrutural(), movLst.getVl_Saldo(), movLst.getVl_Debito(), movLst.getVl_Credito());
			}
		}
		// Cria as linhas do resumo de sinteticas
		int listMovctbRelSize = listMovctbRel.size();
		for (int i=0;i<listMovctbRelSize;i++) {
			Movimento_ContabilED movLst = (Movimento_ContabilED)listMovctbRel.get(i);
			if ("S".equals(movLst.getDm_Tipo_Conta())) {
				edMovCtbRel = new Movimento_ContabilED();
				edMovCtbRel.setOid_Conta(movLst.getOid_Conta());
				edMovCtbRel.setCd_Estrutural("X");
				edMovCtbRel.setCd_Conta(movLst.getCd_Estrutural());
				edMovCtbRel.setNm_Conta(movLst.getNm_Conta());
				edMovCtbRel.setDt_Movimento_Inicial(edComp.getDT_Inicial()); // Monta a data inicial de busca no ed
				edMovCtbRel.setDt_Movimento_Final(edComp.getDT_Final()); // Monta a data final de busca no ed
				edMovCtbRel.setDm_Tipo_Conta(movLst.getDm_Tipo_Conta());
				edMovCtbRel.setNr_Grau(movLst.getNr_Grau());
				edMovCtbRel.setVl_Saldo(movLst.getVl_Saldo());
				edMovCtbRel.setVl_Debito(movLst.getVl_Debito());
				edMovCtbRel.setVl_Credito(movLst.getVl_Credito());
				listMovctbRel.add(edMovCtbRel);
			}
		}

	} catch (Exception e) {
	  e.printStackTrace();
	  throw new Excecoes("erro ao exportar Lancamentos.");
	}
    return listMovctbRel; // Dá o add na lista de eds dos detalhes

  }

	private void somaParaCima(ArrayList l,String ce, double vls, double vld, double vlc){
		ContaED edConta = new ContaED();
		edConta.setCd_Estrutural(ce);
		String cdPai =  edConta.pegaGrauInferior();
		if (! "*".equals(cdPai) ) {
			for ( int i=0;i<l.size();i++){
				Movimento_ContabilED movLst = (Movimento_ContabilED)l.get(i);
				if (cdPai.equals(movLst.getCd_Estrutural())) {
					movLst.setVl_Saldo(Valor.round(movLst.getVl_Saldo() + vls,2));
					movLst.setVl_Debito(Valor.round(movLst.getVl_Debito() + vld,2));
					movLst.setVl_Credito(Valor.round(movLst.getVl_Credito() + vlc,2));
					l.set(i,movLst);
					break;
				}
			}
			this.somaParaCima( l, cdPai,  vls, vld, vlc);
		}
	}

	public ArrayList gera_EDI_Lancamentos_Contabeis_old(EDI_ContabilED edComp)throws Excecoes{

	   String sql = null;
	   ArrayList list = new ArrayList();
	   EDI_ContabilED ed = (EDI_ContabilED)edComp;

	     try{
	    	 sql = " SELECT " +
	    	 	   " Movimentos_Contabeis.nr_lote, " +
	    	 	   " Movimentos_Contabeis.dt_movimento, " +
	    	 	   " Movimentos_Contabeis.dm_debito_credito, " +
	    	 	   " Movimentos_Contabeis.vl_lancamento, " +
	    	 	   " Movimentos_Contabeis.tx_historico_complementar, " +
	    	 	   " Unidades.cd_unidade, " +
	    	 	   " Contas.cd_conta, " +
	    	 	   " Contas.cd_estrutural, " +
	    	 	   " Origens.NM_Origem, " +
	    	 	   " Historicos.cd_historico ";
	    	 sql +=" FROM Movimentos_Contabeis, Unidades, Contas, Historicos, Origens ";
	    	 sql +=" WHERE Movimentos_Contabeis.oid_unidade = Unidades.oid_unidade " +
	    	 	   " AND   Movimentos_Contabeis.oid_conta = Contas.oid_conta " +
	    	 	   " AND   Movimentos_Contabeis.oid_historico = Historicos.oid_historico " +
	    	 	   " AND   Movimentos_Contabeis.oid_Origem = Origens.oid_Origem " +
	    	 	   " AND   Movimentos_Contabeis.dt_movimento >= '" + ed.getDT_Inicial() + "'" +
	               " AND   Movimentos_Contabeis.dt_movimento <= '" + ed.getDT_Final() + "'" ;

	    	 if (ed.getOid_Unidade() >0)
	    		 sql += " and Unidades.oid_Unidade = " + ed.getOid_Unidade() ;

	    	 if (ed.getOid_Origem() >0)

	    		 if (ed.getOid_Origem() == 20){
		    		 sql += " and Movimentos_Contabeis.TX_Chave_Origem = 'DIGITACAO' " ;
	    		 }else if (ed.getOid_Origem() == 21){
		    		 sql += " and Movimentos_Contabeis.oid_Origem > 0 ";
	    		 }else{
		    		 sql += " and Movimentos_Contabeis.oid_Origem = " + ed.getOid_Origem() ;
	    		 }

	    	 sql +=" Order by contas.cd_estrutural, contas.cd_conta, Movimentos_Contabeis.dt_movimento, Movimentos_Contabeis.tx_chave_origem, Movimentos_Contabeis.dm_debito_credito ";

	    	 ResultSet res = null;
	         res = this.executasql.executarConsulta(sql);
	         while (res.next()){
	        	 EDI_ContabilED edVolta = new EDI_ContabilED();
	             edVolta.setDT_Movimento(dataFormatada.getDT_FormataData(res.getString("dt_movimento")));
	             edVolta.setNR_Acerto(res.getLong("nr_lote"));
	             edVolta.setVL_Movimento(res.getDouble("vl_lancamento"));
	             edVolta.setTX_Observacao(SeparaEndereco.corrigeString(res.getString("tx_historico_complementar")));
	             edVolta.setCD_Unidade_Contabil(res.getString("cd_unidade"));
	             edVolta.setCD_Conta_Estrutural(res.getString("cd_estrutural"));
	             edVolta.setCD_Conta(res.getString("cd_conta"));
	             edVolta.setCD_Historico(res.getString("cd_historico"));
	             edVolta.setDM_Debito_Credito(res.getString("dm_debito_credito"));
	             edVolta.setNM_Especie(res.getString("NM_Origem"));
	             list.add(edVolta);
	         }

	     } catch (Exception e) {
	    	 e.printStackTrace();
	    	 throw new Excecoes("erro ao exportar Lancamentos.");
		}
	     return list;
}


}
