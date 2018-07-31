package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import com.master.ed.Preco_ProdutoED;
import com.master.ed.Tabela_VendaED;
import com.master.iu.wms005Bean;
import com.master.root.FormataDataBean;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

public class Preco_ProdutoBD extends BancoUtil {

    private ExecutaSQL executasql;
    FormataDataBean dataFormatada = new FormataDataBean();

    public Preco_ProdutoBD(ExecutaSQL sql) {
        super(sql);
        this.executasql = sql;
    }

    public Preco_ProdutoED inclui(Preco_ProdutoED ed) throws Excecoes {

        String sql = null;
        try {
            //*** Busca OID
            if (ed.getOID_Preco_Produto() < 1)
                ed.setOID_Preco_Produto(getAutoIncremento("oid_Preco_Produto", "Precos_Produtos"));
            sql = " INSERT INTO Precos_Produtos (" + 
            	  "		 oid_Preco_Produto" + 
            	  "		,oid_Tabela_Venda" + 
            	  "		,oid_Produto_Cliente" + 
            	  "		,oid_Pessoa" + 
            	  "		,DT_Stamp" + 
            	  "		,VL_Produto" + 
            	  "		,PE_Desconto_Avista" + 
            	  "		,PE_Desconto_7_Dias" + 
            	  "		,PE_Acrescimo_21_Dias" + 
            	  "		,PE_Acrescimo_28_Dias" +
                  "     ,PE_Acrescimo_30_Dias" + 
            	  "		,VL_Desconto_Avista" + 
            	  "		,VL_Desconto_7_Dias" + 
            	  "		,VL_Acrescimo_21_Dias" + 
            	  "		,VL_Acrescimo_28_Dias" +
                  "     ,VL_Acrescimo_30_Dias" +
                  "     ,DM_Alterado" +
                  "     ,DT_Vigencia)";//Data de Inclusão
            sql +=" VALUES ( ";
            sql += ed.getOID_Preco_Produto() + 
            "," + ed.getOid_Tabela_Venda() + 
            ",'" + ed.getOID_Produto_Cliente() + "'" + 
            ",'" + ed.getOid_Pessoa() + "'" + 
            ",'" + Data.getDataDMY() + "'" + 
            "," + ed.getVL_Produto() + 
            "," + ed.getPE_Desconto_Avista() + 
            "," + ed.getPE_Desconto_7_Dias() + 
            "," + ed.getPE_Acrescimo_21_Dias() + 
            "," + ed.getPE_Acrescimo_28_Dias() +
            "," + ed.getPE_Acrescimo_30_Dias() + 
            "," + ed.getVL_Desconto_Avista() + 
            "," + ed.getVL_Desconto_7_Dias() + 
            "," + ed.getVL_Acrescimo_21_Dias() +
            "," + ed.getVL_Acrescimo_28_Dias() + 
            "," + ed.getVL_Acrescimo_30_Dias() +
            "," + getSQLStringDef(ed.getDM_Alterado(), "S")+
            "," + getSQLDate(Data.getDataDMY()) +")";

            executasql.executarUpdate(sql);
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "inclui()");
        }
        return ed;
    }

    public void altera(Preco_ProdutoED ed) throws Excecoes {

        String sql = null;
        try {

            sql = " UPDATE Precos_Produtos SET ";
            sql += "  VL_Produto= " + ed.getVL_Produto();
            sql += " ,PE_Desconto_Avista= " + ed.getPE_Desconto_Avista();
            sql += " ,PE_Desconto_7_Dias= " + ed.getPE_Desconto_7_Dias();
            sql += " ,PE_Acrescimo_21_Dias= " + ed.getPE_Acrescimo_21_Dias();
            sql += " ,PE_Acrescimo_28_Dias= " + ed.getPE_Acrescimo_28_Dias();
            sql += " ,PE_Acrescimo_30_Dias= " + ed.getPE_Acrescimo_30_Dias();
            sql += " ,VL_Desconto_Avista= " + ed.getVL_Desconto_Avista();
            sql += " ,VL_Desconto_7_Dias= " + ed.getVL_Desconto_7_Dias();
            sql += " ,VL_Acrescimo_21_Dias= " + ed.getVL_Acrescimo_21_Dias();
            sql += " ,VL_Acrescimo_28_Dias= " + ed.getVL_Acrescimo_28_Dias();
            sql += " ,VL_Acrescimo_30_Dias= " + ed.getVL_Acrescimo_30_Dias();
            sql += " ,DM_Alterado= " + getSQLStringDef(ed.getDM_Alterado(), "N");
            sql += " WHERE oid_Preco_Produto = " + ed.getOID_Preco_Produto();

            executasql.executarUpdate(sql);
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "altera()");
        }
    }

    public void deleta(Preco_ProdutoED ed) throws Excecoes {

        String sql = null;
        try {

            sql = " DELETE FROM precos_produtos " + 
            	  " WHERE Oid_preco_Produto = " + ed.getOID_Preco_Produto();
            executasql.executarUpdate(sql);
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "deleta()");
        }
    }

    public ArrayList lista(Preco_ProdutoED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();
        try {

            sql = " SELECT Precos_Produtos.*" +
                  "       ,Produtos_Clientes.*";
            if (ed.isCarregaProduto())
                sql+= "    ,Produtos.CD_Produto" +
                      "    ,Produtos.NM_Produto" +
                      "    ,Produtos.NR_QT_Caixa" +
                      "    ,Produtos.NR_Peso_Medio" +
                      "    ,Produtos.oid_Pessoa as oid_Fornecedor" +
                      "    ,Produtos.oid_Tipo_Produto" +
                      "    ,Produtos.oid_Unidade_Produto" +
                      "    ,Produtos.oid_Estrutura_Produto";
            sql +=" FROM Precos_Produtos" + 
            	  "		,Produtos_Clientes" + 
            	  " WHERE Produtos.oid_Produto = Produtos_Clientes.oid_Produto " + 
            	  "   AND Produtos_Clientes.oid_Produto_Cliente = Precos_Produtos.oid_Produto_Cliente";

            if (ed.getOID_Preco_Produto() > 0)
                sql += " AND Precos_Produtos.oid_Preco_Produto = "+ed.getOID_Preco_Produto();
            else {
                if (ed.getOid_Tabela_Venda() > 0)
                {
                    sql += " AND Precos_Produtos.oid_Tabela_Venda = Tabelas_Vendas.oid_Tabela_Venda" + 
                           " AND Precos_Produtos.oid_Tabela_Venda = "+ed.getOid_Tabela_Venda();
                }
                if (doValida(ed.getOID_Produto()))
                    sql += " AND Produtos_Clientes.oid_Produto = '"+ed.getOID_Produto()+"'";
                if (doValida(ed.getOID_Produto_Cliente()))
                    sql += " AND Precos_Produtos.oid_Produto_Cliente = '"+ed.getOID_Produto_Cliente()+"'";
                if (doValida(ed.getOid_Pessoa()))
                    sql += " AND Precos_Produtos.oid_Pessoa = '"+ed.getOid_Pessoa()+"'";
                if (doValida(ed.getDM_Alterado()))
                    sql += " AND Precos_Produtos.DM_Alterado = '"+ed.getDM_Alterado()+"'";
            }
            sql += " ORDER BY Produtos.CD_Produto";
            
            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                Preco_ProdutoED edVolta = new Preco_ProdutoED();
                edVolta.setOID_Preco_Produto(res.getLong("oid_Preco_Produto"));
                edVolta.setOid_Tabela_Venda(res.getInt("oid_Tabela_Venda"));
                if (edVolta.getOid_Tabela_Venda() > 0 && ed.isCarregaTabela())
                    edVolta.edTabela = new Tabela_VendaBD(executasql).getByRecord(new Tabela_VendaED(edVolta.getOid_Tabela_Venda())); 
                
                edVolta.setOID_Produto(res.getString("oid_Produto"));
                edVolta.setOID_Produto_Cliente(res.getString("oid_Produto_Cliente"));
                edVolta.setOid_Pessoa(res.getString("oid_Pessoa"));
                
                edVolta.setVL_Produto(res.getDouble("VL_Produto"));
                edVolta.setPE_Desconto_Avista(res.getDouble("PE_Desconto_Avista"));
                edVolta.setPE_Desconto_7_Dias(res.getDouble("PE_Desconto_7_Dias"));
                edVolta.setPE_Acrescimo_21_Dias(res.getDouble("PE_Acrescimo_21_Dias"));
                edVolta.setPE_Acrescimo_28_Dias(res.getDouble("PE_Acrescimo_28_Dias"));
                edVolta.setPE_Acrescimo_30_Dias(res.getDouble("PE_Acrescimo_30_Dias"));
                edVolta.setVL_Desconto_Avista(res.getDouble("VL_Desconto_Avista"));
                edVolta.setVL_Desconto_7_Dias(res.getDouble("VL_Desconto_7_Dias"));
                edVolta.setVL_Acrescimo_21_Dias(res.getDouble("VL_Acrescimo_21_Dias"));
                edVolta.setVL_Acrescimo_28_Dias(res.getDouble("VL_Acrescimo_28_Dias"));
                edVolta.setVL_Acrescimo_30_Dias(res.getDouble("VL_Acrescimo_30_Dias"));
                edVolta.setDM_Alterado(res.getString("DM_Alterado"));

                edVolta.setDT_Vigencia(dataFormatada.getDT_FormataData(res.getString("dt_vigencia")));
                edVolta.setDT_Validade(dataFormatada.getDT_FormataData(res.getString("dt_validade")));
                
                /** PRODUTO **/
                if (ed.isCarregaProduto())
                {
                    edVolta.setCD_Produto(res.getString("CD_Produto"));
                    edVolta.setNM_Produto(res.getString("NM_Produto"));
                    edVolta.setNR_QT_Caixa(res.getDouble("NR_QT_Caixa"));
                    edVolta.setNR_Peso_Medio(res.getDouble("NR_Peso_Medio"));

                    edVolta.setOid_Pessoa_Fornecedor(res.getString("oid_Fornecedor"));
                    edVolta.setNR_CNPJ_CPF_Fornecedor(getTableStringValue("NR_CNPJ_CPF","Pessoas", "oid_Pessoa="+getSQLString(edVolta.getOid_Pessoa_Fornecedor())));
                    edVolta.setNM_Razao_Social_Fornecedor(getTableStringValue("NM_Razao_Social","Pessoas", "oid_Pessoa="+getSQLString(edVolta.getOid_Pessoa_Fornecedor())));
                    
                    edVolta.setOid_Estrutura_Produto(res.getInt("oid_Estrutura_Produto"));
                    edVolta.setCD_Estrutura_Produto(getTableStringValue("CD_Estrutura_Produto","Estruturas_Produtos", "oid_Estrutura_Produto="+edVolta.getOid_Estrutura_Produto()));
                    edVolta.setNM_Estrutura_Produto(getTableStringValue("NM_Estrutura_Produto","Estruturas_Produtos", "oid_Estrutura_Produto="+edVolta.getOid_Estrutura_Produto()));
                    
                    edVolta.setOid_Tipo_Produto(res.getInt("oid_Tipo_Produto"));
                    edVolta.setCD_Tipo_Produto(getTableStringValue("CD_Tipo_Produto","Tipos_Produtos", "oid_Tipo_Produto="+edVolta.getOid_Tipo_Produto()));
                    edVolta.setNM_Tipo_Produto(getTableStringValue("NM_Tipo_Produto","Tipos_Produtos", "oid_Tipo_Produto="+edVolta.getOid_Tipo_Produto()));
                    
                    edVolta.setOid_Unidade_Produto(res.getInt("oid_Unidade_Produto"));
                    edVolta.setCD_Unidade_Produto(getTableStringValue("CD_Unidade_Produto","Unidades_Produtos", "oid_Unidade_Produto="+edVolta.getOid_Unidade_Produto()));
                    edVolta.setNM_Unidade_Produto(getTableStringValue("NM_Unidade_Produto","Unidades_Produtos", "oid_Unidade_Produto="+edVolta.getOid_Unidade_Produto()));
                    
                    edVolta.setDM_Situacao(res.getString("DM_Situacao"));
                    edVolta.setPE_Comissao(res.getDouble("PE_Comissao"));
                    edVolta.setNR_QT_Atual(new wms005Bean().getQtdEstoque(edVolta.getOID_Produto_Cliente()));
                }
                list.add(edVolta);
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "lista()");
        }
        return list;
    }
    
    public Preco_ProdutoED getByRecord(Preco_ProdutoED ed) throws Excecoes {

        try {
            Iterator iterator = this.lista(ed).iterator();
            return (iterator.hasNext()) ? (Preco_ProdutoED) iterator.next() : new Preco_ProdutoED();
        } catch (Excecoes exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "getByRecord()");
        }
    }
}