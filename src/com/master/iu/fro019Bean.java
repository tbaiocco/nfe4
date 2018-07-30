package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.RoteiroED;
import com.master.rn.RoteiroRN;
import com.master.util.Excecoes;

public class fro019Bean {

  public RoteiroED inclui(HttpServletRequest request)throws Excecoes{

    try{
      RoteiroRN Roteirorn = new RoteiroRN();
      RoteiroED ed = new RoteiroED();

      ed.setNM_Roteiro(request.getParameter("FT_NM_Roteiro"));

      ed.setTX_Observacao(request.getParameter("FT_TX_Observacao"));
        if (ed.getTX_Observacao() !=  null && ed.getTX_Observacao().length()>400){
        ed.setTX_Observacao(ed.getTX_Observacao().substring(0,400));
      }

      ed.setCD_Roteiro(request.getParameter("FT_CD_Roteiro"));
      ed.setOID_Cidade(new Long(request.getParameter("oid_Cidade_Origem")).longValue());
      ed.setOID_Cidade_Destino(new Long(request.getParameter("oid_Cidade_Destino")).longValue());

      String vl_KM_Carreta = request.getParameter("FT_VL_KM_Carreta");
      if (!vl_KM_Carreta.equals("") && vl_KM_Carreta != null)
        ed.setVL_KM_Carreta(new Double(request.getParameter("FT_VL_KM_Carreta")).doubleValue());

      String vl_KM_Truck = request.getParameter("FT_VL_KM_Truck");
      if (!vl_KM_Truck.equals("") && vl_KM_Truck != null)
        ed.setVL_KM_Truck(new Double(request.getParameter("FT_VL_KM_Truck")).doubleValue());

      String vl_KM_Outros = request.getParameter("FT_VL_KM_Outros");
      if (!vl_KM_Outros.equals("") && vl_KM_Outros != null)
        ed.setVL_KM_Outros(new Double(request.getParameter("FT_VL_KM_Outros")).doubleValue());

      String vl_KM_Carreta_Adto = request.getParameter("FT_VL_KM_Carreta_Adto");
      if (!vl_KM_Carreta_Adto.equals("") && vl_KM_Carreta_Adto != null)
        ed.setVL_KM_Carreta_Adto(new Double(request.getParameter("FT_VL_KM_Carreta_Adto")).doubleValue());

      String vl_KM_Truck_Adto = request.getParameter("FT_VL_KM_Truck_Adto");
      if (!vl_KM_Truck_Adto.equals("") && vl_KM_Truck_Adto != null)
        ed.setVL_KM_Truck_Adto(new Double(request.getParameter("FT_VL_KM_Truck_Adto")).doubleValue());

      String vl_KM_Outros_Adto = request.getParameter("FT_VL_KM_Outros_Adto");
      if (!vl_KM_Outros_Adto.equals("") && vl_KM_Outros_Adto != null)
        ed.setVL_KM_Outros_Adto(new Double(request.getParameter("FT_VL_KM_Outros_Adto")).doubleValue());


      return Roteirorn.inclui(ed);
    }
    catch (Excecoes exc){
    	exc.printStackTrace();
      throw exc;
    }
    catch(Exception exc){
    	exc.printStackTrace();
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
      RoteiroRN Roteirorn = new RoteiroRN();
      RoteiroED ed = new RoteiroED();

      ed.setOID_Cidade(new Long(request.getParameter("oid_Cidade_Origem")).longValue());
      ed.setOID_Cidade_Destino(new Long(request.getParameter("oid_Cidade_Destino")).longValue());

      ed.setNM_Roteiro(request.getParameter("FT_NM_Roteiro"));
      ed.setCD_Roteiro(request.getParameter("FT_CD_Roteiro"));
      
      if (ed.getTX_Observacao() !=  null && ed.getTX_Observacao().length()>400){
        ed.setTX_Observacao(ed.getTX_Observacao().substring(0,400));
      }else {
        ed.setTX_Observacao(" ");
      }
      
      ed.setTX_Observacao(request.getParameter("FT_TX_Observacao"));

      String vl_KM_Carreta = request.getParameter("FT_VL_KM_Carreta");
      if (!vl_KM_Carreta.equals("") && vl_KM_Carreta != null)
        ed.setVL_KM_Carreta(new Double(request.getParameter("FT_VL_KM_Carreta")).doubleValue());

      String vl_KM_Truck = request.getParameter("FT_VL_KM_Truck");
      if (!vl_KM_Truck.equals("") && vl_KM_Truck != null)
        ed.setVL_KM_Truck(new Double(request.getParameter("FT_VL_KM_Truck")).doubleValue());

      String vl_KM_Outros = request.getParameter("FT_VL_KM_Outros");
      if (!vl_KM_Outros.equals("") && vl_KM_Outros != null)
        ed.setVL_KM_Outros(new Double(request.getParameter("FT_VL_KM_Outros")).doubleValue());

      String vl_KM_Carreta_Adto = request.getParameter("FT_VL_KM_Carreta_Adto");
      if (!vl_KM_Carreta_Adto.equals("") && vl_KM_Carreta_Adto != null)
        ed.setVL_KM_Carreta_Adto(new Double(request.getParameter("FT_VL_KM_Carreta_Adto")).doubleValue());

      String vl_KM_Truck_Adto = request.getParameter("FT_VL_KM_Truck_Adto");
      if (!vl_KM_Truck_Adto.equals("") && vl_KM_Truck_Adto != null)
        ed.setVL_KM_Truck_Adto(new Double(request.getParameter("FT_VL_KM_Truck_Adto")).doubleValue());

      String vl_KM_Outros_Adto = request.getParameter("FT_VL_KM_Outros_Adto");
      if (!vl_KM_Outros_Adto.equals("") && vl_KM_Outros_Adto != null)
        ed.setVL_KM_Outros_Adto(new Double(request.getParameter("FT_VL_KM_Outros_Adto")).doubleValue());

      Roteirorn.altera(ed);
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
      RoteiroRN Roteirorn = new RoteiroRN();
      RoteiroED ed = new RoteiroED();

      ed.setCD_Roteiro(request.getParameter("FT_CD_Roteiro"));

      Roteirorn.deleta(ed);
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

  public ArrayList Roteiro_Lista(HttpServletRequest request)throws Excecoes{

      RoteiroED ed = new RoteiroED();
      ed.setNM_Roteiro(request.getParameter("FT_NM_Roteiro"));
      ed.setCD_Roteiro(request.getParameter("FT_CD_Roteiro"));
      String OID_Cidade = request.getParameter("oid_Cidade_Origem");

      if (OID_Cidade != null && !OID_Cidade.equals("null") && !OID_Cidade.equals("")){
        ed.setOID_Cidade(new Long(OID_Cidade).longValue());
      }
// System.out.println(OID_Cidade);
      String OID_Cidade_Destino = request.getParameter("oid_Cidade_Destino");
      if (OID_Cidade_Destino != null && !OID_Cidade_Destino.equals("null") && !OID_Cidade_Destino.equals("")){
        ed.setOID_Cidade_Destino(new Long(OID_Cidade_Destino).longValue());
      }
// System.out.println(OID_Cidade_Destino);

      return new RoteiroRN().lista(ed);

  }

  public ArrayList Lista_Dinamica(HttpServletRequest request)throws Excecoes{

      RoteiroED ed = new RoteiroED();
      String OID_Cidade = request.getParameter("oid_Cidade_Origem");
      if (OID_Cidade != null && !OID_Cidade.equals("null") && !OID_Cidade.equals("")){
        ed.setOID_Cidade(new Long(OID_Cidade).longValue());
      }

      String OID_Cidade_Destino = request.getParameter("oid_Cidade_Destino");
      if (OID_Cidade_Destino != null && !OID_Cidade_Destino.equals("null") && !OID_Cidade_Destino.equals("")){
        ed.setOID_Cidade_Destino(new Long(OID_Cidade_Destino).longValue());
      }

      return new RoteiroRN().lista_Dinamica(ed);

  }

  public RoteiroED getByRecord(HttpServletRequest request)throws Excecoes{

      RoteiroED ed = new RoteiroED();
      ed.setCD_Roteiro(request.getParameter("FT_CD_Roteiro"));
      return new RoteiroRN().getByRecord(ed);
  }

  public byte[] geraRelRotas(HttpServletRequest request, HttpServletResponse Response)throws Excecoes{

      RoteiroED ed = new RoteiroED();

      ed.setNM_Roteiro(request.getParameter("FT_NM_Roteiro"));
      ed.setCD_Roteiro(request.getParameter("FT_CD_Roteiro"));
      String OID_Cidade = request.getParameter("oid_Cidade_Origem");

      ed.setNM_Motorista( request.getParameter("FT_NM_Motorista") );
      if( ed.getNM_Motorista() == null || ed.getNM_Motorista().equals( "null" ) || ed.getNM_Motorista().equals( "" ) )
        ed.setNM_Motorista( "                        " );

      ed.setNR_Placa( request.getParameter( "FT_NR_Placa" ) );
      if( ed.getNR_Placa() == null || ed.getNR_Placa().equals( "null" ) || ed.getNR_Placa().equals( "" ) )
        ed.setNR_Placa( "          " );

      if (OID_Cidade != null && !OID_Cidade.equals("null") && !OID_Cidade.equals("")){
        ed.setOID_Cidade(new Long(OID_Cidade).longValue());
      }

      String OID_Cidade_Destino = request.getParameter("oid_Cidade_Destino");
      if (OID_Cidade_Destino != null && !OID_Cidade_Destino.equals("null") && !OID_Cidade_Destino.equals("")){
        ed.setOID_Cidade_Destino(new Long(OID_Cidade_Destino).longValue());
      }

    RoteiroRN roteiroRN = new RoteiroRN();
    return roteiroRN.geraRelRotas(ed);
  }
  
  public RoteiroED getByRecord(String roteiro)throws Excecoes{

      RoteiroED ed = new RoteiroED();
      ed.setCD_Roteiro(roteiro);
      return new RoteiroRN().getByRecord(ed);
  }


}
