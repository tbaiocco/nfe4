package com.master.bd;

/**
 * Título: WMS_Series_ProdutosBD
 * Descrição: Series - BD
 * Data da criação: 12/2003
 * Atualizado em: 12/2003
 * Empresa: ÊxitoLogística Mastercom
 * Autor: Carlos Eduardo de Holleben
*/

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.WMS_Series_ProdutosED;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

public class WMS_Series_ProdutosBD {

  private ExecutaSQL executasql;

  public WMS_Series_ProdutosBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public WMS_Series_ProdutosED inclui(WMS_Series_ProdutosED ed) throws Excecoes{

    String sql = null;
    int valOid = 0;
    WMS_Series_ProdutosED WMS_Series_ProdutosED = new WMS_Series_ProdutosED();

    try{
      ResultSet rs = executasql.executarConsulta("select max(Oid_Serie_Produto) as result from Series_Produtos");

      while (rs.next()) valOid = rs.getInt("result");

      sql = "insert into Series_Produtos (Oid_Serie_Produto,Oid_Movimento_Produto,NR_Fabrica) values ("+ ++valOid + "," + ed.getOID_Movimento_Produto() +  ", '" +ed.getNR_Fabrica()+"')";


      int i = executasql.executarUpdate(sql);
      WMS_Series_ProdutosED.setOID_Serie_Produto( valOid );
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Nº de Série já registrado!");
      excecoes.setMetodo("inclui()");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return WMS_Series_ProdutosED;

  }

  public void altera(WMS_Series_ProdutosED ed) throws Excecoes{

    String sql = null;

    try{

      sql = " update Series_Produtos set ";
      sql += " Oid_Movimento_Produto = " + ed.getOID_Movimento_Produto() + ", ";
      sql += " NR_Fabrica = '" + ed.getNR_Fabrica() + "' ";
      sql += " where Oid_Serie_Produto = " + ed.getOID_Serie_Produto();

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar dados do Nº de Série");
      excecoes.setMetodo("altera()");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void deleta(WMS_Series_ProdutosED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from Series_Produtos WHERE Oid_Serie_Produto = ";
      sql += ed.getOID_Serie_Produto();

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao deletar Nº de Série");
      excecoes.setMetodo("deleta()");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public WMS_Series_ProdutosED getByRecord(WMS_Series_ProdutosED ed)throws Excecoes{

    String sql = null;

    WMS_Series_ProdutosED edVolta = new WMS_Series_ProdutosED();

    try{

      sql = " select * from Series_Produtos";

      if ( !String.valueOf(ed.getOID_Serie_Produto()).equals("0")){
        sql += " where Oid_Serie_Produto = " + ed.getOID_Serie_Produto();
      }

      ResultSet res = null;

      res = this.executasql.executarConsulta(sql);
      while (res.next()){
        edVolta = new WMS_Series_ProdutosED();
        edVolta.setOID_Serie_Produto(res.getInt("OID_Serie_Produto"));
        edVolta.setOID_Movimento_Produto(res.getInt("OID_Movimento_Produto"));
        edVolta.setNR_Fabrica( res.getString("NR_Fabrica") );
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

  public int contByOid_Movimento_Produto(int oid_movimento_produto)throws Excecoes{

    String sql = null;
    int cont = 0;
    WMS_Series_ProdutosED edVolta = new WMS_Series_ProdutosED();

    try{

      sql = " select * from Series_Produtos";

      sql += " where Oid_Movimento_Produto = " + oid_movimento_produto;

      ResultSet res = null;

      res = this.executasql.executarConsulta(sql);
      while (res.next()){
        cont++;
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

    return cont;
  }

  public ArrayList lista( WMS_Series_ProdutosED ed )throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();
    WMS_Series_ProdutosED edVolta = new WMS_Series_ProdutosED();
    String oid_pessoa = "";
    String oid_produto = "";

    try{

      sql = " select * from Series_Produtos";
        sql += " where oid_movimento_produto ="+ ed.getOID_Movimento_Produto();
        sql += " ORDER BY oid_serie_produto";

      ResultSet res = null, res2 = null;

      res = this.executasql.executarConsulta(sql);
      while (res.next()){
        edVolta = new WMS_Series_ProdutosED();
        edVolta.setOID_Movimento_Produto( res.getInt("Oid_Movimento_Produto") );
        edVolta.setOID_Serie_Produto( res.getInt( "OID_Serie_Produto" ) );
        edVolta.setNR_Fabrica( res.getString( "NR_Fabrica" ) );

        sql = "select * from movimentos_produtos where oid_movimento_produto ="+ edVolta.getOID_Movimento_Produto();
        res2 = this.executasql.executarConsulta(sql);
        while (res2.next()){
          edVolta.setOID_Produto_Cliente( res2.getString( "OID_Produto_Cliente" ) );
        }

        sql = "select * from produtos_clientes where oid_produto_cliente ='"+ edVolta.getOID_Produto_Cliente() +"'";
        res2 = this.executasql.executarConsulta(sql);
        while (res2.next()){
          oid_pessoa = res2.getString( "oid_pessoa" );
          oid_produto = res2.getString( "oid_produto" );
        }

        sql = "select * from produtos where oid_produto ="+ oid_produto;
        res2 = this.executasql.executarConsulta(sql);
        while (res2.next()){
          edVolta.setNM_Produto( res2.getString( "NM_Produto" ) );
        }

        sql = "select * from pessoas where oid_pessoa ='"+ oid_pessoa+"'";
        res2 = this.executasql.executarConsulta(sql);
        while (res2.next()){
          edVolta.setNM_Razao_Social( res2.getString( "NM_Razao_Social" ) );
        }

        list.add(edVolta);
      }

    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao recuperar registro");
      excecoes.setMetodo("lista()");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return list;
  }

}