package com.master.rn;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.master.bd.CompromissoBD;
import com.master.bd.Posto_CompromissoBD;
import com.master.ed.CompromissoED;
import com.master.ed.Posto_CompromissoED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Posto_CompromissoRN extends Transacao  {
  Posto_CompromissoBD Posto_CompromissoBD = null;


  public Posto_CompromissoRN() {
    //Posto_Compromissobd = new Posto_CompromissoBD(this.sql);
  }

  public Posto_CompromissoED inclui(Posto_CompromissoED ed)throws Excecoes{

    Posto_CompromissoED Posto_CompromissoED = new Posto_CompromissoED();

    try{


    //Faz validações de regra de negócio
    //Data pagamento tem de ser menor ou igual a hoje
      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
      Date dataPagamento = formatter.parse(ed.getDt_Pagamento());
      Calendar calDtPgto = Calendar.getInstance();
      calDtPgto.setTime(dataPagamento);

      Date dataHoje = new Date();
      Calendar calDataHoje = Calendar.getInstance();
      calDataHoje.setTime(dataHoje);

      if (1==2){
      //if (calDtPgto.after(calDataHoje)){
        Excecoes exc = new Excecoes();
        exc.setMensagem("Data do Pagamento tem de ser menor ou igual a hoje");
        exc.setClasse(this.getClass().getName());
        exc.setMetodo("Inclui(Posto_CompromissoED)");
        throw exc;
      }

// System.out.println("1");
      //data de pagamento tem de ser maior ou igual data de emissao
      Date dataEmissao = formatter.parse(ed.getDt_Emissao());
      Calendar calDtEmissao = Calendar.getInstance();
      calDtEmissao.setTime(dataEmissao);

// System.out.println("2");

      if (1==2){
      //if (calDtEmissao.after(calDtPgto)){
        Excecoes exc = new Excecoes();
        exc.setMensagem("Data do Pagamento tem de ser maior ou igual a data de emissao");
// System.out.println(exc.getMensagem());
        exc.setClasse(this.getClass().getName());
        exc.setMetodo("Inclui(Posto_CompromissoED)");
        throw exc;
      }

// System.out.println("3");

      double valPgto = ed.getVl_Pagamento().doubleValue();
      double valComp = ed.getVl_Compromisso().doubleValue();

//      if (valPgto > valComp){
//        Excecoes exc = new Excecoes();
//        exc.setMensagem("Valor do pagamento não pode ser maior que o valor devido");
//        exc.setClasse(this.getClass().getName());
//        exc.setMetodo("Inclui(Posto_CompromissoED)");
//        throw exc;
//      }

      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao();

      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      Posto_CompromissoBD = new Posto_CompromissoBD(this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      Posto_CompromissoED = Posto_CompromissoBD.inclui(ed);

      //altera o valor do saldo em compromissos

      //double dValSub = ed.getVl_Pagamento().doubleValue();
      //double dValComp= ed.getVl_Compromisso().doubleValue();
      //CompromissoED edComp = new CompromissoED();
      //edComp.setVl_Saldo(new Double(dValComp - dValSub));
      //edComp.setOid_Compromisso(ed.getOid_Compromisso());
      //this.subtraiSaldo(edComp);

      //faz o commit em cima do objeto transacao
      this.fimTransacao(true);

// System.out.println("rn over");
    }

    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}
    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir");
      excecoes.setMetodo("inclui(Posto_CompromissoED)");
      excecoes.setExc(e);
e.printStackTrace();
      this.abortaTransacao();
      throw excecoes;

    }

    return Posto_CompromissoED;

  }

  public void altera(Posto_CompromissoED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      Posto_CompromissoBD = new Posto_CompromissoBD(this.sql);
      Posto_CompromissoBD.altera(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}
    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar");
      excecoes.setMetodo("inclui(Posto_CompromissoED)");
      excecoes.setExc(e);
      this.abortaTransacao();
      throw excecoes;
    }

  }

  public void deleta(Posto_CompromissoED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      Posto_CompromissoBD = new Posto_CompromissoBD(this.sql);
      Posto_CompromissoBD.deleta(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}
    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao excluir");
      excecoes.setMetodo("inclui(Posto_CompromissoED)");
      excecoes.setExc(e);
      this.abortaTransacao();
      throw excecoes;
    }

  }

  public void estorna_Pagamento(Posto_CompromissoED ed)throws Excecoes{

    try{

      this.inicioTransacao();

      Posto_CompromissoBD = new Posto_CompromissoBD(this.sql);
      Posto_CompromissoBD.estorna_Pagamento(ed);

      this.fimTransacao(true);

    }
    catch(Excecoes exc){
    this.abortaTransacao();
    throw exc;}
    catch(Exception e){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao estornar");
      excecoes.setMetodo("estorna_Pagamento(Posto_CompromissoED ed)");
      excecoes.setExc(e);
      this.abortaTransacao();
      throw excecoes;
    }

  }

  public ArrayList lista(Posto_CompromissoED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Posto_CompromissoBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }

  public Posto_CompromissoED getByRecord(Posto_CompromissoED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      Posto_CompromissoED edVolta = new Posto_CompromissoED();
      //atribui ao ed de retorno
      edVolta = new Posto_CompromissoBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

//  public void geraRelatorio(Posto_CompromissoED ed)throws Excecoes{
//
//    //antes de invocar chamada ao relatorio deve-se
//    //fazer validacoes de regra de negocio
//
//    this.inicioTransacao();
//    Posto_CompromissoBD = new Posto_CompromissoBD(this.sql);
//    Posto_CompromissoBD.geraRelatorio(ed);
//    this.fimTransacao(false);
//
//  }

  public void subtraiSaldo(CompromissoED ed)throws Excecoes{

    CompromissoBD compromissoBD = new CompromissoBD(this.sql);
    compromissoBD.subtraiSaldo(ed);

  }

}
