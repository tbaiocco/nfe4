package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.iu.Cob001RelBean;
import com.master.ed.DuplicataED;
import com.master.ed.DuplicataPesquisaED;
import com.master.ed.Tipo_EventoED;
import com.master.rn.DuplicataRN;
import com.master.util.EnviaPDF;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;
import com.master.util.ed.Parametro_FixoED;

public class Cob001Bean  {

    public DuplicataED inclui(HttpServletRequest request) throws Exception {

        DuplicataED ed = new DuplicataED();
        ed.setDt_Emissao(request.getParameter("FT_DT_Emissao"));
        ed.setDt_Vencimento(request.getParameter("FT_DT_Vencimento"));
        ed.setNr_Documento(request.getParameter("FT_NR_Documento"));
        String nr_Parcela = request.getParameter("FT_NR_Parcela");
        if (!nr_Parcela.equals(""))
            ed.setNr_Parcela(new Integer(nr_Parcela));

        ed.setOid_Vendedor(request.getParameter("oid_Vendedor"));
        ed.setOid_Carteira(new Integer(request.getParameter("oid_Carteira")));
        ed.setOid_Pessoa(request.getParameter("oid_Pessoa"));
        ed.setOid_Tipo_Documento(new Integer(request.getParameter("oid_Tipo_Documento")));
        ed.setOid_Unidade(new Long(request.getParameter("oid_Unidade")));
        ed.setVl_Duplicata(new Double(request.getParameter("FT_VL_Previsto")));

        String vl_Desconto_Faturamento = request.getParameter("FT_VL_Desconto_Faturamento");
        if (!vl_Desconto_Faturamento.equals(""))
            ed.setVl_Desconto_Faturamento(new Double(vl_Desconto_Faturamento));

        String vl_Taxa_Cobranca = request.getParameter("FT_VL_Taxa_Cobranca");
        if (!vl_Taxa_Cobranca.equals(""))
            ed.setVl_Taxa_Cobranca(new Double(vl_Taxa_Cobranca));

        ed.setNR_Bancario(request.getParameter("FT_NR_Bancario"));

        String vl_Juro_Mora_Dia = request.getParameter("FT_VL_Juro_Mora_Dia");
        if (!vl_Juro_Mora_Dia.equals(""))
            ed.setVL_Juro_Mora_Dia(new Double(vl_Juro_Mora_Dia));

        String vl_Multa = request.getParameter("FT_VL_Multa");
        if (!vl_Multa.equals(""))
            ed.setVL_Multa(new Double(vl_Multa));

        ed.setVl_Saldo(new Double(request.getParameter("FT_VL_Saldo")));

        ed.setTX_Observacao(request.getParameter("FT_TX_Observacao"));

        return new DuplicataRN().inclui(ed);
    }

    public DuplicataED calculaSaldo(HttpServletRequest request) throws Exception {

        DuplicataED ed = new DuplicataED();
        ed.setOID_Moeda(new Integer(request.getParameter("oid_Moeda")));
        ed.setDt_Emissao(request.getParameter("FT_DT_Emissao"));
        ed.setDT_Movimento(request.getParameter("FT_DT_Movimento"));

        if (JavaUtil.doValida(request.getParameter("FT_VL_Tarifa")))
            ed.setVL_Tarifa(new Double(request.getParameter("FT_VL_Tarifa")).doubleValue());
        if (JavaUtil.doValida(request.getParameter("FT_VL_Pago")))
          ed.setVL_Pago(new Double(request.getParameter("FT_VL_Pago")).doubleValue());
        if (JavaUtil.doValida(request.getParameter("FT_VL_Cotacao_Emissao")))
          ed.setVL_Cotacao_Emissao(new Double(request.getParameter("FT_VL_Cotacao_Emissao")).doubleValue());
        if (JavaUtil.doValida(request.getParameter("FT_VL_Cotacao_Pagamento")))
          ed.setVL_Cotacao_Pagamento(new Double(request.getParameter("FT_VL_Cotacao_Pagamento")).doubleValue());
        if (JavaUtil.doValida(request.getParameter("FT_VL_Duplicata")))
          ed.setVL_Saldo_Atual(new Double(request.getParameter("FT_VL_Duplicata")).doubleValue());
        if (JavaUtil.doValida(request.getParameter("FT_VL_Juros")))
            ed.setVL_Juros(new Double(request.getParameter("FT_VL_Juros")).doubleValue());
        if (JavaUtil.doValida(request.getParameter("FT_VL_Imposto_Retido1")))
            ed.setVL_Imposto_Retido1(new Double(request.getParameter("FT_VL_Imposto_Retido1")).doubleValue());
        if (JavaUtil.doValida(request.getParameter("FT_VL_Imposto_Retido2")))
            ed.setVL_Imposto_Retido2(new Double(request.getParameter("FT_VL_Imposto_Retido2")).doubleValue());
        if (JavaUtil.doValida(request.getParameter("FT_VL_Desconto")))
          ed.setVL_Desconto(new Double(request.getParameter("FT_VL_Desconto")).doubleValue());
        if (JavaUtil.doValida(request.getParameter("FT_VL_Desconto_Faturamento")))
          ed.setVL_Desconto_Faturamento(new Double(request.getParameter("FT_VL_Desconto_Faturamento")).doubleValue());
        if (JavaUtil.doValida(request.getParameter("FT_VL_Liquido")))
            ed.setVL_Liquido(new Double(request.getParameter("FT_VL_Liquido")).doubleValue());

        return new DuplicataRN().calculaSaldo(ed);
    }


    public void altera(HttpServletRequest request) throws Exception {

        DuplicataED ed = new DuplicataED();

        ed.setOid_Duplicata(new Long(request.getParameter("oid_Duplicata")).longValue());
        ed.setDt_Emissao(request.getParameter("FT_DT_Emissao"));
        ed.setDt_Vencimento(request.getParameter("FT_DT_Vencimento"));
        if (request.getParameter("FT_DT_Credito") != null && request.getParameter("FT_DT_Credito").length()>4){
          ed.setDT_Credito(request.getParameter("FT_DT_Credito"));
        }

        ed.setNr_Duplicata(new Integer(request.getParameter("FT_NR_Duplicata")));
        ed.setNr_Documento(request.getParameter("FT_NR_Documento"));

        String nr_Parcela = request.getParameter("FT_NR_Parcela");
        if (!nr_Parcela.equals(""))
            ed.setNr_Parcela(new Integer(nr_Parcela));
        ed.setOid_Vendedor(request.getParameter("oid_Vendedor"));
        ed.setOid_Carteira(new Integer(request.getParameter("oid_Carteira")));
        ed.setOid_Pessoa(request.getParameter("oid_Pessoa"));
        ed.setOid_Tipo_Documento(new Integer(request.getParameter("oid_Tipo_Documento")));
        ed.setOid_Unidade(new Long(request.getParameter("oid_Unidade")));

        ed.setVl_Duplicata(new Double(request.getParameter("FT_VL_Previsto")));

        String vl_Desconto_Faturamento = request.getParameter("FT_VL_Desconto_Faturamento");
        if (!vl_Desconto_Faturamento.equals(""))
            ed.setVl_Desconto_Faturamento(new Double(vl_Desconto_Faturamento));

        String vl_Taxa_Cobranca = request.getParameter("FT_VL_Taxa_Cobranca");
        if (!vl_Taxa_Cobranca.equals(""))
            ed.setVl_Taxa_Cobranca(new Double(vl_Taxa_Cobranca));

        ed.setNR_Bancario(request.getParameter("FT_NR_Bancario"));

        String vl_Juro_Mora_Dia = request.getParameter("FT_VL_Juro_Mora_Dia");
        if (!vl_Juro_Mora_Dia.equals(""))
            ed.setVL_Juro_Mora_Dia(new Double(vl_Juro_Mora_Dia));

        String vl_Multa = request.getParameter("FT_VL_Multa");
        if (!vl_Multa.equals(""))
            ed.setVL_Multa(new Double(vl_Multa));

        ed.setVl_Saldo(new Double(request.getParameter("FT_VL_Saldo")));

        ed.setTX_Observacao(request.getParameter("FT_TX_Observacao"));

        ed.setNM_Praca_Pagamento(request.getParameter("FT_NM_Praca_Pagamento"));

        new DuplicataRN().altera(ed);
    }



    public void deleta(HttpServletRequest request) throws Excecoes {

        DuplicataED ed = new DuplicataED();
        ed.setOid_Duplicata(new Long(request.getParameter("oid_Duplicata")).longValue());
        new DuplicataRN().deleta(ed);
    }

    public DuplicataED incluiParcela(HttpServletRequest request) throws Excecoes {

        String oid_Duplicata = request.getParameter("oid_Duplicata");
        String oid_Pessoa = request.getParameter("oid_Pessoa");
        String oid_Unidade = request.getParameter("oid_Unidade");
        String oid_Vendedor = request.getParameter("oid_Vendedor");
        String oid_Carteira = request.getParameter("oid_Carteira");
        String oid_Tipo_Documento = request.getParameter("oid_Tipo_Documento");

        String NR_Documento = request.getParameter("FT_NR_Documento");
        String NR_Nova_Parcela = request.getParameter("FT_NR_Nova_Parcela");
        String VL_Duplicata_Parcela = request.getParameter("FT_VL_Duplicata_Parcela");
        String DT_Vencimento_Parcela = request.getParameter("FT_DT_Vencimento_Parcela");
        String DT_Emissao = request.getParameter("FT_DT_Emissao");

        if (!JavaUtil.doValida(oid_Duplicata))
            throw new Mensagens("ID Duplicata não informado!");
        if (!JavaUtil.doValida(oid_Pessoa))
            throw new Mensagens("ID Pessoa não informado!");
        if (!JavaUtil.doValida(oid_Unidade))
            throw new Mensagens("ID Unidade não informado!");
        if (!JavaUtil.doValida(oid_Vendedor))
            throw new Mensagens("ID Vendedor não informado!");
        if (!JavaUtil.doValida(oid_Carteira))
            throw new Mensagens("ID Carteira não informado!");
        if (!JavaUtil.doValida(oid_Tipo_Documento))
            throw new Mensagens("ID Tipo Documento não informado!");
        if (!JavaUtil.doValida(NR_Documento))
            throw new Mensagens("Nº Documento não informado!");
        if (!JavaUtil.doValida(VL_Duplicata_Parcela))
            throw new Mensagens("Valor da Parcela não informado!");
        if (!JavaUtil.doValida(DT_Vencimento_Parcela))
            throw new Mensagens("Data de Vencimento não informado!");
        if (!JavaUtil.doValida(DT_Emissao))
            throw new Mensagens("Data de Emissão não informado!");

        DuplicataED ed = new DuplicataED();
        ed.setOid_Duplicata(new Long(oid_Duplicata).longValue());
        ed.setOid_Vendedor(oid_Vendedor);
        ed.setOid_Carteira(new Integer(oid_Carteira));
        ed.setOid_Pessoa(oid_Pessoa);
        ed.setOid_Tipo_Documento(new Integer(oid_Tipo_Documento));
        ed.setOid_Unidade(new Long(oid_Unidade));
        ed.setDt_Emissao(DT_Emissao);
        ed.setDt_Vencimento(DT_Vencimento_Parcela);
        ed.setNr_Documento(NR_Documento);
        //ed.setNr_Parcela(new Integer(NR_Nova_Parcela));
        ed.setVl_Duplicata(new Double(VL_Duplicata_Parcela));
        ed.setVl_Saldo(new Double(VL_Duplicata_Parcela));

        return new DuplicataRN().incluiParcela(ed);
    }

    public DuplicataED excluiParcela(HttpServletRequest request) throws Excecoes {

        String oid_Duplicata = request.getParameter("oid_Duplicata");

        if (!JavaUtil.doValida(oid_Duplicata))
            throw new Mensagens("ID Duplicata não informado!");

        DuplicataED ed = new DuplicataED();
        ed.setOid_Duplicata(new Long(oid_Duplicata).longValue());

        return new DuplicataRN().excluiParcela(ed);
    }

    public DuplicataED cancelaParcela(HttpServletRequest request) throws Excecoes {

        String oid_Duplicata = request.getParameter("oid_Duplicata");

        if (!JavaUtil.doValida(oid_Duplicata))
            throw new Mensagens("ID Duplicata não informado!");

        DuplicataED ed = new DuplicataED();
        ed.setOid_Duplicata(new Long(oid_Duplicata).longValue());

        return new DuplicataRN().cancelaParcela(ed);
    }
    

    public ArrayList listaParcela(HttpServletRequest request) throws Excecoes {

        String oid_Duplicata = request.getParameter("oid_Duplicata");
        if (!JavaUtil.doValida(oid_Duplicata))
            throw new Mensagens("ID Duplicata não informado para Listar Parcelas!");
        return new DuplicataRN().listaParcela(new DuplicataED(new Long(oid_Duplicata).longValue()));
    }

    public ArrayList duplicata_Lista(HttpServletRequest request) throws Exception {

        DuplicataPesquisaED ed = new DuplicataPesquisaED();

        String oid_Pessoa = request.getParameter("oid_Pessoa");
        if (oid_Pessoa != null && !oid_Pessoa.equals(""))
            ed.setOid_Pessoa(oid_Pessoa);

        String oid_Carteira = request.getParameter("oid_Carteira");
        if (oid_Carteira != null && !oid_Carteira.equals(""))
            ed.setOid_Carteira(new Integer(oid_Carteira));

        String cd_Duplicata = request.getParameter("FT_CD_Duplicata");
        if (cd_Duplicata != null && !cd_Duplicata.equals(""))
            ed.setNr_Duplicata(new Integer(cd_Duplicata));

        String dt_Emissao_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
        if (dt_Emissao_Inicial != null && !dt_Emissao_Inicial.equals(""))
            ed.setDT_Emissao_Inicial(dt_Emissao_Inicial);

        String dt_Emissao_Final = request.getParameter("FT_DT_Emissao_Final");
        if (dt_Emissao_Final != null && !dt_Emissao_Final.equals(""))
            ed.setDT_Emissao_Final(dt_Emissao_Final);

        String VL_Titulo_Inicial = request.getParameter("FT_VL_Titulo_Inicial");
        if (VL_Titulo_Inicial != null && !VL_Titulo_Inicial.equals(""))
            ed.setVL_Titulo_Inicial(new Double(VL_Titulo_Inicial).doubleValue());

        String VL_Titulo_Final = request.getParameter("FT_VL_Titulo_Final");
        if (VL_Titulo_Final != null && !VL_Titulo_Final.equals(""))
            ed.setVL_Titulo_Final(new Double(VL_Titulo_Final).doubleValue());

        String dt_Vencimento_Inicial = request.getParameter("FT_DT_Vencimento_Inicial");
        if (dt_Vencimento_Inicial != null && !dt_Vencimento_Inicial.equals(""))
            ed.setDT_Vencimento_Inicial(dt_Vencimento_Inicial);

        String dt_Vencimento_Final = request.getParameter("FT_DT_Vencimento_Final");
        if (dt_Vencimento_Final != null && !dt_Vencimento_Final.equals(""))
            ed.setDT_Vencimento_Final(dt_Vencimento_Final);

        String nm_Razao_Social = request.getParameter("FT_NM_Razao_Social");
        if (nm_Razao_Social != null && !nm_Razao_Social.equals(""))
            ed.setNm_Razao_Social(nm_Razao_Social);

        String NR_Documento = request.getParameter("FT_NR_Documento");
        if (JavaUtil.doValida(NR_Documento))
            ed.setNr_Documento(NR_Documento);

        String NR_Conhecimento = request.getParameter("FT_NR_Conhecimento");
        if (JavaUtil.doValida(NR_Conhecimento))
            ed.setNR_Conhecimento(NR_Conhecimento);

        String NR_CRT = request.getParameter("FT_NR_CRT");
        if (JavaUtil.doValida(NR_CRT))
            ed.setNR_CRT(NR_CRT);

        String DM_Situacao = request.getParameter("FT_DM_Situacao");
        if (JavaUtil.doValida(DM_Situacao))
            ed.setDM_Situacao(DM_Situacao);

        return new DuplicataRN().lista(ed);

    }

    public ArrayList duplicata_ListaByMoeda(HttpServletRequest request) throws Excecoes {

        DuplicataPesquisaED ed = new DuplicataPesquisaED();

        String oid_Moeda = request.getParameter("oid_Moeda");
        if (JavaUtil.doValida(oid_Moeda))
            ed.setOID_Moeda(new Integer(oid_Moeda));

        String oid_Pessoa = request.getParameter("oid_Pessoa");
        if (oid_Pessoa != null && !oid_Pessoa.equals(""))
            ed.setOid_Pessoa(oid_Pessoa);

        String oid_Carteira = request.getParameter("oid_Carteira");
        if (oid_Carteira != null && !oid_Carteira.equals(""))
            ed.setOid_Carteira(new Integer(oid_Carteira));

        String cd_Duplicata = request.getParameter("FT_CD_Duplicata");
        if (cd_Duplicata != null && !cd_Duplicata.equals(""))
            ed.setNr_Duplicata(new Integer(cd_Duplicata));

        String dt_Emissao_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
        if (dt_Emissao_Inicial != null && !dt_Emissao_Inicial.equals(""))
            ed.setData_Emissao_Inicial(dt_Emissao_Inicial);

        String dt_Emissao_Final = request.getParameter("FT_DT_Emissao_Final");
        if (dt_Emissao_Final != null && !dt_Emissao_Final.equals(""))
            ed.setData_Emissao_Final(dt_Emissao_Final);

        String dt_Vencimento_Inicial = request.getParameter("FT_DT_Vencimento_Inicial");
        if (dt_Vencimento_Inicial != null && !dt_Vencimento_Inicial.equals(""))
            ed.setData_Vencimento_Inicial(dt_Vencimento_Inicial);

        String dt_Vencimento_Final = request.getParameter("FT_DT_Vencimento_Final");
        if (dt_Vencimento_Final != null && !dt_Vencimento_Final.equals(""))
            ed.setData_Vencimento_Final(dt_Vencimento_Final);

        String nm_Razao_Social = request.getParameter("FT_NM_Razao_Social");
        if (nm_Razao_Social != null && !nm_Razao_Social.equals(""))
            ed.setNm_Razao_Social(nm_Razao_Social);

        String NR_Documento = request.getParameter("FT_NR_Documento");
        if (JavaUtil.doValida(NR_Documento))
            ed.setNr_Documento(NR_Documento);

        return new DuplicataRN().duplicata_ListaByMoeda(ed);
    }

    public DuplicataED getByRecordCTRC(HttpServletRequest request) throws Excecoes {

        DuplicataED ed = new DuplicataED();

        String oid_unidade = request.getParameter("oid_Unidade");
        if (oid_unidade != null && oid_unidade.length() > 0 && !oid_unidade.equals("null")) {
            ed.setOid_Unidade(new Long(oid_unidade));
        }
        String NR_conhecimento = request.getParameter("FT_NR_Conhecimento");
        if (NR_conhecimento != null && NR_conhecimento.length() > 0 && !NR_conhecimento.equals("null")) {
            ed.setNr_Documento(NR_conhecimento);
        }
        return new DuplicataRN().getByRecordCTRC(ed);
    }

    public DuplicataED getByRecord(HttpServletRequest request) throws Excecoes {

        DuplicataED ed = new DuplicataED();

        String oid_Duplicata = request.getParameter("oid_Duplicata");
        if (JavaUtil.doValida(oid_Duplicata))
            ed.setOid_Duplicata(new Long(oid_Duplicata).longValue());
        String NR_Duplicata = request.getParameter("FT_NR_Duplicata");
        if (JavaUtil.doValida(NR_Duplicata))
            ed.setNr_Duplicata(new Integer(NR_Duplicata));
        return new DuplicataRN().getByRecord(ed);

    }

    public DuplicataED getByNR_Duplicata(HttpServletRequest request) throws Excecoes {

        DuplicataED ed = new DuplicataED();
        String NR_Duplicata = request.getParameter("FT_NR_Duplicata");
        if (JavaUtil.doValida(NR_Duplicata))
        {
            ed.setNr_Duplicata(new Integer(NR_Duplicata));
            return new DuplicataRN().getByRecord(ed);
        } else return ed;
    }

    public DuplicataED getByNR_Documento(HttpServletRequest request) throws Excecoes {

        DuplicataED ed = new DuplicataED();
        String NR_Documento = request.getParameter("FT_NR_Documento");
        if (JavaUtil.doValida(NR_Documento))
        {
            ed.setNr_Documento(NR_Documento);
            return new DuplicataRN().getByRecord(ed);
        } else return ed;
    }

    public DuplicataED getByOid_Duplicata(HttpServletRequest request) throws Excecoes {

        DuplicataED ed = new DuplicataED();
        String oid_Duplicata = request.getParameter("oid_Duplicata");
        if (JavaUtil.doValida(oid_Duplicata))
        {
            ed.setOid_Duplicata(new Long(oid_Duplicata).longValue());
            return new DuplicataRN().getByRecord(ed);
        } else return ed;
    }

    public DuplicataED getByRecordByMoeda(HttpServletRequest request) throws Excecoes {

        DuplicataED ed = new DuplicataED();

        String oid_Duplicata = request.getParameter("oid_Duplicata");
        if (oid_Duplicata != null && oid_Duplicata.length() > 0 && !oid_Duplicata.equals("null")) {
            ed.setOid_Duplicata(new Long(oid_Duplicata).longValue());
        }

        String NR_Duplicata = request.getParameter("FT_NR_Duplicata");
        if (NR_Duplicata != null && NR_Duplicata.length() > 0 && !NR_Duplicata.equals("null")) {
            ed.setNr_Duplicata(new Integer(NR_Duplicata));
        }

        String oid_Bordero = request.getParameter("oid_Bordero");
        if (oid_Bordero != null && oid_Bordero.length() > 0 && !oid_Bordero.equals("null")) {
            ed.setOidBordero(Integer.parseInt(oid_Bordero));
        }

        return new DuplicataRN().getByRecordByMoeda(ed);

    }

    public void geraRelatorio(HttpServletRequest req) throws Excecoes {
        DuplicataED ed = new DuplicataED();
        new DuplicataRN().geraRelatorio(ed);
    }


    public byte[] geraRelTitulos (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {
      return new Cob001RelBean ().geraRelTitulos (request , Response);
    }

    public void geraRelTitVencUnidade(HttpServletRequest request, HttpServletResponse Response) throws Excecoes {
        new Cob001RelBean ().geraRelTitVencUnidade (request , Response);
    }


    public byte[] geraDiario_Auxiliar_Clientes(HttpServletRequest request, HttpServletResponse Response) throws Excecoes {
      return new Cob001RelBean ().geraRelTitulos (request , Response);
    }

    public void geraRelTitEmitidosPeriodo(HttpServletRequest request, HttpServletResponse Response) throws Excecoes {
      new Cob001RelBean ().geraRelTitEmitidosPeriodo (request , Response);
    }

    public void geraRelTitLiquidadosCart(HttpServletRequest request, HttpServletResponse Response) throws Excecoes {
      new Cob001RelBean ().geraRelTitLiquidadosCart (request , Response);
    }

    public byte[] geraRelTitCliente(HttpServletRequest request, HttpServletResponse Response) throws Excecoes {
      return new Cob001RelBean ().geraRelTitCliente (request , Response);
    }

    public byte[] geraRelTitulosDespesaCobranca(HttpServletRequest request, HttpServletResponse Response) throws Excecoes {
      return new Cob001RelBean ().geraRelTitulosDespesaCobranca (request , Response);
    }


    public byte[] geraRelFatura(HttpServletRequest request, HttpServletResponse Response) throws Exception {
      return new Cob001RelBean ().geraRelFatura (request , Response);
    }


    public ArrayList GeraEDI_Cobranca(HttpServletRequest request) throws Excecoes {
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
        return new DuplicataRN().GeraEDI_Cobranca(ed);
    }


    public ArrayList GeraMinuta_Protocolo_Entrega(HttpServletRequest request) throws Excecoes {
    	DuplicataED ed = new DuplicataED();
        String oid_Pessoa = request.getParameter("oid_Pessoa");
        if (oid_Pessoa != null && !oid_Pessoa.equals(""))
            ed.setOid_Pessoa(oid_Pessoa);

        String oid_Carteira = request.getParameter("oid_Carteira");
        if (oid_Carteira != null && !oid_Carteira.equals(""))
            ed.setOid_Carteira(new Integer(oid_Carteira));

        ed.setDT_Emissao_Inicial(request.getParameter("FT_DT_Emissao_Inicial"));
        ed.setDT_Emissao_Final(request.getParameter("FT_DT_Emissao_Final"));

        String NR_Duplicata_Inicial = request.getParameter("FT_NR_Duplicata_Inicial");
        if (String.valueOf(NR_Duplicata_Inicial) != null && !String.valueOf(NR_Duplicata_Inicial).equals("")) {
            ed.setNR_Duplicata_Inicial(new Long(NR_Duplicata_Inicial).longValue());
        }

        String NR_Duplicata_Final = request.getParameter("FT_NR_Duplicata_Final");
        if (String.valueOf(NR_Duplicata_Final) != null && !String.valueOf(NR_Duplicata_Final).equals("")) {
            ed.setNR_Duplicata_Final(new Long(NR_Duplicata_Final).longValue());
        }

        return new DuplicataRN().GeraMinuta_Protocolo_Entrega(ed);
    }

    public ArrayList GeraEDI_Cobranca_Nota_Servico(HttpServletRequest request) throws Excecoes {
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
        return new DuplicataRN().GeraEDI_Cobranca_Nota_Servico(ed);
    }

    public byte[] geraDemonstrativo_Cobranca(HttpServletRequest request, HttpServletResponse Response) throws Excecoes {
      return new Cob001RelBean ().geraDemonstrativo_Cobranca (request , Response);
    }
    public byte[] geraProtocolo_Cobranca (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {
      return new Cob001RelBean ().geraProtocolo_Cobranca (request , Response);
    }
    public void geraRelTitEDI(HttpServletRequest request, HttpServletResponse Response) throws Excecoes {
      new Cob001RelBean ().geraRelTitEDI (request , Response);
    }
    public void geraDiario_Razao_Clientes(HttpServletRequest request, HttpServletResponse Response) throws Excecoes {
      new Cob001RelBean ().geraDiario_Razao_Clientes (request , Response);
    }

}
