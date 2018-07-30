package com.master.iu;

import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.master.bd.CompromissoBD;
import com.master.ed.CompromissoED;
import com.master.ed.CompromissoPesquisaED;
import com.master.ed.RelatorioED;
import com.master.ed.Tipo_EventoED;
import com.master.ed.UsuarioED;
import com.master.rn.CompromissoRN;
import com.master.util.Data;
import com.master.util.EnviaPDF;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;

public class Cpg001Bean  {

    public CompromissoED inclui(HttpServletRequest request) throws Excecoes {

        CompromissoRN compromissoRN = new CompromissoRN();
        CompromissoED ed = new CompromissoED();

        String oid_Usuario = request.getParameter ("oid_Usuario");
        if (JavaUtil.doValida (oid_Usuario)) {
           ed.setOID_Usuario (new Long (request.getParameter ("oid_Usuario")).longValue ());
        }else {

	       HttpSession session = request.getSession(true);

	       if (session != null && session.getAttribute("usuario") != null
	    		   && (session.getAttribute("usuario") instanceof UsuarioED)) {
	          UsuarioED usuario = (UsuarioED) session.getAttribute("usuario");
	          ed.setOID_Usuario (new Long (String.valueOf(usuario.getOid_Usuario())).longValue ());
           }
        }

        // System.out.println("1");
        String oid_Fatura_Compromisso = request.getParameter ("oid_Fatura_Compromisso");
        if (JavaUtil.doValida (oid_Fatura_Compromisso)) {
          ed.setOid_Fatura_Compromisso (new Long (request.getParameter ("oid_Fatura_Compromisso")).longValue ());
        }

        // System.out.println("2");

        ed.setDt_Emissao(request.getParameter("FT_DT_Emissao"));
        ed.setDt_Vencimento(request.getParameter("FT_DT_Vencimento"));
        ed.setDt_Entrada(request.getParameter("FT_DT_Entrada"));
        ed.setNr_Documento(request.getParameter("FT_NR_Documento"));

        ed.setNr_Parcela (new Integer (1));

        String nr_Parcela = request.getParameter("FT_NR_Parcela");
        if (JavaUtil.doValida(nr_Parcela)) {
          if (!nr_Parcela.equals (""))
            ed.setNr_Parcela (new Integer (nr_Parcela));
        }

        // System.out.println("3");

        ed.setOid_Centro_Custo(new Integer(request.getParameter("oid_Centro_Custo")));
        ed.setOid_Conta(new Integer(request.getParameter("oid_Conta")));
        ed.setOid_Pessoa(request.getParameter("oid_Pessoa"));
        // System.out.println("oid_Pessoa=" + request.getParameter("oid_Pessoa"));

        ed.setOid_Tipo_Documento(new Integer(request.getParameter("oid_Tipo_Documento")));
        ed.setOid_Unidade(new Long(request.getParameter("oid_Unidade")));

        // System.out.println("5");

        if (JavaUtil.doValida(request.getParameter("oid_Unidade_Pagamento"))) {
            ed.setOid_Unidade_Pagamento(new Integer(request.getParameter("oid_Unidade_Pagamento")).intValue());
        }

        ed.setTx_Observacao(request.getParameter("FT_TX_Observacao"));
        ed.setDM_Tipo_Pagamento(request.getParameter("FT_DM_Tipo_Pagamento"));
        // System.out.println("6");

        ed.setVl_Compromisso(new Double(request.getParameter("FT_VL_Previsto")));

        // System.out.println("10");

        if (JavaUtil.doValida(request.getParameter("FT_VL_Desconto_Ate_Vencimento"))){
            ed.setVl_Desconto_Ate_Vencimento (new Double (request.getParameter("FT_VL_Desconto_Ate_Vencimento")));
        }

        // System.out.println("11");

        if (JavaUtil.doValida(request.getParameter("FT_VL_Juro_Mora_Dia"))){
            ed.setVl_Juro_Mora_dia (new Double (request.getParameter("FT_VL_Juro_Mora_Dia")));
        }

        // System.out.println("12");

        if (JavaUtil.doValida(request.getParameter("FT_VL_Taxa_Banco"))){
            ed.setVl_Taxa_Banco (new Double (request.getParameter("FT_VL_Taxa_Banco")));
        }

        // System.out.println("13");

        if (JavaUtil.doValida(request.getParameter("FT_VL_Multa_Apos_Vencimento"))){
            ed.setVl_Multa_Apos_Vencimento (new Double (request.getParameter("FT_VL_Multa_Apos_Vencimento")));
        }

        // System.out.println("20");

        ed.setVl_Saldo(new Double(request.getParameter("FT_VL_Saldo")));
        if (JavaUtil.doValida(request.getParameter("FT_CD_Barra")))
            ed.setCD_Barra(request.getParameter("FT_CD_Barra"));

        return compromissoRN.inclui(ed);
    }

    public CompromissoED inclui_parcela(HttpServletRequest request) throws Excecoes, ParseException {

        CompromissoRN compromissoRN = new CompromissoRN();
        CompromissoED ed = new CompromissoED();
        // System.out.println("inclui_parcela =>>" + request.getParameter("oid_Compromisso"));
        ed.setOid_Compromisso(new Integer(request.getParameter("oid_Compromisso")));
        ed.setDt_Vencimento(request.getParameter("FT_DT_Vencimento_Parcela"));
        String nr_Parcela = request.getParameter("FT_NR_Nova_Parcela");
        if (!nr_Parcela.equals(""))
            ed.setNr_Parcela(new Integer(nr_Parcela));

        // System.out.println("nr_Parcela =>>" + ed.getNr_Parcela());

        ed.setVl_Compromisso(new Double(request.getParameter("FT_VL_Compromisso_Parcela")));

        // System.out.println("setVl_Compromisso =>>" + ed.getVl_Compromisso());

        return compromissoRN.inclui_parcela(ed);
    }

    public CompromissoED inclui_Cartorio(HttpServletRequest request) throws Excecoes, ParseException {

        CompromissoRN compromissoRN = new CompromissoRN();
        CompromissoED ed = new CompromissoED();

        ed.setOid_Compromisso(new Integer(request.getParameter("oid_Compromisso")));
        ed.setDT_Cartorio(request.getParameter("FT_DT_Cartorio"));
        ed.setVL_Custas(new Double(request.getParameter("FT_VL_Custas")).doubleValue());
        return compromissoRN.inclui_Cartorio(ed);
    }

    public CompromissoED exclui_Cartorio(HttpServletRequest request) throws Excecoes, ParseException {

        CompromissoRN compromissoRN = new CompromissoRN();
        CompromissoED ed = new CompromissoED();

        ed.setOid_Compromisso(new Integer(request.getParameter("oid_Compromisso")));
        return compromissoRN.exclui_Cartorio(ed);
    }

    public void altera(HttpServletRequest request) throws Exception {

        CompromissoRN compromissoRN = new CompromissoRN();
        CompromissoED ed = new CompromissoED();

        ed.setOid_Compromisso(new Integer(request.getParameter("oid_Compromisso")));
        ed.setDt_Emissao(request.getParameter("FT_DT_Emissao"));
        ed.setDt_Vencimento(request.getParameter("FT_DT_Vencimento"));
        ed.setDt_Entrada(request.getParameter("FT_DT_Entrada"));
        ed.setNr_Compromisso(new Integer(request.getParameter("FT_NR_Compromisso")));
        ed.setNr_Documento(request.getParameter("FT_NR_Documento"));

        String nr_Parcela = request.getParameter("FT_NR_Parcela");
        if (!nr_Parcela.equals(""))
            ed.setNr_Parcela(new Integer(nr_Parcela));

        ed.setOid_Centro_Custo(new Integer(request.getParameter("oid_Centro_Custo")));
        ed.setOid_Conta(new Integer(request.getParameter("oid_Conta")));
        // ed.setOid_Conta_Credito(new
        // Integer(request.getParameter("oid_Conta_Credito")));
        ed.setOid_Pessoa(request.getParameter("oid_Pessoa"));
        ed.setOid_Tipo_Documento(new Integer(request.getParameter("oid_Tipo_Documento")));
        ed.setOid_Unidade(new Long(request.getParameter("oid_Unidade")));
        ed.setOid_Unidade_Pagamento(new Integer(request.getParameter("oid_Unidade")).intValue());

        if (JavaUtil.doValida(request.getParameter("oid_Unidade_Pagamento"))) {
            ed.setOid_Unidade_Pagamento(new Integer(request.getParameter("oid_Unidade_Pagamento")).intValue());
        }

        String tx_Observacao = request.getParameter("FT_TX_Observacao");
        if (!tx_Observacao.equals(""))
            ed.setTx_Observacao(tx_Observacao);

        String DM_Tipo_Pagamento = request.getParameter("FT_DM_Tipo_Pagamento");
        if (!DM_Tipo_Pagamento.equals(""))
            ed.setDM_Tipo_Pagamento(DM_Tipo_Pagamento);

        String VL_Desconto_Ate_Vencimento = request.getParameter("FT_VL_Desconto_Ate_Vencimento");
        if (JavaUtil.doValida(VL_Desconto_Ate_Vencimento))
            ed.setVl_Desconto_Ate_Vencimento(new Double(VL_Desconto_Ate_Vencimento));

        String vl_Juro_Mora_Dia = request.getParameter("FT_VL_Juro_Mora_Dia");
        if (!vl_Juro_Mora_Dia.equals(""))
            ed.setVl_Juro_Mora_dia(new Double(vl_Juro_Mora_Dia));

        String vl_Taxa_Banco = request.getParameter("FT_VL_Taxa_Banco");
        if (!vl_Taxa_Banco.equals(""))
            ed.setVl_Taxa_Banco(new Double(vl_Taxa_Banco));

        String vl_Imposto = request.getParameter("FT_VL_Imposto");
        if (!vl_Imposto.equals(""))
            ed.setVl_Imposto(new Double(vl_Imposto));

        String pe_Imposto = request.getParameter("FT_PE_Imposto");
        if (pe_Imposto != null && !pe_Imposto.equals(""))
            ed.setPe_Imposto(new Double(pe_Imposto));

        String vl_Multa_Apos = request.getParameter("FT_VL_Multa_Apos_Vencimento");
        if (!vl_Multa_Apos.equals(""))
            ed.setVl_Multa_Apos_Vencimento(new Double(vl_Multa_Apos));

        ed.setVl_Compromisso(new Double(request.getParameter("FT_VL_Previsto")));

        ed.setVl_Saldo(new Double(request.getParameter("FT_VL_Saldo")));

        ed.setVL_Compromisso_Original(new Double(request.getParameter("FT_VL_Compromisso_Original")));
        if (JavaUtil.doValida(request.getParameter("FT_CD_Barra")))
            ed.setCD_Barra(request.getParameter("FT_CD_Barra"));

        ed.setCD_Barra(request.getParameter("FT_CD_Barra"));

        compromissoRN.altera(ed);
    }

    public void altera_pago(HttpServletRequest request) throws Exception {

        CompromissoRN compromissoRN = new CompromissoRN();
        CompromissoED ed = new CompromissoED();

        ed.setOid_Compromisso(new Integer(request.getParameter("oid_Compromisso")));
        ed.setDt_Emissao(request.getParameter("FT_DT_Emissao"));
        ed.setDt_Vencimento(request.getParameter("FT_DT_Vencimento"));
        ed.setDt_Entrada(request.getParameter("FT_DT_Entrada"));
        ed.setNr_Compromisso(new Integer(request.getParameter("FT_NR_Compromisso")));
        ed.setNr_Documento(request.getParameter("FT_NR_Documento"));

        String nr_Parcela = request.getParameter("FT_NR_Parcela");
        if (!nr_Parcela.equals(""))
            ed.setNr_Parcela(new Integer(nr_Parcela));

        ed.setOid_Centro_Custo(new Integer(request.getParameter("oid_Centro_Custo")));
        ed.setOid_Conta(new Integer(request.getParameter("oid_Conta")));
        // ed.setOid_Conta_Credito(new
        // Integer(request.getParameter("oid_Conta_Credito")));
        ed.setOid_Pessoa(request.getParameter("oid_Pessoa"));
//        ed.setOid_Tipo_Documento(new Integer(request.getParameter("oid_Tipo_Documento")));
        ed.setOid_Unidade(new Long(request.getParameter("oid_Unidade")));
        ed.setOid_Unidade_Pagamento(new Integer(request.getParameter("oid_Unidade")).intValue());

        if (JavaUtil.doValida(request.getParameter("oid_Unidade_Pagamento"))) {
            ed.setOid_Unidade_Pagamento(new Integer(request.getParameter("oid_Unidade_Pagamento")).intValue());
        }

        String tx_Observacao = request.getParameter("FT_TX_Observacao");
        if (!tx_Observacao.equals(""))
            ed.setTx_Observacao(tx_Observacao);

//        String DM_Tipo_Pagamento = request.getParameter("FT_DM_Tipo_Pagamento");
//        if (!DM_Tipo_Pagamento.equals(""))
//            ed.setDM_Tipo_Pagamento(DM_Tipo_Pagamento);
//
//        String VL_Desconto_Ate_Vencimento = request.getParameter("FT_VL_Desconto_Ate_Vencimento");
//        if (JavaUtil.doValida(VL_Desconto_Ate_Vencimento))
//            ed.setVl_Desconto_Ate_Vencimento(new Double(VL_Desconto_Ate_Vencimento));
//
//        String vl_Juro_Mora_Dia = request.getParameter("FT_VL_Juro_Mora_Dia");
//        if (!vl_Juro_Mora_Dia.equals(""))
//            ed.setVl_Juro_Mora_dia(new Double(vl_Juro_Mora_Dia));
//
//        String vl_Taxa_Banco = request.getParameter("FT_VL_Taxa_Banco");
//        if (!vl_Taxa_Banco.equals(""))
//            ed.setVl_Taxa_Banco(new Double(vl_Taxa_Banco));
//
//        String vl_Imposto = request.getParameter("FT_VL_Imposto");
//        if (!vl_Imposto.equals(""))
//            ed.setVl_Imposto(new Double(vl_Imposto));
//
//        String pe_Imposto = request.getParameter("FT_PE_Imposto");
//        if (pe_Imposto != null && !pe_Imposto.equals(""))
//            ed.setPe_Imposto(new Double(pe_Imposto));
//
//        String vl_Multa_Apos = request.getParameter("FT_VL_Multa_Apos_Vencimento");
//        if (!vl_Multa_Apos.equals(""))
//            ed.setVl_Multa_Apos_Vencimento(new Double(vl_Multa_Apos));
//
//        ed.setVl_Compromisso(new Double(request.getParameter("FT_VL_Previsto")));
//
//        ed.setVl_Saldo(new Double(request.getParameter("FT_VL_Saldo")));
//
//        ed.setVL_Compromisso_Original(new Double(request.getParameter("FT_VL_Compromisso_Original")));
//        if (JavaUtil.doValida(request.getParameter("FT_CD_Barra")))
//            ed.setCD_Barra(request.getParameter("FT_CD_Barra"));
//
//        ed.setCD_Barra(request.getParameter("FT_CD_Barra"));

        compromissoRN.altera_pago(ed);
    }

    public void vincula(HttpServletRequest request) throws Excecoes {

        CompromissoRN compromissoRN = new CompromissoRN();
        CompromissoED ed = new CompromissoED();

        ed.setOid_Compromisso(new Integer(request.getParameter("oid_Compromisso")));
        ed.setOid_Compromisso_Vinculado(new Integer(request.getParameter("oid_Compromisso_Vinculado")));

        compromissoRN.vincula(ed);
    }

    public void libera_Compromisso(HttpServletRequest request) throws Excecoes {

        CompromissoRN compromissoRN = new CompromissoRN();
        CompromissoED ed = new CompromissoED();

        ed.setOid_Compromisso(new Integer(request.getParameter("oid_Compromisso")));

        String DM_Situacao = request.getParameter("FT_DM_Situacao");
        if (DM_Situacao != null && !DM_Situacao.equals(""))
            ed.setDM_Situacao(DM_Situacao);

        String dt_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
        if (dt_Inicial != null && !dt_Inicial.equals(""))
            ed.setDt_Inicial(dt_Inicial);

        String oid_Usuario = request.getParameter ("oid_Usuario");
        if (JavaUtil.doValida (oid_Usuario)) {
          ed.setOID_Usuario_Liberacao (new Long (request.getParameter ("oid_Usuario")).longValue ());
        }

        compromissoRN.libera_Compromisso(ed);
    }

    public void aprova_Compromisso(HttpServletRequest request) throws Excecoes {

        CompromissoRN compromissoRN = new CompromissoRN();
        CompromissoED ed = new CompromissoED();

        ed.setOid_Compromisso(new Integer(request.getParameter("oid_Compromisso")));

        String oid_Usuario = request.getParameter ("oid_Usuario");
        if (JavaUtil.doValida (oid_Usuario)) {
          ed.setOID_Usuario_Aprovacao (new Long (request.getParameter ("oid_Usuario")).longValue ());
        }

        compromissoRN.aprova_Compromisso(ed);
    }

    public void agenda_Pagamento(HttpServletRequest request) throws Excecoes {

        CompromissoRN compromissoRN = new CompromissoRN();
        CompromissoED ed = new CompromissoED();

        ed.setOid_Compromisso(new Integer(request.getParameter("oid_Compromisso")));

        ed.setDt_Vencimento(request.getParameter("FT_DT_Vencimento"));

        String oid_Usuario = request.getParameter ("oid_Usuario");
        if (JavaUtil.doValida (oid_Usuario)) {
          ed.setOID_Usuario (new Long (request.getParameter ("oid_Usuario")).longValue ());
        }

        String NM_Favorecido = request.getParameter ("FT_NM_Favorecido");
        if (JavaUtil.doValida (NM_Favorecido)) {
          ed.setNM_Favorecido (NM_Favorecido);
        }

        String NR_Identificacao_Favorecido = request.getParameter ("FT_NR_Identificacao_Favorecido");
        if (JavaUtil.doValida (NR_Identificacao_Favorecido)) {
          ed.setNR_Identificacao_Favorecido (NR_Identificacao_Favorecido);
        }

        String CD_Banco_Favorecido = request.getParameter ("FT_CD_Banco_Favorecido");
        if (JavaUtil.doValida (CD_Banco_Favorecido)) {
          ed.setCD_Banco_Favorecido (CD_Banco_Favorecido);
        }

        String NM_Banco_Favorecido = request.getParameter ("FT_NM_Banco_Favorecido");
        if (JavaUtil.doValida (NM_Banco_Favorecido)) {
          ed.setNM_Banco_Favorecido (NM_Banco_Favorecido);
        }

        String NM_Agencia_Favorecido = request.getParameter ("FT_NM_Agencia_Favorecido");
        if (JavaUtil.doValida (NM_Agencia_Favorecido)) {
          ed.setNM_Agencia_Favorecido (NM_Agencia_Favorecido);
        }

        String NR_Conta_Corrente_Favorecido = request.getParameter ("FT_NR_Conta_Corrente_Favorecido");
        if (JavaUtil.doValida (NR_Conta_Corrente_Favorecido)) {
          ed.setNR_Conta_Corrente_Favorecido (NR_Conta_Corrente_Favorecido);
        }

        compromissoRN.agenda_Pagamento(ed);
    }

    public void deleta(HttpServletRequest request) throws Excecoes {

        CompromissoRN compromissoRN = new CompromissoRN();
        CompromissoED ed = new CompromissoED();

        ed.setOid_Compromisso(new Integer(request.getParameter("oid_Compromisso")));

        compromissoRN.deleta(ed);
    }

    public void deleta_Parcelamento(HttpServletRequest request) throws Excecoes {

        CompromissoRN compromissoRN = new CompromissoRN();
        CompromissoED ed = new CompromissoED();

        ed.setNr_Compromisso(new Integer(request.getParameter("FT_NR_Compromisso")));
        ed.setNr_Compromisso_Final(new Integer(request.getParameter("FT_NR_Compromisso_Final")));
        ed.setNr_Documento(request.getParameter("FT_NR_Documento"));
        ed.setOid_Pessoa(request.getParameter("oid_Pessoa"));

        compromissoRN.deleta_Parcelamento(ed);
    }

    public void deleta_Lista(ArrayList lista) throws Excecoes {

        CompromissoRN compromissoRN = new CompromissoRN();
        compromissoRN.deleta_Lista(lista);

    }

    public void deleta_Vinculo(HttpServletRequest request) throws Excecoes {

        CompromissoRN compromissoRN = new CompromissoRN();
        CompromissoED ed = new CompromissoED();

        ed.setOid_Compromisso_Vinculado(new Integer(request.getParameter("oid_Compromisso_Vinculado")));
        ed.setOid_Compromisso(new Integer(request.getParameter("oid_Compromisso")));

        compromissoRN.deleta_Vinculo(ed);
    }

    public void deleta_Selecao_Compromissos(HttpServletRequest request) throws Excecoes {

        CompromissoRN compromissoRN = new CompromissoRN();
        CompromissoPesquisaED ed = new CompromissoPesquisaED();

        String oid_Pessoa = request.getParameter("oid_Pessoa");
        String cd_Compromisso = request.getParameter("FT_CD_Compromisso");
        String cd_Compromisso_Final = request.getParameter("FT_CD_Compromisso_Final");
        String NR_Documento = request.getParameter("FT_NR_Documento");
        String oid_Compromisso_Vinculado = request.getParameter("oid_Compromisso_Vinculado");
        String DT_Entrada_Inicial = request.getParameter("FT_DT_Entrada_Inicial");
        String DT_Entrada_Final = request.getParameter("FT_DT_Entrada_Final");
        String dt_Vencimento_Inicial = request.getParameter("FT_DT_Vencimento_Inicial");
        String dt_Vencimento_Final = request.getParameter("FT_DT_Vencimento_Final");
        String VL_Previsto_Inicial = request.getParameter("FT_VL_Previsto_Inicial");
        String VL_Previsto_Final = request.getParameter("FT_VL_Previsto_Final");
        String dt_Emissao_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
        String dt_Emissao_Final = request.getParameter("FT_DT_Emissao_Final");
        String dt_Pagamento_Inicial = request.getParameter("FT_DT_Pagamento_Inicial");
        String dt_Pagamento_Final = request.getParameter("FT_DT_Pagamento_Final");
        String nm_Razao_Social = request.getParameter("FT_NM_Razao_Social");
        String DM_Situacao = request.getParameter("FT_DM_Situacao");
        String DM_Liberado = request.getParameter("FT_DM_Liberado");
        String DM_Aprovacao = request.getParameter("FT_DM_Aprovacao");
        String oid_Conta = request.getParameter("oid_Conta");
        String oid_Unidade = request.getParameter("oid_Unidade");
        String oid_Unidade_Pagamento = request.getParameter("oid_Unidade_Pagamento");
        String oid_Tipo_Documento = request.getParameter("oid_Tipo_Documento");

        if (JavaUtil.doValida(oid_Conta))
            ed.setOid_Conta(new Integer(oid_Conta));
        if (JavaUtil.doValida(oid_Unidade))
            ed.setOid_Unidade(new Long(oid_Unidade));
        if (JavaUtil.doValida(oid_Unidade_Pagamento))
            ed.setOid_Unidade_Pagamento(new Integer(oid_Unidade_Pagamento).intValue());
        if (JavaUtil.doValida(oid_Pessoa))
            ed.setOid_Pessoa(oid_Pessoa);
        if (JavaUtil.doValida(oid_Tipo_Documento))
            ed.setOid_Tipo_Documento(new Integer(oid_Tipo_Documento));
        if (JavaUtil.doValida(cd_Compromisso))
            ed.setNr_Compromisso(new Integer(cd_Compromisso));
        if (JavaUtil.doValida(cd_Compromisso_Final))
            ed.setNr_Compromisso_Final(new Integer(cd_Compromisso_Final));
        if (JavaUtil.doValida(NR_Documento))
            ed.setNr_Documento(NR_Documento);
        if (JavaUtil.doValida(oid_Compromisso_Vinculado))
            ed.setOid_Compromisso_Vinculado(new Integer(oid_Compromisso_Vinculado));
        if (JavaUtil.doValida(DT_Entrada_Inicial))
            ed.setDt_Entrada_Inicial(DT_Entrada_Inicial);
        if (JavaUtil.doValida(DT_Entrada_Final))
            ed.setDt_Entrada_Final(DT_Entrada_Final);
        if (JavaUtil.doValida(dt_Vencimento_Inicial))
            ed.setData_Vencimento_Inicial(dt_Vencimento_Inicial);
        if (JavaUtil.doValida(dt_Vencimento_Final))
            ed.setData_Vencimento_Final(dt_Vencimento_Final);
        if (JavaUtil.doValida(dt_Pagamento_Inicial))
            ed.setData_Pagamento_Inicial(dt_Pagamento_Inicial);
        if (JavaUtil.doValida(dt_Pagamento_Final))
            ed.setData_Pagamento_Final(dt_Pagamento_Final);
        if (JavaUtil.doValida(dt_Emissao_Inicial))
            ed.setData_Emissao_Inicial(dt_Emissao_Inicial);
        if (JavaUtil.doValida(dt_Emissao_Final))
            ed.setData_Emissao_Final(dt_Emissao_Final);
        if (JavaUtil.doValida(nm_Razao_Social))
            ed.setNm_Razao_Social(nm_Razao_Social);
        if (JavaUtil.doValida(DM_Situacao))
            ed.setDM_Situacao(DM_Situacao);
        if (JavaUtil.doValida(DM_Liberado))
            ed.setDM_Liberado(DM_Liberado);
        if (JavaUtil.doValida(DM_Aprovacao))
            ed.setDM_Aprovacao(DM_Aprovacao);
        if (JavaUtil.doValida(VL_Previsto_Inicial))
            ed.setVL_Previsto_Inicial(new Double(VL_Previsto_Inicial).doubleValue());
        if (JavaUtil.doValida(VL_Previsto_Final))
            ed.setVL_Previsto_Final(new Double(VL_Previsto_Final).doubleValue());

        compromissoRN.deleta_Selecao_Compromissos(ed);
    }

    public ArrayList compromisso_Lista(HttpServletRequest request) throws Excecoes {

        CompromissoPesquisaED ed = new CompromissoPesquisaED();

        String oid_Pessoa = request.getParameter("oid_Pessoa");
        String cd_Compromisso = request.getParameter("FT_CD_Compromisso");
        String NR_Documento = request.getParameter("FT_NR_Documento");
        String oid_Compromisso_Vinculado = request.getParameter("oid_Compromisso_Vinculado");
        String DT_Entrada_Inicial = request.getParameter("FT_DT_Entrada_Inicial");
        String DT_Entrada_Final = request.getParameter("FT_DT_Entrada_Final");
        String dt_Vencimento_Inicial = request.getParameter("FT_DT_Vencimento_Inicial");
        String dt_Vencimento_Final = request.getParameter("FT_DT_Vencimento_Final");
        String VL_Previsto_Inicial = request.getParameter("FT_VL_Previsto_Inicial");
        String VL_Previsto_Final = request.getParameter("FT_VL_Previsto_Final");
        String dt_Emissao_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
        String dt_Emissao_Final = request.getParameter("FT_DT_Emissao_Final");
        String dt_Pagamento_Inicial = request.getParameter("FT_DT_Pagamento_Inicial");
        String dt_Pagamento_Final = request.getParameter("FT_DT_Pagamento_Final");
        String nm_Razao_Social = request.getParameter("FT_NM_Razao_Social");
        String DM_Situacao = request.getParameter("FT_DM_Situacao");
        String DM_Liberado = request.getParameter("FT_DM_Liberado");
        String DM_Aprovacao = request.getParameter("FT_DM_Aprovacao");
        String oid_Conta = request.getParameter("oid_Conta");
        String oid_Unidade = request.getParameter("oid_Unidade");
        String oid_Unidade_Pagamento = request.getParameter("oid_Unidade_Pagamento");
        String oid_Tipo_Documento = request.getParameter("oid_Tipo_Documento");
        String tx_Observacao = request.getParameter("FT_TX_Observacao");

        if (JavaUtil.doValida(tx_Observacao))
            ed.setTx_Observacao(tx_Observacao);
        if (JavaUtil.doValida(oid_Conta))
            ed.setOid_Conta(new Integer(oid_Conta));
        if (JavaUtil.doValida(oid_Unidade))
            ed.setOid_Unidade(new Long(oid_Unidade));
        if (JavaUtil.doValida(oid_Unidade_Pagamento))
            ed.setOid_Unidade_Pagamento(new Integer(oid_Unidade_Pagamento).intValue());
        if (JavaUtil.doValida(oid_Pessoa))
            ed.setOid_Pessoa(oid_Pessoa);
        if (JavaUtil.doValida(oid_Tipo_Documento))
            ed.setOid_Tipo_Documento(new Integer(oid_Tipo_Documento));
        if (JavaUtil.doValida(cd_Compromisso))
            ed.setNr_Compromisso(new Integer(cd_Compromisso));
        if (JavaUtil.doValida(NR_Documento))
            ed.setNr_Documento(NR_Documento);
        if (JavaUtil.doValida(oid_Compromisso_Vinculado))
            ed.setOid_Compromisso_Vinculado(new Integer(oid_Compromisso_Vinculado));
        if (JavaUtil.doValida(DT_Entrada_Inicial))
            ed.setDt_Entrada_Inicial(DT_Entrada_Inicial);
        if (JavaUtil.doValida(DT_Entrada_Final))
            ed.setDt_Entrada_Final(DT_Entrada_Final);
        if (JavaUtil.doValida(dt_Vencimento_Inicial))
            ed.setData_Vencimento_Inicial(dt_Vencimento_Inicial);
        if (JavaUtil.doValida(dt_Vencimento_Final))
            ed.setData_Vencimento_Final(dt_Vencimento_Final);
        if (JavaUtil.doValida(dt_Pagamento_Inicial))
            ed.setData_Pagamento_Inicial(dt_Pagamento_Inicial);
        if (JavaUtil.doValida(dt_Pagamento_Final))
            ed.setData_Pagamento_Final(dt_Pagamento_Final);
        if (JavaUtil.doValida(dt_Emissao_Inicial))
            ed.setData_Emissao_Inicial(dt_Emissao_Inicial);
        if (JavaUtil.doValida(dt_Emissao_Final))
            ed.setData_Emissao_Final(dt_Emissao_Final);
        if (JavaUtil.doValida(nm_Razao_Social))
            ed.setNm_Razao_Social(nm_Razao_Social);
        if (JavaUtil.doValida(DM_Situacao))
            ed.setDM_Situacao(DM_Situacao);
        if (JavaUtil.doValida(DM_Liberado))
            ed.setDM_Liberado(DM_Liberado);
        if (JavaUtil.doValida(DM_Aprovacao))
            ed.setDM_Aprovacao(DM_Aprovacao);
        if (JavaUtil.doValida(VL_Previsto_Inicial))
            ed.setVL_Previsto_Inicial(new Double(VL_Previsto_Inicial).doubleValue());
        if (JavaUtil.doValida(VL_Previsto_Final))
            ed.setVL_Previsto_Final(new Double(VL_Previsto_Final).doubleValue());

        return new CompromissoRN().lista(ed);

    }

    public ArrayList compromisso_Ordem_Servico_Lista(HttpServletRequest request) throws Excecoes {

        CompromissoPesquisaED ed = new CompromissoPesquisaED();

        String dt_Emissao_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
        String dt_Emissao_Final = request.getParameter("FT_DT_Emissao_Final");

        if (JavaUtil.doValida(dt_Emissao_Inicial))
            ed.setDt_Inicial(dt_Emissao_Inicial);
        if (JavaUtil.doValida(dt_Emissao_Final))
            ed.setDt_Final(dt_Emissao_Final);

        return new CompromissoRN().lista_Compromisso_Ordem_Servico(ed);

    }


    public ArrayList compromisso_Lista_Vencidos(HttpServletRequest request) throws Excecoes {

        CompromissoPesquisaED ed = new CompromissoPesquisaED();
        String nm_Razao_Social = request.getParameter("FT_NM_Razao_Social");
        String DM_Lote_Pagamento = request.getParameter("FT_DM_Lote_Pagamento");
        String oid_Fornecedor = request.getParameter("oid_Fornecedor");

        if (JavaUtil.doValida(oid_Fornecedor))
            ed.setOid_Pessoa(oid_Fornecedor);
        if (JavaUtil.doValida(nm_Razao_Social))
            ed.setNm_Razao_Social(nm_Razao_Social);
        if (JavaUtil.doValida(DM_Lote_Pagamento))
            ed.setDM_Lote_Pagamento(DM_Lote_Pagamento);

        ed.setDM_Situacao("A");
        ed.setDM_Lote_Pagamento("N");
        ed.setData_Vencimento_Final(Data.getDataDMY());
        return new CompromissoRN().lista(ed);
    }

    public ArrayList compromisso_Lista_Valor(HttpServletRequest request) throws Excecoes {

        CompromissoPesquisaED ed = new CompromissoPesquisaED();
        ed.setDm_Ordenar("VALOR");
        ed.setDM_Situacao("NAO_LIBERADO");
        ed.setDM_Situacao("A");
        ed.setDM_Lote_Pagamento("N");
        ed.setData_Vencimento_Final(Data.getDataDMY());
        return new CompromissoRN().lista(ed);
    }

    public ArrayList compromisso_Lista_Nao_Liberados(HttpServletRequest request) throws Excecoes {

        CompromissoPesquisaED ed = new CompromissoPesquisaED();
        String oid_Fornecedor = request.getParameter("oid_Fornecedor");
        if (JavaUtil.doValida(oid_Fornecedor))
            ed.setOid_Pessoa(oid_Fornecedor);

        ed.setDM_Situacao("NAO_LIBERADO");
        ed.setDM_Lote_Pagamento("N");
        ed.setData_Vencimento_Final(Data.getDataDMY());
        return new CompromissoRN().lista(ed);
    }


    public ArrayList compromisso_Lista_Vinculado(HttpServletRequest request) throws Excecoes {

        CompromissoPesquisaED ed = new CompromissoPesquisaED();

        String oid_Pessoa = request.getParameter("oid_Pessoa");
        String cd_Compromisso = request.getParameter("FT_CD_Compromisso");
        String oid_Compromisso_Vinculado = request.getParameter("oid_Compromisso_Vinculado");
        String dt_Vencimento_Inicial = request.getParameter("FT_DT_Vencimento_Inicial");
        String dt_Vencimento_Final = request.getParameter("FT_DT_Vencimento_Final");
        String nm_Razao_Social = request.getParameter("FT_NM_Razao_Social");

        if (oid_Pessoa != null && !oid_Pessoa.equals(""))
            ed.setOid_Pessoa(oid_Pessoa);

        if (cd_Compromisso != null && !cd_Compromisso.equals(""))
            ed.setNr_Compromisso(new Integer(cd_Compromisso));

        if (oid_Compromisso_Vinculado != null && !oid_Compromisso_Vinculado.equals(""))
            ed.setOid_Compromisso_Vinculado(new Integer(oid_Compromisso_Vinculado));

        if (dt_Vencimento_Inicial != null && !dt_Vencimento_Inicial.equals(""))
            ed.setData_Vencimento_Inicial(dt_Vencimento_Inicial);

        if (dt_Vencimento_Final != null && !dt_Vencimento_Final.equals(""))
            ed.setData_Vencimento_Final(dt_Vencimento_Final);

        if (nm_Razao_Social != null && !nm_Razao_Social.equals(""))
            ed.setNm_Razao_Social(nm_Razao_Social);

        return new CompromissoRN().lista_Vinculado(ed);

    }

    public ArrayList compromisso_Lista_Parcela(HttpServletRequest request) throws Excecoes {

        CompromissoPesquisaED ed = new CompromissoPesquisaED();

        String oid_Compromisso = request.getParameter("oid_Compromisso");
        ed.setOid_Compromisso(new Integer(oid_Compromisso));
        return new CompromissoRN().lista_Parcela(ed);

    }

    public ArrayList compromisso_Ordem_Lista(HttpServletRequest request) throws Excecoes {

        CompromissoPesquisaED ed = new CompromissoPesquisaED();
        ed.setOID_Ordem_Frete(request.getParameter("oid_Ordem_Frete"));
        return new CompromissoRN().lista_Ordem(ed);

    }

    public ArrayList compromisso_Servico_Lista(HttpServletRequest request) throws Excecoes {

        CompromissoPesquisaED ed = new CompromissoPesquisaED();

        ed.setOID_Movimento_Ordem_Servico(new Integer(request.getParameter("oid_Movimento_Ordem_Servico")));

        return new CompromissoRN().lista_Servico(ed);

    }

    public CompromissoED getByRecord(HttpServletRequest request) throws Exception {

        String oid_Compromisso = request.getParameter("oid_Compromisso");
        String NR_Parcela = request.getParameter("FT_NR_Parcela");
        String NR_Compromisso = request.getParameter("FT_NR_Compromisso");
        String oid_Pessoa = request.getParameter("oid_Pessoa");
        String NR_Documento = request.getParameter("FT_NR_Documento");

        CompromissoED ed = new CompromissoED();
        if (JavaUtil.doValida(oid_Compromisso))
            ed.setOid_Compromisso(new Integer(oid_Compromisso));
        if (JavaUtil.doValida(NR_Compromisso))
            ed.setNr_Compromisso(new Integer(NR_Compromisso));
        if (JavaUtil.doValida(oid_Pessoa))
            ed.setOid_Pessoa(oid_Pessoa);
        if (JavaUtil.doValida(NR_Documento))
            ed.setNr_Documento(NR_Documento);
        if (JavaUtil.doValida(NR_Parcela))
            ed.setNr_Parcela(new Integer(NR_Parcela));
        else ed.setNr_Parcela(new Integer(1));

        return new CompromissoRN().getByRecord(ed);

    }

    public CompromissoED getByRecord_Com_Lote(HttpServletRequest request) throws Exception {

        String oid_Compromisso = request.getParameter("oid_Compromisso");
        String oid_Lote_Pagamento = request.getParameter("oid_Lote_Pagamento");
        String NR_Compromisso = request.getParameter("FT_NR_Compromisso");

        CompromissoED ed = new CompromissoED();
        if (JavaUtil.doValida(oid_Compromisso))
            ed.setOid_Compromisso(new Integer(oid_Compromisso));
        if (JavaUtil.doValida(oid_Lote_Pagamento))
            ed.setOid_Lote_Pagamento((new Long(oid_Lote_Pagamento).longValue()));
        if (JavaUtil.doValida(NR_Compromisso))
            ed.setNr_Compromisso(new Integer(NR_Compromisso));

        return new CompromissoRN().getByRecord_Com_Lote(ed);

    }

    public CompromissoED getByRecord_Troca(HttpServletRequest request) throws Exception {

        String NR_Compromisso = request.getParameter("FT_NR_Compromisso_Troca");

        CompromissoED ed = new CompromissoED();
        if (JavaUtil.doValida(NR_Compromisso))
            ed.setNr_Compromisso(new Integer(NR_Compromisso));

        return new CompromissoRN().getByRecord(ed);

    }

    public CompromissoED getByRecord(String oid_Compromisso, String NR_Compromisso, String NR_Documento, String NR_Parcela) throws Exception {

        CompromissoED ed = new CompromissoED();
        if (JavaUtil.doValida(oid_Compromisso))
            ed.setOid_Compromisso(new Integer(oid_Compromisso));
        if (JavaUtil.doValida(NR_Compromisso))
            ed.setNr_Compromisso(new Integer(NR_Compromisso));
        if (JavaUtil.doValida(NR_Documento))
            ed.setNr_Documento(NR_Documento);
        if (JavaUtil.doValida(NR_Parcela))
            ed.setNr_Parcela(new Integer(NR_Parcela));
        else ed.setNr_Parcela(new Integer(1));

        return new CompromissoRN().getByRecord(ed);

    }


    public CompromissoED getByRecord_Vinculado(HttpServletRequest request) throws Excecoes {

        CompromissoED ed = new CompromissoED();
        String oid_Compromisso = request.getParameter("oid_Compromisso_Vinculado");
        if (oid_Compromisso != null && oid_Compromisso.length() > 0 && !oid_Compromisso.equals("null")) {
            ed.setOid_Compromisso(new Integer(oid_Compromisso));
        }
        String NR_Compromisso = request.getParameter("FT_NR_Compromisso_Vinculado");
        if (NR_Compromisso != null && NR_Compromisso.length() > 0 && !NR_Compromisso.equals("null")) {
            ed.setNr_Compromisso(new Integer(NR_Compromisso));
        }

        return new CompromissoRN().getByRecord_Vinculado(ed);

    }

    public CompromissoED getByRecord_Compromisso_Vinculado(HttpServletRequest request) throws Excecoes {

        CompromissoED ed = new CompromissoED();
        String oid_Compromisso = request.getParameter("oid_Compromisso");
        if (oid_Compromisso != null && oid_Compromisso.length() > 0 && !oid_Compromisso.equals("null")) {
            ed.setOid_Compromisso(new Integer(oid_Compromisso));
        }
        return new CompromissoRN().getByRecord_Compromisso_Vinculado(ed);
    }

    public static void geraCompromisso_Fornecedor(HttpServletRequest request, HttpServletResponse response) throws Excecoes {

        String oid_Pessoa = request.getParameter("oid_Pessoa");
        String DM_Situacao = request.getParameter("FT_DM_Situacao");
        String DM_Classificar = request.getParameter("FT_DM_Classificar");
        String DM_Relatorio = request.getParameter("FT_DM_Relatorio");
        String DT_Vencimento_Inicial = request.getParameter("FT_DT_Vencimento_Inicial");
        String DT_Vencimento_Final = request.getParameter("FT_DT_Vencimento_Final");
        String DT_Pagamento_Inicial = request.getParameter("FT_DT_Pagamento_Inicial");
        String DT_Pagamento_Final = request.getParameter("FT_DT_Pagamento_Final");
        String DT_Emissao_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
        String DT_Emissao_Final = request.getParameter("FT_DT_Emissao_Final");
        String DT_Entrada_Inicial = request.getParameter("FT_DT_Entrada_Inicial");
        String DT_Entrada_Final = request.getParameter("FT_DT_Entrada_Final");
        String oid_Conta = request.getParameter("oid_Conta");
        String oid_Unidade = request.getParameter("oid_Unidade");
        String oid_Centro_Custo = request.getParameter("oid_Centro_Custo");
        String oid_Grupo_Economico = request.getParameter("oid_Grupo_Economico");
        String oid_Empresa = request.getParameter("oid_Empresa");
        String nr_Parcela = request.getParameter("FT_NR_Parcela");

        CompromissoED ed = new CompromissoED();

        if (JavaUtil.doValida(oid_Pessoa))
            ed.setOid_Pessoa(oid_Pessoa);
        if (JavaUtil.doValida(DM_Situacao))
            ed.setDM_Situacao(DM_Situacao);
        if (JavaUtil.doValida(DM_Classificar))
            ed.setDM_Classificar(DM_Classificar);
        if (JavaUtil.doValida(DM_Relatorio))
            ed.setDM_Relatorio(DM_Relatorio);
        if (JavaUtil.doValida(DT_Vencimento_Inicial))
            ed.setDt_Vencimento_Inicial(DT_Vencimento_Inicial);
        if (JavaUtil.doValida(DT_Vencimento_Final))
            ed.setDt_Vencimento_Final(DT_Vencimento_Final);
        if (JavaUtil.doValida(DT_Pagamento_Inicial))
            ed.setDt_Pagamento_Inicial(DT_Pagamento_Inicial);
        if (JavaUtil.doValida(DT_Pagamento_Final))
            ed.setDt_Pagamento_Final(DT_Pagamento_Final);
        if (JavaUtil.doValida(DT_Emissao_Inicial))
            ed.setDt_Inicial(DT_Emissao_Inicial);
        if (JavaUtil.doValida(DT_Emissao_Final))
            ed.setDt_Final(DT_Emissao_Final);
        if (JavaUtil.doValida(DT_Entrada_Inicial))
            ed.setDt_Entrada_Inicial(DT_Entrada_Inicial);
        if (JavaUtil.doValida(DT_Entrada_Final))
            ed.setDt_Entrada_Final(DT_Entrada_Final);

        ed.setNM_Titulo_Relatorio1 ("");
        ed.setNM_Titulo_Relatorio2 ("");
        ed.setNM_Titulo_Relatorio3 ("");
        ed.setNM_Titulo_Relatorio4 ("");

        if (oid_Empresa != null && !oid_Empresa.equals ("")) {
          ed.setOid_Empresa(new Long(oid_Empresa).intValue());
          ed.setNM_Titulo_Relatorio1 ("Empresa:" + request.getParameter ("FT_NM_Empresa"));
        }
        if (oid_Unidade != null && !oid_Unidade.equals ("")) {
          ed.setOid_Unidade (new Long (request.getParameter ("oid_Unidade")));
          ed.setNM_Titulo_Relatorio1 ("Unid.Deb.: " + request.getParameter ("FT_CD_Unidade") + " - " + request.getParameter ("FT_NM_Fantasia"));
        }
        if (oid_Conta != null && !oid_Conta.equals ("")) {
          ed.setOid_Conta (new Integer (request.getParameter ("oid_Conta")));
          ed.setNM_Titulo_Relatorio2 ("/ Conta:" + request.getParameter ("FT_NM_Conta"));
        }
        if (oid_Centro_Custo != null && !oid_Centro_Custo.equals ("")) {
          ed.setOid_Centro_Custo (new Integer (request.getParameter ("oid_Centro_Custo")));
          ed.setNM_Titulo_Relatorio3 ("/ C.Custo:" + request.getParameter ("FT_NM_Centro_Custo"));
        }

        if (oid_Grupo_Economico != null && !oid_Grupo_Economico.equals ("")) {
          ed.setOid_Grupo_Economico (new Integer (request.getParameter ("oid_Grupo_Economico")));
          ed.setNM_Titulo_Relatorio4 ("/ Grupo:" + request.getParameter ("FT_NM_Grupo_Economico"));
        }

        ed.setNr_Parcela (new Integer (0));
        if (JavaUtil.doValida(request.getParameter("nr_Parcela"))) {
          if (!nr_Parcela.equals (""))
            ed.setNr_Parcela (new Integer (nr_Parcela));
        }
        new CompromissoRN().geraCompromisso_Fornecedor(ed, response);
    }

    public byte[] imprime_Agenda_Pagamento(HttpServletRequest request, HttpServletResponse res) throws Excecoes {

        String oid_Compromisso = request.getParameter("oid_Compromisso");
        CompromissoED ed = new CompromissoED();

        if (oid_Compromisso != null && !oid_Compromisso.equals(""))
            ed.setOid_Compromisso(new Integer(request.getParameter("oid_Compromisso")));

        CompromissoRN geRN = new CompromissoRN();
        byte[] b = geRN.imprime_Agenda_Pagamento(ed);

        return b;
    }


    public byte[] geraCompromisso_Unidade(HttpServletRequest request, HttpServletResponse Response) throws Excecoes {

        CompromissoED ed = new CompromissoED();

        String DM_Situacao = request.getParameter("FT_DM_Situacao");
        if (DM_Situacao != null && !DM_Situacao.equals(""))
            ed.setDM_Situacao(DM_Situacao);

        ed.setDM_Relatorio("M1");

        String DM_Relatorio = request.getParameter("FT_DM_Relatorio");
        if (DM_Relatorio != null && !DM_Relatorio.equals(""))
            ed.setDM_Relatorio(DM_Relatorio);

        String oid_Conta = request.getParameter("oid_Conta");
        if (oid_Conta != null && !oid_Conta.equals(""))
            ed.setOid_Conta(new Integer(request.getParameter("oid_Conta")));

        String oid_Unidade = request.getParameter("oid_Unidade");
        if (oid_Unidade != null && !oid_Unidade.equals(""))
            ed.setOid_Unidade(new Long(request.getParameter("oid_Unidade")));

        if (JavaUtil.doValida(request.getParameter("oid_Unidade_Pagamento"))) {
            ed.setOid_Unidade_Pagamento(new Integer(request.getParameter("oid_Unidade_Pagamento")).intValue());
        }

        String dt_Inicial = request.getParameter("FT_DT_Vencimento_Inicial");
        if (dt_Inicial != null && !dt_Inicial.equals(""))
            ed.setDt_Vencimento_Inicial(dt_Inicial);

        String dt_Final = request.getParameter("FT_DT_Vencimento_Final");
        if (dt_Final != null && !dt_Final.equals(""))
            ed.setDt_Vencimento_Final(dt_Final);

        dt_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
        if (dt_Inicial != null && !dt_Inicial.equals(""))
            ed.setDt_Inicial(dt_Inicial);

        dt_Final = request.getParameter("FT_DT_Emissao_Final");
        if (dt_Final != null && !dt_Final.equals(""))
            ed.setDt_Final(dt_Final);

        CompromissoRN geRN = new CompromissoRN();
        byte[] b = geRN.geraCompromisso_Unidade(ed);
        return b;

    }

    // /#### DM SITUACAO
    public void geraCompromisso_Vencimento(HttpServletRequest request, HttpServletResponse res) throws Excecoes {
        CompromissoED ed = new CompromissoED();

        String oid_Pessoa = request.getParameter("oid_Pessoa");
        if (oid_Pessoa != null && !oid_Pessoa.equals(""))
            ed.setOid_Pessoa(oid_Pessoa);

        String DM_Situacao = request.getParameter("FT_DM_Situacao");
        if (DM_Situacao != null && !DM_Situacao.equals(""))
            ed.setDM_Situacao(DM_Situacao);

        String DM_Relatorio = request.getParameter("FT_DM_Relatorio");
        if (DM_Relatorio != null && !DM_Relatorio.equals(""))
            ed.setDM_Relatorio(DM_Relatorio);

        String dt_Inicial = request.getParameter("FT_DT_Vencimento_Inicial");
        if (dt_Inicial != null && !dt_Inicial.equals(""))
            ed.setDt_Inicial(dt_Inicial);

        String dt_Final = request.getParameter("FT_DT_Vencimento_Final");
        if (dt_Final != null && !dt_Final.equals(""))
            ed.setDt_Final(dt_Final);

        CompromissoRN geRN = new CompromissoRN();
        byte[] b = geRN.geraCompromisso_Vencimento(ed);
        new EnviaPDF().enviaBytes(request, res, b);

    }

    public void geraCompromisso_Emissao(HttpServletRequest request, HttpServletResponse res) throws Excecoes {
        CompromissoED ed = new CompromissoED();

        String oid_Pessoa = request.getParameter("oid_Pessoa");
        if (oid_Pessoa != null && !oid_Pessoa.equals(""))
            ed.setOid_Pessoa(oid_Pessoa);

        String DM_Situacao = request.getParameter("FT_DM_Situacao");
        if (DM_Situacao != null && !DM_Situacao.equals(""))
            ed.setDM_Situacao(DM_Situacao);

        String dt_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
        if (dt_Inicial != null && !dt_Inicial.equals(""))
            ed.setDt_Inicial(dt_Inicial);

        String dt_Final = request.getParameter("FT_DT_Emissao_Final");
        if (dt_Final != null && !dt_Final.equals(""))
            ed.setDt_Final(dt_Final);

        CompromissoRN geRN = new CompromissoRN();
        byte[] b = geRN.geraCompromisso_Emissao(ed);
        new EnviaPDF().enviaBytes(request, res, b);

    }

    public byte[] geraCompromisso_Ordem_Servico(HttpServletRequest request, HttpServletResponse res) throws Excecoes {
        CompromissoED ed = new CompromissoED();

        String oid_Pessoa = request.getParameter("oid_Pessoa");
        if (oid_Pessoa != null && !oid_Pessoa.equals(""))
            ed.setOid_Pessoa(oid_Pessoa);

        String DM_Situacao = request.getParameter("FT_DM_Situacao");
        if (DM_Situacao != null && !DM_Situacao.equals(""))
            ed.setDM_Situacao(DM_Situacao);

        String dt_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
        if (dt_Inicial != null && !dt_Inicial.equals(""))
            ed.setDt_Inicial(dt_Inicial);

        String DM_Relatorio = request.getParameter("FT_DM_Relatorio");
        if (JavaUtil.doValida(DM_Relatorio)) {
          ed.setDM_Relatorio (DM_Relatorio);
        }

        CompromissoRN geRN = new CompromissoRN();
        byte[] b = geRN.geraCompromisso_Ordem_Servico(ed);

        return b;

    }

    public void geraCompromisso_Pagamento(HttpServletRequest request, HttpServletResponse res) throws Excecoes {
        CompromissoED ed = new CompromissoED();

        String oid_Pessoa = request.getParameter("oid_Pessoa");
        if (oid_Pessoa != null && !oid_Pessoa.equals(""))
            ed.setOid_Pessoa(oid_Pessoa);

        String dt_Inicial = request.getParameter("FT_DT_Pagamento_Inicial");
        if (dt_Inicial != null && !dt_Inicial.equals(""))
            ed.setDt_Inicial(dt_Inicial);

        String dt_Final = request.getParameter("FT_DT_Pagamento_Final");
        if (dt_Final != null && !dt_Final.equals(""))
            ed.setDt_Final(dt_Final);

        String DM_Relatorio = request.getParameter("FT_DM_Relatorio");
          if (JavaUtil.doValida(DM_Relatorio)) {
              ed.setDM_Relatorio(DM_Relatorio);
          } else
              ed.setDM_Relatorio("M1");


        CompromissoRN geRN = new CompromissoRN();
        byte[] b = geRN.geraCompromisso_Pagamento(ed);
        new EnviaPDF().enviaBytes(request, res, b);

    }

    public void geraDiario_Auxiliar_Fornecedor(HttpServletRequest request, HttpServletResponse res) throws Excecoes {
        CompromissoED ed = new CompromissoED();

        String oid_Pessoa = request.getParameter("oid_Pessoa");
        if (oid_Pessoa != null && !oid_Pessoa.equals(""))
            ed.setOid_Pessoa(oid_Pessoa);

        String dt_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
        if (dt_Inicial != null && !dt_Inicial.equals(""))
            ed.setDt_Inicial(dt_Inicial);

        String dt_Final = request.getParameter("FT_DT_Emissao_Final");
        if (dt_Final != null && !dt_Final.equals(""))
            ed.setDt_Final(dt_Final);

        CompromissoRN geRN = new CompromissoRN();
        byte[] b = geRN.geraDiario_Auxiliar_Fornecedor(ed);
        new EnviaPDF().enviaBytes(request, res, b);

    }

    public ArrayList GeraEDI_Compromissos(HttpServletRequest request) throws Excecoes {
        Tipo_EventoED ed = new Tipo_EventoED();

        ed.setCd_Tipo_Evento(request.getParameter("FT_CD_Tipo_Evento"));
        ed.setNm_Tipo_Evento(request.getParameter("FT_NM_Tipo_Evento"));
        ed.setCd_Historico(request.getParameter("FT_CD_Historico"));
        ed.setCd_Conta_Credito(request.getParameter("FT_CD_Conta_Credito"));
        ed.setCd_Conta_Debito(request.getParameter("FT_CD_Conta_Debito"));
        ed.setNm_Arquivo_Saida(request.getParameter("FT_NM_Arquivo_Saida"));
        ed.setDt_Inicial(request.getParameter("FT_DT_Inicial"));
        ed.setDt_Final(request.getParameter("FT_DT_Final"));
        String oid_Unidade = request.getParameter("oid_Unidade");
        if (String.valueOf(oid_Unidade) != null && !String.valueOf(oid_Unidade).equals("")) {
            ed.setOID_Unidade(new Long(oid_Unidade).longValue());
        }

        return new CompromissoRN().GeraEDI_Compromissos(ed);

    }

    public ArrayList GeraEDI_Movimentos_Compromissos(HttpServletRequest request) throws Excecoes {
        Tipo_EventoED ed = new Tipo_EventoED();

        ed.setCd_Tipo_Evento(request.getParameter("FT_CD_Tipo_Evento"));
        ed.setNm_Tipo_Evento(request.getParameter("FT_NM_Tipo_Evento"));
        ed.setCd_Historico(request.getParameter("FT_CD_Historico"));
        ed.setCd_Conta_Credito(request.getParameter("FT_CD_Conta_Credito"));
        ed.setCd_Conta_Debito(request.getParameter("FT_CD_Conta_Debito"));
        ed.setNm_Arquivo_Saida(request.getParameter("FT_NM_Arquivo_Saida"));
        ed.setDt_Inicial(request.getParameter("FT_DT_Inicial"));
        ed.setDt_Final(request.getParameter("FT_DT_Final"));
        String oid_Unidade = request.getParameter("oid_Unidade");
        if (String.valueOf(oid_Unidade) != null && !String.valueOf(oid_Unidade).equals("")) {
            ed.setOID_Unidade(new Long(oid_Unidade).longValue());
        }

        return new CompromissoRN().GeraEDI_Movimentos_Compromissos(ed);

    }

    public CompromissoED inclui_provisao(HttpServletRequest request) throws Excecoes {

        CompromissoED ed = new CompromissoED();
        ed.setOid_Compromisso(new Integer(request.getParameter("oid_Compromisso")));
        ed.setNR_Qtde_Provisao(new Integer(request.getParameter("FT_NR_Qtde_Provisao")));
        ed.setNR_Dia_Provisao(new Integer(request.getParameter("FT_NR_Dia_Provisao")));
        ed.setDt_Vencimento(request.getParameter("FT_DT_Vencimento"));

        // System.out.println(request.getParameter("FT_NR_Qtde_Provisao"));
        // System.out.println(request.getParameter("FT_NR_Dia_Provisao"));

        return new CompromissoRN().inclui_provisao(ed);
    }

    public CompromissoED gera_parcela(HttpServletRequest request) throws Excecoes {

        CompromissoED ed = new CompromissoED();
        ed.setOid_Compromisso(new Integer(request.getParameter("oid_Compromisso")));
        ed.setNr_Dias_Vencimento(new Integer(request.getParameter("FT_NR_Dias_Vencimento")).intValue());

        String NR_Parcelas = request.getParameter("FT_NR_Parcelas");
        if (JavaUtil.doValida(NR_Parcelas))
            ed.setNr_Parcelas(new Integer(NR_Parcelas).intValue());

        String NR_Dia_Provisao = request.getParameter("FT_NR_Dia_Provisao");
        if (JavaUtil.doValida(NR_Dia_Provisao))
            ed.setNR_Dia_Provisao(new Integer(NR_Dia_Provisao));


        return new CompromissoRN().gera_parcela(ed);
    }

    public ArrayList compromisso_Parcela_Lista(HttpServletRequest request) throws Excecoes {

        CompromissoPesquisaED ed = new CompromissoPesquisaED();
        ed.setOID_Parcelamento(new Long(request.getParameter("oid_Parcela")).longValue());

        return new CompromissoRN().compromisso_Parcela_Lista(ed);
    }

    /** ------------ RELATÓRIOS ---------------- */
    // *** Compromissos a Pagar
    public void relCompromisso(HttpServletRequest request, HttpServletResponse response) throws Excecoes {

        String Relatorio = request.getParameter("Relatorio");
        if (!JavaUtil.doValida(Relatorio))
            throw new Mensagens("Nome do Relatório não informado!");

        RelatorioED ed = new RelatorioED(response, Relatorio);
        String oid_Pessoa = request.getParameter("oid_Pessoa");
        String oid_Centro_Custo = request.getParameter("oid_Centro_Custo");
        String oid_Conta = request.getParameter("oid_Conta");
        String DM_Situacao = request.getParameter("FT_DM_Situacao");
        String DM_Tipo_Pagamento = request.getParameter("FT_DM_Tipo_Pagamento");
        String DT_Entrada_Inicial = request.getParameter("FT_DT_Entrada_Inicial");
        String DT_Entrada_Final = request.getParameter("FT_DT_Entrada_Final");
        String DT_Vencimento_Inicial = request.getParameter("FT_DT_Vencimento_Inicial");
        String DT_Vencimento_Final = request.getParameter("FT_DT_Vencimento_Final");
        String DT_Pagamento_Inicial = request.getParameter("FT_DT_Pagamento_Inicial");
        String DT_Pagamento_Final = request.getParameter("FT_DT_Pagamento_Final");
        String DT_Emissao_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
        String DT_Emissao_Final = request.getParameter("FT_DT_Emissao_Final");

        if (JavaUtil.doValida(oid_Pessoa))
            ed.setOid_pessoa(oid_Pessoa);
        if (JavaUtil.doValida(oid_Conta))
            ed.setOid_conta(Integer.parseInt(oid_Conta));
        if (JavaUtil.doValida(oid_Centro_Custo))
            ed.setOid_centro_custo(Integer.parseInt(oid_Centro_Custo));
        if (JavaUtil.doValida(DM_Situacao))
            ed.setDm_situacao(DM_Situacao);
        if (JavaUtil.doValida(DM_Tipo_Pagamento))
            ed.setDm_forma_pagamento(DM_Tipo_Pagamento);
        if (JavaUtil.doValida(DT_Entrada_Inicial))
            ed.setDt_entrada_inicial(DT_Entrada_Inicial);
        if (JavaUtil.doValida(DT_Entrada_Final))
            ed.setDt_entrada_final(DT_Entrada_Final);
        if (JavaUtil.doValida(DT_Vencimento_Inicial))
            ed.setDt_vencimento(DT_Vencimento_Inicial);
        if (JavaUtil.doValida(DT_Vencimento_Final))
            ed.setDt_vencimento_final(DT_Vencimento_Final);
        if (JavaUtil.doValida(DT_Pagamento_Inicial))
            ed.setDt_pagamento(DT_Pagamento_Inicial);
        if (JavaUtil.doValida(DT_Pagamento_Final))
            ed.setDt_pagamento_final(DT_Pagamento_Final);
        if (JavaUtil.doValida(DT_Emissao_Inicial))
            ed.setDt_emissao(DT_Emissao_Inicial);
        if (JavaUtil.doValida(DT_Emissao_Final))
            ed.setDt_emissao_final(DT_Emissao_Final);

        new CompromissoRN().relCompromisso(ed);
    }


    public void geraDiario_Razao_Fornecedores(HttpServletRequest request, HttpServletResponse res) throws Excecoes {
        CompromissoED ed = new CompromissoED();

        String oid_Pessoa = request.getParameter("oid_Pessoa");
        if (oid_Pessoa != null && !oid_Pessoa.equals(""))
            ed.setOid_Pessoa(oid_Pessoa);

        String dt_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
        if (dt_Inicial != null && !dt_Inicial.equals(""))
            ed.setDt_Inicial(dt_Inicial);

        String dt_Final = request.getParameter("FT_DT_Emissao_Final");
        if (dt_Final != null && !dt_Final.equals(""))
            ed.setDt_Final(dt_Final);

        ed.setDM_Relatorio(request.getParameter("FT_DM_Relatorio"));

        new CompromissoRN().geraDiario_Razao_Fornecedores(ed, res);

    }

    public void troca_Conta(HttpServletRequest request) throws Excecoes {

        CompromissoRN compromissoRN = new CompromissoRN();
        CompromissoED ed = new CompromissoED();

        ed.setOid_Conta(new Integer(request.getParameter("oid_Conta")));
        ed.setOid_Conta_Nova(new Integer(request.getParameter("oid_Conta_Nova")));

        String oid_Unidade = request.getParameter("oid_Unidade");
        if (oid_Unidade != null && !oid_Unidade.equals("") && !oid_Unidade.equals("null"))
          ed.setOid_Unidade(new Long(request.getParameter("oid_Unidade")));

        String oid_Compromisso = request.getParameter("oid_Compromisso");
        if (oid_Compromisso != null && !oid_Compromisso.equals("") && !oid_Compromisso.equals("null"))
          ed.setOid_Compromisso(new Integer(request.getParameter("oid_Compromisso")));

        String dt_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
        if (dt_Inicial != null && !dt_Inicial.equals(""))
            ed.setDt_Inicial(dt_Inicial);

        String dt_Final = request.getParameter("FT_DT_Emissao_Final");
        if (dt_Final != null && !dt_Final.equals(""))
            ed.setDt_Final(dt_Final);


        compromissoRN.troca_Conta(ed);
    }

    public void libera_Aprova_Compromisso(HttpServletRequest request, String dm_Libera_Aprova, String acao) throws Exception {

        CompromissoPesquisaED ed = new CompromissoPesquisaED();

        String oid_Pessoa = request.getParameter("oid_Pessoa");
        String dt_Vencimento_Inicial = request.getParameter("FT_DT_Vencimento_Inicial");
        String dt_Vencimento_Final = request.getParameter("FT_DT_Vencimento_Final");
        String VL_Previsto_Inicial = request.getParameter("FT_VL_Previsto_Inicial");
        String VL_Previsto_Final = request.getParameter("FT_VL_Previsto_Final");
        String DM_Situacao = request.getParameter("FT_DM_Situacao");
        String DM_Liberado = request.getParameter("FT_DM_Liberado");
        String DM_Aprovacao = request.getParameter("FT_DM_Aprovacao");
        String oid_Unidade_Pagamento = request.getParameter("oid_Unidade_Pagamento");
        String NR_Documento = request.getParameter("FT_NR_Documento");

        if (JavaUtil.doValida(NR_Documento))
            ed.setNr_Documento(NR_Documento);

        if (JavaUtil.doValida(oid_Unidade_Pagamento))
            ed.setOid_Unidade_Pagamento(new Integer(oid_Unidade_Pagamento).intValue());
        if (JavaUtil.doValida(oid_Pessoa))
            ed.setOid_Pessoa(oid_Pessoa);
        if (JavaUtil.doValida(dt_Vencimento_Inicial))
            ed.setData_Vencimento_Inicial(dt_Vencimento_Inicial);
        if (JavaUtil.doValida(dt_Vencimento_Final))
            ed.setData_Vencimento_Final(dt_Vencimento_Final);
        if (JavaUtil.doValida(DM_Situacao))
            ed.setDM_Situacao(DM_Situacao);
        if (JavaUtil.doValida(DM_Liberado))
            ed.setDM_Liberado(DM_Liberado);
        if (JavaUtil.doValida(DM_Aprovacao))
            ed.setDM_Aprovacao(DM_Aprovacao);
        if (JavaUtil.doValida(VL_Previsto_Inicial))
            ed.setVL_Previsto_Inicial(new Double(VL_Previsto_Inicial).doubleValue());
        if (JavaUtil.doValida(VL_Previsto_Final))
            ed.setVL_Previsto_Final(new Double(VL_Previsto_Final).doubleValue());

        String oid_Usuario = request.getParameter ("oid_Usuario");
        if (JavaUtil.doValida (oid_Usuario)) {
          ed.setOID_Usuario (new Long (request.getParameter ("oid_Usuario")).longValue ());
        }

          new CompromissoRN().libera_Aprova_Compromisso(ed, dm_Libera_Aprova,acao);

    }

}
