package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.DespesaED;
import com.master.ed.DespesaPesquisaED;
import com.master.rn.DespesaRN;
import com.master.util.EnviaPDF;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

public class Cpg030Bean extends JavaUtil {

    public DespesaED inclui(HttpServletRequest request) throws Excecoes {

        DespesaRN DespesaRN = new DespesaRN();
        DespesaED ed = new DespesaED();
        ed.setDt_Emissao(request.getParameter("FT_DT_Emissao"));
        ed.setDt_Vencimento(request.getParameter("FT_DT_Vencimento"));
        ed.setNr_Documento(request.getParameter("FT_NR_Documento"));

        ed.setOid_Centro_Custo(new Integer(request.getParameter("oid_Centro_Custo")));
        ed.setOid_Conta(new Integer(request.getParameter("oid_Conta")));
        ed.setOid_Pessoa(request.getParameter("oid_Pessoa"));
        ed.setOid_Tipo_Documento(new Integer(request.getParameter("oid_Tipo_Documento")));
        ed.setOid_Natureza_Operacao(new Integer(request.getParameter("oid_Natureza_Operacao")));
        ed.setOid_Unidade(new Long(request.getParameter("oid_Unidade")));
        ed.setTx_Observacao(request.getParameter("FT_TX_Observacao"));
        ed.setDM_Tipo_Pagamento(request.getParameter("FT_DM_Tipo_Pagamento"));
        ed.setVl_Despesa(new Double(request.getParameter("FT_VL_Despesa")));
        ed.setVl_Compromisso(new Double(request.getParameter("FT_VL_Compromisso")));

        return DespesaRN.inclui(ed);




    }


    public void altera(HttpServletRequest request) throws Excecoes {

        DespesaRN DespesaRN = new DespesaRN();
        DespesaED ed = new DespesaED();

        ed.setOid_Despesa(new Integer(request.getParameter("oid_Despesa")));
        ed.setDt_Emissao(request.getParameter("FT_DT_Emissao"));

        ed.setOid_Conta(new Integer(request.getParameter("oid_Conta")));

        String tx_Observacao = request.getParameter("FT_TX_Observacao");
        if (!tx_Observacao.equals(""))
            ed.setTx_Observacao(tx_Observacao);


        ed.setVl_Despesa(new Double(request.getParameter("FT_VL_Despesa")));


        DespesaRN.altera(ed);
    }


    public void deleta(HttpServletRequest request) throws Excecoes {

        DespesaRN DespesaRN = new DespesaRN();
        DespesaED ed = new DespesaED();

        ed.setOid_Despesa(new Integer(request.getParameter("oid_Despesa")));

        DespesaRN.deleta(ed);
    }


    public ArrayList Despesa_Lista(HttpServletRequest request) throws Excecoes {

        DespesaPesquisaED ed = new DespesaPesquisaED();

        String oid_Pessoa = request.getParameter("oid_Pessoa");
        String cd_Despesa = request.getParameter("FT_CD_Despesa");
        String NR_Documento = request.getParameter("FT_NR_Documento");
        String dt_Emissao_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
        String dt_Emissao_Final = request.getParameter("FT_DT_Emissao_Final");
        String nm_Razao_Social = request.getParameter("FT_NM_Razao_Social");

        if (oid_Pessoa != null && !oid_Pessoa.equals(""))
            ed.setOid_Pessoa(oid_Pessoa);

        if (cd_Despesa != null && !cd_Despesa.equals(""))
            ed.setNr_Despesa(new Integer(cd_Despesa));

        if (NR_Documento != null && !NR_Documento.equals(""))
            ed.setNr_Documento(NR_Documento);


        if (dt_Emissao_Inicial != null && !dt_Emissao_Inicial.equals(""))
            ed.setData_Emissao_Inicial(dt_Emissao_Inicial);

        if (dt_Emissao_Final != null && !dt_Emissao_Final.equals(""))
            ed.setData_Emissao_Final(dt_Emissao_Final);

        if (nm_Razao_Social != null && !nm_Razao_Social.equals(""))
            ed.setNm_Razao_Social(nm_Razao_Social);

        return new DespesaRN().lista(ed);

    }


    public DespesaED getByRecord(HttpServletRequest request) throws Excecoes {

        DespesaED ed = new DespesaED();
        String oid_Despesa = request.getParameter("oid_Despesa");
        if (oid_Despesa != null && oid_Despesa.length() > 0 && !oid_Despesa.equals("null")) {
            ed.setOid_Despesa(new Integer(oid_Despesa));
        }

        return new DespesaRN().getByRecord(ed);

    }

    public void geraDespesa_Emissao(HttpServletRequest request, HttpServletResponse res) throws Excecoes {
        DespesaED ed = new DespesaED();

        String oid_Pessoa = request.getParameter("oid_Pessoa");
        if (oid_Pessoa != null && !oid_Pessoa.equals(""))
            ed.setOid_Pessoa(oid_Pessoa);

        String dt_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
        if (dt_Inicial != null && !dt_Inicial.equals(""))
            ed.setDt_Inicial(dt_Inicial);

        String dt_Final = request.getParameter("FT_DT_Emissao_Final");
        if (dt_Final != null && !dt_Final.equals(""))
            ed.setDt_Final(dt_Final);

        DespesaRN geRN = new DespesaRN();
        byte[] b = geRN.geraDespesa_Emissao(ed);
        new EnviaPDF().enviaBytes(request, res, b);

    }


}
