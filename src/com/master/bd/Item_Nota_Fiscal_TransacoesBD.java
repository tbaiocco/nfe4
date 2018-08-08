package com.master.bd;

/**
 * Empresa: �xitoLog�stica Mastercom
 * Autor: Andr� Valadas
*/
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.master.ed.Item_Nota_Fiscal_TransacoesED;
import com.master.ed.Livro_FiscalED;
import com.master.ed.Nota_Fiscal_EletronicaED;
import com.master.ed.ProdutoED;
import com.master.iu.Pro004Bean;
import com.master.iu.ProdutoBean;
import com.master.root.CidadeBean;
import com.master.root.FormataDataBean;
import com.master.root.TaxaBean;
import com.master.root.Taxa_ProdutoBean;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.Mensagens;
import com.master.util.Valor;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;
import com.master.ed.Comissao_VendedorED;
import com.master.bd.Comissao_VendedorBD;

public class Item_Nota_Fiscal_TransacoesBD extends BancoUtil {

    private ExecutaSQL executasql;
    Parametro_FixoED parametro_FixoED = Parametro_FixoED.getInstancia();

    public Item_Nota_Fiscal_TransacoesBD(ExecutaSQL sql) {
        super(sql);
        this.executasql = sql;
    }

    public Item_Nota_Fiscal_TransacoesED inclui(Item_Nota_Fiscal_TransacoesED ed) throws Excecoes {

        String sql = null;
        try{
            //*** Para inser��o de multiplos registros utilizando a mesma conex�o, o oid podera vir informado
            if (ed.getOID_Item_Nota_Fiscal() > 0)
            {
                if (super.doExiste("Itens_Notas_Fiscais", "oid_Item_Nota_Fiscal = "+ed.getOID_Item_Nota_Fiscal()))
                    throw new Excecoes("ID Item Nota Fiscal j� existe!");
            } else ed.setOID_Item_Nota_Fiscal(new Long(getAutoIncremento("oid_Item_Nota_Fiscal","Itens_Notas_Fiscais")).longValue());

            Comissao_VendedorED edCV = new Comissao_VendedorED();
            Comissao_VendedorBD bdCV = new Comissao_VendedorBD(this.sql);

        	/** Busca dados da nota fiscal **/
        	Nota_Fiscal_EletronicaED edNF = new Nota_Fiscal_EletronicaED();
        	edNF.setOid_nota_fiscal(ed.getOID_Nota_Fiscal());
        	edNF = new Nota_Fiscal_EletronicaBD(this.sql).getByRecord(edNF);
        	/** Pega o oid da empresa pra buscar o custo no Produtos_clientes **/
        	String oid_Empresa = edNF.getOid_pessoa();

            //*** Verifica se Calcula Automaticamente os Valores
            if (!ed.isVendaDireta())
            {

            	String tipoICMS = "";
                // As notas fiscais de devolu��o n�o consta ICMS
            	if (edNF.getDm_tipo_nota_fiscal().equals("D")){
            		tipoICMS = "S";
            	}else{
                	tipoICMS = getTableStringValue("DM_ICMS_WMS", "Clientes, Notas_Fiscais",	"Clientes.oid_Pessoa = Notas_Fiscais.oid_Pessoa" + " AND Notas_Fiscais.oid_Nota_Fiscal = '" + ed.getOID_Nota_Fiscal() + "'");
            	}

            	String oidPessoaDestino = "";

            	if (edNF.getDm_tipo_nota_fiscal().equals("S") || edNF.getDm_tipo_nota_fiscal().equals("D") || "18".equals(String.valueOf(edNF.getOid_modelo_nota_fiscal())))
				{
            		oidPessoaDestino = edNF.getOid_pessoa_destinatario();
            	}else{
            		oidPessoaDestino = edNF.getOid_pessoa();
            	}
    			Item_Nota_Fiscal_TransacoesED edItemNF = this.getICMS_Produto(ed.getOID_Produto(),
                                                                              (int)edNF.getOID_Unidade_Fiscal(),
                                                                              oidPessoaDestino,
                                    									      getTableIntValue("oid_Tipo_Produto","Produtos","oid_Produto = "+ed.getOID_Produto()),
                                                                              ed.getOid_natureza_operacao(),
                                    										  ed.getVL_Item(),
                                    										  edNF.getDm_tipo_nota_fiscal(), tipoICMS);

                ed.setVL_Base_Calculo_ICMS(edItemNF.getVL_Base_Calculo_ICMS());
                ed.setOid_Taxa_Produto(edItemNF.getOid_Taxa_Produto());
                ed.setOid_natureza_operacao(edItemNF.getOid_natureza_operacao());
                ed.setPE_Aliquota_ICMS(edItemNF.getPE_Aliquota_ICMS());
                ed.setPE_Aliquota_ICMS_Aprov(edItemNF.getPE_Aliquota_ICMS_Aprov());
                ed.setVL_ICMS(edItemNF.getVL_ICMS());
                ed.setVL_ICMS_Aprov(edItemNF.getVL_ICMS_Aprov());

                if ("S".equals(edNF.getDm_tipo_nota_fiscal()) ) {
	                //---Busca Comissao-venda normal---//
	                ed.setVl_Comissao(0);
	                ed.setVL_Custo(0);
                } else {
                	ed.setVl_Comissao(0);
                }
            } else {
                //---Busca Comissao-venda direta---//
                edCV.setOid_Vendedor(edNF.getOid_pessoa_fornecedor());
                edCV = bdCV.getPEComissaoVD(edCV);
                ed.setVl_Comissao(edCV.getPE_Comissao() * ed.getVL_Item()/100);// calcular venda direta
            }

            // Havendo desconto especial na nota fiscal o mesmo dever� ser raeteado
            // no ICMS e Base ICMS dos itens da nota fiscal de saida
            if (ed.getVL_Desconto_NF() > 0 ){
                ed.setVL_ICMS_Aprov(Valor.round(ed.getVL_ICMS_Aprov() * (ed.getVL_Item() - ed.getVL_Desconto_NF()) / ed.getVL_Item(),2));
                ed.setVL_Base_Calculo_ICMS(Valor.round(ed.getVL_Base_Calculo_ICMS() * (ed.getVL_Item() - ed.getVL_Desconto_NF()) / ed.getVL_Item(),2));
                ed.setVL_ICMS(Valor.round(ed.getVL_ICMS() * (ed.getVL_Item() - ed.getVL_Desconto_NF()) / ed.getVL_Item(),2));
            }

            sql = " INSERT INTO Itens_Notas_Fiscais (" +
            	  "		 OID_Item_Nota_Fiscal" +
            	  "		,OID_Nota_Fiscal" +
            	  "		,OID_Pedido" +
            	  "		,OID_Item_Pedido" +
            	  "		,OID_Produto" +
            	  "		,oid_Taxa_Produto" +
                  "     ,oid_Preco_Produto" +
            	  "		,oid_Localizacao" +
            	  "		,NR_QT_Atendido" +
            	  "		,NR_Peso_Real" +
            	  "		,DM_Quantidade" +
            	  "		,NR_Quantidade" +
            	  "		,NR_QT_Devolucao" +
            	  "		,Dt_Stamp" +
            	  "		,VL_Item" +
            	  "		,VL_Custo" +
                  "     ,VL_Adicional" +
            	  "		,VL_Desconto_NF" +
            	  "		,VL_Desconto" +
            	  "		,VL_Produto" +
            	  "		,VL_Unitario" +
            	  "		,VL_IPI" +
            	  "		,VL_ICMS" +
            	  "		,VL_ICMS_Aprov" +
            	  "		,VL_Base_Calculo_ICMS" +
            	  "		,VL_Margem_Contribuicao" +
            	  "		,PE_Margem_Contribuicao" +
            	  "		,PE_Aliquota_ICMS" +
            	  "		,PE_Aliquota_ICMS_Aprov" +
            	  "		,PE_Desconto" +
                  "     ,PE_Desconto_Extra" +
            	  "		,PE_Aliquota_IPI" +
            	  "		,OID_Natureza_Operacao" +
            	  "		,vl_Comissao" +
            	  "		,oid_Produto_Cliente" +
            	  "		,nr_Lote_Produto" +
            	  "		,dm_Devolvido" +
            	  "		,DM_Situacao, nr_quantidade_fardo, vl_fator)";
            sql+= " VALUES ( " +
            	  ed.getOID_Item_Nota_Fiscal() +
            	  ",'" + ed.getOID_Nota_Fiscal()+  "'" +
            	  "," + ed.getOID_Pedido() +
            	  "," + ed.getOID_Item_Pedido() +
            	  "," + ed.getOID_Produto() +
            	  "," + ed.getOid_Taxa_Produto() +
            	  ", "+ ed.getOid_Preco_Produto() +
                  ", "+ ed.getOid_Localizacao() +
            	  "," + ed.getNR_QT_Atendido() +
            	  "," + ed.getNR_Peso_Real() +
            	  ",'" + getValueDef(ed.getDM_Quantidade(), "") + "'" +
            	  "," + ed.getNR_Quantidade() +
            	  "," + ed.getNR_QT_Devolucao() +
            	  ",'" + Data.getDataDMY() + "'" +
            	  "," + ed.getVL_Item() +
            	  "," + ed.getVL_Custo() +
                  "," + ed.getVL_Adicional() +
            	  "," + ed.getVL_Desconto_NF() +
            	  "," + ed.getVL_Desconto() +
            	  "," + ed.getVL_Produto()  +
            	  "," + ed.getVL_Unitario()  +
            	  "," + ed.getVL_IPI() +
            	  "," + ed.getVL_ICMS() +
            	  "," + ed.getVL_ICMS_Aprov()+
            	  "," + ed.getVL_Base_Calculo_ICMS()+
            	  "," + ed.getVL_Margem_Contribuicao()+
            	  "," + ed.getPE_Margem_Contribuicao()+
            	  "," + ed.getPE_Aliquota_ICMS()+
            	  "," + ed.getPE_Aliquota_ICMS_Aprov() +
            	  "," + ed.getPE_Desconto()  +
                  "," + ed.getPE_Desconto_Extra()  +
            	  "," + ed.getPE_Aliquota_IPI() +
            	  "," + ed.getOid_natureza_operacao() +
            	  "," + ed.getVl_Comissao() +
            	  "," + ed.getOid_Produto_Cliente() +
            	  ",'" + ed.getNR_Lote_Produto()+ "'" +
            	  ",'N'" +
            	  ",'N'" +
            	  "," + ed.getNR_Quantidade_Fardo() +
            	  "," + ed.getVl_Fator() + ")";

            executasql.executarUpdate(sql);
            /** Atualiza QTD_Atendida no Pedido de COMPRA **/
            if (ed.getOID_Item_Pedido() > 0 && !"V".equals(ed.getDM_Pedido()))
            {
                this.atualizaQTDAtendido(ed.getNR_Quantidade(), ed.getOID_Item_Pedido());
            }
            /** Atualiza Quantidade, Valor pelos Itens da Nota **/

            if (edNF.getDm_tipo_nota_fiscal().equals("D")){
            	ed.setDM_Tipo_Nota_Fiscal("D");
            }

            if ("508".equals(ed.getOID_Produto())){

                double vl_icms = getTableDoubleValue("vl_icms",
                        "notas_fiscais",
                        "oid_nota_fiscal = '"+ed.getOID_Nota_Fiscal()+"'");

            	sql = " UPDATE itens_notas_fiscais " +
            		  " set vl_icms=" + vl_icms + ", vl_icms_aprov = " + vl_icms + ", nr_qt_atendido = 0, nr_quantidade =0, vl_unitario=0, vl_produto=0, vl_item=0, vl_custo=0 " +
            		  " where oid_item_nota_fiscal = " + ed.getOID_Item_Nota_Fiscal();
                executasql.executarUpdate(sql);
            }else{
                this.atualizaItensNota(ed);
                this.atualizaMargemFinanceiraItem(ed);
            }

        } catch(Exception exc){
        	exc.printStackTrace();
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "inclui()");
        }
        return ed;
    }

    public void altera(Item_Nota_Fiscal_TransacoesED ed) throws Excecoes{

        String sql = null;
        try{

            //*** Atualiza QTD_Atendida no Pedido de COMPRA
            if (ed.getOID_Item_Pedido() > 0 && !"V".equals(ed.getDM_Pedido()))
            {
                double qtAtendido_OLD = getTableDoubleValue("sum(NR_Quantidade)",
	                    							  		"Itens_Notas_Fiscais",
	                    							  		"oid_Item_Nota_Fiscal = " + ed.getOID_Item_Nota_Fiscal());
                double qtAtendido_NEW = ed.getNR_Quantidade();
	            if (qtAtendido_OLD != qtAtendido_NEW)
                {
	                this.atualizaQTDAtendido(Valor.round(qtAtendido_NEW - qtAtendido_OLD, 3), ed.getOID_Item_Pedido());
	            }
        	}

            Comissao_VendedorED edCV = new Comissao_VendedorED();
            Comissao_VendedorBD bdCV = new Comissao_VendedorBD(this.sql);

        	/** Busca dados da nota fiscal **/
        	Nota_Fiscal_EletronicaED edNF = new Nota_Fiscal_EletronicaED();
        	edNF.setOid_nota_fiscal(ed.getOID_Nota_Fiscal());
        	edNF = new Nota_Fiscal_EletronicaBD(this.sql).getByRecord(edNF);
        	/** Pega o oid da empresa pra buscar o custo no Produtos_clientes **/
        	String oid_Empresa = getTableStringValue("oid_Pessoa","Unidades","oid_unidade = "+edNF.getOID_Unidade_Fiscal());

        	//*** Verifica se Calcula Automaticamente os Valores
            if (!ed.isVendaDireta())
            {

            	String tipoICMS = "";
                // As notas fiscais de devolu��o n�o consta ICMS
            	if (edNF.getDm_tipo_nota_fiscal().equals("D")){
            		tipoICMS = "S";
            	}else{
                	tipoICMS = getTableStringValue("DM_ICMS_WMS", "Clientes, Notas_Fiscais",	"Clientes.oid_Pessoa = Notas_Fiscais.oid_Pessoa" + " AND Notas_Fiscais.oid_Nota_Fiscal = '" + ed.getOID_Nota_Fiscal() + "'");
            	}

            	Item_Nota_Fiscal_TransacoesED edItemNF = this.getICMS_Produto(ed.getOID_Produto(),
                                                                              (int)edNF.getOID_Unidade_Fiscal(),
                                                                              (edNF.getDm_tipo_nota_fiscal().equals("S") || edNF.getDm_tipo_nota_fiscal().equals("D")) ? edNF.getOid_pessoa_destinatario() : edNF.getOid_pessoa(),
                                    									      getTableIntValue("oid_Tipo_Produto","Produtos","oid_Produto = "+ed.getOID_Produto()),
                                                                              ed.getOid_natureza_operacao(),
                                    										  ed.getVL_Item(),
                                    										  edNF.getDm_tipo_nota_fiscal(), tipoICMS);

                ed.setVL_Base_Calculo_ICMS(edItemNF.getVL_Base_Calculo_ICMS());
	            ed.setOid_Taxa_Produto(edItemNF.getOid_Taxa_Produto());
	            ed.setOid_natureza_operacao(edItemNF.getOid_natureza_operacao());
	            ed.setPE_Aliquota_ICMS(edItemNF.getPE_Aliquota_ICMS());
	            ed.setPE_Aliquota_ICMS_Aprov(edItemNF.getPE_Aliquota_ICMS_Aprov());
	            ed.setVL_ICMS_Aprov(edItemNF.getVL_ICMS_Aprov());
	            ed.setVL_ICMS(edItemNF.getVL_ICMS());
                //if ("S".equals(edNF.getDm_tipo_nota_fiscal()) ) {
	            //    //---Busca Comissao-venda normal---//
	            //    edCV.setOid_Produto(new Integer(ed.getOID_Produto()).intValue());
	            //    edCV.setOid_Vendedor(edNF.getOid_pessoa_fornecedor());
	            //    edCV = bdCV.getPEComissao(edCV);
	            //    ed.setVl_Comissao(edCV.getPE_Comissao() * ed.getVL_Item()/100);// calcular venda normal
	            //    ed.setVL_Custo(getTableDoubleValue("vl_preco_custo","Produtos_Clientes","oid_Produto_cliente = '"+ ed.getOID_Produto().trim()+oid_Empresa.trim()+"'"));
                //} else {
                	ed.setVl_Comissao(0);
                //}
            } else {
                //---Busca Comissao-venda direta---//
            	edCV.setOid_Vendedor(edNF.getOid_pessoa_fornecedor());
                edCV = bdCV.getPEComissaoVD(edCV);
                ed.setVl_Comissao(edCV.getPE_Comissao() * ed.getVL_Item()/100);// calcular venda direta
            }

            // Havendo desconto especial na nota fiscal o mesmo dever� ser raeteado
            // no ICMS e Base ICMS dos itens da nota fiscal de saida
            if (ed.getVL_Desconto_NF() > 0 ){
                ed.setVL_ICMS_Aprov(Valor.round(ed.getVL_ICMS_Aprov() * (ed.getVL_Item() - ed.getVL_Desconto_NF()) / ed.getVL_Item(),2));
                ed.setVL_Base_Calculo_ICMS(Valor.round(ed.getVL_Base_Calculo_ICMS() * (ed.getVL_Item() - ed.getVL_Desconto_NF()) / ed.getVL_Item(),2));
                ed.setVL_ICMS(Valor.round(ed.getVL_ICMS() * (ed.getVL_Item() - ed.getVL_Desconto_NF()) / ed.getVL_Item(),2));
            }
            sql = " UPDATE Itens_Notas_Fiscais SET ";
      	  	sql +="     DM_Quantidade= '" + ed.getDM_Quantidade() + "', ";
      	  	sql +="     NR_Lote_Produto= '" + ed.getNR_Lote_Produto() + "', ";
      	  	sql +="     NR_Quantidade= " + ed.getNR_Quantidade() + ", ";
            sql +="     NR_QT_Devolucao= " + ed.getNR_QT_Devolucao() + ", ";
            sql +="     NR_QT_Atendido= " + ed.getNR_QT_Atendido() + ", ";
            sql +="     NR_Peso_Real= " + ed.getNR_Peso_Real() + ", ";
            sql +="     oid_Natureza_Operacao= " + ed.getOid_natureza_operacao() + ", ";
            sql +="     oid_Preco_Produto= " + ed.getOid_Preco_Produto() + ", ";
            sql +="     oid_Taxa_Produto= " + ed.getOid_Taxa_Produto() + ", ";
            sql +="     VL_Item= " + ed.getVL_Item() + ", ";
            sql +="     VL_Custo= " + ed.getVL_Custo() + ", ";
            sql +="     VL_Adicional= " + ed.getVL_Adicional() + ", ";
            sql +="     VL_Desconto_NF= " + ed.getVL_Desconto_NF() + ", ";
            sql +="     VL_Desconto= " + ed.getVL_Desconto() + ", ";
            sql +="     PE_Desconto= " + ed.getPE_Desconto() + ", ";
            sql +="     PE_Desconto_Extra= " + ed.getPE_Desconto_Extra() + ", ";
            sql +="     VL_Produto= " + ed.getVL_Produto() + ", ";
            sql +="     VL_IPI= " + ed.getVL_IPI() + ", ";
            sql +="     VL_ICMS= " + ed.getVL_ICMS() + ", ";
            sql +="     VL_ICMS_Aprov= " + ed.getVL_ICMS_Aprov() + ", ";
            sql +="     VL_Base_Calculo_ICMS= " + ed.getVL_Base_Calculo_ICMS() + ", ";
            sql +="     PE_Aliquota_ICMS= " + ed.getPE_Aliquota_ICMS() + ", ";
            sql +="     PE_Aliquota_ICMS_Aprov= " + ed.getPE_Aliquota_ICMS_Aprov() + ", ";
            sql +="     vl_Comissao= " +ed.getVl_Comissao()+ ", ";
            sql +="     VL_Unitario= " + ed.getVL_Unitario();

            if (doValida(ed.getDm_Devolvido()))
            	sql +=" ,dm_Devolvido ='" + ed.getDm_Devolvido() + "' ";
            sql +=" WHERE OID_Item_Nota_Fiscal = " + ed.getOID_Item_Nota_Fiscal();

            executasql.executarUpdate(sql);

            if (edNF.getDm_tipo_nota_fiscal().equals("D")){
            	ed.setDM_Tipo_Nota_Fiscal("D");
            }

            /** Atualiza Quantidade, Valor pelos Itens da Nota **/
            this.atualizaItensNota(ed);
            this.atualizaMargemFinanceiraItem(ed);
        } catch(Exception exc){
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "altera()");
        }
    }

    public void processa_nota() throws Excecoes{

        String sql = null;
        try{
        	sql = " select itens_notas_fiscais.oid_nota_fiscal, itens_notas_fiscais.OID_Item_Nota_Fiscal " +
        			" from notas_fiscais,itens_notas_fiscais " +
        			" where notas_fiscais.oid_nota_fiscal = itens_notas_fiscais.oid_nota_fiscal" +
        			" and notas_fiscais.nr_nota_fiscal in (26426,26427,26428,26429,26430,26431,26447,26449,26450,26451,26453,26454,26455,26456,26460,26461,26462,26463,26465,26467,26468,26469,26471,26472,26473,26474,26475,26476,26477,26478,26480,26481,26483,26458,26459,26434,26435,26436,26437,26438,26439,26444,26442,26443) and dm_tipo_nota_fiscal = 'S' ";

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
            	Item_Nota_Fiscal_TransacoesED edVolta = new Item_Nota_Fiscal_TransacoesED();
                edVolta.setOID_Item_Nota_Fiscal(res.getLong("OID_Item_Nota_Fiscal"));
                edVolta.setOID_Nota_Fiscal(res.getString("OID_Nota_Fiscal"));

                edVolta = this.getByRecord(edVolta);

                this.altera(edVolta);
                Nota_Fiscal_EletronicaED ed = new Nota_Fiscal_EletronicaED();
                ed.setOid_nota_fiscal(edVolta.getOID_Nota_Fiscal());
                new Livro_FiscalBD(this.sql).geraLivro_Fiscal_Saidas(new Livro_FiscalED(ed.getOid_nota_fiscal(), "NF"), "S");
            }
        } catch(Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            	"deleta()");
        }
    }


    public void deleta(Item_Nota_Fiscal_TransacoesED ed) throws Excecoes{

        String sql = null;
        try{
        	sql = " DELETE FROM Itens_Notas_Fiscais " +
                  " WHERE 1=1 ";
            if (ed.getOID_Item_Nota_Fiscal()>0)
                 sql += " and OID_Item_Nota_Fiscal = " + ed.getOID_Item_Nota_Fiscal() + " ";
            if (doValida(ed.getOID_Nota_Fiscal()))
            	sql += " and OID_Nota_Fiscal = '" + ed.getOID_Nota_Fiscal() + "' ";
            executasql.executarUpdate(sql);

            /** Atualiza QTD_Atendida no Pedido de COMPRA **/
            if (ed.getOID_Item_Pedido() > 0 && !"V".equals(ed.getDM_Pedido()))
            {
                this.atualizaQTDAtendido(-ed.getNR_Quantidade(), ed.getOID_Item_Pedido());
            }
            /** Atualiza Quantidade, Valor pelos Itens da Nota **/
            this.atualizaItensNota(ed);
        } catch(Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            	"deleta()");
        }
    }

    //*** Atualiza Quantidade de Itens Atendidos do Pedido de COMPRA
    public void atualizaQTDAtendido(double ntQuantidadeItem, long oid_Item_Pedido) throws Excecoes {

        String sql = null;
        try {
            //*** QT_Atendida Atual
            double qtAtendidoPed = getTableDoubleValue("NR_QT_ATENDIDO",
                                                       "Itens_Pedidos",
                                                       "oid_Item_Pedido = "+oid_Item_Pedido);
            //*** QT_Pedido
            double qtPedido = getTableDoubleValue("NR_QT_PEDIDO",
                                                  "Itens_Pedidos",
                                                  "oid_Item_Pedido = "+oid_Item_Pedido);
            //*** Atualiza e Valida Quantidade Atendida
            qtAtendidoPed += ntQuantidadeItem;
            if (qtAtendidoPed < 0)
                qtAtendidoPed = 0;
            else if (qtAtendidoPed > qtPedido)
                qtAtendidoPed = qtPedido;
            else if (ntQuantidadeItem > qtAtendidoPed)
                qtAtendidoPed += ntQuantidadeItem - qtAtendidoPed;

            sql = " UPDATE Itens_Pedidos SET " +
                  "   NR_QT_ATENDIDO=" + Valor.round(qtAtendidoPed, 3) +
                  " WHERE oid_Item_Pedido ="+oid_Item_Pedido;
            executasql.executarUpdate(sql);
        } catch(Exception exc){
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "atualizaQTDAtendido()");
        }
    }

    //*** Busca ICMS Relacionado aos Estados
    //    Verifica se tipo de produto possui aliquota separada(exclusiva)
    public Item_Nota_Fiscal_TransacoesED getICMS_Produto(String oid_Produto, int oidUnidade, String oidPessoaDestino, int oidTipoProduto, long oidNaturezaOperacao, double vlItem, String dmOperacao, String tipoICMS) throws Exception {

        try {
            //*** Valida��es
            if (!doValida(oid_Produto))
                throw new Excecoes("Produto n�o informado!");
            if (oidUnidade < 1)
                throw new Excecoes("Unidade n�o informada!");
            if (!doValida(oidPessoaDestino))
                throw new Excecoes("Destinat�rio n�o informado!");
            if (oidTipoProduto < 1)
                throw new Excecoes("Tipo de Produto n�o informado!");
            if (oidNaturezaOperacao < 1)
                throw new Excecoes("Natureza de Opera��o n�o informada!");
            if (!doValida(dmOperacao))
                throw new Excecoes("Opera��o n�o informada!");
            //*** Verifica se CFOP n�o possui Aprova��o de Credito.
            boolean notAprovCredito = "I".equals(getTableStringValue("DM_Tipo_Imposto", "Naturezas_Operacoes", "oid_Natureza_Operacao="+oidNaturezaOperacao));

            String oidPessoaOrigem = "";
            //*** Se dmOperacao for "E"=Entrada de Produtos, a Unidade vai ser o Destino
            //    No caso da "S"=Saida a Unidade � Origem
            if ("E".equals(dmOperacao))
            {
                oidPessoaOrigem = oidPessoaDestino;
                oidPessoaDestino = getTableStringValue("oid_Pessoa",
    												   "unidades",
    												   "oid_unidade = "+oidUnidade);
            } else {
                oidPessoaOrigem = getTableStringValue("oid_Pessoa",
    												  "unidades",
    												  "oid_unidade = "+oidUnidade);
            }
            if (!doValida(oidPessoaOrigem))
                throw new Excecoes("Pessoa Origem n�o localizada!");

            //*** Estado ORIGEM
            CidadeBean edCidade_Orig = CidadeBean.getByOID(null, getTableIntValue("oid_Cidade",
                    														"Pessoas",
                    														"oid_Pessoa = '"+oidPessoaOrigem+"'"));
            //*** Estado DESTINO
            CidadeBean edCidade_Dest = CidadeBean.getByOID(null, getTableIntValue("oid_Cidade",
                    														"Pessoas",
                    														"oid_Pessoa = '"+oidPessoaDestino+"'"));
            Item_Nota_Fiscal_TransacoesED edItemNF = new Item_Nota_Fiscal_TransacoesED();
            edItemNF.setOid_natureza_operacao(oidNaturezaOperacao);
            // Se na natureza de operacao da NF estiver setado para manter para toda a nota n�o tenta mudar para a diferenciada.
            if ("N".equals(getTableStringValue("dm_manter_para_nf","naturezas_operacoes","oid_natureza_operacao = "+oidNaturezaOperacao))) {
	            //*** CFOP DIFERENCIADO CARNE(Saidas)
	            if ("S".equals(dmOperacao) &&
	                parametro_FixoED.getOID_CFOP_Diferenciado() != oidNaturezaOperacao &&
	                "060".equals(getTableStringValue("Situacoes_Tributarias.CD_Situacao_Tributaria","Situacoes_Tributarias, Produtos","Situacoes_Tributarias.oid_Situacao_Tributaria = Produtos.oid_Situacao_Tributaria AND Produtos.oid_Produto="+getSQLString(oid_Produto))))
	                    edItemNF.setOid_natureza_operacao(parametro_FixoED.getOID_CFOP_Diferenciado());
            }

            //*** Taxas por Estados
            List lista = TaxaBean.getByOID_Estado_Origem_Destino(edCidade_Orig.getOID_Estado(), edCidade_Dest.getOID_Estado());
            if (lista.size() > 0)
            {
                /** TAXA **/
                TaxaBean edTaxa = (TaxaBean) lista.iterator().next();

                //*** Valor Base == Valor Item
                edItemNF.setVL_Base_Calculo_ICMS(vlItem);
                //*** Taxas Tipos Produtos
                Taxa_ProdutoBean edTaxaProduto = Taxa_ProdutoBean.getByTaxa_Produto(edCidade_Orig.getOID_Estado(), edCidade_Dest.getOID_Estado(), oidTipoProduto);
                if (edTaxaProduto.getOID() > 0)
                {
                    edItemNF.setOid_Taxa_Produto(edTaxaProduto.getOID());
                    edItemNF.setPE_Aliquota_ICMS(edTaxaProduto.getPE_Aliquota_ICMS());
                    edItemNF.setPE_Aliquota_ICMS_Aprov(edTaxaProduto.getPE_Aliquota_ICMS_Aprov());

                    // Se % Base for maior que zero calcula-se o Valor da Base,
                    // Caso contrario Valor da Base recebe o Valor do Item
                    if (edTaxaProduto.getPE_Base_Calculo() > 0)
                    {
                        edItemNF.setVL_Base_Calculo_ICMS(Valor.round((edItemNF.getVL_Base_Calculo_ICMS() * edTaxaProduto.getPE_Base_Calculo())/100, 2));
                    }
                } else {
                    //% ICMS da TAXA
                    edItemNF.setPE_Aliquota_ICMS(edTaxa.getPE_Aliquota_ICMS());
                    edItemNF.setPE_Aliquota_ICMS_Aprov(edTaxa.getPE_Aliquota_ICMS());
                }

            } else throw new Excecoes("Taxa n�o localizada!");

            //*** CALCULA VALOR do ICMS caso n�o tenha sido calculado pelas Taxas
            if (edItemNF.getVL_ICMS() <= 0)
            {
                //edItemNF.setVL_ICMS(Valor.round(Valor.calcPercentual(vlItem, edItemNF.getPE_Aliquota_ICMS()),2));
                edItemNF.setVL_ICMS(Valor.round(Valor.calcPercentual(edItemNF.getVL_Base_Calculo_ICMS(), edItemNF.getPE_Aliquota_ICMS()),2));
                if (edItemNF.getPE_Aliquota_ICMS() <= 0)
                    edItemNF.setVL_Base_Calculo_ICMS(0);
            }
            if (edItemNF.getPE_Aliquota_ICMS_Aprov() > 0)
                //edItemNF.setVL_ICMS_Aprov(Valor.round(Valor.calcPercentual(vlItem, edItemNF.getPE_Aliquota_ICMS_Aprov()),2));
            	edItemNF.setVL_ICMS_Aprov(Valor.round(Valor.calcPercentual(edItemNF.getVL_Base_Calculo_ICMS(), edItemNF.getPE_Aliquota_ICMS_Aprov()),2));

            //*** Se nao aprova Credito zera Aliquota Aprovada.
            //    O Programa roda ate aqui para carrear a Aliquota normal do Produto
            if (notAprovCredito)
            {
                edItemNF.setVL_Base_Calculo_ICMS(0);
                edItemNF.setPE_Aliquota_ICMS_Aprov(0);
                edItemNF.setVL_ICMS_Aprov(0);
            }
            if ("S".equals(tipoICMS)) {
                edItemNF.setVL_Base_Calculo_ICMS(0);
                edItemNF.setPE_Aliquota_ICMS_Aprov(0);
                edItemNF.setVL_ICMS_Aprov(0);
                edItemNF.setPE_Aliquota_ICMS(0);
                edItemNF.setVL_ICMS(0);
            }


            return edItemNF;
        } catch(Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "getICMS_Produto()");
        }
    }

    public Item_Nota_Fiscal_TransacoesED getByRecord(Item_Nota_Fiscal_TransacoesED ed)throws Excecoes{

        String sql = null;
        ResultSet res = null;
        Item_Nota_Fiscal_TransacoesED edVolta = new Item_Nota_Fiscal_TransacoesED();

        try {

            sql = " SELECT * from Itens_Notas_Fiscais " +
            	  " WHERE OID_Item_Nota_Fiscal = '" + ed.getOID_Item_Nota_Fiscal() + "'";

            res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                edVolta = new Item_Nota_Fiscal_TransacoesED();
                edVolta.setOID_Pedido(res.getLong("oid_Pedido"));
                edVolta.setOID_Item_Nota_Fiscal(res.getLong("OID_Item_Nota_Fiscal"));
                edVolta.setOID_Nota_Fiscal(res.getString("OID_Nota_Fiscal"));
                edVolta.setOID_Produto(res.getString("oid_Produto"));
                edVolta.setNR_Lote_Produto(res.getString("NR_Lote_Produto"));
                edVolta.setOID_Item_Pedido(res.getLong("oid_Item_Pedido"));
                edVolta.setOid_natureza_operacao(res.getLong("oid_Natureza_Operacao"));
                edVolta.setOid_Taxa_Produto(res.getInt("oid_Taxa_Produto"));
                edVolta.setOid_Localizacao(res.getInt("oid_Localizacao"));
                edVolta.setOid_Preco_Produto(res.getInt("oid_Preco_Produto"));

                edVolta.setDM_Quantidade(res.getString("DM_Quantidade"));
                edVolta.setNR_Quantidade(res.getDouble("NR_Quantidade"));
                edVolta.setNR_QT_Devolucao(res.getDouble("NR_QT_Devolucao"));
                edVolta.setNR_QT_Atendido(res.getDouble("NR_QT_Atendido"));
                edVolta.setNR_Peso_Real(res.getDouble("NR_Peso_Real"));
                edVolta.setPesagem(new ProdutoBean().isPesagemByProduto(edVolta.getOID_Produto()));
                edVolta.setVL_Item(res.getDouble("VL_Item"));
                edVolta.setVL_Custo(res.getDouble("VL_Custo"));
                edVolta.setVL_Adicional(res.getDouble("VL_Adicional"));
                edVolta.setVL_Desconto(res.getDouble("VL_Desconto"));
                edVolta.setVL_Desconto_NF(res.getDouble("VL_Desconto_NF"));
                edVolta.setVL_Produto(res.getDouble("VL_Produto"));
                edVolta.setVL_ICMS(res.getDouble("VL_ICMS"));
                edVolta.setVL_ICMS_Aprov(res.getDouble("VL_ICMS_Aprov"));
                edVolta.setPE_Aliquota_ICMS(res.getDouble("PE_Aliquota_ICMS"));
                edVolta.setPE_Aliquota_ICMS_Aprov(res.getDouble("PE_Aliquota_ICMS_Aprov"));
                edVolta.setVL_Desconto(res.getDouble("VL_Desconto"));

                edVolta.setPE_Aliquota_IPI(res.getDouble("PE_Aliquota_IPI"));
                edVolta.setPE_Desconto(res.getDouble("PE_Desconto"));
                edVolta.setPE_Desconto_Extra(res.getDouble("PE_Desconto_Extra"));

                edVolta.setVL_Unitario(res.getDouble("VL_Unitario"));
                edVolta.setVL_IPI(res.getDouble("VL_IPI"));
                edVolta.setDM_Situacao(res.getString("DM_Situacao"));
                edVolta.setDm_Devolvido(res.getString("DM_Devolvido"));
            }
        } catch(Exception exc){
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "getByRecord()");
        } finally {
            super.closeResultset(res);
        }
        return edVolta;
    }

  	public ArrayList lista(Item_Nota_Fiscal_TransacoesED ed)throws Excecoes{

  	    String sql = null;
  	    ResultSet res = null;
  	    ResultSet resPedido = null;
  	    ArrayList list = new ArrayList();
  	    try {

  	        sql = " SELECT Itens_Notas_Fiscais.* " +
                  "       ,Produtos.*" +
  	        	  " FROM Itens_Notas_Fiscais" +
                  "     ,Produtos" +
                  "     ,Situacoes_Tributarias " +
  	        	  " WHERE Itens_Notas_Fiscais.OID_Produto = Produtos.oid_Produto" +
                  "   AND Produtos.oid_Situacao_Tributaria = Situacoes_Tributarias.oid_Situacao_Tributaria " +
  	        	  "   AND Itens_Notas_Fiscais.OID_Nota_Fiscal = '"+ed.getOID_Nota_Fiscal()+"'";
  	        sql +=" ORDER BY Situacoes_Tributarias.CD_Situacao_Tributaria, Itens_Notas_Fiscais.PE_Aliquota_ICMS DESC, Produtos.NM_Produto, Itens_Notas_Fiscais.oid_Natureza_Operacao";

  	        res = this.executasql.executarConsulta(sql);
  	        while (res.next())
            {
  	            Item_Nota_Fiscal_TransacoesED edVolta = new Item_Nota_Fiscal_TransacoesED();

  	            edVolta.setOID_Nota_Fiscal(res.getString("OID_Nota_Fiscal"));
  	            edVolta.setOID_Produto(res.getString("oid_Produto"));
  	            edVolta.setCD_Produto(res.getString("cd_Produto"));
  	            edVolta.setNM_Produto(res.getString("nm_Produto"));
  	            edVolta.setCD_Fornecedor(res.getString("cd_fornecedor"));
                edVolta.setNR_Lote_Produto(res.getString("NR_Lote_Produto"));

  	            edVolta.setOID_Pedido(res.getLong("OID_Pedido"));
  	            edVolta.setOID_Item_Pedido(res.getLong("OID_Item_Pedido"));
  	            edVolta.setOid_Taxa_Produto(res.getInt("oid_Taxa_Produto"));
  	            edVolta.setOid_Localizacao(res.getInt("oid_Localizacao"));
  	            edVolta.setOid_Preco_Produto(res.getInt("oid_Preco_Produto"));

  	            edVolta.setOID_Item_Nota_Fiscal(res.getLong("OID_Item_Nota_Fiscal"));
  	            edVolta.setVL_Item(res.getDouble("VL_Item"));
  	            edVolta.setVL_Unitario(res.getDouble("VL_Unitario"));
  	            edVolta.setVL_Desconto(res.getDouble("VL_Desconto"));
  	            edVolta.setPE_Desconto(res.getDouble("PE_Desconto"));
  	            edVolta.setPE_Desconto_Extra(res.getDouble("PE_Desconto_Extra"));

  	            edVolta.setVL_IPI(res.getDouble("VL_IPI"));
  	            edVolta.setVL_ICMS(res.getDouble("VL_ICMS"));
  	            edVolta.setVL_ICMS_Aprov(res.getDouble("VL_ICMS_Aprov"));
  	            edVolta.setVL_Base_Calculo_ICMS(res.getDouble("VL_Base_Calculo_ICMS"));
  	            edVolta.setVL_Produto(res.getDouble("VL_Produto"));
  	            edVolta.setVL_Custo(res.getDouble("VL_Custo"));
  	            edVolta.setVL_Adicional(res.getDouble("VL_Adicional"));
  	            edVolta.setVL_Desconto_NF(res.getDouble("VL_Desconto_NF"));
  	            edVolta.setDM_Quantidade(res.getString("DM_Quantidade"));
  	            edVolta.setNR_Quantidade(res.getDouble("NR_Quantidade"));
  	            edVolta.setNR_QT_Devolucao(res.getDouble("NR_QT_Devolucao"));
  	            edVolta.setNR_QT_Atendido(res.getDouble("NR_QT_Atendido"));
  	            edVolta.setNR_Peso_Real(res.getDouble("NR_Peso_Real"));
  	            edVolta.setPesagem(new ProdutoBean().isPesagemByProduto(edVolta.getOID_Produto()));
  	            edVolta.setOid_natureza_operacao(res.getLong("oid_Natureza_Operacao"));
  	            edVolta.setPE_Aliquota_ICMS(res.getDouble("PE_Aliquota_ICMS"));
  	            edVolta.setPE_Aliquota_ICMS_Aprov(res.getDouble("PE_Aliquota_ICMS_Aprov"));
  	            edVolta.setDM_Situacao(res.getString("DM_Situacao"));
  	            edVolta.setDm_Devolvido(res.getString("DM_Devolvido"));
            	edVolta.setOid_Produto_Cliente(res.getString("oid_Produto_Cliente"));

  	            edVolta.setNM_Pedido("");
  	            if (edVolta.getOID_Pedido() > 0)
                {
  	                sql = " SELECT NR_Pedido FROM Pedidos WHERE oid_Pedido= " + edVolta.getOID_Pedido();
  	                resPedido = this.executasql.executarConsulta(sql);
  	                while (resPedido.next())
                    {
  	                    edVolta.setNR_Pedido(resPedido.getLong("NR_Pedido"));
  	                    if (edVolta.getNR_Pedido() > 0)
  	                        edVolta.setNM_Pedido(resPedido.getString("NR_Pedido"));
  	                }
  	            }
  	            list.add(edVolta);
  	        }
  	    } catch(Exception exc){
  	    	exc.printStackTrace();
  	        throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "lista()");
  	    } finally {
            super.closeResultset(res);
            super.closeResultset(resPedido);
        }
  	    return list;
  	}

  	public ArrayList listaItemPedido(Item_Nota_Fiscal_TransacoesED ed)throws Excecoes{

  		String sql = null;
  	    ResultSet res = null;
  	    ArrayList list = new ArrayList();
  	    try{

  	        sql = " SELECT " +
  	        	  " Itens_Notas_Fiscais.*, " +
  	        	  " Notas_Fiscais.NR_Nota_Fiscal, " +
  	        	  " Notas_Fiscais.DT_emissao " +
  	        	  " FROM " +
  	        	  " Itens_Notas_Fiscais, Notas_Fiscais" ;
  	        if ( doValida(ed.getDM_Pedido()) )
  	        	sql += ", Pedidos, Itens_Pedidos " ;
  	        sql +=" WHERE " +
  	        	  "Itens_Notas_Fiscais.OID_Nota_Fiscal = Notas_Fiscais.OID_Nota_Fiscal ";

  	        if (ed.getOID_Item_Pedido() > 0)
  	            sql +=" AND   Itens_Notas_Fiscais.OID_Item_Pedido = '" + ed.getOID_Item_Pedido() + "'";
  	        if (doValida(ed.getOID_Pessoa()))
  	            sql +=" AND   Itens_Notas_Fiscais.OID_Pessoa = '" + ed.getOID_Pessoa() + "'";
  	        if (doValida(ed.getOID_Produto()))
  	            sql +=" AND   Itens_Notas_Fiscais.OID_Produto = '" + ed.getOID_Produto() + "'";
  	        if (doValida(ed.getDM_Pedido()))
            {
  	            sql +="   AND Pedidos.DM_Pedido = '" +ed.getDM_Pedido()+ "'" +
  	                  "   AND Itens_Notas_Fiscais.OID_Item_Pedido = Itens_Pedidos.oid_Item_Pedido" +
  	                  "   AND Itens_Pedidos.oid_Pedido = Pedidos.oid_Pedido";
            }
            if (doValida(ed.getDM_Tipo_Nota_Fiscal()))
                sql +="   AND Notas_Fiscais.DM_Tipo_Nota_Fiscal = "+getSQLString(ed.getDM_Tipo_Nota_Fiscal());
            sql +=" ORDER BY Notas_Fiscais.DT_Emissao";

  	        FormataDataBean DataFormatada = new FormataDataBean();
  	        res = this.executasql.executarConsulta(sql);
  	        while (res.next())
  	        {
  	            Item_Nota_Fiscal_TransacoesED edVolta = new Item_Nota_Fiscal_TransacoesED();

  	            edVolta.setOID_Produto(res.getString("oid_Produto"));
  	            edVolta.setOID_Pedido(res.getLong("OID_Pedido"));
  	            edVolta.setOID_Item_Pedido(res.getLong("OID_Item_Pedido"));
  	            edVolta.setOid_Taxa_Produto(res.getInt("oid_Taxa_Produto"));
  	            edVolta.setOid_Localizacao(res.getInt("oid_Localizacao"));
  	            edVolta.setOid_Preco_Produto(res.getInt("oid_Preco_Produto"));
                edVolta.setNR_Lote_Produto(res.getString("NR_Lote_Produto"));

  	            edVolta.setOID_Item_Nota_Fiscal(res.getLong("OID_Item_Nota_Fiscal"));
  	            edVolta.setVL_Item(res.getDouble("VL_Item"));
  	            edVolta.setVL_Unitario(res.getDouble("VL_Unitario"));
  	            edVolta.setVL_Desconto(res.getDouble("VL_Desconto"));
  	            edVolta.setVL_ICMS(res.getDouble("VL_ICMS"));
  	            edVolta.setVL_Produto(res.getDouble("VL_Produto"));
  	            edVolta.setNR_QT_Atendido(res.getDouble("NR_QT_Atendido"));
  	            edVolta.setNR_Peso_Real(res.getDouble("NR_Peso_Real"));
  	            edVolta.setPesagem(new ProdutoBean().isPesagemByProduto(edVolta.getOID_Produto()));
  	            edVolta.setNR_Nota_Fiscal(res.getString("NR_Nota_Fiscal"));
  	            edVolta.setOID_Nota_Fiscal(res.getString("OID_Nota_Fiscal"));
  	            edVolta.setVL_Custo(res.getDouble("VL_Custo"));
  	            edVolta.setVL_Adicional(res.getDouble("VL_Adicional"));
  	            edVolta.setVL_Desconto_NF(res.getDouble("VL_Desconto_NF"));
  	            edVolta.setDM_Quantidade(res.getString("DM_Quantidade"));
  	            edVolta.setNR_Quantidade(res.getDouble("NR_Quantidade"));
  	            edVolta.setNR_QT_Devolucao(res.getDouble("NR_QT_Devolucao"));

  	            edVolta.setDT_Pedido(DataFormatada.getDT_FormataData(res.getString("DT_Emissao")));
  	            edVolta.setNM_Pedido("");
  	            if (edVolta.getOID_Pedido() > 0)
  	            {
                    ed.setNR_Pedido(getTableIntValue("NR_Pedido","Pedidos","oid_Pedido="+edVolta.getOID_Pedido()));
  	                edVolta.setNM_Pedido(String.valueOf(ed.getNR_Pedido()));
  	            }
  	            list.add(edVolta);
  	        }
  	    } catch(Exception exc){
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "listaItemPedido()");
        } finally {
            super.closeResultset(res);
        }
        return list;
  	}

  	/** Atualiza Quantidade, Valor pelos Itens da Nota **/
    public void atualizaItensNota(Item_Nota_Fiscal_TransacoesED ed) throws Excecoes {

        String sql = "";
        try {
            if (!doValida(ed.getOID_Nota_Fiscal()))
                throw new Mensagens("ID Nota Fiscal n�o informado!");

            //M�todo alterado devido o erro ao calcular ICMS na NF de sa�da da Daudt.
            //Altera��o: sql +="    ,VL_ICMS = "+ vl_Icms_Aprov;

            // Busca o somatorio dos itens jah incluidos na NF
            sql="select " +
        	"sum(VL_Produto) as vl_Produto " +
        	",sum(vl_Comissao) as vl_Comissao" +
        	",sum(NR_Quantidade) as nr_Quantidade " +
        	",sum(VL_ICMS_APROV) as vl_Icms_Aprov " +
        	",sum(vl_base_calculo_icms) as vl_Base_Calculo_Icms " +
        	"from " +
        	"Itens_Notas_Fiscais where oid_Nota_Fiscal = "+getSQLString(ed.getOID_Nota_Fiscal());
        	ResultSet rsSomaItemNF = executasql.executarConsulta(sql);
        	rsSomaItemNF.next();

        	double novo_valor = rsSomaItemNF.getDouble("vl_Produto");
        	double vl_Comissao = rsSomaItemNF.getDouble("vl_Comissao");
        	double nr_Quantidade = rsSomaItemNF.getDouble("nr_Quantidade");
        	double vl_Icms_Aprov = rsSomaItemNF.getDouble("vl_Icms_Aprov");
        	double vl_Base_Calculo_Icms = rsSomaItemNF.getDouble("vl_Base_Calculo_Icms");

            //tenta somar os servicos ao valor da nf
            try{
            	novo_valor += getTableDoubleValue("sum(VL_Total)", "Servicos_Notas_Fiscais","oid_Nota_Fiscal = "+getSQLString(ed.getOID_Nota_Fiscal()));
            } catch (Exception e){
            	//do nothing
            }

            sql =  " UPDATE Notas_Fiscais SET " +
                   "    oid_Nota_Fiscal = oid_Nota_Fiscal, " +
                   "    vl_Comissao = "+ vl_Comissao;
            if (ed.isAtualizaItensNota())
            	sql +="    ,NR_Itens = "+ nr_Quantidade;
            if (ed.isAtualizaValorNota() || "D".equals(ed.getDM_Tipo_Nota_Fiscal())){
                sql +="    ,VL_Nota_Fiscal = "+ novo_valor;
                sql +="    ,VL_ICMS = "+ vl_Icms_Aprov;
                sql +="    ,vl_base_calculo_icms = "+ vl_Base_Calculo_Icms;
            }
            sql +=" WHERE OID_Nota_Fiscal = "+getSQLString(ed.getOID_Nota_Fiscal());

            if (!"D".equals(ed.getDM_Tipo_Nota_Fiscal())){
                new Nota_Fiscal_EletronicaBD(this.sql).atualizaValorICMS(ed.getOID_Nota_Fiscal());
            }
            executasql.executarUpdate(sql);
        } catch(Exception exc){
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "atualizaItensNota()");
        }
    }

    //*** Recalcula pre�o dos itens pela Condi��o de Pagamento e Tabela de Pre�os!
    //    Obs.: Tbm existente em Item_PedidoED: recalculaPrecoItensPedido()
    public void recalculaPrecoItensNota(Nota_Fiscal_EletronicaED ed, boolean byTabela, boolean byCondicao) throws Excecoes {

        String sql = null;
        try {

            if (!doValida(ed.getOid_nota_fiscal()))
                throw new Excecoes("Nota Fiscal n�o informada!");
            if (ed.getOid_Condicao_Pagamento() < 1)
                throw new Mensagens("Condi��o de Pagamento n�o informada!");

            ArrayList lista = this.lista(new Item_Nota_Fiscal_TransacoesED(ed.getOid_nota_fiscal()));
            for (int i=0; i < lista.size(); i++)
            {
                Item_Nota_Fiscal_TransacoesED edItem = (Item_Nota_Fiscal_TransacoesED) lista.get(i);
                if ("C".equals(edItem.getDM_Situacao()))
                    return;

                ProdutoED edProduto = (ProdutoED) new ProdutoBD(executasql).getByRecord(new ProdutoED(edItem.getOID_Produto()));

                /** by TABELA DE PRE�OS **/
                if (byTabela)
                    edProduto.edPreco = new Pro004Bean().getPrecoByTabela((int)ed.getOid_Condicao_Pagamento(), edProduto.getOID_Produto(), ed.getOid_Tabela_Venda());
                else
                /** by CONDI��O DE PAGAMENTO (mesma Tabela) **/
                if (byCondicao)
                    edProduto.edPreco = new Pro004Bean().getPrecoByOIDCondPagamento((int)ed.getOid_Condicao_Pagamento(), edItem.getOid_Preco_Produto());

                boolean existPreco = edProduto.edPreco.getOID_Preco_Produto() > 0;
                double nrQtAtendido = edItem.getNR_Quantidade();
                /** Se n�o existe o ITEM NA TABELA, ZERA QUANTIDADE DO MESMO **/
                if (!existPreco)
                    nrQtAtendido = 0;
                double vlUnitario = edProduto.edPreco.getVL_Venda();
                double vlPrecoCusto = edProduto.edPreco.getVL_Preco_Custo();
                double peMargem = edItem.getPE_Margem_Contribuicao();
                double vlMargem = Valor.round((vlUnitario/100) * peMargem, 2);
                vlUnitario = (vlUnitario + vlMargem);
                double vlVenda = vlUnitario;
                double peDesconto = edItem.getPE_Desconto();
                double vlDesconto = Valor.round((vlUnitario/100)*peDesconto, 2);
                vlUnitario = (vlUnitario - vlDesconto);

                //*** Calcula Valores
                vlMargem = (vlMargem * nrQtAtendido);
                vlDesconto = (vlDesconto * nrQtAtendido);
                double vlItem = (nrQtAtendido * vlUnitario);
                double vlProduto = (vlItem + vlDesconto);

                //*** Grava Valor do Item
                sql = " UPDATE Itens_Notas_Fiscais SET" +
                      "      VL_Item = " + Valor.round(vlItem, 2) +
                      "     ,VL_Desconto = " + Valor.round(vlDesconto, 2) +
                      "     ,VL_Produto = " + Valor.round(vlProduto, 2) +
                      "     ,VL_Unitario = " + Valor.round(vlVenda, 2) +
                      "     ,VL_Custo = " + Valor.round(vlPrecoCusto, 2) +
                      "     ,VL_Margem_Contribuicao = " + Valor.round(vlMargem, 2) +
                      "     ,NR_Quantidade = "+(existPreco ? edItem.getNR_Quantidade() : 0) +
                      "     ,oid_Preco_Produto = " + edProduto.edPreco.getOID_Preco_Produto();
                if (!existPreco)
                    sql += "     ,Usuario_Stamp = "+getSQLString(ed.getUsuario_Stamp())+
                           "     ,DT_Stamp = "+getSQLDate(Data.getDataDMY())+
                           "     ,DM_Stamp = 'R'";
                sql += " WHERE oid_Item_Nota_Fiscal = " + edItem.getOID_Item_Nota_Fiscal();
                executasql.executarUpdate(sql);

                this.atualizaMargemFinanceiraItem(edItem);

            }
        } catch(Exception exc){
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "recalculaPrecoItensNota()");
        }
    }
  	/** Atualiza Comissao do item **/
    public void atualizaComissaoItem(Item_Nota_Fiscal_TransacoesED ed) throws Excecoes {

        String sql = "";
        try {
            sql =" Update "+
            	 " Itens_Notas_Fiscais SET " +
                 " vl_Comissao = " + ed.getVl_Comissao() + " ";
            sql+=" Where "+
            	 " oid_item_nota_fiscal = " + ed.getOID_Item_Nota_Fiscal();
            executasql.executarUpdate(sql);

            this.atualizaMargemFinanceiraItem(ed);

        } catch(Exception exc){
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "atualizaComissaoItem()");
        }
    }

    /** Atualiza Margem de contribuicao financeira do item **/
    public void atualizaMargemFinanceiraItem(Item_Nota_Fiscal_TransacoesED ed) throws Excecoes {

        String sql = "";
        try {
            sql =" Update "+
            	 " Itens_Notas_Fiscais SET " +
                 " vl_Margem_Financeira = ( (vl_item/nr_qt_atendido) - ("+ parametro_FixoED.getPE_Aliquota_PIS_COFINS() + " * (vl_item/nr_qt_atendido) / 100) - vl_custo  - (vl_comissao/nr_qt_atendido)) " ;
            sql+=" Where "+
            	 " oid_item_nota_fiscal = " + ed.getOID_Item_Nota_Fiscal();
            executasql.executarUpdate(sql);

        } catch(Exception exc){
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "atualizaMargemFinanceiraItem()");
        }
    }
    /** Calcula o custo de compra do item  **/
    public void calcularCustoDaCompra(Nota_Fiscal_EletronicaED ed) throws Excecoes {

        String sql = "";
        double vlMlt = 0, vlCst=0, vlItn=0, vlIpi=0, vlIap=0, vlFrr=0, vlSer=0, vlDer=0, vlAer=0, vlTFrr=0, vlTSer=0, vlTDer=0, vlTAer=0, qtAte=0;
        ResultSet res = null;
        try {
            sql =" Select "+
            	 " oid_item_nota_fiscal ," +
            	 " vl_item, "+
            	 " vl_icms_aprov,"+
            	 " vl_ipi, "+
            	 " nr_qt_atendido "+
                 " From " +
                 " Itens_Notas_Fiscais "+
                 " Where "+
            	 " oid_nota_fiscal = '" + ed.getOid_nota_fiscal() + "'";
            res = this.executasql.executarConsulta(sql);
            while (res.next()) {
            	/** Guarda valores do item da NF **/
            	vlItn = res.getDouble("vl_item");
            	vlIpi = res.getDouble("vl_ipi");
            	vlIap = res.getDouble("vl_icms_aprov");
            	qtAte = res.getDouble("nr_qt_atendido");
            	/** Calcula o multiplicador para o rateio **/
            	vlMlt = vlItn / ed.getVl_nota_fiscal();
            	/** Faz o rateio dos valores da NF para os itens **/
            	vlFrr = vlMlt * ed.getVl_total_frete();
            	vlSer = vlMlt * ed.getVl_total_seguro();
            	vlDer = vlMlt * ed.getVl_total_despesas();
            	vlAer = vlMlt * ed.getVL_Adicional(); /** Aqui est� o frete que � informado por fora da NF **/
            	/** Guarda o valor do item rateado para o fechamento final **/
            	vlTFrr += vlFrr ; vlTSer += vlSer;vlTDer += vlDer;vlTAer += vlAer;
            	/** Se for o �ltimo item da nf, verifica se h� diferen�a entre o rateio e o valor original **/
            	if (res.isLast()) {
            		vlFrr = vlFrr + ( ed.getVl_total_frete() - vlTFrr );
                	vlSer = vlSer + ( ed.getVl_total_seguro() - vlTSer );
                	vlDer = vlDer + ( ed.getVl_total_despesas() - vlTDer );
                	vlAer = vlAer + ( ed.getVL_Adicional() - vlTAer );
            	}
            	/** Calcula o custo **/
            	/** Pre�o - icms recuperado  - ( 9,25 * pre�o / 100 ) + ipi do item + rateio despesa acessoria NF + rateio frete NF + rateio valor do seguro NF. **/
            	vlCst = Valor.round((vlItn - vlIap - (parametro_FixoED.getPE_Aliquota_PIS_COFINS() * vlItn / 100) + vlIpi + vlFrr + vlSer + vlDer + vlAer ),5) ;
            	vlCst = vlCst / qtAte; /** Calcula o valor unit�rio **/
            	/** Atualiza o item com o novo custo **/
                sql =" Update "+
           	 		 " Itens_Notas_Fiscais "+
           	 		 " Set "+
           	 		 " vl_custo = " + vlCst +
                     " Where "+
           	 		 " oid_item_nota_fiscal = '" + res.getString("oid_item_nota_fiscal") + "'";
                executasql.executarUpdate(sql);
            }

        } catch(Exception exc){
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "calcularCustoDaCompra	()");
        }
    }
    /**
     * Metodo para registrar a devolucao dos itens das notas fiscais de entrada pelo metodo FIFO.
     * @param pOid_Produto_Cliente = oid do produto sendo devolvido.
     * @param pNr_Quantidade_Saida = quantidade a devolver.
     * @return nrNFsEntrada = numero(s) da(s) nf(s) de entrada que foi devolvida.
     * @throws Excecoes
     */
    public ArrayList devolveItem(Item_Nota_Fiscal_TransacoesED edNFi, String pOid_Produto_Cliente, double pNr_Quantidade_Saida) throws Excecoes{
  	  String dm_busca_nf_entrada="", sql = null, dm_Devolvido = "", oid_Item_Nota_Fiscal = "", oid_Nota_Fiscal = "", nr_Nota_Fiscal = "", nr_Lote_Produto = "", dm_Rotatividade="";
  	  double nr_Quantidade_Ja_Devolvida = 0, nr_Quantidade_Movimento = 0, nr_Saldo = 0, vl_Unitario=0, nr_Quantidade_Retirada =0;
  	  int Itens_Nao_Devolvidos = 0;
  	  ResultSet itnE = null, itND = null;
  	  ArrayList lstNF = new ArrayList();

  	  try{
          // Procura no cliente se deve buscar a nota de entrada ou n�o para devolver.
  		  // Feito para a Miro que n�o tinha nota de entrada de alguns clientes. Gelson.
          ResultSet res = null;
          try {

        	  sql= " select clientes.dm_busca_nf_entrada " +
 		  	   " from clientes, notas_fiscais " +
 		  	   " where notas_fiscais.oid_pessoa_destinatario = clientes.oid_pessoa " +
 		  	   " and notas_fiscais.oid_nota_fiscal = '" + edNFi.getOID_Nota_Fiscal() + "'";
              res = this.executasql.executarConsulta(sql);
              while (res.next()) {
            	  dm_busca_nf_entrada = res.getString("dm_busca_nf_entrada");
              }
          } catch(Exception exc){
              throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                      "devolveItem");
          }

  		  if ("N".equals(dm_busca_nf_entrada)){
  			  // Se o campo dm_busca_nf_entrada for igual a N passa lotado.
  		  }else{

	  		  //Pega indicativo de rotatividade
	  		  dm_Rotatividade= getTableStringValue("dm_rotatividade","Produtos_Clientes","oid_produto_cliente = "+pOid_Produto_Cliente);
	  		  //Busca os itens nas nf de entrada (Remessa para armazenagem) que nao foram devolvidos ainda.

	  		  sql="SELECT "+
	  		  	  "nfe.oid_Nota_Fiscal, "+
	  			  "nfe.nr_Nota_Fiscal, "+
	  			  "itn.oid_Item_Nota_Fiscal, "+
	  			  "itn.nr_Quantidade, "+
	  			  "itn.nr_Qt_Devolucao, "+
	  			  "itn.nr_Lote_Produto, "+
	  			  "itn.vl_unitario "+
	  			  "FROM "+
	  			  "Notas_Fiscais as nfe, "+
	  			  "Itens_Notas_Fiscais as itn "+
	  			  "WHERE "+
	  			  "nfe.oid_Nota_Fiscal = itn.oid_Nota_Fiscal and "+
	  			  "nfe.dm_tipo_nota_fiscal = 'E' and "+
	  			  "nfe.dm_Devolvido <> 'S' and "+
	  			  "itn.oid_Produto_Cliente = '"+ pOid_Produto_Cliente +"' and " +
	  			  "itn.dm_Devolvido <> 'S' ";

	  		  if (doValida(edNFi.getNR_Lote_Produto())){
	      		 sql +=" and itn.NR_Lote_Produto = '"+ edNFi.getNR_Lote_Produto() +"' ";
	  		  }
	  		  sql +=" ORDER BY ";
	          if ("FIFO".equals(dm_Rotatividade))
	  			sql+="nfe.dt_Entrada, nfe.hr_Entrada"; // Crescente se FIFO
	  		  else
	  			sql+="nfe.dt_Entrada desc, nfe.hr_Entrada desc"; // Descrescente se LIFO

	  		  itnE = executasql.executarConsulta(sql);
	  		  //Itera os itens
	  		  while( itnE.next() ){
	  			  oid_Nota_Fiscal = itnE.getString("oid_Nota_Fiscal");
	  			  oid_Item_Nota_Fiscal = itnE.getString("oid_Item_Nota_Fiscal");
	  			  nr_Nota_Fiscal = itnE.getString("nr_Nota_Fiscal");
	  			  nr_Lote_Produto = itnE.getString("nr_Lote_Produto");

	  			  nr_Quantidade_Movimento = itnE.getDouble("nr_Quantidade");
	  			  nr_Quantidade_Ja_Devolvida = itnE.getDouble("nr_Qt_Devolucao");
	  			  vl_Unitario = itnE.getDouble("vl_Unitario");
	  			  //Verifica quanto pode ser devolvido do item, desta nfe
	  			  if ((pNr_Quantidade_Saida + nr_Quantidade_Ja_Devolvida) > nr_Quantidade_Movimento){
	  				  // Se somente uma parte da sa�da
	  				  nr_Saldo =  pNr_Quantidade_Saida - (nr_Quantidade_Movimento - nr_Quantidade_Ja_Devolvida); // Ent�o sobrou algo para outra nota
	  				  nr_Quantidade_Ja_Devolvida = nr_Quantidade_Movimento;
	  			  } else {
	  				  // Se toda a sa�da
	  				  nr_Saldo = 0; // Ent�o sobrou nada para outro nota.
	  				  nr_Quantidade_Ja_Devolvida = nr_Quantidade_Ja_Devolvida + pNr_Quantidade_Saida;
	  			  }
	  			  if (nr_Quantidade_Ja_Devolvida == nr_Quantidade_Movimento) {
	  				  // Ent�o n�o h� nada mais a devolver deste item da nfe
	  				  dm_Devolvido = "S";
	  			  } else {
	  				  dm_Devolvido = "N";
	  			  }

	              // Calcula a quantidade que conseguiu retirar
	  			  nr_Quantidade_Retirada = pNr_Quantidade_Saida - nr_Saldo;

	  			  // Insere v�nculos entre as notas fiscais de entrada, sa�da e devolvida.

	              sql = " INSERT INTO Itens_Notas_Fiscais_Vinculadas (" +
	              		"		OID_Item_Nota_Fiscal_Entrada" +
	              		"		,OID_Item_Nota_Fiscal_Devolvida" +
	              		"		,NR_QT_Devolucao" +
	              		"		,Dt_Stamp)";
	              sql+= " VALUES ( " +
	              	  oid_Item_Nota_Fiscal +
		        	  "," + edNFi.getOID_Item_Nota_Fiscal()  +
		        	  "," + nr_Quantidade_Retirada  +
		        	  ",'" + Data.getDataDMY() + "'" +
		        	  ")";
	              executasql.executarUpdate(sql);

	  			  //Atualiza o item da nfe de entrada
	  			  sql="UPDATE "+
	  			  	  "Itens_Notas_Fiscais "+
	  			  	  "SET "+
	  			  	  "nr_Qt_Devolucao = "+ nr_Quantidade_Ja_Devolvida + ", " +
	  			  	  "dm_Devolvido = '" + dm_Devolvido + "' "+
	  			  	  "WHERE "+
	  			  	  "oid_Item_Nota_Fiscal = '"+ oid_Item_Nota_Fiscal + "'" ;
	  			  executasql.executarUpdate(sql);
	  			  //Testa se a NF j� devolveu todos os itens
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
	  			  // Pega o n�mero da NF para colocar na NF de devolucao

	  			  lstNF.add(nr_Nota_Fiscal);
	  			  //Se o saldo for zero para por aqui, sen�o continua a buscar o item para baixar.
	  			  if (nr_Saldo == 0) {
	  				  break;
	  			  } else {
	  				  pNr_Quantidade_Saida = nr_Saldo;
	  			  }
	  		  }
	  		  // Atualiza a nota fiscal de devolucao com o valor do item da nota de entrada
	  		  edNFi.setVL_Unitario(vl_Unitario); // Atualiza o valor unitario
	  		  edNFi.setVL_Produto(edNFi.getVL_Unitario() * edNFi.getNR_Quantidade()); // Atualiza o valor do produto
	  		  edNFi.setVL_Item(edNFi.getVL_Unitario() * edNFi.getNR_Quantidade()); // Atualiza o valor do item
	  		  // Se n�o achou nas notas de entrada, marca o item da nf de devolu��o para evitar a finaliza��o

	  		  if (vl_Unitario <=0) edNFi.setDm_Devolvido("X");
  		  }
  		  this.altera(edNFi);
  		  return lstNF;
  	  }
  	  catch(Exception exc){
          throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
    		  		"devolveItem(String pOid_Produto_Cliente, double pNr_Quantidade_Saida)");
  	  }
    }


  	public ArrayList lista_Codigo(Item_Nota_Fiscal_TransacoesED ed)throws Excecoes{

  	    String sql = null;
  	    ResultSet res = null;
  	    ResultSet resPedido = null;
  	    ArrayList list = new ArrayList();
  	    try {

  	        sql = " SELECT Itens_Notas_Fiscais.* " +
                  "       ,Produtos.*" +
  	        	  " FROM Itens_Notas_Fiscais" +
                  "     ,Produtos" +
                  "     ,Situacoes_Tributarias " +
  	        	  " WHERE Itens_Notas_Fiscais.OID_Produto = Produtos.oid_Produto" +
                  "   AND Produtos.oid_Situacao_Tributaria = Situacoes_Tributarias.oid_Situacao_Tributaria " +
  	        	  "   AND Itens_Notas_Fiscais.OID_Nota_Fiscal = '"+ed.getOID_Nota_Fiscal()+"'";
  	        sql +=" ORDER BY Produtos.oid, Situacoes_Tributarias.CD_Situacao_Tributaria, Itens_Notas_Fiscais.PE_Aliquota_ICMS DESC, Itens_Notas_Fiscais.oid_Natureza_Operacao";

  	        res = this.executasql.executarConsulta(sql);
  	        while (res.next())
            {
  	            Item_Nota_Fiscal_TransacoesED edVolta = new Item_Nota_Fiscal_TransacoesED();

  	            edVolta.setOID_Nota_Fiscal(res.getString("OID_Nota_Fiscal"));
  	            edVolta.setOID_Produto(res.getString("oid_Produto"));
  	            edVolta.setCD_Produto(res.getString("cd_Produto"));
  	            edVolta.setNM_Produto(res.getString("nm_Produto"));
  	            edVolta.setCD_Fornecedor(res.getString("cd_fornecedor"));
                edVolta.setNR_Lote_Produto(res.getString("NR_Lote_Produto"));

  	            edVolta.setOID_Pedido(res.getLong("OID_Pedido"));
  	            edVolta.setOID_Item_Pedido(res.getLong("OID_Item_Pedido"));
  	            edVolta.setOid_Taxa_Produto(res.getInt("oid_Taxa_Produto"));
  	            edVolta.setOid_Localizacao(res.getInt("oid_Localizacao"));
  	            edVolta.setOid_Preco_Produto(res.getInt("oid_Preco_Produto"));

  	            edVolta.setOID_Item_Nota_Fiscal(res.getLong("OID_Item_Nota_Fiscal"));
  	            edVolta.setVL_Item(res.getDouble("VL_Item"));
  	            edVolta.setVL_Unitario(res.getDouble("VL_Unitario"));
  	            edVolta.setVL_Desconto(res.getDouble("VL_Desconto"));
  	            edVolta.setPE_Desconto(res.getDouble("PE_Desconto"));
  	            edVolta.setPE_Desconto_Extra(res.getDouble("PE_Desconto_Extra"));

  	            edVolta.setVL_IPI(res.getDouble("VL_IPI"));
  	            edVolta.setVL_ICMS(res.getDouble("VL_ICMS"));
  	            edVolta.setVL_ICMS_Aprov(res.getDouble("VL_ICMS_Aprov"));
  	            edVolta.setVL_Base_Calculo_ICMS(res.getDouble("VL_Base_Calculo_ICMS"));
  	            edVolta.setVL_Produto(res.getDouble("VL_Produto"));
  	            edVolta.setVL_Custo(res.getDouble("VL_Custo"));
  	            edVolta.setVL_Adicional(res.getDouble("VL_Adicional"));
  	            edVolta.setVL_Desconto_NF(res.getDouble("VL_Desconto_NF"));
  	            edVolta.setDM_Quantidade(res.getString("DM_Quantidade"));
  	            edVolta.setNR_Quantidade(res.getDouble("NR_Quantidade"));
  	            edVolta.setNR_QT_Devolucao(res.getDouble("NR_QT_Devolucao"));
  	            edVolta.setNR_QT_Atendido(res.getDouble("NR_QT_Atendido"));
  	            edVolta.setNR_Peso_Real(res.getDouble("NR_Peso_Real"));
  	            edVolta.setPesagem(new ProdutoBean().isPesagemByProduto(edVolta.getOID_Produto()));
  	            edVolta.setOid_natureza_operacao(res.getLong("oid_Natureza_Operacao"));
  	            edVolta.setPE_Aliquota_ICMS(res.getDouble("PE_Aliquota_ICMS"));
  	            edVolta.setPE_Aliquota_ICMS_Aprov(res.getDouble("PE_Aliquota_ICMS_Aprov"));
  	            edVolta.setDM_Situacao(res.getString("DM_Situacao"));
  	            edVolta.setDm_Devolvido(res.getString("DM_Devolvido"));
            	edVolta.setOid_Produto_Cliente(res.getString("oid_Produto_Cliente"));

  	            edVolta.setNM_Pedido("");
  	            if (edVolta.getOID_Pedido() > 0)
                {
  	                sql = " SELECT NR_Pedido FROM Pedidos WHERE oid_Pedido= " + edVolta.getOID_Pedido();
  	                resPedido = this.executasql.executarConsulta(sql);
  	                while (resPedido.next())
                    {
  	                    edVolta.setNR_Pedido(resPedido.getLong("NR_Pedido"));
  	                    if (edVolta.getNR_Pedido() > 0)
  	                        edVolta.setNM_Pedido(resPedido.getString("NR_Pedido"));
  	                }
  	            }
  	            list.add(edVolta);
  	        }
  	    } catch(Exception exc){
  	    	exc.printStackTrace();
  	        throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "lista()");
  	    } finally {
            super.closeResultset(res);
            super.closeResultset(resPedido);
        }
  	    return list;
  	}

}