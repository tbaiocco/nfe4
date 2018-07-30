package com.master.iu;

import java.util.*;
import javax.servlet.http.*;

import com.master.ed.*;
import com.master.rn.*;
import com.master.util.*;

public class Movimento_EstoqueBean {

  Utilitaria util = new Utilitaria();

  public  byte[] gera_Relatorio_Movimento_Estoque(HttpServletRequest request, HttpServletResponse Response)throws Excecoes{

    Movimento_EstoqueED ed = this.carregaED(request);

    Movimento_EstoqueRN geRN = new Movimento_EstoqueRN();
    byte[] b = geRN.gera_Relatorio_Movimento_Estoque(ed);

    return b;

  }

  public Movimento_EstoqueED carregaED(HttpServletRequest request)
  throws Excecoes{
      Movimento_EstoqueED ed = new Movimento_EstoqueED();

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

}
