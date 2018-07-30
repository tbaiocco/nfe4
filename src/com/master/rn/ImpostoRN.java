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

import com.master.bd.CompromissoBD;
import com.master.bd.ImpostoBD;
import com.master.ed.CompromissoED;
import com.master.ed.ImpostoED;
import com.master.ed.ImpostoPesquisaED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class ImpostoRN extends Transacao  {
  ImpostoBD ImpostoBD = null;


  public ImpostoRN() {
    //Impostobd = new ImpostoBD(this.sql);
  }



  public ImpostoED inclui_old(ImpostoED ed)throws Excecoes{

    ImpostoED ImpostoED = new ImpostoED();

    try{


      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao();

      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      ImpostoBD = new ImpostoBD(this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      ImpostoED = ImpostoBD.inclui(ed);

      //faz o commit em cima do objeto transacao
      this.fimTransacao(true);

    }
    catch(Excecoes exc){
      throw exc;
    }
    catch(Exception e){
      this.abortaTransacao();
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir histórico");
      excecoes.setMetodo("inclui");
      excecoes.setExc(e);
      throw excecoes;
    }

    return ImpostoED;

  }


  public ImpostoED inclui(ImpostoED ed)throws Excecoes{

        // System.out.println("Retem Imposto Inclui " );


    ImpostoED ImpostoED = new ImpostoED();

    try{

//      double valPgto = ed.getVL_Imposto().doubleValue();
//      double valComp = ed.getVl_Compromisso().doubleValue();

//      if (valPgto > valComp){
//        Excecoes exc = new Excecoes();
//        exc.setMensagem("Valor do Imposto não pode ser maior que o valor devido");
//        exc.setClasse(this.getClass().getName());
//        exc.setMetodo("Inclui(ImpostoED)");
//        throw exc;
//      }

        // System.out.println("Retem Imposto Inclui 2" );

//      if ((ed.getVL_Imposto()).doubleValue() <= 0.0){
//        Excecoes exc = new Excecoes();
//        exc.setMensagem("Valor do Imposto menor ou igual a zero.");
//        exc.setClasse(this.getClass().getName());
//        exc.setMetodo("Inclui(ImpostoED)");
//        throw exc;
//      }

//      if ((ed.getPE_Imposto()).doubleValue() < 0.0){
//        Excecoes exc = new Excecoes();
//        exc.setMensagem("% do Imposto igual a zero.");
//        exc.setClasse(this.getClass().getName());
//        exc.setMetodo("Inclui(ImpostoED)");
//        throw exc;
//      }

      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao();
        // System.out.println("Retem Imposto Inclui 3 " );

      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      ImpostoBD = new ImpostoBD(this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      ImpostoED = ImpostoBD.inclui(ed);

      //altera o valor do saldo em compromissos

      String Retem_Imposto = ed.getDM_Retem_Imposto();
         // //// System.out.println(Retem_Imposto);
      if (Retem_Imposto.equals("S")) {
        // System.out.println("Retem Imposto Inclui 4" );

                  // //// System.out.println(Retem_Imposto);
//          double dValSub = ed.getVL_Imposto().doubleValue();
//          double dValComp= ed.getVl_Compromisso().doubleValue();
          CompromissoED edComp = new CompromissoED();
//          edComp.setVl_Imposto(new Double(dValSub));
 //         edComp.setVl_Saldo(new Double(dValComp - dValSub));
//          edComp.setOid_Compromisso(ed.getOid_Compromisso());
        // System.out.println("Retem Imposto Inclui 5" );
          this.retemImposto(edComp);
      }
        //faz o commit em cima do objeto transacao
      this.fimTransacao(true);

    }

    catch(Excecoes exc){
      throw exc;
    }
    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir");
      excecoes.setMetodo("inclui(ImpostoED)");
      excecoes.setExc(e);
      this.abortaTransacao();
      throw excecoes;

    }

    return ImpostoED;

  }

  public void altera(ImpostoED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      ImpostoBD = new ImpostoBD(this.sql);
      ImpostoBD.altera(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
      throw exc;
    }
    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar");
      excecoes.setMetodo("inclui(ImpostoED)");
      excecoes.setExc(e);
      this.abortaTransacao();
      throw excecoes;
    }

  }

  public void deleta(ImpostoED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      ImpostoBD = new ImpostoBD(this.sql);
      ImpostoBD.deleta(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
      throw exc;
    }
    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao excluir");
      excecoes.setMetodo("inclui(ImpostoED)");
      excecoes.setExc(e);
      this.abortaTransacao();
      throw excecoes;
    }

  }

  public ArrayList lista(ImpostoPesquisaED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new ImpostoBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }

  public ImpostoPesquisaED getByRecord(ImpostoPesquisaED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      ImpostoPesquisaED edVolta = new ImpostoPesquisaED();
      //atribui ao ed de retorno
      edVolta = new ImpostoBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

  public void geraRelatorio(ImpostoED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    ImpostoBD = new ImpostoBD(this.sql);
    ImpostoBD.geraRelatorio(ed);
    this.fimTransacao(false);

  }

  public void retemImposto(CompromissoED ed)throws Excecoes{

      // System.out.println("Retem Imposto 0 " );


    CompromissoBD compromissoBD = new CompromissoBD(this.sql);
    compromissoBD.retemImposto(ed);

  }

  public void subtraiSaldo(CompromissoED ed)throws Excecoes{

    CompromissoBD compromissoBD = new CompromissoBD(this.sql);
    compromissoBD.subtraiSaldo(ed);

  }

}