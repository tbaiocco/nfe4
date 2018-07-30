package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Area_VendaED;
import com.master.rn.Area_VendaRN;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

/*
 * Created on 30/09/2004
 */

/**
 * @author Andre Valadas
 */
public class Area_VendaBean extends JavaUtil implements Serializable {
    
	public Area_VendaED inclui(HttpServletRequest request) throws Excecoes {

		try {

			Area_VendaED ed = new Area_VendaED();

			String cd_Area_Venda = request.getParameter("FT_CD_Area_Venda");
			String nm_Area_Venda = request.getParameter("FT_NM_Area_Venda");

			//*** Validações
			if ((cd_Area_Venda != null && !cd_Area_Venda.equals("0") && !cd_Area_Venda.equals("") && !cd_Area_Venda.equals("null"))
				&& (nm_Area_Venda != null && !nm_Area_Venda.equals("0")) && !nm_Area_Venda.equals("") && !nm_Area_Venda.equals("null")) {

				ed.setCD_Area_Venda(cd_Area_Venda);
				ed.setNM_Area_Venda(nm_Area_Venda);

			}

			return new Area_VendaRN().inclui(ed);
		}
		catch (Excecoes exc) {
			throw exc;
		}
	}

	public void altera(HttpServletRequest request) throws Excecoes {

		try {

			Area_VendaED ed = new Area_VendaED();

			String oid_Area_Venda = request.getParameter("oid_Area_Venda");
			String cd_Area_Venda = request.getParameter("FT_CD_Area_Venda");
			String nm_Area_Venda = request.getParameter("FT_NM_Area_Venda");

			//*** Validações
			if ((oid_Area_Venda != null && !oid_Area_Venda.equals("0") && !oid_Area_Venda.equals("") && !oid_Area_Venda.equals("null"))
				&& (cd_Area_Venda != null && !cd_Area_Venda.equals("0") && !cd_Area_Venda.equals("") && !cd_Area_Venda.equals("null"))
				&& (nm_Area_Venda != null && !nm_Area_Venda.equals("0") && !nm_Area_Venda.equals("") && !nm_Area_Venda.equals("null"))) {

				ed.setOid_Area_Venda(new Integer(oid_Area_Venda).intValue());
				ed.setCD_Area_Venda(cd_Area_Venda);
				ed.setNM_Area_Venda(nm_Area_Venda);

			} else // System.err.println("ERRO! Parâmetros incorretos!");

			new Area_VendaRN().altera(ed);

		} catch (Excecoes exc) {
			throw exc;
		}
	}

	public void deleta(HttpServletRequest request) throws Excecoes {

		try {

			Area_VendaED ed = new Area_VendaED();

			String oid_Area_Venda = request.getParameter("oid_Area_Venda");

			//*** Validações
			if (oid_Area_Venda != null && !oid_Area_Venda.equals("0") && 
			   !oid_Area_Venda.equals("") && !oid_Area_Venda.equals("null")) {

				ed.setOid_Area_Venda(new Integer(oid_Area_Venda).intValue());

			} else // System.err.println("ERRO! Parâmetros incorretos!");

			new Area_VendaRN().deleta(ed);

		} catch (Excecoes exc) {
			throw exc;
		}
	}

	public ArrayList Area_Venda_Lista(HttpServletRequest request) throws Excecoes {

		Area_VendaED ed = new Area_VendaED();
		Area_VendaRN Area_VendaRN = new Area_VendaRN();

		return Area_VendaRN.lista(ed);
	}
	
	public Area_VendaED getByRecord(HttpServletRequest request) throws Excecoes {

		Area_VendaED ed = new Area_VendaED();

		String oid_Area_Venda = request.getParameter("oid_Area_Venda");

		//*** Validações
		if (oid_Area_Venda != null && !oid_Area_Venda.equals("0") && 
		   !oid_Area_Venda.equals("") && !oid_Area_Venda.equals("null")) {

			ed.setOid_Area_Venda(new Integer(oid_Area_Venda).intValue());
		}

		return new Area_VendaRN().getByRecord(ed);
	}

	public Area_VendaED getByOidArea_Venda(int oid_Area_Venda) throws Excecoes {

		Area_VendaED ed = new Area_VendaED();

		//*** Validações
		if (oid_Area_Venda <= 0){			
			// System.err.println("ERRO! OID_MIX deve ser maior que ZERO!!!");
		}
		
		return new Area_VendaRN().getByOidArea_Venda(oid_Area_Venda);
	}
	
	public Area_VendaED getByCDArea_Venda(String cd_Area_Venda) throws Excecoes {
		
		//*** Validações
		if (cd_Area_Venda == null || cd_Area_Venda.equals("0") || cd_Area_Venda.equals("") || cd_Area_Venda.equals("null")) {
		    // System.err.println("[getByCdArea_Venda] ERRO! CD_MIX deve ser maior que ZERO!!!");	
		} 
		
		return new Area_VendaRN().getByCDArea_Venda(cd_Area_Venda);
	}
	
    //*** Verifica se registro já existe!
    public boolean doExiste(HttpServletRequest request) throws Excecoes {
        
        String CD_Area_Venda = request.getParameter("FT_CD_Area_Venda");
        
        String strFrom  = "areas_vendas";
        String strWhere = "cd_area_venda = '" +CD_Area_Venda +"'";
        
        return new BancoUtil().doExiste(strFrom, strWhere);
    }
}