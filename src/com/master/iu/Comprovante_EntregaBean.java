package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.Comprovante_EntregaED;
import com.master.rn.Comprovante_EntregaRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Data;
import com.master.util.Mensagens;
import com.master.util.Utilitaria;

public class Comprovante_EntregaBean {
    Utilitaria util = new Utilitaria();

  public void altera(HttpServletRequest request)throws Excecoes{
      Comprovante_EntregaED ed = new Comprovante_EntregaED();

      ed.setOID_Comprovante_Entrega(request.getParameter("oid_Comprovante_Entrega"));

      new Comprovante_EntregaRN().altera(ed);
  }


  public void deleta(HttpServletRequest request)throws Excecoes{

    try{
      Comprovante_EntregaRN Comprovante_Entregarn = new Comprovante_EntregaRN();
      Comprovante_EntregaED ed = new Comprovante_EntregaED();

      ed.setOID_Comprovante_Entrega(request.getParameter("oid_Comprovante_Entrega"));

      Comprovante_Entregarn.deleta(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("erro ao excluir");
      excecoes.setMetodo("excluir");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public ArrayList Comprovante_Entrega_Lista(HttpServletRequest request)throws Excecoes{

      Comprovante_EntregaED ed = new Comprovante_EntregaED();
//// System.out.println("1 ");

       String DT_Emissao = request.getParameter("FT_DT_Emissao");
        if (String.valueOf(DT_Emissao) != null &&
            !String.valueOf(DT_Emissao).equals("null") &&
            !String.valueOf(DT_Emissao).equals("")){
            ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));
        }


      String nr_Comprovante_Entrega = request.getParameter("FT_NR_Comprovante_Entrega");
      if (String.valueOf(nr_Comprovante_Entrega) != null &&
        !String.valueOf(nr_Comprovante_Entrega).equals("") &&
        !String.valueOf(nr_Comprovante_Entrega).equals("null")){
        ed.setNR_Comprovante_Entrega(new Long(nr_Comprovante_Entrega).longValue());
      }

//// System.out.println("2 ");

      String oid_Unidade = request.getParameter("oid_Unidade");
      if (String.valueOf(oid_Unidade) != null &&
        !String.valueOf(oid_Unidade).equals("") &&
        !String.valueOf(oid_Unidade).equals("null")){
        ed.setOID_Unidade(new Long(oid_Unidade).longValue());
      }
//// System.out.println("5 ");

      return new Comprovante_EntregaRN().lista(ed);

  }


  public Comprovante_EntregaED inclui(HttpServletRequest request)
  throws Excecoes{
          Comprovante_EntregaED ed = new Comprovante_EntregaED();

          // System.out.println("man 0" );
          ed.setOID_Unidade(new Long(request.getParameter("oid_Unidade")).longValue());
          ed.setDT_Emissao(Data.getDataDMY());
          if (request.getParameter("FT_DT_Emissao") !=null && request.getParameter("FT_DT_Emissao").length()>4){
            ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));
          }

          ed.setTX_Observacao(" ");
          String TX_Observacao = request.getParameter("FT_TX_Observacao");
          if (String.valueOf(TX_Observacao) != null &&
                  !String.valueOf(TX_Observacao).equals("null") &&
                  !String.valueOf(TX_Observacao).equals("")){
              ed.setTX_Observacao(request.getParameter("FT_TX_Observacao"));
          }
          // System.out.println("man 14" );

          return new Comprovante_EntregaRN().inclui(ed);
  }

  public Comprovante_EntregaED getByRecord(HttpServletRequest request)throws Excecoes{

      Comprovante_EntregaED ed = new Comprovante_EntregaED();

      String NR_Comprovante_Entrega = request.getParameter("FT_NR_Comprovante_Entrega");
      if (String.valueOf(NR_Comprovante_Entrega) != null &&
          !String.valueOf(NR_Comprovante_Entrega).equals("") &&
          !String.valueOf(NR_Comprovante_Entrega).equals("null")){
        ed.setNR_Comprovante_Entrega(new Long(request.getParameter("FT_NR_Comprovante_Entrega")).longValue());
      }

      String oid_Unidade = request.getParameter("oid_Unidade");
      if (String.valueOf(oid_Unidade) != null &&
          !String.valueOf(oid_Unidade).equals("") &&
          !String.valueOf(oid_Unidade).equals("null")){
         ed.setOID_Unidade(new Long(request.getParameter("oid_Unidade")).longValue());
      }

      String oid_Comprovante_Entrega = request.getParameter("oid_Comprovante_Entrega");

      if (String.valueOf(oid_Comprovante_Entrega) != null &&
          !String.valueOf(oid_Comprovante_Entrega).equals("") &&
          !String.valueOf(oid_Comprovante_Entrega).equals("null")){
        ed.setOID_Comprovante_Entrega(request.getParameter("oid_Comprovante_Entrega"));
      }

      if (request.getParameter("MIC") != null && request.getParameter("MIC").equals("S")){
            return new Comprovante_EntregaRN().getByRecord(ed);
      } else{
		    return new Comprovante_EntregaRN().getByRecord(ed);
      }

  }

  public Comprovante_EntregaED getByRecord(String oid_Comprovante_Entrega)throws Excecoes{

      Comprovante_EntregaED ed = new Comprovante_EntregaED();

      if (String.valueOf(oid_Comprovante_Entrega) != null &&
          !String.valueOf(oid_Comprovante_Entrega).equals("") &&
          !String.valueOf(oid_Comprovante_Entrega).equals("null")){
        ed.setOID_Comprovante_Entrega(oid_Comprovante_Entrega);
      }
      return new Comprovante_EntregaRN().getByRecord(ed);

  }

  public byte[] imprime_Comprovante_Entrega(HttpServletRequest request, HttpServletResponse Response)
  throws Excecoes {
      String oid_Comprovante_Entrega = request.getParameter("oid_Comprovante_Entrega");
      Comprovante_EntregaED filtro = new Comprovante_EntregaED();
      if (JavaUtil.doValida(oid_Comprovante_Entrega)) {
          filtro.setOID_Comprovante_Entrega(oid_Comprovante_Entrega);
      } else throw new Mensagens("Informe o Comprovante_Entrega!");
      return new Comprovante_EntregaRN().imprime_Comprovante_Entrega(filtro);
  }


  public Comprovante_EntregaED inclui_Exclui_Documento(HttpServletRequest request, String acao)
  throws Excecoes{
          Comprovante_EntregaED ed = new Comprovante_EntregaED();

          // System.out.println("man 0" );
          ed.setOID_Comprovante_Entrega(request.getParameter("oid_Comprovante_Entrega"));
          ed.setOID_Conhecimento(request.getParameter("oid_Conhecimento"));
          ed.setDM_Acao(acao);

          return new Comprovante_EntregaRN().inclui_Exclui_Documento(ed);
  }

  public ArrayList lista_Conhecimento_Entregue(HttpServletRequest request)throws Excecoes{

      Comprovante_EntregaED ed = new Comprovante_EntregaED();

      ed.setOID_Comprovante_Entrega(request.getParameter("oid_Comprovante_Entrega"));
// System.out.println("5 ");

      return new Comprovante_EntregaRN().lista_Conhecimento_Entregue(ed);

  }


}
