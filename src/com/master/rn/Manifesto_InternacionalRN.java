package com.master.rn;

import com.master.util.*;
import com.master.util.bd.*;
import com.master.bd.Conhecimento_InternacionalBD;
import com.master.bd.ManifestoBD;
import com.master.bd.Manifesto_InternacionalBD;
import com.master.ed.Conhecimento_InternacionalED;
import com.master.ed.ManifestoED;
import com.master.ed.Manifesto_InternacionalED;
import java.util.*;
import java.text.*;

import javax.servlet.http.HttpServletResponse;

public class Manifesto_InternacionalRN extends Transacao  {
  Manifesto_InternacionalBD Manifesto_InternacionalBD = null;


  public Manifesto_InternacionalRN() {
    //Manifesto_Internacionalbd = new Manifesto_InternacionalBD(this.sql);
  }

  public Manifesto_InternacionalED inclui(Manifesto_InternacionalED ed)throws Excecoes{

    Manifesto_InternacionalED manED = new Manifesto_InternacionalED();

    if (ed.getOID_Pessoa().compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Motorista não foi informado !!!");
      throw exc;
    }

    try{

      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

      Calendar calDtPgto = Calendar.getInstance();

      Date dataEmissao = formatter.parse(ed.getDT_Emissao());
      Calendar calDtEmissao = Calendar.getInstance();
      calDtEmissao.setTime(dataEmissao);

      Date dataHoje = new Date();
      Calendar calDataHoje = Calendar.getInstance();
      calDataHoje.setTime(dataHoje);

      if (String.valueOf(ed.getDT_Previsao_Chegada()) != null &&
        !String.valueOf(ed.getDT_Previsao_Chegada()).equals("") &&
        !String.valueOf(ed.getDT_Previsao_Chegada()).equals("null")){
        Date dataPagamento = formatter.parse(ed.getDT_Previsao_Chegada());
        calDtPgto.setTime(dataPagamento);
        if (calDtEmissao.after(calDtPgto)){
          Excecoes exc = new Excecoes();
          exc.setMensagem("Data previsão de chegada tem de ser maior ou igual a data de hoje.");
          exc.setClasse(this.getClass().getName());
          exc.setMetodo("inclui(Manifesto_InternacionalED ed))");
          throw exc;
        }
      }

      this.inicioTransacao();

      Manifesto_InternacionalBD = new Manifesto_InternacionalBD(this.sql);

      manED = Manifesto_InternacionalBD.inclui(ed);

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
      //faz rollback pois deu algum erro
      this.abortaTransacao();

      throw excecoes;
    }

    return manED;

  }

  public void altera(Manifesto_InternacionalED ed)throws Excecoes{

    if (String.valueOf(ed.getOID_Manifesto_Internacional()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Manifesto_Internacional não foi informado !!!");
      throw exc;
    }

    try{

      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

      Calendar calDtPgto = Calendar.getInstance();

      Date dataEmissao = formatter.parse(ed.getDT_Emissao());
      Calendar calDtEmissao = Calendar.getInstance();
      calDtEmissao.setTime(dataEmissao);

      Date dataHoje = new Date();
      Calendar calDataHoje = Calendar.getInstance();
      calDataHoje.setTime(dataHoje);

      if (String.valueOf(ed.getDT_Previsao_Chegada()) != null &&
        !String.valueOf(ed.getDT_Previsao_Chegada()).equals("") &&
        !String.valueOf(ed.getDT_Previsao_Chegada()).equals("null")){
        Date dataPagamento = formatter.parse(ed.getDT_Previsao_Chegada());
        calDtPgto.setTime(dataPagamento);
        if (calDtEmissao.after(calDtPgto)){
          Excecoes exc = new Excecoes();
          exc.setMensagem("Data previsão de chegada tem de ser maior ou igual a data de hoje.");
          exc.setClasse(this.getClass().getName());
          exc.setMetodo("altera(Manifesto_InternacionalED ed))");
          throw exc;
        }
      }

      this.inicioTransacao();

      Manifesto_InternacionalBD = new Manifesto_InternacionalBD(this.sql);
      Manifesto_InternacionalBD.altera(ed);

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
      //faz rollback pois deu algum erro
      this.abortaTransacao();

      throw excecoes;
    }

  }

  public void deleta(Manifesto_InternacionalED ed)throws Excecoes{

    if (String.valueOf(ed.getOID_Manifesto_Internacional()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Manifesto_Internacional não foi informado !!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      Manifesto_InternacionalBD = new Manifesto_InternacionalBD(this.sql);
      Manifesto_InternacionalBD.deleta(ed);

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
      //faz rollback pois deu algum erro
      this.abortaTransacao();

      throw excecoes;
    }

  }

  public ArrayList lista(Manifesto_InternacionalED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Manifesto_InternacionalBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }

  public ArrayList Manifesto_Internacional_Lista_Acerto(Manifesto_InternacionalED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Manifesto_InternacionalBD(sql).Manifesto_Internacional_Lista_Acerto(ed);
      this.fimTransacao(false);
      return lista;
  }

  public Manifesto_InternacionalED getByRecord(Manifesto_InternacionalED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      Manifesto_InternacionalED edVolta = new Manifesto_InternacionalED();
      //atribui ao ed de retorno
      edVolta = new Manifesto_InternacionalBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

  public void geraRelatorio(Manifesto_InternacionalED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    Manifesto_InternacionalBD = new Manifesto_InternacionalBD(this.sql);
    Manifesto_InternacionalBD.geraRelatorio(ed);
    this.fimTransacao(false);

  }
  
  public void inclui_Ocorrencia(Manifesto_InternacionalED ed)throws Excecoes{

      if (String.valueOf(ed.getOID_Manifesto_Internacional()).compareTo("") == 0){
        Excecoes exc = new Excecoes();
        exc.setMensagem("Código do Manifesto não foi informado !!!");
        throw exc;
      }

      try{
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calDtChegada = Calendar.getInstance();
        Date dataHoje = new Date();
        Calendar calDataHoje = Calendar.getInstance();
        calDataHoje.setTime(dataHoje);

        this.inicioTransacao();
        Manifesto_InternacionalBD = new Manifesto_InternacionalBD(this.sql);
        Manifesto_InternacionalBD.inclui_Ocorrencia(ed);
        this.fimTransacao(true);

      }
      catch(Excecoes exc){
      this.abortaTransacao();
      throw exc;}

      catch(Exception e){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao confirmar");
        excecoes.setMetodo("inclui");
        excecoes.setExc(e);
        //faz rollback pois deu algum erro
        this.abortaTransacao();

        throw excecoes;
      }

    }
  
  public void geraRelMICEmitido(Manifesto_InternacionalED ed, HttpServletResponse response) throws Excecoes{

  	//antes de invocar chamada ao relatorio deve-se
  	//fazer validacoes de regra de negocio

  	this.inicioTransacao();
  	Manifesto_InternacionalBD = new Manifesto_InternacionalBD(this.sql);
  	Manifesto_InternacionalBD.geraRelMICEmitido(ed, response);
  	
  	this.fimTransacao(false);
}

}