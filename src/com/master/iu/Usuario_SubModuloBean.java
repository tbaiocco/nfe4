/*
 * Created on 25/02/2005
 */
package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Usuario_SubModuloED;
import com.master.rn.Usuario_SubModuloRN;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.Mensagens;

/**
 * @author André Valadas
 */
public class Usuario_SubModuloBean extends BancoUtil implements Serializable {

	public Usuario_SubModuloED inclui(HttpServletRequest request) throws Excecoes {

		String oid_Usuario = request.getParameter("oid_Usuario");
		String oid_SubModulo_Permissao = request.getParameter("oid_SubModulo_Permissao");

		//*** Validações
		if (!doValida(oid_Usuario) || !doValida(oid_SubModulo_Permissao))
		    throw new Excecoes("Parâmetros incorretos!");

		Usuario_SubModuloED ed = new Usuario_SubModuloED();
		ed.setOid_Usuario(Integer.parseInt(oid_Usuario));
		ed.setOid_SubModulo_Permissao(Integer.parseInt(oid_SubModulo_Permissao));
		
		//*** Valida se o registro não existe para poder incluir
		if (!this.doExiste(ed)) {
		    return new Usuario_SubModuloRN().inclui(ed);
		} else throw new Mensagens("Esse SubModulo já existe para esse Usuário!");
	}

	public void altera(HttpServletRequest request) throws Excecoes {

		String oid_Usuario_SubModulo = request.getParameter("oid_Usuario_SubModulo");
		String oid_SubModulo_Permissao = request.getParameter("oid_SubModulo_Permissao");

		//*** Validações
		if (!doValida(oid_Usuario_SubModulo) || !doValida(oid_SubModulo_Permissao))
		    throw new Excecoes("Parâmetros incorretos!");

		Usuario_SubModuloED ed = new Usuario_SubModuloED();
		ed.setOid_Usuario_SubModulo(Integer.parseInt(oid_Usuario_SubModulo));
		ed.setOid_SubModulo_Permissao(Integer.parseInt(oid_SubModulo_Permissao));
		
		//Válida se pode ser alterado
		doValidaUpdate(ed.getOid_Usuario_SubModulo(), String.valueOf(ed.getOid_SubModulo_Permissao()), "Usuarios_SubModulos", "oid_Usuario_SubModulo", "oid_SubModulo_Permissao");
		new Usuario_SubModuloRN().altera(ed);
	}

	public void deleta(HttpServletRequest request) throws Excecoes {

		String oid_Usuario_SubModulo = request.getParameter("oid_Usuario_SubModulo");
		//*** Validações
		if (!doValida(oid_Usuario_SubModulo))
		    throw new Excecoes("Parâmetros incorretos!");
		
		new Usuario_SubModuloRN().deleta(new Usuario_SubModuloED(Integer.parseInt(oid_Usuario_SubModulo)));
	}

	public ArrayList lista(HttpServletRequest request) throws Excecoes {

	    String oid_Usuario_SubModulo = request.getParameter("oid_Usuario_SubModulo");
	    String oid_Usuario = request.getParameter("oid_Usuario");
		String oid_SubModulo_Permissao = request.getParameter("oid_SubModulo_Permissao");
		String CD_Modulo = request.getParameter("FT_CD_Modulo");
		
		Usuario_SubModuloED ed = new Usuario_SubModuloED();
		if (doValida(oid_Usuario_SubModulo))
		    ed.setOid_Usuario_SubModulo(Integer.parseInt(oid_Usuario_SubModulo));
		if (doValida(oid_Usuario))
		    ed.setOid_Usuario(Integer.parseInt(oid_Usuario));
		if (doValida(oid_SubModulo_Permissao))
		    ed.setOid_SubModulo_Permissao(Integer.parseInt(oid_SubModulo_Permissao));
		if (doValida(CD_Modulo))
		    ed.SubModulo_PermissaoED.setCD_Modulo(CD_Modulo);
		
		return new Usuario_SubModuloRN().lista(ed);

	}

	public Usuario_SubModuloED getByOid(String oid_Usuario_SubModulo) throws Excecoes {

		//*** Validações
		if (!doValida(oid_Usuario_SubModulo))
		    throw new Mensagens("ID não informado!");

		return new Usuario_SubModuloRN().getByRecord(new Usuario_SubModuloED(Integer.parseInt(oid_Usuario_SubModulo)));

	}
	
	public Usuario_SubModuloED getByRecord(HttpServletRequest request) throws Excecoes {

	    String oid_Usuario = request.getParameter("oid_Usuario");
		String CD_Modulo = request.getParameter("FT_CD_Modulo");
		
		Usuario_SubModuloED ed = new Usuario_SubModuloED();
		if (doValida(oid_Usuario))
		    ed.setOid_Usuario(Integer.parseInt(oid_Usuario));
		if (doValida(CD_Modulo))
		    ed.SubModulo_PermissaoED.setCD_Modulo(CD_Modulo);

		return new Usuario_SubModuloRN().getByRecord(ed);

	}
	
    //*** Verifica se registro já existe!
    private boolean doExiste(Usuario_SubModuloED ed) throws Excecoes {
        
		//*** Validações
	    String strFrom  = "Usuarios_SubModulos";
	    String strWhere = " oid_Usuario = " + ed.getOid_Usuario() +
	    				  " AND oid_SubModulo_Permissao = '" + ed.getOid_SubModulo_Permissao() +"'";
	    return super.doExiste(strFrom, strWhere);
    }
}