package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.Ocorrencia_PedidoED;
import com.master.ed.RelatorioED;
import com.master.rn.Ocorrencia_PedidoRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;

/**
 * @author André Valadas
 * @serialData 27/10/2004
 * @JavaBean.class Ocorrencia_PedidoBean
 */
public class Ocorrencia_PedidoBean extends JavaUtil implements Serializable {

	public Ocorrencia_PedidoED inclui(HttpServletRequest request) throws Excecoes {

	    String oid_Pedido = request.getParameter("oid_Pedido");
		String oid_Pessoa = request.getParameter("oid_Pessoa");
		String TX_Observacao = request.getParameter("FT_TX_Observacao");
		String DM_Motivo = request.getParameter("FT_DM_Motivo");

		//*** Validações
		if (!doValida(oid_Pedido) || !doValida(oid_Pessoa) || !doValida(TX_Observacao) || !doValida(DM_Motivo)) 
		    throw new Excecoes("Parâmetros incorretos!", this.getClass().getName(), "inclui()");
		    
		Ocorrencia_PedidoED ed = new Ocorrencia_PedidoED();
		ed.setOid_Pedido(Integer.parseInt(oid_Pedido));
		ed.setOid_Pessoa(oid_Pessoa);
		ed.setTX_Observacao(TX_Observacao);
		ed.setDM_Motivo(DM_Motivo);

		return new Ocorrencia_PedidoRN().inclui(ed);
	}

	public void altera(HttpServletRequest request) throws Excecoes {

		String oid_Ocorrencia_Pedido = request.getParameter("oid_Ocorrencia_Pedido");
		String DM_Motivo = request.getParameter("FT_DM_Motivo");
		
		//*** Validações
		if (!doValida(oid_Ocorrencia_Pedido) || !doValida(DM_Motivo))
		    throw new Excecoes("Parâmetros incorretos!", this.getClass().getName(), "altera()");

		Ocorrencia_PedidoED ed = new Ocorrencia_PedidoED();
		ed.setOid_Ocorrencia_Pedido(Integer.parseInt(oid_Ocorrencia_Pedido));
		ed.setDM_Motivo(DM_Motivo);
		
		new Ocorrencia_PedidoRN().altera(ed);
	}

	public void deleta(HttpServletRequest request) throws Excecoes {

		String oid_Ocorrencia_Pedido = request.getParameter("oid_Ocorrencia_Pedido");
		//*** Validações
		if (!doValida(oid_Ocorrencia_Pedido))
		    throw new Excecoes("Parâmetros incorretos!", this.getClass().getName(), "deleta()");

		new Ocorrencia_PedidoRN().deleta(new Ocorrencia_PedidoED(Integer.parseInt(oid_Ocorrencia_Pedido)));
	}

	public ArrayList listaOcorrencia_Pedido(HttpServletRequest request) throws Excecoes {

	    String oid_Pedido = request.getParameter("oid_Pedido");
		String oid_Pessoa = request.getParameter("oid_Pessoa");
		String DM_Motivo = request.getParameter("FT_DM_Motivo");

		Ocorrencia_PedidoED ed = new Ocorrencia_PedidoED();
		//*** Validações
		if (doValida(oid_Pedido))
		    ed.setOid_Pedido(Integer.parseInt(oid_Pedido));
		if (doValida(oid_Pessoa))
		    ed.setOid_Pessoa(oid_Pessoa);
		if (doValida(DM_Motivo))
			ed.setDM_Motivo(DM_Motivo);
		
		return new Ocorrencia_PedidoRN().lista(ed);
	}
	
	//*** Verifica Crítica Pedidos
    public ArrayList listaCriticaPedidos(HttpServletRequest request) throws Excecoes {

	    String oid_Pedido = request.getParameter("oid_Pedido");
		String oid_Pessoa = request.getParameter("oid_Pessoa");
		String oid_Vendedor = request.getParameter("oid_Vendedor");
		String DM_Situacao = request.getParameter("FT_DM_Situacao");
		String DM_Motivo = request.getParameter("FT_DM_Motivo");
		String DM_Critica_Financeira = request.getParameter("FT_DM_Critica_Financeira");
	    String DM_Critica_Estoque = request.getParameter("FT_DM_Critica_Estoque");

		Ocorrencia_PedidoED ed = new Ocorrencia_PedidoED();
		//*** Validações
		if (doValida(oid_Pedido))
		    ed.setOid_Pedido(Integer.parseInt(oid_Pedido));
		if (doValida(oid_Pessoa))
		    ed.setOid_Pessoa(oid_Pessoa);
		if (doValida(oid_Vendedor))
		    ed.edPedido.setOID_Vendedor(oid_Vendedor);
		if (doValida(DM_Motivo))
			ed.setDM_Motivo(DM_Motivo);
		if (doValida(DM_Critica_Financeira))
		    ed.edPedido.setDM_Critica_Financeira(DM_Critica_Financeira);
		if (doValida(DM_Critica_Estoque))
		    ed.edPedido.setDM_Critica_Estoque(DM_Critica_Estoque);
		if (doValida(DM_Situacao))
		    ed.edPedido.setDM_Situacao(DM_Situacao);
		
		return new Ocorrencia_PedidoRN().listaCriticaPedidos(ed);
	}
	
	public ArrayList listaByPedido(String oid_Pedido) throws Excecoes {

		Ocorrencia_PedidoED ed = new Ocorrencia_PedidoED();
		//*** Validações
		if (doValida(oid_Pedido))
		    ed.setOid_Pedido(Integer.parseInt(oid_Pedido));
		return new Ocorrencia_PedidoRN().lista(ed);
	}

	public Ocorrencia_PedidoED getByOid(String oid_Ocorrencia_Pedido) throws Excecoes {

		//*** Validações
		if (!doValida(oid_Ocorrencia_Pedido)){
		    throw new Excecoes("ID Ocorrencia Pedido não informado!");
		} else return new Ocorrencia_PedidoRN().getByRecord(new Ocorrencia_PedidoED(Integer.parseInt(oid_Ocorrencia_Pedido)));
	}
	
	/** ------------ RELATÓRIOS ---------------- */
	//*** Ocorrências Pedido
    public void relOcorrenciasPedido(HttpServletRequest request, HttpServletResponse response) throws Exception {
      
        String Relatorio = request.getParameter("Relatorio");
        String NR_Pedido = request.getParameter("FT_NR_Pedido");
        String NR_Pedido_Final = request.getParameter("FT_NR_Pedido_Final");
        String CD_Vendedor = request.getParameter("FT_CD_Vendedor");
        String CD_Vendedor_Final = request.getParameter("FT_CD_Vendedor_Final");
        String oid_Entregador = request.getParameter("oid_Entregador");
        String oid_Pessoa = request.getParameter("oid_Pessoa");
        String oid_Tipo_Pedido = request.getParameter("oid_Tipo_Pedido");
        String DT_Emissao_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
        String DT_Emissao_Final = request.getParameter("FT_DT_Emissao_Final");
        String DT_Entrega_Inicial = request.getParameter("FT_DT_Entrega_Inicial");
        String DT_Entrega_Final = request.getParameter("FT_DT_Entrega_Final");
        String DM_Critica_Financeira = request.getParameter("FT_DM_Critica_Financeira");
        String DM_Critica_Estoque = request.getParameter("FT_DM_Critica_Estoque");
        String DM_Motivo = request.getParameter("FT_DM_Motivo");
        
        if (!doValida(Relatorio))
            throw new Mensagens("Nome do Relatório não informado!");
        RelatorioED ed = new RelatorioED(response, Relatorio);
        
        if (doValida(NR_Pedido))
            ed.setNr_pedido(Integer.parseInt(NR_Pedido));
        if (doValida(NR_Pedido_Final))
            ed.setNr_pedido_final(Integer.parseInt(NR_Pedido_Final));
        if (doValida(CD_Vendedor))
            ed.setCd_vendedor(CD_Vendedor);
        if (doValida(CD_Vendedor_Final))
            ed.setCd_vendedor_final(CD_Vendedor_Final);
        if (doValida(oid_Entregador))
            ed.setOid_entregador(Integer.parseInt(oid_Entregador));
        if (doValida(oid_Pessoa))
            ed.setOid_pessoa(oid_Pessoa);
        if (doValida(oid_Tipo_Pedido))
            ed.setOid_tipo_pedido(Integer.parseInt(oid_Tipo_Pedido));
        if (doValida(DT_Emissao_Inicial))
            ed.setDt_emissao_inicial(DT_Emissao_Inicial);
        if (doValida(DT_Emissao_Final))
            ed.setDt_emissao_final(DT_Emissao_Final);
        if (doValida(DT_Entrega_Inicial))
            ed.setDt_entrega_inicial(DT_Entrega_Inicial);
        if (doValida(DT_Entrega_Final))
            ed.setDt_entrega_final(DT_Entrega_Final);
        if (doValida(DM_Critica_Estoque))
            ed.setDm_critica_estoque(DM_Critica_Estoque);
        if (doValida(DM_Critica_Financeira))
            ed.setDm_critica_financeira(DM_Critica_Financeira);
        if (doValida(DM_Motivo))
            ed.setDm_motivo(DM_Motivo);
      
        new Ocorrencia_PedidoRN().relOcorrenciasPedido(ed);       
    }
}