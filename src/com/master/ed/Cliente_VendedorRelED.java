/*
 * Created on 25/08/2004
 */
package com.master.ed;


/**
 * @author Andre Valadas
 */
public class Cliente_VendedorRelED extends MasterED{
    
    public static final String LAYOUT_VEND_SIMPLES = "S";
    public static final String LAYOUT_VEND_COMPLETO = "C";
    public static final String LAYOUT_ROTA = "R";
    public static final String ORDENAR_NOME = "N";
    public static final String ORDENAR_CODIGO = "C";
	
	private String oid_cliente;
	private String oid_vendedor;
	private String cd_cliente;
	private String cd_vendedor;
	private String nm_vendedor;
	private String nm_cliente;
	private String cd_rota_venda;
	
	private String nm_inscricao_estadual;
	private String nm_cidade;
	private String nm_endereco;
	private String nm_bairro;
	private String nr_cep;
	private String nr_telefone;
	
    /**
     * @return Returns the nr_telefone.
     */
    public String getNr_telefone() {
        return nr_telefone;
    }
    /**
     * @param nr_telefone The nr_telefone to set.
     */
    public void setNr_telefone(String nr_telefone) {
        this.nr_telefone = nr_telefone;
    }
	public String getOid_cliente() {
		return oid_cliente;
	}
	public void setOid_cliente(String oid_cliente) {
		this.oid_cliente = oid_cliente;
	}
	public String getOid_vendedor() {
		return oid_vendedor;
	}
	public void setOid_vendedor(String oid_vendedor) {
		this.oid_vendedor = oid_vendedor;
	}
	/**
	 * @return Returns the nm_vendedor.
	 */
	public String getNm_vendedor() {
		return nm_vendedor;
	}
	/**
	 * @param nm_vendedor The nm_vendedor to set.
	 */
	public void setNm_vendedor(String nm_vendedor) {
		this.nm_vendedor = nm_vendedor;
	}
	

	/**
	 * @return Returns the cd_cliente.
	 */
	public String getCd_cliente() {
		return cd_cliente;
	}
	/**
	 * @param cd_cliente The cd_cliente to set.
	 */
	public void setCd_cliente(String cd_cliente) {
		this.cd_cliente = cd_cliente;
	}
	/**
	 * @return Returns the cd_vendedor.
	 */
	public String getCd_vendedor() {
		return cd_vendedor;
	}
	/**
	 * @param cd_vendedor The cd_vendedor to set.
	 */
	public void setCd_vendedor(String cd_vendedor) {
		this.cd_vendedor = cd_vendedor;
	}
	public String getNm_cliente() {
		return nm_cliente;
	}
	public void setNm_cliente(String nm_cliente) {
		this.nm_cliente = nm_cliente;
	}
    /**
     * @return Returns the cd_rota_venda.
     */
    public String getCd_rota_venda() {
        return cd_rota_venda;
    }
    /**
     * @param cd_rota_venda The cd_rota_venda to set.
     */
    public void setCd_rota_venda(String cd_rota_venda) {
        this.cd_rota_venda = cd_rota_venda;
    }
    /**
     * @return Returns the nm_bairro.
     */
    public String getNm_bairro() {
        return nm_bairro;
    }
    /**
     * @param nm_bairro The nm_bairro to set.
     */
    public void setNm_bairro(String nm_bairro) {
        this.nm_bairro = nm_bairro;
    }
    /**
     * @return Returns the nm_cidade.
     */
    public String getNm_cidade() {
        return nm_cidade;
    }
    /**
     * @param nm_cidade The nm_cidade to set.
     */
    public void setNm_cidade(String nm_cidade) {
        this.nm_cidade = nm_cidade;
    }
    /**
     * @return Returns the nm_endereco.
     */
    public String getNm_endereco() {
        return nm_endereco;
    }
    /**
     * @param nm_endereco The nm_endereco to set.
     */
    public void setNm_endereco(String nm_endereco) {
        this.nm_endereco = nm_endereco;
    }
    /**
     * @return Returns the nm_inscricao_estadual.
     */
    public String getNm_inscricao_estadual() {
        return nm_inscricao_estadual;
    }
    /**
     * @param nm_inscricao_estadual The nm_inscricao_estadual to set.
     */
    public void setNm_inscricao_estadual(String nm_inscricao_estadual) {
        this.nm_inscricao_estadual = nm_inscricao_estadual;
    }
    /**
     * @return Returns the nr_cep.
     */
    public String getNr_cep() {
        return nr_cep;
    }
    /**
     * @param nr_cep The nr_cep to set.
     */
    public void setNr_cep(String nr_cep) {
        this.nr_cep = nr_cep;
    }
}
