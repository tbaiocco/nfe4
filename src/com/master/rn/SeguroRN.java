package com.master.rn;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.bd.*;
import com.master.ed.*;
import com.master.rl.JasperRL;
import com.master.util.*;
import com.master.util.bd.*;

public class SeguroRN extends Transacao {

    SeguroBD SeguroBD = null;

    public SeguroRN() {
    }

    public byte[] geraSeguroConhecimento(SeguroED ed) throws Excecoes {

        //antes de invocar chamada ao relatorio deve-se
        //fazer validacoes de regra de negocio

        this.inicioTransacao();
        SeguroBD = new SeguroBD(this.sql);
        byte[] b = SeguroBD.geraSeguroConhecimento(ed);
        this.fimTransacao(false);
        return b;
    }

	public void geraSeguroConhecimentoJasper (HttpServletRequest request, HttpServletResponse response) throws Excecoes {
		try {
			this.inicioTransacao();

			SeguroED ed = new SeguroED();

			ed = carregaED(request);

			ArrayList listMov = new ArrayList();
			listMov = new SeguroBD(this.sql).geraSeguroConhecimentoJasper(ed);

			if("M1".equals(ed.getDM_Relatorio())){
				ed.setNomeRelatorio("RelatorioRelacaoSeguroRodoviario"); // Seta o nome do relatório
			}
			else
			if("M3".equals(ed.getDM_Relatorio())){
				ed.setNomeRelatorio("RelatorioRelacaoSeguroAereo"); // Seta o nome do relatório
			}
			else
			if("M2".equals(ed.getDM_Relatorio())){
				ed.setNomeRelatorio("RelatorioRelacaoSeguroEspecial"); // Seta o nome do relatório
			}
			else
			if("R1".equals(ed.getDM_Relatorio()) || "R2".equals(ed.getDM_Relatorio())){
				ed.setNomeRelatorio("ResumoRelacaoSeguroRodoviario"); // Seta o nome do relatório
			}
			ed.setLista(listMov);
			ed.setResponse(response);
			new JasperRL(ed).geraRelatorio(); // Chama o relatorio passando o ed
		}
		finally {
			this.fimTransacao(true);
		}
	}

	public ArrayList geraArqSeguro (HttpServletRequest request, HttpServletResponse response) throws Excecoes {
		try {
			this.inicioTransacao();

			SeguroED ed = new SeguroED();

			ed = carregaED(request);

			ArrayList listMov = new ArrayList();

			return new SeguroBD(this.sql).geraSeguroConhecimentoJasper(ed);

		}
		finally {
			this.fimTransacao(true);
		}

	}

	public SeguroED carregaED(HttpServletRequest request) throws Excecoes {

		  SeguroED ed = new SeguroED();

		  String oid_Empresa = request.getParameter("oid_Empresa");
	      if (oid_Empresa != null && !oid_Empresa.equals("") && !oid_Empresa.equals("null"))
	        ed.setOid_Empresa(new Long(oid_Empresa).longValue());

	      String oid_Unidade = request.getParameter("oid_Unidade");
	      if (oid_Unidade != null && !oid_Unidade.equals("") && !oid_Unidade.equals("null"))
	        ed.setOid_Unidade(new Long(oid_Unidade).longValue());

	      String oid_Seguradora = request.getParameter("oid_Seguradora");
	      if (oid_Seguradora != null && !oid_Seguradora.equals("") && !oid_Seguradora.equals("null"))
	        ed.setOid_Seguradora(new Long(oid_Seguradora).longValue());


	      String dt_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
	      if (dt_Inicial != null && !dt_Inicial.equals("") && !dt_Inicial.equals("null"))
	        ed.setDT_Inicial(dt_Inicial);

	      String dt_Final = request.getParameter("FT_DT_Emissao_Final");
	      if (dt_Final != null && !dt_Final.equals("") && !dt_Final.equals("null"))
	        ed.setDT_Final(dt_Final);

	      String DM_Situacao = request.getParameter("FT_DM_Situacao");
	      if (DM_Situacao != null && !DM_Situacao.equals("") && !DM_Situacao.equals("null"))
	        ed.setDM_Situacao(DM_Situacao);

	      String DM_Situacao_Cliente = request.getParameter("FT_DM_Situacao_Cliente");
	      if (DM_Situacao_Cliente != null && !DM_Situacao_Cliente.equals("") && !DM_Situacao_Cliente.equals("null"))
	        ed.setDM_Situacao_Cliente(DM_Situacao_Cliente);

	      String DM_Tipo_Transporte = request.getParameter("FT_DM_Tipo_Transporte");
	      if (String.valueOf(DM_Tipo_Transporte) != null && !String.valueOf(DM_Tipo_Transporte).equals("")){
	        ed.setDM_Tipo_Transporte(DM_Tipo_Transporte);
	      }


	      String DM_Relatorio = request.getParameter("FT_DM_Relatorio");
	      if (String.valueOf(DM_Relatorio) != null && !String.valueOf(DM_Relatorio).equals("")){
	        ed.setDM_Relatorio(DM_Relatorio);
	      }

	      ed.setOID_Pessoa(request.getParameter("oid_Pessoa_Remetente"));

	      return ed;

	}


}
