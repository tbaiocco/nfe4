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
import com.master.bd.Modelo_PneuBD;
import com.master.ed.Cotacao_PneuED;
import com.master.ed.Dimensao_PneuED;
import com.master.ed.Fabricante_PneuED;
import com.master.ed.Modelo_PneuED;
import com.master.rl.JasperRL;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.FormataValor;
import com.master.util.OLUtil;
import com.master.util.bd.Transacao;
import com.master.util.Utilitaria;

/**
 * @author Régis Steigleder
 * @serial Cotacao de pneus
 * @serialData 12/2007
 */
public class Cotacao_PneuRN
extends Transacao {
	
	BancoUtil bu = new BancoUtil();
	
	public Cotacao_PneuED inclui (Cotacao_PneuED ed) throws Excecoes {
		inicioTransacao ();
		try {
			Cotacao_PneuED toReturn = new Cotacao_PneuBD (this.sql).inclui (ed);
			fimTransacao (true);
			return toReturn;
		}
		catch (Excecoes e) {
			abortaTransacao ();
			throw e;
		}
	}

	public void altera (Cotacao_PneuED ed) throws Excecoes {
		inicioTransacao ();
		try {
			// Busca o array criando um ed para cada ocorrencia dele e atualiza o registro	
			OLUtil ol = new OLUtil();
			ArrayList lst = ol.pegaArraydaRequest(ed.getArray());
			for (int x=0;x<lst.size();x++) {
				Cotacao_PneuED mpED = (Cotacao_PneuED)lst.get(x);
				mpED.setUsuario_Stamp(ed.getUsuario_Stamp());
				new Cotacao_PneuBD(this.sql).altera(mpED);		// Altera o registro.
			}
			fimTransacao (true);
		}
		catch (Excecoes e) {
			abortaTransacao ();
			throw e;
		}
	}

	public void delete (Cotacao_PneuED ed) throws Excecoes {
		inicioTransacao ();
		try {
			new Cotacao_PneuBD (this.sql).deleta (ed);
			fimTransacao (true);
		}
		catch (Excecoes e) {
			abortaTransacao ();
			throw e;
		}
	}

	public ArrayList lista (Cotacao_PneuED ed) throws Excecoes {
		inicioTransacao ();
		try {
			return new Cotacao_PneuBD (this.sql).lista (ed);
		}
		finally {
			this.fimTransacao (false);
		}

	}

	public Cotacao_PneuED getByRecord (Cotacao_PneuED ed ) throws Excecoes {
		inicioTransacao ();
		try {
			return new Cotacao_PneuBD (this.sql).getByRecord (ed);
		}
		finally {
			fimTransacao (false);
		}
	}


	public void preparaListaCotacao (Cotacao_PneuED ed) throws Excecoes {
		inicioTransacao ();
		try {
			new Cotacao_PneuBD (this.sql).preparaListaCotacao (ed);
			fimTransacao (true);
		}
		catch (Excecoes e) {
			abortaTransacao ();
			throw e;
		}
	}

    public void relatorioCustoReposicao(Cotacao_PneuED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
        try {
            this.inicioTransacao();
    		String nm_Filtro = "";
    		// Monta o filtro e a descricao do filtro utilizado
	    	if (ed.getOid_Modelo_Pneu()>0 ) { 
	    		Modelo_PneuED modpED = new Modelo_PneuED();
	    		modpED.setOid_Empresa(ed.getOid_Empresa());
	    		modpED.setOid_Modelo_Pneu((int)ed.getOid_Modelo_Pneu());
	    		modpED = new Modelo_PneuBD(this.sql).getByRecord(modpED);
	    		nm_Filtro+=" Modelo=" + modpED.getNM_Modelo_Pneu();
	    	}	
	    	if (ed.getOid_Dimensao_Pneu()>0 ) { 
	    		Dimensao_PneuED dimpED = new Dimensao_PneuED();
	    		dimpED.setOid_Empresa(ed.getOid_Empresa());
	    		dimpED.setOid_Dimensao_Pneu(ed.getOid_Dimensao_Pneu());
	    		dimpED = new Dimensao_PneuBD(this.sql).getByRecord(dimpED);
	    		nm_Filtro+=" Dimensão=" + dimpED.getNm_Dimensao_Pneu();
	    	}	
	    	if (ed.getOid_Fabricante_Pneu()>0 ) { 
	    		Fabricante_PneuED fabpED = new Fabricante_PneuED();
	    		fabpED.setOid_Empresa((int)ed.getOid_Empresa());
	    		fabpED.setOid_Fabricante_Pneu((int)ed.getOid_Fabricante_Pneu());
	    		fabpED = new Fabricante_PneuBD(this.sql).getByRecord(fabpED);
	    		nm_Filtro+=" Fabricante=" + fabpED.getNM_Fabricante_Pneu();
	    	}	
            
	    	ArrayList lista = new Cotacao_PneuBD(sql).listaCustoReposicao(ed,"rel");
			ed.setLista(lista); // Joga a lista de cotacoes no ed para enviar pro relatório 
			ed.setResponse(response);
			ed.setNomeRelatorio("pns505"); // Seta o nome do relatório
			// Monta a descricao do filtro utilizado
			ed.setDescFiltro(nm_Filtro);
			new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
        } finally {
            this.fimTransacao(false);
        }
    }

    public String graficoCustoReposicao(Cotacao_PneuED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
    	String string = null;
    	string="Custo de Reposição - Análise Financeira";
        try {
            this.inicioTransacao();
    		String nm_Filtro = "";
    		// Monta o filtro e a descricao do filtro utilizado
	    	if (ed.getOid_Dimensao_Pneu()>0 ) { 
	    		Dimensao_PneuED dimpED = new Dimensao_PneuED();
	    		dimpED.setOid_Empresa(ed.getOid_Empresa());
	    		dimpED.setOid_Dimensao_Pneu(ed.getOid_Dimensao_Pneu());
	    		dimpED = new Dimensao_PneuBD(this.sql).getByRecord(dimpED);
	    		nm_Filtro+=" Dimensão=" + dimpED.getNm_Dimensao_Pneu();
	    	}	
	    	if (ed.getOid_Fabricante_Pneu()>0 ) { 
	    		Fabricante_PneuED fabpED = new Fabricante_PneuED();
	    		fabpED.setOid_Empresa((int)ed.getOid_Empresa());
	    		fabpED.setOid_Fabricante_Pneu((int)ed.getOid_Fabricante_Pneu());
	    		fabpED = new Fabricante_PneuBD(this.sql).getByRecord(fabpED);
	    		nm_Filtro+=" Fabricante=" + fabpED.getNM_Fabricante_Pneu();
	    	}
	    	// Cabeçalho do xml do gráfico 	
	    	string+="<graph " +
	    			"bgColor='EAEAEA' " +
	    			"caption='" + nm_Filtro + "' " + 
	    			"xAxisName='Marca - Modelo' " +
	    			"yAxisName='Valor p/1000Km' " +
	    			"decimalPrecision='3' " +
	    			"formatNumberScale='0'  " +
	    			"decimalSeparator=',' " +
	    			"numberSuffix='R$' " +
	    			"showNames='1' " +
	    			"rotateNames='1' >";
	    	ArrayList lista = new Cotacao_PneuBD(sql).listaCustoReposicao(ed,"gra");
            for (int i=0; i<lista.size(); i++){
				Cotacao_PneuED edVolta = new Cotacao_PneuED();
				edVolta = (Cotacao_PneuED)lista.get(i);
		    	string+="<set " +
		    			"name='" + edVolta.getNm_Fabricante_Pneu().trim() + " " + edVolta.getNm_Modelo_Pneu().trim () + "' " +
		    			"value='" + edVolta.getVl_Cotacao_Pneu() / edVolta.getNr_Km_Acumulada() * 1000 + "' " +
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

	public void processaRL(String rel, Object Obj, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Excecoes {
		//Extrai o bean com os campos da request colados
		Cotacao_PneuED ed = (Cotacao_PneuED)Obj;
		ed.setRequest(request);
		if ("2".equals(rel)) {
			this.relatorioCustoReposicao(ed, request, response);	
		} 
	}

	/**
	 * processaGR
	 * Processa solicitação de relatorio OL retornando sempre um PDF.
	 * @param rel = Qual o relatorio a ser chamado
	 * @param Obj = Um bean populado com parametros para a geracao do relatorio
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws Excecoes
	 */

	public String processaGR(String acao, Object Obj, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Excecoes {
		//Extrai o bean com os campos da request colados
		Cotacao_PneuED ed = (Cotacao_PneuED)Obj;
		return this.graficoCustoReposicao(ed, request, response);	
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
		Cotacao_PneuED ed = (Cotacao_PneuED)Obj;
		//Prepara a saída
		ed.setMasterDetails(request);

		PrintWriter out = response.getWriter();
		out.println("<?xml version='1.0' encoding='ISO-8859-1'?>");
		if ("A".equals(acao)) {
			this.altera(ed);
			out.println("<ret><item oknok='AOK' /></ret>");
		} else			
		if ("PL".equals(acao)) {
			this.preparaListaCotacao(ed);
			acao="L";
		}			
		if ("L".equals(acao)) {
			out.println("<cad>");
			String saida = null;
			ArrayList lst = new ArrayList();
			lst = this.lista(ed);
			for (int i=0; i<lst.size(); i++){
				Cotacao_PneuED edVolta = new Cotacao_PneuED();
				edVolta = (Cotacao_PneuED)lst.get(i);
				if ("L".equals(acao)) {
					saida = "<item ";
					saida += "oid_Cotacao_Pneu='" + edVolta.getOid_Cotacao_Pneu() + "' ";
					saida += "nm_Modelo_Pneu='" + edVolta.getNm_Modelo_Pneu() + "' ";
					saida += "nm_Dimensao_Pneu='" + edVolta.getNm_Dimensao_Pneu() + "' ";
					saida += "nm_Fabricante_Pneu='" + edVolta.getNm_Fabricante_Pneu() + "' ";
					saida += "vl_Cotacao_Pneu='" + FormataValor.formataValorBT(edVolta.getVl_Cotacao_Pneu(),2) + "' ";
					saida += "dt_Cotacao_Pneu='" + edVolta.getDt_Cotacao_Pneu() + "' ";
					saida += "msg_Stamp='" + edVolta.getMsg_Stamp() + "' ";
					saida += "/>";
				}
				out.println(saida);
			}
			out.println("</cad>");
			out.flush();
			out.close();
		}
	}
}
