package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.master.ed.Auxiliar1ED;
import com.master.ed.ConhecimentoED;
import com.master.ed.Item_Nota_Fiscal_TransacoesED;
import com.master.ed.Livro_FiscalED;
import com.master.ed.Natureza_OperacaoED;
import com.master.ed.Nota_Fiscal_CompraED;
import com.master.ed.Nota_Fiscal_EletronicaED;
import com.master.ed.PessoaED;
import com.master.ed.RelatorioED;
import com.master.ed.Situacao_TributariaED;
import com.master.ed.UnidadeED;
import com.master.rn.Auxiliar1RN;
import com.master.rn.Livro_FiscalRN;
import com.master.rn.Nota_Fiscal_EletronicaRN;
import com.master.root.CidadeBean;
import com.master.root.FormataDataBean;
import com.master.root.TaxaBean;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.Mensagens;
import com.master.util.Valor;
import com.master.util.bd.ExecutaSQL;

public class Livro_FiscalBD
    extends BancoUtil {

  private ExecutaSQL executasql;
  FormataDataBean dataFormatada = new FormataDataBean ();
  //*** Declarado para conservar oid antigo para inser��es em sequencia
   private int oid_Livro_Fiscal = 0;

  public Livro_FiscalBD (ExecutaSQL sql) {
    super (sql);
    this.executasql = sql;
  }

  /**
   * ENTRADAS
   */
  public void geraLivro_Fiscal_Entradas (Livro_FiscalED ed, String DM_Tipo_Livro) throws Excecoes {

	    double vl_Contabil = 0;
	    double vl_Contabil_Servico = 0;
	    double vl_Contabil_Outros = 0;
	    String OID_Nota_Fiscal = ed.getOID_Nota_Fiscal();
	    String sql = null;
	    try {

	        if (!doValida (ed.getOID_Nota_Fiscal ())) {
	            throw new Excecoes ("Nota Fiscal n�o informada!");
	          }
	          //*** Exclui se houver livro antigo da NF
	           executasql.executarUpdate ("DELETE FROM Livros_Fiscais " +
	                                      "WHERE oid_Nota_Fiscal = " + getSQLString(ed.getOID_Nota_Fiscal ()));

		// ("geraLivro_Fiscal_Entrada_Simplificada 2");

	          Nota_Fiscal_EletronicaED edNota_Fiscal = new Nota_Fiscal_EletronicaED();
	          edNota_Fiscal.setOid_nota_fiscal(ed.getOID_Nota_Fiscal());
	          edNota_Fiscal = new Nota_Fiscal_EletronicaBD(executasql).getByRecord(edNota_Fiscal);
            if ("N".equals (edNota_Fiscal.edModelo.getDM_Gera_Fiscal ())) {
                return;
              }

            // *** Se a NF for cancelada cria um registro com valores zerados e obs = "CANCELADA"
            if ("C".equals (edNota_Fiscal.getDM_Situacao ())) {
              //*** Busca Dados da Pessoa
            	PessoaED edPessoa = new  PessoaED();
            	if (edNota_Fiscal.edModelo.getOid_Modelo_Nota_Fiscal() == 21){ // Compra de Ativo Imobilizado
                    edPessoa = new PessoaBD (executasql).getByRecord (new PessoaED (edNota_Fiscal.getOid_pessoa()));
            	}else{
                    edPessoa = new PessoaBD (executasql).getByRecord (new PessoaED (edNota_Fiscal.getOid_pessoa_destinatario ()));
            	}

              Natureza_OperacaoED edNOP = new Natureza_OperacaoBD (executasql).getByRecord (new Natureza_OperacaoED (new Integer ( (int) edNota_Fiscal.getOid_natureza_operacao ())));
              ed.setDM_Tipo_Imposto (edNOP.getDm_Tipo_Imposto ());
              ed.setCD_Estado (edPessoa.getCD_Estado ());
              ed.setOID_Natureza_Operacao (edNota_Fiscal.getOid_natureza_operacao ());
              ed.setCD_Natureza_Operacao (edNOP.getCd_Natureza_Operacao ());
              ed.setNM_Natureza_Operacao (edNOP.getNm_Natureza_Operacao ());
              ed.setCD_Unidade ("");
              ed.setDM_Situacao ("S");
              ed.setDM_Tipo_Documento ("NF");
              ed.setCD_Especie ("NF");
              ed.setDM_Tipo_Livro ("E"); // E ou S
              ed.setDT_Emissao (doValida (edNota_Fiscal.getDt_entrada ()) ? edNota_Fiscal.getDt_entrada () : Data.getDataDMY ());
              ed.setDT_Entrada (edNota_Fiscal.getDt_entrada ());
              ed.setNM_Modelo_Documento (edNota_Fiscal.edModelo.getCD_Modelo_Nota_Fiscal ());
              if ("S".equals (DM_Tipo_Livro)) {
                ed.setNM_Serie_Documento (" ");
              }
              else {
                ed.setNM_Serie_Documento (edNota_Fiscal.getNm_serie ());
              }
              ed.setNR_Documento (String.valueOf (edNota_Fiscal.getNr_nota_fiscal ()));
              ed.setOID_Conhecimento ("");
              ed.setOID_Pessoa_Emitente (edNota_Fiscal.getOid_pessoa ());
              ed.setOID_Tipo_Documento (0);
              ed.setOID_Unidade (edNota_Fiscal.getOID_Unidade_Fiscal ());
              ed.setTX_Observacao ("CANCELADA");
              ed.setTX_Mensagem ("CANCELADA");
              ed.setVL_Imposto (0);
              ed.setPE_Aliquota (0);
              ed.setVL_Base_Calculo (0);
              ed.setVL_Contabil (0);
              ed.setVL_Imposto_Creditado (0);
              ed.setDM_Tipo_Operacao ("0");
              this.inclui (ed);
              return;
            }

	          if (1==1) { //"F".equals(edNota_Fiscal.getDm_finalizado()) || "S".equals(edNota_Fiscal.getDm_finalizado())) {

	        	vl_Contabil=edNota_Fiscal.getVl_liquido_nota_fiscal();

	        	//FISCAL PARTE PRINCIPAL
	        	if (vl_Contabil>0 || edNota_Fiscal.getVl_icms()>0) {
		        	Natureza_OperacaoED edNOP = new Natureza_OperacaoBD(executasql).getByRecord(new Natureza_OperacaoED(new Integer((int)edNota_Fiscal.getOid_natureza_operacao())));
		    	    // ("geraLivro_Fiscal_Entrada_Simplificada 5");

		        	if (!"N".equals(edNOP.getDm_Tipo_Imposto())){   ///TIPO N NAO GERA FISCAL
		        	    // ("geraLivro_Fiscal_Entrada_Simplificada 6");

		            	PessoaED edPessoa = new  PessoaED();
		            	if (edNota_Fiscal.edModelo.getOid_Modelo_Nota_Fiscal() == 21){ // Compra de Ativo Imobilizado
		                    edPessoa = new PessoaBD (executasql).getByRecord (new PessoaED (edNota_Fiscal.getOid_pessoa()));
		            	}else{
		                    edPessoa = new PessoaBD (executasql).getByRecord (new PessoaED (edNota_Fiscal.getOid_pessoa_destinatario ()));
		            	}

		        	    // ("geraLivro_Fiscal_Entrada_Simplificada 7");
			            ed.setCD_Estado(edPessoa.getCD_Estado());
			            ed.setDM_Tipo_Livro("E"); // E ou S

			            ed.setDT_Emissao(doValida(edNota_Fiscal.getDt_entrada()) ? edNota_Fiscal.getDt_entrada() : Data.getDataDMY());
			            ed.setDT_Entrada(edNota_Fiscal.getDt_entrada());
			            ed.setNM_Modelo_Documento("NF");
		                if ("S".equals (DM_Tipo_Livro)) {
		                  ed.setNM_Serie_Documento (" ");
		                }
		                else {
		                  ed.setNM_Serie_Documento (edNota_Fiscal.getNm_serie ());
		                }
			            ed.setNR_Documento(String.valueOf(edNota_Fiscal.getNr_nota_fiscal()));
			            ed.setOID_Conhecimento ("");
			            ed.setOID_Pessoa_Emitente(edNota_Fiscal.getOid_pessoa());
			            ed.setOID_Tipo_Documento(0);
			            ed.setOID_Unidade(edNota_Fiscal.getOID_Unidade_Fiscal());
			            ed.setTX_Observacao("");
			            ed.setTX_Mensagem("");
			            ed.setCD_Unidade("");
			            ed.setDM_Situacao("S");

			            boolean DM_Tem_Nota_Com_Produto = false;

			            sql= " select sum(round(vl_produto,2)) as vl_liquido, " +
			            	 " sum(itens_notas_fiscais.vl_icms) as vl_icms, " +
			            	 " sum(itens_notas_fiscais.vl_base_calculo_icms) as vl_base_calculo_icms, " +
			            	 " itens_notas_fiscais.pe_aliquota_icms, " +
			            	 " itens_notas_fiscais.oid_natureza_operacao, " +
			            	 " vl_nota_fiscal as vl_nota_fiscal " +
			            	 " from notas_fiscais, itens_notas_fiscais " +
			            	 " where notas_fiscais.oid_Nota_Fiscal = '" + ed.getOID_Nota_Fiscal () + "' " +
			            	 " and itens_notas_fiscais.oid_Nota_Fiscal = notas_fiscais.oid_Nota_Fiscal " +
			            	 " group by itens_notas_fiscais.pe_aliquota_icms , " +
			            	 "          itens_notas_fiscais.oid_natureza_operacao, " +
			            	 "          vl_nota_fiscal " +
			            	 " order by itens_notas_fiscais.pe_aliquota_icms desc, itens_notas_fiscais.oid_natureza_operacao ";
			            ResultSet resLocal = this.executasql.executarConsulta (sql);
			            double vl_produto_nota_fiscal = 0;
			            while (resLocal.next ()) {
			            	DM_Tem_Nota_Com_Produto = true;
				        	edNOP = new Natureza_OperacaoBD(executasql).getByRecord(new Natureza_OperacaoED(new Integer((int)resLocal.getInt("oid_natureza_operacao"))));
				            ed.setOID_Natureza_Operacao(resLocal.getInt("oid_natureza_operacao"));
				            ed.setCD_Natureza_Operacao(edNOP.getCd_Natureza_Operacao ());
				            ed.setNM_Natureza_Operacao(edNOP.getNm_Natureza_Operacao ());
			        		ed.setDM_Tipo_Imposto(edNOP.getDm_Tipo_Imposto());
				            ed.setDM_Tipo_Documento("NF");
				            ed.setCD_Especie("NF");

				    	    double vlImposto_Gerado = 0;
				    	    double vlOutro = 0;
				    	    double vlBaseCalculo = (resLocal.getDouble("vl_icms")> 0 ? resLocal.getDouble("vl_base_calculo_icms") : 0);
				            double vlContabil = Valor.round(resLocal.getDouble("vl_liquido"),2);
				            double vlImposto = resLocal.getDouble("vl_icms");
				            double vlImposto_Debitado = resLocal.getDouble("vl_icms");
				            double vlNota_Fiscal = Valor.round(resLocal.getDouble("vl_nota_fiscal"),2);

				            vl_produto_nota_fiscal = vl_produto_nota_fiscal + vlContabil;

				             if (vl_produto_nota_fiscal > vlNota_Fiscal){
				            	 vl_produto_nota_fiscal = vlNota_Fiscal;
				             }
						      if (vlBaseCalculo>0){
							      vlImposto_Gerado += Valor.round (vlContabil - vlBaseCalculo , 2);
						      }else{
						    	  vlImposto_Gerado=0;
						      }

						      ed.setVL_Imposto (vlImposto);
						      ed.setVL_Outro(vlOutro);
						      ed.setVL_Base_Calculo (vlBaseCalculo);
						      ed.setVL_Contabil (vlContabil-vlImposto_Gerado);
						      ed.setVL_Imposto_Creditado (vlImposto_Debitado);

						      if (vlImposto == 0) { //ISENTO
						    	  ed.setPE_Aliquota(0);
					                if ("O".equals (ed.getDM_Tipo_Imposto ()) && !"1101".equals (ed.getCD_Natureza_Operacao ())) { // Se for Outros
					                 ed.setDM_Tipo_Operacao ("3");
					                 ed.setPE_Aliquota(1);
					               }
					               else if ("I".equals (ed.getDM_Tipo_Imposto ())) { // Se for Isento
					                 ed.setDM_Tipo_Operacao ("2");

					               }
					               else if (resLocal.getDouble("pe_aliquota_icms") == 0 && !"1102".equals (ed.getCD_Natureza_Operacao ())) { //  Se n�o tiver aliquota e for diferente de 1102
					               	ed.setDM_Tipo_Operacao ("2");
					               }

					          }else { //TRIBUTADO
				        		  ed.setDM_Tipo_Operacao("1");
				        		  ed.setPE_Aliquota(resLocal.getDouble("pe_aliquota_icms"));
					          }

						      this.inclui (ed);

						      if (vlImposto_Gerado > 0){
						    	 if (resLocal.isLast()){
						    		if (vlNota_Fiscal > vl_produto_nota_fiscal || vlNota_Fiscal < vl_produto_nota_fiscal){
						    			vlImposto_Gerado = vlImposto_Gerado + vlNota_Fiscal - vl_produto_nota_fiscal;
						    		}
								    if (vlImposto_Gerado > 0){
								         ed.setDM_Tipo_Operacao ("2");
									     ed.setVL_Imposto (vlImposto_Gerado);
									     ed.setVL_Outro(0);
									     ed.setPE_Aliquota (0);
									     ed.setVL_Base_Calculo (0);
									     ed.setVL_Contabil (vlImposto_Gerado);
									     ed.setVL_Imposto_Creditado (0);
									     this.inclui (ed);
								    }
						    	 }
						      }
			            }

			            if (!DM_Tem_Nota_Com_Produto){

				            ed.setOID_Natureza_Operacao(edNota_Fiscal.getOid_natureza_operacao ());
				            ed.setCD_Natureza_Operacao(edNOP.getCd_Natureza_Operacao ());
				            ed.setNM_Natureza_Operacao(edNOP.getNm_Natureza_Operacao ());
			        		ed.setDM_Tipo_Imposto(edNOP.getDm_Tipo_Imposto());
				            ed.setDM_Tipo_Documento("NF");
				            ed.setCD_Especie("NF");
				            ed.setVL_Imposto(edNota_Fiscal.getVl_icms());
				            ed.setVL_Base_Calculo(edNota_Fiscal.getVl_icms()> 0 ? edNota_Fiscal.getVL_Base_Calculo_ICMS() : 0);
				            ed.setVL_Contabil (vl_Contabil);
				            ed.setVL_Imposto_Creditado(edNota_Fiscal.getVl_icms());
				            if (edNota_Fiscal.getVl_icms() == 0) { //ISENTO
				            	ed.setPE_Aliquota(0);
				                if ("O".equals (ed.getDM_Tipo_Imposto ()) && (!"1101".equals (ed.getCD_Natureza_Operacao ()))) { // Se for Outros
				                 ed.setDM_Tipo_Operacao ("3");
				                 ed.setPE_Aliquota(1);
				               }
				               else if ("I".equals (ed.getDM_Tipo_Imposto ())) { // Se for Isento
				                 ed.setDM_Tipo_Operacao ("2");

				               }
				               else if (edNota_Fiscal.getPE_Aliquota_ICMS () == 0 && !"1102".equals (ed.getCD_Natureza_Operacao ())) { //  Se n�o tiver aliquota e for diferente de 1102

				               	ed.setDM_Tipo_Operacao ("2");
				               }

				            }else { //TRIBUTADO
						        if (edNota_Fiscal.getPE_Aliquota_ICMS () == 0 && !"1102".equals (ed.getCD_Natureza_Operacao ()) && !"1604".equals (ed.getCD_Natureza_Operacao ())) { //  Se n�o tiver aliquota e for diferente de 1102
							        ed.setDM_Tipo_Operacao ("2");
								    ed.setPE_Aliquota(edNota_Fiscal.getPE_Aliquota_ICMS());
						        }else{
					            	ed.setDM_Tipo_Operacao("1");
						            ed.setPE_Aliquota(edNota_Fiscal.getPE_Aliquota_ICMS());
						        }
				            }

				            this.inclui (ed);
			            }
	            	}
	        	}

	          }

	    }
	    catch (Exception exc) {
	      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "geraLivro_Fiscal_Entrada_Simplificada()");
	    }
	  }


  public void geraLivro_Fiscal_Entradas25112009 (Livro_FiscalED ed , String DM_Tipo_Livro) throws Excecoes {

    long oldCFOP = 0;
    double vlContabil = 0;
    double vlImposto = 0;
    double vlImposto_Creditado = 0;
    double vlBaseCalculo = 0;
    double peAliquota = 0;
    boolean isCFOPSubst = false;
    double vlOpe1 = 0;
    double vlOpe2 = 0;
    double vlOpe3 = 0;
    ArrayList lista = new ArrayList ();

    try {
      if (!doValida (ed.getOID_Nota_Fiscal ())) {
        throw new Excecoes ("Nota Fiscal n�o informada!");
      }
      //*** Exclui se houver livro antigo da NF
       executasql.executarUpdate ("DELETE FROM Livros_Fiscais " +
                                  "WHERE oid_Nota_Fiscal = " + getSQLString (ed.getOID_Nota_Fiscal ()));

      Nota_Fiscal_EletronicaED edNota_Fiscal = new Nota_Fiscal_EletronicaBD (executasql).getByRecord (new Nota_Fiscal_EletronicaED (ed.getOID_Nota_Fiscal ()));

      // *** Se a NF for cancelada cria um registro com valores zerados e obs = "CANCELADA"
      if ("C".equals (edNota_Fiscal.getDM_Situacao ())) {
        //*** Busca Dados da Pessoa
         PessoaED edPessoa = new PessoaBD (executasql).getByRecord (new PessoaED (edNota_Fiscal.getOid_pessoa ()));
        Natureza_OperacaoED edNOP = new Natureza_OperacaoBD (executasql).getByRecord (new Natureza_OperacaoED (new Integer ( (int) edNota_Fiscal.getOid_natureza_operacao ())));
        ed.setDM_Tipo_Imposto (edNOP.getDm_Tipo_Imposto ());
        ed.setCD_Estado (edPessoa.getCD_Estado ());
        ed.setOID_Natureza_Operacao (edNota_Fiscal.getOid_natureza_operacao ());
        ed.setCD_Natureza_Operacao (edNOP.getCd_Natureza_Operacao ());
        ed.setNM_Natureza_Operacao (edNOP.getNm_Natureza_Operacao ());
        ed.setCD_Unidade ("");
        ed.setDM_Situacao ("S");
        ed.setDM_Tipo_Documento ("NF");
        ed.setCD_Especie ("NF");
        ed.setDM_Tipo_Livro (DM_Tipo_Livro); // E ou S
        ed.setDT_Emissao (doValida (edNota_Fiscal.getDt_emissao ()) ? edNota_Fiscal.getDt_emissao () : Data.getDataDMY ());
        ed.setDT_Entrada (edNota_Fiscal.getDt_entrada ());
        ed.setNM_Modelo_Documento (edNota_Fiscal.edModelo.getCD_Modelo_Nota_Fiscal ());
        if ("S".equals (DM_Tipo_Livro)) {
          ed.setNM_Serie_Documento (" ");
        }
        else {
          ed.setNM_Serie_Documento (edNota_Fiscal.getNm_serie ());
        }
        ed.setNR_Documento (String.valueOf (edNota_Fiscal.getNr_nota_fiscal ()));
        ed.setOID_Conhecimento ("");
        ed.setOID_Pessoa_Emitente (edNota_Fiscal.getOid_pessoa ());
        ed.setOID_Tipo_Documento (0);
        ed.setOID_Unidade (edNota_Fiscal.getOID_Unidade_Fiscal ());
        ed.setTX_Observacao ("CANCELADA");
        ed.setTX_Mensagem ("CANCELADA");
        ed.setVL_Imposto (0);
        ed.setPE_Aliquota (0);
        ed.setVL_Base_Calculo (0);
        ed.setVL_Contabil (0);
        ed.setVL_Imposto_Creditado (0);
        ed.setDM_Tipo_Operacao ("0");
        this.inclui (ed);
        return;
      }

      //*** Busca Itens da NF
       lista = new Item_Nota_Fiscal_TransacoesBD (executasql).lista (new Item_Nota_Fiscal_TransacoesED (ed.getOID_Nota_Fiscal ()));
      if (lista.size () > 0) {
        /** Lista Ordenada
                      // Este m�todo ordena uma lista */
        Collections.sort (lista , new Comparator () {
          public int compare (Object o1 , Object o2) {
            return String.valueOf ( ( (Item_Nota_Fiscal_TransacoesED) o1).getPE_Aliquota_ICMS ()).compareTo (String.valueOf ( ( (Item_Nota_Fiscal_TransacoesED) o2).getPE_Aliquota_ICMS ()));
          }
        }
        );
        Collections.sort (lista , new Comparator () {
          public int compare (Object o1 , Object o2) {
            return (String.valueOf ( ( (Item_Nota_Fiscal_TransacoesED) o1).getOid_natureza_operacao ()).compareTo (String.valueOf ( ( (Item_Nota_Fiscal_TransacoesED) o2).getOid_natureza_operacao ())));
          }
        }
        );

        //*** Atualiza ICMS
         new Nota_Fiscal_EletronicaBD (executasql).atualizaValorICMS (ed.getOID_Nota_Fiscal ());
        //*** Busca Dados da NF
         edNota_Fiscal = new Nota_Fiscal_EletronicaBD (executasql).getByRecord (new Nota_Fiscal_EletronicaED (ed.getOID_Nota_Fiscal ()));

        if (!doValida (edNota_Fiscal.getOid_nota_fiscal ())) {
          throw new Excecoes ("Nota Fiscal n�o encontrada!");
        }
        if ("N".equals (edNota_Fiscal.edModelo.getDM_Gera_Fiscal ())) {
          return;
        }

        //*** Busca Descri��o do Modelo
         ed.setNM_Modelo_Documento (edNota_Fiscal.edModelo.getCD_Modelo_Nota_Fiscal ());

        //*** Busca Dados da Pessoa
         PessoaED edPessoa = new PessoaBD (executasql).getByRecord (new PessoaED (edNota_Fiscal.getOid_pessoa ()));

        double vlICMSItens = 0 , vlICMSCredItens = 0 , vlItens = 0 , vlBCItens = 0;
        for (int i = 0; i < lista.size (); i++) {
          Item_Nota_Fiscal_TransacoesED edItem_NF = (Item_Nota_Fiscal_TransacoesED) lista.get (i);
          //*** Quebra por Natureza de Opera��o
           if (oldCFOP > 0 && oldCFOP != edItem_NF.getOid_natureza_operacao ()) {
             if (isCFOPSubst) {
               ed.setVL_Base_Calculo (ed.getVL_Base_Calculo () - edNota_Fiscal.getVL_ICMS_Subst ());
             }
             this.inclui (ed);
             vlContabil = 0;
             vlImposto = 0;
             vlImposto_Creditado = 0;
             vlBaseCalculo = 0;
             //*** Quebra por PE_Aliquota_ICMS
           }
           else
           if (oldCFOP > 0 && peAliquota != edItem_NF.getPE_Aliquota_ICMS ()) {
             this.inclui (ed);
             vlContabil = 0;
             vlImposto = 0;
             vlImposto_Creditado = 0;
             vlBaseCalculo = 0;
           }
          //*** Busca NOP
           Natureza_OperacaoED edNOP = new Natureza_OperacaoBD (executasql).getByRecord (new Natureza_OperacaoED (new Integer ( (int) edItem_NF.getOid_natureza_operacao ())));
          ed.setDM_Tipo_Imposto (edNOP.getDm_Tipo_Imposto ());

          ed.setCD_Estado (edPessoa.getCD_Estado ());
          ed.setOID_Natureza_Operacao (edItem_NF.getOid_natureza_operacao ());
          ed.setCD_Natureza_Operacao (edNOP.getCd_Natureza_Operacao ());
          ed.setNM_Natureza_Operacao (edNOP.getNm_Natureza_Operacao ());
          ed.setCD_Unidade (edItem_NF.getCD_Unidade ());
          ed.setDM_Situacao ("S");
          ed.setDM_Tipo_Documento ("NF");
          ed.setCD_Especie ("NF");
          ed.setDM_Tipo_Livro (DM_Tipo_Livro); // E ou S
          ed.setDT_Emissao (doValida (edNota_Fiscal.getDt_emissao ()) ? edNota_Fiscal.getDt_emissao () : Data.getDataDMY ());
          ed.setDT_Entrada (edNota_Fiscal.getDt_entrada ());
          ed.setNM_Modelo_Documento (edNota_Fiscal.edModelo.getCD_Modelo_Nota_Fiscal ());
          if ("S".equals (DM_Tipo_Livro)) {
            ed.setNM_Serie_Documento (" ");
          }
          else {
            ed.setNM_Serie_Documento (edNota_Fiscal.getNm_serie ());
          }
          ed.setNR_Documento (String.valueOf (edNota_Fiscal.getNr_nota_fiscal ()));
          ed.setOID_Conhecimento ("");
          ed.setOID_Pessoa_Emitente (edNota_Fiscal.getOid_pessoa ());
          ed.setOID_Tipo_Documento (0);
          ed.setOID_Unidade (edNota_Fiscal.getOID_Unidade_Fiscal ());
          ed.setTX_Observacao ("");

          //*** Agrega Valores
           //Valor.round(edItem_NF.getVL_Item()-edItem_NF.getVL_Desconto_NF(),2);
           vlContabil += Valor.round (edItem_NF.getVL_Item () - edItem_NF.getVL_Desconto_NF () , 2); //  edItem_NF.getVL_Item(); //Valor.round(edItem_NF.getVL_Item()-edItem_NF.getVL_Desconto_NF(),2);
          vlImposto += Valor.round (edItem_NF.getVL_ICMS () , 2);
          vlImposto_Creditado += Valor.round (edItem_NF.getVL_ICMS_Aprov () , 2);
          vlBaseCalculo += Valor.round (edItem_NF.getVL_Base_Calculo_ICMS () , 2);

          ed.setPE_Aliquota (edItem_NF.getPE_Aliquota_ICMS ());
          ed.setVL_Base_Calculo (vlBaseCalculo);
          ed.setVL_Contabil (vlContabil);
          ed.setVL_Imposto (vlImposto);
          ed.setVL_Imposto_Creditado (vlImposto_Creditado);

          oldCFOP = ed.getOID_Natureza_Operacao ();
          isCFOPSubst = (edNota_Fiscal.getVL_ICMS_Subst () > 0 && "4".equals (Character.toString (ed.getCD_Natureza_Operacao ().charAt (1)))); ;
          peAliquota = ed.getPE_Aliquota ();

          //*** Busca CD_Situa��o_Tribut�ria
           int oid_Situacao_Tributaria = getTableIntValue ("oid_Situacao_Tributaria" ,
                                                           "Produtos" ,
                                                           "oid_Produto = '" + edItem_NF.getOID_Produto () + "'");
          Situacao_TributariaED edSit = new Situacao_TributariaBD (executasql).getByOidSituacao_Tributaria (oid_Situacao_Tributaria);
          ed.setTX_Mensagem (edSit.getNM_Situacao_Tributaria ());
          String codSit = edSit.getCD_Situacao_Tributaria ();
          if ("O".equals (ed.getDM_Tipo_Imposto ()) || codSit.endsWith ("51") || codSit.endsWith ("60") || codSit.endsWith ("81")) {
            ed.setDM_Tipo_Operacao ("3");
            vlOpe3 = vlOpe3 + Valor.round (edItem_NF.getVL_Item () - edItem_NF.getVL_Desconto_NF () , 2);
            // 1905 REMESSA PARA ARMAZEM GERAL
            if ("1905".equals(ed.getCD_Natureza_Operacao())){
                ed.setVL_Base_Calculo (0);
            }else{
                ed.setVL_Base_Calculo (vlContabil);
            }

          }
          else
          //*** Tipo de Opera��o
           if ("I".equals (ed.getDM_Tipo_Imposto ()) || edItem_NF.getPE_Aliquota_ICMS () == 0) {
             ed.setDM_Tipo_Operacao ("2");
             if("SP".equals(edPessoa.getCD_Estado())){
 	      		 //SP � outros e n�o isento...
             	 ed.setDM_Tipo_Operacao ("3");
              }
             vlOpe2 = vlOpe2 + Valor.round (edItem_NF.getVL_Item () - edItem_NF.getVL_Desconto_NF () , 2);
           }
           else {
             ed.setDM_Tipo_Operacao ("1");
             vlOpe1 = vlOpe1 + Valor.round (edItem_NF.getVL_Item () - edItem_NF.getVL_Desconto_NF () , 2);
             // 2905 - ENTRADA PARA ARMAZEM GERAL
             if ("2905".equals(ed.getCD_Natureza_Operacao())){
            	 ed.setVL_Contabil(ed.getVL_Base_Calculo());
             }

           }
          //*** Vari�veis pra fechamento
           vlICMSItens += Valor.round (edItem_NF.getVL_ICMS () , 2);
          vlICMSCredItens += Valor.round (edItem_NF.getVL_ICMS_Aprov () , 2);
          vlItens += Valor.round (edItem_NF.getVL_Item () - edItem_NF.getVL_Desconto_NF () , 2);
          vlBCItens += Valor.round (edItem_NF.getVL_Base_Calculo_ICMS () , 2);

          //*** Se for ultimo registro
           //  - Ajusta valores pela NF
           if ( (i + 1) == lista.size ()) {
             double vlNota = Valor.round (edNota_Fiscal.getVl_liquido_nota_fiscal () - edNota_Fiscal.getVl_ipi () - edNota_Fiscal.getVL_Servico () - edNota_Fiscal.getVl_total_despesas () , 2);
             double vlICMSNota = Valor.round (edNota_Fiscal.getVl_icms () , 2);
             vlItens = Valor.round (vlItens , 2);
             vlICMSItens = Valor.round (vlICMSItens , 2);
             vlICMSCredItens = Valor.round (vlICMSCredItens , 2);
             vlBCItens = Valor.round (vlBCItens , 2);
             double vlTotalDif = 0 , vlICMSDif = 0;

             //*** ICMS
              if (vlICMSNota > 0 && vlICMSNota != vlICMSItens) {
                vlICMSDif = Valor.round (Math.max (vlICMSNota , vlICMSItens) - Math.min (vlICMSNota , vlICMSItens) , 2);
                if (vlICMSNota > vlICMSItens) {
                  ed.setVL_Imposto (ed.getVL_Imposto () + vlICMSDif);
                }
                else {
                  ed.setVL_Imposto (ed.getVL_Imposto () - vlICMSDif);
                }
              }
             //*** ICMS Creditado
              if (vlICMSNota > 0 && vlICMSNota != vlICMSCredItens) {
                vlICMSDif = Valor.round (Math.max (vlICMSNota , vlICMSCredItens) - Math.min (vlICMSNota , vlICMSCredItens) , 2);
                if (vlICMSNota > vlICMSCredItens) {
                  ed.setVL_Imposto_Creditado (ed.getVL_Imposto_Creditado () + vlICMSDif);
                }
                else {
                  ed.setVL_Imposto_Creditado (ed.getVL_Imposto_Creditado () - vlICMSDif);
                }
              }
             //*** VALOR - Alterado por Regis em 06/06  - (vlNota-edNota_Fiscal.getVL_ICMS_Subst()) N�o levava em conta o valor do icms subst....
              // Acerta o valor contabil do ultimo lancamento pela diferenca
              if (Valor.round (vlNota - edNota_Fiscal.getVL_ICMS_Subst () , 2) != vlItens || edNota_Fiscal.getVl_ipi () > 0 || edNota_Fiscal.getVl_total_despesas () > 0) {
                vlTotalDif = Valor.round (Math.max (vlNota , vlItens) - Math.min (vlNota , vlItens) , 2);
                if (vlNota > vlItens) {
                  ed.setVL_Contabil (ed.getVL_Contabil () + vlTotalDif + edNota_Fiscal.getVl_ipi () + edNota_Fiscal.getVl_total_despesas ());
                }
                else {
                  ed.setVL_Contabil (ed.getVL_Contabil () - vlTotalDif + edNota_Fiscal.getVl_ipi () + edNota_Fiscal.getVl_total_despesas ());
                }
              }
             // Acerta a base de calculo se ela ficar maior ou menor 0,01 que o valor contabil dos itens da nota fiscal
             if (Valor.validaTolerancia (vlNota , vlBCItens , 0.01)) {
               vlTotalDif = Valor.round (Math.max (vlNota , vlBCItens) - Math.min (vlNota , vlBCItens) , 2);
               if (vlBCItens > vlNota) {
                 ed.setVL_Base_Calculo (ed.getVL_Base_Calculo () - vlTotalDif);
               }
               else {
                 ed.setVL_Base_Calculo (ed.getVL_Base_Calculo () + vlTotalDif);
               }
             }

             //*** Substituicao Tributaria acresce no valor contabil - Por Regis em 06/06
              if (isCFOPSubst) {
                ed.setVL_Contabil (ed.getVL_Contabil () + edNota_Fiscal.getVL_ICMS_Subst ());
              }
             //*** Arredondamento [Credito Imposto] cod. 1
              if (ed.getVL_Base_Calculo () >= 0) {
                if (!"1".equals (ed.getDM_Tipo_Operacao ())) {
                  ed.setVL_Imposto (0);
                  ed.setVL_Imposto_Creditado (0);
                  ed.setPE_Aliquota (0);
                }

                this.inclui (ed);
              }
             //*** Quebra por Base de Calculo(cod. 2) - Alterado por Regis em 06/06 calculo do valor liquido da nota estava errado
              vlNota = Valor.round (vlNota - edNota_Fiscal.getVl_total_frete () - edNota_Fiscal.getVl_total_seguro () , 2);
             //**
              /*	Isto acabou ficando meio 'gambiarrado' na verdade.
               *  Originalmente o programa ao chegar neste ponto colocava toda a diferenca entre o valor da nota e base de calculo para Isento (2).
               *  Acontece que quando notas devolvidas na natureza 1411 por exemplo n�o tem base de calculo e o valor acabava caindo em isento
               *  causando erro no modelo b ( valor negativo na coluna outros ) e nos livro fiscal de entrada.
               *  Ent�o o que foi feito: O calculo para isento agora est� sendo diminuido do valor de outros vlOpe3
               */
              if (edNota_Fiscal.getVL_Base_Calculo_ICMS () > 0 && !Valor.validaTolerancia (vlNota , edNota_Fiscal.getVL_Base_Calculo_ICMS () , 1)) {
                if (Valor.round (vlNota - edNota_Fiscal.getVL_Base_Calculo_ICMS () - vlOpe3 , 2) > 0) { // Se o resto for > que zero
                  Natureza_OperacaoED edNatureza = new Natureza_OperacaoBD (executasql).getByRecord (new Natureza_OperacaoED (new Integer ( (int) edNota_Fiscal.getOid_natureza_operacao ())));
                  ed.setDM_Tipo_Imposto (edNatureza.getDm_Tipo_Imposto ());
                  ed.setOID_Natureza_Operacao (edNota_Fiscal.getOid_natureza_operacao ());
                  ed.setCD_Natureza_Operacao (edNatureza.getCd_Natureza_Operacao ());
                  ed.setNM_Natureza_Operacao (edNatureza.getNm_Natureza_Operacao ());

                  ed.setDM_Tipo_Operacao ("2");
                  if (ed.getCD_Natureza_Operacao().equals("2905")){
                      ed.setVL_Base_Calculo (0);
                      ed.setVL_Contabil (Valor.round (vlNota - edNota_Fiscal.getVL_Base_Calculo_ICMS () - vlOpe3 , 2));
                  }else{
                      ed.setVL_Base_Calculo (Valor.round (vlNota - edNota_Fiscal.getVL_Base_Calculo_ICMS () - vlOpe3 , 2));
                      ed.setVL_Contabil (0);
                  }
                  ed.setVL_Imposto (0);
                  ed.setVL_Imposto_Creditado (0);
                  ed.setPE_Aliquota (0);
                  // Insert
                  if (ed.getVL_Base_Calculo () >= 0) {
                    this.inclui (ed);
                  }
                }
              }
             //*** Se Nota Possui Natureza de Opera��o de Servi�o
              if (edNota_Fiscal.getOid_Natureza_Operacao_Servico () > 0 && edNota_Fiscal.getVL_Servico () > 0) {
                Natureza_OperacaoED edNatureza = new Natureza_OperacaoBD (executasql).getByRecord (new Natureza_OperacaoED (new Integer (edNota_Fiscal.getOid_Natureza_Operacao_Servico ())));
                ed.setDM_Tipo_Imposto (edNatureza.getDm_Tipo_Imposto ());
                ed.setOID_Natureza_Operacao (edNota_Fiscal.getOid_Natureza_Operacao_Servico ());
                ed.setCD_Natureza_Operacao (edNatureza.getCd_Natureza_Operacao ());
                ed.setNM_Natureza_Operacao (edNatureza.getNm_Natureza_Operacao ());

                ed.setDM_Tipo_Operacao ("3");
                ed.setVL_Base_Calculo (edNota_Fiscal.getVL_Servico ());
                ed.setVL_Contabil (edNota_Fiscal.getVL_Servico ());
                ed.setVL_Imposto (0);
                ed.setVL_Imposto_Creditado (0);
                ed.setPE_Aliquota (0);
                this.inclui (ed);
              }
           }
        }

      }
      else {

        //*** Atualiza ICMS
         new Nota_Fiscal_EletronicaBD (executasql).atualizaValorICMS (ed.getOID_Nota_Fiscal ());
        //*** Busca Dados da NF
         edNota_Fiscal = new Nota_Fiscal_EletronicaBD (executasql).getByRecord (new Nota_Fiscal_EletronicaED (ed.getOID_Nota_Fiscal ()));
        if ("N".equals (edNota_Fiscal.edModelo.getDM_Gera_Fiscal ())) {
          return;
        }
        //*** Busca Descri��o do Modelo
         ed.setNM_Modelo_Documento (edNota_Fiscal.edModelo.getCD_Modelo_Nota_Fiscal ());
        //*** Busca Dados da Pessoa
         PessoaED edPessoa = new PessoaBD (executasql).getByRecord (new PessoaED (edNota_Fiscal.getOid_pessoa ()));
        //*** Busca NOP
         Natureza_OperacaoED edNOP = new Natureza_OperacaoBD (executasql).getByRecord (new Natureza_OperacaoED (new Integer ( (int) edNota_Fiscal.getOid_natureza_operacao ())));
        ed.setDM_Tipo_Imposto (edNOP.getDm_Tipo_Imposto ());
        ed.setCD_Estado (edPessoa.getCD_Estado ());
        ed.setOID_Natureza_Operacao (edNota_Fiscal.getOid_natureza_operacao ());
        ed.setCD_Natureza_Operacao (edNOP.getCd_Natureza_Operacao ());
        ed.setNM_Natureza_Operacao (edNOP.getNm_Natureza_Operacao ());
        ed.setCD_Unidade (edNota_Fiscal.getCD_Unidade ());
        ed.setDM_Situacao ("S");
        ed.setDM_Tipo_Documento ("NF");
        ed.setCD_Especie ("NF");
        ed.setDM_Tipo_Livro (DM_Tipo_Livro); // E ou S
        ed.setDT_Emissao (doValida (edNota_Fiscal.getDt_emissao ()) ? edNota_Fiscal.getDt_emissao () : Data.getDataDMY ());
        ed.setDT_Entrada (edNota_Fiscal.getDt_entrada ());
        ed.setNM_Modelo_Documento (edNota_Fiscal.edModelo.getCD_Modelo_Nota_Fiscal ());
        if ("S".equals (DM_Tipo_Livro)) {
          ed.setNM_Serie_Documento (" ");
        }
        else {
          ed.setNM_Serie_Documento (edNota_Fiscal.getNm_serie ());
        }
        ed.setNR_Documento (String.valueOf (edNota_Fiscal.getNr_nota_fiscal ()));
        ed.setOID_Conhecimento ("");
        ed.setOID_Pessoa_Emitente (edNota_Fiscal.getOid_pessoa ());
        ed.setOID_Tipo_Documento (0);
        ed.setOID_Unidade (getValueDef ( (int) edNota_Fiscal.getOID_Unidade_Fiscal () , (int) edNota_Fiscal.getOID_Unidade ()));
        ed.setTX_Observacao ("");

        //*** Agrega Valores
         vlContabil += edNota_Fiscal.getVl_liquido_nota_fiscal () - edNota_Fiscal.getVL_Servico ();
        vlImposto += edNota_Fiscal.getVl_icms ();
        /** Imposto creditado:
         *  Se a aliquota da NF = 0 e nao for da CFOP 1102 (compras para comercializacao ) nao vai recuperar o imposto
         *  Tipicamente para 1102 nota complementar
         */

        if (edNota_Fiscal.getPE_Aliquota_ICMS () == 0 && (!"1102".equals (ed.getCD_Natureza_Operacao ()) && !"1949".equals (ed.getCD_Natureza_Operacao ())  && !"1604".equals (ed.getCD_Natureza_Operacao ()) && !"1602".equals (ed.getCD_Natureza_Operacao ()) )) {
            vlImposto_Creditado += 0;
          }
          else {
            vlImposto_Creditado += edNota_Fiscal.getVl_icms ();
          }

        System.out.println("vlImposto_Creditado ==== "+ vlImposto_Creditado);
        ed.setPE_Aliquota (edNota_Fiscal.getPE_Aliquota_ICMS ());

        ed.setVL_Base_Calculo (vlContabil - edNota_Fiscal.getVl_ipi ());

        ed.setVL_Contabil (vlContabil);
        ed.setVL_Imposto (vlImposto);
        ed.setVL_Imposto_Creditado (vlImposto_Creditado);
        if (vlContabil <= 0) {
          ed.setTX_Mensagem (edNota_Fiscal.getDm_observacao ());
        }
        //*** Tipo de Opera��o
         ed.setDM_Tipo_Operacao ("1"); // Default para tributado
         if ("O".equals (ed.getDM_Tipo_Imposto ())) { // Se for Outros
          ed.setDM_Tipo_Operacao ("3");
          ed.setVL_Base_Calculo(0);
        }
        else if ("I".equals (ed.getDM_Tipo_Imposto ())) { // Se for Isento
          ed.setDM_Tipo_Operacao ("2");
        }
        else if (edNota_Fiscal.getPE_Aliquota_ICMS () == 0 && !"1102".equals (ed.getCD_Natureza_Operacao ()) && !"1604".equals (ed.getCD_Natureza_Operacao ())) { //  Se n�o tiver aliquota e for diferente de 1102
          ed.setDM_Tipo_Operacao ("2");
        }
        //**********

         this.inclui (ed);

        //*** Se Nota Possui Natureza de Opera��o de Servi�o
         if (edNota_Fiscal.getOid_Natureza_Operacao_Servico () > 0 && edNota_Fiscal.getVL_Servico () > 0) {
           Natureza_OperacaoED edNatureza = new Natureza_OperacaoBD (executasql).getByRecord (new Natureza_OperacaoED (new Integer (edNota_Fiscal.getOid_Natureza_Operacao_Servico ())));
           ed.setDM_Tipo_Imposto (edNatureza.getDm_Tipo_Imposto ());
           ed.setOID_Natureza_Operacao (edNota_Fiscal.getOid_Natureza_Operacao_Servico ());
           ed.setCD_Natureza_Operacao (edNatureza.getCd_Natureza_Operacao ());
           ed.setNM_Natureza_Operacao (edNatureza.getNm_Natureza_Operacao ());

           ed.setDM_Tipo_Operacao ("3");
           ed.setVL_Base_Calculo (edNota_Fiscal.getVL_Servico ());
           ed.setVL_Contabil (edNota_Fiscal.getVL_Servico ());
           ed.setVL_Imposto (0);
           ed.setVL_Imposto_Creditado (0);
           ed.setPE_Aliquota (0);
           this.inclui (ed);
         }
      }
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "geraLivro_Fiscal_Entradas()");
    }
  }

  public void geraLivro_Fiscal_Entradas_savCris (Livro_FiscalED ed , String DM_Tipo_Livro) throws Excecoes {

	    long oldCFOP = 0;
	    double vlContabil = 0;
	    double vlImposto = 0;
	    double vlImposto_Creditado = 0;
	    double vlBaseCalculo = 0;
	    double peAliquota = 0;
	    boolean isCFOPSubst = false;
	    double vlOpe1 = 0;
	    double vlOpe2 = 0;
	    double vlOpe3 = 0;
	    ArrayList lista = new ArrayList ();

	    try {
	      if (!doValida (ed.getOID_Nota_Fiscal ())) {
	        throw new Excecoes ("Nota Fiscal n�o informada!");
	      }
	      //*** Exclui se houver livro antigo da NF
	       executasql.executarUpdate ("DELETE FROM Livros_Fiscais " +
	                                  "WHERE oid_Nota_Fiscal = " + getSQLString (ed.getOID_Nota_Fiscal ()));

	      Nota_Fiscal_EletronicaED edNota_Fiscal = new Nota_Fiscal_EletronicaBD (executasql).getByRecord (new Nota_Fiscal_EletronicaED (ed.getOID_Nota_Fiscal ()));

	      // *** Se a NF for cancelada cria um registro com valores zerados e obs = "CANCELADA"
	      if ("C".equals (edNota_Fiscal.getDM_Situacao ())) {
	        //*** Busca Dados da Pessoa
	         PessoaED edPessoa = new PessoaBD (executasql).getByRecord (new PessoaED (edNota_Fiscal.getOid_pessoa ()));
	        Natureza_OperacaoED edNOP = new Natureza_OperacaoBD (executasql).getByRecord (new Natureza_OperacaoED (new Integer ( (int) edNota_Fiscal.getOid_natureza_operacao ())));
	        ed.setDM_Tipo_Imposto (edNOP.getDm_Tipo_Imposto ());
	        ed.setCD_Estado (edPessoa.getCD_Estado ());
	        ed.setOID_Natureza_Operacao (edNota_Fiscal.getOid_natureza_operacao ());
	        ed.setCD_Natureza_Operacao (edNOP.getCd_Natureza_Operacao ());
	        ed.setNM_Natureza_Operacao (edNOP.getNm_Natureza_Operacao ());
	        ed.setCD_Unidade ("");
	        ed.setDM_Situacao ("S");
	        ed.setDM_Tipo_Documento ("NF");
	        ed.setCD_Especie ("NF");
	        ed.setDM_Tipo_Livro (DM_Tipo_Livro); // E ou S
	        ed.setDT_Emissao (doValida (edNota_Fiscal.getDt_emissao ()) ? edNota_Fiscal.getDt_emissao () : Data.getDataDMY ());
	        ed.setDT_Entrada (edNota_Fiscal.getDt_entrada ());
	        ed.setNM_Modelo_Documento (edNota_Fiscal.edModelo.getCD_Modelo_Nota_Fiscal ());
	        if ("S".equals (DM_Tipo_Livro)) {
	          ed.setNM_Serie_Documento (" ");
	        }
	        else {
	          ed.setNM_Serie_Documento (edNota_Fiscal.getNm_serie ());
	        }
	        ed.setNR_Documento (String.valueOf (edNota_Fiscal.getNr_nota_fiscal ()));
	        ed.setOID_Conhecimento ("");
	        ed.setOID_Pessoa_Emitente (edNota_Fiscal.getOid_pessoa ());
	        ed.setOID_Tipo_Documento (0);
	        ed.setOID_Unidade (edNota_Fiscal.getOID_Unidade_Fiscal ());
	        ed.setTX_Observacao ("CANCELADA");
	        ed.setTX_Mensagem ("CANCELADA");
	        ed.setVL_Imposto (0);
	        ed.setPE_Aliquota (0);
	        ed.setVL_Base_Calculo (0);
	        ed.setVL_Contabil (0);
	        ed.setVL_Imposto_Creditado (0);
	        ed.setDM_Tipo_Operacao ("0");
	        this.inclui (ed);
	        return;
	      }

	      //*** Busca Itens da NF
	       lista = new Item_Nota_Fiscal_TransacoesBD (executasql).lista (new Item_Nota_Fiscal_TransacoesED (ed.getOID_Nota_Fiscal ()));
	      if (lista.size () > 0) {
	        /** Lista Ordenada
	                      // Este m�todo ordena uma lista */
	        Collections.sort (lista , new Comparator () {
	          public int compare (Object o1 , Object o2) {
	            return String.valueOf ( ( (Item_Nota_Fiscal_TransacoesED) o1).getPE_Aliquota_ICMS ()).compareTo (String.valueOf ( ( (Item_Nota_Fiscal_TransacoesED) o2).getPE_Aliquota_ICMS ()));
	          }
	        }
	        );
	        Collections.sort (lista , new Comparator () {
	          public int compare (Object o1 , Object o2) {
	            return (String.valueOf ( ( (Item_Nota_Fiscal_TransacoesED) o1).getOid_natureza_operacao ()).compareTo (String.valueOf ( ( (Item_Nota_Fiscal_TransacoesED) o2).getOid_natureza_operacao ())));
	          }
	        }
	        );

	        //*** Atualiza ICMS
	         new Nota_Fiscal_EletronicaBD (executasql).atualizaValorICMS (ed.getOID_Nota_Fiscal ());
	        //*** Busca Dados da NF
	         edNota_Fiscal = new Nota_Fiscal_EletronicaBD (executasql).getByRecord (new Nota_Fiscal_EletronicaED (ed.getOID_Nota_Fiscal ()));

	        if (!doValida (edNota_Fiscal.getOid_nota_fiscal ())) {
	          throw new Excecoes ("Nota Fiscal n�o encontrada!");
	        }
	        if ("N".equals (edNota_Fiscal.edModelo.getDM_Gera_Fiscal ())) {
	          return;
	        }

	        //*** Busca Descri��o do Modelo
	         ed.setNM_Modelo_Documento (edNota_Fiscal.edModelo.getCD_Modelo_Nota_Fiscal ());

	        //*** Busca Dados da Pessoa
	         PessoaED edPessoa = new PessoaBD (executasql).getByRecord (new PessoaED (edNota_Fiscal.getOid_pessoa ()));

	        double vlICMSItens = 0 , vlICMSCredItens = 0 , vlItens = 0 , vlBCItens = 0;
	        for (int i = 0; i < lista.size (); i++) {
	          Item_Nota_Fiscal_TransacoesED edItem_NF = (Item_Nota_Fiscal_TransacoesED) lista.get (i);
	          //*** Quebra por Natureza de Opera��o
	           if (oldCFOP > 0 && oldCFOP != edItem_NF.getOid_natureza_operacao ()) {
	             if (isCFOPSubst) {
	               ed.setVL_Base_Calculo (ed.getVL_Base_Calculo () - edNota_Fiscal.getVL_ICMS_Subst ());
	             }
	             //this.inclui (ed);
	             vlContabil = 0;
	             vlImposto = 0;
	             vlImposto_Creditado = 0;
	             vlBaseCalculo = 0;
	             //*** Quebra por PE_Aliquota_ICMS
	           }
	           else
	           if (oldCFOP > 0 && peAliquota != edItem_NF.getPE_Aliquota_ICMS ()) {
	             //this.inclui (ed);
	             vlContabil = 0;
	             vlImposto = 0;
	             vlImposto_Creditado = 0;
	             vlBaseCalculo = 0;
	           }
	          //*** Busca NOP
	           Natureza_OperacaoED edNOP = new Natureza_OperacaoBD (executasql).getByRecord (new Natureza_OperacaoED (new Integer ( (int) edItem_NF.getOid_natureza_operacao ())));
	          ed.setDM_Tipo_Imposto (edNOP.getDm_Tipo_Imposto ());

	          ed.setCD_Estado (edPessoa.getCD_Estado ());
	          ed.setOID_Natureza_Operacao (edItem_NF.getOid_natureza_operacao ());
	          ed.setCD_Natureza_Operacao (edNOP.getCd_Natureza_Operacao ());
	          ed.setNM_Natureza_Operacao (edNOP.getNm_Natureza_Operacao ());
	          ed.setCD_Unidade (edItem_NF.getCD_Unidade ());
	          ed.setDM_Situacao ("S");
	          ed.setDM_Tipo_Documento ("NF");
	          ed.setCD_Especie ("NF");
	          ed.setDM_Tipo_Livro (DM_Tipo_Livro); // E ou S
	          ed.setDT_Emissao (doValida (edNota_Fiscal.getDt_emissao ()) ? edNota_Fiscal.getDt_emissao () : Data.getDataDMY ());
	          ed.setDT_Entrada (edNota_Fiscal.getDt_entrada ());
	          ed.setNM_Modelo_Documento (edNota_Fiscal.edModelo.getCD_Modelo_Nota_Fiscal ());
	          if ("S".equals (DM_Tipo_Livro)) {
	            ed.setNM_Serie_Documento (" ");
	          }
	          else {
	            ed.setNM_Serie_Documento (edNota_Fiscal.getNm_serie ());
	          }
	          ed.setNR_Documento (String.valueOf (edNota_Fiscal.getNr_nota_fiscal ()));
	          ed.setOID_Conhecimento ("");
	          ed.setOID_Pessoa_Emitente (edNota_Fiscal.getOid_pessoa ());
	          ed.setOID_Tipo_Documento (0);
	          ed.setOID_Unidade (edNota_Fiscal.getOID_Unidade_Fiscal ());
	          ed.setTX_Observacao ("");

	          //*** Agrega Valores
	           //Valor.round(edItem_NF.getVL_Item()-edItem_NF.getVL_Desconto_NF(),2);
	           vlContabil += Valor.round (edItem_NF.getVL_Item () - edItem_NF.getVL_Desconto_NF () , 2); //  edItem_NF.getVL_Item(); //Valor.round(edItem_NF.getVL_Item()-edItem_NF.getVL_Desconto_NF(),2);
	          vlImposto += Valor.round (edItem_NF.getVL_ICMS () , 2);
	          vlImposto_Creditado += Valor.round (edItem_NF.getVL_ICMS_Aprov () , 2);
	          vlBaseCalculo += Valor.round (edItem_NF.getVL_Base_Calculo_ICMS () , 2);

	          ed.setPE_Aliquota (edItem_NF.getPE_Aliquota_ICMS ());
	          ed.setVL_Base_Calculo (vlBaseCalculo);
	          ed.setVL_Contabil (vlContabil);
	          ed.setVL_Imposto (vlImposto);
	          ed.setVL_Imposto_Creditado (vlImposto_Creditado);

	          oldCFOP = ed.getOID_Natureza_Operacao ();
	          isCFOPSubst = (edNota_Fiscal.getVL_ICMS_Subst () > 0 && "4".equals (Character.toString (ed.getCD_Natureza_Operacao ().charAt (1)))); ;
	          peAliquota = ed.getPE_Aliquota ();

	          //*** Busca CD_Situa��o_Tribut�ria
	           int oid_Situacao_Tributaria = getTableIntValue ("oid_Situacao_Tributaria" ,
	                                                           "Produtos" ,
	                                                           "oid_Produto = '" + edItem_NF.getOID_Produto () + "'");
	          Situacao_TributariaED edSit = new Situacao_TributariaBD (executasql).getByOidSituacao_Tributaria (oid_Situacao_Tributaria);
	          ed.setTX_Mensagem (edSit.getNM_Situacao_Tributaria ());
	          String codSit = edSit.getCD_Situacao_Tributaria ();
	          if ("O".equals (ed.getDM_Tipo_Imposto ()) || codSit.endsWith ("51") || codSit.endsWith ("60") || codSit.endsWith ("81")) {
	            ed.setDM_Tipo_Operacao ("3");
	            vlOpe3 = vlOpe3 + Valor.round (edItem_NF.getVL_Item () - edItem_NF.getVL_Desconto_NF () , 2);
	            // 1905 REMESSA PARA ARMAZEM GERAL
	            if ("1905".equals(ed.getCD_Natureza_Operacao())){
	                ed.setVL_Base_Calculo (0);
	            }else{
	                ed.setVL_Base_Calculo (vlContabil);
	            }

	          }
	          else
	          //*** Tipo de Opera��o
	           if ("I".equals (ed.getDM_Tipo_Imposto ()) || edItem_NF.getPE_Aliquota_ICMS () == 0) {
	             ed.setDM_Tipo_Operacao ("2");
	             if("SP".equals(edPessoa.getCD_Estado())){
	 	      		 //SP � outros e n�o isento...
	             	 ed.setDM_Tipo_Operacao ("3");
	              }
	             vlOpe2 = vlOpe2 + Valor.round (edItem_NF.getVL_Item () - edItem_NF.getVL_Desconto_NF () , 2);
	           }
	           else {
	             ed.setDM_Tipo_Operacao ("1");
	             vlOpe1 = vlOpe1 + Valor.round (edItem_NF.getVL_Item () - edItem_NF.getVL_Desconto_NF () , 2);
	             // 2905 - ENTRADA PARA ARMAZEM GERAL
	             if ("2905".equals(ed.getCD_Natureza_Operacao())){
	            	 ed.setVL_Contabil(ed.getVL_Base_Calculo());
	             }

	           }
	          //*** Vari�veis pra fechamento
	           vlICMSItens += Valor.round (edItem_NF.getVL_ICMS () , 2);
	          vlICMSCredItens += Valor.round (edItem_NF.getVL_ICMS_Aprov () , 2);
	          vlItens += Valor.round (edItem_NF.getVL_Item () - edItem_NF.getVL_Desconto_NF () , 2);
	          vlBCItens += Valor.round (edItem_NF.getVL_Base_Calculo_ICMS () , 2);

	          //*** Se for ultimo registro
	           //  - Ajusta valores pela NF
	           if ( (i + 1) == lista.size ()) {
	             double vlNota = Valor.round (edNota_Fiscal.getVl_liquido_nota_fiscal () - edNota_Fiscal.getVl_ipi () - edNota_Fiscal.getVL_Servico () - edNota_Fiscal.getVl_total_despesas () , 2);
	             double vlICMSNota = Valor.round (edNota_Fiscal.getVl_icms () , 2);
	             vlItens = Valor.round (vlItens , 2);
	             vlICMSItens = Valor.round (vlICMSItens , 2);
	             vlICMSCredItens = Valor.round (vlICMSCredItens , 2);
	             vlBCItens = Valor.round (vlBCItens , 2);
	             double vlTotalDif = 0 , vlICMSDif = 0;

	             //*** ICMS
	              if (vlICMSNota > 0 && vlICMSNota != vlICMSItens) {
	                vlICMSDif = Valor.round (Math.max (vlICMSNota , vlICMSItens) - Math.min (vlICMSNota , vlICMSItens) , 2);
	                if (vlICMSNota > vlICMSItens) {
	                  ed.setVL_Imposto (ed.getVL_Imposto () + vlICMSDif);
	                }
	                else {
	                  ed.setVL_Imposto (ed.getVL_Imposto () - vlICMSDif);
	                }
	              }
	             //*** ICMS Creditado
	              if (vlICMSNota > 0 && vlICMSNota != vlICMSCredItens) {
	                vlICMSDif = Valor.round (Math.max (vlICMSNota , vlICMSCredItens) - Math.min (vlICMSNota , vlICMSCredItens) , 2);
	                if (vlICMSNota > vlICMSCredItens) {
	                  ed.setVL_Imposto_Creditado (ed.getVL_Imposto_Creditado () + vlICMSDif);
	                }
	                else {
	                  ed.setVL_Imposto_Creditado (ed.getVL_Imposto_Creditado () - vlICMSDif);
	                }
	              }
	             //*** VALOR - Alterado por Regis em 06/06  - (vlNota-edNota_Fiscal.getVL_ICMS_Subst()) N�o levava em conta o valor do icms subst....
	              // Acerta o valor contabil do ultimo lancamento pela diferenca
	              if (Valor.round (vlNota - edNota_Fiscal.getVL_ICMS_Subst () , 2) != vlItens || edNota_Fiscal.getVl_ipi () > 0 || edNota_Fiscal.getVl_total_despesas () > 0) {
	                vlTotalDif = Valor.round (Math.max (vlNota , vlItens) - Math.min (vlNota , vlItens) , 2);
	                if (vlNota > vlItens) {
	                  ed.setVL_Contabil (ed.getVL_Contabil () + vlTotalDif + edNota_Fiscal.getVl_ipi () + edNota_Fiscal.getVl_total_despesas ());
	                }
	                else {
	                  ed.setVL_Contabil (ed.getVL_Contabil () - vlTotalDif + edNota_Fiscal.getVl_ipi () + edNota_Fiscal.getVl_total_despesas ());
	                }
	              }
	             // Acerta a base de calculo se ela ficar maior ou menor 0,01 que o valor contabil dos itens da nota fiscal
	             if (Valor.validaTolerancia (vlNota , vlBCItens , 0.01)) {
	               vlTotalDif = Valor.round (Math.max (vlNota , vlBCItens) - Math.min (vlNota , vlBCItens) , 2);
	               if (vlBCItens > vlNota) {
	                 ed.setVL_Base_Calculo (ed.getVL_Base_Calculo () - vlTotalDif);
	               }
	               else {
	                 ed.setVL_Base_Calculo (ed.getVL_Base_Calculo () + vlTotalDif);
	               }
	             }

	             //*** Substituicao Tributaria acresce no valor contabil - Por Regis em 06/06
	              if (isCFOPSubst) {
	                ed.setVL_Contabil (ed.getVL_Contabil () + edNota_Fiscal.getVL_ICMS_Subst ());
	              }
	             //*** Arredondamento [Credito Imposto] cod. 1
	              if (ed.getVL_Base_Calculo () >= 0) {
	                if (!"1".equals (ed.getDM_Tipo_Operacao ())) {
	                  ed.setVL_Imposto (0);
	                  ed.setVL_Imposto_Creditado (0);
	                  ed.setPE_Aliquota (0);
	                }

			    	double vlImposto_Gerado=0;

	  		        if (ed.getVL_Base_Calculo() > 0){
					    vlImposto_Gerado += Valor.round (vlContabil - ed.getVL_Base_Calculo() , 2);
				    }else{
				    	vlImposto_Gerado=0;
				    }

				    ed.setVL_Imposto (0);
				    ed.setVL_Outro(0);
				    ed.setVL_Contabil (vlContabil-vlImposto_Gerado);
		            this.inclui (ed);

				    if (vlImposto_Gerado > 0){
				       ed.setDM_Tipo_Operacao ("2");
					   ed.setVL_Imposto (vlImposto_Gerado);
					   ed.setVL_Outro(0);
					   ed.setPE_Aliquota (0);
					   ed.setVL_Base_Calculo (0);
					   ed.setVL_Contabil (vlImposto_Gerado);
					   ed.setVL_Imposto_Creditado (0);
		 			   this.inclui (ed);
				    }

	                // this.inclui (ed);
	              }
	             //*** Quebra por Base de Calculo(cod. 2) - Alterado por Regis em 06/06 calculo do valor liquido da nota estava errado
	              vlNota = Valor.round (vlNota - edNota_Fiscal.getVl_total_frete () - edNota_Fiscal.getVl_total_seguro () , 2);
	             //**
	              /*	Isto acabou ficando meio 'gambiarrado' na verdade.
	               *  Originalmente o programa ao chegar neste ponto colocava toda a diferenca entre o valor da nota e base de calculo para Isento (2).
	               *  Acontece que quando notas devolvidas na natureza 1411 por exemplo n�o tem base de calculo e o valor acabava caindo em isento
	               *  causando erro no modelo b ( valor negativo na coluna outros ) e nos livro fiscal de entrada.
	               *  Ent�o o que foi feito: O calculo para isento agora est� sendo diminuido do valor de outros vlOpe3
	               */
	              if (edNota_Fiscal.getVL_Base_Calculo_ICMS () > 0 && !Valor.validaTolerancia (vlNota , edNota_Fiscal.getVL_Base_Calculo_ICMS () , 1)) {
	                if (Valor.round (vlNota - edNota_Fiscal.getVL_Base_Calculo_ICMS () - vlOpe3 , 2) > 0) { // Se o resto for > que zero
	                  Natureza_OperacaoED edNatureza = new Natureza_OperacaoBD (executasql).getByRecord (new Natureza_OperacaoED (new Integer ( (int) edNota_Fiscal.getOid_natureza_operacao ())));
	                  ed.setDM_Tipo_Imposto (edNatureza.getDm_Tipo_Imposto ());
	                  ed.setOID_Natureza_Operacao (edNota_Fiscal.getOid_natureza_operacao ());
	                  ed.setCD_Natureza_Operacao (edNatureza.getCd_Natureza_Operacao ());
	                  ed.setNM_Natureza_Operacao (edNatureza.getNm_Natureza_Operacao ());

	                  ed.setDM_Tipo_Operacao ("2");
	                  if (ed.getCD_Natureza_Operacao().equals("2905")){
	                      ed.setVL_Base_Calculo (0);
	                      ed.setVL_Contabil (Valor.round (vlNota - edNota_Fiscal.getVL_Base_Calculo_ICMS () - vlOpe3 , 2));
	                  }else{
	                      ed.setVL_Base_Calculo (Valor.round (vlNota - edNota_Fiscal.getVL_Base_Calculo_ICMS () - vlOpe3 , 2));
	                      ed.setVL_Contabil (0);
	                  }
	                  ed.setVL_Imposto (0);
	                  ed.setVL_Imposto_Creditado (0);
	                  ed.setPE_Aliquota (0);
	                  // Insert
	                  if (ed.getVL_Base_Calculo () >= 0) {
	                    // this.inclui (ed);
	                  }
	                }
	              }
	             //*** Se Nota Possui Natureza de Opera��o de Servi�o
	              if (edNota_Fiscal.getOid_Natureza_Operacao_Servico () > 0 && edNota_Fiscal.getVL_Servico () > 0) {
	                Natureza_OperacaoED edNatureza = new Natureza_OperacaoBD (executasql).getByRecord (new Natureza_OperacaoED (new Integer (edNota_Fiscal.getOid_Natureza_Operacao_Servico ())));
	                ed.setDM_Tipo_Imposto (edNatureza.getDm_Tipo_Imposto ());
	                ed.setOID_Natureza_Operacao (edNota_Fiscal.getOid_Natureza_Operacao_Servico ());
	                ed.setCD_Natureza_Operacao (edNatureza.getCd_Natureza_Operacao ());
	                ed.setNM_Natureza_Operacao (edNatureza.getNm_Natureza_Operacao ());

	                ed.setDM_Tipo_Operacao ("3");
	                ed.setVL_Base_Calculo (edNota_Fiscal.getVL_Servico ());
	                ed.setVL_Contabil (edNota_Fiscal.getVL_Servico ());
	                ed.setVL_Imposto (0);
	                ed.setVL_Imposto_Creditado (0);
	                ed.setPE_Aliquota (0);
	                this.inclui (ed);
	              }
	           }
	        }

	      }
	      else {

	        //*** Atualiza ICMS
	         new Nota_Fiscal_EletronicaBD (executasql).atualizaValorICMS (ed.getOID_Nota_Fiscal ());
	        //*** Busca Dados da NF
	         edNota_Fiscal = new Nota_Fiscal_EletronicaBD (executasql).getByRecord (new Nota_Fiscal_EletronicaED (ed.getOID_Nota_Fiscal ()));
	        if ("N".equals (edNota_Fiscal.edModelo.getDM_Gera_Fiscal ())) {
	          return;
	        }
	        //*** Busca Descri��o do Modelo
	         ed.setNM_Modelo_Documento (edNota_Fiscal.edModelo.getCD_Modelo_Nota_Fiscal ());
	        //*** Busca Dados da Pessoa
	         PessoaED edPessoa = new PessoaBD (executasql).getByRecord (new PessoaED (edNota_Fiscal.getOid_pessoa ()));
	        //*** Busca NOP
	         Natureza_OperacaoED edNOP = new Natureza_OperacaoBD (executasql).getByRecord (new Natureza_OperacaoED (new Integer ( (int) edNota_Fiscal.getOid_natureza_operacao ())));
	        ed.setDM_Tipo_Imposto (edNOP.getDm_Tipo_Imposto ());
	        ed.setCD_Estado (edPessoa.getCD_Estado ());
	        ed.setOID_Natureza_Operacao (edNota_Fiscal.getOid_natureza_operacao ());
	        ed.setCD_Natureza_Operacao (edNOP.getCd_Natureza_Operacao ());
	        ed.setNM_Natureza_Operacao (edNOP.getNm_Natureza_Operacao ());
	        ed.setCD_Unidade (edNota_Fiscal.getCD_Unidade ());
	        ed.setDM_Situacao ("S");
	        ed.setDM_Tipo_Documento ("NF");
	        ed.setCD_Especie ("NF");
	        ed.setDM_Tipo_Livro (DM_Tipo_Livro); // E ou S
	        ed.setDT_Emissao (doValida (edNota_Fiscal.getDt_emissao ()) ? edNota_Fiscal.getDt_emissao () : Data.getDataDMY ());
	        ed.setDT_Entrada (edNota_Fiscal.getDt_entrada ());
	        ed.setNM_Modelo_Documento (edNota_Fiscal.edModelo.getCD_Modelo_Nota_Fiscal ());
	        if ("S".equals (DM_Tipo_Livro)) {
	          ed.setNM_Serie_Documento (" ");
	        }
	        else {
	          ed.setNM_Serie_Documento (edNota_Fiscal.getNm_serie ());
	        }
	        ed.setNR_Documento (String.valueOf (edNota_Fiscal.getNr_nota_fiscal ()));
	        ed.setOID_Conhecimento ("");
	        ed.setOID_Pessoa_Emitente (edNota_Fiscal.getOid_pessoa ());
	        ed.setOID_Tipo_Documento (0);
	        ed.setOID_Unidade (getValueDef ( (int) edNota_Fiscal.getOID_Unidade_Fiscal () , (int) edNota_Fiscal.getOID_Unidade ()));
	        ed.setTX_Observacao ("");

	        //*** Agrega Valores
	         vlContabil += edNota_Fiscal.getVl_liquido_nota_fiscal () - edNota_Fiscal.getVL_Servico ();
	        vlImposto += edNota_Fiscal.getVl_icms ();
	        /** Imposto creditado:
	         *  Se a aliquota da NF = 0 e nao for da CFOP 1102 (compras para comercializacao ) nao vai recuperar o imposto
	         *  Tipicamente para 1102 nota complementar
	         */

	        if (edNota_Fiscal.getPE_Aliquota_ICMS () == 0 && (!"1102".equals (ed.getCD_Natureza_Operacao ()) && !"1949".equals (ed.getCD_Natureza_Operacao ())  && !"1604".equals (ed.getCD_Natureza_Operacao ()) && !"1602".equals (ed.getCD_Natureza_Operacao ()) )) {
	            vlImposto_Creditado += 0;
	          }
	          else {
	            vlImposto_Creditado += edNota_Fiscal.getVl_icms ();
	          }

	        System.out.println("vlImposto_Creditado ==== "+ vlImposto_Creditado);
	        ed.setPE_Aliquota (edNota_Fiscal.getPE_Aliquota_ICMS ());

	        ed.setVL_Base_Calculo (vlContabil - edNota_Fiscal.getVl_ipi ());

	        ed.setVL_Contabil (vlContabil);
	        ed.setVL_Imposto (vlImposto);
	        ed.setVL_Imposto_Creditado (vlImposto_Creditado);
	        if (vlContabil <= 0) {
	          ed.setTX_Mensagem (edNota_Fiscal.getDm_observacao ());
	        }
	        //*** Tipo de Opera��o
	         ed.setDM_Tipo_Operacao ("1"); // Default para tributado
	         if ("O".equals (ed.getDM_Tipo_Imposto ())) { // Se for Outros
	          ed.setDM_Tipo_Operacao ("3");
	          ed.setVL_Base_Calculo(0);
	        }
	        else if ("I".equals (ed.getDM_Tipo_Imposto ())) { // Se for Isento
	          ed.setDM_Tipo_Operacao ("2");
	        }
	        else if (edNota_Fiscal.getPE_Aliquota_ICMS () == 0 && !"1102".equals (ed.getCD_Natureza_Operacao ()) && !"1604".equals (ed.getCD_Natureza_Operacao ())) { //  Se n�o tiver aliquota e for diferente de 1102
	          ed.setDM_Tipo_Operacao ("2");
	        }
	        //**********

	         this.inclui (ed);

	        //*** Se Nota Possui Natureza de Opera��o de Servi�o
	         if (edNota_Fiscal.getOid_Natureza_Operacao_Servico () > 0 && edNota_Fiscal.getVL_Servico () > 0) {
	           Natureza_OperacaoED edNatureza = new Natureza_OperacaoBD (executasql).getByRecord (new Natureza_OperacaoED (new Integer (edNota_Fiscal.getOid_Natureza_Operacao_Servico ())));
	           ed.setDM_Tipo_Imposto (edNatureza.getDm_Tipo_Imposto ());
	           ed.setOID_Natureza_Operacao (edNota_Fiscal.getOid_Natureza_Operacao_Servico ());
	           ed.setCD_Natureza_Operacao (edNatureza.getCd_Natureza_Operacao ());
	           ed.setNM_Natureza_Operacao (edNatureza.getNm_Natureza_Operacao ());

	           ed.setDM_Tipo_Operacao ("3");
	           ed.setVL_Base_Calculo (edNota_Fiscal.getVL_Servico ());
	           ed.setVL_Contabil (edNota_Fiscal.getVL_Servico ());
	           ed.setVL_Imposto (0);
	           ed.setVL_Imposto_Creditado (0);
	           ed.setPE_Aliquota (0);
	           this.inclui (ed);
	         }
	      }
	    }
	    catch (Exception exc) {
	      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "geraLivro_Fiscal_Entradas()");
	    }
	  }

  public static void main (String[] args) throws Excecoes {
    Nota_Fiscal_EletronicaED ed = new Nota_Fiscal_EletronicaED ();
    ed.setNr_nota_fiscal (426);
    ed.setNr_nota_fiscal_final (439);
    ed.setDm_tipo_nota_fiscal ("S");
    ArrayList lista = new Nota_Fiscal_EletronicaRN ().lista (ed);
    for (int i = 0; i < lista.size (); i++) {
      Nota_Fiscal_EletronicaED edNota = (Nota_Fiscal_EletronicaED) lista.get (i);
      new Livro_FiscalRN ().geraLivro_Fiscal_Saidas (new Livro_FiscalED (edNota.getOid_nota_fiscal () , "NFS") , "S");
    }
    System.exit ( -1);
  }

  /**
   * SAIDAS
   */
  public void geraLivro_Fiscal_Saidas (Livro_FiscalED ed , String DM_Tipo_Livro) throws Excecoes {

    long oldCFOP = 0;
    double vlContabil = 0;
    double vlImposto = 0;
    double vlImposto_Gerado = 0;
    double vlImposto_Debitado = 0;
    double vlBaseCalculo = 0;
    double peAliquota = 0;
    double vlImposto_Creditado = 0;
    double vlContabilItem = 0;
    double vlBaseCalculoItem = 0;

    try {
      if (!doValida (ed.getOID_Nota_Fiscal ())) {
        throw new Excecoes ("Nota Fiscal n�o informada!");
      }

      //*** Busca Dados da NF
       Nota_Fiscal_EletronicaED edNota_Fiscal = new Nota_Fiscal_EletronicaBD (executasql).getByRecord (new Nota_Fiscal_EletronicaED (ed.getOID_Nota_Fiscal ()));
      if (!doValida (edNota_Fiscal.getOid_nota_fiscal ())) {
        throw new Excecoes ("Nota Fiscal n�o encontrada!");
      }
      if ("N".equals (edNota_Fiscal.edModelo.getDM_Gera_Fiscal ())) {
        return;
      }

      //*** Exclui se houver livro antigo da NF
       executasql.executarUpdate ("DELETE FROM Livros_Fiscais " +
                                  "WHERE oid_Nota_Fiscal = " + getSQLString (ed.getOID_Nota_Fiscal ()));

      //*** Busca Dados da Pessoa
       PessoaED edPessoa = new PessoaBD (executasql).getByRecord (new PessoaED (edNota_Fiscal.getOid_pessoa_destinatario()));

      // *** Se a NF for cancelada cria um registro com valores zerados e obs = "CANCELADA"
      if ("C".equals (edNota_Fiscal.getDM_Situacao ())) {
        Natureza_OperacaoED edNOP = new Natureza_OperacaoBD (executasql).getByRecord (new Natureza_OperacaoED (new Integer ( (int) edNota_Fiscal.getOid_natureza_operacao ())));
        ed.setDM_Tipo_Imposto (edNOP.getDm_Tipo_Imposto ());
        ed.setCD_Estado (edPessoa.getCD_Estado ());
        ed.setOID_Natureza_Operacao (edNota_Fiscal.getOid_natureza_operacao ());
        ed.setCD_Natureza_Operacao (edNOP.getCd_Natureza_Operacao ());
        ed.setNM_Natureza_Operacao (edNOP.getNm_Natureza_Operacao ());
        ed.setCD_Unidade ("");
        ed.setDM_Situacao ("S");
        ed.setDM_Tipo_Documento ("NF");
        ed.setCD_Especie ("NF");
        ed.setDM_Tipo_Livro (DM_Tipo_Livro); // E ou S
        ed.setDT_Emissao (doValida (edNota_Fiscal.getDt_emissao ()) ? edNota_Fiscal.getDt_emissao () : Data.getDataDMY ());
        ed.setDT_Entrada (edNota_Fiscal.getDt_entrada ());
        ed.setNM_Modelo_Documento (edNota_Fiscal.edModelo.getCD_Modelo_Nota_Fiscal ());
        ed.setNM_Serie_Documento (" ");
        ed.setNR_Documento (String.valueOf (edNota_Fiscal.getNr_nota_fiscal ()));
        ed.setOID_Conhecimento ("");
        ed.setOID_Pessoa_Emitente (edNota_Fiscal.getOid_pessoa ());
        ed.setOID_Tipo_Documento (0);
        ed.setOID_Unidade (edNota_Fiscal.getOID_Unidade_Fiscal ());
        ed.setTX_Observacao ("CANCELADA");
        ed.setTX_Mensagem ("CANCELADA");
        ed.setVL_Imposto (0);
        ed.setPE_Aliquota (0);
        ed.setVL_Base_Calculo (0);
        ed.setVL_Contabil (0);
        ed.setVL_Imposto_Creditado (0);
        ed.setDM_Tipo_Operacao ("0");
        this.inclui (ed);
        return;
      }

      //*** Busca Itens da NF
       ArrayList lista = new Item_Nota_Fiscal_TransacoesBD (executasql).lista (new Item_Nota_Fiscal_TransacoesED (ed.getOID_Nota_Fiscal ()));
      //*** Gera Livro Pelos Itens (Exclui livro caso j� exista para essa NF)

       if (lista.size () > 0) {
         /** Lista Ordenada
                          // Este m�todo ordena uma lista */
         Collections.sort (lista , new Comparator () {
           public int compare (Object o1 , Object o2) {
             return String.valueOf ( ( (Item_Nota_Fiscal_TransacoesED) o1).getPE_Aliquota_ICMS ()).compareTo (String.valueOf ( ( (Item_Nota_Fiscal_TransacoesED) o2).getPE_Aliquota_ICMS ()));
           }
         }
         );
         Collections.sort (lista , new Comparator () {
           public int compare (Object o1 , Object o2) {
             return (String.valueOf ( ( (Item_Nota_Fiscal_TransacoesED) o1).getOid_natureza_operacao ()).compareTo (String.valueOf ( ( (Item_Nota_Fiscal_TransacoesED) o2).getOid_natureza_operacao ())));
           }
         }
         );

         //*** Atualiza ICMS
          new Nota_Fiscal_EletronicaBD (executasql).atualizaValorICMS (ed.getOID_Nota_Fiscal ());

         //*** Busca Descri��o do Modelo
          ed.setNM_Modelo_Documento (edNota_Fiscal.edModelo.getCD_Modelo_Nota_Fiscal ());

         double vlTotalBase = 0 , vlTotalContabil = 0 , vlTotalImposto = 0;
         for (int i = 0; i < lista.size (); i++) {
           Item_Nota_Fiscal_TransacoesED edItem_NF = (Item_Nota_Fiscal_TransacoesED) lista.get (i);
           //*** Quebra por Natureza de Opera��o
            if (oldCFOP > 0 && oldCFOP != edItem_NF.getOid_natureza_operacao ()) {
              double vlResto = roundImposto (vlTotalContabil , vlTotalBase , vlTotalImposto);
              if (vlResto != 0) {
                ed.setVL_Base_Calculo (ed.getVL_Base_Calculo () + vlResto);
              }
              this.inclui (ed);
              vlContabil = 0;
              vlImposto = 0;
              vlImposto_Debitado = 0;
              vlBaseCalculo = 0;
              //*** Quebra por PE_Aliquota_ICMS
            }
            else
            if (oldCFOP > 0 && peAliquota != 0 && peAliquota != edItem_NF.getPE_Aliquota_ICMS ()) {
              double vlResto = roundImposto (vlTotalContabil , vlTotalBase , vlTotalImposto);
              if (vlResto != 0) {
                ed.setVL_Base_Calculo (ed.getVL_Base_Calculo () + vlResto);
              }
              this.inclui (ed);
              vlContabil = 0;
              vlImposto = 0;
              vlImposto_Debitado = 0;
              vlBaseCalculo = 0;
            }
           //*** Busca NOP
            Natureza_OperacaoED edNOP = new Natureza_OperacaoBD (executasql).getByRecord (new Natureza_OperacaoED (new Integer ( (int) edItem_NF.getOid_natureza_operacao ())));
           ed.setDM_Tipo_Imposto (edNOP.getDm_Tipo_Imposto ());

           ed.setCD_Estado (edPessoa.getCD_Estado ());
           ed.setOID_Natureza_Operacao (edItem_NF.getOid_natureza_operacao ());
           ed.setCD_Natureza_Operacao (edNOP.getCd_Natureza_Operacao ());
           ed.setNM_Natureza_Operacao (edNOP.getNm_Natureza_Operacao ());
           ed.setCD_Unidade (edItem_NF.getCD_Unidade ());
           ed.setDM_Situacao ("S");
           ed.setDM_Tipo_Documento ("NF");
           ed.setCD_Especie ("NF");
           ed.setDM_Tipo_Livro (DM_Tipo_Livro); // E ou S
           ed.setDT_Emissao (doValida (edNota_Fiscal.getDt_emissao ()) ? edNota_Fiscal.getDt_emissao () : Data.getDataDMY ());
           ed.setDT_Entrada (edNota_Fiscal.getDt_entrada ());
           ed.setNM_Modelo_Documento (edNota_Fiscal.edModelo.getCD_Modelo_Nota_Fiscal ());
           ed.setNM_Serie_Documento (" ");
           ed.setNR_Documento (String.valueOf (edNota_Fiscal.getNr_nota_fiscal ()));
           ed.setOID_Conhecimento ("");
           ed.setOID_Pessoa_Emitente (edNota_Fiscal.getOid_pessoa ());
           ed.setOID_Tipo_Documento (0);
           ed.setOID_Unidade (edNota_Fiscal.getOID_Unidade_Fiscal ());
           ed.setTX_Observacao ("");
           ed.setVL_Outro (0);

           vlContabilItem = Valor.round (edItem_NF.getVL_Item () - edItem_NF.getVL_Desconto_NF () , 2);
           //*** Calcula o valor l�quido da base ( base - desconto da nf rateado que � gravado separado, proporcionalmente  )
            //vlBaseCalculoItem = Valor.round( ( edItem_NF.getVL_Base_Calculo_ICMS() - ( edItem_NF.getVL_Desconto_NF() / edItem_NF.getVL_Item() * edItem_NF.getVL_Base_Calculo_ICMS() ) ),2);
            vlBaseCalculoItem = Valor.round (edItem_NF.getVL_Base_Calculo_ICMS () , 2);

           //*** Agrega Valores
            vlContabil += vlContabilItem;
           vlTotalContabil += vlContabilItem;
           vlImposto_Debitado += Valor.round (edItem_NF.getVL_ICMS_Aprov () , 2);
           if (edItem_NF.getPE_Aliquota_ICMS () != 0) {
             vlBaseCalculo += vlBaseCalculoItem;
             vlTotalBase += vlBaseCalculoItem;
           }
           /** ISENTOS **/
           if (edItem_NF.getPE_Aliquota_ICMS () == 0) {
             vlImposto += vlContabilItem;
             vlTotalImposto += vlContabilItem;
           }
           else
           if (edItem_NF.getPE_Aliquota_ICMS () != edItem_NF.getPE_Aliquota_ICMS_Aprov ()) {
             double vlBaseReduzida = Valor.round (Math.max (vlBaseCalculoItem , vlContabilItem) - Math.min (vlBaseCalculoItem , vlContabilItem) , 2);
             vlImposto += vlBaseReduzida;

             vlTotalImposto += vlBaseReduzida;
           }

           ed.setVL_Imposto (vlImposto);
           ed.setPE_Aliquota (edItem_NF.getPE_Aliquota_ICMS ());
           ed.setVL_Base_Calculo (vlBaseCalculo);
           ed.setVL_Contabil (vlContabil);
           ed.setVL_Imposto_Creditado (vlImposto_Debitado);

           oldCFOP = ed.getOID_Natureza_Operacao ();
           peAliquota = ed.getPE_Aliquota ();

           //*** Busca CD_Situa��o_Tribut�ria
            int oid_Situacao_Tributaria = getTableIntValue ("oid_Situacao_Tributaria" ,
                                                            "Produtos" ,
                                                            "oid_Produto = '" + edItem_NF.getOID_Produto () + "'");
           Situacao_TributariaED edSit = new Situacao_TributariaBD (executasql).getByOidSituacao_Tributaria (oid_Situacao_Tributaria);
           ed.setTX_Mensagem (edSit.getNM_Situacao_Tributaria ());
           String codSit = edSit.getCD_Situacao_Tributaria ();
           if ("O".equals (ed.getDM_Tipo_Imposto ()) || codSit.endsWith ("51") || codSit.endsWith ("60") || codSit.endsWith ("81")) {
             ed.setDM_Tipo_Operacao ("3");
             if ("3".equals (ed.getDM_Tipo_Operacao ()) && "S".equals (ed.getDM_Tipo_Livro ())) {
               ed.setVL_Outro (vlImposto);
               ed.setVL_Imposto (0);
             }
             else {
               ed.setVL_Imposto (vlImposto);
             }

           }
           else
           //*** Tipo de Opera��o
            if ("I".equals (ed.getDM_Tipo_Imposto ()) || edItem_NF.getPE_Aliquota_ICMS () == 0) {
              ed.setDM_Tipo_Operacao ("2");
            }
            else {
              ed.setDM_Tipo_Operacao ("1");
            }

           //*** Se for ultimo registro
            if ( (i + 1) == lista.size ()) {
              if (Valor.round (vlTotalBase + vlTotalImposto , 2) < Valor.round (vlTotalContabil , 2)) {
                vlImposto += Valor.round (vlTotalContabil - (vlTotalBase + vlTotalImposto) , 2);

                vlTotalImposto += Valor.round (vlTotalContabil - (vlTotalBase + vlTotalImposto) , 2);

                if ("3".equals (ed.getDM_Tipo_Operacao ()) && "S".equals (ed.getDM_Tipo_Livro ())) {
                  ed.setVL_Outro (vlImposto);
                  ed.setVL_Imposto (0);
                }
                else {
                  ed.setVL_Imposto (vlImposto);
                }
              }
              double vlResto = roundImposto (vlTotalContabil , vlTotalBase , vlTotalImposto);
              if (vlResto != 0) {
                ed.setVL_Base_Calculo (ed.getVL_Base_Calculo () + vlResto);
              }
              // Gelson
              // Situa��o da Miro devido a substirui��o tribut�ria que precisa gerar dois registros nos livros da mesma nota de sa�da
              if (!"5923".equals (ed.getCD_Natureza_Operacao ())) {
                 this.inclui (ed);
              }else {

    		      if (ed.getVL_Base_Calculo() > 0){
    			      vlImposto_Gerado += Valor.round (vlContabil - ed.getVL_Base_Calculo() , 2);
    		      }else{
    		    	  vlImposto_Gerado=0;
    		      }

    		      ed.setVL_Imposto (0);
    		      ed.setVL_Outro(0);
    		      ed.setVL_Contabil (vlContabil-vlImposto_Gerado);
                  this.inclui (ed);

    		      if (vlImposto_Gerado > 0){
    		         ed.setDM_Tipo_Operacao ("2");
    			     ed.setVL_Imposto (vlImposto_Gerado);
    			     ed.setVL_Outro(0);
    			     ed.setPE_Aliquota (0);
    			     ed.setVL_Base_Calculo (0);
    			     ed.setVL_Contabil (vlImposto_Gerado);
    			     ed.setVL_Imposto_Creditado (0);
     			     this.inclui (ed);
    		      }
              }

              /** Icms_substituido - Por R�gis em 06/2006
               *  N�o ativei a grava��o de registro de icms substituido devido �s l�gicas tortuosas de de utiliz��o
               *  tabela Livros_fiscais notadamento quanto a emiss�o do livro de registro de sa�das e n�o se sabe mais onde
               *  vai ficar aguardando uma reestrutura��o do tratamento fiscal
               *  Para coerencia de utilizac�o do livro fiscal seria necess�rio a gravac�o de um registro com o icms substituido.
               *  Este valor � originario do cabe�alho da nota fiscal. Ele � impresso no livro Registro de sa�da onde � listada
               *  nota a nota e ent�o � pegop direto da nota. No entanto, em outros relat�rios � necess�rio que seja feito fun��es
               *  de agrega��o no sql onde h� um join entre a tabele de livros fiscais e o cabecalho da nota. Dai n�o funciona
               *  corretamento porque existem mais de um registro de livros fiscais para cada nf .
               *  Ent�o no relatorio para o modelo B, onde se agrupa notas por cfop ou estado, � necess�rio fazer um outro sql
               *  com funcoes de agregacao no campo vl_icms_substituido somente sobre o cabecalho da nota com a mesma l�gica de
               *  acesso ao livro.
                                       if (edNota_Fiscal.getVL_ICMS_Subst() > 0 ) {
               ed.setPE_Aliquota(0);
                  ed.setVL_Contabil(0);
                  ed.setVL_Base_Calculo(0);
                  ed.setVL_Imposto(0);
                  ed.setVL_Imposto_Creditado(0);
                  ed.setVL_Outro(0);
                  ed.setVl_Icms_Subst(edNota_Fiscal.getVL_ICMS_Subst());
                  this.inclui(ed);
                                       }
               */
            }
         }
       }
       else {

         //*** Atualiza ICMS
          new Nota_Fiscal_EletronicaBD (executasql).atualizaValorICMS (ed.getOID_Nota_Fiscal ());
         //*** Busca Dados da NF
          edNota_Fiscal = new Nota_Fiscal_EletronicaBD (executasql).getByRecord (new Nota_Fiscal_EletronicaED (ed.getOID_Nota_Fiscal ()));
         if ("N".equals (edNota_Fiscal.edModelo.getDM_Gera_Fiscal ())) {
           return;
         }
         //*** Busca Descri��o do Modelo
          ed.setNM_Modelo_Documento (edNota_Fiscal.edModelo.getCD_Modelo_Nota_Fiscal ());
         //*** Busca Dados da Pessoa
          edPessoa = new PessoaBD (executasql).getByRecord (new PessoaED (edNota_Fiscal.getOid_pessoa ()));
         //*** Busca NOP
          Natureza_OperacaoED edNOP = new Natureza_OperacaoBD (executasql).getByRecord (new Natureza_OperacaoED (new Integer ( (int) edNota_Fiscal.getOid_natureza_operacao ())));
         ed.setDM_Tipo_Imposto (edNOP.getDm_Tipo_Imposto ());
         ed.setCD_Estado (edPessoa.getCD_Estado ());
         ed.setOID_Natureza_Operacao (edNota_Fiscal.getOid_natureza_operacao ());
         ed.setCD_Natureza_Operacao (edNOP.getCd_Natureza_Operacao ());
         ed.setNM_Natureza_Operacao (edNOP.getNm_Natureza_Operacao ());
         ed.setCD_Unidade (edNota_Fiscal.getCD_Unidade ());
         ed.setDM_Situacao ("S");
         ed.setDM_Tipo_Documento ("NF");
         ed.setCD_Especie ("NF");
         ed.setDM_Tipo_Livro (DM_Tipo_Livro); // E ou S
         ed.setDT_Emissao (doValida (edNota_Fiscal.getDt_emissao ()) ? edNota_Fiscal.getDt_emissao () : Data.getDataDMY ());
         ed.setDT_Entrada (edNota_Fiscal.getDt_entrada ());
         ed.setNM_Modelo_Documento (edNota_Fiscal.edModelo.getCD_Modelo_Nota_Fiscal ());
         ed.setNM_Serie_Documento (" ");
         ed.setNR_Documento (String.valueOf (edNota_Fiscal.getNr_nota_fiscal ()));
         ed.setOID_Conhecimento ("");
         ed.setOID_Pessoa_Emitente (edNota_Fiscal.getOid_pessoa ());
         ed.setOID_Tipo_Documento (0);
         ed.setOID_Unidade (getValueDef ( (int) edNota_Fiscal.getOID_Unidade_Fiscal () , (int) edNota_Fiscal.getOID_Unidade ()));
         ed.setTX_Observacao ("");

         //*** Agrega Valores
          vlContabil += edNota_Fiscal.getVl_liquido_nota_fiscal () - edNota_Fiscal.getVL_Servico ();
         vlImposto += edNota_Fiscal.getVl_icms ();
         /** Imposto creditado:
          *  Se a aliquota da NF = 0 e nao for da CFOP 1102 (compras para comercializacao ) nao vai recuperar o imposto
          *  Tipicamente para 1102 nota complementar
          */
         if (edNota_Fiscal.getPE_Aliquota_ICMS () == 0 && (!"5102".equals (ed.getCD_Natureza_Operacao ()) && !"6949".equals (ed.getCD_Natureza_Operacao ()) && !"5923".equals (ed.getCD_Natureza_Operacao ()) && !"5602".equals (ed.getCD_Natureza_Operacao ()) && !"5556".equals (ed.getCD_Natureza_Operacao ()) )) {
           vlImposto_Creditado += 0;
         }
         else {
           vlImposto_Creditado += edNota_Fiscal.getVl_icms ();
         }
         ed.setPE_Aliquota (edNota_Fiscal.getPE_Aliquota_ICMS ());
         ed.setVL_Base_Calculo (vlContabil - edNota_Fiscal.getVl_ipi ());
         ed.setVL_Contabil (vlContabil);
         ed.setVL_Imposto (vlImposto);
         ed.setVL_Imposto_Creditado (vlImposto_Creditado);
         if (vlContabil <= 0) {
           ed.setTX_Mensagem (edNota_Fiscal.getDm_observacao ());
         }

         //*** Tipo de Opera��o
          ed.setDM_Tipo_Operacao ("1"); // Default para tributado
         if ("O".equals (ed.getDM_Tipo_Imposto ())) { // Se for Outros
           ed.setDM_Tipo_Operacao ("3");
           ed.setVL_Base_Calculo (vlContabil);
           // 5949 Simples Remessa
           if (edNota_Fiscal.getPE_Aliquota_ICMS () == 0 && ed.getVL_Imposto_Creditado() == 0 && ed.getVL_Imposto() == 0 &&
        		                                 ("6556".equals (ed.getCD_Natureza_Operacao ())
        		                                		  || "5949".equals (ed.getCD_Natureza_Operacao ())
        		                                		  || "6909".equals (ed.getCD_Natureza_Operacao ())
        		                                		  || "5102".equals (ed.getCD_Natureza_Operacao ())
        		                                		  || "5915".equals (ed.getCD_Natureza_Operacao ())
        		                                		  || "5907".equals (ed.getCD_Natureza_Operacao ()))){
        	  ed.setVL_Outro(vlContabil);
              ed.setVL_Base_Calculo (0);
           }

         }
         else if ("I".equals (ed.getDM_Tipo_Imposto ())) { // Se for Isento
           ed.setDM_Tipo_Operacao ("2");
         }
         else if (edNota_Fiscal.getPE_Aliquota_ICMS () == 0 && !"5102".equals (ed.getCD_Natureza_Operacao ())) { //  Se n�o tiver aliquota e for diferente de 1102
           ed.setDM_Tipo_Operacao ("2");
           if("5556".equals(ed.getCD_Natureza_Operacao())){
        	   ed.setDM_Tipo_Operacao ("3");
        	   ed.setVL_Outro(vlContabil);
               ed.setVL_Base_Calculo (0);
           }
         }
         //**********

          this.inclui (ed);

         //*** Se Nota Possui Natureza de Opera��o de Servi�o
          if (edNota_Fiscal.getOid_Natureza_Operacao_Servico () > 0 && edNota_Fiscal.getVL_Servico () > 0) {
            Natureza_OperacaoED edNatureza = new Natureza_OperacaoBD (executasql).getByRecord (new Natureza_OperacaoED (new Integer (edNota_Fiscal.getOid_Natureza_Operacao_Servico ())));
            ed.setDM_Tipo_Imposto (edNatureza.getDm_Tipo_Imposto ());
            ed.setOID_Natureza_Operacao (edNota_Fiscal.getOid_Natureza_Operacao_Servico ());
            ed.setCD_Natureza_Operacao (edNatureza.getCd_Natureza_Operacao ());
            ed.setNM_Natureza_Operacao (edNatureza.getNm_Natureza_Operacao ());

            ed.setDM_Tipo_Operacao ("3");
            ed.setVL_Base_Calculo (edNota_Fiscal.getVL_Servico ());
            ed.setVL_Contabil (edNota_Fiscal.getVL_Servico ());
            ed.setVL_Imposto (0);
            ed.setVL_Imposto_Creditado (0);
            ed.setPE_Aliquota (0);
            this.inclui (ed);
          }
       }
    }
    catch (Mensagens m) {
      throw m;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "geraLivro_Fiscal_Saidas()");
    }
  }

  /** P/ ARREDONDAMENTO **/
  private static double roundImposto (double vlTotalContabil , double vlTotalBase , double vlTotalImposto) {
    return Valor.round ( (Valor.round (vlTotalContabil , 2) - Valor.round ( (Valor.round (vlTotalBase , 2) + Valor.round (vlTotalImposto , 2)) , 2)) , 2);
  }

  /** PONTO DE VENDAS **/
  public void geraLivro_Fiscal_PDV (Livro_FiscalED ed , String DM_Tipo_Livro) throws Excecoes {

    long oldCFOP = 0;
    double vlContabil = 0;
    double vlImposto = 0;
    double vlImposto_Debitado = 0;
    double vlBaseCalculo = 0;
    double peAliquota = 0;

    try {
      if (!doValida (ed.getOID_Nota_Fiscal ())) {
        throw new Excecoes ("Nota Fiscal n�o informada!");
      }
      //*** Exclui se houver livro antigo da NF
       executasql.executarUpdate ("DELETE FROM Livros_Fiscais " +
                                  "WHERE oid_Nota_Fiscal = " + getSQLString (ed.getOID_Nota_Fiscal ()));
      //*** Busca Itens da NF
       ArrayList lista = new Item_Nota_Fiscal_TransacoesBD (executasql).lista (new Item_Nota_Fiscal_TransacoesED (ed.getOID_Nota_Fiscal ()));
      //*** Gera Livro Pelos Itens (Exclui livro caso j� exista para essa NF)
       if (lista.size () > 0) {
         /** Lista Ordenada
                          // Este m�todo ordena uma lista */
         Collections.sort (lista , new Comparator () {
           public int compare (Object o1 , Object o2) {
             return String.valueOf ( ( (Item_Nota_Fiscal_TransacoesED) o1).getPE_Aliquota_ICMS_Aprov ()).compareTo (String.valueOf ( ( (Item_Nota_Fiscal_TransacoesED) o2).getPE_Aliquota_ICMS_Aprov ()));
           }
         }
         );
         Collections.sort (lista , new Comparator () {
           public int compare (Object o1 , Object o2) {
             return (String.valueOf ( ( (Item_Nota_Fiscal_TransacoesED) o1).getOid_natureza_operacao ()).compareTo (String.valueOf ( ( (Item_Nota_Fiscal_TransacoesED) o2).getOid_natureza_operacao ())));
           }
         }
         );

         //*** Atualiza ICMS
          new Nota_Fiscal_EletronicaBD (executasql).atualizaValorICMS (ed.getOID_Nota_Fiscal ());
         //*** Busca Dados da NF
          Nota_Fiscal_EletronicaED edNota_Fiscal = new Nota_Fiscal_EletronicaBD (executasql).getByRecord (new Nota_Fiscal_EletronicaED (ed.getOID_Nota_Fiscal ()));
         if (!doValida (edNota_Fiscal.getOid_nota_fiscal ())) {
           throw new Excecoes ("Nota Fiscal n�o encontrada!");
         }
         if ("N".equals (edNota_Fiscal.edModelo.getDM_Gera_Fiscal ())) {
           return;
         }

         //*** Busca Descri��o do Modelo
          ed.setNM_Modelo_Documento (edNota_Fiscal.edModelo.getCD_Modelo_Nota_Fiscal ());

         //*** Busca Dados da Pessoa
          PessoaED edPessoa = new PessoaBD (executasql).getByRecord (new PessoaED (edNota_Fiscal.getOid_pessoa ()));
         double vlTotalBase = 0 , vlTotalContabil = 0 , vlTotalImposto = 0;
         for (int i = 0; i < lista.size (); i++) {
           Item_Nota_Fiscal_TransacoesED edItem_NF = (Item_Nota_Fiscal_TransacoesED) lista.get (i);
           //*** Quebra por Natureza de Opera��o
            if (oldCFOP > 0 && oldCFOP != edItem_NF.getOid_natureza_operacao ()) {
              this.inclui (ed);
              vlContabil = 0;
              vlImposto = 0;
              vlImposto_Debitado = 0;
              vlBaseCalculo = 0;
              //*** Quebra por PE_Aliquota_ICMS
            }
            else
            if (oldCFOP > 0 && peAliquota != 0 && peAliquota != edItem_NF.getPE_Aliquota_ICMS_Aprov ()) {
              this.inclui (ed);
              vlContabil = 0;
              vlImposto = 0;
              vlImposto_Debitado = 0;
              vlBaseCalculo = 0;
            }
           //*** Busca NOP
            Natureza_OperacaoED edNOP = new Natureza_OperacaoBD (executasql).getByRecord (new Natureza_OperacaoED (new Integer ( (int) edItem_NF.getOid_natureza_operacao ())));
           ed.setDM_Tipo_Imposto (edNOP.getDm_Tipo_Imposto ());

           ed.setCD_Estado (edPessoa.getCD_Estado ());
           ed.setOID_Natureza_Operacao (edItem_NF.getOid_natureza_operacao ());
           ed.setCD_Natureza_Operacao (edNOP.getCd_Natureza_Operacao ());
           ed.setNM_Natureza_Operacao (edNOP.getNm_Natureza_Operacao ());
           ed.setCD_Unidade (edItem_NF.getCD_Unidade ());
           ed.setDM_Situacao ("S");
           ed.setDM_Tipo_Documento ("CF");
           ed.setDM_Tipo_Livro (DM_Tipo_Livro); // E ou S
           ed.setDT_Emissao (doValida (edNota_Fiscal.getDt_emissao ()) ? edNota_Fiscal.getDt_emissao () : Data.getDataDMY ());
           ed.setDT_Entrada (edNota_Fiscal.getDt_entrada ());
           ed.setNM_Modelo_Documento (edNota_Fiscal.edModelo.getCD_Modelo_Nota_Fiscal ());
           if ("S".equals (DM_Tipo_Livro)) {
             ed.setNM_Serie_Documento (" ");
           }
           else {
             ed.setNM_Serie_Documento (edNota_Fiscal.getNm_serie ());
           }
           ed.setNR_Documento (String.valueOf (edNota_Fiscal.getNr_nota_fiscal ()));
           ed.setOID_Conhecimento ("");
           ed.setOID_Pessoa_Emitente (edNota_Fiscal.getOid_pessoa ());
           ed.setOID_Tipo_Documento (0);
           ed.setOID_Unidade (edNota_Fiscal.getOID_Unidade_Fiscal ());
           ed.setTX_Observacao ("");

           //*** Agrega Valores
            vlContabil += edItem_NF.getVL_Item ();
           vlTotalContabil += Valor.round (edItem_NF.getVL_Item () , 2);
           vlImposto_Debitado += Valor.round (edItem_NF.getVL_ICMS_Aprov () , 2);
           if (edItem_NF.getPE_Aliquota_ICMS_Aprov () != 0) {
             vlBaseCalculo += Valor.round (edItem_NF.getVL_Item () , 2);
             vlTotalBase += Valor.round (edItem_NF.getVL_Item () , 2);
           }
           /** ISENTOS **/
           if (edItem_NF.getPE_Aliquota_ICMS_Aprov () == 0) {
             vlImposto += Valor.round (vlContabil , 2);
             vlTotalImposto += Valor.round (vlContabil , 2);
           }
           ed.setVL_Imposto (vlImposto);
           ed.setPE_Aliquota (edItem_NF.getPE_Aliquota_ICMS_Aprov ());
           ed.setVL_Base_Calculo (vlBaseCalculo);
           ed.setVL_Contabil (vlContabil);
           ed.setVL_Imposto_Creditado (vlImposto_Debitado);

           oldCFOP = edItem_NF.getOid_natureza_operacao ();
           peAliquota = edItem_NF.getPE_Aliquota_ICMS_Aprov ();

           //*** Busca CD_Situa��o_Tribut�ria
            int oid_Situacao_Tributaria = getTableIntValue ("oid_Situacao_Tributaria" ,
                                                            "Produtos" ,
                                                            "oid_Produto = '" + edItem_NF.getOID_Produto () + "'");
           Situacao_TributariaED edSit = new Situacao_TributariaBD (executasql).getByOidSituacao_Tributaria (oid_Situacao_Tributaria);
           ed.setTX_Mensagem (edSit.getNM_Situacao_Tributaria ());
           String codSit = edSit.getCD_Situacao_Tributaria ();
           if ("O".equals (ed.getDM_Tipo_Imposto ()) || codSit.endsWith ("51") || codSit.endsWith ("60") || codSit.endsWith ("81")) {
             ed.setDM_Tipo_Operacao ("3");
           }
           else
           //*** Tipo de Opera��o
            if ("I".equals (ed.getDM_Tipo_Imposto ()) || edItem_NF.getPE_Aliquota_ICMS () == 0) {
              ed.setDM_Tipo_Operacao ("2");
            }
            else {
              ed.setDM_Tipo_Operacao ("1");
            }

           //*** Se for ultimo registro
            if ( (i + 1) == lista.size ()) {
              if (Valor.round (vlTotalBase + vlTotalImposto , 2) < Valor.round (vlTotalContabil , 2)) {
                vlImposto += Valor.round (vlTotalContabil - (vlTotalBase + vlTotalImposto) , 2);
                vlTotalImposto += Valor.round (vlTotalContabil - (vlTotalBase + vlTotalImposto) , 2);
                ed.setVL_Imposto (vlImposto);
              }
              this.inclui (ed);
            }
         }
       }
       else {
         throw new Mensagens ("N�o ha Itens para gerar Fiscal!");
       }
    }
    catch (Mensagens m) {
      throw m;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "geraLivro_Fiscal_PDV()");
    }
  }

  /**
   * VENDA DIRETA
   */
  public void geraLivro_Fiscal_VendaDireta (Livro_FiscalED ed , String DM_Tipo_Livro) throws Excecoes {

    double vlContabil = 0;
    double vlImposto = 0;
    double vlImposto_Debitado = 0;
    double vlBaseCalculo = 0;
    String OID_Nota_Fiscal = ed.getOID_Nota_Fiscal ();

    try {
      if (!doValida (ed.getOID_Nota_Fiscal ())) {
        throw new Excecoes ("Nota Fiscal n�o informada!");
      }
      //*** Exclui se houver livro antigo da NF
       executasql.executarUpdate ("DELETE FROM Livros_Fiscais " +
                                  "WHERE oid_Nota_Fiscal = " + getSQLString (ed.getOID_Nota_Fiscal ()));

      // Gera Nota Tempor�ria somente para gerar o livro fiscal dos valores do conhecimento de frete

      Nota_Fiscal_EletronicaED edNota_FiscalTemporaria = new Nota_Fiscal_EletronicaBD (executasql).getByRecord (new Nota_Fiscal_EletronicaED (ed.getOID_Nota_Fiscal ()));
      edNota_FiscalTemporaria.setNr_nota_fiscal_final (1111111111);
      Nota_Fiscal_EletronicaED edNota_FiscalInclui = new Nota_Fiscal_EletronicaBD (executasql).inclui (edNota_FiscalTemporaria);

      ed.setOID_Nota_Fiscal (edNota_FiscalInclui.getOid_nota_fiscal ());
      //*** Atualiza ICMS
       ConhecimentoED conhecimentoED = new Nota_Fiscal_EletronicaBD (executasql).atualizaValorICMSByConhecimentos (OID_Nota_Fiscal , ed.getOID_Nota_Fiscal ());
      //*** Busca Dados da NF
       Nota_Fiscal_EletronicaED edNota_Fiscal = new Nota_Fiscal_EletronicaBD (executasql).getByRecord (new Nota_Fiscal_EletronicaED (ed.getOID_Nota_Fiscal ()));
      boolean excluirParcelas = false;
      new Nota_Fiscal_EletronicaBD (executasql).deleta (new Nota_Fiscal_EletronicaED (ed.getOID_Nota_Fiscal ()) , excluirParcelas);
      if ("N".equals (edNota_Fiscal.edModelo.getDM_Gera_Fiscal ())) {
        return;
      }
      //*** Busca Descri��o do Modelo
       ed.setNM_Modelo_Documento (edNota_Fiscal.edModelo.getCD_Modelo_Nota_Fiscal ());
      //*** Busca Dados da Pessoa
       PessoaED edPessoa = new PessoaBD (executasql).getByRecord (new PessoaED (edNota_Fiscal.getOid_pessoa ()));
      //*** Busca NOP
       Natureza_OperacaoED edNOP = new Natureza_OperacaoBD (executasql).getByRecord (new Natureza_OperacaoED (new Integer ( (int) edNota_Fiscal.getOid_natureza_operacao ())));
      ed.setOID_Nota_Fiscal (OID_Nota_Fiscal);
      ed.setDM_Tipo_Imposto (edNOP.getDm_Tipo_Imposto ());
      ed.setCD_Estado (edPessoa.getCD_Estado ());
      ed.setOID_Natureza_Operacao (edNota_Fiscal.getOid_natureza_operacao ());
      ed.setCD_Natureza_Operacao (edNOP.getCd_Natureza_Operacao ());
      ed.setNM_Natureza_Operacao (edNOP.getNm_Natureza_Operacao ());
      ed.setCD_Unidade (edNota_Fiscal.getCD_Unidade ());
      ed.setDM_Situacao ("S");
      ed.setDM_Tipo_Documento ("CN");
      ed.setCD_Especie ("NF");
      ed.setDM_Tipo_Livro (DM_Tipo_Livro); // E ou S
      ed.setDT_Emissao (doValida (conhecimentoED.getDT_Emissao ()) ? conhecimentoED.getDT_Emissao () : Data.getDataDMY ());
      ed.setDT_Entrada (ed.getDT_Emissao ());
      ed.setNM_Modelo_Documento (edNota_Fiscal.edModelo.getCD_Modelo_Nota_Fiscal ());
      ed.setNM_Serie_Documento ("U");
      ed.setNR_Documento (String.valueOf (conhecimentoED.getNR_Conhecimento ()));
      ed.setOID_Conhecimento (String.valueOf (conhecimentoED.getOID_Conhecimento ()));
      ed.setOID_Pessoa_Emitente (edNota_Fiscal.getOid_pessoa ());
      ed.setOID_Tipo_Documento (0);
      ed.setOID_Unidade (getValueDef ( (int) edNota_Fiscal.getOID_Unidade_Fiscal () , (int) edNota_Fiscal.getOID_Unidade ()));
      ed.setTX_Observacao ("");

      //*** Agrega Valores
       vlContabil += edNota_Fiscal.getVl_liquido_nota_fiscal () - edNota_Fiscal.getVL_Servico ();
      vlImposto_Debitado += Valor.round (edNota_Fiscal.getVl_icms () , 2);

      /** BASE DE C�LCULO **/
      if (edNota_Fiscal.getPE_Aliquota_ICMS () != 0 && edNota_Fiscal.getVL_Base_Calculo_ICMS () > 0) {
        vlBaseCalculo += Valor.round (edNota_Fiscal.getVL_Base_Calculo_ICMS () , 2);
      }
      else
      /** ISENTOS **/
      if (edNota_Fiscal.getPE_Aliquota_ICMS () == 0) {
        vlImposto += Valor.round (vlContabil , 2);
      }
      else {
        vlImposto += Valor.round (vlContabil - edNota_Fiscal.getVL_Base_Calculo_ICMS () , 2);
      }

      ed.setVL_Imposto (vlImposto);
      ed.setPE_Aliquota (vlBaseCalculo > 0 ? edNota_Fiscal.getPE_Aliquota_ICMS () : 0);
      ed.setVL_Base_Calculo (vlBaseCalculo);
      ed.setVL_Contabil (vlContabil);
      ed.setVL_Imposto_Creditado (vlImposto_Debitado);
      if (vlContabil <= 0) {
        ed.setTX_Mensagem (edNota_Fiscal.getDm_observacao ());
      }

      //*** Tipo de Opera��o
       if ("O".equals (ed.getDM_Tipo_Imposto ())) {
         ed.setDM_Tipo_Operacao ("3");
         ed.setVL_Base_Calculo (vlContabil);
       }
       else
       if ("I".equals (ed.getDM_Tipo_Imposto ()) || edNota_Fiscal.getPE_Aliquota_ICMS () == 0) {
         ed.setDM_Tipo_Operacao ("2");
       }
       else {
         ed.setDM_Tipo_Operacao ("1");
       }
      this.inclui (ed);

    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "geraLivro_Fiscal_VendaDireta()");
    }
  }

  /**
   * CTRCs
   */
  public void geraLivro_Fiscal_CTRC (Livro_FiscalED ed) throws Excecoes {

	    double vlContabil = 0;
	    double vlImposto = 0;
	    double vlImposto_Gerado = 0;
	    double vlImposto_Debitado = 0;
	    double vlBaseCalculo = 0;
	    double vlOutro = 0;
	    String OID_Conhecimento = ed.getOID_Conhecimento();

	    try {
	      if (!doValida (ed.getOID_Conhecimento())) {
	        throw new Excecoes ("Conhecimento n�o informada!");
	      }

	      // System.out.println("Cto livro 0");

	      //*** Exclui se houver livro antigo
	       executasql.executarUpdate ("DELETE FROM Livros_Fiscais " +
	                                  "WHERE oid_Conhecimento = " + getSQLString (ed.getOID_Conhecimento()));

	      ConhecimentoED buscador = new ConhecimentoED();
	      buscador.setOID_Conhecimento(ed.getOID_Conhecimento());
	      ConhecimentoED conhecimentoED = new ConhecimentoBD(executasql).getByRecord(buscador);
	      boolean excluirParcelas = false;

	      ed.setNM_Modelo_Documento ("CTRC");
	      //*** Busca Dados da Pessoa
	       PessoaED edPessoa = new PessoaBD (executasql).getByRecord (new PessoaED (conhecimentoED.getOID_Pessoa_Destinatario()));
//	      CidadeBean destino = CidadeBean.getByOID(new Long(conhecimentoED.getOID_Cidade_Destino()).intValue());
	      ed.setCD_Estado(edPessoa.getCD_Estado());

	      //*** Busca NOP
	       Natureza_OperacaoED edNOP = new Natureza_OperacaoBD(executasql).getByRecord(new Natureza_OperacaoED(conhecimentoED.getCD_CFO()));
	      //ed.setOID_Conhecimento(conhecimentoED.getOID_Conhecimento());
//	      ed.setCD_Estado(edPessoa.getCD_Estado ());
	      if(doValida(String.valueOf(edNOP.getOid_Natureza_Operacao()))){
	    	  ed.setDM_Tipo_Imposto(edNOP.getDm_Tipo_Imposto());
		      ed.setOID_Natureza_Operacao(edNOP.getOid_Natureza_Operacao().longValue());
		      ed.setCD_Natureza_Operacao(edNOP.getCd_Natureza_Operacao());
		      ed.setNM_Natureza_Operacao(edNOP.getNm_Natureza_Operacao ());
	      } else {
	    	  // System.out.println("Natureza Operacao nao Localizada p/Cto: !" + conhecimentoED.getNR_Conhecimento() + " CFO: " + conhecimentoED.getCD_CFO()) ;
	          throw new Excecoes ("Natureza Operacao nao Localizada p/Cto: !" + conhecimentoED.getNR_Conhecimento() + " CFO: " + conhecimentoED.getCD_CFO()) ;
	      }
	      ed.setOID_Unidade(conhecimentoED.getOID_Unidade());
	      UnidadeED unidade = new UnidadeBD(executasql).getByRecord(new UnidadeED(conhecimentoED.getOID_Unidade()));
	      ed.setCD_Unidade(unidade.getCd_Unidade());
	      ed.setDM_Situacao ("S");
	      ed.setDM_Tipo_Documento ("CN");
	      ed.setCD_Especie ("CTRC");
	      ed.setDM_Tipo_Livro ("S"); // E ou S
	      ed.setDT_Emissao (doValida (conhecimentoED.getDT_Emissao ()) ? conhecimentoED.getDT_Emissao () : Data.getDataDMY ());
	      ed.setDT_Entrada (ed.getDT_Emissao ());
	      ed.setNM_Modelo_Documento("CTRC");
	      ed.setNM_Serie_Documento(conhecimentoED.getNM_Serie());
	      ed.setNR_Documento(String.valueOf(conhecimentoED.getNR_Conhecimento ()));
	      ed.setOID_Conhecimento(String.valueOf(conhecimentoED.getOID_Conhecimento ()));
	      ed.setOID_Pessoa_Emitente(unidade.getOid_Pessoa());
	      ed.setOID_Tipo_Documento(0);
	      ed.setTX_Observacao ("");

	      //cancelados
	      if(doValida(conhecimentoED.getDM_Situacao()) && conhecimentoED.getDM_Situacao().equals("C")){
	    	  ed.setTX_Observacao("Cancelada");
	    	  ed.setDM_Tipo_Operacao ("0");
	    	  ed.setVL_Imposto (0.0);
	          ed.setVL_Base_Calculo (0.0);
	          ed.setVL_Contabil (0.0);
	          ed.setVL_Imposto_Creditado (0.0);
	          ed.setVL_Outro(0.0);
	          ed.setPE_Aliquota(0.0);
		      if ("C".equals(conhecimentoED.getDM_Tipo_Documento())) {
		    	   this.inclui (ed);
		      }
	      }else{
		      //*** Agrega Valores
		      vlContabil += conhecimentoED.getVL_TOTAL_FRETE();
		      vlImposto_Debitado += Valor.round (conhecimentoED.getVL_ICMS() , 2);

		      /** BASE DE C�LCULO **/
		      if (conhecimentoED.getVL_BASE_CALCULO_ICMS() > 0) {
		        vlBaseCalculo += Valor.round (conhecimentoED.getVL_BASE_CALCULO_ICMS() , 2);
		      }
		      //else
		      /** ISENTOS **/
		      if (conhecimentoED.getVL_ICMS() == 0) {
		        vlImposto += Valor.round (vlContabil , 2);
		      }
		      else {
		        vlImposto = 0;
		      }

		      if (vlContabil <= 0) {
		    	  if(doValida(conhecimentoED.getTX_Observacao()))
		    		  ed.setTX_Mensagem (conhecimentoED.getTX_Observacao());
		    	  else
		    		  ed.setTX_Mensagem(ed.getTX_Observacao());
		      }
//	          String CD_Estado = new CidadeBD(executasql).getByRecord(new CidadeED(conhecimentoED.getOID_Cidade())).getCD_Estado();
		      if (conhecimentoED.getVL_ICMS() == 0) { //ISENTO
		         ed.setDM_Tipo_Operacao ("2");

		         if ("O".equals(ed.getDM_Tipo_Imposto()) || "6".equals(conhecimentoED.getDM_Tipo_Conhecimento())){
				     ed.setDM_Tipo_Operacao ("3");
			      	 vlOutro = vlImposto;
			      	 vlImposto=0;

		         }
		         //if("SP".equals(CD_Estado)){
			      	 //SP � outros e n�o isento...
			     // 	 ed.setDM_Tipo_Operacao ("3");
			      	 //vlOutro = vlImposto;
			      	 //vlImposto=0;
			     //}
		      }else { //TRIBUTADO
		         ed.setDM_Tipo_Operacao ("1");
		      }
		      if (vlBaseCalculo>0){
			      vlImposto_Gerado += Valor.round (vlContabil - conhecimentoED.getVL_BASE_CALCULO_ICMS() , 2);
		      }else{
		    	  vlImposto_Gerado=0;
		      }

		      ed.setVL_Imposto (vlImposto);
		      ed.setVL_Outro(vlOutro);
		      ed.setPE_Aliquota (vlBaseCalculo > 0 ? conhecimentoED.getPE_ALIQUOTA_ICMS() : 0);
		      ed.setVL_Base_Calculo (vlBaseCalculo);
		      ed.setVL_Contabil (vlContabil-vlImposto_Gerado);
		      ed.setVL_Imposto_Creditado (vlImposto_Debitado);
		      if ("C".equals(conhecimentoED.getDM_Tipo_Documento())) {
		    	   this.inclui (ed);
		      }

		      if (vlImposto_Gerado > 0){
		         ed.setDM_Tipo_Operacao ("2");
			     ed.setVL_Imposto (vlImposto_Gerado);
			     ed.setVL_Outro(0);
		         if ("O".equals(ed.getDM_Tipo_Imposto()) || "6".equals(conhecimentoED.getDM_Tipo_Conhecimento())){
				     ed.setDM_Tipo_Operacao ("3");
			      	 vlOutro = vlImposto;
			      	 vlImposto=0;

		         }
		         //if("SP".equals(CD_Estado)){
			      	 //SP � outros e n�o isento...
			     // 	 ed.setDM_Tipo_Operacao ("3");
				     //ed.setVL_Imposto (0);
				     //ed.setVL_Outro(vlImposto_Gerado);
		         //}
			     ed.setPE_Aliquota (0);
			     ed.setVL_Base_Calculo (0);
			     ed.setVL_Contabil (vlImposto_Gerado);
			     ed.setVL_Imposto_Creditado (0);
                 if ("C".equals(conhecimentoED.getDM_Tipo_Documento())) {
			       this.inclui (ed);
			     }
		      }
	      }

	       // System.out.println("Cto livro 1");

	    }
	    catch (Exception exc) {
	      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "geraLivro_Fiscal_CTRC()");
	    }
	  }

  //Sem itens de Nota Fiscal  // nf COMPRAS
  public void geraLivro_Fiscal_Entrada_Simplificada (Livro_FiscalED ed) throws Excecoes {

	    double vl_Contabil = 0;
	    double vl_Contabil_Servico = 0;
	    double vl_Contabil_Outros = 0;
	    String OID_Nota_Fiscal = ed.getOID_Nota_Fiscal();

	    // System.out.println("geraLivro_Fiscal_Entrada_Simplificada 1");
	    try {

	        if (!doValida (ed.getOID_Nota_Fiscal ())) {
	            throw new Excecoes ("Nota Fiscal n�o informada!");
	          }
	          //*** Exclui se houver livro antigo da NF
	           executasql.executarUpdate ("DELETE FROM Livros_Fiscais " +
	                                      "WHERE oid_Nota_Fiscal = " + getSQLString(ed.getOID_Nota_Fiscal ()));

		// System.out.println("geraLivro_Fiscal_Entrada_Simplificada 2");

	          Nota_Fiscal_CompraED edNota_Fiscal = new Nota_Fiscal_CompraED();
	          edNota_Fiscal.setOid_nota_fiscal(ed.getOID_Nota_Fiscal());
	          edNota_Fiscal = new Nota_Fiscal_CompraBD(executasql).getByRecord(edNota_Fiscal);

	  	    // System.out.println("geraLivro_Fiscal_Entrada_Simplificada 3");

	          // *** Somente para Notas finalizadas
	          if (1==1) { //"F".equals(edNota_Fiscal.getDm_finalizado()) || "S".equals(edNota_Fiscal.getDm_finalizado())) {

	        	vl_Contabil=edNota_Fiscal.getVl_liquido_nota_fiscal() + edNota_Fiscal.getVL_Cofins() + edNota_Fiscal.getVL_Csll() + edNota_Fiscal.getVl_irrf()+edNota_Fiscal.getVL_Pis();

	        	if (edNota_Fiscal.getVL_Servico()==0 && edNota_Fiscal.getVl_isqn() > 0){
	        		vl_Contabil = vl_Contabil + edNota_Fiscal.getVl_isqn();
	        	}
	        	if (edNota_Fiscal.getVL_Servico()>0){
	        		vl_Contabil_Servico=edNota_Fiscal.getVL_Servico();
	        		vl_Contabil = vl_Contabil - edNota_Fiscal.getVL_Servico() + edNota_Fiscal.getVl_isqn() ;
	        	}

	        	if (edNota_Fiscal.getVL_Outros()>0){
		        	vl_Contabil_Outros=edNota_Fiscal.getVL_Outros();
	        		vl_Contabil-=vl_Contabil_Outros;
	        	}

	    	    // System.out.println("geraLivro_Fiscal_Entrada_Simplificada 4");

	        	//FISCAL PARTE PRINCIPAL
	        	if (vl_Contabil>0) {
		        	Natureza_OperacaoED edNOP = new Natureza_OperacaoBD(executasql).getByRecord(new Natureza_OperacaoED(new Integer((int)edNota_Fiscal.getOid_natureza_operacao())));
		    	    // System.out.println("geraLivro_Fiscal_Entrada_Simplificada 5");

		        	if (!"N".equals(edNOP.getDm_Tipo_Imposto())){   ///TIPO N NAO GERA FISCAL
		        	    // System.out.println("geraLivro_Fiscal_Entrada_Simplificada 6");

		        		PessoaED edPessoa = new PessoaBD(executasql).getByRecord(new PessoaED(edNota_Fiscal.getOid_pessoa()));

		        	    // System.out.println("geraLivro_Fiscal_Entrada_Simplificada 7");
		        		ed.setDM_Tipo_Imposto(edNOP.getDm_Tipo_Imposto());
			            ed.setCD_Estado(edPessoa.getCD_Estado());
			            ed.setOID_Natureza_Operacao(edNota_Fiscal.getOid_natureza_operacao ());
			            ed.setCD_Natureza_Operacao(edNOP.getCd_Natureza_Operacao ());
			            ed.setNM_Natureza_Operacao(edNOP.getNm_Natureza_Operacao ());
			            ed.setCD_Unidade("");
			            ed.setDM_Situacao("S");

			            if("2351".equals(edNOP.getCd_Natureza_Operacao())){ // Situa��o da NovaEra - deve ser tratada - Gelson
			      	      ed.setDM_Tipo_Documento ("CN");
			    	      ed.setCD_Especie ("CTRC");
			            }else{
				            ed.setDM_Tipo_Documento("NF");
				            ed.setCD_Especie("NF");
			            }

			            ed.setDM_Tipo_Livro("E"); // E ou S
			    	    // System.out.println("geraLivro_Fiscal_Entrada_Simplificada 8");

			            ed.setDT_Emissao(doValida(edNota_Fiscal.getDt_emissao()) ? edNota_Fiscal.getDt_emissao() : Data.getDataDMY());
			            ed.setDT_Entrada(edNota_Fiscal.getDt_entrada());
			            ed.setNM_Modelo_Documento("NF");
		//	            ed.setNM_Modelo_Documento(edNota_Fiscal.edModelo.getCD_Modelo_Nota_Fiscal ());
		                ed.setNM_Serie_Documento(edNota_Fiscal.getNm_serie());

			            ed.setNR_Documento(String.valueOf(edNota_Fiscal.getNr_nota_fiscal()));
			            ed.setOID_Conhecimento ("");
			            ed.setOID_Pessoa_Emitente(edNota_Fiscal.getOid_pessoa());
			            ed.setOID_Tipo_Documento(0);
			            ed.setOID_Unidade(edNota_Fiscal.getOID_Unidade_Fiscal());

			            ed.setTX_Observacao("");
			            ed.setTX_Mensagem("");
			    	    // System.out.println("geraLivro_Fiscal_Entrada_Simplificada 9");

			            ed.setVL_Imposto(edNota_Fiscal.getVl_icms());
			            ed.setVL_Base_Calculo(edNota_Fiscal.getVl_icms()> 0 ? vl_Contabil : 0);
			            ed.setVL_Contabil (vl_Contabil);
			            ed.setVL_Imposto_Creditado(edNota_Fiscal.getVl_icms());
			            if (edNota_Fiscal.getVl_icms() == 0) { //ISENTO
			   	            ed.setDM_Tipo_Operacao("3");
			   	            ed.setPE_Aliquota(0);
	//			   	         if("SP".equals(edPessoa.getCD_Estado())){
					   	     	 //SP � outros e n�o isento...
	//			   	        	 ed.setDM_Tipo_Operacao ("3");
				   	        	 //vlOutro = vlImposto;
				   	        	 //vlImposto=0;
	//			   	         }
			            }
			   	       	else { //TRIBUTADO
				   	        ed.setDM_Tipo_Operacao("1");
				    	    // System.out.println("geraLivro_Fiscal_Entrada_Simplificada 10");
				            String oidPessoaOrigem = ed.getOID_Pessoa_Emitente();
				            String oidPessoaDestino = getTableStringValue("oid_Pessoa",
				    												   "unidades",
				    												   "oid_unidade = "+ed.getOID_Unidade());

				            //*** Estado ORIGEM
				            CidadeBean edCidade_Orig = CidadeBean.getByOID(getTableIntValue("oid_Cidade",
				                    														"Pessoas",
				                    														"oid_Pessoa = '"+oidPessoaOrigem+"'"));
				            //*** Estado DESTINO
				            CidadeBean edCidade_Dest = CidadeBean.getByOID(getTableIntValue("oid_Cidade",
				                    														"Pessoas",
				                    														"oid_Pessoa = '"+oidPessoaDestino+"'"));

				            //*** Taxas por Estados
				            List lista = TaxaBean.getByOID_Estado_Origem_Destino(edCidade_Orig.getOID_Estado(), edCidade_Dest.getOID_Estado());
				            if (lista.size() > 0)
				            {
				                /** TAXA **/
				                TaxaBean edTaxa = (TaxaBean) lista.iterator().next();

				                    //% ICMS da TAXA
				                ed.setPE_Aliquota(edTaxa.getPE_Aliquota_ICMS());

				            } else throw new Excecoes("Taxa n�o localizada!");
			   	       	}

			            this.inclui (ed);
	            	}
	        	}
	    	    // System.out.println("geraLivro_Fiscal_Entrada_Simplificada 100");

	        	//FISCAL OUTROS
	        	if (vl_Contabil_Outros>0) {
		        	Natureza_OperacaoED edNOP = new Natureza_OperacaoBD(executasql).getByRecord(new Natureza_OperacaoED(new Integer((int)edNota_Fiscal.getOid_natureza_operacao_outros())));
		            if (!"N".equals(edNOP.getDm_Tipo_Imposto())){   ///TIPO N NAO GERA FISCAL
				        PessoaED edPessoa = new PessoaBD(executasql).getByRecord(new PessoaED(edNota_Fiscal.getOid_pessoa()));
			            ed.setDM_Tipo_Imposto(edNOP.getDm_Tipo_Imposto());
			            ed.setCD_Estado(edPessoa.getCD_Estado());
			            ed.setOID_Natureza_Operacao(edNota_Fiscal.getOid_natureza_operacao_outros ());
			            ed.setCD_Natureza_Operacao(edNOP.getCd_Natureza_Operacao ());
			            ed.setNM_Natureza_Operacao(edNOP.getNm_Natureza_Operacao ());
			            ed.setCD_Unidade("");
			            ed.setDM_Situacao("S");
			            ed.setDM_Tipo_Documento("NF");
			            ed.setCD_Especie("NF");
			            ed.setDM_Tipo_Livro("E"); // E ou S
			            ed.setDT_Emissao(doValida(edNota_Fiscal.getDt_emissao()) ? edNota_Fiscal.getDt_emissao() : Data.getDataDMY());
			            ed.setDT_Entrada(edNota_Fiscal.getDt_entrada());
			            ed.setNM_Modelo_Documento("NF");
		//	            ed.setNM_Modelo_Documento(edNota_Fiscal.edModelo.getCD_Modelo_Nota_Fiscal ());
		                ed.setNM_Serie_Documento(edNota_Fiscal.getNm_serie());

			            ed.setNR_Documento(String.valueOf(edNota_Fiscal.getNr_nota_fiscal()));
			            ed.setOID_Conhecimento ("");
			            ed.setOID_Pessoa_Emitente(edNota_Fiscal.getOid_pessoa());
			            ed.setOID_Tipo_Documento(0);
			            ed.setOID_Unidade(edNota_Fiscal.getOID_Unidade_Fiscal());

			            ed.setTX_Observacao("");
			            ed.setTX_Mensagem("");


			            //ed.setVL_Imposto(edNota_Fiscal.getVl_icms());
			            ed.setVL_Imposto(0);
			            //ed.setVL_Base_Calculo(edNota_Fiscal.getVl_icms()> 0 ? vl_Contabil_Outros : 0);
			            ed.setVL_Base_Calculo(0);
			            ed.setVL_Contabil (vl_Contabil_Outros);
			            //ed.setVL_Imposto_Creditado(edNota_Fiscal.getVl_icms());
			            ed.setVL_Imposto_Creditado(0);
			            ed.setPE_Aliquota(0);

			   	        ed.setDM_Tipo_Operacao("3");

			            this.inclui (ed);
	            	}
	        	}
	    	    // System.out.println("geraLivro_Fiscal_Entrada_Simplificada vl_Contabil_Servico = "+vl_Contabil_Servico);

	        	//FISCAL SERVICOS
	        	if (vl_Contabil_Servico>0) {
		        	Natureza_OperacaoED edNOP = new Natureza_OperacaoBD(executasql).getByRecord(new Natureza_OperacaoED(new Integer((int)edNota_Fiscal.getOid_natureza_operacao_servico())));
		            if (!"N".equals(edNOP.getDm_Tipo_Imposto())){   ///TIPO N NAO GERA FISCAL
				        PessoaED edPessoa = new PessoaBD(executasql).getByRecord(new PessoaED(edNota_Fiscal.getOid_pessoa()));
			            ed.setDM_Tipo_Imposto(edNOP.getDm_Tipo_Imposto());
			            ed.setCD_Estado(edPessoa.getCD_Estado());
			            ed.setOID_Natureza_Operacao(edNota_Fiscal.getOid_natureza_operacao_servico ());
			            ed.setCD_Natureza_Operacao(edNOP.getCd_Natureza_Operacao ());
			            ed.setNM_Natureza_Operacao(edNOP.getNm_Natureza_Operacao ());
			            ed.setCD_Unidade("");
			            ed.setDM_Situacao("S");
			            ed.setDM_Tipo_Documento("NF");
			            ed.setCD_Especie("NF");
			            ed.setDM_Tipo_Livro("E"); // E ou S
			            ed.setDT_Emissao(doValida(edNota_Fiscal.getDt_emissao()) ? edNota_Fiscal.getDt_emissao() : Data.getDataDMY());
			            ed.setDT_Entrada(edNota_Fiscal.getDt_entrada());
			            ed.setNM_Modelo_Documento("NF");
		//	            ed.setNM_Modelo_Documento(edNota_Fiscal.edModelo.getCD_Modelo_Nota_Fiscal ());
		                ed.setNM_Serie_Documento(edNota_Fiscal.getNm_serie());

			            ed.setNR_Documento(String.valueOf(edNota_Fiscal.getNr_nota_fiscal()));
			            ed.setOID_Conhecimento ("");
			            ed.setOID_Pessoa_Emitente(edNota_Fiscal.getOid_pessoa());
			            ed.setOID_Tipo_Documento(0);
			            ed.setOID_Unidade(edNota_Fiscal.getOID_Unidade_Fiscal());

			            ed.setTX_Observacao("");
			            ed.setTX_Mensagem("");

			            ed.setVL_Imposto(0);

			            ed.setVL_Base_Calculo(0);
			            ed.setVL_Contabil (vl_Contabil_Servico);
			            ed.setVL_Imposto_Creditado(0);
			   	        ed.setDM_Tipo_Operacao("3");

			            ed.setPE_Aliquota(0);

			            this.inclui (ed);
	            	}
	        	}

	          }

	    }
	    catch (Exception exc) {
	      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "geraLivro_Fiscal_Entrada_Simplificada()");
	    }
	  }

  public Livro_FiscalED inclui (Livro_FiscalED ed) throws Excecoes {

    String sql = null;
    try {
      //*** Busca OID
       if (oid_Livro_Fiscal > 0) {
         ++oid_Livro_Fiscal;
       }
       else {
         oid_Livro_Fiscal = getAutoIncremento ("oid_Livro_Fiscal" , "Livros_Fiscais");
       }
      ed.setOID_Livro_Fiscal (oid_Livro_Fiscal);

      if (doValida (ed.getNR_Documento()) && !"0".equals(ed.getNR_Documento())) {

	      sql = "INSERT INTO Livros_Fiscais (" +
	          "		OID_Livro_Fiscal, " +
	          "		OID_Nota_Fiscal, " +
	          "		OID_Natureza_Operacao, " +
	          "		OID_Unidade, " +
	          "		OID_Conhecimento, " +
	          "		OID_Pessoa_Emitente, " +
	          "		CD_Especie,  " +
	          "		NR_Documento, " +
	          "		NM_Serie_Documento, " +
	          "		NM_Modelo_Documento, " +
	          "		DM_Tipo_Documento, " +
	          "		DM_Tipo_Livro, " +
	          "		DM_Tipo_Imposto, " +
	          "		DT_Emissao, " +
	          "		CD_Estado, " +
	          "		DM_Tipo_Operacao, " +
	          "		DT_Entrada, " +
	          "		PE_Aliquota, " +
	          "		VL_Contabil, " +
	          "		VL_Imposto, " +
	          "		VL_Imposto_Creditado, " +
	          "		VL_Base_Calculo, " +
	          "     VL_Outro, " +
	          //"     VL_Icms_Subst, " + * N�o foi ativado a grava��o deste campo - R�gis em 06/2006
	          "		DM_Situacao, " +
	          "		Dt_Stamp, " +
	          "     TX_Mensagem, " +
	          "		TX_Observacao)";
	      sql += " VALUES ( ";
	      sql += ed.getOID_Livro_Fiscal () +
          ",'" + ed.getOID_Nota_Fiscal () + "'" +
          "," + ed.getOID_Natureza_Operacao () +
          "," + ed.getOID_Unidade () +
          ",'" + ed.getOID_Conhecimento () + "'" +
          ",'" + ed.getOID_Pessoa_Emitente () + "'" +
          ",'" + ed.getCD_Especie () + "'" +
          ",'" + ed.getNR_Documento () + "'" +
          ",'" + ed.getNM_Serie_Documento () + "'" +
          ",'" + ed.getNM_Modelo_Documento () + "'" +
          ",'" + ed.getDM_Tipo_Documento () + "'" +
          ",'" + ed.getDM_Tipo_Livro () + "'" +
          ",'" + ed.getDM_Tipo_Imposto () + "'" +
          ",'" + ed.getDT_Emissao () + "'" +
          ",'" + ed.getCD_Estado () + "'" +
          ",'" + ed.getDM_Tipo_Operacao () + "'" +
          ",'" + ed.getDT_Entrada () + "'" +
          "," + ed.getPE_Aliquota () +
          "," + ed.getVL_Contabil () +
          "," + ed.getVL_Imposto () +
          "," + ed.getVL_Imposto_Creditado () +
          "," + ed.getVL_Base_Calculo () +
          "," + ed.getVL_Outro () +
          //"," + ed.getVl_Icms_Subst() + N�o foi ativada a grava��o deste campo - Regis em 06/2006
          ",'" + ed.getDM_Situacao () + "'" +
          ",'" + Data.getDataDMY () + "'" +
          ",'" + ed.getTX_Mensagem () + "'" +
          ",'" + ed.getTX_Observacao () + "')";

		    // System.out.println("geraLivro_Fiscal="+sql);

      	executasql.executarUpdate (sql);
      }
      return ed;
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "inclui()");
    }
  }

  public void altera (Livro_FiscalED ed) throws Excecoes {

    String sql = null;

    try {

      sql = " UPDATE Livros_Fiscais SET ";
      sql += " DT_Emissao= '" + ed.getDT_Emissao () + "', ";
      sql += " NR_Documento= '" + ed.getNR_Documento () + "', ";
      sql += " DM_Situacao= 'A',";
      sql += " TX_Observacao= '" + ed.getTX_Observacao () + "',";
      sql += " CD_Estado= '" + ed.getCD_Estado () + "', ";
      sql += " DT_Entrada= '" + ed.getDT_Entrada () + "', ";
      sql += " OID_Natureza_Operacao= " + ed.getOID_Natureza_Operacao () + ", ";
      sql += " WHERE OID_Livro_Fiscal = " + ed.getOID_Livro_Fiscal ();

      executasql.executarUpdate (sql);
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "altera()");
    }
  }

  public void deleta (Livro_FiscalED ed) throws Excecoes {

    String sql = null;

    try {

      sql = " DELETE FROM Itens_Livros_Fiscais " +
          " WHERE oid_Livro_Fiscal = " + ed.getOID_Livro_Fiscal ();

      executasql.executarUpdate (sql);
      if ("D".equals (ed.getDM_Situacao ())) {
        sql = " DELETE FROM Livros_Fiscais " +
            " WHERE oid_Livro_Fiscal = " + ed.getOID_Livro_Fiscal ();
        executasql.executarUpdate (sql);
      }
      else
      if ("C".equals (ed.getDM_Situacao ())) {
        sql = " UPDATE Livros_Fiscais SET DM_Situacao='C' " +
            " WHERE oid_Livro_Fiscal = " + ed.getOID_Livro_Fiscal ();
        executasql.executarUpdate (sql);
      }
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "delete()");
    }
  }

  /**
   * @exception Tipos_Livros_Fiscais n�o existe no Banco...
   */
  public Livro_FiscalED getByRecord (Livro_FiscalED ed) throws Excecoes {

    String sql = null;
    ResultSet res = null;
    Livro_FiscalED edVolta = new Livro_FiscalED ();
    try {

      sql = " SELECT * FROM Livros_Fiscais, Pessoas, Condicoes_Pagamentos, Tipos_Livros_Fiscais, Unidades " +
          " WHERE  Livros_Fiscais.CD_Estado = Pessoas.CD_Estado " +
          " AND    Livros_Fiscais.OID_Natureza_Operacao = Condicoes_Pagamentos.OID_Natureza_Operacao " +
          " AND    Livros_Fiscais.OID_Conhecimento = Tipos_Livros_Fiscais.OID_Conhecimento " +
          " AND    Livros_Fiscais.OID_Unidade = Unidades.OID_Unidade ";

      if (ed.getOID_Livro_Fiscal () > 0) {
        sql += " AND oid_Livro_Fiscal = " + ed.getOID_Livro_Fiscal ();
      }

      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta = new Livro_FiscalED ();
        edVolta.setOID_Livro_Fiscal (res.getLong ("oid_Livro_Fiscal"));
        edVolta.setDM_Tipo_Livro (res.getString ("DM_Tipo_Livro"));
        edVolta.setCD_Especie (res.getString ("CD_Especie"));
        edVolta.setOID_Nota_Fiscal (res.getString ("OID_Nota_Fiscal"));
        edVolta.setDM_Situacao (res.getString ("DM_Situacao"));
        edVolta.setTX_Mensagem (res.getString ("TX_Mensagem"));
        edVolta.setTX_Observacao (res.getString ("TX_Observacao"));
        edVolta.setDM_Situacao (res.getString ("DM_Situacao"));
        edVolta.setPE_Aliquota (res.getLong ("PE_Aliquota"));
        edVolta.setVL_Contabil (res.getDouble ("VL_Contabil"));
        edVolta.setVL_Base_Calculo (res.getDouble ("VL_Base_Calculo"));
        edVolta.setVL_Imposto (res.getDouble ("VL_Imposto"));
        edVolta.setVL_Imposto_Creditado (res.getDouble ("VL_Imposto_Creditado"));
        edVolta.setVL_Outro (res.getDouble ("VL_Outro"));
        edVolta.setDM_Tipo_Operacao (res.getString ("DM_Tipo_Operacao"));
        dataFormatada.setDT_FormataData (res.getString ("DT_Entrada"));
        edVolta.setDT_Entrada (dataFormatada.getDT_FormataData ());
        dataFormatada.setDT_FormataData (res.getString ("DT_Emissao"));
        edVolta.setDT_Emissao (dataFormatada.getDT_FormataData ());
        edVolta.setNM_Razao_Social (res.getString ("NM_Razao_Social"));
        edVolta.setNR_CNPJ_CPF (res.getString ("NR_CNPJ_CPF"));
        edVolta.setCD_Estado (res.getString ("CD_Estado"));
        edVolta.setOID_Unidade (res.getInt ("OID_Unidade"));
        edVolta.setCD_Unidade (res.getString ("CD_Unidade"));
        edVolta.setOID_Natureza_Operacao (res.getInt ("OID_Natureza_Operacao"));
        edVolta.setCD_Natureza_Operacao (res.getString ("CD_Natureza_Operacao"));
        edVolta.setNM_Natureza_Operacao (res.getString ("NM_Natureza_Operacao"));
        edVolta.setOID_Conhecimento (res.getString ("OID_Conhecimento"));
      }
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "getByRecord()");
    }
    finally {
      closeResultset (res);
    }
    return edVolta;
  }

  public ArrayList lista (Livro_FiscalED ed) throws Excecoes {

    String sql = null;
    ResultSet res = null;
    ArrayList list = new ArrayList ();

    try {
      sql = " SELECT *, Livros_Fiscais.DM_Tipo_Operacao as DM_Tipo_OperacaoOK FROM Livros_Fiscais, Naturezas_Operacoes " +
          " WHERE  Livros_Fiscais.oid_Natureza_Operacao = Naturezas_Operacoes.oid_Natureza_Operacao ";

      if (doValida (ed.getOID_Nota_Fiscal ())) {
        sql += " AND Livros_Fiscais.oid_Nota_Fiscal= '" + ed.getOID_Nota_Fiscal () + "'";
      }
      if (doValida (ed.getOID_Conhecimento())) {
          sql += " AND Livros_Fiscais.oid_Conhecimento= '" + ed.getOID_Conhecimento() + "'";
        }
      if (doValida (ed.getDM_Tipo_Livro ())) {
        sql += " AND Livros_Fiscais.DM_Tipo_Livro = '" + ed.getDM_Tipo_Livro () + "'";
      }
      if (ed.getOID_Unidade () > 0) {
        sql += " AND Livros_Fiscais.oid_Unidade = " + ed.getOID_Unidade ();
      }
      if (doValida (ed.getDT_Inicial ()) && doValida (ed.getDT_Final ())) {
        sql += " AND Livros_Fiscais.DT_Entrada BETWEEN '" + ed.getDT_Inicial () + "' AND '" + ed.getDT_Final () + "'";
      }
      else if (doValida (ed.getDT_Inicial ()) || doValida (ed.getDT_Final ())) {
        sql += " AND Livros_Fiscais.DT_Entrada  = '" + (doValida (ed.getDT_Inicial ()) ? ed.getDT_Inicial () : ed.getDT_Final ()) + "'";
      }
      sql += " ORDER BY CD_Natureza_Operacao, PE_Aliquota";

      // System.out.println(sql);
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        Livro_FiscalED edVolta = new Livro_FiscalED ();

        edVolta.setOID_Livro_Fiscal (res.getLong ("oid_Livro_Fiscal"));
        edVolta.setDM_Tipo_Livro (res.getString ("DM_Tipo_Livro"));
        edVolta.setCD_Especie (res.getString ("CD_Especie"));
        edVolta.setPE_Aliquota (res.getDouble ("PE_Aliquota"));
        edVolta.setVL_Contabil (res.getDouble ("VL_Contabil"));
        edVolta.setVL_Base_Calculo (res.getDouble ("VL_Base_Calculo"));
        edVolta.setVL_Imposto (res.getDouble ("VL_Imposto"));
        edVolta.setVL_Imposto_Creditado (res.getDouble ("VL_Imposto_Creditado"));
        edVolta.setVL_Outro (res.getDouble ("VL_Outro"));

        edVolta.setDM_Tipo_Operacao (res.getString ("DM_Tipo_OperacaoOK"));
        if (edVolta.getDM_Tipo_Operacao ().equals ("1")) {
          edVolta.setNM_Tipo_Operacao ("Com Cred.Imposto");
        }
        if (edVolta.getDM_Tipo_Operacao ().equals ("2")) {
          edVolta.setNM_Tipo_Operacao ("Isenta/N�o Trib.");
        }
        if (edVolta.getDM_Tipo_Operacao ().equals ("3")) {
          edVolta.setNM_Tipo_Operacao ("Sem Cred.Imposto");
        }

        edVolta.setDM_Tipo_Imposto (res.getString ("DM_Tipo_Imposto"));
        dataFormatada.setDT_FormataData (res.getString ("DT_Entrada"));
        edVolta.setDT_Entrada (dataFormatada.getDT_FormataData ());

        dataFormatada.setDT_FormataData (res.getString ("DT_Emissao"));
        edVolta.setDT_Emissao (dataFormatada.getDT_FormataData ());

        edVolta.setCD_Estado (res.getString ("CD_Estado"));
        edVolta.setOID_Unidade (res.getInt ("OID_Unidade"));
        edVolta.setOID_Natureza_Operacao (res.getInt ("OID_Natureza_Operacao"));
        edVolta.setCD_Natureza_Operacao (res.getString ("CD_Natureza_Operacao"));
        edVolta.setNM_Natureza_Operacao (res.getString ("NM_Natureza_Operacao"));

        // System.out.println("Listando...");

        list.add (edVolta);
      }
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "lista()");
    }
    finally {
      closeResultset (res);
    }
    return list;
  }

  /**
   * Rotina especial para regeracoes de livros fiscais - Uso exclusivo pelo analista da area
   * @param ed
   * @throws Excecoes
   * @throws SQLException
   */
  public void regeraLivro_Fiscal_Temp (RelatorioED ed) throws Excecoes , SQLException {
    try {
      this.inicioTransacao ();
      ResultSet res = null;
      Livro_FiscalED edLivro = new Livro_FiscalED (); // instancia um ed de Livro
      Livro_FiscalBD LivroBD = new Livro_FiscalBD (this.sql); // Instancia o Livro para gravar
      // Altere este sql de modo a que retorne somente o oid das notaws a seremm regeradas
      String sql = "select distinct notas_fiscais.oid_nota_fiscal " +
          "from notas_fiscais , itens_notas_fiscais " +
          "where notas_fiscais.oid_nota_fiscal = itens_notas_fiscais.oid_nota_fiscal and " +
          "itens_notas_fiscais.oid_natureza_operacao = 31 and " +
          "dt_emissao between '01/08/2006' and '31/08/2006'";
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edLivro.setOID_Nota_Fiscal (res.getString ("Oid_nota_fiscal")); // Set o oid da NF para gerar o livro
        // Aten�ao: aqui est� gerando somente para os livros de entradas
        LivroBD.geraLivro_Fiscal_Entradas (edLivro , ed.getDm_tipo ()); // Gera o Livro de entrada
        // Atencao: aqui est� gerando somente para o livro de sa�das
        //LivroBD.geraLivro_Fiscal_Saidas(edLivro, ed.getDm_tipo());      // Gera o Livro de entrada
      }
      //*/
      this.fimTransacao (true);
    }
    catch (Excecoes e) {
      this.abortaTransacao ();
      throw e;
    }
    catch (RuntimeException e) {
      this.abortaTransacao ();
      throw e;
    }
  }

  /**
   * Rotina especial para regeracoes de livros fiscais - Uso exclusivo pelo analista da area
   * @param dt_inicio
   * @param dt_fim
   * @throws Excecoes
   * @throws SQLException
   */
  public void regeraLivro_Fiscal_Temp_CTRC(String dt_inicio, String dt_fim) throws Excecoes , SQLException {
    try {
      ResultSet res = null;
      Livro_FiscalED edLivro = new Livro_FiscalED (); // instancia um ed de Livro
      // Altere este sql de modo a que retorne somente o oid das notaws a seremm regeradas
      String sql = "select distinct(conhecimentos.oid_conhecimento) " +
          "from conhecimentos " +
          "where conhecimentos.DM_Tipo_Documento = 'C' " +
          "and conhecimentos.dm_impresso = 'S' " +
          "and conhecimentos.dt_emissao between '" + dt_inicio + "' and '" + dt_fim + "'";
// System.out.println("Regera FISCAl CTRC: "+sql);
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
    	  // System.out.println("Regera whiele");

    	  edLivro.setOID_Conhecimento(res.getString("Oid_conhecimento")); // Set o oid do CTRC para gerar o livro
        // Aten�ao: aqui est� gerando somente para os livros de CTRC (sa�da)
        geraLivro_Fiscal_CTRC(edLivro);

      }
    }
    catch (Excecoes e) {
      this.abortaTransacao ();
      throw e;
    }
    catch (RuntimeException e) {
      this.abortaTransacao ();
      throw e;
    }
    catch (Exception e) {
	    this.abortaTransacao ();
	    throw new Excecoes();
	  }
  }

  /**
   * Rotina especial para regeracoes de livros fiscais - Uso exclusivo pelo analista da area
   * @param dt_inicio
   * @param dt_fim
   * @throws Excecoes
   * @throws SQLException
   */
  public void regeraLivro_Fiscal_Temp_Entrada_Simplificada(String dt_inicio, String dt_fim) throws Excecoes , SQLException {
    try {
      this.inicioTransacao ();
      ResultSet res = null;
      Livro_FiscalED edLivro = new Livro_FiscalED (); // instancia um ed de Livro
      Livro_FiscalBD LivroBD = new Livro_FiscalBD (this.sql); // Instancia o Livro para gravar
      // Altere este sql de modo a que retorne somente o oid das notaws a seremm regeradas
      String sql = "select distinct(notas_fiscais.oid_nota_fiscal) " +
          "from notas_fiscais_transacoes notas_fiscais " +
          "where (notas_fiscais.DM_finalizado = 'S' or notas_fiscais.DM_finalizado = 'F') " +
          "and notas_fiscais.dt_emissao between '" + dt_inicio + "' and '" + dt_fim + "'";
// System.out.println("Regera FISCAL: "+sql);
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edLivro.setOID_Nota_Fiscal(res.getString("Oid_nota_fiscal")); // Set o oid da NF para gerar o livro
        // Aten�ao: aqui est� gerando somente para os livros de NF  (padrao entrada simplificada)
        LivroBD.geraLivro_Fiscal_Entrada_Simplificada(edLivro);

      }
      //*/
      this.fimTransacao (true);
    }
    catch (Excecoes e) {
      this.abortaTransacao ();
      throw e;
    }
    catch (RuntimeException e) {
      this.abortaTransacao ();
      throw e;
    }
    catch (Exception e) {
	    this.abortaTransacao ();
	    throw new Excecoes();
	  }
  }

  public byte[] geraLivro_Fiscal_Conhecimento (ConhecimentoED ed) throws Excecoes {

    byte[] b =null;
    ResultSet res = null;
    ResultSet resUF = null;
    ResultSet resCFOP= null;
    String DM_Selecao=ed.getDM_Relatorio ();

      return b;
  }




  private ResultSet monta_Selecao (ConhecimentoED ed, String DM_Selecao) throws Excecoes {

    String sql = null;
    byte[] b = null;
    boolean auxiliar = false;
    long Nr_Sort = (new Long (String.valueOf (System.currentTimeMillis ()).toString ().substring (3 , 12)).longValue ());
    String Classifica = "PRIMEIRO";
    String Campo_Lido = "";
    String contribuinte = "";
    double vl_total_frete_geral = 0;
    double vl_isentas = 0;
    double vl_credito_diferido = 0;
    double vl_credito_destacado = 0;
    double vl_icms = 0;
    double perc_icms = 0;
    double vl_base_icms = 0;
    double vl_total_isentas = 0;
    double vl_total_credito_diferido = 0;
    double vl_total_credito_destacado = 0;
    double vl_total_base_icms = 0;
    double vl_total_icms = 0;
    double vl_total_frete = 0;
    double vl_frete = 0;
    double vl_outras = 0;
    double vl_total_outras = 0;
    ResultSet res = null;

    try {

      if (DM_Selecao.equals ("RLIVRO")) {

        sql =
            " select Conhecimentos.oid_Taxa, " +
            "        Conhecimentos.oid_unidade, " +
            "        Conhecimentos.oid_Cidade_Destino, " +
            "        Conhecimentos.DM_Tipo_Conhecimento, " +
            "        Conhecimentos.DM_Situacao, " +
            "        Conhecimentos.DT_Emissao, " +
            "        Conhecimentos.NR_Conhecimento, " +
            "        Conhecimentos.vl_total_frete, " +
            "        Conhecimentos.Cd_CFO_Conhecimento, " +
            "        Conhecimentos.vl_icms, " +
            "        conhecimentos.vl_base_calculo_icms, " +
            "        Conhecimentos.PE_ALIQUOTA_ICMS, " +
            "        Taxas.DM_Campo_ICMS_Nao_Dest, " +
            "        Taxas.TX_Obs_ICMS_Nao_Dest " +
            " from Conhecimentos, Taxas, Unidades Unidade_Conhecimento, Unidades Unidade_Fiscal  " +
            " where Conhecimentos.DM_Impresso = 'S' " +
            " AND   Conhecimentos.oid_unidade = Unidade_Conhecimento.oid_Unidade " +
            " AND   Unidade_Conhecimento.oid_Unidade_Fiscal = Unidade_Fiscal.oid_Unidade " +
            " AND Conhecimentos.oid_Taxa = Taxas.oid_Taxa ";

      }
      else {

        sql =
            " select Conhecimentos.PE_ALIQUOTA_ICMS as PE_ALIQUOTA_ICMS_CTO, " +
            "        Conhecimentos.DM_Tipo_Conhecimento, " +
            "        Cidade_Origem.NM_Cidade as NM_Cidade_Origem, " +
            "        Conhecimentos.DM_Situacao, " +
            "        Conhecimentos.DT_Emissao, " +
            "        Conhecimentos.NR_Conhecimento, " +
            "        Conhecimentos.OID_Conhecimento, " +
            "        Conhecimentos.vl_total_frete, " +
            "        Conhecimentos.Cd_CFO_Conhecimento, " +
            "        Conhecimentos.vl_icms, " +
            "        Conhecimentos.vl_icms_Antecipado, " +
            "        conhecimentos.vl_base_calculo_icms, " +
            "        Unidade_Fiscal.CD_Unidade, " +
            "        Unidade_Fiscal.NM_Sigla, " +
            "        Cidade_Origem.NM_Cidade, " +
            "        Cidade_Unidade.NM_Cidade as NM_Cidade_Unidade, " +
            "        Estado_Destino.CD_Estado as CD_Estado_Destino, " +
            "        Paises.CD_Pais, " +
            "        Conhecimentos.DM_ICMS_Contribuinte, " +
            "        Taxas.PE_ALIQUOTA_ICMS as PE_ALIQUOTA_ICMS, " +
            "        Taxas.DM_Campo_ICMS_Nao_Dest, " +
            "        Taxas.TX_Obs_ICMS_Nao_Dest, " +
            "        Pessoa_Unidade.NM_Fantasia as NM_Fantasia, " +
            "        Pessoa_Cliente.NR_CNPJ_CPF as NR_CNPJ_CPF_Cliente, " +
            "        Pessoa_Cliente.NM_Fantasia as NM_Fantasia_Cliente " +
            " from Cidades Cidade_Origem, " +
            "      Conhecimentos, " +
            "      Taxas, " +
            "      Unidades, " +
            "      Estados Estado_Destino, " +
            "      Regioes_Paises, " +
            "      Paises, " +
            "      Cidades Cidade_Unidade, " +
            "      Pessoas Pessoa_Unidade, " +
            "      Unidades Unidade_Fiscal , " +
            "      Pessoas Pessoa_Cliente" +
            " where Conhecimentos.oid_Taxa = Taxas.oid_Taxa " +
            "   AND Conhecimentos.oid_Unidade = Unidades.oid_unidade " +
            "   AND Unidades.oid_pessoa = Pessoa_Unidade.oid_Pessoa " +
            "   AND Pessoa_Unidade.oid_cidade = Cidade_Unidade.oid_cidade " +
            "   AND Conhecimentos.oid_Cidade  = Cidade_Origem.oid_cidade " +
            "   AND Taxas.OID_Estado_Destino = Estado_Destino.OID_Estado " +
            "   AND Estado_Destino.OID_Regiao_Pais = Regioes_Paises.OID_Regiao_Pais " +
            "   AND Regioes_Paises.OID_Pais = Paises.OID_Pais " +
            "   AND Conhecimentos.OID_Pessoa_Pagador = Pessoa_Cliente.OID_Pessoa " +
            "   AND Conhecimentos.DM_Situacao <> 'C' " +
            "   AND Conhecimentos.DM_Impresso = 'S' " +
            "   AND Unidades.oid_Unidade_Fiscal = Unidade_Fiscal.oid_Unidade ";

      }

      if (ed.getOID_Unidade () > 0) {
        sql += " and Unidade_Fiscal.OID_Unidade = " + ed.getOID_Unidade ();
      }
      if (String.valueOf (ed.getDt_Emissao_Inicial ()) != null &&
          !String.valueOf (ed.getDt_Emissao_Inicial ()).equals ("") &&
          !String.valueOf (ed.getDt_Emissao_Inicial ()).equals ("null")) {
        sql += " and Conhecimentos.DT_Emissao >= '" + ed.getDt_Emissao_Inicial () + "'";
      }
      if (String.valueOf (ed.getDt_Emissao_Final ()) != null &&
          !String.valueOf (ed.getDt_Emissao_Final ()).equals ("") &&
          !String.valueOf (ed.getDt_Emissao_Final ()).equals ("null")) {
        sql += " and Conhecimentos.DT_Emissao <= '" + ed.getDt_Emissao_Final () + "'";
      }

      if (String.valueOf (ed.getDm_Situacao_Cobranca ()) != null &&
          !String.valueOf (ed.getDm_Situacao_Cobranca ()).equals ("null") &&
          String.valueOf (ed.getDm_Situacao_Cobranca ()).equals ("C") &&
          !String.valueOf (ed.getDm_Situacao_Cobranca ()).equals ("")) {
        sql += " and Conhecimentos.vl_icms > 0 ";
      }

      if (String.valueOf (ed.getDm_Situacao_Cobranca ()) != null &&
          !String.valueOf (ed.getDm_Situacao_Cobranca ()).equals ("null") &&
          String.valueOf (ed.getDm_Situacao_Cobranca ()).equals ("I") &&
          !String.valueOf (ed.getDm_Situacao_Cobranca ()).equals ("")) {
        sql += " and Conhecimentos.vl_icms <= 0 ";
      }

      if (String.valueOf (ed.getCD_Estado_CTRC_Destino ()) != null &&
          !String.valueOf (ed.getCD_Estado_CTRC_Destino ()).equals ("null") &&
          !String.valueOf (ed.getCD_Estado_CTRC_Destino ()).equals ("")) {
        sql += " and  Estado_Destino.CD_Estado = '" + ed.getCD_Estado_CTRC_Destino () + "'";
      }

      if (String.valueOf (ed.getCD_CFO ()) != null &&
          !String.valueOf (ed.getCD_CFO ()).equals ("null") &&
          !String.valueOf (ed.getCD_CFO ()).equals ("")) {
        sql += " and  Conhecimentos.CD_CFO_CONHECIMENTO = '" + ed.getCD_CFO () + "'";
      }

      if (DM_Selecao.equals ("RLIVRO")) {
        sql += " order by conhecimentos.dt_emissao, conhecimentos.nr_conhecimento ";
        auxiliar = false;
      }
      if (DM_Selecao.equals ("RANAL")) {
        sql += " order by Unidade_Fiscal.CD_Unidade, conhecimentos.dt_emissao, conhecimentos.nr_conhecimento ";
        auxiliar = false;
      }
      else if (DM_Selecao.equals ("RANALCLI")) {
        sql += " order by Unidade_Fiscal.CD_Unidade, conhecimentos.nr_conhecimento, conhecimentos.dt_emissao ";
        auxiliar = false;
      }
      if (DM_Selecao.equals ("RCFOP")) {
        sql += " order by conhecimentos.Cd_CFO_Conhecimento, Conhecimentos.PE_ALIQUOTA_ICMS ";
        auxiliar = true;
      }
      if (DM_Selecao.equals ("RUF")) {
        sql += " order by  DM_ICMS_Contribuinte, Estado_Destino.CD_Estado ";
        auxiliar = true;
      }
      if (DM_Selecao.equals ("RCID")) {
        sql += " order by Cidade_Origem.NM_Cidade ";
        auxiliar = true;
      }


      // System.out.println(">>>>>SQL: \n" + sql);
      // System.out.println("Nr_Sort=" + Nr_Sort);


      res = this.executasql.executarConsulta (sql.toString ());
      if (auxiliar == true) {
        Auxiliar1RN Auxiliar1RN = new Auxiliar1RN ();
        while (res.next ()) {
          contribuinte = "S";
          if (!"C".equals(res.getString("DM_Situacao"))){
            if (!DM_Selecao.equals ("C")) {

            	//resumo CFOP
            	if (DM_Selecao.equals ("RCFOP")) {
	                Campo_Lido = res.getString ("Cd_CFO_Conhecimento");
	                if (res.getDouble ("PE_ALIQUOTA_ICMS_CTO") > 0) {
	                  Campo_Lido += " - " + res.getString ("PE_ALIQUOTA_ICMS_CTO") + "%";
	                }
	                else if (res.getDouble ("PE_ALIQUOTA_ICMS") > 0) {
	                  Campo_Lido += " - " + res.getString ("PE_ALIQUOTA_ICMS") + "%";
	                }
	                else {
	                  Campo_Lido += " - 12.0 %";
	                }
                }

            	//resumo Cidade
            	if (DM_Selecao.equals ("RCID")) {
            		Campo_Lido = res.getString ("NM_Cidade_Origem");
            	}

            	//resumo Estado
            	if (DM_Selecao.equals ("RUF") && !Campo_Lido.equals (res.getString ("DM_ICMS_Contribuinte") + " - " + res.getString ("CD_Estado_Destino"))) {
            		Campo_Lido = res.getString ("DM_ICMS_Contribuinte") + " - " + res.getString ("CD_Estado_Destino");
            	}

              vl_frete = res.getDouble ("vl_total_frete");

              vl_icms = res.getDouble ("vl_icms");
              vl_base_icms = res.getDouble ("vl_total_frete");
              vl_isentas = 0;
              vl_credito_diferido = 0;
              vl_credito_destacado = 0;
              vl_outras = 0;

              if (vl_icms <= 0) {
                //vl_isentas = (vl_base_icms- (vl_base_icms * (1
                // -(perc_icms/100))));
                vl_isentas = res.getDouble ("VL_total_frete");
                //vl_credito_diferido = ( (vl_isentas * 20) / 100);
                vl_icms = 0;
                vl_base_icms = 0;
              }
              else {
                //vl_credito_destacado = ( (vl_icms * 20) / 100);
              }

              int ano = new Integer (dataFormatada.getDT_FormataData (res.getString ("dt_emissao")).substring (6 , 10)).intValue ();
              if (ano < 2005) {
                vl_outras = vl_isentas;
                vl_isentas = 0;
              }

              if (Classifica.equals ("PRIMEIRO") || Campo_Lido.equals (Classifica)) {
                Classifica = Campo_Lido;

                vl_total_frete = vl_total_frete + vl_frete;
                vl_total_isentas = vl_total_isentas + vl_isentas;
                vl_total_credito_diferido = vl_total_credito_diferido + vl_credito_diferido;
                vl_total_credito_destacado = vl_total_credito_destacado + vl_credito_destacado;
                vl_total_base_icms = vl_total_base_icms + vl_base_icms;
                vl_total_icms = vl_total_icms + vl_icms;
                vl_total_outras = vl_total_outras + vl_outras;

              }
              else {
                if (vl_total_frete > 0) {
                  Auxiliar1ED edAuxiliar1 = new Auxiliar1ED ();
                  edAuxiliar1.setNM_Classifica1 (Classifica);
                  edAuxiliar1.setNR_Sort (Nr_Sort);
                  edAuxiliar1.setVL_Classifica1 (vl_total_frete);
                  edAuxiliar1.setVL_Classifica2 (vl_total_base_icms);
                  edAuxiliar1.setVL_Classifica3 (vl_total_icms);
                  edAuxiliar1.setVL_Classifica4 (vl_total_isentas);
                  edAuxiliar1.setVL_Classifica5 (vl_total_credito_destacado);
                  edAuxiliar1.setVL_Classifica6 (vl_total_credito_diferido);
                  edAuxiliar1.setVL_Classifica7 (vl_total_outras);
                  Auxiliar1RN.inclui (edAuxiliar1);
                }
                Classifica = Campo_Lido;

                vl_total_frete = vl_frete;
                vl_total_isentas = vl_isentas;
                vl_total_credito_diferido = vl_credito_diferido;
                vl_total_credito_destacado = vl_credito_destacado;
                vl_total_base_icms = vl_base_icms;
                vl_total_icms = vl_icms;
                vl_total_outras = vl_outras;
              }

            }
          }
        }
        if (vl_total_frete > 0) {
          Auxiliar1ED edAuxiliar1 = new Auxiliar1ED ();
          edAuxiliar1.setNM_Classifica1 (Classifica);
          edAuxiliar1.setNR_Sort (Nr_Sort);
          edAuxiliar1.setVL_Classifica1 (vl_total_frete);
          edAuxiliar1.setVL_Classifica2 (vl_total_base_icms);
          edAuxiliar1.setVL_Classifica3 (vl_total_icms);
          edAuxiliar1.setVL_Classifica4 (vl_total_isentas);
          edAuxiliar1.setVL_Classifica5 (vl_total_credito_destacado);
          edAuxiliar1.setVL_Classifica6 (vl_total_credito_diferido);
          edAuxiliar1.setVL_Classifica7 (vl_total_outras);

          Auxiliar1RN.inclui (edAuxiliar1);
        }

        sql = " select * from auxiliar1 where Auxiliar1.Nr_Sort = " + Nr_Sort;
        sql += " order by auxiliar1.nm_classifica1  ";
        res = this.executasql.executarConsulta (sql.toString ());
      }

      if (auxiliar == true ) {
          sql = "delete from Auxiliar1 WHERE Auxiliar1.Nr_Sort = " + Nr_Sort;
          int i = executasql.executarUpdate (sql);
      }
    }

    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "monta_Selecao");
    }
    return res;
  }



}