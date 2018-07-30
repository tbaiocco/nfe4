package com.master.iu;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Mix_ProdutoED;
import com.master.rn.Mix_ProdutoRN;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;

/*
 * Created on 24/08/2004
 */

/**
 * @author Andre Valadas
 */
public class Mix_ProdutoBean extends JavaUtil implements Serializable {

    public Mix_ProdutoED inclui(HttpServletRequest request) throws Excecoes {

        String oid_Mix = request.getParameter("oid_Mix");
        String oid_Produto = request.getParameter("oid_Produto");
        String oid_Pessoa_Distribuidor = request.getParameter("oid_Pessoa_Distribuidor");

        //*** Validações
        if (!doValida(oid_Mix) || !doValida(oid_Pessoa_Distribuidor) || !doValida(oid_Produto))
            throw new Excecoes("Parâmetros incorretos!");

        Mix_ProdutoED ed = new Mix_ProdutoED();
        ed.setOid_Mix(new Integer(oid_Mix).intValue());
        ed.setOid_Produto(new Integer(oid_Produto).intValue());
        ed.setOid_Pessoa_Distribuidor(oid_Pessoa_Distribuidor);
        
        return new Mix_ProdutoRN().inclui(ed);
    }

    public void altera(HttpServletRequest request) throws Excecoes, SQLException {

        String oid_Mix_Produto = request.getParameter("oid_Mix_Produto");
        String oid_Mix = request.getParameter("oid_Mix");
        String oid_Produto = request.getParameter("oid_Produto");
        String oid_Pessoa_Distribuidor = request.getParameter("oid_Pessoa_Distribuidor");

        //*** Validações
        if (!doValida(oid_Mix_Produto) || !doValida(oid_Pessoa_Distribuidor) || 
            !doValida(oid_Mix) || !doValida(oid_Produto))
            throw new Excecoes("Parâmetros incorretos!");
        
        Mix_ProdutoED ed = new Mix_ProdutoED();
        ed.setOid_Mix_Produto(new Integer(oid_Mix_Produto).intValue());
        ed.setOid_Mix(new Integer(oid_Mix).intValue());
        ed.setOid_Produto(new Integer(oid_Produto).intValue());
        ed.setOid_Pessoa_Distribuidor(oid_Pessoa_Distribuidor);
        
        if (new BancoUtil().doExiste("Mix_Produtos", 
                                	 "oid_Mix = "+ed.getOid_Mix()+
                                	 " AND oid_Produto = "+ed.getOid_Produto()+
                                	 " AND oid_Pessoa_Distribuidor = '"+ed.getOid_Pessoa_Distribuidor()+"'"+
                                	 " AND oid_Mix_Produto <> "+ed.getOid_Mix_Produto()))
            throw new Mensagens("Ja existe esse mix para esse produto!");
        
        new Mix_ProdutoRN().altera(ed);
    }

    public void deleta(HttpServletRequest request) throws Excecoes, SQLException {

        String oid_Mix_Produto = request.getParameter("oid_Mix_Produto");

        //*** Validações
        if (!doValida(oid_Mix_Produto))
            throw new Excecoes("ID Mix Produto não informado!");        
        
        Mix_ProdutoED ed = new Mix_ProdutoED();
        ed.setOid_Mix_Produto(new Integer(oid_Mix_Produto).intValue());

        new Mix_ProdutoRN().deleta(ed);
    }

    public ArrayList Mix_Produto_Lista(HttpServletRequest request)
            throws Excecoes {

        String oid_Produto = request.getParameter("oid_Produto");
        String oid_Pessoa_Distribuidor = request.getParameter("oid_Pessoa_Distribuidor");

        //*** Validações
        if (!doValida(oid_Produto) || !doValida(oid_Pessoa_Distribuidor))
            throw new Excecoes("Parâmetros incorretos!");

        Mix_ProdutoED ed = new Mix_ProdutoED();
        ed.setOid_Produto(new Integer(oid_Produto).intValue());
        ed.setOid_Pessoa_Distribuidor(oid_Pessoa_Distribuidor);

        return new Mix_ProdutoRN().lista(ed);
    }

    public Mix_ProdutoED getByRecord(HttpServletRequest request)
            throws Excecoes {

        String oid_Mix_Produto = request.getParameter("oid_Mix_Produto");

        //*** Validações
        if (!doValida(oid_Mix_Produto))
            throw new Excecoes("ID Mix Produto não informado!");           

        Mix_ProdutoED ed = new Mix_ProdutoED();       
        ed.setOid_Mix_Produto(new Integer(oid_Mix_Produto).intValue());
        
        return new Mix_ProdutoRN().getByRecord(ed);
    }

    public Mix_ProdutoED getByOidMixProduto(int oid_Mix_Produto)
            throws Excecoes {

        return new Mix_ProdutoRN().getByOidMixProduto(oid_Mix_Produto);
    }

    //*** Verifica se registro já existe!
    public boolean doExiste(HttpServletRequest request) throws Excecoes {

        String oid_Mix = request.getParameter("oid_Mix");
        String oid_Produto = request.getParameter("oid_Produto");
        String oid_Pessoa_Distribuidor = request.getParameter("oid_Pessoa_Distribuidor");

        //*** Validações
        if (doValida(oid_Pessoa_Distribuidor) && doValida(oid_Mix) && doValida(oid_Produto)) {

            String strFrom = "mix_produtos";
            String strWhere = " oid_Pessoa_Distribuidor = '"
                    + oid_Pessoa_Distribuidor + "'" + " and oid_Produto = "
                    + oid_Produto + " and oid_Mix = " + oid_Mix;

            return new BancoUtil().doExiste(strFrom, strWhere);

        } else throw new Excecoes("Falta de Parâmetros para validar se registro já existe!");
    }
}