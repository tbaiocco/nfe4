package com.master.bd;

/**
 * Empresa: ÊxitoLogística Mastercom
 * Autor: André Valadas
*/
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.master.ed.Item_Nota_Fiscal_TransacoesED;
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

public class Servico_Nota_Fiscal_TransacoesBD extends BancoUtil {

    private ExecutaSQL executasql;
    Parametro_FixoED parametro_FixoED = Parametro_FixoED.getInstancia();

    public Servico_Nota_Fiscal_TransacoesBD(ExecutaSQL sql) {
        super(sql);
        this.executasql = sql;
    }

    public Item_Nota_Fiscal_TransacoesED inclui(Item_Nota_Fiscal_TransacoesED ed) throws Excecoes {

        String sql = null;
        try{
            //*** Para inserção de multiplos registros utilizando a mesma conexão, o oid podera vir informado
            if (ed.getOID_Item_Nota_Fiscal() > 0)
            {
                if (super.doExiste("Servicos_Notas_Fiscais", "oid_Servico_Nota_Fiscal = "+ed.getOID_Item_Nota_Fiscal()))
                    throw new Excecoes("ID Item Nota Fiscal já existe!");
            } else ed.setOID_Item_Nota_Fiscal(new Long(getAutoIncremento("oid_Servico_Nota_Fiscal","Servicos_Notas_Fiscais")).longValue());

            Comissao_VendedorED edCV = new Comissao_VendedorED();
            Comissao_VendedorBD bdCV = new Comissao_VendedorBD(this.sql);

        	/** Busca dados da nota fiscal **/
        	Nota_Fiscal_EletronicaED edNF = new Nota_Fiscal_EletronicaED();
        	edNF.setOid_nota_fiscal(ed.getOID_Nota_Fiscal());
        	edNF = new Nota_Fiscal_EletronicaBD(this.sql).getByRecord(edNF);

            sql = " INSERT INTO Servicos_Notas_Fiscais (" +
            	  "		 OID_Servico_Nota_Fiscal" +
            	  "		,OID_Nota_Fiscal" +
            	  "		,OID_Tipo_Servico" +
            	  "		,pe_issqn" +
            	  "		,vl_issqn" +
            	  "		,vl_unitario" +
            	  "		,NR_Quantidade" +
            	  "		,vl_total" +
            	  "		,Dt_Stamp" +
            	  "		,Usuario_Stamp" +
            	  "		,DM_Stamp)";
            sql+= " VALUES ( " +
            	  ed.getOID_Item_Nota_Fiscal() +
            	  ",'" + ed.getOID_Nota_Fiscal()+  "'" +
            	  "," + ed.getOid_Tipo_Servico() +
            	  "," + ed.getPE_ISSQN() +
            	  "," + ed.getVL_ISSQN() +
            	  "," + ed.getVL_Unitario() +
            	  "," + ed.getNR_Quantidade() +
            	  "," + ed.getVL_Item() +
            	  ",'" + Data.getDataDMY() + "'" +
            	  ",'" + ed.getUser() + "'" +
            	  ",'N')";
// System.out.println(sql);
            executasql.executarUpdate(sql);

            /** Atualiza Quantidade, Valor pelos Itens da Nota **/
            this.atualizaNota(ed);
//            this.atualizaMargemFinanceiraItem(ed);

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

        	Comissao_VendedorED edCV = new Comissao_VendedorED();
            Comissao_VendedorBD bdCV = new Comissao_VendedorBD(this.sql);

        	/** Busca dados da nota fiscal **/
        	Nota_Fiscal_EletronicaED edNF = new Nota_Fiscal_EletronicaED();
        	edNF.setOid_nota_fiscal(ed.getOID_Nota_Fiscal());
        	edNF = new Nota_Fiscal_EletronicaBD(this.sql).getByRecord(edNF);

            sql = " UPDATE Servicos_Notas_Fiscais SET ";
      	  	sql +="     NR_Quantidade= " + ed.getNR_Quantidade() + ", ";
            sql +="     oid_Tipo_Servico= " + ed.getOid_Tipo_Servico() + ", ";
            sql +="     VL_Total= " + ed.getVL_Item() + ", ";
            sql +="     VL_ISSQN= " + ed.getVL_ISSQN() + ", ";
            sql +="     PE_ISSQN= " + ed.getPE_ISSQN() + ", ";
            sql +="     VL_Unitario= " + ed.getVL_Unitario();

            sql +=" WHERE OID_Servico_Nota_Fiscal = " + ed.getOID_Item_Nota_Fiscal();

            executasql.executarUpdate(sql);

            /** Atualiza Quantidade, Valor pelos Itens da Nota **/
            this.atualizaNota(ed);
//            this.atualizaMargemFinanceiraItem(ed);
        } catch(Exception exc){
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "altera()");
        }
    }

    public void deleta(Item_Nota_Fiscal_TransacoesED ed) throws Excecoes{

        String sql = null;
        try{
        	sql = " DELETE FROM Servicos_Notas_Fiscais " +
                  " WHERE 1=1 ";
            if (ed.getOID_Item_Nota_Fiscal()>0)
                 sql += " and OID_Servico_Nota_Fiscal = " + ed.getOID_Item_Nota_Fiscal() + " ";
            if (doValida(ed.getOID_Nota_Fiscal()))
            	sql += " and OID_Nota_Fiscal = '" + ed.getOID_Nota_Fiscal() + "' ";
            executasql.executarUpdate(sql);

            /** Atualiza Quantidade, Valor pelos Itens da Nota **/
            if (doValida(ed.getOID_Nota_Fiscal()))
            	this.atualizaNota(ed);
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
            //*** Validações
            if (!doValida(oid_Produto))
                throw new Excecoes("Produto não informado!");
            if (oidUnidade < 1)
                throw new Excecoes("Unidade não informada!");
            if (!doValida(oidPessoaDestino))
                throw new Excecoes("Destinatário não informado!");
            if (oidTipoProduto < 1)
                throw new Excecoes("Tipo de Produto não informado!");
            if (oidNaturezaOperacao < 1)
                throw new Excecoes("Natureza de Operação não informada!");
            if (!doValida(dmOperacao))
                throw new Excecoes("Operação não informada!");
            //*** Verifica se CFOP não possui Aprovação de Credito.
            boolean notAprovCredito = "I".equals(getTableStringValue("DM_Tipo_Imposto", "Naturezas_Operacoes", "oid_Natureza_Operacao="+oidNaturezaOperacao));

            String oidPessoaOrigem = "";
            //*** Se dmOperacao for "E"=Entrada de Produtos, a Unidade vai ser o Destino
            //    No caso da "S"=Saida a Unidade é Origem
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
                throw new Excecoes("Pessoa Origem não localizada!");

            // System.out.println("NOTA FISCAl = dmOperacao"+dmOperacao);
            // System.out.println("NOTA FISCAl = oidPessoaOrigem"+oidPessoaOrigem);
            // System.out.println("NOTA FISCAl = oidPessoaDestino"+oidPessoaDestino);
            // System.out.println("NOTA FISCAl = oidTipoProduto"+oidTipoProduto);

            //*** Estado ORIGEM
            CidadeBean edCidade_Orig = CidadeBean.getByOID(getTableIntValue("oid_Cidade",
                    														"Pessoas",
                    														"oid_Pessoa = '"+oidPessoaOrigem+"'"));
            //*** Estado DESTINO
            CidadeBean edCidade_Dest = CidadeBean.getByOID(getTableIntValue("oid_Cidade",
                    														"Pessoas",
                    														"oid_Pessoa = '"+oidPessoaDestino+"'"));
            Item_Nota_Fiscal_TransacoesED edItemNF = new Item_Nota_Fiscal_TransacoesED();
            edItemNF.setOid_natureza_operacao(oidNaturezaOperacao);
            // Se na natureza de operacao da NF estiver setado para manter para toda a nota não tenta mudar para a diferenciada.
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

            } else throw new Excecoes("Taxa não localizada!");

            //*** CALCULA VALOR do ICMS caso não tenha sido calculado pelas Taxas
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

        Item_Nota_Fiscal_TransacoesED edVolta = new Item_Nota_Fiscal_TransacoesED();

        try {
        	Iterator iterador = this.lista(ed).iterator();
        	if(iterador.hasNext())
        		edVolta = (Item_Nota_Fiscal_TransacoesED)iterador.next();

        } catch(Exception exc){
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "getByRecord()");
        }
        return edVolta;
    }

  	public ArrayList lista(Item_Nota_Fiscal_TransacoesED ed)throws Excecoes{

  	    String sql = null;
  	    ResultSet res = null;
  	    ResultSet resPedido = null;
  	    ArrayList list = new ArrayList();
  	    try {

  	        sql = " SELECT Servicos_Notas_Fiscais.* " +
                  "       ,Tipos_Servicos.*" +
  	        	  " FROM Servicos_Notas_Fiscais" +
                  "     ,Tipos_Servicos " +
  	        	  " WHERE Servicos_Notas_Fiscais.OID_Tipo_Servico = Tipos_Servicos.OID_Tipo_Servico";
  	        if(doValida(ed.getOID_Nota_Fiscal())){
  	        	sql +="   AND Servicos_Notas_Fiscais.OID_Nota_Fiscal = '"+ed.getOID_Nota_Fiscal()+"'";
  	        }
  	      if(doValida(String.valueOf(ed.getOID_Item_Nota_Fiscal()))){
	        	sql +="   AND Servicos_Notas_Fiscais.OID_Servico_Nota_Fiscal = '" + ed.getOID_Item_Nota_Fiscal() + "'";
	        }
  	        sql +=" ORDER BY Tipos_Servicos.NM_Tipo_Servico, Servicos_Notas_Fiscais.oid_Servico_Nota_Fiscal";

  	        res = this.executasql.executarConsulta(sql);
  	        while (res.next())
            {
  	            Item_Nota_Fiscal_TransacoesED edVolta = new Item_Nota_Fiscal_TransacoesED();

  	            edVolta.setOID_Nota_Fiscal(res.getString("OID_Nota_Fiscal"));
  	            edVolta.setOid_Tipo_Servico(res.getInt("oid_Tipo_Servico"));
  	            edVolta.setCD_Tipo_Servico(res.getString("cd_Tipo_Servico"));
  	            edVolta.setNM_Tipo_Servico(res.getString("nm_Tipo_Servico"));

  	            edVolta.setOID_Item_Nota_Fiscal(res.getLong("OID_Servico_Nota_Fiscal"));
  	            edVolta.setVL_Item(res.getDouble("VL_Total"));
  	            edVolta.setVL_Unitario(res.getDouble("VL_Unitario"));
  	            edVolta.setVL_ISSQN(res.getDouble("VL_issqn"));
  	            edVolta.setPE_ISSQN(res.getDouble("PE_issqn"));

  	            edVolta.setNR_Quantidade(res.getDouble("NR_Quantidade"));

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

  	/** Atualiza Quantidade, Valor pelos Itens da Nota **/
    public void atualizaNota(Item_Nota_Fiscal_TransacoesED ed) throws Excecoes {

        String sql = "";
        try {
            if (!doValida(ed.getOID_Nota_Fiscal()))
                throw new Mensagens("ID Nota Fiscal não informado!");

            double novo_valor = getTableDoubleValue("sum(VL_Total)", "Servicos_Notas_Fiscais","oid_Nota_Fiscal = "+getSQLString(ed.getOID_Nota_Fiscal()));
            //agora soma os produtos...
            novo_valor += getTableDoubleValue("sum(VL_Produto)", "Itens_Notas_Fiscais","oid_Nota_Fiscal = "+getSQLString(ed.getOID_Nota_Fiscal()));

            double vl_inss = novo_valor * 0.11;
            if(vl_inss < 29.0){
            	vl_inss = 0;
            }
            String cliente = getTableStringValue("oid_pessoa", "notas_fiscais", "oid_Nota_Fiscal = "+getSQLString(ed.getOID_Nota_Fiscal()));
            String dm_calculo_inss_cliente = getTableStringValue("dm_calcula_inss_cliente", "clientes", "oid_cliente='"+cliente+"'");
            if(doValida(dm_calculo_inss_cliente) && "N".equals(dm_calculo_inss_cliente)){
            	vl_inss = 0;
            }

            sql = " UPDATE Notas_Fiscais SET " +
                  "    oid_Nota_Fiscal = oid_Nota_Fiscal " +
                  "    ,VL_Nota_Fiscal = "+ novo_valor +
                  "    ,VL_INSS = "+ vl_inss +
                  "    ,VL_ISQN = "+ getTableDoubleValue("sum(VL_ISSQN)", "Servicos_Notas_Fiscais","oid_Nota_Fiscal = "+getSQLString(ed.getOID_Nota_Fiscal()));;
            sql +=" WHERE OID_Nota_Fiscal = "+getSQLString(ed.getOID_Nota_Fiscal());
            executasql.executarUpdate(sql);

        } catch(Exception exc){
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "atualizaNota()");
        }
    }

    //*** Recalcula preço dos itens pela Condição de Pagamento e Tabela de Preços!
    //    Obs.: Tbm existente em Item_PedidoED: recalculaPrecoItensPedido()
    public void recalculaPrecoItensNota(Nota_Fiscal_EletronicaED ed, boolean byTabela, boolean byCondicao) throws Excecoes {

        String sql = null;
        try {

            if (!doValida(ed.getOid_nota_fiscal()))
                throw new Excecoes("Nota Fiscal não informada!");
            if (ed.getOid_Condicao_Pagamento() < 1)
                throw new Mensagens("Condição de Pagamento não informada!");

            ArrayList lista = this.lista(new Item_Nota_Fiscal_TransacoesED(ed.getOid_nota_fiscal()));
            for (int i=0; i < lista.size(); i++)
            {
                Item_Nota_Fiscal_TransacoesED edItem = (Item_Nota_Fiscal_TransacoesED) lista.get(i);
                if ("C".equals(edItem.getDM_Situacao()))
                    return;

                ProdutoED edProduto = (ProdutoED) new ProdutoBD(executasql).getByRecord(new ProdutoED(edItem.getOID_Produto()));

                /** by TABELA DE PREÇOS **/
                if (byTabela)
                    edProduto.edPreco = new Pro004Bean().getPrecoByTabela((int)ed.getOid_Condicao_Pagamento(), edProduto.getOID_Produto(), ed.getOid_Tabela_Venda());
                else
                /** by CONDIÇÃO DE PAGAMENTO (mesma Tabela) **/
                if (byCondicao)
                    edProduto.edPreco = new Pro004Bean().getPrecoByOIDCondPagamento((int)ed.getOid_Condicao_Pagamento(), edItem.getOid_Preco_Produto());

                boolean existPreco = edProduto.edPreco.getOID_Preco_Produto() > 0;
                double nrQtAtendido = edItem.getNR_Quantidade();
                /** Se não existe o ITEM NA TABELA, ZERA QUANTIDADE DO MESMO **/
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

}