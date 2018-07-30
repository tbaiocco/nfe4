package com.master.rn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.bd.UnidadeBD;
import com.master.ed.UnidadeED;
import com.master.rl.JasperRL;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class UnidadeRN extends Transacao {

    public UnidadeRN() {
    }

    public UnidadeED inclui(UnidadeED ed) throws Excecoes {

        this.inicioTransacao();
        ed = new UnidadeBD(this.sql).inclui(ed);
        this.fimTransacao(true);

        return ed;
    }

    public void altera(UnidadeED ed) throws Excecoes {

        this.inicioTransacao();
        new UnidadeBD(this.sql).altera(ed);
        this.fimTransacao(true);
    }

    public void deleta(UnidadeED ed) throws Excecoes {

        this.inicioTransacao();
        new UnidadeBD(this.sql).deleta(ed);
        this.fimTransacao(true);
    }

    public ArrayList lista(UnidadeED ed) throws Excecoes {

        this.inicioTransacao();
        ArrayList lista = new UnidadeBD(sql).lista(ed);
        this.fimTransacao(false);
        return lista;
    }

    public UnidadeED getByRecord(UnidadeED ed) throws Excecoes {

        this.inicioTransacao();
        ed = new UnidadeBD(this.sql).getByRecord(ed);
        this.fimTransacao(false);

        return ed;
    }

    
    public void relatorio(UnidadeED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
        try {
            this.inicioTransacao();
            String nm_Filtro = "";
            ArrayList lista = new UnidadeBD(sql).lista(ed);
			ed.setLista(lista); // Joga a lista de unidades no ed para enviar pro relatório 
			ed.setResponse(response);
			ed.setNomeRelatorio("pns009"); // Seta o nome do relatório
			// Monta a descricao do filtro utilizado
			BancoUtil bu = new BancoUtil();
			if (bu.doValida(ed.getNm_Unidade()))
				nm_Filtro+=" Unidade=" + ed.getNm_Unidade();
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
		UnidadeED ed = (UnidadeED)Obj;
		
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
    	UnidadeED ed = (UnidadeED)Obj;
    	//Prepara a saída
    	PrintWriter out = response.getWriter();
    	out.println("<?xml version='1.0' encoding='ISO-8859-1'?>");
    	if ("I".equals(acao) ) {
    		ed = this.inclui(ed);
    		out.println("<ret><item oknok='IOK' oid='" + ed.getOid_Unidade() + "'/></ret>");
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
				UnidadeED edVolta = new UnidadeED();
				edVolta = (UnidadeED)lst.get(i);
				if ("L".equals(acao)) {
					saida = "<item ";
					saida += "oid_Unidade='" + edVolta.getOid_Unidade() + "' ";
					saida += "nm_Unidade='" + edVolta.getNm_Unidade() + "' ";
					saida += "oid_Empresa='" + edVolta.getOid_Empresa() + "' ";
					saida += "oid_Pessoa='" + edVolta.getOid_Pessoa() + "' ";
					saida += ">";
					saida += "</item>";
				} else
				if ("CB".equals(acao) || "CBC".equals(acao)) {
					if ( i==0 && "CBC".equals(acao) ) {
						saida = "<item ";
						saida += "value='0'>TODAS</item>";
						out.println(saida);
					}
					saida = "<item ";
					saida += "value='" + edVolta.getOid_Unidade() + "'>";
					saida +=  edVolta.getNm_Unidade();
					saida += "</item>";
				}
				out.println(saida);
			}
			out.println("</cad>");
		}
    	out.flush();
    	out.close();
    }
    
}