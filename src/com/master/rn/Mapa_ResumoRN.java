package com.master.rn;

import java.util.ArrayList;
import com.master.bd.Mapa_ResumoBD;
import com.master.ed.Mapa_ResumoED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;
import com.master.util.bd.Transacao;

/**
 * @author André Valadas
 * @serial Mapa Resumo PDV
 * @serialData 16/03/2006
 */
public class Mapa_ResumoRN extends Transacao {

    public Mapa_ResumoRN() {
    }
    
    public boolean exist(String oid_Nota_Fiscal) throws Excecoes {
        if (!JavaUtil.doValida(oid_Nota_Fiscal))
            throw new Mensagens("ID Nota Fiscal não informado!");
        return new BancoUtil().doExiste("Mapas_Resumos","oid_Nota_Fiscal ="+JavaUtil.getSQLString(oid_Nota_Fiscal));
    }

    public Mapa_ResumoED gravar(Mapa_ResumoED ed) throws Exception {

        try {
            this.inicioTransacao();
            if (!exist(ed.getOid_Nota_Fiscal()))
                new Mapa_ResumoBD(this.sql).inclui(ed);
            else new Mapa_ResumoBD(this.sql).altera(ed);
            this.fimTransacao(true);
            return ed;
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }
    
    public void deleta(Mapa_ResumoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            new Mapa_ResumoBD(this.sql).deleta(ed);
            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    }

    public ArrayList lista(Mapa_ResumoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Mapa_ResumoBD(sql).lista(ed);
        } finally {
            this.fimTransacao(false);
        }
    }

    public Mapa_ResumoED getByRecord(Mapa_ResumoED ed) throws Excecoes {

        try {
            this.inicioTransacao();
            return new Mapa_ResumoBD(sql).getByRecord(ed);
        } finally {
            this.fimTransacao(false);
        }
    }
}