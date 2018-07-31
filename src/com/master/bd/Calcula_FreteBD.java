//tipo cto
//"1">Normal
//"2">Cortesia
//"3">Devolução
//"4">Reentrega
//"E">Exportação
//"R">Refaturamento
//"6">Materia Prima

package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.master.ed.Calcula_FreteED;
import com.master.ed.Movimento_ConhecimentoED;
import com.master.rn.Movimento_ConhecimentoRN;
import com.master.root.ClienteBean;
import com.master.root.DateFormatter;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.Mensagens;
import com.master.util.Valor;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;

public class Calcula_FreteBD {
  private String Tipo_Movimento;
  private double VL_Movimento;
  private String OID_Conhecimento;
  private String DT_Movimento;
  private String HR_Movimento;
  private String OID_Pessoa_Entregadora;

  Movimento_ConhecimentoBD Movimento_ConhecimentoBD = null;
  Parametro_FixoED parametro_FixoED = new Parametro_FixoED ();
  DecimalFormat dec = new DecimalFormat ("###########0.00");

  String SHORT_DATE = "dd/MM/yyyy";
  DateFormatter dateFormatter = new DateFormatter ();

  String sql = null;
  long valOid = 0;
  long oid_Taxa;
  long oid_Coleta = 0;
  String chave = null;
  String tipo_calculo = null;
  String OID_Produto = null;
  String OID_Cliente = null;
  String OID_Item_Nota_Fiscal = null;
  long OID_Tipo_Movimento = 0;
  long oid_Cidade_Origem = 0;
  long oid_Cidade_Destino = 0;
  String DT_Emissao_Nota_Fiscal = null;
  String Tipo_Taxa = null;
  double VL_Movimento_Frete = 0;
  double VL_Total_Frete = 0;
  double VL_Total_Frete_Nota_Fiscal = 0;
  double NR_Peso_NF;
  double NR_Peso_Cubado_NF;
  double NR_Itens_NF;
  long NR_Volumes_NF;
  double NR_Peso_Tabela;
  double NR_Peso_Cub;

  double NR_Cubagem;
  double NR_Cubagem_Total;

  double VL_ICMS = 0;
  double VL_ICMS_Antecipado = 0;
  double VL_Frete_Peso = 0;
  double VL_AD_Valorem = 0;
  double VL_SEC_CAT = 0;
  double VL_Pedagio = 0;
  double VL_Outros1 = 0;
  double VL_Outros2 = 0;
  double VL_Outros3 = 0;
  double VL_Outros4 = 0;
  double VL_Outros5 = 0;
  double VL_Outros6 = 0;
  double VL_Outros7 = 0;
  double VL_Outros8 = 0;
  double VL_Outros9 = 0;
  double VL_Outros10 = 0;
  double VL_Despacho = 0;
  double VL_Base_ICMS = 0;
  double VL_Rateio_Frete_Peso = 0;
  double VL_Total_Frete_Calculado = 0;
  double PE_Juros_Cobranca = 0;
  double VL_Movimento_Custo = 0;
  double VL_Nota_Fiscal = 0;
  double Vl_Nota_Fiscal_Seguro = 0;
  double VL_Frete_Entrega = 0;
  double VL_Total_Custo = 0;
  double VL_PIS = 0;
  double VL_COFINS = 0;
  double PE_Desconto = 1;

  long OID_Estado_Unidade = 0;
  long OID_Estado_Origem = 0;
  long OID_Estado_Destino = 0;
  long OID_Estado_Pagador = 0;
  long OID_Estado_Origem_Taxas = 0;
  long OID_Estado_Destino_Taxas = 0;
  double PE_Aliquota_ICMS = 0;
  double PE_Aliquota_Seguro = 0;
  double PE_Aliquota_COFINS = 0;
  double PE_Aliquota_PIS = 0;
  double VL_Seguro = 0 , PE_PIS_COFINS = 0;
  double VL_Custo_Coleta = 0 , VL_Custo_Transferencia = 0 ,
      VL_Custo_Entrega = 0 , VL_Custo_Imposto = 0 , VL_Custo_Seguro = 0 ,
      VL_Custo_Outros = 0;

  String DT_Previsao_Entrega = "";
  int NR_Prazo_Entrega = 0;

  String CD_CFO = "";
  String CD_CFO_CONHECIMENTO = "";
  String DM_ICMS_CIF = "";
  String DM_ICMS_FOB = "";
  String NM_Inscricao_Estadual = null;
  String DM_Isencao_ICMS = "N";
  String DM_ICMS_Incluso_Frete = "N";
  String DM_PIS_COFINS = "S";

  String DM_Isencao_Seguro = "N";
  String CD_Grupo_CFO = "";
  String DM_Tipo_Localizacao = null;
  String DM_Suframa = "N";
  String DM_Fluvial = "N";
  String OID_Tabela_Frete = "";
  String DM_Unidade_Produto = "C";

  String localizado = null;
  String DM_FOB_Dirigido = null;
  String DM_CIF_FOB = null;
  String OID_Pessoa = null;
  String DM_Transferencia = null;
  String DM_Tipo_Produto = null;
  String DM_Exportacao = null;
  String DM_Totaliza = "";
  String DM_Tipo_Erro = "";
  String DM_Tem_Peso = "S";
  String DM_Tem_Modal = "S";
  String DM_Tem_Validade = "S";
  String DM_Tem_Vigencia = "S";
  String DM_Tem_Produto = "S";
  String DM_Tem_Empresa = "N";
  String DM_Tabela_Reversa = "S";
  String DM_Tem_Entregador = "N";
  String DM_Tabela_Cliente_Entregador = "C";
  String DM_Tem_Subregiao = "N";
  String DM_Tem_Cidade = "N";
  String DM_Tem_Regiao_Estado = "N";
  String DM_Tem_Estado = "N";
  String DM_Tem_Regiao_Pais = "N";
  String DM_Tem_Pais = "N";
  String DM_Tem_Tabela_Unidade = "N";
  String DM_Tipo_Tabela_Frete = "N";
  String DM_Tipo_Conhecimento = "1";
  String DM_Percurso_Busca = "Origem_Destino";
  String CD_Pais_Origem = "";
  String CD_Pais_Destino = "";

  ResultSet rs = null;
  ResultSet rsTabela = null;

  private ExecutaSQL executasql;

  public Calcula_FreteBD (ExecutaSQL sql) {
    this.executasql = sql;
  }

  public Calcula_FreteED calcula_frete (Calcula_FreteED ed) throws Excecoes {

    // System.out.println (" calcula frete novo");

    String sql = null;
    long valOid = 0;
    String tipo_calculo = null;
    int qt_nota_fiscal = 0;

    Calcula_FreteED conED = new Calcula_FreteED ();

    DM_Tipo_Conhecimento = ed.getDM_Tipo_Conhecimento ();

    try {

      tipo_calculo = "Tabela";

      sql = " SELECT Produtos.DM_Unidade, Notas_Fiscais.NR_Nota_Fiscal, Notas_Fiscais.VL_Nota_Fiscal, " +
      		"        Notas_Fiscais.DM_Tipo_Conhecimento, Notas_Fiscais.NR_PESO, Notas_Fiscais.NR_PESO_CUBADO, " +
      		"        Notas_Fiscais.NR_VOLUMES, Conhecimentos.oid_Coleta, Notas_Fiscais.NR_ITENS,  " +
      		"        Notas_Fiscais.NR_Cubagem, Notas_Fiscais.NR_Cubagem_Total, Notas_Fiscais.VL_Total_Frete,  " +
      		"        Notas_Fiscais.DM_TRANSFERENCIA, Conhecimentos.Vl_Nota_Fiscal_Seguro " +
      		"  FROM Notas_Fiscais, Produtos, Conhecimentos_Notas_Fiscais, Conhecimentos ";
      sql += " WHERE Notas_Fiscais.OID_Nota_Fiscal = Conhecimentos_Notas_Fiscais.OID_Nota_Fiscal ";
      sql += " AND Conhecimentos.OID_Conhecimento  = Conhecimentos_Notas_Fiscais.OID_Conhecimento ";
      sql += " AND Conhecimentos.OID_Produto = Produtos.OID_Produto ";
      sql += " AND Conhecimentos_Notas_Fiscais.OID_Conhecimento = '" +
          ed.getOID_Conhecimento () + "'";

      rs = null;
      rs = this.executasql.executarConsulta (sql);

      // System.out.println (" nf sql" + sql);

      while (rs.next ()) {
        // System.out.println (" LENDO nf do cto" + rs.getString ("NR_Nota_Fiscal"));
        qt_nota_fiscal++;

        VL_Total_Frete_Nota_Fiscal = VL_Total_Frete_Nota_Fiscal +
            rs.getDouble ("VL_Total_Frete");
        VL_Nota_Fiscal = VL_Nota_Fiscal + rs.getDouble ("VL_NOTA_FISCAL");
        Vl_Nota_Fiscal_Seguro = rs.getDouble ("Vl_Nota_Fiscal_Seguro");
        
        NR_Peso_NF = NR_Peso_NF + rs.getDouble ("NR_PESO");
        NR_Peso_Cub = NR_Peso_Cub + rs.getDouble ("NR_PESO_CUBADO");

        NR_Cubagem = NR_Cubagem + rs.getDouble ("NR_Cubagem");
        NR_Cubagem_Total = rs.getDouble ("NR_Cubagem_Total");

        if ( (rs.getDouble ("NR_PESO_CUBADO") > rs.getDouble ("NR_PESO"))
            || isCubagemConhecimento (DM_Tipo_Conhecimento)) {
          NR_Peso_Cubado_NF = NR_Peso_Cubado_NF +
              rs.getDouble ("NR_PESO_CUBADO");
        }
        else {
          NR_Peso_Cubado_NF = NR_Peso_Cubado_NF + rs.getDouble ("NR_PESO");
        }
        NR_Volumes_NF = NR_Volumes_NF + rs.getLong ("NR_VOLUMES");
        NR_Itens_NF = NR_Itens_NF + rs.getDouble ("NR_ITENS");
        DM_Transferencia = rs.getString ("DM_Transferencia");
        DM_Unidade_Produto = rs.getString ("DM_Unidade");
        if (rs.getString ("DM_Tipo_Conhecimento") != null &&
            DM_Tipo_Conhecimento == null) {
          DM_Tipo_Conhecimento = rs.getString ("DM_Tipo_Conhecimento");
        }
        oid_Coleta = rs.getLong ("oid_Coleta");
      }

      String Tipo_Cto = "1";
      if (DM_Tipo_Conhecimento != null) {
        Tipo_Cto = DM_Tipo_Conhecimento;
      }
      DM_Tipo_Conhecimento = Tipo_Cto;

      if (DM_Tipo_Conhecimento != null && DM_Tipo_Conhecimento.equals ("B") &&
          qt_nota_fiscal == 0) {
        sql = " SELECT NR_Peso, NR_Peso_Cubado, NR_Volumes, VL_Nota_Fiscal, Vl_Nota_Fiscal_Seguro FROM Conhecimentos ";
        sql += " WHERE  Conhecimentos.OID_Conhecimento = '" +
            ed.getOID_Conhecimento () + "'";

        rs = null;
        rs = this.executasql.executarConsulta (sql);

        // System.out.println (" cto sem nf sql" + sql);

        while (rs.next ()) {
          // System.out.println (" 1");

          VL_Nota_Fiscal = rs.getDouble ("VL_NOTA_FISCAL");
          Vl_Nota_Fiscal_Seguro = rs.getDouble ("Vl_Nota_Fiscal_Seguro");
          NR_Peso_NF = rs.getDouble ("NR_PESO");
          NR_Peso_Cub = rs.getDouble ("NR_PESO_CUBADO");
          NR_Cubagem = 0;
          // System.out.println (" 2");
          NR_Cubagem_Total = 0;
          if (rs.getDouble ("NR_PESO_CUBADO") > rs.getDouble ("NR_PESO")) {
            NR_Peso_Cubado_NF = rs.getDouble ("NR_PESO_CUBADO");
          }
          else {
            NR_Peso_Cubado_NF = rs.getDouble ("NR_PESO");
          }
          // System.out.println (" 3");
          NR_Volumes_NF = rs.getLong ("NR_VOLUMES");
          NR_Itens_NF = 1;
          DM_Transferencia = "N";
          DM_Unidade_Produto = "P";
        }
      }

      if (ed.getVL_Peso_Cubado () > 0 && DM_Tipo_Conhecimento.equals ("C")) { /// cubagen pelo cto
        NR_Cubagem_Total = ed.getVL_Peso_Cubado ();
        //NR_Peso_Cubado_NF= ed.getVL_Peso_Cubado();
      }


     //** Calcula cubagem ou pelo M3 X peso para cubagem do cadastro do cliente */
     //   double pesoCubadoCalculado = calculaCubagem (ed.getOID_Pessoa_Pagador_Tabela () , NR_Peso_Cub);
     //   // System.out.println (
     //       ">>>Calculo de cubagem pelo cadastro do cliente (Acha tabela)<<<");
     //   // System.out.println ("pesoCubadoCalculado: " + pesoCubadoCalculado);
     //   //** Peso para fins de cálculo é o peso maior entre peso bruto e peso cubado */
     //   double pesoCalculado = NR_Peso_NF > pesoCubadoCalculado ? NR_Peso_NF :          pesoCubadoCalculado;

     //   NR_Peso_Tabela = pesoCalculado;
     //   NR_Peso_Cubado_NF= pesoCalculado Usar sempre o NR_Peso_Cubado_NF


      // System.out.println (" Tipo Cto " + Tipo_Cto);

      NR_Peso_Tabela = NR_Peso_Cubado_NF;
      if (DM_Unidade_Produto != null && DM_Unidade_Produto.equals ("P")) {
        NR_Peso_Tabela = NR_Peso_NF;
      }
      if (DM_Unidade_Produto != null && DM_Unidade_Produto.equals ("V")) {
        NR_Peso_Tabela = NR_Volumes_NF;
      }
      if (DM_Unidade_Produto != null && DM_Unidade_Produto.equals ("I")) {
        NR_Peso_Tabela = NR_Itens_NF;
      }
      if (DM_Unidade_Produto != null && DM_Unidade_Produto.equals ("M")) {
        NR_Peso_Tabela = VL_Nota_Fiscal;
      }

      sql = " SELECT * FROM Tipos_Movimentos, Movimentos_Conhecimentos " +
          " WHERE Movimentos_Conhecimentos.oid_Tipo_Movimento = Tipos_Movimentos.oid_Tipo_Movimento" +
          " AND Movimentos_Conhecimentos.OID_Conhecimento = '" +
          ed.getOID_Conhecimento () + "'" +
          " AND (Tipos_Movimentos.CD_Tipo_Movimento = 'FIN' " +
          " OR Tipos_Movimentos.DM_Tipo_Movimento = 'I')";
      sql += " AND DM_Lancado_Gerado = 'L' ";

      ResultSet rs = null;
      rs = this.executasql.executarConsulta (sql);

      while (rs.next ()) {
        tipo_calculo = "Frete_Informado";
      }

      // System.out.println (" limpar mov");

      OID_Conhecimento = ed.getOID_Conhecimento ();
      sql = "delete from Movimentos_Conhecimentos WHERE oid_Conhecimento = ";
      sql += "('" + OID_Conhecimento + "')";
      sql += " AND DM_Lancado_Gerado = 'G' ";
      // System.out.println (" sql mov ctos " + sql);

      int i = executasql.executarUpdate (sql);

      // System.out.println (" limpou mov");

      sql = " UPDATE Conhecimentos SET ";
      sql += " VL_FRETE_PESO = 0";
      sql += " ,VL_FRETE_VALOR = 0";
      sql += " ,PE_ALIQUOTA_ICMS= 0";
      sql += " ,VL_SEC_CAT = 0";
      sql += " ,VL_PEDAGIO = 0";
      sql += " ,VL_DESPACHO = 0";
      sql += " ,VL_PIS      = 0";
      sql += " ,VL_COFINS   = 0";
      sql += " ,VL_OUTROS1 = 0";
      sql += " ,VL_OUTROS2 = 0";
      sql += " ,VL_OUTROS3 = 0";
      sql += " ,VL_OUTROS4 = 0";
      sql += " ,VL_OUTROS5 = 0";
      sql += " ,VL_OUTROS6 = 0";
      sql += " ,VL_OUTROS7 = 0";
      sql += " ,VL_OUTROS8 = 0";
      sql += " ,VL_OUTROS9 = 0";
      sql += " ,VL_OUTROS10 = 0";
      sql += " ,VL_TOTAL_FRETE = 0";
      sql += " ,VL_BASE_CALCULO_ICMS = 0";
      sql += " ,VL_ICMS = 0";
      sql += " ,VL_ICMS_ANTECIPADO = 0";
      sql += " ,CD_CFO_CONHECIMENTO = '' ";
      sql += " ,DM_Tipo_Calculo = ''";
      sql += " WHERE OID_Conhecimento = '" + OID_Conhecimento + "'";

      // System.out.println (" delete cto" + sql);

      i = executasql.executarUpdate (sql);

      // System.out.println (" delete cto ok");

      // System.out.println (" parametro>>> " + parametro_FixoED.getNM_Empresa ());

      if (DM_Tipo_Conhecimento != null && DM_Tipo_Conhecimento.equals ("2") &&
          parametro_FixoED.getDM_Frete_Cortesia ().equals ("PARAMETRO")) {
        // --------------------CORTESIA TIPO(2)--------------------------------------------
        Tipo_Movimento = "FP";
        tipo_calculo="cortesia";
        // System.out.println (" CORTESIA 1 ");
        VL_Movimento_Frete = parametro_FixoED.getVL_Frete_Cortesia ();
        gera_movimento (Tipo_Movimento , VL_Movimento_Frete , OID_Conhecimento);
      }

      if ("Tabela".equals(tipo_calculo)) {

         DM_Tem_Peso = "N";
         DM_Tem_Modal = "N";
         DM_Tem_Validade = "N";
         DM_Tem_Vigencia = "N";
         DM_Tem_Empresa = "N";
         DM_Tem_Cidade = "N";
         DM_Tem_Regiao_Estado = "N";
         DM_Tem_Estado = "N";
         DM_Tem_Subregiao = "N";
         DM_Tem_Regiao_Pais = "N";
         DM_Tem_Pais = "N";
         DM_Tipo_Tabela_Frete = "N";
         DM_Tem_Tabela_Unidade = "N";
         DM_Tabela_Reversa = "N";


        if ("R".equals (ed.getDM_Tipo_Tabela_Frete ())) {
          if ("Rodov01".equals (parametro_FixoED.getNM_Modelo_Tabela_Rodoviario ())) {
            // System.out.println (" calc levaetraz rod");
            DM_Tem_Peso = "S";
            DM_Tem_Modal = "S";
            DM_Tem_Vigencia = "S";
            DM_Tem_Cidade = "S";
            DM_Tem_Regiao_Estado = "S";
            DM_Tem_Estado = "S";
            DM_Tem_Pais = "S";
            conED = calcula_frete_Tabela_Rodov01 (localizaCondicaoTabela (ed));
          }
        }
        if ("R".equals (ed.getDM_Tipo_Tabela_Frete ())) {
          if ("Rodov02".equals (parametro_FixoED.getNM_Modelo_Tabela_Rodoviario ())) {
            // System.out.println (" calc kieling rod ");
            DM_Tem_Cidade = "S";
            DM_Tem_Estado = "S";
            DM_Tem_Subregiao = "S";
            DM_Tem_Pais = "S";
            DM_Tipo_Tabela_Frete = "S";
            DM_Tem_Tabela_Unidade = "S";
            conED = calcula_frete_Tabela_Rodov02 (localizaCondicaoTabela (ed));
          }
        }
        if ("C".equals (ed.getDM_Tipo_Tabela_Frete ())) {
          if ("Aereo02".equals (parametro_FixoED.getNM_Modelo_Tabela_Aereo ())) {
            // System.out.println (" calc kieling aereo ");
            DM_Tem_Cidade = "S";
            DM_Tem_Estado = "S";
            DM_Tem_Subregiao = "S";
            DM_Tem_Pais = "S";
            DM_Tipo_Tabela_Frete = "S";
            DM_Tem_Tabela_Unidade = "S";

            conED = calcula_frete_Tabela_Aereo02 (localizaCondicaoTabela (ed));
          }
        }
        if ("D".equals (ed.getDM_Tipo_Tabela_Frete ())) {
          if ("Documento02".equals (parametro_FixoED.getNM_Modelo_Tabela_Aereo ())) {
            // System.out.println (" calc kieling documentos ");
            DM_Tem_Cidade = "S";
            DM_Tem_Estado = "S";
            DM_Tem_Subregiao = "S";
            DM_Tem_Pais = "S";
            DM_Tipo_Tabela_Frete = "S";
            DM_Tem_Tabela_Unidade = "S";

            conED = calcula_frete_Tabela_Documento02 (localizaCondicaoTabela (ed));
          }
        }

      }

      // System.out.println (" retornou do calc ");

      // --------------------TAXA DESPACHO-----------------------------------------------
      sql = " SELECT Clientes.VL_Taxa_Despacho FROM Clientes ";
      sql += " WHERE  VL_Taxa_Despacho >0 AND  Clientes.OID_Pessoa = '" +
          ed.getOID_Pessoa_Pagador () + "'";

      // System.out.println (" TAXA DESPACHO  " + sql);
      Tipo_Movimento = "TDC";
      VL_Movimento_Frete = 0;

      rs = this.executasql.executarConsulta (sql);
      while (rs.next ()) {
        // System.out.println (" Taxa dsp OK ");
        VL_Movimento_Frete = (rs.getDouble ("VL_Taxa_Despacho"));
        gera_movimento (Tipo_Movimento , VL_Movimento_Frete , OID_Conhecimento);
      }

      if (VL_Movimento_Frete == 0 &&
          ed.getOID_Pessoa_Destinatario ().equals (ed.getOID_Pessoa_Pagador ())) {
        sql = " SELECT Clientes.VL_Taxa_Despacho FROM Clientes ";
        sql += " WHERE  VL_Taxa_Despacho >0 AND  Clientes.OID_Pessoa = '" +
            ed.getOID_Pessoa () + "'";

        // System.out.println (" TAXA DESPACHO FOB dir  " + sql);
        Tipo_Movimento = "TDC";
        VL_Movimento_Frete = 0;

        rs = this.executasql.executarConsulta (sql);
        while (rs.next ()) {
          // System.out.println (" Taxa dsp OK ");
          VL_Movimento_Frete = (rs.getDouble ("VL_Taxa_Despacho"));
          gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                          OID_Conhecimento);
        }
      }

      // System.out.println (" Taxa dsp FIM ");

      this.totaliza_movimento (ed.getOID_Conhecimento ());

      // System.out.println (" incicio do fim  ");

      if (VL_Total_Frete > 0) {

        // System.out.println (" fim  1 ");

        // --------------------CUSTO FINANCEIRO--------------------------------------------
        // System.out.println (" fim cf ");
        Tipo_Movimento = "CF";
        VL_Movimento_Frete = ( (VL_Total_Frete * PE_Juros_Cobranca) / 100);
        gera_movimento (Tipo_Movimento , VL_Movimento_Frete , OID_Conhecimento);

        // System.out.println (" fim 1 ");

        // --------------------ICMS--------------------------------------------------------
        Tipo_Movimento = "ICM";

        DM_CIF_FOB = "F";
        if (ed.getDM_Tipo_Pagamento ().equals ("1")) {
          DM_CIF_FOB = "C";
        }

        // System.out.println (" fim icm ");

        OID_Estado_Origem_Taxas = ed.getOID_Estado_Origem ();
        if (parametro_FixoED.getDM_Estado_Origem_Taxas ().equals ("U")) {
          sql = " SELECT Estados.OID_Estado FROM Cidades, Regioes_Estados, Estados, Pessoas, Unidades ";
          sql += " WHERE Estados.OID_Estado = Regioes_Estados.OID_Estado ";
          sql +=
              " AND Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado ";
          sql += " AND Pessoas.oid_Cidade  = Cidades.OID_Cidade ";
          sql += " AND Pessoas.oid_Pessoa  = Unidades.OID_Pessoa ";
          sql += " AND Unidades.OID_Unidade = " + ed.getOID_Unidade ();

          rs = this.executasql.executarConsulta (sql);
          // System.out.println (" inicio sql estado ");
          while (rs.next ()) {
            OID_Estado_Unidade = rs.getLong ("OID_Estado");
            OID_Estado_Origem_Taxas = rs.getLong ("OID_Estado");
          }
        }

        PE_Aliquota_ICMS = 0;
        DM_ICMS_CIF = "";
        DM_ICMS_FOB = "";
        CD_CFO = "";

        sql = " SELECT Pessoas.NM_Inscricao_Estadual FROM Pessoas ";
        sql += " WHERE Pessoas.OID_Pessoa = '" + ed.getOID_Pessoa_Pagador () +
            "'";

        // System.out.println (" sql inscr estad " + sql);

        rs = null;
        rs = this.executasql.executarConsulta (sql);

        while (rs.next ()) {
          NM_Inscricao_Estadual = rs.getString ("NM_Inscricao_Estadual");
        }

        // System.out.println (" verifica isencao icsm  ");

        OID_Estado_Destino_Taxas = ed.getOID_Estado_Destino ();
        if (parametro_FixoED.getDM_Estado_Destino_Taxas ().equals ("P")) {
          sql =
              " SELECT  Estados.OID_Estado FROM Cidades, Regioes_Estados, Estados, Pessoas ";
          sql += " WHERE   Estados.OID_Estado = Regioes_Estados.OID_Estado ";
          sql +=
              " AND Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado ";
          sql += " AND Cidades.OID_Cidade = Pessoas.oid_Cidade ";
          sql += " AND Pessoas.OID_Pessoa = '" + ed.getOID_Pessoa_Pagador () +
              "'";

          // System.out.println (" sql pessoa estado dest  " + sql);

          rs = null;
          rs = this.executasql.executarConsulta (sql);
          while (rs.next ()) {
            // System.out.println (" sql pessoa pagador estado destino ok");
            OID_Estado_Pagador = rs.getLong ("OID_Estado");
            OID_Estado_Destino_Taxas = rs.getLong ("OID_Estado");
          }
        }

        sql = " SELECT Clientes.DM_PIS_COFINS, Clientes.PE_PIS_COFINS, Clientes.DM_Isencao_ICMS, Clientes.DM_Isencao_Seguro, Ramos_Atividades.CD_Grupo_CFO FROM Clientes, Ramos_Atividades ";
        sql +=
            " WHERE  Clientes.oid_ramo_atividade = Ramos_Atividades.oid_ramo_atividade ";
        sql += " AND    Clientes.OID_Pessoa = '" + ed.getOID_Pessoa_Pagador () +
            "'";

        // System.out.println (" sql cliente pagador  " + sql);

        rs = null;
        rs = this.executasql.executarConsulta (sql);
        CD_Grupo_CFO = "351";
        PE_PIS_COFINS = 0;
        DM_PIS_COFINS = parametro_FixoED.getDM_Calcula_PIS_COFINS ();
        while (rs.next ()) {
          // System.out.println (" sql cliente pagador  ok");
          DM_Isencao_ICMS = rs.getString ("DM_Isencao_ICMS");
          DM_Isencao_Seguro = rs.getString ("DM_Isencao_Seguro");
          PE_PIS_COFINS = rs.getDouble ("PE_PIS_COFINS");
          if (rs.getString ("CD_Grupo_CFO") != null &&
              !rs.getString ("CD_Grupo_CFO").equals ("") &&
              !rs.getString ("CD_Grupo_CFO").equals ("null")) {
            CD_Grupo_CFO = rs.getString ("CD_Grupo_CFO");
          }
          if (rs.getString ("DM_PIS_COFINS") != null &&
              !rs.getString ("DM_PIS_COFINS").equals ("") &&
              !rs.getString ("DM_PIS_COFINS").equals ("null")) {
            DM_PIS_COFINS = rs.getString ("DM_PIS_COFINS");
          }

        }

        sql = " SELECT  CD_Pais FROM Estados, Regioes_Paises, Paises " +
            " WHERE   Estados.OID_Regiao_Pais = Regioes_Paises.OID_Regiao_Pais " +
            " AND     Regioes_Paises.OID_Pais = Paises.OID_Pais " +
            " AND     Estados.OID_Estado = " + OID_Estado_Origem_Taxas;

        rs = this.executasql.executarConsulta (sql);
        while (rs.next ()) {
          CD_Pais_Origem = rs.getString ("CD_Pais").toUpperCase ();
          if (CD_Pais_Origem.equals ("BR")) {
            CD_Pais_Origem = "BRA";
          }
        }

        if (CD_Pais_Origem != null && !CD_Pais_Origem.equals ("BRA")) {
          sql = " SELECT Estados.OID_Estado FROM Cidades, Regioes_Estados, Estados, Pessoas, Unidades ";
          sql += " WHERE Estados.OID_Estado = Regioes_Estados.OID_Estado ";
          sql +=
              " AND Cidades.OID_Regiao_Estado = Regioes_Estados.OID_Regiao_Estado ";
          sql += " AND Pessoas.oid_Cidade  = Cidades.OID_Cidade ";
          sql += " AND Pessoas.oid_Pessoa  = Unidades.OID_Pessoa ";
          sql += " AND Unidades.OID_Unidade = " + ed.getOID_Unidade ();

          rs = this.executasql.executarConsulta (sql);
          while (rs.next ()) {
            OID_Estado_Unidade = rs.getLong ("OID_Estado");
            OID_Estado_Origem_Taxas = rs.getLong ("OID_Estado");
          }
        }

        sql = " SELECT  CD_Pais FROM Estados, Regioes_Paises, Paises " +
            " WHERE   Estados.OID_Regiao_Pais = Regioes_Paises.OID_Regiao_Pais " +
            " AND     Regioes_Paises.OID_Pais = Paises.OID_Pais " +
            " AND     Estados.OID_Estado = " + OID_Estado_Destino_Taxas;

        rs = this.executasql.executarConsulta (sql);
        while (rs.next ()) {
          CD_Pais_Destino = rs.getString ("CD_Pais").toUpperCase ();
          if (CD_Pais_Destino.equals ("BR")) {
            CD_Pais_Destino = "BRA";
          }
          if (!CD_Pais_Destino.equals ("BRA")) {
            OID_Estado_Destino_Taxas = OID_Estado_Origem_Taxas;
          }
        }

        sql = " SELECT * FROM Taxas ";
        sql += " WHERE Taxas.OID_Estado = " + OID_Estado_Origem_Taxas;
        sql += " AND   Taxas.OID_Estado_Destino = " + OID_Estado_Destino_Taxas;
        // System.out.println ("taxas " + sql);

        rs = null;
        rs = this.executasql.executarConsulta (sql);

        while (rs.next ()) {

          oid_Taxa = rs.getLong ("oid_Taxa");
          // System.out.println (" tem taxas ->>>  " + oid_Taxa);

          //PE_Aliquota_Seguro = rs.getDouble("PE_Aliquota_Seguro");
          PE_Aliquota_COFINS = rs.getDouble ("PE_Aliquota_COFINS");
          PE_Aliquota_PIS = rs.getDouble ("PE_Aliquota_PIS");

          if (parametro_FixoED.getPE_Aliquota_PIS () > 0) {
            PE_Aliquota_PIS = parametro_FixoED.getPE_Aliquota_PIS ();
          }
          if (parametro_FixoED.getPE_Aliquota_COFINS () > 0) {
            PE_Aliquota_COFINS = parametro_FixoED.getPE_Aliquota_COFINS ();
          }

          if (PE_PIS_COFINS > 0) {
            PE_Aliquota_PIS = 0;
            PE_Aliquota_COFINS = PE_PIS_COFINS;
          }

          CD_CFO = "5"; // rs.getString("CD_CFO");
          if (ed.getOID_Estado_Origem () != ed.getOID_Estado_Destino ()) {
            CD_CFO = "6";
          }

          if (NM_Inscricao_Estadual != null &&
              !NM_Inscricao_Estadual.equals ("") &&
              !NM_Inscricao_Estadual.equals ("null")) {
            PE_Aliquota_ICMS = rs.getDouble ("PE_Aliquota_ICMS");
            DM_ICMS_CIF = rs.getString ("DM_ICMS_CIF");
            DM_ICMS_FOB = rs.getString ("DM_ICMS_FOB");
          }
          else {
            // System.out.println ("icms nao contr ->> ICN");

            Tipo_Movimento = "ICN";
            PE_Aliquota_ICMS = rs.getDouble ("PE_ICMS_Nao_Contribuinte");
            CD_Grupo_CFO = "357";
            DM_ICMS_CIF = "S";
            DM_ICMS_FOB = "S";
          }
        }

        // System.out.println (" taxas ");

        if (!CD_CFO.equals ("")) {
          CD_CFO_CONHECIMENTO = CD_CFO + CD_Grupo_CFO;
        }

        //Situacao Nova (Remetente é pagador não do estado e tem que cobrar icms

        if (parametro_FixoED.getDM_Estado_Origem_Taxas ().equals ("U") &&
            parametro_FixoED.getDM_Estado_Destino_Taxas ().equals ("P") &&
            ed.getOID_Estado_Origem () == ed.getOID_Estado_Destino () &&
            ed.getOID_Estado_Origem () == OID_Estado_Unidade &&
            ed.getOID_Estado_Origem () != OID_Estado_Pagador) {
          DM_Isencao_ICMS = "R";
          // System.out.println (" taxas situacao especial");
        }

        if (DM_Isencao_ICMS != null && !DM_Isencao_ICMS.equals ("R")) {
          if ( (DM_CIF_FOB != null && DM_CIF_FOB.equals ("C") &&
                DM_ICMS_CIF.equals ("N")) ||
              (DM_CIF_FOB != null && DM_CIF_FOB.equals ("F") &&
               DM_ICMS_FOB.equals ("N")) ||
              (DM_Isencao_ICMS != null && DM_Isencao_ICMS.equals ("S"))) {
            PE_Aliquota_ICMS = 0;
          }
        }

        // System.out.println (" taxas 2");

        if (PE_Aliquota_ICMS > 0) {
          VL_ICMS = (VL_Base_ICMS / ( (100 - PE_Aliquota_ICMS) / 100) -
                     VL_Base_ICMS);

          if (parametro_FixoED.getDM_Inclui_ICMS_Parcela_CTRC ().equals ("S")) {

            sql = " SELECT VL_Movimento, oid_Movimento_Conhecimento FROM Tipos_Movimentos, Movimentos_Conhecimentos " +
                " WHERE Movimentos_Conhecimentos.oid_Tipo_Movimento = Tipos_Movimentos.oid_Tipo_Movimento " +
                " AND DM_Base_ICMS='S' and VL_Movimento >0 " +
                " AND Movimentos_Conhecimentos.OID_Conhecimento = '" +
                ed.getOID_Conhecimento () + "'";

            rs = this.executasql.executarConsulta (sql);
            VL_Base_ICMS = 0;
            double VL_Movimento_Mais_ICMS = 0;
            while (rs.next ()) {
              // System.out.println (" embutindo icms ");
              VL_Movimento_Mais_ICMS = rs.getDouble ("VL_Movimento") /
                  ( (100 - PE_Aliquota_ICMS) / 100);
              VL_Base_ICMS += VL_Movimento_Mais_ICMS;

              sql = " UPDATE Movimentos_Conhecimentos SET ";
              sql += " VL_Movimento = " + VL_Movimento_Mais_ICMS;
              sql += " WHERE oid_Movimento_Conhecimento = '" +
                  rs.getString ("oid_Movimento_Conhecimento") + "'";

              // System.out.println (" update mov + icms cto > " + sql);

              i = executasql.executarUpdate (sql);
            }

            this.totaliza_movimento (ed.getOID_Conhecimento ());
          }
        }

        VL_ICMS_Antecipado = 0;
        if (ed.getDM_Tipo_Conhecimento () != null &&
            ed.getDM_Tipo_Conhecimento ().equals ("5")) { //ICMS Antecipado
          VL_ICMS_Antecipado = VL_ICMS;
          VL_ICMS = 0;
          Tipo_Movimento = "ICA";
        }
        if (DM_Tipo_Conhecimento.equals ("8")) {
          Tipo_Movimento = "ICC";
          VL_ICMS = VL_Base_ICMS;
        }

        VL_Movimento = VL_ICMS + VL_ICMS_Antecipado;
        gera_movimento (Tipo_Movimento , VL_Movimento , OID_Conhecimento);

        sql = " SELECT SUM(Movimentos_Conhecimentos.VL_Movimento) AS Valor_Movimento FROM Movimentos_Conhecimentos, Tipos_Movimentos ";
        sql += " WHERE Tipos_Movimentos.OID_Tipo_Movimento = Movimentos_Conhecimentos.OID_Tipo_Movimento ";
        sql += " AND Tipos_Movimentos.DM_Calcula_Frete = 'S'";
        sql += " AND Movimentos_Conhecimentos.OID_Conhecimento = '" +
            OID_Conhecimento + "'";

        rs = null;
        rs = this.executasql.executarConsulta (sql);

        while (rs.next ()) {
          VL_Total_Frete_Calculado = rs.getDouble ("Valor_Movimento");
        }

        if (DM_Tipo_Conhecimento.equals ("8")) {
          VL_Base_ICMS = VL_ICMS;
        }
        else {
          VL_Base_ICMS = VL_Base_ICMS + VL_ICMS + VL_ICMS_Antecipado;
          if (DM_ICMS_Incluso_Frete != null &&
              DM_ICMS_Incluso_Frete.equals ("S")) {
            VL_Total_Frete_Calculado = VL_Total_Frete_Calculado - VL_ICMS -
                VL_ICMS_Antecipado; ;
            VL_Base_ICMS = VL_Total_Frete_Calculado;
          }
        }

        // --------------------BASE DE ICMS------------------------------------------------
        Tipo_Movimento = "BIC";
        if (DM_Tipo_Conhecimento.equals ("8")) {
          Tipo_Movimento = "BCC"; //ICMS COMPLEM
        }
        gera_movimento (Tipo_Movimento , VL_Base_ICMS , OID_Conhecimento);

        // System.out.println (" VL_Base_ICMS ----------->>>  " + VL_Base_ICMS);
        // System.out.println (" VL_ICMS ->>>  " + VL_ICMS);

        // --------------------PIS---------------------------------------------------------
        Tipo_Taxa = "Taxas";
        Tipo_Movimento = "PIS";

        VL_PIS = ( (VL_Total_Frete_Calculado * PE_Aliquota_PIS) / 100);
        VL_Movimento = VL_PIS;
        gera_movimento (Tipo_Movimento , VL_Movimento , OID_Conhecimento);

        // System.out.println (" pis " + VL_Movimento);

        // --------------------COFINS------------------------------------------------------
        Tipo_Movimento = "CFN";

        VL_COFINS = ( (VL_Total_Frete_Calculado * PE_Aliquota_COFINS) / 100);
        VL_Movimento = VL_COFINS;
        gera_movimento (Tipo_Movimento , VL_Movimento , OID_Conhecimento);

        // System.out.println (" COFINS " + VL_Movimento);

        // --------------------TOTAL DO FRETE----------------------------------------------
        Tipo_Movimento = "TFC";
        // System.out.println ("calc TFC");

        if (parametro_FixoED.getDM_Calcula_PIS_COFINS ().equals ("S")) {
          if (DM_PIS_COFINS != null && !DM_PIS_COFINS.equals ("X") &&
              !DM_PIS_COFINS.equals ("F")) {
            VL_Total_Frete_Calculado = VL_Total_Frete_Calculado + VL_COFINS +
                VL_PIS;
          }
        }
        if (DM_Tipo_Conhecimento.equals ("8")) {
          VL_Total_Frete_Calculado = VL_ICMS;
        }

        gera_movimento (Tipo_Movimento , VL_Total_Frete_Calculado ,
                        OID_Conhecimento);

        // --------------------SEGURO------------------------------------------------------
        if (CD_Pais_Origem != null && CD_Pais_Origem.equals ("BRA") &&
            CD_Pais_Destino != null && CD_Pais_Destino.equals ("BRA")) {
          sql = " SELECT * FROM Taxas ";
          sql += " WHERE Taxas.OID_Estado = " + ed.getOID_Estado_Origem ();
          sql += " AND   Taxas.OID_Estado_Destino = " +
              ed.getOID_Estado_Destino ();
          rs = this.executasql.executarConsulta (sql);
          while (rs.next ()) {
            PE_Aliquota_Seguro = rs.getDouble ("PE_Aliquota_Seguro");
          }
          Tipo_Movimento = "SEG";
          if (Vl_Nota_Fiscal_Seguro > 0){
              VL_Seguro = ( (Vl_Nota_Fiscal_Seguro * PE_Aliquota_Seguro) / 100);
          }else{
              VL_Seguro = ( (VL_Nota_Fiscal * PE_Aliquota_Seguro) / 100);
          }

          if (ed.getDM_Tipo_Tabela_Frete () != null &&
              !ed.getDM_Tipo_Tabela_Frete ().equals ("null") &&
              !ed.getDM_Tipo_Tabela_Frete ().equals ("") &&
              (ed.getDM_Tipo_Tabela_Frete ().equals ("C") ||
               ed.getDM_Tipo_Tabela_Frete ().equals ("D"))) { ///AEREO
              if (Vl_Nota_Fiscal_Seguro > 0){
            	  VL_Seguro = (Vl_Nota_Fiscal_Seguro * 0.132 / 100);
              }else{
            	  VL_Seguro = (VL_Nota_Fiscal * 0.132 / 100);
              }
          }
        }
        if (DM_Isencao_Seguro != null && DM_Isencao_Seguro.equals ("S")) {
          VL_Seguro = 0;
        }
        // System.out.println (" SEGURO 2 " + VL_Movimento);

        VL_Movimento = VL_Seguro;
        gera_movimento (Tipo_Movimento , VL_Movimento , OID_Conhecimento);

        // --------------------CARREGA ED VOLTA--------------------------------------------

        conED.setVL_Total_Frete (VL_Total_Frete_Calculado);
        conED.setVL_Nota_Fiscal (VL_Nota_Fiscal);
        conED.setVL_ICMS (VL_ICMS + VL_ICMS_Antecipado);
        conED.setNR_Peso (NR_Peso_NF);
        if (isCubagemConhecimento (DM_Tipo_Conhecimento)) {
          conED.setNR_Peso_Cubado (NR_Cubagem_Total);
        }
        else {
          conED.setNR_Peso_Cubado (NR_Peso_Cubado_NF);
        }
        conED.setVL_Volumes (NR_Volumes_NF);

        // --------------------CUSTO DE COLETA---------------------------------------------
        // System.out.println (" oid coleta ->>>" + oid_Coleta);
        Tipo_Movimento = "COL";
        if (oid_Coleta > 0) {
          sql =
              " SELECT VL_Custo, NR_Peso, VL_Mercadoria, QT_Volumes FROM Coletas ";
          sql += " WHERE Coletas.oid_Coleta = " + oid_Coleta;
          // System.out.println (" coleta ->>>" + sql);

          rs = null;
          rs = this.executasql.executarConsulta (sql);

          while (rs.next ()) {
            VL_Movimento_Frete = (rs.getDouble ("VL_Custo") /
                                  rs.getDouble ("NR_Peso")) * NR_Peso_NF;
            gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                            OID_Conhecimento);
          }
        }

        // --------------------ENTREGADORA-------------------------------------------------
        DM_Tem_Entregador = "N";

        // System.out.println (" pesso ent " + OID_Pessoa_Entregadora);

        if (OID_Pessoa_Entregadora != null &&
            !OID_Pessoa_Entregadora.equals ("null") &&
            !OID_Pessoa_Entregadora.equals ("") &&
            !OID_Pessoa_Entregadora.equals (null)) {
          DM_Tem_Entregador = "S";
          calcula_frete_Entrega (ed , VL_Total_Frete_Calculado);

          // System.out.println (" entregadora " + OID_Pessoa_Entregadora);
          conED.setOID_Pessoa_Entregador (OID_Pessoa_Entregadora);

          // --------------------PREVISAO DE ENTREGA-----------------------------------------
          if (NR_Prazo_Entrega <= 0) {
            NR_Prazo_Entrega = 1;
          }
          Calendar cal = Data.stringToCalendar (ed.getDT_Emissao () ,
                                                "dd/MM/yyyy");
          int diasAtuais = cal.get (Calendar.DAY_OF_MONTH);
          cal.set (Calendar.DAY_OF_MONTH , diasAtuais + NR_Prazo_Entrega);
          DT_Previsao_Entrega = (dateFormatter.calendarToString (cal ,
              SHORT_DATE));

          // System.out.println (" prev entrega" + DT_Previsao_Entrega);
        }

        // --------------------MARGEM------------------------------------------------------
        Tipo_Movimento = "TF";
        //gera_movimento(Tipo_Movimento, VL_Movimento_Frete, OID_Conhecimento);


        // --------------------AT. CTO-----------------------------------------------------

        // System.out.println (" limpar mov tipo cto" + DM_Tipo_Conhecimento);

        // System.out.println (" ed.getVL_Peso_Cubado()" + ed.getVL_Peso_Cubado ());

        sql = " UPDATE Conhecimentos SET ";
        sql += "  NR_PESO = " + NR_Peso_NF;
        if (isCubagemConhecimento (DM_Tipo_Conhecimento)) {
          sql += " ,NR_PESO_CUBADO = " + NR_Cubagem_Total;
        }
        else {
          sql += " ,NR_PESO_CUBADO = " + NR_Peso_Cubado_NF;
        }
        sql += " ,NR_VOLUMES = " + NR_Volumes_NF;
        sql += " ,oid_Taxa = " + oid_Taxa;
        sql += " ,PE_ALIQUOTA_ICMS = " + PE_Aliquota_ICMS;
        sql += " ,VL_NOTA_FISCAL = " + VL_Nota_Fiscal;
        sql += " ,VL_FRETE_PESO = " + VL_Frete_Peso;
        sql += " ,VL_FRETE_VALOR = " + VL_AD_Valorem;
        sql += " ,VL_SEC_CAT = " + VL_SEC_CAT;
        sql += " ,VL_PEDAGIO = " + VL_Pedagio;
        sql += " ,VL_DESPACHO = " + VL_Despacho;
        sql += " ,VL_PIS = " + VL_PIS;
        sql += " ,VL_COFINS = " + VL_COFINS;
        sql += " ,VL_OUTROS1 = " + VL_Outros1;
        sql += " ,VL_OUTROS2 = " + VL_Outros2;
        sql += " ,VL_OUTROS3 = " + VL_Outros3;
        sql += " ,VL_OUTROS4 = " + VL_Outros4;
        sql += " ,VL_OUTROS5 = " + VL_Outros5;
        sql += " ,VL_OUTROS6 = " + VL_Outros6;
        sql += " ,VL_OUTROS7 = " + VL_Outros7;
        sql += " ,VL_OUTROS8 = " + VL_Outros8;
        sql += " ,VL_OUTROS9 = " + VL_Outros9;
        sql += " ,VL_OUTROS10 = " + VL_Outros10;
        sql += " ,VL_TOTAL_FRETE = " + VL_Total_Frete_Calculado;
        sql += " ,VL_BASE_CALCULO_ICMS = " + VL_Base_ICMS;
        sql += " ,VL_TOTAL_CUSTO = " + VL_Total_Custo;
        sql += " ,VL_Custo_Coleta = " + VL_Custo_Coleta;
        sql += " ,VL_Custo_Transferencia = " + VL_Custo_Transferencia;
        sql += " ,VL_Custo_Entrega = " + VL_Custo_Entrega;
        sql += " ,VL_Custo_Seguro = " + VL_Custo_Seguro;
        sql += " ,VL_Custo_Imposto = " + VL_Custo_Imposto;
        sql += " ,VL_Custo_Outros = " + VL_Custo_Outros;
        sql += " ,VL_ICMS = " + VL_ICMS;
        sql += " ,VL_ICMS_ANTECIPADO = " + VL_ICMS_Antecipado;
        sql += " ,CD_CFO_CONHECIMENTO = '" + CD_CFO_CONHECIMENTO + "'";
        if (DT_Previsao_Entrega != null && !DT_Previsao_Entrega.equals ("") &&
            !DT_Previsao_Entrega.equals ("null")) {
          sql += " ,DT_Previsao_Entrega = '" + DT_Previsao_Entrega + "'";
        }

        if (DM_Tem_Entregador.equals ("S")) {
          sql += " ,OID_Pessoa_Entregadora = '" + OID_Pessoa_Entregadora + "'";
        }
        sql += " ,DM_Tipo_Calculo = 'T'";

        sql += " WHERE OID_Conhecimento = '" + OID_Conhecimento + "'";

        // System.out.println (" update cto > " + sql);

        i = executasql.executarUpdate (sql);

        if (DM_Tipo_Conhecimento.equals ("889988888")) { //icms complementar
          sql = " SELECT oid_Movimento_Conhecimento from  Movimentos_Conhecimentos, Tipos_Movimentos  " +
              " WHERE Movimentos_Conhecimentos.oid_Tipo_Movimento = Tipos_Movimentos.oid_Tipo_Movimento" +
              " AND Movimentos_Conhecimentos.OID_Conhecimento = '" +
              ed.getOID_Conhecimento () + "'" +
              " AND Tipos_Movimentos.DM_Tipo_Movimento = 'I' ";
          rs = this.executasql.executarConsulta (sql);
          while (rs.next ()) {
            sql =
                "delete from Movimentos_Conhecimentos WHERE oid_Movimento_Conhecimento = ";
            sql += "('" + rs.getString ("oid_Movimento_Conhecimento") + "')";
            // System.out.println (" exclui movimentos nao icms compl " + sql);

            i = executasql.executarUpdate (sql);
          }
        }

        // System.out.println (" update cto ok " + VL_Movimento_Frete);

      }
    }

    catch (SQLException exc) {
      throw new Excecoes (exc.getMessage () , exc , getClass ().getName () ,
                          "calcula frete");
    }
    return conED;
  }

  public Calcula_FreteED calcula_frete_Entregadora (Calcula_FreteED ed) throws
      Excecoes {

    // System.out.println (" calcula frete entregadora");

    String sql = null;
    long valOid = 0;
    String tipo_calculo = null;

    OID_Pessoa_Entregadora = ed.getOID_Pessoa_Entregador ();

    // System.out.println ("OID_Pessoa_Entregadora " + OID_Pessoa_Entregadora);

    Calcula_FreteED conED = new Calcula_FreteED ();

    DM_Tipo_Conhecimento = ed.getDM_Tipo_Conhecimento ();

    try {

      sql = " SELECT Produtos.DM_Unidade, Notas_Fiscais.NR_Nota_Fiscal, Notas_Fiscais.VL_Nota_Fiscal, Notas_Fiscais.DM_Tipo_Conhecimento, Notas_Fiscais.NR_PESO, Notas_Fiscais.NR_PESO_CUBADO, Notas_Fiscais.NR_VOLUMES, Conhecimentos.oid_Coleta, Notas_Fiscais.NR_ITENS, Notas_Fiscais.DM_TRANSFERENCIA FROM Notas_Fiscais, Produtos, Conhecimentos_Notas_Fiscais, Conhecimentos ";
      sql += " WHERE Notas_Fiscais.OID_Nota_Fiscal = Conhecimentos_Notas_Fiscais.OID_Nota_Fiscal ";
      sql += " AND Conhecimentos.OID_Conhecimento  = Conhecimentos_Notas_Fiscais.OID_Conhecimento ";
      sql += " AND Conhecimentos.OID_Produto = Produtos.OID_Produto ";
      sql += " AND Conhecimentos_Notas_Fiscais.OID_Conhecimento = '" +
          ed.getOID_Conhecimento () + "'";

      rs = null;
      rs = this.executasql.executarConsulta (sql);

      // System.out.println (" nf sql" + sql);

      while (rs.next ()) {
        // System.out.println (" LENDO nf do cto" + rs.getString ("NR_Nota_Fiscal"));

        VL_Nota_Fiscal = VL_Nota_Fiscal + rs.getDouble ("VL_NOTA_FISCAL");
        NR_Peso_NF = NR_Peso_NF + rs.getDouble ("NR_PESO");
        if (rs.getDouble ("NR_PESO_CUBADO") > rs.getDouble ("NR_PESO")) {
          NR_Peso_Cubado_NF = NR_Peso_Cubado_NF +
              rs.getDouble ("NR_PESO_CUBADO");
        }
        else {
          NR_Peso_Cubado_NF = NR_Peso_Cubado_NF + rs.getDouble ("NR_PESO");
        }
        NR_Volumes_NF = NR_Volumes_NF + rs.getLong ("NR_VOLUMES");
        NR_Itens_NF = NR_Itens_NF + rs.getDouble ("NR_ITENS");
        DM_Transferencia = rs.getString ("DM_Transferencia");
        DM_Unidade_Produto = rs.getString ("DM_Unidade");
        if (rs.getString ("DM_Tipo_Conhecimento") != null &&
            DM_Tipo_Conhecimento == null) {
          DM_Tipo_Conhecimento = rs.getString ("DM_Tipo_Conhecimento");
        }
        oid_Coleta = rs.getLong ("oid_Coleta");
      }

      // System.out.println (" DM_Tipo_Conhecimento NF  " + DM_Tipo_Conhecimento);

      String Tipo_Cto = "1";
      if (DM_Tipo_Conhecimento != null) {
        Tipo_Cto = DM_Tipo_Conhecimento;
      }
      DM_Tipo_Conhecimento = Tipo_Cto;

      // System.out.println (" Tipo Cto " + Tipo_Cto);

      NR_Peso_Tabela = NR_Peso_Cubado_NF;
      if (DM_Unidade_Produto != null && DM_Unidade_Produto.equals ("P")) {
        NR_Peso_Tabela = NR_Peso_NF;
      }
      if (DM_Unidade_Produto != null && DM_Unidade_Produto.equals ("V")) {
        NR_Peso_Tabela = NR_Volumes_NF;
      }
      if (DM_Unidade_Produto != null && DM_Unidade_Produto.equals ("I")) {
        NR_Peso_Tabela = NR_Itens_NF;
      }

      OID_Conhecimento = ed.getOID_Conhecimento ();

      sql = " SELECT oid_Movimento_Conhecimento from  Movimentos_Conhecimentos, Tipos_Movimentos  " +
          " WHERE Movimentos_Conhecimentos.oid_Tipo_Movimento = Tipos_Movimentos.oid_Tipo_Movimento" +
          " AND Movimentos_Conhecimentos.OID_Conhecimento = '" +
          ed.getOID_Conhecimento () + "'" +
          " AND Tipos_Movimentos.CD_Tipo_Movimento = 'ENT' ";
      rs = this.executasql.executarConsulta (sql);
      while (rs.next ()) {
        sql =
            "delete from Movimentos_Conhecimentos WHERE oid_Movimento_Conhecimento = ";
        sql += "('" + rs.getString ("oid_Movimento_Conhecimento") + "')";
        // System.out.println (" exclui movimentos  " + sql);
        int i = executasql.executarUpdate (sql);
      }

      // System.out.println (" limpou mov");

      sql = " UPDATE Conhecimentos SET ";
      sql += " VL_Custo_Entrega = 0";
      sql += " WHERE OID_Conhecimento = '" + OID_Conhecimento + "'";

      // System.out.println (" zerou custo entrega cto" + sql);

      int i = executasql.executarUpdate (sql);

      // System.out.println (" delete cto ok");

      // System.out.println (" parametro = " + parametro_FixoED.getNM_Empresa ());

      if (parametro_FixoED.getNM_Empresa ().equals ("MEGATRENDS") ||
          parametro_FixoED.getNM_Empresa ().equals ("MEGATRENDS_TESTE")) {
        // System.out.println (" calc mega ");
        DM_Tem_Peso = "S";
        DM_Tem_Modal = "S";
        DM_Tem_Validade = "N";
        DM_Tem_Vigencia = "S";
        DM_Tem_Empresa = "S";
        DM_Tem_Cidade = "S";
        DM_Tem_Regiao_Estado = "S";
        DM_Tem_Estado = "S";
        DM_Tem_Subregiao = "N";
        DM_Tem_Regiao_Pais = "N";
        DM_Tem_Pais = "S";
      }

      // --------------------ENTREGADORA-------------------------------------------------
      DM_Tem_Entregador = "N";

      // System.out.println (" pesso ent " + OID_Pessoa_Entregadora);

      if (OID_Pessoa_Entregadora != null &&
          !OID_Pessoa_Entregadora.equals ("null") &&
          !OID_Pessoa_Entregadora.equals ("") &&
          !OID_Pessoa_Entregadora.equals (null)) {
        DM_Tem_Entregador = "S";
        calcula_frete_Entrega (ed , VL_Total_Frete_Calculado);

        // System.out.println (" entregadora " + OID_Pessoa_Entregadora);
        conED.setOID_Pessoa_Entregador (OID_Pessoa_Entregadora);
      }
      // --------------------PREVISAO DE ENTREGA-----------------------------------------
      if (ed.getDT_Previsao_Entrega () != null &&
          !ed.getDT_Previsao_Entrega ().equals ("") &&
          !ed.getDT_Previsao_Entrega ().equals ("null")) {
        DT_Previsao_Entrega = ed.getDT_Previsao_Entrega ();
      }
      else {
        if (NR_Prazo_Entrega <= 0) {
          NR_Prazo_Entrega = 1;
        }
        Calendar cal = Data.stringToCalendar (ed.getDT_Emissao () ,
                                              "dd/MM/yyyy");
        int diasAtuais = cal.get (Calendar.DAY_OF_MONTH);
        cal.set (Calendar.DAY_OF_MONTH , diasAtuais + NR_Prazo_Entrega);
        DT_Previsao_Entrega = (dateFormatter.calendarToString (cal , SHORT_DATE));
      }
      // System.out.println (" prev entrega" + DT_Previsao_Entrega);

      // --------------------AT. CTO-----------------------------------------------------

      // System.out.println (" limpar mov tipo cto" + DM_Tipo_Conhecimento);

      if (!DM_Tipo_Conhecimento.equals ("8")) { // nao é icms complementar
        sql = " UPDATE Conhecimentos SET ";
        sql += " VL_Custo_Entrega = " + VL_Custo_Entrega;
        sql += " ,DT_Previsao_Entrega = '" + DT_Previsao_Entrega + "'";
        sql += " ,OID_Pessoa_Entregadora = '" + ed.getOID_Pessoa_Entregador () +
            "'";
      }
      sql += " WHERE OID_Conhecimento = '" + OID_Conhecimento + "'";

      // System.out.println (" update cto > " + sql);

      i = executasql.executarUpdate (sql);

      // System.out.println (" update cto ok " + VL_Movimento_Frete);
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () ,
                          "calcula_frete_Entregadora(Calcula_FreteED ed)");
    }
    return conED;
  }

  public void gera_movimento (String Tipo_Movimento , double VL_Movimento ,
                              String OID_Conhecimento) throws Excecoes {

    String sql = null;
    long OID_Tipo_Movimento = 0;
    double VL_Credito = 0;
    double VL_Debito = 0;

    String SHORT_DATE = "dd/MM/yyyy";
    String SHORT_TIME = "HH:mm";
    //instancia objeto de formatacao de data
    //instancia objeto de data
    Calendar calendar = Calendar.getInstance ();
    SimpleDateFormat formatter_date = new SimpleDateFormat (SHORT_DATE);
    SimpleDateFormat formatter_time = new SimpleDateFormat (SHORT_TIME);
    java.util.Date currentTime_1 = calendar.getTime ();
    this.DT_Movimento = formatter_date.format (currentTime_1);
    this.HR_Movimento = formatter_time.format (currentTime_1);
    String DM_Tipo_Movimento = "";
    String DM_Totaliza = "";
    String DM_Base_ICMS = "";
    String DM_Base_PIS_COFINS = "";
    try {
      if (VL_Movimento > 0) {
        sql = " SELECT * FROM Tipos_Movimentos ";
        sql += " WHERE CD_Tipo_Movimento = '" + Tipo_Movimento + "'";

        ResultSet rs = null;
        rs = this.executasql.executarConsulta (sql);

        while (rs.next ()) {
          OID_Tipo_Movimento = rs.getLong ("OID_Tipo_Movimento");
          DM_Tipo_Movimento = rs.getString ("DM_Tipo_Movimento");
          DM_Totaliza = rs.getString ("DM_Totaliza");
          DM_Base_ICMS = rs.getString ("DM_Base_ICMS");
          DM_Base_PIS_COFINS = rs.getString ("DM_Base_PIS_COFINS");
        }

        if (DM_Tipo_Movimento.equals ("D")) {

          VL_Total_Custo = VL_Total_Custo + VL_Movimento;
          if (DM_Totaliza.equals ("K")) {
            VL_Custo_Coleta = VL_Custo_Coleta + VL_Movimento;
          }
          if (DM_Totaliza.equals ("T")) {
            VL_Custo_Transferencia = VL_Custo_Transferencia + VL_Movimento;
          }
          if (DM_Totaliza.equals ("M")) {
            VL_Custo_Entrega = VL_Custo_Entrega + VL_Movimento;
          }
          if (DM_Totaliza.equals ("U")) {
            // System.out.println ("Custo seguro " + VL_Movimento);
            VL_Custo_Imposto = VL_Custo_Imposto + VL_Movimento;
          }
          if (DM_Totaliza.equals ("S")) {
            VL_Custo_Seguro = VL_Custo_Seguro + VL_Movimento;
          }

          if (VL_Total_Custo >
              (VL_Custo_Coleta + VL_Custo_Transferencia + VL_Custo_Entrega +
               VL_Custo_Imposto + VL_Custo_Seguro)) {
            VL_Custo_Outros = VL_Total_Custo -
                (VL_Custo_Coleta + VL_Custo_Transferencia + VL_Custo_Entrega +
                 VL_Custo_Imposto + VL_Custo_Seguro);
          }
        }

        if (DM_Base_PIS_COFINS != null &&
            DM_PIS_COFINS != null &&
            parametro_FixoED.getDM_Calcula_PIS_COFINS ().equals ("F") &&
            DM_PIS_COFINS.equals ("F") &&
            DM_Base_PIS_COFINS.equals ("S")) {
          VL_Movimento = VL_Movimento * (1 + (PE_PIS_COFINS / 100));
        }

        Movimento_ConhecimentoRN Movimento_Conhecimentorn = new
            Movimento_ConhecimentoRN ();
        Movimento_ConhecimentoED edMovimento_Conhecimento = new
            Movimento_ConhecimentoED ();

        edMovimento_Conhecimento.setOID_Tipo_Movimento (OID_Tipo_Movimento);

        edMovimento_Conhecimento.setVL_Movimento (Valor.round (VL_Movimento , 2));

        edMovimento_Conhecimento.setDT_Movimento_Conhecimento (this.
            DT_Movimento);
        edMovimento_Conhecimento.setHR_Movimento_Conhecimento (this.
            HR_Movimento);
        edMovimento_Conhecimento.setOID_Conhecimento (OID_Conhecimento);

        edMovimento_Conhecimento.setDM_Tipo_Movimento (DM_Tipo_Movimento);
        edMovimento_Conhecimento.setDM_Lancado_Gerado ("G");
        edMovimento_Conhecimento.setOID_Tabela_Frete (OID_Tabela_Frete);

        edMovimento_Conhecimento.setNM_Pessoa_Entrega ("");

        Movimento_ConhecimentoBD = new Movimento_ConhecimentoBD (this.
            executasql);

        Movimento_ConhecimentoBD.inclui (edMovimento_Conhecimento);

      }
    }

    catch (SQLException exc) {
      throw new Excecoes (exc.getMessage () , exc , getClass ().getName () ,
                          "gera movimento");
    }
  }

  public void lancaMovimento (String Tipo_Movimento , double VL_Movimento ,
                              String OID_Conhecimento) throws Excecoes {

    String sql = null;
    long OID_Tipo_Movimento = 0;
    double VL_Credito = 0;
    double VL_Debito = 0;

    String SHORT_DATE = "dd/MM/yyyy";
    String SHORT_TIME = "HH:mm";
    //instancia objeto de formatacao de data
    //instancia objeto de data
    Calendar calendar = Calendar.getInstance ();
    SimpleDateFormat formatter_date = new SimpleDateFormat (SHORT_DATE);
    SimpleDateFormat formatter_time = new SimpleDateFormat (SHORT_TIME);
    java.util.Date currentTime_1 = calendar.getTime ();
    this.DT_Movimento = formatter_date.format (currentTime_1);
    this.HR_Movimento = formatter_time.format (currentTime_1);
    String DM_Tipo_Movimento = "";
    String DM_Totaliza = "";
    String DM_Base_ICMS = "";
    String DM_Base_PIS_COFINS = "";
    try {
      if (VL_Movimento > 0) {
        sql = " SELECT * FROM Tipos_Movimentos ";
        sql += " WHERE CD_Tipo_Movimento = '" + Tipo_Movimento + "'";

        ResultSet rs = null;
        rs = this.executasql.executarConsulta (sql);

        while (rs.next ()) {
          OID_Tipo_Movimento = rs.getLong ("OID_Tipo_Movimento");
          DM_Tipo_Movimento = rs.getString ("DM_Tipo_Movimento");
          DM_Totaliza = rs.getString ("DM_Totaliza");
          DM_Base_ICMS = rs.getString ("DM_Base_ICMS");
          DM_Base_PIS_COFINS = rs.getString ("DM_Base_PIS_COFINS");
        }

        if (DM_Tipo_Movimento.equals ("D")) {

          VL_Total_Custo = VL_Total_Custo + VL_Movimento;
          if (DM_Totaliza.equals ("K")) {
            VL_Custo_Coleta = VL_Custo_Coleta + VL_Movimento;
          }
          if (DM_Totaliza.equals ("T")) {
            VL_Custo_Transferencia = VL_Custo_Transferencia + VL_Movimento;
          }
          if (DM_Totaliza.equals ("M")) {
            VL_Custo_Entrega = VL_Custo_Entrega + VL_Movimento;
          }
          if (DM_Totaliza.equals ("U")) {
            // System.out.println ("Custo seguro " + VL_Movimento);
            VL_Custo_Imposto = VL_Custo_Imposto + VL_Movimento;
          }
          if (DM_Totaliza.equals ("S")) {
            VL_Custo_Seguro = VL_Custo_Seguro + VL_Movimento;
          }

          if (VL_Total_Custo >
              (VL_Custo_Coleta + VL_Custo_Transferencia + VL_Custo_Entrega +
               VL_Custo_Imposto + VL_Custo_Seguro)) {
            VL_Custo_Outros = VL_Total_Custo -
                (VL_Custo_Coleta + VL_Custo_Transferencia + VL_Custo_Entrega +
                 VL_Custo_Imposto + VL_Custo_Seguro);
          }
        }

        if (DM_Base_PIS_COFINS != null &&
            DM_PIS_COFINS != null &&
            parametro_FixoED.getDM_Calcula_PIS_COFINS ().equals ("F") &&
            DM_PIS_COFINS.equals ("F") &&
            DM_Base_PIS_COFINS.equals ("S")) {
          VL_Movimento = VL_Movimento * (1 + (PE_PIS_COFINS / 100));
        }

        Movimento_ConhecimentoRN Movimento_Conhecimentorn = new
            Movimento_ConhecimentoRN ();
        Movimento_ConhecimentoED edMovimento_Conhecimento = new
            Movimento_ConhecimentoED ();

        edMovimento_Conhecimento.setOID_Tipo_Movimento (OID_Tipo_Movimento);

        edMovimento_Conhecimento.setVL_Movimento (new Double (VL_Movimento).
                                                  doubleValue ());

        edMovimento_Conhecimento.setDT_Movimento_Conhecimento (this.
            DT_Movimento);
        edMovimento_Conhecimento.setHR_Movimento_Conhecimento (this.
            HR_Movimento);
        edMovimento_Conhecimento.setOID_Conhecimento (OID_Conhecimento);

        edMovimento_Conhecimento.setDM_Tipo_Movimento (DM_Tipo_Movimento);
        edMovimento_Conhecimento.setDM_Lancado_Gerado ("L");
        edMovimento_Conhecimento.setOID_Tabela_Frete (OID_Tabela_Frete);

        edMovimento_Conhecimento.setNM_Pessoa_Entrega ("");

        Movimento_ConhecimentoBD = new Movimento_ConhecimentoBD (this.
            executasql);

        Movimento_ConhecimentoBD.inclui (edMovimento_Conhecimento);

      }
    }

    catch (Exception e) {
      Excecoes excecoes = new Excecoes (e.getMessage () , e ,
                                        this.getClass ().getName () , "lanca_movimento(String Tipo_Movimento, double VL_Movimento, String OID_Conhecimento)");
    }
  }

  public void excluiMovimentos (String Tipo_Movimento , String OID_Conhecimento) throws
      Excecoes {
    String sql =
        " select oid_Tipo_Movimento from tipos_movimentos where CD_Tipo_Movimento = '" +
        Tipo_Movimento + "'";
    ResultSet res = executasql.executarConsulta (sql);
    try {
      if (res.next ()) {
        sql =
            " delete from Movimentos_Conhecimentos where oid_Conhecimento = '" +
            OID_Conhecimento + "' and oid_Tipo_Movimento = " +
            res.getLong ("oid_Tipo_Movimento");
        executasql.executarUpdate (sql);
      }
    }
    catch (SQLException e) {
      new Excecoes (e.getMessage () , e , this.getClass ().getName () ,
          "excluiMovimentos(String Tipo_Movimento, String OID_Conhecimento)");
    }
  }

  public void totaliza_movimento (String OID_Conhecimento) throws Excecoes {

    try {

      VL_Frete_Peso = 0;
      VL_AD_Valorem = 0;
      VL_SEC_CAT = 0;
      VL_Pedagio = 0;
      VL_Outros1 = 0;
      VL_Outros2 = 0;
      VL_Outros3 = 0;
      VL_Outros4 = 0;
      VL_Outros5 = 0;
      VL_Outros6 = 0;
      VL_Outros7 = 0;
      VL_Outros8 = 0;
      VL_Outros9 = 0;
      VL_Outros10 = 0;
      VL_Despacho = 0;
      VL_PIS = 0;
      VL_COFINS = 0;

      sql = " SELECT * FROM Tipos_Movimentos, Movimentos_Conhecimentos " +
          " WHERE Movimentos_Conhecimentos.oid_Tipo_Movimento = Tipos_Movimentos.oid_Tipo_Movimento" +
          " AND Movimentos_Conhecimentos.OID_Conhecimento = '" +
          OID_Conhecimento + "'";

      rs = this.executasql.executarConsulta (sql);

      while (rs.next ()) {
        // System.out.println (" totaliza_movimento ");

        if (rs.getString ("DM_Base_ICMS").equals ("S")) {
          VL_Base_ICMS = VL_Base_ICMS + rs.getDouble ("VL_Movimento");
        }
        if (rs.getString ("DM_Totaliza").equals ("1")) {
          VL_Frete_Peso = VL_Frete_Peso + rs.getDouble ("VL_Movimento");
        }
        if (rs.getString ("DM_Totaliza").equals ("2")) {
          VL_AD_Valorem = VL_AD_Valorem + rs.getDouble ("VL_Movimento");
        }
        if (rs.getString ("DM_Totaliza").equals ("3")) {
          VL_SEC_CAT = VL_SEC_CAT + rs.getDouble ("VL_Movimento");
        }

        if (rs.getString ("DM_Totaliza").equals ("4")) {
          VL_Pedagio = VL_Pedagio + rs.getDouble ("VL_Movimento");

        }
        if (rs.getString ("DM_Totaliza").equals ("5")) {
          VL_Despacho = VL_Despacho + rs.getDouble ("VL_Movimento");
        }
        if (rs.getString ("DM_Totaliza").equals ("A")) {
          VL_Outros1 = VL_Outros1 + rs.getDouble ("VL_Movimento");
        }
        if (rs.getString ("DM_Totaliza").equals ("B")) {
          VL_Outros2 = VL_Outros2 + rs.getDouble ("VL_Movimento");
        }
        if (rs.getString ("DM_Totaliza").equals ("C")) {
          VL_Outros3 = VL_Outros3 + rs.getDouble ("VL_Movimento");
        }
        if (rs.getString ("DM_Totaliza").equals ("D")) {
          VL_Outros4 = VL_Outros4 + rs.getDouble ("VL_Movimento");
        }
        if (rs.getString ("DM_Totaliza").equals ("E")) {
          VL_Outros5 = VL_Outros5 + rs.getDouble ("VL_Movimento");
        }
        if (rs.getString ("DM_Totaliza").equals ("F")) {
          VL_Outros6 = VL_Outros6 + rs.getDouble ("VL_Movimento");
        }
        if (rs.getString ("DM_Totaliza").equals ("G")) {
          VL_Outros7 = VL_Outros7 + rs.getDouble ("VL_Movimento");
        }
        if (rs.getString ("DM_Totaliza").equals ("H")) {
          VL_Outros8 = VL_Outros8 + rs.getDouble ("VL_Movimento");
        }
        if (rs.getString ("DM_Totaliza").equals ("I")) {
          VL_Outros9 = VL_Outros9 + rs.getDouble ("VL_Movimento");
        }
        if (rs.getString ("DM_Totaliza").equals ("J")) {
          VL_Outros10 = VL_Outros10 + rs.getDouble ("VL_Movimento");
        }
        if (rs.getString ("DM_Totaliza").equals ("V")) {
          VL_PIS = VL_PIS + rs.getDouble ("VL_Movimento");
        }
        if (rs.getString ("DM_Totaliza").equals ("X")) {
          VL_COFINS = VL_COFINS + rs.getDouble ("VL_Movimento");
        }
      }
      // System.out.println (" fim 1 ");

      VL_Total_Frete = (VL_Frete_Peso + VL_AD_Valorem + VL_SEC_CAT + VL_Pedagio +
                        VL_Despacho + VL_Outros1 + VL_Outros2);

    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () ,
                          "totaliza_movimento(String OID_Conhecimento)");
    }
  }

  public Calcula_FreteED calcula_frete_Tabela_Rodov04 (Calcula_FreteED ed) throws
      Excecoes {

    // Roflan

    Calcula_FreteED conED = new Calcula_FreteED ();

    Calcula_FreteED calcula_frete_FracionadoED = new Calcula_FreteED ();

    // System.out.println (" roflan ");

    try {

      localizado = "N";
      DM_Tem_Peso = "S";
      DM_Tem_Modal = "S";
      DM_Tem_Validade = "N";
      DM_Tem_Vigencia = "S";
      DM_Tem_Empresa = "N";
      DM_Tem_Cidade = "S";
      DM_Tem_Regiao_Estado = "S";
      DM_Tem_Estado = "S";
      DM_Tem_Subregiao = "N";
      DM_Tem_Regiao_Pais = "N";
      DM_Tem_Pais = "S";

      DM_Tabela_Reversa = "N";
      DM_Tem_Tabela_Unidade = "N";

      // System.out.println (" DM_Tipo_Conhecimento " + DM_Tipo_Conhecimento);

      if (DM_Tipo_Conhecimento != null && DM_Tipo_Conhecimento.equals ("2") &&
          parametro_FixoED.getDM_Frete_Cortesia ().equals ("PARAMETRO")) {
        // --------------------CORTESIA TIPO(2)--------------------------------------------
        Tipo_Movimento = "FP";
        // System.out.println (" CORTESIA 1 ");
        VL_Movimento_Frete = parametro_FixoED.getVL_Frete_Cortesia ();
        gera_movimento (Tipo_Movimento , VL_Movimento_Frete , OID_Conhecimento);
      }
      else {
        DM_Percurso_Busca = "Origem_Destino";
        if (DM_Tipo_Conhecimento != null && DM_Tipo_Conhecimento.equals ("3")) {
          DM_Percurso_Busca = "Destino_Origem";
        }
        // System.out.println (" calc 0 " + DM_Percurso_Busca);

        calcula_frete_FracionadoED = localizaTabela (ed);

        // System.out.println (" calc 1 ");

        if (calcula_frete_FracionadoED.getDM_Localizado ().equals ("N") &&
            (ed.getOID_Pessoa_Destinatario ().equals (ed.getOID_Pessoa_Pagador ()))) {

          sql = " SELECT DM_FOB_Dirigido FROM Clientes ";
          sql += " WHERE Clientes.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";

          // System.out.println (" calc 2 ");

          rs = null;
          rs = this.executasql.executarConsulta (sql);

          while (rs.next ()) {
            DM_FOB_Dirigido = rs.getString ("DM_FOB_Dirigido");
          }

          if (DM_FOB_Dirigido != null && DM_FOB_Dirigido.equals ("S")) {
            ed.setOID_Pessoa_Pagador_Tabela (ed.getOID_Pessoa ());
            calcula_frete_FracionadoED = localizaTabela (ed);
          }
        }
        // System.out.println (" calc 3 ");

        if (calcula_frete_FracionadoED.getDM_Localizado ().equals ("N") &&
            (ed.getOID_Pessoa ().equals (ed.getOID_Pessoa_Pagador ()))) {

          // System.out.println (" calc 4 ");

          sql = " SELECT DM_FOB_Dirigido FROM Clientes ";
          sql += " WHERE Clientes.OID_Pessoa = '" +
              ed.getOID_Pessoa_Destinatario () + "'";

          rs = null;
          rs = this.executasql.executarConsulta (sql);

          // System.out.println (" calc 5 ");

          while (rs.next ()) {
            DM_FOB_Dirigido = rs.getString ("DM_FOB_Dirigido");
          }

          if (DM_FOB_Dirigido != null && DM_FOB_Dirigido.equals ("S")) {
            ed.setOID_Pessoa_Pagador_Tabela (ed.getOID_Pessoa_Destinatario ());
            // System.out.println (" calc 52 ");

            calcula_frete_FracionadoED = localizaTabela (ed);

          }
        }

        if (calcula_frete_FracionadoED.getDM_Localizado ().equals ("N") &&
            DM_Percurso_Busca.equals ("Origem_Destino") &&
            DM_Tabela_Reversa.equals ("S")) {
          DM_Percurso_Busca = "Destino_Origem";
          calcula_frete_FracionadoED = localizaTabela (ed);
          // System.out.println (" calc Destino_Origem 2 ");
        }

        if (calcula_frete_FracionadoED.getDM_Localizado ().equals ("N") &&
            DM_Tem_Tabela_Unidade.equals ("S")) {
          sql = " SELECT oid_Pessoa from Unidades ";
          sql += " WHERE Unidades.oid_unidade =" + ed.getOID_Unidade ();

          rs = null;
          rs = this.executasql.executarConsulta (sql);

          while (rs.next ()) {
            //ed.setOID_Produto(3); produto tabela geral
            ed.setOID_Pessoa_Pagador_Tabela (String.valueOf (rs.getString (
                "oid_Pessoa")));
            calcula_frete_FracionadoED = localizaTabela (ed);
          }
        }
        // System.out.println (" calc 6 ");

        if (calcula_frete_FracionadoED.getDM_Localizado () != null &&

            calcula_frete_FracionadoED.getDM_Localizado ().equals ("S")) {
          OID_Conhecimento = ed.getOID_Conhecimento ();

          // System.out.println (" achou tabela ");

          // --------------------FRETE PESO--------------------------------------------------
          Tipo_Movimento = "FP";
          VL_Movimento_Frete = 0;
          if (DM_Tipo_Conhecimento.equals ("9")) {
            sql =
                " SELECT VL_Total_Frete, NR_Peso, VL_Mercadoria, QT_Volumes FROM Coletas ";
            sql += " WHERE Coletas.oid_Coleta = " + oid_Coleta;
            //                         // System.out.println(" coleta para frete peso ->>>" + sql);

            rs = null;
            rs = this.executasql.executarConsulta (sql);
            while (rs.next ()) {
              VL_Movimento_Frete = (rs.getDouble ("VL_Total_Frete") /
                                    rs.getDouble ("NR_Peso")) * NR_Peso_NF;
              // VL_Movimento_Frete=(rs.getDouble("VL_Total_Frete")/rs.getDouble("QT_Volumes"))*NR_Volumes_NF;
            }
          }
          else {
            VL_Movimento_Frete = calcula_frete_FracionadoED.
                getVL_Frete_Fracionado ();
            if (calcula_frete_FracionadoED.getDM_Tipo_Peso ().equals ("K")) {
              VL_Movimento_Frete = (calcula_frete_FracionadoED.
                                    getVL_Frete_Fracionado () *
                                    (NR_Peso_Cubado_NF -
                                     calcula_frete_FracionadoED.
                                     getNR_Peso_Minimo ()));
            }
            else if (calcula_frete_FracionadoED.getDM_Tipo_Peso ().equals ("T")) {
              VL_Movimento_Frete = (calcula_frete_FracionadoED.
                                    getVL_Frete_Fracionado () *
                                    (NR_Peso_Cubado_NF -
                                     calcula_frete_FracionadoED.
                                     getNR_Peso_Minimo ()) / 1000);
            }
            else if (calcula_frete_FracionadoED.getDM_Tipo_Peso ().equals ("C")) {
              VL_Movimento_Frete = (calcula_frete_FracionadoED.
                                    getVL_Frete_Fracionado () * NR_Peso_Cub);
            }

            // System.out.println (" calc fp 2 " + DM_Tipo_Conhecimento);

            if (VL_Movimento_Frete < calcula_frete_FracionadoED.
                getVL_Frete_Minimo ()) {
              VL_Movimento_Frete = calcula_frete_FracionadoED.
                  getVL_Frete_Minimo ();
            }
            if (DM_Tipo_Conhecimento.equals ("3") &&
                calcula_frete_FracionadoED.getPE_Devolucao () > 0) {
              VL_Movimento_Frete = VL_Movimento_Frete *
                  ( (100 - calcula_frete_FracionadoED.getPE_Devolucao ()) / 100);
            }
            // System.out.println (" calc fp 3 ");

            if (DM_Tipo_Conhecimento.equals ("R") &&
                calcula_frete_FracionadoED.getPE_Refaturamento () > 0) {
              VL_Movimento_Frete = VL_Movimento_Frete *
                  ( (100 - calcula_frete_FracionadoED.getPE_Refaturamento ()) /
                   100);
            }
            // System.out.println (" calc fp 3 b ");

            if (DM_Tipo_Conhecimento.equals ("4") &&
                calcula_frete_FracionadoED.getPE_Reentrega () > 0) {
              VL_Movimento_Frete = VL_Movimento_Frete *
                  ( (100 - calcula_frete_FracionadoED.getPE_Reentrega ()) / 100);
            }

            if (NR_Cubagem_Total > 0 && NR_Cubagem_Total > NR_Cubagem) {
              VL_Movimento_Frete = ( (VL_Movimento_Frete / NR_Cubagem_Total) *
                                    NR_Cubagem);
            }
          }
          gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                          OID_Conhecimento);

          // --------------------CARGA EXPRESSA--------------------------------------------------
          Tipo_Movimento = "CE";

          if (ed.getPE_Carga_Expressa () > 0 &&
              calcula_frete_FracionadoED.getVL_Frete_Fracionado () > 0) {
            VL_Movimento_Frete = calcula_frete_FracionadoED.
                getVL_Frete_Fracionado () * (ed.getPE_Carga_Expressa () / 100);
            gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                            OID_Conhecimento);
          }

          // --------------------AD VALOREM--------------------------------------------------
          // System.out.println (" calc av ");
          if (calcula_frete_FracionadoED.getVL_Minimo_Nota_Fiscal () <=
              VL_Nota_Fiscal) {
            Tipo_Movimento = "AV";
            VL_Movimento_Frete = (VL_Nota_Fiscal *
                                  calcula_frete_FracionadoED.getPE_AD_Valorem () /
                                  100);

            if (VL_Movimento_Frete < calcula_frete_FracionadoED.
                getVL_AD_Valorem_Minimo ()) {
              VL_Movimento_Frete = calcula_frete_FracionadoED.
                  getVL_AD_Valorem_Minimo ();
            }
            if (DM_Tipo_Conhecimento.equals ("3") &&
                calcula_frete_FracionadoED.getPE_Devolucao () > 0) {
              VL_Movimento_Frete = VL_Movimento_Frete *
                  ( (100 - calcula_frete_FracionadoED.getPE_Devolucao ()) / 100);
            }
            if (DM_Tipo_Conhecimento.equals ("R") &&
                calcula_frete_FracionadoED.getPE_Refaturamento () > 0) {
              VL_Movimento_Frete = VL_Movimento_Frete *
                  ( (100 - calcula_frete_FracionadoED.getPE_Refaturamento ()) /
                   100);
            }

            if (DM_Tipo_Conhecimento.equals ("4") &&
                calcula_frete_FracionadoED.getPE_Reentrega () > 0) {
              VL_Movimento_Frete = VL_Movimento_Frete *
                  ( (100 - calcula_frete_FracionadoED.getPE_Reentrega ()) / 100);
            }

            gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                            OID_Conhecimento);
          }
          // --------------------ADEME/GRIS--------------------------------------------------
          // System.out.println (" calc gr ");

          Tipo_Movimento = "ADM";
          VL_Movimento_Frete = (VL_Nota_Fiscal *
                                calcula_frete_FracionadoED.getPE_Ademe () / 100);
          if (VL_Movimento_Frete < calcula_frete_FracionadoED.
              getVL_Ademe_Minimo ()) {
            VL_Movimento_Frete = calcula_frete_FracionadoED.getVL_Ademe_Minimo ();
          }
          if (DM_Tipo_Conhecimento.equals ("3") &&
              calcula_frete_FracionadoED.getPE_Devolucao () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcula_frete_FracionadoED.getPE_Devolucao ()) / 100);
          }
          if (DM_Tipo_Conhecimento.equals ("R") &&
              calcula_frete_FracionadoED.getPE_Refaturamento () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcula_frete_FracionadoED.getPE_Refaturamento ()) /
                 100);
          }
          if (DM_Tipo_Conhecimento.equals ("4") &&
              calcula_frete_FracionadoED.getPE_Reentrega () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcula_frete_FracionadoED.getPE_Reentrega ()) / 100);
          }

          gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                          OID_Conhecimento);

          // --------------------TAXAS-------------------------------------------------------
          // System.out.println (" calc tx ");

          Tipo_Movimento = "TX";
          VL_Movimento_Frete = calcula_frete_FracionadoED.getVL_Taxas ();
          if (DM_Tipo_Conhecimento.equals ("3") &&
              calcula_frete_FracionadoED.getPE_Devolucao () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcula_frete_FracionadoED.getPE_Devolucao ()) / 100);
          }
          if (DM_Tipo_Conhecimento.equals ("R") &&
              calcula_frete_FracionadoED.getPE_Refaturamento () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcula_frete_FracionadoED.getPE_Refaturamento ()) /
                 100);
          }
          if (DM_Tipo_Conhecimento.equals ("4") &&
              calcula_frete_FracionadoED.getPE_Reentrega () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcula_frete_FracionadoED.getPE_Reentrega ()) / 100);
          }

          gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                          OID_Conhecimento);

          // --------------------DESPACHO----------------------------------------------------
          // System.out.println (" calc dp ");

          Tipo_Movimento = "DP";
          VL_Movimento_Frete = calcula_frete_FracionadoED.getVL_Despacho ();
          if (DM_Tipo_Conhecimento.equals ("3") &&
              calcula_frete_FracionadoED.getPE_Devolucao () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcula_frete_FracionadoED.getPE_Devolucao ()) / 100);
          }
          if (DM_Tipo_Conhecimento.equals ("R") &&
              calcula_frete_FracionadoED.getPE_Refaturamento () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcula_frete_FracionadoED.getPE_Refaturamento ()) /
                 100);
          }
          if (DM_Tipo_Conhecimento.equals ("4") &&
              calcula_frete_FracionadoED.getPE_Reentrega () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcula_frete_FracionadoED.getPE_Reentrega ()) / 100);
          }

          gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                          OID_Conhecimento);

          // --------------------PEDAGIO-----------------------------------------------------
          // System.out.println (" calc ped ");

          Tipo_Movimento = "PED";
          VL_Movimento_Frete = calcula_frete_FracionadoED.getVL_Pedagio ();
          if (calcula_frete_FracionadoED.getDM_Tipo_Pedagio ().equals ("K")) {
            VL_Movimento_Frete = (calcula_frete_FracionadoED.getVL_Pedagio () *
                                  NR_Peso_Cubado_NF);
          }
          if (DM_Tipo_Conhecimento.equals ("3") &&
              calcula_frete_FracionadoED.getPE_Devolucao () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcula_frete_FracionadoED.getPE_Devolucao ()) / 100);
          }
          if (DM_Tipo_Conhecimento.equals ("R") &&
              calcula_frete_FracionadoED.getPE_Refaturamento () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcula_frete_FracionadoED.getPE_Refaturamento ()) /
                 100);
          }
          if (DM_Tipo_Conhecimento.equals ("4") &&
              calcula_frete_FracionadoED.getPE_Reentrega () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcula_frete_FracionadoED.getPE_Reentrega ()) / 100);
          }

          if (NR_Cubagem_Total > 0 && NR_Cubagem_Total > NR_Cubagem) {
            VL_Movimento_Frete = ( (VL_Movimento_Frete / NR_Cubagem_Total) *
                                  NR_Cubagem);
          }

          gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                          OID_Conhecimento);

        }
        NR_Peso_Cubado_NF = NR_Peso_Cub;
      }

    }
    catch (Excecoes e) {
      if (DM_Tipo_Erro != null && DM_Tipo_Erro.equals ("Nota")) {
        throw new Mensagens (
            "Erro ao incluir - Valor do Frete Maior que o Valor Máximo da Tabela.");
      }
      else {
        throw e;
      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () ,
                          "calcula_frete_Tabela_Roflan(Calcula_FreteED ed)");
    }
    return conED;
  }

  public Calcula_FreteED calcula_frete_Tabela_Rodov07 (Calcula_FreteED ed) throws
      Excecoes {

    Calcula_FreteED conED = new Calcula_FreteED ();

    Calcula_FreteED calcula_frete_FracionadoED = new Calcula_FreteED ();

    // System.out.println (" colorado ");

    try {

      localizado = "N";
      DM_Tem_Peso = "S";
      DM_Tem_Modal = "S";
      DM_Tem_Validade = "N";
      DM_Tem_Vigencia = "S";
      DM_Tem_Empresa = "N";
      DM_Tem_Cidade = "S";
      DM_Tem_Regiao_Estado = "S";
      DM_Tem_Estado = "S";
      DM_Tem_Subregiao = "N";
      DM_Tem_Regiao_Pais = "N";
      DM_Tem_Pais = "S";

      DM_Tabela_Reversa = "N";
      DM_Tem_Tabela_Unidade = "N";

      // System.out.println (" DM_Tipo_Conhecimento " + DM_Tipo_Conhecimento);

      if (DM_Tipo_Conhecimento != null && DM_Tipo_Conhecimento.equals ("2") &&
          parametro_FixoED.getDM_Frete_Cortesia ().equals ("PARAMETRO")) {
        // --------------------CORTESIA TIPO(2)--------------------------------------------
        Tipo_Movimento = "FP";
        // System.out.println (" CORTESIA 1 ");
        VL_Movimento_Frete = parametro_FixoED.getVL_Frete_Cortesia ();
        gera_movimento (Tipo_Movimento , VL_Movimento_Frete , OID_Conhecimento);
      }
      else {
        DM_Percurso_Busca = "Origem_Destino";
        if (DM_Tipo_Conhecimento != null && DM_Tipo_Conhecimento.equals ("3")) {
          DM_Percurso_Busca = "Destino_Origem";
        }
        // System.out.println (" calc 0 " + DM_Percurso_Busca);

        calcula_frete_FracionadoED = localizaTabela (ed);

        // System.out.println (" calc 1 ");

        if (calcula_frete_FracionadoED.getDM_Localizado ().equals ("N") &&
            (ed.getOID_Pessoa_Destinatario ().equals (ed.getOID_Pessoa_Pagador ()))) {

          sql = " SELECT DM_FOB_Dirigido FROM Clientes ";
          sql += " WHERE Clientes.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";

          // System.out.println (" calc 2 ");

          rs = null;
          rs = this.executasql.executarConsulta (sql);

          while (rs.next ()) {
            DM_FOB_Dirigido = rs.getString ("DM_FOB_Dirigido");
          }

          if (DM_FOB_Dirigido != null && DM_FOB_Dirigido.equals ("S")) {
            ed.setOID_Pessoa_Pagador_Tabela (ed.getOID_Pessoa ());
            calcula_frete_FracionadoED = localizaTabela (ed);
          }
        }
        // System.out.println (" calc 3 ");

        if (calcula_frete_FracionadoED.getDM_Localizado ().equals ("N") &&
            (ed.getOID_Pessoa ().equals (ed.getOID_Pessoa_Pagador ()))) {

          // System.out.println (" calc 4 ");

          sql = " SELECT DM_FOB_Dirigido FROM Clientes ";
          sql += " WHERE Clientes.OID_Pessoa = '" +
              ed.getOID_Pessoa_Destinatario () + "'";

          rs = null;
          rs = this.executasql.executarConsulta (sql);

          // System.out.println (" calc 5 ");

          while (rs.next ()) {
            DM_FOB_Dirigido = rs.getString ("DM_FOB_Dirigido");
          }

          if (DM_FOB_Dirigido != null && DM_FOB_Dirigido.equals ("S")) {
            ed.setOID_Pessoa_Pagador_Tabela (ed.getOID_Pessoa_Destinatario ());
            // System.out.println (" calc 52 ");

            calcula_frete_FracionadoED = localizaTabela (ed);

          }
        }

        if (calcula_frete_FracionadoED.getDM_Localizado ().equals ("N") &&
            DM_Percurso_Busca.equals ("Origem_Destino") &&
            DM_Tabela_Reversa.equals ("S")) {
          DM_Percurso_Busca = "Destino_Origem";
          calcula_frete_FracionadoED = localizaTabela (ed);
          // System.out.println (" calc Destino_Origem 2 ");
        }

        if (calcula_frete_FracionadoED.getDM_Localizado ().equals ("N") &&
            DM_Tem_Tabela_Unidade.equals ("S")) {
          sql = " SELECT oid_Pessoa from Unidades ";
          sql += " WHERE Unidades.oid_unidade =" + ed.getOID_Unidade ();

          rs = null;
          rs = this.executasql.executarConsulta (sql);

          while (rs.next ()) {
            //ed.setOID_Produto(3); produto tabela geral
            ed.setOID_Pessoa_Pagador_Tabela (String.valueOf (rs.getString (
                "oid_Pessoa")));
            calcula_frete_FracionadoED = localizaTabela (ed);
          }
        }
        // System.out.println (" calc 6 ");

        if (calcula_frete_FracionadoED.getDM_Localizado () != null &&

            calcula_frete_FracionadoED.getDM_Localizado ().equals ("S")) {
          OID_Conhecimento = ed.getOID_Conhecimento ();

          // System.out.println (" achou tabela ");

          // --------------------FRETE PESO--------------------------------------------------
          Tipo_Movimento = "FP";
          VL_Movimento_Frete = 0;
          if (DM_Tipo_Conhecimento.equals ("9")) {
            sql =
                " SELECT VL_Total_Frete, NR_Peso, VL_Mercadoria, QT_Volumes FROM Coletas ";
            sql += " WHERE Coletas.oid_Coleta = " + oid_Coleta;
//                         // System.out.println(" coleta para frete peso ->>>" + sql);

            rs = null;
            rs = this.executasql.executarConsulta (sql);
            while (rs.next ()) {
              VL_Movimento_Frete = (rs.getDouble ("VL_Total_Frete") /
                                    rs.getDouble ("NR_Peso")) * NR_Peso_NF;
              // VL_Movimento_Frete=(rs.getDouble("VL_Total_Frete")/rs.getDouble("QT_Volumes"))*NR_Volumes_NF;
            }
          }
          else {
            VL_Movimento_Frete = calcula_frete_FracionadoED.
                getVL_Frete_Fracionado ();
            if (calcula_frete_FracionadoED.getDM_Tipo_Peso ().equals ("K")) {
              VL_Movimento_Frete = (calcula_frete_FracionadoED.
                                    getVL_Frete_Fracionado () *
                                    (NR_Peso_Cubado_NF -
                                     calcula_frete_FracionadoED.
                                     getNR_Peso_Minimo ()));
            }
            else if (calcula_frete_FracionadoED.getDM_Tipo_Peso ().equals ("T")) {
              VL_Movimento_Frete = (calcula_frete_FracionadoED.
                                    getVL_Frete_Fracionado () *
                                    (NR_Peso_Cubado_NF -
                                     calcula_frete_FracionadoED.
                                     getNR_Peso_Minimo ()) / 1000);
            }
            else if (calcula_frete_FracionadoED.getDM_Tipo_Peso ().equals ("C")) {
              VL_Movimento_Frete = (calcula_frete_FracionadoED.
                                    getVL_Frete_Fracionado () * NR_Peso_Cub);
            }

            if (VL_Movimento_Frete < calcula_frete_FracionadoED.
                getVL_Frete_Minimo ()) {
              VL_Movimento_Frete = calcula_frete_FracionadoED.
                  getVL_Frete_Minimo ();
            }
            if (DM_Tipo_Conhecimento.equals ("3") &&
                calcula_frete_FracionadoED.getPE_Devolucao () > 0) {
              VL_Movimento_Frete = VL_Movimento_Frete *
                  ( (100 - calcula_frete_FracionadoED.getPE_Devolucao ()) / 100);
            }
            // System.out.println (" calc fp 3 ");

            if (DM_Tipo_Conhecimento.equals ("R") &&
                calcula_frete_FracionadoED.getPE_Refaturamento () > 0) {
              VL_Movimento_Frete = VL_Movimento_Frete *
                  ( (100 - calcula_frete_FracionadoED.getPE_Refaturamento ()) /
                   100);
            }
            // System.out.println (" calc fp 3 b ");

            if (DM_Tipo_Conhecimento.equals ("4") &&
                calcula_frete_FracionadoED.getPE_Reentrega () > 0) {
              VL_Movimento_Frete = VL_Movimento_Frete *
                  ( (100 - calcula_frete_FracionadoED.getPE_Reentrega ()) / 100);
            }

          }
          gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                          OID_Conhecimento);

          // --------------------CARGA EXPRESSA--------------------------------------------------
          Tipo_Movimento = "CE";
          if (ed.getPE_Carga_Expressa () > 0 &&
              calcula_frete_FracionadoED.getVL_Frete_Fracionado () > 0) {
            VL_Movimento_Frete = calcula_frete_FracionadoED.
                getVL_Frete_Fracionado () * (ed.getPE_Carga_Expressa () / 100);
            gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                            OID_Conhecimento);
          }

          // --------------------AD VALOREM--------------------------------------------------
          // System.out.println (" calc av ");
          if (calcula_frete_FracionadoED.getVL_Minimo_Nota_Fiscal () <=
              VL_Nota_Fiscal) {
            Tipo_Movimento = "AV";
            VL_Movimento_Frete = (VL_Nota_Fiscal *
                                  calcula_frete_FracionadoED.getPE_AD_Valorem () /
                                  100);

            if (VL_Movimento_Frete < calcula_frete_FracionadoED.
                getVL_AD_Valorem_Minimo ()) {
              VL_Movimento_Frete = calcula_frete_FracionadoED.
                  getVL_AD_Valorem_Minimo ();
            }
            if (DM_Tipo_Conhecimento.equals ("3") &&
                calcula_frete_FracionadoED.getPE_Devolucao () > 0) {
              VL_Movimento_Frete = VL_Movimento_Frete *
                  ( (100 - calcula_frete_FracionadoED.getPE_Devolucao ()) / 100);
            }
            if (DM_Tipo_Conhecimento.equals ("R") &&
                calcula_frete_FracionadoED.getPE_Refaturamento () > 0) {
              VL_Movimento_Frete = VL_Movimento_Frete *
                  ( (100 - calcula_frete_FracionadoED.getPE_Refaturamento ()) /
                   100);
            }

            if (DM_Tipo_Conhecimento.equals ("4") &&
                calcula_frete_FracionadoED.getPE_Reentrega () > 0) {
              VL_Movimento_Frete = VL_Movimento_Frete *
                  ( (100 - calcula_frete_FracionadoED.getPE_Reentrega ()) / 100);
            }

            gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                            OID_Conhecimento);
          }
          // --------------------ADEME/GRIS--------------------------------------------------
          // System.out.println (" calc gr ");

          Tipo_Movimento = "ADM";
          VL_Movimento_Frete = (VL_Nota_Fiscal *
                                calcula_frete_FracionadoED.getPE_Ademe () / 100);
          if (VL_Movimento_Frete < calcula_frete_FracionadoED.
              getVL_Ademe_Minimo ()) {
            VL_Movimento_Frete = calcula_frete_FracionadoED.getVL_Ademe_Minimo ();
          }
          if (DM_Tipo_Conhecimento.equals ("3") &&
              calcula_frete_FracionadoED.getPE_Devolucao () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcula_frete_FracionadoED.getPE_Devolucao ()) / 100);
          }
          if (DM_Tipo_Conhecimento.equals ("R") &&
              calcula_frete_FracionadoED.getPE_Refaturamento () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcula_frete_FracionadoED.getPE_Refaturamento ()) /
                 100);
          }
          if (DM_Tipo_Conhecimento.equals ("4") &&
              calcula_frete_FracionadoED.getPE_Reentrega () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcula_frete_FracionadoED.getPE_Reentrega ()) / 100);
          }

          gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                          OID_Conhecimento);

          // --------------------TAXAS-------------------------------------------------------
          // System.out.println (" calc tx ");

          Tipo_Movimento = "TX";
          VL_Movimento_Frete = calcula_frete_FracionadoED.getVL_Taxas ();
          if (DM_Tipo_Conhecimento.equals ("3") &&
              calcula_frete_FracionadoED.getPE_Devolucao () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcula_frete_FracionadoED.getPE_Devolucao ()) / 100);
          }
          if (DM_Tipo_Conhecimento.equals ("R") &&
              calcula_frete_FracionadoED.getPE_Refaturamento () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcula_frete_FracionadoED.getPE_Refaturamento ()) /
                 100);
          }
          if (DM_Tipo_Conhecimento.equals ("4") &&
              calcula_frete_FracionadoED.getPE_Reentrega () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcula_frete_FracionadoED.getPE_Reentrega ()) / 100);
          }

          gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                          OID_Conhecimento);

          // --------------------DESPACHO----------------------------------------------------
          // System.out.println (" calc dp ");

          Tipo_Movimento = "DP";
          VL_Movimento_Frete = calcula_frete_FracionadoED.getVL_Despacho ();
          if (DM_Tipo_Conhecimento.equals ("3") &&
              calcula_frete_FracionadoED.getPE_Devolucao () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcula_frete_FracionadoED.getPE_Devolucao ()) / 100);
          }
          if (DM_Tipo_Conhecimento.equals ("R") &&
              calcula_frete_FracionadoED.getPE_Refaturamento () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcula_frete_FracionadoED.getPE_Refaturamento ()) /
                 100);
          }
          if (DM_Tipo_Conhecimento.equals ("4") &&
              calcula_frete_FracionadoED.getPE_Reentrega () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcula_frete_FracionadoED.getPE_Reentrega ()) / 100);
          }

          gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                          OID_Conhecimento);

          // --------------------PEDAGIO-----------------------------------------------------
          // System.out.println (" calc ped ");

          Tipo_Movimento = "PED";
          VL_Movimento_Frete = calcula_frete_FracionadoED.getVL_Pedagio ();
          if (calcula_frete_FracionadoED.getDM_Tipo_Pedagio ().equals ("K")) {
            VL_Movimento_Frete = (calcula_frete_FracionadoED.getVL_Pedagio () *
                                  NR_Peso_Cubado_NF);
          }
          if (DM_Tipo_Conhecimento.equals ("3") &&
              calcula_frete_FracionadoED.getPE_Devolucao () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcula_frete_FracionadoED.getPE_Devolucao ()) / 100);
          }
          if (DM_Tipo_Conhecimento.equals ("R") &&
              calcula_frete_FracionadoED.getPE_Refaturamento () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcula_frete_FracionadoED.getPE_Refaturamento ()) /
                 100);
          }
          if (DM_Tipo_Conhecimento.equals ("4") &&
              calcula_frete_FracionadoED.getPE_Reentrega () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcula_frete_FracionadoED.getPE_Reentrega ()) / 100);
          }

          if (NR_Cubagem_Total > 0 && NR_Cubagem_Total > NR_Cubagem) {
            VL_Movimento_Frete = ( (VL_Movimento_Frete / NR_Cubagem_Total) *
                                  NR_Cubagem);
          }

          gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                          OID_Conhecimento);

        }
      }

    }

    catch (SQLException exc) {
      throw new Excecoes (exc.getMessage () , exc , getClass ().getName () ,
                          "calcula frete");
    }
    return conED;
  }

  public Calcula_FreteED calcula_frete_Tabela_Rodov06 (Calcula_FreteED ed) throws
      Excecoes {
    // BUZIN
    Calcula_FreteED conED = new Calcula_FreteED ();

    Calcula_FreteED calcula_frete_FracionadoED = new Calcula_FreteED ();

    // System.out.println (" buzin ");

    try {
      Tipo_Movimento = "FP";
      // System.out.println (" FRETE DA NOTA ");
      VL_Movimento_Frete = VL_Total_Frete_Nota_Fiscal;
      gera_movimento (Tipo_Movimento , VL_Movimento_Frete , OID_Conhecimento);
    }
    catch (Excecoes e) {
      if (DM_Tipo_Erro != null && DM_Tipo_Erro.equals ("Nota")) {
        throw new Mensagens (
            "Erro ao incluir - Valor do Frete Maior que o Valor Máximo da Tabela.");
      }
      else {
        throw e;
      }
    }
    return conED;
  }

  public Calcula_FreteED calcula_frete_Tabela_Rodov03 (Calcula_FreteED ed) throws
      Excecoes {

    //Mega

    Calcula_FreteED conED = new Calcula_FreteED ();

    Calcula_FreteED calcula_frete_FracionadoED = new Calcula_FreteED ();

    // System.out.println (" mega ");

    try {

      localizado = "N";
      DM_Tem_Peso = "S";
      DM_Tem_Modal = "S";
      DM_Tem_Validade = "N";
      DM_Tem_Vigencia = "S";
      DM_Tem_Empresa = "S";
      DM_Tem_Cidade = "S";
      DM_Tem_Regiao_Estado = "S";
      DM_Tem_Estado = "S";
      DM_Tem_Subregiao = "N";
      DM_Tem_Regiao_Pais = "N";
      DM_Tem_Pais = "S";

      // System.out.println (" DM_Tipo_Conhecimento " + DM_Tipo_Conhecimento);

      if (DM_Tipo_Conhecimento != null && DM_Tipo_Conhecimento.equals ("2") &&
          parametro_FixoED.getDM_Frete_Cortesia ().equals ("PARAMETRO")) {
        // --------------------CORTESIA TIPO(2)--------------------------------------------
        Tipo_Movimento = "FP";
        // System.out.println (" CORTESIA 1 ");
        VL_Movimento_Frete = parametro_FixoED.getVL_Frete_Cortesia ();
        gera_movimento (Tipo_Movimento , VL_Movimento_Frete , OID_Conhecimento);
      }
      else {
        DM_Percurso_Busca = "Origem_Destino";
        if (DM_Tipo_Conhecimento != null && DM_Tipo_Conhecimento.equals ("3")) {
          DM_Percurso_Busca = "Destino_Origem";
        }
        // System.out.println (" calc 0 " + DM_Percurso_Busca);

        calcula_frete_FracionadoED = localizaTabela (ed);

        // System.out.println (" calc 1 ");

        if (calcula_frete_FracionadoED.getDM_Localizado ().equals ("N") &&
            (ed.getOID_Pessoa_Destinatario ().equals (ed.getOID_Pessoa_Pagador ()))) {

          sql = " SELECT DM_FOB_Dirigido FROM Clientes ";
          sql += " WHERE Clientes.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";

          // System.out.println (" calc 2 ");

          rs = null;
          rs = this.executasql.executarConsulta (sql);

          while (rs.next ()) {
            DM_FOB_Dirigido = rs.getString ("DM_FOB_Dirigido");
          }

          if (DM_FOB_Dirigido != null && DM_FOB_Dirigido.equals ("S")) {
            ed.setOID_Pessoa_Pagador_Tabela (ed.getOID_Pessoa ());
            calcula_frete_FracionadoED = localizaTabela (ed);
          }
        }
        // System.out.println (" calc 3 ");

        if (calcula_frete_FracionadoED.getDM_Localizado ().equals ("N") &&
            (ed.getOID_Pessoa ().equals (ed.getOID_Pessoa_Pagador ()))) {

          // System.out.println (" calc 4 ");

          sql = " SELECT DM_FOB_Dirigido FROM Clientes ";
          sql += " WHERE Clientes.OID_Pessoa = '" +
              ed.getOID_Pessoa_Destinatario () + "'";

          rs = null;
          rs = this.executasql.executarConsulta (sql);

          // System.out.println (" calc 5 ");

          while (rs.next ()) {
            DM_FOB_Dirigido = rs.getString ("DM_FOB_Dirigido");
          }

          if (DM_FOB_Dirigido != null && DM_FOB_Dirigido.equals ("S")) {
            ed.setOID_Pessoa_Pagador_Tabela (ed.getOID_Pessoa_Destinatario ());
            // System.out.println (" calc 52 ");

            calcula_frete_FracionadoED = localizaTabela (ed);

          }
        }

        if (calcula_frete_FracionadoED.getDM_Localizado ().equals ("N") &&
            DM_Percurso_Busca.equals ("Origem_Destino")) {
          DM_Percurso_Busca = "Destino_Origem";
          calcula_frete_FracionadoED = localizaTabela (ed);
          // System.out.println (" calc Destino_Origem 2 ");
        }

        if (calcula_frete_FracionadoED.getDM_Localizado ().equals ("N") &&
            DM_Tem_Tabela_Unidade.equals ("S")) {
          sql = " SELECT oid_Pessoa from Unidades ";
          sql += " WHERE Unidades.oid_unidade =" + ed.getOID_Unidade ();

          rs = null;
          rs = this.executasql.executarConsulta (sql);

          while (rs.next ()) {
            //ed.setOID_Produto(3); produto tabela geral
            ed.setOID_Pessoa_Pagador_Tabela (String.valueOf (rs.getString (
                "oid_Pessoa")));
            calcula_frete_FracionadoED = localizaTabela (ed);
          }
        }
        // System.out.println (" calc 6 ");

        if (calcula_frete_FracionadoED.getDM_Localizado () != null &&

            calcula_frete_FracionadoED.getDM_Localizado ().equals ("S")) {
          OID_Conhecimento = ed.getOID_Conhecimento ();

          // System.out.println (" achou tabela ");

          // --------------------FRETE PESO--------------------------------------------------
          Tipo_Movimento = "FP";
          VL_Movimento_Frete = 0;
          if (DM_Tipo_Conhecimento.equals ("9")) {
            sql =
                " SELECT VL_Total_Frete, NR_Peso, VL_Mercadoria, QT_Volumes FROM Coletas ";
            sql += " WHERE Coletas.oid_Coleta = " + oid_Coleta;
//                         // System.out.println(" coleta para frete peso ->>>" + sql);

            rs = null;
            rs = this.executasql.executarConsulta (sql);
            while (rs.next ()) {
              VL_Movimento_Frete = (rs.getDouble ("VL_Total_Frete") /
                                    rs.getDouble ("NR_Peso")) * NR_Peso_NF;
              // VL_Movimento_Frete=(rs.getDouble("VL_Total_Frete")/rs.getDouble("QT_Volumes"))*NR_Volumes_NF;
            }
          }
          else {
            VL_Movimento_Frete = calcula_frete_FracionadoED.
                getVL_Frete_Fracionado ();
            if (calcula_frete_FracionadoED.getDM_Tipo_Peso ().equals ("K")) {
              VL_Movimento_Frete = (calcula_frete_FracionadoED.
                                    getVL_Frete_Fracionado () *
                                    (NR_Peso_Cubado_NF -
                                     calcula_frete_FracionadoED.
                                     getNR_Peso_Minimo ()));
            }
            else if (calcula_frete_FracionadoED.getDM_Tipo_Peso ().equals ("T")) {
              VL_Movimento_Frete = (calcula_frete_FracionadoED.
                                    getVL_Frete_Fracionado () *
                                    (NR_Peso_Cubado_NF -
                                     calcula_frete_FracionadoED.
                                     getNR_Peso_Minimo ()) / 1000);
            }
            else if (calcula_frete_FracionadoED.getDM_Tipo_Peso ().equals ("C")) {
              VL_Movimento_Frete = (calcula_frete_FracionadoED.
                                    getVL_Frete_Fracionado () * NR_Peso_Cub);
            }

            // System.out.println (" calc fp 2 " + DM_Tipo_Conhecimento);

            if (VL_Movimento_Frete < calcula_frete_FracionadoED.
                getVL_Frete_Minimo ()) {
              VL_Movimento_Frete = calcula_frete_FracionadoED.
                  getVL_Frete_Minimo ();
            }
            if (DM_Tipo_Conhecimento.equals ("3") &&
                calcula_frete_FracionadoED.getPE_Devolucao () > 0) {
              VL_Movimento_Frete = VL_Movimento_Frete *
                  ( (100 - calcula_frete_FracionadoED.getPE_Devolucao ()) / 100);
            }
            // System.out.println (" calc fp 3 ");

            if (DM_Tipo_Conhecimento.equals ("R") &&
                calcula_frete_FracionadoED.getPE_Refaturamento () > 0) {
              VL_Movimento_Frete = VL_Movimento_Frete *
                  ( (100 - calcula_frete_FracionadoED.getPE_Refaturamento ()) /
                   100);
            }
            // System.out.println (" calc fp 3 b ");

            if (DM_Tipo_Conhecimento.equals ("4") &&
                calcula_frete_FracionadoED.getPE_Reentrega () > 0) {
              VL_Movimento_Frete = VL_Movimento_Frete *
                  ( (100 - calcula_frete_FracionadoED.getPE_Reentrega ()) / 100);
            }
            // System.out.println (" calc fp 4 ");
          }
          gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                          OID_Conhecimento);

          // --------------------AD VALOREM--------------------------------------------------
          // System.out.println (" calc av ");
          if (calcula_frete_FracionadoED.getVL_Minimo_Nota_Fiscal () <=
              VL_Nota_Fiscal) {
            Tipo_Movimento = "AV";
            VL_Movimento_Frete = (VL_Nota_Fiscal *
                                  calcula_frete_FracionadoED.getPE_AD_Valorem () /
                                  100);

            if (VL_Movimento_Frete < calcula_frete_FracionadoED.
                getVL_AD_Valorem_Minimo ()) {
              VL_Movimento_Frete = calcula_frete_FracionadoED.
                  getVL_AD_Valorem_Minimo ();
            }
            if (DM_Tipo_Conhecimento.equals ("3") &&
                calcula_frete_FracionadoED.getPE_Devolucao () > 0) {
              VL_Movimento_Frete = VL_Movimento_Frete *
                  ( (100 - calcula_frete_FracionadoED.getPE_Devolucao ()) / 100);
            }
            if (DM_Tipo_Conhecimento.equals ("R") &&
                calcula_frete_FracionadoED.getPE_Refaturamento () > 0) {
              VL_Movimento_Frete = VL_Movimento_Frete *
                  ( (100 - calcula_frete_FracionadoED.getPE_Refaturamento ()) /
                   100);
            }

            if (DM_Tipo_Conhecimento.equals ("4") &&
                calcula_frete_FracionadoED.getPE_Reentrega () > 0) {
              VL_Movimento_Frete = VL_Movimento_Frete *
                  ( (100 - calcula_frete_FracionadoED.getPE_Reentrega ()) / 100);
            }

            gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                            OID_Conhecimento);
          }
          // --------------------ADEME/GRIS--------------------------------------------------
          // System.out.println (" calc gr ");

          Tipo_Movimento = "ADM";
          VL_Movimento_Frete = (VL_Nota_Fiscal *
                                calcula_frete_FracionadoED.getPE_Ademe () / 100);
          if (VL_Movimento_Frete < calcula_frete_FracionadoED.
              getVL_Ademe_Minimo ()) {
            VL_Movimento_Frete = calcula_frete_FracionadoED.getVL_Ademe_Minimo ();
          }
          if (DM_Tipo_Conhecimento.equals ("3") &&
              calcula_frete_FracionadoED.getPE_Devolucao () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcula_frete_FracionadoED.getPE_Devolucao ()) / 100);
          }
          if (DM_Tipo_Conhecimento.equals ("R") &&
              calcula_frete_FracionadoED.getPE_Refaturamento () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcula_frete_FracionadoED.getPE_Refaturamento ()) /
                 100);
          }
          if (DM_Tipo_Conhecimento.equals ("4") &&
              calcula_frete_FracionadoED.getPE_Reentrega () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcula_frete_FracionadoED.getPE_Reentrega ()) / 100);
          }

          gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                          OID_Conhecimento);

          // --------------------TAXAS-------------------------------------------------------
          // System.out.println (" calc tx ");

          Tipo_Movimento = "TX";
          VL_Movimento_Frete = calcula_frete_FracionadoED.getVL_Taxas ();
          if (DM_Tipo_Conhecimento.equals ("3") &&
              calcula_frete_FracionadoED.getPE_Devolucao () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcula_frete_FracionadoED.getPE_Devolucao ()) / 100);
          }
          if (DM_Tipo_Conhecimento.equals ("R") &&
              calcula_frete_FracionadoED.getPE_Refaturamento () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcula_frete_FracionadoED.getPE_Refaturamento ()) /
                 100);
          }
          if (DM_Tipo_Conhecimento.equals ("4") &&
              calcula_frete_FracionadoED.getPE_Reentrega () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcula_frete_FracionadoED.getPE_Reentrega ()) / 100);
          }

          gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                          OID_Conhecimento);

          // --------------------DESPACHO----------------------------------------------------
          // System.out.println (" calc dp ");

          Tipo_Movimento = "DP";
          VL_Movimento_Frete = calcula_frete_FracionadoED.getVL_Despacho ();
          if (DM_Tipo_Conhecimento.equals ("3") &&
              calcula_frete_FracionadoED.getPE_Devolucao () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcula_frete_FracionadoED.getPE_Devolucao ()) / 100);
          }
          if (DM_Tipo_Conhecimento.equals ("R") &&
              calcula_frete_FracionadoED.getPE_Refaturamento () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcula_frete_FracionadoED.getPE_Refaturamento ()) /
                 100);
          }
          if (DM_Tipo_Conhecimento.equals ("4") &&
              calcula_frete_FracionadoED.getPE_Reentrega () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcula_frete_FracionadoED.getPE_Reentrega ()) / 100);
          }

          gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                          OID_Conhecimento);

          // --------------------PEDAGIO-----------------------------------------------------
          // System.out.println (" calc ped ");

          Tipo_Movimento = "PED";
          VL_Movimento_Frete = calcula_frete_FracionadoED.getVL_Pedagio ();
          if (calcula_frete_FracionadoED.getDM_Tipo_Pedagio ().equals ("K")) {
            VL_Movimento_Frete = (calcula_frete_FracionadoED.getVL_Pedagio () *
                                  NR_Peso_Cubado_NF);
          }
          if (DM_Tipo_Conhecimento.equals ("3") &&
              calcula_frete_FracionadoED.getPE_Devolucao () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcula_frete_FracionadoED.getPE_Devolucao ()) / 100);
          }
          if (DM_Tipo_Conhecimento.equals ("R") &&
              calcula_frete_FracionadoED.getPE_Refaturamento () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcula_frete_FracionadoED.getPE_Refaturamento ()) /
                 100);
          }
          if (DM_Tipo_Conhecimento.equals ("4") &&
              calcula_frete_FracionadoED.getPE_Reentrega () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcula_frete_FracionadoED.getPE_Reentrega ()) / 100);
          }
          gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                          OID_Conhecimento);

        }
      }

    }
    catch (Excecoes e) {
      if (DM_Tipo_Erro != null && DM_Tipo_Erro.equals ("Nota")) {
        throw new Mensagens (
            "Erro ao incluir - Valor do Frete Maior que o Valor Máximo da Tabela.");
      }
      else {
        throw e;
      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () ,
                          "calcula_frete_Tabela_MT(Calcula_FreteED ed)");
    }
    return conED;
  }

  public Calcula_FreteED calcula_frete_Tabela_Rodov05 (Calcula_FreteED ed) throws
      Excecoes {
    //Transito

    Calcula_FreteED conED = new Calcula_FreteED ();

    Calcula_FreteED calcula_frete_FracionadoED = new Calcula_FreteED ();

    // System.out.println (" transito ");

    try {

      localizado = "N";
      DM_Tem_Produto = "S";
      DM_Tem_Peso = "N";
      DM_Tem_Modal = "N";
      DM_Tem_Validade = "N";
      DM_Tem_Vigencia = "N";
      DM_Tem_Empresa = "N";

      DM_Tem_Cidade = "S";
      DM_Tem_Regiao_Estado = "N";
      DM_Tem_Estado = "S";
      DM_Tem_Subregiao = "S";
      DM_Tem_Regiao_Pais = "N";
      DM_Tem_Pais = "N";
      DM_Tem_Tabela_Unidade = "S";

      calcula_frete_FracionadoED = localizaTabela (ed);

      if (calcula_frete_FracionadoED.getDM_Localizado ().equals ("N") &&
          (ed.getOID_Pessoa_Destinatario ().equals (ed.getOID_Pessoa_Pagador ()))) {

        sql = " SELECT DM_FOB_Dirigido FROM Clientes ";
        sql += " WHERE Clientes.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";

        rs = null;
        rs = this.executasql.executarConsulta (sql);

        while (rs.next ()) {
          DM_FOB_Dirigido = rs.getString ("DM_FOB_Dirigido");
        }

        if (DM_FOB_Dirigido != null && DM_FOB_Dirigido.equals ("S")) {
          ed.setOID_Pessoa_Pagador_Tabela (ed.getOID_Pessoa ());
          calcula_frete_FracionadoED = localizaTabela (ed);
        }
      }

      if (calcula_frete_FracionadoED.getDM_Localizado ().equals ("N") &&
          (ed.getOID_Pessoa ().equals (ed.getOID_Pessoa_Pagador ()))) {

        sql = " SELECT DM_FOB_Dirigido FROM Clientes ";
        sql += " WHERE Clientes.OID_Pessoa = '" + ed.getOID_Pessoa_Destinatario () +
            "'";

        rs = null;
        rs = this.executasql.executarConsulta (sql);

        while (rs.next ()) {
          DM_FOB_Dirigido = rs.getString ("DM_FOB_Dirigido");
        }

        if (DM_FOB_Dirigido != null && DM_FOB_Dirigido.equals ("S")) {
          ed.setOID_Pessoa_Pagador_Tabela (ed.getOID_Pessoa_Destinatario ());
          calcula_frete_FracionadoED = localizaTabela (ed);
        }
      }

      if (calcula_frete_FracionadoED.getDM_Localizado ().equals ("N") &&
          DM_Tem_Tabela_Unidade.equals ("S")) {
        sql = " SELECT oid_Pessoa from Unidades ";
        sql += " WHERE Unidades.oid_unidade =" + ed.getOID_Unidade ();

        rs = null;
        rs = this.executasql.executarConsulta (sql);

        while (rs.next ()) {
          //ed.setOID_Produto(3); produto tabela geral
          ed.setOID_Pessoa_Pagador_Tabela (String.valueOf (rs.getString (
              "oid_Pessoa")));
          calcula_frete_FracionadoED = localizaTabela (ed);
        }
      }

      if (calcula_frete_FracionadoED.getDM_Localizado () != null &&
          calcula_frete_FracionadoED.getDM_Localizado ().equals ("S")) {
        OID_Conhecimento = ed.getOID_Conhecimento ();

        // --------------------FRETE PESO--------------------------------------------------
        Tipo_Movimento = "FP";

        if (NR_Peso_Cubado_NF < 21) {
          VL_Movimento_Frete = calcula_frete_FracionadoED.getVL_Faixa1 ();
        }
        else if (NR_Peso_Cubado_NF < 51) {
          VL_Movimento_Frete = calcula_frete_FracionadoED.getVL_Faixa2 ();
        }
        else if (NR_Peso_Cubado_NF < 101) {
          VL_Movimento_Frete = calcula_frete_FracionadoED.getVL_Faixa3 ();
        }
        else if (NR_Peso_Cubado_NF < 151) {
          VL_Movimento_Frete = calcula_frete_FracionadoED.getVL_Faixa4 ();
        }
        else if (NR_Peso_Cubado_NF < 201) {
          VL_Movimento_Frete = calcula_frete_FracionadoED.getVL_Faixa5 ();
        }
        if (VL_Movimento_Frete == 0.0 || NR_Peso_Cubado_NF > 200) {
          VL_Movimento_Frete = (calcula_frete_FracionadoED.getVL_Frete_KG () *
                                NR_Peso_Cubado_NF / 1000);
        }

        if (VL_Movimento_Frete < calcula_frete_FracionadoED.getVL_Faixa1 ()) {
          VL_Movimento_Frete = calcula_frete_FracionadoED.getVL_Faixa1 ();
        }
        boolean DM_Adicional_Frete = false;
        if (VL_Movimento_Frete > 0) {
          VL_Movimento_Frete = VL_Movimento_Frete +
              calcula_frete_FracionadoED.getVL_Outros1 ();
          DM_Adicional_Frete = true;
        }
        // System.out.println (" transito fp ");
        gera_movimento (Tipo_Movimento , VL_Movimento_Frete , OID_Conhecimento);

        // --------------------AD VALOREM--------------------------------------------------
        Tipo_Movimento = "AV";

        if (VL_Movimento_Frete > ed.getVL_Maximo_Nota_Fiscal ()) {
          VL_Movimento_Frete = (VL_Movimento_Frete *
                                calcula_frete_FracionadoED.getPE_AD_Valorem () /
                                1000);
        }
        if (NR_Peso_Cubado_NF > 200) {
          VL_Movimento_Frete = (VL_Movimento_Frete *
                                calcula_frete_FracionadoED.getVL_Outros4 () /
                                1000);
        }
        if (!DM_Adicional_Frete) {
          VL_Movimento_Frete = VL_Movimento_Frete +
              calcula_frete_FracionadoED.getVL_Outros1 ();
        }

        gera_movimento (Tipo_Movimento , VL_Movimento_Frete , OID_Conhecimento);
        // System.out.println (" transito AD ");

        // --------------------GRIS--------------------------------------------------------
        Tipo_Movimento = "GR";
        if (calcula_frete_FracionadoED.getVL_Outros2 () > 0) {
          VL_Movimento_Frete = (VL_Nota_Fiscal *
                                calcula_frete_FracionadoED.getVL_Outros2 () /
                                100);
          gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                          OID_Conhecimento);
        }
        // System.out.println (" transito GRIS ");

        // --------------------PEDAGIO-----------------------------------------------------
        Tipo_Movimento = "PED";
        VL_Movimento_Frete = calcula_frete_FracionadoED.getVL_Pedagio ();
        if (NR_Peso_Cubado_NF > 200) {
          VL_Movimento_Frete = ( (NR_Peso_Cubado_NF / 200) *
                                calcula_frete_FracionadoED.getVL_Pedagio ());
        }
        gera_movimento (Tipo_Movimento , VL_Movimento_Frete , OID_Conhecimento);
        // System.out.println (" transito PED ");

        // --------------------TAXAS-------------------------------------------------------
        Tipo_Movimento = "TX";
        if (NR_Peso_Cubado_NF > 200) {
          VL_Movimento_Frete = calcula_frete_FracionadoED.getVL_Taxas ();
          gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                          OID_Conhecimento);
        }
        // System.out.println (" transito TX ");

      }
    }
    catch (Excecoes e) {
      if (DM_Tipo_Erro != null && DM_Tipo_Erro.equals ("Nota")) {
        throw new Mensagens (
            "Erro ao incluir - Valor do Frete Maior que o Valor Máximo da Tabela.");
      }
      else {
        throw e;
      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () ,
                          "calcula_frete_Tabela_Transito(Calcula_FreteED ed)");
    }
    return conED;
  }

  public Calcula_FreteED calcula_frete_Entrega (Calcula_FreteED ed ,
                                                double VL_Total_Frete_Calculado) throws
      Excecoes {

    Calcula_FreteED conED = new Calcula_FreteED ();

    Calcula_FreteED calcula_frete_FracionadoED = new Calcula_FreteED ();

    // System.out.println (" inicio calculo entrega  ");

    VL_Frete_Entrega = 0;
    DM_Tabela_Cliente_Entregador = "E";
    calcula_frete_FracionadoED = localizaTabela (ed);

    if (calcula_frete_FracionadoED.getDM_Localizado () != null &&
        calcula_frete_FracionadoED.getDM_Localizado ().equals ("S")) {

      OID_Conhecimento = ed.getOID_Conhecimento ();

      // --------------------FRETE %   --------------------------------------------------
      VL_Movimento_Frete = VL_Total_Frete_Calculado *
          calcula_frete_FracionadoED.getPE_Frete_Entrega () / 100;
      if (VL_Movimento_Frete < calcula_frete_FracionadoED.getVL_Frete_Minimo ()) {
        VL_Movimento_Frete = calcula_frete_FracionadoED.getVL_Frete_Minimo ();
      }
      VL_Frete_Entrega = VL_Frete_Entrega + VL_Movimento_Frete;

      // --------------------FRETE PESO--------------------------------------------------
      VL_Movimento_Frete = calcula_frete_FracionadoED.getVL_Frete_Fracionado ();
      if (calcula_frete_FracionadoED.getDM_Tipo_Peso ().equals ("K")) {
        VL_Movimento_Frete = (calcula_frete_FracionadoED.getVL_Frete_Fracionado () *
                              NR_Peso_Cubado_NF);
      }
      else if (calcula_frete_FracionadoED.getDM_Tipo_Peso ().equals ("T")) {
        VL_Movimento_Frete = (calcula_frete_FracionadoED.getVL_Frete_Fracionado () *
                              NR_Peso_Cubado_NF / 1000);
      }
      if (VL_Movimento_Frete < calcula_frete_FracionadoED.getVL_Frete_Minimo ()) {
        VL_Movimento_Frete = calcula_frete_FracionadoED.getVL_Frete_Minimo ();
      }
      VL_Frete_Entrega = VL_Frete_Entrega + VL_Movimento_Frete;

      // --------------------AD VALOREM--------------------------------------------------
      VL_Movimento_Frete = (VL_Nota_Fiscal *
                            calcula_frete_FracionadoED.getPE_AD_Valorem () /
                            1000);

      if (VL_Movimento_Frete < calcula_frete_FracionadoED.
          getVL_AD_Valorem_Minimo ()) {
        VL_Movimento_Frete = calcula_frete_FracionadoED.getVL_AD_Valorem_Minimo ();
      }
      VL_Frete_Entrega = VL_Frete_Entrega + VL_Movimento_Frete;

      // --------------------ADEME/GRIS--------------------------------------------------
      VL_Movimento_Frete = (VL_Nota_Fiscal *
                            calcula_frete_FracionadoED.getPE_Ademe () / 1000);
      if (VL_Movimento_Frete < calcula_frete_FracionadoED.getVL_Ademe_Minimo ()) {
        VL_Movimento_Frete = calcula_frete_FracionadoED.getVL_Ademe_Minimo ();
      }
      VL_Frete_Entrega = VL_Frete_Entrega + VL_Movimento_Frete;

      // --------------------TAXAS-------------------------------------------------------
      VL_Movimento_Frete = calcula_frete_FracionadoED.getVL_Taxas ();
      VL_Frete_Entrega = VL_Frete_Entrega + VL_Movimento_Frete;

      // --------------------DESPACHO----------------------------------------------------
      VL_Movimento_Frete = calcula_frete_FracionadoED.getVL_Despacho ();
      VL_Frete_Entrega = VL_Frete_Entrega + VL_Movimento_Frete;

      // --------------------PEDAGIO-----------------------------------------------------
      VL_Movimento_Frete = calcula_frete_FracionadoED.getVL_Pedagio ();
      if (calcula_frete_FracionadoED.getDM_Tipo_Pedagio ().equals ("K")) {
        VL_Movimento_Frete = (calcula_frete_FracionadoED.getVL_Pedagio () *
                              NR_Peso_Cubado_NF);
      }
      VL_Frete_Entrega = VL_Frete_Entrega + VL_Movimento_Frete;

      Tipo_Movimento = "ENT";
      gera_movimento (Tipo_Movimento , VL_Frete_Entrega , OID_Conhecimento);

      // System.out.println (" calculo entrega  " + VL_Frete_Entrega);

    }
    return conED;
  }

  public Calcula_FreteED calcula_frete_Tabela_Rodov01 (Calcula_FreteED calcFreteED) throws      Excecoes {

    //LEVA E TRAZ
    try {

      // System.out.println ("rodoviario ");

          OID_Conhecimento = calcFreteED.getOID_Conhecimento ();

          // System.out.println (" achou tabela ");

          // --------------------FRETE PESO--------------------------------------------------
          Tipo_Movimento = "FP";
          VL_Movimento_Frete = 0;

          // System.out.println (" coleta para frete peso ->>>" + sql);

          if (DM_Tipo_Conhecimento.equals ("9")) {
            sql =
                " SELECT VL_Total_Frete, NR_Peso, VL_Mercadoria, QT_Volumes FROM Coletas ";
            sql += " WHERE Coletas.oid_Coleta = " + oid_Coleta;
            //                         // System.out.println(" coleta para frete peso ->>>" + sql);

            rs = null;
            rs = this.executasql.executarConsulta (sql);
            while (rs.next ()) {
              VL_Movimento_Frete = (rs.getDouble ("VL_Total_Frete") /
                                    rs.getDouble ("NR_Peso")) * NR_Peso_NF;
              // VL_Movimento_Frete=(rs.getDouble("VL_Total_Frete")/rs.getDouble("QT_Volumes"))*NR_Volumes_NF;
            }
          }
          else {
            VL_Movimento_Frete = calcFreteED.
                getVL_Frete_Fracionado ();
            if (calcFreteED.getDM_Tipo_Peso ().equals ("K")) {
              VL_Movimento_Frete = (calcFreteED.
                                    getVL_Frete_Fracionado () *
                                    (NR_Peso_Cubado_NF -
                                     calcFreteED.getNR_Peso_Minimo ()));
            }
            else if (calcFreteED.getDM_Tipo_Peso ().equals ("T")) {
              VL_Movimento_Frete = (calcFreteED.
                                    getVL_Frete_Fracionado () *
                                    (NR_Peso_Cubado_NF -
                                     calcFreteED.getNR_Peso_Minimo ()) /
                                    1000);
            }
            else if (calcFreteED.getDM_Tipo_Peso ().equals ("C")) {
              VL_Movimento_Frete = (calcFreteED.
                                    getVL_Frete_Fracionado () * NR_Peso_Cub);
            }

            if (calcFreteED.getVL_Minimo_Nota_Fiscal () >
                VL_Nota_Fiscal) {
              VL_Movimento_Frete += calcFreteED.getVL_Outros2 ();
            }

            if (VL_Movimento_Frete < calcFreteED.
                getVL_Frete_Minimo ()) {
              VL_Movimento_Frete = calcFreteED.
                  getVL_Frete_Minimo ();
            }
            if (DM_Tipo_Conhecimento.equals ("3") &&
                calcFreteED.getPE_Devolucao () > 0) {
              VL_Movimento_Frete = VL_Movimento_Frete *
                  ( (100 - calcFreteED.getPE_Devolucao ()) / 100);
            }

            if (DM_Tipo_Conhecimento.equals ("R") &&
                calcFreteED.getPE_Refaturamento () > 0) {
              VL_Movimento_Frete = VL_Movimento_Frete *
                  ( (100 - calcFreteED.getPE_Refaturamento ()) /
                   100);
            }

            if (DM_Tipo_Conhecimento.equals ("4") &&
                calcFreteED.getPE_Reentrega () > 0) {
              VL_Movimento_Frete = VL_Movimento_Frete *
                  ( (100 - calcFreteED.getPE_Reentrega ()) / 100);
            }

            if (NR_Cubagem_Total > 0 && NR_Cubagem_Total > NR_Cubagem) {
              VL_Movimento_Frete = ( (VL_Movimento_Frete / NR_Cubagem_Total) *
                                    NR_Cubagem);
            }
          }
          gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                          OID_Conhecimento);

          // --------------------CARGA EXPRESSA--------------------------------------------------
          Tipo_Movimento = "CE";
          if (calcFreteED.getPE_Carga_Expressa () > 0 &&
              calcFreteED.getVL_Frete_Fracionado () > 0) {
            VL_Movimento_Frete = calcFreteED.
                getVL_Frete_Fracionado () * (calcFreteED.getPE_Carga_Expressa () / 100);
            gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                            OID_Conhecimento);
          }

          // --------------------AD VALOREM--------------------------------------------------
          // System.out.println (" calc av ");
          if (calcFreteED.getVL_Minimo_Nota_Fiscal () <=
              VL_Nota_Fiscal) {
            Tipo_Movimento = "AV";
            VL_Movimento_Frete = (VL_Nota_Fiscal *
                                  calcFreteED.getPE_AD_Valorem () /
                                  100);

            if (VL_Movimento_Frete < calcFreteED.
                getVL_AD_Valorem_Minimo ()) {
              VL_Movimento_Frete = calcFreteED.
                  getVL_AD_Valorem_Minimo ();
            }
            if (DM_Tipo_Conhecimento.equals ("3") &&
                calcFreteED.getPE_Devolucao () > 0) {
              VL_Movimento_Frete = VL_Movimento_Frete *
                  ( (100 - calcFreteED.getPE_Devolucao ()) / 100);
            }
            if (DM_Tipo_Conhecimento.equals ("R") &&
                calcFreteED.getPE_Refaturamento () > 0) {
              VL_Movimento_Frete = VL_Movimento_Frete *
                  ( (100 - calcFreteED.getPE_Refaturamento ()) /
                   100);
            }

            if (DM_Tipo_Conhecimento.equals ("4") &&
                calcFreteED.getPE_Reentrega () > 0) {
              VL_Movimento_Frete = VL_Movimento_Frete *
                  ( (100 - calcFreteED.getPE_Reentrega ()) / 100);
            }

            gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                            OID_Conhecimento);
          }
          // --------------------ADEME/GRIS--------------------------------------------------
          // System.out.println (" calc gr ");

          Tipo_Movimento = "ADM";
          VL_Movimento_Frete = (VL_Nota_Fiscal *
                                calcFreteED.getPE_Ademe () / 100);
          if (VL_Movimento_Frete < calcFreteED.
              getVL_Ademe_Minimo ()) {
            VL_Movimento_Frete = calcFreteED.getVL_Ademe_Minimo ();
          }
          if (DM_Tipo_Conhecimento.equals ("3") &&
              calcFreteED.getPE_Devolucao () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcFreteED.getPE_Devolucao ()) / 100);
          }
          if (DM_Tipo_Conhecimento.equals ("R") &&
              calcFreteED.getPE_Refaturamento () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcFreteED.getPE_Refaturamento ()) /
                 100);
          }
          if (DM_Tipo_Conhecimento.equals ("4") &&
              calcFreteED.getPE_Reentrega () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcFreteED.getPE_Reentrega ()) / 100);
          }

          gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                          OID_Conhecimento);

          // --------------------TAXAS-------------------------------------------------------
          // System.out.println (" calc tx ");

          Tipo_Movimento = "TX";
          VL_Movimento_Frete = calcFreteED.getVL_Taxas ();
          if (DM_Tipo_Conhecimento.equals ("3") &&
              calcFreteED.getPE_Devolucao () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcFreteED.getPE_Devolucao ()) / 100);
          }
          if (DM_Tipo_Conhecimento.equals ("R") &&
              calcFreteED.getPE_Refaturamento () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcFreteED.getPE_Refaturamento ()) /
                 100);
          }
          if (DM_Tipo_Conhecimento.equals ("4") &&
              calcFreteED.getPE_Reentrega () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcFreteED.getPE_Reentrega ()) / 100);
          }

          gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                          OID_Conhecimento);

          // --------------------DESPACHO----------------------------------------------------
          // System.out.println (" calc dp ");

          Tipo_Movimento = "DP";
          VL_Movimento_Frete = calcFreteED.getVL_Despacho ();
          if (DM_Tipo_Conhecimento.equals ("3") &&
              calcFreteED.getPE_Devolucao () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcFreteED.getPE_Devolucao ()) / 100);
          }
          if (DM_Tipo_Conhecimento.equals ("R") &&
              calcFreteED.getPE_Refaturamento () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcFreteED.getPE_Refaturamento ()) /
                 100);
          }
          if (DM_Tipo_Conhecimento.equals ("4") &&
              calcFreteED.getPE_Reentrega () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcFreteED.getPE_Reentrega ()) / 100);
          }

          gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                          OID_Conhecimento);

          // --------------------PEDAGIO-----------------------------------------------------
          // System.out.println (" calc ped ");

          Tipo_Movimento = "PED";
          VL_Movimento_Frete = calcFreteED.getVL_Pedagio ();
          if (calcFreteED.getDM_Tipo_Pedagio ().equals ("K")) {
            VL_Movimento_Frete = (calcFreteED.getVL_Pedagio () *
                                  NR_Peso_Cubado_NF);
          }
          if (DM_Tipo_Conhecimento.equals ("3") &&
              calcFreteED.getPE_Devolucao () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcFreteED.getPE_Devolucao ()) / 100);
          }
          if (DM_Tipo_Conhecimento.equals ("R") &&
              calcFreteED.getPE_Refaturamento () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcFreteED.getPE_Refaturamento ()) /
                 100);
          }
          if (DM_Tipo_Conhecimento.equals ("4") &&
              calcFreteED.getPE_Reentrega () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcFreteED.getPE_Reentrega ()) / 100);
          }

          if (NR_Cubagem_Total > 0 && NR_Cubagem_Total > NR_Cubagem) {
            VL_Movimento_Frete = ( (VL_Movimento_Frete / NR_Cubagem_Total) *
                                  NR_Cubagem);
          }

          gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                          OID_Conhecimento);

          // --------------------SEC/CAT-----------------------------------------------------
          Tipo_Movimento = "SEC";
          VL_Movimento_Frete = calcFreteED.getVL_Outros1 ();
          gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                          OID_Conhecimento);

        NR_Peso_Cubado_NF = NR_Peso_Cub;

    }
    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () ,
                          "calcula_frete_Rodov02(Calcula_FreteED ed)");
    }
    return calcFreteED;
  }


  public Calcula_FreteED calcula_frete_Tabela_Rodov02 (Calcula_FreteED calcFreteED) throws      Excecoes {

    ///kieling
    try {

      // System.out.println ("rodoviario ");

      // --------------------FRETE PESO--------------------------------------------------
      Tipo_Movimento = "FP";

      if (calcFreteED.getVL_R_Ate7500() <=0 || NR_Peso_Tabela <=200) {  ///RONALDO

        if (NR_Peso_Tabela < 11) {
          VL_Movimento_Frete = calcFreteED.getVL_R_Ate10 ();
        }
        else if (NR_Peso_Tabela < 21) {
          VL_Movimento_Frete = calcFreteED.getVL_R_Ate20 ();
        }
        else if (NR_Peso_Tabela < 31) {
          VL_Movimento_Frete = calcFreteED.getVL_R_Ate30 ();
        }
        else if (NR_Peso_Tabela < 51) {
          VL_Movimento_Frete = calcFreteED.getVL_R_Ate50 ();
        }
        else if (NR_Peso_Tabela < 71) {
          VL_Movimento_Frete = calcFreteED.getVL_R_Ate70 ();
        }
        else if (NR_Peso_Tabela < 101) {
          VL_Movimento_Frete = calcFreteED.getVL_R_Ate100 ();
        }
        else if (NR_Peso_Tabela < 151) {
          VL_Movimento_Frete = calcFreteED.getVL_R_Ate150 ();
        }
        else if (NR_Peso_Tabela < 201) {
          VL_Movimento_Frete = calcFreteED.getVL_R_Ate200 ();
        }

        if (VL_Movimento_Frete == 0.0 || NR_Peso_Tabela > 200) {
          VL_Movimento_Frete = (calcFreteED.getVL_R_Ate200 () +
                                (calcFreteED.getVL_R_Acima200 () *
                                 (NR_Peso_Tabela - 200)));
        }

        if (VL_Movimento_Frete < calcFreteED.getVL_R_Ate10 ()) {
          VL_Movimento_Frete = calcFreteED.getVL_R_Ate10 ();
        }
      }
      else { //elster
        if (NR_Peso_Tabela <= 7500) {
          VL_Movimento_Frete = calcFreteED.getVL_R_Ate7500 () *
              NR_Peso_Tabela / 1000;
        }
        else if (NR_Peso_Tabela <= 14500) {
          VL_Movimento_Frete = calcFreteED.getVL_R_Ate14500 () *
              14.5;
        }
        else {
          VL_Movimento_Frete = calcFreteED.getVL_R_Acima14500 () *
              25.5;
        }
      }
      if (VL_Movimento_Frete < calcFreteED.getVL_R_Ate10 ()) {
        VL_Movimento_Frete = calcFreteED.getVL_R_Ate10 ();
      }
      gera_movimento (Tipo_Movimento , VL_Movimento_Frete , OID_Conhecimento);

      // System.out.println (" FP ");
      if (calcFreteED.getVL_R_Ate7500 () <= 0) {
        // --------------------AD VALOREM FRACIONADA---------------------------------------
        Tipo_Movimento = "AV";
        VL_Movimento_Frete = (VL_Nota_Fiscal *
                              calcFreteED.getPE_AD_Valorem () /
                              100);
        if (VL_Movimento_Frete < calcFreteED.
            getVL_AD_Valorem_Minimo ()) {
          VL_Movimento_Frete = calcFreteED.
              getVL_AD_Valorem_Minimo ();
        }
        gera_movimento (Tipo_Movimento , VL_Movimento_Frete , OID_Conhecimento);
      }
      else {
        // --------------------AD VALOREM completa-----------------------------------------
        Tipo_Movimento = "AV";
        if (NR_Peso_Tabela <= 7500) {
          VL_Movimento_Frete = (VL_Nota_Fiscal *
                                calcFreteED.getPE_AD_Valorem () /
                                100);
          if (VL_Movimento_Frete < calcFreteED.
              getVL_AD_Valorem_Minimo ()) {
            VL_Movimento_Frete = calcFreteED.
                getVL_AD_Valorem_Minimo ();
          }
        }
        else {
          VL_Movimento_Frete = (VL_Nota_Fiscal *
                                calcFreteED.getPE_AD_Valorem2 () /
                                100);
          if (VL_Movimento_Frete < calcFreteED.
              getVL_AD_Valorem_Minimo ()) {
            VL_Movimento_Frete = calcFreteED.
                getVL_AD_Valorem_Minimo ();
          }
        }
        gera_movimento (Tipo_Movimento , VL_Movimento_Frete , OID_Conhecimento);

      }

      // System.out.println (" AV  ");

      // --------------------AD ADEME----------------------------------------------------
      Tipo_Movimento = "ADM"; //ademe
      VL_Movimento_Frete = (VL_Nota_Fiscal *
                            calcFreteED.getPE_Ademe () / 100);
      if (VL_Movimento_Frete < calcFreteED.getVL_Ademe_Minimo ()) {
        VL_Movimento_Frete = calcFreteED.getVL_Ademe_Minimo ();
      }
      gera_movimento (Tipo_Movimento , VL_Movimento_Frete , OID_Conhecimento);

      // System.out.println (" ADM ");
      if (DM_Suframa != null && DM_Suframa.equals ("S")) {
        // --------------------SUFRAMA-----------------------------------------------------
        Tipo_Movimento = "SUF"; //ademe
        VL_Movimento_Frete = (VL_Nota_Fiscal *
                              calcFreteED.getPE_Suframa () / 100);
        if (VL_Movimento_Frete < calcFreteED.
            getVL_Suframa_Minimo ()) {
          VL_Movimento_Frete = calcFreteED.getVL_Suframa_Minimo ();
        }
        gera_movimento (Tipo_Movimento , VL_Movimento_Frete , OID_Conhecimento);

        // System.out.println (" SUF  ");
      }
      if (DM_Fluvial != null && DM_Fluvial.equals ("S")) {
        // --------------------FLUVIAL-----------------------------------------------------
        Tipo_Movimento = "FLU"; //ademe
        VL_Movimento_Frete = (VL_Nota_Fiscal *
                              calcFreteED.getPE_Fluvial () / 100);
        if (VL_Movimento_Frete < calcFreteED.
            getVL_Fluvial_Minimo ()) {
          VL_Movimento_Frete = calcFreteED.getVL_Fluvial_Minimo ();
        }
        gera_movimento (Tipo_Movimento , VL_Movimento_Frete , OID_Conhecimento);
      }

      // --------------------PEDAGIO-----------------------------------------------------
      Tipo_Movimento = "PED";
      VL_Movimento_Frete = calcFreteED.getVL_Pedagio ();
      // System.out.println (" PED 2");
      if (NR_Peso_Tabela > 100) {
        VL_Movimento_Frete = (NR_Peso_Tabela / 100 * 0.96);
      }
      // System.out.println (" PED 3");

//                    double Peso_Pedagio=NR_Peso_Tabela;
//                    VL_Movimento_Frete =0.72;
//                    while (Peso_Pedagio >100){
//                        Peso_Pedagio=Peso_Pedagio-100;
//                        VL_Movimento_Frete=VL_Movimento_Frete + 0.72;
//                    }
      if (VL_Movimento_Frete < calcFreteED.getVL_Pedagio ()) {
        VL_Movimento_Frete = calcFreteED.getVL_Pedagio ();
      }
      // System.out.println (" PED 4");

      if (calcFreteED.getDM_Tipo_Pedagio () != null &&
          calcFreteED.getDM_Tipo_Pedagio ().equals ("C")) {
        // System.out.println (" PED 5");
        VL_Movimento_Frete = calcFreteED.getVL_Pedagio ();
      }
      // System.out.println (" PED 6");
      gera_movimento (Tipo_Movimento , VL_Movimento_Frete , OID_Conhecimento);
      // System.out.println (" FLU  ");
      // --------------------DESPACHO----------------------------------------------------
        // System.out.println (" calc do despacho ");

        Tipo_Movimento = "DP";
        VL_Movimento_Frete = calcFreteED.getVL_Despacho ();
        gera_movimento (Tipo_Movimento , VL_Movimento_Frete , OID_Conhecimento);

        // System.out.println (" calc do despacho OK ");

    }
    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () ,
                          "calcula_frete_Rodov02(Calcula_FreteED ed)");
    }
    return calcFreteED;
  }

  public Calcula_FreteED calcula_frete_Tabela_Aereo02 (Calcula_FreteED calcFreteED) throws      Excecoes {

    ///kieling
    try {

          // System.out.println ("AEREO");
          // --------------------FRETE PESO--------------------------------------------------
          Tipo_Movimento = "FP";
          if (NR_Peso_Tabela < 26) {
            VL_Movimento_Frete = calcFreteED.getVL_C_Ate25 () *
                NR_Peso_Tabela;
          }
          else if (NR_Peso_Tabela < 51) {
            VL_Movimento_Frete = calcFreteED.getVL_C_Ate50 () *
                NR_Peso_Tabela;
          }
          else if (NR_Peso_Tabela < 301) {
            VL_Movimento_Frete = calcFreteED.getVL_C_Ate300 () *
                NR_Peso_Tabela;
          }
          else if (NR_Peso_Tabela < 501) {
            VL_Movimento_Frete = calcFreteED.getVL_C_Ate500 () *
                NR_Peso_Tabela;
          }
          else if (NR_Peso_Tabela < 1001) {
            VL_Movimento_Frete = calcFreteED.getVL_C_Ate1000 () *
                NR_Peso_Tabela;
          }
          if (VL_Movimento_Frete == 0.0 || NR_Peso_Cubado_NF > 1000) {
            VL_Movimento_Frete = (calcFreteED.getVL_C_Acima1000 () *
                                  NR_Peso_Tabela);
          }
          if (VL_Movimento_Frete < calcFreteED.
              getVL_C_Taxa_Minima ()) {
            VL_Movimento_Frete = calcFreteED.getVL_C_Taxa_Minima ();
          }
          gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                          OID_Conhecimento);

          // System.out.println (" FP ");

          // --------------------AD VALOREM--------------------------------------------------
          Tipo_Movimento = "AV";
          VL_Movimento_Frete = (VL_Nota_Fiscal *
                                calcFreteED.getPE_C_Ad_Valorem () /
                                100);
          gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                          OID_Conhecimento);

          // --------------------PEDAGIO-----------------------------------------------------
          Tipo_Movimento = "PED";
          VL_Movimento_Frete = calcFreteED.getVL_C_Pedagio ();

          if (NR_Peso_Tabela > 100) {
            VL_Movimento_Frete = (NR_Peso_Tabela / 100 * 0.96);
          }
          // System.out.println (" PED 3");

//                double Peso_Pedagio=NR_Peso_Cubado_NF;
//                while (Peso_Pedagio >100){
//                    Peso_Pedagio=Peso_Pedagio-100;
//                    VL_Movimento_Frete=VL_Movimento_Frete + 0.72;
//                }

          if (VL_Movimento_Frete < 1.45) {
            VL_Movimento_Frete = calcFreteED.getVL_C_Pedagio ();
          }
          gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                          OID_Conhecimento);

          if (!calcFreteED.getDM_Tipo_Coleta ().equals ("X") &&
              !calcFreteED.getDM_Tipo_Coleta ().equals ("") &&
              !calcFreteED.getDM_Tipo_Coleta ().equals ("null")) {
            // --------------------TX EXC COLETA--------------------------------------------------
            Tipo_Movimento = "TEC";
            if (NR_Peso_Tabela > 10) {
              VL_Movimento_Frete = calcFreteED.
                  getVL_TX_Exc_Coleta () * (NR_Peso_Tabela - 10);
              gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                              OID_Conhecimento);
            }
            if (calcFreteED.getDM_Tipo_Coleta ().equals ("U")) {
              // --------------------TX COLETA URG-------------------------------------------------
              Tipo_Movimento = "TCU";
              if (NR_Peso_Tabela < 201) {
                VL_Movimento_Frete = calcFreteED.
                    getVL_TX_Col_Urg_200 ();
              }
              else if (NR_Peso_Tabela < 1001) {
                VL_Movimento_Frete = calcFreteED.
                    getVL_TX_Col_Urg_1000 ();
              }
              if (VL_Movimento_Frete == 0.0 || NR_Peso_Tabela > 1000) {
                VL_Movimento_Frete = (calcFreteED.
                                      getVL_TX_Col_Urg_Ton ());
              }
              gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                              OID_Conhecimento);
            }
            else {
              // --------------------TX COLETA------------------------------------------------------
              Tipo_Movimento = "TCN";
              VL_Movimento_Frete = calcFreteED.getVL_TX_Coleta ();
              gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                              OID_Conhecimento);
            }
          }

          if (!calcFreteED.getDM_Tipo_Entrega ().equals ("X") &&
              !calcFreteED.getDM_Tipo_Entrega ().equals ("") &&
              !calcFreteED.getDM_Tipo_Entrega ().equals ("null")) {
            // --------------------TX EXC ENTREGA-------------------------------------------------
            Tipo_Movimento = "TEE";
            if (NR_Peso_Tabela > 10) {
              VL_Movimento_Frete = calcFreteED.
                  getVL_TX_Exc_Entrega () * (NR_Peso_Tabela - 10);
              gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                              OID_Conhecimento);
            }

            if (calcFreteED.getDM_Tipo_Entrega ().equals ("U")) {
              // --------------------TX ENTREGA URG-------------------------------------------------
              Tipo_Movimento = "TEU";
              if (NR_Peso_Tabela < 201) {
                VL_Movimento_Frete = calcFreteED.
                    getVL_TX_Ent_Urg_200 ();
              }
              else if (NR_Peso_Tabela < 1001) {
                VL_Movimento_Frete = calcFreteED.
                    getVL_TX_Ent_Urg_1000 ();
              }
              if (VL_Movimento_Frete == 0.0 || NR_Peso_Tabela > 1000) {
                VL_Movimento_Frete = (calcFreteED.
                                      getVL_TX_Ent_Urg_Ton ());
              }
              gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                              OID_Conhecimento);
            }
            else {
              // --------------------TX ENTREGA-----------------------------------------------------
              Tipo_Movimento = "TEN";
              VL_Movimento_Frete = calcFreteED.getVL_TX_Entrega ();
              gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                              OID_Conhecimento);
            }
          }
          // --------------------DESPACHO----------------------------------------------------
            // System.out.println (" calc do despacho ");

            Tipo_Movimento = "DP";
            VL_Movimento_Frete = calcFreteED.getVL_Despacho ();
            gera_movimento (Tipo_Movimento , VL_Movimento_Frete , OID_Conhecimento);

            // System.out.println (" calc do despacho OK ");

      // System.out.println (" FLU  ");

    }
    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () ,
                          "calcula_frete_Aereo02(Calcula_FreteED ed)");
    }
    return calcFreteED;
  }



  public Calcula_FreteED calcula_frete_Tabela_Documento02 (Calcula_FreteED calcFreteED) throws      Excecoes {

    ///kieling
    try {

          // --------------------FRETE PESO--------------------------------------------------
          Tipo_Movimento = "FP";

          if (!DM_Tipo_Localizacao.equals ("I")) {

            // System.out.println ("DOC 1");
            if (NR_Peso_Tabela < 2) {
              VL_Movimento_Frete = calcFreteED.getVL_D_Ate1C ();
            }
            else if (NR_Peso_Tabela < 3) {
              VL_Movimento_Frete = calcFreteED.getVL_D_Ate2C ();
            }
            else if (NR_Peso_Tabela < 4) {
              VL_Movimento_Frete = calcFreteED.getVL_D_Ate3C ();
            }
            else if (NR_Peso_Tabela < 5) {
              VL_Movimento_Frete = calcFreteED.getVL_D_Ate4C ();
            }
            else if (NR_Peso_Tabela < 6) {
              VL_Movimento_Frete = calcFreteED.getVL_D_Ate5C ();
            }
            else if (NR_Peso_Tabela < 7) {
              VL_Movimento_Frete = calcFreteED.getVL_D_Ate6C ();
            }
            else if (NR_Peso_Tabela < 8) {
              VL_Movimento_Frete = calcFreteED.getVL_D_Ate7C ();
            }
            else if (NR_Peso_Tabela < 9) {
              VL_Movimento_Frete = calcFreteED.getVL_D_Ate8C ();
            }
            else if (NR_Peso_Tabela < 10) {
              VL_Movimento_Frete = calcFreteED.getVL_D_Ate9C ();
            }
            else if (NR_Peso_Tabela == 10) {
              VL_Movimento_Frete = calcFreteED.getVL_D_Ate10C ();
            }
            if (VL_Movimento_Frete == 0.0 || NR_Peso_Tabela > 10) {
              VL_Movimento_Frete = (calcFreteED.getVL_D_Ate10C () +
                                    (calcFreteED.
                                     getVL_D_ExcedenteC () *
                                     (NR_Peso_Tabela - 10)));
            }
            if (VL_Movimento_Frete < calcFreteED.getVL_D_Ate1C ()) {
              VL_Movimento_Frete = calcFreteED.getVL_D_Ate1C ();
            }
            gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                            OID_Conhecimento);
            // System.out.println (" FP ");
          }
          else {
            // System.out.println ("DOC INTER 1");

            if (NR_Peso_Tabela < 2) {
              VL_Movimento_Frete = calcFreteED.getVL_D_Ate1D ();
            }
            else if (NR_Peso_Tabela < 3) {
              VL_Movimento_Frete = calcFreteED.getVL_D_Ate2D ();
            }
            else if (NR_Peso_Tabela < 4) {
              VL_Movimento_Frete = calcFreteED.getVL_D_Ate3D ();
            }
            else if (NR_Peso_Tabela < 5) {
              VL_Movimento_Frete = calcFreteED.getVL_D_Ate4D ();
            }
            else if (NR_Peso_Tabela < 6) {
              VL_Movimento_Frete = calcFreteED.getVL_D_Ate5D ();
            }
            else if (NR_Peso_Tabela < 7) {
              VL_Movimento_Frete = calcFreteED.getVL_D_Ate6D ();
            }
            else if (NR_Peso_Tabela < 8) {
              VL_Movimento_Frete = calcFreteED.getVL_D_Ate7D ();
            }
            else if (NR_Peso_Tabela < 9) {
              VL_Movimento_Frete = calcFreteED.getVL_D_Ate8D ();
            }
            else if (NR_Peso_Tabela < 10) {
              VL_Movimento_Frete = calcFreteED.getVL_D_Ate9D ();
            }
            else if (NR_Peso_Tabela == 10) {
              VL_Movimento_Frete = calcFreteED.getVL_D_Ate10D ();
            }
            if (VL_Movimento_Frete == 0.0 || NR_Peso_Tabela > 10) {
              VL_Movimento_Frete = (calcFreteED.getVL_D_Ate10D () +
                                    (calcFreteED.
                                     getVL_D_ExcedenteD () *
                                     (NR_Peso_Tabela - 10)));
            }
            // System.out.println ("DOC INTER 3");

            gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                            OID_Conhecimento);
          }

          // --------------------AD VALOREM--------------------------------------------------
          Tipo_Movimento = "AV";
          VL_Movimento_Frete = (VL_Nota_Fiscal *
                                calcFreteED.getPE_D_Ad_Valorem () /
                                100);
          gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                          OID_Conhecimento);


          // --------------------DESPACHO----------------------------------------------------
            // System.out.println (" calc do despacho ");

            Tipo_Movimento = "DP";
            VL_Movimento_Frete = calcFreteED.getVL_Despacho ();
            gera_movimento (Tipo_Movimento , VL_Movimento_Frete , OID_Conhecimento);

            // System.out.println (" calc do despacho OK ");


    }
    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () ,
                          "calcula_frete_Documento02(Calcula_FreteED ed)");
    }
    return calcFreteED;
  }

  public Calcula_FreteED calcula_frete_Tabela_EComerce02 (Calcula_FreteED calcFreteED) throws      Excecoes {

    ///kieling
    try {

          // System.out.println ("E-COMERCE");
          // --------------------FRETE PESO--------------------------------------------------
          Tipo_Movimento = "FP";

          VL_Movimento_Frete = calcFreteED.getVL_E_Ate1 ();
          if (NR_Peso_Tabela >= 1 && NR_Peso_Tabela < 2) {
            VL_Movimento_Frete = calcFreteED.getVL_E_1Kg ();
          }
          if (NR_Peso_Tabela >= 2) {

            double NR_Peso_Tabela_Calc = Valor.round (NR_Peso_Tabela , 0);
            if ( (NR_Peso_Tabela * 10) > (NR_Peso_Tabela_Calc * 10)) {
              NR_Peso_Tabela_Calc += 1;
            }
            VL_Movimento_Frete = calcFreteED.getVL_E_1Kg () +
                (calcFreteED.getVL_E_Excedente () *
                 (Valor.round (NR_Peso_Tabela_Calc , 0) - 1));

          }

          gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                          OID_Conhecimento);

          // System.out.println (" FP E");

          // --------------------AD VALOREM--------------------------------------------------
          Tipo_Movimento = "AV";
          VL_Movimento_Frete = (VL_Nota_Fiscal *
                                calcFreteED.getPE_E_Ad_Valorem () /
                                100);
          gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                          OID_Conhecimento);
          // System.out.println (" AV E ");

    }
    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () ,
                          "calcula_frete_Aereo02(Calcula_FreteED ed)");
    }
    return calcFreteED;
  }


  public Calcula_FreteED localizaCondicaoTabela (Calcula_FreteED ed) throws
      Excecoes {

    Calcula_FreteED conED = new Calcula_FreteED ();
    Calcula_FreteED calcula_frete_FracionadoED = new Calcula_FreteED ();

    try {
      localizado = "N";
      DM_Tem_Peso = "N";
      DM_Tem_Modal = "N";
      DM_Tem_Validade = "N";
      DM_Tem_Vigencia = "N";
      DM_Tem_Empresa = "N";

      DM_Tem_Cidade = "S";
      DM_Tem_Regiao_Estado = "N";
      DM_Tem_Estado = "S";
      DM_Tem_Subregiao = "S";
      DM_Tem_Regiao_Pais = "N";
      DM_Tem_Pais = "S";
      DM_Tipo_Tabela_Frete = "S";
      DM_Tem_Tabela_Unidade = "S";

      PE_PIS_COFINS = parametro_FixoED.getPE_Aliquota_COFINS ();
      DM_PIS_COFINS = parametro_FixoED.getDM_Calcula_PIS_COFINS ();
      if (DM_PIS_COFINS.equals ("F")) {
        sql =
            " SELECT Clientes.DM_PIS_COFINS, Clientes.PE_PIS_COFINS FROM Clientes ";
        sql += " WHERE  Clientes.OID_Pessoa = '" + ed.getOID_Pessoa_Pagador () +
            "'";

        rs = this.executasql.executarConsulta (sql);
        while (rs.next ()) {
          if (rs.getString ("DM_PIS_COFINS") != null &&
              !rs.getString ("DM_PIS_COFINS").equals ("") &&
              !rs.getString ("DM_PIS_COFINS").equals ("null")) {
            DM_PIS_COFINS = rs.getString ("DM_PIS_COFINS");
          }
          PE_PIS_COFINS = rs.getDouble ("PE_PIS_COFINS");
        }
      }


      sql =
          " SELECT DM_TIPO_LOCALIZACAO, DM_SUFRAMA, DM_FLUVIAL  FROM Cidades ";
      sql += " WHERE Cidades.OID_Cidade = " + ed.getOID_Cidade_Destino ();
      // System.out.println (sql);

      rs = null;
      rs = this.executasql.executarConsulta (sql);

      DM_Tipo_Localizacao = "I";
      DM_Suframa = "N";
      DM_Fluvial = "N";
      while (rs.next ()) {
        DM_Tipo_Localizacao = rs.getString ("DM_TIPO_LOCALIZACAO");
        DM_Suframa = rs.getString ("DM_SUFRAMA");
        DM_Fluvial = rs.getString ("DM_FLUVIAL");
      }

      DM_Tipo_Tabela_Frete = ed.getDM_Tipo_Tabela_Frete ();

      calcula_frete_FracionadoED = localizaTabela (ed);

      if (calcula_frete_FracionadoED.getDM_Localizado ().equals ("N") &&
          (ed.getOID_Pessoa_Destinatario ().equals (ed.getOID_Pessoa_Pagador ()))) {

        sql = " SELECT DM_FOB_Dirigido FROM Clientes ";
        sql += " WHERE Clientes.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";

        rs = null;
        rs = this.executasql.executarConsulta (sql);

        while (rs.next ()) {
          DM_FOB_Dirigido = rs.getString ("DM_FOB_Dirigido");
        }

        if (DM_FOB_Dirigido != null && DM_FOB_Dirigido.equals ("S")) {
          ed.setOID_Pessoa_Pagador_Tabela (ed.getOID_Pessoa ());
          calcula_frete_FracionadoED = localizaTabela (ed);
        }
      }

      if (calcula_frete_FracionadoED.getDM_Localizado ().equals ("N") &&
          (ed.getOID_Pessoa ().equals (ed.getOID_Pessoa_Pagador ()))) {

        sql = " SELECT DM_FOB_Dirigido FROM Clientes ";
        sql += " WHERE Clientes.OID_Pessoa = '" + ed.getOID_Pessoa_Destinatario () +
            "'";

        rs = null;
        rs = this.executasql.executarConsulta (sql);

        while (rs.next ()) {
          DM_FOB_Dirigido = rs.getString ("DM_FOB_Dirigido");
        }

        if (DM_FOB_Dirigido != null && DM_FOB_Dirigido.equals ("S")) {
          ed.setOID_Pessoa_Pagador_Tabela (ed.getOID_Pessoa_Destinatario ());
          calcula_frete_FracionadoED = localizaTabela (ed);
        }
      }

      if (calcula_frete_FracionadoED.getDM_Localizado ().equals ("N") &&
          DM_Tem_Tabela_Unidade.equals ("S")) {

        sql = " SELECT oid_Pessoa from Unidades ";
        sql += " WHERE Unidades.oid_unidade =" + ed.getOID_Unidade ();

        rs = null;
        rs = this.executasql.executarConsulta (sql);

        while (rs.next ()) {
          ed.setOID_Produto (3);
          ed.setOID_Pessoa_Pagador_Tabela (String.valueOf (rs.getString (
              "oid_Pessoa")));
          calcula_frete_FracionadoED = localizaTabela (ed);
        }
      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () ,
                          "pesquisa tabela");
    }
    return calcula_frete_FracionadoED;
  }

  public Calcula_FreteED calcula_frete_Tabela_Rodov01OLD (Calcula_FreteED ed) throws
      Excecoes {
    //LEVA E TRAZ
    Calcula_FreteED conED = new Calcula_FreteED ();

    Calcula_FreteED calcula_frete_FracionadoED = new Calcula_FreteED ();

    // System.out.println (" levaetraz ");

    try {

      localizado = "N";
      DM_Tem_Peso = "S";
      DM_Tem_Modal = "S";
      DM_Tem_Validade = "N";
      DM_Tem_Vigencia = "S";
      DM_Tem_Empresa = "N";
      DM_Tem_Cidade = "S";
      DM_Tem_Regiao_Estado = "S";
      DM_Tem_Estado = "S";
      DM_Tem_Subregiao = "N";
      DM_Tem_Regiao_Pais = "N";
      DM_Tem_Pais = "S";

      DM_Tabela_Reversa = "N";
      DM_Tem_Tabela_Unidade = "N";

      // System.out.println (" DM_Tipo_Conhecimento " + DM_Tipo_Conhecimento);

      if (DM_Tipo_Conhecimento != null && DM_Tipo_Conhecimento.equals ("2") &&
          parametro_FixoED.getDM_Frete_Cortesia ().equals ("PARAMETRO")) {
        // --------------------CORTESIA TIPO(2)--------------------------------------------
        Tipo_Movimento = "FP";
        // System.out.println (" CORTESIA 1 ");
        VL_Movimento_Frete = parametro_FixoED.getVL_Frete_Cortesia ();
        gera_movimento (Tipo_Movimento , VL_Movimento_Frete , OID_Conhecimento);
      }
      else {
        DM_Percurso_Busca = "Origem_Destino";
        if (DM_Tipo_Conhecimento != null && DM_Tipo_Conhecimento.equals ("3")) {
          DM_Percurso_Busca = "Destino_Origem";
        }
        // System.out.println (" calc 0 " + DM_Percurso_Busca);

        //** Calcula cubagem ou pelo M3 X peso para cubagem do cadastro do cliente */
        double pesoCubadoCalculado = calculaCubagem (ed.
            getOID_Pessoa_Pagador_Tabela () , NR_Peso_Cub);
        // System.out.println ("pesoCubadoCalculado: " + pesoCubadoCalculado);
        //** Peso para fins de cálculo é o peso maior entre peso bruto e peso cubado */
        double pesoCalculado = NR_Peso_NF > pesoCubadoCalculado ? NR_Peso_NF :
            pesoCubadoCalculado;

        NR_Peso_Tabela = pesoCalculado;

        calcula_frete_FracionadoED = localizaTabela (ed);

        // System.out.println (" calc 1 ");

        if (calcula_frete_FracionadoED.getDM_Localizado ().equals ("N") &&
            (ed.getOID_Pessoa_Destinatario ().equals (ed.getOID_Pessoa_Pagador ()))) {

          sql = " SELECT DM_FOB_Dirigido FROM Clientes ";
          sql += " WHERE Clientes.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";

          // System.out.println (" calc 2 ");

          rs = null;
          rs = this.executasql.executarConsulta (sql);

          while (rs.next ()) {
            DM_FOB_Dirigido = rs.getString ("DM_FOB_Dirigido");
          }

          if (DM_FOB_Dirigido != null && DM_FOB_Dirigido.equals ("S")) {
            ed.setOID_Pessoa_Pagador_Tabela (ed.getOID_Pessoa ());
            calcula_frete_FracionadoED = localizaTabela (ed);
          }
        }
        // System.out.println (" calc 3 ");

        if (calcula_frete_FracionadoED.getDM_Localizado ().equals ("N") &&
            (ed.getOID_Pessoa ().equals (ed.getOID_Pessoa_Pagador ()))) {

          // System.out.println (" calc 4 ");

          sql = " SELECT DM_FOB_Dirigido FROM Clientes ";
          sql += " WHERE Clientes.OID_Pessoa = '" +
              ed.getOID_Pessoa_Destinatario () + "'";

          rs = null;
          rs = this.executasql.executarConsulta (sql);

          // System.out.println (" calc 5 ");

          while (rs.next ()) {
            DM_FOB_Dirigido = rs.getString ("DM_FOB_Dirigido");
          }

          if (DM_FOB_Dirigido != null && DM_FOB_Dirigido.equals ("S")) {
            ed.setOID_Pessoa_Pagador_Tabela (ed.getOID_Pessoa_Destinatario ());
            // System.out.println (" calc 52 ");

            calcula_frete_FracionadoED = localizaTabela (ed);

          }
        }

        if (calcula_frete_FracionadoED.getDM_Localizado ().equals ("N") &&
            DM_Percurso_Busca.equals ("Origem_Destino") &&
            DM_Tabela_Reversa.equals ("S")) {
          DM_Percurso_Busca = "Destino_Origem";
          calcula_frete_FracionadoED = localizaTabela (ed);
          // System.out.println (" calc Destino_Origem 2 ");
        }

        if (calcula_frete_FracionadoED.getDM_Localizado ().equals ("N") &&
            DM_Tem_Tabela_Unidade.equals ("S")) {
          sql = " SELECT oid_Pessoa from Unidades ";
          sql += " WHERE Unidades.oid_unidade =" + ed.getOID_Unidade ();

          rs = null;
          rs = this.executasql.executarConsulta (sql);

          while (rs.next ()) {
            //ed.setOID_Produto(3); produto tabela geral
            ed.setOID_Pessoa_Pagador_Tabela (String.valueOf (rs.getString (
                "oid_Pessoa")));
            calcula_frete_FracionadoED = localizaTabela (ed);
          }
        }
        // System.out.println (" calc 6 ");

        if (!"S".equals (calcula_frete_FracionadoED.getDM_Localizado ())) {
          String oid_PessoaAnterior = ed.getOID_Pessoa_Pagador_Tabela ();
          ed.setOID_Pessoa_Pagador_Tabela (Parametro_FixoED.getInstancia ().
                                           getOID_Pessoa_Padrao_Tabela_Frete ());
          calcula_frete_FracionadoED = localizaTabela (ed);
          ed.setOID_Pessoa_Pagador_Tabela (oid_PessoaAnterior);
        }

        if (calcula_frete_FracionadoED.getDM_Localizado () != null &&

            calcula_frete_FracionadoED.getDM_Localizado ().equals ("S")) {
          OID_Conhecimento = ed.getOID_Conhecimento ();

          // System.out.println (" achou tabela ");

          // --------------------FRETE PESO--------------------------------------------------
          Tipo_Movimento = "FP";
          VL_Movimento_Frete = 0;

          // System.out.println (" coleta para frete peso ->>>" + sql);

          if (DM_Tipo_Conhecimento.equals ("9")) {
            sql =
                " SELECT VL_Total_Frete, NR_Peso, VL_Mercadoria, QT_Volumes FROM Coletas ";
            sql += " WHERE Coletas.oid_Coleta = " + oid_Coleta;
            //                         // System.out.println(" coleta para frete peso ->>>" + sql);

            rs = null;
            rs = this.executasql.executarConsulta (sql);
            while (rs.next ()) {
              VL_Movimento_Frete = (rs.getDouble ("VL_Total_Frete") /
                                    rs.getDouble ("NR_Peso")) * NR_Peso_NF;
              // VL_Movimento_Frete=(rs.getDouble("VL_Total_Frete")/rs.getDouble("QT_Volumes"))*NR_Volumes_NF;
            }
          }
          else {
            VL_Movimento_Frete = calcula_frete_FracionadoED.
                getVL_Frete_Fracionado ();
            if (calcula_frete_FracionadoED.getDM_Tipo_Peso ().equals ("K")) {
              VL_Movimento_Frete = (calcula_frete_FracionadoED.
                                    getVL_Frete_Fracionado () *
                                    (pesoCalculado -
                                     calcula_frete_FracionadoED.getNR_Peso_Minimo ()));
            }
            else if (calcula_frete_FracionadoED.getDM_Tipo_Peso ().equals ("T")) {
              VL_Movimento_Frete = (calcula_frete_FracionadoED.
                                    getVL_Frete_Fracionado () *
                                    (pesoCalculado -
                                     calcula_frete_FracionadoED.getNR_Peso_Minimo ()) /
                                    1000);
            }
            else if (calcula_frete_FracionadoED.getDM_Tipo_Peso ().equals ("C")) {
              VL_Movimento_Frete = (calcula_frete_FracionadoED.
                                    getVL_Frete_Fracionado () * NR_Peso_Cub);
            }

            if (calcula_frete_FracionadoED.getVL_Minimo_Nota_Fiscal () >
                VL_Nota_Fiscal) {
              VL_Movimento_Frete += calcula_frete_FracionadoED.getVL_Outros2 ();
            }

            if (VL_Movimento_Frete < calcula_frete_FracionadoED.
                getVL_Frete_Minimo ()) {
              VL_Movimento_Frete = calcula_frete_FracionadoED.
                  getVL_Frete_Minimo ();
            }
            if (DM_Tipo_Conhecimento.equals ("3") &&
                calcula_frete_FracionadoED.getPE_Devolucao () > 0) {
              VL_Movimento_Frete = VL_Movimento_Frete *
                  ( (100 - calcula_frete_FracionadoED.getPE_Devolucao ()) / 100);
            }

            if (DM_Tipo_Conhecimento.equals ("R") &&
                calcula_frete_FracionadoED.getPE_Refaturamento () > 0) {
              VL_Movimento_Frete = VL_Movimento_Frete *
                  ( (100 - calcula_frete_FracionadoED.getPE_Refaturamento ()) /
                   100);
            }

            if (DM_Tipo_Conhecimento.equals ("4") &&
                calcula_frete_FracionadoED.getPE_Reentrega () > 0) {
              VL_Movimento_Frete = VL_Movimento_Frete *
                  ( (100 - calcula_frete_FracionadoED.getPE_Reentrega ()) / 100);
            }

            if (NR_Cubagem_Total > 0 && NR_Cubagem_Total > NR_Cubagem) {
              VL_Movimento_Frete = ( (VL_Movimento_Frete / NR_Cubagem_Total) *
                                    NR_Cubagem);
            }
          }
          gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                          OID_Conhecimento);

          // --------------------CARGA EXPRESSA--------------------------------------------------
          Tipo_Movimento = "CE";

          if (ed.getPE_Carga_Expressa () > 0 &&
              calcula_frete_FracionadoED.getVL_Frete_Fracionado () > 0) {
            VL_Movimento_Frete = calcula_frete_FracionadoED.
                getVL_Frete_Fracionado () * (ed.getPE_Carga_Expressa () / 100);
            gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                            OID_Conhecimento);
          }

          // --------------------AD VALOREM--------------------------------------------------
          // System.out.println (" calc av ");
          if (calcula_frete_FracionadoED.getVL_Minimo_Nota_Fiscal () <=
              VL_Nota_Fiscal) {
            Tipo_Movimento = "AV";
            VL_Movimento_Frete = (VL_Nota_Fiscal *
                                  calcula_frete_FracionadoED.getPE_AD_Valorem () /
                                  100);

            if (VL_Movimento_Frete < calcula_frete_FracionadoED.
                getVL_AD_Valorem_Minimo ()) {
              VL_Movimento_Frete = calcula_frete_FracionadoED.
                  getVL_AD_Valorem_Minimo ();
            }
            if (DM_Tipo_Conhecimento.equals ("3") &&
                calcula_frete_FracionadoED.getPE_Devolucao () > 0) {
              VL_Movimento_Frete = VL_Movimento_Frete *
                  ( (100 - calcula_frete_FracionadoED.getPE_Devolucao ()) / 100);
            }
            if (DM_Tipo_Conhecimento.equals ("R") &&
                calcula_frete_FracionadoED.getPE_Refaturamento () > 0) {
              VL_Movimento_Frete = VL_Movimento_Frete *
                  ( (100 - calcula_frete_FracionadoED.getPE_Refaturamento ()) /
                   100);
            }

            if (DM_Tipo_Conhecimento.equals ("4") &&
                calcula_frete_FracionadoED.getPE_Reentrega () > 0) {
              VL_Movimento_Frete = VL_Movimento_Frete *
                  ( (100 - calcula_frete_FracionadoED.getPE_Reentrega ()) / 100);
            }

            gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                            OID_Conhecimento);
          }
          // --------------------ADEME/GRIS--------------------------------------------------
          // System.out.println (" calc gr ");

          Tipo_Movimento = "ADM";
          VL_Movimento_Frete = (VL_Nota_Fiscal *
                                calcula_frete_FracionadoED.getPE_Ademe () / 100);
          if (VL_Movimento_Frete < calcula_frete_FracionadoED.
              getVL_Ademe_Minimo ()) {
            VL_Movimento_Frete = calcula_frete_FracionadoED.getVL_Ademe_Minimo ();
          }
          if (DM_Tipo_Conhecimento.equals ("3") &&
              calcula_frete_FracionadoED.getPE_Devolucao () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcula_frete_FracionadoED.getPE_Devolucao ()) / 100);
          }
          if (DM_Tipo_Conhecimento.equals ("R") &&
              calcula_frete_FracionadoED.getPE_Refaturamento () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcula_frete_FracionadoED.getPE_Refaturamento ()) /
                 100);
          }
          if (DM_Tipo_Conhecimento.equals ("4") &&
              calcula_frete_FracionadoED.getPE_Reentrega () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcula_frete_FracionadoED.getPE_Reentrega ()) / 100);
          }

          gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                          OID_Conhecimento);

          // --------------------TAXAS-------------------------------------------------------
          // System.out.println (" calc tx ");

          Tipo_Movimento = "TX";
          VL_Movimento_Frete = calcula_frete_FracionadoED.getVL_Taxas ();
          if (DM_Tipo_Conhecimento.equals ("3") &&
              calcula_frete_FracionadoED.getPE_Devolucao () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcula_frete_FracionadoED.getPE_Devolucao ()) / 100);
          }
          if (DM_Tipo_Conhecimento.equals ("R") &&
              calcula_frete_FracionadoED.getPE_Refaturamento () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcula_frete_FracionadoED.getPE_Refaturamento ()) /
                 100);
          }
          if (DM_Tipo_Conhecimento.equals ("4") &&
              calcula_frete_FracionadoED.getPE_Reentrega () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcula_frete_FracionadoED.getPE_Reentrega ()) / 100);
          }

          gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                          OID_Conhecimento);

          // --------------------DESPACHO----------------------------------------------------
          // System.out.println (" calc dp ");

          Tipo_Movimento = "DP";
          VL_Movimento_Frete = calcula_frete_FracionadoED.getVL_Despacho ();
          if (DM_Tipo_Conhecimento.equals ("3") &&
              calcula_frete_FracionadoED.getPE_Devolucao () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcula_frete_FracionadoED.getPE_Devolucao ()) / 100);
          }
          if (DM_Tipo_Conhecimento.equals ("R") &&
              calcula_frete_FracionadoED.getPE_Refaturamento () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcula_frete_FracionadoED.getPE_Refaturamento ()) /
                 100);
          }
          if (DM_Tipo_Conhecimento.equals ("4") &&
              calcula_frete_FracionadoED.getPE_Reentrega () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcula_frete_FracionadoED.getPE_Reentrega ()) / 100);
          }

          gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                          OID_Conhecimento);

          // --------------------PEDAGIO-----------------------------------------------------
          // System.out.println (" calc ped ");

          Tipo_Movimento = "PED";
          VL_Movimento_Frete = calcula_frete_FracionadoED.getVL_Pedagio ();
          if (calcula_frete_FracionadoED.getDM_Tipo_Pedagio ().equals ("K")) {
            VL_Movimento_Frete = (calcula_frete_FracionadoED.getVL_Pedagio () *
                                  NR_Peso_Cubado_NF);
          }
          if (DM_Tipo_Conhecimento.equals ("3") &&
              calcula_frete_FracionadoED.getPE_Devolucao () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcula_frete_FracionadoED.getPE_Devolucao ()) / 100);
          }
          if (DM_Tipo_Conhecimento.equals ("R") &&
              calcula_frete_FracionadoED.getPE_Refaturamento () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcula_frete_FracionadoED.getPE_Refaturamento ()) /
                 100);
          }
          if (DM_Tipo_Conhecimento.equals ("4") &&
              calcula_frete_FracionadoED.getPE_Reentrega () > 0) {
            VL_Movimento_Frete = VL_Movimento_Frete *
                ( (100 - calcula_frete_FracionadoED.getPE_Reentrega ()) / 100);
          }

          if (NR_Cubagem_Total > 0 && NR_Cubagem_Total > NR_Cubagem) {
            VL_Movimento_Frete = ( (VL_Movimento_Frete / NR_Cubagem_Total) *
                                  NR_Cubagem);
          }

          gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                          OID_Conhecimento);

          // --------------------SEC/CAT-----------------------------------------------------
          Tipo_Movimento = "SEC";
          VL_Movimento_Frete = calcula_frete_FracionadoED.getVL_Outros1 ();
          gera_movimento (Tipo_Movimento , VL_Movimento_Frete ,
                          OID_Conhecimento);

        }
        NR_Peso_Cubado_NF = NR_Peso_Cub;
      }

    }
    catch (Excecoes e) {
      if (DM_Tipo_Erro != null && DM_Tipo_Erro.equals ("Nota")) {
        throw new Mensagens (
            "Erro ao incluir - Valor do Frete Maior que o Valor Máximo da Tabela.");
      }
      else {
        throw e;
      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () ,
                          "calcula_frete_Tabela_LevaETraz(Calcula_FreteED ed)");
    }
    return conED;
  }

  public Calcula_FreteED carregaTabela (Calcula_FreteED ed , ResultSet rsTabela) throws
      Excecoes {

    Calcula_FreteED conED = new Calcula_FreteED ();
    try {

      conED.setDM_Localizado ("S");

      // System.out.println (" carrega tabela 1");

      OID_Tabela_Frete = rsTabela.getString ("oid_tabela_frete");

      conED.setOID_Pessoa (rsTabela.getString ("oid_Pessoa"));

      conED.setVL_Frete_Fracionado (rsTabela.getDouble ("VL_Frete"));
      conED.setVL_Frete_KG (rsTabela.getDouble ("VL_Frete_KG"));
      conED.setVL_Taxas (rsTabela.getDouble ("VL_Taxas"));
      conED.setPE_AD_Valorem (rsTabela.getDouble ("PE_AD_Valorem"));

      conED.setVL_Outros1 (rsTabela.getDouble ("VL_Outros1"));
      conED.setVL_Outros2 (rsTabela.getDouble ("VL_Outros2"));
      conED.setVL_Outros3 (rsTabela.getDouble ("VL_Outros3"));
      conED.setVL_Outros4 (rsTabela.getDouble ("VL_Outros4"));
      conED.setVL_Outros5 (rsTabela.getDouble ("VL_Outros5"));
      conED.setVL_Outros6 (rsTabela.getDouble ("VL_Outros6"));
      conED.setVL_Outros7 (rsTabela.getDouble ("VL_Outros7"));
      conED.setVL_Outros8 (rsTabela.getDouble ("VL_Outros8"));
      conED.setVL_Outros9 (rsTabela.getDouble ("VL_Outros9"));

      conED.setVL_Faixa1 (rsTabela.getDouble ("VL_Faixa1"));
      conED.setVL_Faixa2 (rsTabela.getDouble ("VL_Faixa2"));
      conED.setVL_Faixa3 (rsTabela.getDouble ("VL_Faixa3"));
      conED.setVL_Faixa4 (rsTabela.getDouble ("VL_Faixa4"));
      conED.setVL_Faixa5 (rsTabela.getDouble ("VL_Faixa5"));

      // System.out.println (" 11");

      conED.setVL_R_Ate10 (rsTabela.getDouble ("VL_R_Ate10"));
      conED.setVL_R_Ate20 (rsTabela.getDouble ("VL_R_Ate20"));
      conED.setVL_R_Ate30 (rsTabela.getDouble ("VL_R_Ate30"));
      conED.setVL_R_Ate50 (rsTabela.getDouble ("VL_R_Ate50"));
      conED.setVL_R_Ate70 (rsTabela.getDouble ("VL_R_Ate70"));
      conED.setVL_R_Ate100 (rsTabela.getDouble ("VL_R_Ate100"));
      conED.setVL_R_Ate150 (rsTabela.getDouble ("VL_R_Ate150"));
      conED.setVL_R_Ate200 (rsTabela.getDouble ("VL_R_Ate200"));
      conED.setVL_R_Acima200 (rsTabela.getDouble ("VL_R_Acima200"));
      // System.out.println (" 12");
      conED.setVL_R_Ate7500 (rsTabela.getDouble ("VL_R_ATE7500"));
      conED.setVL_R_Ate14500 (rsTabela.getDouble ("VL_R_ATE14500"));
      conED.setVL_R_Acima14500 (rsTabela.getDouble ("VL_R_ACIMA14500"));
      conED.setPE_AD_Valorem2 (rsTabela.getDouble ("PE_AD_Valorem2"));
      // System.out.println (" 12 b");

      conED.setVL_Suframa_Minimo (rsTabela.getDouble ("VL_Suframa_Minimo"));
      conED.setPE_Suframa (rsTabela.getDouble ("PE_Suframa"));
      conED.setPE_Fluvial (rsTabela.getDouble ("PE_Fluvial"));
      conED.setVL_Fluvial_Minimo (rsTabela.getDouble ("VL_Fluvial_Minimo"));
      conED.setVL_C_Ate25 (rsTabela.getDouble ("VL_C_Ate25"));
      conED.setVL_C_Ate50 (rsTabela.getDouble ("VL_C_Ate50"));
      conED.setVL_C_Ate300 (rsTabela.getDouble ("VL_C_Ate300"));
      conED.setVL_C_Ate500 (rsTabela.getDouble ("VL_C_Ate500"));
      conED.setVL_C_Ate1000 (rsTabela.getDouble ("VL_C_Ate1000"));
      conED.setVL_C_Acima1000 (rsTabela.getDouble ("VL_C_Acima1000"));
      conED.setVL_C_Taxa_Minima (rsTabela.getDouble ("VL_C_Taxa_Minima"));
      // System.out.println (" 13");

      conED.setPE_C_Ad_Valorem (rsTabela.getDouble ("PE_C_Ad_Valorem"));
      conED.setVL_C_Pedagio (rsTabela.getDouble ("VL_C_Pedagio"));
      conED.setVL_TX_KM_Rodado (rsTabela.getDouble ("VL_TX_KM_Rodado"));
      conED.setVL_TX_Coleta (rsTabela.getDouble ("VL_TX_Coleta"));
      conED.setVL_TX_Entrega (rsTabela.getDouble ("VL_TX_Entrega"));
      conED.setVL_TX_Col_Urg_200 (rsTabela.getDouble ("VL_TX_Col_Urg_200"));
      conED.setVL_TX_Col_Urg_1000 (rsTabela.getDouble ("VL_TX_Col_Urg_1000"));
      conED.setVL_TX_Col_Urg_Ton (rsTabela.getDouble ("VL_TX_Col_Urg_Ton"));
      conED.setVL_TX_Ent_Urg_200 (rsTabela.getDouble ("VL_TX_Ent_Urg_200"));
      conED.setVL_TX_Ent_Urg_1000 (rsTabela.getDouble ("VL_TX_Ent_Urg_1000"));
      conED.setVL_TX_Ent_Urg_Ton (rsTabela.getDouble ("VL_TX_Ent_Urg_Ton"));
      conED.setVL_TX_Exc_Coleta (rsTabela.getDouble ("VL_TX_Exc_Coleta"));
      conED.setVL_TX_Exc_Entrega (rsTabela.getDouble ("VL_TX_Exc_Entrega"));
      // System.out.println (" 14");

      conED.setVL_D_Ate1C (rsTabela.getDouble ("VL_D_Ate1C"));
      conED.setVL_D_Ate2C (rsTabela.getDouble ("VL_D_Ate2C"));
      conED.setVL_D_Ate3C (rsTabela.getDouble ("VL_D_Ate3C"));
      conED.setVL_D_Ate4C (rsTabela.getDouble ("VL_D_Ate4C"));
      conED.setVL_D_Ate5C (rsTabela.getDouble ("VL_D_Ate5C"));
      conED.setVL_D_Ate6C (rsTabela.getDouble ("VL_D_Ate6C"));
      conED.setVL_D_Ate7C (rsTabela.getDouble ("VL_D_Ate7C"));
      conED.setVL_D_Ate8C (rsTabela.getDouble ("VL_D_Ate8C"));
      conED.setVL_D_Ate9C (rsTabela.getDouble ("VL_D_Ate9C"));
      conED.setVL_D_Ate10C (rsTabela.getDouble ("VL_D_Ate10C"));
      conED.setVL_D_Ate1D (rsTabela.getDouble ("VL_D_Ate1D"));
      conED.setVL_D_Ate2D (rsTabela.getDouble ("VL_D_Ate2D"));
      conED.setVL_D_Ate3D (rsTabela.getDouble ("VL_D_Ate3D"));
      conED.setVL_D_Ate4D (rsTabela.getDouble ("VL_D_Ate4D"));
      conED.setVL_D_Ate5D (rsTabela.getDouble ("VL_D_Ate5D"));
      conED.setVL_D_Ate6D (rsTabela.getDouble ("VL_D_Ate6D"));
      conED.setVL_D_Ate7D (rsTabela.getDouble ("VL_D_Ate7D"));
      conED.setVL_D_Ate8D (rsTabela.getDouble ("VL_D_Ate8D"));
      conED.setVL_D_Ate9D (rsTabela.getDouble ("VL_D_Ate9D"));
      // System.out.println (" 15");

      conED.setVL_D_Ate10D (rsTabela.getDouble ("VL_D_Ate10D"));
      conED.setVL_D_ExcedenteC (rsTabela.getDouble ("VL_D_ExcedenteC"));
      conED.setVL_D_ExcedenteD (rsTabela.getDouble ("VL_D_ExcedenteD"));
      conED.setPE_D_Ad_Valorem (rsTabela.getDouble ("PE_D_Ad_Valorem"));
      conED.setPE_Desc_FP (rsTabela.getDouble ("PE_Desc_FP"));
      conED.setPE_Desc_FV (rsTabela.getDouble ("PE_Desc_FV"));
      conED.setVL_E_Ate1 (rsTabela.getDouble ("VL_E_Ate1"));
      conED.setVL_E_1Kg (rsTabela.getDouble ("VL_E_1Kg"));
      conED.setVL_E_Excedente (rsTabela.getDouble ("VL_E_Excedente"));
      conED.setVL_E_Ad_Valorem (rsTabela.getDouble ("VL_E_Ad_Valorem"));
      conED.setPE_E_Ad_Valorem (rsTabela.getDouble ("PE_E_Ad_Valorem"));

      // System.out.println (" 16");

      conED.setDM_Tipo_Peso (rsTabela.getString ("DM_Tipo_Peso"));
      conED.setDM_Tipo_Tabela (rsTabela.getString ("DM_Tipo_Tabela"));
      conED.setVL_Frete_Minimo (rsTabela.getDouble ("VL_Frete_Minimo"));
      conED.setVL_Ademe_Minimo (rsTabela.getDouble ("VL_Ademe_Minimo"));
      conED.setPE_Ademe (rsTabela.getDouble ("PE_Ademe"));
      conED.setPE_Devolucao (rsTabela.getDouble ("PE_Devolucao"));
      conED.setPE_Reentrega (rsTabela.getDouble ("PE_Reentrega"));
      conED.setVL_AD_Valorem_Minimo (rsTabela.getDouble ("VL_AD_Valorem_Minimo"));
      conED.setPE_AD_Valorem (rsTabela.getDouble ("PE_AD_Valorem"));
      conED.setVL_Maximo_Nota_Fiscal (rsTabela.getDouble (
          "VL_Maximo_Nota_Fiscal"));
      conED.setVL_Minimo_Nota_Fiscal (rsTabela.getDouble (
          "VL_Minimo_Nota_Fiscal"));

      // System.out.println (" 61 peso ->> " + conED.getDM_Tipo_Peso ());

      conED.setVL_Despacho (rsTabela.getDouble ("VL_Despacho"));
      conED.setPE_Frete_Entrega (rsTabela.getDouble ("VL_Frete_Valor_Minimo"));
      conED.setDM_Tipo_Pedagio (rsTabela.getString ("DM_Tipo_Pedagio"));
      conED.setVL_Pedagio (rsTabela.getDouble ("VL_Pedagio"));
      conED.setOID_Empresa (rsTabela.getLong ("OID_empresa"));

      conED.setOID_Pessoa_Entregador (rsTabela.getString (
          "OID_PESSOA_REDESPACHO"));
      conED.setVL_Minimo_Nota_Fiscal (rsTabela.getDouble (
          "VL_Minimo_Nota_Fiscal"));
      OID_Pessoa_Entregadora = rsTabela.getString ("OID_PESSOA_REDESPACHO");
      NR_Prazo_Entrega = rsTabela.getInt ("NR_Prazo_Entrega");
      DM_ICMS_Incluso_Frete = (rsTabela.getString ("DM_ICMS"));

      // System.out.println (" retorno dm_icms ->> " + DM_ICMS_Incluso_Frete);

      // System.out.println (" volta do carrega tabela");

    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () ,
          "carregaTabela(Calcula_FreteED ed, ResultSet rsTabela )");
    }
    return conED;
  }

  public Calcula_FreteED localizaTabela (Calcula_FreteED ed) throws Excecoes {

    // System.out.println (" acha tabela 1 ");
    String sql = null;
    Calcula_FreteED conED = new Calcula_FreteED ();

    conED.setDM_Localizado ("N");

    OID_Pessoa = ed.getOID_Pessoa_Pagador_Tabela ();
    if (DM_Tabela_Cliente_Entregador.equals ("E")) {
      OID_Cliente = OID_Pessoa;
      OID_Pessoa = OID_Pessoa_Entregadora;
    }

    // System.out.println (" acha tabela 3 ");

    try {
      //-------------------------CIDADE---------------------------------------------
      if (DM_Tem_Cidade.equals ("S")) {
        if (conED.getDM_Localizado ().equals ("N")) {
          sql = " SELECT * FROM Tabelas_Fretes ";
          sql += " WHERE Tabelas_Fretes.OID_Pessoa = '" + OID_Pessoa + "'";
          if (DM_Percurso_Busca.equals ("Origem_Destino")) {
            sql += " AND Tabelas_Fretes.OID_Origem = " +
                ed.getOID_Cidade_Origem ();
            sql += " AND Tabelas_Fretes.OID_Destino = " +
                ed.getOID_Cidade_Destino ();
            sql += " AND Tabelas_Fretes.DM_Origem = 'C'";
            sql += " AND Tabelas_Fretes.DM_Destino = 'C'";

          }
          if (DM_Percurso_Busca.equals ("Destino_Origem")) {
            sql += " AND Tabelas_Fretes.OID_Destino = " +
                ed.getOID_Cidade_Origem ();
            sql += " AND Tabelas_Fretes.OID_Origem = " +
                ed.getOID_Cidade_Destino ();
            sql += " AND Tabelas_Fretes.DM_Origem = 'C'";
            sql += " AND Tabelas_Fretes.DM_Destino = 'C'";
          }

          if (DM_Tabela_Cliente_Entregador.equals ("E")) {
            sql += " AND Tabelas_Fretes.OID_Pessoa_Redespacho = '" +
                OID_Cliente + "'";
          }

          if (DM_Tem_Produto.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Produto = " + ed.getOID_Produto ();
          }

          if (DM_Tem_Empresa.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Empresa = " + ed.getOID_Empresa ();
          }
          if (DM_Tem_Peso.equals ("S")) {
            sql += " AND Tabelas_Fretes.NR_Peso_Inicial <= " + NR_Peso_Tabela;
            sql += " AND Tabelas_Fretes.NR_Peso_Final >= " + NR_Peso_Tabela;
          }
          if (DM_Tem_Modal.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Modal = " + ed.getOID_Modal ();
          }
          if (DM_Tem_Vigencia.equals ("S")) {
            sql += " AND Tabelas_Fretes.DT_Vigencia <= '" + ed.getDT_Emissao () +
                "'";
          }
          if (DM_Tem_Validade.equals ("S")) {
            sql += " AND Tabelas_Fretes.DT_Validade >= '" + ed.getDT_Emissao () +
                "'";
          }
          if (DM_Tipo_Tabela_Frete.equals ("R")) {
            sql += " AND (Tabelas_Fretes.VL_R_Ate10 > 0 OR Tabelas_Fretes.VL_R_Acima200 > 0  OR Tabelas_Fretes.PE_AD_VALOREM > 0) ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("D")) {
            sql += " AND Tabelas_Fretes.VL_D_Ate1C > 0 ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("E")) {
            sql += " AND Tabelas_Fretes.VL_E_Ate1 > 0  ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("C")) {
            sql += " AND Tabelas_Fretes.VL_C_Ate25 > 0  ";
          }
          sql += " ORDER BY Tabelas_Fretes.DT_Vigencia ASC  ";

//            // System.out.println(" tabela CIDADE X CIDADE" + sql);

          rsTabela = this.executasql.executarConsulta (sql);

          while (rsTabela.next ()) {
            //// System.out.println(" tabela localizada") ;
            conED = this.carregaTabela (conED , rsTabela);
          }
        }

        if (conED.getDM_Localizado ().equals ("N")) {
          sql = " SELECT * FROM Tabelas_Fretes ";
          sql += " WHERE Tabelas_Fretes.OID_Pessoa = '" + OID_Pessoa + "'";
          if (DM_Percurso_Busca.equals ("Origem_Destino")) {
            sql += " AND Tabelas_Fretes.OID_Origem = " +
                ed.getOID_Cidade_Origem ();
            sql += " AND Tabelas_Fretes.OID_Destino = " +
                ed.getOID_Regiao_Destino ();
            sql += " AND Tabelas_Fretes.DM_Origem = 'C'";
            sql += " AND Tabelas_Fretes.DM_Destino = 'R'";

          }
          if (DM_Percurso_Busca.equals ("Destino_Origem")) {
            sql += " AND Tabelas_Fretes.OID_Destino = " +
                ed.getOID_Cidade_Origem ();
            sql += " AND Tabelas_Fretes.OID_Origem = " +
                ed.getOID_Regiao_Destino ();
            sql += " AND Tabelas_Fretes.DM_Origem = 'R'";
            sql += " AND Tabelas_Fretes.DM_Destino = 'C'";
          }
          if (DM_Tem_Empresa.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Empresa = " + ed.getOID_Empresa ();
          }

          if (DM_Tabela_Cliente_Entregador.equals ("E")) {
            sql += " AND Tabelas_Fretes.OID_Pessoa_Redespacho = '" +
                OID_Cliente + "'";
          }

          if (DM_Tem_Produto.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Produto = " + ed.getOID_Produto ();
          }
          if (DM_Tem_Peso.equals ("S")) {
            sql += " AND Tabelas_Fretes.NR_Peso_Inicial <= " + NR_Peso_Tabela;
            sql += " AND Tabelas_Fretes.NR_Peso_Final >= " + NR_Peso_Tabela;
          }
          if (DM_Tem_Modal.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Modal = " + ed.getOID_Modal ();
          }
          if (DM_Tem_Vigencia.equals ("S")) {
            sql += " AND Tabelas_Fretes.DT_Vigencia <= '" + ed.getDT_Emissao () +
                "'";
          }
          if (DM_Tem_Validade.equals ("S")) {
            sql += " AND Tabelas_Fretes.DT_Validade >= '" + ed.getDT_Emissao () +
                "'";
          }
          if (DM_Tipo_Tabela_Frete.equals ("R")) {
            sql += " AND (Tabelas_Fretes.VL_R_Ate10 > 0 OR Tabelas_Fretes.VL_R_Acima200 > 0  OR Tabelas_Fretes.PE_AD_VALOREM > 0) ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("D")) {
            sql += " AND Tabelas_Fretes.VL_D_Ate1C > 0 ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("E")) {
            sql += " AND Tabelas_Fretes.VL_E_Ate1 > 0  ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("C")) {
            sql += " AND Tabelas_Fretes.VL_C_Ate25 > 0  ";
          }
          sql += " ORDER BY Tabelas_Fretes.DT_Vigencia ASC  ";

          //// System.out.println(" tabela CIDADE X REGIAO" + sql);

          rsTabela = this.executasql.executarConsulta (sql);
          while (rsTabela.next ()) {
            conED = this.carregaTabela (conED , rsTabela);
          }
        }

        // System.out.println (" acha tabela 10 ");

        if (conED.getDM_Localizado ().equals ("N")) {
          sql = " SELECT * FROM Tabelas_Fretes ";
          sql += " WHERE Tabelas_Fretes.OID_Pessoa = '" + OID_Pessoa + "'";
          if (DM_Percurso_Busca.equals ("Origem_Destino")) {
            sql += " AND Tabelas_Fretes.OID_Origem = " +
                ed.getOID_Cidade_Origem ();
            sql += " AND Tabelas_Fretes.OID_Destino = " +
                ed.getOID_Subregiao_Destino ();
            sql += " AND Tabelas_Fretes.DM_Origem = 'C'";
            sql += " AND Tabelas_Fretes.DM_Destino = 'S'";

          }
          if (DM_Percurso_Busca.equals ("Destino_Origem")) {
            sql += " AND Tabelas_Fretes.OID_Destino = " +
                ed.getOID_Cidade_Origem ();
            sql += " AND Tabelas_Fretes.OID_Origem = " +
                ed.getOID_Subregiao_Destino ();
            sql += " AND Tabelas_Fretes.DM_Origem = 'S'";
            sql += " AND Tabelas_Fretes.DM_Destino = 'C'";

          }

          if (DM_Tem_Produto.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Produto = " + ed.getOID_Produto ();
          }
          if (DM_Tem_Empresa.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Empresa = " + ed.getOID_Empresa ();
          }
          if (DM_Tabela_Cliente_Entregador.equals ("E")) {
            sql += " AND Tabelas_Fretes.OID_Pessoa_Redespacho = '" +
                OID_Cliente + "'";
          }

          if (DM_Tem_Peso.equals ("S")) {
            sql += " AND Tabelas_Fretes.NR_Peso_Inicial <= " + NR_Peso_Tabela;
            sql += " AND Tabelas_Fretes.NR_Peso_Final >= " + NR_Peso_Tabela;
          }
          if (DM_Tem_Modal.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Modal = " + ed.getOID_Modal ();
          }
          if (DM_Tem_Vigencia.equals ("S")) {
            sql += " AND Tabelas_Fretes.DT_Vigencia <= '" + ed.getDT_Emissao () +
                "'";
          }
          if (DM_Tem_Validade.equals ("S")) {
            sql += " AND Tabelas_Fretes.DT_Validade >= '" + ed.getDT_Emissao () +
                "'";
          }
          if (DM_Tipo_Tabela_Frete.equals ("R")) {
            sql += " AND (Tabelas_Fretes.VL_R_Ate10 > 0 OR Tabelas_Fretes.VL_R_Acima200 > 0  OR Tabelas_Fretes.PE_AD_VALOREM > 0) ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("D")) {
            sql += " AND Tabelas_Fretes.VL_D_Ate1C > 0 ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("E")) {
            sql += " AND Tabelas_Fretes.VL_E_Ate1 > 0  ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("C")) {
            sql += " AND Tabelas_Fretes.VL_C_Ate25 > 0  ";
          }
          sql += " ORDER BY Tabelas_Fretes.DT_Vigencia ASC  ";

          //// System.out.println(" tabela CIDADE X POLO " + sql);


          rsTabela = this.executasql.executarConsulta (sql);
          while (rsTabela.next ()) {

            //// System.out.println(" CIDADE sub ok ");

            conED = this.carregaTabela (conED , rsTabela);
          }
        }

        // System.out.println (" acha tabela 20 ");

        if (conED.getDM_Localizado ().equals ("N")) {
          sql = " SELECT * FROM Tabelas_Fretes ";
          sql += " WHERE Tabelas_Fretes.OID_Pessoa = '" + OID_Pessoa + "'";
          if (DM_Percurso_Busca.equals ("Origem_Destino")) {
            sql += " AND Tabelas_Fretes.OID_Origem = " +
                ed.getOID_Cidade_Origem ();
            sql += " AND Tabelas_Fretes.OID_Destino = " +
                ed.getOID_Estado_Destino ();
            sql += " AND Tabelas_Fretes.DM_Origem = 'C'";
            sql += " AND Tabelas_Fretes.DM_Destino = 'E'";

          }
          if (DM_Percurso_Busca.equals ("Destino_Origem")) {
            sql += " AND Tabelas_Fretes.OID_Destino = " +
                ed.getOID_Cidade_Origem ();
            sql += " AND Tabelas_Fretes.OID_Origem = " +
                ed.getOID_Estado_Destino ();
            sql += " AND Tabelas_Fretes.DM_Origem = 'E'";
            sql += " AND Tabelas_Fretes.DM_Destino = 'C'";
          }

          if (DM_Tem_Produto.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Produto = " + ed.getOID_Produto ();
          }
          if (DM_Tem_Empresa.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Empresa = " + ed.getOID_Empresa ();
          }
          if (DM_Tabela_Cliente_Entregador.equals ("E")) {
            sql += " AND Tabelas_Fretes.OID_Pessoa_Redespacho = '" +
                OID_Cliente + "'";
          }

          if (DM_Tem_Peso.equals ("S")) {
            sql += " AND Tabelas_Fretes.NR_Peso_Inicial <= " + NR_Peso_Tabela;
            sql += " AND Tabelas_Fretes.NR_Peso_Final >= " + NR_Peso_Tabela;
          }
          if (DM_Tem_Modal.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Modal = " + ed.getOID_Modal ();
          }
          if (DM_Tem_Vigencia.equals ("S")) {
            sql += " AND Tabelas_Fretes.DT_Vigencia <= '" + ed.getDT_Emissao () +
                "'";
          }
          if (DM_Tem_Validade.equals ("S")) {
            sql += " AND Tabelas_Fretes.DT_Validade >= '" + ed.getDT_Emissao () +
                "'";
          }
          if (DM_Tipo_Tabela_Frete.equals ("R")) {
            sql += " AND (Tabelas_Fretes.VL_R_Ate10 > 0 OR Tabelas_Fretes.VL_R_Acima200 > 0  OR Tabelas_Fretes.PE_AD_VALOREM > 0) ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("D")) {
            sql += " AND Tabelas_Fretes.VL_D_Ate1C > 0 ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("E")) {
            sql += " AND Tabelas_Fretes.VL_E_Ate1 > 0  ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("C")) {
            sql += " AND Tabelas_Fretes.VL_C_Ate25 > 0  ";
          }
          sql += " ORDER BY Tabelas_Fretes.DT_Vigencia ASC  ";

          //// System.out.println(" tabela CIDADE X ESTADO " + sql);

          rsTabela = this.executasql.executarConsulta (sql);
          while (rsTabela.next ()) {
            conED = this.carregaTabela (conED , rsTabela);
          }
        }

        // System.out.println (" acha tabela 30 ");

        if (conED.getDM_Localizado ().equals ("N")) {
          sql = " SELECT * FROM Tabelas_Fretes ";
          sql += " WHERE Tabelas_Fretes.OID_Pessoa = '" + OID_Pessoa + "'";
          if (DM_Percurso_Busca.equals ("Origem_Destino")) {
            sql += " AND Tabelas_Fretes.OID_Origem = " +
                ed.getOID_Cidade_Origem ();
            sql += " AND Tabelas_Fretes.OID_Destino = 1 ";
          }
          if (DM_Percurso_Busca.equals ("Destino_Origem")) {
            sql += " AND Tabelas_Fretes.OID_Destino = " +
                ed.getOID_Cidade_Origem ();
            sql += " AND Tabelas_Fretes.OID_Origem =  1 ";
          }
          sql += " AND Tabelas_Fretes.DM_Origem = 'C'";
          sql += " AND Tabelas_Fretes.DM_Destino = 'P'";
          if (DM_Tem_Produto.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Produto = " + ed.getOID_Produto ();
          }
          if (DM_Tem_Empresa.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Empresa = " + ed.getOID_Empresa ();
          }
          if (DM_Tabela_Cliente_Entregador.equals ("E")) {
            sql += " AND Tabelas_Fretes.OID_Pessoa_Redespacho = '" +
                OID_Cliente + "'";
          }

          if (DM_Tem_Peso.equals ("S")) {
            sql += " AND Tabelas_Fretes.NR_Peso_Inicial <= " + NR_Peso_Tabela;
            sql += " AND Tabelas_Fretes.NR_Peso_Final >= " + NR_Peso_Tabela;
          }
          if (DM_Tem_Modal.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Modal = " + ed.getOID_Modal ();
          }
          if (DM_Tem_Vigencia.equals ("S")) {
            sql += " AND Tabelas_Fretes.DT_Vigencia <= '" + ed.getDT_Emissao () +
                "'";
          }
          if (DM_Tem_Validade.equals ("S")) {
            sql += " AND Tabelas_Fretes.DT_Validade >= '" + ed.getDT_Emissao () +
                "'";
          }
          if (DM_Tipo_Tabela_Frete.equals ("R")) {
            sql += " AND (Tabelas_Fretes.VL_R_Ate10 > 0 OR Tabelas_Fretes.VL_R_Acima200 > 0  OR Tabelas_Fretes.PE_AD_VALOREM > 0) ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("D")) {
            sql += " AND Tabelas_Fretes.VL_D_Ate1C > 0 ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("E")) {
            sql += " AND Tabelas_Fretes.VL_E_Ate1 > 0  ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("C")) {
            sql += " AND Tabelas_Fretes.VL_C_Ate25 > 0  ";
          }
          sql += " ORDER BY Tabelas_Fretes.DT_Vigencia ASC  ";
          // System.out.println (" tabela CIDADE X POLO " + sql);

          rsTabela = this.executasql.executarConsulta (sql);
          while (rsTabela.next ()) {
            conED = this.carregaTabela (conED , rsTabela);
          }
        }
      }

      // System.out.println (" acha tabela 40 ");

      //-------------------------REGIAO ESTADO--------------------------------------
      if (DM_Tem_Regiao_Estado.equals ("S")) {

        if (conED.getDM_Localizado ().equals ("N")) {
          sql = " SELECT * FROM Tabelas_Fretes ";
          sql += " WHERE Tabelas_Fretes.OID_Pessoa = '" + OID_Pessoa + "'";
          if (DM_Percurso_Busca.equals ("Origem_Destino")) {
            sql += " AND Tabelas_Fretes.OID_Origem = " +
                ed.getOID_Regiao_Origem ();
            sql += " AND Tabelas_Fretes.OID_Destino = " +
                ed.getOID_Cidade_Destino ();
            sql += " AND Tabelas_Fretes.DM_Origem = 'R'";
            sql += " AND Tabelas_Fretes.DM_Destino = 'C'";
          }
          if (DM_Percurso_Busca.equals ("Destino_Origem")) {
            sql += " AND Tabelas_Fretes.OID_Destino = " +
                ed.getOID_Regiao_Origem ();
            sql += " AND Tabelas_Fretes.OID_Origem = " +
                ed.getOID_Cidade_Destino ();
            sql += " AND Tabelas_Fretes.DM_Origem = 'C'";
            sql += " AND Tabelas_Fretes.DM_Destino = 'R'";
          }
          if (DM_Tem_Produto.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Produto = " + ed.getOID_Produto ();
          }
          if (DM_Tem_Empresa.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Empresa = " + ed.getOID_Empresa ();
          }
          if (DM_Tabela_Cliente_Entregador.equals ("E")) {
            sql += " AND Tabelas_Fretes.OID_Pessoa_Redespacho = '" +
                OID_Cliente + "'";
          }

          if (DM_Tem_Peso.equals ("S")) {
            sql += " AND Tabelas_Fretes.NR_Peso_Inicial <= " + NR_Peso_Tabela;
            sql += " AND Tabelas_Fretes.NR_Peso_Final >= " + NR_Peso_Tabela;
          }
          if (DM_Tem_Modal.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Modal = " + ed.getOID_Modal ();
          }
          if (DM_Tem_Vigencia.equals ("S")) {
            sql += " AND Tabelas_Fretes.DT_Vigencia <= '" + ed.getDT_Emissao () +
                "'";
          }
          if (DM_Tem_Validade.equals ("S")) {
            sql += " AND Tabelas_Fretes.DT_Validade >= '" + ed.getDT_Emissao () +
                "'";
          }
          if (DM_Tipo_Tabela_Frete.equals ("R")) {
            sql += " AND (Tabelas_Fretes.VL_R_Ate10 > 0 OR Tabelas_Fretes.VL_R_Acima200 > 0  OR Tabelas_Fretes.PE_AD_VALOREM > 0) ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("D")) {
            sql += " AND Tabelas_Fretes.VL_D_Ate1C > 0 ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("E")) {
            sql += " AND Tabelas_Fretes.VL_E_Ate1 > 0  ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("C")) {
            sql += " AND Tabelas_Fretes.VL_C_Ate25 > 0  ";
          }
          sql += " ORDER BY Tabelas_Fretes.DT_Vigencia ASC  ";
          //// System.out.println(" tabela REGIAO X CIDADE " + sql);

          rsTabela = this.executasql.executarConsulta (sql);
          while (rsTabela.next ()) {
            conED = this.carregaTabela (conED , rsTabela);
          }
        }

        if (conED.getDM_Localizado ().equals ("N")) {
          sql = " SELECT * FROM Tabelas_Fretes ";
          sql += " WHERE Tabelas_Fretes.OID_Pessoa = '" + OID_Pessoa + "'";
          if (DM_Percurso_Busca.equals ("Origem_Destino")) {
            sql += " AND Tabelas_Fretes.OID_Origem = " +
                ed.getOID_Regiao_Origem ();
            sql += " AND Tabelas_Fretes.OID_Destino = " +
                ed.getOID_Regiao_Destino ();
            sql += " AND Tabelas_Fretes.DM_Origem = 'R'";
            sql += " AND Tabelas_Fretes.DM_Destino = 'R'";
          }
          if (DM_Percurso_Busca.equals ("Destino_Origem")) {
            sql += " AND Tabelas_Fretes.OID_Destino = " +
                ed.getOID_Regiao_Origem ();
            sql += " AND Tabelas_Fretes.OID_Origem = " +
                ed.getOID_Regiao_Destino ();
            sql += " AND Tabelas_Fretes.DM_Origem = 'R'";
            sql += " AND Tabelas_Fretes.DM_Destino = 'R'";
          }
          if (DM_Tem_Produto.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Produto = " + ed.getOID_Produto ();
          }
          if (DM_Tem_Empresa.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Empresa = " + ed.getOID_Empresa ();
          }
          if (DM_Tabela_Cliente_Entregador.equals ("E")) {
            sql += " AND Tabelas_Fretes.OID_Pessoa_Redespacho = '" +
                OID_Cliente + "'";
          }

          if (DM_Tem_Peso.equals ("S")) {
            sql += " AND Tabelas_Fretes.NR_Peso_Inicial <= " + NR_Peso_Tabela;
            sql += " AND Tabelas_Fretes.NR_Peso_Final >= " + NR_Peso_Tabela;
          }
          if (DM_Tem_Modal.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Modal = " + ed.getOID_Modal ();
          }
          if (DM_Tem_Vigencia.equals ("S")) {
            sql += " AND Tabelas_Fretes.DT_Vigencia <= '" + ed.getDT_Emissao () +
                "'";
          }
          if (DM_Tem_Validade.equals ("S")) {
            sql += " AND Tabelas_Fretes.DT_Validade >= '" + ed.getDT_Emissao () +
                "'";
          }
          if (DM_Tipo_Tabela_Frete.equals ("R")) {
            sql += " AND (Tabelas_Fretes.VL_R_Ate10 > 0 OR Tabelas_Fretes.VL_R_Acima200 > 0  OR Tabelas_Fretes.PE_AD_VALOREM > 0) ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("D")) {
            sql += " AND Tabelas_Fretes.VL_D_Ate1C > 0 ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("E")) {
            sql += " AND Tabelas_Fretes.VL_E_Ate1 > 0  ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("C")) {
            sql += " AND Tabelas_Fretes.VL_C_Ate25 > 0  ";
          }
          sql += " ORDER BY Tabelas_Fretes.DT_Vigencia ASC  ";

          rsTabela = this.executasql.executarConsulta (sql);
          while (rsTabela.next ()) {
            conED = this.carregaTabela (conED , rsTabela);
          }
        }

        if (conED.getDM_Localizado ().equals ("N")) {
          sql = " SELECT * FROM Tabelas_Fretes ";
          sql += " WHERE Tabelas_Fretes.OID_Pessoa = '" + OID_Pessoa + "'";
          if (DM_Percurso_Busca.equals ("Origem_Destino")) {
            sql += " AND Tabelas_Fretes.OID_Origem = " +
                ed.getOID_Regiao_Origem ();
            sql += " AND Tabelas_Fretes.OID_Destino = " +
                ed.getOID_Subregiao_Destino ();
            sql += " AND Tabelas_Fretes.DM_Origem = 'R'";
            sql += " AND Tabelas_Fretes.DM_Destino = 'S'";
          }
          if (DM_Percurso_Busca.equals ("Destino_Origem")) {
            sql += " AND Tabelas_Fretes.OID_Destino = " +
                ed.getOID_Regiao_Origem ();
            sql += " AND Tabelas_Fretes.OID_Origem = " +
                ed.getOID_Subregiao_Destino ();
            sql += " AND Tabelas_Fretes.DM_Origem = 'S'";
            sql += " AND Tabelas_Fretes.DM_Destino = 'R'";
          }
          if (DM_Tem_Produto.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Produto = " + ed.getOID_Produto ();
          }
          if (DM_Tem_Empresa.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Empresa = " + ed.getOID_Empresa ();
          }
          if (DM_Tabela_Cliente_Entregador.equals ("E")) {
            sql += " AND Tabelas_Fretes.OID_Pessoa_Redespacho = '" +
                OID_Cliente + "'";
          }

          if (DM_Tem_Peso.equals ("S")) {
            sql += " AND Tabelas_Fretes.NR_Peso_Inicial <= " + NR_Peso_Tabela;
            sql += " AND Tabelas_Fretes.NR_Peso_Final >= " + NR_Peso_Tabela;
          }
          if (DM_Tem_Modal.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Modal = " + ed.getOID_Modal ();
          }
          if (DM_Tem_Vigencia.equals ("S")) {
            sql += " AND Tabelas_Fretes.DT_Vigencia <= '" + ed.getDT_Emissao () +
                "'";
          }
          if (DM_Tem_Validade.equals ("S")) {
            sql += " AND Tabelas_Fretes.DT_Validade >= '" + ed.getDT_Emissao () +
                "'";
          }
          if (DM_Tipo_Tabela_Frete.equals ("R")) {
            sql += " AND (Tabelas_Fretes.VL_R_Ate10 > 0 OR Tabelas_Fretes.VL_R_Acima200 > 0  OR Tabelas_Fretes.PE_AD_VALOREM > 0) ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("D")) {
            sql += " AND Tabelas_Fretes.VL_D_Ate1C > 0 ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("E")) {
            sql += " AND Tabelas_Fretes.VL_E_Ate1 > 0  ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("C")) {
            sql += " AND Tabelas_Fretes.VL_C_Ate25 > 0  ";
          }
          sql += " ORDER BY Tabelas_Fretes.DT_Vigencia ASC  ";
          //// System.out.println(" tabela REGIAO X POLO " + sql);

          rsTabela = this.executasql.executarConsulta (sql);
          while (rsTabela.next ()) {
            conED = this.carregaTabela (conED , rsTabela);
          }
        }

        if (conED.getDM_Localizado ().equals ("N")) {
          sql = " SELECT * FROM Tabelas_Fretes ";
          sql += " WHERE Tabelas_Fretes.OID_Pessoa = '" + OID_Pessoa + "'";
          if (DM_Percurso_Busca.equals ("Origem_Destino")) {
            sql += " AND Tabelas_Fretes.OID_Origem = " +
                ed.getOID_Regiao_Origem ();
            sql += " AND Tabelas_Fretes.OID_Destino = " +
                ed.getOID_Estado_Destino ();
            sql += " AND Tabelas_Fretes.DM_Origem = 'R'";
            sql += " AND Tabelas_Fretes.DM_Destino = 'E'";
          }
          if (DM_Percurso_Busca.equals ("Destino_Origem")) {
            sql += " AND Tabelas_Fretes.OID_Destino = " +
                ed.getOID_Regiao_Origem ();
            sql += " AND Tabelas_Fretes.OID_Origem = " +
                ed.getOID_Estado_Destino ();
            sql += " AND Tabelas_Fretes.DM_Origem = 'E'";
            sql += " AND Tabelas_Fretes.DM_Destino = 'R'";
          }
          if (DM_Tem_Produto.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Produto = " + ed.getOID_Produto ();
          }
          if (DM_Tem_Empresa.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Empresa = " + ed.getOID_Empresa ();
          }
          if (DM_Tabela_Cliente_Entregador.equals ("E")) {
            sql += " AND Tabelas_Fretes.OID_Pessoa_Redespacho = '" +
                OID_Cliente + "'";
          }

          if (DM_Tem_Peso.equals ("S")) {
            sql += " AND Tabelas_Fretes.NR_Peso_Inicial <= " + NR_Peso_Tabela;
            sql += " AND Tabelas_Fretes.NR_Peso_Final >= " + NR_Peso_Tabela;
          }
          if (DM_Tem_Modal.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Modal = " + ed.getOID_Modal ();
          }
          if (DM_Tem_Vigencia.equals ("S")) {
            sql += " AND Tabelas_Fretes.DT_Vigencia <= '" + ed.getDT_Emissao () +
                "'";
          }
          if (DM_Tem_Validade.equals ("S")) {
            sql += " AND Tabelas_Fretes.DT_Validade >= '" + ed.getDT_Emissao () +
                "'";
          }
          if (DM_Tipo_Tabela_Frete.equals ("R")) {
            sql += " AND (Tabelas_Fretes.VL_R_Ate10 > 0 OR Tabelas_Fretes.VL_R_Acima200 > 0  OR Tabelas_Fretes.PE_AD_VALOREM > 0) ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("D")) {
            sql += " AND Tabelas_Fretes.VL_D_Ate1C > 0 ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("E")) {
            sql += " AND Tabelas_Fretes.VL_E_Ate1 > 0  ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("C")) {
            sql += " AND Tabelas_Fretes.VL_C_Ate25 > 0  ";
          }
          sql += " ORDER BY Tabelas_Fretes.DT_Vigencia ASC  ";

          //// System.out.println(" tabela REGIAO X ESTADO " + sql);

          rsTabela = this.executasql.executarConsulta (sql);
          while (rsTabela.next ()) {
            conED = this.carregaTabela (conED , rsTabela);
          }
        }
      }

      // System.out.println (" acha tabela 60 ");

      //-------------------------SUBREGIAO------------------------------------------
      if (DM_Tem_Subregiao.equals ("S")) {

        if (conED.getDM_Localizado ().equals ("N")) {
          sql = " SELECT * FROM Tabelas_Fretes ";
          sql += " WHERE Tabelas_Fretes.OID_Pessoa = '" + OID_Pessoa + "'";
          if (DM_Percurso_Busca.equals ("Origem_Destino")) {
            sql += " AND Tabelas_Fretes.OID_Origem = " +
                ed.getOID_Subregiao_Origem ();
            sql += " AND Tabelas_Fretes.OID_Destino = " +
                ed.getOID_Cidade_Destino ();
            sql += " AND Tabelas_Fretes.DM_Origem = 'S'";
            sql += " AND Tabelas_Fretes.DM_Destino = 'C'";

          }
          if (DM_Percurso_Busca.equals ("Destino_Origem")) {
            sql += " AND Tabelas_Fretes.OID_Destino = " +
                ed.getOID_Subregiao_Origem ();
            sql += " AND Tabelas_Fretes.OID_Origem = " +
                ed.getOID_Cidade_Destino ();
            sql += " AND Tabelas_Fretes.DM_Origem = 'C'";
            sql += " AND Tabelas_Fretes.DM_Destino = 'S'";

          }

          if (DM_Tem_Produto.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Produto = " + ed.getOID_Produto ();
          }
          if (DM_Tem_Empresa.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Empresa = " + ed.getOID_Empresa ();
          }
          if (DM_Tabela_Cliente_Entregador.equals ("E")) {
            sql += " AND Tabelas_Fretes.OID_Pessoa_Redespacho = '" +
                OID_Cliente + "'";
          }

          if (DM_Tem_Peso.equals ("S")) {
            sql += " AND Tabelas_Fretes.NR_Peso_Inicial <= " + NR_Peso_Tabela;
            sql += " AND Tabelas_Fretes.NR_Peso_Final >= " + NR_Peso_Tabela;
          }
          if (DM_Tem_Modal.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Modal = " + ed.getOID_Modal ();
          }
          if (DM_Tem_Vigencia.equals ("S")) {
            sql += " AND Tabelas_Fretes.DT_Vigencia <= '" + ed.getDT_Emissao () +
                "'";
          }
          if (DM_Tem_Validade.equals ("S")) {
            sql += " AND Tabelas_Fretes.DT_Validade >= '" + ed.getDT_Emissao () +
                "'";
          }
          if (DM_Tipo_Tabela_Frete.equals ("R")) {
            sql += " AND (Tabelas_Fretes.VL_R_Ate10 > 0 OR Tabelas_Fretes.VL_R_Acima200 > 0  OR Tabelas_Fretes.PE_AD_VALOREM > 0) ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("D")) {
            sql += " AND Tabelas_Fretes.VL_D_Ate1C > 0 ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("E")) {
            sql += " AND Tabelas_Fretes.VL_E_Ate1 > 0  ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("C")) {
            sql += " AND Tabelas_Fretes.VL_C_Ate25 > 0  ";
          }
          sql += " ORDER BY Tabelas_Fretes.DT_Vigencia ASC  ";

          //// System.out.println(" tabela POLO X CIDADE " + sql);

          rsTabela = this.executasql.executarConsulta (sql);
          while (rsTabela.next ()) {
            //// System.out.println(" ok SUB CIDADE");

            conED = this.carregaTabela (conED , rsTabela);
          }
        }

        if (conED.getDM_Localizado ().equals ("N")) {
          sql = " SELECT * FROM Tabelas_Fretes ";
          sql += " WHERE Tabelas_Fretes.OID_Pessoa = '" + OID_Pessoa + "'";
          if (DM_Percurso_Busca.equals ("Origem_Destino")) {
            sql += " AND Tabelas_Fretes.OID_Origem = " +
                ed.getOID_Subregiao_Origem ();
            sql += " AND Tabelas_Fretes.OID_Destino = " +
                ed.getOID_Subregiao_Destino ();
            sql += " AND Tabelas_Fretes.DM_Origem = 'S'";
            sql += " AND Tabelas_Fretes.DM_Destino = 'S'";
          }
          if (DM_Percurso_Busca.equals ("Destino_Origem")) {
            sql += " AND Tabelas_Fretes.OID_Destino = " +
                ed.getOID_Subregiao_Origem ();
            sql += " AND Tabelas_Fretes.OID_Origem = " +
                ed.getOID_Subregiao_Destino ();
            sql += " AND Tabelas_Fretes.DM_Origem = 'S'";
            sql += " AND Tabelas_Fretes.DM_Destino = 'S'";
          }

          if (DM_Tem_Produto.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Produto = " + ed.getOID_Produto ();
          }
          if (DM_Tem_Empresa.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Empresa = " + ed.getOID_Empresa ();
          }
          if (DM_Tabela_Cliente_Entregador.equals ("E")) {
            sql += " AND Tabelas_Fretes.OID_Pessoa_Redespacho = '" +
                OID_Cliente + "'";
          }

          if (DM_Tem_Peso.equals ("S")) {
            sql += " AND Tabelas_Fretes.NR_Peso_Inicial <= " + NR_Peso_Tabela;
            sql += " AND Tabelas_Fretes.NR_Peso_Final >= " + NR_Peso_Tabela;
          }
          if (DM_Tem_Modal.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Modal = " + ed.getOID_Modal ();
          }
          if (DM_Tem_Vigencia.equals ("S")) {
            sql += " AND Tabelas_Fretes.DT_Vigencia <= '" + ed.getDT_Emissao () +
                "'";
          }
          if (DM_Tem_Validade.equals ("S")) {
            sql += " AND Tabelas_Fretes.DT_Validade >= '" + ed.getDT_Emissao () +
                "'";
          }
          if (DM_Tipo_Tabela_Frete.equals ("R")) {
            sql += " AND (Tabelas_Fretes.VL_R_Ate10 > 0 OR Tabelas_Fretes.VL_R_Acima200 > 0  OR Tabelas_Fretes.PE_AD_VALOREM > 0) ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("D")) {
            sql += " AND Tabelas_Fretes.VL_D_Ate1C > 0 ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("E")) {
            sql += " AND Tabelas_Fretes.VL_E_Ate1 > 0  ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("C")) {
            sql += " AND Tabelas_Fretes.VL_C_Ate25 > 0  ";
          }

          sql += " ORDER BY Tabelas_Fretes.DT_Vigencia ASC  ";
          //// System.out.println(" tabela POLO X POLO " + sql);

          rsTabela = this.executasql.executarConsulta (sql);
          while (rsTabela.next ()) {
            conED = this.carregaTabela (conED , rsTabela);
          }
        }

        if (conED.getDM_Localizado ().equals ("N")) {
          sql = " SELECT * FROM Tabelas_Fretes ";
          sql += " WHERE Tabelas_Fretes.OID_Pessoa = '" + OID_Pessoa + "'";
          if (DM_Percurso_Busca.equals ("Origem_Destino")) {
            sql += " AND Tabelas_Fretes.OID_Origem = " +
                ed.getOID_Subregiao_Origem ();
            sql += " AND Tabelas_Fretes.OID_Destino = " +
                ed.getOID_Estado_Destino ();
            sql += " AND Tabelas_Fretes.DM_Origem = 'S'";
            sql += " AND Tabelas_Fretes.DM_Destino = 'E'";
          }
          if (DM_Percurso_Busca.equals ("Destino_Origem")) {
            sql += " AND Tabelas_Fretes.OID_Destino = " +
                ed.getOID_Subregiao_Origem ();
            sql += " AND Tabelas_Fretes.OID_Origem = " +
                ed.getOID_Estado_Destino ();
            sql += " AND Tabelas_Fretes.DM_Origem = 'E'";
            sql += " AND Tabelas_Fretes.DM_Destino = 'S'";
          }

          if (DM_Tem_Produto.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Produto = " + ed.getOID_Produto ();
          }
          if (DM_Tem_Empresa.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Empresa = " + ed.getOID_Empresa ();
          }
          if (DM_Tabela_Cliente_Entregador.equals ("E")) {
            sql += " AND Tabelas_Fretes.OID_Pessoa_Redespacho = '" +
                OID_Cliente + "'";
          }

          if (DM_Tem_Peso.equals ("S")) {
            sql += " AND Tabelas_Fretes.NR_Peso_Inicial <= " + NR_Peso_Tabela;
            sql += " AND Tabelas_Fretes.NR_Peso_Final >= " + NR_Peso_Tabela;
          }
          if (DM_Tem_Modal.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Modal = " + ed.getOID_Modal ();
          }
          if (DM_Tem_Vigencia.equals ("S")) {
            sql += " AND Tabelas_Fretes.DT_Vigencia <= '" + ed.getDT_Emissao () +
                "'";
          }
          if (DM_Tem_Validade.equals ("S")) {
            sql += " AND Tabelas_Fretes.DT_Validade >= '" + ed.getDT_Emissao () +
                "'";
          }
          if (DM_Tipo_Tabela_Frete.equals ("R")) {
            sql += " AND (Tabelas_Fretes.VL_R_Ate10 > 0 OR Tabelas_Fretes.VL_R_Acima200 > 0  OR Tabelas_Fretes.PE_AD_VALOREM > 0) ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("D")) {
            sql += " AND Tabelas_Fretes.VL_D_Ate1C > 0 ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("E")) {
            sql += " AND Tabelas_Fretes.VL_E_Ate1 > 0  ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("C")) {
            sql += " AND Tabelas_Fretes.VL_C_Ate25 > 0  ";
          }
          sql += " ORDER BY Tabelas_Fretes.DT_Vigencia ASC  ";

          //// System.out.println(" tabela POLO X ESTADO " + sql);

          rsTabela = this.executasql.executarConsulta (sql);
          while (rsTabela.next ()) {
            conED = this.carregaTabela (conED , rsTabela);
          }
        }

        // System.out.println (" acha tabela 80 ");

        if (conED.getDM_Localizado ().equals ("N")) {
          sql = " SELECT * FROM Tabelas_Fretes ";
          sql += " WHERE Tabelas_Fretes.OID_Pessoa = '" + OID_Pessoa + "'";
          if (DM_Percurso_Busca.equals ("Origem_Destino")) {
            sql += " AND Tabelas_Fretes.OID_Origem = " +
                ed.getOID_Cidade_Origem ();
            sql += " AND Tabelas_Fretes.OID_Destino = 1 ";
            sql += " AND Tabelas_Fretes.DM_Origem = 'S'";
            sql += " AND Tabelas_Fretes.DM_Destino = 'P'";
          }
          if (DM_Percurso_Busca.equals ("Destino_Origem")) {
            sql += " AND Tabelas_Fretes.OID_Destino = 1 ";
            sql += " AND Tabelas_Fretes.OID_Origem = " +
                ed.getOID_Cidade_Origem ();
            sql += " AND Tabelas_Fretes.DM_Origem = 'P'";
            sql += " AND Tabelas_Fretes.DM_Destino = 'S'";
          }

          if (DM_Tem_Produto.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Produto = " + ed.getOID_Produto ();
          }
          if (DM_Tem_Empresa.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Empresa = " + ed.getOID_Empresa ();
          }
          if (DM_Tabela_Cliente_Entregador.equals ("E")) {
            sql += " AND Tabelas_Fretes.OID_Pessoa_Redespacho = '" +
                OID_Cliente + "'";
          }

          if (DM_Tem_Peso.equals ("S")) {
            sql += " AND Tabelas_Fretes.NR_Peso_Inicial <= " + NR_Peso_Tabela;
            sql += " AND Tabelas_Fretes.NR_Peso_Final >= " + NR_Peso_Tabela;
          }
          if (DM_Tem_Modal.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Modal = " + ed.getOID_Modal ();
          }
          if (DM_Tem_Vigencia.equals ("S")) {
            sql += " AND Tabelas_Fretes.DT_Vigencia <= '" + ed.getDT_Emissao () +
                "'";
          }
          if (DM_Tem_Validade.equals ("S")) {
            sql += " AND Tabelas_Fretes.DT_Validade >= '" + ed.getDT_Emissao () +
                "'";
          }
          if (DM_Tipo_Tabela_Frete.equals ("R")) {
            sql += " AND (Tabelas_Fretes.VL_R_Ate10 > 0 OR Tabelas_Fretes.VL_R_Acima200 > 0  OR Tabelas_Fretes.PE_AD_VALOREM > 0) ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("D")) {
            sql += " AND Tabelas_Fretes.VL_D_Ate1C > 0 ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("E")) {
            sql += " AND Tabelas_Fretes.VL_E_Ate1 > 0  ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("C")) {
            sql += " AND Tabelas_Fretes.VL_C_Ate25 > 0  ";
          }
          sql += " ORDER BY Tabelas_Fretes.DT_Vigencia ASC  ";

          //// System.out.println(" tabela POLO X PAIS " + sql);

          rsTabela = this.executasql.executarConsulta (sql);
          while (rsTabela.next ()) {
            conED = this.carregaTabela (conED , rsTabela);
          }
        }

      }

      // System.out.println (" acha tabela 90 ");

      //-------------------------ESTADO---------------------------------------------
      if (DM_Tem_Estado.equals ("S")) {

        if (conED.getDM_Localizado ().equals ("N")) {
          sql = " SELECT * FROM Tabelas_Fretes ";
          sql += " WHERE Tabelas_Fretes.OID_Pessoa = '" + OID_Pessoa + "'";
          if (DM_Percurso_Busca.equals ("Origem_Destino")) {
            sql += " AND Tabelas_Fretes.OID_Origem = " +
                ed.getOID_Estado_Origem ();
            sql += " AND Tabelas_Fretes.OID_Destino = " +
                ed.getOID_Cidade_Destino ();
            sql += " AND Tabelas_Fretes.DM_Origem = 'E'";
            sql += " AND Tabelas_Fretes.DM_Destino = 'C'";
          }
          if (DM_Percurso_Busca.equals ("Destino_Origem")) {
            sql += " AND Tabelas_Fretes.OID_Destino = " +
                ed.getOID_Estado_Origem ();
            sql += " AND Tabelas_Fretes.OID_Origem = " +
                ed.getOID_Cidade_Destino ();
            sql += " AND Tabelas_Fretes.DM_Origem = 'C'";
            sql += " AND Tabelas_Fretes.DM_Destino = 'E'";
          }
          if (DM_Tem_Produto.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Produto = " + ed.getOID_Produto ();
          }
          if (DM_Tem_Empresa.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Empresa = " + ed.getOID_Empresa ();
          }
          if (DM_Tabela_Cliente_Entregador.equals ("E")) {
            sql += " AND Tabelas_Fretes.OID_Pessoa_Redespacho = '" +
                OID_Cliente + "'";
          }

          if (DM_Tem_Peso.equals ("S")) {
            sql += " AND Tabelas_Fretes.NR_Peso_Inicial <= " + NR_Peso_Tabela;
            sql += " AND Tabelas_Fretes.NR_Peso_Final >= " + NR_Peso_Tabela;
          }
          if (DM_Tem_Modal.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Modal = " + ed.getOID_Modal ();
          }
          if (DM_Tem_Vigencia.equals ("S")) {
            sql += " AND Tabelas_Fretes.DT_Vigencia <= '" + ed.getDT_Emissao () +
                "'";
          }
          if (DM_Tem_Validade.equals ("S")) {
            sql += " AND Tabelas_Fretes.DT_Validade >= '" + ed.getDT_Emissao () +
                "'";
          }
          if (DM_Tipo_Tabela_Frete.equals ("R")) {
            sql += " AND (Tabelas_Fretes.VL_R_Ate10 > 0 OR Tabelas_Fretes.VL_R_Acima200 > 0  OR Tabelas_Fretes.PE_AD_VALOREM > 0) ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("D")) {
            sql += " AND Tabelas_Fretes.VL_D_Ate1C > 0 ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("E")) {
            sql += " AND Tabelas_Fretes.VL_E_Ate1 > 0  ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("C")) {
            sql += " AND Tabelas_Fretes.VL_C_Ate25 > 0  ";
          }
          sql += " ORDER BY Tabelas_Fretes.DT_Vigencia ASC  ";

          //// System.out.println(" tabela ESTADO X CIDADE " + sql);

          rsTabela = this.executasql.executarConsulta (sql);
          while (rsTabela.next ()) {
            conED = this.carregaTabela (conED , rsTabela);
          }
        }

        if (conED.getDM_Localizado ().equals ("N")) {
          sql = " SELECT * FROM Tabelas_Fretes ";
          sql += " WHERE Tabelas_Fretes.OID_Pessoa = '" + OID_Pessoa + "'";
          if (DM_Percurso_Busca.equals ("Origem_Destino")) {
            sql += " AND Tabelas_Fretes.OID_Origem = " +
                ed.getOID_Estado_Origem ();
            sql += " AND Tabelas_Fretes.OID_Destino = " +
                ed.getOID_Regiao_Destino ();
            sql += " AND Tabelas_Fretes.DM_Origem = 'E'";
            sql += " AND Tabelas_Fretes.DM_Destino = 'R'";
          }
          if (DM_Percurso_Busca.equals ("Destino_Origem")) {
            sql += " AND Tabelas_Fretes.OID_Destino = " +
                ed.getOID_Estado_Origem ();
            sql += " AND Tabelas_Fretes.OID_Origem = " +
                ed.getOID_Regiao_Destino ();
            sql += " AND Tabelas_Fretes.DM_Origem = 'R'";
            sql += " AND Tabelas_Fretes.DM_Destino = 'E'";
          }

          if (DM_Tem_Produto.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Produto = " + ed.getOID_Produto ();
          }
          if (DM_Tem_Empresa.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Empresa = " + ed.getOID_Empresa ();
          }
          if (DM_Tabela_Cliente_Entregador.equals ("E")) {
            sql += " AND Tabelas_Fretes.OID_Pessoa_Redespacho = '" +
                OID_Cliente + "'";
          }

          if (DM_Tem_Peso.equals ("S")) {
            sql += " AND Tabelas_Fretes.NR_Peso_Inicial <= " + NR_Peso_Tabela;
            sql += " AND Tabelas_Fretes.NR_Peso_Final >= " + NR_Peso_Tabela;
          }
          if (DM_Tem_Modal.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Modal = " + ed.getOID_Modal ();
          }
          if (DM_Tem_Vigencia.equals ("S")) {
            sql += " AND Tabelas_Fretes.DT_Vigencia <= '" + ed.getDT_Emissao () +
                "'";
          }
          if (DM_Tem_Validade.equals ("S")) {
            sql += " AND Tabelas_Fretes.DT_Validade >= '" + ed.getDT_Emissao () +
                "'";
          }
          if (DM_Tipo_Tabela_Frete.equals ("R")) {
            sql += " AND (Tabelas_Fretes.VL_R_Ate10 > 0 OR Tabelas_Fretes.VL_R_Acima200 > 0  OR Tabelas_Fretes.PE_AD_VALOREM > 0) ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("D")) {
            sql += " AND Tabelas_Fretes.VL_D_Ate1C > 0 ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("E")) {
            sql += " AND Tabelas_Fretes.VL_E_Ate1 > 0  ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("C")) {
            sql += " AND Tabelas_Fretes.VL_C_Ate25 > 0  ";
          }
          sql += " ORDER BY Tabelas_Fretes.DT_Vigencia ASC  ";

          //// System.out.println(" tabela ESTADO X REGIAO " + sql);

          rsTabela = this.executasql.executarConsulta (sql);
          while (rsTabela.next ()) {
            conED = this.carregaTabela (conED , rsTabela);
          }
        }

        if (conED.getDM_Localizado ().equals ("N")) {
          sql = " SELECT * FROM Tabelas_Fretes ";
          sql += " WHERE Tabelas_Fretes.OID_Pessoa = '" + OID_Pessoa + "'";
          if (DM_Percurso_Busca.equals ("Origem_Destino")) {
            sql += " AND Tabelas_Fretes.OID_Origem = " +
                ed.getOID_Estado_Origem ();
            sql += " AND Tabelas_Fretes.OID_Destino = " +
                ed.getOID_Subregiao_Destino ();
            sql += " AND Tabelas_Fretes.DM_Origem = 'E'";
            sql += " AND Tabelas_Fretes.DM_Destino = 'S'";
          }
          if (DM_Percurso_Busca.equals ("Destino_Origem")) {
            sql += " AND Tabelas_Fretes.OID_Destino = " +
                ed.getOID_Estado_Origem ();
            sql += " AND Tabelas_Fretes.OID_Origem = " +
                ed.getOID_Subregiao_Destino ();
            sql += " AND Tabelas_Fretes.DM_Origem = 'S'";
            sql += " AND Tabelas_Fretes.DM_Destino = 'E'";

          }

          if (DM_Tem_Produto.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Produto = " + ed.getOID_Produto ();
          }
          if (DM_Tem_Empresa.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Empresa = " + ed.getOID_Empresa ();
          }
          if (DM_Tabela_Cliente_Entregador.equals ("E")) {
            sql += " AND Tabelas_Fretes.OID_Pessoa_Redespacho = '" +
                OID_Cliente + "'";
          }

          if (DM_Tem_Peso.equals ("S")) {
            sql += " AND Tabelas_Fretes.NR_Peso_Inicial <= " + NR_Peso_Tabela;
            sql += " AND Tabelas_Fretes.NR_Peso_Final >= " + NR_Peso_Tabela;
          }
          if (DM_Tem_Modal.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Modal = " + ed.getOID_Modal ();
          }
          if (DM_Tem_Vigencia.equals ("S")) {
            sql += " AND Tabelas_Fretes.DT_Vigencia <= '" + ed.getDT_Emissao () +
                "'";
          }
          if (DM_Tem_Validade.equals ("S")) {
            sql += " AND Tabelas_Fretes.DT_Validade >= '" + ed.getDT_Emissao () +
                "'";
          }
          if (DM_Tipo_Tabela_Frete.equals ("R")) {
            sql += " AND (Tabelas_Fretes.VL_R_Ate10 > 0 OR Tabelas_Fretes.VL_R_Acima200 > 0  OR Tabelas_Fretes.PE_AD_VALOREM > 0) ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("D")) {
            sql += " AND Tabelas_Fretes.VL_D_Ate1C > 0 ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("E")) {
            sql += " AND Tabelas_Fretes.VL_E_Ate1 > 0  ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("C")) {
            sql += " AND Tabelas_Fretes.VL_C_Ate25 > 0  ";
          }
          sql += " ORDER BY Tabelas_Fretes.DT_Vigencia ASC  ";

          //// System.out.println(" tabela ESTADO X POLO " + sql);

          rsTabela = this.executasql.executarConsulta (sql);
          while (rsTabela.next ()) {
            conED = this.carregaTabela (conED , rsTabela);
          }
        }

        if (conED.getDM_Localizado ().equals ("N")) {
          sql = " SELECT * FROM Tabelas_Fretes ";
          sql += " WHERE Tabelas_Fretes.OID_Pessoa = '" + OID_Pessoa + "'";
          if (DM_Percurso_Busca.equals ("Origem_Destino")) {
            sql += " AND Tabelas_Fretes.OID_Origem = " +
                ed.getOID_Estado_Origem ();
            sql += " AND Tabelas_Fretes.OID_Destino = " +
                ed.getOID_Estado_Destino ();
            sql += " AND Tabelas_Fretes.DM_Origem = 'E'";
            sql += " AND Tabelas_Fretes.DM_Destino = 'E'";
          }
          if (DM_Percurso_Busca.equals ("Destino_Origem")) {
            sql += " AND Tabelas_Fretes.OID_Destino = " +
                ed.getOID_Estado_Origem ();
            sql += " AND Tabelas_Fretes.OID_Origem = " +
                ed.getOID_Estado_Destino ();
            sql += " AND Tabelas_Fretes.DM_Origem = 'E'";
            sql += " AND Tabelas_Fretes.DM_Destino = 'E'";
          }
          if (DM_Tem_Produto.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Produto = " + ed.getOID_Produto ();
          }
          if (DM_Tem_Empresa.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Empresa = " + ed.getOID_Empresa ();
          }
          if (DM_Tabela_Cliente_Entregador.equals ("E")) {
            sql += " AND Tabelas_Fretes.OID_Pessoa_Redespacho = '" +
                OID_Cliente + "'";
          }

          if (DM_Tem_Peso.equals ("S")) {
            sql += " AND Tabelas_Fretes.NR_Peso_Inicial <= " + NR_Peso_Tabela;
            sql += " AND Tabelas_Fretes.NR_Peso_Final >= " + NR_Peso_Tabela;
          }
          if (DM_Tem_Modal.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Modal = " + ed.getOID_Modal ();
          }
          if (DM_Tem_Vigencia.equals ("S")) {
            sql += " AND Tabelas_Fretes.DT_Vigencia <= '" + ed.getDT_Emissao () +
                "'";
          }
          if (DM_Tem_Validade.equals ("S")) {
            sql += " AND Tabelas_Fretes.DT_Validade >= '" + ed.getDT_Emissao () +
                "'";
          }
          if (DM_Tipo_Tabela_Frete.equals ("R")) {
            sql += " AND (Tabelas_Fretes.VL_R_Ate10 > 0 OR Tabelas_Fretes.VL_R_Acima200 > 0  OR Tabelas_Fretes.PE_AD_VALOREM > 0) ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("D")) {
            sql += " AND Tabelas_Fretes.VL_D_Ate1C > 0 ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("E")) {
            sql += " AND Tabelas_Fretes.VL_E_Ate1 > 0  ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("C")) {
            sql += " AND Tabelas_Fretes.VL_C_Ate25 > 0  ";
          }
          sql += " ORDER BY Tabelas_Fretes.DT_Vigencia ASC  ";

          //// System.out.println(" tabela ESTADO X ESTADO " + sql);

          rsTabela = this.executasql.executarConsulta (sql);
          while (rsTabela.next ()) {
            conED = this.carregaTabela (conED , rsTabela);
          }
        }
      }

      // System.out.println (" acha tabela 100 ");

      //-------------------------PAIS-----------------------------------------------
      if (DM_Tem_Pais.equals ("S")) {

        if (conED.getDM_Localizado ().equals ("N")) {
          sql = " SELECT * FROM Tabelas_Fretes ";
          sql += " WHERE Tabelas_Fretes.OID_Pessoa = '" + OID_Pessoa + "'";
          if (DM_Percurso_Busca.equals ("Origem_Destino")) {
            sql += " AND Tabelas_Fretes.OID_Origem = 1 ";
            sql += " AND Tabelas_Fretes.OID_Destino = " +
                ed.getOID_Cidade_Destino ();
          }
          if (DM_Percurso_Busca.equals ("Destino_Origem")) {
            sql += " AND Tabelas_Fretes.OID_Destino = " +
                ed.getOID_Cidade_Destino ();
            sql += " AND Tabelas_Fretes.OID_Origem = 1 ";
          }

          sql += " AND Tabelas_Fretes.DM_Origem = 'P'";
          sql += " AND Tabelas_Fretes.DM_Destino = 'C'";
          if (DM_Tem_Produto.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Produto = " + ed.getOID_Produto ();
          }
          if (DM_Tem_Empresa.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Empresa = " + ed.getOID_Empresa ();
          }
          if (DM_Tabela_Cliente_Entregador.equals ("E")) {
            sql += " AND Tabelas_Fretes.OID_Pessoa_Redespacho = '" +
                OID_Cliente + "'";
          }

          if (DM_Tem_Peso.equals ("S")) {
            sql += " AND Tabelas_Fretes.NR_Peso_Inicial <= " + NR_Peso_Tabela;
            sql += " AND Tabelas_Fretes.NR_Peso_Final >= " + NR_Peso_Tabela;
          }
          if (DM_Tem_Modal.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Modal = " + ed.getOID_Modal ();
          }
          if (DM_Tem_Vigencia.equals ("S")) {
            sql += " AND Tabelas_Fretes.DT_Vigencia <= '" + ed.getDT_Emissao () +
                "'";
          }
          if (DM_Tem_Validade.equals ("S")) {
            sql += " AND Tabelas_Fretes.DT_Validade >= '" + ed.getDT_Emissao () +
                "'";
          }
          if (DM_Tipo_Tabela_Frete.equals ("R")) {
            sql += " AND (Tabelas_Fretes.VL_R_Ate10 > 0 OR Tabelas_Fretes.VL_R_Acima200 > 0  OR Tabelas_Fretes.PE_AD_VALOREM > 0) ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("D")) {
            sql += " AND Tabelas_Fretes.VL_D_Ate1C > 0 ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("E")) {
            sql += " AND Tabelas_Fretes.VL_E_Ate1 > 0  ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("C")) {
            sql += " AND Tabelas_Fretes.VL_C_Ate25 > 0  ";
          }
          sql += " ORDER BY Tabelas_Fretes.DT_Vigencia ASC  ";

          //// System.out.println(" tabela PAIS X CIDADE " + sql);

          rsTabela = this.executasql.executarConsulta (sql);
          while (rsTabela.next ()) {
            conED = this.carregaTabela (conED , rsTabela);
          }
        }

        if (conED.getDM_Localizado ().equals ("N")) {

          // System.out.println (" acha tabela 120 ");
          sql = " SELECT * FROM Tabelas_Fretes ";
          sql += " WHERE Tabelas_Fretes.OID_Pessoa = '" + OID_Pessoa + "'";
          if (DM_Percurso_Busca.equals ("Origem_Destino")) {
            sql += " AND Tabelas_Fretes.OID_Origem = 1 ";
            sql += " AND Tabelas_Fretes.OID_Destino = " +
                ed.getOID_Regiao_Destino ();
          }
          if (DM_Percurso_Busca.equals ("Destino_Origem")) {
            sql += " AND Tabelas_Fretes.OID_Destino = " +
                ed.getOID_Regiao_Destino ();
            sql += " AND Tabelas_Fretes.OID_Origem = 1 ";
          }
          sql += " AND Tabelas_Fretes.DM_Origem = 'P'";
          sql += " AND Tabelas_Fretes.DM_Destino = 'R'";
          if (DM_Tem_Produto.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Produto = " + ed.getOID_Produto ();
          }
          if (DM_Tem_Empresa.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Empresa = " + ed.getOID_Empresa ();
          }
          if (DM_Tabela_Cliente_Entregador.equals ("E")) {
            sql += " AND Tabelas_Fretes.OID_Pessoa_Redespacho = '" +
                OID_Cliente + "'";
          }

          if (DM_Tem_Peso.equals ("S")) {
            sql += " AND Tabelas_Fretes.NR_Peso_Inicial <= " + NR_Peso_Tabela;
            sql += " AND Tabelas_Fretes.NR_Peso_Final >= " + NR_Peso_Tabela;
          }
          if (DM_Tem_Modal.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Modal = " + ed.getOID_Modal ();
          }
          if (DM_Tem_Vigencia.equals ("S")) {
            sql += " AND Tabelas_Fretes.DT_Vigencia <= '" + ed.getDT_Emissao () +
                "'";
          }
          if (DM_Tem_Validade.equals ("S")) {
            sql += " AND Tabelas_Fretes.DT_Validade >= '" + ed.getDT_Emissao () +
                "'";
          }
          if (DM_Tipo_Tabela_Frete.equals ("R")) {
            sql += " AND (Tabelas_Fretes.VL_R_Ate10 > 0 OR Tabelas_Fretes.VL_R_Acima200 > 0  OR Tabelas_Fretes.PE_AD_VALOREM > 0) ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("D")) {
            sql += " AND Tabelas_Fretes.VL_D_Ate1C > 0 ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("E")) {
            sql += " AND Tabelas_Fretes.VL_E_Ate1 > 0  ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("C")) {
            sql += " AND Tabelas_Fretes.VL_C_Ate25 > 0  ";
          }
          sql += " ORDER BY Tabelas_Fretes.DT_Vigencia ASC  ";

          // System.out.println (" tabela ->>> PAIS X REGIAO " + sql);

          rsTabela = this.executasql.executarConsulta (sql);
          while (rsTabela.next ()) {
            conED = this.carregaTabela (conED , rsTabela);
          }
        }

        if (conED.getDM_Localizado ().equals ("N")) {
          // System.out.println (" acha tabela 130 ");

          sql = " SELECT * FROM Tabelas_Fretes ";
          sql += " WHERE Tabelas_Fretes.OID_Pessoa = '" + OID_Pessoa + "'";
          if (DM_Percurso_Busca.equals ("Origem_Destino")) {
            sql += " AND Tabelas_Fretes.OID_Origem = 1 ";
            sql += " AND Tabelas_Fretes.OID_Destino = " +
                ed.getOID_Subregiao_Destino ();
          }
          if (DM_Percurso_Busca.equals ("Destino_Origem")) {
            sql += " AND Tabelas_Fretes.OID_Destino = " +
                ed.getOID_Subregiao_Destino ();
            sql += " AND Tabelas_Fretes.OID_Origem = 1 ";
          }

          sql += " AND Tabelas_Fretes.DM_Origem = 'P'";
          sql += " AND Tabelas_Fretes.DM_Destino = 'S'";
          if (DM_Tem_Produto.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Produto = " + ed.getOID_Produto ();
          }
          if (DM_Tem_Empresa.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Empresa = " + ed.getOID_Empresa ();
          }
          if (DM_Tabela_Cliente_Entregador.equals ("E")) {
            sql += " AND Tabelas_Fretes.OID_Pessoa_Redespacho = '" +
                OID_Cliente + "'";
          }

          if (DM_Tem_Peso.equals ("S")) {
            sql += " AND Tabelas_Fretes.NR_Peso_Inicial <= " + NR_Peso_Tabela;
            sql += " AND Tabelas_Fretes.NR_Peso_Final >= " + NR_Peso_Tabela;
          }
          if (DM_Tem_Modal.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Modal = " + ed.getOID_Modal ();
          }
          if (DM_Tem_Vigencia.equals ("S")) {
            sql += " AND Tabelas_Fretes.DT_Vigencia <= '" + ed.getDT_Emissao () +
                "'";
          }
          if (DM_Tem_Validade.equals ("S")) {
            sql += " AND Tabelas_Fretes.DT_Validade >= '" + ed.getDT_Emissao () +
                "'";
          }
          if (DM_Tipo_Tabela_Frete.equals ("R")) {
            sql += " AND (Tabelas_Fretes.VL_R_Ate10 > 0 OR Tabelas_Fretes.VL_R_Acima200 > 0  OR Tabelas_Fretes.PE_AD_VALOREM > 0) ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("D")) {
            sql += " AND Tabelas_Fretes.VL_D_Ate1C > 0 ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("E")) {
            sql += " AND Tabelas_Fretes.VL_E_Ate1 > 0  ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("C")) {
            sql += " AND Tabelas_Fretes.VL_C_Ate25 > 0  ";
          }
          sql += " ORDER BY Tabelas_Fretes.DT_Vigencia ASC  ";

          //// System.out.println(" tabela PAIS X POLO " + sql);

          rsTabela = this.executasql.executarConsulta (sql);
          while (rsTabela.next ()) {
            conED = this.carregaTabela (conED , rsTabela);
          }
        }

        if (conED.getDM_Localizado ().equals ("N")) {
          // System.out.println (" acha tabela 150 ");

          sql = " SELECT * FROM Tabelas_Fretes ";
          sql += " WHERE Tabelas_Fretes.OID_Pessoa = '" + OID_Pessoa + "'";
          if (DM_Percurso_Busca.equals ("Origem_Destino")) {
            sql += " AND Tabelas_Fretes.OID_Origem = 1 ";
            sql += " AND Tabelas_Fretes.OID_Destino = " +
                ed.getOID_Estado_Destino ();
          }
          if (DM_Percurso_Busca.equals ("Destino_Origem")) {
            sql += " AND Tabelas_Fretes.OID_Destino = " +
                ed.getOID_Estado_Destino ();
            sql += " AND Tabelas_Fretes.OID_Origem = 1 ";
          }

          sql += " AND Tabelas_Fretes.DM_Origem = 'P'";
          sql += " AND Tabelas_Fretes.DM_Destino = 'E'";
          if (DM_Tem_Produto.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Produto = " + ed.getOID_Produto ();
          }
          if (DM_Tem_Empresa.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Empresa = " + ed.getOID_Empresa ();
          }
          if (DM_Tabela_Cliente_Entregador.equals ("E")) {
            sql += " AND Tabelas_Fretes.OID_Pessoa_Redespacho = '" +
                OID_Cliente + "'";
          }

          if (DM_Tem_Peso.equals ("S")) {
            sql += " AND Tabelas_Fretes.NR_Peso_Inicial <= " + NR_Peso_Tabela;
            sql += " AND Tabelas_Fretes.NR_Peso_Final >= " + NR_Peso_Tabela;
          }
          if (DM_Tem_Modal.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Modal = " + ed.getOID_Modal ();
          }
          if (DM_Tem_Vigencia.equals ("S")) {
            sql += " AND Tabelas_Fretes.DT_Vigencia <= '" + ed.getDT_Emissao () +
                "'";
          }
          if (DM_Tem_Validade.equals ("S")) {
            sql += " AND Tabelas_Fretes.DT_Validade >= '" + ed.getDT_Emissao () +
                "'";
          }
          if (DM_Tipo_Tabela_Frete.equals ("R")) {
            sql += " AND (Tabelas_Fretes.VL_R_Ate10 > 0 OR Tabelas_Fretes.VL_R_Acima200 > 0  OR Tabelas_Fretes.PE_AD_VALOREM > 0) ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("D")) {
            sql += " AND Tabelas_Fretes.VL_D_Ate1C > 0 ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("E")) {
            sql += " AND Tabelas_Fretes.VL_E_Ate1 > 0  ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("C")) {
            sql += " AND Tabelas_Fretes.VL_C_Ate25 > 0  ";
          }
          sql += " ORDER BY Tabelas_Fretes.DT_Vigencia ASC  ";

          //// System.out.println(" tabela PAIS X ESTADO " + sql);

          rsTabela = this.executasql.executarConsulta (sql);
          while (rsTabela.next ()) {
            conED = this.carregaTabela (conED , rsTabela);
          }

        }
        if (conED.getDM_Localizado ().equals ("N")) {

          // System.out.println (" acha tabela 160 ");

          sql = " SELECT * FROM Tabelas_Fretes ";
          sql += " WHERE Tabelas_Fretes.OID_Pessoa = '" + OID_Pessoa + "'";
          //           if (DM_Percurso_Busca.equals("Origem_Destino")) {
          //
          //              sql += " AND Tabelas_Fretes.OID_Origem = 1 ";
          //              sql += " AND Tabelas_Fretes.OID_Destino = " + ed.getOID_Cidade_Destino();
          //            }
          //            if (DM_Percurso_Busca.equals("Destino_Origem")) {
          //              sql += " AND Tabelas_Fretes.OID_Destino = " + ed.getOID_Cidade_Destino();
          //              sql += " AND Tabelas_Fretes.OID_Origem = 1 ";
          //            }

          sql += " AND Tabelas_Fretes.DM_Origem = 'P'";
          sql += " AND Tabelas_Fretes.DM_Destino = 'P'";
          if (DM_Tem_Produto.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Produto = " + ed.getOID_Produto ();
          }
          if (DM_Tem_Empresa.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Empresa = " + ed.getOID_Empresa ();
          }
          if (DM_Tabela_Cliente_Entregador.equals ("E")) {
            sql += " AND Tabelas_Fretes.OID_Pessoa_Redespacho = '" +
                OID_Cliente + "'";
          }

          if (DM_Tem_Peso.equals ("S")) {
            sql += " AND Tabelas_Fretes.NR_Peso_Inicial <= " + NR_Peso_Tabela;
            sql += " AND Tabelas_Fretes.NR_Peso_Final >= " + NR_Peso_Tabela;
          }
          if (DM_Tem_Modal.equals ("S")) {
            sql += " AND Tabelas_Fretes.OID_Modal = " + ed.getOID_Modal ();
          }
          if (DM_Tem_Vigencia.equals ("S")) {
            sql += " AND Tabelas_Fretes.DT_Vigencia <= '" + ed.getDT_Emissao () +
                "'";
          }
          if (DM_Tem_Validade.equals ("S")) {
            sql += " AND Tabelas_Fretes.DT_Validade >= '" + ed.getDT_Emissao () +
                "'";
          }
          if (DM_Tipo_Tabela_Frete.equals ("R")) {
            sql += " AND (Tabelas_Fretes.VL_R_Ate10 > 0 OR Tabelas_Fretes.VL_R_Acima200 > 0  OR Tabelas_Fretes.PE_AD_VALOREM > 0) ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("D")) {
            sql += " AND Tabelas_Fretes.VL_D_Ate1C > 0 ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("E")) {
            sql += " AND Tabelas_Fretes.VL_E_Ate1 > 0  ";
          }
          if (DM_Tipo_Tabela_Frete.equals ("C")) {
            sql += " AND Tabelas_Fretes.VL_C_Ate25 > 0  ";
          }
          sql += " ORDER BY Tabelas_Fretes.DT_Vigencia ASC  ";

//            // System.out.println(" tabela PAIS X PAIS " + sql);

          rsTabela = this.executasql.executarConsulta (sql);
          while (rsTabela.next ()) {
            conED = this.carregaTabela (conED , rsTabela);
          }
        }

      }
      // System.out.println (" acha tabela 999 ");

    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () ,
                          "localizaTabela(Calcula_FreteED ed)");
    }
    return conED;
  }

  private boolean isCubagemConhecimento (String tipoConhecimento) {
    return "C".equals (tipoConhecimento)
        || "D".equals (tipoConhecimento);
  }

  public double calculaCubagem (String oid_Pessoa_Pagadora , double NR_Peso_Cub) throws
      Excecoes {
    int fatorCubagem;
    try {
      fatorCubagem = ClienteBean.getByOID_Cliente (oid_Pessoa_Pagadora).
          getNR_Peso_Cubado ();
    }
    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () ,
          "calculaCubagem(String oid_Pessoa_Pagadora, double NR_Peso_Cub)");
    }
    return NR_Peso_Cub * fatorCubagem;
  }
}
