/*
 * Created on 21/10/2004
 */
package com.master.ed;

import java.util.Date;

import com.master.root.FormataDataBean;

/**
 * @author Andre Valadas
 */
public class Produto_VendedorED extends MasterED{
	
    public Produto_VendedorED() {
        super();
    }
    public Produto_VendedorED(int oid_Contrato_Vendedor) {
        super();
        this.oid_Contrato_Vendedor = oid_Contrato_Vendedor;
    }
    
    //*** Tipo
    public static final String TIPO_ANEXO = "ANEXO";
    public static final String TIPO_ADENDO = "ADENDO";
    
    //*** Módulos
    public static final String MODULO_PRODUTO = "PRODUTO";
    public static final String MODULO_MIX_PRODUTO = "MIX_PRODUTO";
    public static final String MODULO_MIX_VENDEDOR = "MIX_VENDEDOR";
    
    //*** Operações
    public static final String OP_INCLUIR = "INCLUIR";
    public static final String OP_ALTERAR = "ALTERAR";
    public static final String OP_EXCLUIR = "EXCLUIR";
    
    //***Complementos das constantes
    public String Tipo;
    public String Operacao;
    public String Modulo;
    public String oid_Pessoa;
    public int oid_Mix;  
    
    //*** Campos da Tabela
	private int oid_Produto_Vendedor;
	private int oid_Produto;
	private String oid_Vendedor;
	private double PE_Comissao;
	private Date DT_Inclusao;
	private Date DT_Alteracao;
	private Date DT_Exclusao;
	private int oid_Contrato_Vendedor;
	
	//*** Complementos da tabela
	private Date DT_Inicial;
	private Date DT_Final;
	
    public Date getDT_Alteracao() {           
        return new FormataDataBean().getFormatDate(DT_Alteracao);
    }
    public void setDT_Alteracao(Date alteracao) {
        DT_Alteracao = alteracao;
    }
    public Date getDT_Exclusao() {        
        return new FormataDataBean().getFormatDate(DT_Exclusao);
    }
    public void setDT_Exclusao(Date exclusao) {
        DT_Exclusao = exclusao;
    }
    public Date getDT_Inclusao() {  
        return new FormataDataBean().getFormatDate(DT_Inclusao);
    }
    public void setDT_Inclusao(Date inclusao) {
        DT_Inclusao = inclusao;
    }
    public int getOid_Contrato_Vendedor() {
        return oid_Contrato_Vendedor;
    }
    public void setOid_Contrato_Vendedor(int oid_Contrato_Vendedor) {
        this.oid_Contrato_Vendedor = oid_Contrato_Vendedor;
    }
    public int getOid_Produto() {
        return oid_Produto;
    }
    public void setOid_Produto(int oid_Produto) {
        this.oid_Produto = oid_Produto;
    }
    public int getOid_Produto_Vendedor() {
        return oid_Produto_Vendedor;
    }
    public void setOid_Produto_Vendedor(int oid_Produto_Vendedor) {
        this.oid_Produto_Vendedor = oid_Produto_Vendedor;
    }
    public String getOid_Vendedor() {
        return oid_Vendedor;
    }
    public void setOid_Vendedor(String oid_Vendedor) {
        this.oid_Vendedor = oid_Vendedor;
    }
    public double getPE_Comissao() {
        return PE_Comissao;
    }
    public void setPE_Comissao(double comissao) {
        PE_Comissao = comissao;
    }
    public String getModulo() {
        return Modulo;
    }
    public void setModulo(String modulo) {
        Modulo = modulo;
    }
    public String getOperacao() {
        return Operacao;
    }
    public void setOperacao(String operacao) {
        Operacao = operacao;
    }
    public int getOid_Mix() {
        return oid_Mix;
    }
    public void setOid_Mix(int oid_Mix) {
        this.oid_Mix = oid_Mix;
    }
    public String getOid_Pessoa() {
        return oid_Pessoa;
    }
    public void setOid_Pessoa(String oid_Pessoa) {
        this.oid_Pessoa = oid_Pessoa;
    }
    public Date getDT_Final() {
        return new FormataDataBean().getFormatDate(DT_Final);
    }
    public void setDT_Final(Date final1) {
        DT_Final = final1;
    }
    public Date getDT_Inicial() {
        return new FormataDataBean().getFormatDate(DT_Inicial);
    }
    public void setDT_Inicial(Date inicial) {
        DT_Inicial = inicial;
    }
    public String getTipo() {
        return Tipo;
    }
    public void setTipo(String tipo) {
        Tipo = tipo;
    }
}
