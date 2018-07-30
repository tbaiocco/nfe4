/*
 * Created on 25/02/2005
 */
package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Tipo_Permissao_SubModuloED;
import com.master.rn.Tipo_Permissao_SubModuloRN;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.Mensagens;

/**
 * @author André Valadas
 */
public class Tipo_Permissao_SubModuloBean extends BancoUtil implements Serializable {

	public Tipo_Permissao_SubModuloED inclui(HttpServletRequest request) throws Excecoes {

		String oid_SubModulo_Permissao = request.getParameter("oid_SubModulo_Permissao");
		String DM_Tipo_Permissao = request.getParameter("FT_DM_Tipo_Permissao");
		String NM_Tipo_Permissao = request.getParameter("FT_NM_Tipo_Permissao");

		//*** Validações
		if (!doValida(oid_SubModulo_Permissao) || !doValida(DM_Tipo_Permissao) || !doValida(NM_Tipo_Permissao))
		    throw new Excecoes("Parâmetros incorretos!");

		Tipo_Permissao_SubModuloED ed = new Tipo_Permissao_SubModuloED();
		ed.setOid_SubModulo_Permissao(Integer.parseInt(oid_SubModulo_Permissao));
		ed.setDM_Tipo_Permissao(DM_Tipo_Permissao);
		ed.setNM_Tipo_Permissao(NM_Tipo_Permissao);
		
		//*** Valida se o registro não existe para poder incluir
		if (!this.doExiste(ed)) {
		    return new Tipo_Permissao_SubModuloRN().inclui(ed);
		} else throw new Mensagens("Já existe um Tipo de Permissão com o código informado!");
	}

	public void altera(HttpServletRequest request) throws Excecoes {

		String oid_Tipo_Permissao_SubModulo = request.getParameter("oid_Tipo_Permissao_SubModulo");
		String oid_SubModulo_Permissao = request.getParameter("oid_SubModulo_Permissao");
		String DM_Tipo_Permissao = request.getParameter("FT_DM_Tipo_Permissao");
		String NM_Tipo_Permissao = request.getParameter("FT_NM_Tipo_Permissao");

		//*** Validações
		if (!doValida(oid_Tipo_Permissao_SubModulo) || !doValida(oid_SubModulo_Permissao) || !doValida(DM_Tipo_Permissao) || !doValida(NM_Tipo_Permissao))
		    throw new Excecoes("Parâmetros incorretos!");

		Tipo_Permissao_SubModuloED ed = new Tipo_Permissao_SubModuloED();
		ed.setOid_Tipo_Permissao_SubModulo(Integer.parseInt(oid_Tipo_Permissao_SubModulo));
		ed.setOid_SubModulo_Permissao(Integer.parseInt(oid_SubModulo_Permissao));
		ed.setDM_Tipo_Permissao(DM_Tipo_Permissao);
		ed.setNM_Tipo_Permissao(NM_Tipo_Permissao);
		
		//*** Valida se o registro não existe para poder alterar
		if (super.doExiste("Tipos_Permissoes_SubModulos",
		        			"oid_SubModulo_Permissao = " +oid_SubModulo_Permissao+
		        			" AND oid_Tipo_Permissao_SubModulo <> "+oid_Tipo_Permissao_SubModulo+
		        			" AND DM_Tipo_Permissao = '"+DM_Tipo_Permissao+"'" +
							" OR NM_Tipo_Permissao = '"+NM_Tipo_Permissao+"'")) {
		    throw new Mensagens("Já existe um Tipo de Permissão com os dados informados!");
		}
		
		new Tipo_Permissao_SubModuloRN().altera(ed);
	}

	public void deleta(HttpServletRequest request) throws Excecoes {

		String oid_Tipo_Permissao_SubModulo = request.getParameter("oid_Tipo_Permissao_SubModulo");
		//*** Validações
		if (!doValida(oid_Tipo_Permissao_SubModulo))
		    throw new Excecoes("Parâmetros incorretos!");
		
		new Tipo_Permissao_SubModuloRN().deleta(new Tipo_Permissao_SubModuloED(Integer.parseInt(oid_Tipo_Permissao_SubModulo)));
	}

	public ArrayList lista(HttpServletRequest request) throws Excecoes {

	    String oid_Tipo_Permissao_SubModulo = request.getParameter("oid_Tipo_Permissao_SubModulo");
	    String oid_SubModulo_Permissao = request.getParameter("oid_SubModulo_Permissao");
		String DM_Tipo_Permissao = request.getParameter("FT_DM_Tipo_Permissao");
		String NM_Tipo_Permissao = request.getParameter("FT_NM_Tipo_Permissao");
		
		Tipo_Permissao_SubModuloED ed = new Tipo_Permissao_SubModuloED();
		if (doValida(oid_Tipo_Permissao_SubModulo))
		    ed.setOid_Tipo_Permissao_SubModulo(Integer.parseInt(oid_Tipo_Permissao_SubModulo));
		if (doValida(oid_SubModulo_Permissao))
		    ed.setOid_SubModulo_Permissao(Integer.parseInt(oid_SubModulo_Permissao));
		if (doValida(DM_Tipo_Permissao))
		    ed.setDM_Tipo_Permissao(DM_Tipo_Permissao);
		if (doValida(NM_Tipo_Permissao))
		    ed.setNM_Tipo_Permissao(NM_Tipo_Permissao);
		
		return new Tipo_Permissao_SubModuloRN().lista(ed);

	}
	
	public ArrayList listaByOid_SubModulo_Permissao(String oid_SubModulo_Permissao) throws Excecoes {

		if (!doValida(oid_SubModulo_Permissao))
		    throw new Mensagens("SubMódulo não informado!");
		
		Tipo_Permissao_SubModuloED ed = new Tipo_Permissao_SubModuloED();
		ed.setOid_SubModulo_Permissao(Integer.parseInt(oid_SubModulo_Permissao));
		
		return new Tipo_Permissao_SubModuloRN().lista(ed);

	}

	public Tipo_Permissao_SubModuloED getByOid(String oid_Tipo_Permissao_SubModulo) throws Excecoes {

		//*** Validações
		if (!doValida(oid_Tipo_Permissao_SubModulo))
		    throw new Mensagens("ID não informado!");

		return new Tipo_Permissao_SubModuloRN().getByRecord(new Tipo_Permissao_SubModuloED(Integer.parseInt(oid_Tipo_Permissao_SubModulo)));

	}
	
	public Tipo_Permissao_SubModuloED getByDM_Tipo_Permissao(String DM_Tipo_Permissao) throws Excecoes {

		//*** Validações
		if (!doValida(DM_Tipo_Permissao))
		    throw new Mensagens("Tipo de Permissão não informada!");

		Tipo_Permissao_SubModuloED ed = new Tipo_Permissao_SubModuloED();
		ed.setDM_Tipo_Permissao(DM_Tipo_Permissao);
		return new Tipo_Permissao_SubModuloRN().getByRecord(ed);

	}
	
    //*** Verifica se registro já existe!
    private boolean doExiste(Tipo_Permissao_SubModuloED ed) throws Excecoes {
        
		//*** Validações
	    String strFrom  = "Tipos_Permissoes_SubModulos";
	    String strWhere = " oid_SubModulo_Permissao = " + ed.getOid_SubModulo_Permissao();
	          strWhere += " AND DM_Tipo_Permissao = '"+ed.getDM_Tipo_Permissao()+"'";
	    
	    return super.doExiste(strFrom, strWhere);
    }
}