package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.SegmentoED;
import com.master.rn.SegmentoRN;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

/*
 * Created on 15/10/2004
 */

/**
 * @author Andre Valadas
 */
public class SegmentoBean extends JavaUtil implements Serializable {

	public SegmentoED inclui(HttpServletRequest request) throws Excecoes {

		SegmentoED ed = new SegmentoED();

		String cd_Segmento = request.getParameter("FT_CD_Segmento");
		String nm_Segmento = request.getParameter("FT_NM_Segmento");

		//*** Validações
		if (doValida(cd_Segmento)&& doValida(nm_Segmento)) {

			ed.setCd_Segmento(cd_Segmento);
			ed.setNm_Segmento(nm_Segmento);

		} else throw new Excecoes("Parâmetros incorretos!");
		
		//*** Valida se o registro não existe para poder incluir
		if (!this.doExiste(ed)) {
		    return new SegmentoRN().inclui(ed);
		} else {
		    throw new Excecoes("Esse Segmento já existe!");
		}
	}

	public void altera(HttpServletRequest request) throws Excecoes {

		SegmentoED ed = new SegmentoED();

		String oid_Segmento = request.getParameter("oid_Segmento");
		String cd_Segmento = request.getParameter("FT_CD_Segmento");
		String nm_Segmento = request.getParameter("FT_NM_Segmento");

		//*** Validações
		if (doValida(oid_Segmento)&& doValida(cd_Segmento) && doValida(nm_Segmento)) {

			ed.setOid_Segmento(Integer.parseInt(oid_Segmento));
			ed.setCd_Segmento(cd_Segmento);
			ed.setNm_Segmento(nm_Segmento);

		} else throw new Excecoes("Parâmetros incorretos!");
		
		//Válida se pode ser alterado
		new BancoUtil().doValidaUpdate(ed.getOid_Segmento(), ed.getCd_Segmento(), "Segmentos", "oid_segmento", "cd_segmento");
		new SegmentoRN().altera(ed);
	}

	public void deleta(HttpServletRequest request) throws Excecoes {

		SegmentoED ed = new SegmentoED();

		String oid_Segmento = request.getParameter("oid_Segmento");

		//*** Validações
		if (doValida(oid_Segmento)) {

			ed.setOid_Segmento(Integer.parseInt(oid_Segmento));

		} else throw new Excecoes("Parâmetros incorretos!");

		new SegmentoRN().deleta(ed);
	}

	public ArrayList Segmento_Lista(HttpServletRequest request) throws Excecoes {

		SegmentoED ed = new SegmentoED();
		return new SegmentoRN().lista(ed);

	}

	public SegmentoED getByOidSegmento(int oid_Segmento) throws Excecoes {

		//*** Validações
		if (oid_Segmento <= 0){
		    throw new Excecoes("ID do segmento deve ser maior que ZERO!");
		}
		
		return new SegmentoRN().getByOidSegmento(oid_Segmento);

	}
	
	public SegmentoED getByCdSegmento(String cd_Segmento) throws Excecoes {
		
		//*** Validações
		if (!doValida(cd_Segmento)) {
		    throw new Excecoes("Parâmetros incorretos!");	
		}
		
		return new SegmentoRN().getByCdSegmento(cd_Segmento);
	}
	
    //*** Verifica se registro já existe!
    private boolean doExiste(SegmentoED ed) throws Excecoes {
        
		//*** Validações
	    String strFrom  = "Segmentos";
	    String strWhere = " cd_Segmento = '" + ed.getCd_Segmento() +"'";
	    
	    return new BancoUtil().doExiste(strFrom, strWhere);
    }
    
	//*** RELATÓRIOS
    /*public void RelSegmento(HttpServletResponse response) throws Exception {
        new SegmentoRN().RelSegmento(response);
    }*/
}