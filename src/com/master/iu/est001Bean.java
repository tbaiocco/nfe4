package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.EstoqueED;
import com.master.rn.EstoqueRN;
import com.master.util.EnviaPDF;
import com.master.util.Excecoes;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class est001Bean {

  public void inclui(HttpServletRequest request)throws Excecoes{

    try{
      EstoqueRN Estoquern = new EstoqueRN();
      EstoqueED ed = new EstoqueED();

      //request em cima dos campos dos forms html

      Estoquern.inclui(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
  }

  public void altera(HttpServletRequest request)throws Excecoes{

    try{
      EstoqueRN Estoquern = new EstoqueRN();
      EstoqueED ed = new EstoqueED();


      Estoquern.altera(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
  }

  public void deleta(HttpServletRequest request)throws Excecoes{

    try{
      EstoqueRN Estoquern = new EstoqueRN();
      EstoqueED ed = new EstoqueED();

      Estoquern.deleta(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
  }

  public ArrayList Estoque_Lista(HttpServletRequest request)throws Excecoes{

      EstoqueED ed = new EstoqueED();
      return new EstoqueRN().Lista(ed);

  }

  public EstoqueED getByRecord(HttpServletRequest request)throws Excecoes{

      EstoqueED ed = new EstoqueED();
      return new EstoqueRN().getByRecord(ed);

  }

  public byte[] gera_Rel_Movimento_Estoque(HttpServletRequest request, HttpServletResponse Response) throws Excecoes {
    EstoqueED ed = new EstoqueED ();

    String OID_Estoque = request.getParameter ("oid_Estoque");
    if (String.valueOf (OID_Estoque) != null && !String.valueOf (OID_Estoque).equals ("") && !String.valueOf (OID_Estoque).equals ("null")) {
      ed.setOid_estoque (new Long (OID_Estoque).longValue ());
    }

    String oid_Pessoa = request.getParameter ("oid_Pessoa");
    if (String.valueOf (oid_Pessoa) != null && !String.valueOf (oid_Pessoa).equals ("") && !String.valueOf (oid_Pessoa).equals ("null")) {
      ed.setOid_fornecedor(oid_Pessoa);
    }

    String DT_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
    if (String.valueOf (DT_Emissao_Inicial) != null && !String.valueOf (DT_Emissao_Inicial).equals ("")) {
      ed.setDT_Inicial (DT_Emissao_Inicial);
    }
    String DT_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");
    if (String.valueOf (DT_Emissao_Final) != null && !String.valueOf (DT_Emissao_Final).equals ("")) {
      ed.setDT_Final (DT_Emissao_Final);
    }
    String NR_Placa = request.getParameter ("FT_NR_Placa");
    if (String.valueOf (NR_Placa) != null && !String.valueOf (NR_Placa).equals ("")) {
      ed.setNR_Placa (NR_Placa);
    }

    String DM_Tipo_Movimento = request.getParameter ("FT_DM_Tipo_Movimento");
    if (String.valueOf (DM_Tipo_Movimento) != null && !String.valueOf (DM_Tipo_Movimento).equals ("")) {
      ed.setDM_Tipo_Movimento (DM_Tipo_Movimento);
    }

    ed.setDM_Relatorio (request.getParameter ("FT_DM_Relatorio"));

    return new EstoqueRN().gera_Rel_Movimento_Estoque(ed);
  }

}