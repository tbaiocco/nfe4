package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.DespachanteED;
import com.master.rn.DespachanteRN;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

/*
 * Created on 19/10/2004
 */
/**
 * @author André Valadas
 */
public class DespachanteBean extends JavaUtil implements Serializable {

	public DespachanteED inclui(HttpServletRequest request) throws Excecoes {

		DespachanteED ed = new DespachanteED();

		String oid_Pessoa = request.getParameter("oid_Pessoa");
		String CD_Despachante = request.getParameter("FT_CD_Despachante");
		String DM_Tipo = request.getParameter("FT_DM_Tipo");

		//*** Validações
		if (doValida(oid_Pessoa) && doValida(CD_Despachante) && doValida(DM_Tipo)) {
		    ed.setOid_Pessoa(oid_Pessoa);
			ed.setCD_Despachante(CD_Despachante);
			ed.setDM_Tipo(DM_Tipo);
		} else throw new Excecoes("Parâmetros incorretos!");

		//*** Valida se o registro não existe para poder incluir
		if (!this.doExiste(ed)) {
		    return new DespachanteRN().inclui(ed);
		} else throw new Excecoes("Esse Despachante já existe!");
	}

	public void altera(HttpServletRequest request) throws Excecoes {

		DespachanteED ed = new DespachanteED();

		String oid_Despachante = request.getParameter("oid_Despachante");
		String CD_Despachante = request.getParameter("FT_CD_Despachante");
		String DM_Tipo = request.getParameter("FT_DM_Tipo");

		//*** Validações
		if (!doValida(oid_Despachante) || !doValida(CD_Despachante) || !doValida(DM_Tipo))
		    throw new Excecoes("Parâmetros incorretos!");

		ed.setOid_Despachante(Integer.parseInt(oid_Despachante));
		ed.setCD_Despachante(CD_Despachante);
		ed.setDM_Tipo(DM_Tipo);
		
		//Válida se pode ser alterado
		new BancoUtil().doValidaUpdate(ed.getOid_Despachante(), ed.getCD_Despachante(), "Despachantes", "oid_Despachante", "cd_Despachante");
		new DespachanteRN().altera(ed);
	}

	public void deleta(HttpServletRequest request) throws Excecoes {

		String oid_Despachante = request.getParameter("oid_Despachante");
		//*** Validações
		if (doValida(oid_Despachante)) {
		    new DespachanteRN().deleta(new DespachanteED(Integer.parseInt(oid_Despachante)));
		} else throw new Excecoes("Parâmetros incorretos!");
	}

	public ArrayList listaDespachante(HttpServletRequest request) throws Excecoes {

	    DespachanteED ed = new DespachanteED();
	    String oid_Pessoa = request.getParameter("oid_Pessoa");
	    if (doValida(oid_Pessoa)){
		    ed.setOid_Pessoa(oid_Pessoa);
		}
	    
		return new DespachanteRN().lista(ed);

	}

	public DespachanteED getByRecord(HttpServletRequest request) throws Excecoes {

	    DespachanteED ed = new DespachanteED();

		String oid_Despachante = request.getParameter("oid_Despachante");
		String oid_Pessoa = request.getParameter("oid_Pessoa");
		
		//*** Validações
		if (!doValida(oid_Despachante) && !doValida(oid_Pessoa)){
		    throw new Excecoes("ID do Despachante deve ser maior que ZERO!");
		}
		if (doValida(oid_Despachante)){
		    ed.setOid_Despachante(Integer.parseInt(oid_Despachante));
		}
		if (doValida(oid_Pessoa)){
		    ed.setOid_Pessoa(oid_Pessoa);
		}
		return new DespachanteRN().getByRecord(ed);

	}
	
	public DespachanteED getByOid(String oid_Despachante) throws Excecoes {

		//*** Validações
		if (!doValida(oid_Despachante)){
		    throw new Excecoes("ID do Despachante deve ser maior que ZERO!");
		} else return new DespachanteRN().getByRecord(new DespachanteED(Integer.parseInt(oid_Despachante)));

	}
	
	public DespachanteED getByOid_Pessoa(String oid_Pessoa) throws Excecoes {

		//*** Validações
		if (!doValida(oid_Pessoa)){
		    throw new Excecoes("ID do Pessoa deve ser maior que ZERO!");
		}
		DespachanteED ed = new DespachanteED();
		ed.setOid_Pessoa(oid_Pessoa);
		return new DespachanteRN().getByRecord(ed);

	}
	
	public DespachanteED getByCdDespachante(String cd_Despachante) throws Excecoes {
		
		//*** Validações
		if (!doValida(cd_Despachante)) {
		    throw new Excecoes("Parâmetros incorretos!");	
		}
		
		return new DespachanteRN().getByCdDespachante(cd_Despachante);
	}
	
    //*** Verifica se registro já existe!
    private boolean doExiste(DespachanteED ed) throws Excecoes {
        
		//*** Validações
	    String strFrom  = "Despachantes";
	    String strWhere = " (cd_Despachante = '" + ed.getCD_Despachante() +"'" +
	    				  " OR oid_Pessoa = '"+ed.getOid_Pessoa()+"')" +
	    				  " and dm_tipo = '"+ed.getDM_Tipo()+"'";
	    
	    return new BancoUtil().doExiste(strFrom, strWhere);
    }
}