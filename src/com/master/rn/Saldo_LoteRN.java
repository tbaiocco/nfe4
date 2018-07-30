package com.master.rn;

/**
 * <p>Title: Saldo_LoteRN </p>
 * <p>Description: Inclui, altera, exclui e gerencia saldos para programacao e/ou emissao de cheques.
 * Controla Limite de pgtos por dia. </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: ExitoLogistica & MasterCOM </p>
 * @author Teofilo Poletto Baiocco
 * @version 1.0
 */

import java.util.ArrayList;

import com.master.bd.Saldo_LoteBD;
import com.master.ed.Saldo_LoteED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Saldo_LoteRN extends Transacao  {
  Saldo_LoteBD Saldo_LoteBD = null;


  public Saldo_LoteRN() {
  }
  
  public Saldo_LoteED inclui(Saldo_LoteED ed)throws Excecoes{

      Saldo_LoteED Saldo_LoteED = new Saldo_LoteED();

      try{
        this.inicioTransacao();
        Saldo_LoteBD = new Saldo_LoteBD(this.sql);
        Saldo_LoteED = Saldo_LoteBD.inclui(ed);
        this.fimTransacao(true);
      }
      catch(Excecoes exc){
        throw exc;
      }
      catch(Exception e){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao incluir Saldo");
        excecoes.setMetodo("inclui_Saldo_Lote(Saldo_LoteED)");
        excecoes.setExc(e);
        this.abortaTransacao();
        throw excecoes;
      }
      return Saldo_LoteED;
    }

  public void altera(Saldo_LoteED ed)throws Excecoes{

      try{
        this.inicioTransacao();
        Saldo_LoteBD = new Saldo_LoteBD(this.sql);
        Saldo_LoteBD.altera(ed);
        this.fimTransacao(true);
      }
      catch(Excecoes exc){
      this.abortaTransacao();
      throw exc;}
      catch(Exception e){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao alterar");
        excecoes.setMetodo("altera_Saldo_Lote(Saldo_LoteED)");
        excecoes.setExc(e);
        this.abortaTransacao();
        throw excecoes;
      }
    }
  
  public void deleta(Saldo_LoteED ed)throws Excecoes{

      try{
        this.inicioTransacao();
        Saldo_LoteBD = new Saldo_LoteBD(this.sql);
        Saldo_LoteBD.deleta(ed);
        this.fimTransacao(true);
      }
      catch(Excecoes exc){
      this.abortaTransacao();
      throw exc;}
      catch(Exception e){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao excluir");
        excecoes.setMetodo("inclui(Saldo_LoteED)");
        excecoes.setExc(e);
        this.abortaTransacao();
        throw excecoes;
      }
    }
  
  public Saldo_LoteED getByRecord(Saldo_LoteED ed)throws Excecoes{
      this.inicioTransacao();
      Saldo_LoteED edVolta = new Saldo_LoteED();
      edVolta = new Saldo_LoteBD(this.sql).getByRecord(ed);
      this.fimTransacao(false);
      return edVolta;
  }
  
  public ArrayList lista(Saldo_LoteED ed)throws Excecoes{
      this.inicioTransacao();
      ArrayList lista = new Saldo_LoteBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }
  
  public Saldo_LoteED getTotalProgramado(Saldo_LoteED ed)throws Excecoes{
      this.inicioTransacao();
      Saldo_LoteED edVolta = new Saldo_LoteED();
      edVolta = new Saldo_LoteBD(this.sql).getTotalProgramado(ed);
      this.fimTransacao(false);
      return edVolta;
  }

}