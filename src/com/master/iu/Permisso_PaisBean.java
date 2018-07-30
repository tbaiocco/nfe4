/*
 * Created on 12/11/2004
 *
 */
package com.master.iu;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Permisso_PaisED;
import com.master.rn.Permisso_PaisRN;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;

/**
 * @author Tiago Sauter Lauxen
 *
 */
public class Permisso_PaisBean extends JavaUtil implements Serializable {
    public static final String FIELD_oid_Permisso_Pais = "oid_Permisso_Pais";
    public static final String FIELD_oid_Pais_Origem = "oid_Pais_Origem";
    public static final String FIELD_CD_Pais_Origem = "FT_CD_Pais_Origem";
    public static final String FIELD_NM_Pais_Origem = "FT_NM_Pais_Origem";
    public static final String FIELD_oid_Pais_Destino = "oid_Pais_Destino";
    public static final String FIELD_CD_Pais_Destino = "FT_CD_Pais_Destino";
    public static final String FIELD_NM_Pais_Destino = "FT_NM_Pais_Destino";
    public static final String FIELD_oid_aidof = "oid_aidof";
    public static final String FIELD_CD_aidof = "FT_CD_aidof";
    public static final String FIELD_NR_Permisso = "FT_NR_Permisso";
    
	public Permisso_PaisED inclui(HttpServletRequest request) throws Excecoes {
	    String oid_Pais_Origem = request.getParameter(FIELD_oid_Pais_Origem);
	    String oid_Pais_Destino = request.getParameter(FIELD_oid_Pais_Destino);
	    String oid_aidof = request.getParameter(FIELD_oid_aidof);
	    String NR_Permisso = request.getParameter(FIELD_NR_Permisso);
	    //Validações dos campos do request
	    if (doValida(oid_Pais_Origem)
	    && doValida(oid_Pais_Destino)
	    && doValida(oid_aidof)) {
            Permisso_PaisED ed = new Permisso_PaisED(0, 
                    Integer.parseInt(oid_Pais_Origem), 
                    Integer.parseInt(oid_Pais_Destino), 
                    Integer.parseInt(oid_aidof), 
                    NR_Permisso);
    		//*** Valida se o registro já existe
    		if (!doExiste(ed)) {
    		    return new Permisso_PaisRN().inclui(ed);
    		} else {
    		    throw new Mensagens("Já existe um permisso com este país de origem e de destino!");
    		}
        } else throw new Mensagens("Parâmetros inválidos!");
    }

	public void altera(HttpServletRequest request)
	throws Excecoes {
	    String oid_Permisso_Pais = request.getParameter(FIELD_oid_Permisso_Pais);
	    String oid_Pais_Origem = request.getParameter(FIELD_oid_Pais_Origem);
	    String oid_Pais_Destino = request.getParameter(FIELD_oid_Pais_Destino);
	    String oid_aidof = request.getParameter(FIELD_oid_aidof);
	    String NR_Permisso = request.getParameter(FIELD_NR_Permisso);
	    
	    //Validações dos campos do request
	    if (doValida(oid_Permisso_Pais)
	    && doValida(oid_Pais_Origem)
	    && doValida(oid_Pais_Destino)
	    && doValida(oid_aidof)) {
            Permisso_PaisED ed = new Permisso_PaisED(Integer.parseInt(oid_Permisso_Pais), 
                    Integer.parseInt(oid_Pais_Origem), 
                    Integer.parseInt(oid_Pais_Destino), 
                    Integer.parseInt(oid_aidof), 
                    NR_Permisso);
    		//*** Valida se o registro já existe
    		if (!doExiste(ed)) {
    		    new Permisso_PaisRN().altera(ed);
    		} else {
    		    throw new Mensagens("Já existe um permisso com este país de origem e de destino!");
    		}
        } else throw new Mensagens("Parâmetros inválidos!");
	}
	
	public void delete(HttpServletRequest request)
	throws Excecoes {
	    String oid_Permisso_Pais = request.getParameter(FIELD_oid_Permisso_Pais);
	    if (doValida(oid_Permisso_Pais)) {
	        Permisso_PaisED ed = new Permisso_PaisED();
	        ed.setOid_Permisso_Pais(Integer.parseInt(oid_Permisso_Pais));
	        new Permisso_PaisRN().delete(ed);
	    } else throw new Mensagens("Parâmetros inválidos");
	}
	
	public List lista(HttpServletRequest request)
	throws Excecoes {
	    String oid_Permisso_Pais = request.getParameter(FIELD_oid_Permisso_Pais);
	    String oid_Pais_Origem = request.getParameter(FIELD_oid_Pais_Origem);
	    String oid_Pais_Destino = request.getParameter(FIELD_oid_Pais_Destino);
	    String oid_aidof = request.getParameter(FIELD_oid_aidof);
	    String NR_Permisso = request.getParameter(FIELD_NR_Permisso);

	    if (!doValida(oid_Permisso_Pais)) {
	        oid_Permisso_Pais = "0";
	    }
	    if (!doValida(oid_Pais_Origem)) {
	        oid_Pais_Origem = "0";
	    }
	    if (!doValida(oid_Pais_Destino)) {
	        oid_Pais_Destino = "0";
	    }
	    if (!doValida(oid_aidof)) {
	        oid_aidof = "0";
	    }
        
        return new Permisso_PaisRN().lista(new Permisso_PaisED(Integer.parseInt(oid_Permisso_Pais), 
                Integer.parseInt(oid_Pais_Origem), 
                Integer.parseInt(oid_Pais_Destino), 
                Integer.parseInt(oid_aidof), 
                NR_Permisso));
	}
	
	public Permisso_PaisED getByRecord(HttpServletRequest request)
	throws Excecoes {
	    String oid_Permisso_Pais = request.getParameter(FIELD_oid_Permisso_Pais);
	    if (doValida(oid_Permisso_Pais)) {
	        return new Permisso_PaisRN().getByRecord(Integer.parseInt(oid_Permisso_Pais));
	    } else throw new Mensagens("Parâmetros inválidos!");
	}
	
	//*** Verifica se registro já existe!
    private boolean doExiste(Permisso_PaisED ed)
    throws Excecoes {
		//*** Validações
	    String strFrom  = "Permissos_Paises";
	    String strWhere =
	        " oid_Pais_Origem = " + ed.getOid_Pais_Origem() +
	        " and oid_Pais_Destino = " + ed.getOid_Pais_Destino();
	    if (ed.getOid_Permisso_Pais() > 0) {
	        strWhere += " and oid_Permisso_Pais <> " + ed.getOid_Permisso_Pais();
	    }
	    return new BancoUtil().doExiste(strFrom, strWhere);
    }
}
