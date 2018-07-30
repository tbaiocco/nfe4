package com.master.iu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.CompromissoED;
import com.master.ed.DiarioED;
import com.master.rn.CompromissoRN;
import com.master.rn.DiarioRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;

public class Cob016Bean {

    public void geraDiario_Razao_Clientes(HttpServletRequest request, HttpServletResponse Response) throws Excecoes {

        DiarioED ed = new DiarioED();

        String oid_Pessoa = request.getParameter("oid_Pessoa");
        if (oid_Pessoa != null && !oid_Pessoa.equals(""))
            ed.setOid_Pessoa(oid_Pessoa);

        String dt_Emissao_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
        String dt_Emissao_Final = request.getParameter("FT_DT_Emissao_Final");

        if (dt_Emissao_Inicial != null && !dt_Emissao_Inicial.equals(""))
            ed.setData_Emissao_Inicial(dt_Emissao_Inicial);

        if (dt_Emissao_Final != null && !dt_Emissao_Final.equals(""))
            ed.setData_Emissao_Final(dt_Emissao_Final);

        ed.setDM_Relatorio(request.getParameter("FT_DM_Relatorio"));
        ed.setSO(request.getParameter("FT_DM_SO"));

        String origem = request.getParameter("FT_DM_Origem");
        if(JavaUtil.doValida(origem)){
            ed.setDM_Origem(origem);
        }
        //else throw new Mensagens("Selecione uma Origem para o Diário/Razão!");

        String vl_saldo = request.getParameter("FT_VL_Saldo");
        if(JavaUtil.doValida(vl_saldo)){
        	ed.setVL_Saldo_Inicial(new Double(vl_saldo).doubleValue());
        }

        ed.setNr_Proximo_Numero(request.getParameter("FT_NR_Documento"));
//System.out.println(request.getParameter("FT_NR_Documento"));


        new DiarioRN().geraDiario_Razao_Clientes(ed, Response);

    }

    public void geraDiario_Razao_Fornecedores(HttpServletRequest request, HttpServletResponse res) throws Excecoes {
        CompromissoED ed = new CompromissoED();

        String oid_Pessoa = request.getParameter("oid_Pessoa");
        if (oid_Pessoa != null && !oid_Pessoa.equals(""))
            ed.setOid_Pessoa(oid_Pessoa);

        String oid_Conta = request.getParameter("oid_Conta");
        if (oid_Conta != null && !oid_Conta.equals(""))
            ed.setOid_Conta(new Integer(oid_Conta));

        String dt_Inicial = request.getParameter("FT_DT_Emissao_Inicial");
        if (dt_Inicial != null && !dt_Inicial.equals(""))
            ed.setDt_Inicial(dt_Inicial);

        String dt_Final = request.getParameter("FT_DT_Emissao_Final");
        if (dt_Final != null && !dt_Final.equals(""))
            ed.setDt_Final(dt_Final);

        ed.setDM_Relatorio(request.getParameter("FT_DM_Relatorio"));
        ed.setSO(request.getParameter("FT_DM_SO"));

        String contas = request.getParameter("FT_Contas");
        if (contas != null && !contas.equals("")){
            ed.setNm_Conta(contas);

	        String in_notin = request.getParameter("FT_In_Or_Not");
	        if (JavaUtil.doValida(in_notin))
	            ed.setDM_Situacao(in_notin);
	        else ed.setDM_Situacao("I");
        }

        String vl_saldo = request.getParameter("FT_VL_Saldo");
        if(JavaUtil.doValida(vl_saldo)){
        	ed.setVL_Saldo_Inicial(new Double(vl_saldo).doubleValue());
        }


        new DiarioRN().geraDiario_Razao_Fornecedores(ed, res);

    }

}
