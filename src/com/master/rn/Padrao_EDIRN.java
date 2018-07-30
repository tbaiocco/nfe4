package com.master.rn;

import java.util.ArrayList;

import com.master.bd.Padrao_EDIBD;
import com.master.ed.Padrao_EDIED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;


public class Padrao_EDIRN extends Transacao  {
  Padrao_EDIBD Padrao_EDIBD = null;

  public Padrao_EDIRN() {
  }

  public Padrao_EDIED incluiPadrao(Padrao_EDIED padrao_ed)throws Excecoes{

  Padrao_EDIED padraoED = (Padrao_EDIED)padrao_ed;

    try{

      this.inicioTransacao();
      Padrao_EDIBD = new Padrao_EDIBD(this.sql);
//// System.out.println("rn!!!!!!!!");
      padraoED = Padrao_EDIBD.incluiPadrao(padrao_ed);
      this.fimTransacao(true);
    }

    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao importar");
      excecoes.setMetodo("EDI_ConhecimentoRN.inclui()");
      excecoes.setExc(e);
      //faz rollback pois deu algum erro
      this.abortaTransacao();

      throw excecoes;
    }

    return padraoED;

  }

  public Padrao_EDIED incluiTipo(Padrao_EDIED padrao_ed)throws Excecoes{

  Padrao_EDIED padraoED = (Padrao_EDIED)padrao_ed;

    try{




      this.inicioTransacao();
      // System.out.println("incluiTipo RN 1");


      Padrao_EDIBD = new Padrao_EDIBD(this.sql);
      padraoED = Padrao_EDIBD.incluiTipo(padrao_ed);
      // System.out.println("incluiTipo RN 2");
      this.fimTransacao(true);
    }

    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluiTipo");
      excecoes.setMetodo("Padrao_EDIED.inclui()");
      excecoes.setExc(e);
      //faz rollback pois deu algum erro
      this.abortaTransacao();

      throw excecoes;
    }

    return padraoED;
  }

  public Padrao_EDIED getByCd(Padrao_EDIED padrao_ed) throws Excecoes{

      this.inicioTransacao();
      Padrao_EDIED edi = new Padrao_EDIED();
      edi = new Padrao_EDIBD(this.sql).getByCd(padrao_ed);
      this.fimTransacao(false);

      return edi;
  }

  public Padrao_EDIED getByCdPadrao(Padrao_EDIED padrao_ed) throws Excecoes{

      this.inicioTransacao();
      Padrao_EDIED edi = new Padrao_EDIED();
      edi = new Padrao_EDIBD(this.sql).getByCdPadrao(padrao_ed);
      this.fimTransacao(false);

      return edi;
  }

  public ArrayList getByNmDescricao(Padrao_EDIED padrao_ed) throws Excecoes{

      this.inicioTransacao();
      ArrayList Padrao_Lista = new Padrao_EDIBD(this.sql).getByNmDescricao(padrao_ed);
      this.fimTransacao(false);

      return Padrao_Lista;
  }

  public ArrayList getByNmDescricao2(Padrao_EDIED padrao_ed) throws Excecoes{

      this.inicioTransacao();
      ArrayList Padrao_Lista = new Padrao_EDIBD(this.sql).getByNmDescricao2(padrao_ed);
      this.fimTransacao(false);

      return Padrao_Lista;
  }

  public ArrayList getByOidPadrao(Padrao_EDIED padrao_ed) throws Excecoes{

      this.inicioTransacao();
      ArrayList Lista = new Padrao_EDIBD(this.sql).getByOidPadrao(padrao_ed);
      this.fimTransacao(false);

      return Lista;
  }

  public ArrayList getByOidTipo2(Padrao_EDIED padrao_ed) throws Excecoes{

      this.inicioTransacao();
      ArrayList Lista = new Padrao_EDIBD(this.sql).getByOidTipo2(padrao_ed);
      this.fimTransacao(false);

      return Lista;
  }

  public boolean deleta(Padrao_EDIED ed)throws Excecoes{

      this.inicioTransacao();
      //// System.out.println("rn");
      boolean deletado = new Padrao_EDIBD(sql).deleta(ed);
      this.fimTransacao(true);
      return deletado;
  }

  public boolean deletaTipo(Padrao_EDIED ed)throws Excecoes{

      this.inicioTransacao();
      //// System.out.println("rn");
      boolean deletado = new Padrao_EDIBD(sql).deletaTipo(ed);
      this.fimTransacao(true);
      return deletado;
  }

  public Padrao_EDIED getByRecord(Padrao_EDIED padrao_ed) throws Excecoes{

      this.inicioTransacao();
      Padrao_EDIED edi = new Padrao_EDIED();
      edi = new Padrao_EDIBD(this.sql).getByRecord(padrao_ed);
      this.fimTransacao(false);

      return edi;
  }

  public Padrao_EDIED getByOidTipo(Padrao_EDIED padrao_ed) throws Excecoes{

      this.inicioTransacao();
      Padrao_EDIED edi = new Padrao_EDIED();
      edi = new Padrao_EDIBD(this.sql).getByOidTipo(padrao_ed);
      this.fimTransacao(false);

      return edi;
  }

  public void updatePadrao(Padrao_EDIED padrao_ed) throws Excecoes{

      this.inicioTransacao();
      Padrao_EDIBD = new Padrao_EDIBD(this.sql);
      Padrao_EDIBD.updatePadrao(padrao_ed);
      this.fimTransacao(true);

  }

  public void updateTipo(Padrao_EDIED padrao_ed) throws Excecoes{

      this.inicioTransacao();
      Padrao_EDIBD = new Padrao_EDIBD(this.sql);
      Padrao_EDIBD.updateTipo(padrao_ed);
      this.fimTransacao(true);

  }
}
