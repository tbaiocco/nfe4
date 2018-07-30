package com.master.rn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.bd.FornecedorBD;
import com.master.bd.Marca_VeiculoBD;
import com.master.bd.PneuBD;
import com.master.bd.RecapagemBD;
import com.master.bd.Vida_PneuBD;
import com.master.ed.FornecedorED;
import com.master.ed.Marca_VeiculoED;
import com.master.ed.PneuED;
import com.master.ed.RecapagemED;
import com.master.ed.Vida_PneuED;
import com.master.rl.JasperRL;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author Régis Steigleder
 * @serial Fornecedores
 * @serialData 06/2007
 */
public class FornecedorRN extends Transacao {

    public FornecedorRN() {
    }

    public FornecedorED inclui(FornecedorED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new FornecedorBD(this.sql).inclui(ed);
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

    public void altera(FornecedorED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new FornecedorBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    public void deleta(FornecedorED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new FornecedorBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(FornecedorED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new FornecedorBD(sql).lista(ed);
            return lista;
        } finally {
            this.fimTransacao(false);
        }
    }

    public void lista(FornecedorED ed, HttpServletRequest request, String nmObj) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new FornecedorBD(sql).lista(ed);
            request.setAttribute(nmObj, lista);
        } finally {
            this.fimTransacao(false);
        }
    }

    public FornecedorED getByRecord(FornecedorED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new FornecedorBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public void relatorio(FornecedorED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
        try {
            this.inicioTransacao();
            String nm_Filtro = "";
            ArrayList lista = new FornecedorBD(sql).lista(ed);
			ed.setLista(lista); // Joga a lista de veiculos no ed para enviar pro relatório 
			ed.setResponse(response);
			ed.setNomeRelatorio("pns014"); // Seta o nome do relatório
			// Monta a descricao do filtro utilizado
			BancoUtil bu = new BancoUtil();
			if (bu.doValida(ed.getOid_Pessoa()))
				nm_Filtro+=" CNPJ=" + ed.getOid_Pessoa();
			if (bu.doValida(ed.getNm_Razao_Social()))
				nm_Filtro+=" Razão Social=" + ed.getNm_Razao_Social();
			ed.setDescFiltro(nm_Filtro);
			new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
        } finally {
            this.fimTransacao(false);
        }
    }
    
    /**
     * processaRL
     * Processa solicitação de relatorio OL retornando sempre um PDF.
     * @param rel = Qual o relatorio a ser chamado
     * @param Obj = Um bean populado com parametros para a geracao do relatorio
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws Excecoes
     */
    
    public void processaRL(String rel, Object Obj, HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException, Excecoes {
		//Extrai o bean com os campos da request colados
		FornecedorED ed = (FornecedorED)Obj;
		ed.setRequest(request);
		//if ("1".equals(rel)) {
			this.relatorio(ed, request, response);	
		//} 
	
    }
    
    /**
     * processaOL
     * Processa solicitação de tela OL retornando sempre arquivo XML com a seguinte estrutura.
     * <cad>
     * 		<item campo=valor />
     * </cad>
     * @param acao
     * @param Obj
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws Excecoes
     */
    public void processaOL(String acao, Object Obj, HttpServletRequest request, HttpServletResponse response)
    	throws ServletException, IOException, Excecoes {
    	//Extrai o bean com os campos da request colados
    	FornecedorED ed = (FornecedorED)Obj;
    	//Prepara a saída
    	ed.setMasterDetails(request);
    	
    	PrintWriter out = response.getWriter();
    	out.println("<?xml version='1.0' encoding='ISO-8859-1'?>");
    	if ("I".equals(acao) ) {
    		if (checkDuplo(ed,acao)) {  
    			out.println("<ret><item oknok='Fornecedor já existe com este CNPJ!'/></ret>");
    		} else {
	    		ed = this.inclui(ed);
	    		out.println("<ret><item oknok='IOK' oid='" + ed.getOid_Fornecedor() + "' /></ret>");
    		}
    	} else 
		if ("A".equals(acao)) {
    		if (checkDuplo(ed,acao)) {  
    			out.println("<ret><item oknok='Não pode alterar o cnpj!'/></ret>");
    		} else {
    			this.altera(ed);
    			out.println("<ret><item oknok='AOK' /></ret>");
    		}
		} else 
		if ("D".equals(acao)) {
			if (checkEmUso(ed)) {
				out.println("<ret><item oknok='Impossível excluir! Fornecedor em uso!' /></ret>");
			} else {
				this.deleta(ed);
				out.println("<ret><item oknok='DOK' /></ret>");
			}			
		} else {
		out.println("<cad>");
		String saida=null;
		ArrayList lst = new ArrayList();
		lst = this.lista(ed);
		for (int i=0; i<lst.size(); i++){
			FornecedorED edVolta = new FornecedorED();
			edVolta = (FornecedorED)lst.get(i);
			if ("L".equals(acao)) {
				saida = "<item ";
				saida += "oid_Fornecedor='" + edVolta.getOid_Fornecedor() + "' ";
				saida += "oid_Pessoa='" + edVolta.getOid_Pessoa() + "' ";
				saida += "nm_Razao_Social='" + edVolta.getNm_Razao_Social() + "' ";
				saida += "nm_Endereco='" + edVolta.getNm_Endereco() + "' ";
				saida += "nm_Bairro='" + edVolta.getNm_Bairro() + "' ";
				saida += "nr_Cep='" + edVolta.getNr_Cep() + "' ";
				saida += "nm_Inscricao_Estadual='" + edVolta.getNm_Inscricao_Estadual() + "' ";
				saida += "nm_Cidade='" + edVolta.getNm_Cidade() + "' ";
				saida += "cd_Estado='" + edVolta.getCd_Estado() + "' ";
				saida += "nr_Telefone='" + edVolta.getNr_Telefone() + "' ";
				saida += "nr_Fax='" + edVolta.getNr_Fax() + "' ";
				saida += "nm_eMail='" + edVolta.getNm_eMail() + "' ";
				saida += "msg_Stamp='" + edVolta.getMsg_Stamp() + "' ";
				saida += "/>";
			}else
			if ("CB".equals(acao) || "CBC".equals(acao)) {
				if ( i==0 && "CBC".equals(acao) ) {
					saida = "<item ";
					saida += "value='0'>TODOS</item>";
					out.println(saida);
				}
				saida = "<item ";
				saida += "value='" + edVolta.getOid_Fornecedor() + "'>";
				if (edVolta.getNm_Razao_Social().length() > 30) {
					saida +=  edVolta.getNm_Razao_Social().substring(0, 30);
				} else {
					saida +=  edVolta.getNm_Razao_Social();
				}
				saida += "</item>";
			}
			out.println(saida);
		}
		out.println("</cad>");
		}
    	out.flush();
    	out.close();
    }

    public boolean checkDuplo ( FornecedorED ed, String acao) throws Excecoes {
		FornecedorED edChk = new FornecedorED();
		edChk.setOid_Empresa(ed.getOid_Empresa());
		edChk.setOid_Pessoa(ed.getOid_Pessoa());
		edChk = this.getByRecord(edChk);
		BancoUtil bu = new BancoUtil();
    	if ("I".equals(acao) && bu.doValida(edChk.getOid_Fornecedor()))
    		return true;
    	else
    	if ("A".equals(acao) && !ed.getOid_Fornecedor().equals(edChk.getOid_Fornecedor()) )
    		return true;
    	else
    		return false;
    }

    public boolean checkEmUso ( FornecedorED ed ) throws Excecoes {
		try {
			boolean achei=false;
			PneuED pneuED = new PneuED();
			pneuED.setOid_Empresa(ed.getOid_Empresa());
			pneuED.setOid_Fornecedor(ed.getOid_Fornecedor());
			this.inicioTransacao();
			achei=(new PneuBD(this.sql).lista(pneuED).size()>0 ? true : false);
			if (achei==false) {
				RecapagemED recED = new RecapagemED();
				recED.setOid_Empresa(ed.getOid_Empresa());
				recED.setOid_Fornecedor_Recapagem(ed.getOid_Fornecedor());
				achei=(new RecapagemBD(this.sql).lista(recED).size()>0 ? true : false);
				if (achei==false) {
					Vida_PneuED vidED = new Vida_PneuED();
					vidED.setOid_Empresa(ed.getOid_Empresa());
					vidED.setOid_Fornecedor(ed.getOid_Fornecedor());
					achei=(new Vida_PneuBD(this.sql).lista(vidED).size()>0 ? true : false);
				}
			}
			return achei ;
			
        } finally {
            this.fimTransacao(false);
        }
    }
    
    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}