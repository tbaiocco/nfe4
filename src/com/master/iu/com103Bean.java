package com.master.iu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.ComissaoED;
import com.master.rn.ComissaoRN;
import com.master.util.Excecoes;

public class com103Bean {


  public  byte[] geraComissao_Vendas(HttpServletRequest request, HttpServletResponse Response)throws Excecoes{

    ComissaoED ed = new ComissaoED();

    String oid_Vendedor = request.getParameter("oid_Vendedor");
    if (String.valueOf(oid_Vendedor) != null && !String.valueOf(oid_Vendedor).equals("") && !String.valueOf(oid_Vendedor).equals("null") ){
      ed.setOID_Vendedor(oid_Vendedor);
      ed.setNM_Vendedor(request.getParameter("FT_NM_Fantasia_Vendedor"));
    }

    String DT_Emissao_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
    if (String.valueOf(DT_Emissao_Inicial) != null && !String.valueOf(DT_Emissao_Inicial).equals("")){
      ed.setDT_Emissao_Inicial(DT_Emissao_Inicial);
    }

    String DT_Emissao_Final = request.getParameter("FT_DT_Emissao_Final");
    if (String.valueOf(DT_Emissao_Final) != null && !String.valueOf(DT_Emissao_Final).equals("")){
      ed.setDT_Emissao_Final(DT_Emissao_Final);
    }

    String NR_Amostra = request.getParameter("FT_NR_Amostra");
    if (String.valueOf(NR_Amostra) != null && !String.valueOf(NR_Amostra).equals("")){
      ed.setNR_Amostra(NR_Amostra);
    }


     String oid_Empresa = request.getParameter("oid_Empresa");


    ed.setDM_Lista_Conhecimento(request.getParameter("FT_DM_Lista_Conhecimento"));
    ed.setDM_Relatorio(request.getParameter("FT_DM_Relatorio"));

    ComissaoRN geRN = new ComissaoRN();
    byte[] b = geRN.geraComissao_Vendas(ed);

    return b;

  }


}