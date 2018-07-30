package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.ModeloNotaFiscalED;
import com.master.ed.Movimento_Conta_CorrenteED;
import com.master.rn.ModeloNotaFiscalRN;
import com.master.rn.Movimento_Conta_CorrenteRN;
import com.master.util.Excecoes;

public class Fin102Bean {

    public Movimento_Conta_CorrenteED finalizaMovimento(HttpServletRequest request) throws Excecoes {


        Movimento_Conta_CorrenteED ed = new Movimento_Conta_CorrenteED();

        // System.out.println("1");

        ed.setDT_Movimento_Conta_Corrente(request.getParameter("FT_DT_Movimento_Conta_Corrente"));
        ed.setOid_Conta_Corrente(request.getParameter("oid_Conta_Corrente"));
        ed.setVL_Saldo_Inicial(new Double(request.getParameter("FT_VL_Saldo_Inicial")).doubleValue());
        ed.setVL_Saldo_Final(new Double(request.getParameter("FT_VL_Saldo_Final")).doubleValue());

        // System.out.println("99");

        return new Movimento_Conta_CorrenteRN().finalizaMovimento(ed);
    }

    public Movimento_Conta_CorrenteED inclui(HttpServletRequest request) throws Excecoes {

        Movimento_Conta_CorrenteED ed = new Movimento_Conta_CorrenteED();

        ed.setDT_Movimento_Conta_Corrente(request.getParameter("FT_DT_Movimento_Conta_Corrente"));
        ed.setOid_Conta_Corrente(request.getParameter("oid_Conta_Corrente"));
        ed.setNR_Documento(request.getParameter("FT_NR_Documento"));
        ed.setNM_Complemento_Historico(" ");
        String NM_Complemento_Historico = request.getParameter("FT_NM_Complemento_Historico");
        if (NM_Complemento_Historico != null && !NM_Complemento_Historico.equals("") && !NM_Complemento_Historico.equals("null"))
            ed.setNM_Complemento_Historico(NM_Complemento_Historico);

        ed.setDM_Debito_Credito(request.getParameter("FT_DM_Debito_Credito"));
        ed.setDM_Tipo_Lancamento("L");

        ed.setOID_Tipo_Documento(new Integer(request.getParameter("oid_Tipo_Documento")));
        ed.setOid_Lote_Pagamento(0);

        ed.setOid_Historico(new Integer(request.getParameter("oid_Historico")));

        ed.setOid_Conta(new Integer(request.getParameter("oid_Conta")).intValue());
        ed.setVL_Lancamento(new Double(request.getParameter("FT_VL_Lancamento")));

        return new Movimento_Conta_CorrenteRN().inclui(ed);
    }

    public Movimento_Conta_CorrenteED transfere(HttpServletRequest request) throws Excecoes {

        Movimento_Conta_CorrenteED ed = new Movimento_Conta_CorrenteED();

        ed.setDT_Movimento_Conta_Corrente(request.getParameter("FT_DT_Movimento_Conta_Corrente"));
        ed.setDT_Movimento_Conta_Corrente_Origem(request.getParameter("FT_DT_Movimento_Conta_Corrente_Origem"));
        ed.setOid_Conta_Corrente(request.getParameter("oid_Conta_Corrente_Origem"));
        ed.setOid_Conta_Corrente_Destino(request.getParameter("oid_Conta_Corrente"));
        ed.setNR_Documento("TRANF001");

        ed.setVL_Lancamento(new Double(request.getParameter("FT_VL_Lancamento")));
        ed.setVL_Transferencia(new Double(request.getParameter("FT_VL_Transferencia")));

        ed.setNM_Complemento_Historico(" ");
        String NM_Complemento_Historico = request.getParameter("FT_NM_Complemento_Historico");
        if (NM_Complemento_Historico != null && !NM_Complemento_Historico.equals("") && !NM_Complemento_Historico.equals("null"))
            ed.setNM_Complemento_Historico(NM_Complemento_Historico);

        return new Movimento_Conta_CorrenteRN().transfere(ed);
    }

    public Movimento_Conta_CorrenteED inclui_Saldo_Acerto_Contas(HttpServletRequest request) throws Excecoes {

        Movimento_Conta_CorrenteED ed = new Movimento_Conta_CorrenteED();

        ed.setDT_Movimento_Conta_Corrente(request.getParameter("FT_DT_Chegada"));
        ed.setOid_Lote_Pagamento(0);
        ed.setNR_Documento(request.getParameter("FT_NR_Acerto"));
        ed.setDM_Tipo_Lancamento("G");
        ed.setNM_Complemento_Historico("Saldo Acerto Contas ");

        ed.setDM_Debito_Credito("D");
        ed.setVL_Lancamento(new Double(request.getParameter("FT_VL_Saldo")));
        if (ed.getVL_Lancamento().doubleValue() < 0) {
            ed.setDM_Debito_Credito("C");
            ed.setVL_Lancamento(new Double(ed.getVL_Lancamento().doubleValue() * -1));
        }
        ed.setOID_Tipo_Documento(new Integer(request.getParameter("oid_Tipo_Documento")));
        ed.setOid_Historico(new Integer(request.getParameter("oid_Historico")));
        ed.setOid_Conta_Corrente(request.getParameter("oid_Conta_Corrente"));

        return new Movimento_Conta_CorrenteRN().inclui(ed);
    }

    public Movimento_Conta_CorrenteED inclui_Movimento_Ordem_Frete_Terceiro(HttpServletRequest request) throws Excecoes {

        Movimento_Conta_CorrenteED ed = new Movimento_Conta_CorrenteED();

        ed.setDT_Movimento_Conta_Corrente(request.getParameter("FT_DT_Emissao"));
        ed.setOid_Lote_Pagamento(0);
        ed.setNR_Documento(request.getParameter("FT_NR_Recibo"));
        ed.setDM_Tipo_Lancamento("G");
        ed.setNM_Complemento_Historico("Movimento Ordem Frete Terceiro Nr:" + request.getParameter("FT_NR_Ordem_Frete_Terceiro") + " Fornecedor: "
                + request.getParameter("FT_NM_Razao_Social_Fornecedor"));

        ed.setDM_Debito_Credito("D");
        ed.setVL_Lancamento(new Double(request.getParameter("FT_VL_Saldo")));
        if (ed.getVL_Lancamento().doubleValue() < 0) {
            ed.setDM_Debito_Credito("C");
            ed.setVL_Lancamento(new Double(ed.getVL_Lancamento().doubleValue() * -1));
        }
        ed.setOID_Tipo_Documento(new Integer(request.getParameter("oid_Tipo_Documento")));
        ed.setOid_Historico(new Integer(request.getParameter("oid_Historico")));
        ed.setOid_Conta_Corrente(request.getParameter("oid_Conta_Corrente"));

        return new Movimento_Conta_CorrenteRN().inclui(ed);
    }

    public Movimento_Conta_CorrenteED geraSaldoInicial(HttpServletRequest request) throws Excecoes {

        Movimento_Conta_CorrenteED ed = new Movimento_Conta_CorrenteED();
        ed.setDT_Movimento_Conta_Corrente(request.getParameter("FT_DT_Movimento_Conta_Corrente"));
        ed.setOid_Conta_Corrente(request.getParameter("oid_Conta_Corrente"));

        return new Movimento_Conta_CorrenteRN().geraSaldoInicial(ed);
    }

    public Movimento_Conta_CorrenteED geraLancamentoMotoristas(HttpServletRequest request) throws Excecoes {

        Movimento_Conta_CorrenteED ed = new Movimento_Conta_CorrenteED();
        ed.setDT_Movimento_Conta_Corrente(request.getParameter("FT_DT_Movimento_Conta_Corrente"));
        return new Movimento_Conta_CorrenteRN().geraLancamentoMotoristas(ed);
    }

    public Movimento_Conta_CorrenteED acertaSaldo(HttpServletRequest request) throws Excecoes {

        Movimento_Conta_CorrenteED ed = new Movimento_Conta_CorrenteED();
        ed.setDT_Inicial(request.getParameter("FT_DT_Inicial"));
        ed.setDT_Final(request.getParameter("FT_DT_Final"));
        ed.setOid_Conta_Corrente(request.getParameter("oid_Conta_Corrente"));
        return new Movimento_Conta_CorrenteRN().acertaSaldo(ed);
    }

    public void altera(HttpServletRequest request) throws Excecoes {

        Movimento_Conta_CorrenteED ed = new Movimento_Conta_CorrenteED();

        ed.setDT_Movimento_Conta_Corrente(request.getParameter("FT_DT_Movimento_Conta_Corrente"));
        ed.setNR_Documento(request.getParameter("FT_NR_Documento"));
        ed.setNM_Complemento_Historico(request.getParameter("FT_NM_Complemento_Historico"));
        ed.setDM_Debito_Credito(request.getParameter("FT_DM_Debito_Credito"));
        ed.setDM_Tipo_Lancamento(request.getParameter("FT_DM_Tipo_Lancamento"));
        ed.setOID_Tipo_Documento(new Integer(request.getParameter("oid_Tipo_Documento")));

        ed.setOid_Historico(new Integer(request.getParameter("oid_Historico")));

        ed.setOid_Conta(new Integer(request.getParameter("oid_Conta")).intValue());

        ed.setVL_Lancamento(new Double(request.getParameter("FT_VL_Lancamento")));

        ed.setOid_Movimento_Conta_Corrente(new Long(request.getParameter("oid_Movimento_Conta_Corrente")).longValue());

        ed.setNM_Complemento_Historico(" ");
        String NM_Complemento_Historico = request.getParameter("FT_NM_Complemento_Historico");
        if (NM_Complemento_Historico != null && !NM_Complemento_Historico.equals("") && !NM_Complemento_Historico.equals("null"))
            ed.setNM_Complemento_Historico(NM_Complemento_Historico);

        new Movimento_Conta_CorrenteRN().altera(ed);
    }
    public void alteraConta(HttpServletRequest request) throws Excecoes {

        Movimento_Conta_CorrenteED ed = new Movimento_Conta_CorrenteED();
        ed.setOid_Movimento_Conta_Corrente(new Long(request.getParameter("oid_Movimento_Conta_Corrente")).longValue());

        ed.setOid_Conta(new Integer(request.getParameter("oid_Conta")).intValue());

        new Movimento_Conta_CorrenteRN().alteraConta(ed);
    }

    public void deleta(HttpServletRequest request) throws Excecoes {

        Movimento_Conta_CorrenteED ed = new Movimento_Conta_CorrenteED();
        ed.setOid_Movimento_Conta_Corrente(new Long(request.getParameter("oid_Movimento_Conta_Corrente")).longValue());
        new Movimento_Conta_CorrenteRN().deleta(ed);
    }

    public ArrayList Movimento_Conta_Corrente_Lista(HttpServletRequest request) throws Excecoes {

        Movimento_Conta_CorrenteED ed = new Movimento_Conta_CorrenteED();
        ed.setOid_Conta_Corrente(request.getParameter("oid_Conta_Corrente"));
        ed.setDT_Inicial(request.getParameter("FT_DT_Movimento_Conta_Corrente_Inicial"));
        ed.setDT_Final(request.getParameter("FT_DT_Movimento_Conta_Corrente_Final"));

        String NM_Complemento_Historico = request.getParameter("FT_NM_Complemento_Historico");
        if (NM_Complemento_Historico != null && !NM_Complemento_Historico.equals("") && !NM_Complemento_Historico.equals("null"))
            ed.setNM_Complemento_Historico(NM_Complemento_Historico);

        String NR_Documento = request.getParameter("FT_NR_Documento");
        if (NR_Documento != null && !NR_Documento.equals("") && !NR_Documento.equals("null"))
            ed.setNR_Documento(NR_Documento);

        
        if (request.getParameter("FT_VL_Previsto_Inicial") != null && 
        		!request.getParameter("FT_VL_Previsto_Inicial").equals("") && 
        		!request.getParameter("FT_VL_Previsto_Inicial").equals("null")) {
            ed.setVL_1(new Double(request.getParameter("FT_VL_Previsto_Inicial")).doubleValue());
        }

        if (request.getParameter("FT_VL_Previsto_Final") != null && 
        		!request.getParameter("FT_VL_Previsto_Final").equals("") && 
        		!request.getParameter("FT_VL_Previsto_Final").equals("null")) {
            ed.setVL_2(new Double(request.getParameter("FT_VL_Previsto_Final")).doubleValue());
        }
        
        return new Movimento_Conta_CorrenteRN().lista(ed);

    }

    public ArrayList listaSaldo(HttpServletRequest request) throws Excecoes {

        Movimento_Conta_CorrenteED ed = new Movimento_Conta_CorrenteED();
        ed.setOid_Conta_Corrente(request.getParameter("oid_Conta_Corrente"));
        return new Movimento_Conta_CorrenteRN().listaSaldo(ed);

    }


    public Movimento_Conta_CorrenteED getByRecord(HttpServletRequest request) throws Excecoes {

        Movimento_Conta_CorrenteED ed = new Movimento_Conta_CorrenteED();
        String oid_Movimento_Conta_Corrente = request.getParameter("oid_Movimento_Conta_Corrente");
        if (oid_Movimento_Conta_Corrente != null && oid_Movimento_Conta_Corrente.length() > 0 && !oid_Movimento_Conta_Corrente.equals("null")) {
            ed.setOid_Movimento_Conta_Corrente(new Long(request.getParameter("oid_Movimento_Conta_Corrente")).longValue());
        }
        return new Movimento_Conta_CorrenteRN().getByRecord(ed);

    }

    public byte[] imprime_Movimento_Conta_Corrente(HttpServletRequest request, HttpServletResponse Response) throws Excecoes {

        Movimento_Conta_CorrenteED ed = new Movimento_Conta_CorrenteED();

        ed.setOid_Conta_Corrente(request.getParameter("oid_Conta_Corrente"));
        ed.setDT_Inicial(request.getParameter("FT_DT_Movimento_Conta_Corrente_Inicial"));
        ed.setDT_Final(request.getParameter("FT_DT_Movimento_Conta_Corrente_Final"));

        return new Movimento_Conta_CorrenteRN().imprime_Movimento_Conta_Corrente(ed);
    }

    public byte[] gera_Rel_Movimento_Conta_Corrente(HttpServletRequest request, HttpServletResponse Response) throws Excecoes {

        Movimento_Conta_CorrenteED ed = new Movimento_Conta_CorrenteED();

        ed.setOid_Conta_Corrente(request.getParameter("oid_Conta_Corrente"));
        if (request.getParameter("oid_Conta") != null && !request.getParameter("oid_Conta").equals("")) {
            ed.setOid_Conta(new Integer(request.getParameter("oid_Conta")).intValue());
        }
        ed.setDT_Inicial(request.getParameter("FT_DT_Movimento_Conta_Corrente_Inicial"));
        ed.setDT_Final(request.getParameter("FT_DT_Movimento_Conta_Corrente_Final"));
        ed.setDM_Relatorio(request.getParameter("FT_DM_Relatorio"));
        ed.setDM_Tipo_Conta_Corrente(request.getParameter("FT_DM_Tipo_Conta_Corrente"));
        ed.setDM_Situacao(request.getParameter("FT_DM_Situacao"));

        return new Movimento_Conta_CorrenteRN().gera_Rel_Movimento_Conta_Corrente(ed);
    }
}
