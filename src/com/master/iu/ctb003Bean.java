package com.master.iu;

import javax.servlet.http.HttpServletRequest;
import com.master.ed.Importacao_ContabilED;
import com.master.rn.Importacao_ContabilRN;
import com.master.ed.Movimento_Contabil_TempED;
import com.master.util.Excecoes;

public class ctb003Bean {

  public void inclui (HttpServletRequest request) throws Excecoes {

    Importacao_ContabilED ed = new Importacao_ContabilED ();

    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (oid_Unidade != null && !oid_Unidade.equals ("null")) {
      ed.setOid_Unidade (new Long (oid_Unidade).longValue ());
    }
    ed.setDM_Tipo_Lancamento (request.getParameter ("FT_DM_Tipo_Lancamento"));
    ed.setDT_Lancamento_Inicial (request.getParameter ("FT_DT_Emissao_Inicial"));
    ed.setDT_Lancamento_Final (request.getParameter ("FT_DT_Emissao_Final"));

    try {
      if (!"11".equals(ed.getDM_Tipo_Lancamento())){
        Movimento_Contabil_TempED edMovCtb = new Movimento_Contabil_TempED ();
        edMovCtb.setDt_Movimento (ed.getDT_Lancamento_Inicial ());
        if (!edMovCtb.isPeriodo_Aberto ()) {
          throw new Excecoes ("Período fechado !!! " + ed.getDT_Lancamento_Inicial ());
        }
        edMovCtb.setDt_Movimento (ed.getDT_Lancamento_Final ());
        if (!edMovCtb.isPeriodo_Aberto ()) {
          throw new Excecoes ("Período fechado !!! " + ed.getDT_Lancamento_Final ());
        }
      }

      new Importacao_ContabilRN ().inclui (ed);
    }
    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "inclui(Importacao_ContabilED ed)");
    }
  }

  public void exclui (HttpServletRequest request) throws Excecoes {

    Importacao_ContabilED ed = new Importacao_ContabilED ();

    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (oid_Unidade != null && !oid_Unidade.equals ("null")) {
      ed.setOid_Unidade (new Long (oid_Unidade).longValue ());
    }
    ed.setDM_Tipo_Lancamento (request.getParameter ("FT_DM_Tipo_Lancamento"));
    ed.setDT_Lancamento_Inicial (request.getParameter ("FT_DT_Emissao_Inicial"));
    ed.setDT_Lancamento_Final (request.getParameter ("FT_DT_Emissao_Final"));

    try {
      if (!"11".equals(ed.getDM_Tipo_Lancamento())){
        Movimento_Contabil_TempED edMovCtb = new Movimento_Contabil_TempED ();
        edMovCtb.setDt_Movimento (ed.getDT_Lancamento_Inicial ());
        if (!edMovCtb.isPeriodo_Aberto ()) {
          throw new Excecoes ("Período fechado !!! " + ed.getDT_Lancamento_Inicial ());
        }
        edMovCtb.setDt_Movimento (ed.getDT_Lancamento_Final ());
        if (!edMovCtb.isPeriodo_Aberto ()) {
          throw new Excecoes ("Período fechado !!! " + ed.getDT_Lancamento_Final ());
        }
      }

      new Importacao_ContabilRN ().exclui (ed);
    }
    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "exclui(Importacao_ContabilED ed)");
    }
  }

}