package com.master.rn;

import java.util.ArrayList;

import com.master.bd.Pessoa_EMailBD;
import com.master.ed.Pessoa_EMailED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Pessoa_EMailRN
    extends Transacao {
  Pessoa_EMailBD Pessoa_EMailBD = null;

  public Pessoa_EMailRN () {
  }

  public Pessoa_EMailED inclui (Pessoa_EMailED ed) throws Excecoes {

    Pessoa_EMailED edVolta = (Pessoa_EMailED) ed;

    try {

      this.inicioTransacao ();
      Pessoa_EMailBD = new Pessoa_EMailBD (this.sql);
      edVolta = Pessoa_EMailBD.inclui (ed);
      this.fimTransacao (true);
    }

    catch (Exception e) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao incluir");
      excecoes.setMetodo ("inclui()");
      excecoes.setExc (e);
      this.abortaTransacao ();

      throw excecoes;
    }

    return edVolta;

  }

  public ArrayList lista (Pessoa_EMailED ed) throws Excecoes {

    this.inicioTransacao ();
    ArrayList list = new Pessoa_EMailBD (this.sql).lista (ed);
    this.fimTransacao (false);

    return list;
  }


  public boolean deleta (Pessoa_EMailED ed) throws Excecoes {

    this.inicioTransacao ();
    //// System.out.println("rn");
    boolean deletado = new Pessoa_EMailBD (sql).deleta (ed);
    this.fimTransacao (true);
    return deletado;
  }

  public Pessoa_EMailED getByRecord (Pessoa_EMailED pessoa_ed) throws Excecoes {

    this.inicioTransacao ();
    Pessoa_EMailED edi = new Pessoa_EMailED ();
    edi = new Pessoa_EMailBD (this.sql).getByRecord (pessoa_ed);
    this.fimTransacao (false);

    return edi;
  }

  public void update (Pessoa_EMailED pessoa_ed) throws Excecoes {

    this.inicioTransacao ();
    Pessoa_EMailBD = new Pessoa_EMailBD (this.sql);
    Pessoa_EMailBD.update (pessoa_ed);
    this.fimTransacao (true);

  }
}