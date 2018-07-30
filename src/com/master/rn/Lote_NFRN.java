package com.master.rn;

import java.util.ArrayList;

import com.master.bd.Lote_NFBD;
import com.master.ed.Lote_NFED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Lote_NFRN extends Transacao  {
  Lote_NFBD Lote_NFBD = null;

  public Lote_NFRN() {
  }

  public Lote_NFED inclui(Lote_NFED ed)throws Excecoes{

  Lote_NFED ED = (Lote_NFED)ed;

    try{

      this.inicioTransacao();
      Lote_NFBD = new Lote_NFBD(this.sql);
//// System.out.println("rn!!!!!!!!");
      ED = Lote_NFBD.inclui(ed);
      this.fimTransacao(true);
    }

    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao importar");
      excecoes.setMetodo("inclui");
      excecoes.setExc(e);
      //faz rollback pois deu algum erro
      this.abortaTransacao();

      throw excecoes;
    }

    return ED;

  }

  public ArrayList getByOid(Lote_NFED nf) throws Excecoes{

      this.inicioTransacao();
      ArrayList list = new Lote_NFBD(this.sql).getByOid(nf);
      this.fimTransacao(false);

      return list;
  }

   public Lote_NFED getByRecord(Lote_NFED nf) throws Excecoes{

      this.inicioTransacao();
      Lote_NFED lote = new Lote_NFBD(this.sql).getByRecord(nf);
      this.fimTransacao(false);

      return lote;
  }

  public Lote_NFED getByCD(Lote_NFED nf) throws Excecoes{

      this.inicioTransacao();
      Lote_NFED lote = new Lote_NFBD(this.sql).getByCD(nf);
      this.fimTransacao(false);

      return lote;
  }

  public boolean deleta(Lote_NFED ed)throws Excecoes{

      this.inicioTransacao();
      //// System.out.println("rn");
      boolean deletado = new Lote_NFBD(sql).deleta(ed);
      this.fimTransacao(true);
      return deletado;
  }

 public void update(Lote_NFED ed) throws Exception{

      this.inicioTransacao();
      Lote_NFBD = new Lote_NFBD(this.sql);
      Lote_NFBD.update(ed);
      this.fimTransacao(true);

  }

  public ArrayList lista(Lote_NFED ed) throws Exception{

      this.inicioTransacao();
      ArrayList lista = new ArrayList();

      try{
            Lote_NFBD = new Lote_NFBD(this.sql);
            lista = Lote_NFBD.lista(ed);
	    this.fimTransacao(true);
         }

      catch (Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao selecionar");
      excecoes.setMetodo("lista");
      excecoes.setExc(e);
      //faz rollback pois deu algum erro
      this.abortaTransacao();
      throw excecoes;
      }

      return lista;
  }
}