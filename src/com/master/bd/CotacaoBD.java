package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.master.ed.CotacaoED;
import com.master.rl.CotacaoRL;
import com.master.root.FormataDataBean;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FormataData;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;
import com.master.util.Utilitaria;
import com.master.util.Valor;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;

public class CotacaoBD {

  Calcula_FreteBD Calcula_FreteBD = null;

  Parametro_FixoED parametro_FixoED = new Parametro_FixoED ();
  FormataDataBean dataFormatada = new FormataDataBean ();

  String Situacao_Cliente = "";
  String Tipo_Cobranca = "B";
  String OID_Vendedor = "";

  private ExecutaSQL executasql;
  Utilitaria util = null;

  public CotacaoBD (ExecutaSQL sql) {
    //super(sql);
    util = new Utilitaria (executasql);
    this.executasql = sql;
  }


  public CotacaoED inclui (CotacaoED ed) throws Excecoes {

    String sql = null;
    long valOid = 0;
    long nr_Cotacao=0;
    OID_Vendedor = parametro_FixoED.getOID_Cliente_Complemento_Padrao ();

    CotacaoED conED = new CotacaoED ();

    String Pessoa_Pagador = ed.getOID_Pessoa_Pagador ();
    if (!util.doValida (Pessoa_Pagador)) {
      throw new Mensagens ("Pessoa Pagador n�o informada!");
    }

    try {
      if (ed.getNR_Cotacao () == 0) {

        //*** Busca Pr�ximo Registro
         nr_Cotacao = util.getTableIntValue ("nr_proxima_cotacao" , "Parametros_Filiais" , "Parametros_Filiais.OID_Unidade = " + ed.getOID_Unidade ());

        //*** VALIDA�AO
         if (util.doExiste ("Cotacoes" , "nr_Cotacao = " + nr_Cotacao + " AND OID_UNIDADE = " + ed.getOID_Unidade ())) {
           throw new Excecoes ("NR de Cotacao ja existente. Alterar parametro da Unidade!");
         }

        //*** Atualiza Paramentros
         sql = " UPDATE Parametros_Filiais SET nr_proxima_cotacao= " + (nr_Cotacao + 1) +
             " WHERE Parametros_Filiais.OID_Unidade = " + ed.getOID_Unidade ();
             executasql.executarUpdate (sql);

        ed.setNR_Cotacao (nr_Cotacao);
      }

      ed.setOID_Cotacao (String.valueOf (System.currentTimeMillis ()));

      sql =
          " insert into Cotacoes " +
          "   (OID_Cotacao,  " +
          "    OID_Usuario, " +
          "    OID_Pessoa, "+
          "    DT_Previsao_Entrega, " +
          "    HR_Previsao_Entrega, " +
          "    OID_Pessoa_Destinatario, " +
          "    OID_Pessoa_Consignatario, " +
          "    OID_Pessoa_Redespacho, " +
          "    OID_Modal, " +
          "    OID_Coleta, " +
          "    OID_Unidade, " +
          "    OID_Vendedor, " +
          "    OID_Cidade, " +
          "    OID_Cidade_Destino, " +
          "    OID_Pessoa_Pagador, " +
          "    OID_Produto, " +
          "    NR_Cotacao, " +
          "    DM_Tipo_Pagamento, " +
          "    DM_Tipo_Cotacao, " +
          "    TX_Observacao, " +
          "    NM_Solicitante, " +
          "    DM_Isento_Seguro, " +
          "    NM_Natureza, " +
          "    NM_Especie, " +
          "    DT_Emissao, " +
          "    DM_Situacao, " +
          "    NR_Peso, " +
          "    NR_Peso_Cubado, " +
          "    NR_Volumes, " +
          "    VL_Nota_Fiscal ) " +
          " values " +

          " ('" + ed.getOID_Cotacao () + "'," +
          ed.getOID_Usuario () + ", " +
          " '" + ed.getOID_Pessoa () + "', " +
          JavaUtil.getSQLDate (ed.getDT_Previsao_Entrega ()) + ", " +
          " '" + ed.getHR_Previsao_Entrega () + "', " +
          " '" + ed.getOID_Pessoa_Destinatario () + "', " +
          " '" + ed.getOID_Pessoa_Consignatario () + "', " +
          " '" + ed.getOID_Pessoa_Redespacho () + "', " +
          ed.getOID_Modal () + "," +
          ed.getOID_Coleta () + ", " +
          ed.getOID_Unidade () + ", " +
          " '" + OID_Vendedor + "', " +
          ed.getOID_Cidade () + ", " +
          ed.getOID_Cidade_Destino () + ", " +
          " '" + ed.getOID_Pessoa_Pagador () + "', " +
          ed.getOID_Produto () + "," +
          ed.getNR_Cotacao () + ", " +
          " '" + ed.getDM_Tipo_Pagamento () + "', " +
          " '" + ed.getDM_Tipo_Cotacao () + "', " +
          " '" + ed.getTX_Observacao () + "', " +
          " '" + ed.getNM_Solicitante () + "', " +
          " '" + ed.getDM_Isento_Seguro () + "', " +
          " '" + ed.getNM_Natureza () + "', " +
          " '" + ed.getNM_Especie () + "', " +
          " '" + ed.getDT_Emissao () + "', " +
          " '" + "N" + "', " +
          ed.getNR_Peso () + ", " +
          ed.getNR_Peso_Cubado () + ", " +
          ed.getNR_Volumes () + ", " +
          ed.getVL_Nota_Fiscal () + ")";

      // System.out.print (sql);

      int i = executasql.executarUpdate (sql);

      conED.setOID_Cotacao (ed.getOID_Cotacao ());
    }

    catch (SQLException e) {
      e.printStackTrace ();
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "inclui(CotacaoED ed)");
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "inclui(CotacaoED ed)");
    }
    return conED;
  }

  public CotacaoED salva_Cotacao (CotacaoED ed) throws Excecoes {

    String sql = null;
    CotacaoED conED = new CotacaoED ();
    conED = this.getByRecord (ed);

    ResultSet res = null;
    try {
      sql = " SELECT oid_cotacao  " +
          " FROM   Movimentos_Cotacoes   " +
          " WHERE  OID_Cotacao='"       + conED.getOID_Cotacao () + "'" +
          " AND    oid_Modal="          + conED.getOID_Modal () +
          " AND    vl_total_frete="     + conED.getVL_TOTAL_FRETE () +
          " AND    vl_frete_calculado=" + conED.getVL_Frete_Calculado ();

      // System.out.println(sql);
      
      res = this.executasql.executarConsulta (sql);
      if (!res.next ()) {

        String chave = String.valueOf (System.currentTimeMillis ());
        String oid_Movimento_Cotacao = chave;
        int nr_simulacao = 0;
        sql = " SELECT count (oid_cotacao) as nr_simulacao " +
            " FROM   Movimentos_Cotacoes   " +
            " WHERE  OID_Cotacao='" + conED.getOID_Cotacao () + "'";
        res = this.executasql.executarConsulta (sql);
        if (res.next ()) {
          nr_simulacao = res.getInt ("nr_simulacao");
        }
        nr_simulacao++;

        sql =
            " insert into Movimentos_Cotacoes " +
            "   (OID_Cotacao,  " +
            "    OID_Movimento_Cotacao, " +
            "    OID_Modal, " +
            "    DM_Situacao, " +
            "    DM_Tipo_Calculo, " +
            "    vl_frete_peso, " +
            "    vl_frete_valor, " +
            "    vl_pedagio, " +
            "    vl_outros1, " +
            "    vl_coleta, " +
            "    vl_entrega, " +
            "    vl_total_frete, " +
            "    vl_frete_calculado, " +
            "    OID_Tabela_Frete, " +
            "    vl_total_custo, " +
            "    vl_margem, " +
            "    vl_desconto, " +
            "    pe_margem, " +
            "    pe_desconto, " +
            "    vl_icms, " +
            "    nr_simulacao ) " +
            " values " +

            " ('" + conED.getOID_Cotacao () + "'," +
            " '" + oid_Movimento_Cotacao + "', " +
            conED.getOID_Modal () + "," +
            " 'N', '" +
            conED.getDM_Tipo_Calculo () + "', " +
            conED.getVL_FRETE_PESO () + ", " +
            conED.getVL_FRETE_VALOR () + ", " +
            conED.getVL_PEDAGIO () + ", " +
            conED.getVL_OUTROS1 () + ", " +
            conED.getVL_COLETA () + ", " +
            conED.getVL_ENTREGA () + ", " +
            conED.getVL_TOTAL_FRETE () + ", " +
            conED.getVL_Frete_Calculado () + ", '" +
            conED.getOID_Tabela_Frete () + "', " +
            conED.getVL_Total_Custo () + ", " +
            conED.getVL_Margem () + ", " +
            conED.getVL_Desconto () + ", " +
            conED.getPE_Margem () + ", " +
            conED.getPE_Desconto () + ", " +
            conED.getVL_ICMS () + ", " +
            nr_simulacao + ")";

        // System.out.print (sql);

        int i = executasql.executarUpdate (sql);

        // System.out.println (sql);

        sql = " SELECT * " +
            " FROM  Movimentos_Conhecimentos " +
            " WHERE Movimentos_Conhecimentos.oid_Cotacao='" + conED.getOID_Cotacao () + "'";

        // System.out.println (sql);

        res = this.executasql.executarConsulta (sql);
        while (res.next ()) {

          // System.out.println ("Movimento Conhecimento inclui >>> " + res.getString ("oid_Conhecimento"));

          chave = String.valueOf (System.currentTimeMillis ()).toString ();

          sql = " insert into Movimentos_Conhecimentos (OID_Movimento_Conhecimento, OID_Fornecedor, NR_Documento, NR_Movimento_Conhecimento, VL_Movimento, OID_Conhecimento, OID_Ordem_Frete, OID_Movimento_Cotacao, OID_Tipo_Movimento, DT_Movimento_Conhecimento, HR_Movimento_Conhecimento, DM_Tipo_Movimento, DM_Lancado_Gerado , OID_Tabela_Frete, OID_Lote_Fornecedor, VL_Previsto, VL_Realizado, VL_Tarifa) values ";
          sql += "('" + chave + "','" + res.getString ("OID_Fornecedor") + "','" + 0 + "'," + 0 + "," + res.getDouble ("vl_movimento") + ",'" + res.getString ("oid_Conhecimento") + "','" + null +"','" + oid_Movimento_Cotacao + "'," + res.getString ("OID_Tipo_Movimento") + ",'" + Data.getDataDMY () + "','" + Data.getHoraHM () + "','" + res.getString ("DM_Tipo_Movimento") + "','" + res.getString ("DM_Lancado_Gerado") + "','" +
              res.getString ("oid_tabela_frete") + "'," + 0 + "," + res.getDouble ("vl_previsto") + "," + 0 + ", " + 0 + ")";

          // System.out.println (sql);

          executasql.executarUpdate (sql);
        }

        //coletas que devem ser aprovada, setar como COTADA!
        sql = " SELECT Coletas.oid_Coleta " +
            " FROM   Cotacoes, Coletas " +
            " WHERE  Coletas.oid_Coleta = Cotacoes.oid_Coleta " +
            " AND    Coletas.DM_Situacao_Cotacao='N'" +
            " AND    Cotacoes.oid_Cotacao ='" + ed.getOID_Cotacao () + "'";
        res = this.executasql.executarConsulta (sql);
        if (res.next ()) {
          sql = " UPDATE Coletas SET DM_Situacao_Cotacao='S' " +
              " WHERE Coletas.oid_Coleta = '" + res.getString ("oid_Coleta") + "'";
          // System.out.println (sql);
          executasql.executarUpdate (sql);
        }

      }
      conED.setOID_Cotacao (ed.getOID_Cotacao ());
    }

    catch (SQLException e) {
      e.printStackTrace ();
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "inclui(CotacaoED ed)");
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "inclui(CotacaoED ed)");
    }
    return conED;
  }


  public void altera (CotacaoED ed) throws Excecoes {

    String Pessoa_Pagador = ed.getOID_Pessoa_Pagador ();

    String sql = " UPDATE Cotacoes SET  " +
        "  OID_Pessoa= '" + ed.getOID_Pessoa () + "'" + 
        " ,OID_Pessoa_Destinatario= '"  + ed.getOID_Pessoa_Destinatario () + "'" +
        " ,OID_Pessoa_Consignatario= '" + ed.getOID_Pessoa_Consignatario () + "'" +
        " ,OID_Pessoa_Redespacho= '" + ed.getOID_Pessoa_Redespacho ()+ "'" +
        " ,OID_Modal= " + ed.getOID_Modal () + 
        " ,OID_Produto= " + ed.getOID_Produto () + 
        " ,OID_Cidade= " + ed.getOID_Cidade () +
        " ,OID_Cidade_Destino= " + ed.getOID_Cidade_Destino () + 
        " ,OID_Pessoa_Pagador= '" + ed.getOID_Pessoa_Pagador () + "'" +
        " ,DM_Tipo_Pagamento= " + util.getSQLStringDef (ed.getDM_Tipo_Pagamento () , "") + 
        " ,DM_Tipo_Cotacao= " + util.getSQLStringDef (ed.getDM_Tipo_Cotacao () , "") + 
        " ,TX_Observacao= '" + ed.getTX_Observacao () + "'" +
        " ,NM_Solicitante= '" + ed.getNM_Solicitante () + "'" +
        " ,DM_Isento_Seguro= " + util.getSQLStringDef (ed.getDM_Isento_Seguro () , "") + 
        " ,NM_Natureza= '" + ed.getNM_Natureza () + "'" +
        " ,NM_Especie= '" + ed.getNM_Especie ()+ "'" +
        ", NR_Peso   = " + ed.getNR_Peso () +
        ", NR_Peso_Cubado   = " + ed.getNR_Peso_Cubado () +
        ", NR_Volumes   = " + ed.getNR_Volumes () +
        ", PE_Desconto   = " + ed.getPE_Desconto () +
        ", VL_Nota_Fiscal   = " + ed.getVL_Nota_Fiscal () ;

    if (!ed.getDT_Previsao_Entrega ().equals ("")) {
      sql += ", DT_Previsao_Entrega= '" + ed.getDT_Previsao_Entrega () + "'";
    }
    if (!ed.getHR_Previsao_Entrega ().equals ("")) {
      sql += ", HR_Previsao_Entrega= '" + ed.getHR_Previsao_Entrega () + "'";
    }

    sql += " where oid_Cotacao = '" + ed.getOID_Cotacao () + "'";
    try {
      // System.out.println (sql);
      int i = executasql.executarUpdate (sql);
    }
    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "altera(CotacaoED ed)");
    }
  }

  public void confirma_Cotacao (CotacaoED ed) throws Excecoes {
    try {

      String sql =" SELECT * FROM Movimentos_Cotacoes " +
                  " WHERE oid_Movimento_Cotacao = '" + ed.getOID_Movimento_Cotacao () + "'";
      ResultSet res = this.executasql.executarConsulta (sql);

      if (res.next ()) {

        sql = " UPDATE Cotacoes SET  " +
            "  OID_Modal= " + ed.getOID_Modal () +
            " ,DM_Tipo_Calculo= '" + res.getString("DM_Tipo_Calculo") + "'" +
            " ,VL_FRETE_PESO= " + res.getDouble("VL_FRETE_PESO") +
            " ,VL_FRETE_VALOR= " + res.getDouble("VL_FRETE_VALOR") +
            " ,VL_PEDAGIO= " + res.getDouble("VL_PEDAGIO") +
            " ,VL_OUTROS1= " + res.getDouble("VL_OUTROS1") +
            " ,VL_TOTAL_FRETE= " + res.getDouble("VL_TOTAL_FRETE") +
            " ,VL_Frete_Calculado= " + res.getDouble("VL_Frete_Calculado") +
            " ,VL_Total_Custo= " + res.getDouble("VL_Total_Custo") +
            " ,VL_Desconto= " + res.getDouble("VL_Desconto") +
            " ,PE_Desconto= " + res.getDouble("PE_Desconto") +
            " ,VL_ICMS= " + res.getDouble("VL_ICMS") +
            " ,DM_Situacao= 'A' " +
            "  WHERE oid_Cotacao = '" + ed.getOID_Cotacao () + "'";
        // System.out.println (sql);
        executasql.executarUpdate (sql);
      }


    sql = " UPDATE Movimentos_Cotacoes SET  " +
          " DM_Situacao= '-' "  + 
          " WHERE oid_Cotacao = '" + ed.getOID_Cotacao () + "'";
      // System.out.println (sql);
      executasql.executarUpdate (sql);

    sql = " UPDATE Movimentos_Conhecimentos SET  " +
          " oid_Cotacao ='999999' " +
          " WHERE oid_Cotacao = '" + ed.getOID_Cotacao () + "'";
      // System.out.println (sql);
      executasql.executarUpdate (sql);

    sql = " UPDATE Movimentos_Conhecimentos SET  " +
          " oid_Cotacao ='" + ed.getOID_Cotacao () + "'" +
          " WHERE oid_Movimento_Cotacao = '" + ed.getOID_Movimento_Cotacao () + "'";
      // System.out.println (sql);
      executasql.executarUpdate (sql);


    sql = " UPDATE Movimentos_Cotacoes SET  " +
          " DM_Situacao= 'A' "  + 
          " WHERE oid_Movimento_Cotacao = '" + ed.getOID_Movimento_Cotacao () + "'";
      // System.out.println (sql);
      executasql.executarUpdate (sql);

      ed.setAcao("CONFIRMADA");
      this.altera_Situacao(ed);

    }
    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "altera(CotacaoED ed)");
    }
  }


  public void deleta (CotacaoED ed) throws Excecoes {

    String sql = null;

    try {


      sql = "delete from Movimentos_Cotacoes WHERE oid_Cotacao = ";
      sql += "('" + ed.getOID_Cotacao () + "')";

      executasql.executarUpdate (sql);

      sql = "delete from Cotacoes WHERE oid_Cotacao = ";
      sql += "('" + ed.getOID_Cotacao () + "')";

      executasql.executarUpdate (sql);
    }

    catch (Exception exc) {
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao excluir");
      excecoes.setMetodo ("excluir");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public void altera_Situacao (CotacaoED ed) throws Excecoes {

    // System.out.println("altera_Situacao COTACAO ");

    String sql = null;
    String dm_Situacao="";
    String nm_Situacao="";
    String dm_acao="";
    CotacaoED edCota = this.getByRecord(ed);

    // System.out.println("altera_Situacao COTACAO="+edCota.getNR_Cotacao());

    try {

      if ("CONFIRMADA".equals(ed.getAcao())){
        dm_acao="COTCONF";
        nm_Situacao="Cotacao " + edCota.getNR_Cotacao()+ " foi confirmada. ";
        dm_Situacao="A";
      }

      if ("CANCELA".equals(ed.getAcao())){
        dm_acao="COTCANC";
        nm_Situacao="Cotacao " + edCota.getNR_Cotacao()+ " foi cancelada. ";
        dm_Situacao="C";
      }

      if ("CUSTO_DEFINIDO".equals(ed.getAcao())){
        dm_acao="COTCDEF";
        nm_Situacao="Custo da Cotacao " + edCota.getNR_Cotacao()+ " foi definido.";
        dm_Situacao="O";
      }

      if ("DEFINIR_CUSTO".equals(ed.getAcao())){
        dm_acao="COTDFC";
        nm_Situacao="Definir custo da Cotacao " + edCota.getNR_Cotacao();
        dm_Situacao="D";
      }

      if ("CUSTO".equals(ed.getAcao())){
        dm_Situacao="C";
      }

      if ("PERDA".equals(ed.getAcao())){
        dm_acao="COTPERDA";
        nm_Situacao="Cotacao " + edCota.getNR_Cotacao() + " foi perdida p/"+ed.getTX_Motivo_Perda();
        dm_Situacao="P";
      }

      if ("FECHAR".equals(ed.getAcao())){
        //COTOK
        dm_acao="COTOK";
        nm_Situacao="Cotacao " + edCota.getNR_Cotacao() + " Fechada!";
        dm_Situacao="F";
      }

      if (!"".equals(dm_Situacao)){
        sql = " update Cotacoes set DM_Situacao='" + dm_Situacao + "'";
        if ("P".equals(dm_Situacao)){
          sql +=" , DM_Motivo_Perda='" + ed.getDM_Motivo_Perda() + "'" +
                " , TX_Motivo_Perda='" + ed.getTX_Motivo_Perda() + "'" +
                " , VL_Preco_Perda=" + ed.getVL_Preco_Perda();
        }
        
        sql += " where oid_Cotacao = '" + ed.getOID_Cotacao () + "'";

        // System.out.println(sql);
        
        executasql.executarUpdate (sql);

        if ("D".equals(dm_Situacao)){
          sql =" DELETE From Movimentos_Conhecimentos WHERE oid_Cotacao = '" + ed.getOID_Cotacao () + "'";
          executasql.executarUpdate (sql);

          sql =" UPDATE Cotacoes SET vl_total_frete=0  " +
               " ,vl_icms=0 "  +
               " ,vl_total_custo=0 " +  
               " ,pe_desconto=0 "  +
               " ,vl_desconto=0 "  +
               " ,vl_frete_calculado=0 " +   
               " ,vl_custo=0 "  +
                " WHERE oid_Cotacao = '" + ed.getOID_Cotacao () + "'";
          executasql.executarUpdate (sql);

        }
      }

      if (!nm_Situacao.equals("")){

        // System.out.println("email COTACAO acao="+dm_acao+"  sit=" +nm_Situacao);

        new Pessoa_EMailBD (this.executasql).envia_eMail (edCota.getOID_Pessoa_Pagador() , dm_acao , "" , nm_Situacao , "" , "");
      }

    }

    catch (Exception exc) {
      exc.printStackTrace ();
      Excecoes excecoes = new Excecoes ();
      excecoes.setClasse (this.getClass ().getName ());
      excecoes.setMensagem ("Erro ao altera_Situacao");
      excecoes.setMetodo ("altera_Situacao");
      excecoes.setExc (exc);
      throw excecoes;
    }
  }

  public ArrayList lista (CotacaoED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();

    try {

      sql = " SELECT Cotacoes.*, " +
          " Unidades.CD_Unidade, " +
          " Usuarios.nm_usuario, " +
          " Modal.cd_modal, " +
          " Modal.nm_modal, " +
          " Pessoa_Remetente.NM_Razao_Social as NM_Razao_Social_Remetente, " +
          " Pessoa_Destinatario.NM_Razao_Social as NM_Razao_Social_Destinatario " +
          " FROM Cotacoes, Usuarios, Unidades, Pessoas Pessoa_Remetente, Pessoas Pessoa_Destinatario, Modal " +
          " WHERE Unidades.oid_Unidade = Cotacoes.oid_Unidade " +
          " AND Cotacoes.oid_Usuario = Usuarios.oid_Usuario " +
          " AND Cotacoes.oid_Modal   = Modal.oid_Modal " +
          " AND Cotacoes.oid_Pessoa = Pessoa_Remetente.oid_Pessoa " +
          " AND Cotacoes.oid_Pessoa_Destinatario = Pessoa_Destinatario.oid_Pessoa ";

        if (ed.getNR_Cotacao () > 0) {
          sql += " and Cotacoes.NR_Cotacao = " + ed.getNR_Cotacao ();
        }
        if (String.valueOf (ed.getOID_Usuario ()) != null && !String.valueOf (ed.getOID_Usuario ()).equals ("0")) {
          sql += " and Cotacoes.OID_Usuario = " + ed.getOID_Usuario ();
        }

        if (String.valueOf (ed.getOID_Unidade ()) != null && !String.valueOf (ed.getOID_Unidade ()).equals ("0")) {
          sql += " and Cotacoes.OID_Unidade = " + ed.getOID_Unidade ();
        }

        if (String.valueOf (ed.getOID_Coleta ()) != null && !String.valueOf (ed.getOID_Coleta ()).equals ("0")) {
          sql += " and Cotacoes.OID_Coleta = " + ed.getOID_Coleta ();
        }

        if (String.valueOf (ed.getOID_Pessoa ()) != null && !String.valueOf (ed.getOID_Pessoa ()).equals ("")
            && !String.valueOf (ed.getOID_Pessoa ()).equals ("null")) {
          sql += " and Cotacoes.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
        }
        if (String.valueOf (ed.getOID_Pessoa_Destinatario ()) != null && !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("")
            && !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("null")) {
          sql += " and Cotacoes.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario () + "'";
        }
        if (String.valueOf (ed.getOID_Pessoa_Consignatario ()) != null && !String.valueOf (ed.getOID_Pessoa_Consignatario ()).equals ("")
            && !String.valueOf (ed.getOID_Pessoa_Consignatario ()).equals ("null")) {
          sql += " and Cotacoes.OID_Pessoa_Consignatario = '" + ed.getOID_Pessoa_Consignatario () + "'";
        }
        if (String.valueOf (ed.getDt_Emissao_Inicial ()) != null && !String.valueOf (ed.getDt_Emissao_Inicial ()).equals ("") && !String.valueOf (ed.getDt_Emissao_Inicial ()).equals ("null")) {
          sql += " and Cotacoes.DT_Emissao >= '" + ed.getDt_Emissao_Inicial () + "'";
        }
    
        if (String.valueOf (ed.getDt_Emissao_Final ()) != null && !String.valueOf (ed.getDt_Emissao_Final ()).equals ("") && !String.valueOf (ed.getDt_Emissao_Final ()).equals ("null")) {
          sql += " and Cotacoes.DT_Emissao <= '" + ed.getDt_Emissao_Final () + "'";
        }
        if (ed.getDM_Situacao() != null &&  !"".equals(ed.getDM_Situacao()) && !"null".equals(ed.getDM_Situacao()) ){
          sql += " and Cotacoes.DM_Situacao ='" + ed.getDM_Situacao () + "'";
        }
        
      sql += " Order by Cotacoes.DT_Emissao, Cotacoes.NR_Cotacao ";

      // System.out.println (sql);

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);

      //popula

      FormataDataBean DataFormatada = new FormataDataBean ();

      while (res.next ()) {
      // System.out.println ("Lista 1 ");

        CotacaoED edVolta = new CotacaoED ();

        edVolta.setDM_Impresso (res.getString ("DM_Impresso"));
        edVolta.setDT_Emissao (res.getString ("DT_Emissao"));
        DataFormatada.setDT_FormataData (edVolta.getDT_Emissao ());
        edVolta.setDT_Emissao (DataFormatada.getDT_FormataData ());

        edVolta.setNR_Cotacao (res.getLong ("NR_Cotacao"));

      // System.out.println ("Lista 12 ");

        edVolta.setOID_Cotacao (res.getString ("OID_Cotacao"));
        edVolta.setCD_Unidade (res.getString ("CD_Unidade"));
        edVolta.setNM_Modal (res.getString ("NM_Modal"));
        edVolta.setNM_Pessoa_Remetente (res.getString ("NM_Razao_Social_Remetente"));
        edVolta.setNM_Pessoa_Destinatario (res.getString ("NM_Razao_Social_Destinatario"));

      // System.out.println ("Lista 3 ");
        edVolta.setVL_Frete (res.getDouble ("VL_Total_Frete"));
        edVolta.setVL_TOTAL_FRETE (res.getDouble ("VL_Total_Frete"));

        edVolta.setDM_Situacao (res.getString ("DM_Situacao"));
        edVolta = consultaSituacao (edVolta);



        edVolta.setNM_Atendente(res.getString ("NM_Usuario"));
        
        list.add (edVolta);
      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "lista(CotacaoED ed)");
    }

    return list;
  }

  public ArrayList lista_Cotacao_Salva (CotacaoED ed) throws Excecoes {

    String sql = null;
    ArrayList list = new ArrayList ();

    try {

      sql = " SELECT Cotacoes.oid_Cotacao, Movimentos_Cotacoes.vl_total_Frete, * " +
          " FROM   Cotacoes, Movimentos_Cotacoes, Modal " +
          " WHERE  Movimentos_Cotacoes.oid_Cotacao  = Cotacoes.oid_Cotacao " +
          " AND    Movimentos_Cotacoes.oid_Modal  = Modal.oid_Modal ";

      if (ed.getOID_Cotacao()!=null && !"null".equals(ed.getOID_Cotacao()) && !"".equals(ed.getOID_Cotacao())  ){
       sql+= " AND    Cotacoes.oid_Cotacao ='" + ed.getOID_Cotacao () + "'" ;
      }

      if (ed.getOID_Coleta()>0) {
       sql+= " AND    Cotacoes.oid_Coleta ='" + ed.getOID_Coleta () + "'" ;
      }
          sql +=" ORDER BY   Movimentos_Cotacoes.vl_total_Frete asc ";
      // System.out.println (sql);

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);

      //popula

      FormataDataBean DataFormatada = new FormataDataBean ();

      while (res.next ()) {
      // System.out.println ("Lista smsmsmsms");

        CotacaoED edVolta = new CotacaoED ();
        edVolta.setOID_Cotacao (res.getString ("OID_Cotacao"));
        edVolta.setOID_Movimento_Cotacao (res.getString ("OID_Movimento_Cotacao"));

        edVolta.setDM_Situacao (res.getString ("DM_Situacao"));
        edVolta.setDM_Tipo_Calculo (res.getString ("DM_Tipo_Calculo"));
        edVolta.setNR_Simulacao(res.getInt("NR_Simulacao"));

        if ("T".equals(res.getString ("DM_Tipo_Calculo"))){
          edVolta.setNM_Tipo_Calculo ("TABELA");
        }
        if ("C".equals(res.getString ("DM_Tipo_Calculo"))){
          edVolta.setNM_Tipo_Calculo ("CUSTO");
        }
        if ("I".equals(res.getString ("DM_Tipo_Calculo"))){
          edVolta.setNM_Tipo_Calculo ("F.INF.");
        }

        edVolta.setCD_Modal (res.getString ("CD_Modal"));
        edVolta.setNM_Modal (res.getString ("NM_Modal"));

        edVolta.setVL_Frete_Calculado(res.getDouble("VL_Frete_Calculado"));
        edVolta.setVL_TOTAL_FRETE(res.getDouble("vl_total_Frete"));
        edVolta.setVL_FRETE_PESO(res.getDouble("vl_frete_peso"));
        edVolta.setVL_FRETE_VALOR(res.getDouble("vl_frete_valor"));
        edVolta.setVL_PEDAGIO (res.getDouble ("VL_Pedagio"));
        edVolta.setVL_ICMS (res.getDouble ("VL_Icms"));
        edVolta.setVL_COLETA (res.getDouble ("VL_Coleta"));
        edVolta.setVL_ENTREGA (res.getDouble ("VL_Entrega"));
        edVolta.setVL_OUTROS1 (res.getDouble ("VL_Outros1"));


        edVolta.setPE_Desconto (res.getDouble ("PE_Desconto"));
        edVolta.setVL_Desconto (res.getDouble ("VL_Desconto"));
        edVolta.setVL_Total_Custo (res.getDouble ("VL_Total_Custo"));
        edVolta.setOID_Tabela_Frete (res.getString ("OID_Tabela_Frete"));
        if (edVolta.getVL_TOTAL_FRETE()>0) {
          edVolta.setVL_Margem(edVolta.getVL_TOTAL_FRETE()-edVolta.getVL_Total_Custo());
          edVolta.setPE_Margem(edVolta.getVL_Margem()/edVolta.getVL_TOTAL_FRETE()*100);
        }

      // System.out.println ("Lista 3 ");

        list.add (edVolta);
      }
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "lista(CotacaoED ed)");
    }

    return list;
  }


  public CotacaoED getByRecord (CotacaoED ed) throws Excecoes {
    String sql =
        " select Cotacoes.* " +
        "       ,Usuarios.NM_Usuario " +
        "       ,Modal.DM_Tipo_Tabela_Frete as DM_Tipo_Tabela_Frete" +
        "       ,Modal.DM_Tipo_Transporte " +
        "       ,Modal.DM_Tipo_Coleta " +
        "       ,Modal.DM_Tipo_Entrega " +
        "       ,Unidades.oid_Empresa " +
        " from  Cotacoes " +
        "      ,Modal" +
        "      ,Usuarios" +
        "      ,Unidades " +
        " where Cotacoes.oid_unidade = Unidades.oid_unidade " +
        " AND   Cotacoes.oid_Usuario = Usuarios.oid_Usuario " +
        " and   Cotacoes.oid_Modal   = Modal.oid_Modal " +
        " and   OID_Cotacao = '" + ed.getOID_Cotacao () + "'";

    CotacaoED edVolta = new CotacaoED ();
    try {
      ResultSet res = this.executasql.executarConsulta (sql);
      if (res.next ()) {

        // System.out.println (" Cotacao getByRecord NOVINHO !!!!");

        edVolta.setDM_Impresso (res.getString ("DM_Impresso"));
        edVolta.setDM_Isento_Seguro (res.getString ("DM_Isento_Seguro"));

        edVolta.setDM_Tipo_Pagamento (res.getString ("DM_Tipo_Pagamento"));

        edVolta.setDM_Tipo_Cotacao (res.getString ("DM_Tipo_Cotacao"));

        edVolta.setDM_Tipo_Calculo (res.getString ("DM_Tipo_Calculo"));
        edVolta.setNM_Atendente(res.getString ("NM_Usuario"));

        edVolta.setDM_Situacao (res.getString ("DM_Situacao"));
        edVolta.setDT_Emissao (FormataData.formataDataBT (res.getDate ("DT_Emissao")));
        edVolta.setDT_Previsao_Entrega (FormataData.formataDataBT (res.getDate ("DT_Previsao_Entrega")));
        edVolta.setHR_Previsao_Entrega (res.getString ("HR_Previsao_Entrega"));

        edVolta.setNM_Atendente (res.getString ("NM_Usuario"));
        edVolta.setNM_Natureza (res.getString ("NM_Natureza"));
        edVolta.setNM_Especie (res.getString ("NM_Especie"));
        edVolta.setNM_Solicitante (res.getString ("NM_Solicitante"));
        edVolta.setTX_Observacao (res.getString ("TX_Observacao"));
        edVolta.setNR_Cotacao (res.getLong ("NR_Cotacao"));
        edVolta.setOID_Cidade (res.getLong ("OID_Cidade"));

        edVolta.setOID_Cidade_Destino (res.getLong ("OID_Cidade_Destino"));
        edVolta.setOID_Coleta (res.getLong ("OID_Coleta"));
        edVolta.setOID_Cotacao (res.getString ("OID_Cotacao"));

        edVolta.setOID_Modal (res.getLong ("OID_Modal"));
        edVolta.setDM_Tipo_Transporte (res.getString ("DM_Tipo_Transporte"));
        edVolta.setDM_Tipo_Coleta (res.getString ("DM_Tipo_Coleta"));
        edVolta.setDM_Tipo_Entrega (res.getString ("DM_Tipo_Entrega"));
        edVolta.setDM_Tipo_Tabela_Frete (res.getString ("DM_Tipo_Tabela_Frete"));

        edVolta.setOID_Pessoa (res.getString ("OID_Pessoa"));
        edVolta.setOID_Pessoa_Consignatario (res.getString ("OID_Pessoa_Consignatario"));
        edVolta.setOID_Pessoa_Redespacho (res.getString ("OID_Pessoa_Redespacho"));
        edVolta.setOID_Pessoa_Pagador (res.getString ("OID_Pessoa_Pagador"));
        edVolta.setOID_Pessoa_Destinatario (res.getString ("OID_Pessoa_Destinatario"));
        edVolta.setOID_Produto (res.getLong ("OID_Produto"));
        edVolta.setOID_Unidade (res.getLong ("OID_Unidade"));
        edVolta.setOID_Empresa (res.getLong ("OID_Empresa"));
        edVolta.setOID_Vendedor (res.getString ("OID_Vendedor"));
        edVolta.setPE_Aliquota_ICMS (res.getDouble ("PE_Aliquota_ICMS"));

        edVolta.setVL_Nota_Fiscal (res.getDouble ("VL_NOTA_FISCAL"));

        edVolta.setNR_Volumes (Valor.truncLong (res.getDouble ("NR_Volumes")));
        edVolta.setNR_Peso (res.getDouble ("NR_Peso"));
        edVolta.setNR_Cubagem (res.getDouble ("NR_Cubagem"));
        edVolta.setNR_Peso_Cubado (res.getDouble ("NR_PESO_CUBADO"));
        edVolta.setVL_Frete (res.getDouble ("VL_Total_Frete"));
        edVolta.setVL_Frete_Calculado (res.getDouble ("VL_Frete_Calculado"));
        edVolta.setVL_TOTAL_FRETE (res.getDouble ("VL_Total_Frete"));
        edVolta.setVL_FRETE_PESO (res.getDouble ("VL_Frete_Peso"));
        edVolta.setVL_PEDAGIO (res.getDouble ("VL_Pedagio"));
        edVolta.setVL_ICMS (res.getDouble ("VL_Icms"));

        edVolta.setPE_Desconto (res.getDouble ("PE_Desconto"));
        edVolta.setVL_Desconto (res.getDouble ("VL_Desconto"));
        edVolta.setVL_Total_Custo (res.getDouble ("VL_Total_Custo"));
        edVolta.setOID_Tabela_Frete (res.getString ("OID_Tabela_Frete"));

        // System.out.println (" CTO getByRecord 1");
        if (!"F".equals (edVolta.getDM_Situacao ()) &&
            !"D".equals (edVolta.getDM_Situacao ())) {
        // System.out.println (" CTO CALCULAR");
        }
        if (edVolta.getVL_TOTAL_FRETE()>0) {
          edVolta.setVL_Margem(edVolta.getVL_TOTAL_FRETE()-edVolta.getVL_Total_Custo());
          edVolta.setPE_Margem(edVolta.getVL_Margem()/edVolta.getVL_TOTAL_FRETE()*100);
        }
        edVolta = consultaSituacao (edVolta);

      }
    }
    catch (SQLException exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "getByRecord()");
    }
    return edVolta;
  }

  public CotacaoED consulta_Cotacao_Salva (CotacaoED ed) throws Excecoes {
    String sql =
        " select Cotacoes.* " +
        "       ,Cotacoes.DM_Situacao as DM_Situacao_Cotacao " +
        "       ,Modal.CD_Modal " +
        "       ,Modal.NM_Modal " +
        "       ,Unidades.oid_Empresa " +
        "       ,Movimentos_Cotacoes.NR_Simulacao " +
        "       ,Movimentos_Cotacoes.oid_Modal as oid_Modal_Movimento " +
        "       ,Movimentos_Cotacoes.oid_Movimento_Cotacao " +
        "       ,Movimentos_Cotacoes.VL_Total_Frete " +
        "       ,Movimentos_Cotacoes.vl_frete_peso " +
        "       ,Movimentos_Cotacoes.vl_frete_valor " +
        "       ,Movimentos_Cotacoes.VL_Pedagio " +
        "       ,Movimentos_Cotacoes.VL_Icms " +
        "       ,Movimentos_Cotacoes.VL_Coleta " +
        "       ,Movimentos_Cotacoes.VL_Entrega " +
        "       ,Movimentos_Cotacoes.VL_Outros1 " +
        " from  Cotacoes " +
        "      ,Movimentos_Cotacoes" +
        "      ,Modal" +
        "      ,Unidades " +
        " where Cotacoes.oid_unidade = Unidades.oid_unidade " +
        " and   Movimentos_Cotacoes.oid_Modal = Modal.oid_Modal " +
        " and   Cotacoes.OID_Cotacao   = Movimentos_Cotacoes.OID_Cotacao " +
        " and   Movimentos_Cotacoes.OID_Movimento_Cotacao = '" + ed.getOID_Movimento_Cotacao () + "'";

        // System.out.println (" consulta_Cotacao_Salva->>>" + sql);

    CotacaoED edVolta = new CotacaoED ();
    try {
      ResultSet res = this.executasql.executarConsulta (sql);
      if (res.next ()) {

        // System.out.println (" consulta_Cotacao_Salva !!!!");

        edVolta.setDM_Impresso (res.getString ("DM_Impresso"));
        edVolta.setOID_Cotacao (res.getString ("OID_Cotacao"));
        edVolta.setOID_Movimento_Cotacao (res.getString ("OID_Movimento_Cotacao"));
        edVolta.setNR_Simulacao(res.getInt("NR_Simulacao"));
        edVolta.setDM_Situacao (res.getString ("DM_Situacao"));
        edVolta.setDT_Emissao (FormataData.formataDataBT (res.getDate ("DT_Emissao")));
        edVolta.setOID_Modal (res.getLong ("oid_Modal_Movimento"));
        edVolta.setOID_Pessoa (res.getString ("OID_Pessoa"));
        edVolta.setOID_Pessoa_Consignatario (res.getString ("OID_Pessoa_Consignatario"));
        edVolta.setOID_Pessoa_Redespacho (res.getString ("OID_Pessoa_Redespacho"));
        edVolta.setOID_Pessoa_Pagador (res.getString ("OID_Pessoa_Pagador"));
        edVolta.setOID_Pessoa_Destinatario (res.getString ("OID_Pessoa_Destinatario"));
        edVolta.setOID_Produto (res.getLong ("OID_Produto"));
        edVolta.setOID_Unidade (res.getLong ("OID_Unidade"));
        edVolta.setOID_Empresa (res.getLong ("OID_Empresa"));

        // System.out.println (" consulta_Cotacao_Salva 2");

        edVolta.setVL_Nota_Fiscal (res.getDouble ("VL_NOTA_FISCAL"));
        edVolta.setNR_Volumes (Valor.truncLong (res.getDouble ("NR_Volumes")));
        edVolta.setNR_Peso (res.getDouble ("NR_Peso"));
        edVolta.setNR_Cubagem (res.getDouble ("NR_Cubagem"));
        edVolta.setNR_Peso_Cubado (res.getDouble ("NR_PESO_CUBADO"));

        edVolta.setDM_Situacao (res.getString ("DM_Situacao_Cotacao"));

        // System.out.println (" consulta_Cotacao_Salva 3");

        edVolta.setCD_Modal (res.getString ("CD_Modal"));
        edVolta.setNM_Modal (res.getString ("NM_Modal"));
        edVolta.setVL_TOTAL_FRETE(res.getDouble("vl_total_Frete"));
        edVolta.setVL_FRETE_PESO(res.getDouble("vl_frete_peso"));
        edVolta.setVL_FRETE_VALOR(res.getDouble("vl_frete_valor"));
        edVolta.setVL_PEDAGIO (res.getDouble ("VL_Pedagio"));
        edVolta.setVL_ICMS (res.getDouble ("VL_Icms"));
        edVolta.setVL_COLETA (res.getDouble ("VL_Coleta"));
        edVolta.setVL_ENTREGA (res.getDouble ("VL_Entrega"));
        edVolta.setVL_OUTROS1 (res.getDouble ("VL_Outros1"));

        // System.out.println (" consulta_Cotacao_Salva 4");

      }
    }
    catch (SQLException exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () , "getByRecord()");
    }
    return edVolta;
  }

  private CotacaoED consultaSituacao (CotacaoED ed) {

    ed.setNM_Situacao ("---");
    ed.setNM_Motivo_Perda("");
    if ("A".equals (ed.getDM_Situacao ())) {
      ed.setNM_Situacao ("Confirmada");
    }
    if ("C".equals (ed.getDM_Situacao ())) {
      ed.setNM_Situacao ("Cancelada");
    }
    if ("N".equals (ed.getDM_Situacao ())) {
      ed.setNM_Situacao ("Aberta");
    }
    if ("D".equals (ed.getDM_Situacao ())) {
      ed.setNM_Situacao ("Definir Custo");
    }
    if ("O".equals (ed.getDM_Situacao ())) {
      ed.setNM_Situacao ("Custo Definido");
    }
    if ("F".equals (ed.getDM_Situacao ())) {
      ed.setNM_Situacao ("Fechada");
    }
    if ("P".equals (ed.getDM_Situacao ())) {
      ed.setNM_Situacao ("Perdida");
      if ("P".equals(ed.getDM_Motivo_Perda())){
        ed.setNM_Motivo_Perda ("Pre�o");
      }
      if ("R".equals(ed.getDM_Motivo_Perda())){
        ed.setNM_Motivo_Perda ("Prazo");
      }
      if ("S".equals(ed.getDM_Motivo_Perda())){
        ed.setNM_Motivo_Perda ("Sem Prev.Emb.");
      }
      if ("O".equals(ed.getDM_Motivo_Perda())){
        ed.setNM_Motivo_Perda ("Or�amento");
      }
      if ("T".equals(ed.getDM_Motivo_Perda())){
        ed.setNM_Motivo_Perda ("Tempo Resp.");
      }
      if ("X".equals(ed.getDM_Motivo_Perda())){
        ed.setNM_Motivo_Perda ("Outros");
      }
    }

    return ed;
  }

  public byte[] geraRelCotacao (CotacaoED ed) throws Excecoes {

    String sql = null;
    byte[] b = null;

    sql = " SELECT  Empresas.oid_Pessoa as oid_Pessoa_Empresa, " +
        " Cotacoes.*, " +
        " Estado_Destino.CD_Estado  as CD_Estado_Destino, " +
        " Cidade_Destino.NM_Cidade  as NM_Cidade_DEstino, " +
        " Cidade_Destino.oid_Cidade as oid_Cidade_Destino, " +
        " Unidades.CD_Unidade, " +
        " Unidades.oid_Pessoa as oid_Pessoa_Unidade " +
        " FROM Empresas, " +
        " Cotacoes, " +
        " Unidades, " +
        " Cidades Cidade_Origem, " +
        " Cidades Cidade_Destino, " +
        " Regioes_Estados Regiao_Estado_Origem,  " +
        " Regioes_Estados Regiao_Estado_Destino, " +
        " Estados Estado_Origem, " +
        " Estados Estado_Destino " +
        " WHERE Unidades.oid_Unidade = Cotacoes.oid_Unidade " +
        " AND Unidades.oid_Empresa = Empresas.oid_Empresa " +
        " AND Cotacoes.oid_Cidade          = Cidade_Origem.oid_Cidade " +
        " AND Cotacoes.oid_Cidade_Destino  = Cidade_Destino.oid_Cidade " +
        " AND Cidade_Origem.oid_Regiao_Estado   = Regiao_Estado_Origem.oid_Regiao_Estado " +
        " AND Cidade_Destino.oid_Regiao_Estado  = Regiao_Estado_Destino.oid_Regiao_Estado " +
        " AND Regiao_Estado_Origem.oid_Estado   = Estado_Origem.oid_Estado" +
        " AND Regiao_Estado_Destino.oid_Estado  = Estado_Destino.oid_Estado ";

    if (String.valueOf (ed.getOID_Empresa ()) != null && !String.valueOf (ed.getOID_Empresa ()).equals ("0")) {
      sql += " AND Unidades.OID_Empresa = " + ed.getOID_Empresa ();
    }
    if (String.valueOf (ed.getNR_Cotacao ()) != null && !String.valueOf (ed.getNR_Cotacao ()).equals ("0")) {
      sql += " AND Cotacoes.NR_Cotacao = " + ed.getNR_Cotacao ();
    }
    if (String.valueOf (ed.getOID_Unidade ()) != null && !String.valueOf (ed.getOID_Unidade ()).equals ("0")) {
      sql += " AND Cotacoes.OID_Unidade = " + ed.getOID_Unidade ();
    }
    if (String.valueOf (ed.getOID_Produto ()) != null && !String.valueOf (ed.getOID_Produto ()).equals ("0")) {
      sql += " AND Cotacoes.OID_Produto = " + ed.getOID_Produto ();
    }
    if (String.valueOf (ed.getOID_Modal ()) != null && !String.valueOf (ed.getOID_Modal ()).equals ("0")) {
      sql += " AND Cotacoes.OID_Modal = " + ed.getOID_Modal ();
    }
    if (String.valueOf (ed.getOID_Pessoa ()) != null && !String.valueOf (ed.getOID_Pessoa ()).equals ("")) {
      sql += " AND Cotacoes.OID_Pessoa = '" + ed.getOID_Pessoa () + "'";
    }
    if (String.valueOf (ed.getOID_Pessoa_Destinatario ()) != null && !String.valueOf (ed.getOID_Pessoa_Destinatario ()).equals ("")) {
      sql += " AND Cotacoes.OID_Pessoa_Destinatario = '" + ed.getOID_Pessoa_Destinatario () + "'";
    }
    if (String.valueOf (ed.getOID_Pessoa_Consignatario ()) != null && !String.valueOf (ed.getOID_Pessoa_Consignatario ()).equals ("")) {
      sql += " AND Cotacoes.OID_Pessoa_Consignatario = '" + ed.getOID_Pessoa_Consignatario () + "'";
    }
    if (String.valueOf (ed.getOID_Pessoa_Pagador ()) != null && !String.valueOf (ed.getOID_Pessoa_Pagador ()).equals ("") && !String.valueOf (ed.getOID_Pessoa_Pagador ()).equals ("null")) {
      sql += " AND Cotacoes.OID_Pessoa_Pagador = '" + ed.getOID_Pessoa_Pagador () + "'";
    }
    if (String.valueOf (ed.getDt_Emissao_Inicial ()) != null && !String.valueOf (ed.getDt_Emissao_Inicial ()).equals ("") && !String.valueOf (ed.getDt_Emissao_Inicial ()).equals ("null")) {
      sql += " AND Cotacoes.DT_Emissao >= '" + ed.getDt_Emissao_Inicial () + "'";
    }
    if (String.valueOf (ed.getDt_Emissao_Final ()) != null && !String.valueOf (ed.getDt_Emissao_Final ()).equals ("") && !String.valueOf (ed.getDt_Emissao_Final ()).equals ("null")) {
      sql += " AND Cotacoes.DT_Emissao <= '" + ed.getDt_Emissao_Final () + "'";
    }
    if (String.valueOf (ed.getDM_Situacao ()) != null && !String.valueOf (ed.getDM_Situacao ()).equals ("null") && String.valueOf (ed.getDM_Situacao ()).equals ("C")
        && !String.valueOf (ed.getDM_Situacao ()).equals ("")) {
      sql += " AND Cotacoes.DM_Situacao = 'C'";
    }

    if (String.valueOf (ed.getDM_Origem ()) != null && !String.valueOf (ed.getDM_Origem ()).equals ("null") && String.valueOf (ed.getDM_Origem ()).equals ("C")
        && !String.valueOf (ed.getDM_Origem ()).equals ("")) {
      sql += " AND Cotacoes.oid_cidade = " + ed.getOID_Origem ();
    }
    if (String.valueOf (ed.getDM_Origem ()) != null && !String.valueOf (ed.getDM_Origem ()).equals ("null") && String.valueOf (ed.getDM_Origem ()).equals ("R")
        && !String.valueOf (ed.getDM_Origem ()).equals ("")) {
      sql += " AND Regiao_Estado_Origem.oid_Regiao_Estado = " + ed.getOID_Origem ();
    }
    if (String.valueOf (ed.getDM_Origem ()) != null && !String.valueOf (ed.getDM_Origem ()).equals ("null") && String.valueOf (ed.getDM_Origem ()).equals ("E")
        && !String.valueOf (ed.getDM_Origem ()).equals ("")) {
      sql += " AND Estado_Origem.oid_Estado = " + ed.getOID_Origem ();
    }
    if (String.valueOf (ed.getDM_Destino ()) != null && !String.valueOf (ed.getDM_Destino ()).equals ("null") && String.valueOf (ed.getDM_Destino ()).equals ("C")
        && !String.valueOf (ed.getDM_Destino ()).equals ("")) {
      sql += " AND Cotacoes.oid_cidade_Destino = " + ed.getOID_Destino ();
    }
    if (String.valueOf (ed.getDM_Destino ()) != null && !String.valueOf (ed.getDM_Destino ()).equals ("null") && String.valueOf (ed.getDM_Destino ()).equals ("R")
        && !String.valueOf (ed.getDM_Destino ()).equals ("")) {
      sql += " AND Regiao_Estado_Destino.oid_Regiao_Estado = " + ed.getOID_Destino ();
    }
    if (String.valueOf (ed.getDM_Destino ()) != null && !String.valueOf (ed.getDM_Destino ()).equals ("null") && String.valueOf (ed.getDM_Destino ()).equals ("E")
        && !String.valueOf (ed.getDM_Destino ()).equals ("")) {
      sql += " AND Estado_Destino.oid_Estado = " + ed.getOID_Destino ();
    }
    if (ed.getDM_Relatorio ().equals ("M1")) {
      sql += " ORDER by unidades.oid_Empresa, Cotacoes.dt_emissao, Cotacoes.nr_Cotacao ";
    }
    // System.out.println (" cto emb -> " + sql);

    try {

      //// // System.out.println(sql);

      ResultSet res = null;
      res = this.executasql.executarConsulta (sql.toString ());

      CotacaoRL conRL = new CotacaoRL ();
      if (ed.getDM_Relatorio ().substring (0 , 1).equals ("M")) {
        b = conRL.geraRelCotacao (res , ed);
      }

    }

    catch (Excecoes e) {
      throw e;
    }
    catch (Exception exc) {
      Excecoes exce = new Excecoes ();
      exce.setExc (exc);
      exce.setMensagem ("Erro no m�todo listar");
      exce.setClasse (this.getClass ().getName ());
      exce.setMetodo ("geraRelatorio(CotacaoED ed)");
    }
    return b;
  }


  public byte[] imprime_Cotacao (CotacaoED ed) throws Excecoes {
    ResultSet res = null;
    try {

    String sql = "Select Cotacoes.* ," +
        " Cotacoes.oid_Produto as oid_Produto_Cotacao, " +
        " Cotacoes.VL_Nota_Fiscal as VL_Nota_Fiscal_CTRC, " +
        " Unidades.CD_Unidade, Unidades.oid_pessoa as oid_Pessoa_Unidade, " +
        " Cidade_Unidade.NM_Cidade as NM_Cidade_Unidade, " +
        " Cidade_CTRC_Origem.NM_Cidade as NM_Cidade_CTRC_Origem, " +
        " Cidade_CTRC_Origem.CD_Cidade as CD_Cidade_CTRC_Origem, " +
        " Estado_CTRC_Origem.CD_Estado as CD_Estado_CTRC_Origem, " +
        " Cidade_CTRC_Destino.NM_Cidade as NM_Cidade_CTRC_Destino, " +
        " Cidade_CTRC_Destino.CD_Cidade as CD_Cidade_CTRC_Destino, " +
        " Estado_CTRC_Destino.CD_Estado as CD_Estado_CTRC_Destino, " +
        " Produtos.NM_Produto, " +
        " Modal.CD_Modal, " +
        " Modal.NM_Modal, " +
        " Modal.DM_Tipo_Tabela_Frete " +
        " FROM " +
        " Cotacoes, Unidades, Modal, Pessoas, " +
        " Produtos, " +
        " cidades Cidade_Unidade, " +
        " cidades Cidade_CTRC_Origem, " +
        " estados Estado_CTRC_Origem, " +
        " regioes_estados Regiao_Estado_CTRC_Origem, " +
        " cidades Cidade_CTRC_Destino, " +
        " estados Estado_CTRC_Destino, " +
        " regioes_estados Regiao_Estado_CTRC_Destino " +
        " where " +
        " Cotacoes.oid_modal   = Modal.oid_modal and" +
        " Cotacoes.oid_produto = Produtos.oid_produto and" +
        " Cotacoes.oid_cidade = Cidade_CTRC_Origem.oid_cidade and" +
        " Cotacoes.oid_unidade = Unidades.oid_unidade and" +
        " Unidades.oid_pessoa = Pessoas.oid_Pessoa and " +
        " Pessoas.oid_cidade = Cidade_Unidade.oid_Cidade and " +
        " Cidade_CTRC_Origem.oid_regiao_Estado = Regiao_Estado_CTRC_Origem.oid_regiao_estado and" +
        " Regiao_Estado_CTRC_Origem.oid_Estado = Estado_CTRC_Origem.oid_Estado and" +
        " Cotacoes.oid_cidade_Destino = Cidade_CTRC_Destino.oid_cidade and" +
        " Cidade_CTRC_Destino.oid_regiao_Estado = Regiao_Estado_CTRC_Destino.oid_regiao_estado and" +
        " Regiao_Estado_CTRC_Destino.oid_Estado = Estado_CTRC_Destino.oid_Estado ";
    if (JavaUtil.doValida (ed.getOID_Cotacao ())) {
      sql += " and OID_Cotacao = '" + ed.getOID_Cotacao () + "'";
    }

        // System.out.println ("IMP sql->>" + sql);


    res = executasql.executarConsulta (sql);
    return new CotacaoRL ().imprime_Cotacao (res , executasql , ed);

    }
    catch (Exception e) {
      e.printStackTrace ();
      throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "imprime_CotacaoMatricial(ResultSet res, ExecutaSQL sql)");
    }
    finally {
      util.closeResultset (res);
    }
  }


  public CotacaoED copia_Cotacao (CotacaoED ed) throws Excecoes {

    CotacaoED conED = new CotacaoED ();
    conED = this.getByRecord(ed);
    try {
      conED.setNR_Cotacao(0);
      conED.setDM_Impresso("N");
      conED.setDT_Emissao(Data.getDataDMY());
      conED.setVL_FRETE_PESO (0);
      conED.setVL_FRETE_VALOR (0);
      conED.setVL_ICMS (0);
      conED.setVL_PEDAGIO (0);
      conED.setVL_TOTAL_FRETE (0);
      conED.setVL_BASE_CALCULO_ICMS (0);
      conED = this.inclui (conED);      
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "inclui(CotacaoED ed)");
    }
    return conED;
  }

}
