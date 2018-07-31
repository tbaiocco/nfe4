package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.master.ed.DuplicataED;
import com.master.ed.Movimento_ConhecimentoED;
import com.master.ed.Movimento_DuplicataED;
import com.master.ed.Ocorrencia_DuplicataED;
import com.master.ed.Origem_DuplicataED;
import com.master.rn.Movimento_ConhecimentoRN;
import com.master.root.FormataDataBean;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;
import com.master.rn.Movimento_DuplicataRN;

public class Origem_DuplicataBD {

  private ExecutaSQL executasql;
  FormataDataBean DataFormatada = new FormataDataBean ();

  Parametro_FixoED parametro_FixoED = new Parametro_FixoED ();
  double vl_total = 0;
  double vl_estorno = 0;

  public Origem_DuplicataBD (ExecutaSQL sql) {
    this.executasql = sql;
  }

  public void inclui (Origem_DuplicataED ed) throws Excecoes {

    String sql = null;
    String chave = null;
    try {

      chave = (ed.getOID_Duplicata () + ed.getOID_Conhecimento ());
      if ("I".equals (ed.getDM_Situacao ())) { // inclui avulso
        chave = String.valueOf (System.currentTimeMillis ()).toString ();
      }
      else {
        ed.setDM_Situacao ("A");
      }
      sql = " insert into Origens_Duplicatas (OID_Origem_Duplicata, OID_Conhecimento, OID_Duplicata, DT_Origem_Duplicata, HR_Origem_Duplicata, DM_Situacao ) values ";
      sql += "('" + chave + "','" + ed.getOID_Conhecimento () + "','" + ed.getOID_Duplicata () + "','" + ed.getDT_Origem_Duplicata () + "','" + ed.getHR_Origem_Duplicata () + "','" + ed.getDM_Situacao () + "')";
      // System.out.println (sql);

      executasql.executarUpdate (sql);
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "inclui(Origem_DuplicataED ed)");
    }
  }

  public void altera (Origem_DuplicataED ed) throws Excecoes {

    String sql = null;
    try {

      sql = " update Origens_Duplicatas set OID_Duplicata= " + ed.getOID_Duplicata () + "'";
      sql += " where oid_Origem_Duplicata = '" + ed.getOID_Origem_Duplicata () + "'";

      executasql.executarUpdate (sql);
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "altera(Origem_DuplicataED ed)");
    }
  }

  public void estorna (Origem_DuplicataED ed) throws Excecoes {

    String sql = null;

    try {
      FormataDataBean DataFormatada = new FormataDataBean ();
      Parametro_FixoED Parametro_FixoED = new Parametro_FixoED ();

      if (JavaUtil.doValida (ed.getOID_Conhecimento ())) {
        sql = " UPDATE Conhecimentos SET DM_Situacao = 'G' " +
            " ,oid_Lote_Faturamento = 0 " +
            " ,DM_QUEBRA_FATURAMENTO = '7' " +
            " ,NR_Duplicata = 0 ";
        if (JavaUtil.doValida (ed.getOID_Pessoa ())) {
          sql += " ,OID_Pessoa_Pagador= '" + ed.getOID_Pessoa () + "'";
        }
        sql += " WHERE OID_Conhecimento = '" + ed.getOID_Conhecimento () + "'";
        executasql.executarUpdate (sql);
      }
      if (JavaUtil.doValida (ed.getOID_Conhecimento_Faturamento ())) {

        sql = " UPDATE Conhecimentos_Faturamentos SET DM_Situacao = 'L', oid_Duplicata = null ";
        sql += " WHERE OID_Conhecimento_Faturamento = " + ed.getOID_Conhecimento_Faturamento ();

        executasql.executarUpdate (sql);

        sql = " UPDATE Conhecimentos_Internacionais SET DM_Situacao = 'L' ";
        sql += " WHERE Conhecimentos_Internacionais.OID_Conhecimento = Conhecimentos_Faturamentos.OID_Conhecimento";
        sql += " AND Conhecimentos_Faturamentos.OID_Conhecimento_Faturamento = " + ed.getOID_Conhecimento_Faturamento ();

        executasql.executarUpdate (sql);
      }

      if (JavaUtil.doValida (ed.getOid_Nota_Fiscal())) {
          sql = " UPDATE Notas_Fiscais SET DM_Faturado = 'N' ";
          sql += " WHERE OID_Nota_fiscal = '" + ed.getOid_Nota_Fiscal() + "'";
          executasql.executarUpdate (sql);
        }

      Movimento_DuplicataED edComp = new Movimento_DuplicataED ();

      sql = " SELECT Duplicatas.OID_Duplicata, Duplicatas.OID_Carteira, Duplicatas.DT_Emissao, " +
          "Duplicatas.VL_Saldo " +
          "FROM Origens_Duplicatas, Duplicatas ";
      sql += " WHERE Duplicatas.OID_Duplicata = Origens_Duplicatas.OID_Duplicata ";
      sql += " AND Origens_Duplicatas.oid_Origem_Duplicata = '" + ed.getOID_Origem_Duplicata () + "'";

      ResultSet rsTP = null;
      rsTP = this.executasql.executarConsulta (sql);
      while (rsTP.next ()) {

        edComp.setOid_Duplicata (rsTP.getLong("OID_Duplicata"));
        edComp.setOID_Carteira (new Integer (rsTP.getInt ("OID_Carteira")));
        edComp.setDT_Movimento (ed.getDt_stamp ());
        edComp.setVl_Duplicata (new Double (rsTP.getDouble ("VL_Saldo")));

        edComp.setDt_Emissao (rsTP.getString ("DT_Emissao"));
        DataFormatada.setDT_FormataData (edComp.getDt_Emissao ());
        edComp.setDt_Emissao (DataFormatada.getDT_FormataData ());

        edComp.setDM_Gera_Movimento ("C");
      }

      if (JavaUtil.doValida (ed.getOID_Conhecimento_Faturamento ())) {
        sql = " select VL_Faturar_Conversao as VL_Total_Frete, '0' as VL_Desconto from conhecimentos_Faturamentos ";
        sql += " where oid_conhecimento_Faturamento = " + ed.getOID_Conhecimento_Faturamento ();

      }
      else {
        sql = " select * from conhecimentos ";
        sql += " where oid_conhecimento = '" + ed.getOID_Conhecimento () + "'";
      }

      rsTP = null;
      rsTP = this.executasql.executarConsulta (sql);
      long valMovimento = 0;

      while (rsTP.next ()) {
        edComp.setVL_Credito (new Double (rsTP.getDouble ("VL_Total_Frete") - rsTP.getDouble ("VL_Desconto")));
        edComp.setVL_Debito (new Double (valMovimento));
      }
      if (JavaUtil.doValida (ed.getOid_Nota_Fiscal())) {

	      sql = " select VL_Nota_Fiscal from Notas_Fiscais ";
	      sql += " where oid_Nota_Fiscal = '" + ed.getOid_Nota_Fiscal() + "'";

	      rsTP = null;
	      rsTP = this.executasql.executarConsulta (sql);

	      while (rsTP.next ()) {
	        edComp.setVL_Credito (new Double (rsTP.getDouble ("VL_Nota_Fiscal")));
	        edComp.setVL_Debito (new Double (valMovimento));
	      }
      }
      edComp.setOID_Tipo_Instrucao (new Integer (Parametro_FixoED.getOID_Tipo_Instrucao_Estorno ()));
      if (Parametro_FixoED.getOID_Tipo_Instrucao_Estorno () == 0) {
        sql = " select * from Tipos_Instrucoes ";
        sql += " where CD_Tipo_Instrucao = 'EST'";

        rsTP = null;
        rsTP = this.executasql.executarConsulta (sql);

        while (rsTP.next ()) {
          edComp.setOID_Tipo_Instrucao (new Integer (rsTP.getInt ("OID_Tipo_Instrucao")));
        }
      }


      /* double valPgto = 0;
       valPgto = edComp.getVL_Credito().doubleValue();
       double valComp = edComp.getVl_Duplicata().doubleValue();*/

      //      if (valPgto > valComp){
      //        Excecoes exc = new Excecoes();
      //        exc.setMensagem("Valor do pagamento não pode ser maior que o
      // valor devido");
      //        exc.setClasse(this.getClass().getName());
      //        exc.setMetodo("Inclui(Origem_DuplicataBD)");
      //        throw exc;
      //      }

      String chave = null;
      long valOid = 0;

      ResultSet rs = executasql.executarConsulta ("select max(NR_Sequencia_Duplicata) as result from Movimentos_Duplicatas where oid_Duplicata = " + edComp.getOid_Duplicata ());
      //pega proximo valor da chave
      while (rs.next ()) {
        valOid = rs.getLong ("result");
        edComp.setNR_Sequencia_Duplicata (++valOid);
      }

      chave = (String.valueOf (edComp.getOid_Duplicata ()) + String.valueOf (edComp.getNR_Sequencia_Duplicata ()));

      boolean DM_Chave_Unica = true;
      while (DM_Chave_Unica) {
          sql = " SELECT Movimentos_Duplicatas.OID_Movimento_Duplicata " +
                " FROM Movimentos_Duplicatas " +
                " WHERE Movimentos_Duplicatas.OID_Movimento_Duplicata = '" + chave + "'";
          ResultSet res = this.executasql.executarConsulta(sql);

          DM_Chave_Unica = false;
          while (res.next()) {
              DM_Chave_Unica = true;
              valOid = valOid + 1;
              edComp.setNR_Sequencia_Duplicata (valOid);
              chave = (String.valueOf (edComp.getOid_Duplicata ()) + String.valueOf (edComp.getNR_Sequencia_Duplicata ()));
          }
      }


      sql = " insert into Movimentos_Duplicatas " + "(OID_Movimento_Duplicata, DT_Movimento, NR_Sequencia_Duplicata, VL_Credito, VL_Debito, "
          + "DT_STAMP, USUARIO_STAMP, DM_STAMP, OID_Duplicata, OID_Tipo_Instrucao, OID_Carteira, OID_Bordero) values " +
          "('" + chave + "',";
      sql += "'" + edComp.getDT_Movimento () + "',";
      sql += edComp.getNR_Sequencia_Duplicata () + ",";
      sql += edComp.getVL_Credito () == null ? "null," : edComp.getVL_Credito () + ",";
      sql += edComp.getVL_Debito () == null ? "null," : edComp.getVL_Debito () + ",";
      sql += "'" + edComp.getDt_stamp () + "',";
      sql += "'" + edComp.getUsuario_Stamp () + "',";
      sql += "'" + edComp.getDm_Stamp () + "',";
      sql += edComp.getOid_Duplicata () + ",";
      sql += edComp.getOID_Tipo_Instrucao () + ",";
      sql += edComp.getOID_Carteira () + ",0)";

      executasql.executarUpdate (sql);

      double dValSub = 0;

      dValSub = edComp.getVL_Credito ().doubleValue ();

      double dValComp = edComp.getVl_Duplicata ().doubleValue ();

      DuplicataED edCompDuplicata = new DuplicataED ();

      edCompDuplicata.setVl_Saldo (new Double (dValComp - dValSub));
      double zero = 0;
      if (edCompDuplicata.getVl_Saldo ().doubleValue () <= zero) {
        edCompDuplicata.setVl_Saldo (new Double (zero));
      }

      edCompDuplicata.setOid_Duplicata (edComp.getOid_Duplicata ());


      sql = " UPDATE Origens_Duplicatas set DM_Situacao = 'E' ";
      sql += " WHERE oid_Origem_Duplicata = '" + ed.getOID_Origem_Duplicata () + "'";

      executasql.executarUpdate (sql);

      String dm_situacao="A";
      if (edCompDuplicata.getVl_Saldo ().doubleValue()==0){
        dm_situacao="C";  //cancelada
        sql = " SELECT Origens_Duplicatas.oid_Origem_Duplicata " +
            " FROM   Origens_Duplicatas " +
            " WHERE  Origens_Duplicatas.DM_Situacao <> 'E' " +
            " AND    Origens_Duplicatas.oid_Duplicata = " + edCompDuplicata.getOid_Duplicata ();
        // System.out.println (sql);

        ResultSet rs2 = executasql.executarConsulta (sql);
        //pega proximo valor da chave
        if (rs2.next ()) {
          dm_situacao = "P";
        }
      }

      if ("C".equals(dm_situacao)){
          sql = " update Duplicatas set " +
          		"        DM_Situacao = '" + dm_situacao + "'" +
                "        , vl_saldo =   " + edCompDuplicata.getVl_Saldo () +
                "        , dt_cancelamento = '" + Data.getDataDMY() + "'" +
                " WHERE oid_Duplicata = ";
          sql += edCompDuplicata.getOid_Duplicata ();
      }else{
          sql = " update Duplicatas set " +
          		"        DM_Situacao = '" + dm_situacao + "'" +
          		"        , vl_saldo =   " + edCompDuplicata.getVl_Saldo () +
                " WHERE oid_Duplicata = ";
          sql += edCompDuplicata.getOid_Duplicata ();
      }

      // System.out.println (sql);

      executasql.executarUpdate (sql);

      Ocorrencia_DuplicataED ocodupED = new Ocorrencia_DuplicataED ();

      ResultSet rsoco = executasql.executarConsulta ("select max(oid_Ocorrencia_Duplicata) as result from Ocorrencias_Duplicatas");

      //pega proximo valor da chave
      while (rsoco.next ()) {
        valOid = rsoco.getInt ("result");
      }

      ocodupED.setOID_Ocorrencia_Duplicata (++valOid);

      sql = " insert into Ocorrencias_Duplicatas (OID_OCORRENCIA_DUPLICATA, OID_DUPLICATA, OID_TIPO_OCORRENCIA, DT_OCORRENCIA_DUPLICATA, HR_OCORRENCIA_DUPLICATA, TX_OCORRENCIA_DUPLICATA ) values ";

      String tx_Observacao="Estorno de Conhecimento";
      if (ed.getTX_Observacao()!=null && !ed.getTX_Observacao().equals("null")){
        tx_Observacao="Estorno de Conhecimento. " + ed.getTX_Observacao();
      }


      sql += "(" + ocodupED.getOID_Ocorrencia_Duplicata () + "," + edComp.getOid_Duplicata () + "," + Parametro_FixoED.getOID_Tipo_Ocorrencia_Estorno_CTRC () + ",'" + Data.getDataDMY () + "','"
          + Data.getHoraHM () + "','" + tx_Observacao + "')";

      executasql.executarUpdate (sql);

      ////
    }
    catch (SQLException e) {
      e.printStackTrace ();
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "estorna(Origem_DuplicataED ed)");
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "estorna(Origem_DuplicataED ed)");
    }
  }

  public void deleta (Origem_DuplicataED ed) throws Excecoes {
	  String sql = null;
	  try{
		  sql = "DELETE from origens_duplicatas where Origens_Duplicatas.DM_Situacao ='E' " +
		  		" AND   Origens_Duplicatas.OID_Duplicata ='" + ed.getOID_Duplicata () + "'";
// System.out.println(sql);
		  this.executasql.executarUpdate(sql);

	  } catch (Exception e) {
		  throw new Excecoes (e.getMessage () , e , getClass ().getName () , "deleta(Origem_DuplicataED ed)");
	  }
  }

  public void estornaTodos (Origem_DuplicataED ed) throws Excecoes {

    String sql = null;

    try {

      sql = " SELECT Origens_Duplicatas.OID_Origem_Duplicata, Origens_Duplicatas.DM_Situacao, Origens_Duplicatas.Oid_Conhecimento, " +
      		" Origens_Duplicatas.Oid_Duplicata, Origens_Duplicatas.Oid_Nota_Fiscal " +
          " FROM  Origens_Duplicatas " +
          " WHERE Origens_Duplicatas.DM_Situacao ='A' " +
          " AND   Origens_Duplicatas.OID_Duplicata ='" + ed.getOID_Duplicata () + "'";

      ResultSet res = this.executasql.executarConsulta (sql);

      while (res.next ()) {

        Origem_DuplicataED OrigDup = new Origem_DuplicataED ();
        OrigDup.setOID_Origem_Duplicata (res.getString ("OID_Origem_Duplicata"));
        OrigDup.setOID_Duplicata (res.getString ("OID_Duplicata"));
        OrigDup.setOID_Conhecimento (res.getString ("Oid_Conhecimento"));
        OrigDup.setOid_Nota_Fiscal(res.getString ("Oid_Nota_Fiscal"));
        this.estorna (OrigDup);
      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "estornaTodos(Origem_DuplicataED ed)");
    }
  }

  public ArrayList lista (Origem_DuplicataED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();
    ResultSet res = null;
    int tem_nacional = 0 , tem_internacional = 0 , tem_ordem_frete_terceiro = 0;
    String dm_pode_estornar= this.verificaPodeEstornar(ed.getOID_Duplicata());

    try {



//        	SELECT PARA CONHECIMENTOS_DUPLICATAS - GELSON
      if ("Nacional-Internacional".equals (parametro_FixoED.getDM_Tipo_Conhecimento ()) ||
          "Nacional".equals (parametro_FixoED.getDM_Tipo_Conhecimento ())) {
        sql =
            " SELECT OID_Origem_Duplicata, Origens_Duplicatas.DM_Situacao, Origens_Duplicatas.oid_Movimento_Duplicata, DT_Origem_Duplicata, HR_Origem_Duplicata, Conhecimentos.NR_Conhecimento, Conhecimentos.DT_Emissao, Conhecimentos.VL_Total_Frete, Duplicatas.NR_Duplicata, Unidades.CD_Unidade, Pessoa_Remetente.NM_Razao_Social as NM_Razao_Social_Remetente, Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario, Duplicatas.OID_Duplicata from Origens_Duplicatas, Conhecimentos, Unidades, Pessoas Pessoa_Remetente, Pessoas Pessoa_Destinatario, Duplicatas ";
        sql += " WHERE Unidades.oid_Unidade = Conhecimentos.oid_Unidade ";
        sql += " AND Conhecimentos.oid_Pessoa = Pessoa_Remetente.oid_Pessoa ";
        sql += " AND Conhecimentos.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa ";
        sql += " AND Origens_Duplicatas.OID_Conhecimento = Conhecimentos.OID_Conhecimento ";
        sql += " AND Origens_Duplicatas.OID_Duplicata = Duplicatas.OID_Duplicata ";

        if (String.valueOf (ed.getNR_Conhecimento ()) != null && !String.valueOf (ed.getNR_Conhecimento ()).equals ("0") && !String.valueOf (ed.getNR_Conhecimento ()).equals ("null")) {
          sql += " and Conhecimentos.NR_Conhecimento = " + ed.getNR_Conhecimento ();
        }
        if (String.valueOf (ed.getOID_Duplicata ()) != null && !String.valueOf (ed.getOID_Duplicata ()).equals ("0") && !String.valueOf (ed.getOID_Duplicata ()).equals ("null")) {
          sql += " and Duplicatas.OID_Duplicata = '" + ed.getOID_Duplicata () + "'";
        }
        if (String.valueOf (ed.getOID_Unidade ()) != null && !String.valueOf (ed.getOID_Unidade ()).equals ("0") && !String.valueOf (ed.getOID_Unidade ()).equals ("null")) {
          sql += " and Conhecimentos.OID_Unidade = " + ed.getOID_Unidade ();
        }
        if (String.valueOf (ed.getOID_Pessoa ()) != null && !String.valueOf (ed.getOID_Pessoa ()).equals ("") && !String.valueOf (ed.getOID_Pessoa ()).equals ("null")) {
          sql += " and Conhecimentos.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
        }
        if (String.valueOf (ed.getOID_Pessoa_Destinatario ()) != null && !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("")
            && !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("null")) {
          sql += " and Conhecimentos.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario () + "'";
        }
        if (String.valueOf (ed.getDT_Emissao ()) != null && !String.valueOf (ed.getDT_Emissao ()).equals ("") && !String.valueOf (ed.getDT_Emissao ()).equals ("null")) {
          sql += " and Conhecimentos.DT_Emissao = '" + ed.getDT_Emissao () + "'";
        }
        if (String.valueOf (ed.getDT_Origem_Duplicata ()) != null && !String.valueOf (ed.getDT_Origem_Duplicata ()).equals ("") && !String.valueOf (ed.getDT_Origem_Duplicata ()).equals ("null")) {
          sql += " and Origens_Duplicatas.DT_Origem_Duplicata = '" + ed.getDT_Origem_Duplicata () + "'";
        }
        if (String.valueOf (ed.getOid_Nota_Fiscal ()) != null && !String.valueOf (ed.getOid_Nota_Fiscal ()).equals ("") && !String.valueOf (ed.getOid_Nota_Fiscal ()).equals ("null")) {
          sql += " and Origens_Duplicatas.Oid_Nota_Fiscal = '" + ed.getOid_Nota_Fiscal () + "'";
        }

        sql += " Order by Conhecimentos.NR_Conhecimento ";

        res = this.executasql.executarConsulta (sql);

        //popula
        while (res.next ()) {
          Origem_DuplicataED edVolta = new Origem_DuplicataED ();
          edVolta.setDM_Pode_Estornar(dm_pode_estornar);
          edVolta.setOID_Origem_Duplicata (res.getString ("OID_Origem_Duplicata"));
          edVolta.setDM_Tipo_Origem ("CTRC");
          edVolta.setDT_Origem_Duplicata (res.getString ("DT_Origem_Duplicata"));
          DataFormatada.setDT_FormataData (edVolta.getDT_Origem_Duplicata ());
          edVolta.setDT_Origem_Duplicata (DataFormatada.getDT_FormataData ());

          edVolta.setHR_Origem_Duplicata (res.getString ("HR_Origem_Duplicata"));
          edVolta.setNR_Conhecimento (res.getLong ("NR_Conhecimento"));

          edVolta.setDT_Emissao (res.getString ("DT_Emissao"));
          DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
          edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());

          edVolta.setNR_Duplicata (res.getLong ("NR_Duplicata"));
          edVolta.setCD_Unidade (res.getString ("CD_Unidade"));
          edVolta.setNM_Pessoa_Remetente (res.getString ("NM_Razao_Social_Remetente"));
          edVolta.setNM_Pessoa_Destinatario (res.getString ("NM_Razao_Social_Destinatario"));
          edVolta.setOID_Duplicata (res.getString ("OID_Duplicata"));
          edVolta.setDM_Situacao (res.getString ("DM_Situacao"));
          edVolta.setVL_Total_Frete (res.getDouble ("VL_Total_Frete"));
          vl_total += res.getDouble ("VL_Total_Frete");
          if ("E".equals (res.getString ("DM_Situacao"))) {
            vl_estorno += res.getDouble ("VL_Total_Frete");
          }

          tem_nacional++;
          list.add (edVolta);
        }
//    	SELECT PARA NOTAS_FISCAIS
        sql =
            " SELECT OID_Origem_Duplicata, Origens_Duplicatas.DM_Situacao, " +
            " Origens_Duplicatas.oid_Movimento_Duplicata, DT_Origem_Duplicata, HR_Origem_Duplicata, " +
            " Notas_Fiscais.NR_Nota_Fiscal, Notas_Fiscais.DT_Emissao, " +
            " Notas_Fiscais.VL_Nota_Fiscal, Duplicatas.NR_Duplicata, Unidades.CD_Unidade, " +
            " Pessoa_Remetente.NM_Razao_Social as NM_Razao_Social_Remetente, " +
            " Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario, " +
            " Duplicatas.OID_Duplicata " +
            " from Origens_Duplicatas, Notas_Fiscais, Unidades, Pessoas Pessoa_Remetente, Pessoas Pessoa_Destinatario, Duplicatas ";
        sql += " WHERE Unidades.oid_Unidade = Notas_Fiscais.oid_Unidade ";
        sql += " AND Notas_Fiscais.oid_Pessoa = Pessoa_Remetente.oid_Pessoa ";
        sql += " AND Notas_Fiscais.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa ";
        sql += " AND Origens_Duplicatas.OID_Nota_Fiscal = Notas_Fiscais.OID_Nota_Fiscal ";
        sql += " AND Origens_Duplicatas.OID_Duplicata = Duplicatas.OID_Duplicata ";

        if (String.valueOf (ed.getNR_Nota_Fiscal ()) != null && !String.valueOf (ed.getNR_Nota_Fiscal ()).equals ("0") && !String.valueOf (ed.getNR_Nota_Fiscal ()).equals ("null")) {
          sql += " and Notas_Fiscais.NR_Nota_Fiscal = " + ed.getNR_Nota_Fiscal ();
        }
        if (String.valueOf (ed.getOID_Duplicata ()) != null && !String.valueOf (ed.getOID_Duplicata ()).equals ("0") && !String.valueOf (ed.getOID_Duplicata ()).equals ("null")) {
          sql += " and Duplicatas.OID_Duplicata = '" + ed.getOID_Duplicata () + "'";
        }
        if (String.valueOf (ed.getOID_Unidade ()) != null && !String.valueOf (ed.getOID_Unidade ()).equals ("0") && !String.valueOf (ed.getOID_Unidade ()).equals ("null")) {
          sql += " and Notas_Fiscais.OID_Unidade = " + ed.getOID_Unidade ();
        }
        if (String.valueOf (ed.getOID_Pessoa ()) != null && !String.valueOf (ed.getOID_Pessoa ()).equals ("") && !String.valueOf (ed.getOID_Pessoa ()).equals ("null")) {
          sql += " and Notas_Fiscais.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
        }
        if (String.valueOf (ed.getOID_Pessoa_Destinatario ()) != null && !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("")
            && !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("null")) {
          sql += " and Notas_Fiscais.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario () + "'";
        }
        if (String.valueOf (ed.getDT_Emissao ()) != null && !String.valueOf (ed.getDT_Emissao ()).equals ("") && !String.valueOf (ed.getDT_Emissao ()).equals ("null")) {
          sql += " and Notas_Fiscais.DT_Emissao = '" + ed.getDT_Emissao () + "'";
        }
        if (String.valueOf (ed.getDT_Origem_Duplicata ()) != null && !String.valueOf (ed.getDT_Origem_Duplicata ()).equals ("") && !String.valueOf (ed.getDT_Origem_Duplicata ()).equals ("null")) {
          sql += " and Origens_Duplicatas.DT_Origem_Duplicata = '" + ed.getDT_Origem_Duplicata () + "'";
        }
        if (String.valueOf (ed.getOid_Nota_Fiscal ()) != null && !String.valueOf (ed.getOid_Nota_Fiscal ()).equals ("") && !String.valueOf (ed.getOid_Nota_Fiscal ()).equals ("null")) {
          sql += " and Origens_Duplicatas.Oid_Nota_Fiscal = '" + ed.getOid_Nota_Fiscal () + "'";
        }

        sql += " Order by Notas_Fiscais.NR_Nota_Fiscal ";

        res = this.executasql.executarConsulta (sql);

        //popula
        while (res.next ()) {
          Origem_DuplicataED edVolta = new Origem_DuplicataED ();
          edVolta.setDM_Pode_Estornar(dm_pode_estornar);

          edVolta.setOID_Origem_Duplicata (res.getString ("OID_Origem_Duplicata"));
          edVolta.setDM_Tipo_Origem ("NOTA FISCAL");
          edVolta.setDT_Origem_Duplicata (res.getString ("DT_Origem_Duplicata"));
          DataFormatada.setDT_FormataData (edVolta.getDT_Origem_Duplicata ());
          edVolta.setDT_Origem_Duplicata (DataFormatada.getDT_FormataData ());

          edVolta.setHR_Origem_Duplicata (res.getString ("HR_Origem_Duplicata"));
          edVolta.setNR_Nota_Fiscal (res.getLong ("NR_Nota_Fiscal"));

          edVolta.setDT_Emissao (res.getString ("DT_Emissao"));
          DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
          edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());

          edVolta.setNR_Duplicata (res.getLong ("NR_Duplicata"));
          edVolta.setCD_Unidade (res.getString ("CD_Unidade"));
          edVolta.setNM_Pessoa_Remetente (res.getString ("NM_Razao_Social_Remetente"));
          edVolta.setNM_Pessoa_Destinatario (res.getString ("NM_Razao_Social_Destinatario"));
          edVolta.setOID_Duplicata (res.getString ("OID_Duplicata"));
          edVolta.setDM_Situacao (res.getString ("DM_Situacao"));
          edVolta.setVL_Total_Frete (res.getDouble ("VL_Nota_Fiscal"));
          vl_total += res.getDouble ("VL_Nota_Fiscal");
          if ("E".equals (res.getString ("DM_Situacao"))) {
            vl_estorno += res.getDouble ("VL_Nota_Fiscal");
          }

          tem_nacional++;
          list.add (edVolta);
        }
        if (tem_nacional > 0) {
          Origem_DuplicataED edVolta = new Origem_DuplicataED ();
          edVolta.setDM_Pode_Estornar(dm_pode_estornar);

          edVolta.setNM_Pessoa_Remetente (" ");
          edVolta.setDT_Emissao (" ");
          edVolta.setNM_Pessoa_Destinatario ("Total Emissão->> ");
          edVolta.setVL_Total_Frete (vl_total);
          list.add (edVolta);

          edVolta = new Origem_DuplicataED ();
          edVolta.setNM_Pessoa_Remetente (" ");
          edVolta.setDT_Emissao (" ");
          edVolta.setNM_Pessoa_Destinatario ("Total Estorno->> ");
          edVolta.setVL_Total_Frete (vl_estorno);
          list.add (edVolta);

          edVolta = new Origem_DuplicataED ();
          edVolta.setNM_Pessoa_Remetente (" ");
          edVolta.setDT_Emissao (" ");
          edVolta.setNM_Pessoa_Destinatario ("Total Liquido->> ");
          edVolta.setVL_Total_Frete (vl_total - vl_estorno);
          list.add (edVolta);
        }

      }

      if ("Nacional-Internacional".equals (parametro_FixoED.getDM_Tipo_Conhecimento ()) ||
          "Internacional".equals (parametro_FixoED.getDM_Tipo_Conhecimento ())) {
        sql = " SELECT OID_Origem_Duplicata, Origens_Duplicatas.DM_Situacao, DT_Origem_Duplicata, " +
            "HR_Origem_Duplicata, CI.NR_Conhecimento, CF.DT_Emissao, " +
            "CF.VL_Faturar as VL_Total_Frete, Duplicatas.NR_Duplicata, " +
            "Pessoa_Remetente.NM_Razao_Social as NM_Razao_Social_Remetente, " +
            "Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario, " +
            "Duplicatas.OID_Duplicata, Unidades.CD_Unidade ";

        sql += "from Origens_Duplicatas, Conhecimentos_Internacionais CI, Conhecimentos_Faturamentos CF, " +
            "Pessoas Pessoa_Remetente, Pessoas Pessoa_Destinatario, Duplicatas, Unidades ";

        sql += " WHERE Unidades.oid_Unidade = CI.oid_Unidade ";
        sql += " AND CI.oid_Pessoa = Pessoa_Remetente.oid_Pessoa ";
        sql += " AND CI.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa ";
        sql += " AND CI.oid_Conhecimento = CF.oid_Conhecimento ";
        sql += " AND Origens_Duplicatas.oid_Conhecimento_Faturamento = CF.oid_Conhecimento_Faturamento ";
        sql += " AND Origens_Duplicatas.OID_Duplicata = Duplicatas.OID_Duplicata ";

        if (String.valueOf (ed.getNR_Conhecimento ()) != null && !String.valueOf (ed.getNR_Conhecimento ()).equals ("0") && !String.valueOf (ed.getNR_Conhecimento ()).equals ("null")) {
          sql += " and CI.NR_Conhecimento = " + ed.getNR_Conhecimento ();
        }
        if (String.valueOf (ed.getOID_Duplicata ()) != null && !String.valueOf (ed.getOID_Duplicata ()).equals ("0") && !String.valueOf (ed.getOID_Duplicata ()).equals ("null")) {
          sql += " and Duplicatas.OID_Duplicata = '" + ed.getOID_Duplicata () + "'";
        }
        if (String.valueOf (ed.getOID_Unidade ()) != null && !String.valueOf (ed.getOID_Unidade ()).equals ("0") && !String.valueOf (ed.getOID_Unidade ()).equals ("null")) {
          sql += " and CI.OID_Unidade = " + ed.getOID_Unidade ();
        }
        if (String.valueOf (ed.getOID_Pessoa ()) != null && !String.valueOf (ed.getOID_Pessoa ()).equals ("") && !String.valueOf (ed.getOID_Pessoa ()).equals ("null")) {
          sql += " and CI.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
        }
        if (String.valueOf (ed.getOID_Pessoa_Destinatario ()) != null && !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("")
            && !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("null")) {
          sql += " and CI.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario () + "'";
        }
        if (String.valueOf (ed.getDT_Emissao ()) != null && !String.valueOf (ed.getDT_Emissao ()).equals ("") && !String.valueOf (ed.getDT_Emissao ()).equals ("null")) {
          sql += " and CF.DT_Emissao = '" + ed.getDT_Emissao () + "'";
        }
        if (String.valueOf (ed.getDT_Origem_Duplicata ()) != null && !String.valueOf (ed.getDT_Origem_Duplicata ()).equals ("") && !String.valueOf (ed.getDT_Origem_Duplicata ()).equals ("null")) {
          sql += " and Origens_Duplicatas.DT_Origem_Duplicata = '" + ed.getDT_Origem_Duplicata () + "'";
        }
        if (String.valueOf (ed.getOid_Nota_Fiscal ()) != null && !String.valueOf (ed.getOid_Nota_Fiscal ()).equals ("") && !String.valueOf (ed.getOid_Nota_Fiscal ()).equals ("null")) {
          sql += " and Origens_Duplicatas.Oid_Nota_Fiscal = '" + ed.getOid_Nota_Fiscal () + "'";
        }

        sql += " Order by CI.NR_Conhecimento ";

        res = null;
        res = this.executasql.executarConsulta (sql);
        while (res.next ()) {
          Origem_DuplicataED edVolta = new Origem_DuplicataED ();
          edVolta.setDM_Pode_Estornar(dm_pode_estornar);

          edVolta.setOID_Origem_Duplicata (res.getString ("OID_Origem_Duplicata"));
          edVolta.setCD_Unidade (res.getString ("CD_Unidade"));
          edVolta.setDM_Tipo_Origem ("CRT");

          edVolta.setDT_Origem_Duplicata (res.getString ("DT_Origem_Duplicata"));
          DataFormatada.setDT_FormataData (edVolta.getDT_Origem_Duplicata ());
          edVolta.setDT_Origem_Duplicata (DataFormatada.getDT_FormataData ());

          edVolta.setHR_Origem_Duplicata (res.getString ("HR_Origem_Duplicata"));
          edVolta.setNR_Conhecimento (res.getLong ("NR_Conhecimento"));

          edVolta.setDT_Emissao (res.getString ("DT_Emissao"));
          DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
          edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());

          edVolta.setNR_Duplicata (res.getLong ("NR_Duplicata"));
          edVolta.setNM_Pessoa_Remetente (res.getString ("NM_Razao_Social_Remetente"));
          edVolta.setNM_Pessoa_Destinatario (res.getString ("NM_Razao_Social_Destinatario"));
          edVolta.setOID_Duplicata (res.getString ("OID_Duplicata"));
          edVolta.setDM_Situacao (res.getString ("DM_Situacao"));
          edVolta.setVL_Total_Frete (res.getDouble ("VL_Total_Frete"));
          vl_total += res.getDouble ("VL_Total_Frete");
          if ("E".equals (res.getString ("DM_Situacao"))) {
            vl_estorno += res.getDouble ("VL_Total_Frete");
          }
          tem_internacional++;

          list.add (edVolta);
        }
        if (tem_internacional > 0) {
          Origem_DuplicataED edVolta = new Origem_DuplicataED ();
          edVolta.setDM_Pode_Estornar(dm_pode_estornar);

          edVolta.setNM_Pessoa_Remetente (" ");
          edVolta.setDT_Emissao (" ");
          edVolta.setNM_Pessoa_Destinatario ("Total Emissão->> ");
          edVolta.setVL_Total_Frete (vl_total);
          list.add (edVolta);

          edVolta = new Origem_DuplicataED ();
          edVolta.setNM_Pessoa_Remetente (" ");
          edVolta.setDT_Emissao (" ");
          edVolta.setNM_Pessoa_Destinatario ("Total Estorno->> ");
          edVolta.setVL_Total_Frete (vl_estorno);
          list.add (edVolta);

          edVolta = new Origem_DuplicataED ();
          edVolta.setNM_Pessoa_Remetente (" ");
          edVolta.setDT_Emissao (" ");
          edVolta.setNM_Pessoa_Destinatario ("Total Liquido->> ");
          edVolta.setVL_Total_Frete (vl_total - vl_estorno);
          list.add (edVolta);
        }
      }

      if (ed.getOID_Duplicata () != null && ed.getOID_Duplicata ().length () > 4) {
        sql = " SELECT OID_Origem_Duplicata, Origens_Duplicatas.DM_Situacao, Origens_Duplicatas.oid_Movimento_Duplicata, DT_Origem_Duplicata, HR_Origem_Duplicata, Ordens_Fretes.NR_Ordem_Frete, Ordens_Fretes.DT_Emissao, Ordens_Fretes.vl_frete_devolvido as vl_total_Frete, Duplicatas.NR_Duplicata, Unidades.CD_Unidade, Pessoa_Remetente.NM_Razao_Social as NM_Razao_Social_Remetente, Duplicatas.OID_Duplicata from Origens_Duplicatas, Ordens_Fretes, Unidades, Pessoas Pessoa_Remetente, Duplicatas ";
        sql += " WHERE Unidades.oid_Unidade = Ordens_Fretes.oid_Unidade ";
        sql += " AND Ordens_Fretes.oid_Fornecedor = Pessoa_Remetente.oid_Pessoa ";
        sql += " AND Origens_Duplicatas.oid_ordem_frete_terceiro = Ordens_Fretes.OID_Ordem_Frete ";
        sql += " AND Origens_Duplicatas.OID_Duplicata = Duplicatas.OID_Duplicata ";
        sql += " and Duplicatas.OID_Duplicata = '" + ed.getOID_Duplicata () + "'";

        res = this.executasql.executarConsulta (sql);
        while (res.next ()) {
          Origem_DuplicataED edVolta = new Origem_DuplicataED ();
          edVolta.setDM_Pode_Estornar(dm_pode_estornar);

          edVolta.setDM_Tipo_Origem ("OFT");

          edVolta.setOID_Origem_Duplicata (res.getString ("OID_Origem_Duplicata"));
          edVolta.setCD_Unidade (res.getString ("CD_Unidade"));
          edVolta.setDT_Origem_Duplicata (res.getString ("DT_Origem_Duplicata"));
          DataFormatada.setDT_FormataData (edVolta.getDT_Origem_Duplicata ());
          edVolta.setDT_Origem_Duplicata (DataFormatada.getDT_FormataData ());

          edVolta.setHR_Origem_Duplicata (res.getString ("HR_Origem_Duplicata"));
          edVolta.setNR_Conhecimento (res.getLong ("NR_Ordem_Frete"));

          edVolta.setDT_Emissao (res.getString ("DT_Emissao"));
          DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
          edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());

          edVolta.setNR_Duplicata (res.getLong ("NR_Duplicata"));
          edVolta.setNM_Pessoa_Remetente (res.getString ("NM_Razao_Social_Remetente"));
          edVolta.setNM_Pessoa_Destinatario (" ");
          edVolta.setOID_Duplicata (res.getString ("OID_Duplicata"));
          edVolta.setDM_Situacao (res.getString ("DM_Situacao"));
          edVolta.setVL_Total_Frete (res.getDouble ("VL_Total_Frete"));
          vl_total += res.getDouble ("VL_Total_Frete");
          if ("E".equals (res.getString ("DM_Situacao"))) {
            vl_estorno += res.getDouble ("VL_Total_Frete");
          }
          tem_ordem_frete_terceiro++;
          list.add (edVolta);
        }
        if (tem_ordem_frete_terceiro > 0) {
          Origem_DuplicataED edVolta = new Origem_DuplicataED ();
          edVolta.setDM_Pode_Estornar(dm_pode_estornar);

          edVolta.setNM_Pessoa_Remetente (" ");
          edVolta.setDT_Emissao (" ");
          edVolta.setNM_Pessoa_Destinatario ("Total Emissão->> ");
          edVolta.setVL_Total_Frete (vl_total);
          list.add (edVolta);

          edVolta = new Origem_DuplicataED ();
          edVolta.setNM_Pessoa_Remetente (" ");
          edVolta.setDT_Emissao (" ");
          edVolta.setNM_Pessoa_Destinatario ("Total Estorno->> ");
          edVolta.setVL_Total_Frete (vl_estorno);
          list.add (edVolta);

          edVolta = new Origem_DuplicataED ();
          edVolta.setNM_Pessoa_Remetente (" ");
          edVolta.setDT_Emissao (" ");
          edVolta.setNM_Pessoa_Destinatario ("Total Liquido->> ");
          edVolta.setVL_Total_Frete (vl_total - vl_estorno);
          list.add (edVolta);
        }

      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "lista(Origem_DuplicataED ed)");
    }
    return list;
  }

  public Origem_DuplicataED getByRecord (Origem_DuplicataED ed) throws Excecoes {

    String sql = null;
    boolean encontrou = false;
    Origem_DuplicataED edVolta = new Origem_DuplicataED ();

    try {

      sql = " SELECT OID_Origem_Duplicata, Origens_Duplicatas.DM_Situacao, " +
          "Origens_Duplicatas.OID_Duplicata, DT_Origem_Duplicata, HR_Origem_Duplicata, " +
          "Conhecimentos.oid_Conhecimento, Conhecimentos.oid_Pessoa, Conhecimentos.oid_Lote_Faturamento, " +
          "Conhecimentos.oid_Pessoa_Destinatario, Conhecimentos.oid_Pessoa_Pagador, " +
          "Conhecimentos.NR_Conhecimento, Conhecimentos.VL_Total_Frete, " +
          "Conhecimentos.vl_Desconto, Conhecimentos.DT_Emissao, Duplicatas.NR_Duplicata, Duplicatas.VL_Saldo, " +
          "Conhecimentos.oid_Unidade ";

      sql += "from Origens_Duplicatas, Conhecimentos, Duplicatas ";

      sql += " WHERE Origens_Duplicatas.OID_Conhecimento = Conhecimentos.OID_Conhecimento ";
      sql += " AND Origens_Duplicatas.OID_Duplicata = Duplicatas.OID_Duplicata ";

      if (JavaUtil.doValida (ed.getOID_Origem_Duplicata ())) {
        sql += " and OID_Origem_Duplicata = '" + ed.getOID_Origem_Duplicata () + "'";
      }
      if (JavaUtil.doValida (ed.getOid_Nota_Fiscal ())) {
        sql += " and Oid_Nota_Fiscal = '" + ed.getOid_Nota_Fiscal () + "'";
      }
      if (ed.getOid_Parcelamento () > 0) {
        sql += " and Oid_Parcelamento = " + ed.getOid_Parcelamento ();
      }

      FormataDataBean DataFormatada = new FormataDataBean ();

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        encontrou = true;
        edVolta.setOID_Origem_Duplicata (res.getString ("OID_Origem_Duplicata"));
        edVolta.setOID_Duplicata (res.getString ("OID_Duplicata"));
        edVolta.setOID_Conhecimento (res.getString ("OID_Conhecimento"));
        edVolta.setOID_Lote_Faturamento (res.getLong ("OID_Lote_Faturamento"));
        edVolta.setOID_Pessoa (res.getString ("OID_Pessoa"));
        edVolta.setOID_Pessoa_Destinatario (res.getString ("OID_Pessoa_Destinatario"));
        edVolta.setOID_Pessoa_Pagador (res.getString ("OID_Pessoa_Pagador"));
        edVolta.setNR_Conhecimento (res.getLong ("NR_Conhecimento"));
        edVolta.setNR_Duplicata (res.getLong ("NR_Duplicata"));

        edVolta.setDT_Origem_Duplicata (res.getString ("DT_Origem_Duplicata"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Origem_Duplicata ());
        edVolta.setDT_Origem_Duplicata (DataFormatada.getDT_FormataData ());

        edVolta.setHR_Origem_Duplicata (res.getString ("HR_Origem_Duplicata"));
        edVolta.setDM_Situacao (res.getString ("DM_Situacao"));

        edVolta.setDT_Emissao (res.getString ("DT_Emissao"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
        edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());

        edVolta.setVL_Total_Frete (res.getDouble ("VL_Total_Frete"));
        edVolta.setVL_Desconto_Concedido (res.getDouble ("VL_Desconto"));
        edVolta.setVL_Saldo (res.getDouble ("VL_Saldo"));
        edVolta.setOID_Unidade (res.getLong ("oid_Unidade"));
        edVolta.setDM_Pode_Estornar(verificaPodeEstornar(edVolta.getOID_Duplicata()));
        if(edVolta.getDM_Pode_Estornar().equals("S") && edVolta.getDM_Situacao().equals("E"))
        	edVolta.setDM_Pode_Estornar("N");
      }

      if (!encontrou && "Nacional-Internacional".equals (parametro_FixoED.getDM_Tipo_Conhecimento ()) ||
          "Internacional".equals (parametro_FixoED.getDM_Tipo_Conhecimento ())) {

        sql = " SELECT OID_Origem_Duplicata, Origens_Duplicatas.DM_Situacao, " +
            "Origens_Duplicatas.OID_Duplicata, DT_Origem_Duplicata, HR_Origem_Duplicata, " +
            "CI.oid_Conhecimento, CF.oid_Conhecimento_Faturamento, CI.oid_Pessoa, " +
            "CI.oid_Pessoa_Destinatario, CF.oid_Pessoa_Pagador, " +
            "CI.NR_Conhecimento, CF.VL_Faturar as VL_Total_Frete, " +
            "'0' as vl_Desconto, CI.DT_Emissao, Duplicatas.NR_Duplicata, Duplicatas.VL_Saldo, " +
            "CI.oid_Unidade ";

        sql += "from Origens_Duplicatas, Conhecimentos_Internacionais CI, Duplicatas, " +
            "Conhecimentos_Faturamentos CF";

        sql += " WHERE Origens_Duplicatas.OID_Conhecimento_Faturamento = CF.OID_Conhecimento_Faturamento ";
        sql += " AND CF.OID_Conhecimento = CI.OID_Conhecimento ";
        sql += " AND Origens_Duplicatas.OID_Duplicata = Duplicatas.OID_Duplicata ";

        if (JavaUtil.doValida (ed.getOID_Origem_Duplicata ())) {
          sql += " and OID_Origem_Duplicata = '" + ed.getOID_Origem_Duplicata () + "'";
        }
        if (JavaUtil.doValida (ed.getOid_Nota_Fiscal ())) {
          sql += " and Oid_Nota_Fiscal = '" + ed.getOid_Nota_Fiscal () + "'";
        }
        if (ed.getOid_Parcelamento () > 0) {
          sql += " and Oid_Parcelamento = " + ed.getOid_Parcelamento ();
        }

        res = null;
        res = this.executasql.executarConsulta (sql);
        while (res.next ()) {
          encontrou = true;
          edVolta.setOID_Origem_Duplicata (res.getString ("OID_Origem_Duplicata"));
          edVolta.setOID_Duplicata (res.getString ("OID_Duplicata"));
          edVolta.setOID_Conhecimento (res.getString ("OID_Conhecimento"));
          edVolta.setOID_Conhecimento_Faturamento (res.getString ("OID_Conhecimento_Faturamento"));
          edVolta.setOID_Pessoa (res.getString ("OID_Pessoa"));
          edVolta.setOID_Pessoa_Destinatario (res.getString ("OID_Pessoa_Destinatario"));
          edVolta.setOID_Pessoa_Pagador (res.getString ("OID_Pessoa_Pagador"));
          edVolta.setNR_Conhecimento (res.getLong ("NR_Conhecimento"));
          edVolta.setNR_Duplicata (res.getLong ("NR_Duplicata"));

          edVolta.setDT_Origem_Duplicata (res.getString ("DT_Origem_Duplicata"));
          DataFormatada.setDT_FormataData (edVolta.getDT_Origem_Duplicata ());
          edVolta.setDT_Origem_Duplicata (DataFormatada.getDT_FormataData ());

          edVolta.setHR_Origem_Duplicata (res.getString ("HR_Origem_Duplicata"));
          edVolta.setDM_Situacao (res.getString ("DM_Situacao"));

          edVolta.setDT_Emissao (res.getString ("DT_Emissao"));
          DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
          edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());

          edVolta.setVL_Total_Frete (res.getDouble ("VL_Total_Frete"));
          edVolta.setVL_Desconto_Concedido (res.getDouble ("VL_Desconto"));
          edVolta.setVL_Saldo (res.getDouble ("VL_Saldo"));

          edVolta.setOID_Unidade (res.getLong ("oid_Unidade"));
          edVolta.setDM_Pode_Estornar(verificaPodeEstornar(edVolta.getOID_Duplicata()));
        }

      }

      if (!encontrou) {
          sql = " SELECT OID_Origem_Duplicata, Origens_Duplicatas.DM_Situacao, Origens_Duplicatas.oid_Movimento_Duplicata, DT_Origem_Duplicata, HR_Origem_Duplicata, Ordens_Fretes.NR_Ordem_Frete, Ordens_Fretes.DT_Emissao, Ordens_Fretes.vl_frete_devolvido as vl_total_Frete, Duplicatas.NR_Duplicata, Duplicatas.VL_Saldo, Duplicatas.oid_Duplicata, Unidades.CD_Unidade, Pessoa_Remetente.NM_Razao_Social as NM_Razao_Social_Remetente, Duplicatas.OID_Duplicata from Origens_Duplicatas, Ordens_Fretes, Unidades, Pessoas Pessoa_Remetente, Duplicatas ";
          sql += " WHERE Unidades.oid_Unidade = Ordens_Fretes.oid_Unidade ";
          sql += " AND Ordens_Fretes.oid_Fornecedor = Pessoa_Remetente.oid_Pessoa ";
          sql += " AND Origens_Duplicatas.oid_ordem_frete_terceiro = Ordens_Fretes.OID_Ordem_Frete ";
          sql += " AND Origens_Duplicatas.OID_Duplicata = Duplicatas.OID_Duplicata ";
          if (String.valueOf (ed.getOID_Duplicata ()) != null && !String.valueOf (ed.getOID_Duplicata ()).equals ("0") && !String.valueOf (ed.getOID_Duplicata ()).equals ("null")) {
            sql += " and Duplicatas.OID_Duplicata = '" + ed.getOID_Duplicata () + "'";
          }
          res = this.executasql.executarConsulta (sql);
          while (res.next ()) {
            edVolta.setDM_Tipo_Origem ("OFT");

            edVolta.setOID_Origem_Duplicata (res.getString ("OID_Origem_Duplicata"));
            edVolta.setOID_Duplicata (res.getString ("OID_Duplicata"));
            edVolta.setCD_Unidade (res.getString ("CD_Unidade"));
            edVolta.setDT_Origem_Duplicata (res.getString ("DT_Origem_Duplicata"));
            DataFormatada.setDT_FormataData (edVolta.getDT_Origem_Duplicata ());
            edVolta.setDT_Origem_Duplicata (DataFormatada.getDT_FormataData ());

            edVolta.setHR_Origem_Duplicata (res.getString ("HR_Origem_Duplicata"));
            edVolta.setNR_Conhecimento (res.getLong ("NR_Ordem_Frete"));

            edVolta.setDT_Emissao (res.getString ("DT_Emissao"));
            DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
            edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());

            edVolta.setNR_Duplicata (res.getLong ("NR_Duplicata"));
            edVolta.setNM_Pessoa_Remetente (res.getString ("NM_Razao_Social_Remetente"));
            edVolta.setNM_Pessoa_Destinatario (" ");
            edVolta.setOID_Duplicata (res.getString ("OID_Duplicata"));
            edVolta.setDM_Situacao (res.getString ("DM_Situacao"));
            edVolta.setVL_Total_Frete (res.getDouble ("VL_Total_Frete"));
            edVolta.setVL_Saldo (res.getDouble ("VL_Saldo"));
            vl_total += res.getDouble ("VL_Total_Frete");
            if ("E".equals (res.getString ("DM_Situacao"))) {
              vl_estorno += res.getDouble ("VL_Total_Frete");
            }
            edVolta.setDM_Pode_Estornar(verificaPodeEstornar(edVolta.getOID_Duplicata()));
          }
        }

        if(!encontrou){
        	sql =
                " SELECT OID_Origem_Duplicata, Origens_Duplicatas.DM_Situacao, " +
                " Origens_Duplicatas.oid_Movimento_Duplicata, DT_Origem_Duplicata, HR_Origem_Duplicata, " +
                " Notas_Fiscais.NR_Nota_Fiscal, Notas_Fiscais.DT_Emissao, Notas_Fiscais.OID_Nota_Fiscal, " +
                " Notas_Fiscais.OID_Pessoa, Notas_Fiscais.OID_Pessoa_Destinatario, " +
                " Notas_Fiscais.VL_Nota_Fiscal, Duplicatas.NR_Duplicata, Unidades.CD_Unidade, " +
                " Pessoa_Remetente.NM_Razao_Social as NM_Razao_Social_Remetente, " +
                " Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario, " +
                " Duplicatas.OID_Duplicata, Duplicatas.VL_Saldo, Notas_Fiscais.oid_Unidade  " +
                " from Origens_Duplicatas, Notas_Fiscais, Unidades, Pessoas Pessoa_Remetente, Pessoas Pessoa_Destinatario, Duplicatas ";
            sql += " WHERE Unidades.oid_Unidade = Notas_Fiscais.oid_Unidade ";
            sql += " AND Notas_Fiscais.oid_Pessoa = Pessoa_Remetente.oid_Pessoa ";
            sql += " AND Notas_Fiscais.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa ";
            sql += " AND Origens_Duplicatas.OID_Nota_Fiscal = Notas_Fiscais.OID_Nota_Fiscal ";
            sql += " AND Origens_Duplicatas.OID_Duplicata = Duplicatas.OID_Duplicata ";

            if (JavaUtil.doValida (ed.getOID_Origem_Duplicata ())) {
                sql += " and OID_Origem_Duplicata = '" + ed.getOID_Origem_Duplicata () + "'";
              }
              if (JavaUtil.doValida (ed.getOid_Nota_Fiscal ())) {
                sql += " and Oid_Nota_Fiscal = '" + ed.getOid_Nota_Fiscal () + "'";
              }
              if (ed.getOid_Parcelamento () > 0) {
                sql += " and Oid_Parcelamento = " + ed.getOid_Parcelamento ();
              }

            res = this.executasql.executarConsulta (sql);

            //popula
            while (res.next ()) {
            	encontrou = true;
              edVolta.setOID_Origem_Duplicata (res.getString ("OID_Origem_Duplicata"));
              edVolta.setOID_Duplicata (res.getString ("OID_Duplicata"));
              edVolta.setOid_Nota_Fiscal(res.getString ("OID_Nota_Fiscal"));
              edVolta.setOID_Pessoa (res.getString ("OID_Pessoa"));
              edVolta.setOID_Pessoa_Destinatario (res.getString ("OID_Pessoa_Destinatario"));
              edVolta.setNR_Nota_Fiscal (res.getLong ("NR_Nota_Fiscal"));
              edVolta.setNR_Duplicata (res.getLong ("NR_Duplicata"));

              edVolta.setDT_Origem_Duplicata (res.getString ("DT_Origem_Duplicata"));
              DataFormatada.setDT_FormataData (edVolta.getDT_Origem_Duplicata ());
              edVolta.setDT_Origem_Duplicata (DataFormatada.getDT_FormataData ());

              edVolta.setHR_Origem_Duplicata (res.getString ("HR_Origem_Duplicata"));
              edVolta.setDM_Situacao (res.getString ("DM_Situacao"));

              edVolta.setDT_Emissao (res.getString ("DT_Emissao"));
              DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
              edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());

              edVolta.setVL_Total_Frete (res.getDouble ("VL_Nota_Fiscal"));
              edVolta.setVL_Desconto_Concedido (0);
              edVolta.setVL_Saldo (res.getDouble ("VL_Saldo"));
              edVolta.setOID_Unidade (res.getLong ("oid_Unidade"));
            }
        }

    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "getByRecord(Origem_DuplicataED ed)");
    }
    return edVolta;
  }

  public Origem_DuplicataED getByParcelamento (Origem_DuplicataED ed) throws Excecoes {

    String sql = null;
    Origem_DuplicataED edVolta = new Origem_DuplicataED ();
    try {

      sql = " SELECT * FROM Origens_Duplicatas, Duplicatas";
      sql += " WHERE Origens_Duplicatas.OID_Duplicata = Duplicatas.OID_Duplicata ";

      if (JavaUtil.doValida (ed.getOID_Origem_Duplicata ())) {
        sql += " AND oid_Origem_Duplicata = '" + ed.getOID_Origem_Duplicata () + "'";
      }
      if (JavaUtil.doValida (ed.getOid_Nota_Fiscal ())) {
        sql += " AND Oid_Nota_Fiscal = '" + ed.getOid_Nota_Fiscal () + "'";
      }
      if (ed.getOid_Parcelamento () > 0) {
        sql += " AND Oid_Parcelamento = " + ed.getOid_Parcelamento ();
      }

      ResultSet res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
        edVolta.setOID_Origem_Duplicata (res.getString ("OID_Origem_Duplicata"));
        edVolta.setOID_Duplicata (res.getString ("OID_Duplicata"));
        edVolta.setOid_Carteira (res.getInt ("Oid_Carteira"));
        edVolta.setOID_Pessoa (res.getString ("OID_Pessoa"));
        edVolta.setOID_Pessoa_Destinatario (res.getString ("OID_Pessoa"));
        edVolta.setNR_Duplicata (res.getLong ("NR_Duplicata"));
        edVolta.setOID_Unidade (res.getLong ("oid_Unidade"));
        edVolta.setOid_Parcelamento (res.getInt ("oid_Parcelamento"));
        edVolta.setDM_Situacao (res.getString ("DM_Situacao"));
      }
    }
    catch (SQLException exc) {
      throw new Excecoes (exc.getMessage () , this.getClass ().getName () , "getByParcelamento()");
    }
    return edVolta;
  }

  public String verificaPodeEstornar (String oid_Duplicata) throws Excecoes {

	    String sql = null;
	    String dm_pode_estornar="S";

	    try {

	    	sql =" SELECT oid_Movimento_Duplicata FROM Movimentos_Duplicatas WHERE DM_Principal='S' AND oid_Duplicata="  + oid_Duplicata;
	    	ResultSet res = this.executasql.executarConsulta (sql);
	        if (res.next ()) {
	        	dm_pode_estornar="N";
	        }
	        if ("S".equals(dm_pode_estornar)){
	        	sql =" SELECT oid_Duplicata FROM Duplicatas WHERE DM_Situacao <>'C' AND oid_Duplicata_Principal="  + oid_Duplicata;
	            res = this.executasql.executarConsulta (sql);
	            if (res.next ()) {
	            	dm_pode_estornar="N";
	            }
	        }
	        if ("S".equals(dm_pode_estornar)){
	            sql =" SELECT oid_Duplicata FROM Origens_Duplicatas WHERE DM_Situacao='E' AND oid_Duplicata="  + oid_Duplicata;
//	            res = this.executasql.executarConsulta (sql);
//	            if (res.next ()) {
//	                dm_pode_estornar="N";
//	            }
	        }
	    }
	    catch (SQLException exc) {
	      throw new Excecoes (exc.getMessage () , this.getClass ().getName () , "verificaEstorno()");
	    }
	    return dm_pode_estornar;
  }

  public void desconto (Origem_DuplicataED ed) throws Excecoes {

    String sql = null;

    try {

      FormataDataBean DataFormatada = new FormataDataBean ();
      Parametro_FixoED Parametro_FixoED = new Parametro_FixoED ();

      sql = " UPDATE Conhecimentos SET VL_Desconto=" + (ed.getVL_Desconto () + ed.getVL_Desconto_Concedido ());
      sql += " WHERE OID_Conhecimento = '" + ed.getOID_Conhecimento () + "'";

      // System.out.println("DESCONTO ->> "+ sql);

      executasql.executarUpdate (sql);

      Movimento_DuplicataED edComp = new Movimento_DuplicataED ();

      sql = " SELECT Duplicatas.OID_Duplicata, Duplicatas.OID_Carteira, Duplicatas.DT_Emissao, Duplicatas.VL_Duplicata, Duplicatas.VL_Saldo, Duplicatas.VL_Desconto_Faturamento FROM Origens_Duplicatas, Duplicatas ";
      sql += " WHERE Duplicatas.OID_Duplicata = Origens_Duplicatas.OID_Duplicata ";
      sql += " AND Origens_Duplicatas.oid_Origem_Duplicata = '" + ed.getOID_Origem_Duplicata () + "'";

      // System.out.println("DESCONTO 2->> "+ sql);

      ResultSet rsTP = null;
      rsTP = this.executasql.executarConsulta (sql);
      while (rsTP.next ()) {
      // System.out.println("DESCONTO 3->> DUP OK");

        edComp.setOid_Duplicata (rsTP.getLong("OID_Duplicata"));
        edComp.setOID_Carteira (new Integer (rsTP.getInt ("OID_Carteira")));
        edComp.setDT_Movimento (ed.getDt_stamp ());
        edComp.setVl_Duplicata (new Double (rsTP.getDouble ("Vl_Duplicata")));
        edComp.setVl_Saldo (new Double (rsTP.getDouble ("VL_Saldo")));
        edComp.setVl_Desconto_Faturamento (new Double (rsTP.getDouble ("VL_Desconto_Faturamento")));

        edComp.setDt_Emissao (rsTP.getString ("DT_Emissao"));
        DataFormatada.setDT_FormataData (edComp.getDt_Emissao ());
        edComp.setDt_Emissao (DataFormatada.getDT_FormataData ());
        edComp.setDM_Gera_Movimento ("C");
      }


      double vl_Saldo = edComp.getVl_Saldo ().doubleValue ();
      double vl_Desconto_Faturamento = edComp.getVl_Desconto_Faturamento ().doubleValue ();
      double vl_Desconto = ed.getVL_Desconto ();

      vl_Saldo = vl_Saldo - vl_Desconto;

      vl_Desconto_Faturamento = vl_Desconto_Faturamento + vl_Desconto;

      // System.out.println("DESCONTO 4 Saldo->>" + vl_Saldo);

      if (vl_Saldo < 0) {
        vl_Saldo = 0;
      }
      if (vl_Desconto_Faturamento > edComp.getVl_Duplicata ().doubleValue ()) {
        vl_Desconto_Faturamento = edComp.getVl_Duplicata ().doubleValue ();
      }

      edComp.setVL_Debito (new Double (0.0));
      edComp.setVL_Credito (new Double (vl_Desconto));

      edComp.setOID_Tipo_Instrucao (new Integer (Parametro_FixoED.getOID_Tipo_Instrucao_Desconto ()));

      String chave = null;
      long valOid = 0;

      ResultSet rs = executasql.executarConsulta ("select max(NR_Sequencia_Duplicata) as result from Movimentos_Duplicatas where oid_Duplicata = " + edComp.getOid_Duplicata ());
      //pega proximo valor da chave
      while (rs.next ()) {
        valOid = rs.getLong ("result");
        edComp.setNR_Sequencia_Duplicata (++valOid);
      }

      // System.out.println("DESCONTO 5 ->>" );

      chave = (String.valueOf (edComp.getOid_Duplicata ()) + String.valueOf (edComp.getNR_Sequencia_Duplicata ()));

      valOid = (new Long (chave).longValue ());

      sql = " insert into Movimentos_Duplicatas " + "(OID_Movimento_Duplicata, DT_Movimento, NR_Sequencia_Duplicata, oid_bordero, VL_Credito, VL_Debito, "
          + "DT_STAMP, USUARIO_STAMP, DM_STAMP, OID_Duplicata, OID_Tipo_Instrucao, OID_Carteira) values " + "('" + valOid + "',";
      sql += "'" + edComp.getDT_Movimento () + "',";
      sql += edComp.getNR_Sequencia_Duplicata () + ",0,";
      sql += edComp.getVL_Credito () == null ? "null," : edComp.getVL_Credito () + ",";
      sql += edComp.getVL_Debito () == null ? "null," : edComp.getVL_Debito () + ",";
      sql += "'" + edComp.getDt_stamp () + "',";
      sql += "'" + edComp.getUsuario_Stamp () + "',";
      sql += "'" + edComp.getDm_Stamp () + "',";
      sql += edComp.getOid_Duplicata () + ",";
      sql += edComp.getOID_Tipo_Instrucao () + ",";
      sql += edComp.getOID_Carteira () + ")";

      // System.out.println("DESCONTO 9 ->>" + sql);

      executasql.executarUpdate (sql);

      sql = "update Duplicatas set vl_saldo = " + vl_Saldo + ",vl_Desconto_Faturamento = " + vl_Desconto_Faturamento + " WHERE oid_Duplicata = ";
      sql += edComp.getOid_Duplicata ();

      // System.out.println("DESCONTO 10 ->>" + sql);

      executasql.executarUpdate (sql);

      Ocorrencia_DuplicataED ocodupED = new Ocorrencia_DuplicataED ();

      ResultSet rsoco = executasql.executarConsulta ("select max(oid_Ocorrencia_Duplicata) as result from Ocorrencias_Duplicatas");

      //pega proximo valor da chave
      while (rsoco.next ()) {
        valOid = rsoco.getInt ("result");
      }

      ocodupED.setOID_Ocorrencia_Duplicata (++valOid);

      sql = " insert into Ocorrencias_Duplicatas (OID_OCORRENCIA_DUPLICATA, OID_DUPLICATA, OID_TIPO_OCORRENCIA, DT_OCORRENCIA_DUPLICATA, HR_OCORRENCIA_DUPLICATA, TX_OCORRENCIA_DUPLICATA ) values ";
      sql += "(" + ocodupED.getOID_Ocorrencia_Duplicata () + "," + edComp.getOid_Duplicata () + "," + ed.getOID_Tipo_Ocorrencia () + ",'" + Data.getDataDMY () + "','" + Data.getHoraHM ()
          + "','" + "Desc.Ctrc :" + ed.getNR_Conhecimento () + " Vlr.:" + String.valueOf (ed.getVL_Desconto ()) + " -" + ed.getTX_Observacao () + "')";
      // System.out.println("DESCONTO 20 ->>" + sql);

      executasql.executarUpdate (sql);


      rs = executasql.executarConsulta ("select max(oid_Ocorrencia_Conhecimento) as result from Ocorrencias_Conhecimentos");

      while (rs.next ()) {
        valOid = rs.getInt ("result");
      }


      // System.out.println("DESCONTO 21 ------------- ->>" );

      valOid++;

      sql = " insert into Ocorrencias_Conhecimentos (OID_Ocorrencia_Conhecimento, OID_Conhecimento, OID_Tipo_Ocorrencia, DT_Ocorrencia_Conhecimento, HR_Ocorrencia_Conhecimento, TX_Observacao ) values ";
      sql += "(" + valOid + ",'" + ed.getOID_Conhecimento () + "'," + ed.getOID_Tipo_Ocorrencia () + ",'" + Data.getDataDMY () + "','" + Data.getHoraHM () + "','" + "Desc.Ctrc :"
          + ed.getNR_Conhecimento () + " Vlr.:" + String.valueOf (ed.getVL_Desconto ()) + " -" + ed.getTX_Observacao () + "')";

      // System.out.println("DESCONTO 30 ->>" + sql);

      executasql.executarUpdate (sql);

//      Movimento_ConhecimentoED edMovimento_Conhecimento = new Movimento_ConhecimentoED ();
//      edMovimento_Conhecimento.setOID_Tipo_Movimento (new Long (Parametro_FixoED.getOID_Tipo_Movimento_Custo_Desconto ()).longValue ());
//
//      edMovimento_Conhecimento.setVL_Movimento (ed.getVL_Desconto ());
//      edMovimento_Conhecimento.setDT_Movimento_Conhecimento (Data.getDataDMY ());
//      edMovimento_Conhecimento.setHR_Movimento_Conhecimento (Data.getHoraHM ());
//
//      edMovimento_Conhecimento.setOID_Conhecimento (ed.getOID_Conhecimento ());
//      edMovimento_Conhecimento.setDM_Lancado_Gerado ("D");
//      edMovimento_Conhecimento.setNM_Pessoa_Entrega ("");
//      edMovimento_Conhecimento.setDM_Tipo_Movimento ("D");
//      edMovimento_Conhecimento.setOID_Tabela_Frete ("");
//
//      Movimento_ConhecimentoRN Movimento_Conhecimentorn = new Movimento_ConhecimentoRN ();
//
//      // System.out.println("DESCONTO 40 Antes Inclui Mov Cto ->>");
//
//      Movimento_Conhecimentorn.inclui (edMovimento_Conhecimento);



    }
    catch (SQLException exc) {
      throw new Excecoes (exc.getMessage () , this.getClass ().getName () , "desconto(Origem_DuplicataED ed)");
    }
  }

  //  public void geraRelatorio(Origem_DuplicataED ed)throws Excecoes{
  //
  //    String sql = null;
  //
  //    Origem_DuplicataED edVolta = new Origem_DuplicataED();
  //
  //    try{
  //
  //      sql = "select * from Origem_Duplicatas where oid_Origem_Duplicata > 0";
  //
  //      ResultSet res = null;
  //      res = this.executasql.executarConsulta(sql);

  //Origem_DuplicataRL Origem_Duplicata_rl = new Origem_DuplicataRL();
  //Origem_Duplicata_rl.geraRelatEstoque(res);
  //    }
  //    catch (Excecoes e){
  //      throw e;
  //    }
  //    catch(Exception exc){
  //      Excecoes exce = new Excecoes();
  //      exce.setExc(exc);
  //      exce.setMensagem("Erro no método listar");
  //      exce.setClasse(this.getClass().getName());
  //      exce.setMetodo("geraRelatorio(Origem_DuplicataED ed)");
  //    }

  // }

  public void incluiAvulso (Origem_DuplicataED ed) throws Excecoes {

    String sql = null;
    ResultSet rsTP = null;
    double vl_total_frete = 0 , vl_saldo = 0 , vl_pago = 0 , vl_total_origem = 0;
    long oid_Carteira = 0;
    Movimento_DuplicataED movimento_DuplicataED = new Movimento_DuplicataED ();

    try {
      Parametro_FixoED Parametro_FixoED = new Parametro_FixoED ();

      // System.out.println("var INCLUIR OK->"+new Long (ed.getOID_Duplicata ()).longValue ());

      this.inclui (ed);
      // System.out.println("INCLUI OK");

      movimento_DuplicataED = new Movimento_DuplicataRN().consultaMovimento (new Long (ed.getOID_Duplicata ()).longValue ());

      // System.out.println("movimento_DuplicataED OK");

      vl_pago = movimento_DuplicataED.getVL_Pago ();
      vl_total_origem = this.totalizaOrigem (new Long (ed.getOID_Duplicata ()).longValue ());

      sql = " SELECT  oid_Carteira " +
            " FROM  Duplicatas  " +
            " WHERE Duplicatas.OID_Duplicata = '" + ed.getOID_Duplicata () + "'";
      rsTP = this.executasql.executarConsulta (sql);
      while (rsTP.next ()) {
        oid_Carteira = rsTP.getLong ("oid_Carteira");
      }

      vl_saldo = vl_total_origem - vl_pago;

      if (JavaUtil.doValida (ed.getOID_Conhecimento ())) {
        sql = " UPDATE Conhecimentos SET DM_Situacao = 'F' " +
              " WHERE OID_Conhecimento = '" + ed.getOID_Conhecimento () + "'";
        // System.out.println (sql);

        executasql.executarUpdate (sql);
      }

      sql = " UPDATE Duplicatas set vl_Duplicata = " + vl_saldo + ", vl_saldo = " + vl_saldo +
          " WHERE oid_Duplicata = '" + ed.getOID_Duplicata () + "'";

      // System.out.println (sql);

      executasql.executarUpdate (sql);

      String sqlBusca = " select * from Tipos_Instrucoes ";
      sqlBusca += " where CD_Tipo_Instrucao = 'AVU'";
      String tp_instrucao = "0";
      rsTP = null;
      rsTP = this.executasql.executarConsulta (sqlBusca);

      while (rsTP.next ()) {
    	  tp_instrucao = rsTP.getString("OID_Tipo_Instrucao");
      }

      Movimento_DuplicataED edComp = new Movimento_DuplicataED ();
      String chave = String.valueOf (System.currentTimeMillis ()).toString ();

      sql = " insert into Movimentos_Duplicatas " +
          " (OID_Movimento_Duplicata, DT_Movimento, NR_Sequencia_Duplicata, VL_Credito, VL_Debito, "
          + "DT_STAMP, USUARIO_STAMP, DM_STAMP, OID_Duplicata, OID_Tipo_Instrucao, OID_Carteira, OID_Bordero) values " +
          "('" + chave + "',";
      sql += "'" + Data.getDataDMY () + "',";
      sql += edComp.getNR_Sequencia_Duplicata () + ",";
      sql += "0,";
      sql += vl_total_frete + ",";
      sql += "'" + Data.getDataDMY () + "',";
      sql += "'" + edComp.getUsuario_Stamp () + "',";
      sql += "'" + edComp.getDm_Stamp () + "',";
      sql += ed.getOID_Duplicata () + ",";
      sql += tp_instrucao + ",";
      sql += oid_Carteira + ",0)";

//      // System.out.println (sql);

      //executasql.executarUpdate (sql);
      ////
    }
    catch (SQLException e) {
      e.printStackTrace ();
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "estorna(Origem_DuplicataED ed)");
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "estorna(Origem_DuplicataED ed)");
    }
  }

  public double totalizaOrigem (long oid_Duplicata) throws Excecoes {

    String sql = null;
    ResultSet rsTP = null;
    double vl_Total_Origem=0;

    try {


      sql = " SELECT SUM (VL_Total_Frete) as vl_Total_Origem " +
          " FROM  Conhecimentos, Origens_Duplicatas  " +
          " WHERE Origens_Duplicatas.oid_Conhecimento = Conhecimentos.oid_Conhecimento " +
          " AND   Origens_Duplicatas.DM_Situacao <> 'E' " +
          " AND   Origens_Duplicatas.oid_Duplicata = '" + oid_Duplicata + "'";
      rsTP = this.executasql.executarConsulta (sql);
      if (rsTP.next ()) {
        vl_Total_Origem = rsTP.getDouble ("vl_Total_Origem");
      }
      ////
    }
    catch (SQLException e) {
      e.printStackTrace ();
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "estorna(Origem_DuplicataED ed)");
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "estorna(Origem_DuplicataED ed)");
    }
    return vl_Total_Origem;
  }


}
