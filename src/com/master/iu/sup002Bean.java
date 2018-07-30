/*
 * Created on 05/07/2006
 */
package com.master.iu;

/**
 * by Jonas
 **/

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import com.master.ed.Movimento_ChamadoED;
import com.master.rn.Movimento_ChamadoRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

public class sup002Bean {
	
	public Movimento_ChamadoED inclui(HttpServletRequest request) throws Excecoes {
		
		Movimento_ChamadoED ed = new Movimento_ChamadoED();
		
		ed.setOID_Chamado(Long.parseLong(request.getParameter("oid_Chamado")));
		ed.setOID_Movimento_Chamado(request.getParameter("oid_Movimento_Chamado"));
		ed.setOID_Responsavel(request.getParameter("oid_Pessoa_Responsavel"));
		ed.setDT_Inicio(request.getParameter("FT_DT_Inicio"));
		ed.setHR_Inicio(request.getParameter("FT_HR_Inicio"));
		ed.setDT_Fim(request.getParameter("FT_DT_Fim"));
		ed.setHR_Fim(request.getParameter("FT_HR_Fim"));
		ed.setDM_Tipo_Movimento(request.getParameter("FT_DM_Situacao"));
		ed.setTX_Descricao(request.getParameter("FT_TX_Observacao"));
		ed.setDT_Stamp(request.getParameter("usuario_stamp"));
		
		return new Movimento_ChamadoRN().inclui(ed);
	}
	
	
	public void altera(HttpServletRequest request) throws Excecoes {
		
		Movimento_ChamadoED ed = new Movimento_ChamadoED();
		
		ed.setOID_Chamado(new Long(request.getParameter("oid_Chamado")).longValue());
		ed.setOID_Movimento_Chamado(request.getParameter("oid_Movimento_Chamado"));
		ed.setOID_Responsavel(request.getParameter("oid_Pessoa_Responsavel"));
		
		if (JavaUtil.doValida(request.getParameter("FT_DT_Inicio")))
			ed.setDT_Inicio(request.getParameter("FT_DT_Inicio"));
		
		if (JavaUtil.doValida(request.getParameter("FT_HR_Inicio")))
			ed.setHR_Inicio(request.getParameter("FT_HR_Inicio"));
		
		if (JavaUtil.doValida(request.getParameter("FT_DT_Fim")))
			ed.setDT_Fim(request.getParameter("FT_DT_Fim"));
		
		if (JavaUtil.doValida(request.getParameter("FT_HR_Fim")))
			ed.setHR_Fim(request.getParameter("FT_HR_Fim"));
		
		ed.setDM_Tipo_Movimento(request.getParameter("FT_DM_Situacao"));
		ed.setTX_Descricao(request.getParameter("FT_TX_Observacao"));
		ed.setDT_Stamp(request.getParameter("usuario_stamp"));

		
		new Movimento_ChamadoRN().altera(ed);
	}
	
	public void deleta(HttpServletRequest request)throws Excecoes{

	    try{
	      Movimento_ChamadoRN movimento_ChamadoRN = new Movimento_ChamadoRN();
	      Movimento_ChamadoED ed = new Movimento_ChamadoED();	     

	      String OID_Movimento_Chamado = request.getParameter("oid_Movimento_Chamado");
	      if (String.valueOf(OID_Movimento_Chamado) != null && !String.valueOf(OID_Movimento_Chamado).equals("")
	        && !String.valueOf(OID_Movimento_Chamado).equals("null")){
	        ed.setOID_Movimento_Chamado(OID_Movimento_Chamado);
	      }

	      movimento_ChamadoRN.deleta(ed);
	    }
	    catch (Excecoes exc){
	      throw exc;
	    }
	    catch(Exception exc){
	      Excecoes excecoes = new Excecoes();
	      excecoes.setClasse(this.getClass().getName());
	      excecoes.setMensagem("erro ao excluir");
	      excecoes.setMetodo("deleta");
	      excecoes.setExc(exc);
	      throw excecoes;
	    }
	  }
	
	
	public ArrayList Lista(HttpServletRequest request)throws Excecoes{
		
		
		Movimento_ChamadoED ed = new Movimento_ChamadoED();
		
		ed.setOID_Chamado(new Long(request.getParameter("oid_Chamado")).longValue());
		ed.setOID_Movimento_Chamado(request.getParameter("oid_Movimento_Chamado"));
		ed.setOID_Responsavel(request.getParameter("oid_Pessoa_Responsavel"));
		ed.setDT_Inicio(request.getParameter("FT_DT_Inicio"));
		ed.setHR_Inicio(request.getParameter("FT_HR_Inicio"));
		ed.setDT_Fim(request.getParameter("FT_DT_Fim"));
		ed.setHR_Fim(request.getParameter("FT_HR_Fim"));
		ed.setDM_Tipo_Movimento(request.getParameter("FT_DM_Tipo_Movimento"));
		ed.setTX_Descricao(request.getParameter("FT_TX_Observacao"));
		ed.setDT_Stamp(request.getParameter("usuario_stamp"));
				
		return new Movimento_ChamadoRN().lista(ed);
	}
	
	public Movimento_ChamadoED getByRecord(HttpServletRequest request)throws Excecoes{
		
		
		Movimento_ChamadoED ed = new Movimento_ChamadoED();
		
		if (JavaUtil.doValida(request.getParameter("oid_Movimento_Chamado")))
			ed.setOID_Movimento_Chamado(request.getParameter("oid_Movimento_Chamado"));
		if (JavaUtil.doValida(request.getParameter("oid_Chamado")))
			ed.setOID_Chamado(Long.parseLong(request.getParameter("oid_Chamado")));
		ed.setOID_Movimento_Chamado(request.getParameter("oid_Movimento_Chamado"));
		ed.setOID_Responsavel(request.getParameter("oid_Pessoa_Responsavel"));
		ed.setDT_Inicio(request.getParameter("FT_DT_Inicio"));
		ed.setHR_Inicio(request.getParameter("FT_HR_Inicio"));
		ed.setDT_Fim(request.getParameter("FT_DT_Fim"));
		ed.setHR_Fim(request.getParameter("FT_HR_Fim"));
		ed.setDM_Tipo_Movimento(request.getParameter("FT_DM_Tipo_Movimento"));
		ed.setTX_Descricao(request.getParameter("FT_TX_Observacao"));
		ed.setDT_Stamp(request.getParameter("usuario_stamp"));
				
		return new Movimento_ChamadoRN().getByRecord(ed);
	}

}

