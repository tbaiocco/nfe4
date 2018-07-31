package com.master.ed;

import java.util.*;
import javax.servlet.http.HttpServletResponse;

public class Faturamentos_ConsolidadosED extends RelatorioBaseED{

    private int oid_Faturamento_Consolidado; 
    private String oid_Manifesto_Internacional;
    private String nr_Manifesto_Internacional;
    private String dt_Emissao_Manifesto_Internacional;
    private String oid_Conhecimento;
    private String nr_Conhecimento;
    private String oid_Cliente;
    private double vl_Faturamento;
    private double vl_Total_Faturamento;
    private double vl_Frete_Base;
    private double vl_Total_Frete_Base;
    private double vl_Seguro;
    private double vl_Total_Seguro;
    private double vl_Adicional;
    private double vl_Total_Adicional;
    private double vl_Peso_MIC;
    private double vl_Peso_CRT;
    private double vl_Frete;
    private double vl_Frete_CRT;
    private double vl_Total_Frete_CRT;
    private double vl_Mercadoria;
    private double vl_NF;
    private int oid_Tipo_Faturamento;
    private ArrayList crtLista;
    private String nm_Remetente;
    private String nm_Destinatario;
    private String nr_Fatura;
    private String path_Imagens;
    
    private int oid_Unidade;
    private String cd_Unidade;
    private String nm_Unidade;
    private String nm_Cidade_Unidade;
    
    private String nr_Manifesto_Internacional_Inicial;
    private String nr_Manifesto_Internacional_Final;
    private String dt_Emissao_MIC_Inicial;
    private String dt_Emissao_MIC_Final;
    private String dm_Tipo_Relatorio;
    private String tx_Documento_Anexo;
    
    private String nm_Razao_Social;
    private String nm_Endereco;
    private String nm_Bairro;
    private String nr_CEP;
    private String nm_Cidade;
    private String cd_Estado;
    private String nm_Pais;
    private String nr_CNPJ_CPF;
    private String nm_Inscricao_Estadual;
    private String nr_Fone_Fax;
    private String nm_EMail;

    private String nm_Razao_Social_Cliente;
    private String nm_Endereco_Cliente;
    private String nm_Bairro_Cliente;
    private String nr_CEP_Cliente;
    private String nm_Cidade_Cliente;
    private String cd_Estado_Cliente;
    private String nm_Pais_Cliente;
    private String nr_CNPJ_CPF_Cliente;
    private String nm_Inscricao_Estadual_Cliente;
    private String nr_Fone_Fax_Cliente;
    private String nm_EMail_Cliente;
    
    public Faturamentos_ConsolidadosED() {
        
    }
    
    public Faturamentos_ConsolidadosED(HttpServletResponse response, String nomeRelatorio) {
        super(response, nomeRelatorio);
    }
    
    
    
    public String getNm_Cidade_Unidade() {
        return nm_Cidade_Unidade;
    }
    public void setNm_Cidade_Unidade(String nm_Cidade_Unidade) {
        this.nm_Cidade_Unidade = nm_Cidade_Unidade;
    }
    public String getCd_Unidade() {
        return cd_Unidade;
    }
    public void setCd_Unidade(String cd_Unidade) {
        this.cd_Unidade = cd_Unidade;
    }
    public String getNm_Unidade() {
        return nm_Unidade;
    }
    public void setNm_Unidade(String nm_Unidade) {
        this.nm_Unidade = nm_Unidade;
    }
    public String getTx_Documento_Anexo() {
        return tx_Documento_Anexo;
    }
    public void setTx_Documento_Anexo(String tx_Documento_Anexo) {
        this.tx_Documento_Anexo = tx_Documento_Anexo;
    }
    public String getPath_Imagens() {
        return path_Imagens;
    }
    public void setPath_Imagens(String path_Imagens) {
        this.path_Imagens = path_Imagens;
    }
    public String getCd_Estado_Cliente() {
        return cd_Estado_Cliente;
    }
    public void setCd_Estado_Cliente(String cd_Estado_Cliente) {
        this.cd_Estado_Cliente = cd_Estado_Cliente;
    }
    public String getNm_Bairro_Cliente() {
        return nm_Bairro_Cliente;
    }
    public void setNm_Bairro_Cliente(String nm_Bairro_Cliente) {
        this.nm_Bairro_Cliente = nm_Bairro_Cliente;
    }
    public String getNm_Cidade_Cliente() {
        return nm_Cidade_Cliente;
    }
    public void setNm_Cidade_Cliente(String nm_Cidade_Cliente) {
        this.nm_Cidade_Cliente = nm_Cidade_Cliente;
    }
    public String getNm_EMail_Cliente() {
        return nm_EMail_Cliente;
    }
    public void setNm_EMail_Cliente(String nm_EMail_Cliente) {
        this.nm_EMail_Cliente = nm_EMail_Cliente;
    }
    public String getNm_Endereco_Cliente() {
        return nm_Endereco_Cliente;
    }
    public void setNm_Endereco_Cliente(String nm_Endereco_Cliente) {
        this.nm_Endereco_Cliente = nm_Endereco_Cliente;
    }
    public String getNm_Inscricao_Estadual_Cliente() {
        return nm_Inscricao_Estadual_Cliente;
    }
    public void setNm_Inscricao_Estadual_Cliente(
            String nm_Inscricao_Estadual_Cliente) {
        this.nm_Inscricao_Estadual_Cliente = nm_Inscricao_Estadual_Cliente;
    }
    public String getNm_Pais_Cliente() {
        return nm_Pais_Cliente;
    }
    public void setNm_Pais_Cliente(String nm_Pais_Cliente) {
        this.nm_Pais_Cliente = nm_Pais_Cliente;
    }
    public String getNm_Razao_Social_Cliente() {
        return nm_Razao_Social_Cliente;
    }
    public void setNm_Razao_Social_Cliente(String nm_Razao_Social_Cliente) {
        this.nm_Razao_Social_Cliente = nm_Razao_Social_Cliente;
    }
    public String getNr_CEP_Cliente() {
        return nr_CEP_Cliente;
    }
    public void setNr_CEP_Cliente(String nr_CEP_Cliente) {
        this.nr_CEP_Cliente = nr_CEP_Cliente;
    }
    public String getNr_CNPJ_CPF_Cliente() {
        return nr_CNPJ_CPF_Cliente;
    }
    public void setNr_CNPJ_CPF_Cliente(String nr_CNPJ_CPF_Cliente) {
        this.nr_CNPJ_CPF_Cliente = nr_CNPJ_CPF_Cliente;
    }
    public String getNr_Fone_Fax_Cliente() {
        return nr_Fone_Fax_Cliente;
    }
    public void setNr_Fone_Fax_Cliente(String nr_Fone_Fax_Cliente) {
        this.nr_Fone_Fax_Cliente = nr_Fone_Fax_Cliente;
    }
    public String getCd_Estado() {
        return cd_Estado;
    }
    public void setCd_Estado(String cd_Estado) {
        this.cd_Estado = cd_Estado;
    }
    public String getNm_Bairro() {
        return nm_Bairro;
    }
    public void setNm_Bairro(String nm_Bairro) {
        this.nm_Bairro = nm_Bairro;
    }
    public String getNm_Cidade() {
        return nm_Cidade;
    }
    public void setNm_Cidade(String nm_Cidade) {
        this.nm_Cidade = nm_Cidade;
    }
    public String getNm_EMail() {
        return nm_EMail;
    }
    public void setNm_EMail(String nm_EMail) {
        this.nm_EMail = nm_EMail;
    }
    public String getNm_Endereco() {
        return nm_Endereco;
    }
    public void setNm_Endereco(String nm_Endereco) {
        this.nm_Endereco = nm_Endereco;
    }
    public String getNm_Inscricao_Estadual() {
        return nm_Inscricao_Estadual;
    }
    public void setNm_Inscricao_Estadual(String nm_Inscricao_Estadual) {
        this.nm_Inscricao_Estadual = nm_Inscricao_Estadual;
    }
    public String getNm_Pais() {
        return nm_Pais;
    }
    public void setNm_Pais(String nm_Pais) {
        this.nm_Pais = nm_Pais;
    }
    public String getNm_Razao_Social() {
        return nm_Razao_Social;
    }
    public void setNm_Razao_Social(String nm_Razao_Social) {
        this.nm_Razao_Social = nm_Razao_Social;
    }
    public String getNr_CEP() {
        return nr_CEP;
    }
    public void setNr_CEP(String nr_CEP) {
        this.nr_CEP = nr_CEP;
    }
    public String getNr_CNPJ_CPF() {
        return nr_CNPJ_CPF;
    }
    public void setNr_CNPJ_CPF(String nr_CNPJ_CPF) {
        this.nr_CNPJ_CPF = nr_CNPJ_CPF;
    }
    public String getNr_Fone_Fax() {
        return nr_Fone_Fax;
    }
    public void setNr_Fone_Fax(String nr_Fone_Fax) {
        this.nr_Fone_Fax = nr_Fone_Fax;
    }
    public String getDm_Tipo_Relatorio() {
        return dm_Tipo_Relatorio;
    }
    public void setDm_Tipo_Relatorio(String dm_Tipo_Relatorio) {
        this.dm_Tipo_Relatorio = dm_Tipo_Relatorio;
    }
    public String getDt_Emissao_MIC_Final() {
        return dt_Emissao_MIC_Final;
    }
    public void setDt_Emissao_MIC_Final(String dt_Emissao_MIC_Final) {
        this.dt_Emissao_MIC_Final = dt_Emissao_MIC_Final;
    }
    public String getDt_Emissao_MIC_Inicial() {
        return dt_Emissao_MIC_Inicial;
    }
    public void setDt_Emissao_MIC_Inicial(String dt_Emissao_MIC_Inicial) {
        this.dt_Emissao_MIC_Inicial = dt_Emissao_MIC_Inicial;
    }
    public String getDt_Emissao_Manifesto_Internacional() {
        return dt_Emissao_Manifesto_Internacional;
    }
    public void setDt_Emissao_Manifesto_Internacional(
            String dt_Emissao_Manifesto_Internacional) {
        this.dt_Emissao_Manifesto_Internacional = dt_Emissao_Manifesto_Internacional;
    }
    public String getNm_Destinatario() {
        return nm_Destinatario;
    }
    public void setNm_Destinatario(String nm_Destinatario) {
        this.nm_Destinatario = nm_Destinatario;
    }
    public String getNm_Remetente() {
        return nm_Remetente;
    }
    public void setNm_Remetente(String nm_Remetente) {
        this.nm_Remetente = nm_Remetente;
    }
    public String getNr_Fatura() {
        return nr_Fatura;
    }
    public void setNr_Fatura(String nr_Fatura) {
        this.nr_Fatura = nr_Fatura;
    }
    public String getNr_Manifesto_Internacional_Final() {
        return nr_Manifesto_Internacional_Final;
    }
    public void setNr_Manifesto_Internacional_Final(
            String nr_Manifesto_Internacional_Final) {
        this.nr_Manifesto_Internacional_Final = nr_Manifesto_Internacional_Final;
    }
    public String getNr_Manifesto_Internacional_Inicial() {
        return nr_Manifesto_Internacional_Inicial;
    }
    public void setNr_Manifesto_Internacional_Inicial(
            String nr_Manifesto_Internacional_Inicial) {
        this.nr_Manifesto_Internacional_Inicial = nr_Manifesto_Internacional_Inicial;
    }
    public int getOid_Unidade() {
        return oid_Unidade;
    }
    public void setOid_Unidade(int oid_Unidade) {
        this.oid_Unidade = oid_Unidade;
    }
    public double getVl_Frete_CRT() {
        return vl_Frete_CRT;
    }
    public void setVl_Frete_CRT(double vl_Frete_CRT) {
        this.vl_Frete_CRT = vl_Frete_CRT;
    }
    public double getVl_Mercadoria() {
        return vl_Mercadoria;
    }
    public void setVl_Mercadoria(double vl_Mercadoria) {
        this.vl_Mercadoria = vl_Mercadoria;
    }
    public double getVl_NF() {
        return vl_NF;
    }
    public void setVl_NF(double vl_NF) {
        this.vl_NF = vl_NF;
    }
    public double getVl_Total_Adicional() {
        return vl_Total_Adicional;
    }
    public void setVl_Total_Adicional(double vl_Total_Adicional) {
        this.vl_Total_Adicional = vl_Total_Adicional;
    }
    public double getVl_Total_Faturamento() {
        return vl_Total_Faturamento;
    }
    public void setVl_Total_Faturamento(double vl_Total_Faturamento) {
        this.vl_Total_Faturamento = vl_Total_Faturamento;
    }
    public double getVl_Total_Frete_Base() {
        return vl_Total_Frete_Base;
    }
    public void setVl_Total_Frete_Base(double vl_Total_Frete_Base) {
        this.vl_Total_Frete_Base = vl_Total_Frete_Base;
    }
    public double getVl_Total_Frete_CRT() {
        return vl_Total_Frete_CRT;
    }
    public void setVl_Total_Frete_CRT(double vl_Total_Frete_CRT) {
        this.vl_Total_Frete_CRT = vl_Total_Frete_CRT;
    }
    public double getVl_Total_Seguro() {
        return vl_Total_Seguro;
    }
    public void setVl_Total_Seguro(double vl_Total_Seguro) {
        this.vl_Total_Seguro = vl_Total_Seguro;
    }
    public double getVl_Peso_CRT() {
        return vl_Peso_CRT;
    }
    public void setVl_Peso_CRT(double vl_Peso_CRT) {
        this.vl_Peso_CRT = vl_Peso_CRT;
    }
    public ArrayList getCrtLista() {
        return crtLista;
    }
    public void setCrtLista(ArrayList crtLista) {
        this.crtLista = crtLista;
    }

    public String getNr_Conhecimento() {
        return nr_Conhecimento;
    }
    public void setNr_Conhecimento(String nr_Conhecimento) {
        this.nr_Conhecimento = nr_Conhecimento;
    }
    public String getNr_Manifesto_Internacional() {
        return nr_Manifesto_Internacional;
    }
    public void setNr_Manifesto_Internacional(String nr_Manifesto_Internacional) {
        this.nr_Manifesto_Internacional = nr_Manifesto_Internacional;
    }
    public String getOid_Cliente() {
        return oid_Cliente;
    }
    public void setOid_Cliente(String oid_Cliente) {
        this.oid_Cliente = oid_Cliente;
    }
    public String getOid_Conhecimento() {
        return oid_Conhecimento;
    }
    public void setOid_Conhecimento(String oid_Conhecimento) {
        this.oid_Conhecimento = oid_Conhecimento;
    }
    public int getOid_Faturamento_Consolidado() {
        return oid_Faturamento_Consolidado;
    }
    public void setOid_Faturamento_Consolidado(int oid_Faturamento_Consolidado) {
        this.oid_Faturamento_Consolidado = oid_Faturamento_Consolidado;
    }
    public String getOid_Manifesto_Internacional() {
        return oid_Manifesto_Internacional;
    }
    public void setOid_Manifesto_Internacional(
            String oid_Manifesto_Internacional) {
        this.oid_Manifesto_Internacional = oid_Manifesto_Internacional;
    }
    public int getOid_Tipo_Faturamento() {
        return oid_Tipo_Faturamento;
    }
    public void setOid_Tipo_Faturamento(int oid_Tipo_Faturamento) {
        this.oid_Tipo_Faturamento = oid_Tipo_Faturamento;
    }
    public double getVl_Adicional() {
        return vl_Adicional;
    }
    public void setVl_Adicional(double vl_Adicional) {
        this.vl_Adicional = vl_Adicional;
    }
    public double getVl_Faturamento() {
        return vl_Faturamento;
    }
    public void setVl_Faturamento(double vl_Faturamento) {
        this.vl_Faturamento = vl_Faturamento;
    }
    public double getVl_Frete() {
        return vl_Frete;
    }
    public void setVl_Frete(double vl_Frete) {
        this.vl_Frete = vl_Frete;
    }
    public double getVl_Frete_Base() {
        return vl_Frete_Base;
    }
    public void setVl_Frete_Base(double vl_Frete_Base) {
        this.vl_Frete_Base = vl_Frete_Base;
    }
    public double getVl_Peso_MIC() {
        return vl_Peso_MIC;
    }
    public void setVl_Peso_MIC(double vl_Peso_MIC) {
        this.vl_Peso_MIC = vl_Peso_MIC;
    }
    public double getVl_Seguro() {
        return vl_Seguro;
    }
    public void setVl_Seguro(double vl_Seguro) {
        this.vl_Seguro = vl_Seguro;
    }
}