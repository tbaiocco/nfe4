package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.RelatorioED;
import com.master.ed.Tabela_VendaED;
import com.master.rn.Tabela_VendaRN;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.Mensagens;

/**
 * @author André Valadas
 * @serial Tabelas de Venda
 * @serialData 11/04/2005
 */
public class Tabela_VendaBean extends BancoUtil implements Serializable {

	public Tabela_VendaED inclui(HttpServletRequest request) throws Excecoes {

        String NR_Tabela = request.getParameter("FT_NR_Tabela");
	    String oid_Tipo_Tabela_Venda = request.getParameter("oid_Tipo_Tabela_Venda");
	    String oid_Pessoa = request.getParameter("oid_Pessoa");
	    String DT_Vigencia = request.getParameter("FT_DT_Vigencia");
		String DT_Validade = request.getParameter("FT_DT_Validade");

		//*** Validações
        if (!doValida(NR_Tabela))
            throw new Mensagens("Nº da Tabela de Venda não informado!");
        if (doExiste("Tabelas_Vendas", "NR_Tabela = "+Integer.parseInt(NR_Tabela)))
            throw new Mensagens("Já existe uma Tabela de Venda com o Número informado!");
		if (!doValida(oid_Tipo_Tabela_Venda))
		    throw new Mensagens("Tipo Tabela de Venda não informado!");
		if (!doValida(oid_Pessoa))
		    throw new Mensagens("Distribuidora não informada!");
		if (!doValida(DT_Vigencia))
		    throw new Mensagens("Data de Vigência não informada!");
		if (!doValida(DT_Validade))
		    throw new Mensagens("Data de Validade não informada!");
		
		Tabela_VendaED ed = new Tabela_VendaED();
        ed.setNR_Tabela(Integer.parseInt(NR_Tabela));
		ed.setOid_Tipo_Tabela_Venda(Integer.parseInt(oid_Tipo_Tabela_Venda));
		ed.setOid_Pessoa(oid_Pessoa);
		ed.setDT_Vigencia(DT_Vigencia);
		ed.setDT_Validade(DT_Validade);
		ed.setDT_Inclusao(Data.getDataDMY());

		return new Tabela_VendaRN().inclui(ed);
	}

	public void altera(HttpServletRequest request) throws Excecoes {

		String oid_Tabela_Venda = request.getParameter("oid_Tabela_Venda");
		String DT_Validade = request.getParameter("FT_DT_Validade");
		String DT_Vigencia = request.getParameter("FT_DT_Vigencia");

		//*** Validações
		if (!doValida(oid_Tabela_Venda))
		    throw new Mensagens("ID Tabela de Venda não informado!");
		if (!doValida(DT_Vigencia))
		    throw new Mensagens("Data de Vigência não informada!");
		if (!doValida(DT_Validade))
		    throw new Mensagens("Data de Validade não informada!");

		Tabela_VendaED ed = new Tabela_VendaED();
		ed.setOid_Tabela_Venda(Integer.parseInt(oid_Tabela_Venda));
		ed.setDT_Validade(DT_Validade);
		ed.setDT_Vigencia(DT_Vigencia);
		
		new Tabela_VendaRN().altera(ed);
	}

	public void deleta(HttpServletRequest request) throws Excecoes {

		String oid_Tabela_Venda = request.getParameter("oid_Tabela_Venda");
		if (!doValida(oid_Tabela_Venda))
		    throw new Mensagens("ID Tabela de Venda não informado!");
		
		new Tabela_VendaRN().deleta(new Tabela_VendaED(Integer.parseInt(oid_Tabela_Venda)));
	}

	public ArrayList lista(HttpServletRequest request) throws Excecoes {

	    String oid_Tabela_Venda = request.getParameter("oid_Tabela_Venda");
	    String oid_Tipo_Tabela_Venda = request.getParameter("oid_Tipo_Tabela_Venda");
	    String oid_Pessoa = request.getParameter("oid_Pessoa");
	    String NR_Tabela = request.getParameter("FT_NR_Tabela");
	    String DT_Validade = request.getParameter("FT_DT_Validade");
		String DT_Vigencia = request.getParameter("FT_DT_Vigencia");
		String DT_Validade_Final = request.getParameter("FT_DT_Validade_Final");
		String DT_Vigencia_Final = request.getParameter("FT_DT_Vigencia_Final");

		Tabela_VendaED ed = new Tabela_VendaED();
		if (doValida(oid_Tabela_Venda))
		    ed.setOid_Tabela_Venda(Integer.parseInt(oid_Tabela_Venda));
		if (doValida(oid_Tipo_Tabela_Venda))
		    ed.setOid_Tipo_Tabela_Venda(Integer.parseInt(oid_Tipo_Tabela_Venda));
		if (doValida(oid_Pessoa))
		    ed.setOid_Pessoa(oid_Pessoa);
		if (doValida(NR_Tabela))
		    ed.setNR_Tabela(Integer.parseInt(NR_Tabela));
		if (doValida(DT_Validade))
		    ed.setDT_Validade(DT_Validade);
		if (doValida(DT_Vigencia))
		    ed.setDT_Vigencia(DT_Vigencia);
		if (doValida(DT_Validade_Final))
		    ed.setDT_Validade_Final(DT_Validade_Final);
		if (doValida(DT_Vigencia_Final))
		    ed.setDT_Vigencia_Final(DT_Vigencia_Final);
		
		return new Tabela_VendaRN().lista(ed);
	}

	public Tabela_VendaED getByOid(String oid_Tabela_Venda) throws Excecoes {

		if (!doValida(oid_Tabela_Venda))
		    throw new Mensagens("ID Tabela de Venda não informado!");

		return new Tabela_VendaRN().getByRecord(new Tabela_VendaED(Integer.parseInt(oid_Tabela_Venda)));
	}

	public Tabela_VendaED getByNR_Tabela(String NR_Tabela) throws Excecoes {
		
		//*** Validações
		if (!doValida(NR_Tabela))
		    throw new Mensagens("Número da Tabela de Venda não informado!");
		Tabela_VendaED ed = new Tabela_VendaED();
		ed.setNR_Tabela(Integer.parseInt(NR_Tabela));
		
		return new Tabela_VendaRN().getByRecord(ed);
	}
    
    public Tabela_VendaED getUltimaTabelaByTipo(String oid_PesUnidade, String oid_TipoTabela, boolean tabAtiva) throws Excecoes {
        
        if (!doValida(oid_PesUnidade))
            throw new Mensagens("Unidade não informada!");
        if (!doValida(oid_TipoTabela))
            throw new Mensagens("Tipo de Tabela não informado!");
        
        Tabela_VendaED ed = new Tabela_VendaED();
        ed.setOid_Pessoa(oid_PesUnidade);
        ed.setOid_Tipo_Tabela_Venda(Integer.parseInt(oid_TipoTabela));
        return new Tabela_VendaRN().getUltimaTabelaByTipo(ed, tabAtiva);
    }
	
	public Tabela_VendaED duplicarTabela(HttpServletRequest request) throws Excecoes {

	    String oid_Tipo_Tabela_Venda = request.getParameter("oid_Tipo_Tabela_Venda");
	    String oid_Pessoa = request.getParameter("oid_Pessoa");
	    String DT_Encerramento = request.getParameter("FT_DT_Encerramento");
	    String DT_Vigencia = request.getParameter("FT_DT_Vigencia");
		String DT_Validade = request.getParameter("FT_DT_Validade");
		String PE_Ajuste = request.getParameter("FT_PE_Ajuste");

		//*** Validações
		if (!doValida(oid_Tipo_Tabela_Venda))
		    throw new Mensagens("Tipo Tabela de Venda não informado!");
		if (!doValida(oid_Pessoa))
		    throw new Mensagens("Distribuidora não informada!");
		
		//*** Verifica se Existe tabela para Reajustar(Duplicar)
		if (!doExiste("Tabelas_Vendas",
		        	  "oid_Pessoa = '"+oid_Pessoa+"'"+
		        	  " AND oid_Tipo_Tabela_Venda = "+oid_Tipo_Tabela_Venda))
		    throw new Mensagens("Atenção! Não há Tabela cadastrada com esse TIPO e UNIDADE!");
		if (!doValida(DT_Encerramento))
		    throw new Mensagens("Data de Encerramento não informada!");
		if (!doValida(DT_Vigencia))
		    throw new Mensagens("Data de Vigência não informada!");
		if (!doValida(DT_Validade))
		    throw new Mensagens("Data de Validade não informada!");
		
		Tabela_VendaED ed = new Tabela_VendaED();
        ed.setMasterDetails(request);
		ed.setOid_Tipo_Tabela_Venda(Integer.parseInt(oid_Tipo_Tabela_Venda));
		ed.setOid_Pessoa(oid_Pessoa);
		ed.setDT_Encerramento(DT_Encerramento);
		ed.setDT_Vigencia(DT_Vigencia);
		ed.setDT_Validade(DT_Validade);
		ed.setDT_Inclusao(Data.getDataDMY());
		if (doValida(PE_Ajuste))
		    ed.setPE_Ajuste(Double.parseDouble(PE_Ajuste));
		
		return new Tabela_VendaRN().duplicarTabela(ed);
	}
    
    public Tabela_VendaED reajusteTabela(HttpServletRequest request) throws Excecoes {

        String oid_Tipo_Tabela_Venda = request.getParameter("oid_Tipo_Tabela_Venda");
        String oid_Pessoa = request.getParameter("oid_Pessoa");
        String DT_Encerramento = request.getParameter("FT_DT_Encerramento");
        String DT_Vigencia = request.getParameter("FT_DT_Vigencia");
        String DT_Validade = request.getParameter("FT_DT_Validade");
        String PE_Ajuste = request.getParameter("FT_PE_Ajuste");

        //*** Validações
        if (!doValida(oid_Tipo_Tabela_Venda))
            throw new Mensagens("Tipo Tabela de Venda não informado!");
        if (!doValida(oid_Pessoa))
            throw new Mensagens("Distribuidora não informada!");
        
        //*** Verifica se Existe tabela para Reajustar(Duplicar)
        if (!doExiste("Tabelas_Vendas",
                      "oid_Pessoa = '"+oid_Pessoa+"'"+
                      " AND oid_Tipo_Tabela_Venda = "+oid_Tipo_Tabela_Venda))
            throw new Mensagens("Atenção! Não há Tabela cadastrada com esse TIPO e UNIDADE!");
        if (!doValida(DT_Encerramento))
            throw new Mensagens("Data de Encerramento não informada!");
        if (!doValida(DT_Vigencia))
            throw new Mensagens("Data de Vigência não informada!");
        if (!doValida(DT_Validade))
            throw new Mensagens("Data de Validade não informada!");
        
        Tabela_VendaED ed = new Tabela_VendaED();
        ed.setMasterDetails(request);
        ed.setOid_Tipo_Tabela_Venda(Integer.parseInt(oid_Tipo_Tabela_Venda));
        ed.setOid_Pessoa(oid_Pessoa);
        ed.setDT_Encerramento(DT_Encerramento);
        ed.setDT_Vigencia(DT_Vigencia);
        ed.setDT_Validade(DT_Validade);
        ed.setDT_Inclusao(Data.getDataDMY());
        if (doValida(PE_Ajuste))
            ed.setPE_Ajuste(Double.parseDouble(PE_Ajuste));
        
        return new Tabela_VendaRN().reajusteTabela(ed);
    }
	
	/** RELATÓRIOS --------------------------------------**/
	public void relTabelasPrecos(HttpServletRequest request, HttpServletResponse response) throws Excecoes {

	    String oid_Tabela_Venda = request.getParameter("oid_Tabela_Venda");
	    String oid_Tipo_Tabela_Venda = request.getParameter("oid_Tipo_Tabela_Venda");
	    String oid_Pessoa = request.getParameter("oid_Pessoa");
        String oid_Pessoa_Fornecedor = request.getParameter("oid_Pessoa_Fornecedor");
	    String NR_Tabela = request.getParameter("FT_NR_Tabela");
        String DT_Inclusao = request.getParameter("FT_DT_Inclusao");
	    String DT_Validade = request.getParameter("FT_DT_Validade");
		String DT_Vigencia = request.getParameter("FT_DT_Vigencia");
        String DT_Inclusao_Final = request.getParameter("FT_DT_Inclusao_Final");
		String DT_Validade_Final = request.getParameter("FT_DT_Validade_Final");
		String DT_Vigencia_Final = request.getParameter("FT_DT_Vigencia_Final");
		String Relatorio = request.getParameter("Relatorio");

		if (!doValida(Relatorio))
            throw new Mensagens("Nome do Relatório não informado!");
        if (!doValida(oid_Pessoa))
            throw new Mensagens("Unidade não informada!");
		
        RelatorioED ed = new RelatorioED(response, Relatorio);
		if (doValida(oid_Tabela_Venda))
		    ed.setOid_tabela_venda(Integer.parseInt(oid_Tabela_Venda));
		if (doValida(oid_Tipo_Tabela_Venda))
		    ed.setOid_tipo_tabela_venda(Integer.parseInt(oid_Tipo_Tabela_Venda));
		if (doValida(oid_Pessoa))
		    ed.setOid_pessoa(oid_Pessoa);
        if (doValida(oid_Pessoa_Fornecedor))
            ed.setOid_fornecedor(oid_Pessoa_Fornecedor);
		if (doValida(NR_Tabela))
		    ed.setNr_tabela(Integer.parseInt(NR_Tabela));
        if (doValida(DT_Inclusao))
            ed.setDt_inicial(DT_Inclusao);
        if (doValida(DT_Inclusao_Final))
            ed.setDt_final(DT_Inclusao_Final);
		if (doValida(DT_Validade))
		    ed.setDt_validade(DT_Validade);
		if (doValida(DT_Vigencia))
		    ed.setDt_vigencia(DT_Vigencia);
		if (doValida(DT_Validade_Final))
		    ed.setDt_validade_final(DT_Validade_Final);
		if (doValida(DT_Vigencia_Final))
		    ed.setDt_vigencia_final(DT_Vigencia_Final);
        ed.setDm_alterado(request.getParameter("FT_DM_Alterado"));
		
		new Tabela_VendaRN().relTabelasPrecos(ed);
	}
}