package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Lote_NFED;
import com.master.rn.Lote_NFRN;
import com.master.util.Data;
import com.master.util.Excecoes;

       public class con101Bean {

       private Data dt_stamp = new Data();

  public Lote_NFED inclui(HttpServletRequest request)throws Excecoes{

    try{
    //// System.out.println("Beannnnnnnnnnnnnnnn!!!!!!!!!!!");
      Lote_NFRN Lote_NFrn = new Lote_NFRN();
      Lote_NFED ed = new Lote_NFED();

      //request em cima dos campos dos forms html

      //ed.setOid_Lote(Long.parseLong(request.getParameter("oid_lote")));
      ed.setOid_Unidade(Long.parseLong(request.getParameter("oid_Unidade")));
      //// System.out.println(ed.getOid_Unidade());
      ed.setOid_Pessoa(request.getParameter("oid_Pessoa"));
      //// System.out.println(ed.getOid_Pessoa());
      ed.setNr_Lote(request.getParameter("FT_NR_Lote"));
      ed.setDt_Cadastro(request.getParameter("FT_DT_Lote"));
      ed.setVl_Lote(Double.parseDouble(request.getParameter("FT_VL_Mercadoria")));
      //// System.out.println(ed.getVl_Lote());
      ed.setNr_Volumes(Long.parseLong(request.getParameter("FT_QT_Volumes")));
      //// System.out.println("passei");
      ed.setVl_Total_Frete(Double.parseDouble(request.getParameter("FT_VL_Frete")));
      //// System.out.println("também");
      ed.setUsuario_stamp(request.getParameter("FT_NM_Usuario"));
      //// System.out.println(ed.getUsuario_stamp());
      ed.setDt_Stamp(dt_stamp.getDataDMY());

      return Lote_NFrn.inclui(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("erro ao incluir o Lote");
      excecoes.setMetodo("inclui");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public ArrayList getByOid(HttpServletRequest request) throws Excecoes{

	 Lote_NFED nf = new Lote_NFED();

	 String oid_lote = request.getParameter("oid_Lote");
//// System.out.println("bean " +oid_lote);
         if (String.valueOf(oid_lote) != null && String.valueOf(oid_lote).length() > 0)
         {
	    nf.setOid_Lote(Long.parseLong(request.getParameter("oid_Pessoa")));
        }

        return new Lote_NFRN().getByOid(nf);

  }

  public Lote_NFED getByRecord(String oid) throws Excecoes{

	 Lote_NFED nf = new Lote_NFED();

	 String oid_lote = oid;
//// System.out.println("bean " +oid_lote);
         if (String.valueOf(oid_lote) != null && String.valueOf(oid_lote).length() > 0)
         {
	    nf.setOid_Lote(Long.parseLong(oid_lote));
        }

        return new Lote_NFRN().getByRecord(nf);

  }

   public Lote_NFED getByCD(String cd) throws Excecoes{

	 Lote_NFED nf = new Lote_NFED();

//// System.out.println("bean " +cd);
         if (String.valueOf(cd) != null && String.valueOf(cd).length() > 0)
         {
	    nf.setNr_Lote(cd);
        }

        return new Lote_NFRN().getByCD(nf);

  }

  public boolean deleta(HttpServletRequest request)throws Excecoes{

    try{
      Lote_NFRN rn = new Lote_NFRN();
      Lote_NFED ed = new Lote_NFED();
      //request em cima dos campos dos forms html

      String oid_lote = request.getParameter("oid_Lote");
//// System.out.println("delete: " +oid_lote);
         if (String.valueOf(oid_lote) != null && String.valueOf(oid_lote).length() > 0)
         {
	    ed.setOid_Lote(Long.parseLong(request.getParameter("oid_Lote")));
        }
      return rn.deleta(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("erro ao deletar");
      excecoes.setMetodo("deleta");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void update(HttpServletRequest request)throws Excecoes{

    try{
      Lote_NFRN rn = new Lote_NFRN();
      Lote_NFED ed = new Lote_NFED();

      //request em cima dos campos dos forms html

      ed.setOid_Lote(Long.parseLong(request.getParameter("oid_Lote")));
      ed.setOid_Unidade(Long.parseLong(request.getParameter("oid_Unidade")));
    //// System.out.println(ed.getOid_Unidade());
      ed.setOid_Pessoa(request.getParameter("oid_Pessoa"));
    //// System.out.println(ed.getOid_Pessoa());
      ed.setNr_Lote(request.getParameter("FT_NR_Lote"));
      ed.setDt_Cadastro(request.getParameter("FT_DT_Lote"));
      ed.setVl_Lote(Double.parseDouble(request.getParameter("FT_VL_Mercadoria")));
    //// System.out.println(ed.getVl_Lote());
      ed.setNr_Volumes(Long.parseLong(request.getParameter("FT_QT_Volumes")));
    //// System.out.println("passei");
      ed.setVl_Total_Frete(Double.parseDouble(request.getParameter("FT_VL_Frete")));
    //// System.out.println("também");
      ed.setUsuario_stamp(request.getParameter("FT_NM_Usuario"));
    //// System.out.println(ed.getUsuario_stamp());
      ed.setDt_Stamp(dt_stamp.getDataDMY());

      rn.update(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("erro ao alterar o Lote");
      excecoes.setMetodo("update");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public ArrayList lista(HttpServletRequest request)throws Excecoes{

  ArrayList lista = new ArrayList();

    try{
      Lote_NFRN rn = new Lote_NFRN();
      Lote_NFED ed = new Lote_NFED();

      //request em cima dos campos dos forms html
//// System.out.println("bean 1");
      ed.setNr_Lote(request.getParameter("FT_NR_Lote"));
//// System.out.println("bean 2");
      ed.setDt_Cadastro(request.getParameter("FT_DT_Lote"));
//// System.out.println("bean 3");
      ed.setUsuario_stamp(request.getParameter("FT_NM_Usuario"));
//// System.out.println("bean 4");
      lista = rn.lista(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("erro ao alterar o Lote");
      excecoes.setMetodo("update");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return lista;
  }
}