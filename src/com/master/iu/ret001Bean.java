package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.Relatorio_GeralED;
import com.master.rn.Relatorio_GeralRN;
import com.master.util.EnviaPDF;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.ed.Parametro_FixoED;


public class ret001Bean extends JavaUtil implements Serializable {


  public void geraPre_Faturamento_Pellenz(HttpServletRequest request, HttpServletResponse Response)throws Excecoes{
    Relatorio_GeralED ed = new Relatorio_GeralED();

    String nr_Conhecimento = request.getParameter("FT_NR_Conhecimento");
    if (String.valueOf(nr_Conhecimento) != null && !String.valueOf(nr_Conhecimento).equals("")){
      ed.setNR_Conhecimento(new Long(nr_Conhecimento).longValue());
    }
    ed.setOID_Pessoa(request.getParameter("oid_Pessoa_Remetente"));
    ed.setOID_Pessoa_Consignatario(request.getParameter("oid_Pessoa_Consignatario"));
    ed.setOID_Pessoa_Destinatario(request.getParameter("oid_Pessoa_Destinatario"));

    String oid_Pessoa_Pagador = request.getParameter("oid_Pessoa_Pagador");
    if (String.valueOf(oid_Pessoa_Pagador) != null && !String.valueOf(oid_Pessoa_Pagador).equals("")){
      ed.setOID_Pessoa_Pagador(oid_Pessoa_Pagador);
    }

    String Dt_Emissao_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
    if (String.valueOf(Dt_Emissao_Inicial) != null && !String.valueOf(Dt_Emissao_Inicial).equals("")){
      ed.setDt_Emissao_Inicial(Dt_Emissao_Inicial);
    }

    String Dt_Emissao_Final = request.getParameter("FT_DT_Emissao_Final");
    if (String.valueOf(Dt_Emissao_Final) != null && !String.valueOf(Dt_Emissao_Final).equals("")){
      ed.setDt_Emissao_Final(Dt_Emissao_Final);
    }

    String oid_Unidade = request.getParameter("oid_Unidade");
    if (String.valueOf(oid_Unidade) != null && !String.valueOf(oid_Unidade).equals("")){
      ed.setOID_Unidade(new Long(oid_Unidade).longValue());
    }

    String Dm_Tipo_Conhecimento = request.getParameter("FT_DM_Tipo_Conhecimento");
    if (String.valueOf(Dm_Tipo_Conhecimento) != null && !String.valueOf(Dm_Tipo_Conhecimento).equals("")){
      ed.setDM_Tipo_Conhecimento(Dm_Tipo_Conhecimento);
    }

    String DM_Tipo_Cobranca = request.getParameter("FT_DM_Tipo_Cobranca");
    if (String.valueOf(DM_Tipo_Cobranca) != null && !String.valueOf(Dm_Tipo_Conhecimento).equals("")){
      ed.setDM_Tipo_Cobranca(DM_Tipo_Cobranca);
    }

    String DM_Tipo_Faturamento = request.getParameter("FT_DM_Tipo_Faturamento");
    if (String.valueOf(DM_Tipo_Faturamento) != null && !String.valueOf(DM_Tipo_Faturamento).equals("")){
      ed.setDM_Tipo_Faturamento(DM_Tipo_Faturamento);
    }

    Relatorio_GeralRN geRN = new Relatorio_GeralRN();
    new EnviaPDF().enviaBytes(request,Response,geRN.geraPre_Faturamento_Pellenz(ed));
  }

}
