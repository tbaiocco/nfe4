package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.EDIED;
import com.master.rn.EDIRN;
import com.master.util.Excecoes;

public class Edi006Bean {

  public ArrayList gera_Arquivo_EDI(HttpServletRequest request)throws Excecoes{

      EDIED ed = new EDIED();
      String NR_Placa = request.getParameter("FT_NR_Placa");
      if (NR_Placa != null && !NR_Placa.equals("") && !NR_Placa.equals("null"))
      {
        ed.setNR_Placa(request.getParameter("FT_NR_Placa"));
      }

      return new EDIRN().gera_Arquivo_EDI(ed);
  }

}