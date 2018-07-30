package com.master.rn;

import java.util.ArrayList;

import com.master.bd.Ocorrencia_EDIBD;
import com.master.ed.Ocorrencia_EDIED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Ocorrencia_EDIRN
    extends Transacao {
  Ocorrencia_EDIBD Ocorrencia_EDIBD = null;

  public Ocorrencia_EDIRN () {
  }

  public Ocorrencia_EDIED inclui (Ocorrencia_EDIED ed) throws Excecoes {

    Ocorrencia_EDIED ocorrencia_EDIED = new Ocorrencia_EDIED ();
    // System.out.println ("rn");

    try {

      this.inicioTransacao ();
      Ocorrencia_EDIBD = new Ocorrencia_EDIBD (this.sql);
      ocorrencia_EDIED = Ocorrencia_EDIBD.inclui (ed);
      this.fimTransacao (true);
    }

    catch (Exception e) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("inclui");
      excecoes.setMetodo ("EDI_ConhecimentoRN.inclui()");
      excecoes.setExc (e);
      //faz rollback pois deu algum erro
      this.abortaTransacao ();

      throw excecoes;
    }

    return ocorrencia_EDIED;

  }

  public ArrayList lista (Ocorrencia_EDIED ed) throws Excecoes {

    //retorna um arraylist de ED´s
    this.inicioTransacao ();
    ArrayList lista = new Ocorrencia_EDIBD (sql).lista (ed);
    this.fimTransacao (false);
    return lista;
  }

  public boolean deleta (Ocorrencia_EDIED ed) throws Excecoes {

    this.inicioTransacao ();
    //// System.out.println("rn");
    boolean deletado = new Ocorrencia_EDIBD (sql).deleta (ed);
    this.fimTransacao (true);
    return deletado;
  }

  public Ocorrencia_EDIED getByRecord (Ocorrencia_EDIED ed) throws Excecoes {

    this.inicioTransacao ();
    Ocorrencia_EDIED edi = new Ocorrencia_EDIED ();
    edi = new Ocorrencia_EDIBD (this.sql).getByRecord (ed);
    this.fimTransacao (false);

    return edi;
  }

  public void update (Ocorrencia_EDIED ed) throws Excecoes {

    this.inicioTransacao ();
    Ocorrencia_EDIBD = new Ocorrencia_EDIBD (this.sql);
    Ocorrencia_EDIBD.update (ed);
    this.fimTransacao (true);

  }

}