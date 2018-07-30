package com.master.rn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.servlet.http.HttpServletResponse;

import com.master.bd.CompromissoBD;
import com.master.bd.DiarioBD;
import com.master.ed.CompromissoED;
import com.master.ed.DiarioED;
import com.master.rl.DiarioRL;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.bd.Transacao;

public class DiarioRN extends Transacao {

    public DiarioRN() {
    }

    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }

    public void geraDiario_Razao_ClientesOLD(DiarioED ed, HttpServletResponse response) throws Excecoes {
    	ArrayList toReport = new ArrayList();
    	double saldo_anterior = 0, saldo_inicial_geral = 0;
    	double saldo = 0;
    	String cliente = "" , clieAux = "";

        try {
            this.inicioTransacao();
//            if(JavaUtil.doValida(ed.getDM_Origem())){
//	            if(ed.getDM_Origem().equals("FAT")) new DiarioBD(this.sql).geraDiario_Razao_Clientes_FAT(ed, response);
//	            else if(ed.getDM_Origem().equals("CTRC")) new DiarioBD(this.sql).geraDiario_Razao_Clientes_CTRC(ed, response);
//	            else if(ed.getDM_Origem().equals("CRT")) new DiarioBD(this.sql).geraDiario_Razao_Clientes_CRT(ed, response);
//            }
            //agora faz tudo na mesma pegada! hahaha!!!
            DiarioBD diario = new DiarioBD(this.sql);
//            toReport.addAll(diario.getDadosDiario_Razao_Clientes_CRT(ed));
//            toReport.addAll(diario.getDadosDiario_Razao_Clientes_CTRC(ed));
            toReport.addAll(diario.getDadosDiario_Razao_Clientes_FAT(ed));
            //ordena tudo pela data
            Collections.sort (toReport , new Comparator () {
	          public int compare (Object o1 , Object o2) {
	            DiarioED ed1 = (DiarioED) o1;
	            DiarioED ed2 = (DiarioED) o2;
	            return ed1.getDt_Emissao().compareTo(ed2.getDt_Emissao());
	          }
	        });

            //calcula saldos
            ArrayList resultados = new ArrayList();
            clieAux = "";
            saldo_anterior = 0;
            saldo_inicial_geral = 0;
            if (ed.getDM_Relatorio ().equals ("R")) {
            	//ordena pelo nome
            	Collections.sort (toReport , new Comparator () {
      	          public int compare (Object o1 , Object o2) {
      	            DiarioED ed1 = (DiarioED) o1;
      	            DiarioED ed2 = (DiarioED) o2;
      	            return ed1.getNM_Razao_Social().compareTo(ed2.getNM_Razao_Social());
      	          }
      	        });
            	//faz a iteração...
            	for(int t=0;t<toReport.size();t++){
            		DiarioED edLista = (DiarioED)toReport.get(t);
            		cliente = edLista.getOid_Pessoa();
//System.out.println(t + " | cli: " + cliente);
            		if (!cliente.equals (clieAux)) {
            			saldo_anterior = getSaldoAnterior(cliente, ed.getData_Emissao_Inicial(), ed.getData_Emissao_Final(), "FAT");
            			edLista.setVL_Saldo_Inicial(saldo_anterior);
					  	saldo = saldo_anterior;
					  	saldo_inicial_geral += saldo_anterior;
				  	}
            		saldo = saldo - edLista.getVL_Credito() + edLista.getVL_Debito();
            		edLista.setVL_Saldo(saldo);
            		resultados.add(edLista);
  					clieAux = cliente;
			  	}
            	toReport = new ArrayList(resultados);
            }
            ed.setVL_Saldo_Inicial(saldo_inicial_geral);
            if(JavaUtil.doValida(ed.getSO()) && ed.getSO().equalsIgnoreCase("CSV")){
            	new DiarioRL().geraDiario_Razao_ClientesTXT(toReport, ed, response);
            } else {
            	new DiarioRL().geraDiario_Razao_ClientesTOTAL(toReport, ed, response);
            }
            this.fimTransacao(true);
        } catch (Excecoes exc) {
        	exc.printStackTrace();
            this.abortaTransacao();
            throw exc;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void geraDiario_Razao_Clientes(DiarioED ed, HttpServletResponse response) throws Excecoes {
    	ArrayList toReport = new ArrayList();
    	double saldo_anterior = 0, saldo_inicial_geral = 0;
    	double saldo = 0;
    	String cliente = "" , clieAux = "";

        try {
            this.inicioTransacao();

            DiarioBD diario = new DiarioBD(this.sql);
            if(JavaUtil.doValida(ed.getDM_Origem())){
            	if(ed.getDM_Origem().equals("FAT"))
            		toReport.addAll(diario.getDadosDiario_Razao_Clientes_FAT(ed));
            	else if(ed.getDM_Origem().equals("CTRC")){
            		toReport.addAll(diario.getDadosDiario_Razao_Clientes_CTRC(ed));
            		toReport.addAll(diario.getDadosDiario_Razao_Clientes_NF(ed));
            	}
            } else {
            	toReport.addAll(diario.getDadosDiario_Razao_Clientes_CTRC(ed));
                toReport.addAll(diario.getDadosDiario_Razao_Clientes_FAT(ed));
            }
            //ordena primeiro por rz social
            Collections.sort (toReport , new Comparator () {
  	          public int compare (Object o1 , Object o2) {
  	            DiarioED ed1 = (DiarioED) o1;
  	            DiarioED ed2 = (DiarioED) o2;
  	            return ed1.getNM_Razao_Social().compareTo(ed2.getNM_Razao_Social());
  	          }
  	        });
            //
            //ordena tudo pela data
            Collections.sort (toReport , new Comparator () {
	          public int compare (Object o1 , Object o2) {
	            DiarioED ed1 = (DiarioED) o1;
	            DiarioED ed2 = (DiarioED) o2;
	            return ed1.getDt_Emissao().compareTo(ed2.getDt_Emissao());
	          }
	        });

            //calcula saldos
            ArrayList resultados = new ArrayList();
            clieAux = "";
            saldo_anterior = 0;
            saldo_inicial_geral = 0;
            if (ed.getDM_Relatorio ().equals ("R")) {
            	//ordena pelo nome
            	Collections.sort (toReport , new Comparator () {
      	          public int compare (Object o1 , Object o2) {
      	            DiarioED ed1 = (DiarioED) o1;
      	            DiarioED ed2 = (DiarioED) o2;
      	            return ed1.getNM_Razao_Social().compareTo(ed2.getNM_Razao_Social());
      	          }
      	        });
            	//faz a iteração...
            	for(int t=0;t<toReport.size();t++){
            		DiarioED edLista = (DiarioED)toReport.get(t);
            		cliente = edLista.getOid_Pessoa();
//System.out.println(t + " | cli: " + cliente);
            		if (!cliente.equals (clieAux)) {
            			saldo_anterior = getSaldoAnterior(cliente, ed.getData_Emissao_Inicial(), ed.getData_Emissao_Final(), ed.getDM_Origem());
            			edLista.setVL_Saldo_Inicial(saldo_anterior);
					  	saldo = saldo_anterior;
					  	saldo_inicial_geral += saldo_anterior;
				  	}
            		saldo = saldo - edLista.getVL_Credito() + edLista.getVL_Debito();
            		edLista.setVL_Saldo(saldo);
            		resultados.add(edLista);
  					clieAux = cliente;
			  	}
            	toReport = new ArrayList(resultados);
            }
            ed.setVL_Saldo_Inicial(saldo_inicial_geral);
            if(JavaUtil.doValida(ed.getSO()) && ed.getSO().equalsIgnoreCase("CSV")){
            	new DiarioRL().geraDiario_Razao_ClientesTXT(toReport, ed, response);
            } else {
            	new DiarioRL().geraDiario_Razao_ClientesTOTAL(toReport, ed, response);
            }
            this.fimTransacao(true);
        } catch (Excecoes exc) {
        	exc.printStackTrace();
            this.abortaTransacao();
            throw exc;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void geraDiario_Razao_Fornecedores(CompromissoED ed, HttpServletResponse res) throws Excecoes {

        try {
            this.inicioTransacao();
            new DiarioBD(this.sql).geraDiario_Razao_Fornecedores(ed, res);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    private double getSaldoAnterior(String cliente, String dt_inicial, String dt_final, String origem) throws Excecoes {
    	double toReturn = 0;
    	try {
    		this.inicioTransacao();

    		if(!JavaUtil.doValida(origem)){
    			toReturn = new DiarioBD(this.sql).getSaldoAnterior(cliente, dt_inicial, dt_final, "CTRC");

    		} else {
    			toReturn = new DiarioBD(this.sql).getSaldoAnterior(cliente, dt_inicial, dt_final, origem);
    		}

            this.fimTransacao(false);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
        return toReturn;
    }

}
