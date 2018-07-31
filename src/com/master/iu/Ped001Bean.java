package com.master.iu;

/**
 * M�dulo: Pedidos
 * @author Andr� Valadas
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.PedidoED;
import com.master.ed.ProdutoED;
import com.master.ed.RelatorioED;
import com.master.rn.PedidoRN;
import com.master.rn.ProdutoRN;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;

public class Ped001Bean extends JavaUtil {
    
    public PedidoED inclui(HttpServletRequest request)throws Excecoes{

      	String oid_Pessoa = request.getParameter("oid_Pessoa");
      	String oid_Entregador = request.getParameter("oid_Entregador");
      	String oid_Vendedor = request.getParameter("oid_Vendedor");
      	String oid_Unidade = request.getParameter("oid_Unidade");
      	String oid_Tipo_Pedido = request.getParameter("oid_Tipo_Pedido");
      	String oid_Condicao_Pagamento = request.getParameter("oid_Condicao_Pagamento");
      	String oid_Pessoa_Distribuidor = request.getParameter("oid_Pessoa_Distribuidor");
      	String oid_Natureza_Operacao = request.getParameter("oid_Natureza_Operacao");
      	String oid_Modelo_Nota_Fiscal = request.getParameter("oid_Modelo_Nota_Fiscal");
        String oid_Tabela_Venda = request.getParameter("oid_Tabela_Venda");
      	
      	String DT_Pedido = request.getParameter("FT_DT_Pedido");
      	String DT_Previsao_Entrega = request.getParameter("FT_DT_Previsao_Entrega");
      	String DM_Pedido = request.getParameter("FT_DM_Pedido");
      	String DM_Meio_Pagamento = request.getParameter("FT_DM_Meio_Pagamento");
      	String NM_Comprador = request.getParameter("FT_NM_Comprador");
      	String NM_Vendedor = request.getParameter("FT_NM_Vendedor");
      	String TX_Observacao = request.getParameter("FT_TX_Observacao");
      	String NR_Nota_Fiscal = request.getParameter("FT_NR_Nota_Fiscal");
      	String NM_Serie = request.getParameter("FT_NM_Serie");
      	
        if (!doValida(oid_Pessoa))
            throw new Excecoes("ID Pessoa n�o informado!");
        
        PedidoED ed = new PedidoED();
        ed.setOID_Pessoa(oid_Pessoa);
      	ed.setDM_Frete("F");
      	ed.setDM_Situacao("N");
      	ed.setDM_Critica_Financeira("N");
      	ed.setDM_Critica_Estoque("N");
      	ed.setHR_Pedido(Data.getHoraHM());
      	ed.setDT_Faturamento(Data.getDataDMY());
      	
      	//*** Informar o tipo de Pedido[Compra ou Venda]
        if (doValida(DM_Pedido))
        {
            ed.setDM_Pedido(DM_Pedido);
            if (DM_Pedido.equals("V"))
            {
                ed.setDM_Frete("C");
        	    if (doValida(oid_Entregador))
        	        ed.setOid_Entregador(Integer.parseInt(oid_Entregador));
        	    if (doValida(oid_Natureza_Operacao))
        	        ed.setOid_Natureza_Operacao(Integer.parseInt(oid_Natureza_Operacao));
        	    else throw new Excecoes("Natureza de Opera��o n�o informada!");
        	}
        } else throw new Excecoes("Tipo de Pedido[Compra ou Venda] n�o informado!");
      	
		if (doValida(oid_Vendedor))
		    ed.setOID_Vendedor(oid_Vendedor);
		if (doValida(oid_Condicao_Pagamento))		
		    ed.setOID_Condicao_Pagamento(Integer.parseInt(oid_Condicao_Pagamento));
		if (doValida(oid_Unidade))
		    ed.setOID_Unidade(Integer.parseInt(oid_Unidade));
		if (doValida(oid_Tipo_Pedido))
		    ed.setOID_Tipo_Pedido(Integer.parseInt(oid_Tipo_Pedido));		
		if (doValida(oid_Pessoa_Distribuidor))
		    ed.setOid_Pessoa_Distribuidor(oid_Pessoa_Distribuidor);
		
		if (doValida(DM_Meio_Pagamento))
		    ed.setDM_Meio_Pagamento(DM_Meio_Pagamento);
		else ed.setDM_Meio_Pagamento("");
      	if (doValida(DT_Pedido))
      	    ed.setDT_Pedido(DT_Pedido);
      	if (doValida(DT_Previsao_Entrega))
      	    ed.setDT_Previsao_Entrega(DT_Previsao_Entrega);				
		if (doValida(NM_Comprador))
		    ed.setNM_Comprador(NM_Comprador);		
		if (doValida(NM_Vendedor))
		    ed.setNM_Vendedor(NM_Vendedor);		
		if (doValida(TX_Observacao))
		    ed.setTX_Observacao(TX_Observacao);	
		if (doValida(NR_Nota_Fiscal))
		    ed.setNR_Nota_Fiscal(Integer.parseInt(NR_Nota_Fiscal));
		if (doValida(NM_Serie))
		    ed.setNM_Serie(NM_Serie);
		if (doValida(oid_Modelo_Nota_Fiscal))
		    ed.setOid_Modelo_Nota_Fiscal(Integer.parseInt(oid_Modelo_Nota_Fiscal));
        if (doValida(oid_Tabela_Venda))
            ed.setOid_Tabela_Venda(Integer.parseInt(oid_Tabela_Venda));
 
		return new PedidoRN().inclui(ed);
    }

    public void altera(HttpServletRequest request)throws Excecoes{

        PedidoED ed = new PedidoED();
        ed.setMasterDetails(request);
        String oid_Entregador = request.getParameter("oid_Entregador");
        String oid_Pessoa_Distribuidor = request.getParameter("oid_Pessoa_Distribuidor");
        String oid_Natureza_Operacao = request.getParameter("oid_Natureza_Operacao");
        String oid_Pedido = request.getParameter("oid_Pedido");
        String oid_Modelo_Nota_Fiscal = request.getParameter("oid_Modelo_Nota_Fiscal");
        String oid_Tabela_Venda = request.getParameter("oid_Tabela_Venda");
        String DM_Frete = request.getParameter("FT_DM_Frete");
        String DM_Pedido = request.getParameter("FT_DM_Pedido");
        String NR_Nota_Fiscal = request.getParameter("FT_NR_Nota_Fiscal");
      	String NM_Serie = request.getParameter("FT_NM_Serie");
        
        ed.setUpdatePrecosByCond("true".equals(request.getParameter("updatePrecosByCond")));
        ed.setUpdatePrecosByTabela("true".equals(request.getParameter("updatePrecosByTabela")));
        if (doValida(oid_Pedido) && doValida(oid_Pessoa_Distribuidor) && doValida(DM_Frete)) {
            ed.setOid_Pessoa_Distribuidor(oid_Pessoa_Distribuidor);
            ed.setDM_Frete(DM_Frete);
            ed.setOID_Pedido(Long.parseLong(oid_Pedido));            
        } else throw new Excecoes("Par�metros incorretos!");            

        //*** Informar o tipo de Pedido[Compra ou Venda]
        if (doValida(DM_Pedido))
        {
            ed.setDM_Pedido(DM_Pedido);
        	if (DM_Pedido.equals("V"))
            {
        	    if (doValida(oid_Entregador))
        	        ed.setOid_Entregador(Integer.parseInt(oid_Entregador));
        	    if (doValida(oid_Natureza_Operacao))
        	        ed.setOid_Natureza_Operacao(Integer.parseInt(oid_Natureza_Operacao));
        	    else throw new Excecoes("Natureza de Opera��o n�o informada!");
        	}
        } else throw new Excecoes("Tipo de Pedido[Compra ou Venda] n�o informado!");
        
        ed.setDT_Faturamento(request.getParameter("FT_DT_Faturamento"));
        if (doValida(request.getParameter("FT_DM_Meio_Pagamento")))
            ed.setDM_Meio_Pagamento(request.getParameter("FT_DM_Meio_Pagamento"));
        else ed.setDM_Meio_Pagamento("");
        
        ed.setDT_Pedido(request.getParameter("FT_DT_Pedido"));
        ed.setDT_Previsao_Entrega(request.getParameter("FT_DT_Previsao_Entrega"));
        ed.setHR_Pedido(request.getParameter("FT_HR_Pedido"));
        
        ed.setOID_Pessoa(request.getParameter("oid_Pessoa"));
        ed.setOID_Vendedor(request.getParameter("oid_Vendedor"));
        
        ed.setOID_Condicao_Pagamento(new Integer(request.getParameter("oid_Condicao_Pagamento")).intValue());
        ed.setOID_Unidade(new Integer(request.getParameter("oid_Unidade")).intValue());
        ed.setVL_Frete(new Double(request.getParameter("FT_VL_Frete")).doubleValue());
        ed.setVL_Total_Pedido(new Double(request.getParameter("FT_VL_Total_Pedido")).doubleValue());
        ed.setNM_Comprador("");
        if (doValida(request.getParameter("FT_NM_Comprador")))
            ed.setNM_Comprador(request.getParameter("FT_NM_Comprador"));
        ed.setNM_Vendedor("");
        if (doValida(request.getParameter("FT_NM_Vendedor")))
            ed.setNM_Vendedor(request.getParameter("FT_NM_Vendedor"));
        ed.setTX_Observacao("");
        if (doValida(request.getParameter("FT_TX_Observacao")))
            ed.setTX_Observacao(request.getParameter("FT_TX_Observacao"));
        ed.setOID_Tipo_Pedido(new Integer(request.getParameter("oid_Tipo_Pedido")).intValue());
        if (doValida(request.getParameter("oid_pessoa_distribuidor")))
            ed.setOid_Pessoa_Distribuidor(request.getParameter("oid_pessoa_distribuidor"));
        if (doValida(NR_Nota_Fiscal))
		    ed.setNR_Nota_Fiscal(Integer.parseInt(NR_Nota_Fiscal));
		if (doValida(NM_Serie))
		    ed.setNM_Serie(NM_Serie);
		if (doValida(oid_Modelo_Nota_Fiscal))
		    ed.setOid_Modelo_Nota_Fiscal(Integer.parseInt(oid_Modelo_Nota_Fiscal));
        if (doValida(oid_Tabela_Venda))
            ed.setOid_Tabela_Venda(Integer.parseInt(oid_Tabela_Venda));
        
        new PedidoRN().altera(ed);    
    }

    public void deleta(HttpServletRequest request)throws Excecoes{

        PedidoED ed = new PedidoED();

        String oid_Pedido = request.getParameter("oid_Pedido");
        String DM_Situacao = "D";
        
        if (doValida(oid_Pedido)){
            ed.setOID_Pedido(Long.parseLong(oid_Pedido));
            ed.setDM_Situacao(DM_Situacao);
        } else throw new Excecoes("Par�metros incorretos!");
        
        new PedidoRN().deleta(ed);
    }
    
    public void cancela(HttpServletRequest request)throws Excecoes{

    	String oid_Pedido = request.getParameter("oid_Pedido");
    	if (!doValida(oid_Pedido))
    	    throw new Excecoes("Par�metros incorretos!");
    	
    	PedidoED ed = new PedidoED(Long.parseLong(oid_Pedido));
    	ed.setDM_Situacao("C");
    	new PedidoRN().deleta(ed);
    }

    //*** VERIFICA CRITICAS FINANCEIRAS
    public int verificaCriticaFinanceira(HttpServletRequest request)throws Exception {

        String oid_Pedido = request.getParameter("oid_Pedido");
        if (!doValida(oid_Pedido))
            throw new Excecoes("Pedido n�o informado!");

        return new PedidoRN().verificaCriticaFinanceira(new PedidoRN().getByRecord(new PedidoED(Long.parseLong(oid_Pedido), "V")));
    }
    
    //*** APROVA��O DO PEDIDO DE VENDA
    public int setAprovacaoPedidoVenda(HttpServletRequest request)throws Exception {

        String oid_Pedido = request.getParameter("oid_Pedido");
        if (!doValida(oid_Pedido))
            throw new Excecoes("Pedido n�o informado!");

        return new PedidoRN().setAprovacaoPedidoVenda(new PedidoRN().getByRecord(new PedidoED(Long.parseLong(oid_Pedido), "V")));
    }

    //*** CANCELA PEDIDO
    public void cancelaPedido(HttpServletRequest request)throws Exception{

        String oid_Pedido = request.getParameter("oid_Pedido");
        if (!doValida(oid_Pedido))
            throw new Excecoes("Pedido n�o informado!");

        new PedidoRN().atualizaSituacaoPedido(Long.parseLong(oid_Pedido), "C");
    }
    
    //*** LIBERA CR�TICA FINANCEIRA e ESTOQUE
    public void liberaCriticas(HttpServletRequest request) throws Exception {

        String oid_Pedido = request.getParameter("oid_Pedido");
        String DM_Critica_Financeira = request.getParameter("FT_DM_Critica_Financeira");
        String DM_Critica_Estoque = request.getParameter("FT_DM_Critica_Estoque");
        
        if (!doValida(oid_Pedido))
            throw new Mensagens("Pedido n�o informado!");
        if (!doValida(DM_Critica_Estoque) && !doValida(DM_Critica_Financeira))
            throw new Mensagens("Critica n�o informada para libera��o!");
        if (!"S".equals(DM_Critica_Financeira) && !"S".equals(DM_Critica_Estoque))
            throw new Mensagens("N�o h� Criticas nesse Pedido para libera��o!");
        
        if ("S".equals(DM_Critica_Financeira))
            new PedidoRN().atualizaCriticaFinanceira(Long.parseLong(oid_Pedido), "L");
        if ("S".equals(DM_Critica_Estoque))
            new PedidoRN().atualizaCriticaEstoque(Long.parseLong(oid_Pedido), "L");
    }
    //*** Libera Pedidos Bloqueados
    public void liberaPedido(HttpServletRequest request) throws Exception {

        String NR_Pedido = request.getParameter("FT_NR_Pedido");
        String oid_Unidade = request.getParameter("oid_Unidade");
        String oid_Vendedor = request.getParameter("oid_Vendedor");
        String DT_Entrega = request.getParameter("FT_DT_Entrega");
        String DM_Critica = request.getParameter("FT_DM_Critica");
        String DM_Pedido = request.getParameter("FT_DM_Pedido");
        
        if (!doValida(NR_Pedido))
            throw new Mensagens("Pedido n�o informado!");
        if (!doValida(oid_Unidade))
            throw new Mensagens("Unidade n�o informada para libera��o!");
        if (!doValida(oid_Vendedor))
            throw new Mensagens("Vendedor n�o informada para libera��o!");
        if (!doValida(DT_Entrega))
            throw new Mensagens("Data de Entrega n�o informada para libera��o!");
        if (!doValida(DM_Critica))
            throw new Mensagens("Tipo de Cr�tica n�o informada para libera��o!");
        if (!doValida(DM_Pedido))
            throw new Mensagens("Tipo de Pedido n�o informado para libera��o!");
        
        PedidoED ed = new PedidoED();
        ed.setNR_Pedido(Long.parseLong(NR_Pedido));
        ed.setOID_Unidade(Long.parseLong(oid_Unidade));
        ed.setOID_Vendedor(oid_Vendedor);
        ed.setDT_Entrega(DT_Entrega);
        ed.setDM_Filtrar_ACF(DM_Critica);
        ed.setDM_Pedido(DM_Pedido);
        new PedidoRN().liberaPedido(ed);
    }
    
    //*** EXISTE ITENS PEDIDO
    public boolean doExisteItensPedido(String oid_Pedido) throws Excecoes {

        if (!doValida(oid_Pedido))
            throw new Excecoes("Pedido n�o informado!");
        return new Ped002Bean().doExisteItensPedido(oid_Pedido);
    }

    public PedidoED getByRecord(HttpServletRequest request)throws Excecoes{

        String oid_Pedido = request.getParameter("oid_Pedido");
        String NR_Pedido = request.getParameter("FT_NR_Pedido");
        String DM_Pedido = request.getParameter("FT_DM_Pedido");
        if (!doValida(DM_Pedido))
            throw new Excecoes("Tipo de Pedido[Compra ou Venda] n�o informado!");            
        
        PedidoED ed = new PedidoED();
        if (doValida(oid_Pedido))
            ed.setOID_Pedido(Long.parseLong(oid_Pedido));
        if (doValida(DM_Pedido))
            ed.setDM_Pedido(DM_Pedido);
        if (doValida(NR_Pedido))
            ed.setNR_Pedido(Long.parseLong(NR_Pedido));
        return new PedidoRN().getByRecord(ed);
    }

    public PedidoED getByOID_Pedido(String oid_Pedido, String DM_Pedido) throws Excecoes{

        if (!doValida(oid_Pedido))
            throw new Excecoes("ID Pedido n�o informado!");
        if (!doValida(DM_Pedido))
            throw new Excecoes("Tipo de Pedido[Compra ou Venda] n�o informado!");
        return new PedidoRN().getByRecord(new PedidoED(Long.parseLong(oid_Pedido), DM_Pedido));
    }

    public PedidoED getByNR_Pedido(HttpServletRequest request)throws Excecoes{

        PedidoED ed = new PedidoED();      
        String NR_Pedido = request.getParameter("FT_NR_Pedido");
        String DM_Pedido = request.getParameter("FT_DM_Pedido");

        if (!doValida(NR_Pedido))
            return ed;
        if (!doValida(DM_Pedido))
            throw new Excecoes("Tipo de Pedido[Compra ou Venda] n�o informado!");
        
        ed.setNR_Pedido(Integer.parseInt(NR_Pedido));
        ed.setDM_Pedido(DM_Pedido);
        return new PedidoRN().getByRecord(ed);
    }
    public PedidoED getByNR_Pedido_Palm(HttpServletRequest request)throws Excecoes{

        PedidoED ed = new PedidoED();      
        String NR_Pedido_Palm = request.getParameter("FT_NR_Pedido_Palm");
        String DM_Pedido = request.getParameter("FT_DM_Pedido");

        if (!doValida(NR_Pedido_Palm))
            return ed;
        if (!doValida(DM_Pedido))
            throw new Excecoes("Tipo de Pedido[Compra ou Venda] n�o informado!");
        
        ed.setNR_Pedido_Palm(NR_Pedido_Palm);
        ed.setDM_Pedido(DM_Pedido);
        return new PedidoRN().getByRecord(ed);
    }

    public ArrayList Pedido_Lista(HttpServletRequest request)throws Excecoes{

        PedidoED ed = new PedidoED();

        String oid_Pedido = request.getParameter("oid_Pedido");
        String oid_Entregador = request.getParameter("oid_Entregador");
        String oid_Pessoa = request.getParameter("oid_Pessoa");
        String oid_Vendedor = request.getParameter("oid_Vendedor");
        String oid_Produto = request.getParameter("oid_Produto");
        String oid_Tipo_Pedido = request.getParameter("oid_Tipo_Pedido");
        String DT_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
        String DT_Final = request.getParameter("FT_DT_Emissao_Final");
        String DT_Previsao_Inicial = request.getParameter("FT_DT_Previsao_Inicial");
        String DT_Previsao_Final = request.getParameter("FT_DT_Previsao_Final");
        String DM_Dituacao = request.getParameter("FT_DM_Situacao");
        String DM_Pedido = request.getParameter("FT_DM_Pedido");
        String DM_Critica_Financeira = request.getParameter("FT_DM_Critica_Financeira");
        String DM_Critica_Estoque = request.getParameter("FT_DM_Critica_Estoque");

        if (doValida(oid_Pedido))
            ed.setOID_Pedido(Long.parseLong(oid_Pedido));
        if (doValida(oid_Pessoa))
            ed.setOID_Pessoa(oid_Pessoa);
        if (doValida(oid_Produto))
            ed.setOID_Produto(oid_Produto);
        if (doValida(oid_Vendedor))
            ed.setOID_Vendedor(oid_Vendedor);
        if (doValida(oid_Tipo_Pedido))
            ed.setOID_Tipo_Pedido(Long.parseLong(oid_Tipo_Pedido));

        if (doValida(DT_Inicial))
            ed.setDT_Pedido_Inicial(DT_Inicial);
        if (doValida(DT_Final))
            ed.setDT_Pedido_Final(DT_Final);
        
        if (doValida(DT_Previsao_Inicial))
            ed.setDT_Previsao_Inicial(DT_Previsao_Inicial);
        if (doValida(DT_Previsao_Final))
            ed.setDT_Previsao_Final(DT_Previsao_Final);
        if (doValida(DM_Dituacao) && !DM_Dituacao.equals("T"))
          ed.setDM_Situacao(DM_Dituacao);
        if (doValida(DM_Critica_Financeira) && !DM_Critica_Financeira.equals("T"))
            ed.setDM_Critica_Financeira(DM_Critica_Financeira);
        if (doValida(DM_Critica_Estoque) && !DM_Critica_Estoque.equals("T"))
            ed.setDM_Critica_Estoque(DM_Critica_Estoque);

        //*** Informar o tipo de Pedido[Compra ou Venda]
        if (doValida(DM_Pedido))
        {
            ed.setDM_Pedido(DM_Pedido);
            if ("V".equals(DM_Pedido))
        	    if (doValida(oid_Entregador))
        	        ed.setOid_Entregador(Integer.parseInt(oid_Entregador));
        } else throw new Mensagens("Tipo de Pedido[Compra ou Venda] n�o informado!");  
        return new PedidoRN().lista(ed);
    }
    
    //*** Listagem para Buscar Pedidos com ou Sem Entregadores 
    public ArrayList listaEntregadorPedido(HttpServletRequest request)throws Excecoes{

        String NR_Pedido = request.getParameter("FT_NR_Pedido");
        String NR_Pedido_Final = request.getParameter("FT_NR_Pedido_Final");
        String Numeros_Pedido = request.getParameter("FT_Numeros_Pedido");
        String oid_Pessoa = request.getParameter("oid_Pessoa"); // Cliente
        String oid_Vendedor = request.getParameter("oid_Vendedor");
        String oid_Pessoa_Distribuidor = request.getParameter("oid_Pessoa_Distribuidor");
        String oid_Tipo_Pedido = request.getParameter("oid_Tipo_Pedido");
        String DT_Entrega_Inicial = request.getParameter("FT_DT_Entrega_Inicial");
        String DT_Entrega_Final = request.getParameter("FT_DT_Entrega_Final");
        String DM_Critica_Financeira = request.getParameter("FT_DM_Critica_Financeira");
        String DM_Critica_Estoque = request.getParameter("FT_DM_Critica_Estoque");
        String DM_Sem_Entregador = request.getParameter("FT_DM_Sem_Entregador");
        
        PedidoED ed = new PedidoED();
        if (doValida(NR_Pedido))
            ed.setNR_Pedido(Long.parseLong(NR_Pedido));
        if (doValida(NR_Pedido_Final)) 
            ed.setNR_Pedido_Final(Long.parseLong(NR_Pedido_Final));
        if (doValida(Numeros_Pedido))
            ed.setNumeros_Pedido(Numeros_Pedido);
        if (doValida(oid_Pessoa))
            ed.setOID_Pessoa(oid_Pessoa);
        if (doValida(oid_Vendedor))
            ed.setOID_Vendedor(oid_Vendedor);
        if (doValida(oid_Pessoa_Distribuidor))
            ed.setOid_Pessoa_Distribuidor(oid_Pessoa_Distribuidor);
        if (doValida(oid_Tipo_Pedido))
            ed.setOID_Tipo_Pedido(Long.parseLong(oid_Tipo_Pedido));
        if (doValida(DM_Critica_Financeira))
            ed.setDM_Critica_Financeira(DM_Critica_Financeira);
        if (doValida(DM_Critica_Estoque))
            ed.setDM_Critica_Estoque(DM_Critica_Estoque);
        if (doValida(DM_Sem_Entregador))
            ed.setDM_Sem_Entregador(DM_Sem_Entregador);
        if (doValida(DT_Entrega_Inicial))
            ed.setDT_Previsao_Inicial(DT_Entrega_Inicial);
        if (doValida(DT_Entrega_Final))
            ed.setDT_Previsao_Final(DT_Entrega_Final);
        ed.setDM_Filtrar_ACF("N"); //*** N�o Filtrar Aprovados, Cancelados ou Finalizados
        ed.setDM_Pedido("V");
        return new PedidoRN().lista(ed);
    }
    
    //*** Seta Entregador em uma determinada lista de Pedidos 
    public void atualizaEntregadorPedido(ArrayList lista, String oid_Entregador, String oid_Veiculo)throws Excecoes {

        if (lista.size() < 1)
            throw new Mensagens("Lista de Pedidos vazia! Execute novamente a consulta!");
        if (!doValida(oid_Entregador))
            throw new Excecoes("Entregador n�o informado!");
        
        new PedidoRN().atualizaEntregadorPedido(lista, Integer.parseInt(oid_Entregador), oid_Veiculo);
    }
    
    //*** Seta Entregador em uma determinada lista de Pedidos aprovando os mesmos caso n�o possuam ocorr�ncias  
    public void aprovacaoByEntregadorPedido(ArrayList lista, String oid_Entregador, String oid_Veiculo, String update)throws Exception {
        new PedidoRN().aprovacaoByEntregadorPedido(lista, oid_Entregador, oid_Veiculo, update);
    }
    
    //*** Verifica Criticas Lista 
    public void verificaCriticaFinanceira(ArrayList lista, String oid_Entregador, String oid_Veiculo, String update) throws Exception {

        if ("true".equals(update))
            this.atualizaEntregadorPedido(lista, oid_Entregador, oid_Veiculo);
        
        int nrOcorrencias = 0,
            nrOld = 0,
            nrCriticados = 0;
        //*** Aprova Pedidos de Venda
        for (int i = 0; i < lista.size(); i++)
        {
            PedidoED ed = (PedidoED) lista.get(i);
            //*** Se ja existem criticas n�o verifica
            if (!"S".equals(ed.getDM_Critica_Financeira()))
                nrOcorrencias += new PedidoRN().verificaCriticaFinanceira(ed);
            
            if (nrOcorrencias > nrOld) 
                ++nrCriticados;
            nrOld = nrOcorrencias;
        }
        //*** Lan�a mensagem na tela para usu�rio 
        if (nrOcorrencias > 0)
            throw new Mensagens("ATEN��O: ["+nrCriticados+"] de ["+lista.size()+"] Pedido(s) possuem Criticas Financeiras! TOTAL: ["+nrOcorrencias+"] Ocorr�ncias! Consulte as Ocorr�ncias para Libera��o dos Pedidos.");
    }

    /** ------------ RELAT�RIOS ---------------- */
    // Pedido de Compra em branco(PADR�O)
    public void Rel_Padrao(HttpServletRequest request, HttpServletResponse response) throws Exception {
      
        ArrayList lista = new ArrayList();

        String oid_Pessoa = request.getParameter("oid_Pessoa");
        String oid_Pessoa_Distribuidor = request.getParameter("oid_Pessoa_Distribuidor");

        ProdutoED edProduto = new ProdutoED();
        if (doValida(oid_Pessoa))
            edProduto.setOid_Pessoa(oid_Pessoa);
        if (doValida(oid_Pessoa_Distribuidor))
            edProduto.setOid_Pessoa_Distribuidor(oid_Pessoa_Distribuidor);

        ArrayList lista_Produtos = new ProdutoRN().lista(edProduto);      
        for (int i = 0;i<lista_Produtos.size();i++){

            ProdutoED edPro = (ProdutoED) lista_Produtos.get(i);

            RelatorioED ed = new RelatorioED();
            ed.setNr_cnpj_cpf(edPro.getNR_CNPJ_CPF());
            ed.setNm_razao_social(edPro.getNM_Razao_Social());
            ed.setCd_produto(edPro.getCD_Produto());
            ed.setCd_fornecedor(edPro.getCD_Fornecedor());
            ed.setNm_produto(edPro.getNM_Produto());             

            lista.add(ed);
        }
        //*** Ordena Lista
        Collections.sort(lista, new Comparator() {
            public int compare(Object o1, Object o2) {
                return (((RelatorioED)o1).getNm_razao_social().compareTo(((RelatorioED)o2).getNm_razao_social()));
            }
        });
        RelatorioED edRel = new RelatorioED(response, "Pedido_Compra_5");
        edRel.setLista(lista);
    }
  
    //*** Relat�rio geral para COMPRA
    public void relListagemGeral(HttpServletRequest request, HttpServletResponse response) throws Exception {
      
        String Relatorio = request.getParameter("Relatorio");
        String oid_Pessoa_Distribuidor = request.getParameter("oid_Pessoa_Distribuidor");
        String DT_Emissao_Inicial = request.getParameter("FT_DT_Inicial");
        String DT_Emissao_Final = request.getParameter("FT_DT_Final");
        
        if (!doValida(Relatorio))
            throw new Mensagens("Nome do Relat�rio n�o informado!");
        if (!doValida(oid_Pessoa_Distribuidor))
            throw new Mensagens("Distribuidor n�o informado!");
        if (!doValida(DT_Emissao_Inicial))
            throw new Mensagens("Data de Emiss�o Inicial n�o informada!");
        if (!doValida(DT_Emissao_Final))
            throw new Mensagens("Data de Emiss�o Final n�o informada!");
        
        RelatorioED ed = new RelatorioED(response, Relatorio);
        ed.setOid_pessoa_distribuidor(oid_Pessoa_Distribuidor);
        ed.setDt_emissao_inicial(DT_Emissao_Inicial);
        ed.setDt_emissao_final(DT_Emissao_Final);
      
        new PedidoRN().relListagemGeral(ed);       
    }
    
    //*** Necessidade de Compra(Produtos para comprar) 
    public void relNecessidadeCompra(HttpServletRequest request, HttpServletResponse response) throws Exception {
      
        RelatorioED ed = new RelatorioED();
        String oid_Pessoa = request.getParameter("oid_Pessoa");
        String oid_Pessoa_Distribuidor = request.getParameter("oid_Pessoa_Distribuidor");
        String oid_Entregador = request.getParameter("oid_Entregador");
        String NR_Pedido = request.getParameter("FT_NR_Pedido");
        String NR_Pedido_Final = request.getParameter("FT_NR_Pedido_Final");
        String CD_Vendedor = request.getParameter("FT_CD_Vendedor");
        String CD_Vendedor_Final = request.getParameter("FT_CD_Vendedor_Final");
        String DT_Emissao_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
        String DT_Emissao_Final = request.getParameter("FT_DT_Emissao_Final");
        String DT_Entrega_Inicial = request.getParameter("FT_DT_Entrega_Inicial");
        String DT_Entrega_Final = request.getParameter("FT_DT_Entrega_Final");
      
        if (doValida(oid_Pessoa))
            ed.setOid_pessoa(oid_Pessoa);
        if (doValida(oid_Pessoa_Distribuidor))
            ed.setOid_pessoa_distribuidor(oid_Pessoa_Distribuidor);
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
        if (doValida(DT_Emissao_Inicial))
            ed.setDt_emissao_inicial(DT_Emissao_Inicial);
        if (doValida(DT_Emissao_Final))
            ed.setDt_emissao_final(DT_Emissao_Final);
        if (doValida(DT_Entrega_Inicial))
            ed.setDt_entrega_inicial(DT_Entrega_Inicial);
        if (doValida(DT_Entrega_Final))
            ed.setDt_entrega_final(DT_Entrega_Final);
      
        new PedidoRN().relNecessidadeCompra(response, ed);       
    }
    //*** Produtos por Entregador(Pedido de Venda)
    public void relProdutosEntregador(HttpServletRequest request, HttpServletResponse response) throws Exception {
      
        String Relatorio = request.getParameter("Relatorio");
        String oid_Entregador = request.getParameter("oid_Entregador");
        String NR_Pedido = request.getParameter("FT_NR_Pedido");
        String NR_Pedido_Final = request.getParameter("FT_NR_Pedido_Final");
        String CD_Vendedor = request.getParameter("FT_CD_Vendedor");
        String CD_Vendedor_Final = request.getParameter("FT_CD_Vendedor_Final");
        String oid_Pessoa = request.getParameter("oid_Pessoa");
        String oid_Tipo_Pedido = request.getParameter("oid_Tipo_Pedido");
        String DT_Emissao_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
        String DT_Emissao_Final = request.getParameter("FT_DT_Emissao_Final");
        String DT_Entrega_Inicial = request.getParameter("FT_DT_Entrega_Inicial");
        String DT_Entrega_Final = request.getParameter("FT_DT_Entrega_Final");
        String DM_Situacao = request.getParameter("FT_DM_Situacao");
        String DM_Critica_Financeira = request.getParameter("FT_DM_Critica_Financeira");
        String DM_Critica_Estoque = request.getParameter("FT_DM_Critica_Estoque");
        
        if (!doValida(Relatorio))
            throw new Mensagens("Nome do Relat�rio n�o informado!");
        if (!doValida(oid_Entregador))
            throw new Mensagens("Entregador n�o informado!");

        RelatorioED ed = new RelatorioED(response, Relatorio);
        ed.setOid_entregador(Integer.parseInt(oid_Entregador));
        if (doValida(NR_Pedido))
            ed.setNr_pedido(Integer.parseInt(NR_Pedido));
        if (doValida(NR_Pedido_Final))
            ed.setNr_pedido_final(Integer.parseInt(NR_Pedido_Final));
        if (doValida(CD_Vendedor))
            ed.setCd_vendedor(CD_Vendedor);
        if (doValida(CD_Vendedor_Final))
            ed.setCd_vendedor_final(CD_Vendedor_Final);
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
        if (doValida(DM_Situacao))
            ed.setDm_situacao(DM_Situacao);
        if (doValida(DM_Critica_Estoque))
            ed.setDm_critica_estoque(DM_Critica_Estoque);
        if (doValida(DM_Critica_Financeira))
            ed.setDm_critica_financeira(DM_Critica_Financeira);
      
        new PedidoRN().relProdutosEntregador(ed);
    }
    //*** Produtos p/ Pesar(Pedido de Venda)
    public void relProdutosPesar(HttpServletRequest request, HttpServletResponse response) throws Exception {
      
        String Relatorio = request.getParameter("Relatorio");
        String DM_Tipo_Quebra = request.getParameter("DM_Tipo_Quebra");
        String oid_Entregador = request.getParameter("oid_Entregador");
        String NR_Pedido = request.getParameter("FT_NR_Pedido");
        String NR_Pedido_Final = request.getParameter("FT_NR_Pedido_Final");
        String CD_Vendedor = request.getParameter("FT_CD_Vendedor");
        String CD_Vendedor_Final = request.getParameter("FT_CD_Vendedor_Final");
        String oid_Pessoa = request.getParameter("oid_Pessoa");
        String oid_Tipo_Pedido = request.getParameter("oid_Tipo_Pedido");
        String DT_Emissao_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
        String DT_Emissao_Final = request.getParameter("FT_DT_Emissao_Final");
        String DT_Entrega_Inicial = request.getParameter("FT_DT_Entrega_Inicial");
        String DT_Entrega_Final = request.getParameter("FT_DT_Entrega_Final");
        String DM_Situacao = request.getParameter("FT_DM_Situacao");
        String DM_Critica_Financeira = request.getParameter("FT_DM_Critica_Financeira");
        String DM_Critica_Estoque = request.getParameter("FT_DM_Critica_Estoque");
        
        if (!doValida(Relatorio))
            throw new Mensagens("Nome do Relat�rio n�o informado!");
        if (!doValida(DM_Tipo_Quebra))
            throw new Mensagens("Tipo de Quebra n�o informado!");
        //*** Agrupa o nome do Relat�rio com o Tipo de Quebra
        RelatorioED ed = new RelatorioED(response, Relatorio+DM_Tipo_Quebra);
        if (doValida(oid_Entregador))
            ed.setOid_entregador(Integer.parseInt(oid_Entregador));
        if (doValida(NR_Pedido))
            ed.setNr_pedido(Integer.parseInt(NR_Pedido));
        if (doValida(NR_Pedido_Final))
            ed.setNr_pedido_final(Integer.parseInt(NR_Pedido_Final));
        if (doValida(CD_Vendedor))
            ed.setCd_vendedor(CD_Vendedor);
        if (doValida(CD_Vendedor_Final))
            ed.setCd_vendedor_final(CD_Vendedor_Final);
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
        if (doValida(DM_Situacao))
            ed.setDm_situacao(DM_Situacao);
        if (doValida(DM_Critica_Estoque))
            ed.setDm_critica_estoque(DM_Critica_Estoque);
        if (doValida(DM_Critica_Financeira))
            ed.setDm_critica_financeira(DM_Critica_Financeira);
      
        new PedidoRN().relProdutosPesar(ed);       
    }
    //*** Produtos n�o Vendidos/Sem Estoque(Pedido de Venda)
    public void relFaltaEstoque(HttpServletRequest request, HttpServletResponse response) throws Exception {
      
        String Relatorio = request.getParameter("Relatorio");
        String oid_Entregador = request.getParameter("oid_Entregador");
        String NR_Pedido = request.getParameter("FT_NR_Pedido");
        String NR_Pedido_Final = request.getParameter("FT_NR_Pedido_Final");
        String CD_Vendedor = request.getParameter("FT_CD_Vendedor");
        String CD_Vendedor_Final = request.getParameter("FT_CD_Vendedor_Final");
        String oid_Pessoa = request.getParameter("oid_Pessoa");
        String oid_Tipo_Pedido = request.getParameter("oid_Tipo_Pedido");
        String DT_Emissao_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
        String DT_Emissao_Final = request.getParameter("FT_DT_Emissao_Final");
        String DT_Entrega_Inicial = request.getParameter("FT_DT_Entrega_Inicial");
        String DT_Entrega_Final = request.getParameter("FT_DT_Entrega_Final");
        String DM_Situacao = request.getParameter("FT_DM_Situacao");
        String DM_Critica_Financeira = request.getParameter("FT_DM_Critica_Financeira");
        String DM_Critica_Estoque = request.getParameter("FT_DM_Critica_Estoque");
        
        if (!doValida(Relatorio))
            throw new Mensagens("Nome do Relat�rio n�o informado!");

        RelatorioED ed = new RelatorioED(response, Relatorio);
        if (doValida(oid_Entregador))
            ed.setOid_entregador(Integer.parseInt(oid_Entregador));
        if (doValida(NR_Pedido))
            ed.setNr_pedido(Integer.parseInt(NR_Pedido));
        if (doValida(NR_Pedido_Final))
            ed.setNr_pedido_final(Integer.parseInt(NR_Pedido_Final));
        if (doValida(CD_Vendedor))
            ed.setCd_vendedor(CD_Vendedor);
        if (doValida(CD_Vendedor_Final))
            ed.setCd_vendedor_final(CD_Vendedor_Final);
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
        if (doValida(DM_Situacao))
            ed.setDm_situacao(DM_Situacao);
        if (doValida(DM_Critica_Estoque))
            ed.setDm_critica_estoque(DM_Critica_Estoque);
        if (doValida(DM_Critica_Financeira))
            ed.setDm_critica_financeira(DM_Critica_Financeira);

        new PedidoRN().relFaltaEstoque(ed);       
    }
    //*** Pedido de Compra e Venda
    public void relPedidoCompraVenda(HttpServletRequest request, HttpServletResponse response) throws Exception {
      
        String Relatorio = request.getParameter("Relatorio");
        String oid_Pedido = request.getParameter("oid_Pedido");
        String DM_Pedido = request.getParameter("FT_DM_Pedido");
        String DM_Situacao = request.getParameter("FT_DM_Situacao");
        String DM_Critica_Financeira = request.getParameter("FT_DM_Critica_Financeira");
        String DM_Critica_Estoque = request.getParameter("FT_DM_Critica_Estoque");
        String NR_Pedido = request.getParameter("FT_NR_Pedido");
        String NR_Pedido_Final = request.getParameter("FT_NR_Pedido_Final");
        String CD_Vendedor = request.getParameter("FT_CD_Vendedor");
        String CD_Vendedor_Final = request.getParameter("FT_CD_Vendedor_Final");
        String oid_Entregador = request.getParameter("oid_Entregador");
        String oid_Pessoa = request.getParameter("oid_Pessoa");
        String oid_Tipo_Pedido = request.getParameter("oid_Tipo_Pedido");
        String NM_Comprador = request.getParameter("FT_NM_Comprador");
        String DT_Emissao_Inicial = getValueDef(request.getParameter("FT_DT_Emissao_Inicial"), request.getParameter("FT_DT_Inicial"));
        String DT_Emissao_Final = getValueDef(request.getParameter("FT_DT_Emissao_Final"), request.getParameter("FT_DT_Final"));
        String DT_Entrega_Inicial = request.getParameter("FT_DT_Entrega_Inicial");
        String DT_Entrega_Final = request.getParameter("FT_DT_Entrega_Final");
        
        if (!doValida(Relatorio))
            throw new Mensagens("Nome do Relat�rio n�o informado!");
        if (!doValida(DM_Pedido))
            throw new Mensagens("Tipo de Pedido n�o informado!");
        
        RelatorioED ed = new RelatorioED(response, Relatorio);
        if (doValida(oid_Pedido))
            ed.setOid_pedido(Integer.parseInt(oid_Pedido));
        if (doValida(DM_Pedido))
            ed.setDm_pedido(DM_Pedido);
        if (doValida(DM_Situacao))
            ed.setDm_situacao(DM_Situacao);
        if (doValida(DM_Critica_Estoque))
            ed.setDm_critica_estoque(DM_Critica_Estoque);
        if (doValida(DM_Critica_Financeira))
            ed.setDm_critica_financeira(DM_Critica_Financeira);
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
        if (doValida(NM_Comprador))
            ed.setNm_comprador(NM_Comprador);
        if (doValida(DT_Emissao_Inicial))
            ed.setDt_emissao_inicial(DT_Emissao_Inicial);
        if (doValida(DT_Emissao_Final))
            ed.setDt_emissao_final(DT_Emissao_Final);
        if (doValida(DT_Entrega_Inicial))
            ed.setDt_entrega_inicial(DT_Entrega_Inicial);
        if (doValida(DT_Entrega_Final))
            ed.setDt_entrega_final(DT_Entrega_Final);
      
        new PedidoRN().relPedidoCompraVenda(ed);       
    }
    /** Pedido de Compra e Venda MATRICIAL **/
    public String printPedidos(HttpServletRequest request) throws Exception {
      
        String oid_Pedido = request.getParameter("oid_Pedido");
        String DM_Pedido = request.getParameter("FT_DM_Pedido");
        String DM_Situacao = request.getParameter("FT_DM_Situacao");
        String DM_Critica_Financeira = request.getParameter("FT_DM_Critica_Financeira");
        String DM_Critica_Estoque = request.getParameter("FT_DM_Critica_Estoque");
        String NR_Pedido = request.getParameter("FT_NR_Pedido");
        String NR_Pedido_Final = request.getParameter("FT_NR_Pedido_Final");
        String CD_Vendedor = request.getParameter("FT_CD_Vendedor");
        String CD_Vendedor_Final = request.getParameter("FT_CD_Vendedor_Final");
        String oid_Entregador = request.getParameter("oid_Entregador");
        String oid_Pessoa = request.getParameter("oid_Pessoa");
        String oid_Tipo_Pedido = request.getParameter("oid_Tipo_Pedido");
        String NM_Comprador = request.getParameter("FT_NM_Comprador");
        String DT_Emissao_Inicial = getValueDef(request.getParameter("FT_DT_Emissao_Inicial"), request.getParameter("FT_DT_Inicial"));
        String DT_Emissao_Final = getValueDef(request.getParameter("FT_DT_Emissao_Final"), request.getParameter("FT_DT_Final"));
        String DT_Entrega_Inicial = request.getParameter("FT_DT_Entrega_Inicial");
        String DT_Entrega_Final = request.getParameter("FT_DT_Entrega_Final");
        
        if (!doValida(DM_Pedido))
            throw new Mensagens("Tipo de Pedido n�o informado!");
        
        RelatorioED ed = new RelatorioED();
        if (doValida(oid_Pedido))
            ed.setOid_pedido(Integer.parseInt(oid_Pedido));
        if (doValida(DM_Pedido))
            ed.setDm_pedido(DM_Pedido);
        if (doValida(DM_Situacao))
            ed.setDm_situacao(DM_Situacao);
        if (doValida(DM_Critica_Estoque))
            ed.setDm_critica_estoque(DM_Critica_Estoque);
        if (doValida(DM_Critica_Financeira))
            ed.setDm_critica_financeira(DM_Critica_Financeira);
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
        if (doValida(NM_Comprador))
            ed.setNm_comprador(NM_Comprador);
        if (doValida(DT_Emissao_Inicial))
            ed.setDt_emissao_inicial(DT_Emissao_Inicial);
        if (doValida(DT_Emissao_Final))
            ed.setDt_emissao_final(DT_Emissao_Final);
        if (doValida(DT_Entrega_Inicial))
            ed.setDt_entrega_inicial(DT_Entrega_Inicial);
        if (doValida(DT_Entrega_Final))
            ed.setDt_entrega_final(DT_Entrega_Final);
      
        return new PedidoRN().printPedidos(ed);       
    }
    
    //*** Trocas Clientes
    public void relTrocasClientes(HttpServletRequest request, HttpServletResponse response) throws Exception {
      
        String Relatorio = request.getParameter("Relatorio");
        String oid_Entregador = request.getParameter("oid_Entregador");
        String NR_Pedido = request.getParameter("FT_NR_Pedido");
        String NR_Pedido_Final = request.getParameter("FT_NR_Pedido_Final");
        String CD_Vendedor = request.getParameter("FT_CD_Vendedor");
        String CD_Vendedor_Final = request.getParameter("FT_CD_Vendedor_Final");
        String oid_Pessoa = request.getParameter("oid_Pessoa");
        String oid_Tipo_Pedido = request.getParameter("oid_Tipo_Pedido");
        String DT_Emissao_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
        String DT_Emissao_Final = request.getParameter("FT_DT_Emissao_Final");
        String DT_Entrega_Inicial = request.getParameter("FT_DT_Entrega_Inicial");
        String DT_Entrega_Final = request.getParameter("FT_DT_Entrega_Final");
        String DM_Situacao = request.getParameter("FT_DM_Situacao");
        String DM_Critica_Financeira = request.getParameter("FT_DM_Critica_Financeira");
        String DM_Critica_Estoque = request.getParameter("FT_DM_Critica_Estoque");
        
        if (!doValida(Relatorio))
            throw new Mensagens("Nome do Relat�rio n�o informado!");

        RelatorioED ed = new RelatorioED(response, Relatorio);
        if (doValida(oid_Entregador))
            ed.setOid_entregador(Integer.parseInt(oid_Entregador));
        if (doValida(NR_Pedido))
            ed.setNr_pedido(Integer.parseInt(NR_Pedido));
        if (doValida(NR_Pedido_Final))
            ed.setNr_pedido_final(Integer.parseInt(NR_Pedido_Final));
        if (doValida(CD_Vendedor))
            ed.setCd_vendedor(CD_Vendedor);
        if (doValida(CD_Vendedor_Final))
            ed.setCd_vendedor_final(CD_Vendedor_Final);
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
        if (doValida(DM_Situacao))
            ed.setDm_situacao(DM_Situacao);
        if (doValida(DM_Critica_Estoque))
            ed.setDm_critica_estoque(DM_Critica_Estoque);
        if (doValida(DM_Critica_Financeira))
            ed.setDm_critica_financeira(DM_Critica_Financeira);

        new PedidoRN().relTrocasClientes(ed);       
    }
    
    //*** Margem de Contribui��o do Vendedor(GORDURA)
    public void relResumoGorduras(HttpServletRequest request, HttpServletResponse response) throws Exception {
          
        String Relatorio = request.getParameter("Relatorio");
        String oid_Entregador = request.getParameter("oid_Entregador");
        String NR_Pedido = request.getParameter("FT_NR_Pedido");
        String NR_Pedido_Final = request.getParameter("FT_NR_Pedido_Final");
        String CD_Vendedor = request.getParameter("FT_CD_Vendedor");
        String CD_Vendedor_Final = request.getParameter("FT_CD_Vendedor_Final");
        String oid_Pessoa = request.getParameter("oid_Pessoa");
        String oid_Tipo_Pedido = request.getParameter("oid_Tipo_Pedido");
        String DT_Emissao_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
        String DT_Emissao_Final = request.getParameter("FT_DT_Emissao_Final");
        String DT_Entrega_Inicial = request.getParameter("FT_DT_Entrega_Inicial");
        String DT_Entrega_Final = request.getParameter("FT_DT_Entrega_Final");
        String DM_Situacao = request.getParameter("FT_DM_Situacao");
        String DM_Critica_Financeira = request.getParameter("FT_DM_Critica_Financeira");
        String DM_Critica_Estoque = request.getParameter("FT_DM_Critica_Estoque");
        
        if (!doValida(Relatorio))
            throw new Mensagens("Nome do Relat�rio n�o informado!");

        RelatorioED ed = new RelatorioED(response, Relatorio);
        if (doValida(oid_Entregador))
            ed.setOid_entregador(Integer.parseInt(oid_Entregador));
        if (doValida(NR_Pedido))
            ed.setNr_pedido(Integer.parseInt(NR_Pedido));
        if (doValida(NR_Pedido_Final))
            ed.setNr_pedido_final(Integer.parseInt(NR_Pedido_Final));
        if (doValida(CD_Vendedor))
            ed.setCd_vendedor(CD_Vendedor);
        if (doValida(CD_Vendedor_Final))
            ed.setCd_vendedor_final(CD_Vendedor_Final);
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
        if (doValida(DM_Situacao))
            ed.setDm_situacao(DM_Situacao);
        if (doValida(DM_Critica_Estoque))
            ed.setDm_critica_estoque(DM_Critica_Estoque);
        if (doValida(DM_Critica_Financeira))
            ed.setDm_critica_financeira(DM_Critica_Financeira);

        new PedidoRN().relResumoGorduras(ed);       
    }
}