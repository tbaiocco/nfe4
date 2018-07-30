package com.master.iu;

import javax.servlet.http.*;
import com.master.rn.Conhecimento_InternacionalRN;
import com.master.root.CidadeBean;
import com.master.root.ClienteBean;
import com.master.ed.Conhecimento_InternacionalED;
import com.master.util.Excecoes;
import com.master.util.Mensagens;


import java.util.*;

import com.master.util.Data;

import com.master.util.*;
import com.master.ed.ConhecimentoED;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class con009Bean {

  public Conhecimento_InternacionalED inclui(HttpServletRequest request)throws Excecoes{

    try{
      Conhecimento_InternacionalRN Conhecimento_Internacionalrn = new Conhecimento_InternacionalRN();
      Conhecimento_InternacionalED ed = new Conhecimento_InternacionalED();
      ClienteBean ClienteVolta = new ClienteBean();
      
      ed.setOID_Produto_Custo(request.getParameter("oid_Produto_Custo"));
      ed.setOID_Unidade_Negocio(request.getParameter("oid_Unidade_Negocio"));

      ed.setDM_Isento_Seguro(request.getParameter("FT_DM_Isento_Seguro"));
      ed.setDM_Responsavel_Cobranca(request.getParameter("FT_DM_Responsavel_Cobranca"));
      ed.setNR_Fatura(request.getParameter("FT_NR_Fatura"));
      
      ArrayList faturas = new ArrayList();
      if(JavaUtil.doValida(request.getParameter("FT_NR_Fatura1"))){
// System.out.println("ADD fatura = "+request.getParameter("FT_NR_Fatura1"));
          faturas.add(request.getParameter("FT_NR_Fatura1"));
      }
      if(JavaUtil.doValida(request.getParameter("FT_NR_Fatura2"))){
// System.out.println("ADD fatura = "+request.getParameter("FT_NR_Fatura2"));
          faturas.add(request.getParameter("FT_NR_Fatura2"));
      }
      if(JavaUtil.doValida(request.getParameter("FT_NR_Fatura3"))){
// System.out.println("ADD fatura = "+request.getParameter("FT_NR_Fatura3"));
          faturas.add(request.getParameter("FT_NR_Fatura3"));
      }
      if(JavaUtil.doValida(request.getParameter("FT_NR_Fatura4"))){
// System.out.println("ADD fatura = "+request.getParameter("FT_NR_Fatura4"));
          faturas.add(request.getParameter("FT_NR_Fatura4"));
      }
      if(JavaUtil.doValida(request.getParameter("FT_NR_Fatura5"))){
// System.out.println("ADD fatura = "+request.getParameter("FT_NR_Fatura5"));
          faturas.add(request.getParameter("FT_NR_Fatura5"));
      }
      ed.setFaturas(faturas);

      ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));
      
      if (request.getParameter("acao").equals("IC")) ed.setDT_Emissao(Data.getDataDMY());
      
      ed.setDT_Conversao(request.getParameter("FT_DT_Conversao"));
      
      ed.setOID_Cidade(new Long(request.getParameter("oid_Cidade_Origem")).longValue());
      ed.setNM_Cidade_Estado_Pais_Emissao(request.getParameter("FT_NM_Cidade_Origem"));
      
      ed.setOID_Cidade_Destino(new Long(request.getParameter("oid_Cidade_Destino")).longValue());
      ed.setNM_Cidade_Estado_Pais_Entrega(request.getParameter("FT_NM_Cidade_Destino"));
      
      ed.setOID_Cidade_Embarque(new Long(request.getParameter("oid_Cidade_Embarque")).longValue());
      ed.setNM_Cidade_Estado_Pais_Embarque(request.getParameter("FT_NM_Cidade_Embarque"));
      
      ed.setOID_Modal(new Long(request.getParameter("oid_Modal")).longValue());
      
      ed.setOID_Pessoa(request.getParameter("oid_Pessoa_Remetente"));
      ed.setNM_Remetente(request.getParameter("FT_NM_Razao_Social_Remetente"));
      ed.setNR_CNPJ_Remetente_Complementar(request.getParameter("FT_NR_CNPJ_CPF_Remetente"));
      ed.setNM_Razao_Social_Remetente("FT_NM_Razao_Social_Remetente");
      ed.setNM_Endereco_Remetente(request.getParameter("FT_NM_Endereco_Remetente"));
      ed.setNM_Endereco_Remetente2(request.getParameter("FT_NM_Endereco_Remetente2"));
      ed.setNM_Cidade_Estado_Pais_Remetente(request.getParameter("FT_NM_Cidade_Estado_Pais_Remetente"));

      ed.setOID_Pessoa_Destinatario(request.getParameter("oid_Pessoa_Destinatario"));
      ed.setNM_Destinatario(request.getParameter("FT_NM_Razao_Social_Destinatario"));
      ed.setNR_CNPJ_Destinatario_Complementar(request.getParameter("FT_NR_CNPJ_CPF_Destinatario"));
      ed.setNM_Endereco_Destinatario(request.getParameter("FT_NM_Endereco_Destinatario"));
      ed.setNM_Cidade_Estado_Pais_Destinatario(request.getParameter("FT_NM_Cidade_Estado_Pais_Destinatario"));
      
      ed.setOID_Pessoa_Consignatario(request.getParameter("oid_Pessoa_Consignatario"));
      ed.setNM_Consignatario(request.getParameter("FT_NM_Razao_Social_Consignatario"));
      ed.setNM_Endereco_Consignatario(request.getParameter("FT_NM_Endereco_Consignatario"));
      ed.setNM_Cidade_Estado_Pais_Consignatario(request.getParameter("FT_NM_Cidade_Estado_Pais_Consignatario"));
      ed.setNR_CPF_CNPJ_Consignatario_Complementar(request.getParameter("FT_NR_CNPJ_CPF_Consignatario_Editado"));
      
      ed.setOID_Pessoa_Notificar(request.getParameter("oid_Pessoa_Notificar"));
      ed.setNM_Notificar(request.getParameter("FT_NM_Razao_Social_Notificar"));
      ed.setNM_Endereco_Notificar(request.getParameter("FT_NM_Endereco_Notificar"));
      ed.setNM_Cidade_Estado_Pais_Notificar(request.getParameter("FT_NM_Cidade_Estado_Pais_Notificar"));
      ed.setNR_CPF_CNPJ_Notificar_Complementar(request.getParameter("FT_NR_CNPJ_CPF_Notificar_Editado"));

      ed.setOID_Pessoa_Cotacao(request.getParameter("oid_Pessoa_Cotacao"));
      ed.setNM_Cotacao(request.getParameter("FT_NM_Razao_Social_Cotacao"));
      
      ed.setOID_Pessoa_Devedor_Exportador(request.getParameter("oid_Pessoa_Devedor_Exportador"));
      ed.setNM_Devedor_Exportador(request.getParameter("FT_NM_Razao_Social_Devedor_Exportador"));

      ed.setOID_Pessoa_Devedor_Importador(request.getParameter("oid_Pessoa_Devedor_Importador"));
      ed.setNM_Devedor_Importador(request.getParameter("FT_NM_Razao_Social_Devedor_Importador"));
      
      ed.setOID_Produto(new Long(request.getParameter("oid_Produto")).longValue());
      
      ed.setOID_Unidade_Origem(new Integer(request.getParameter("oid_Unidade_Origem")).intValue());
      ed.setOID_Unidade_Destino(new Integer(request.getParameter("oid_Unidade_Destino")).intValue());
      ed.setOID_Unidade_Fronteira(new Integer(request.getParameter("oid_Unidade_Fronteira")).intValue());
      
      ed.setTX_Observacao1(request.getParameter("FT_TX_Observacao"));
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
      
      ed.setTX_Documentos1(request.getParameter("FT_TX_Documentos"));
      ed.setTX_Documentos2(request.getParameter("FT_TX_Documentos2"));
      ed.setTX_Documentos3(request.getParameter("FT_TX_Documentos3"));
      ed.setTX_Documentos4(request.getParameter("FT_TX_Documentos4"));
      ed.setTX_Documentos5(request.getParameter("FT_TX_Documentos5"));
      ed.setTX_Documentos6(request.getParameter("FT_TX_Documentos6"));

      ed.setTX_Alfandega1(request.getParameter("FT_TX_Alfandega"));
      ed.setTX_Alfandega2(request.getParameter("FT_TX_Alfandega2"));
      ed.setTX_Alfandega3(request.getParameter("FT_TX_Alfandega3"));
      ed.setTX_Alfandega4(request.getParameter("FT_TX_Alfandega4"));
      ed.setTX_Alfandega5(request.getParameter("FT_TX_Alfandega5"));
      ed.setTX_Alfandega6(request.getParameter("FT_TX_Alfandega6"));
      
      ed.setTX_Declaracao1(request.getParameter("FT_TX_Declaracao"));
      ed.setTX_Declaracao2(request.getParameter("FT_TX_Declaracao2"));
      ed.setTX_Declaracao3(request.getParameter("FT_TX_Declaracao3"));
      ed.setTX_Declaracao4(request.getParameter("FT_TX_Declaracao4"));
      ed.setTX_Declaracao5(request.getParameter("FT_TX_Declaracao5"));
      ed.setTX_Declaracao6(request.getParameter("FT_TX_Declaracao6"));
      ed.setTX_Declaracao7(request.getParameter("FT_TX_Declaracao7"));
      ed.setTX_Declaracao8(request.getParameter("FT_TX_Declaracao8"));

      ed.setTX_Remetente(request.getParameter("FT_TX_Remetente"));
      
      ed.setNM_Gasto_Remetente1(request.getParameter("FT_NM_Gasto_Remetente1"));
      ed.setNM_Gasto_Remetente2(request.getParameter("FT_NM_Gasto_Remetente2"));
      ed.setNM_Gasto_Remetente3(request.getParameter("FT_NM_Gasto_Remetente3"));
      ed.setNM_Gasto_Remetente4(request.getParameter("FT_NM_Gasto_Remetente4"));
      
      ed.setNM_Gasto_Destinatario1(request.getParameter("FT_NM_Gasto_Destinatario1"));
      ed.setNM_Gasto_Destinatario2(request.getParameter("FT_NM_Gasto_Destinatario2"));
      ed.setNM_Gasto_Destinatario3(request.getParameter("FT_NM_Gasto_Destinatario3"));
      ed.setNM_Gasto_Destinatario4(request.getParameter("FT_NM_Gasto_Destinatario4"));

      String NR_Volumes = request.getParameter("FT_NR_Volumes");
      if (String.valueOf(NR_Volumes) != null && !String.valueOf(NR_Volumes).equals("")){
        ed.setNR_Volumes(new Double(NR_Volumes).doubleValue());
      }

      String VL_Peso = request.getParameter("FT_VL_Peso");
      if (String.valueOf(VL_Peso) != null && !String.valueOf(VL_Peso).equals("")){
        ed.setVL_Peso(new Double(VL_Peso).doubleValue());
      }

      String VL_Peso_Cubado = request.getParameter("FT_VL_Peso_Cubado");
      if (String.valueOf(VL_Peso_Cubado) != null && !String.valueOf(VL_Peso_Cubado).equals("")){
        ed.setVL_Peso_Cubado(new Double(VL_Peso_Cubado).doubleValue());
      }

      ed.setNR_Fatura(request.getParameter("FT_NR_Fatura"));

      String VL_Frete = request.getParameter("FT_VL_Frete");
      if (String.valueOf(VL_Frete) != null && !String.valueOf(VL_Frete).equals("")){
        ed.setVL_Frete(new Double(VL_Frete).doubleValue());
      }

      String VL_Seguro = request.getParameter("FT_VL_Seguro");
      if (String.valueOf(VL_Seguro) != null && !String.valueOf(VL_Seguro).equals("")){
        ed.setVL_Seguro(new Double(VL_Seguro).doubleValue());
      }
      ed.setNM_Icoterm(request.getParameter("FT_NM_Icoterm"));

      String VL_Mercadoria = request.getParameter("FT_VL_Mercadoria");
      if (String.valueOf(VL_Mercadoria) != null && !String.valueOf(VL_Mercadoria).equals("")){
        ed.setVL_Mercadoria(new Double(VL_Mercadoria).doubleValue());
      }

      String VL_Nota_Fiscal = request.getParameter("FT_VL_Nota_Fiscal");
      if (String.valueOf(VL_Nota_Fiscal) != null && !String.valueOf(VL_Nota_Fiscal).equals("")){
        ed.setVL_Nota_Fiscal(new Double(VL_Nota_Fiscal).doubleValue());
      }

      String VL_Reembolso = request.getParameter("FT_VL_Reembolso");
      if (String.valueOf(VL_Reembolso) != null && !String.valueOf(VL_Reembolso).equals("")){
        ed.setVL_Reembolso(new Double(VL_Reembolso).doubleValue());
      }
      
      String VL_Dolar = request.getParameter("FT_VL_Dolar");
      if (String.valueOf(VL_Dolar) != null && !String.valueOf(VL_Dolar).equals("")){
        ed.setVL_Dolar(new Double(VL_Dolar).doubleValue());
      }

      String VL_Gasto_Remetente1 = request.getParameter("FT_VL_Gasto_Remetente1");
      if (String.valueOf(VL_Gasto_Remetente1) != null && !String.valueOf(VL_Gasto_Remetente1).equals("")){
        ed.setVL_Gasto_Remetente1(new Double(VL_Gasto_Remetente1).doubleValue());
      }

      String VL_Gasto_Remetente2 = request.getParameter("FT_VL_Gasto_Remetente2");
      if (String.valueOf(VL_Gasto_Remetente2) != null && !String.valueOf(VL_Gasto_Remetente2).equals("")){
        ed.setVL_Gasto_Remetente2(new Double(VL_Gasto_Remetente2).doubleValue());
      }
      
      String VL_Gasto_Remetente3 = request.getParameter("FT_VL_Gasto_Remetente3");
      if (String.valueOf(VL_Gasto_Remetente3) != null && !String.valueOf(VL_Gasto_Remetente3).equals("")){
        ed.setVL_Gasto_Remetente3(new Double(VL_Gasto_Remetente3).doubleValue());
      }
      
      String VL_Gasto_Remetente4 = request.getParameter("FT_VL_Gasto_Remetente4");
      if (String.valueOf(VL_Gasto_Remetente4) != null && !String.valueOf(VL_Gasto_Remetente4).equals("")){
        ed.setVL_Gasto_Remetente4(new Double(VL_Gasto_Remetente4).doubleValue());
      }
      
      String VL_Gasto_Destinatario1 = request.getParameter("FT_VL_Gasto_Destinatario1");
      if (String.valueOf(VL_Gasto_Destinatario1) != null && !String.valueOf(VL_Gasto_Destinatario1).equals("")){
        ed.setVL_Gasto_Destinatario1(new Double(VL_Gasto_Destinatario1).doubleValue());
      }
      
      String VL_Gasto_Destinatario2 = request.getParameter("FT_VL_Gasto_Destinatario2");
      if (String.valueOf(VL_Gasto_Destinatario2) != null && !String.valueOf(VL_Gasto_Destinatario2).equals("")){
        ed.setVL_Gasto_Destinatario2(new Double(VL_Gasto_Destinatario2).doubleValue());
      }
      
      String VL_Gasto_Destinatario3 = request.getParameter("FT_VL_Gasto_Destinatario3");
      if (String.valueOf(VL_Gasto_Destinatario3) != null && !String.valueOf(VL_Gasto_Destinatario3).equals("")){
        ed.setVL_Gasto_Destinatario3(new Double(VL_Gasto_Destinatario3).doubleValue());
      }
      
      String VL_Gasto_Destinatario4 = request.getParameter("FT_VL_Gasto_Destinatario4");
      if (String.valueOf(VL_Gasto_Destinatario4) != null && !String.valueOf(VL_Gasto_Destinatario4).equals("")){
        ed.setVL_Gasto_Destinatario4(new Double(VL_Gasto_Destinatario4).doubleValue());
      }

// System.out.println("request >"+request.getParameter("oid_Tabela_Frete")+"<");
      String oid_Tabela_Frete = request.getParameter("oid_Tabela_Frete");
      if (JavaUtil.doValida(oid_Tabela_Frete)) {
          ed.setOid_Tabela_Frete(Long.parseLong(oid_Tabela_Frete));
      }
// System.out.println("ed >"+ed.getOid_Tabela_Frete()+"<");

      //TEO 18/04/05
      String NR_Volumes_Editado = request.getParameter("FT_NR_Volumes_Editado");
      if (String.valueOf(NR_Volumes_Editado) != null && !String.valueOf(NR_Volumes_Editado).equals("")){
        ed.setNR_Volumes_Editado(NR_Volumes_Editado);
      }
      String NR_Volumes_Observacao = request.getParameter("FT_NR_Volumes_Observacao");
      if (String.valueOf(NR_Volumes_Observacao) != null && !String.valueOf(NR_Volumes_Observacao).equals("")){
        ed.setNR_Volumes_Observacao(Long.parseLong(NR_Volumes_Observacao));
      }
      String VL_Peso_Editado = request.getParameter("FT_VL_Peso_Editado");
      if (String.valueOf(VL_Peso_Editado) != null && !String.valueOf(VL_Peso_Editado).equals("")){
        ed.setVL_Peso_Editado(VL_Peso_Editado);
      }
      String VL_Peso_Cubado_Editado = request.getParameter("FT_VL_Peso_Cubado_Editado");
      if (String.valueOf(VL_Peso_Cubado_Editado) != null && !String.valueOf(VL_Peso_Cubado_Editado).equals("")){
        ed.setVL_Peso_Cubado_Editado(VL_Peso_Cubado_Editado);
      }
      String VL_Frete_Editado = request.getParameter("FT_VL_Frete_Editado");
      if (String.valueOf(VL_Frete_Editado) != null && !String.valueOf(VL_Frete_Editado).equals("")){
        ed.setVL_Frete_Editado(VL_Frete_Editado);
      }
      String VL_Mercadoria_Editado = request.getParameter("FT_VL_Mercadoria_Editado");
      if (String.valueOf(VL_Mercadoria_Editado) != null && !String.valueOf(VL_Mercadoria_Editado).equals("")){
        ed.setVL_Mercadoria_Editado(VL_Mercadoria_Editado);
      }
      
      String c15_VL_Frete_Peso = request.getParameter("c15_VL_Frete_Peso");
      if (String.valueOf(c15_VL_Frete_Peso) != null && !String.valueOf(c15_VL_Frete_Peso).equals("")){
        ed.setC15_VL_Frete_Peso(new Double(c15_VL_Frete_Peso).doubleValue());
      }
      String c15_VL_Ad_Valorem = request.getParameter("c15_VL_Ad_Valorem");
      if (String.valueOf(c15_VL_Ad_Valorem) != null && !String.valueOf(c15_VL_Ad_Valorem).equals("")){
        ed.setC15_VL_Ad_Valorem(new Double(c15_VL_Ad_Valorem).doubleValue());
      }
      String c15_VL_Taxas = request.getParameter("c15_VL_Taxas");
      if (String.valueOf(c15_VL_Taxas) != null && !String.valueOf(c15_VL_Taxas).equals("")){
        ed.setC15_VL_Taxas(new Double(c15_VL_Taxas).doubleValue());
      }
      String c15_VL_Outros = request.getParameter("c15_VL_Outros");
      if (String.valueOf(c15_VL_Outros) != null && !String.valueOf(c15_VL_Outros).equals("")){
        ed.setC15_VL_Outros(new Double(c15_VL_Outros).doubleValue());
      }
      String c15_VL_Total = request.getParameter("c15_VL_Total");
      if (String.valueOf(c15_VL_Total) != null && !String.valueOf(c15_VL_Total).equals("")){
        ed.setC15_VL_Total(new Double(c15_VL_Total).doubleValue());
      }
      
      ed.setDM_Veiculo_Novo(request.getParameter("FT_DM_Veiculo_Novo"));
      String VL_RCTRC = request.getParameter("FT_VL_RCTRC");
      if (String.valueOf(VL_RCTRC) != null && !String.valueOf(VL_RCTRC).equals("")){
        ed.setVL_RCTRC(new Double(VL_RCTRC).doubleValue());
      }
      String VL_Desconto_RCTRC = request.getParameter("FT_VL_Desconto_RCTRC");
      if (String.valueOf(VL_Desconto_RCTRC) != null && !String.valueOf(VL_Desconto_RCTRC).equals("")){
        ed.setVL_Desconto_RCTRC(new Double(VL_Desconto_RCTRC).doubleValue());
      }
      String VL_RCTR_VI = request.getParameter("FT_VL_RCTR_VI");
      if (String.valueOf(VL_RCTR_VI) != null && !String.valueOf(VL_RCTR_VI).equals("")){
        ed.setVL_RCTR_VI(new Double(VL_RCTR_VI).doubleValue());
      }
      String VL_RCTR_DC = request.getParameter("FT_VL_RCTR_DC");
      if (String.valueOf(VL_RCTR_DC) != null && !String.valueOf(VL_RCTR_DC).equals("")){
        ed.setVL_RCTR_DC(new Double(VL_RCTR_DC).doubleValue());
      }
      
      String NR_Coleta = request.getParameter("FT_NR_Coleta");
      if (String.valueOf(NR_Coleta) != null && !String.valueOf(NR_Coleta).equals("")){
        ed.setOID_Coleta(new Long(NR_Coleta).longValue());
      }
      
      String PC_Exportador = request.getParameter("FT_PC_Exportador");
      if (String.valueOf(PC_Exportador) != null && !String.valueOf(PC_Exportador).equals("")){
        ed.setPE_Exportador(new Double(PC_Exportador).doubleValue());
      }
      
      ed.setOID_Vendedor(request.getParameter("oid_Vendedor"));

      return Conhecimento_Internacionalrn.inclui(ed);
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
      Conhecimento_InternacionalRN Conhecimento_Internacionalrn = new Conhecimento_InternacionalRN();
      Conhecimento_InternacionalED ed = new Conhecimento_InternacionalED();
      ed.setOID_Produto_Custo(request.getParameter("oid_Produto_Custo"));
      ed.setOID_Unidade_Negocio(request.getParameter("oid_Unidade_Negocio"));

      ed.setNR_Fatura(request.getParameter("FT_NR_Fatura"));
      ed.setOID_Conhecimento(request.getParameter("oid_Conhecimento"));
      ed.setDM_Isento_Seguro(request.getParameter("FT_DM_Isento_Seguro"));
      ed.setDM_Responsavel_Cobranca(request.getParameter("FT_DM_Responsavel_Cobranca"));
      ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));
      ed.setDT_Conversao(request.getParameter("FT_DT_Conversao"));
      ed.setOID_Cidade(new Long(request.getParameter("oid_Cidade_Origem")).longValue());
      ed.setOID_Cidade_Destino(new Long(request.getParameter("oid_Cidade_Destino")).longValue());
      ed.setOID_Cidade_Embarque(new Long(request.getParameter("oid_Cidade_Embarque")).longValue());
      ed.setOID_Modal(new Long(request.getParameter("oid_Modal")).longValue());
      
      ed.setOID_Produto(new Long(request.getParameter("oid_Produto")).longValue());
      
      ed.setOID_Unidade_Origem(new Integer(request.getParameter("oid_Unidade_Origem")).intValue());
      ed.setOID_Unidade_Destino(new Integer(request.getParameter("oid_Unidade_Destino")).intValue());
      //ed.setOID_Unidade_Fronteira(new Integer(request.getParameter("oid_Unidade_Fronteira")).intValue());
      
      ed.setNM_Cidade_Estado_Pais_Emissao(request.getParameter("FT_NM_Cidade_Origem"));
      ed.setNM_Cidade_Estado_Pais_Entrega(request.getParameter("FT_NM_Cidade_Destino"));
      ed.setNM_Cidade_Estado_Pais_Embarque(request.getParameter("FT_NM_Cidade_Embarque"));
      
      ed.setTX_Observacao1(request.getParameter("FT_TX_Observacao"));
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
      
      ed.setTX_Documentos1(request.getParameter("FT_TX_Documentos"));
      ed.setTX_Documentos2(request.getParameter("FT_TX_Documentos2"));
      ed.setTX_Documentos3(request.getParameter("FT_TX_Documentos3"));
      ed.setTX_Documentos4(request.getParameter("FT_TX_Documentos4"));
      ed.setTX_Documentos5(request.getParameter("FT_TX_Documentos5"));
      ed.setTX_Documentos6(request.getParameter("FT_TX_Documentos6"));
      
      ed.setTX_Alfandega1(request.getParameter("FT_TX_Alfandega"));
      ed.setTX_Alfandega2(request.getParameter("FT_TX_Alfandega2"));
      ed.setTX_Alfandega3(request.getParameter("FT_TX_Alfandega3"));
      ed.setTX_Alfandega4(request.getParameter("FT_TX_Alfandega4"));
      ed.setTX_Alfandega5(request.getParameter("FT_TX_Alfandega5"));
      ed.setTX_Alfandega6(request.getParameter("FT_TX_Alfandega6"));
      
      ed.setTX_Declaracao1(request.getParameter("FT_TX_Declaracao"));
      ed.setTX_Declaracao2(request.getParameter("FT_TX_Declaracao2"));
      ed.setTX_Declaracao3(request.getParameter("FT_TX_Declaracao3"));
      ed.setTX_Declaracao4(request.getParameter("FT_TX_Declaracao4"));
      ed.setTX_Declaracao5(request.getParameter("FT_TX_Declaracao5"));
      ed.setTX_Declaracao6(request.getParameter("FT_TX_Declaracao6"));
      ed.setTX_Declaracao7(request.getParameter("FT_TX_Declaracao7"));
      ed.setTX_Declaracao8(request.getParameter("FT_TX_Declaracao8"));
      
      ed.setTX_Remetente(request.getParameter("FT_TX_Remetente"));
      
      ed.setNM_Gasto_Remetente1(request.getParameter("FT_NM_Gasto_Remetente1"));
      ed.setNM_Gasto_Remetente2(request.getParameter("FT_NM_Gasto_Remetente2"));
      ed.setNM_Gasto_Remetente3(request.getParameter("FT_NM_Gasto_Remetente3"));
      ed.setNM_Gasto_Remetente4(request.getParameter("FT_NM_Gasto_Remetente4"));
      
      ed.setNM_Gasto_Destinatario1(request.getParameter("FT_NM_Gasto_Destinatario1"));
      ed.setNM_Gasto_Destinatario2(request.getParameter("FT_NM_Gasto_Destinatario2"));
      ed.setNM_Gasto_Destinatario3(request.getParameter("FT_NM_Gasto_Destinatario3"));
      ed.setNM_Gasto_Destinatario4(request.getParameter("FT_NM_Gasto_Destinatario4"));
      
//      ed.setNM_Endereco_Remetente2(request.getParameter("FT_NM_Endereco_Remetente2"));
      
      ed.setOID_Pessoa(request.getParameter("oid_Pessoa_Remetente"));
      ed.setNM_Endereco_Remetente(request.getParameter("FT_NM_Endereco_Remetente"));
      ed.setNM_Remetente(request.getParameter("FT_NM_Razao_Social_Remetente"));
      ed.setNR_CNPJ_Remetente_Complementar(request.getParameter("FT_NR_CNPJ_CPF_Remetente"));
      ed.setNM_Cidade_Estado_Pais_Remetente(request.getParameter("FT_NM_Cidade_Estado_Pais_Remetente"));
      
      ed.setOID_Pessoa_Destinatario(request.getParameter("oid_Pessoa_Destinatario"));
      ed.setNM_Endereco_Destinatario(request.getParameter("FT_NM_Endereco_Destinatario"));
      ed.setNM_Destinatario(request.getParameter("FT_NM_Razao_Social_Destinatario"));
      ed.setNR_CNPJ_Destinatario_Complementar(request.getParameter("FT_NR_CNPJ_CPF_Destinatario"));
      ed.setNM_Cidade_Estado_Pais_Destinatario(request.getParameter("FT_NM_Cidade_Estado_Pais_Destinatario"));
      
      ed.setOID_Pessoa_Consignatario(request.getParameter("oid_Pessoa_Consignatario"));
      ed.setNM_Endereco_Consignatario(request.getParameter("FT_NM_Endereco_Consignatario"));
      ed.setNM_Consignatario(request.getParameter("FT_NM_Razao_Social_Consignatario"));
      ed.setNM_Cidade_Estado_Pais_Consignatario(request.getParameter("FT_NM_Cidade_Estado_Pais_Consignatario"));
      ed.setNR_CPF_CNPJ_Consignatario_Complementar(request.getParameter("FT_NR_CNPJ_CPF_Consignatario_Editado"));

      ed.setOID_Pessoa_Notificar(request.getParameter("oid_Pessoa_Notificar"));
      ed.setNM_Endereco_Notificar(request.getParameter("FT_NM_Endereco_Notificar"));
      ed.setNM_Notificar(request.getParameter("FT_NM_Razao_Social_Notificar"));
      ed.setNM_Cidade_Estado_Pais_Notificar(request.getParameter("FT_NM_Cidade_Estado_Pais_Notificar"));
      ed.setNR_CPF_CNPJ_Notificar_Complementar(request.getParameter("FT_NR_CNPJ_CPF_Notificar_Editado"));
      
      ed.setOID_Pessoa_Cotacao(request.getParameter("oid_Pessoa_Cotacao"));
      ed.setNM_Cotacao(request.getParameter("FT_NM_Razao_Social_Cotacao"));
      
      ed.setOID_Pessoa_Devedor_Exportador(request.getParameter("oid_Pessoa_Devedor_Exportador"));
      ed.setNM_Devedor_Exportador(request.getParameter("FT_NM_Razao_Social_Devedor_Exportador"));

      ed.setOID_Pessoa_Devedor_Importador(request.getParameter("oid_Pessoa_Devedor_Importador"));
      ed.setNM_Devedor_Importador(request.getParameter("FT_NM_Razao_Social_Devedor_Importador"));
      
      String NR_Volumes = request.getParameter("FT_NR_Volumes");
      if (String.valueOf(NR_Volumes) != null &&
        !String.valueOf(NR_Volumes).equals("")){
        ed.setNR_Volumes(new Double(NR_Volumes).doubleValue());
      }

      String NR_Original = request.getParameter("FT_NR_Original");
      if (String.valueOf(NR_Original) != null &&
        !String.valueOf(NR_Original).equals("")){
        ed.setNR_Original(new Long(NR_Original).longValue());
      }

      String VL_Peso = request.getParameter("FT_VL_Peso");
      if (String.valueOf(VL_Peso) != null &&
        !String.valueOf(VL_Peso).equals("")){
        ed.setVL_Peso(new Double(VL_Peso).doubleValue());
      }

      String VL_Peso_Cubado = request.getParameter("FT_VL_Peso_Cubado");
      if (String.valueOf(VL_Peso_Cubado) != null &&
        !String.valueOf(VL_Peso_Cubado).equals("")){
        ed.setVL_Peso_Cubado(new Double(VL_Peso_Cubado).doubleValue());
      }

      String VL_Frete = request.getParameter("FT_VL_Frete");
      if (String.valueOf(VL_Frete) != null &&
        !String.valueOf(VL_Frete).equals("")){
        ed.setVL_Frete(new Double(VL_Frete).doubleValue());
      }

      String VL_Nota_Fiscal = request.getParameter("FT_VL_Nota_Fiscal");
      if (String.valueOf(VL_Nota_Fiscal) != null &&
        !String.valueOf(VL_Nota_Fiscal).equals("")){
        ed.setVL_Nota_Fiscal(new Double(VL_Nota_Fiscal).doubleValue());
      }

      String VL_Reembolso = request.getParameter("FT_VL_Reembolso");
      if (String.valueOf(VL_Reembolso) != null &&
        !String.valueOf(VL_Reembolso).equals("")){
        ed.setVL_Reembolso(new Double(VL_Reembolso).doubleValue());
      }

      String VL_Dolar = request.getParameter("FT_VL_Dolar");
      if (String.valueOf(VL_Dolar) != null &&
        !String.valueOf(VL_Dolar).equals("")){
        ed.setVL_Dolar(new Double(VL_Dolar).doubleValue());
      }
      
      String VL_Gasto_Remetente1 = request.getParameter("FT_VL_Gasto_Remetente1");
      if (String.valueOf(VL_Gasto_Remetente1) != null &&
        !String.valueOf(VL_Gasto_Remetente1).equals("")){
        ed.setVL_Gasto_Remetente1(new Double(VL_Gasto_Remetente1).doubleValue());
      }

      String VL_Gasto_Remetente2 = request.getParameter("FT_VL_Gasto_Remetente2");
      if (String.valueOf(VL_Gasto_Remetente2) != null &&
        !String.valueOf(VL_Gasto_Remetente2).equals("")){
        ed.setVL_Gasto_Remetente2(new Double(VL_Gasto_Remetente2).doubleValue());
      }
      String VL_Gasto_Remetente3 = request.getParameter("FT_VL_Gasto_Remetente3");
      if (String.valueOf(VL_Gasto_Remetente3) != null &&
        !String.valueOf(VL_Gasto_Remetente3).equals("")){
        ed.setVL_Gasto_Remetente3(new Double(VL_Gasto_Remetente3).doubleValue());
      }
      String VL_Gasto_Remetente4 = request.getParameter("FT_VL_Gasto_Remetente4");
      if (String.valueOf(VL_Gasto_Remetente4) != null &&
        !String.valueOf(VL_Gasto_Remetente4).equals("")){
        ed.setVL_Gasto_Remetente4(new Double(VL_Gasto_Remetente4).doubleValue());
      }
      String VL_Gasto_Destinatario1 = request.getParameter("FT_VL_Gasto_Destinatario1");
      if (String.valueOf(VL_Gasto_Destinatario1) != null &&
        !String.valueOf(VL_Gasto_Destinatario1).equals("")){
        ed.setVL_Gasto_Destinatario1(new Double(VL_Gasto_Destinatario1).doubleValue());
      }
      String VL_Gasto_Destinatario2 = request.getParameter("FT_VL_Gasto_Destinatario2");
      if (String.valueOf(VL_Gasto_Destinatario2) != null &&
        !String.valueOf(VL_Gasto_Destinatario2).equals("")){
        ed.setVL_Gasto_Destinatario2(new Double(VL_Gasto_Destinatario2).doubleValue());
      }
      String VL_Gasto_Destinatario3 = request.getParameter("FT_VL_Gasto_Destinatario3");
      if (String.valueOf(VL_Gasto_Destinatario3) != null &&
        !String.valueOf(VL_Gasto_Destinatario3).equals("")){
        ed.setVL_Gasto_Destinatario3(new Double(VL_Gasto_Destinatario3).doubleValue());
      }
      String VL_Gasto_Destinatario4 = request.getParameter("FT_VL_Gasto_Destinatario4");
      if (String.valueOf(VL_Gasto_Destinatario4) != null &&
        !String.valueOf(VL_Gasto_Destinatario4).equals("")){
        ed.setVL_Gasto_Destinatario4(new Double(VL_Gasto_Destinatario4).doubleValue());
      }

      String VL_Seguro = request.getParameter("FT_VL_Seguro");
      if (String.valueOf(VL_Seguro) != null &&
        !String.valueOf(VL_Seguro).equals("")){
        ed.setVL_Seguro(new Double(VL_Seguro).doubleValue());
      }
      ed.setNM_Icoterm(request.getParameter("FT_NM_Icoterm"));

      String VL_Mercadoria = request.getParameter("FT_VL_Mercadoria");
      if (String.valueOf(VL_Mercadoria) != null &&
        !String.valueOf(VL_Mercadoria).equals("")){
        ed.setVL_Mercadoria(new Double(VL_Mercadoria).doubleValue());
      }
// System.out.println("request >"+request.getParameter("oid_Tabela_Frete")+"<");
      String oid_Tabela_Frete = request.getParameter("oid_Tabela_Frete");
      if (JavaUtil.doValida(oid_Tabela_Frete)) {
          ed.setOid_Tabela_Frete(Long.parseLong(oid_Tabela_Frete));
      }
// System.out.println("ed >"+ed.getOid_Tabela_Frete()+"<");

//    TEO 18/04/05
      String NR_Volumes_Editado = request.getParameter("FT_NR_Volumes_Editado");
      if (String.valueOf(NR_Volumes_Editado) != null && !String.valueOf(NR_Volumes_Editado).equals("")){
        ed.setNR_Volumes_Editado(NR_Volumes_Editado);
      }
      String NR_Volumes_Observacao = request.getParameter("FT_NR_Volumes_Observacao");
      if (String.valueOf(NR_Volumes_Observacao) != null && !String.valueOf(NR_Volumes_Observacao).equals("")){
        ed.setNR_Volumes_Observacao(Long.parseLong(NR_Volumes_Observacao));
      }
      String VL_Peso_Editado = request.getParameter("FT_VL_Peso_Editado");
      if (String.valueOf(VL_Peso_Editado) != null && !String.valueOf(VL_Peso_Editado).equals("")){
        ed.setVL_Peso_Editado(VL_Peso_Editado);
      }
      String VL_Peso_Cubado_Editado = request.getParameter("FT_VL_Peso_Cubado_Editado");
      if (String.valueOf(VL_Peso_Cubado_Editado) != null && !String.valueOf(VL_Peso_Cubado_Editado).equals("")){
        ed.setVL_Peso_Cubado_Editado(VL_Peso_Cubado_Editado);
      }
      String VL_Frete_Editado = request.getParameter("FT_VL_Frete_Editado");
      if (String.valueOf(VL_Frete_Editado) != null && !String.valueOf(VL_Frete_Editado).equals("")){
        ed.setVL_Frete_Editado(VL_Frete_Editado);
      }
      String VL_Mercadoria_Editado = request.getParameter("FT_VL_Mercadoria_Editado");
      if (String.valueOf(VL_Mercadoria_Editado) != null && !String.valueOf(VL_Mercadoria_Editado).equals("")){
        ed.setVL_Mercadoria_Editado(VL_Mercadoria_Editado);
      }
      
      String c15_VL_Frete_Peso = request.getParameter("c15_VL_Frete_Peso");
      if (String.valueOf(c15_VL_Frete_Peso) != null && !String.valueOf(c15_VL_Frete_Peso).equals("")){
        ed.setC15_VL_Frete_Peso(new Double(c15_VL_Frete_Peso).doubleValue());
      }
      String c15_VL_Ad_Valorem = request.getParameter("c15_VL_Ad_Valorem");
      if (String.valueOf(c15_VL_Ad_Valorem) != null && !String.valueOf(c15_VL_Ad_Valorem).equals("")){
        ed.setC15_VL_Ad_Valorem(new Double(c15_VL_Ad_Valorem).doubleValue());
      }
      String c15_VL_Taxas = request.getParameter("c15_VL_Taxas");
      if (String.valueOf(c15_VL_Taxas) != null && !String.valueOf(c15_VL_Taxas).equals("")){
        ed.setC15_VL_Taxas(new Double(c15_VL_Taxas).doubleValue());
      }
      String c15_VL_Outros = request.getParameter("c15_VL_Outros");
      if (String.valueOf(c15_VL_Outros) != null && !String.valueOf(c15_VL_Outros).equals("")){
        ed.setC15_VL_Outros(new Double(c15_VL_Outros).doubleValue());
      }
      String c15_VL_Total = request.getParameter("c15_VL_Total");
      if (String.valueOf(c15_VL_Total) != null && !String.valueOf(c15_VL_Total).equals("")){
        ed.setC15_VL_Total(new Double(c15_VL_Total).doubleValue());
      }
      
      ed.setDM_Veiculo_Novo(request.getParameter("FT_DM_Veiculo_Novo"));
      String VL_RCTRC = request.getParameter("FT_VL_RCTRC");
      if (String.valueOf(VL_RCTRC) != null && !String.valueOf(VL_RCTRC).equals("")){
        ed.setVL_RCTRC(new Double(VL_RCTRC).doubleValue());
      }
      String VL_Desconto_RCTRC = request.getParameter("FT_VL_Desconto_RCTRC");
      if (String.valueOf(VL_Desconto_RCTRC) != null && !String.valueOf(VL_Desconto_RCTRC).equals("")){
        ed.setVL_Desconto_RCTRC(new Double(VL_Desconto_RCTRC).doubleValue());
      }
      String VL_RCTR_VI = request.getParameter("FT_VL_RCTR_VI");
      if (String.valueOf(VL_RCTR_VI) != null && !String.valueOf(VL_RCTR_VI).equals("")){
        ed.setVL_RCTR_VI(new Double(VL_RCTR_VI).doubleValue());
      }
      String VL_RCTR_DC = request.getParameter("FT_VL_RCTR_DC");
      if (String.valueOf(VL_RCTR_DC) != null && !String.valueOf(VL_RCTR_DC).equals("")){
        ed.setVL_RCTR_DC(new Double(VL_RCTR_DC).doubleValue());
      }
      
//      String NR_Coleta = request.getParameter("FT_NR_Coleta");
//      if (String.valueOf(NR_Coleta) != null && !String.valueOf(NR_Coleta).equals("")){
//        ed.setOID_Coleta(new Long(NR_Coleta).longValue());
//      }
      
      String oid_Coleta = request.getParameter("oid_Coleta");
      if (String.valueOf(oid_Coleta) != null && !String.valueOf(oid_Coleta).equals("")){
        ed.setOID_Coleta(new Long(oid_Coleta).longValue());
        new col001Bean().vincula_CTRC(oid_Coleta, ed.getOID_Conhecimento());
      }
      
      String PC_Exportador = request.getParameter("FT_PC_Exportador");
      if (String.valueOf(PC_Exportador) != null && !String.valueOf(PC_Exportador).equals("")){
        ed.setPE_Exportador(new Double(PC_Exportador).doubleValue());
      }
// System.out.println("ed.getPE_Exportador >> "+ed.getPE_Exportador());
      
      ed.setOID_Vendedor(request.getParameter("oid_Vendedor"));

      Conhecimento_Internacionalrn.altera(ed);
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

  public void altera_Pagador(HttpServletRequest request)throws Excecoes{

    try{
      Conhecimento_InternacionalRN Conhecimento_Internacionalrn = new Conhecimento_InternacionalRN();
      Conhecimento_InternacionalED ed = new Conhecimento_InternacionalED();


      ed.setOID_Conhecimento(request.getParameter("OID_Conhecimento"));
      ed.setOID_Pessoa_Pagador(request.getParameter("oid_Pessoa_Pagador"));
      ed.setTX_Observacao1(request.getParameter("FT_TX_Observacao"));

      Conhecimento_Internacionalrn.altera_Pagador(ed);
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
      Conhecimento_InternacionalRN Conhecimento_Internacionalrn = new Conhecimento_InternacionalRN();
      Conhecimento_InternacionalED ed = new Conhecimento_InternacionalED();

      ed.setOID_Conhecimento(request.getParameter("oid_Conhecimento"));

      Conhecimento_Internacionalrn.deleta(ed);
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

  public ArrayList Conhecimento_Internacional_Lista(HttpServletRequest request)throws Excecoes{

      Conhecimento_InternacionalED ed = new Conhecimento_InternacionalED();

      ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));
      ed.setDT_Fim(request.getParameter("FT_DT_Fim"));
      ed.setDT_Conversao(request.getParameter("FT_DT_Conversao"));
      String NR_Conhecimento = request.getParameter("FT_NR_Conhecimento");
      if (JavaUtil.doValida(NR_Conhecimento)){
        ed.setNR_Conhecimento(new Long(NR_Conhecimento).longValue());
      }
      ed.setOID_Pessoa(request.getParameter("oid_Pessoa_Remetente"));
      ed.setOID_Pessoa_Consignatario(request.getParameter("oid_Pessoa_Consignatario"));
      ed.setOID_Pessoa_Destinatario(request.getParameter("oid_Pessoa_Destinatario"));
      String oid_Unidade_Origem = request.getParameter("oid_Unidade_Origem");
      if (JavaUtil.doValida(oid_Unidade_Origem)){
        ed.setOID_Unidade_Origem(new Integer(oid_Unidade_Origem).intValue());
      }
      String oid_Unidade_Destino = request.getParameter("oid_Unidade_Destino");
      if (JavaUtil.doValida(oid_Unidade_Destino)){
        ed.setOID_Unidade_Destino(new Integer(oid_Unidade_Destino).intValue());
      }
      String oid_Unidade_Fronteira = request.getParameter("oid_Unidade_Fronteira");
      if (JavaUtil.doValida(oid_Unidade_Fronteira)){
        ed.setOID_Unidade_Fronteira(new Integer(oid_Unidade_Fronteira).intValue());
      }
      String NR_Fatura = request.getParameter("FT_NR_Fatura");
      if (NR_Fatura != null && NR_Fatura.length() > 0)
      {
        ed.setNR_Fatura(NR_Fatura);
      }
      
      String oid_Coleta = request.getParameter("oid_Coleta");
      if (JavaUtil.doValida(oid_Coleta)){
        ed.setOID_Coleta(new Long(oid_Coleta).longValue());
      }

      ed.setDM_Situacao(request.getParameter("FT_DM_Situacao"));

      return new Conhecimento_InternacionalRN().lista(ed);

  }

  public Conhecimento_InternacionalED getByRecord(HttpServletRequest request)throws Excecoes{

      Conhecimento_InternacionalED ed = new Conhecimento_InternacionalED();

      ed.setOID_Conhecimento(request.getParameter("oid_Conhecimento_Internacional"));
      String NR_Conhecimento = request.getParameter("FT_NR_Conhecimento");
      if (NR_Conhecimento != null && NR_Conhecimento.length() > 0){
        ed.setNR_Conhecimento(new Long(NR_Conhecimento).longValue());
      }
      
      String oid_Unidade_Origem = request.getParameter("oid_Unidade_Origem");
      if (oid_Unidade_Origem != null && oid_Unidade_Origem.length() > 0){
        ed.setOID_Unidade_Origem(new Integer(oid_Unidade_Origem).intValue());
      }
      
      String NR_Fatura = "";
      NR_Fatura="11";
//      NR_Fatura = request.getParameter("FT_NR_Fatura");
// // System.err.println( NR_Fatura);
      if (NR_Fatura != null && NR_Fatura.length() > 0)
      {
        ed.setNR_Fatura(NR_Fatura);
      }
// System.err.println( ed.getOID_Conhecimento());
	  if (JavaUtil.doValida(request.getParameter("oid_Conhecimento")) && !JavaUtil.doValida( ed.getOID_Conhecimento())){
	      ed.setOID_Conhecimento(request.getParameter("oid_Conhecimento"));
	  }
// System.err.println("despois "+ ed.getOID_Conhecimento());

      return new Conhecimento_InternacionalRN().getByRecord(ed);

  }

  public byte[] geraRelConhecInternacionalAntigo(HttpServletRequest request, HttpServletResponse Response)throws Excecoes{
      Conhecimento_InternacionalED ed = new Conhecimento_InternacionalED();

        ed.setOID_Conhecimento(request.getParameter("oid_Conhecimento"));

        ed.setDM_SO(request.getParameter("so"));
  // System.out.println("ALEX "+ed.getDM_SO());

      //    String nr_Conhecimento = request.getParameter("FT_NR_Conhecimento");
//      if (String.valueOf(nr_Conhecimento) != null &&
//        !String.valueOf(nr_Conhecimento).equals("")){
//        ed.setNR_Conhecimento(new Long(nr_Conhecimento).longValue());
//      }

        ed.setNR_Original(1);
        String NR_Original = request.getParameter("FT_NR_Original");
        if (String.valueOf(NR_Original) != null &&
          !String.valueOf(NR_Original).equals("") &&
          !String.valueOf(NR_Original).equals("null")){
          ed.setNR_Original(new Long(NR_Original).longValue());
        }

      Conhecimento_InternacionalRN geRN = new Conhecimento_InternacionalRN();

      byte[] b = geRN.geraRelConhecInternacionalAntigo(ed);
      geRN = null;
      return b;

    }


  public void geraRelConhecInternacional(HttpServletRequest request, HttpServletResponse response)throws Excecoes{
    Conhecimento_InternacionalED ed = new Conhecimento_InternacionalED();

      ed.setOID_Conhecimento(request.getParameter("oid_Conhecimento"));

      ed.setDM_SO(request.getParameter("so"));

    //    String nr_Conhecimento = request.getParameter("FT_NR_Conhecimento");
//    if (String.valueOf(nr_Conhecimento) != null &&
//      !String.valueOf(nr_Conhecimento).equals("")){
//      ed.setNR_Conhecimento(new Long(nr_Conhecimento).longValue());
//    }

      ed.setNR_Original(1);
      String NR_Original = request.getParameter("FT_NR_Original");
      if (String.valueOf(NR_Original) != null &&
        !String.valueOf(NR_Original).equals("") &&
        !String.valueOf(NR_Original).equals("null")){
        ed.setNR_Original(new Long(NR_Original).longValue());
      }
      
      String folha = request.getParameter("FT_Folha");
      if (folha != null && !folha.equals("") &&  !folha.equals("null")){
        ed.setDM_Folha(folha);
      }
      
      String carimbo = request.getParameter("FT_DM_Carimbo");
      if (carimbo != null && !carimbo.equals("") &&  !carimbo.equals("null")){
        ed.setDM_SO(carimbo);
      }

    Conhecimento_InternacionalRN geRN = new Conhecimento_InternacionalRN();

    geRN.geraRelConhecInternacional(ed,response);
    geRN = null;

  }

  public void geraRelatorio(HttpServletRequest req)throws Excecoes{
    Conhecimento_InternacionalED ed = new Conhecimento_InternacionalED();

    /* * * * * *         A T E N Ç Ã O     * * * * * * *
    /* SETAR O ED PARA PESQUISA NO BD
    */


    Conhecimento_InternacionalRN geRN = new Conhecimento_InternacionalRN();
    geRN.geraRelatorio(ed);
  }

  public void cancela(HttpServletRequest request)throws Excecoes{

  	try{
  		Conhecimento_InternacionalRN Conhecimento_Internacionalrn = new Conhecimento_InternacionalRN();
  		Conhecimento_InternacionalED ed = new Conhecimento_InternacionalED();
// // System.out.println(request.getParameter("oid_Conhecimento"));
  		ed.setOID_Conhecimento(request.getParameter("oid_Conhecimento"));

  		Conhecimento_Internacionalrn.cancela(ed);
  	}
  	catch (Excecoes exc){
  		throw exc;
  	}
  	catch(Exception exc){
  		Excecoes excecoes = new Excecoes();
  		excecoes.setClasse(this.getClass().getName());
  		excecoes.setMensagem("erro ao cancelar");
  		excecoes.setMetodo("cancela");
  		excecoes.setExc(exc);
  		throw excecoes;
  	}
  }

  public void geraPre_Faturamento_inter(HttpServletRequest request, HttpServletResponse Response)throws Excecoes{
  	ConhecimentoED ed = new ConhecimentoED();

  	String nr_Conhecimento = request.getParameter("FT_NR_Conhecimento");
  	if (String.valueOf(nr_Conhecimento) != null && !String.valueOf(nr_Conhecimento).equals("")){
  		ed.setNR_Conhecimento(new Long(nr_Conhecimento).longValue());
  	}
  	ed.setOID_Pessoa(request.getParameter("oid_Pessoa_Remetente"));
  	ed.setOID_Pessoa_Consignatario(request.getParameter("oid_Pessoa_Consignatario"));
  	ed.setOID_Pessoa_Destinatario(request.getParameter("oid_Pessoa_Destinatario"));

  	String oid_Pessoa_Pagador = request.getParameter("oid_Pessoa_Pagador");
  	if (String.valueOf(oid_Pessoa_Pagador) != null && !String.valueOf(oid_Pessoa_Pagador).equals("")){
  		ed.setOID_Pessoa_Pagador(oid_Pessoa_Pagador);
  	}

  	String Dt_Emissao_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
  	if (String.valueOf(Dt_Emissao_Inicial) != null && !String.valueOf(Dt_Emissao_Inicial).equals("")){
  		ed.setDt_Emissao_Inicial(Dt_Emissao_Inicial);
  	}

  	String Dt_Emissao_Final = request.getParameter("FT_DT_Emissao_Final");
  	if (String.valueOf(Dt_Emissao_Final) != null && !String.valueOf(Dt_Emissao_Final).equals("")){
  		ed.setDt_Emissao_Final(Dt_Emissao_Final);
  	}

  	String oid_Unidade = request.getParameter("oid_Unidade");
  	if (String.valueOf(oid_Unidade) != null && !String.valueOf(oid_Unidade).equals("")){
  		ed.setOID_Unidade(new Long(oid_Unidade).longValue());
  	}

  	Conhecimento_InternacionalRN geRN = new Conhecimento_InternacionalRN();
  	new EnviaPDF().enviaBytes(request,Response,geRN.geraPre_Faturamento_inter(ed));

  }

  
  public void geraRelCRTEmitidoInter(HttpServletRequest request, HttpServletResponse Response)throws Excecoes{
    	ConhecimentoED ed = new ConhecimentoED();

    	String nr_Conhecimento = request.getParameter("FT_NR_Conhecimento");
    	if (String.valueOf(nr_Conhecimento) != null && !String.valueOf(nr_Conhecimento).equals("")){
    		ed.setNR_Conhecimento(new Long(nr_Conhecimento).longValue());
    	}
    	ed.setOID_Pessoa(request.getParameter("oid_Pessoa_Remetente"));
    	ed.setOID_Pessoa_Consignatario(request.getParameter("oid_Pessoa_Consignatario"));
    	ed.setOID_Pessoa_Destinatario(request.getParameter("oid_Pessoa_Destinatario"));

    	String oid_Pessoa_Pagador = request.getParameter("oid_Pessoa_Pagador");
    	if (String.valueOf(oid_Pessoa_Pagador) != null && !String.valueOf(oid_Pessoa_Pagador).equals("")){
    		ed.setOID_Pessoa_Pagador(oid_Pessoa_Pagador);
    	}

    	String Dt_Emissao_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
    	if (String.valueOf(Dt_Emissao_Inicial) != null && !String.valueOf(Dt_Emissao_Inicial).equals("")){
    		ed.setDt_Emissao_Inicial(Dt_Emissao_Inicial);
    	}

    	String Dt_Emissao_Final = request.getParameter("FT_DT_Emissao_Final");
    	if (String.valueOf(Dt_Emissao_Final) != null && !String.valueOf(Dt_Emissao_Final).equals("")){
    		ed.setDt_Emissao_Final(Dt_Emissao_Final);
    	}

    	String oid_Unidade = request.getParameter("oid_Unidade");
    	if (String.valueOf(oid_Unidade) != null && !String.valueOf(oid_Unidade).equals("")){
    		ed.setOID_Unidade(new Long(oid_Unidade).longValue());
    	}
    	
        ed.setDM_Situacao(request.getParameter("FT_DM_Situacao"));

    	Conhecimento_InternacionalRN geRN = new Conhecimento_InternacionalRN();
    	new EnviaPDF().enviaBytes(request,Response,geRN.geraRelCRTEmitidoInter(ed));

    }
  
  
  public ArrayList Lista_Fatura(HttpServletRequest request)throws Excecoes{

      Conhecimento_InternacionalED ed = new Conhecimento_InternacionalED();

      String oid_Conhecimento = request.getParameter("oid_Conhecimento");
      if (String.valueOf(oid_Conhecimento) != null && !String.valueOf(oid_Conhecimento).equals("")){
        ed.setOID_Conhecimento(oid_Conhecimento);
      }
      String NR_Fatura = request.getParameter("FT_NR_Fatura");
      if (NR_Fatura != null && NR_Fatura.length() > 0)
      {
        ed.setNR_Fatura(NR_Fatura);
      }


      return new Conhecimento_InternacionalRN().lista_fatura(ed);

  }

  public Conhecimento_InternacionalED getByRecord_fatura(HttpServletRequest request)throws Excecoes{

      Conhecimento_InternacionalED ed = new Conhecimento_InternacionalED();

      String oid_Conhecimento_Internacional_Fatura = request.getParameter("oid_Conhecimento_Internacional_Fatura");
      if (oid_Conhecimento_Internacional_Fatura != null && oid_Conhecimento_Internacional_Fatura.length() > 0)
      {
        ed.setOID_Conhecimento_Internacional_Fatura(request.getParameter("oid_Conhecimento_Internacional_Fatura"));
      }

      return new Conhecimento_InternacionalRN().getByRecord_fatura(ed);

  }

  public Conhecimento_InternacionalED inclui_fatura(HttpServletRequest request)throws Excecoes{

      try{
        Conhecimento_InternacionalRN Conhecimento_Internacionalrn = new Conhecimento_InternacionalRN();
        Conhecimento_InternacionalED ed = new Conhecimento_InternacionalED();

        ed.setNR_Fatura(request.getParameter("FT_NR_Fatura"));
        //ed.setDt_stamp(request.getParameter("FT_DT_Stamp"));
        //ed.setHr_stamp(request.getParameter("FT_HR_Stamp"));
        ed.setDt_stamp(Data.getDataDMY());
        ed.setHr_stamp(Data.getHoraHM());
        ed.setOID_Conhecimento(request.getParameter("oid_Conhecimento"));
        ed.setDm_Stamp("S");
        ed.setUsuario_Stamp(" ");

        return Conhecimento_Internacionalrn.inclui_fatura(ed);
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

  public void altera_fatura(HttpServletRequest request)throws Excecoes{

      try{
        Conhecimento_InternacionalRN Conhecimento_Internacionalrn = new Conhecimento_InternacionalRN();
        Conhecimento_InternacionalED ed = new Conhecimento_InternacionalED();


        ed.setOID_Conhecimento_Internacional_Fatura(request.getParameter("oid_Conhecimento_Internacional_Fatura"));
        ed.setNR_Fatura(request.getParameter("FT_NR_Fatura"));

        Conhecimento_Internacionalrn.altera_fatura(ed);
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

  public void deleta_fatura(HttpServletRequest request)throws Excecoes{

      try{
        Conhecimento_InternacionalRN Conhecimento_Internacionalrn = new Conhecimento_InternacionalRN();
        Conhecimento_InternacionalED ed = new Conhecimento_InternacionalED();

        ed.setOID_Conhecimento_Internacional_Fatura(request.getParameter("oid_Conhecimento_Internacional_Fatura"));
        
        // System.out.println("VAI 2");

        Conhecimento_Internacionalrn.deleta_fatura(ed);
      }
      catch (Excecoes exc){
    	  exc.printStackTrace();
        throw exc;
      }
      catch(Exception exc){
    	  exc.printStackTrace();
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("erro ao excluir");
        excecoes.setMetodo("excluir");
        excecoes.setExc(exc);
        throw excecoes;
      }
    }
  
  public String validaOrigemDestino(HttpServletRequest request) throws Excecoes{

      try{
          
          String paisesIguais = "";
          
          if(JavaUtil.doValida(request.getParameter("oid_Cidade_Origem")) 
             && JavaUtil.doValida(request.getParameter("oid_Cidade_Destino"))){
          
	      int oidCidadeOrigem = new Integer(request.getParameter("oid_Cidade_Origem")).intValue();
	      String oid_Pais_Origem = CidadeBean.getByOID(oidCidadeOrigem).getCD_Pais();
	      
	      int oidPaisDestino = new Integer(request.getParameter("oid_Cidade_Destino")).intValue();
	      String oid_Pais_Destino = CidadeBean.getByOID(oidPaisDestino).getCD_Pais();
	
	      if(oid_Pais_Origem.equals(oid_Pais_Destino)){
	          paisesIguais = "As cidades de origem e destino não podem ser do mesmo país!";
	      }
          }
	      return paisesIguais;
      }
      catch(Exception exc){
      	exc.printStackTrace();
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMetodo("inclui");
        throw excecoes;
      }
    }

  public String getSituacaoCRTByCodigo(String codigo){

      String situacao = null;
      
      if(JavaUtil.doValida(codigo)){
          if(codigo.equals("I")){
              situacao = "Impresso";
          }
          else if(codigo.equals("G")){
              situacao = "Gerado";
          }
          else if(codigo.equals("C")){
              situacao = "Cancelado";
          }
          else if(codigo.equals("F")){
              situacao = "Faturado";
          }
          else if(codigo.equals("L")){
              situacao = "Pré-Faturado";
          }
          else{
              situacao = "CRT.dm_situacao="+codigo;
          }
      }
      else{
          situacao = "Indeterminado";
      }
      return situacao;
    }
  
  public ArrayList Conhecimento_Internacional_Lista_MIC(HttpServletRequest request)throws Excecoes{

      Conhecimento_InternacionalED ed = new Conhecimento_InternacionalED();
   
      String oid_Unidade_Origem = request.getParameter("oid_Unidade");
      if (String.valueOf(oid_Unidade_Origem) != null && !String.valueOf(oid_Unidade_Origem).equals("")){
        ed.setOID_Unidade_Origem(new Integer(oid_Unidade_Origem).intValue());
      }
      String nr_Conhecimento = request.getParameter("FT_NR_Conhecimento");
      if (String.valueOf(nr_Conhecimento) != null && !String.valueOf(nr_Conhecimento).equals("")){
        ed.setNR_Conhecimento(new Long(nr_Conhecimento).longValue());
      }
      
      return new Conhecimento_InternacionalRN().lista_MIC(ed);

  }
  
  public void geraRelCRTEmitidoJasper(HttpServletRequest request, HttpServletResponse Response)throws Excecoes{
  	Conhecimento_InternacionalED ed = new Conhecimento_InternacionalED();

  	ed.setOID_Pessoa(request.getParameter("oid_Pessoa_Remetente"));
  	ed.setOID_Pessoa_Destinatario(request.getParameter("oid_Pessoa_Destinatario"));
  	
  	String oid_Origem = request.getParameter("oid_Pais_Origem");
  	if (JavaUtil.doValida(oid_Origem)){
  	    ed.setOid_Origem(new Long(oid_Origem).longValue());
  	}
  	String oid_Destino = request.getParameter("oid_Pais_Destino");
  	if (JavaUtil.doValida(oid_Destino)){
  	    ed.setOid_Destino(new Long(oid_Destino).longValue());
  	}

  	String oid_Pessoa_Pagador = request.getParameter("oid_Pessoa_Pagador");
  	if (String.valueOf(oid_Pessoa_Pagador) != null && !String.valueOf(oid_Pessoa_Pagador).equals("")){
  		ed.setOID_Pessoa_Pagador(oid_Pessoa_Pagador);
  	}

  	String Dt_Emissao_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
  	if (String.valueOf(Dt_Emissao_Inicial) != null && !String.valueOf(Dt_Emissao_Inicial).equals("")){
  		ed.setDT_Emissao_Inicial(Dt_Emissao_Inicial);
  	}

  	String Dt_Emissao_Final = request.getParameter("FT_DT_Emissao_Final");
  	if (String.valueOf(Dt_Emissao_Final) != null && !String.valueOf(Dt_Emissao_Final).equals("")){
  		ed.setDT_Emissao_Final(Dt_Emissao_Final);
  	}

  	String oid_Unidade = request.getParameter("oid_Unidade");
  	if (String.valueOf(oid_Unidade) != null && !String.valueOf(oid_Unidade).equals("")){
  		ed.setOID_Unidade_Origem(new Integer(oid_Unidade).intValue());
  	}
  	
    ed.setDM_Situacao(request.getParameter("FT_DM_Situacao"));
    ed.setDM_SO(request.getParameter("FT_DM_SO"));
// System.out.println("REL : "+ed.getDM_SO());

  	Conhecimento_InternacionalRN geRN = new Conhecimento_InternacionalRN();
  	geRN.geraRelCRTEmitidoJasper(ed, Response);

  }
  
  public void geraRelCRT_Comissao(HttpServletRequest request, HttpServletResponse Response)throws Excecoes{
    	Conhecimento_InternacionalED ed = new Conhecimento_InternacionalED();

    	ed.setOID_Pessoa(request.getParameter("oid_Pessoa_Remetente"));
    	ed.setOID_Pessoa_Destinatario(request.getParameter("oid_Pessoa_Destinatario"));
    	
    	String Dt_Emissao_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
    	if (String.valueOf(Dt_Emissao_Inicial) != null && !String.valueOf(Dt_Emissao_Inicial).equals("")){
    		ed.setDT_Emissao_Inicial(Dt_Emissao_Inicial);
    	}

    	String Dt_Emissao_Final = request.getParameter("FT_DT_Emissao_Final");
    	if (String.valueOf(Dt_Emissao_Final) != null && !String.valueOf(Dt_Emissao_Final).equals("")){
    		ed.setDT_Emissao_Final(Dt_Emissao_Final);
    	}

    	String oid_Unidade = request.getParameter("oid_Unidade");
    	if (String.valueOf(oid_Unidade) != null && !String.valueOf(oid_Unidade).equals("")){
    		ed.setOID_Unidade_Origem(new Integer(oid_Unidade).intValue());
    	}
    	ed.setOID_Vendedor(request.getParameter("oid_Vendedor"));
    	
    	Conhecimento_InternacionalRN geRN = new Conhecimento_InternacionalRN();
    	geRN.geraRelCRT_Comissao(ed, Response);

    }
  
  public Conhecimento_InternacionalED getByOID(HttpServletRequest request)throws Excecoes{

      Conhecimento_InternacionalED ed = new Conhecimento_InternacionalED();

      ed.setOID_Conhecimento(request.getParameter("oid_Conhecimento_Internacional"));
     
// System.err.println( ed.getOID_Conhecimento());
	  if (JavaUtil.doValida(request.getParameter("oid_Conhecimento")) && !JavaUtil.doValida( ed.getOID_Conhecimento())){
	      ed.setOID_Conhecimento(request.getParameter("oid_Conhecimento"));
	  }
// System.err.println("e vai??? "+ ed.getOID_Conhecimento());

      return new Conhecimento_InternacionalRN().getByRecord(ed);

  }
  
  public void geraRelConhecInternacionalMultiplo(HttpServletRequest request, HttpServletResponse response)throws Excecoes{

      Conhecimento_InternacionalED ed = new Conhecimento_InternacionalED();

      ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));
      ed.setDT_Fim(request.getParameter("FT_DT_Fim"));
      
      String NR_Conhecimento = request.getParameter("FT_NR_Conhecimento");
      if (String.valueOf(NR_Conhecimento) != null && !String.valueOf(NR_Conhecimento).equals("")){
        ed.setNR_Conhecimento(new Long(NR_Conhecimento).longValue());
      } 
      String NR_Conhecimento_Final = request.getParameter("FT_NR_Conhecimento_Final");
      if (String.valueOf(NR_Conhecimento_Final) != null && !String.valueOf(NR_Conhecimento_Final).equals("")){
        ed.setNR_Conhecimento_Final(new Long(NR_Conhecimento_Final).longValue());
      } 
      
      String oid_Unidade_Origem = request.getParameter("oid_Unidade_Origem");
      if (String.valueOf(oid_Unidade_Origem) != null && !String.valueOf(oid_Unidade_Origem).equals("")){
        ed.setOID_Unidade_Origem(new Integer(oid_Unidade_Origem).intValue());
      }
      String oid_Unidade_Destino = request.getParameter("oid_Unidade_Destino");
      if (JavaUtil.doValida(oid_Unidade_Destino)){
        ed.setOID_Unidade_Destino(new Integer(oid_Unidade_Destino).intValue());
      }
      String oid_Unidade_Fronteira = request.getParameter("oid_Unidade_Fronteira");
      if (JavaUtil.doValida(oid_Unidade_Fronteira)){
        ed.setOID_Unidade_Fronteira(new Integer(oid_Unidade_Fronteira).intValue());
      }

      String folha = request.getParameter("FT_Folha");
      if (folha != null && !folha.equals("") &&  !folha.equals("null")){
        ed.setDM_Folha(folha);
      }
      
      ed.setDM_Imprime1(request.getParameter("FT_DM_imprime1"));
      ed.setDM_Imprime2(request.getParameter("FT_DM_imprime2"));
      ed.setDM_Imprime3(request.getParameter("FT_DM_imprime3"));
      ed.setDM_ImprimeCP(request.getParameter("FT_DM_imprimeCP"));
//// System.out.println("1:"+request.getParameter("FT_DM_imprime1"));
//// System.out.println("2:"+request.getParameter("FT_DM_imprime2"));
//// System.out.println("3:"+request.getParameter("FT_DM_imprime3"));
//// System.out.println("CP:"+request.getParameter("FT_DM_imprimeCP"));
      
      String carimbo = request.getParameter("FT_DM_Carimbo");
      if (carimbo != null && !carimbo.equals("") &&  !carimbo.equals("null")){
        ed.setDM_SO(carimbo);
      }

    Conhecimento_InternacionalRN geRN = new Conhecimento_InternacionalRN();

    geRN.geraRelConhecInternacionalMultiplo(ed,response);
    geRN = null;

  }
  
  public void LiberaFaturamentoInternacionalMultiplo(HttpServletRequest request)throws Excecoes{

      Conhecimento_InternacionalED ed = new Conhecimento_InternacionalED();

      ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));
      ed.setDT_Fim(request.getParameter("FT_DT_Fim"));
      
      String NR_Conhecimento = request.getParameter("FT_NR_Conhecimento");
      if (String.valueOf(NR_Conhecimento) != null && !String.valueOf(NR_Conhecimento).equals("")){
        ed.setNR_Conhecimento(new Long(NR_Conhecimento).longValue());
      } 
      String NR_Conhecimento_Final = request.getParameter("FT_NR_Conhecimento_Final");
      if (String.valueOf(NR_Conhecimento_Final) != null && !String.valueOf(NR_Conhecimento_Final).equals("")){
        ed.setNR_Conhecimento_Final(new Long(NR_Conhecimento_Final).longValue());
      } 
      
      String oid_Unidade_Origem = request.getParameter("oid_Unidade_Origem");
      if (String.valueOf(oid_Unidade_Origem) != null && !String.valueOf(oid_Unidade_Origem).equals("")){
        ed.setOID_Unidade_Origem(new Integer(oid_Unidade_Origem).intValue());
      }

    Conhecimento_InternacionalRN geRN = new Conhecimento_InternacionalRN();

    geRN.LiberaFaturamentoInternacionalMultiplo(ed);
    geRN = null;

  }
  
  
  public void geraRelCusto(HttpServletRequest request, HttpServletResponse Response)throws Excecoes{
    	Conhecimento_InternacionalED ed = new Conhecimento_InternacionalED();

    	ed.setOID_Pessoa(request.getParameter("oid_Pessoa_Remetente"));
    	ed.setOID_Pessoa_Destinatario(request.getParameter("oid_Pessoa_Destinatario"));
    	
    	String oid_Origem = request.getParameter("oid_Pais_Origem");
    	if (JavaUtil.doValida(oid_Origem)){
    	    ed.setOid_Origem(new Long(oid_Origem).longValue());
    	}
    	String oid_Destino = request.getParameter("oid_Pais_Destino");
    	if (JavaUtil.doValida(oid_Destino)){
    	    ed.setOid_Destino(new Long(oid_Destino).longValue());
    	}

    	String oid_Pessoa_Pagador = request.getParameter("oid_Pessoa_Pagador");
    	if (String.valueOf(oid_Pessoa_Pagador) != null && !String.valueOf(oid_Pessoa_Pagador).equals("")){
    		ed.setOID_Pessoa_Pagador(oid_Pessoa_Pagador);
    	}

    	String Dt_Emissao_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
    	if (String.valueOf(Dt_Emissao_Inicial) != null && !String.valueOf(Dt_Emissao_Inicial).equals("")){
    		ed.setDT_Emissao_Inicial(Dt_Emissao_Inicial);
    	}

    	String Dt_Emissao_Final = request.getParameter("FT_DT_Emissao_Final");
    	if (String.valueOf(Dt_Emissao_Final) != null && !String.valueOf(Dt_Emissao_Final).equals("")){
    		ed.setDT_Emissao_Final(Dt_Emissao_Final);
    	}
    	String NR_Conhecimento = request.getParameter("FT_NR_Conhecimento");
        if (String.valueOf(NR_Conhecimento) != null && !String.valueOf(NR_Conhecimento).equals("")){
          ed.setNR_Conhecimento(new Long(NR_Conhecimento).longValue());
        } 
        String NR_Manifesto = request.getParameter("FT_NR_Manifesto");
        if (String.valueOf(NR_Manifesto) != null && !String.valueOf(NR_Manifesto).equals("")){
          ed.setNM_Manifesto(NR_Manifesto);
        } 

    	String oid_Unidade = request.getParameter("oid_Unidade");
    	if (String.valueOf(oid_Unidade) != null && !String.valueOf(oid_Unidade).equals("")){
    		ed.setOID_Unidade_Origem(new Integer(oid_Unidade).intValue());
    	}
    	
        ed.setDM_SO(request.getParameter("FT_DM_SO"));
// System.out.println("REL : "+ed.getDM_SO());

    	Conhecimento_InternacionalRN geRN = new Conhecimento_InternacionalRN();
    	geRN.geraRelCusto(ed, Response);

    }
  
}
