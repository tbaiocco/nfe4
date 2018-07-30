package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Grupo_Nota_FiscalED;
import com.master.rn.Grupo_Nota_FiscalRN;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;

/**
 * @author André Valadas
 * @serial Grupos Notas Fiscais
 * @serialData 17/06/2005
 */
public class Grupo_Nota_FiscalBean extends JavaUtil implements Serializable {

	public Grupo_Nota_FiscalED inclui(HttpServletRequest request) throws Excecoes {

		String CD_Grupo_Nota_Fiscal = request.getParameter("FT_CD_Grupo_Nota_Fiscal");
		String NM_Grupo_Nota_Fiscal = request.getParameter("FT_NM_Grupo_Nota_Fiscal");
		String NM_Modelos = request.getParameter("FT_NM_Modelos");

		//*** Validações
		if (!doValida(CD_Grupo_Nota_Fiscal))
		    throw new Mensagens("Código Grupo Notas Fiscais não informado!");
		if (!doValida(NM_Grupo_Nota_Fiscal))
		    throw new Mensagens("Nome Grupo Notas Fiscais não informado!");
		if (!doValida(NM_Modelos))
		    throw new Mensagens("Modelos Grupo Notas Fiscais não informado!");

		Grupo_Nota_FiscalED ed = new Grupo_Nota_FiscalED();
		ed.setCD_Grupo_Nota_Fiscal(CD_Grupo_Nota_Fiscal);
		ed.setNM_Grupo_Nota_Fiscal(NM_Grupo_Nota_Fiscal);
		ed.setNM_Modelos(NM_Modelos);

		//*** Valida se o registro não existe para poder incluir
		if (!this.doExiste(ed)) {
		    return new Grupo_Nota_FiscalRN().inclui(ed);
		} else throw new Mensagens("Esse Grupo Notas Fiscais já existe!");
	}

	public void altera(HttpServletRequest request) throws Excecoes {


		String oid_Grupo_Nota_Fiscal = request.getParameter("oid_Grupo_Nota_Fiscal");
		String CD_Grupo_Nota_Fiscal = request.getParameter("FT_CD_Grupo_Nota_Fiscal");
		String NM_Grupo_Nota_Fiscal = request.getParameter("FT_NM_Grupo_Nota_Fiscal");
		String NM_Modelos = request.getParameter("FT_NM_Modelos");

		//*** Validações
		if (!doValida(oid_Grupo_Nota_Fiscal))
		    throw new Mensagens("ID Grupo Notas Fiscais não informado!");
		if (!doValida(CD_Grupo_Nota_Fiscal))
		    throw new Mensagens("Código Grupo Notas Fiscais não informado!");
		if (!doValida(NM_Grupo_Nota_Fiscal))
		    throw new Mensagens("Nome Grupo Notas Fiscais não informado!");
		if (!doValida(NM_Modelos))
		    throw new Mensagens("Modelos Grupo Notas Fiscais não informado!");

		Grupo_Nota_FiscalED ed = new Grupo_Nota_FiscalED();
		ed.setOid_Grupo_Nota_Fiscal(Integer.parseInt(oid_Grupo_Nota_Fiscal));
		ed.setCD_Grupo_Nota_Fiscal(CD_Grupo_Nota_Fiscal);
		ed.setNM_Grupo_Nota_Fiscal(NM_Grupo_Nota_Fiscal);
		ed.setNM_Modelos(NM_Modelos);
		
		//Válida se pode ser alterado
		new BancoUtil().doValidaUpdate(ed.getOid_Grupo_Nota_Fiscal(), ed.getCD_Grupo_Nota_Fiscal(), "Grupos_Notas_Fiscais", "oid_Grupo_Nota_Fiscal", "CD_Grupo_Nota_Fiscal");
		new Grupo_Nota_FiscalRN().altera(ed);
	}

	public void deleta(HttpServletRequest request) throws Excecoes {

		String oid_Grupo_Nota_Fiscal = request.getParameter("oid_Grupo_Nota_Fiscal");
		if (!doValida(oid_Grupo_Nota_Fiscal))
		    throw new Mensagens("ID Grupo Notas Fiscais não informado!");
		
		new Grupo_Nota_FiscalRN().deleta(new Grupo_Nota_FiscalED(Integer.parseInt(oid_Grupo_Nota_Fiscal)));
	}

	public ArrayList lista(HttpServletRequest request) throws Excecoes {

	    String oid_Grupo_Nota_Fiscal = request.getParameter("oid_Grupo_Nota_Fiscal");
	    String CD_Grupo_Nota_Fiscal = request.getParameter("FT_CD_Grupo_Nota_Fiscal");
		String NM_Grupo_Nota_Fiscal = request.getParameter("FT_NM_Grupo_Nota_Fiscal");

		Grupo_Nota_FiscalED ed = new Grupo_Nota_FiscalED();
		if (doValida(oid_Grupo_Nota_Fiscal))
		    ed.setOid_Grupo_Nota_Fiscal(Integer.parseInt(oid_Grupo_Nota_Fiscal));
		if (doValida(CD_Grupo_Nota_Fiscal))
		    ed.setCD_Grupo_Nota_Fiscal(CD_Grupo_Nota_Fiscal);
		if (doValida(NM_Grupo_Nota_Fiscal))
		    ed.setNM_Grupo_Nota_Fiscal(NM_Grupo_Nota_Fiscal);
		
		return new Grupo_Nota_FiscalRN().lista(ed);
	}

	public Grupo_Nota_FiscalED getByOid(String oid_Grupo_Nota_Fiscal) throws Excecoes {

		if (!doValida(oid_Grupo_Nota_Fiscal))
		    throw new Mensagens("ID Grupo Notas Fiscais não informado!");

		return new Grupo_Nota_FiscalRN().getByRecord(new Grupo_Nota_FiscalED(Integer.parseInt(oid_Grupo_Nota_Fiscal)));
	}
	
	public Grupo_Nota_FiscalED getByCDGrupo_Nota_Fiscal(String CD_Grupo_Nota_Fiscal) throws Excecoes {
		
		//*** Validações
		if (!doValida(CD_Grupo_Nota_Fiscal))
		    throw new Mensagens("Código Grupo Notas Fiscais não informado!");
		Grupo_Nota_FiscalED ed = new Grupo_Nota_FiscalED();
		ed.setCD_Grupo_Nota_Fiscal(CD_Grupo_Nota_Fiscal);
		
		return new Grupo_Nota_FiscalRN().getByRecord(ed);
	}

    //*** Verifica se registro já existe!
    private boolean doExiste(Grupo_Nota_FiscalED ed) throws Excecoes {
        
	    return new BancoUtil().doExiste("Grupos_Notas_Fiscais", 
                	            		" CD_Grupo_Nota_Fiscal = '" + ed.getCD_Grupo_Nota_Fiscal() +"'" +
                	            		" OR NM_Grupo_Nota_Fiscal = '"+ed.getNM_Grupo_Nota_Fiscal()+"'");
    }
}