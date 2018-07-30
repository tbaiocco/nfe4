package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Area_VendedorED;
import com.master.rn.Area_VendedorRN;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

/*
 * Created on 30/09/2004
 */

/**
 * @author Andre Valadas
 */
public class Area_VendedorBean extends JavaUtil implements Serializable {

	public Area_VendedorED inclui(HttpServletRequest request) throws Excecoes {

		try {

			Area_VendedorED ed = new Area_VendedorED();

			String oid_Area_Venda = request.getParameter("oid_Area_Venda");
			String oid_Vendedor = request.getParameter("oid_Vendedor");
			
			//*** Validações
			if ((oid_Area_Venda != null && !oid_Area_Venda.equals("0") && !oid_Area_Venda.equals("") && !oid_Area_Venda.equals("null")) && 
				(oid_Vendedor != null && !oid_Vendedor.equals("0"))&& !oid_Vendedor.equals("") && !oid_Vendedor.equals("null")) {

				ed.setOid_Area_Venda(Integer.parseInt(oid_Area_Venda));
				ed.setOid_Vendedor(oid_Vendedor);

				return new Area_VendedorRN().inclui(ed);
				
			} else {
			    // System.err.println("ERRO! Falta de parametros!");
				return ed;
			}
		}
		catch (Excecoes exc) {
			throw exc;
		}
	}

	public void altera(HttpServletRequest request) throws Excecoes {

		try {
			Area_VendedorRN Area_VendedorRN = new Area_VendedorRN();
			Area_VendedorED ed = new Area_VendedorED();

			String oid_Area_Vendedor = request.getParameter("oid_Area_Vendedor");
			String oid_Area_Venda = request.getParameter("oid_Area_Venda");
			String oid_Vendedor = request.getParameter("oid_Vendedor");

			//*** Validações
			if ((oid_Area_Vendedor != null && !oid_Area_Vendedor.equals("0") && !oid_Area_Vendedor.equals("") && !oid_Area_Vendedor.equals("null"))&& 
				(oid_Area_Venda != null && !oid_Area_Venda.equals("0") && !oid_Area_Venda.equals("") && !oid_Area_Venda.equals("null"))&& 
				(oid_Vendedor != null && !oid_Vendedor.equals("0") && !oid_Vendedor.equals("") && !oid_Vendedor.equals("null"))) {

				ed.setOid_Area_Vendedor(Integer.parseInt(oid_Area_Vendedor));
				ed.setOid_Area_Venda(Integer.parseInt(oid_Area_Venda));
				ed.setOid_Vendedor(oid_Vendedor);

			} else // System.err.println("ERRO! Parametros incorretos!");

			Area_VendedorRN.altera(ed);

		} catch (Excecoes exc) {
			throw exc;
		}
	}

	public void deleta(HttpServletRequest request) throws Excecoes {

		try {
			Area_VendedorRN Area_VendedorRN = new Area_VendedorRN();
			Area_VendedorED ed = new Area_VendedorED();

			String oid_Area_Vendedor = request.getParameter("oid_Area_Vendedor");

			//*** Validações
			if (oid_Area_Vendedor != null && !oid_Area_Vendedor.equals("0")&& !oid_Area_Vendedor.equals("") &&!oid_Area_Vendedor.equals("null")) {

				ed.setOid_Area_Vendedor(Integer.parseInt(oid_Area_Vendedor));

			} else // System.err.println("ERRO! Parâmetros incorretos!");

			Area_VendedorRN.deleta(ed);

		} catch (Excecoes exc) {
			throw exc;
		}
	}

	public ArrayList Lista_Area_Vendedor(HttpServletRequest request)
			throws Excecoes {

		Area_VendedorED ed = new Area_VendedorED();
		Area_VendedorRN Area_VendedorRN = new Area_VendedorRN();

		String oid_Vendedor = request.getParameter("oid_Vendedor");

		//*** Validações
		if (oid_Vendedor != null && !oid_Vendedor.equals("0")&& !oid_Vendedor.equals("") && !oid_Vendedor.equals("null")) {
			ed.setOid_Vendedor(oid_Vendedor);
		}

		return Area_VendedorRN.lista(ed);
	}

	public Area_VendedorED getByOidArea_Vendedor(int oid_Area_Vendedor) throws Excecoes {

		Area_VendedorED ed = new Area_VendedorED();

		//*** Validações
		if (oid_Area_Vendedor > 0) {
			ed.setOid_Area_Vendedor(oid_Area_Vendedor);
		}		
		return new Area_VendedorRN().getByOidArea_Vendedor(oid_Area_Vendedor);
	}
	
    //*** Verifica se registro já existe!
    public boolean doExiste(HttpServletRequest request) throws Excecoes {
        
        String oid_Area_Venda = request.getParameter("oid_Area_Venda");
        String oid_Vendedor = request.getParameter("oid_Vendedor");
        
        String strFrom  = "areas_vendedores";
        String strWhere = "oid_Area_Venda = " +Integer.parseInt(oid_Area_Venda)+
        			 	  " and oid_Vendedor = '"+oid_Vendedor+"'";
        
        return new BancoUtil().doExiste(strFrom, strWhere);
    }
}