package com.master.rn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.bd.Marca_VeiculoBD;
import com.master.bd.Modelo_VeiculoBD;
import com.master.bd.VeiculoBD;
import com.master.ed.Marca_VeiculoED;
import com.master.ed.Modelo_VeiculoED;
import com.master.ed.VeiculoED;
import com.master.rl.JasperRL;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author Cristian Vianna Garcia
 * @serial Modelos de Veículos
 * @serialData 06/2007
 */
public class Modelo_VeiculoRN extends Transacao {

    public Modelo_VeiculoRN() {
    }

    public Modelo_VeiculoED inclui(Modelo_VeiculoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Modelo_VeiculoBD(this.sql).inclui(ed);
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

    public void altera(Modelo_VeiculoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Modelo_VeiculoBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    public void deleta(Modelo_VeiculoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Modelo_VeiculoBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(Modelo_VeiculoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Modelo_VeiculoBD(sql).lista(ed);
            return lista;
        } finally {
            this.fimTransacao(false);
        }
    }

    public void lista(Modelo_VeiculoED ed, HttpServletRequest request, String nmObj) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Modelo_VeiculoBD(sql).lista(ed);
            request.setAttribute(nmObj, lista);
        } finally {
            this.fimTransacao(false);
        }
    }

    public Modelo_VeiculoED getByRecord(Modelo_VeiculoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Modelo_VeiculoBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public void getByRecord(Modelo_VeiculoED ed, HttpServletRequest request, String nmObj) throws Excecoes {

    	try {
    		this.inicioTransacao();
    		Modelo_VeiculoED edQBR = new Modelo_VeiculoBD(this.sql).getByRecord(ed);
    		request.setAttribute(nmObj, edQBR.getOid_Modelo_Veiculo()== 0 ? null : edQBR);

    	} finally {
    		this.fimTransacao(false);
    	}
    }

    public void relatorio(Modelo_VeiculoED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
        try {
            this.inicioTransacao();
            String nm_Filtro = "";
            ArrayList lista = new Modelo_VeiculoBD(sql).lista(ed);
			ed.setLista(lista); // Joga a lista de veiculos no ed para enviar pro relatório 
			ed.setResponse(response);
			ed.setNomeRelatorio("pns008"); // Seta o nome do relatório
			// Monta a descricao do filtro utilizado
			BancoUtil bu = new BancoUtil();
			if (bu.doValida(ed.getNm_Modelo_Veiculo()))
				nm_Filtro+=" Modelo Veículo=" + ed.getNm_Modelo_Veiculo();
			if (bu.doValida(ed.getNm_Marca_Veiculo()))
				nm_Filtro+=" Marca Veículo=" + ed.getNm_Marca_Veiculo();
			if (bu.doValida(ed.getNm_Tipo_Veiculo()))
				nm_Filtro+=" Tipo Veículo=" + ed.getNm_Tipo_Veiculo();
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
	Modelo_VeiculoED ed = (Modelo_VeiculoED)Obj;
	
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
    	Modelo_VeiculoED ed = (Modelo_VeiculoED)Obj;
    	//Prepara a saída
    	
    	PrintWriter out = response.getWriter();
    	out.println("<?xml version='1.0' encoding='ISO-8859-1'?>");
    	if ("I".equals(acao) ) {
    		if (checkDuplo(ed,acao)) {  
    			out.println("<ret><item oknok='Modelo já existente com esta descricao !'/></ret>");
    		} else {
	    		ed = this.inclui(ed);
	    		out.println("<ret><item oknok='IOK' oid='" + ed.getOid_Modelo_Veiculo() + "' /></ret>");
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
				this.deleta(ed);
				out.println("<ret><item oknok='DOK' /></ret>");	
			}
		} else {
		out.println("<cad>");
		String saida=null;
		ArrayList lst = new ArrayList();
		lst = this.lista(ed);
		for (int i=0; i<lst.size(); i++){
			Modelo_VeiculoED edVolta = new Modelo_VeiculoED();
			edVolta = (Modelo_VeiculoED)lst.get(i);
			if ("L".equals(acao)) {
				saida = "<item ";
				saida += "oid_Modelo_Veiculo='" + edVolta.getOid_Modelo_Veiculo() + "' ";
				saida += "nm_Modelo_Veiculo='" + edVolta.getNm_Modelo_Veiculo() + "' ";
				saida += "oid_Marca_Veiculo='" + edVolta.getOid_Marca_Veiculo() + "' ";
				saida += "oid_Tipo_Veiculo='" + edVolta.getOid_Tipo_Veiculo() + "' ";
				saida += "nm_Marca_Veiculo='" + edVolta.getNm_Marca_Veiculo() + "' ";
				saida += "nm_Tipo_Veiculo='" + edVolta.getNm_Tipo_Veiculo() + "' ";
				saida += "dm_Tipo_Chassis='" + edVolta.getDm_Tipo_Chassis() + "' ";
				saida += "/>";
			}else
			if ("CB".equals(acao) || "CBC".equals(acao)) {
				if ( i==0 && "CBC".equals(acao) ) {
					saida = "<item ";
					saida += "value='0'>TODOS</item>";
					out.println(saida);
				}
				saida = "<item ";
				saida += "Nm_Marca_Veiculo='" + edVolta.getNm_Marca_Veiculo() +  "' Dm_Tipo_Chassis='" + edVolta.getDm_Tipo_Chassis() + "' Nm_Tipo_Veiculo='" + edVolta.getNm_Tipo_Veiculo() + "' value='" + edVolta.getOid_Modelo_Veiculo() + "'>";
				saida +=  edVolta.getNm_Modelo_Veiculo();
				saida += "</item>";
			}
			out.println(saida);
		}
		out.println("</cad>");
		}
    	out.flush();
    	out.close();
    }

    public boolean checkDuplo ( Modelo_VeiculoED ed, String acao) throws Excecoes {
    	Modelo_VeiculoED edChk = new Modelo_VeiculoED();
		edChk.setOid_Empresa(ed.getOid_Empresa());
		edChk.setNm_Modelo_Veiculo(ed.getNm_Modelo_Veiculo());
		edChk = this.getByRecord(edChk);
		if ("I".equals(acao) && edChk.getOid_Modelo_Veiculo()>0)
    		return true;
    	else
    	if ("A".equals(acao) && ed.getOid_Modelo_Veiculo()!=edChk.getOid_Modelo_Veiculo() ) 
    		return true;
    	else
    		return false;
    }
    
    public boolean checkEmUso ( Modelo_VeiculoED ed ) throws Excecoes {
		try {
			VeiculoED veiculoED = new VeiculoED();
			veiculoED.setOid_Empresa(ed.getOid_Empresa());
			veiculoED.setOid_Modelo_Veiculo(ed.getOid_Modelo_Veiculo());
			this.inicioTransacao();
			return (new VeiculoBD(this.sql).lista(veiculoED).size()>0 ? true : false); 
        } finally {
            this.fimTransacao(false);
        }
    }
    
    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}