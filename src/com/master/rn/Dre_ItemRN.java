package com.master.rn;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.bd.DRE_ItemBD;
import com.master.bd.Dre_Item_FormacaoBD;
import com.master.bd.Movimento_ContabilBD;
import com.master.ed.ContaED;
import com.master.ed.Dre_ItemED;
import com.master.ed.Dre_Item_FormacaoED;
import com.master.ed.Movimento_ContabilED;
import com.master.rl.JasperRL;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Valor;
import com.master.util.bd.Transacao;

/**
 * @author Régis Steigleder
 * @serial Itens do DRE.
 * @serialData 02/2006
 */
public class Dre_ItemRN extends Transacao {

    public Dre_ItemRN() {
    }

    public Dre_ItemED inclui(Dre_ItemED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new DRE_ItemBD(this.sql).inclui(ed);
            this.fimTransacao(true);
            return ed;
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void altera(Dre_ItemED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new DRE_ItemBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    public void deleta(Dre_ItemED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new DRE_ItemBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(Dre_ItemED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new DRE_ItemBD(sql).lista(ed);
            return lista;
        } finally {
            this.fimTransacao(false);
        }
    }
    public void lista(Dre_ItemED ed, HttpServletRequest request, String nmObj) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new DRE_ItemBD(sql).lista(ed);
            request.setAttribute(nmObj, lista);
            request.setAttribute("disabled",lista.size() > 0 ? "disabled": " ") ;
        } finally {
            this.fimTransacao(false);
        }
    }
    
   
    public Dre_ItemED getByRecord(Dre_ItemED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new DRE_ItemBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public void getByRecord(Dre_ItemED ed, HttpServletRequest request, String nmObj) throws Excecoes {

        try {
            this.inicioTransacao();
            Dre_ItemED edQBR = new DRE_ItemBD(this.sql).getByRecord(ed);
			request.setAttribute(nmObj, edQBR.getOid_Dre_Item()== 0 ? null : edQBR);
        } finally {
            this.fimTransacao(false);
        }
    }
    /* relDre
     * Rotina para confecção do relatório DRE
     * Faz dois tipos de relatórios - Anual e Semestral
     * Dm_Mod_Saida = A : Anual - S : Semestral
     * Sequencia da execução:
     * 	1 - Calcula as datas inicias e finais dos períodos de busca	
     * 	2 - Busca a lista de itens do dre já classificada por código estrutural
     *  Agora, sempre iterando a lista original:
     * 	3 - Para os ítens Analíticos, busca o somatório das contas de cada item dependendo do período calculado
     * 	4 - Totaliza as colunas conforme Anual ou Semestral.
     * 	5 - Faz a soma pra cima dos anaíticos para os sintéticos
     * 	6 - Faz a totalização para os itens de grau zero que tenham Dm_Acao =  "="
     *  7 - Calcula o percentual de participaçaO de cada item dependendo do item 1 (total do faturamento)  
     * 
     */
    public void relDre(Dre_ItemED ed, HttpServletRequest request, HttpServletResponse response, String nmObj) throws Excecoes {
    	String dt1I="",dt2I="",dt3I="",dt4I="",dt5I="",dt6I="";
    	String dt1F="",dt2F="",dt3F="",dt4F="",dt5F="",dt6F="";
    	String tx_Item_Descricao="",tx_Ano_Base=""; 
    	double vl_100_1=0,vl_100_2=0,vl_100_3=0,vl_100_4=0,vl_100_5=0,vl_100_6=0,vl_100_7=0,vl_100_8=0,vl_100_9=0;
        try {
            this.inicioTransacao();
            tx_Ano_Base= "Mes/Ano Base: " + ed.getTx_Mes_Ano_Base();            
            if ("A".equals(ed.getDm_Mod_Saida())){
            	//Inicializa as datas para a busca dos saldos
            	//Primeiro trimestre
            	dt1I = "01/" + ed.getTx_Mes_Ano_Base();
            	ed.setTx_Mes_Ano_Base( somaMes(ed.getTx_Mes_Ano_Base(),2) );
            	dt1F = Data.getUltimoDiaDoMes("01/" + ed.getTx_Mes_Ano_Base());
            	//Segundo trimestre
            	ed.setTx_Mes_Ano_Base( somaMes(ed.getTx_Mes_Ano_Base(),1) );
            	dt2I = "01/" + ed.getTx_Mes_Ano_Base();
            	ed.setTx_Mes_Ano_Base( somaMes(ed.getTx_Mes_Ano_Base(),2) );
            	dt2F = Data.getUltimoDiaDoMes("01/" + ed.getTx_Mes_Ano_Base());
            	//Terceiro trimestre
            	ed.setTx_Mes_Ano_Base( somaMes(ed.getTx_Mes_Ano_Base(),1) );
            	dt3I = "01/" + ed.getTx_Mes_Ano_Base();
            	ed.setTx_Mes_Ano_Base( somaMes(ed.getTx_Mes_Ano_Base(),2) );
            	dt3F = Data.getUltimoDiaDoMes("01/" + ed.getTx_Mes_Ano_Base());
            	//Quarto trimestre
            	ed.setTx_Mes_Ano_Base( somaMes(ed.getTx_Mes_Ano_Base(),1) );
            	dt4I = "01/" + ed.getTx_Mes_Ano_Base();
            	ed.setTx_Mes_Ano_Base( somaMes(ed.getTx_Mes_Ano_Base(),2) );
            	dt4F = Data.getUltimoDiaDoMes("01/" + ed.getTx_Mes_Ano_Base());
            }else{
            	//Inicializa as datas para a busca dos saldos
            	//Primeiro mes, primeiro trimestre
            	dt1I = "01/" + ed.getTx_Mes_Ano_Base();
            	dt1F = Data.getUltimoDiaDoMes(dt1I);
            	//Segundo mes, primeiro trimestre
            	ed.setTx_Mes_Ano_Base( somaMes(ed.getTx_Mes_Ano_Base(),1) );
            	dt2I = "01/" + ed.getTx_Mes_Ano_Base();
            	dt2F = Data.getUltimoDiaDoMes(dt2I);
            	ed.setTx_Mes2(ed.getTx_Mes_Ano_Base());
            	//Terceiro mes, primeiro trimestre
            	ed.setTx_Mes_Ano_Base( somaMes(ed.getTx_Mes_Ano_Base(),1) );
            	dt3I = "01/" + ed.getTx_Mes_Ano_Base();
            	dt3F = Data.getUltimoDiaDoMes(dt3I);
            	ed.setTx_Mes3(ed.getTx_Mes_Ano_Base());
            	//Primeiro mes, segundo trimestre
            	ed.setTx_Mes_Ano_Base( somaMes(ed.getTx_Mes_Ano_Base(),1) );
            	dt4I = "01/" + ed.getTx_Mes_Ano_Base();
            	dt4F = Data.getUltimoDiaDoMes(dt4I);
            	ed.setTx_Mes4(ed.getTx_Mes_Ano_Base());
            	//Segundo mes, segundo trimestre
            	ed.setTx_Mes_Ano_Base( somaMes(ed.getTx_Mes_Ano_Base(),1) );
            	dt5I = "01/" + ed.getTx_Mes_Ano_Base();
            	dt5F = Data.getUltimoDiaDoMes(dt5I);
            	ed.setTx_Mes5(ed.getTx_Mes_Ano_Base());
            	//Terceiro mes, segundo trimestre
            	ed.setTx_Mes_Ano_Base( somaMes(ed.getTx_Mes_Ano_Base(),1) );
            	dt6I = "01/" + ed.getTx_Mes_Ano_Base();
            	dt6F = Data.getUltimoDiaDoMes(dt6I);
            	ed.setTx_Mes6(ed.getTx_Mes_Ano_Base());
            }
            ArrayList lista = new DRE_ItemBD(sql).lista(ed); // Busca todos os itens do DRE
            for (int i=0 ; i<lista.size() ; i++) {			 // Itera os itens do dre para buscar os itens de formação
            	Dre_ItemED item = (Dre_ItemED)lista.get(i);  // Pega a item da lista
            	item.setNm_Fantasia("Unidade: " + ed.getNm_Fantasia());
            	item.setTx_Mes_Ano_Base(tx_Ano_Base);
            	if ("S".equals(ed.getDm_Mod_Saida())){
	            	item.setTx_Mes1(dt1I.substring(3,dt1I.length()));
	            	item.setTx_Mes2(dt2I.substring(3,dt2I.length()));
	            	item.setTx_Mes3(dt3I.substring(3,dt3I.length()));
	            	item.setTx_Mes4(dt4I.substring(3,dt4I.length()));
	            	item.setTx_Mes5(dt5I.substring(3,dt5I.length()));
	            	item.setTx_Mes6(dt6I.substring(3,dt6I.length()));
            	}
            	if ("A".equals(item.getDm_Tipo_Conta())){
            		tx_Item_Descricao = item.getNm_Item().substring(0,1) + item.getNm_Item().substring(1,item.getNm_Item().length()).toLowerCase(); 
            	}else{
            		tx_Item_Descricao = item.getNm_Item();
            	}
            	item.setNm_Item(JavaUtil.LFill(tx_Item_Descricao, tx_Item_Descricao.length() + (item.getNr_Grau().intValue() * 3), ' ', false));
            	if ( "A".equals(item.getDm_Tipo_Conta())) {  // Se não for analítico escapa
	            	Dre_Item_FormacaoED edFI = new Dre_Item_FormacaoED(); 
	            	edFI.setOid_Dre_Item(item.getOid_Dre_Item());
	            	ArrayList lstFI = new Dre_Item_FormacaoBD(sql).lista(edFI); 		// Busca os itens de formação do item do DRE
	            	for (int ii=0 ; ii<lstFI.size() ; ii++) {							// Itera os itens de formação em busca das contas contábeis
		            	Dre_Item_FormacaoED itemF = (Dre_Item_FormacaoED)lstFI.get(ii); // Pega a item da lista
	                	Movimento_ContabilED edCtbB = new Movimento_ContabilED(); 		// instancia o Movimento Contabil para busca		
	                	Movimento_ContabilED edCtbR = new Movimento_ContabilED(); 		// instancia o Movimento Contabil para retorno
	                	edCtbB.setOid_Unidade(ed.getOid_Unidade());               		// Seta a unidade
	                	edCtbB.setOid_Conta(itemF.getOid_Conta());      			 	// Seta a conta
	                	if ("A".equals(ed.getDm_Mod_Saida())){
		                	// Busca soma para o trimestre um
	                		edCtbB.setDt_Movimento_Inicial(dt1I); // Seta a data inicial
		                	edCtbB.setDt_Movimento_Final(dt1F);   // Seta a data final 
		                	edCtbR = new Movimento_ContabilBD(this.sql).getSomaConta(edCtbB); // Busca a soma do débito e do crédito 
		                	item.setVl_Valor1(item.getVl_Valor1() + (edCtbR.getVl_Credito() - edCtbR.getVl_Debito())); // Adiciona C - D no Valor do item
		                	// Busca soma para o trimestre dois
	                		edCtbB.setDt_Movimento_Inicial(dt2I); // Seta a data inicial
		                	edCtbB.setDt_Movimento_Final(dt2F);   // Seta a data final 
		                	edCtbR = new Movimento_ContabilBD(this.sql).getSomaConta(edCtbB); // Busca a soma do débito e do crédito 
		                	item.setVl_Valor2(item.getVl_Valor2() + (edCtbR.getVl_Credito() - edCtbR.getVl_Debito())); // Adiciona C - D no Valor do item
		                	// Busca soma para o trimestre tres
	                		edCtbB.setDt_Movimento_Inicial(dt3I); // Seta a data inicial
		                	edCtbB.setDt_Movimento_Final(dt3F);   // Seta a data final 
		                	edCtbR = new Movimento_ContabilBD(this.sql).getSomaConta(edCtbB); // Busca a soma do débito e do crédito 
		                	item.setVl_Valor3(item.getVl_Valor3() + (edCtbR.getVl_Credito() - edCtbR.getVl_Debito())); // Adiciona C - D no Valor do item
		                	// Busca soma para o trimestre quatro
	                		edCtbB.setDt_Movimento_Inicial(dt4I); // Seta a data inicial
		                	edCtbB.setDt_Movimento_Final(dt4F);   // Seta a data final 
		                	edCtbR = new Movimento_ContabilBD(this.sql).getSomaConta(edCtbB); // Busca a soma do débito e do crédito 
		                	item.setVl_Valor4(item.getVl_Valor4() + (edCtbR.getVl_Credito() - edCtbR.getVl_Debito())); // Adiciona C - D no Valor do item
	                	} else {
		                	// Busca soma para o mes um primeiro trimestre
	                		edCtbB.setDt_Movimento_Inicial(dt1I); // Seta a data inicial
		                	edCtbB.setDt_Movimento_Final(dt1F);   // Seta a data final 
		                	edCtbR = new Movimento_ContabilBD(this.sql).getSomaConta(edCtbB); // Busca a soma do débito e do crédito 
		                	item.setVl_Valor1(item.getVl_Valor1() + (edCtbR.getVl_Credito() - edCtbR.getVl_Debito())); // Adiciona C - D no Valor do item
		                	// Busca soma para o mes dois primeiro trimestre
	                		edCtbB.setDt_Movimento_Inicial(dt2I); // Seta a data inicial
		                	edCtbB.setDt_Movimento_Final(dt2F);   // Seta a data final 
		                	edCtbR = new Movimento_ContabilBD(this.sql).getSomaConta(edCtbB); // Busca a soma do débito e do crédito 
		                	item.setVl_Valor2(item.getVl_Valor2() + (edCtbR.getVl_Credito() - edCtbR.getVl_Debito())); // Adiciona C - D no Valor do item
		                	// Busca soma para o mes tres primeiro trimestre
	                		edCtbB.setDt_Movimento_Inicial(dt3I); // Seta a data inicial
		                	edCtbB.setDt_Movimento_Final(dt3F);   // Seta a data final 
		                	edCtbR = new Movimento_ContabilBD(this.sql).getSomaConta(edCtbB); // Busca a soma do débito e do crédito 
		                	item.setVl_Valor3(item.getVl_Valor3() + (edCtbR.getVl_Credito() - edCtbR.getVl_Debito())); // Adiciona C - D no Valor do item
		                	// Busca soma para o mes um segundo trimestre
	                		edCtbB.setDt_Movimento_Inicial(dt4I); // Seta a data inicial
		                	edCtbB.setDt_Movimento_Final(dt4F);   // Seta a data final 
		                	edCtbR = new Movimento_ContabilBD(this.sql).getSomaConta(edCtbB); // Busca a soma do débito e do crédito 
		                	item.setVl_Valor5(item.getVl_Valor5() + (edCtbR.getVl_Credito() - edCtbR.getVl_Debito())); // Adiciona C - D no Valor do item
		                	// Busca soma para o mes dois segundo trimestre
	                		edCtbB.setDt_Movimento_Inicial(dt5I); // Seta a data inicial
		                	edCtbB.setDt_Movimento_Final(dt5F);   // Seta a data final 
		                	edCtbR = new Movimento_ContabilBD(this.sql).getSomaConta(edCtbB); // Busca a soma do débito e do crédito 
		                	item.setVl_Valor6(item.getVl_Valor6() + (edCtbR.getVl_Credito() - edCtbR.getVl_Debito())); // Adiciona C - D no Valor do item
		                	// Busca soma para o mes tres segundo trimestre
	                		edCtbB.setDt_Movimento_Inicial(dt6I); // Seta a data inicial
		                	edCtbB.setDt_Movimento_Final(dt6F);   // Seta a data final 
		                	edCtbR = new Movimento_ContabilBD(this.sql).getSomaConta(edCtbB); // Busca a soma do débito e do crédito 
		                	item.setVl_Valor7(item.getVl_Valor7() + (edCtbR.getVl_Credito() - edCtbR.getVl_Debito())); // Adiciona C - D no Valor do item
	                	}
	            	}
            	}
            }
            //Totaliza as colunas
            for (int i=0 ; i<lista.size() ; i++) {			 // Itera os itens do dre para totalizar
            	Dre_ItemED item = (Dre_ItemED)lista.get(i);  // Pega a item da lista
            	if ("A".equals(ed.getDm_Mod_Saida())){
	            	// Soma para o total do ano coluna(5)
	            	item.setVl_Valor5(item.getVl_Valor5() + ( item.getVl_Valor1() + item.getVl_Valor2() + item.getVl_Valor3() + item.getVl_Valor4()) ); // Soma para o trimestre
            	}else {
            		item.setVl_Valor4(item.getVl_Valor4() + ( item.getVl_Valor1() + item.getVl_Valor2() + item.getVl_Valor3()) ); // Soma para o primeiro trimestre
            		item.setVl_Valor8(item.getVl_Valor8() + ( item.getVl_Valor5() + item.getVl_Valor6() + item.getVl_Valor7()) ); // Soma para o segundo trimestre
            		item.setVl_Valor9(item.getVl_Valor9() + ( item.getVl_Valor4() + item.getVl_Valor8() ) ); // Soma para o semestre
            	}
            }
            //Agora precisa fazer a soma pra cima dos itens analíticos para os sintéticos.
            for (int i=0 ; i<lista.size() ; i++) {			 // Itera os itens do dre para somar pra cima
            	Dre_ItemED item = (Dre_ItemED)lista.get(i);  // Pega a item da lista
				if ("A".equals(item.getDm_Tipo_Conta())){
					somaParaCima(lista, item.getCd_Estrutural(), item.getVl_Valor1(), item.getVl_Valor2(), item.getVl_Valor3(), item.getVl_Valor4(), item.getVl_Valor5(), item.getVl_Valor6(), item.getVl_Valor7(), item.getVl_Valor8(), item.getVl_Valor9());
				}
			}
            //Agora precisa fazer a totalização dos itens do DRE nos itens com grau 0 (zero).
            double vl1 = 0, vl2 = 0, vl3 = 0, vl4 = 0, vl5 = 0, vl6 = 0, vl7 = 0, vl8 = 0, vl9 = 0;
            for (int i=0 ; i<lista.size() ; i++) {			 // Itera os itens do dre para somar pra cima
            	Dre_ItemED item = (Dre_ItemED)lista.get(i);  // Pega a item da lista
				if (item.getNr_Grau().equals(new Integer(0))){
					vl1 = vl1 + item.getVl_Valor1();
					vl2 = vl2 + item.getVl_Valor2();
					vl3 = vl3 + item.getVl_Valor3();
					vl4 = vl4 + item.getVl_Valor4();
					vl5 = vl5 + item.getVl_Valor5();
					vl6 = vl6 + item.getVl_Valor6();
					vl7 = vl7 + item.getVl_Valor7();
					vl8 = vl8 + item.getVl_Valor8();
					vl9 = vl9 + item.getVl_Valor9();
					if ( "=".equals(item.getDm_Acao()) ){
						item.setVl_Valor1(vl1);
						item.setVl_Valor2(vl2);
						item.setVl_Valor3(vl3);
						item.setVl_Valor4(vl4);
						item.setVl_Valor5(vl5);
						item.setVl_Valor6(vl6);
						item.setVl_Valor7(vl7);
						item.setVl_Valor8(vl8);
						item.setVl_Valor9(vl9);
					}
				}
			}
            //Agora precisa calcular o percentual de participação dos itens em relação ao faturamento global
            for (int i=0 ; i<lista.size() ; i++) {			 // Itera os itens do dre para calcular percentual
            	Dre_ItemED item = (Dre_ItemED)lista.get(i);  // Pega a item da lista
				if ( i == 0 ){								 // Guarda o valor do item 0
					vl_100_1=item.getVl_Valor1();item.setVl_Perc1(100);
					vl_100_2=item.getVl_Valor2();item.setVl_Perc2(100);
					vl_100_3=item.getVl_Valor3();item.setVl_Perc3(100);
					vl_100_4=item.getVl_Valor4();item.setVl_Perc4(100);
					vl_100_5=item.getVl_Valor5();item.setVl_Perc5(100);
					vl_100_6=item.getVl_Valor6();item.setVl_Perc6(100);
					vl_100_7=item.getVl_Valor7();item.setVl_Perc7(100);
					vl_100_8=item.getVl_Valor8();item.setVl_Perc8(100);
					vl_100_9=item.getVl_Valor9();item.setVl_Perc9(100);
				}else{										 // Calcula percentual para os demais itens.
					item.setVl_Perc1(item.getVl_Valor1()/vl_100_1*100);
					item.setVl_Perc2(item.getVl_Valor2()/vl_100_2*100);
					item.setVl_Perc3(item.getVl_Valor3()/vl_100_3*100);
					item.setVl_Perc4(item.getVl_Valor4()/vl_100_4*100);
					item.setVl_Perc5(item.getVl_Valor5()/vl_100_5*100);
					item.setVl_Perc6(item.getVl_Valor6()/vl_100_6*100);
					item.setVl_Perc7(item.getVl_Valor7()/vl_100_7*100);
					item.setVl_Perc8(item.getVl_Valor8()/vl_100_8*100);
					item.setVl_Perc9(item.getVl_Valor9()/vl_100_9*100);
				}
			}
			// Se tiver alguma item na lista, monta relatório senão avisa na tela
			if (lista.size() > 0 ) {
				ed.setLista(lista); // Joga alista de contas ( grupo ) na ed para enviar pro relatório 
				ed.setResponse(response);
				if ("A".equals(ed.getDm_Mod_Saida())){
					ed.setNomeRelatorio("DRE2"); // Seta o nome do relatório
	            }else {
	            	ed.setNomeRelatorio("DRE1"); // Seta o nome do relatório
	            }
				new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
				//request.setAttribute("retorno",new String("T"));
			} else {
				//request.setAttribute("retorno",new String("F"));
			}
        } finally {
            this.fimTransacao(false);
        }
    }
    /* somaParaCima
     * Utilizada na rotina relDre para somar os itens analiticos nos sintéticos
     */
	private void somaParaCima(ArrayList l,String ce, double vl1, double vl2, double vl3, double vl4, double vl5, double vl6, double vl7, double vl8, double vl9){
		ContaED edConta = new ContaED();
		edConta.setCd_Estrutural(ce);
		String cdPai =  edConta.pegaGrauInferior();
		if (! "*".equals(cdPai) ) {
			for ( int i=0;i<l.size();i++){
				Dre_ItemED item = (Dre_ItemED)l.get(i);
				if (cdPai.equals(item.getCd_Estrutural())) {
					item.setVl_Valor1(Valor.round(item.getVl_Valor1() + vl1,2));
					item.setVl_Valor2(Valor.round(item.getVl_Valor2() + vl2,2));
					item.setVl_Valor3(Valor.round(item.getVl_Valor3() + vl3,2));
					item.setVl_Valor4(Valor.round(item.getVl_Valor4() + vl4,2));
					item.setVl_Valor5(Valor.round(item.getVl_Valor5() + vl5,2));
					item.setVl_Valor6(Valor.round(item.getVl_Valor6() + vl6,2));
					item.setVl_Valor7(Valor.round(item.getVl_Valor7() + vl7,2));
					item.setVl_Valor8(Valor.round(item.getVl_Valor8() + vl8,2));
					item.setVl_Valor9(Valor.round(item.getVl_Valor9() + vl9,2));
					l.set(i,item);
					break;
				}
			}	
			this.somaParaCima( l, cdPai, vl1, vl2, vl3, vl4, vl5, vl6, vl7, vl8, vl9);
		}
	}
    private String somaMes ( String mesAtual, int quantosMeses){
    	
    	int mes = Integer.valueOf(mesAtual.substring(0,2)).intValue();
    	int ano = Integer.valueOf(mesAtual.substring(3,7)).intValue();
    	mes = mes + quantosMeses;
    	if ( mes > 12 ){ ano ++ ; mes = mes - 12; }
    	String voltaMes = String.valueOf(mes)+"/"+ String.valueOf(ano);
    	if (voltaMes.length() < 7){
    		voltaMes = "0" + voltaMes;
    	}
    	return  voltaMes ;
    }
    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}