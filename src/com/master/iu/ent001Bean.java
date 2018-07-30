package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Romaneio_EntregaED;
import com.master.rn.Romaneio_EntregaRN;
import com.master.util.Excecoes;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class ent001Bean {

  public Romaneio_EntregaED inclui(HttpServletRequest request)throws Excecoes{

    try{
      Romaneio_EntregaRN Romaneio_Entregarn = new Romaneio_EntregaRN();
      Romaneio_EntregaED ed = new Romaneio_EntregaED();

      //request em cima dos campos dos forms html

      ed.setOID_Pessoa(request.getParameter("oid_Pessoa"));
      ed.setOID_Veiculo(request.getParameter("oid_Veiculo"));
      ed.setOID_Unidade(new Long(request.getParameter("oid_Unidade")).longValue());
      ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));
      ed.setDT_Previsao_Saida(request.getParameter("FT_DT_Previsao_Saida"));
      ed.setHR_Previsao_Saida(request.getParameter("FT_HR_Previsao_Saida"));
      ed.setTX_Observacao(request.getParameter("FT_TX_Observacao"));

      String NR_Odometro_Inicial = request.getParameter("FT_NR_Odometro_Inicial");
      if (String.valueOf(NR_Odometro_Inicial) != null &&
          !String.valueOf(NR_Odometro_Inicial).equals("")){
        ed.setNR_Odometro_Inicial(new Long(NR_Odometro_Inicial).longValue());
      }

      return Romaneio_Entregarn.inclui(ed);
    }

    catch (Excecoes exc){
      throw exc;
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("erro ao incluir");
      excecoes.setMetodo("inclui");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void altera(HttpServletRequest request)throws Excecoes{

    try{
      Romaneio_EntregaRN Romaneio_Entregarn = new Romaneio_EntregaRN();
      Romaneio_EntregaED ed = new Romaneio_EntregaED();


      ed.setOID_Romaneio_Entrega(request.getParameter("oid_Romaneio_Entrega"));

      ed.setOID_Pessoa(request.getParameter("oid_Pessoa"));
      ed.setOID_Veiculo(request.getParameter("oid_Veiculo"));
      ed.setOID_Unidade(new Long(request.getParameter("oid_Unidade")).longValue());
      ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));
      ed.setDT_Previsao_Chegada(request.getParameter("FT_DT_Previsao_Chegada"));
      ed.setHR_Previsao_Chegada(request.getParameter("FT_HR_Previsao_Chegada"));
      ed.setDT_Previsao_Saida(request.getParameter("FT_DT_Previsao_Saida"));
      ed.setHR_Previsao_Saida(request.getParameter("FT_HR_Previsao_Saida"));
      ed.setTX_Observacao(request.getParameter("FT_TX_Observacao"));

      String NR_Odometro_Inicial = request.getParameter("FT_NR_Odometro_Inicial");
      if (String.valueOf(NR_Odometro_Inicial) != null &&
          !String.valueOf(NR_Odometro_Inicial).equals("")){
        ed.setNR_Odometro_Inicial(new Long(NR_Odometro_Inicial).longValue());
      }
      String NR_Odometro_Final = request.getParameter("FT_NR_Odometro_Final");
      if (String.valueOf(NR_Odometro_Final) != null &&
          !String.valueOf(NR_Odometro_Final).equals("")){
        ed.setNR_Odometro_Final(new Long(NR_Odometro_Final).longValue());
      }

      Romaneio_Entregarn.altera(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("erro ao alterar");
      excecoes.setMetodo("alterar");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void deleta(HttpServletRequest request)throws Excecoes{

    try{
      Romaneio_EntregaRN Romaneio_Entregarn = new Romaneio_EntregaRN();
      Romaneio_EntregaED ed = new Romaneio_EntregaED();

      ed.setOID_Romaneio_Entrega(request.getParameter("oid_Romaneio_Entrega"));

      Romaneio_Entregarn.deleta(ed);
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

  public ArrayList Romaneio_Entrega_Lista(HttpServletRequest request)throws Excecoes{

      Romaneio_EntregaED ed = new Romaneio_EntregaED();

      ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));
      String nr_Romaneio_Entrega = request.getParameter("FT_NR_Romaneio_Entrega");
      if (String.valueOf(nr_Romaneio_Entrega) != null &&
        !String.valueOf(nr_Romaneio_Entrega).equals("") &&
        !String.valueOf(nr_Romaneio_Entrega).equals("null")){
        ed.setNR_Romaneio_Entrega(new Long(nr_Romaneio_Entrega).longValue());
      }

      ed.setOID_Pessoa(request.getParameter("oid_Pessoa"));

      ed.setOID_Veiculo(request.getParameter("oid_Veiculo"));

      String oid_Unidade = request.getParameter("oid_Unidade");
      if (String.valueOf(oid_Unidade) != null &&
        !String.valueOf(oid_Unidade).equals("") &&
        !String.valueOf(oid_Unidade).equals("null")){
        ed.setOID_Unidade(new Long(oid_Unidade).longValue());
      }

      return new Romaneio_EntregaRN().lista(ed);

  }

  public Romaneio_EntregaED getByRecord(HttpServletRequest request)throws Excecoes{

      Romaneio_EntregaED ed = new Romaneio_EntregaED();

      String NR_Romaneio_Entrega = request.getParameter("FT_NR_Romaneio_Entrega");
      if (String.valueOf(NR_Romaneio_Entrega) != null &&
          !String.valueOf(NR_Romaneio_Entrega).equals("") &&
          !String.valueOf(NR_Romaneio_Entrega).equals("null")){
        ed.setNR_Romaneio_Entrega(new Long(request.getParameter("FT_NR_Romaneio_Entrega")).longValue());
        ed.setOID_Unidade(new Long(request.getParameter("oid_Unidade")).longValue());
      }

      String oid_Romaneio_Entrega = request.getParameter("oid_Romaneio_Entrega");

      if (String.valueOf(oid_Romaneio_Entrega) != null &&
          !String.valueOf(oid_Romaneio_Entrega).equals("") &&
          !String.valueOf(oid_Romaneio_Entrega).equals("null")){
        ed.setOID_Romaneio_Entrega(request.getParameter("oid_Romaneio_Entrega"));
      }

      return new Romaneio_EntregaRN().getByRecord(ed);

  }

  public void geraRelatorio(HttpServletRequest req)throws Excecoes{
    Romaneio_EntregaED ed = new Romaneio_EntregaED();

    /* * * * * *         A T E N Ç Ã O     * * * * * * *
    /* SETAR O ED PARA PESQUISA NO BD
    */


    Romaneio_EntregaRN geRN = new Romaneio_EntregaRN();
    geRN.geraRelatorio(ed);
  }

}