package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.ContaED;
import com.master.rl.JasperRL;
import com.master.rn.ContaRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

public class Fin006Bean
    extends JavaUtil {

  public ContaED inclui (HttpServletRequest request) throws Excecoes {

    ContaED ed = new ContaED ();

    ed.setCd_Conta (request.getParameter ("FT_CD_Conta"));
    ed.setNm_Conta (request.getParameter ("FT_NM_Conta"));

    ed.setCd_Estrutural (request.getParameter ("FT_CD_Estrutural"));
    ed.setCd_Estrutural_Sped(request.getParameter ("FT_CD_Estrutural_Sped"));
    ed.setDm_Tipo_Conta (request.getParameter ("FT_DM_Tipo_Conta"));

    if ("S".equals (request.getParameter ("FT_DM_Tipo_Conta"))) {
      ed.setOid_Grupo_Conta (new Integer ("0"));
      ed.setOid_Historico (new Integer ("0"));
      ed.setOid_Tipo_Documento (new Integer ("0"));
      ed.setDm_Apropriacao ("X");
      ed.setDm_Despesa ("X");
      ed.setDm_Tipo_Operacao ("N");
      ed.setCd_Conta_Contabil ("");
      ed.setOid_Natureza_Operacao (new Integer ("0"));
      ed.setDm_Centro_Custo ("N");
      ed.setDm_Zera ("F");
    }
    else {
      ed.setOid_Grupo_Conta (new Integer (request.getParameter ("FT_OID_Grupo_Conta")));
      ed.setOid_Historico (new Integer (request.getParameter ("oid_Historico")));
      ed.setOid_Tipo_Documento (new Integer (request.getParameter ("oid_Tipo_Documento")));
      ed.setDm_Apropriacao (request.getParameter ("FT_DM_Apropriacao"));
      ed.setDm_Despesa (request.getParameter ("FT_DM_Despesa"));
      ed.setDm_Tipo_Operacao (request.getParameter ("FT_DM_Tipo"));
      ed.setCd_Conta_Contabil (request.getParameter ("FT_CD_Conta_Contabil"));
      ed.setOid_Natureza_Operacao (new Integer (request.getParameter ("oid_Natureza_Operacao")));

      ed.setDm_Centro_Custo (request.getParameter ("FT_DM_Centro_Custo"));
      ed.setDm_Zera (request.getParameter ("FT_DM_Zera"));
    }

    return new ContaRN ().inclui (ed);

  }

  public ContaED inclui_Orcamento (HttpServletRequest request) throws Excecoes {

    ContaED ed = new ContaED ();
    ed.setOid_Conta (new Integer (request.getParameter ("oid_Conta")));
    ed.setDm_Orcado(request.getParameter ("FT_NM_Ano")+"-"+request.getParameter ("FT_NM_Mes"));
    ed.setVL_Orcado(new Double (request.getParameter ("FT_VL_Orcado")).doubleValue());
    return new ContaRN ().inclui_Orcamento (ed);

  }

  public ContaED copia_Orcamento (HttpServletRequest request) throws Excecoes {

    ContaED ed = new ContaED ();
    if (request.getParameter ("oid_Conta") !=null && !request.getParameter ("oid_Conta").equals("null") && !request.getParameter ("oid_Conta").equals("")){
      ed.setOid_Conta (new Integer (request.getParameter ("oid_Conta")));
    }
    ed.setDm_Orcado(request.getParameter ("FT_NM_Ano")+"-"+request.getParameter ("FT_NM_Mes"));
    ed.setDm_Orcado_Destino(request.getParameter ("FT_NM_Ano_Destino")+"-"+request.getParameter ("FT_NM_Mes_Destino"));
    return new ContaRN ().copia_Orcamento (ed);

  }

  public void altera (HttpServletRequest request) throws Excecoes {

    ContaRN contaRN = new ContaRN ();
    ContaED ed = new ContaED ();

    ed.setOid_Conta (new Integer (request.getParameter ("oid_Conta")));

    ed.setCd_Conta (request.getParameter ("FT_CD_Conta"));
    ed.setNm_Conta (request.getParameter ("FT_NM_Conta"));
    ed.setOid_Historico (new Integer (request.getParameter ("oid_Historico")));
    ed.setOid_Tipo_Documento (new Integer (request.getParameter ("oid_Tipo_Documento")));
    ed.setDm_Apropriacao (request.getParameter ("FT_DM_Apropriacao"));
    ed.setDm_Despesa (request.getParameter ("FT_DM_Despesa"));
    ed.setDm_Tipo_Operacao (request.getParameter ("FT_DM_Tipo"));
    ed.setCd_Conta_Contabil (request.getParameter ("FT_CD_Conta_Contabil"));
    ed.setOid_Natureza_Operacao (new Integer (request.getParameter ("oid_Natureza_Operacao")));
    ed.setOid_Grupo_Conta (new Integer (request.getParameter ("FT_OID_Grupo_Conta")));

    ed.setCd_Estrutural (request.getParameter ("FT_CD_Estrutural"));
    ed.setDm_Centro_Custo (request.getParameter ("FT_DM_Centro_Custo"));
    ed.setDm_Tipo_Conta (request.getParameter ("FT_DM_Tipo_Conta"));
    ed.setDm_Zera (request.getParameter ("FT_DM_Zera"));
    //ed.setNm_Grau(request.getParameter(0));

     contaRN.altera (ed);
  }

  public void altera_fluxo (HttpServletRequest request) throws Excecoes {

	    ContaRN contaRN = new ContaRN ();
	    ContaED ed = new ContaED ();

	    ed.setOid_Conta (new Integer (request.getParameter ("oid_Conta")));
	    ed.setDM_Pedido_Compra(request.getParameter("FT_DM_Pedido_Compra"));
	    ed.setDM_Fluxo_Caixa(request.getParameter("FT_DM_Fluxo_Caixa"));
	    ed.setDM_Vencimento(request.getParameter("FT_DM_Vencimento"));
	    contaRN.altera_fluxo (ed);
	  }

  public void altera_Grupo (HttpServletRequest request) throws Excecoes {

	    ContaRN contaRN = new ContaRN ();
	    ContaED ed = new ContaED ();

	    ed.setOid_Conta (new Integer (request.getParameter ("oid_Conta")));
	    ed.setOid_Grupo_Conta (new Integer (request.getParameter ("oid_Grupo_Conta")));
	    contaRN.altera_Grupo (ed);
	  }

  public void exclui_Conta_Grupo (HttpServletRequest request) throws Excecoes {

	    ContaRN contaRN = new ContaRN ();
	    ContaED ed = new ContaED ();

	    ed.setOid_Conta (new Integer (request.getParameter ("oid_Conta")));
	    contaRN.altera_Grupo (ed);
  }

  public void altera_Orcamento (HttpServletRequest request) throws Excecoes {

    ContaRN contaRN = new ContaRN ();
    ContaED ed = new ContaED ();

    ed.setOid_Orcamento_Conta (request.getParameter ("oid_Orcamento_Conta"));

    ed.setVL_Orcado (new Double (request.getParameter ("FT_VL_Orcado")).doubleValue ());

    contaRN.altera_Orcamento (ed);
  }

  public void deleta (HttpServletRequest request) throws Excecoes {

    ContaRN Contarn = new ContaRN ();
    ContaED ed = new ContaED ();

    ed.setOid_Conta (new Integer (request.getParameter ("oid_Conta")));

    Contarn.deleta (ed);
  }

  public void deleta_Orcamento (HttpServletRequest request) throws Excecoes {

    ContaRN Contarn = new ContaRN ();
    ContaED ed = new ContaED ();

    ed.setOid_Orcamento_Conta (request.getParameter ("oid_Orcamento_Conta"));

    Contarn.deleta_Orcamento (ed);
  }

  public ArrayList Conta_Lista (HttpServletRequest request) throws Excecoes {

    ContaED ed = new ContaED ();

    String oid_Grupo_Conta = request.getParameter ("oid_Grupo_Conta");
    String FT_OID_Grupo_Conta = request.getParameter ("FT_OID_Grupo_Conta");
    String oid_Conta = request.getParameter ("oid_Conta");
    String cd_Conta = request.getParameter ("FT_CD_Conta");
    String nm_Conta = request.getParameter ("FT_NM_Conta");
    String cd_Estrutural = request.getParameter ("FT_CD_Estrutural");
    String dm_Tipo_Conta = request.getParameter ("FT_DM_Tipo_Conta");
    String dm_Relatorio = request.getParameter ("FT_DM_Relatorio");

    if (doValida (oid_Conta)) {
      ed.setOid_Conta (new Integer (oid_Conta));
    }
    if (doValida (cd_Conta)) {
      ed.setCd_Conta (cd_Conta);
    }
    if (doValida (FT_OID_Grupo_Conta)) {
        ed.setOid_Grupo_Conta (new Integer (FT_OID_Grupo_Conta));
    }
    if (doValida (oid_Grupo_Conta)) {
      ed.setOid_Grupo_Conta (new Integer (oid_Grupo_Conta));
    }
    if (doValida (nm_Conta)) {
      ed.setNm_Conta (nm_Conta);
    }
    if (doValida (cd_Estrutural)) {
      ed.setCd_Estrutural (cd_Estrutural);
    }
    if (doValida (dm_Tipo_Conta)) {
      ed.setDm_Tipo_Conta (dm_Tipo_Conta);
    }
    if (doValida (dm_Relatorio)) {
      ed.setDM_Relatorio (dm_Relatorio);
    }

    return new ContaRN ().lista (ed);
  }

  public ArrayList lista_Orcamento (HttpServletRequest request) throws Excecoes {

    ContaED ed = new ContaED ();

    String oid_Conta = request.getParameter ("oid_Conta");
    String dm_Orcado = request.getParameter ("FT_NM_Ano")+"-"+request.getParameter ("FT_NM_Mes");

    // System.out.println("dm_Orcado=>>"+dm_Orcado);
    if (doValida (oid_Conta)) {
      ed.setOid_Conta (new Integer (oid_Conta));
    }
    if (doValida (dm_Orcado) && !"-------".endsWith(dm_Orcado)) {
      ed.setDm_Orcado (dm_Orcado);
    }

    return new ContaRN ().lista_Orcamento (ed);
  }

  public ContaED getByRecord (HttpServletRequest request) throws Excecoes {

    ContaED ed = new ContaED ();
    String oid_Conta = request.getParameter ("oid_Conta");
    String cd_Conta = request.getParameter ("FT_CD_Conta");
    String DM_Pedido_Compra = request.getParameter ("FT_DM_Pedido_Compra");
    String DM_Fluxo_Caixa = request.getParameter ("FT_DM_Fluxo_Caixa");
    String DM_Vencimento = request.getParameter ("FT_DM_Vencimento");


    if (oid_Conta != null && oid_Conta.length () > 0) {
      ed.setOid_Conta (new Integer (oid_Conta));
    }
    else if (cd_Conta != null && !cd_Conta.equals ("0")) {
      ed.setCd_Conta (cd_Conta);
    }
    if (DM_Pedido_Compra != null && DM_Pedido_Compra.length () > 0) {
        ed.setDM_Pedido_Compra (DM_Pedido_Compra);
      }
    if (DM_Fluxo_Caixa != null && DM_Fluxo_Caixa.length () > 0) {
        ed.setDM_Fluxo_Caixa (DM_Fluxo_Caixa);
      }
    if (DM_Vencimento != null && DM_Vencimento.length () > 0) {
        ed.setDM_Vencimento (DM_Vencimento);
      }
    return new ContaRN ().getByRecord (ed);
  }

  public ContaED getByOrcamento (HttpServletRequest request) throws Excecoes {

    ContaED ed = new ContaED ();

    String oid_Orcamento_Conta = request.getParameter ("oid_Orcamento_Conta");
    ed.setOid_Orcamento_Conta (oid_Orcamento_Conta);

    if (request.getParameter ("oid_Conta") !=null && !request.getParameter ("oid_Conta").equals("null") && !request.getParameter ("oid_Conta").equals("")){
      ed.setOid_Conta (new Integer (request.getParameter ("oid_Conta")));
    }

    ed.setDm_Orcado(request.getParameter ("FT_NM_Ano")+"-"+request.getParameter ("FT_NM_Mes"));

    // System.out.println("getByOrcamento");

    return new ContaRN ().getByOrcamento (ed);
  }

  public ContaED getByOrcamento (String oid_Orcamento_Conta) throws Excecoes {

    ContaED ed = new ContaED ();
    ed.setOid_Orcamento_Conta (oid_Orcamento_Conta);
    return new ContaRN ().getByOrcamento (ed);
  }

  public ContaED getByOrcamento (String oid_Conta, String DM_Orcado) throws Excecoes {

	    ContaED ed = new ContaED ();
	    if (doValida (oid_Conta)) {
		    ed.setOid_Conta (new Integer (oid_Conta));
	    }
	    ed.setDm_Orcado(DM_Orcado);

	    return new ContaRN ().getByOrcamento (ed);
	  }

  public ContaED getByCD (HttpServletRequest request) throws Excecoes {

    String cd_Conta = request.getParameter ("FT_CD_Conta");
    if (!doValida (cd_Conta)) {
      return new ContaED ();
    }

    ContaED ed = new ContaED ();
    ed.setCd_Conta (cd_Conta);
    return new ContaRN ().getByRecord (ed);
  }

  public ContaED getByCD (String CD_Conta) throws Excecoes {

    if (!doValida (CD_Conta)) {
      return new ContaED ();
    }
    ContaED ed = new ContaED ();
    ed.setCd_Conta (CD_Conta);
    return new ContaRN ().getByRecord (ed);
  }

  public ContaED getByOid (String oid_Conta) throws Excecoes {

    if (!doValida (oid_Conta)) {
      return new ContaED ();
    }

    return new ContaRN ().getByRecord (new ContaED (new Integer (oid_Conta)));
  }

  public ContaED getByOid (int oid_Conta) throws Excecoes {

              // System.out.println("contaVolta oid_Conta->>>" + oid_Conta);

    ContaED ed = new ContaED ();
    ed.setOid_Conta (new Integer (oid_Conta));
    return new ContaRN ().getByRecord (ed);
  }

  public ContaED getByRecord_Credito (HttpServletRequest request) throws Excecoes {

    ContaED ed = new ContaED ();

    String oid_Conta = request.getParameter ("oid_Conta_Credito");
    String cd_Conta = request.getParameter ("FT_CD_Conta_Credito");

    if (oid_Conta != null && oid_Conta.length () > 0) {
      ed.setOid_Conta (new Integer (oid_Conta));
    }
    if (cd_Conta != null && !cd_Conta.equals ("0")) {
      ed.setCd_Conta (cd_Conta);
    }

    return new ContaRN ().getByRecord (ed);

  }

  public byte[] geraRelacaoContas (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {

    ContaED ed = new ContaED ();

    String oid_Grupo_Conta = request.getParameter ("FT_OID_Grupo_Conta");
    if (doValida (oid_Grupo_Conta)) {
      ed.setOid_Grupo_Conta (new Integer (oid_Grupo_Conta));
    }

    ed.setDM_Relatorio (request.getParameter ("FT_DM_Relatorio"));

    ContaRN geRN = new ContaRN ();
    byte[] b = geRN.geraRelacaoContas (ed);

    return b;

  }

  public byte[] geraRelOrcamento (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {

    ContaED ed = new ContaED ();

    String oid_Conta = request.getParameter ("oid_Conta");
    String dm_Orcado = request.getParameter ("FT_NM_Ano")+"-"+request.getParameter ("FT_NM_Mes");

    // System.out.println("dm_Orcado=>>"+dm_Orcado);
    if (doValida (oid_Conta)) {
      ed.setOid_Conta (new Integer (oid_Conta));
    }
    if (doValida (dm_Orcado) && !"-------".endsWith(dm_Orcado)) {
      ed.setDm_Orcado (dm_Orcado);
    }

    ed.setDM_Relatorio (request.getParameter ("FT_DM_Relatorio"));

    ContaRN geRN = new ContaRN ();
    byte[] b = geRN.geraRelOrcamento (ed);

    return b;

  }

  public void geraRelatorioContas (HttpServletRequest request , HttpServletResponse response) throws Excecoes {

    ContaED ed = new ContaED ();
    ArrayList listContasRel = new ArrayList ();
    listContasRel = this.Conta_Lista (request); // Chama o método que retorna a lista

    ed.setLista (listContasRel); // Joga a lista de contas no ed para enviar pro relatório
    ed.setResponse (response);
    ed.setNomeRelatorio ("Contas_Estrutural"); // Seta o nome do relatório
    new JasperRL (ed).geraRelatorio (); // Chama o relatorio passando o ed
  }

}
