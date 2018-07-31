package com.master.ed;

import java.io.Serializable;

import com.master.root.PessoaBean;
import com.master.root.UnidadeBean;

/**
 * @author André Valadas
 * @serial Recibos
 * @serialData 13/10/2005
 */
public class ReciboED extends MasterED implements Serializable {

    public ReciboED() {
        super();
    }
    public ReciboED(int oid_Recibo) {
        this.oid_Recibo = oid_Recibo;
    }
    public ReciboED(boolean corrente, boolean pessoa, boolean unidade) {
        carregaCCorrente = corrente;
        carregaPessoa = pessoa;
        carregaUnidade = unidade;
    }
    public ReciboED(int oid_recebimento, boolean corrente, boolean pessoa, boolean unidade) {
        carregaCCorrente = corrente;
        carregaPessoa = pessoa;
        carregaUnidade = unidade;
        oid_Recibo = oid_recebimento;
    }
    
    public Conta_CorrenteED edConta_Corrente = new Conta_CorrenteED();
    public UnidadeBean edUnidade = new UnidadeBean();
    public PessoaBean edPessoa = new PessoaBean();
    
    private boolean carregaCCorrente = true;
    private boolean carregaUnidade = true;
    private boolean carregaPessoa = true;
    
    private int oid_Recibo;
    private int oid_Unidade;
    private String oid_Conta_Corrente;
    private String oid_Pessoa;
    private int NR_Recibo;
    private String DM_Tipo; //(P=Pagamento / R=Recebimento)
    private String DT_Recibo;
    private double VL_Recibo;
    private String TX_Observacao;
    
    private String DT_Recibo_Final;
    
    public String getDescDM_Tipo(){
        if ("P".equals(DM_Tipo))
           return "Pagamento";     
        else if ("R".equals(DM_Tipo))
           return "Recebimento";     
        else return "Não Informada!";
    }
    
    public boolean isCarregaCCorrente() {
        return carregaCCorrente;
    }
    
    public void setCarregaCCorrente(boolean carregaCCorrente) {
        this.carregaCCorrente = carregaCCorrente;
    }
    
    public boolean isCarregaPessoa() {
        return carregaPessoa;
    }
    
    public void setCarregaPessoa(boolean carregaPessoa) {
        this.carregaPessoa = carregaPessoa;
    }
    
    public boolean isCarregaUnidade() {
        return carregaUnidade;
    }
    
    public void setCarregaUnidade(boolean carregaUnidade) {
        this.carregaUnidade = carregaUnidade;
    }
    
    public String getDM_Tipo() {
        return DM_Tipo;
    }
    
    public void setDM_Tipo(String tipo) {
        DM_Tipo = tipo;
    }
    
    public String getDT_Recibo() {
        return DT_Recibo;
    }
    
    public void setDT_Recibo(String recibo) {
        DT_Recibo = recibo;
    }
    
    public String getDT_Recibo_Final() {
        return DT_Recibo_Final;
    }
    
    public void setDT_Recibo_Final(String recibo_Final) {
        DT_Recibo_Final = recibo_Final;
    }
    
    public int getNR_Recibo() {
        return NR_Recibo;
    }
    
    public void setNR_Recibo(int recibo) {
        NR_Recibo = recibo;
    }
    
    public String getOid_Conta_Corrente() {
        return oid_Conta_Corrente;
    }
    
    public void setOid_Conta_Corrente(String oid_Conta_Corrente) {
        this.oid_Conta_Corrente = oid_Conta_Corrente;
    }
    
    public String getOid_Pessoa() {
        return oid_Pessoa;
    }
    
    public void setOid_Pessoa(String oid_Pessoa) {
        this.oid_Pessoa = oid_Pessoa;
    }
    
    public int getOid_Recibo() {
        return oid_Recibo;
    }
    
    public void setOid_Recibo(int oid_Recibo) {
        this.oid_Recibo = oid_Recibo;
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
    
    public double getVL_Recibo() {
        return VL_Recibo;
    }
    
    public void setVL_Recibo(double recibo) {
        VL_Recibo = recibo;
    }
}