/*
 * Created on 12/11/2004
 */
package com.master.rn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.bd.Fabricante_PneuBD;
import com.master.bd.Motivo_SucateamentoBD;
import com.master.bd.PneuBD;
import com.master.ed.Fabricante_PneuED;
import com.master.ed.Motivo_SucateamentoED;
import com.master.ed.PneuED;
import com.master.rl.JasperRL;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.Utilitaria;
import com.master.util.bd.Transacao;

/**
 * @author Régis Steigleder
 * @serial Fabricantes de pneus
 * @serialData 06/2007
 */
public class Fabricante_PneuRN extends Transacao {

	public Fabricante_PneuED inclui (Fabricante_PneuED ed) throws Excecoes {
		try {
			inicioTransacao ();
			Fabricante_PneuED toReturn = new Fabricante_PneuBD (sql).inclui (ed);
			fimTransacao (true);
			return toReturn;
		}
		catch (Excecoes e) {
			abortaTransacao ();
			throw e;
		}
		catch (RuntimeException e) {
			abortaTransacao ();
			throw e;
		}
	}

	public void altera (Fabricante_PneuED ed) throws Excecoes {
		try {
			inicioTransacao ();
			new Fabricante_PneuBD (sql).altera (ed);
			this.fimTransacao (true);
		}
		catch (Excecoes e) {
			abortaTransacao ();
			throw e;
		}
		catch (RuntimeException e) {
			abortaTransacao ();
			throw e;
		}
	}

	public void delete (Fabricante_PneuED ed) throws Excecoes {
		try {
			inicioTransacao ();
			new Fabricante_PneuBD (sql).delete (ed);
			fimTransacao (true);
		}
		catch (Excecoes e) {
			abortaTransacao ();
			throw e;
		}
		catch (RuntimeException e) {
			abortaTransacao ();
			throw e;
		}
	}
	
	public ArrayList lista (Fabricante_PneuED ed) throws Excecoes {
		try {
			this.inicioTransacao ();
			return new Fabricante_PneuBD (sql).lista (ed);
		}
		finally {
			fimTransacao (false);
		}
	}

	public Fabricante_PneuED getByRecord (Fabricante_PneuED ed) throws Excecoes {
		try {
			this.inicioTransacao ();
			return new Fabricante_PneuBD (sql).getByRecord (ed);
		}
		finally {
			fimTransacao (false);
		}
	}

	public Fabricante_PneuED getByRecord (int oid) throws Excecoes {
		try {
			this.inicioTransacao ();
			return new Fabricante_PneuBD (sql).getByRecord (oid);
		}
		finally {
			fimTransacao (false);
		}
	}

	public Fabricante_PneuED getByCodigo (String codigo) throws Excecoes {
		try {
			this.inicioTransacao ();
			return new Fabricante_PneuBD (sql).getByCodigo (codigo);
		}
		finally {
			fimTransacao (false);
		}
	}

    public void relatorio(Fabricante_PneuED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
        try {
            this.inicioTransacao();
            String nm_Filtro = "";
            ArrayList lista = new Fabricante_PneuBD(sql).lista(ed);
			ed.setLista(lista); // Joga a lista de veiculos no ed para enviar pro relatório 
			ed.setResponse(response);
			ed.setNomeRelatorio("pns002"); // Seta o nome do relatório
			// Monta a descricao do filtro utilizado
			BancoUtil bu = new BancoUtil();
			if (bu.doValida(ed.getNM_Fabricante_Pneu()))
				nm_Filtro+=" Descrição=" + ed.getNM_Fabricante_Pneu();
			ed.setDescFiltro(nm_Filtro);
			new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
        } finally {
            this.fimTransacao(false);
        }
    }

    public String graficoPneusPorMarca(Fabricante_PneuED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
    	String string = null;
    	string="Distribuição de Pneus por Marca";
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
	    			"xAxisName='Marca' " +
	    			"yAxisName='Percentual' " +
	    			"decimalPrecision='2' " +
	    			"formatNumberScale='0' " +
	    			"decimalSeparator=',' " +
	    			"numberSuffix='%25' " +
	    			"showNames='1' " +
	    			"rotateNames='1' >" ;
	    	ArrayList lista = new Fabricante_PneuBD(sql).listaPneuPorMarca(ed,"gra");
            for (int i=0; i<lista.size(); i++){
				Fabricante_PneuED edVolta = new Fabricante_PneuED();
				edVolta = (Fabricante_PneuED)lista.get(i);
		    	string+="<set " +
		    			"name='" + edVolta.getNM_Fabricante_Pneu().trim() +  "' " +
		    			"value='" + edVolta.getVl_Fabricante_Pneu() + "' " +
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
    	Fabricante_PneuED ed = (Fabricante_PneuED)Obj;
	
	//if ("1".equals(rel)) {
		this.relatorio(ed, request, response);	
	//} 

}	
	
	public String processaGR(String acao, Object Obj, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Excecoes {
		//Extrai o bean com os campos da request colados
		Fabricante_PneuED ed = (Fabricante_PneuED)Obj;
		return this.graficoPneusPorMarca(ed, request, response);	
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
    	Fabricante_PneuED ed = (Fabricante_PneuED)Obj;
    	//Prepara a saída
    	ed.setMasterDetails(request);
    	
    	PrintWriter out = response.getWriter();
    	out.println("<?xml version='1.0' encoding='ISO-8859-1'?>");
    	if ("I".equals(acao) ) {
    		if (checkDuplo(ed,acao)) {  
    			out.println("<ret><item oknok='Marca já existente com esta descricao !'/></ret>");
    		} else {
	    		ed = this.inclui(ed);
	    		out.println("<ret><item oknok='IOK' oid='" + ed.getOid_Fabricante_Pneu() + "' /></ret>");
    		}    		
    	} else 
		if ("A".equals(acao)) {
    		if (checkDuplo(ed,acao)) {  
    			out.println("<ret><item oknok='Marca já existente com esta descricao !'/></ret>");
    		} else {
				this.altera(ed);
				out.println("<ret><item oknok='AOK' /></ret>");
    		}
		} else 
		if ("D".equals(acao)) {
			if (checkEmUso(ed)) {
				out.println("<ret><item oknok='Impossível excluir! Marca em uso!' /></ret>");
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
			Fabricante_PneuED edVolta = new Fabricante_PneuED();
			edVolta = (Fabricante_PneuED)lst.get(i);
			if ("L".equals(acao)) {
				saida = "<item ";
				saida += "oid_Fabricante_Pneu='" + edVolta.getOid_Fabricante_Pneu() + "' ";
				saida += "NM_Fabricante_Pneu='" + edVolta.getNM_Fabricante_Pneu() + "' ";
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
				saida += "value='" + edVolta.getOid_Fabricante_Pneu() + "'>";
				saida +=  edVolta.getNM_Fabricante_Pneu();
				saida += "</item>";
			}
			out.println(saida);
		}
		out.println("</cad>");
		}
    	out.flush();
    	out.close();
    }
    
    public boolean checkDuplo ( Fabricante_PneuED ed, String acao) throws Excecoes {
    	boolean ret = false;
    	Fabricante_PneuED edChk = new Fabricante_PneuED();
		edChk.setOid_Empresa(ed.getOid_Empresa());
		edChk.setNM_Fabricante_Pneu(ed.getNM_Fabricante_Pneu());
		edChk = this.getByRecord(edChk);
    	if ("I".equals(acao) && edChk.getOid_Fabricante_Pneu()>0)
    		ret = true;
    	else
        	if ("A".equals(acao) && edChk.getOid_Fabricante_Pneu()>0 ) {
    			if (ed.getOid_Fabricante_Pneu()!=edChk.getOid_Fabricante_Pneu() )
    				ret = true ;
    	}
    	return ret;
    }

    public boolean checkEmUso ( Fabricante_PneuED ed ) throws Excecoes {
		try {
			PneuED pneuED = new PneuED();
			pneuED.setOid_Empresa(ed.getOid_Empresa());
			pneuED.setOid_Fabricante_Pneu(ed.getOid_Fabricante_Pneu());
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