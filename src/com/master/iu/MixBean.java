package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.MixED;
import com.master.rn.MixRN;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

/*
 * Created on 24/08/2004
 */

/**
 * @author Andre Valadas
 */
public class MixBean extends JavaUtil implements Serializable {

	public MixED inclui(HttpServletRequest request) throws Excecoes {

		MixED ed = new MixED();

		String cd_Mix = request.getParameter("FT_CD_Mix");
		String nm_Mix = request.getParameter("FT_NM_Mix");

		//*** Validações
		if (doValida(cd_Mix) && doValida(nm_Mix)) {
		    ed.setCd_Mix(cd_Mix);
			ed.setNm_Mix(nm_Mix);
		} else throw new Excecoes("Falta de parametros!");

		//*** Valida se o registro já existe
		if (!doExiste(ed)) {
		    return new MixRN().inclui(ed);
		} else {
		    throw new Excecoes("Este mix já existe!");
		}
	}

	public void altera(HttpServletRequest request) throws Excecoes {

		MixED ed = new MixED();

		String oid_Mix = request.getParameter("oid_Mix");
		String cd_Mix = request.getParameter("FT_CD_Mix");
		String nm_Mix = request.getParameter("FT_NM_Mix");

		//*** Validações
		if (doValida(oid_Mix) && doValida(cd_Mix) && doValida(nm_Mix)) {

			ed.setOid_Mix(Integer.parseInt(oid_Mix));
			ed.setCd_Mix(cd_Mix);
			ed.setNm_Mix(nm_Mix);

		} else throw new Excecoes("Parâmetros incorretos!");

		//Valida se é possível a atualização do registro
		new BancoUtil().doValidaUpdate(ed.getOid_Mix(), ed.getCd_Mix(), "Mix", "oid_mix", "cd_mix");
		
		new MixRN().altera(ed);
	}

	public void deleta(HttpServletRequest request) throws Excecoes {

	    MixED ed = new MixED();

		String oid_Mix = request.getParameter("oid_Mix");

		//*** Validações
		if (doValida(oid_Mix)) {

			ed.setOid_Mix(Integer.parseInt(oid_Mix));

		} else throw new Excecoes("Parâmetros incorretos!");

		new MixRN().deleta(ed);
	}

	public ArrayList Mix_Lista(HttpServletRequest request) throws Excecoes {

		MixED ed = new MixED();
		return new MixRN().lista(ed);

	}

	public MixED getByRecord(HttpServletRequest request) throws Excecoes {

		MixED ed = new MixED();

		String oid_Mix = request.getParameter("oid_Mix");

		//*** Validações
		if (doValida(oid_Mix)) {
			ed.setOid_Mix(new Integer(oid_Mix).intValue());
		} else throw new Excecoes("Parâmetros incorretos!");

		return new MixRN().getByRecord(ed);

	}

	public MixED getByOidMix(int oid_Mix) throws Excecoes {

		MixED ed = new MixED();

		//*** Validações
		if (oid_Mix > 0) {
		    ed.setOid_Mix(oid_Mix);
		} else throw new Excecoes("Parâmetros incorretos!");
		
		return new MixRN().getByRecord(ed);
	}
	
	public MixED getByCdMix(String cd_Mix) throws Excecoes {
		
		//*** Validações
	    if (!doValida(cd_Mix))
		    throw new Excecoes("Código do Mix não informado!");	
		
		return new MixRN().getByCdMix(cd_Mix);
	}
	
    //*** Verifica se registro já existe!
    private boolean doExiste(MixED ed) throws Excecoes {
        
		//*** Validações
	    String strFrom  = "Mix";
	    String strWhere = " cd_mix = '" + ed.getCd_Mix() +"'";
	    
	    return new BancoUtil().doExiste(strFrom, strWhere);
    }
    
	//*** RELATÓRIOS
    public void RelMix(HttpServletResponse response) throws Exception {
        new MixRN().RelMix(response);
    }
   
}