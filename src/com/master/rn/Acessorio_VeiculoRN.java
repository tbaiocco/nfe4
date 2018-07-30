package com.master.rn;

/**
 * <p>Title: Acessorio_VeiculoRN </p>
 * <p>Description: Cadastro, exclusão e alteração de Acessórios de veículos.</p>
 * <p>Copyright: ÊxitoLogística & MasterCOM (c) 2005</p>
 * <p>Company: ÊxitoLogística Consultoria e Sistemas Ltda.</p>
 * @author Teófilo Poletto Baiocco
 * @version 1.0
 */


import com.master.util.*;
import com.master.util.bd.*;
import com.master.bd.Acessorio_VeiculoBD;
import com.master.ed.Acessorio_VeiculoED;
import java.util.*;

public class Acessorio_VeiculoRN extends Transacao  {
  Acessorio_VeiculoBD Acessorio_VeiculoBD = null;


  public Acessorio_VeiculoRN() {
  }


  public Acessorio_VeiculoED inclui(Acessorio_VeiculoED ed)throws Excecoes{

      try{

        this.inicioTransacao();

        Acessorio_VeiculoBD = new Acessorio_VeiculoBD(this.sql);

        ed = Acessorio_VeiculoBD.inclui(ed);

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

    public void altera(Acessorio_VeiculoED ed)throws Excecoes{

      try{

        this.inicioTransacao();

        Acessorio_VeiculoBD = new Acessorio_VeiculoBD(this.sql);
        Acessorio_VeiculoBD.altera(ed);

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

    public void deleta(Acessorio_VeiculoED ed)throws Excecoes{

      try{

        this.inicioTransacao();

        Acessorio_VeiculoBD = new Acessorio_VeiculoBD(this.sql);
        Acessorio_VeiculoBD.deleta(ed);

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


    public Acessorio_VeiculoED getByRecord(Acessorio_VeiculoED ed)throws Excecoes{
        //inicia conexao com bd
        this.inicioTransacao();
        //instancia ed de retorno
        Acessorio_VeiculoED edVolta = new Acessorio_VeiculoED();
        //atribui ao ed de retorno
        edVolta = new Acessorio_VeiculoBD(this.sql).getByRecord(ed);
        //libera conexao nao "commitando"
        this.fimTransacao(false);

        return edVolta;
    }


      public ArrayList lista(Acessorio_VeiculoED ed)throws Excecoes{
          //retorna um arraylist de ED´s
          this.inicioTransacao();
          ArrayList lista = new Acessorio_VeiculoBD(sql).lista(ed);
          this.fimTransacao(false);
          return lista;
      }


}