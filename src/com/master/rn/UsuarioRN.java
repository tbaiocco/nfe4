package com.master.rn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.bd.Acerto_ViagemBD;
import com.master.bd.UsuarioBD;
import com.master.ed.Acerto_ViagemED;
import com.master.ed.UsuarioED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class UsuarioRN extends Transacao {

    public UsuarioRN() {
    }

    public UsuarioED inclui(UsuarioED ed) throws Excecoes {

        this.inicioTransacao();
        ed = new UsuarioBD(this.sql).inclui(ed);
        this.fimTransacao(true);

        return ed;
    }
    
 

    public void altera(UsuarioED ed) throws Excecoes {

        this.inicioTransacao();
        new UsuarioBD(this.sql).altera(ed);
        this.fimTransacao(true);
    }

  
    public void deleta(UsuarioED ed) throws Excecoes {

        this.inicioTransacao();
        new UsuarioBD(this.sql).deleta(ed);
        this.fimTransacao(true);
    }

    public ArrayList lista(UsuarioED ed) throws Excecoes {

        this.inicioTransacao();
        ArrayList lista = new UsuarioBD(sql).lista(ed);
        this.fimTransacao(false);
        return lista;
    }
    
    public ArrayList lista_Help(UsuarioED ed) throws Excecoes {

        this.inicioTransacao();
        ArrayList lista = new UsuarioBD(sql).lista_Help(ed);
        this.fimTransacao(false);
        return lista;
    }

    public UsuarioED getByRecord(UsuarioED ed) throws Excecoes {

        this.inicioTransacao();
        ed = new UsuarioBD(this.sql).getByRecord(ed);
        this.fimTransacao(true);

        return ed;
    }

    public void geraRelatorio(UsuarioED ed) throws Excecoes {

        //antes de invocar chamada ao relatorio deve-se
        //fazer validacoes de regra de negocio
        this.inicioTransacao();
        new UsuarioBD(this.sql).geraRelatorio(ed);
        this.fimTransacao(false);
    }

    /**
    public byte[] geraRelacaoUsuarios(UsuarioED ed) throws Excecoes {
        try {
            this.inicioTransacao();
            return new UsuarioBD(this.sql).geraRelacaoUsuarios(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    **/
    public boolean getByEncrypt(String chave, String usuario) throws Excecoes {

        Excecoes excecoes = new Excecoes();
        boolean ok = false;
        this.inicioTransacao();
        //instancia ed de retorno
        try {
            // System.out.println("entrou no rn");
            String key = chave;
            String usr = usuario;
            ok = new UsuarioBD(this.sql).getByEncrypt(key, usr);
            //libera conexao nao "commitando"
            this.fimTransacao(false);
        } catch (Exception e) {
            this.abortaTransacao();
            excecoes.setClasse(this.getClass().getName());
            e.getMessage();
            excecoes.setMetodo("getByEncrypt");
            excecoes.setExc(e);
            throw excecoes;
        }
        return ok;
    }
    
    public boolean getByEncrypt(String chave, String usuario, String acao) throws Excecoes {

        Excecoes excecoes = new Excecoes();
        boolean ok = false;
        this.inicioTransacao();
        //instancia ed de retorno
        try {
            // System.out.println("entrou no rn");
            String key = chave;
            String usr = usuario;
            ok = new UsuarioBD(this.sql).getByEncrypt(key, usr, acao);
            //libera conexao nao "commitando"
            this.fimTransacao(true);
        } catch (Exception e) {
            this.abortaTransacao();
            excecoes.setClasse(this.getClass().getName());
            e.getMessage();
            excecoes.setMetodo("getByEncrypt");
            excecoes.setExc(e);
            throw excecoes;
        }
        return ok;
    }

    public byte[] geraRelacaoUsuarios(UsuarioED ed) throws Excecoes {
        try {
            this.inicioTransacao();
            return new UsuarioBD(this.sql).geraRelacaoUsuarios(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
    
    public UsuarioED getByRecord_Encrypt(UsuarioED ed) throws Excecoes {

        this.inicioTransacao();
        ed = new UsuarioBD(this.sql).getByRecord_Encrypt(ed);
        this.fimTransacao(true);

        return ed;
    }
    
    /**
     * valida senha, usuario, empresa stgp 
     * @param ed
     * @return
     * @throws Excecoes
     */
    public UsuarioED getByRecord_Encrypt_Stgp(UsuarioED ed) throws Excecoes {

        this.inicioTransacao();
        ed = new UsuarioBD(this.sql).getByRecord_Encrypt_Stgp(ed);
        this.fimTransacao(false);

        return ed;
    }
    /**
     * valida senha, usuario, empresa help 
     * @param ed
     * @return
     * @throws Excecoes
     */
    public UsuarioED getByRecord_Encrypt_Help(UsuarioED ed) throws Excecoes {
  
        this.inicioTransacao();
        ed = new UsuarioBD(this.sql).getByRecord_Encrypt_Help(ed);
        this.fimTransacao(false);

        return ed;
    }

    /**
     * valida senha, usuario, empresa help 
     * @param ed
     * @return
     * @throws Excecoes
     */
    public UsuarioED getByRecord_Help(UsuarioED ed) throws Excecoes {

        this.inicioTransacao();
        ed = new UsuarioBD(this.sql).getByRecord_Help(ed);
        this.fimTransacao(false);

        return ed;
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
    	UsuarioED ed = (UsuarioED)Obj;
    	//Prepara a saída
    	
    	PrintWriter out = response.getWriter();
    	out.println("<?xml version='1.0' encoding='ISO-8859-1'?>");
    	if ("I".equals(acao) ) {
    		ed = this.inclui(ed);
    		out.println("<ret><item oknok='IOK' oid='" + ed.getOid_Usuario() + "'/></ret>");
    	} else 
		if ("A".equals(acao)) {
			this.altera(ed);
			out.println("<ret><item oknok='AOK' /></ret>");
		} else 
		if ("D".equals(acao)) {
			this.deleta(ed);
			out.println("<ret><item oknok='DOK' /></ret>");
		} else {
			out.println("<cad>");
			if ("L".equals(acao)) {
				String saida;
				ArrayList lst = new ArrayList();
				lst = this.lista(ed);
				for (int i=0; i<lst.size(); i++){
					UsuarioED edVolta = new UsuarioED();
					edVolta = (UsuarioED)lst.get(i);
					saida = "<item ";
					saida += "oid_Usuario='" + edVolta.getOid_Usuario() + "' ";
					saida += "oid_Sistema='" + edVolta.getOid_Sistema() + "' ";
					saida += "oid_Menu_Perfil='" + edVolta.getOid_Menu_Perfil() + "' ";
					saida += "nm_Usuario='" + edVolta.getNm_Usuario() + "' ";
					saida += "nm_Menu_Perfil='" + edVolta.getNm_Menu_Perfil() + "' ";
					saida += "NM_Senha='" + edVolta.getNM_Senha() + "' ";
					saida += "oid_Unidade='" + edVolta.getOid_Unidade() + "' ";
					saida += "nm_Unidade='" + edVolta.getNm_Unidade() + "' ";
					saida += ">";
					saida += "</item>";
					out.println(saida);
				}
			} else
			if ("CB".equals(acao)) {
				String saida;
				ArrayList lst = new ArrayList();
				lst = this.lista(ed);
				for (int i=0; i<lst.size(); i++){
					UsuarioED edVolta = new UsuarioED();
					edVolta = (UsuarioED)lst.get(i);
					saida = "<item ";
					saida += "value='" + edVolta.getOid_Usuario() + "'>";
					saida +=  edVolta.getNm_Usuario();
					saida += "</item>";
					out.println(saida);
				}
			}
			out.println("</cad>");
		}
    	out.flush();
    	out.close();
    }

    
    
}