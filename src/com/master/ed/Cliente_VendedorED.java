/*
 * Created on 25/08/2004
 */
package com.master.ed;

import com.master.root.ClienteBean;
import com.master.root.VendedorBean;


/**
 * @author Andre Valadas
 */
public class Cliente_VendedorED extends MasterED{
	
    public Cliente_VendedorED() {
        super();
    }
    public Cliente_VendedorED(String oid_Vendedor) {
        this.oid_Vendedor = oid_Vendedor;
    }
    
	private String oid_Cliente_Vendedor;
	private String oid_Cliente;
	private String oid_Vendedor;
	private int oid_Tipo_Tabela_Venda;
	private String cd_Cliente;
	private String cd_Vendedor;
	private String nm_Vendedor;
	private String nm_Cliente;
	private String dm_Situacao;
	public ClienteBean edCliente = new ClienteBean();
	public VendedorBean edVendedor = new VendedorBean();
	public Tipo_Tabela_VendaED edTipo_Tabela = new Tipo_Tabela_VendaED();
	
	public String getDescDM_Situacao(){
        if ("L".equals(dm_Situacao) || "A".equals(dm_Situacao))
            return "Liberado";
        else if ("B".equals(dm_Situacao) || "C".equals(dm_Situacao))
            return "Bloqueado";
        else return "Não Informada!";
    }
	public String getOid_Cliente() {
		return oid_Cliente;
	}
	public void setOid_Cliente(String oid_Cliente) {
		this.oid_Cliente = oid_Cliente;
	}
	public String getOid_Vendedor() {
		return oid_Vendedor;
	}
	public void setOid_Vendedor(String oid_Vendedor) {
		this.oid_Vendedor = oid_Vendedor;
	}
	/**
	 * @return Returns the nm_Vendedor.
	 */
	public String getNm_Vendedor() {
		return nm_Vendedor;
	}
	/**
	 * @param nm_Vendedor The nm_Vendedor to set.
	 */
	public void setNm_Vendedor(String nm_Vendedor) {
		this.nm_Vendedor = nm_Vendedor;
	}
	

	/**
	 * @return Returns the cd_Cliente.
	 */
	public String getCd_Cliente() {
		return cd_Cliente;
	}
	/**
	 * @param cd_Cliente The cd_Cliente to set.
	 */
	public void setCd_Cliente(String cd_Cliente) {
		this.cd_Cliente = cd_Cliente;
	}
	/**
	 * @return Returns the cd_Vendedor.
	 */
	public String getCd_Vendedor() {
		return cd_Vendedor;
	}
	/**
	 * @param cd_Vendedor The cd_Vendedor to set.
	 */
	public void setCd_Vendedor(String cd_Vendedor) {
		this.cd_Vendedor = cd_Vendedor;
	}
	/**
	 * @return Returns the dm_Situacao.
	 */
	public String getDm_Situacao() {
		return dm_Situacao;
	}
	/**
	 * @param dm_Situacao The dm_Situacao to set.
	 */
	public void setDm_Situacao(String dm_Situacao) {
		this.dm_Situacao = dm_Situacao;
	}
	public String getNm_Cliente() {
		return nm_Cliente;
	}
	public void setNm_Cliente(String nm_Cliente) {
		this.nm_Cliente = nm_Cliente;
	}
	public String getOid_Cliente_Vendedor() {
		return oid_Cliente_Vendedor;
	}
	public void setOid_Cliente_Vendedor(String oid_Cliente_Vendedor) {
		this.oid_Cliente_Vendedor = oid_Cliente_Vendedor;
	}
    public int getOid_Tipo_Tabela_Venda() {
        return oid_Tipo_Tabela_Venda;
    }
    public void setOid_Tipo_Tabela_Venda(int oid_Tipo_Tabela_Venda) {
        this.oid_Tipo_Tabela_Venda = oid_Tipo_Tabela_Venda;
    }
}
