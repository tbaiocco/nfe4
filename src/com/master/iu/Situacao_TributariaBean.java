package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Situacao_TributariaED;
import com.master.rn.Situacao_TributariaRN;
import com.master.util.Excecoes;

/*
 * Created on 10/09/2004
 */

/**
 * @author Andre Valadas
 */

public class Situacao_TributariaBean implements Serializable {

	public Situacao_TributariaED inclui(HttpServletRequest request) throws Excecoes {

			Situacao_TributariaED ed = new Situacao_TributariaED();

    		String dm_Procedencia = request.getParameter("FT_DM_Procedencia");
			String cd_Tipo = request.getParameter("FT_CD_Tipo");
			String nm_Situacao_Tributaria = request.getParameter("FT_NM_Situacao_Tributaria");
			
			// System.err.println("dm_Procedencia = "+dm_Procedencia+", cd_Tipo= "+cd_Tipo+", nm_Situacao_Tributaria= "+nm_Situacao_Tributaria);
			
			//*** Validações
			if ((cd_Tipo != null && !cd_Tipo.equals("0") && !cd_Tipo.equals("") && !cd_Tipo.equals("null")) && 
			    (nm_Situacao_Tributaria != null && !nm_Situacao_Tributaria.equals("0") && !nm_Situacao_Tributaria.equals("") && !nm_Situacao_Tributaria.equals("null")) &&    
			    (dm_Procedencia != null) && !dm_Procedencia.equals("") && !dm_Procedencia.equals("null")) {

			    //*** CÓDIGO concatenado ***//
			    ed.setCD_Situacao_Tributaria(dm_Procedencia+cd_Tipo);
			    ed.setDM_Procedencia(dm_Procedencia);
			    ed.setCD_Tipo(cd_Tipo);
				ed.setNM_Situacao_Tributaria(nm_Situacao_Tributaria);

			}

			return new Situacao_TributariaRN().inclui(ed);
	}

	public void altera(HttpServletRequest request) throws Excecoes {

			Situacao_TributariaRN Situacao_TributariaRN = new Situacao_TributariaRN();
			Situacao_TributariaED ed = new Situacao_TributariaED();

			String oid_Situacao_Tributaria = request.getParameter("oid_Situacao_Tributaria");
			String dm_Procedencia = request.getParameter("FT_DM_Procedencia");
			String cd_Tipo = request.getParameter("FT_CD_Tipo");
			String nm_Situacao_Tributaria = request.getParameter("FT_NM_Situacao_Tributaria");

			//*** Validações
			if ((oid_Situacao_Tributaria != null && !oid_Situacao_Tributaria.equals("0") && !oid_Situacao_Tributaria.equals("") && !oid_Situacao_Tributaria.equals("null")) && 
			    (dm_Procedencia != null && !dm_Procedencia.equals("") && !dm_Procedencia.equals("null")) && 
			    (cd_Tipo != null && !cd_Tipo.equals("0") && !cd_Tipo.equals("") && !cd_Tipo.equals("null")) &&
			    (nm_Situacao_Tributaria != null && !nm_Situacao_Tributaria.equals("0")&& !nm_Situacao_Tributaria.equals("") && !nm_Situacao_Tributaria.equals("null"))) {

				ed.setOid_Situacao_Tributaria(new Integer(oid_Situacao_Tributaria).intValue());
				//*** CÓDIGO concatenado ***//
			    ed.setCD_Situacao_Tributaria(dm_Procedencia+cd_Tipo);
				ed.setDM_Procedencia(dm_Procedencia);
			    ed.setCD_Tipo(cd_Tipo);
				ed.setNM_Situacao_Tributaria(nm_Situacao_Tributaria);

			} else // System.err.println("ERRO [ALTERA]! Parâmetros incorretos!");

			Situacao_TributariaRN.altera(ed);

	}

	public void deleta(HttpServletRequest request) throws Excecoes {

			Situacao_TributariaRN Situacao_TributariaRN = new Situacao_TributariaRN();
			Situacao_TributariaED ed = new Situacao_TributariaED();

			String oid_Situacao_Tributaria = request.getParameter("oid_Situacao_Tributaria");

			//*** Validações
			if (oid_Situacao_Tributaria != null && !oid_Situacao_Tributaria.equals("0") && 
			   !oid_Situacao_Tributaria.equals("") && !oid_Situacao_Tributaria.equals("null")) {

				ed.setOid_Situacao_Tributaria(new Integer(oid_Situacao_Tributaria).intValue());

			} else // System.err.println("ERRO [DELETA]! Parâmetros incorretos!");

			Situacao_TributariaRN.deleta(ed);
	}

	public ArrayList Lista_Situacao_Tributaria(HttpServletRequest request) throws Excecoes {

		Situacao_TributariaED ed = new Situacao_TributariaED();

		String NM_Situacao_Tributaria	= request.getParameter("FT_NM_Situacao_Tributaria");
		if (NM_Situacao_Tributaria != null)
			ed.setNM_Situacao_Tributaria(NM_Situacao_Tributaria);
		
		return new Situacao_TributariaRN().lista(ed);

	}

	public Situacao_TributariaED getByRecord(HttpServletRequest request) throws Excecoes {

		Situacao_TributariaED ed = new Situacao_TributariaED();

		String oid_Situacao_Tributaria = request.getParameter("oid_Situacao_Tributaria");

		//*** Validações
		if (oid_Situacao_Tributaria != null && !oid_Situacao_Tributaria.equals("0") && 
		   !oid_Situacao_Tributaria.equals("") && !oid_Situacao_Tributaria.equals("null")) {

			ed.setOid_Situacao_Tributaria(new Integer(oid_Situacao_Tributaria).intValue());
		}
		return new Situacao_TributariaRN().getByRecord(ed);
	}

	public Situacao_TributariaED getByOidSituacao_Tributaria(int oid_Situacao_Tributaria) throws Excecoes {

		//*** Validações
		if (oid_Situacao_Tributaria <= 0){			
			// System.err.println("ERRO! OID_Classificacao_Fiscal deve ser maior que ZERO!!!");
		}
		
		return new Situacao_TributariaRN().getByOidSituacao_Tributaria(oid_Situacao_Tributaria);

	}
	
	public Situacao_TributariaED getByCD_Situacao_Tributaria(String cd_Situacao_Tributaria) throws Excecoes {
		
		//*** Validações
		if (cd_Situacao_Tributaria == null || cd_Situacao_Tributaria.equals("0") || cd_Situacao_Tributaria.equals("") || cd_Situacao_Tributaria.equals("null")) {
		    // System.err.println("[getByCdMix] ERRO! CD_MIX deve ser maior que ZERO!!!");	
		} 
		
		return new Situacao_TributariaRN().getByCD_Situacao_Tributaria(cd_Situacao_Tributaria);

	}
	
    // Verifica se registro já existe!
    public boolean doExiste(HttpServletRequest request) throws Excecoes {
        
        Situacao_TributariaED ed = new Situacao_TributariaED();

		String dm_Procedencia = request.getParameter("FT_DM_Procedencia");
		String cd_Tipo = request.getParameter("FT_CD_Tipo");

		//*** Validações
		if ((cd_Tipo != null && !cd_Tipo.equals("0") && !cd_Tipo.equals("") && !cd_Tipo.equals("null")) && 
		    (dm_Procedencia != null) && !dm_Procedencia.equals("") && !dm_Procedencia.equals("null")) {

		    ed.setDM_Procedencia(dm_Procedencia);
		    ed.setCD_Tipo(cd_Tipo);

		}

		return new Situacao_TributariaRN().doExiste(ed);
    }
}