package com.master.bd;

import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;

import com.master.ed.Promocao_ProdutoED;
import com.master.root.FormataDataBean;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

public class Promocao_ProdutoBD extends BancoUtil{

    private ExecutaSQL executasql;
    DecimalFormat dec = new DecimalFormat("000000000000");
    FormataDataBean dataFormatada = new FormataDataBean();

  public Promocao_ProdutoBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public Promocao_ProdutoED inclui(Promocao_ProdutoED ed) throws Excecoes{

    String sql = null;

    try{

        ed.setOID_Promocao_Produto(getAutoIncremento("oid_Promocao_Produto", "Promocoes_Produtos")); 

        sql = "INSERT INTO Promocoes_Produtos (OID_Promocao_Produto, OID_Produto_Cliente, OID_Pessoa,  DT_Stamp, DT_Vigencia, DT_Validade, PE_Desconto, NR_QT_Minima, NR_QT_Maxima)";
        sql+= " VALUES ( ";
        sql+= ed.getOID_Promocao_Produto() + ",'"+ ed.getOID_Produto_Cliente() + "','"+ed.getOid_Pessoa() + "','"+ Data.getDataDMY()+ "','" + ed.getDT_Vigencia() + "','" + ed.getDT_Validade() + "'," + ed.getPE_Desconto() + "," + ed.getNR_QT_Minima() + "," + ed.getNR_QT_Maxima()  + ")";

        executasql.executarUpdate(sql);
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

  public void altera(Promocao_ProdutoED ed) throws Excecoes{

    String sql = null;

    try{

      sql = " UPDATE Promocoes_Produtos SET ";
      sql += "  PE_Desconto= " + ed.getPE_Desconto();
      sql += " ,NR_QT_Minima= " + ed.getNR_QT_Minima();
      sql += " ,NR_QT_Maxima= " + ed.getNR_QT_Maxima();
      sql += " ,DT_Vigencia= '" + ed.getDT_Vigencia()+"'";
      sql += " ,DT_Validade= '" + ed.getDT_Validade()+"'";
      sql += " WHERE OID_Promocao_Produto = " + ed.getOID_Promocao_Produto();

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

  public void deleta(Promocao_ProdutoED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "DELETE FROM Promocoes_produtos " +
      		"WHERE Oid_Promocao_Produto = "+ ed.getOID_Promocao_Produto();

      executasql.executarUpdate(sql);
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao deletar Promoção!");
      excecoes.setMetodo("deleta()");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public Promocao_ProdutoED getByRecord(Promocao_ProdutoED ed)throws Excecoes{

    String sql = null;

    Promocao_ProdutoED edVolta = new Promocao_ProdutoED();

    try{

      sql = " SELECT * FROM Promocoes_Produtos, Produtos, Pessoas, Produtos_Clientes, Estruturas_Produtos, Unidades_Produtos " +
            " WHERE  Promocoes_Produtos.oid_pessoa = Pessoas.oid_Pessoa " +
            " AND    Produtos.oid_Produto = Produtos_Clientes.oid_Produto " +
            " AND    Produtos_Clientes.oid_Produto_Cliente = Promocoes_Produtos.oid_Produto_Cliente " +
            " AND    Produtos.oid_Estrutura_Produto = Estruturas_Produtos.oid_Estrutura_Produto " +
            " AND    Produtos.oid_Unidade_Produto = Unidades_Produtos.oid_Unidade_Produto " ;

      if (ed.getOID_Promocao_Produto() >0) {
          sql += " AND oid_Promocao_Produto = " + ed.getOID_Promocao_Produto();
      }
      if (doValida(ed.getOID_Produto_Cliente())) {
          sql += " AND Promocoes_Produtos.oid_Produto_Cliente = '" +ed.getOID_Produto_Cliente()+"'";
      }
      if (doValida(ed.getOID_Produto_Cliente())) {
          sql += " AND Promocoes_Produtos.oid_Pessoa = '" +ed.getOid_Pessoa()+"'";
      }

      ResultSet res = this.executasql.executarConsulta(sql);
      while (res.next()){

        edVolta = new Promocao_ProdutoED();

        edVolta.setOID_Promocao_Produto(res.getLong("oid_Promocao_Produto"));
        edVolta.setOID_Produto(res.getString("oid_Produto"));
        edVolta.setOID_Produto_Cliente(res.getString("OID_Produto_Cliente"));
        edVolta.setCD_Produto(res.getString("cd_Produto"));
        edVolta.setNM_Produto(res.getString("nm_Produto"));

        edVolta.setPE_Desconto(res.getDouble("PE_Desconto"));
        edVolta.setNR_QT_Minima(res.getDouble("NR_QT_Minima"));
        edVolta.setNR_QT_Maxima(res.getDouble("NR_QT_Maxima"));


        dataFormatada.setDT_FormataData(res.getString("dt_vigencia"));
        edVolta.setDT_Vigencia(dataFormatada.getDT_FormataData());

        dataFormatada.setDT_FormataData(res.getString("dt_validade"));
        edVolta.setDT_Validade(dataFormatada.getDT_FormataData());

        edVolta.setNR_Peso_Liquido(res.getDouble("NR_Peso_Liquido"));
        edVolta.setNR_Peso_Medio(res.getDouble("NR_Peso_Medio"));
        edVolta.setNR_QT_Caixa(res.getInt("NR_QT_Caixa"));

        edVolta.setCD_Fornecedor(res.getString("CD_Fornecedor"));
        edVolta.setNM_Descricao_Caixa(res.getString("NM_Descricao_Caixa"));

        edVolta.setCD_Unidade_Produto(res.getString("cd_Unidade_Produto"));
        edVolta.setNM_Unidade_Produto(res.getString("nm_Unidade_Produto"));

        edVolta.setNM_Razao_Social(res.getString("NM_Razao_Social"));
        edVolta.setNR_CNPJ_CPF(res.getString("NR_CNPJ_CPF"));
        edVolta.setOid_Pessoa(res.getString("Oid_Pessoa"));

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
  
  //*** Busca Promoção referente a Ultima Data de Vigencia, validando de Passada esta dentro do RANGE da promoção
  public Promocao_ProdutoED getByUltDataVigencia(Promocao_ProdutoED ed)throws Excecoes{

      String sql = null;

      Promocao_ProdutoED edVolta = new Promocao_ProdutoED();

      try{
          if (!doExiste("Promocoes_Produtos",
                  		"oid_Produto_Cliente = '" +ed.getOID_Produto_Cliente()+"'" +
                  		" AND oid_Pessoa = '" +ed.getOid_Pessoa()+"'" +
                  		" AND '" + ed.getDT_Referencia()+"' between dt_vigencia and dt_validade")){
              return edVolta;
          }

	      sql = " SELECT * FROM Promocoes_Produtos, Produtos, Pessoas, Produtos_Clientes, Estruturas_Produtos, Unidades_Produtos " +
	      		" WHERE  Promocoes_Produtos.oid_pessoa = Pessoas.oid_Pessoa " +
	            " AND    Produtos.oid_Produto = Produtos_Clientes.oid_Produto " +
	            " AND    Produtos_Clientes.oid_Produto_Cliente = Promocoes_Produtos.oid_Produto_Cliente " +
	            " AND    Produtos.oid_Estrutura_Produto = Estruturas_Produtos.oid_Estrutura_Produto " +
	            " AND    Produtos.oid_Unidade_Produto = Unidades_Produtos.oid_Unidade_Produto " +
	            " AND    Promocoes_Produtos.oid_Produto_Cliente = '" +ed.getOID_Produto_Cliente()+"'" +
	            " AND    Promocoes_Produtos.oid_Pessoa = '" +ed.getOid_Pessoa()+"'"+
	        	" AND    '" + ed.getDT_Referencia()+"' between Promocoes_Produtos.dt_vigencia and Promocoes_Produtos.dt_validade" +
	        	" AND    Promocoes_Produtos.dt_vigencia = '"+getMaximoData("dt_vigencia",
	        	        												   "Promocoes_Produtos",
	        	          												   "oid_Produto_Cliente = '" +ed.getOID_Produto_Cliente()+"'" +
	        	          												   " AND oid_Pessoa = '" +ed.getOid_Pessoa()+"'" +
	        	          												   " AND '" + ed.getDT_Referencia()+"' between dt_vigencia and dt_validade")+"'";
        
	      ResultSet res = this.executasql.executarConsulta(sql);
	      while (res.next())
	      {
	          edVolta = new Promocao_ProdutoED();
	          edVolta.setOID_Promocao_Produto(res.getLong("oid_Promocao_Produto"));
	          edVolta.setOID_Produto(res.getString("oid_Produto"));
	          edVolta.setOID_Produto_Cliente(res.getString("OID_Produto_Cliente"));
	          edVolta.setCD_Produto(res.getString("cd_Produto"));
	          edVolta.setNM_Produto(res.getString("nm_Produto"));

	          edVolta.setPE_Desconto(res.getDouble("PE_Desconto"));
	          edVolta.setNR_QT_Minima(res.getDouble("NR_QT_Minima"));
	          edVolta.setNR_QT_Maxima(res.getDouble("NR_QT_Maxima"));

	          dataFormatada.setDT_FormataData(res.getString("dt_vigencia"));
	          edVolta.setDT_Vigencia(dataFormatada.getDT_FormataData());

	          dataFormatada.setDT_FormataData(res.getString("dt_validade"));
	          edVolta.setDT_Validade(dataFormatada.getDT_FormataData());

	          edVolta.setNR_Peso_Liquido(res.getDouble("NR_Peso_Liquido"));
	          edVolta.setNR_Peso_Medio(res.getDouble("NR_Peso_Medio"));
	          edVolta.setNR_QT_Caixa(res.getInt("NR_QT_Caixa"));

	          edVolta.setCD_Fornecedor(res.getString("CD_Fornecedor"));
	          edVolta.setNM_Descricao_Caixa(res.getString("NM_Descricao_Caixa"));

	          edVolta.setCD_Unidade_Produto(res.getString("cd_Unidade_Produto"));
	          edVolta.setNM_Unidade_Produto(res.getString("nm_Unidade_Produto"));

	          edVolta.setNM_Razao_Social(res.getString("NM_Razao_Social"));
	          edVolta.setNR_CNPJ_CPF(res.getString("NR_CNPJ_CPF"));
	          edVolta.setOid_Pessoa(res.getString("Oid_Pessoa"));
	      }
      }
      catch(Exception exc){
          throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
          		"getByUltDataVigencia()");
      }
      return edVolta;
    }

  public ArrayList lista(Promocao_ProdutoED ed)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();

    try{

      sql = " select * from Promocoes_Produtos, Produtos, Pessoas, Produtos_Clientes, Estruturas_Produtos, Unidades_Produtos " +
            " WHERE  Promocoes_Produtos.oid_pessoa = Pessoas.oid_Pessoa " +
            " AND    Produtos.oid_Produto = Produtos_Clientes.oid_Produto " +
            " AND    Produtos_Clientes.oid_Produto_Cliente = Promocoes_Produtos.oid_Produto_Cliente " +
            " AND    Produtos.oid_Estrutura_Produto = Estruturas_Produtos.oid_Estrutura_Produto " +
            " AND    Produtos.oid_Unidade_Produto = Unidades_Produtos.oid_Unidade_Produto " ;

      if (ed.getOID_Promocao_Produto() >0) {
          sql += " AND oid_Promocao_Produto = " + ed.getOID_Promocao_Produto();
      }
      if (ed.getOID_Produto_Cliente() != null ) {
          sql += " AND Produtos_Clientes.oid_Produto_Cliente = '" + ed.getOID_Produto_Cliente() + "'";
      }
      sql += " Order by DT_Vigencia";

      ResultSet res = this.executasql.executarConsulta(sql);

      //popula
      while (res.next()){

        Promocao_ProdutoED edVolta = new Promocao_ProdutoED();

        edVolta.setOID_Promocao_Produto(res.getLong("oid_Promocao_Produto"));
        edVolta.setOID_Produto(res.getString("oid_Produto"));
        edVolta.setOID_Produto_Cliente(res.getString("OID_Produto_Cliente"));
        edVolta.setCD_Produto(res.getString("cd_Produto"));
        edVolta.setNM_Produto(res.getString("nm_Produto"));

        edVolta.setPE_Desconto(res.getDouble("PE_Desconto"));
        edVolta.setNR_QT_Minima(res.getDouble("NR_QT_Minima"));
        edVolta.setNR_QT_Maxima(res.getDouble("NR_QT_Maxima"));


        dataFormatada.setDT_FormataData(res.getString("dt_vigencia"));
        edVolta.setDT_Vigencia(dataFormatada.getDT_FormataData());

        dataFormatada.setDT_FormataData(res.getString("dt_validade"));
        edVolta.setDT_Validade(dataFormatada.getDT_FormataData());

        edVolta.setNR_Peso_Liquido(res.getDouble("NR_Peso_Liquido"));
        edVolta.setNR_Peso_Medio(res.getDouble("NR_Peso_Medio"));
        edVolta.setNR_QT_Caixa(res.getInt("NR_QT_Caixa"));

        edVolta.setCD_Fornecedor(res.getString("CD_Fornecedor"));
        edVolta.setNM_Descricao_Caixa(res.getString("NM_Descricao_Caixa"));

        edVolta.setCD_Unidade_Produto(res.getString("cd_Unidade_Produto"));
        edVolta.setNM_Unidade_Produto(res.getString("nm_Unidade_Produto"));

        edVolta.setNM_Razao_Social(res.getString("NM_Razao_Social"));
        edVolta.setNR_CNPJ_CPF(res.getString("NR_CNPJ_CPF"));
        edVolta.setOid_Pessoa(res.getString("Oid_Pessoa"));

        list.add(edVolta);
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao listar Promoção_Produtos - SQL="+sql);
      excecoes.setMetodo("lista(Promocao_ProdutosED)");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return list;
  }
}