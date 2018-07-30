package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Tipo_Tabela_VendaED;
import com.master.rn.Tipo_Tabela_VendaRN;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.Mensagens;

/**
 * @author André Valadas
 * @serial Tipos de Tabelas de Venda
 * @serialData 11/04/2005
 */
public class Tipo_Tabela_VendaBean extends BancoUtil implements Serializable {

	public Tipo_Tabela_VendaED inclui(HttpServletRequest request) throws Excecoes {

		String CD_Tipo_Tabela_Venda = request.getParameter("FT_CD_Tipo_Tabela_Venda");
		String NM_Tipo_Tabela_Venda = request.getParameter("FT_NM_Tipo_Tabela_Venda");

		//*** Validações
		if (!doValida(CD_Tipo_Tabela_Venda))
		    throw new Mensagens("Código Tipo Tabela de Venda não informado!");
		if (!doValida(NM_Tipo_Tabela_Venda))
		    throw new Mensagens("Nome Tipo Tabela de Venda não informado!");

		Tipo_Tabela_VendaED ed = new Tipo_Tabela_VendaED();
		ed.setCD_Tipo_Tabela_Venda(CD_Tipo_Tabela_Venda);
		ed.setNM_Tipo_Tabela_Venda(NM_Tipo_Tabela_Venda);

		//*** Valida se o registro não existe para poder incluir
		if (!this.doExiste(ed)) {
		    return new Tipo_Tabela_VendaRN().inclui(ed);
		} else throw new Mensagens("Esse Tipo Tabela de Venda já existe!");
	}

	public void altera(HttpServletRequest request) throws Excecoes {


		String oid_Tipo_Tabela_Venda = request.getParameter("oid_Tipo_Tabela_Venda");
		String CD_Tipo_Tabela_Venda = request.getParameter("FT_CD_Tipo_Tabela_Venda");
		String NM_Tipo_Tabela_Venda = request.getParameter("FT_NM_Tipo_Tabela_Venda");

		//*** Validações
		if (!doValida(oid_Tipo_Tabela_Venda))
		    throw new Mensagens("ID Tipo Tabela de Venda não informado!");
		if (!doValida(CD_Tipo_Tabela_Venda))
		    throw new Mensagens("Código Tipo Tabela de Venda não informado!");
		if (!doValida(NM_Tipo_Tabela_Venda))
		    throw new Mensagens("Nome Tipo Tabela de Venda não informado!");

		Tipo_Tabela_VendaED ed = new Tipo_Tabela_VendaED();
		ed.setOid_Tipo_Tabela_Venda(Integer.parseInt(oid_Tipo_Tabela_Venda));
		ed.setCD_Tipo_Tabela_Venda(CD_Tipo_Tabela_Venda);
		ed.setNM_Tipo_Tabela_Venda(NM_Tipo_Tabela_Venda);
		
		//Válida se pode ser alterado
		doValidaUpdate(ed.getOid_Tipo_Tabela_Venda(), ed.getCD_Tipo_Tabela_Venda(), "Tipos_Tabelas_Vendas", "oid_Tipo_Tabela_Venda", "CD_Tipo_Tabela_Venda");
		new Tipo_Tabela_VendaRN().altera(ed);
	}

	public void deleta(HttpServletRequest request) throws Excecoes {

		String oid_Tipo_Tabela_Venda = request.getParameter("oid_Tipo_Tabela_Venda");
		if (!doValida(oid_Tipo_Tabela_Venda))
		    throw new Mensagens("ID Tipo Tabela de Venda não informado!");
		
		new Tipo_Tabela_VendaRN().deleta(new Tipo_Tabela_VendaED(Integer.parseInt(oid_Tipo_Tabela_Venda)));
	}

	public ArrayList lista(HttpServletRequest request) throws Excecoes {

	    String oid_Tipo_Tabela_Venda = request.getParameter("oid_Tipo_Tabela_Venda");
	    String CD_Tipo_Tabela_Venda = request.getParameter("FT_CD_Tipo_Tabela_Venda");
		String NM_Tipo_Tabela_Venda = request.getParameter("FT_NM_Tipo_Tabela_Venda");

		Tipo_Tabela_VendaED ed = new Tipo_Tabela_VendaED();
		if (doValida(oid_Tipo_Tabela_Venda))
		    ed.setOid_Tipo_Tabela_Venda(Integer.parseInt(oid_Tipo_Tabela_Venda));
		if (doValida(CD_Tipo_Tabela_Venda))
		    ed.setCD_Tipo_Tabela_Venda(CD_Tipo_Tabela_Venda);
		if (doValida(NM_Tipo_Tabela_Venda))
		    ed.setNM_Tipo_Tabela_Venda(NM_Tipo_Tabela_Venda);
		
		return new Tipo_Tabela_VendaRN().lista(ed);
	}

	public Tipo_Tabela_VendaED getByOid(String oid_Tipo_Tabela_Venda) throws Excecoes {

		if (!doValida(oid_Tipo_Tabela_Venda))
		    throw new Mensagens("ID Tipo Tabela de Venda não informado!");

		return new Tipo_Tabela_VendaRN().getByRecord(new Tipo_Tabela_VendaED(Integer.parseInt(oid_Tipo_Tabela_Venda)));
	}
	
	public Tipo_Tabela_VendaED getByCDTipo_Tabela_Venda(String CD_Tipo_Tabela_Venda) throws Excecoes {
		
		//*** Validações
		if (!doValida(CD_Tipo_Tabela_Venda))
		    throw new Mensagens("Código Tipo Tabela de Venda não informado!");
		Tipo_Tabela_VendaED ed = new Tipo_Tabela_VendaED();
		ed.setCD_Tipo_Tabela_Venda(CD_Tipo_Tabela_Venda);
		
		return new Tipo_Tabela_VendaRN().getByRecord(ed);
	}

    //*** Verifica se registro já existe!
    private boolean doExiste(Tipo_Tabela_VendaED ed) throws Excecoes {
        
	    return super.doExiste("Tipos_Tabelas_Vendas", 
	            			  " CD_Tipo_Tabela_Venda = '" + ed.getCD_Tipo_Tabela_Venda() +"'" +
	            			  " OR NM_Tipo_Tabela_Venda = '"+ed.getNM_Tipo_Tabela_Venda()+"'");
    }
}