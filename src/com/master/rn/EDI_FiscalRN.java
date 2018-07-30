package com.master.rn;

import java.util.ArrayList;

import com.master.bd.EDI_FiscalBD;
import com.master.ed.EDI_FiscalED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class EDI_FiscalRN extends Transacao  {

  public EDI_FiscalRN() {
  }

  public ArrayList gera_Arquivo_EDI_Sintegra(EDI_FiscalED ed)throws Excecoes{
      //retorna um arraylist de ED´s
      ArrayList lista = null;

      	// System.out.println("Vai chamar o  " + ed.getDM_Tipo_Documento_Saida());


      this.inicioTransacao();
      if (ed.getDM_Tipo_Documento_Saida().equals("CRT")) {
          // System.out.println("RN Vai chamar o gera_Sintegra_CRT_Internacional");
          lista = new EDI_FiscalBD(sql).gera_Sintegra_CRT_Internacional(ed);
      } else if (ed.getDM_Tipo_Documento_Entrada().equals("NFC") ||
         ed.getDM_Tipo_Documento_Saida().equals("CTN")) {
         // System.out.println("RN Vai chamar o gera_Sintegra_CTRC_Nacional");
         lista = new EDI_FiscalBD(sql).gera_Sintegra_CTRC_Nacional(ed);
     }
      this.fimTransacao(false);
      return lista;
  }

  public String gera_Arquivo_EDI_ISSQN(EDI_FiscalED ed)throws Excecoes{
      this.inicioTransacao();
      String xml_ISSQ = new EDI_FiscalBD(sql).gera_Arquivo_EDI_ISSQN(ed);
      this.fimTransacao(false);
      return xml_ISSQ;
  }

}

