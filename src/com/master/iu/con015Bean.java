package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.EventoED;
import com.master.rn.EventoRN;
import com.master.util.Excecoes;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class con015Bean {

  public EventoED inclui(EventoED ed)throws Excecoes{

    try{
      EventoRN Eventorn = new EventoRN();

      return Eventorn.inclui(ed);
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

  public void altera(EventoED ed)throws Excecoes{

    try{
      EventoRN Eventorn = new EventoRN();
      Eventorn.altera(ed);
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

  public void deleta(EventoED ed)throws Excecoes{

    try{
      EventoRN Eventorn = new EventoRN();

      Eventorn.deleta(ed);
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

  public ArrayList Evento_Lista(HttpServletRequest request)throws Excecoes{
      EventoED ed = new EventoED();
      String sugestao = request.getParameter("FT_CD_Sugestao");
      ed.setOid_sugestao_contabil(Long.parseLong(sugestao));

      return new EventoRN().lista(ed);
  }

  public EventoED getByRecord(HttpServletRequest request)throws Excecoes{

      EventoED ed = new EventoED();
      String oid_Evento = request.getParameter("FT_CD_Evento");
      if (String.valueOf(oid_Evento) != null &&
          !String.valueOf(oid_Evento).equals("") &&
          !String.valueOf(oid_Evento).equals("null")){
        ed.setOid_evento_contabil(new Long(oid_Evento).longValue());
      }

      return new EventoRN().getByRecord(ed);

  }

  public EventoED getByOid(String Oid)throws Excecoes{

      return new EventoRN().getByOid(Oid);
  }



    public ArrayList getByNM_Evento(HttpServletRequest request)throws Excecoes{

      EventoED ed = new EventoED();
      String nm_Evento = request.getParameter("FT_NM_Evento");

      if (nm_Evento != null &&
          !nm_Evento.equals("") &&
          !nm_Evento.equals("null")){
        ed.setNm_evento_contabil(nm_Evento);
      }

      return new EventoRN().getByNM_Evento(ed);

  }


}