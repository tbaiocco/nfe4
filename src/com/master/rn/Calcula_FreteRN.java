package com.master.rn;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import com.master.bd.Calcula_FreteBD;
import com.master.ed.Calcula_FreteED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class Calcula_FreteRN extends Transacao  {
  Calcula_FreteBD Calcula_FreteBD = null;
  Calcula_FreteBD Calcula_Frete_Cto_BD = null;


  public Calcula_FreteRN() {
  }

  public Calcula_FreteED calcula_frete(Calcula_FreteED ed)throws Excecoes{

    Calcula_FreteED conED = new Calcula_FreteED();

    try{

      //a chamada a este metodo da superclasse
      //iniciotransacao faz com que se abra uma conexao
      //e a deixe no pool
      this.inicioTransacao();

      //instancia objeto sql, que eh
      //uma referencia ao objeto ExecutaSQL, que por sua
      //vez possui a referencia a conexao ativa
      Calcula_Frete_Cto_BD = new Calcula_FreteBD(this.sql);

      //chama o inclui passando a estrutura de dados
      //como parametro
      conED = Calcula_Frete_Cto_BD.calcula_frete(ed);

      //faz o commit em cima do objeto transacao
      this.fimTransacao(true);

    } catch(Excecoes exc){
        this.abortaTransacao();
        throw exc;
    } catch (RuntimeException e) {
        abortaTransacao();
        throw e;
    }
    return conED;

  }


  }
