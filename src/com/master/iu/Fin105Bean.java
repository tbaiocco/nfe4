package com.master.iu;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Documento_Conta_CorrenteED;
import com.master.rn.Documento_Conta_CorrenteRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;


public class Fin105Bean extends JavaUtil {

    /***************************************************************************
     * 
     **************************************************************************/
    public Documento_Conta_CorrenteED inclui(HttpServletRequest request) throws Excecoes {

        Documento_Conta_CorrenteRN Documento_Conta_CorrenteRN = new Documento_Conta_CorrenteRN();
        Documento_Conta_CorrenteED ed = new Documento_Conta_CorrenteED();
        ed.setoid_Conta_Corrente(request.getParameter("oid_Conta_Corrente"));
        ed.setOid_Tipo_Documento(new Integer(request.getParameter("oid_Tipo_Documento")));

        String Nr_Inicial = request.getParameter("FT_NR_Inicial");
        if (!Nr_Inicial.equals("") && Nr_Inicial != null)
            ed.setNr_Inicial(new Long(request.getParameter("FT_NR_Inicial")).longValue());

        String Nr_Final = request.getParameter("FT_NR_Final");
        if (!Nr_Final.equals("") && Nr_Final != null)
            ed.setNr_Final(new Long(request.getParameter("FT_NR_Final")).longValue());

        String Nr_Atual = request.getParameter("FT_NR_Atual");
        if (!Nr_Atual.equals("") && Nr_Atual != null)
            ed.setNr_Atual(new Long(request.getParameter("FT_NR_Atual")).longValue());

        String nm_Serie = request.getParameter("FT_NM_Serie");
        if (nm_Serie != null && !nm_Serie.equals("")) {
            ed.setNm_Serie(nm_Serie);
        }

        return Documento_Conta_CorrenteRN.inclui(ed);

    }

    /***************************************************************************
     * 
     **************************************************************************/
    public void altera(HttpServletRequest request) throws Excecoes {

        Documento_Conta_CorrenteRN Documento_Conta_CorrenteRN = new Documento_Conta_CorrenteRN();
        Documento_Conta_CorrenteED ed = new Documento_Conta_CorrenteED();

        ed.setOid_Documento_Conta_Corrente(new Integer(request.getParameter("oid_Documento_Conta_Corrente")));

        String Nr_Inicial = request.getParameter("FT_NR_Inicial");
        if (!Nr_Inicial.equals("") && Nr_Inicial != null)
            ed.setNr_Inicial(new Long(request.getParameter("FT_NR_Inicial")).longValue());

        String Nr_Final = request.getParameter("FT_NR_Final");
        if (!Nr_Final.equals("") && Nr_Final != null)
            ed.setNr_Final(new Long(request.getParameter("FT_NR_Final")).longValue());

        String Nr_Atual = request.getParameter("FT_NR_Atual");
        if (!Nr_Atual.equals("") && Nr_Atual != null)
            ed.setNr_Atual(new Long(request.getParameter("FT_NR_Atual")).longValue());

        String nm_Serie = request.getParameter("FT_NM_Serie");
        if (nm_Serie != null && !nm_Serie.equals("")) {
            ed.setNm_Serie(nm_Serie);
        }

        String dm_Padrao = request.getParameter("FT_DM_Padrao");
        if (dm_Padrao != null && !dm_Padrao.equals("")) {
            ed.setDm_Padrao(dm_Padrao);
        }
        String oid_Conta_Corrente = request.getParameter("oid_Conta_Corrente");
        if (doValida(oid_Conta_Corrente))
            ed.setoid_Conta_Corrente(oid_Conta_Corrente);

        Documento_Conta_CorrenteRN.altera(ed);
    }

    /***************************************************************************
     * 
     **************************************************************************/
    public void deleta(HttpServletRequest request) throws Excecoes {

        Documento_Conta_CorrenteRN Documento_Conta_Correntern = new Documento_Conta_CorrenteRN();
        Documento_Conta_CorrenteED ed = new Documento_Conta_CorrenteED();

        ed.setOid_Documento_Conta_Corrente(new Integer(request.getParameter("oid_Documento_Conta_Corrente")));

        Documento_Conta_Correntern.deleta(ed);
    }

    /***************************************************************************
     * 
     **************************************************************************/
    public ArrayList Documento_Conta_Corrente_Lista(HttpServletRequest request) throws Excecoes {

        Documento_Conta_CorrenteED ed = new Documento_Conta_CorrenteED();

        String oid_Documento_Conta_Corrente = request.getParameter("oid_Documento_Conta_Corrente");
        String oid_Conta_Corrente = request.getParameter("oid_Conta_Corrente");
        String NM_Tipo_Documento = request.getParameter("FT_NM_Tipo_Documento");

        if (doValida(oid_Documento_Conta_Corrente))
            ed.setOid_Documento_Conta_Corrente(new Integer(oid_Documento_Conta_Corrente));
        if (doValida(oid_Conta_Corrente))
            ed.setoid_Conta_Corrente(oid_Conta_Corrente);
        if (doValida(NM_Tipo_Documento))
            ed.setNm_Tipo_Documento(NM_Tipo_Documento);

        return new Documento_Conta_CorrenteRN().lista(ed);

    }

    /***************************************************************************
     * 
     **************************************************************************/
    public Documento_Conta_CorrenteED getByRecord(HttpServletRequest request) throws Excecoes {

        Documento_Conta_CorrenteED ed = new Documento_Conta_CorrenteED();
        String oid_Documento_Conta_Corrente = request.getParameter("oid_Documento_Conta_Corrente");
        if (doValida(oid_Documento_Conta_Corrente)) {
            ed.setOid_Documento_Conta_Corrente(new Integer(oid_Documento_Conta_Corrente));
        }
        return new Documento_Conta_CorrenteRN().getByRecord(ed);

    }
    
    public Documento_Conta_CorrenteED getByCodigo(String oid_Conta_Corrente, String CD_Documento) throws Excecoes {

        if (!doValida(oid_Conta_Corrente))
            throw new Mensagens("ID Conta Corrente não informado!");
        if (!doValida(CD_Documento))
            throw new Mensagens("Código do Documento não informado!");
        
        Documento_Conta_CorrenteED ed = new Documento_Conta_CorrenteED();
        ed.setoid_Conta_Corrente(oid_Conta_Corrente);
        ed.setCd_Tipo_Documento(CD_Documento);
        return new Documento_Conta_CorrenteRN().getByRecord(ed);

    }

    public void geraRelatorio(HttpServletRequest req) throws Excecoes {
        Documento_Conta_CorrenteED ed = new Documento_Conta_CorrenteED();

        // ed.setCD_Documento_Conta_Corrente(req.getParameter("codigo"));
        // ed.setCD_Remessa(req.getParameter("nome"));

        Documento_Conta_CorrenteRN geRN = new Documento_Conta_CorrenteRN();
        geRN.geraRelatorio(ed);
    }

}
