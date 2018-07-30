package com.master.iu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.master.ed.DepreciacaoED;
import com.master.rn.DepreciacaoRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;
import com.master.util.Utilitaria;

public class Ctb037Bean extends JavaUtil {

    public DepreciacaoED inclui(HttpServletRequest request) throws Excecoes {

        DepreciacaoED ed = new DepreciacaoED();

        ed.setOid_Produto_Patrimonio(new Integer(request.getParameter("oid_Produto_Patrimonio")));
        ed.setOid_Unidade(new Integer(request.getParameter("oid_Unidade")));
        ed.setDt_aquisicao(request.getParameter("FT_DT_Aquisicao"));
        ed.setDt_inicio_depreciacao(request.getParameter("FT_DT_Inicio_Depreciacao"));
        ed.setDm_situacao(request.getParameter("FT_DM_Situacao"));
        ed.setOid_pessoa(request.getParameter("oid_Pessoa"));
        ed.setNr_nota_fiscal(new Long(request.getParameter("FT_NR_Nota_Fiscal")).longValue());
        ed.setNr_quantidade(new Long(request.getParameter("FT_NR_Quantidade")).longValue());
        ed.setVl_original(new Double(request.getParameter("FT_VL_Original")).doubleValue());
        ed.setTx_observacao_depreciacao(request.getParameter("FT_TX_Observacao_Depreciacao"));
        ed.setOid_conta_devedora(new Long(request.getParameter("oid_Conta_Devedora")).longValue());

        return new DepreciacaoRN().inclui(ed);
    }

    public void altera(HttpServletRequest request) throws Excecoes {

        DepreciacaoED ed = new DepreciacaoED();

        ed.setOid_Depreciacao(request.getParameter("oid_Depreciacao"));
        ed.setOid_Produto_Patrimonio(new Integer(request.getParameter("oid_Produto_Patrimonio")));
        ed.setOid_Unidade(new Integer(request.getParameter("oid_Unidade")));
        ed.setOid_pessoa(request.getParameter("oid_Pessoa"));
        ed.setDt_aquisicao(request.getParameter("FT_DT_Aquisicao"));
        ed.setDt_inicio_depreciacao(request.getParameter("FT_DT_Inicio_Depreciacao"));
        ed.setDm_situacao(request.getParameter("FT_DM_Situacao"));
        ed.setNr_nota_fiscal(new Long(request.getParameter("FT_NR_Nota_Fiscal")).longValue());
        ed.setNr_quantidade(new Long(request.getParameter("FT_NR_Quantidade")).longValue());
        ed.setVl_original(new Double(request.getParameter("FT_VL_Original")).doubleValue());
        ed.setTx_observacao_depreciacao(request.getParameter("FT_TX_Observacao_Depreciacao"));
        ed.setOid_conta_devedora(new Long(request.getParameter("oid_Conta_Devedora")).longValue());

        new DepreciacaoRN().altera(ed);
    }

    public void vender(HttpServletRequest request) throws Excecoes {

        DepreciacaoED ed = new DepreciacaoED();

        ed.setOid_Depreciacao(request.getParameter("oid_Depreciacao"));
        ed.setVl_total_depreciacao(new Double(request.getParameter("FT_VL_Total_Depreciacao")).doubleValue());
        ed.setVl_venda(new Double(request.getParameter("FT_VL_Venda")).doubleValue());
        ed.setVl_ganho_perda(new Double(request.getParameter("FT_VL_Ganho_Perda")).doubleValue());
        ed.setDt_venda(request.getParameter("FT_DT_Venda"));
        ed.setTx_observacao(request.getParameter("FT_TX_Observacao"));

        new DepreciacaoRN().vender(ed);
    }

    public void gerarDepreciacao(HttpServletRequest request) throws Excecoes {

        DepreciacaoED ed = new DepreciacaoED();

        ed.setOid_Depreciacao(request.getParameter("oid_Depreciacao"));

        new DepreciacaoRN().gerarDepreciacao(ed);
    }

    public void deleta(HttpServletRequest request) throws Excecoes {

        DepreciacaoED ed = new DepreciacaoED();

        ed.setOid_Depreciacao(request.getParameter("oid_Depreciacao"));

        new DepreciacaoRN().deleta(ed);
    }

    public ArrayList Depreciacao_Lista(HttpServletRequest request) throws Excecoes {

        DepreciacaoED ed = new DepreciacaoED();

        String Oid_Depreciacao = request.getParameter("oid_Depreciacao");
        if (Oid_Depreciacao != null && !Oid_Depreciacao.equals(""))
        	ed.setOid_Depreciacao(Oid_Depreciacao);

        String oid_Produto_Patrimonio = request.getParameter("oid_Produto_Patrimonio");
        if (oid_Produto_Patrimonio != null && !oid_Produto_Patrimonio.equals(""))
        	ed.setOid_Produto_Patrimonio(new Integer(oid_Produto_Patrimonio));

        String oid_Unidade = request.getParameter("oid_Unidade");
        if (oid_Unidade != null && !oid_Unidade.equals(""))
        	ed.setOid_Unidade(new Integer(oid_Unidade));

        String oid_Conta_Devedora = request.getParameter("oid_Conta_Devedora");
        if (oid_Conta_Devedora != null && !oid_Conta_Devedora.equals(""))
        	ed.setOid_conta_devedora(new Long(oid_Conta_Devedora).longValue());

        String NR_Nota_Fiscal = request.getParameter("FT_NR_Nota_Fiscal");
        if (NR_Nota_Fiscal != null && !NR_Nota_Fiscal.equals(""))
           ed.setNr_nota_fiscal(new Long(NR_Nota_Fiscal).longValue());

        ed.setOid_pessoa(request.getParameter("oid_Pessoa"));

        ed.setDt_aquisicao_inicial(request.getParameter("FT_DT_Aquisicao_Inicial"));
        ed.setDt_inicio_depreciacao_inicial(request.getParameter("FT_DT_Inicio_Depreciacao_Inicial"));
        ed.setDt_aquisicao_final(request.getParameter("FT_DT_Aquisicao_Final"));
        ed.setDt_inicio_depreciacao_final(request.getParameter("FT_DT_Inicio_Depreciacao_Final"));

        ed.setDt_aquisicao(request.getParameter("FT_DT_Aquisicao"));
        ed.setDt_inicio_depreciacao(request.getParameter("FT_DT_Inicio_Depreciacao"));
        ed.setDm_situacao(request.getParameter("FT_DM_Situacao"));
        ed.setDt_emissao_inicial(request.getParameter("FT_DT_Emissao_Inicial"));
        ed.setDt_emissao_final(request.getParameter("FT_DT_Emissao_Final"));

        return new DepreciacaoRN().lista(ed);

    }

    public ArrayList Depreciacao_Mes_Lista(HttpServletRequest request) throws Excecoes {

        DepreciacaoED ed = new DepreciacaoED();

        String Oid_Depreciacao = request.getParameter("oid_Depreciacao");
        if (Oid_Depreciacao != null && !Oid_Depreciacao.equals(""))
        	ed.setOid_Depreciacao(Oid_Depreciacao);

        return new DepreciacaoRN().Depreciacao_Mes_Lista(ed);

    }

    public ArrayList Depreciacao_Sintetico_Lista(HttpServletRequest request) throws Excecoes {

        DepreciacaoED ed = new DepreciacaoED();

        String Oid_Depreciacao = request.getParameter("oid_Depreciacao");
        if (Oid_Depreciacao != null && !Oid_Depreciacao.equals(""))
        	ed.setOid_Depreciacao(Oid_Depreciacao);

        ed.setDt_emissao_inicial(request.getParameter("FT_DT_Emissao_Inicial"));
        ed.setDt_emissao_final(request.getParameter("FT_DT_Emissao_Final"));

        return new DepreciacaoRN().Depreciacao_Sintetico_Lista(ed);

    }

    public DepreciacaoED getByRecord(HttpServletRequest request) throws Excecoes {

        DepreciacaoED ed = new DepreciacaoED();

        String oid_Depreciacao = request.getParameter("oid_Depreciacao");

        if (oid_Depreciacao != null && oid_Depreciacao.length() > 0) {
            ed.setOid_Depreciacao(oid_Depreciacao);
        }
        return new DepreciacaoRN().getByRecord(ed);
    }

    public DepreciacaoED getByRecord(String oid_Depreciacao) throws Excecoes {

        if (doValida(oid_Depreciacao))
            return new DepreciacaoRN().getByRecord(new DepreciacaoED(oid_Depreciacao));
        else return new DepreciacaoED();
    }

    public DepreciacaoED getByRecordVenda(HttpServletRequest request) throws Excecoes {

        DepreciacaoED ed = new DepreciacaoED();

        String oid_Depreciacao = request.getParameter("oid_Depreciacao");

        if (oid_Depreciacao != null && oid_Depreciacao.length() > 0) {
            ed.setOid_Depreciacao(oid_Depreciacao);
        }
        return new DepreciacaoRN().getByRecordVenda(ed);
    }

    public void relDepreciacao(HttpServletRequest request, HttpServletResponse response) throws Excecoes {

        String Relatorio = request.getParameter ("Relatorio");
        if (!Utilitaria.doValida (Relatorio)) {
            throw new Mensagens ("Nome do Relatório não informado!");
          }

        String descFiltro = "";

        DepreciacaoED ed = new DepreciacaoED(response , Relatorio);

        String Oid_Depreciacao = request.getParameter("oid_Depreciacao");
        if (Oid_Depreciacao != null && !Oid_Depreciacao.equals("")){
        	ed.setOid_Depreciacao(Oid_Depreciacao);
        }

        String oid_Produto_Patrimonio = request.getParameter("oid_Produto_Patrimonio");
        if (oid_Produto_Patrimonio != null && !oid_Produto_Patrimonio.equals("")){
        	ed.setOid_Produto_Patrimonio(new Integer(oid_Produto_Patrimonio));
        	descFiltro += " Prod.: " + request.getParameter("FT_NM_Produto_Patrimonio")+",";
        }

        String oid_Unidade = request.getParameter("oid_Unidade");
        if (oid_Unidade != null && !oid_Unidade.equals("")){
        	ed.setOid_Unidade(new Integer(oid_Unidade));
        	descFiltro += " Un.: " + request.getParameter("FT_NM_Fantasia")+",";
        }

        String NR_Nota_Fiscal = request.getParameter("FT_NR_Nota_Fiscal");
        if (NR_Nota_Fiscal != null && !NR_Nota_Fiscal.equals("")){
        	ed.setNr_nota_fiscal(new Long(NR_Nota_Fiscal).longValue());
        	descFiltro += " NF: " + request.getParameter("FT_NR_Nota_Fiscal")+",";
        }
        String oid_Conta_Devedora = request.getParameter("oid_Conta_Devedora");
        if (oid_Conta_Devedora != null && !oid_Conta_Devedora.equals(""))
        	ed.setOid_conta_devedora(new Long(oid_Conta_Devedora).longValue());

        if(Utilitaria.doValida(request.getParameter("oid_Pessoa"))){
        	ed.setOid_pessoa(request.getParameter("oid_Pessoa"));
        	descFiltro += " Forn.: " + request.getParameter("FT_NM_Razao_Social")+",";
        }
        if(Utilitaria.doValida(request.getParameter("FT_DT_Aquisicao_Inicial"))){
        	ed.setDt_aquisicao_inicial(request.getParameter("FT_DT_Aquisicao_Inicial"));
        	descFiltro += " Aquis. de: " + request.getParameter("FT_DT_Aquisicao_Inicial")+",";
        }
        if(Utilitaria.doValida(request.getParameter("FT_DT_Aquisicao_Final"))){
        	ed.setDt_aquisicao_final(request.getParameter("FT_DT_Aquisicao_Final"));
        	descFiltro += " até: " + request.getParameter("FT_DT_Aquisicao_Final")+",";
        }
        if(Utilitaria.doValida(request.getParameter("FT_DT_Inicio_Depreciacao_Inicial"))){
        	ed.setDt_inicio_depreciacao_inicial(request.getParameter("FT_DT_Inicio_Depreciacao_Inicial"));
        	descFiltro += " Início Dep. de: " + request.getParameter("FT_DT_Inicio_Depreciacao_Inicial")+",";
        }
        if(Utilitaria.doValida(request.getParameter("FT_DT_Inicio_Depreciacao_Final"))){
        	ed.setDt_inicio_depreciacao_final(request.getParameter("FT_DT_Inicio_Depreciacao_Final"));
        	descFiltro += " até: " + request.getParameter("FT_DT_Inicio_Depreciacao_Final")+",";
        }
        if(Utilitaria.doValida(request.getParameter("FT_DT_Aquisicao"))){
        	ed.setDt_aquisicao(request.getParameter("FT_DT_Aquisicao"));
        	descFiltro += " Aquis.: " + request.getParameter("FT_DT_Aquisicao")+",";
        }
        if(Utilitaria.doValida(request.getParameter("FT_DT_Inicio_Depreciacao"))){
        	ed.setDt_inicio_depreciacao(request.getParameter("FT_DT_Inicio_Depreciacao"));
        	descFiltro += " Início Dep.: " + request.getParameter("FT_DT_Inicio_Depreciacao")+",";
        }
        if(Utilitaria.doValida(request.getParameter("FT_DM_Situacao"))){
        	ed.setDm_situacao(request.getParameter("FT_DM_Situacao"));
//        	descFiltro += " Sit.: " + request.getParameter("FT_DM_Situacao");
        }
        if(Utilitaria.doValida(request.getParameter("FT_DT_Emissao_Inicial"))){
        	ed.setDt_emissao_inicial(request.getParameter("FT_DT_Emissao_Inicial"));
        	descFiltro += " Dep. de: " + request.getParameter("FT_DT_Emissao_Inicial")+",";
        }
        if(Utilitaria.doValida(request.getParameter("FT_DT_Emissao_Final"))){
        	ed.setDt_emissao_final(request.getParameter("FT_DT_Emissao_Final"));
        	descFiltro += " até: " + request.getParameter("FT_DT_Emissao_Final");
        }
        ed.setDescFiltro(descFiltro);

        new DepreciacaoRN().relDepreciacao(ed);

    }

}
