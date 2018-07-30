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

import com.master.bd.Acerto_ViagemBD;
import com.master.ed.Acerto_ViagemED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Acerto_ViagemRN extends Transacao  {
  Acerto_ViagemBD Acerto_ViagemBD = null;


  public Acerto_ViagemRN() {

  }

  public ArrayList lista(Acerto_ViagemED ed) throws Excecoes {

      this.inicioTransacao();
      ArrayList lista = new Acerto_ViagemBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }

  public void altera(Acerto_ViagemED ed)
  throws Excecoes {
      try {
          this.inicioTransacao();
          new Acerto_ViagemBD(this.sql).altera(ed);
          this.fimTransacao(true);
      } catch (Excecoes e) {
          this.abortaTransacao();
          throw e;
      } catch (RuntimeException e) {
          abortaTransacao();
          throw e;
      }
  }

  public byte[] geraRelAcerto_Veiculos(Acerto_ViagemED ed) throws Excecoes {
      try {
          this.inicioTransacao();
          return new Acerto_ViagemBD(this.sql).geraRelAcerto_Veiculos(ed);
      } finally {
          this.fimTransacao(false);
      }
  }

  public byte[] geraRelAcertos_Motorista(Acerto_ViagemED ed) throws Excecoes {
      try {
          this.inicioTransacao();
          return new Acerto_ViagemBD(this.sql).geraRelAcertos_Motorista(ed);
      } finally {
          this.fimTransacao(false);
      }
  }

  public byte[] imprimeAcerto(Acerto_ViagemED ed) throws Excecoes {
      try {
          this.inicioTransacao();
          return new Acerto_ViagemBD(this.sql).imprimeAcerto(ed);
      } finally {
          this.fimTransacao(false);
      }
  }


  public void finaliza(Acerto_ViagemED ed )
  throws Excecoes {
      try {
          this.inicioTransacao();
          new Acerto_ViagemBD(this.sql).finaliza(ed);
          this.fimTransacao(true);
      } catch (Excecoes e) {
          this.abortaTransacao();
          throw e;
      } catch (RuntimeException e) {
          abortaTransacao();
          throw e;
      }
  }


  public void reabre(Acerto_ViagemED ed )
  throws Excecoes {
      try {
          this.inicioTransacao();
          new Acerto_ViagemBD(this.sql).reabre(ed);
          this.fimTransacao(true);
      } catch (Excecoes e) {
          this.abortaTransacao();
          throw e;
      } catch (RuntimeException e) {
          abortaTransacao();
          throw e;
      }
  }


  public void deleta(Acerto_ViagemED ed)
  throws Excecoes {
      try {
          this.inicioTransacao();
          new Acerto_ViagemBD(this.sql).deleta(ed);
          this.fimTransacao(true);
      } catch (Excecoes e) {
          this.abortaTransacao();
          throw e;
      } catch (RuntimeException e) {
          abortaTransacao();
          throw e;
      }
  }

  public Acerto_ViagemED inclui(Acerto_ViagemED ed) throws Excecoes {
      Acerto_ViagemED edVolta = new Acerto_ViagemED();
      try {
               // System.out.println ("Tipo inclui -------------");


          this.inicioTransacao();
          edVolta = new Acerto_ViagemBD(this.sql).inclui_Despesas_Custo_Fixo(ed);
          this.fimTransacao(true);

          this.inicioTransacao();
          edVolta = new Acerto_ViagemBD(this.sql).inclui(ed);
          this.fimTransacao(true);

      } catch (Excecoes exc) {
          this.abortaTransacao();
          throw exc;
      } catch (RuntimeException e) {
          abortaTransacao();
          throw e;
      }
      return edVolta;
  }

  public Acerto_ViagemED consultaAcertoNaoFinalizado(Acerto_ViagemED  ed) throws Excecoes {
    Acerto_ViagemED edVolta = new Acerto_ViagemED();
      try {
          this.inicioTransacao();
          edVolta = new Acerto_ViagemBD(this.sql).consultaAcertoNaoFinalizado(ed);
          this.fimTransacao(true);
      } catch (Excecoes exc) {
          this.abortaTransacao();
          throw exc;
      } catch (RuntimeException e) {
          abortaTransacao();
          throw e;
      }
      return edVolta;
  }



  public Acerto_ViagemED getByRecord(Acerto_ViagemED ed) throws Excecoes {
      Acerto_ViagemED edVolta = new Acerto_ViagemED();
      try {
          this.inicioTransacao();
          edVolta = new Acerto_ViagemBD(this.sql).getByRecord(ed);
          this.fimTransacao(true);
      } catch (Excecoes exc) {
          this.abortaTransacao();
          throw exc;
      } catch (RuntimeException e) {
          abortaTransacao();
          throw e;
      }
      return edVolta;
  }



}
