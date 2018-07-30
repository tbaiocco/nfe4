package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.Produto_PatrimonioED;
import com.master.rn.Produto_PatrimonioRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

public class Ctb039Bean extends JavaUtil {

    public Produto_PatrimonioED inclui(HttpServletRequest request) throws Excecoes {

        Produto_PatrimonioED ed = new Produto_PatrimonioED();

        ed.setNm_Produto_Patrimonio(request.getParameter("FT_NM_Produto_Patrimonio"));

        ed.setOid_Categoria(new Integer(request.getParameter("oid_Categoria")));

        ed.setOid_Conta(new Integer(request.getParameter("oid_Conta")));

        return new Produto_PatrimonioRN().inclui(ed);
    }

    public void altera(HttpServletRequest request) throws Excecoes {

        Produto_PatrimonioED ed = new Produto_PatrimonioED();

        ed.setOid_Produto_Patrimonio(request.getParameter("oid_Produto_Patrimonio"));
        ed.setNm_Produto_Patrimonio(request.getParameter("FT_NM_Produto_Patrimonio"));

        ed.setOid_Categoria(new Integer(request.getParameter("oid_Categoria")));
        ed.setOid_Conta(new Integer(request.getParameter("oid_Conta")));

        new Produto_PatrimonioRN().altera(ed);
    }

    public void deleta(HttpServletRequest request) throws Excecoes {

        Produto_PatrimonioED ed = new Produto_PatrimonioED();

        ed.setOid_Produto_Patrimonio(request.getParameter("oid_Produto_Patrimonio"));

        new Produto_PatrimonioRN().deleta(ed);
    }

    public ArrayList Produto_Patrimonio_Lista(HttpServletRequest request) throws Excecoes {

        Produto_PatrimonioED ed = new Produto_PatrimonioED();

        String Oid_Produto_Patrimonio = request.getParameter("oid_Produto_Patrimonio");
        if (Oid_Produto_Patrimonio != null && !Oid_Produto_Patrimonio.equals(""))
        	ed.setOid_Produto_Patrimonio(Oid_Produto_Patrimonio);

        String Nm_Produto_Patrimonio = request.getParameter("FT_NM_Produto_Patrimonio");
        if (Nm_Produto_Patrimonio != null && !Nm_Produto_Patrimonio.equals(""))
        	ed.setNm_Produto_Patrimonio(Nm_Produto_Patrimonio);

        String oid_Categoria = request.getParameter("oid_Categoria");
        if (oid_Categoria != null && !oid_Categoria.equals(""))
        	ed.setOid_Categoria(new Integer(oid_Categoria));

        String oid_Conta = request.getParameter("oid_Conta");
        if (oid_Conta != null && !oid_Conta.equals(""))
        	ed.setOid_Conta(new Integer(oid_Conta));

        return new Produto_PatrimonioRN().lista(ed);

    }

    public Produto_PatrimonioED getByRecord(HttpServletRequest request) throws Excecoes {

        Produto_PatrimonioED ed = new Produto_PatrimonioED();

        String oid_Produto_Patrimonio = request.getParameter("oid_Produto_Patrimonio");

        if (oid_Produto_Patrimonio != null && oid_Produto_Patrimonio.length() > 0) {
            ed.setOid_Produto_Patrimonio(oid_Produto_Patrimonio);
        }
        return new Produto_PatrimonioRN().getByRecord(ed);
    }

    public Produto_PatrimonioED getByRecord(String oid_Produto_Patrimonio) throws Excecoes {

        if (doValida(oid_Produto_Patrimonio))
            return new Produto_PatrimonioRN().getByRecord(new Produto_PatrimonioED(oid_Produto_Patrimonio));
        else return new Produto_PatrimonioED();
    }
}
