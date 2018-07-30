package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Lancamento_Centro_CustoED;
import com.master.rn.Lancamento_Centro_CustoRN;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;

public class Lancamento_Centro_CustoBean extends JavaUtil {

    public Lancamento_Centro_CustoED inclui(HttpServletRequest request) throws Excecoes {

        String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
        String oid_Centro_Custo = request.getParameter("oid_Centro_Custo");
        String oid_Lancamento = request.getParameter("oid_Lancamento");
        String PE_Lancamento = request.getParameter("FT_PE_Lancamento");
        String VL_Lancamento = request.getParameter("FT_VL_Lancamento");
        
        if (!doValida(oid_Nota_Fiscal))
            throw new Mensagens("Nota Fiscal não informado!");
        if (!doValida(oid_Centro_Custo))
            throw new Mensagens("Centro de Custo não informado!");

        Lancamento_Centro_CustoED ed = new Lancamento_Centro_CustoED();
        ed.setOid_Nota_Fiscal(oid_Nota_Fiscal);
        ed.setOid_Centro_Custo(Integer.parseInt(oid_Centro_Custo));
        if (doValida(oid_Lancamento))
            ed.setOid_Lancamento(Integer.parseInt(oid_Lancamento));
        if (doValida(PE_Lancamento))
            ed.setPE_Lancamento(Double.parseDouble(PE_Lancamento));
        if (doValida(VL_Lancamento))
            ed.setVL_Lancamento(Double.parseDouble(VL_Lancamento));
        
        return new Lancamento_Centro_CustoRN().inclui(ed);
    }

    public void altera(HttpServletRequest request) throws Excecoes {

        String oid_Lancamento_Centro_Custo = request.getParameter("oid_Lancamento_Centro_Custo");
        String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
        String oid_Centro_Custo = request.getParameter("oid_Centro_Custo");
        String oid_Lancamento = request.getParameter("oid_Lancamento");
        String PE_Lancamento = request.getParameter("FT_PE_Lancamento");
        String VL_Lancamento = request.getParameter("FT_VL_Lancamento");
        
        if (!doValida(oid_Lancamento_Centro_Custo))
            throw new Excecoes("ID Lancamento Centro Custo não informado!");
        if (!doValida(oid_Nota_Fiscal))
            throw new Mensagens("Nota Fiscal não informada!");
        if (!doValida(oid_Centro_Custo))
            throw new Mensagens("Centro de Custo não informado!");

        Lancamento_Centro_CustoED ed = new Lancamento_Centro_CustoED(Integer.parseInt(oid_Lancamento_Centro_Custo));
        ed.setOid_Nota_Fiscal(oid_Nota_Fiscal);
        ed.setOid_Centro_Custo(Integer.parseInt(oid_Centro_Custo));
        if (doValida(oid_Lancamento))
            ed.setOid_Lancamento(Integer.parseInt(oid_Lancamento));
        if (doValida(PE_Lancamento))
            ed.setPE_Lancamento(Double.parseDouble(PE_Lancamento));
        if (doValida(VL_Lancamento))
            ed.setVL_Lancamento(Double.parseDouble(VL_Lancamento));

        new Lancamento_Centro_CustoRN().altera(ed);
    }

    public void deleta(HttpServletRequest request) throws Excecoes {

        String oid_Lancamento_Centro_Custo = request.getParameter("oid_Lancamento_Centro_Custo");
        String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
        String DM_Tipo_Lancamento = request.getParameter("FT_DM_Tipo_Lancamento");
        
        if (!doValida(oid_Lancamento_Centro_Custo))
            throw new Excecoes("ID Lancamento Centro Custo não informado!");
        if (!doValida(oid_Nota_Fiscal))
            throw new Mensagens("Nota Fiscal não informada!");
        if (!doValida(DM_Tipo_Lancamento))
            throw new Mensagens("Tipo de Lançamento não informado!");

        Lancamento_Centro_CustoED ed = new Lancamento_Centro_CustoED(Integer.parseInt(oid_Lancamento_Centro_Custo));
        ed.setOid_Nota_Fiscal(oid_Nota_Fiscal);
        ed.setDM_Tipo_Lancamento(DM_Tipo_Lancamento);
        
        new Lancamento_Centro_CustoRN().deleta(ed);
    }

    public Lancamento_Centro_CustoED getByRecord(HttpServletRequest request) throws Excecoes {

        String oid_Lancamento_Centro_Custo = request.getParameter("oid_Lancamento_Centro_Custo");
        if (!doValida(oid_Lancamento_Centro_Custo))
            throw new Excecoes("ID Lancamento Centro Custo não informado!");

        return new Lancamento_Centro_CustoRN().getByRecord(new Lancamento_Centro_CustoED(Integer.parseInt(oid_Lancamento_Centro_Custo)));
    }

    public ArrayList lista(HttpServletRequest request) throws Excecoes {

        String oid_Lancamento_Centro_Custo = request.getParameter("oid_Lancamento_Centro_Custo");
        String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
        String oid_Centro_Custo = request.getParameter("oid_Centro_Custo");
        String oid_Lancamento = request.getParameter("oid_Lancamento");
        
        Lancamento_Centro_CustoED ed = new Lancamento_Centro_CustoED();
        if (doValida(oid_Lancamento_Centro_Custo))
            Integer.parseInt(oid_Lancamento_Centro_Custo);
        if (doValida(oid_Nota_Fiscal))
            ed.setOid_Nota_Fiscal(oid_Nota_Fiscal);
        if (doValida(oid_Centro_Custo))
            ed.setOid_Centro_Custo(Integer.parseInt(oid_Centro_Custo));
        if (doValida(oid_Lancamento))
            ed.setOid_Lancamento(Integer.parseInt(oid_Lancamento));
        
        return new Lancamento_Centro_CustoRN().lista(ed);
    }
    
    public double getValorRateios(String oid_Nota_Fiscal) throws Excecoes {
        
        if (!doValida(oid_Nota_Fiscal))
            throw new Mensagens("Nota Fiscal não informada!");
        return new BancoUtil().getTableDoubleValue("SUM(VL_Lancamento)", 
                                                   "Lancamentos_Centros_Custos", 
                                                   "oid_Nota_Fiscal = '"+oid_Nota_Fiscal+"'" +
                                                   " AND DM_Tipo_Lancamento = 'R'");
    }
}