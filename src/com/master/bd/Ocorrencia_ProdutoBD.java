package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.Ocorrencia_ProdutoED;
import com.master.root.FormataDataBean;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

public class Ocorrencia_ProdutoBD extends BancoUtil{

  private ExecutaSQL executasql;

  public Ocorrencia_ProdutoBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public Ocorrencia_ProdutoED inclui(Ocorrencia_ProdutoED ed) throws Excecoes{

    String sql = null;
    String chave = null;

    try{

      //*** AutoIncremento  
      ed.setOid_Ocorrencia_Produto(getAutoIncremento("oid_Ocorrencia_Produto", "Ocorrencias_Produtos"));

      sql = " insert into Ocorrencias_Produtos (" +
      		"		oid_Ocorrencia_Produto, " +
      		" 		oid_Produto, " +
      		"		oid_Tipo_Ocorrencia, " +
      		"		DT_Ocorrencia_Produto, " +
      		"		HR_Ocorrencia_Produto, " +
      		"		TX_Descricao ) " +
      		" values ";
      
      sql += "(" + ed.getOid_Ocorrencia_Produto() + ",'" + ed.getOid_Produto() + "'," + ed.getOid_Tipo_Ocorrencia() + ",'"  + ed.getDT_Ocorrencia_Produto() + "','" + ed.getHR_Ocorrencia_Produto() + "','" + ed.getTX_Descricao() + "')";
      //*** SQL
      //// System.out.println(sql);
      executasql.executarUpdate(sql);

    }
      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao incluir Ocorrência Produto");
        excecoes.setMetodo("inclui");
        excecoes.setExc(exc);
        throw excecoes;
      }
      return ed;
  }

  public void altera(Ocorrencia_ProdutoED ed) throws Excecoes{

    String sql = null;

    try{

      sql =  " update Ocorrencias_Produtos set oid_Tipo_Ocorrencia= " + ed.getOid_Tipo_Ocorrencia() + ", TX_Descricao = '" + ed.getTX_Descricao() + "'";
      sql += " where oid_Ocorrencia_Produto = " + ed.getOid_Ocorrencia_Produto();

      executasql.executarUpdate(sql);
    
    }catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao alterar Ocorrência Produto");
        excecoes.setMetodo("alterar");
        excecoes.setExc(exc);
        throw excecoes;
    }
  }

  public void deleta(Ocorrencia_ProdutoED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from Ocorrencias_Produtos WHERE oid_Ocorrencia_Produto = ";
      sql += "(" + ed.getOid_Ocorrencia_Produto() + ")";

      executasql.executarUpdate(sql);
      
    }catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao excluir Ocorrência Produto");
        excecoes.setMetodo("excluir");
        excecoes.setExc(exc);
        throw excecoes;
    }
  }

  public ArrayList lista(Ocorrencia_ProdutoED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();

    try{
      sql =  " SELECT Ocorrencias_Produtos.*, Produtos.oid_Produto, Tipos_Ocorrencias.NM_Tipo_Ocorrencia, Tipos_Ocorrencias.CD_Tipo_Ocorrencia, Produtos.CD_Produto, Produtos.NM_Produto from Ocorrencias_Produtos, Produtos, Tipos_Ocorrencias ";
      sql += " WHERE Ocorrencias_Produtos.oid_Produto = Produtos.oid_Produto ";
      sql += " AND Ocorrencias_Produtos.OID_Tipo_Ocorrencia = Tipos_Ocorrencias.OID_Tipo_Ocorrencia ";
      
      if (ed.getOid_Produto() > 0)
          sql += " AND Ocorrencias_Produtos.oid_Produto = "+ ed.getOid_Produto();
      else if (ed.getOid_Ocorrencia_Produto() > 0)
          sql += " AND Ocorrencias_Produtos.oid_Ocorrencia_Produto = "+ ed.getOid_Ocorrencia_Produto();
      
      //*** SQL
      //// System.out.println("SQL LISTA >> "+sql);

      ResultSet res = this.executasql.executarConsulta(sql);

      FormataDataBean DataFormatada = new FormataDataBean();

      //popula
      while (res.next()){
        Ocorrencia_ProdutoED edVolta = new Ocorrencia_ProdutoED();

        edVolta.setOid_Ocorrencia_Produto(res.getInt("OID_Ocorrencia_Produto"));
        
        edVolta.setOid_Tipo_Ocorrencia(res.getInt("oid_Tipo_Ocorrencia"));
        edVolta.setCD_Tipo_Ocorrencia(res.getString("CD_Tipo_Ocorrencia"));
        edVolta.setNM_Tipo_Ocorrencia(res.getString("NM_Tipo_Ocorrencia"));
        
        edVolta.setDT_Ocorrencia_Produto(res.getString("DT_Ocorrencia_Produto"));
        DataFormatada.setDT_FormataData(edVolta.getDT_Ocorrencia_Produto());
        edVolta.setDT_Ocorrencia_Produto(DataFormatada.getDT_FormataData());
        
        edVolta.setHR_Ocorrencia_Produto(res.getString("HR_Ocorrencia_Produto"));
        
        edVolta.setOid_Produto(res.getInt("oid_Produto"));
        edVolta.setCD_Produto(res.getString("CD_Produto"));
        edVolta.setNM_Produto(res.getString("NM_Produto"));

        list.add(edVolta);
      }
    }
      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao listar Ocorrência Produto");
        excecoes.setMetodo("listar");
        excecoes.setExc(exc);
        throw excecoes;
      }

    return list;
  }

  public Ocorrencia_ProdutoED getByRecord(Ocorrencia_ProdutoED ed)throws Excecoes{

    String sql = null;
    Ocorrencia_ProdutoED edVolta = new Ocorrencia_ProdutoED();

    try{

      sql =  " SELECT Ocorrencias_Produtos.*, Produtos.oid_Produto, Tipos_Ocorrencias.NM_Tipo_Ocorrencia, Tipos_Ocorrencias.CD_Tipo_Ocorrencia, Produtos.CD_Produto, Produtos.NM_Produto from Ocorrencias_Produtos, Produtos, Tipos_Ocorrencias ";
      sql += " WHERE Ocorrencias_Produtos.oid_Produto = Produtos.oid_Produto ";
      sql += " AND Ocorrencias_Produtos.OID_Tipo_Ocorrencia = Tipos_Ocorrencias.OID_Tipo_Ocorrencia ";
      
      if (ed.getOid_Ocorrencia_Produto() > 0){
        sql += " and oid_Ocorrencia_Produto = " + ed.getOid_Ocorrencia_Produto();
      }

      FormataDataBean DataFormatada = new FormataDataBean();

      ResultSet res = this.executasql.executarConsulta(sql);
      while (res.next()){

          edVolta.setOid_Ocorrencia_Produto(res.getInt("OID_Ocorrencia_Produto"));
          
          edVolta.setOid_Tipo_Ocorrencia(res.getInt("oid_Tipo_Ocorrencia"));
          edVolta.setCD_Tipo_Ocorrencia(res.getString("CD_Tipo_Ocorrencia"));
          edVolta.setNM_Tipo_Ocorrencia(res.getString("NM_Tipo_Ocorrencia"));
          
          edVolta.setDT_Ocorrencia_Produto(res.getString("DT_Ocorrencia_Produto"));
          DataFormatada.setDT_FormataData(edVolta.getDT_Ocorrencia_Produto());
          edVolta.setDT_Ocorrencia_Produto(DataFormatada.getDT_FormataData());
          
          edVolta.setHR_Ocorrencia_Produto(res.getString("HR_Ocorrencia_Produto"));
          edVolta.setTX_Descricao(res.getString("TX_Descricao"));
          
          edVolta.setOid_Produto(res.getInt("oid_Produto"));
          edVolta.setCD_Produto(res.getString("CD_Produto"));
          edVolta.setNM_Produto(res.getString("NM_Produto"));

      }
    }
      catch(Exception exc){
        Excecoes excecoes = new Excecoes();
        excecoes.setClasse(this.getClass().getName());
        excecoes.setMensagem("Erro ao selecionar Ocorrência Produto");
        excecoes.setMetodo("seleção");
        excecoes.setExc(exc);
        throw excecoes;
      }
    return edVolta;
  }
}