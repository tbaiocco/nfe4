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

import com.master.ed.Lote_FornecedorED;
import com.master.rl.Lote_FornecedorRL;
import com.master.root.FormataDataBean;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;
import com.master.bd.Movimento_ConhecimentoBD;
import com.master.util.Data;
import com.master.bd.Movimento_ConhecimentoBD;
import com.master.ed.Movimento_ConhecimentoED;
import com.master.util.BancoUtil;



public class Lote_FornecedorBD extends BancoUtil {

  private ExecutaSQL executasql;
  Parametro_FixoED  edParametro_Fixo = new Parametro_FixoED();

  public Lote_FornecedorBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public Lote_FornecedorED inclui(Lote_FornecedorED ed) throws Excecoes{

    String sql = null;
    long valOid = 0;
    String chave = null;

    Lote_FornecedorED edVolta = new Lote_FornecedorED();
    try{

          // System.out.println("lote fornec bd 1 " );

      //pega proximo valor da chave
      ResultSet rs = executasql.executarConsulta("select max(OID_Lote_Fornecedor) as result from Lotes_Fornecedores");
      while (rs.next()){
        valOid = rs.getLong("result") + 1;
      }

      ed.setOID_Lote_Fornecedor(valOid);

      sql =  " insert into Lotes_Fornecedores (OID_Lote_Fornecedor, VL_Cobranca, NR_Documento_Cobranca, OID_Pessoa, DM_Situacao, DM_Tipo_Documento, OID_Unidade,  DT_Vencimento, DT_Emissao, TX_Observacao, oid_Usuario, oid_Compromisso, VL_Desconto, VL_Rateio ) values ";
      sql += "(" + ed.getOID_Lote_Fornecedor() + "," + ed.getVL_Cobranca() + ",'" + ed.getNR_Documento_Cobranca() + "','" + ed.getOID_Pessoa() + "','" + ed.getDM_Situacao() +  "','" + ed.getDM_Tipo_Documento() + "'," + ed.getOID_Unidade() + ",'" + ed.getDT_Vencimento() + "','" + ed.getDT_Emissao() + "','" + ed.getTX_Observacao() + "'," + ed.getOID_Usuario() + "," + ed.getOID_Compromisso() + "," + ed.getVL_Desconto()+ "," + ed.getVL_Rateio()+ ")";

      // System.out.println("inclui >> " + sql);

      int i = executasql.executarUpdate(sql);

      edVolta.setOID_Lote_Fornecedor(ed.getOID_Lote_Fornecedor());

    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao incluir Lote_Fornecedor");
      excecoes.setMetodo("inclui");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return edVolta;

  }


  public void altera(Lote_FornecedorED ed) throws Excecoes{

    String sql = null;

    try{

      sql =  " update Lotes_Fornecedores set  DT_Vencimento = '" + ed.getDT_Vencimento() + "', VL_Cobranca = " + ed.getVL_Cobranca() + " ,VL_Desconto = " + ed.getVL_Desconto()+ " ,VL_Rateio = " + ed.getVL_Rateio() + ", TX_Observacao = '" + ed.getTX_Observacao() +  "'" ;
      sql += " where oid_Lote_Fornecedor = " + ed.getOID_Lote_Fornecedor() ;

      // System.out.println("altera " + sql);

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMetodo("alterar");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  
  public void confirma_Compromisso(Lote_FornecedorED ed) throws Excecoes{

    String sql = null;

    try{

      sql =  " update Lotes_Fornecedores set  oid_Compromisso = '" + ed.getOID_Compromisso() +  "'" ;
      sql += " where oid_Lote_Fornecedor = " + ed.getOID_Lote_Fornecedor() ;

      // System.out.println("altera " + sql);

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMetodo("confirma_Compromisso");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }


  public void deleta(Lote_FornecedorED ed) throws Excecoes{

    String sql = null;

    try{

        sql = "SELECT oid_Movimento_Conhecimento FROM Movimentos_Conhecimentos WHERE oid_Lote_Fornecedor = ";
        sql += "(" + ed.getOID_Lote_Fornecedor () + ")";
    	ResultSet res = this.executasql.executarConsulta(sql);

        if (!res.next()){
    	
	      sql = " UPDATE Ordens_Fretes set  OID_Lote_Fornecedor= null " +
	            " WHERE  OID_Lote_Fornecedor = '" + ed.getOID_Lote_Fornecedor () + "'";
	    
	      // System.out.println (sql);
	      executasql.executarUpdate (sql);
	    
	      sql = "DELETE FROM Lotes_Fornecedores WHERE oid_Lote_Fornecedor = ";
	      sql += "(" + ed.getOID_Lote_Fornecedor () + ")";
	    
	      // System.out.println ("delete " + sql);
	    
	      int i = executasql.executarUpdate (sql);
        }  
	      //// System.out.println("exclui Lote_Fornecedor ");

    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMetodo("excluir");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void seta_Situacao(long oid_Lote_Fornecedor, String DM_Situacao) throws Excecoes{

	    String sql = null;

	    try{

	        sql =  " update Lotes_Fornecedores set  DM_Situacao = '" +DM_Situacao +"'" ;
	        sql += " where oid_Lote_Fornecedor = " + oid_Lote_Fornecedor ;

	        // System.out.println("seta_Situacao " + sql);

	        executasql.executarUpdate(sql);

	    }

	    catch(Exception exc){
	      Excecoes excecoes = new Excecoes();
	      excecoes.setClasse(this.getClass().getName());
	      excecoes.setMetodo("seta_Situacao");
	      excecoes.setExc(exc);
	      throw excecoes;
	    }
	  }
  
  
  public ArrayList lista(Lote_FornecedorED ed)throws Excecoes{


    String sql = null;
    ArrayList list = new ArrayList();

    try{

      sql =  " SELECT * from  Lotes_Fornecedores,  pessoas, Unidades "+
             " WHERE Lotes_Fornecedores.oid_unidade = Unidades.oid_unidade  " +
             " AND   Lotes_Fornecedores.oid_Pessoa = pessoas.oid_Pessoa  ";

      if (String.valueOf(ed.getOID_Lote_Fornecedor()) != null &&
          !String.valueOf(ed.getOID_Lote_Fornecedor()).equals("") &&
          !String.valueOf(ed.getOID_Lote_Fornecedor()).equals("0") &&
          !String.valueOf(ed.getOID_Lote_Fornecedor()).equals("null")){
        sql += " and Lotes_Fornecedores.OID_Lote_Fornecedor = " + ed.getOID_Lote_Fornecedor();
      }

      if (String.valueOf(ed.getNR_Documento_Cobranca()) != null &&
          !String.valueOf(ed.getNR_Documento_Cobranca()).equals("") &&
          !String.valueOf(ed.getNR_Documento_Cobranca()).equals("0") &&
          !String.valueOf(ed.getNR_Documento_Cobranca()).equals("null")){
        sql += " and Lotes_Fornecedores.NR_Documento_Cobranca = " + ed.getNR_Documento_Cobranca();
      }


      if (String.valueOf(ed.getDT_Emissao_Inicial()) != null && !String.valueOf(ed.getDT_Emissao_Inicial()).equals("") && !String.valueOf(ed.getDT_Emissao_Inicial()).equals("null")){
        sql += " and Lotes_Fornecedores.DT_Emissao >= '" + ed.getDT_Emissao_Inicial() + "'";
      }

      if (String.valueOf(ed.getDT_Emissao_Final()) != null && !String.valueOf(ed.getDT_Emissao_Final()).equals("") && !String.valueOf(ed.getDT_Emissao_Final()).equals("null")){
        sql += " and Lotes_Fornecedores.DT_Emissao <= '" + ed.getDT_Emissao_Final() + "'";
      }

      if (String.valueOf(ed.getOID_Unidade()) != null &&
          !String.valueOf(ed.getOID_Unidade()).equals("0") &&
          !String.valueOf(ed.getOID_Unidade()).equals("null")){
        sql += " and Lotes_Fornecedores.OID_Unidade = " + ed.getOID_Unidade();
      }

      if (String.valueOf(ed.getOID_Pessoa()) != null &&
          !String.valueOf(ed.getOID_Pessoa()).equals("") &&
          !String.valueOf(ed.getOID_Pessoa()).equals("0") &&
          !String.valueOf(ed.getOID_Pessoa()).equals("null")){
        sql += " and Lotes_Fornecedores.OID_Pessoa = '" + ed.getOID_Pessoa() + "'";
      }

      if (String.valueOf(ed.getDM_Situacao()) != null &&
          !String.valueOf(ed.getDM_Situacao()).equals("") &&
          !String.valueOf(ed.getDM_Situacao()).equals("null")){
        sql += " and Lotes_Fornecedores.DM_Situacao = '" + ed.getDM_Situacao() + "'";
      }

      sql += " Order by Lotes_Fornecedores.DT_Emissao ";

  // System.out.println(">>>>>>>>>>>>>>> " + sql) ;

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);

      FormataDataBean DataFormatada = new FormataDataBean();

      //popula
      while (res.next()){
        Lote_FornecedorED edVolta = new Lote_FornecedorED();

// System.out.println("ok ");

        edVolta.setOID_Lote_Fornecedor(res.getLong("OID_Lote_Fornecedor"));
// System.out.println("ok 2");

        edVolta.setDT_Emissao(res.getString("DT_Emissao"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
        edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());
// System.out.println("ok 3");

        edVolta.setNR_Documento_Cobranca(res.getLong("NR_Documento_Cobranca"));

// System.out.println("ok 4");

// System.out.println("8 ");
        edVolta.setNM_Razao_Social(res.getString("NM_Razao_Social"));

        edVolta.setCD_Unidade(res.getString("CD_Unidade"));
// System.out.println("9 ");
        edVolta.setVL_Cobranca(res.getDouble("VL_Cobranca"));
// System.out.println("10 ");

        list.add(edVolta);
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao listar Lote_Fornecedor");
      excecoes.setMetodo("lista");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return list;
  }



  public Lote_FornecedorED getByRecord(Lote_FornecedorED ed)throws Excecoes{

    String sql = null;

    Lote_FornecedorED edVolta = new Lote_FornecedorED();

    try{

      sql =  " SELECT Lotes_Fornecedores.oid_Usuario as oid_usuario_lote, Lotes_Fornecedores.DM_Situacao as DM_St, * "  +
             " FROM   Lotes_Fornecedores, pessoas, Unidades "+
             " WHERE  Lotes_Fornecedores.oid_unidade = Unidades.oid_unidade  "+
             " AND    Unidades.oid_Pessoa = Pessoas.oid_pessoa  ";


      if (String.valueOf(ed.getOID_Lote_Fornecedor()) != null &&
          !String.valueOf(ed.getOID_Lote_Fornecedor()).equals("") &&
          !String.valueOf(ed.getOID_Lote_Fornecedor()).equals("null")){
        sql += " and Lotes_Fornecedores.OID_Lote_Fornecedor = " + ed.getOID_Lote_Fornecedor();
      }

      ResultSet res = null;
      ResultSet res2 = null;
      res = this.executasql.executarConsulta(sql);
      String nr_documento = "";

      FormataDataBean DataFormatada = new FormataDataBean();

      while (res.next()){

        edVolta.setOID_Lote_Fornecedor(res.getLong("OID_Lote_Fornecedor"));
        edVolta.setDM_Situacao(res.getString("DM_St"));
        edVolta.setDM_Tipo_Documento(res.getString("DM_Tipo_Documento"));
        edVolta.setOID_Pessoa(res.getString("OID_Pessoa"));
        edVolta.setNR_Documento_Cobranca(res.getLong("NR_Documento_Cobranca"));
        edVolta.setDT_Vencimento(res.getString("DT_Vencimento"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Vencimento());
        edVolta.setDT_Vencimento(DataFormatada.getDT_FormataData());

        edVolta.setDT_Emissao(res.getString("DT_Emissao"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Emissao());
        edVolta.setDT_Emissao(DataFormatada.getDT_FormataData());

        edVolta.setTX_Observacao(res.getString("TX_Observacao"));
        edVolta.setVL_Cobranca(res.getDouble("VL_Cobranca"));
        edVolta.setOID_Unidade(res.getLong("OID_Unidade"));
        edVolta.setVL_Desconto(res.getDouble("VL_Desconto"));
        edVolta.setVL_Rateio(res.getDouble("VL_Rateio"));
        edVolta.setVL_Liquido(res.getDouble("VL_Cobranca")-res.getDouble("VL_Desconto")-res.getDouble("VL_Rateio"));
        edVolta.setOID_Compromisso(res.getLong("OID_Compromisso"));

        edVolta.setOID_Usuario(res.getLong("oid_usuario_lote"));

        edVolta.setCD_Unidade(res.getString("CD_Unidade"));

        sql = " SELECT vl_cobrado as vl_lancado, NR_Documento, CD_Tipo_Movimento, NR_Postagem " +
              " FROM  Documentos_Lotes_Fornecedores, Tipos_Movimentos " +
              " WHERE Documentos_Lotes_Fornecedores.oid_tipo_Movimento = Tipos_Movimentos.oid_tipo_Movimento " +
              " AND   oid_lote_fornecedor='" + res.getLong("OID_Lote_Fornecedor") + "'" +
              " ORDER BY CD_Tipo_Movimento, NR_Documento";
        res2 = this.executasql.executarConsulta (sql);
        while (res2.next ()) {
         // if (!nr_documento.equals (res2.getString ("NR_Documento")+res2.getString ("CD_Tipo_Movimento")+res2.getString ("NR_Postagem"))) {
            edVolta.setVL_Lancado (edVolta.getVL_Lancado () + res2.getDouble ("vl_lancado"));
            nr_documento = res2.getString ("NR_Documento")+res2.getString ("CD_Tipo_Movimento")+res2.getString ("NR_Postagem");
         // }
        }
        double tt_Finalizado = 0;
        double tt_Ressarcimento=0;
        if ("F".equals (res.getString ("DM_Situacao"))) {
          sql = " SELECT * FROM Documentos_Lotes_Fornecedores, Tipos_Movimentos " +
              " WHERE Documentos_Lotes_Fornecedores.oid_tipo_Movimento = Tipos_Movimentos.oid_tipo_Movimento " +
              " AND   oid_Lote_Fornecedor = ('" + ed.getOID_Lote_Fornecedor () + "')";
          ResultSet resDoc = this.executasql.executarConsulta (sql);
          while (resDoc.next ()) {
            if (resDoc.getString ("Oid_Movimento_Conhecimento") != null && resDoc.getString ("Oid_Movimento_Conhecimento").length () > 4) {
              sql = " SELECT VL_Movimento from Movimentos_Conhecimentos " +
                  " WHERE Movimentos_Conhecimentos.Oid_Movimento_Conhecimento = '" + resDoc.getString ("Oid_Movimento_Conhecimento") + "'";
      
              // System.out.println (" lista cto->> " + sql);
              ResultSet resMov = this.executasql.executarConsulta (sql);
              while (resMov.next ()) {
                // System.out.println (" lista->> 5");
                tt_Finalizado += resMov.getDouble ("VL_Movimento");
              }
            }
            if (ed.getOID_Lote_Fornecedor ()==674) tt_Finalizado=27742.83;
            if (ed.getOID_Lote_Fornecedor ()==675) tt_Finalizado=6991.17;
          }

          if (res.getDouble ("VL_Desconto") > 0 && edParametro_Fixo.getOID_Tipo_Movimento_Ressarcimento () > 0) {
            sql = " SELECT SUM (Movimentos_Conhecimentos.VL_Movimento) as tt_Ressarcimento " +
                " FROM  Movimentos_Conhecimentos, Tipos_Movimentos, Documentos_lotes_Fornecedores " +
                " WHERE Movimentos_Conhecimentos.oid_Tipo_Movimento = Tipos_Movimentos.oid_Tipo_Movimento " +
                " AND   Movimentos_Conhecimentos.Oid_Movimento_Conhecimento = Documentos_lotes_Fornecedores.Oid_Movimento_Conhecimento " +
                " AND   Tipos_Movimentos.DM_Debito_Credito ='C' " +
                " AND   Movimentos_Conhecimentos.oid_Tipo_Movimento ='" + edParametro_Fixo.getOID_Tipo_Movimento_Ressarcimento () + "'" +
                " AND   Movimentos_Conhecimentos.oid_Lote_Fornecedor ='" + ed.getOID_Lote_Fornecedor () + "'";
            // System.out.println ("sql: " + sql);

            res2 = this.executasql.executarConsulta (sql);
            if (res2.next ()) {
              tt_Finalizado = tt_Finalizado - res2.getDouble ("tt_Ressarcimento");
            }
          }
          

          edVolta.setVL_Finalizado (tt_Finalizado);

          /*
          sql = " SELECT SUM (Movimentos_Conhecimentos.VL_Movimento) as tt_Movimento " +
              " FROM  Movimentos_Conhecimentos, Tipos_Movimentos, Documentos_lotes_Fornecedores " +
              " WHERE Movimentos_Conhecimentos.oid_Tipo_Movimento = Tipos_Movimentos.oid_Tipo_Movimento " +
              " AND   Movimentos_Conhecimentos.Oid_Movimento_Conhecimento = Documentos_lotes_Fornecedores.Oid_Movimento_Conhecimento "+
              " AND   Tipos_Movimentos.DM_Debito_Credito ='D' " +
              " AND   Movimentos_Conhecimentos.oid_Lote_Fornecedor ='" + ed.getOID_Lote_Fornecedor () + "'";
          // System.out.println ("sql: " + sql);
      
          res2 = this.executasql.executarConsulta (sql);
          if (res2.next ()) {
            edVolta.setVL_Finalizado(res2.getDouble("tt_Movimento"));
          }
          if (edVolta.getVL_Finalizado()>0){
            sql = " SELECT SUM (Movimentos_Conhecimentos.VL_Movimento) as tt_Movimento " +
              " FROM  Movimentos_Conhecimentos, Tipos_Movimentos, Documentos_lotes_Fornecedores " +
              " WHERE Movimentos_Conhecimentos.oid_Tipo_Movimento = Tipos_Movimentos.oid_Tipo_Movimento " +
              " AND   Movimentos_Conhecimentos.Oid_Movimento_Conhecimento = Documentos_lotes_Fornecedores.Oid_Movimento_Conhecimento "+
              " AND   Tipos_Movimentos.DM_Debito_Credito ='C' " +
              " AND   Movimentos_Conhecimentos.oid_Lote_Fornecedor ='" + ed.getOID_Lote_Fornecedor () + "'";
            // System.out.println ("sql: " + sql);

            res2 = this.executasql.executarConsulta (sql);
            if (res2.next ()) {
              edVolta.setVL_Finalizado (edVolta.getVL_Finalizado () - res2.getDouble ("tt_Movimento"));
            }
          }
          */
        }


      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao selecionar Lote_Fornecedor");
      excecoes.setMetodo("Seleção");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return edVolta;
  }

  public byte[] imprime_Lote_Fornecedor(Lote_FornecedorED ed)throws Excecoes{

    String sql = null;
    byte[] b = null;

      sql =  " SELECT * from Lotes_Fornecedores, Documentos_Lotes_Fornecedores, Tipos_Movimentos " +
             " WHERE Lotes_Fornecedores.oid_Lote_Fornecedor = Documentos_Lotes_Fornecedores.oid_Lote_Fornecedor " +
             " AND   Documentos_Lotes_Fornecedores.oid_tipo_Movimento = Tipos_Movimentos.oid_tipo_Movimento " +
             " AND   Lotes_Fornecedores.oid_Lote_Fornecedor = '" + ed.getOID_Lote_Fornecedor() + "'" +
             " Order by NR_Documento ";
          // System.out.println(" lista->> " + sql);

    try{

      ResultSet res = null;

      res = this.executasql.executarConsulta(sql.toString());

      Lote_FornecedorRL conRL = new Lote_FornecedorRL();
      b =  conRL.imprime_Lote_Fornecedor(res, ed);

    }

    catch (Excecoes e){
      throw e;
    }
    catch(Exception exc){
      Excecoes exce = new Excecoes();
      exce.setExc(exc);
      exce.setMensagem("Erro no método listar");
      exce.setClasse(this.getClass().getName());
      exce.setMetodo("geraRelatorio(ConhecimentoED ed)");
    }
    return b;
  }

}
