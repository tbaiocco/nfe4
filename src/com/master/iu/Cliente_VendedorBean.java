package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.Cliente_VendedorED;
import com.master.rn.Cliente_VendedorRN;
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
public class Cliente_VendedorBean extends JavaUtil implements Serializable {

    public Cliente_VendedorED inclui(HttpServletRequest request) throws Excecoes {

        Cliente_VendedorED ed = new Cliente_VendedorED();

        String Oid_Cliente = request.getParameter("oid_Cliente");
        String Oid_Vendedor = request.getParameter("oid_Vendedor");
        String Dm_Situacao = request.getParameter("FT_DM_Situacao");
        String oid_Tipo_Tabela_Venda = request.getParameter("oid_Tipo_Tabela_Venda");

        //*** Validações
        if (doValida(Oid_Cliente) && doValida(Oid_Vendedor) && doValida(Dm_Situacao)) {

            ed.setOid_Cliente(Oid_Cliente);
            ed.setOid_Vendedor(Oid_Vendedor);
            ed.setDm_Situacao(Dm_Situacao);

        } else throw new Excecoes("Falta de parâmetros!");

        //*** Verifica se Registro Existe
        if (new BancoUtil().doExiste("Clientes_Vendedores", "oid_Cliente = '" + Oid_Cliente + "'" + " AND oid_Vendedor = '" + Oid_Vendedor + "'"))
            throw new Mensagens("Vendedor já cadastrado para esse Cliente!");

        if (doValida(oid_Tipo_Tabela_Venda))
            ed.setOid_Tipo_Tabela_Venda(Integer.parseInt(oid_Tipo_Tabela_Venda));
        return new Cliente_VendedorRN().inclui(ed);
    }

    public void altera(HttpServletRequest request) throws Excecoes {

        Cliente_VendedorRN Cliente_VendedorRN = new Cliente_VendedorRN();
        Cliente_VendedorED ed = new Cliente_VendedorED();

        String Oid_Cliente_Vendedor = request.getParameter("oid_Cliente_Vendedor");
        String Oid_Cliente = request.getParameter("oid_Cliente");
        String Oid_Vendedor = request.getParameter("oid_Vendedor");
        String Dm_Situacao = request.getParameter("FT_DM_Situacao");
        String oid_Tipo_Tabela_Venda = request.getParameter("oid_Tipo_Tabela_Venda");

        // *** Validações
        if (doValida(Oid_Cliente_Vendedor) && doValida(Oid_Cliente) && doValida(Oid_Vendedor) && doValida(Dm_Situacao)) {

            ed.setOid_Cliente_Vendedor(Oid_Cliente_Vendedor);
            ed.setCd_Cliente(Oid_Cliente);
            ed.setCd_Vendedor(Oid_Vendedor);
            ed.setDm_Situacao(Dm_Situacao);

        } else throw new Excecoes("Falta de parâmetros!");
        
        if (doValida(oid_Tipo_Tabela_Venda))
            ed.setOid_Tipo_Tabela_Venda(Integer.parseInt(oid_Tipo_Tabela_Venda));
        Cliente_VendedorRN.altera(ed);
    }

    public void deleta(HttpServletRequest request) throws Excecoes {

        Cliente_VendedorRN Cliente_VendedorRN = new Cliente_VendedorRN();
        Cliente_VendedorED ed = new Cliente_VendedorED();

        String oid_Cliente_Vendedor = request.getParameter("oid_Cliente_Vendedor");

        //*** Validações
        if (doValida(oid_Cliente_Vendedor)) {

            ed.setOid_Cliente_Vendedor(oid_Cliente_Vendedor);

        } else throw new Excecoes("Falta de parâmetros!");

        Cliente_VendedorRN.deleta(ed);
    }

    public ArrayList Cliente_Vendedor_Lista(HttpServletRequest request) throws Excecoes {

        Cliente_VendedorED ed = new Cliente_VendedorED();
        Cliente_VendedorRN Cliente_VendedorRN = new Cliente_VendedorRN();

        String Oid_Cliente = request.getParameter("oid_Pessoa");

        //*** Validações
        if (doValida(Oid_Cliente))
            ed.setOid_Cliente(Oid_Cliente);

        return Cliente_VendedorRN.lista(ed);

    }

    public Cliente_VendedorED getByRecord(HttpServletRequest request) throws Excecoes {

        Cliente_VendedorED ed = new Cliente_VendedorED();

        String oid_Cliente_Vendedor = request.getParameter("oid_Cliente_Vendedor");

        //*** Validações
        if (doValida(oid_Cliente_Vendedor)) {
            ed.setOid_Cliente_Vendedor(oid_Cliente_Vendedor);
        }
        return new Cliente_VendedorRN().getByRecord(ed);
    }
    
    public Cliente_VendedorED getByRecord(String oid_Vendedor, String oid_Cliente) throws Excecoes {

        Cliente_VendedorED ed = new Cliente_VendedorED();
        if (!doValida(oid_Vendedor) || !doValida(oid_Cliente))
            return ed;
        
        ed.setOid_Vendedor(oid_Vendedor);
        ed.setOid_Cliente(oid_Cliente);
        return new Cliente_VendedorRN().getByRecord(ed);
    }

    //*** Busca Cliente do Vendedor pelo Código CPF/CNPJ
    public Cliente_VendedorED getByCD_Cliente(String oid_Vendedor, String CD_Cliente) throws Excecoes {

        Cliente_VendedorED ed = new Cliente_VendedorED();
        //*** Validações
        if (doValida(oid_Vendedor) && doValida(CD_Cliente)) {
            ed.setOid_Vendedor(oid_Vendedor);
            ed.setCd_Cliente(CD_Cliente);
        } 
        return new Cliente_VendedorRN().getByRecord(ed);
    }
    
    //*** Busca Cliente do Vendedor pelo Código PALM
    public Cliente_VendedorED getByCodigo(String oid_Vendedor, String cdCliente) throws Excecoes {

        Cliente_VendedorED ed = new Cliente_VendedorED();

        //*** Validações
        if (doValida(oid_Vendedor) && doValida(cdCliente)) {
            ed.setOid_Vendedor(oid_Vendedor);
            ed.edCliente.setCD_Cliente_Palm(cdCliente);
        }
        return new Cliente_VendedorRN().getByRecord(ed);
    }

    //*** Lista de Clientes do Vendedor
    public ArrayList Lista_Cliente(String oid_Vendedor) throws Excecoes {

        //*** Validações
        if (doValida(oid_Vendedor)) {
            return new Cliente_VendedorRN().lista(new Cliente_VendedorED(oid_Vendedor));
        } else throw new Mensagens("Vendedor não informado!");
    }

    //*** RELATÓRIOS
    // Clientes do Vendedor
    public void RelCliente_Vendedor(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String oid_Vendedor = request.getParameter("oid_Vendedor");
        String Layout = request.getParameter("FT_Layout");
        String Ordenar = request.getParameter("FT_Ordenar");

        new Cliente_VendedorRN().RelCliente_Vendedor(response, oid_Vendedor, Layout, Ordenar);

    }
}