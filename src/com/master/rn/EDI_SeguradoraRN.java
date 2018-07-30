package com.master.rn;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import java.util.ArrayList;

import com.master.bd.EDI_SeguradoraBD;
import com.master.ed.EDI_SeguradoraED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class EDI_SeguradoraRN extends Transacao  {
  EDI_SeguradoraBD EDI_SeguradoraBD = null;


  public EDI_SeguradoraRN() {
  }


  public ArrayList gera_EDI_Seguradora(EDI_SeguradoraED ed)throws Excecoes{
      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new EDI_SeguradoraBD(sql).gera_EDI_Seguradora(ed);
      this.fimTransacao(false);
      return lista;
  }
  
  public ArrayList gera_EDI_Seguradora_CRT(EDI_SeguradoraED ed)throws Excecoes{
      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new EDI_SeguradoraBD(sql).gera_EDI_Seguradora_CRT(ed);
      this.fimTransacao(false);
      return lista;
  }


}
