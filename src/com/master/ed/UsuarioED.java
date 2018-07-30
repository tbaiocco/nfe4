package com.master.ed;

import java.io.Serializable;

import com.master.root.UnidadeBean;

public class UsuarioED extends MasterED implements Serializable {

	private static final long serialVersionUID = 5313866954428938543L;

	public UsuarioED() {
        super();
    }
    public UsuarioED(Integer oid_Usuario) {
        this.oid_Usuario = oid_Usuario;
    }
    private Integer oid_Usuario;
    private String nm_Usuario;
    private String NM_Senha;
    private String DM_Perfil;
    private Integer oid_Unidade;
    private String CD_Unidade;
    private String NM_Razao_Social;
    private String oid_Pessoa;
    private long oid_Empresa;
    private long oid_Sistema;
    private long oid_Menu_Perfil;
    private String nm_Menu_Perfil;
    private String DM_Situacao;
    private String Nm_Email;    
    private String nr_Cnpj;
    private String nm_Razao_Social;
    private String DM_Relatorio;
    private String DT_Acesso_Inicial;
    private String DT_Acesso_Final;
    
    
    private String nm_Unidade;
    
    public UnidadeBean edUnidade = new UnidadeBean();
  	private String DM_Administrador;

    public String getDescDM_Perfil() {
        if ("M".equals(DM_Perfil))
            return "Master";
        else if ("N".equals(DM_Perfil))
            return "Financeiro";
        else if ("A".equals(DM_Perfil))
            return "Operacional";
        else if ("F".equals(DM_Perfil))
            return "Administrativo";
        else if ("U".equals(DM_Perfil))
            return "Filial";
        else if ("R".equals(DM_Perfil))
            return "Frota";
        else if ("E".equals(DM_Perfil))
            return "Posto Avançado";
        else if ("C".equals(DM_Perfil))
            return "Cliente";
        else if ("D".equals(DM_Perfil))
            return "Diretoria";
        else if ("T".equals(DM_Perfil))
            return "Fiscal/Contabil";
        else if ("S".equals(DM_Perfil))
            return "Segurança";
        else return DM_Perfil; 
    }
    
    public String getNm_Usuario() {
        return nm_Usuario;
    }

    public Integer getOid_Usuario() {
        return oid_Usuario;
    }

    public void setOid_Usuario(Integer oid_Usuario) {
        this.oid_Usuario = oid_Usuario;
    }

    public void setNm_Usuario(String nm_Usuario) {
        this.nm_Usuario = nm_Usuario;
    }

    public void setNM_Senha(String NM_Senha) {
        this.NM_Senha = NM_Senha;
    }

    public String getNM_Senha() {
        return NM_Senha;
    }

    public void setDM_Perfil(String DM_Perfil) {
        this.DM_Perfil = DM_Perfil;
    }

    public String getDM_Perfil() {
        return DM_Perfil;
    }

    public void setOid_Unidade(Integer oid_Unidade) {
        this.oid_Unidade = oid_Unidade;
    }

    public Integer getOid_Unidade() {
        return oid_Unidade;
    }

    public void setCD_Unidade(String CD_Unidade) {
        this.CD_Unidade = CD_Unidade;
    }

    public String getCD_Unidade() {
        return CD_Unidade;
    }

    public void setNM_Razao_Social(String NM_Razao_Social) {
        this.NM_Razao_Social = NM_Razao_Social;
    }

    public String getNM_Razao_Social() {
        return NM_Razao_Social;
    }
	public String getOid_Pessoa() {
        return oid_Pessoa;
    }

  	public String getDM_Administrador () {
    	return DM_Administrador;
  	}

    public void setOid_Pessoa(String oid_Pessoa) {
        this.oid_Pessoa = oid_Pessoa;
    }

  	public void setDM_Administrador (String DM_Administrador) {
    	this.DM_Administrador = DM_Administrador;
	}
	public long getOid_Empresa() {
		return oid_Empresa;
	}
	public void setOid_Empresa(long oid_Empresa) {
		this.oid_Empresa = oid_Empresa;
	}
	public long getOid_Menu_Perfil() {
		return oid_Menu_Perfil;
	}
	public void setOid_Menu_Perfil(long oid_Menu_Perfil) {
		this.oid_Menu_Perfil = oid_Menu_Perfil;
	}
	public long getOid_Sistema() {
		return oid_Sistema;
	}
	public void setOid_Sistema(long oid_Sistema) {
		this.oid_Sistema = oid_Sistema;
	}
	public String getNm_Menu_Perfil() {
		return nm_Menu_Perfil;
	}
	public void setNm_Menu_Perfil(String nm_Menu_Perfil) {
		this.nm_Menu_Perfil = nm_Menu_Perfil;
	}
	public String getNm_Unidade() {
		return nm_Unidade;
	}
	public void setNm_Unidade(String nm_Unidade) {
		this.nm_Unidade = nm_Unidade;
	}
	public String getDM_Situacao() {
		return DM_Situacao;
	}
	public void setDM_Situacao(String situacao) {
		DM_Situacao = situacao;
	}
	public String getNm_Email() {
		return Nm_Email;
	}
	public void setNm_Email(String nm_Email) {
		Nm_Email = nm_Email;
	}
	public String getNr_Cnpj() {
		return nr_Cnpj;
	}
	public void setNr_Cnpj(String nr_Cnpj) {
		this.nr_Cnpj = nr_Cnpj;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getNm_Razao_Social() {
		return nm_Razao_Social;
	}
	public void setNm_Razao_Social(String nm_Razao_Social) {
		this.nm_Razao_Social = nm_Razao_Social;
	}
	public String getDM_Relatorio() {
		return DM_Relatorio;
	}
	public void setDM_Relatorio(String relatorio) {
		DM_Relatorio = relatorio;
	}
	public String getDT_Acesso_Final() {
		return DT_Acesso_Final;
	}
	public void setDT_Acesso_Final(String acesso_Final) {
		DT_Acesso_Final = acesso_Final;
	}
	public String getDT_Acesso_Inicial() {
		return DT_Acesso_Inicial;
	}
	public void setDT_Acesso_Inicial(String acesso_Inicial) {
		DT_Acesso_Inicial = acesso_Inicial;
	}
}
