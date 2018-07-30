package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Classificacao_FiscalED;
import com.master.rn.Classificacao_FiscalRN;
import com.master.util.Excecoes;

/*
 * Created on 10/09/2004
 */

/**
 * @author Andre Valadas
 */

public class Classificacao_FiscalBean implements Serializable {

	public Classificacao_FiscalED inclui(HttpServletRequest request) throws Excecoes {

		try {

			Classificacao_FiscalED ed = new Classificacao_FiscalED();

			String cd_Reduzido = request.getParameter("FT_CD_Reduzido");
			String cd_Fiscal = request.getParameter("FT_CD_Fiscal");

			//*** Validações
			if ((cd_Reduzido != null && !cd_Reduzido.equals("0") && !cd_Reduzido.equals("") && !cd_Reduzido.equals("null")) && 
			    (cd_Fiscal != null && !cd_Fiscal.equals("0")) && !cd_Fiscal.equals("") && !cd_Fiscal.equals("null")) {

				ed.setCD_Reduzido(cd_Reduzido);
				ed.setCD_Fiscal(cd_Fiscal);

			}

			return new Classificacao_FiscalRN().inclui(ed);
		}

		catch (Excecoes exc) {
			throw exc;
		}
	}

	public void altera(HttpServletRequest request) throws Excecoes {

		try {
			Classificacao_FiscalRN Classificacao_FiscalRN = new Classificacao_FiscalRN();
			Classificacao_FiscalED ed = new Classificacao_FiscalED();

			String oid_Classificacao_Fiscal = request.getParameter("oid_Classificacao_Fiscal");
			String cd_Reduzido = request.getParameter("FT_CD_Reduzido");
			String cd_Fiscal = request.getParameter("FT_CD_Fiscal");

			ed.setOid_Classificacao_Fiscal(new Integer(oid_Classificacao_Fiscal).intValue());
			ed.setCD_Reduzido(cd_Reduzido);
			ed.setCD_Fiscal(cd_Fiscal);

			Classificacao_FiscalRN.altera(ed);

		} catch (Excecoes exc) {
			throw exc;
		}
	}

	public void deleta(HttpServletRequest request) throws Excecoes {

		try {
			Classificacao_FiscalRN Classificacao_FiscalRN = new Classificacao_FiscalRN();
			Classificacao_FiscalED ed = new Classificacao_FiscalED();

			String oid_Classificacao_Fiscal = request.getParameter("oid_Classificacao_Fiscal");

			ed.setOid_Classificacao_Fiscal(new Integer(oid_Classificacao_Fiscal).intValue());

			Classificacao_FiscalRN.deleta(ed);

		} catch (Excecoes exc) {
			throw exc;
		}
	}

	public ArrayList Classificacao_Fiscal_Lista(HttpServletRequest request) throws Excecoes {

		Classificacao_FiscalED ed = new Classificacao_FiscalED();
		
		String CD_Fiscal= request.getParameter("FT_CD_Fiscal");
		if (CD_Fiscal != null)
			ed.setCD_Fiscal(CD_Fiscal);

		return new Classificacao_FiscalRN().lista(ed);

	}

	public Classificacao_FiscalED getByRecord(HttpServletRequest request) throws Excecoes {

		Classificacao_FiscalED ed = new Classificacao_FiscalED();

		String oid_Classificacao_Fiscal = request.getParameter("oid_Classificacao_Fiscal");

		//*** Validações
		if (oid_Classificacao_Fiscal != null && !oid_Classificacao_Fiscal.equals("0") && 
		   !oid_Classificacao_Fiscal.equals("") && !oid_Classificacao_Fiscal.equals("null")) {

			ed.setOid_Classificacao_Fiscal(new Integer(oid_Classificacao_Fiscal).intValue());
		}

		return new Classificacao_FiscalRN().getByRecord(ed);

	}

	public Classificacao_FiscalED getByOidClassificacao_Fiscal(int oid_Classificacao_Fiscal) throws Excecoes {

		Classificacao_FiscalED ed = new Classificacao_FiscalED();

		//*** Validações
		
		return new Classificacao_FiscalRN().getByOidClassificacao_Fiscal(oid_Classificacao_Fiscal);

	}
	
	public Classificacao_FiscalED getByCdReduzido(String cd_Reduzido) throws Excecoes {
		
		//*** Validações
		
		return new Classificacao_FiscalRN().getByCdReduzido(cd_Reduzido);

	}
}