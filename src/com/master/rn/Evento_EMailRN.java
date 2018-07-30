package com.master.rn;

import java.util.ArrayList;

import com.master.bd.Evento_EMailBD;
import com.master.ed.Evento_EMailED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Evento_EMailRN
    extends Transacao {
  Evento_EMailBD Evento_EMailBD = null;

  public Evento_EMailRN () {
  }

  public Evento_EMailED inclui (Evento_EMailED ed) throws Excecoes {

    Evento_EMailED edVolta = new Evento_EMailED ();

    try {
      this.inicioTransacao ();
            // System.out.println("inclui ev rn 2");

      Evento_EMailBD = new Evento_EMailBD (this.sql);
            // System.out.println("inclui ev rn 3");

      edVolta = Evento_EMailBD.inclui (ed);
            // System.out.println("inclui ev rn 4");

      this.fimTransacao (true);
    }

    catch (Exception e) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao inclui");
      excecoes.setMetodo ("Evento_EMailED.inclui()");
      excecoes.setExc (e);
      this.abortaTransacao ();
      throw excecoes;
    }

    return edVolta;
  }

  public ArrayList lista (Evento_EMailED ed) throws Excecoes {

    this.inicioTransacao ();
    ArrayList Lista = new Evento_EMailBD (this.sql).lista (ed);
    this.fimTransacao (false);
    return Lista;
  }

  public boolean deleta (Evento_EMailED ed) throws Excecoes {

    this.inicioTransacao ();
    //// System.out.println("rn");
    boolean deletado = new Evento_EMailBD (sql).deleta (ed);
    this.fimTransacao (true);
    return deletado;
  }

  public Evento_EMailED getByRecord (Evento_EMailED ed) throws Excecoes {

    this.inicioTransacao ();
    Evento_EMailED edVolta = new Evento_EMailED ();
    edVolta = new Evento_EMailBD (this.sql).getByRecord (ed);
    this.fimTransacao (false);

    return edVolta;
  }

  public void update (Evento_EMailED ed) throws Excecoes {

    this.inicioTransacao ();
    Evento_EMailBD = new Evento_EMailBD (this.sql);
    Evento_EMailBD.update (ed);
    this.fimTransacao (true);

  }
}
