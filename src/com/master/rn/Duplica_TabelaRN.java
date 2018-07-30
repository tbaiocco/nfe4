package com.master.rn;

import com.master.bd.Duplica_TabelaBD;
import com.master.ed.Duplica_TabelaED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Duplica_TabelaRN
    extends Transacao {
  Duplica_TabelaBD Duplica_TabelaBD = null;

  public Duplica_TabelaRN () {
  }


  public void inclui_km (Duplica_TabelaED ed) throws Excecoes {

    try {
      this.inicioTransacao ();

      Duplica_TabelaBD = new Duplica_TabelaBD (this.sql);

      Duplica_TabelaBD.inclui_km (ed);

      this.fimTransacao (true);

    }

    catch (Exception e) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao incluir");
      excecoes.setMetodo ("inclui");
      excecoes.setExc (e);
      this.abortaTransacao ();

      throw excecoes;
    }
  }

  public void copia_Tabela (Duplica_TabelaED ed) throws Excecoes {

    try {
      this.inicioTransacao ();

      Duplica_TabelaBD = new Duplica_TabelaBD (this.sql);

      Duplica_TabelaBD.copia_Tabela (ed);

      this.fimTransacao (true);

    }

    catch (Exception e) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao incluir");
      excecoes.setMetodo ("inclui");
      excecoes.setExc (e);
      this.abortaTransacao ();

      throw excecoes;
    }
  }

}