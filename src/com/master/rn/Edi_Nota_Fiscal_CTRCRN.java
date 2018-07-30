package com.master.rn;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: C opyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import java.util.ArrayList;

import com.master.bd.Edi_Nota_Fiscal_CTRCBD;
import com.master.ed.Edi_Nota_Fiscal_CTRCED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Edi_Nota_Fiscal_CTRCRN extends Transacao  {
  Edi_Nota_Fiscal_CTRCBD Edi_Nota_Fiscal_CTRCBD = null;

 
  public Edi_Nota_Fiscal_CTRCRN() {
  }


  public ArrayList gera_Edi_Nota_Fiscal_CTRC(Edi_Nota_Fiscal_CTRCED ed)throws Excecoes{
    //retorna um arraylist de ED´s
    this.inicioTransacao();
    ArrayList lista = new Edi_Nota_Fiscal_CTRCBD(sql).gera_Edi_Nota_Fiscal_CTRC(ed);
    this.fimTransacao(false);
    return lista;
}

}
