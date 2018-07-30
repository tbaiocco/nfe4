/*
 * Created on 25/02/2005
 */
package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.SubModulo_PermissaoED;
import com.master.rn.SubModulo_PermissaoRN;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.Mensagens;

/**
 * @author Andr� Valadas
 */
public class SubModulo_PermissaoBean extends BancoUtil implements Serializable {

	public SubModulo_PermissaoED inclui(HttpServletRequest request) throws Excecoes {

	    boolean addPermissoes = new Boolean(request.getParameter("AddPermissoes")).booleanValue(); //*** Se deve incluir as Opera��es Basicas 1-Incluir 2-Alterar...
		String CD_Modulo = request.getParameter("FT_CD_Modulo");
		String CD_SubModulo = request.getParameter("FT_CD_SubModulo");
		String NM_SubModulo = request.getParameter("FT_NM_SubModulo");

		//*** Valida��es
		if (!doValida(CD_Modulo) || !doValida(CD_SubModulo) || !doValida(NM_SubModulo))
		    throw new Excecoes("Par�metros incorretos!");

		SubModulo_PermissaoED ed = new SubModulo_PermissaoED();
		ed.setCD_Modulo(CD_Modulo);
		ed.setCD_SubModulo(CD_SubModulo);
		ed.setNM_SubModulo(NM_SubModulo);
		
		//*** Valida se o registro n�o existe para poder incluir
		if (!this.doExiste(ed)) {
		    return new SubModulo_PermissaoRN().inclui(ed, addPermissoes);
		} else throw new Mensagens("Esse SubModulo j� existe!");
	}

	public void altera(HttpServletRequest request) throws Excecoes {

		String oid_SubModulo_Permissao = request.getParameter("oid_SubModulo_Permissao");
		String CD_SubModulo = request.getParameter("FT_CD_SubModulo");
		String NM_SubModulo = request.getParameter("FT_NM_SubModulo");

		//*** Valida��es
		if (!doValida(oid_SubModulo_Permissao) || !doValida(CD_SubModulo) || !doValida(NM_SubModulo))
		    throw new Excecoes("Par�metros incorretos!");

		SubModulo_PermissaoED ed = new SubModulo_PermissaoED();
		ed.setOid_SubModulo_Permissao(Integer.parseInt(oid_SubModulo_Permissao));
		ed.setCD_SubModulo(CD_SubModulo);
		ed.setNM_SubModulo(NM_SubModulo);
		
		//V�lida se pode ser alterado
		doValidaUpdate(ed.getOid_SubModulo_Permissao(), ed.getCD_SubModulo(), "SubModulos_Permissoes", "oid_SubModulo_Permissao", "CD_SubModulo");
		new SubModulo_PermissaoRN().altera(ed);
	}

	public void deleta(HttpServletRequest request) throws Excecoes {

		String oid_SubModulo_Permissao = request.getParameter("oid_SubModulo_Permissao");
		//*** Valida��es
		if (!doValida(oid_SubModulo_Permissao))
		    throw new Excecoes("Par�metros incorretos!");
		
		new SubModulo_PermissaoRN().deleta(new SubModulo_PermissaoED(Integer.parseInt(oid_SubModulo_Permissao)));
	}

	public ArrayList lista(HttpServletRequest request) throws Excecoes {

	    String oid_SubModulo_Permissao = request.getParameter("oid_SubModulo_Permissao");
	    String CD_Modulo = request.getParameter("FT_CD_Modulo");
		String CD_SubModulo = request.getParameter("FT_CD_SubModulo");
		String NM_SubModulo = request.getParameter("FT_NM_SubModulo");
		
		SubModulo_PermissaoED ed = new SubModulo_PermissaoED();
		if (doValida(oid_SubModulo_Permissao))
		    ed.setOid_SubModulo_Permissao(Integer.parseInt(oid_SubModulo_Permissao));
		if (doValida(CD_Modulo))
		    ed.setCD_Modulo(CD_Modulo);
		if (doValida(CD_SubModulo))
		    ed.setCD_SubModulo(CD_SubModulo);
		if (doValida(NM_SubModulo))
		    ed.setNM_SubModulo(NM_SubModulo);
		
		return new SubModulo_PermissaoRN().lista(ed);

	}
	
	public SubModulo_PermissaoED getByOid(String oid_SubModulo_Permissao) throws Excecoes {

		//*** Valida��es
		if (!doValida(oid_SubModulo_Permissao))
		    throw new Excecoes("ID n�o informado!");

		return new SubModulo_PermissaoRN().getByRecord(new SubModulo_PermissaoED(Integer.parseInt(oid_SubModulo_Permissao)));

	}
	
	public SubModulo_PermissaoED getByCD_SubModulo(String CD_SubModulo) throws Excecoes {

		//*** Valida��es
		if (!doValida(CD_SubModulo))
		    throw new Mensagens("SubM�dulo n�o informado!");

		SubModulo_PermissaoED ed = new SubModulo_PermissaoED();
		ed.setCD_SubModulo(CD_SubModulo);
		return new SubModulo_PermissaoRN().getByRecord(ed);
	}
	
    //*** Verifica se registro j� existe!
    private boolean doExiste(SubModulo_PermissaoED ed) throws Excecoes {
        
		//*** Valida��es
	    String strFrom  = "SubModulos_Permissoes";
	    String strWhere = " CD_SubModulo = '" + ed.getCD_SubModulo() +"'";
	    if (doValida(ed.getNM_SubModulo()))
	        strWhere += " OR NM_SubModulo = '"+ed.getNM_SubModulo()+"'";
	    
	    return super.doExiste(strFrom, strWhere);
    }
}