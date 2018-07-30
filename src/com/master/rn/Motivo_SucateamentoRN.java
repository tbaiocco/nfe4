package com.master.rn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.bd.Cotacao_PneuBD;
import com.master.bd.Dimensao_PneuBD;
import com.master.bd.Fabricante_PneuBD;
import com.master.bd.Motivo_SucateamentoBD;
import com.master.bd.PneuBD;
import com.master.ed.Cotacao_PneuED;
import com.master.ed.Dimensao_PneuED;
import com.master.ed.Fabricante_PneuED;
import com.master.ed.IndexadorED;
import com.master.ed.Motivo_SucateamentoED;
import com.master.ed.PneuED;
import com.master.ed.UsuarioED;
import com.master.rl.JasperRL;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.RequestUtil;
import com.master.util.Utilitaria;
import com.master.util.bd.Transacao;

/**
 * @author Ralph
 * @serial Motivos de Sucateamento
 * @serialData 06/2007
 */
public class Motivo_SucateamentoRN extends Transacao {

    public Motivo_SucateamentoRN() {
    }

    public Motivo_SucateamentoED inclui(Motivo_SucateamentoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Motivo_SucateamentoBD(this.sql).inclui(ed);
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

    public void altera(Motivo_SucateamentoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Motivo_SucateamentoBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void deleta(Motivo_SucateamentoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Motivo_SucateamentoBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(Motivo_SucateamentoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Motivo_SucateamentoBD(sql).lista(ed);
            return lista;
        } finally {
            this.fimTransacao(false);
        }
    }

    public void lista(Motivo_SucateamentoED ed, HttpServletRequest request, String nmObj) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Motivo_SucateamentoBD(sql).lista(ed);
            request.setAttribute(nmObj, lista);
        } finally {
            this.fimTransacao(false);
        }
    }

    public Motivo_SucateamentoED getByRecord(Motivo_SucateamentoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Motivo_SucateamentoBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public void getByRecord(Motivo_SucateamentoED ed, HttpServletRequest request, String nmObj) throws Excecoes {

    	try {
    		this.inicioTransacao();
    		Motivo_SucateamentoED edQBR = new Motivo_SucateamentoBD(this.sql).getByRecord(ed);
    		request.setAttribute(nmObj, edQBR.getOid_Motivo_Sucateamento()== 0 ? null : edQBR);

    	} finally {
    		this.fimTransacao(false);
    	}
    }

    public void relatorio(Motivo_SucateamentoED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
        try {
            this.inicioTransacao();
            String nm_Filtro = "";
            ArrayList lista = new Motivo_SucateamentoBD(sql).lista(ed);
			ed.setLista(lista); // Joga a lista de veiculos no ed para enviar pro relatório
			ed.setResponse(response);
			ed.setNomeRelatorio("pns010"); // Seta o nome do relatório
			// Monta a descricao do filtro utilizado
			BancoUtil bu = new BancoUtil();
			if (bu.doValida(ed.getNm_Motivo_Sucateamento()))
				nm_Filtro+=" Motivo Sucateamento=" + ed.getNm_Motivo_Sucateamento();
			ed.setDescFiltro(nm_Filtro);
			new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
        } finally {
            this.fimTransacao(false);
        }
    }

    public String graficoMotivoSucateamento(Motivo_SucateamentoED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
    	String string = null;
    	string="Motivos de Sucateamento";
        try {
            this.inicioTransacao();
    		String nm_Filtro = "";
    		// Monta o filtro e a descricao do filtro utilizado
	    	if (ed.getOid_Fabricante_Pneu()>0 ) {
	    		nm_Filtro+=" Fabricante=" + ed.getNm_Fabricante_Pneu();
	    	}
	    	// Cabeçalho do xml do gráfico
	    	string+="<graph  " +
					"bgColor='EAEAEA' "+
	    			"caption='" + nm_Filtro + "' " +
	    			"xAxisName='Marca' " +
	    			"yAxisName='Percentual' " +
	    			"decimalPrecision='2' " +
	    			"formatNumberScale='0' " +
	    			"decimalSeparator=',' " +
	    			"numberSuffix='%25' " +
	    			"showNames='1' " +
	    			"rotateNames='1' >" ;
	    	ArrayList lista = new Motivo_SucateamentoBD(sql).listaMotivoSucateamento(ed,"gra");
            for (int i=0; i<lista.size(); i++){
				Motivo_SucateamentoED edVolta = new Motivo_SucateamentoED();
				edVolta = (Motivo_SucateamentoED)lista.get(i);
		    	string+="<set " +
		    			"name='" + edVolta.getNm_Motivo_Sucateamento().trim() +  "' " +
		    			"value='" + edVolta.getVl_Motivo_Sucateamento() + "' " +
    					"color='"+new Utilitaria().getCorAleatoria()+"'/>";
            }
            string+="</graph>";
        } finally {
            this.fimTransacao(false);
        }

    	return string;
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
    Motivo_SucateamentoED ed = (Motivo_SucateamentoED)Obj;
    ed.setRequest(request);
	//if ("1".equals(rel)) {
		this.relatorio(ed, request, response);
	//}

}

	public String processaGR(String acao, Object Obj, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Excecoes {
		//Extrai o bean com os campos da request colados
		Motivo_SucateamentoED ed = (Motivo_SucateamentoED)Obj;
		UsuarioED user = (UsuarioED) RequestUtil.getSessionAttribute(request,"usuario");
		ed.setOid_Empresa(user.getOid_Empresa());
		ed.setUser(user.getOid_Usuario().intValue());
		ed.setUsuario_Stamp(user.getNm_Usuario());
		return this.graficoMotivoSucateamento(ed, request, response);
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
    	Motivo_SucateamentoED ed = (Motivo_SucateamentoED)Obj;
    	//Prepara a saída

    	PrintWriter out = response.getWriter();
    	out.println("<?xml version='1.0' encoding='ISO-8859-1'?>");
    	if ("I".equals(acao) ) {
    		if (checkDuplo(ed,acao)) {
    			out.println("<ret><item oknok='Motivo de Sucateamento já existente com esta descricao !'/></ret>");
    		} else {
	    		ed = this.inclui(ed);
	    		out.println("<ret><item oknok='IOK' oid='" + ed.getOid_Motivo_Sucateamento() + "' /></ret>");
    		}
    	} else
		if ("A".equals(acao)) {
    		if (checkDuplo(ed,acao)) {
    			out.println("<ret><item oknok='Motivo de Sucateamento já existente com esta descricao !'/></ret>");
    		} else {
				this.altera(ed);
				out.println("<ret><item oknok='AOK' /></ret>");
    		}
		} else
		if ("D".equals(acao)) {
			if (checkEmUso(ed)) {
				out.println("<ret><item oknok='Impossível excluir! Motivo de Sucateamento em uso!' /></ret>");
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
			Motivo_SucateamentoED edVolta = new Motivo_SucateamentoED();
			edVolta = (Motivo_SucateamentoED)lst.get(i);
			if ("L".equals(acao)) {
				saida = "<item ";
				saida += "oid_Motivo_Sucateamento='" + edVolta.getOid_Motivo_Sucateamento() + "' ";
				saida += "nm_Motivo_Sucateamento='" + edVolta.getNm_Motivo_Sucateamento() + "' ";
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
				saida += "value='" + edVolta.getOid_Motivo_Sucateamento() + "'>";
				saida +=  edVolta.getNm_Motivo_Sucateamento();
				saida += "</item>";
			}
			out.println(saida);
		}
		out.println("</cad>");
		}
    	out.flush();
    	out.close();
    }

    public void processaRequisicao(Motivo_SucateamentoED ed,
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
				request.setAttribute("motivo_sucateamento", (Motivo_SucateamentoED) lst
						.get(0));
			}
		}
	}

    public boolean checkDuplo ( Motivo_SucateamentoED ed, String acao) throws Excecoes {
    	boolean ret = false;
    	Motivo_SucateamentoED edChk = new Motivo_SucateamentoED();
		edChk.setOid_Empresa(ed.getOid_Empresa());
		edChk.setNm_Motivo_Sucateamento(ed.getNm_Motivo_Sucateamento());
		edChk = this.getByRecord(edChk);
    	if ("I".equals(acao) && edChk.getOid_Motivo_Sucateamento()>0)
    		ret = true;
    	else
        	if ("A".equals(acao) && edChk.getOid_Motivo_Sucateamento()>0 ) {
    			if (ed.getOid_Motivo_Sucateamento()!=edChk.getOid_Motivo_Sucateamento() )
    				ret = true ;
    	}
    	return ret;
    }

    public boolean checkEmUso (Motivo_SucateamentoED ed) throws Excecoes {
		try {
			PneuED pneuED = new PneuED();
			pneuED.setOid_Empresa(ed.getOid_Empresa());
			pneuED.setOid_Motivo_Sucateamento(ed.getOid_Motivo_Sucateamento());
			this.inicioTransacao();
			return (new PneuBD(this.sql).lista(pneuED).size()>0 ? true : false);
        } finally {
            this.fimTransacao(false);
        }
    }

    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}