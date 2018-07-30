package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.Comissao_AgenciamentoED;
import com.master.rn.Comissao_AgenciamentoRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

public class Comissao_AgenciamentoBean
    extends JavaUtil implements Serializable {

  public Comissao_AgenciamentoED inclui (HttpServletRequest request) throws Excecoes {

    Comissao_AgenciamentoED ed = new Comissao_AgenciamentoED ();

    String oid_Vendedor = request.getParameter ("oid_Vendedor");
    String PE_Comissao = request.getParameter ("FT_PE_Comissao");
    String DM_Situacao = request.getParameter ("FT_DM_Situacao");

    //*** Validações
     if (doValida (oid_Vendedor) && doValida (PE_Comissao) && doValida (DM_Situacao)) {

       ed.setPE_Comissao (Double.parseDouble (PE_Comissao));
       ed.setDM_Situacao (DM_Situacao);

       return new Comissao_AgenciamentoRN ().inclui (ed);

     }
     else {
       throw new Excecoes ("Falta de parametros!");
     }
  }

  public void altera (HttpServletRequest request) throws Excecoes {

    Comissao_AgenciamentoRN Comissao_AgenciamentoRN = new Comissao_AgenciamentoRN ();
    Comissao_AgenciamentoED ed = new Comissao_AgenciamentoED ();
    Comissao_AgenciamentoRN.altera (ed);
  }

  public void deleta (HttpServletRequest request) throws Excecoes {

    try {
      Comissao_AgenciamentoRN Comissao_AgenciamentoRN = new Comissao_AgenciamentoRN ();
      Comissao_AgenciamentoED ed = new Comissao_AgenciamentoED ();

      String oid_Comissao_Agenciamento = request.getParameter ("oid_Comissao_Agenciamento");

      //*** Validações
       if (doValida (oid_Comissao_Agenciamento)) {

         ed.setOid_Comissao_Agenciamento (new Integer (oid_Comissao_Agenciamento).intValue ());

       }
       else {
         throw new Excecoes ("Falta de parametros!");
       }

      Comissao_AgenciamentoRN.deleta (ed);

    }
    catch (Excecoes exc) {
      throw exc;
    }
  }

  public ArrayList Comissao_Agenciamento_Lista (HttpServletRequest request) throws Excecoes {

    Comissao_AgenciamentoED ed = new Comissao_AgenciamentoED ();
    Comissao_AgenciamentoRN Comissao_AgenciamentoRN = new Comissao_AgenciamentoRN ();

    return Comissao_AgenciamentoRN.lista (ed);

  }

  public Comissao_AgenciamentoED getByRecord (HttpServletRequest request) throws Excecoes {

    Comissao_AgenciamentoED ed = new Comissao_AgenciamentoED ();

    String oid_Comissao_Agenciamento = request.getParameter ("oid_Comissao_Agenciamento");

    //*** Validações
     if (doValida (oid_Comissao_Agenciamento)) {

       ed.setOid_Comissao_Agenciamento (new Integer (oid_Comissao_Agenciamento).intValue ());

     }
     else {
       throw new Excecoes ("Falta de parametros!");
     }

    return new Comissao_AgenciamentoRN ().getByRecord (ed);
  }

  public byte[] gera_Comissao_Agenciamento (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {

    Comissao_AgenciamentoED ed = new Comissao_AgenciamentoED ();
    Comissao_AgenciamentoRN Comissao_AgenciamentoRN = new Comissao_AgenciamentoRN ();

    String oid_Unidade = request.getParameter ("oid_Unidade");

    if (String.valueOf (oid_Unidade) != null && !String.valueOf (oid_Unidade).equals ("")) {
      ed.setOid_Unidade (new Long (oid_Unidade).longValue ());
    }

    String Dt_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
    if (String.valueOf (Dt_Emissao_Inicial) != null && !String.valueOf (Dt_Emissao_Inicial).equals ("")) {
      ed.setDT_Emissao_Inicial (Dt_Emissao_Inicial);
    }

    String Dt_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");
    if (String.valueOf (Dt_Emissao_Final) != null && !String.valueOf (Dt_Emissao_Final).equals ("")) {
      ed.setDT_Emissao_Final (Dt_Emissao_Final);
    }

    ed.setDM_Tipo_Documento (request.getParameter ("FT_DM_Tipo_Documento"));

    ed.setDM_Relatorio (request.getParameter ("FT_DM_Relatorio"));

    byte[] b = Comissao_AgenciamentoRN.gera_Comissao_Agenciamento (ed);

    return b;

  }

}
