package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.EmbarqueED;
import com.master.rn.EmbarqueRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.ed.*;

public class emb001Bean {


  private ArrayList lista = null;

  public EmbarqueED inclui(HttpServletRequest request)throws Excecoes{

    try{
      EmbarqueRN Embarquern = new EmbarqueRN();
      EmbarqueED ed = new EmbarqueED();

      ed.setDM_Carga_Veiculo(request.getParameter("FT_DM_Carga_Veiculo"));
      ed.setOID_Cidade_Origem(new Long(request.getParameter("oid_Cidade_Origem")).longValue());
      ed.setOID_Cidade_Destino(new Long(request.getParameter("oid_Cidade_Destino")).longValue());
      ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));
      ed.setDM_Expresso(request.getParameter("FT_DM_Expresso"));
      //ed.setDT_Previsao_Chegada(request.getParameter("FT_DT_Previsao_Chegada"));
      //ed.setHR_Previsao_Chegada(request.getParameter("FT_HR_Previsao_Chegada"));
      ed.setNM_Motorista(request.getParameter("FT_NM_Motorista"));
      ed.setNR_Placa(request.getParameter("oid_Veiculo"));
      ed.setNR_Celular(request.getParameter("FT_NR_Celular"));

      ed.setNR_Chassi(request.getParameter("FT_NR_Chassi"));
      ed.setNR_Carro(request.getParameter("FT_NR_Carro"));

      ed.setOID_Veiculo(request.getParameter("oid_Veiculo"));
      ed.setOID_Veiculo_Carreta(request.getParameter("oid_Veiculo_Carreta"));
      ed.setDM_Situacao("9");
      ed.setCD_Roteiro(request.getParameter("FT_CD_Roteiro"));

//// // System.out.println("aqui 01 "+request.getParameter("FT_NR_Odometro_Inicial"));

      String odometro = request.getParameter("FT_NR_Odometro_Inicial");
//// // System.out.println("aqui 02 "+ odometro);
      if (String.valueOf(odometro) != null && !String.valueOf(odometro).equals("")
        && !String.valueOf(odometro).equals("null")){
        ed.setNR_Odometro_Inicial(new Long(odometro).longValue());
      }
//      ed.setNR_Odometro_Inicial(new Long(request.getParameter("FT_NR_Odometro_Inicial")).longValue());
  
      return Embarquern.inclui(ed);
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

  public void altera(HttpServletRequest request)throws Excecoes{

    try{
      EmbarqueRN Embarquern = new EmbarqueRN();
      EmbarqueED ed = new EmbarqueED();

      ed.setDM_Carga_Veiculo(request.getParameter("FT_DM_Carga_Veiculo"));

      ed.setOID_Embarque(new Long(request.getParameter("oid_Embarque")).longValue());
      ed.setOID_Cidade_Origem(new Long(request.getParameter("oid_Cidade_Origem")).longValue());
      ed.setOID_Cidade_Destino(new Long(request.getParameter("oid_Cidade_Destino")).longValue());
      ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));
      ed.setOID_Veiculo(request.getParameter("oid_Veiculo"));
      ed.setOID_Veiculo_Carreta(request.getParameter("oid_Veiculo_Carreta"));
      ed.setDM_Expresso(request.getParameter("FT_DM_Expresso"));
      ed.setNM_Motorista(request.getParameter("FT_NM_Motorista"));
      ed.setNR_Placa(request.getParameter("oid_Veiculo"));
      ed.setNR_Celular(request.getParameter("FT_NR_Celular"));

      ed.setOID_Manifesto(request.getParameter("oid_Manifesto"));
// System.out.println(ed.getOID_Manifesto());

      ed.setNR_Chassi(request.getParameter("FT_NR_Chassi"));
      ed.setNR_Carro(request.getParameter("FT_NR_Carro"));

      ed.setDM_Situacao(request.getParameter("FT_DM_Situacao"));
      ed.setDT_Chegada(request.getParameter("FT_DT_Chegada"));
      ed.setHR_Chegada(request.getParameter("FT_HR_Chegada"));
      ed.setDT_Previsao_Chegada(request.getParameter("FT_DT_Previsao_Chegada"));
      ed.setHR_Previsao_Chegada(request.getParameter("FT_HR_Previsao_Chegada"));
      ed.setDT_Entrega(request.getParameter("FT_DT_Entrega"));
      ed.setHR_Entrega(request.getParameter("FT_HR_Entrega"));
      ed.setDT_Nova_Previsao_Chegada(request.getParameter("FT_DT_Nova_Previsao_Chegada"));
      ed.setHR_Nova_Previsao_Chegada(request.getParameter("FT_HR_Nova_Previsao_Chegada"));
      ed.setDT_Saida(request.getParameter("FT_DT_Saida"));
      ed.setHR_Saida(request.getParameter("FT_HR_Saida"));

      ed.setDT_Previsao_Chegada_Anterior(request.getParameter("FT_DT_Previsao_Chegada_Anterior"));
      ed.setDT_Entrega_Anterior(request.getParameter("FT_DT_Entrega_Anterior"));
      ed.setDT_Saida_Anterior(request.getParameter("FT_DT_Saida_Anterior"));
      ed.setDT_Chegada_Anterior(request.getParameter("FT_DT_Chegada_Anterior"));
      ed.setCD_Roteiro(request.getParameter("FT_CD_Roteiro"));

      ed.setNR_Odometro_Inicial(new Long(request.getParameter("FT_NR_Odometro_Inicial")).longValue());

      Embarquern.altera(ed);
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
      EmbarqueRN Embarquern = new EmbarqueRN();
      EmbarqueED ed = new EmbarqueED();

      ed.setOID_Embarque(new Long(request.getParameter("oid_Embarque")).longValue());

      Embarquern.deleta(ed);
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

  public ArrayList Embarque_Lista(HttpServletRequest request)throws Excecoes{

      EmbarqueED ed = new EmbarqueED();

      ed.setOID_Veiculo(request.getParameter("oid_Veiculo"));
      ed.setOID_Veiculo_Carreta(request.getParameter("oid_Veiculo_Carreta"));

      ed.setNM_Motorista(request.getParameter("FT_NM_Motorista"));
      ed.setNR_Placa(request.getParameter("FT_NR_Placa"));
      ed.setNR_Placa_Carreta(request.getParameter("FT_NR_Placa_Carreta"));
      ed.setDM_Situacao(request.getParameter("FT_DM_Situacao"));

      ed.setNR_Chassi(request.getParameter("FT_NR_Chassi"));
      ed.setNR_Carro(request.getParameter("FT_NR_Carro"));

      ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));
      ed.setDT_Emissao_Final(request.getParameter("FT_DT_Emissao_Final"));
      ed.setDT_Previsao_Chegada(request.getParameter("FT_DT_Previsao_Chegada"));
      ed.setDT_Previsao_Chegada_Final(request.getParameter("FT_DT_Previsao_Chegada_Final"));
      ed.setDT_Entrega(request.getParameter("FT_DT_Entrega"));
      ed.setDT_Entrega_Final(request.getParameter("FT_DT_Entrega_Final"));

      ed.setDM_Carga_Veiculo(request.getParameter("FT_DM_Carga_Veiculo"));
      ed.setDM_Tipo_Frota(request.getParameter("FT_DM_Tipo_Frota"));

      String nr_Embarque = request.getParameter("FT_NR_Embarque");
      if (String.valueOf(nr_Embarque) != null &&
        !String.valueOf(nr_Embarque).equals("") &&
        !String.valueOf(nr_Embarque).equals("null")){
        ed.setNR_Embarque(new Long(nr_Embarque).longValue());
      }

      String OID_Cidade_Destino = request.getParameter("oid_Cidade_Destino");
      if (String.valueOf(OID_Cidade_Destino) != null &&
        !String.valueOf(OID_Cidade_Destino).equals("") &&
        !String.valueOf(OID_Cidade_Destino).equals("null")){
        ed.setOID_Cidade_Destino(new Long(OID_Cidade_Destino).longValue());
      }

      String OID_Estado_Destino = request.getParameter("oid_Estado_Destino");
      if (String.valueOf(OID_Estado_Destino) != null &&
        !String.valueOf(OID_Estado_Destino).equals("") &&
        !String.valueOf(OID_Estado_Destino).equals("null")){
        ed.setOID_Estado_Destino(new Long(OID_Estado_Destino).longValue());
      }
      String OID_Pais = request.getParameter("oid_Pais");
      if (String.valueOf(OID_Pais) != null &&
        !String.valueOf(OID_Pais).equals("") &&
        !String.valueOf(OID_Pais).equals("null")){
        ed.setOID_Pais(new Long(OID_Pais).longValue());
      }
      String OID_Cidade_Origem = request.getParameter("oid_Cidade_Origem");
      if (String.valueOf(OID_Cidade_Origem) != null &&
        !String.valueOf(OID_Cidade_Origem).equals("") &&
        !String.valueOf(OID_Cidade_Origem).equals("null")){
        ed.setOID_Cidade_Origem(new Long(OID_Cidade_Origem).longValue());
      }

      String OID_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
      if (String.valueOf(OID_Nota_Fiscal) != null &&
        !String.valueOf(OID_Nota_Fiscal).equals("") &&
        !String.valueOf(OID_Nota_Fiscal).equals("null")){
        ed.setOID_Nota_Fiscal(OID_Nota_Fiscal);
      }

      String NR_Nota_Fiscal = request.getParameter("FT_NR_Nota_Fiscal");
      if (String.valueOf(NR_Nota_Fiscal) != null &&
        !String.valueOf(NR_Nota_Fiscal).equals("") &&
        !String.valueOf(NR_Nota_Fiscal).equals("null")){
        ed.setNR_Nota_Fiscal(NR_Nota_Fiscal);
      }

      String NR_Pedido = request.getParameter("FT_NR_Pedido");
      if (String.valueOf(NR_Pedido) != null &&
        !String.valueOf(NR_Pedido).equals("") &&
        !String.valueOf(NR_Pedido).equals("null")){
        ed.setNR_Pedido(NR_Pedido);
      }

      String OID_Rota = request.getParameter("FT_CD_Rota");
      if (String.valueOf(OID_Rota) != null &&
        !String.valueOf(OID_Rota).equals("") &&
        !String.valueOf(OID_Rota).equals("null")){
        ed.setOID_Rota(new Long(OID_Rota).longValue());
      }

      String OID_Tipo_Veiculo = request.getParameter("oid_Tipo_Veiculo");
      if (String.valueOf(OID_Tipo_Veiculo) != null &&
        !String.valueOf(OID_Tipo_Veiculo).equals("") &&
        !String.valueOf(OID_Tipo_Veiculo).equals("null")){
        ed.setOID_Tipo_Veiculo(new Long(OID_Tipo_Veiculo).longValue());
      }

      String OID_Tipo_Veiculo_Carreta = request.getParameter("oid_Tipo_Veiculo_Carreta");
      if (String.valueOf(OID_Tipo_Veiculo_Carreta) != null &&
        !String.valueOf(OID_Tipo_Veiculo_Carreta).equals("") &&
        !String.valueOf(OID_Tipo_Veiculo_Carreta).equals("null")){
        ed.setOID_Tipo_Veiculo_Carreta(new Long(OID_Tipo_Veiculo_Carreta).longValue());
      }

      String OID_Cidade_Apoio = request.getParameter("oid_Cidade_Apoio");
      if (String.valueOf(OID_Cidade_Apoio) != null &&
        !String.valueOf(OID_Cidade_Apoio).equals("") &&
        !String.valueOf(OID_Cidade_Apoio).equals("null")){
        ed.setOID_Cidade_Apoio(new Long(OID_Cidade_Apoio).longValue());
      }

//fatura
      String NR_Fatura = request.getParameter("FT_NR_Fatura");
      if (String.valueOf(NR_Fatura) != null &&
        !String.valueOf(NR_Fatura).equals("") &&
        !String.valueOf(NR_Fatura).equals("null")){
        ed.setNR_Fatura(NR_Fatura);
      }

// MIC e Manifesto
      String NR_MIC = request.getParameter("FT_NR_MIC");
      if (String.valueOf(NR_MIC) != null &&
        !String.valueOf(NR_MIC).equals("") &&
        !String.valueOf(NR_MIC).equals("null")){
        ed.setNR_MIC(NR_MIC);
      }
      String NR_Manifesto = request.getParameter("FT_NR_Manifesto");
      if (String.valueOf(NR_Manifesto) != null &&
        !String.valueOf(NR_Manifesto).equals("") &&
        !String.valueOf(NR_Manifesto).equals("null")){
        ed.setNR_Manifesto(NR_Manifesto);
      }
//    CRT e Conhecimento
      String NR_CRT = request.getParameter("FT_NR_CRT");
      if (String.valueOf(NR_CRT) != null &&
        !String.valueOf(NR_CRT).equals("") &&
        !String.valueOf(NR_CRT).equals("null")){
        ed.setNR_CRT(NR_CRT);
      }
      String NR_Conhecimento = request.getParameter("FT_NR_Conhecimento");
      if (String.valueOf(NR_Conhecimento) != null &&
        !String.valueOf(NR_Conhecimento).equals("") &&
        !String.valueOf(NR_Conhecimento).equals("null")){
        ed.setNR_Conhecimento(NR_Conhecimento);
      }
      
//	  Tipo Internacional I ou Nacional N
      ed.setDM_Tipo_Embarque("I");
      if (String.valueOf(request.getParameter("FT_DM_Tipo_Embarque")) != null &&
         !String.valueOf(request.getParameter("FT_DM_Tipo_Embarque")).equals("") &&
         !String.valueOf(request.getParameter("FT_DM_Tipo_Embarque")).equals("null")){
          ed.setDM_Tipo_Embarque(request.getParameter("FT_DM_Tipo_Embarque"));
      }
      // System.out.println("ED " + ed.getDM_Tipo_Embarque());
      // System.out.println("request > " +request.getParameter("FT_DM_Tipo_Embarque"));
      
//    Remetente e Destinatario
      String oid_pessoa = request.getParameter("oid_Pessoa_Remetente");
      if (String.valueOf(oid_pessoa) != null &&
        !String.valueOf(oid_pessoa).equals("") &&
        !String.valueOf(oid_pessoa).equals("null")){
        ed.setOID_Pessoa(oid_pessoa);
      }
      String oid_pessoa_destinatario = request.getParameter("oid_Pessoa_Destinatario");
      if (String.valueOf(oid_pessoa_destinatario) != null &&
        !String.valueOf(oid_pessoa_destinatario).equals("") &&
        !String.valueOf(oid_pessoa_destinatario).equals("null")){
        ed.setOID_Pessoa_Destinatario(oid_pessoa_destinatario);
      }

      return new EmbarqueRN().lista(ed);

  }

  public ArrayList Embarque_Lista_Traking(HttpServletRequest request)throws Excecoes{

      EmbarqueED ed = new EmbarqueED();

      ed.setOID_Veiculo(request.getParameter("oid_Veiculo"));
      ed.setOID_Pessoa_Senha(request.getParameter("oid_Pessoa_Senha"));
      ed.setOID_Veiculo_Carreta(request.getParameter("oid_Veiculo_Carreta"));

      ed.setNM_Motorista(request.getParameter("FT_NM_Motorista"));
      ed.setNR_Placa(request.getParameter("FT_NR_Placa"));
      ed.setNR_Placa_Carreta(request.getParameter("FT_NR_Placa_Carreta"));
      ed.setDM_Situacao(request.getParameter("FT_DM_Situacao"));

      ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));
      ed.setDT_Emissao_Final(request.getParameter("FT_DT_Emissao_Final"));
      ed.setDT_Previsao_Chegada(request.getParameter("FT_DT_Previsao_Chegada"));
      ed.setDT_Previsao_Chegada_Final(request.getParameter("FT_DT_Previsao_Chegada_Final"));
      ed.setDT_Entrega(request.getParameter("FT_DT_Entrega"));
      ed.setDT_Entrega_Final(request.getParameter("FT_DT_Entrega_Final"));

      ed.setDM_Carga_Veiculo(request.getParameter("FT_DM_Carga_Veiculo"));
      ed.setDM_Tipo_Frota(request.getParameter("FT_DM_Tipo_Frota"));

      String nr_Embarque = request.getParameter("FT_NR_Embarque");
      if (String.valueOf(nr_Embarque) != null &&
        !String.valueOf(nr_Embarque).equals("") &&
        !String.valueOf(nr_Embarque).equals("null")){
        ed.setNR_Embarque(new Long(nr_Embarque).longValue());
      }

      String OID_Cidade_Destino = request.getParameter("oid_Cidade_Destino");
      if (String.valueOf(OID_Cidade_Destino) != null &&
        !String.valueOf(OID_Cidade_Destino).equals("") &&
        !String.valueOf(OID_Cidade_Destino).equals("null")){
        ed.setOID_Cidade_Destino(new Long(OID_Cidade_Destino).longValue());
      }

      String OID_Estado_Destino = request.getParameter("oid_Estado_Destino");
      if (String.valueOf(OID_Estado_Destino) != null &&
        !String.valueOf(OID_Estado_Destino).equals("") &&
        !String.valueOf(OID_Estado_Destino).equals("null")){
        ed.setOID_Estado_Destino(new Long(OID_Estado_Destino).longValue());
      }

      String OID_Pais = request.getParameter("oid_Pais");
      if (String.valueOf(OID_Pais) != null &&
        !String.valueOf(OID_Pais).equals("") &&
        !String.valueOf(OID_Pais).equals("null")){
        ed.setOID_Pais(new Long(OID_Pais).longValue());
      }

      String OID_Cidade_Origem = request.getParameter("oid_Cidade_Origem");
      if (String.valueOf(OID_Cidade_Origem) != null &&
        !String.valueOf(OID_Cidade_Origem).equals("") &&
        !String.valueOf(OID_Cidade_Origem).equals("null")){
        ed.setOID_Cidade_Origem(new Long(OID_Cidade_Origem).longValue());
      }

      String OID_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
      if (String.valueOf(OID_Nota_Fiscal) != null &&
        !String.valueOf(OID_Nota_Fiscal).equals("") &&
        !String.valueOf(OID_Nota_Fiscal).equals("null")){
        ed.setOID_Nota_Fiscal(OID_Nota_Fiscal);
      }

      String NR_Nota_Fiscal = request.getParameter("FT_NR_Nota_Fiscal");
      if (String.valueOf(NR_Nota_Fiscal) != null &&
        !String.valueOf(NR_Nota_Fiscal).equals("") &&
        !String.valueOf(NR_Nota_Fiscal).equals("null")){
        ed.setNR_Nota_Fiscal(NR_Nota_Fiscal);
      }

      String NR_Pedido = request.getParameter("FT_NR_Pedido");
      if (String.valueOf(NR_Pedido) != null &&
        !String.valueOf(NR_Pedido).equals("") &&
        !String.valueOf(NR_Pedido).equals("null")){
        ed.setNR_Pedido(NR_Pedido);
      }

      String OID_Rota = request.getParameter("FT_CD_Rota");
      if (String.valueOf(OID_Rota) != null &&
        !String.valueOf(OID_Rota).equals("") &&
        !String.valueOf(OID_Rota).equals("null")){
        ed.setOID_Rota(new Long(OID_Rota).longValue());
      }

      String OID_Tipo_Veiculo = request.getParameter("oid_Tipo_Veiculo");
      if (String.valueOf(OID_Tipo_Veiculo) != null &&
        !String.valueOf(OID_Tipo_Veiculo).equals("") &&
        !String.valueOf(OID_Tipo_Veiculo).equals("null")){
        ed.setOID_Tipo_Veiculo(new Long(OID_Tipo_Veiculo).longValue());
      }

      String OID_Tipo_Veiculo_Carreta = request.getParameter("oid_Tipo_Veiculo_Carreta");
      if (String.valueOf(OID_Tipo_Veiculo_Carreta) != null &&
        !String.valueOf(OID_Tipo_Veiculo_Carreta).equals("") &&
        !String.valueOf(OID_Tipo_Veiculo_Carreta).equals("null")){
        ed.setOID_Tipo_Veiculo_Carreta(new Long(OID_Tipo_Veiculo_Carreta).longValue());
      }

      String OID_Cidade_Apoio = request.getParameter("oid_Cidade_Apoio");
      if (String.valueOf(OID_Cidade_Apoio) != null &&
        !String.valueOf(OID_Cidade_Apoio).equals("") &&
        !String.valueOf(OID_Cidade_Apoio).equals("null")){
        ed.setOID_Cidade_Apoio(new Long(OID_Cidade_Apoio).longValue());
      }

//fatura
      String NR_Fatura = request.getParameter("FT_NR_Fatura");
      if (String.valueOf(NR_Fatura) != null &&
        !String.valueOf(NR_Fatura).equals("") &&
        !String.valueOf(NR_Fatura).equals("null")){
        ed.setNR_Fatura(NR_Fatura);
      }

      return new EmbarqueRN().lista_Traking(ed);

  }

  public EmbarqueED getByRecord(HttpServletRequest request)throws Excecoes{

      EmbarqueED ed = new EmbarqueED();

      String NR_Embarque = request.getParameter("FT_NR_Embarque");
      if (String.valueOf(NR_Embarque) != null &&
          !String.valueOf(NR_Embarque).equals("") &&
          !String.valueOf(NR_Embarque).equals("null")){
        ed.setNR_Embarque(new Long(request.getParameter("FT_NR_Embarque")).longValue());
      }

      String oid_Embarque = request.getParameter("oid_Embarque");

      if (String.valueOf(oid_Embarque) != null &&
          !String.valueOf(oid_Embarque).equals("") &&
          !String.valueOf(oid_Embarque).equals("null")){
        ed.setOID_Embarque(new Long(oid_Embarque).longValue());
      }


      String oid_Manifesto = request.getParameter("oid_Manifesto");

      if (String.valueOf(oid_Manifesto) != null &&
          !String.valueOf(oid_Manifesto).equals("") &&
          !String.valueOf(oid_Manifesto).equals("null")){
        ed.setOID_Manifesto(oid_Manifesto);
      }

      return new EmbarqueRN().getByRecord(ed);

  }

  public void geraRelatorio(HttpServletRequest request)throws Excecoes{
    EmbarqueED ed = new EmbarqueED();

      ed.setNM_Motorista(request.getParameter("FT_NM_Motorista"));
      ed.setNR_Placa(request.getParameter("FT_NR_Placa"));
      ed.setDM_Situacao(request.getParameter("FT_DM_Situacao"));

      ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));
      String nr_Embarque = request.getParameter("FT_NR_Embarque");
      if (String.valueOf(nr_Embarque) != null &&
        !String.valueOf(nr_Embarque).equals("") &&
        !String.valueOf(nr_Embarque).equals("null")){
        ed.setNR_Embarque(new Long(nr_Embarque).longValue());
      }

      String OID_Cidade_Destino = request.getParameter("oid_Cidade_Destino");
      if (String.valueOf(OID_Cidade_Destino) != null &&
        !String.valueOf(OID_Cidade_Destino).equals("") &&
        !String.valueOf(OID_Cidade_Destino).equals("null")){
        ed.setOID_Cidade_Destino(new Long(OID_Cidade_Destino).longValue());
      }

      String OID_Cidade_Origem = request.getParameter("oid_Cidade_Origem");
      if (String.valueOf(OID_Cidade_Origem) != null &&
        !String.valueOf(OID_Cidade_Origem).equals("") &&
        !String.valueOf(OID_Cidade_Origem).equals("null")){
        ed.setOID_Cidade_Origem(new Long(OID_Cidade_Origem).longValue());
      }

    EmbarqueRN geRN = new EmbarqueRN();
    geRN.geraRelatorio(ed);
  }

  public ArrayList lista_Localizacao_Veiculos(String orderby, String dm_procedencia, String placa)throws Excecoes{
    try{
        EmbarqueRN RN = new EmbarqueRN();
        this.setLista( RN.lista_Localizacao_Veiculos( orderby, dm_procedencia, placa) );
    }catch( Exception ex ){}

    return this.getLista();
}

  public ArrayList lista_Localizacao_Veiculos_Placar(String orderby, String dm_procedencia, String placa)throws Excecoes{
    try{
        EmbarqueRN RN = new EmbarqueRN();
        this.setLista( RN.lista_Localizacao_Veiculos_Placar( orderby, dm_procedencia, placa) );
    }catch( Exception ex ){}

    return this.getLista();
}

  public void setLista( ArrayList array ){
    this.lista = array;
  }

  public ArrayList getLista(){
    return this.lista;
  }
  
  public void geraInformeEmbarque(HttpServletRequest request, HttpServletResponse response)throws Excecoes{
    	EmbarqueED ed = new EmbarqueED();

    	ed.setNM_Motorista(request.getParameter("FT_NM_Motorista"));
    	ed.setNR_Placa(request.getParameter("FT_NR_Placa"));
    	ed.setDM_Situacao(request.getParameter("FT_DM_Situacao"));
    	ed.setOID_Embarque(new Long(request.getParameter("oid_Embarque")).longValue());

    	ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));
    	String nr_Embarque = request.getParameter("FT_NR_Embarque");
    	if (String.valueOf(nr_Embarque) != null &&
    			!String.valueOf(nr_Embarque).equals("") &&
  			!String.valueOf(nr_Embarque).equals("null")){
    		ed.setNR_Embarque(new Long(nr_Embarque).longValue());
    	}

    	String OID_Cidade_Destino = request.getParameter("oid_Cidade_Destino");
    	if (String.valueOf(OID_Cidade_Destino) != null &&
    			!String.valueOf(OID_Cidade_Destino).equals("") &&
  			!String.valueOf(OID_Cidade_Destino).equals("null")){
    		ed.setOID_Cidade_Destino(new Long(OID_Cidade_Destino).longValue());
    	}

    	String OID_Cidade_Origem = request.getParameter("oid_Cidade_Origem");
    	if (String.valueOf(OID_Cidade_Origem) != null &&
    			!String.valueOf(OID_Cidade_Origem).equals("") &&
  			!String.valueOf(OID_Cidade_Origem).equals("null")){
    		ed.setOID_Cidade_Origem(new Long(OID_Cidade_Origem).longValue());
    	}

    	EmbarqueRN geRN = new EmbarqueRN();
    	geRN.geraInformeEmbarque(ed, response);
    }
  
  public void relEmbarque(HttpServletRequest request, HttpServletResponse response)throws Excecoes{

      EmbarqueED ed = new EmbarqueED();

      ed.setOID_Veiculo(request.getParameter("oid_Veiculo"));
      ed.setOID_Veiculo_Carreta(request.getParameter("oid_Veiculo_Carreta"));

      ed.setNM_Motorista(request.getParameter("FT_NM_Motorista"));
      ed.setNR_Placa(request.getParameter("FT_NR_Placa"));
      ed.setNR_Placa_Carreta(request.getParameter("FT_NR_Placa_Carreta"));
      ed.setDM_Situacao(request.getParameter("FT_DM_Situacao"));

      ed.setNR_Chassi(request.getParameter("FT_NR_Chassi"));
      ed.setNR_Carro(request.getParameter("FT_NR_Carro"));

      ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));
      ed.setDT_Emissao_Final(request.getParameter("FT_DT_Emissao_Final"));
      ed.setDT_Previsao_Chegada(request.getParameter("FT_DT_Previsao_Chegada"));
      ed.setDT_Previsao_Chegada_Final(request.getParameter("FT_DT_Previsao_Chegada_Final"));
      ed.setDT_Entrega(request.getParameter("FT_DT_Entrega"));
      ed.setDT_Entrega_Final(request.getParameter("FT_DT_Entrega_Final"));

      ed.setDM_Carga_Veiculo(request.getParameter("FT_DM_Carga_Veiculo"));
      ed.setDM_Tipo_Frota(request.getParameter("FT_DM_Tipo_Frota"));

      String nr_Embarque = request.getParameter("FT_NR_Embarque");
      if (JavaUtil.doValida(String.valueOf(nr_Embarque))){
        ed.setNR_Embarque(new Long(nr_Embarque).longValue());
      }

      String OID_Cidade_Destino = request.getParameter("oid_Cidade_Destino");
      if (JavaUtil.doValida(String.valueOf(OID_Cidade_Destino))){
        ed.setOID_Cidade_Destino(new Long(OID_Cidade_Destino).longValue());
      }

      String OID_Estado_Destino = request.getParameter("oid_Estado_Destino");
      if (JavaUtil.doValida(String.valueOf(OID_Estado_Destino))){
        ed.setOID_Estado_Destino(new Long(OID_Estado_Destino).longValue());
      }
      String OID_Pais = request.getParameter("oid_Pais");
      if (JavaUtil.doValida(String.valueOf(OID_Pais))){
        ed.setOID_Pais(new Long(OID_Pais).longValue());
      }
      String OID_Cidade_Origem = request.getParameter("oid_Cidade_Origem");
      if (JavaUtil.doValida(String.valueOf(OID_Cidade_Origem))){
        ed.setOID_Cidade_Origem(new Long(OID_Cidade_Origem).longValue());
      }

      String OID_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
      if (JavaUtil.doValida(String.valueOf(OID_Nota_Fiscal))){
        ed.setOID_Nota_Fiscal(OID_Nota_Fiscal);
      }

      String NR_Nota_Fiscal = request.getParameter("FT_NR_Nota_Fiscal");
      if (JavaUtil.doValida(String.valueOf(NR_Nota_Fiscal))){
        ed.setNR_Nota_Fiscal(NR_Nota_Fiscal);
      }

      String NR_Pedido = request.getParameter("FT_NR_Pedido");
      if (JavaUtil.doValida(String.valueOf(NR_Pedido))){
        ed.setNR_Pedido(NR_Pedido);
      }

      String OID_Rota = request.getParameter("FT_CD_Rota");
      if (JavaUtil.doValida(String.valueOf(OID_Rota))){
        ed.setOID_Rota(new Long(OID_Rota).longValue());
      }

      String OID_Tipo_Veiculo = request.getParameter("oid_Tipo_Veiculo");
      if (JavaUtil.doValida(String.valueOf(OID_Tipo_Veiculo))){
        ed.setOID_Tipo_Veiculo(new Long(OID_Tipo_Veiculo).longValue());
      }

      String OID_Tipo_Veiculo_Carreta = request.getParameter("oid_Tipo_Veiculo_Carreta");
      if (JavaUtil.doValida(String.valueOf(OID_Tipo_Veiculo_Carreta))){
        ed.setOID_Tipo_Veiculo_Carreta(new Long(OID_Tipo_Veiculo_Carreta).longValue());
      }

      String OID_Cidade_Apoio = request.getParameter("oid_Cidade_Apoio");
      if (JavaUtil.doValida(String.valueOf(OID_Cidade_Apoio))){
        ed.setOID_Cidade_Apoio(new Long(OID_Cidade_Apoio).longValue());
      }

//fatura
      String NR_Fatura = request.getParameter("FT_NR_Fatura");
      if (JavaUtil.doValida(String.valueOf(NR_Fatura))){
        ed.setNR_Fatura(NR_Fatura);
      }

// MIC e Manifesto
      String NR_MIC = request.getParameter("FT_NR_MIC");
      if (JavaUtil.doValida(String.valueOf(NR_MIC))){
        ed.setNR_MIC(NR_MIC);
      }
      String NR_Manifesto = request.getParameter("FT_NR_Manifesto");
      if (JavaUtil.doValida(String.valueOf(NR_Manifesto))){
        ed.setNR_Manifesto(NR_Manifesto);
      }
//    CRT e Conhecimento
      String NR_CRT = request.getParameter("FT_NR_CRT");
      if (JavaUtil.doValida(String.valueOf(NR_CRT))){
        ed.setNR_CRT(NR_CRT);
      }
      String NR_Conhecimento = request.getParameter("FT_NR_Conhecimento");
      if (JavaUtil.doValida(String.valueOf(NR_Conhecimento))){
        ed.setNR_Conhecimento(NR_Conhecimento);
      }
      
//	  Tipo Internacional I ou Nacional N
      ed.setDM_Tipo_Embarque("I");
      if (JavaUtil.doValida(request.getParameter("FT_DM_Tipo_Embarque"))){
          ed.setDM_Tipo_Embarque(request.getParameter("FT_DM_Tipo_Embarque"));
      }
      // System.out.println("ED " + ed.getDM_Tipo_Embarque());
      // System.out.println("request > " +request.getParameter("FT_DM_Tipo_Embarque"));
      
//    Remetente e Destinatario
      String oid_pessoa = request.getParameter("oid_Pessoa_Remetente");
      if (JavaUtil.doValida(String.valueOf(oid_pessoa))){
        ed.setOID_Pessoa(oid_pessoa);
        ed.setNM_Remetente(request.getParameter("FT_NM_Razao_Social_Remetente"));
      }
      String oid_pessoa_destinatario = request.getParameter("oid_Pessoa_Destinatario");
      if (JavaUtil.doValida(String.valueOf(oid_pessoa_destinatario))){
        ed.setOID_Pessoa_Destinatario(oid_pessoa_destinatario);
        ed.setNM_Destinatario(request.getParameter("FT_NM_Razao_Social_Destinatario"));
      }

      new EmbarqueRN().relEmbarque(ed, response);

  }
  
  public void relChegadaEntrega(HttpServletRequest request, HttpServletResponse response)throws Excecoes{

      EmbarqueED ed = new EmbarqueED();

      ed.setOID_Veiculo(request.getParameter("oid_Veiculo"));
      ed.setOID_Veiculo_Carreta(request.getParameter("oid_Veiculo_Carreta"));

      ed.setNR_Chassi(request.getParameter("FT_NR_Chassi"));
      ed.setNR_Carro(request.getParameter("FT_NR_Carro"));

      ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));
      ed.setDT_Emissao_Final(request.getParameter("FT_DT_Emissao_Final"));
      ed.setDT_Previsao_Chegada(request.getParameter("FT_DT_Previsao_Chegada"));
      ed.setDT_Previsao_Chegada_Final(request.getParameter("FT_DT_Previsao_Chegada_Final"));
      ed.setDT_Entrega(request.getParameter("FT_DT_Entrega"));
      ed.setDT_Entrega_Final(request.getParameter("FT_DT_Entrega_Final"));

      String nr_Embarque = request.getParameter("FT_NR_Embarque");
      if (JavaUtil.doValida(String.valueOf(nr_Embarque))){
        ed.setNR_Embarque(new Long(nr_Embarque).longValue());
      }

      String OID_Cidade_Destino = request.getParameter("oid_Cidade_Destino");
      if (JavaUtil.doValida(String.valueOf(OID_Cidade_Destino))){
        ed.setOID_Cidade_Destino(new Long(OID_Cidade_Destino).longValue());
      }
      String OID_Cidade_Origem = request.getParameter("oid_Cidade_Origem");
      if (JavaUtil.doValida(String.valueOf(OID_Cidade_Origem))){
        ed.setOID_Cidade_Origem(new Long(OID_Cidade_Origem).longValue());
      }

// MIC e Manifesto
      String NR_MIC = request.getParameter("FT_NR_MIC");
      if (JavaUtil.doValida(String.valueOf(NR_MIC))){
        ed.setNR_MIC(NR_MIC);
      }
      String NR_Manifesto = request.getParameter("FT_NR_Manifesto");
      if (JavaUtil.doValida(String.valueOf(NR_Manifesto))){
        ed.setNR_Manifesto(NR_Manifesto);
      }
//    CRT e Conhecimento
      String NR_CRT = request.getParameter("FT_NR_CRT");
      if (JavaUtil.doValida(String.valueOf(NR_CRT))){
        ed.setNR_CRT(NR_CRT);
      }
      String NR_Conhecimento = request.getParameter("FT_NR_Conhecimento");
      if (JavaUtil.doValida(String.valueOf(NR_Conhecimento))){
        ed.setNR_Conhecimento(NR_Conhecimento);
      }
      
//	  Tipo Internacional I ou Nacional N
      ed.setDM_Tipo_Embarque("I");
      if (JavaUtil.doValida(request.getParameter("FT_DM_Tipo_Embarque"))){
          ed.setDM_Tipo_Embarque(request.getParameter("FT_DM_Tipo_Embarque"));
      }
      // System.out.println("ED " + ed.getDM_Tipo_Embarque());
      // System.out.println("request > " +request.getParameter("FT_DM_Tipo_Embarque"));
      
//    Remetente e Destinatario
      String oid_pessoa = request.getParameter("oid_Pessoa_Remetente");
      if (JavaUtil.doValida(String.valueOf(oid_pessoa))){
        ed.setOID_Pessoa(oid_pessoa);
        ed.setNM_Remetente(request.getParameter("FT_NM_Razao_Social_Remetente"));
      }
      String oid_pessoa_destinatario = request.getParameter("oid_Pessoa_Destinatario");
      if (JavaUtil.doValida(String.valueOf(oid_pessoa_destinatario))){
        ed.setOID_Pessoa_Destinatario(oid_pessoa_destinatario);
        ed.setNM_Destinatario(request.getParameter("FT_NM_Razao_Social_Destinatario"));
      }

      new EmbarqueRN().relChegadaEntrega(ed, response);

  }
  
  public void relTransitTime(HttpServletRequest request, HttpServletResponse response)throws Excecoes{

      EmbarqueED ed = new EmbarqueED();

      ed.setOID_Veiculo(request.getParameter("oid_Veiculo"));
      ed.setOID_Veiculo_Carreta(request.getParameter("oid_Veiculo_Carreta"));

      ed.setNR_Chassi(request.getParameter("FT_NR_Chassi"));
      ed.setNR_Carro(request.getParameter("FT_NR_Carro"));

      ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));
      ed.setDT_Emissao_Final(request.getParameter("FT_DT_Emissao_Final"));
      ed.setDT_Previsao_Chegada(request.getParameter("FT_DT_Previsao_Chegada"));
      ed.setDT_Previsao_Chegada_Final(request.getParameter("FT_DT_Previsao_Chegada_Final"));
      ed.setDT_Entrega(request.getParameter("FT_DT_Entrega"));
      ed.setDT_Entrega_Final(request.getParameter("FT_DT_Entrega_Final"));

      String nr_Embarque = request.getParameter("FT_NR_Embarque");
      if (JavaUtil.doValida(String.valueOf(nr_Embarque))){
        ed.setNR_Embarque(new Long(nr_Embarque).longValue());
      }

      String OID_Cidade_Destino = request.getParameter("oid_Cidade_Destino");
      if (JavaUtil.doValida(String.valueOf(OID_Cidade_Destino))){
        ed.setOID_Cidade_Destino(new Long(OID_Cidade_Destino).longValue());
      }
      String OID_Cidade_Origem = request.getParameter("oid_Cidade_Origem");
      if (JavaUtil.doValida(String.valueOf(OID_Cidade_Origem))){
        ed.setOID_Cidade_Origem(new Long(OID_Cidade_Origem).longValue());
      }

// MIC e Manifesto
      String NR_MIC = request.getParameter("FT_NR_MIC");
      if (JavaUtil.doValida(String.valueOf(NR_MIC))){
        ed.setNR_MIC(NR_MIC);
      }
      String NR_Manifesto = request.getParameter("FT_NR_Manifesto");
      if (JavaUtil.doValida(String.valueOf(NR_Manifesto))){
        ed.setNR_Manifesto(NR_Manifesto);
      }
//    CRT e Conhecimento
      String NR_CRT = request.getParameter("FT_NR_CRT");
      if (JavaUtil.doValida(String.valueOf(NR_CRT))){
        ed.setNR_CRT(NR_CRT);
      }
      String NR_Conhecimento = request.getParameter("FT_NR_Conhecimento");
      if (JavaUtil.doValida(String.valueOf(NR_Conhecimento))){
        ed.setNR_Conhecimento(NR_Conhecimento);
      }
      
//	  Tipo Internacional I ou Nacional N
      ed.setDM_Tipo_Embarque("I");
      if (JavaUtil.doValida(request.getParameter("FT_DM_Tipo_Embarque"))){
          ed.setDM_Tipo_Embarque(request.getParameter("FT_DM_Tipo_Embarque"));
      }
      // System.out.println("ED " + ed.getDM_Tipo_Embarque());
      // System.out.println("request > " +request.getParameter("FT_DM_Tipo_Embarque"));
      
//    Remetente e Destinatario
      String oid_pessoa = request.getParameter("oid_Pessoa_Remetente");
      if (JavaUtil.doValida(String.valueOf(oid_pessoa))){
        ed.setOID_Pessoa(oid_pessoa);
        ed.setNM_Remetente(request.getParameter("FT_NM_Razao_Social_Remetente"));
      }
      String oid_pessoa_destinatario = request.getParameter("oid_Pessoa_Destinatario");
      if (JavaUtil.doValida(String.valueOf(oid_pessoa_destinatario))){
        ed.setOID_Pessoa_Destinatario(oid_pessoa_destinatario);
        ed.setNM_Destinatario(request.getParameter("FT_NM_Razao_Social_Destinatario"));
      }

      new EmbarqueRN().relTransitTime(ed, response);

  }
  
  public void relCRT_Armazem(HttpServletRequest request, HttpServletResponse response)throws Excecoes{

      EmbarqueED ed = new EmbarqueED();

      ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));
      ed.setDT_Emissao_Final(request.getParameter("FT_DT_Emissao_Final"));

//    CRT e Conhecimento
      String NR_CRT = request.getParameter("FT_NR_CRT");
      if (JavaUtil.doValida(String.valueOf(NR_CRT))){
        ed.setNR_CRT(NR_CRT);
      }
      String NR_Conhecimento = request.getParameter("FT_NR_Conhecimento");
      if (JavaUtil.doValida(String.valueOf(NR_Conhecimento))){
        ed.setNR_Conhecimento(NR_Conhecimento);
      }
      
      ed.setDM_Procedencia("C");
      if (JavaUtil.doValida(request.getParameter("FT_DM_Procedencia"))){
          ed.setDM_Procedencia(request.getParameter("FT_DM_Procedencia"));
      }
      
//	  Tipo Internacional I ou Nacional N
      ed.setDM_Tipo_Embarque("I");
      if (JavaUtil.doValida(request.getParameter("FT_DM_Tipo_Embarque"))){
          ed.setDM_Tipo_Embarque(request.getParameter("FT_DM_Tipo_Embarque"));
      }
      // System.out.println("ED " + ed.getDM_Tipo_Embarque());
      // System.out.println("request > " +request.getParameter("FT_DM_Tipo_Embarque"));
      
//    Remetente e Destinatario
      String oid_pessoa = request.getParameter("oid_Pessoa_Remetente");
      if (JavaUtil.doValida(String.valueOf(oid_pessoa))){
        ed.setOID_Pessoa(oid_pessoa);
        ed.setNM_Remetente(request.getParameter("FT_NM_Razao_Social_Remetente"));
      }
      String oid_pessoa_destinatario = request.getParameter("oid_Pessoa_Destinatario");
      if (JavaUtil.doValida(String.valueOf(oid_pessoa_destinatario))){
        ed.setOID_Pessoa_Destinatario(oid_pessoa_destinatario);
        ed.setNM_Destinatario(request.getParameter("FT_NM_Razao_Social_Destinatario"));
      }

      new EmbarqueRN().relCRT_Armazem(ed, response);

  }
  
  public void geraControleViagem(HttpServletRequest request, HttpServletResponse response)throws Excecoes{
  	EmbarqueED ed = new EmbarqueED();

  	ed.setNM_Motorista(request.getParameter("FT_NM_Motorista"));
  	ed.setNR_Placa(request.getParameter("FT_NR_Placa"));
  	ed.setDM_Situacao(request.getParameter("FT_DM_Situacao"));
  	//ed.setOID_Embarque(new Long(request.getParameter("oid_Embarque")).longValue());
  	
  	ed.setOID_Manifesto(request.getParameter("oid_Manifesto_Internacional"));

  	ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));
  	String nr_Embarque = request.getParameter("FT_NR_Embarque");
  	if (String.valueOf(nr_Embarque) != null &&
  			!String.valueOf(nr_Embarque).equals("") &&
			!String.valueOf(nr_Embarque).equals("null")){
  		ed.setNR_Embarque(new Long(nr_Embarque).longValue());
  	}

  	String OID_Cidade_Destino = request.getParameter("oid_Cidade_Destino");
  	if (String.valueOf(OID_Cidade_Destino) != null &&
  			!String.valueOf(OID_Cidade_Destino).equals("") &&
			!String.valueOf(OID_Cidade_Destino).equals("null")){
  		ed.setOID_Cidade_Destino(new Long(OID_Cidade_Destino).longValue());
  	}

  	String OID_Cidade_Origem = request.getParameter("oid_Cidade_Origem");
  	if (String.valueOf(OID_Cidade_Origem) != null &&
  			!String.valueOf(OID_Cidade_Origem).equals("") &&
			!String.valueOf(OID_Cidade_Origem).equals("null")){
  		ed.setOID_Cidade_Origem(new Long(OID_Cidade_Origem).longValue());
  	}

  	EmbarqueRN geRN = new EmbarqueRN();
  	geRN.geraControleViagem(ed, response);
  }

}
