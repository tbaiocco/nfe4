package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.Ordem_PagamentoED;
import com.master.rn.Ordem_PagamentoRN;
import com.master.util.EnviaPDF;
import com.master.util.Excecoes;




/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class Ordem_PagamentoBean {



  public void Relatorio_Ordem_Pagamento(HttpServletRequest request, HttpServletResponse Response)throws Excecoes{
    Ordem_PagamentoED ed = new Ordem_PagamentoED();

      //// System.out.println("BEAN");


      String OID_Ordem_Pagamento = request.getParameter("oid_Ordem_Pagamento");
      if (String.valueOf(OID_Ordem_Pagamento) != null &&
          !String.valueOf(OID_Ordem_Pagamento).equals("") &&
          !String.valueOf(OID_Ordem_Pagamento).equals("null")){
          ed.setOID_Ordem_Pagamento(new Long(OID_Ordem_Pagamento).longValue());
      }
      //// System.out.println("BEAN 2");

      String oid_Pessoa = request.getParameter("oid_Pessoa");
      if (String.valueOf(oid_Pessoa) != null && !String.valueOf(oid_Pessoa).equals("") && !String.valueOf(oid_Pessoa).equals("null") ){
        ed.setOID_Pessoa(oid_Pessoa);
      }

      //// System.out.println("BEAN 3");

      String DT_Emissao_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
      if (String.valueOf(DT_Emissao_Inicial) != null && !String.valueOf(DT_Emissao_Inicial).equals("")){
        ed.setDT_Emissao_Inicial(DT_Emissao_Inicial);
      }
      //// System.out.println("BEAN 4");

      String DT_Emissao_Final = request.getParameter("FT_DT_Emissao_Final");
      if (String.valueOf(DT_Emissao_Final) != null && !String.valueOf(DT_Emissao_Final).equals("")){
        ed.setDT_Emissao_Final(DT_Emissao_Final);
      }
      //// System.out.println("BEAN 5");

      String NR_Placa = request.getParameter("FT_NR_Placa");
      if (String.valueOf(NR_Placa) != null && !String.valueOf(NR_Placa).equals("")){
        ed.setNR_Placa(NR_Placa);
      }
      //// System.out.println("BEAN 6");

      String DM_Tipo_Pagamento = request.getParameter("FT_DM_Tipo_Pagamento");
      if (String.valueOf(DM_Tipo_Pagamento) != null && !String.valueOf(DM_Tipo_Pagamento).equals("")){
        ed.setDM_Tipo_Pagamento(DM_Tipo_Pagamento);
      }
      //// System.out.println("BEAN 7");

      String oid_Unidade = request.getParameter("oid_Unidade");
      if (String.valueOf(oid_Unidade) != null && !String.valueOf(oid_Unidade).equals("")){
        ed.setOID_Unidade(new Long(oid_Unidade).longValue());
      }
      //// System.out.println("BEAN 81");

      String oid_Tipo_Pagamento = request.getParameter("oid_Tipo_Pagamento");
      if (String.valueOf(oid_Tipo_Pagamento) != null && !String.valueOf(oid_Tipo_Pagamento).equals("")){
        ed.setOID_Tipo_Pagamento(new Long(oid_Tipo_Pagamento).longValue());
      }
      //// System.out.println("BEAN 82");


      String oid_Empresa = request.getParameter("oid_Empresa");

                //// System.out.println("oid_Empresa" + oid_Empresa );

        if (String.valueOf(oid_Empresa) != null &&
            !String.valueOf(oid_Empresa).equals("") &&
            !String.valueOf(oid_Empresa).equals("null")){
           ed.setOID_Empresa(new Long(request.getParameter("oid_Empresa")).longValue());
        }

        ed.setNM_Empresa(request.getParameter("FT_NM_Empresa"));
              //// System.out.println("FT_NM_Empresa" +  ed.getNM_Empresa() );



        ed.setDM_Relatorio(request.getParameter("FT_DM_Relatorio"));
      //// System.out.println("BEAN 10");


    Ordem_PagamentoRN geRN = new Ordem_PagamentoRN();
    new EnviaPDF().enviaBytes(request,Response,geRN.Relatorio_Ordem_Pagamento(ed));
  }


  public void deleta_Movimento(HttpServletRequest request)throws Excecoes{

    try{
      Ordem_PagamentoRN Ordem_PagamentoRN = new Ordem_PagamentoRN();
      Ordem_PagamentoED ed = new Ordem_PagamentoED();

      ed.setOID_Movimento_Ordem_Pagamento(new Long(request.getParameter("oid_Movimento_Ordem_Pagamento")).longValue());

      Ordem_PagamentoRN.deleta_Movimento(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
  }


  public  byte[] imprime_Requisicao(HttpServletRequest request, HttpServletResponse Response)throws Excecoes{

//// System.out.println("a 0");
    Ordem_PagamentoED ed = new Ordem_PagamentoED();


      String OID_Ordem_Pagamento = request.getParameter("oid_Ordem_Pagamento");
      if (String.valueOf(OID_Ordem_Pagamento) != null &&
          !String.valueOf(OID_Ordem_Pagamento).equals("") &&
          !String.valueOf(OID_Ordem_Pagamento).equals("null")){
          ed.setOID_Ordem_Pagamento(new Long(OID_Ordem_Pagamento).longValue());
      }
      ed.setDM_Relatorio(request.getParameter("FT_DM_Tipo_Servico"));

      // System.out.println("FT_DM_Tipo_Servico->" + request.getParameter("FT_DM_Tipo_Servico"));

    Ordem_PagamentoRN geRN = new Ordem_PagamentoRN();

    return geRN.imprime_Requisicao(ed);

  }


}