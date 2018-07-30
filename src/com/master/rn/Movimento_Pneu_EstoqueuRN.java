package com.master.rn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.bd.Movimento_Pneu_EstoqueBD;
import com.master.bd.PneuBD;
import com.master.ed.Movimento_Pneu_EstoqueED;
import com.master.ed.PneuED;
import com.master.ed.Vida_PneuED;
import com.master.rl.JasperRL;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.FormataValor;
import com.master.util.bd.Transacao;


/**
 * @author PRS
 * @serial Movimentos Pneus Estoque
 * @serialData 06/2007
 */
public class Movimento_Pneu_EstoqueuRN extends Transacao {

    public Movimento_Pneu_EstoqueuRN() {
    }

    public Movimento_Pneu_EstoqueED inclui(Movimento_Pneu_EstoqueED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Movimento_Pneu_EstoqueBD(this.sql).inclui(ed);
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

    
    public void deleta(Movimento_Pneu_EstoqueED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Movimento_Pneu_EstoqueBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(Movimento_Pneu_EstoqueED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Movimento_Pneu_EstoqueBD(sql).lista(ed);
            return lista;
        } finally {
            this.fimTransacao(false);
        }
    }

    public Movimento_Pneu_EstoqueED getByRecord(Movimento_Pneu_EstoqueED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Movimento_Pneu_EstoqueBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    /**
    public void relatorio(Movimento_Pneu_EstoqueED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
        try {
            this.inicioTransacao();
            String nm_Filtro = "";
            ArrayList lista = new Movimento_Pneu_EstoqueBD(sql).lista(ed);
			ed.setLista(lista); // Joga a lista de veiculos no ed para enviar pro relatório 
			ed.setResponse(response);
			ed.setNomeRelatorio("pns016"); // Seta o nome do relatório
			// Monta a descricao do filtro utilizado
			BancoUtil bu = new BancoUtil();
			if (bu.doValida(ed.getNr_Fogo()))
				nm_Filtro+=" Pneu=" + ed.getNr_Fogo();
			if (bu.doValida(ed.getNm_Fornecedor()))
				nm_Filtro+=" Fornecedor=" + ed.getNm_Fornecedor();    					
			if (bu.doValida(ed.getNm_Fabricante_Pneu()))
				nm_Filtro+=" Fabricante=" + ed.getNm_Fabricante_Pneu();
			if (bu.doValida(ed.getNm_Modelo_Pneu()))
				nm_Filtro+=" Desenho=" + ed.getNm_Modelo_Pneu();
			ed.setDescFiltro(nm_Filtro);
			new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
        } finally {
            this.fimTransacao(false);
        }
    }
    **/
    
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
    	Movimento_Pneu_EstoqueED ed = (Movimento_Pneu_EstoqueED)Obj;
    	ed.setRequest(request);
    	//if ("1".equals(rel)) {
    	//	this.relatorio(ed, request, response);	
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
    	Movimento_Pneu_EstoqueED ed = (Movimento_Pneu_EstoqueED)Obj;
    	//Prepara a saída
    	
    	PrintWriter out = response.getWriter();
    	out.println("<?xml version='1.0' encoding='ISO-8859-1'?>");
    	if ("I".equals(acao) ) {
   			ed = this.inclui(ed);
			out.println("<ret><item oknok='IOK' oid='" + ed.getOid_Movimento_Pneu_Estoque() + "' /></ret>");
       	}else
		if ("D".equals(acao)) {
			this.deleta(ed);
			out.println("<ret><item oknok='DOK' /></ret>");
		} else {
		out.println("<cad>");
		String saida;
		ArrayList lst = new ArrayList();
		lst = this.lista(ed);
		for (int i=0; i<lst.size(); i++){
			Movimento_Pneu_EstoqueED edVolta = new Movimento_Pneu_EstoqueED();
			edVolta = (Movimento_Pneu_EstoqueED)lst.get(i);
			if ("L".equals(acao)) {
				saida = "<item ";
				saida += "oid_Empresa='"+ edVolta.getOid_Empresa() + "' ";
				saida += "oid_Movimento_Pneu_Estoque='"+ edVolta.getOid_Movimento_Pneu_Estoque() + "' ";
				saida += "oid_Pneu='"+ edVolta.getOid_Pneu() + "' ";
				saida += "dt_Movimento_Pneu_Estoque='"+ edVolta.getDt_Movimento_Pneu_Estoque() + "' ";
				saida += "oid_Local_Estoque_Origem='"+ edVolta.getOid_Local_Estoque_Origem() + "' ";
				saida += "oid_Local_Estoque_Destino='"+ edVolta.getOid_Local_Estoque_Destino() + "' ";
				saida += "nm_Local_Estoque_Origem='"+ edVolta.getNm_Local_Estoque_Origem() + "' ";
				saida += "nm_Local_Estoque_Destino='"+ edVolta.getNm_Local_Estoque_Destino() + "' ";
				saida += "nr_Fogo='"+ edVolta.getNr_Fogo() + "' ";
				saida += "/>";
				out.println(saida);
			}
		}
		out.println("</cad>");
		}
    	out.flush();
    	out.close();
    }
   
    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}