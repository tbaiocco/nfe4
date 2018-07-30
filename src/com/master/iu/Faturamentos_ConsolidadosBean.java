package com.master.iu;

import javax.servlet.http.*;

import com.master.rn.*;
import com.master.ed.*;
import com.master.util.*;

import java.util.*;

public class Faturamentos_ConsolidadosBean {

    
    public Faturamentos_ConsolidadosED inclui(HttpServletRequest request)throws Excecoes{
        
        try{
            
            Faturamentos_ConsolidadosED fcED = new Faturamentos_ConsolidadosED();
            
            fcED.setOid_Manifesto_Internacional(request.getParameter("oid_Manifesto_Internacional"));
            fcED.setOid_Cliente(request.getParameter("oid_Cliente"));
            fcED.setVl_Peso_MIC(Double.parseDouble(request.getParameter("FT_VL_Peso_MIC")));
            fcED.setVl_Frete(Double.parseDouble(request.getParameter("FT_VL_Frete")));
            fcED.setVl_Frete_Base(Double.parseDouble(request.getParameter("FT_VL_Frete_Base")));
            fcED.setVl_Seguro(Double.parseDouble(request.getParameter("FT_VL_Seguro")));
            fcED.setVl_Adicional(Double.parseDouble(request.getParameter("FT_VL_Adicional")));
            fcED.setVl_Faturamento(Double.parseDouble(request.getParameter("FT_VL_Faturamento")));
            fcED.setOid_Tipo_Faturamento(Integer.parseInt(request.getParameter("oid_Tipo_Faturamento")));
            
            return new Faturamentos_ConsolidadosRN().inclui(fcED);
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
        Faturamentos_ConsolidadosED fcED = new Faturamentos_ConsolidadosED();
        
        fcED.setOid_Faturamento_Consolidado(Integer.parseInt(request.getParameter("oid_Faturamento_Consolidado")));
        fcED.setOid_Manifesto_Internacional(request.getParameter("oid_Manifesto_Internacional"));
        fcED.setOid_Cliente(request.getParameter("oid_Cliente"));
        fcED.setVl_Peso_MIC(Double.parseDouble(request.getParameter("FT_VL_Peso_MIC")));
        fcED.setVl_Frete(Double.parseDouble(request.getParameter("FT_VL_Frete")));
        fcED.setVl_Frete_Base(Double.parseDouble(request.getParameter("FT_VL_Frete_Base")));
        fcED.setVl_Seguro(Double.parseDouble(request.getParameter("FT_VL_Seguro")));
        fcED.setVl_Adicional(Double.parseDouble(request.getParameter("FT_VL_Adicional")));
        fcED.setVl_Faturamento(Double.parseDouble(request.getParameter("FT_VL_Faturamento")));
        fcED.setOid_Tipo_Faturamento(Integer.parseInt(request.getParameter("oid_Tipo_Faturamento")));

      new Faturamentos_ConsolidadosRN().altera(fcED);
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
      Faturamentos_ConsolidadosED fcED = new Faturamentos_ConsolidadosED();

      fcED.setOid_Faturamento_Consolidado(Integer.parseInt(request.getParameter("oid_Faturamento_Consolidado")));

      new Faturamentos_ConsolidadosRN().deleta(fcED);
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

  
  public Faturamentos_ConsolidadosED getByOidFatConsolidado(HttpServletRequest request)throws Excecoes{

      Faturamentos_ConsolidadosED fcED = new Faturamentos_ConsolidadosED();

      String oid_Faturamento_Consolidado = request.getParameter("oid_Faturamento_Consolidado");
      if (JavaUtil.doValida(oid_Faturamento_Consolidado)){
        fcED.setOid_Faturamento_Consolidado(Integer.parseInt(oid_Faturamento_Consolidado));
      }
      
//      String NR_Manifesto_Internacional = request.getParameter("FT_NR_Manifesto_Internacional");
//      if (JavaUtil.doValida(NR_Manifesto_Internacional)){
//        fcED.setNr_Manifesto_Internacional(NR_Manifesto_Internacional);
//      }
//
//      String oid_Manifesto_Internacional = request.getParameter("oid_Manifesto_Internacional");
//      if (JavaUtil.doValida(oid_Manifesto_Internacional)){
//        fcED.setOid_Manifesto_Internacional(oid_Manifesto_Internacional);
//      }
//      
      String oid_Cliente = request.getParameter("oid_Cliente");
      if (JavaUtil.doValida(oid_Cliente)){
         fcED.setOid_Cliente(oid_Cliente);
      }

      return new Faturamentos_ConsolidadosRN().getByOidFatConsolidado(fcED);

  }
  public Faturamentos_ConsolidadosED getCRTConsolidados(String oid_Conhecimento)throws Excecoes{

      Faturamentos_ConsolidadosED fcED = new Faturamentos_ConsolidadosED();

      fcED.setOid_Conhecimento(oid_Conhecimento);

      return new Faturamentos_ConsolidadosRN().getCRTConsolidados(fcED);

  }
  
  public Faturamentos_ConsolidadosED getCRTByMIC(HttpServletRequest request, boolean setCliente, boolean consolidado)throws Excecoes{

      Faturamentos_ConsolidadosED fcED = new Faturamentos_ConsolidadosED();

      String oid_Manifesto_Internacional = request.getParameter("oid_Manifesto_Internacional");
      if (JavaUtil.doValida(oid_Manifesto_Internacional)){
        fcED.setOid_Manifesto_Internacional(oid_Manifesto_Internacional);
      }
      
      if(setCliente){
          String oid_Cliente = request.getParameter("oid_Cliente");
          if (JavaUtil.doValida(oid_Cliente)){
              fcED.setOid_Cliente(oid_Cliente);
          }
          else{
              oid_Cliente = request.getAttribute("oid_Cliente").toString();
              if (JavaUtil.doValida(oid_Cliente)) fcED.setOid_Cliente(oid_Cliente);
          }
      }
      return new Faturamentos_ConsolidadosRN().getCRTByMIC(fcED, setCliente, consolidado);

  }
  
  public ArrayList Faturamentos_Consolidados_Lista(HttpServletRequest request)throws Excecoes{

      Faturamentos_ConsolidadosED fcED = new Faturamentos_ConsolidadosED();

      String oid_Manifesto_Internacional = request.getParameter("oid_Manifesto_Internacional");
      if (JavaUtil.doValida(oid_Manifesto_Internacional)){
        fcED.setOid_Manifesto_Internacional(oid_Manifesto_Internacional);
      }
      
      String oid_Cliente = request.getParameter("oid_Cliente");
      if (JavaUtil.doValida(oid_Cliente)){
        fcED.setOid_Cliente(oid_Cliente);
      }
      
      return new Faturamentos_ConsolidadosRN().lista(fcED);

  }


  public void gera_Relatorio_Consolidacao_MIC_CRT(HttpServletRequest request, HttpServletResponse response)throws Excecoes{
      String dm_Tipo_Relatorio = request.getParameter("FT_DM_Tipo_Relatorio");
      if (!JavaUtil.doValida(dm_Tipo_Relatorio)){
        dm_Tipo_Relatorio = "";
      }
      
      Faturamentos_ConsolidadosED fcED = new Faturamentos_ConsolidadosED(response, dm_Tipo_Relatorio);

      fcED.setDm_Tipo_Relatorio(dm_Tipo_Relatorio);
      
      String oid_Unidade = request.getParameter("oid_Unidade");
      if (JavaUtil.doValida(oid_Unidade)){
        fcED.setOid_Unidade(Integer.parseInt(oid_Unidade));
      }
      
      String nr_Manifesto_Inicial = request.getParameter("FT_NR_Manifesto_Inicial");
      if (JavaUtil.doValida(nr_Manifesto_Inicial)){
        fcED.setNr_Manifesto_Internacional_Inicial(nr_Manifesto_Inicial);
      }

      String nr_Manifesto_Final= request.getParameter("FT_NR_Manifesto_Final");
      if (JavaUtil.doValida(nr_Manifesto_Final)){
        fcED.setNr_Manifesto_Internacional_Final(nr_Manifesto_Final);
      }
      
      String dt_Emissao_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
      if (JavaUtil.doValida(dt_Emissao_Inicial)){
        fcED.setDt_Emissao_MIC_Inicial(dt_Emissao_Inicial);
      }

      String dt_Emissao_Final = request.getParameter("FT_DT_Emissao_Final");
      if (JavaUtil.doValida(dt_Emissao_Final)){
        fcED.setDt_Emissao_MIC_Inicial(dt_Emissao_Final);
      }
      
      new Faturamentos_ConsolidadosRN().gera_Relatorio_Consolidacao_MIC_CRT(fcED);
  }

  
  public void imprimeFaturaProform(HttpServletRequest request, HttpServletResponse response)throws Excecoes{
      
      Faturamentos_ConsolidadosED fcED = new Faturamentos_ConsolidadosED(response, null);
      
      fcED.setOid_Manifesto_Internacional(request.getParameter("oid_Manifesto_Internacional"));
     
      new Faturamentos_ConsolidadosRN().imprimeFaturaProform(fcED);
  }
  
  public void Consolida(HttpServletRequest request)throws Excecoes{
      
      Faturamentos_ConsolidadosED fcED = new Faturamentos_ConsolidadosED();
      
      fcED.setOid_Manifesto_Internacional(request.getParameter("oid_Manifesto_Internacional"));
     
      new Faturamentos_ConsolidadosRN().Consolida(fcED);
  }
  
  
}
