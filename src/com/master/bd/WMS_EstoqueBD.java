package com.master.bd;

/**
 * @author: André Valadas
 */

import java.sql.ResultSet;
import java.util.ArrayList;
import com.master.ed.Item_Nota_Fiscal_TransacoesED;
import com.master.ed.Nota_Fiscal_EletronicaED;
import com.master.ed.Movimento_Produto_ClienteED;
import com.master.ed.Nota_Fiscal_EletronicaED;
import com.master.ed.Ocorrencia_Nota_FiscalED;
import com.master.ed.Produto_ClienteED;
import com.master.ed.WMS_EstoqueED;
import com.master.iu.ProdutoBean;
import com.master.iu.wms005Bean;
import com.master.rn.Produto_ClienteRN;
import com.master.rn.WMS_EstoqueRN;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.Mensagens;
import com.master.util.Utilitaria;
import com.master.util.bd.ExecutaSQL;
import com.master.util.ed.Parametro_FixoED;
import com.master.util.Valor;
import com.master.bd.Item_Nota_Fiscal_TransacoesBD;
import com.master.rn.Ocorrencia_Nota_FiscalRN;

public class WMS_EstoqueBD{

    private ExecutaSQL executasql;
	Utilitaria util = new Utilitaria(executasql);

    public WMS_EstoqueBD(ExecutaSQL sql) {
        this.executasql = sql;
    }

    public void tranfereEstoque(WMS_EstoqueED ed) throws Excecoes {

        try {
            if (ed.getNR_Quantidade() <= 0)
                return;

            //*** Diminui da Quantidade Atual
            //OBS.: oid_Estoque refere-se ao Estoque ORIGINAL, mas somente o OID
            this.subtrai(ed.getOID_Estoque(), ed.getNR_Quantidade(), true);
            //*** Adiciona ou Soma a Tranferencia no Estoque se existir
            this.inclui(ed, true);

        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "tranfereEstoque()");
        }
    }

    public void reajusteEstoque(WMS_EstoqueED ed) throws Excecoes {

        try {
            //*** Busca Quantidade Anterior
            double nrQtdAtual = util.getTableDoubleValue("NR_Quantidade", "Estoques_Clientes", "oid_Estoque_Cliente = '"+ed.getOID_Estoque()+"'");
            double nrQtdAtendida = new wms005Bean().getQtdTotalAtendida(ed.getOID_Produto_Cliente(), null, String.valueOf(ed.getOID_Tipo_Estoque()), ed.getOID_Localizacao(), null);
            double nrQuantidade = nrQtdAtual - nrQtdAtendida;
            int oid_Tipo_Movimento = 0;
            if (nrQuantidade != ed.getNR_Quantidade()) {
                if(nrQuantidade > ed.getNR_Quantidade()) {
                    oid_Tipo_Movimento = Parametro_FixoED.getInstancia().getOid_Tipo_Movimento_Ajuste_S();
                    nrQuantidade = nrQuantidade - ed.getNR_Quantidade();
                } else {
                    oid_Tipo_Movimento = Parametro_FixoED.getInstancia().getOid_Tipo_Movimento_Ajuste_E();
                    nrQuantidade = ed.getNR_Quantidade() - nrQuantidade;
                }
                //*** Grava Registro em Movimentos
                Movimento_Produto_ClienteED movimento_Produto_ClienteED = new Movimento_Produto_ClienteED();
                movimento_Produto_ClienteED = new Movimento_Produto_ClienteBD(executasql).inclui(new Movimento_Produto_ClienteED(oid_Tipo_Movimento
		 				 																		,ed.getOID_Produto_Cliente()
																								,ed.getOID_Tipo_Estoque()
																								,ed.getOID_Localizacao(),""
																								,0,0
																								,nrQuantidade,nrQuantidade, ed.getNR_Quantidade()
																								,Data.getDataDMY(), Data.getHoraHM(), ed.getTx_Observacao()));
            }

            String oid_Estoque = ed.getOID_Produto_Cliente() + ed.getOID_Localizacao() + ed.getOID_Tipo_Estoque();
            //*** Verifica se já existe estoque, para não existir dois produtos para o mesmo estoque com OIDs diferentes
            if (haNoEstoque(oid_Estoque, ed.getOID_Tipo_Estoque()) && !oid_Estoque.equals(ed.getOID_Estoque()) &&
                (ed.getOID_Tipo_Estoque() == util.getTableIntValue("oid_Tipo_Estoque", "Estoques_Clientes", "oid_Estoque_Cliente = '"+ed.getOID_Produto_Cliente()+ed.getOID_Localizacao()+"'"))) {
                //*** Excluir OID atual
                this.deleta(ed);
                //*** Seta o OID existente de mesmo local
                ed.setOID_Estoque(ed.getOID_Produto_Cliente()+ed.getOID_Localizacao()+ed.getOID_Tipo_Estoque());
                nrQuantidade = util.getTableDoubleValue("NR_Quantidade", "Estoques_Clientes", "oid_Estoque_Cliente = '"+ed.getOID_Estoque()+"'");
                //*** Atribui quantidade
                ed.setNR_Quantidade(ed.getNR_Quantidade() + nrQuantidade);
            } else ed.setNR_Quantidade(ed.getNR_Quantidade() + nrQtdAtendida);

            //*** Altera
            this.altera(ed);
        }
        catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            		"reajusteEstoque()");
        }
    }

    public WMS_EstoqueED inclui(WMS_EstoqueED ed, boolean addMovimento) throws Excecoes {

        String sql = null;
        double qtFinal = 0 ;
        try {
            if (!util.doValida(ed.getOID_Produto_Cliente()))
                throw new Excecoes("ID Produto Cliente não informado!");
            if (ed.getOID_Localizacao() < 1)
                throw new Excecoes("ID Localização não informado!");
            if (ed.getOID_Tipo_Estoque() < 1)
                throw new Excecoes("ID Tipo de Estoque não informado!");

            ed.setOID_Estoque(ed.getOID_Produto_Cliente() + ed.getOID_Localizacao() + ed.getOID_Tipo_Estoque());
            if (!haNoEstoque(ed.getOID_Estoque(), ed.getOID_Tipo_Estoque()))
            {
                sql = " INSERT INTO Estoques_Clientes (" +
                	  "		oid_Estoque_Cliente," +
                	  "		oid_Produto_Cliente," +
                	  "		oid_Tipo_Estoque," +
                	  "		oid_Localizacao," +
                	  "		NR_Quantidade," +
                	  "		dt_stamp) " +
                	  " VALUES (" +
                	  "'" + ed.getOID_Estoque() +
                	  "','" + ed.getOID_Produto_Cliente() +
                	  "','" + ed.getOID_Tipo_Estoque() +
                	  "'," + ed.getOID_Localizacao() +
                	  "," + ed.getNR_Quantidade() +
                	  ",'"+ Data.getDataDMY()+ "')";

                executasql.executarUpdate(sql);
            } else {
                double nrQtdAtual = util.getTableDoubleValue("NR_Quantidade", "Estoques_Clientes", "oid_Estoque_Cliente = '"+ed.getOID_Estoque()+"'");
                qtFinal = (nrQtdAtual + ed.getNR_Quantidade()) ;
                sql = " UPDATE Estoques_Clientes SET ";
                sql +="      NR_Quantidade = " + (nrQtdAtual + ed.getNR_Quantidade());
                sql +="     ,oid_Localizacao = " + ed.getOID_Localizacao();
                sql +=" WHERE oid_Estoque_Cliente = '" + ed.getOID_Estoque() + "'" +
                      "   AND oid_Tipo_Estoque = "+ed.getOID_Tipo_Estoque();
                executasql.executarUpdate(sql);
            }
            //*** Grava Registro em Movimentos
            if (addMovimento)
	            new Movimento_Produto_ClienteBD(executasql).inclui(new Movimento_Produto_ClienteED(Parametro_FixoED.getInstancia().getOid_Tipo_Movimento_Ajuste_E()
																								   ,ed.getOID_Produto_Cliente()
																								   ,ed.getOID_Tipo_Estoque()
																								   ,ed.getOID_Localizacao(),""
																								   ,0,0
																								   ,ed.getNR_Quantidade(),ed.getNR_Quantidade(), qtFinal
																								   ,Data.getDataDMY(), Data.getHoraHM(), ed.getTx_Observacao()));
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            		"inclui()");
        }
        return ed;
    }

    public void altera(WMS_EstoqueED ed) throws Excecoes {

        String sql = null;
        try {
            sql = " UPDATE estoques_clientes SET ";
            sql +="      oid_Produto_Cliente = '" + ed.getOID_Produto_Cliente() + "'";
            sql +="     ,oid_Tipo_Estoque = " + ed.getOID_Tipo_Estoque();
            sql +="     ,oid_Localizacao = " + ed.getOID_Localizacao();
            sql +="     ,NR_Quantidade = " + ed.getNR_Quantidade();
            sql +=" WHERE oid_estoque_cliente = '" + ed.getOID_Estoque() + "'";
            executasql.executarUpdate(sql);

        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            		"altera()");
        }
    }

    public void deleta(WMS_EstoqueED ed) throws Excecoes {

        String sql = null;
        try {
            sql = " DELETE FROM estoques_clientes WHERE ";
            sql +=" oid_estoque_cliente = '" + ed.getOID_Estoque() + "'";
            executasql.executarUpdate(sql);

        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            		"deleta()");
        }
    }

    public void subtrai(String oid_Estoque_Cliente, double nrQuantidade, boolean addMovimento) throws Excecoes {

        String sql = null;
        try {
            //*** Busca Quantidade Anterior
            WMS_EstoqueED ed = this.getByRecord(new WMS_EstoqueED(oid_Estoque_Cliente));
            //double nrQtdAtendida = new wms005Bean().getQtdTotalAtendida(ed.getOID_Produto_Cliente(), null, String.valueOf(ed.getOID_Tipo_Estoque()), ed.getOID_Localizacao(), null);
            double nrQtdDisponivel = ed.getNR_Quantidade();

            //*** Valida Quantidade
            if (nrQtdDisponivel <= 0)
                throw new Excecoes("Produto ["+ed.getCD_Produto()+"] sem Quantidade Disponível em Estoque!");
            if (nrQtdDisponivel < nrQuantidade)
                throw new Excecoes("Produto ["+ed.getCD_Produto()+"] Há somente " + nrQtdDisponivel + " Itens Disponíveis em Estoque!");

	        sql =  " UPDATE Estoques_Clientes SET ";
	        sql += " 	NR_Quantidade = " + (ed.getNR_Quantidade() - nrQuantidade);
	        sql += " WHERE oid_Estoque_Cliente = '" +oid_Estoque_Cliente+ "'";
	        executasql.executarUpdate(sql);

            //*** Grava Registro em Movimentos
            if (addMovimento)
	            new Movimento_Produto_ClienteBD(executasql).inclui(new Movimento_Produto_ClienteED(Parametro_FixoED.getInstancia().getOid_Tipo_Movimento_Ajuste_S()
						 																		   ,ed.getOID_Produto_Cliente()
						 																		   ,ed.getOID_Tipo_Estoque()
						 																		   ,ed.getOID_Localizacao(),""
						 																		   ,0,0
						 																		   ,nrQuantidade,nrQuantidade,(ed.getNR_Quantidade() - nrQuantidade)
						 																		   ,Data.getDataDMY(), Data.getHoraHM(), ed.getTx_Observacao()));
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            		"subtrai()");
        }
    }

    public WMS_EstoqueED getByRecord(WMS_EstoqueED ed) throws Excecoes {

        String sql = null;
        ResultSet res = null;
        WMS_EstoqueED edVolta = new WMS_EstoqueED();
        try {

            sql = " SELECT Estoques_Clientes.oid_estoque_cliente" +
                  "    ,Estoques_Clientes.oid_Produto_Cliente" +
                  "    ,Estoques_Clientes.oid_localizacao" +
                  "    ,Estoques_Clientes.nr_quantidade" +
                  "    ,Tipos_Estoques.OID_Tipo_Estoque" +
                  "    ,Tipos_Estoques.CD_Tipo_Estoque" +
                  "    ,Tipos_Estoques.NM_Tipo_Estoque" +
                  "    ,Produtos.oid_produto" +
                  "    ,Produtos.CD_Produto" +
                  "    ,Produtos.NM_Produto" +
                  "    ,Produtos.oid_Pessoa as oid_Fornecedor" +
                  "    ,Pessoas.oid_pessoa" +
                  "    ,Pessoas.NR_CNPJ_CPF" +
                  "    ,Pessoas.NM_Razao_Social " +
            	  " FROM Estoques_Clientes" +
            	  "	 	,Tipos_Estoques" +
            	  "	 	,Pessoas" +
            	  "	 	,Produtos" +
                  "     ,Produtos_Clientes" +
            	  " WHERE Estoques_Clientes.oid_Tipo_Estoque = Tipos_Estoques.oid_Tipo_Estoque" +
            	  "   AND Estoques_Clientes.oid_Produto_Cliente = Produtos_Clientes.oid_Produto_Cliente " +
            	  "   AND Produtos.oid_Produto = Produtos_Clientes.oid_Produto" +
            	  "   AND Pessoas.oid_Pessoa = Produtos_Clientes.oid_Pessoa";
            if (util.doValida(ed.getOID_Estoque()))
                sql += " AND Estoques_Clientes.oid_estoque_cliente = '" + ed.getOID_Estoque() + "'";

            res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                edVolta = new WMS_EstoqueED();

                edVolta.setOID_Estoque(res.getString("oid_estoque_cliente"));
                edVolta.setOID_Produto_Cliente(res.getString("oid_Produto_Cliente"));
                edVolta.setOID_Localizacao(res.getInt("oid_localizacao"));
                edVolta.setNR_Quantidade(res.getDouble("nr_quantidade"));

                edVolta.setOID_Tipo_Estoque(res.getInt("OID_Tipo_Estoque"));
                edVolta.setCD_Tipo_Estoque(res.getString("CD_Tipo_Estoque"));
                edVolta.setNM_Tipo_Estoque(res.getString("NM_Tipo_Estoque"));

                edVolta.setOID_Produto(res.getInt("oid_produto"));
                edVolta.setCD_Produto(res.getString("CD_Produto"));
                edVolta.setNM_Produto(res.getString("NM_Produto"));

                //*** Fornecedor
                edVolta.setOID_Pessoa(res.getString("oid_Fornecedor"));
                edVolta.setNR_CNPJ_CPF(res.getString("oid_Fornecedor"));
                edVolta.setNM_Razao_Social(util.getTableStringValue("NM_Razao_Social", "Pessoas", "oid_Pessoa = "+util.getSQLString(res.getString("oid_Fornecedor"))));
                //*** Distribuidor
                edVolta.setOid_Pessoa_Distribuidor(res.getString("oid_pessoa"));
                edVolta.setNR_CNPJ_CPF_Distribuidor(res.getString("NR_CNPJ_CPF"));
                edVolta.setNM_Distribuidor(res.getString("NM_Razao_Social"));
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "getByRecord()");
        } finally {
        	util.closeResultset(res);
        }
        return edVolta;
    }

    public ArrayList lista(WMS_EstoqueED ed, String orderby) throws Excecoes {

        String sql = null;
        ResultSet res = null;
        ArrayList list = new ArrayList();
        String order_by = " ORDER BY Produtos_Clientes.oid_Pessoa, Produtos.NM_Produto";

        try {

            sql = " SELECT Estoques_Clientes.oid_Estoque_Cliente" +
                  "       ,Estoques_Clientes.oid_Localizacao" +
                  "       ,Estoques_Clientes.oid_Produto_Cliente" +
                  "       ,Estoques_Clientes.NR_Quantidade" +
                  "       ,Estoques_Clientes.oid_Tipo_Estoque" +
                  "       ,Tipos_Estoques.CD_Tipo_Estoque" +
                  "       ,Tipos_Estoques.NM_Tipo_Estoque" +
                  "       ,Produtos.oid_Produto" +
                  "       ,Produtos.CD_Produto" +
                  "       ,Produtos.NM_Produto" +
                  "       ,Produtos.oid_Pessoa as oid_Fornecedor" +
                  "       ,Produtos_Clientes.oid_Pessoa as oid_Distribuidor" +
      	  		  " FROM Estoques_Clientes" +
      	  		  "	 	,Tipos_Estoques" +
      	  		  "	 	,Produtos" +
                  "     ,Produtos_Clientes" +
                  (util.doValida(ed.getOID_Pessoa()) ? ", Fornecedores" : "")+
      	  		  " WHERE Estoques_Clientes.oid_Tipo_Estoque = Tipos_Estoques.oid_Tipo_Estoque" +
      	  		  "   AND Estoques_Clientes.oid_Produto_Cliente = Produtos_Clientes.oid_Produto_Cliente " +
      	  		  "   AND Produtos.oid_Produto = Produtos_Clientes.oid_Produto";

            if (util.doValida(ed.getOID_Produto_Cliente()))
                sql += " AND Estoques_Clientes.OID_Produto_Cliente = '" + ed.getOID_Produto_Cliente() + "'";
            if (util.doValida(ed.getOID_Pessoa()))
            {
                sql +="   AND Fornecedores.oid_Pessoa = '"+ed.getOID_Pessoa()+"'" +
                      "   AND (Produtos.oid_pessoa = Fornecedores.oid_Pessoa OR (SELECT count(1) " +
                      "                                                          FROM Fornecedores_Produtos" +
                      "                                                          WHERE Fornecedores_Produtos.oid_Pessoa = Fornecedores.oid_pessoa" +
                      "                                                            AND Fornecedores_Produtos.oid_Produto = Produtos.oid_Produto) > 0)";
            }
            if (util.doValida(ed.getOid_Pessoa_Distribuidor()))
                sql += " AND Produtos_Clientes.oid_Pessoa = '" + ed.getOid_Pessoa_Distribuidor() + "'";
            if (ed.getOID_Tipo_Estoque() > 0)
                sql += " AND Estoques_Clientes.OID_Tipo_Estoque = " + ed.getOID_Tipo_Estoque();
            if (ed.getOID_Localizacao() > 0)
                sql += " AND Estoques_Clientes.OID_Localizacao = " + ed.getOID_Localizacao();
            if (ed.getNR_Quantidade() > 0)
                sql += " AND Estoques_Clientes.NR_Quantidade = " + ed.getNR_Quantidade();

            if (orderby != null && !order_by.equals(orderby))
                sql += orderby;
            else sql += order_by;

            res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                WMS_EstoqueED edVolta = new WMS_EstoqueED();

                edVolta.setOID_Estoque(res.getString("OID_Estoque_Cliente"));
                edVolta.setOID_Localizacao(res.getInt("OID_Localizacao"));
                edVolta.setOID_Tipo_Estoque(res.getInt("OID_Tipo_Estoque"));
                edVolta.setOID_Produto_Cliente(res.getString("OID_Produto_Cliente"));
                edVolta.setNR_Quantidade(res.getDouble("NR_Quantidade"));

                edVolta.setOID_Produto(res.getInt("oid_produto"));
                edVolta.setNM_Produto(res.getString("NM_Produto"));
                edVolta.setCD_Produto(res.getString("CD_Produto"));
                edVolta.setCD_Tipo_Estoque(res.getString("CD_Tipo_Estoque"));
                edVolta.setNM_Tipo_Estoque(res.getString("NM_Tipo_Estoque"));

                //*** Fornecedor
                edVolta.setOID_Pessoa(res.getString("oid_Fornecedor"));
                edVolta.setNR_CNPJ_CPF(util.getTableStringValue("NR_CNPJ_CPF","Pessoas","oid_Pessoa='"+edVolta.getOID_Pessoa()+"'"));
                edVolta.setNM_Razao_Social(util.getTableStringValue("NM_Razao_Social","Pessoas","oid_Pessoa='"+edVolta.getOID_Pessoa()+"'"));
                //*** Distribuidor
                edVolta.setOid_Pessoa_Distribuidor(res.getString("oid_Distribuidor"));
                edVolta.setNR_CNPJ_CPF_Distribuidor(util.getTableStringValue("NR_CNPJ_CPF","Pessoas","oid_Pessoa='"+edVolta.getOid_Pessoa_Distribuidor()+"'"));
                edVolta.setNM_Distribuidor(util.getTableStringValue("NM_Razao_Social","Pessoas","oid_Pessoa='"+edVolta.getOid_Pessoa_Distribuidor()+"'"));

                list.add(edVolta);
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "lista()");
        } finally {
        	util.closeResultset(res);
        }
        return list;
    }

    public ArrayList lista_Lote(WMS_EstoqueED ed, String orderby) throws Excecoes {

        String sql = null;
        ResultSet res = null;
        ArrayList list = new ArrayList();
        String order_by = " ORDER BY Produtos_Clientes.oid_Pessoa, Produtos.NM_Produto, Itens_Notas_Fiscais.nr_lote_produto";

        try {

            sql = " SELECT sum(Itens_Notas_Fiscais.NR_Quantidade) as NR_Quantidade" +
                  "       ,sum(Itens_Notas_Fiscais.nr_qt_devolucao) as nr_qt_devolucao" +
                  "       ,Itens_Notas_Fiscais.nr_lote_produto" +
                  "       ,Produtos.oid_Produto" +
                  "       ,Produtos.CD_Produto" +
                  "       ,Produtos.CD_Fornecedor" +
                  "       ,Produtos.NM_Produto" +
                  "       ,Produtos.oid_Pessoa as oid_Fornecedor" +
                  "       ,Produtos_Clientes.oid_Pessoa as oid_Distribuidor" +
                  "       ,Produtos_Clientes.oid_Produto_Cliente" +
      	  		  " FROM Produtos" +
                  "     ,Produtos_Clientes" +
                  "     ,itens_notas_fiscais" +
                  "     ,notas_fiscais" +
                  (util.doValida(ed.getOID_Pessoa()) ? ", Fornecedores" : "")+
      	  		  " WHERE Produtos.oid_Produto = Produtos_Clientes.oid_Produto" +
      	  		  "   AND Produtos.oid_Produto = Itens_Notas_Fiscais.oid_Produto" +
      	  		  "   AND Notas_Fiscais.oid_Nota_Fiscal = Itens_Notas_Fiscais.oid_Nota_Fiscal" +
      	  		  //"   AND Itens_Notas_Fiscais.dm_devolvido = 'N'" +
      	  		  "   AND Notas_Fiscais.dm_tipo_nota_fiscal = 'E'";

            if (util.doValida(ed.getOID_Produto_Cliente()))
                sql += " AND Produtos_Clientes.OID_Produto_Cliente = '" + ed.getOID_Produto_Cliente() + "'";
            if (util.doValida(ed.getNR_Lote_Produto()))
                sql += " AND Itens_Notas_Fiscais.nr_lote_produto = '" + ed.getNR_Lote_Produto() + "'";
            if (util.doValida(ed.getOID_Pessoa()))
            {
                sql +="   AND Fornecedores.oid_Pessoa = '"+ed.getOID_Pessoa()+"'" +
                      "   AND (Produtos.oid_pessoa = Fornecedores.oid_Pessoa OR (SELECT count(1) " +
                      "                                                          FROM Fornecedores_Produtos" +
                      "                                                          WHERE Fornecedores_Produtos.oid_Pessoa = Fornecedores.oid_pessoa" +
                      "                                                            AND Fornecedores_Produtos.oid_Produto = Produtos.oid_Produto) > 0)";
            }
            if (util.doValida(ed.getOid_Pessoa_Distribuidor()))
                sql += " AND Produtos_Clientes.oid_Pessoa = '" + ed.getOid_Pessoa_Distribuidor() + "'";
//            if (ed.getOID_Tipo_Estoque() > 0)
//                sql += " AND Estoques_Clientes.OID_Tipo_Estoque = " + ed.getOID_Tipo_Estoque();
//            if (ed.getOID_Localizacao() > 0)
//                sql += " AND Estoques_Clientes.OID_Localizacao = " + ed.getOID_Localizacao();
//            if (ed.getNR_Quantidade() > 0)
//                sql += " AND Estoques_Clientes.NR_Quantidade = " + ed.getNR_Quantidade();

            sql += " GROUP BY Itens_Notas_Fiscais.nr_lote_produto" +
                  "       ,Produtos.oid_Produto" +
                  "       ,Produtos.CD_Produto" +
                  "       ,Produtos.CD_Fornecedor" +
                  "       ,Produtos.NM_Produto" +
                  "       ,Produtos.oid_Pessoa" +
                  "       ,Produtos_Clientes.oid_Pessoa" +
                  "       ,Produtos_Clientes.oid_Produto_Cliente";

            if (orderby != null && !order_by.equals(orderby))
                sql += orderby;
            else sql += order_by;
// System.out.println("LIS LOTE: "+sql);
            res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                WMS_EstoqueED edVolta = new WMS_EstoqueED();

                edVolta.setOID_Produto_Cliente(res.getString("OID_Produto_Cliente"));

                edVolta.setNR_Quantidade(res.getDouble("NR_Quantidade"));
                edVolta.setNR_Quantidade_Devolucao(res.getDouble("nr_qt_devolucao"));
                edVolta.setNR_Lote_Produto(res.getString("nr_lote_produto"));

                edVolta.setOID_Produto(res.getInt("oid_produto"));
                edVolta.setNM_Produto(res.getString("NM_Produto"));
                edVolta.setCD_Produto(res.getString("CD_Fornecedor"));

                //*** Fornecedor
                edVolta.setOID_Pessoa(res.getString("oid_Fornecedor"));
                edVolta.setNR_CNPJ_CPF(util.getTableStringValue("NR_CNPJ_CPF","Pessoas","oid_Pessoa='"+edVolta.getOID_Pessoa()+"'"));
                edVolta.setNM_Razao_Social(util.getTableStringValue("NM_Razao_Social","Pessoas","oid_Pessoa='"+edVolta.getOID_Pessoa()+"'"));
                //*** Distribuidor
                edVolta.setOid_Pessoa_Distribuidor(res.getString("oid_Distribuidor"));
                edVolta.setNR_CNPJ_CPF_Distribuidor(util.getTableStringValue("NR_CNPJ_CPF","Pessoas","oid_Pessoa='"+edVolta.getOid_Pessoa_Distribuidor()+"'"));
                edVolta.setNM_Distribuidor(util.getTableStringValue("NM_Razao_Social","Pessoas","oid_Pessoa='"+edVolta.getOid_Pessoa_Distribuidor()+"'"));

                String sqlAux = "";
                sqlAux = " SELECT sum(Itens_Notas_Fiscais.NR_Quantidade) as NR_Quantidade" +
                		 "       ,Itens_Notas_Fiscais.nr_lote_produto" +
                		 " FROM notas_fiscais, itens_notas_fiscais" +
                		 " WHERE itens_notas_fiscais.oid_nota_fiscal = notas_fiscais.oid_nota_fiscal" +
                		 " AND   Notas_Fiscais.dm_tipo_nota_fiscal = 'S'" +
                		 " AND   notas_fiscais.dm_finalizado = 'F'" +
                		 " AND   notas_fiscais.dm_situacao != 'C'" +
                		 " AND   Itens_Notas_Fiscais.nr_lote_produto = '" + res.getString("nr_lote_produto") + "'" +
                		 " AND   itens_notas_fiscais.oid_produto = " + res.getInt("oid_produto");
                sqlAux += " group by Itens_Notas_Fiscais.nr_lote_produto ";

                ResultSet rs = null;
                rs = this.executasql.executarConsulta(sqlAux);
                while(rs.next())
                	edVolta.setNR_Quantidade_Pendente(rs.getDouble(1));

                list.add(edVolta);
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
                    "lista()");
        } finally {
        	util.closeResultset(res);
        }
        return list;
    }

    public boolean haNoEstoque(String oid_estoque_cliente, int oid_tipo_estoque) throws Excecoes {

        try {
            if (!util.doValida(oid_estoque_cliente))
                throw new Excecoes("ID Estoque Cliente não informado!");
            if (oid_tipo_estoque < 1)
                throw new Excecoes("ID Tipo Estoque não informado!");

            return util.doExiste("Estoques_Clientes",
                    		"oid_estoque_cliente = '"+oid_estoque_cliente+"'"+
                    		" AND oid_Tipo_Estoque = " +oid_tipo_estoque);
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            	"haNoEstoque()");
        }
    }
    public double getQtdTotalAtendida(String where) throws Excecoes {

    	double NR_QT_Atendido = 0;
    	String sql = null;
    	ResultSet res = null;

        try {
            if (!util.doValida(where))
                throw new Excecoes("Clausula WHERE não informada !");

            sql = "select DISTINCT itens_notas_fiscais.oid_item_nota_fiscal, itens_notas_fiscais.NR_QT_Atendido " +
            		" from Itens_Notas_Fiscais, Produtos_Clientes, Estoques_Clientes, Notas_Fiscais " +
            		" WHERE " + where +
					" ORDER BY itens_notas_fiscais.oid_item_nota_fiscal ";

            res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
            	NR_QT_Atendido = NR_QT_Atendido + res.getDouble("NR_QT_Atendido");
            }

            return NR_QT_Atendido;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            	"getQtdTotalAtendida(String where)");
        }
    }

    public boolean haNoEstoque(String oid_estoque_cliente) throws Excecoes {

        try {
            if (!util.doValida(oid_estoque_cliente))
                throw new Excecoes("ID Estoque Cliente não informado!");

            return util.doExiste("Estoques_Clientes", "oid_estoque_cliente = '" +oid_estoque_cliente+ "'");
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            	"haNoEstoque()");
        }
    }

    //*** SAIDA Estoque Nota Fiscal
    public boolean saidaEstoqueByNota(Nota_Fiscal_EletronicaED ed, String pOrigem)throws Excecoes {
        String sql = null;
        ResultSet res = null;
        ResultSet resLocal = null;
        String oid_Produto_Cliente = "";
        String oid_Estoque_Cliente = "";
        String oid_Localizacao = "";
        String cd_Produto = "";
        double saldoSTK = 0;
        boolean deuOcorrencia = false;

        try {

            if (!util.doValida(ed.getOid_nota_fiscal()))
                throw new Excecoes("ID Nota Fiscal nao informado!");
            if (ed.getOID_Unidade() < 1)
                throw new Excecoes("ID Unidade nao informado!");

            sql = "SELECT "+
            	  "Itens_Notas_Fiscais.oid_Produto, " +
            	  "Itens_Notas_Fiscais.oid_Produto_Cliente, " +
                  "Itens_Notas_Fiscais.oid_Localizacao, " +
                  "Produtos.cd_Produto, " +
                  "sum(Itens_Notas_Fiscais.NR_Peso_Real) as NR_Peso_Real, " +
                  "sum(Itens_Notas_Fiscais.NR_QT_Atendido) as NR_QT_Atendido " +
                  "FROM " +
                  "Itens_Notas_Fiscais, " +
                  "Notas_Fiscais, " +
                  "Produtos " +
                  "WHERE " +
                  "Itens_Notas_Fiscais.oid_Produto = Produtos.oid_Produto and " +
                  "Notas_Fiscais.oid_Unidade = "+ed.getOID_Unidade() + " and " +
                  "Itens_Notas_Fiscais.oid_Nota_Fiscal = Notas_Fiscais.OID_Nota_Fiscal and " +
                  "Itens_Notas_Fiscais.oid_Nota_Fiscal = '"+ed.getOid_nota_fiscal()+"' " +
                  "GROUP BY "+
                  "Itens_Notas_Fiscais.oid_Produto, "+
                  "Itens_Notas_Fiscais.oid_Produto_Cliente, "+
                  "Itens_Notas_Fiscais.oid_Localizacao," +
                  "Produtos.cd_Produto ";

            res = this.executasql.executarConsulta(sql);
            while (res.next())
            {

                //*** Pega ID do Estoque Cliente
            	oid_Produto_Cliente = res.getString("oid_Produto_Cliente");
// System.out.println("oid_Produto_Cliente = "+oid_Produto_Cliente);
            	cd_Produto = res.getString("cd_Produto");
                double nrQuantidade = 0;
                double nrQuantidadeAtendida = 0;
                double nrQuantidadeSaldo = 0;
                //*** Se Item é de Pesagem
                if (new ProdutoBean().isPesagemByProduto(res.getString("oid_Produto")))
                    nrQuantidade = res.getDouble("NR_Peso_Real");
                else nrQuantidade = res.getDouble("NR_QT_Atendido");

                oid_Localizacao = String.valueOf(res.getInt("oid_Localizacao"));
// System.out.println("oid_Localizacao = "+oid_Localizacao);
                sql = " select Estoques_Clientes.oid_Estoque_Cliente, Estoques_Clientes.nr_quantidade " +
                	  " from Estoques_Clientes, Produtos_Clientes " +
                      " WHERE Estoques_Clientes.oid_Produto_Cliente = Produtos_Clientes.oid_Produto_Cliente "+
                      " AND Estoques_Clientes.oid_Produto_Cliente = '"+oid_Produto_Cliente+"'" +
                      " AND Estoques_Clientes.oid_Localizacao = "+res.getInt("oid_Localizacao")+
                      " AND Estoques_Clientes.oid_Tipo_Estoque = "+ed.edModelo.getOid_Tipo_Estoque()+
					  " AND Estoques_Clientes.nr_quantidade > 0 ";
                resLocal = this.executasql.executarConsulta(sql);
                while (resLocal.next())
                {
                	oid_Estoque_Cliente = resLocal.getString("oid_Estoque_Cliente");
                	nrQuantidadeSaldo = nrQuantidade - resLocal.getDouble("nr_quantidade");
                	if (nrQuantidadeSaldo <= 0){
                		nrQuantidadeAtendida = nrQuantidade;
                	}else{
                    	nrQuantidadeAtendida = resLocal.getDouble("nr_quantidade");
                	}
                	saldoSTK =  resLocal.getDouble("nr_quantidade") - nrQuantidade;
                }
                if (util.doValida(oid_Estoque_Cliente) && nrQuantidadeAtendida > 0){

                	nrQuantidade = nrQuantidade - nrQuantidadeAtendida;
                	this.subtrai(oid_Estoque_Cliente, nrQuantidadeAtendida, false);

                    //*** Grava Registro em Movimentos
                    new Movimento_Produto_ClienteBD(executasql).inclui(new Movimento_Produto_ClienteED(ed.edModelo.getOid_Tipo_Movimento_Produto()
                            																		  ,oid_Produto_Cliente
  																		                              ,ed.edModelo.getOid_Tipo_Estoque()
																		                              ,res.getInt("oid_Localizacao")
																		                              ,ed.getOid_nota_fiscal()
																		                              ,0,0
																		                              ,nrQuantidadeAtendida, nrQuantidadeAtendida,saldoSTK
																		                              ,Data.getDataDMY(), Data.getHoraHM(), ed.getTx_Observacao()));
                }

                if (nrQuantidade > 0){

                  oid_Estoque_Cliente = null;
                  nrQuantidadeAtendida = 0;
                  oid_Localizacao = oid_Localizacao + "," + util.getTableStringValue("oid_Localizacao", "Produtos_Clientes", "oid_Produto_Cliente = '"+oid_Produto_Cliente+"'");
                  sql = " select Estoques_Clientes.oid_Estoque_Cliente, Estoques_Clientes.nr_quantidade " +
	              	    " from Estoques_Clientes, Produtos_Clientes " +
	                    " WHERE Estoques_Clientes.oid_Produto_Cliente = Produtos_Clientes.oid_Produto_Cliente "+
	                    " AND Estoques_Clientes.oid_Produto_Cliente = '"+oid_Produto_Cliente+"'" +
	                    " AND Estoques_Clientes.oid_Localizacao = "+util.getTableIntValue("oid_Localizacao", "Produtos_Clientes", "oid_Produto_Cliente = '"+oid_Produto_Cliente+"'")+
	                    " AND Estoques_Clientes.oid_Tipo_Estoque = "+ed.edModelo.getOid_Tipo_Estoque()+
						" AND Estoques_Clientes.nr_quantidade > 0 ";
	              resLocal = this.executasql.executarConsulta(sql);
	              while (resLocal.next())
	              {

	            	oid_Estoque_Cliente = resLocal.getString("oid_Estoque_Cliente");
	              	nrQuantidadeSaldo = nrQuantidade - resLocal.getDouble("nr_quantidade");
	              	if (nrQuantidadeSaldo <= 0){
	              		nrQuantidadeAtendida = nrQuantidade;
	              	}else{
	              		nrQuantidadeAtendida = resLocal.getDouble("nr_quantidade");
	              	}
	              	saldoSTK =  resLocal.getDouble("nr_quantidade") - nrQuantidade;
	              }

	              if (util.doValida(oid_Estoque_Cliente) && nrQuantidadeAtendida > 0){

	              	nrQuantidade = nrQuantidade - nrQuantidadeAtendida;
	              	this.subtrai(oid_Estoque_Cliente, nrQuantidadeAtendida, false);

	                //*** Grava Registro em Movimentos
	                new Movimento_Produto_ClienteBD(executasql).inclui(new Movimento_Produto_ClienteED(ed.edModelo.getOid_Tipo_Movimento_Produto()
	                                                                                                    ,oid_Produto_Cliente
	                                                                                                    ,ed.edModelo.getOid_Tipo_Estoque()
	                                                                                                    ,util.getTableIntValue("oid_Localizacao", "Produtos_Clientes", "oid_Produto_Cliente = '"+oid_Produto_Cliente+"'")
	                                                                                                    ,ed.getOid_nota_fiscal()
	                                                                                                    ,0,0
	                                                                                                    ,nrQuantidadeAtendida,nrQuantidadeAtendida,saldoSTK
	                                                                                                    ,Data.getDataDMY(), Data.getHoraHM(), ed.getTx_Observacao()));
	              }
                }
                if (nrQuantidade > 0){

                  oid_Estoque_Cliente = null;
                  nrQuantidadeAtendida = 0;

                  sql = " select Estoques_Clientes.oid_Estoque_Cliente, " +
                        " Estoques_Clientes.nr_quantidade, " +
                        " Estoques_Clientes.oid_Localizacao " +
                        " from Estoques_Clientes, Produtos_Clientes " +
                        " WHERE Estoques_Clientes.oid_Produto_Cliente = Produtos_Clientes.oid_Produto_Cliente "+
                        " AND Estoques_Clientes.oid_Produto_Cliente = '"+oid_Produto_Cliente+"'" +
                        " AND Estoques_Clientes.oid_Localizacao not in ("+oid_Localizacao+")"+
                        " AND Estoques_Clientes.oid_Tipo_Estoque = "+ed.edModelo.getOid_Tipo_Estoque()+
                        " AND Estoques_Clientes.nr_quantidade > 0 ";
 System.out.println("sql = "+sql);
                  resLocal = this.executasql.executarConsulta(sql);
                  while (resLocal.next())
                  {
                    oid_Estoque_Cliente = resLocal.getString("oid_Estoque_Cliente");
                    nrQuantidadeSaldo = nrQuantidade - resLocal.getDouble("nr_quantidade");
                    if (nrQuantidadeSaldo <= 0){
                        nrQuantidadeAtendida = nrQuantidade;
                    }else{
                    	nrQuantidadeAtendida = resLocal.getDouble("nr_quantidade");
                    }
                    saldoSTK =  resLocal.getDouble("nr_quantidade") - nrQuantidade;
                    if (util.doValida(oid_Estoque_Cliente) && nrQuantidadeAtendida > 0){

                      nrQuantidade = nrQuantidade - nrQuantidadeAtendida;
                      this.subtrai(oid_Estoque_Cliente, nrQuantidadeAtendida, false);

                      //*** Grava Registro em Movimentos
                      new Movimento_Produto_ClienteBD(executasql).inclui(new Movimento_Produto_ClienteED(ed.edModelo.getOid_Tipo_Movimento_Produto()
                                                                                                          ,oid_Produto_Cliente
                                                                                                          ,ed.edModelo.getOid_Tipo_Estoque()
                                                                                                          ,resLocal.getInt("oid_Localizacao")
                                                                                                          ,ed.getOid_nota_fiscal()
                                                                                                          ,0,0
                                                                                                          ,nrQuantidadeAtendida,nrQuantidadeAtendida,saldoSTK
                                                                                                          ,Data.getDataDMY(), Data.getHoraHM(), ed.getTx_Observacao()));
                    }

                    if (nrQuantidade <= 0){
                        break;
                    }
                  }
                }
                // Critica de estoque //
                //tem que abrir uma nova conexão com o banco, pois está dando exceção e no rn está dando
                //break antes do final de transação, ficando a coneção em aberto no RN
                if (!util.doValida(oid_Estoque_Cliente)) {
	                	Ocorrencia_Nota_FiscalED ocoNF = new Ocorrencia_Nota_FiscalED();
	                	ocoNF.setOID_Nota_Fiscal(ed.getOid_nota_fiscal());
	                	ocoNF.setTX_Observacao("Estoque do Cliente não encontrado! Produto = " + cd_Produto);
	                	ocoNF.setDM_Pendencia("S");
	                	ocoNF.setOID_Tipo_Ocorrencia(116);
	                	ocoNF.setDT_Ocorrencia_Nota_Fiscal(Data.getDataDMY());
	                	ocoNF.setHR_Ocorrencia_Nota_Fiscal(Data.getHoraHM());
	                	ocoNF.setDM_Origem_Ocorrencia("I");
	                	Ocorrencia_Nota_FiscalBD ocorrencia_Nota_FiscalBD = new Ocorrencia_Nota_FiscalBD(this.executasql);
	                    //chama o inclui passando a estrutura de dados
	                    //como parametro
	                    ocorrencia_Nota_FiscalBD.inclui(ocoNF);
	                	deuOcorrencia = true;
                }
                if (nrQuantidade > 0){
	                	Ocorrencia_Nota_FiscalED ocoNF = new Ocorrencia_Nota_FiscalED();
		            	ocoNF.setOID_Nota_Fiscal(ed.getOid_nota_fiscal());
		            	ocoNF.setTX_Observacao("Produto sem estoque disponível!  Produto = " + cd_Produto);
		            	ocoNF.setDM_Pendencia("S");
		            	ocoNF.setOID_Tipo_Ocorrencia(116);
		            	ocoNF.setDT_Ocorrencia_Nota_Fiscal(Data.getDataDMY());
		            	ocoNF.setHR_Ocorrencia_Nota_Fiscal(Data.getHoraHM());
		            	ocoNF.setDM_Origem_Ocorrencia("I");
	                	Ocorrencia_Nota_FiscalBD ocorrencia_Nota_FiscalBD = new Ocorrencia_Nota_FiscalBD(this.executasql);
	                    //chama o inclui passando a estrutura de dados
	                    //como parametro
	                    ocorrencia_Nota_FiscalBD.inclui(ocoNF);
		            	deuOcorrencia = true;
                }
            }
            return !deuOcorrencia;

        } catch(Exception exc){
        	exc.printStackTrace();
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "saidaEstoqueByNota()");
        } finally {

            util.closeResultset(res);
        }
    }

    //*** ENTRADA Estoque Nota Fiscal
    public void entradaEstoqueByNota(Item_Nota_Fiscal_TransacoesED ed) throws Excecoes {

        String sql = null;
        ResultSet res = null;
        String oid_Pessoa = "";
        String oid_Produto_Cliente = "";
        int oid_Localizacao_Produto = 0;
        double qtFinal = 0 ;

        //*** Validações
        if (ed.getOID_Unidade() < 1)
            throw new Mensagens("Unidade não informada para atualizar Estoque!");
        if (!util.doValida(ed.getOID_Nota_Fiscal()))
            throw new Mensagens("Nota Fiscal não informada para atualizar Estoque!");
        if (ed.getOid_Tipo_Estoque() < 1)
            throw new Mensagens("Tipo de Estoque não informada para atualizar Estoque!");

        try {
            //*** Busca oid_Pessoa
            //oid_Pessoa = getTableStringValue("oid_Pessoa", "Unidades", "oid_Unidade = "+ed.getOID_Unidade());
        	oid_Pessoa = ed.getOID_Pessoa();
            /** Quando é devolução de nf de venda (retorno) a nota aqui (ed.getOID_Nota_Fiscal()) é a de
             * saída original que está sendo devolvida na tela pedI015v...
             ***/
            sql = " SELECT Itens_Notas_Fiscais.*" +
                  "        ,Produtos.CD_Fornecedor " +
                  "        ,Notas_Fiscais.dm_tipo_nota_fiscal" +
                  "        ,Notas_Fiscais.oid_Nota_Fiscal_Devolucao " +
                  " FROM Itens_Notas_Fiscais, Notas_Fiscais, Produtos" +
                  " WHERE Itens_Notas_Fiscais.OID_Produto = Produtos.oid_Produto " +
                  "   AND Itens_Notas_Fiscais.OID_Nota_Fiscal = Notas_Fiscais.OID_Nota_Fiscal " +
                  "   AND Itens_Notas_Fiscais.OID_Nota_Fiscal = '"+ed.getOID_Nota_Fiscal()+"'";

            //*** Busca ID para localização para os produtos q não possuam Localização referencia do Estoque
            ed.setOid_Localizacao(util.getValueDef(ed.getOid_Localizacao(), util.getTableIntValue("oid_Localizacao", "Localizacoes", "CD_Localizacao = '"+ed.getCD_Local_Descarga()+"'")));

            res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                /** Busca produto/cliente **/
                Produto_ClienteED edProdCliVolta = new Produto_ClienteED();
                edProdCliVolta.setOID_Produto(new Integer(res.getString("OID_Produto")).intValue());
                edProdCliVolta.setOID_Pessoa(oid_Pessoa);

                edProdCliVolta = new Produto_ClienteBD(this.executasql).getByRecord(edProdCliVolta);
                oid_Produto_Cliente = edProdCliVolta.getOID_Produto_Cliente();
                boolean isPesagem = new ProdutoBean().isPesagemByProduto(res.getString("oid_Produto"));

                Produto_ClienteED edProdCli = new Produto_ClienteED();
                //*** Se não encontrou
                if (!util.doValida(oid_Produto_Cliente))
                {
                    edProdCli.setOID_Pessoa(oid_Pessoa);
                    edProdCli.setOID_Embalagem(Parametro_FixoED.getInstancia().getOID_Embalagem());
                    edProdCli.setOid_Tipo_Pallet(Parametro_FixoED.getInstancia().getOID_Tipo_Pallet());
                    edProdCli.setDM_Rotatividade("1");
                    edProdCli.setDM_Serie("N");
                    edProdCli.setDM_Situacao("B");
                    edProdCli.setOID_Produto(res.getInt("OID_Produto"));
                    edProdCli.setNR_Peso(0);
                    edProdCli.setNr_Altura(0);
                    edProdCli.setVL_Preco_Custo(res.getDouble("VL_Custo"));
                    edProdCli.setNM_Referencia(res.getString("CD_Fornecedor"));
                    edProdCli.setVL_Markup(0);
                    edProdCli.setVL_Produto(0);
                    edProdCli.setNR_QT_Minimo(1);
                    edProdCli.setNr_Largura(0);
                    edProdCli.setNr_Profundidade(0);
                    edProdCli.setNr_Empilhagem(0);
                    edProdCli.setOid_Localizacao(1);
                    edProdCli.setOID_Tipo_Estoque(1);
                    edProdCli= new Produto_ClienteRN().inclui(edProdCli);
                    oid_Produto_Cliente = edProdCli.getOID_Produto_Cliente();

                } else {
                    /** ATUALIZA VALOR ULTIMA COMPRA E PRECO CUSTO **/
                    if (res.getDouble("VL_Custo") > 0)
                    {
                        if (isPesagem)
                            edProdCli.setNR_QT_Atual(res.getDouble("NR_Peso_Real"));
                        else edProdCli.setNR_QT_Atual(res.getDouble("NR_QT_Atendido"));
                        edProdCli.setOID_Produto_Cliente(oid_Produto_Cliente);

                        /** Preço da última Compra e custo somente para nota de entrada normal - Compra **/
                        if ("E".equals(res.getString("dm_tipo_nota_fiscal")) ) {
                        	edProdCli.setVL_Produto(Valor.round( res.getDouble("VL_Item") / res.getDouble("NR_QT_Atendido"),3));
                        	edProdCli.setVL_Preco_Custo(res.getDouble("VL_Custo"));//Custo Médio
                        } else {
                        	edProdCli.setVL_Produto(0);
                        	edProdCli.setVL_Preco_Custo(edProdCliVolta.getVL_Preco_Custo());//Custo Médio
                        }
                        new Produto_ClienteBD(executasql).atualizaCustoBySaldoFinanceiro(edProdCli);
                    }
                }

                //*** Adiciona em ESTOQUE
                WMS_EstoqueED edEstoque = new WMS_EstoqueED();
                edEstoque.setOID_Produto_Cliente(oid_Produto_Cliente);
                oid_Localizacao_Produto = util.getTableIntValue("oid_Localizacao", "Produtos_Clientes", "oid_Produto_Cliente = '"+oid_Produto_Cliente+"'");
                //*** Busca Localização do Item da Nota, caso não encontre busca localização do Produto
                edEstoque.setOID_Localizacao(util.getValueDef(res.getInt("oid_Localizacao"), oid_Localizacao_Produto));
                //*** Caso ainda não encontre busca a localização de Descarga informada no Armazem
                if (edEstoque.getOID_Localizacao() < 1)
                {
                    edEstoque.setOID_Localizacao(ed.getOid_Localizacao());
                    if (edEstoque.getOID_Localizacao() < 1)
                        throw new Mensagens("Localização não encontrada para Atualização do Estoque!");
                }

                //*** Verifica Deposito
                if (ed.getOID_Deposito() < 1)
                {
                    ed.setOID_Deposito(util.getTableIntValue("oid_Deposito", "Localizacoes", "oid_Localizacao = '"+edEstoque.getOID_Localizacao()+"'"));
                    if (ed.getOID_Deposito() < 1)
                        throw new Mensagens("Depósito não informado para atualizar Estoque!");
                }

                //*** Se Item é de Pesagem
                if (isPesagem)
                    edEstoque.setNR_Quantidade(res.getDouble("NR_Peso_Real"));
                else edEstoque.setNR_Quantidade(res.getDouble("NR_QT_Atendido"));
                edEstoque.setOID_Tipo_Estoque(ed.getOid_Tipo_Estoque());
                // Busca o saldo atual
                edEstoque.setOID_Estoque(edEstoque.getOID_Produto_Cliente() + edEstoque.getOID_Localizacao() + edEstoque.getOID_Tipo_Estoque());
                WMS_EstoqueED edEstoqueAtual = new WMS_EstoqueED();
                edEstoqueAtual = new WMS_EstoqueBD(executasql).getByRecord(edEstoque);
                qtFinal = edEstoqueAtual.getNR_Quantidade() + edEstoque.getNR_Quantidade();
                new WMS_EstoqueBD(executasql).inclui(edEstoque, false);

                if (ed.getOid_Tipo_Movimento_Produto() < 1)
                {
                    Nota_Fiscal_EletronicaED edNota = new Nota_Fiscal_EletronicaBD(executasql).getByRecord(new Nota_Fiscal_EletronicaED(ed.getOID_Nota_Fiscal()));
                    ed.setOid_Tipo_Movimento_Produto(edNota.edModelo.getOid_Tipo_Movimento_Produto());
                }
                //*** Grava Registro em Movimentos
                new Movimento_Produto_ClienteBD(executasql).inclui(new Movimento_Produto_ClienteED(ed.getOid_Tipo_Movimento_Produto()
                                                                                                  ,oid_Produto_Cliente
                                                                                                  ,edEstoque.getOID_Tipo_Estoque()
                                                                                                  ,edEstoque.getOID_Localizacao()
                                                                                                  ,ed.getOID_Nota_Fiscal()
                                                                                                  ,0,0
                                                                                                  ,edEstoque.getNR_Quantidade(),edEstoque.getNR_Quantidade(),qtFinal
                                                                                                  ,Data.getDataDMY(), Data.getHoraHM(), ed.getTx_Observacao()));

                //*** Verifica se há QUANTIDADE DEVOLVIDA
                if (res.getDouble("NR_QT_Devolucao") > 0 && !util.doValida(res.getString("oid_Nota_Fiscal_Devolucao")))
                {
                    edEstoque.setNR_Quantidade(res.getDouble("NR_QT_Devolucao"));
                    edEstoque.setOID_Tipo_Estoque(Parametro_FixoED.getInstancia().getOID_Tipo_Estoque_Devolucao());
                    edEstoque.setOID_Localizacao(Parametro_FixoED.getInstancia().getOID_Localizacao_Devolucao());
                    new WMS_EstoqueRN().inclui(edEstoque, false);

                    //*** Grava Registro em Movimentos
                    new Movimento_Produto_ClienteBD(executasql).inclui(new Movimento_Produto_ClienteED(ed.getOid_Tipo_Movimento_Produto()
																									  ,oid_Produto_Cliente
																									  ,edEstoque.getOID_Tipo_Estoque()
																								  	  ,edEstoque.getOID_Localizacao()
																									  ,ed.getOID_Nota_Fiscal()
																									  ,0,0
																									  ,edEstoque.getNR_Quantidade(),edEstoque.getNR_Quantidade(),0
																									  ,Data.getDataDMY(), Data.getHoraHM(), ed.getTx_Observacao()));
                }
                //*** Atualiza Situação no Item da Nota Fiscal
                sql = " UPDATE Itens_Notas_Fiscais SET DM_Situacao = 'A' "+
                      " WHERE OID_Item_Nota_Fiscal = '" + res.getString("OID_Item_Nota_Fiscal") + "'";
                executasql.executarUpdate(sql);

                //*** Atualiza Nota Fiscal
                sql = " UPDATE Notas_Fiscais SET " +
                      "      oid_Deposito = "+ed.getOID_Deposito() +
                      "     ,oid_Unidade = "+ed.getOID_Unidade() +
                      "     ,DM_Estoque = 'S'" +
                      " WHERE oid_Nota_Fiscal = '" + ed.getOID_Nota_Fiscal() + "'";
                executasql.executarUpdate(sql);
            }
        } catch(Exception exc){
        	exc.printStackTrace();
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "entradaEstoqueByNota()");
        } finally {
            util.closeResultset(res);
        }
    }

    /** Método utilitário para calculo do custo dos itens comprados
     *  De uso esporádico.
     **/
    public void atualizaPrecoUltimaCompra() throws Excecoes {

    	String sql = null;
        ResultSet res = null;
        ResultSet resI = null;

        try {
        	/** Recalcula o custo dos itens das notas de entrada **/
        	sql = " select " +
        		  " oid_nota_fiscal " +
        		  " ,Vl_nota_fiscal " +
        		  " ,Vl_total_frete " +
        		  " ,Vl_total_seguro " +
        		  " ,Vl_total_despesas " +
        		  " ,VL_Adicional " +
		  		  " from " +
		  		  " notas_fiscais " +
		  		  " where " +
		  		  "  notas_fiscais.dm_tipo_nota_fiscal = 'E' " +
		  		  " and notas_fiscais.dm_situacao = 'F' " +
		  		  " and oid_nota_fiscal_original = 'null' " +
		  		  " order by dt_entrada, hr_entrada, nr_nota_fiscal" ;
            res = this.executasql.executarConsulta(sql);
            while (res.next())  {
            	Nota_Fiscal_EletronicaED nf = new Nota_Fiscal_EletronicaED();
            	nf.setVl_nota_fiscal(res.getDouble("Vl_nota_fiscal"));
            	nf.setOid_nota_fiscal(res.getString("oid_nota_fiscal"));
            	nf.setVl_total_frete(res.getDouble("Vl_total_frete"));
            	nf.setVl_total_seguro(res.getDouble("Vl_total_seguro"));
            	nf.setVl_total_despesas(res.getDouble("Vl_total_despesas"));
            	nf.setVL_Adicional(res.getDouble("VL_Adicional"));
            	new Item_Nota_Fiscal_TransacoesBD(this.executasql).calcularCustoDaCompra(nf);
            }
            /** Atualiza o custo pela última nota de entrada do propduto **/
        	sql = " select oid_produto_cliente, oid_produto from produtos_clientes order by oid_produto ";
            res = this.executasql.executarConsulta(sql);
            while (res.next())  {
            	sql = " select " +
            	      " itens_notas_fiscais.vl_item / itens_notas_fiscais.nr_qt_atendido as vl_produto, " +
            	      " vl_custo " +
            		  " from " +
            		  " itens_notas_fiscais ," +
            		  " notas_fiscais " +
            		  " where " +
            		  " itens_notas_fiscais.oid_nota_fiscal = notas_fiscais.oid_nota_fiscal " +
            		  " and notas_fiscais.dm_tipo_nota_fiscal = 'E' " +
            		  " and notas_fiscais.dm_situacao = 'F' " +
            		  " and oid_nota_fiscal_original = 'null' " +
            		  " and itens_notas_fiscais.oid_produto = " + res.getString("oid_produto")  +
            		  " order by dt_entrada, hr_entrada, nr_nota_fiscal" ;
                resI = this.executasql.executarConsulta(sql);
                while (resI.next()) {
                	if (resI.isLast()) {
	                    sql = " update produtos_clientes set " +
	                          " vl_produto = " + Valor.round(resI.getDouble("vl_produto"), 3) +
	                          " ,vl_preco_custo = " + Valor.round(resI.getDouble("vl_custo"), 5) +
	                          " where oid_produto_cliente = '" + res.getString("oid_produto_cliente") + "'";
	                    executasql.executarUpdate(sql);
                	}
            	}
            }
            /** Rotina utilizada uma única vez para atualizar o custo dos produtos vendidos para tras
            sql = " select " +
            	  " itens_notas_fiscais.oid_item_nota_fiscal, " +
            	  " vl_preco_custo " +
            	  " from " +
            	  " notas_fiscais, itens_notas_fiscais, produtos_clientes " +
            	  " where " +
            	  " notas_fiscais.oid_nota_fiscal = itens_notas_fiscais.oid_nota_fiscal " +
            	  "	and	produtos_clientes.oid_pessoa = '06236704000144' " +
            	  " and produtos_clientes.oid_produto = itens_notas_fiscais.oid_produto " +
            	  " and DM_TIPO_NOTA_FISCAL ='S' " ;
		    resI = this.executasql.executarConsulta(sql);
		    while (resI.next()) {
		    	sql = " update itens_notas_fiscais set " +
                " vl_custo = " + Valor.round(resI.getDouble("vl_preco_custo"), 5) +
                " where oid_item_nota_fiscal = " + resI.getInt("oid_item_nota_fiscal");
		    	executasql.executarUpdate(sql);
		    }
            **/
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(),
            	"atualizaPrecoUltimaCompra()");
        }
    }

}