package com.master.iu;

/**
 * Módulo: Itens Pedido
 * @author André Valadas
*/
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Item_PedidoED;
import com.master.rn.Item_PedidoRN;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.Mensagens;
import com.master.util.Valores;

public class Ped002Bean extends BancoUtil {

    public Item_PedidoED inclui(HttpServletRequest request)throws Excecoes {

        String oid_Pedido = request.getParameter("oid_Pedido");
        String oid_Produto = request.getParameter("oid_Produto");
        String oid_Preco_Produto = request.getParameter("oid_Preco_Produto");
        String oid_Promocao_Produto = request.getParameter("oid_Promocao_Produto");
        String oid_Localizacao = request.getParameter("oid_Localizacao");

        String DM_Pedido = request.getParameter("FT_DM_Pedido");
        String NR_QT_Atendido = request.getParameter("FT_NR_QT_Atendido");
        String NR_Peso_Pedido = request.getParameter("FT_NR_Peso_Pedido");
        String NR_QT_Pedido = request.getParameter("FT_NR_QT_Pedido");
        String VL_Promocao = request.getParameter("FT_VL_Promocao");
        String VL_Item = request.getParameter("FT_VL_Item");
        String VL_Produto = request.getParameter("FT_VL_Produto");
        String VL_Unitario = request.getParameter("FT_VL_Unitario");
        String VL_Unitario_Tabela = request.getParameter("FT_VL_Unitario_Tabela");
        String VL_Desconto = request.getParameter("FT_VL_Desconto");
        String VL_IPI = request.getParameter("FT_VL_IPI");
        String VL_Margem_Contribuicao = request.getParameter("FT_VL_Margem_Contribuicao");
        String PE_Desconto = request.getParameter("FT_PE_Desconto");
        String PE_Aliquota_IPI = request.getParameter("FT_PE_Aliquota_IPI");
        String PE_Margem_Contribuicao = request.getParameter("FT_PE_Margem_Contribuicao");
        String DM_Quantidade = request.getParameter("FT_DM_Quantidade");
        String NR_Quantidade = request.getParameter("FT_NR_Quantidade");
        String NR_QT_Troca = request.getParameter("FT_NR_QT_Troca");
        String VL_Troca = request.getParameter("FT_VL_Troca");
        
        String isPesagem = request.getParameter("isPesagem");
        
        Item_PedidoED ed = new Item_PedidoED();
        if (doValida(oid_Pedido))
          ed.setOID_Pedido(Long.parseLong(oid_Pedido));
        if (doValida(oid_Produto))
            ed.setOID_Produto(oid_Produto);
        if (doValida(oid_Preco_Produto))
            ed.setOid_Preco_Produto(Integer.parseInt(oid_Preco_Produto));
        if (doValida(oid_Promocao_Produto))
            ed.setOid_Promocao_Produto(Integer.parseInt(oid_Promocao_Produto));
        if (doValida(oid_Localizacao))
            ed.setOid_Localizacao(Integer.parseInt(oid_Localizacao));

        //*** Valida Peso e Quantidades
        if (doValida(NR_QT_Atendido))
        {
            ed.setNR_QT_Pedido(Double.parseDouble(NR_QT_Atendido));
            ed.setNR_QT_Atendido(ed.getNR_QT_Pedido());
            //*** PESO PEDIDO
            if ("true".equals(isPesagem) && doValida(NR_Peso_Pedido))
                ed.setNR_Peso_Pedido(Double.parseDouble(NR_Peso_Pedido));
        }
        if (doValida(NR_QT_Pedido))
            ed.setNR_QT_Pedido(Double.parseDouble(NR_QT_Pedido));
        if ("C".equals(DM_Pedido))
            ed.setNR_Peso_Real(ed.getNR_QT_Pedido());
        else ed.setNR_Peso_Real(0);
        if (doValida(VL_Promocao))
            ed.setVL_Promocao(Double.parseDouble(VL_Promocao));
        if (doValida(VL_Item))
            ed.setVL_Item(Double.parseDouble(VL_Item));
        if (doValida(VL_Produto))
            ed.setVL_Produto(Double.parseDouble(VL_Produto));
        if (doValida(VL_Unitario))
            ed.setVL_Unitario(Double.parseDouble(VL_Unitario));
        if (doValida(VL_Unitario_Tabela))
            ed.setVL_Unitario_Tabela(Double.parseDouble(VL_Unitario_Tabela));
        if (doValida(VL_Desconto))
            ed.setVL_Desconto(Double.parseDouble(VL_Desconto));
        if (doValida(VL_IPI))
            ed.setVL_IPI(Double.parseDouble(VL_IPI));
        if (doValida(VL_Margem_Contribuicao))
            ed.setVL_Margem_Contribuicao(Double.parseDouble(VL_Margem_Contribuicao));
        if (doValida(NR_QT_Troca))
            ed.setNR_QT_Troca(Double.parseDouble(NR_QT_Troca));
        if (doValida(VL_Troca))
            ed.setVL_Troca(Double.parseDouble(VL_Troca));
        if (doValida(PE_Desconto))
            ed.setPE_Desconto(Double.parseDouble(PE_Desconto));
        if (doValida(PE_Aliquota_IPI))
            ed.setPE_Aliquota_IPI(Double.parseDouble(PE_Aliquota_IPI));        if (doValida(PE_Margem_Contribuicao))
            ed.setPE_Margem_Contribuicao(Double.parseDouble(PE_Margem_Contribuicao));
        if (doValida(DM_Quantidade))
            ed.setDM_Quantidade(DM_Quantidade);
        else ed.setDM_Quantidade("U");
        if (doValida(NR_Quantidade))
            ed.setNR_Quantidade(Double.parseDouble(NR_Quantidade));

        ed.setDM_Situacao("N");
        return new Item_PedidoRN().inclui(ed);
    }

    public void altera(HttpServletRequest request)throws Excecoes{

    	Item_PedidoED ed = new Item_PedidoED();

    	String oid_Item_Pedido = request.getParameter("oid_Item_Pedido");
    	String oid_Pedido = request.getParameter("oid_Pedido");
        String oid_Produto = request.getParameter("oid_Produto");
        String oid_Preco_Produto = request.getParameter("oid_Preco_Produto");
        String oid_Promocao_Produto = request.getParameter("oid_Promocao_Produto");
        String oid_Localizacao = request.getParameter("oid_Localizacao");
        
        String DM_Pedido = request.getParameter("FT_DM_Pedido");
        String NR_Peso_Real = request.getParameter("FT_NR_Peso_Real");
        String NR_Peso_Medio = request.getParameter("FT_NR_Peso_Medio");
        String NR_QT_Atendido = request.getParameter("FT_NR_QT_Atendido");
        String NR_QT_Pedido = request.getParameter("FT_NR_QT_Pedido");
        String VL_Promocao = request.getParameter("FT_VL_Promocao");
        String VL_Item = request.getParameter("FT_VL_Item");
        String VL_Produto = request.getParameter("FT_VL_Produto");
        String VL_Unitario = request.getParameter("FT_VL_Unitario");
        String VL_Desconto = request.getParameter("FT_VL_Desconto");
        String VL_IPI = request.getParameter("FT_VL_IPI");
        String VL_Margem_Contribuicao = request.getParameter("FT_VL_Margem_Contribuicao");
        String PE_Desconto = request.getParameter("FT_PE_Desconto");
        String PE_Aliquota_IPI = request.getParameter("FT_PE_Aliquota_IPI");
        String PE_Margem_Contribuicao = request.getParameter("FT_PE_Margem_Contribuicao");
        String DM_Situacao = request.getParameter("FT_DM_Situacao");
        String DM_Quantidade = request.getParameter("FT_DM_Quantidade");
        String NR_Quantidade = request.getParameter("FT_NR_Quantidade");
        String NR_QT_Troca = request.getParameter("FT_NR_QT_Troca");
        String VL_Troca = request.getParameter("FT_VL_Troca");
        
        //*** Validação da Pesagem 
        String isPesagem = request.getParameter("isPesagem");	
        
        if (doValida(oid_Item_Pedido))
            ed.setOID_Item_Pedido(Long.parseLong(oid_Item_Pedido));
        else throw new Excecoes("ID Item Pedido nao informado!");
        if (doValida(oid_Pedido))
          ed.setOID_Pedido(Long.parseLong(oid_Pedido));
        if (doValida(oid_Produto))
            ed.setOID_Produto(oid_Produto);
        if (doValida(oid_Preco_Produto))
            ed.setOid_Preco_Produto(Integer.parseInt(oid_Preco_Produto));
        if (doValida(oid_Promocao_Produto))
            ed.setOid_Promocao_Produto(Integer.parseInt(oid_Promocao_Produto));
        if (doValida(oid_Localizacao))
            ed.setOid_Localizacao(Integer.parseInt(oid_Localizacao));
        if (doValida(NR_QT_Atendido))
            ed.setNR_QT_Atendido(Double.parseDouble(NR_QT_Atendido));
        if (doValida(NR_QT_Pedido))
            ed.setNR_QT_Pedido(Double.parseDouble(NR_QT_Pedido));
        //*** PESAGEM
        if (doValida(isPesagem) && new Boolean(isPesagem).booleanValue())
        {	
            if ("C".equals(DM_Pedido))
                ed.setNR_Peso_Real(ed.getNR_QT_Pedido());
            else ed.setNR_Peso_Real(getValueDef(NR_Peso_Real, 0.0));
            if (ed.getNR_Peso_Real() > 0)
                ed.setNR_QT_Atendido(ed.getNR_QT_Atendido()-this.doValidaPesagem(ed.getNR_Peso_Real(), 
                        														 getValueDef(NR_Peso_Medio, 0.0), 
                        														 ed.getNR_QT_Atendido()));
        }
        if (doValida(VL_Promocao))
            ed.setVL_Promocao(Double.parseDouble(VL_Promocao));
        if (doValida(VL_Item))
            ed.setVL_Item(Double.parseDouble(VL_Item));
        if (doValida(VL_Produto))
            ed.setVL_Produto(Double.parseDouble(VL_Produto));
        if (doValida(VL_Unitario))
            ed.setVL_Unitario(Double.parseDouble(VL_Unitario));
        if (doValida(VL_Desconto))
            ed.setVL_Desconto(Double.parseDouble(VL_Desconto));
        if (doValida(VL_IPI))
            ed.setVL_IPI(Double.parseDouble(VL_IPI));
        if (doValida(VL_Margem_Contribuicao))
            ed.setVL_Margem_Contribuicao(Double.parseDouble(VL_Margem_Contribuicao));
        if (doValida(NR_QT_Troca))
            ed.setNR_QT_Troca(Double.parseDouble(NR_QT_Troca));
        if (doValida(VL_Troca))
            ed.setVL_Troca(Double.parseDouble(VL_Troca));
        if (doValida(PE_Desconto))
            ed.setPE_Desconto(Double.parseDouble(PE_Desconto));
        if (doValida(PE_Aliquota_IPI))
            ed.setPE_Aliquota_IPI(Double.parseDouble(PE_Aliquota_IPI));
        if (doValida(PE_Margem_Contribuicao))
            ed.setPE_Margem_Contribuicao(Double.parseDouble(PE_Margem_Contribuicao));
        if (doValida(DM_Situacao))
            ed.setDM_Situacao(DM_Situacao);
        else ed.setDM_Situacao("");

        if (doValida(DM_Quantidade))
            ed.setDM_Quantidade(DM_Quantidade);
        else ed.setDM_Quantidade("U");
        if (doValida(NR_Quantidade))
            ed.setNR_Quantidade(Double.parseDouble(NR_Quantidade));
        
        new Item_PedidoRN().altera(ed);
  	}

    public void deleta(HttpServletRequest request)throws Excecoes{

        String oid_Pedido = request.getParameter("oid_Pedido");
        String oid_Item_Pedido = request.getParameter("oid_Item_Pedido");
        if (!doValida(oid_Pedido) || !doValida(oid_Item_Pedido))
            throw new Excecoes("ID Pedido ou ID Item_Pedido não informado!");
        
        new Item_PedidoRN().deleta(new Item_PedidoED(Long.parseLong(oid_Item_Pedido), Long.parseLong(oid_Pedido), ""));
    }
    
    public void atualizaSituacaoItemPedido(HttpServletRequest request, String dmSituacao) throws Excecoes {

        String oid_Item_Pedido = request.getParameter("oid_Item_Pedido");
        String oid_Pedido = request.getParameter("oid_Pedido");

        if (!doValida(oid_Item_Pedido))
            throw new Mensagens("Item Pedido não informado!");
        if (!doValida(oid_Pedido))
            throw new Mensagens("Pedido não informado!");
        if (!doValida(dmSituacao))
            throw new Mensagens("Situação não informada!");
        
        new Item_PedidoRN().atualizaSituacaoItemPedido(new Item_PedidoED(Long.parseLong(oid_Item_Pedido), Long.parseLong(oid_Pedido), dmSituacao));
    }
    
    /** PESAGEM ITENS
     *  listaItens<Itens_Pedidos>
     */
    public void pesagemItens(HttpServletRequest request) throws Exception {

        ArrayList lista = (ArrayList) request.getSession(true).getAttribute("listaItens"); 
        if (lista.size() < 1)
            throw new Mensagens("Lista de Itens vazia! Execute novamente a consulta!");
        ArrayList itensPesados = new ArrayList();
        //*** Carrega Pesagem da Tela
        for (int i=0; i<lista.size(); i++)
        {
            Item_PedidoED edItem = (Item_PedidoED) lista.get(i);
            edItem.setNR_Peso_Real(Valores.converteStringToDouble(request.getParameter("FT_NR_Peso_Real"+i)));
            edItem.setVL_Desconto(Double.parseDouble(request.getParameter("FT_VL_Desconto"+i)));
            edItem.setVL_Promocao(Double.parseDouble(request.getParameter("FT_VL_Promocao"+i)));
            edItem.setVL_Produto(Double.parseDouble(request.getParameter("FT_VL_Produto"+i)));
            edItem.setVL_Item(Double.parseDouble(request.getParameter("FT_VL_Item"+i)));
            itensPesados.add(edItem);
        }
        new Item_PedidoRN().pesagemItens(itensPesados);
    }
    
	public Item_PedidoED getByRecord(HttpServletRequest request)throws Excecoes {
	 
	    Item_PedidoED ed = new Item_PedidoED();
	    String oid_Item_Pedido = request.getParameter("oid_Item_Pedido");
	    if (doValida(oid_Item_Pedido))
	    	ed.setOID_Item_Pedido(new Long(oid_Item_Pedido).longValue());
	
	    return new Item_PedidoRN().getByRecord(ed);	
	}
    
    public Item_PedidoED getByRecord(String NR_Pedido, String CD_Produto, String CD_Fornecedor, String DM_Pedido)throws Excecoes {
        
        Item_PedidoED ed = new Item_PedidoED();
        if (doValida(NR_Pedido) &&  doValida(DM_Pedido) && (doValida(CD_Produto) || doValida(CD_Fornecedor)))
        {
            ed.setOID_Pedido(getTableIntValue("oid_Pedido","Pedidos","DM_Pedido="+getSQLString(DM_Pedido)+" AND NR_Pedido="+getSQLString(NR_Pedido)));
            if (ed.getOID_Pedido() > 0)
            {
                ed.setCD_Produto(CD_Produto);
                ed.setCD_Fornecedor(CD_Fornecedor);
                return new Item_PedidoRN().getByRecord(ed); 
            } else return ed;
        } else return ed;
    }
    
	public Item_PedidoED getByOID_Item_Pedido(String oid_Item_Pedido)throws Excecoes{

	    if (!doValida(oid_Item_Pedido))
	       throw new Excecoes("ID Item Pedido não informado!");      

        Item_PedidoED ed = new Item_PedidoED();
        ed.setOID_Item_Pedido(Long.parseLong(oid_Item_Pedido));
	    return new Item_PedidoRN().getByRecord(ed);
	}

	public ArrayList Item_Pedido_Lista(HttpServletRequest request)throws Excecoes{

	    String DM_Pedido = request.getParameter("FT_DM_Pedido");
	    String oid_Pedido = request.getParameter("oid_Pedido");
	    String oid_Pessoa = request.getParameter("oid_Pessoa");
	    String oid_Produto = request.getParameter("oid_Produto");
	    
        Item_PedidoED ed = new Item_PedidoED();
	    if (doValida(DM_Pedido))	            
	        ed.setDM_Pedido(DM_Pedido);
        if (doValida(oid_Pedido))
          ed.setOID_Pedido(Long.parseLong(oid_Pedido));        
        if (doValida(oid_Pessoa))
          ed.setOID_Pessoa(oid_Pessoa);        
        if (doValida(oid_Produto))
          ed.setOID_Produto(oid_Produto);
        
        ArrayList lista = new Item_PedidoRN().lista(ed);
        Collections.sort(lista, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Item_PedidoED) o1).getNM_Produto().compareTo(((Item_PedidoED) o2).getNM_Produto());
            }
        });        
        return lista;
	}
    //*** Itens p/ Pesagem
    public ArrayList listaItensPesagem(String oid_Pedido, String oid_Unidade, String dmPedido) throws Excecoes {

        if (!doValida(oid_Pedido))
            throw new Mensagens("Pedido não informado para Listar Itens de Pesagem!");
        if (!doValida(oid_Unidade))
            throw new Mensagens("Unidade não informada para Listar Itens de Pesagem!");
        if (!doValida(dmPedido))
            throw new Mensagens("Tipo de Pedido não informado para Listar Itens de Pesagem!");
        
        Item_PedidoED ed = new Item_PedidoED(Long.parseLong(oid_Pedido));
        ed.setOID_Unidade(Long.parseLong(oid_Unidade));
        ed.setDM_Pedido(dmPedido);
        ed.setNR_QT_Listar("Pesagem");

        return new Item_PedidoRN().lista(ed);
    }
	//*** Recebidos e Faturados 
	public ArrayList Item_Recebido_Lista(HttpServletRequest request)throws Excecoes {

	    String oid_Pedido = request.getParameter("oid_Pedido");
	    if (!doValida(oid_Pedido))
            throw new Excecoes("ID Pedido não informado!");

        return new Item_PedidoRN().lista_Recebidos(new Item_PedidoED(Long.parseLong(oid_Pedido)));
	}

	public ArrayList Item_Pedido_Lista_Pendente(HttpServletRequest request)throws Excecoes {

    	String DM_Pedido = request.getParameter("FT_DM_Pedido");
    	String oid_Pedido = request.getParameter("oid_Pedido");
    	String oid_Pessoa = request.getParameter("oid_Pessoa");
    	String NM_Produto = request.getParameter("FT_NM_Produto");
        String NR_Pedido = request.getParameter("FT_NR_Pedido");
    	
        Item_PedidoED ed = new Item_PedidoED();
    	if (doValida(DM_Pedido))
    	    ed.setDM_Pedido(DM_Pedido);
        if (doValida(oid_Pedido))
          ed.setOID_Pedido(Long.parseLong(oid_Pedido));
        if (doValida(oid_Pessoa))
          ed.setOID_Pessoa(oid_Pessoa);
        if (doValida(NM_Produto))
            ed.setNM_Produto(NM_Produto);
        if (doValida(NR_Pedido))
            ed.setNR_Pedido(Long.parseLong(NR_Pedido));
        ed.setNR_QT_Listar("Pendente");

        return new Item_PedidoRN().lista(ed);
	}
	
	//*** Valida e busca Mensagem caso encontre alguma irregularidade[Pedido de Compra e Venda]
	public String doValidacao(String DM_Situacao, String DM_Critica_Financeira, String DT_Pedido) throws ParseException {
	    
      	Calendar calDataHoje = Calendar.getInstance();
      	calDataHoje.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(Data.getDataDMY()));
      	
      	Calendar calDataPedido = Calendar.getInstance();
      	calDataPedido.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(DT_Pedido));
      	
  		if ("C".equals(DM_Situacao)){
  		    return "Esse Pedido esta cancelado!";
  		} else if ("F".equals(DM_Situacao)){
  		    return "Esse Pedido ja foi finalizado!";
  		} else if ("A".equals(DM_Situacao)){
  		    return "Esse Pedido ja foi aprovado!";
  		//} else if (DT_Pedido != null && (calDataHoje.after(calDataPedido))){
  		//    return "A data atual é superior a data do Pedido! Você não pode alterar os Produtos!";
  		} else if ("S".equals(DM_Critica_Financeira)){
  		    return "Crítica Financeira: Cliente com crédito bloqueado para o Pedido!";
  		} else return null;
	}
	
	//*** Não deixa ALTERAR ou EXCLUIR produtos(Compra ou Venda) que estejam relacionados a uma NF
	public boolean doExiste(HttpServletRequest request) throws Excecoes {
	    
        try {
    	    String oid_Item_Pedido = request.getParameter("oid_Item_Pedido");
    	    String oid_Pedido = null;
    	    if (!doValida(oid_Item_Pedido))
            { 
    	        oid_Pedido = request.getParameter("oid_Pedido");
    	        if (!doValida(oid_Pedido))
    	            throw new Excecoes("ÌD Pedido nao informado!!");
    	    }
    
    	    String strFrom = "Itens_Notas_Fiscais, Notas_Fiscais ";
    	    String strWhere = "Itens_Notas_Fiscais.oid_Nota_Fiscal = Notas_Fiscais.oid_Nota_Fiscal ";
    	    if (doValida(oid_Item_Pedido))
    	        strWhere +=" AND Itens_Notas_Fiscais.OID_Item_Pedido = '" +oid_Item_Pedido+ "'";
    	    else {
    	        strWhere += " AND Itens_Notas_Fiscais.OID_Item_Pedido = Itens_Pedidos.oid_Item_Pedido " +
    	        			" AND Itens_Pedidos.oid_Pedido = Pedidos.oid_Pedido" +
    	       			  	" AND Pedidos.oid_Pedido = '"+oid_Pedido+"'";
    	    }
            return super.doExiste(strFrom, strWhere);
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "doExiste()");
        }
    }
	
	//*** Se existe Itens para esse Pedido
	public boolean doExisteItensPedido(String oid_Pedido) throws Excecoes {
	    
        try {
    	    if (!doValida(oid_Pedido))
    	        throw new Excecoes("Pedido não informado!");
    	    return super.doExiste("Itens_Pedidos",
                    			  " oid_Pedido = " +oid_Pedido +
                    			  " AND DM_Situacao <> 'C'");
        } catch (Mensagens e) {
            throw e;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "doExisteItensPedido()");
        }
    }
	
	//*** Valida Peso Medio / Quantidade atendida (Pedido Venda) - retorna DIFERENÇA
	public double doValidaPesagem(double NR_Peso_Real, double NR_Peso_Medio, double NR_QT_Atendido) throws Excecoes {
	    
	    if (NR_Peso_Real <= 0 || NR_Peso_Medio <= 0 || NR_QT_Atendido <= 0)
	        return 0;
	 
	    double resultDiv = NR_Peso_Real / NR_Peso_Medio; 
	    if (resultDiv > 0 && resultDiv < NR_QT_Atendido) {
	        return Math.floor(NR_QT_Atendido % resultDiv); // Diminui QTD 
	    } else return 0;
    }
}