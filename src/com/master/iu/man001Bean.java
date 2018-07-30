package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.ManifestoED;
import com.master.rn.ManifestoRN;
import com.master.root.UsuarioBean;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FormataValor;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;
import com.master.util.Utilitaria;

public class man001Bean {
    Utilitaria util = new Utilitaria();
    FormataValor formataValor = new FormataValor();

  public void altera(HttpServletRequest request)throws Excecoes{
      String NR_Odometro_Inicial = request.getParameter("FT_NR_Odometro_Inicial");
      String NR_Odometro_Final = request.getParameter("FT_NR_Odometro_Final");
      String NM_Liberacao_Seguradora = request.getParameter("FT_NM_Liberacao_Seguradora");
      String CD_Roteiro = request.getParameter("FT_CD_Roteiro");
      String oid_Veiculo_Carreta = request.getParameter("oid_Veiculo_Carreta");
      String oid_Subregiao = request.getParameter("oid_Subregiao");
      String oid_Pessoa_Unidade_Destino = request.getParameter("oid_Pessoa_Unidade_Destino");
      String oid_Pessoa_Reveza = request.getParameter("oid_Pessoa_Reveza");
      String DT_Troca_Motorista = request.getParameter("DT_Troca_Motorista");
      String oid_Unidade_Destino = request.getParameter("oid_Unidade_Destino");
      String NR_Odometro_Troca_Motorista = request.getParameter("FT_NR_Odometro_Troca_Motorista");
      String DM_Lancado_Ordem_Frete = request.getParameter("FT_DM_Lancado_Ordem_Frete");
      String NM_Ajudante1 = request.getParameter("FT_NM_Ajudante1");
      String NM_Ajudante2 = request.getParameter("FT_NM_Ajudante2");
      String NM_Ajudante3 = request.getParameter("FT_NM_Ajudante3");
      String TX_Observacao = request.getParameter("FT_TX_Observacao");
      String HR_Saida = request.getParameter("FT_HR_Saida");
      String HR_Chegada = request.getParameter("FT_HR_Chegada");
      String VL_Frete_Carreteiro = request.getParameter("FT_VL_Frete_Carreteiro");
      String PE_Custo_Entrega = request.getParameter("FT_PE_Custo_Entrega");
      String cd_Rota_Entrega = request.getParameter("FT_CD_Rota_Entrega");
      String oid_cia_aerea = request.getParameter("oid_cia_aerea");
      String cd_Cia_Aerea = request.getParameter("FT_CD_Cia_Aerea");
      String oid_tipo_servico_aereo= request.getParameter("oid_tipo_servico_aereo");
      String cd_Tipo_Servico_Aereo = request.getParameter("FT_CD_Tipo_Servico_Aereo");
      String nm_Tipo_Servico_Aereo = request.getParameter("FT_NM_Tipo_Servico_Aereo");

      ManifestoED ed = new ManifestoED();

      ed.setOID_Manifesto(request.getParameter("oid_Manifesto"));
      ed.setOID_Pessoa(request.getParameter("oid_Pessoa"));
      ed.setOID_Veiculo(request.getParameter("oid_Veiculo"));
      ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));
      ed.setDM_Expresso(request.getParameter("FT_DM_Expresso"));
      ed.setDT_Previsao_Chegada(request.getParameter("FT_DT_Previsao_Chegada"));
      ed.setHR_Previsao_Chegada(request.getParameter("FT_HR_Previsao_Chegada"));

      // System.out.println("man 1->" +NR_Odometro_Inicial);

      if (String.valueOf(NR_Odometro_Inicial) != null &&
          !String.valueOf(NR_Odometro_Inicial).equals("null") &&
          !String.valueOf(NR_Odometro_Inicial).equals("")){
        ed.setNR_Odometro_Inicial(new Long(NR_Odometro_Inicial).longValue());
      }

      if (String.valueOf(NR_Odometro_Final) != null &&
          !String.valueOf(NR_Odometro_Final).equals("null") &&
          !String.valueOf(NR_Odometro_Final).equals("")){
        ed.setNR_Odometro_Final(new Long(NR_Odometro_Final).longValue());
      }

      // System.out.println("man 2->" +NR_Odometro_Final);

      if (String.valueOf(NM_Liberacao_Seguradora) != null &&
          !String.valueOf(NM_Liberacao_Seguradora).equals("")){
        ed.setNM_Liberacao_Seguradora(NM_Liberacao_Seguradora);
      }
      if (String.valueOf(CD_Roteiro) != null &&
          !String.valueOf(CD_Roteiro).equals("")&&
          !String.valueOf(CD_Roteiro).equals("null")){
         ed.setCD_Roteiro(CD_Roteiro);
      }

      if (JavaUtil.doValida(cd_Rota_Entrega)){
    	  ed.setCD_Rota_Entrega(cd_Rota_Entrega);
      }


      ed.setOID_Veiculo_Carreta("");
      if (String.valueOf(oid_Veiculo_Carreta) != null &&
          !String.valueOf(oid_Veiculo_Carreta).equals("null") &&
          !String.valueOf(oid_Veiculo_Carreta).equals("")){
          ed.setOID_Veiculo_Carreta(request.getParameter("oid_Veiculo_Carreta"));
      }

          //// System.out.println("man 7" );

      ed.setOID_Ordem_Frete("");
      String oid_Ordem_Frete = request.getParameter("oid_Ordem_Frete");
      if (String.valueOf(oid_Ordem_Frete) != null &&
          !String.valueOf(oid_Ordem_Frete).equals("null") &&
          !String.valueOf(oid_Ordem_Frete).equals("")){
          ed.setOID_Ordem_Frete(request.getParameter("oid_Ordem_Frete"));
      }

      String oid_Gaiola = request.getParameter("oid_Gaiola");
      if (String.valueOf(oid_Gaiola) != null &&
              !String.valueOf(oid_Gaiola).equals("null") &&
              !String.valueOf(oid_Gaiola).equals("")){
          ed.setOID_Gaiola(new Long(request.getParameter("oid_Gaiola")).longValue());
      }

      ed.setOID_Subregiao(0);
      if (String.valueOf(oid_Subregiao) != null &&
          !String.valueOf(oid_Subregiao).equals("null") &&
          !String.valueOf(oid_Subregiao).equals("")){
          ed.setOID_Subregiao(new Long(request.getParameter("oid_Subregiao")).longValue());
      }

      ed.setNR_KM_Viagem(0);
      String NR_KM_Viagem = request.getParameter("FT_NR_KM_Viagem");
      if (String.valueOf(NR_KM_Viagem) != null &&
          !String.valueOf(NR_KM_Viagem).equals("null") &&
          !String.valueOf(NR_KM_Viagem).equals("")){
           long NR_KM_Via = new Double(NR_KM_Viagem).longValue();
          ed.setNR_KM_Viagem(NR_KM_Via);
          // System.out.println("man 9--->>>"  + NR_KM_Via);
      }


      ed.setOID_Pessoa_Unidade_Destino("");
      if (String.valueOf(oid_Pessoa_Unidade_Destino) != null &&
          !String.valueOf(oid_Pessoa_Unidade_Destino).equals("null") &&
          !String.valueOf(oid_Pessoa_Unidade_Destino).equals("")){
          ed.setOID_Pessoa_Unidade_Destino(request.getParameter("oid_Pessoa_Unidade_Destino"));
      }
          //// System.out.println("man 10" );

      ed.setOID_Pessoa_Reveza("");
      if (String.valueOf(oid_Pessoa_Reveza) != null &&
          !String.valueOf(oid_Pessoa_Reveza).equals("null") &&
          !String.valueOf(oid_Pessoa_Reveza).equals("")){
          ed.setOID_Pessoa_Reveza(request.getParameter("oid_Pessoa_Reveza"));
      }
          //// System.out.println("man 10" );

      ed.setDT_Troca_Motorista("");
      if (String.valueOf(DT_Troca_Motorista) != null &&
          !String.valueOf(DT_Troca_Motorista).equals("null") &&
          !String.valueOf(DT_Troca_Motorista).equals("")){
          ed.setDT_Troca_Motorista(request.getParameter("DT_Troca_Motorista"));
      }
          //// System.out.println("man 10" );


      ed.setOID_Unidade_Destino(0);
      if (String.valueOf(oid_Unidade_Destino) != null &&
          !String.valueOf(oid_Unidade_Destino).equals("null") &&
          !String.valueOf(oid_Unidade_Destino).equals("")){
          ed.setOID_Unidade_Destino(new Long(request.getParameter("oid_Unidade_Destino")).longValue());
      }

      String oid_Cidade_Origem = request.getParameter("oid_Cidade_Origem");
      if (String.valueOf(oid_Cidade_Origem) != null &&
          !String.valueOf(oid_Cidade_Origem).equals("null") &&
          !String.valueOf(oid_Cidade_Origem).equals("")){
          ed.setOID_Cidade_Origem(new Long(request.getParameter("oid_Cidade_Origem")).longValue());
      }
      // System.out.println("man 11" );

      String NR_Ajudante = request.getParameter("FT_NR_Ajudante");
      if (String.valueOf(NR_Ajudante) != null &&
          !String.valueOf(NR_Ajudante).equals("null") &&
          !String.valueOf(NR_Ajudante).equals("")){
          ed.setNR_Ajudante(new Integer(NR_Ajudante).intValue());
      }

      String NR_KIT = request.getParameter("FT_NR_KIT");
      if (String.valueOf(NR_KIT) != null &&
          !String.valueOf(NR_KIT).equals("null") &&
          !String.valueOf(NR_KIT).equals("")){
          ed.setNR_KIT(new Integer(NR_KIT).intValue());
      }

      // System.out.println("man 31" );

      ed.setNM_Liberacao_Seguradora(request.getParameter("FT_NM_Liberacao_Seguradora"));

      if (String.valueOf(NR_Odometro_Troca_Motorista) != null &&
          !String.valueOf(NR_Odometro_Troca_Motorista).equals("") &&
          !String.valueOf(NR_Odometro_Troca_Motorista).equals("null")){
        ed.setNR_Odometro_Troca_Motorista(new Long(NR_Odometro_Troca_Motorista).longValue());
      }

      ed.setDM_Lancado_Ordem_Frete("VERIFICAR");
      if (String.valueOf(DM_Lancado_Ordem_Frete) != null &&
          !String.valueOf(DM_Lancado_Ordem_Frete).equals("null") &&
          !String.valueOf(DM_Lancado_Ordem_Frete).equals("")){
          ed.setDM_Lancado_Ordem_Frete(request.getParameter("FT_DM_Lancado_Ordem_Frete"));
      }
      ed.setNM_Ajudante1("");
      if (String.valueOf(NM_Ajudante1) != null &&
          !String.valueOf(NM_Ajudante1).equals("null") &&
          !String.valueOf(NM_Ajudante1).equals("")){
          ed.setNM_Ajudante1(request.getParameter("FT_NM_Ajudante1"));
      }
      ed.setNM_Ajudante2("");
      if (String.valueOf(NM_Ajudante2) != null &&
          !String.valueOf(NM_Ajudante2).equals("null") &&
          !String.valueOf(NM_Ajudante2).equals("")){
          ed.setNM_Ajudante2(request.getParameter("FT_NM_Ajudante2"));
      }
      ed.setNM_Ajudante3("");
      if (String.valueOf(NM_Ajudante3) != null &&
          !String.valueOf(NM_Ajudante3).equals("null") &&
          !String.valueOf(NM_Ajudante3).equals("")){
          ed.setNM_Ajudante3(request.getParameter("FT_NM_Ajudante3"));
      }
      ed.setTX_Observacao("");
      if (String.valueOf(TX_Observacao) != null &&
          !String.valueOf(TX_Observacao).equals("null") &&
          !String.valueOf(TX_Observacao).equals("")){
          ed.setTX_Observacao(request.getParameter("FT_TX_Observacao"));
      }
      ed.setHR_Saida("");
      if (String.valueOf(HR_Saida) != null &&
          !String.valueOf(HR_Saida).equals("null") &&
          !String.valueOf(HR_Saida).equals("")){
          ed.setHR_Saida(request.getParameter("FT_HR_Saida"));
      }
      ed.setHR_Chegada("");
      if (String.valueOf(HR_Chegada) != null &&
          !String.valueOf(HR_Chegada).equals("null") &&
          !String.valueOf(HR_Chegada).equals("")){
          ed.setHR_Chegada(request.getParameter("FT_HR_Chegada"));
      }

      if (JavaUtil.doValida(VL_Frete_Carreteiro)) {
      	ed.setVL_Frete_Carreteiro(Double.parseDouble(VL_Frete_Carreteiro));
      } else {
      	ed.setVL_Frete_Carreteiro(0);
      }
      // System.out.println("PE_Custo_Entrega: " + PE_Custo_Entrega);
      if (JavaUtil.doValida(PE_Custo_Entrega)) {
          ed.setPE_Custo_Entrega(Double.parseDouble(PE_Custo_Entrega));
      }

      String DM_Manifesto_Romaneio = request.getParameter("FT_DM_Manifesto_Romaneio");
      if (String.valueOf(DM_Manifesto_Romaneio) != null &&
                  !String.valueOf(DM_Manifesto_Romaneio).equals("null") &&
                  !String.valueOf(DM_Manifesto_Romaneio).equals("")){
            ed.setDM_Manifesto_Romaneio(request.getParameter("FT_DM_Manifesto_Romaneio"));
      }

      // tipo servico aereo
      if (String.valueOf(oid_tipo_servico_aereo) != null &&
              !String.valueOf(oid_tipo_servico_aereo).equals("null") &&
              !String.valueOf(oid_tipo_servico_aereo).equals("")){
            ed.setOID_Tipo_Servico_Aereo(new Long(oid_tipo_servico_aereo).longValue());
          }

      if (String.valueOf(cd_Tipo_Servico_Aereo) != null &&
              !String.valueOf(cd_Tipo_Servico_Aereo).equals("")&&
              !String.valueOf(cd_Tipo_Servico_Aereo).equals("null")){
             ed.setCD_Tipo_Servico_Aereo(cd_Tipo_Servico_Aereo);
          }

      if (String.valueOf(nm_Tipo_Servico_Aereo) != null &&
              !String.valueOf(nm_Tipo_Servico_Aereo).equals("")&&
              !String.valueOf(nm_Tipo_Servico_Aereo).equals("null")){
             ed.setNM_Tipo_Servico_Aereo(nm_Tipo_Servico_Aereo);
          }
      //////////////////////////////////////////////

      // companhia aerea
      if (String.valueOf(oid_cia_aerea) != null &&
              !String.valueOf(oid_cia_aerea).equals("null") &&
              !String.valueOf(oid_cia_aerea).equals("")){
            ed.setOID_Cia_Aerea(new Long(oid_cia_aerea).longValue());
          }

      if (String.valueOf(cd_Cia_Aerea) != null &&
              !String.valueOf(cd_Cia_Aerea).equals("")&&
              !String.valueOf(cd_Cia_Aerea).equals("null")){
             ed.setCD_Cia_Aerea(cd_Cia_Aerea);
          }
      ////////////////////////////////

      // System.out.println("man b 9999999999" );

      new ManifestoRN().altera(ed);
  }

  public void finaliza_manifesto(HttpServletRequest request)throws Excecoes{
	  String NR_Odometro_Inicial = request.getParameter("FT_NR_Odometro_Inicial");
      String NR_Odometro_Final = request.getParameter("FT_NR_Odometro_Final");
      String HR_Saida = request.getParameter("FT_HR_Saida");
      String HR_Chegada = request.getParameter("FT_HR_Chegada");
      String DT_Chegada = request.getParameter("FT_DT_Chegada");
      String OID_Veiculo = request.getParameter("oid_Veiculo");


      ManifestoED ed = new ManifestoED();
	  ed.setOID_Manifesto(request.getParameter("oid_Manifesto"));

      if (String.valueOf(OID_Veiculo) != null &&
              !String.valueOf(OID_Veiculo).equals("null") &&
              !String.valueOf(OID_Veiculo).equals("")){
    	  ed.setOID_Veiculo(OID_Veiculo);
          }

      if (String.valueOf(NR_Odometro_Inicial) != null &&
          !String.valueOf(NR_Odometro_Inicial).equals("null") &&
          !String.valueOf(NR_Odometro_Inicial).equals("")){
        ed.setNR_Odometro_Inicial(new Long(NR_Odometro_Inicial).longValue());
      }

      if (String.valueOf(NR_Odometro_Final) != null &&
          !String.valueOf(NR_Odometro_Final).equals("null") &&
          !String.valueOf(NR_Odometro_Final).equals("")){
        ed.setNR_Odometro_Final(new Long(NR_Odometro_Final).longValue());
      }

      ed.setHR_Saida("");
      if (String.valueOf(HR_Saida) != null &&
          !String.valueOf(HR_Saida).equals("null") &&
          !String.valueOf(HR_Saida).equals("")){
          ed.setHR_Saida(HR_Saida);
      }
      ed.setHR_Chegada("");
      if (String.valueOf(HR_Chegada) != null &&
          !String.valueOf(HR_Chegada).equals("null") &&
          !String.valueOf(HR_Chegada).equals("")){
          ed.setHR_Chegada(HR_Chegada);
      }
      ed.setDT_Chegada("");
      if (String.valueOf(DT_Chegada) != null &&
          !String.valueOf(DT_Chegada).equals("null") &&
          !String.valueOf(DT_Chegada).equals("")){
          ed.setDT_Chegada(DT_Chegada);
      }

      new ManifestoRN().finaliza_manifesto(ed);
  }

  public void inclui_Ocorrencia(HttpServletRequest request)throws Excecoes{

    try{
      ManifestoRN Manifestorn = new ManifestoRN();
      ManifestoED ed = new ManifestoED();

       //// System.out.println("1");

       ed.setOID_Manifesto(request.getParameter("oid_Manifesto"));
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

       ed.setTX_Observacao("");
       String TX_Observacao = request.getParameter("FT_TX_Observacao");
        if (String.valueOf(TX_Observacao) != null &&
            !String.valueOf(TX_Observacao).equals("null") &&
            !String.valueOf(TX_Observacao).equals("")){
            ed.setTX_Observacao(request.getParameter("FT_TX_Observacao"));
        }
        ed.setDM_Tipo(request.getParameter("FT_DM_Tipo"));


       //// System.out.println("3");


      Manifestorn.inclui_Ocorrencia(ed);
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
      ManifestoRN Manifestorn = new ManifestoRN();
      ManifestoED ed = new ManifestoED();

      ed.setDM_Lancado_Ordem_Frete("VERIFICAR");
      String DM_Lancado_Ordem_Frete = request.getParameter("FT_DM_Lancado_Ordem_Frete");
      if (String.valueOf(DM_Lancado_Ordem_Frete) != null &&
          !String.valueOf(DM_Lancado_Ordem_Frete).equals("null") &&
          !String.valueOf(DM_Lancado_Ordem_Frete).equals("")){
          ed.setDM_Lancado_Ordem_Frete(request.getParameter("FT_DM_Lancado_Ordem_Frete"));
      }
          //// System.out.println("man 10" );

      ed.setOID_Manifesto(request.getParameter("oid_Manifesto"));

      Manifestorn.deleta(ed);
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

  public ManifestoED geraManifesto(HttpServletRequest request)throws Excecoes{

	    try{
	      ManifestoRN Manifestorn = new ManifestoRN();
	      ManifestoED ed = new ManifestoED();

	      ed.setOID_Manifesto(request.getParameter("oid_Manifesto"));

          return new ManifestoRN().geraManifesto(ed);
	    }
	    catch (Excecoes exc){
	      throw exc;
	    }
	    catch(Exception exc){
	      Excecoes excecoes = new Excecoes();
	      excecoes.setClasse(this.getClass().getName());
	      excecoes.setMensagem("erro ao Gerar Manifesto");
	      excecoes.setMetodo("geraManifesto");
	      excecoes.setExc(exc);
	      throw excecoes;
	    }
  }

  public ArrayList Manifesto_Lista(HttpServletRequest request)throws Excecoes{

      ManifestoED ed = new ManifestoED();
//// System.out.println("1 ");

      String DT_Emissao = request.getParameter ("FT_DT_Emissao");
      if (String.valueOf (DT_Emissao) != null &&
          !String.valueOf (DT_Emissao).equals ("null") &&
          !String.valueOf (DT_Emissao).equals ("")) {
        ed.setDT_Emissao (request.getParameter ("FT_DT_Emissao"));
      }

      String DT_Emissao_Inicial = request.getParameter ("FT_DT_Emissao_Inicial");

      // System.out.println("DT_Emissao_Inicial=========== " + DT_Emissao_Inicial);

      if (util.doValida (DT_Emissao_Inicial)) {
        ed.setDT_Emissao_Inicial (DT_Emissao_Inicial);
      }

      String DT_Emissao_Final = request.getParameter ("FT_DT_Emissao_Final");
      if (util.doValida (DT_Emissao_Final)) {
        ed.setDT_Emissao_Final (DT_Emissao_Final);
      }

      ed.setDM_Manifesto_Romaneio("");
      String DM_Manifesto_Romaneio = request.getParameter("FT_DM_Manifesto_Romaneio");
      if (String.valueOf(DM_Manifesto_Romaneio) != null &&
                !String.valueOf(DM_Manifesto_Romaneio).equals("null") &&
                !String.valueOf(DM_Manifesto_Romaneio).equals("")){
            ed.setDM_Manifesto_Romaneio(request.getParameter("FT_DM_Manifesto_Romaneio"));
      }

      String nr_Manifesto = request.getParameter("FT_NR_Manifesto");
      if (String.valueOf(nr_Manifesto) != null &&
        !String.valueOf(nr_Manifesto).equals("") &&
        !String.valueOf(nr_Manifesto).equals("null")){
        ed.setNR_Manifesto(new Long(nr_Manifesto).longValue());
      }

      ed.setOID_Pessoa_Entregadora("");
      String oid_Pessoa_Entregadora = request.getParameter("oid_Pessoa_Entregadora");
      if (String.valueOf(oid_Pessoa_Entregadora) != null &&
          !String.valueOf(oid_Pessoa_Entregadora).equals("null") &&
          !String.valueOf(oid_Pessoa_Entregadora).equals("")){
          ed.setOID_Pessoa_Entregadora(request.getParameter("oid_Pessoa_Entregadora"));
      }


       String OID_Pessoa = request.getParameter("oid_Pessoa");
        if (String.valueOf(OID_Pessoa) != null &&
            !String.valueOf(OID_Pessoa).equals("null") &&
            !String.valueOf(OID_Pessoa).equals("")){
            ed.setOID_Pessoa(request.getParameter("oid_Pessoa"));
        }
        String OID_Veiculo = request.getParameter("oid_Veiculo");
        if (String.valueOf(OID_Veiculo) != null &&
            !String.valueOf(OID_Veiculo).equals("null") &&
            !String.valueOf(OID_Veiculo).equals("")){
            ed.setOID_Veiculo(request.getParameter("oid_Veiculo"));
        }

        String OID_Veiculo_Carreta = request.getParameter("FT_NR_Placa_Carreta");
        if (String.valueOf(OID_Veiculo_Carreta) != null &&
            !String.valueOf(OID_Veiculo_Carreta).equals("null") &&
            !String.valueOf(OID_Veiculo_Carreta).equals("")){
            ed.setOID_Veiculo_Carreta(OID_Veiculo_Carreta);
        }


      String oid_Unidade = request.getParameter("oid_Unidade");
      if (String.valueOf(oid_Unidade) != null &&
        !String.valueOf(oid_Unidade).equals("") &&
        !String.valueOf(oid_Unidade).equals("null")){
        ed.setOID_Unidade(new Long(oid_Unidade).longValue());
      }

      String oid_tipo_servico_aereo = request.getParameter("oid_tipo_servico_aereo");
      if (String.valueOf(oid_tipo_servico_aereo) != null &&
        !String.valueOf(oid_tipo_servico_aereo).equals("") &&
        !String.valueOf(oid_tipo_servico_aereo).equals("null")){
        ed.setOID_Tipo_Servico_Aereo(new Long(oid_tipo_servico_aereo).longValue());
      }

      String oid_cia_aerea = request.getParameter("oid_cia_aerea");
      if (String.valueOf(oid_cia_aerea) != null &&
        !String.valueOf(oid_cia_aerea).equals("") &&
        !String.valueOf(oid_cia_aerea).equals("null")){
        ed.setOID_Cia_Aerea(new Long(oid_cia_aerea).longValue());
      }

      return new ManifestoRN().lista(ed);

  }

  public ArrayList Manifesto_Lista_Acerto(HttpServletRequest request)throws Excecoes{

      ManifestoED ed = new ManifestoED();

      String oid_Acerto = request.getParameter("oid_Acerto");
      if (String.valueOf(oid_Acerto) != null &&
        !String.valueOf(oid_Acerto).equals("") &&
        !String.valueOf(oid_Acerto).equals("null")){
        ed.setOID_Acerto(new Long(oid_Acerto).longValue());
      }

      return new ManifestoRN().Manifesto_Lista_Acerto(ed);

  }



  public ManifestoED inclui(HttpServletRequest request)
  throws Excecoes{
          ManifestoED ed = new ManifestoED();


          ed.setUser(UsuarioBean.getUsuarioCorrente(request).getOid_Usuario().intValue());
          ed.setDM_Lancado_Ordem_Frete("N");
          // System.out.println("man 0" );

          ed.setOID_Seguradora(1);
          String oid_Seguradora = request.getParameter("oid_Seguradora");
          if (String.valueOf(oid_Seguradora) != null &&
                  !String.valueOf(oid_Seguradora).equals("null") &&
                  !String.valueOf(oid_Seguradora).equals("")){
              ed.setOID_Seguradora(new Long(request.getParameter("oid_Seguradora")).longValue());
          }

          String oid_Gaiola = request.getParameter("oid_Gaiola");
          if (String.valueOf(oid_Gaiola) != null &&
                  !String.valueOf(oid_Gaiola).equals("null") &&
                  !String.valueOf(oid_Gaiola).equals("")){
              ed.setOID_Gaiola(new Long(request.getParameter("oid_Gaiola")).longValue());
          }

          ed.setOID_Pessoa(request.getParameter("oid_Pessoa"));

          // System.out.println("man 0" );

          ed.setOID_Veiculo(request.getParameter("oid_Veiculo"));
          ed.setOID_Cidade(new Long(request.getParameter("oid_Cidade")).longValue());
          ed.setOID_Cidade_Origem(new Long(request.getParameter("oid_Cidade_Origem")).longValue());

          // System.out.println("man 1 n2" );

          ed.setOID_Unidade(new Long(request.getParameter("oid_Unidade")).longValue());



          ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));
          ed.setDT_Previsao_Chegada(request.getParameter("FT_DT_Emissao"));

          String DT_Previsao_Chegada = request.getParameter("FT_DT_Previsao_Chegada");
          if (String.valueOf(DT_Previsao_Chegada) != null &&
                  !String.valueOf(DT_Previsao_Chegada).equals("null") &&
                  !String.valueOf(DT_Previsao_Chegada).equals(" ") &&
                  !String.valueOf(DT_Previsao_Chegada).equals("")){
              ed.setDT_Previsao_Chegada(DT_Previsao_Chegada);
              // System.out.println("man 3 n" );
          }
          ed.setHR_Previsao_Chegada(request.getParameter("FT_HR_Previsao_Chegada"));

          // System.out.println("man 3 ax" +ed.getDT_Previsao_Chegada());

          String NR_Odometro_Inicial = request.getParameter("FT_NR_Odometro_Inicial");
          if (String.valueOf(NR_Odometro_Inicial) != null &&
                  !String.valueOf(NR_Odometro_Inicial).equals("null") &&
                  !String.valueOf(NR_Odometro_Inicial).equals("")){
              ed.setNR_Odometro_Inicial(new Long(NR_Odometro_Inicial).longValue());
          }

          // System.out.println("man 4" );

          String NM_Liberacao_Seguradora = request.getParameter("FT_NM_Liberacao_Seguradora");
          if (String.valueOf(NM_Liberacao_Seguradora) != null &&
                  !String.valueOf(NM_Liberacao_Seguradora).equals("")){
              ed.setNM_Liberacao_Seguradora(NM_Liberacao_Seguradora);
          }

          // System.out.println("man 5" );

          String CD_Roteiro = request.getParameter("FT_CD_Roteiro");
          if (String.valueOf(CD_Roteiro) != null &&
                  !String.valueOf(CD_Roteiro).equals("")&&
                  !String.valueOf(CD_Roteiro).equals("null")){
              ed.setCD_Roteiro(CD_Roteiro);
          }

          String cd_Rota_Entrega = request.getParameter("FT_CD_Rota_Entrega");
          if (JavaUtil.doValida(cd_Rota_Entrega)){
        	  ed.setCD_Rota_Entrega(cd_Rota_Entrega);
          }

          // System.out.println("man 6" );

          ed.setOID_Veiculo_Carreta("");
          String oid_Veiculo_Carreta = request.getParameter("oid_Veiculo_Carreta");
          if (String.valueOf(oid_Veiculo_Carreta) != null &&
                  !String.valueOf(oid_Veiculo_Carreta).equals("null") &&
                  !String.valueOf(oid_Veiculo_Carreta).equals("")){
              ed.setOID_Veiculo_Carreta(request.getParameter("oid_Veiculo_Carreta"));
          }

          ed.setOID_Ordem_Frete("");
          String oid_Ordem_Frete = request.getParameter("oid_Ordem_Frete");
          if (String.valueOf(oid_Ordem_Frete) != null &&
                  !String.valueOf(oid_Ordem_Frete).equals("null") &&
                  !String.valueOf(oid_Ordem_Frete).equals("")){
              ed.setOID_Ordem_Frete(request.getParameter("oid_Ordem_Frete"));
          }

          ed.setOID_Subregiao(0);
          String oid_Subregiao = request.getParameter("oid_Subregiao");
          if (String.valueOf(oid_Subregiao) != null &&
                  !String.valueOf(oid_Subregiao).equals("null") &&
                  !String.valueOf(oid_Subregiao).equals("")){
              ed.setOID_Subregiao(new Long(request.getParameter("oid_Subregiao")).longValue());
          }

          ed.setOID_Pessoa_Unidade_Destino("");
          String oid_Pessoa_Unidade_Destino = request.getParameter("oid_Pessoa_Unidade_Destino");
          if (String.valueOf(oid_Pessoa_Unidade_Destino) != null &&
                  !String.valueOf(oid_Pessoa_Unidade_Destino).equals("null") &&
                  !String.valueOf(oid_Pessoa_Unidade_Destino).equals("")){
              ed.setOID_Pessoa_Unidade_Destino(request.getParameter("oid_Pessoa_Unidade_Destino"));
          }

          ed.setOID_Pessoa_Entregadora("");
          String oid_Pessoa_Entregadora = request.getParameter("oid_Pessoa_Entregadora");
          if (String.valueOf(oid_Pessoa_Entregadora) != null &&
                  !String.valueOf(oid_Pessoa_Entregadora).equals("null") &&
                  !String.valueOf(oid_Pessoa_Entregadora).equals("")){
              ed.setOID_Pessoa_Entregadora(request.getParameter("oid_Pessoa_Entregadora"));
          }

          ed.setOID_Unidade_Destino(0);
          String oid_Unidade_Destino = request.getParameter("oid_Unidade_Destino");
          if (String.valueOf(oid_Unidade_Destino) != null &&
                  !String.valueOf(oid_Unidade_Destino).equals("null") &&
                  !String.valueOf(oid_Unidade_Destino).equals("")){
              ed.setOID_Unidade_Destino(new Long(request.getParameter("oid_Unidade_Destino")).longValue());
          }


          ed.setNM_Ajudante1(" ");
          String NM_Ajudante1 = request.getParameter("FT_NM_Ajudante1");
          if (String.valueOf(NM_Ajudante1) != null &&
                  !String.valueOf(NM_Ajudante1).equals("null") &&
                  !String.valueOf(NM_Ajudante1).equals("")){
              ed.setNM_Ajudante1(request.getParameter("FT_NM_Ajudante1"));
          }
          ed.setNM_Ajudante2(" ");
          String NM_Ajudante2 = request.getParameter("FT_NM_Ajudante2");
          if (String.valueOf(NM_Ajudante2) != null &&
                  !String.valueOf(NM_Ajudante2).equals("null") &&
                  !String.valueOf(NM_Ajudante2).equals("")){
              ed.setNM_Ajudante2(request.getParameter("FT_NM_Ajudante2"));
          }
          ed.setNM_Ajudante3(" ");
          String NM_Ajudante3 = request.getParameter("FT_NM_Ajudante3");
          if (String.valueOf(NM_Ajudante3) != null &&
                  !String.valueOf(NM_Ajudante3).equals("null") &&
                  !String.valueOf(NM_Ajudante3).equals("")){
              ed.setNM_Ajudante3(request.getParameter("FT_NM_Ajudante3"));
          }
          ed.setTX_Observacao(" ");
          String TX_Observacao = request.getParameter("FT_TX_Observacao");
          if (String.valueOf(TX_Observacao) != null &&
                  !String.valueOf(TX_Observacao).equals("null") &&
                  !String.valueOf(TX_Observacao).equals("")){
              ed.setTX_Observacao(request.getParameter("FT_TX_Observacao"));
          }
          ed.setHR_Saida(Data.getHoraHM());
          String HR_Saida = request.getParameter("FT_HR_Saida");
          if (String.valueOf(HR_Saida) != null &&
                  !String.valueOf(HR_Saida).equals("null") &&
                  !String.valueOf(HR_Saida).equals("")){
              ed.setHR_Saida(request.getParameter("FT_HR_Saida"));
          }


          ed.setDM_Expresso("N");
          String DM_Expresso = request.getParameter("FT_DM_Expresso");
          if (String.valueOf(DM_Expresso) != null &&
                  !String.valueOf(DM_Expresso).equals("null") &&
                  !String.valueOf(DM_Expresso).equals("")){
              ed.setDM_Expresso(request.getParameter("FT_DM_Expresso"));
          }

          ed.setDM_Manifesto_Romaneio("M");
          String DM_Manifesto_Romaneio = request.getParameter("FT_DM_Manifesto_Romaneio");
          if (String.valueOf(DM_Manifesto_Romaneio) != null &&
                  !String.valueOf(DM_Manifesto_Romaneio).equals("null") &&
                  !String.valueOf(DM_Manifesto_Romaneio).equals("")){
              ed.setDM_Manifesto_Romaneio(request.getParameter("FT_DM_Manifesto_Romaneio"));
          }


          String VL_Frete_Carreteiro = request.getParameter("FT_VL_Frete_Carreteiro");
          if (JavaUtil.doValida(VL_Frete_Carreteiro)) {
              ed.setVL_Frete_Carreteiro(Double.parseDouble(VL_Frete_Carreteiro));
          } else {
              ed.setVL_Frete_Carreteiro(0);
          }
          // System.out.println("man 13" );

          String PE_Custo_Entrega = request.getParameter("FT_PE_Custo_Entrega");
          if (JavaUtil.doValida(PE_Custo_Entrega)) {
              ed.setPE_Custo_Entrega(Double.parseDouble(PE_Custo_Entrega));
          } else {
              ed.setPE_Custo_Entrega(0);
          }
          // System.out.println("man 14" );


          String NR_Ajudante = request.getParameter ("FT_NR_Ajudante");
          if (String.valueOf (NR_Ajudante) != null &&
              !String.valueOf (NR_Ajudante).equals ("null") &&
              !String.valueOf (NR_Ajudante).equals ("")) {
            ed.setNR_Ajudante (new Integer (NR_Ajudante).intValue ());
          }

          String NR_KIT = request.getParameter ("FT_NR_KIT");
          if (String.valueOf (NR_KIT) != null &&
              !String.valueOf (NR_KIT).equals ("null") &&
              !String.valueOf (NR_KIT).equals ("")) {
            ed.setNR_KIT (new Integer (NR_KIT).intValue ());
          }

          ed.setNR_KM_Viagem (0);
          String NR_KM_Viagem = request.getParameter ("FT_NR_KM_Viagem");
          if (String.valueOf (NR_KM_Viagem) != null &&
              !String.valueOf (NR_KM_Viagem).equals ("null") &&
              !String.valueOf (NR_KM_Viagem).equals ("")) {
            long NR_KM_Via = new Double (NR_KM_Viagem).longValue ();
            ed.setNR_KM_Viagem (NR_KM_Via);
            // System.out.println ("man 9--->>>" + NR_KM_Via);
          }

//          String cd_Cia_Aerea = request.getParameter("FT_CD_Cia_Aerea");
//          if (String.valueOf(cd_Cia_Aerea) != null &&
//                  !String.valueOf(cd_Cia_Aerea).equals("")&&
//                  !String.valueOf(cd_Cia_Aerea).equals("null")){
//              ed.setCD_Cia_Aerea(cd_Cia_Aerea);
//          }

          ed.setOID_Cia_Aerea(0);
          String oid_cia_aerea = request.getParameter("oid_cia_aerea");
          if (String.valueOf(oid_cia_aerea) != null &&
                  !String.valueOf(oid_cia_aerea).equals("null") &&
                  !String.valueOf(oid_cia_aerea).equals("")){
              ed.setOID_Cia_Aerea(new Long(request.getParameter("oid_cia_aerea")).longValue());
          }

          ed.setOID_Tipo_Servico_Aereo(0);
          String oid_tipo_servico_aereo = request.getParameter("oid_tipo_servico_aereo");
          if (String.valueOf(oid_tipo_servico_aereo) != null &&
                  !String.valueOf(oid_tipo_servico_aereo).equals("null") &&
                  !String.valueOf(oid_tipo_servico_aereo).equals("")){
              ed.setOID_Tipo_Servico_Aereo(new Long(request.getParameter("oid_tipo_servico_aereo")).longValue());
          }

//          String cd_Tipo_Servico_Aereo = request.getParameter("FT_CD_Tipo_Servico_Aereo");
//          if (String.valueOf(cd_Tipo_Servico_Aereo) != null &&
//                  !String.valueOf(cd_Tipo_Servico_Aereo).equals("")&&
//                  !String.valueOf(cd_Tipo_Servico_Aereo).equals("null")){
//              ed.setCD_Tipo_Servico_Aereo(cd_Tipo_Servico_Aereo);
//          }

          return new ManifestoRN().inclui(ed);
  }

  public ManifestoED getByRecord(HttpServletRequest request)throws Excecoes{

      ManifestoED ed = new ManifestoED();

      String NR_Manifesto = request.getParameter("FT_NR_Manifesto");
      if (String.valueOf(NR_Manifesto) != null &&
          !String.valueOf(NR_Manifesto).equals("") &&
          !String.valueOf(NR_Manifesto).equals("null")){
        ed.setNR_Manifesto(new Long(request.getParameter("FT_NR_Manifesto")).longValue());
      }

      String oid_Unidade = request.getParameter("oid_Unidade");
      if (String.valueOf(oid_Unidade) != null &&
          !String.valueOf(oid_Unidade).equals("") &&
          !String.valueOf(oid_Unidade).equals("null")){
         ed.setOID_Unidade(new Long(request.getParameter("oid_Unidade")).longValue());
      }

      String oid_Manifesto = request.getParameter("oid_Manifesto");

      if (String.valueOf(oid_Manifesto) != null &&
          !String.valueOf(oid_Manifesto).equals("") &&
          !String.valueOf(oid_Manifesto).equals("null")){
        ed.setOID_Manifesto(request.getParameter("oid_Manifesto"));
      }

      if (request.getParameter("MIC") != null && request.getParameter("MIC").equals("S")){
            return new ManifestoRN().getByRecord(ed);
      } else{
		    return new ManifestoRN().getByRecord(ed);
      }

  }

  public ManifestoED getByRecord(String oid_Manifesto)throws Excecoes{

      ManifestoED ed = new ManifestoED();

      if (JavaUtil.doValida(oid_Manifesto)) {
          ed.setOID_Manifesto(oid_Manifesto);
      } else throw new Mensagens("Informe o manifesto!");

      return new ManifestoRN().getByRecord(ed);

  }

  public byte[] imprime_Manifesto(HttpServletRequest request, HttpServletResponse Response)
  throws Excecoes {
      String oid_Manifesto = request.getParameter("oid_Manifesto");
      ManifestoED filtro = new ManifestoED();
      filtro.setUser(UsuarioBean.getUsuarioCorrente(request).getOid_Usuario().intValue());
      if (JavaUtil.doValida(oid_Manifesto)) {
          filtro.setOID_Manifesto(oid_Manifesto);
      } else throw new Mensagens("Informe o manifesto!");
      return new ManifestoRN().imprime_Manifesto(filtro);
  }

  public byte[] imprime_Minuta_Embarque(HttpServletRequest request, HttpServletResponse Response)
  throws Excecoes {
      String oid_Manifesto = request.getParameter("oid_Manifesto");
      ManifestoED filtro = new ManifestoED();
      if (JavaUtil.doValida(oid_Manifesto)) {
        filtro.setOID_Manifesto (oid_Manifesto);
        filtro.setDM_Relatorio ("ME");
      } else throw new Mensagens("Informe o manifesto!");
      return new ManifestoRN().imprime_Manifesto(filtro);
  }


  public  byte[] imprime_Manifesto(HttpServletRequest request, HttpServletResponse Response, String DM_Tipo_Manifesto)
  throws Excecoes{

// System.out.println("a 0");

    String oid_Manifesto = request.getParameter("oid_Manifesto");
    ManifestoED filtro = new ManifestoED();
    filtro.setUser(UsuarioBean.getUsuarioCorrente(request).getOid_Usuario().intValue());
    if (JavaUtil.doValida(oid_Manifesto)) {
        filtro.setOID_Manifesto(oid_Manifesto);
    } else throw new Mensagens("Informe o manifesto!");


    ManifestoRN manRN = new ManifestoRN();

    return manRN.imprime_Manifesto(filtro);

  }


  public void relComissaoManifesto(HttpServletRequest request, HttpServletResponse response)
  throws Excecoes {
      String Relatorio = request.getParameter("Relatorio");
      String oid_Empresa = request.getParameter("oid_Empresa");
      String oid_Unidade = request.getParameter("oid_Unidade");
      String oid_Manifesto = request.getParameter("oid_Manifesto");
      String oid_Pessoa = request.getParameter("oid_Pessoa");
      String DT_Emissao_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
      String DT_Emissao_Final = request.getParameter("FT_DT_Emissao_Final");

      if (!util.doValida(Relatorio)) {
          throw new Mensagens("Nome do Relatório não informado!");
      }
      ManifestoED ed = new ManifestoED(response, Relatorio);

      if (util.doValida(oid_Empresa)) {
          ed.setOid_Empresa(Long.parseLong(oid_Empresa));
      }
      if (util.doValida(oid_Unidade)) {
          ed.setOID_Unidade(Long.parseLong(oid_Unidade));
      }
      if (util.doValida(oid_Manifesto)) {
          ed.setOID_Manifesto(oid_Manifesto);
      }
      if (util.doValida(oid_Pessoa)) {
          ed.setOID_Pessoa(oid_Pessoa);
      }
      if (util.doValida(DT_Emissao_Inicial)) {
          ed.setDT_Emissao_Inicial(DT_Emissao_Inicial);
      }
      if (util.doValida(DT_Emissao_Final)) {
          ed.setDT_Emissao_Final(DT_Emissao_Final);
      }
      new ManifestoRN().relComissaoManifesto(ed);
  }


  public  byte[] gera_Relatorio_Manifesto(HttpServletRequest request, HttpServletResponse Response)throws Excecoes{

      String NR_Manifesto = request.getParameter("FT_NR_Manifesto");
      String OID_Cidade = request.getParameter("oid_Cidade_Destino");
      String OID_Empresa = request.getParameter("oid_Empresa");
      String OID_Veiculo = request.getParameter("FT_NR_Placa");
      String OID_Unidade = request.getParameter("oid_Unidade");
      String DT_Emissao_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
      String DT_Emissao_Final = request.getParameter("FT_DT_Emissao_Final");
      String DM_Tipo_Veiculo = request.getParameter("FT_DM_Tipo_Veiculo");
      String OID_Pessoa = request.getParameter("oid_Pessoa");
      String DM_Tipo_ICMS = request.getParameter("FT_DM_Tipo_ICMS");
      String DM_Relatorio = request.getParameter("FT_DM_Relatorio");
      String DM_Nota_Fiscal = request.getParameter("FT_DM_Nota_Fiscal");
      String oid_Pessoa_Destinatario = request.getParameter("oid_Pessoa_Destinatario");

      ManifestoED ed = new ManifestoED();

      if (JavaUtil.doValida(NR_Manifesto)) {
          ed.setNR_Manifesto(new Long(NR_Manifesto).longValue());
      }
      if (JavaUtil.doValida(OID_Cidade)) {
          ed.setOID_Cidade(new Long(OID_Cidade).longValue());
      }

      if (JavaUtil.doValida(OID_Empresa)) {
          ed.setOID_Empresa(new Long(OID_Empresa).longValue());
      }

      if (JavaUtil.doValida(OID_Unidade)) {
          ed.setOID_Unidade(new Long(OID_Unidade).longValue());
      }

      if (JavaUtil.doValida(DT_Emissao_Inicial)) {
          ed.setDT_Emissao_Inicial(DT_Emissao_Inicial);
      }
      if (JavaUtil.doValida(DT_Emissao_Final)) {
          ed.setDT_Emissao_Final(DT_Emissao_Final);
      }
      if (JavaUtil.doValida(DM_Tipo_Veiculo)) {
          ed.setDM_Tipo_Veiculo(DM_Tipo_Veiculo);
      }
      if (JavaUtil.doValida(OID_Pessoa)) {
          ed.setOID_Pessoa(OID_Pessoa);
      }
      if (JavaUtil.doValida(OID_Veiculo)) {
          ed.setOID_Veiculo(OID_Veiculo);
      }
      if (JavaUtil.doValida(DM_Tipo_ICMS)) {
          ed.setDM_Tipo_ICMS(DM_Tipo_ICMS);
      }
      if (JavaUtil.doValida(DM_Relatorio)) {
          ed.setDM_Relatorio(DM_Relatorio);
      }
      if (JavaUtil.doValida(DM_Nota_Fiscal)) {
          ed.setDM_Nota_Fiscal(DM_Nota_Fiscal);
      }
      if (util.doValida(oid_Pessoa_Destinatario)) {
          ed.setOID_Pessoa_Destinatario(oid_Pessoa_Destinatario);
      }

      String cd_Rota_Entrega = request.getParameter ("FT_CD_Rota_Entrega");
      if (JavaUtil.doValida (cd_Rota_Entrega)) {
        ed.setCD_Rota_Entrega (cd_Rota_Entrega);
      }

      ManifestoRN manRN = new ManifestoRN();

      return manRN.gera_Relatorio_Manifesto(ed);


  }

  public ManifestoED inclui_AWB(HttpServletRequest request)
  throws Excecoes{
          ManifestoED ed = new ManifestoED();

          // System.out.println("inclui_AWB 0" );
          ed.setOID_Manifesto(request.getParameter("oid_Manifesto"));

          return new ManifestoRN().inclui_AWB(ed);
  }

  public void rel_Resumo_Entrega_Rota(HttpServletRequest request, HttpServletResponse Response)throws Excecoes{

      String OID_Unidade = request.getParameter("oid_Unidade");
      String DT_Emissao_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
      String DT_Emissao_Final = request.getParameter("FT_DT_Emissao_Final");
      String cd_Rota_Entrega = request.getParameter("FT_CD_Rota_Entrega");
      String OID_Pessoa = request.getParameter("oid_Pessoa");

      ManifestoED ed = new ManifestoED();

      if (JavaUtil.doValida(OID_Unidade)) {
          ed.setOID_Unidade(new Long(OID_Unidade).longValue());
}
      if (JavaUtil.doValida(DT_Emissao_Inicial)) {
          ed.setDT_Emissao_Inicial(DT_Emissao_Inicial);
      }
      if (JavaUtil.doValida(DT_Emissao_Final)) {
          ed.setDT_Emissao_Final(DT_Emissao_Final);
      }
      if (JavaUtil.doValida(cd_Rota_Entrega)){
    	  ed.setCD_Rota_Entrega(cd_Rota_Entrega);
      }
      if (JavaUtil.doValida(OID_Pessoa)) {
          ed.setOID_Pessoa(OID_Pessoa);
      }
      new ManifestoRN().rel_Resumo_Entrega_Rota(ed, Response);
  }


}
