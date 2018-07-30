/*
 * Created on 04/07/2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.master.iu;


import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import com.master.ed.ChamadoED;
import com.master.rn.ChamadoRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

/**
 * @author Jonas
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class sup001Bean {

	public ChamadoED inclui(HttpServletRequest request) throws Excecoes {

		ChamadoED ed = new ChamadoED();


		ed.setOID_Cliente(request.getParameter("oid_Pessoa"));
		ed.setOID_Responsavel(request.getParameter("oid_Pessoa_Responsavel"));
		ed.setDT_Entrada(request.getParameter("FT_DT_Entrada"));
		ed.setHR_Entrada(request.getParameter("FT_HR_Entrada"));
		ed.setDM_Prioridade(request.getParameter("FT_DM_Prioridade"));
		ed.setDM_Classificacao(request.getParameter("FT_DM_Classificacao"));
		ed.setNM_Solicitante(request.getParameter("FT_NM_Solicitante"));
		ed.setTX_Descricao(request.getParameter("FT_TX_Observacao"));
		ed.setNR_Previsao(request.getParameter("FT_NR_Previsao"));
		ed.setDT_Inicio(request.getParameter("FT_DT_Inicio"));
		ed.setHR_Inicio(request.getParameter("FT_HR_Inicio"));
		ed.setDT_Conclusao(request.getParameter("FT_DT_Conclusao"));
		ed.setHR_Conclusao(request.getParameter("FT_HR_Conclusao"));
		ed.setDM_Situacao(request.getParameter("FT_DM_Situacao"));
		ed.setUsuario_stamp(request.getParameter("usuario_stamp"));



		return new ChamadoRN().inclui(ed);
	}


	public ChamadoED altera(HttpServletRequest request) throws Excecoes {


		ChamadoED ed = new ChamadoED();

		ed.setOID_Chamado(new Long(request.getParameter("oid_Chamado")).longValue());
		ed.setOID_Cliente(request.getParameter("oid_Pessoa"));
		ed.setOID_Responsavel(request.getParameter("oid_Pessoa_Responsavel"));
		ed.setDT_Entrada(request.getParameter("FT_DT_Entrada"));
		ed.setHR_Entrada(request.getParameter("FT_HR_Entrada"));
		ed.setDM_Prioridade(request.getParameter("FT_DM_Prioridade"));
		ed.setDM_Classificacao(request.getParameter("FT_DM_Classificacao"));
		ed.setNM_Solicitante(request.getParameter("FT_NM_Solicitante"));
		ed.setTX_Descricao(request.getParameter("FT_TX_Observacao"));
		ed.setNR_Previsao(request.getParameter("FT_NR_Previsao"));
		ed.setDT_Inicio(request.getParameter("FT_DT_Inicio"));
		ed.setHR_Inicio(request.getParameter("FT_HR_Inicio"));
		ed.setDT_Conclusao(request.getParameter("FT_DT_Conclusao"));
		ed.setHR_Conclusao(request.getParameter("FT_HR_Conclusao"));
		ed.setDM_Situacao(request.getParameter("FT_DM_Situacao"));
		ed.setUsuario_stamp(request.getParameter("usuario_stamp"));

		// System.out.println("#################chegou bean#####################");


		return new ChamadoRN().altera(ed);
	}


	public void deleta(HttpServletRequest request)throws Excecoes{

	    try{
	      ChamadoRN chamadoRN = new ChamadoRN();
	      ChamadoED ed = new ChamadoED();

	      String OID_Chamado = request.getParameter("oid_Chamado");
	      if (String.valueOf(OID_Chamado) != null && !String.valueOf(OID_Chamado).equals("")
	        && !String.valueOf(OID_Chamado).equals("null")){
	        ed.setOID_Chamado(new Long(OID_Chamado).longValue());
	      }

	      chamadoRN.deleta(ed);
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


		ChamadoED ed = new ChamadoED();

		if (JavaUtil.doValida(request.getParameter("oid_Chamado")))
			ed.setOID_Chamado(Long.parseLong(request.getParameter("oid_Chamado")));
		ed.setOID_Cliente(request.getParameter("oid_Pessoa"));
		ed.setOID_Responsavel(request.getParameter("oid_Pessoa_Responsavel"));
		ed.setDT_Entrada(request.getParameter("FT_DT_Entrada"));
		ed.setDT_Entrada_Final(request.getParameter("FT_DT_Entrada_Final"));
		ed.setHR_Entrada(request.getParameter("FT_HR_Entrada"));
		ed.setDM_Prioridade(request.getParameter("FT_DM_Prioridade"));
		ed.setDM_Classificacao(request.getParameter("FT_DM_Classificacao"));
		ed.setNM_Solicitante(request.getParameter("FT_NM_Solicitante"));
		ed.setTX_Descricao(request.getParameter("FT_TX_Descricao"));
		ed.setNR_Previsao(request.getParameter("FT_NR_Previsao"));
		ed.setDT_Inicio(request.getParameter("FT_DT_Inicio"));
		ed.setDT_Inicio_Final(request.getParameter("FT_DT_Inicio_Final"));
		ed.setHR_Inicio(request.getParameter("FT_HR_inicio"));
		ed.setDT_Conclusao(request.getParameter("FT_DT_Conclusao"));
		ed.setHR_Conclusao(request.getParameter("FT_HR_Conclusao"));
		ed.setDM_Situacao(request.getParameter("FT_DM_Situacao"));
		ed.setUsuario_stamp(request.getParameter("usuario_stamp"));

		return new ChamadoRN().lista(ed);
	}


	public ChamadoED getByRecord(HttpServletRequest request)throws Excecoes{


		ChamadoED ed = new ChamadoED();

		if (JavaUtil.doValida(request.getParameter("oid_Chamado")))
				ed.setOID_Chamado(Long.parseLong(request.getParameter("oid_Chamado")));

		ed.setOID_Cliente(request.getParameter("oid_Pessoa"));
		ed.setOID_Responsavel(request.getParameter("oid_Pessoa_Responsavel"));
		ed.setDT_Entrada(request.getParameter("FT_DT_Entrada"));
		ed.setDT_Entrada_Final(request.getParameter("FT_DT_Entrada_Final"));
		ed.setHR_Entrada(request.getParameter("FT_HR_Entrada"));
		ed.setDM_Prioridade(request.getParameter("FT_DM_Prioridade"));
		ed.setDM_Classificacao(request.getParameter("FT_DM_Classificacao"));
		ed.setNM_Solicitante(request.getParameter("FT_NM_Solicitante"));
		ed.setTX_Descricao(request.getParameter("FT_TX_Descricao"));
		ed.setNR_Previsao(request.getParameter("FT_NR_Previsao"));
		ed.setDT_Inicio(request.getParameter("FT_DT_Inicio"));
		ed.setDT_Inicio_Final(request.getParameter("FT_DT_Inicio_Final"));
		ed.setHR_Inicio(request.getParameter("FT_HR_inicio"));
		ed.setDT_Conclusao(request.getParameter("FT_DT_Conclusao"));
		ed.setHR_Conclusao(request.getParameter("FT_HR_Conclusao"));
		ed.setDM_Situacao(request.getParameter("FT_DM_Situacao"));
		ed.setUsuario_stamp(request.getParameter("usuario_stamp"));

		return new ChamadoRN().getByRecord(ed);
	}

}
