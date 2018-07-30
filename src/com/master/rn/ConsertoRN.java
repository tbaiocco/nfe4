package com.master.rn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.bd.ConsertoBD;
import com.master.ed.BandaED;
import com.master.ed.ConsertoED;
import com.master.ed.UsuarioED;
import com.master.rl.JasperRL;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.FormataValor;
import com.master.util.JavaUtil;
import com.master.util.RequestUtil;
import com.master.util.bd.Transacao;

/**
 * @author Cristian Vianna Garcia
 * @serial Consertos/Resulcagens
 * @serialData 08/2007
 */
public class ConsertoRN extends Transacao {

    public ConsertoRN() {
    }

    public ConsertoED inclui(ConsertoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new ConsertoBD(this.sql).inclui(ed);
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

    public void altera(ConsertoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new ConsertoBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void deleta(ConsertoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new ConsertoBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(ConsertoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new ConsertoBD(sql).lista(ed);
            return lista;
        } finally {
            this.fimTransacao(false);
        }
    }

	public ArrayList listaConsertosPorVida(ConsertoED ed) throws Excecoes {
		try {
			inicioTransacao();
			ArrayList listaConsertosPorVida = new ConsertoBD(sql).listaConsertosPorVida(ed);
			return listaConsertosPorVida;
		} finally {
			fimTransacao(false);
		}

	}

    public void lista(ConsertoED ed, HttpServletRequest request, String nmObj) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new ConsertoBD(sql).lista(ed);
            request.setAttribute(nmObj, lista);
        } finally {
            this.fimTransacao(false);
        }
    }

    public ConsertoED getByRecord(ConsertoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new ConsertoBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public void getByRecord(ConsertoED ed, HttpServletRequest request, String nmObj) throws Excecoes {

    	try {
    		this.inicioTransacao();
    		ConsertoED edQBR = new ConsertoBD(this.sql).getByRecord(ed);
    		request.setAttribute(nmObj, edQBR.getOid_Conserto()== 0 ? null : edQBR);

    	} finally {
    		this.fimTransacao(false);
    	}
    }

    public void relatorio(ConsertoED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
        try {
            this.inicioTransacao();
            String nm_Filtro = "";
            ArrayList lista = new ConsertoBD(sql).lista(ed);
			ed.setLista(lista); // Joga a lista de veiculos no ed para enviar pro relatório
			ed.setResponse(response);
			if ("1".equals(ed.getDm_Opcao())){
				ed.setNomeRelatorio("pns019");
			}
			if ("2".equals(ed.getDm_Opcao())){
				ed.setNomeRelatorio("pns019R");
			}
			// Monta a descricao do filtro utilizado
			BancoUtil bu = new BancoUtil();
			if (bu.doValida(ed.getNr_Frota_Conserto()))
				nm_Filtro+=" Veiculo=" + ed.getNr_Frota_Conserto();
			if (bu.doValida(ed.getNr_Fogo_Conserto()))
				nm_Filtro+=" Pneu=" + ed.getNr_Fogo_Conserto();
			if (bu.doValida(ed.getNm_Vida_Conserto()))
				nm_Filtro+=" Vida=" + ed.getNm_Vida_Conserto();
			if (bu.doValida(ed.getDt_Inicial_Conserto()))
				nm_Filtro+=" Data Inicial=" + ed.getDt_Inicial_Conserto();
			if (bu.doValida(ed.getDt_Final_Conserto()))
				nm_Filtro+=" Data Final=" + ed.getDt_Final_Conserto();
			ed.setDescFiltro(nm_Filtro);
			new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
        } finally {
            this.fimTransacao(false);
        }
    }


    public void relatorioConsertosPorVida(ConsertoED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
        try {
            this.inicioTransacao();
            String nm_Filtro = "";
            ArrayList lista = new ConsertoBD(sql).listaConsertosPorVida(ed);
			ed.setLista(lista); // Joga a lista de veiculos no ed para enviar pro relatório
			ed.setResponse(response);
			ed.setNomeRelatorio("pns322");
			// Monta a descricao do filtro utilizado
			BancoUtil bu = new BancoUtil();
			if (ed.getOid_Dimensao_Pneu() > 0)
				nm_Filtro+=" Dimensão=" + ed.getNm_Dimensao_Pneu();
			if (ed.getOid_Fabricante_Pneu() > 0)
				nm_Filtro+=" Marca=" + ed.getNm_Fabricante_Pneu();
			if (ed.getOid_Modelo_Pneu() > 0)
				nm_Filtro+=" Modelo=" + ed.getNm_Modelo_Pneu();
			if (bu.doValida(ed.getDt_Inicial_Conserto()))
				nm_Filtro+=" Data Inicial=" + ed.getDt_Inicial_Conserto();
			if (bu.doValida(ed.getDt_Final_Conserto()))
				nm_Filtro+=" Data Final=" + ed.getDt_Final_Conserto();
			HashMap map = new HashMap();
			map.put("dm_Fabricante_Pneu", (ed.getDm_Fabricante_Pneu()));
			map.put("dm_Dimensao_Pneu", (ed.getDm_Dimensao_Pneu()));
			map.put("dm_Modelo_Pneu", (ed.getDm_Modelo_Pneu()));
			ed.setHashMap(map);
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
		ConsertoED ed = (ConsertoED)Obj;
		ed.setRequest(request);

		if ("1".equals(rel)) {
			this.relatorio(ed, request, response);
		}
		if ("2".equals(rel)) {
			this.relatorioConsertosPorVida(ed, request, response);
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
    	ConsertoED ed = (ConsertoED)Obj;
    	//Prepara a saída
    	ed.setMasterDetails(request);

    	PrintWriter out = response.getWriter();
    	out.println("<?xml version='1.0' encoding='ISO-8859-1'?>");
    	if ("I".equals(acao) ) {
    		//if (checkDuplo(ed,acao)) {
    			//out.println("<ret><item oknok='Conserto já existente com esta descricao !'/></ret>");
    		//} else {
	    		ed = this.inclui(ed);
	    		out.println("<ret><item oknok='IOK' oid='" + ed.getOid_Conserto() + "' /></ret>");
    		//}
    	} else
		if ("A".equals(acao)) {
    		//if (checkDuplo(ed,acao)) {
    			//out.println("<ret><item oknok='Conserto já existente com esta descricao !'/></ret>");
    		//} else {
				this.altera(ed);
				out.println("<ret><item oknok='AOK' /></ret>");
    		//}
		} else
		if ("D".equals(acao)) {
			//if (checkEmUso(ed)) {
				//out.println("<ret><item oknok='Impossível excluir! Conserto em uso!' /></ret>");
			//} else {
				this.deleta(ed);
				out.println("<ret><item oknok='DOK' /></ret>");
			//}
		} else {
		out.println("<cad>");
		String saida=null;
		ArrayList lst = new ArrayList();
		if ("L".equals(acao)) { // Lista de conserrtos
			lst = this.lista(ed);
		}
		if ("LCV".equals(acao)) { // Lista de conserto por vida
			lst = this.listaConsertosPorVida(ed);
		}
		for (int i=0; i<lst.size(); i++){
			ConsertoED edVolta = new ConsertoED();
			edVolta = (ConsertoED)lst.get(i);
			if ("L".equals(acao) || "LCV".equals(acao)) {
				saida = "<item ";
				saida += "oid_Empresa='" + edVolta.getOid_Empresa() + "' ";
				saida += "oid_Conserto='" + edVolta.getOid_Conserto() + "' ";
				saida += "oid_Pneu='" + edVolta.getOid_Pneu_Conserto() + "' ";
				saida += "oid_Veiculo='" + edVolta.getOid_Veiculo() + "' ";
				saida += "nr_Frota_Conserto='" + edVolta.getNr_Frota_Conserto() + "' ";
				saida += "nr_Fogo_Conserto='" + edVolta.getNr_Fogo_Conserto() + "' ";
				saida += "dt_Conserto='" + edVolta.getDt_Conserto() + "' ";
				saida += "vl_Conserto='" + FormataValor.formataValorBT(edVolta.getVl_Conserto(),2) + "' ";
				saida += "dm_Vida_Conserto='" + edVolta.getDm_Vida_Conserto() + "' ";
				saida += "nr_Documento_Conserto='" + edVolta.getNr_Documento_Conserto() + "' ";
				saida += "oid_Fornecedor_Conserto='" + edVolta.getOid_Fornecedor_Conserto() + "' ";
				if (JavaUtil.doValida(edVolta.getTx_Descricao_Conserto())) {
					saida += "tx_Descricao_Conserto='" + edVolta.getTx_Descricao_Conserto() + "' ";
				}else{
					saida += "tx_Descricao_Conserto='" + "" + "' ";
				}
				saida += "vl_Prof_Sulco_Resulcagem='" + FormataValor.formataValorBT(edVolta.getVl_Prof_Sulco_Resulcagem(),2) + "' ";
				saida += "nm_Vida_Conserto='" + edVolta.getNm_Vida_Conserto() + "' ";
				saida += "tx_Descricao_Resulcagem='" + edVolta.getTx_Descricao_Resulcagem() + "' ";
				saida += "nm_Fornecedor='" + edVolta.getNm_Fornecedor() + "' ";
				saida += "nm_Fabricante_Pneu='" + edVolta.getNm_Fabricante_Pneu() + "' ";
				saida += "nm_Dimensao_Pneu='" + edVolta.getNm_Dimensao_Pneu() + "' ";
				saida += "nm_Modelo_Pneu='" + edVolta.getNm_Modelo_Pneu() + "' ";
				saida += "nr_Consertos='" + edVolta.getNr_Consertos() + "' ";
				saida += "nr_Vida='" + edVolta.getNr_Vida()+ "' ";
				saida += "tx_Situacao='" + edVolta.getTx_Situacao() + "' ";
				saida += "msg_Stamp='" + edVolta.getMsg_Stamp() + "' ";
				saida += "/>";
			}
			out.println(saida);
		}
		out.println("</cad>");
		}
    	out.flush();
    	out.close();
    }

    public void processaRequisicao(ConsertoED ed, HttpServletRequest request)
	throws ServletException, IOException, Excecoes {

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
		if ("I".equals(acao)) {
			ed = this.inclui(ed);
		} else if ("A".equals(acao)) {
			this.altera(ed);
		} else if ("D".equals(acao)) {
			this.deleta(ed);
		} else {
			ArrayList lst = new ArrayList();
			lst = this.lista(ed);
			if ("LCV".equals(acao)) { // Lista de conserto por vida
				lst = this.listaConsertosPorVida(ed);
			}
			if ("L".equals(acao) || "LCV".equals(acao)) {
				request.setAttribute("lista", lst);
			} else if ("M".equals(acao) || "S".equals(acao)) {
				request.setAttribute("conserto", (ConsertoED) lst.get(0));
			}

		}
    }

    public void processaRelatorio(String rel, ConsertoED ed, HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException, Excecoes {

    	UsuarioED user = (UsuarioED) RequestUtil.getSessionAttribute(request,
				"usuario");
		ed.setOid_Empresa(user.getOid_Empresa());
		ed.setUser(user.getOid_Usuario().intValue());
		ed.setUsuario_Stamp(user.getNm_Usuario());
		ed.setRequest(request);

		if ("1".equals(rel)) {
			this.relatorio(ed, request, response);
		}
		if ("2".equals(rel)) {
			this.relatorioConsertosPorVida(ed, request, response);
		}
	}

    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}