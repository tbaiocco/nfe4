package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.*;
import com.master.rn.*;
import com.master.util.Excecoes;
import com.master.util.ed.*;


public class Cob003Bean {

    public Movimento_DuplicataED inclui(HttpServletRequest request) throws Excecoes {

        Movimento_DuplicataRN movimento_DuplicataRN = new Movimento_DuplicataRN();
        Movimento_DuplicataED ed = new Movimento_DuplicataED();

        // System.out.println("1");

        ed.setOid_Duplicata(new Long(request.getParameter("oid_Duplicata")).longValue());

        String oid_Tipo_Instrucao = request.getParameter("oid_Tipo_Instrucao");
        if (oid_Tipo_Instrucao != null && oid_Tipo_Instrucao.length() > 0 && !oid_Tipo_Instrucao.equals("null")) {
            ed.setOID_Tipo_Instrucao(new Integer(oid_Tipo_Instrucao));
        }

        ed.setOID_Carteira(new Integer(request.getParameter("oid_Carteira")));

        ed.setDt_Emissao(request.getParameter("FT_DT_Emissao"));
        ed.setDT_Movimento(request.getParameter("FT_DT_Movimento"));


        String VL_Duplicata = request.getParameter("FT_VL_Duplicata");
        if (VL_Duplicata != null && VL_Duplicata.length() > 0 && !VL_Duplicata.equals("null") && !VL_Duplicata.equals("")) {
          ed.setVl_Duplicata(new Double(request.getParameter("FT_VL_Duplicata")));
        }

        // System.out.println("2");

        String VL_Liquido = request.getParameter("FT_VL_Liquido");
        if (VL_Liquido != null && VL_Liquido.length() > 0 && !VL_Liquido.equals("null") && !VL_Liquido.equals("")) {
          ed.setVL_Liquido(new Double(request.getParameter("FT_VL_Liquido")).doubleValue());
        }

        ed.setDM_Gera_Movimento(request.getParameter("FT_DM_Gera_Movimento"));

        String oid_Bordero = request.getParameter("oid_Bordero");
        if (oid_Bordero != null && oid_Bordero.length() > 0 && !oid_Bordero.equals("null")) {
            ed.setOid_Bordero(new Integer(oid_Bordero));
        }

        String VL_Pago = request.getParameter("FT_VL_Pago");
        if (VL_Pago != null && VL_Pago.length() > 0 && !VL_Pago.equals("null") && !VL_Pago.equals("")) {
            ed.setVL_Pago(new Double(VL_Pago).doubleValue());
        }

        String VL_Cotacao_Emissao = request.getParameter("FT_VL_Cotacao_Emissao");
        if (VL_Cotacao_Emissao != null && VL_Cotacao_Emissao.length() > 0 && !VL_Cotacao_Emissao.equals("null") && !VL_Cotacao_Emissao.equals("")) {
            ed.setVL_Cotacao_Emissao(new Double(VL_Cotacao_Emissao).doubleValue());
        }

        String VL_Cotacao_Pagamento = request.getParameter("FT_VL_Cotacao_Pagamento");
        if (VL_Cotacao_Pagamento != null && VL_Cotacao_Pagamento.length() > 0 && !VL_Cotacao_Pagamento.equals("null") && !VL_Cotacao_Pagamento.equals("")) {
            ed.setVL_Cotacao_Pagamento(new Double(VL_Cotacao_Pagamento).doubleValue());
        }

        String VL_Saldo = request.getParameter("FT_VL_Saldo");
        if (VL_Saldo != null && VL_Saldo.length() > 0 && !VL_Saldo.equals("null") && !VL_Saldo.equals("")) {
            ed.setVL_Saldo(new Double(VL_Saldo).doubleValue());
        }

        String VL_Tarifa = request.getParameter("FT_VL_Tarifa");
        if (VL_Tarifa != null && VL_Tarifa.length() > 0 && !VL_Tarifa.equals("null") && !VL_Tarifa.equals("")) {
            ed.setVL_Tarifa(new Double(VL_Tarifa).doubleValue());
        }

        String VL_Taxa = request.getParameter("FT_VL_Taxa");
        if (VL_Taxa != null && VL_Taxa.length() > 0 && !VL_Taxa.equals("null") && !VL_Taxa.equals("")) {
            ed.setVL_Taxa(new Double(VL_Taxa).doubleValue());
        }

        // System.out.println("3");

        String VL_Juros = request.getParameter("FT_VL_Juros");
        if (VL_Juros != null && VL_Juros.length() > 0 && !VL_Juros.equals("null") && !VL_Juros.equals("")) {
            ed.setVL_Juros(new Double(VL_Juros).doubleValue());
        }

        String VL_Imposto_Retido1 = request.getParameter("FT_VL_Imposto_Retido1");
        if (VL_Imposto_Retido1 != null && VL_Imposto_Retido1.length() > 0 && !VL_Imposto_Retido1.equals("null") && !VL_Imposto_Retido1.equals("")) {
            ed.setVL_Imposto_Retido1(new Double(VL_Imposto_Retido1).doubleValue());
        }

        String VL_Imposto_Retido2 = request.getParameter("FT_VL_Imposto_Retido2");
        if (VL_Imposto_Retido2 != null && VL_Imposto_Retido2.length() > 0 && !VL_Imposto_Retido2.equals("null") && !VL_Imposto_Retido2.equals("")) {
            ed.setVL_Imposto_Retido2(new Double(VL_Imposto_Retido2).doubleValue());
        }

        String VL_Desconto = request.getParameter("FT_VL_Desconto");
        if (VL_Desconto != null && VL_Desconto.length() > 0 && !VL_Desconto.equals("null") && !VL_Desconto.equals("")) {
            ed.setVL_Desconto(new Double(VL_Desconto).doubleValue());
        }

        // System.out.println("4");

        ed.setDM_Tipo_Pagamento(request.getParameter("FT_DM_Tipo_Pagamento"));


        String VL_Variacao_Cambial = request.getParameter("FT_VL_Variacao_Cambial");
        if (VL_Variacao_Cambial != null && VL_Variacao_Cambial.length() > 0 && !VL_Variacao_Cambial.equals("null") && !VL_Variacao_Cambial.equals("")) {
            ed.setVL_Variacao_Cambial(new Double(VL_Variacao_Cambial).doubleValue());
        }

        // /### NEW
        String VL_Reembolso = request.getParameter("FT_VL_Reembolso");
        if (VL_Reembolso != null && VL_Reembolso.length() > 0 && !VL_Reembolso.equals("null") && !VL_Reembolso.equals("")) {
            ed.setVL_Reembolso(new Double(VL_Reembolso).doubleValue());
        }

        String VL_Juros_Reembolso = request.getParameter("FT_VL_Juros_Reembolso");
        if (VL_Juros_Reembolso != null && VL_Juros_Reembolso.length() > 0 && !VL_Juros_Reembolso.equals("null") && !VL_Juros_Reembolso.equals("")) {
            ed.setVL_Juros_Reembolso(new Double(VL_Juros_Reembolso).doubleValue());
        }

        // System.out.println("5");

        String oid_Carteira_Troca = request.getParameter("oid_Carteira_Troca");
        if (oid_Carteira_Troca != null && oid_Carteira_Troca.length() > 0 && !oid_Carteira_Troca.equals("null")) {
            ed.setOID_Carteira_Troca(new Integer(oid_Carteira_Troca));
        }

        ed.setOid_Conta_Corrente(request.getParameter("oid_Conta_Corrente"));

        ed.setNR_Documento(request.getParameter("FT_NR_Duplicata"));
        ed.setDT_Movimento_Conta_Corrente(request.getParameter("FT_DT_Movimento"));
        ed.setNM_Complemento_Historico("Bordero: " + oid_Bordero);
        ed.setDM_Debito_Credito("C");
        ed.setDM_Tipo_Lancamento("L");

        // System.out.println("6");

        String oid_Tipo_Documento = request.getParameter("oid_Tipo_Documento");
        if (oid_Tipo_Documento != null && oid_Tipo_Documento.length() > 0 && !oid_Tipo_Documento.equals("null")) {
          ed.setOid_Tipo_Documento(new Integer(request.getParameter("oid_Tipo_Documento")));
        }

        String oid_Conta = request.getParameter("oid_Conta");
        if (oid_Conta != null && oid_Conta.length() > 0 && !oid_Conta.equals("null")) {
          ed.setOid_Conta(new Integer(request.getParameter("oid_Conta")));
        }

        ed.setOid_Historico(new Integer(Parametro_FixoED.getInstancia().getOID_Historico_Bordero()));

        // System.out.println("7 n");

        ed.setVL_Lancamento(new Double(0.0));
        String VL_Lancamento = request.getParameter("FT_VL_Lancamento");
        if (VL_Lancamento != null && VL_Lancamento.length() > 0 && !VL_Lancamento.equals("null") && !VL_Lancamento.equals("")) {
          ed.setVL_Lancamento(new Double(request.getParameter("FT_VL_Lancamento")));
        }


        ed.setOid_Conhecimento(request.getParameter("oid_Conhecimento"));
        // System.out.println("Cto p/Liquidar iu->> " + ed.getOid_Conhecimento());

        return movimento_DuplicataRN.inclui(ed);
    }

    public void altera(HttpServletRequest request) throws Excecoes {

        Movimento_DuplicataRN movimento_DuplicataRN = new Movimento_DuplicataRN();
        Movimento_DuplicataED ed = new Movimento_DuplicataED();

        ed.setOid_Duplicata(new Long(request.getParameter("oid_Duplicata")).longValue());
        ed.setOid_Movimento_Duplicata(request.getParameter("oid_Movimento_Duplicata"));
        ed.setOID_Tipo_Instrucao(new Integer(request.getParameter("oid_Tipo_Instrucao")));

        ed.setDT_Movimento(request.getParameter("FT_DT_Movimento"));
        ed.setVL_Credito(new Double(request.getParameter("FT_VL_Credito")));
        ed.setVl_Duplicata(new Double(request.getParameter("FT_VL_Duplicata")));

        String VL_Debito = request.getParameter("FT_VL_Debito");
        if (!VL_Debito.equals(""))
            ed.setVL_Debito(new Double(VL_Debito));

        movimento_DuplicataRN.altera(ed);
    }

    public void estorna_Movimento(HttpServletRequest request) throws Excecoes {

        Movimento_DuplicataRN movimento_DuplicataRN = new Movimento_DuplicataRN();
        Movimento_DuplicataED ed = new Movimento_DuplicataED();

        ed.setOid_Movimento_Duplicata(request.getParameter("oid_Movimento_Duplicata"));

        movimento_DuplicataRN.estorna_Movimento(ed);
    }


    public void confere(HttpServletRequest request) throws Excecoes {

        Movimento_DuplicataRN movimento_DuplicataRN = new Movimento_DuplicataRN();
        Movimento_DuplicataPesquisaED ed = new Movimento_DuplicataPesquisaED();

        String data_Inicial = request.getParameter("FT_DT_Vencimento_Inicial");
        String data_Final = request.getParameter("FT_DT_Vencimento_Final");

        if (data_Inicial != null && !data_Inicial.equals(""))
            ed.setDt_Pgto_Inicial(data_Inicial);

        if (data_Final != null && !data_Final.equals(""))
            ed.setDt_Pgto_Final(data_Final);

        movimento_DuplicataRN.confere(ed);
    }

    public void deleta(HttpServletRequest request) throws Excecoes {

        Movimento_DuplicataRN movimento_DuplicataRN = new Movimento_DuplicataRN();
        Movimento_DuplicataED ed = new Movimento_DuplicataED();

        ed.setOid_Movimento_Duplicata(request.getParameter("oid_Movimento_Duplicata"));

        movimento_DuplicataRN.deleta(ed);
    }

    public ArrayList movimento_Duplicata_Lista(HttpServletRequest request) throws Excecoes {

        Movimento_DuplicataPesquisaED ed = new Movimento_DuplicataPesquisaED();

        String oid_Pessoa = request.getParameter("oid_Pessoa");
        String oid_Duplicata = request.getParameter("oid_Duplicata");
        String oid_Bordero = request.getParameter("oid_Bordero");

        String data_Inicial = request.getParameter("FT_DT_Vencimento_Inicial");
        String data_Final = request.getParameter("FT_DT_Vencimento_Final");
        String nr_Documento = request.getParameter("FT_NR_Documento");

        if (oid_Duplicata != null && !oid_Duplicata.equals(""))
            ed.setOid_Duplicata(new Long(oid_Duplicata).longValue());

        if (oid_Bordero != null && !oid_Bordero.equals(""))
            ed.setOid_Bordero(new Integer(oid_Bordero));

        if (oid_Pessoa != null && !oid_Pessoa.equals(""))
            ed.setOid_Pessoa(oid_Pessoa);

        if (data_Inicial != null && !data_Inicial.equals(""))
            ed.setDt_Pgto_Inicial(data_Inicial);

        if (data_Final != null && !data_Final.equals(""))
            ed.setDt_Pgto_Final(data_Final);

        if (nr_Documento != null && !nr_Documento.equals(""))
            ed.setNr_Documento(nr_Documento);

        return new Movimento_DuplicataRN().lista(ed);

    }

    public Movimento_DuplicataED getByRecord(HttpServletRequest request) throws Excecoes {

        Movimento_DuplicataED ed = new Movimento_DuplicataED();

        String oid_Movimento_Duplicata = request.getParameter("oid_Movimento_Duplicata");
        if (oid_Movimento_Duplicata != null && oid_Movimento_Duplicata.length() > 0) {
            ed.setOid_Movimento_Duplicata(request.getParameter("oid_Movimento_Duplicata"));
        }

        return new Movimento_DuplicataRN().getByRecord(ed);

    }

    public Movimento_DuplicataED consultaMovimento(long oid_Duplicata)  throws Excecoes {
        return new Movimento_DuplicataRN().consultaMovimento(oid_Duplicata);
    }


    public void geraRelatorio(HttpServletRequest req) throws Excecoes {
        Movimento_DuplicataED ed = new Movimento_DuplicataED();

        // ed.setCD_Movimento_Duplicata(req.getParameter("codigo"));
        // ed.setCD_Remessa(req.getParameter("nome"));

        Movimento_DuplicataRN geRN = new Movimento_DuplicataRN();
        geRN.geraRelatorio(ed);
    }

}
