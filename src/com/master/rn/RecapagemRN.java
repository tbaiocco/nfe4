/*
 * Created on 12/11/2004
 *
 */
package com.master.rn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.bd.PneuBD;
import com.master.bd.RecapagemBD;
import com.master.ed.ConsertoED;
import com.master.ed.PneuED;
import com.master.ed.RecapagemED;
import com.master.ed.UsuarioED;
import com.master.rl.JasperRL;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.FormataValor;
import com.master.util.JavaUtil;
import com.master.util.RequestUtil;
import com.master.util.bd.Transacao;
import com.master.util.ed.Parametro_FixoED;

/**
 * @author Régis Steigleder
 * @serial Recapagens
 * @serialData 06/2007
 * MODIFICADO
 * @author Ralph Renato
 * @serial consulta recapagens efetuadas
 * @serialData 11/2007
 */
public class RecapagemRN
    extends Transacao {

  public RecapagemED inclui (RecapagemED ed) throws Excecoes {
    inicioTransacao ();
    try {
      RecapagemED toReturn = new RecapagemBD (this.sql).inclui (ed);
      fimTransacao (true);
      return toReturn;
    }
    catch (Excecoes e) {
      abortaTransacao ();
      throw e;
    }
  }

  public void altera (RecapagemED ed) throws Excecoes {
    inicioTransacao ();
    try {
      new RecapagemBD (this.sql).altera (ed);
      fimTransacao (true);
    }
    catch (Excecoes e) {
      abortaTransacao ();
      throw e;
    }
  }

  public void delete (RecapagemED ed) throws Excecoes {
    inicioTransacao ();
    try {
      new RecapagemBD (this.sql).delete (ed);
      fimTransacao (true);
    }
    catch (Excecoes e) {
      abortaTransacao ();
      throw e;
    }
  }

  public ArrayList lista (RecapagemED ed) throws Excecoes {
    inicioTransacao ();
    try {
      return new RecapagemBD (this.sql).lista (ed);
    }
    finally {
      this.fimTransacao (false);
    }

  }

  public RecapagemED getByRecord (RecapagemED ed ) throws Excecoes {
	  inicioTransacao ();
	  try {
		  return new RecapagemBD (this.sql).getByRecord (ed);
	  }
	  finally {
		  fimTransacao (false);
	  }
  }


  public RecapagemED getLastRecord (RecapagemED ed ) throws Excecoes {
	  inicioTransacao ();
	  try {
		  return new RecapagemBD (this.sql).getLastRecord (ed);
	  }
	  finally {
		  fimTransacao (false);
	  }
  }

  /**
   *
   * @param ed
   * @param request
   * @param response
   * @throws Excecoes
   */
    public void relatorio(RecapagemED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
        try {
            this.inicioTransacao();
            String nm_Filtro = "";
            ArrayList lista = new RecapagemBD(sql).lista(ed);
			ed.setLista(lista); // Joga a lista de veiculos no ed para enviar pro relatório
			ed.setResponse(response);
			ed.setNomeRelatorio("pns017"); // Seta o nome do relatório
			// Monta a descricao do filtro utilizado
			BancoUtil bu = new BancoUtil();
			if (bu.doValida(ed.getNm_Fabricante_Banda()))
				nm_Filtro+=" Descrição=" + ed.getNm_Fabricante_Banda();
			ed.setDescFiltro(nm_Filtro);
			new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
        } finally {
            this.fimTransacao(false);
        }
    }

    public void relatorioConsulta_Recapagens_Efetuadas(RecapagemED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
        try {
            this.inicioTransacao();
            String nm_Filtro = "";
            ArrayList lst = new RecapagemBD(sql).listaRecapagensEfetuadas(ed);
			RecapagemED voltaED = (RecapagemED)lst.get(lst.size()-1); // Coloca no último registro a lista para fazer o resumo
			voltaED.setSublista(lst);
			ed.setLista(lst); // Joga a lista de veiculos no ed para enviar pro relatório
			ed.setResponse(response);
			ed.setNomeRelatorio("pns319"); // Seta o nome do relatório
			// Monta a descricao do filtro utilizado
			BancoUtil bu = new BancoUtil();
        	// Monta a descricao do filtro utilizado
			if (bu.doValida(ed.getNr_Fogo()))
				nm_Filtro+="Pneu:" + ed.getNr_Fogo();
			if (bu.doValida(ed.getNm_Dimensao_Pneu()) )
				nm_Filtro+= "Dimensão: " + ed.getNm_Dimensao_Pneu() + " ";
			if (bu.doValida(ed.getNm_Fabricante_Banda()) )
				nm_Filtro+= "Borracha: " + ed.getNm_Fabricante_Banda() + " ";
			if (bu.doValida(ed.getNm_Banda()) )
				nm_Filtro+= "Desenho: " + ed.getNm_Banda() + " ";
			if (bu.doValida(ed.getNm_Fornecedor_Recapagem()) )
				nm_Filtro+= "Fornecedor: " + ed.getNm_Fornecedor_Recapagem() + " ";
			if (bu.doValida(ed.getDt_Recapagem_Inicial()) || bu.doValida(ed.getDt_Recapagem_Final())) {
    			nm_Filtro+= "Período de: " +
    						(bu.doValida(ed.getDt_Recapagem_Inicial())? ed.getDt_Recapagem_Inicial():"início" ) +
    						" até: " +
    						(bu.doValida(ed.getDt_Recapagem_Final())? ed.getDt_Recapagem_Final():"fim" );
    		}
			ed.setDescFiltro(nm_Filtro);
    		HashMap map = new HashMap();
            map.put("PATH_SUBLIST", Parametro_FixoED.getInstancia().getPATH_RELATORIOS());
    		ed.setHashMap(map);
			new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
        } finally {
            this.fimTransacao(false);
        }
    }

    public ArrayList listaRecapagensEfetuadas (RecapagemED ed) throws Excecoes {
    	try {
    		this.inicioTransacao();
    		ArrayList lista = new ArrayList(); // Array final que será enviado para o relatório/OL
				RecapagemED recED = new RecapagemED ();
				recED.setOid_Empresa(ed.getOid_Empresa());
				recED.setNr_Fogo(ed.getNr_Fogo());
				recED.setOid_Dimensao_Pneu(ed.getOid_Dimensao_Pneu());
				recED.setOid_Fabricante_Banda(ed.getOid_Fabricante_Banda());
				recED.setOid_Banda(ed.getOid_Banda());
				recED.setDt_Recapagem_Inicial(ed.getDt_Recapagem_Inicial());
				recED.setDt_Recapagem_Final(ed.getDt_Recapagem_Final());
				recED.setOid_Fornecedor_Recapagem(ed.getOid_Fornecedor_Recapagem());
				ArrayList lstRecapagemEfetuada  = new RecapagemBD(this.sql).listaRecapagensEfetuadas(recED);
				for (int x=0; x<lstRecapagemEfetuada.size(); x++){ // Gira no array de pneus por marca encontrados
					RecapagemED recapeRetorno = new RecapagemED ();
					recapeRetorno = (RecapagemED)lstRecapagemEfetuada.get(x);
					lista.add(recapeRetorno);
				}
    		return lista;
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
	    	RecapagemED ed = (RecapagemED)Obj;

		if ("1".equals(rel)) {
			this.relatorio(ed, request, response);
		}
		if ("2".equals(rel)) {
			this.relatorioConsulta_Recapagens_Efetuadas(ed, request, response);
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
  	RecapagemED ed = (RecapagemED)Obj;
  	//Prepara a saída

  	PrintWriter out = response.getWriter();
  	out.println("<?xml version='1.0' encoding='ISO-8859-1'?>");
	if ("I".equals(acao) ) {
		if (checkDuplo(ed,acao)) {
			out.println("<ret><item oknok='Recapagem já existente com esta data !'/></ret>");
		} else {
    		ed = this.inclui(ed);
    		out.println("<ret><item oknok='IOK' oid='" + ed.getOid_Recapagem() + "' /></ret>");
		}
	} else
	if ("A".equals(acao)) {
		//if (checkDuplo(ed,acao)) {
		//	out.println("<ret><item oknok='Recapagem já existente com esta data !'/></ret>");
		//} else {
			this.altera(ed);
			out.println("<ret><item oknok='AOK' /></ret>");
		//}
	} else
	if ("D".equals(acao)) {
		if (checkEmUso(ed)) {
			out.println("<ret><item oknok='Impossível excluir! ' /></ret>");
		} else {
			this.delete(ed);
			out.println("<ret><item oknok='DOK' /></ret>");
		}
	} else {
	out.println("<cad>");
	String saida = null;
	ArrayList lst = new ArrayList();
	lst = this.lista(ed);
	if ("L".equals(acao)) { // Lista
		lst = this.lista(ed);
	}
	if ("LRE".equals(acao)) { // Lista de Recapagens Efetuadas
		lst = this.listaRecapagensEfetuadas(ed);
	}
	for (int i=0; i<lst.size(); i++){
		RecapagemED edVolta = new RecapagemED();
		edVolta = (RecapagemED)lst.get(i);
		if ("L".equals(acao) || "LRE".equals(acao)) {
			saida = montaRegistro(edVolta);
		} else
		if ("CB".equals(acao)) {
			saida = "<item ";
			saida += "value='" + edVolta.getOid_Recapagem() + "'>";
			saida +=  edVolta.getDt_Recapagem() + " " + edVolta.getNm_Banda();
			saida += "</item>";
		}
		out.println(saida);
	}
	out.println("</cad>");
	}
  	out.flush();
  	out.close();
  }

  public void processaRequisicao(RecapagemED ed, HttpServletRequest request)
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
			// if (checkDuplo(ed,acao)) {
			//
			// } else {
			ed = this.inclui(ed);
			// }
		} else if ("A".equals(acao)) {
			this.altera(ed);
		} else if ("D".equals(acao)) {
			// if (checkEmUso(ed)) {
			//
			// } else {
			this.delete(ed);
			// }
		} else {
			ArrayList lst = new ArrayList();
			lst = this.lista(ed);

			if ("LRE".equals(acao)) { // Lista de Recapagens Efetuadas
				lst = this.listaRecapagensEfetuadas(ed);
			}
			if ("L".equals(acao) || "LRE".equals(acao)) {
				request.setAttribute("lista", lst);
			} else if ("M".equals(acao) || "S".equals(acao)) {
				request.setAttribute("recapagem", (RecapagemED) lst.get(0));
			}
		}
	}

  public void processaRelatorio(String rel, RecapagemED ed, HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException, Excecoes {

	  UsuarioED user = (UsuarioED) RequestUtil.getSessionAttribute(request,
				"usuario");
	  ed.setOid_Empresa(user.getOid_Empresa());
	  ed.setUser(user.getOid_Usuario().intValue());
	  ed.setUsuario_Stamp(user.getNm_Usuario());
	  ed.setRequest(request);
	  ed.setOid_Fornecedor_Recapagem(ed.getOid_Fornecedor());
	  ed.setNr_Cnpj_Cpf_Recapagem(ed.getNr_Cnpj_Cpf());
	  ed.setNm_Fornecedor_Recapagem(ed.getNm_Fornecedor());

	if ("1".equals(rel)) {
		this.relatorio(ed, request, response);
	}
	if ("2".equals(rel)) {
		this.relatorioConsulta_Recapagens_Efetuadas(ed, request, response);
	}

}

  private String montaRegistro(RecapagemED edVolta) {
  	String saida;
	saida = "<item ";
	saida += "oid_Recapagem='" + edVolta.getOid_Recapagem() + "' ";
	saida += "oid_Pneu='" + edVolta.getOid_Pneu() + "' ";
	saida += "oid_Empresa='" + edVolta.getOid_Empresa() + "' ";
	saida += "oid_Fornecedor_Recapagem='" + edVolta.getOid_Fornecedor_Recapagem() + "' ";
	saida += "oid_Fabricante_Banda='" + edVolta.getOid_Fabricante_Banda() + "' ";
	saida += "oid_Banda='" + edVolta.getOid_Banda() + "' ";
	saida += "vl_Recapagem='" + FormataValor.formataValorBT(edVolta.getVl_Recapagem(),2) + "' ";
	saida += "dt_Recapagem='" + edVolta.getDt_Recapagem() + "' ";
	saida += "nr_Nota_Fiscal_Recapagem='" + edVolta.getNr_Nota_Fiscal_Recapagem() + "' ";
	saida += "nr_Os_Recapagem='" + edVolta.getNr_Os_Recapagem() + "' ";
	saida += "nr_Mm_Sulco_Recapagem='" + FormataValor.formataValorBT(edVolta.getNr_Mm_Sulco_Recapagem(),1) + "' ";
	saida += "dm_Garantia_Recapagem='" + edVolta.getDm_Garantia_Recapagem() + "' ";
	//Consulta Recapagens Efetuadas
	saida += "nm_Dimensao_Pneu='" + edVolta.getNm_Dimensao_Pneu() + "' ";
	saida += "nm_Fornecedor_Recapagem='" + edVolta.getNm_Fornecedor_Recapagem() + "' ";
	saida += "nm_Fabricante_Banda='" + edVolta.getNm_Fabricante_Banda() + "' ";
	saida += "nm_Banda='" + edVolta.getNm_Banda() + "' ";
	saida += "nr_Fogo='" + edVolta.getNr_Fogo() + "' ";
	saida += "nr_Vida='" + edVolta.getNr_Vida() + "' ";
	saida += "tx_Situacao='" + edVolta.getTx_Situacao() + "' ";
	saida += "/>";
	return saida;
  }

  public boolean checkDuplo ( RecapagemED ed, String acao) throws Excecoes {
  	boolean ret = false;
  	RecapagemED edChk = new RecapagemED();
  		edChk.setOid_Recapagem(ed.getOid_Recapagem());
		edChk.setDt_Recapagem(ed.getDt_Recapagem());
		edChk = this.getByRecord(edChk);
  	if ("I".equals(acao) && edChk.getOid_Recapagem()>0)
  		ret = true;
  	else
      	if ("A".equals(acao) && edChk.getOid_Recapagem()>0 ) {
  			if (ed.getOid_Recapagem()!=edChk.getOid_Recapagem() )
  				ret = true ;
  	}
  	return ret;
  }

  public boolean checkEmUso ( RecapagemED ed ) throws Excecoes {
		try {
			RecapagemED recED = new RecapagemED();
			//pneuED.setOid_Empresa(ed.getOid_Empresa());
			//pneuED.setOid_Modelo_Pneu(ed.getOid_Modelo_Pneu());
			this.inicioTransacao();
			return (new RecapagemBD(this.sql).lista(recED).size()>0 ? true : false);
      } finally {
          this.fimTransacao(false);
      }
  }

}
