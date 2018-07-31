package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;

import com.master.ed.DuplicataED;
import com.master.ed.FaturamentoCRTED;
import com.master.root.DateFormatter;
import com.master.root.FormataDataBean;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.bd.ExecutaSQL;
import com.master.util.bd.Transacao;
import com.master.util.ed.Parametro_FixoED;

public class FaturamentoCRTBD extends Transacao {

  private ExecutaSQL executasql;

  public FaturamentoCRTBD(ExecutaSQL sql) {
    this.executasql = sql;
  }


  public ArrayList inclui(FaturamentoCRTED ed) throws Excecoes{

      String sql = null;
      String sql_salvo = null;
      long valOid = 0;
      String chave = null;
      ArrayList list = new ArrayList();
      long NR_Duplicata = 999999999;
      long NR_Duplicata_AIDOF = 0;
      ResultSet rs = null;
      boolean DM_Valor_Limite_Fatura = true;
      double VL_Faturamento_Geral = 0;
      double VL_Faturar = 0;
      String dm_fatura_quinta = null;
      int nr_maximo_cto_fatura = 0;
      int nr_cto_fatura = 0;
      String DM_Tipo_Conhecimento = "";
      String OID_Pagador_Inicial = "";
      String OID_Pagador_Final = "";
      String Dm_Condicao_Vencimento = "";
      String DM_Quebra_Faturamento_Inicial = "NAO_QUEBRA";
      String DM_Quebra_Faturamento_Final = "";

      double VL_Fatura_Acumulado = 0;
      double VL_Fatura_Acumulado_Geral = 0;
      double Vl_Maximo_Fatura = 0;


      String  SHORT_DATE = "dd/MM/yyyy";
      DateFormatter dateFormatter = new DateFormatter();
      FaturamentoCRTED fatED = new FaturamentoCRTED();
      Parametro_FixoED parametro_FixoED = new Parametro_FixoED();

      try{

          ResultSet rsTP = null;

          ed.setOid_Tipo_Documento(parametro_FixoED.getOID_Tipo_Documento_Faturamento());

          //SETA QUAL UNIDADE DE COBRAÇA DEVE PEGAR - OU PARAMETRO_FIXOED OU DA TELA DO SISTEMA
          if (ed.getOID_Unidade_Cobranca() <= 0){
              ed.setOID_Unidade(parametro_FixoED.getOID_Unidade_Faturamento());
          }else{
              ed.setOID_Unidade(ed.getOID_Unidade_Cobranca());
          }


          //SELECT PARA PEGAR OS DADOS DA UNIDADE DE COBRANÇA
          sql = " SELECT Unidades.oid_moeda as oid_moeda_unidade, * FROM Unidades, Clientes, Carteiras " +
          	    " WHERE Unidades.OID_Unidade = " + ed.getOID_Unidade_Cobranca() +
          	    " AND Unidades.oid_Pessoa = Clientes.oid_Cliente "+
          	    " AND Clientes.oid_Carteira = Carteiras.oid_Carteira";

          rs = null;
          rsTP = null;

          rsTP = this.executasql.executarConsulta(sql);
          while (rsTP.next()){

              ed.setOID_Unidade(rsTP.getLong("OID_Unidade"));
              ed.setOid_Carteira(rsTP.getLong("OID_Carteira"));
              ed.setVL_Juro_Mora_Dia(new Double(rsTP.getDouble("PE_Juros")).doubleValue());
              ed.setVL_Multa(new Double(rsTP.getDouble("PE_Multa")).doubleValue());
              ed.setNR_Dias_Vencimento(rsTP.getLong("NR_Dias_Vencimento"));
              ed.setVL_Taxa_Cobranca(rsTP.getDouble("VL_Taxa_Cobranca"));

              //PARA PEGAR O OID_MOEDA DA UNIDADE DE COBRANCA
              ed.setOid_Moeda_Unidade_Cobranca(rsTP.getInt("oid_moeda_unidade"));//NOVO LEANDRO

              nr_maximo_cto_fatura = rsTP.getInt("nr_maximo_cto_fatura");
          }

          //SELECT PARA BUSCAR AS CRTs RELATIVAS AOS DADOS RETORNADOS DA TELA DE PREPARA FATURA
          sql =
              " select Conhecimentos_Faturamentos.OID_Pessoa_Pagador as oid_Pagador_Frete, " +
              "Conhecimentos_Internacionais.DT_Emissao as DT_Emissao_CRT,    *     " +

              "FROM " +
              " Conhecimentos_Internacionais, " +
              " Conhecimentos_Faturamentos, " +
              " Clientes, " +
              " Tipos_Faturamentos, " +
              " Carteiras, " +
              " Contas_Correntes " + //NOVO LEANDRO

              " WHERE  Conhecimentos_Internacionais.oid_Conhecimento = Conhecimentos_Faturamentos.oid_Conhecimento " +
              " AND    Conhecimentos_Faturamentos.oid_Pessoa_Pagador = Clientes.OID_Cliente " +
              " AND    Conhecimentos_Faturamentos.oid_Tipo_Faturamento = Tipos_Faturamentos.oid_Tipo_Faturamento " +
              " AND    Clientes.oid_Carteira = Carteiras.oid_Carteira " +

              //PARA GARANTIR QUE SERÃO PEGOS SOMENTE CRT COM PESSOA_PAGADOR DE MESMA MOEDA DA UNIDADE DE COBRANCA
              " AND    Carteiras.oid_conta_corrente = Contas_Correntes.oid_conta_corrente " + //NOVO LEANDRO
              " AND    Contas_Correntes.oid_moeda = " + ed.getOid_Moeda_Unidade_Cobranca() + //NOVO LEANDRO

              " AND    Conhecimentos_Faturamentos.VL_Faturar > 0 " +
              " AND    Conhecimentos_Faturamentos.DM_Situacao = 'L' ";

          if (String.valueOf(ed.getOID_Unidade_CTRC()) != null &&
                  !String.valueOf(ed.getOID_Unidade_CTRC()).equals("0")){
              sql += " and Conhecimentos_Internacionais.OID_Unidade = " + ed.getOID_Unidade_CTRC();
          }
          if (String.valueOf(ed.getOID_Produto()) != null &&
                  !String.valueOf(ed.getOID_Produto()).equals("0") &&
                  !String.valueOf(ed.getOID_Produto()).equals("null")){
              sql += " and Conhecimentos_Internacionais.OID_Produto = " + ed.getOID_Produto();
          }

          if (String.valueOf(ed.getOID_Pessoa_Pagador()) != null &&
                  !String.valueOf(ed.getOID_Pessoa_Pagador()).equals("")){
              sql += " and Conhecimentos_Faturamentos.OID_Pessoa_Pagador = '" + ed.getOID_Pessoa_Pagador() + "'";
          }

          if (String.valueOf(ed.getDT_Emissao_Inicial()) != null &&
                  !String.valueOf(ed.getDT_Emissao_Inicial()).equals("") &&
                  String.valueOf(ed.getDT_Emissao_Final()) != null &&
                  !String.valueOf(ed.getDT_Emissao_Final()).equals("")){
              sql += " AND Conhecimentos_Internacionais.DT_Emissao >= '" + ed.getDT_Emissao_Inicial() + "'";
              sql += " AND Conhecimentos_Internacionais.DT_Emissao <= '" + ed.getDT_Emissao_Final() + "'";
          }

          if (String.valueOf(ed.getNR_Conhecimento_Inicial()) != null &&
                  !String.valueOf(ed.getNR_Conhecimento_Inicial()).equals("0") &&
                  String.valueOf(ed.getNR_Conhecimento_Final()) != null &&
                  !String.valueOf(ed.getNR_Conhecimento_Final()).equals("0")){
              sql += " AND Conhecimentos_Internacionais.NR_Conhecimento >= " + ed.getNR_Conhecimento_Inicial() + "";
              sql += " AND Conhecimentos_Internacionais.NR_Conhecimento <= " + ed.getNR_Conhecimento_Final() + "";
          }
          else if (JavaUtil.doValida(ed.getNR_Conhecimento_Sequencia())){
                sql += " AND Conhecimentos_Internacionais.NR_Conhecimento in (" + ed.getNR_Conhecimento_Sequencia()+")";
          }

          sql_salvo=sql;

          sql += " Order by Conhecimentos_Faturamentos.OID_Pessoa_Pagador, " +
          		"Tipos_Faturamentos.DM_Multiplo, " +
          		"Tipos_Faturamentos.CD_Tipo_Faturamento, " +
          		"Conhecimentos_Faturamentos.DT_Emissao DESC";

          // System.out.println("");
          // System.out.println("FatCRTBD().inclui() sql = "+sql);

          ResultSet res = null;
          res = this.executasql.executarConsulta(sql);




          //INICIO DO WHILE PARA PEGAR CADA CRT PARA MONTAR A FATURA
          while (res.next()){

              this.inicioTransacao();
              this.executasql = this.sql;


              ed.setOID_Conhecimento(res.getString("OID_Conhecimento"));

              //SELECT PARA SETAR O VENDEDOR DA FATURA
              sql = " SELECT oid_Vendedor FROM Clientes WHERE oid_Cliente='"+res.getString("oid_Pagador_Frete")+"'";

              rs = this.executasql.executarConsulta(sql);
              while (rs.next()) ed.setOID_Vendedor(rs.getString("OID_Vendedor"));

              ed.setOID_Pessoa_Pagador(res.getString("oid_Pagador_Frete"));

              OID_Pagador_Inicial = ed.getOID_Pessoa_Pagador();

              ed.setOid_Carteira(res.getLong("OID_Carteira"));
              ed.setVL_Juro_Mora_Dia(new Double(res.getDouble("PE_Juros")).doubleValue());
              ed.setVL_Multa(new Double(res.getDouble("PE_Multa")).doubleValue());

              ed.setNR_Dias_Vencimento(res.getLong("NR_Dias_Vencimento"));
              ed.setVL_Taxa_Cobranca(res.getDouble("VL_Taxa_Cobranca"));
              dm_fatura_quinta = res.getString("dm_fatura_quinta");
              Vl_Maximo_Fatura = res.getDouble("Vl_Maximo_Fatura");
              nr_maximo_cto_fatura = res.getInt("nr_maximo_cto_fatura");

              Dm_Condicao_Vencimento = res.getString("Dm_Condicao_Vencimento");

              if (  !OID_Pagador_Inicial.equals(OID_Pagador_Final) ||
                      ( !DM_Quebra_Faturamento_Inicial.equals(DM_Quebra_Faturamento_Final) &&
                              !DM_Quebra_Faturamento_Inicial.equals("NAO_QUEBRA"))  ){

                  OID_Pagador_Final = ed.getOID_Pessoa_Pagador();

                  DM_Quebra_Faturamento_Final = res.getString("DM_Multiplo") + res.getString("CD_Tipo_Faturamento");

                  if (ed.getVL_Fatura() > 0 || Vl_Maximo_Fatura > 0 || nr_maximo_cto_fatura > 0){

                      if (ed.getVL_Fatura() > 0 || Vl_Maximo_Fatura == 0){
                          Vl_Maximo_Fatura = 999999999;
                      }
                      if (ed.getVL_Fatura() == 0 || Vl_Maximo_Fatura > 0){
                          ed.setVL_Fatura(999999999);
                      }

                      DM_Valor_Limite_Fatura = true;
                      while (DM_Valor_Limite_Fatura){

                          ed.setVL_Duplicata(0);

                          //SELECT PARA PEGAR O PROXIMO NUMERO DE FATURA CRT, OID, ETC
                          sql =  " SELECT AIDOF.NR_Proximo, AIDOF.OID_AIDOF, AIDOF.NM_Serie " +
                          		 " FROM Parametros_Filiais, AIDOF " +
                          		 " WHERE Parametros_Filiais.OID_AIDOF_FaturaCRT = AIDOF.OID_AIDOF " +
                          		 " AND Parametros_Filiais.OID_Unidade = " + ed.getOID_Unidade_Cobranca();

                          rs = this.executasql.executarConsulta(sql);
                          while (rs.next()){
                              ed.setNM_Serie(rs.getString("NM_Serie"));
                              ed.setNR_Fatura(rs.getLong("NR_Proximo"));
                              NR_Duplicata_AIDOF=rs.getLong("NR_Proximo");
                              valOid = rs.getLong("OID_AIDOF");
                          }

                          //                String N_Duplicata = String.valueOf(ed.getOID_Unidade_Cobranca()+
                          //                                     ((String.valueOf(ed.getNR_Fatura()+1000).substring(1,4))));

                          //RETIRADA O OID_UNIDADE_COBRANCA PARA TER SEQUENCIA DE NR DE FATURA PARA TODAS AS
                          //UNIDADES DO CLIENTE - LEANDRO

                          String N_Duplicata = String.valueOf(ed.getNR_Fatura()); //+1000).substring(1,4);

                          ed.setNR_Fatura(new Long(N_Duplicata).longValue());

                          if (NR_Duplicata > ed.getNR_Fatura() || NR_Duplicata == 999999999){
                              NR_Duplicata = ed.getNR_Fatura();
                          }

                          //              chave = String.valueOf(String.valueOf(ed.getNR_Fatura()) + ed.getNM_Serie());
                          //RETIRADO ESTA FORMA DE CONTROLE DE OID DEVIDO QUE A CADA OID CRIADO CONSOME MIL POSIÇÕES NO BANCO
                          //PORQUE OS NRS DE OIDs SÃO INCREMENTADOS NA CASA DE MILHAR E NÃO NA UNIDADE - LEANDRO

                          ResultSet rsOid = executasql.executarConsulta("select max(OID_Duplicata) as result from Duplicatas");
                          while (rsOid.next()){
                              chave = String.valueOf(rsOid.getInt("result") + 1);
                          }

                          ed.setOID_Fatura(chave);

                          if (ed.getDT_Vencimento() == null || ed.getDT_Vencimento().equals("") || ed.getDT_Vencimento().equals("null")) {

                              FormataDataBean DataFormatadata = new FormataDataBean();
                              DataFormatadata.setDT_FormataData(res.getString("DT_Emissao_CRT"));
                              String DT_Emissao_CRT = DataFormatadata.getDT_FormataData();
                              String DT_Emissao_Fatura = ed.getDT_Emissao();
                              ed.setDT_Vencimento(this.calcula_Vencimento(ed.getOID_Pessoa_Pagador(), DT_Emissao_Fatura, DT_Emissao_CRT));
                          }
//// System.out.println(ed.getDT_Vencimento());
                          if (ed.getDT_Vencimento_Minimo() != null && !ed.getDT_Vencimento_Minimo().equals("") && !ed.getDT_Vencimento_Minimo().equals("null") && !ed.getDT_Vencimento_Minimo().equals("undefined")) {

	                          String data1 = ed.getDT_Vencimento();
	                          String data2 = ed.getDT_Vencimento_Minimo(); // Data.getDataDMY();
	                          Calendar cal1 = Data.stringToCalendar(data1,"dd/MM/yyyy");
	                          Calendar cal2 = Data.stringToCalendar(data2,"dd/MM/yyyy");
	                          Data data = new Data();
	                          if (cal2.after(cal1)){
	                              ed.setDT_Vencimento(ed.getDT_Vencimento_Minimo());
	                          }
                          }

                          if (ed.getDT_Vencimento() == null || ed.getDT_Vencimento().equals("") || ed.getDT_Vencimento().equals("null")) {
                              ed.setDT_Vencimento(Data.getDataDMY());
                          }
//// System.out.println("after " +ed.getDT_Vencimento());
                          ed.setVL_Desconto_Faturamento(0.0);
                          ed.setVL_Taxa_Refaturamento(0.0);

                          sql =
                          " insert into Duplicatas "+
                          "(OID_Duplicata, NR_DOCUMENTO, NR_PARCELA, "+
                          "DT_EMISSAO, DT_VENCIMENTO, DT_COTACAO, VL_Duplicata, VL_Cotacao, "+
                          "VL_Taxa_Cobranca, "+
                          "VL_Multa, "+
                          "VL_Juro_Mora_Dia, "+
                          "NR_Duplicata, DT_STAMP, USUARIO_STAMP, "+
                          "DM_STAMP, VL_Desconto_Faturamento, VL_SALDO, "+
                          "OID_TIPO_DOCUMENTO, OID_Carteira, OID_PESSOA, DM_Quebra_Faturamento, "+
                          "OID_Vendedor, OID_UNIDADE, NR_REMESSA, DM_Tipo_Documento, oid_moeda) values "+
                          "(" + ed.getOID_Fatura() + ",";
                          sql += ed.getNR_Fatura() + ",";
                          sql += 1 + ",";
                          sql += "'" + ed.getDT_Emissao() + "',";
                          sql += "'" + ed.getDT_Vencimento() + "',";
                          sql += "'" + ed.getDT_Cotacao() + "',";
                          sql += ed.getVL_Duplicata() + ",";
                          sql += ed.getVL_Cotacao() + ",";
                          sql += ed.getVL_Taxa_Cobranca() + ",";
                          sql += ed.getVL_Multa() + ",";
                          sql += ed.getVL_Juro_Mora_Dia() + ",";
                          sql += ed.getNR_Fatura() + ",";
                          sql += "'" + ed.getDt_stamp() + "',";
                          sql += "'" + ed.getUsuario_Stamp() + "',";
                          sql += "'" + ed.getDm_Stamp() + "',";
                          sql += ed.getVL_Desconto_Faturamento()  + ",";
                          sql += ed.getVL_Duplicata() + ",";
                          sql += ed.getOid_Tipo_Documento() + ",";
                          sql += ed.getOid_Carteira() + ",";
                          sql += "'" + ed.getOID_Pessoa_Pagador() + "','";
                          sql += DM_Quebra_Faturamento_Inicial + "','";
                          sql += ed.getOID_Vendedor() + "',";
                          sql += ed.getOID_Unidade() + ",0,'2',";
                          sql += ed.getOid_Moeda_Unidade_Cobranca() + ")";

// System.out.println("FatCRTBD.inclui() sql = " + sql);

                          int u = executasql.executarUpdate(sql);

                          VL_Fatura_Acumulado = 0;
                          DM_Valor_Limite_Fatura = false;
                          VL_Fatura_Acumulado_Geral = VL_Faturamento_Geral;
                          nr_cto_fatura = 0;
                          if (nr_maximo_cto_fatura == 0){
                              nr_maximo_cto_fatura = 1;
                          }

                          sql =  sql_salvo;
                          sql += " and Conhecimentos_Faturamentos.OID_Pessoa_Pagador = '" + ed.getOID_Pessoa_Pagador() + "'";

                          sql += " ORDER BY VL_Faturar";
// System.out.println("FatCRTBD.inclui() sql 3 = " + sql);
                          rs = this.executasql.executarConsulta(sql);

                          while (rs.next()){

                              VL_Faturar = ((rs.getDouble("VL_Faturar") + rs.getDouble("VL_Faturar_Adicional")) * ed.getVL_Cotacao());

                              VL_Fatura_Acumulado = VL_Fatura_Acumulado + VL_Faturar;
                              VL_Fatura_Acumulado_Geral = VL_Fatura_Acumulado_Geral + VL_Faturar;

                              if (ed.getVL_Fatura() >= VL_Fatura_Acumulado_Geral){
                                  if (VL_Fatura_Acumulado <= Vl_Maximo_Fatura){
                                      if (nr_cto_fatura < nr_maximo_cto_fatura){

                                          DM_Valor_Limite_Fatura = true;
                                          ed.setVL_Duplicata(VL_Fatura_Acumulado);

                                          VL_Faturamento_Geral = VL_Faturamento_Geral + VL_Fatura_Acumulado;

                                          nr_cto_fatura = nr_cto_fatura + 1;

                                          sql =  " UPDATE Duplicatas SET VL_Duplicata = " + ed.getVL_Duplicata() + ", VL_Saldo=" + ed.getVL_Duplicata();
                                          sql += " WHERE OID_Duplicata = '" + ed.getOID_Fatura() + "'" ;

                                          u = executasql.executarUpdate(sql);
// System.out.println("FatCRTBD.inclui() sql 4 = " + sql);
                                          chave = (ed.getOID_Fatura() + rs.getString("OID_Conhecimento")+ String.valueOf(rs.getString("oid_Conhecimento_Faturamento") ));

                                          sql = " insert into Origens_Duplicatas (OID_Origem_Duplicata, OID_Conhecimento, OID_Duplicata, DT_Origem_Duplicata, HR_Origem_Duplicata, DM_Situacao, oid_Conhecimento_Faturamento ) values ";
                                          sql += "('" + chave + "','" + rs.getString("OID_Conhecimento") + "'," + ed.getOID_Fatura() + ",'"  + ed.getDt_stamp() + "','" + ed.getHr_stamp() + "','A'," + rs.getString("oid_Conhecimento_Faturamento") + ")";

                                          int ins = executasql.executarUpdate(sql);
// System.out.println("FatCRTBD.inclui() sql 5 = " + sql);
                                          sql = " UPDATE Conhecimentos_Faturamentos SET DM_Situacao = 'F', "+
                                          		" oid_Duplicata = " + ed.getOID_Fatura()+ "," +
                                          		" VL_Faturar_Conversao = " + VL_Faturar + "," +
                                          		" VL_Cotacao = " + ed.getVL_Cotacao() +
                                          		" WHERE Conhecimentos_Faturamentos.oid_Conhecimento_Faturamento = " + rs.getString("oid_Conhecimento_Faturamento") ;

                                          int up = executasql.executarUpdate(sql);
// System.out.println("FatCRTBD.inclui() sql 7 = " + sql);
                                          sql = " UPDATE Conhecimentos_Internacionais SET DM_Situacao = 'F' " +
                                          		" WHERE Conhecimentos_Internacionais.oid_Conhecimento = '" + rs.getString("oid_Conhecimento") +"'";

                                          int ut = executasql.executarUpdate(sql);
// System.out.println("FatCRTBD.inclui() sql 8 = " + sql);
                                          if (valOid > 0){
                                              sql =  " UPDATE AIDOF SET NR_Proximo= " +( NR_Duplicata_AIDOF+ 1);
                                              sql += " WHERE OID_AIDOF = " + valOid ;

                                              u = executasql.executarUpdate(sql);
// System.out.println("FatCRTBD.inclui() sql 9 = " + sql);
                                              valOid = 0;
                                          }
                                      }
                                  }
                              }
                          }

                      }

                      //DELEÇÃO DE DUPLICATAS COM VALOR ZERO
                      sql = " DELETE FROM Duplicatas "	+
                      	    " WHERE OID_Duplicata = '" + ed.getOID_Fatura() + "'" +
                            " AND VL_Duplicata = 0";

                      int u = executasql.executarUpdate(sql);

                  }

                  OID_Pagador_Final = ed.getOID_Pessoa_Pagador();
                  DM_Quebra_Faturamento_Final = res.getString("DM_Multiplo") + res.getString("CD_Tipo_Faturamento");

              }
              this.fimTransacao(true);

          }

          this.inicioTransacao();
          this.executasql = this.sql;


          //SELECT PARA RETORNAR A DUPLICATA GERADA PARA A TELA
          sql =
          "select Duplicatas.OID_Duplicata, Pessoas.oid_pessoa, Pessoas.nr_cnpj_cpf, Pessoas.nm_razao_Social, " +
          "Pessoas_Bancos.nm_razao_Social as NM_Razao_Banco, Carteiras.oid_Carteira, Carteiras.cd_Carteira, " +
          "Contas_Correntes.NR_conta_corrente, Tipos_Documentos.oid_tipo_documento, " +
          "Tipos_Documentos.cd_tipo_documento, Tipos_Documentos.nm_tipo_documento, Duplicatas.nr_Duplicata, " +
          "Duplicatas.nr_parcela, Duplicatas.nr_documento, Duplicatas.dt_vencimento, Duplicatas.vl_saldo, " +
          "Duplicatas.dt_emissao, Duplicatas.vl_duplicata, Duplicatas.vl_saldo " +

          "from Duplicatas, pessoas, Pessoas Pessoas_Bancos, tipos_documentos, Carteiras, Contas_Correntes " +

          "where "+
          " duplicatas.oid_pessoa = pessoas.oid_pessoa and"+
          " duplicatas.oid_Carteira = Carteiras.oid_Carteira and"+
          " duplicatas.oid_tipo_documento = tipos_documentos.oid_tipo_documento and"+
          " carteiras.oid_conta_corrente = contas_correntes.oid_conta_corrente and "+
          " contas_correntes.oid_pessoa = pessoas_bancos.oid_pessoa " +
          " and duplicatas.nr_duplicata >= " + NR_Duplicata +
          " and duplicatas.DT_EMISSAO = '" + ed.getDT_Emissao() + "'" +
          " ORDER BY pessoas.nm_razao_social, duplicatas.nr_duplicata, duplicatas.DT_Vencimento ";
// System.out.println("FAT volta pra tela >>> "+sql);
          res = null;
          res = this.executasql.executarConsulta(sql);

          //POPULA O EDVOLTA PARA RETORNA A TELA
          while (res.next()){

              DuplicataED edVolta = new DuplicataED();
              //popular os dados de duplicata para voltar

              edVolta.setOid_Duplicata(new Long(res.getString("oid_duplicata")).longValue());

              edVolta.setOid_Pessoa(res.getString("oid_pessoa"));
              edVolta.setNr_CNPJ_CPF(res.getString("nr_cnpj_cpf"));
              edVolta.setNm_Razao_Social(res.getString("nm_razao_Social"));

              edVolta.setCD_Conta_Corrente(res.getString("nr_Conta_Corrente"));
              edVolta.setNM_Banco(res.getString("NM_Razao_Banco"));

              edVolta.setOid_Carteira(new Integer(res.getString("oid_Carteira")));
              edVolta.setCd_Carteira(res.getString("cd_Carteira"));

              edVolta.setOid_Tipo_Documento(new Integer(res.getString("oid_tipo_documento")));
              edVolta.setCd_Tipo_Documento(res.getString("cd_tipo_documento"));
              edVolta.setNm_Tipo_Documento(res.getString("nm_tipo_documento"));

              edVolta.setNr_Duplicata(new Integer(res.getString("nr_Duplicata")));
              edVolta.setNr_Parcela(new Integer(res.getString("nr_parcela")));

              FormataDataBean DataFormatada = new FormataDataBean();
              DataFormatada.setDT_FormataData(res.getString("dt_vencimento"));
              edVolta.setDt_Vencimento(DataFormatada.getDT_FormataData());
              DataFormatada.setDT_FormataData(res.getString("dt_emissao"));
              edVolta.setDt_Emissao(DataFormatada.getDT_FormataData());

              edVolta.setNr_Documento(res.getString("nr_documento"));

              String nr_Parcela = res.getString("nr_parcela");
              if (nr_Parcela != null)
                  edVolta.setNr_Parcela(new Integer(nr_Parcela));

              edVolta.setVl_Duplicata(new Double(res.getString("vl_duplicata")));
              edVolta.setVl_Saldo(new Double(res.getString("vl_saldo")));

              list.add(edVolta);
          }
          this.fimTransacao(true);

      }
      catch(Exception exc){
          Excecoes excecoes = new Excecoes();
          excecoes.setClasse(this.getClass().getName());
          excecoes.setMensagem("Erro ao incluir Faturamento");
          excecoes.setMetodo("inclui(FaturamentoED)");
          excecoes.setExc(exc);
          this.abortaTransacao();
          throw excecoes;
      }

      return list;
  }

  public String calcula_Vencimento(String OID_Pessoa_Pagador, String DT_Emissao_Fatura, String DT_Emissao_CRT) throws Excecoes{

    String DT_Vencimento = null;
    String  SHORT_DATE = "dd/MM/yyyy";
    DateFormatter dateFormatter = new DateFormatter();
    String DM_VENCIMENTO_SEGUNDA = null;
    String DM_VENCIMENTO_TERCA = null;
    String DM_VENCIMENTO_QUARTA = null;
    String DM_VENCIMENTO_QUINTA = null;
    String DM_VENCIMENTO_SEXTA = null;
    String NR_Dias = "0";
    String Dm_Condicao_Vencimento = null;
    String sql = null;

    try{

		sql = " SELECT * FROM Clientes WHERE Clientes.OID_Cliente = '" + OID_Pessoa_Pagador + "'";
		// System.out.println("Vencimento 3 sql = "+sql);

		ResultSet rsTP = null;
		rsTP = this.executasql.executarConsulta(sql);

		while (rsTP.next()){
			NR_Dias = String.valueOf(rsTP.getLong("NR_Dias_Vencimento"));
			DM_VENCIMENTO_SEGUNDA = rsTP.getString("DM_VENCIMENTO_SEGUNDA");
			DM_VENCIMENTO_TERCA = rsTP.getString("DM_VENCIMENTO_TERCA");
			DM_VENCIMENTO_QUARTA = rsTP.getString("DM_VENCIMENTO_QUARTA");
			DM_VENCIMENTO_QUINTA = rsTP.getString("DM_VENCIMENTO_QUINTA");
			DM_VENCIMENTO_SEXTA = rsTP.getString("DM_VENCIMENTO_SEXTA");
			Dm_Condicao_Vencimento = rsTP.getString("Dm_Condicao_Vencimento");
		}
		rsTP = null;

    	Calendar cal = Data.stringToCalendar(DT_Emissao_Fatura,"dd/MM/yyyy");
        if (Dm_Condicao_Vencimento != null && !Dm_Condicao_Vencimento.equals("") &&
                Dm_Condicao_Vencimento.equals("F")){
            cal = Data.stringToCalendar(DT_Emissao_Fatura,"dd/MM/yyyy");
        }else{
            cal = Data.stringToCalendar(DT_Emissao_CRT,"dd/MM/yyyy");
        }
        int diasAtuais = cal.get(Calendar.DAY_OF_MONTH);
        int NR_Dias_Vencimento = new Integer(NR_Dias).intValue();
        cal.set(Calendar.DAY_OF_MONTH,diasAtuais+NR_Dias_Vencimento);

        //Domingo = 1, Segunda=2, terça=3 ... sábado=7
        switch(cal.get(Calendar.DAY_OF_WEEK)){
          case 1:
          	if (DM_VENCIMENTO_SEGUNDA != null && DM_VENCIMENTO_SEGUNDA.equals("S")){
              	NR_Dias = "1";
          	}else if (DM_VENCIMENTO_TERCA != null && DM_VENCIMENTO_TERCA.equals("S")){
              	NR_Dias = "2";
          	}else if (DM_VENCIMENTO_QUARTA != null && DM_VENCIMENTO_QUARTA.equals("S")){
              	NR_Dias = "3";
          	}else if (DM_VENCIMENTO_QUINTA != null && DM_VENCIMENTO_QUINTA.equals("S")){
              	NR_Dias = "4";
          	}else if (DM_VENCIMENTO_SEXTA != null && DM_VENCIMENTO_SEXTA.equals("S")){
              	NR_Dias = "5";
          	}
            break;
          case 2:
          	if (DM_VENCIMENTO_SEGUNDA != null && DM_VENCIMENTO_SEGUNDA.equals("S")){
              	NR_Dias = "0";
          	}else if (DM_VENCIMENTO_TERCA != null && DM_VENCIMENTO_TERCA.equals("S")){
              	NR_Dias = "1";
          	}else if (DM_VENCIMENTO_QUARTA != null && DM_VENCIMENTO_QUARTA.equals("S")){
              	NR_Dias = "2";
          	}else if (DM_VENCIMENTO_QUINTA != null && DM_VENCIMENTO_QUINTA.equals("S")){
              	NR_Dias = "3";
          	}else if (DM_VENCIMENTO_SEXTA != null && DM_VENCIMENTO_SEXTA.equals("S")){
              	NR_Dias = "4";
          	}
            break;
          case 3:
          	if (DM_VENCIMENTO_TERCA != null && DM_VENCIMENTO_TERCA.equals("S")){
              	NR_Dias = "0";
          	}else if (DM_VENCIMENTO_QUARTA != null && DM_VENCIMENTO_QUARTA.equals("S")){
              	NR_Dias = "1";
          	}else if (DM_VENCIMENTO_QUINTA != null && DM_VENCIMENTO_QUINTA.equals("S")){
              	NR_Dias = "2";
          	}else if (DM_VENCIMENTO_SEXTA != null && DM_VENCIMENTO_SEXTA.equals("S")){
              	NR_Dias = "3";
          	}else if (DM_VENCIMENTO_SEGUNDA != null && DM_VENCIMENTO_SEGUNDA.equals("S")){
              	NR_Dias = "6";
          	}
            break;
          case 4:
          	if (DM_VENCIMENTO_QUARTA != null && DM_VENCIMENTO_QUARTA.equals("S")){
              	NR_Dias = "0";
          	}else if (DM_VENCIMENTO_QUINTA != null && DM_VENCIMENTO_QUINTA.equals("S")){
              	NR_Dias = "1";
          	}else if (DM_VENCIMENTO_SEXTA != null && DM_VENCIMENTO_SEXTA.equals("S")){
              	NR_Dias = "2";
          	}else if (DM_VENCIMENTO_SEGUNDA != null && DM_VENCIMENTO_SEGUNDA.equals("S")){
              	NR_Dias = "5";
          	}else if (DM_VENCIMENTO_TERCA != null && DM_VENCIMENTO_TERCA.equals("S")){
              	NR_Dias = "6";
          	}
            break;
          case 5:
          	if (DM_VENCIMENTO_QUINTA != null && DM_VENCIMENTO_QUINTA.equals("S")){
              	NR_Dias = "0";
          	}else if (DM_VENCIMENTO_SEXTA != null && DM_VENCIMENTO_SEXTA.equals("S")){
              	NR_Dias = "1";
          	}else if (DM_VENCIMENTO_SEGUNDA != null && DM_VENCIMENTO_SEGUNDA.equals("S")){
              	NR_Dias = "4";
          	}else if (DM_VENCIMENTO_TERCA != null && DM_VENCIMENTO_TERCA.equals("S")){
              	NR_Dias = "5";
          	}else if (DM_VENCIMENTO_QUARTA != null && DM_VENCIMENTO_QUARTA.equals("S")){
              	NR_Dias = "6";
          	}
            break;
          case 6:
          	if (DM_VENCIMENTO_SEXTA != null && DM_VENCIMENTO_SEXTA.equals("S")){
              	NR_Dias = "0";
          	}else if (DM_VENCIMENTO_SEGUNDA != null && DM_VENCIMENTO_SEGUNDA.equals("S")){
              	NR_Dias = "3";
          	}else if (DM_VENCIMENTO_TERCA != null && DM_VENCIMENTO_TERCA.equals("S")){
              	NR_Dias = "4";
          	}else if (DM_VENCIMENTO_QUARTA != null && DM_VENCIMENTO_QUARTA.equals("S")){
              	NR_Dias = "5";
          	}else if (DM_VENCIMENTO_QUINTA != null && DM_VENCIMENTO_QUINTA.equals("S")){
              	NR_Dias = "6";
          	}
            break;
          case 7:
          	if (DM_VENCIMENTO_SEGUNDA != null && DM_VENCIMENTO_SEGUNDA.equals("S")){
              	NR_Dias = "2";
          	}else if (DM_VENCIMENTO_TERCA != null && DM_VENCIMENTO_TERCA.equals("S")){
              	NR_Dias = "3";
          	}else if (DM_VENCIMENTO_QUARTA != null && DM_VENCIMENTO_QUARTA.equals("S")){
              	NR_Dias = "4";
          	}else if (DM_VENCIMENTO_QUINTA != null && DM_VENCIMENTO_QUINTA.equals("S")){
              	NR_Dias = "5";
          	}else if (DM_VENCIMENTO_SEXTA != null && DM_VENCIMENTO_SEXTA.equals("S")){
              	NR_Dias = "6";
          	}
            break;
        }

        if (!NR_Dias.equals("0")){
            diasAtuais = cal.get(Calendar.DAY_OF_MONTH);
            NR_Dias_Vencimento = new Integer(NR_Dias).intValue();
            cal.set(Calendar.DAY_OF_MONTH,diasAtuais+NR_Dias_Vencimento);
        }

        DT_Vencimento = dateFormatter.calendarToString(cal, SHORT_DATE);

    	return DT_Vencimento;
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar");
      excecoes.setMetodo("alterar");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void altera(FaturamentoCRTED ed) throws Excecoes{

    String sql = null;

    try{
      int i = executasql.executarUpdate(sql);
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar");
      excecoes.setMetodo("alterar");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void deleta(FaturamentoCRTED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from Conhecimentos WHERE oid_Conhecimento = ";
      sql += "('" + ed.getOID_Conhecimento() + "')";

      int i = executasql.executarUpdate(sql);
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao excluir");
      excecoes.setMetodo("excluir");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }




  public ArrayList lista(FaturamentoCRTED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();
    return list;
  }

  public FaturamentoCRTED getByRecord(FaturamentoCRTED ed)throws Excecoes{

    String sql = null;

    FaturamentoCRTED edVolta = new FaturamentoCRTED();

    try{

      sql = " select * from Conhecimentos where 1=1 ";

      if (String.valueOf(ed.getOID_Conhecimento()) != null &&
          !String.valueOf(ed.getOID_Conhecimento()).equals("")){
        sql += " and OID_Conhecimento = '" + ed.getOID_Conhecimento() + "'";
      }
      if (String.valueOf(ed.getNR_Conhecimento()) != null && !String.valueOf(ed.getNR_Conhecimento()).equals("0")){
        sql += " and NR_Conhecimento = " + ed.getNR_Conhecimento();
        sql += " and OID_Unidade = " + ed.getOID_Unidade();
      }

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      FormataDataBean DataFormatada = new FormataDataBean();

      while (res.next()){
      }

      sql =  " SELECT SUM(Notas_Fiscais.VL_Nota_Fiscal) AS VL_Notas, SUM(Notas_Fiscais.NR_Volumes) AS NR_Volume, SUM(Notas_Fiscais.NR_Peso) AS NR_Peso FROM Notas_Fiscais, Conhecimentos_Notas_Fiscais ";
      sql += " WHERE Notas_Fiscais.OID_Nota_Fiscal = Conhecimentos_Notas_Fiscais.OID_Nota_Fiscal ";
      sql += " AND Conhecimentos_Notas_Fiscais.OID_Conhecimento = '" + edVolta.getOID_Conhecimento() + "'";

      ResultSet rs = null;
      rs = this.executasql.executarConsulta(sql);

      while (rs.next()){

     }

      sql =  " SELECT * from Movimentos_Conhecimentos, Tipos_Movimentos ";
      sql += " WHERE oid_Conhecimento ='" + edVolta.getOID_Conhecimento() + "'";
      sql += " AND Tipos_Movimentos.CD_Tipo_Movimento = 'TFC' ";
      sql += " AND Tipos_Movimentos.OID_Tipo_Movimento = Movimentos_Conhecimentos.OID_Tipo_Movimento ";

      rs = null;
      rs = this.executasql.executarConsulta(sql);

      while (rs.next()){
        edVolta.setVL_Total_Frete(rs.getDouble("VL_Movimento"));
      }

      sql =  " SELECT * from Movimentos_Conhecimentos, Tipos_Movimentos ";
      sql += " WHERE oid_Conhecimento ='" + edVolta.getOID_Conhecimento() + "'";
      sql += " AND Tipos_Movimentos.CD_Tipo_Movimento = 'ICM' ";
      sql += " AND Tipos_Movimentos.OID_Tipo_Movimento = Movimentos_Conhecimentos.OID_Tipo_Movimento ";

      rs = null;
      rs = this.executasql.executarConsulta(sql);

      while (rs.next()){
        edVolta.setVL_ICMS(rs.getDouble("VL_Movimento"));
      }

    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao selecionar");
      excecoes.setMetodo("selecionar");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return edVolta;
  }
}
