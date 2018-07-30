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

import javax.servlet.http.HttpServletResponse;

import com.master.bd.Processo_AduaneiroBD;
import com.master.ed.Processo_AduaneiroED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Processo_AduaneiroRN extends Transacao  {
  Processo_AduaneiroBD Processo_AduaneiroBD = null;


  public Processo_AduaneiroRN() {
    //Processo_Aduaneirobd = new Processo_AduaneiroBD(this.sql);
  }


  public Processo_AduaneiroED inclui(Processo_AduaneiroED ed) throws Excecoes {

  	Processo_AduaneiroED conED = new Processo_AduaneiroED();

    try {
        //// System.out.println(" inclui RN 1");
        this.inicioTransacao();
        Processo_AduaneiroBD = new Processo_AduaneiroBD(this.sql);
        conED = Processo_AduaneiroBD.inclui(ed);
        this.fimTransacao(true);

    } catch (Excecoes exc) {
        this.abortaTransacao();
        throw exc;
    }

    catch (Exception e) {
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao incluir");
        excecoes.setMetodo("inclui");
        excecoes.setExc(e);
        //faz rollback pois deu algum erro
        this.abortaTransacao();

        throw excecoes;
    }

    return conED;

}

  public Processo_AduaneiroED getByRecord(Processo_AduaneiroED ed) throws Excecoes {
    Processo_AduaneiroED edVolta = new Processo_AduaneiroED();
    try {
        this.inicioTransacao();
        edVolta = new Processo_AduaneiroBD(this.sql).getByRecord(ed);
        this.fimTransacao(true);
    } catch (Excecoes exc) {
        this.abortaTransacao();
        throw exc;
    } catch (RuntimeException e) {
        abortaTransacao();
        throw e;
    }
    return edVolta;
}

  public ArrayList Lista(Processo_AduaneiroED ed)throws Excecoes{

      //retorna um arraylist de ED´s
      this.inicioTransacao();
      ArrayList lista = new Processo_AduaneiroBD(sql).Lista_Processo_Aduaneiro(ed);
      this.fimTransacao(false);
      return lista;
  }

    public void altera(Processo_AduaneiroED ed)throws Excecoes{

        try{
          this.inicioTransacao();
          Processo_AduaneiroBD = new Processo_AduaneiroBD(this.sql);
          Processo_AduaneiroBD.altera(ed);
          this.fimTransacao(true);
        }
        catch(Excecoes exc){
            this.abortaTransacao();
            throw exc;
        }catch(Exception e){
          Excecoes excecoes = new Excecoes();
          excecoes.setClasse(this.getClass().getName());
          excecoes.setMensagem("Erro ao alterar");
          excecoes.setMetodo("altera_Veiculo");
          excecoes.setExc(e);
          //faz rollback pois deu algum erro
          this.abortaTransacao();
          throw excecoes;
        }
      }
  
  public void Imprimer(Processo_AduaneiroED ed, HttpServletResponse resp)throws Excecoes{

      //antes de invocar chamada ao relatorio deve-se
      //fazer validacoes de regra de negocio

      this.inicioTransacao();
      Processo_AduaneiroBD = new Processo_AduaneiroBD(this.sql);
      Processo_AduaneiroBD.Imprime(ed, resp);
      this.fimTransacao(false);
    }
  
  public void deleta(Processo_AduaneiroED ed) throws Excecoes{

      try{
        this.inicioTransacao();
        Processo_AduaneiroBD = new Processo_AduaneiroBD(this.sql);
        Processo_AduaneiroBD.deleta(ed);
        this.fimTransacao(true);
      }
      catch(Excecoes exc){
          this.abortaTransacao();
          throw exc;
      }catch(Exception e){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao excluir");
        excecoes.setMetodo("deleta(Processo_AduaneiroED ed)");
        excecoes.setExc(e);
        //faz rollback pois deu algum erro
        this.abortaTransacao();
        throw excecoes;
      }
    }

}
