package com.master.rn;

import java.sql.SQLException;

import com.master.bd.Importacao_ContabilBD;
import com.master.ed.Importacao_ContabilED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Importacao_ContabilRN
    extends Transacao {

  public Importacao_ContabilRN () {
  }


  public void inclui (Importacao_ContabilED ed) throws Excecoes {
    inicioTransacao ();
    try {
      new Importacao_ContabilBD (sql).inclui (ed);
      fimTransacao (true);
    }
    catch (Excecoes e) {
      abortaTransacao ();
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "inclui(Importacao_ContabilED ed)");
    }
    catch (RuntimeException e) {
      abortaTransacao ();
      throw e;
    }
  }

  public void exclui (Importacao_ContabilED ed) throws Excecoes {
    inicioTransacao ();
    try {
      new Importacao_ContabilBD (sql).exclui (ed);
      fimTransacao (true);
    }
    catch (Excecoes e) {
      abortaTransacao ();
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "exclui(Importacao_ContabilED ed)");
    }
    catch (RuntimeException e) {
      abortaTransacao ();
      throw e;
    }
  }

  public void gera_Contabilizacao_Total () throws Excecoes {
	    inicioTransacao ();
	    try {
	    	System.out.println("passo 2 gera_Contabilizacao_Total()");

	      new Importacao_ContabilBD (sql).gera_Contabilizacao_Total();
	      fimTransacao (true);
	    }
	    catch (Excecoes e) {
	      abortaTransacao ();
	      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "exclui(Importacao_ContabilED ed)");
	    }
	    catch (RuntimeException e) {
	      abortaTransacao ();
	      throw e;
	    }
	  }


}