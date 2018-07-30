package com.master.rn;

/**
 * <p>Title: Entrega_VeiculoRN </p>
 * <p>Description: Cadastro, exclusão e alteração de Packing-list de veículos para Transporte.
 * cliente: Transp. Pellenz.</p>
 * <p>Copyright: ÊxitoLogística & MasterCOM (c) 2005</p>
 * <p>Company: ÊxitoLogística Consultoria e Sistemas Ltda.</p>
 * @author Teófilo Poletto Baiocco
 * @version 1.0
 */


import com.master.util.*;
import com.master.util.bd.*;
import com.master.bd.Entrega_VeiculoBD;
import com.master.ed.Entrega_VeiculoED;
import java.util.*;

public class Entrega_VeiculoRN extends Transacao  {
    Entrega_VeiculoBD Entrega_VeiculoBD = null;


  public Entrega_VeiculoRN() {
  }


  public Entrega_VeiculoED inclui(Entrega_VeiculoED ed)throws Excecoes{

      try{

        this.inicioTransacao();
        Entrega_VeiculoBD = new Entrega_VeiculoBD(this.sql);
        ed = Entrega_VeiculoBD.inclui(ed);
        this.fimTransacao(true);

      }
      catch(Excecoes exc){
      this.abortaTransacao();
      throw exc;}

      catch(Exception e){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao incluir");
        excecoes.setMetodo("inclui");
        excecoes.setExc(e);
        this.abortaTransacao();

        throw excecoes;
      }
      return ed;
    }

    public void altera(Entrega_VeiculoED ed)throws Excecoes{

      try{

        this.inicioTransacao();
        Entrega_VeiculoBD = new Entrega_VeiculoBD(this.sql);
        Entrega_VeiculoBD.altera(ed);
        this.fimTransacao(true);
      }
      catch(Excecoes exc){
      this.abortaTransacao();
      throw exc;}

      catch(Exception e){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao alterar");
        excecoes.setMetodo("alterar");
        excecoes.setExc(e);
        //faz rollback pois deu algum erro
        this.abortaTransacao();

        throw excecoes;
      }
    }

    public void deleta(Entrega_VeiculoED ed)throws Excecoes{

      try{

        this.inicioTransacao();
        Entrega_VeiculoBD = new Entrega_VeiculoBD(this.sql);
        Entrega_VeiculoBD.deleta(ed);
        this.fimTransacao(true);

      }
      catch(Excecoes exc){
      this.abortaTransacao();
      throw exc;}

      catch(Exception e){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao excluir");
        excecoes.setMetodo("excluir");
        excecoes.setExc(e);
        //faz rollback pois deu algum erro
        this.abortaTransacao();

        throw excecoes;
      }
    }


    public Entrega_VeiculoED getByRecord(Entrega_VeiculoED ed)throws Excecoes{
        //inicia conexao com bd
        this.inicioTransacao();
        //instancia ed de retorno
        Entrega_VeiculoED edVolta = new Entrega_VeiculoED();
        //atribui ao ed de retorno
        edVolta = new Entrega_VeiculoBD(this.sql).getByRecord(ed);
        //libera conexao nao "commitando"
        this.fimTransacao(false);

        return edVolta;
    }


      public ArrayList lista(Entrega_VeiculoED ed)throws Excecoes{
          //retorna um arraylist de ED´s
          this.inicioTransacao();
          ArrayList lista = new Entrega_VeiculoBD(sql).lista(ed);
          this.fimTransacao(false);
          return lista;
      }


}