
package com.master.iu;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.OperadorED;
import com.master.rn.OperadorRN;
import com.master.util.Excecoes;

/*
 * Created on 24/08/2004
 */

/**
 * @author Andre Valadas
 */
public class wms001Bean {

    public OperadorED inclui(HttpServletRequest request) throws Excecoes {

        try {
            OperadorRN OperadorRN = new OperadorRN();
            OperadorED ed = new OperadorED();

            ed.setCd_Operador(request.getParameter("FT_CD_Operador"));
            ed.setNm_Operador(request.getParameter("FT_NM_Operador"));
            ed.setDm_Situacao(request.getParameter("FT_DM_Situacao"));

            return OperadorRN.inclui(ed);

        }

        catch (Excecoes exc) {
            throw exc;
        }
    }

    public void altera(HttpServletRequest request) throws Excecoes {

        try {
            OperadorRN OperadorRN = new OperadorRN();
            OperadorED ed = new OperadorED();

            ed.setOid_Operador(new Integer(request.getParameter("oid_Operador")));
            ed.setCd_Operador(request.getParameter("FT_CD_Operador"));
            ed.setNm_Operador(request.getParameter("FT_NM_Operador"));
            ed.setDm_Situacao(request.getParameter("FT_DM_Situacao"));

            OperadorRN.altera(ed);
        } catch (Excecoes exc) {
            throw exc;
        }
    }

    public void deleta(HttpServletRequest request) throws Excecoes {

        try {
            OperadorRN OperadorRN = new OperadorRN();
            OperadorED ed = new OperadorED();

            ed.setOid_Operador(new Integer(request.getParameter("oid_Operador")));

            OperadorRN.deleta(ed);
        } catch (Excecoes exc) {
            throw exc;
        }
    }

    public ArrayList Operador_Lista(HttpServletRequest request)
            throws Excecoes {

        OperadorED ed = new OperadorED();

        String FT_CD_Operador = request.getParameter("FT_CD_Operador");
        String FT_NM_Operador = request.getParameter("FT_NM_Operador");

        // System.out.println("iu perador=" + FT_CD_Operador);     
        
        if (FT_CD_Operador != null && !FT_CD_Operador.equals(""))
            ed.setCd_Operador(FT_CD_Operador);

        if (FT_NM_Operador != null && !FT_NM_Operador.equals(""))
            ed.setNm_Operador(FT_NM_Operador);

        return new OperadorRN().lista(ed);

    }

    public OperadorED getByRecord(HttpServletRequest request) throws Excecoes {

        OperadorED ed = new OperadorED();

        String oid_Operador = request.getParameter("oid_Operador");
        String FT_CD_Operador = request.getParameter("FT_CD_Operador");

        if (oid_Operador != null && oid_Operador.length() > 0) {
            ed.setOid_Operador(new Integer(oid_Operador));
        }
        if (FT_CD_Operador != null && !FT_CD_Operador.equals("0")) {
            ed.setCd_Operador(FT_CD_Operador);
        }

        return new OperadorRN().getByRecord(ed);

    }

}