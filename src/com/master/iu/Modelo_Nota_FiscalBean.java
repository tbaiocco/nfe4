package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Modelo_Nota_FiscalED;
import com.master.rn.Modelo_Nota_FiscalRN;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;

/**
 * @author André Valadas
 * - Modelos de Notas Fiscais
 */
public class Modelo_Nota_FiscalBean extends JavaUtil implements Serializable {

    //*** LISTAS
    private static final int listaCompras[] = {10,11,26,32,35,99};
    private static final int listaVendas[] = {51,76,99};
    private static final int listaDevolucoes[] = {14,38,52,78,79,99};
    private static final int listaTrocas[] = {25,65,99};
    private static final int listaOutras[] = {27,60,61,67,71,99};

    private static boolean verificaModelo(int lista[], int CD_Modelo) {
        for (int i=0; i < lista.length; i++) {
            if (lista[i] == CD_Modelo)
                return true;
        }
        return false;
    }
    //*** Compras
    public static boolean isCompra(int CD_Modelo) {
        return verificaModelo(listaCompras, CD_Modelo);
    }
    //*** Vendas
    public static boolean isVenda(int CD_Modelo) {
        return verificaModelo(listaVendas, CD_Modelo);
    }
    //*** Devoluções
    public static boolean isDevolucao(int CD_Modelo) {
        return verificaModelo(listaDevolucoes, CD_Modelo);
    }
    //*** Trocas
    public static boolean isTroca(int CD_Modelo) {
        return verificaModelo(listaTrocas, CD_Modelo);
    }
    //*** Outros
    public static boolean isOutro(int CD_Modelo) {
        return verificaModelo(listaOutras, CD_Modelo);
    }
    /** -------------------------------------------------------------------- */

	public Modelo_Nota_FiscalED inclui(HttpServletRequest request) throws Excecoes {

	    String oid_Tipo_Estoque = request.getParameter("oid_Tipo_Estoque");
	    String oid_Tipo_Movimento_Produto = request.getParameter("oid_Tipo_Movimento_Produto");
	    String CD_Modelo_Nota_Fiscal = request.getParameter("FT_CD_Modelo_Nota_Fiscal");
	    String NM_Modelo_Nota_Fiscal = request.getParameter("FT_NM_Modelo_Nota_Fiscal");
	    String DM_Tipo_Nota_Fiscal = request.getParameter("FT_DM_Tipo_Nota_Fiscal");
	    String DM_Gera_Fiscal = request.getParameter("FT_DM_Gera_Fiscal");
	    String DM_Gera_Devolucao = request.getParameter("FT_DM_Gera_Devolucao");
	    String DM_Nota_Fiscal = request.getParameter("FT_DM_Nota_Fiscal");
	    String DM_Movimenta_Estoque = request.getParameter("FT_DM_Movimenta_Estoque");
	    String DM_Movimenta_Financeiro = request.getParameter("FT_DM_Movimenta_Financeiro");
	    String DM_Exige_Pedido = request.getParameter("FT_DM_Exige_Pedido");
	    String DM_Aliquota_ICMS = request.getParameter("FT_DM_Aliquota_ICMS");
	    String TX_Observacao = request.getParameter("FT_TX_Observacao");

	    String sugestao = request.getParameter("oid_Sugestao");

	    String DM_Permite_Servico = request.getParameter("FT_DM_Permite_Servico");

		//*** Validações
		if (!doValida(CD_Modelo_Nota_Fiscal))
		    throw new Mensagens("Código não informado!");
		if (!doValida(NM_Modelo_Nota_Fiscal))
		    throw new Mensagens("Descrição não informada!");
		if (!doValida(DM_Tipo_Nota_Fiscal))
		    throw new Mensagens("Modelo de Nota Fiscal não informado!");
		if (!doValida(DM_Gera_Fiscal))
		    throw new Mensagens("Gera Fiscal não informado!");
		if (!doValida(DM_Gera_Devolucao))
		    throw new Mensagens("Gera Devolução não informado!");
		if (!doValida(DM_Nota_Fiscal))
		    throw new Mensagens("Gera Fiscal não informado!");
		if (!doValida(DM_Movimenta_Financeiro))
		    throw new Mensagens("Movimenta Financeiro não informado!");
		if (!doValida(DM_Movimenta_Estoque))
		    throw new Mensagens("Movimenta Estoque não informado!");

		//*** Verifica se existe NF para esse Modelo_Nota_Fiscaldor
		//    Que ainda não esteje relacionada a Documentos_Modelo_Nota_Fiscals
		if (new BancoUtil().doExiste("Modelos_Notas_Fiscais",
		        	                 "Modelos_Notas_Fiscais.CD_Modelo_Nota_Fiscal = '"+CD_Modelo_Nota_Fiscal+"'"))
		    throw new Mensagens("Já existe um Modelo cadastrado com esse código!");

		Modelo_Nota_FiscalED ed = new Modelo_Nota_FiscalED();
		if (doValida(oid_Tipo_Estoque))
		    ed.setOid_Tipo_Estoque(Integer.parseInt(oid_Tipo_Estoque));
		if (doValida(oid_Tipo_Movimento_Produto))
		    ed.setOid_Tipo_Movimento_Produto(Integer.parseInt(oid_Tipo_Movimento_Produto));
	  	ed.setCD_Modelo_Nota_Fiscal(CD_Modelo_Nota_Fiscal);
	  	ed.setNM_Modelo_Nota_Fiscal(NM_Modelo_Nota_Fiscal);
	  	ed.setDM_Tipo_Nota_Fiscal(DM_Tipo_Nota_Fiscal);
	  	ed.setDM_Gera_Fiscal(DM_Gera_Fiscal);
	  	ed.setDM_Gera_Devolucao(DM_Gera_Devolucao);
	  	ed.setDM_Nota_Fiscal(DM_Gera_Fiscal);
	  	ed.setDM_Movimenta_Estoque(DM_Movimenta_Estoque);
	  	ed.setDM_Movimenta_Financeiro(DM_Movimenta_Financeiro);
	  	ed.setDM_Exige_Pedido(getValueDef(DM_Exige_Pedido, "N"));
	  	if ("N".equals(DM_Movimenta_Estoque))
	  	    ed.setDM_Aliquota_ICMS(getValueDef(DM_Aliquota_ICMS, "N"));
	  	else ed.setDM_Aliquota_ICMS("N");
	  	ed.setTX_Observacao(getValueDef(TX_Observacao, ""));

	  	if (doValida(sugestao))
	  		ed.setOid_Sugestao_Contabil(Long.parseLong(sugestao));
	  	else ed.setOid_Sugestao_Contabil(0);

	  	ed.setDM_Permite_Servico(getValueDef(DM_Permite_Servico, "N"));

		return new Modelo_Nota_FiscalRN().inclui(ed);
	}

	public void altera(HttpServletRequest request) throws Excecoes {

	    String oid_Modelo_Nota_Fiscal = request.getParameter("oid_Modelo_Nota_Fiscal");
	    String oid_Tipo_Estoque = request.getParameter("oid_Tipo_Estoque");
	    String oid_Tipo_Movimento_Produto = request.getParameter("oid_Tipo_Movimento_Produto");
	    String CD_Modelo_Nota_Fiscal = request.getParameter("FT_CD_Modelo_Nota_Fiscal");
	    String NM_Modelo_Nota_Fiscal = request.getParameter("FT_NM_Modelo_Nota_Fiscal");
	    String DM_Tipo_Nota_Fiscal = request.getParameter("FT_DM_Tipo_Nota_Fiscal");
	    String DM_Gera_Fiscal = request.getParameter("FT_DM_Gera_Fiscal");
	    String DM_Gera_Devolucao = request.getParameter("FT_DM_Gera_Devolucao");
	    String DM_Nota_Fiscal = request.getParameter("FT_DM_Nota_Fiscal");
	    String DM_Movimenta_Estoque = request.getParameter("FT_DM_Movimenta_Estoque");
	    String DM_Movimenta_Financeiro = request.getParameter("FT_DM_Movimenta_Financeiro");
	    String DM_Exige_Pedido = request.getParameter("FT_DM_Exige_Pedido");
	    String DM_Aliquota_ICMS = request.getParameter("FT_DM_Aliquota_ICMS");
	    String TX_Observacao = request.getParameter("FT_TX_Observacao");

	    String sugestao = request.getParameter("oid_Sugestao");

	    String DM_Permite_Servico = request.getParameter("FT_DM_Permite_Servico");

	    //*** Validações
	    if (!doValida(oid_Modelo_Nota_Fiscal))
		    throw new Mensagens("Modelo Nota Fiscal não informado!");
	    if (!doValida(CD_Modelo_Nota_Fiscal))
		    throw new Mensagens("Código não informado!");
		if (!doValida(NM_Modelo_Nota_Fiscal))
		    throw new Mensagens("Descrição não informada!");
		if (!doValida(DM_Tipo_Nota_Fiscal))
		    throw new Mensagens("Modelo de Nota Fiscal não informado!");
		if (!doValida(DM_Gera_Fiscal))
		    throw new Mensagens("Gera Fiscal não informado!");
		if (!doValida(DM_Gera_Devolucao))
		    throw new Mensagens("Gera Devolução não informado!");
		if (!doValida(DM_Nota_Fiscal))
		    throw new Mensagens("Gera Fiscal não informado!");
		if (!doValida(DM_Movimenta_Financeiro))
		    throw new Mensagens("Movimenta Financeiro não informado!");
		if (!doValida(DM_Movimenta_Estoque))
		    throw new Mensagens("Movimenta Estoque não informado!");

		//*** Valida se Pode Alterar
		new BancoUtil().doValidaUpdate(Integer.parseInt(oid_Modelo_Nota_Fiscal), CD_Modelo_Nota_Fiscal, "Modelos_Notas_Fiscais", "oid_Modelo_Nota_Fiscal", "CD_Modelo_Nota_Fiscal");

		Modelo_Nota_FiscalED ed = new Modelo_Nota_FiscalED(Integer.parseInt(oid_Modelo_Nota_Fiscal));
		if (doValida(oid_Tipo_Estoque))
		    ed.setOid_Tipo_Estoque(Integer.parseInt(oid_Tipo_Estoque));
		if (doValida(oid_Tipo_Movimento_Produto))
		    ed.setOid_Tipo_Movimento_Produto(Integer.parseInt(oid_Tipo_Movimento_Produto));
	  	ed.setCD_Modelo_Nota_Fiscal(CD_Modelo_Nota_Fiscal);
	  	ed.setNM_Modelo_Nota_Fiscal(NM_Modelo_Nota_Fiscal);
	  	ed.setDM_Tipo_Nota_Fiscal(DM_Tipo_Nota_Fiscal);
	  	ed.setDM_Gera_Fiscal(DM_Gera_Fiscal);
	  	ed.setDM_Gera_Devolucao(DM_Gera_Devolucao);
	  	ed.setDM_Nota_Fiscal(DM_Nota_Fiscal);
	  	ed.setDM_Movimenta_Estoque(DM_Movimenta_Estoque);
	  	ed.setDM_Movimenta_Financeiro(DM_Movimenta_Financeiro);
	  	ed.setDM_Exige_Pedido(getValueDef(DM_Exige_Pedido, "N"));
	  	if ("N".equals(DM_Movimenta_Estoque))
	  	    ed.setDM_Aliquota_ICMS(getValueDef(DM_Aliquota_ICMS, "N"));
	  	else ed.setDM_Aliquota_ICMS("N");
	  	ed.setTX_Observacao(getValueDef(TX_Observacao, ""));

	  	if (doValida(sugestao))
	  		ed.setOid_Sugestao_Contabil(Long.parseLong(sugestao));
	  	else ed.setOid_Sugestao_Contabil(0);

	  	ed.setDM_Permite_Servico(getValueDef(DM_Permite_Servico, "N"));

		new Modelo_Nota_FiscalRN().altera(ed);
	}

	public void deleta(HttpServletRequest request) throws Excecoes {

		String oid_Modelo_Nota_Fiscal = request.getParameter("oid_Modelo_Nota_Fiscal");
		if (!doValida(oid_Modelo_Nota_Fiscal))
		    throw new Mensagens("Modelo Nota Fiscal não informado!");
		new Modelo_Nota_FiscalRN().deleta(new Modelo_Nota_FiscalED(Integer.parseInt(oid_Modelo_Nota_Fiscal)));
	}

	public ArrayList lista(HttpServletRequest request) throws Excecoes {

	    String oid_Modelo_Nota_Fiscal = request.getParameter("oid_Modelo_Nota_Fiscal");
	    String oid_Tipo_Estoque = request.getParameter("oid_Tipo_Estoque");
	    String oid_Tipo_Movimento_Produto = request.getParameter("oid_Tipo_Movimento_Produto");
	    String CD_Modelo_Nota_Fiscal = request.getParameter("FT_CD_Modelo_Nota_Fiscal");
	    String NM_Modelo_Nota_Fiscal = request.getParameter("FT_NM_Modelo_Nota_Fiscal");
	    String DM_Tipo_Nota_Fiscal = request.getParameter("FT_DM_Tipo_Nota_Fiscal");
	    String DM_Gera_Fiscal = request.getParameter("FT_DM_Gera_Fiscal");
	    String DM_Gera_Devolucao = request.getParameter("FT_DM_Gera_Devolucao");
	    String DM_Movimenta_Estoque = request.getParameter("FT_DM_Movimenta_Estoque");
	    String DM_Movimenta_Financeiro = request.getParameter("FT_DM_Movimenta_Financeiro");
	    String DM_Exige_Pedido = request.getParameter("FT_DM_Exige_Pedido");
	    String DM_Aliquota_ICMS = request.getParameter("FT_DM_Aliquota_ICMS");

	    String DM_Permite_Servico = request.getParameter("FT_DM_Permite_Servico");

		Modelo_Nota_FiscalED ed = new Modelo_Nota_FiscalED();
		if (doValida(oid_Modelo_Nota_Fiscal))
		    ed.setOid_Modelo_Nota_Fiscal(Integer.parseInt(oid_Modelo_Nota_Fiscal));
		if (doValida(oid_Tipo_Estoque))
		    ed.setOid_Tipo_Estoque(Integer.parseInt(oid_Tipo_Estoque));
		if (doValida(oid_Tipo_Movimento_Produto))
		    ed.setOid_Tipo_Movimento_Produto(Integer.parseInt(oid_Tipo_Movimento_Produto));
		if (doValida(CD_Modelo_Nota_Fiscal))
		    ed.setCD_Modelo_Nota_Fiscal(CD_Modelo_Nota_Fiscal);
		if (doValida(CD_Modelo_Nota_Fiscal))
		    ed.setNM_Modelo_Nota_Fiscal(NM_Modelo_Nota_Fiscal);
	  	if (doValida(DM_Tipo_Nota_Fiscal))
	  	    ed.setDM_Tipo_Nota_Fiscal(DM_Tipo_Nota_Fiscal);
	  	if (doValida(DM_Gera_Fiscal))
	  	    ed.setDM_Gera_Fiscal(DM_Gera_Fiscal);
	  	if (doValida(DM_Gera_Devolucao))
	  	    ed.setDM_Gera_Devolucao(DM_Gera_Devolucao);
	  	if (doValida(DM_Movimenta_Estoque))
	  	    ed.setDM_Movimenta_Estoque(DM_Movimenta_Estoque);
	  	if (doValida(DM_Movimenta_Financeiro))
	  	    ed.setDM_Movimenta_Financeiro(DM_Movimenta_Financeiro);
	  	if (doValida(DM_Exige_Pedido))
	  	    ed.setDM_Exige_Pedido(DM_Exige_Pedido);
	  	if (doValida(DM_Aliquota_ICMS))
	  	    ed.setDM_Aliquota_ICMS(DM_Aliquota_ICMS);
	  	if (doValida(DM_Permite_Servico))
	  	    ed.setDM_Permite_Servico(DM_Permite_Servico);

		return new Modelo_Nota_FiscalRN().lista(ed);
	}

	public Modelo_Nota_FiscalED getByOid_Modelo(String oid_Modelo_Nota_Fiscal) throws Excecoes {

	    if (!doValida(oid_Modelo_Nota_Fiscal))
	        throw new Mensagens("Modelo Nota Fiscal não informado!");
		return new Modelo_Nota_FiscalRN().getByRecord(new Modelo_Nota_FiscalED(Integer.parseInt(oid_Modelo_Nota_Fiscal)));
	}
	//*** Obrigatório Passagem do Tipo da NF
	public Modelo_Nota_FiscalED getByOid_Modelo(String oid_Modelo_Nota_Fiscal, String DM_Tipo_NF) throws Excecoes {

	    if (!doValida(oid_Modelo_Nota_Fiscal))
	        throw new Mensagens("Modelo Nota Fiscal não informado!");
	    if (!doValida(DM_Tipo_NF))
	        throw new Mensagens("Tipo Nota Fiscal não informado!");

		return new Modelo_Nota_FiscalRN().getByRecord(new Modelo_Nota_FiscalED(Integer.parseInt(oid_Modelo_Nota_Fiscal), DM_Tipo_NF));
	}

	public Modelo_Nota_FiscalED getByCD_Modelo(String CD_Modelo_Nota_Fiscal) throws Excecoes {

	    if (!doValida(CD_Modelo_Nota_Fiscal))
	        throw new Mensagens("Código do Modelo Nota Fiscal não informado!");

	    Modelo_Nota_FiscalED ed = new Modelo_Nota_FiscalED();
	    ed.setCD_Modelo_Nota_Fiscal(CD_Modelo_Nota_Fiscal);
		return new Modelo_Nota_FiscalRN().getByRecord(ed);
	}
	//*** Obrigatório Passagem do Tipo da NF
	public Modelo_Nota_FiscalED getByCD_Modelo(String CD_Modelo_Nota_Fiscal, String DM_Tipo_NF) throws Excecoes {

	    if (!doValida(CD_Modelo_Nota_Fiscal))
	        throw new Mensagens("Código do Modelo Nota Fiscal não informado!");
	    if (!doValida(DM_Tipo_NF))
	        throw new Mensagens("Tipo Nota Fiscal não informado!");

	    Modelo_Nota_FiscalED ed = new Modelo_Nota_FiscalED();
	    ed.setCD_Modelo_Nota_Fiscal(CD_Modelo_Nota_Fiscal);
	    ed.setDM_Tipo_Nota_Fiscal(DM_Tipo_NF);
		return new Modelo_Nota_FiscalRN().getByRecord(ed);
	}
}