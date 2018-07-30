package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Conta_CorrenteED;
import com.master.rn.Conta_CorrenteRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

public class Fin009Bean extends JavaUtil {

    /***************************************************************************
     * 
     **************************************************************************/
    public Conta_CorrenteED inclui(HttpServletRequest request) throws Excecoes {

        Conta_CorrenteED ed = new Conta_CorrenteED();

        ed.setCd_Conta_Corrente(request.getParameter("FT_CD_Conta_Corrente"));

        if ("U".equals(ed.getDm_Tipo_Conta_Corrente())) {
            ed.setOid_Unidade(Integer.parseInt(request.getParameter("oid_Unidade")));
        }
        ed.setDm_Tipo_Conta_Corrente(request.getParameter("FT_DM_Tipo_Conta_Corrente"));

        ed.setDm_Controle_Saldo(request.getParameter("FT_DM_Controle_Saldo"));
        ed.setDm_Controle_Cobranca(request.getParameter("FT_DM_Controle_Cobranca"));
        ed.setDm_Contabilizacao(request.getParameter("FT_DM_Contabilizacao"));
        ed.setDm_Grupo(request.getParameter("FT_DM_Grupo"));

        ed.setNr_Agencia(request.getParameter("FT_CD_Agencia"));
        ed.setNr_Conta_Corrente(request.getParameter("FT_NR_Conta_Corrente"));
        ed.setOid_Conta(new Integer(request.getParameter("oid_Conta")));
        ed.setOid_Moeda(new Integer(request.getParameter("oid_Moeda")));
        ed.setOid_Pessoa(request.getParameter("oid_Pessoa"));
        ed.setOid_Empresa(new Integer(request.getParameter("oid_Empresa")));

        String valInicial = request.getParameter("FT_VL_Inicial");
        if (valInicial != null && !valInicial.equals(""))
            ed.setVl_Saldo_Inicial(new Double(valInicial));

        return new Conta_CorrenteRN().inclui(ed);
    }

    /***************************************************************************
     * 
     **************************************************************************/
    public void altera(HttpServletRequest request) throws Excecoes {

        Conta_CorrenteED ed = new Conta_CorrenteED();
        ed.setOid_Empresa(new Integer(request.getParameter("oid_Empresa")));

        ed.setOid_Conta_Corrente(request.getParameter("oid_Conta_Corrente"));
        ed.setCd_Conta_Corrente(request.getParameter("FT_CD_Conta_Corrente"));
        ed.setNr_Agencia(request.getParameter("FT_CD_Agencia"));
        ed.setNr_Conta_Corrente(request.getParameter("FT_NR_Conta_Corrente"));
        ed.setOid_Conta(new Integer(request.getParameter("oid_Conta")));
        ed.setOid_Moeda(new Integer(request.getParameter("oid_Moeda")));
        ed.setOid_Pessoa(request.getParameter("oid_Pessoa"));

        ed.setDm_Controle_Saldo(request.getParameter("FT_DM_Controle_Saldo"));
        ed.setDm_Controle_Cobranca(request.getParameter("FT_DM_Controle_Cobranca"));
        ed.setDm_Contabilizacao(request.getParameter("FT_DM_Contabilizacao"));
        ed.setDm_Grupo(request.getParameter("FT_DM_Grupo"));

        if ("U".equals(ed.getDm_Tipo_Conta_Corrente()) && request.getParameter("oid_Unidade") != null && !request.getParameter("oid_Unidade").equals("")) {
            ed.setOid_Unidade(Integer.parseInt(request.getParameter("oid_Unidade")));
        }

        ed.setVl_Saldo_Inicial(new Double(0));

        new Conta_CorrenteRN().altera(ed);
    }

    /***************************************************************************
     * 
     **************************************************************************/
    public void deleta(HttpServletRequest request) throws Excecoes {

        Conta_CorrenteED ed = new Conta_CorrenteED();

        ed.setOid_Conta_Corrente(request.getParameter("oid_Conta_Corrente"));

        new Conta_CorrenteRN().deleta(ed);
    }

    /***************************************************************************
     * 
     **************************************************************************/
    public ArrayList conta_Corrente_Lista(HttpServletRequest request) throws Excecoes {

        Conta_CorrenteED ed = new Conta_CorrenteED();

        String nr_Conta_Corrente = request.getParameter("FT_NR_Conta_Corrente");
        String cd_Conta_Corrente = request.getParameter("FT_CD_Conta_Corrente");
        String oid_Pessoa = request.getParameter("oid_Pessoa");
        String NM_Razao_Social = request.getParameter("FT_NM_Razao_Social");

        ed.setDm_Tipo_Conta_Corrente(request.getParameter("FT_DM_Tipo_Conta_Corrente"));


        if (nr_Conta_Corrente != null && !nr_Conta_Corrente.equals(""))
            ed.setNr_Conta_Corrente(nr_Conta_Corrente);

        if (cd_Conta_Corrente != null && !cd_Conta_Corrente.equals(""))
            ed.setCd_Conta_Corrente(cd_Conta_Corrente);

        if (oid_Pessoa != null && !oid_Pessoa.equals(""))
            ed.setOid_Pessoa(oid_Pessoa);

        if (NM_Razao_Social != null && !NM_Razao_Social.equals(""))
            ed.setNm_Razao_Social(NM_Razao_Social);

        return new Conta_CorrenteRN().lista(ed);

    }

    /***************************************************************************
     * 
     **************************************************************************/
    public Conta_CorrenteED getByRecord(HttpServletRequest request) throws Excecoes {

        Conta_CorrenteED ed = new Conta_CorrenteED();

        String oid_Conta_Corrente = request.getParameter("oid_Conta_Corrente");
        String cd_Conta_Corrente = request.getParameter("FT_CD_Conta_Corrente");

        if (oid_Conta_Corrente != null && oid_Conta_Corrente.length() > 0) {
            ed.setOid_Conta_Corrente(oid_Conta_Corrente);
        }
        if (cd_Conta_Corrente != null && cd_Conta_Corrente.length() > 0) {
            ed.setCd_Conta_Corrente(cd_Conta_Corrente);
        }
        return new Conta_CorrenteRN().getByRecord(ed);
    }

    public Conta_CorrenteED getByCD_Conta_Corrente(String CD_Conta_Corrente) throws Excecoes {

        Conta_CorrenteED edConta = new Conta_CorrenteED();
        if (doValida(CD_Conta_Corrente)) {
            edConta.setCd_Conta_Corrente(CD_Conta_Corrente);
            return new Conta_CorrenteRN().getByRecord(edConta);
        } else return edConta;
    }

    public Conta_CorrenteED getByRecord(String oid_Conta_Corrente) throws Excecoes {

        if (doValida(oid_Conta_Corrente))
            return new Conta_CorrenteRN().getByRecord(new Conta_CorrenteED(oid_Conta_Corrente));
        else return new Conta_CorrenteED();
    }
}
