package com.master.iu;

import java.io.File;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.master.ed.Livro_FiscalED;
import com.master.ed.Mapa_ResumoED;
import com.master.ed.RelatorioED;
import com.master.rn.Livro_FiscalRN;
import com.master.rn.Mapa_ResumoRN;
import com.master.root.UsuarioBean;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;
import com.master.util.Valores;
import com.master.rn.ConhecimentoRN;
import com.master.ed.ConhecimentoED;

/**
 * André Valadas
 */

public class Fis001Bean extends JavaUtil {

    public Livro_FiscalED inclui(HttpServletRequest request)throws Excecoes{

    	Livro_FiscalED ed = new Livro_FiscalED();
    	ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));
    	ed.setDT_Entrada(request.getParameter("FT_DT_Entrada"));
    	ed.setOID_Natureza_Operacao(new Long(request.getParameter("oid_Natureza_Operacao")).longValue());
    	ed.setOID_Nota_Fiscal(request.getParameter("oid_Nota_Fiscal"));
    	ed.setTX_Observacao(getValueDef(request.getParameter("FT_TX_Observacao"), ""));
    	ed.setDM_Tipo_Livro(" ");
    	ed.setDM_Tipo_Operacao("I");

    	ed.setDM_Situacao(request.getParameter("FT_DM_Situacao"));
    	return new Livro_FiscalRN().inclui(ed);
    }

    public void altera(HttpServletRequest request)throws Excecoes {

    	Livro_FiscalED ed = new Livro_FiscalED();

    	ed.setOID_Livro_Fiscal(new Long(request.getParameter("oid_Livro_Fiscal")).longValue());
    	ed.setOID_Natureza_Operacao(new Long(request.getParameter("oid_Natureza_Operacao")).longValue());
    	ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));
    	ed.setDT_Entrada(request.getParameter("FT_DT_Entrada"));
    	ed.setTX_Observacao(request.getParameter("FT_TX_Observacao"));
    	if (!doValida(ed.getTX_Observacao())) {
    	    ed.setTX_Observacao(" ");
    	}

    	new Livro_FiscalRN().altera(ed);
    }

    public void deleta(HttpServletRequest request)throws Excecoes {

    	Livro_FiscalED ed = new Livro_FiscalED();
    	ed.setOID_Livro_Fiscal(new Long(request.getParameter("oid_Livro_Fiscal")).longValue());
    	new Livro_FiscalRN().deleta(ed);
    }

    public ArrayList Livro_Fiscal_Lista(HttpServletRequest request)throws Excecoes{

    	String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
    	if (!doValida(oid_Nota_Fiscal))
    	    throw new Mensagens("ID Nota Fiscal não informado!");

    	return new Livro_FiscalRN().lista(new Livro_FiscalED(oid_Nota_Fiscal,null));
    }

    public ArrayList Livro_Fiscal_Lista_CTRC(HttpServletRequest request)throws Excecoes{

    	String oid_Conhecimento = request.getParameter("oid_Conhecimento");
    	Livro_FiscalED livro = new Livro_FiscalED();
    	if (!doValida(oid_Conhecimento))
    	    throw new Mensagens("ID Conhecimento não informado!");
    	livro.setOID_Conhecimento(oid_Conhecimento);

    	return new Livro_FiscalRN().lista(livro);
    }

    public Livro_FiscalED getByRecord(HttpServletRequest request)throws Excecoes{

    	Livro_FiscalED ed = new Livro_FiscalED();

    	String oid_Livro_Fiscal = request.getParameter("oid_Livro_Fiscal");
    	if (doValida(oid_Livro_Fiscal)){
    	    ed.setOID_Livro_Fiscal(new Long(oid_Livro_Fiscal).longValue());
    	}
    	return new Livro_FiscalRN().getByRecord(ed);
    }

    //*** Inclui Livro Fiscal Entradas
    public void geraLivro_Fiscal_Entradas(HttpServletRequest request) throws Excecoes{

        String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
        if (!doValida(oid_Nota_Fiscal))
            throw new Excecoes("ID Nota Fiscal não informado!");

        new Livro_FiscalRN().geraLivro_Fiscal_Entradas( new Livro_FiscalED(oid_Nota_Fiscal, "NF"), "E");
    }

    //*** Inclui Livro Fiscal Saidas
    public void geraLivro_Fiscal_Saidas(HttpServletRequest request) throws Excecoes{

        String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
        if (!doValida(oid_Nota_Fiscal))
            throw new Excecoes("ID Nota Fiscal não informado!");

        new Livro_FiscalRN().geraLivro_Fiscal_Saidas( new Livro_FiscalED(oid_Nota_Fiscal, "NFS"), "S");
    }

    public void geraLivro_Fiscal_PDV(HttpServletRequest request) throws Excecoes{

        String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
        if (!doValida(oid_Nota_Fiscal))
            throw new Excecoes("ID Nota Fiscal não informado!");

        new Livro_FiscalRN().geraLivro_Fiscal_PDV(new Livro_FiscalED(oid_Nota_Fiscal, "CF"), "S");
    }

    public void geraLivro_Fiscal_CTRC(HttpServletRequest request) throws Excecoes{

    	String oid_Conhecimento = request.getParameter("oid_Conhecimento");
    	Livro_FiscalED livro = new Livro_FiscalED();

    	livro.setDT_Inicial(request.getParameter("DT_Inicial"));
    	livro.setDT_Final(request.getParameter("DT_Final"));

    	if (!doValida(oid_Conhecimento))
    	    throw new Mensagens("ID Conhecimento não informado!");
    	livro.setOID_Conhecimento(oid_Conhecimento);

    	new Livro_FiscalRN().geraLivro_Fiscal_CTRC(livro);
    }

    public void geraLivro_Fiscal_Entrada_Simplificada(HttpServletRequest request) throws Excecoes{

    	String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
    	Livro_FiscalED livro = new Livro_FiscalED();

    	livro.setDT_Inicial(request.getParameter("DT_Inicial"));
    	livro.setDT_Final(request.getParameter("DT_Final"));

    	if (!doValida(oid_Nota_Fiscal))
    	    throw new Mensagens("ID Nota Fiscal não informado!");
    	livro.setOID_Nota_Fiscal(oid_Nota_Fiscal);

    	new Livro_FiscalRN().geraLivro_Fiscal_Entrada_Simplificada(livro);
    }

    //*** Entradas e Saidas
    
    public void relRegistroFiscal(HttpServletRequest request, HttpServletResponse response) throws Excecoes {

        String oid_Unidade = request.getParameter("oid_Unidade");
        String oid_Natureza_Operacao = request.getParameter("oid_Natureza_Operacao");
        String DT_Inicial = request.getParameter("FT_DT_Inicial");
        String DT_Final = request.getParameter("FT_DT_Final");
        String Relatorio = request.getParameter("Relatorio");
        String DM_Tipo_Livro = request.getParameter("FT_DM_Tipo_Livro");
        String incluiDuplicata = request.getParameter("incluiDuplicata");
        long NR_Livro = 1;
        if (doValida(request.getParameter("FT_NR_Livro"))){
        	NR_Livro = Long.parseLong(request.getParameter("FT_NR_Livro"));
        }
        long NR_Pagina = 1;
        if (doValida(request.getParameter("FT_NR_Pagina"))){
        	NR_Pagina = Long.parseLong(request.getParameter("FT_NR_Pagina"));
        }
       

        String oid_Modelo_Nota_Fiscal = request.getParameter("oid_Modelo_Nota_Fiscal");
        String oid_Grupo_Nota_Fiscal = request.getParameter("oid_Grupo_Nota_Fiscal");

        if (!doValida(Relatorio))
            throw new Mensagens("Nome do Relatório não informado!");
        if (!doValida(oid_Unidade))
            throw new Mensagens("Unidade não informada!");
        if (!doValida(DM_Tipo_Livro))
            throw new Mensagens("Tipo de Livro não informado!");

        RelatorioED ed = new RelatorioED(response, Relatorio+DM_Tipo_Livro);
        ed.setIncluiduplicata(new Boolean(getValueDef(incluiDuplicata, "false")).booleanValue());
        if (doValida(DT_Inicial))
            ed.setDt_entrada_inicial(DT_Inicial);
        if (doValida(DT_Final))
            ed.setDt_entrada_final(DT_Final);
        if (doValida(oid_Unidade))
            ed.setOid_unidade(oid_Unidade);
        if (doValida(oid_Natureza_Operacao))  
            ed.setOid_natureza_operacao(oid_Natureza_Operacao);
        if (doValida(DM_Tipo_Livro))
            ed.setDm_tipo(DM_Tipo_Livro);
        if (doValida(oid_Modelo_Nota_Fiscal))
            ed.setOid_modelo(Integer.parseInt(oid_Modelo_Nota_Fiscal));
        if (doValida(oid_Grupo_Nota_Fiscal))
            ed.setOid_grupo_nota_fiscal(Integer.parseInt(oid_Grupo_Nota_Fiscal));

        ed.setNr_pagina(NR_Pagina);  
        ed.setNr_livro(NR_Livro);

        new Livro_FiscalRN().relRegistroFiscal(ed);
    }

//  *** Entradas e Saidas
    public void relResumoFiscal(HttpServletRequest request, HttpServletResponse response) throws Excecoes {

        String oid_Unidade = request.getParameter("oid_Unidade");
        String DT_Inicial = request.getParameter("FT_DT_Inicial");
        String DT_Final = request.getParameter("FT_DT_Final");
        String Relatorio = request.getParameter("Relatorio");
        String DM_Tipo_Livro = request.getParameter("FT_DM_Tipo_Livro");
        String incluiDuplicata = request.getParameter("incluiDuplicata");
        long NR_Livro = 1;
        if (doValida(request.getParameter("FT_NR_Livro"))){
        	NR_Livro = Long.parseLong(request.getParameter("FT_NR_Livro"));
        }
        long NR_Pagina = 1;
        if (doValida(request.getParameter("FT_NR_Pagina"))){
        	NR_Pagina = Long.parseLong(request.getParameter("FT_NR_Pagina"));
        }
        String oid_Natureza_Operacao = request.getParameter("oid_Natureza_Operacao");

        String NM_Credito_1 = request.getParameter("FT_NM_Credito_1");
        String NM_Credito_2 = request.getParameter("FT_NM_Credito_2");
        String NM_Credito_3 = request.getParameter("FT_NM_Credito_3");
        String NM_Credito_4 = request.getParameter("FT_NM_Credito_4");
        double VL_Credito_1 = Valores.converteStringToDouble(request.getParameter("FT_VL_Credito_1")) ;
        double VL_Credito_2 = Valores.converteStringToDouble(request.getParameter("FT_VL_Credito_2")) ;
        double VL_Credito_3 = Valores.converteStringToDouble(request.getParameter("FT_VL_Credito_3")) ;
        double VL_Credito_4 = Valores.converteStringToDouble(request.getParameter("FT_VL_Credito_4")) ;
        String NM_Debito_1 = request.getParameter("FT_NM_Debito_1");
        String NM_Debito_2 = request.getParameter("FT_NM_Debito_2");
        String NM_Debito_3 = request.getParameter("FT_NM_Debito_3");
        String NM_Debito_4 = request.getParameter("FT_NM_Debito_4");
        double VL_Debito_1 = Valores.converteStringToDouble(request.getParameter("FT_VL_Debito_1")) ;
        double VL_Debito_2 = Valores.converteStringToDouble(request.getParameter("FT_VL_Debito_2")) ;
        double VL_Debito_3 = Valores.converteStringToDouble(request.getParameter("FT_VL_Debito_3")) ;
        double VL_Debito_4 = Valores.converteStringToDouble(request.getParameter("FT_VL_Debito_4")) ;
        String NM_EDebito_1 = request.getParameter("FT_NM_EDebito_1");
        String NM_EDebito_2 = request.getParameter("FT_NM_EDebito_2");
        double VL_EDebito_1 = Valores.converteStringToDouble(request.getParameter("FT_VL_EDebito_1"));
        double VL_EDebito_2 = Valores.converteStringToDouble(request.getParameter("FT_VL_EDebito_2"));
        String NM_ECredito_1 = request.getParameter("FT_NM_ECredito_1");
        String NM_ECredito_2 = request.getParameter("FT_NM_ECredito_2");
        double VL_ECredito_1 = Valores.converteStringToDouble(request.getParameter("FT_VL_ECredito_1"));
        double VL_ECredito_2 = Valores.converteStringToDouble(request.getParameter("FT_VL_ECredito_2"));
        double VL_Saldo = Valores.converteStringToDouble(request.getParameter("FT_VL_Saldo_1"));
        String NM_Deducao = request.getParameter("FT_NM_Deducao_1");
        double VL_Deducao = Valores.converteStringToDouble(request.getParameter("FT_VL_Deducao_1"));

        String oid_Modelo_Nota_Fiscal = request.getParameter("oid_Modelo_Nota_Fiscal");
        String oid_Grupo_Nota_Fiscal = request.getParameter("oid_Grupo_Nota_Fiscal");

        if (!doValida(Relatorio))
            throw new Mensagens("Nome do Relatório não informado!");
        if (!doValida(oid_Unidade))
            throw new Mensagens("Unidade não informada!");
        if (!doValida(DM_Tipo_Livro))
            throw new Mensagens("Tipo de Livro não informado!");

        RelatorioED ed = new RelatorioED(response, Relatorio);
        ed.setIncluiduplicata(new Boolean(getValueDef(incluiDuplicata, "false")).booleanValue());
        if (doValida(DT_Inicial))
            ed.setDt_entrada_inicial(DT_Inicial);
        if (doValida(DT_Final))
            ed.setDt_entrada_final(DT_Final);
        if (doValida(oid_Unidade))
            ed.setOid_unidade(oid_Unidade);
        if (doValida(DM_Tipo_Livro))
            ed.setDm_tipo(DM_Tipo_Livro);
        if (doValida(oid_Modelo_Nota_Fiscal))
            ed.setOid_modelo(Integer.parseInt(oid_Modelo_Nota_Fiscal));
        if (doValida(oid_Grupo_Nota_Fiscal))
            ed.setOid_grupo_nota_fiscal(Integer.parseInt(oid_Grupo_Nota_Fiscal));
        if (doValida(oid_Natureza_Operacao))  
            ed.setOid_natureza_operacao(oid_Natureza_Operacao);

        ed.setNm_credito_1(NM_Credito_1);
        ed.setNm_credito_2(NM_Credito_2);
        ed.setNm_credito_3(NM_Credito_3);
        ed.setNm_credito_4(NM_Credito_4);
        ed.setNm_debito_1(NM_Debito_1);
        ed.setNm_debito_2(NM_Debito_2);
        ed.setNm_debito_3(NM_Debito_3);
        ed.setNm_debito_4(NM_Debito_4);
        ed.setVl_credito_1(VL_Credito_1);
        ed.setVl_credito_2(VL_Credito_2);
        ed.setVl_credito_3(VL_Credito_3);
        ed.setVl_credito_4(VL_Credito_4);
        ed.setVl_debito_1(VL_Debito_1);
        ed.setVl_debito_2(VL_Debito_2);
        ed.setVl_debito_3(VL_Debito_3);
        ed.setVl_debito_4(VL_Debito_4);
        ed.setNm_ecredito_1(NM_ECredito_1);
        ed.setNm_ecredito_2(NM_ECredito_2);
        ed.setVl_ecredito_1(VL_ECredito_1);
        ed.setVl_ecredito_2(VL_ECredito_2);
        ed.setNm_edebito_1(NM_EDebito_1);
        ed.setNm_edebito_2(NM_EDebito_2);
        ed.setVl_edebito_1(VL_EDebito_1);
        ed.setVl_edebito_2(VL_EDebito_2);
        ed.setVl_saldo(VL_Saldo);
        ed.setNm_documento(NM_Deducao);
        ed.setVl_desconto(VL_Deducao);

        ed.setNr_pagina(NR_Pagina);
        ed.setNr_livro(NR_Livro);

        new Livro_FiscalRN().relResumoFiscal(ed);
    }

    public void relRegistroISSQN(HttpServletRequest request, HttpServletResponse response) throws Excecoes {

        String oid_Unidade = request.getParameter("oid_Unidade");
        String oid_Natureza_Operacao = request.getParameter("oid_Natureza_Operacao");
        String DT_Inicial = request.getParameter("FT_DT_Inicial");
        String DT_Final = request.getParameter("FT_DT_Final");
        String Relatorio = request.getParameter("Relatorio");
        String DM_Tipo_Livro = request.getParameter("FT_DM_Tipo_Livro");
        String incluiDuplicata = request.getParameter("incluiDuplicata");
        long NR_Livro = Long.parseLong(request.getParameter("FT_NR_Livro"));
        long NR_Pagina = Long.parseLong(request.getParameter("FT_NR_Pagina"));

        String oid_Modelo_Nota_Fiscal = request.getParameter("oid_Modelo_Nota_Fiscal");
        String oid_Grupo_Nota_Fiscal = request.getParameter("oid_Grupo_Nota_Fiscal");

        if (!doValida(Relatorio))
            throw new Mensagens("Nome do Relatório não informado!");
        if (!doValida(oid_Unidade))
            throw new Mensagens("Unidade não informada!");
        if (!doValida(DM_Tipo_Livro))
            throw new Mensagens("Tipo de Livro não informado!");

        RelatorioED ed = new RelatorioED(response, Relatorio);
        ed.setIncluiduplicata(new Boolean(getValueDef(incluiDuplicata, "false")).booleanValue());
        if (doValida(DT_Inicial))
            ed.setDt_entrada_inicial(DT_Inicial);
        if (doValida(DT_Final))
            ed.setDt_entrada_final(DT_Final);
        if (doValida(oid_Unidade))
            ed.setOid_unidade(oid_Unidade);
        if (doValida(oid_Natureza_Operacao))
            ed.setOid_natureza_operacao(oid_Natureza_Operacao);
        if (doValida(DM_Tipo_Livro))
            ed.setDm_tipo(DM_Tipo_Livro);
        if (doValida(oid_Modelo_Nota_Fiscal))
            ed.setOid_modelo(Integer.parseInt(oid_Modelo_Nota_Fiscal));
        if (doValida(oid_Grupo_Nota_Fiscal))
            ed.setOid_grupo_nota_fiscal(Integer.parseInt(oid_Grupo_Nota_Fiscal));

        ed.setNr_pagina(NR_Pagina);
        ed.setNr_livro(NR_Livro);

        new Livro_FiscalRN().relRegistroISSQN(ed);
    }
    
    //*** Modelo B
    public void relRegistroModeloB(HttpServletRequest request, HttpServletResponse response) throws Excecoes {

        String oid_Unidade = request.getParameter("oid_Unidade");
        String DT_Inicial = request.getParameter("FT_DT_Inicial");
        String DT_Final = request.getParameter("FT_DT_Final");
        String Relatorio = request.getParameter("Relatorio");
        String DM_Tipo_Livro = request.getParameter("FT_DM_Tipo_Livro");
        String incluiDuplicata = request.getParameter("incluiDuplicata");
        long NR_Livro = Long.parseLong(request.getParameter("FT_NR_Livro"));
        long NR_Pagina = Long.parseLong(request.getParameter("FT_NR_Pagina"));

        String oid_Modelo_Nota_Fiscal = request.getParameter("oid_Modelo_Nota_Fiscal");
        String oid_Grupo_Nota_Fiscal = request.getParameter("oid_Grupo_Nota_Fiscal");

        if (!doValida(Relatorio))
            throw new Mensagens("Nome do Relatório não informado!");
        if (!doValida(oid_Unidade))
            throw new Mensagens("Unidade não informada!");
        if (!doValida(DM_Tipo_Livro))
            throw new Mensagens("Tipo de Livro não informado!");

        String NM_Credito_1 = request.getParameter("FT_NM_Credito_1");
        String NM_Credito_2 = request.getParameter("FT_NM_Credito_2");
        String NM_Credito_3 = request.getParameter("FT_NM_Credito_3");
        String NM_Credito_4 = request.getParameter("FT_NM_Credito_4");
        double VL_Credito_1 = Valores.converteStringToDouble(request.getParameter("FT_VL_Credito_1")) ;
        double VL_Credito_2 = Valores.converteStringToDouble(request.getParameter("FT_VL_Credito_2")) ;
        double VL_Credito_3 = Valores.converteStringToDouble(request.getParameter("FT_VL_Credito_3")) ;
        double VL_Credito_4 = Valores.converteStringToDouble(request.getParameter("FT_VL_Credito_4")) ;
        String NM_Debito_1 = request.getParameter("FT_NM_Debito_1");
        String NM_Debito_2 = request.getParameter("FT_NM_Debito_2");
        String NM_Debito_3 = request.getParameter("FT_NM_Debito_3");
        String NM_Debito_4 = request.getParameter("FT_NM_Debito_4");
        double VL_Debito_1 = Valores.converteStringToDouble(request.getParameter("FT_VL_Debito_1")) ;
        double VL_Debito_2 = Valores.converteStringToDouble(request.getParameter("FT_VL_Debito_2")) ;
        double VL_Debito_3 = Valores.converteStringToDouble(request.getParameter("FT_VL_Debito_3")) ;
        double VL_Debito_4 = Valores.converteStringToDouble(request.getParameter("FT_VL_Debito_4")) ;
        String NM_EDebito_1 = request.getParameter("FT_NM_EDebito_1");
        String NM_EDebito_2 = request.getParameter("FT_NM_EDebito_2");
        double VL_EDebito_1 = Valores.converteStringToDouble(request.getParameter("FT_VL_EDebito_1"));
        double VL_EDebito_2 = Valores.converteStringToDouble(request.getParameter("FT_VL_EDebito_2"));
        String NM_ECredito_1 = request.getParameter("FT_NM_ECredito_1");
        String NM_ECredito_2 = request.getParameter("FT_NM_ECredito_2");
        double VL_ECredito_1 = Valores.converteStringToDouble(request.getParameter("FT_VL_ECredito_1"));
        double VL_ECredito_2 = Valores.converteStringToDouble(request.getParameter("FT_VL_ECredito_2"));
        double VL_Saldo = Valores.converteStringToDouble(request.getParameter("FT_VL_Saldo_1"));
        String NM_Deducao = request.getParameter("FT_NM_Deducao_1");
        double VL_Deducao = Valores.converteStringToDouble(request.getParameter("FT_VL_Deducao_1"));
        String TX_Observacao_1 =request.getParameter("FT_TX_Observacao_1");
        String TX_Observacao_2 =request.getParameter("FT_TX_Observacao_2");

        RelatorioED ed = new RelatorioED(response, Relatorio);
        ed.setIncluiduplicata(new Boolean(getValueDef(incluiDuplicata, "false")).booleanValue());
        if (doValida(DT_Inicial))
            ed.setDt_entrada_inicial(DT_Inicial);
        if (doValida(DT_Final))
            ed.setDt_entrada_final(DT_Final);
        if (doValida(oid_Unidade))
            ed.setOid_unidade(oid_Unidade);
        if (doValida(DM_Tipo_Livro))
            ed.setDm_tipo(DM_Tipo_Livro);
        if (doValida(oid_Modelo_Nota_Fiscal))
            ed.setOid_modelo(Integer.parseInt(oid_Modelo_Nota_Fiscal));
        if (doValida(oid_Grupo_Nota_Fiscal))
            ed.setOid_grupo_nota_fiscal(Integer.parseInt(oid_Grupo_Nota_Fiscal));

        ed.setNr_pagina(NR_Pagina);
        ed.setNr_livro(NR_Livro);

        ed.setNm_credito_1(NM_Credito_1);
        ed.setNm_credito_2(NM_Credito_2);
        ed.setNm_credito_3(NM_Credito_3);
        ed.setNm_credito_4(NM_Credito_4);
        ed.setNm_debito_1(NM_Debito_1);
        ed.setNm_debito_2(NM_Debito_2);
        ed.setNm_debito_3(NM_Debito_3);
        ed.setNm_debito_4(NM_Debito_4);
        ed.setVl_credito_1(VL_Credito_1);
        ed.setVl_credito_2(VL_Credito_2);
        ed.setVl_credito_3(VL_Credito_3);
        ed.setVl_credito_4(VL_Credito_4);
        ed.setVl_debito_1(VL_Debito_1);
        ed.setVl_debito_2(VL_Debito_2);
        ed.setVl_debito_3(VL_Debito_3);
        ed.setVl_debito_4(VL_Debito_4);
        ed.setNm_ecredito_1(NM_ECredito_1);
        ed.setNm_ecredito_2(NM_ECredito_2);
        ed.setVl_ecredito_1(VL_ECredito_1);
        ed.setVl_ecredito_2(VL_ECredito_2);
        ed.setNm_edebito_1(NM_EDebito_1);
        ed.setNm_edebito_2(NM_EDebito_2);
        ed.setVl_edebito_1(VL_EDebito_1);
        ed.setVl_edebito_2(VL_EDebito_2);
        ed.setVl_saldo(VL_Saldo);
        ed.setNm_documento(NM_Deducao);
        ed.setVl_desconto(VL_Deducao);
        ed.setTx_mensagem(TX_Observacao_1);
        ed.setTx_observacao(TX_Observacao_2);
        new Livro_FiscalRN().relModeloB(ed,Relatorio);
    }
    //*** Modelo B
    public void relRegistroModeloB_Aberto(HttpServletRequest request, HttpServletResponse response) throws Excecoes {

        String oid_Unidade = request.getParameter("oid_Unidade");
        String DT_Inicial = request.getParameter("FT_DT_Inicial");
        String DT_Final = request.getParameter("FT_DT_Final");
        String Relatorio = request.getParameter("Relatorio");
        String DM_Tipo_Livro = request.getParameter("FT_DM_Tipo_Livro");
        String incluiDuplicata = request.getParameter("incluiDuplicata");
        long NR_Livro = Long.parseLong(request.getParameter("FT_NR_Livro"));
        long NR_Pagina = Long.parseLong(request.getParameter("FT_NR_Pagina"));

        String oid_Modelo_Nota_Fiscal = request.getParameter("oid_Modelo_Nota_Fiscal");
        String oid_Grupo_Nota_Fiscal = request.getParameter("oid_Grupo_Nota_Fiscal");

        if (!doValida(Relatorio))
            throw new Mensagens("Nome do Relatório não informado!");
        if (!doValida(oid_Unidade))
            throw new Mensagens("Unidade não informada!");
        if (!doValida(DM_Tipo_Livro))
            throw new Mensagens("Tipo de Livro não informado!");

        String NM_Credito_1 = request.getParameter("FT_NM_Credito_1");
        String NM_Credito_2 = request.getParameter("FT_NM_Credito_2");
        String NM_Credito_3 = request.getParameter("FT_NM_Credito_3");
        String NM_Credito_4 = request.getParameter("FT_NM_Credito_4");
        double VL_Credito_1 = Valores.converteStringToDouble(request.getParameter("FT_VL_Credito_1")) ;
        double VL_Credito_2 = Valores.converteStringToDouble(request.getParameter("FT_VL_Credito_2")) ;
        double VL_Credito_3 = Valores.converteStringToDouble(request.getParameter("FT_VL_Credito_3")) ;
        double VL_Credito_4 = Valores.converteStringToDouble(request.getParameter("FT_VL_Credito_4")) ;
        String NM_Debito_1 = request.getParameter("FT_NM_Debito_1");
        String NM_Debito_2 = request.getParameter("FT_NM_Debito_2");
        String NM_Debito_3 = request.getParameter("FT_NM_Debito_3");
        String NM_Debito_4 = request.getParameter("FT_NM_Debito_4");
        double VL_Debito_1 = Valores.converteStringToDouble(request.getParameter("FT_VL_Debito_1")) ;
        double VL_Debito_2 = Valores.converteStringToDouble(request.getParameter("FT_VL_Debito_2")) ;
        double VL_Debito_3 = Valores.converteStringToDouble(request.getParameter("FT_VL_Debito_3")) ;
        double VL_Debito_4 = Valores.converteStringToDouble(request.getParameter("FT_VL_Debito_4")) ;
        String NM_EDebito_1 = request.getParameter("FT_NM_EDebito_1");
        String NM_EDebito_2 = request.getParameter("FT_NM_EDebito_2");
        double VL_EDebito_1 = Valores.converteStringToDouble(request.getParameter("FT_VL_EDebito_1"));
        double VL_EDebito_2 = Valores.converteStringToDouble(request.getParameter("FT_VL_EDebito_2"));
        String NM_ECredito_1 = request.getParameter("FT_NM_ECredito_1");
        String NM_ECredito_2 = request.getParameter("FT_NM_ECredito_2");
        double VL_ECredito_1 = Valores.converteStringToDouble(request.getParameter("FT_VL_ECredito_1"));
        double VL_ECredito_2 = Valores.converteStringToDouble(request.getParameter("FT_VL_ECredito_2"));
        double VL_Saldo = Valores.converteStringToDouble(request.getParameter("FT_VL_Saldo_1"));
        String NM_Deducao = request.getParameter("FT_NM_Deducao_1");
        double VL_Deducao = Valores.converteStringToDouble(request.getParameter("FT_VL_Deducao_1"));
        String TX_Observacao_1 =request.getParameter("FT_TX_Observacao_1");
        String TX_Observacao_2 =request.getParameter("FT_TX_Observacao_2");

        RelatorioED ed = new RelatorioED(response, Relatorio);
        ed.setIncluiduplicata(new Boolean(getValueDef(incluiDuplicata, "false")).booleanValue());
        if (doValida(DT_Inicial))
            ed.setDt_entrada_inicial(DT_Inicial);
        if (doValida(DT_Final))
            ed.setDt_entrada_final(DT_Final);
        if (doValida(oid_Unidade))
            ed.setOid_unidade(oid_Unidade);
        if (doValida(DM_Tipo_Livro))
            ed.setDm_tipo(DM_Tipo_Livro);
        if (doValida(oid_Modelo_Nota_Fiscal))
            ed.setOid_modelo(Integer.parseInt(oid_Modelo_Nota_Fiscal));
        if (doValida(oid_Grupo_Nota_Fiscal))
            ed.setOid_grupo_nota_fiscal(Integer.parseInt(oid_Grupo_Nota_Fiscal));

        ed.setNr_pagina(NR_Pagina);
        ed.setNr_livro(NR_Livro);

        ed.setNm_credito_1(NM_Credito_1);
        ed.setNm_credito_2(NM_Credito_2);
        ed.setNm_credito_3(NM_Credito_3);
        ed.setNm_credito_4(NM_Credito_4);
        ed.setNm_debito_1(NM_Debito_1);
        ed.setNm_debito_2(NM_Debito_2);
        ed.setNm_debito_3(NM_Debito_3);
        ed.setNm_debito_4(NM_Debito_4);
        ed.setVl_credito_1(VL_Credito_1);
        ed.setVl_credito_2(VL_Credito_2);
        ed.setVl_credito_3(VL_Credito_3);
        ed.setVl_credito_4(VL_Credito_4);
        ed.setVl_debito_1(VL_Debito_1);
        ed.setVl_debito_2(VL_Debito_2);
        ed.setVl_debito_3(VL_Debito_3);
        ed.setVl_debito_4(VL_Debito_4);
        ed.setNm_ecredito_1(NM_ECredito_1);
        ed.setNm_ecredito_2(NM_ECredito_2);
        ed.setVl_ecredito_1(VL_ECredito_1);
        ed.setVl_ecredito_2(VL_ECredito_2);
        ed.setNm_edebito_1(NM_EDebito_1);
        ed.setNm_edebito_2(NM_EDebito_2);
        ed.setVl_edebito_1(VL_EDebito_1);
        ed.setVl_edebito_2(VL_EDebito_2);
        ed.setVl_saldo(VL_Saldo);
        ed.setNm_documento(NM_Deducao);
        ed.setVl_desconto(VL_Deducao);
        ed.setTx_mensagem(TX_Observacao_1);
        ed.setTx_observacao(TX_Observacao_2);
        new Livro_FiscalRN().relModeloB_Aberto(ed,Relatorio);
    }
    //***  Bonificação Recebida
    public void relBonificaoRecebida(HttpServletRequest request, HttpServletResponse response) throws Excecoes {

        String oid_Unidade = request.getParameter("oid_Unidade");
        String DT_Inicial = request.getParameter("FT_DT_Inicial");
        String DT_Final = request.getParameter("FT_DT_Final");
        String Relatorio = request.getParameter("Relatorio");
        String DM_Tipo_Livro = request.getParameter("FT_DM_Tipo_Livro");
        String incluiDuplicata = request.getParameter("incluiDuplicata");
        long NR_Livro = Long.parseLong(request.getParameter("FT_NR_Livro"));
        long NR_Pagina = Long.parseLong(request.getParameter("FT_NR_Pagina"));
        String oid_Modelo_Nota_Fiscal = request.getParameter("oid_Modelo_Nota_Fiscal");
        String oid_Grupo_Nota_Fiscal = request.getParameter("oid_Grupo_Nota_Fiscal");

        if (!doValida(Relatorio))
            throw new Mensagens("Nome do Relatório não informado!");
        if (!doValida(oid_Unidade))
            throw new Mensagens("Unidade não informada!");
        if (!doValida(DM_Tipo_Livro))
            throw new Mensagens("Tipo de Livro não informado!");

        RelatorioED ed = new RelatorioED(response, Relatorio);
        ed.setIncluiduplicata(new Boolean(getValueDef(incluiDuplicata, "false")).booleanValue());
        if (doValida(DT_Inicial))
            ed.setDt_entrada_inicial(DT_Inicial);
        if (doValida(DT_Final))
            ed.setDt_entrada_final(DT_Final);
        if (doValida(oid_Unidade))
            ed.setOid_unidade(oid_Unidade);
        if (doValida(DM_Tipo_Livro))
            ed.setDm_tipo(DM_Tipo_Livro);
        if (doValida(oid_Modelo_Nota_Fiscal))
            ed.setOid_modelo(Integer.parseInt(oid_Modelo_Nota_Fiscal));
        if (doValida(oid_Grupo_Nota_Fiscal))
            ed.setOid_grupo_nota_fiscal(Integer.parseInt(oid_Grupo_Nota_Fiscal));

        ed.setNr_pagina(NR_Pagina);
        ed.setNr_livro(NR_Livro);

        new Livro_FiscalRN().relBonificaoRecebida(ed);
    }

    //***  Bonificação Concedida
    public void relBonificaoConcedida(HttpServletRequest request, HttpServletResponse response) throws Excecoes {

        String oid_Unidade = request.getParameter("oid_Unidade");
        String DT_Inicial = request.getParameter("FT_DT_Inicial");
        String DT_Final = request.getParameter("FT_DT_Final");
        String Relatorio = request.getParameter("Relatorio");
        String DM_Tipo_Livro = request.getParameter("FT_DM_Tipo_Livro");
        String incluiDuplicata = request.getParameter("incluiDuplicata");
        long NR_Livro = Long.parseLong(request.getParameter("FT_NR_Livro"));
        long NR_Pagina = Long.parseLong(request.getParameter("FT_NR_Pagina"));
        String oid_Modelo_Nota_Fiscal = request.getParameter("oid_Modelo_Nota_Fiscal");
        String oid_Grupo_Nota_Fiscal = request.getParameter("oid_Grupo_Nota_Fiscal");

        if (!doValida(Relatorio))
            throw new Mensagens("Nome do Relatório não informado!");
        if (!doValida(oid_Unidade))
            throw new Mensagens("Unidade não informada!");
        if (!doValida(DM_Tipo_Livro))
            throw new Mensagens("Tipo de Livro não informado!");

        RelatorioED ed = new RelatorioED(response, Relatorio);
        ed.setIncluiduplicata(new Boolean(getValueDef(incluiDuplicata, "false")).booleanValue());
        if (doValida(DT_Inicial))
            ed.setDt_entrada_inicial(DT_Inicial);
        if (doValida(DT_Final))
            ed.setDt_entrada_final(DT_Final);
        if (doValida(oid_Unidade))
            ed.setOid_unidade(oid_Unidade);
        if (doValida(DM_Tipo_Livro))
            ed.setDm_tipo(DM_Tipo_Livro);

        if (doValida(oid_Modelo_Nota_Fiscal))
            ed.setOid_modelo(Integer.parseInt(oid_Modelo_Nota_Fiscal));
        if (doValida(oid_Grupo_Nota_Fiscal))
            ed.setOid_grupo_nota_fiscal(Integer.parseInt(oid_Grupo_Nota_Fiscal));

        ed.setNr_pagina(NR_Pagina);
        ed.setNr_livro(NR_Livro);

        new Livro_FiscalRN().relBonificaoConcedida(ed);
    }

    //*** Validação do Contribuinte
    public void validaContribuinte(HttpServletRequest request) throws Exception {

        String oid_Unidade = UsuarioBean.getUsuarioCorrente(request).edUnidade.getOID_Pessoa();
        String NM_Arquivo = request.getParameter("FT_NM_Arquivo");

        if (!doValida(oid_Unidade))
            throw new Mensagens("Unidade não informada!");
        if (!doValida(NM_Arquivo))
            throw new Mensagens("Arquivo não informado!");
        if (!new File(NM_Arquivo).exists())
            throw new Mensagens("Arquivo não encontrado!");

        RelatorioED ed = new RelatorioED();
        ed.setOid_unidade(oid_Unidade);
        ed.setNm_arquivo(NM_Arquivo);

        new Livro_FiscalRN().validaContribuinte(ed);
    }

    //*** Retório de Validação do Contribuinte
    public void relValidacaoContribuinte(HttpServletRequest request, HttpServletResponse response) throws Excecoes {

        String oid_Unidade = UsuarioBean.getUsuarioCorrente(request).edUnidade.getOID_Pessoa();
        String Relatorio = request.getParameter("Relatorio");
        String dmTipo = request.getParameter("FT_DM_Tipo");

        if (!doValida(Relatorio))
            throw new Mensagens("Nome do Relatório não informado!");
        if (!doValida(oid_Unidade))
            throw new Mensagens("Unidade não informada!");
        if (!doValida(dmTipo))
            throw new Mensagens("Tipo de Pessoa a Validar não informada!");


        RelatorioED ed = new RelatorioED(response, Relatorio+dmTipo);
        ed.setOid_unidade(oid_Unidade);
        ed.setDm_tipo(dmTipo);

        new Livro_FiscalRN().relValidacaoContribuinte(ed);
    }

    /** MAPA RESUMO PDV
     *  lista<Livro_FiscalED>
     */
    public void mapaResumoPDV(HttpServletRequest request, HttpSession session) throws Exception {

        // MAPA RESUMO
        String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
        if (!doValida(oid_Nota_Fiscal))
            throw new Mensagens("Nota não informada!");

        Mapa_ResumoED edMapa = new Mapa_ResumoED(oid_Nota_Fiscal);
        edMapa.setDT_Emissao(request.getParameter("FT_DT_Emissao"));
        edMapa.setNR_Documento(getValueDef(request.getParameter("FT_NR_Documento"),0));
        edMapa.setNR_Ultimo_Documento(getValueDef(request.getParameter("FT_NR_Ultimo_Documento"),0));
        edMapa.setNR_Reducao(getValueDef(request.getParameter("FT_NR_Reducao"),0));
        edMapa.setVL_GT_Inicial(Valores.converteStringToDouble(request.getParameter("FT_VL_GT_Inicial")));
        edMapa.setVL_GT_Final(Valores.converteStringToDouble(request.getParameter("FT_VL_GT_Final")));
        edMapa.setVL_Cancelamento(Valores.converteStringToDouble(request.getParameter("FT_VL_Cancelamento")));
        edMapa.setVL_Desconto(Valores.converteStringToDouble(request.getParameter("FT_VL_Desconto")));
        new Mapa_ResumoRN().gravar(edMapa);

        // DIFERENCAS DE ALIQUOTAS
        ArrayList lista = (ArrayList) session.getAttribute("lista");
        if (lista == null || lista.size() < 1)
            throw new Mensagens("Lista de ICMS vazia! Execute novamente a consulta!");

        ArrayList aliquotas = new ArrayList();
        //*** Carrega Valores da Tela
        for (int i=0; i<lista.size(); i++)
        {
            Livro_FiscalED edFiscal = (Livro_FiscalED) lista.get(i);
            edFiscal.setVL_Contabil(Valores.converteStringToDouble(request.getParameter("FT_VL_Contabil"+i)));
            edFiscal.setVL_Base_Calculo(Valores.converteStringToDouble(request.getParameter("FT_VL_Base_Calculo"+i)));
            edFiscal.setPE_Aliquota(Valores.converteStringToDouble(request.getParameter("FT_PE_Aliquota"+i)));
            edFiscal.setVL_Imposto_Creditado(Valores.converteStringToDouble(request.getParameter("FT_VL_Imposto_Creditado"+i)));
            edFiscal.setVL_Imposto(Valores.converteStringToDouble(request.getParameter("FT_VL_Imposto"+i)));
            edFiscal.setVL_Outro(Valores.converteStringToDouble(request.getParameter("FT_VL_Outro"+i)));
            aliquotas.add(edFiscal);
        }
        new Livro_FiscalRN().alteraAliquotas(aliquotas);
    }

  public byte[] geraLivro_Fiscal_Conhecimento (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {

    ConhecimentoED ed = new ConhecimentoED ();

    String NR_Pagina_Inicial = request.getParameter ("FT_NR_Pagina_Inicial");
    if (String.valueOf (NR_Pagina_Inicial) != null && !String.valueOf (NR_Pagina_Inicial).equals ("")) {
      ed.setNR_Pagina_Inicial (new Long (NR_Pagina_Inicial).longValue ());
    }

    String Dt_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
    if (String.valueOf (Dt_Emissao_Inicial) != null && !String.valueOf (Dt_Emissao_Inicial).equals ("")) {
      ed.setDt_Emissao_Inicial (Dt_Emissao_Inicial);
    }

    String Dt_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");
    if (String.valueOf (Dt_Emissao_Final) != null && !String.valueOf (Dt_Emissao_Final).equals ("")) {
      ed.setDt_Emissao_Final (Dt_Emissao_Final);
    }

    String Dm_Situacao = request.getParameter ("FT_DM_Situacao");
    if (String.valueOf (Dm_Situacao) != null && !String.valueOf (Dm_Situacao).equals ("")) {
      ed.setDm_Situacao_Cobranca (Dm_Situacao);
    }

    String Cd_CFO = request.getParameter ("FT_Cd_CFO");
    if (String.valueOf (Cd_CFO) != null && !String.valueOf (Cd_CFO).equals ("")) {
      ed.setCD_CFO (Cd_CFO);
    }

    String CD_Estado_Destino = request.getParameter ("FT_CD_Estado_Destino");
    if (String.valueOf (CD_Estado_Destino) != null && !String.valueOf (CD_Estado_Destino).equals ("")) {
      ed.setCD_Estado_CTRC_Destino (CD_Estado_Destino);
    }

    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (String.valueOf (oid_Unidade) != null && !String.valueOf (oid_Unidade).equals ("")) {
      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
    }
    String DM_Imprime_Cancelados = request.getParameter ("FT_DM_Imprime_Cancelados");
    if (JavaUtil.doValida (DM_Imprime_Cancelados)) {
      ed.setDM_Imprime_Cancelados (DM_Imprime_Cancelados);
    }

    ed.setDM_Relatorio (request.getParameter ("FT_DM_Relatorio"));
    ed.setDM_Layout (request.getParameter ("FT_DM_Layout"));

    Livro_FiscalRN geRN = new Livro_FiscalRN ();
    // System.out.println("LIVRO BYTE IU 1");
   
    byte[] b = geRN.geraLivro_Fiscal_Conhecimento (ed);
    // System.out.println("LIVRO BYTE IU 2");

    return b;

  }
  
  public void relResumoFiscalCreditoPresumido(HttpServletRequest request, HttpServletResponse response) throws Excecoes {

      String oid_Unidade = request.getParameter("oid_Unidade");
      String DT_Inicial = request.getParameter("FT_DT_Inicial");
      String DT_Final = request.getParameter("FT_DT_Final");
      String Relatorio = request.getParameter("Relatorio");
      String DM_Tipo_Livro = request.getParameter("FT_DM_Tipo_Livro");
      String incluiDuplicata = request.getParameter("incluiDuplicata");
      long NR_Livro = Long.parseLong(request.getParameter("FT_NR_Livro"));
      long NR_Pagina = Long.parseLong(request.getParameter("FT_NR_Pagina"));
      String oid_Natureza_Operacao = request.getParameter("oid_Natureza_Operacao");

      String NM_Credito_1 = request.getParameter("FT_NM_Credito_1");
      String NM_Credito_2 = request.getParameter("FT_NM_Credito_2");
      String NM_Credito_3 = request.getParameter("FT_NM_Credito_3");
      String NM_Credito_4 = request.getParameter("FT_NM_Credito_4");
      double VL_Credito_1 = Valores.converteStringToDouble(request.getParameter("FT_VL_Credito_1")) ;
      double VL_Credito_2 = Valores.converteStringToDouble(request.getParameter("FT_VL_Credito_2")) ;
      double VL_Credito_3 = Valores.converteStringToDouble(request.getParameter("FT_VL_Credito_3")) ;
      double VL_Credito_4 = Valores.converteStringToDouble(request.getParameter("FT_VL_Credito_4")) ;
      String NM_Debito_1 = request.getParameter("FT_NM_Debito_1");
      String NM_Debito_2 = request.getParameter("FT_NM_Debito_2");
      String NM_Debito_3 = request.getParameter("FT_NM_Debito_3");
      String NM_Debito_4 = request.getParameter("FT_NM_Debito_4");
      double VL_Debito_1 = Valores.converteStringToDouble(request.getParameter("FT_VL_Debito_1")) ;
      double VL_Debito_2 = Valores.converteStringToDouble(request.getParameter("FT_VL_Debito_2")) ;
      double VL_Debito_3 = Valores.converteStringToDouble(request.getParameter("FT_VL_Debito_3")) ;
      double VL_Debito_4 = Valores.converteStringToDouble(request.getParameter("FT_VL_Debito_4")) ;
      String NM_EDebito_1 = request.getParameter("FT_NM_EDebito_1");
      String NM_EDebito_2 = request.getParameter("FT_NM_EDebito_2");
      double VL_EDebito_1 = Valores.converteStringToDouble(request.getParameter("FT_VL_EDebito_1"));
      double VL_EDebito_2 = Valores.converteStringToDouble(request.getParameter("FT_VL_EDebito_2"));
      String NM_ECredito_1 = request.getParameter("FT_NM_ECredito_1");
      String NM_ECredito_2 = request.getParameter("FT_NM_ECredito_2");
      double VL_ECredito_1 = Valores.converteStringToDouble(request.getParameter("FT_VL_ECredito_1"));
      double VL_ECredito_2 = Valores.converteStringToDouble(request.getParameter("FT_VL_ECredito_2"));
      double VL_Saldo = Valores.converteStringToDouble(request.getParameter("FT_VL_Saldo_1"));
      String NM_Deducao = request.getParameter("FT_NM_Deducao_1");
      double VL_Deducao = Valores.converteStringToDouble(request.getParameter("FT_VL_Deducao_1"));

      String oid_Modelo_Nota_Fiscal = request.getParameter("oid_Modelo_Nota_Fiscal");
      String oid_Grupo_Nota_Fiscal = request.getParameter("oid_Grupo_Nota_Fiscal");

      if (!doValida(Relatorio))
          throw new Mensagens("Nome do Relatório não informado!");
      if (!doValida(oid_Unidade))
          throw new Mensagens("Unidade não informada!");
      if (!doValida(DM_Tipo_Livro))
          throw new Mensagens("Tipo de Livro não informado!");

      RelatorioED ed = new RelatorioED(response, Relatorio);
      ed.setIncluiduplicata(new Boolean(getValueDef(incluiDuplicata, "false")).booleanValue());
      if (doValida(DT_Inicial))
          ed.setDt_entrada_inicial(DT_Inicial);
      if (doValida(DT_Final))
          ed.setDt_entrada_final(DT_Final);
      if (doValida(oid_Unidade))
          ed.setOid_unidade(oid_Unidade);
      if (doValida(DM_Tipo_Livro))
          ed.setDm_tipo(DM_Tipo_Livro);
      if (doValida(oid_Modelo_Nota_Fiscal))
          ed.setOid_modelo(Integer.parseInt(oid_Modelo_Nota_Fiscal));
      if (doValida(oid_Grupo_Nota_Fiscal))
          ed.setOid_grupo_nota_fiscal(Integer.parseInt(oid_Grupo_Nota_Fiscal));
      if (doValida(oid_Natureza_Operacao))  
          ed.setOid_natureza_operacao(oid_Natureza_Operacao);

      ed.setNm_credito_1(NM_Credito_1);
      ed.setNm_credito_2(NM_Credito_2);
      ed.setNm_credito_3(NM_Credito_3);
      ed.setNm_credito_4(NM_Credito_4);
      ed.setNm_debito_1(NM_Debito_1);
      ed.setNm_debito_2(NM_Debito_2);
      ed.setNm_debito_3(NM_Debito_3);
      ed.setNm_debito_4(NM_Debito_4);
      ed.setVl_credito_1(VL_Credito_1);
      ed.setVl_credito_2(VL_Credito_2);
      ed.setVl_credito_3(VL_Credito_3);
      ed.setVl_credito_4(VL_Credito_4);
      ed.setVl_debito_1(VL_Debito_1);
      ed.setVl_debito_2(VL_Debito_2);
      ed.setVl_debito_3(VL_Debito_3);
      ed.setVl_debito_4(VL_Debito_4);
      ed.setNm_ecredito_1(NM_ECredito_1);
      ed.setNm_ecredito_2(NM_ECredito_2);
      ed.setVl_ecredito_1(VL_ECredito_1);
      ed.setVl_ecredito_2(VL_ECredito_2);
      ed.setNm_edebito_1(NM_EDebito_1);
      ed.setNm_edebito_2(NM_EDebito_2);
      ed.setVl_edebito_1(VL_EDebito_1);
      ed.setVl_edebito_2(VL_EDebito_2);
      ed.setVl_saldo(VL_Saldo);
      ed.setNm_documento(NM_Deducao);
      ed.setVl_desconto(VL_Deducao);

      ed.setNr_pagina(NR_Pagina);
      ed.setNr_livro(NR_Livro);

      new Livro_FiscalRN().relResumoFiscal(ed);
  }


}