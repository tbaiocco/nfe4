package com.master.iu;

import javax.servlet.http.*;

import com.master.rn.ManifestoRN;
import com.master.rn.Manifesto_InternacionalRN;
import com.master.root.Parametro_FilialBean;
import com.master.ed.ManifestoED;
import com.master.ed.Manifesto_InternacionalED;
import com.master.util.Excecoes;
import com.master.util.ed.Parametro_FixoED;

import java.util.*;

public class man009Bean {

  public void altera(HttpServletRequest request)throws Excecoes{

    try{
      Manifesto_InternacionalRN Manifesto_Internacionalrn = new Manifesto_InternacionalRN();
      Manifesto_InternacionalED ed = new Manifesto_InternacionalED();
      ed.setOID_Manifesto_Internacional(request.getParameter("oid_Manifesto_Internacional"));
      ed.setCD_Roteiro(request.getParameter("FT_CD_Roteiro"));
      ed.setOID_Pessoa(request.getParameter("oid_Pessoa"));
      ed.setOID_Pessoa_Permisso(request.getParameter("oid_Pessoa_Permisso"));
      ed.setOID_Pessoa_Permisso2(request.getParameter("oid_Pessoa_Permisso2"));
      ed.setOID_Veiculo(request.getParameter("oid_Veiculo"));
      ed.setOID_Cidade(new Long(request.getParameter("oid_Cidade")).longValue());
      
      ed.setOID_Unidade_Origem(new Integer(request.getParameter("oid_Unidade_Origem")).intValue());
      ed.setOID_Unidade_Destino(new Integer(request.getParameter("oid_Unidade_Destino")).intValue());
      ed.setOID_Unidade_Fronteira(new Integer(request.getParameter("oid_Unidade_Fronteira")).intValue());
      
      ed.setDM_Expresso(request.getParameter("FT_DM_Expresso"));
      ed.setDT_Previsao_Chegada(request.getParameter("FT_DT_Previsao_Chegada"));
      ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));
      ed.setDT_Troca_Motorista(request.getParameter("FT_DT_Troca_Motorista"));
      ed.setHR_Previsao_Chegada(request.getParameter("FT_HR_Previsao_Chegada"));
      ed.setNM_Liberacao_Seguradora(request.getParameter("FT_NM_Liberacao_Seguradora"));
      ed.setDM_Transito_Aduaneiro(request.getParameter("FT_DM_Transito_Aduaneiro"));
      ed.setOID_Cidade_Alfandega(new Long(request.getParameter("oid_Cidade_Alfandega")).longValue());
      ed.setOID_Cidade_Destino(new Long(request.getParameter("oid_Cidade_Destino")).longValue());
      ed.setNM_Cidade_Destino(request.getParameter("FT_NM_Cidade_Destino"));
      ed.setNM_Pais(request.getParameter("FT_NM_Pais"));
      
      ed.setOID_Aduana(new Long(request.getParameter("oid_Aduana")).longValue());
      ed.setOID_Aduana_Destino(new Long(request.getParameter("oid_Aduana_Destino")).longValue());

      ed.setTX_Observacao1(request.getParameter("FT_TX_Observacao1"));
      ed.setTX_Observacao2(request.getParameter("FT_TX_Observacao2"));
      ed.setTX_Observacao3(request.getParameter("FT_TX_Observacao3"));
      ed.setTX_Observacao4(request.getParameter("FT_TX_Observacao4"));
      ed.setTX_Observacao5(request.getParameter("FT_TX_Observacao5"));
      ed.setTX_Observacao6(request.getParameter("FT_TX_Observacao6"));
      ed.setTX_Observacao7(request.getParameter("FT_TX_Observacao7"));
      ed.setTX_Observacao8(request.getParameter("FT_TX_Observacao8"));
      ed.setTX_Observacao9(request.getParameter("FT_TX_Observacao9"));
      ed.setTX_Observacao10(request.getParameter("FT_TX_Observacao10"));
      ed.setTX_Observacao11(request.getParameter("FT_TX_Observacao11"));
      ed.setTX_Observacao12(request.getParameter("FT_TX_Observacao12"));
      ed.setTX_Observacao13(request.getParameter("FT_TX_Observacao13"));
      ed.setTX_Observacao14(request.getParameter("FT_TX_Observacao14"));
      ed.setTX_Observacao15(request.getParameter("FT_TX_Observacao15"));
      ed.setTX_Observacao16(request.getParameter("FT_TX_Observacao16"));
      ed.setTX_Observacao17(request.getParameter("FT_TX_Observacao17"));
      ed.setTX_Observacao18(request.getParameter("FT_TX_Observacao18"));
      
      ed.setTX_Rota1(request.getParameter("FT_TX_Rota1"));
      ed.setTX_Rota2(request.getParameter("FT_TX_Rota2"));
      ed.setTX_Rota3(request.getParameter("FT_TX_Rota3"));
      ed.setNM_Lacre(request.getParameter("FT_NM_Lacre"));
      ed.setTX_Rota4(request.getParameter("FT_TX_Rota4"));
      ed.setTX_Rota5(request.getParameter("FT_TX_Rota5"));
      ed.setTX_Rota6(request.getParameter("FT_TX_Rota6"));
      ed.setTX_Rota7(request.getParameter("FT_TX_Rota7"));
      ed.setTX_Rota8(request.getParameter("FT_TX_Rota8"));
      ed.setTX_Rota9(request.getParameter("FT_TX_Rota9"));
      
      ed.setDM_Transito_Aduaneiro(request.getParameter("FT_DM_Transito_Aduaneiro"));


      String NR_Odometro_Inicial = request.getParameter("FT_NR_Odometro_Inicial");
      if (String.valueOf(NR_Odometro_Inicial) != null &&
          !String.valueOf(NR_Odometro_Inicial).equals("")){
        ed.setNR_Odometro_Inicial(new Long(NR_Odometro_Inicial).longValue());
      }

      String oid_Seguradora = request.getParameter("oid_Seguradora");
      if (String.valueOf(oid_Seguradora) != null &&
          !String.valueOf(oid_Seguradora).equals("")){
        ed.setOID_Seguradora(new Long(oid_Seguradora).longValue());
      }

     ed.setOID_Veiculo_Carreta("");
     String oid_Veiculo_Carreta = request.getParameter("oid_Veiculo_Carreta");
      if (String.valueOf(oid_Veiculo_Carreta) != null &&
          !String.valueOf(oid_Veiculo_Carreta).equals("null") &&
          !String.valueOf(oid_Veiculo_Carreta).equals("")){
          ed.setOID_Veiculo_Carreta(request.getParameter("oid_Veiculo_Carreta"));
      }
      ed.setNM_Pessoa_Proprietario(request.getParameter("FT_NM_Razao_Social_Proprietario"));
      ed.setNM_End_Proprietario(request.getParameter("FT_NM_Endereco_Proprietario"));
      ed.setNM_Cid_UF_Pais_Proprietario(request.getParameter("FT_NM_Cidade_UF_Pais_Proprietario"));

      Manifesto_Internacionalrn.altera(ed);
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
      Manifesto_InternacionalRN Manifesto_Internacionalrn = new Manifesto_InternacionalRN();
      Manifesto_InternacionalED ed = new Manifesto_InternacionalED();

      ed.setOID_Manifesto_Internacional(request.getParameter("oid_Manifesto_Internacional"));

      Manifesto_Internacionalrn.deleta(ed);
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

  public ArrayList Manifesto_Internacional_Lista(HttpServletRequest request)throws Excecoes{

      Manifesto_InternacionalED ed = new Manifesto_InternacionalED();

      ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));
      ed.setDT_Fim(request.getParameter("FT_DT_Fim"));
// System.out.println(request.getParameter("FT_NR_Manifesto_Internacional"));
      String nr_Manifesto_Internacional = request.getParameter("FT_NR_Manifesto_Internacional");
      if (String.valueOf(nr_Manifesto_Internacional) != null &&
        !String.valueOf(nr_Manifesto_Internacional).equals("") &&
        !String.valueOf(nr_Manifesto_Internacional).equals("null")){
        ed.setNR_Manifesto_Internacional(new Long(nr_Manifesto_Internacional).longValue());
      }

      ed.setOID_Pessoa(request.getParameter("oid_Pessoa"));
      ed.setOID_Veiculo(request.getParameter("oid_Veiculo"));
      
      ed.setOID_Veiculo_Carreta(request.getParameter("oid_Veiculo_Carreta"));

      String oid_Unidade_Origem = request.getParameter("oid_Unidade_Origem");
      if (String.valueOf(oid_Unidade_Origem) != null && !String.valueOf(oid_Unidade_Origem).equals("") && !String.valueOf(oid_Unidade_Origem).equals("null")){
        ed.setOID_Unidade_Origem(new Integer(oid_Unidade_Origem).intValue());
      }
      String oid_Unidade_Destino = request.getParameter("oid_Unidade_Destino");
      if (String.valueOf(oid_Unidade_Destino) != null && !String.valueOf(oid_Unidade_Destino).equals("") && !String.valueOf(oid_Unidade_Destino).equals("null")){
        ed.setOID_Unidade_Destino(new Integer(oid_Unidade_Destino).intValue());
      }
      String oid_Unidade_Fronteira = request.getParameter("oid_Unidade_Fronteira");
      if (String.valueOf(oid_Unidade_Fronteira) != null && !String.valueOf(oid_Unidade_Fronteira).equals("") && !String.valueOf(oid_Unidade_Fronteira).equals("null")){
        ed.setOID_Unidade_Fronteira(new Integer(oid_Unidade_Fronteira).intValue());
      }
      String nr_Conhecimento = request.getParameter("FT_NR_Conhecimento");
      if (String.valueOf(nr_Conhecimento) != null &&
        !String.valueOf(nr_Conhecimento).equals("") &&
        !String.valueOf(nr_Conhecimento).equals("null")){
        ed.setNR_Conhecimento(new Long(nr_Conhecimento).longValue());
      }

      return new Manifesto_InternacionalRN().lista(ed);

  }

  public ArrayList Manifesto_Internacional_Lista_Acerto(HttpServletRequest request)throws Excecoes{

      Manifesto_InternacionalED ed = new Manifesto_InternacionalED();

      String oid_Acerto = request.getParameter("oid_Acerto");
      if (String.valueOf(oid_Acerto) != null &&
        !String.valueOf(oid_Acerto).equals("") &&
        !String.valueOf(oid_Acerto).equals("null")){
        ed.setOID_Acerto(new Long(oid_Acerto).longValue());
      }

      return new Manifesto_InternacionalRN().Manifesto_Internacional_Lista_Acerto(ed);

  }


  public void geraRelatorio(HttpServletRequest req)throws Excecoes{
    Manifesto_InternacionalED ed = new Manifesto_InternacionalED();

    Manifesto_InternacionalRN geRN = new Manifesto_InternacionalRN();
    geRN.geraRelatorio(ed);
  }

  public Manifesto_InternacionalED inclui(HttpServletRequest request)throws Excecoes{

    try{
      Manifesto_InternacionalRN Manifesto_Internacionalrn = new Manifesto_InternacionalRN();
      Manifesto_InternacionalED ed = new Manifesto_InternacionalED();

      ed.setDM_Tipo_Operacao(request.getParameter("FT_DM_Tipo_Operacao"));
      ed.setDM_Transito_Aduaneiro(request.getParameter("FT_DM_Transito_Aduaneiro"));
      ed.setDM_Lancado_Ordem_Frete("N");
      
      ed.setOID_Pessoa(request.getParameter("oid_Pessoa"));
	
      ed.setOID_Veiculo(request.getParameter("oid_Veiculo"));
      ed.setOID_Cidade(new Long(request.getParameter("oid_Cidade")).longValue());
      ed.setOID_Cidade_Origem(new Long(request.getParameter("oid_Cidade_Origem")).longValue());
      
      if (request.getParameter("FT_DM_Tipo_Operacao") != null && (request.getParameter("FT_DM_Tipo_Operacao").equals("1") || request.getParameter("FT_DM_Tipo_Operacao").equals("3"))){
	      ed.setOID_Cidade_Alfandega(new Long(request.getParameter("oid_Cidade_Alfandega")).longValue());
	      ed.setOID_Seguradora(new Long(request.getParameter("oid_Seguradora")).longValue());
	      ed.setOID_Unidade_Fronteira(new Integer(request.getParameter("oid_Unidade_Fronteira")).intValue());
	      ed.setOID_Aduana(new Long(request.getParameter("oid_Aduana")).longValue());
	      ed.setOID_Aduana_Destino(new Long(request.getParameter("oid_Aduana_Destino")).longValue());
      } else {
    	  if(Parametro_FixoED.getInstancia().getNM_Empresa().equals("COSTARICA")){
    		  ed.setOID_Cidade_Alfandega(new Long(2737).longValue());
		      ed.setOID_Seguradora(new Long(4).longValue());
		      ed.setOID_Unidade_Fronteira(new Integer(6).intValue());
		      ed.setOID_Aduana(new Long(1).longValue());
		      ed.setOID_Aduana_Destino(new Long(1).longValue());
    	  } else {
	          ed.setOID_Cidade_Alfandega(new Long(659).longValue());
		      ed.setOID_Seguradora(new Long(4).longValue());
		      ed.setOID_Unidade_Fronteira(new Integer(20).intValue());
		      ed.setOID_Aduana(new Long(3).longValue());
		      ed.setOID_Aduana_Destino(new Long(3).longValue());
    	  }
      }
      
      ed.setOID_Cidade_Destino(new Long(request.getParameter("oid_Cidade_Destino")).longValue());
      ed.setNM_Cidade_Destino(request.getParameter("FT_NM_Cidade_Destino"));
      ed.setNM_Pais(request.getParameter("FT_NM_Pais"));
      
      ed.setOID_Unidade_Origem(new Integer(request.getParameter("oid_Unidade_Origem")).intValue());
      ed.setOID_Unidade_Destino(new Integer(request.getParameter("oid_Unidade_Destino")).intValue());
      
      ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));
      ed.setDM_Expresso(request.getParameter("FT_DM_Expresso"));
      ed.setDT_Previsao_Chegada(request.getParameter("FT_DT_Previsao_Chegada"));
      ed.setHR_Previsao_Chegada(request.getParameter("FT_HR_Previsao_Chegada"));
      ed.setNM_Liberacao_Seguradora(request.getParameter("FT_NM_Liberacao_Seguradora"));

      ed.setTX_Observacao1(request.getParameter("FT_TX_Observacao1"));
      ed.setTX_Observacao2(request.getParameter("FT_TX_Observacao2"));
      ed.setTX_Observacao3(request.getParameter("FT_TX_Observacao3"));
      ed.setTX_Observacao4(request.getParameter("FT_TX_Observacao4"));
      ed.setTX_Observacao5(request.getParameter("FT_TX_Observacao5"));
      ed.setTX_Observacao6(request.getParameter("FT_TX_Observacao6"));
      ed.setTX_Observacao7(request.getParameter("FT_TX_Observacao7"));
      ed.setTX_Observacao8(request.getParameter("FT_TX_Observacao8"));
      ed.setTX_Observacao9(request.getParameter("FT_TX_Observacao9"));
      ed.setTX_Observacao10(request.getParameter("FT_TX_Observacao10"));
      ed.setTX_Observacao11(request.getParameter("FT_TX_Observacao11"));
      ed.setTX_Observacao12(request.getParameter("FT_TX_Observacao12"));
      ed.setTX_Observacao13(request.getParameter("FT_TX_Observacao13"));
      ed.setTX_Observacao14(request.getParameter("FT_TX_Observacao14"));
      ed.setTX_Observacao15(request.getParameter("FT_TX_Observacao15"));
      ed.setTX_Observacao16(request.getParameter("FT_TX_Observacao16"));
      ed.setTX_Observacao17(request.getParameter("FT_TX_Observacao17"));
      ed.setTX_Observacao18(request.getParameter("FT_TX_Observacao18"));

      ed.setTX_Rota1(" ");
      ed.setTX_Rota2(" ");
      ed.setTX_Rota3(" ");
      ed.setNM_Lacre(" ");
      
      ed.setTX_Rota4(request.getParameter("FT_TX_Rota4"));
      ed.setTX_Rota5(request.getParameter("FT_TX_Rota5"));
      ed.setTX_Rota6(request.getParameter("FT_TX_Rota6"));
      ed.setTX_Rota7(request.getParameter("FT_TX_Rota7"));
      ed.setTX_Rota8(request.getParameter("FT_TX_Rota8"));
      ed.setTX_Rota9(request.getParameter("FT_TX_Rota9"));

      String TX_Rota1 = request.getParameter("FT_TX_Rota1");
      if (String.valueOf(TX_Rota1) != null &&
          !String.valueOf(TX_Rota1).equals("")&&
          !String.valueOf(TX_Rota1).equals("null")){
         ed.setTX_Rota1(TX_Rota1);
      }
      String TX_Rota2 = request.getParameter("FT_TX_Rota2");
      if (String.valueOf(TX_Rota2) != null &&
          !String.valueOf(TX_Rota2).equals("")&&
          !String.valueOf(TX_Rota2).equals("null")){
         ed.setTX_Rota2(TX_Rota2);
      }
      String TX_Rota3 = request.getParameter("FT_TX_Rota3");
      if (String.valueOf(TX_Rota3) != null &&
           !String.valueOf(TX_Rota3).equals("")&&
          !String.valueOf(TX_Rota3).equals("null")){
         ed.setTX_Rota3(TX_Rota3);
      }
      String NM_Lacre = request.getParameter("FT_NM_Lacre");
      if (String.valueOf(NM_Lacre) != null &&
          !String.valueOf(NM_Lacre).equals("")&&
          !String.valueOf(NM_Lacre).equals("null")){
         ed.setNM_Lacre(NM_Lacre);
      }

      String NR_Odometro_Inicial = request.getParameter("FT_NR_Odometro_Inicial");
      if (String.valueOf(NR_Odometro_Inicial) != null &&
          !String.valueOf(NR_Odometro_Inicial).equals("")){
        ed.setNR_Odometro_Inicial(new Long(NR_Odometro_Inicial).longValue());
      }

      String CD_Roteiro = request.getParameter("FT_CD_Roteiro");
      if (String.valueOf(CD_Roteiro) != null &&
          !String.valueOf(CD_Roteiro).equals("")&&
          !String.valueOf(CD_Roteiro).equals("null")){
         ed.setCD_Roteiro(CD_Roteiro);
      }

     ed.setOID_Veiculo_Carreta("");
     String oid_Veiculo_Carreta = request.getParameter("oid_Veiculo_Carreta");
      if (String.valueOf(oid_Veiculo_Carreta) != null &&
          !String.valueOf(oid_Veiculo_Carreta).equals("null") &&
          !String.valueOf(oid_Veiculo_Carreta).equals("")){
          ed.setOID_Veiculo_Carreta(request.getParameter("oid_Veiculo_Carreta"));
      }
      ed.setNM_Pessoa_Proprietario(request.getParameter("FT_NM_Razao_Social_Proprietario"));
      ed.setNM_End_Proprietario(request.getParameter("FT_NM_Endereco_Proprietario"));
      ed.setNM_Cid_UF_Pais_Proprietario(request.getParameter("FT_NM_Cidade_UF_Pais_Proprietario"));

      return Manifesto_Internacionalrn.inclui(ed);
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

  public Manifesto_InternacionalED getByRecord(HttpServletRequest request)throws Excecoes{

      Manifesto_InternacionalED ed = new Manifesto_InternacionalED();

      String oid_Manifesto = request.getParameter("oid_Manifesto");
      if (String.valueOf(oid_Manifesto) != null &&
          !String.valueOf(oid_Manifesto).equals("") &&
          !String.valueOf(oid_Manifesto).equals("null")){
        ed.setOID_Manifesto_Internacional(request.getParameter("oid_Manifesto"));
      }
      String NR_Manifesto = request.getParameter("FT_NR_Manifesto");
      if (String.valueOf(NR_Manifesto) != null &&
          !String.valueOf(NR_Manifesto).equals("") &&
          !String.valueOf(NR_Manifesto).equals("null")){
        ed.setNR_Manifesto_Internacional(new Long(request.getParameter("FT_NR_Manifesto")).longValue());
      }
      
      String NR_Manifesto_Internacional = request.getParameter("FT_NR_Manifesto_Internacional");
      if (String.valueOf(NR_Manifesto_Internacional) != null &&
          !String.valueOf(NR_Manifesto_Internacional).equals("") &&
          !String.valueOf(NR_Manifesto_Internacional).equals("null")){
        ed.setNR_Manifesto_Internacional(new Long(request.getParameter("FT_NR_Manifesto_Internacional")).longValue());
      }

      String oid_Unidade = request.getParameter("oid_Unidade");
      if (String.valueOf(oid_Unidade) != null &&
          !String.valueOf(oid_Unidade).equals("") &&
          !String.valueOf(oid_Unidade).equals("null")){
         ed.setOID_Unidade(new Long(request.getParameter("oid_Unidade")).longValue());
      }

      String oid_Manifesto_Internacional = request.getParameter("oid_Manifesto_Internacional");

      if (String.valueOf(oid_Manifesto_Internacional) != null &&
          !String.valueOf(oid_Manifesto_Internacional).equals("") &&
          !String.valueOf(oid_Manifesto_Internacional).equals("null")){
        ed.setOID_Manifesto_Internacional(request.getParameter("oid_Manifesto_Internacional"));
      }
      
      return new Manifesto_InternacionalRN().getByRecord(ed);

  }
  
  public void inclui_Ocorrencia(HttpServletRequest request)throws Excecoes{

      try{
        Manifesto_InternacionalRN Manifesto_IntRN = new Manifesto_InternacionalRN();
        Manifesto_InternacionalED ed = new Manifesto_InternacionalED();

         //// System.out.println("1");

         ed.setOID_Manifesto_Internacional(request.getParameter("oid_Manifesto"));
         ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));

         ed.setDT_Chegada("");
         String DT_Chegada = request.getParameter("FT_DT_Chegada");
          if (String.valueOf(DT_Chegada) != null &&
              !String.valueOf(DT_Chegada).equals("null") &&
              !String.valueOf(DT_Chegada).equals("")){
              ed.setDT_Chegada(request.getParameter("FT_DT_Chegada"));
          }
         //// System.out.println("2");

         ed.setHR_Chegada("");
         String HR_Chegada = request.getParameter("FT_HR_Chegada");
          if (String.valueOf(HR_Chegada) != null &&
              !String.valueOf(HR_Chegada).equals("null") &&
              !String.valueOf(HR_Chegada).equals("")){
              ed.setHR_Chegada(request.getParameter("FT_HR_Chegada"));
          }


        ed.setOID_Tipo_Ocorrencia(new Long(request.getParameter("oid_Tipo_Ocorrencia")).longValue());

         ed.setTX_Observacao1("");
         String TX_Observacao = request.getParameter("FT_TX_Observacao");
          if (String.valueOf(TX_Observacao) != null &&
              !String.valueOf(TX_Observacao).equals("null") &&
              !String.valueOf(TX_Observacao).equals("")){
              ed.setTX_Observacao1(request.getParameter("FT_TX_Observacao"));
          }
          ed.setDM_Tipo(request.getParameter("FT_DM_Tipo"));


         //// System.out.println("3");


        Manifesto_IntRN.inclui_Ocorrencia(ed);
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
  
  public void geraRelMICEmitido(HttpServletRequest request, HttpServletResponse Response)throws Excecoes{

      Manifesto_InternacionalED ed = new Manifesto_InternacionalED();

      ed.setDT_Emissao_Inicial(request.getParameter("FT_DT_Emissao"));
      ed.setDT_Emissao_Final(request.getParameter("FT_DT_Fim"));

      ed.setOID_Pessoa(request.getParameter("oid_Pessoa_Remetente"));
      ed.setOID_Pessoa_Destinatario(request.getParameter("oid_Pessoa_Destinatario"));
      ed.setOID_Veiculo(request.getParameter("oid_Veiculo"));
      
      String oid_Unidade_Origem = request.getParameter("oid_Unidade_Origem");
      if (String.valueOf(oid_Unidade_Origem) != null && !String.valueOf(oid_Unidade_Origem).equals("")){
        ed.setOID_Unidade_Origem(new Integer(oid_Unidade_Origem).intValue());
      }
      String oid_Unidade_Destino = request.getParameter("oid_Unidade_Destino");
      if (String.valueOf(oid_Unidade_Destino) != null && !String.valueOf(oid_Unidade_Destino).equals("")){
        ed.setOID_Unidade_Destino(new Integer(oid_Unidade_Destino).intValue());
      }
      

      Manifesto_InternacionalRN geRN = new Manifesto_InternacionalRN();
      geRN.geraRelMICEmitido(ed, Response);

  }

}
