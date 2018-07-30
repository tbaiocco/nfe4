package com.master.rn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.bd.Medicao_PneuBD;
import com.master.bd.PneuBD;
import com.master.ed.Medicao_PneuED;
import com.master.ed.PneuED;
import com.master.rl.JasperRL;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FormataValor;
import com.master.util.bd.Transacao;

/**
 * @author Regis Steigleder
 * @serial Medicoes de pneus
 * @serialData 08/2007
 */
public class Medicao_PneuRN extends Transacao {

    public Medicao_PneuRN() {

    }

    public Medicao_PneuED inclui(Medicao_PneuED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Medicao_PneuBD(this.sql).inclui(ed);
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

    public void altera(Medicao_PneuED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Medicao_PneuBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void deleta(Medicao_PneuED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Medicao_PneuBD(this.sql).delete(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(Medicao_PneuED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Medicao_PneuBD(sql).lista(ed);
            return lista;
        } finally {
            this.fimTransacao(false);
        }
    }

    public Medicao_PneuED getByRecord(Medicao_PneuED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Medicao_PneuBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    // Busca a ultima medicao de sulco da vida
    public Medicao_PneuED getMedicao(Medicao_PneuED ed) throws Excecoes {
    	Medicao_PneuED edQBR = new Medicao_PneuED();
        try {
            this.inicioTransacao();
    		edQBR.setOid_Pneu(ed.getOid_Pneu());
    		edQBR.setNr_Vida(ed.getNr_Vida());
    		edQBR = new Medicao_PneuBD(this.sql).getMedicao(edQBR) ;
        } finally {
            this.fimTransacao(false);
        }
        return edQBR;
    }


    public void relatorio(Medicao_PneuED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
        try {
            this.inicioTransacao();
            String nm_Filtro = "";
            ArrayList lista = new Medicao_PneuBD(sql).lista(ed);
			ed.setLista(lista); // Joga a lista de veiculos no ed para enviar pro relatório
			ed.setResponse(response);
			ed.setNomeRelatorio("pns305"); // Seta o nome do relatório
			// Monta a descricao do filtro utilizado
			BancoUtil bu = new BancoUtil();
			if (bu.doValida(ed.getNr_Fogo()))
				nm_Filtro+=" Pneu=" + ed.getNr_Fogo();
			if (bu.doValida(ed.getDt_inicial()))
				nm_Filtro+=" Data Inicial=" + ed.getDt_Medicao_Pneu_Inicial();
			if (bu.doValida(ed.getDt_final()))
				nm_Filtro+=" Data Final=" + ed.getDt_Medicao_Pneu_Final();
			ed.setDescFiltro(nm_Filtro);
			new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
        } finally {
            this.fimTransacao(false);
        }
    }

    public void relatorioMedicao(Medicao_PneuED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
    	BancoUtil bu = new BancoUtil();
    	try {
    		this.inicioTransacao();
    		String nm_Filtro = "";
            ArrayList lista = new Medicao_PneuBD(sql).lista(ed);
    		ed.setLista(lista); // Joga a lista de veiculos no ed para enviar pro relatório
    		ed.setResponse(response);
    		ed.setNomeRelatorio("pns305"); // Seta o nome do relatório
    		// Monta a descricao do filtro utilizado
    		if (ed.getOid_Pneu()>0)
    			nm_Filtro+=" Pneu=" + ed.getOid_Pneu();
    		if (bu.doValida(ed.getDt_Medicao_Pneu()))
    			nm_Filtro+=" Medicao=" + ed.getDt_Medicao_Pneu();
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
		Medicao_PneuED ed = (Medicao_PneuED)Obj;
		ed.setRequest(request);
		//if ("1".equals(rel)) {
			//this.relatorio(ed, request, response);
		//}
		if ("2".equals(rel)) {
			this.relatorio(ed, request, response);
		}
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
    	Medicao_PneuED ed = (Medicao_PneuED)Obj;
    	//Prepara a saída

    	PrintWriter out = response.getWriter();
    	out.println("<?xml version='1.0' encoding='ISO-8859-1'?>");
    	if ("I".equals(acao) ) {
    		ed = this.inclui(ed);
    		out.println("<ret><item oknok='IOK' oid='" + ed.getOid_Medicao_Pneu() + "' /></ret>");
    	} else
		if ("A".equals(acao)) {
			this.altera(ed);
			out.println("<ret><item oknok='AOK' /></ret>");
		} else
		if ("D".equals(acao)) {
			this.deleta(ed);
			out.println("<ret><item oknok='DOK' /></ret>");
		} else {
		out.println("<cad>");
		String saida=null;
		ArrayList lst = new ArrayList();
		lst = this.lista(ed);
		for (int i=0; i<lst.size(); i++){
			Medicao_PneuED edVolta = new Medicao_PneuED();
			edVolta = (Medicao_PneuED)lst.get(i);
			if ("L".equals(acao)) {
				saida = "<item ";
				saida += "oid_Medicao_Pneu='" + edVolta.getOid_Medicao_Pneu() + "' ";
				saida += "oid_Empresa='" + edVolta.getOid_Empresa() + "' ";
				saida += "oid_Pneu='" + edVolta.getOid_Pneu() + "' ";
				saida += "nr_Vida='" + edVolta.getNr_Vida() + "' ";
				saida += "dt_Medicao_Pneu='" + edVolta.getDt_Medicao_Pneu()+ "' ";
				saida += "nr_Mm_Sulco='" + FormataValor.formataValorBT(edVolta.getNr_Mm_Sulco(),2) + "' ";
				saida += "nr_Km_Acumulada_Pneu='" + FormataValor.formataValorBT(edVolta.getNr_Km_Acumulada_Pneu(),1) + "' ";
				saida += "nr_Fogo='" + edVolta.getNr_Fogo() + "' ";
				saida += "tx_Situacao='" + edVolta.getTx_Situacao() + "' ";
				saida += "/>";
			}
			out.println(saida);
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