package com.master.rn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.bd.Dimensao_PneuBD;
import com.master.bd.Fabricante_PneuBD;
import com.master.bd.Marca_VeiculoBD;
import com.master.bd.Modelo_VeiculoBD;
import com.master.bd.PneuBD;
import com.master.ed.BandaED;
import com.master.ed.Dimensao_PneuED;
import com.master.ed.Fabricante_PneuED;
import com.master.ed.Marca_VeiculoED;
import com.master.ed.Modelo_VeiculoED;
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
 * @author Régis Steigleder
 * @serial Dimensões de pneus
 * @serialData 06/2007
 */
public class Dimensao_PneuRN extends Transacao {

    public Dimensao_PneuRN() {
    }

    public Dimensao_PneuED inclui(Dimensao_PneuED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Dimensao_PneuBD(this.sql).inclui(ed);
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

    public void altera(Dimensao_PneuED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Dimensao_PneuBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public void deleta(Dimensao_PneuED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Dimensao_PneuBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(Dimensao_PneuED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Dimensao_PneuBD(sql).lista(ed);
            return lista;
        } finally {
            this.fimTransacao(false);
        }
    }

    public void lista(Dimensao_PneuED ed, HttpServletRequest request, String nmObj) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Dimensao_PneuBD(sql).lista(ed);
            request.setAttribute(nmObj, lista);
        } finally {
            this.fimTransacao(false);
        }
    }

    public Dimensao_PneuED getByRecord(Dimensao_PneuED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Dimensao_PneuBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public void getByRecord(Dimensao_PneuED ed, HttpServletRequest request, String nmObj) throws Excecoes {

    	try {
    		this.inicioTransacao();
    		Dimensao_PneuED edQBR = new Dimensao_PneuBD(this.sql).getByRecord(ed);
    		request.setAttribute(nmObj, edQBR.getOid_Dimensao_Pneu()== 0 ? null : edQBR);

    	} finally {
    		this.fimTransacao(false);
    	}
    }

    public void relatorio(Dimensao_PneuED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
        try {
            this.inicioTransacao();
            String nm_Filtro = "";
            ArrayList lista = new Dimensao_PneuBD(sql).lista(ed);
			ed.setLista(lista); // Joga a lista de veiculos no ed para enviar pro relatório
			ed.setResponse(response);
			ed.setNomeRelatorio("pns004"); // Seta o nome do relatório
			// Monta a descricao do filtro utilizado
			BancoUtil bu = new BancoUtil();
			if (bu.doValida(ed.getNm_Dimensao_Pneu()))
				nm_Filtro+=" Dimensão Pneu=" + ed.getNm_Dimensao_Pneu();
			ed.setDescFiltro(nm_Filtro);
			new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
        } finally {
            this.fimTransacao(false);
        }
    }


    public String graficoPneusPorDimensao(Dimensao_PneuED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
    	String string = null;
    	string="Distribuição de Pneus por Dimensao";
        try {
            this.inicioTransacao();
    		String nm_Filtro = "";
    		// Monta o filtro e a descricao do filtro utilizado
    		if ("true".equals(ed.getDm_Todos())){
	    		nm_Filtro+="Todos Pneus";
	    	}
    		if ("true".equals(ed.getDm_Sucateados())){
	    		nm_Filtro+="Somente Pneus Sucateados";
	    	}
    		if ("true".equals(ed.getDm_Nao_Sucateados())){
	    		nm_Filtro+="Todos Pneus Não Sucateados";
	    	}
	    	// Cabeçalho do xml do gráfico
	    	string+="<graph  " +
					"bgColor='EAEAEA' "+
	    			"caption='" + nm_Filtro + "' " +
	    			"xAxisName='Dimensão' " +
	    			"yAxisName='Percentual' " +
	    			"decimalPrecision='2' " +
	    			"formatNumberScale='0' " +
	    			"decimalSeparator=',' " +
	    			"numberSuffix='%25' " +
	    			"showNames='1' " +
	    			"rotateNames='1' >" ;
	    	ArrayList lista = new Dimensao_PneuBD(sql).listaPneuPorDimensao(ed,"gra");
            for (int i=0; i<lista.size(); i++){
				Dimensao_PneuED edVolta = new Dimensao_PneuED();
				edVolta = (Dimensao_PneuED)lista.get(i);
		    	string+="<set " +
		    			"name='" + edVolta.getNm_Dimensao_Pneu().trim() +  "' " +
		    			"value='" + edVolta.getVl_Dimensao_Pneu() + "' " +
    					"color='"+new Utilitaria().getCorGrafico(i)+"'/>";
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
    Dimensao_PneuED ed = (Dimensao_PneuED)Obj;

	//if ("1".equals(rel)) {
		this.relatorio(ed, request, response);
	//}

}

	public String processaGR(String acao, Object Obj, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Excecoes {
		//Extrai o bean com os campos da request colados
		Dimensao_PneuED ed = (Dimensao_PneuED)Obj;
		return this.graficoPneusPorDimensao(ed, request, response);
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
    	Dimensao_PneuED ed = (Dimensao_PneuED)Obj;
    	//Prepara a saída
    	ed.setMasterDetails(request);

    	PrintWriter out = response.getWriter();
    	out.println("<?xml version='1.0' encoding='ISO-8859-1'?>");
    	if ("I".equals(acao) ) {
    		if (checkDuplo(ed,acao)) {
    			out.println("<ret><item oknok='Dimensão já existente com esta descricao !'/></ret>");
    		} else {
	    		ed = this.inclui(ed);
	    		out.println("<ret><item oknok='IOK' oid='" + ed.getOid_Dimensao_Pneu() + "' /></ret>");
    		}
    	} else
		if ("A".equals(acao)) {
    		if (checkDuplo(ed,acao)) {
    			out.println("<ret><item oknok='Dimensão já existente com esta descricao !'/></ret>");
    		} else {
				this.altera(ed);
				out.println("<ret><item oknok='AOK' /></ret>");
    		}
		} else
		if ("D".equals(acao)) {
			if (checkEmUso(ed)) {
				out.println("<ret><item oknok='Impossível excluir! Dimensão em uso!' /></ret>");
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
			Dimensao_PneuED edVolta = new Dimensao_PneuED();
			edVolta = (Dimensao_PneuED)lst.get(i);
			if ("L".equals(acao)) {
				saida = "<item ";
				saida += "oid_Dimensao_Pneu='" + edVolta.getOid_Dimensao_Pneu() + "' ";
				saida += "nm_Dimensao_Pneu='" + edVolta.getNm_Dimensao_Pneu() + "' ";
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
				saida += "value='" + edVolta.getOid_Dimensao_Pneu() + "'>";
				saida +=  edVolta.getNm_Dimensao_Pneu();
				saida += "</item>";
			}
			out.println(saida);
		}
		out.println("</cad>");
		}
    	out.flush();
    	out.close();
    }

    public void processaRequisicao(Dimensao_PneuED ed, HttpServletRequest request)
			throws ServletException, IOException, Excecoes {
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
				request.setAttribute("dimensao_pneu", (Dimensao_PneuED) lst.get(0));
			}
		}
	}

    public boolean checkDuplo ( Dimensao_PneuED ed, String acao) throws Excecoes {
    	boolean ret = false;
    	Dimensao_PneuED edChk = new Dimensao_PneuED();
		edChk.setOid_Empresa(ed.getOid_Empresa());
		edChk.setNm_Dimensao_Pneu(ed.getNm_Dimensao_Pneu());
		edChk = this.getByRecord(edChk);
    	if ("I".equals(acao) && edChk.getOid_Dimensao_Pneu()>0)
    		ret = true;
    	else
        	if ("A".equals(acao) && edChk.getOid_Dimensao_Pneu()>0 ) {
    			if (ed.getOid_Dimensao_Pneu()!=edChk.getOid_Dimensao_Pneu() )
    				ret = true ;
    	}
    	return ret;
    }

    public boolean checkEmUso ( Dimensao_PneuED ed ) throws Excecoes {
		try {
			PneuED pneuED = new PneuED();
			pneuED.setOid_Empresa(ed.getOid_Empresa());
			pneuED.setOid_Dimensao_Pneu(ed.getOid_Dimensao_Pneu());
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