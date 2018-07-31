package com.master.ed;


/**
 * @author Andr� Valadas
 * USADO SOMENTE PELOS PROGRAMADORES
 */
public class SubModulo_PermissaoED {

    /*** Exemplo:
     * 	1	001	com005	Vendedores
     * 	2	002	ped001	Pedido de Venda
     * 	3	002	ped004	Notas Fiscais de Saida
    */
    public SubModulo_PermissaoED() {
        super();
    }
    public SubModulo_PermissaoED(int oid_SubModulo_Permissao) {
        this.oid_SubModulo_Permissao = oid_SubModulo_Permissao;
    }  
    
    private int oid_SubModulo_Permissao;
	private String CD_Modulo; // Comercial, Financeiro, Manuten��o, etc...
	private String CD_SubModulo;
	private String NM_SubModulo;
	
	public String getNM_Modulo() {
	    if ("001".equals(CD_Modulo)) {
	        return "Financeiro";
	    } else if ("002".equals(CD_Modulo)) {
	        return "Operacional";
	    } else if ("003".equals(CD_Modulo)) {
	        return "Comercial";
	    } else if ("004".equals(CD_Modulo)) {
	        return "Manuten��o";
	    } else if ("005".equals(CD_Modulo)) {
	        return "Estoque";
	    } else if ("006".equals(CD_Modulo)) {
	        return "Par�metros Gerais";
	    } else if ("007".equals(CD_Modulo)) {
	        return "Gerencial";
	    } else if ("008".equals(CD_Modulo)) {
	        return "Troca Eletr�nica de Dados";
	    } else if ("009".equals(CD_Modulo)) {
	        return "Fiscal/Cont�bil";
	    } else if ("010".equals(CD_Modulo)) {
	        return "Compras";
	    } else if ("011".equals(CD_Modulo)) {
	        return "Vendas";
	    } else if ("012".equals(CD_Modulo)) {
	        return "Produtos";
	    } else if ("013".equals(CD_Modulo)) {
	        return "Armazenagem";
	    } else if ("014".equals(CD_Modulo)) {
	        return "Distribui��o";
	    } else return "N�o Informado!"; 
	}
    
    public String getCD_Modulo() {
        return CD_Modulo;
    }
    public String getCD_SubModulo() {
        return CD_SubModulo;
    }
    public String getNM_SubModulo() {
        return NM_SubModulo;
    }
    public int getOid_SubModulo_Permissao() {
        return oid_SubModulo_Permissao;
    }
    public void setCD_Modulo(String modulo) {
        CD_Modulo = modulo;
    }
    public void setCD_SubModulo(String subModulo) {
        CD_SubModulo = subModulo;
    }
    public void setNM_SubModulo(String subModulo) {
        NM_SubModulo = subModulo;
    }
    public void setOid_SubModulo_Permissao(int oid_SubModulo_Permissao) {
        this.oid_SubModulo_Permissao = oid_SubModulo_Permissao;
    }
}