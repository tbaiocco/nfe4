package com.master.rn;

import java.util.ArrayList;
import com.master.bd.Relatorio_GeralBD;
import com.master.ed.Relatorio_GeralED;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;
import com.master.util.bd.Transacao;
import com.master.util.ed.Parametro_FixoED;

public class Relatorio_GeralRN extends Transacao {

    Relatorio_GeralBD Relatorio_GeralBD = null;

    public Relatorio_GeralRN() {
    }

    public byte[] geraPre_Faturamento_Pellenz(Relatorio_GeralED ed) throws Excecoes {
        this.inicioTransacao();
        Relatorio_GeralBD = new Relatorio_GeralBD(this.sql);
        byte[] b = Relatorio_GeralBD.geraPre_Faturamento_Pellenz(ed);
        this.fimTransacao(false);
        return b;
    }

}
