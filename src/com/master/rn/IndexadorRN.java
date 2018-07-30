package com.master.rn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.bd.IndexadorBD;
import com.master.bd.Marca_VeiculoBD;
import com.master.ed.IndexadorED;
import com.master.ed.Local_EstoqueED;
import com.master.ed.Marca_VeiculoED;
import com.master.ed.UsuarioED;
import com.master.rl.JasperRL;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.RequestUtil;
import com.master.util.bd.Transacao;
import com.master.util.FormataValor;

/**
 * @author Ralph
 * @serial Cadastro de Indexadores
 * @serialData 06/2007
 */
public class IndexadorRN extends Transacao {

    public IndexadorRN() {
    }

    public IndexadorED inclui(IndexadorED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new IndexadorBD(this.sql).inclui(ed);
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

    public void altera(IndexadorED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new IndexadorBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void deleta(IndexadorED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new IndexadorBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(IndexadorED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new IndexadorBD(sql).lista(ed);
            return lista;
        } finally {
            this.fimTransacao(false);
        }
    }

    public void lista(IndexadorED ed, HttpServletRequest request, String nmObj) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new IndexadorBD(sql).lista(ed);
            request.setAttribute(nmObj, lista);
        } finally {
            this.fimTransacao(false);
        }
    }

    public IndexadorED getByRecord(IndexadorED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new IndexadorBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public IndexadorED getByData(IndexadorED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new IndexadorBD(this.sql).getByData(ed);
        } finally {
            this.fimTransacao(false);
        }
    }


    public void relatorio(IndexadorED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
        try {
            this.inicioTransacao();
            String nm_Filtro = "";
            ArrayList lista = new IndexadorBD(sql).lista(ed);
			ed.setLista(lista); // Joga a lista de veiculos no ed para enviar pro relatório
			ed.setResponse(response);
			ed.setNomeRelatorio("pns011"); // Seta o nome do relatório
			// Monta a descricao do filtro utilizado
			BancoUtil bu = new BancoUtil();
			if (bu.doValida(ed.getDt_Mes_Indexador()))
				nm_Filtro+=" Mês=" + ed.getDt_Mes_Indexador();
			if (bu.doValida(ed.getDt_Ano_Indexador()))
				nm_Filtro+=" Ano=" + ed.getDt_Ano_Indexador();
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
		IndexadorED ed = (IndexadorED)Obj;
		ed.setRequest(request);
		//if ("1".equals(rel)) {
			this.relatorio(ed, request, response);
		//}

	}

    /**
     * processaOL
     * Processa colicitação de tela OL retornando sempre arquivo XML com a seguinte estrutura.
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
    	IndexadorED ed = (IndexadorED)Obj;
    	//Prepara a saída
    	ed.setMasterDetails(request);

    	PrintWriter out = response.getWriter();
    	out.println("<?xml version='1.0' encoding='ISO-8859-1'?>");
    	if ("I".equals(acao) ) {
    		if (checkDuplo(ed,acao)) {
    			out.println("<ret><item oknok='Indexador já existe para esta data!'/></ret>");
    		} else {
	    		this.inclui(ed);
	    		out.println("<ret><item oknok='IOK' oid='"+ed.getOid_Indexador()+"'/></ret>");
    		}
    	} else
		if ("A".equals(acao)) {
    		if (checkDuplo(ed,acao)) {
    			out.println("<ret><item oknok='Indexador já existe para esta data!'/></ret>");
    		} else {
    			this.altera(ed);
    			out.println("<ret><item oknok='AOK' /></ret>");
    		}
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
			IndexadorED edVolta = new IndexadorED();
			edVolta = (IndexadorED)lst.get(i);
			if ("L".equals(acao)) {
				saida = "<item ";
				saida += "oid_Indexador='" + edVolta.getOid_Indexador() + "' ";
				saida += "dt_Indexador='" + edVolta.getDt_Indexador() + "' ";
				saida += "vl_Indexador='" + FormataValor.formataValorBT(edVolta.getVl_Indexador(), 4) + "' ";
				saida += "msg_Stamp='" + edVolta.getMsg_Stamp() + "' ";
				saida += "/>";
			}else
			if ("CB".equals(acao)) {
				saida = "<item ";
				saida += "value='" + edVolta.getOid_Indexador() + "'>";
				saida +=  edVolta.getDt_Indexador();
				saida += "</item>";
			}
			out.println(saida);
		}
		out.println("</cad>");
		}
    	out.flush();
    	out.close();
    }

    public void processaRequisicao(IndexadorED ed,
			HttpServletRequest request) throws ServletException, IOException,
			Excecoes {
		Object toReturn = new Object();
		// PADRAO PARA TODAS...
		String acao = JavaUtil.getValueDef(request.getParameter("acao"), "M");
		UsuarioED user = (UsuarioED) RequestUtil.getSessionAttribute(request,
				"usuario");
		ed.setOid_Empresa(user.getOid_Empresa());
		ed.setUser(user.getOid_Usuario().intValue());
		ed.setUsuario_Stamp(user.getNm_Usuario());
		ed.setDm_Stamp(acao);
		ed.setTime_millis(System.currentTimeMillis());
		System.out.println(acao);
		// FIM PADRAO!
		if ("I".equals(acao)) {
			this.inclui(ed);
		} else if ("A".equals(acao)) {
			this.altera(ed);
		} else if ("D".equals(acao)) {
			this.deleta(ed);
		} else {
			ArrayList lst = new ArrayList();
			lst = this.lista(ed);
			if ("L".equals(acao)) {
				request.setAttribute("lista", lst);
			} else if ("M".equals(acao) || "S".equals(acao)) {
				request.setAttribute("indexador", (IndexadorED) lst
						.get(0));
			}
		}
	}

    public boolean checkDuplo ( IndexadorED ed, String acao) throws Excecoes {
		IndexadorED edChk = new IndexadorED();
		edChk.setOid_Empresa(ed.getOid_Empresa());
		edChk.setDt_Indexador(ed.getDt_Indexador());
		edChk = this.getByRecord(edChk);
    	if ("I".equals(acao) && edChk.getOid_Indexador() > 0)
    		return true;
    	else
    	if ("A".equals(acao) && edChk.getOid_Indexador() > 0 && edChk.getOid_Indexador() != ed.getOid_Indexador() )
    		return true;
    	else
    		return false;
    }

    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}