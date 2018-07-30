package com.master.rn;

import com.master.bd.Movimento_EstoqueBD;
import com.master.ed.Movimento_EstoqueED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;
import java.util.ArrayList;

public class Movimento_EstoqueRN extends Transacao  {
  Movimento_EstoqueBD Movimento_EstoqueBD = null;


  public Movimento_EstoqueRN() {
  }


  public byte[] gera_Relatorio_Movimento_Estoque(Movimento_EstoqueED ed)throws Excecoes{

    //antes de invocar chamada ao relatorio deve-se
    //fazer validacoes de regra de negocio

    this.inicioTransacao();
    Movimento_EstoqueBD = new Movimento_EstoqueBD(this.sql);
    byte[] b = Movimento_EstoqueBD.gera_Relatorio_Movimento_Estoque(ed);
    this.fimTransacao(false);
    return b;

  }


}
