package com.master.ed;

/**
 * @author André Valadas
 * @serial Mensagens Vendedores
 * @serialData 27/02/2006
 */
public class Mensagem_VendedorED extends MasterED {

    public Mensagem_VendedorED() {
    }
    public Mensagem_VendedorED(int oid_Mensagem_Vendedor) {
        this.oid_Mensagem_Vendedor = oid_Mensagem_Vendedor;
    }
    public Mensagem_VendedorED(String vendedor) {
        oid_Vendedor = vendedor;
    }
    public Mensagem_VendedorED(String oid_vendedor, String dt_mensagem) {
        super();
        DT_Mensagem = dt_mensagem;
        oid_Vendedor = oid_vendedor;
    }
    
    private int oid_Mensagem_Vendedor;
    private String oid_Vendedor;
    private String DT_Mensagem;
    private String HR_Mensagem;
    private String TX_Assunto;
    private String TX_Mensagem;
    
    public String getDT_Mensagem() {
        return DT_Mensagem;
    }
    
    public void setDT_Mensagem(String mensagem) {
        DT_Mensagem = mensagem;
    }
    
    public String getHR_Mensagem() {
        return HR_Mensagem;
    }
    
    public void setHR_Mensagem(String mensagem) {
        HR_Mensagem = mensagem;
    }
    
    public int getOid_Mensagem_Vendedor() {
        return oid_Mensagem_Vendedor;
    }
    
    public void setOid_Mensagem_Vendedor(int oid_Mensagem_Vendedor) {
        this.oid_Mensagem_Vendedor = oid_Mensagem_Vendedor;
    }
    
    public String getOid_Vendedor() {
        return oid_Vendedor;
    }
    
    public void setOid_Vendedor(String oid_Vendedor) {
        this.oid_Vendedor = oid_Vendedor;
    }
    
    public String getTX_Assunto() {
        return TX_Assunto;
    }
    
    public void setTX_Assunto(String assunto) {
        TX_Assunto = assunto;
    }
    
    public String getTX_Mensagem() {
        return TX_Mensagem;
    }
    
    public void setTX_Mensagem(String mensagem) {
        TX_Mensagem = mensagem;
    }
}