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

import com.master.bd.Ordem_ManifestoBD;
import com.master.ed.Ordem_ManifestoED;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.bd.Transacao;

public class Ordem_ManifestoRN extends Transacao  {
  Ordem_ManifestoBD Ordem_ManifestoBD = null;


  public Ordem_ManifestoRN() {
    //Ordem_Manifestobd = new Ordem_ManifestoBD(this.sql);
  }

  public void inclui(Ordem_ManifestoED ed)throws Excecoes{

    if (String.valueOf(ed.getOID_Manifesto()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Manifesto não foi informado !!!");
      throw exc;
    }

    try{

      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao();

      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      Ordem_ManifestoBD = new Ordem_ManifestoBD(this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      Ordem_ManifestoBD.inclui(ed);

      //faz o commit em cima do objeto transacao
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

  public void altera(Ordem_ManifestoED ed)throws Excecoes{

    if (String.valueOf(ed.getOID_Ordem_Manifesto()).compareTo("") == 0){
      Excecoes exc = new Excecoes();
      exc.setMensagem("Código do Ordem_Manifesto não foi informado !!!");
      throw exc;
    }

    try{

      this.inicioTransacao();

      Ordem_ManifestoBD = new Ordem_ManifestoBD(this.sql);
      Ordem_ManifestoBD.altera(ed);

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

  public void deleta(Ordem_ManifestoED ed)
  throws Excecoes{
  	if (!JavaUtil.doValida(ed.getOID_Ordem_Manifesto())){
  		Excecoes exc = new Excecoes();
  		exc.setMensagem("Código do Ordem_Manifesto não foi informado !!!");
  		throw exc;
  	}
  	
  	try{this.inicioTransacao();
  		new Ordem_ManifestoBD(this.sql).deleta(ed);
  		this.fimTransacao(true);
  	} catch(Excecoes e){
  		this.abortaTransacao();
  		throw e;
    } catch (RuntimeException e) {
        abortaTransacao();
        throw e;
  	}
  }

  public ArrayList lista(Ordem_ManifestoED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Ordem_ManifestoBD(sql).lista(ed);
      this.fimTransacao(false);
      return lista;
  }

  public Ordem_ManifestoED getByRecord(Ordem_ManifestoED ed)throws Excecoes{
      //inicia conexao com bd
      this.inicioTransacao();
      //instancia ed de retorno
      Ordem_ManifestoED edVolta = new Ordem_ManifestoED();
      //atribui ao ed de retorno
      edVolta = new Ordem_ManifestoBD(this.sql).getByRecord(ed);
      //libera conexao nao "commitando"
      this.fimTransacao(false);

      return edVolta;
  }

  public void geraRelatorio(Ordem_ManifestoED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    Ordem_ManifestoBD = new Ordem_ManifestoBD(this.sql);
    Ordem_ManifestoBD.geraRelatorio(ed);
    this.fimTransacao(false);

  }
  
  public void inclui_MIC(Ordem_ManifestoED ed)throws Excecoes{

      if (String.valueOf(ed.getOID_Manifesto()).compareTo("") == 0){
        Excecoes exc = new Excecoes();
        exc.setMensagem("Código do Manifesto não foi informado !!!");
        throw exc;
      }

      try{

        //a chamada a este metodo da superclasse
        //iniciotransacao faz com que se abra uma conexao
        //e a deixe no pool
        this.inicioTransacao();

        //instancia objeto sql, que eh
        //uma referencia ao objeto ExecutaSQL, que por sua
        //vez possui a referencia a conexao ativa
        Ordem_ManifestoBD = new Ordem_ManifestoBD(this.sql);

        //chama o inclui passando a estrutura de dados
        //como parametro
        Ordem_ManifestoBD.inclui_MIC(ed);

        //faz o commit em cima do objeto transacao
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

    public void altera_MIC(Ordem_ManifestoED ed)throws Excecoes{

      if (String.valueOf(ed.getOID_Ordem_Manifesto()).compareTo("") == 0){
        Excecoes exc = new Excecoes();
        exc.setMensagem("Código do Ordem_Manifesto não foi informado !!!");
        throw exc;
      }

      try{

        this.inicioTransacao();

        Ordem_ManifestoBD = new Ordem_ManifestoBD(this.sql);
        Ordem_ManifestoBD.altera_MIC(ed);

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

    public void deleta_MIC(Ordem_ManifestoED ed)
    throws Excecoes{
    	if (!JavaUtil.doValida(ed.getOID_Ordem_Manifesto())){
    		Excecoes exc = new Excecoes();
    		exc.setMensagem("Código do Ordem_Manifesto não foi informado !!!");
    		throw exc;
    	}
    	
    	try{this.inicioTransacao();
    		new Ordem_ManifestoBD(this.sql).deleta_MIC(ed);
    		this.fimTransacao(true);
    	} catch(Excecoes e){
    		this.abortaTransacao();
    		throw e;
        } catch (RuntimeException e) {
            abortaTransacao();
            throw e;
    	}
    }

    public ArrayList lista_MIC(Ordem_ManifestoED ed)throws Excecoes{

        //retorna um arraylist de ED´s
        this.inicioTransacao();
        ArrayList lista = new Ordem_ManifestoBD(sql).lista_MIC(ed);
        this.fimTransacao(false);
        return lista;
    }

    public Ordem_ManifestoED getByRecord_MIC(Ordem_ManifestoED ed)throws Excecoes{
        //inicia conexao com bd
        this.inicioTransacao();
        //instancia ed de retorno
        Ordem_ManifestoED edVolta = new Ordem_ManifestoED();
        //atribui ao ed de retorno
        edVolta = new Ordem_ManifestoBD(this.sql).getByRecord_MIC(ed);
        //libera conexao nao "commitando"
        this.fimTransacao(false);

        return edVolta;
    }

}