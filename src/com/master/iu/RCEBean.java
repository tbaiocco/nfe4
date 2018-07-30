/*
 * Created on 28/06/2005
 *
 */
package com.master.iu;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.RCEED;
import com.master.rn.RCERN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;


/**
 * @author Tiago
 *
 */
public class RCEBean extends JavaUtil {
    
    public RCEED inclui(HttpServletRequest request)
    throws Excecoes {
        String NR_RCE = request.getParameter("FT_NR_RCE");
        String oid_Unidade = request.getParameter("oid_Unidade");
        String NM_Destinatario = request.getParameter("FT_NM_Destinatario");
        String DT_Referencia = request.getParameter("FT_DT_Referencia");
        
        RCEED ed = new RCEED();
        if (doValida(NR_RCE)) {
            ed.setOid_RCE(Integer.parseInt(NR_RCE));
        }
        if (doValida(oid_Unidade)) {
            ed.setOid_Unidade(Integer.parseInt(oid_Unidade));
        } else throw new Mensagens("Unidade não informada!");
        if (doValida(NM_Destinatario)) {
            ed.setNM_Destinatario(NM_Destinatario);
        } else throw new Mensagens("Nome do destinatário não informado!");
        if (doValida(DT_Referencia)) {
            ed.setDT_Referencia(DT_Referencia);
        } else throw new Mensagens("Data de referência não informada!");
        
        return new RCERN().inclui(ed);
    }
    
    public RCEED altera(HttpServletRequest request)
    throws Excecoes {
        String oid_RCE = request.getParameter("oid_RCE");
        String NR_RCE = request.getParameter("FT_NR_RCE");
        String oid_Unidade = request.getParameter("oid_Unidade");
        String NM_Destinatario = request.getParameter("FT_NM_Destinatario");
        String DT_Lancamento = request.getParameter("FT_DT_Lancamento");
        String DT_Referencia = request.getParameter("FT_DT_Referencia");
        
        RCEED ed = new RCEED();
        if (doValida(oid_RCE)) {
            ed.setOid_RCE(Integer.parseInt(oid_RCE));
        } else throw new Mensagens("RCE não informada!");
        if (doValida(NR_RCE)) {
            ed.setNR_RCE(Integer.parseInt(NR_RCE));
        } else throw new Mensagens("Número não informado!");
        if (doValida(oid_Unidade)) {
            ed.setOid_Unidade(Integer.parseInt(oid_Unidade));
        } else throw new Mensagens("Número não informado!");
        if (doValida(NM_Destinatario)) {
            ed.setNM_Destinatario(NM_Destinatario);
        } else throw new Mensagens("Nome do destinatário não informado!");
        if (doValida(DT_Lancamento)) {
            ed.setDT_Lancamento(DT_Lancamento);
        } else throw new Mensagens("Data de lançamento não informada!");
        if (doValida(DT_Referencia)) {
            ed.setDT_Referencia(DT_Referencia);
        } else throw new Mensagens("Data de referência não informada!");
        
        return new RCERN().altera(ed);
    }
    
    public void delete(HttpServletRequest request)
    throws Excecoes {
        String oid_RCE = request.getParameter("oid_RCE");
        
        RCEED ed = new RCEED();
        if (doValida(oid_RCE)) {
            ed.setOid_RCE(Integer.parseInt(oid_RCE));
        } else throw new Mensagens("RCE não informada!");
        
        new RCERN().delete(ed);
    }
    
    public List lista(HttpServletRequest request)
    throws Excecoes {
        String oid_RCE = request.getParameter("oid_RCE");
        String NR_RCE = request.getParameter("FT_NR_RCE");
        String oid_Unidade = request.getParameter("oid_Unidade");
        String NM_Destinatario = request.getParameter("FT_NM_Destinatario");
        String DT_Lancamento = request.getParameter("FT_DT_Lancamento");
        String DT_Referencia = request.getParameter("FT_DT_Referencia");
        
        RCEED ed = new RCEED();
        if (doValida(oid_RCE)) {
            ed.setOid_RCE(Integer.parseInt(oid_RCE));
        }
        if (doValida(NR_RCE)) {
            ed.setOid_RCE(Integer.parseInt(NR_RCE));
        }
        if (doValida(oid_Unidade)) {
            ed.setOid_Unidade(Integer.parseInt(oid_Unidade));
        }
        if (doValida(NM_Destinatario)) {
            ed.setNM_Destinatario(NM_Destinatario);
        }
        if (doValida(DT_Lancamento)) {
            ed.setDT_Lancamento(DT_Lancamento);
        }
        if (doValida(DT_Referencia)) {
            ed.setDT_Referencia(DT_Referencia);
        }
        
        return new RCERN().lista(ed);
    }

    public RCEED getByRecord(HttpServletRequest request)
    throws Excecoes {
        String oid_RCE = request.getParameter("oid_RCE");
        String NR_RCE = request.getParameter("FT_NR_RCE");
        String oid_Unidade = request.getParameter("oid_Unidade");
        String NM_Destinatario = request.getParameter("FT_NM_Destinatario");
        String DT_Lancamento = request.getParameter("FT_DT_Lancamento");
        String DT_Referencia = request.getParameter("FT_DT_Referencia");
        
        RCEED ed = new RCEED();
        if (doValida(oid_RCE)) {
            ed.setOid_RCE(Integer.parseInt(oid_RCE));
        }
        if (doValida(NR_RCE)) {
            ed.setOid_RCE(Integer.parseInt(NR_RCE));
        }
        if (doValida(oid_Unidade)) {
            ed.setOid_Unidade(Integer.parseInt(oid_Unidade));
        }
        if (doValida(NM_Destinatario)) {
            ed.setNM_Destinatario(NM_Destinatario);
        }
        if (doValida(DT_Lancamento)) {
            ed.setDT_Lancamento(DT_Lancamento);
        }
        if (doValida(DT_Referencia)) {
            ed.setDT_Referencia(DT_Referencia);
        }
        
        return new RCERN().getByRecord(ed);
    }
    
    public RCEED getByOid(int oid) throws Excecoes {
        return new RCERN().getByOid(oid);
    }

    public RCEED getByNumero(int oid_Unidade, int numero) throws Excecoes {
        if (oid_Unidade <= 0) {
            throw new Mensagens("Informe a unidade!");
        }
        return new RCERN().getByNumero(oid_Unidade, numero);
    }
    
    public void relRCE(HttpServletRequest request, HttpServletResponse response) throws Excecoes {
        String Relatorio = request.getParameter("Relatorio");
        String oid_RCE = request.getParameter("oid_RCE");
        
        if (!doValida(Relatorio)) {
            throw new Mensagens("Nome do Relatório não informado!");
        }

        RCEED filtro = new RCEED(response, Relatorio);
        if (doValida(oid_RCE)) {
            filtro.setOid_RCE(Integer.parseInt(oid_RCE));
        }
        
        new RCERN().relRCE(filtro);
    }
}
