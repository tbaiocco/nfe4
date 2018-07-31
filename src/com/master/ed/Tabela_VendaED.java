package com.master.ed;

import com.master.root.UnidadeBean;
import com.master.util.Data;
import com.master.util.Excecoes;

/**
 * @author André Valadas
 * @serial Tabelas de Venda
 * @serialData 11/04/2005
 */
public class Tabela_VendaED extends MasterED {

    public Tabela_VendaED() {
    }
    public Tabela_VendaED(int oid_Tabela_Venda) {
        this.oid_Tabela_Venda = oid_Tabela_Venda;
    }
    
    public Tipo_Tabela_VendaED edTipo_Tabela = new Tipo_Tabela_VendaED(); 
    public UnidadeBean edUnidade = new UnidadeBean();
    
    private int oid_Tabela_Venda;
    private int oid_Tipo_Tabela_Venda;
    private String oid_Pessoa;
    private int NR_Tabela;
    private String DT_Vigencia;
    private String DT_Validade;
    private String DT_Inclusao;
    
    /** ----------------------- */
    private double PE_Ajuste;
    private String DT_Tabela; // Data de referencia
    private String DT_Encerramento;
    private String DT_Vigencia_Final;
    private String DT_Validade_Final;
    /** ----------------------- */
    
    public String getDescSituacao(int oid_Tabela_Venda, String DT_Vigencia, String DT_Validade) throws Excecoes {
        this.oid_Tabela_Venda = oid_Tabela_Venda;
        this.DT_Vigencia = DT_Vigencia;
        this.DT_Validade = DT_Validade;
        return this.isAtiva() ? "Ativa" : isVencida() ? "Vencida" : "Em espera";
    }
    public String getDescSituacao() throws Excecoes {
        return this.isAtiva() ? "Ativa" : isVencida() ? "Vencida" : "Em espera";
    }
    public boolean isAtiva() throws Excecoes {
        return (this.oid_Tabela_Venda > 0 && (Data.stringToCalendar(Data.getDataDMY(), "dd/MM/yyyy").before(Data.stringToCalendar(this.DT_Validade, "dd/MM/yyyy")) || Data.getDataDMY().equals(this.DT_Validade))) &&
        	   (this.oid_Tabela_Venda > 0 && (Data.stringToCalendar(Data.getDataDMY(), "dd/MM/yyyy").after(Data.stringToCalendar(this.DT_Vigencia, "dd/MM/yyyy")) || Data.getDataDMY().equals(this.DT_Vigencia)));
    }
    public boolean isVencida() throws Excecoes {
        return Data.stringToCalendar(Data.getDataDMY(), "dd/MM/yyy").after(Data.stringToCalendar(this.DT_Validade, "dd/MM/yyy"));
    }
    public boolean isVencida(String DataRef) throws Excecoes {
        return !(this.oid_Tabela_Venda > 0 && DataRef != null && (Data.stringToCalendar(DataRef, "dd/MM/yyyy").before(Data.stringToCalendar(this.DT_Validade, "dd/MM/yyyy")) || DataRef.equals(this.DT_Validade))) ||
        	   !(this.oid_Tabela_Venda > 0 && DataRef != null && (Data.stringToCalendar(DataRef, "dd/MM/yyyy").after(Data.stringToCalendar(this.DT_Vigencia, "dd/MM/yyyy")) || DataRef.equals(this.DT_Vigencia)));
    }
    
    public String getDT_Inclusao() {
        return DT_Inclusao;
    }
    public void setDT_Inclusao(String inclusao) {
        DT_Inclusao = inclusao;
    }
    public String getDT_Validade() {
        return DT_Validade;
    }
    public void setDT_Validade(String validade) {
        DT_Validade = validade;
    }
    public String getDT_Vigencia() {
        return DT_Vigencia;
    }
    public void setDT_Vigencia(String vigencia) {
        DT_Vigencia = vigencia;
    }
    public String getOid_Pessoa() {
        return oid_Pessoa;
    }
    public void setOid_Pessoa(String oid_Pessoa) {
        this.oid_Pessoa = oid_Pessoa;
    }
    public int getOid_Tabela_Venda() {
        return oid_Tabela_Venda;
    }
    public void setOid_Tabela_Venda(int oid_Tabela_Venda) {
        this.oid_Tabela_Venda = oid_Tabela_Venda;
    }
    public int getOid_Tipo_Tabela_Venda() {
        return oid_Tipo_Tabela_Venda;
    }
    public void setOid_Tipo_Tabela_Venda(int oid_Tipo_Tabela_Venda) {
        this.oid_Tipo_Tabela_Venda = oid_Tipo_Tabela_Venda;
    }
    public int getNR_Tabela() {
        return NR_Tabela;
    }
    public void setNR_Tabela(int tabela) {
        NR_Tabela = tabela;
    }
    public double getPE_Ajuste() {
        return PE_Ajuste;
    }
    public void setPE_Ajuste(double ajuste) {
        PE_Ajuste = ajuste;
    }
    public String getDT_Encerramento() {
        return DT_Encerramento;
    }
    public void setDT_Encerramento(String encerramento) {
        DT_Encerramento = encerramento;
    }
    public String getDT_Validade_Final() {
        return DT_Validade_Final;
    }
    public void setDT_Validade_Final(String validade_Final) {
        DT_Validade_Final = validade_Final;
    }
    public String getDT_Vigencia_Final() {
        return DT_Vigencia_Final;
    }
    public void setDT_Vigencia_Final(String vigencia_Final) {
        DT_Vigencia_Final = vigencia_Final;
    }
    public String getDT_Tabela() {
        return DT_Tabela;
    }
    public void setDT_Tabela(String tabela) {
        DT_Tabela = tabela;
    }
}