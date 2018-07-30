package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Parcelamento_FinanceiroED;
import com.master.rn.Parcelamento_FinanceiroRN;
import com.master.util.Data;
import com.master.util.Excecoes;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class est006Bean {

  public void inclui(HttpServletRequest request)throws Excecoes{

    try{
      Parcelamento_FinanceiroRN Parcelamento_FinanceiroRN = new Parcelamento_FinanceiroRN();
      Parcelamento_FinanceiroED ed = new Parcelamento_FinanceiroED();
      String oid_NF = request.getParameter("oid_Nota_Fiscal");
      if((oid_NF != null) && (!oid_NF.equals("")) && (!oid_NF.equals("null")))
         ed.setOID_Nota_Fiscal(oid_NF);

      String  oid_Pagto = request.getParameter("oid_Parcelamento");

      if((oid_Pagto != null) && (!oid_Pagto.equals("")) && (!oid_Pagto.equals("null")) )
         ed.setOID_Parcelamento(Long.parseLong(oid_Pagto));


      String  Valor = request.getParameter("FT_VL_Parcela");
      if((Valor != null) && (!Valor.equals("")) && (!Valor.equals("null")))
         ed.setVL_Parcela(Double.parseDouble(Valor));
      else ed.setVL_Parcela(0);

      String Numero = request.getParameter("FT_NR_Parcelamento");
      if((Numero != null) && (!Numero.equals("")) && (!Numero.equals("null")) )
         ed.setNR_Parcelamento(Long.parseLong(Numero));
      else ed.setNR_Parcelamento(0);


      String DataP = request.getParameter("FT_DT_Pagamento");
      if((DataP != null) && (!DataP.equals("")) && (!DataP.equals("null")) )
         ed.setDT_Pagamento(DataP);
      else ed.setDT_Pagamento(Data.getDataDMY());

      ed.setDt_stamp(Data.getDataDMY());
      ed.setDm_Stamp(" ");

      Parcelamento_FinanceiroRN.inclui(ed);
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


  public String altera(HttpServletRequest request)throws Excecoes{


    try{
      Parcelamento_FinanceiroRN Parcelamento_FinanceiroRN = new Parcelamento_FinanceiroRN();
      Parcelamento_FinanceiroED ed = new Parcelamento_FinanceiroED();


      String  oid_Pagto = request.getParameter("oid_Parcelamento");

      if((oid_Pagto != null) && (!oid_Pagto.equals("")) && (!oid_Pagto.equals("null")))
         ed.setOID_Parcelamento(Long.parseLong(oid_Pagto));


      String  Valor = request.getParameter("FT_VL_Parcela");
      if((Valor != null) && (!Valor.equals("")) && (!Valor.equals("null")) )
         ed.setVL_Parcela(Double.parseDouble(Valor));
      else ed.setVL_Parcela(0);


      String DataP = request.getParameter("FT_DT_Pagamento");
      if(  (DataP != null) && (!DataP.equals("")) && (!DataP.equals("null")))
         ed.setDT_Pagamento(DataP);
      else ed.setDT_Pagamento(Data.getDataDMY());


      ed.setDt_stamp(Data.getDataDMY());
      ed.setDm_Stamp(" ");

      return Parcelamento_FinanceiroRN.altera(ed);
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
      Parcelamento_FinanceiroRN Parcelamento_FinanceiroRN = new Parcelamento_FinanceiroRN();
      Parcelamento_FinanceiroED ed = new Parcelamento_FinanceiroED();
      ed.setOID_Parcelamento(Long.parseLong(request.getParameter("oid_Parcelamento")));
      Parcelamento_FinanceiroRN.deleta(ed);
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

  public Parcelamento_FinanceiroED getByRecord(HttpServletRequest request)throws Excecoes{

      Parcelamento_FinanceiroED ed = new Parcelamento_FinanceiroED();

      String oid_NF = request.getParameter("oid_Nota_Fiscal");
      if((!oid_NF.equals("")) && (!oid_NF.equals("null")) && (oid_NF != null))
         ed.setOID_Nota_Fiscal(oid_NF);

      String  oid_Pagto = request.getParameter("oid_Parcelamento");
      if((!oid_Pagto.equals("")) && (!oid_Pagto.equals("null")) && (oid_Pagto != null))
         ed.setOID_Parcelamento(Long.parseLong(oid_Pagto));

      return new Parcelamento_FinanceiroRN().getByRecord(ed);

  }

    public ArrayList Lista(HttpServletRequest request)throws Excecoes{

      Parcelamento_FinanceiroED ed = new Parcelamento_FinanceiroED();
       ed.setOID_Nota_Fiscal(request.getParameter("oid_Nota_Fiscal"));
      return new Parcelamento_FinanceiroRN().lista(ed);
  }

    public double checkSoma(String nota)throws Excecoes{

      Parcelamento_FinanceiroED ed = new Parcelamento_FinanceiroED();
      return new Parcelamento_FinanceiroRN().checkSoma(nota);
  }

    public Parcelamento_FinanceiroED getByRecord_Compra(HttpServletRequest request)throws Excecoes{

        Parcelamento_FinanceiroED ed = new Parcelamento_FinanceiroED();

        String oid_NF = request.getParameter("oid_Nota_Fiscal");
        if((!oid_NF.equals("")) && (!oid_NF.equals("null")) && (oid_NF != null))
           ed.setOID_Nota_Fiscal(oid_NF);

        String  oid_Pagto = request.getParameter("oid_Parcelamento");
        if((!oid_Pagto.equals("")) && (!oid_Pagto.equals("null")) && (oid_Pagto != null))
           ed.setOID_Parcelamento(Long.parseLong(oid_Pagto));

        return new Parcelamento_FinanceiroRN().getByRecord_Compra(ed);

    }


  }