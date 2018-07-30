package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.master.ed.LicenciamentoED;
import com.master.rn.LicenciamentoRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

public class Opn039Bean extends JavaUtil {

    public LicenciamentoED inclui(HttpServletRequest request) throws Excecoes {

        LicenciamentoED ed = new LicenciamentoED();

        ed.setDt_Limite(request.getParameter("FT_DT_Limite"));
        ed.setNr_Dezena_Placa(request.getParameter("FT_NR_Dezena_Placa"));

        return new LicenciamentoRN().inclui(ed);
    }

    public void altera(HttpServletRequest request) throws Excecoes {

        LicenciamentoED ed = new LicenciamentoED();

        ed.setOid_Licenciamento(request.getParameter("oid_Licenciamento"));
        ed.setDt_Limite(request.getParameter("FT_DT_Limite"));
        ed.setNr_Dezena_Placa(request.getParameter("FT_NR_Dezena_Placa"));

        new LicenciamentoRN().altera(ed);
    }

    public void deleta(HttpServletRequest request) throws Excecoes {

        LicenciamentoED ed = new LicenciamentoED();

        ed.setOid_Licenciamento(request.getParameter("oid_Licenciamento"));

        new LicenciamentoRN().deleta(ed);
    }

    public ArrayList Licenciamento_Lista(HttpServletRequest request) throws Excecoes {

        LicenciamentoED ed = new LicenciamentoED();

        String Oid_Licenciamento = request.getParameter("oid_Licenciamento");
        if (Oid_Licenciamento != null && !Oid_Licenciamento.equals(""))
        	ed.setOid_Licenciamento(Oid_Licenciamento);

        String DT_Limite = request.getParameter("FT_DT_Limite");
        if (DT_Limite != null && !DT_Limite.equals(""))
        	ed.setDt_Limite(DT_Limite);

        String NR_Dezena_Placa = request.getParameter("FT_NR_Dezena_Placa");
        if (NR_Dezena_Placa != null && !NR_Dezena_Placa.equals(""))
        	ed.setNr_Dezena_Placa(NR_Dezena_Placa);

        return new LicenciamentoRN().lista(ed);

    }

    public LicenciamentoED getByRecord(HttpServletRequest request) throws Excecoes {

        LicenciamentoED ed = new LicenciamentoED();

        String oid_Licenciamento = request.getParameter("oid_Licenciamento");

        if (oid_Licenciamento != null && oid_Licenciamento.length() > 0) {
            ed.setOid_Licenciamento(oid_Licenciamento);
        }
        return new LicenciamentoRN().getByRecord(ed);
    }

    public LicenciamentoED getByRecord(String oid_Licenciamento) throws Excecoes {

        if (doValida(oid_Licenciamento))
            return new LicenciamentoRN().getByRecord(new LicenciamentoED(oid_Licenciamento));
        else return new LicenciamentoED();
    }

    public boolean getLiberacao_Licenciamento(String oid_Veiculo) throws Excecoes {

        if (doValida(oid_Veiculo))
            return new LicenciamentoRN().getLiberacao_Licenciamento(oid_Veiculo);
        else return false;
    }

}
