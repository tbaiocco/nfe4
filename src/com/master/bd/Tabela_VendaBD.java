package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import com.master.ed.Preco_ProdutoED;
import com.master.ed.ProdutoED;
import com.master.ed.RelatorioED;
import com.master.ed.Tabela_VendaED;
import com.master.ed.Tipo_Tabela_VendaED;
import com.master.iu.Pro004Bean;
import com.master.rl.JasperRL;
import com.master.root.FormataDataBean;
import com.master.root.UnidadeBean;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.Mensagens;
import com.master.util.bd.ExecutaSQL;

/**
 * @author André Valadas
 * @serial Tabelas de Venda
 * @serialData 11/04/2005
 */
public class Tabela_VendaBD extends BancoUtil {

    private ExecutaSQL executasql;

    public Tabela_VendaBD(ExecutaSQL sql) {
        super(sql);
        this.executasql = sql;
    }

    public Tabela_VendaED inclui(Tabela_VendaED ed) throws Excecoes {

        String sql = null;
        try {
            //*** Auto Incrementa quando nao informado Numero(Reajuste e Duplicação de Tabelas)
            if (ed.getNR_Tabela() < 1)
                ed.setNR_Tabela(getMaximo("NR_Tabela", "Tabelas_Vendas", "oid_Tipo_Tabela_Venda = "+ed.getOid_Tipo_Tabela_Venda()+" AND oid_Pessoa = '"+ed.getOid_Pessoa()+"'") + 1);
            if (ed.getOid_Tabela_Venda() < 1)
                ed.setOid_Tabela_Venda(getAutoIncremento("oid_Tabela_Venda", "Tabelas_Vendas"));
            sql = " INSERT INTO Tabelas_Vendas (" +
            	  "		 oid_Tabela_Venda" +
            	  "		,oid_Tipo_Tabela_Venda" +
            	  "		,oid_Pessoa" +
            	  "		,NR_Tabela" +
            	  "		,DT_Vigencia" +
            	  "		,DT_Validade" +
            	  "		,DT_Inclusao" +
            	  "		,USUARIO_STAMP) " +
            	  " VALUES (" +
            	  	ed.getOid_Tabela_Venda() +
            	  	"," + ed.getOid_Tipo_Tabela_Venda() +
            	  	",'" + ed.getOid_Pessoa() + "'" +
            	  	"," + ed.getNR_Tabela() +
            	  	",'" + ed.getDT_Vigencia() + "'" +
            	  	",'" + ed.getDT_Validade() + "'" +
            	  	",'" + ed.getDT_Inclusao() + "'" +
            	  	",'" + ed.getUsuario_Stamp() + "')";
            executasql.executarUpdate(sql);
        	return ed;
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
                    "inclui()");
        }
    }

    public void altera(Tabela_VendaED ed) throws Excecoes {

        String sql = null;
        try {
            sql = " UPDATE Tabelas_Vendas SET ";
            sql +="     DT_Vigencia = '"+ed.getDT_Vigencia() + "'" +
            	  "    ,DT_Validade = '"+ed.getDT_Validade()+"'";
            sql +=" WHERE oid_Tabela_Venda = "+ed.getOid_Tabela_Venda();
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
            	"altera()");
        }
    }

    public void deleta(Tabela_VendaED ed) throws Excecoes {

        String sql = null;
        try {
            sql = " DELETE FROM Tabelas_Vendas " +
            	  " WHERE oid_Tabela_Venda = " + ed.getOid_Tabela_Venda();
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
            		"deleta()");
        }
    }
    
    public ArrayList lista(Tabela_VendaED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();

        try {

            sql = " SELECT * " +
            	  " FROM Tabelas_Vendas " +
            	  " WHERE Tabelas_Vendas.oid_Tipo_Tabela_Venda = Tipos_Tabelas_Vendas.oid_Tipo_Tabela_Venda ";
            if (ed.getOid_Tabela_Venda() > 0)
            {
                sql += " AND Tabelas_Vendas.oid_Tabela_Venda = "+ed.getOid_Tabela_Venda();
            } else {
                if (ed.getOid_Tipo_Tabela_Venda() > 0)
                    sql += " AND Tabelas_Vendas.oid_Tipo_Tabela_Venda = "+ed.getOid_Tipo_Tabela_Venda();
                if (doValida(ed.getOid_Pessoa()))
                    sql += " AND Tabelas_Vendas.oid_Pessoa = '"+ed.getOid_Pessoa()+"'";
                if (ed.getNR_Tabela() > 0)
                    sql += " AND Tabelas_Vendas.NR_Tabela = "+ed.getNR_Tabela();
                //*** Data da Vigência
                if (doValida(ed.getDT_Vigencia()) && doValida(ed.getDT_Vigencia_Final())) {
                    sql +=" AND Tabelas_Vendas.DT_Vigencia BETWEEN '"+ed.getDT_Vigencia()+"' AND '"+ed.getDT_Vigencia_Final()+"'";
                } else if (doValida(ed.getDT_Vigencia()) || doValida(ed.getDT_Vigencia_Final())) {
                    sql +=" AND Tabelas_Vendas.DT_Vigencia = '"+(doValida(ed.getDT_Vigencia()) ? ed.getDT_Vigencia() : ed.getDT_Vigencia_Final())+"'";
                }
                //*** Data da Validade
                if (doValida(ed.getDT_Validade()) && doValida(ed.getDT_Validade_Final())) {
                    sql +=" AND Tabelas_Vendas.DT_Validade BETWEEN '"+ed.getDT_Validade()+"' AND '"+ed.getDT_Validade_Final()+"'";
                } else if (doValida(ed.getDT_Validade()) || doValida(ed.getDT_Validade_Final())) {
                    sql +=" AND Tabelas_Vendas.DT_Validade = '"+(doValida(ed.getDT_Validade()) ? ed.getDT_Validade() : ed.getDT_Validade_Final())+"'";
                }
                sql +=" ORDER BY Tabelas_Vendas.oid_Pessoa, Tipos_Tabelas_Vendas.CD_Tipo_Tabela_Venda, Tabelas_Vendas.DT_Vigencia DESC";
            }
            
            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                Tabela_VendaED edVolta = new Tabela_VendaED();
                edVolta.setOid_Tabela_Venda(res.getInt("oid_Tabela_Venda"));
                edVolta.setOid_Tipo_Tabela_Venda(res.getInt("oid_Tipo_Tabela_Venda"));
                edVolta.setOid_Pessoa(res.getString("oid_Pessoa"));
                if (edVolta.getOid_Tipo_Tabela_Venda() > 0)
                    edVolta.edTipo_Tabela = new Tipo_Tabela_VendaBD(executasql).getByRecord(new Tipo_Tabela_VendaED(edVolta.getOid_Tipo_Tabela_Venda()));
                if (doValida(edVolta.getOid_Pessoa()))
                    edVolta.edUnidade = UnidadeBean.getByOID_Pessoa(edVolta.getOid_Pessoa());
                edVolta.setNR_Tabela(res.getInt("NR_Tabela"));
                edVolta.setDT_Vigencia(FormataDataBean.getFormatDate(res.getString("DT_Vigencia")));
                edVolta.setDT_Validade(FormataDataBean.getFormatDate(res.getString("DT_Validade")));
                edVolta.setDT_Inclusao(FormataDataBean.getFormatDate(res.getString("DT_Inclusao")));
                list.add(edVolta);
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
    				"lista()");
        }
        return list;
    }

    public Tabela_VendaED getByRecord(Tabela_VendaED ed) throws Excecoes {

        try {
            Iterator iterator = this.lista(ed).iterator();
            if (iterator.hasNext())
                return (Tabela_VendaED) iterator.next();
            else return new Tabela_VendaED();
        } catch(Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
					"getByRecord()");
        }
    }

    public Tabela_VendaED getUltimaTabelaByTipo(Tabela_VendaED ed, boolean tabAtiva) throws Excecoes {

        try {
            if (!doValida(ed.getOid_Pessoa()))
                throw new Mensagens("Unidade não informada!");
            if (ed.getOid_Tipo_Tabela_Venda() < 1)
                throw new Mensagens("Tipo de Tabela não informado!");
            //*** Verifica se há Tabelas Para o Tipo Informado	
            if (!doExiste("Tabelas_Vendas",
                    	  " oid_Pessoa = '" +ed.getOid_Pessoa()+ "'" +
                    	  " AND oid_Tipo_Tabela_Venda = "+ed.getOid_Tipo_Tabela_Venda()))
                throw new Mensagens("Não há Tabela para esse Tipo!");

            //*** Monta-se a Clausula WHERE
            String qryWhere = "oid_Pessoa = '" +ed.getOid_Pessoa()+ "'" +
		 	  				  "	AND oid_Tipo_Tabela_Venda = "+ed.getOid_Tipo_Tabela_Venda();
            if (tabAtiva) qryWhere += " AND '"+getValueDef(ed.getDT_Tabela(), Data.getDataDMY())+"' BETWEEN DT_Vigencia AND DT_Validade";
            //*** Verifica se Existe Tabela, Buscando Data da Ultima TABELA
            String lastDate = getMaximoData("DT_Vigencia",
						 					"Tabelas_Vendas", 
						 					qryWhere);
            if (!doValida(lastDate))
            {
                if (tabAtiva) throw new Mensagens("Não há Tabela Ativa de Venda!");
                else throw new Mensagens("Tabela não encontrada!");
            }
            Tabela_VendaED edVolta = new Tabela_VendaED(getTableIntValue("oid_Tabela_Venda",
                    													 "Tabelas_Vendas",
                    													 "oid_Pessoa = '" +ed.getOid_Pessoa()+ "'" +
                    													 "	AND oid_Tipo_Tabela_Venda = "+ed.getOid_Tipo_Tabela_Venda() +
                    										  		  	 "	AND DT_Vigencia = '"+lastDate+"'"));
            if (edVolta.getOid_Tabela_Venda() < 1)
                throw new Mensagens("Tabela não encontrada!");

            return getByRecord(edVolta);

        } catch (Mensagens exc) {
            throw exc;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
    				"getUltimaTabelaByTipo()");
        }
    }

    public Tabela_VendaED duplicarTabela(Tabela_VendaED ed) throws Excecoes {

        try {
            //*** Busca Ultima Tabela Vigente
            Tabela_VendaED edVolta = getUltimaTabelaByTipo(ed, false);
            //*** Não Permite Duplicar uma Tabela Ativa caso ja Exista outra Tabela do Mesmo TIPO em Espera para entrar em Vigência
            if (!edVolta.isAtiva() && !edVolta.isVencida())
            	throw new Mensagens("Você so pode Reajustar Tabelas ATIVAS! Já Existe uma Tabela em ESPERA para o Tipo Passado!");

            //*** Busca Lista de Produtos Relacionados a essa Tabela
            ArrayList lista = new Preco_ProdutoBD(executasql).lista(new Preco_ProdutoED(edVolta.getOid_Tabela_Venda()));
            //*** ATUALIZA a Anterior com DT_Validade = DT_Encerramento
            edVolta.setDT_Validade(ed.getDT_Encerramento());
            altera(edVolta);
            //*** INCLUI Nova Tabela
            ed = new Tabela_VendaBD(executasql).inclui(ed);
            //*** Insere todos os Produtos para a Nova Tabela
            //    Ajustando o Preço se necessário
            int oid_Preco_Produto = 0;
            for (int i=0; i<lista.size(); i++)
            {
                Preco_ProdutoED edPreco = (Preco_ProdutoED) lista.get(i);
                edPreco.setDT_Vigencia(null);
                if (oid_Preco_Produto < 1)
                    oid_Preco_Produto = getAutoIncremento("oid_Preco_Produto", "Precos_Produtos");
                else ++oid_Preco_Produto;
                edPreco.setOID_Preco_Produto(oid_Preco_Produto);
                edPreco.setOid_Tabela_Venda(ed.getOid_Tabela_Venda());
                if (ed.getPE_Ajuste() != 0)
                    Pro004Bean.calculaPrecosByPerc(edPreco, edPreco.getVL_Produto(), 0, ed.getPE_Ajuste());

                //*** Tabelas duplicadas não destacão Itens como Alterado!
                edPreco.setDM_Alterado("N");
                new Preco_ProdutoBD(executasql).inclui(edPreco);
            }
            return ed;
        } catch (Mensagens exc) {
            throw exc;    
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
    				"duplicarTabela()");
        }
    }
    
    public Tabela_VendaED reajusteTabela(Tabela_VendaED ed) throws Excecoes {

        try {
            //*** Busca Ultima Tabela Vigente
            Tabela_VendaED edVolta = getUltimaTabelaByTipo(ed, false);
            //*** Não Permite Duplicar uma Tabela Ativa caso ja Exista outra Tabela do Mesmo TIPO em Espera para entrar em Vigência
            if (!edVolta.isAtiva() && !edVolta.isVencida())
                throw new Mensagens("Você so pode Reajustar Tabelas ATIVAS! Já Existe uma Tabela em ESPERA para o Tipo Passado!");

            //*** Busca Lista de Produtos Relacionados a essa Tabela
            ArrayList lista = new Preco_ProdutoBD(executasql).lista(new Preco_ProdutoED(edVolta.getOid_Tabela_Venda(), false, false));
            //*** ATUALIZA a Anterior com DT_Validade = DT_Encerramento
            edVolta.setDT_Validade(ed.getDT_Encerramento());
            altera(edVolta);
            //*** INCLUI Nova Tabela
            ed = new Tabela_VendaBD(executasql).inclui(ed);
            //*** Insere todos os Produtos para a Nova Tabela
            //    Ajustando pelo Preço de Venda
            int oid_Preco_Produto = 0;
            for (int i=0; i<lista.size(); i++)
            {
                Preco_ProdutoED edPreco = (Preco_ProdutoED) lista.get(i);
                edPreco.setDT_Vigencia(null);
                double vlVenda = getTableDoubleValue("VL_Produto", "Produtos_Clientes", "oid_Produto_Cliente = '"+edPreco.getOID_Produto_Cliente()+"'");
                double peMarkup = getTableDoubleValue("VL_Markup", "Produtos_Clientes", "oid_Produto_Cliente = '"+edPreco.getOID_Produto_Cliente()+"'");

                if (oid_Preco_Produto < 1)
                    oid_Preco_Produto = getAutoIncremento("oid_Preco_Produto", "Precos_Produtos");
                else ++oid_Preco_Produto;
                edPreco.setOID_Preco_Produto(oid_Preco_Produto);
                edPreco.setOid_Tabela_Venda(ed.getOid_Tabela_Venda());
                
                double vlAntigo = edPreco.getVL_Produto();
                //*** Calcula Valores
                Pro004Bean.calculaPrecosByPerc(edPreco, vlVenda, peMarkup, ed.getPE_Ajuste());
                //*** Verifica se não houve reajuste, o Default na inclusão é "S"
                if (vlAntigo == edPreco.getVL_Produto())
                    edPreco.setDM_Alterado("N");

                new Preco_ProdutoBD(executasql).inclui(edPreco);
            }
            return ed;
        } catch (Mensagens exc) {
            throw exc;    
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
                    "reajusteTabela()");
        }
    }
    
    public void relTabelasPrecos(RelatorioED ed) throws Excecoes {

        String sql = null;
        ArrayList lista = new ArrayList();
        try {

            sql = " SELECT * " +
            	  " FROM Tabelas_Vendas " +
            	  " WHERE Tabelas_Vendas.oid_Tipo_Tabela_Venda = Tipos_Tabelas_Vendas.oid_Tipo_Tabela_Venda ";
            if (ed.getOid_tabela_venda() > 0)
                sql += " AND Tabelas_Vendas.oid_Tabela_Venda = "+ed.getOid_tabela_venda();
            else {
                if (ed.getOid_tipo_tabela_venda() > 0)
                    sql += " AND Tabelas_Vendas.oid_Tipo_Tabela_Venda = "+ed.getOid_tipo_tabela_venda();
                if (doValida(ed.getOid_pessoa()))
                    sql += " AND Tabelas_Vendas.oid_Pessoa = '"+ed.getOid_pessoa()+"'";
                if (ed.getNr_tabela() > 0)
                    sql += " AND Tabelas_Vendas.NR_Tabela = "+ed.getNr_tabela();
                //*** Data da Vigência
                if (doValida(ed.getDt_vigencia()) && doValida(ed.getDt_vigencia_final())) {
                    sql +=" AND Tabelas_Vendas.DT_Vigencia BETWEEN '"+ed.getDt_vigencia()+"' AND '"+ed.getDt_vigencia_final()+"'";
                } else if (doValida(ed.getDt_vigencia()) || doValida(ed.getDt_vigencia_final())) {
                    sql +=" AND Tabelas_Vendas.DT_Vigencia = '"+(doValida(ed.getDt_vigencia()) ? ed.getDt_vigencia() : ed.getDt_vigencia_final())+"'";
                }
                //*** Data da Validade
                if (doValida(ed.getDt_validade()) && doValida(ed.getDt_validade_final())) {
                    sql +=" AND Tabelas_Vendas.DT_Validade BETWEEN '"+ed.getDt_validade()+"' AND '"+ed.getDt_validade_final()+"'";
                } else if (doValida(ed.getDt_validade()) || doValida(ed.getDt_validade_final())) {
                    sql +=" AND Tabelas_Vendas.DT_Validade = '"+(doValida(ed.getDt_validade()) ? ed.getDt_validade() : ed.getDt_validade_final())+"'";
                }
            }
            sql +=" ORDER BY Tipos_Tabelas_Vendas.CD_Tipo_Tabela_Venda, Tabelas_Vendas.DT_Vigencia DESC";
            
            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                ProdutoED edPro = new ProdutoED(res.getString("oid_Pessoa"), res.getInt("oid_Tabela_Venda"));
                edPro.setOid_Pessoa(ed.getOid_fornecedor());//Fornecedor
                //*** Inclusos no Periodo
                edPro.edPreco.setDT_Vigencia(ed.getDt_inicial());
                edPro.edPreco.setDT_Vigencia_Final(ed.getDt_final());
                edPro.edPreco.setDM_Alterado(ed.getDm_alterado());//Itens Destacados
                ArrayList listaProdutos = new ProdutoBD(executasql).listaProdutoTabela(edPro);
                for (int i=0; i < listaProdutos.size(); i++)
                {
                    RelatorioED edVolta = new RelatorioED();
	                edVolta.setOid_tabela_venda(res.getInt("oid_Tabela_Venda"));
	                edVolta.setOid_tipo_tabela_venda(res.getInt("oid_Tipo_Tabela_Venda"));
	                edVolta.setOid_pessoa(res.getString("oid_Pessoa"));
	                if (edVolta.getOid_tipo_tabela_venda() > 0)
                    {
	                    Tipo_Tabela_VendaED edTipo_Tabela = new Tipo_Tabela_VendaBD(executasql).getByRecord(new Tipo_Tabela_VendaED(edVolta.getOid_tipo_tabela_venda()));
	                    edVolta.setCd_tipo_tabela_venda(edTipo_Tabela.getCD_Tipo_Tabela_Venda());
	                    edVolta.setNm_tipo_tabela_venda(edTipo_Tabela.getNM_Tipo_Tabela_Venda());
	                }
	                if (doValida(edVolta.getOid_pessoa()))
                    {
	                    UnidadeBean edUnidade = UnidadeBean.getByOID_Pessoa(edVolta.getOid_pessoa());
	                    edVolta.setCd_unidade(edUnidade.getCD_Unidade());
	                    edVolta.setNm_unidade(edUnidade.getNM_Fantasia());
	                }
	                edVolta.setNr_tabela(res.getInt("NR_Tabela"));
	                edVolta.setDt_vigencia(FormataDataBean.getFormatDate(res.getString("DT_Vigencia")));
	                edVolta.setDt_validade(FormataDataBean.getFormatDate(res.getString("DT_Validade")));
                    edVolta.setDt_inicial(ed.getDt_inicial());
                    edVolta.setDt_final(ed.getDt_final());
	                edVolta.setDm_situacao(new Tabela_VendaED().getDescSituacao(edVolta.getOid_tabela_venda(), edVolta.getDt_vigencia(), edVolta.getDt_validade()));
                
	                //*** Dados da Lista de Produtos
                    ProdutoED edProduto = (ProdutoED) listaProdutos.get(i);
                    
                    edVolta.setOid_produto(Integer.parseInt(edProduto.getOID_Produto()));
                    edVolta.setCd_produto(edProduto.getCD_Produto());
                    edVolta.setNm_produto(edProduto.getNM_Produto());
                    edVolta.setCd_unidade_produto(edProduto.getCD_Unidade_Produto());
                    edVolta.setNm_descricao(getValueDef(edProduto.getNM_Descricao_Caixa(), ""));
                    edVolta.setVl_desconto_avista(edProduto.edPreco.getVL_Desconto_Avista());
                    edVolta.setVl_desconto_7_dias(edProduto.edPreco.getVL_Desconto_7_Dias());
                    edVolta.setVl_produto(edProduto.edPreco.getVL_Produto());
                    edVolta.setVl_acrescimo_21_dias(edProduto.edPreco.getVL_Acrescimo_21_Dias());
                    edVolta.setVl_acrescimo_28_dias(edProduto.edPreco.getVL_Acrescimo_28_Dias());
                    edVolta.setVl_acrescimo_30_dias(edProduto.edPreco.getVL_Acrescimo_30_Dias());
                    edVolta.setDm_alterado(edProduto.edPreco.getDM_Alterado());
                    lista.add(edVolta);
                }
            }
            ed.setLista(lista);
            //*** Chama o Gerador de Relatórios Jasper
   	        new JasperRL(ed).geraRelatorio();
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), 
    				"relTabelasPrecos()");
        }
    }
}