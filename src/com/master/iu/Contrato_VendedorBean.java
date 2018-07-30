package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.Contrato_VendedorED;
import com.master.ed.Contrato_VendedorRelED;
import com.master.rn.Contrato_VendedorRN;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

/*
 * Created on 25/10/2004
 */

/**
 * @author Andre Valadas
 */
public class Contrato_VendedorBean extends JavaUtil implements Serializable {

    public Contrato_VendedorED inclui(HttpServletRequest request) throws Excecoes {

        Contrato_VendedorED ed = new Contrato_VendedorED();

        String Oid_Vendedor = request.getParameter("oid_Vendedor");
        String oid_Pessoa_Distribuidor = request.getParameter("oid_Pessoa_Distribuidor");
        String DM_Tipo_Contrato = request.getParameter("FT_DM_Tipo_Contrato");
        String DT_Contrato = request.getParameter("FT_DT_Contrato");
        String NR_Contrato = request.getParameter("FT_NR_Contrato");
        String TX_Observacao1 = request.getParameter("FT_TX_Observacao1");
        String TX_Observacao2 = request.getParameter("FT_TX_Observacao2");
        String TX_Observacao3 = request.getParameter("FT_TX_Observacao3");
        //*** Se TIPO == ADENDO
        String NR_Folha = request.getParameter("FT_NR_Folha");
        String DT_Inicial = request.getParameter("FT_DT_Inicial");
        String DT_Final = request.getParameter("FT_DT_Final");

        //*** Validações
        if ((doValida(oid_Pessoa_Distribuidor)) && (doValida(Oid_Vendedor))
                && (doValida(DT_Contrato)) && (doValida(DM_Tipo_Contrato))
                && (doValida(NR_Contrato))) {

            ed.setOid_Pessoa(oid_Pessoa_Distribuidor);
            ed.setOid_Vendedor(Oid_Vendedor);
            ed.setDT_Contrato(Data.strToDate(DT_Contrato));
            ed.setDM_Tipo_Contrato(DM_Tipo_Contrato);
            ed.setNR_Contrato(NR_Contrato);
            ed.setTX_Observacao(TX_Observacao1 + " " + TX_Observacao2 + " "
                    + TX_Observacao3);

            if (DM_Tipo_Contrato.equals("ADENDO") && doValida(DT_Inicial)
                    && doValida(DT_Final) && doValida(NR_Folha)) {

                ed.setDT_Inicial(Data.strToDate(DT_Inicial));
                ed.setDT_Final(Data.strToDate(DT_Final));
                ed.setNR_Folha(Integer.parseInt(NR_Folha));
            }
        } else throw new Excecoes("Parâmetros incorretos!");

        return new Contrato_VendedorRN().inclui(ed);
    }

    //*** Se for ANEXO, exclui todos os ADENDOS referentes
    public void deleta(HttpServletRequest request) throws Excecoes {

        String oid_Contrato_Vendedor = request.getParameter("oid_Contrato_Vendedor");
        String oid_Vendedor = request.getParameter("oid_Vendedor");
        String DM_Tipo_Contrato = request.getParameter("FT_DM_Tipo_Contrato");
        
        //*** Validações
        if (!doValida(oid_Contrato_Vendedor) || !doValida(DM_Tipo_Contrato) || !doValida(oid_Vendedor))
            throw new Excecoes("Parâmetros incorretos!");

        Contrato_VendedorED ed = new Contrato_VendedorED(Integer.parseInt(oid_Contrato_Vendedor));
        ed.setOid_Vendedor(oid_Vendedor);
        ed.setDM_Tipo_Contrato(DM_Tipo_Contrato);
        new Contrato_VendedorRN().deleta(ed);
    }

    public ArrayList Contrato_Vendedor_Lista(HttpServletRequest request) throws Excecoes {

        Contrato_VendedorED ed = new Contrato_VendedorED();

        String Oid_Vendedor = request.getParameter("oid_Vendedor");
        String DM_Tipo_Contrato = request.getParameter("FT_DM_Tipo_Contrato");

        //*** Validações
        if (doValida(Oid_Vendedor)) {

            ed.setOid_Vendedor(Oid_Vendedor);
            ed.setDM_Tipo_Contrato(DM_Tipo_Contrato);

        } else throw new Excecoes("Parâmetros incorretos!");

        return new Contrato_VendedorRN().lista(ed);
    }

    //*** Verifica se existe ANEXO!
    public boolean doExisteAnexo(String oid_Vendedor) throws Excecoes {

        String strFrom = "contratos_vededores";
        String strWhere = "Oid_Vendedor = '" + oid_Vendedor + "'" +
        //Distribuidora - "oid_Pessoa_Distribuidor =
        // '"+oid_Pessoa_Distribuidor+"'" +
                " and DM_Tipo_Contrato = 'ANEXO'";
        return new BancoUtil().doExiste(strFrom, strWhere);
    }

    //*** Próximo Registro
    public int getPróximoNRContrato(String oid_Vendedor, String oid_Pessoa_Distribuidor) throws Excecoes {

        //*** Validações
        if (doValida(oid_Vendedor) && doValida(oid_Pessoa_Distribuidor)) {

            String strCampo = "NR_Contrato";
            String strFrom = "contratos_vendedores";
            String strWhere = " oid_Vendedor = '" + oid_Vendedor + "'" +
                              " AND oid_Pessoa = '"+oid_Pessoa_Distribuidor+"'";
            return (new BancoUtil().getMaximo(strCampo, strFrom, strWhere) + 1);

        } else throw new Excecoes("Parâmetros incorretos!");

    }

    public Contrato_VendedorED getByRecord(String oid_Contrato_Vendedor) throws Excecoes {

        //*** Validações
        if (doValida(oid_Contrato_Vendedor)) {
            return new Contrato_VendedorRN().getByRecord(new Contrato_VendedorED(Integer.parseInt(oid_Contrato_Vendedor)));
        } else throw new Excecoes("Parâmetros incorretos!");
    }

    //*** RELATÓRIOS
    // Contratos do Vendedor
    public void RelContrato_Vendedor_ANEXO(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        String oid_Contrato_Vendedor = request.getParameter("oid_Contrato_Vendedor");
        String oid_Vendedor = request.getParameter("oid_Vendedor");

        if (doValida(oid_Contrato_Vendedor) && doValida(oid_Vendedor)) {

            Contrato_VendedorRelED ed = new Contrato_VendedorRelED();
            ed.setOid_contrato_vendedor(Integer.parseInt(oid_Contrato_Vendedor));
            ed.setOid_vendedor(oid_Vendedor);
            ed.setNR_Contrato("000");
            new Contrato_VendedorRN().RelContrato_Vendedor_ANEXO(response, ed);

        } else throw new Excecoes("Parâmetros incorretos!");
    }

    public void RelContrato_Vendedor_ADENDO(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        String Oid_Contrato_Vendedor = request.getParameter("oid_Contrato_Vendedor");
        String Oid_Vendedor = request.getParameter("oid_Vendedor");

        //*** Validações
        if (doValida(Oid_Vendedor) && doValida(Oid_Contrato_Vendedor)) {

            Contrato_VendedorRelED ed = new Contrato_VendedorRelED();
            ed.setOid_contrato_vendedor(Integer.parseInt(Oid_Contrato_Vendedor));
            ed.setOid_vendedor(Oid_Vendedor);

            new Contrato_VendedorRN().RelContrato_Vendedor_ADENDO(response, ed);

        } else throw new Excecoes("Parâmetros incorretos!");
    }
}