package com.master.ed;

import java.io.Serializable;

import com.master.root.Tipo_DocumentoBean;
import com.master.root.UnidadeBean;

/**
 * @author André Valadas
 * @serial Lotes Recebimentos
 * @serialData 15/08/2005
 */
public class Lote_RecebimentoED extends MasterED implements Serializable {

    public Lote_RecebimentoED() {
        super();
    }
    public Lote_RecebimentoED(int oid_Lote_Recebimento) {
        this.oid_Lote_Recebimento = oid_Lote_Recebimento;
    }
    public Lote_RecebimentoED(boolean corrente, boolean doc, boolean unidade) {
        carregaCCorrente = corrente;
        carregaTipoDoc = doc;
        carregaUnidade = unidade;
    }
    public Lote_RecebimentoED(int oid_recebimento, boolean corrente, boolean doc, boolean unidade) {
        carregaCCorrente = corrente;
        carregaTipoDoc = doc;
        carregaUnidade = unidade;
        oid_Lote_Recebimento = oid_recebimento;
    }
    
    public Conta_CorrenteED edConta_Corrente = new Conta_CorrenteED();
    public UnidadeBean edUnidade = new UnidadeBean();
    public Tipo_DocumentoBean edTipoDoc = new Tipo_DocumentoBean();
    
    private boolean carregaCCorrente = true;
    private boolean carregaUnidade = true;
    private boolean carregaTipoDoc = true;
    
    private int oid_Lote_Recebimento;
    private int oid_Unidade;
    private int NR_Lote;
    private double VL_Lote;
    private String oid_Conta_Corrente;
    private int oid_Tipo_Documento;
    private String DT_Emissao;
    private String DT_Programada;
    private String DT_Compensacao;
    private String DM_Situacao;
    private String TX_Observacao;
    
    
    private String DT_Emissao_Final;
    private String DT_Programada_Final;
    private String DT_Compensacao_Final;
    
    public String getDescDM_Situacao(){
        if ("I".equals(DM_Situacao))
           return "Impresso";     
        else if ("N".equals(DM_Situacao))
           return "Não Impresso";     
        else if ("F".equals(DM_Situacao))
           return "Compensado";     
        else if ("C".equals(DM_Situacao))
           return "Cancelado";     
        else return "Não Informada!";
    }
    
    public String getDM_Situacao() {
        return DM_Situacao;
    }
    
    public void setDM_Situacao(String situacao) {
        DM_Situacao = situacao;
    }
    
    public String getDT_Emissao() {
        return DT_Emissao;
    }
    
    public void setDT_Emissao(String emissao) {
        DT_Emissao = emissao;
    }
    
    public String getDT_Programada() {
        return DT_Programada;
    }
    
    public void setDT_Programada(String programada) {
        DT_Programada = programada;
    }
    
    public int getNR_Lote() {
        return NR_Lote;
    }
    
    public void setNR_Lote(int lote) {
        NR_Lote = lote;
    }
    
    public String getOid_Conta_Corrente() {
        return oid_Conta_Corrente;
    }
    
    public void setOid_Conta_Corrente(String oid_Conta_Corrente) {
        this.oid_Conta_Corrente = oid_Conta_Corrente;
    }
    
    public int getOid_Lote_Recebimento() {
        return oid_Lote_Recebimento;
    }
    
    public void setOid_Lote_Recebimento(int oid_Lote_Recebimento) {
        this.oid_Lote_Recebimento = oid_Lote_Recebimento;
    }
    
    public int getOid_Tipo_Documento() {
        return oid_Tipo_Documento;
    }
    
    public void setOid_Tipo_Documento(int oid_Tipo_Documento) {
        this.oid_Tipo_Documento = oid_Tipo_Documento;
    }
    
    public int getOid_Unidade() {
        return oid_Unidade;
    }
    
    public void setOid_Unidade(int oid_Unidade) {
        this.oid_Unidade = oid_Unidade;
    }
    
    public String getTX_Observacao() {
        return TX_Observacao;
    }
    
    public void setTX_Observacao(String observacao) {
        TX_Observacao = observacao;
    }
    
    public double getVL_Lote() {
        return VL_Lote;
    }
    
    public void setVL_Lote(double lote) {
        VL_Lote = lote;
    }
    
    public String getDT_Emissao_Final() {
        return DT_Emissao_Final;
    }
    
    public void setDT_Emissao_Final(String emissao_Final) {
        DT_Emissao_Final = emissao_Final;
    }
    
    public String getDT_Programada_Final() {
        return DT_Programada_Final;
    }
    
    public void setDT_Programada_Final(String programada_Final) {
        DT_Programada_Final = programada_Final;
    }
    
    public String getDT_Compensacao() {
        return DT_Compensacao;
    }
    
    public void setDT_Compensacao(String compensacao) {
        DT_Compensacao = compensacao;
    }
    
    public String getDT_Compensacao_Final() {
        return DT_Compensacao_Final;
    }
    
    public void setDT_Compensacao_Final(String compensacao_Final) {
        DT_Compensacao_Final = compensacao_Final;
    }
    
    public boolean isCarregaCCorrente() {
        return carregaCCorrente;
    }
    
    public void setCarregaCCorrente(boolean carregaCCorrente) {
        this.carregaCCorrente = carregaCCorrente;
    }
    
    public boolean isCarregaTipoDoc() {
        return carregaTipoDoc;
    }
    
    public void setCarregaTipoDoc(boolean carregaTipoDoc) {
        this.carregaTipoDoc = carregaTipoDoc;
    }
    
    public boolean isCarregaUnidade() {
        return carregaUnidade;
    }
    
    public void setCarregaUnidade(boolean carregaUnidade) {
        this.carregaUnidade = carregaUnidade;
    }
}