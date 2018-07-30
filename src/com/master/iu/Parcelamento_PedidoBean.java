package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Parcelamento_PedidoED;
import com.master.rn.Parcelamento_PedidoRN;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class Parcelamento_PedidoBean {

  public void inclui(HttpServletRequest request)throws Excecoes{

    try{
      Parcelamento_PedidoRN Parcelamento_PedidoRN = new Parcelamento_PedidoRN();
      Parcelamento_PedidoED ed = new Parcelamento_PedidoED();
      String oid_NF = request.getParameter("oid_Pedido_Compra");
      if((oid_NF != null) && (!oid_NF.equals("")) && (!oid_NF.equals("null")))
         ed.setOID_Pedido_Compra(oid_NF);

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

      Parcelamento_PedidoRN.inclui(ed);
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


  public String gera_Parcelamento(HttpServletRequest request)throws Excecoes{


	    try{
	      Parcelamento_PedidoRN Parcelamento_PedidoRN = new Parcelamento_PedidoRN();
	      Parcelamento_PedidoED ed = new Parcelamento_PedidoED();

	      ed.setOID_Pedido_Compra(request.getParameter("oid_Pedido_Compra"));


	      String  NR_Parcelas = request.getParameter("FT_NR_Parcelas");
	      if((NR_Parcelas != null) && (!NR_Parcelas.equals("")) && (!NR_Parcelas.equals("null")) )
	         ed.setNR_Parcelas(Long.parseLong(NR_Parcelas));
	      else ed.setNR_Parcelas(1);


	      String DataP = request.getParameter("FT_DT_Pagamento");
	      if(  (DataP != null) && (!DataP.equals("")) && (!DataP.equals("null")))
	         ed.setDT_Pagamento(DataP);
	      else ed.setDT_Pagamento(Data.getDataDMY());


	      String VL_Parcela_Entrada = request.getParameter("FT_VL_Parcela_Entrada");
	      if (JavaUtil.doValida (VL_Parcela_Entrada)) {
	          ed.setVl_Parcela_Entrada (Double.parseDouble (VL_Parcela_Entrada));
	      }

	      return Parcelamento_PedidoRN.gera_Parcelamento(ed);
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


  public String altera(HttpServletRequest request)throws Excecoes{


    try{
      Parcelamento_PedidoRN Parcelamento_PedidoRN = new Parcelamento_PedidoRN();
      Parcelamento_PedidoED ed = new Parcelamento_PedidoED();


      String  oid_Parcelamento = request.getParameter("oid_Parcelamento");

      if(oid_Parcelamento != null && !oid_Parcelamento.equals("") && !oid_Parcelamento.equals("null"))
         ed.setOID_Parcelamento(Long.parseLong(oid_Parcelamento));


      ed.setDT_Pagamento(request.getParameter("FT_DT_Pagamento"));


      ed.setDt_stamp(Data.getDataDMY());
      ed.setDm_Stamp(" ");

      return Parcelamento_PedidoRN.altera(ed);
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
      Parcelamento_PedidoRN Parcelamento_PedidoRN = new Parcelamento_PedidoRN();
      Parcelamento_PedidoED ed = new Parcelamento_PedidoED();
      ed.setOID_Parcelamento(Long.parseLong(request.getParameter("oid_Parcelamento")));
      Parcelamento_PedidoRN.deleta(ed);
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

  public Parcelamento_PedidoED getByRecord(HttpServletRequest request)throws Excecoes{

      Parcelamento_PedidoED ed = new Parcelamento_PedidoED();

      String  oid_Parcelamento = request.getParameter("oid_Parcelamento");

      System.out.println("oid_Parcelamento=" + oid_Parcelamento);

      if(oid_Parcelamento != null && !oid_Parcelamento.equals("") && !oid_Parcelamento.equals("null"))
         ed.setOID_Parcelamento(Long.parseLong(oid_Parcelamento));

      return new Parcelamento_PedidoRN().getByRecord(ed);

  }

    public ArrayList Lista(HttpServletRequest request)throws Excecoes{

      Parcelamento_PedidoED ed = new Parcelamento_PedidoED();
       ed.setOID_Pedido_Compra(request.getParameter("oid_Pedido_Compra"));
      return new Parcelamento_PedidoRN().lista(ed);
  }



  }