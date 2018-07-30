package com.master.iu;

import java.util.*;
import javax.servlet.http.*;

import com.master.ed.*;
import com.master.rn.*;
import com.master.util.*;

public class Localizacao_VeiculoBean {

  Utilitaria util = new Utilitaria();

  public ArrayList lista(HttpServletRequest request)
  throws Excecoes{

      Localizacao_VeiculoED ed = this.carregaED(request);

      return new Localizacao_VeiculoRN().lista(ed);
  }


  public void atualiza(HttpServletRequest request) throws Excecoes {

    Localizacao_VeiculoED ed = new Localizacao_VeiculoED();
    new Localizacao_VeiculoRN().atualiza(ed);
  }


  public  byte[] imprime_Localizacao_Veiculo(HttpServletRequest request, HttpServletResponse Response)throws Excecoes{

    Localizacao_VeiculoED ed = this.carregaED(request);

    Localizacao_VeiculoRN geRN = new Localizacao_VeiculoRN();
    byte[] b = geRN.imprime_Localizacao_Veiculo(ed);

    return b;

  }

  public Localizacao_VeiculoED carregaED(HttpServletRequest request)
  throws Excecoes{
      Localizacao_VeiculoED ed = new Localizacao_VeiculoED();

      String DT_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
      if (String.valueOf (DT_Emissao_Inicial) != null &&
          !String.valueOf (DT_Emissao_Inicial).equals ("")) {
        ed.setDT_Emissao_Inicial (DT_Emissao_Inicial);
      }
      String DT_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");
      if (String.valueOf (DT_Emissao_Final) != null &&
          !String.valueOf (DT_Emissao_Final).equals ("")) {
        ed.setDT_Emissao_Final (DT_Emissao_Final);
      }

      if (util.doValida (request.getParameter ("FT_DM_Procedencia"))) {
        ed.setDM_Procedencia (request.getParameter ("FT_DM_Procedencia"));
      }

      if (util.doValida (request.getParameter ("FT_DM_Tipo_Monitoramento"))) {
        ed.setDM_Tipo_Monitoramento (request.getParameter ("FT_DM_Tipo_Monitoramento"));
      }

      if (util.doValida (request.getParameter ("FT_DM_Procedencia_Carreta"))) {
        ed.setDM_Procedencia_Carreta (request.getParameter ("FT_DM_Procedencia_Carreta"));
      }

      ed.setDM_Classificacao ("1");
      ed.setDM_Classificacao (request.getParameter ("FT_DM_Classificacao"));

      return ed;
  }

    public void importaPosicao(HttpServletRequest request) throws Excecoes {

        Localizacao_VeiculoED ed = new Localizacao_VeiculoED();

        ed.setNM_Arquivo((String)request.getAttribute("FT_NM_Arquivo"));
        ed.setCD_Seguradora(request.getParameter("FT_CD_Seguradora"));
        //ed.setOid_Seguradora((new Integer(request.getParameter("oid_Seguradora")).intValue()));

        // System.out.println("inicio importacao arquivo " + ed.getNM_Arquivo());
        // System.out.println("inicio importacao banco " + ed.getOid_Seguradora());

        new Localizacao_VeiculoRN().importaPosicao(ed);
    }

}
