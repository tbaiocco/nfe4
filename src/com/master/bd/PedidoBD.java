package com.master.bd;

/**
 * @author: André Valadas
*/

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import com.master.ed.EntregadorED;
import com.master.ed.Item_Nota_Fiscal_TransacoesED;
import com.master.ed.Item_PedidoED;
import com.master.ed.Modelo_Nota_FiscalED;
import com.master.ed.Nota_Fiscal_EletronicaED;
import com.master.ed.PedidoED;
import com.master.ed.Pedido_Nota_FiscalED;
import com.master.iu.Ped002Bean;
import com.master.iu.ProdutoBean;
import com.master.root.FormataDataBean;
import com.master.root.VendedorBean;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Valor;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;

public class PedidoBD extends BancoUtil {

    private ExecutaSQL executasql;
	FormataDataBean dataFormatada = new FormataDataBean();
	Parametro_FixoED parametro_FixoED = Parametro_FixoED.getInstancia();

	public PedidoBD(ExecutaSQL sql) {
	    super(sql);
	    this.executasql = sql;
	}

  	public PedidoED inclui(PedidoED ed) throws Excecoes{

  	    String sql = null;
  	    int nrPedido = 0;
  	    try {
	  	    //*** Auto Incremento
  	        ed.setOID_Pedido(JavaUtil.truncInverse(System.currentTimeMillis(), 9));
	  	    
	  	    //*** Busca referencia para Nº do Pedido
  	        nrPedido = getTableIntValue("C".equals(ed.getDM_Pedido()) ? "NR_PROXIMO_PEDIDO_COMPRA" : "NR_PROXIMO_PEDIDO_VENDA",
	  	               			         "Parametros_Filiais",
	  	               			         "oid_Unidade = "+ ed.getOID_Unidade()); 
	  	    //*** Caso 1º seja NULL no banco, incrementa manualmente em seguida
	  	    ++nrPedido;
	  	    
		    sql = " INSERT INTO Pedidos (" +
		    	  "		 OID_Pedido" +
		    	  "		,DM_Pedido" +
		    	  "		,DT_Previsao_Entrega" +
		    	  "		,Dt_Stamp" +
		    	  "		,OID_Pessoa" +
		    	  "		,OID_Condicao_Pagamento" +
		    	  "		,OID_Unidade" +
		    	  "		,HR_Pedido" +
		    	  "		,DT_Pedido" +
		    	  "		,NR_Pedido" +
		    	  "		,NR_Pedido_Palm" +
		    	  "		,VL_Total_Pedido" +
		    	  "		,VL_Total_Desconto" +
		    	  "		,OID_Tipo_Pedido" +
		    	  "		,NM_Comprador" +
		    	  "		,NM_Vendedor" +
		    	  "		,TX_Observacao" +
		    	  "		,DM_Frete" +
		    	  "		,oid_pessoa_distribuidor" +
		    	  "		,DM_Situacao" +
		    	  "		,oid_Vendedor" +
		    	  "		,oid_Entregador" +
		    	  "		,oid_Natureza_Operacao" +
                  "     ,oid_Tabela_Venda" +
		    	  "		,DM_Meio_Pagamento" +
		    	  "		,DM_Critica_Financeira" +
		    	  "		,DM_Critica_Estoque" +
		    	  "		,NR_Nota_Fiscal" +
		    	  "		,NM_Serie" +
		    	  "		,oid_Modelo_Nota_Fiscal" +
		    	  "		,DT_Faturamento)";
		    sql+= " VALUES ( "+
		    	  ed.getOID_Pedido() + 
		    	  ",'"+ ed.getDM_Pedido() + "'" +
		    	  ",'"+ed.getDT_Previsao_Entrega()+ "'" +
		    	  ",'"+ Data.getDataDMY()+ "'" +
		    	  ",'" + ed.getOID_Pessoa() + "'" +
		    	  "," + ed.getOID_Condicao_Pagamento() +
		    	  "," + ed.getOID_Unidade() +
		    	  ",'" + ed.getHR_Pedido() + "'" +
		    	  ",'" + ed.getDT_Pedido() + "'" +
		    	  "," + nrPedido +
		    	  ",'" + ed.getNR_Pedido_Palm()+ "'" +
		    	  "," + ed.getVL_Total_Pedido() + 
		    	  "," + ed.getVL_Total_Desconto() + 
		    	  "," + ed.getOID_Tipo_Pedido() +
		    	  ",'" + ed.getNM_Comprador()+ "'" +
		    	  ",'" + ed.getNM_Vendedor()+ "'" +
		    	  ",'" + ed.getTX_Observacao() +  "'" +
		    	  ",'" +getValueDef(ed.getDM_Frete(), "C")+"'" +
		    	  ",'"+ ed.getOid_Pessoa_Distribuidor() + "'" +
		    	  ",'"+getValueDef(ed.getDM_Situacao(), "A")+"'" +
		    	  ",'"+ed.getOID_Vendedor()+"'" +
		    	  ","+ed.getOid_Entregador()+
		    	  ","+ed.getOid_Natureza_Operacao()+
                  ","+ed.getOid_Tabela_Venda()+
		    	  ",'"+getValueDef(ed.getDM_Meio_Pagamento(), "1")+"'" +
		    	  ",'"+getValueDef(ed.getDM_Critica_Financeira(), "N")+"'" +
		    	  ",'"+getValueDef(ed.getDM_Critica_Estoque(), "N")+"'" +
		    	  ","+ed.getNR_Nota_Fiscal()+
		    	  ",'"+getValueDef(ed.getNM_Serie(), "")+"'" +
		    	  ","+ed.getOid_Modelo_Nota_Fiscal()+
		    	  ",'"+ed.getDT_Faturamento()+"')";
	      	executasql.executarUpdate(sql);
	
	      	sql="UPDATE Parametros_Filiais SET "+("C".equals(ed.getDM_Pedido()) ? "NR_PROXIMO_PEDIDO_COMPRA" : "NR_PROXIMO_PEDIDO_VENDA")+" = "+nrPedido+" WHERE oid_Unidade = "  + ed.getOID_Unidade();
	      	executasql.executarUpdate(sql);
            return ed;            
            
  		} catch(Exception exc){
  		    throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
  		    		"inclui()");
  		}
  	}
  	
  	public void altera(PedidoED ed) throws Excecoes {

  	    String sql = null;
  	    try{
  	        sql = " UPDATE Pedidos SET ";
  	        sql += " DT_Previsao_Entrega= '" + ed.getDT_Previsao_Entrega() + "', ";
  	        sql += " NM_Comprador= '" + ed.getNM_Comprador() + "',";
  	        sql += " NM_Vendedor= '" + ed.getNM_Vendedor() + "',";
  	        sql += " TX_Observacao= '" + ed.getTX_Observacao() + "',";
  	        sql += " DM_Pedido= '" + ed.getDM_Pedido() + "',";
  	        sql += " oid_Pessoa= '" + ed.getOID_Pessoa() + "', ";
  	        sql += " oid_Vendedor= '" + ed.getOID_Vendedor() + "', ";
  	        sql += " DT_Pedido= '" + ed.getDT_Pedido() + "', ";
  	        sql += " oid_Tabela_Venda= " + ed.getOid_Tabela_Venda() + ", ";
  	        sql += " OID_Condicao_Pagamento= " + ed.getOID_Condicao_Pagamento() + ", ";
  	        sql += " OID_Unidade= " + ed.getOID_Unidade() + ", ";
  	        sql += " OID_Tipo_Pedido= " + ed.getOID_Tipo_Pedido() + ", ";
  	        sql += " VL_Frete= " + ed.getVL_Frete() + ", ";
  	        sql += " VL_Total_Troca= " + ed.getVL_Total_Troca() + ", ";
  	        sql += " DM_Frete= '" +getValueDef(ed.getDM_Frete(), "C")+ "', ";
  	        sql += " DM_Meio_Pagamento= '" + ed.getDM_Meio_Pagamento() + "', ";
  	        sql += " NR_Nota_Fiscal= " + ed.getNR_Nota_Fiscal() + ", ";
  	        sql += " NM_Serie= '" + getValueDef(ed.getNM_Serie(), "") + "', ";
  	        sql += " oid_Modelo_Nota_Fiscal=" + ed.getOid_Modelo_Nota_Fiscal() + ", ";
  	        sql += " oid_Entregador=" + ed.getOid_Entregador() + ", ";
  	        sql += " oid_Natureza_Operacao=" + ed.getOid_Natureza_Operacao() + ", ";
  	        sql += " oid_pessoa_distribuidor = '" + ed.getOid_Pessoa_Distribuidor() + "'";
  	        sql += " WHERE OID_Pedido = " + ed.getOID_Pedido();

  	        //*** Sempre q Alterar esse Campos no Pedido de VENDA, SETAR CRITICA FINANCEIRA COMO "N"=NÃO VARIFICADA
  	        if ("V".equals(ed.getDM_Pedido()))
            {
  	            String dmCriticaFinanceira = getTableStringValue("DM_Meio_Pagamento","Pedidos","oid_Pedido="+ed.getOID_Pedido());
  	            String dmMeioPagamento = getTableStringValue("DM_Meio_Pagamento","Pedidos","oid_Pedido="+ed.getOID_Pedido());
  	            long oidCondicaoPagamento = getTableIntValue("oid_Condicao_Pagamento","Pedidos","oid_Pedido="+ed.getOID_Pedido());
  	            long oidTipoPedido = getTableIntValue("oid_Tipo_Pedido","Pedidos","oid_Pedido="+ed.getOID_Pedido());
              
  	            if (!"N".equals(dmCriticaFinanceira) && (!ed.getDM_Meio_Pagamento().equals(dmMeioPagamento) || ed.getOID_Condicao_Pagamento() != oidCondicaoPagamento || ed.getOID_Tipo_Pedido() != oidTipoPedido))
                {
  	                this.atualizaCriticaFinanceira(ed.getOID_Pedido(), "N");
  	            }
  	            //*** Recalcula preço dos itens
                //    Por Condição de Pagamento, ou por Tabela de Preços 
                if (ed.isUpdatePrecosByTabela() || ed.isUpdatePrecosByCond() && new Ped002Bean().doExisteItensPedido(String.valueOf(ed.getOID_Pedido())))
                    new Item_PedidoBD(executasql).recalculaPrecoItensPedido(ed, ed.isUpdatePrecosByTabela(), ed.isUpdatePrecosByCond());
  	        }
  	        executasql.executarUpdate(sql);
  	        this.total_Pedido(ed.getOID_Pedido());
            
  	    } catch(Exception exc){
  	        throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
  	        		"altera()");
  	    }
  	}

  	public void deleta(PedidoED ed) throws Excecoes{

  	    String sql = null;
  	    try{
  	        if ("D".equals(ed.getDM_Situacao()))
            {
  	            sql = " DELETE FROM Pedidos " +
  	            	  " WHERE Oid_Pedido = "+ ed.getOID_Pedido();
  	            executasql.executarUpdate(sql);
  	        } else if ("C".equals(ed.getDM_Situacao()))
  	            this.atualizaSituacaoPedido(ed.getOID_Pedido(), "C");
  	        else throw new Excecoes("Situação não informada[Cancela ou Deleta]!");

  	        sql = " DELETE FROM Itens_Pedidos " +
  	        	  " WHERE Oid_Pedido = "+ ed.getOID_Pedido();
  	        executasql.executarUpdate(sql);
  	    
        } catch(Exception exc){
  	        throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
  	                "deleta()");
  	    }
  	}
  
  	public ArrayList lista(PedidoED ed)throws Excecoes {

  	    String sql = null;
  	    ArrayList list = new ArrayList();

  	    try {
  	      sql = " SELECT  Pedidos.*" +
    			"		 ,Pessoas.NR_CNPJ_CPF" +
                "        ,Pessoas.NM_Razao_Social" +
    			"		 ,Condicoes_Pagamentos.CD_Condicao_Pagamento" +
                "        ,Condicoes_Pagamentos.NM_Condicao_Pagamento" +
    			"		 ,Tipos_Pedidos.CD_Tipo_Pedido" +
                "        ,Tipos_Pedidos.NM_Tipo_Pedido" +
                "        ,Tipos_Pedidos.DM_Nota_Fiscal" +
    			"		 ,Unidades.CD_Unidade" +
    			"        ,dis.nr_cnpj_cpf as cd_distribuidor" +
    			"		 ,dis.nm_razao_social as nm_distribuidor " +
    			" FROM Pedidos, Pessoas, Condicoes_Pagamentos, Tipos_Pedidos, Unidades, Pessoas dis " +
    			" WHERE Pedidos.OID_Pessoa = Pessoas.OID_Pessoa "+
    			"   AND Pedidos.OID_Condicao_Pagamento = Condicoes_Pagamentos.OID_Condicao_Pagamento " +
    			"   AND Pedidos.OID_Tipo_Pedido = Tipos_Pedidos.OID_Tipo_Pedido " +
    			"   AND Pedidos.OID_Unidade = Unidades.OID_Unidade " +
    			"   AND Pedidos.oid_pessoa_distribuidor = dis.oid_pessoa";    
            //*** ID
    		if (ed.getOID_Pedido() > 0)
  		       	sql += " AND Pedidos.oid_Pedido = " + ed.getOID_Pedido();
            else {
      		    if (doValida(ed.getOID_Pessoa()))
      		        sql += " AND Pedidos.oid_Pessoa = '" + ed.getOID_Pessoa() +"'";
      		    if (doValida(ed.getOid_Pessoa_Distribuidor()))
    		        sql += " AND Pedidos.oid_Pessoa_Distribuidor = '" + ed.getOid_Pessoa_Distribuidor() +"'";
      		    if (ed.getOid_Entregador() > 0)
      		        sql += " AND Pedidos.oid_Entregador = " + ed.getOid_Entregador();
                if (ed.getOID_Tipo_Pedido() > 0)
                    sql += " AND Pedidos.oid_Tipo_Pedido = " + ed.getOID_Tipo_Pedido();
      		    //*** Range do Numero do Pedido
      		    if (ed.getNR_Pedido() > 0 && ed.getNR_Pedido_Final() > 0)
      		        sql += " AND Pedidos.NR_Pedido between " + ed.getNR_Pedido() +" AND " +ed.getNR_Pedido_Final();
      		    else if (ed.getNR_Pedido() > 0 )
      		        sql += " AND Pedidos.NR_Pedido = " + ed.getNR_Pedido();
      		    else if (ed.getNR_Pedido_Final() > 0 )
      		        sql += " AND Pedidos.NR_Pedido = " + ed.getNR_Pedido_Final();
                if (doValida(ed.getNumeros_Pedido()))
                    sql += " AND Pedidos.NR_Pedido IN ("+ed.getNumeros_Pedido()+")";
      		    if (doValida(ed.getNR_Pedido_Palm()))
                    sql += " AND Pedidos.NR_Pedido_Palm = " + getSQLString(ed.getNR_Pedido_Palm());
      		    if ("S".equals(ed.getDM_Sem_Entregador()))
      		        sql += " AND (Pedidos.oid_entregador = 0 OR Pedidos.oid_entregador IS NULL)";
      		    //*** Se deve Filtrar Aprovados, Cancelados, Finalizados
      		    if ("N".equals(ed.getDM_Filtrar_ACF()))
    		        sql += " AND Pedidos.DM_Situacao NOT IN('A', 'F', 'C')";
      		    if (doValida(ed.getOID_Vendedor()))
      		        sql += " AND Pedidos.oid_Vendedor = '" + ed.getOID_Vendedor() +"'";
      		    if (doValida(ed.getOID_Produto()))
                {
      		        sql += " AND Itens_Pedidos.oid_Produto = '" + ed.getOID_Produto() +"'" +
      		        	   " AND Itens_Pedidos.oid_Pedido = pedidos.oid_Pedido";	
      		    }
      		    //*** Data do Pedido
                if (doValida(ed.getDT_Pedido_Inicial()) && doValida(ed.getDT_Pedido_Final())) {
                    sql +=" AND Pedidos.DT_Pedido BETWEEN '"+ed.getDT_Pedido_Inicial()+"' AND '"+ed.getDT_Pedido_Final()+"'";
                } else if (doValida(ed.getDT_Pedido_Inicial()) || doValida(ed.getDT_Pedido_Final())) {
                    sql +=" AND Pedidos.DT_Pedido = '"+(doValida(ed.getDT_Pedido_Inicial()) ? ed.getDT_Pedido_Inicial() : ed.getDT_Pedido_Final())+"'";
                }
                //*** Data do Previsão
                if (doValida(ed.getDT_Previsao_Inicial()) && doValida(ed.getDT_Previsao_Final())) {
                    sql +=" AND Pedidos.DT_Previsao_Entrega BETWEEN '"+ed.getDT_Previsao_Inicial()+"' AND '"+ed.getDT_Previsao_Final()+"'";
                } else if (doValida(ed.getDT_Previsao_Inicial()) || doValida(ed.getDT_Previsao_Final())) {
                    sql +=" AND Pedidos.DT_Previsao_Entrega = '"+(doValida(ed.getDT_Previsao_Inicial()) ? ed.getDT_Previsao_Inicial() : ed.getDT_Previsao_Final())+"'";
                }
                if (doValida(ed.getDM_Situacao()))
      		        sql += " AND Pedidos.DM_Situacao = '" + ed.getDM_Situacao() +"'";
                if (doValida(ed.getDM_Critica_Financeira()))
                {
                    if ("X".equals(ed.getDM_Critica_Financeira()))
                        sql +=" AND Pedidos.DM_Critica_Financeira IN ('A','L')";
                    else sql +=" AND Pedidos.DM_Critica_Financeira = '"+ed.getDM_Critica_Financeira()+"'";
                }
                if (doValida(ed.getDM_Critica_Estoque()))
                {
                    if ("X".equals(ed.getDM_Critica_Estoque()))
                        sql +=" AND Pedidos.DM_Critica_Estoque IN ('A','L')";
                    else sql +=" AND Pedidos.DM_Critica_Estoque = '"+ed.getDM_Critica_Estoque()+"'";
                }
                if (doValida(ed.getDM_Pedido()))
                    sql += " AND Pedidos.DM_PEDIDO = '"+ed.getDM_Pedido()+"'";
      		    if ("V".equals(ed.getDM_Pedido()))
      		        sql += " ORDER BY Pedidos.oid_Entregador, Pedidos.NR_Pedido";
      		    else sql += " ORDER BY Pedidos.NR_Pedido";
            }
  		
  		    ResultSet res = this.executasql.executarConsulta(sql);
            try {
      		    while (res.next())
                {
      				PedidoED edVolta = new PedidoED();
      				edVolta.setOID_Pedido(res.getLong("oid_Pedido"));
      				if ("V".equals(ed.getDM_Pedido()))
                    {
      				    edVolta.setOid_Entregador(res.getInt("oid_Entregador"));
                        edVolta.setOid_Veiculo(res.getString("oid_Veiculo"));
      				    if (edVolta.getOid_Entregador() > 0)
                        {
      				        EntregadorED edEntregador = new EntregadorBD(executasql).getByRecord(new EntregadorED(edVolta.getOid_Entregador()));
      				        edVolta.setCD_Entregador(edEntregador.getCD_Entregador());
      				        edVolta.setNM_Entregador(edEntregador.getNM_Entregador());
      				    }
      				    edVolta.setOid_Natureza_Operacao(res.getInt("oid_Natureza_Operacao"));
                        if (edVolta.getOid_Natureza_Operacao() > 0)
                        {
                            edVolta.setCD_Natureza_Operacao(getTableStringValue("CD_Natureza_Operacao","Naturezas_Operacoes","oid_Natureza_Operacao="+edVolta.getOid_Natureza_Operacao()));
                            edVolta.setNM_Natureza_Operacao(getTableStringValue("NM_Natureza_Operacao","Naturezas_Operacoes","oid_Natureza_Operacao="+edVolta.getOid_Natureza_Operacao()));
                        }      				    
      				    edVolta.setOid_Modelo_Nota_Fiscal(res.getInt("oid_Modelo_Nota_Fiscal"));
      				    if (edVolta.getOid_Modelo_Nota_Fiscal() > 0)
      				        edVolta.edModelo = new Modelo_Nota_FiscalBD(executasql).getByRecord(new Modelo_Nota_FiscalED(edVolta.getOid_Modelo_Nota_Fiscal()));
      				    edVolta.setDM_Situacao_Cliente(getTableStringValue("DM_Credito", "Clientes", "oid_Cliente = "+getSQLString(res.getString("oid_Pessoa"))));
      				}
      				edVolta.setDM_Pedido(res.getString("DM_Pedido"));
      				edVolta.setNM_Comprador(res.getString("NM_Comprador"));
      				edVolta.setNM_Vendedor(res.getString("NM_Vendedor"));
      				edVolta.setTX_Observacao(res.getString("TX_Observacao"));
      				edVolta.setDT_Faturamento(dataFormatada.getDT_FormataData(res.getString("DT_Faturamento")));
      				edVolta.setDM_Meio_Pagamento(res.getString("DM_Meio_Pagamento"));
      				edVolta.setDM_Frete(res.getString("DM_Frete"));
      				edVolta.setDM_Situacao(res.getString("DM_Situacao"));
      				edVolta.setDM_Critica_Financeira(res.getString("DM_Critica_Financeira"));
      				edVolta.setDM_Critica_Estoque(res.getString("DM_Critica_Estoque"));
      				edVolta.setNR_Pedido(res.getLong("NR_Pedido"));
      				edVolta.setNR_Pedido_Palm(res.getString("NR_Pedido_Palm"));
      				edVolta.setNR_Nota_Fiscal(res.getInt("NR_Nota_Fiscal"));
      				edVolta.setNM_Serie(res.getString("NM_Serie"));
      				
      				edVolta.setVL_Total_IPI(res.getDouble("VL_Total_IPI"));
      				edVolta.setVL_Total_Troca(res.getDouble("VL_Total_Troca"));
      				edVolta.setVL_Total_Pedido(res.getDouble("VL_Total_Pedido"));
      				edVolta.setVL_Total_Item(res.getDouble("VL_Total_Item"));
      				edVolta.setVL_Total_Desconto(res.getDouble("VL_Total_Desconto"));
      				edVolta.setVL_Total_Produto(res.getDouble("VL_Total_Produto"));
      				edVolta.setVL_Frete(res.getDouble("VL_Frete"));
      				edVolta.setHR_Pedido(res.getString("HR_Pedido"));
      				dataFormatada.setDT_FormataData(res.getString("DT_Pedido"));
      				edVolta.setDT_Pedido(dataFormatada.getDT_FormataData());
      				dataFormatada.setDT_FormataData(res.getString("DT_Previsao_Entrega"));
      				edVolta.setDT_Previsao_Entrega(dataFormatada.getDT_FormataData());
                    edVolta.setOID_Pessoa(res.getString("OID_Pessoa"));
                    edVolta.setNR_CNPJ_CPF(res.getString("NR_CNPJ_CPF"));
      				edVolta.setNM_Razao_Social(res.getString("NM_Razao_Social"));
      				edVolta.setOID_Unidade(res.getInt("OID_Unidade"));
      				edVolta.setCD_Unidade(res.getString("CD_Unidade"));
                    edVolta.setOid_Tabela_Venda(res.getInt("oid_Tabela_Venda"));
      				edVolta.setOID_Condicao_Pagamento(res.getInt("OID_Condicao_Pagamento"));
      				edVolta.setCD_Condicao_Pagamento(res.getString("CD_Condicao_Pagamento"));
      				edVolta.setNM_Condicao_Pagamento(res.getString("NM_Condicao_Pagamento"));
      				edVolta.setOID_Tipo_Pedido(res.getInt("OID_Tipo_Pedido"));
      				edVolta.setCD_Tipo_Pedido(res.getString("CD_Tipo_Pedido"));
      				edVolta.setNM_Tipo_Pedido(res.getString("NM_Tipo_Pedido"));
      				edVolta.setDM_Nota_Fiscal(res.getString("DM_Nota_Fiscal"));
      				
      				edVolta.setOid_Pessoa_Distribuidor(res.getString("oid_pessoa_distribuidor"));
      				edVolta.setCD_Distribuidor(res.getString("cd_distribuidor"));
      				edVolta.setNM_Distribuidor(res.getString("nm_distribuidor"));
      				
      				if (doValida(res.getString("oid_Vendedor")))
                    {
      				    VendedorBean edVendedor = VendedorBean.getByOID_Vendedor(res.getString("oid_Vendedor"));
      				    edVolta.setOID_Vendedor(res.getString("oid_Vendedor"));
      				    edVolta.setCD_Vendedor(edVendedor.getCD_Vendedor());
      				    edVolta.setNM_Vendedor(edVendedor.getNM_Razao_Social());
      				}
      				list.add(edVolta);
      		    }
            } finally {
                this.closeResultset(res);
            }
  		    return list;
  	    } catch(Exception exc){
  	        throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
					"lista()");
  	    }
    }
    
    public PedidoED getByRecord(PedidoED ed)throws Excecoes{

        try {
            Iterator it = this.lista(ed).iterator();
            return it.hasNext() ? (PedidoED) it.next() : new PedidoED();
        } catch(Exception exc){
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "getByRecord()");
        }
    }
  	
  	//*** FINALIZA PEDIDO DE VENDA
    public void finalizaPedidoVenda(long oid_Pedido, String oid_Nota_Fiscal) throws Excecoes {

        try {
            if (oid_Pedido < 1)
            {
                if (!doValida(oid_Nota_Fiscal) || (oid_Pedido = getTableIntValue("DISTINCT oid_Pedido", "Itens_Notas_Fiscais", "oid_Nota_Fiscal = '"+oid_Nota_Fiscal+"'")) < 1)
                    throw new Excecoes("Pedido não informado!");
            }
    	    //*** OBS.: O PEDIDO somente sera FINALIZADO na impresão da NF de SAIDA
    	    String sql = " UPDATE Pedidos SET ";
	           	  sql += "  DT_Stamp = '" + Data.getDataDMY() + "'";
	           	  sql += " ,DM_Situacao = 'F'";
	           	  sql += " WHERE oid_Pedido = " + oid_Pedido;

	  	    executasql.executarUpdate(sql);
    	} catch(Exception e){
            throw new Excecoes("Erro ao Finalizar Pedido! ["+e.getMessage()+"]", e, this.getClass().getName(), 
                    "finalizaPedidoVenda()");
    	}
    }
  	
  	//*** APROVAÇÃO DO PEDIDO DE VENDA
    public void setAprovacaoPedidoVenda(PedidoED ed) throws Exception {

        double NR_Itens = 0;
        //*** Gera NF, Itens_NF, Duplicatas(Parcelas Contas a Receber), Seta Produto como Aprovado!
        //*** Carrega os dados do Item Pedido pelo OID_PEDIDO
        ArrayList lista = new Item_PedidoBD(executasql).lista(new Item_PedidoED(ed.getOID_Pedido()));
        for (int i = 0; i < lista.size(); i++)
        {
            Item_PedidoED edItemPed = (Item_PedidoED) lista.get(i);
            //*** Não lista Produtos Cancelados
            if (!"C".equals(edItemPed.getDM_Situacao()))
                NR_Itens += (edItemPed.getNR_Peso_Real() > 0) ? edItemPed.getNR_Peso_Real() : edItemPed.getNR_QT_Atendido();
        }

        //*** Nota Fiscal
        Nota_Fiscal_EletronicaED edNota_Fiscal = new Nota_Fiscal_EletronicaED();
    	edNota_Fiscal.setDM_Contabiliza("N");
    	edNota_Fiscal.setOid_natureza_operacao(ed.getOid_Natureza_Operacao());
    	//edNota_Fiscal.setOid_Conta();
    	//*** Cliente
    	edNota_Fiscal.setOid_pessoa(ed.getOID_Pessoa());
    	edNota_Fiscal.setOid_pessoa_destinatario(ed.getOID_Pessoa());
    	//*** Vendedor
    	edNota_Fiscal.setOid_pessoa_fornecedor(ed.getOID_Vendedor());
        edNota_Fiscal.setOid_Tabela_Venda(ed.getOid_Tabela_Venda());
    	edNota_Fiscal.setOid_Condicao_Pagamento((int)ed.getOID_Condicao_Pagamento());
    	edNota_Fiscal.setOid_Entregador(ed.getOid_Entregador());
        if (ed.getOid_Entregador() > 0 && !doValida(ed.getOid_Veiculo()))
        {
            edNota_Fiscal.setOid_Veiculo(getTableStringValue("oid_Veiculo", "Entregadores", "oid_Entregador = "+ed.getOid_Entregador()));
        } else edNota_Fiscal.setOid_Veiculo(ed.getOid_Veiculo());        

    	edNota_Fiscal.setDt_entrada(Data.getDataDMY());
    	edNota_Fiscal.setDm_forma_pagamento(ed.getDM_Meio_Pagamento());
    	edNota_Fiscal.setOID_Unidade_Contabil(ed.getOID_Unidade());
    	edNota_Fiscal.setOID_Unidade_Fiscal(ed.getOID_Unidade());
    	edNota_Fiscal.setOID_Unidade(ed.getOID_Unidade());
    	edNota_Fiscal.setVl_total_frete(ed.getVL_Frete());
    	edNota_Fiscal.setVl_descontos(ed.getVL_Total_Desconto());
        //*** Valor das Trocas é RATIADO entre Itens de Venda
        edNota_Fiscal.setVL_Desconto_Itens(ed.getVL_Total_Troca());
    	edNota_Fiscal.setVl_nota_fiscal(ed.getVL_Total_Produto());
    	edNota_Fiscal.setNr_Itens(NR_Itens);
    	
    	edNota_Fiscal.setDm_observacao(ed.getTX_Observacao());
    	edNota_Fiscal.setDm_tipo_nota_fiscal("S");

    	//*** Seta OID, NUMERO, SÉRIE e MODELO
    	//    Verifica se Informou os Dados da Nota na Entrada
    	if ("E".equals(ed.getDM_Nota_Fiscal()) && ed.getNR_Nota_Fiscal() > 0 && doValida(ed.getNM_Serie()) && ed.getOid_Modelo_Nota_Fiscal() > 0)
        {
    	    edNota_Fiscal.setOid_modelo_nota_fiscal(ed.getOid_Modelo_Nota_Fiscal());
    	    edNota_Fiscal.setNr_nota_fiscal(ed.getNR_Nota_Fiscal());
    	    edNota_Fiscal.setNm_serie(ed.getNM_Serie());
    	    edNota_Fiscal.setOid_nota_fiscal(String.valueOf(ed.getOID_Pessoa()) + String.valueOf(ed.getNR_Nota_Fiscal()) + ed.getNM_Serie());
    	    
    	} else {
        	new Nota_Fiscal_EletronicaBD(executasql).setNrSerieNotaFromAIDOF(edNota_Fiscal, "NF1");
            //*** Pedido Reaberto, NF Devolvida, seta Condição 91 p/ reemissão da NF de Venda
            if (doExiste("Pedidos_Notas_Fiscais", "Pedidos_Notas_Fiscais.oid_Pedido = "+ed.getOID_Pedido()+
                                                  " AND Pedidos_Notas_Fiscais.oid_Nota_Fiscal = Notas_Fiscais.oid_Nota_Fiscal" +
                                                  " AND Notas_Fiscais.DM_Situacao = 'D'"))
            {
                edNota_Fiscal.setOid_modelo_nota_fiscal(getTableIntValue("oid_Modelo_Nota_Fiscal", "Modelos_Notas_Fiscais", "CD_Modelo_Nota_Fiscal = "+getSQLString("91")));
                //*** Seta Data de Emissão antiga, informada no Retorno
                edNota_Fiscal.setDt_emissao(getTableStringValue("Notas_Fiscais.DT_Emissao", "Notas_Fiscais, Notas_Fiscais as NFs", "Pedidos_Notas_Fiscais.oid_Pedido = "+ed.getOID_Pedido()+
                                                                                            " AND Pedidos_Notas_Fiscais.oid_Nota_Fiscal = NFs.oid_Nota_Fiscal" +
                                                                                            " AND NFs.DM_Situacao = 'D'" +
                                                                                            " AND Notas_Fiscais.oid_Nota_Fiscal = NFs.oid_Nota_Fiscal_Devolucao"));
            }
            if (edNota_Fiscal.getOid_modelo_nota_fiscal() < 1)
                edNota_Fiscal.setOid_modelo_nota_fiscal(parametro_FixoED.getOid_Modelo_Nota_Fiscal());
    	}
    	//*** NOTA FISCAL e PEDIDO de VENDA só serão FINALIZADOS na IMPRESSÃO DA NOTA FISCAL
    	edNota_Fiscal.setDm_finalizado("N");
    	edNota_Fiscal.setDM_Pendencia("N");
    	edNota_Fiscal.setDM_Situacao("N");
    	edNota_Fiscal.setDM_Impresso("N");

    	//*** Inclui Nota Fiscal de VENDA
    	edNota_Fiscal = new Nota_Fiscal_EletronicaBD(executasql).inclui(edNota_Fiscal); 

    	boolean canInsert = true;
        double vlTotalRateio = 0;
    	long oid_Item_Nota_Fiscal = 0;
    	int oid_Pedido_Nota_Fiscal = 0;
    	oid_Pedido_Nota_Fiscal = getMaximo("oid_Pedido_Nota_Fiscal", "Pedidos_Notas_Fiscais", "");
        
    	//*** Varre a lista incluindo os itens
        nextItem: for (int i = 0; i < lista.size(); i++)
        {
    	    Item_PedidoED edItem_Pedido = (Item_PedidoED) lista.get(i);
    	    //*** Não inserir itens com QTD == 0 (cancelado)
    	    if (edItem_Pedido.getNR_QT_Atendido() > 0 && !"C".equals(edItem_Pedido.getDM_Situacao()))
            {
                if (new ProdutoBean().isPesagemByProduto(edItem_Pedido.getOID_Produto()) && edItem_Pedido.getNR_Peso_Real() <= 0)
                    continue nextItem;
    	        //*** Busca % de ICMS
	    	    //*** Itens Nota Fiscal
                String tipoICMS = "";
                Item_Nota_Fiscal_TransacoesED edItem_NF = new Item_Nota_Fiscal_TransacoesBD(executasql).getICMS_Produto(edItem_Pedido.getOID_Produto(),
                                                                                                                        (int)edNota_Fiscal.getOID_Unidade(), 
	        	        													 										    edNota_Fiscal.getOid_pessoa_destinatario(),
	        	        													 										    edItem_Pedido.getOid_Tipo_Produto(),
	        	        													 										    edNota_Fiscal.getOid_natureza_operacao(),
	        	        													 										    edItem_Pedido.getVL_Item()-edItem_Pedido.getVL_IPI(),
	        	        													 										    edNota_Fiscal.getDm_tipo_nota_fiscal(),
	        	        													 										    tipoICMS);
	        	
	    	    //*** OID_NOTA_FISCAL
	    	    edItem_NF.setOID_Nota_Fiscal(edNota_Fiscal.getOid_nota_fiscal());
	
	    	    edItem_NF.setOID_Pedido(edItem_Pedido.getOID_Pedido());
	    	    edItem_NF.setOID_Item_Pedido(edItem_Pedido.getOID_Item_Pedido());
	    	    edItem_NF.setOID_Produto(edItem_Pedido.getOID_Produto());
	    	    edItem_NF.setOid_Localizacao(edItem_Pedido.getOid_Localizacao());
                edItem_NF.setOid_Preco_Produto(edItem_Pedido.getOid_Preco_Produto());
	    	    edItem_NF.setNR_Peso_Real(edItem_Pedido.getNR_Peso_Real());
	    	    if (edItem_NF.getNR_Peso_Real() > 0)
	    	        edItem_NF.setNR_QT_Atendido(edItem_Pedido.getNR_Peso_Real());
	    	    else edItem_NF.setNR_QT_Atendido(edItem_Pedido.getNR_QT_Atendido());
	    	    edItem_NF.setNR_Quantidade(edItem_NF.getNR_QT_Atendido());
	    	    edItem_NF.setDM_Quantidade(edItem_Pedido.getDM_Quantidade());
	    	    edItem_NF.setVL_Item(edItem_Pedido.getVL_Item());
	    	    edItem_NF.setVL_Produto(edItem_Pedido.getVL_Produto());
    	        //*** Valor Unitário, ou Promocional
	    	    if (edItem_Pedido.getOid_Promocao_Produto() > 0 && edItem_Pedido.getVL_Promocao() > 0)
	    	        edItem_Pedido.setVL_Unitario(edItem_Pedido.getVL_Promocao());	    	    
	    	    edItem_NF.setVL_Unitario(edItem_Pedido.getVL_Unitario());
	    	    edItem_NF.setVL_Desconto(edItem_Pedido.getVL_Desconto());
                edItem_NF.setPE_Desconto(edItem_Pedido.getPE_Desconto());
	    	    edItem_NF.setVL_Margem_Contribuicao(edItem_Pedido.getVL_Margem_Contribuicao());
	    	    edItem_NF.setVL_IPI(edItem_Pedido.getVL_IPI());
	    	    edItem_NF.setDM_Situacao("N");
	    	    edItem_NF.setDM_Pedido("V");
	    	    
	    	    //*** Seta OID_Item_Nota_Fiscal antes, pra evitar erro utilizandoa mesma transação
	    	    if (oid_Item_Nota_Fiscal < 1)
	    	        oid_Item_Nota_Fiscal = new Long(getAutoIncremento("oid_Item_Nota_Fiscal","Itens_Notas_Fiscais")).longValue();
	    	    else oid_Item_Nota_Fiscal++;
	    	    edItem_NF.setOID_Item_Nota_Fiscal(oid_Item_Nota_Fiscal);
                
                /** RATEIO ENTRE OS ITENS(DESCONTO) **/ 
                if (edNota_Fiscal.getVL_Desconto_Itens() > 0 && edItem_Pedido.getVL_Produto() > 0 && edNota_Fiscal.getVl_nota_fiscal() > 0)
                {
                    double peDivisao = Valor.round(edItem_Pedido.getVL_Produto()/edNota_Fiscal.getVl_nota_fiscal(), 9);
                    edItem_NF.setVL_Desconto_NF(Valor.round(peDivisao * edNota_Fiscal.getVL_Desconto_Itens(),2));
                    vlTotalRateio += edItem_NF.getVL_Desconto_NF();
                    
                    //*** Para Arredondamento do Rateio
                    if ((lista.size() - 1) == i && vlTotalRateio != edNota_Fiscal.getVL_Desconto_Itens())
                    {
                        double vlDiferenca = Valor.round(Math.max(vlTotalRateio, edNota_Fiscal.getVL_Desconto_Itens()) - Math.min(vlTotalRateio, edNota_Fiscal.getVL_Desconto_Itens()),2);
                        if (vlTotalRateio > edNota_Fiscal.getVL_Desconto_Itens())
                            edItem_NF.setVL_Desconto_NF(edItem_NF.getVL_Desconto_NF()-vlDiferenca);
                        else edItem_NF.setVL_Desconto_NF(edItem_NF.getVL_Desconto_NF()+vlDiferenca);
                    }
                }
	    	    
	    	    //*** Inclui Item Nota Fiscal
	    	    new Item_Nota_Fiscal_TransacoesBD(executasql).inclui(edItem_NF);
	    	    
	    	    //*** Insere registro na Tabela Pedidos_Notas_Fiscais, caso não exista
	    	    if (canInsert && edItem_NF.getOID_Pedido() > 0 && !doExiste("Pedidos_Notas_Fiscais",
	    	            									   				"oid_Pedido = "+edItem_NF.getOID_Pedido()+
	    	            									   				" AND oid_Nota_Fiscal = '"+edNota_Fiscal.getOid_nota_fiscal()+"'")) {
	    	        new Pedido_Nota_FiscalBD(executasql).inclui(new Pedido_Nota_FiscalED(++oid_Pedido_Nota_Fiscal
	    	                															,(int)edItem_NF.getOID_Pedido()
							 															,edNota_Fiscal.getOid_nota_fiscal()
							 															,"S"
							 															,edNota_Fiscal.getDM_Situacao()));
	    	        canInsert = false;
	    	    }
    	    }
    	}
        
    	//*** ATUALIZA Valor ICMS, e Base de Calculo na Nota Fiscal
        new Nota_Fiscal_EletronicaBD(executasql).atualizaValorICMS(edNota_Fiscal.getOid_nota_fiscal());
    	
    	try {
	    	//*** Seta Pedido como Aprovado!
    	    //    OBS.: O PEDIDO somente sera FINALIZADO na impresão da NF de SAIDA
	    	String sql = " UPDATE Pedidos SET ";
		           sql +="    DT_Aprovacao = '" + Data.getDataDMY() + "'";
	  	           sql +="   ,HR_Aprovacao = '" + Data.getHoraHM() + "'";
	  	           sql +="   ,DM_Situacao = 'A'";
	  	           sql +=" WHERE OID_Pedido = " + ed.getOID_Pedido();
	  	    executasql.executarUpdate(sql);
            
        } catch(Exception e){
            throw new Excecoes("Erro ao Aprovar Pedido! ["+e.getMessage()+"]", e, this.getClass().getName(), 
                    "setAprovacaoPedidoVenda()");
    	}
    }

    //*** Atualiza a Situacao do Pedido (A ou N=Aberto , F=Finalizado, C=Cancelado)
    public void atualizaSituacaoPedido(long oid_Pedido, String DM_Situacao) throws Excecoes  {

        String sql = null;
        try {
            if (!doValida(DM_Situacao))
                throw new Excecoes("Situacão do Pedido não informada!");
            if (oid_Pedido < 1)
                throw new Excecoes("Pedido não informado!");
            
            sql = " UPDATE Pedidos SET " +
             	  "		DM_Situacao = '"+DM_Situacao+"'" +
               	  " WHERE oid_Pedido ="+oid_Pedido;
            executasql.executarUpdate(sql);
            //*** Itens Pedidos (Itens cancelados, tem seus VALORES ZERADOS!)
            if (!"C".equals(DM_Situacao))
            {
                sql = " UPDATE Itens_Pedidos SET " +
                	  "		DM_Situacao = '"+DM_Situacao+"'" +
       	  		  	  " WHERE oid_Pedido ="+oid_Pedido;
                executasql.executarUpdate(sql);
            } else new Item_PedidoBD(executasql).alteraByPedido(new Item_PedidoED(0, oid_Pedido, DM_Situacao));
            
        } catch(Exception e) {
            throw new Excecoes(e.getMessage(), e, this.getClass().getName(), 
                "atualizaSituacaoPedido()");
        }
    }
    
    //*** Atualiza a Critica Financeira do Pedido (A=Aprovado , N=Não Verificada, S=Crédito Bloqueado, L=Estoque Liberado)
    public void atualizaCriticaFinanceira(long oid_Pedido, String DM_Critica_Financeira) throws Excecoes {

        String sql = null;
        try {
            if (!doValida(DM_Critica_Financeira))
                throw new Excecoes("Crítica Financeira não informada!");
            if (oid_Pedido < 1)
                throw new Excecoes("Pedido não informado!");
            
            sql = " UPDATE Pedidos SET " +
             	  "		DM_Critica_Financeira = '"+DM_Critica_Financeira+"'" +
             	  "	   ,DT_Stamp = '"+Data.getDataYMD()+"'" +
               	  " WHERE oid_Pedido ="+oid_Pedido;
            executasql.executarUpdate(sql);
            
        } catch(Exception e) {
            throw new Excecoes(e.getMessage(), e, this.getClass().getName(), 
                "atualizaCriticaFinanceira()");
        }
    }
    //*** Atualiza a Critica Estoque do Pedido (A=Aprovada , N=Não Verificada, S=Crédito Bloqueado, L=Crédito Liberado)
    public void atualizaCriticaEstoque(long oid_Pedido, String DM_Critica_Estoque) throws Excecoes {

        String sql = null;
        try {
            if (!doValida(DM_Critica_Estoque))
                throw new Excecoes("Crítica de Estoque não informada!");
            if (oid_Pedido < 1)
                throw new Excecoes("Pedido não informado!");
            
            sql = " UPDATE Pedidos SET " +
             	  "		DM_Critica_Estoque = '"+DM_Critica_Estoque+"'" +
             	  "	   ,DT_Stamp = '"+Data.getDataYMD()+"'" +
               	  " WHERE oid_Pedido ="+oid_Pedido;
            executasql.executarUpdate(sql);
            
        } catch(Exception e) {
            throw new Excecoes(e.getMessage(), e, this.getClass().getName(), 
                "atualizaCriticaEstoque()");
        }
    }
    
    //*** Atualiza Entregador do Pedido
    public void atualizaEntregadorPedido(long oid_Pedido, int oid_Entregador, String oid_Veiculo) throws Excecoes {

        String sql = null;
        try {
            if (oid_Entregador < 1)
                throw new Excecoes("Entregador não informado!");
            if (oid_Pedido < 1)
                throw new Excecoes("Pedido não informado!");
            
        	sql = " UPDATE Pedidos SET " +
        		  "		 oid_Entregador = "+oid_Entregador+
                  "     ,oid_Veiculo = "+getSQLString(oid_Veiculo)+
        		  " WHERE oid_Pedido ="+oid_Pedido;
        	executasql.executarUpdate(sql);
        } catch(Exception e) {
            throw new Excecoes(e.getMessage(), e, this.getClass().getName(), 
                    "atualizaEntregadorPedido()");
        }
    }
    
  	public void total_Pedido(long oid_Pedido) throws Excecoes{

        if (oid_Pedido < 1)
            return;
        
  	    String sql = null;
  	    double VL_Total_Troca=0, VL_Frete=0, 
               VL_Total_Item=0, VL_Total_IPI=0, 
               VL_Total_Desconto=0, VL_Total_Produto=0, 
               VL_Total_Margem=0;
  	    try{

  	        VL_Frete = getTableDoubleValue("sum(VL_Frete)", "Pedidos", "oid_Pedido = "+oid_Pedido);
  	        sql = " SELECT sum(VL_Item) as VL_Item" +
                  "     ,sum(VL_Produto) as VL_Produto" +
                  "     ,sum(NR_QT_Troca*VL_Troca) as VL_Troca" +
                  "     ,sum(VL_IPI) as VL_IPI" +
                  "     ,sum(VL_Desconto) as VL_Desconto" +
                  "     ,sum(VL_Margem_Contribuicao) as VL_Margem_Contribuicao" +
                  " FROM Itens_Pedidos " +
  	        	  " WHERE Itens_Pedidos.oid_Pedido = "+oid_Pedido+
  	        	  "   AND Itens_Pedidos.DM_Situacao <> 'C'";
            ResultSet res = null;
            try {
      	        res = this.executasql.executarConsulta(sql);
      	        while (res.next())
                {
      	            VL_Total_Item += res.getDouble("VL_Item");
      	            VL_Total_Produto += res.getDouble("VL_Produto");
      	            VL_Total_Troca += res.getDouble("VL_Troca");
      	            VL_Total_IPI += res.getDouble("VL_IPI");
      	            VL_Total_Desconto += res.getDouble("VL_Desconto");
      	            VL_Total_Margem += res.getDouble("VL_Margem_Contribuicao");
      	        }
            } finally {
                closeResultset(res);
            }
  	        sql = " UPDATE Pedidos SET ";
  	        sql +="  VL_Total_Pedido= " +(VL_Total_Item + VL_Frete - VL_Total_Troca)+ ", ";
  	        sql +="  VL_Total_Item= " +VL_Total_Item + ", ";
  	        sql +="  VL_Total_Produto= " +VL_Total_Produto + ", ";
  	        sql +="  VL_Total_Troca= " +VL_Total_Troca + ", ";
  	        sql +="  VL_Total_IPI= " +VL_Total_IPI + ", ";
  	        sql +="  VL_Total_Margem= " +VL_Total_Margem + ", ";
  	        sql +="  VL_Total_Desconto= " +VL_Total_Desconto;
  	        sql +=" WHERE oid_Pedido = " +oid_Pedido;
  	        executasql.executarUpdate(sql);
            
  	    } catch(Exception exc){
  	        throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
  	      	      "total_Pedido()");
  	    }
  	}
}