package com.master.bd;

/**
 * @author: André Valadas
*/

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import com.master.ed.Classificacao_FiscalED;
import com.master.ed.Estrutura_ProdutoED;
import com.master.ed.MixED;
import com.master.ed.PessoaED;
import com.master.ed.Preco_ProdutoED;
import com.master.ed.ProdutoED;
import com.master.ed.ProdutoRelED;
import com.master.ed.RelatorioED;
import com.master.ed.Setor_ProdutoED;
import com.master.ed.Situacao_TributariaED;
import com.master.ed.Tabela_VendaED;
import com.master.iu.ProdutoBean;
import com.master.iu.wms005Bean;
import com.master.rl.JasperRL;
import com.master.rl.ProdutoRL;
import com.master.root.FormataDataBean;
import com.master.root.PessoaBean;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;
import com.master.util.Utilitaria;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;
import com.master.ed.WMS_LocalizacaoED;
import com.master.rn.WMS_LocalizacaoRN;

public class ProdutoBD  {

    private ExecutaSQL executasql;
	Utilitaria util = new Utilitaria(executasql);

    public ProdutoBD(ExecutaSQL sql) {
        this.executasql = sql;
    }

    public ProdutoED inclui(ProdutoED ed) throws Excecoes {

        String sql = null;
        try {

            ed.setOid(util.getAutoIncremento("oid_Produto", "Produtos"));
            ed.setOID_Produto(String.valueOf(ed.getOid()));

            ///*** Valida Estrutura do Fornecedor
            if (ed.getOid_Estrutura_Fornecedor() <= 0)
                ed.setOid_Estrutura_Fornecedor(ed.getOid_Estrutura_Produto());

            if (!util.doValida(ed.getCD_Fornecedor()))
            	ed.setCD_Fornecedor(ed.getCD_Produto());

            sql = "INSERT INTO Produtos (" +
            	  "		OID_Produto" +
            	  "		,CD_Produto" +
            	  "		,NM_Produto" +
            	  "		,Dt_Stamp" +
            	  "		,OID_Pessoa" +
            	  "		,OID_Estrutura_Produto" +
            	  "		,OID_Unidade_Produto" +
            	  "		,CD_Fornecedor" +
            	  "		,NM_Descricao_Caixa" +
            	  "		,NR_Peso_Liquido" +
            	  "		,NR_Peso_Medio" +
            	  "		,NR_QT_Caixa" +
            	  "		,OID_Tipo_Produto" +
            	  "		,oid_Estrutura_Fornecedor" +
            	  "		,oid_Classificacao_Fiscal" +
            	  "		,oid_Situacao_Tributaria" +
            	  "		,oid_Setor_Produto)";
            sql +=" VALUES ( ";
            sql += ed.getOid() +
            	",'" + ed.getCD_Produto() + "'" +
            	",'" + ed.getNM_Produto() + "'" +
            	",'" + Data.getDataDMY() + "'" +
            	",'" + ed.getOid_Pessoa() + "'" +
            	"," + ed.getOid_Estrutura_Produto() +
            	"," + ed.getOid_Unidade_Produto() +
            	",'" + ed.getCD_Fornecedor() + "'" +
            	",'" + ed.getNM_Descricao_Caixa() + "'" +
            	"," + ed.getNR_Peso_Liquido() +
            	"," + ed.getNR_Peso_Medio() +
            	"," + ed.getNR_QT_Caixa() +
            	"," + ed.getOID_Tipo_Produto() +
            	"," + ed.getOid_Estrutura_Fornecedor() +
            	"," + ed.getOid_Classificacao_Fiscal() +
            	"," + ed.getOid_Situacao_Tributaria()+
            	"," + ed.getOid_Setor_Produto()+ ")";

            executasql.executarUpdate(sql);
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "inclui()");
        }
        return ed;
    }

  	public void altera(ProdutoED ed) throws Excecoes {

        String sql = null;
        try {

            sql = " UPDATE Produtos SET ";
            sql += " NM_Produto= '" + ed.getNM_Produto() + "', ";
            sql += " CD_Produto= '" + ed.getCD_Produto() + "',";
            sql += " oid_Pessoa= '" + ed.getOid_Pessoa() + "', ";
            sql += " CD_Fornecedor= '" + ed.getCD_Fornecedor() + "', ";
            sql += " NM_Descricao_Caixa= '" + ed.getNM_Descricao_Caixa() + "', ";
            sql += " OID_Estrutura_Produto= " + ed.getOid_Estrutura_Produto() + ", ";
            sql += " OID_Unidade_Produto= " + ed.getOid_Unidade_Produto() + ", ";
            sql += " OID_Tipo_Produto= " + ed.getOID_Tipo_Produto() + ", ";
            //*** Valida Estrutura do Fornecedor
            if (ed.getOid_Estrutura_Fornecedor() > 0) {
                sql += " oid_Estrutura_Fornecedor= " + ed.getOid_Estrutura_Fornecedor() + ", ";
            } else sql += " oid_Estrutura_Fornecedor= " + ed.getOid_Estrutura_Produto() + ", ";
            sql += " oid_Classificacao_Fiscal= " + ed.getOid_Classificacao_Fiscal() + ", ";
            sql += " oid_Situacao_Tributaria= " + ed.getOid_Situacao_Tributaria() + ", ";
            sql += " oid_Setor_Produto= " + ed.getOid_Setor_Produto() + ", ";
            sql += " NR_Peso_Liquido= " + ed.getNR_Peso_Liquido() + ", ";
            sql += " NR_Peso_Medio= " + ed.getNR_Peso_Medio() + ", ";
            sql += " NR_QT_Caixa= " + ed.getNR_QT_Caixa();
            sql += " WHERE OID_Produto = " + ed.getOID_Produto();

            executasql.executarUpdate(sql);
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "altera()");
        }
    }

  	public void deleta(ProdutoED ed) throws Excecoes {

        String sql = null;
        try {

            sql = "DELETE FROM produtos WHERE Oid_Produto = ";
            sql += "(" + ed.getOID_Produto() + ")";
            executasql.executarUpdate(sql);

        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "delete()");
        }
    }

	public ProdutoED getByRecord(ProdutoED ed)throws Excecoes {

		try{
	  	    Iterator it = this.lista(ed).iterator();
	  	    if (it.hasNext()) {
	  	        return (ProdutoED) it.next();
	  	    } else return new ProdutoED();
		} catch(Exception exc){
	  	   	throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "getByRecord()");
	  	}
	}

	public ProdutoED getByRecordProdCli(ProdutoED ed)throws Excecoes {

		try{
	  	    Iterator it = this.listaProdutoCliente(ed).iterator();
	  	    if (it.hasNext()) {
	  	        return (ProdutoED) it.next();
	  	    } else return new ProdutoED();
		} catch(Exception exc){
	  	   	throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "getByRecordProdCli()");
	  	}
	}

    public ArrayList lista(ProdutoED ed) throws Excecoes {

	    String sql = null;
	    ArrayList list = new ArrayList();
	    try {

	        sql = " SELECT DISTINCT Produtos.*" +
	        	  "		  ,Unidades_Produtos.*" +
	        	  "		  ,Tipos_Produtos.* " +
				  " FROM Produtos" +
				  "		,Tipos_Produtos" +
				  "		,Unidades_Produtos" +
				  "		,Classificacoes_Fiscais" +
				  "		,Situacoes_Tributarias" +
				  "		,Setores_Produtos" ;
	        if (util.doValida(ed.getOid_Pessoa_Distribuidor()) && (!util.doValida(ed.getOID_Produto()) && ed.getOid() == 0))
	        	sql+= " ,Produtos_Clientes ";
	     	sql+= " WHERE Produtos.oid_Tipo_Produto = Tipos_Produtos.oid_Tipo_Produto " +
	     		  "   AND Produtos.oid_unidade_produto = Unidades_Produtos.oid_unidade_produto " +
	     		  "   AND Produtos.oid_Classificacao_Fiscal = Classificacoes_Fiscais.oid_Classificacao_Fiscal " +
	     		  "   AND Produtos.oid_Situacao_Tributaria = Situacoes_Tributarias.oid_Situacao_Tributaria " +
	     		  "   AND Produtos.oid_Setor_Produto = Setores_Produtos.oid_Setor_Produto ";

            if (util.doValida(ed.getOID_Produto()))
                sql += " AND Produtos.OID_Produto = '"+ed.getOID_Produto()+"'";
            else if (ed.getOid() > 0)
                sql += " AND Produtos.OID_Produto = '"+ed.getOid()+"'";
            else {
            	if (util.doValida(ed.getOid_Pessoa_Distribuidor()))
    		        sql += " AND  Produtos_Clientes.oid_pessoa = '" + ed.getOid_Pessoa_Distribuidor() + "'" +
		          	       " AND  Produtos_Clientes.oid_Produto = Produtos.oid_Produto";
    		    else  if (util.doValida(ed.getOid_Pessoa()))
                    sql += " AND (Produtos.oid_pessoa = Fornecedores.oid_Pessoa OR (SELECT count(*) " +
                    "                                                        FROM Fornecedores_Produtos" +
                    "                                                        WHERE Fornecedores_Produtos.oid_Pessoa = Fornecedores.oid_pessoa" +
                    "                                                          AND Fornecedores_Produtos.oid_Produto = Produtos.oid_Produto) > 0) " +
                    " AND Fornecedores.oid_Pessoa = "+util.getSQLString(ed.getOid_Pessoa());
    		    else
                if (ed.getOID_Tipo_Produto() > 0)
                    sql += " AND Produtos.oid_Tipo_Produto = "+ed.getOID_Tipo_Produto();
    		    if (ed.getOid_Setor_Produto() > 0)
    		        sql += " AND Produtos.oid_Setor_Produto = "+ed.getOid_Setor_Produto();
    		    if (util.doValida(ed.getCD_Produto()))
    		        sql += " AND Produtos.CD_Produto = '"+ed.getCD_Produto()+"'";
    		    if (util.doValida(ed.getCD_Fornecedor()))
    		        sql += " AND Produtos.CD_Fornecedor = '"+ed.getCD_Fornecedor()+"'";
    		    if (util.doValida(ed.getNM_Produto()))
    		        sql += " AND Produtos.NM_Produto LIKE '%"+ed.getNM_Produto()+"%'";
    		    if (util.doValida(ed.getCD_Estrutura_Produto()))
    		        sql += " AND Estruturas_Produtos.CD_Estrutura_Produto = '"+ed.getCD_Estrutura_Produto()+"'";
    		    if (util.doValida(ed.getOid_Pessoa_Distribuidor())) {
    		        sql += " AND  Produtos_Clientes.oid_pessoa = '" + ed.getOid_Pessoa_Distribuidor() + "'" +
    		          	   " AND  Produtos_Clientes.oid_Produto = Produtos.oid_Produto";
    		    }
    		    if (ed.getOid_Mix() > 0)
                {
    		        sql += " AND Produtos.oid_produto = Mix_Produtos.oid_produto" +
    		        	   " AND Mix_Produtos.oid_mix = "+ed.getOid_Mix();
    		    }
            }
		    sql += " ORDER BY Produtos.NM_Produto";

//		    System.out.println(sql);

		    ResultSet res = this.executasql.executarConsulta(sql);
		    while (res.next())
            {
		    	ProdutoED edVolta = (ProdutoED) ed.clone();

		        edVolta.setOid(res.getInt("oid_Produto"));
		        edVolta.setOID_Produto(res.getString("oid_Produto"));
		        edVolta.setCD_Produto(res.getString("cd_Produto"));
		        edVolta.setNM_Produto(res.getString("nm_Produto"));
		        edVolta.setCD_Fornecedor(res.getString("CD_Fornecedor"));
		        edVolta.setOid_Pessoa(res.getString("oid_Pessoa"));
		        edVolta.setOID_Tipo_Produto(res.getInt("oid_Tipo_Produto"));
		        edVolta.setCD_Tipo_Produto(res.getString("cd_Tipo_Produto"));
		        edVolta.setNM_Tipo_Produto(res.getString("nm_Tipo_Produto"));

		        edVolta.setOid_Unidade_Produto(res.getInt("oid_Unidade_Produto"));
		        edVolta.setCD_Unidade_Produto(res.getString("cd_Unidade_Produto"));
		        edVolta.setNM_Unidade_Produto(res.getString("nm_Unidade_Produto"));
		        edVolta.setDM_Pesagem(res.getString("DM_Pesagem"));
		        edVolta.setPesagem("S".equals(edVolta.getDM_Pesagem()));

		        edVolta.setNM_Descricao_Caixa(res.getString("NM_Descricao_Caixa"));
		        edVolta.setNR_QT_Caixa(res.getInt("NR_QT_Caixa"));
		        edVolta.setNR_Peso_Liquido(res.getDouble("NR_Peso_Liquido"));
		        edVolta.setNR_Peso_Medio(res.getDouble("NR_Peso_Medio"));

/*		        edVolta.setOid_Estrutura_Produto(res.getInt("oid_Estrutura_Produto"));
		        edVolta.setCD_Estrutura_Produto(res.getString("cd_Estrutura_Produto"));
		        edVolta.setNM_Estrutura_Produto(res.getString("NM_Estrutura_Produto"));
		        edVolta.setDm_Estrutura_Produto(res.getString("dm_Estrutura_Produto"));
		        edVolta.setOid_Estrutura_Fornecedor(res.getInt("oid_Estrutura_Fornecedor"));
*/
		        edVolta.setOid_Classificacao_Fiscal(res.getInt("oid_Classificacao_Fiscal"));
		        edVolta.setOid_Situacao_Tributaria(res.getInt("oid_Situacao_Tributaria"));
		        edVolta.setOid_Setor_Produto(res.getInt("Oid_Setor_Produto"));
		        if (edVolta.getOid_Setor_Produto() > 0)
		            edVolta.edSetor = new Setor_ProdutoBD(executasql).getByRecord(new Setor_ProdutoED(edVolta.getOid_Setor_Produto()));

/*		        edVolta.setOid_Nivel_Produto1(res.getInt("Oid_Nivel_Produto1"));
		        edVolta.setOid_Nivel_Produto2(res.getInt("Oid_Nivel_Produto2"));
		        edVolta.setOid_Nivel_Produto3(res.getInt("Oid_Nivel_Produto3"));
		        edVolta.setOid_Nivel_Produto4(res.getInt("Oid_Nivel_Produto4"));
		        edVolta.setOid_Nivel_Produto5(res.getInt("Oid_Nivel_Produto5"));
*/                //*** Pessoa_Fornecedor
                if (util.doValida(edVolta.getOid_Pessoa()))
                {
                    PessoaED edPessoa = new PessoaBD(executasql).getByRecord(new PessoaED(edVolta.getOid_Pessoa()));
                    edVolta.setNR_CNPJ_CPF(edPessoa.getNR_CNPJ_CPF());
                    edVolta.setNM_Razao_Social(edPessoa.getNM_Razao_Social());
                }
		        //*** Pessoa_Distribuidor
		        if (util.doValida(ed.getOid_Pessoa_Distribuidor()))
                {
		            PessoaBean edPessoa = PessoaBean.getByNR_CNPJ_CPF(ed.getOid_Pessoa_Distribuidor());
		            edVolta.setOid_Pessoa_Distribuidor(ed.getOid_Pessoa_Distribuidor());
		            edVolta.setNM_Distribuidor(edPessoa.getNM_Razao_Social());
		            edVolta.setCD_Distribuidor(edPessoa.getNR_CNPJ_CPF());
		        }
		        //*** Estrutura_Fornecedor
		        if (edVolta.getOid_Estrutura_Fornecedor() > 0)
                {
		            Estrutura_ProdutoED edEstrutura = new Estrutura_ProdutoBD(executasql).getByRecord(new Estrutura_ProdutoED(edVolta.getOid_Estrutura_Fornecedor()));
		            edVolta.setCD_Estrutura_Fornecedor(edEstrutura.getCd_Estrutura_Produto());
		            edVolta.setNM_Estrutura_Fornecedor(edEstrutura.getNm_Estrutura_Produto());
		        }
		        //*** Classificação Fiscal
		        if (edVolta.getOid_Classificacao_Fiscal() > 0)
                {
		            Classificacao_FiscalED edFiscal_Volta = new Classificacao_FiscalBD(executasql).getByOidClassificacao_Fiscal(edVolta.getOid_Classificacao_Fiscal());
		            edVolta.setCD_Reduzido(edFiscal_Volta.getCD_Reduzido());
		            edVolta.setCD_Fiscal(edFiscal_Volta.getCD_Fiscal());
		        }
		        //*** Situação Tributária
		        if (edVolta.getOid_Situacao_Tributaria() > 0)
                {
		            Situacao_TributariaED edSituacao = new Situacao_TributariaBD(executasql).getByOidSituacao_Tributaria(edVolta.getOid_Situacao_Tributaria());
		            edVolta.setCD_Situacao_Tributaria(edSituacao.getCD_Situacao_Tributaria());
		            if("001".equals(edSituacao.getCD_Situacao_Tributaria()))
		            	edVolta.setCD_Situacao_Tributaria("000");
		            if("002".equals(edSituacao.getCD_Situacao_Tributaria()))
		            	edVolta.setCD_Situacao_Tributaria("020");
		            edVolta.setNM_Situacao_Tributaria(edSituacao.getNM_Situacao_Tributaria());
		        }

	        	list.add(edVolta);
	      	}
	    	return list;

	    } catch(Exception exc){
	        throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "lista()");
		}
    }

    public ArrayList listaProdutoCliente(ProdutoED ed, String orderBy)throws Excecoes {

  	    String sql = null;
  	    ArrayList list = new ArrayList();
  	    try {

	    sql = " SELECT DISTINCT * "+
			  " FROM Produtos, " +
			  "		 Unidades_Produtos, " +
			  "		 Tipos_Produtos, " +
			  "		 Classificacoes_Fiscais, " +
			  "		 Situacoes_Tributarias,"+
			  "      Setores_Produtos,";
			  if (ed.getOid_Mix() > 0) sql += " Mix_Produtos, ";
			  sql += "	Produtos_Clientes " +
			  " WHERE  Produtos.oid_Tipo_Produto = Tipos_Produtos.oid_Tipo_Produto " +
			  "    AND Produtos.oid_Classificacao_Fiscal = Classificacoes_Fiscais.oid_Classificacao_Fiscal " +
			  "    AND Produtos.oid_Situacao_Tributaria = Situacoes_Tributarias.oid_Situacao_Tributaria " +
			  "    AND Produtos.oid_Unidade_Produto = Unidades_Produtos.oid_Unidade_Produto " +
			  "    AND Produtos_Clientes.oid_Produto = Produtos.oid_Produto"+
     		  "    AND Produtos.oid_Setor_Produto = Setores_Produtos.oid_Setor_Produto ";
	      if (util.doValida(ed.getOid_Pessoa()))
	          sql += " AND (Produtos.oid_pessoa = Fornecedores.oid_Pessoa OR (SELECT count(*) " +
                     "                                                        FROM Fornecedores_Produtos" +
                     "                                                        WHERE Fornecedores_Produtos.oid_Pessoa = Fornecedores.oid_pessoa" +
                     "                                                          AND Fornecedores_Produtos.oid_Produto = Produtos.oid_Produto) > 0) " +
                     " AND Fornecedores.oid_Pessoa = "+util.getSQLString(ed.getOid_Pessoa());
	      if (util.doValida(ed.getCD_Produto()))
	          sql += " AND Produtos.CD_Produto = '" + ed.getCD_Produto()+ "'";
	      if (util.doValida(ed.getNM_Produto()))
	          sql += " AND Produtos.NM_Produto LIKE '" + ed.getNM_Produto()+ "%'";
	      if (ed.getOid_Estrutura_Produto() > 0)
	          sql += " AND Produtos.oid_Estrutura_Produto = " +ed.getOid_Estrutura_Produto();
	      if (ed.getOid_Estrutura_Fornecedor() > 0)
	          sql += " AND Produtos.oid_Estrutura_Fornecedor = " + ed.getOid_Estrutura_Fornecedor();
	      if (ed.getOid_Setor_Produto() > 0)
	          sql += " AND Produtos.oid_Setor_Produto = "+ed.getOid_Setor_Produto();
	      if (util.doValida(ed.getCD_Estrutura_Produto()))
	          sql += " AND Estruturas_Produtos.CD_Estrutura_Produto = '" + ed.getCD_Estrutura_Produto()+ "'";
	      if (util.doValida(ed.getOID_Produto_Cliente()))
	          sql += " AND Produtos_Clientes.oid_Produto_Cliente = '"+ed.getOID_Produto_Cliente()+"'";
	      if (util.doValida(ed.getOid_Pessoa_Distribuidor()))
	          sql += " AND Produtos_Clientes.oid_pessoa = '" + ed.getOid_Pessoa_Distribuidor() + "'";
	      else if (ed.getOid_Mix() > 0){
	          sql += " AND Produtos.oid_produto = Mix_Produtos.oid_produto" +
	          		 " AND Mix_Produtos.oid_mix = " + ed.getOid_Mix();
	      }
	      if (util.doValida(ed.getNOT_DM_Situacao()))
	          sql += " AND  Produtos_Clientes.DM_Situacao <> '" + ed.getNOT_DM_Situacao() + "'";

	      sql += !util.doValida(orderBy) ? " Order by NM_Produto" : orderBy;

	      ResultSet res = this.executasql.executarConsulta(sql);
	      while (res.next())
          {
	          ProdutoED edVolta = new ProdutoED();

	          edVolta.setOID_Produto_Cliente(res.getString("oid_Produto_Cliente"));
	          edVolta.setOID_Produto(res.getString("oid_Produto"));
	          edVolta.setCD_Produto(res.getString("cd_Produto"));
	          edVolta.setNM_Produto(res.getString("nm_Produto"));
	          edVolta.setCD_Fornecedor(res.getString("CD_Fornecedor"));
	          edVolta.setOid_Localizacao(res.getInt("oid_Localizacao"));

	          edVolta.setCD_Unidade_Produto(res.getString("cd_Unidade_Produto"));
	          edVolta.setNR_QT_Caixa(res.getInt("NR_QT_Caixa"));
	          edVolta.setNR_Peso_Medio(res.getDouble("NR_Peso_Medio"));
	          edVolta.setDM_Pesagem(res.getString("DM_Pesagem"));
	          edVolta.setPesagem("S".equals(edVolta.getDM_Pesagem()));
              edVolta.setOid_Pessoa_Distribuidor(util.getTableStringValue("oid_Pessoa","Produtos_Clientes","oid_Produto_Cliente = '"+edVolta.getOID_Produto_Cliente()+"'"));
	          edVolta.setOid_Pessoa(res.getString("oid_Pessoa"));
	          //edVolta.setOid_Estrutura_Produto(res.getInt("oid_Estrutura_Produto"));
	          //edVolta.setCD_Estrutura_Produto(res.getString("cd_Estrutura_Produto"));
	          //edVolta.setNM_Estrutura_Produto(res.getString("NM_Estrutura_Produto"));
	          //edVolta.setDm_Estrutura_Produto(res.getString("dm_Estrutura_Produto"));
	          edVolta.setOid_Estrutura_Fornecedor(res.getInt("oid_Estrutura_Fornecedor"));
	          edVolta.setOid_Classificacao_Fiscal(res.getInt("oid_Classificacao_Fiscal"));
	          edVolta.setOid_Situacao_Tributaria(res.getInt("oid_Situacao_Tributaria"));
	          edVolta.setOid_Setor_Produto(res.getInt("Oid_Setor_Produto"));
	          if (edVolta.getOid_Setor_Produto() > 0)
	              edVolta.edSetor = new Setor_ProdutoBD(executasql).getByRecord(new Setor_ProdutoED(edVolta.getOid_Setor_Produto()));

	          //*** Pessoa_Fornecedor
              if (util.doValida(edVolta.getOid_Pessoa()))
              {
                  PessoaED edPessoa = new PessoaBD(executasql).getByRecord(new PessoaED(edVolta.getOid_Pessoa()));
                  edVolta.setNR_CNPJ_CPF(edPessoa.getNR_CNPJ_CPF());
                  edVolta.setNM_Razao_Social(edPessoa.getNM_Razao_Social());
              }
	          //*** Pessoa_Distribuidor
	          if (util.doValida(edVolta.getOid_Pessoa_Distribuidor()))
              {
	              PessoaBean edPessoa = PessoaBean.getByNR_CNPJ_CPF(edVolta.getOid_Pessoa_Distribuidor());
	              edVolta.setNM_Distribuidor(edPessoa.getNM_Razao_Social());
	              edVolta.setCD_Distribuidor(edPessoa.getNR_CNPJ_CPF());
	          }
	          //*** Mix
	          if (ed.getOid_Mix() > 0)
              {
	              MixED edMix = new MixBD(executasql).getByRecord(new MixED(ed.getOid_Mix()));
	              edVolta.setOid_Mix(edMix.getOid_Mix());
	              edVolta.setNM_Mix(edMix.getNm_Mix());
	              edVolta.setCD_Mix(edMix.getCd_Mix());
	          }
	          //*** Estrutura_Fornecedor
	          if (edVolta.getOid_Estrutura_Fornecedor() > 0)
              {
	              Estrutura_ProdutoED edEstrutura = new Estrutura_ProdutoBD(executasql).getByRecord(new Estrutura_ProdutoED(edVolta.getOid_Estrutura_Fornecedor()));
	              edVolta.setCD_Estrutura_Fornecedor(edEstrutura.getCd_Estrutura_Produto());
	              edVolta.setNM_Estrutura_Fornecedor(edEstrutura.getNm_Estrutura_Produto());
	          }
	          //*** Classificação Fiscal
	          if (edVolta.getOid_Classificacao_Fiscal() > 0)
              {
	              Classificacao_FiscalED edFiscal_Volta = new Classificacao_FiscalBD(executasql).getByOidClassificacao_Fiscal(edVolta.getOid_Classificacao_Fiscal());
	              edVolta.setCD_Reduzido(edFiscal_Volta.getCD_Reduzido());
	              edVolta.setCD_Fiscal(edFiscal_Volta.getCD_Fiscal());
	          }
	          //*** Situação Tributária
	          if (edVolta.getOid_Situacao_Tributaria() > 0)
              {
	              Situacao_TributariaED edSituacao = new Situacao_TributariaBD(executasql).getByOidSituacao_Tributaria(edVolta.getOid_Situacao_Tributaria());
	              edVolta.setCD_Situacao_Tributaria(edSituacao.getCD_Situacao_Tributaria());
	              edVolta.setNM_Situacao_Tributaria(edSituacao.getNM_Situacao_Tributaria());
	          }
	          //*** Localizacao
              if (edVolta.getOid_Localizacao() > 0) {
            	  WMS_LocalizacaoED edLocalizacao = new WMS_LocalizacaoED();
            	  edLocalizacao.setOid_Localizacao(edVolta.getOid_Localizacao());
                  edLocalizacao = new WMS_LocalizacaoBD(executasql).getByRecord(edLocalizacao);
                  edVolta.setCD_Localizacao(edLocalizacao.getCD_Localizacao());
              } else {
            	  edVolta.setCD_Localizacao("");
              }

	          list.add(edVolta);
	      }
  	    } catch(Exception exc){
  	        throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "listaProdutoCliente()");
  	    }
  	    return list;
  	}

  	public ArrayList listaProdutoCliente(ProdutoED ed)throws Excecoes {
  	    return this.listaProdutoCliente(ed, null);
  	}

  	//*** Produtos Vendedor(Produtos relacionado ao Mix do Vendedor)
  	public ArrayList listaProdutoVendedor(String oid_Vendedor, String oid_Pessoa_Distribuidora)throws Excecoes{

      String sql = null;
      ArrayList lista = new ArrayList();
      try{

          if (!util.doValida(oid_Vendedor))
    		    throw new Excecoes("Vendedor não informado!");
          if (!util.doValida(oid_Pessoa_Distribuidora))
  		    	throw new Excecoes("Distribuidora não informada!");

	      sql = " SELECT DISTINCT * "+
	  			" FROM 	Produtos, " +
	  			"		Estruturas_Produtos, " +
	  			"		Unidades_Produtos, " +
	  			"		Tipos_Produtos, " +
	  			"		Classificacoes_Fiscais, " +
	  			"		Situacoes_Tributarias," +
	  			"		Produtos_Clientes " +
	  			" WHERE Produtos.oid_Tipo_Produto = Tipos_Produtos.oid_Tipo_Produto " +
	  			" 	AND Produtos.oid_Classificacao_Fiscal = Classificacoes_Fiscais.oid_Classificacao_Fiscal " +
	  			" 	AND Produtos.oid_Situacao_Tributaria = Situacoes_Tributarias.oid_Situacao_Tributaria " +
	  			" 	AND Produtos.oid_Unidade_Produto = Unidades_Produtos.oid_Unidade_Produto " +
	     		"   AND Produtos.oid_Setor_Produto = Setores_Produtos.oid_Setor_Produto "+
	  			" 	AND Produtos_Clientes.oid_Produto = Produtos.oid_Produto" +
	  			/*"   AND Mix_Vendedores.oid_mix = Mix.oid_mix" +
	  			"   AND Mix_Vendedores.oid_vendedor = Vendedores.oid_vendedor" +
	  			"   AND Mix_Produtos.oid_mix = Mix_Vendedores.oid_mix" +
	  			"   AND Mix_Produtos.oid_produto = Produtos.oid_produto" +*/
	  			"   AND Vendedores.oid_Vendedor ='" + oid_Vendedor + "'" +
	  			"   AND Produtos_Clientes.oid_Pessoa = '" + oid_Pessoa_Distribuidora + "'" +
	        	" ORDER BY NM_Produto";

	        ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                ProdutoED edVolta = new ProdutoED();

                edVolta.setOID_Produto(res.getString("oid_Produto"));
                edVolta.setCD_Produto(res.getString("cd_Produto"));
                edVolta.setNM_Produto(res.getString("nm_Produto"));
                edVolta.setCD_Fornecedor(res.getString("CD_Fornecedor"));

                edVolta.setCD_Unidade_Produto(res.getString("cd_Unidade_Produto"));
                edVolta.setDM_Pesagem(res.getString("DM_Pesagem"));
                edVolta.setPesagem("S".equals(edVolta.getDM_Pesagem()));
                edVolta.setOid_Pessoa(res.getString("oid_Pessoa"));
                edVolta.setOid_Estrutura_Produto(res.getInt("oid_Estrutura_Produto"));
                edVolta.setCD_Estrutura_Produto(res.getString("cd_Estrutura_Produto"));
                edVolta.setDm_Estrutura_Produto(res.getString("dm_Estrutura_Produto"));

                edVolta.setOid_Estrutura_Fornecedor(res.getInt("oid_Estrutura_Fornecedor"));
                edVolta.setOid_Classificacao_Fiscal(res.getInt("oid_Classificacao_Fiscal"));
                edVolta.setOid_Situacao_Tributaria(res.getInt("oid_Situacao_Tributaria"));
                edVolta.setOid_Setor_Produto(res.getInt("Oid_Setor_Produto"));
                if (edVolta.getOid_Setor_Produto() > 0)
  	              	edVolta.edSetor = new Setor_ProdutoBD(executasql).getByRecord(new Setor_ProdutoED(edVolta.getOid_Setor_Produto()));

                edVolta.setOid_Nivel_Produto1(res.getInt("Oid_Nivel_Produto1"));
                edVolta.setOid_Nivel_Produto2(res.getInt("Oid_Nivel_Produto2"));
                edVolta.setOid_Nivel_Produto3(res.getInt("Oid_Nivel_Produto3"));
                edVolta.setOid_Nivel_Produto4(res.getInt("Oid_Nivel_Produto4"));
                edVolta.setOid_Nivel_Produto5(res.getInt("Oid_Nivel_Produto5"));

                //*** Pessoa_Fornecedor
                if (util.doValida(edVolta.getOid_Pessoa()))
                {
                    PessoaED edPessoa = new PessoaBD(executasql).getByRecord(new PessoaED(edVolta.getOid_Pessoa()));
                    edVolta.setNR_CNPJ_CPF(edPessoa.getNR_CNPJ_CPF());
                    edVolta.setNM_Razao_Social(edPessoa.getNM_Razao_Social());
                }
                //*** Pessoa_Distribuidor
                if (util.doValida(oid_Pessoa_Distribuidora))
                {
                    PessoaBean edPessoa = PessoaBean.getByNR_CNPJ_CPF(oid_Pessoa_Distribuidora);
                    edVolta.setOid_Pessoa_Distribuidor(oid_Pessoa_Distribuidora);
                    edVolta.setNM_Distribuidor(edPessoa.getNM_Razao_Social());
                    edVolta.setCD_Distribuidor(edPessoa.getNR_CNPJ_CPF());
                }
                //*** Mix
                edVolta.setOid_Mix(util.getTableIntValue("oid_Mix",
                        							"Mix_Vendedores",
                        							" Mix_Vendedores.oid_Mix = Mix.oid_Mix"+
                        							" AND Mix_Vendedores.oid_Mix = Mix_Produtos.oid_Mix" +
                        							" AND Mix_Vendedores.oid_Vendedor = '"+oid_Vendedor+"'"+
                        							" AND Mix_Produtos.oid_Produto = '"+edVolta.getOID_Produto()+"'"));
                if (edVolta.getOid_Mix() > 0)
                {
                    MixED edMix = new MixBD(executasql).getByRecord(new MixED(edVolta.getOid_Mix()));
                    edVolta.setNM_Mix(edMix.getNm_Mix());
                    edVolta.setCD_Mix(edMix.getCd_Mix());
                }
                //*** Estrutura Produto
                edVolta.setNM_Estrutura_Produto(res.getString("NM_Estrutura_Produto"));
                //*** Estrutura Fornecedor
                if (edVolta.getOid_Estrutura_Fornecedor() > 0)
                {
                    Estrutura_ProdutoED edEstrutura = new Estrutura_ProdutoBD(executasql).getByRecord(new Estrutura_ProdutoED(edVolta.getOid_Estrutura_Fornecedor()));
                    edVolta.setCD_Estrutura_Fornecedor(edEstrutura.getCd_Estrutura_Produto());
                    edVolta.setNM_Estrutura_Fornecedor(edEstrutura.getNm_Estrutura_Produto());
                }
                //*** Classificação Fiscal
                if (edVolta.getOid_Classificacao_Fiscal() > 0)
                {
                    Classificacao_FiscalED edFiscal = new Classificacao_FiscalBD(executasql).getByOidClassificacao_Fiscal(edVolta.getOid_Classificacao_Fiscal());
                    edVolta.setCD_Reduzido(edFiscal.getCD_Reduzido());
                    edVolta.setCD_Fiscal(edFiscal.getCD_Fiscal());
                }
                //*** Situação Tributária
                if (edVolta.getOid_Situacao_Tributaria() > 0)
                {
                    Situacao_TributariaED edSituacao = new Situacao_TributariaBD(executasql).getByOidSituacao_Tributaria(edVolta.getOid_Situacao_Tributaria());
                    edVolta.setCD_Situacao_Tributaria(edSituacao.getCD_Situacao_Tributaria());
                    edVolta.setNM_Situacao_Tributaria(edSituacao.getNM_Situacao_Tributaria());
                }
                lista.add(edVolta);
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "listaProdutoVendedor()");
        }
        return lista;
    }

  	//*** Produtos Para Tabelas de Vendas
  	//    Busca todos produtos ainda NÃO RELACIONADOS a TABELA Passada
  	public ArrayList listaProdutoSemTabela(ProdutoED ed)throws Excecoes {

  	    String sql = null;
  	    ArrayList lista = new ArrayList();
  	    try{

  	        if (ed.edPreco.getOid_Tabela_Venda() < 1)
  	            throw new Excecoes("Tabela de Venda não informada!");
  	        if (!util.doValida(ed.getOid_Pessoa_Distribuidor()))
  		    	throw new Excecoes("Distribuidora não informada!");

  	        sql = " SELECT DISTINCT "+
                  "     Produtos.oid_Produto" +
                  "    ,Produtos.CD_Produto" +
                  "    ,Produtos.NM_Produto" +
                  "    ,Produtos.CD_Fornecedor" +
                  "    ,Produtos.oid_Pessoa" +
                  "    ,Produtos_Clientes.oid_Produto_Cliente" +
                  "    ,Produtos_Clientes.DM_Situacao" +
                  "    ,Precos_Produtos.oid_Preco_Produto" +
                  "    ,Unidades_Produtos.CD_Unidade_Produto" +
                  "    ,Unidades_Produtos.NM_Unidade_Produto" +
                  "    ,Unidades_Produtos.DM_Pesagem"+
  	        	  " FROM Produtos " +
  	        	  "		,Produtos_Clientes" +
  	        	  "		 LEFT JOIN Precos_Produtos" +
				  "			ON Produtos_Clientes.oid_Produto_Cliente = Precos_Produtos.oid_Produto_Cliente" +
				  "			AND Precos_Produtos.oid_Tabela_Venda = "+ed.edPreco.getOid_Tabela_Venda()+
  	        	  "		,Unidades_Produtos" +
  	        	  " WHERE Produtos.oid_Unidade_Produto = Unidades_Produtos.oid_Unidade_Produto " +
  	        	  "   AND Produtos_Clientes.oid_Produto = Produtos.oid_Produto" +
  	        	  "   AND Produtos_Clientes.oid_Pessoa = '"+ed.getOid_Pessoa_Distribuidor()+"'" +
  	        	  "   AND Precos_Produtos.oid_Preco_Produto IS NULL";

  	        if (util.doValida(ed.getOID_Produto()))
  	            sql +="   AND Produtos.oid_Produto = '"+ed.getOID_Produto()+"'";
  	        if (util.doValida(ed.getCD_Produto()))
	            sql +="   AND Produtos.CD_Produto = '"+ed.getCD_Produto()+"'";
  	        if (util.doValida(ed.getCD_Fornecedor()))
	            sql +="   AND Produtos.CD_Fornecedor = '"+ed.getCD_Fornecedor()+"'";
  	        if (util.doValida(ed.getOid_Pessoa()))
            {
	            sql +="   AND Fornecedores.oid_Pessoa = '"+ed.getOid_Pessoa()+"'" +
                      "   AND (Produtos.oid_pessoa = Fornecedores.oid_Pessoa OR (SELECT count(*) " +
                      "                                                          FROM Fornecedores_Produtos" +
                      "                                                          WHERE Fornecedores_Produtos.oid_Pessoa = Fornecedores.oid_pessoa" +
                      "                                                            AND Fornecedores_Produtos.oid_Produto = Produtos.oid_Produto) > 0)";
            }
  	        if (ed.getOid_Setor_Produto() > 0)
		        sql +="   AND Produtos.oid_Setor_Produto = "+ed.getOid_Setor_Produto();
	        if (ed.getOid_Estrutura_Produto() > 0)
	            sql +="   AND Produtos.oid_Estrutura_Produto = "+ed.getOid_Estrutura_Produto();
	        if (ed.getOid_Mix() > 0) {
	            sql +="   AND Produtos.oid_Produto = Mix_Produtos.oid_Produto" +
	            	  "   AND Mix_Produtos.oid_Mix = "+ed.getOid_Mix();
	        }
  	        sql +=" ORDER BY Produtos.NM_Produto";
  	        //*** Paginação
  	        sql += ed.getSQLPaginacao();

  	        ResultSet res = this.executasql.executarConsulta(sql);
  	        while (res.next())
            {
                ProdutoED edVolta = new ProdutoED();

                edVolta.setOID_Produto(res.getString("oid_Produto"));
                edVolta.setOID_Produto_Cliente(res.getString("oid_Produto_Cliente"));
                edVolta.setCD_Produto(res.getString("CD_Produto"));
                edVolta.setNM_Produto(res.getString("NM_Produto"));
                edVolta.setCD_Fornecedor(res.getString("CD_Fornecedor"));
                edVolta.setCD_Unidade_Produto(res.getString("CD_Unidade_Produto"));
                edVolta.setNM_Unidade_Produto(res.getString("NM_Unidade_Produto"));
                edVolta.setDM_Situacao(res.getString("DM_Situacao"));
                edVolta.setDM_Pesagem(res.getString("DM_Pesagem"));
                edVolta.setPesagem("S".equals(edVolta.getDM_Pesagem()));
                //*** Fornecedor
                edVolta.setOid_Pessoa(res.getString("oid_Pessoa"));
                if (util.doValida(edVolta.getOid_Pessoa()))
                {
                    PessoaED edPessoa = new PessoaBD(executasql).getByRecord(new PessoaED(edVolta.getOid_Pessoa()));
                    edVolta.setNR_CNPJ_CPF(edPessoa.getNR_CNPJ_CPF());
                    edVolta.setNM_Razao_Social(edPessoa.getNM_Razao_Social());
                }
                lista.add(edVolta);
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "listaProdutoSemTabela()");
        }
        return lista;
    }
  	public ProdutoED getProdutoSemTabela(ProdutoED ed)throws Excecoes{

  	    try{
  	        Iterator iterator = listaProdutoSemTabela(ed).iterator();
  	        if (iterator.hasNext()) {
  	            return (ProdutoED) iterator.next();
  	        } else return new ProdutoED();
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "getProdutoSemTabela()");
        }
    }

  	//*** Produtos Tabelas de Vendas
  	//    Busca todos produtos RELACIONADOS a TABELAS DE VENDA
  	public ArrayList listaProdutoTabela(ProdutoED ed)throws Excecoes {

  	    String sql = null;
  	    ArrayList lista = new ArrayList();
  	    try{

  	        if (!util.doValida(ed.getOid_Pessoa_Distribuidor()))
  		    	throw new Excecoes("Distribuidora não informada!");

  	        sql = " SELECT DISTINCT " +
                  "     Produtos.oid_Produto" +
                  "    ,Produtos.CD_Produto" +
                  "    ,Produtos.NM_Produto" +
                  "    ,Produtos.CD_Fornecedor" +
                  "    ,Produtos.NM_Descricao_Caixa" +
                  "    ,Produtos.oid_Setor_Produto" +
                  "    ,Produtos.oid_Pessoa" +
                  "    ,Produtos_Clientes.oid_Produto_Cliente" +
                  "    ,Produtos_Clientes.DM_Situacao" +
                  "    ,Precos_Produtos.oid_Preco_Produto" +
                  "    ,Unidades_Produtos.CD_Unidade_Produto" +
                  "    ,Unidades_Produtos.NM_Unidade_Produto" +
                  "    ,Unidades_Produtos.DM_Pesagem"+
  	        	  " FROM Produtos " +
  	        	  "		,Produtos_Clientes" +
  	        	  "		 INNER JOIN Precos_Produtos" +
				  "			ON Produtos_Clientes.oid_Produto_Cliente = Precos_Produtos.oid_Produto_Cliente" +
  	        	  "		,Unidades_Produtos" +
  	        	  " WHERE Produtos.oid_Unidade_Produto = Unidades_Produtos.oid_Unidade_Produto " +
  	        	  "   AND Produtos_Clientes.oid_Produto = Produtos.oid_Produto" +
  	        	  "   AND Produtos_Clientes.oid_Pessoa = '"+ed.getOid_Pessoa_Distribuidor()+"'";

  	        if (ed.edPreco.getOid_Tabela_Venda() > 0)
  	            sql +="   AND Precos_Produtos.oid_Tabela_Venda = " + ed.edPreco.getOid_Tabela_Venda();
  	        if (util.doValida(ed.edPreco.getDM_Alterado()))
                sql +="   AND Precos_Produtos.DM_Alterado = '"+ed.edPreco.getDM_Alterado()+"'";
  	        if (util.doValida(ed.getOID_Produto_Cliente()))
	            sql +="   AND Produtos_Clientes.oid_Produto_Cliente = '"+ed.getOID_Produto_Cliente()+"'";
  	        if (util.doValida(ed.getOID_Produto()))
  	            sql +="   AND Produtos.oid_Produto = '"+ed.getOID_Produto()+"'";
  	        if (util.doValida(ed.getCD_Produto()))
	            sql +="   AND Produtos.CD_Produto = '"+ed.getCD_Produto()+"'";
  	        if (util.doValida(ed.getCD_Fornecedor()))
	            sql +="   AND Produtos.CD_Fornecedor = '"+ed.getCD_Fornecedor()+"'";
  	        if (util.doValida(ed.getOid_Pessoa()))
	            sql +="   AND Fornecedores.oid_Pessoa = '"+ed.getOid_Pessoa()+"'" +
                      "   AND (Produtos.oid_pessoa = Fornecedores.oid_Pessoa OR (SELECT count(*) " +
                      "                                                          FROM Fornecedores_Produtos" +
                      "                                                          WHERE Fornecedores_Produtos.oid_Pessoa = Fornecedores.oid_pessoa" +
                      "                                                            AND Fornecedores_Produtos.oid_Produto = Produtos.oid_Produto) > 0)";
  	        if (ed.getOid_Estrutura_Produto() > 0)
	            sql +="   AND Produtos.oid_Estrutura_Produto = "+ed.getOid_Estrutura_Produto();
  	        if (ed.getOid_Mix() > 0) {
	            sql +="   AND Produtos.oid_Produto = Mix_Produtos.oid_Produto" +
	            	  "   AND Mix_Produtos.oid_Mix = "+ed.getOid_Mix();
  	        }
  	        //*** Inclusões no PERIODO
            if (util.doValida(ed.edPreco.getDT_Vigencia()) && util.doValida(ed.edPreco.getDT_Vigencia_Final())) {
                sql +=" AND Precos_Produtos.DT_Vigencia BETWEEN '"+ed.edPreco.getDT_Vigencia()+"' AND '"+ed.edPreco.getDT_Vigencia_Final()+"'";
            } else if (util.doValida(ed.edPreco.getDT_Vigencia()) || util.doValida(ed.edPreco.getDT_Vigencia_Final())) {
                sql +=" AND Precos_Produtos.DT_Vigencia = '"+(util.doValida(ed.edPreco.getDT_Vigencia()) ? ed.edPreco.getDT_Vigencia() : ed.edPreco.getDT_Vigencia_Final())+"'";
            }
  	        sql +=" ORDER BY Produtos.NM_Produto";
  	        //*** Paginação
  	        sql += ed.getSQLPaginacao();

  	        ResultSet res = this.executasql.executarConsulta(sql);
  	        while (res.next())
            {
  	            ProdutoED edVolta = new ProdutoED();

                edVolta.setOID_Produto(res.getString("oid_Produto"));
                edVolta.setOID_Produto_Cliente(res.getString("oid_Produto_Cliente"));
                edVolta.setCD_Produto(res.getString("CD_Produto"));
                edVolta.setNM_Produto(res.getString("NM_Produto"));
                edVolta.setCD_Fornecedor(res.getString("CD_Fornecedor"));
                edVolta.setCD_Unidade_Produto(res.getString("CD_Unidade_Produto"));
                edVolta.setNM_Unidade_Produto(res.getString("NM_Unidade_Produto"));
                edVolta.setNM_Descricao_Caixa(res.getString("NM_Descricao_Caixa"));
                edVolta.setDM_Situacao(res.getString("DM_Situacao"));
                edVolta.setDM_Pesagem(res.getString("DM_Pesagem"));

                edVolta.edPreco = new Preco_ProdutoBD(executasql).getByRecord(new Preco_ProdutoED(res.getLong("oid_Preco_Produto"), true, false));
                edVolta.setOid_Setor_Produto(res.getInt("Oid_Setor_Produto"));
                if (edVolta.getOid_Setor_Produto() > 0)
  	              	edVolta.edSetor = new Setor_ProdutoBD(executasql).getByRecord(new Setor_ProdutoED(edVolta.getOid_Setor_Produto()));
                edVolta.setPesagem("S".equals(edVolta.getDM_Pesagem()));
                //*** Fornecedor
                edVolta.setOid_Pessoa(res.getString("oid_Pessoa"));
                if (util.doValida(edVolta.getOid_Pessoa()))
                {
                    PessoaED edPessoa = new PessoaBD(executasql).getByRecord(new PessoaED(edVolta.getOid_Pessoa()));
                    edVolta.setNR_CNPJ_CPF(edPessoa.getNR_CNPJ_CPF());
                    edVolta.setNM_Razao_Social(edPessoa.getNM_Razao_Social());
                }
                lista.add(edVolta);
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "listaProdutoTabela()");
        }
        return lista;
    }
  	public ProdutoED getProdutoTabela(ProdutoED ed)throws Excecoes {

  	    try{
  	        Iterator iterator = listaProdutoTabela(ed).iterator();
  	        if (iterator.hasNext()) {
  	            return (ProdutoED) iterator.next();
  	        } else return new ProdutoED();
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "getProdutoTabela()");
        }
    }

  	public ArrayList listaDistribuidoras(ProdutoED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();
        try {

            sql = " SELECT Produtos_Clientes.oid_Produto_Cliente" +
                  "     ,Produtos.oid_Produto" +
                  "     ,Produtos.CD_Produto" +
                  "     ,Produtos.NM_Produto" +
                  "     ,Produtos_Clientes.oid_produto_cliente" +

                  "     ,Pessoa_Cliente.NM_Razao_Social as NM_Cliente " +
                  " FROM Produtos" +

                  "     ,Pessoas Pessoa_Cliente" +
                  "     ,Produtos_Clientes " +
                  " WHERE Produtos_Clientes.oid_Pessoa = Pessoa_Cliente.oid_Pessoa " +

                  "   AND Produtos.oid_Produto = Produtos_Clientes.oid_Produto ";

            if (util.doValida(ed.getOID_Produto()))
                sql += " AND  Produtos.oid_Produto = " + ed.getOID_Produto();
            if (util.doValida(ed.getOid_Pessoa()))
                sql += " AND  Produtos.OID_Pessoa '= " + ed.getOid_Pessoa() + "'";
            if (util.doValida(ed.getOid_Pessoa_Cliente()))
                sql += " AND  Produtos_Clientes.Oid_Pessoa '= " + ed.getOid_Pessoa_Cliente() + "'";
            sql += " ORDER BY cd_Produto";

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                ProdutoED edVolta = new ProdutoED();

                edVolta.setOID_Produto(res.getString("oid_Produto"));
                edVolta.setOID_Produto_Cliente(res.getString("oid_Produto_Cliente"));
                edVolta.setCD_Produto(res.getString("cd_Produto"));
                edVolta.setNM_Produto(res.getString("nm_Produto"));
                edVolta.setNM_Razao_Social_Cliente(res.getString("NM_Cliente"));

                list.add(edVolta);
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "listaDistribuidoras()");
        }
        return list;
    }

  	// *** Produtos Relacionados a Tabela de Preços
  	public ArrayList listaProdutosVenda(ProdutoED ed) throws Excecoes {

  	    String sql = null;
  	    ArrayList lista = new ArrayList();
  	    try {

  	        if (!util.doValida(ed.getOid_Pessoa()))
  	            throw new Mensagens("ID Vendedor não informado!");
  	        if (!util.doValida(ed.getOid_Pessoa_Distribuidor()))
  	            throw new Mensagens("ID Distribuidor não informado!");
  	        if (!util.doValida(ed.getOid_Pessoa_Cliente()))
  	            throw new Mensagens("ID Cliente não informado!");
  	        //*** Caso não seje passado um Tipo de Estoque, dusca do Estoque Disponível
  	        if (ed.getOid_Tipo_Estoque() < 1)
            {
  	            ed.setOid_Tipo_Estoque(Parametro_FixoED.getInstancia().getOID_Tipo_Estoque());
  	            if (ed.getOid_Tipo_Estoque() < 1)
  	                throw new Excecoes("ID Tipo Estoque não informado!");
  	        }

            /** BUSCA TABELA PELO TIPO
            if (ed.edPreco.getOid_Tabela_Venda() < 1)
            {
      	        //*** Verifica se existe Tipo de Tabela para o Cliente
      	        if ((oid_Tipo_Tabela_Venda = getTableIntValue("oid_Tipo_Tabela_Venda",
      	                									  "Clientes_Vendedores",
      	                									  "oid_Cliente = '"+ed.getOid_Pessoa_Cliente()+"' " +
      	                									  " AND oid_Vendedor = '"+ed.getOid_Pessoa()+"'")) < 1) {
      	            //*** Caso não Encontre Tabela no Cliente, Verifica se existe Tipo de Tabela para o Vendedor
      	            if ((oid_Tipo_Tabela_Venda = getTableIntValue("oid_Tipo_Tabela_Venda",
      	                    									  "Vendedores",
      	                    									  "oid_Vendedor = '"+ed.getOid_Pessoa()+"'")) < 1) {
      	                throw new Mensagens("Vendedor sem Tipo de Tabela Cadastrado!");
      	            }
      	        }
      	        ed.edPreco.edTabela.setOid_Tipo_Tabela_Venda(oid_Tipo_Tabela_Venda);
      	        ed.edPreco.edTabela.setOid_Pessoa(ed.getOid_Pessoa_Distribuidor());
      	        edTabela = new Tabela_VendaBD(executasql).getUltimaTabelaByTipo(ed.edPreco.edTabela, true);
            } else {
                BUSCA PELA TABELA INFORMADA
                edTabela = new Tabela_VendaBD(executasql).getByRecord(new Tabela_VendaED(ed.edPreco.getOid_Tabela_Venda()));
            }
  	    	**/
  	        //*** Busca os Produtos Relacionados ao:
  	        //  - Mix_Vendedor;
  	        //	- Distribuidora;
  	        //	- Ultima Tabela de Venda Ativa
  	        sql = " SELECT Produtos.oid_Produto" +
                  "       ,Produtos.CD_Produto" +
                  "       ,Produtos.CD_Fornecedor" +
                  "       ,Produtos.NM_Produto" +
                  "       ,Produtos_Clientes.oid_Produto_Cliente" +
                  "       ,Estoques_Clientes.oid_Localizacao" +
	      		  " FROM Produtos" +
	      		  "		,Estoques_Clientes" +
	      		  "		,Produtos_Clientes" +
	      		  "		, Tipos_Estoques"+
	      		  //"		,Precos_Produtos" +
	      		  " WHERE Produtos.oid_Produto = Produtos_Clientes.oid_Produto" +
	      		  "   AND Estoques_Clientes.oid_Produto_Cliente = Produtos_Clientes.oid_Produto_Cliente " + //++++
	      		  "   AND Estoques_Clientes.oid_Tipo_Estoque = Tipos_Estoques.oid_Tipo_Estoque "+ //++++
	      		  "   AND Estoques_Clientes.oid_Tipo_Estoque = "+ ed.getOid_Tipo_Estoque()+
	      		  "   AND Produtos_Clientes.oid_Pessoa = '"+ed.getOid_Pessoa_Distribuidor()+"'" ;
  	        if (util.doValida(ed.getOID_Produto()))
  	            sql += " AND Produtos.oid_Produto = "+ed.getOID_Produto();
  	        if (ed.getOid_Localizacao() > 0)
	            sql += " AND Estoques_Clientes.oid_Localizacao = "+ed.getOid_Localizacao();
  	        if (util.doValida(ed.getCD_Produto()))
  	            sql += " AND Produtos.CD_Produto = '"+ed.getCD_Produto()+"'";
  	        if (util.doValida(ed.getCD_Fornecedor()))
  	            sql += " AND Produtos.CD_Fornecedor = '"+ed.getCD_Fornecedor()+"'";
  	        if (util.doValida(ed.getNM_Produto()))
  	            sql += " AND Produtos.NM_Produto LIKE '"+ed.getNM_Produto()+"%'";
            sql += " ORDER BY Produtos.NM_Produto";

//            System.out.println(sql);

            ResultSet res = executasql.executarConsulta(sql);
  	        while (res.next())
            {
  	            ProdutoED edVolta = new ProdutoED();

  	            edVolta.setOID_Produto_Cliente(res.getString("oid_Produto_Cliente"));
  	            edVolta.setOID_Produto(res.getString("oid_Produto"));
  	            edVolta.setOid_Pessoa_Cliente(ed.getOid_Pessoa_Cliente());
  	            edVolta.setOid_Pessoa_Distribuidor(ed.getOid_Pessoa_Distribuidor());

  	            edVolta.setCD_Produto(res.getString("CD_Produto"));
  	            edVolta.setCD_Fornecedor(res.getString("CD_Fornecedor"));
  	            edVolta.setNM_Produto(res.getString("NM_Produto"));
  	            edVolta.setOid_Localizacao(res.getInt("oid_Localizacao"));

  	            //*** Quantidade em Estoque
  	            edVolta.setNR_QT_Estoque(new wms005Bean().getQtdEstoque(edVolta.getOID_Produto_Cliente(), String.valueOf(ed.getOid_Tipo_Estoque()), edVolta.getOid_Localizacao()));
  	            edVolta.setPossui_Estoque(edVolta.getNR_QT_Estoque() > 0);

  	            //*** Lista completa
  	            lista.add(edVolta);
  	        }
  	    } catch (Excecoes exc) {
  	        throw exc;
  	    } catch (Exception exc) {
  	        throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                  	"listaProdutosTabela()");
  	    }
  	    return lista;
  	}

  	//*** Produto relacionados a Tabela de Preços
    public ProdutoED getPrecoTabelaByProduto(ProdutoED ed) throws Excecoes {

    	Iterator iterator = this.listaProdutosVenda(ed).iterator();
        while (iterator.hasNext())
        {
//        	System.out.println("inside list.iterator");
            ProdutoED edProduto = (ProdutoED) iterator.next();
            //*** Quantidade Total Atendida de um PRODUTO
			double qtdTotalAtendida = new wms005Bean().getQtdTotalAtendida(null, edProduto.getOID_Produto(), String.valueOf(edProduto.getOid_Tipo_Estoque()), edProduto.getOid_Localizacao(), "S");
//			System.out.println("back: " + edProduto.getNR_QT_Estoque());
//			System.out.println("atendida: " + qtdTotalAtendida);
			if ((edProduto.getNR_QT_Estoque() - qtdTotalAtendida) > 0)
                return this.getByRecord(edProduto);
        }
        return new ProdutoED();
    }

  	/** -------- RELATÓRIOS ---------- **/
    public void relEstruturasProduto(RelatorioED ed) throws Excecoes {

   	    ArrayList lista = new ArrayList();
   	    try{
   	        ProdutoED edVolta = new ProdutoED();
   	        edVolta.setOid_Pessoa(ed.getOid_pessoa());
   	        edVolta.setOid_Pessoa_Distribuidor(ed.getOid_pessoa_distribuidor());
   	        edVolta.setOid_Estrutura_Produto(ed.getOid_estrutura_produto());
   	        edVolta.setOid_Estrutura_Fornecedor(ed.getOid_estrutura_fornecedor());

   	        ArrayList listaProduto = this.listaProdutoCliente(edVolta, " ORDER BY Produtos."+ (ed.isProduto() ? "oid_Estrutura_Produto" : "oid_Estrutura_Fornecedor"));
   	        for (int i=0; i<listaProduto.size(); i++)
            {
   	            ProdutoED edPro = (ProdutoED) listaProduto.get(i);
   	            RelatorioED edRel = new RelatorioED();

   	            Estrutura_ProdutoED edEstrutura = new Estrutura_ProdutoBD(executasql).getByRecord(new Estrutura_ProdutoED(ed.isProduto() ? edPro.getOid_Estrutura_Produto() : edPro.getOid_Estrutura_Fornecedor()));
   	            edRel.setOid_estrutura_produto(edEstrutura.getOid_Estrutura_Produto());
   	            edRel.setCd_estrutura_produto(edEstrutura.getCd_Estrutura_Produto());
   	            edRel.setNm_estrutura_produto(edEstrutura.getNm_Estrutura_Produto());

   	            edRel.setOid_produto(Integer.parseInt(edPro.getOID_Produto()));
   	            edRel.setCd_produto(edPro.getCD_Produto());
   	            edRel.setCd_fornecedor(edPro.getCD_Fornecedor());
   	            edRel.setNm_produto(edPro.getNM_Produto());
   	            edRel.setCd_unidade_produto(edPro.getCD_Unidade_Produto());
   	            edRel.setNr_qt_atual(new wms005Bean().getQtdEstoque(edPro.getOID_Produto_Cliente(), String.valueOf(ed.getOid_tipo_estoque())));
   	            edRel.setNr_caixa(ProdutoBean.getQtdEmCaixas(edRel.getNr_qt_atual(), edPro.getNR_QT_Caixa()));
   	            edRel.setNr_peso_medio(edPro.getNR_Peso_Medio());

   	            lista.add(edRel);
   	        }
   	        ed.setLista(lista);

   	        //*** Seta Parâmetros
            HashMap map = new HashMap();
            map.put("FILTER", ed.isProduto() ? "Estrutura Interna" : "Estrutura do Fornecedor");
            ed.setHashMap(map);

   	        //*** Chama o Gerador de Relatórios Jasper
   	        new JasperRL(ed).geraRelatorio();

   	    } catch (Excecoes e){
	        throw e;
	    } catch(Exception exc){
	        throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
   	                "relEstruturasProduto()");
   	    }
   	}

    //Produtos Modelo 01 (Simples)
    public void RelProdutos(HttpServletResponse response, ProdutoED ed, String Filter) throws Exception {

        ProdutoRelED edRelatorio = new ProdutoRelED();
        ArrayList lista_Volta = new ArrayList();

        ArrayList lista = this.listaProdutoCliente(ed);
        for (int i = 0; i < lista.size(); i++)
        {
            ProdutoRelED edVolta = new ProdutoRelED();

            ProdutoED edProduto = (ProdutoED) lista.get(i);
            edVolta.setCd_produto(edProduto.getCD_Produto());
            edVolta.setCd_produto_fornecedor(util.getValueDef(edProduto.getCD_Fornecedor(), ""));
            edVolta.setNm_produto(edProduto.getNM_Produto());
            edVolta.setCd_fornecedor(edProduto.getCD_Fornecedor());
            edVolta.setNm_fornecedor(edProduto.getNM_Razao_Social());
            edVolta.setCd_estrutura_produto(edProduto.getCD_Estrutura_Produto());
            edVolta.setNm_estrutura_produto(edProduto.getNM_Estrutura_Produto());
            edVolta.setCd_mix(edProduto.getCD_Mix());
            edVolta.setNm_mix(edProduto.getNM_Mix());
            edVolta.setCd_distribuidor(edProduto.getCD_Distribuidor());
            edVolta.setNm_distribuidor(edProduto.getNM_Distribuidor());
            edVolta.setCd_unidade(edProduto.getCD_Unidade_Produto());
            edVolta.setNm_unidade(edProduto.getNM_Unidade_Produto());

            lista_Volta.add(edVolta);
        }
        //*** Seta Layout e Filtro
        edRelatorio.setModelo(ProdutoRelED.MODELO_1);
        edRelatorio.setFilter(Filter);

        new ProdutoRL().geraRelProduto(lista_Volta, edRelatorio, response);
    }

  	//*** Produtos Estoque
  	public void RelProdutosEstoque(HttpServletResponse response, RelatorioED ed) throws Exception {

  	    ArrayList lista = new ArrayList();
  	    String query = null;

  	    query = " SELECT DISTINCT *" +
  	    		" FROM   estoques_clientes" +
  	    		"		,localizacoes" +
  	    		"		,tipos_estoques" +
  	    		"		,produtos_clientes" +
  	    		"		,produtos" +
  	    		"		,Depositos" +
  	    		" WHERE estoques_clientes.oid_localizacao = localizacoes.oid_localizacao" +
  	    		"   AND estoques_clientes.oid_tipo_estoque = tipos_estoques.oid_tipo_estoque" +
  	    		"   AND estoques_clientes.oid_produto_cliente = produtos_clientes.oid_produto_cliente" +
  	    		"	AND localizacoes.oid_Deposito = Depositos.oid_Deposito" +
  	    		"   AND produtos_clientes.oid_produto = produtos.oid_produto";
  	    		//*** Caso seja passado a localização (Deposito, Armazem)
		  		if (ed.getOid_produto() > 0)
		  		    query += "  AND produtos.oid_produto = "+ed.getOid_produto();
  	    		//*** Caso seja passado Fornecedor
		  		if (util.doValida(ed.getOid_pessoa()))
                  query += " AND (Produtos.oid_pessoa = Fornecedores.oid_Pessoa OR (SELECT count(*) " +
                           "                                                        FROM Fornecedores_Produtos" +
                           "                                                        WHERE Fornecedores_Produtos.oid_Pessoa = Fornecedores.oid_pessoa" +
                           "                                                          AND Fornecedores_Produtos.oid_Produto = Produtos.oid_Produto) > 0) " +
                           " AND Fornecedores.oid_Pessoa = "+util.getSQLString(ed.getOid_pessoa());
  	    		//*** Caso seja passado Distribuidor
      		  	if (util.doValida(ed.getOid_pessoa_distribuidor())){
      		  	    query += "  AND estoques_clientes.oid_produto_cliente = produtos_clientes.oid_produto_cliente" +
      		  	    		 "  AND produtos_clientes.oid_pessoa = '" + ed.getOid_pessoa_distribuidor() +"'";
      		  	}
      		  	//*** Caso seja passado a localização (Deposito, Armazem)
      		  	if (ed.getOid_deposito() > 0)
      		  	    query += "  AND localizacoes.oid_deposito = "+ed.getOid_deposito();
      		  	//*** Caso seja passado o Tipo de Estoque
      		  	if (ed.getOid_tipo_estoque() > 0)
      		  	    query += "  AND estoques_clientes.oid_tipo_estoque = " + ed.getOid_tipo_estoque();

      		  	//*** Ordenação
      		  	if (ed.getLayout().equals(ProdutoRelED.MODELO_ESTOQUE_1))
      		  	    query += " ORDER BY produtos.nm_produto, tipos_estoques.nm_tipo_estoque, Depositos.nm_Deposito";
      		  	else if (ed.getLayout().equals(ProdutoRelED.MODELO_ESTOQUE_2))
      		  	    query += " ORDER BY tipos_estoques.nm_tipo_estoque, produtos.nm_produto, Depositos.nm_Deposito";
      		  	else if (ed.getLayout().equals(ProdutoRelED.MODELO_ESTOQUE_3))
    		  	    query += " ORDER BY Depositos.nm_Deposito, tipos_estoques.nm_tipo_estoque, produtos.nm_produto";

	    ResultSet res = executasql.executarConsulta(query);
	    while (res.next())
        {
        	RelatorioED edRel = new RelatorioED();
	        // Produto
      		edRel.setCd_produto(res.getString("cd_produto"));
      		edRel.setNm_produto(res.getString("nm_produto"));
      		// Tipo de Estoque
      		edRel.setCd_tipo_estoque(res.getString("cd_tipo_estoque"));
    		edRel.setNm_tipo_estoque(res.getString("nm_tipo_estoque"));
    		// Localização
    		edRel.setNm_deposito(res.getString("nm_deposito"));
    		edRel.setNm_rua(JavaUtil.LFill(res.getString("nm_rua"),2,true));
    		edRel.setNr_endereco(res.getString("nr_endereco"));
    		edRel.setNr_apartamento(JavaUtil.LFill(res.getInt("nr_apartamento"),3,true));
    		edRel.setNr_quantidade(res.getDouble("nr_quantidade"));

    		lista.add(edRel);
	    }
	    //*** Layout é setado no Bean
	    new ProdutoRL().geraRelProdutoEstoque(lista, ed, response);
  	}

  	//*** Produtos Comprados
  	public void RelProdutosComprados(HttpServletResponse response, RelatorioED ed) throws Exception {

  	    FormataDataBean dataFormatada = new FormataDataBean();
  	    double Valor_Total = 0;
  	    int NR_Nota_Fiscal = 0;
  	    ArrayList lista = new ArrayList();
  	    String query = null;

  	    query = " SELECT DISTINCT Notas_Fiscais.NR_Nota_Fiscal" +
  	    		"		,Notas_Fiscais.VL_Nota_Fiscal" +
  	    		"		,Notas_Fiscais.DT_Emissao" +
  	    		" 	    ,Notas_Fiscais.DT_Entrada" +
  	    		"  	    ,Pessoas.NM_Razao_Social" +
  	    		" 	    ,Produtos.CD_Produto" +
  	    		" 	    ,Produtos.CD_Fornecedor" +
  	    		" 	    ,Produtos.NM_Produto" +
  	    		" 	    ,Produtos.CD_Produto" +
  	    		" 	    ,Itens_Notas_Fiscais.NR_QT_Atendido" +
  	    		" 	    ,Itens_Notas_Fiscais.VL_Unitario" +
  	    		" FROM Notas_Fiscais, Itens_Notas_Fiscais, Produtos, Pessoas" +
  	    		" WHERE Notas_Fiscais.oid_nota_fiscal = Itens_Notas_Fiscais.oid_nota_fiscal" +
  	    		"   AND Itens_Notas_Fiscais.oid_produto = Produtos.oid_produto" +
  	    		"   AND Produtos.oid_produto = produtos_clientes.oid_produto" +
  	    		"   AND (Notas_Fiscais.dm_situacao = 'F' or Notas_Fiscais.dm_finalizado = 'F')"; // Somente NF Finalizadas
  	    // Se informado Produto
  	    if (ed.getOid_produto() > 0)
  	        query += "   AND Produtos.oid_produto = "+ed.getOid_produto();
  	    // Se informado Fornecedor
  	    if (util.doValida(ed.getOid_pessoa()))
  	        query += " AND (Produtos.oid_pessoa = Fornecedores.oid_Pessoa OR (SELECT count(*) " +
                     "                                                        FROM Fornecedores_Produtos" +
                     "                                                        WHERE Fornecedores_Produtos.oid_Pessoa = Fornecedores.oid_pessoa" +
                     "                                                          AND Fornecedores_Produtos.oid_Produto = Produtos.oid_Produto) > 0) " +
                     " AND Fornecedores.oid_Pessoa = "+util.getSQLString(ed.getOid_pessoa());
  	    // Se informado Distrubuidora
  	    if (util.doValida(ed.getOid_pessoa_distribuidor()))
	        query += "   AND Produtos_Clientes.oid_pessoa = '"+ed.getOid_pessoa_distribuidor()+"'";
  	    // Se informado DT_Emissao
  	    if (util.doValida(ed.getDt_emissao_inicial()) && util.doValida(ed.getDt_emissao_final()))
  	        query += "   AND Notas_Fiscais.dt_emissao between '"+ed.getDt_emissao_inicial()+"' and '"+ed.getDt_emissao_final()+"'";
  	    // Se informado DT_Entrada
  	    if (util.doValida(ed.getDt_entrada_inicial()) && util.doValida(ed.getDt_entrada_final()))
	        query += "   AND Notas_Fiscais.dt_entrada between '"+ed.getDt_entrada_inicial()+"' and '"+ed.getDt_entrada_final()+"'";
  	    //*** Ordenação
  	    query += " ORDER BY Notas_Fiscais.nr_nota_fiscal, Produtos.nm_produto";

	    ResultSet res = executasql.executarConsulta(query);
	    while (res.next())
        {
        	RelatorioED edRel = new RelatorioED();
        	//*** Adiciona Valor total da NF
        	if (NR_Nota_Fiscal == 0 || NR_Nota_Fiscal != res.getInt("nr_nota_fiscal")){
        	    Valor_Total += res.getDouble("vl_nota_fiscal");
        	}

        	// Nota Fiscal
        	edRel.setNr_nota_fiscal(res.getInt("nr_nota_fiscal"));
        	edRel.setNm_razao_social(res.getString("Nm_razao_social"));
        	edRel.setDt_emissao(dataFormatada.getDT_FormataData(res.getString("Dt_emissao")));
        	edRel.setDt_entrada(dataFormatada.getDT_FormataData(res.getString("Dt_entrada")));
	        // Produto
      		edRel.setCd_produto(res.getString("cd_produto"));
      		edRel.setCd_fornecedor(res.getString("cd_fornecedor"));
      		edRel.setNm_produto(res.getString("nm_produto"));
      		edRel.setNr_qt_atendido(res.getDouble("nr_qt_atendido"));
      		edRel.setVl_item(res.getDouble("vl_unitario"));
      		edRel.setVl_nota_fiscal(res.getDouble("vl_nota_fiscal"));

      		//*** Atrubui variavel de comparação
      		NR_Nota_Fiscal = res.getInt("nr_nota_fiscal");

      		lista.add(edRel);
	    }
	    ed.setValor_total(Valor_Total);

	    //*** Layout é setado no Bean
	    new ProdutoRL().geraRelProdutoComprados(lista, ed, response);
  	}

  	public byte[] geraRelatorioItemXCliente(ProdutoED ed) throws Excecoes {

        String sql = null;
        try {

            sql = "select ";
            sql += "pessoas.nm_razao_social, ";
            sql += "depositos.nm_deposito, ";
            sql += "produtos.nm_produto, ";
            sql += "produtos.cd_produto, ";
            sql += "estoques_clientes.nr_quantidade, ";
            sql += "produtos_clientes.vl_produto ";
            sql += "FROM ";
            sql += "produtos, produtos_clientes, estoques_clientes, localizacoes, pessoas, depositos ";
            sql += "WHERE  ";
            sql += "localizacoes.oid_deposito = " + ed.getOID_Deposito();
            sql += "AND  ";
            sql += "estoques_clientes.oid_localizacao = localizacoes.oid_localizacao ";
            sql += "AND  ";
            sql += "produtos_clientes.oid_produto_cliente = estoques_clientes.oid_produto_cliente ";
            sql += "AND  ";
            sql += "produtos_clientes.oid_pessoa = '" + ed.getOid_Pessoa() + "' ";
            sql += "AND  ";
            sql += "produtos.oid_produto = produtos_clientes.oid_produto ";
            sql += "AND  ";
            sql += "pessoas.oid_pessoa = '" + ed.getOid_Pessoa() + "' ";
            sql += "AND  ";
            sql += "depositos.oid_deposito = " + ed.getOID_Deposito();

  		  	if (ed.getOid_Tipo_Estoque() > 0)
  		  	    sql += "  AND estoques_clientes.oid_tipo_estoque = " + ed.getOid_Tipo_Estoque();
  		  		sql += "  AND estoques_clientes.nr_quantidade > 0 ";
            sql += " order by produtos.cd_produto ";

            ResultSet res = this.executasql.executarConsulta(sql);
            return new ProdutoRL().geraRelatorioItemXCliente(res);
        } catch (Excecoes e) {
            throw e;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "geraRelatorioItemXCliente()");
        }
    }

    public byte[] geraRelatorioItemXLocalizacao(ProdutoED ed) throws Excecoes {

        String sql = null;
        try {

            sql = "select ";
            sql += "pessoas.nm_razao_social, ";
            sql += "depositos.nm_deposito, ";
            sql += "produtos.nm_produto, ";
            sql += "produtos.cd_produto, ";
            sql += "produtos_clientes.vl_produto, ";
            sql += "estoques_clientes.nr_quantidade, ";
            sql += "localizacoes.oid_localizacao ";
            sql += "FROM ";
            sql += "produtos, produtos_clientes, estoques_clientes, localizacoes, pessoas, depositos ";
            sql += "WHERE  ";
            sql += "produtos_clientes.oid_produto_cliente = '" + ed.getOID_Produto() + ed.getOid_Pessoa() + "' ";
            sql += "AND  ";
            sql += "localizacoes.oid_deposito = " + ed.getOID_Deposito();
            sql += "AND  ";
            sql += "estoques_clientes.oid_localizacao = localizacoes.oid_localizacao ";
            sql += "AND  ";
            sql += "produtos_clientes.oid_produto_cliente = estoques_clientes.oid_produto_cliente ";
            sql += "AND  ";
            sql += "produtos_clientes.oid_pessoa = '" + ed.getOid_Pessoa() + "' ";
            sql += "AND  ";
            sql += "produtos.oid_produto = produtos_clientes.oid_produto ";
            sql += "AND  ";
            sql += "pessoas.oid_pessoa = '" + ed.getOid_Pessoa() + "' ";
            sql += "AND  ";
            sql += "depositos.oid_deposito = " + ed.getOID_Deposito();
            sql += " ORDER BY produtos.cd_produto ";

            ResultSet res = this.executasql.executarConsulta(sql);
            return new ProdutoRL().geraRelatorioItemXLocalizacao(res);
        } catch (Excecoes e) {
            throw e;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "geraRelatorioItemXLocalizacao()");
        }
    }
}