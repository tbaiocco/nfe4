package com.master.bd;

/**
 * Título: WMS_Item_Produto_ClienteBD
 * Descrição: Itens da Nota Fiscal - BD
 * Data da criação: 11/2003
 * Atualizado em: 03/2004
 * Empresa: ÊxitoLogística Mastercom
 * Autor: Carlos Eduardo de Holleben
*/

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.WMS_Item_Produto_ClienteED;
import com.master.ed.WMS_Nota_FiscalED;
import com.master.ed.WMS_Nota_Fiscal_DevolucaoED;
import com.master.iu.wms008Bean;
import com.master.rn.WMS_Item_Produto_ClienteRN;
import com.master.rn.WMS_Nota_FiscalRN;
import com.master.root.FormataDataBean;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.FormataValor;
import com.master.util.bd.ExecutaSQL;
import com.master.ed.WMS_EstoqueED;
import com.master.bd.WMS_EstoqueBD;

public class WMS_Item_Produto_ClienteBD {

  private ExecutaSQL executasql;

  public WMS_Item_Produto_ClienteBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public void inclui(WMS_Item_Produto_ClienteED ed) throws Excecoes{

    String sql = null;
    int valOid = 0;
    String chave = null;

    try{
    	sql = "Select " + 
    		  "max(nr_item_produto_cliente) as result " +
    		  "from " + 
    		  "Itens_Produtos_Clientes " +
    		  "where " + 
    		  "oid_nota_fiscal = '" + ed.getOid_Nota_Fiscal() + "'";
    	ResultSet rs = executasql.executarConsulta(sql);
    	while( rs.next() )
    		valOid = rs.getInt("result");
    	
    	chave = ed.getOid_Produto_Cliente() + ed.getOid_Nota_Fiscal() + ++valOid;
    	sql = "Insert " + 
    		  "into Itens_Produtos_Clientes (" +
    		  "oid_Item_Produto_Cliente, " +
    		  "oid_Tipo_Estoque, " +
    		  "oid_Produto_Cliente, "+ 
    		  "oid_Nota_Fiscal, "+ 
    		  "oid_Localizacao, "+
    		  "vl_Unitario, " +
    		  "vl_Ipi, "+
    		  "vl_Produto, "+ 
    		  "nr_Quantidade_Movimento, "+
    		  "nr_Quantidade_Devolucao, "+ 
    		  "nr_item_produto_cliente, "+
    		  "dm_devolvido "+
    		  ") values ('" + 
    		  chave + "'," +
    		  ed.getOid_Tipo_Estoque() + ",'" + 
    		  ed.getOid_Produto_Cliente() + "','" + 
    		  ed.getOid_Nota_Fiscal() + "'," +
    		  ed.getOid_Localizacao() + "," +
    		  ed.getVl_Unitario() + "," +
    		  ed.getVl_Ipi() + "," +
    		  ed.getVl_Produto() + "," + 
    		  ed.getNr_Quantidade_Movimento() + 
    		  ",0," + 
    		  valOid + "," +
    		  "' '" +
    		  ")";
    	
    	executasql.executarUpdate(sql);     
    	
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());      
      excecoes.setMensagem("Erro ao incluir");     
      excecoes.setMetodo("inclui");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void altera(WMS_Item_Produto_ClienteED ed) throws Excecoes{

    String sql = null;

    try{
      sql  = " update Itens_Produtos_Clientes set "+
      " OID_Tipo_Estoque= " + ed.getOid_Tipo_Estoque() +
      ", OID_Nota_Fiscal= '" + ed.getOid_Nota_Fiscal() +"'"+
      ", OID_Produto_Cliente= '" + ed.getOid_Produto_Cliente() +"'"+
      ", VL_Produto= '" + ed.getVl_Produto() +"'"+
      ", NR_Quantidade_Movimento = " + ed.getNr_Quantidade_Movimento();

      sql += " where oid_Item_Produto_Cliente = '" + ed.getOid_Item_Produto_Cliente() + "'";

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar");
      excecoes.setMetodo("alterar");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void alteraPelaMovimentacao(WMS_Item_Produto_ClienteED ed) throws Excecoes{

    String sql = null;

    try{
      sql  = " update Itens_Produtos_Clientes set ";
      sql += " NR_Quantidade_Devolucao = "+ ed.getNr_Quantidade_Devolucao();
      sql += " where oid_Item_Produto_Cliente = '" + ed.getOid_Item_Produto_Cliente() + "'";

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao alterar");
      excecoes.setMetodo("alteraPelaMovimentacao");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void deleta(WMS_Item_Produto_ClienteED ed) throws Excecoes{

    String sql = null;
    String DM_Impresso = null;

    try{

      sql = "delete from Itens_Produtos_Clientes WHERE oid_Item_Produto_Cliente = ";
      sql += "('" + ed.getOid_Item_Produto_Cliente() + "')";

      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMetodo("excluir");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public ArrayList lista(WMS_Item_Produto_ClienteED edV, String orderby)throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();
    FormataDataBean DataFormatada = new FormataDataBean();
    String order_by = " ORDER BY itens_produtos_clientes.oid_Item_Produto_Cliente";
    try{
        
    	sql= "select "+ 
       		 "itens_produtos_clientes.*, "+
       		 "produtos_clientes.oid_produto, "+
       		 "produtos_clientes.oid_pessoa, "+
       		 "produtos.nm_produto, "+
       		 "produtos.cd_produto, "+
       		 "pessoas.nm_razao_social, "+
       		 "pessoas.nr_cnpj_cpf, "+	   
       		 "tipos_estoques.oid_tipo_estoque, "+
       		 "tipos_estoques.cd_tipo_estoque, "+
       		 "tipos_estoques.nm_tipo_estoque, " +
       		 "localizacoes.cd_localizacao " +
       		 "FROM "+
       		 "itens_produtos_clientes, "+ 
       		 "produtos_clientes, "+ 
       		 "produtos, "+
       		 "pessoas, "+
       		 "localizacoes, "+
       		 "tipos_estoques "+
       		 "WHERE "+
       		 "produtos_clientes.oid_produto_cliente = itens_produtos_clientes.oid_produto_cliente and "+
       		 "produtos.oid_produto = produtos_clientes.oid_produto and "+
       		 "pessoas.oid_pessoa = produtos_clientes.oid_pessoa and "+
       		 "localizacoes.oid_localizacao = itens_produtos_clientes.oid_localizacao and " +
       		 "itens_produtos_clientes.oid_tipo_estoque = tipos_estoques.oid_tipo_estoque and "+      
       		 "itens_produtos_clientes.oid_Nota_Fiscal LIKE '"+edV.getOid_Nota_Fiscal()+"%'";        
       
		if( !order_by.equals( orderby ) )
			sql += orderby;
		else
			sql += order_by;
		ResultSet res = null;


      res = this.executasql.executarConsulta(sql);

      //popula
      while (res.next()){
    	  WMS_Item_Produto_ClienteED ed = new WMS_Item_Produto_ClienteED();
    	  
    	  ed.setNM_Tipo_Estoque( res.getString("nm_tipo_estoque") );
    	  ed.setNM_Produto( res.getString("nm_produto") );
    	  ed.setVl_Produto( res.getDouble("VL_Produto") );    
    	  ed.setVl_Ipi( res.getDouble("VL_Ipi"));
    	  ed.setNM_Razao_Social( res.getString("NM_Razao_Social") );    
    	  ed.setNr_Quantidade_Movimento( res.getDouble("nr_quantidade_movimento") );      
    	  ed.setOid_Item_Produto_Cliente( res.getString("OID_Item_Produto_Cliente") );   
    	  ed.setOid_Nota_Fiscal( res.getString("oid_Nota_Fiscal") ); 
    	  ed.setOid_Produto_Cliente( res.getString("OID_Produto_Cliente") );  	
    	  ed.setOid_Tipo_Estoque( res.getInt("OID_Tipo_Estoque") ); 
    	  ed.setNr_Quantidade_Movimento( res.getDouble("NR_Quantidade_Movimento") );
    	  ed.setCD_Produto( res.getString("cd_produto") );
    	  ed.setNR_CNPJ_CPF( res.getString("NR_CNPJ_CPF") );
    	  ed.setCD_Tipo_Estoque( res.getString("CD_Tipo_Estoque") );
    	  ed.setOID_Produto( res.getString("OID_Produto") );
    	  ed.setOID_Pessoa( res.getString("OID_Pessoa") );
    	  ed.setCD_Localizacao( res.getString("cd_Localizacao") );
    	  ed.setOid_Localizacao( res.getInt("oid_Localizacao") ); 
    	  ed.setVl_Ipi_TX(FormataValor.formataValorBT(res.getDouble("VL_Ipi"),2));
    	  ed.setVl_Produto_TX(FormataValor.formataValorBT(res.getDouble("VL_Produto"), 2));
    	  ed.setNr_Quantidade_Movimento_TX(FormataValor.formataValorBT(res.getDouble("nr_quantidade_movimento"), 3));
    	  
    	  list.add(ed);
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao selecionar Notas Fiscais");
      excecoes.setMetodo("Seleção");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return list;
  }

  public void atualizaEstoque(String oidNf)throws Excecoes{
	  
	  String sql = null;
	  try{
		  sql= "Select "+ 
		  "* "+
		  "From "+
		  "itens_produtos_clientes "+ 
		  "WHERE "+
		  "oid_Nota_Fiscal LIKE '"+ oidNf +"'";        
		  ResultSet res = null;
		  res = this.executasql.executarConsulta(sql);
		  //popula
		  while (res.next()){
			  WMS_EstoqueED edE = new WMS_EstoqueED();
			  //ed.setVl_Produto( res.getDouble("VL_Produto") );    
			  //ed.setVl_Ipi( res.getDouble("VL_Ipi"));
			  edE.setOID_Localizacao(res.getInt("OID_Localizacao"));
			  edE.setOID_Tipo_Estoque( res.getInt("OID_Tipo_Estoque") );
			  edE.setOID_Produto_Cliente( res.getString("OID_Produto_Cliente") );
			  edE.setNR_Quantidade(res.getDouble("nr_quantidade_movimento"));
			  WMS_EstoqueBD bdE = new WMS_EstoqueBD(this.executasql);
			  bdE.inclui(edE,true);
		  }
	  }
	  catch(Exception exc){
		  Excecoes excecoes = new Excecoes();
		  excecoes.setClasse(this.getClass().getName());
		  excecoes.setMensagem("Erro ao selecionar Notas Fiscais");
		  excecoes.setMetodo("Seleção");
		  excecoes.setExc(exc);
		  throw excecoes;
	  }
  }

  
  public WMS_Item_Produto_ClienteED getByRecord(WMS_Item_Produto_ClienteED ed)throws Excecoes{

    String sql = null;

    WMS_Item_Produto_ClienteED edVolta = new WMS_Item_Produto_ClienteED();

    try{
      sql =  "SELECT "+
             "Itens_Produtos_Clientes.*, "+ 
             "Notas_Fiscais_wms.OID_Nota_Fiscal "+ 
             "from "+ 
             "Itens_Produtos_Clientes, "+ 
             "Notas_Fiscais_wms ";
      sql += "WHERE "+ 
      	     "Notas_Fiscais_wms.oid_nota_fiscal = Itens_Produtos_Clientes.OID_Nota_Fiscal ";

      if (String.valueOf(ed.getOid_Item_Produto_Cliente()) != null &&
          !String.valueOf(ed.getOid_Item_Produto_Cliente()).equals("") &&
          !String.valueOf(ed.getOid_Item_Produto_Cliente()).equals("null")){
        sql += " AND Itens_Produtos_Clientes.OID_Item_Produto_Cliente = '" + ed.getOid_Item_Produto_Cliente() + "'";
      }

      if (String.valueOf(ed.getOid_Nota_Fiscal()) != null &&
          !String.valueOf(ed.getOid_Nota_Fiscal()).equals("") &&
          !String.valueOf(ed.getOid_Nota_Fiscal()).equals("null")){
        sql += " AND Itens_Produtos_Clientes.OID_Nota_Fiscal = '" + ed.getOid_Nota_Fiscal() + "'";
      }

      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);
      while (res.next()){
        edVolta.setOid_Item_Produto_Cliente(res.getString( "OID_Item_Produto_Cliente" ));
        edVolta.setOid_Nota_Fiscal(res.getString("OID_Nota_Fiscal"));
        edVolta.setOid_Produto_Cliente(res.getString("OID_Produto_Cliente"));
        edVolta.setOid_Tipo_Estoque(res.getInt("OID_Tipo_Estoque"));
        edVolta.setVl_Produto(res.getDouble("VL_Produto"));
        edVolta.setNr_Quantidade_Movimento(res.getDouble("NR_Quantidade_Movimento"));
        edVolta.setNr_Quantidade_Devolucao(res.getDouble("NR_Quantidade_Devolucao"));
        edVolta.setDm_Devolvido(res.getString("dm_Devolvido"));
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao selecionar");
      excecoes.setMetodo("selecionar");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return edVolta;
  }

  public boolean itemJaIncluso( String oid_produto_cliente, String oid_nota_fiscal )throws Excecoes{

    String sql = null;

    boolean bol = false;

    try{
      sql =  " SELECT oid_produto_cliente from itens_produtos_clientes where oid_produto_cliente = '"+oid_produto_cliente+"' AND oid_nota_fiscal ='"+oid_nota_fiscal+"'";
      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);
      while (res.next()){
        bol = true;
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao selecionar");
      excecoes.setMetodo("selecionar");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return bol;
  }

  public WMS_Item_Produto_ClienteED Reverter( String oid )throws Excecoes{

    String strselect = null;

    WMS_Item_Produto_ClienteED edVolta = new WMS_Item_Produto_ClienteED();

    try{
       strselect = "select itens_produtos_clientes.*, "+
                   "produtos_clientes.oid_produto AS OIDPROD, "+
                   "produtos_clientes.oid_pessoa AS OIDPESSOA, "+
                   "produtos.nm_produto AS NM, "+
                   "produtos.cd_produto AS PRODUTO, "+
                   "pessoas.nm_razao_social AS NMPESSOA, "+
                   "pessoas.nr_cnpj_cpf AS CNPJ, "+
                   "tipos_estoques.oid_tipo_estoque AS OIDEST, "+
                   "tipos_estoques.cd_tipo_estoque AS CODEST, "+
                   "tipos_estoques.nm_tipo_estoque AS NMEST";
       strselect += "     FROM itens_produtos_clientes, produtos_clientes, produtos, pessoas, tipos_estoques ";
       strselect += " WHERE produtos_clientes.oid_produto_cliente = itens_produtos_clientes.oid_produto_cliente ";
       strselect += " AND produtos.oid_produto = produtos_clientes.oid_produto ";
       strselect += " AND pessoas.oid_pessoa = produtos_clientes.oid_pessoa ";
       strselect += " AND itens_produtos_clientes.oid_tipo_estoque = tipos_estoques.oid_tipo_estoque ";
       strselect += " AND notas_fiscais_wms.oid_nota_fiscal = itens_produtos_clientes.oid_nota_fiscal ";
       strselect += " AND itens_produtos_clientes.oid_item_produto_cliente = '"+oid+"'";


      ResultSet res = null;
      res = this.executasql.executarConsulta(strselect);
      while (res.next()){
        edVolta.setVl_Produto(res.getDouble("VL_Produto"));
        edVolta.setNr_Quantidade_Movimento(res.getInt("NR_Quantidade_Movimento"));
        edVolta.setCD_Tipo_Estoque( res.getString( "CODEST" ) );
        edVolta.setNM_Tipo_Estoque( res.getString( "NMEST" ) );
        edVolta.setOid_Tipo_Estoque( res.getInt( "OIDEST" ) );
        edVolta.setCD_Produto( res.getString( "PRODUTO" ) );
        edVolta.setNM_Produto( res.getString( "NM" ) );
        edVolta.setOID_Produto(res.getString("OIDPROD"));
        edVolta.setOID_Pessoa( res.getString( "OIDPESSOA" ) );
        edVolta.setNM_Razao_Social( res.getString( "NMPESSOA" ) );
        edVolta.setNR_CNPJ_CPF( res.getString( "CNPJ" ) );
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao selecionar");
      excecoes.setMetodo("selecionar");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return edVolta;
  }
  
  public String verificaItens(WMS_Item_Produto_ClienteED edI, WMS_Nota_FiscalED edN) throws Excecoes{
    int nr_qt_saiu = 0;
    double nr_qt_tirar = 0;
    int nr_qt_disponivel = 0;
    int valOid = 0;   
    double nr_qt_devolucao = 0;   
    String novoOid = "";
    String sql = null;   
    ResultSet rs = null, res = null;
    String retorno = "0";
    boolean bol = false;
    String oid = "", oid2 = "";
        
    try{     
       wms008Bean wms = new wms008Bean();        
       WMS_Nota_FiscalRN RN = new WMS_Nota_FiscalRN(); 
       WMS_Nota_FiscalED ED = new WMS_Nota_FiscalED(); 
       WMS_Nota_FiscalED ed2 = new WMS_Nota_FiscalED();
       WMS_Item_Produto_ClienteRN itemRN = new WMS_Item_Produto_ClienteRN();
       WMS_Item_Produto_ClienteED itemED = new WMS_Item_Produto_ClienteED();
       WMS_Nota_Fiscal_DevolucaoED devED = new WMS_Nota_Fiscal_DevolucaoED();
       
       nr_qt_tirar = edI.getNr_Quantidade_Movimento();
   
       sql = "SELECT notas_fiscais_wms.oid_nota_fiscal, itens_produtos_clientes.nr_quantidade_movimento, itens_produtos_clientes.nr_quantidade_devolucao, ";
       sql+= "itens_produtos_clientes.oid_item_produto_cliente,itens_produtos_clientes.oid_produto_cliente, notas_fiscais_wms.nr_nota_fiscal, itens_produtos_clientes.vl_produto, itens_produtos_clientes.oid_tipo_estoque ";
       sql+= "FROM notas_fiscais_wms,itens_produtos_clientes ";
       sql+= "WHERE notas_fiscais_wms.dm_tipo_nota_fiscal ='E' ";     
       sql+= "AND notas_fiscais_wms.dm_impresso is null ";  
       sql+= "AND itens_produtos_clientes.oid_nota_fiscal = notas_fiscais_wms.oid_nota_fiscal ";
       sql+= "AND ( itens_produtos_clientes.nr_quantidade_movimento > itens_produtos_clientes.nr_quantidade_devolucao OR itens_produtos_clientes.nr_quantidade_devolucao is null ) ";
       sql+= "AND itens_produtos_clientes.oid_produto_cliente = '"+ edI.getOid_Produto_Cliente() +"'";
       
       if( edN.getOID_Unidade() != 0 )
           sql+= " AND notas_fiscais_wms.oid_unidade_emissora = " + edN.getOID_Unidade();
       
       if( edN.getOid_pessoa() != null && !edN.getOid_pessoa().equals( "" ) )
           sql+= " AND notas_fiscais_wms.oid_pessoa = '" + edN.getOid_pessoa() + "'";   
       
       sql+= " ORDER BY notas_fiscais_wms.dt_emissao "; 

       res = executasql.executarConsulta(sql);
       while( res.next() && nr_qt_saiu < nr_qt_tirar ){  
           
                    if( !oid.equals( oid2 ) )
                        oid = oid2;
                    else oid = edN.getOid_nota_fiscal();        
                          
                    nr_qt_devolucao = 0;
                                       
                    WMS_Nota_FiscalED ed = new WMS_Nota_FiscalED();						

                    nr_qt_disponivel = res.getInt( "nr_quantidade_movimento" ) - res.getInt( "nr_quantidade_devolucao" );	

                    if ( ( nr_qt_tirar - nr_qt_saiu ) > nr_qt_disponivel ){
                       nr_qt_devolucao = nr_qt_disponivel;                   
                    }
                    else{
                       nr_qt_devolucao = nr_qt_tirar - nr_qt_saiu;
                    }					

                    nr_qt_saiu += nr_qt_devolucao;	
 
                    // 1) GRAVA qtde_devolucao na nota_fiscal de entrada                       
                    itemED.setOid_Nota_Fiscal( res.getString( "oid_Nota_Fiscal" ) );
                    itemED.setNr_Quantidade_Devolucao( nr_qt_devolucao );
                    itemED.setOid_Item_Produto_Cliente( res.getString( "oid_item_produto_cliente" ) );
                    RN.atualiza_Quantidade_Devolucao( itemED );            
          
                    // 3) INCLUI (SE AINDA NAO FOI) a nota_fiscal delta                  
                                        
                    novoOid = itemRN.isInclui(oid);
                    if( novoOid.equals( "null" ) ){  
                        
                        ed.setOid_pessoa( edN.getOid_pessoa() );
                        
                        sql = " select pessoas.oid_pessoa AS REMETENTE from unidades, pessoas ";
                        sql+= " where unidades.oid_unidade = "+edN.getOID_Unidade();         
                        sql+= " and pessoas.oid_pessoa = unidades.oid_pessoa";
                        rs = executasql.executarConsulta( sql );
                        while(rs.next()){
                            ed.setOid_pessoa( rs.getString( "REMETENTE" ) );                            
                        } 
                                            
                      ed.setOid_pessoa_destinatario( "10948651000161" );              
                      ed.setOid_natureza_operacao( 156 );          
                      ed.setOID_Unidade_Fiscal( 1 );
                      ed.setOID_Unidade_Contabil( 1 );
                      ed.setOID_Unidade( edN.getOID_Unidade() );
                      ed.setOID_Empresa( 2 );                   
                      ed.setDt_entrada( Data.getDataDMY() );
                      ed.setHr_entrada( Data.getHoraHM() );
                      ed.setOid_pessoa_transportador( "33333333333" );
                      ed.setVL_Base_Calculo_ICMS( 0 );
                      ed.setVL_Base_Calculo_ICMS_Substituicao( 0 ); 
                      ed.setVL_ICMS_Substituicao( 0 );
                      ed.setVL_Total_Produtos( 0 );   
                      ed.setOid_modelo_nota_fiscal( 0 );
                      ed.setVl_total_frete( 0 );
                      ed.setVl_icms( 0 );
                      ed.setVL_Servico( 0 );
                      ed.setVl_inss( 0 );
                      ed.setVL_PIS( 0 );
                      ed.setVL_CSLL( 0 );
                      ed.setVL_Cofins( 0 );
                      ed.setVl_irrf( 0 );
                      ed.setVl_ipi( 0 );
                      ed.setVl_isqn( 0 );
                      ed.setVl_total_seguro( 0 );
                      ed.setVl_total_despesas( 0 );
                      ed.setVl_nota_fiscal( 0 );
                      ed.setVl_liquido_nota_fiscal( 0 );
                      ed.setDm_tipo_nota_fiscal( "S" );                
                      ed.setDm_observacao( "ICMS NÃO INCIDENTE CFM LIVRO I ART 11 INC XI DECRETO 37699/97 REF NF: "+ res.getString( "nr_nota_fiscal" ) );
                      ed.setVl_descontos( 0 );
                      ed.setDM_Gerado( edN.getDM_Gerado() );

                      ed2 = RN.inclui( ed );      
                      
                      bol = true;

                      itemED.setOid_Nota_Fiscal( ed2.getOid_nota_fiscal() );
                      
                      oid2 = ed2.getOid_nota_fiscal();
                    }else{           
                      itemED.setOid_Nota_Fiscal( novoOid );                      
                      RN.concatena_Observacao( novoOid, res.getString( "nr_nota_fiscal" ) );                    
                    }  
                  
                    // 4) INCLUI registro na itens_produtos_clientes da nota fiscal de saida/delta
                   
                    itemED.setOid_Tipo_Estoque( res.getInt( "OID_Tipo_Estoque" ) );
                   
                    itemED.setOid_Produto_Cliente( res.getString( "oid_Produto_Cliente" ) );
                  
                    itemED.setVl_Produto( res.getDouble( "vl_produto" ) );

                    itemED.setNr_Quantidade_Movimento( nr_qt_devolucao );  
                  
                    itemRN.inclui( itemED );

                    // 5) INCLUI registro na nota_fiscal_devolucao
                    sql = "select max(oid_nota_fiscal_devolucao) as result from notas_fiscais_devolucao ";              
                    rs = executasql.executarConsulta( sql );
                    while(rs.next()){
                        valOid = rs.getInt( "result" );                            
                    }  
              /**
                    devED.setOid_Nota_Fiscal_Devolucao( ++valOid );             
                    devED.setOid_Nota_Fiscal_Entrada_Cliente( res.getString( "oid_nota_fiscal" ) );  
                    devED.setOid_Nota_Fiscal_Saida( itemED.getOid_Nota_Fiscal() );                          
                    devED.setOID_Item_Entrada_Cliente( res.getString( "oid_item_produto_cliente" ) );                  
                    devED.setNR_Quantidade( nr_qt_devolucao );                   
                **/
                    RN.insere_Nota_Devolucao( devED );
                   
                    ED.setOid_nota_fiscal( itemED.getOid_Nota_Fiscal() );
         
                    wms.atualiza_Total_Nota(ED);
                    wms.atualiza_Total_Produtos(ED);
                    wms.atualiza_Quantidade_Itens(ED);
              
          
       }    
                      
       retorno = String.valueOf( nr_qt_saiu )+"-"+bol;
       
       return retorno;
             
     }
     catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem(exc.getMessage());
      excecoes.setMetodo("verificaItens()");
      excecoes.setExc(exc);
      throw excecoes;
    }  
  }
  
  public String isInclui(String oid) throws Excecoes{
    String sql = null;
    ResultSet res = null, rs = null;
    int cont = 0;
    boolean isInclui = true;   
    String volta = "null";
    try{    
              
       sql = "SELECT * FROM notas_fiscais_wms ";
       sql+= "WHERE oid_nota_fiscal = '"+oid+"'";       

       res = executasql.executarConsulta(sql);
       while(res.next()){                     
            sql = "select max(nr_item_produto_cliente) as result from itens_produtos_clientes ";
            sql+= " where oid_nota_fiscal = '"+ res.getString( "oid_nota_fiscal" ) +"'";
            rs = executasql.executarConsulta( sql );
            while(rs.next()){
                if( rs.getInt( "result" ) < 13 ){
                    isInclui = false;
                    break;
                }           
            }           
            
            if( !isInclui ){                
                volta = res.getString( "oid_nota_fiscal" );                
                break;
            }
       }
       
     }
     catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem(exc.getMessage());
      excecoes.setMetodo("isInclui()");
      excecoes.setExc(exc);
      throw excecoes;
    }        
    return volta;
  }
  
  public void atualiza_Notas(WMS_Item_Produto_ClienteED edV) throws Excecoes{
    String sql = null;
    ResultSet res = null, rs = null;
    int i = 0;
    String oid_produto_cliente = "";
   
    try{         
       sql = "SELECT * FROM notas_fiscais_devolucao, itens_produtos_clientes ";       
       sql+= "WHERE notas_fiscais_devolucao.oid_nota_fiscal_saida = '"+ edV.getOid_Nota_Fiscal() +"' ";  
       sql+= "AND itens_produtos_clientes.oid_item_produto_cliente = '"+edV.getOid_Item_Produto_Cliente()+"' ";
       sql+= "AND itens_produtos_clientes.oid_nota_fiscal = notas_fiscais_devolucao.oid_nota_fiscal_saida";

       res = executasql.executarConsulta(sql);
       while(res.next()){                       
                    
            if( res.getString( "oid_nota_fiscal_entrada_cliente" ) != null && !res.getString( "oid_nota_fiscal_entrada_cliente" ).equals( "" ) ){          
                sql = "UPDATE itens_produtos_clientes SET ";
                sql+= "nr_quantidade_devolucao = ( nr_quantidade_devolucao - " + res.getString( "nr_quantidade" ) +")"; 
                sql+= "WHERE oid_nota_fiscal = '"+ res.getString( "oid_nota_fiscal_entrada_cliente" ) +"' ";
                sql+= "AND oid_item_produto_cliente = '"+ res.getString( "oid_item_entrada_cliente" ) +"' ";         

                i = executasql.executarUpdate(sql);            
            }
           
            sql = "DELETE from notas_fiscais_devolucao ";
            sql+= "WHERE oid_nota_fiscal_devolucao = " + res.getInt( "oid_nota_fiscal_devolucao" );
     
            i = executasql.executarUpdate(sql);          
       }
       
     }
     catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem(exc.getMessage());
      excecoes.setMetodo("atualiza_Notas(WMS_Nota_FiscalED edV)");
      excecoes.setExc(exc);
      throw excecoes;
    }  
  }
  
 
  public ArrayList listaItemPedido(WMS_Item_Produto_ClienteED ed)throws Excecoes{
	  
	  String sql = null;
	  ResultSet res = null;
	  ArrayList list = new ArrayList();
	  try{
		  
		  sql = " SELECT * FROM notas_fiscais_wms, itens_produtos_clientes " +
		  " WHERE notas_fiscais_wms.oid_nota_fiscal = itens_produtos_clientes.oid_nota_fiscal ";
		  
		  sql +=" ORDER BY DT_Emissao";
		  
		  FormataDataBean DataFormatada = new FormataDataBean();
		  res = this.executasql.executarConsulta(sql);
		  while (res.next())
		  {
			  WMS_Item_Produto_ClienteED edVolta = new WMS_Item_Produto_ClienteED();
			  edVolta.setOid_Nota_Fiscal(res.getString("OID_Nota_Fiscal"));
			  edVolta.setNR_Nota_Fiscal(res.getInt("Nr_Nota_Fiscal"));
			  edVolta.setVl_Produto(res.getDouble("VL_Produto"));////////
			  edVolta.setNr_Quantidade_Movimento(res.getDouble("NR_Quantidade_Movimento"));
			  edVolta.setDT_Emissao(DataFormatada.getDT_FormataData(res.getString("DT_Emissao")));
			  edVolta.setVl_Produto_TX(FormataValor.formataValorBT(res.getDouble("VL_Produto"), 2));
			  edVolta.setNr_Quantidade_Movimento_TX(FormataValor.formataValorBT(res.getDouble("nr_quantidade_movimento"), 3));

			  list.add(edVolta);
		  }
	  } catch(Exception exc){
		  throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "listaItemPedido()");
	  }
	  return list;
  } 
  
  /**
   * Metodo para registrar a devolucao dos itens das notas fiscais de entrada pelo metodo FIFO.
   * @param pOid_Produto_Cliente = oid do produto sendo devolvido.
   * @param pNr_Quantidade_Saida = quantidade a devolver.
   * @return nrNFsEntrada = numero(s) da(s) nf(s) de entrada que foi devolvida.
   * @throws Excecoes
   */
  public String devolveItem(String pOid_Produto_Cliente, double pNr_Quantidade_Saida) throws Excecoes{
	  String sql = null,  nrNFsEntrada = "", dm_Devolvido = "", oid_Item_Nota_Fiscal = "", oid_Nota_Fiscal = "", nr_Nota_Fiscal = "";
	  double nr_Quantidade_Ja_Devolvida = 0, nr_Quantidade_Movimento = 0, nr_Saldo = 0;
	  int Itens_Nao_Devolvidos = 0;
	  ResultSet itnE = null, itND = null;
	  
	  try{         
		  //Busca os itens nas nf de entrada (Remessa para armazenagem) que nao foram devolvidos ainda.
		  sql="SELECT "+
		  	  "nfe.oid_Nota_Fiscal, "+
			  "nfe.nr_Nota_Fiscal, "+
			  "itn.oid_Item_Notas_Fiscais, "+
			  "itn.nr_Quantidade, "+
			  "itn.nr_Qt_Devolucao "+
			  "FROM "+
			  "Notas_Fiscais as nfe, "+
			  "Itens_Notas_Fiscais as itn "+
			  "WHERE "+
			  "nfe.oid_Nota_Fiscal = itn.oid_Nota_Fiscal and "+
			  "nfe.dm_tipo_nota_fiscal = 'E' and "+
			  "nfe.dm_Devolvido <> 'S' and "+
			  "itn.oid_Produto_Cliente = '"+ pOid_Produto_Cliente +"' and " +
			  "itn.dm_Devolvido <> 'S' "+
			  "ORDER BY "+
			  "nfe.dt_Entrada, nfe.hr_Entrada";
		  itnE = executasql.executarConsulta(sql);

		  while( itnE.next() ){
			  
			  oid_Nota_Fiscal = itnE.getString("oid_Nota_Fiscal");
			  oid_Item_Nota_Fiscal = itnE.getString("oid_Item_Nota_Fiscal");
			  nr_Nota_Fiscal = itnE.getString("nr_Nota_Fiscal");
			  nr_Quantidade_Movimento = itnE.getDouble("nr_Quantidade");
			  nr_Quantidade_Ja_Devolvida = itnE.getDouble("nr_Qt_Devolucao");
			  //Verifica quanto pode ser devolvido do item, desta nfe
			  if ((pNr_Quantidade_Saida + nr_Quantidade_Ja_Devolvida) > nr_Quantidade_Movimento){
				  // Se somente uma parte da saída	
				  nr_Saldo =  pNr_Quantidade_Saida - (nr_Quantidade_Movimento - nr_Quantidade_Ja_Devolvida); // Então sobrou algo para outra nota
				  nr_Quantidade_Ja_Devolvida = nr_Quantidade_Movimento; 
			  } else {
				  // Se toda a saída
				  nr_Saldo = 0; // Então sobrou nada para outro nota.
				  nr_Quantidade_Ja_Devolvida = nr_Quantidade_Ja_Devolvida + pNr_Quantidade_Saida;
			  }
			  if (nr_Quantidade_Ja_Devolvida == nr_Quantidade_Movimento) {
				  // Então não há nada mais a devolver deste item da nfe
				  dm_Devolvido = "S";
			  } else {
				  dm_Devolvido = " ";
			  }
			  //Atualiza o item da nfe de entrada
			  sql="UPDATE "+
			  	  "Itens_Notas_Fiscais "+
			  	  "SET "+
			  	  "nr_Qt_Devolucao = "+ nr_Quantidade_Ja_Devolvida + ", " +
			  	  "dm_Devolvido = '" + dm_Devolvido + "' "+
			  	  "WHERE "+
			  	  "oid_Item_Nota_Fiscal = '"+ oid_Item_Nota_Fiscal + "'" ; 
			  executasql.executarUpdate(sql);
			  //Testa se a NF já devolveu todos os itens
			  sql="SELECT "+
			  	  "count(oid_Item_Nota_Fiscal) as Itens_Nao_Devolvidos "+
			  	  "FROM "+
				  "Itens_Notas_Fiscais "+
				  "WHERE "+
				  "Oid_Nota_Fiscal = '" + oid_Nota_Fiscal + "' and "+
				  "dm_Devolvido <> 'S'";
			  itND = executasql.executarConsulta(sql);
			  itND.next();
			  Itens_Nao_Devolvidos = itND.getInt("Itens_Nao_Devolvidos");
			  if (Itens_Nao_Devolvidos == 0){
				  sql="UPDATE "+
			  	  	  "Notas_Fiscais "+
			  	      "SET "+
			  	      "dm_Devolvido = 'S' "+
			  	      "WHERE "+
			  	      "oid_Nota_Fiscal = '"+ oid_Nota_Fiscal + "'" ; 
				  executasql.executarUpdate(sql);
			  }
			  // Pega o número da NF para colocar na NF de devolucao
			  nrNFsEntrada = nrNFsEntrada + ", " + nr_Nota_Fiscal;
			  //Se o saldo for zero para por aqui, senão continua a buscar o item para baixar.
			  if (nr_Saldo == 0) {
				  break;
			  } else {
				  pNr_Quantidade_Saida = nr_Saldo;  
			  }
		  }
		  return nrNFsEntrada.trim().substring(0,nrNFsEntrada.lastIndexOf(","));
	  }
	  catch(Exception exc){
		  Excecoes excecoes = new Excecoes();
		  excecoes.setClasse(this.getClass().getName());
		  excecoes.setMensagem(exc.getMessage());
		  excecoes.setMetodo("devolveItem(String pOid_Produto_Cliente, double pNr_Quantidade_Saida)");
		  excecoes.setExc(exc);
		  throw excecoes;
	  }    
  }
  
}