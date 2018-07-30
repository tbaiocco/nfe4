package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Pedido_Nota_FiscalED;
import com.master.rn.Pedido_Nota_FiscalRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

/**
 * @author André Valadas
 * @serialData 03/02/2005
 * @JavaBean.class Pedido_Nota_FiscalBean
 */
public class Pedido_Nota_FiscalBean extends JavaUtil implements Serializable {

	public Pedido_Nota_FiscalED inclui(HttpServletRequest request) throws Excecoes {

	    String oid_Pedido = request.getParameter("oid_Pedido");
		String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
		String DM_Situacao = request.getParameter("FT_DM_Situacao");

		//*** Validações
		if (!doValida(oid_Pedido) || !doValida(oid_Nota_Fiscal) || !doValida(DM_Situacao)) 
		    throw new Excecoes("Parâmetros incorretos!", this.getClass().getName(), "inclui()");
		    
		Pedido_Nota_FiscalED ed = new Pedido_Nota_FiscalED();
		ed.setOid_Pedido(Integer.parseInt(oid_Pedido));
		ed.setOid_Nota_Fiscal(oid_Nota_Fiscal);
		ed.setDM_Situacao(DM_Situacao);

		return new Pedido_Nota_FiscalRN().inclui(ed);
	}

	public void altera(HttpServletRequest request) throws Excecoes {

		String oid_Pedido_Nota_Fiscal = request.getParameter("oid_Pedido_Nota_Fiscal");
		String DM_Situacao = request.getParameter("FT_DM_Situacao");
		
		//*** Validações
		if (!doValida(oid_Pedido_Nota_Fiscal) || !doValida(DM_Situacao))
		    throw new Excecoes("Parâmetros incorretos!", this.getClass().getName(), "altera()");

		Pedido_Nota_FiscalED ed = new Pedido_Nota_FiscalED();
		ed.setOid_Pedido_Nota_Fiscal(Integer.parseInt(oid_Pedido_Nota_Fiscal));
		ed.setDM_Situacao(DM_Situacao);
		
		new Pedido_Nota_FiscalRN().altera(ed);
	}

	public void deleta(HttpServletRequest request) throws Excecoes {

		String oid_Pedido_Nota_Fiscal = request.getParameter("oid_Pedido_Nota_Fiscal");
		//*** Validações
		if (!doValida(oid_Pedido_Nota_Fiscal))
		    throw new Excecoes("Parâmetros incorretos!", this.getClass().getName(), "deleta()");

		new Pedido_Nota_FiscalRN().deleta(new Pedido_Nota_FiscalED(Integer.parseInt(oid_Pedido_Nota_Fiscal)));
	}

	public ArrayList listaPedido_Nota_Fiscal(HttpServletRequest request) throws Excecoes {

	    String oid_Pedido = request.getParameter("oid_Pedido");
		String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
		String DM_Situacao = request.getParameter("FT_DM_Situacao");
		String DM_Pedido = request.getParameter("FT_DM_Pedido");

		Pedido_Nota_FiscalED ed = new Pedido_Nota_FiscalED();
		//*** Validações
		if (doValida(oid_Pedido))
		    ed.setOid_Pedido(Integer.parseInt(oid_Pedido));
		if (doValida(oid_Nota_Fiscal))
		    ed.setOid_Nota_Fiscal(oid_Nota_Fiscal);
		if (doValida(DM_Situacao))
			ed.setDM_Situacao(DM_Situacao);
		if (doValida(DM_Pedido))
		    ed.edPedido.setDM_Pedido(DM_Pedido);
		
		return new Pedido_Nota_FiscalRN().lista(ed);
	}
}