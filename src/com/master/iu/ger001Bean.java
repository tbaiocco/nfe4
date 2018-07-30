package com.master.iu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.GerencialED;
import com.master.rn.GerencialRN;
import com.master.util.Excecoes;
import com.master.util.Data;

public class ger001Bean {

  public byte[] geraAnalise_Gerencial_Movimento_Conhecimentos (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {

//// System.out.println("a 0");
    GerencialED ed = new GerencialED ();
//// System.out.println("a");

    String oid_Pessoa = request.getParameter ("oid_Pessoa");
    if (String.valueOf (oid_Pessoa) != null && !String.valueOf (oid_Pessoa).equals ("") && !String.valueOf (oid_Pessoa).equals ("null")) {
      ed.setOID_Pessoa (oid_Pessoa);
    }
    String oid_Pessoa_Remetente = request.getParameter ("oid_Pessoa_Remetente");
    if (String.valueOf (oid_Pessoa_Remetente) != null && !String.valueOf (oid_Pessoa_Remetente).equals ("") && !String.valueOf (oid_Pessoa_Remetente).equals ("null")) {
      ed.setOID_Pessoa_Remetente (oid_Pessoa_Remetente);
    }
    String oid_Pessoa_Destinatario = request.getParameter ("oid_Pessoa_Destinatario");
    if (String.valueOf (oid_Pessoa_Destinatario) != null && !String.valueOf (oid_Pessoa_Destinatario).equals ("") && !String.valueOf (oid_Pessoa_Destinatario).equals ("null")) {
      ed.setOID_Pessoa_Destinatario (oid_Pessoa_Destinatario);
    }
    String oid_Pessoa_Pagador = request.getParameter ("oid_Pessoa_Pagador");
    if (String.valueOf (oid_Pessoa_Pagador) != null && !String.valueOf (oid_Pessoa_Pagador).equals ("") && !String.valueOf (oid_Pessoa_Pagador).equals ("null")) {
      ed.setOID_Pessoa_Pagador (oid_Pessoa_Pagador);
    }

    String oid_Pessoa_Fornecedor = request.getParameter ("oid_Pessoa_Fornecedor");
    if (String.valueOf (oid_Pessoa_Fornecedor) != null && !String.valueOf (oid_Pessoa_Fornecedor).equals ("") && !String.valueOf (oid_Pessoa_Fornecedor).equals ("null")) {
      ed.setOID_Pessoa_Fornecedor (oid_Pessoa_Fornecedor);
    }

    String oid_Vendedor = request.getParameter ("oid_Vendedor");
    if (String.valueOf (oid_Vendedor) != null && !String.valueOf (oid_Vendedor).equals ("") && !String.valueOf (oid_Vendedor).equals ("null")) {
      ed.setOID_Vendedor (oid_Vendedor);
      ed.setNM_Vendedor (request.getParameter ("FT_NM_Fantasia_Vendedor"));
    }

    //// System.out.println("vendedor iu " + ed.getOID_Vendedor());

    String DT_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
    if (String.valueOf (DT_Emissao_Inicial) != null && !String.valueOf (DT_Emissao_Inicial).equals ("")) {
      ed.setDT_Emissao_Inicial (DT_Emissao_Inicial);
    }

    String DT_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");
    if (String.valueOf (DT_Emissao_Final) != null && !String.valueOf (DT_Emissao_Final).equals ("")) {
      ed.setDT_Emissao_Final (DT_Emissao_Final);
    }

    String DT_Movimento_Inicial = request.getParameter ("FT_DT_Movimento_Inicial");
    if (String.valueOf (DT_Movimento_Inicial) != null && !String.valueOf (DT_Movimento_Inicial).equals ("")) {
      ed.setDT_Movimento_Inicial (DT_Movimento_Inicial);
    }

    String DT_Movimento_Final = request.getParameter ("FT_DT_Movimento_Final");
    if (String.valueOf (DT_Movimento_Final) != null && !String.valueOf (DT_Movimento_Final).equals ("")) {
      ed.setDT_Movimento_Final (DT_Movimento_Final);
    }

//// System.out.println("c");

    String DM_Frete = request.getParameter ("FT_DM_Frete");
    if (String.valueOf (DM_Frete) != null && !String.valueOf (DM_Frete).equals ("")) {
      ed.setDM_Frete (DM_Frete);
    }

    String DM_Pessoa = request.getParameter ("FT_DM_Pessoa");
    if (String.valueOf (DM_Pessoa) != null && !String.valueOf (DM_Pessoa).equals ("")) {
      ed.setDM_Pessoa (DM_Pessoa);
    }

    String DM_Classificar = request.getParameter ("FT_DM_Classificar");
    if (String.valueOf (DM_Classificar) != null && !String.valueOf (DM_Classificar).equals ("")) {
      ed.setDM_Classificar (DM_Classificar);
    }

    String NR_Amostra = request.getParameter ("FT_NR_Amostra");
    if (String.valueOf (NR_Amostra) != null && !String.valueOf (NR_Amostra).equals ("")) {
      ed.setNR_Amostra (NR_Amostra);
    }

    String DM_Tipo_Pagamento = request.getParameter ("FT_DM_Tipo_Pagamento");
    if (String.valueOf (DM_Tipo_Pagamento) != null && !String.valueOf (DM_Tipo_Pagamento).equals ("")) {
      ed.setDM_Tipo_Pagamento (DM_Tipo_Pagamento);
    }
//// System.out.println("e");

    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (String.valueOf (oid_Unidade) != null && !String.valueOf (oid_Unidade).equals ("")) {
      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
    }

    String oid_Modal = request.getParameter ("oid_Modal");
    if (String.valueOf (oid_Modal) != null && !String.valueOf (oid_Modal).equals ("")) {
      ed.setOID_Modal (new Long (oid_Modal).longValue ());
    }

    String oid_Grupo_Economico = request.getParameter ("oid_Grupo_Economico");
    if (String.valueOf (oid_Grupo_Economico) != null && !String.valueOf (oid_Grupo_Economico).equals ("")) {
      ed.setOID_Grupo_Economico (new Long (oid_Grupo_Economico).longValue ());
    }

    String oid_Produto = request.getParameter ("oid_Produto");
    if (String.valueOf (oid_Produto) != null && !String.valueOf (oid_Produto).equals ("")) {
      ed.setOID_Produto (new Long (oid_Produto).longValue ());
    }

    String oid_Cidade_Origem = request.getParameter ("oid_Cidade_Origem");
    if (String.valueOf (oid_Cidade_Origem) != null && !String.valueOf (oid_Cidade_Origem).equals ("")) {
      ed.setOID_Cidade_Origem (new Long (oid_Cidade_Origem).longValue ());
    }

    String oid_Cidade_Destino = request.getParameter ("oid_Cidade_Destino");
    if (String.valueOf (oid_Cidade_Destino) != null && !String.valueOf (oid_Cidade_Destino).equals ("")) {
      ed.setOID_Cidade_Destino (new Long (oid_Cidade_Destino).longValue ());
    }

    String oid_Empresa = request.getParameter ("oid_Empresa");

    //// System.out.println("oid_Empresa" + oid_Empresa );

//      if (String.valueOf(oid_Empresa) != null &&
//          !String.valueOf(oid_Empresa).equals("") &&
//          !String.valueOf(oid_Empresa).equals("null")){
//         ed.setOID_Empresa(new Long(request.getParameter("oid_Empresa")).longValue());
//    }
//
//         ed.setNM_Empresa(request.getParameter("FT_NM_Empresa"));
//              //// System.out.println("FT_NM_Empresa" +  ed.getNM_Empresa() );



    ed.setDM_Lista_Conhecimento (request.getParameter ("FT_DM_Lista_Conhecimento"));
    ed.setDM_Relatorio (request.getParameter ("FT_DM_Relatorio"));

    GerencialRN geRN = new GerencialRN ();

    return geRN.geraAnalise_Gerencial_Movimento_Conhecimentos (ed);

  }

  public byte[] geraAnalise_Gerencial_Conhecimentos (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {

//// System.out.println("a 0");
    GerencialED ed = new GerencialED ();
//// System.out.println("a");

    String oid_Pessoa = request.getParameter ("oid_Pessoa");
    if (String.valueOf (oid_Pessoa) != null && !String.valueOf (oid_Pessoa).equals ("") && !String.valueOf (oid_Pessoa).equals ("null")) {
      ed.setOID_Pessoa (oid_Pessoa);
    }
    String oid_Pessoa_Remetente = request.getParameter ("oid_Pessoa_Remetente");
    if (String.valueOf (oid_Pessoa_Remetente) != null && !String.valueOf (oid_Pessoa_Remetente).equals ("") && !String.valueOf (oid_Pessoa_Remetente).equals ("null")) {
      ed.setOID_Pessoa_Remetente (oid_Pessoa_Remetente);
    }
    String oid_Pessoa_Destinatario = request.getParameter ("oid_Pessoa_Destinatario");
    if (String.valueOf (oid_Pessoa_Destinatario) != null && !String.valueOf (oid_Pessoa_Destinatario).equals ("") && !String.valueOf (oid_Pessoa_Destinatario).equals ("null")) {
      ed.setOID_Pessoa_Destinatario (oid_Pessoa_Destinatario);
    }
    String oid_Pessoa_Pagador = request.getParameter ("oid_Pessoa_Pagador");
    if (String.valueOf (oid_Pessoa_Pagador) != null && !String.valueOf (oid_Pessoa_Pagador).equals ("") && !String.valueOf (oid_Pessoa_Pagador).equals ("null")) {
      ed.setOID_Pessoa_Pagador (oid_Pessoa_Pagador);
    }

    String oid_Vendedor = request.getParameter ("oid_Vendedor");
    if (String.valueOf (oid_Vendedor) != null && !String.valueOf (oid_Vendedor).equals ("") && !String.valueOf (oid_Vendedor).equals ("null")) {
      ed.setOID_Vendedor (oid_Vendedor);
      ed.setNM_Vendedor (request.getParameter ("FT_NM_Fantasia_Vendedor"));
    }

    //// System.out.println("vendedor iu " + ed.getOID_Vendedor());

    String DT_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
    if (String.valueOf (DT_Emissao_Inicial) != null && !String.valueOf (DT_Emissao_Inicial).equals ("")) {
      ed.setDT_Emissao_Inicial (DT_Emissao_Inicial);
    }

    String DT_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");
    if (String.valueOf (DT_Emissao_Final) != null && !String.valueOf (DT_Emissao_Final).equals ("")) {
      ed.setDT_Emissao_Final (DT_Emissao_Final);
    }
//// System.out.println("c");

    String DM_Frete = request.getParameter ("FT_DM_Frete");
    if (String.valueOf (DM_Frete) != null && !String.valueOf (DM_Frete).equals ("")) {
      ed.setDM_Frete (DM_Frete);
    }

    String DM_Vendedor = request.getParameter ("FT_DM_Vendedor");
    ed.setDM_Vendedor("CL");
    if (String.valueOf (DM_Vendedor) != null && !String.valueOf (DM_Vendedor).equals ("")) {
      ed.setDM_Vendedor (DM_Vendedor);
    }

    String DM_Situacao_Cliente = request.getParameter ("FT_DM_Situacao_Cliente");
    if (String.valueOf (DM_Situacao_Cliente) != null && !String.valueOf (DM_Situacao_Cliente).equals ("") && !String.valueOf (DM_Situacao_Cliente).equals ("null")) {
      ed.setDM_Situacao_Cliente (DM_Situacao_Cliente);
    }

    String DM_Pessoa = request.getParameter ("FT_DM_Pessoa");
    if (String.valueOf (DM_Pessoa) != null && !String.valueOf (DM_Pessoa).equals ("")) {
      ed.setDM_Pessoa (DM_Pessoa);
    }

    String DM_Classificar = request.getParameter ("FT_DM_Classificar");
    if (String.valueOf (DM_Classificar) != null && !String.valueOf (DM_Classificar).equals ("")) {
      ed.setDM_Classificar (DM_Classificar);
    }

    String NR_Amostra = request.getParameter ("FT_NR_Amostra");
    if (String.valueOf (NR_Amostra) != null && !String.valueOf (NR_Amostra).equals ("")) {
      ed.setNR_Amostra (NR_Amostra);
    }

    String DM_Tipo_Pagamento = request.getParameter ("FT_DM_Tipo_Pagamento");
    if (String.valueOf (DM_Tipo_Pagamento) != null && !String.valueOf (DM_Tipo_Pagamento).equals ("")) {
      ed.setDM_Tipo_Pagamento (DM_Tipo_Pagamento);
    }

    String DM_Tipo_Documento = request.getParameter ("FT_DM_Tipo_Documento");
    if (String.valueOf (DM_Tipo_Documento) != null && !String.valueOf (DM_Tipo_Documento).equals ("")) {
      ed.setDM_Tipo_Documento (DM_Tipo_Documento);
    }

//// System.out.println("e");

    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (String.valueOf (oid_Unidade) != null && !String.valueOf (oid_Unidade).equals ("")) {
      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
      ed.setNM_Unidade (request.getParameter ("FT_NM_Fantasia"));
    }

    String oid_Modal = request.getParameter ("oid_Modal");
    if (String.valueOf (oid_Modal) != null && !String.valueOf (oid_Modal).equals ("")) {
      ed.setOID_Modal (new Long (oid_Modal).longValue ());
    }

    String oid_Grupo_Economico = request.getParameter ("oid_Grupo_Economico");
    if (String.valueOf (oid_Grupo_Economico) != null && !String.valueOf (oid_Grupo_Economico).equals ("")) {
      ed.setOID_Grupo_Economico (new Long (oid_Grupo_Economico).longValue ());
    }

    String oid_Produto = request.getParameter ("oid_Produto");
    if (String.valueOf (oid_Produto) != null && !String.valueOf (oid_Produto).equals ("")) {
      ed.setOID_Produto (new Long (oid_Produto).longValue ());
    }


    String Dm_Origem = request.getParameter ("FT_DM_Origem");
    if (String.valueOf (Dm_Origem) != null && !String.valueOf (Dm_Origem).equals ("")) {
      ed.setDM_Origem (Dm_Origem);
    }

    String Dm_Destino = request.getParameter ("FT_DM_Destino");
    if (String.valueOf (Dm_Destino) != null && !String.valueOf (Dm_Destino).equals ("")) {
      ed.setDM_Destino (Dm_Destino);
    }

    ed.setOID_Origem (0);
    if (!Dm_Origem.equals ("T")) {
      String oid_Origem = request.getParameter ("oid_Origem");
      if (String.valueOf (oid_Origem) != null && !String.valueOf (oid_Origem).equals ("0")) {
        ed.setOID_Origem (new Long (oid_Origem).longValue ());
      }
    }

    ed.setOID_Destino (0);
    if (!Dm_Destino.equals ("T")) {
      String oid_Destino = request.getParameter ("oid_Destino");
      if (String.valueOf (oid_Destino) != null && !String.valueOf (oid_Destino).equals ("0")) {
        ed.setOID_Destino (new Long (oid_Destino).longValue ());
      }
    }

    String oid_Empresa = request.getParameter ("oid_Empresa");

    // System.out.println ("oid_Empresa" + oid_Empresa);

    if (String.valueOf (oid_Empresa) != null &&
        !String.valueOf (oid_Empresa).equals ("") &&
        !String.valueOf (oid_Empresa).equals ("null")) {
      ed.setOID_Empresa (new Long (request.getParameter ("oid_Empresa")).longValue ());
      ed.setNM_Empresa (request.getParameter ("FT_NM_Empresa"));
    }
   
    String VL_Margem_Inicial = request.getParameter ("FT_VL_Margem_Inicial");
    if (String.valueOf (VL_Margem_Inicial) != null && !String.valueOf (VL_Margem_Inicial).equals ("") && !String.valueOf (VL_Margem_Inicial).equals ("null")) {
      ed.setVL_Margem_Inicial (new Double (VL_Margem_Inicial).doubleValue ());
    }

    String VL_Margem_Final = request.getParameter ("FT_VL_Margem_Final");
    if (String.valueOf (VL_Margem_Final) != null && !String.valueOf (VL_Margem_Final).equals ("") && !String.valueOf (VL_Margem_Final).equals ("null")) {
      ed.setVL_Margem_Final (new Double (VL_Margem_Final).doubleValue ());
    }


    ed.setDM_Lista_Conhecimento (request.getParameter ("FT_DM_Lista_Conhecimento"));
    ed.setDM_Relatorio (request.getParameter ("FT_DM_Relatorio"));

    GerencialRN geRN = new GerencialRN ();

    return geRN.geraAnalise_Gerencial_Conhecimentos (ed);

  }

  public byte[] geraAnalise_Gerencial_Clientes (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {

//// System.out.println("a 0");
    GerencialED ed = new GerencialED ();
//// System.out.println("a");

    // System.out.println ("geraAnalise_Gerencial_Clientes");

    String oid_Pessoa = request.getParameter ("oid_Pessoa");
    if (String.valueOf (oid_Pessoa) != null && !String.valueOf (oid_Pessoa).equals ("") && !String.valueOf (oid_Pessoa).equals ("null")) {
      ed.setOID_Pessoa (oid_Pessoa);
    }

    String NM_Razao_Social = request.getParameter ("FT_NM_Razao_Social");
    if (String.valueOf (NM_Razao_Social) != null && !String.valueOf (NM_Razao_Social).equals ("") && !String.valueOf (NM_Razao_Social).equals ("null")) {
      ed.setNM_Razao_Social (NM_Razao_Social);
    }

    String NM_Grupo_Economico = request.getParameter ("FT_NM_Grupo_Economico");
    if (String.valueOf (NM_Grupo_Economico) != null && !String.valueOf (NM_Grupo_Economico).equals ("") && !String.valueOf (NM_Grupo_Economico).equals ("null")) {
      ed.setNM_Razao_Social (NM_Grupo_Economico);
    }

    String NM_Mes_Inicial = request.getParameter ("FT_NM_Mes_Inicial");
    if (String.valueOf (NM_Mes_Inicial) != null && !String.valueOf (NM_Mes_Inicial).equals ("") && !String.valueOf (NM_Mes_Inicial).equals ("null")) {
      ed.setNM_Mes_Inicial (NM_Mes_Inicial);
    }
    String NM_Mes_Final = request.getParameter ("FT_NM_Mes_Final");
    if (String.valueOf (NM_Mes_Final) != null && !String.valueOf (NM_Mes_Final).equals ("") && !String.valueOf (NM_Mes_Final).equals ("null")) {
      ed.setNM_Mes_Final (NM_Mes_Final);
    }
    String NM_Ano_Inicial = request.getParameter ("FT_NM_Ano_Inicial");
    if (String.valueOf (NM_Ano_Inicial) != null && !String.valueOf (NM_Ano_Inicial).equals ("") && !String.valueOf (NM_Ano_Inicial).equals ("null")) {
      ed.setNM_Ano_Inicial (NM_Ano_Inicial);
    }
    String NM_Ano_Final = request.getParameter ("FT_NM_Ano_Final");
    if (String.valueOf (NM_Ano_Final) != null && !String.valueOf (NM_Ano_Final).equals ("") && !String.valueOf (NM_Ano_Final).equals ("null")) {
      ed.setNM_Ano_Final (NM_Ano_Final);
    }

    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (String.valueOf (oid_Unidade) != null && !String.valueOf (oid_Unidade).equals ("")) {
      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
      ed.setNM_Unidade (request.getParameter ("FT_NM_Fantasia"));
    }

    String oid_Modal = request.getParameter ("oid_Modal");
    if (String.valueOf (oid_Modal) != null && !String.valueOf (oid_Modal).equals ("")) {
      ed.setOID_Modal (new Long (oid_Modal).longValue ());
    }

    String oid_Vendedor = request.getParameter ("oid_Vendedor");
    if (String.valueOf (oid_Vendedor) != null && !String.valueOf (oid_Vendedor).equals ("") && !String.valueOf (oid_Vendedor).equals ("null")) {
      ed.setOID_Vendedor (oid_Vendedor);
      ed.setNM_Vendedor (request.getParameter ("FT_NM_Fantasia_Vendedor"));
    }

    String oid_Produto = request.getParameter ("oid_Produto");
    if (String.valueOf (oid_Produto) != null && !String.valueOf (oid_Produto).equals ("")) {
      ed.setOID_Produto (new Long (oid_Produto).longValue ());
    }

    String oid_Grupo_Economico = request.getParameter ("oid_Grupo_Economico");
    if (String.valueOf (oid_Grupo_Economico) != null && !String.valueOf (oid_Grupo_Economico).equals ("")) {
      ed.setOID_Grupo_Economico (new Long (oid_Grupo_Economico).longValue ());
    }

    String oid_Cidade_Origem = request.getParameter ("oid_Cidade_Origem");
    if (String.valueOf (oid_Cidade_Origem) != null && !String.valueOf (oid_Cidade_Origem).equals ("")) {
      ed.setOID_Cidade_Origem (new Long (oid_Cidade_Origem).longValue ());
    }

    String oid_Cidade_Destino = request.getParameter ("oid_Cidade_Destino");
    if (String.valueOf (oid_Cidade_Destino) != null && !String.valueOf (oid_Cidade_Destino).equals ("")) {
      ed.setOID_Cidade_Destino (new Long (oid_Cidade_Destino).longValue ());
    }

    String oid_Empresa = request.getParameter ("oid_Empresa");

    // System.out.println ("oid_Empresa" + oid_Empresa);

    if (String.valueOf (oid_Empresa) != null &&
        !String.valueOf (oid_Empresa).equals ("") &&
        !String.valueOf (oid_Empresa).equals ("null")) {
      ed.setOID_Empresa (new Long (request.getParameter ("oid_Empresa")).longValue ());
      ed.setNM_Empresa (request.getParameter ("FT_NM_Empresa"));
    }
    // System.out.println (ed.getNM_Mes_Inicial ());
    // System.out.println (ed.getNM_Ano_Inicial ());
    // System.out.println (ed.getNM_Mes_Final ());
    // System.out.println (ed.getNM_Ano_Final ());

    int mes1 = new Integer (ed.getNM_Mes_Inicial ()).intValue ();
    int mes2 = new Integer (ed.getNM_Mes_Final ()).intValue ();
    int ano1 = new Integer (ed.getNM_Ano_Inicial ()).intValue ();
    int ano2 = new Integer (ed.getNM_Ano_Final ()).intValue ();

    ed.setDT_Emissao_Inicial ("01/" + ed.getNM_Mes_Inicial () + "/" + String.valueOf (ano1));
    ed.setDT_Emissao_Final (Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_Final () + "/" + String.valueOf (ano2)));

    // System.out.println (ed.getDT_Emissao_Inicial ());
    // System.out.println (ed.getDT_Emissao_Final ());

    ed.setDM_Relatorio (request.getParameter ("FT_DM_Relatorio"));

    GerencialRN geRN = new GerencialRN ();

    return geRN.geraAnalise_Gerencial_Clientes (ed);

  }

  public byte[] geraAnalise_Gerencial_Modal (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {

//	// System.out.println("a 0");
	    GerencialED ed = new GerencialED ();
//	// System.out.println("a");

	    // System.out.println ("geraAnalise_Gerencial_Clientes");

	    String oid_Pessoa = request.getParameter ("oid_Pessoa");
	    if (String.valueOf (oid_Pessoa) != null && !String.valueOf (oid_Pessoa).equals ("") && !String.valueOf (oid_Pessoa).equals ("null")) {
	      ed.setOID_Pessoa (oid_Pessoa);
	    }

	    String NM_Razao_Social = request.getParameter ("FT_NM_Razao_Social");
	    if (String.valueOf (NM_Razao_Social) != null && !String.valueOf (NM_Razao_Social).equals ("") && !String.valueOf (NM_Razao_Social).equals ("null")) {
	      ed.setNM_Razao_Social (NM_Razao_Social);
	    }

	    String NM_Grupo_Economico = request.getParameter ("FT_NM_Grupo_Economico");
	    if (String.valueOf (NM_Grupo_Economico) != null && !String.valueOf (NM_Grupo_Economico).equals ("") && !String.valueOf (NM_Grupo_Economico).equals ("null")) {
	      ed.setNM_Razao_Social (NM_Grupo_Economico);
	    }

	    String NM_Mes_Inicial = request.getParameter ("FT_NM_Mes_Inicial");
	    if (String.valueOf (NM_Mes_Inicial) != null && !String.valueOf (NM_Mes_Inicial).equals ("") && !String.valueOf (NM_Mes_Inicial).equals ("null")) {
	      ed.setNM_Mes_Inicial (NM_Mes_Inicial);
	    }
	    String NM_Mes_Final = request.getParameter ("FT_NM_Mes_Final");
	    if (String.valueOf (NM_Mes_Final) != null && !String.valueOf (NM_Mes_Final).equals ("") && !String.valueOf (NM_Mes_Final).equals ("null")) {
	      ed.setNM_Mes_Final (NM_Mes_Final);
	    }
	    String NM_Ano_Inicial = request.getParameter ("FT_NM_Ano_Inicial");
	    if (String.valueOf (NM_Ano_Inicial) != null && !String.valueOf (NM_Ano_Inicial).equals ("") && !String.valueOf (NM_Ano_Inicial).equals ("null")) {
	      ed.setNM_Ano_Inicial (NM_Ano_Inicial);
	    }
	    String NM_Ano_Final = request.getParameter ("FT_NM_Ano_Final");
	    if (String.valueOf (NM_Ano_Final) != null && !String.valueOf (NM_Ano_Final).equals ("") && !String.valueOf (NM_Ano_Final).equals ("null")) {
	      ed.setNM_Ano_Final (NM_Ano_Final);
	    }

	    String oid_Unidade = request.getParameter ("oid_Unidade");
	    if (String.valueOf (oid_Unidade) != null && !String.valueOf (oid_Unidade).equals ("")) {
	      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
	      ed.setNM_Unidade (request.getParameter ("FT_NM_Fantasia"));
	    }

	    String oid_Modal = request.getParameter ("oid_Modal");
	    if (String.valueOf (oid_Modal) != null && !String.valueOf (oid_Modal).equals ("")) {
	      ed.setOID_Modal (new Long (oid_Modal).longValue ());
	    }

	    String oid_Vendedor = request.getParameter ("oid_Vendedor");
	    if (String.valueOf (oid_Vendedor) != null && !String.valueOf (oid_Vendedor).equals ("") && !String.valueOf (oid_Vendedor).equals ("null")) {
	      ed.setOID_Vendedor (oid_Vendedor);
	      ed.setNM_Vendedor (request.getParameter ("FT_NM_Fantasia_Vendedor"));
	    }


	    String oid_Empresa = request.getParameter ("oid_Empresa");

	    // System.out.println ("oid_Empresa" + oid_Empresa);

	    if (String.valueOf (oid_Empresa) != null &&
	        !String.valueOf (oid_Empresa).equals ("") &&
	        !String.valueOf (oid_Empresa).equals ("null")) {
	      ed.setOID_Empresa (new Long (request.getParameter ("oid_Empresa")).longValue ());
	      ed.setNM_Empresa (request.getParameter ("FT_NM_Empresa"));
	    }
	    // System.out.println (ed.getNM_Mes_Inicial ());
	    // System.out.println (ed.getNM_Ano_Inicial ());
	    // System.out.println (ed.getNM_Mes_Final ());
	    // System.out.println (ed.getNM_Ano_Final ());

	    int mes1 = new Integer (ed.getNM_Mes_Inicial ()).intValue ();
	    int mes2 = new Integer (ed.getNM_Mes_Final ()).intValue ();
	    int ano1 = new Integer (ed.getNM_Ano_Inicial ()).intValue ();
	    int ano2 = new Integer (ed.getNM_Ano_Final ()).intValue ();

	    ed.setDT_Emissao_Inicial ("01/" + ed.getNM_Mes_Inicial () + "/" + String.valueOf (ano1));
	    ed.setDT_Emissao_Final (Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_Final () + "/" + String.valueOf (ano2)));

	    // System.out.println (ed.getDT_Emissao_Inicial ());
	    // System.out.println (ed.getDT_Emissao_Final ());

	    ed.setDM_Relatorio (request.getParameter ("FT_DM_Relatorio"));

	    GerencialRN geRN = new GerencialRN ();
	    byte[] b = geRN.geraAnalise_Gerencial_Modal (ed);

	    return b;

	  }

  	public byte[] geraAnalise_Gerencial_Tabelas_Fretes (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {

//		// System.out.println("a 0");
		    GerencialED ed = new GerencialED ();
//		// System.out.println("a");

		     System.out.println ("geraAnalise_Gerencial_Tabelas_Fretes");

		    String oid_Pessoa = request.getParameter ("oid_Pessoa");
		    if (String.valueOf (oid_Pessoa) != null && !String.valueOf (oid_Pessoa).equals ("") && !String.valueOf (oid_Pessoa).equals ("null")) {
		      ed.setOID_Pessoa (oid_Pessoa);
		    }

		    String NM_Razao_Social = request.getParameter ("FT_NM_Razao_Social");
		    if (String.valueOf (NM_Razao_Social) != null && !String.valueOf (NM_Razao_Social).equals ("") && !String.valueOf (NM_Razao_Social).equals ("null")) {
		      ed.setNM_Razao_Social (NM_Razao_Social);
		    }

		    String NM_Grupo_Economico = request.getParameter ("FT_NM_Grupo_Economico");
		    if (String.valueOf (NM_Grupo_Economico) != null && !String.valueOf (NM_Grupo_Economico).equals ("") && !String.valueOf (NM_Grupo_Economico).equals ("null")) {
		      ed.setNM_Razao_Social (NM_Grupo_Economico);
		    }

		    String NM_Mes_Inicial = request.getParameter ("FT_NM_Mes_Inicial");
		    if (String.valueOf (NM_Mes_Inicial) != null && !String.valueOf (NM_Mes_Inicial).equals ("") && !String.valueOf (NM_Mes_Inicial).equals ("null")) {
		      ed.setNM_Mes_Inicial (NM_Mes_Inicial);
		    }
		    String NM_Mes_Final = request.getParameter ("FT_NM_Mes_Final");
		    if (String.valueOf (NM_Mes_Final) != null && !String.valueOf (NM_Mes_Final).equals ("") && !String.valueOf (NM_Mes_Final).equals ("null")) {
		      ed.setNM_Mes_Final (NM_Mes_Final);
		    }
		    String NM_Ano_Inicial = request.getParameter ("FT_NM_Ano_Inicial");
		    if (String.valueOf (NM_Ano_Inicial) != null && !String.valueOf (NM_Ano_Inicial).equals ("") && !String.valueOf (NM_Ano_Inicial).equals ("null")) {
		      ed.setNM_Ano_Inicial (NM_Ano_Inicial);
		    }
		    String NM_Ano_Final = request.getParameter ("FT_NM_Ano_Final");
		    if (String.valueOf (NM_Ano_Final) != null && !String.valueOf (NM_Ano_Final).equals ("") && !String.valueOf (NM_Ano_Final).equals ("null")) {
		      ed.setNM_Ano_Final (NM_Ano_Final);
		    }

		    String oid_Unidade = request.getParameter ("oid_Unidade");
		    if (String.valueOf (oid_Unidade) != null && !String.valueOf (oid_Unidade).equals ("")) {
		      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
		      ed.setNM_Unidade (request.getParameter ("FT_NM_Fantasia"));
		    }

		    String oid_Modal = request.getParameter ("oid_Modal");
		    if (String.valueOf (oid_Modal) != null && !String.valueOf (oid_Modal).equals ("")) {
		      ed.setOID_Modal (new Long (oid_Modal).longValue ());
		    }

		    String oid_Vendedor = request.getParameter ("oid_Vendedor");
		    if (String.valueOf (oid_Vendedor) != null && !String.valueOf (oid_Vendedor).equals ("") && !String.valueOf (oid_Vendedor).equals ("null")) {
		      ed.setOID_Vendedor (oid_Vendedor);
		      ed.setNM_Vendedor (request.getParameter ("FT_NM_Fantasia_Vendedor"));
		    }


		    String oid_Empresa = request.getParameter ("oid_Empresa");

		    // System.out.println ("oid_Empresa" + oid_Empresa);

		    if (String.valueOf (oid_Empresa) != null &&
		        !String.valueOf (oid_Empresa).equals ("") &&
		        !String.valueOf (oid_Empresa).equals ("null")) {
		      ed.setOID_Empresa (new Long (request.getParameter ("oid_Empresa")).longValue ());
		      ed.setNM_Empresa (request.getParameter ("FT_NM_Empresa"));
		    }
		    // System.out.println (ed.getNM_Mes_Inicial ());
		    // System.out.println (ed.getNM_Ano_Inicial ());
		    // System.out.println (ed.getNM_Mes_Final ());
		    // System.out.println (ed.getNM_Ano_Final ());

		    int mes1 = new Integer (ed.getNM_Mes_Inicial ()).intValue ();
		    int mes2 = new Integer (ed.getNM_Mes_Final ()).intValue ();
		    int ano1 = new Integer (ed.getNM_Ano_Inicial ()).intValue ();
		    int ano2 = new Integer (ed.getNM_Ano_Final ()).intValue ();

		    ed.setDT_Emissao_Inicial ("01/" + ed.getNM_Mes_Inicial () + "/" + String.valueOf (ano1));
		    ed.setDT_Emissao_Final (Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_Final () + "/" + String.valueOf (ano2)));

		    // System.out.println (ed.getDT_Emissao_Inicial ());
		    // System.out.println (ed.getDT_Emissao_Final ());

		    ed.setDM_Relatorio (request.getParameter ("FT_DM_Relatorio"));

		    GerencialRN geRN = new GerencialRN ();
		    byte[] b = geRN.geraAnalise_Gerencial_Tabelas_Fretes (ed);

		    return b;

  }
  
  public byte[] geraAnalise_Gerencial_Fornecedores (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {

//	// System.out.println("a 0");
	    GerencialED ed = new GerencialED ();
//	// System.out.println("a");

	    // System.out.println ("geraAnalise_Gerencial_Fornecedores");

	    String oid_Pessoa = request.getParameter ("oid_Pessoa");
	    if (String.valueOf (oid_Pessoa) != null && !String.valueOf (oid_Pessoa).equals ("") && !String.valueOf (oid_Pessoa).equals ("null")) {
	      ed.setOID_Pessoa (oid_Pessoa);
	    }

	    String NM_Razao_Social = request.getParameter ("FT_NM_Razao_Social");
	    if (String.valueOf (NM_Razao_Social) != null && !String.valueOf (NM_Razao_Social).equals ("") && !String.valueOf (NM_Razao_Social).equals ("null")) {
	      ed.setNM_Razao_Social (NM_Razao_Social);
	    }
	    
	    String NM_Grupo_Economico = request.getParameter ("FT_NM_Grupo_Economico");
	    if (String.valueOf (NM_Grupo_Economico) != null && !String.valueOf (NM_Grupo_Economico).equals ("") && !String.valueOf (NM_Grupo_Economico).equals ("null")) {
	      ed.setNM_Razao_Social (NM_Grupo_Economico);
	    }

	    String NM_Mes_Inicial = request.getParameter ("FT_NM_Mes_Inicial");
	    if (String.valueOf (NM_Mes_Inicial) != null && !String.valueOf (NM_Mes_Inicial).equals ("") && !String.valueOf (NM_Mes_Inicial).equals ("null")) {
	      ed.setNM_Mes_Inicial (NM_Mes_Inicial);
	    }
	    String NM_Mes_Final = request.getParameter ("FT_NM_Mes_Final");
	    if (String.valueOf (NM_Mes_Final) != null && !String.valueOf (NM_Mes_Final).equals ("") && !String.valueOf (NM_Mes_Final).equals ("null")) {
	      ed.setNM_Mes_Final (NM_Mes_Final);
	    }
	    String NM_Ano_Inicial = request.getParameter ("FT_NM_Ano_Inicial");
	    if (String.valueOf (NM_Ano_Inicial) != null && !String.valueOf (NM_Ano_Inicial).equals ("") && !String.valueOf (NM_Ano_Inicial).equals ("null")) {
	      ed.setNM_Ano_Inicial (NM_Ano_Inicial);
	    }
	    String NM_Ano_Final = request.getParameter ("FT_NM_Ano_Final");
	    if (String.valueOf (NM_Ano_Final) != null && !String.valueOf (NM_Ano_Final).equals ("") && !String.valueOf (NM_Ano_Final).equals ("null")) {
	      ed.setNM_Ano_Final (NM_Ano_Final);
	    }

	    String oid_Unidade = request.getParameter ("oid_Unidade");
	    if (String.valueOf (oid_Unidade) != null && !String.valueOf (oid_Unidade).equals ("")) {
	      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
	      ed.setNM_Unidade (request.getParameter ("FT_NM_Fantasia"));
	    }

	    String oid_Modal = request.getParameter ("oid_Modal");
	    if (String.valueOf (oid_Modal) != null && !String.valueOf (oid_Modal).equals ("")) {
	      ed.setOID_Modal (new Long (oid_Modal).longValue ());
	    }

	    String oid_Vendedor = request.getParameter ("oid_Vendedor");
	    if (String.valueOf (oid_Vendedor) != null && !String.valueOf (oid_Vendedor).equals ("") && !String.valueOf (oid_Vendedor).equals ("null")) {
	      ed.setOID_Vendedor (oid_Vendedor);
	      ed.setNM_Vendedor (request.getParameter ("FT_NM_Fantasia_Vendedor"));
	    }

	    String oid_Produto = request.getParameter ("oid_Produto");
	    if (String.valueOf (oid_Produto) != null && !String.valueOf (oid_Produto).equals ("")) {
	      ed.setOID_Produto (new Long (oid_Produto).longValue ());
	    }

	    String oid_Grupo_Economico = request.getParameter ("oid_Grupo_Economico");
	    if (String.valueOf (oid_Grupo_Economico) != null && !String.valueOf (oid_Grupo_Economico).equals ("")) {
	      ed.setOID_Grupo_Economico (new Long (oid_Grupo_Economico).longValue ());
	    }

	    String oid_Cidade_Origem = request.getParameter ("oid_Cidade_Origem");
	    if (String.valueOf (oid_Cidade_Origem) != null && !String.valueOf (oid_Cidade_Origem).equals ("")) {
	      ed.setOID_Cidade_Origem (new Long (oid_Cidade_Origem).longValue ());
	    }

	    String oid_Cidade_Destino = request.getParameter ("oid_Cidade_Destino");
	    if (String.valueOf (oid_Cidade_Destino) != null && !String.valueOf (oid_Cidade_Destino).equals ("")) {
	      ed.setOID_Cidade_Destino (new Long (oid_Cidade_Destino).longValue ());
	    }

	    String oid_Empresa = request.getParameter ("oid_Empresa");

	    // System.out.println ("oid_Empresa" + oid_Empresa);

	    if (String.valueOf (oid_Empresa) != null &&
	        !String.valueOf (oid_Empresa).equals ("") &&
	        !String.valueOf (oid_Empresa).equals ("null")) {
	      ed.setOID_Empresa (new Long (request.getParameter ("oid_Empresa")).longValue ());
	      ed.setNM_Empresa (request.getParameter ("FT_NM_Empresa"));
	    }
	    // System.out.println (ed.getNM_Mes_Inicial ());
	    // System.out.println (ed.getNM_Ano_Inicial ());
	    // System.out.println (ed.getNM_Mes_Final ());
	    // System.out.println (ed.getNM_Ano_Final ());

	    int mes1 = new Integer (ed.getNM_Mes_Inicial ()).intValue ();
	    int mes2 = new Integer (ed.getNM_Mes_Final ()).intValue ();
	    int ano1 = new Integer (ed.getNM_Ano_Inicial ()).intValue ();
	    int ano2 = new Integer (ed.getNM_Ano_Final ()).intValue ();

	    ed.setDT_Emissao_Inicial ("01/" + ed.getNM_Mes_Inicial () + "/" + String.valueOf (ano1));
	    ed.setDT_Emissao_Final (Data.getDataMaximaNoMes ("31" + "/" + ed.getNM_Mes_Final () + "/" + String.valueOf (ano2)));

	    // System.out.println (ed.getDT_Emissao_Inicial ());
	    // System.out.println (ed.getDT_Emissao_Final ());

	    ed.setDM_Relatorio (request.getParameter ("FT_DM_Relatorio"));

	    GerencialRN geRN = new GerencialRN ();
	    byte[] b = geRN.geraAnalise_Gerencial_Fornecedores (ed);

	    return b;

	  }
  
  public byte[] geraAnalise_Gerencial_Financeira (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {

//// System.out.println("a 0");
    GerencialED ed = new GerencialED ();

    //// System.out.println("a");

    String DT_1 = request.getParameter ("FT_DT_1");
    if (String.valueOf (DT_1) != null && !String.valueOf (DT_1).equals ("")) {
      ed.setDT_1 (DT_1);
    }
    String DT_2 = request.getParameter ("FT_DT_2");
    if (String.valueOf (DT_2) != null && !String.valueOf (DT_2).equals ("")) {
      ed.setDT_2 (DT_2);
    }
    String DT_3 = request.getParameter ("FT_DT_3");
    if (String.valueOf (DT_3) != null && !String.valueOf (DT_3).equals ("")) {
      ed.setDT_3 (DT_3);
    }
    String DT_4 = request.getParameter ("FT_DT_4");
    if (String.valueOf (DT_4) != null && !String.valueOf (DT_4).equals ("")) {
      ed.setDT_4 (DT_4);
    }
    String DT_5 = request.getParameter ("FT_DT_5");
    if (String.valueOf (DT_5) != null && !String.valueOf (DT_5).equals ("")) {
      ed.setDT_5 (DT_5);
    }

//// System.out.println("e");

    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (String.valueOf (oid_Unidade) != null && !String.valueOf (oid_Unidade).equals ("")) {
      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
    }

    String oid_Empresa = request.getParameter ("oid_Empresa");

    //// System.out.println("oid_Empresa" + oid_Empresa );

//      if (String.valueOf(oid_Empresa) != null &&
//          !String.valueOf(oid_Empresa).equals("") &&
//          !String.valueOf(oid_Empresa).equals("null")){
//         ed.setOID_Empresa(new Long(request.getParameter("oid_Empresa")).longValue());
//    }
//
//         ed.setNM_Empresa(request.getParameter("FT_NM_Empresa"));
//              //// System.out.println("FT_NM_Empresa" +  ed.getNM_Empresa() );


    ed.setDM_Vencer (request.getParameter ("FT_DM_Vencer"));
    ed.setDM_Vencido (request.getParameter ("FT_DM_Vencido"));
    ed.setDM_Contas_Pagar (request.getParameter ("FT_DM_Contas_Pagar"));
    ed.setDM_Banco (request.getParameter ("FT_DM_Banco"));
    ed.setDM_Cobranca (request.getParameter ("FT_DM_Cobranca"));

    ed.setDM_Relatorio (request.getParameter ("FT_DM_Relatorio"));

    GerencialRN geRN = new GerencialRN ();

    return geRN.geraAnalise_Gerencial_Financeira (ed);

  }

  public byte[] geraAnalise_Gerencial_Receita_Despesa (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {

//// System.out.println("a 0");
    GerencialED ed = new GerencialED ();

    //// System.out.println("a");

    String NM_Mes_1 = request.getParameter ("FT_NM_Mes_1");
    if (String.valueOf (NM_Mes_1) != null && !String.valueOf (NM_Mes_1).equals ("") && !String.valueOf (NM_Mes_1).equals ("null")) {
      ed.setNM_Mes_1 (NM_Mes_1);
    }
    String NM_Mes_2 = request.getParameter ("FT_NM_Mes_2");
    if (String.valueOf (NM_Mes_2) != null && !String.valueOf (NM_Mes_2).equals ("") && !String.valueOf (NM_Mes_2).equals ("null")) {
      ed.setNM_Mes_2 (NM_Mes_2);
    }
    String NM_Mes_3 = request.getParameter ("FT_NM_Mes_3");
    if (String.valueOf (NM_Mes_3) != null && !String.valueOf (NM_Mes_3).equals ("") && !String.valueOf (NM_Mes_3).equals ("null")) {
      ed.setNM_Mes_3 (NM_Mes_3);
    }

    String NM_Ano_1 = request.getParameter ("FT_NM_Ano_1");
    if (String.valueOf (NM_Ano_1) != null && !String.valueOf (NM_Ano_1).equals ("") && !String.valueOf (NM_Ano_1).equals ("null")) {
      ed.setNM_Ano_1 (NM_Ano_1);
    }
    String NM_Ano_2 = request.getParameter ("FT_NM_Ano_2");
    if (String.valueOf (NM_Ano_2) != null && !String.valueOf (NM_Ano_2).equals ("") && !String.valueOf (NM_Ano_2).equals ("null")) {
      ed.setNM_Ano_2 (NM_Ano_2);
    }
    String NM_Ano_3 = request.getParameter ("FT_NM_Ano_3");
    if (String.valueOf (NM_Ano_3) != null && !String.valueOf (NM_Ano_3).equals ("") && !String.valueOf (NM_Ano_3).equals ("null")) {
      ed.setNM_Ano_3 (NM_Ano_3);
    }

    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (String.valueOf (oid_Unidade) != null && !String.valueOf (oid_Unidade).equals ("")) {
      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
    }

    String oid_Grupo_Conta = request.getParameter ("FT_OID_Grupo_Conta");
    if (String.valueOf (oid_Grupo_Conta) != null && !String.valueOf (oid_Grupo_Conta).equals ("")) {
      ed.setOID_Grupo_Conta (new Long (oid_Grupo_Conta).longValue ());
    }

    String oid_Empresa = request.getParameter ("oid_Empresa");

    // System.out.println ("oid_Empresa" + oid_Empresa);
    if (String.valueOf (oid_Empresa) != null &&
        !String.valueOf (oid_Empresa).equals ("") &&
        !String.valueOf (oid_Empresa).equals ("null")) {
      ed.setOID_Empresa (new Long (request.getParameter ("oid_Empresa")).longValue ());
    }
    ed.setNM_Empresa (request.getParameter ("FT_NM_Empresa"));
    // System.out.println ("FT_NM_Empresa" + ed.getNM_Empresa ());

    ed.setDM_Tipo (request.getParameter ("FT_DM_Tipo"));

    ed.setDM_Periodo (request.getParameter ("FT_DM_Periodo"));

    ed.setDM_Criterio_Receita (request.getParameter ("FT_DM_Criterio_Receita"));
    ed.setDM_Criterio_Despesa (request.getParameter ("FT_DM_Criterio_Despesa"));
    
    ed.setDM_Consolida (request.getParameter ("FT_DM_Consolida"));

    ed.setDM_Relatorio (request.getParameter ("FT_DM_Relatorio"));

    GerencialRN geRN = new GerencialRN ();

    return geRN.geraAnalise_Gerencial_Receita_Despesa (ed);

  }

  public byte[] geraAnalise_Gerencial_Demonstrativo_Resultado (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {

//// System.out.println("a 0");
    GerencialED ed = new GerencialED ();

    //// System.out.println("a");

    String NM_Mes_1 = request.getParameter ("FT_NM_Mes_1");
    if (String.valueOf (NM_Mes_1) != null && !String.valueOf (NM_Mes_1).equals ("") && !String.valueOf (NM_Mes_1).equals ("null")) {
      ed.setNM_Mes_1 (NM_Mes_1);
    }
    String NM_Mes_2 = request.getParameter ("FT_NM_Mes_2");
    if (String.valueOf (NM_Mes_2) != null && !String.valueOf (NM_Mes_2).equals ("") && !String.valueOf (NM_Mes_2).equals ("null")) {
      ed.setNM_Mes_2 (NM_Mes_2);
    }
    String NM_Mes_3 = request.getParameter ("FT_NM_Mes_3");
    if (String.valueOf (NM_Mes_3) != null && !String.valueOf (NM_Mes_3).equals ("") && !String.valueOf (NM_Mes_3).equals ("null")) {
      ed.setNM_Mes_3 (NM_Mes_3);
    }

    String NM_Ano_1 = request.getParameter ("FT_NM_Ano_1");
    if (String.valueOf (NM_Ano_1) != null && !String.valueOf (NM_Ano_1).equals ("") && !String.valueOf (NM_Ano_1).equals ("null")) {
      ed.setNM_Ano_1 (NM_Ano_1);
    }
    String NM_Ano_2 = request.getParameter ("FT_NM_Ano_2");
    if (String.valueOf (NM_Ano_2) != null && !String.valueOf (NM_Ano_2).equals ("") && !String.valueOf (NM_Ano_2).equals ("null")) {
      ed.setNM_Ano_2 (NM_Ano_2);
    }
    String NM_Ano_3 = request.getParameter ("FT_NM_Ano_3");
    if (String.valueOf (NM_Ano_3) != null && !String.valueOf (NM_Ano_3).equals ("") && !String.valueOf (NM_Ano_3).equals ("null")) {
      ed.setNM_Ano_3 (NM_Ano_3);
    }

    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (String.valueOf (oid_Unidade) != null && !String.valueOf (oid_Unidade).equals ("")) {
      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
    }

    String oid_Grupo_Conta = request.getParameter ("FT_OID_Grupo_Conta");
    if (String.valueOf (oid_Grupo_Conta) != null && !String.valueOf (oid_Grupo_Conta).equals ("")) {
      ed.setOID_Grupo_Conta (new Long (oid_Grupo_Conta).longValue ());
    }

    String oid_Empresa = request.getParameter ("oid_Empresa");

    // System.out.println ("oid_Empresa" + oid_Empresa);
    if (String.valueOf (oid_Empresa) != null &&
        !String.valueOf (oid_Empresa).equals ("") &&
        !String.valueOf (oid_Empresa).equals ("null")) {
      ed.setOID_Empresa (new Long (request.getParameter ("oid_Empresa")).longValue ());
    }
    ed.setNM_Empresa (request.getParameter ("FT_NM_Empresa"));
    // System.out.println ("FT_NM_Empresa" + ed.getNM_Empresa ());

    ed.setDM_Tipo (request.getParameter ("FT_DM_Tipo"));

    ed.setDM_Consolida (request.getParameter ("FT_DM_Consolida"));

    ed.setDM_Relatorio (request.getParameter ("FT_DM_Relatorio"));

    GerencialRN geRN = new GerencialRN ();

    return geRN.geraAnalise_Gerencial_Demonstrativo_Resultado (ed);

  }

  public byte[] geraAnalise_Gerencial_Veiculos (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {

//// System.out.println("a 0");
    GerencialED ed = new GerencialED ();

    //// System.out.println("a");

    String NM_Mes_Inicial = request.getParameter ("FT_NM_Mes_Inicial");
    if (String.valueOf (NM_Mes_Inicial) != null && !String.valueOf (NM_Mes_Inicial).equals ("") && !String.valueOf (NM_Mes_Inicial).equals ("null")) {
      ed.setNM_Mes_Inicial (NM_Mes_Inicial);
    }
    String NM_Mes_Final = request.getParameter ("FT_NM_Mes_Final");
    if (String.valueOf (NM_Mes_Final) != null && !String.valueOf (NM_Mes_Final).equals ("") && !String.valueOf (NM_Mes_Final).equals ("null")) {
      ed.setNM_Mes_Final (NM_Mes_Final);
    }
    String NM_Ano_Inicial = request.getParameter ("FT_NM_Ano_Inicial");
    if (String.valueOf (NM_Ano_Inicial) != null && !String.valueOf (NM_Ano_Inicial).equals ("") && !String.valueOf (NM_Ano_Inicial).equals ("null")) {
      ed.setNM_Ano_Inicial (NM_Ano_Inicial);
    }
    String NM_Ano_Final = request.getParameter ("FT_NM_Ano_Final");
    if (String.valueOf (NM_Ano_Final) != null && !String.valueOf (NM_Ano_Final).equals ("") && !String.valueOf (NM_Ano_Final).equals ("null")) {
      ed.setNM_Ano_Final (NM_Ano_Final);
    }

    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (String.valueOf (oid_Unidade) != null && !String.valueOf (oid_Unidade).equals ("")) {
      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
    }

    String oid_Modelo_Veiculo = request.getParameter ("oid_Modelo_Veiculo");
    if (String.valueOf (oid_Modelo_Veiculo) != null && !String.valueOf (oid_Modelo_Veiculo).equals ("")) {
      ed.setOid_Modelo_Veiculo (new Long (oid_Modelo_Veiculo).longValue ());
    }
    String oid_Marca_Veiculo = request.getParameter ("oid_Marca_Veiculo");
    if (String.valueOf (oid_Marca_Veiculo) != null && !String.valueOf (oid_Marca_Veiculo).equals ("")) {
      ed.setOid_Marca_Veiculo (new Long (oid_Marca_Veiculo).longValue ());
    }

    String oid_Empresa = request.getParameter ("oid_Empresa");

    // System.out.println ("oid_Empresa" + oid_Empresa);
    if (String.valueOf (oid_Empresa) != null &&
        !String.valueOf (oid_Empresa).equals ("") &&
        !String.valueOf (oid_Empresa).equals ("null")) {
      ed.setOID_Empresa (new Long (request.getParameter ("oid_Empresa")).longValue ());
    }
    ed.setNM_Empresa (request.getParameter ("FT_NM_Empresa"));
    // System.out.println ("FT_NM_Empresa" + ed.getNM_Empresa ());

    ed.setDM_Tipo (request.getParameter ("FT_DM_Tipo"));

    ed.setDM_Relatorio (request.getParameter ("FT_DM_Relatorio"));

    GerencialRN geRN = new GerencialRN ();

    return geRN.geraAnalise_Gerencial_Veiculos (ed);

  }

  public byte[] geraAnalise_Gerencial_Prazo_Medio (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {

//// System.out.println("a 0");
    GerencialED ed = new GerencialED ();

    //// System.out.println("a");

    String DT_1 = request.getParameter ("FT_DT_1");
    if (String.valueOf (DT_1) != null && !String.valueOf (DT_1).equals ("")) {
      ed.setDT_1 (DT_1);
    }
    String DT_2 = request.getParameter ("FT_DT_2");
    if (String.valueOf (DT_2) != null && !String.valueOf (DT_2).equals ("")) {
      ed.setDT_2 (DT_2);
    }

    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (String.valueOf (oid_Unidade) != null && !String.valueOf (oid_Unidade).equals ("")) {
      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
    }

    String oid_Empresa = request.getParameter ("oid_Empresa");

    // System.out.println ("oid_Empresa" + oid_Empresa);
    if (String.valueOf (oid_Empresa) != null &&
        !String.valueOf (oid_Empresa).equals ("") &&
        !String.valueOf (oid_Empresa).equals ("null")) {
      ed.setOID_Empresa (new Long (request.getParameter ("oid_Empresa")).longValue ());
    }
    ed.setNM_Empresa (request.getParameter ("FT_NM_Empresa"));
    // System.out.println ("FT_NM_Empresa" + ed.getNM_Empresa ());

    ed.setDM_Tipo (request.getParameter ("FT_DM_Tipo"));

    ed.setDM_Relatorio (request.getParameter ("FT_DM_Relatorio"));

    GerencialRN geRN = new GerencialRN ();

    return geRN.geraAnalise_Gerencial_Prazo_Medio (ed);

  }

  public byte[] geraAnalise_Gerencial_Contas_Receber (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {

    GerencialED ed = new GerencialED ();

    String oid_Pessoa = request.getParameter ("oid_Pessoa");
    if (String.valueOf (oid_Pessoa) != null && !String.valueOf (oid_Pessoa).equals ("") && !String.valueOf (oid_Pessoa).equals ("null")) {
      ed.setOID_Pessoa (oid_Pessoa);
    }

    String oid_Empresa = request.getParameter ("oid_Empresa");
    if (String.valueOf (oid_Empresa) != null && !String.valueOf (oid_Empresa).equals ("") && !String.valueOf (oid_Empresa).equals ("null")) {
      ed.setOID_Empresa (Long.valueOf (oid_Empresa).longValue ());
    }

    String oid_Carteira = request.getParameter ("oid_Carteira");
    if (String.valueOf (oid_Carteira) != null && !String.valueOf (oid_Carteira).equals ("") && !String.valueOf (oid_Carteira).equals ("null")) {
      ed.setOID_Carteira (Long.valueOf (oid_Carteira).longValue ());
    }

    String oid_Vendedor = request.getParameter ("oid_Vendedor");
    if (String.valueOf (oid_Vendedor) != null && !String.valueOf (oid_Vendedor).equals ("") && !String.valueOf (oid_Vendedor).equals ("null")) {
      ed.setOID_Vendedor (oid_Vendedor);
      ed.setNM_Vendedor (request.getParameter ("FT_NM_Fantasia_Vendedor"));
    }

    String DT_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
    if (String.valueOf (DT_Emissao_Inicial) != null && !String.valueOf (DT_Emissao_Inicial).equals ("")) {
      ed.setDT_Emissao_Inicial (DT_Emissao_Inicial);
    }

    String DT_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");
    if (String.valueOf (DT_Emissao_Final) != null && !String.valueOf (DT_Emissao_Final).equals ("")) {
      ed.setDT_Emissao_Final (DT_Emissao_Final);
    }

    String DT_Vencimento_Inicial = request.getParameter ("FT_DT_Vencimento_Inicial");
    if (String.valueOf (DT_Vencimento_Inicial) != null && !String.valueOf (DT_Vencimento_Inicial).equals ("")) {
      ed.setDT_Vencimento_Inicial (DT_Vencimento_Inicial);
    }

    String DT_Vencimento_Final = request.getParameter ("FT_DT_Vencimento_Final");
    if (String.valueOf (DT_Vencimento_Final) != null && !String.valueOf (DT_Vencimento_Final).equals ("")) {
      ed.setDT_Vencimento_Final (DT_Vencimento_Final);
    }

    if (!request.getParameter ("FT_VL_Titulo_Inicial").equals ("")) {
      String x = request.getParameter ("FT_VL_Titulo_Inicial");
      x = x.replace (',' , '.');
      double VL_Titulo_Inicial = Double.valueOf (x).doubleValue ();
      if (String.valueOf (VL_Titulo_Inicial) != null && !String.valueOf (VL_Titulo_Inicial).equals ("")) {
        ed.setVL_Titulo_Inicial (VL_Titulo_Inicial);
      }
    }

    if (!request.getParameter ("FT_VL_Titulo_Final").equals ("")) {
      String y = request.getParameter ("FT_VL_Titulo_Final");
      y = y.replace (',' , '.');
      double VL_Titulo_Final = Double.valueOf (y).doubleValue ();
      if (String.valueOf (VL_Titulo_Final) != null && !String.valueOf (VL_Titulo_Final).equals ("")) {
        ed.setVL_Titulo_Final (VL_Titulo_Final);
      }
    }

    String DM_Classificar = request.getParameter ("FT_DM_Classificar");
    if (String.valueOf (DM_Classificar) != null && !String.valueOf (DM_Classificar).equals ("")) {
      ed.setDM_Classificar (DM_Classificar);
    }

    String NM_Banco = request.getParameter ("FT_NM_Banco");
    if (String.valueOf (NM_Banco) != null && !String.valueOf (NM_Banco).equals ("")) {
      ed.setNM_Banco (NM_Banco);
    }

    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (String.valueOf (oid_Unidade) != null && !String.valueOf (oid_Unidade).equals ("")) {
      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
    }

    String oid_Tipo_Documento = request.getParameter ("oid_Tipo_Documento");
    if (String.valueOf (oid_Tipo_Documento) != null && !String.valueOf (oid_Tipo_Documento).equals ("")) {
      ed.setOID_Tipo_Documento (oid_Tipo_Documento);
    }

    ed.setDM_Relatorio (request.getParameter ("FT_DM_Relatorio"));
    ed.setDM_Situacao (request.getParameter ("FT_DM_Situacao"));

    GerencialRN geRN = new GerencialRN ();

    return geRN.geraAnalise_Gerencial_Contas_Receber (ed);

  }

  public byte[] geraAnalise_Gerencial_CRT (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {

//    // System.out.println("a 0");
    GerencialED ed = new GerencialED ();
    // System.out.println ("a ### INTER");

    String oid_Pessoa = request.getParameter ("oid_Pessoa");
    if (String.valueOf (oid_Pessoa) != null && !String.valueOf (oid_Pessoa).equals ("") && !String.valueOf (oid_Pessoa).equals ("null")) {
      ed.setOID_Pessoa (oid_Pessoa);
    }
    String oid_Pessoa_Remetente = request.getParameter ("oid_Pessoa_Remetente");
    if (String.valueOf (oid_Pessoa_Remetente) != null && !String.valueOf (oid_Pessoa_Remetente).equals ("") && !String.valueOf (oid_Pessoa_Remetente).equals ("null")) {
      ed.setOID_Pessoa_Remetente (oid_Pessoa_Remetente);
    }
    String oid_Pessoa_Destinatario = request.getParameter ("oid_Pessoa_Destinatario");
    if (String.valueOf (oid_Pessoa_Destinatario) != null && !String.valueOf (oid_Pessoa_Destinatario).equals ("") && !String.valueOf (oid_Pessoa_Destinatario).equals ("null")) {
      ed.setOID_Pessoa_Destinatario (oid_Pessoa_Destinatario);
    }
    String oid_Pessoa_Pagador = request.getParameter ("oid_Pessoa_Pagador");
    if (String.valueOf (oid_Pessoa_Pagador) != null && !String.valueOf (oid_Pessoa_Pagador).equals ("") && !String.valueOf (oid_Pessoa_Pagador).equals ("null")) {
      ed.setOID_Pessoa_Pagador (oid_Pessoa_Pagador);
    }

    String oid_Vendedor = request.getParameter ("oid_Vendedor");
    if (String.valueOf (oid_Vendedor) != null && !String.valueOf (oid_Vendedor).equals ("") && !String.valueOf (oid_Vendedor).equals ("null")) {
      ed.setOID_Vendedor (oid_Vendedor);
      ed.setNM_Vendedor (request.getParameter ("FT_NM_Fantasia_Vendedor"));
    }

    //// System.out.println("vendedor iu " + ed.getOID_Vendedor());

    String DT_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");
    if (String.valueOf (DT_Emissao_Inicial) != null && !String.valueOf (DT_Emissao_Inicial).equals ("")) {
      ed.setDT_Emissao_Inicial (DT_Emissao_Inicial);
    }

    String DT_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");
    if (String.valueOf (DT_Emissao_Final) != null && !String.valueOf (DT_Emissao_Final).equals ("")) {
      ed.setDT_Emissao_Final (DT_Emissao_Final);
    }
//    // System.out.println("c");

    String DM_Frete = request.getParameter ("FT_DM_Frete");
    if (String.valueOf (DM_Frete) != null && !String.valueOf (DM_Frete).equals ("")) {
      ed.setDM_Frete (DM_Frete);
    }

    String DM_Pessoa = request.getParameter ("FT_DM_Pessoa");
    if (String.valueOf (DM_Pessoa) != null && !String.valueOf (DM_Pessoa).equals ("")) {
      ed.setDM_Pessoa (DM_Pessoa);
    }

    String DM_Classificar = request.getParameter ("FT_DM_Classificar");
    if (String.valueOf (DM_Classificar) != null && !String.valueOf (DM_Classificar).equals ("")) {
      ed.setDM_Classificar (DM_Classificar);
    }

    String NR_Amostra = request.getParameter ("FT_NR_Amostra");
    if (String.valueOf (NR_Amostra) != null && !String.valueOf (NR_Amostra).equals ("")) {
      ed.setNR_Amostra (NR_Amostra);
    }

    String DM_Tipo_Pagamento = request.getParameter ("FT_DM_Tipo_Pagamento");
    if (String.valueOf (DM_Tipo_Pagamento) != null && !String.valueOf (DM_Tipo_Pagamento).equals ("")) {
      ed.setDM_Tipo_Pagamento (DM_Tipo_Pagamento);
    }
//    // System.out.println("e");

    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (String.valueOf (oid_Unidade) != null && !String.valueOf (oid_Unidade).equals ("")) {
      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
    }

    String oid_Modal = request.getParameter ("oid_Modal");
    if (String.valueOf (oid_Modal) != null && !String.valueOf (oid_Modal).equals ("")) {
      ed.setOID_Modal (new Long (oid_Modal).longValue ());
    }

    String oid_Grupo_Economico = request.getParameter ("oid_Grupo_Economico");
    if (String.valueOf (oid_Grupo_Economico) != null && !String.valueOf (oid_Grupo_Economico).equals ("")) {
      ed.setOID_Grupo_Economico (new Long (oid_Grupo_Economico).longValue ());
    }

    String oid_Produto = request.getParameter ("oid_Produto");
    if (String.valueOf (oid_Produto) != null && !String.valueOf (oid_Produto).equals ("")) {
      ed.setOID_Produto (new Long (oid_Produto).longValue ());
    }

    String oid_Cidade_Origem = request.getParameter ("oid_Cidade_Origem");
    if (String.valueOf (oid_Cidade_Origem) != null && !String.valueOf (oid_Cidade_Origem).equals ("")) {
      ed.setOID_Cidade_Origem (new Long (oid_Cidade_Origem).longValue ());
    }

    String oid_Cidade_Destino = request.getParameter ("oid_Cidade_Destino");
    if (String.valueOf (oid_Cidade_Destino) != null && !String.valueOf (oid_Cidade_Destino).equals ("")) {
      ed.setOID_Cidade_Destino (new Long (oid_Cidade_Destino).longValue ());
    }

    String oid_Empresa = request.getParameter ("oid_Empresa");

    //// System.out.println("oid_Empresa" + oid_Empresa );

//          if (String.valueOf(oid_Empresa) != null &&
//              !String.valueOf(oid_Empresa).equals("") &&
//              !String.valueOf(oid_Empresa).equals("null")){
//             ed.setOID_Empresa(new Long(request.getParameter("oid_Empresa")).longValue());
//        }
    //
//             ed.setNM_Empresa(request.getParameter("FT_NM_Empresa"));
//                  //// System.out.println("FT_NM_Empresa" +  ed.getNM_Empresa() );



    ed.setDM_Lista_Conhecimento (request.getParameter ("FT_DM_Lista_Conhecimento"));
    ed.setDM_Relatorio (request.getParameter ("FT_DM_Relatorio"));

    GerencialRN geRN = new GerencialRN ();

    return geRN.geraAnalise_Gerencial_CRT (ed);

  }

  public byte[] geraAnalise_Gerencial_Faturamento (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {

//// System.out.println("a 0");
    GerencialED ed = new GerencialED ();

    //// System.out.println("a");

    String NM_Mes_1 = request.getParameter ("FT_NM_Mes_1");
    if (String.valueOf (NM_Mes_1) != null && !String.valueOf (NM_Mes_1).equals ("") && !String.valueOf (NM_Mes_1).equals ("null")) {
      ed.setNM_Mes_1 (NM_Mes_1);
    }
    String NM_Mes_2 = request.getParameter ("FT_NM_Mes_2");
    if (String.valueOf (NM_Mes_2) != null && !String.valueOf (NM_Mes_2).equals ("") && !String.valueOf (NM_Mes_2).equals ("null")) {
      ed.setNM_Mes_2 (NM_Mes_2);
    }
    String NM_Mes_3 = request.getParameter ("FT_NM_Mes_3");
    if (String.valueOf (NM_Mes_3) != null && !String.valueOf (NM_Mes_3).equals ("") && !String.valueOf (NM_Mes_3).equals ("null")) {
      ed.setNM_Mes_3 (NM_Mes_3);
    }

    String NM_Ano_1 = request.getParameter ("FT_NM_Ano_1");
    if (String.valueOf (NM_Ano_1) != null && !String.valueOf (NM_Ano_1).equals ("") && !String.valueOf (NM_Ano_1).equals ("null")) {
      ed.setNM_Ano_1 (NM_Ano_1);
    }
    String NM_Ano_2 = request.getParameter ("FT_NM_Ano_2");
    if (String.valueOf (NM_Ano_2) != null && !String.valueOf (NM_Ano_2).equals ("") && !String.valueOf (NM_Ano_2).equals ("null")) {
      ed.setNM_Ano_2 (NM_Ano_2);
    }
    String NM_Ano_3 = request.getParameter ("FT_NM_Ano_3");
    if (String.valueOf (NM_Ano_3) != null && !String.valueOf (NM_Ano_3).equals ("") && !String.valueOf (NM_Ano_3).equals ("null")) {
      ed.setNM_Ano_3 (NM_Ano_3);
    }

    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (String.valueOf (oid_Unidade) != null && !String.valueOf (oid_Unidade).equals ("")) {
      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
    }

    String oid_Empresa = request.getParameter ("oid_Empresa");

    // System.out.println ("oid_Empresa" + oid_Empresa);
    if (String.valueOf (oid_Empresa) != null &&
        !String.valueOf (oid_Empresa).equals ("") &&
        !String.valueOf (oid_Empresa).equals ("null")) {
      ed.setOID_Empresa (new Long (request.getParameter ("oid_Empresa")).longValue ());
    }
    ed.setNM_Empresa (request.getParameter ("FT_NM_Empresa"));
    // System.out.println ("FT_NM_Empresa" + ed.getNM_Empresa ());

    ed.setDM_Tipo (request.getParameter ("FT_DM_Tipo"));

    ed.setDM_Relatorio (request.getParameter ("FT_DM_Relatorio"));

    GerencialRN geRN = new GerencialRN ();

    return geRN.geraAnalise_Gerencial_Faturamento (ed);

  }

  public byte[] geraAnalise_Gerencial_Faturamento_Diario (HttpServletRequest request , HttpServletResponse Response) throws Excecoes {

//// System.out.println("a 0");
    GerencialED ed = new GerencialED ();

    //// System.out.println("a");

    String NM_Mes_1 = request.getParameter ("FT_NM_Mes_1");
    if (String.valueOf (NM_Mes_1) != null && !String.valueOf (NM_Mes_1).equals ("") && !String.valueOf (NM_Mes_1).equals ("null")) {
      ed.setNM_Mes_1 (NM_Mes_1);
    }
    String NM_Ano_1 = request.getParameter ("FT_NM_Ano_1");
    if (String.valueOf (NM_Ano_1) != null && !String.valueOf (NM_Ano_1).equals ("") && !String.valueOf (NM_Ano_1).equals ("null")) {
      ed.setNM_Ano_1 (NM_Ano_1);
    }
    String oid_Unidade = request.getParameter ("oid_Unidade");
    if (String.valueOf (oid_Unidade) != null && !String.valueOf (oid_Unidade).equals ("")) {
      ed.setOID_Unidade (new Long (oid_Unidade).longValue ());
    }

    String oid_Empresa = request.getParameter ("oid_Empresa");

    // System.out.println ("oid_Empresa" + oid_Empresa);
    if (String.valueOf (oid_Empresa) != null &&
        !String.valueOf (oid_Empresa).equals ("") &&
        !String.valueOf (oid_Empresa).equals ("null")) {
      ed.setOID_Empresa (new Long (request.getParameter ("oid_Empresa")).longValue ());
    }
    ed.setNM_Empresa (request.getParameter ("FT_NM_Empresa"));
    // System.out.println ("FT_NM_Empresa" + ed.getNM_Empresa ());

    ed.setDM_Tipo (request.getParameter ("FT_DM_Tipo"));

    ed.setDM_Periodo (request.getParameter ("FT_DM_Periodo"));

    ed.setDM_Relatorio (request.getParameter ("FT_DM_Relatorio"));

    GerencialRN geRN = new GerencialRN ();

    return geRN.geraAnalise_Gerencial_Faturamento_Diario (ed);

  }

}
