package com.master.bd;

/**
 * Título: WMS_Movimentos_ProdutosBD
 * Descrição: Movimentos - BD
 * Data da criação: 12/2003
 * Atualizado em: 12/2003
 * Empresa: ÊxitoLogística Mastercom
 * Autor: Carlos Eduardo de Holleben
*/

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.WMS_Movimentos_ProdutosED;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

public class WMS_Movimentos_ProdutosBD {

  private ExecutaSQL executasql;

  public WMS_Movimentos_ProdutosBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public WMS_Movimentos_ProdutosED inclui(WMS_Movimentos_ProdutosED ed) throws Excecoes{

    String sql = null;
    int valOid = 0;
    WMS_Movimentos_ProdutosED WMS_Movimentos_ProdutosED = new WMS_Movimentos_ProdutosED();

    try{
      String data_mov = "'"+ed.getDT_Movimentacao()+"'";
      String hora_mov = ed.getHR_Movimentacao();
      if( hora_mov == null || hora_mov.equals( "" ) || hora_mov.equals( "null" ) )
        hora_mov = "";
      if( ed.getDT_Movimentacao() == null || ed.getDT_Movimentacao().equals( "" ) || ed.getDT_Movimentacao().equals( "null" ) )
        data_mov = null;

      ResultSet rs = executasql.executarConsulta("select max(Oid_Movimento_Produto) as result from Movimentos_Produtos");

      while (rs.next()) valOid = rs.getInt("result");

      sql = "insert into Movimentos_Produtos (Oid_Movimento_Produto,Oid_Produto_Cliente,Oid_Requisicao_Produto,Nr_Quantidade_Requerida,Dt_Movimentacao,Hr_Movimentacao,Oid_Operador,Dm_Tipo_Movimento)"+
            " values ("+ ++valOid + ",'" + ed.getOID_Produto_Cliente() +  "'," +ed.getOID_Requisicao_Produto() + "," + ed.getNR_Quantidade_Requerida() +"," + data_mov +",'" + hora_mov +"',"+ ed.getOID_Operador() +",'" + ed.getDM_Tipo_Movimento() +"')";


      int i = executasql.executarUpdate(sql);
      WMS_Movimentos_ProdutosED.setOID_Movimento_Produto( valOid );
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Movimentação já registrada!");
      excecoes.setMetodo("inclui()");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return WMS_Movimentos_ProdutosED;

  }

  public void altera(WMS_Movimentos_ProdutosED ed) throws Excecoes{

    String sql = null;

    try{

      sql = " update Movimentos_Produtos set ";
      sql += " OID_Produto_Cliente = '" + ed.getOID_Produto_Cliente() + "', ";
      sql += " OID_Requisicao_Produto = " + ed.getOID_Requisicao_Produto() + ", ";
      sql += " NR_Quantidade_Efetiva = " + ed.getNR_Quantidade_Efetiva() + ", ";
      sql += " NR_Quantidade_Requerida = " + ed.getNR_Quantidade_Requerida() + ", ";
      sql += " DT_Movimentacao = '" + ed.getDT_Movimentacao() + "', ";
      sql += " HR_Movimentacao = '" + ed.getHR_Movimentacao() + "', ";
      sql += " OID_Operador = " + ed.getOID_Operador() + ", ";
      sql += " DM_Tipo_Movimento = '" + ed.getDM_Tipo_Movimento() + "' ";
      sql += " where Oid_Movimento_Produto = " + ed.getOID_Movimento_Produto();

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar dados da Movimentação");
      excecoes.setMetodo("altera()");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void deleta(WMS_Movimentos_ProdutosED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from Movimentos_Produtos WHERE Oid_Movimento_Produto = ";
      sql +=  ed.getOID_Movimento_Produto();

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao deletar Movimentação");
      excecoes.setMetodo("deleta()");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public WMS_Movimentos_ProdutosED getByRecord(WMS_Movimentos_ProdutosED ed)throws Excecoes{

    String sql = null;
    String dt_d = "";
    String dt_m = "";
    String dt_a = "";

    WMS_Movimentos_ProdutosED edVolta = new WMS_Movimentos_ProdutosED();

    try{

      sql = " select * from Movimentos_Produtos";

      if ( !String.valueOf(ed.getOID_Movimento_Produto()).equals("0")){
        sql += " where OID_Movimento_Produto = " + ed.getOID_Movimento_Produto();
      }

      ResultSet res = null, res2 = null;

      res = this.executasql.executarConsulta(sql);
      while (res.next()){
        edVolta = new WMS_Movimentos_ProdutosED();
        edVolta.setOID_Movimento_Produto( res.getInt("OID_Movimento_Produto") );
        edVolta.setOID_Produto_Cliente(res.getString("OID_Produto_Cliente"));

        sql = "select * from produtos_clientes where oid_produto_cliente = '" + res.getString("OID_Produto_Cliente")+"'";
        res2 = this.executasql.executarConsulta(sql);
        while (res2.next()){
          edVolta.setOID_Pessoa( res2.getString( "OID_Pessoa" ) );
          edVolta.setOID_Produto( res2.getInt( "OID_Produto" ) );
        }

        sql = "select * from pessoas where oid_pessoa = '" + edVolta.getOID_Pessoa()+"'";
        res2 = this.executasql.executarConsulta(sql);
        while (res2.next()){
          edVolta.setNM_Razao_Social( res2.getString( "NM_Razao_Social" ) );
          edVolta.setNR_CNPJ_CPF( res2.getString( "NR_CNPJ_CPF" ) );
        }

        sql = "select * from produtos where oid_produto = " + edVolta.getOID_Produto();
        res2 = this.executasql.executarConsulta(sql);
        while (res2.next()){
          edVolta.setCD_Produto( res2.getString( "CD_Produto" ) );
          edVolta.setNM_Produto( res2.getString( "NM_Produto" ) );
        }

        edVolta.setOID_Requisicao_Produto(res.getInt("OID_Requisicao_Produto"));
        edVolta.setNR_Quantidade_Efetiva(res.getInt("NR_Quantidade_Efetiva"));
        edVolta.setNR_Quantidade_Requerida(res.getInt("NR_Quantidade_Requerida"));

        if( res.getString( "DT_Movimentacao" ) != null && res.getString( "DT_Movimentacao" ).substring( 4, 5 ).equals( "-" ) ){
            dt_a = res.getString( "DT_Movimentacao" ).substring( 0, 4 );
            dt_d = res.getString( "DT_Movimentacao" ).substring( 8, 10 );
            dt_m = res.getString( "DT_Movimentacao" ).substring( 5, 7 );
            edVolta.setDT_Movimentacao( dt_d + "/" + dt_m + "/" + dt_a );
        }else edVolta.setDT_Movimentacao( "" );

        edVolta.setHR_Movimentacao(res.getString("HR_Movimentacao"));

        sql = "select * from operadores where oid_operador = " + res.getInt("OID_Operador");
        res2 = this.executasql.executarConsulta(sql);
        while (res2.next()){
          edVolta.setNM_Operador( res2.getString( "NM_Operador" ) );
        }

        edVolta.setOID_Operador(res.getInt("OID_Operador"));
        edVolta.setDM_Tipo_Movimento(res.getString("DM_Tipo_Movimento"));
      }

    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao recuperar registro");
      excecoes.setMetodo("getByRecord()");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return edVolta;
  }

  public ArrayList getAll()throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();
  /*
    WMS_Movimentos_ProdutosED edVolta = new WMS_Movimentos_ProdutosED();

    try{

      sql = " select * from Tipos_Movimentos_Produtos";
        sql += " where 1=1 ";


      ResultSet res = null;

      res = this.executasql.executarConsulta(sql);
      while (res.next()){
        edVolta = new WMS_Movimentos_ProdutosED();
        edVolta.setOid_Movimentos_Produtos_Produto(new Integer(res.getString("Oid_Movimentos_Produtos_Produto")).intValue());
        edVolta.setNm_Descricao(res.getString("Nm_Descricao"));
        edVolta.setDm_Origem(res.getString("Dm_Origem"));
        edVolta.setDm_Destino(res.getString("Dm_Destino"));
        edVolta.setDm_Situacao(res.getString("Dm_Situacao"));
        edVolta.setDm_NF(res.getString("Dm_NF"));

        list.add(edVolta);
      }

    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao recuperar registro");
      excecoes.setMetodo("getByAll()");
      excecoes.setExc(exc);
      throw excecoes;
    } */

    return list;
  }

  public ArrayList lista( WMS_Movimentos_ProdutosED ed, String orderby )throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();
    ResultSet res = null;
    ResultSet res2 = null;
    String order_by = "";

    String dt_d = "";
    String dt_m = "";
    String dt_a = "";

    try{

      sql = "SELECT * FROM movimentos_produtos WHERE oid_requisicao_produto =" + ed.getOID_Requisicao_Produto();
      order_by += orderby;
      sql += order_by;

      res = this.executasql.executarConsulta(sql);

      //popula
      while (res.next()){
        WMS_Movimentos_ProdutosED edVolta = new WMS_Movimentos_ProdutosED();
        edVolta.setOID_Movimento_Produto( res.getInt( "OID_Movimento_Produto" ) );

        sql = "select * from produtos_clientes where oid_produto_cliente = '"+ res.getString( "oid_produto_cliente" ) +"'";
        res2 = this.executasql.executarConsulta(sql);
        while (res2.next()){
            edVolta.setOID_Produto( res2.getInt( "OID_Produto" ) );
            edVolta.setOID_Pessoa( res2.getString( "OID_Pessoa" ) );
        }
        edVolta.setOID_Produto_Cliente( res.getString( "OID_Produto_Cliente" ) );

        sql = "select * from pessoas where oid_pessoa = '"+ edVolta.getOID_Pessoa() +"'";
        res2 = this.executasql.executarConsulta(sql);
        while (res2.next()){
            edVolta.setNR_CNPJ_CPF( res2.getString( "nr_cnpj_cpf" ) );
            edVolta.setNM_Razao_Social( res2.getString( "nm_razao_social" ) );
        }

        sql = "select * from produtos where oid_produto = "+ edVolta.getOID_Produto();
        res2 = this.executasql.executarConsulta(sql);
        while (res2.next()){
            edVolta.setCD_Produto( res2.getString( "CD_Produto" ) );
            edVolta.setNM_Produto( res2.getString( "NM_Produto" ) );
        }

        sql = "select * from operadores where oid_operador = "+res.getString( "oid_operador" );
        res2 = this.executasql.executarConsulta(sql);
        while (res2.next()){
            edVolta.setNM_Operador( res2.getString( "NM_Operador" ) );
        }
        edVolta.setOID_Operador( Integer.valueOf( res.getString( "oid_operador" ) ).intValue() );

        edVolta.setOID_Requisicao_Produto( res.getInt( "OID_Requisicao_Produto" ) );
        edVolta.setNR_Quantidade_Requerida( res.getInt( "NR_Quantidade_Requerida" ) );
        edVolta.setNR_Quantidade_Efetiva( res.getInt( "NR_Quantidade_Efetiva" ) );

        if( res.getString( "DM_Tipo_Movimento" ).equals( "E" ) )
          edVolta.setDM_Tipo_Movimento( "ENTRADA" );
        else
        if( res.getString( "DM_Tipo_Movimento" ).equals( "S" ) )
          edVolta.setDM_Tipo_Movimento( "SAÍDA" );
        else
        if( res.getString( "DM_Tipo_Movimento" ).equals( "T" ) )
          edVolta.setDM_Tipo_Movimento( "TRANSFERÊNCIA" );

        if( res.getString( "DT_Movimentacao" ) != null && res.getString( "DT_Movimentacao" ).substring( 4, 5 ).equals( "-" ) ){
            dt_a = res.getString( "DT_Movimentacao" ).substring( 0, 4 );
            dt_d = res.getString( "DT_Movimentacao" ).substring( 8, 10 );
            dt_m = res.getString( "DT_Movimentacao" ).substring( 5, 7 );
            edVolta.setDT_Movimentacao( dt_d + "/" + dt_m + "/" + dt_a );
        }else edVolta.setDT_Movimentacao( "" );

        edVolta.setHR_Movimentacao( res.getString( "HR_Movimentacao" ) );

        list.add(edVolta);
      }

    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao listar Movimentos - SQL="+sql);
      excecoes.setMetodo("lista(WMS_Movimentos_ProdutosED ed)");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return list;
  }

  public void geraRelatorio(WMS_Movimentos_ProdutosED ed)throws Excecoes{

//    String sql = null;
//
//    WMS_Movimentos_ProdutosED edVolta = new WMS_Movimentos_ProdutosED();
//
//    try{
//
//      sql = "select * from Movimentos_Produtoses where Oid_Movimentos_Produtos_Produto > 0";
//
//      if (ed.getCd_Movimentos_Produtos() != null && !ed.getCd_Movimentos_Produtos().equals("")){
//        sql += " and Cd_Movimentos_Produtos = '" + ed.getCd_Movimentos_Produtos() + "'";
//      }
//      if (ed.getCd_Remessa() != null && !ed.getCd_Remessa().equals("")){
//        sql += " and Cd_Remessa = '" + ed.getCd_Remessa() + "'";
//      }
//
//      ResultSet res = null;
//      res = this.executasql.executarConsulta(sql);
//
//      WMS_Movimentos_ProdutosRL WMS_Movimentos_Produtos_rl = new WMS_Movimentos_ProdutosRL();
//      WMS_Movimentos_Produtos_rl.geraRelatEstoque(res);
//    }
//    catch (Excecoes e){
//      throw e;
//    }
//    catch(Exception exc){
//      Excecoes exce = new Excecoes();
//      exce.setExc(exc);
//      exce.setMensagem("Erro no método listar");
//      exce.setClasse(this.getClass().getName());
//      exce.setMetodo("geraRelatorio(WMS_Movimentos_ProdutosED ed)");
//    }
//
  }

}