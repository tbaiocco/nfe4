package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.Fatura_CompromissoED;
import com.master.ed.RelatorioED;
import com.master.rn.Fatura_CompromissoRN;
import com.master.util.EnviaPDF;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;
 
public class Fatura_CompromissoBean extends JavaUtil {

    public Fatura_CompromissoED inclui(HttpServletRequest request) throws Excecoes {

        Fatura_CompromissoED ed = new Fatura_CompromissoED();

        ed.setOid_Pessoa(request.getParameter("oid_Pessoa"));

        ed.setDt_Emissao(request.getParameter("FT_DT_Emissao"));
        ed.setNr_Documento(request.getParameter("FT_NR_Documento"));
        String oid_Compromisso = request.getParameter("oid_Compromisso");
        if (JavaUtil.doValida(oid_Compromisso))
            ed.setOID_Compromisso(new Long(request.getParameter("oid_Compromisso")).longValue());
        ed.setTx_Observacao(request.getParameter("FT_TX_Observacao"));
        ed.setDM_Imprimir(request.getParameter("FT_DM_Imprimir"));
        ed.setOid_Unidade(new Long(request.getParameter("oid_Unidade")));
        ed.setVl_Fatura_Compromisso(new Double(request.getParameter("FT_VL_Fatura_Compromisso")));
        return new Fatura_CompromissoRN().inclui(ed);
    }

    public void altera(HttpServletRequest request) throws Excecoes {

        Fatura_CompromissoED ed = new Fatura_CompromissoED();

        ed.setOid_Fatura_Compromisso(new Integer(request.getParameter("oid_Fatura_Compromisso")));
        ed.setDt_Emissao(request.getParameter("FT_DT_Emissao"));
        ed.setNr_Documento(request.getParameter("FT_NR_Documento"));
        ed.setTx_Observacao(request.getParameter("FT_TX_Observacao"));
        ed.setDM_Imprimir(request.getParameter("FT_DM_Imprimir"));
        ed.setDM_Situacao(request.getParameter("FT_DM_Situacao"));
        String tx_Observacao = request.getParameter("FT_TX_Observacao");
        if (!tx_Observacao.equals(""))
            ed.setTx_Observacao(tx_Observacao);

        new Fatura_CompromissoRN().altera(ed);
    }

    public void incluiCompromissoVinculado(HttpServletRequest request) throws Excecoes {

        Fatura_CompromissoED ed = new Fatura_CompromissoED();

        ed.setOid_Fatura_Compromisso(new Integer(request.getParameter("oid_Fatura_Compromisso")));
        ed.setOID_Compromisso(new Long(request.getParameter("oid_Compromisso")).longValue());
        
        new Fatura_CompromissoRN().incluiCompromissoVinculado(ed);
    }


    public void deleta(HttpServletRequest request) throws Excecoes {
        Fatura_CompromissoED ed = new Fatura_CompromissoED();

        ed.setDM_Situacao(request.getParameter("FT_DM_Situacao"));
        ed.setOid_Fatura_Compromisso(new Integer(request.getParameter("oid_Fatura_Compromisso")));

        new Fatura_CompromissoRN().deleta(ed);
    }

    public void desvinculaTudo(HttpServletRequest request) throws Excecoes {
        Fatura_CompromissoED ed = new Fatura_CompromissoED();

        ed.setOid_Fatura_Compromisso(new Integer(request.getParameter("oid_Fatura_Compromisso")));

        new Fatura_CompromissoRN().desvinculaTudo(ed);
    }


    public void excluiCompromisso(HttpServletRequest request) throws Excecoes {
        Fatura_CompromissoED ed = new Fatura_CompromissoED();

        ed.setOID_Compromisso(new Long(request.getParameter("oid_Compromisso")).longValue());
        ed.setOid_Fatura_Compromisso(new Integer(request.getParameter("oid_Fatura_Compromisso")));

        new Fatura_CompromissoRN().excluiCompromisso(ed);
    }


    public ArrayList Fatura_Compromisso_Lista(HttpServletRequest request) throws Excecoes {

        Fatura_CompromissoED ed = new Fatura_CompromissoED();

        String oid_Pessoa = request.getParameter("oid_Pessoa");
        String NR_Fatura_Compromisso = request.getParameter("FT_NR_Fatura_Compromisso");
        String NR_Compromisso = request.getParameter("FT_NR_Compromisso");
        String NR_Documento = request.getParameter("FT_NR_Documento");
        String oid_Compromisso = request.getParameter("oid_Compromisso");
        String DT_Emissao_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
        String DT_Emissao_Final = request.getParameter("FT_DT_Emissao_Final");
        String nm_Razao_Social = request.getParameter("FT_NM_Razao_Social");
        String Vl_Fatura_Compromisso = request.getParameter("FT_Vl_Fatura_Compromisso");

        if (oid_Pessoa != null && !oid_Pessoa.equals(""))
            ed.setOid_Pessoa(oid_Pessoa);

        if (NR_Compromisso != null && !NR_Compromisso.equals(""))
            ed.setNR_Compromisso(new Integer(NR_Compromisso));

        if (NR_Documento != null && !NR_Documento.equals("") && !NR_Documento.equals("null"))
            ed.setNr_Documento(NR_Documento);

        if (oid_Compromisso != null && !oid_Compromisso.equals("") && !oid_Compromisso.equals("null"))
            ed.setOID_Compromisso(new Long(oid_Compromisso).longValue());

        if (NR_Fatura_Compromisso != null && !NR_Fatura_Compromisso.equals(""))
            ed.setNr_Fatura_Compromisso(new Integer(NR_Fatura_Compromisso));


        if (DT_Emissao_Inicial != null && !DT_Emissao_Inicial.equals(""))
            ed.setDT_Emissao_Inicial(DT_Emissao_Inicial);

        if (DT_Emissao_Final != null && !DT_Emissao_Final.equals(""))
            ed.setDT_Emissao_Final(DT_Emissao_Final);

        if (nm_Razao_Social != null && !nm_Razao_Social.equals(""))
            ed.setNm_Razao_Social(nm_Razao_Social);

        if (Vl_Fatura_Compromisso != null && !Vl_Fatura_Compromisso.equals(""))
            ed.setVl_Fatura_Compromisso(new Double(Vl_Fatura_Compromisso));

        return new Fatura_CompromissoRN().lista(ed);

    }

    public ArrayList listaCompromissoVinculado(HttpServletRequest request) throws Excecoes {

        Fatura_CompromissoED ed = new Fatura_CompromissoED();

        ed.setOid_Fatura_Compromisso(new Integer(request.getParameter("oid_Fatura_Compromisso")));

        return new Fatura_CompromissoRN().listaCompromissoVinculado(ed);

    }

    public Fatura_CompromissoED getByRecord(HttpServletRequest request) throws Excecoes {

        Fatura_CompromissoED ed = new Fatura_CompromissoED();

        String oid_Fatura_Compromisso = request.getParameter("oid_Fatura_Compromisso");
        if (oid_Fatura_Compromisso != null && oid_Fatura_Compromisso.length() > 0 && !oid_Fatura_Compromisso.equals("null")) {
            ed.setOid_Fatura_Compromisso(new Integer(oid_Fatura_Compromisso));
        }
        String NR_Fatura_Compromisso = request.getParameter("FT_NR_Fatura_Compromisso");
        if (NR_Fatura_Compromisso != null && NR_Fatura_Compromisso.length() > 0 && !NR_Fatura_Compromisso.equals("null")) {
            ed.setNr_Fatura_Compromisso(new Integer(NR_Fatura_Compromisso));
        }

        String NR_Documento = request.getParameter("FT_NR_Documento");
        if (NR_Documento != null && NR_Documento.length() > 0 && !NR_Documento.equals("null")) {
            ed.setNr_Documento(NR_Documento);
        }
        return new Fatura_CompromissoRN().getByRecord(ed);
    }
    

    public byte[] imprime_Fatura_Compromisso(HttpServletRequest request, HttpServletResponse Response) throws Excecoes {
        Fatura_CompromissoED ed = new Fatura_CompromissoED();
        
        ed.setDM_Relatorio(request.getParameter("acao"));

        String NR_Fatura_Compromisso = request.getParameter("FT_NR_Fatura_Compromisso");
        if (NR_Fatura_Compromisso != null && !NR_Fatura_Compromisso.equals("") && !NR_Fatura_Compromisso.equals("null"))
            ed.setNr_Fatura_Compromisso(new Integer(NR_Fatura_Compromisso));
        ed.setNR_Fatura_Compromisso_Final(new Integer(NR_Fatura_Compromisso));

        String NR_Fatura_Compromisso_Final = request.getParameter("FT_NR_Fatura_Compromisso_Final");
        if (NR_Fatura_Compromisso_Final != null && !NR_Fatura_Compromisso_Final.equals("") && !NR_Fatura_Compromisso_Final.equals("null"))
            ed.setNR_Fatura_Compromisso_Final(new Integer(NR_Fatura_Compromisso_Final));

        return new Fatura_CompromissoRN().imprime_Fatura_Compromisso(ed);

    }

}