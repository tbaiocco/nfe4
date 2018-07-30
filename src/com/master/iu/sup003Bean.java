/*
 * Created on 05/07/2006
 */
package com.master.iu;

/**
 * by Jonas
 **/

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import com.master.ed.Arquivo_ChamadoED;
import com.master.rn.Arquivo_ChamadoRN;
import com.master.util.Excecoes;

public class sup003Bean {

	public Arquivo_ChamadoED inclui(HttpServletRequest request) throws Excecoes {
		
		Arquivo_ChamadoED ed = new Arquivo_ChamadoED();
		
		ed.setOID_Chamado(Long.parseLong(request.getParameter("oid_Chamado")));
		ed.setOID_Arquivo_Chamado(request.getParameter("oid_Arquivo_Chamado"));		
		ed.setNM_Arquivo(String.valueOf(request.getAttribute("FT_NM_Arquivo")));
		ed.setNM_Path_Arquivo(request.getRealPath("/") + "suporte/arquivos/");		
		
		return new Arquivo_ChamadoRN().inclui(ed);
	}
	
	public void altera(HttpServletRequest request) throws Excecoes {
		
		Arquivo_ChamadoED ed = new Arquivo_ChamadoED();
		
		ed.setOID_Chamado(Long.parseLong(request.getParameter("oid_Chamado")));
		ed.setOID_Arquivo_Chamado(request.getParameter("oid_Arquivo_Chamado"));		
		ed.setNM_Arquivo(request.getParameter("FT_NM_Arquivo"));
		ed.setNM_Path_Arquivo(request.getParameter("FT_NM_Path_Arquivo"));
		
		new Arquivo_ChamadoRN().altera(ed);
	}
	
	public void deleta(HttpServletRequest request)throws Excecoes{

	    try{
	      Arquivo_ChamadoRN arquivo_ChamadoRN = new Arquivo_ChamadoRN();
	      Arquivo_ChamadoED ed = new Arquivo_ChamadoED();	     

	      String OID_Arquivo_Chamado = request.getParameter("oid_Arquivo_Chamado");
	      if (String.valueOf(OID_Arquivo_Chamado) != null && !String.valueOf(OID_Arquivo_Chamado).equals("")
	        && !String.valueOf(OID_Arquivo_Chamado).equals("null")){
	        ed.setOID_Arquivo_Chamado(OID_Arquivo_Chamado);
	      }

	      arquivo_ChamadoRN.deleta(ed);
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
		
		
		Arquivo_ChamadoED ed = new Arquivo_ChamadoED();
		
		ed.setOID_Chamado(Long.parseLong(request.getParameter("oid_Chamado")));
		ed.setOID_Arquivo_Chamado(request.getParameter("oid_Arquivo_Chamado"));		
		ed.setNM_Arquivo(request.getParameter("FT_NM_Arquivo"));
		ed.setNM_Path_Arquivo(request.getParameter("FT_NM_Path_Arquivo"));
				
		return new Arquivo_ChamadoRN().lista(ed);
	}
	
	public Arquivo_ChamadoED getByRecord(HttpServletRequest request)throws Excecoes{
		
		
		Arquivo_ChamadoED ed = new Arquivo_ChamadoED();
		
		ed.setOID_Chamado(Long.parseLong(request.getParameter("oid_Chamado")));
		ed.setOID_Arquivo_Chamado(request.getParameter("oid_Arquivo_Chamado"));		
		ed.setNM_Arquivo(request.getParameter("FT_NM_Arquivo"));
		ed.setNM_Path_Arquivo(request.getParameter("FT_NM_Path_Arquivo"));
				
		return new Arquivo_ChamadoRN().getByRecord(ed);
	}
	
}
