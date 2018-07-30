package com.master.rn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.bd.Marca_VeiculoBD;
import com.master.bd.Modelo_VeiculoBD;
import com.master.bd.Tipo_VeiculoBD;
import com.master.ed.Marca_VeiculoED;
import com.master.ed.Modelo_VeiculoED;
import com.master.ed.Tipo_VeiculoED;
import com.master.rl.JasperRL;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author Ralph
 * @serial Tipos de Ve�culos
 * @serialData 06/2006
 */
public class Tipo_VeiculoRN extends Transacao {

    public Tipo_VeiculoRN() {
    }

    public Tipo_VeiculoED inclui(Tipo_VeiculoED ed) throws Excecoes {
        try {
            this.inicioTransacao();
            ed = new Tipo_VeiculoBD(this.sql).inclui(ed);
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

    public void altera(Tipo_VeiculoED ed) throws Excecoes {
        try {
            this.inicioTransacao();
            new Tipo_VeiculoBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    public void deleta(Tipo_VeiculoED ed) throws Excecoes {
        try {
            this.inicioTransacao();
            new Tipo_VeiculoBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(Tipo_VeiculoED ed) throws Excecoes {
        try {
            this.inicioTransacao();
            ArrayList lista = new Tipo_VeiculoBD(sql).lista(ed);
            return lista;
        } finally {
            this.fimTransacao(false);
        }
    }

    public void lista(Tipo_VeiculoED ed, HttpServletRequest request, String nmObj) throws Excecoes {
        try {
            this.inicioTransacao();
            ArrayList lista = new Tipo_VeiculoBD(sql).lista(ed);
            request.setAttribute(nmObj, lista);
        } finally {
            this.fimTransacao(false);
        }
    }

    public Tipo_VeiculoED getByRecord(Tipo_VeiculoED ed) throws Excecoes {
        try {
            this.inicioTransacao();
            return new Tipo_VeiculoBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public void getByRecord(Tipo_VeiculoED ed, HttpServletRequest request, String nmObj) throws Excecoes {
    	try {
    		this.inicioTransacao();
    		Tipo_VeiculoED edQBR = new Tipo_VeiculoBD(this.sql).getByRecord(ed);
    		request.setAttribute(nmObj, edQBR.getOid_Tipo_Veiculo()== 0 ? null : edQBR);
    	} finally {
    		this.fimTransacao(false);
    	}
    }

    public void relatorio(Tipo_VeiculoED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
        try {
            this.inicioTransacao();
            String nm_Filtro = "";
            ArrayList lista = new Tipo_VeiculoBD(sql).lista(ed);
			ed.setLista(lista); // Joga a lista de veiculos no ed para enviar pro relat�rio 
			ed.setResponse(response);
			ed.setNomeRelatorio("pns007"); // Seta o nome do relat�rio
			// Monta a descricao do filtro utilizado
			BancoUtil bu = new BancoUtil();
			if (bu.doValida(ed.getNm_Tipo_Veiculo()))
				nm_Filtro+=" Descri��o=" + ed.getNm_Tipo_Veiculo();
			ed.setDescFiltro(nm_Filtro);
			new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
        } finally {
            this.fimTransacao(false);
        }
    }
    
    /**
     * processaRL
     * Processa solicita��o de relatorio OL retornando sempre um PDF.
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
	Tipo_VeiculoED ed = (Tipo_VeiculoED)Obj;
	
	//if ("1".equals(rel)) {
		this.relatorio(ed, request, response);	
	//} 

}
    
    /**
     * processaOL
     * Processa colicita��o de tela OL retornando sempre arquivo XML com a seguinte estrutura.
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
    	Tipo_VeiculoED ed = (Tipo_VeiculoED)Obj;
    	//Prepara a sa�da
    	
    	PrintWriter out = response.getWriter();
    	out.println("<?xml version='1.0' encoding='ISO-8859-1'?>");
    	if ("I".equals(acao) ) {
    		if (checkDuplo(ed,acao)) {  
    			out.println("<ret><item oknok='Tipo j� existente com esta descricao !'/></ret>");
    		} else {
	    		ed = this.inclui(ed);
	    		out.println("<ret><item oknok='IOK' oid='" + ed.getOid_Tipo_Veiculo() + "' /></ret>");
    		}    		
    	} else 
		if ("A".equals(acao)) {
    		if (checkDuplo(ed,acao)) {  
    			out.println("<ret><item oknok='Tipo j� existente com esta descricao !'/></ret>");
    		} else {
				this.altera(ed);
				out.println("<ret><item oknok='AOK'/></ret>");
    		}
		} else 
		if ("D".equals(acao)) {
			if (checkEmUso(ed)) {
				out.println("<ret><item oknok='Imposs�vel excluir! Tipo em uso!' /></ret>");
			} else {
				this.deleta(ed);
				out.println("<ret><item oknok='DOK'/></ret>");
				
			}
		} else {
		out.println("<cad>");
		String saida=null;
		ArrayList lst = new ArrayList();
		lst = this.lista(ed);
		for (int i=0; i<lst.size(); i++){
			Tipo_VeiculoED edVolta = new Tipo_VeiculoED();
			edVolta = (Tipo_VeiculoED)lst.get(i);
			if ("L".equals(acao)) {
				saida = "<item ";
				saida += "oid_Tipo_Veiculo='" + edVolta.getOid_Tipo_Veiculo() + "' ";
				saida += "nm_Tipo_Veiculo='" + edVolta.getNm_Tipo_Veiculo() + "' ";
				saida += "/>";
		}else
			if ("CB".equals(acao) || "CBC".equals(acao)) {
				if ( i==0 && "CBC".equals(acao) ) {
					saida = "<item ";
					saida += "value='0'>TODOS</item>";
					out.println(saida);
				}
				saida = "<item ";
				saida += "value='" + edVolta.getOid_Tipo_Veiculo() + "' NM_Tipo_Veiculo='" + edVolta.getNm_Tipo_Veiculo() + "'>";
				saida +=  edVolta.getNm_Tipo_Veiculo();
				saida += "</item>";
		}
			out.println(saida);
		}
		out.println("</cad>");
		}
    	out.flush();
    	out.close();
    }

    public boolean checkDuplo ( Tipo_VeiculoED ed, String acao) throws Excecoes {
    	boolean ret = false;
    	Tipo_VeiculoED edChk = new Tipo_VeiculoED();
		edChk.setOid_Empresa(ed.getOid_Empresa());
		edChk.setNm_Tipo_Veiculo(ed.getNm_Tipo_Veiculo());
		edChk = this.getByRecord(edChk);
    	if ("I".equals(acao) && edChk.getOid_Tipo_Veiculo()>0)
    		ret = true;
    	else
        	if ("A".equals(acao) && edChk.getOid_Tipo_Veiculo()>0 ) {
    			if (ed.getOid_Tipo_Veiculo()!=edChk.getOid_Tipo_Veiculo() )
    				ret = true ;
    	}
    	return ret;
    }

    public boolean checkEmUso ( Tipo_VeiculoED ed ) throws Excecoes {
		try {
			Modelo_VeiculoED modelo_veiculoED = new Modelo_VeiculoED();
			modelo_veiculoED.setOid_Empresa(ed.getOid_Empresa());
			modelo_veiculoED.setOid_Tipo_Veiculo(ed.getOid_Tipo_Veiculo());
			this.inicioTransacao();
			return (new Modelo_VeiculoBD(this.sql).lista(modelo_veiculoED).size()>0 ? true : false); 
        } finally {
            this.fimTransacao(false);
        }
    }
    
    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}