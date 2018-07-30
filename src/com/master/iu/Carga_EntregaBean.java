package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import com.master.ed.Carga_EntregaED;
import com.master.rn.Carga_EntregaRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

/**
 * @author Andr� Valadas
 */
public class Carga_EntregaBean extends JavaUtil implements Serializable {

	public Carga_EntregaED inclui(HttpServletRequest request) throws Excecoes {

	    String oid_Unidade = request.getParameter("oid_Unidade");
	    String oid_Entregador = request.getParameter("oid_Entregador");
	    String oid_Veiculo = request.getParameter("oid_Veiculo");
	    String DT_Entrega = request.getParameter("FT_DT_Entrega");
	    
		//*** Valida��es
		if (!doValida(oid_Unidade))
		    throw new Excecoes("Unidade n�o informada!");
		if (!doValida(oid_Entregador))
		    throw new Excecoes("Entregador n�o informado!");
		if (!doValida(oid_Veiculo))
		    throw new Excecoes("Ve�culo n�o informado!");
		if (!doValida(DT_Entrega))
		    throw new Excecoes("Data da Entrega n�o informada!");
		
        Carga_EntregaED ed = new Carga_EntregaED();
		ed.setOid_Unidade(Integer.parseInt(oid_Unidade));
		ed.setOid_Entregador(Integer.parseInt(oid_Entregador));
		ed.setOid_Veiculo(oid_Veiculo);
		ed.setDT_Entrega(DT_Entrega);
		
		return new Carga_EntregaRN().inclui(ed);
	}

	public void deleta(HttpServletRequest request) throws Excecoes {

		String oid_Carga_Entrega = request.getParameter("oid_Carga_Entrega");
		if (!doValida(oid_Carga_Entrega))
		    throw new Excecoes("Carga Entrega n�o informada!");
		new Carga_EntregaRN().deleta(new Carga_EntregaED(Integer.parseInt(oid_Carga_Entrega)));
	}

    public ArrayList lista(HttpServletRequest request) throws Excecoes {

        return this.lista(request, true, true, true);
    }
    
	public ArrayList lista(HttpServletRequest request, boolean entregador, boolean unidade, boolean veiculo) throws Excecoes {

	    String oid_Carga_Entrega = request.getParameter("oid_Carga_Entrega");
	    String oid_Unidade = request.getParameter("oid_Unidade");
	    String oid_Entregador = request.getParameter("oid_Entregador");
	    String oid_Veiculo = request.getParameter("oid_Veiculo");
	    String DT_Entrega = request.getParameter("FT_DT_Entrega");
	    
        Carga_EntregaED ed = new Carga_EntregaED(entregador, unidade, veiculo);
		//*** Valida��es
	    if (doValida(oid_Carga_Entrega))
		    ed.setOid_Carga_Entrega(Integer.parseInt(oid_Carga_Entrega));
		if (doValida(oid_Unidade))
		    ed.setOid_Unidade(Integer.parseInt(oid_Unidade));
		if (doValida(oid_Entregador))
		    ed.setOid_Entregador(Integer.parseInt(oid_Entregador));
		if (doValida(oid_Veiculo))
		    ed.setOid_Veiculo(oid_Veiculo);
		if (doValida(DT_Entrega))
		    ed.setDT_Entrega(DT_Entrega);
	    
		return new Carga_EntregaRN().lista(ed);
	}

    public Carga_EntregaED getByOid(String oid_Carga_Entrega) throws Excecoes {

        return new Carga_EntregaRN().getByRecord(new Carga_EntregaED(Integer.parseInt(oid_Carga_Entrega), true, true, true));
    }
	public Carga_EntregaED getByOid(String oid_Carga_Entrega, boolean entregador, boolean unidade, boolean veiculo) throws Excecoes {

	    if (!doValida(oid_Carga_Entrega))
		    throw new Excecoes("Carga Entrega n�o informada!");
		return new Carga_EntregaRN().getByRecord(new Carga_EntregaED(Integer.parseInt(oid_Carga_Entrega), entregador, unidade, veiculo));
	}
	
	public Carga_EntregaED getByNR_Carga(String NR_Carga) throws Excecoes {

        Carga_EntregaED ed = new Carga_EntregaED();
	    if (!doValida(NR_Carga))
		    return ed;
	    ed.setNR_Carga(Integer.parseInt(NR_Carga));
		return new Carga_EntregaRN().getByRecord(ed);
	}
}