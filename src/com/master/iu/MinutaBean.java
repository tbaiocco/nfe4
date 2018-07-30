package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Nota_FiscalED;
import com.master.rn.MinutaRN;
import com.master.util.Excecoes;
import com.master.util.Mensagens;
import com.master.util.Utilitaria;
import javax.servlet.http.HttpServletResponse;

public class MinutaBean {

    Utilitaria util = new Utilitaria();

  ///peso cubado
  public Nota_FiscalED inclui(HttpServletRequest request)throws Excecoes{
      MinutaRN MinutaRN = new MinutaRN();
      Nota_FiscalED ed = new Nota_FiscalED();

      String NM_Especie = request.getParameter("FT_NM_Especie");

      //request em cima dos campos dos forms html

      String NR_Nota_Fiscal = request.getParameter("FT_NR_Nota_Fiscal");
      if (String.valueOf(NR_Nota_Fiscal) != null && !String.valueOf(NR_Nota_Fiscal).equals("")
        && !String.valueOf(NR_Nota_Fiscal).equals("null")){
        ed.setNR_Nota_Fiscal(new Long(request.getParameter("FT_NR_Nota_Fiscal")).longValue());
      }
      else {
        ed.setNR_Nota_Fiscal(0);
      }

      String OID_Natureza_Operacao = request.getParameter("oid_Natureza_Operacao");
      if (String.valueOf(OID_Natureza_Operacao) != null && !String.valueOf(OID_Natureza_Operacao).equals("")
        && !String.valueOf(OID_Natureza_Operacao).equals("null")){
        ed.setOID_Natureza_Operacao(new Long(request.getParameter("oid_Natureza_Operacao")).longValue());
      }

       //// System.out.println("nf1");

      ed.setNM_Serie(request.getParameter("FT_NM_Serie"));
      ed.setDM_Transferencia(request.getParameter("FT_DM_Transferencia"));
      ed.setDM_Exportacao(request.getParameter("FT_DM_Exportacao"));
      ed.setOID_Pessoa(request.getParameter("oid_Pessoa_Remetente"));
      ed.setOID_Pessoa_Destinatario(request.getParameter("oid_Pessoa_Destinatario"));
      ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));
      ed.setDT_Entrada(request.getParameter("FT_DT_Entrada"));
      ed.setNR_Pedido(request.getParameter("FT_NR_Pedido"));

      ed.setDM_Situacao(request.getParameter("FT_DM_Situacao"));

      String NR_Peso = request.getParameter("FT_NR_Peso");
      if (String.valueOf(NR_Peso) != null && !String.valueOf(NR_Peso).equals("")
        && !String.valueOf(NR_Peso).equals("null")){
        ed.setNR_Peso(new Double(NR_Peso).doubleValue());
      }

      String VL_Total_Frete = request.getParameter("FT_VL_Total_Frete");
      if (String.valueOf(VL_Total_Frete) != null && !String.valueOf(VL_Total_Frete).equals("")
        && !String.valueOf(VL_Total_Frete).equals("null")){
        ed.setVL_Total_Frete(new Double(VL_Total_Frete).doubleValue());
      }

      // System.out.println("FT_DM_Tabela: " + request.getParameter("FT_DM_Tabela"));
      String DM_Tabela = request.getParameter("FT_DM_Tabela");
      if (String.valueOf(DM_Tabela) != null && !String.valueOf(DM_Tabela).equals("")
        && !String.valueOf(DM_Tabela).equals("null")){
        ed.setDM_Tabela(request.getParameter("FT_DM_Tabela"));
      }
      else ed.setDM_Tabela("N");

       // System.out.println("nf2");


      String NR_Cubagem = request.getParameter("FT_NR_Cubagem");
      if (String.valueOf(NR_Cubagem) != null && !String.valueOf(NR_Cubagem).equals("")
        && !String.valueOf(NR_Cubagem).equals("null")){
        ed.setNR_Cubagem(new Double(NR_Cubagem).doubleValue());
      }

      String NR_Cubagem_Total = request.getParameter("FT_NR_Cubagem_Total");
      if (String.valueOf(NR_Cubagem_Total) != null && !String.valueOf(NR_Cubagem_Total).equals("")
        && !String.valueOf(NR_Cubagem_Total).equals("null")){
        ed.setNR_Cubagem_Total(new Double(NR_Cubagem_Total).doubleValue());
      }



      ed.setHR_Entrada("");

      String HR_Entrada = request.getParameter("FT_HR_Entrada");
      if (String.valueOf(HR_Entrada) != null && !String.valueOf(HR_Entrada).equals("")
        && !String.valueOf(HR_Entrada).equals("null")){
        ed.setHR_Entrada(request.getParameter("FT_HR_Entrada"));
      }

      ed.setDM_Tipo_Conhecimento("1");
      String DM_Tipo_Conhecimento = request.getParameter("FT_DM_Tipo_Conhecimento");
      if (String.valueOf(DM_Tipo_Conhecimento) != null && !String.valueOf(DM_Tipo_Conhecimento).equals("")
        && !String.valueOf(DM_Tipo_Conhecimento).equals("null")){
        ed.setDM_Tipo_Conhecimento(request.getParameter("FT_DM_Tipo_Conhecimento"));
      }

      //ed.setNR_Peso_Cubado(ed.getNR_Peso());
      // System.out.println("CUBADO!!! > "+request.getParameter("FT_NR_Peso_Cubado"));
      String NR_Peso_Cubado = request.getParameter("FT_NR_Peso_Cubado");
      if (String.valueOf(NR_Peso_Cubado) != null && !String.valueOf(NR_Peso_Cubado).equals("")
        && !String.valueOf(NR_Peso_Cubado).equals("null")){
        ed.setNR_Peso_Cubado(new Double(NR_Peso_Cubado).doubleValue());
      }


      String NR_Itens = request.getParameter("FT_NR_Itens");
      if (String.valueOf(NR_Itens) != null && !String.valueOf(NR_Itens).equals("")
        && !String.valueOf(NR_Itens).equals("null")){
        ed.setNR_Itens(new Double(NR_Itens).doubleValue());
      }

      ed.setNR_Lote("");
      String NR_Lote = request.getParameter("FT_NR_Lote");
      if (String.valueOf(NR_Lote) != null && !String.valueOf(NR_Lote).equals("")
        && !String.valueOf(NR_Lote).equals("null")){
        ed.setNR_Lote(NR_Lote);
      }

// System.out.println("nf11111");

      String NR_Volumes = request.getParameter("FT_NR_Volumes");
      if (String.valueOf(NR_Volumes) != null && !String.valueOf(NR_Volumes).equals("")
        && !String.valueOf(NR_Volumes).equals("null")){
      ed.setNR_Volumes(new Double(request.getParameter("FT_NR_Volumes")).doubleValue());
      }

      String VL_Nota_Fiscal = request.getParameter("FT_VL_Nota_Fiscal");
      if (String.valueOf(VL_Nota_Fiscal) != null && !String.valueOf(VL_Nota_Fiscal).equals("")
        && !String.valueOf(VL_Nota_Fiscal).equals("null")){
        ed.setVL_Nota_Fiscal(new Double(VL_Nota_Fiscal).doubleValue());
      }
      ///###
// System.out.println("nf2 again");

      String OID_Produto = request.getParameter("oid_Produto");
      if (String.valueOf(OID_Produto) != null && !String.valueOf(OID_Produto).equals("")
        && !String.valueOf(OID_Produto).equals("null")){
        ed.setOID_Produto(new Long(request.getParameter("oid_Produto")).longValue());
      }
       //// System.out.println("nf3");

      String oid_Pessoa_Consignatario = request.getParameter("oid_Pessoa_Consignatario");
      if (String.valueOf(oid_Pessoa_Consignatario) != null && !String.valueOf(oid_Pessoa_Consignatario).equals("")
        && !String.valueOf(oid_Pessoa_Consignatario).equals(null)
        && !String.valueOf(oid_Pessoa_Consignatario).equals("null")){
        ed.setOID_Pessoa_Consignatario(request.getParameter("oid_Pessoa_Consignatario"));
      }
      else ed.setOID_Pessoa_Consignatario("");

       //// System.out.println("nf4");

      String oid_Pessoa_Redespacho = request.getParameter("oid_Pessoa_Redespacho");
      if (String.valueOf(oid_Pessoa_Redespacho) != null && !String.valueOf(oid_Pessoa_Redespacho).equals("")
        && !String.valueOf(oid_Pessoa_Redespacho).equals(null)
        && !String.valueOf(oid_Pessoa_Redespacho).equals("null")){
        ed.setOID_Pessoa_Redespacho(request.getParameter("oid_Pessoa_Redespacho"));
      }
      else ed.setOID_Pessoa_Redespacho("");

       //// System.out.println("nf5");


      String OID_Coleta = request.getParameter("oid_Coleta");
      if (String.valueOf(OID_Coleta) != null && !String.valueOf(OID_Coleta).equals("")
        && !String.valueOf(OID_Coleta).equals("null")){
        ed.setOID_Coleta(new Long(request.getParameter("oid_Coleta")).longValue());
      }

// System.out.println("nf6");

      String OID_Modal = request.getParameter("oid_Modal");
      if (String.valueOf(OID_Modal) != null && !String.valueOf(OID_Modal).equals("")
        && !String.valueOf(OID_Modal).equals("null")){
        ed.setOID_Modal(new Long(request.getParameter("oid_Modal")).longValue());
      }

       // System.out.println("nf7");

      String DM_Tipo_Pagamento = request.getParameter("FT_DM_Tipo_Pagamento");
      if (String.valueOf(DM_Tipo_Pagamento) != null && !String.valueOf(DM_Tipo_Pagamento).equals("")
        && !String.valueOf(DM_Tipo_Pagamento).equals("null")){
        ed.setDM_Tipo_Pagamento(request.getParameter("FT_DM_Tipo_Pagamento"));
      } else ed.setDM_Tipo_Pagamento("");

       // System.out.println("nf8");


      String TX_Observacao = request.getParameter("FT_TX_Observacao");
      if (String.valueOf(TX_Observacao) != null && !String.valueOf(TX_Observacao).equals("")
        && !String.valueOf(TX_Observacao).equals("null")){
        ed.setTX_Observacao(request.getParameter("FT_TX_Observacao"));
      } else ed.setTX_Observacao("");


      // System.out.println("nf9");


      if (util.doValida(NM_Especie)) {
          ed.setNM_Especie(NM_Especie);
      }

      // System.out.println("nf 10");

      return MinutaRN.inclui(ed);
  }

  public void altera(HttpServletRequest request)throws Excecoes{
  	Nota_FiscalED ed = new Nota_FiscalED();
  	String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
  	String oid_Natureza_Operacao = request.getParameter("oid_Natureza_Operacao");
  	String NR_Nota_Fiscal = request.getParameter("FT_NR_Nota_Fiscal");
  	String NM_Serie = request.getParameter("FT_NM_Serie");
  	String DM_Transferencia = request.getParameter("FT_DM_Transferencia");
  	String DM_Exportacao = request.getParameter("FT_DM_Exportacao");
  	String oid_Pessoa_Remetente = request.getParameter("oid_Pessoa_Remetente");
  	String oid_Pessoa_Destinatario = request.getParameter("oid_Pessoa_Destinatario");
  	String oid_Pessoa_Consignatario = request.getParameter("oid_Pessoa_Consignatario");
  	String oid_Pessoa_Redespacho = request.getParameter("oid_Pessoa_Redespacho");
  	String DT_Emissao = request.getParameter("FT_DT_Emissao");
  	String DT_Entrada = request.getParameter("FT_DT_Entrada");
  	String HR_Entrada = request.getParameter("FT_HR_Entrada");
	String NR_Pedido = request.getParameter("FT_NR_Pedido");
	String DM_Situacao = request.getParameter("FT_DM_Situacao");
  	String NR_Peso = request.getParameter("FT_NR_Peso");
  	String NR_Cubagem = request.getParameter("FT_NR_Cubagem");
  	String NR_Cubagem_Total = request.getParameter("FT_NR_Cubagem_Total");
  	String NR_Peso_Cubado = request.getParameter("FT_NR_Peso_Cubado");
  	String NR_Volumes = request.getParameter("FT_NR_Volumes");
  	String VL_Nota_Fiscal = request.getParameter("FT_VL_Nota_Fiscal");
  	String oid_Produto = request.getParameter("oid_Produto");
  	String NR_Lote = request.getParameter("FT_NR_Lote");
  	String oid_Modal = request.getParameter("oid_Modal");
  	String DM_Tipo_Pagamento = request.getParameter("FT_DM_Tipo_Pagamento");
  	String TX_Observacao = request.getParameter("FT_TX_Observacao");
  	String NR_Itens = request.getParameter("FT_NR_Itens");
  	String VL_Total_Frete = request.getParameter("FT_VL_Total_Frete");
  	String VL_Tabela = request.getParameter("FT_VL_Tabela");
  	String NM_Especie = request.getParameter("FT_NM_Especie");

  	if (util.doValida(oid_Nota_Fiscal)) {
  		ed.setOID_Nota_Fiscal(oid_Nota_Fiscal);
  	}
  	if (util.doValida(oid_Natureza_Operacao)) {
  		ed.setOID_Natureza_Operacao(Long.parseLong(oid_Natureza_Operacao));
  	}
  	if (util.doValida(NR_Nota_Fiscal)) {
  		ed.setNR_Nota_Fiscal(Long.parseLong(NR_Nota_Fiscal));
  	}
  	if (util.doValida(NM_Serie)) {
  		ed.setNM_Serie(NM_Serie);
  	}
  	if (util.doValida(DM_Transferencia)) {
  		ed.setDM_Transferencia(DM_Transferencia);
  	}
  	if (util.doValida(DM_Exportacao)) {
  		ed.setDM_Exportacao(DM_Exportacao);
  	}
  	if (util.doValida(oid_Pessoa_Remetente)) {
  		ed.setOID_Pessoa(oid_Pessoa_Remetente);
  	}
  	if (util.doValida(oid_Pessoa_Destinatario)) {
  		ed.setOID_Pessoa_Destinatario(oid_Pessoa_Destinatario);
  	}
  	if (util.doValida(oid_Pessoa_Consignatario)) {
  		ed.setOID_Pessoa_Consignatario(request.getParameter("oid_Pessoa_Consignatario"));
  	}
  	if (util.doValida(oid_Pessoa_Redespacho)) {
  		ed.setOID_Pessoa_Redespacho(request.getParameter("oid_Pessoa_Redespacho"));
  	}
  	if (util.doValida(DT_Emissao)) {
  		ed.setDT_Emissao(DT_Emissao);
  	}
  	if (util.doValida(DT_Entrada)) {
  		ed.setDT_Entrada(DT_Entrada);
  	}
  	if (util.doValida(HR_Entrada)) {
  		ed.setHR_Entrada(HR_Entrada);
  	}
  	if (util.doValida(NR_Pedido)) {
  		ed.setNR_Pedido(NR_Pedido);
  	}
  	if (util.doValida(DM_Situacao)) {
  		ed.setDM_Situacao(DM_Situacao);
  	}
  	if (util.doValida(NR_Peso)) {
  		ed.setNR_Peso(Double.parseDouble(NR_Peso));
  	}
  	if (util.doValida(NR_Cubagem)) {
  		ed.setNR_Cubagem(Double.parseDouble(NR_Cubagem));
  	}
  	if (util.doValida(NR_Cubagem_Total)) {
  		ed.setNR_Cubagem_Total(Double.parseDouble(NR_Cubagem_Total));
  	}
  	if (util.doValida(NR_Peso_Cubado)) {
  		ed.setNR_Peso_Cubado(Double.parseDouble(NR_Peso_Cubado));
  	}
  	if (util.doValida(NR_Volumes)) {
  		ed.setNR_Volumes(Double.parseDouble(NR_Volumes));
  	}
  	if (util.doValida(VL_Nota_Fiscal)) {
  		ed.setVL_Nota_Fiscal(Double.parseDouble(VL_Nota_Fiscal));
  	}
  	if (util.doValida(oid_Produto)) {
  		ed.setOID_Produto(Long.parseLong(oid_Produto));
  	}
  	if (util.doValida(NR_Lote)) {
  		ed.setNR_Lote(NR_Lote);
  	}
  	if (util.doValida(oid_Modal)) {
  		ed.setOID_Modal(Long.parseLong(oid_Modal));
  	}
  	if (util.doValida(DM_Tipo_Pagamento)) {
  		ed.setDM_Tipo_Pagamento(DM_Tipo_Pagamento);
  	} else ed.setDM_Tipo_Pagamento("");
  	if (util.doValida(TX_Observacao)) {
  		ed.setTX_Observacao(TX_Observacao);
  	}
  	if (util.doValida(NR_Itens)) {
  		ed.setNR_Itens(Double.parseDouble(NR_Itens));
  	}
  	if (util.doValida(VL_Total_Frete)) {
  		ed.setVL_Total_Frete(Double.parseDouble(VL_Total_Frete));
  	}
  	if (util.doValida(VL_Tabela)) {
  		ed.setVL_Tabela(Double.parseDouble(VL_Tabela));
  	}
  	if (util.doValida(NM_Especie)) {
  	    ed.setNM_Especie(NM_Especie);
  	}
  	new MinutaRN().altera(ed);
  }

  public void deleta(HttpServletRequest request)throws Excecoes{

    try{
      MinutaRN MinutaRN = new MinutaRN();
      Nota_FiscalED ed = new Nota_FiscalED();

      ed.setOID_Nota_Fiscal(request.getParameter("oid_Nota_Fiscal"));

      MinutaRN.deleta(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("erro ao excluir");
      excecoes.setMetodo("excluir");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }


	public ArrayList lista(HttpServletRequest request)
	throws Excecoes{
		String NR_Pedido = request.getParameter("FT_NR_Pedido");
		String DT_Emissao = request.getParameter("FT_DT_Emissao");
		String DT_Emissao_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
		String DT_Emissao_Final = request.getParameter("FT_DT_Emissao_Final");
		String DT_Saida_Inicial = request.getParameter("FT_DT_Saida_Inicial");
		String DT_Saida_Final = request.getParameter("FT_DT_Saida_Final");
		String nr_Nota_Fiscal = request.getParameter("FT_NR_Nota_Fiscal");
		String oid_Pessoa_Remetente = request.getParameter("oid_Pessoa_Remetente");
		String oid_Pessoa_Destinatario = request.getParameter("oid_Pessoa_Destinatario");
		String oid_Pessoa_Consignatario = request.getParameter("oid_Pessoa_Consignatario");
		String NR_Despacho = request.getParameter("FT_NR_Despacho");
		String CD_Acesso = request.getParameter("FT_CD_Acesso");
		String CD_Referencia = request.getParameter("FT_CD_Referencia");
		String DM_Situacao = request.getParameter("FT_DM_Situacao");
                String DM_Tipo_Consulta = request.getParameter("FT_DM_Tipo_Consulta");

		Nota_FiscalED ed = new Nota_FiscalED();

		if (util.doValida(NR_Pedido)) {
			ed.setNR_Pedido(NR_Pedido);
		}
		if (util.doValida(DT_Emissao)) {
			ed.setDT_Emissao(DT_Emissao);
		}
		if (util.doValida(DT_Emissao_Inicial)) {
			ed.setDT_Emissao_Inicial(DT_Emissao_Inicial);
		}
		if (util.doValida(DT_Emissao_Final)) {
			ed.setDT_Emissao_Final(DT_Emissao_Final);
		}
		if (util.doValida(DT_Saida_Inicial)) {
			ed.setDT_Saida_Inicial(DT_Saida_Inicial);
		}
		if (util.doValida(DT_Saida_Final)) {
			ed.setDT_Saida_Final(DT_Saida_Final);
		}
		if (util.doValida(nr_Nota_Fiscal)) {
			ed.setNR_Nota_Fiscal(Long.parseLong(nr_Nota_Fiscal));
	    }
	    if (util.doValida(oid_Pessoa_Remetente)) {
	    	ed.setOID_Pessoa(oid_Pessoa_Remetente);
	    }
	    if (util.doValida(oid_Pessoa_Destinatario)) {
	    	ed.setOID_Pessoa_Destinatario(oid_Pessoa_Destinatario);
	    }
	    if (util.doValida(oid_Pessoa_Consignatario)) {
	    	ed.setOID_Pessoa_Consignatario(oid_Pessoa_Consignatario);
	    }
	    if (util.doValida(NR_Despacho)) {
	    	ed.setNR_Despacho(Long.parseLong(NR_Despacho));
	    }
	    if (util.doValida(CD_Acesso)) {
	    	ed.setCD_Acesso(request.getParameter("FT_CD_Acesso"));
	    } else throw new Mensagens("Código de acesso não informado!");
	    if (util.doValida(CD_Referencia)) {
	    	ed.setCD_Referencia(CD_Referencia);
	    }
	    if (util.doValida(DM_Situacao)) {
	    	ed.setDM_Situacao(DM_Situacao);
	    }

	    return new MinutaRN().lista(ed);
	}


  public Nota_FiscalED getByRecord(HttpServletRequest request)throws Excecoes{

      Nota_FiscalED ed = new Nota_FiscalED();

      String NR_Nota_Fiscal = request.getParameter("FT_NR_Nota_Fiscal");
      if (NR_Nota_Fiscal != null && NR_Nota_Fiscal.length() > 0)
      {
        ed.setNR_Nota_Fiscal(new Long(request.getParameter("FT_NR_Nota_Fiscal")).longValue());
      }

      String NR_Despacho = request.getParameter("FT_NR_Despacho");
      if (NR_Despacho != null && NR_Despacho.length() > 0)
      {
        ed.setNR_Despacho(new Long(request.getParameter("FT_NR_Despacho")).longValue());
      }

      String NM_Serie = request.getParameter("FT_NM_Serie");
      if (NM_Serie != null && !NM_Serie.equals("") && !NM_Serie.equals("null"))
      {
        ed.setNM_Serie(request.getParameter("FT_NM_Serie"));
      }

      ed.setDM_Tipo_Conhecimento("");
      String DM_Tipo_Conhecimento = request.getParameter("FT_DM_Tipo_Conhecimento");
      if (String.valueOf(DM_Tipo_Conhecimento) != null && !String.valueOf(DM_Tipo_Conhecimento).equals("")
        && !String.valueOf(DM_Tipo_Conhecimento).equals("null")){
        ed.setDM_Tipo_Conhecimento(request.getParameter("FT_DM_Tipo_Conhecimento"));
      }


      ed.setOID_Nota_Fiscal(request.getParameter("oid_Nota_Fiscal"));
      ed.setOID_Pessoa(request.getParameter("oid_Pessoa_Remetente"));

      return new MinutaRN().getByRecord(ed);

  }

  public byte[] imprime_Minuta(HttpServletRequest request, HttpServletResponse Response)throws Excecoes{
    Nota_FiscalED ed = new Nota_FiscalED();

    String OID_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
    if (String.valueOf(OID_Nota_Fiscal) != null &&
      !String.valueOf(OID_Nota_Fiscal).equals("") &&
      !String.valueOf(OID_Nota_Fiscal).equals("null")){
      ed.setOID_Nota_Fiscal(OID_Nota_Fiscal);
    }

    MinutaRN MinutaRN = new MinutaRN();
    byte[] b = MinutaRN.imprime_Minuta(ed);

    return b;

  }

}
