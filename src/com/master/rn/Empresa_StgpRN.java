package com.master.rn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.bd.Empresa_StgpBD;
import com.master.bd.Menu_PerfilBD;
import com.master.bd.UnidadeBD;
import com.master.bd.UsuarioBD;
import com.master.ed.Empresa_StgpED;
import com.master.ed.FornecedorED;
import com.master.ed.Menu_PerfilED;
import com.master.ed.Menu_Perfil_SistemaED;
import com.master.ed.UnidadeED;
import com.master.ed.UsuarioED;
import com.master.ed.VeiculoED;
import com.master.rl.JasperRL;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.FormataValor;
import com.master.util.Utilitaria;
import com.master.util.bd.Transacao;

/**
 * @author 
 * @serial Empresas
 * @serialData 12/2007
 */
public class Empresa_StgpRN extends Transacao {

    public Empresa_StgpRN() {
    }

    public Empresa_StgpED inclui(Empresa_StgpED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ed = new Empresa_StgpBD(this.sql).inclui(ed);
            
            // Criar a unidade associada a empresa
            UnidadeED uniED = new UnidadeED();
            uniED.setOid_Empresa(ed.getOid_Empresa());
            uniED.setNm_Unidade("MATRIZ");
            uniED = new UnidadeBD(this.sql).incluiStgp(uniED);
            
            //Criar perfil ADMINISTRADOR ( associado a empresa/sistema stgp )
            Menu_PerfilED mpED = new Menu_PerfilED();
            mpED.setOid_Empresa((int)ed.getOid_Empresa());
            mpED.setOid_Sistema(1);
            mpED.setNm_Menu_Perfil("ADMINISTRADOR");
            mpED = new Menu_PerfilBD(this.sql).inclui(mpED);
            
            //Criar o usuario ADMIN/ADMIN como perfil ADMINISTRADOR
            UsuarioED usuED = new UsuarioED();
            usuED.setNm_Usuario("ADMIN");
            usuED.setNM_Senha("ADMIN");
            usuED.setOid_Empresa(ed.getOid_Empresa());
            usuED.setOid_Unidade(new Integer((int)uniED.getOid_Unidade()));
            usuED.setOid_Menu_Perfil(mpED.getOid_Menu_Perfil());
            usuED.setDM_Perfil("A");
            new UsuarioBD(this.sql).inclui(usuED);
            
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

    public void altera(Empresa_StgpED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Empresa_StgpBD(this.sql).altera(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    public void deleta(Empresa_StgpED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Empresa_StgpBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(Empresa_StgpED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Empresa_StgpBD(sql).lista(ed);
            return lista;
        } finally {
            this.fimTransacao(false);
        }
    }

    public void lista(Empresa_StgpED ed, HttpServletRequest request, String nmObj) throws Excecoes {

        try {
            this.inicioTransacao();
            ArrayList lista = new Empresa_StgpBD(sql).lista(ed);
            request.setAttribute(nmObj, lista);
        } finally {
            this.fimTransacao(false);
        }
    }

    public Empresa_StgpED getByRecord(Empresa_StgpED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Empresa_StgpBD(this.sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public void relatorio(Empresa_StgpED ed, HttpServletRequest request, HttpServletResponse response) throws Excecoes {
        try {
            this.inicioTransacao();
            String nm_Filtro = "";
            ArrayList lista = new Empresa_StgpBD(sql).lista(ed);
			ed.setLista(lista); // Joga a lista de veiculos no ed para enviar pro relatório 
			ed.setResponse(response);
			ed.setNomeRelatorio("pns099"); // Seta o nome do relatório
			// Monta a descricao do filtro utilizado
			BancoUtil bu = new BancoUtil();

			if (bu.doValida(ed.getNm_Razao_Social()))
				nm_Filtro+=" Razão Social=" + ed.getNm_Razao_Social();
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
    	Empresa_StgpED ed = (Empresa_StgpED)Obj;
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
    	BancoUtil bu = new BancoUtil();
    	//Extrai o bean com os campos da request colados
    	Empresa_StgpED ed = (Empresa_StgpED)Obj;
    	//Prepara a saída
    	PrintWriter out = response.getWriter();
    	out.println("<?xml version='1.0' encoding='ISO-8859-1'?>");
    	if ("I".equals(acao)) {
    		if (checkDuplo(ed,acao)) {
    			out.println("<ret><item oknok='Empresa já existe com este CNPJ!'/></ret>");
    		} else {
    			ed = this.inclui(ed);
	    		out.println("<ret><item oknok='IOK' oid='" + ed.getOid_Empresa() + "' /></ret>");
    		}
    	} else 
		if ("A".equals(acao)) {
    		if (checkDuplo(ed,acao)) {  
    			out.println("<ret><item oknok='Não pode alterar o cnpj!'/></ret>");
    		} else {
    			this.altera(ed);
    			out.println("<ret><item oknok='AOK' /></ret>");
    		}
		}else
		if ("C".equals(acao)) {
			Empresa_StgpED edVolta = this.getByRecord(ed);
			if (edVolta.getOid_Empresa() > 0) {
				out.println("<cad>");
				out.println(montaRegistro(edVolta));
				out.println("</cad>");
			} else
				out.println("<ret><item oknok='Empresa não encontrada!' /></ret>");
		} else {
		out.println("<cad>");
		String saida=null;
		ArrayList lst = new ArrayList();
		lst = this.lista(ed);
		for (int i=0; i<lst.size(); i++){
			Empresa_StgpED edVolta = new Empresa_StgpED();
			edVolta = (Empresa_StgpED)lst.get(i);
			if ("L".equals(acao)) {
				this.montaRegistro(edVolta);
			}else
			if ("CB".equals(acao) || "CBC".equals(acao)) {
				if ( i==0 && "CBC".equals(acao) ) {
					saida = "<item ";
					saida += "value='0'>TODOS</item>";
					out.println(saida);
				}
				saida = "<item ";
				saida += "value='" + edVolta.getOid_Empresa() + "'>";
				if (edVolta.getNm_Razao_Social().length() > 30) {
					saida +=  edVolta.getNm_Razao_Social().substring(0, 30);
				} else {
					saida +=  edVolta.getNm_Razao_Social();
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
    
    private String montaRegistro( Empresa_StgpED edVolta ){
    	String saida;
    	saida = "<item ";
		saida += "oid_Empresa_Stgp='" + edVolta.getOid_Empresa() + "' ";
		saida += "nr_Cnpj='" + edVolta.getNr_Cnpj() + "' ";
		saida += "nm_Razao_Social='" + edVolta.getNm_Razao_Social() + "' ";
		saida += "nm_Endereco='" + edVolta.getNm_Endereco() + "' ";
		saida += "nm_Bairro='" + edVolta.getNm_Bairro() + "' ";
		saida += "nr_Cep='" + edVolta.getNr_Cep() + "' ";
		saida += "nm_Inscricao_Estadual='" + edVolta.getNm_Inscricao_Estadual() + "' ";
		saida += "nm_Cidade='" + edVolta.getNm_Cidade() + "' ";
		saida += "cd_Estado='" + edVolta.getCd_Estado() + "' ";
		saida += "nr_Telefone='" + edVolta.getNr_Telefone() + "' ";
		saida += "nr_Fax='" + edVolta.getNr_Fax() + "' ";
		saida += "nm_Email='" + edVolta.getNm_Email() + "' ";
		saida += "dm_Tipo_Frota='" + edVolta.getDm_Tipo_Frota() + "' ";
		saida += "/>";
		return saida;
    }

    public boolean checkDuplo ( Empresa_StgpED ed, String acao) throws Excecoes {
    	Empresa_StgpED edChk = new Empresa_StgpED();
    	edChk.setNr_Cnpj(ed.getNr_Cnpj());
		edChk = this.getByRecord(edChk);
		BancoUtil bu = new BancoUtil();
    	if ("I".equals(acao) && bu.doValida(edChk.getNr_Cnpj()))
    		return true;
    	else
    	if ("A".equals(acao) && !(ed.getNr_Cnpj().equals(edChk.getNr_Cnpj()) ))
    		return true;
    	else
    		return false;
    }
   
    
    protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}