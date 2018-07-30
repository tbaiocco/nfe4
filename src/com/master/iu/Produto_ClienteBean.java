package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.Produto_ClienteED;
import com.master.ed.Produto_ClienteRelED;
import com.master.rn.Produto_ClienteRN;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;

public class Produto_ClienteBean extends JavaUtil {

    private ArrayList lista = null;
    private ArrayList listaRastreamento = null;

    public ArrayList listaRastreamento(String oid_produto_cliente, String orderby) throws Excecoes {

        Produto_ClienteRN RN = new Produto_ClienteRN();
        this.setListaRastreamento(RN.listaRastreamento(oid_produto_cliente, orderby));
        return this.getListaRastreamento();
    }

    public void setListaRastreamento(ArrayList array) {
        this.listaRastreamento = array;
    }

    public ArrayList getListaRastreamento() {
        return this.listaRastreamento;
    }

    public Produto_ClienteED inclui(HttpServletRequest request) throws Excecoes {

        Produto_ClienteED ed = new Produto_ClienteED();
        ed.setOID_Pessoa(getValueDef(request.getParameter("oid_Pessoa"),request.getParameter("oid_Pessoa_Distribuidor")));
        if (!doValida(ed.getOID_Pessoa()))
            throw new Mensagens("Pessoa Distribuidora não informada!");

        ed.setOID_Embalagem(new Integer(request.getParameter("FT_OID_Embalagem")).intValue());
        ed.setDM_Rotatividade(request.getParameter("FT_DM_Rotatividade"));
        ed.setNM_Referencia(request.getParameter("FT_NM_Referencia"));
        ed.setDM_Serie(request.getParameter("FT_DM_Serie"));
        
        ed.setDM_Situacao(request.getParameter("FT_DM_Situacao"));
        ed.setDM_Validade(request.getParameter("FT_DM_Validade"));

        String OID_Produto = request.getParameter("FT_OID_Produto");
        if (doValida(OID_Produto))
            ed.setOID_Produto(new Integer(request.getParameter("FT_OID_Produto")).intValue());

        OID_Produto = request.getParameter("oid_Produto");
        if (doValida(OID_Produto))
            ed.setOID_Produto(new Integer(request.getParameter("oid_Produto")).intValue());

        String NR_Peso = request.getParameter("FT_NR_Peso");
        if (doValida(NR_Peso))
            ed.setNR_Peso(new Double(request.getParameter("FT_NR_Peso")).doubleValue());

        String NR_Peso_Liquido = request.getParameter("FT_NR_Peso_Liquido");
        if (doValida(NR_Peso_Liquido))
            ed.setNR_Peso_Liquido(new Double(request.getParameter("FT_NR_Peso_Liquido")).doubleValue());
        
        String NR_Peso_Bruto = request.getParameter("FT_NR_Peso_Bruto");
        if (doValida(NR_Peso_Bruto))
            ed.setNR_Peso_Bruto(new Double(request.getParameter("FT_NR_Peso_Bruto")).doubleValue());
        
        String NR_Altura = request.getParameter("FT_NR_Altura");
        if (doValida(NR_Altura))
            ed.setNr_Altura(new Double(request.getParameter("FT_NR_Altura")).doubleValue());

        String VL_Preco_Custo = request.getParameter("FT_VL_Preco_Custo");
        if (doValida(VL_Preco_Custo))
            ed.setVL_Preco_Custo(new Double(request.getParameter("FT_VL_Preco_Custo")).doubleValue());

        String VL_Markup = request.getParameter("FT_VL_Markup");
        if (doValida(VL_Markup))
            ed.setVL_Markup(new Double(request.getParameter("FT_VL_Markup")).doubleValue());
        
        String PE_Desconto_Avista = request.getParameter("FT_PE_Desconto_Avista");
        String PE_Desconto_7_Dias = request.getParameter("FT_PE_Desconto_7_Dias");
        String PE_Acrescimo_21_Dias = request.getParameter("FT_PE_Acrescimo_21_Dias");
        String PE_Acrescimo_28_Dias = request.getParameter("FT_PE_Acrescimo_28_Dias");
        if (doValida(PE_Desconto_Avista))
            ed.setPE_Desconto_Avista(Double.parseDouble(PE_Desconto_Avista));
        if (doValida(PE_Desconto_7_Dias))
            ed.setPE_Desconto_7_Dias(Double.parseDouble(PE_Desconto_7_Dias));
        if (doValida(PE_Acrescimo_21_Dias))
            ed.setPE_Acrescimo_21_Dias(Double.parseDouble(PE_Acrescimo_21_Dias));
        if (doValida(PE_Acrescimo_28_Dias))
            ed.setPE_Acrescimo_28_Dias(Double.parseDouble(PE_Acrescimo_28_Dias));

        ed.setNR_Quantidade(Double.parseDouble(request.getParameter("FT_NR_Quantidade")));
        ed.setOID_Tipo_Estoque(Integer.valueOf(request.getParameter("oid_Tipo_Estoque")).intValue());

        String PE_Comissao = request.getParameter("FT_PE_Comissao");
        if (doValida(PE_Comissao))
            ed.setPE_Comissao(Double.parseDouble(PE_Comissao));

        String NR_QT_Minimo = request.getParameter("FT_NR_QT_Minimo");
        if (doValida(NR_QT_Minimo))
            ed.setNR_QT_Minimo(new Double(request.getParameter("FT_NR_QT_Minimo")).doubleValue());

        String NR_Largura = request.getParameter("FT_NR_Largura");
        if (doValida(NR_Largura))
            ed.setNr_Largura(new Double(request.getParameter("FT_NR_Largura")).doubleValue());

        String NR_Profundidade = request.getParameter("FT_NR_Profundidade");
        if (doValida(NR_Profundidade))
            ed.setNr_Profundidade(new Double(request.getParameter("FT_NR_Profundidade")).doubleValue());

        String NR_Empilhagem = request.getParameter("FT_NR_Empilhagem");
        if (doValida(NR_Empilhagem))
            ed.setNr_Empilhagem(new Integer(request.getParameter("FT_NR_Empilhagem")).intValue());

        String DM_Situacao = request.getParameter("FT_DM_Situacao");
        if (doValida(DM_Situacao))
            ed.setDM_Situacao(request.getParameter("FT_DM_Situacao"));
        else ed.setDM_Situacao("");
        String DM_Validade = request.getParameter("FT_DM_Validade");
        if (doValida(DM_Validade))
            ed.setDM_Validade(request.getParameter("FT_DM_Validade"));
        else ed.setDM_Validade("");
        String DM_Rotatividade = request.getParameter("FT_DM_Rotatividade");
        if (doValida(DM_Rotatividade))
            ed.setDM_Rotatividade(request.getParameter("FT_DM_Rotatividade"));
        else ed.setDM_Rotatividade("");

        ed.setOid_Tipo_Pallet(new Integer(request.getParameter("FT_OID_Tipo_Pallet")).intValue());
        String oid_Localizacao = request.getParameter("oid_Localizacao");
        if (doValida(oid_Localizacao))
            ed.setOid_Localizacao(Integer.parseInt(oid_Localizacao));

        return new Produto_ClienteRN().inclui(ed);   
    }

    public void altera(HttpServletRequest request) throws Excecoes {

        Produto_ClienteRN Produto_ClienteRN = new Produto_ClienteRN();
        Produto_ClienteED ed = new Produto_ClienteED();

        ed.setOID_Produto_Cliente(request.getParameter("oid_Produto_Cliente"));

        ed.setOID_Embalagem(new Integer(request.getParameter("FT_OID_Embalagem")).intValue());
        ed.setDM_Rotatividade(request.getParameter("FT_DM_Rotatividade"));
        ed.setNM_Referencia(request.getParameter("FT_NM_Referencia"));
        ed.setDM_Serie(request.getParameter("FT_DM_Serie"));
        ed.setDM_Situacao(request.getParameter("FT_DM_Situacao"));
        ed.setDM_Validade(request.getParameter("FT_DM_Validade"));

        String NR_Peso = request.getParameter("FT_NR_Peso");
        if (doValida(NR_Peso))
            ed.setNR_Peso(Double.parseDouble(NR_Peso));

        String NR_Altura = request.getParameter("FT_NR_Altura");
        if (doValida(NR_Altura))
            ed.setNr_Altura(Double.parseDouble(NR_Altura));

        String VL_Preco_Custo = request.getParameter("FT_VL_Preco_Custo");
        if (doValida(VL_Preco_Custo))
            ed.setVL_Preco_Custo(Double.parseDouble(VL_Preco_Custo));

        String VL_Markup = request.getParameter("FT_VL_Markup");
        if (doValida(VL_Markup))
            ed.setVL_Markup(Double.parseDouble(VL_Markup));
        String VL_Produto = request.getParameter("FT_VL_Produto");
        if (doValida(VL_Produto))
            ed.setVL_Produto(Double.parseDouble(VL_Produto));
        
        String PE_Desconto_Avista = request.getParameter("FT_PE_Desconto_Avista");
        String PE_Desconto_7_Dias = request.getParameter("FT_PE_Desconto_7_Dias");
        String PE_Acrescimo_21_Dias = request.getParameter("FT_PE_Acrescimo_21_Dias");
        String PE_Acrescimo_28_Dias = request.getParameter("FT_PE_Acrescimo_28_Dias");
        if (doValida(PE_Desconto_Avista))
            ed.setPE_Desconto_Avista(Double.parseDouble(PE_Desconto_Avista));
        if (doValida(PE_Desconto_7_Dias))
            ed.setPE_Desconto_7_Dias(Double.parseDouble(PE_Desconto_7_Dias));
        if (doValida(PE_Acrescimo_21_Dias))
            ed.setPE_Acrescimo_21_Dias(Double.parseDouble(PE_Acrescimo_21_Dias));
        if (doValida(PE_Acrescimo_28_Dias))
            ed.setPE_Acrescimo_28_Dias(Double.parseDouble(PE_Acrescimo_28_Dias));

        String PE_Comissao = request.getParameter("FT_PE_Comissao");
        if ((PE_Comissao != null && !PE_Comissao.equals("0")) && !PE_Comissao.equals("") && !PE_Comissao.equals("null"))
            ed.setPE_Comissao(Double.parseDouble(PE_Comissao));

        String NR_QT_Minimo = request.getParameter("FT_NR_QT_Minimo");
        if (NR_QT_Minimo != null && NR_QT_Minimo.length() != 0)
            ed.setNR_QT_Minimo(new Double(request.getParameter("FT_NR_QT_Minimo")).doubleValue());

        String NR_Peso_Liquido = request.getParameter("FT_NR_Peso_Liquido");
        if (doValida(NR_Peso_Liquido))
            ed.setNR_Peso_Liquido(new Double(request.getParameter("FT_NR_Peso_Liquido")).doubleValue());
        
        String NR_Peso_Bruto = request.getParameter("FT_NR_Peso_Bruto");
        if (doValida(NR_Peso_Bruto))
            ed.setNR_Peso_Bruto(new Double(request.getParameter("FT_NR_Peso_Bruto")).doubleValue());
        
        String NR_Largura = request.getParameter("FT_NR_Largura");
        if (NR_Largura != null && NR_Largura.length() != 0)
            ed.setNr_Largura(new Double(request.getParameter("FT_NR_Largura")).doubleValue());

        String NR_Profundidade = request.getParameter("FT_NR_Profundidade");
        if (NR_Profundidade != null && NR_Profundidade.length() != 0)
            ed.setNr_Profundidade(new Double(request.getParameter("FT_NR_Profundidade")).doubleValue());

        String NR_Empilhagem = request.getParameter("FT_NR_Empilhagem");
        if (NR_Empilhagem != null && NR_Empilhagem.length() != 0)
            ed.setNr_Empilhagem(new Integer(request.getParameter("FT_NR_Empilhagem")).intValue());

        ed.setOid_Tipo_Pallet(new Integer(request.getParameter("FT_OID_Tipo_Pallet")).intValue());
        String oid_Localizacao = request.getParameter("oid_Localizacao");
        if (doValida(oid_Localizacao))
            ed.setOid_Localizacao(Integer.parseInt(oid_Localizacao));
        
        Produto_ClienteRN.altera(ed);
    }

    public void deleta(HttpServletRequest request) throws Excecoes {

        Produto_ClienteED ed = new Produto_ClienteED();

        String FT_OID_Produto_Cliente = request.getParameter("FT_OID_Produto_Cliente");
        String OID_Produto_Cliente = request.getParameter("oid_Produto_Cliente");

        if (doValida(FT_OID_Produto_Cliente))
            ed.setOID_Produto_Cliente(FT_OID_Produto_Cliente);
        else if (doValida(OID_Produto_Cliente)) {
            ed.setOID_Produto_Cliente(OID_Produto_Cliente);
        } else throw new Excecoes("ID Produto Cliente não informado!");

        new Produto_ClienteRN().deleta(ed);
    }

    public Produto_ClienteED getByRecord(HttpServletRequest request) throws Excecoes {

        Produto_ClienteED ed = new Produto_ClienteED();

        String FT_OID_Produto_Cliente = request.getParameter("FT_OID_Produto_Cliente");
        String OID_Produto_Cliente = request.getParameter("oid_Produto_Cliente");

        if (doValida(FT_OID_Produto_Cliente))
            ed.setOID_Produto_Cliente(FT_OID_Produto_Cliente);
        else if (doValida(OID_Produto_Cliente)) {
            ed.setOID_Produto_Cliente(OID_Produto_Cliente);
        }
        return new Produto_ClienteRN().getByRecord(ed);
    }

    public Produto_ClienteED getByOID_Produto_Cliente(String oid_Produto_Cliente) throws Excecoes {

        if (!doValida(oid_Produto_Cliente))
            throw new Mensagens("ID Produto Cliente não informado!");
        return new Produto_ClienteRN().getByRecord(new Produto_ClienteED(oid_Produto_Cliente));
    }

    public ArrayList lista(HttpServletRequest request, String orderby) throws Excecoes {

        Produto_ClienteRN RN = new Produto_ClienteRN();
        this.setLista(RN.lista(request, orderby));

        return this.getLista();
    }

    public void setLista(ArrayList array) {
        this.lista = array;
    }

    public ArrayList getLista() {
        return this.lista;
    }

    public ArrayList listaProdutoClienteEstoque(HttpServletRequest request) throws Excecoes {

        Produto_ClienteED ed = new Produto_ClienteED();

        String oid_Produto_Cliente = request.getParameter("oid_Produto_Cliente");
        if (oid_Produto_Cliente != null && !oid_Produto_Cliente.equals(""))
            ed.setOID_Produto_Cliente(oid_Produto_Cliente);

        return new Produto_ClienteRN().listaProdutoClienteEstoque(ed);

    }

    public ArrayList listaProdutoClienteLocal(String oid_Produto_Cliente, int oid_Tipo_Estoque) throws Excecoes {

        Produto_ClienteED ed = new Produto_ClienteED();
        ed.setOID_Produto_Cliente(oid_Produto_Cliente);
        ed.setOID_Tipo_Estoque(oid_Tipo_Estoque);
        return new Produto_ClienteRN().listaProdutoClienteLocal(ed);
    }
    
    //*** Busca OID_Localizacao Referencia do Produto_Cliente
    public int getOid_Localizacao(String oid_Produto_Cliente) throws Excecoes {

        if (!doValida(oid_Produto_Cliente))
            throw new Excecoes("ID Produto Cliente não informado!");
        return new BancoUtil().getTableIntValue("oid_Localizacao","Produtos_Clientes","oid_Produto_Cliente = '"+oid_Produto_Cliente+"'");
    }

    //*** RELATÓRIOS
    // Clientes do Vendedor
    public void RelProdutos_Cliente(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String oid_Cliente = request.getParameter("oid_Cliente");
        String oid_Pessoa = request.getParameter("oid_Pessoa");
        String NR_Pagina = request.getParameter("FT_NR_Pagina");

        String Layout = request.getParameter("FT_Layout");
        String Ordenar = request.getParameter("FT_Ordenar");

        Produto_ClienteRelED ed = new Produto_ClienteRelED();
        ed.setOid_vendedor(oid_Cliente);
        ed.setOid_pessoa(oid_Pessoa);
        ed.setNr_pagina(NR_Pagina);
        ed.setLayout(Layout);
        ed.setOrderBy(Ordenar);

        new Produto_ClienteRN().RelProdutos_Cliente(response, ed);
    }
}