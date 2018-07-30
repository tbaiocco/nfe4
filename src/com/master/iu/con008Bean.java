package com.master.iu;
import javax.servlet.http.HttpServletRequest;

import com.master.ed.Calcula_FreteED;
import com.master.rn.Calcula_FreteRN;
import com.master.util.Excecoes;
import com.master.util.Utilitaria;

public class con008Bean  {

  Utilitaria util = new Utilitaria();

    public Calcula_FreteED calcula_frete(HttpServletRequest request) throws Excecoes {

        try {
            Calcula_FreteRN Calcula_Fretern = new Calcula_FreteRN();
            Calcula_FreteED ed = new Calcula_FreteED();

            ed.setDM_Responsavel_Cobranca(request.getParameter("FT_DM_Responsavel_Cobranca"));
            ed.setDM_Tipo_Pagamento(request.getParameter("FT_DM_Tipo_Pagamento"));
            ed.setDM_Tipo_Conhecimento(request.getParameter("FT_DM_Tipo_Conhecimento"));
            ed.setDM_Tipo_Tabela_Frete(request.getParameter("FT_DM_Tipo_Tabela_Frete"));
            ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));
            ed.setOID_Cidade_Origem(new Long(request.getParameter("oid_Cidade_Origem")).longValue());
            ed.setOID_Estado_Origem(new Long(request.getParameter("oid_Estado_Origem")).longValue());
            ed.setOID_Regiao_Origem(new Long(request.getParameter("oid_Regiao_Origem")).longValue());
            ed.setOID_Cidade_Destino(new Long(request.getParameter("oid_Cidade_Destino")).longValue());
            ed.setOID_Estado_Destino(new Long(request.getParameter("oid_Estado_Destino")).longValue());
            ed.setOID_Regiao_Destino(new Long(request.getParameter("oid_Regiao_Destino")).longValue());
            ed.setOID_Modal(new Long(request.getParameter("oid_Modal")).longValue());
            ed.setOID_Pessoa(request.getParameter("oid_Pessoa_Remetente"));
            ed.setOID_Pessoa_Consignatario(request.getParameter("oid_Pessoa_Consignatario"));
            ed.setOID_Pessoa_Destinatario(request.getParameter("oid_Pessoa_Destinatario"));
            ed.setOID_Pessoa_Pagador(request.getParameter("oid_Pessoa_Pagador"));
            ed.setOID_Pessoa_Pagador_Tabela(request.getParameter("oid_Pessoa_Pagador"));
            ed.setOID_Produto(new Long(request.getParameter("oid_Produto")).longValue());
            ed.setOID_Unidade(new Long(request.getParameter("oid_Unidade")).longValue());
            ed.setOID_Conhecimento(request.getParameter("oid_Conhecimento"));
            ed.setOID_Cidade_Remetente(new Long(request.getParameter("oid_Cidade_Remetente")).longValue());
            ed.setOID_Cidade_Destinatario(new Long(request.getParameter("oid_Cidade_Destinatario")).longValue());
            ed.setOID_Cidade_Consignatario(new Long(request.getParameter("oid_Cidade_Consignatario")).longValue());
            ed.setDM_Tipo_Tarifa(request.getParameter("FT_DM_Tipo_Tarifa"));

            String VL_Peso_Cubado = request.getParameter("FT_VL_Peso_Cubado");
            if (String.valueOf(VL_Peso_Cubado) != null && !String.valueOf(VL_Peso_Cubado).equals("")) {
                ed.setVL_Peso_Cubado(new Double(VL_Peso_Cubado).doubleValue());
            }

            return Calcula_Fretern.calcula_frete(ed);
        } catch (Excecoes exc) {
            throw exc;
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("erro ao incluir");
            excecoes.setMetodo("inclui");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

    public Calcula_FreteED calcula_frete_Entregadora(HttpServletRequest request) throws Excecoes {

        try {
            Calcula_FreteRN Calcula_Fretern = new Calcula_FreteRN();
            Calcula_FreteED ed = new Calcula_FreteED();

            ed.setDM_Responsavel_Cobranca(request.getParameter("FT_DM_Responsavel_Cobranca"));
            ed.setDM_Tipo_Pagamento(request.getParameter("FT_DM_Tipo_Pagamento"));
            ed.setDM_Tipo_Tabela_Frete(request.getParameter("FT_DM_Tipo_Tabela_Frete"));
            ed.setDM_Tipo_Conhecimento(request.getParameter("FT_DM_Tipo_Conhecimento"));
            ed.setDT_Emissao(request.getParameter("FT_DT_Emissao"));
            ed.setDT_Previsao_Entrega(request.getParameter("FT_DT_Previsao_Entrega"));
            ed.setOID_Cidade_Origem(new Long(request.getParameter("oid_Cidade_Origem")).longValue());
            ed.setOID_Estado_Origem(new Long(request.getParameter("oid_Estado_Origem")).longValue());
            ed.setOID_Regiao_Origem(new Long(request.getParameter("oid_Regiao_Origem")).longValue());

            ed.setOID_Regiao_Destino(new Long(request.getParameter("oid_Regiao_Destino")).longValue());

            String OID_Subregiao_Origem = request.getParameter("oid_Subregiao_Origem");
            if (String.valueOf(OID_Subregiao_Origem) != null && !String.valueOf(OID_Subregiao_Origem).equals("") && !String.valueOf(OID_Subregiao_Origem).equals("null")) {
                ed.setOID_Subregiao_Origem(new Long(request.getParameter("oid_Subregiao_Origem")).longValue());
            }

            String OID_Subregiao_Destino = request.getParameter("oid_Subregiao_Destino");
            if (String.valueOf(OID_Subregiao_Destino) != null && !String.valueOf(OID_Subregiao_Destino).equals("") && !String.valueOf(OID_Subregiao_Destino).equals("null")) {
                ed.setOID_Subregiao_Destino(new Long(request.getParameter("oid_Subregiao_Destino")).longValue());
            }

            ed.setOID_Cidade_Destino(new Long(request.getParameter("oid_Cidade_Destino")).longValue());
            ed.setOID_Estado_Destino(new Long(request.getParameter("oid_Estado_Destino")).longValue());
            ed.setOID_Modal(new Long(request.getParameter("oid_Modal")).longValue());
            ed.setOID_Pessoa(request.getParameter("oid_Pessoa_Remetente"));
            ed.setOID_Pessoa_Consignatario(request.getParameter("oid_Pessoa_Consignatario"));

            ed.setOID_Pessoa_Destinatario(request.getParameter("oid_Pessoa_Destinatario"));
            ed.setOID_Pessoa_Pagador(request.getParameter("oid_Pessoa_Pagador"));
            ed.setOID_Pessoa_Pagador_Tabela(request.getParameter("oid_Pessoa_Pagador"));
            ed.setOID_Pessoa_Entregador(request.getParameter("oid_Pessoa_Entregadora"));

            ed.setOID_Produto(new Long(request.getParameter("oid_Produto")).longValue());
            ed.setOID_Unidade(new Long(request.getParameter("oid_Unidade")).longValue());
            ed.setOID_Conhecimento(request.getParameter("oid_Conhecimento"));
            ed.setOID_Cidade_Remetente(new Long(request.getParameter("oid_Cidade_Remetente")).longValue());
            ed.setOID_Cidade_Destinatario(new Long(request.getParameter("oid_Cidade_Destinatario")).longValue());
            ed.setOID_Cidade_Consignatario(new Long(request.getParameter("oid_Cidade_Consignatario")).longValue());


            String VL_Peso_Cubado = request.getParameter("FT_VL_Peso_Cubado");
            if (String.valueOf(VL_Peso_Cubado) != null && !String.valueOf(VL_Peso_Cubado).equals("")) {
                ed.setVL_Peso_Cubado(new Double(VL_Peso_Cubado).doubleValue());
            }
            String OID_Empresa = request.getParameter("oid_Empresa");
            if (String.valueOf(OID_Empresa) != null && !String.valueOf(OID_Empresa).equals("") && !String.valueOf(OID_Empresa).equals("null")) {
                ed.setOID_Empresa(new Long(request.getParameter("oid_Empresa")).longValue());
            }
            String FT_DM_Tipo_Coleta = request.getParameter("FT_DM_Tipo_Coleta");
            if (String.valueOf(FT_DM_Tipo_Coleta) != null && !String.valueOf(FT_DM_Tipo_Coleta).equals("") && !String.valueOf(FT_DM_Tipo_Coleta).equals("null")) {
                ed.setDM_Tipo_Coleta(request.getParameter("FT_DM_Tipo_Coleta"));
            }
            String FT_DM_Tipo_Entrega = request.getParameter("FT_DM_Tipo_Entrega");
            if (String.valueOf(FT_DM_Tipo_Entrega) != null && !String.valueOf(FT_DM_Tipo_Entrega).equals("") && !String.valueOf(FT_DM_Tipo_Entrega).equals("null")) {
                ed.setDM_Tipo_Entrega(request.getParameter("FT_DM_Tipo_Entrega"));
            }

            return Calcula_Fretern.calcula_frete(ed);
        } catch (Excecoes exc) {
            throw exc;
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("erro ao incluir");
            excecoes.setMetodo("inclui");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

}
