package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Ocorrencia_Nota_FiscalED;
import com.master.rn.Ocorrencia_Nota_FiscalRN;
import com.master.util.Excecoes;
import com.master.util.Utilitaria;

public class con006Bean {

    Utilitaria util = new Utilitaria();

    public Ocorrencia_Nota_FiscalED inclui(HttpServletRequest request)throws Excecoes{

    	Ocorrencia_Nota_FiscalED ed = new Ocorrencia_Nota_FiscalED();

    	ed.setDT_Ocorrencia_Nota_Fiscal(request.getParameter("FT_DT_Ocorrencia_Nota_Fiscal"));
    	ed.setHR_Ocorrencia_Nota_Fiscal(request.getParameter("FT_HR_Ocorrencia_Nota_Fiscal"));
    	ed.setOID_Tipo_Ocorrencia(new Long(request.getParameter("oid_Tipo_Ocorrencia")).longValue());
    	ed.setOID_Nota_Fiscal(request.getParameter("oid_Nota_Fiscal"));

    	ed.setTX_Observacao(request.getParameter("FT_TX_Observacao"));
    	if (ed.getTX_Observacao().equals("null") || ed.getTX_Observacao().equals(null) || ed.getTX_Observacao().equals("")) {
    	    ed.setTX_Observacao(" ");
    	}

    	ed.setNM_Pessoa(" ");
    	if (util.doValida(ed.getNM_Pessoa())) {
    	    ed.setNM_Pessoa(request.getParameter("FT_NM_Pessoa"));
    	}

    	ed.setTX_Observacao_Cliente(" ");
    	ed.setDM_Origem_Ocorrencia("I");

    	ed.setDM_Pendencia(request.getParameter("FT_DM_Pendencia"));
      	return new Ocorrencia_Nota_FiscalRN().inclui(ed);
	}

	public void altera(HttpServletRequest request)throws Exception{

	    Ocorrencia_Nota_FiscalED ed = new Ocorrencia_Nota_FiscalED();

	    ed.setOID_Ocorrencia_Nota_Fiscal(new Long(request.getParameter("oid_Ocorrencia_Nota_Fiscal")).longValue());
	    ed.setOID_Tipo_Ocorrencia(new Long(request.getParameter("oid_Tipo_Ocorrencia")).longValue());
	    ed.setDT_Ocorrencia_Nota_Fiscal(request.getParameter("FT_DT_Ocorrencia_Nota_Fiscal"));
	    ed.setHR_Ocorrencia_Nota_Fiscal(request.getParameter("FT_HR_Ocorrencia_Nota_Fiscal"));
	    ed.setTX_Observacao(request.getParameter("FT_TX_Observacao"));
	    if (ed.getTX_Observacao().equals("null") || ed.getTX_Observacao().equals(null) || ed.getTX_Observacao().equals("")) {
	        ed.setTX_Observacao(" ");
	    }

	    new Ocorrencia_Nota_FiscalRN().altera(ed);
    }

	public void deleta(HttpServletRequest request)throws Excecoes{

	    Ocorrencia_Nota_FiscalED ed = new Ocorrencia_Nota_FiscalED();

	    ed.setOID_Ocorrencia_Nota_Fiscal(new Long(request.getParameter("oid_Ocorrencia_Nota_Fiscal")).longValue());

	    new Ocorrencia_Nota_FiscalRN().deleta(ed);
    }

	public ArrayList Ocorrencia_Nota_Fiscal_Lista(HttpServletRequest request)throws Excecoes{

    	Ocorrencia_Nota_FiscalED ed = new Ocorrencia_Nota_FiscalED();

    	String OID_Tipo_Ocorrencia = request.getParameter("oid_Tipo_Ocorrencia");
    	if (util.doValida(OID_Tipo_Ocorrencia)){
    	    ed.setOID_Tipo_Ocorrencia(new Long(OID_Tipo_Ocorrencia).longValue());
    	}
    	ed.setDT_Ocorrencia_Nota_Fiscal(request.getParameter("FT_DT_Ocorrencia_Nota_Fiscal"));
    	ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));

    	String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
    	if (util.doValida(oid_Nota_Fiscal)){
    	    ed.setOID_Nota_Fiscal(oid_Nota_Fiscal);
    	}

    	String DM_Acesso = request.getParameter("FT_DM_Acesso");
    	if (util.doValida(DM_Acesso)){
    	    ed.setDM_Acesso(DM_Acesso);
    	}

    	String nr_Nota_Fiscal = request.getParameter("FT_NR_Nota_Fiscal");
    	if (util.doValida(nr_Nota_Fiscal)){
    	    ed.setNR_Nota_Fiscal(new Long(nr_Nota_Fiscal).longValue());
    	}
    	ed.setOID_Pessoa(request.getParameter("oid_Pessoa_Remetente"));
    	ed.setOID_Pessoa_Destinatario(request.getParameter("oid_Pessoa_Destinatario"));

    	return new Ocorrencia_Nota_FiscalRN().lista(ed);
	}

	public ArrayList Ocorrencia_Nota_Fiscal_Lista_Cliente(HttpServletRequest request)throws Excecoes{

    	Ocorrencia_Nota_FiscalED ed = new Ocorrencia_Nota_FiscalED();

    	String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
    	if (util.doValida(oid_Nota_Fiscal)){
    	    ed.setOID_Nota_Fiscal(oid_Nota_Fiscal);
    	}
    	ed.setDM_Acesso("L");

    	return new Ocorrencia_Nota_FiscalRN().lista(ed);
	}

	public Ocorrencia_Nota_FiscalED getByRecord(HttpServletRequest request)throws Excecoes{

    	Ocorrencia_Nota_FiscalED ed = new Ocorrencia_Nota_FiscalED();

    	String oid_Ocorrencia_Nota_Fiscal = request.getParameter("oid_Ocorrencia_Nota_Fiscal");
    	if (util.doValida(oid_Ocorrencia_Nota_Fiscal)){
    	    ed.setOID_Ocorrencia_Nota_Fiscal(new Long(oid_Ocorrencia_Nota_Fiscal).longValue());
    	}

    	return new Ocorrencia_Nota_FiscalRN().getByRecord(ed);
	}

	public void geraRelatorio(HttpServletRequest req)throws Excecoes{

	    Ocorrencia_Nota_FiscalED ed = new Ocorrencia_Nota_FiscalED();
	    /* * * * * *         A T E N Ç Ã O     * * * * * * *
	    /* SETAR O ED PARA PESQUISA NO BD
	    */
	    new Ocorrencia_Nota_FiscalRN().geraRelatorio(ed);
	}
}
