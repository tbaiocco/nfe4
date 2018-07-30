package com.master.rn;

import java.util.ArrayList;

import com.master.bd.Pessoa_EDIBD;
import com.master.ed.Pessoa_EDIED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Pessoa_EDIRN
    extends Transacao {
  Pessoa_EDIBD Pessoa_EDIBD = null;

  public Pessoa_EDIRN () {
  }

  public Pessoa_EDIED inclui (Pessoa_EDIED pessoa_ed) throws Excecoes {

    Pessoa_EDIED pessoaED = (Pessoa_EDIED) pessoa_ed;

    try {

      this.inicioTransacao ();
      Pessoa_EDIBD = new Pessoa_EDIBD (this.sql);
//// System.out.println("rn!!!!!!!!");
      pessoaED = Pessoa_EDIBD.inclui (pessoa_ed);
      this.fimTransacao (true);
    }

    catch (Exception e) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao importar");
      excecoes.setMetodo ("inclui()");
      excecoes.setExc (e);
      //faz rollback pois deu algum erro
      this.abortaTransacao ();

      throw excecoes;
    }

    return pessoaED;

  }

  public ArrayList getByOidPessoa (Pessoa_EDIED ed) throws Excecoes {

    this.inicioTransacao ();
    ArrayList list = new Pessoa_EDIBD (this.sql).getByOidPessoa (ed);
    this.fimTransacao (false);

    return list;
  }

  public Pessoa_EDIED getByOidPessoa2 (Pessoa_EDIED ed) throws Excecoes {

    this.inicioTransacao ();
    Pessoa_EDIED edi = new Pessoa_EDIBD (this.sql).getByOidPessoa2 (ed);
    this.fimTransacao (false);

    return edi;
  }

  public String getByPasta (String oid_Pessoa , String oid_Padrao , String dm_Imp_Exp) throws Excecoes {
    String nm_Pasta = "";
    this.inicioTransacao ();
    nm_Pasta = new Pessoa_EDIBD (this.sql).getByPasta (oid_Pessoa , oid_Padrao , dm_Imp_Exp);
    this.fimTransacao (false);

    return nm_Pasta;
  }

  /* public Pessoa_EDIED getByCdPessoa(Pessoa_EDIED pessoa_ed) throws Excecoes{
       this.inicioTransacao();
       Pessoa_EDIED edi = new Pessoa_EDIED();
       edi = new Pessoa_EDIBD(this.sql).getByCdPessoa(pessoa_ed);
       this.fimTransacao(false);

       return edi;
   }*/

  public boolean deleta (Pessoa_EDIED ed) throws Excecoes {

    this.inicioTransacao ();
    //// System.out.println("rn");
    boolean deletado = new Pessoa_EDIBD (sql).deleta (ed);
    this.fimTransacao (true);
    return deletado;
  }

  public Pessoa_EDIED getByRecord (Pessoa_EDIED pessoa_ed) throws Excecoes {

    this.inicioTransacao ();
    Pessoa_EDIED edi = new Pessoa_EDIED ();
    edi = new Pessoa_EDIBD (this.sql).getByRecord (pessoa_ed);
    this.fimTransacao (false);

    return edi;
  }

  public void update (Pessoa_EDIED pessoa_ed) throws Excecoes {

    this.inicioTransacao ();
    Pessoa_EDIBD = new Pessoa_EDIBD (this.sql);
    Pessoa_EDIBD.update (pessoa_ed);
    this.fimTransacao (true);

  }
}