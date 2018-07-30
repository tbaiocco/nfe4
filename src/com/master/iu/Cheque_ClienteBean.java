package com.master.iu;

import java.io.Serializable;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import com.master.ed.Cheque_ClienteED;
import com.master.ed.UsuarioED;
import com.master.rn.Cheque_ClienteRN;
import com.master.root.UnidadeBean;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;

/**
 * @author André Valadas
 * @serial Cheques Clientes
 * @serialData 04/04/2005
 */
public class Cheque_ClienteBean extends JavaUtil implements Serializable {

	public Cheque_ClienteED inclui(HttpServletRequest request) throws Exception {

	    String oid_Cliente = request.getParameter("oid_Cliente");
	    String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
	    String oid_Entrega = request.getParameter("oid_Entrega");
	    String oid_Banco = request.getParameter("oid_Banco");
        String oid_Conta_Corrente_Unidade = request.getParameter("oid_Conta_Corrente");
	    String NM_Pessoa_Emissor = request.getParameter("FT_NM_Pessoa_Emissor");
	    
        String NR_Comp = request.getParameter("FT_NR_Comp");
        String NR_Agencia = request.getParameter("FT_NR_Agencia");
        String NR_C1 = request.getParameter("FT_NR_C1");
        String NR_Conta = request.getParameter("FT_NR_Conta");
        String NR_C2 = request.getParameter("FT_NR_C2");
        String NR_Cheque = request.getParameter("FT_NR_Cheque");
        String NR_C3 = request.getParameter("FT_NR_C3");
        String VL_Cheque = request.getParameter("FT_VL_Cheque");
        String DT_Programado = request.getParameter("FT_DT_Programado");
	    String DM_Origem = request.getParameter("FT_DM_Origem");
	    String DT_Entrada = request.getParameter("FT_DT_Entrada");
        
        //*** Inclusão apartir de um Lote de Recebimento
        String oid_Lote_Recebimento = request.getParameter("oid_Lote_Recebimento");
	    
		//*** Validações
		if (!doValida(oid_Cliente))
		    throw new Excecoes("Cliente não informado!");
		if (!doValida(oid_Banco))
		    throw new Excecoes("Banco não informado!");
		if (!doValida(NM_Pessoa_Emissor))
		    throw new Excecoes("Emissor não informado!");
		
		Cheque_ClienteED ed = new Cheque_ClienteED();
		ed.setOid_Cliente(oid_Cliente);
		ed.setOid_Banco(Integer.parseInt(oid_Banco));
		ed.setNM_Pessoa_Emissor(NM_Pessoa_Emissor);

        if (doValida(oid_Nota_Fiscal))
            ed.setOid_Nota_Fiscal(oid_Nota_Fiscal);
        if (doValida(oid_Entrega))
            ed.setOid_Entrega(Integer.parseInt(oid_Entrega));
        if (doValida(oid_Conta_Corrente_Unidade))
            ed.setOid_Conta_Corrente_Unidade(oid_Conta_Corrente_Unidade);
        else {
            UsuarioED edUser = (UsuarioED) request.getSession().getAttribute("usuario");
            ed.setOid_Conta_Corrente_Unidade(UnidadeBean.getByOID_Unidade(edUser.getOid_Unidade().intValue()).getOID_Conta_Corrente());
        }
        if (doValida(NR_Comp))
            ed.setNR_Comp(NR_Comp);
		if (doValida(NR_Agencia))
		    ed.setNR_Agencia(NR_Agencia);
        if (doValida(NR_C1))
            ed.setNR_C1(NR_C1);
		if (doValida(NR_Conta))
		    ed.setNR_Conta(NR_Conta);
        if (doValida(NR_C2))
            ed.setNR_C2(NR_C2);
		if (doValida(NR_Cheque))
		    ed.setNR_Cheque(NR_Cheque);
        if (doValida(NR_C3))
            ed.setNR_C3(NR_C3);
		if (doValida(DT_Programado))
		    ed.setDT_Programado(DT_Programado);
		if (doValida(VL_Cheque))
		    ed.setVL_Cheque(Double.parseDouble(VL_Cheque));
		if (doValida(DM_Origem))
		    ed.setDM_Origem(DM_Origem);
		if (doValida(DT_Entrada))
		    ed.setDT_Entrada(DT_Entrada);
        if (doValida(oid_Lote_Recebimento))
            ed.setOid_Lote_Recebimento(Integer.parseInt(oid_Lote_Recebimento));
		
		return new Cheque_ClienteRN().inclui(ed);
	}

	public void altera(HttpServletRequest request) throws Exception {

	    String oid_Cheque_Cliente = request.getParameter("oid_Cheque_Cliente");
	    String oid_Banco = request.getParameter("oid_Banco");
        String oid_Entrega = request.getParameter("oid_Entrega");
        String oid_Conta_Corrente_Unidade = request.getParameter("oid_Conta_Corrente");
	    String NM_Pessoa_Emissor = request.getParameter("FT_NM_Pessoa_Emissor");
	    
        String NR_Comp = request.getParameter("FT_NR_Comp");
        String NR_Agencia = request.getParameter("FT_NR_Agencia");
        String NR_C1 = request.getParameter("FT_NR_C1");
        String NR_Conta = request.getParameter("FT_NR_Conta");
        String NR_C2 = request.getParameter("FT_NR_C2");
        String NR_Cheque = request.getParameter("FT_NR_Cheque");
        String NR_C3 = request.getParameter("FT_NR_C3");
	    String VL_Cheque = request.getParameter("FT_VL_Cheque");
        String DT_Programado = request.getParameter("FT_DT_Programado");
        String DM_Origem = request.getParameter("FT_DM_Origem");

	    //*** Validações
	    if (!doValida(oid_Cheque_Cliente))
		    throw new Excecoes("Cheque Cliente não informado!");
		if (!doValida(oid_Banco))
		    throw new Excecoes("Banco não informado!");
		if (!doValida(NM_Pessoa_Emissor))
		    throw new Excecoes("Emissor não informado!");
	    
		Cheque_ClienteED ed = new Cheque_ClienteED(Integer.parseInt(oid_Cheque_Cliente));
		ed.setOid_Banco(Integer.parseInt(oid_Banco));
		ed.setNM_Pessoa_Emissor(NM_Pessoa_Emissor);

        if (doValida(oid_Entrega))
            ed.setOid_Entrega(Integer.parseInt(oid_Entrega));
        if (doValida(oid_Conta_Corrente_Unidade))
            ed.setOid_Conta_Corrente_Unidade(oid_Conta_Corrente_Unidade);
        else {
            UsuarioED edUser = (UsuarioED) request.getSession().getAttribute("usuario");
            ed.setOid_Conta_Corrente_Unidade(UnidadeBean.getByOID_Unidade(edUser.getOid_Unidade().intValue()).getOID_Conta_Corrente());
        }
        if (doValida(NR_Comp))
            ed.setNR_Comp(NR_Comp);
		if (doValida(NR_Agencia))
		    ed.setNR_Agencia(NR_Agencia);
        if (doValida(NR_C1))
            ed.setNR_C1(NR_C1);
		if (doValida(NR_Conta))
		    ed.setNR_Conta(NR_Conta);
        if (doValida(NR_C2))
            ed.setNR_C2(NR_C2);
		if (doValida(NR_Cheque))
		    ed.setNR_Cheque(NR_Cheque);
        if (doValida(NR_C3))
            ed.setNR_C3(NR_C3);
		if (doValida(DT_Programado))
		    ed.setDT_Programado(DT_Programado);
		if (doValida(VL_Cheque))
		    ed.setVL_Cheque(Double.parseDouble(VL_Cheque));
		if (doValida(DM_Origem))
		    ed.setDM_Origem(DM_Origem);
		
		new Cheque_ClienteRN().altera(ed);
	}

	public void deleta(HttpServletRequest request) throws Excecoes {

		String oid_Cheque_Cliente = request.getParameter("oid_Cheque_Cliente");
        String oid_Entrega = request.getParameter("oid_Entrega");
		if (!doValida(oid_Cheque_Cliente))
		    throw new Mensagens("ID Cheque Cliente não informado!");
        Cheque_ClienteED edCheque = new Cheque_ClienteED(Integer.parseInt(oid_Cheque_Cliente));
        if (doValida(oid_Entrega))
            edCheque.setOid_Entrega(Integer.parseInt(oid_Entrega));
		new Cheque_ClienteRN().deleta(edCheque);
	}

	public ArrayList lista(HttpServletRequest request) throws Excecoes {

		return this.lista(request, true, true, true, true, true, true);
	}
    
    public ArrayList lista(HttpServletRequest request, boolean banco, boolean cCorrente, boolean cliente, boolean entrega, boolean nota, boolean motivo_dev) throws Excecoes {

        String oid_Cheque_Cliente = request.getParameter("oid_Cheque_Cliente");
        String oid_Cliente = request.getParameter("oid_Cliente");
        String oid_Nota_Fiscal = request.getParameter("oid_Nota_Fiscal");
        String oid_Entrega = request.getParameter("oid_Entrega");
        String oid_Banco = request.getParameter("oid_Banco");
        String oid_Conta_Corrente = request.getParameter("oid_Conta_Corrente");
        String oid_Conta_Corrente_Unidade = request.getParameter("oid_Conta_Corrente_Unidade");
        String NM_Pessoa_Emissor = request.getParameter("FT_NM_Pessoa_Emissor");
        String DM_Origem = request.getParameter("FT_DM_Origem");
        String DM_Situacao = request.getParameter("FT_DM_Situacao");
        String oid_Motivo_Devolucao = request.getParameter("oid_Motivo_Devolucao");
        
        String NR_Cheque = request.getParameter("FT_NR_Cheque");
        String VL_Cheque = request.getParameter("FT_VL_Cheque");
        String DT_Entrada = request.getParameter("FT_DT_Entrada");
        String DT_Entrada_Final = request.getParameter("FT_DT_Entrada_Final");
        String DT_Programado = request.getParameter("FT_DT_Programado");
        String DT_Programado_Final = request.getParameter("FT_DT_Programado_Final");
        String DT_Compensacao = request.getParameter("FT_DT_Compensacao");
        String DT_Compensacao_Final = request.getParameter("FT_DT_Compensacao_Final");
        String DT_Apresentacao1 = request.getParameter("FT_DT_Apresentacao1");
        String DT_Apresentacao1_Final = request.getParameter("FT_DT_Apresentacao1_Final");
        String DT_Apresentacao2 = request.getParameter("FT_DT_Apresentacao2");
        String DT_Apresentacao2_Final = request.getParameter("FT_DT_Apresentacao2_Final");
        
        Cheque_ClienteED ed = new Cheque_ClienteED(banco, cCorrente, cliente, entrega, nota, motivo_dev);
        //*** Validações
        if (doValida(oid_Cheque_Cliente))
            ed.setOid_Cheque_Cliente(Integer.parseInt(oid_Cheque_Cliente));
        if (doValida(oid_Cliente))
            ed.setOid_Cliente(oid_Cliente);
        if (doValida(oid_Nota_Fiscal))
            ed.setOid_Nota_Fiscal(oid_Nota_Fiscal);
        if (doValida(oid_Entrega))
            ed.setOid_Entrega(Integer.parseInt(oid_Entrega));
        if (doValida(oid_Banco))
            ed.setOid_Banco(Integer.parseInt(oid_Banco));
        if (doValida(oid_Conta_Corrente))
            ed.setOid_Conta_Corrente(oid_Conta_Corrente);
        if (doValida(oid_Conta_Corrente_Unidade))
            ed.setOid_Conta_Corrente_Unidade(oid_Conta_Corrente_Unidade);
        if (doValida(NM_Pessoa_Emissor))
            ed.setNM_Pessoa_Emissor(NM_Pessoa_Emissor);
        if (doValida(DM_Origem))
            ed.setDM_Origem(DM_Origem);
        if (doValida(DM_Situacao))
            ed.setDM_Situacao(DM_Situacao);
        if (doValida(oid_Motivo_Devolucao))
            ed.setOid_Motivo_Devolucao(Integer.parseInt(oid_Motivo_Devolucao));
        if (doValida(VL_Cheque))
            ed.setVL_Cheque(Double.parseDouble(VL_Cheque));
        if (doValida(NR_Cheque))
            ed.setNR_Cheque(NR_Cheque);
        if (doValida(DT_Entrada))
            ed.setDT_Entrada(DT_Entrada);
        if (doValida(DT_Entrada_Final))
            ed.setDT_Entrada_Final(DT_Entrada_Final);
        if (doValida(DT_Programado))
            ed.setDT_Programado(DT_Programado);
        if (doValida(DT_Programado_Final))
            ed.setDT_Programado_Final(DT_Programado_Final);
        if (doValida(DT_Compensacao))
            ed.setDT_Compensacao(DT_Compensacao);
        if (doValida(DT_Compensacao_Final))
            ed.setDT_Compensacao_Final(DT_Compensacao_Final);
        if (doValida(DT_Apresentacao1))
            ed.setDT_Apresentacao1(DT_Apresentacao1);
        if (doValida(DT_Apresentacao1_Final))
            ed.setDT_Apresentacao1_Final(DT_Apresentacao1_Final);
        if (doValida(DT_Apresentacao2))
            ed.setDT_Apresentacao2(DT_Apresentacao2);
        if (doValida(DT_Apresentacao2_Final))
            ed.setDT_Apresentacao2_Final(DT_Apresentacao2_Final);

        return new Cheque_ClienteRN().lista(ed);
    }
    
    public ArrayList listaSemLote(HttpServletRequest request) throws Excecoes {

        String NR_Cheque = request.getParameter("FT_NR_Cheque");
        String NM_Pessoa_Emissor = request.getParameter("FT_NM_Pessoa_Emissor");
        
        Cheque_ClienteED ed = new Cheque_ClienteED(false, false, true, false, false, false);
        ed.setSemLote(true);
        ed.setSemEntrega(false);
        ed.setDM_Apresentacao("S");
        if (doValida(NR_Cheque))
            ed.setNR_Cheque(NR_Cheque);
        if (doValida(NM_Pessoa_Emissor))
            ed.setNM_Pessoa_Emissor(NM_Pessoa_Emissor);
        
        return new Cheque_ClienteRN().listaSemLote(ed);
    }

    public Cheque_ClienteED getByOidSemLote(String oid_Cheque_Cliente, boolean banco, boolean cCorrente, boolean cliente, boolean entrega, boolean nota, boolean motivo_dev) throws Excecoes {

        if (!doValida(oid_Cheque_Cliente))
            throw new Mensagens("ID Cheque Cliente não informado!");
        return new Cheque_ClienteRN().getByRecordSemLote(new Cheque_ClienteED(Integer.parseInt(oid_Cheque_Cliente), banco, cCorrente, cliente, entrega, nota, motivo_dev));
    }
    public Cheque_ClienteED getByOidSemLote(String oid_Cheque_Cliente) throws Excecoes {

        if (!doValida(oid_Cheque_Cliente))
            throw new Mensagens("ID Cheque Cliente não informado!");
        return new Cheque_ClienteRN().getByRecordSemLote(new Cheque_ClienteED(Integer.parseInt(oid_Cheque_Cliente), true, true, true, true, true, true));
    }
    
	public Cheque_ClienteED getByOid(String oid_Cheque_Cliente) throws Excecoes {

		return this.getByOid(oid_Cheque_Cliente, true, true, true, true, true, true);
	}
    
    public Cheque_ClienteED getByOid(String oid_Cheque_Cliente, boolean banco, boolean cCorrente, boolean cliente, boolean entrega, boolean nota, boolean motivo_dev) throws Excecoes {

        if (!doValida(oid_Cheque_Cliente))
            throw new Mensagens("ID Cheque Cliente não informado!");
        return new Cheque_ClienteRN().getByRecord(new Cheque_ClienteED(Integer.parseInt(oid_Cheque_Cliente), banco, cCorrente, cliente, entrega, nota, motivo_dev));
    }
    
	public int getOid_Documento(String oid_Entrega, String oid_Nota_Fiscal) throws Excecoes {
	    
	    if (!doValida(oid_Entrega))
	        throw new Mensagens("ID Entrega não informado!");
	    if (!doValida(oid_Nota_Fiscal))
	        throw new Mensagens("ID Nota Fiscal não informado!");
	    
	    return new BancoUtil().getTableIntValue("oid_Documento_Entrega",
                	            				"Documentos_Entregas",
                	            				"oid_Entrega = "+oid_Entrega+
                	            				" AND oid_Nota_Fiscal = '"+oid_Nota_Fiscal+"'");
	}
    
    /** Malote Eletrônico - BIU **/
    public String geraMaloteEletronico(HttpServletRequest request) throws Excecoes {

        String oid_Unidade = request.getParameter("oid_Unidade");
        String DT_Entrada = request.getParameter("FT_DT_Entrada");
        String DT_Entrada_Final = request.getParameter("FT_DT_Entrada_Final");

        //*** Validações
        if (!doValida(oid_Unidade))
            throw new Mensagens("Unidade não informada!");
        if (!doValida(DT_Entrada) && !doValida(DT_Entrada_Final))
            throw new Mensagens("Período para consulta não informado!");
        
        Cheque_ClienteED ed = new Cheque_ClienteED();
        ed.edEntrega.setOid_Unidade(Integer.parseInt(oid_Unidade));

        if (doValida(DT_Entrada))
            ed.setDT_Entrada(DT_Entrada);
        if (doValida(DT_Entrada_Final))
            ed.setDT_Entrada_Final(DT_Entrada_Final);
        
        return new Cheque_ClienteRN().geraMaloteEletronico(ed);
    }
}