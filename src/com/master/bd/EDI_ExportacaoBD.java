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

import com.master.ed.DuplicataED;
import com.master.ed.EDI_ExportacaoED;
import com.master.ed.EDI_Nota_FiscalED;
import com.master.ed.Item_Nota_Fiscal_TransacoesED;
import com.master.ed.Nota_Fiscal_EletronicaED;
import com.master.root.CidadeBean;
import com.master.root.FormataDataBean;
import com.master.root.PessoaBean;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Utilitaria;
import com.master.util.bd.ExecutaSQL;
import com.master.util.bd.Transacao;

public class EDI_ExportacaoBD
    extends Transacao {

  private ExecutaSQL executasql;

  public EDI_ExportacaoBD (ExecutaSQL sql) {
    this.executasql = sql;
  }

  public ArrayList gera_EDI_Conhecimento (EDI_ExportacaoED edComp) throws Excecoes {

	    String sql = null;
	    ArrayList list = new ArrayList ();
	    EDI_ExportacaoED ed = (EDI_ExportacaoED) edComp;

	    try {

	      sql = " select " +
	          " Conhecimentos.NM_Serie as NM_Serie_Conhecimento, " +
	          " Conhecimentos.oid_Conhecimento, " +
	          " Conhecimentos.oid_Pessoa, " +
	          " Conhecimentos.oid_Pessoa_Pagador, " +
	          " Conhecimentos.oid_Pessoa_Destinatario, " +
	          " Conhecimentos.oid_Pessoa_Consignatario, " +
	          " Conhecimentos.oid_Pessoa_Redespacho, " +
	          " Conhecimentos.oid_Pessoa_Entregadora, " +
	          " Conhecimentos.oid_Cidade_Destino, " +
	          " Conhecimentos.DM_Tipo_Conhecimento, " +
	          " Conhecimentos.DT_Previsao_Entrega, " +
	          " Conhecimentos.HR_Previsao_Entrega, " +
	          " Conhecimentos.NM_Natureza, " +
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
	          " Conhecimentos.nm_liberacao_veiculo, " +
	          " Conhecimentos.VL_Nota_Fiscal_Seguro, " +
	          " Conhecimentos.VL_Nota_Fiscal as VL_Nota_Fiscal_Conhecimento, " +
	          " Conhecimentos.NR_Volumes as NR_Volumes_Conhecimento, " +
	          " Conhecimentos.oid_Produto as oid_Produto_Conhecimento, " +
	          " Modal.CD_Modal, " +
	          " Modal.DM_Tipo_Transporte, " +
	          " Unidade_Conhecimento.CD_Unidade as CD_Unidade_Conhecimento, " +
	          " Unidade_Conhecimento.oid_Pessoa as oid_Pessoa_Unidade, " +
	          " Notas_Fiscais.NM_Serie as NM_Serie_Nota_Fiscal, " +
	          " Notas_Fiscais.NR_Nota_Fiscal, " +
	          " Notas_Fiscais.oid_Nota_Fiscal, " +
	          " Notas_Fiscais.NR_Pedido, " +
	          " Notas_Fiscais.DT_Emissao as DT_Emissao_Nota_Fiscal, " +
	          " Notas_Fiscais.NR_Peso as NR_Peso_Nota_Fiscal,  " +
	          " Notas_Fiscais.NR_Volumes as NR_Volumes_Nota_Fiscal,  " +
	          " Notas_Fiscais.NR_Peso_Cubado as NR_Peso_Cubado_Nota_Fiscal,  " +
	          " Notas_Fiscais.VL_Nota_Fiscal as VL_Nota_Fiscal_Nota_Fiscal ";
	      sql+=" FROM  Conhecimentos, Conhecimentos_Notas_Fiscais, Modal, Notas_Fiscais, Unidades Unidade_Conhecimento ";
	      sql+=" WHERE Conhecimentos.oid_Conhecimento = Conhecimentos_Notas_Fiscais.oid_conhecimento " +
	          " AND   Conhecimentos_Notas_Fiscais.oid_Nota_Fiscal = Notas_Fiscais.oid_Nota_Fiscal " +
	          " AND   Conhecimentos.oid_unidade = Unidade_Conhecimento.oid_Unidade " +
	          " AND   Conhecimentos.oid_Modal = Modal.oid_Modal " +
	          " AND   Conhecimentos.DM_Impresso = 'S' " +
	          " AND   Conhecimentos.VL_Total_Frete > 0" +
	          " AND   Unidade_Conhecimento.oid_Empresa = " + ed.getOid_Empresa () +
	          " AND   Conhecimentos.DT_Emissao >= '" + ed.getDT_Inicial () + "'" +
	          " AND   Conhecimentos.DT_Emissao <= '" + ed.getDT_Final () + "'";

	      if (ed.getOid_Pessoa_Entregadora()!=null && ed.getOid_Pessoa_Entregadora().length()>4) {
	    	  //edi para parceiros/entregadores
		       sql += " AND   Conhecimentos.Oid_Pessoa_Entregadora ='" + ed.getOid_Pessoa_Entregadora() + "'";

	      }else {
	    	  //edi para clientes
	          sql +=" AND   Conhecimentos.DM_Tipo_Documento = 'C' ";

	          boolean opcao_cli=false;
		      if ("E".equals (ed.getDM_Tipo_Frete ())) {
				     sql += " AND  (Conhecimentos.oid_Pessoa in " + getByGrupo_Economico (ed);
				     sql += " OR   Conhecimentos.oid_Pessoa_Destinatario in " + getByGrupo_Economico (ed) + ")";
				     opcao_cli=true;
			  }
		      if ("EC".equals (ed.getDM_Tipo_Frete ())) {
				     sql += " AND   Conhecimentos.oid_Pessoa in " + getByGrupo_Economico (ed);
				     sql += " AND   Conhecimentos.DM_Tipo_Pagamento='1' " ;
				     opcao_cli=true;
			  }
		      if ("EF".equals (ed.getDM_Tipo_Frete ())) {
				     sql += " AND   Conhecimentos.oid_Pessoa in " + getByGrupo_Economico (ed);
				     sql += " AND   Conhecimentos.DM_Tipo_Pagamento<>'1' " ;
				     opcao_cli=true;
			  }
		      if ("R".equals (ed.getDM_Tipo_Frete ())) {
				     sql += " AND   Conhecimentos.oid_Pessoa_Destinatario in " + getByGrupo_Economico (ed);
				     opcao_cli=true;
			  }
		      if ("RC".equals (ed.getDM_Tipo_Frete ())) {
				     sql += " AND   Conhecimentos.oid_Pessoa_Destinatario in " + getByGrupo_Economico (ed);
				     sql += " AND   Conhecimentos.DM_Tipo_Pagamento='1' " ;
				     opcao_cli=true;
			  }
		      if ("RF".equals (ed.getDM_Tipo_Frete ())) {
				     sql += " AND   Conhecimentos.oid_Pessoa_Destinatario in " + getByGrupo_Economico (ed);
				     sql += " AND   Conhecimentos.DM_Tipo_Pagamento<>'1' " ;
				     opcao_cli=true;
			  }
		      if ("P".equals (ed.getDM_Tipo_Frete ())) {
				     sql += " AND   Conhecimentos.oid_Pessoa_Pagador in " + getByGrupo_Economico (ed);
				     opcao_cli=true;
		      }
		      if ("PC".equals (ed.getDM_Tipo_Frete ())) {
				     sql += " AND   Conhecimentos.oid_Pessoa_Pagador in " + getByGrupo_Economico (ed);
				     sql += " AND   Conhecimentos.DM_Tipo_Pagamento='1' " ;
				     opcao_cli=true;
			  }
		      if ("PF".equals (ed.getDM_Tipo_Frete ())) {
				     sql += " AND   Conhecimentos.oid_Pessoa_Pagador in " + getByGrupo_Economico (ed);
				     sql += " AND   Conhecimentos.DM_Tipo_Pagamento<>'1' " ;
				     opcao_cli=true;
			  }
		      if (!opcao_cli) {
			          sql += " AND   Conhecimentos.oid_Pessoa in " + getByGrupo_Economico (ed);
			  }
	      }

	      if (ed.getOid_Unidade () > 0) {
	        sql += " AND Conhecimentos.oid_Unidade = " + ed.getOid_Unidade ();
	      }

	      sql += " Order by  Conhecimentos.NR_Conhecimento, Notas_Fiscais.NR_NOta_Fiscal ";

//	       System.out.println ("==============> " + sql);

	      ResultSet res = null;
	      ResultSet res2 = null;

	      res = this.executasql.executarConsulta (sql);

	      FormataDataBean dataFormatada = new FormataDataBean ();
	      double vl_icms = 0;
	      while (res.next ()) {
	        EDI_ExportacaoED edVolta = new EDI_ExportacaoED ();
	        // System.err.println ("1");
	        edVolta.setDM_Tipo_Pagamento ("C");
	        if (!res.getString ("oid_Pessoa").equals (res.getString ("oid_Pessoa_Pagador"))) {
	          edVolta.setDM_Tipo_Pagamento ("F");
	        }

	        edVolta.setOid_Pessoa (res.getString ("oid_Pessoa"));

	        edVolta.setOid_Pessoa_Destinatario (res.getString ("oid_Pessoa_Destinatario"));
	        edVolta.setOid_Pessoa_Consignatario (res.getString ("oid_Pessoa_Consignatario"));
	        edVolta.setOid_Pessoa_Redespacho (res.getString ("oid_Pessoa_Redespacho"));
	        edVolta.setOid_Pessoa_Entregadora (res.getString ("oid_Pessoa_Entregadora"));

	        edVolta.setOid_Pessoa_Pagador (res.getString ("oid_Pessoa_Pagador"));

	        edVolta.setOid_Pessoa_Unidade (res.getString ("oid_Pessoa_Unidade"));

	        edVolta.setOID_Cidade_Destino(res.getInt("oid_Cidade_Destino"));

	        edVolta.setNM_Filial_Conhecimento (res.getString ("CD_Unidade_Conhecimento"));
	        edVolta.setNR_Serie_Conhecimento (res.getString ("NM_Serie_Conhecimento"));
	        edVolta.setNR_Conhecimento (res.getString ("NR_Conhecimento"));
	        // System.err.println ("2");

	        dataFormatada.setDT_FormataData (res.getString ("DT_Emissao"));
	        edVolta.setDT_Emissao_Conhecimento (dataFormatada.getDT_FormataData ());

	        dataFormatada.setDT_FormataData (res.getString ("DT_Previsao_Entrega"));
	        edVolta.setDT_Previsao_Entrega (dataFormatada.getDT_FormataData ());

	        edVolta.setHR_Previsao_Entrega (res.getString ("HR_Previsao_Entrega"));
	        edVolta.setTX_Observacao (res.getString ("TX_Observacao"));
	        edVolta.setNR_Duplicata (res.getString ("NR_Duplicata"));

	        edVolta.setNM_Identificador_Registro(new Utilitaria().getValueDef(res.getString("NM_liberacao_veiculo"), "0"));

	        edVolta.setDM_Tipo_Conhecimento (res.getString ("DM_Tipo_Conhecimento"));
	        edVolta.setDM_Tipo_Transporte (res.getString ("DM_Tipo_Transporte"));
	        if ("KMD".equals (res.getString ("CD_Modal"))) {
	          edVolta.setDM_Tipo_Transporte ("D");
	        }

	        // System.err.println ("3 ok ");
	        edVolta.setVL_Total_Frete (res.getDouble ("VL_Total_Frete"));
	        edVolta.setVL_ICMS (res.getDouble ("VL_ICMS"));
	        if (edVolta.getVL_ICMS () > 0) {
	          edVolta.setDM_Substituicao_Tributaria ("N");
	          edVolta.setVL_Base_Calculo_ICMS (res.getDouble ("VL_Base_Calculo_ICMS"));
	          edVolta.setPE_Aliquota_ICMS (res.getDouble ("PE_ALIQUOTA_ICMS"));
	        }
	        else {
	          edVolta.setDM_Substituicao_Tributaria ("S");
	          edVolta.setVL_Base_Calculo_ICMS (0);
	          edVolta.setPE_Aliquota_ICMS (0);
	        }

	        edVolta.setVL_Frete_Peso (res.getDouble ("VL_Frete_Peso"));
	        edVolta.setVL_Frete_Valor (res.getDouble ("VL_Frete_Valor"));
	        // System.err.println ("4 ICMS " + res.getDouble ("VL_ICMS"));
	        edVolta.setVL_Despacho (res.getDouble ("VL_Despacho"));
	        edVolta.setVL_Pedagio (res.getDouble ("VL_Pedagio"));
	        // System.err.println ("4 b");
	        edVolta.setVL_Outros1 (res.getDouble ("VL_Outros1"));
	        edVolta.setVL_Ademe (res.getDouble ("VL_Outros2"));
	        // System.err.println ("4 c");

	        edVolta.setVL_SEC_CAT(res.getDouble ("vl_nota_fiscal_seguro"));

	        edVolta.setCD_CFO_Conhecimento (res.getString ("CD_CFO_Conhecimento"));

	        if ("W".equals(res.getString ("DM_Tipo_Conhecimento"))){
  	            edVolta.setNR_Peso_Conhecimento (0);
		        edVolta.setNR_Nota_Fiscal (" ");
		        edVolta.setNR_Serie_Nota_Fiscal (" ");
		        edVolta.setNR_Peso_Nota_Fiscal (0);
		        edVolta.setDT_Emissao_Nota_Fiscal (" ");
		        edVolta.setVL_Nota_Fiscal (0);
		        edVolta.setNR_Volume_Nota_Fiscal (0);
		        edVolta.setNM_Natureza (" ");

	        }else{
		        edVolta.setNR_Peso_Conhecimento (res.getDouble ("NR_Peso_Cubado"));
		        if (edVolta.getNR_Peso_Conhecimento () <= 0) {
		          edVolta.setNR_Peso_Conhecimento (res.getDouble ("NR_Peso"));
		        }
		        edVolta.setNR_Nota_Fiscal (res.getString ("NR_Nota_Fiscal"));
		        edVolta.setNR_Serie_Nota_Fiscal (res.getString ("NM_Serie_Nota_Fiscal"));
		        edVolta.setNR_Peso_Nota_Fiscal (res.getDouble ("NR_Peso_Cubado_Nota_Fiscal"));
		        if (edVolta.getNR_Peso_Nota_Fiscal () <= 0) {
		          edVolta.setNR_Peso_Nota_Fiscal (res.getDouble ("NR_Peso_Nota_Fiscal"));
		        }
		        dataFormatada.setDT_FormataData (res.getString ("DT_Emissao_Nota_Fiscal"));
		        edVolta.setDT_Emissao_Nota_Fiscal (dataFormatada.getDT_FormataData ());

		        edVolta.setVL_Nota_Fiscal (res.getDouble ("VL_Nota_Fiscal_Nota_Fiscal"));
		        edVolta.setNR_Volume_Nota_Fiscal (res.getDouble ("NR_Volumes_Nota_Fiscal"));
			    edVolta.setNM_Natureza (res.getString ("NM_Natureza"));
	        }

	        // System.err.println ("4 e");
	        edVolta.setNR_Pedido (res.getString ("NR_Pedido"));
	        // System.err.println ("5");


	        sql = " Select CD_Unidade from Unidades WHERE oid_Pessoa='" + res.getString ("oid_Pessoa_Entregadora") + "'";
	        res2 = this.executasql.executarConsulta (sql);
	        while (res2.next ()) {
	          edVolta.setCD_Unidade_Entregadora (res2.getString ("CD_Unidade"));
	        }

	        sql = " select " +
	            " Viagens.DT_Viagem, " +
	            " Viagens.HR_Viagem, " +
	            " Unidades.CD_Unidade, " +
	            " Manifestos.NR_Manifesto " +
	            " FROM Manifestos, Viagens, Unidades " +
	            " WHERE Manifestos.oid_Manifesto = Viagens.oid_Manifesto " +
	            " AND   Manifestos.oid_Unidade = Unidades.oid_unidade " +
	            //" AND   Manifestos.oid_Unidade_Destino = Unidades.oid_unidade " +
	            " AND   Viagens.oid_Conhecimento = '" + res.getString ("oid_Conhecimento") + "'";

	        //// System.err.println ("sql viagen " + sql);

	        res2 = this.executasql.executarConsulta (sql);
	        while (res2.next ()) {
	          // System.err.println ("tem viagem");
	          edVolta.setNR_Manifesto (res2.getString ("NR_Manifesto"));
	          dataFormatada.setDT_FormataData (res2.getString ("DT_Viagem"));
	          edVolta.setDT_Embarque (dataFormatada.getDT_FormataData ());
	          edVolta.setHR_Embarque (res2.getString ("HR_Viagem"));
	          edVolta.setNM_Unidade_Entregadora (res2.getString ("CD_Unidade"));
	        }

	        //Produtos, se necessário...
	        ArrayList produtos = new ArrayList();
	        if(JavaUtil.doValida(ed.getDM_Tipo_Padrao()) && "Muller".equals(ed.getDM_Tipo_Padrao())){

	        	// System.out.println("CTRC: "+res.getString("NR_Conhecimento") + " |" + res.getString("oid_Conhecimento"));

	        	sql= "SELECT notas_fiscais.nr_nota_fiscal, Produtos.nm_produto, itens_notas_fiscais.nr_qt_atendido, " +
	        		 " unidades_produtos.cd_unidade_produto, itens_notas_fiscais.vl_item ";
	        	sql+="FROM notas_fiscais, itens_notas_fiscais, produtos, unidades_produtos, conhecimentos_notas_fiscais ";
	        	sql+="WHERE notas_fiscais.oid_nota_fiscal = itens_notas_fiscais.oid_nota_fiscal " +
	 	  	   		 " AND itens_notas_fiscais.oid_produto = produtos.oid_produto " +
	 	  	   		 " AND produtos.oid_unidade_produto = unidades_produtos.oid_unidade_produto " +
	 	  	   		 " AND notas_fiscais.oid_nota_fiscal = conhecimentos_notas_fiscais.oid_nota_fiscal" +
	 	  	   		 " AND conhecimentos_notas_fiscais.oid_conhecimento = '" + res.getString("oid_Conhecimento") + "'" +
	 	  	   		 " ORDER BY notas_fiscais.nr_nota_fiscal ASC, itens_notas_fiscais.oid_item_nota_fiscal ASC ";
	        	res2 = this.executasql.executarConsulta(sql);

	        	boolean DM_Tem_Nota_Fiscal_Com_Item = false;
	        	while (res2.next()) {
	        		// System.out.println("NF: "+res2.getString("NR_Nota_Fiscal"));
	            	EDI_ExportacaoED prod = new EDI_ExportacaoED();
	    	        if ("W".equals(res.getString ("DM_Tipo_Conhecimento"))){
		            	prod.setNR_Nota_Fiscal(" ");
		            	prod.setNM_Produto(" ");
		            	prod.setNR_Quantidade(0);
		            	prod.setCD_Unidade_Medida(" ");
		            	prod.setVL_Produto(0);
	    	        }else{
		            	prod.setNR_Nota_Fiscal(res2.getString("nr_nota_fiscal"));
		            	prod.setNM_Produto(res2.getString("nm_produto"));
		            	prod.setNR_Quantidade(res2.getDouble("nr_qt_atendido"));
		            	prod.setCD_Unidade_Medida(res2.getString("cd_unidade_produto"));
		            	prod.setVL_Produto(res2.getDouble("vl_item"));
	    	        }

	            	produtos.add(prod);
	            	DM_Tem_Nota_Fiscal_Com_Item = true;
	            }

	        	if (!DM_Tem_Nota_Fiscal_Com_Item){

		        	sql= "SELECT notas_fiscais.nr_nota_fiscal, Produtos.nm_produto, notas_fiscais.nr_volumes as nr_qt_atendido, " +
		       		     " unidades_produtos.cd_unidade_produto, notas_fiscais.vl_nota_fiscal as vl_item ";
		       	    sql+="FROM notas_fiscais, produtos, unidades_produtos, conhecimentos_notas_fiscais ";
		       	    sql+="WHERE notas_fiscais.oid_produto = produtos.oid_produto " +
			  	   		 " AND produtos.oid_unidade_produto = unidades_produtos.oid_unidade_produto " +
			  	   		 " AND notas_fiscais.oid_nota_fiscal = conhecimentos_notas_fiscais.oid_nota_fiscal" +
			  	   		 " AND conhecimentos_notas_fiscais.oid_conhecimento = '" + res.getString("oid_Conhecimento") + "'" +
			  	   		 " ORDER BY notas_fiscais.nr_nota_fiscal ASC ";

		       	    res2 = this.executasql.executarConsulta(sql);

		       	    DM_Tem_Nota_Fiscal_Com_Item = false;
		       	    while (res2.next()) {
		       		   // System.out.println("NF: "+res2.getString("NR_Nota_Fiscal"));
		           	   EDI_ExportacaoED prod = new EDI_ExportacaoED();
		   	           if ("W".equals(res.getString ("DM_Tipo_Conhecimento"))){
			            	prod.setNR_Nota_Fiscal(" ");
			            	prod.setNM_Produto(" ");
			            	prod.setNR_Quantidade(0);
			            	prod.setCD_Unidade_Medida(" ");
			            	prod.setVL_Produto(0);
		   	           }else{
			            	prod.setNR_Nota_Fiscal(res2.getString("nr_nota_fiscal"));
			            	prod.setNM_Produto(res2.getString("nm_produto"));
			            	prod.setNR_Quantidade(res2.getDouble("nr_qt_atendido"));
			            	prod.setCD_Unidade_Medida(res2.getString("cd_unidade_produto"));
			            	prod.setVL_Produto(res2.getDouble("vl_item"));
		   	           }

	           	       produtos.add(prod);
	           	       DM_Tem_Nota_Fiscal_Com_Item = true;
	                }
	        	}
	        	if (!DM_Tem_Nota_Fiscal_Com_Item){
	            	EDI_ExportacaoED prod = new EDI_ExportacaoED();
	    	        if ("W".equals(res.getString ("DM_Tipo_Conhecimento"))){
		            	prod.setNR_Nota_Fiscal(" ");
		            	prod.setNR_Quantidade(0);
		            	prod.setVL_Produto(0);
		            	prod.setCD_Unidade_Medida(" ");
			            prod.setNM_Produto(" ");
	    	        }else{
		            	prod.setNR_Nota_Fiscal(edVolta.getNR_Nota_Fiscal());
		            	prod.setNR_Quantidade(res.getDouble ("NR_Volumes_Conhecimento"));
		            	prod.setVL_Produto(res.getDouble ("VL_Nota_Fiscal_Conhecimento"));
		            	prod.setCD_Unidade_Medida("DZ");
		    	        sql = " Select Produtos.nm_produto " +
		    	        		" from produtos " +
		    	        		" WHERE produtos.oid_produto=" + res.getString ("oid_Produto_Conhecimento");
		    	        res2 = this.executasql.executarConsulta (sql);
		    	        while (res2.next ()) {
			            	prod.setNM_Produto(res2.getString("nm_produto"));
		    	        }
	    	        }

	            	produtos.add(prod);
	            	DM_Tem_Nota_Fiscal_Com_Item = false;
	        	}
	            // System.out.println("TAM: "+produtos.size());
	            if(produtos.size()>0){
	            	edVolta.setProdutos(produtos);
	            	list.add (edVolta);
	            }
	        } else {
	        	list.add (edVolta);
	        }
	      }

	    }
	    catch (Exception exc) {
	    	exc.printStackTrace();
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      excecoes.setMensagem ("Erro ao listar");
	      excecoes.setMetodo ("listar");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }

	    return list;
	  }

  public ArrayList gera_EDI_Ocorrencia (EDI_ExportacaoED edComp) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    EDI_ExportacaoED ed = (EDI_ExportacaoED) edComp;

    try {

      sql = " select " +
          " Ocorrencias_Conhecimentos.DT_Ocorrencia_Conhecimento, " +
          " Ocorrencias_Conhecimentos.HR_Ocorrencia_Conhecimento, " +
          " Ocorrencias_Conhecimentos.DT_Ocorrencia_Lancada, " +
          " Ocorrencias_Conhecimentos.HR_Ocorrencia_Lancada, " +
          " Ocorrencias_Conhecimentos.TX_Observacao, " +
          " Conhecimentos.oid_Conhecimento, " +
          " Conhecimentos.oid_Pessoa, " +
          " Conhecimentos.oid_Pessoa_Entregadora, " +
          " Conhecimentos.oid_Pessoa_Destinatario, " +
          " Ocorrencias_EDI.CD_Ocorrencia, " +
          " Conhecimentos.NM_Serie as NM_Serie_Conhecimento, " +
          " Conhecimentos.NR_Conhecimento, " +
          " Conhecimentos.DT_Emissao, " +
          " Conhecimentos.DT_Previsao_Entrega, " +
          " Conhecimentos.DT_Entrega, " +
          " Conhecimentos.HR_Entrega, " +
          " Tipos_Ocorrencias.DM_Tipo as DM_Tipo_Ocorrencia, " +
          " Tipos_Ocorrencias.NM_Tipo_Ocorrencia , " +
          " Unidade_Conhecimento.CD_Unidade as CD_Unidade_Conhecimento, " +
          " Unidade_Conhecimento.oid_Pessoa as oid_Pessoa_Unidade, " +
          " Notas_Fiscais.NM_Serie as NM_Serie_Nota_Fiscal, " +
          " Notas_Fiscais.NR_Nota_Fiscal, " +
          " Notas_Fiscais.DT_Emissao as DT_Emissao_Nota_Fiscal, " +
          " Notas_Fiscais.NR_Peso,  " +
          " Notas_Fiscais.NR_Peso_Cubado,  " +
          " Notas_Fiscais.VL_Nota_Fiscal  " +
          " FROM  Conhecimentos, Conhecimentos_Notas_Fiscais, Notas_Fiscais, Unidades Unidade_Conhecimento, Ocorrencias_Conhecimentos, Ocorrencias_EDI, Tipos_Ocorrencias " +
          " WHERE Conhecimentos.oid_Conhecimento = Conhecimentos_Notas_Fiscais.oid_conhecimento " +
          " AND   Conhecimentos_Notas_Fiscais.oid_Nota_Fiscal = Notas_Fiscais.oid_Nota_Fiscal " +
          " AND   Conhecimentos.oid_unidade = Unidade_Conhecimento.oid_Unidade " +
          " AND   Ocorrencias_Conhecimentos.oid_Conhecimento = Conhecimentos.oid_conhecimento " +
          " AND   Ocorrencias_Conhecimentos.oid_tipo_ocorrencia = Tipos_Ocorrencias.oid_tipo_Ocorrencia " +
          " AND   Ocorrencias_Conhecimentos.oid_tipo_ocorrencia = Ocorrencias_EDI.oid_tipo_Ocorrencia " +
          //" AND   Conhecimentos.DM_Impresso = 'S' " +
          " AND   Conhecimentos.VL_Total_Frete > 0 " +
          " AND   Tipos_Ocorrencias.DM_Envia_EDI = 'S' ";

      	  if (JavaUtil.doValida (ed.getDT_Inicial ())) {
      	     sql += " AND Ocorrencias_Conhecimentos.DT_Ocorrencia_Conhecimento >= '" + ed.getDT_Inicial () + "'";
      	  }
      	  if (JavaUtil.doValida (ed.getDT_Final ())) {
      		 sql += " AND Ocorrencias_Conhecimentos.DT_Ocorrencia_Conhecimento <= '" + ed.getDT_Final () + "'";
      	  }
      	  if (JavaUtil.doValida (ed.getDT_Lancamento_Inicial ())) {
       	     sql += " AND Ocorrencias_Conhecimentos.dt_ocorrencia_lancada >= '" + ed.getDT_Lancamento_Inicial () + "'";
      	  }
      	  if (JavaUtil.doValida (ed.getDT_Lancamento_Final ())) {
       		 sql += " AND Ocorrencias_Conhecimentos.dt_ocorrencia_lancada <= '" + ed.getDT_Lancamento_Final () + "'";
      	  }

	      boolean opcao_cli=false;
	      if ("E".equals (ed.getDM_Tipo_Frete ())) {
			     sql += " AND  (Conhecimentos.oid_Pessoa in " + getByGrupo_Economico (ed);
			     sql += " OR   Conhecimentos.oid_Pessoa_Destinatario in " + getByGrupo_Economico (ed) + ")";
			     opcao_cli=true;
		  }
	      if ("EC".equals (ed.getDM_Tipo_Frete ())) {
			     sql += " AND   Conhecimentos.oid_Pessoa in " + getByGrupo_Economico (ed);
			     sql += " AND   Conhecimentos.DM_Tipo_Pagamento='1' " ;
			     opcao_cli=true;
		  }
	      if ("EF".equals (ed.getDM_Tipo_Frete ())) {
			     sql += " AND   Conhecimentos.oid_Pessoa in " + getByGrupo_Economico (ed);
			     sql += " AND   Conhecimentos.DM_Tipo_Pagamento<>'1' " ;
			     opcao_cli=true;
		  }
	      if ("R".equals (ed.getDM_Tipo_Frete ())) {
			     sql += " AND   Conhecimentos.oid_Pessoa_Destinatario in " + getByGrupo_Economico (ed);
			     opcao_cli=true;
		  }
	      if ("RC".equals (ed.getDM_Tipo_Frete ())) {
			     sql += " AND   Conhecimentos.oid_Pessoa_Destinatario in " + getByGrupo_Economico (ed);
			     sql += " AND   Conhecimentos.DM_Tipo_Pagamento='1' " ;
			     opcao_cli=true;
		  }
	      if ("RF".equals (ed.getDM_Tipo_Frete ())) {
			     sql += " AND   Conhecimentos.oid_Pessoa_Destinatario in " + getByGrupo_Economico (ed);
			     sql += " AND   Conhecimentos.DM_Tipo_Pagamento<>'1' " ;
			     opcao_cli=true;
		  }
	      if ("P".equals (ed.getDM_Tipo_Frete ())) {
			     sql += " AND   Conhecimentos.oid_Pessoa_Pagador in " + getByGrupo_Economico (ed);
			     opcao_cli=true;
	      }
	      if ("PC".equals (ed.getDM_Tipo_Frete ())) {
			     sql += " AND   Conhecimentos.oid_Pessoa_Pagador in " + getByGrupo_Economico (ed);
			     sql += " AND   Conhecimentos.DM_Tipo_Pagamento='1' " ;
			     opcao_cli=true;
		  }
	      if ("PF".equals (ed.getDM_Tipo_Frete ())) {
			     sql += " AND   Conhecimentos.oid_Pessoa_Pagador in " + getByGrupo_Economico (ed);
			     sql += " AND   Conhecimentos.DM_Tipo_Pagamento<>'1' " ;
			     opcao_cli=true;
		  }
	      if (!opcao_cli) {
		          sql += " AND   Conhecimentos.oid_Pessoa in " + getByGrupo_Economico (ed);
		  }

	      if ("29737368001433".equals(edComp.getOid_Pessoa())){
	    	  sql += " and Notas_Fiscais.oid_produto not in (276)";
	      }

	      if ("06096180000214".equals(edComp.getOid_Pessoa())){
	    	  sql += " and Notas_Fiscais.nm_serie not in ('2')";
	      }
	      sql +=" Order by  Conhecimentos.NR_Conhecimento, Ocorrencias_Conhecimentos.DT_Ocorrencia_Lancada desc, Ocorrencias_Conhecimentos.HR_Ocorrencia_Lancada desc, Notas_Fiscais.NR_NOta_Fiscal ";

      ResultSet res = null;
      ResultSet res2 = null;
      res = this.executasql.executarConsulta (sql);

      FormataDataBean dataFormatada = new FormataDataBean ();
      double vl_icms = 0;
      String NR_Conhecimento = "";
      while (res.next ()) {
        //if (!NR_Conhecimento.equals(res.getString("NR_Conhecimento"))){
        EDI_ExportacaoED edVolta = new EDI_ExportacaoED ();
        NR_Conhecimento = res.getString ("NR_Conhecimento");

        // System.err.println ("a GERANDO");
        edVolta.setOid_Pessoa_Unidade (res.getString ("oid_Pessoa_Unidade"));
        edVolta.setOid_Pessoa (res.getString ("oid_Pessoa"));
        edVolta.setOid_Pessoa_edi(ed.getOid_Pessoa()); // Esta é a pessoa do EDI que veio do JSP
        edVolta.setOid_Pessoa_Entregadora (res.getString ("oid_Pessoa_Entregadora"));
        edVolta.setOid_Pessoa_Destinatario(res.getString ("oid_Pessoa_Destinatario"));

        edVolta.setNM_Filial_Conhecimento (res.getString ("CD_Unidade_Conhecimento"));
        edVolta.setNR_Serie_Conhecimento (res.getString ("NM_Serie_Conhecimento"));
        edVolta.setNR_Conhecimento (res.getString ("NR_Conhecimento"));
        // System.err.println ("2");

        edVolta.setCD_Ocorrencia (res.getString ("CD_Ocorrencia"));

        edVolta.setDM_Tipo_Ocorrencia (res.getString ("DM_Tipo_Ocorrencia"));

        dataFormatada.setDT_FormataData (res.getString ("DT_Emissao"));
        edVolta.setDT_Emissao_Conhecimento (dataFormatada.getDT_FormataData ());

        dataFormatada.setDT_FormataData (res.getString ("DT_Ocorrencia_Conhecimento"));
        edVolta.setDT_Ocorrencia (dataFormatada.getDT_FormataData ());
        // System.err.println ("4 a ->" + edVolta.getDT_Ocorrencia ());
        edVolta.setHR_Ocorrencia (res.getString ("HR_Ocorrencia_Conhecimento"));

        edVolta.setDT_Ocorrencia_Lancada (edVolta.getDT_Ocorrencia ());
        edVolta.setHR_Ocorrencia_Lancada (edVolta.getHR_Ocorrencia ());
        if (res.getString ("DT_Ocorrencia_Lancada") != null && !res.getString ("DT_Ocorrencia_Lancada").equals ("null") && !res.getString ("DT_Ocorrencia_Lancada").equals ("")) {
          // System.err.println ("4 b->" + edVolta.getDT_Ocorrencia ());

          dataFormatada.setDT_FormataData (res.getString ("DT_Ocorrencia_Lancada"));
          edVolta.setDT_Ocorrencia_Lancada (dataFormatada.getDT_FormataData ());
          edVolta.setHR_Ocorrencia_Lancada (res.getString ("HR_Ocorrencia_Lancada"));
        }

        edVolta.setTX_Observacao (res.getString ("NM_Tipo_Ocorrencia"));
        if (res.getString ("TX_Observacao") != null && !res.getString ("TX_Observacao").equals ("null")) {
          edVolta.setTX_Observacao (edVolta.getTX_Observacao () + "/" + res.getString ("TX_Observacao"));
        }

        edVolta.setDT_Previsao_Entrega(dataFormatada.getDT_FormataData(res.getString("DT_Previsao_Entrega")));

        dataFormatada.setDT_FormataData (res.getString ("DT_Entrega"));
        edVolta.setDT_Entrega (dataFormatada.getDT_FormataData ());
        edVolta.setHR_Entrega (res.getString ("HR_Entrega"));

        edVolta.setNR_Nota_Fiscal (res.getString ("NR_Nota_Fiscal"));
        edVolta.setNR_Serie_Nota_Fiscal (res.getString ("NM_Serie_Nota_Fiscal"));
        // System.err.println ("5");

        dataFormatada.setDT_FormataData (res.getString ("DT_Emissao_Nota_Fiscal"));
        edVolta.setDT_Emissao_Nota_Fiscal (dataFormatada.getDT_FormataData ());
        edVolta.setNM_Unidade_Entregadora ("     ");

        sql = " select " +
            " Viagens.DT_Viagem, " +
            " Viagens.HR_Viagem, " +
            " Unidades.CD_Unidade, " +
            " Manifestos.NR_Manifesto " +
            " FROM Manifestos, Viagens, Unidades " +
            " WHERE Manifestos.oid_Manifesto = Viagens.oid_Manifesto " +
            " AND   Manifestos.oid_Unidade_Destino = Unidades.oid_unidade " +
            " AND   Viagens.oid_Conhecimento = '" + res.getString ("oid_Conhecimento") + "'";

        // System.err.println ("sql viagen " + sql);

        res2 = this.executasql.executarConsulta (sql);
        while (res2.next ()) {
          // System.err.println ("tem viagem");
          edVolta.setNR_Manifesto (res2.getString ("NR_Manifesto"));
          dataFormatada.setDT_FormataData (res2.getString ("DT_Viagem"));
          edVolta.setDT_Embarque (dataFormatada.getDT_FormataData ());
          edVolta.setHR_Embarque (res2.getString ("HR_Viagem"));
          edVolta.setNM_Unidade_Entregadora (res2.getString ("CD_Unidade"));
        }

        // System.err.println ("99");

        list.add (edVolta);

        //}
      }

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao listar");
      excecoes.setMetodo ("listar");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return list;
  }

  public ArrayList gera_EDI_Faturas (EDI_ExportacaoED edComp) throws Excecoes {

    String sql = null;
    String sqlBusca = null;
    ArrayList list = new ArrayList ();
    EDI_ExportacaoED ed = (EDI_ExportacaoED) edComp;

    try {

      sql = " select " +
          " Duplicatas.OID_Duplicata, " +
          " Duplicatas.OID_Carteira, " +
          " Duplicatas.NR_Duplicata, " +
          " Duplicatas.DT_Emissao as DT_Emissao_Duplicata, " +
          " Duplicatas.DT_Vencimento, " +
          " Duplicatas.VL_Duplicata, " +
          " Duplicatas.VL_Saldo, " +
          " Duplicatas.VL_Juro_Mora_Dia, " +
          " Duplicatas.VL_Desconto_Faturamento, " +
          " Unidade_Duplicata.CD_Unidade as CD_Unidade_Duplicata, " +
          " Conhecimentos.NM_Serie as NM_Serie_Conhecimento, " +
          " Conhecimentos.NR_Conhecimento, " +
          " Conhecimentos.DM_Tipo_Conhecimento, " +
          " Conhecimentos.DT_Previsao_Entrega, " +
          " Conhecimentos.HR_Previsao_Entrega, " +
          " Conhecimentos.NM_Natureza, " +
          " Conhecimentos.TX_Observacao, " +
          " Conhecimentos.NR_Duplicata, " +
          " Conhecimentos.DT_Emissao as DT_Emissao_Conhecimento, " +
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
          " Conhecimentos.OID_Cidade, " +
          " Conhecimentos.OID_Cidade_Destino, " +
          " Unidade_Conhecimento.CD_Unidade as CD_Unidade_Conhecimento, " +
          " Unidade_Conhecimento.oid_Pessoa as oid_Pessoa_Unidade, " +
          " Notas_Fiscais.oid_Pessoa as Oid_Pessoa_Nota_Fiscal, " +
          " Notas_Fiscais.NM_Serie as NM_Serie_Nota_Fiscal, " +
          " Notas_Fiscais.NR_Nota_Fiscal, " +
          " Notas_Fiscais.DT_Emissao as DT_Emissao_Nota_Fiscal, " +
          " Notas_Fiscais.NR_Peso,  " +
          " Notas_Fiscais.NR_Peso_Cubado,  " +
          " Notas_Fiscais.NR_Lote,  " +
          " Notas_Fiscais.NR_Pedido,  " +
          " Notas_Fiscais.VL_Nota_Fiscal  " +
          " FROM Duplicatas, Origens_Duplicatas, Conhecimentos, Conhecimentos_Notas_Fiscais, Notas_Fiscais, Unidades Unidade_Duplicata, Unidades Unidade_Conhecimento " +
          " WHERE Duplicatas.oid_Duplicata = Origens_Duplicatas.oid_duplicata " +
          " AND   Origens_Duplicatas.oid_Conhecimento = Conhecimentos.oid_Conhecimento " +
          " AND   Conhecimentos.oid_Conhecimento = Conhecimentos_Notas_Fiscais.oid_conhecimento " +
          " AND   Conhecimentos_Notas_Fiscais.oid_Nota_Fiscal = Notas_Fiscais.oid_Nota_Fiscal " +
          " AND   Conhecimentos.oid_unidade = Unidade_Conhecimento.oid_Unidade " +
          " AND   Duplicatas.oid_unidade = Unidade_Duplicata.oid_Unidade " +
          " AND   Origens_Duplicatas.dm_Situacao != 'E' " +
          " AND   Unidade_Conhecimento.oid_Empresa = " + ed.getOid_Empresa () +
          " AND   Duplicatas.DT_Emissao >= '" + ed.getDT_Inicial () + "'" +
          " AND   Duplicatas.DT_Emissao <= '" + ed.getDT_Final () + "'" ;

	      boolean opcao_cli=false;
	      if ("E".equals (ed.getDM_Tipo_Frete ())) {
			     sql += " AND  (Conhecimentos.oid_Pessoa in " + getByGrupo_Economico (ed);
			     sql += " OR   Conhecimentos.oid_Pessoa_Destinatario in " + getByGrupo_Economico (ed) + ")";
			     opcao_cli=true;
		  }
	      if ("EC".equals (ed.getDM_Tipo_Frete ())) {
			     sql += " AND   Conhecimentos.oid_Pessoa in " + getByGrupo_Economico (ed);
			     sql += " AND   Conhecimentos.DM_Tipo_Pagamento='1' " ;
			     opcao_cli=true;
		  }
	      if ("EF".equals (ed.getDM_Tipo_Frete ())) {
			     sql += " AND   Conhecimentos.oid_Pessoa in " + getByGrupo_Economico (ed);
			     sql += " AND   Conhecimentos.DM_Tipo_Pagamento<>'1' " ;
			     opcao_cli=true;
		  }
	      if ("R".equals (ed.getDM_Tipo_Frete ())) {
			     sql += " AND   Conhecimentos.oid_Pessoa_Destinatario in " + getByGrupo_Economico (ed);
			     opcao_cli=true;
		  }
	      if ("RC".equals (ed.getDM_Tipo_Frete ())) {
			     sql += " AND   Conhecimentos.oid_Pessoa_Destinatario in " + getByGrupo_Economico (ed);
			     sql += " AND   Conhecimentos.DM_Tipo_Pagamento='1' " ;
			     opcao_cli=true;
		  }
	      if ("RF".equals (ed.getDM_Tipo_Frete ())) {
			     sql += " AND   Conhecimentos.oid_Pessoa_Destinatario in " + getByGrupo_Economico (ed);
			     sql += " AND   Conhecimentos.DM_Tipo_Pagamento<>'1' " ;
			     opcao_cli=true;
		  }
	      if ("P".equals (ed.getDM_Tipo_Frete ())) {
			     sql += " AND   Conhecimentos.oid_Pessoa_Pagador in " + getByGrupo_Economico (ed);
			     opcao_cli=true;
	      }
	      if ("PC".equals (ed.getDM_Tipo_Frete ())) {
			     sql += " AND   Conhecimentos.oid_Pessoa_Pagador in " + getByGrupo_Economico (ed);
			     sql += " AND   Conhecimentos.DM_Tipo_Pagamento='1' " ;
			     opcao_cli=true;
		  }
	      if ("PF".equals (ed.getDM_Tipo_Frete ())) {
			     sql += " AND   Conhecimentos.oid_Pessoa_Pagador in " + getByGrupo_Economico (ed);
			     sql += " AND   Conhecimentos.DM_Tipo_Pagamento<>'1' " ;
			     opcao_cli=true;
		  }
	      if (!opcao_cli) {
		          sql += " AND   Conhecimentos.oid_Pessoa in " + getByGrupo_Economico (ed);
		  }

	      if (JavaUtil.doValida (ed.getNR_Duplicata ())) {
	        sql += " AND Duplicatas.nr_duplicata = " + ed.getNR_Duplicata ();
	      }
	      sql += " Order by  Duplicatas.NR_Duplicata, Conhecimentos.NR_Conhecimento, Notas_Fiscais.NR_NOta_Fiscal ";

       // System.err.println (sql);

      ResultSet res = null;
      ResultSet resLocal = null;

      res = this.executasql.executarConsulta (sql);

      FormataDataBean dataFormatada = new FormataDataBean ();
      double vl_icms = 0;
      while (res.next ()) {

        EDI_ExportacaoED edVolta = new EDI_ExportacaoED ();
        // System.err.println ("1");

        edVolta.setNM_Filial_Duplicata (res.getString ("CD_Unidade_Duplicata"));
        // System.err.println ("1 a");
        edVolta.setNM_Tipo_Documento ("0");
        // System.err.println ("1 b" + res.getString ("NR_Duplicata"));
        edVolta.setNR_Serie_Duplicata ("UNI");
        edVolta.setNR_Duplicata (res.getString ("NR_Duplicata"));
        // System.err.println ("2");

        dataFormatada.setDT_FormataData (res.getString ("DT_Emissao_Duplicata"));
        edVolta.setDT_Emissao_Duplicata (dataFormatada.getDT_FormataData ());

        dataFormatada.setDT_FormataData (res.getString ("DT_Vencimento"));
        edVolta.setDT_Vencimento_Duplicata (dataFormatada.getDT_FormataData ());
        edVolta.setNM_Tipo_Cobranca ("BCO");
        // System.err.println ("3");

        edVolta.setVL_Duplicata (res.getDouble ("VL_Duplicata"));
        edVolta.setVL_Juro_Mora_Dia (res.getDouble ("VL_Juro_Mora_Dia"));
        edVolta.setVL_Desconto (res.getDouble ("VL_Desconto_Faturamento"));
        edVolta.setNM_Agente_Cobranca (" ");
        edVolta.setNM_Agencia ("0000 ");
        edVolta.setNM_Conta_Corrente ("0000000000 ");

        String sqlAux = " SELECT Pessoas.nm_Razao_Social, Contas_Correntes.nr_conta_corrente, Contas_Correntes.nr_agencia " +
            " from Pessoas, Contas_Correntes, Carteiras " +
            " where pessoas.oid_pessoa = contas_correntes.oid_pessoa " +
            " and contas_correntes.oid_conta_corrente = carteiras.oid_conta_corrente " +
            " and carteiras.oid_carteira = " + res.getLong ("oid_Carteira");
        ResultSet rsAux = this.executasql.executarConsulta (sqlAux);
        if (rsAux.next ()) {
          edVolta.setNM_Agente_Cobranca (rsAux.getString (1));
          edVolta.setNM_Agencia (rsAux.getString (3));
          edVolta.setNM_Conta_Corrente (rsAux.getString (2));
        }

        edVolta.setOid_Pessoa (res.getString ("Oid_Pessoa_Nota_Fiscal"));
        edVolta.setOid_Pessoa_Pagador (ed.getOid_Pessoa ());

        edVolta.setNM_Filial_Conhecimento (res.getString ("CD_Unidade_Conhecimento"));
        edVolta.setNR_Serie_Conhecimento (res.getString ("NM_Serie_Conhecimento"));
        edVolta.setNR_Conhecimento (res.getString ("NR_Conhecimento"));

        edVolta.setNR_Nota_Fiscal (res.getString ("NR_Nota_Fiscal"));
        edVolta.setNR_Serie_Nota_Fiscal (res.getString ("NM_Serie_Nota_Fiscal"));
        // System.err.println ("5");

        dataFormatada.setDT_FormataData (res.getString ("DT_Emissao_Nota_Fiscal"));
        edVolta.setDT_Emissao_Nota_Fiscal (dataFormatada.getDT_FormataData ());

        edVolta.setNR_Pedido (res.getString ("NR_Pedido"));

        edVolta.setNR_Peso_Nota_Fiscal (res.getDouble ("NR_Peso_Cubado"));
        if (edVolta.getNR_Peso_Nota_Fiscal () <= 0) {
          edVolta.setNR_Peso_Nota_Fiscal (res.getDouble ("NR_Peso"));
        }
        edVolta.setVL_Nota_Fiscal (res.getDouble ("VL_Nota_Fiscal"));

        edVolta.setNR_Lote (res.getString ("NR_Lote"));

        // System.err.println ("99");

        sqlBusca = "Select " +
            " VL_ICMS, vl_total_Frete  " +
            " from " +
            " Conhecimentos, Origens_Duplicatas " +
            " WHERE Origens_Duplicatas.oid_Conhecimento = Conhecimentos.oid_Conhecimento " +
            " AND   Origens_Duplicatas.dm_Situacao != 'E' " +
            " AND   Origens_Duplicatas.oid_Duplicata = '" + res.getString ("OID_Duplicata") + "'";

        resLocal = this.executasql.executarConsulta (sqlBusca);

        edVolta.setVL_ICMS (0);
        edVolta.setVL_Duplicata (0);
        while (resLocal.next ()) {
          edVolta.setVL_ICMS (edVolta.getVL_ICMS () + resLocal.getDouble ("VL_ICMS"));
          edVolta.setVL_Duplicata (edVolta.getVL_Duplicata () + resLocal.getDouble ("VL_Total_Frete"));
        }
        // System.err.println ("100");

        edVolta.setOid_Pessoa_Unidade (res.getString ("oid_Pessoa_Unidade"));
        String uf = PessoaBean.getByOID (res.getString ("oid_Pessoa_Unidade")).getCD_Estado () + "  ";
        edVolta.setCD_Estado_Unidade (uf.substring (0 , 2));
        uf = CidadeBean.getByOID (res.getInt ("oid_Cidade")).getCD_Estado () + "  ";
        edVolta.setCD_Estado_Origem (uf.substring (0 , 2));
        uf = CidadeBean.getByOID (res.getInt ("oid_Cidade_Destino")).getCD_Estado () + "  ";
        edVolta.setCD_Estado_Destino (uf.substring (0 , 2));

        edVolta.setNM_Filial_Conhecimento (res.getString ("CD_Unidade_Conhecimento"));
        edVolta.setNR_Serie_Conhecimento (res.getString ("NM_Serie_Conhecimento"));
        edVolta.setNR_Conhecimento (res.getString ("NR_Conhecimento"));
        // System.err.println ("102");

        dataFormatada.setDT_FormataData (res.getString ("DT_Previsao_Entrega"));
        edVolta.setDT_Previsao_Entrega (dataFormatada.getDT_FormataData ());

        edVolta.setHR_Previsao_Entrega (res.getString ("HR_Previsao_Entrega"));
        edVolta.setNM_Natureza (res.getString ("NM_Natureza"));
        edVolta.setTX_Observacao (res.getString ("TX_Observacao"));
        edVolta.setNR_Duplicata (res.getString ("NR_Duplicata"));

        edVolta.setDM_Tipo_Conhecimento (res.getString ("DM_Tipo_Conhecimento"));

        edVolta.setNR_Peso_Conhecimento (res.getDouble ("NR_Peso"));
        // System.err.println ("103");

        edVolta.setNR_Peso_Cubado_Conhecimento (res.getDouble ("NR_Peso_Cubado"));
        if (edVolta.getNR_Peso_Cubado_Conhecimento () <= 0) {
          edVolta.setNR_Peso_Cubado_Conhecimento (res.getDouble ("NR_Peso"));
        }

        // System.err.println ("3 ok ");
        edVolta.setVL_Total_Frete (res.getDouble ("VL_Total_Frete"));
        edVolta.setVL_Base_Calculo_ICMS (res.getDouble ("VL_Base_Calculo_ICMS"));
        edVolta.setPE_Aliquota_ICMS (res.getDouble ("PE_ALIQUOTA_ICMS"));
        edVolta.setVL_ICMS_Conhecimento (res.getDouble ("VL_ICMS"));

        edVolta.setVL_Frete_Peso (res.getDouble ("VL_Frete_Peso"));
        edVolta.setVL_Frete_Valor (res.getDouble ("VL_Frete_Valor"));
        // System.err.println ("4 ICMS " + res.getDouble ("VL_ICMS"));
        edVolta.setVL_Despacho (res.getDouble ("VL_Despacho"));
        edVolta.setVL_Pedagio (res.getDouble ("VL_Pedagio"));
        // System.err.println ("4 b");
        edVolta.setVL_Outros1 (res.getDouble ("VL_Outros1"));
        edVolta.setVL_Ademe (res.getDouble ("VL_Outros2"));
        // System.err.println ("4 c");

        edVolta.setCD_CFO_Conhecimento (res.getString ("CD_CFO_Conhecimento"));

        dataFormatada.setDT_FormataData (res.getString ("DT_Emissao_Conhecimento"));
        edVolta.setDT_Emissao_Conhecimento (dataFormatada.getDT_FormataData ());

        list.add (edVolta);
      }

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao listar");
      excecoes.setMetodo ("listar");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return list;
  }

  public ArrayList gera_EDI_FaturasIpiranga (EDI_ExportacaoED edComp) throws Excecoes {

    String sql = null;
    String sqlBusca = null;
    ArrayList list = new ArrayList ();
    EDI_ExportacaoED ed = (EDI_ExportacaoED) edComp;

    try {

      sql = " select " +
          " Duplicatas.OID_Duplicata, " +
          " Duplicatas.OID_Carteira, " +
          " Duplicatas.NR_Duplicata, " +
          " Duplicatas.DT_Emissao as DT_Emissao_Duplicata, " +
          " Duplicatas.DT_Vencimento, " +
          " Duplicatas.VL_Duplicata, " +
          " Duplicatas.VL_Saldo, " +
          " Duplicatas.VL_Juro_Mora_Dia, " +
          " Duplicatas.VL_Desconto_Faturamento," +
          " Conhecimentos.NR_Conhecimento," +
          " Pessoas.oid_Pessoa," +
          " Pessoas.NR_CNPJ_CPF," +
          " Pessoas.oid_Cidade," +
          " Pessoas.NM_Razao_Social, " +
          " Cidades.NM_Cidade, " +
          " Estados.CD_Estado " +
          " FROM Duplicatas, Conhecimentos, Origens_Duplicatas, " +
          " Pessoas, Unidades, Cidades, Regioes_Estados, Estados " +
          " WHERE Duplicatas.OID_Duplicata = Origens_Duplicatas.OID_Duplicata" +
          " AND Origens_Duplicatas.oid_Conhecimento = Conhecimentos.oid_Conhecimento" +
          " AND Duplicatas.oid_Pessoa = Pessoas.oid_Pessoa" +
          " AND Duplicatas.oid_Unidade = Unidades.oid_Unidade " +
          " AND Pessoas.oid_Cidade = Cidades.oid_Cidade " +
          " AND Cidades.oid_Regiao_Estado = Regioes_Estados.oid_Regiao_Estado " +
          " AND Regioes_Estados.oid_Estado = Estados.oid_Estado " +
          " AND   Unidades.oid_Empresa = " + ed.getOid_Empresa () +
          " AND   Duplicatas.DT_Emissao >= '" + ed.getDT_Inicial () + "'" +
          " AND   Duplicatas.DT_Emissao <= '" + ed.getDT_Final () + "'" +
          " AND   Conhecimentos.oid_Pessoa = '" + ed.getOid_Pessoa () + "'";
//          " AND   Duplicatas.oid_Pessoa in " + getByGrupo_Economico(ed);
      if (JavaUtil.doValida (ed.getNR_Duplicata ())) {
        sql += " AND Duplicatas.nr_duplicata = " + ed.getNR_Duplicata ();
      }
      sql += " Order by Duplicatas.vl_saldo, Duplicatas.NR_Duplicata ";

      ResultSet res = null;
      ResultSet resLocal = null;

      res = this.executasql.executarConsulta (sql);

      FormataDataBean dataFormatada = new FormataDataBean ();
      double vl_icms = 0;
      while (res.next ()) {

        EDI_ExportacaoED edVolta = new EDI_ExportacaoED ();
        edVolta.setNR_Duplicata (res.getString ("NR_Duplicata"));
        edVolta.setNR_Conhecimento (res.getString ("NR_Conhecimento"));
        dataFormatada.setDT_FormataData (res.getString ("DT_Emissao_Duplicata"));
        edVolta.setDT_Emissao_Duplicata (dataFormatada.getDT_FormataData ());
        dataFormatada.setDT_FormataData (res.getString ("DT_Vencimento"));
        edVolta.setDT_Vencimento_Duplicata (dataFormatada.getDT_FormataData ());
        edVolta.setVL_Duplicata (res.getDouble ("VL_Duplicata"));
        edVolta.setVL_Juro_Mora_Dia (res.getDouble ("VL_Juro_Mora_Dia"));
        edVolta.setVL_Desconto (res.getDouble ("VL_Desconto_Faturamento"));
        edVolta.setVL_Saldo (res.getDouble ("VL_Saldo"));
        edVolta.setOid_Pessoa (res.getString ("NR_CNPJ_CPF"));
        edVolta.setNM_Razao_Social (res.getString ("NM_Razao_Social"));
        edVolta.setNM_Cidade_Origem (res.getString ("NM_Cidade"));
        edVolta.setCD_Estado_Origem (res.getString ("CD_Estado"));
        edVolta.setDM_Situacao (this.consulta_Situacao (edVolta));

        list.add (edVolta);

      }
    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao selecionar dados de importação");
      excecoes.setMetodo ("gera_EDI_FaturasIpiranga(EDI_ExportacaoED edComp)");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return list;
  }

  public ArrayList gera_EDI_Nota_Fiscal (EDI_ExportacaoED edComp) throws Excecoes {

	    String sql = null;
	    ArrayList list = new ArrayList ();
	    EDI_ExportacaoED ed = (EDI_ExportacaoED) edComp;

	    try {

	      sql = " select " +
	          " Conhecimentos.NR_Conhecimento, " +
	          " Conhecimentos.DT_Emissao, " +
	          " Conhecimentos.oid_pessoa, " +
	          " Conhecimentos.oid_pessoa_pagador, " +
	          " Modal.DM_Tipo_Transporte, " +
	          " Unidades.CD_Unidade, " +
	          " Unidades.oid_Pessoa as oid_Pessoa_Unidade, " +
	          " Notas_Fiscais.oid_pessoa as remetente, " +
	          " Notas_Fiscais.oid_pessoa_destinatario as destinatario, " +
	          " Conhecimentos.oid_pessoa_redespacho as transportador, " +
	          " Notas_Fiscais.NM_Serie as NM_Serie_Nota_Fiscal, " +
	          " Notas_Fiscais.NR_Nota_Fiscal, " +
	          " Notas_Fiscais.DT_Emissao as DT_Emissao_Nota_Fiscal, " +
	          " Notas_Fiscais.NR_Peso as NR_Peso_Nota_Fiscal,  " +
	          " Notas_Fiscais.NR_Volumes as NR_Volumes_Nota_Fiscal,  " +
	          " Notas_Fiscais.NR_Peso_Cubado as NR_Peso_Cubado_Nota_Fiscal,  " +
	          " Notas_Fiscais.VL_Nota_Fiscal as VL_Nota_Fiscal_Nota_Fiscal " +
	          " FROM  Conhecimentos, Conhecimentos_Notas_Fiscais, Modal, Notas_Fiscais, Unidades " +
	          " WHERE Conhecimentos.oid_Conhecimento = Conhecimentos_Notas_Fiscais.oid_conhecimento " +
	          " AND   Conhecimentos_Notas_Fiscais.oid_Nota_Fiscal = Notas_Fiscais.oid_Nota_Fiscal " +
	          " AND   Conhecimentos.oid_unidade = Unidades.oid_Unidade " +
	          " AND   Conhecimentos.oid_Modal = Modal.oid_Modal " +
	          " AND   Conhecimentos.DM_Impresso = 'S' " +
	          " AND   Conhecimentos.VL_Total_Frete > 0" +
	          " AND   Conhecimentos.DM_Tipo_Documento = 'M' " +
	          " AND   Unidades.oid_Empresa = " + ed.getOid_Empresa () +
	          " AND   Conhecimentos.DT_Emissao >= '" + ed.getDT_Inicial () + "'" +
	          " AND   Conhecimentos.DT_Emissao <= '" + ed.getDT_Final () + "'";

	      sql += " AND   Conhecimentos.oid_Pessoa_Redespacho in " + getByGrupo_Economico (ed);

	      if (ed.getOid_Unidade () > 0) {
	        sql += " AND Conhecimentos.oid_Unidade = " + ed.getOid_Unidade ();
	      }

	      sql += " Order by  Notas_Fiscais.NR_Nota_Fiscal ";

	      // System.out.println ("EDI NF => " + sql);

	      ResultSet res = null;
	      ResultSet res2 = null;

	      res = this.executasql.executarConsulta (sql);

	      FormataDataBean dataFormatada = new FormataDataBean ();

	      while (res.next ()) {
	        EDI_ExportacaoED edVolta = new EDI_ExportacaoED();
	        // System.err.println("1");
	        edVolta.setDM_Tipo_Pagamento("C");
	        if (!res.getString("oid_Pessoa").equals(res.getString ("oid_Pessoa_Pagador"))) {
	          edVolta.setDM_Tipo_Pagamento ("F");
	        }

	        edVolta.setOid_Pessoa(res.getString("remetente")); //Remetente da NF
	        edVolta.setOid_Pessoa_Destinatario(res.getString("destinatario")); //Destinatario da NF

	        edVolta.setOid_Pessoa_Redespacho(res.getString("transportador")); //Transportadora

	        edVolta.setOid_Pessoa_Unidade(res.getString("oid_Pessoa_Unidade")); //Unidade
	        edVolta.setNM_Filial_Conhecimento(res.getString("CD_Unidade"));

	        edVolta.setNR_Conhecimento(res.getString("NR_Conhecimento"));
	        edVolta.setDT_Emissao_Conhecimento(dataFormatada.getDT_FormataData(res.getString("DT_Emissao")));
	        edVolta.setDM_Tipo_Transporte("1");
	        // System.err.println ("2");
	        //Dados da NF
	        edVolta.setNR_Nota_Fiscal(res.getString("NR_Nota_Fiscal"));
	        edVolta.setNR_Serie_Nota_Fiscal(res.getString("NM_Serie_Nota_Fiscal"));
	        edVolta.setDT_Emissao_Nota_Fiscal(res.getString("DT_Emissao_Nota_Fiscal"));
	        edVolta.setNR_Peso_Nota_Fiscal(res.getDouble("NR_Peso_Nota_Fiscal"));
	        edVolta.setNR_Peso_Cubado_Nota_Fiscal(res.getDouble("NR_Peso_Cubado_Nota_Fiscal"));
	        edVolta.setVL_Nota_Fiscal(res.getDouble("VL_Nota_Fiscal_Nota_Fiscal"));
	        edVolta.setNR_Volume_Nota_Fiscal(res.getDouble("NR_Volumes_Nota_Fiscal"));

	        // System.err.println ("99");

	        list.add (edVolta);
	      }

	    }
	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      excecoes.setMensagem ("Erro ao listar");
	      excecoes.setMetodo ("listar");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }

	    return list;
	  }

  public ArrayList gera_EDI_Nota_FiscalByManifesto (EDI_ExportacaoED edComp) throws Excecoes {

	    String sql = null;
	    ArrayList list = new ArrayList ();
	    EDI_ExportacaoED ed = (EDI_ExportacaoED) edComp;
	    FormataDataBean dataFormatada = new FormataDataBean ();

	    int qt_notas=0;
	    try {

	      sql = " select " +
	          " Unidades.CD_Unidade, " +
	          " Unidades.oid_Pessoa as oid_Pessoa_Unidade, " +
	          " Notas_Fiscais.oid_pessoa as remetente, " +
	          " Notas_Fiscais.oid_pessoa_destinatario as destinatario, " +
	          " Notas_Fiscais.NM_Serie as NM_Serie_Nota_Fiscal, " +
	          " Notas_Fiscais.NR_Nota_Fiscal, " +
	          " Notas_Fiscais.DT_Emissao as DT_Emissao_Nota_Fiscal, " +
	          " Notas_Fiscais.NR_Peso as NR_Peso_Nota_Fiscal,  " +
	          " Notas_Fiscais.NR_Volumes as NR_Volumes_Nota_Fiscal,  " +
	          " Notas_Fiscais.NR_Peso_Cubado as NR_Peso_Cubado_Nota_Fiscal,  " +
	          " Notas_Fiscais.VL_Nota_Fiscal as VL_Nota_Fiscal_Nota_Fiscal " +
	          " FROM  Manifestos, viagens, Notas_Fiscais, Unidades " +
	          " WHERE Manifestos.oid_Manifesto = viagens.oid_manifesto " +
	          " AND   viagens.oid_Nota_Fiscal = Notas_Fiscais.oid_Nota_Fiscal " +
	          " AND   manifestos.oid_unidade_destino = Unidades.oid_Unidade " +
	          " AND   Manifestos.oid_Manifesto = '" + ed.getOID_Manifesto() + "'";

	      sql += " Order by  Notas_Fiscais.NR_Nota_Fiscal ";

	      // System.out.println ("EDI NF => " + sql);

	      ResultSet res = null;

	      res = this.executasql.executarConsulta (sql);

	      while (res.next ()) {
	        EDI_ExportacaoED edVolta = new EDI_ExportacaoED();
	        // System.err.println("1");
	        edVolta.setDM_Tipo_Pagamento("C");
	        if (!res.getString("oid_Pessoa_Unidade").equals(res.getString ("remetente"))) {
	          edVolta.setDM_Tipo_Pagamento ("F");
	        }

	        edVolta.setOid_Pessoa(res.getString("remetente")); //Remetente da NF
	        edVolta.setOid_Pessoa_Destinatario(res.getString("destinatario")); //Destinatario da NF

	        //edVolta.setOid_Pessoa_Redespacho(res.getString("transportador")); //Transportadora

	        edVolta.setOid_Pessoa_Unidade(res.getString("oid_Pessoa_Unidade")); //Unidade
	        edVolta.setNM_Filial_Conhecimento(res.getString("CD_Unidade"));

	        //edVolta.setNR_Conhecimento(res.getString("NR_Conhecimento"));
	        //edVolta.setDT_Emissao_Conhecimento(dataFormatada.getDT_FormataData(res.getString("DT_Emissao")));
	        edVolta.setDM_Tipo_Transporte("1");
	        // System.err.println ("2");
	        //Dados da NF
	        edVolta.setNR_Nota_Fiscal(res.getString("NR_Nota_Fiscal"));
	        edVolta.setNR_Serie_Nota_Fiscal(res.getString("NM_Serie_Nota_Fiscal"));

	        dataFormatada.setDT_FormataData (res.getString ("DT_Emissao_Nota_Fiscal"));
	        edVolta.setDT_Emissao_Nota_Fiscal (dataFormatada.getDT_FormataData ());

	        edVolta.setNR_Peso_Nota_Fiscal(res.getDouble("NR_Peso_Nota_Fiscal"));
	        edVolta.setNR_Peso_Cubado_Nota_Fiscal(res.getDouble("NR_Peso_Cubado_Nota_Fiscal"));
	        edVolta.setVL_Nota_Fiscal(res.getDouble("VL_Nota_Fiscal_Nota_Fiscal"));
	        edVolta.setNR_Volume_Nota_Fiscal(res.getDouble("NR_Volumes_Nota_Fiscal"));

	        // System.err.println ("99");
	        qt_notas++;
	        list.add (edVolta);
	      }
	      if (qt_notas==0){
		      sql = " select " +
	          " Unidades.CD_Unidade, " +
	          " Unidades.oid_Pessoa as oid_Pessoa_Unidade, " +
	          " Notas_Fiscais.oid_pessoa as remetente, " +
	          " Notas_Fiscais.oid_pessoa_destinatario as destinatario, " +
	          " Notas_Fiscais.NM_Serie as NM_Serie_Nota_Fiscal, " +
	          " Notas_Fiscais.NR_Nota_Fiscal, " +
	          " Notas_Fiscais.DT_Emissao as DT_Emissao_Nota_Fiscal, " +
	          " Notas_Fiscais.NR_Peso as NR_Peso_Nota_Fiscal,  " +
	          " Notas_Fiscais.NR_Volumes as NR_Volumes_Nota_Fiscal,  " +
	          " Notas_Fiscais.NR_Peso_Cubado as NR_Peso_Cubado_Nota_Fiscal,  " +
	          " Notas_Fiscais.VL_Nota_Fiscal as VL_Nota_Fiscal_Nota_Fiscal " +
	          " FROM  Manifestos, viagens, Conhecimentos, Conhecimentos_Notas_Fiscais, Notas_Fiscais, Unidades " +
	          " WHERE Manifestos.oid_Manifesto = viagens.oid_manifesto " +
	          " AND   viagens.oid_Conhecimento = Conhecimentos.oid_Conhecimento " +
	          " AND   Conhecimentos.oid_Conhecimento = Conhecimentos_Notas_Fiscais.oid_Conhecimento " +
	          " AND   Conhecimentos_Notas_Fiscais.oid_Nota_Fiscal = Notas_Fiscais.oid_Nota_Fiscal " +
	          " AND   manifestos.oid_unidade_destino = Unidades.oid_Unidade " +
	          " AND   Manifestos.oid_Manifesto = '" + ed.getOID_Manifesto() + "'";

		      sql += " Order by  Notas_Fiscais.NR_Nota_Fiscal ";

		      // System.out.println ("EDI NF => " + sql);
		      res = this.executasql.executarConsulta (sql);

		      while (res.next ()) {
		        EDI_ExportacaoED edVolta = new EDI_ExportacaoED();
		        qt_notas++;
		        // System.err.println("1");
		        edVolta.setDM_Tipo_Pagamento("C");
		        if (!res.getString("oid_Pessoa_Unidade").equals(res.getString ("remetente"))) {
		          edVolta.setDM_Tipo_Pagamento ("F");
		        }

		        edVolta.setOid_Pessoa(res.getString("remetente")); //Remetente da NF
		        edVolta.setOid_Pessoa_Destinatario(res.getString("destinatario")); //Destinatario da NF

		        //edVolta.setOid_Pessoa_Redespacho(res.getString("transportador")); //Transportadora

		        edVolta.setOid_Pessoa_Unidade(res.getString("oid_Pessoa_Unidade")); //Unidade
		        edVolta.setNM_Filial_Conhecimento(res.getString("CD_Unidade"));

		        //edVolta.setNR_Conhecimento(res.getString("NR_Conhecimento"));
		        //edVolta.setDT_Emissao_Conhecimento(dataFormatada.getDT_FormataData(res.getString("DT_Emissao")));
		        edVolta.setDM_Tipo_Transporte("1");
		        // System.err.println ("2");

		        //Dados da NF
		        edVolta.setNR_Nota_Fiscal(res.getString("NR_Nota_Fiscal"));
		        edVolta.setNR_Serie_Nota_Fiscal(res.getString("NM_Serie_Nota_Fiscal"));

		        dataFormatada.setDT_FormataData (res.getString ("DT_Emissao_Nota_Fiscal"));
		        edVolta.setDT_Emissao_Nota_Fiscal (dataFormatada.getDT_FormataData ());

		        edVolta.setNR_Peso_Nota_Fiscal(res.getDouble("NR_Peso_Nota_Fiscal"));
		        edVolta.setNR_Peso_Cubado_Nota_Fiscal(res.getDouble("NR_Peso_Cubado_Nota_Fiscal"));
		        edVolta.setVL_Nota_Fiscal(res.getDouble("VL_Nota_Fiscal_Nota_Fiscal"));
		        edVolta.setNR_Volume_Nota_Fiscal(res.getDouble("NR_Volumes_Nota_Fiscal"));

		        // System.err.println ("99");

		        list.add (edVolta);
		      }
		      if (qt_notas==0){
			        EDI_ExportacaoED edVolta = new EDI_ExportacaoED();
			        edVolta.setNR_Nota_Fiscal("999999");
			        list.add (edVolta);
		      }

	      }

	    }
	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      excecoes.setMensagem ("Erro ao listar");
	      excecoes.setMetodo ("listar");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }

	    return list;
	  }

  public String consulta_Situacao (EDI_ExportacaoED ed) throws Excecoes {
    String nm_situacao = "";
    nm_situacao = "ABERTA";
    if (ed.getVL_Saldo () <= 0) {
      if ("C".equals (ed.getDM_Situacao ())) {
        nm_situacao = "CANCELADA";
      }
      else {
        nm_situacao = "LIQUIDADA";
      }
    }
    return nm_situacao;
  }

  public ArrayList exporta_Conhecimento (EDI_ExportacaoED edComp) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    EDI_ExportacaoED ed = (EDI_ExportacaoED) edComp;

    try {

      sql = " select " +
          " Conhecimentos.NM_Serie as NM_Serie_Conhecimento, " +
          " Conhecimentos.oid_Conhecimento, " +
          " Conhecimentos.oid_Pessoa, " +
          " Conhecimentos.oid_Veiculo, " +
          " Conhecimentos.oid_Carreta, " +
          " Conhecimentos.oid_Carreta2, " +
          " Conhecimentos.oid_Pessoa_Destinatario, " +
          " Conhecimentos.oid_Pessoa_Consignatario, " +
          " Conhecimentos.oid_Pessoa_Redespacho, " +
          " Conhecimentos.oid_Pessoa_Entregadora, " +
          " Conhecimentos.oid_Pessoa_Pagador, " +
          " Conhecimentos.DM_Tipo_Conhecimento, " +
          " Conhecimentos.DT_Previsao_Entrega, " +
          " Conhecimentos.HR_Previsao_Entrega, " +
          " Conhecimentos.NM_Natureza, " +
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
          " Conhecimentos.vl_total_custo, " +
          " Conhecimentos.vl_desconto, " +
          " Conhecimentos.VL_Outros2, " +
          " Conhecimentos.CD_CFO_Conhecimento, " +
          " Conhecimentos.TX_Observacao, " +
          " Unidade_Conhecimento.CD_Unidade as CD_Unidade_Conhecimento, " +
          " Unidade_Conhecimento.oid_Pessoa as oid_Pessoa_Unidade, " +
          " Modal.CD_Modal, " +
          " Produtos.nm_produto, " +
          " Cidades.NM_Cidade as NM_Cidade_Origem, " +
          " Cidade_Destinatario.NM_Cidade as NM_Cidade_Destino, " +
          " Pessoa_Remetente.NM_Razao_Social as NM_Remetente, " +
          " Pessoa_Destinatario.NM_Razao_Social as NM_Destinatario, " +
          " Notas_Fiscais.NM_Serie as NM_Serie_Nota_Fiscal, " +
          " Notas_Fiscais.NR_Nota_Fiscal, " +
          " Notas_Fiscais.NR_Pedido, " +
          " Notas_Fiscais.DT_Emissao as DT_Emissao_Nota_Fiscal, " +
          " Notas_Fiscais.NR_Peso as NR_Peso_Nota_Fiscal,  " +
          " Notas_Fiscais.NR_Volumes as NR_Volumes_Nota_Fiscal,  " +
          " Notas_Fiscais.NR_Peso_Cubado as NR_Peso_Cubado_Nota_Fiscal,  " +
          " Notas_Fiscais.VL_Nota_Fiscal as VL_Nota_Fiscal_Nota_Fiscal " +
          " FROM  Pessoas Pessoa_Remetente, Pessoas Pessoa_Destinatario, Conhecimentos, Conhecimentos_Notas_Fiscais, Notas_Fiscais, Unidades Unidade_Conhecimento, Modal,  Cidades, Cidades Cidade_Destinatario, Produtos " +
          " WHERE Conhecimentos.oid_Conhecimento = Conhecimentos_Notas_Fiscais.oid_conhecimento " +
          " AND   Conhecimentos_Notas_Fiscais.oid_Nota_Fiscal = Notas_Fiscais.oid_Nota_Fiscal " +
          " AND   Conhecimentos.oid_unidade = Unidade_Conhecimento.oid_Unidade " +
          " AND Conhecimentos.OID_Produto = Produtos.OID_Produto " +
          " AND Conhecimentos.OID_Pessoa = Pessoa_Remetente.OID_Pessoa " +
          " AND Conhecimentos.OID_Pessoa_Destinatario = Pessoa_Destinatario.OID_Pessoa " +
          " AND Conhecimentos.OID_Modal = Modal.OID_Modal " +
          " AND Conhecimentos.oid_Cidade_Destino = Cidade_Destinatario.oid_cidade " +
          " AND Conhecimentos.oid_Cidade = Cidades.oid_cidade " +
          " AND Conhecimentos.DM_Impresso = 'S' " +
          " AND Conhecimentos.VL_Total_Frete > 0" +
          " AND   Unidade_Conhecimento.oid_Empresa = " + ed.getOid_Empresa () +
          " AND   Conhecimentos.DT_Emissao >= '" + ed.getDT_Inicial () + "'" +
          " AND   Conhecimentos.DT_Emissao <= '" + ed.getDT_Final () + "'" +
          " Order by  Conhecimentos.NR_Conhecimento, Notas_Fiscais.NR_NOta_Fiscal ";

      // System.err.println (sql);

      ResultSet res = null;
      ResultSet res2 = null;

      res = this.executasql.executarConsulta (sql);

      FormataDataBean dataFormatada = new FormataDataBean ();
      double vl_icms = 0;
      while (res.next ()) {
        EDI_ExportacaoED edVolta = new EDI_ExportacaoED ();
        // System.err.println ("1 inicio");
        edVolta.setDM_Tipo_Pagamento ("C");
        if (!res.getString ("oid_Pessoa").equals (res.getString ("oid_Pessoa_Pagador"))) {
          edVolta.setDM_Tipo_Pagamento ("F");
        }
        edVolta.setDM_Substituicao_Tributaria ("N");
        if (res.getDouble ("VL_ICMS") <= 0) edVolta.setDM_Substituicao_Tributaria ("S");

        edVolta.setOid_Pessoa (res.getString ("oid_Pessoa"));

        edVolta.setOID_Veiculo (res.getString ("oid_Veiculo"));
        edVolta.setOID_Carreta (res.getString ("oid_Carreta"));
        edVolta.setOID_Carreta2 (res.getString ("oid_Carreta2"));

        edVolta.setOid_Pessoa_Destinatario (res.getString ("oid_Pessoa_Destinatario"));
        edVolta.setOid_Pessoa_Consignatario (res.getString ("oid_Pessoa_Consignatario"));
        edVolta.setOid_Pessoa_Redespacho (res.getString ("oid_Pessoa_Redespacho"));
        edVolta.setOid_Pessoa_Entregadora (res.getString ("oid_Pessoa_Entregadora"));
        // System.err.println ("1 b");

        edVolta.setNM_Remetente (res.getString ("NM_Remetente"));
        edVolta.setNM_Destinatario (res.getString ("NM_Destinatario"));
        // System.err.println ("1 c");

        edVolta.setNM_Produto (res.getString ("NM_Produto"));
        // System.err.println ("1 d");
        edVolta.setNM_Natureza (res.getString ("NM_Natureza"));

        edVolta.setTX_Observacao (res.getString ("TX_Observacao"));

        edVolta.setNM_Cidade_Origem (res.getString ("NM_Cidade_Origem"));
        edVolta.setNM_Cidade_Destino (res.getString ("NM_Cidade_Destino"));
        // System.err.println ("1 e");

        edVolta.setOid_Pessoa_Pagador (res.getString ("oid_Pessoa_Pagador"));

        edVolta.setOid_Pessoa_Unidade (res.getString ("oid_Pessoa_Unidade"));

        edVolta.setNM_Filial_Conhecimento (res.getString ("CD_Unidade_Conhecimento"));
        edVolta.setNR_Serie_Conhecimento (res.getString ("NM_Serie_Conhecimento"));
        edVolta.setNR_Conhecimento (res.getString ("NR_Conhecimento"));
        // System.err.println ("2");

        dataFormatada.setDT_FormataData (res.getString ("DT_Emissao"));
        edVolta.setDT_Emissao_Conhecimento (dataFormatada.getDT_FormataData ());

        dataFormatada.setDT_FormataData (res.getString ("DT_Previsao_Entrega"));
        edVolta.setDT_Previsao_Entrega (dataFormatada.getDT_FormataData ());

        edVolta.setHR_Previsao_Entrega (res.getString ("HR_Previsao_Entrega"));
        edVolta.setNM_Natureza (res.getString ("NM_Natureza"));
        edVolta.setTX_Observacao (res.getString ("TX_Observacao"));
        edVolta.setNR_Duplicata (res.getString ("NR_Duplicata"));

        edVolta.setDM_Tipo_Conhecimento (res.getString ("DM_Tipo_Conhecimento"));

        edVolta.setNR_Peso_Conhecimento (res.getDouble ("NR_Peso_Cubado"));
        if (edVolta.getNR_Peso_Conhecimento () <= 0) {
          edVolta.setNR_Peso_Conhecimento (res.getDouble ("NR_Peso"));
        }

        // System.err.println ("3 ok ");
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

        edVolta.setCD_CFO_Conhecimento (res.getString ("CD_CFO_Conhecimento"));

        edVolta.setNR_Nota_Fiscal (res.getString ("NR_Nota_Fiscal"));
        // System.err.println ("4 e");
        edVolta.setNR_Pedido (res.getString ("NR_Pedido"));
        edVolta.setNR_Serie_Nota_Fiscal (res.getString ("NM_Serie_Nota_Fiscal"));
        // System.err.println ("5");

        dataFormatada.setDT_FormataData (res.getString ("DT_Emissao_Nota_Fiscal"));
        edVolta.setDT_Emissao_Nota_Fiscal (dataFormatada.getDT_FormataData ());

        edVolta.setNR_Peso_Nota_Fiscal (res.getDouble ("NR_Peso_Cubado_Nota_Fiscal"));
        if (edVolta.getNR_Peso_Nota_Fiscal () <= 0) {
          edVolta.setNR_Peso_Nota_Fiscal (res.getDouble ("NR_Peso_Nota_Fiscal"));
        }
        edVolta.setVL_Nota_Fiscal (res.getDouble ("VL_Nota_Fiscal_Nota_Fiscal"));
        edVolta.setNR_Volume_Nota_Fiscal (res.getDouble ("NR_Volumes_Nota_Fiscal"));

        sql = " Select CD_Unidade from Unidades WHERE oid_Pessoa='" + res.getString ("oid_Pessoa_Entregadora") + "'";
        res2 = this.executasql.executarConsulta (sql);
        while (res2.next ()) {
          edVolta.setCD_Unidade_Entregadora (res2.getString ("CD_Unidade"));
        }

        sql = " select " +
            " Viagens.DT_Viagem, " +
            " Viagens.HR_Viagem, " +
            " Unidades.CD_Unidade, " +
            " Manifestos.NR_Manifesto " +
            " FROM Manifestos, Viagens, Unidades " +
            " WHERE Manifestos.oid_Manifesto = Viagens.oid_Manifesto " +
            " AND   Manifestos.oid_Unidade_Destino = Unidades.oid_unidade " +
            " AND   Viagens.oid_Conhecimento = '" + res.getString ("oid_Conhecimento") + "'";

        // System.err.println ("sql viagen " + sql);

        res2 = this.executasql.executarConsulta (sql);
        while (res2.next ()) {
          edVolta.setNR_Manifesto (res2.getString ("NR_Manifesto"));
          dataFormatada.setDT_FormataData (res2.getString ("DT_Viagem"));
          edVolta.setDT_Embarque (dataFormatada.getDT_FormataData ());
          edVolta.setHR_Embarque (res2.getString ("HR_Viagem"));
          edVolta.setNM_Unidade_Entregadora (res2.getString ("CD_Unidade"));
        }

        // System.err.println ("99");

        list.add (edVolta);
      }

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao listar");
      excecoes.setMetodo ("listar");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return list;
  }

  public ArrayList exporta_Nota_Fiscal_Astral (EDI_ExportacaoED edComp) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();

    try {

      sql = " select " +
          " Notas_Fiscais.*, Unidades.oid_empresa " +
          " FROM Notas_Fiscais, Unidades " +
          " WHERE Notas_Fiscais.oid_Unidade = Unidades.oid_Unidade" +
          " AND   Notas_Fiscais.DM_Tipo_Nota_Fiscal = 'D' " +
          " AND   Notas_Fiscais.DM_Situacao = 'F' " +
          " AND   (Notas_Fiscais.DM_Exportacao != 'S' or Notas_Fiscais.DM_Exportacao is null)" +
          " AND   Unidades.oid_Empresa = " + edComp.getOid_Empresa () +
          " AND   Notas_Fiscais.DT_Emissao >= '" + edComp.getDT_Inicial () + "'" +
          " AND   Notas_Fiscais.DT_Emissao <= '" + edComp.getDT_Final () + "'" +
          " Order by Notas_Fiscais.NR_Nota_Fiscal, Notas_Fiscais.DT_Emissao ";

//// System.err.println(sql);

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);

      FormataDataBean dataFormatada = new FormataDataBean ();
      Item_Nota_Fiscal_TransacoesBD ItemBD = new Item_Nota_Fiscal_TransacoesBD (this.executasql);

      while (res.next ()) {
        EDI_Nota_FiscalED ed = new EDI_Nota_FiscalED ();

        //Registro 1
        ed.setCd_empresa ("1");
        ed.setCd_unidade ("10073");
        ed.setNr_nota_fiscal (res.getString ("nr_nota_fiscal"));
        ed.setNm_serie (res.getString ("nm_serie"));
        ed.setDt_emissao (dataFormatada.getDT_FormataData (res.getString ("dt_emissao")));
        ed.setDt_recebimento (dataFormatada.getDT_FormataData (res.getString ("dt_emissao")));
        ed.setDt_base (dataFormatada.getDT_FormataData (res.getString ("dt_emissao")));
        ed.setDt_saida (dataFormatada.getDT_FormataData (res.getString ("dt_emissao")));

        //Registro 2
        ed.setVl_produtos (res.getDouble ("vl_nota_fiscal"));
        ed.setVl_nota_fiscal (res.getDouble ("vl_nota_fiscal"));

        //Registro 3

        //Registro 4
        ed.setNr_ordem_compra (res.getString ("nr_pedido"));
        //Pega os itens
        Item_Nota_Fiscal_TransacoesED notaED = new Item_Nota_Fiscal_TransacoesED ();
        notaED.setOID_Nota_Fiscal (res.getString ("oid_nota_fiscal"));
        ed.setItens (ItemBD.lista (notaED));

        //Registro 5
        ed.setNr_titulo (res.getString ("nr_nota_fiscal"));
        ed.setVl_titulo (res.getDouble ("vl_nota_fiscal"));
        ed.setDt_aceite (dataFormatada.getDT_FormataData (res.getString ("dt_emissao")));
        ed.setDt_vencimento (Data.manipulaDias (ed.getDt_aceite () , 15));

        list.add (ed);

      }

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao listar");
      excecoes.setMetodo ("listar");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return list;
  }

  private String getByGrupo_Economico (EDI_ExportacaoED ed) throws Excecoes {

    // System.out.println ("getByGrupo_Economico");
    String grupo = null;
    int qt_cli = 1;
    try {

      grupo = "('" + ed.getOid_Pessoa () + "'";
      if ("G".equals (ed.getDM_Grupo_Economico ())) {

        String sql = " SELECT Clientes.oid_Grupo_Economico " +
            " FROM   Clientes " +
            " WHERE  Clientes.oid_Cliente ='" + ed.getOid_Pessoa () + "'";

        ResultSet res = this.executasql.executarConsulta (sql);
        if (res.next ()) {

          sql = " SELECT Clientes.oid_Cliente " +
              " FROM   Clientes " +
              " WHERE  Clientes.oid_Grupo_Economico='" + res.getString ("oid_Grupo_Economico") + "'";
          ResultSet res2 = this.executasql.executarConsulta (sql);

          while (res2.next ()) {
            if (qt_cli > 0) grupo += ",";
            grupo += "'" + res2.getString ("oid_Cliente") + "'";
            qt_cli++;
          }
        }
      }
      grupo += ")";

      // System.out.println ("grupo->>" + grupo);

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("getByGrupo_Economico");
      excecoes.setMetodo ("getByGrupo_Economico");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return grupo;
  }

}
