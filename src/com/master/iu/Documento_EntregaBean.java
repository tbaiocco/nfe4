package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Documento_EntregaED;
import com.master.rn.Documento_EntregaRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;

/**
 * @author André Valadas
 * @serial Documentos Entregas
 * @serialData 31/03/2005
 */
public class Documento_EntregaBean extends JavaUtil implements Serializable {

	public Documento_EntregaED inclui(HttpServletRequest request) throws Excecoes {

	    String oid_Entrega = request.getParameter("oid_Entrega");
	    String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
	    
		//*** Validações
		if (!doValida(oid_Entrega))
		    throw new Excecoes("Entrega não informada!");
		if (!doValida(oid_Nota_Fiscal))
		    throw new Excecoes("Nota Fiscal não informada!");
		
		Documento_EntregaED ed = new Documento_EntregaED();
		ed.setOid_Entrega(Integer.parseInt(oid_Entrega));
		ed.setOid_Nota_Fiscal(oid_Nota_Fiscal);
		ed.setDM_Situacao("N");
		
		return new Documento_EntregaRN().inclui(ed);
	}

	public void altera(HttpServletRequest request) throws Excecoes {

	    String oid_Documento_Entrega = request.getParameter("oid_Documento_Entrega");
        String DM_Forma_Recebimento = request.getParameter("FT_DM_Forma_Recebimento");
	    String DM_Situacao = request.getParameter("FT_DM_Situacao");
	    String TX_Observacao = request.getParameter("FT_TX_Observacao");
	    
	    if (!doValida(oid_Documento_Entrega))
		    throw new Excecoes("Documento Entrega não informado!");
	    if (!doValida(DM_Situacao))
		    throw new Excecoes("Situação não informada!");
	    
		Documento_EntregaED ed = new Documento_EntregaED(Integer.parseInt(oid_Documento_Entrega));
		ed.setDM_Situacao(DM_Situacao);
        if (doValida(DM_Forma_Recebimento))
            ed.setDM_Forma_Recebimento(DM_Forma_Recebimento);
		if (doValida(TX_Observacao))
		    ed.setTX_Observacao(TX_Observacao);
		
		new Documento_EntregaRN().altera(ed);
	}

	public void deleta(HttpServletRequest request) throws Excecoes {

		String oid_Documento_Entrega = request.getParameter("oid_Documento_Entrega");
		if (!doValida(oid_Documento_Entrega))
		    throw new Excecoes("Documento Entrega não informada!");
		new Documento_EntregaRN().deleta(new Documento_EntregaED(Integer.parseInt(oid_Documento_Entrega)));
	}

	public ArrayList lista(HttpServletRequest request) throws Excecoes {

	    String oid_Documento_Entrega = request.getParameter("oid_Documento_Entrega");
	    String oid_Entrega = request.getParameter("oid_Entrega");
	    String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
	    String DM_Situacao = request.getParameter("FT_DM_Situacao");
	    String DT_Entrega = request.getParameter("FT_DT_Entrega");
	    String DT_Entrega_Final = request.getParameter("FT_DT_Entrega_Final");
	    String DT_Acerto = request.getParameter("FT_DT_Acerto");
	    String DT_Acerto_Final = request.getParameter("FT_DT_Acerto_Final");
	    
	    Documento_EntregaED ed = new Documento_EntregaED();
		//*** Validações
	    if (doValida(oid_Documento_Entrega))
		    ed.setOid_Documento_Entrega(Integer.parseInt(oid_Documento_Entrega));
		if (doValida(oid_Entrega))
		    ed.setOid_Entrega(Integer.parseInt(oid_Entrega));
		if (doValida(oid_Nota_Fiscal))
		    ed.setOid_Nota_Fiscal(oid_Nota_Fiscal);
		if (doValida(DM_Situacao))
		    ed.setDM_Situacao(DM_Situacao);
		if (doValida(DT_Entrega))
		    ed.edEntrega.setDT_Entrega(DT_Entrega);
		if (doValida(DT_Entrega_Final))
		    ed.edEntrega.setDT_Entrega_Final(DT_Entrega_Final);
		if (doValida(DT_Acerto))
		    ed.edEntrega.setDT_Acerto(DT_Acerto);
		if (doValida(DT_Acerto_Final))
		    ed.edEntrega.setDT_Acerto_Final(DT_Acerto_Final);

		return new Documento_EntregaRN().lista(ed);
	}

	public Documento_EntregaED getByOid(String oid_Documento_Entrega) throws Excecoes {

	    if (!doValida(oid_Documento_Entrega))
		    throw new Excecoes("Documento Entrega não informada!");
		return new Documento_EntregaRN().getByRecord(new Documento_EntregaED(Integer.parseInt(oid_Documento_Entrega)));
	}
	
	public Documento_EntregaED getByNRDocumento(String oid_Entrega, String NR_Documento) throws Excecoes {

	    if (!doValida(oid_Entrega))
		    throw new Mensagens("Entrega não informada!");
	    if (!doValida(NR_Documento))
		    throw new Mensagens("Número do Documento não informado!");
	    Documento_EntregaED edDoc = new Documento_EntregaED();
	    edDoc.setOid_Entrega(Integer.parseInt(oid_Entrega));
	    edDoc.edNota.setNr_nota_fiscal(Long.parseLong(NR_Documento));
	    
		return new Documento_EntregaRN().getByRecord(edDoc);
	}
}