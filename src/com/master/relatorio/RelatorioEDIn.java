// Decompiled by DJ v3.5.5.77 Copyright 2003 Atanas Neshkov Date: 19/11/2003
// 16:00:37
// Home Page : http://members.fortunecity.com/neshkov/dj.html - Check often for
// new version!
// Decompiler options: packimports(3)
// Source File Name: RelatorioEDIn.java

package com.master.relatorio;

import java.util.Calendar;

public class RelatorioEDIn {

    public RelatorioEDIn() {
    }

    public Calendar getDtHoraEmissao() {
        return dtHoraEmissao;
    }

    public void setDtHoraEmissao(Calendar aDtHoraEmissao) {
        dtHoraEmissao = aDtHoraEmissao;
    }

    public Integer getMatriculaSoe() {
        return null;
    }

    public void setMatriculaSoe(Integer integer1) {
    }

    private String destino;
    private Calendar dtHoraEmissao;
    private Integer matriculaSoe;
}