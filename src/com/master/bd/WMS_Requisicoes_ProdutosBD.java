package com.master.bd;

/**
 * Título: WMS_Requisicoes_ProdutosBD
 * Descrição: Requisições - BD
 * Data da criação: 12/2003
 * Atualizado em: 03/2004
 * Empresa: ÊxitoLogística Mastercom
 * Autor: Carlos Eduardo de Holleben
*/

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.WMS_Requisicoes_ProdutosED;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

public class WMS_Requisicoes_ProdutosBD {

  private ExecutaSQL executasql;

  public WMS_Requisicoes_ProdutosBD(ExecutaSQL sql) {
    this.executasql = sql;
  }

  public WMS_Requisicoes_ProdutosED isExisteRequisicao( String oid_nota_fiscal_transacao ) throws Excecoes{
    String sql = null;
    WMS_Requisicoes_ProdutosED ed = new WMS_Requisicoes_ProdutosED();
    try{
      sql = " select * from Requisicoes_Produtos where oid_nota_fiscal_transacao ='"+ oid_nota_fiscal_transacao +"'";

      ResultSet res = null, res2 = null;
      res = this.executasql.executarConsulta(sql);
      while(res.next()){
        ed.setOID_Requisicao_Produto( res.getInt( "oid_requisicao_produto" ) );
        ed.setDT_Conclusao( res.getString( "DT_Conclusao" ) );
        ed.setHR_Conclusao( res.getString( "HR_Conclusao" ) );
        sql = "select * from notas_fiscais_wms where oid_nota_fiscal = '"+ res.getString( "oid_nota_fiscal_transacao" ) +"'";
        res2 = this.executasql.executarConsulta(sql);
        while(res2.next()){
          ed.setNR_Nota_Fiscal_Transacao( res2.getString( "NR_Nota_Fiscal" ) );
        }
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao recuperar registro");
      excecoes.setMetodo("isExisteRequisicao()");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return ed;
  }
  
  public WMS_Requisicoes_ProdutosED isExisteRequisicaoAberta( String oid_nota_fiscal_transacao ) throws Excecoes{
    String sql = null;
    WMS_Requisicoes_ProdutosED ed = new WMS_Requisicoes_ProdutosED();
    try{
      sql = " select * from Requisicoes_Produtos where oid_nota_fiscal_transacao ='"+ oid_nota_fiscal_transacao +"'";
      sql+= " AND dm_situacao = 'A'";

      ResultSet res = null, res2 = null;
      res = this.executasql.executarConsulta(sql);
      while(res.next()){
        ed.setOID_Requisicao_Produto( res.getInt( "oid_requisicao_produto" ) );
        ed.setDT_Conclusao( res.getString( "DT_Conclusao" ) );
        ed.setHR_Conclusao( res.getString( "HR_Conclusao" ) );
        sql = "select * from notas_fiscais_wms where oid_nota_fiscal = '"+ res.getString( "oid_nota_fiscal_transacao" ) +"'";
        res2 = this.executasql.executarConsulta(sql);
        while(res2.next()){
          ed.setNR_Nota_Fiscal_Transacao( res2.getString( "NR_Nota_Fiscal" ) );
        }
      }
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao recuperar registro");
      excecoes.setMetodo("isExisteRequisicaoAberta()");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return ed;
  }

  public WMS_Requisicoes_ProdutosED inclui(WMS_Requisicoes_ProdutosED ed) throws Excecoes{

    String sql = null;
    int valOid = 0;
    String oid_nota = "";
    WMS_Requisicoes_ProdutosED WMS_Requisicoes_ProdutosED = new WMS_Requisicoes_ProdutosED();
    String mensagem = "Requisição já registrada!";
    String data_Conclusao = null;
    String hora_Conclusao = "";

    try{
      if( ed.getOID_Nota_Fiscal_Transacao() != null && !ed.getOID_Nota_Fiscal_Transacao().equals("") ){
        ResultSet rs1 = executasql.executarConsulta( "select oid_nota_fiscal from notas_fiscais_wms where OID_Nota_Fiscal = '"+ed.getOID_Nota_Fiscal_Transacao()+"'" );
        while (rs1.next()){
          oid_nota = rs1.getString("oid_nota_fiscal");
        }

        if( oid_nota == null ){
          mensagem = "Nº da Nota Fiscal incorreto!";
          throw new Exception();
        }
      }

      if( ed.getHR_Conclusao() != null && !ed.getHR_Conclusao().equals("") ) hora_Conclusao = "'"+ed.getHR_Conclusao()+"'";
      if( ed.getDT_Conclusao() != null && !ed.getDT_Conclusao().equals("") ) data_Conclusao = ed.getDT_Conclusao();

      ResultSet rs = executasql.executarConsulta("select max(Oid_Requisicao_Produto) as result from Requisicoes_Produtos");

      while (rs.next()) valOid = rs.getInt("result");

      sql = "insert into Requisicoes_Produtos (Oid_Requisicao_Produto,oid_deposito,oid_pessoa,oid_pessoa_destinatario,oid_pessoa_transportador,oid_tipo_movimento_produto,dt_requisicao,hr_requisicao,dm_situacao,dt_conclusao,hr_conclusao,oid_nota_fiscal_transacao) values "+
            "("+ ++valOid + "," + ed.getOID_Deposito() +  ",'" +ed.getOID_Pessoa() + "','" + ed.getOID_Pessoa_Destinatario() +"','" + ed.getOID_Pessoa_Transportador() +"'," + ed.getOID_Tipo_Movimento_Produto()+",'" + ed.getDT_Requisicao() +"','"+ed.getHR_Requisicao()+"','A',"+data_Conclusao+",'"+hora_Conclusao+"','"+oid_nota+"')";

      int i = executasql.executarUpdate(sql);
      WMS_Requisicoes_ProdutosED.setOID_Requisicao_Produto( valOid );
      WMS_Requisicoes_ProdutosED.setOID_Deposito( ed.getOID_Deposito() );
      WMS_Requisicoes_ProdutosED.setOID_Pessoa( ed.getOID_Pessoa() );
      WMS_Requisicoes_ProdutosED.setOID_Pessoa_Destinatario( ed.getOID_Pessoa_Destinatario() );
      WMS_Requisicoes_ProdutosED.setOID_Pessoa_Transportador( ed.getOID_Pessoa_Transportador() );
      WMS_Requisicoes_ProdutosED.setOID_Tipo_Movimento_Produto( ed.getOID_Tipo_Movimento_Produto() );
      WMS_Requisicoes_ProdutosED.setDT_Conclusao( data_Conclusao );
      WMS_Requisicoes_ProdutosED.setHR_Conclusao( hora_Conclusao );
      WMS_Requisicoes_ProdutosED.setDT_Requisicao( ed.getDT_Requisicao() );
      WMS_Requisicoes_ProdutosED.setHR_Requisicao( ed.getHR_Requisicao() );
      WMS_Requisicoes_ProdutosED.setOID_Nota_Fiscal_Transacao( oid_nota );
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem(mensagem);
      excecoes.setMetodo("inclui()");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return WMS_Requisicoes_ProdutosED;

  }

  public void altera(WMS_Requisicoes_ProdutosED ed) throws Excecoes{

    String sql = null;
    String oid_nota = "";
    String mensagem = "Erro ao alterar dados da Requisição!";
    String data_Conclusao = null;
    String hora_Conclusao = "";

    try{

      if( ed.getOID_Nota_Fiscal_Transacao() != null && !ed.getOID_Nota_Fiscal_Transacao().equals("") ){
        ResultSet rs1 = executasql.executarConsulta( "select oid_nota_fiscal from notas_fiscais_wms where oid_nota_fiscal_transacao = '"+ed.getOID_Nota_Fiscal_Transacao()+"'" );
        while (rs1.next()){
          oid_nota = rs1.getString("oid_nota_fiscal");
        }
        if( oid_nota == null ){
          mensagem = "Nº da Nota Fiscal incorreto!";
          throw new Exception();
        }
      }

      if( ed.getHR_Conclusao() != null && !ed.getHR_Conclusao().equals("") ) hora_Conclusao = "'"+ed.getHR_Conclusao()+"'";
      if( ed.getDT_Conclusao() != null && !ed.getDT_Conclusao().equals("") ) data_Conclusao = "'"+ed.getDT_Conclusao()+"'";

      sql = " update Requisicoes_Produtos set ";
      sql += " oid_deposito = " + ed.getOID_Deposito() + ", ";
      sql += " oid_pessoa = '" + ed.getOID_Pessoa() + "', ";
      sql += " oid_pessoa_destinatario = '" + ed.getOID_Pessoa_Destinatario() + "', ";
      sql += " oid_pessoa_transportador = '" + ed.getOID_Pessoa_Transportador() + "', ";
      sql += " oid_tipo_movimento_produto = " + ed.getOID_Tipo_Movimento_Produto() + ", ";
      sql += " dt_requisicao = '" + ed.getDT_Requisicao() + "', ";
      sql += " hr_requisicao = '" + ed.getHR_Requisicao() + "', ";
      sql += " dm_situacao = '" + ed.getDM_Situacao() + "', ";
      sql += " dt_conclusao = " + data_Conclusao + ", ";
      sql += " hr_conclusao = '" + hora_Conclusao + "', ";
      sql += " oid_nota_fiscal_transacao = '" + oid_nota + "' ";
      sql += " where Oid_Requisicao_Produto = " + ed.getOID_Requisicao_Produto();



      int i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem(mensagem);
      excecoes.setMetodo("altera()");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void alteraSituacao( int oid_requisicao_produto, String dm_situacao ) throws Excecoes{
    String sql = null;
    String mensagem = "Erro ao alterar dados da Requisição!";
    try{
      sql = "UPDATE Requisicoes_Produtos SET ";
      sql += " dm_situacao = '" + dm_situacao + "',";
      sql += " dt_conclusao = '" + Data.getDataDMY() + "',";
      sql += " hr_conclusao = '" + Data.getHoraHM() + "'";
      sql += " WHERE Oid_Requisicao_Produto = " + oid_requisicao_produto;

      int i = executasql.executarUpdate(sql);
    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem(mensagem);
      excecoes.setMetodo("alteraSituacao()");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public void deleta(WMS_Requisicoes_ProdutosED ed) throws Excecoes{

    String sql = null;

    try{

      sql = "delete from Requisicoes_Produtos WHERE Oid_Requisicao_Produto = "+ ed.getOID_Requisicao_Produto();

      int i = executasql.executarUpdate(sql);

      sql = "select oid_movimento_produto from Movimentos_Produtos WHERE Oid_Requisicao_Produto = " + ed.getOID_Requisicao_Produto();
      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);
      while (res.next()){
        sql="delete from series_produtos where oid_movimento_produto ="+ res.getInt( "oid_movimento_produto" );
        i = executasql.executarUpdate(sql);
      }

      sql = "delete from Movimentos_Produtos WHERE Oid_Requisicao_Produto = " + ed.getOID_Requisicao_Produto();

      i = executasql.executarUpdate(sql);
    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao deletar Requisição");
      excecoes.setMetodo("deleta()");
      excecoes.setExc(exc);
      throw excecoes;
    }
  }

  public WMS_Requisicoes_ProdutosED getByRecord(WMS_Requisicoes_ProdutosED ed)throws Excecoes{

    String sql = null;

    WMS_Requisicoes_ProdutosED edVolta = new WMS_Requisicoes_ProdutosED();

    try{
      sql = " select * from Requisicoes_Produtos";
      if ( ed.getOID_Requisicao_Produto() > 0 ){
        sql += " where Oid_Requisicao_Produto = " + ed.getOID_Requisicao_Produto();
      }
      ResultSet res = null, res2 = null;
      res = this.executasql.executarConsulta(sql);

      while (res.next()){
        edVolta = new WMS_Requisicoes_ProdutosED();
        edVolta.setOID_Requisicao_Produto( res.getInt("Oid_Requisicao_Produto") );
        edVolta.setOID_Nota_Fiscal_Transacao(res.getString("oid_nota_fiscal_transacao"));
        edVolta.setOID_Deposito(res.getInt("oid_deposito"));
        edVolta.setOID_Pessoa(res.getString("oid_pessoa"));
        edVolta.setOID_Pessoa_Destinatario(res.getString("oid_pessoa_destinatario"));
        edVolta.setOID_Pessoa_Transportador(res.getString("oid_pessoa_transportador"));
        edVolta.setOID_Tipo_Movimento_Produto(res.getInt("oid_tipo_movimento_produto"));
        edVolta.setDT_Requisicao(res.getString("dt_requisicao"));
        edVolta.setHR_Requisicao(res.getString("hr_requisicao"));
        edVolta.setDM_Situacao(res.getString("dm_situacao"));
        edVolta.setDT_Conclusao(res.getString("dt_conclusao"));
        edVolta.setHR_Conclusao(res.getString("hr_conclusao"));

        sql = "select * from pessoas where oid_pessoa = '"+edVolta.getOID_Pessoa()+"'";
        res2 = this.executasql.executarConsulta(sql);
        while (res2.next()){
          edVolta.setNM_Razao_Social_Pessoa( res2.getString( "NM_Razao_Social" ) );
          edVolta.setNR_CNPJ_CPF_Pessoa( res2.getString( "NR_CNPJ_CPF" ) );
        }

        sql = "select * from pessoas where oid_pessoa = '"+edVolta.getOID_Pessoa_Destinatario()+"'";
        res2 = this.executasql.executarConsulta(sql);
        while (res2.next()){
          edVolta.setNM_Razao_Social_Destinatario( res2.getString( "NM_Razao_Social" ) );
          edVolta.setNR_CNPJ_CPF_Destinatario( res2.getString( "NR_CNPJ_CPF" ) );
        }

        sql = "select * from pessoas where oid_pessoa = '"+edVolta.getOID_Pessoa_Transportador()+"'";
        res2 = this.executasql.executarConsulta(sql);
        while (res2.next()){
          edVolta.setNM_Razao_Social_Transportador( res2.getString( "NM_Razao_Social" ) );
          edVolta.setNR_CNPJ_CPF_Transportador( res2.getString( "NR_CNPJ_CPF" ) );
        }

        sql = "select * from depositos where oid_deposito = "+edVolta.getOID_Deposito();
        res2 = this.executasql.executarConsulta(sql);
        while (res2.next()){
          edVolta.setNM_Deposito( res2.getString( "NM_Deposito" ) );
        }
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

  public ArrayList lista( WMS_Requisicoes_ProdutosED ed, String orderby )throws Excecoes{

    String sql = null;
    ArrayList list = new ArrayList();
    ResultSet res = null;
    ResultSet res2 = null;
    String order_by = "";
    int oid_deposito = 0;
    String remetente = "";
    String destinatario = "";
    String transportador = "";
    int movimento = 0;
    String nota_fiscal = "";
    String dt_d = "";
    String dt_m = "";
    String dt_a = "";

    try{
      sql = "SELECT * FROM requisicoes_produtos, notas_fiscais_wms WHERE requisicoes_produtos.oid_requisicao_produto > 0";
      order_by += orderby;
      
      sql+= " AND requisicoes_produtos.oid_nota_fiscal_transacao = notas_fiscais_wms.oid_nota_fiscal ";
      sql+= " AND notas_fiscais_wms.oid_unidade_emissora = "+ ed.getOID_Unidade();

      if( (ed.getDM_Situacao()).equals("0") )
         ed.setDM_Situacao( "" );

      if( ed.getOID_Deposito() != 0 )
         sql+= " AND requisicoes_produtos.oid_deposito = " + ed.getOID_Deposito();

      if( ed.getOID_Pessoa() != null && !(ed.getOID_Pessoa()).equals("") )
         sql+= " AND requisicoes_produtos.oid_pessoa = '" + ed.getOID_Pessoa()+"'";

      if( ed.getOID_Pessoa_Destinatario() != null && !(ed.getOID_Pessoa_Destinatario()).equals("") )
         sql+= " AND requisicoes_produtos.oid_pessoa_destinatario = '" + ed.getOID_Pessoa_Destinatario()+"'";

      if( ed.getOID_Pessoa_Transportador() != null && !(ed.getOID_Pessoa_Transportador()).equals("") )
         sql+= " AND requisicoes_produtos.oid_pessoa_transportador = '"+ed.getOID_Pessoa_Transportador()+"'";

      if( ed.getOID_Tipo_Movimento_Produto() != 0 )
         sql+= " AND requisicoes_produtos.oid_tipo_movimento_produto = "+ed.getOID_Tipo_Movimento_Produto();

      if( ed.getDM_Situacao() != null && !(ed.getDM_Situacao()).equals("") )
         sql+= " AND requisicoes_produtos.dm_situacao LIKE '"+ed.getDM_Situacao()+"%'";

      if( ed.getOID_Nota_Fiscal_Transacao() != null && !(ed.getOID_Nota_Fiscal_Transacao()).equals("") )
         sql+= " AND requisicoes_produtos.oid_nota_fiscal_transacao = '"+ed.getOID_Nota_Fiscal_Transacao()+"'";

      if( ed.getDT_Conclusao() != null && !(ed.getDT_Conclusao()).equals("") )
         sql+= " AND requisicoes_produtos.dt_conclusao = '"+ed.getDT_Conclusao()+"'";

      if( ed.getHR_Conclusao() != null && !(ed.getHR_Conclusao()).equals("") )
         sql+= " AND requisicoes_produtos.hr_conclusao = '"+ed.getHR_Conclusao()+"%'";

      if( ed.getDT_Requisicao() != null && !(ed.getDT_Requisicao()).equals("") )
         sql+= " AND requisicoes_produtos.dt_requisicao = '"+ed.getDT_Requisicao()+"'";

      if( ed.getHR_Requisicao() != null && !(ed.getHR_Requisicao()).equals("") )
         sql+= " AND requisicoes_produtos.hr_requisicao = '"+ed.getHR_Requisicao()+"'";

      if( ed.getDM_Nota_Fiscal() != null && (ed.getDM_Nota_Fiscal()).equals("S") )
         sql+= " AND requisicoes_produtos.oid_nota_fiscal_transacao != ''";

      if( ed.getDM_Nota_Fiscal() != null && (ed.getDM_Nota_Fiscal()).equals("N") )
         sql+= " AND requisicoes_produtos.oid_nota_fiscal_transacao = ''";

      sql += order_by;

    //  // System.out.println( "==== req === " + sql );
      
      res2 = this.executasql.executarConsulta(sql);

      //popula
      while (res2.next()){
        oid_deposito = res2.getInt( "oid_deposito" );
        remetente = res2.getString( "oid_pessoa" );
        destinatario = res2.getString( "oid_pessoa_destinatario" );
        transportador = res2.getString( "oid_pessoa_transportador" );
        movimento = res2.getInt( "oid_tipo_movimento_produto" );
        nota_fiscal = res2.getString( "oid_nota_fiscal_transacao" ); 
        
        WMS_Requisicoes_ProdutosED edVolta = new WMS_Requisicoes_ProdutosED();
        
        edVolta.setOID_Nota_Fiscal_Transacao( nota_fiscal );
        
        sql = "select nm_razao_social, nr_cnpj_cpf from pessoas where oid_pessoa = '"+ remetente +"'";
        res = this.executasql.executarConsulta(sql);
        while (res.next()){
            edVolta.setNR_CNPJ_CPF_Pessoa( res.getString( "nr_cnpj_cpf" ) );
            edVolta.setNM_Razao_Social_Pessoa( res.getString( "nm_razao_social" ) );
        }

        sql = "select nm_razao_social, nr_cnpj_cpf from pessoas where oid_pessoa = '"+ destinatario +"'";
        res = this.executasql.executarConsulta(sql);
        while (res.next()){
            edVolta.setNR_CNPJ_CPF_Destinatario( res.getString( "nr_cnpj_cpf" ) );
            edVolta.setNM_Razao_Social_Destinatario( res.getString( "nm_razao_social" ) );
        }

        sql = "select nm_razao_social, nr_cnpj_cpf from pessoas where oid_pessoa = '"+ transportador +"'";
        res = this.executasql.executarConsulta(sql);
        while (res.next()){
            edVolta.setNR_CNPJ_CPF_Transportador( res.getString( "nr_cnpj_cpf" ) );
            edVolta.setNM_Razao_Social_Transportador( res.getString( "nm_razao_social" ) );
        }

        sql = "select nm_deposito, oid_deposito from depositos where oid_deposito = "+oid_deposito;
        res = this.executasql.executarConsulta(sql);
        while (res.next()){
            edVolta.setNM_Deposito( res.getString( "nm_deposito" ) );
            edVolta.setOID_Deposito( res.getInt( "oid_deposito" ) );
        }

        sql = "select nm_descricao from tipos_movimentos_produtos where oid_tipo_movimento_produto = "+ movimento;
        res = this.executasql.executarConsulta(sql);
        while (res.next()) edVolta.setNM_Tipo_Movimento_Produto( res.getString( "nm_descricao" ) );

        sql = "select nr_nota_fiscal from notas_fiscais_wms where oid_nota_fiscal = '"+ nota_fiscal +"'";
        res = this.executasql.executarConsulta(sql);
        while (res.next()) edVolta.setNR_Nota_Fiscal_Transacao( res.getString( "nr_nota_fiscal" ) );

        if( edVolta.getNR_Nota_Fiscal_Transacao() == null ) edVolta.setNR_Nota_Fiscal_Transacao( "" );

        edVolta.setOID_Requisicao_Produto( res2.getInt( "OID_Requisicao_Produto" ) );
        edVolta.setOID_Deposito( res2.getInt( "OID_Deposito" ) );
        edVolta.setOID_Pessoa( res2.getString( "OID_Pessoa" ) );
        edVolta.setOID_Pessoa_Destinatario( res2.getString( "OID_Pessoa_Destinatario" ) );
        edVolta.setOID_Pessoa_Transportador( res2.getString( "OID_Pessoa_Transportador" ) );
        edVolta.setOID_Nota_Fiscal_Transacao( res2.getString( "OID_Nota_Fiscal_Transacao" ) );
        edVolta.setOID_Tipo_Movimento_Produto( res2.getInt( "OID_Tipo_Movimento_Produto" ) );

        if( res2.getString( "DT_Requisicao" ) != null && res2.getString( "DT_Requisicao" ).substring( 4, 5 ).equals( "-" ) ){
            dt_a = res2.getString( "DT_Requisicao" ).substring( 0, 4 );
            dt_d = res2.getString( "DT_Requisicao" ).substring( 8, 10 );
            dt_m = res2.getString( "DT_Requisicao" ).substring( 5, 7 );
            edVolta.setDT_Requisicao( dt_d + "/" + dt_m + "/" + dt_a );
        }else edVolta.setDT_Requisicao( "" );
        if( res2.getString( "DT_Conclusao" ) != null && res2.getString( "DT_Conclusao" ).substring( 4, 5 ).equals( "-" ) ){
            dt_a = res2.getString( "DT_Conclusao" ).substring( 0, 4 );
            dt_d = res2.getString( "DT_Conclusao" ).substring( 8, 10 );
            dt_m = res2.getString( "DT_Conclusao" ).substring( 5, 7 );
            edVolta.setDT_Conclusao( dt_d + "/" + dt_m + "/" + dt_a );
        }else edVolta.setDT_Conclusao( "" );

        edVolta.setHR_Conclusao( res2.getString( "HR_Conclusao" ) );
        edVolta.setHR_Requisicao( res2.getString( "HR_Requisicao" ) );

        edVolta.setDM_Situacao( res2.getString( "DM_Situacao" ) );
        if( edVolta.getDM_Situacao().equals( "A" ) ){
          edVolta.setDM_Situacao( "ABERTA" );
        }
        if( edVolta.getDM_Situacao().equals( "F" ) ){
          edVolta.setDM_Situacao( "FINALIZADA" );
        }

        list.add(edVolta);
      }

    }
    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem("Erro ao listar Requisições - SQL="+sql);
      excecoes.setMetodo("lista(WMS_Requisicoes_ProdutosED ed)");
      excecoes.setExc(exc);
      throw excecoes;
    }

    return list;
  }

  public void geraRelatorio(WMS_Requisicoes_ProdutosED ed)throws Excecoes{

//    String sql = null;
//
//    WMS_Requisicoes_ProdutosED edVolta = new WMS_Requisicoes_ProdutosED();
//
//    try{
//
//      sql = "select * from Tipo_Movimentoes where Oid_Tipo_Movimento_Produto > 0";
//
//      if (ed.getCd_Tipo_Movimento() != null && !ed.getCd_Tipo_Movimento().equals("")){
//        sql += " and Cd_Tipo_Movimento = '" + ed.getCd_Tipo_Movimento() + "'";
//      }
//      if (ed.getCd_Remessa() != null && !ed.getCd_Remessa().equals("")){
//        sql += " and Cd_Remessa = '" + ed.getCd_Remessa() + "'";
//      }
//
//      ResultSet res = null;
//      res = this.executasql.executarConsulta(sql);
//
//      WMS_Requisicoes_ProdutosRL WMS_Requisicoes_Produtos_rl = new WMS_Requisicoes_ProdutosRL();
//      WMS_Requisicoes_Produtos_rl.geraRelatEstoque(res);
//    }
//    catch (Excecoes e){
//      throw e;
//    }
//    catch(Exception exc){
//      Excecoes exce = new Excecoes();
//      exce.setExc(exc);
//      exce.setMensagem("Erro no método listar");
//      exce.setClasse(this.getClass().getName());
//      exce.setMetodo("geraRelatorio(WMS_Requisicoes_ProdutosED ed)");
//    }
//
  }

  public boolean isFinalizada( int oid_requisicao_produto )throws Excecoes{
    boolean bol = true;
    String sql = null;
    String oid_nota = "";
    String mensagem = "Erro interno!";
    String data_Conclusao = null;
    String hora_Conclusao = "";

    try{
      sql = "select * from movimentos_produtos where oid_requisicao_produto ="+oid_requisicao_produto;
      ResultSet res = null;
      res = this.executasql.executarConsulta(sql);
      while (res.next()){
        if( res.getInt( "nr_quantidade_efetiva" ) != res.getInt( "nr_quantidade_requerida" ) ){
          bol = false;
          break;
        }
      }

    }

    catch(Exception exc){
      Excecoes excecoes = new Excecoes();
      excecoes.setClasse(this.getClass().getName());
      excecoes.setMensagem(mensagem);
      excecoes.setMetodo("isFinalizada()");
      excecoes.setExc(exc);
      throw excecoes;
    }
    return bol;
  }

}