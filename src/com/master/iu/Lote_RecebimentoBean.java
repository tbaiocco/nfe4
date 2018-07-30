package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.Lote_RecebimentoED;
import com.master.ed.RelatorioED;
import com.master.rn.Lote_RecebimentoRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;

/**
 * @author André Valadas
 * @serial Lotes Recebimentos
 * @serialData 15/08/2005
 */
public class Lote_RecebimentoBean extends JavaUtil implements Serializable {

	public Lote_RecebimentoED inclui(HttpServletRequest request) throws Excecoes {

	    String oid_Unidade = request.getParameter("oid_Unidade");
	    String oid_Conta_Corrente = request.getParameter("oid_Conta_Corrente");
	    String oid_Tipo_Documento = request.getParameter("oid_Tipo_Documento");
	    
	    String DT_Emissao = request.getParameter("FT_DT_Emissao");
        String DT_Programada = request.getParameter("FT_DT_Programada");
	    String VL_Lote = request.getParameter("FT_VL_Lote");
        String TX_Observacao = request.getParameter("FT_TX_Observacao");
	    
		//*** Validações 
		if (!doValida(oid_Unidade))
		    throw new Excecoes("Unidade não informada!");
		if (!doValida(oid_Conta_Corrente))
		    throw new Excecoes("Conta Corrente não informada!");
		if (!doValida(oid_Tipo_Documento))
		    throw new Excecoes("Tipo de Documento não informado!");
        if (!doValida(DT_Programada))
            throw new Excecoes("Data Programada não informada!");
        
		Lote_RecebimentoED ed = new Lote_RecebimentoED();
		ed.setOid_Unidade(Integer.parseInt(oid_Unidade));
		ed.setOid_Conta_Corrente(oid_Conta_Corrente);
		ed.setOid_Tipo_Documento(Integer.parseInt(oid_Tipo_Documento));
		ed.setDT_Programada(DT_Programada);

        if (doValida(VL_Lote))
            ed.setVL_Lote(Double.parseDouble(VL_Lote));
        if (doValida(DT_Emissao))
            ed.setDT_Emissao(DT_Emissao);
        if (doValida(TX_Observacao))
            ed.setTX_Observacao(TX_Observacao);
		
		return new Lote_RecebimentoRN().inclui(ed);
	}

	public void altera(HttpServletRequest request) throws Excecoes {

	    String oid_Lote_Recebimento = request.getParameter("oid_Lote_Recebimento");
	    String DT_Programada = request.getParameter("FT_DT_Programada");
	    String TX_Observacao = request.getParameter("FT_TX_Observacao");

	    //*** Validações 
	    if (!doValida(oid_Lote_Recebimento))
		    throw new Excecoes("ID Lote Recebimento não informado!");
	    
		Lote_RecebimentoED ed = new Lote_RecebimentoED(Integer.parseInt(oid_Lote_Recebimento));
        if (doValida(DT_Programada))
            ed.setDT_Programada(DT_Programada);
        if (doValida(TX_Observacao))
            ed.setTX_Observacao(TX_Observacao);
        
		new Lote_RecebimentoRN().altera(ed);
	}

	public void cancela(HttpServletRequest request) throws Exception {

		String oid_Lote_Recebimento = request.getParameter("oid_Lote_Recebimento");
		if (!doValida(oid_Lote_Recebimento))
		    throw new Mensagens("ID Lote Recebimento não informado!");
        Lote_RecebimentoED edLote = new Lote_RecebimentoED(Integer.parseInt(oid_Lote_Recebimento));
        edLote.setDM_Situacao("C");
        //*** Cancela Lote
		new Lote_RecebimentoRN().cancela(edLote);
	}
    
    public void compensacao(HttpServletRequest request) throws Exception {

        String oid_Lote_Recebimento = request.getParameter("oid_Lote_Recebimento");
        String DT_Compensacao = request.getParameter("FT_DT_Compensacao");
        if (!doValida(oid_Lote_Recebimento))
            throw new Mensagens("ID Lote Recebimento não informado!");
        if (!doValida(DT_Compensacao))
            throw new Mensagens("Data de Compensação não informada!");

        Lote_RecebimentoED edLote = new Lote_RecebimentoED(Integer.parseInt(oid_Lote_Recebimento));
        edLote.setDT_Compensacao(DT_Compensacao);
        edLote.setDM_Situacao("F");
        new Lote_RecebimentoRN().compensacao(edLote);
    }
    
    public void deleta(HttpServletRequest request) throws Excecoes {

        String oid_Lote_Recebimento = request.getParameter("oid_Lote_Recebimento");
        
        if (!doValida(oid_Lote_Recebimento))
            throw new Mensagens("ID Lote Recebimento não informado!");
        new Lote_RecebimentoRN().deleta(new Lote_RecebimentoED(Integer.parseInt(oid_Lote_Recebimento)));
    }

	public ArrayList lista(HttpServletRequest request, boolean cCorrente, boolean tipoDoc, boolean unidade) throws Excecoes {

	    String oid_Lote_Recebimento = request.getParameter("oid_Lote_Recebimento");
	    String oid_Unidade = request.getParameter("oid_Unidade");
	    String oid_Conta_Corrente = request.getParameter("oid_Conta_Corrente");
	    String oid_Tipo_Documento = request.getParameter("oid_Tipo_Documento");
	    String DM_Situacao = request.getParameter("FT_DM_Situacao");
        
        String NR_Lote = request.getParameter("FT_NR_Lote");
        String VL_Lote = request.getParameter("FT_VL_Lote");
        String DT_Emissao = request.getParameter("FT_DT_Emissao");
        String DT_Emissao_Final = request.getParameter("FT_DT_Emissao_Final");
        String DT_Programada = request.getParameter("FT_DT_Programada");
        String DT_Programada_Final = request.getParameter("FT_DT_Programada_Final");
        String DT_Compensacao = request.getParameter("FT_DT_Compensacao");
        String DT_Compensacao_Final = request.getParameter("FT_DT_Compensacao_Final");
	    
	    Lote_RecebimentoED ed = new Lote_RecebimentoED(cCorrente, tipoDoc, unidade);
		//*** Validações
	    if (doValida(oid_Lote_Recebimento))
		    ed.setOid_Lote_Recebimento(Integer.parseInt(oid_Lote_Recebimento));
		if (doValida(oid_Unidade))
		    ed.setOid_Unidade(Integer.parseInt(oid_Unidade));
        if (doValida(oid_Conta_Corrente))
            ed.setOid_Conta_Corrente(oid_Conta_Corrente);
		if (doValida(oid_Tipo_Documento))
		    ed.setOid_Tipo_Documento(Integer.parseInt(oid_Tipo_Documento));
		if (doValida(DM_Situacao))
		    ed.setDM_Situacao(DM_Situacao);
        if (doValida(NR_Lote))
            ed.setNR_Lote(Integer.parseInt(NR_Lote));
        if (doValida(VL_Lote))
            ed.setVL_Lote(Double.parseDouble(VL_Lote));
        if (doValida(DT_Emissao))
            ed.setDT_Emissao(DT_Emissao);
        if (doValida(DT_Emissao_Final))
            ed.setDT_Emissao_Final(DT_Emissao_Final);
        if (doValida(DT_Programada))
            ed.setDT_Programada(DT_Programada);
        if (doValida(DT_Programada_Final))
            ed.setDT_Programada_Final(DT_Programada_Final);
        if (doValida(DT_Compensacao))
            ed.setDT_Compensacao(DT_Compensacao);
        if (doValida(DT_Compensacao_Final))
            ed.setDT_Compensacao_Final(DT_Compensacao_Final);

		return new Lote_RecebimentoRN().lista(ed);
	}
    
    public ArrayList lista(HttpServletRequest request) throws Excecoes {
        
        return this.lista(request, true, true, true);
    }

	public Lote_RecebimentoED getByOid(String oid_Lote_Recebimento) throws Excecoes {

        return this.getByOid(oid_Lote_Recebimento, true, true, true);
	}
    public Lote_RecebimentoED getByOid(String oid_Lote_Recebimento, boolean cCorrente, boolean tipoDoc, boolean unidade) throws Excecoes {

        if (!doValida(oid_Lote_Recebimento))
            throw new Mensagens("ID Lote Recebimento não informado!");
        return new Lote_RecebimentoRN().getByRecord(new Lote_RecebimentoED(Integer.parseInt(oid_Lote_Recebimento), cCorrente, tipoDoc, unidade));
    }
    
    public Lote_RecebimentoED getByNR_Lote(String NR_Lote) throws Excecoes {

        return this.getByNR_Lote(NR_Lote, true, true, true);
    }
    public Lote_RecebimentoED getByNR_Lote(String NR_Lote, boolean cCorrente, boolean tipoDoc, boolean unidade) throws Excecoes {

        if (doValida(NR_Lote))
        {
            Lote_RecebimentoED edLote = new Lote_RecebimentoED();
            edLote.setCarregaCCorrente(cCorrente);
            edLote.setCarregaTipoDoc(tipoDoc);
            edLote.setCarregaUnidade(unidade);
            
            edLote.setNR_Lote(Integer.parseInt(NR_Lote));
            return new Lote_RecebimentoRN().getByRecord(edLote);
        } else return new Lote_RecebimentoED();
    }
    
    /** ------------ RELATÓRIOS ---------------- */
    //*** Lotes Recebimentos
    public void relLoteRecebimento(HttpServletRequest request, HttpServletResponse response) throws Excecoes {
      
        String Relatorio = request.getParameter("Relatorio");
        String oid_Lote_Recebimento = request.getParameter("oid_Lote_Recebimento");
        
        if (!doValida(Relatorio))
            throw new Mensagens("Nome do Relatório não informado!");

        RelatorioED ed = new RelatorioED(response, Relatorio);
        if (doValida(oid_Lote_Recebimento))
            ed.setOid_lote_recebimento(Integer.parseInt(oid_Lote_Recebimento));
      
        new Lote_RecebimentoRN().relLoteRecebimento(ed);
    }
}