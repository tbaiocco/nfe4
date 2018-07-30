package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Forma_PagamentoED;
import com.master.rn.Forma_PagamentoRN;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;

/**
 * @author André Valadas
 * - Formas de Pagamentos
 */
public class Forma_PagamentoBean extends JavaUtil implements Serializable {

	public Forma_PagamentoED inclui(HttpServletRequest request) throws Excecoes {

	    String oid_Condicao_Pagamento = request.getParameter("oid_Condicao_Pagamento");
	    String DM_Tipo_Pagamento = request.getParameter("FT_DM_Tipo_Pagamento");
	    String DM_Tipo_Operacao = request.getParameter("FT_DM_Tipo_Operacao");
	    String DM_Tipo_Cobranca = request.getParameter("FT_DM_Tipo_Cobranca");
	    
		//*** Validações
	    if (!doValida(oid_Condicao_Pagamento))
		    throw new Mensagens("Condição de Pagamento não informada!");
		if (!doValida(DM_Tipo_Pagamento))
		    throw new Mensagens("Tipo de Pagamento não informado!");
		if (!doValida(DM_Tipo_Operacao))
		    throw new Mensagens("Tipo de Operação não informada!");
		if (!doValida(DM_Tipo_Cobranca))
		    throw new Mensagens("Tipo de Cobrança não informada!");
		
		if (new BancoUtil().doExiste("Formas_Pagamentos",
                		        	 "oid_Condicao_Pagamento = "+oid_Condicao_Pagamento+
                		        	 " AND DM_Tipo_Pagamento = '"+DM_Tipo_Pagamento+"'"))
		    throw new Mensagens("Esse Tipo já esta cadastrado para essa Condição de Pagamento!");
		
		Forma_PagamentoED ed = new Forma_PagamentoED();
		if (doValida(oid_Condicao_Pagamento))
		    ed.setOid_Condicao_Pagamento(Integer.parseInt(oid_Condicao_Pagamento));
	  	ed.setDM_Tipo_Pagamento(DM_Tipo_Pagamento);
	  	ed.setDM_Tipo_Operacao(DM_Tipo_Operacao);
	  	ed.setDM_Tipo_Cobranca(DM_Tipo_Cobranca);
		
		return new Forma_PagamentoRN().inclui(ed);
	}

	public void altera(HttpServletRequest request) throws Excecoes {

	    String oid_Forma_Pagamento = request.getParameter("oid_Forma_Pagamento");
	    String oid_Condicao_Pagamento = request.getParameter("oid_Condicao_Pagamento");
	    String DM_Tipo_Pagamento = request.getParameter("FT_DM_Tipo_Pagamento");
	    String DM_Tipo_Operacao = request.getParameter("FT_DM_Tipo_Operacao");
	    String DM_Tipo_Cobranca = request.getParameter("FT_DM_Tipo_Cobranca");
	    
		//*** Validações
	    if (!doValida(oid_Forma_Pagamento))
	    	throw new Mensagens("Forma de Pagamento não informada!");
	    if (!doValida(oid_Condicao_Pagamento))
		    throw new Mensagens("Condição de Pagamento não informada!");
		if (!doValida(DM_Tipo_Pagamento))
		    throw new Mensagens("Tipo de Pagamento não informado!");
		if (!doValida(DM_Tipo_Operacao))
		    throw new Mensagens("Tipo de Operação não informada!");
		if (!doValida(DM_Tipo_Cobranca))
		    throw new Mensagens("Tipo de Cobrança não informada!");
	    
		//*** Valida se Pode Alterar
		if (new BancoUtil().doExiste("Formas_Pagamentos",
                	        	 	 "oid_Forma_Pagamento <> "+oid_Forma_Pagamento+
                	        	 	 " AND oid_Condicao_Pagamento = "+oid_Condicao_Pagamento+
                	        	 	 " AND DM_Tipo_Pagamento = '"+DM_Tipo_Pagamento+"'"))
		    throw new Mensagens("Esse Tipo já existe para essa Condição de Pagamento!");
		
		Forma_PagamentoED ed = new Forma_PagamentoED(Integer.parseInt(oid_Forma_Pagamento));
		if (doValida(oid_Condicao_Pagamento))
		    ed.setOid_Condicao_Pagamento(Integer.parseInt(oid_Condicao_Pagamento));
	  	ed.setDM_Tipo_Pagamento(DM_Tipo_Pagamento);
	  	ed.setDM_Tipo_Operacao(DM_Tipo_Operacao);
	  	ed.setDM_Tipo_Cobranca(DM_Tipo_Cobranca);
		
		new Forma_PagamentoRN().altera(ed);
	}

	public void deleta(HttpServletRequest request) throws Excecoes {

		String oid_Forma_Pagamento = request.getParameter("oid_Forma_Pagamento");
		if (!doValida(oid_Forma_Pagamento))
		    throw new Mensagens("Forma de Pagamento não informada!");
		new Forma_PagamentoRN().deleta(new Forma_PagamentoED(Integer.parseInt(oid_Forma_Pagamento)));
	}

	public ArrayList lista(HttpServletRequest request) throws Excecoes {

	    String oid_Forma_Pagamento = request.getParameter("oid_Forma_Pagamento");
	    String oid_Condicao_Pagamento = request.getParameter("oid_Condicao_Pagamento");
	    String DM_Tipo_Pagamento = request.getParameter("FT_DM_Tipo_Pagamento");
	    String DM_Tipo_Operacao = request.getParameter("FT_DM_Tipo_Operacao");
	    String DM_Tipo_Cobranca = request.getParameter("FT_DM_Tipo_Cobranca");
		
		Forma_PagamentoED ed = new Forma_PagamentoED();
		if (doValida(oid_Forma_Pagamento))
		    ed.setOid_Forma_Pagamento(Integer.parseInt(oid_Forma_Pagamento));
		if (doValida(oid_Condicao_Pagamento))
		    ed.setOid_Condicao_Pagamento(Integer.parseInt(oid_Condicao_Pagamento));
		if (doValida(DM_Tipo_Pagamento))
		    ed.setDM_Tipo_Pagamento(DM_Tipo_Pagamento);
		if (doValida(DM_Tipo_Pagamento))
		    ed.setDM_Tipo_Operacao(DM_Tipo_Operacao);
	  	if (doValida(DM_Tipo_Cobranca))
	  	    ed.setDM_Tipo_Cobranca(DM_Tipo_Cobranca);
	    
		return new Forma_PagamentoRN().lista(ed);
	}

	public Forma_PagamentoED getByOid_Forma(String oid_Forma_Pagamento) throws Excecoes {

	    if (!doValida(oid_Forma_Pagamento))
	        throw new Mensagens("Forma de Pagamento não informada!");
		return new Forma_PagamentoRN().getByRecord(new Forma_PagamentoED(Integer.parseInt(oid_Forma_Pagamento)));
	}
    
    public Forma_PagamentoED getByTipoPagamento(String oid_Condicao_Pagamento, String DM_Tipo_Pagamento) throws Excecoes {

        if (!doValida(oid_Condicao_Pagamento))
            throw new Mensagens("Condição de Pagamento não informada!");
        if (!doValida(DM_Tipo_Pagamento))
            throw new Mensagens("Meio de Pagamento não informado!");
        Forma_PagamentoED ed = new Forma_PagamentoED();
        ed.setOid_Condicao_Pagamento(Integer.parseInt(oid_Condicao_Pagamento));
        ed.setDM_Tipo_Pagamento(DM_Tipo_Pagamento);
        
        return new Forma_PagamentoRN().getByRecord(ed);
    }
}