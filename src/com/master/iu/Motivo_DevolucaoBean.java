package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Motivo_DevolucaoED;
import com.master.rn.Motivo_DevolucaoRN;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;

/**
 * @author Andr� Valadas
 * @serial Motivos de Devolu��o dos Cheques
 * @serialData 24/08/2005
 */
public class Motivo_DevolucaoBean extends JavaUtil implements Serializable {

	public Motivo_DevolucaoED inclui(HttpServletRequest request) throws Excecoes {

		String CD_Motivo_Devolucao = request.getParameter("FT_CD_Motivo_Devolucao");
		String NM_Motivo_Devolucao = request.getParameter("FT_NM_Motivo_Devolucao");

		//*** Valida��es
		if (!doValida(CD_Motivo_Devolucao))
		    throw new Mensagens("C�digo Motivo Devolu��o n�o informado!");
		if (!doValida(NM_Motivo_Devolucao))
		    throw new Mensagens("Nome Motivo Devolu��o n�o informado!");

		Motivo_DevolucaoED ed = new Motivo_DevolucaoED();
		ed.setCD_Motivo_Devolucao(CD_Motivo_Devolucao);
		ed.setNM_Motivo_Devolucao(NM_Motivo_Devolucao);

		//*** Valida se o registro n�o existe para poder incluir
		if (!this.doExiste(ed)) {
		    return new Motivo_DevolucaoRN().inclui(ed);
		} else throw new Mensagens("Esse Motivo Devolu��o j� existe!");
	}

	public void altera(HttpServletRequest request) throws Excecoes {


		String oid_Motivo_Devolucao = request.getParameter("oid_Motivo_Devolucao");
		String CD_Motivo_Devolucao = request.getParameter("FT_CD_Motivo_Devolucao");
		String NM_Motivo_Devolucao = request.getParameter("FT_NM_Motivo_Devolucao");

		//*** Valida��es
		if (!doValida(oid_Motivo_Devolucao))
		    throw new Mensagens("ID Motivo Devolu��o n�o informado!");
		if (!doValida(CD_Motivo_Devolucao))
		    throw new Mensagens("C�digo Motivo Devolu��o n�o informado!");
		if (!doValida(NM_Motivo_Devolucao))
		    throw new Mensagens("Nome Motivo Devolu��o n�o informado!");

		Motivo_DevolucaoED ed = new Motivo_DevolucaoED();
		ed.setOid_Motivo_Devolucao(Integer.parseInt(oid_Motivo_Devolucao));
		ed.setCD_Motivo_Devolucao(CD_Motivo_Devolucao);
		ed.setNM_Motivo_Devolucao(NM_Motivo_Devolucao);
		
		//V�lida se pode ser alterado
		new BancoUtil().doValidaUpdate(ed.getOid_Motivo_Devolucao(), ed.getCD_Motivo_Devolucao(), "Motivos_Devolucoes", "oid_Motivo_Devolucao", "CD_Motivo_Devolucao");
		new Motivo_DevolucaoRN().altera(ed);
	}

	public void deleta(HttpServletRequest request) throws Excecoes {

		String oid_Motivo_Devolucao = request.getParameter("oid_Motivo_Devolucao");
		if (!doValida(oid_Motivo_Devolucao))
		    throw new Mensagens("ID Motivo Devolu��o n�o informado!");
		
		new Motivo_DevolucaoRN().deleta(new Motivo_DevolucaoED(Integer.parseInt(oid_Motivo_Devolucao)));
	}

	public ArrayList lista(HttpServletRequest request) throws Excecoes {

	    String oid_Motivo_Devolucao = request.getParameter("oid_Motivo_Devolucao");
	    String CD_Motivo_Devolucao = request.getParameter("FT_CD_Motivo_Devolucao");
		String NM_Motivo_Devolucao = request.getParameter("FT_NM_Motivo_Devolucao");

		Motivo_DevolucaoED ed = new Motivo_DevolucaoED();
		if (doValida(oid_Motivo_Devolucao))
		    ed.setOid_Motivo_Devolucao(Integer.parseInt(oid_Motivo_Devolucao));
		if (doValida(CD_Motivo_Devolucao))
		    ed.setCD_Motivo_Devolucao(CD_Motivo_Devolucao);
		if (doValida(NM_Motivo_Devolucao))
		    ed.setNM_Motivo_Devolucao(NM_Motivo_Devolucao);
		
		return new Motivo_DevolucaoRN().lista(ed);
	}

	public Motivo_DevolucaoED getByOid(String oid_Motivo_Devolucao) throws Excecoes {

		if (!doValida(oid_Motivo_Devolucao))
		    throw new Mensagens("ID Motivo Devolu��o n�o informado!");

		return new Motivo_DevolucaoRN().getByRecord(new Motivo_DevolucaoED(Integer.parseInt(oid_Motivo_Devolucao)));
	}
	
	public Motivo_DevolucaoED getByCDMotivo_Devolucao(String CD_Motivo_Devolucao) throws Excecoes {
		
		//*** Valida��es
		if (!doValida(CD_Motivo_Devolucao))
		    throw new Mensagens("C�digo Motivo Devolu��o n�o informado!");
		Motivo_DevolucaoED ed = new Motivo_DevolucaoED();
		ed.setCD_Motivo_Devolucao(CD_Motivo_Devolucao);
		
		return new Motivo_DevolucaoRN().getByRecord(ed);
	}

    //*** Verifica se registro j� existe!
    private boolean doExiste(Motivo_DevolucaoED ed) throws Excecoes {
        
	    return new BancoUtil().doExiste("Motivos_Devolucoes", 
            	            			" CD_Motivo_Devolucao = '" + ed.getCD_Motivo_Devolucao() +"'" +
            	            			" OR NM_Motivo_Devolucao = '"+ed.getNM_Motivo_Devolucao()+"'");
    }
}