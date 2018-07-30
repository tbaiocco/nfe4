package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.EDI_ImportacaoED;
import com.master.ed.Lote_FaturamentoED;
import com.master.rn.EDI_ImportacaoRN;
import com.master.rn.Lote_FaturamentoRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.rn.FaturamentoRN;
import com.master.ed.FaturamentoED;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class Lote_FaturamentoBean {


  public void altera(HttpServletRequest request)throws Excecoes{

    try{
      Lote_FaturamentoRN Lote_Faturamentorn = new Lote_FaturamentoRN();
      Lote_FaturamentoED ed = new Lote_FaturamentoED();

          //// System.out.println("man -1" );

      ed.setOID_Lote_Faturamento(new Long(request.getParameter("oid_Lote_Faturamento")).longValue());

      ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));
      ed.setDT_Vencimento(request.getParameter("FT_DT_Vencimento"));
          // System.out.println("man " );



      Lote_Faturamentorn.altera(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("erro ao alterar");
      excecoes.setMetodo("alterar");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }



  public void deleta(HttpServletRequest request)throws Excecoes{

    try{
      Lote_FaturamentoRN Lote_Faturamentorn = new Lote_FaturamentoRN();
      Lote_FaturamentoED ed = new Lote_FaturamentoED();

      ed.setOID_Lote_Faturamento(new Long(request.getParameter("oid_Lote_Faturamento")).longValue());

      Lote_Faturamentorn.deleta(ed);
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

  public ArrayList Lote_Faturamento_Lista(HttpServletRequest request)throws Excecoes{

      Lote_FaturamentoED ed = new Lote_FaturamentoED();
//// System.out.println("1 ");

      String oid_Lote_Faturamento = request.getParameter("oid_Lote_Faturamento");
      if (String.valueOf(oid_Lote_Faturamento) != null &&
          !String.valueOf(oid_Lote_Faturamento).equals("") &&
          !String.valueOf(oid_Lote_Faturamento).equals("null")){
          ed.setOID_Lote_Faturamento(new Long(request.getParameter("oid_Lote_Faturamento")).longValue());
      }

      String NR_Documento_Cobranca = request.getParameter("FT_NR_Documento_Cobranca");
      if (String.valueOf(NR_Documento_Cobranca) != null &&
          !String.valueOf(NR_Documento_Cobranca).equals("") &&
          !String.valueOf(NR_Documento_Cobranca).equals("null")){
          ed.setNR_Documento_Cobranca(new Long(request.getParameter("FT_NR_Documento_Cobranca")).longValue());
      }

      String NR_Pre_Fatura = request.getParameter("FT_NR_Pre_Fatura");
      if (String.valueOf(NR_Pre_Fatura) != null &&
          !String.valueOf(NR_Pre_Fatura).equals("") &&
          !String.valueOf(NR_Pre_Fatura).equals("null")){
          ed.setNR_Pre_Fatura(NR_Pre_Fatura);
      }




       String DT_Emissao_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
        if (String.valueOf(DT_Emissao_Inicial) != null &&
            !String.valueOf(DT_Emissao_Inicial).equals("null") &&
            !String.valueOf(DT_Emissao_Inicial).equals("")){
            ed.setDT_Emissao_Inicial(request.getParameter("FT_DT_Emissao_Inicial"));
        }
       String DT_Emissao_Final = request.getParameter("FT_DT_Emissao_Final");
        if (String.valueOf(DT_Emissao_Final) != null &&
            !String.valueOf(DT_Emissao_Final).equals("null") &&
            !String.valueOf(DT_Emissao_Final).equals("")){
            ed.setDT_Emissao_Final(request.getParameter("FT_DT_Emissao_Final"));
        }

       String OID_Pessoa = request.getParameter("oid_Pessoa");
        if (String.valueOf(OID_Pessoa) != null &&
            !String.valueOf(OID_Pessoa).equals("null") &&
            !String.valueOf(OID_Pessoa).equals("")){
            ed.setOID_Pessoa(request.getParameter("oid_Pessoa"));
        }

       String DM_Situacao = request.getParameter("DM_Situacao");
        if (String.valueOf(DM_Situacao) != null &&
            !String.valueOf(DM_Situacao).equals("null") &&
            !String.valueOf(DM_Situacao).equals("")){
            ed.setDM_Situacao(request.getParameter("DM_Situacao"));
        }
//// System.out.println("2 ");
      String oid_Unidade = request.getParameter("oid_Unidade");
      if (String.valueOf(oid_Unidade) != null &&
        !String.valueOf(oid_Unidade).equals("") &&
        !String.valueOf(oid_Unidade).equals("null")){
        ed.setOID_Unidade(new Long(oid_Unidade).longValue());
      }
//// System.out.println("5 ");

      return new Lote_FaturamentoRN().lista(ed);

  }


  public Lote_FaturamentoED inclui(HttpServletRequest request)throws Excecoes{

    try{

      // System.out.println("man 0" );

      Lote_FaturamentoRN Lote_Faturamentorn = new Lote_FaturamentoRN();
      Lote_FaturamentoED ed = new Lote_FaturamentoED();

      ed.setOID_Pessoa(request.getParameter("oid_Pessoa"));

          // System.out.println("man 0" );

      ed.setDM_Situacao("I");

      ed.setOID_Unidade(new Long(request.getParameter("oid_Unidade")).longValue());
      ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));
      ed.setDT_Vencimento(request.getParameter("FT_DT_Vencimento"));
          // System.out.println("man 3" );

      ed.setDM_Tipo_Documento(request.getParameter("FT_DM_Tipo_Documento"));

      String VL_Cobranca = request.getParameter("FT_VL_Cobranca");
      if (String.valueOf(VL_Cobranca) != null &&
        !String.valueOf(VL_Cobranca).equals("") &&
        !String.valueOf(VL_Cobranca).equals("null")){
        ed.setVL_Cobranca(new Double(VL_Cobranca).doubleValue());
      }

      return Lote_Faturamentorn.inclui(ed);
    }
    catch (Excecoes exc){
      throw exc;
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("erro ao incluir");
      excecoes.setMetodo("inclui");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public Lote_FaturamentoED getByRecord(HttpServletRequest request)throws Excecoes{

    // System.out.println("lote Fat get by bea  2 ->>");
      Lote_FaturamentoED ed = new Lote_FaturamentoED();
      String oid_Lote_Faturamento = request.getParameter("oid_Lote_Faturamento");
      if (String.valueOf(oid_Lote_Faturamento) != null &&
          !String.valueOf(oid_Lote_Faturamento).equals("") &&
          !String.valueOf(oid_Lote_Faturamento).equals("null")){
          ed.setOID_Lote_Faturamento(new Long(request.getParameter("oid_Lote_Faturamento")).longValue());
      }

      return new Lote_FaturamentoRN().getByRecord(ed);

  }

  public Lote_FaturamentoED getByRecord(long oid_Lote_Faturamento)throws Excecoes{

    // System.out.println("lote Fat get by bea  2 ->>");
      Lote_FaturamentoED ed = new Lote_FaturamentoED();
      ed.setOID_Lote_Faturamento (oid_Lote_Faturamento);

      return new Lote_FaturamentoRN().getByRecord(ed);

  }

  public byte[] imprime_Lote_Faturamento(HttpServletRequest request, HttpServletResponse Response)throws Excecoes{
      Lote_FaturamentoED ed = new Lote_FaturamentoED();
      String oid_Lote_Faturamento = request.getParameter("oid_Lote_Faturamento");

      if (String.valueOf(oid_Lote_Faturamento) != null &&
          !String.valueOf(oid_Lote_Faturamento).equals("") &&
          !String.valueOf(oid_Lote_Faturamento).equals("null")){
          ed.setOID_Lote_Faturamento(new Long(request.getParameter("oid_Lote_Faturamento")).longValue());
      }
 // System.out.println("iu 4 ");

    Lote_FaturamentoRN geRN = new Lote_FaturamentoRN();

    return geRN.imprime_Lote_Faturamento(ed);

  }

  public FaturamentoED geraFatura(HttpServletRequest request)throws Excecoes{

      FaturamentoED ed = new FaturamentoED();
      // System.out.println("geraFatura  incluiFatura ->> " + request.getParameter("oid_Unidade"));

      ed.setOID_Unidade(new Long(request.getParameter("oid_Unidade")).longValue());

      ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));
      ed.setDT_Vencimento(request.getParameter("FT_DT_Vencimento"));
      ed.setDT_Emissao_Inicial("");
      ed.setDT_Emissao_Final("");
      ed.setOID_Pessoa_Pagador(request.getParameter("oid_Pessoa"));
      ed.setNM_Lote_Faturamento(request.getParameter("oid_Lote_Faturamento"));
      ed.setNM_Lote_Faturamento(request.getParameter("oid_Lote_Faturamento"));
      if(JavaUtil.doValida(request.getParameter("oid_Lote_Faturamento"))){
          ed.setOid_Lote_Faturamento(new Long(request.getParameter("oid_Lote_Faturamento")).longValue());
      }

      return new FaturamentoRN().geraFatura(ed);

  }

  public FaturamentoED geraFaturaOrdem_Frete_Terceiro(HttpServletRequest request)throws Excecoes{

      FaturamentoED ed = new FaturamentoED();
      // System.out.println("geraFatura  geraFaturaOrdem_Frete_Terceiro ->> " + request.getParameter("oid_Unidade"));

      ed.setOid_Lote_Faturamento(new Long(request.getParameter("oid_Lote_Faturamento")).longValue());

      return new FaturamentoRN().geraFaturaOrdem_Frete_Terceiro(ed);

  }

  public void importaArquivo(HttpServletRequest request) throws Excecoes {

	    // System.out.println ("inicio importaArquivo  ");
	  Lote_FaturamentoED ed = new Lote_FaturamentoED();

	    try {

	      String arquivo = request.getParameter ("arquivo");
	      String padrao = request.getParameter ("padrao");
	      if(JavaUtil.doValida(request.getParameter("oid_Pessoa"))){
	          ed.setOID_Pessoa(request.getParameter("oid_Pessoa"));
	      }

	      new Lote_FaturamentoRN().importaArquivo(arquivo, padrao, ed);
	    }
	    catch (Excecoes exc) {
	      throw exc;
	    }
	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      excecoes.setMensagem ("ERRO NO LAY-OUT DO ARQUIVO. VOCE DEVE LIMPAR O DIRETORIO E REFAZER O PROCESSO !!! ");
	      excecoes.setMetodo ("importaArquivo");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }
	  }

  public void importaArquivo(String arquivo, String padrao, String oid_Pessoa) throws Excecoes {

	    // System.out.println ("inicio importaArquivo  ");
	  Lote_FaturamentoED ed = new Lote_FaturamentoED();

	    try {
	    	if(JavaUtil.doValida(oid_Pessoa)){
		          ed.setOID_Pessoa(oid_Pessoa);
		      }
	      new Lote_FaturamentoRN().importaArquivo(arquivo, padrao, ed);
	    }
	    catch (Excecoes exc) {
	      throw exc;
	    }
	    catch (Exception exc) {
	      Excecoes excecoes = new Excecoes ();
	      excecoes.setClasse (this.getClass ().getName ());
	      excecoes.setMensagem ("ERRO NO LAY-OUT DO ARQUIVO. VOCE DEVE LIMPAR O DIRETORIO E REFAZER O PROCESSO !!! ");
	      excecoes.setMetodo ("importaArquivo");
	      excecoes.setExc (exc);
	      throw excecoes;
	    }
	  }

}
