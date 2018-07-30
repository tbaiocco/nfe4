package com.master.iu;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Calcula_FreteCRTED;
import com.master.ed.Conhecimento_InternacionalED;
import com.master.rn.Calcula_FreteCRTRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;

public class Calcula_FreteCRTBean extends JavaUtil implements Serializable {

    public Calcula_FreteCRTED calcula_frete_crt(HttpServletRequest request)
 	throws Excecoes{
 	    String oid_Conhecimento = request.getParameter("oid_Conhecimento");

 	    Conhecimento_InternacionalED ed = new Conhecimento_InternacionalED();
 	    if (JavaUtil.doValida(oid_Conhecimento)) {
 	        ed.setOID_Conhecimento(oid_Conhecimento);
 	    } else {
 	        throw new Mensagens("Conhecimento não informado!");
 	    }
 	    
 	    return new Calcula_FreteCRTRN().calcula_frete_crt(ed);
 	}
}