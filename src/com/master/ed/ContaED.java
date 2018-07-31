package com.master.ed;

import java.util.*;

import com.master.rn.*;
import com.master.util.*;

public class ContaED extends RelatorioBaseED {

    public ContaED() {
        super();
    }

    public ContaED(Integer oid_Conta) {
        this.oid_Conta = oid_Conta;
    }

    private Integer oid_Conta;
    private Integer oid_Conta_Nova;
    private Integer oid_Unidade;
    private Integer oid_Empresa;
    private String nm_Empresa;
    private String nm_Conta;
    private String cd_Conta;
    private String dm_Tipo_Operacao;
    private String dm_Apropriacao;
    private String cd_Conta_Contabil;
    private Integer oid_Historico;
    private Integer oid_Grupo_Conta;
    private Integer oid_Tipo_Documento;
    private Integer oid_Natureza_Operacao;
    private String nm_Grupo_Conta;
    private String nm_Historico;
    private String nm_Tipo_Documento;
    private String cd_Historico;
    private String cd_Tipo_Documento;
    private String dm_Despesa;
    private String cd_Natureza_Operacao;
    private String nm_Natureza_Operacao;
    private String dt_Inicial;
    private String dt_Final;
    private String DM_Relatorio;
    private String DM_Pedido_Compra;
    private String DM_Fluxo_Caixa;
    private String DM_Vencimento;
    private String cd_Estrutural_Sped;

    //Contabilidade ---------------
    private String cd_Estrutural;
    private String dm_Tipo_Conta;
    private String dm_Zera;
    private Integer nr_Grau;
    private String dm_Centro_Custo;
    private String cd_Estrutural_Inicial;
    private String cd_Estrutural_Final;
    //--- Funções booleanas-------------
    private boolean paiEstruturalValido;
    private boolean paiEstrutural;
    private boolean estruturalExistente;
    private boolean codigoExistente;
    private double vl_Limite_Bloqueio_Compromisso;
  private double VL_Orcado;
  private String dm_Orcado;
  private String oid_Orcamento_Conta;
  private double VL_Apropriado;
  private double VL_Saldo;
  private String dm_Orcado_Destino;

  private String dm_libera_compromisso;




public String getCd_Conta() {
        return cd_Conta;
    }

    public String getCd_Conta_Contabil() {
        return cd_Conta_Contabil;
    }

    public String getDm_Apropriacao() {
        return dm_Apropriacao;
    }

    public String getDm_Tipo_Operacao() {
        return dm_Tipo_Operacao;
    }

    public String getNm_Conta() {
        return nm_Conta;
    }

    public Integer getOid_Conta() {
        return oid_Conta;
    }

    public Integer getOid_Grupo_Conta() {
        return oid_Grupo_Conta;
    }

    public Integer getOid_Historico() {
        return oid_Historico;
    }

    public Integer getOid_Tipo_Documento() {
        return oid_Tipo_Documento;
    }

    public void setOid_Tipo_Documento(Integer oid_Tipo_Documento) {
        this.oid_Tipo_Documento = oid_Tipo_Documento;
    }

    public void setOid_Historico(Integer oid_Historico) {
        this.oid_Historico = oid_Historico;
    }

    public void setOid_Grupo_Conta(Integer oid_Grupo_Conta) {
        this.oid_Grupo_Conta = oid_Grupo_Conta;
    }

    public void setOid_Conta(Integer oid_Conta) {
        this.oid_Conta = oid_Conta;
    }

    public void setNm_Conta(String nm_Conta) {
        this.nm_Conta = nm_Conta;
    }

    public void setDm_Tipo_Operacao(String dm_Tipo_Operacao) {
        this.dm_Tipo_Operacao = dm_Tipo_Operacao;
    }

    public void setDm_Apropriacao(String dm_Apropriacao) {
        this.dm_Apropriacao = dm_Apropriacao;
    }

    public void setCd_Conta_Contabil(String cd_Conta_Contabil) {
        this.cd_Conta_Contabil = cd_Conta_Contabil;
    }

    public void setCd_Conta(String cd_Conta) {
        this.cd_Conta = cd_Conta;
    }

    public void setNm_Grupo_Conta(String nm_Grupo_Conta) {
        this.nm_Grupo_Conta = nm_Grupo_Conta;
    }

    public String getNm_Grupo_Conta() {
        return nm_Grupo_Conta;
    }

    public void setNm_Historico(String nm_Historico) {
        this.nm_Historico = nm_Historico;
    }

    public String getNm_Historico() {
        return nm_Historico;
    }

    public void setNm_Tipo_Documento(String nm_Tipo_Documento) {
        this.nm_Tipo_Documento = nm_Tipo_Documento;
    }

    public String getNm_Tipo_Documento() {
        return nm_Tipo_Documento;
    }

    public void setCd_Historico(String cd_Historico) {
        this.cd_Historico = cd_Historico;
    }

    public String getCd_Historico() {
        return cd_Historico;
    }

    public void setCd_Tipo_Documento(String cd_Tipo_Documento) {
        this.cd_Tipo_Documento = cd_Tipo_Documento;
    }

    public String getCd_Tipo_Documento() {
        return cd_Tipo_Documento;
    }

    public void setDm_Despesa(String dm_Despesa) {
        this.dm_Despesa = dm_Despesa;
    }

    public String getDm_Despesa() {
        return dm_Despesa;
    }

    public Integer getOid_Empresa() {
        return oid_Empresa;
    }

    public void setOid_Empresa(Integer oid_Empresa) {
        this.oid_Empresa = oid_Empresa;
    }

    public String getNm_Empresa() {
        return nm_Empresa;
    }

    public void setNm_Empresa(String nm_Empresa) {
        this.nm_Empresa = nm_Empresa;
    }

    public Integer getOid_Natureza_Operacao() {
        return oid_Natureza_Operacao;
    }

    public void setOid_Natureza_Operacao(Integer oid_Natureza_Operacao) {
        this.oid_Natureza_Operacao = oid_Natureza_Operacao;
    }

    public String getCd_Natureza_Operacao() {
        return cd_Natureza_Operacao;
    }

    public String getNm_Natureza_Operacao() {
        return nm_Natureza_Operacao;
    }

  public Integer getOid_Conta_Nova () {
    return oid_Conta_Nova;
  }

  public Integer getOid_Unidade () {
    return oid_Unidade;
  }

  public String getDt_Inicial () {
    return dt_Inicial;
  }

  public String getDt_Final () {
    return dt_Final;
  }

  public String getDM_Relatorio () {
    return DM_Relatorio;
  }

  public void setCd_Natureza_Operacao(String cd_Natureza_Operacao) {
        this.cd_Natureza_Operacao = cd_Natureza_Operacao;
    }

    public void setNm_Natureza_Operacao(String nm_Natureza_Operacao) {
        this.nm_Natureza_Operacao = nm_Natureza_Operacao;
    }

  public void setOid_Conta_Nova (Integer oid_Conta_Nova) {
    this.oid_Conta_Nova = oid_Conta_Nova;
  }

  public void setOid_Unidade (Integer oid_Unidade) {
    this.oid_Unidade = oid_Unidade;
  }

  public void setDt_Inicial (String dt_Inicial) {
    this.dt_Inicial = dt_Inicial;
  }

  public void setDt_Final (String dt_Final) {
    this.dt_Final = dt_Final;
  }

  public void setDM_Relatorio (String DM_Relatorio) {
    this.DM_Relatorio = DM_Relatorio;
  }

  public String getDm_libera_compromisso() {
		return dm_libera_compromisso;
	}

  public void setDm_libera_compromisso(String dm_libera_compromisso) {
		this.dm_libera_compromisso = dm_libera_compromisso;
	}


	//Contabilidade --------------------------
	public String getCd_Estrutural() {
		return cd_Estrutural;
	}
	public String getDm_Centro_Custo() {
		return dm_Centro_Custo;
	}
	public String getDm_Tipo_Conta() {
		return dm_Tipo_Conta;
	}
	public String getDm_Zera() {
		return dm_Zera;
	}
	public Integer getNr_Grau() {
		return nr_Grau;
	}
	public void setCd_Estrutural(String cd_Estrutural) {
		this.cd_Estrutural = cd_Estrutural;
	}
	public void setDm_Centro_Custo(String dm_Centro_Custo) {
		this.dm_Centro_Custo = dm_Centro_Custo;
	}
	public void setDm_Tipo_Conta(String dm_Tipo_conta) {
		this.dm_Tipo_Conta = dm_Tipo_conta;
	}
	public void setDm_Zera(String dm_Zera) {
		this.dm_Zera = dm_Zera;
	}
	public void setNr_Grau(Integer nr_Grau) {
		this.nr_Grau = nr_Grau;
	}
	public String getCd_Estrutural_Final() {
		return cd_Estrutural_Final;
	}
	public String getCd_Estrutural_Inicial() {
		return cd_Estrutural_Inicial;
	}
	public void setCd_Estrutural_Final(String cd_Estrutural_Final) {
		this.cd_Estrutural_Final = cd_Estrutural_Final;
	}
	public void setCd_Estrutural_Inicial(String cd_Estrutural_Inicial) {
		this.cd_Estrutural_Inicial = cd_Estrutural_Inicial;
	}

	/** - Contabilidade --------------------------
	 *  Funções booleanas
	 */
	/**
	 * Verifica se existe uma conta pai (sintética) para este código estrutural analitico
	 */
	public boolean isPaiEstruturalValido() throws Excecoes {
		setPaiEstruturalValido( true ) ;
		ContaED ed = new ContaED();
		ed.setCd_Estrutural(pegaGrauInferior());
		if (!"*".equals(ed.cd_Estrutural)) {
			ed = new ContaRN().getByEstrutural(ed);
			if ( ed.getCd_Conta() == null ){
				setPaiEstruturalValido( false );
			}else{
				if ("A".equals(ed.getDm_Tipo_Conta())) {
					setPaiEstruturalValido( false );
				}
			}
		}
		return paiEstruturalValido;
	}
	public void setPaiEstruturalValido(boolean paiEstruturalValido) {
		this.paiEstruturalValido = paiEstruturalValido;
	}
	public boolean isEstruturalExistente() throws Excecoes {
		ContaED ed = new ContaED();
		ed.setCd_Estrutural(this.cd_Estrutural);
		ed = new ContaRN().getByEstrutural(ed);
		if ( this.oid_Conta == null )
		{ 	// Inclusao
			setEstruturalExistente( ed.getCd_Conta() == null ?  false : true);
		}else{
			if ( this.oid_Conta.equals(ed.oid_Conta) ) {
				setEstruturalExistente( false );
			}else{
				setEstruturalExistente( ed.getCd_Conta() == null ?  false : true);
			}
		}
		return estruturalExistente;
	}
	public void setEstruturalExistente(boolean estruturalExistente) {
		this.estruturalExistente = estruturalExistente;
	}
	public boolean isCodigoExistente() throws Excecoes {
		ContaED ed = new ContaED();
		ed.setCd_Conta(this.cd_Conta);
		ed = new ContaRN().getByCD(ed);
		if ( this.oid_Conta == null )
		{
			setCodigoExistente ( ed.getCd_Conta() == null ?  false : true );
		}else{
			if ( this.oid_Conta.equals(ed.oid_Conta) ) {
				setCodigoExistente( false );
			}else{
				setCodigoExistente ( ed.getCd_Conta() == null ?  false : true );
			}
		}
		return codigoExistente;
	}
	public void setCodigoExistente(boolean codigoExistente) {
		this.codigoExistente = codigoExistente;
	}
	public boolean isPaiEstrutural()throws Excecoes {
		ContaED ed = new ContaED();
		ed.setCd_Estrutural(this.cd_Estrutural);
		ArrayList lista ;
		lista = new ContaRN().lista(ed);
		setPaiEstrutural (lista.size() > 1 ? true:false);
		return paiEstrutural;
	}

  public double getvl_Limite_Bloqueio_Compromisso () {
    return vl_Limite_Bloqueio_Compromisso;
  }

  public String getDm_Orcado () {

    return dm_Orcado;
  }


  public String getOid_Orcamento_Conta () {
    return oid_Orcamento_Conta;
  }

  public String getDm_Orcado_Destino () {
    return dm_Orcado_Destino;
  }

  public double getVL_Saldo () {
    return VL_Saldo;
  }

  public double getVL_Apropriado () {
    return VL_Apropriado;
  }

  public double getVL_Orcado () {
    return VL_Orcado;
  }

  public void setPaiEstrutural(boolean paiEstrutural) {
		this.paiEstrutural = paiEstrutural;
	}


  public void setvl_Limite_Bloqueio_Compromisso (double vl_Limite_Bloqueio_Compromisso) {
    this.vl_Limite_Bloqueio_Compromisso = vl_Limite_Bloqueio_Compromisso;
  }

  public void setDm_Orcado (String dm_Orcado) {

    this.dm_Orcado = dm_Orcado;
  }

  public void setOid_Orcamento_Conta (String oid_Orcamento_Conta) {
    this.oid_Orcamento_Conta = oid_Orcamento_Conta;
  }

  public void setDm_Orcado_Destino (String dm_Orcado_Destino) {
    this.dm_Orcado_Destino = dm_Orcado_Destino;
  }

  public void setVL_Saldo (double VL_Saldo) {
    this.VL_Saldo = VL_Saldo;
  }

  public void setVL_Apropriado (double VL_Apropriado) {
    this.VL_Apropriado = VL_Apropriado;
  }

  public void setVL_Orcado (double VL_Orcado) {
    this.VL_Orcado = VL_Orcado;
  }

  /**
	 * Separa a conta com grau imediatamente inferior
	 */
	public String pegaGrauInferior() {
		int i = this.cd_Estrutural.lastIndexOf(".");
		if (i < 0) {
			return "*";
		}else {
			return this.cd_Estrutural.substring(0, i);
		}
	}
	/**
	 * Calcula o grau ( soma quantos pontos tem o código estrutural )
	 */
	public Integer calcGrau(String cd_Estrutural){
		int cta = 0;
		int sai = 0 ;
                if (cd_Estrutural !=null) {
                  do {
                    sai = cd_Estrutural.indexOf ("." , sai);
                    if (sai > -1) {
                      cta++;
                      sai++;
                    }
                  }
                  while (sai > 0);
                }
		return new Integer(cta);
	}

	public String getDM_Fluxo_Caixa() {
		return DM_Fluxo_Caixa;
	}

	public void setDM_Fluxo_Caixa(String fluxo_Caixa) {
		DM_Fluxo_Caixa = fluxo_Caixa;
	}

	public String getDM_Pedido_Compra() {
		return DM_Pedido_Compra;
	}

	public void setDM_Pedido_Compra(String pedido_Compra) {
		DM_Pedido_Compra = pedido_Compra;
	}

	public String getDM_Vencimento() {
		return DM_Vencimento;
	}

	public void setDM_Vencimento(String vencimento) {
		DM_Vencimento = vencimento;
	}

	public String getCd_Estrutural_Sped() {
		return cd_Estrutural_Sped;
	}

	public void setCd_Estrutural_Sped(String cd_Estrutural_Sped) {
		this.cd_Estrutural_Sped = cd_Estrutural_Sped;
	}
}
