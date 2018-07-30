package com.master.rn;

import com.master.bd.Calcula_FreteCRTBD;
import com.master.bd.Conhecimento_InternacionalBD;
import com.master.ed.Calcula_FreteCRTED;
import com.master.ed.Conhecimento_InternacionalED;
import com.master.util.Excecoes;
import com.master.util.Mensagens;
import com.master.util.bd.Transacao;

public class Calcula_FreteCRTRN extends Transacao  {

  public Calcula_FreteCRTRN() {
  }

  public Calcula_FreteCRTED calcula_frete_crt(Conhecimento_InternacionalED ed)
  throws Excecoes{
      this.inicioTransacao();
      try {
          Conhecimento_InternacionalED conhecimentoInternacionalED = new Conhecimento_InternacionalBD(sql).getByRecord(ed);
          
          //FOI COLOCADO NA MÃO ATÉ A FINALIZAÇÃO DE CRT X COTACAO
          conhecimentoInternacionalED.setOid_Tabela_Frete(19);
          
          if (conhecimentoInternacionalED.getOid_Tabela_Frete() <= 0) {
              //throw new Mensagens("Tabela de frete não informada!");
              throw new Mensagens("Cotacao oid 19 não existente!");
          }
          
          Calcula_FreteCRTED toReturn = new Calcula_FreteCRTBD(sql).calcula_frete(conhecimentoInternacionalED);
          fimTransacao(true);
          return toReturn;
      } catch (Excecoes e) {
          abortaTransacao();
          throw e;
      } catch (RuntimeException e) {
          abortaTransacao();
          throw e;
      }
  }
}
