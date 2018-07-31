package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import com.master.ed.Rota_VendaED;
import com.master.rn.Rota_VendaRN;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;

/*
 * Created on 01/10/2004
 */

/**
 * @author Andre Valadas
 */
public class Rota_VendaBean extends JavaUtil implements Serializable {
    
	public Rota_VendaED inclui(HttpServletRequest request) throws Excecoes {

		String oid_Vendedor = request.getParameter("oid_Vendedor");
		String oid_Cliente = request.getParameter("oid_Cliente");
		String NR_Sequencia = request.getParameter("FT_NR_Sequencia");
		String DM_Dia = request.getParameter("FT_DM_Dia");
		String DM_Situacao = request.getParameter("FT_DM_Situacao");
		
		//*** Valida��es
		if (!doValida(oid_Vendedor))
            throw new Excecoes("Vendedor n�o informado!");
        if (!doValida(oid_Cliente))
            throw new Excecoes("Cliente n�o informado!");
        if (!doValida(NR_Sequencia))
            throw new Excecoes("NR Sequencia n�o informada!");
        if (!doValida(DM_Dia))
            throw new Excecoes("DIA n�o informado!");
        if (!doValida(DM_Situacao))
            throw new Excecoes("Situa��o n�o informada!");

        Rota_VendaED ed = new Rota_VendaED(oid_Vendedor, oid_Cliente);
        ed.setNR_Sequencia(Integer.parseInt(NR_Sequencia));
        ed.setDM_Dia(DM_Dia);
        ed.setDM_Situacao(DM_Situacao);
		return new Rota_VendaRN().inclui(ed);
	}

	public Rota_VendaED altera(HttpServletRequest request) throws Excecoes {

		String oid_Rota_Venda = request.getParameter("oid_Rota_Venda");
		String oid_Vendedor = request.getParameter("oid_Vendedor");
        String oid_Cliente = request.getParameter("oid_Cliente");
		//*** Referencia Original
        String NR_Sequencia_Orig = request.getParameter("FT_NR_Sequencia_Orig");
        String DM_Dia_Orig = request.getParameter("FT_DM_Dia_Orig");
        //*** REFERENCIA COM POSSIVEL ALTERA��O
        String NR_Sequencia = request.getParameter("FT_NR_Sequencia");
        String DM_Dia = request.getParameter("FT_DM_Dia");
		String DM_Situacao = request.getParameter("FT_DM_Situacao");
        
		//*** Valida��es
        if (!doValida(oid_Rota_Venda))
            throw new Excecoes("Rota Venda n�o informada!");
        if (!doValida(oid_Vendedor))
            throw new Excecoes("Vendedor n�o informado!");
        if (!doValida(oid_Cliente))
            throw new Excecoes("Cliente n�o informado!");
        if (!doValida(NR_Sequencia_Orig))
            throw new Excecoes("NR Sequencia Origem n�o informada!");
        if (!doValida(DM_Dia_Orig))
            throw new Excecoes("DIA Origem n�o informado!");
        if (!doValida(NR_Sequencia))
            throw new Excecoes("NR Sequencia n�o informada!");
        if (!doValida(DM_Dia))
            throw new Excecoes("DIA n�o informado!");
        if (!doValida(DM_Situacao))
            throw new Excecoes("Situa��o n�o informada!");        
        
        //*** Se alterado Dia da Semana, exclui da Origem e inclui na nova ROTA
        if (DM_Dia.equals(DM_Dia_Orig))
        {
            Rota_VendaED ed = new Rota_VendaED();
            ed.setOid_Rota_Venda(Integer.parseInt(oid_Rota_Venda));
            ed.setOid_Vendedor(oid_Vendedor);   
            ed.setOid_Cliente(oid_Cliente);
            ed.setNR_Sequencia(Integer.parseInt(NR_Sequencia));                 
            ed.setDM_Dia(DM_Dia);
            ed.setDM_Situacao(DM_Situacao);
            
            return new Rota_VendaRN().altera(ed);
        } else {
            this.deleta(request);
            return this.inclui(request);
        }
	}

	public void deleta(HttpServletRequest request) throws Excecoes {

		String oid_Rota_Venda = request.getParameter("oid_Rota_Venda");
		String oid_Vendedor = request.getParameter("oid_Vendedor");
		//*** Referencia Original
        String NR_Sequencia = request.getParameter("FT_NR_Sequencia_Orig");
        String DM_Dia = request.getParameter("FT_DM_Dia_Orig");

		//*** Valida��es
        if (!doValida(oid_Rota_Venda))
            throw new Excecoes("oid_Rota_Venda n�o informado!");
        if (!doValida(oid_Vendedor))
            throw new Excecoes("Vendedor n�o informado!");
        if (!doValida(DM_Dia))
            throw new Excecoes("DIA n�o informado!");
        if (!doValida(NR_Sequencia))
            throw new Excecoes("Sequencia n�o informada!");
        
        Rota_VendaED ed = new Rota_VendaED();
        ed.setOid_Rota_Venda(Integer.parseInt(oid_Rota_Venda));
        ed.setOid_Vendedor(oid_Vendedor);
        ed.setDM_Dia(DM_Dia);
        ed.setNR_Sequencia(Integer.parseInt(NR_Sequencia));
        
        new Rota_VendaRN().deleta(ed);
	}
    
    public void deletaByDia(HttpServletRequest request) throws Excecoes {

        String oid_Vendedor = request.getParameter("oid_Vendedor");
        String DM_Dia = request.getParameter("FT_DM_Dia");

        //*** Valida��es
        if (!doValida(oid_Vendedor))
            throw new Excecoes("Vendedor n�o informado!");
        if (!doValida(DM_Dia))
            throw new Excecoes("DIA n�o informado!");
        
        Rota_VendaED ed = new Rota_VendaED();
        ed.setOid_Vendedor(oid_Vendedor);
        ed.setDM_Dia(DM_Dia);
        new Rota_VendaRN().deletaByDia(ed);
    }
    
    /** TRANSFER�NCIA DE CLIENTES/ROTAS **/
    public void tranfereByVendedor(HttpServletRequest request) throws Exception {

        String oid_Vendedor = request.getParameter("oid_Vendedor");
        String oid_Vendedor2 = request.getParameter("oid_Vendedor2");
        //*** Se informado DIA transfere Todas as Rotas daquele dia, caso contrario transfere tudo
        if (!doValida(oid_Vendedor))
            throw new Excecoes("Vendedor n�o informado!");
        if (!doValida(oid_Vendedor2))
            throw new Excecoes("Vendedor de Destino n�o informado!");
        
        ArrayList rotas = this.Lista_Rota_Venda(request);
        new Rota_VendaRN().tranfereRotasVendas(rotas, oid_Vendedor2);
    }
    public void tranfereByClientes(HttpServletRequest request) throws Exception {

        //*** Vendedor de Destino
        String oid_Vendedor2 = request.getParameter("oid_Vendedor2");
        if (!doValida(oid_Vendedor2))
            throw new Excecoes("Vendedor n�o informado!");
        
        ArrayList lista = lista = (ArrayList) request.getSession(true).getAttribute("listaRotas"); 
        if (lista.size() < 1)
            throw new Mensagens("Lista de Rotas vazia! Execute novamente a consulta!");
        
        ArrayList rotas = new ArrayList();
        //*** Carrega Rotas da Tela
        for (int i=0; i<lista.size(); i++)
        {
            Rota_VendaED edRota = (Rota_VendaED) lista.get(i);
            if ("on".equals(request.getParameter("transfere"+i)))
                rotas.add(edRota);
        }
        new Rota_VendaRN().tranfereRotasVendas(rotas, oid_Vendedor2);
    }
    
	public ArrayList Lista_Rota_Venda(HttpServletRequest request) throws Excecoes {

		String oid_Vendedor = request.getParameter("oid_Vendedor");
        String oid_Cliente = request.getParameter("oid_Cliente");
		String DM_Dia = request.getParameter("FT_DM_Dia");
        String DM_Situacao = request.getParameter("FT_DM_Situacao");
		
		//*** Valida��es
        if (!doValida(oid_Vendedor))
            throw new Excecoes("Vendedor n�o informado!");
        
        Rota_VendaED ed = new Rota_VendaED();
        ed.setOid_Vendedor(oid_Vendedor);
        ed.setDM_Dia(DM_Dia);
        ed.setDM_Situacao(DM_Situacao);
        ed.setOid_Cliente(oid_Cliente);
		return new Rota_VendaRN().lista(ed);
	}
	
	public Rota_VendaED getByOidRota_Venda(int oid_Rota_Venda) throws Excecoes {

        //*** Valida��es
        if (oid_Rota_Venda < 1)
            throw new Mensagens("Rota Venda n�o informada!");
		Rota_VendaED ed = new Rota_VendaED();
		ed.setOid_Rota_Venda(oid_Rota_Venda);
		return new Rota_VendaRN().getByRecord(ed);
	}
    
    public Rota_VendaED getByRecord(String oid_Vendedor, String cdRota_Venda) throws Excecoes {

        Rota_VendaED ed = new Rota_VendaED();
        if (doValida(oid_Vendedor) && doValida(cdRota_Venda))
        {
            ed.setOid_Vendedor(oid_Vendedor);
            ed.setCD_Rota_Venda(cdRota_Venda);
            return new Rota_VendaRN().getByRecord(ed);
        } else return ed;
    }

	
    //*** Verifica se registro j� existe!
    public boolean doExiste(HttpServletRequest request) throws Excecoes {
        
        String oid_Vendedor = request.getParameter("oid_Vendedor");
		String oid_Cliente = request.getParameter("oid_Cliente");
		String DM_Dia = request.getParameter("FT_DM_Dia");
		
		//*** Valida��es
        if (!doValida(oid_Vendedor))
            throw new Excecoes("Vendedor n�o informado!");
        if (!doValida(oid_Cliente))
            throw new Excecoes("Cliente n�o informado!");
        if (!doValida(DM_Dia))
            throw new Excecoes("DIA n�o informado!");
        
        String strFrom  = "rotas_vendas";
        String strWhere = " oid_Vendedor = '" +oid_Vendedor +"'" +
		      		      " and oid_Cliente = '" +oid_Cliente+"'" +
		    		      " and DM_Dia = '" +DM_Dia+"'";		    
        return new BancoUtil().doExiste(strFrom, strWhere);
    }
}