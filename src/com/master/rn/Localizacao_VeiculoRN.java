package com.master.rn;

import com.master.bd.Localizacao_VeiculoBD;
import com.master.ed.Localizacao_VeiculoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;
import java.util.ArrayList;

public class Localizacao_VeiculoRN extends Transacao  {
  Localizacao_VeiculoBD Localizacao_VeiculoBD = null;


  public Localizacao_VeiculoRN() {
  }

  public ArrayList lista(Localizacao_VeiculoED ed)throws Excecoes{
      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Localizacao_VeiculoBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }

  public Localizacao_VeiculoED getByRecord(Localizacao_VeiculoED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      Localizacao_VeiculoED edVolta = new Localizacao_VeiculoED();
      //atribui ao ed de retorno
      edVolta = new Localizacao_VeiculoBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }


  public byte[] imprime_Localizacao_Veiculo(Localizacao_VeiculoED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    Localizacao_VeiculoBD = new Localizacao_VeiculoBD(this.sql);
    byte[] b = Localizacao_VeiculoBD.imprime_Localizacao_Veiculo(ed);
    this.fimTransacao(false);
    return b;

  }


  public void inclui(Localizacao_VeiculoED ed)throws Excecoes{

    try{

      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao();
//// System.out.println("RNRNRNRNRNRN");
      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      Localizacao_VeiculoBD = new Localizacao_VeiculoBD(this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      Localizacao_VeiculoBD.inclui(ed);

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

  public void altera(Localizacao_VeiculoED ed)throws Excecoes{

    try{

      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao();
//// System.out.println("RNRNRNRNRNRN");
      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      Localizacao_VeiculoBD = new Localizacao_VeiculoBD(this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      Localizacao_VeiculoBD.altera(ed);

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

  public void atualiza(Localizacao_VeiculoED ed)throws Excecoes{

    try{

      this.inicioTransacao();
      Localizacao_VeiculoBD = new Localizacao_VeiculoBD(this.sql);
      Localizacao_VeiculoBD.atualiza(ed);
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

    public Localizacao_VeiculoED importaPosicao(Localizacao_VeiculoED ed) throws Excecoes {

        this.inicioTransacao();
        Localizacao_VeiculoBD = new Localizacao_VeiculoBD(this.sql);
        ed = Localizacao_VeiculoBD.importaPosicao(ed);
        this.fimTransacao(true);

        return ed;
    }


}
