package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Cliente_DespachanteED;
import com.master.rn.Cliente_DespachanteRN;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

/**
 * @author André Valadas
 */
public class Cliente_DespachanteBean extends JavaUtil implements Serializable {

	public Cliente_DespachanteED inclui(HttpServletRequest request) throws Excecoes {

		String oid_Cliente = request.getParameter("oid_Cliente");
		String oid_Despachante = request.getParameter("oid_Despachante");
		String DM_Situacao = request.getParameter("FT_DM_Situacao");
		String oid_Aduana = request.getParameter("oid_Aduana");

		//*** Validações
		if (!doValida(oid_Cliente) || !doValida(oid_Despachante) || !doValida(DM_Situacao))
		    throw new Excecoes("Parâmetros incorretos!");

		Cliente_DespachanteED ed = new Cliente_DespachanteED();
		
		ed.setOid_Cliente(oid_Cliente);
		ed.setOid_Despachante(Integer.parseInt(oid_Despachante));
		ed.setDM_Situacao(DM_Situacao);
		if(doValida(oid_Aduana)){
		    ed.setOid_Aduana(Integer.parseInt(oid_Aduana));
		} else {
		    throw new Excecoes("Aduana não Preenchida!");
		}

		//*** Valida se o registro não existe para poder incluir
		if (this.doExiste(ed))
		    throw new Excecoes("O Despachante já está cadastrado para esse Cliente!");
		
		return new Cliente_DespachanteRN().inclui(ed);
	}

	public void altera(HttpServletRequest request) throws Excecoes {

		String oid_Cliente_Despachante = request.getParameter("oid_Cliente_Despachante");
		String oid_Despachante = request.getParameter("oid_Despachante");
		String DM_Situacao = request.getParameter("FT_DM_Situacao");
		String oid_Aduana = request.getParameter("oid_Aduana");

		//*** Validações
		if (!doValida(oid_Cliente_Despachante) || !doValida(oid_Despachante) || !doValida(DM_Situacao))
		    throw new Excecoes("Parâmetros incorretos!");

		Cliente_DespachanteED ed = new Cliente_DespachanteED(Integer.parseInt(oid_Cliente_Despachante));
		ed.setOid_Despachante(Integer.parseInt(oid_Despachante));
		ed.setDM_Situacao(DM_Situacao);
		if(doValida(oid_Aduana)){
		    ed.setOid_Aduana(Integer.parseInt(oid_Aduana));
		} else {
		    throw new Excecoes("Aduana não Preenchida!");
		}
		
		new Cliente_DespachanteRN().altera(ed);
	}

	public void deleta(HttpServletRequest request) throws Excecoes {

		String oid_Cliente_Despachante = request.getParameter("oid_Cliente_Despachante");
		//*** Validações
		if (!doValida(oid_Cliente_Despachante))
		    throw new Excecoes("ID Cliente Despachante não informado!");
		
		new Cliente_DespachanteRN().deleta(new Cliente_DespachanteED(Integer.parseInt(oid_Cliente_Despachante)));
	}

	public ArrayList listaCliente_Despachante(HttpServletRequest request) throws Excecoes {

		String oid_Cliente = request.getParameter("oid_Cliente");
		//*** Validações
		if (!doValida(oid_Cliente))
		    throw new Excecoes("ID Cliente não informado!");

		Cliente_DespachanteED ed = new Cliente_DespachanteED();
		ed.setOid_Cliente(oid_Cliente);
	    
		return new Cliente_DespachanteRN().lista(ed);
	}

	public Cliente_DespachanteED getByRecord(HttpServletRequest request) throws Excecoes {

		String oid_Cliente_Despachante = request.getParameter("oid_Cliente_Despachante");
		
		//*** Validações
		if (!doValida(oid_Cliente_Despachante)){
		    throw new Excecoes("ID do Cliente_Despachante deve ser maior que ZERO!");
		}
		
		Cliente_DespachanteED ed = new Cliente_DespachanteED(Integer.parseInt(oid_Cliente_Despachante));
		
		return new Cliente_DespachanteRN().getByRecord(ed);
	}
	
	public Cliente_DespachanteED getByOid(String oid_Cliente_Despachante) throws Excecoes {

		//*** Validações
		if (!doValida(oid_Cliente_Despachante)){
		    throw new Excecoes("ID do Cliente_Despachante deve ser maior que ZERO!");
		} else return new Cliente_DespachanteRN().getByRecord(new Cliente_DespachanteED(Integer.parseInt(oid_Cliente_Despachante)));

	}
	
    //*** Verifica se registro já existe!
    private boolean doExiste(Cliente_DespachanteED ed) throws Excecoes {
        
		//*** Validações
	    String strFrom  = "Clientes_Despachantes";
	    String strWhere = " oid_Cliente = '" +ed.getOid_Cliente()+"'" +
	    				  " AND oid_Despachante = " + ed.getOid_Despachante();
	    return new BancoUtil().doExiste(strFrom, strWhere);
    }
    
	public ArrayList listaCliente_Despachante_MIC(HttpServletRequest request) throws Excecoes {

		String oid_Cliente = request.getParameter("oid_Cliente");
		//*** Validações
		if (!doValida(oid_Cliente))
		    throw new Excecoes("ID Cliente não informado!");

		Cliente_DespachanteED ed = new Cliente_DespachanteED();
		ed.setOid_Cliente(oid_Cliente);
	    
		return new Cliente_DespachanteRN().lista_MIC(ed);
	}
}