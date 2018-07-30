package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import com.master.ed.Mensagem_VendedorED;
import com.master.rn.Mensagem_VendedorRN;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.Mensagens;

/**
 * @author André Valadas
 * @serial Mensagens Vendedores
 * @serialData 27/02/2006
 */
public class Mensagem_VendedorBean extends BancoUtil implements Serializable {

	public Mensagem_VendedorED inclui(HttpServletRequest request) throws Exception {

		String oid_Vendedor = request.getParameter("oid_Vendedor");
		String DT_Mensagem = request.getParameter("FT_DT_Mensagem");
        String HR_Mensagem = request.getParameter("FT_HR_Mensagem");
        String TX_Assunto = request.getParameter("FT_TX_Assunto");
        String TX_Mensagem = request.getParameter("FT_TX_Mensagem");
        
		//*** Validações
		if (!doValida(DT_Mensagem))
		    throw new Mensagens("Data não informada!");
        if (!doValida(HR_Mensagem))
            throw new Mensagens("Hora não informada!");
        if (!doValida(TX_Assunto))
            throw new Mensagens("Assunto não informado!");
        if (!doValida(TX_Mensagem))
            throw new Mensagens("Mensagem não informada!");

		Mensagem_VendedorED ed = new Mensagem_VendedorED();
        if (doValida(oid_Vendedor))
            ed.setOid_Vendedor(oid_Vendedor);
		ed.setDT_Mensagem(DT_Mensagem);
		ed.setHR_Mensagem(HR_Mensagem);
        ed.setTX_Assunto(TX_Assunto);
        ed.setTX_Mensagem(TX_Mensagem);
        return new Mensagem_VendedorRN().inclui(ed);
	}
    
    public void altera(HttpServletRequest request) throws Exception {

        ArrayList lista = (ArrayList) request.getSession(true).getAttribute("listaMsgs"); 
        if (lista.size() < 1)
            throw new Mensagens("Lista de Mensagens vazia! Execute novamente a consulta!");
        ArrayList mensagens = new ArrayList();
        //*** Carrega Pesagem da Tela
        for (int i=0; i<lista.size(); i++)
        {
            Mensagem_VendedorED edMsg = (Mensagem_VendedorED) lista.get(i);
            String strMsg = request.getParameter("FT_TX_Mensagem"+i); 
            if (doValida(strMsg) && !strMsg.equals(edMsg.getTX_Mensagem()))
            {
                edMsg.setTX_Mensagem(request.getParameter("FT_TX_Mensagem"+i));
                mensagens.add(edMsg);
            }
        }
        new Mensagem_VendedorRN().altera(mensagens);
    }

	public void deleta(HttpServletRequest request) throws Excecoes {

		String oid_Mensagem_Vendedor = request.getParameter("oid_Mensagem_Vendedor");
        String DT_Mensagem = request.getParameter("FT_DT_Mensagem");
		if (!doValida(oid_Mensagem_Vendedor) && !doValida(DT_Mensagem))
		    throw new Mensagens("ID Parametros não informados!");
		
        Mensagem_VendedorED ed = new Mensagem_VendedorED();
        if (doValida(oid_Mensagem_Vendedor))
            ed.setOid_Mensagem_Vendedor(Integer.parseInt(oid_Mensagem_Vendedor));
        if (doValida(DT_Mensagem))
            ed.setDT_Mensagem(DT_Mensagem);
		new Mensagem_VendedorRN().deleta(ed);
	}

	public ArrayList lista(HttpServletRequest request) throws Excecoes {

	    String oid_Mensagem_Vendedor = request.getParameter("oid_Mensagem_Vendedor");
	    String oid_Vendedor = request.getParameter("oid_Vendedor");
        String DT_Mensagem = request.getParameter("FT_DT_Mensagem");

		Mensagem_VendedorED ed = new Mensagem_VendedorED();
		if (doValida(oid_Mensagem_Vendedor))
		    ed.setOid_Mensagem_Vendedor(Integer.parseInt(oid_Mensagem_Vendedor));
		if (doValida(oid_Vendedor))
		    ed.setOid_Vendedor(oid_Vendedor);
        if (doValida(DT_Mensagem))
            ed.setDT_Mensagem(DT_Mensagem);
		
		return new Mensagem_VendedorRN().lista(ed);
	}

	public Mensagem_VendedorED getByOid(String oid_Mensagem_Vendedor) throws Excecoes {

		if (!doValida(oid_Mensagem_Vendedor))
		    throw new Mensagens("ID Tipo Tabela de Venda não informado!");

		return new Mensagem_VendedorRN().getByRecord(new Mensagem_VendedorED(Integer.parseInt(oid_Mensagem_Vendedor)));
	}
}