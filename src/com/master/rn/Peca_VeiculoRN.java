package com.master.rn;

/**
 * <p>Title: Peca_VeiculoRN </p>
 * <p>Description: Cadastro, exclusão e alteração de Acessórios de veículos.</p>
 * <p>Copyright: ÊxitoLogística & MasterCOM (c) 2005</p>
 * <p>Company: ÊxitoLogística Consultoria e Sistemas Ltda.</p>
 * @author Teófilo Poletto Baiocco
 * @version 1.0
 */


import com.master.util.*;
import com.master.util.bd.*;
import com.master.bd.Peca_VeiculoBD;
import com.master.ed.Peca_VeiculoED;
import java.util.*;

public class Peca_VeiculoRN extends Transacao  {
  Peca_VeiculoBD Peca_VeiculoBD = null;


  public Peca_VeiculoRN() {
  }


  public Peca_VeiculoED inclui(Peca_VeiculoED ed)throws Excecoes{

      try{

        this.inicioTransacao();

        Peca_VeiculoBD = new Peca_VeiculoBD(this.sql);

        ed = Peca_VeiculoBD.inclui(ed);

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

    public void altera(Peca_VeiculoED ed)throws Excecoes{

      try{

        this.inicioTransacao();

        Peca_VeiculoBD = new Peca_VeiculoBD(this.sql);
        Peca_VeiculoBD.altera(ed);

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

    public void deleta(Peca_VeiculoED ed)throws Excecoes{

      try{

        this.inicioTransacao();

        Peca_VeiculoBD = new Peca_VeiculoBD(this.sql);
        Peca_VeiculoBD.deleta(ed);

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


    public Peca_VeiculoED getByRecord(Peca_VeiculoED ed)throws Excecoes{
        //inicia conexao com bd
        this.inicioTransacao();
        //instancia ed de retorno
        Peca_VeiculoED edVolta = new Peca_VeiculoED();
        //atribui ao ed de retorno
        edVolta = new Peca_VeiculoBD(this.sql).getByRecord(ed);
        //libera conexao nao "commitando"
        this.fimTransacao(false);

        return edVolta;
    }


      public ArrayList lista(Peca_VeiculoED ed)throws Excecoes{
          //retorna um arraylist de ED´s
          this.inicioTransacao();
          ArrayList lista = new Peca_VeiculoBD(sql).lista(ed);
          this.fimTransacao(false);
          return lista;
      }


}
