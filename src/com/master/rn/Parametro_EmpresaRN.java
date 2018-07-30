package com.master.rn;

import javax.servlet.http.HttpServletRequest;

import com.master.bd.Parametro_EmpresaBD;
import com.master.ed.Parametro_EmpresaED;
import com.master.util.Excecoes;
import com.master.util.bd.Transacao;

/**
 * @author Vinicius e Jeanine
 * @serial Parametro empresa 
 * @serialData 02/05/2008
 */

public class Parametro_EmpresaRN extends Transacao {

    public Parametro_EmpresaRN() {    
    }
 
    public void altera(Parametro_EmpresaED ed, String metodo) throws Excecoes {

        try {
            this.inicioTransacao();
    		if ("altera".equals(metodo)) new Parametro_EmpresaBD(this.sql).altera(ed);
    		if ("alteraAcoesGerais".equals(metodo)) new Parametro_EmpresaBD(this.sql).alteraAcoesGerais(ed);
    		if ("alteraAliquota".equals(metodo)) new Parametro_EmpresaBD(this.sql).alteraAliquota(ed);
    		if ("alteraBaseDados".equals(metodo)) new Parametro_EmpresaBD(this.sql).alteraBaseDados(ed);
    		if ("alteraCalculos".equals(metodo)) new Parametro_EmpresaBD(this.sql).alteraCalculos(ed);
    		if ("alteraCarteira".equals(metodo)) new Parametro_EmpresaBD(this.sql).alteraCarteira(ed);
    		if ("alteraCentroCusto".equals(metodo)) new Parametro_EmpresaBD(this.sql).alteraCentroCusto(ed);
    		if ("alteraCfopTaxa".equals(metodo)) new Parametro_EmpresaBD(this.sql).alteraCfopTaxa(ed);
    		if ("alteraConta".equals(metodo)) new Parametro_EmpresaBD(this.sql).alteraConta(ed);
    		if ("alteraDescricao".equals(metodo)) new Parametro_EmpresaBD(this.sql).alteraDescricao(ed);
    		if ("alteraDocumento".equals(metodo)) new Parametro_EmpresaBD(this.sql).alteraDocumento(ed);
    		if ("alteraFaturamento".equals(metodo)) new Parametro_EmpresaBD(this.sql).alteraFaturamento(ed);
			if ("alteraFormulario".equals(metodo)) new Parametro_EmpresaBD(this.sql).alteraFormulario(ed);
    		if ("alteraFrota".equals(metodo)) new Parametro_EmpresaBD(this.sql).alteraFrota(ed);
    		if ("alteraHistorico".equals(metodo)) new Parametro_EmpresaBD(this.sql).alteraHistorico(ed);
    		if ("alteraImpressao".equals(metodo)) new Parametro_EmpresaBD(this.sql).alteraImpressao(ed);
    		if ("alteraInstrucoes".equals(metodo)) new Parametro_EmpresaBD(this.sql).alteraInstrucoes(ed);
    		if ("alteraLocalizacao".equals(metodo)) new Parametro_EmpresaBD(this.sql).alteraLocalizacao(ed);
    		if ("alteraModal".equals(metodo)) new Parametro_EmpresaBD(this.sql).alteraModal(ed);
    		if ("alteraModelo".equals(metodo)) new Parametro_EmpresaBD(this.sql).alteraModelo(ed);
    		if ("alteraModeloTabela".equals(metodo)) new Parametro_EmpresaBD(this.sql).alteraModeloTabela(ed);
    		if ("alteraMovimento".equals(metodo)) new Parametro_EmpresaBD(this.sql).alteraMovimento(ed);
    		if ("alteraNatureza".equals(metodo)) new Parametro_EmpresaBD(this.sql).alteraNatureza(ed);
    		if ("alteraNumeracoes".equals(metodo)) new Parametro_EmpresaBD(this.sql).alteraNumeracoes(ed);
    		if ("alteraOcorrencia".equals(metodo)) new Parametro_EmpresaBD(this.sql).alteraOcorrencia(ed);
    		if ("alteraPessoa".equals(metodo)) new Parametro_EmpresaBD(this.sql).alteraPessoa(ed);
    		if ("alteraServico".equals(metodo)) new Parametro_EmpresaBD(this.sql).alteraServico(ed);
    		if ("alteraTransporteNacional".equals(metodo)) new Parametro_EmpresaBD(this.sql).alteraTransporteNacional(ed);
    		if ("alteraTransporteInternacional".equals(metodo)) new Parametro_EmpresaBD(this.sql).alteraTransporteInternacional(ed);
			if ("alteraUnidade".equals(metodo)) new Parametro_EmpresaBD(this.sql).alteraUnidade(ed);
    		if ("alteraWMS".equals(metodo)) new Parametro_EmpresaBD(this.sql).alteraWMS(ed);

            this.fimTransacao(true);
        } catch (Excecoes e) {
            this.abortaTransacao();
            throw e;
        } catch (RuntimeException e) {
            this.abortaTransacao();
            throw e;
        }
    } 
       
	public Parametro_EmpresaED getByRecord(Parametro_EmpresaED ed) throws Excecoes {
		try {
			this.inicioTransacao();
			Parametro_EmpresaED edQBR = new Parametro_EmpresaBD(this.sql).getByRecord(ed);
			return edQBR;
		} 
		finally { 
			this.fimTransacao(false);
		}
	}

	protected void finalize() throws Throwable {
        if (this.sql != null)
            this.abortaTransacao();
    }
}