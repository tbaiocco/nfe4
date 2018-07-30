package com.master.rn;

import br.cte.model.Empresa;

import com.master.bd.CTeBD;
import com.master.bd.NFeBD;
import com.master.ed.Nota_Fiscal_EletronicaED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

public class NFeRN extends Transacao {
	CTeBD cteBD = null;

	public NFeRN(Empresa empresa) {
		super(empresa);
	}

	protected void finalize() throws Throwable {
		if (this.sql != null)
			this.abortaTransacao();
	}

	public Nota_Fiscal_EletronicaED numeraNFe(Nota_Fiscal_EletronicaED ed,String nmSerie) throws Exception {
		try {
            this.inicioTransacao();
            this.doBeginTransaction();
            ed = new NFeBD(this.sql).numeraNFe(ed, nmSerie);
            this.fimTransacao(true);
        } catch(Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (Exception e) {
			this.abortaTransacao();
			throw new Excecoes("Erro ao numerar NFe", e, this.getClass()
					.getName(), "numeraNFe()");
		}
        return ed;
	}

}
