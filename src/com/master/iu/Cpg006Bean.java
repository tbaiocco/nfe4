package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Lote_CompromissoED;
import com.master.rn.Lote_CompromissoRN;
import com.master.util.Excecoes;

public class Cpg006Bean {

	
    public Lote_CompromissoED inclui(HttpServletRequest request) throws Exception {

        Lote_CompromissoED ed = new Lote_CompromissoED();

        ed.setOid_Compromisso(new Integer(request.getParameter("oid_Compromisso")));
        ed.setOid_Lote_Pagamento(new Long(request.getParameter("oid_Lote_Pagamento")).longValue());

        ed.setDt_Pagamento(request.getParameter("FT_DT_Pagamento"));
        ed.setDt_Emissao(request.getParameter("FT_DT_Emissao"));
        ed.setVl_Pagamento(new Double(request.getParameter("FT_VL_Pagamento_Total")));
        ed.setVl_Compromisso(new Double(request.getParameter("FT_VL_Compromisso")));
        ed.setVl_Saldo_Lote_Compromisso(new Double(request.getParameter("FT_VL_Compromisso")));

        String vl_Multa_Pagamento = request.getParameter("FT_VL_Multa_Pagamento");
        if (!vl_Multa_Pagamento.equals(""))
            ed.setVl_Multa_Pagamento(new Double(vl_Multa_Pagamento));

        String vl_Outras_Despesas = request.getParameter("FT_VL_Outras_Despesas");
        if (!vl_Outras_Despesas.equals(""))
            ed.setVl_Outras_Despesas(new Double(vl_Outras_Despesas));

        String vl_Desconto = request.getParameter("FT_VL_Desconto");
        if (!vl_Desconto.equals(""))
            ed.setVl_Desconto(new Double(vl_Desconto));

        String obs = request.getParameter("FT_TX_Observacao");
        if (!obs.equals(""))
            ed.setTx_Observacao(obs);

        double dValSub = ed.getVl_Pagamento().doubleValue();
        double dValComp = ed.getVl_Compromisso().doubleValue();
        ed.setVl_Saldo_Lote_Compromisso(new Double(dValComp - dValSub));

        return new Lote_CompromissoRN().inclui(ed);
    }

    public void altera(HttpServletRequest request) throws Excecoes {

        Lote_CompromissoED ed = new Lote_CompromissoED();

        ed.setOid_Compromisso(new Integer(request.getParameter("oid_Compromisso")));
        ed.setOid_Lote_Compromisso(request.getParameter("oid_Lote_Compromisso"));
        ed.setOid_Lote_Pagamento(new Long(request.getParameter("oid_Lote_Pagamento")).longValue());

        ed.setDt_Pagamento(request.getParameter("FT_DT_Pagamento"));
        ed.setDt_Emissao(request.getParameter("FT_DT_Emissao"));
        ed.setVl_Pagamento(new Double(request.getParameter("FT_VL_Pagamento")));
        ed.setVl_Compromisso(new Double(request.getParameter("FT_VL_Compromisso")));

        String vl_Multa_Pagamento = request.getParameter("FT_VL_Multa_Pagamento");
        if (!vl_Multa_Pagamento.equals(""))
            ed.setVl_Multa_Pagamento(new Double(vl_Multa_Pagamento));

        String vl_Outras_Despesas = request.getParameter("FT_VL_Outras_Despesas");
        if (!vl_Outras_Despesas.equals(""))
            ed.setVl_Outras_Despesas(new Double(vl_Outras_Despesas));

        String vl_Desconto = request.getParameter("FT_VL_Desconto");
        if (!vl_Desconto.equals(""))
            ed.setVl_Desconto(new Double(vl_Desconto));

        String obs = request.getParameter("FT_TX_Observacao");
        if (!obs.equals(""))
            ed.setTx_Observacao(obs);

        new Lote_CompromissoRN().altera(ed);
    }

    public void deleta(HttpServletRequest request) throws Excecoes {

        Lote_CompromissoED ed = new Lote_CompromissoED();

        ed.setOid_Lote_Compromisso(request.getParameter("oid_Lote_Compromisso"));
        ed.setOid_Lote_Pagamento(new Long(request.getParameter("oid_Lote_Pagamento")).longValue());

        new Lote_CompromissoRN().deleta(ed);
    }

    public void estorna_Pagamento(HttpServletRequest request) throws Excecoes {

        Lote_CompromissoED ed = new Lote_CompromissoED();

        ed.setOid_Lote_Compromisso(request.getParameter("oid_Lote_Compromisso"));
        ed.setOid_Lote_Pagamento(new Long(request.getParameter("oid_Lote_Pagamento")).longValue());

        new Lote_CompromissoRN().estorna_Pagamento(ed);
    }

    public ArrayList Lote_Compromisso_Lista(HttpServletRequest request) throws Excecoes {

        Lote_CompromissoED ed = new Lote_CompromissoED();

        String oid_Pessoa = request.getParameter("oid_Pessoa");
        String oid_Compromisso = request.getParameter("oid_Compromisso");
        String data_Inicial = request.getParameter("FT_DT_Vencimento_Inicial");
        String data_Final = request.getParameter("FT_DT_Vencimento_Final");
        String nr_Documento = request.getParameter("FT_NR_Documento");
        String oid_Lote_Pagamento = request.getParameter("oid_Lote_Pagamento");

        if (oid_Lote_Pagamento != null && !oid_Lote_Pagamento.equals(""))
        	ed.setOid_Lote_Pagamento(new Long(oid_Lote_Pagamento).longValue());

        if (oid_Compromisso != null && !oid_Compromisso.equals(""))
            ed.setOid_Compromisso(new Integer(oid_Compromisso));

        if (oid_Pessoa != null && !oid_Pessoa.equals(""))
            ed.setOid_Pessoa(oid_Pessoa);

        if (data_Inicial != null && !data_Inicial.equals(""))
            ed.setDt_Pgto_Inicial(data_Inicial);

        if (data_Final != null && !data_Final.equals(""))
            ed.setDt_Pgto_Final(data_Final);

        if (nr_Documento != null && !nr_Documento.equals(""))
            ed.setNr_Documento(nr_Documento);

        return new Lote_CompromissoRN().lista(ed);
        
    }

    public Lote_CompromissoED getByRecord(HttpServletRequest request) throws Excecoes {

        Lote_CompromissoED ed = new Lote_CompromissoED();

        String Oid_Lote_Compromisso = request.getParameter("oid_Lote_Compromisso");
        if (Oid_Lote_Compromisso != null && Oid_Lote_Compromisso.length() > 0) {
            ed.setOid_Lote_Compromisso(Oid_Lote_Compromisso);
        }

        return new Lote_CompromissoRN().getByRecord(ed);
    }
}