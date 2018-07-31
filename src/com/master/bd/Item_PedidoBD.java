package com.master.bd;

/**
 * @author André Valadas
*/

import java.sql.ResultSet;
import java.util.ArrayList;
import com.master.ed.Item_PedidoED;
import com.master.ed.PedidoED;
import com.master.ed.ProdutoED;
import com.master.iu.Pro004Bean;
import com.master.rn.Item_PedidoRN;
import com.master.root.FormataDataBean;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.Mensagens;
import com.master.util.Valor;
import com.master.util.bd.ExecutaSQL;

public class Item_PedidoBD extends BancoUtil {

    private ExecutaSQL executasql;
    FormataDataBean dataFormatada = new FormataDataBean();

  public Item_PedidoBD(ExecutaSQL sql) {
      super(sql);
      this.executasql = sql;
  }

  public Item_PedidoED inclui(Item_PedidoED ed) throws Excecoes{

      String sql = null;
      try{

          //*** Busca próximo OID
          ed.setOID_Item_Pedido(getAutoIncremento("oid_Item_Pedido", "Itens_Pedidos"));
          sql = " INSERT INTO Itens_Pedidos (" +
                "    oid_Item_Pedido" +
            	"	,oid_Pedido" +
            	"	,oid_Produto" +
            	"	,oid_Preco_Produto" +
            	"	,oid_Promocao_Produto" +
            	"	,oid_Localizacao" +
            	"	,DM_Quantidade" +
            	"	,NR_Quantidade" +
            	"   ,NR_QT_Pedido" +
            	"	,NR_QT_Atendido" +
            	"	,NR_Peso_Pedido" +
            	"	,NR_Peso_Real" +
                "   ,NR_QT_Troca" +
                "   ,VL_Troca" +
            	"	,Dt_Stamp" +
            	"	,VL_Item" +
            	"	,VL_Desconto" +
            	"	,VL_Produto" +
                "   ,VL_Promocao" + 
            	"	,VL_Unitario" +
            	"	,VL_Unitario_Tabela" +
            	"	,VL_IPI" +
            	"	,VL_Margem_Contribuicao" +
            	"	,PE_Desconto" +
            	"	,PE_Aliquota_IPI" +
            	"	,PE_Margem_Contribuicao" +
            	"	,DM_Situacao)";
          sql+= " VALUES ( " +
            	ed.getOID_Item_Pedido() + 
            	"," + ed.getOID_Pedido() + 
            	"," + ed.getOID_Produto() +
            	"," + ed.getOid_Preco_Produto() +
            	"," + ed.getOid_Promocao_Produto() +
            	"," + ed.getOid_Localizacao() +
            	",'" + getValueDef(ed.getDM_Quantidade(),"U") + "'" +
            	"," + ed.getNR_Quantidade() +
            	"," + ed.getNR_QT_Pedido() +
            	"," + ed.getNR_QT_Atendido() +
            	"," + ed.getNR_Peso_Pedido() + //*** QT_Atendido X Peso_Medio 
            	"," + ed.getNR_Peso_Real() +
                "," + ed.getNR_QT_Troca() +
                "," + ed.getVL_Troca() +
            	",'" + Data.getDataDMY() + "'" +
            	"," + ed.getVL_Item() + 
            	"," + ed.getVL_Desconto() + 
            	"," + ed.getVL_Produto() +
            	"," + ed.getVL_Promocao() +
            	"," + ed.getVL_Unitario() +
            	"," + ed.getVL_Unitario_Tabela() +
            	"," + ed.getVL_IPI() + 
            	"," + ed.getVL_Margem_Contribuicao() +
            	"," + ed.getPE_Desconto() + 
            	"," + ed.getPE_Aliquota_IPI() + 
            	"," + ed.getPE_Margem_Contribuicao() +
            	",'" + ed.getDM_Situacao() + "')";
          executasql.executarUpdate(sql);
          
      } catch(Exception exc){
          throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
              "inclui()");
      }
      return ed;
  }

    public void altera(Item_PedidoED ed) throws Excecoes {

        String sql = null;
        try {

            sql = " UPDATE Itens_Pedidos SET ";
            sql += " oid_Produto= '"+ed.getOID_Produto()+"', ";
            sql += " DM_Quantidade= '" + ed.getDM_Quantidade() + "', ";
            sql += " NR_Quantidade= " + ed.getNR_Quantidade() + ", ";
            sql += " NR_QT_Pedido= " + ed.getNR_QT_Pedido() + ", ";
            sql += " NR_QT_Atendido= " + ed.getNR_QT_Atendido() + ", ";
            sql += " oid_Promocao_Produto= " + ed.getOid_Promocao_Produto() + ", ";
            sql += " oid_Localizacao= " + ed.getOid_Localizacao() + ", ";
            if (ed.getOid_Preco_Produto() > 0)
                sql += " oid_Preco_Produto= " + ed.getOid_Preco_Produto() + ", ";
            sql += " VL_Item= " + ed.getVL_Item() + ", ";
            sql += " VL_Desconto= " + ed.getVL_Desconto() + ", ";
            sql += " VL_Produto= " + ed.getVL_Produto() + ", ";
            sql += " VL_IPI= " + ed.getVL_IPI() + ", ";
            sql += " VL_Margem_Contribuicao= " + ed.getVL_Margem_Contribuicao() + ", ";
            sql += " PE_Desconto= " + ed.getPE_Desconto() + ", ";
            sql += " PE_Aliquota_IPI= " + ed.getPE_Aliquota_IPI() + ", ";
            sql += " PE_Margem_Contribuicao= " + ed.getPE_Margem_Contribuicao() + ", ";
            sql += " VL_Promocao= " + ed.getVL_Promocao() + ", ";
            sql += " VL_Unitario= " + ed.getVL_Unitario() + ", ";
            sql += " NR_Peso_Real= " + ed.getNR_Peso_Real() + ", ";
            sql += " NR_QT_Troca= " + ed.getNR_QT_Troca() + ", ";
            sql += " VL_Troca= " + ed.getVL_Troca() + ", ";
            sql += " DM_Situacao= '" + ed.getDM_Situacao() + "'";
            sql += " WHERE OID_Item_Pedido = " + ed.getOID_Item_Pedido();
            executasql.executarUpdate(sql);

        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "altera()");
        }
    }

  //*** Alterações em todos itens de um pedido.. (em caso de Cancelamento)
  public void alteraByPedido(Item_PedidoED ed) throws Excecoes{

      String sql = null;
      try{

        sql = " UPDATE Itens_Pedidos SET ";
        sql += " NR_Quantidade= " + ed.getNR_Quantidade() + ", ";
        sql += " NR_QT_Pedido= " + ed.getNR_QT_Pedido() + ", ";
        sql += " NR_QT_Atendido= " + ed.getNR_QT_Atendido() + ", ";
        sql += " oid_Promocao_Produto= " + ed.getOid_Promocao_Produto() + ", ";
        sql += " oid_Localizacao= " + ed.getOid_Localizacao() + ", ";
        sql += " VL_Item= " + ed.getVL_Item() + ", ";
        sql += " VL_Desconto= " + ed.getVL_Desconto() + ", ";
        sql += " VL_Produto= " + ed.getVL_Produto() + ", ";
        sql += " VL_IPI= " + ed.getVL_IPI() + ", ";
        sql += " VL_Margem_Contribuicao= " + ed.getVL_Margem_Contribuicao() + ", ";
        sql += " PE_Desconto= " + ed.getPE_Desconto() + ", ";
        sql += " PE_Aliquota_IPI= " + ed.getPE_Aliquota_IPI() + ", ";
        sql += " PE_Margem_Contribuicao= " + ed.getPE_Margem_Contribuicao() + ", ";
        sql += " VL_Promocao= " + ed.getVL_Promocao() + ", " ;
        sql += " VL_Unitario= " + ed.getVL_Unitario() + ", " ;
        sql += " NR_Peso_Real= " + ed.getNR_Peso_Real() + ", " ;
        sql += " NR_Peso_Pedido= " + ed.getNR_Peso_Pedido() + ", " ;
        sql += " NR_QT_Troca= " + ed.getNR_QT_Troca() + ", " ;
        sql += " VL_Troca= " + ed.getVL_Troca() + ", " ;
        sql += " DM_Situacao= '" + ed.getDM_Situacao() +"'" ;
        sql += " WHERE OID_Pedido = " + ed.getOID_Pedido();

        executasql.executarUpdate(sql);
      } catch(Exception exc) {
          throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
          		"alteraByPedido()");
      }
  }
  
  public void deleta(Item_PedidoED ed) throws Excecoes{

    String sql = null;
    try{

      sql = "DELETE FROM Itens_Pedidos WHERE Oid_Item_Pedido = ";
      sql += "(" + ed.getOID_Item_Pedido() + ")";
      executasql.executarUpdate(sql);
      
    } catch(Exception exc) {
        throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
            "deleta()");
    }
  }

  public Item_PedidoED getByRecord(Item_PedidoED ed)throws Excecoes{

    String sql = null;
    ResultSet res = null;
    Item_PedidoED edVolta = new Item_PedidoED();

    try{
        sql = " SELECT * FROM Itens_Pedidos " +
              " WHERE 1 = 1";
        if (ed.getOID_Item_Pedido() > 0)
            sql +="  AND Itens_Pedidos.oid_Item_Pedido = '"+ed.getOID_Item_Pedido()+"'";
        else {
            if (ed.getOID_Pedido() > 0 && (doValida(ed.getCD_Produto()) || doValida(ed.getCD_Fornecedor())))
            {
                sql +="  AND Itens_Pedidos.oid_Pedido = '"+ed.getOID_Pedido()+"'" +
                      "  AND Itens_Pedidos.oid_Produto = Produtos.oid_Produto";
                if (doValida(ed.getCD_Produto()))
                    sql +="  AND Produtos.CD_Produto = '"+ed.getCD_Produto()+"'";
                if (doValida(ed.getCD_Fornecedor()))
                    sql +="  AND Produtos.CD_Fornecedor = '"+ed.getCD_Fornecedor()+"'";
            }
        }

        res = this.executasql.executarConsulta(sql);
        while (res.next())
        {
        	edVolta = new Item_PedidoED();
	        edVolta.setOID_Pedido(res.getLong("oid_Pedido"));
	        edVolta.setOID_Item_Pedido(res.getLong("oid_Item_Pedido"));
	        edVolta.setOID_Produto(res.getString("oid_Produto"));
	        edVolta.setOid_Promocao_Produto(res.getInt("oid_Promocao_Produto"));
	        edVolta.setOid_Preco_Produto(res.getInt("oid_Preco_Produto"));
	        edVolta.setOid_Localizacao(res.getInt("oid_Localizacao"));
	
	        edVolta.setDM_Quantidade(res.getString("DM_Quantidade"));
	        edVolta.setNR_Quantidade(res.getDouble("NR_Quantidade"));
	        edVolta.setNR_QT_Pedido(res.getDouble("NR_QT_Pedido"));
	        edVolta.setNR_QT_Atendido(res.getDouble("NR_QT_Atendido"));
	        edVolta.setNR_Peso_Pedido(res.getDouble("NR_Peso_Pedido"));
	        edVolta.setNR_Peso_Real(res.getDouble("NR_Peso_Real"));
	        edVolta.setNR_QT_Pendente(edVolta.getNR_QT_Pedido() - edVolta.getNR_QT_Atendido());
	        edVolta.setVL_Promocao(res.getDouble("VL_Promocao"));
	        edVolta.setVL_Item(res.getDouble("VL_Item"));
	        edVolta.setVL_Desconto(res.getDouble("VL_Desconto"));
	        edVolta.setVL_Produto(res.getDouble("VL_Produto"));
	        edVolta.setVL_IPI(res.getDouble("VL_IPI"));
	        edVolta.setVL_Desconto(res.getDouble("VL_Desconto"));
	        edVolta.setVL_Margem_Contribuicao(res.getDouble("VL_Margem_Contribuicao"));
            edVolta.setNR_QT_Troca(res.getDouble("NR_QT_Troca"));
            edVolta.setVL_Troca(res.getDouble("VL_Troca"));
	
	        edVolta.setPE_Aliquota_IPI(res.getDouble("PE_Aliquota_IPI"));
	        edVolta.setPE_Desconto(res.getDouble("PE_Desconto"));
	        edVolta.setPE_Margem_Contribuicao(res.getDouble("PE_Margem_Contribuicao"));
	
	        edVolta.setVL_Unitario(res.getDouble("VL_Unitario"));
	        edVolta.setVL_Unitario_Tabela(res.getDouble("VL_Unitario_Tabela"));
	        edVolta.setDM_Situacao(res.getString("DM_Situacao"));
        }
    } catch(Exception exc){
        throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "getByRecord()");
    } finally {
        super.closeResultset(res);
    }
    return edVolta;
  }

  public ArrayList lista(Item_PedidoED ed)throws Excecoes{

    String sql = null;
    ResultSet res = null;
    ArrayList list = new ArrayList();
    try {

      sql = " SELECT DISTINCT" +
            "     Pedidos.oid_Pedido" +
            "    ,Pedidos.NR_Pedido" +
            "    ,Pedidos.DT_Pedido" +
            "    ,Itens_Pedidos.oid_Item_Pedido" +
            "    ,Itens_Pedidos.oid_Preco_Produto" +
            "    ,Itens_Pedidos.oid_Promocao_Produto" +
            "    ,Itens_Pedidos.oid_Produto" +
            "    ,Itens_Pedidos.oid_Localizacao" +
            "    ,Itens_Pedidos.DM_Quantidade" +
            "    ,Itens_Pedidos.NR_Quantidade" +
            "    ,Itens_Pedidos.NR_QT_Pedido" +
            "    ,Itens_Pedidos.NR_QT_Atendido" +
            "    ,Itens_Pedidos.NR_Peso_Pedido" +
            "    ,Itens_Pedidos.NR_Peso_Real" +
            "    ,Itens_Pedidos.NR_QT_Troca" +
            "    ,Itens_Pedidos.VL_Troca" +
            "    ,Itens_Pedidos.VL_Promocao" +
            "    ,Itens_Pedidos.VL_Item" +
            "    ,Itens_Pedidos.VL_Desconto" +
            "    ,Itens_Pedidos.PE_Desconto" +
            "    ,Itens_Pedidos.VL_Produto" +
            "    ,Itens_Pedidos.VL_IPI" +
            "    ,Itens_Pedidos.VL_Unitario" +
            "    ,Itens_Pedidos.VL_Unitario_Tabela" +
            "    ,Itens_Pedidos.VL_Margem_Contribuicao" +
            "    ,Itens_Pedidos.PE_Margem_Contribuicao" +
            "    ,Itens_Pedidos.DM_Situacao" +
            "    ,Produtos.CD_Produto" +
            "    ,Produtos.NM_Produto" +
            "    ,Produtos.CD_Fornecedor" +
            "    ,Produtos.oid_Tipo_Produto" +
            " FROM Itens_Pedidos" +
            " WHERE Itens_Pedidos.OID_Produto = Produtos.oid_Produto " +
            "   AND Itens_Pedidos.OID_Pedido = Pedidos.OID_Pedido ";

        if (ed.getOID_Pedido() > 0)
            sql += " AND Pedidos.OID_Pedido = "+ed.getOID_Pedido();
        if (ed.getOID_Unidade() > 0)
            sql += " AND Pedidos.oid_Unidade = "+ed.getOID_Unidade();
        if (doValida(ed.getOID_Pessoa()))
            sql += " AND (Produtos.oid_pessoa = Fornecedores.oid_Pessoa OR (SELECT count(*) " +
                   "                                                        FROM Fornecedores_Produtos" +
                   "                                                        WHERE Fornecedores_Produtos.oid_Pessoa = Fornecedores.oid_pessoa" +
                   "                                                          AND Fornecedores_Produtos.oid_Produto = Produtos.oid_Produto) > 0) "+
                   " AND Fornecedores.oid_Pessoa = '"+ed.getOID_Pessoa()+"'";
        if (doValida(ed.getOID_Produto()))
            sql += " AND Produtos.oid_Produto = '"+ed.getOID_Produto()+"'";
        if (doValida(ed.getNM_Produto()))
            sql += " AND Produtos.NM_Produto LIKE '"+ed.getNM_Produto()+"%'";
        if (ed.getNR_Pedido() > 0)
            sql += " AND Pedidos.NR_Pedido = "+ed.getNR_Pedido();
        if (doValida(ed.getDM_Pedido()))
            sql += " AND Pedidos.DM_Pedido = '"+ed.getDM_Pedido()+"'";
        //*** SOMENTE PEDIDOS EM ABERTO
        if ("Pendente".equals(ed.getNR_QT_Listar()))
        {
            sql += " AND ((Itens_Pedidos.NR_QT_Atendido is null) OR (Itens_Pedidos.NR_QT_Pedido > Itens_Pedidos.NR_QT_Atendido))" +
            	   " AND Pedidos.DM_Situacao IN('N','A')";
        } else
        if ("Pesagem".equals(ed.getNR_QT_Listar()))
        {
            sql += " AND Produtos.oid_Unidade_Produto = Unidades_Produtos.oid_Unidade_Produto" +
                   " AND Unidades_Produtos.DM_Pesagem = 'S'" +
                   " AND Itens_Pedidos.DM_Situacao = 'N'" +
                   " AND Itens_Pedidos.NR_QT_Atendido > 0";
        }
        sql +=" ORDER BY Pedidos.NR_Pedido, Produtos.NM_Produto";

        res = this.executasql.executarConsulta(sql);
        while (res.next())
        {
	        Item_PedidoED edVolta = new Item_PedidoED();
	
	        edVolta.setOid_Preco_Produto(res.getInt("oid_Preco_Produto"));	
	        edVolta.setOid_Promocao_Produto(res.getInt("oid_Promocao_Produto"));
	        edVolta.setOID_Produto(res.getString("oid_Produto"));
	        edVolta.setOid_Tipo_Produto(res.getInt("oid_Tipo_Produto"));
	        edVolta.setOid_Localizacao(res.getInt("oid_Localizacao"));
	        edVolta.setCD_Produto(res.getString("cd_Produto"));
	        edVolta.setNM_Produto(res.getString("nm_Produto"));
	        edVolta.setCD_Fornecedor(res.getString("cd_fornecedor"));
	
	        edVolta.setOID_Item_Pedido(res.getLong("oid_Item_Pedido"));
	        edVolta.setNR_Pedido(res.getLong("NR_Pedido"));
	        edVolta.setDM_Quantidade(res.getString("DM_Quantidade"));
	        edVolta.setNR_Quantidade(res.getDouble("NR_Quantidade"));
	        edVolta.setNR_QT_Pedido(res.getDouble("NR_QT_Pedido"));
	        edVolta.setNR_QT_Atendido(res.getDouble("NR_QT_Atendido"));
	        edVolta.setNR_Peso_Pedido(res.getDouble("NR_Peso_Pedido"));
	        edVolta.setNR_Peso_Real(res.getDouble("NR_Peso_Real"));
	        edVolta.setNR_QT_Pendente(edVolta.getNR_QT_Pedido() - edVolta.getNR_QT_Atendido());
            edVolta.setNR_QT_Troca(res.getDouble("NR_QT_Troca"));
            edVolta.setVL_Troca(res.getDouble("VL_Troca"));
	
	        edVolta.setVL_Promocao(res.getDouble("VL_Promocao"));
	        edVolta.setVL_Item(res.getDouble("VL_Item"));
	        edVolta.setVL_Desconto(res.getDouble("VL_Desconto"));
	        edVolta.setPE_Desconto(res.getDouble("PE_Desconto"));
	        edVolta.setVL_Produto(res.getDouble("VL_Produto"));
	        edVolta.setVL_IPI(res.getDouble("VL_IPI"));
	        edVolta.setVL_Unitario(res.getDouble("VL_Unitario"));
	        edVolta.setVL_Unitario_Tabela(res.getDouble("VL_Unitario_Tabela"));
	        edVolta.setVL_Margem_Contribuicao(res.getDouble("VL_Margem_Contribuicao"));
	        edVolta.setPE_Margem_Contribuicao(res.getDouble("PE_Margem_Contribuicao"));

	        edVolta.setOID_Pedido(res.getInt("oid_Pedido"));
	        edVolta.setDT_Pedido(dataFormatada.getDT_FormataData(res.getString("dt_Pedido")));
	        edVolta.setDM_Situacao(res.getString("DM_Situacao"));
	        
	        list.add(edVolta);
        }
        return list;
    } catch(Exception exc){
        throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "lista()");
    } finally {
        super.closeResultset(res);
    }
  }
  
  //*** Recebidos e Faturados 
  public ArrayList lista_Recebidos(Item_PedidoED ed)throws Excecoes{

      String sql = null;
      ResultSet res = null;
      ArrayList list = new ArrayList();
      try {

        sql = " SELECT * " +
        	  " FROM Itens_Pedidos, Itens_notas_fiscais, Produtos, Pedidos " +
              " WHERE  Itens_Pedidos.OID_Produto = Produtos.oid_Produto " +
              " AND    Itens_Pedidos.OID_Pedido  = Pedidos.OID_Pedido " +
              " AND    Itens_Pedidos.OID_Pedido = Itens_notas_fiscais.oid_pedido " +
              " AND	   Itens_notas_fiscais.oid_item_pedido = Itens_Pedidos.oid_item_pedido ";
          if (ed.getOID_Pedido() > 0)
              sql += " AND Pedidos.OID_Pedido = " + ed.getOID_Pedido();
    
          res = this.executasql.executarConsulta(sql);
          while (res.next())
          {
  	     	Item_PedidoED edVolta = new Item_PedidoED();
  	
  	     	edVolta.setOid_Preco_Produto(res.getInt("oid_Preco_Produto"));	
	        edVolta.setOid_Promocao_Produto(res.getInt("oid_Promocao_Produto"));
	        edVolta.setOid_Item_Nota_Fiscal(res.getInt("Oid_Item_Nota_Fiscal"));
	        edVolta.setOid_Localizacao(res.getInt("oid_Localizacao"));
  	        edVolta.setCD_Produto(res.getString("cd_Produto"));
  	        edVolta.setNM_Produto(res.getString("nm_Produto"));
  	        edVolta.setCD_Fornecedor(res.getString("cd_fornecedor"));
  	
  	        edVolta.setOID_Item_Pedido(res.getLong("oid_Item_Pedido"));
  	        edVolta.setNR_Pedido(res.getLong("NR_Pedido"));
  	        edVolta.setDM_Quantidade(res.getString("DM_Quantidade"));
	        edVolta.setNR_Quantidade(res.getDouble("NR_Quantidade"));
  	        edVolta.setNR_QT_Pedido(res.getDouble("NR_QT_Pedido"));
  	        edVolta.setNR_QT_Atendido(res.getDouble("NR_QT_Atendido"));
  	        edVolta.setNR_Peso_Pedido(res.getDouble("NR_Peso_Pedido"));
  	        edVolta.setNR_Peso_Real(res.getDouble("NR_Peso_Real"));
  	        edVolta.setNR_QT_Pendente(edVolta.getNR_QT_Pedido() - edVolta.getNR_QT_Atendido());
  	        edVolta.setNR_QT_Troca(res.getDouble("NR_QT_Troca"));
  	        edVolta.setVL_Troca(res.getDouble("VL_Troca"));
  	
  	        edVolta.setVL_Promocao(res.getDouble("VL_Promocao"));
  	        edVolta.setVL_Item(res.getDouble("VL_Item"));
  	        edVolta.setVL_Desconto(res.getDouble("VL_Desconto"));
  	        edVolta.setVL_Produto(res.getDouble("VL_Produto"));
  	        edVolta.setVL_IPI(res.getDouble("VL_IPI"));
  	        edVolta.setVL_Desconto(res.getDouble("VL_Desconto"));
  	        edVolta.setVL_Unitario(res.getDouble("VL_Unitario"));

  	        edVolta.setDT_Pedido(res.getString("dt_Pedido"));
  	        edVolta.setDT_Pedido(dataFormatada.getDT_FormataData(res.getString("dt_Pedido")));
  	        
  	        //*** Nota Fiscal
  	        edVolta.setOid_Nota_Fiscal(res.getString("oid_Nota_Fiscal"));  	        
  	        edVolta.setNR_Nota_Fiscal(getTableIntValue("NR_Nota_Fiscal",
  	                								   "Notas_Fiscais",
  	                								   "oid_Nota_Fiscal = '"+edVolta.getOid_Nota_Fiscal()+"'"));  	        
  	        list.add(edVolta);
          }
      } catch(Exception exc){
          throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "lista_Recebidos()");
      } finally {
          super.closeResultset(res);
      }
      return list;
    }
  
  	//*** Atualiza a Situacao do Item do Pedido (A ou N=Aberto , F=Finalizado, C=Cancelado)
  	public void atualizaSituacaoItemPedido(Item_PedidoED ed) throws Excecoes {

  	    String sql = null;
  	    try {
	  	    if (!doValida(ed.getDM_Situacao()))
	  	        throw new Excecoes("Situacão do Item Pedido não informada!");
	  	    if (ed.getOID_Item_Pedido() < 1)
	  	        throw new Excecoes("Item Pedido não informado!");
	      
	  	    if (!"C".equals(ed.getDM_Situacao()))
            {
	  	        sql = " UPDATE Itens_Pedidos SET " +
	  	        	  "		DM_Situacao = '"+ed.getDM_Situacao()+"'" +
	  	        	  " WHERE oid_Item_Pedido ="+ed.getOID_Item_Pedido();
	  	        executasql.executarUpdate(sql);
	  	    } else altera(new Item_PedidoED(ed.getOID_Item_Pedido(), 0, ed.getDM_Situacao()));
            
  	    } catch(Exception exc){
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
            		"atualizaSituacaoItemPedido()");
        }
  	}
  	
  	//*** Recalcula preço dos itens pela Condição de Pagamento e Tabela de Preços!
  	//    Obs.: Tbm existente em Item_Nota_Fiscal_TransacoesED: recalculaPrecoItensNota()
  	public void recalculaPrecoItensPedido(PedidoED ed, boolean byTabela, boolean byCondicao) throws Excecoes {

  	    String sql = null;
  	    try {
  	        
  	        if (ed.getOID_Pedido() < 1)
  	            throw new Excecoes("Pedido não informado!");
	        if (ed.getOID_Condicao_Pagamento() < 1)
	            throw new Mensagens("Condição de Pagamento não informada!");
  	        
  	        ArrayList lista = this.lista(new Item_PedidoED(ed.getOID_Pedido()));
  	        for (int i=0; i < lista.size(); i++)
            {
  	            Item_PedidoED edItem = (Item_PedidoED) lista.get(i);
  	            if ("C".equals(edItem.getDM_Situacao()))
  	                return;
  	            
    	        ProdutoED edProduto = (ProdutoED) new ProdutoBD(executasql).getByRecord(new ProdutoED(edItem.getOID_Produto()));
                
                /** by TABELA DE PREÇOS **/
                if (byTabela)
                    edProduto.edPreco = new Pro004Bean().getPrecoByTabela((int)ed.getOID_Condicao_Pagamento(), edProduto.getOID_Produto(), ed.getOid_Tabela_Venda());
                else
    	        /** by CONDIÇÃO DE PAGAMENTO (mesma Tabela) **/
                if (byCondicao)
                    edProduto.edPreco = new Pro004Bean().getPrecoByOIDCondPagamento((int)ed.getOID_Condicao_Pagamento(), edItem.getOid_Preco_Produto());
        		
        		//*** Converte valores em variáveis numéricas
                boolean existPreco = edProduto.edPreco.getOID_Preco_Produto() > 0;
        		
                /** Se não existe o ITEM NA TABELA, ZERA QUANTIDADE DO MESMO **/
                if (!existPreco)
                    edItem.setNR_QT_Atendido(0);
                edItem.setVL_Unitario(edProduto.edPreco.getVL_Venda());
                edItem.setVL_Unitario_Tabela(edProduto.edPreco.getVL_Venda());
                
                edItem.setOID_Vendedor(ed.getOID_Vendedor());
                Item_PedidoRN.calculaProduto(edItem, true);
                
            	//*** Grava Valor do Item
            	sql = " UPDATE Itens_Pedidos SET" +
            		  "		 VL_Item = " + Valor.round(edItem.getVL_Item()) +
            		  "		,VL_Desconto = " + Valor.round(edItem.getVL_Desconto()) +
            		  "		,VL_Produto = " + Valor.round(edItem.getVL_Produto()) +
            		  "		,VL_Promocao = " + Valor.round(edItem.getVL_Promocao()) +
            		  "		,VL_Unitario = " + Valor.round(edItem.getVL_Unitario()) + 
            		  "		,VL_Unitario_Tabela = " + Valor.round(edItem.getVL_Unitario_Tabela()) +
            		  "		,VL_Margem_Contribuicao = " + Valor.round(edItem.getVL_Margem_Contribuicao()) +
                      "     ,NR_QT_Atendido = "+(existPreco ? edItem.getNR_QT_Atendido() > 0 ? edItem.getNR_QT_Atendido() : edItem.getNR_QT_Pedido() : 0) +    
                      "     ,oid_Preco_Produto = " + edProduto.edPreco.getOID_Preco_Produto();
            	if (!existPreco)
                    sql += "     ,Usuario_Stamp = "+getSQLString(ed.getUsuario_Stamp())+
                           "     ,DT_Stamp = "+getSQLDate(Data.getDataDMY())+
                           "     ,DM_Stamp = 'R'";
            	sql += " WHERE oid_Item_Pedido = " + edItem.getOID_Item_Pedido();
            	executasql.executarUpdate(sql);
            }
  	    } catch(Exception exc){
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
            		"recalculaPrecoItensPedido()");
        }
  	}
}