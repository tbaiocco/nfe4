package com.master.rn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.util.JavaUtil;
import com.master.bd.Menu_Perfil_SistemaBD;
import com.master.ed.Menu_Perfil_SistemaED;
import com.master.ed.UsuarioED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author Régis Steigleder
 * @serial Menus Perfil do Sistema
 * @serialData 05/2007
 */
public class Menu_Perfil_SistemaRN extends Transacao {

    public Menu_Perfil_SistemaRN() {
    }

    public Menu_Perfil_SistemaED inclui(Menu_Perfil_SistemaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Menu_Perfil_SistemaBD(this.sql).inclui(ed);
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

    public void altera(Menu_Perfil_SistemaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Menu_Perfil_SistemaBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    public void deleta(Menu_Perfil_SistemaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Menu_Perfil_SistemaBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(Menu_Perfil_SistemaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Menu_Perfil_SistemaBD(sql).lista(ed);
            return lista;
        } finally {
            this.fimTransacao(false);
        }
    }

    public void lista(Menu_Perfil_SistemaED ed, HttpServletRequest request, String nmObj) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Menu_Perfil_SistemaBD(sql).lista(ed);
            request.setAttribute(nmObj, lista);
        } finally {
            this.fimTransacao(false);
        }
    }

    public Menu_Perfil_SistemaED getByRecord(Menu_Perfil_SistemaED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Menu_Perfil_SistemaBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public void getByRecord(Menu_Perfil_SistemaED ed, HttpServletRequest request, String nmObj) throws Excecoes {

    	try {
    		this.inicioTransacao();
    		Menu_Perfil_SistemaED edQBR = new Menu_Perfil_SistemaBD(this.sql).getByRecord(ed);
    		request.setAttribute(nmObj, edQBR.getOid_Menu_Perfil()== 0 ? null : edQBR);

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
    	Menu_Perfil_SistemaED mpED = (Menu_Perfil_SistemaED)Obj;
    	//Prepara a saída
    	
    	PrintWriter out = response.getWriter();
    	out.println("<?xml version='1.0' encoding='ISO-8859-1'?>");
    	if ("I".equals(acao) ) {
    		mpED = this.inclui(mpED);
    		out.println("<ret><item oknok='IOK' oid='" + mpED.getOid_Menu_Perfil_Sistema() + "'/></ret>");
    	} else 
		if ("A".equals(acao)) {
			this.altera(mpED);
			out.println("<ret><item oknok='AOK' /></ret>");
		} else 
		if ("D".equals(acao)) {
			this.deleta(mpED);
			out.println("<ret><item oknok='DOK' /></ret>");
		} else 
		if ("M".equals(acao)) {
			// Pega o usuario da session e busca o perfil de menu dele
            UsuarioED usuario = (UsuarioED)request.getSession(true).getAttribute("usuario");
            mpED.setOid_Menu_Perfil((int) usuario.getOid_Menu_Perfil());
            // Prepara a saída do menu dele....
            out.println("<menu>");
			String saida = ""; boolean flagTopic, flagSection, flagItem;
			flagTopic = false ; flagSection = false ; flagItem = false;
			ArrayList lst = new ArrayList();
			lst = this.lista(mpED);
			for (int i=0; i<lst.size(); i++) {
				Menu_Perfil_SistemaED edVolta = new Menu_Perfil_SistemaED();
				edVolta = (Menu_Perfil_SistemaED)lst.get(i);
				if (edVolta.getNr_Nivel()==0) {
					if (flagItem==true) { 
						saida = "</item>  " ;
						out.println(saida);
						flagItem=false;
					}	
					if (flagTopic==true) { 
						saida = "</topic>  " ;
						out.println(saida);
						flagTopic=false;
					}	
					if (flagSection==true) { 
						saida = "</section>  " ;
						out.println(saida);
						flagSection=false;
					}	
					if ("true".equals(edVolta.getDm_Acesso())) {
						flagSection=true;
						saida =  "<section " ;
					}
				} else	
				if (edVolta.getNr_Nivel()==1) {
					if (flagItem==true) { 
						saida = "</item>  " ;
						out.println(saida);
						flagItem=false;
					}	
					if (flagTopic==true) { 
						saida = "</topic>  " ;
						out.println(saida);
						flagTopic=false;
					}	
					if ("true".equals(edVolta.getDm_Acesso())) {
						flagTopic=true;
						saida =  "<topic " ;
					}
				} else
				if (edVolta.getNr_Nivel()==2) {
					if (flagItem==true) { 
						saida = "</item>  " ;
						out.println(saida);
						flagItem=false;
					}	
					if ("true".equals(edVolta.getDm_Acesso())) {
						flagItem=true;
						saida =  "<item " ;
					}
				}
				if ("true".equals(edVolta.getDm_Acesso())) {
					saida += " name='" + edVolta.getNm_Opcao() + "'";
					if (edVolta.getNm_Tela().length()>0)
						saida += " src='" + edVolta.getNm_Tela() +"' ";
					saida += ">";
					out.println(saida);
				}
			}	
			if (flagItem==true) { 
				saida = "</item> " ;
				out.println(saida);
			}	
			if (flagTopic==true) { 
				saida = "</topic> " ;
				out.println(saida);
			}
			if (flagSection==true) { 
				saida = "</section>  " ;
				out.println(saida);
			}	
			out.println("</menu>");
		} else {
		out.println("<cad>");
		if ("L".equals(acao)) {
			String saida;
			ArrayList lst = new ArrayList();
			lst = this.lista(mpED);
			for (int i=0; i<lst.size(); i++){
				Menu_Perfil_SistemaED edVolta = new Menu_Perfil_SistemaED();
				edVolta = (Menu_Perfil_SistemaED)lst.get(i);
				saida = "<item ";
				saida += "oid_Menu_Perfil_Sistema='" + edVolta.getOid_Menu_Perfil_Sistema() + "' ";
				saida += "oid_Menu_Perfil='" + edVolta.getOid_Menu_Perfil() + "' ";
				saida += "oid_Menu_Sistema='" + edVolta.getOid_Menu_Sistema() + "' ";
				saida += "cd_Opcao='" + edVolta.getCd_Opcao() + "' ";
				saida += "cd_Opcao_Pai='" + edVolta.getCd_Opcao_Pai() + "' ";
				saida += "nm_Opcao='" + edVolta.getNm_Opcao() + "' ";
				if (!JavaUtil.doValida(edVolta.getDm_Acesso()))
					saida += "dm_Acesso='false' ";
				else
					saida += "dm_Acesso='" + edVolta.getDm_Acesso() + "' ";
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