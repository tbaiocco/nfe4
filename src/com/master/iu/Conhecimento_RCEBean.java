/*
 * Created on 29/06/2005
 *
 */
package com.master.iu;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Conhecimento_RCEED;
import com.master.rn.Conhecimento_RCERN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

/**
 * @author Tiago
 *
 */
public class Conhecimento_RCEBean extends JavaUtil {
    
    public Conhecimento_RCEED inclui(HttpServletRequest request)
    throws Excecoes {
        String oid_RCE = request.getParameter("oid_RCE");
        String oid_Conhecimento = request.getParameter("oid_Conhecimento");
        
        Conhecimento_RCEED ed = new Conhecimento_RCEED();
        
        if (doValida(oid_RCE)) {
            ed.setOid_RCE(Integer.parseInt(oid_RCE));
        }
        if (doValida(oid_Conhecimento)) {
            ed.setOid_Conhecimento(oid_Conhecimento);
        }
        
        return new Conhecimento_RCERN().inclui(ed);
    }
    
    public Conhecimento_RCEED altera(HttpServletRequest request)
    throws Excecoes {
        String oid_Conhecimento_RCE = request.getParameter("oid_Conhecimento_RCE");
        String oid_RCE = request.getParameter("oid_RCE");
        String oid_Conhecimento = request.getParameter("oid_Conhecimento");
        
        Conhecimento_RCEED ed = new Conhecimento_RCEED();
        
        if (doValida(oid_Conhecimento_RCE)) {
            ed.setOid_Conhecimento_RCE(Integer.parseInt(oid_Conhecimento_RCE));
        }
        if (doValida(oid_RCE)) {
            ed.setOid_RCE(Integer.parseInt(oid_RCE));
        }
        if (doValida(oid_Conhecimento)) {
            ed.setOid_Conhecimento(oid_Conhecimento);
        }
        
        return new Conhecimento_RCERN().altera(ed);
    }
    
    public void delete(HttpServletRequest request)
    throws Excecoes {
        String oid_Conhecimento_RCE = request.getParameter("oid_Conhecimento_RCE");
        
        Conhecimento_RCEED ed = new Conhecimento_RCEED();
        
        if (doValida(oid_Conhecimento_RCE)) {
            ed.setOid_Conhecimento_RCE(Integer.parseInt(oid_Conhecimento_RCE));
        }
        
        new Conhecimento_RCERN().delete(ed);
    }
    
    public List lista(HttpServletRequest request)
    throws Excecoes {
        String oid_Conhecimento_RCE = request.getParameter("oid_Conhecimento_RCE");
        String oid_RCE = request.getParameter("oid_RCE");
        String oid_Conhecimento = request.getParameter("oid_Conhecimento");
        
        Conhecimento_RCEED ed = new Conhecimento_RCEED();
        
        if (doValida(oid_Conhecimento_RCE)) {
            ed.setOid_Conhecimento_RCE(Integer.parseInt(oid_Conhecimento_RCE));
        }
        if (doValida(oid_RCE)) {
            ed.setOid_RCE(Integer.parseInt(oid_RCE));
        }
        if (doValida(oid_Conhecimento)) {
            ed.setOid_Conhecimento(oid_Conhecimento);
        }
        
        return new Conhecimento_RCERN().lista(ed);
    }

    public Conhecimento_RCEED getByOid(int oid) throws Excecoes {
        return new Conhecimento_RCERN().getByOid(oid);
    }
    
    public List getByOid_RCE(int oid_RCE) throws Excecoes {
        return new Conhecimento_RCERN().getByOid_RCE(oid_RCE);
    }

    public List getByOid_Conhecimento(String oid_Conhecimento) throws Excecoes {
        return new Conhecimento_RCERN().getByOid_Conhecimento(oid_Conhecimento);
    }
}
