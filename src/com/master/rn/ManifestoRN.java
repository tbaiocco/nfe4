package com.master.rn;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import com.master.bd.ManifestoBD;
import com.master.ed.ManifestoED;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.bd.Transacao;
import com.master.bd.ViagemBD;
import com.master.ed.ViagemED;

public class ManifestoRN
    extends Transacao {
  ManifestoBD ManifestoBD = null;

  public ManifestoRN () {
    //Manifestobd = new ManifestoBD(this.sql);
  }

  public ManifestoED inclui (ManifestoED ed) throws Excecoes {
    ManifestoED manED = new ManifestoED ();
    try {
      SimpleDateFormat formatter = new SimpleDateFormat ("dd/MM/yyyy");

      Calendar calDtPgto = Calendar.getInstance ();

      Date dataEmissao = formatter.parse (ed.getDT_Emissao ());
      Calendar calDtEmissao = Calendar.getInstance ();
      calDtEmissao.setTime (dataEmissao);

      Date dataHoje = new Date ();
      Calendar calDataHoje = Calendar.getInstance ();
      calDataHoje.setTime (dataHoje);

      if (String.valueOf (ed.getDT_Previsao_Chegada ()) != null &&
          !String.valueOf (ed.getDT_Previsao_Chegada ()).equals ("") &&
          !String.valueOf (ed.getDT_Previsao_Chegada ()).equals ("null")) {
        Date dataPagamento = formatter.parse (ed.getDT_Previsao_Chegada ());
        calDtPgto.setTime (dataPagamento);
        if (calDtEmissao.after (calDtPgto)) {
          Excecoes exc = new Excecoes ();
          exc.setMensagem ("Data previsão de chegada tem de ser maior ou igual a data de hoje.");
          exc.setClasse (this.getClass ().getName ());
          exc.setMetodo ("inclui(ManifestoED ed))");
          throw exc;
        }
      }

      this.inicioTransacao ();

      ManifestoBD = new ManifestoBD (this.sql);

      manED = ManifestoBD.inclui (ed);

      this.fimTransacao (true);

    }

    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }

    catch (Exception e) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMetodo ("inclui");
      excecoes.setExc (e);
      //faz rollback pois deu algum erro
      this.abortaTransacao ();

      throw excecoes;
    }

    return manED;

  }

  public ManifestoED geraManifesto (ManifestoED ed) throws Excecoes {
	    ManifestoED manED = new ManifestoED ();
	    try {
	      this.inicioTransacao ();

	      ManifestoBD = new ManifestoBD (this.sql);

	      manED = ManifestoBD.geraManifesto (ed);

	      this.fimTransacao (true);

	    }

	    catch (Excecoes exc) {
	      this.abortaTransacao ();
	      throw exc;
	    }

	    catch (Exception e) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      excecoes.setMetodo ("inclui");
	      excecoes.setExc (e);
	      //faz rollback pois deu algum erro
	      this.abortaTransacao ();

	      throw excecoes;
	    }

	    return manED;

	  }

  public void altera (ManifestoED ed) throws Excecoes {

    if (String.valueOf (ed.getOID_Manifesto ()).compareTo ("") == 0) {
      Excecoes exc = new Excecoes ();
      exc.setMensagem ("Código do Manifesto não foi informado !!!");
      throw exc;
    }

    try {

      SimpleDateFormat formatter = new SimpleDateFormat ("dd/MM/yyyy");

      Calendar calDtPgto = Calendar.getInstance ();

      Date dataEmissao = formatter.parse (ed.getDT_Emissao ());
      Calendar calDtEmissao = Calendar.getInstance ();
      calDtEmissao.setTime (dataEmissao);

      Date dataHoje = new Date ();
      Calendar calDataHoje = Calendar.getInstance ();
      calDataHoje.setTime (dataHoje);

      if (String.valueOf (ed.getDT_Previsao_Chegada ()) != null &&
          !String.valueOf (ed.getDT_Previsao_Chegada ()).equals ("") &&
          !String.valueOf (ed.getDT_Previsao_Chegada ()).equals ("null")) {
        Date dataPagamento = formatter.parse (ed.getDT_Previsao_Chegada ());
        calDtPgto.setTime (dataPagamento);
        if (calDtEmissao.after (calDtPgto)) {
          Excecoes exc = new Excecoes ();
          exc.setMensagem ("Data previsão de chegada tem de ser maior ou igual a data de hoje.");
          exc.setClasse (this.getClass ().getName ());
          exc.setMetodo ("altera(ManifestoED ed))");
          throw exc;
        }
      }

      this.inicioTransacao ();

      ManifestoBD = new ManifestoBD (this.sql);
      ManifestoBD.altera (ed);

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }

    catch (Exception e) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao alterar");
      excecoes.setMetodo ("inclui");
      excecoes.setExc (e);
      //faz rollback pois deu algum erro
      this.abortaTransacao ();

      throw excecoes;
    }

  }


  public void finaliza_manifesto (ManifestoED ed) throws Excecoes {

	    if (String.valueOf (ed.getOID_Manifesto ()).compareTo ("") == 0) {
	      Excecoes exc = new Excecoes ();
	      exc.setMensagem ("Código do Manifesto não foi informado !!!");
	      throw exc;
	    }

	    try {
	      if (String.valueOf (ed.getNR_Odometro_Final()) != null &&
		          !String.valueOf (ed.getNR_Odometro_Final()).equals ("") &&
		          !String.valueOf (ed.getNR_Odometro_Final()).equals ("null")) {
		        long HodometroFinal = ed.getNR_Odometro_Final();
		        if (HodometroFinal <= ed.getNR_Odometro_Inicial()) {
		          Excecoes exc = new Excecoes ();
		          exc.setMensagem ("Hodômetro Final tem que ser maior ao Hodômetro Inicial !");
		          exc.setClasse (this.getClass ().getName ());
		          exc.setMetodo ("altera(ManifestoED ed))");
		          throw exc;
		        }
		      }

	      this.inicioTransacao ();

	      ManifestoBD = new ManifestoBD (this.sql);
	      ManifestoBD.finaliza_manifesto (ed);

	      this.fimTransacao (true);

	    }
	    catch (Excecoes exc) {
	      this.abortaTransacao ();
	      throw exc;
	    }

	    catch (Exception e) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      excecoes.setMensagem ("Erro ao alterar");
	      excecoes.setMetodo ("inclui");
	      excecoes.setExc (e);
	      //faz rollback pois deu algum erro
	      this.abortaTransacao ();

	      throw excecoes;
	    }

	  }

  public void inclui_Ocorrencia (ManifestoED ed) throws Excecoes {

    if (String.valueOf (ed.getOID_Manifesto ()).compareTo ("") == 0) {
      Excecoes exc = new Excecoes ();
      exc.setMensagem ("Código do Manifesto não foi informado !!!");
      throw exc;
    }

    try {

      SimpleDateFormat formatter = new SimpleDateFormat ("dd/MM/yyyy");

      Calendar calDtChegada = Calendar.getInstance ();

      //// System.out.println("rn 1");

      Date dataHoje = new Date ();
      Calendar calDataHoje = Calendar.getInstance ();
      calDataHoje.setTime (dataHoje);

      //// System.out.println("rn 2");

//      if (String.valueOf(ed.getDT_Chegada()) != null &&
//        !String.valueOf(ed.getDT_Chegada()).equals("") &&
//        !String.valueOf(ed.getDT_Chegada()).equals("null")){
//        Date dataChegada = formatter.parse(ed.getDT_Chegada());
//        calDtChegada.setTime(dataChegada);
//       //// System.out.println("rn 3");
//
//        if (calDataHoje.before(calDtChegada)){
//
//           //// System.out.println("rn 4");
//
//          Excecoes exc = new Excecoes();
//          exc.setMensagem("Data da chegada tem de ser menor ou igual a data de hoje.");
//          exc.setClasse(this.getClass().getName());
//          exc.setMetodo("altera(ManifestoED ed))");
//          throw exc;
//        }
//      }

      //// System.out.println("rn 5");

      this.inicioTransacao ();

      ManifestoBD = new ManifestoBD (this.sql);
      ManifestoBD.inclui_Ocorrencia (ed);

      this.fimTransacao (true);

    }
    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }

    catch (Exception e) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao confirmar");
      excecoes.setMetodo ("inclui");
      excecoes.setExc (e);
      //faz rollback pois deu algum erro
      this.abortaTransacao ();

      throw excecoes;
    }

  }

  public void deleta (ManifestoED ed) throws Excecoes {
    if (!JavaUtil.doValida (ed.getOID_Manifesto ())) {
      Excecoes exc = new Excecoes ("Código do Manifesto não foi informado!");
    }
    try {
      this.inicioTransacao ();
      new ManifestoBD (this.sql).deleta (ed);
      this.fimTransacao (true);
    }
    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }
    catch (RuntimeException e) {
      abortaTransacao ();
      throw e;
    }
  }

  public ArrayList lista (ManifestoED ed) throws Excecoes {

    //retorna um arraylist de ED´s
    this.inicioTransacao ();
    ArrayList lista = new ManifestoBD (sql).lista (ed);
    this.fimTransacao (false);
    return lista;
  }

  public ArrayList Manifesto_Lista_Acerto (ManifestoED ed) throws Excecoes {

    //retorna um arraylist de ED´s
    this.inicioTransacao ();
    ArrayList lista = new ManifestoBD (sql).Manifesto_Lista_Acerto (ed);
    this.fimTransacao (false);
    return lista;
  }

  public ManifestoED getByRecord (ManifestoED ed) throws Excecoes {
    //inicia conexao com bd
    this.inicioTransacao ();
    //instancia ed de retorno
    ManifestoED edVolta = new ManifestoED ();
    //atribui ao ed de retorno
    edVolta = new ManifestoBD (this.sql).getByRecord (ed);
    //libera conexao nao "commitando"
    this.fimTransacao (false);

    return edVolta;
  }

  public byte[] imprime_Manifesto (ManifestoED filtro) throws Excecoes {
    inicioTransacao ();
    try {
      return new ManifestoBD (sql).imprime_Manifesto (filtro);
    }
    finally {
      fimTransacao (true);
    }
  }

  public void relComissaoManifesto (ManifestoED ed) throws Excecoes {
    inicioTransacao ();
    try {
      new ManifestoBD (sql).relComissaoManifesto (ed);
    }
    finally {
      fimTransacao (false);
    }
  }

  public byte[] gera_Relatorio_Manifesto (ManifestoED ed) throws Excecoes {

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao ();
    try {
      ManifestoBD = new ManifestoBD (this.sql);
      return ManifestoBD.gera_Relatorio_Manifesto (ed);
    }
    finally {
      this.fimTransacao (false);
    }
  }

  public ManifestoED inclui_AWB (ManifestoED ed) throws Excecoes {
    ManifestoED manED = new ManifestoED ();
    try {
      this.inicioTransacao ();

      ManifestoBD = new ManifestoBD (this.sql);

      manED = ManifestoBD.inclui_AWB (ed);

      this.fimTransacao (true);

    }

    catch (Excecoes exc) {
      this.abortaTransacao ();
      throw exc;
    }

    catch (Exception e) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMetodo ("inclui");
      excecoes.setExc (e);
      //faz rollback pois deu algum erro
      this.abortaTransacao ();

      throw excecoes;
    }

    return manED;

  }

  public void rel_Resumo_Entrega_Rota (ManifestoED ed , HttpServletResponse response) throws Excecoes {

    this.inicioTransacao ();
    try {
      new ManifestoBD (this.sql).rel_Resumo_Entrega_Rota (ed , response);
    }
    finally {
      this.fimTransacao (false);
    }
  }

}
