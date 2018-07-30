package com.master.rn;

import com.master.bd.Movimento_VeiculoBD;
import com.master.ed.Movimento_VeiculoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;
import java.util.ArrayList;

public class Movimento_VeiculoRN extends Transacao  {
  Movimento_VeiculoBD Movimento_VeiculoBD = null;


  public Movimento_VeiculoRN() {
  }

  public ArrayList lista(Movimento_VeiculoED ed)throws Excecoes{
      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Movimento_VeiculoBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }

  public Movimento_VeiculoED getByRecord(Movimento_VeiculoED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      Movimento_VeiculoED edVolta = new Movimento_VeiculoED();
      //atribui ao ed de retorno
      edVolta = new Movimento_VeiculoBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }


  public byte[] imprime_Movimento_Veiculo(Movimento_VeiculoED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    Movimento_VeiculoBD = new Movimento_VeiculoBD(this.sql);
    byte[] b = Movimento_VeiculoBD.imprime_Movimento_Veiculo(ed);
    this.fimTransacao(false);
    return b;

  }


  public void inclui(Movimento_VeiculoED ed)throws Excecoes{

    try{

      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao();
//// System.out.println("RNRNRNRNRNRN");
      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      Movimento_VeiculoBD = new Movimento_VeiculoBD(this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      Movimento_VeiculoBD.inclui(ed);

      //faz o commit em cima do objeto transacao
      this.fimTransacao(true);

    }
    catch(Exception e){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Erro de inclusão");

      //faz rollback pois deu algum erro
      this.abortaTransacao();

      throw exc;
    }

  }

  public void altera(Movimento_VeiculoED ed)throws Excecoes{

    try{

      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao();
//// System.out.println("RNRNRNRNRNRN");
      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      Movimento_VeiculoBD = new Movimento_VeiculoBD(this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      Movimento_VeiculoBD.altera(ed);

      //faz o commit em cima do objeto transacao
      this.fimTransacao(true);

    }
    catch(Exception e){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Erro de inclusão");

      //faz rollback pois deu algum erro
      this.abortaTransacao();

      throw exc;
    }

  }

  public void atualiza(Movimento_VeiculoED ed)throws Excecoes{

    try{

      this.inicioTransacao();
      Movimento_VeiculoBD = new Movimento_VeiculoBD(this.sql);
      Movimento_VeiculoBD.atualiza(ed);
      this.fimTransacao(true);

    }
    catch(Exception e){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Erro de inclusão");

      //faz rollback pois deu algum erro
      this.abortaTransacao();

      throw exc;
    }

  }

    public Movimento_VeiculoED importaPosicao(Movimento_VeiculoED ed) throws Excecoes {

        this.inicioTransacao();
        Movimento_VeiculoBD = new Movimento_VeiculoBD(this.sql);
        ed = Movimento_VeiculoBD.importaPosicao(ed);
        this.fimTransacao(true);

        return ed;
    }


}
