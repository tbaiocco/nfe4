package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import com.master.ed.Cheque_ClienteED;
import com.master.ed.Lote_ChequeED;
import com.master.rn.Cheque_ClienteRN;
import com.master.rn.Lote_ChequeRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;

/**
 * @author André Valadas
 * @serial Lotes Cheques
 * @serialData 16/08/2005
 */
public class Lote_ChequeBean extends JavaUtil implements Serializable {

	public Lote_ChequeED inclui(HttpServletRequest request) throws Excecoes {

	    String oid_Lote_Recebimento = request.getParameter("oid_Lote_Recebimento");
	    String oid_Cheque_Cliente = request.getParameter("oid_Cheque_Cliente");
        String DM_Situacao = request.getParameter("FT_DM_Situacao");
	    
		//*** Validações 
		if (!doValida(oid_Lote_Recebimento))
		    throw new Excecoes("Lote Recebimento não informado!");
		if (!doValida(oid_Cheque_Cliente))
		    throw new Excecoes("Cheque Cliente não informado!");
        
		Lote_ChequeED ed = new Lote_ChequeED();
		ed.setOid_Lote_Recebimento(Integer.parseInt(oid_Lote_Recebimento));
		ed.setOid_Cheque_Cliente(Integer.parseInt(oid_Cheque_Cliente));
        if (doValida(DM_Situacao))
            ed.setDM_Situacao(DM_Situacao);
		
		return new Lote_ChequeRN().inclui(ed);
	}

	public void altera(HttpServletRequest request) throws Excecoes {

	    String oid_Lote_Cheque = request.getParameter("oid_Lote_Cheque");
	    String DM_Situacao = request.getParameter("FT_DM_Situacao");

	    //*** Validações 
	    if (!doValida(oid_Lote_Cheque))
		    throw new Excecoes("ID Lote Cheque não informado!");
	    
		Lote_ChequeED ed = new Lote_ChequeED(Integer.parseInt(oid_Lote_Cheque));
        if (doValida(DM_Situacao))
            ed.setDM_Situacao(DM_Situacao);
		new Lote_ChequeRN().altera(ed);
	}

    public void deleta(HttpServletRequest request) throws Excecoes {

        String oid_Lote_Cheque = request.getParameter("oid_Lote_Cheque");
        if (!doValida(oid_Lote_Cheque))
            throw new Mensagens("ID Lote Cheque não informado!");
        new Lote_ChequeRN().deleta(new Lote_ChequeED(Integer.parseInt(oid_Lote_Cheque)));
    }
    
    public void estornarCheque(String oid_Lote_Cheque) throws Excecoes {

        if (!doValida(oid_Lote_Cheque))
            throw new Mensagens("ID Lote Cheque não informado!");
        Lote_ChequeED ed = new Lote_ChequeED(Integer.parseInt(oid_Lote_Cheque));
        
        new Lote_ChequeRN().deleta(ed);
    }
    
    public static void main(String[] args) throws Exception {
        Cheque_ClienteED edCheque = new Cheque_ClienteED();
        edCheque.setOid_Lote_Recebimento(1);
        ArrayList lista = new Cheque_ClienteRN().lista(edCheque);
        for (int i=0; i<lista.size(); i++)
        {
            Cheque_ClienteED edChq = (Cheque_ClienteED) lista.get(i);
            if (!"D".equals(edChq.getDM_Situacao()))
            {
                Lote_ChequeED ed = new Lote_ChequeED();
                ed.setOid_Lote_Recebimento(edChq.getOid_Lote_Recebimento());
                ed.setOid_Cheque_Cliente(edChq.getOid_Cheque_Cliente());
                ed.edCheque.setOid_Motivo_Devolucao(2);
                ed.edCheque.setDM_Apresentacao("N");
                new Lote_ChequeRN().devolucaoCheque(ed);
                // System.out.println(i);
            }
        }
        System.exit(0);
    }
    
    public void devolucaoCheque(HttpServletRequest request) throws Exception {

        String oid_Lote_Recebimento = request.getParameter("oid_Lote_Recebimento");
        String oid_Cheque_Cliente = request.getParameter("oid_Cheque_Cliente");
        String oid_Motivo_Devolucao = request.getParameter("oid_Motivo_Devolucao");
        String DM_Apresentacao = request.getParameter("FT_DM_Apresentacao");
        
        if (!doValida(oid_Lote_Recebimento))
            throw new Mensagens("ID Lote Recebimento não informado!");
        if (!doValida(oid_Cheque_Cliente))
            throw new Mensagens("ID Cheque Cliente não informado!");
        if (!doValida(oid_Motivo_Devolucao))
            throw new Mensagens("ID Motivo Devolução não informado!");
        if (!doValida(DM_Apresentacao))
            throw new Mensagens("Permissão para Reapresentação não informada!");
        Lote_ChequeED ed = new Lote_ChequeED();
        ed.setOid_Lote_Recebimento(Integer.parseInt(oid_Lote_Recebimento));
        ed.setOid_Cheque_Cliente(Integer.parseInt(oid_Cheque_Cliente));
        ed.edCheque.setOid_Motivo_Devolucao(Integer.parseInt(oid_Motivo_Devolucao));
        ed.edCheque.setDM_Apresentacao(DM_Apresentacao);
        
        new Lote_ChequeRN().devolucaoCheque(ed);
    }
    
    /**
     * LOTES RECEBIMENTOS
     */
    public void addChequesEntregas(String oid_Lote_Recebimento) throws Excecoes {

        if (!doValida(oid_Lote_Recebimento))
            throw new Mensagens("ID Lote Recebimento não informado!");
        Lote_ChequeED ed = new Lote_ChequeED();
        ed.setOid_Lote_Recebimento(Integer.parseInt(oid_Lote_Recebimento));
        new Lote_ChequeRN().addChequesEntregas(ed);
    }
    public void addChequesByData(String oid_Lote_Recebimento) throws Excecoes {

        if (!doValida(oid_Lote_Recebimento))
            throw new Mensagens("ID Lote Recebimento não informado!");
        Lote_ChequeED ed = new Lote_ChequeED();
        ed.setOid_Lote_Recebimento(Integer.parseInt(oid_Lote_Recebimento));
        new Lote_ChequeRN().addChequesByData(ed);
    }

	public ArrayList lista(HttpServletRequest request) throws Excecoes {

	    return this.lista(request, true);
	}
    
    public ArrayList lista(HttpServletRequest request, boolean motivo_dev) throws Excecoes {

        String oid_Lote_Cheque = request.getParameter("oid_Lote_Cheque");
        String oid_Lote_Recebimento = request.getParameter("oid_Lote_Recebimento");
        String oid_Cheque_Cliente = request.getParameter("oid_Cheque_Cliente");
        String DM_Situacao = request.getParameter("FT_DM_Situacao");
        
        Lote_ChequeED ed = new Lote_ChequeED(motivo_dev);
        //*** Validações
        if (doValida(oid_Lote_Cheque))
            ed.setOid_Lote_Cheque(Integer.parseInt(oid_Lote_Cheque));
        if (doValida(oid_Lote_Recebimento))
            ed.setOid_Lote_Recebimento(Integer.parseInt(oid_Lote_Recebimento));
        if (doValida(oid_Cheque_Cliente))
            ed.setOid_Cheque_Cliente(Integer.parseInt(oid_Cheque_Cliente));
        if (doValida(DM_Situacao))
            ed.setDM_Situacao(DM_Situacao);

        return new Lote_ChequeRN().lista(ed);
    }

	public Lote_ChequeED getByOid(String oid_Lote_Cheque) throws Excecoes {

		return this.getByOid(oid_Lote_Cheque, true);
	}
    public Lote_ChequeED getByOid(String oid_Lote_Cheque, boolean motivo_dev) throws Excecoes {

        if (!doValida(oid_Lote_Cheque))
            throw new Mensagens("ID Lote Cheque não informado!");
        return new Lote_ChequeRN().getByRecord(new Lote_ChequeED(Integer.parseInt(oid_Lote_Cheque), motivo_dev));
    }
    
    public Lote_ChequeED getByRecord(String oid_Lote_Recebimento, String oid_Cheque_Cliente) throws Excecoes {

        return this.getByRecord(oid_Lote_Recebimento, oid_Cheque_Cliente, true);
    }
    public Lote_ChequeED getByRecord(String oid_Lote_Recebimento, String oid_Cheque_Cliente, boolean motivo_dev) throws Excecoes {

        if (!doValida(oid_Lote_Recebimento))
            throw new Mensagens("ID Lote Recebimento não informado!");
        if (!doValida(oid_Cheque_Cliente))
            throw new Mensagens("ID Cheque Cliente não informado!");
        return new Lote_ChequeRN().getByRecord(new Lote_ChequeED(null, Integer.parseInt(oid_Cheque_Cliente), Integer.parseInt(oid_Lote_Recebimento), motivo_dev));
    }
}