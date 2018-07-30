/*
 * Created on 12/11/2004
 *
 */
package com.master.rn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.bd.Modelo_PneuBD;
import com.master.bd.PneuBD;
import com.master.ed.BandaED;
import com.master.ed.Modelo_PneuED;
import com.master.ed.PneuED;
import com.master.ed.UsuarioED;
import com.master.rl.JasperRL;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.RequestUtil;
import com.master.util.bd.Transacao;

/**
 * @author Régis Steigleder
 * @serial DModelos de pneus
 * @serialData 06/2007
 */
public class Modelo_PneuRN extends Transacao {

  public Modelo_PneuED inclui (Modelo_PneuED ed) throws Excecoes {
    inicioTransacao ();
    try {
      Modelo_PneuED toReturn = new Modelo_PneuBD (this.sql).inclui(ed);
      fimTransacao (true);
      return toReturn;
    }
    catch (Excecoes e) {
      abortaTransacao ();
      throw e;
    }
  }

  public void altera (Modelo_PneuED ed) throws Excecoes {
    inicioTransacao ();
    try {
      new Modelo_PneuBD (this.sql).altera (ed);
      fimTransacao (true);
    }
    catch (Excecoes e) {
      abortaTransacao ();
      throw e;
    }
  }

  public void delete (Modelo_PneuED ed) throws Excecoes {
    inicioTransacao ();
    try {
      new Modelo_PneuBD (this.sql).delete (ed);
      fimTransacao (true);
    }
    catch (Excecoes e) {
      abortaTransacao ();
      throw e;
    }
  }

  public ArrayList lista (Modelo_PneuED ed) throws Excecoes {
    inicioTransacao ();
    try {
      return new Modelo_PneuBD (this.sql).lista (ed);
    }
    finally {
      this.fimTransacao (false);
    }

  }

  public Modelo_PneuED getByRecord (Modelo_PneuED ed , int oid) throws Excecoes {
    inicioTransacao ();
    try {
      return new Modelo_PneuBD (this.sql).getByRecord (ed,oid);
    }
    finally {
      fimTransacao (false);
    }
  }

  public Modelo_PneuED getByRecord (int oid) throws Excecoes {
	    inicioTransacao ();
	    try {
	      return new Modelo_PneuBD (this.sql).getByRecord (oid);
	    }
	    finally {
	      fimTransacao (false);
	    }
  }

    public Modelo_PneuED getByRecord (Modelo_PneuED ed ) throws Excecoes {
        inicioTransacao ();
        try {
          return new Modelo_PneuBD (this.sql).getByRecord (ed);
        }
        finally {
          fimTransacao (false);
        }
  }

    public void relatorio(Modelo_PneuED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
        try {
            this.inicioTransacao();
            String nm_Filtro = "";
            ArrayList lista = new Modelo_PneuBD(sql).lista(ed);
			ed.setLista(lista); // Joga a lista de veiculos no ed para enviar pro relatório
			ed.setResponse(response);
			ed.setNomeRelatorio("pns003"); // Seta o nome do relatório
			// Monta a descricao do filtro utilizado
			BancoUtil bu = new BancoUtil();
			if (bu.doValida(ed.getNM_Modelo_Pneu()))
				nm_Filtro+=" Descrição=" + ed.getNM_Modelo_Pneu();
			if (ed.getOid_Fabricante_Pneu()>0)
				nm_Filtro+=" Marca=" + ed.getNm_Fabricante_Pneu();
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
    	Modelo_PneuED ed = (Modelo_PneuED)Obj;
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
  	Modelo_PneuED ed = (Modelo_PneuED)Obj;
  	//Prepara a saída
  	ed.setMasterDetails(request);

  	PrintWriter out = response.getWriter();
  	out.println("<?xml version='1.0' encoding='ISO-8859-1'?>");
	if ("I".equals(acao) ) {
		if (checkDuplo(ed,acao)) {
			out.println("<ret><item oknok='Modelo já existente com esta descricao !'/></ret>");
		} else {
    		ed = this.inclui(ed);
    		out.println("<ret><item oknok='IOK' oid='" + ed.getOid_Modelo_Pneu() + "' /></ret>");
		}
	} else
	if ("A".equals(acao)) {
		if (checkDuplo(ed,acao)) {
			out.println("<ret><item oknok='Modelo já existente com esta descricao !'/></ret>");
		} else {
			this.altera(ed);
			out.println("<ret><item oknok='AOK' /></ret>");
		}
	} else
	if ("D".equals(acao)) {
		if (checkEmUso(ed)) {
			out.println("<ret><item oknok='Impossível excluir! Modelo em uso!' /></ret>");
		} else {
			this.delete(ed);
			out.println("<ret><item oknok='DOK' /></ret>");
		}
	} else {
	out.println("<cad>");
	String saida = null;
	ArrayList lst = new ArrayList();
	lst = this.lista(ed);
	for (int i=0; i<lst.size(); i++){
		Modelo_PneuED edVolta = new Modelo_PneuED();
		edVolta = (Modelo_PneuED)lst.get(i);
		if ("L".equals(acao)) {
			saida = "<item ";
			saida += "oid_Modelo_Pneu='" + edVolta.getOid_Modelo_Pneu() + "' ";
			saida += "oid_Fabricante_Pneu='" + edVolta.getOid_Fabricante_Pneu() + "' ";
			saida += "NM_Modelo_Pneu='" + edVolta.getNM_Modelo_Pneu() + "' ";
			saida += "nm_Fabricante_Pneu='" + edVolta.getNm_Fabricante_Pneu() + "' ";
			saida += "msg_Stamp='" + edVolta.getMsg_Stamp() + "' ";
			saida += "/>";
		} else
			if ("CB".equals(acao) || "CBC".equals(acao)) {
				if ( i==0 && "CBC".equals(acao) ) {
					saida = "<item ";
					saida += "value='0'>TODOS</item>";
					out.println(saida);
				}
			saida = "<item ";
			saida += "value='" + edVolta.getOid_Modelo_Pneu() + "'>";
			saida +=  edVolta.getNM_Modelo_Pneu();
			saida += "</item>";
		}
		out.println(saida);
	}
	out.println("</cad>");
	}
  	out.flush();
  	out.close();
  }

  public void processaRequisicao(Modelo_PneuED ed, HttpServletRequest request)
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
		// FIM PADARO!
		if ("I".equals(acao)) {
			this.inclui(ed);
		} else if ("A".equals(acao)) {
			this.altera(ed);
		} else if ("D".equals(acao)) {
			this.delete(ed);
		} else {
			ArrayList lst = new ArrayList();
			lst = this.lista(ed);
			if ("L".equals(acao)) {
				request.setAttribute("lista", lst);
			} else if ("M".equals(acao) || "S".equals(acao)) {
				request.setAttribute("modelo_pneu", (Modelo_PneuED) lst.get(0));
			}
		}
	}

  public boolean checkDuplo ( Modelo_PneuED ed, String acao) throws Excecoes {
  	boolean ret = false;
  	Modelo_PneuED edChk = new Modelo_PneuED();
		edChk.setOid_Empresa(ed.getOid_Empresa());
		edChk.setNM_Modelo_Pneu(ed.getNM_Modelo_Pneu());
		edChk = this.getByRecord(edChk);
  	if ("I".equals(acao) && edChk.getOid_Modelo_Pneu()>0)
  		ret = true;
  	else
      	if ("A".equals(acao) && edChk.getOid_Modelo_Pneu()>0 ) {
  			if (ed.getOid_Modelo_Pneu()!=edChk.getOid_Modelo_Pneu() )
  				ret = true ;
  	}
  	return ret;
  }

  public boolean checkEmUso ( Modelo_PneuED ed ) throws Excecoes {
		try {
			PneuED pneuED = new PneuED();
			pneuED.setOid_Empresa(ed.getOid_Empresa());
			pneuED.setOid_Modelo_Pneu(ed.getOid_Modelo_Pneu());
			this.inicioTransacao();
			return (new PneuBD(this.sql).lista(pneuED).size()>0 ? true : false);
      } finally {
          this.fimTransacao(false);
      }
  }

  public Modelo_PneuED getByCodigo (String codigo) throws Excecoes {
    inicioTransacao ();
    try {
      return new Modelo_PneuBD (this.sql).getByCodigo (codigo);
    }
    finally {
      fimTransacao (false);
    }
  }
}
