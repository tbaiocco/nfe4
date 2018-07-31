package com.master.bd;

/**
 * @author Anré Valadas
*/

import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;

import com.master.ed.Validade_ProdutoED;
import com.master.root.FormataDataBean;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;


public class Validade_ProdutoBD extends BancoUtil{

  private ExecutaSQL executasql;
      DecimalFormat dec = new DecimalFormat("000000000000");
      FormataDataBean dataFormatada = new FormataDataBean();
      Parametro_FixoED parametro_FixoED = new Parametro_FixoED();

  public Validade_ProdutoBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  	public Validade_ProdutoED inclui(Validade_ProdutoED ed) throws Excecoes{

  	    String sql = null;

  	    try{

  	        ed.setOID_Validade_Produto(getAutoIncremento("OID_Validade_Produto","Validades_Produtos"));

  	        sql = "INSERT INTO Validades_Produtos (" +
  	        	  "		OID_Validade_Produto" +
  	        	  "		,OID_Produto_Cliente" +
  	        	  "		,OID_Produto" +
  	        	  "		,OID_Nota_Fiscal" +
  	        	  "		,DT_Stamp" +
  	        	  "		, NR_Lote" +
  	        	  "		, DT_Validade" +
  	        	  "		, NR_Quantidade)";
  	        sql+= " values ( ";
  	        sql+= ed.getOID_Validade_Produto() +
  	        	  ",'"+ ed.getOID_Produto_Cliente() + "'" +
  	        	  ","+ ed.getOID_Produto() +
  	        	  ",'"+ ed.getOID_Nota_Fiscal() + "'" +
  	        	  ",'"+ Data.getDataDMY() + "'" +
  	        	  ",'"+ ed.getNR_Lote()  + "'" +
  	        	  ",'" + ed.getDT_Validade() + "'" +
  	        	  "," + ed.getNR_Quantidade()  + ")";

  	        executasql.executarUpdate(sql);

  	        if (doValida(ed.getOID_Nota_Fiscal())){

  	            long NR_Proximo_Lote = (getMaximo("NR_Proximo_Lote","Notas_Fiscais","OID_Nota_Fiscal = '" + ed.getOID_Nota_Fiscal() + "'") + 1);
            
  	            sql = " Update Notas_Fiscais Set  NR_Proximo_Lote= " + NR_Proximo_Lote +
  	            	  " WHERE OID_Nota_Fiscal = '" + ed.getOID_Nota_Fiscal() + "'";

  	            executasql.executarUpdate(sql);
  	        }
  	    }
  	    catch(Exception exc){
  	        Excecoes excecoes = new Excecoes();
  	        excecoes.setClasse(this.getClass().getName());
  	        excecoes.setMensagem("Erro ao incluir a Promoção!");
  	        excecoes.setMetodo("inclui()");
  	        excecoes.setExc(exc);
  	        throw excecoes;
  	    }
  	    return ed;
  	}

  	public void altera(Validade_ProdutoED ed) throws Excecoes{

  	    String sql = null;

  	    try{

  	        sql = " UPDATE Validades_Produtos SET ";
  	        sql += "  DT_Validade= '" + ed.getDT_Validade() + "'," ;
  	        sql += "  NR_Lote= '" + ed.getNR_Lote() + "'" ;
  	        sql += " WHERE OID_Validade_Produto = " + ed.getOID_Validade_Produto();

  	        executasql.executarUpdate(sql);
  	    }
  	    catch(Exception exc){
  	        Excecoes excecoes = new Excecoes();
  	        excecoes.setClasse(this.getClass().getName());
  	        excecoes.setMensagem("Erro ao alterar dados do Produto");
  	        excecoes.setMetodo("altera()");
  	        excecoes.setExc(exc);
  	        throw excecoes;
  	    }
  	}

  	public void deleta(Validade_ProdutoED ed) throws Excecoes{

  	    String sql = null;

  	    try{

  	        sql = "delete from Validades_Produtos WHERE Oid_Validade_Produto = ";
  	        sql += "(" + ed.getOID_Validade_Produto() + ")";

  	        executasql.executarUpdate(sql);
  	    }
  	    catch(Exception exc){
  	        Excecoes excecoes = new Excecoes();
  	        excecoes.setClasse(this.getClass().getName());
  	        excecoes.setMensagem("Erro ao deletar Produto");
  	        excecoes.setMetodo("deleta()");
  	        excecoes.setExc(exc);
  	        throw excecoes;
  	    }
  	}

  	public Validade_ProdutoED getByRecord(Validade_ProdutoED ed)throws Excecoes{

  	    String sql = null;

  	    Validade_ProdutoED edVolta = new Validade_ProdutoED();

  	    try{

  	        sql = " select * from Validades_Produtos" +
  	        	  " WHERE oid_Validade_Produto = " + ed.getOID_Validade_Produto();
  	       
  	        ResultSet res = this.executasql.executarConsulta(sql);
  	        while (res.next()){

  	            edVolta = new Validade_ProdutoED();

  	            edVolta.setOID_Validade_Produto(res.getLong("oid_Validade_Produto"));
  	            edVolta.setOID_Produto_Cliente(res.getString("OID_Produto_Cliente"));
  	            edVolta.setOID_Nota_Fiscal(res.getString("OID_Nota_Fiscal"));
  	            edVolta.setOID_Produto(res.getString("OID_Produto"));
  	            edVolta.setNR_Quantidade((long)res.getDouble("nr_quantidade"));
  	            edVolta.setNR_Lote(res.getString("NR_Lote"));

  	            dataFormatada.setDT_FormataData(res.getString("dt_validade"));
  	            edVolta.setDT_Validade(dataFormatada.getDT_FormataData());
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

  	public ArrayList lista(Validade_ProdutoED ed)throws Excecoes{

  	    String sql = null;
  	    ArrayList list = new ArrayList();

  	    try{

  	        sql = " SELECT oid_Validade_Produto" +
  	        	  "		  ,OID_Produto_Cliente" +
  	        	  "		  ,OID_Produto" +
  	        	  "		  ,OID_Nota_Fiscal" +
  	        	  "       ,NR_Quantidade" +
  	        	  "       ,NR_Lote" +
  	        	  "       ,DT_Validade " +
  	        	  " FROM Validades_Produtos WHERE 1=1";

  	        if (doValida(ed.getOID_Produto_Cliente())) {
  	            sql += " AND OID_Produto_Cliente = '" + ed.getOID_Produto_Cliente() + "'";
  	        }
  	        if (doValida(ed.getOID_Nota_Fiscal())) {
  	            sql += " AND OID_Nota_Fiscal = '" + ed.getOID_Nota_Fiscal() + "'";
  	        }
  	        if (doValida(ed.getOID_Produto())) {
  	            sql += " AND OID_Produto = " + ed.getOID_Produto();
  	        }
  	        sql += " Order by DT_Validade";

  	        ResultSet res = this.executasql.executarConsulta(sql);

  	        while (res.next()){

  	            Validade_ProdutoED edVolta = new Validade_ProdutoED();

  	            edVolta.setOID_Validade_Produto(res.getLong("oid_Validade_Produto"));
  	            edVolta.setOID_Produto_Cliente(res.getString("OID_Produto_Cliente"));
  	            edVolta.setOID_Produto(res.getString("OID_Produto"));
  	            edVolta.setOID_Nota_Fiscal(res.getString("OID_Nota_Fiscal"));
  	            edVolta.setNR_Quantidade((long)res.getDouble("nr_quantidade"));
  	            edVolta.setNR_Lote(res.getString("NR_Lote"));

  	            dataFormatada.setDT_FormataData(res.getString("DT_Validade"));
  	            edVolta.setDT_Validade(dataFormatada.getDT_FormataData());

  	            list.add(edVolta);
  	        }
  	    }
  	    catch(Exception exc){
  	        Excecoes excecoes = new Excecoes();
  	        excecoes.setClasse(this.getClass().getName());
  	        excecoes.setMensagem("Erro ao listar Estruturas_Produtos - SQL="+sql);
  	        excecoes.setMetodo("lista(Estruturas_ProdutosED)");
  	        excecoes.setExc(exc);
  	        throw excecoes;
  	    }
  	    return list;
  	}
}