package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import com.master.ed.DuplicataED;
import com.master.ed.DuplicataPesquisaED;
import com.master.rl.BloquetoRL;
import com.master.root.FormataDataBean;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FormataValor;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;
import com.master.util.SeparaEndereco;
import com.master.util.Utilitaria;
import com.master.util.bd.ExecutaSQL;

public class BloquetoBD {

  private ExecutaSQL executasql;
  Utilitaria util = new Utilitaria (executasql);

  public BloquetoBD (ExecutaSQL sql) {
    this.executasql = sql;
    util = new Utilitaria (this.executasql);
  }

  public byte[] imprime_Bloqueto (DuplicataPesquisaED ed) throws Excecoes {

    ResultSet res = this.monta_Pesquisa(ed);

    return new BloquetoRL ().imprime_Bloqueto (res , ed.getCD_Banco ());
  }


  public String imprime_Bloqueto_Matricial (DuplicataPesquisaED ed) throws Excecoes {

    ResultSet res = this.monta_Pesquisa(ed);

    return new BloquetoRL ().imprime_BloquetoMatricial(res , ed);
  }


  private ResultSet monta_Pesquisa (DuplicataPesquisaED ed) throws Excecoes {

    String sql = null;

    if ("C".equals(ed.getDM_Relatorio())){ // Ordena por Conhecimento

	    sql = " SELECT Duplicatas.OID_Duplicata" +
	        "     ,Pessoas.oid_pessoa" +
	        "     ,Pessoas.nr_cnpj_cpf" +
	        "     ,Pessoas.nm_razao_Social" +
	        "     ,Pessoas.NM_ENDERECO" +
	        "     ,Pessoas.NM_BAIRRO" +
	        "     ,Pessoas.NR_CEP" +
	        "     ,Pessoas.NM_INSCRICAO_ESTADUAL" +
	        "     ,Cidades.NM_Cidade" +
	        "     ,Estados.CD_Estado" +
	        "     ,Pessoas_Bancos.nm_razao_Social as NM_Razao_Banco" +
	        "     ,Conhecimentos.NR_Conhecimento " +
	        "     ,Carteiras.oid_Carteira" +
	        "     ,Carteiras.cd_Carteira" +
	        "     ,Contas_Correntes.NR_conta_corrente" +
	        "     ,Tipos_Documentos.cd_tipo_documento" +
	        "     ,Duplicatas.nr_Duplicata" +
	        "     ,Duplicatas.nr_parcela" +
	        "     ,Duplicatas.oid_unidade" +
	        "     ,Duplicatas.nr_documento" +
	        "     ,Duplicatas.nr_bancario" +
	        "     ,Duplicatas.dt_vencimento" +
	        "     ,Duplicatas.dt_emissao" +
	        "     ,Duplicatas.vl_duplicata" +
	        "     ,Duplicatas.vl_juro_mora_dia" +
	        "     ,Duplicatas.vl_taxa_cobranca" +
	        "     ,Duplicatas.vl_multa" +
	        "     ,Duplicatas.vl_saldo " +
	        " FROM Duplicatas" +
	        "     ,pessoas" +
	        "     ,Pessoas Pessoas_Bancos" +
	        "     ,Cidades" +
	        "     ,Estados" +
	        "     ,tipos_documentos" +
	        "     ,Origens_Duplicatas" +
	        "     ,Conhecimentos" +
	        "     ,Carteiras" +
	        "     ,Contas_Correntes " +
	        " WHERE duplicatas.oid_pessoa = pessoas.oid_pessoa " +
	        "   AND pessoas.oid_Cidade = Cidades.oid_Cidade " +
	        "   AND Cidades.oid_regiao_Estado = Regioes_Estados.oid_regiao_estado " +
	        "   AND Regioes_Estados.oid_Estado = Estados.oid_Estado " +
	        "   AND duplicatas.oid_Carteira = Carteiras.oid_Carteira " +
	        "   AND duplicatas.oid_Duplicata = Origens_Duplicatas.oid_Duplicata " +
	        "   AND Origens_Duplicatas.oid_conhecimento = Conhecimentos.oid_Conhecimento " +
	        "   AND duplicatas.oid_tipo_documento = tipos_documentos.oid_tipo_documento " +
	        "   AND carteiras.oid_conta_corrente = contas_correntes.oid_conta_corrente " +
	        "   AND contas_correntes.oid_pessoa = pessoas_bancos.oid_pessoa " +
	        "   AND carteiras.dm_tipo_emissao <>'F' " +
	        "   AND Duplicatas.vl_saldo > 0 ";

	    if (1==2) {
	      sql +="   AND Duplicatas.nr_Bancario >'' ";
	    }

	    if (ed.getNr_Duplicata () != null) {
	      sql += " AND duplicatas.nr_duplicata >= " + ed.getNr_Duplicata ();
	    }
	    if (ed.getNr_Duplicata_Final () != null) {
	      sql += " AND duplicatas.NR_Duplicata <= " + ed.getNr_Duplicata_Final ();
	    }
	    if (ed.getOid_Pessoa () != null) {
	      sql += " AND duplicatas.oid_pessoa = '" + ed.getOid_Pessoa () + "'";
	    }
	    if (ed.getOid_Carteira () != null) {
	      sql += " AND duplicatas.oid_Carteira = " + ed.getOid_Carteira ();
	    }

	    ///sql += " ORDER BY Duplicatas.nr_duplicata ";
	    sql += " ORDER BY Conhecimentos.NR_Conhecimento DESC ";
    }else {

	        sql = " SELECT Duplicatas.OID_Duplicata" +
	        "     ,Pessoas.oid_pessoa" +
	        "     ,Pessoas.nr_cnpj_cpf" +
	        "     ,Pessoas.nm_razao_Social" +
	        "     ,Pessoas.NM_ENDERECO" +
	        "     ,Pessoas.NM_BAIRRO" +
	        "     ,Pessoas.NR_CEP" +
	        "     ,Pessoas.NM_INSCRICAO_ESTADUAL" +
	        "     ,Cidades.NM_Cidade" +
	        "     ,Estados.CD_Estado" +
	        "     ,Pessoas_Bancos.nm_razao_Social as NM_Razao_Banco" +
	        "     ,Carteiras.oid_Carteira" +
	        "     ,Carteiras.cd_Carteira" +
	        "     ,Contas_Correntes.NR_conta_corrente" +
	        "     ,Tipos_Documentos.cd_tipo_documento" +
	        "     ,Duplicatas.nr_Duplicata" +
	        "     ,Duplicatas.nr_parcela" +
	        "     ,Duplicatas.oid_unidade" +
	        "     ,Duplicatas.nr_documento" +
	        "     ,Duplicatas.nr_bancario" +
	        "     ,Duplicatas.dt_vencimento" +
	        "     ,Duplicatas.dt_emissao" +
	        "     ,Duplicatas.vl_duplicata" +
	        "     ,Duplicatas.vl_juro_mora_dia" +
	        "     ,Duplicatas.vl_taxa_cobranca" +
	        "     ,Duplicatas.vl_multa" +
	        "     ,Duplicatas.vl_saldo " +
	        " FROM Duplicatas" +
	        "     ,pessoas" +
	        "     ,Pessoas Pessoas_Bancos" +
	        "     ,Cidades" +
	        "     ,Estados" +
	        "     ,tipos_documentos" +
	        "     ,Carteiras" +
	        "     ,Contas_Correntes " +
	        " WHERE duplicatas.oid_pessoa = pessoas.oid_pessoa " +
	        "   AND pessoas.oid_Cidade = Cidades.oid_Cidade " +
	        "   AND Cidades.oid_regiao_Estado = Regioes_Estados.oid_regiao_estado " +
	        "   AND Regioes_Estados.oid_Estado = Estados.oid_Estado " +
	        "   AND duplicatas.oid_Carteira = Carteiras.oid_Carteira " +
	        "   AND duplicatas.oid_tipo_documento = tipos_documentos.oid_tipo_documento " +
	        "   AND carteiras.oid_conta_corrente = contas_correntes.oid_conta_corrente " +
	        "   AND contas_correntes.oid_pessoa = pessoas_bancos.oid_pessoa " +
	        "   AND carteiras.dm_tipo_emissao <>'F' " +
	        "   AND Duplicatas.vl_saldo > 0 ";

	    if (1==2) {
	      sql +="   AND Duplicatas.nr_Bancario >'' ";
	    }

	    if (ed.getNr_Duplicata () != null) {
	      sql += " AND duplicatas.nr_duplicata >= " + ed.getNr_Duplicata ();
	    }
	    if (ed.getNr_Duplicata_Final () != null) {
	      sql += " AND duplicatas.NR_Duplicata <= " + ed.getNr_Duplicata_Final ();
	    }
	    if (ed.getOid_Pessoa () != null) {
	      sql += " AND duplicatas.oid_pessoa = '" + ed.getOid_Pessoa () + "'";
	    }
	    if (ed.getOid_Carteira () != null) {
	      sql += " AND duplicatas.oid_Carteira = " + ed.getOid_Carteira ();
	    }

	    sql += " ORDER BY Duplicatas.nr_duplicata ";

    }
    ResultSet res = this.executasql.executarConsulta (sql.toString ());
    return res;
  }


  public DuplicataED geraNumeroBloqueto (DuplicataED ed) throws Excecoes {

    DuplicataED duplicataED = new DuplicataED ();
    String sql = null;
    String digitoCalc = "";
    long nrBloqueto = new Long (ed.getNR_Bloqueto_informado ()).longValue ();
    String nrDigito_Bloqueto = ed.getNR_Digito_Bloqueto ();

    duplicataED.setNR_Bloqueto_Calculado ("");
    sql = " SELECT Duplicatas.OID_Duplicata " +
        "       ,Carteiras.CD_Tipo_Carteira " +
        "       ,Carteiras.DM_Tipo_Impressao_Bloqueto " +
        " FROM Duplicatas " +
        "     ,Carteiras " +
        " WHERE Duplicatas.oid_Carteira = Carteiras.oid_Carteira " +
        "   AND Duplicatas.vl_saldo > 0 " +
        "   AND (Duplicatas.nr_Bancario = '' or Duplicatas.nr_Bancario is null) " +
        "   AND Duplicatas.nr_remessa = 0";

    if (ed.getNr_Duplicata () != null && ed.getNr_Duplicata ().longValue () > 0) {
      sql += " AND duplicatas.nr_duplicata >= " + ed.getNr_Duplicata ();
    }
    if (ed.getNr_Duplicata_Final () != null && ed.getNr_Duplicata_Final ().longValue () > 0) {
      sql += " AND duplicatas.NR_Duplicata <= " + ed.getNr_Duplicata_Final ();
    }
    if (JavaUtil.doValida (ed.getOid_Pessoa ())) {
      sql += " AND duplicatas.oid_pessoa = '" + ed.getOid_Pessoa () + "'";
    }
    if (ed.getOid_Carteira () != null && ed.getOid_Carteira ().longValue () > 0) {
      sql += " AND duplicatas.oid_Carteira = " + ed.getOid_Carteira ();
    }
    sql += " ORDER by Duplicatas.nr_duplicata ";

    ResultSet res = this.executasql.executarConsulta (sql.toString ());
    try {
      try {
        while (res.next ()) {
          String nrBloquetoCalculado = calculaNumeroBloqueto (nrBloqueto , ed.getCD_Banco () , res.getString ("CD_Tipo_Carteira"));

          if ("E".equals (res.getString ("DM_Tipo_Impressao_Bloqueto"))) {
            nrDigito_Bloqueto = digitoCalc;
            ed.setNR_Digito_Bloqueto (digitoCalc);
          }
          else
          if (res.first () && !nrDigito_Bloqueto.equals (digitoCalc)) {
            throw new Mensagens ("Digito do Bloqueto n�o Confere");
          }
          if (!JavaUtil.doValida (nrBloquetoCalculado)) {
            throw new Mensagens ("Bloqueto n�o calculado!");
          }

          if (util.doExiste ("Duplicatas" , "NR_Bancario = '" + nrBloquetoCalculado + "'")) {
            throw new Mensagens ("N�mero de Bloqueto [" + nrBloquetoCalculado + "] ja Existente!");
          }

          executasql.executarUpdate (" UPDATE Duplicatas SET " +
                                     " NR_Bancario='" + nrBloquetoCalculado + "'" +
                                     " WHERE oid_duplicata = '" + res.getString ("oid_duplicata") + "'");
          duplicataED.setNR_Bloqueto_Calculado ("OK");
          nrBloqueto++;
        }
        return duplicataED;
      }
      catch (SQLException e) {
        throw new Excecoes (e.getMessage () , e , getClass ().getName () , "geraNumeroBloqueto()");
      }
    }
    finally {
      util.closeResultset (res);
    }
  }

  public DuplicataED excluiNumeroBloqueto (DuplicataED ed) throws Excecoes {

    DuplicataED duplicataED = new DuplicataED ();
    String sql = null;
    sql = " SELECT Duplicatas.OID_Duplicata " +
        "       ,Carteiras.CD_Tipo_Carteira " +
        "       ,Carteiras.DM_Tipo_Impressao_Bloqueto " +
        " FROM Duplicatas " +
        "     ,Carteiras " +
        " WHERE Duplicatas.oid_Carteira = Carteiras.oid_Carteira " +
        "   AND Duplicatas.vl_saldo > 0 " +
        "   AND Duplicatas.nr_remessa = 0" +
        "   AND duplicatas.oid_Carteira = " + ed.getOid_Carteira ();

    if (ed.getNr_Duplicata () != null && ed.getNr_Duplicata ().longValue () > 0) {
      sql += " AND duplicatas.nr_duplicata >= " + ed.getNr_Duplicata ();
    }
    if (ed.getNr_Duplicata_Final () != null && ed.getNr_Duplicata_Final ().longValue () > 0) {
      sql += " AND duplicatas.NR_Duplicata <= " + ed.getNr_Duplicata_Final ();
    }
    sql += " ORDER by Duplicatas.nr_duplicata ";

    ResultSet res = this.executasql.executarConsulta (sql.toString ());
    try {
      try {
        while (res.next ()) {
          sql = " UPDATE Duplicatas SET NR_Bancario='' ";
          sql += " WHERE oid_duplicata = '" + res.getString ("oid_duplicata") + "'";
          executasql.executarUpdate (sql);
        }
        return duplicataED;
      }
      catch (SQLException e) {
        throw new Excecoes (e.getMessage () , e , getClass ().getName () , "excluiNumeroBloqueto()");
      }
    }
    finally {
      util.closeResultset (res);
    }
  }

  public String calculaNumeroBloqueto (long nrBloqueto , String cdBanco , String cdCarteira_Banco) throws Excecoes {

    if (!JavaUtil.doValida (cdBanco)) {
      throw new Mensagens ("C�digo do banco n�o informado para C�lculo do Bloqueto!");
    }
    String toReturn = "";
    return toReturn;
  }

  public DuplicataPesquisaED geraCod_Barras_Bradesco (DuplicataPesquisaED ed) throws Excecoes {

    String sql = null;
    ResultSet rsCD = null;
    String Cd_Banco = "237";
    String moeda = "9";
    String DV = "";
    String FV = "";
    String valor = "";
    String agencia = "1622";
    String carteira = "09";
    String N_numero = "";
    String Cc = "0000926";
    String zero = "0";
    String cd_barra = "";
    String linha_digitavel = "";
    String linha1 = "";
    String linha2 = "";
    String linha3 = "";
    String linha4 = "";
    String linha5 = "";

    long calc = 0;

    sql = " SELECT Duplicatas.* " +
        "       ,Carteiras.CD_Carteira " +
        " FROM Duplicatas, Carteiras " +
        " WHERE Duplicatas.oid_Carteira = Carteiras.oid_Carteira ";

    if (JavaUtil.doValida(String.valueOf(ed.getOid_Duplicata()))) {
      sql += " AND Duplicatas.oid_duplicata = " + ed.getOid_Duplicata ();
    }

    rsCD = this.executasql.executarConsulta (sql.toString ());

    try {
      try {
        while (rsCD.next ()) {
          // Hardcode para boletos bradesco
          // T�o 14/07/05

          // Nosso Nro.:
          N_numero = rsCD.getString ("oid_duplicata");
          if (N_numero.length () > 11) {
            N_numero = N_numero.substring (0 , 11);
          }
          N_numero = SeparaEndereco.preencheZeros (N_numero , 11);
          // Fator de Vcto:
          String dt_Vcto = new FormataDataBean ().getDT_FormataData (rsCD.getString ("dt_vencimento"));
          Calendar cal1 = Data.stringToCalendar ("07/10/1997" , "dd/MM/yyyy");
          Calendar cal2 = Data.stringToCalendar (dt_Vcto , "dd/MM/yyyy");
          FV = String.valueOf (Data.diferencaDatasDias (cal1 , cal2));

          // Valor:
          valor = SeparaEndereco.limpaCampo (rsCD.getString ("vl_saldo"));
          valor = SeparaEndereco.preencheZeros (valor , 10);

          // cd sem d�gito:
          cd_barra = Cd_Banco + moeda + FV + valor + agencia + carteira + N_numero + Cc + zero;

          // calculo do DV:
          calc = Integer.parseInt (cd_barra.substring (0 , 1)) * 4;
          calc += new Integer (cd_barra.substring (1 , 2)).intValue () * 3;
          calc += new Integer (cd_barra.substring (2 , 3)).intValue () * 2;
          calc += new Integer (cd_barra.substring (3 , 4)).intValue () * 9;
          calc += new Integer (cd_barra.substring (4 , 5)).intValue () * 8;
          calc += new Integer (cd_barra.substring (5 , 6)).intValue () * 7;
          calc += new Integer (cd_barra.substring (6 , 7)).intValue () * 6;
          calc += new Integer (cd_barra.substring (7 , 8)).intValue () * 5;
          calc += new Integer (cd_barra.substring (8 , 9)).intValue () * 4;
          calc += new Integer (cd_barra.substring (9 , 10)).intValue () * 3;
          calc += new Integer (cd_barra.substring (10 , 11)).intValue () * 2;
          calc += new Integer (cd_barra.substring (11 , 12)).intValue () * 9;
          calc += new Integer (cd_barra.substring (12 , 13)).intValue () * 8;
          calc += new Integer (cd_barra.substring (13 , 14)).intValue () * 7;
          calc += new Integer (cd_barra.substring (14 , 15)).intValue () * 6;
          calc += new Integer (cd_barra.substring (15 , 16)).intValue () * 5;
          calc += new Integer (cd_barra.substring (16 , 17)).intValue () * 4;
          calc += new Integer (cd_barra.substring (17 , 18)).intValue () * 3;
          calc += new Integer (cd_barra.substring (18 , 19)).intValue () * 2;
          calc += new Integer (cd_barra.substring (19 , 20)).intValue () * 9;
          calc += new Integer (cd_barra.substring (20 , 21)).intValue () * 8;
          calc += new Integer (cd_barra.substring (21 , 22)).intValue () * 7;
          calc += new Integer (cd_barra.substring (22 , 23)).intValue () * 6;
          calc += new Integer (cd_barra.substring (23 , 24)).intValue () * 5;
          calc += new Integer (cd_barra.substring (24 , 25)).intValue () * 4;
          calc += new Integer (cd_barra.substring (25 , 26)).intValue () * 3;
          calc += new Integer (cd_barra.substring (26 , 27)).intValue () * 2;
          calc += new Integer (cd_barra.substring (27 , 28)).intValue () * 9;
          calc += new Integer (cd_barra.substring (28 , 29)).intValue () * 8;
          calc += new Integer (cd_barra.substring (29 , 30)).intValue () * 7;
          calc += new Integer (cd_barra.substring (30 , 31)).intValue () * 6;
          calc += new Integer (cd_barra.substring (31 , 32)).intValue () * 5;
          calc += new Integer (cd_barra.substring (32 , 33)).intValue () * 4;
          calc += new Integer (cd_barra.substring (33 , 34)).intValue () * 3;
          calc += new Integer (cd_barra.substring (34 , 35)).intValue () * 2;
          calc += new Integer (cd_barra.substring (35 , 36)).intValue () * 9;
          calc += new Integer (cd_barra.substring (36 , 37)).intValue () * 8;
          calc += new Integer (cd_barra.substring (37 , 38)).intValue () * 7;
          calc += new Integer (cd_barra.substring (38 , 39)).intValue () * 6;
          calc += new Integer (cd_barra.substring (39 , 40)).intValue () * 5;
          calc += new Integer (cd_barra.substring (40 , 41)).intValue () * 4;
          calc += new Integer (cd_barra.substring (41 , 42)).intValue () * 3;
          calc += new Integer (cd_barra.substring (42 , 43)).intValue () * 2;
          long resto = 11 - (calc % 11);

          DV = String.valueOf (resto);
          if (resto <= 1 || resto >= 9) {
            DV = "1";
          }

          // codigo de barras:
          cd_barra = Cd_Banco + moeda + DV + FV + valor + agencia + carteira + N_numero + Cc + zero;
          ed.setCodigo_Barra (cd_barra);
          // ---------------------------------------------------------------------------------------------------------------------

          // Linha digit�vel:
          // campo 5: FV + valor
          linha5 = FV + valor;
          // campo 4: DV
          linha4 = DV + " ";
          // campo 3: pos 35-39 + . + pos 40-44 + DAC
          linha3 = cd_barra.substring (34 , 39) + cd_barra.substring (39 , 44);
          linha1 = linha1.substring (1 , linha1.length ());
          linha1 = SeparaEndereco.limpaCampo (linha1);
          linha1 = linha1.substring (0 , 5) + "." + linha1.substring (5 , linha1.length ()) + " ";
          // Linha digitavel:
          linha_digitavel = linha1 + linha2 + linha3 + linha4 + linha5;
          ed.setLinha_Digitavel (linha_digitavel);
          // ----------------------------------------------------------------------------------------------------------------------

          // Campos para BOLETO:
          ed.setAg_Cod (agencia + "-5/" + Cc + "-1");
          ed.setCarteira ("0" + carteira);
          // ed.setNosso_Numero(carteira+"/"+N_numero+"-"+un.substring(un.length()-1));
          String instrucoes = "PROTESTAR NO 5� DIA AP�S O VENCIMENTO.\n";
          instrucoes += "AP�S O VENCIMENTO COBRAR " + FormataValor.formataValorBT (rsCD.getDouble ("vl_multa") , 2) + "% DE MULTA.\n";
          instrucoes += "AP�S O VENCIMENTO MORA DE R$ " + FormataValor.formataValorBT ( (rsCD.getDouble ("vl_juro_mora_dia") * rsCD.getDouble ("vl_saldo")) / 100 , 2) + " AO DIA.\n";

          ed.setTX_Instrucoes (instrucoes);

          // sql = " update Duplicatas set NR_Bancario='" +
          // Nr_Bloqueto_Calculado + "'";
          // sql += " where oid_duplicata = '" +
          // res.getString("oid_duplicata") + "'";
          //
          // int i = executasql.executarUpdate(sql);

        }
        return ed;
      }
      catch (Exception e) {
        e.printStackTrace ();
        throw new Excecoes (e.getMessage () , e , getClass ().getName () , "geraCod_Barras(DuplicataED ed)");
      }
    }
    finally {
      util.closeResultset (rsCD);
    }
  }


}