package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import com.master.ed.Ordem_EmbarqueED;
import com.master.rn.Ordem_EmbarqueRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

/**
 * @author André Valadas
 */
public class Ordem_EmbarqueBean extends JavaUtil implements Serializable {

	public Ordem_EmbarqueED inclui(HttpServletRequest request) throws Excecoes {

		
		String cd_Ordem_Servico = request.getParameter("FT_CD_Ordem_Servico");
	    String nm_Tecnico = request.getParameter("FT_NM_Tecnico");
	    String nr_placa = request.getParameter("FT_NR_Placa");
	    String DT_Entrega = request.getParameter("FT_DT_Entrega");
	    
		//*** Validações
		if (!doValida(cd_Ordem_Servico))
		    throw new Excecoes("Ordem_Servico não informada!");
		if (!doValida(nm_Tecnico))
		    throw new Excecoes("Tecnico não informado!");
		if (!doValida(nr_placa))
		    throw new Excecoes("Veículo não informado!");
		if (!doValida(DT_Entrega))
		    throw new Excecoes("Data da Entrega não informada!");
		
        Ordem_EmbarqueED ed = new Ordem_EmbarqueED();
		ed.setCd_ordem_servico(cd_Ordem_Servico);
		ed.setNm_tecnico(nm_Tecnico);
		ed.setNr_placa(nr_placa);
		ed.setDT_Entrega(DT_Entrega);
		
		return new Ordem_EmbarqueRN().inclui(ed);
	}

	public void deleta(HttpServletRequest request) throws Excecoes {

		String oid_Ordem_Embarque = request.getParameter("oid_Ordem_Embarque");
		if (!doValida(oid_Ordem_Embarque))
		    throw new Excecoes("Carga Entrega não informada!");
		new Ordem_EmbarqueRN().deleta(new Ordem_EmbarqueED(Integer.parseInt(oid_Ordem_Embarque)));
	}

    public ArrayList lista(HttpServletRequest request) throws Excecoes {

        return this.lista(request, true, true, true);
    }
    
	public ArrayList lista(HttpServletRequest request, boolean Tecnico, boolean Ordem_Servico, boolean veiculo) throws Excecoes {

		String oid_Ordem_Embarque = request.getParameter("oid_Ordem_Embarque");
		String cd_Ordem_Servico = request.getParameter("FT_CD_Ordem_Servico");
	    String nm_Tecnico = request.getParameter("FT_NM_Tecnico");
	    String nr_placa = request.getParameter("FT_NR_Placa");
	    String nr_carga = request.getParameter("FT_NR_Carga");
	    
	    String DT_Entrega = request.getParameter("FT_DT_Entrega");
		
        Ordem_EmbarqueED ed = new Ordem_EmbarqueED(Tecnico, Ordem_Servico, veiculo);
		//*** Validações
	    if (doValida(oid_Ordem_Embarque))
		    ed.setOid_Ordem_Embarque(Integer.parseInt(oid_Ordem_Embarque));
		if (doValida(cd_Ordem_Servico))
		    ed.setCd_ordem_servico(cd_Ordem_Servico);
		if (doValida(nm_Tecnico))
		    ed.setNm_tecnico(nm_Tecnico);
		if (doValida(nr_placa))
		    ed.setNr_placa(nr_placa);
		if (doValida(DT_Entrega))
		    ed.setDT_Entrega(DT_Entrega);
		if (doValida(nr_carga))
		    ed.setNR_Carga(Integer.parseInt(nr_carga));
	    
		return new Ordem_EmbarqueRN().lista(ed);
	}

    public Ordem_EmbarqueED getByOid(String oid_Ordem_Embarque) throws Excecoes {

        return new Ordem_EmbarqueRN().getByRecord(new Ordem_EmbarqueED(Integer.parseInt(oid_Ordem_Embarque), true, true, true));
    }
	public Ordem_EmbarqueED getByOid(String oid_Ordem_Embarque, boolean Tecnico, boolean Ordem_Servico, boolean veiculo) throws Excecoes {

	    if (!doValida(oid_Ordem_Embarque))
		    throw new Excecoes("Carga Entrega não informada!");
		return new Ordem_EmbarqueRN().getByRecord(new Ordem_EmbarqueED(Integer.parseInt(oid_Ordem_Embarque), Tecnico, Ordem_Servico, veiculo));
	}
	
	public Ordem_EmbarqueED getByNR_Carga(String NR_Carga) throws Excecoes {

        Ordem_EmbarqueED ed = new Ordem_EmbarqueED();
	    if (!doValida(NR_Carga))
		    return ed;
	    ed.setNR_Carga(Integer.parseInt(NR_Carga));
		return new Ordem_EmbarqueRN().getByRecord(ed);
	}
}