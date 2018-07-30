package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.ProdutoED;
import com.master.ed.ProdutoRelED;
import com.master.ed.RelatorioED;
import com.master.rn.ProdutoRN;
import com.master.util.Excecoes;
import com.master.util.Mensagens;
import com.master.util.Utilitaria;
import com.master.util.bd.ExecutaSQL;

public class ProdutoBean  {
	private ExecutaSQL executasql;
	Utilitaria util = new Utilitaria(executasql);

    public ProdutoED inclui(HttpServletRequest request) throws Excecoes {

        String oid_Pessoa = request.getParameter("oid_Pessoa");
        String oid_Estrutura_Produto = request.getParameter("oid_Estrutura_Produto");
        String oid_Unidade_Produto = request.getParameter("oid_Unidade_Produto");
        String oid_Tipo_Produto = request.getParameter("oid_Tipo_Produto");
        String oid_Estrutura_Fornecedor = request.getParameter("oid_Estrutura_Fornecedor");
        String oid_Classificacao_Fiscal = request.getParameter("oid_Classificacao_Fiscal");
        String oid_Situacao_Tributaria = request.getParameter("oid_Situacao_Tributaria");
        String oid_Setor_Produto = request.getParameter("oid_Setor_Produto");

        String CD_Produto = request.getParameter("FT_CD_Produto");
        String NM_Produto = request.getParameter("FT_NM_Produto");
        String NM_Descricao_Caixa = request.getParameter("FT_NM_Descricao_Caixa");
        String CD_Fornecedor = request.getParameter("FT_CD_Fornecedor");
        String NR_Peso_Liquido = request.getParameter("FT_NR_Peso_Liquido");
        String NR_Peso_Medio = request.getParameter("FT_NR_Peso_Medio");
        String NR_QT_Caixa = request.getParameter("FT_NR_QT_Caixa");

        if (!util.doValida(CD_Produto))
            throw new Mensagens("Código do produto não informado!");
        if (!util.doValida(NM_Produto))
            throw new Mensagens("Descrição do produto não informada!");
        //if (!util.doValida(oid_Pessoa))
           // throw new Mensagens("Fornecedor não informado!");
        //if (!util.doValida(oid_Estrutura_Produto))
            //throw new Mensagens("Estrutura do Produto não informada!");
        if (!util.doValida(oid_Unidade_Produto))
            throw new Mensagens("Unidade do Produto não informada!");
        if (!util.doValida(oid_Tipo_Produto))
            throw new Mensagens("Tipo do Produto não informado!");
        if (!util.doValida(oid_Classificacao_Fiscal))
            throw new Mensagens("Classificação Fiscal não informada!");
        if (!util.doValida(oid_Situacao_Tributaria))
            throw new Mensagens("Situação Tributária não informada!");
        if (!util.doValida(oid_Setor_Produto))
            throw new Mensagens("Setor não informado!");

        //*** Valida
        if (util.doExiste("Produtos", "CD_Produto = '"+CD_Produto+"'"))
            throw new Mensagens("Ja existe um produto com o Código informado!");

        ProdutoED ed = new ProdutoED();
        ed.setCD_Produto(CD_Produto);
        ed.setNM_Produto(NM_Produto);
        ed.setOid_Pessoa(oid_Pessoa);
        // ed.setOid_Estrutura_Produto(Integer.parseInt(oid_Estrutura_Produto));
        ed.setOid_Unidade_Produto(Integer.parseInt(oid_Unidade_Produto));
        ed.setOID_Tipo_Produto(Integer.parseInt(oid_Tipo_Produto));
        ed.setOid_Classificacao_Fiscal(Integer.parseInt(oid_Classificacao_Fiscal));
        ed.setOid_Situacao_Tributaria(Integer.parseInt(oid_Situacao_Tributaria));
        ed.setOid_Setor_Produto(Integer.parseInt(oid_Setor_Produto));

        if (util.doValida(NM_Descricao_Caixa))
            ed.setNM_Descricao_Caixa(NM_Descricao_Caixa);
        if (util.doValida(CD_Fornecedor))
            ed.setCD_Fornecedor(CD_Fornecedor);
        if (util.doValida(oid_Estrutura_Fornecedor))
            ed.setOid_Estrutura_Fornecedor(Integer.parseInt(oid_Estrutura_Fornecedor));
        if (util.doValida(NR_Peso_Liquido))
            ed.setNR_Peso_Liquido(Double.parseDouble(NR_Peso_Liquido));
        if (util.doValida(NR_Peso_Medio))
            ed.setNR_Peso_Medio(Double.parseDouble(NR_Peso_Medio));
        if (util.doValida(NR_QT_Caixa))
            ed.setNR_QT_Caixa(Integer.parseInt(NR_QT_Caixa));

        return new ProdutoRN().inclui(ed);
    }

    public void altera(HttpServletRequest request) throws Excecoes {

        String oid_Produto = request.getParameter("oid_Produto");
        String oid_Pessoa = request.getParameter("oid_Pessoa");
        String oid_Estrutura_Produto = request.getParameter("oid_Estrutura_Produto");
        String oid_Unidade_Produto = request.getParameter("oid_Unidade_Produto");
        String oid_Tipo_Produto = request.getParameter("oid_Tipo_Produto");
        String oid_Estrutura_Fornecedor = request.getParameter("oid_Estrutura_Fornecedor");
        String oid_Classificacao_Fiscal = request.getParameter("oid_Classificacao_Fiscal");
        String oid_Situacao_Tributaria = request.getParameter("oid_Situacao_Tributaria");
        String oid_Setor_Produto = request.getParameter("oid_Setor_Produto");

        String CD_Produto = request.getParameter("FT_CD_Produto");
        String NM_Produto = request.getParameter("FT_NM_Produto");
        String NM_Descricao_Caixa = request.getParameter("FT_NM_Descricao_Caixa");
        String CD_Fornecedor = request.getParameter("FT_CD_Fornecedor");
        String NR_Peso_Liquido = request.getParameter("FT_NR_Peso_Liquido");
        String NR_Peso_Medio = request.getParameter("FT_NR_Peso_Medio");
        String NR_QT_Caixa = request.getParameter("FT_NR_QT_Caixa");

        if (!util.doValida(oid_Produto))
            throw new Mensagens("Código Produto não informado!");
        if (!util.doValida(CD_Produto))
            throw new Mensagens("Código do produto não informado!");
        if (!util.doValida(NM_Produto))
            throw new Mensagens("Descrição do produto não informada!");
       // if (!util.doValida(oid_Pessoa))
         //   throw new Mensagens("Fornecedor não informado!");
        //if (!util.doValida(oid_Estrutura_Produto))
          //  throw new Mensagens("Estrutura do Produto não informada!");
        if (!util.doValida(oid_Unidade_Produto))
            throw new Mensagens("Unidade do Produto não informada!");
        if (!util.doValida(oid_Tipo_Produto))
            throw new Mensagens("Tipo do Produto não informado!");
        if (!util.doValida(oid_Classificacao_Fiscal))
            throw new Mensagens("Classificação Fiscal não informada!");
        if (!util.doValida(oid_Situacao_Tributaria))
            throw new Mensagens("Situação Tributária não informada!");
        if (!util.doValida(oid_Setor_Produto))
            throw new Mensagens("Setor não informado!");

        ProdutoED ed = new ProdutoED(oid_Produto);
        ed.setCD_Produto(CD_Produto);
        ed.setNM_Produto(NM_Produto);
        ed.setOid_Pessoa(oid_Pessoa);
        ed.setOid_Estrutura_Produto(Integer.parseInt(oid_Estrutura_Produto));
        ed.setOid_Unidade_Produto(Integer.parseInt(oid_Unidade_Produto));
        ed.setOID_Tipo_Produto(Integer.parseInt(oid_Tipo_Produto));
        ed.setOid_Classificacao_Fiscal(Integer.parseInt(oid_Classificacao_Fiscal));
        ed.setOid_Situacao_Tributaria(Integer.parseInt(oid_Situacao_Tributaria));
        ed.setOid_Setor_Produto(Integer.parseInt(oid_Setor_Produto));

        if (util.doValida(NM_Descricao_Caixa))
            ed.setNM_Descricao_Caixa(NM_Descricao_Caixa);
        if (util.doValida(CD_Fornecedor))
            ed.setCD_Fornecedor(CD_Fornecedor);
        if (util.doValida(oid_Estrutura_Fornecedor))
            ed.setOid_Estrutura_Fornecedor(Integer.parseInt(oid_Estrutura_Fornecedor));
        if (util.doValida(NR_Peso_Liquido))
            ed.setNR_Peso_Liquido(Double.parseDouble(NR_Peso_Liquido));
        if (util.doValida(NR_Peso_Medio))
            ed.setNR_Peso_Medio(Double.parseDouble(NR_Peso_Medio));
        if (util.doValida(NR_QT_Caixa))
            ed.setNR_QT_Caixa(Integer.parseInt(NR_QT_Caixa));

        new ProdutoRN().altera(ed);
    }

    public void deleta(HttpServletRequest request) throws Excecoes {

        String oid_Produto = request.getParameter("oid_Produto");
        if (!util.doValida(oid_Produto))
            throw new Mensagens("Produto não informado!");

        new ProdutoRN().deleta(new ProdutoED(oid_Produto));
    }

    public ProdutoED getByOID_Produto(String oid_Produto) throws Excecoes {

        ProdutoED ed = new ProdutoED();
        if (util.doValida(oid_Produto))
            ed.setOID_Produto(oid_Produto);

        return new ProdutoRN().getByRecord(ed);
    }

    public ProdutoED getByOID_ProdutoDistribuidora(String oid_Produto, String oid_Distribuidor) throws Excecoes {

        if (!util.doValida(oid_Produto) || !util.doValida(oid_Distribuidor))
            throw new Excecoes("Parâmetros incorretos!");

        ProdutoED ed = new ProdutoED();
        ed.setOID_Produto(oid_Produto);
        ed.setOid_Pessoa_Distribuidor(oid_Distribuidor);

        return new ProdutoRN().getByRecord(ed);
    }

    public ProdutoED getByOID_Produto_Cliente(String oid_Produto_Cliente) throws Excecoes {

        if (!util.doValida(oid_Produto_Cliente))
            throw new Mensagens("ID Produto Cliente não informado!");

        ProdutoED ed = new ProdutoED();
        ed.setOID_Produto_Cliente(oid_Produto_Cliente);
        Iterator iterator = new ProdutoRN().listaProdutoCliente(ed).iterator();
        if (iterator.hasNext())
            return (ProdutoED) iterator.next();
        else return new ProdutoED();
    }

    public ProdutoED getByRecord(HttpServletRequest request) throws Excecoes {

        String oid_Produto = request.getParameter("oid_Produto");
        String oid_Pessoa = request.getParameter("oid_Pessoa");

        ProdutoED ed = new ProdutoED();
        if (util.doValida(oid_Produto))
            ed.setOID_Produto(oid_Produto);
        if (util.doValida(oid_Pessoa))
            ed.setOid_Pessoa(oid_Pessoa);

        return new ProdutoRN().getByRecord(ed);

    }

    public ProdutoED getByCD_Produto(HttpServletRequest request) throws Excecoes {

        String oid_Pessoa_Distribuidor = request.getParameter("oid_Pessoa_Distribuidor");
        String cd_Produto = request.getParameter("FT_CD_Produto");
        String oid_Pessoa = request.getParameter("oid_Pessoa");

        ProdutoED ed = new ProdutoED();
        if (util.doValida(cd_Produto))
            ed.setCD_Produto(cd_Produto);
        if (util.doValida(oid_Pessoa_Distribuidor))
            ed.setOid_Pessoa_Distribuidor(oid_Pessoa_Distribuidor);
        if (util.doValida(oid_Pessoa))
            ed.setOid_Pessoa(oid_Pessoa);

        return new ProdutoRN().getByRecord(ed);
    }
    public ProdutoED getByCD_Produto(String cdProduto) throws Excecoes {

        ProdutoED ed = new ProdutoED();
        if (util.doValida(cdProduto))
        {
            if (util.doValida(cdProduto))
                ed.setCD_Produto(cdProduto);
            return new ProdutoRN().getByRecord(ed);
        } else return ed;
    }

    public ProdutoED getByCD_Fornecedor(HttpServletRequest request) throws Excecoes {

        String oid_Pessoa_Distribuidor = request.getParameter("oid_Pessoa_Distribuidor");
        String CD_Fornecedor = request.getParameter("FT_CD_Fornecedor");
        String oid_Pessoa = request.getParameter("oid_Pessoa");

        ProdutoED ed = new ProdutoED();
        if (util.doValida(CD_Fornecedor))
            ed.setCD_Fornecedor(CD_Fornecedor);
        if (util.doValida(oid_Pessoa_Distribuidor))
            ed.setOid_Pessoa_Distribuidor(oid_Pessoa_Distribuidor);
        if (util.doValida(oid_Pessoa))
            ed.setOid_Pessoa(oid_Pessoa);

        return new ProdutoRN().getByRecord(ed);
    }

    public byte[] geraRelatorioItemXCliente(HttpServletRequest req, HttpServletResponse Response) throws Excecoes {

        ProdutoED ed = new ProdutoED();
        ed.setOid_Pessoa(req.getParameter("oid_Pessoa"));
        String deposito = req.getParameter("FT_OID_Deposito");
        if (util.doValida(deposito)){
        	ed.setOID_Deposito(Integer.valueOf(deposito).intValue());
        } else {
        	deposito = req.getParameter("oid_Deposito");
            if (util.doValida(deposito)){
            	ed.setOID_Deposito(Integer.valueOf(deposito).intValue());
            }
        }

        String oid_Tipo_Estoque = req.getParameter("oid_Tipo_Estoque");
        if (util.doValida(oid_Tipo_Estoque))
            ed.setOid_Tipo_Estoque(Integer.parseInt(oid_Tipo_Estoque));

        return new ProdutoRN().geraRelatorioItemXCliente(ed);
    }

    public ArrayList Produto_Lista(HttpServletRequest request) throws Excecoes {

        String oid_Pessoa = request.getParameter("oid_Pessoa");
        String oid_Pessoa_Distribuidor = request.getParameter("oid_Pessoa_Distribuidor");
        String oid_Setor_Produto = request.getParameter("oid_Setor_Produto");

        ProdutoED ed = new ProdutoED();
        if (util.doValida(oid_Pessoa))
            ed.setOid_Pessoa(oid_Pessoa);
        if (util.doValida(oid_Pessoa_Distribuidor))
            ed.setOid_Pessoa_Distribuidor(oid_Pessoa_Distribuidor);
        if (util.doValida(oid_Setor_Produto))
            ed.setOid_Setor_Produto(Integer.parseInt(oid_Setor_Produto));

        return new ProdutoRN().lista(ed);
    }

    public ArrayList Produto_Lista_Filtro(HttpServletRequest request) throws Excecoes {

        String oid_Mix = request.getParameter("oid_Mix");
        String oid_Pessoa = request.getParameter("oid_Pessoa");
        String oid_Tipo_Produto = request.getParameter("oid_Tipo_Produto");
        String oid_Setor_Produto = request.getParameter("oid_Setor_Produto");
        String oid_Pessoa_Distribuidor = request.getParameter("oid_Pessoa_Distribuidor");
        String cd_Produto = request.getParameter("FT_CD_Produto");
        String NM_Produto = request.getParameter("FT_NM_Produto");
        String cd_Estrutura_Produto = request.getParameter("FT_CD_Estrututa_Produto");
        String cd_Fornecedor = request.getParameter("FT_CD_Fornecedor");

        ProdutoED ed = new ProdutoED();
        if (util.doValida(cd_Produto))
            ed.setCD_Produto(cd_Produto);
        else if (util.doValida(oid_Pessoa))
            ed.setOid_Pessoa(oid_Pessoa);
        else if (util.doValida(cd_Estrutura_Produto))
            ed.setCD_Estrutura_Produto(cd_Estrutura_Produto);

        if (util.doValida(cd_Fornecedor))
            ed.setCD_Fornecedor(cd_Fornecedor);
        if (util.doValida(NM_Produto))
            ed.setNM_Produto(NM_Produto);
        if (util.doValida(oid_Mix))
            ed.setOid_Mix(Integer.parseInt(oid_Mix));
        if (util.doValida(oid_Setor_Produto))
            ed.setOid_Setor_Produto(Integer.parseInt(oid_Setor_Produto));
        if (util.doValida(oid_Tipo_Produto))
            ed.setOID_Tipo_Produto(Integer.parseInt(oid_Tipo_Produto));
        if (util.doValida(oid_Pessoa_Distribuidor))
            ed.setOid_Pessoa_Distribuidor(oid_Pessoa_Distribuidor);

        return new ProdutoRN().lista(ed);
    }

    public ArrayList listaProdutoCliente(HttpServletRequest request) throws Excecoes {

        String oid_Mix = request.getParameter("oid_Mix");
        String oid_Pessoa = request.getParameter("oid_Pessoa");
        String oid_Pessoa_Distribuidor = request.getParameter("oid_Pessoa_Distribuidor");
        String oid_Setor_Produto = request.getParameter("oid_Setor_Produto");
        String cd_Produto = request.getParameter("FT_CD_Produto");
        String nm_Produto = request.getParameter("FT_NM_Produto");
        String cd_Estrutura_Produto = request.getParameter("FT_CD_Estrututa_Produto");
        String NOT_DM_Situacao = request.getParameter("FT_NOT_DM_Situacao");// Situação q não deve listar

        ProdutoED ed = new ProdutoED();
        if (util.doValida(cd_Produto))
            ed.setCD_Produto(cd_Produto);
        else if (util.doValida(oid_Pessoa))
            ed.setOid_Pessoa(oid_Pessoa);
        else if (util.doValida(oid_Pessoa_Distribuidor))
            ed.setOid_Pessoa_Distribuidor(oid_Pessoa_Distribuidor);
        else if (util.doValida(cd_Estrutura_Produto))
            ed.setCD_Estrutura_Produto(cd_Estrutura_Produto);
        else if (util.doValida(oid_Mix))
            ed.setOid_Mix(Integer.parseInt(oid_Mix));
        if (util.doValida(oid_Setor_Produto))
            ed.setOid_Setor_Produto(Integer.parseInt(oid_Setor_Produto));
        if (util.doValida(NOT_DM_Situacao))
            ed.setNOT_DM_Situacao(NOT_DM_Situacao);
        if (util.doValida(nm_Produto))
            ed.setNM_Produto(nm_Produto);

        return new ProdutoRN().listaProdutoCliente(ed);
    }

    public ArrayList listaDistribuidoras(HttpServletRequest request) throws Excecoes {

        String oid_Produto = request.getParameter("oid_Produto");
        String cd_Produto = request.getParameter("FT_CD_Produto");
        String nm_Produto = request.getParameter("FT_NM_Produto");

        ProdutoED ed = new ProdutoED();
        if (util.doValida(oid_Produto))
            ed.setOID_Produto(oid_Produto);
        if (util.doValida(cd_Produto))
            ed.setCD_Produto(cd_Produto);
        if (util.doValida(nm_Produto))
            ed.setNM_Produto(nm_Produto);

        return new ProdutoRN().listaDistribuidoras(ed);
    }

    //*** Produtos relacionados a tabela de preco de venda [usado em Pedido de Venda]
    public ArrayList listaProdutosVenda(HttpServletRequest request) throws Excecoes, Exception {

        String oid_Vendedor = request.getParameter("oid_Vendedor");
        String oid_Pessoa_Distribuidor = request.getParameter("oid_Pessoa_Distribuidor");
        String oid_Pessoa_Cliente = request.getParameter("oid_Pessoa_Cliente");
        String NM_Produto = request.getParameter("FT_NM_Produto");
        String oid_Tipo_Estoque = request.getParameter("oid_Tipo_Estoque");
        String oid_Tabela_Venda = request.getParameter("oid_Tabela_Venda");

        if (!util.doValida(oid_Vendedor))
            throw new Mensagens("ID Vendedor não informado!");
        if (!util.doValida(oid_Pessoa_Distribuidor))
            throw new Mensagens("ID Distribuidor não informado!");
        if (!util.doValida(oid_Pessoa_Cliente))
            throw new Mensagens("ID Cliente não informado!");

        ProdutoED ed = new ProdutoED();
        ed.setOid_Pessoa(oid_Vendedor);
        ed.setOid_Pessoa_Cliente(oid_Pessoa_Cliente);
        ed.setOid_Pessoa_Distribuidor(oid_Pessoa_Distribuidor);
        if (util.doValida(NM_Produto))
            ed.setNM_Produto(NM_Produto);
        if (util.doValida(oid_Tipo_Estoque))
            ed.setOid_Tipo_Estoque(Integer.parseInt(oid_Tipo_Estoque));
        if (util.doValida(oid_Tabela_Venda))
            ed.edPreco.setOid_Tabela_Venda(Integer.parseInt(oid_Tabela_Venda));

        return new ProdutoRN().listaProdutosVenda(ed);
    }

    //*** Produtos relacionados a tabela de preco de venda [usado em Pedido de Venda]
    public ProdutoED getPrecoTabelaByOID(HttpServletRequest request) throws Excecoes {

        String oid_Produto = request.getParameter("oid_Produto");
        String oid_Vendedor = request.getParameter("oid_Vendedor");
        String oid_Pessoa_Distribuidor = request.getParameter("oid_Pessoa_Distribuidor");
        String oid_Pessoa_Cliente = request.getParameter("oid_Pessoa_Cliente");
        String oid_Tipo_Estoque = request.getParameter("oid_Tipo_Estoque");
        String oid_Localizacao = request.getParameter("oid_Localizacao");
        String oid_Tabela_Venda = request.getParameter("oid_Tabela_Venda");
        if (!util.doValida(oid_Pessoa_Cliente))
            oid_Pessoa_Cliente = request.getParameter("oid_Pessoa");

        if (!util.doValida(oid_Produto))
            throw new Mensagens("ID Produto não informado!");
        if (!util.doValida(oid_Vendedor))
            throw new Mensagens("ID Vendedor não informado!");
        if (!util.doValida(oid_Pessoa_Distribuidor))
            throw new Mensagens("ID Distribuidor não informado!");
        if (!util.doValida(oid_Pessoa_Cliente))
            throw new Mensagens("ID Cliente não informado!");

        ProdutoED ed = new ProdutoED(oid_Produto);
        ed.setOid_Pessoa(oid_Vendedor);
        ed.setOid_Pessoa_Cliente(oid_Pessoa_Cliente);
        ed.setOid_Pessoa_Distribuidor(oid_Pessoa_Distribuidor);
        if (util.doValida(oid_Tipo_Estoque))
            ed.setOid_Tipo_Estoque(Integer.parseInt(oid_Tipo_Estoque));
        if (util.doValida(oid_Localizacao))
            ed.setOid_Localizacao(Integer.parseInt(oid_Localizacao));
        if (util.doValida(oid_Tabela_Venda))
            ed.edPreco.setOid_Tabela_Venda(Integer.parseInt(oid_Tabela_Venda));

        return new ProdutoRN().getPrecoTabelaByProduto(ed);
    }
    //*** Produtos relacionados a tabela de preco de venda [usado em Pedido de Venda]
    public ProdutoED getPrecoTabelaByCDProduto(HttpServletRequest request) throws Excecoes {

        String CD_Produto = request.getParameter("FT_CD_Produto");
        String oid_Vendedor = request.getParameter("oid_Vendedor");
        String oid_Pessoa_Distribuidor = request.getParameter("oid_Pessoa_Distribuidor");
        String oid_Pessoa_Cliente = request.getParameter("oid_Pessoa_Cliente");
        String oid_Tipo_Estoque = request.getParameter("oid_Tipo_Estoque");
        String oid_Localizacao = request.getParameter("oid_Localizacao");
        String oid_Tabela_Venda = request.getParameter("oid_Tabela_Venda");
        if (!util.doValida(oid_Pessoa_Cliente))
            oid_Pessoa_Cliente = request.getParameter("oid_Pessoa");

        if (!util.doValida(CD_Produto))
            throw new Mensagens("Código do Produto não informado!");
        if (!util.doValida(oid_Vendedor))
            throw new Mensagens("ID Vendedor não informado!");
        if (!util.doValida(oid_Pessoa_Distribuidor))
            throw new Mensagens("ID Distribuidor não informado!");
        if (!util.doValida(oid_Pessoa_Cliente))
            throw new Mensagens("ID Cliente não informado!");

        ProdutoED ed = new ProdutoED();
        ed.setCD_Produto(CD_Produto);
        ed.setOid_Pessoa(oid_Vendedor);
        ed.setOid_Pessoa_Cliente(oid_Pessoa_Cliente);
        ed.setOid_Pessoa_Distribuidor(oid_Pessoa_Distribuidor);
        if (util.doValida(oid_Tipo_Estoque))
            ed.setOid_Tipo_Estoque(Integer.parseInt(oid_Tipo_Estoque));
        if (util.doValida(oid_Localizacao))
            ed.setOid_Localizacao(Integer.parseInt(oid_Localizacao));
        if (util.doValida(oid_Tabela_Venda))
            ed.edPreco.setOid_Tabela_Venda(Integer.parseInt(oid_Tabela_Venda));

        return new ProdutoRN().getPrecoTabelaByProduto(ed);
    }
    //*** Produtos relacionados a tabela de preco de venda [usado em Pedido de Venda]
    public ProdutoED getPrecoTabelaByCDFornecedor(HttpServletRequest request) throws Excecoes {

        String CD_Fornecedor = request.getParameter("FT_CD_Fornecedor");
        String oid_Vendedor = request.getParameter("oid_Vendedor");
        String oid_Pessoa_Distribuidor = request.getParameter("oid_Pessoa_Distribuidor");
        String oid_Pessoa_Cliente = request.getParameter("oid_Pessoa_Cliente");
        String oid_Tipo_Estoque = request.getParameter("oid_Tipo_Estoque");
        String oid_Localizacao = request.getParameter("oid_Localizacao");
        String oid_Tabela_Venda = request.getParameter("oid_Tabela_Venda");
        if (!util.doValida(oid_Pessoa_Cliente))
            oid_Pessoa_Cliente = request.getParameter("oid_Pessoa");

        if (!util.doValida(CD_Fornecedor))
            throw new Mensagens("Código do Produto não informado!");
        if (!util.doValida(oid_Vendedor))
            throw new Mensagens("ID Vendedor não informado!");
        if (!util.doValida(oid_Pessoa_Distribuidor))
            throw new Mensagens("ID Distribuidor não informado!");
        if (!util.doValida(oid_Pessoa_Cliente))
            throw new Mensagens("ID Cliente não informado!");

        ProdutoED ed = new ProdutoED();
        ed.setCD_Fornecedor(CD_Fornecedor);
        ed.setOid_Pessoa(oid_Vendedor);
        ed.setOid_Pessoa_Cliente(oid_Pessoa_Cliente);
        ed.setOid_Pessoa_Distribuidor(oid_Pessoa_Distribuidor);
        if (util.doValida(oid_Tipo_Estoque))
            ed.setOid_Tipo_Estoque(Integer.parseInt(oid_Tipo_Estoque));
        if (util.doValida(oid_Localizacao))
            ed.setOid_Localizacao(Integer.parseInt(oid_Localizacao));
        if (util.doValida(oid_Tabela_Venda))
            ed.edPreco.setOid_Tabela_Venda(Integer.parseInt(oid_Tabela_Venda));

        return new ProdutoRN().getPrecoTabelaByProduto(ed);
    }

    /** ------------------------------------------------------------------------ */
  	//*** Produtos Para Tabelas de Vendas
  	//    Busca todos produtos ainda NÃO RELACIONADOS a TABELA Passada
    public ArrayList listaProdutoSemTabela(HttpServletRequest request) throws Excecoes {

        String oid_Tabela_Venda = request.getParameter("oid_Tabela_Venda");
        String oid_Pessoa_Distribuidor = request.getParameter("oid_Pessoa_Distribuidor"); // Unidade
        String oid_Produto = request.getParameter("oid_Produto");

        String oid_Mix = request.getParameter("oid_Mix");
		String oid_Pessoa_Fornecedor = request.getParameter("oid_Pessoa_Fornecedor");
    	String oid_Estrutura_Produto = request.getParameter("oid_Estrutura_Produto");

    	if (!util.doValida(oid_Tabela_Venda))
            throw new Mensagens("Tabela de Venda não informada!");
        if (!util.doValida(oid_Pessoa_Distribuidor))
            throw new Mensagens("Unidade não informada!");

        ProdutoED ed = new ProdutoED();
        ed.setPaginacao(request);
        ed.setOid_Pessoa_Distribuidor(oid_Pessoa_Distribuidor);
        ed.edPreco.setOid_Tabela_Venda(Integer.parseInt(oid_Tabela_Venda));
        if (util.doValida(oid_Produto))
            ed.setOID_Produto(oid_Produto);
        if (util.doValida(oid_Mix))
            ed.setOid_Mix(Integer.parseInt(oid_Mix));
        if (util.doValida(oid_Pessoa_Fornecedor))
            ed.setOid_Pessoa(oid_Pessoa_Fornecedor);
        if (util.doValida(oid_Estrutura_Produto))
            ed.setOid_Estrutura_Produto(Integer.parseInt(oid_Estrutura_Produto));
        return new ProdutoRN().listaProdutoSemTabela(ed);
    }
    public ProdutoED getByOIDProdutoSemTabela(HttpServletRequest request) throws Excecoes {

        String oid_Tabela_Venda = request.getParameter("oid_Tabela_Venda");
        String oid_Pessoa_Distribuidor = request.getParameter("oid_Pessoa_Distribuidor"); // Unidade
        String oid_Produto = request.getParameter("oid_Produto");

        if (!util.doValida(oid_Tabela_Venda))
            throw new Mensagens("Tabela de Venda não informada!");
        if (!util.doValida(oid_Pessoa_Distribuidor))
            throw new Mensagens("Unidade não informada!");
        if (!util.doValida(oid_Produto))
            throw new Mensagens("Código Produto não informado!");

        ProdutoED ed = new ProdutoED(oid_Produto);
        ed.setOid_Pessoa_Distribuidor(oid_Pessoa_Distribuidor);
        ed.edPreco.setOid_Tabela_Venda(Integer.parseInt(oid_Tabela_Venda));
        return new ProdutoRN().getProdutoSemTabela(ed);
    }
    public ProdutoED getByCDProdutoSemTabela(HttpServletRequest request) throws Excecoes {

        String oid_Tabela_Venda = request.getParameter("oid_Tabela_Venda");
        String oid_Pessoa_Distribuidor = request.getParameter("oid_Pessoa_Distribuidor"); // Unidade
        String CD_Produto = request.getParameter("FT_CD_Produto");

        if (!util.doValida(oid_Tabela_Venda))
            throw new Mensagens("Tabela de Venda não informada!");
        if (!util.doValida(oid_Pessoa_Distribuidor))
            throw new Mensagens("Unidade não informada!");
        if (!util.doValida(CD_Produto))
            throw new Mensagens("Código Produto não informado!");

        ProdutoED ed = new ProdutoED();
        ed.edPreco.setOid_Tabela_Venda(Integer.parseInt(oid_Tabela_Venda));
        ed.setOid_Pessoa_Distribuidor(oid_Pessoa_Distribuidor);
        ed.setCD_Produto(CD_Produto);
        return new ProdutoRN().getProdutoSemTabela(ed);
    }
  	//*** Produtos Tabelas de Vendas
  	//    Busca todos produtos RELACIONADOS a TABELAS DE VENDA
    public ArrayList listaProdutoTabela(HttpServletRequest request) throws Excecoes {

        String oid_Tabela_Venda = request.getParameter("oid_Tabela_Venda");
        String oid_Pessoa_Distribuidor = request.getParameter("oid_Pessoa_Distribuidor"); // Unidade
        String oid_Produto = request.getParameter("oid_Produto");

        String oid_Mix = request.getParameter("oid_Mix");
		String oid_Pessoa_Fornecedor = request.getParameter("oid_Pessoa_Fornecedor");
    	String oid_Estrutura_Produto = request.getParameter("oid_Estrutura_Produto");

        if (!util.doValida(oid_Pessoa_Distribuidor))
            throw new Mensagens("Unidade não informada!");
        if (!util.doValida(oid_Tabela_Venda))
            throw new Mensagens("Tabela de Venda não informada!");

        ProdutoED ed = new ProdutoED();
        //*** Informa Paginação
        ed.setPaginacao(request);
        ed.setOid_Pessoa_Distribuidor(oid_Pessoa_Distribuidor);
        ed.edPreco.setOid_Tabela_Venda(Integer.parseInt(oid_Tabela_Venda));

        if (util.doValida(oid_Produto))
            ed.setOID_Produto(oid_Produto);
        if (util.doValida(oid_Mix))
            ed.setOid_Mix(Integer.parseInt(oid_Mix));
        if (util.doValida(oid_Pessoa_Fornecedor))
            ed.setOid_Pessoa(oid_Pessoa_Fornecedor);
        if (util.doValida(oid_Estrutura_Produto))
            ed.setOid_Estrutura_Produto(Integer.parseInt(oid_Estrutura_Produto));

        return new ProdutoRN().listaProdutoTabela(ed);
    }
    /**---- Usado na Paginação*/
    public int countProdutosTabela(String oid_Pessoa_Distribuidor, int oid_Tabela_Venda) throws Excecoes {
        return util.getTableIntValue("count(*)",
                                     "Produtos_Clientes" +
                                     "      INNER JOIN Precos_Produtos" +
                                     "         ON Produtos_Clientes.oid_Produto_Cliente = Precos_Produtos.oid_Produto_Cliente",
                                     "Produtos_Clientes.oid_Pessoa = '"+oid_Pessoa_Distribuidor+"'" +
                                     "   AND Precos_Produtos.oid_Tabela_Venda = "+oid_Tabela_Venda);
    }
    public int countProdutosSemTabela(String oid_Pessoa_Distribuidor, int oid_Tabela_Venda) throws Excecoes {
        return util.getTableIntValue("count(*)",
                                     "Produtos_Clientes" +
                                     "      LEFT JOIN Precos_Produtos" +
                                     "         ON Produtos_Clientes.oid_Produto_Cliente = Precos_Produtos.oid_Produto_Cliente" +
                                     "         AND Precos_Produtos.oid_Tabela_Venda = "+oid_Tabela_Venda,
                                     "Produtos_Clientes.oid_Pessoa = '"+oid_Pessoa_Distribuidor+"'" +
                                     "   AND Precos_Produtos.oid_Preco_Produto IS NULL");
    }


    //*** Lista Tabelas Relacionadas a um PRODUTO CLIENTE
    public ArrayList listaProdutoClienteTabelas(HttpServletRequest request) throws Excecoes {

        String oid_Pessoa_Distribuidor = request.getParameter("oid_Pessoa_Distribuidor"); // Unidade
        String oid_Produto_Cliente = request.getParameter("oid_Produto_Cliente");

        if (!util.doValida(oid_Pessoa_Distribuidor))
            throw new Mensagens("Unidade não informada!");
        if (!util.doValida(oid_Produto_Cliente))
            throw new Mensagens("Produto Cliente não informado!");

        ProdutoED ed = new ProdutoED();
        ed.setOid_Pessoa_Distribuidor(oid_Pessoa_Distribuidor);
        ed.setOID_Produto_Cliente(oid_Produto_Cliente);

        ArrayList lista = new ProdutoRN().listaProdutoTabela(ed);
        Collections.sort(lista, new Comparator() {

            public int compare(Object o1, Object o2) {
                return ((ProdutoED) o2).edPreco.edTabela.getDT_Validade().compareTo(((ProdutoED) o1).edPreco.edTabela.getDT_Validade());
            }
        });
        Collections.sort(lista, new Comparator() {

            public int compare(Object o1, Object o2) {
                return String.valueOf(((ProdutoED) o1).edPreco.edTabela.edTipo_Tabela.getCD_Tipo_Tabela_Venda()).compareTo(String.valueOf(((ProdutoED) o2).edPreco.edTabela.edTipo_Tabela.getCD_Tipo_Tabela_Venda()));
            }
        });
        return lista;
    }
    public ProdutoED getByOIDProdutoTabela(HttpServletRequest request) throws Excecoes {

        String oid_Tabela_Venda = request.getParameter("oid_Tabela_Venda");
        String oid_Pessoa_Distribuidor = request.getParameter("oid_Pessoa_Distribuidor"); // Unidade
        String oid_Produto_Cliente = request.getParameter("oid_Produto_Cliente");
        String oid_Produto = request.getParameter("oid_Produto");

        if (!util.doValida(oid_Pessoa_Distribuidor))
            throw new Mensagens("Unidade não informada!");
        if (!util.doValida(oid_Tabela_Venda))
            throw new Mensagens("Tabela de Venda não informada!");
        if (!util.doValida(oid_Produto) && !util.doValida(oid_Produto_Cliente))
            throw new Mensagens("Código Produto não informado!");

        ProdutoED ed = new ProdutoED(oid_Produto);
        if (util.doValida(oid_Produto))
            ed.setOID_Produto(oid_Produto);
        if (util.doValida(oid_Produto_Cliente))
            ed.setOID_Produto_Cliente(oid_Produto_Cliente);
        ed.setOid_Pessoa_Distribuidor(oid_Pessoa_Distribuidor);
        ed.edPreco.setOid_Tabela_Venda(Integer.parseInt(oid_Tabela_Venda));

        return new ProdutoRN().getProdutoTabela(ed);
    }
    public ProdutoED getByCDProdutoTabela(HttpServletRequest request) throws Excecoes {

        String oid_Tabela_Venda = request.getParameter("oid_Tabela_Venda");
        String oid_Pessoa_Distribuidor = request.getParameter("oid_Pessoa_Distribuidor"); // Unidade
        String CD_Produto = request.getParameter("FT_CD_Produto");

        if (!util.doValida(oid_Pessoa_Distribuidor))
            throw new Mensagens("Unidade não informada!");
        if (!util.doValida(oid_Tabela_Venda))
            throw new Mensagens("Tabela de Venda não informada!");
        if (!util.doValida(CD_Produto))
            throw new Mensagens("Código Produto não informado!");

        ProdutoED ed = new ProdutoED();
        ed.setCD_Produto(CD_Produto);
        ed.setOid_Pessoa_Distribuidor(oid_Pessoa_Distribuidor);
        ed.edPreco.setOid_Tabela_Venda(Integer.parseInt(oid_Tabela_Venda));

        return new ProdutoRN().getProdutoTabela(ed);
    }

    //*** Verifica se produto é para PESAGEM
    public boolean isPesagemByProduto(String oid_Produto) throws Excecoes {

        try {
            return util.doValida(oid_Produto) && "S".equals(util.getTableStringValue("DM_Pesagem",
                    													   "Unidades_Produtos, Produtos",
                    													   "Unidades_Produtos.oid_Unidade_Produto = Produtos.oid_Unidade_Produto" +
                    													   " AND Produtos.oid_produto = '" + oid_Produto + "'"));
        } catch (Mensagens e) {
            throw e;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "getQtdEstoque()");
        }
    }

    //*** Número de Caixas por Quantidade...
    public static String getQtdEmCaixas(double nrQuantidade, int nrQtCaixa) {

        if (nrQtCaixa > 0) {
            long caixas = Math.round(nrQuantidade) / nrQtCaixa;
            long resto = Math.round(nrQuantidade % nrQtCaixa);
            return resto > 0 ? String.valueOf(caixas+ "+ "+resto) : String.valueOf(caixas);
        } else return "";//String.valueOf("0+ "+nrQuantidade);
    }
    public static int getQtdEmCaixasToInt(String nrQtCaixas) {

        int toReturn = 0;
        try {
            toReturn = Integer.parseInt(nrQtCaixas);
        } catch (NumberFormatException n) {
            try {
                int index = 0;
                if ((index = nrQtCaixas.indexOf("+")) != -1)
                    toReturn = Integer.parseInt(nrQtCaixas.substring(0, index));
            } catch (NumberFormatException n2) {}
        }
        return toReturn;
    }

    //*** RELATÓRIOS
    //Produtos Modelo 1
    public void RelProdutos(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String Filter = "";
        String oid_Mix = request.getParameter("oid_Mix");
        String oid_Pessoa = request.getParameter("oid_Pessoa");
        String oid_Setor_Produto = request.getParameter("oid_Setor_Produto");
        String oid_Produto = request.getParameter("oid_Produto");
        String oid_Estrutura_Produto = request.getParameter("oid_Estrututa_Produto");
        String Layout = request.getParameter("FT_Layout");

        ProdutoED ed = new ProdutoED();
        if (util.doValida(oid_Produto)) {
            ed.setOID_Produto(oid_Produto);
            Filter += ProdutoRelED.FILTER_PRODUTO;
        }
        if (util.doValida(oid_Pessoa)) {
            ed.setOid_Pessoa(oid_Pessoa);
            Filter += ProdutoRelED.FILTER_FORNECEDOR;
        }
        if (util.doValida(oid_Estrutura_Produto)) {
            ed.setOid_Estrutura_Produto(Integer.parseInt(oid_Estrutura_Produto));
            Filter += ProdutoRelED.FILTER_ESTRUTURA;
        }
        if (util.doValida(oid_Mix)) {
            ed.setOid_Mix(Integer.parseInt(oid_Mix));
            Filter += ProdutoRelED.FILTER_MIX;
        }
        if (util.doValida(oid_Setor_Produto)) {
            ed.setOid_Setor_Produto(Integer.parseInt(oid_Setor_Produto));
            Filter += ProdutoRelED.FILTER_SETOR;
        }

        if (util.doValida(Layout))
            new ProdutoRN().RelProdutos(response, ed, Layout, Filter);
        else throw new Excecoes("Layout não informado!");
    } //Produtos Modelo

    //*** Produtos Estoques
    public void RelProdutosEstoque(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String oid_Produto = request.getParameter("oid_Produto");
        String oid_Pessoa = request.getParameter("oid_Pessoa");
        String oid_Pessoa_Distribuidor = request.getParameter("oid_Pessoa_Distribuidor");
        String oid_Deposito = request.getParameter("oid_Deposito");
        String oid_Tipo_Estoque = request.getParameter("oid_Tipo_Estoque");
        String Layout = request.getParameter("FT_Layout");

        RelatorioED ed = new RelatorioED();
        if (util.doValida(oid_Produto)) {
            ed.setOid_produto(Integer.parseInt(oid_Produto));
        }
        if (util.doValida(oid_Pessoa)) {
            ed.setOid_pessoa(oid_Pessoa);
        }
        if (util.doValida(oid_Pessoa_Distribuidor)) {
            ed.setOid_pessoa_distribuidor(oid_Pessoa_Distribuidor);
        }
        if (util.doValida(oid_Deposito)) {
            ed.setOid_deposito(Integer.parseInt(oid_Deposito));
        }
        if (util.doValida(oid_Tipo_Estoque)) {
            ed.setOid_tipo_estoque(Integer.parseInt(oid_Tipo_Estoque));
        }
        if (util.doValida(Layout)) {
            ed.setLayout(Layout);
        }

        new ProdutoRN().RelProdutosEstoque(response, ed);
    }// Produtos Estoques

    //*** Produtos Comprados
    public void RelProdutosComprados(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String oid_Produto = request.getParameter("oid_Produto");
        String oid_Pessoa = request.getParameter("oid_Pessoa");
        String oid_Pessoa_Distribuidor = request.getParameter("oid_Pessoa_Distribuidor");
        String DT_Emissao_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
        String DT_Emissao_Final = request.getParameter("FT_DT_Emissao_Final");
        String DT_Entrada_Inicial = request.getParameter("FT_DT_Entrada_Inicial");
        String DT_Entrada_Final = request.getParameter("FT_DT_Entrada_Final");
        String Layout = request.getParameter("FT_Layout");

        RelatorioED ed = new RelatorioED();
        if (util.doValida(oid_Produto)) {
            ed.setOid_produto(Integer.parseInt(oid_Produto));
        }
        if (util.doValida(oid_Pessoa)) {
            ed.setOid_pessoa(oid_Pessoa);
        }
        if (util.doValida(oid_Pessoa_Distribuidor)) {
            ed.setOid_pessoa_distribuidor(oid_Pessoa_Distribuidor);
        }
        if (util.doValida(DT_Emissao_Inicial) && util.doValida(DT_Emissao_Final)) {
            ed.setDt_emissao_inicial(DT_Emissao_Inicial);
            ed.setDt_emissao_final(DT_Emissao_Final);
        }
        if (util.doValida(DT_Entrada_Inicial) && util.doValida(DT_Entrada_Final)) {
            ed.setDt_entrada_inicial(DT_Entrada_Inicial);
            ed.setDt_entrada_final(DT_Entrada_Final);
        }
        if (util.doValida(Layout)) {
            ed.setLayout(Layout);
        }

        new ProdutoRN().RelProdutosComprados(response, ed);
    }// Produtos Comprados

    public void relEstruturasProduto(HttpServletRequest request, HttpServletResponse response) throws Excecoes {

        String oid_Pessoa_Distribuidor = request.getParameter("oid_Pessoa_Distribuidor");
        String oid_Estrutura_Produto = request.getParameter("oid_Estrutura_Produto");
        String oid_Tipo_Estoque = request.getParameter("oid_Tipo_Estoque");
        String oid_Pessoa = request.getParameter("oid_Pessoa");// Fornecedor
        String DM_Tipo_Estrutura = request.getParameter("FT_DM_Tipo_Estrutura"); // P="Produto"/F="Fornecedor"
        String Relatorio = request.getParameter("Relatorio");

        if (!util.doValida(Relatorio))
            throw new Mensagens("Nome do Relatório não informado!");
        if (!util.doValida(oid_Pessoa_Distribuidor))
            throw new Mensagens("Cliente não informado!");
        if (!util.doValida(DM_Tipo_Estrutura))
            throw new Mensagens("Tipo de Estrutura não informada!");

        RelatorioED ed = new RelatorioED(response, Relatorio);
        ed.setProduto("P".equals(DM_Tipo_Estrutura));
        if (util.doValida(oid_Pessoa_Distribuidor))
            ed.setOid_pessoa_distribuidor(oid_Pessoa_Distribuidor);
        if (util.doValida(oid_Pessoa))
            ed.setOid_pessoa(oid_Pessoa);
        if (util.doValida(oid_Tipo_Estoque))
            ed.setOid_tipo_estoque(Integer.parseInt(oid_Tipo_Estoque));
        if (util.doValida(oid_Estrutura_Produto)) {
            if (ed.isProduto())
                ed.setOid_estrutura_produto(Integer.parseInt(oid_Estrutura_Produto));
            else ed.setOid_estrutura_fornecedor(Integer.parseInt(oid_Estrutura_Produto));
        }
        new ProdutoRN().relEstruturasProduto(ed);
    }

    //*** XSL
    public byte[] geraRelatorioItemXLocalizacao(HttpServletRequest req, HttpServletResponse Response) throws Excecoes {
        ProdutoED ed = new ProdutoED();

        ed.setOid_Pessoa(req.getParameter("oid_Pessoa"));
        ed.setOID_Produto(req.getParameter("FT_OID_Produto"));
        String deposito = req.getParameter("FT_OID_Deposito");
        if (util.doValida(deposito)){
        	ed.setOID_Deposito(Integer.valueOf(deposito).intValue());
        } else {
        	deposito = req.getParameter("oid_Deposito");
            if (util.doValida(deposito)){
            	ed.setOID_Deposito(Integer.valueOf(deposito).intValue());
            }
        }

        ProdutoRN geRN = new ProdutoRN();
        return geRN.geraRelatorioItemXLocalizacao(ed);
    }
}