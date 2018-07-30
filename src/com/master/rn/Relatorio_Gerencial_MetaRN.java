package com.master.rn;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.bd.Relatorio_Gerencial_MetaBD;
import com.master.ed.Relatorio_Gerencial_MetaED;
import com.master.ed.Movimento_ContabilED;
import com.master.bd.Movimento_ContabilBD;
import com.master.root.UnidadeBean;
import com.master.rl.JasperRL;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;
import com.master.util.Data;
import com.master.util.FormataValor;

/**
 * @author Regis Steigleder
 * @serial Relatorios Gerenciais
 * @serialData 08/01/2006
 */

public class Relatorio_Gerencial_MetaRN extends Transacao {

    public Relatorio_Gerencial_MetaRN() {
    }
   
    public Relatorio_Gerencial_MetaED inclui(Relatorio_Gerencial_MetaED ed ) throws Excecoes {

        try {
        	this.inicioTransacao();
			ed = new Relatorio_Gerencial_MetaBD(this.sql).inclui(ed);
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

    public void altera(Relatorio_Gerencial_MetaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Relatorio_Gerencial_MetaBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    public void deleta(Relatorio_Gerencial_MetaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            // System.out.println(ed.getOid_Relatorio_Gerencial_Meta());
            new Relatorio_Gerencial_MetaBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(Relatorio_Gerencial_MetaED ed,String listaTp) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Relatorio_Gerencial_MetaBD(this.sql).lista(ed);
            return lista;
        } finally {
            this.fimTransacao(false);
        }
    }
    
    public void lista(Relatorio_Gerencial_MetaED ed, HttpServletRequest request, HttpServletResponse response, String nmObj,String listaTp, String tipo) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Relatorio_Gerencial_MetaBD(this.sql).lista(ed);
            for (int i=0;i<lista.size();i++) {
            	Relatorio_Gerencial_MetaED meta = (Relatorio_Gerencial_MetaED)lista.get(i); // Pega a conta da lista
                meta.setOid_Unidade(ed.getOid_Unidade());	
                if ("M".equals(listaTp)){
            		Relatorio_Gerencial_MetaED edQBR = new Relatorio_Gerencial_MetaED();
            		edQBR.setOid_Relatorio_Gerencial_Conta(meta.getOid_Relatorio_Gerencial_Conta());
            		edQBR.setOid_Unidade(ed.getOid_Unidade());
            		edQBR.setTx_Mes_Ano(ed.getTx_Mes_Ano());
            		edQBR = new Relatorio_Gerencial_MetaBD(this.sql).getByRecord(edQBR);
            		if ( edQBR.getOid_Relatorio_Gerencial_Conta() > 0) {
            			meta.setOid_Relatorio_Gerencial_Meta(edQBR.getOid_Relatorio_Gerencial_Meta());
            			meta.setTx_Mes_Ano(edQBR.getTx_Mes_Ano());
            			meta.setVl_Meta(edQBR.getVl_Meta());
            			meta.setVl_Meta_TX(edQBR.getVl_Meta_TX());
            			meta.setVl_Tendencia(edQBR.getVl_Tendencia());
            			meta.setVl_Tendencia_TX(edQBR.getVl_Tendencia_TX());
            			meta.setVl_Realizado(edQBR.getVl_Realizado());
            			meta.setVl_Realizado_TX(edQBR.getVl_Realizado_TX());
            			meta.setDm_Congelado(edQBR.getDm_Congelado());
            		}
                } else {
                	/**Aqui tem que buscar o somatório (D/C) dos lançamentos da conta no mes 
                	 * e gravar (não funcionou)  a diferenca (D-C) como o realizado em metas.
                	 * Daí retornar o registro de metas no ed dependendo do mes do trimestre. 
                	 */  
                	pegaMeta(ed, meta);
                	String Mes2 = pegaMeta2(ed, meta);
                	String Mes3 = pegaMeta3(ed, meta);
                	
                	Movimento_ContabilED edCtb = new Movimento_ContabilED();
                	edCtb.setOid_Unidade(ed.getOid_Unidade());
                	edCtb.setOid_Conta(meta.getOid_Conta());
                	edCtb.setDt_Movimento_Inicial("01/" + ed.getTx_Mes_Ano());
                	edCtb.setDt_Movimento_Final(Data.getUltimoDiaDoMes("01/" + ed.getTx_Mes_Ano()));
                	meta.setTx_Ultima_Data_Mes1(edCtb.getDt_Movimento_Final());
                	edCtb = new Movimento_ContabilBD(this.sql).getSomaConta(edCtb); 
                	meta.setVl_Realizado(edCtb.getVl_Debito() - edCtb.getVl_Credito());
                	if (meta.getVl_Realizado()<0) meta.setVl_Realizado(meta.getVl_Realizado()*-1); 
                	new Relatorio_Gerencial_MetaBD(this.sql).altera(meta);
                	meta.setTx_Mes_Ano_Ext1(ed.getTx_Mes_Ano());

                	Movimento_ContabilED edCtb2 = new Movimento_ContabilED();
                	edCtb2.setOid_Unidade(ed.getOid_Unidade());
                	edCtb2.setOid_Conta(meta.getOid_Conta());
                	edCtb2.setDt_Movimento_Inicial("01/" + Mes2);
                	edCtb2.setDt_Movimento_Final(Data.getUltimoDiaDoMes("01/" + Mes2));
                	meta.setTx_Ultima_Data_Mes2(edCtb2.getDt_Movimento_Final());
                	edCtb2 = new Movimento_ContabilBD(this.sql).getSomaConta(edCtb2); 
                	meta.setVl_Realizado2(edCtb2.getVl_Debito() - edCtb2.getVl_Credito());
                	if (meta.getVl_Realizado2()<0) meta.setVl_Realizado2(meta.getVl_Realizado2()*-1);
                	new Relatorio_Gerencial_MetaBD(this.sql).altera(meta);
                	meta.setTx_Mes_Ano_Ext2(Mes2);	

                	Movimento_ContabilED edCtb3 = new Movimento_ContabilED();
                	edCtb3.setOid_Unidade(ed.getOid_Unidade());
                	edCtb3.setOid_Conta(meta.getOid_Conta());
                	edCtb3.setDt_Movimento_Inicial("01/" + Mes3);
                	edCtb3.setDt_Movimento_Final(Data.getUltimoDiaDoMes("01/" + Mes3));
                	meta.setTx_Ultima_Data_Mes3(edCtb3.getDt_Movimento_Final());
                	edCtb3 = new Movimento_ContabilBD(this.sql).getSomaConta(edCtb3); 
                	meta.setVl_Realizado3(edCtb3.getVl_Debito() - edCtb3.getVl_Credito());
                	if (meta.getVl_Realizado3()<0) meta.setVl_Realizado3(meta.getVl_Realizado3()*-1);
                	new Relatorio_Gerencial_MetaBD(this.sql).altera(meta);
                	meta.setTx_Mes_Ano_Ext3(Mes3);	
                }
            }
			if ( tipo == "R") {
				HashMap hashMap = new HashMap();
				hashMap.put("TITULO","Relatório : " + ed.getCd_Relatorio_Gerencial() + "-" + ed.getNm_Relatorio_Gerencial());
				try {
					UnidadeBean UnidadeBeanId = new UnidadeBean();
					UnidadeBean UnidadeVolta = new UnidadeBean();
					UnidadeVolta = UnidadeBeanId.getByOID_Unidade((int)ed.getOid_Unidade());
					if (UnidadeVolta.getOID_Unidade() > 0 )
					{
						hashMap.put("SUB_TITULO", "Unidade : " + UnidadeVolta.getNM_Fantasia());
					} else {
						hashMap.put("SUB_TITULO", "Unidade : Geral ");
					}
				} catch (Exception exc) {
		            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "lista(Relatorio_Gerencial_MetaRN ed...)");
		        }
				ed.setHashMap(hashMap); // Joga o hashmap de parametros no ED
				ed.setLista(lista); // Joga alista de contas ( grupo ) na ed para enviar pro relatório 
				ed.setResponse(response);
				ed.setNomeRelatorio("Relatorio_Gerencial"); // Seta o nome do relatório
				
				new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
			}else{
	            request.setAttribute(nmObj, lista);
	            request.setAttribute("disabled",lista.size() > 0 ? "disabled": " ") ;
			}	

        } finally {
            this.fimTransacao(true);
        }
    }

    public void listaMeta(Relatorio_Gerencial_MetaED ed, HttpServletRequest request, String nmObj) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Relatorio_Gerencial_MetaBD(this.sql).listaMeta(ed);
            request.setAttribute(nmObj, lista);
            request.setAttribute("disabled",lista.size() > 0 ? "disabled": " ") ;
        } finally {
            this.fimTransacao(false);
        }
    }

    public Relatorio_Gerencial_MetaED getByRecord(Relatorio_Gerencial_MetaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Relatorio_Gerencial_MetaBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    
	public void getByRecord(Relatorio_Gerencial_MetaED ed, HttpServletRequest request, String nmObj) throws Excecoes {

		try {
			this.inicioTransacao();
			Relatorio_Gerencial_MetaED edQBR = new Relatorio_Gerencial_MetaBD(this.sql).getByRecord(ed);
			request.setAttribute(nmObj, edQBR.getOid_Relatorio_Gerencial()== 0 ? null : edQBR);
		} finally { 
			this.fimTransacao(false);
		}
	}

	/** pegaMeta - Preenche a meta do mes solicitado - Chamado por this.lista
	 * @param ed
	 * @param meta
	 * @throws Excecoes
	 */
	private void pegaMeta(Relatorio_Gerencial_MetaED ed, Relatorio_Gerencial_MetaED meta) throws Excecoes {
		//Aqui tem que acessar a meta correspondente a conta do relatorio e ao mes solicitado
		Relatorio_Gerencial_MetaED edQBR = new Relatorio_Gerencial_MetaED();
		edQBR.setOid_Relatorio_Gerencial_Conta(meta.getOid_Relatorio_Gerencial_Conta());
		edQBR.setOid_Unidade(ed.getOid_Unidade());
		edQBR.setTx_Mes_Ano(ed.getTx_Mes_Ano());
		edQBR = new Relatorio_Gerencial_MetaBD(this.sql).getMeta(edQBR);
		if ( edQBR.getOid_Relatorio_Gerencial_Conta() > 0) {
			meta.setOid_Relatorio_Gerencial_Meta(edQBR.getOid_Relatorio_Gerencial_Meta());
			//meta.setTx_Mes_Ano(edQBR.getTx_Mes_Ano());
			meta.setVl_Meta(edQBR.getVl_Meta());
			//meta.setVl_Meta_TX(edQBR.getVl_Meta_TX());
			meta.setVl_Tendencia(edQBR.getVl_Tendencia());
			//meta.setVl_Tendencia_TX(edQBR.getVl_Tendencia_TX());
			meta.setVl_Realizado(edQBR.getVl_Realizado());
			//meta.setVl_Realizado_TX(edQBR.getVl_Realizado_TX());
			//meta.setDm_Congelado(edQBR.getDm_Congelado());
		}
	}
	/** pegaMeta2 - Preenche a meta do mes solicitado - Chamado por this.lista
	 * @param ed
	 * @param meta
	 * @return mes/ano
	 * @throws Excecoes
	 */
	private String pegaMeta2(Relatorio_Gerencial_MetaED ed, Relatorio_Gerencial_MetaED meta) throws Excecoes {
		//Aqui tem que acessar a meta correspondente a conta do relatorio e ao mes solicitado
		Relatorio_Gerencial_MetaED edQBR = new Relatorio_Gerencial_MetaED();
		edQBR.setOid_Relatorio_Gerencial_Conta(meta.getOid_Relatorio_Gerencial_Conta());
		edQBR.setOid_Unidade(ed.getOid_Unidade());
		int Mes = Integer.parseInt(ed.getTx_Mes_Ano().substring(0,2));
		int Ano = Integer.parseInt(ed.getTx_Mes_Ano().substring(3,7));
		Mes=Mes+1; if (Mes > 12){Mes=Mes-12; Ano++;}
		edQBR.setTx_Mes_Ano(FormataValor.fillLeft(Mes,"0", 2) +"/"+ String.valueOf(Ano));
		edQBR = new Relatorio_Gerencial_MetaBD(this.sql).getMeta(edQBR);
		if ( edQBR.getOid_Relatorio_Gerencial_Conta() > 0) {
			meta.setVl_Meta2(edQBR.getVl_Meta());
			meta.setVl_Tendencia2(edQBR.getVl_Tendencia());
			meta.setVl_Realizado2(edQBR.getVl_Realizado());
		}
		return (FormataValor.fillLeft(Mes,"0", 2) +"/"+ String.valueOf(Ano));
	}
	/** pegaMeta3 - Preenche a meta do mes solicitado - Chamado por this.lista
	 * @param ed
	 * @param meta
	 * @return mes/ano
	 * @throws Excecoes
	 */
	private String pegaMeta3(Relatorio_Gerencial_MetaED ed, Relatorio_Gerencial_MetaED meta) throws Excecoes {
		//Aqui tem que acessar a meta correspondente a conta do relatorio e ao mes solicitado
		Relatorio_Gerencial_MetaED edQBR = new Relatorio_Gerencial_MetaED();
		edQBR.setOid_Relatorio_Gerencial_Conta(meta.getOid_Relatorio_Gerencial_Conta());
		edQBR.setOid_Unidade(ed.getOid_Unidade());
		int Mes = Integer.parseInt(ed.getTx_Mes_Ano().substring(0,2));
		int Ano = Integer.parseInt(ed.getTx_Mes_Ano().substring(3,7));
		Mes = Mes + 2 ; if (Mes > 12){Mes=Mes-12; Ano++;}
		edQBR.setTx_Mes_Ano(FormataValor.fillLeft(Mes,"0", 2) +"/"+ String.valueOf(Ano));
		edQBR = new Relatorio_Gerencial_MetaBD(this.sql).getMeta(edQBR);
		if ( edQBR.getOid_Relatorio_Gerencial_Conta() > 0) {
			meta.setVl_Meta3(edQBR.getVl_Meta());
			meta.setVl_Tendencia3(edQBR.getVl_Tendencia());
			meta.setVl_Realizado3(edQBR.getVl_Realizado());
		}
		return (FormataValor.fillLeft(Mes,"0", 2) +"/"+ String.valueOf(Ano));
	}

    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}