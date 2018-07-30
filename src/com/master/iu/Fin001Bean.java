package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.CarteiraED;
import com.master.rn.CarteiraRN;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

public class Fin001Bean {

    public CarteiraED inclui(HttpServletRequest request) throws Excecoes {
        String dataAbertura = request.getParameter("FT_DT_Abertura_Carteira");
        String dataEncerramento = request.getParameter("FT_DT_Encerramento_Carteira");
        String nr_Limite_Credito = request.getParameter("FT_NR_Limite_Credito");
        String nr_Remessa = request.getParameter("FT_NR_Remessa");
        String pe_Juros = request.getParameter("FT_PE_Juros");
        String pe_Multa = request.getParameter("FT_PE_Multa");
        String DM_Tipo_Impressao_Bloqueto = request.getParameter("FT_DM_Tipo_Impressao_Bloqueto");
        String NR_Bloqueto_Inicial = request.getParameter("FT_NR_Bloqueto_Inicial");
        String NR_Bloqueto_Atual = request.getParameter("FT_NR_Bloqueto_Atual");
        String NR_Bloqueto_Final = request.getParameter("FT_NR_Bloqueto_Final");

        CarteiraED ed = new CarteiraED();

        ed.setCd_Carteira(request.getParameter("FT_CD_Carteira"));
        // ed.setCd_Conta_Corrente(request.getParameter("cd_conta_corrente"));
        ed.setCd_Empresa_Banco(request.getParameter("FT_CD_Empresa_Banco"));
        ed.setCd_Tipo_Carteira(request.getParameter("FT_CD_Tipo_Carteira"));
        ed.setDm_Alterar_Vencimento(request.getParameter("FT_Alterar_Vencimento"));
        ed.setDm_Carteira(request.getParameter("FT_DM_Carteira"));
        ed.setDm_Conceder_Desconto(request.getParameter("FT_Conceder_Desconto"));
        ed.setDm_Pedido_Baixa(request.getParameter("FT_Pedido_Baixa"));
        ed.setDm_Protestar(request.getParameter("FT_Protestar"));
        ed.setDm_Remete_EDI(request.getParameter("FT_DM_Remete_EDI"));
        ed.setDm_Sustar_Protesto(request.getParameter("FT_Sustar_Protesto"));
        ed.setDm_Tipo_Emissao(request.getParameter("FT_DM_Tipo_Emissao"));

        ed.setDt_abertura_carteira(Data.getDataDMY());
        if (dataAbertura != null && !dataAbertura.equals(""))
            ed.setDt_abertura_carteira(dataAbertura);

        ed.setDT_Inicio_EDI(Data.getDataDMY());
        dataAbertura = request.getParameter("FT_DT_Inicio_EDI");
        if (dataAbertura != null && !dataAbertura.equals(""))
            ed.setDT_Inicio_EDI(dataAbertura);

        ed.setDt_Encerramento_Carteira("01/12/2099");
        if (dataEncerramento != null && !dataEncerramento.equals(""))
            ed.setDt_Encerramento_Carteira(dataEncerramento);

        ed.setNr_Convenio(request.getParameter("FT_NR_Convenio"));

        if (nr_Limite_Credito != null && !nr_Limite_Credito.equals(""))
            ed.setNr_Limite_Credito(new Double(nr_Limite_Credito));

        if (JavaUtil.doValida(nr_Remessa)) {
            ed.setNr_Remessa(new Integer(nr_Remessa));
        } else
            ed.setNr_Remessa(new Integer(1));

        ed.setOid_Conta_Corrente(request.getParameter("oid_Conta_Corrente"));

        if (pe_Juros != null && !pe_Juros.equals("") && !pe_Juros.equals("0"))
            ed.setPe_Juros(new Double(pe_Juros));

        if (pe_Multa != null && !pe_Multa.equals("") && !pe_Multa.equals("0"))
            ed.setPe_Multa(new Double(pe_Multa));

        if (JavaUtil.doValida(DM_Tipo_Impressao_Bloqueto)) {
            ed.setDM_Tipo_Impressao_Bloqueto(DM_Tipo_Impressao_Bloqueto);
        }
        if (JavaUtil.doValida(NR_Bloqueto_Inicial)) {
            ed.setNR_Bloqueto_Inicial(Long.parseLong(NR_Bloqueto_Inicial));
        }
        if (JavaUtil.doValida(NR_Bloqueto_Atual)) {
            ed.setNR_Bloqueto_Atual(Long.parseLong(NR_Bloqueto_Atual));
        }
        if (JavaUtil.doValida(NR_Bloqueto_Final)) {
            ed.setNR_Bloqueto_Final(Long.parseLong(NR_Bloqueto_Final));
        }
        String NR_Dias_Liberacao_Cobranca = request.getParameter("FT_NR_Dias_Liberacao_Cobranca");
        if (JavaUtil.doValida(NR_Dias_Liberacao_Cobranca) || "0".equals(NR_Dias_Liberacao_Cobranca)) {
            ed.setNR_Dias_Liberacao_Cobranca(NR_Dias_Liberacao_Cobranca);
        }

        return new CarteiraRN().inclui(ed);
    }

    public void altera(HttpServletRequest request) throws Excecoes {
        String nr_Limite_Credito = request.getParameter("FT_NR_Limite_Credito");
        String nr_Remessa = request.getParameter("FT_NR_Remessa");
        String pe_Juros = request.getParameter("FT_PE_Juros");
        String pe_Multa = request.getParameter("FT_PE_Multa");
        String DM_Tipo_Impressao_Bloqueto = request.getParameter("FT_DM_Tipo_Impressao_Bloqueto");
        String NR_Bloqueto_Inicial = request.getParameter("FT_NR_Bloqueto_Inicial");
        String NR_Bloqueto_Atual = request.getParameter("FT_NR_Bloqueto_Atual");
        String NR_Bloqueto_Final = request.getParameter("FT_NR_Bloqueto_Final");

        CarteiraED ed = new CarteiraED();
        ed.setTX_Bloqueto(request.getParameter("FT_TX_Bloqueto"));
        ed.setTX_Fatura(request.getParameter("FT_TX_Fatura"));

        ed.setCd_Carteira(request.getParameter("FT_CD_Carteira"));
        // ed.setCd_Conta_Corrente(request.getParameter("cd_conta_corrente"));
        ed.setCd_Empresa_Banco(request.getParameter("FT_CD_Empresa_Banco"));
        ed.setCd_Tipo_Carteira(request.getParameter("FT_CD_Tipo_Carteira"));
        ed.setDm_Alterar_Vencimento(request.getParameter("FT_Alterar_Vencimento"));
        ed.setDm_Carteira(request.getParameter("FT_DM_Carteira"));
        ed.setDm_Conceder_Desconto(request.getParameter("FT_Conceder_Desconto"));
        ed.setDm_Pedido_Baixa(request.getParameter("FT_Pedido_Baixa"));
        ed.setDm_Protestar(request.getParameter("FT_Protestar"));
        ed.setDm_Remete_EDI(request.getParameter("FT_DM_Remete_EDI"));
        ed.setDm_Sustar_Protesto(request.getParameter("FT_Sustar_Protesto"));
        ed.setDm_Tipo_Emissao(request.getParameter("FT_DM_Tipo_Emissao"));

        ed.setDT_Inicio_EDI(request.getParameter("FT_DT_Inicio_EDI"));

        ed.setDt_abertura_carteira(request.getParameter("FT_DT_Abertura_Carteira"));
        ed.setDt_Encerramento_Carteira(request.getParameter("FT_DT_Encerramento_Carteira"));

        ed.setNr_Convenio(request.getParameter("FT_NR_Convenio"));

        if (nr_Limite_Credito != null && !nr_Limite_Credito.equals(""))
            ed.setNr_Limite_Credito(new Double(nr_Limite_Credito));

        if (nr_Remessa != null && !nr_Remessa.equals(""))
            ed.setNr_Remessa(new Integer(nr_Remessa));

        ed.setOid_Carteira(new Integer(request.getParameter("oid_Carteira")));
        ed.setOid_Conta_Corrente(request.getParameter("oid_Conta_Corrente"));

        if (pe_Juros != null && !pe_Juros.equals("") && !pe_Juros.equals("0"))
            ed.setPe_Juros(new Double(pe_Juros));

        if (pe_Multa != null && !pe_Multa.equals("") && !pe_Multa.equals("0"))
            ed.setPe_Multa(new Double(pe_Multa));

        if (JavaUtil.doValida(DM_Tipo_Impressao_Bloqueto)) {
            ed.setDM_Tipo_Impressao_Bloqueto(DM_Tipo_Impressao_Bloqueto);
        }
        if (JavaUtil.doValida(NR_Bloqueto_Inicial)) {
            ed.setNR_Bloqueto_Inicial(Long.parseLong(NR_Bloqueto_Inicial));
        }
        if (JavaUtil.doValida(NR_Bloqueto_Atual)) {
            ed.setNR_Bloqueto_Atual(Long.parseLong(NR_Bloqueto_Atual));
        }
        if (JavaUtil.doValida(NR_Bloqueto_Final)) {
            ed.setNR_Bloqueto_Final(Long.parseLong(NR_Bloqueto_Final));
        }
        String NR_Dias_Liberacao_Cobranca = request.getParameter("FT_NR_Dias_Liberacao_Cobranca");
        if (JavaUtil.doValida(NR_Dias_Liberacao_Cobranca) || "0".equals(NR_Dias_Liberacao_Cobranca)) {
            ed.setNR_Dias_Liberacao_Cobranca(NR_Dias_Liberacao_Cobranca);
        }

        new CarteiraRN().altera(ed);
    }

    public void deleta(HttpServletRequest request) throws Excecoes {
        CarteiraRN carteiraRN = new CarteiraRN();
        CarteiraED ed = new CarteiraED();

        ed.setOid_Carteira(new Integer(request.getParameter("oid_Carteira")));

        carteiraRN.deleta(ed);
    }

    public ArrayList carteira_Lista(HttpServletRequest request) throws Excecoes {

        CarteiraED ed = new CarteiraED();

        String oid_Conta_Corrente = request.getParameter("oid_Conta_Corrente");
        String cd_Carteira = request.getParameter("FT_CD_Carteira");
        String dm_Carteira = request.getParameter("FT_DM_Carteira");

        String NM_Banco = request.getParameter("FT_NR_Carteira");

        if (oid_Conta_Corrente != null && !oid_Conta_Corrente.equals(""))
            ed.setOid_Conta_Corrente(oid_Conta_Corrente);

        if (cd_Carteira != null && !cd_Carteira.equals(""))
            ed.setCd_Carteira(cd_Carteira);

        if (dm_Carteira != null && !dm_Carteira.equals(""))
            ed.setDm_Carteira(dm_Carteira);

        if (NM_Banco != null && !NM_Banco.equals(""))
            ed.setNm_Razao_Social(NM_Banco);

        return new CarteiraRN().lista(ed);

    }

    public ArrayList carteira_ListaByMoeda(HttpServletRequest request) throws Excecoes {

        CarteiraED ed = new CarteiraED();

        String oid_Conta_Corrente = request.getParameter("oid_Conta_Corrente");
        if (oid_Conta_Corrente != null && !oid_Conta_Corrente.equals(""))
            ed.setOid_Conta_Corrente(oid_Conta_Corrente);

        String cd_Carteira = request.getParameter("FT_CD_Carteira");
        if (cd_Carteira != null && !cd_Carteira.equals(""))
            ed.setCd_Carteira(cd_Carteira);

        String dm_Carteira = request.getParameter("FT_DM_Carteira");
        if (dm_Carteira != null && !dm_Carteira.equals(""))
            ed.setDm_Carteira(dm_Carteira);

        String NM_Banco = request.getParameter("FT_NR_Carteira");
        if (NM_Banco != null && !NM_Banco.equals(""))
            ed.setNm_Razao_Social(NM_Banco);

        String oid_Moeda = request.getParameter("oid_Moeda");
        if (oid_Moeda != null && oid_Moeda.length() > 0) {
            ed.setOid_Moeda(oid_Moeda);
        }

        return new CarteiraRN().listaByMoeda(ed);

    }

    public CarteiraED getByRecord(HttpServletRequest request) throws Excecoes {

        CarteiraED ed = new CarteiraED();

        String oid_Carteira = request.getParameter("oid_Carteira");
        if (oid_Carteira != null && oid_Carteira.length() > 0) {
            ed.setOid_Carteira(new Integer(oid_Carteira));
        }

        ed.setCd_Carteira(request.getParameter("FT_CD_Carteira"));

        return new CarteiraRN().getByRecord(ed);

    }

    public CarteiraED getByCD_Carteira(String codigo) throws Excecoes {

        CarteiraED ed = new CarteiraED();
        if (JavaUtil.doValida(codigo)) {
            ed.setCd_Carteira(codigo);
            return new CarteiraRN().getByRecord(ed);
        } else return ed;
    }

    public CarteiraED getByRecordByMoeda(HttpServletRequest request) throws Excecoes {

        CarteiraED ed = new CarteiraED();

        String oid_Carteira = request.getParameter("oid_Carteira");
        if (oid_Carteira != null && oid_Carteira.length() > 0) {
            ed.setOid_Carteira(new Integer(oid_Carteira));
        }

        String oid_Moeda = request.getParameter("oid_Moeda");
        if (oid_Moeda != null && oid_Moeda.length() > 0) {
            ed.setOid_Moeda(oid_Moeda);
        }

        ed.setCd_Carteira(request.getParameter("FT_CD_Carteira"));

        return new CarteiraRN().getByRecordByMoeda(ed);

    }

    public CarteiraED getByOID(int oid) throws Excecoes {

        CarteiraED ed = new CarteiraED();
        ed.setOid_Carteira(new Integer(oid));
        return new CarteiraRN().getByRecord(ed);
    }
}