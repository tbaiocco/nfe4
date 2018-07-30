package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.Lote_PagamentoED;
import com.master.ed.Lote_PagamentoPesquisaED;
import com.master.ed.RelatorioED;
import com.master.rn.Lote_PagamentoRN;
import com.master.util.EnviaPDF;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;
 
public class Cpg005Bean extends JavaUtil {

    public Lote_PagamentoED inclui(HttpServletRequest request) throws Excecoes {
    	
        Lote_PagamentoED ed = new Lote_PagamentoED();

        ed.setOID_Fornecedor(request.getParameter("oid_Pessoa"));

        ed.setDt_Emissao(request.getParameter("FT_DT_Emissao"));
        ed.setDT_Programada(request.getParameter("FT_DT_Programada"));
        ed.setDT_Compensacao(request.getParameter("FT_DT_Compensacao"));
        ed.setNr_Documento(request.getParameter("FT_NR_Documento"));
        ed.setTx_Observacao(request.getParameter("FT_TX_Observacao"));
        ed.setDM_Copia_Cheque(request.getParameter("FT_DM_Copia_Cheque"));
        ed.setDM_Imprimir(request.getParameter("FT_DM_Imprimir"));
        ed.setNM_Favorecido(request.getParameter("FT_NM_Favorecido"));
        ed.setOID_Conta_Corrente(request.getParameter("oid_Conta_Corrente"));
        ed.setOID_Conta_Corrente_Destino(request.getParameter("oid_Conta_Corrente_Destino"));
        ed.setOid_Unidade(new Long(request.getParameter("oid_Unidade")));
        ed.setOID_Tipo_Documento(new Integer(request.getParameter("oid_Tipo_Documento")));
        ed.setVl_Lote_Pagamento(new Double(request.getParameter("FT_VL_Lote_Pagamento")));

        String oid_Compromisso = request.getParameter("oid_Compromisso");
        if (oid_Compromisso != null && !oid_Compromisso.equals("") && !oid_Compromisso.equals("null"))
            ed.setOID_Compromisso(new Long(oid_Compromisso).longValue());

        return new Lote_PagamentoRN().inclui(ed);
    }

    public void altera(HttpServletRequest request) throws Excecoes {

        Lote_PagamentoED ed = new Lote_PagamentoED();

        ed.setOid_Lote_Pagamento(new Integer(request.getParameter("oid_Lote_Pagamento")));
        ed.setDt_Emissao(request.getParameter("FT_DT_Emissao"));
        ed.setDT_Programada(request.getParameter("FT_DT_Programada"));
        ed.setDT_Compensacao(request.getParameter("FT_DT_Compensacao"));
        ed.setNr_Documento(request.getParameter("FT_NR_Documento"));
        ed.setTx_Observacao(request.getParameter("FT_TX_Observacao"));
        ed.setDM_Copia_Cheque(request.getParameter("FT_DM_Copia_Cheque"));
        ed.setDM_Imprimir(request.getParameter("FT_DM_Imprimir"));
        ed.setDM_Situacao(request.getParameter("FT_DM_Situacao"));
        ed.setNM_Favorecido(request.getParameter("FT_NM_Favorecido"));
        ed.setOID_Conta_Corrente(request.getParameter("oid_Conta_Corrente"));

        String tx_Observacao = request.getParameter("FT_TX_Observacao");
        if (!tx_Observacao.equals(""))
            ed.setTx_Observacao(tx_Observacao);

        new Lote_PagamentoRN().altera(ed);
    }

    public int altera_Compensacao(HttpServletRequest request) throws Excecoes {

        Lote_PagamentoED ed = new Lote_PagamentoED();

        ed.setDT_Compensacao(request.getParameter("FT_DT_Compensacao"));
        ed.setOid_Lote_Pagamento(new Integer(request.getParameter("oid_Lote_Pagamento")));

        return new Lote_PagamentoRN().altera_Compensacao(ed);
    }

    public int devolucao(HttpServletRequest request) throws Excecoes {

        Lote_PagamentoED ed = new Lote_PagamentoED();

        ed.setDT_Apresentacao(request.getParameter("FT_DT_Apresentacao"));
        ed.setDM_Apresentacao(request.getParameter("FT_DM_Apresentacao"));
        ed.setCD_Motivo_Retorno(request.getParameter("FT_CD_Motivo_Devolucao"));
        ed.setOid_Lote_Pagamento(new Integer(request.getParameter("oid_Lote_Pagamento")));
        ed.setOid_Motivo_Retorno(new Integer(request.getParameter("oid_Motivo_Devolucao")).intValue());

        return new Lote_PagamentoRN().devolucao(ed);
    }

    public int estorna_Compensacao(HttpServletRequest request) throws Excecoes {

        Lote_PagamentoED ed = new Lote_PagamentoED();

        ed.setOid_Lote_Pagamento(new Integer(request.getParameter("oid_Lote_Pagamento")));

        return new Lote_PagamentoRN().estorna_Compensacao(ed);
    }


    public void deleta(HttpServletRequest request) throws Excecoes {
        Lote_PagamentoED ed = new Lote_PagamentoED();

        ed.setDM_Situacao(request.getParameter("FT_DM_Situacao"));
        ed.setOid_Lote_Pagamento(new Integer(request.getParameter("oid_Lote_Pagamento")));

        new Lote_PagamentoRN().deleta(ed);
    }

    public void cancela(HttpServletRequest request) throws Excecoes {

        Lote_PagamentoED ed = new Lote_PagamentoED();

        ed.setDM_Situacao(request.getParameter("FT_DM_Situacao"));
        ed.setOid_Lote_Pagamento(new Integer(request.getParameter("oid_Lote_Pagamento")));

        new Lote_PagamentoRN().cancela(ed);
    }

    public ArrayList Lote_Pagamento_Lista(HttpServletRequest request) throws Excecoes {

        Lote_PagamentoPesquisaED ed = new Lote_PagamentoPesquisaED();

        String oid_Pessoa = request.getParameter("oid_Pessoa");
        String oid_Conta_Corrente = request.getParameter("oid_Conta_Corrente");

        String NR_Lote_Pagamento = request.getParameter("FT_NR_Lote_Pagamento");
        String NR_Compromisso = request.getParameter("FT_NR_Compromisso");
        String NR_Documento = request.getParameter("FT_NR_Documento");
        String oid_Compromisso = request.getParameter("oid_Compromisso");
        String DT_Emissao_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
        String DT_Emissao_Final = request.getParameter("FT_DT_Emissao_Final");
        String DT_Programada_Inicial = request.getParameter("FT_DT_Programada_Inicial");
        String DT_Programada_Final = request.getParameter("FT_DT_Programada_Final");
        String nm_Razao_Social = request.getParameter("FT_NM_Razao_Social");
        String NM_Favorecido = request.getParameter("FT_NM_Favorecido");
        String Vl_Lote_Pagamento = request.getParameter("FT_Vl_Lote_Pagamento");
        String DM_Situacao = request.getParameter("FT_DM_Situacao");

        if (oid_Pessoa != null && !oid_Pessoa.equals(""))
            ed.setOid_Pessoa(oid_Pessoa);

        if (oid_Conta_Corrente != null && !oid_Conta_Corrente.equals(""))
            ed.setOID_Conta_Corrente(oid_Conta_Corrente);

        if (NR_Compromisso != null && !NR_Compromisso.equals(""))
            ed.setNR_Compromisso(new Integer(NR_Compromisso));

        if (NR_Documento != null && !NR_Documento.equals("") && !NR_Documento.equals("null"))
            ed.setNR_Documento(NR_Documento);

        if (oid_Compromisso != null && !oid_Compromisso.equals("") && !oid_Compromisso.equals("null"))
            ed.setOID_Compromisso(new Long(oid_Compromisso).longValue());

        if (NR_Lote_Pagamento != null && !NR_Lote_Pagamento.equals(""))
            ed.setNr_Lote_Pagamento(new Integer(NR_Lote_Pagamento));

        if (DT_Programada_Inicial != null && !DT_Programada_Inicial.equals(""))
            ed.setDT_Programada_Inicial(DT_Programada_Inicial);

        if (DT_Programada_Final != null && !DT_Programada_Final.equals(""))
            ed.setDT_Programada_Final(DT_Programada_Final);

        if (DT_Emissao_Inicial != null && !DT_Emissao_Inicial.equals(""))
            ed.setDT_Emissao_Inicial(DT_Emissao_Inicial);

        if (DT_Emissao_Final != null && !DT_Emissao_Final.equals(""))
            ed.setDT_Emissao_Final(DT_Emissao_Final);

        if (nm_Razao_Social != null && !nm_Razao_Social.equals(""))
            ed.setNm_Razao_Social(nm_Razao_Social);

        if (NM_Favorecido != null && !NM_Favorecido.equals(""))
            ed.setNM_Favorecido(NM_Favorecido);

        if (Vl_Lote_Pagamento != null && !Vl_Lote_Pagamento.equals(""))
            ed.setVl_Lote_Pagamento(new Double(Vl_Lote_Pagamento));

        if (DM_Situacao != null && !DM_Situacao.equals(""))
            ed.setDM_Situacao(DM_Situacao);

        return new Lote_PagamentoRN().lista(ed);

    }

    public Lote_PagamentoED getByRecord(HttpServletRequest request) throws Excecoes {

        Lote_PagamentoED ed = new Lote_PagamentoED();

        String oid_Lote_Pagamento = request.getParameter("oid_Lote_Pagamento");
        if (oid_Lote_Pagamento != null && oid_Lote_Pagamento.length() > 0 && !oid_Lote_Pagamento.equals("null")) {
            ed.setOid_Lote_Pagamento(new Integer(oid_Lote_Pagamento));
        }

        String oid_Conta_Corrente = request.getParameter("oid_Conta_Corrente");
        if (oid_Conta_Corrente != null && oid_Conta_Corrente.length() > 0 && !oid_Conta_Corrente.equals("null")) {
            ed.setOID_Conta_Corrente(oid_Conta_Corrente);
        }

        String NR_Lote_Pagamento = request.getParameter("FT_NR_Lote_Pagamento");
        if (NR_Lote_Pagamento != null && NR_Lote_Pagamento.length() > 0 && !NR_Lote_Pagamento.equals("null")) {
            ed.setNr_Lote_Pagamento(new Integer(NR_Lote_Pagamento));
        }

        String NR_Documento = request.getParameter("FT_NR_Documento");
        if (NR_Documento != null && NR_Documento.length() > 0 && !NR_Documento.equals("null")) {
            ed.setNr_Documento(NR_Documento);
        }
        return new Lote_PagamentoRN().getByRecord(ed);
    }
    
    public Lote_PagamentoED getByOidLote_Pagamento(String oid_Lote_Pagamento) throws Excecoes {

        Lote_PagamentoED ed = new Lote_PagamentoED();
        if (doValida(oid_Lote_Pagamento))
        {
            ed.setOid_Lote_Pagamento(new Integer(oid_Lote_Pagamento));
            return new Lote_PagamentoRN().getByRecord(ed);
        } else return ed;
    }

    public void geraLote_Pagamento_Emissao(HttpServletRequest request, HttpServletResponse res) throws Excecoes {

        Lote_PagamentoED ed = new Lote_PagamentoED();

        String oid_Conta_Corrente = request.getParameter("oid_Conta_Corrente");
        if (String.valueOf(oid_Conta_Corrente) != null && !String.valueOf(oid_Conta_Corrente).equals("") && !String.valueOf(oid_Conta_Corrente).equals("null")) {
            ed.setOID_Conta_Corrente(oid_Conta_Corrente);
        }

        String dt_Inicial = request.getParameter("FT_DT_Inicial");
        if (dt_Inicial != null && !dt_Inicial.equals(""))
            ed.setDt_Inicial(dt_Inicial);

        String dt_Final = request.getParameter("FT_DT_Final");
        if (dt_Final != null && !dt_Final.equals(""))
            ed.setDt_Final(dt_Final);

        String DM_Compensado = request.getParameter("FT_DM_Compensado");
        if (DM_Compensado != null && !DM_Compensado.equals(""))
            ed.setDM_Compensado(DM_Compensado);

        String DM_Situacao = request.getParameter("FT_DM_Situacao");
        if (DM_Situacao != null && !DM_Situacao.equals(""))
            ed.setDM_Situacao(DM_Situacao);

        new EnviaPDF().enviaBytes(request, res, new Lote_PagamentoRN().geraLote_Pagamento_Emissao(ed));
    }

    public void geraLote_Pagamento_Unidade(HttpServletRequest request, HttpServletResponse res) throws Excecoes {

        Lote_PagamentoED ed = new Lote_PagamentoED();

        String oid_Unidade = request.getParameter("oid_Unidade");
        if (JavaUtil.doValida(oid_Unidade)) {
            ed.setOid_Unidade(new Long(oid_Unidade));
        }

        String dt_Inicial = request.getParameter("FT_DT_Inicial");
        if (dt_Inicial != null && !dt_Inicial.equals(""))
            ed.setDt_Inicial(dt_Inicial);

        String dt_Final = request.getParameter("FT_DT_Final");
        if (dt_Final != null && !dt_Final.equals(""))
            ed.setDt_Final(dt_Final);

        String DM_Compensado = request.getParameter("FT_DM_Compensado");
        if (DM_Compensado != null && !DM_Compensado.equals(""))
            ed.setDM_Compensado(DM_Compensado);

        new EnviaPDF().enviaBytes(request, res, new Lote_PagamentoRN().geraLote_Pagamento_Unidade(ed));
    }

    public void geraLote_Pagamento_Nao_Compensados(HttpServletRequest request, HttpServletResponse res) throws Excecoes {

        Lote_PagamentoED ed = new Lote_PagamentoED();

        String oid_Conta_Corrente = request.getParameter("oid_Conta_Corrente");
        if (String.valueOf(oid_Conta_Corrente) != null && !String.valueOf(oid_Conta_Corrente).equals("") && !String.valueOf(oid_Conta_Corrente).equals("null")) {
            ed.setOID_Conta_Corrente(oid_Conta_Corrente);
        }

        String dt_Inicial = request.getParameter("FT_DT_Inicial");
        if (dt_Inicial != null && !dt_Inicial.equals(""))
            ed.setDt_Inicial(dt_Inicial);

        String dt_Final = request.getParameter("FT_DT_Final");
        if (dt_Final != null && !dt_Final.equals(""))
            ed.setDt_Final(dt_Final);

        new EnviaPDF().enviaBytes(request, res, new Lote_PagamentoRN().geraLote_Pagamento_Nao_Compensados(ed));
    }

    public void geraLote_Pagamento_Programacao(HttpServletRequest request, HttpServletResponse res) throws Excecoes {

        Lote_PagamentoED ed = new Lote_PagamentoED();

        // System.out.println("oid_Cc="+request.getParameter("oid_Conta_Corrente"));

        String oid_Conta_Corrente = request.getParameter("oid_Conta_Corrente");
        if (String.valueOf(oid_Conta_Corrente) != null && !String.valueOf(oid_Conta_Corrente).equals("") && !String.valueOf(oid_Conta_Corrente).equals("null")) {
            ed.setOID_Conta_Corrente(oid_Conta_Corrente);
        }

        String dt_Inicial = request.getParameter("FT_DT_Inicial");
        if (dt_Inicial != null && !dt_Inicial.equals(""))
            ed.setDt_Inicial(dt_Inicial);

        String dt_Final = request.getParameter("FT_DT_Final");
        if (dt_Final != null && !dt_Final.equals(""))
            ed.setDt_Final(dt_Final);

        String DM_Compensado = request.getParameter("FT_DM_Compensado");
        if (DM_Compensado != null && !DM_Compensado.equals(""))
            ed.setDM_Compensado(DM_Compensado);

        new EnviaPDF().enviaBytes(request, res, new Lote_PagamentoRN().geraLote_Pagamento_Programacao(ed));
    }

    public void geraLote_Pagamento_Compensacao(HttpServletRequest request, HttpServletResponse res) throws Excecoes {

        Lote_PagamentoED ed = new Lote_PagamentoED();

        String oid_Conta_Corrente = request.getParameter("oid_Conta_Corrente");
        if (String.valueOf(oid_Conta_Corrente) != null && !String.valueOf(oid_Conta_Corrente).equals("") && !String.valueOf(oid_Conta_Corrente).equals("null")) {
            ed.setOID_Conta_Corrente(oid_Conta_Corrente);
        }

        String dt_Inicial = request.getParameter("FT_DT_Inicial");
        if (dt_Inicial != null && !dt_Inicial.equals(""))
            ed.setDt_Inicial(dt_Inicial);

        String dt_Final = request.getParameter("FT_DT_Final");
        if (dt_Final != null && !dt_Final.equals(""))
            ed.setDt_Final(dt_Final);

        new EnviaPDF().enviaBytes(request, res, new Lote_PagamentoRN().geraLote_Pagamento_Compensacao(ed));
    }

    public byte[] imprime_Documento_Pagamento(HttpServletRequest request, HttpServletResponse Response) throws Excecoes {
        return new Lote_PagamentoRN().imprime_Documento_Pagamento(carregaED_Impressao(request));
    }

    public String imprime_Documento_Pagamento_Matricial (HttpServletRequest request) throws Excecoes {
      return new Lote_PagamentoRN ().imprime_Documento_Pagamento_Matricial (carregaED_Impressao (request));
    }


    private Lote_PagamentoED carregaED_Impressao(HttpServletRequest request) throws Excecoes {
        Lote_PagamentoED ed = new Lote_PagamentoED();

        String oid_Conta_Corrente = request.getParameter("oid_Conta_Corrente");
        if (oid_Conta_Corrente != null && !oid_Conta_Corrente.equals(""))
            ed.setOID_Conta_Corrente(oid_Conta_Corrente);

        String oid_Tipo_Documento = request.getParameter("oid_Tipo_Documento");
        if (oid_Tipo_Documento != null && !oid_Tipo_Documento.equals("") && !oid_Tipo_Documento.equals("null"))
            ed.setOID_Tipo_Documento(new Integer(request.getParameter("oid_Tipo_Documento")));

        String oid_Documento_Conta_Corrente = request.getParameter("oid_Documento_Conta_Corrente");
        if (oid_Documento_Conta_Corrente != null && !oid_Documento_Conta_Corrente.equals(""))
            ed.setOid_Documento_Conta_Corrente(new Integer(request.getParameter("oid_Documento_Conta_Corrente")));

        String NR_Lote_Pagamento = request.getParameter("FT_NR_Lote_Pagamento");
        if (NR_Lote_Pagamento != null && !NR_Lote_Pagamento.equals("") && !NR_Lote_Pagamento.equals("null"))
            ed.setNr_Lote_Pagamento(new Integer(NR_Lote_Pagamento));
        ed.setNR_Lote_Pagamento_Final(new Integer(NR_Lote_Pagamento));

        String NR_Lote_Pagamento_Final = request.getParameter("FT_NR_Lote_Pagamento_Final");
        if (NR_Lote_Pagamento_Final != null && !NR_Lote_Pagamento_Final.equals("") && !NR_Lote_Pagamento_Final.equals("null"))
            ed.setNR_Lote_Pagamento_Final(new Integer(NR_Lote_Pagamento_Final));

        String NR_Documento = request.getParameter("FT_NR_Documento");
        if (NR_Documento != null && !NR_Documento.equals("") && !NR_Documento.equals("null"))
            ed.setNr_Documento(NR_Documento);

        return ed;
    }


    public byte[] imprime_Lote_Pagamento(HttpServletRequest request, HttpServletResponse Response) throws Excecoes {
        Lote_PagamentoED ed = new Lote_PagamentoED();
        
        ed.setDM_Relatorio(request.getParameter("acao"));

        String oid_Conta_Corrente = request.getParameter("oid_Conta_Corrente");
        if (oid_Conta_Corrente != null && !oid_Conta_Corrente.equals(""))
            ed.setOID_Conta_Corrente(oid_Conta_Corrente);

        String oid_Tipo_Documento = request.getParameter("oid_Tipo_Documento");
        if (oid_Tipo_Documento != null && !oid_Tipo_Documento.equals("") && !oid_Tipo_Documento.equals("null"))
            ed.setOID_Tipo_Documento(new Integer(request.getParameter("oid_Tipo_Documento")));

        String NR_Lote_Pagamento = request.getParameter("FT_NR_Lote_Pagamento");
        if (NR_Lote_Pagamento != null && !NR_Lote_Pagamento.equals("") && !NR_Lote_Pagamento.equals("null"))
            ed.setNr_Lote_Pagamento(new Integer(NR_Lote_Pagamento));
        ed.setNR_Lote_Pagamento_Final(new Integer(NR_Lote_Pagamento));

        String NR_Lote_Pagamento_Final = request.getParameter("FT_NR_Lote_Pagamento_Final");
        if (NR_Lote_Pagamento_Final != null && !NR_Lote_Pagamento_Final.equals("") && !NR_Lote_Pagamento_Final.equals("null"))
            ed.setNR_Lote_Pagamento_Final(new Integer(NR_Lote_Pagamento_Final));

        return new Lote_PagamentoRN().imprime_Lote_Pagamento(ed);

    }

    public byte[] geraLote_Pagamento_Devolvido(HttpServletRequest request, HttpServletResponse Response) throws Excecoes {
        Lote_PagamentoED ed = new Lote_PagamentoED();

        String oid_Conta_Corrente = request.getParameter("oid_Conta_Corrente");
        if (String.valueOf(oid_Conta_Corrente) != null && !String.valueOf(oid_Conta_Corrente).equals("") && !String.valueOf(oid_Conta_Corrente).equals("null")) {
            ed.setOID_Conta_Corrente(oid_Conta_Corrente);
        }

        String dt_Inicial = request.getParameter("FT_DT_Inicial");
        if (dt_Inicial != null && !dt_Inicial.equals(""))
            ed.setDt_Inicial(dt_Inicial);

        String dt_Final = request.getParameter("FT_DT_Final");
        if (dt_Final != null && !dt_Final.equals(""))
            ed.setDt_Final(dt_Final);

        return new Lote_PagamentoRN().geraLote_Pagamento_Devolvido(ed);
    }


    // /### GM Sul 28052003
    public void geraLote_Pagamento_Fornecedor(HttpServletRequest request, HttpServletResponse res) throws Excecoes {

        Lote_PagamentoED ed = new Lote_PagamentoED();

        String oid_Pessoa = request.getParameter("oid_Pessoa");
        if (String.valueOf(oid_Pessoa) != null && !String.valueOf(oid_Pessoa).equals("") && !String.valueOf(oid_Pessoa).equals("null")) {
            ed.setOid_Pessoa(oid_Pessoa);
        }
        String dt_Inicial = request.getParameter("FT_DT_Inicial");
        if (dt_Inicial != null && !dt_Inicial.equals(""))
            ed.setDt_Inicial(dt_Inicial);

        String dt_Final = request.getParameter("FT_DT_Final");
        if (dt_Final != null && !dt_Final.equals(""))
            ed.setDt_Final(dt_Final);

        String DM_Compensado = request.getParameter("FT_DM_Compensado");
        if (DM_Compensado != null && !DM_Compensado.equals(""))
            ed.setDM_Compensado(DM_Compensado);

        new EnviaPDF().enviaBytes(request, res, new Lote_PagamentoRN().geraLote_Pagamento_Fornecedor(ed));
    }

    // /### gm 13062003
    public int altera_Programacao(HttpServletRequest request) throws Excecoes {

        Lote_PagamentoED ed = new Lote_PagamentoED();

        ed.setDT_Programada(request.getParameter("FT_DT_Programada"));
        ed.setNr_Documento(request.getParameter("FT_NR_Documento"));
        ed.setOID_Conta_Corrente(request.getParameter("oid_Conta_Corrente"));
        String tx_Observacao = request.getParameter("FT_TX_Observacao");
        if (!tx_Observacao.equals(""))
            ed.setTx_Observacao(tx_Observacao);

        return new Lote_PagamentoRN().altera_Programacao(ed);
    }
    
    /** ------------ RELATÓRIOS ---------------- */
    // *** Lotes Pagamentos
    public void relLote_Pagamento(HttpServletRequest request, HttpServletResponse response) throws Excecoes {

        String Relatorio = request.getParameter("Relatorio");
        if (!doValida(Relatorio))
            throw new Mensagens("Nome do Relatório não informado!");

        RelatorioED ed = new RelatorioED(response, Relatorio);
        String oid_Conta_Corrente = request.getParameter("oid_Conta_Corrente");
        String oid_Tipo_Documento = request.getParameter("oid_Tipo_Documento");
        String DM_Situacao = request.getParameter("FT_DM_Situacao");
        
        String DT_Compensacao_Inicial = request.getParameter("FT_DT_Compensacao_Inicial");
        String DT_Compensacao_Final = request.getParameter("FT_DT_Compensacao_Final");
        String DT_Programada_Inicial = request.getParameter("FT_DT_Programada_Inicial");
        String DT_Programada_Final = request.getParameter("FT_DT_Programada_Final");
        String DT_Emissao_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
        String DT_Emissao_Final = request.getParameter("FT_DT_Emissao_Final");

        if (doValida(oid_Conta_Corrente))
            ed.setOid_conta_corrente(oid_Conta_Corrente);
        if (doValida(oid_Tipo_Documento))
            ed.setOid_tipo_documento(Integer.parseInt(oid_Tipo_Documento));
        if (doValida(DM_Situacao))
            ed.setDm_situacao(DM_Situacao);
        if (doValida(DT_Compensacao_Inicial))
            ed.setDt_compensacao(DT_Compensacao_Inicial);
        if (doValida(DT_Compensacao_Final))
            ed.setDt_compensacao_final(DT_Compensacao_Final);
        if (doValida(DT_Programada_Inicial))
            ed.setDt_programada(DT_Programada_Inicial);
        if (doValida(DT_Programada_Final))
            ed.setDt_programada_final(DT_Programada_Final);
        if (doValida(DT_Emissao_Inicial))
            ed.setDt_emissao(DT_Emissao_Inicial);
        if (doValida(DT_Emissao_Final))
            ed.setDt_emissao_final(DT_Emissao_Final);

        new Lote_PagamentoRN().relLote_Pagamento(ed);
    }

    public Lote_PagamentoED substitui_Lote_Pagamento(HttpServletRequest request) throws Excecoes {
        Lote_PagamentoED ed = new Lote_PagamentoED();
        ed.setOid_Lote_Pagamento(new Integer(request.getParameter("oid_Lote_Pagamento")));
        return new Lote_PagamentoRN().substitui_Lote_Pagamento(ed);
    }

    public void troca_Compromisso_Lote_Pagamento(HttpServletRequest request) throws Excecoes {

        Lote_PagamentoED ed = new Lote_PagamentoED();

        ed.setOid_Lote_Pagamento(new Integer(request.getParameter("oid_Lote_Pagamento")));
        ed.setOID_Compromisso(new Long(request.getParameter("oid_Compromisso")).longValue());
        ed.setOID_Compromisso_Troca(new Long(request.getParameter("oid_Compromisso_Troca")).longValue());


        new Lote_PagamentoRN().troca_Compromisso_Lote_Pagamento(ed);
    }


}