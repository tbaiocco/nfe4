package com.master.rn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.bd.Menu_SistemaBD;
import com.master.ed.Menu_SistemaED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author Régis Steigleder
 * @serial Menu Sistema
 * @serialData 05/2007
 */
public class Menu_SistemaRN extends Transacao {

    public Menu_SistemaRN() {
    }

    public Menu_SistemaED inclui(Menu_SistemaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Menu_SistemaBD(this.sql).inclui(ed);
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

    public void altera(Menu_SistemaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Menu_SistemaBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    public void deleta(Menu_SistemaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Menu_SistemaBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(Menu_SistemaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Menu_SistemaBD(sql).lista(ed);
            return lista;
        } finally {
            this.fimTransacao(false);
        }
    }

    public void lista(Menu_SistemaED ed, HttpServletRequest request, String nmObj) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Menu_SistemaBD(sql).lista(ed);
            request.setAttribute(nmObj, lista);
        } finally {
            this.fimTransacao(false);
        }
    }
    
    public Menu_SistemaED getByRecord(Menu_SistemaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Menu_SistemaBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public void getByRecord(Menu_SistemaED ed, HttpServletRequest request, String nmObj) throws Excecoes {

    	try {
    		this.inicioTransacao();
    		Menu_SistemaED edQBR = new Menu_SistemaBD(this.sql).getByRecord(ed);
    		request.setAttribute(nmObj, edQBR.getOid_Menu_Sistema()== 0 ? null : edQBR);

    	} finally {
    		this.fimTransacao(false);
    	}
    }
    
    public ArrayList listaFilhos (Menu_SistemaED ed) throws Excecoes {
    	try {
            this.inicioTransacao();
            ArrayList lista = new Menu_SistemaBD(sql).listaFilhos(ed);
            return lista;
        } finally {
            this.fimTransacao(false);
        }
    }

    //Método desativado temporariamente.. Apaga-lo assim que possível.
    public ArrayList listaEstruturado(Menu_SistemaED ed) throws Excecoes {

        try {
        	ArrayList lista = new ArrayList();
            this.inicioTransacao();
            ArrayList lst1 = new Menu_SistemaBD(sql).listaFilhos(ed); // Array Primeiro nível
            for (int i1=0; i1<lst1.size(); i1++){
				Menu_SistemaED ed1 = new Menu_SistemaED();
				ed1 = (Menu_SistemaED)lst1.get(i1);
				lista.add(ed1);
				ArrayList lst2 = new Menu_SistemaBD(sql).listaFilhos(ed1); // Array  Segundo nivel
	            for (int i2=0; i2<lst2.size(); i2++){
					Menu_SistemaED ed2 = new Menu_SistemaED();
					ed2 = (Menu_SistemaED)lst2.get(i2);
					lista.add(ed2);
					ArrayList lst3 = new Menu_SistemaBD(sql).listaFilhos(ed2); // Array Terceiro nivel
		            for (int i3=0; i3<lst3.size(); i3++){
						Menu_SistemaED ed3 = new Menu_SistemaED();
						ed3 = (Menu_SistemaED)lst3.get(i3);
						lista.add(ed3);
						ArrayList lst4 = new Menu_SistemaBD(sql).listaFilhos(ed3); // Array Quarto nivel
			            for (int i4=0; i4<lst4.size(); i4++){
							Menu_SistemaED ed4 = new Menu_SistemaED();
							ed4 = (Menu_SistemaED)lst4.get(i4);
							lista.add(ed4);
			            }	
		            }	
	            }	
            }	
            return lista;
        } finally {
            this.fimTransacao(false);
        }
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
    	Menu_SistemaED mpED = (Menu_SistemaED)Obj;
    	//Prepara a saída
    	
    	PrintWriter out = response.getWriter();
    	out.println("<?xml version='1.0' encoding='ISO-8859-1'?>");
    	if ("I".equals(acao) ) {
    		Menu_SistemaED gbrED = this.getByRecord(mpED); 	// Procura se já está cadastrado
    		if (gbrED.getOid_Menu_Sistema()>0){ 			// Item já está cadastrado
    			out.println("<ret><item oknok='Código da opção já está cadastrada!' /></ret>");
    		} else {
    			gbrED.setOid_Menu_Sistema(mpED.getOid_Menu_Sistema()); 				// Procura se o pai existe para o lançamento
				gbrED.setCd_Opcao(mpED.getCd_Opcao_Pai()); 	// Procura se o pai existe para o lançamento
				gbrED = this.getByRecord(gbrED);
				if (gbrED.getOid_Menu_Sistema()==0) { 		// Código pai não cadastrado
					out.println("<ret><item oknok='Código da opção pai não cadastrado!' /></ret>");
	    		} else {    			
	    			mpED = this.inclui(mpED); 				// Inclui e Retorna ok com o oid do registro incluído
	    			out.println("<ret><item oknok='IOK' oid='" +mpED.getOid_Menu_Sistema() + "'/></ret>"); 
	    		}
    		}
    	} else 
		if ("A".equals(acao)) {
			this.altera(mpED);
			out.println("<ret><item oknok='AOK' /></ret>");
		} else 
		if ("D".equals(acao)) {
			if (this.listaFilhos(mpED).size()>0) {
				out.println("<ret><item oknok='Impossível excluir! Opção tem sub-itens!' /></ret>");
			} else {
				this.deleta(mpED);
				out.println("<ret><item oknok='DOK' /></ret>");
			}
		} else {
		out.println("<cad>");
		if ("L".equals(acao)) {
			String saida;
			ArrayList lst = new ArrayList();
			lst = this.lista(mpED);
			for (int i=0; i<lst.size(); i++){
				Menu_SistemaED edVolta = new Menu_SistemaED();
				edVolta = (Menu_SistemaED)lst.get(i);
				saida = "<item ";
				saida += "oid_Sistema='" + edVolta.getOid_Sistema() + "' ";
				saida += "oid_Menu_Sistema='" + edVolta.getOid_Menu_Sistema() + "' ";
				saida += "cd_Opcao='" + edVolta.getCd_Opcao() + "' ";
				saida += "cd_Opcao_Pai='" + edVolta.getCd_Opcao_Pai() + "' ";
				saida += "nm_Opcao='" + edVolta.getNm_Opcao() + "' ";
				saida += "nm_Tela='" + edVolta.getNm_Tela() + "' ";
				saida += "nm_Sistema='" + edVolta.getNm_Sistema() + "' ";
				saida += ">";
				saida += "</item>";
				out.println(saida);
			}
		}
		out.println("</cad>");
		}
    	out.flush();
    	out.close();
    }

    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}