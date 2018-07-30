package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.EntregadorED;
import com.master.rn.EntregadorRN;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;

/*
 * Created on 15/10/2004
 */

/**
 * @author Andre Valadas
 */
public class EntregadorBean extends JavaUtil implements Serializable {

	public EntregadorED inclui(HttpServletRequest request) throws Excecoes {

		EntregadorED ed = new EntregadorED();

		String oid_Pessoa = request.getParameter("oid_Pessoa");
        String oid_Veiculo = request.getParameter("oid_Veiculo");
		String CD_Entregador = request.getParameter("FT_CD_Entregador");

        if (!doValida(oid_Veiculo))
            throw new Mensagens("Veiculo não informado!");
		//*** Validações
		if (doValida(oid_Pessoa) && doValida(CD_Entregador)) {
		    ed.setOid_Pessoa(oid_Pessoa);
			ed.setCD_Entregador(CD_Entregador);
            ed.setOid_Veiculo(oid_Veiculo);
		} else throw new Excecoes("Parâmetros incorretos!");

		//*** Valida se o registro não existe para poder incluir
		if (!this.doExiste(ed)) {
		    return new EntregadorRN().inclui(ed);
		} else {
		    throw new Excecoes("Esse Entregador já existe!");
		}
	}

	public void altera(HttpServletRequest request) throws Excecoes {

		EntregadorED ed = new EntregadorED();

		String oid_Entregador = request.getParameter("oid_Entregador");
        String oid_Veiculo = request.getParameter("oid_Veiculo");
		String CD_Entregador = request.getParameter("FT_CD_Entregador");

        if (!doValida(oid_Veiculo))
            throw new Mensagens("Veiculo não informado!");
        
		//*** Validações
		if (doValida(oid_Entregador) && doValida(CD_Entregador)) {

			ed.setOid_Entregador(Integer.parseInt(oid_Entregador));
			ed.setCD_Entregador(CD_Entregador);
            ed.setOid_Veiculo(oid_Veiculo);
		} else throw new Excecoes("Parâmetros incorretos!");
		
		//Válida se pode ser alterado
		new BancoUtil().doValidaUpdate(ed.getOid_Entregador(), ed.getCD_Entregador(), "Entregadores", "oid_Entregador", "cd_Entregador");
		new EntregadorRN().altera(ed);
	}

	public void deleta(HttpServletRequest request) throws Excecoes {

		String oid_Entregador = request.getParameter("oid_Entregador");
		//*** Validações
		if (doValida(oid_Entregador)) {
		    new EntregadorRN().deleta(new EntregadorED(Integer.parseInt(oid_Entregador)));
		} else throw new Excecoes("Parâmetros incorretos!");
	}

	public ArrayList listaEntregador(HttpServletRequest request) throws Excecoes {

	    EntregadorED ed = new EntregadorED();
	    String NM_Entregador = request.getParameter("FT_NM_Entregador");
	    if (doValida(NM_Entregador))
	        ed.setNM_Entregador(NM_Entregador);
		return new EntregadorRN().lista(ed);
	}

	public EntregadorED getByRecord(HttpServletRequest request) throws Excecoes {

	    EntregadorED ed = new EntregadorED();

		String oid_Entregador = request.getParameter("oid_Entregador");
		String oid_Pessoa = request.getParameter("oid_Pessoa");
		
		//*** Validações
		if (!doValida(oid_Entregador) && !doValida(oid_Pessoa)){
		    throw new Excecoes("ID do Entregador deve ser maior que ZERO!");
		}
		if (doValida(oid_Entregador)){
		    ed.setOid_Entregador(Integer.parseInt(oid_Entregador));
		}
		if (doValida(oid_Pessoa)){
		    ed.setOid_Pessoa(oid_Pessoa);
		}
		return new EntregadorRN().getByRecord(ed);

	}
	
	public EntregadorED getByOid(String oid_Entregador) throws Excecoes {

		//*** Validações
		if (doValida(oid_Entregador))
			return new EntregadorRN().getByRecord(new EntregadorED(Integer.parseInt(oid_Entregador)));
	
		return new EntregadorED();
	}
	
	public EntregadorED getByCdEntregador(String cd_Entregador) throws Excecoes {
		
		//*** Validações
		if (!doValida(cd_Entregador))
		    throw new Excecoes("Código do Entregador não informado!");
        EntregadorED ed = new EntregadorED();
        ed.setCD_Entregador(cd_Entregador);
		return new EntregadorRN().getByRecord(ed);
	}
	
    //*** Verifica se registro já existe!
    private boolean doExiste(EntregadorED ed) throws Excecoes {
        
		//*** Validações
	    String strFrom  = "Entregadores";
	    String strWhere = " cd_Entregador = '" + ed.getCD_Entregador() +"'";
	    
	    return new BancoUtil().doExiste(strFrom, strWhere);
    }
}