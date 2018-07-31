package com.master.ed;

/**
 * @author André Valadas
 * @serial Palm
 * @serialData 27/02/2006
 */
public class PalmED extends MasterED {

    public PalmED() {
    }
    
    public PalmED(String oid_vendedor, String cd_vendedor, String pedido, String pedido_final, String duplicata, String duplicata_final, String mensagem) {
        super();
        oid_Vendedor = oid_vendedor;
        CD_Vendedor = cd_vendedor;
        DT_Pedido = pedido;
        DT_Pedido_Final = pedido_final;
        DT_Duplicata = duplicata;
        DT_Duplicata_Final = duplicata_final;
        DT_Mensagem = mensagem;
    }
    
    private String oid_Vendedor;
    private String CD_Vendedor;
    private String DT_Pedido;
    private String DT_Pedido_Final;
    private String DT_Duplicata;
    private String DT_Duplicata_Final;
    private String DT_Mensagem;
    
    public String getCD_Vendedor() {
        return CD_Vendedor;
    }

    
    public void setCD_Vendedor(String vendedor) {
        CD_Vendedor = vendedor;
    }

    
    public String getDT_Duplicata() {
        return DT_Duplicata;
    }

    
    public void setDT_Duplicata(String duplicata) {
        DT_Duplicata = duplicata;
    }

    
    public String getDT_Duplicata_Final() {
        return DT_Duplicata_Final;
    }

    
    public void setDT_Duplicata_Final(String duplicata_Final) {
        DT_Duplicata_Final = duplicata_Final;
    }

    
    public String getDT_Mensagem() {
        return DT_Mensagem;
    }

    
    public void setDT_Mensagem(String mensagem) {
        DT_Mensagem = mensagem;
    }

    
    public String getDT_Pedido() {
        return DT_Pedido;
    }

    
    public void setDT_Pedido(String pedido) {
        DT_Pedido = pedido;
    }

    
    public String getDT_Pedido_Final() {
        return DT_Pedido_Final;
    }

    
    public void setDT_Pedido_Final(String pedido_Final) {
        DT_Pedido_Final = pedido_Final;
    }

    
    public String getOid_Vendedor() {
        return oid_Vendedor;
    }

    
    public void setOid_Vendedor(String oid_Vendedor) {
        this.oid_Vendedor = oid_Vendedor;
    }
    
}