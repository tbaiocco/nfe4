package com.master.iu;

/**
 * <p>Title: cpg020Bean </p>
 * <p>Description: Inclui, altera, exclui e gerencia saldos para programacao e/ou emissao de cheques.
 * Controla Limite de pgtos por dia. </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: ExitoLogistica & MasterCOM </p>
 * @author Teofilo Poletto Baiocco
 * @version 1.0
 */


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.Lote_PagamentoED;
import com.master.ed.Saldo_LoteED;
import com.master.rn.Lote_PagamentoRN;
import com.master.rn.Saldo_LoteRN;
import com.master.util.EnviaPDF;
import com.master.util.Excecoes;
import com.master.util.ed.Parametro_FixoED;

public class Cpg020Bean {

  public Saldo_LoteED inclui(HttpServletRequest request)throws Excecoes{

      try{
        Saldo_LoteRN Saldo_LoteRN = new Saldo_LoteRN();
        Saldo_LoteED ed = new Saldo_LoteED();
        ed.setDT_Saldo(request.getParameter("FT_DT_Saldo"));
        String Saldo = request.getParameter("FT_VL_Saldo");
        if (Saldo != null && !Saldo.equals("null") && !Saldo.equals("0")){
            ed.setVL_Saldo(new Double(Saldo).doubleValue());
        }
       return Saldo_LoteRN.inclui(ed);
      }
      catch (Excecoes exc){
        throw exc;
      }
    }
  
  public void altera(HttpServletRequest request)throws Excecoes{

      try{
        Saldo_LoteRN Saldo_LoteRN = new Saldo_LoteRN();
        Saldo_LoteED ed = new Saldo_LoteED();
        String oid = request.getParameter("oid_Saldo");
        if (oid != null && !oid.equals("")){
            ed.setOid_Saldo(new Long(oid).longValue());            
        }
        ed.setVL_Saldo(new Double(request.getParameter("FT_VL_Saldo")).doubleValue());
        Saldo_LoteRN.altera(ed);
      }
      catch (Excecoes exc){
        throw exc;
      }
    }
  
  public void deleta(HttpServletRequest request)throws Excecoes{

      try{
        Saldo_LoteRN Saldo_LoteRN = new Saldo_LoteRN();
        Saldo_LoteED ed = new Saldo_LoteED();
        String oid = request.getParameter("oid_Saldo");
        if (oid != null && !oid.equals("")){
            ed.setOid_Saldo(new Long(oid).longValue());            
        }
        Saldo_LoteRN.deleta(ed);
      }
      catch (Excecoes exc){
        throw exc;
      }
    }
  
  public Saldo_LoteED getByRecord(HttpServletRequest request)throws Excecoes{
        Saldo_LoteED ed = new Saldo_LoteED();
        String oid_Caixa = request.getParameter("oid_Caixa");
        String oid = request.getParameter("oid_Saldo");
        if (oid != null && !oid.equals("")){
            ed.setOid_Saldo(new Long(oid).longValue());            
        }
        return new Saldo_LoteRN().getByRecord(ed);
    }
  
  public ArrayList lista(HttpServletRequest request)throws Excecoes{

      Saldo_LoteED ed = new Saldo_LoteED();
        ed.setDT_Inicial(request.getParameter("FT_DT_Saldo"));
        ed.setDT_Final(request.getParameter("FT_DT_Saldo_Final"));
      return new Saldo_LoteRN().lista(ed);

    }
  
  public Saldo_LoteED getTotalProgramado(String data)throws Excecoes{
      Saldo_LoteED ed = new Saldo_LoteED();
      Saldo_LoteED edVolta = new Saldo_LoteED();
      if(Parametro_FixoED.getInstancia().getDM_Saldo_Programado() != null &&
              Parametro_FixoED.getInstancia().getDM_Saldo_Programado().equals("S")){
          ed.setDT_Saldo(data);
          edVolta = new Saldo_LoteRN().getTotalProgramado(ed);
      }else {
          edVolta.setVL_Saldo(999999999999.99);
          edVolta.setVL_Programado(0.0);
      }
      
      return edVolta;
  }
  
  public Lote_PagamentoED getLoteByOID(String oid_Lote_Pagamento)throws Excecoes{

      Lote_PagamentoED ed = new Lote_PagamentoED();

      if (oid_Lote_Pagamento != null && oid_Lote_Pagamento.length() > 0 && !oid_Lote_Pagamento.equals("null"))
      {
        ed.setOid_Lote_Pagamento(new Integer(oid_Lote_Pagamento));
      }

      return new Lote_PagamentoRN().getByRecord(ed);

  }

}