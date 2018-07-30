/*
 * Created on 25/02/2005
 */
package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Usuario_SubModulo_PermissaoED;
import com.master.rn.Usuario_SubModulo_PermissaoRN;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.Mensagens;

/**
 * @author Andr� Valadas
 */
public class Usuario_SubModulo_PermissaoBean extends BancoUtil implements Serializable {

	public Usuario_SubModulo_PermissaoED inclui(HttpServletRequest request) throws Excecoes {

		String oid_Usuario_SubModulo = request.getParameter("oid_Usuario_SubModulo");
		String oid_Tipo_Permissao_SubModulo = request.getParameter("oid_Tipo_Permissao_SubModulo");

		//*** Valida��es
		if (!doValida(oid_Usuario_SubModulo) || !doValida(oid_Tipo_Permissao_SubModulo))
		    throw new Excecoes("Par�metros incorretos!");

		Usuario_SubModulo_PermissaoED ed = new Usuario_SubModulo_PermissaoED();
		ed.setOid_Usuario_SubModulo(Integer.parseInt(oid_Usuario_SubModulo));
		ed.setOid_Tipo_Permissao_SubModulo(Integer.parseInt(oid_Tipo_Permissao_SubModulo));
		
		//*** Valida se o registro n�o existe para poder incluir
		if (!this.doExiste(ed)) {
		    return new Usuario_SubModulo_PermissaoRN().inclui(ed);
		} else throw new Mensagens("Essa Permiss�o j� existe para esse SubM�dulo desse Usu�rio!");
	}

	public void altera(HttpServletRequest request) throws Excecoes {

		String oid_Usuario_SubModulo_Permissao = request.getParameter("oid_Usuario_SubModulo_Permissao");
		String oid_Tipo_Permissao_SubModulo = request.getParameter("oid_Tipo_Permissao_SubModulo");

		//*** Valida��es
		if (!doValida(oid_Usuario_SubModulo_Permissao) || !doValida(oid_Tipo_Permissao_SubModulo))
		    throw new Excecoes("Par�metros incorretos!");

		Usuario_SubModulo_PermissaoED ed = new Usuario_SubModulo_PermissaoED();
		ed.setOid_Usuario_SubModulo_Permissao(Integer.parseInt(oid_Usuario_SubModulo_Permissao));
		ed.setOid_Tipo_Permissao_SubModulo(Integer.parseInt(oid_Tipo_Permissao_SubModulo));
		
		//V�lida se pode ser alterado
		doValidaUpdate(ed.getOid_Usuario_SubModulo_Permissao(), String.valueOf(ed.getOid_Tipo_Permissao_SubModulo()), "Usuarios_SubModulos_Permissoes", "oid_Usuario_SubModulo_Permissao", "oid_Tipo_Permissao_SubModulo");
		new Usuario_SubModulo_PermissaoRN().altera(ed);
	}

	public void deleta(HttpServletRequest request) throws Excecoes {

		String oid_Usuario_SubModulo_Permissao = request.getParameter("oid_Usuario_SubModulo_Permissao");
		//*** Valida��es
		if (!doValida(oid_Usuario_SubModulo_Permissao))
		    throw new Excecoes("Par�metros incorretos!");
		
		new Usuario_SubModulo_PermissaoRN().deleta(new Usuario_SubModulo_PermissaoED(Integer.parseInt(oid_Usuario_SubModulo_Permissao)));
	}

	public ArrayList lista(HttpServletRequest request) throws Excecoes {

	    String oid_Usuario_SubModulo_Permissao = request.getParameter("oid_Usuario_SubModulo_Permissao");
	    String oid_Usuario_SubModulo = request.getParameter("oid_Usuario_SubModulo");
		String oid_Tipo_Permissao_SubModulo = request.getParameter("oid_Tipo_Permissao_SubModulo");
		String oid_Usuario = request.getParameter("oid_Usuario");
		
		Usuario_SubModulo_PermissaoED ed = new Usuario_SubModulo_PermissaoED();
		if (doValida(oid_Usuario_SubModulo_Permissao))
		    ed.setOid_Usuario_SubModulo_Permissao(Integer.parseInt(oid_Usuario_SubModulo_Permissao));
		if (doValida(oid_Usuario_SubModulo))
		    ed.setOid_Usuario_SubModulo(Integer.parseInt(oid_Usuario_SubModulo));
		if (doValida(oid_Tipo_Permissao_SubModulo))
		    ed.setOid_Tipo_Permissao_SubModulo(Integer.parseInt(oid_Tipo_Permissao_SubModulo));
		if (doValida(oid_Usuario))
		    ed.Usuario_SubModuloED.setOid_Usuario(Integer.parseInt(oid_Usuario));
		
		return new Usuario_SubModulo_PermissaoRN().lista(ed);

	}

	public Usuario_SubModulo_PermissaoED getByOid(String oid_Usuario_SubModulo_Permissao) throws Excecoes {

		//*** Valida��es
		if (!doValida(oid_Usuario_SubModulo_Permissao))
		    throw new Excecoes("ID n�o informado!");

		return new Usuario_SubModulo_PermissaoRN().getByRecord(new Usuario_SubModulo_PermissaoED(Integer.parseInt(oid_Usuario_SubModulo_Permissao)));

	}
	
    //*** Verifica se registro j� existe!
    private boolean doExiste(Usuario_SubModulo_PermissaoED ed) throws Excecoes {
        
		//*** Valida��es
	    String strFrom  = "Usuarios_SubModulos_Permissoes";
	    String strWhere = " oid_Usuario_SubModulo = " + ed.getOid_Usuario_SubModulo() +
	    				  " AND oid_Tipo_Permissao_SubModulo = " + ed.getOid_Tipo_Permissao_SubModulo();
	    return super.doExiste(strFrom, strWhere);
    }
}