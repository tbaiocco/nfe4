package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.EntregaED;
import com.master.ed.RelatorioED;
import com.master.rn.EntregaRN;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;

/**
 * @author André Valadas
 */
public class EntregaBean extends JavaUtil implements Serializable {

	public EntregaED inclui(HttpServletRequest request) throws Excecoes {

	    String oid_Unidade = request.getParameter("oid_Unidade");
	    String oid_Entregador = request.getParameter("oid_Entregador");
	    String oid_Veiculo = request.getParameter("oid_Veiculo");
	    String DT_Entrega = request.getParameter("FT_DT_Entrega");
	    
		//*** Validações
		if (!doValida(oid_Unidade))
		    throw new Excecoes("Unidade não informada!");
		if (!doValida(oid_Entregador))
		    throw new Excecoes("Entregador não informado!");
		if (!doValida(oid_Veiculo))
		    throw new Excecoes("Veículo não informado!");
		if (!doValida(DT_Entrega))
		    throw new Excecoes("Data da Entrega não informada!");
		
		//*** Verifica se existe NF para esse Entregador
		//    Que ainda não esteje relacionada a Documentos_Entregas 
		if (!new BancoUtil().doExiste("Notas_Fiscais " +
                					  "		LEFT JOIN Documentos_Entregas" +
                					  "			ON Notas_Fiscais.oid_nota_fiscal = Documentos_Entregas.oid_Nota_Fiscal",
                		        	  " Notas_Fiscais.DM_Tipo_Nota_Fiscal IN ('S','D')" +
                		        	  " AND  Notas_Fiscais.DM_Situacao IN ('F','D')" +
                		        	  " AND (Notas_Fiscais.oid_Nota_Fiscal <> Documentos_Entregas.oid_Nota_Fiscal OR Documentos_Entregas.oid_Nota_Fiscal is null)" +
                		        	  " AND  Notas_Fiscais.oid_Entregador = Entregadores.oid_Entregador" +
                		        	  " AND  Notas_Fiscais.oid_Entregador = "+oid_Entregador+
                                      " AND  Notas_Fiscais.DT_Entrega = '"+DT_Entrega+"'"))
		    throw new Mensagens("Não há Notas Fiscais para esse Entregador!");
		
		EntregaED ed = new EntregaED();
		ed.setOid_Unidade(Integer.parseInt(oid_Unidade));
		ed.setOid_Entregador(Integer.parseInt(oid_Entregador));
		ed.setOid_Veiculo(oid_Veiculo);
		ed.setDT_Entrega(DT_Entrega);
		ed.setDM_Situacao("N");
		
		return new EntregaRN().inclui(ed);
	}

	public void altera(HttpServletRequest request) throws Excecoes {

	    String oid_Entrega = request.getParameter("oid_Entrega");
	    String VL_Vendas = request.getParameter("FT_VL_Vendas");
	    String VL_Cobranca = request.getParameter("FT_VL_Cobranca");
	    String VL_Devolucao_Cancelamento = request.getParameter("FT_VL_Devolucao_Cancelamento");
	    String VL_Dinheiro = request.getParameter("FT_VL_Dinheiro");
	    String VL_Moeda = request.getParameter("FT_VL_Moeda");
	    String VL_Cheque = request.getParameter("FT_VL_Cheque");
	    String VL_Vale = request.getParameter("FT_VL_Vale");
	    String VL_Saldo = request.getParameter("FT_VL_Saldo");
	    
	    if (!doValida(oid_Entrega))
		    throw new Excecoes("Entrega não informada!");
	    
		EntregaED ed = new EntregaED(Integer.parseInt(oid_Entrega));
		if (doValida(VL_Vendas))
		    ed.setVL_Vendas(Double.parseDouble(VL_Vendas));
		if (doValida(VL_Cobranca))
		    ed.setVL_Cobranca(Double.parseDouble(VL_Cobranca));
		if (doValida(VL_Devolucao_Cancelamento))
		    ed.setVL_Devolucao_Cancelamento(Double.parseDouble(VL_Devolucao_Cancelamento));
		if (doValida(VL_Dinheiro))
		    ed.setVL_Dinheiro(Double.parseDouble(VL_Dinheiro));
		if (doValida(VL_Moeda))
		    ed.setVL_Moeda(Double.parseDouble(VL_Moeda));
		if (doValida(VL_Cheque))
		    ed.setVL_Cheque(Double.parseDouble(VL_Cheque));
		if (doValida(VL_Vale))
		    ed.setVL_Vale(Double.parseDouble(VL_Vale));
		if (doValida(VL_Saldo))
		    ed.setVL_Saldo(Double.parseDouble(VL_Saldo));
		
		new EntregaRN().altera(ed);
	}

	public void deleta(HttpServletRequest request) throws Excecoes {

		String oid_Entrega = request.getParameter("oid_Entrega");
		if (!doValida(oid_Entrega))
		    throw new Excecoes("Entrega não informada!");
		new EntregaRN().deleta(new EntregaED(Integer.parseInt(oid_Entrega)));
	}

    public ArrayList lista(HttpServletRequest request) throws Excecoes {

        return this.lista(request, true, true, true);
    }
    
	public ArrayList lista(HttpServletRequest request, boolean entregador, boolean unidade, boolean veiculo) throws Excecoes {

	    String oid_Entrega = request.getParameter("oid_Entrega");
	    String oid_Unidade = request.getParameter("oid_Unidade");
	    String oid_Entregador = request.getParameter("oid_Entregador");
	    String oid_Veiculo = request.getParameter("oid_Veiculo");
	    String DM_Situacao = request.getParameter("FT_DM_Situacao");
	    String DT_Entrega = request.getParameter("FT_DT_Entrega");
	    String DT_Entrega_Final = request.getParameter("FT_DT_Entrega_Final");
	    String DT_Acerto = request.getParameter("FT_DT_Acerto");
	    String DT_Acerto_Final = request.getParameter("FT_DT_Acerto_Final");
	    
	    EntregaED ed = new EntregaED(entregador, unidade, veiculo);
		//*** Validações
	    if (doValida(oid_Entrega))
		    ed.setOid_Entrega(Integer.parseInt(oid_Entrega));
		if (doValida(oid_Unidade))
		    ed.setOid_Unidade(Integer.parseInt(oid_Unidade));
		if (doValida(oid_Entregador))
		    ed.setOid_Entregador(Integer.parseInt(oid_Entregador));
		if (doValida(oid_Veiculo))
		    ed.setOid_Veiculo(oid_Veiculo);
		if (doValida(DM_Situacao))
		    ed.setDM_Situacao(DM_Situacao);
		if (doValida(DT_Entrega))
		    ed.setDT_Entrega(DT_Entrega);
		if (doValida(DT_Entrega_Final))
		    ed.setDT_Entrega_Final(DT_Entrega_Final);
		if (doValida(DT_Acerto))
		    ed.setDT_Acerto(DT_Acerto);
		if (doValida(DT_Acerto_Final))
		    ed.setDT_Acerto_Final(DT_Acerto_Final);
	    
		return new EntregaRN().lista(ed);
	}

    public EntregaED getByOid(String oid_Entrega) throws Excecoes {

        return new EntregaRN().getByRecord(new EntregaED(Integer.parseInt(oid_Entrega), true, true, true));
    }
	public EntregaED getByOid(String oid_Entrega, boolean entregador, boolean unidade, boolean veiculo) throws Excecoes {

	    if (!doValida(oid_Entrega))
		    throw new Excecoes("Entrega não informada!");
		return new EntregaRN().getByRecord(new EntregaED(Integer.parseInt(oid_Entrega), entregador, unidade, veiculo));
	}
	
	public EntregaED getByNR_Entrega(String NR_Entrega) throws Excecoes {

	    EntregaED ed = new EntregaED();
	    if (!doValida(NR_Entrega))
		    return ed;
	    ed.setNR_Entrega(Integer.parseInt(NR_Entrega));
		return new EntregaRN().getByRecord(ed);
	}
	
	//*** Finaliza ACERTO/ENTREGA
	public void finalizaAcerto(HttpServletRequest request) throws Excecoes {

	    String oid_Entrega = request.getParameter("oid_Entrega");
	    if (!doValida(oid_Entrega))
	        throw new Mensagens("Entrega não informada!");
		new EntregaRN().finalizaAcerto(new EntregaED(Integer.parseInt(oid_Entrega)));
	}
	
	/** ------------ RELATÓRIOS ---------------- */
	//*** Acerto Entregas
    public void relAcertoEntrega(HttpServletRequest request, HttpServletResponse response) throws Excecoes {
      
        String Relatorio = request.getParameter("Relatorio");
        String oid_Entrega = request.getParameter("oid_Entrega");
        String DM_Situacao = request.getParameter("FT_DM_Situacao");
        
        if (!doValida(Relatorio))
            throw new Mensagens("Nome do Relatório não informado!");

        RelatorioED ed = new RelatorioED(response, Relatorio);
        if (doValida(oid_Entrega))
            ed.setOid_entrega(Integer.parseInt(oid_Entrega));
        if (doValida(DM_Situacao))
            ed.setDm_situacao(DM_Situacao);
      
        new EntregaRN().relAcertoEntrega(ed);       
    }
}