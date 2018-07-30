package com.master.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import br.servicos.CteServicos;

import com.master.root.UnidadeBean;

import br.cte.model.InformacoesCertificado;

public class NFeUtil {

	public InformacoesCertificado getInfCertificado(String cnpj_formatado) throws Excecoes{
		br.cte.model.InformacoesCertificado retorno = new br.cte.model.InformacoesCertificado();
		try {
			CteServicos servico = new CteServicos();
	        retorno = servico.getInformacoesCertificado(cnpj_formatado);
	        if (retorno != null) {
//	            System.out.println("Retorno Certificado ok!!!\n"
//	                    + " por: " + retorno.getEmissor() + "ms\n"
//	                    + " para: " + retorno.getEmitidoPara() + "\n"
//	                    + " ate: " + retorno.getValidoAte());
	        } else {
	            System.out.println("Erro ao buscar dados Certificado ... consulte hashMap");
	        }
		} catch(Exception e){
			e.printStackTrace();
			throw new Excecoes();
		}
		return retorno;
	}

	public List getInfCertificado() throws Excecoes{
		ArrayList retorno = new ArrayList();
		try{
			Iterator it = UnidadeBean.getAll_Unidade().iterator();
		    while(it.hasNext()){
		    	UnidadeBean un = (UnidadeBean)it.next();

		    	String cnpj_f = JavaUtil.formataCNPJ_CPF(un.getOID_Pessoa());
	    		System.out.println("consultando...:"+cnpj_f);
	    		InformacoesCertificado crt = this.getInfCertificado(cnpj_f);
	    		if(crt != null && JavaUtil.doValida(crt.getEmitidoPara())){
	    			retorno.add(crt);
	    		}
		    }

		    if(retorno.size()>0){
		    	Collections.sort(retorno, new Comparator() {
		            public int compare(Object o1, Object o2) {
		            	InformacoesCertificado ed1 = (InformacoesCertificado)o1;
		            	InformacoesCertificado ed2 = (InformacoesCertificado)o2;
		            	return ed1.getCnpj().compareTo(ed2.getCnpj());
		            }
		        });
		    }

		} catch(Exception e){
			e.printStackTrace();
			throw new Excecoes();
		}

		return retorno;
	}

	public void getInfCertificadoVencendo() throws Excecoes{
		ArrayList retorno = new ArrayList();

		try{
			String dataComparacao = Data.manipulaDias(Data.getDataDMY(), 15);
			Iterator it = UnidadeBean.getAll_Unidade().iterator();
		    while(it.hasNext()){
		    	UnidadeBean un = (UnidadeBean)it.next();

		    	String cnpj_f = JavaUtil.formataCNPJ_CPF(un.getOID_Pessoa());
//	    		System.out.println("consultando...:"+cnpj_f);
	    		InformacoesCertificado crt = this.getInfCertificado(cnpj_f);
	    		if(crt != null && JavaUtil.doValida(crt.getEmitidoPara())){

	    			if(crt.getValidoAte().before(Data.strToDate(dataComparacao))){
	    				retorno.add(crt);
	    			}
	    		}
		    }

		    if(retorno.size()>0){
		    	Collections.sort(retorno, new Comparator() {
		            public int compare(Object o1, Object o2) {
		            	InformacoesCertificado ed1 = (InformacoesCertificado)o1;
		            	InformacoesCertificado ed2 = (InformacoesCertificado)o2;
		            	return ed1.getValidoAte().compareTo(ed2.getValidoAte());
		            }
		        });
		    }

		    Iterator<InformacoesCertificado> itr = retorno.iterator();
		    FileUtil.deleteFile("/data/miro/cert.html");
		    String aux = "<html><head><style>";
		    aux += "legend                    ";
		    aux += "{                         ";
		    aux += "color: #000080;           ";
		    aux += "font-family: Verdana;     ";
		    aux += "font-weight: bold;        ";
		    aux += "background: #D3D3D3;      ";
		    aux += "border: 1px solid #000080;";
		    aux += "padding: 2px 6px;         ";
		    aux += "}                         ";
		    aux += "fieldset                  ";
		    aux += "{                         ";
		    aux += "font-family: Verdana;     ";
		    aux += "padding: 20px 26px;       ";
		    aux += "padding-right: 20px;      ";
		    aux += "margin-left: 20px;        ";
		    aux += "}                         ";
		    aux += "</style></head></body>";
		    aux += "<fieldset><legend> Certificados VENCENDO em 15 dias: </legend>";
		    while(itr.hasNext()){
		    	InformacoesCertificado cert = (InformacoesCertificado)itr.next();
		    	aux += "<font color=\"navy\">Emitido para: <strong>"+cert.getCnpj()+"</strong></font><font color=\"red\"><strong>&nbsp;VENCIMENTO:&nbsp;"+(cert.getValidoAte().toLocaleString())+"&nbsp;!!!</strong></font><br>";
		    }
		    aux += "</body></html>";
		    FileUtil.saveToFile("/data/miro/cert.html", aux);

		} catch(Exception e){
			e.printStackTrace();
			throw new Excecoes();
		}

	}
}
