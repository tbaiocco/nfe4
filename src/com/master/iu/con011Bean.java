package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.master.ed.Processo_AduaneiroED;
import com.master.rn.Processo_AduaneiroRN;
import com.master.util.*;

public class con011Bean {

	  public Processo_AduaneiroED inclui(HttpServletRequest request) throws Excecoes {
	  	Processo_AduaneiroED ed = new Processo_AduaneiroED();

                  ed = this.carregaED(request);
//                  ed.setDM_Situacao("1");

                return new Processo_AduaneiroRN().inclui(ed);
	  }

      public Processo_AduaneiroED getByRecord(HttpServletRequest request) throws Excecoes{
	  	Processo_AduaneiroED ed = new Processo_AduaneiroED();

	  	String oid_Conhecimento = request.getParameter("oid_Conhecimento");
	  	if (JavaUtil.doValida(oid_Conhecimento)) {
	  		ed.setOid_Conhecimento(oid_Conhecimento);
	  	}
	  	String oid_Processo_Aduaneiro = request.getParameter("oid_Processo_Aduaneiro");
	  	if (JavaUtil.doValida(oid_Processo_Aduaneiro)) {
	  		ed.setOid_Processo_Aduaneiro(oid_Processo_Aduaneiro);
	  	}
	  	return new Processo_AduaneiroRN().getByRecord(ed);
      }

      public Processo_AduaneiroED getByRecord(String oid_Processo_Aduaneiro) throws Excecoes{
                Processo_AduaneiroED ed = new Processo_AduaneiroED();

                ed.setOid_Processo_Aduaneiro(oid_Processo_Aduaneiro);
                return new Processo_AduaneiroRN().getByRecord(ed);
      }


      public void altera(HttpServletRequest request)throws Excecoes{

          Processo_AduaneiroED ed = new Processo_AduaneiroED();
          ed = this.carregaED(request);

          new Processo_AduaneiroRN().altera(ed);
      }


  public ArrayList Lista_Processo_Aduaneiro(HttpServletRequest request)throws Excecoes{

      Processo_AduaneiroED ed = new Processo_AduaneiroED();

      ed = this.carregaED(request);

      return new Processo_AduaneiroRN().Lista(ed);

  }
  
    public Processo_AduaneiroED carregaED(HttpServletRequest request)throws Excecoes{

        Processo_AduaneiroED ed = new Processo_AduaneiroED();
        
        String oid_Conhecimento = request.getParameter("oid_Conhecimento");
        if (JavaUtil.doValida(oid_Conhecimento)) {
	  		ed.setOid_Conhecimento(oid_Conhecimento);
	  	}
        String oid_Processo_Aduaneiro = request.getParameter("oid_Processo_Aduaneiro");
        if (JavaUtil.doValida(oid_Processo_Aduaneiro)) {
	  		ed.setOid_Processo_Aduaneiro(oid_Processo_Aduaneiro);
	  	}
        
        String oid_Pessoa_Remetente = request.getParameter("oid_Pessoa_Remetente");
        String oid_Pessoa_Destinatario = request.getParameter("oid_Pessoa_Destinatario");
    	String oid_Unidade_Origem = request.getParameter("oid_Unidade_Origem");

        String DT_Emissao = request.getParameter("FT_DT_Emissao");
        if (JavaUtil.doValida(DT_Emissao)) {
	  		ed.setDT_Emissao(DT_Emissao);
	  	}
    	String HR_Emissao = request.getParameter("FT_HR_Emissao");
    	if (JavaUtil.doValida(HR_Emissao)) {
	  		ed.setHR_Emissao(HR_Emissao);
	  	}
        String NR_Fatura = request.getParameter("FT_NR_Fatura");
        if (JavaUtil.doValida(NR_Fatura)) {
	  		ed.setNR_Fatura(NR_Fatura);
	  	}
        String NR_Nota_Fiscal = request.getParameter("FT_NR_Nota_Fiscal");
        if (JavaUtil.doValida(NR_Nota_Fiscal)) {
	  		ed.setNR_Nota_Fiscal(NR_Nota_Fiscal);
	  	}
        String NR_SD = request.getParameter("FT_NR_SD");
        if (JavaUtil.doValida(NR_SD)) {
	  		ed.setNR_SD(NR_SD);
	  	}
        String NR_RE = request.getParameter("FT_NR_RE");
        if (JavaUtil.doValida(NR_RE)) {
	  		ed.setNR_RE(NR_RE);
	  	}
        String DT_Validade = request.getParameter("FT_DT_Validade");
        if (JavaUtil.doValida(DT_Validade)) {
	  		ed.setDT_Validade(DT_Validade);
	  	}
        String oid_Aduana_Destino = request.getParameter("oid_Aduana_Destino");
        if (JavaUtil.doValida(String.valueOf(oid_Aduana_Destino))) {
	  		ed.setOid_Aduana(new Integer(oid_Aduana_Destino).intValue());
	  	}
        String oid_Despachante = request.getParameter("oid_Despachante");
        if (JavaUtil.doValida(String.valueOf(oid_Despachante))) {
	  		ed.setOid_Despachante(new Integer(oid_Despachante).intValue());
	  	}
        String oid_Corretora = request.getParameter("oid_Corretora");
        if (JavaUtil.doValida(String.valueOf(oid_Corretora))) {
	  		ed.setOid_Corretora(new Integer(oid_Corretora).intValue());
	  	}
        String DT_Chegada = request.getParameter("FT_DT_Chegada");
        if (JavaUtil.doValida(DT_Chegada)) {
	  		ed.setDT_Chegada(DT_Chegada);
	  	}
        String DT_Solicitacao_Cruze = request.getParameter("FT_DT_Solicitacao_Cruze");
        if (JavaUtil.doValida(DT_Solicitacao_Cruze)) {
	  		ed.setDT_Solicitacao_Cruze(DT_Solicitacao_Cruze);
	  	}
        String DT_Liberacao = request.getParameter("FT_DT_Liberacao");
        if (JavaUtil.doValida(DT_Liberacao)) {
	  		ed.setDT_Liberacao(DT_Liberacao);
	  	}
        String DT_Lancamento = request.getParameter("FT_DT_Lancamento");
        if (JavaUtil.doValida(DT_Lancamento)) {
	  		ed.setDT_Lancamento(DT_Lancamento);
	  	}
        String DT_Cruze = request.getParameter("FT_DT_Cruze");
        if (JavaUtil.doValida(DT_Cruze)) {
	  		ed.setDT_Cruze(DT_Cruze);
	  	}
        String DT_Encerramento = request.getParameter("FT_DT_Encerramento");
        if (JavaUtil.doValida(DT_Encerramento)) {
	  		ed.setDT_Encerramento(DT_Encerramento);
	  	}
        String TX_Observacao = request.getParameter("FT_TX_Observacao");
        if (JavaUtil.doValida(TX_Observacao)) {
	  		ed.setTX_Observacao(TX_Observacao);
	  	}
        String NM_Volumes = request.getParameter("FT_NM_Volumes_Observacao");
        if (JavaUtil.doValida(NM_Volumes)) {
	  		ed.setNM_Volumes_Observacao(NM_Volumes);
	  	}

        return ed;
    }
    
    public Processo_AduaneiroED getByOID(String oid_Processo_Aduaneiro) throws Excecoes{
	  	Processo_AduaneiroED ed = new Processo_AduaneiroED();

	  	if (JavaUtil.doValida(oid_Processo_Aduaneiro)) {
	  		ed.setOid_Processo_Aduaneiro(oid_Processo_Aduaneiro);
	  	}
	  	return new Processo_AduaneiroRN().getByRecord(ed);
	  }
    
    public void Imprime(HttpServletRequest request, HttpServletResponse Response)throws Excecoes{
        Processo_AduaneiroED ed = new Processo_AduaneiroED();

        ed = this.carregaED(request);
        Processo_AduaneiroRN geRN = new Processo_AduaneiroRN();
        geRN.Imprimer(ed,Response);
      }
    
    public void deleta(HttpServletRequest request) throws Excecoes {
        	  	
        Processo_AduaneiroED ed = new Processo_AduaneiroED();
        ed = this.carregaED(request);
        new Processo_AduaneiroRN().deleta(ed);
    }



}
