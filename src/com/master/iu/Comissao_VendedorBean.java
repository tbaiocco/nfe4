package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.Comissao_VendedorED;
import com.master.rn.Comissao_VendedorRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

public class Comissao_VendedorBean
    extends JavaUtil implements Serializable {

  public Comissao_VendedorED inclui (HttpServletRequest request) throws Excecoes {

    Comissao_VendedorED ed = new Comissao_VendedorED ();

    String oid_Vendedor = request.getParameter ("oid_Vendedor");
    String PE_Comissao = request.getParameter ("FT_PE_Comissao");
    String oid_Mix = request.getParameter ("oid_Mix");
    String oid_Produto = request.getParameter ("oid_Produto");
    String oid_Tipo_Produto = request.getParameter ("oid_Tipo_Produto");
    String oid_Estrutura_Produto = request.getParameter ("oid_Estrutura_Produto");
    String DM_Situacao = request.getParameter ("FT_DM_Situacao");

    //*** Validações
     if (doValida (oid_Vendedor) && doValida (PE_Comissao) && doValida (DM_Situacao)) {

       ed.setOid_Vendedor (oid_Vendedor);
       ed.setPE_Comissao (Double.parseDouble (PE_Comissao));
       ed.setDM_Situacao (DM_Situacao);

       if (doValida (oid_Mix)) {
         ed.setOid_Mix (new Integer (oid_Mix).intValue ());
       }
       else if (doValida (oid_Estrutura_Produto)) {
         ed.setOid_Estrutura_Produto (new Integer (oid_Estrutura_Produto).intValue ());
       }
       else if (doValida (oid_Produto)) {
         ed.setOid_Produto (new Integer (oid_Produto).intValue ());
       }
       else if (doValida (oid_Tipo_Produto)) {
         ed.setOid_Tipo_Produto (new Integer (oid_Tipo_Produto).intValue ());
       }
       else {
         throw new Excecoes ("Nenhum tipo de comissão selecionada!");
       }

       return new Comissao_VendedorRN ().inclui (ed);

     }
     else {
       throw new Excecoes ("Falta de parametros!");
     }
  }

  public void altera (HttpServletRequest request) throws Excecoes {

    Comissao_VendedorRN Comissao_VendedorRN = new Comissao_VendedorRN ();
    Comissao_VendedorED ed = new Comissao_VendedorED ();

    String oid_Comissao_Vendedor = request.getParameter ("oid_Comissao_Vendedor");
    String oid_Vendedor = request.getParameter ("oid_Vendedor");
    String oid_Mix = request.getParameter ("oid_Mix");
    String oid_Produto = request.getParameter ("oid_Produto");
    String oid_Tipo_Produto = request.getParameter ("oid_Tipo_Produto");
    String oid_Estrutura_Produto = request.getParameter ("oid_Estrutura_Produto");
    String PE_Comissao = request.getParameter ("FT_PE_Comissao");
    String DM_Situacao = request.getParameter ("FT_DM_Situacao");

    //*** Validações
     if (doValida (oid_Vendedor) && doValida (PE_Comissao) && doValida (DM_Situacao)) {

       ed.setOid_Comissao_Vendedor (new Integer (oid_Comissao_Vendedor).intValue ());
       ed.setOid_Vendedor (oid_Vendedor);
       ed.setPE_Comissao (Double.parseDouble (PE_Comissao));
       ed.setDM_Situacao (DM_Situacao);

       if (doValida (oid_Mix)) {
         ed.setOid_Mix (new Integer (oid_Mix).intValue ());
       }
       else if (doValida (oid_Estrutura_Produto)) {
         ed.setOid_Estrutura_Produto (new Integer (oid_Estrutura_Produto).intValue ());
       }
       else if (doValida (oid_Produto)) {
         ed.setOid_Produto (new Integer (oid_Produto).intValue ());
       }
       else if (doValida (oid_Tipo_Produto)) {
         ed.setOid_Tipo_Produto (new Integer (oid_Tipo_Produto).intValue ());
       }
       else {
         throw new Excecoes ("Nenhum tipo de comissão selecionada!");
       }

     }
     else {
       throw new Excecoes ("Falta de parametros!");
     }

    Comissao_VendedorRN.altera (ed);
  }

  public void deleta (HttpServletRequest request) throws Excecoes {

    try {
      Comissao_VendedorRN Comissao_VendedorRN = new Comissao_VendedorRN ();
      Comissao_VendedorED ed = new Comissao_VendedorED ();

      String oid_Comissao_Vendedor = request.getParameter ("oid_Comissao_Vendedor");

      //*** Validações
       if (doValida (oid_Comissao_Vendedor)) {

         ed.setOid_Comissao_Vendedor (new Integer (oid_Comissao_Vendedor).intValue ());

       }
       else {
         throw new Excecoes ("Falta de parametros!");
       }

      Comissao_VendedorRN.deleta (ed);

    }
    catch (Excecoes exc) {
      throw exc;
    }
  }

  public ArrayList Comissao_Vendedor_Lista (HttpServletRequest request) throws Excecoes {

    Comissao_VendedorED ed = new Comissao_VendedorED ();
    Comissao_VendedorRN Comissao_VendedorRN = new Comissao_VendedorRN ();

    String oid_Vendedor = request.getParameter ("oid_Vendedor");

    //*** Validações
     if (doValida (oid_Vendedor)) {
       ed.setOid_Vendedor (oid_Vendedor);
     }
     else {
       throw new Excecoes ("Falta de parametros!");
     }

    return Comissao_VendedorRN.lista (ed);

  }

  public Comissao_VendedorED getByRecord (HttpServletRequest request) throws Excecoes {

    Comissao_VendedorED ed = new Comissao_VendedorED ();

    String oid_Comissao_Vendedor = request.getParameter ("oid_Comissao_Vendedor");

    //*** Validações
     if (doValida (oid_Comissao_Vendedor)) {

       ed.setOid_Comissao_Vendedor (new Integer (oid_Comissao_Vendedor).intValue ());

     }
     else {
       throw new Excecoes ("Falta de parametros!");
     }

    return new Comissao_VendedorRN ().getByRecord (ed);
  }

  public byte[] geraComissaoConhecimentos (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {

    Comissao_VendedorED ed = new Comissao_VendedorED ();
    Comissao_VendedorRN Comissao_VendedorRN = new Comissao_VendedorRN ();

    String Dt_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
    if (String.valueOf (Dt_Emissao_Inicial) != null && !String.valueOf (Dt_Emissao_Inicial).equals ("")) {
      ed.setDT_Emissao_Inicial (Dt_Emissao_Inicial);
    }

    String Dt_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");
    if (String.valueOf (Dt_Emissao_Final) != null && !String.valueOf (Dt_Emissao_Final).equals ("")) {
      ed.setDT_Emissao_Final (Dt_Emissao_Final);
    }

    String oid_Vendedor = request.getParameter ("oid_Vendedor");
    if (String.valueOf (oid_Vendedor) != null && !String.valueOf (oid_Vendedor).equals ("")) {
      ed.setOid_Vendedor (oid_Vendedor);
    }

    ed.setDM_Tipo_Documento (request.getParameter ("FT_DM_Tipo_Documento"));

    ed.setDM_Relatorio (request.getParameter ("FT_DM_Relatorio"));

    byte[] b = Comissao_VendedorRN.geraComissaoConhecimentos (ed);

    return b;

  }

}
