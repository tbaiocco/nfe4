package com.master.bd;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.Edi_Nota_Fiscal_CTRCED;
import com.master.root.FormataDataBean;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.bd.ExecutaSQL;
import com.master.util.bd.Transacao;
import com.master.ed.PessoaED;

public class Edi_Nota_Fiscal_CTRCBD
    extends Transacao {

  private ExecutaSQL executasql;

  public Edi_Nota_Fiscal_CTRCBD (ExecutaSQL sql) {
    this.executasql = sql;
  }

  public ArrayList gera_Edi_Nota_Fiscal_CTRC (Edi_Nota_Fiscal_CTRCED edComp) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    Edi_Nota_Fiscal_CTRCED ed = (Edi_Nota_Fiscal_CTRCED) edComp;

    // System.err.println ("edi bd 1");

    String nr_Conhecimento = "";
    try {

      sql = " SELECT " +
          " Notas_Fiscais.HR_Entrada as HR_Entrada_NF," +
          " Notas_Fiscais.NR_Pedido," +
          " Notas_Fiscais.NR_Nota_Fiscal," +
          " Notas_Fiscais.VL_Nota_Fiscal, " +
          " Notas_Fiscais.DT_Emissao as DT_Emissao_NF, " +
          " Notas_Fiscais.DT_Entrada," +
          " Notas_Fiscais.NR_Peso as NR_Peso_NF," +
          " Notas_Fiscais.NR_Volumes as NR_Volumes_NF," +
          " Conhecimentos.OID_Conhecimento, " +
          " Conhecimentos.OID_Centro_Custo, " +
          " Conhecimentos.OID_Modal, " +
          " Conhecimentos.OID_Produto, " +
          " Conhecimentos.oid_pessoa_pagador, " +
          " Conhecimentos.Oid_Pessoa_Destinatario, " +
          " Conhecimentos.oid_pessoa, " +
          " Conhecimentos.oid_Veiculo, " +
          " Conhecimentos.oid_Carreta, " +
          " Conhecimentos.NR_Conhecimento, " +
          " Conhecimentos.NR_Prazo_Entrega, " +
          " Conhecimentos.NR_Dias_Entrega_Realizado, " +
          " Conhecimentos.NR_Duplicata, " +
          " Conhecimentos.cd_cfo_conhecimento, " +
          " Conhecimentos.NR_AWB, " +
          " Conhecimentos.NR_Minuta, " +
          " Conhecimentos.NR_Postagem, " +
          " Conhecimentos.DM_Tipo_Postagem, " +
          " Conhecimentos.DT_Emissao as DT_Emissao_CTO, " +
          " Conhecimentos.DT_Coleta, " +
          " Conhecimentos.DT_Entrega, " +
          " Conhecimentos.HR_Entrega, " +
          " Conhecimentos.DT_Previsao_Entrega, " +
          " Conhecimentos.HR_Previsao_Entrega, " +
          " Conhecimentos.DM_Tipo_Documento, " +
          " Conhecimentos.NM_Pessoa_Entrega, " +
          " Conhecimentos.VL_Total_Frete, " +
          " Conhecimentos.VL_Base_Calculo_ICMS, " +
          " Conhecimentos.VL_ICMS, " +
          " Conhecimentos.NR_Volumes, " +
          " Conhecimentos.NR_Peso, " +
          " Conhecimentos.NR_Peso_Cubado, " +
          " Conhecimentos.VL_Nota_Fiscal as VL_Nota_Fiscal_Cto, " +
          " Conhecimentos.PE_Aliquota_ICMS, " +
          " Conhecimentos.Vl_frete_peso, " +
          " Conhecimentos.Vl_frete_valor, " +
          " Conhecimentos.VL_Pedagio, " +
          " Conhecimentos.VL_Despacho, " +
          " Conhecimentos.VL_Sec_Cat, " +
          " Conhecimentos.VL_Outros1, " +
          " Conhecimentos.VL_Outros2, " +
          " Conhecimentos.VL_Outros3, " +
          " Conhecimentos.VL_Outros4, " +
          " Conhecimentos.VL_Outros5, " +
          " Conhecimentos.VL_Outros6, " +
          " Conhecimentos.VL_Outros7, " +
          " Conhecimentos.VL_Outros8, " +
          " Conhecimentos.VL_Outros9, " +
          " Conhecimentos.DM_Tipo_Pagamento, " +
          " Conhecimentos.DM_Cia_Aerea, " +
          " Conhecimentos.NM_Natureza, " +
          " Conhecimentos.TX_Observacao, " +
          " Conhecimentos.DM_Tipo_Documento " +
          " FROM Clientes, Conhecimentos, Conhecimentos_Notas_Fiscais, Notas_Fiscais " +
          " WHERE Conhecimentos_Notas_Fiscais.OID_Conhecimento = Conhecimentos.OID_Conhecimento " +
          " AND Conhecimentos_Notas_Fiscais.OID_Nota_Fiscal = Notas_Fiscais.OID_Nota_Fiscal " +
          " AND Conhecimentos.oid_Pessoa_pagador = Clientes.oid_Pessoa " +
          " AND Conhecimentos.DM_Situacao <>'C' " +
          " AND Conhecimentos.VL_Total_Frete>0 " +
          " AND Conhecimentos.DM_Impresso ='S' ";

      // System.err.println ("->>>> " + sql);
//            data//Edi_Nota_Fiscal_CTRC//duplicata.txt
      // System.err.println ("dt ini ->>>> " + ed.getDT_Inicial ());

      if (String.valueOf (ed.getDT_Inicial ()) != null &&
          !String.valueOf (ed.getDT_Inicial ()).equals ("") &&
          !String.valueOf (ed.getDT_Inicial ()).equals ("null")) {
        sql += " and Notas_Fiscais.DT_Emissao >= '" + ed.getDT_Inicial () + "'";
      }
      if (String.valueOf (ed.getDT_Final ()) != null &&
          !String.valueOf (ed.getDT_Final ()).equals ("") &&
          !String.valueOf (ed.getDT_Final ()).equals ("null")) {
        sql += " and Notas_Fiscais.DT_Emissao <= '" + ed.getDT_Final () + "'";
      }
      if (String.valueOf (ed.getOid_Pessoa ()) != null &&
          !String.valueOf (ed.getOid_Pessoa ()).equals ("") &&
          !String.valueOf (ed.getOid_Pessoa ()).equals ("null")) {
        sql += " and Notas_Fiscais.oid_pessoa = '" + ed.getOid_Pessoa () + "'";
      }

      if (String.valueOf (ed.getOid_Pessoa_Destinatario ()) != null &&
          !String.valueOf (ed.getOid_Pessoa_Destinatario ()).equals ("") &&
          !String.valueOf (ed.getOid_Pessoa_Destinatario ()).equals ("null")) {
        sql += " and Notas_Fiscais.oid_pessoa_destinatario = '" + ed.getOid_Pessoa_Destinatario () + "'";
      }

      if ("E".equals (ed.getDM_Situacao ())) {
        sql += " and not Conhecimentos.DT_Entrega is null ";
      }
      else if ("P".equals (ed.getDM_Situacao ())) {
        sql += " and Conhecimentos.DT_Entrega is null ";
      }

      if ("M1".equals (ed.getDM_Relatorio ())) {
        sql += " AND clientes.oid_grupo_economico = 2 " +
            " ORDER by Conhecimentos.oid_Pessoa_pagador , Conhecimentos.NR_Conhecimento,  Notas_Fiscais.DT_Entrada asc, Notas_Fiscais.NR_Nota_Fiscal ";
      }
      else {
        if ("M3".equals (ed.getDM_Relatorio ())) {
          sql += " AND clientes.oid_grupo_economico = 7 " +
              " ORDER by Conhecimentos.DT_Emissao, Conhecimentos.NR_Conhecimento, Notas_Fiscais.NR_Pedido desc ";
        }
        else {
          if ("M4".equals (ed.getDM_Relatorio ())) {
            sql += " AND clientes.oid_grupo_economico = 7 " +
                " ORDER by Notas_Fiscais.DT_Emissao,  Notas_Fiscais.NR_Nota_Fiscal ";
          }
          else {
            if ("M5".equals (ed.getDM_Relatorio ())) {
              sql += " ORDER by Conhecimentos.DT_Emissao ";
            }
            else {
              sql += " ORDER by Conhecimentos.DT_Emissao ";
            }
          }
        }
      }

      // System.err.println (sql);

      ResultSet res = null;
      ResultSet res2 = null;

      res = this.executasql.executarConsulta (sql);

      FormataDataBean dataFormatada = new FormataDataBean ();
      while (res.next ()) {
        Edi_Nota_Fiscal_CTRCED edVolta = new Edi_Nota_Fiscal_CTRCED ();

        // System.err.println ("Lendo EDI Cto NF->> " + res.getLong ("NR_Conhecimento"));

        long nr_cto = 1000000 + res.getLong ("NR_Conhecimento");
        String cto = String.valueOf (nr_cto);
        edVolta.setNR_Conhecimento (cto.substring (1 , 7));

        edVolta.setOid_Pessoa (" ");
        edVolta.setNM_Remetente (" ");
        edVolta.setNM_Cidade_Origem (" ");
        edVolta.setNM_Estado_Origem (" ");
        edVolta.setNM_Destinatario (" ");
        edVolta.setNM_Cidade_Destino (" ");
        edVolta.setNM_Estado_Destino (" ");
        edVolta.setDT_Entrega (" ");
        edVolta.setNR_Placa (" ");
        edVolta.setNR_Placa_Carreta (" ");
        edVolta.setNM_Motorista (" ");
        edVolta.setNR_Fatura (" ");
        edVolta.setNM_Tipo_Veiculo (" ");
        edVolta.setNM_Natureza (res.getString ("NM_Natureza"));

        edVolta.setDT_Coleta ("");
        edVolta.setHR_Coleta ("");
        edVolta.setDT_Entrega ("");
        edVolta.setHR_Entrega ("");
        edVolta.setNM_Cia_Aerea ("");
        
        edVolta.setNR_Fatura ("");

        edVolta.setTX_Observacao (res.getString ("TX_Observacao"));

        // System.err.println ("Lendo  NF->> " + res.getLong ("NR_Nota_Fiscal"));
        edVolta.setNR_Nota_Fiscal (res.getString("NR_Nota_Fiscal"));

        // System.err.println ("NF->> " + edVolta.getNR_Nota_Fiscal());

        edVolta.setVL_Nota_Fiscal (res.getDouble ("VL_Nota_Fiscal"));

        edVolta.setNR_Peso_Nota_Fiscal (res.getDouble ("NR_Peso_NF"));
        edVolta.setNR_Volume_Nota_Fiscal (res.getDouble ("NR_Volumes_NF"));

        edVolta.setNR_Peso_Conhecimento (res.getDouble ("NR_Peso"));
        edVolta.setNR_Peso_Cubado (res.getDouble ("NR_Peso"));
        if (res.getDouble ("NR_Peso_Cubado") > res.getDouble ("NR_Peso")) {
          edVolta.setNR_Peso_Cubado (res.getDouble ("NR_Peso_Cubado"));
        }

        edVolta.setNR_Volumes_Conhecimento (res.getDouble ("NR_Volumes"));

        dataFormatada.setDT_FormataData (res.getString ("DT_Emissao_NF"));
        edVolta.setDT_Emissao_Nota_Fiscal (dataFormatada.getDT_FormataData ());

        dataFormatada.setDT_FormataData (res.getString ("DT_Emissao_CTO"));
        edVolta.setDT_Emissao_Conhecimento (dataFormatada.getDT_FormataData ());

        dataFormatada.setDT_FormataData (res.getString ("DT_Previsao_Entrega"));
        edVolta.setDT_Previsao_Entrega (dataFormatada.getDT_FormataData ());
        edVolta.setHR_Previsao_Entrega(res.getString ("HR_Previsao_Entrega"));

        dataFormatada.setDT_FormataData (res.getString ("DT_Entrada"));
        edVolta.setDT_Entrada (dataFormatada.getDT_FormataData ());

        if (!"M1".equals (ed.getDM_Relatorio ())) {
          nr_Conhecimento = "";
        }

        if (!nr_Conhecimento.equals (res.getString ("NR_Conhecimento"))) {

          if (res.getString ("NR_Pedido") != null && !res.getString ("NR_Pedido").equals ("null")) {
            edVolta.setNR_Pedido (res.getString ("NR_Pedido"));
          }
          edVolta.setOid_Pessoa_Pagador (res.getString ("oid_pessoa_pagador"));
          edVolta.setOid_Pessoa_Destinatario (res.getString ("Oid_Pessoa_Destinatario"));
          edVolta.setOid_Pessoa (res.getString ("oid_pessoa"));

          PessoaED pessoaED = new PessoaBD(executasql).getByRecord(res.getString ("oid_pessoa"));
          edVolta.setNR_CNPJ_CPF_Remetente (pessoaED.getNR_CNPJ_CPF());
          edVolta.setNM_Remetente (pessoaED.getNM_Razao_Social());
          edVolta.setNM_Inscricao_Estadual_Remetente ("|" + pessoaED.getNM_Inscricao_Estadual());
          edVolta.setNM_Cidade_Origem (pessoaED.getNM_Cidade());
          edVolta.setNM_Estado_Origem (pessoaED.getCD_Estado());

          pessoaED = new PessoaBD(executasql).getByRecord(res.getString ("oid_pessoa_destinatario"));
          edVolta.setNR_CNPJ_CPF_Destinatario (pessoaED.getNR_CNPJ_CPF());
          edVolta.setNM_Destinatario (pessoaED.getNM_Razao_Social());
          edVolta.setNM_Inscricao_Estadual_Destinatario ("|" + pessoaED.getNM_Inscricao_Estadual());
          edVolta.setNM_Cidade_Destino (pessoaED.getNM_Cidade());
          edVolta.setNM_Estado_Destino (pessoaED.getCD_Estado());


          if (res.getString ("DM_Cia_Aerea") != null && !res.getString ("DM_Cia_Aerea").equals ("null")) {
            edVolta.setNM_Cia_Aerea (res.getString ("DM_Cia_Aerea"));
          }

          edVolta.setCD_CFO_Conhecimento (res.getString ("cd_cfo_conhecimento"));

          if (res.getString ("DT_Coleta") != null && res.getString ("DT_Coleta").length () > 4) {
            dataFormatada.setDT_FormataData (res.getString ("DT_Coleta"));
            edVolta.setDT_Coleta (dataFormatada.getDT_FormataData ());
            if (res.getString ("HR_Entrada_NF") != null && res.getString ("HR_Entrada_NF").length () > 4) {
              edVolta.setHR_Coleta (res.getString ("HR_Entrada_NF"));
            }
          }

          edVolta.setNR_Prazo_Entrega(res.getLong("NR_Prazo_Entrega"));
          edVolta.setNR_Dias_Entrega_Realizado(res.getLong("NR_Dias_Entrega_Realizado"));
          edVolta.setNM_Situacao_Entrega("---");
          edVolta.setNM_Localizacao_Entrega(" ");
          if (res.getString ("DT_Entrega") != null && res.getString ("DT_Entrega").length () > 4) {
            dataFormatada.setDT_FormataData (res.getString ("DT_Entrega"));
            edVolta.setDT_Entrega (dataFormatada.getDT_FormataData ());
            if (res.getString ("HR_Entrega") != null && res.getString ("HR_Entrega").length () > 4) {
              edVolta.setHR_Entrega (res.getString ("HR_Entrega"));
            }
            edVolta.setNM_Localizacao_Entrega("FIM DE VIAGEM");
            edVolta.setNM_Situacao_Entrega("S");
            if (res.getLong("NR_Dias_Entrega_Realizado")>0) {
                edVolta.setNM_Situacao_Entrega("N");
            }
          }
          
          edVolta.setVL_Frete (res.getDouble ("Vl_total_frete"));
          edVolta.setVL_Frete_Peso (res.getDouble ("Vl_frete_peso"));
          edVolta.setVL_Frete_Valor (res.getDouble ("Vl_frete_valor"));
          edVolta.setVL_Pedagio (res.getDouble ("VL_Pedagio"));
          edVolta.setVL_Despacho (res.getDouble ("VL_Despacho"));
          edVolta.setVL_Sec_Cat (res.getDouble ("VL_Sec_Cat"));
          edVolta.setVL_Ademe (res.getDouble ("VL_Outros1") + res.getDouble ("VL_Outros2") + res.getDouble ("VL_Outros3") + res.getDouble ("VL_Outros4") + res.getDouble ("VL_Outros5"));

          edVolta.setVL_Coleta (res.getDouble ("VL_OUTROS3") + res.getDouble ("VL_OUTROS4") + res.getDouble ("VL_OUTROS5"));
          edVolta.setVL_Entrega (res.getDouble ("VL_OUTROS1") + res.getDouble ("VL_OUTROS6") + res.getDouble ("VL_OUTROS7") + res.getDouble ("VL_OUTROS8"));

          edVolta.setVL_Base_Calculo_ICMS (res.getDouble ("VL_Base_Calculo_ICMS"));
          edVolta.setVL_ICMS (res.getDouble ("VL_ICMS"));
          edVolta.setPE_Aliquota_ICMS (res.getDouble ("PE_Aliquota_ICMS"));
          edVolta.setVL_Nota_Fiscal (res.getDouble ("VL_Nota_Fiscal"));
          edVolta.setNR_Peso_Nota_Fiscal (res.getDouble ("NR_Peso"));
          if (res.getString ("oid_Veiculo") !=null && res.getString ("oid_Veiculo").length()>4) {
        	  edVolta.setNR_Placa (res.getString ("oid_Veiculo"));
          }
          if (res.getString ("oid_Carreta") !=null && res.getString ("oid_Carreta").length()>4) {
        	  edVolta.setNR_Placa_Carreta (res.getString ("oid_Carreta"));
          }	  

          
          
          if ("M1".equals (ed.getDM_Relatorio ())) {
            sql = " SELECT Veiculos.NR_Placa, nm_modelo_veiculo FROM Manifestos, Viagens, Modelos_Veiculos, Veiculos " +
                " WHERE  Manifestos.oid_Manifesto = Viagens.oid_Manifesto " +
                " AND    Manifestos.oid_Veiculo = Veiculos.oid_Veiculo " +
                " AND    Modelos_Veiculos.oid_modelo_veiculo = Veiculos.oid_modelo_veiculo " +
                " AND    Viagens.oid_Conhecimento ='" + res.getString ("oid_Conhecimento") + "'" +
                " ORDER BY Veiculos.oid_Veiculo ";

            res2 = this.executasql.executarConsulta (sql);
            if (res2.next ()) {
              edVolta.setNR_Placa (res2.getString ("NR_Placa"));
              edVolta.setNM_Tipo_Veiculo (res2.getString ("nm_modelo_veiculo"));
            }
          }
          if ("M8".equals (ed.getDM_Relatorio ())) {
              sql = " SELECT Veiculos.NR_Placa, nm_modelo_veiculo, Pessoas.NM_Razao_Social, Manifestos.oid_Veiculo_Carreta as NR_Placa_Carreta FROM Manifestos, Viagens, Modelos_Veiculos, Veiculos, Pessoas " +
                  " WHERE  Manifestos.oid_Manifesto = Viagens.oid_Manifesto " +
                  " AND    Manifestos.oid_Pessoa = Pessoas.oid_Pessoa " +
                  " AND    Manifestos.oid_Veiculo = Veiculos.oid_Veiculo " +
                  " AND    Modelos_Veiculos.oid_modelo_veiculo = Veiculos.oid_modelo_veiculo " +
                  " AND    Viagens.oid_Conhecimento ='" + res.getString ("oid_Conhecimento") + "'" +
                  " ORDER BY Veiculos.oid_Veiculo ";

              res2 = this.executasql.executarConsulta (sql);
              if (res2.next ()) {
                edVolta.setNM_Motorista (res2.getString ("NM_Razao_Social"));
                edVolta.setNM_Tipo_Veiculo (res2.getString ("nm_modelo_veiculo"));
              }
          }

          if ("M2".equals (ed.getDM_Relatorio ()) && res.getLong ("oid_Modal") > 0) {
            sql = " SELECT CD_Modal FROM Modal " +
                " WHERE  Modal.oid_Modal =" + res.getLong ("oid_Modal");
            res2 = this.executasql.executarConsulta (sql);
            if (res2.next ()) {
              edVolta.setCD_Modal (res2.getString ("CD_Modal"));
            }
          }

          if ("M2".equals (ed.getDM_Relatorio ()) && res.getLong ("oid_Unidade") > 0) {
            sql = " SELECT CD_Unidade FROM Unidades " +
                  " WHERE  Unidades.oid_Unidade =" + res.getLong ("oid_Unidade");
            res2 = this.executasql.executarConsulta (sql);
            if (res2.next ()) {
              edVolta.setCD_Unidade (res2.getString ("CD_Unidade"));
            }
          }

          if ("M2".equals (ed.getDM_Relatorio ()) && res.getLong ("oid_Produto") > 0) {
            sql = " SELECT NM_Produto FROM Produtos " +
                " WHERE  Produtos.oid_Produto =" + res.getLong ("oid_Produto");
            res2 = this.executasql.executarConsulta (sql);
            if (res2.next ()) {
              edVolta.setNM_Produto (res2.getString ("NM_Produto"));
            }
          }

          if ("M3".equals (ed.getDM_Relatorio ()) || "M4".equals (ed.getDM_Relatorio ())) {
            sql = " SELECT NM_Centro_Custo FROM Centros_Custos " +
                " WHERE  Centros_Custos.oid_Centro_Custo =" + res.getLong ("oid_Centro_Custo");
            res2 = this.executasql.executarConsulta (sql);
            if (res2.next ()) {
              edVolta.setNM_Centro_Custo (res2.getString ("NM_Centro_Custo"));
            }
          }

          if (res.getString ("NR_Duplicata") != null && res.getString ("NR_Duplicata").equals ("null")) {
            edVolta.setNR_Fatura (res.getString ("NR_Duplicata"));
          }
        }

        if ("M3".equals (ed.getDM_Relatorio ()) || "M4".equals (ed.getDM_Relatorio ())) {
          sql = " SELECT NM_Razao_Social FROM Viagens, Ordens_Manifestos, Ordens_Fretes, Pessoas " +
              " WHERE  Ordens_Manifestos.oid_Manifesto = Viagens.oid_Manifesto " +
              " AND    Ordens_Manifestos.oid_Ordem_Frete = Ordens_Fretes.oid_Ordem_Frete " +
              " AND    Ordens_Fretes.oid_Pessoa = Pessoas.oid_Pessoa " +
              " AND    Viagens.oid_Conhecimento ='" + res.getString ("oid_Conhecimento") + "'" +
              " ORDER BY Viagens.dt_viagem ";

          // System.out.println (sql);

          res2 = this.executasql.executarConsulta (sql);
          if (res2.next ()) {
            edVolta.setNM_Cia_Aerea (res2.getString ("NM_Razao_Social"));
          }
        }

        nr_Conhecimento = res.getString ("NR_Conhecimento");

        list.add (edVolta);
      }
      // System.err.println ("->>>> 999 ");

    }
    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao gerar REL Nota Fiscal -CTRC");
      excecoes.setMetodo ("gera_Edi_Nota_Fiscal_CTRC");
      excecoes.setExc (exc);
      throw excecoes;
    }

    return list;
  }

}
