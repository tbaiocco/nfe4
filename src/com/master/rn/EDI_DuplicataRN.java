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

import com.master.bd.EDI_DuplicataBD;
import com.master.ed.EDI_DuplicataED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class EDI_DuplicataRN extends Transacao  {
  EDI_DuplicataBD EDI_DuplicataBD = null;

 
  public EDI_DuplicataRN() {
  }


  public ArrayList gera_EDI_Duplicata(EDI_DuplicataED ed)throws Excecoes{
    //retorna um arraylist de ED´s
    this.inicioTransacao();
    ArrayList lista = new EDI_DuplicataBD(sql).gera_EDI_Duplicata(ed);
    this.fimTransacao(false);
    return lista;
}

  public ArrayList gera_EDI_Cliente(EDI_DuplicataED ed)throws Excecoes{
    //retorna um arraylist de ED´s
    this.inicioTransacao();
    ArrayList lista = new EDI_DuplicataBD(sql).gera_EDI_Cliente(ed);
    this.fimTransacao(false);
    return lista;
}
  

}
