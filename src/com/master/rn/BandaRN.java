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

import com.master.bd.BandaBD;
import com.master.bd.PneuBD;
import com.master.bd.RecapagemBD;
import com.master.bd.Vida_PneuBD;
import com.master.ed.BandaED;
import com.master.ed.Marca_PneuED;
import com.master.ed.PneuED;
import com.master.ed.RecapagemED;
import com.master.ed.UsuarioED;
import com.master.ed.Vida_PneuED;
import com.master.rl.JasperRL;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.FormataValor;
import com.master.util.JavaUtil;
import com.master.util.RequestUtil;
import com.master.util.bd.Transacao;

/**
 * @author Régis Steigleder
 * @serial DModelos de pneus
 * @serialData 06/2007
 */
public class BandaRN
    extends Transacao {

  public BandaED inclui (BandaED ed) throws Excecoes {
    inicioTransacao ();
    try {
      BandaED toReturn = new BandaBD (this.sql).inclui (ed);
      fimTransacao (true);
      return toReturn;
    }
    catch (Excecoes e) {
      abortaTransacao ();
      throw e;
    }
  }

  public void altera (BandaED ed) throws Excecoes {
    inicioTransacao ();
    try {
      new BandaBD (this.sql).altera (ed);
      fimTransacao (true);
    }
    catch (Excecoes e) {
      abortaTransacao ();
      throw e;
    }
  }

  public void delete (BandaED ed) throws Excecoes {
    inicioTransacao ();
    try {
      new BandaBD (this.sql).delete (ed);
      fimTransacao (true);
    }
    catch (Excecoes e) {
      abortaTransacao ();
      throw e;
    }
  }

  public ArrayList lista (BandaED ed) throws Excecoes {
    inicioTransacao ();
    try {
      return new BandaBD (this.sql).lista (ed);
    }
    finally {
      this.fimTransacao (false);
    }

  }

    public BandaED getByRecord (BandaED ed ) throws Excecoes {
        inicioTransacao ();
        try {
          return new BandaBD (this.sql).getByRecord (ed);
        }
        finally {
          fimTransacao (false);
        }
  }

    public void relatorio(BandaED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
        try {
            this.inicioTransacao();
            String nm_Filtro = "";
            ArrayList lista = new BandaBD(sql).lista(ed);
			ed.setLista(lista); // Joga a lista de veiculos no ed para enviar pro relatório
			ed.setResponse(response);
			ed.setNomeRelatorio("pns021"); // Seta o nome do relatório
			// Monta a descricao do filtro utilizado
			BancoUtil bu = new BancoUtil();
			if (bu.doValida(ed.getNm_Banda()))
				nm_Filtro+=" Desenho=" + ed.getNm_Banda();
			if (ed.getOid_Fabricante_Banda()>0)
				nm_Filtro+=" Marca=" + ed.getNm_Fabricante_Banda();
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
    	BandaED ed = (BandaED)Obj;
    	ed.setRequest(request);
	//if ("1".equals(rel)) {
		this.relatorio(ed, request, response);
	//}

    }

    public void processaRequisicao(BandaED ed, HttpServletRequest request)
			throws ServletException, IOException, Excecoes {
		Object toReturn = new Object();
		// PADRAO PARA TODAS...
		String acao = JavaUtil.getValueDef(request.getParameter("acao"), "M");
    	UsuarioED user = (UsuarioED)RequestUtil.getSessionAttribute(request, "usuario");
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
				request.setAttribute("banda", (BandaED) lst.get(0));
			}
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
  	BandaED ed = (BandaED)Obj;
  	//Prepara a saída
  	ed.setMasterDetails(request);

  	PrintWriter out = response.getWriter();
  	out.println("<?xml version='1.0' encoding='ISO-8859-1'?>");
	if ("I".equals(acao) ) {
		if (checkDuplo(ed,acao)) {
			out.println("<ret><item oknok='Banda já existente com esta descricao !'/></ret>");
		} else {
    		ed = this.inclui(ed);
    		out.println("<ret><item oknok='IOK' oid='" + ed.getOid_Banda() + "' /></ret>");
		}
	} else
	if ("A".equals(acao)) {
		if (checkDuplo(ed,acao)) {
			out.println("<ret><item oknok='Banda já existente com esta descricao !'/></ret>");
		} else {
			this.altera(ed);
			out.println("<ret><item oknok='AOK' /></ret>");
		}
	} else
	if ("D".equals(acao)) {
		if (checkEmUso(ed)) {
			out.println("<ret><item oknok='Impossível excluir! Banda em uso!' /></ret>");
		} else {
			this.delete(ed);
			out.println("<ret><item oknok='DOK' /></ret>");
		}
	} else {
	out.println("<cad>");
	if ("CB".equals(acao)) {// Colocado para buscar tambem o registro "ORIGINAL"
		if (ed.getNr_Profundidade()!=9999998) ed.setNr_Profundidade(9999999);
	}
	String saida = null;
	ArrayList lst = new ArrayList();
	lst = this.lista(ed);
	for (int i=0; i<lst.size(); i++){
		BandaED edVolta = new BandaED();
		edVolta = (BandaED)lst.get(i);
		if ("L".equals(acao)) {
			saida = "<item ";
			saida += "oid_Banda='" + edVolta.getOid_Banda() + "' ";
			saida += "oid_Fabricante_Banda='" + edVolta.getOid_Fabricante_Banda() + "' ";
			saida += "nm_Banda='" + edVolta.getNm_Banda() + "' ";
			saida += "nm_Fabricante_Banda='" + edVolta.getNm_Fabricante_Banda() + "' ";
			saida += "nr_Profundidade='" + FormataValor.formataValorBT(edVolta.getNr_Profundidade(),1) + "' ";
			saida += "nr_Largura='" + FormataValor.formataValorBT(edVolta.getNr_Largura(),1) + "' ";
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
			saida += "value='" + edVolta.getOid_Banda() + "' nr_Profundidade='" + FormataValor.formataValorBT(edVolta.getNr_Profundidade(),1) +  "'>";
			if ( "ORIGINAL".equals(edVolta.getNm_Banda()) ) {
				saida +=  edVolta.getNm_Banda().trim() ;
			} else {
				saida +=  edVolta.getNm_Banda().trim() + " - L:" + FormataValor.formataValorBT(edVolta.getNr_Largura(),1) + " mm P:" + FormataValor.formataValorBT(edVolta.getNr_Profundidade(),1) + " mm";
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

  public boolean checkDuplo ( BandaED ed, String acao) throws Excecoes {
  	boolean ret = false;
  	BandaED edChk = new BandaED();
		edChk.setNm_Banda(ed.getNm_Banda());
		edChk.setOid_Empresa(ed.getOid_Empresa());
		edChk = this.getByRecord(edChk);
  	if ("I".equals(acao) && edChk.getOid_Banda()>0)
  		ret = true;
  	else
      	if ("A".equals(acao) && edChk.getOid_Banda()>0 ) {
  			if (ed.getOid_Banda()!=edChk.getOid_Banda() )
  				ret = true ;
  	}
  	return ret;
  }

  public boolean checkEmUso ( BandaED ed ) throws Excecoes {
		try {
			boolean achei=false;
			// Procura na recapagem
			this.inicioTransacao();
			RecapagemED recED = new RecapagemED();
			recED.setOid_Empresa(ed.getOid_Empresa());
			recED.setOid_Banda(ed.getOid_Banda());
			achei=(new RecapagemBD(this.sql).lista(recED).size()>0 ? true : false);
			if (achei == false) { // Só tenta na vida se não encontrou na recapagem
				//Procura na Vida
				Vida_PneuED vidED = new Vida_PneuED();
				vidED.setOid_Empresa(ed.getOid_Empresa());
				vidED.setOid_Banda(ed.getOid_Banda());
				achei=(new Vida_PneuBD(this.sql).lista(vidED).size()>0 ? true : false);
			}
			return (achei);
      } finally {
          this.fimTransacao(false);
      }
  }

}
