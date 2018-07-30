package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.ReciboED;
import com.master.rn.ReciboRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;

/**
 * @author André Valadas
 * @JavaBean.class Recibos
 * @serialData 13/10/2005
 */
public class ReciboBean extends JavaUtil implements Serializable {

	public ReciboED inclui(HttpServletRequest request) throws Excecoes {

	    String oid_Unidade = request.getParameter("oid_Unidade");
	    String oid_Conta_Corrente = request.getParameter("oid_Conta_Corrente");
	    String oid_Pessoa = request.getParameter("oid_Pessoa");
	    
	    String DT_Recibo = request.getParameter("FT_DT_Recibo");
	    String VL_Recibo = request.getParameter("FT_VL_Recibo");
        String DM_Tipo = request.getParameter("FT_DM_Tipo");
        String TX_Observacao = request.getParameter("FT_TX_Observacao");
	    
		//*** Validações 
		if (!doValida(oid_Unidade))
		    throw new Mensagens("Unidade não informada!");
		if (!doValida(oid_Conta_Corrente))
		    throw new Mensagens("Conta Corrente não informada!");
		if (!doValida(oid_Pessoa))
		    throw new Mensagens("Pessoa não informada!");
        if (!doValida(DM_Tipo))
            throw new Mensagens("Tipo de Recibo não informado!");
        
		ReciboED ed = new ReciboED();
		ed.setOid_Unidade(Integer.parseInt(oid_Unidade));
		ed.setOid_Conta_Corrente(oid_Conta_Corrente);
		ed.setOid_Pessoa(oid_Pessoa);
        ed.setDM_Tipo(DM_Tipo);

        if (doValida(VL_Recibo))
            ed.setVL_Recibo(Double.parseDouble(VL_Recibo));
        if (doValida(DT_Recibo))
            ed.setDT_Recibo(DT_Recibo);
        if (doValida(TX_Observacao))
            ed.setTX_Observacao(TX_Observacao);
		
		return new ReciboRN().inclui(ed);
	}

	public void altera(HttpServletRequest request) throws Excecoes {

	    String oid_Recibo = request.getParameter("oid_Recibo");
        String oid_Unidade = request.getParameter("oid_Unidade");
        String oid_Conta_Corrente = request.getParameter("oid_Conta_Corrente");
        String oid_Pessoa = request.getParameter("oid_Pessoa");
        
        String DT_Recibo = request.getParameter("FT_DT_Recibo");
        String VL_Recibo = request.getParameter("FT_VL_Recibo");
        String DM_Tipo = request.getParameter("FT_DM_Tipo");
        String TX_Observacao = request.getParameter("FT_TX_Observacao");

	    //*** Validações 
	    if (!doValida(oid_Recibo))
		    throw new Mensagens("ID Recibo Recebimento não informado!");
	    
		ReciboED ed = new ReciboED(Integer.parseInt(oid_Recibo));
        if (doValida(oid_Unidade))
            ed.setOid_Unidade(Integer.parseInt(oid_Unidade));
        if (doValida(oid_Conta_Corrente))
            ed.setOid_Conta_Corrente(oid_Conta_Corrente);
        if (doValida(oid_Pessoa))
            ed.setOid_Pessoa(oid_Pessoa);
        if (doValida(DM_Tipo))
            ed.setDM_Tipo(DM_Tipo);
        if (doValida(VL_Recibo))
            ed.setVL_Recibo(Double.parseDouble(VL_Recibo));
        if (doValida(DT_Recibo))
            ed.setDT_Recibo(DT_Recibo);
        if (doValida(TX_Observacao))
            ed.setTX_Observacao(TX_Observacao);
        
		new ReciboRN().altera(ed);
	}

    public void deleta(HttpServletRequest request) throws Excecoes {

        String oid_Recibo = request.getParameter("oid_Recibo");
        if (!doValida(oid_Recibo))
            throw new Mensagens("ID Recibo não informado!");
        new ReciboRN().deleta(new ReciboED(Integer.parseInt(oid_Recibo)));
    }

    public ArrayList lista(HttpServletRequest request) throws Excecoes {
        
        return this.lista(request, true, true, true);
    }
	public ArrayList lista(HttpServletRequest request, boolean cCorrente, boolean tipoDoc, boolean unidade) throws Excecoes {

	    String oid_Recibo = request.getParameter("oid_Recibo");
	    String oid_Unidade = request.getParameter("oid_Unidade");
	    String oid_Conta_Corrente = request.getParameter("oid_Conta_Corrente");
	    String oid_Pessoa = request.getParameter("oid_Pessoa");
	    String DM_Tipo = request.getParameter("FT_DM_Tipo");
        
        String NR_Recibo = request.getParameter("FT_NR_Recibo");
        String VL_Recibo = request.getParameter("FT_VL_Recibo");
        String DT_Recibo = request.getParameter("FT_DT_Recibo");
        String DT_Recibo_Final = request.getParameter("FT_DT_Recibo_Final");
	    
	    ReciboED ed = new ReciboED(cCorrente, tipoDoc, unidade);
		//*** Validações
	    if (doValida(oid_Recibo))
		    ed.setOid_Recibo(Integer.parseInt(oid_Recibo));
		if (doValida(oid_Unidade))
		    ed.setOid_Unidade(Integer.parseInt(oid_Unidade));
        if (doValida(oid_Conta_Corrente))
            ed.setOid_Conta_Corrente(oid_Conta_Corrente);
		if (doValida(oid_Pessoa))
		    ed.setOid_Pessoa(oid_Pessoa);
		if (doValida(DM_Tipo))
		    ed.setDM_Tipo(DM_Tipo);
        if (doValida(NR_Recibo))
            ed.setNR_Recibo(Integer.parseInt(NR_Recibo));
        if (doValida(VL_Recibo))
            ed.setVL_Recibo(Double.parseDouble(VL_Recibo));
        if (doValida(DT_Recibo))
            ed.setDT_Recibo(DT_Recibo);
        if (doValida(DT_Recibo_Final))
            ed.setDT_Recibo_Final(DT_Recibo_Final);

		return new ReciboRN().lista(ed);
	}
    
	public ReciboED getByOid(String oid_Recibo) throws Excecoes {

        return this.getByOid(oid_Recibo, true, true, true);
	}
    public ReciboED getByOid(String oid_Recibo, boolean cCorrente, boolean pessoa, boolean unidade) throws Excecoes {

        if (!doValida(oid_Recibo))
            throw new Mensagens("ID Recibo não informado!");
        return new ReciboRN().getByRecord(new ReciboED(Integer.parseInt(oid_Recibo), cCorrente, pessoa, unidade));
    }
    
    public ReciboED getByNR_Recibo(String NR_Recibo) throws Excecoes {

        return this.getByNR_Recibo(NR_Recibo, true, true, true);
    }
    public ReciboED getByNR_Recibo(String NR_Recibo, boolean cCorrente, boolean pessoa, boolean unidade) throws Excecoes {

        if (doValida(NR_Recibo))
        {
            ReciboED edRecibo = new ReciboED();
            edRecibo.setCarregaCCorrente(cCorrente);
            edRecibo.setCarregaPessoa(pessoa);
            edRecibo.setCarregaUnidade(unidade);
            
            edRecibo.setNR_Recibo(Integer.parseInt(NR_Recibo));
            return new ReciboRN().getByRecord(edRecibo);
        } else return new ReciboED();
    }
    
    /** ------------ RELATÓRIOS ---------------- */
}