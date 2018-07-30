package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Mix_VendedorED;
import com.master.rn.Mix_VendedorRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

/*
 * Created on 09/09/2004
 */

/**
 * @author Andre Valadas
 */
public class Mix_VendedorBean extends JavaUtil implements Serializable {

    public Mix_VendedorED inclui(HttpServletRequest request) throws Excecoes {

        Mix_VendedorED ed = new Mix_VendedorED();
        String oid_Mix = request.getParameter("oid_Mix");
        String oid_Vendedor = request.getParameter("oid_Vendedor");

        // *** Validações
        if (!doValida(oid_Mix) || !doValida(oid_Vendedor))
            new Excecoes("Falta de Parâmetros!");

        ed.setOid_Mix(new Integer(oid_Mix).intValue());
        ed.setOid_Vendedor(oid_Vendedor);

        return new Mix_VendedorRN().inclui(ed);
    }

    public void altera(HttpServletRequest request) throws Excecoes {

        Mix_VendedorRN Mix_VendedorRN = new Mix_VendedorRN();
        Mix_VendedorED ed = new Mix_VendedorED();

        String oid_Mix_Vendedor = request.getParameter("oid_Mix_Vendedor");
        String oid_Mix = request.getParameter("oid_Mix");
        String oid_Vendedor = request.getParameter("oid_Vendedor");

        // *** Validações
        if (!doValida(oid_Mix_Vendedor) || !doValida(oid_Mix) || doValida(oid_Vendedor))
            new Excecoes("Falta de Parâmetros!");

        ed.setOid_Mix_Vendedor(new Integer(oid_Mix_Vendedor).intValue());
        ed.setOid_Mix(new Integer(oid_Mix).intValue());
        ed.setOid_Vendedor(oid_Vendedor);
        Mix_VendedorRN.altera(ed);

    }

    public void deleta(HttpServletRequest request) throws Excecoes {

        Mix_VendedorRN Mix_VendedorRN = new Mix_VendedorRN();
        Mix_VendedorED ed = new Mix_VendedorED();

        String oid_Mix_Vendedor = request.getParameter("oid_Mix_Vendedor");
        String oid_Vendedor = request.getParameter("oid_Vendedor");

        // *** Validações
        if (!doValida(oid_Mix_Vendedor) || doValida(oid_Vendedor))
            new Excecoes("Falta de Parâmetros!");

        ed.setOid_Vendedor(oid_Vendedor);
        ed.setOid_Mix_Vendedor(new Integer(oid_Mix_Vendedor).intValue());
        Mix_VendedorRN.deleta(ed);
    }

    public ArrayList Mix_Vendedor_Lista(HttpServletRequest request) throws Excecoes {

        Mix_VendedorED ed = new Mix_VendedorED();
        Mix_VendedorRN Mix_VendedorRN = new Mix_VendedorRN();
        String oid_Vendedor = request.getParameter("oid_Vendedor");

        // *** Validações
        if (doValida(oid_Vendedor))
            ed.setOid_Vendedor(oid_Vendedor);

        return Mix_VendedorRN.lista(ed);

    }

    public Mix_VendedorED getByRecord(HttpServletRequest request) throws Excecoes {

        Mix_VendedorED ed = new Mix_VendedorED();

        String oid_Mix_Vendedor = request.getParameter("oid_Mix_Vendedor");

        // *** Validações
        if (oid_Mix_Vendedor != null && !oid_Mix_Vendedor.equals("0") && !oid_Mix_Vendedor.equals("") && !oid_Mix_Vendedor.equals("null")) {

            ed.setOid_Mix_Vendedor(new Integer(oid_Mix_Vendedor).intValue());
        }

        return new Mix_VendedorRN().getByRecord(ed);

    }

    public Mix_VendedorED getByOidMixVendedor(int oid_Mix_Vendedor) throws Excecoes {

        Mix_VendedorED ed = new Mix_VendedorED();

        // *** Validações
        if (oid_Mix_Vendedor > 0) {
            ed.setOid_Mix_Vendedor(oid_Mix_Vendedor);
        }

        return new Mix_VendedorRN().getByOidMixProduto(oid_Mix_Vendedor);
    }
}