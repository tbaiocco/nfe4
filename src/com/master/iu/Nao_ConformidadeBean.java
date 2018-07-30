package com.master.iu;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import auth.OracleConnection2;

import com.master.ed.Nao_ConformidadeED;
import com.master.rn.Nao_ConformidadeRN;
import com.master.root.PessoaBean;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;


public class Nao_ConformidadeBean extends JavaUtil implements Serializable {
	
	
	private long oid_nao_conformidade;
	private long oid_identificador_problema;
		/**
	 * @return Returns the oid_identificador_problema.
	 */
	public long getOid_identificador_problema() {
		return oid_identificador_problema;
	}
	/**
	 * @param oid_identificador_problema The oid_identificador_problema to set.
	 */
	public void setOid_identificador_problema(long oid_identificador_problema) {
		this.oid_identificador_problema = oid_identificador_problema;
	}
	/**
	 * @return Returns the oid_nao_conformidade.
	 */
	public long getOid_nao_conformidade() {
		return oid_nao_conformidade;
	}
	/**
	 * @param oid_nao_conformidade The oid_nao_conformidade to set.
	 */
	public void setOid_nao_conformidade(long oid_nao_conformidade) {
		this.oid_nao_conformidade = oid_nao_conformidade;
	}
/**
	 * @return Returns the oid_cliente_fornecedor.
	 */
	public long getOid_cliente_fornecedor() {
		return oid_cliente_fornecedor;
	}
	/**
	 * @param oid_cliente_fornecedor The oid_cliente_fornecedor to set.
	 */
	public void setOid_cliente_fornecedor(long oid_cliente_fornecedor) {
		this.oid_cliente_fornecedor = oid_cliente_fornecedor;
	}
	/**
	 * @return Returns the oid_nao_conhecimento.
	 */
	public long getOid_nao_conhecimento() {
		return oid_nao_conformidade;
	}
	/**
	 * @param oid_nao_conhecimento The oid_nao_conhecimento to set.
	 */
	public void setOid_nao_conhecimento(long oid_nao_conhecimento) {
		this.oid_nao_conformidade = oid_nao_conhecimento;
	}
	private long oid_cliente_fornecedor;
	

	public Nao_ConformidadeED inclui(HttpServletRequest request) throws Excecoes {

		try {
			
			
			Nao_ConformidadeRN Nao_ConformidadeRN = new Nao_ConformidadeRN();
			Nao_ConformidadeED ed = new Nao_ConformidadeED();
			
			
							
				ed.setNM_descricao_problema(request.getParameter("FT_NM_descricao_problema"));
				ed.setNM_disposicao_problema(request.getParameter("FT_NM_disposicao_problema"));
				ed.setNM_acao_contencao(request.getParameter("FT_NM_acao_contencao"));
				ed.setNM_causa(request.getParameter("FT_NM_causa"));
				ed.setNM_acao_corretiva(request.getParameter("FT_NM_acao_corretiva"));
				ed.setNM_verificacao_implemantacao(request.getParameter("FT_NM_verificacao_implemantacao"));
				ed.setDT_Prazo(request.getParameter("FT_DT_Prazo"));
				ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));
				ed.setNM_verificacao_eficacia(request.getParameter("FT_DM_NM_verificacao_eficacia"));				

				
				return Nao_ConformidadeRN.inclui(ed);
				
			
		}
		catch (Excecoes exc) {
			// System.out.println(exc);
			throw exc;
		}
	}

	public void altera(HttpServletRequest request) throws Excecoes {

		try {
			Nao_ConformidadeRN Nao_ConformidadeRN = new Nao_ConformidadeRN();
			Nao_ConformidadeED ed = new Nao_ConformidadeED();

			String oid_Nao_Conformidade = request.getParameter("oid_nao_conformidade");

			//*** Validações
			if (JavaUtil.doValida(oid_Nao_Conformidade)) {

				ed.setOid_nao_conformidade(Integer.parseInt(oid_Nao_Conformidade));				
				//ed.setOid_nao_conformidade(oid_Nao_Conformidade);

			} else // System.err.println("ERRO! Parametros incorretos!");

			Nao_ConformidadeRN.altera(ed);

		} catch (Excecoes exc) {
			throw exc;
		}
	}

	public void deleta(HttpServletRequest request) throws Excecoes {

		try {
			Nao_ConformidadeRN Nao_ConformidadeRN = new Nao_ConformidadeRN();
			Nao_ConformidadeED ed = new Nao_ConformidadeED();

			String oid_Nao_Conformidade = request.getParameter("oid_nao_conformidade");

			//*** Validações
			if (Integer.parseInt(oid_Nao_Conformidade)>0) {

				ed.setOid_nao_conformidade(Integer.parseInt(oid_Nao_Conformidade));
				//ed.setOid_nao_conformidade(oid_Nao_Conformidade);

			} else // System.err.println("ERRO! Parâmetros incorretos!");

			Nao_ConformidadeRN.deleta(ed);

		} catch (Excecoes exc) {
			// System.out.println(exc);
			throw exc;
		}
	}
	
	public ArrayList Lista_Nao_Conformidade(HttpServletRequest request)
			throws Excecoes {

		Nao_ConformidadeED ed = new Nao_ConformidadeED();
		Nao_ConformidadeRN Nao_ConformidadeRN = new Nao_ConformidadeRN();

		String oid_Nao_Conformidade = request.getParameter("oid_nao_conformidade");
        String oid_cliente_fornecedor = request.getParameter("oid_Pessoa");
        ed.setOid_cliente_fornecedor(request.getParameter("oid_Pessoa"));
        String oid_identificador_problema = request.getParameter("oid_Pessoa2");
        ed.setOid_identificador_problema(request.getParameter("oid_Pessoa2"));
        String dt_Emissao = request.getParameter("FT_DT_Emissao");
        ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));

		//*** Validações
		//if (Integer.parseInt(oid_Nao_Conformidade)>0) {
        if (JavaUtil.doValida(oid_Nao_Conformidade)) {
			ed.setOid_nao_conformidade(Integer.parseInt(oid_Nao_Conformidade));
			//ed.setOid_nao_conformidade(oid_Nao_Conformidade);
		}

		return Nao_ConformidadeRN.lista(ed);
	}

	public Nao_ConformidadeED getByOidNaoConformidade(int oid_Nao_Conformidade) throws Excecoes {

		Nao_ConformidadeED ed = new Nao_ConformidadeED();

		//*** Validações
		if (oid_Nao_Conformidade > 0) {
			ed.setOid_nao_conformidade(oid_Nao_Conformidade);
		}		
		return new Nao_ConformidadeRN().getByOidNao_Conformidade(Integer.toString(oid_Nao_Conformidade));
	}
	
    //*** Verifica se registro já existe!
    public boolean doExiste(HttpServletRequest request) throws Excecoes {
        
        String oid_Nao_Conformidade = request.getParameter("oid_nao_conformidade");
        
        String strFrom  = "nao_conformidades";
        String strWhere = "oid_nao_conformidade = " +Integer.parseInt(oid_Nao_Conformidade);
        
        return new BancoUtil().doExiste(strFrom, strWhere);
    }
}