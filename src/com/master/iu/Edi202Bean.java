package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.EDI_BancoED;
import com.master.rn.EDI_BancoRN;
import com.master.util.Excecoes;
import com.master.util.Utilitaria;

public class Edi202Bean  {


  Utilitaria util = new Utilitaria();

    public void importaRetornoCobranca(HttpServletRequest request) throws Excecoes {

        EDI_BancoED ed = new EDI_BancoED();

        ed.setNM_Arquivo((String)request.getAttribute("FT_NM_Arquivo"));
        //ed.setNM_Arquivo(request.getParameter("FT_NM_Arquivo"));
        ed.setCD_Banco(request.getParameter("FT_CD_Banco"));
        ed.setOID_Banco((new Long(request.getParameter("oid_Banco")).longValue()));

        // System.out.println("inicio importacao arquivo " + ed.getNM_Arquivo());
        // System.out.println("inicio importacao banco " + ed.getOID_Banco());

        new EDI_BancoRN().importaRetornoCobranca(ed);
    }

    public void atualizaRetornoCobranca(HttpServletRequest request) throws Excecoes {

        EDI_BancoED ed = new EDI_BancoED();

        ed.setOID_Banco((new Long(request.getParameter("oid_Banco")).longValue()));
        String dt_Importacao = request.getParameter("FT_DT_Importacao");

        if (util.doValida(dt_Importacao))
            ed.setDT_Importacao(dt_Importacao);

        // System.out.println("inicio importacao banco " + ed.getOID_Banco());

        new EDI_BancoRN().atualizaRetornoCobranca(ed);
    }

    public ArrayList listaRetornoCobranca(HttpServletRequest request) throws Excecoes {

        EDI_BancoED ed = new EDI_BancoED();

        String oid_Duplicata = request.getParameter ("oid_Duplicata");
        if (String.valueOf (oid_Duplicata) != null &&
            !String.valueOf (oid_Duplicata).equals ("") &&
            !String.valueOf (oid_Duplicata).equals ("null")) {
          ed.setOid_Duplicata (new Long (oid_Duplicata).longValue ());
        }
        String oid_Banco = request.getParameter ("oid_Banco");
        if (String.valueOf (oid_Banco) != null &&
            !String.valueOf (oid_Banco).equals ("") &&
            !String.valueOf (oid_Banco).equals ("null")) {
          ed.setOID_Banco (new Long (oid_Banco).longValue ());
        }

        ed.setDM_Situacao(request.getParameter("FT_DM_Situacao"));
        String dt_Importacao = request.getParameter("FT_DT_Importacao");

        if (util.doValida(dt_Importacao))
            ed.setDT_Importacao(dt_Importacao);
        return new EDI_BancoRN().listaRetornoCobranca(ed);
    }

    public void atualizaRemessaCobranca(HttpServletRequest request) throws Excecoes {

        EDI_BancoED ed = new EDI_BancoED();

        String oid_Carteira = request.getParameter("oid_Carteira");
        String NR_Remessa = request.getParameter("FT_NR_Remessa");
        String DM_Tipo_Emissao = request.getParameter("FT_DM_Tipo_Emissao");
        String CD_Banco = request.getParameter("FT_CD_Banco");
        if (util.doValida (CD_Banco))
          ed.setCD_Banco (request.getParameter ("FT_CD_Banco"));

        if (util.doValida(oid_Carteira))
            ed.setOid_Carteira(new Integer(request.getParameter("oid_Carteira")));
        if (util.doValida(NR_Remessa))
            ed.setNr_Remessa(new Long(request.getParameter("FT_NR_Remessa")).longValue());
        if (util.doValida(DM_Tipo_Emissao))
            ed.setDM_Tipo_Emissao(request.getParameter("FT_DM_Tipo_Emissao"));

        String dt_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
        if (dt_Inicial != null && !dt_Inicial.equals ("") && !dt_Inicial.equals ("null"))
          ed.setDT_Inicial (dt_Inicial);
      
        String dt_Final = request.getParameter ("FT_DT_Emissao_Final");
        if (dt_Final != null && !dt_Final.equals ("") && !dt_Final.equals ("null"))
          ed.setDT_Final (dt_Final);

        String NR_Autorizacao = request.getParameter ("FT_NR_Autorizacao");
        if (NR_Autorizacao != null && !NR_Autorizacao.equals ("") && !NR_Autorizacao.equals ("null"))
          ed.setNR_Autorizacao (NR_Autorizacao);


        new EDI_BancoRN().atualizaRemessaCobranca(ed);
    }


    public ArrayList geraRemessaCobranca(HttpServletRequest request) throws Excecoes {

        EDI_BancoED ed = new EDI_BancoED();

        String oid_Carteira = request.getParameter("oid_Carteira");
        String NR_Remessa = request.getParameter("FT_NR_Remessa");
        String CD_Banco = request.getParameter("FT_CD_Banco");
        String DM_Tipo_Emissao = request.getParameter("FT_DM_Tipo_Emissao");

        if (util.doValida (oid_Carteira))
          ed.setOid_Carteira (new Integer (request.getParameter ("oid_Carteira")));
        if (util.doValida (NR_Remessa))
          ed.setNr_Remessa (new Long (request.getParameter ("FT_NR_Remessa")).longValue ());
        if (util.doValida (DM_Tipo_Emissao))
          ed.setDM_Tipo_Emissao (request.getParameter ("FT_DM_Tipo_Emissao"));
        if (util.doValida (CD_Banco))
          ed.setCD_Banco (request.getParameter ("FT_CD_Banco"));
      
        String dt_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
        if (dt_Inicial != null && !dt_Inicial.equals ("") && !dt_Inicial.equals ("null"))
          ed.setDT_Inicial (dt_Inicial);
      
        String dt_Final = request.getParameter ("FT_DT_Emissao_Final");
        if (dt_Final != null && !dt_Final.equals ("") && !dt_Final.equals ("null"))
          ed.setDT_Final (dt_Final);

        String NR_Autorizacao = request.getParameter ("FT_NR_Autorizacao");
        if (NR_Autorizacao != null && !NR_Autorizacao.equals ("") && !NR_Autorizacao.equals ("null"))
          ed.setNR_Autorizacao (NR_Autorizacao);

        return new EDI_BancoRN().geraRemessaCobranca(ed);
    }
    
    public ArrayList geraPreviaRemessa(HttpServletRequest request) throws Excecoes {

        EDI_BancoED ed = new EDI_BancoED();

        String oid_Carteira = request.getParameter("oid_Carteira");
        String NR_Remessa = request.getParameter("FT_NR_Remessa");
        String NR_Duplicata = request.getParameter("FT_NR_Duplicata");
        String CD_Banco = request.getParameter("FT_CD_Banco");
        String DM_Tipo_Emissao = request.getParameter("FT_DM_Tipo_Emissao");

        if (util.doValida (NR_Duplicata))
        ed.setNR_Duplicata(new Long (NR_Duplicata).longValue ());        
        
        if (util.doValida (oid_Carteira))
          ed.setOid_Carteira (new Integer (request.getParameter ("oid_Carteira")));
        if (util.doValida (NR_Remessa))
          ed.setNr_Remessa (new Long (request.getParameter ("FT_NR_Remessa")).longValue ());
        if (util.doValida (DM_Tipo_Emissao))
          ed.setDM_Tipo_Emissao (request.getParameter ("FT_DM_Tipo_Emissao"));
        if (util.doValida (CD_Banco))
          ed.setCD_Banco (request.getParameter ("FT_CD_Banco"));
      
        String dt_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
        if (dt_Inicial != null && !dt_Inicial.equals ("") && !dt_Inicial.equals ("null"))
          ed.setDT_Inicial (dt_Inicial);
      
        String dt_Final = request.getParameter ("FT_DT_Emissao_Final");
        if (dt_Final != null && !dt_Final.equals ("") && !dt_Final.equals ("null"))
          ed.setDT_Final (dt_Final);

        String NR_Autorizacao = request.getParameter ("FT_NR_Autorizacao");
        if (NR_Autorizacao != null && !NR_Autorizacao.equals ("") && !NR_Autorizacao.equals ("null"))
          ed.setNR_Autorizacao (NR_Autorizacao);

        return new EDI_BancoRN().geraPreviaRemessa(ed);
    }

}
