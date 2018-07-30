package com.master.rn;

import java.util.ArrayList;

import com.master.bd.EDIBD;
import com.master.ed.EDIED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class EDIRN extends Transacao  {
  EDIBD EDIBD = null;


  public EDIRN() {
    //EDIbd = new EDIBD(this.sql);
  }

//  public EDIRN(ExecutaSQL sqlTrans) {
//    this.sql = sql;
//    new Transacao(sqlTrans);
//    new EDIBD(this.sql);
//  }

  public ArrayList gera_Arquivo_EDI(EDIED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new EDIBD(sql).gera_Arquivo_EDI(ed);
      this.fimTransacao(false);
      return lista;
  }
}