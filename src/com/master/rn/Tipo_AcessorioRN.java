package com.master.rn;

/**
 * <p>Title: Tipo_AcessorioRN </p>
 * <p>Description: Cadastro, exclusão e alteração de Acessórios de veículos.</p>
 * <p>Copyright: ÊxitoLogística & MasterCOM (c) 2005</p>
 * <p>Company: ÊxitoLogística Consultoria e Sistemas Ltda.</p>
 * @author Teófilo Poletto Baiocco
 * @version 1.0
 */


import com.master.util.*;
import com.master.util.bd.*;
import com.master.bd.Tipo_AcessorioBD;
import com.master.ed.Tipo_AcessorioED;
import java.util.*;

public class Tipo_AcessorioRN extends Transacao  {
  Tipo_AcessorioBD Tipo_AcessorioBD = null;


  public Tipo_AcessorioRN() {
  }


  public Tipo_AcessorioED inclui(Tipo_AcessorioED ed)throws Excecoes{

      try{

        this.inicioTransacao();

        Tipo_AcessorioBD = new Tipo_AcessorioBD(this.sql);

        ed = Tipo_AcessorioBD.inclui(ed);

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

    public void altera(Tipo_AcessorioED ed)throws Excecoes{

      try{

        this.inicioTransacao();

        Tipo_AcessorioBD = new Tipo_AcessorioBD(this.sql);
        Tipo_AcessorioBD.altera(ed);

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

    public void deleta(Tipo_AcessorioED ed)throws Excecoes{

      try{

        this.inicioTransacao();

        Tipo_AcessorioBD = new Tipo_AcessorioBD(this.sql);
        Tipo_AcessorioBD.deleta(ed);

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

    public ArrayList lista(Tipo_AcessorioED ed)throws Excecoes{
        //retorna um arraylist de ED´s
        this.inicioTransacao();
        ArrayList lista = new Tipo_AcessorioBD(sql).lista(ed);
        this.fimTransacao(false);
        return lista;
    }

    public Tipo_AcessorioED getByRecord(Tipo_AcessorioED ed)throws Excecoes{
        //inicia conexao com bd
        this.inicioTransacao();
        //instancia ed de retorno
        Tipo_AcessorioED edVolta = new Tipo_AcessorioED();
        //atribui ao ed de retorno
        edVolta = new Tipo_AcessorioBD(this.sql).getByRecord(ed);
        //libera conexao nao "commitando"
        this.fimTransacao(false);

        return edVolta;
    }


}