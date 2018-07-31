/*
 * Created on 11/04/2005
 *
 */
package com.master.ed;

import javax.servlet.http.HttpServletResponse;

/**   
 * @author Tiago
 * vai tom * 
 */
public class Movimento_Ordem_ServicoED extends RelatorioBaseED {
    private int oid_ordem_servico;
    private int nr_ordem_servico;
    private int oid_Empresa;
    private int oid_Unidade;
    private String oid_veiculo;
    private String NR_Placa;
    private String NM_Modelo_Veiculo;
    private String dt_inicial;
    private String dt_final;
    private String oid_tipo_servico;
    private String cd_tipo_servico;
    private String nm_tipo_servico;
    private String dm_tipo_despesa;
    private String oid_fornecedor_os;
    private String nr_cnpj_cpf_fornecedor_os;
    private String nm_fornecedor_os;
    private String oid_fornecedor_movimento_os;
    private String nr_cnpj_cpf_fornecedor_movimento_os;
    private String nm_fornecedor_movimento_os;
    private String dt_movimento_ordem_servico;
    private int nr_kilometragem;
    private int nr_kilometragem_diferenca;
    private String nr_documento;
    private double nr_quantidade;
    private double vl_movimento;
    private double media_consumo;
    private String oid_motorista;
    private String nr_cnpj_cpf_motorista;
    private String nm_motorista;
    private String dm_tipo_pagamento;
    private String dm_medida;
    
    //Laszlo
    private long oid_Movimento_Ordem_Servico;
    private String dt_Movimento_Ordem_Servico;
    private String dt_Vencimento;
    private double nr_Quantidade;
    private double vl_Movimento;
    private double nr_Kilometragem;
    private double nr_Odometro;
    private long oid_Tipo_Servico;
    private long oid_Pessoa;
    private long oid_Veiculo;
    private long nr_Documento;
    private long oid_Nota_Fiscal;
    private long oid_Estoque;
    private long oid_Moeda;
    private long oid_Motorista;
    private long nr_Apontador_Ctf;
    private long nr_Numabast_Ctf;
    private String dm_Tipo_Abastec;
    private long oid_Usuario;
    private String dt_Inicial;
    private String dt_Final;
    private String nr_Frota;
    private String nm_Tipo_Servico;
    private String nm_Motorista;
    private double nr_Kilometragem_Percurso;
    private double vl_Media_Abastecimento;
    private double vl_Media_Geral_Abastecimento;
    private String nm_Tipo_Abastec;
    private String nm_Razao_Social;
    private String Nm_Tipo_Veiculo;
    private String Nm_Marca_Veiculo;
    private double vl_Unitario;
    private double Vl_Combinado;
    private double Nr_Media_Inferior;
    private double Nr_Media_Superior;
    private double Vl_Km;
    
    private String dm_Opcao;
    private long oid_Tipo_Veiculo;
    private String nm_Modelo_Veiculo;
    private double vl_Media_Veiculo;
    private double vl_Media_Tipo;
    private double Vl_Media_Modelo;
    private double Vl_Media_Tipo_Modelo;
    private long oid_Modelo_Veiculo;    
    
    public long getOid_Modelo_Veiculo() {
		return oid_Modelo_Veiculo;
	}

	public void setOid_Modelo_Veiculo(long oid_Modelo_Veiculo) {
		this.oid_Modelo_Veiculo = oid_Modelo_Veiculo;
	}

	public String getNm_Marca_Veiculo() {
		return Nm_Marca_Veiculo;
	}

	public void setNm_Marca_Veiculo(String nm_Marca_Veiculo) {
		Nm_Marca_Veiculo = nm_Marca_Veiculo;
	}

	public String getNm_Tipo_Veiculo() {
		return Nm_Tipo_Veiculo;
	}

	public void setNm_Tipo_Veiculo(String nm_Tipo_Veiculo) {
		Nm_Tipo_Veiculo = nm_Tipo_Veiculo;
	}

	public double getNr_Media_Inferior() {
		return Nr_Media_Inferior;
	}

	public void setNr_Media_Inferior(double nr_Media_Inferior) {
		Nr_Media_Inferior = nr_Media_Inferior;
	}

	public double getNr_Media_Superior() {
		return Nr_Media_Superior;
	}

	public void setNr_Media_Superior(double nr_Media_Superior) {
		Nr_Media_Superior = nr_Media_Superior;
	}

	public Movimento_Ordem_ServicoED() {
        super();
    }
    
    public Movimento_Ordem_ServicoED(HttpServletResponse response,
            String nomeRelatorio) {
        super(response, nomeRelatorio);
    }

    public Movimento_Ordem_ServicoED(int oid_ordem_servico,
            int nr_ordem_servico, int oid_Empresa, int oid_Unidade,
            String oid_veiculo, String dt_inicial, String dt_final,
            String oid_tipo_servico, String cd_tipo_servico,
            String nm_tipo_servico, String dm_tipo_despesa,
            String oid_fornecedor_os, String nr_cnpj_cpf_fornecedor_os,
            String nm_fornecedor_os, String oid_fornecedor_movimento_os,
            String nr_cnpj_cpf_fornecedor_movimento_os,
            String nm_fornecedor_movimento_os,
            String dt_movimento_ordem_servico, int nr_kilometragem,
            int nr_kilometragem_diferenca, String nr_documento,
            double nr_quantidade, double vl_movimento, double media_consumo,
            String oid_motorista, String nr_cnpj_cpf_motorista,
            String nm_motorista) {
        super();
        this.oid_ordem_servico = oid_ordem_servico;
        this.nr_ordem_servico = nr_ordem_servico;
        this.oid_Empresa = oid_Empresa;
        this.oid_Unidade = oid_Unidade;
        this.oid_veiculo = oid_veiculo;
        this.dt_inicial = dt_inicial;
        this.dt_final = dt_final;
        this.oid_tipo_servico = oid_tipo_servico;
        this.cd_tipo_servico = cd_tipo_servico;
        this.nm_tipo_servico = nm_tipo_servico;
        this.dm_tipo_despesa = dm_tipo_despesa;
        this.oid_fornecedor_os = oid_fornecedor_os;
        this.nr_cnpj_cpf_fornecedor_os = nr_cnpj_cpf_fornecedor_os;
        this.nm_fornecedor_os = nm_fornecedor_os;
        this.oid_fornecedor_movimento_os = oid_fornecedor_movimento_os;
        this.nr_cnpj_cpf_fornecedor_movimento_os = nr_cnpj_cpf_fornecedor_movimento_os;
        this.nm_fornecedor_movimento_os = nm_fornecedor_movimento_os;
        this.dt_movimento_ordem_servico = dt_movimento_ordem_servico;
        this.nr_kilometragem = nr_kilometragem;
        this.nr_kilometragem_diferenca = nr_kilometragem_diferenca;
        this.nr_documento = nr_documento;
        this.nr_quantidade = nr_quantidade;
        this.vl_movimento = vl_movimento;
        this.media_consumo = media_consumo;
        this.oid_motorista = oid_motorista;
        this.nr_cnpj_cpf_motorista = nr_cnpj_cpf_motorista;
        this.nm_motorista = nm_motorista;
    }
    
    public Object clone() throws CloneNotSupportedException {
        return new Movimento_Ordem_ServicoED(getOid_ordem_servico(),
                getNr_ordem_servico(), 
                getOid_Empresa(), 
                getOid_Unidade(), 
                getOid_veiculo(), 
                getDt_inicial(), 
                getDt_final(), 
                getOid_tipo_servico(), 
                getCd_tipo_servico(), 
                getNm_tipo_servico(), 
                getDm_tipo_despesa(), 
                getOid_fornecedor_os(), 
                getNr_cnpj_cpf_fornecedor_os(), 
                getNm_fornecedor_os(), 
                getOid_fornecedor_movimento_os(), 
                getNr_cnpj_cpf_fornecedor_movimento_os(), 
                getNm_fornecedor_movimento_os(), 
                getDt_movimento_ordem_servico(), 
                getNr_kilometragem(), 
                getNr_kilometragem_diferenca(), 
                getNr_documento(), 
                getNr_quantidade(), 
                getVl_movimento(), 
                getMedia_consumo(), 
                getOid_motorista(), 
                getNr_cnpj_cpf_motorista(), 
                getNm_motorista());
    }
    
    public String getDt_final() {
        return this.dt_final;
    }
    public void setDt_final(String dt_final) {
        this.dt_final = dt_final;
    }
    public String getDt_inicial() {
        return this.dt_inicial;
    }
    public void setDt_inicial(String dt_inicial) {
        this.dt_inicial = dt_inicial;
    }
    public String getOid_fornecedor_movimento_os() {
        return this.oid_fornecedor_movimento_os;
    }
    public void setOid_fornecedor_movimento_os(
            String oid_fornecedor_movimento_os) {
        this.oid_fornecedor_movimento_os = oid_fornecedor_movimento_os;
    }
    public String getOid_fornecedor_os() {
        return this.oid_fornecedor_os;
    }
    public void setOid_fornecedor_os(String oid_fornecedor_os) {
        this.oid_fornecedor_os = oid_fornecedor_os;
    }
    public int getOid_ordem_servico() {
        return this.oid_ordem_servico;
    }
    public void setOid_ordem_servico(int oid_ordem_servico) {
        this.oid_ordem_servico = oid_ordem_servico;
    }
    public String getOid_tipo_servico() {
        return this.oid_tipo_servico;
    }
    public void setOid_tipo_servico(String oid_tipo_servico) {
        this.oid_tipo_servico = oid_tipo_servico;
    }
    public String getOid_veiculo() {
        return this.oid_veiculo;
    }
    public void setOid_veiculo(String oid_veiculo) {
        this.oid_veiculo = oid_veiculo;
    }
    public String getCd_tipo_servico() {
        return this.cd_tipo_servico;
    }
    public void setCd_tipo_servico(String cd_tipo_servico) {
        this.cd_tipo_servico = cd_tipo_servico;
    }
    public String getNm_fornecedor_movimento_os() {
        return this.nm_fornecedor_movimento_os;
    }
    public void setNm_fornecedor_movimento_os(String nm_fornecedor_movimento_os) {
        this.nm_fornecedor_movimento_os = nm_fornecedor_movimento_os;
    }
    public String getNm_fornecedor_os() {
        return this.nm_fornecedor_os;
    }
    public void setNm_fornecedor_os(String nm_fornecedor_os) {
        this.nm_fornecedor_os = nm_fornecedor_os;
    }
    public String getNm_tipo_servico() {
        return this.nm_tipo_servico;
    }
    public void setNm_tipo_servico(String nm_tipo_servico) {
        this.nm_tipo_servico = nm_tipo_servico;
    }
    public int getNr_ordem_servico() {
        return this.nr_ordem_servico;
    }
    public void setNr_ordem_servico(int nr_ordem_servico) {
        this.nr_ordem_servico = nr_ordem_servico;
    }
    public String getDt_movimento_ordem_servico() {
        return this.dt_movimento_ordem_servico;
    }
    public void setDt_movimento_ordem_servico(String dt_movimento_ordem_servico) {
        this.dt_movimento_ordem_servico = dt_movimento_ordem_servico;
    }
    public int getNr_kilometragem() {
        return this.nr_kilometragem;
    }
    public void setNr_kilometragem(int nr_kilometragem) {
        this.nr_kilometragem = nr_kilometragem;
    }
    public String getNr_documento() {
        return this.nr_documento;
    }
    public void setNr_documento(String nr_documento) {
        this.nr_documento = nr_documento;
    }
    public double getNr_quantidade() {
        return this.nr_quantidade;
    }
    public void setNr_quantidade(double nr_quantidade) {
        this.nr_quantidade = nr_quantidade;
    }
    public double getVl_movimento() {
        return this.vl_movimento;
    }
    public void setVl_movimento(double vl_movimento) {
        this.vl_movimento = vl_movimento;
    }
    public String getNr_cnpj_cpf_fornecedor_movimento_os() {
        return this.nr_cnpj_cpf_fornecedor_movimento_os;
    }
    public void setNr_cnpj_cpf_fornecedor_movimento_os(
            String nr_cnpj_cpf_fornecedor_movimento_os) {
        this.nr_cnpj_cpf_fornecedor_movimento_os = nr_cnpj_cpf_fornecedor_movimento_os;
    }
    public String getNr_cnpj_cpf_fornecedor_os() {
        return this.nr_cnpj_cpf_fornecedor_os;
    }
    public void setNr_cnpj_cpf_fornecedor_os(String nr_cnpj_cpf_fornecedor_os) {
        this.nr_cnpj_cpf_fornecedor_os = nr_cnpj_cpf_fornecedor_os;
    }
    public String getDm_tipo_despesa() {
        return this.dm_tipo_despesa;
    }
    public void setDm_tipo_despesa(String dm_tipo_despesa) {
        this.dm_tipo_despesa = dm_tipo_despesa;
    }
    public int getNr_kilometragem_diferenca() {
        return this.nr_kilometragem_diferenca;
    }
    public void setNr_kilometragem_diferenca(int nr_kilometragem_diferenca) {
        this.nr_kilometragem_diferenca = nr_kilometragem_diferenca;
    }
    public double getMedia_consumo() {
        return this.media_consumo;
    }
    public void setMedia_consumo(double media_consumo) {
        this.media_consumo = media_consumo;
    }
    public int getOid_Empresa() {
        return this.oid_Empresa;
    }
    public void setOid_Empresa(int oid_Empresa) {
        this.oid_Empresa = oid_Empresa;
    }
    public int getOid_Unidade() {
        return this.oid_Unidade;
    }
    public void setOid_Unidade(int oid_Unidade) {
        this.oid_Unidade = oid_Unidade;
    }
    public String getNm_motorista() {
        return this.nm_motorista;
    }
    public void setNm_motorista(String nm_motorista) {
        this.nm_motorista = nm_motorista;
    }
    public String getNr_cnpj_cpf_motorista() {
        return this.nr_cnpj_cpf_motorista;
    }
    public void setNr_cnpj_cpf_motorista(String nr_cnpj_cpf_motorista) {
        this.nr_cnpj_cpf_motorista = nr_cnpj_cpf_motorista;
    }
    public String getOid_motorista() {
        return this.oid_motorista;
    }
    public void setOid_motorista(String oid_motorista) {
        this.oid_motorista = oid_motorista;
    }
	public String getNR_Placa() {
		return NR_Placa;
	}
	public void setNR_Placa(String placa) {
		NR_Placa = placa;
	}
	public String getNM_Modelo_Veiculo() {
		return NM_Modelo_Veiculo;
	}
	public void setNM_Modelo_Veiculo(String modelo_Veiculo) {
		NM_Modelo_Veiculo = modelo_Veiculo;
	}
    public String getDm_tipo_pagamento() {
        return dm_tipo_pagamento;
    }
    public void setDm_tipo_pagamento(String dm_tipo_pagamento) {
        this.dm_tipo_pagamento = dm_tipo_pagamento;
    }

	public long getOid_Movimento_Ordem_Servico() {
		return oid_Movimento_Ordem_Servico;
	}

	public void setOid_Movimento_Ordem_Servico(long oid_Movimento_Ordem_Servico) {
		this.oid_Movimento_Ordem_Servico = oid_Movimento_Ordem_Servico;
	}

	public String getDm_Tipo_Abastec() {
		return dm_Tipo_Abastec;
	}

	public void setDm_Tipo_Abastec(String dm_Tipo_Abastec) {
		this.dm_Tipo_Abastec = dm_Tipo_Abastec;
	}

	public String getDt_Movimento_Ordem_Servico() {
		return dt_Movimento_Ordem_Servico;
	}

	public void setDt_Movimento_Ordem_Servico(String dt_Movimento_Ordem_Servico) {
		this.dt_Movimento_Ordem_Servico = dt_Movimento_Ordem_Servico;
	}

	public String getDt_Vencimento() {
		return dt_Vencimento;
	}

	public void setDt_Vencimento(String dt_Vencimento) {
		this.dt_Vencimento = dt_Vencimento;
	}

	public long getNr_Apontador_Ctf() {
		return nr_Apontador_Ctf;
	}

	public void setNr_Apontador_Ctf(long nr_Apontador_Ctf) {
		this.nr_Apontador_Ctf = nr_Apontador_Ctf;
	}

	public long getNr_Documento() {
		return nr_Documento;
	}

	public void setNr_Documento(long nr_Documento) {
		this.nr_Documento = nr_Documento;
	}

	public double getNr_Kilometragem() {
		return nr_Kilometragem;
	}

	public void setNr_Kilometragem(double nr_Kilometragem) {
		this.nr_Kilometragem = nr_Kilometragem;
	}

	public long getNr_Numabast_Ctf() {
		return nr_Numabast_Ctf;
	}

	public void setNr_Numabast_Ctf(long nr_Numabast_Ctf) {
		this.nr_Numabast_Ctf = nr_Numabast_Ctf;
	}

	public double getNr_Odometro() {
		return nr_Odometro;
	}

	public void setNr_Odometro(double nr_Odometro) {
		this.nr_Odometro = nr_Odometro;
	}
	
	public long getOid_Estoque() {
		return oid_Estoque;
	}

	public void setOid_Estoque(long oid_Estoque) {
		this.oid_Estoque = oid_Estoque;
	}

	public long getOid_Moeda() {
		return oid_Moeda;
	}

	public void setOid_Moeda(long oid_Moeda) {
		this.oid_Moeda = oid_Moeda;
	}

	public long getOid_Motorista() {
		return oid_Motorista;
	}

	public void setOid_Motorista(long oid_Motorista) {
		this.oid_Motorista = oid_Motorista;
	}

	public long getOid_Nota_Fiscal() {
		return oid_Nota_Fiscal;
	}

	public void setOid_Nota_Fiscal(long oid_Nota_Fiscal) {
		this.oid_Nota_Fiscal = oid_Nota_Fiscal;
	}

	public long getOid_Pessoa() {
		return oid_Pessoa;
	}

	public void setOid_Pessoa(long oid_Pessoa) {
		this.oid_Pessoa = oid_Pessoa;
	}

	public long getOid_Tipo_Servico() {
		return oid_Tipo_Servico;
	}

	public void setOid_Tipo_Servico(long oid_Tipo_Servico) {
		this.oid_Tipo_Servico = oid_Tipo_Servico;
	}

	public long getOid_Usuario() {
		return oid_Usuario;
	}

	public void setOid_Usuario(long oid_Usuario) {
		this.oid_Usuario = oid_Usuario;
	}

	public long getOid_Veiculo() {
		return oid_Veiculo;
	}

	public void setOid_Veiculo(long oid_Veiculo) {
		this.oid_Veiculo = oid_Veiculo;
	}

	public double getVl_Movimento() {
		return vl_Movimento;
	}

	public void setVl_Movimento(double vl_Movimento) {
		this.vl_Movimento = vl_Movimento;
	}

	public String getDt_Final() {
		return dt_Final;
	}

	public void setDt_Final(String dt_Final) {
		this.dt_Final = dt_Final;
	}

	public String getDt_Inicial() {
		return dt_Inicial;
	}

	public void setDt_Inicial(String dt_Inicial) {
		this.dt_Inicial = dt_Inicial;
	}

	public String getNr_Frota() {
		return nr_Frota;
	}

	public void setNr_Frota(String nr_Frota) {
		this.nr_Frota = nr_Frota;
	}

	public String getNm_Tipo_Servico() {
		return nm_Tipo_Servico;
	}

	public void setNm_Tipo_Servico(String nm_Tipo_Servico) {
		this.nm_Tipo_Servico = nm_Tipo_Servico;
	}

	public String getNm_Motorista() {
		return nm_Motorista;
	}

	public void setNm_Motorista(String nm_Motorista) {
		this.nm_Motorista = nm_Motorista;
	}

	public double getNr_Quantidade() {
		return nr_Quantidade;
	}

	public void setNr_Quantidade(double nr_Quantidade) {
		this.nr_Quantidade = nr_Quantidade;
	}

	public double getNr_Kilometragem_Percurso() {
		return nr_Kilometragem_Percurso;
	}

	public void setNr_Kilometragem_Percurso(double nr_Kilometragem_Percurso) {
		this.nr_Kilometragem_Percurso = nr_Kilometragem_Percurso;
	}

	public double getVl_Media_Abastecimento() {
		return vl_Media_Abastecimento;
	}

	public void setVl_Media_Abastecimento(double vl_Media_Abastecimento) {
		this.vl_Media_Abastecimento = vl_Media_Abastecimento;
	}

	public double getVl_Media_Geral_Abastecimento() {
		return vl_Media_Geral_Abastecimento;
	}

	public void setVl_Media_Geral_Abastecimento(double vl_Media_Geral_Abastecimento) {
		this.vl_Media_Geral_Abastecimento = vl_Media_Geral_Abastecimento;
	}

	public String getNm_Tipo_Abastec() {
		return nm_Tipo_Abastec;
	}

	public void setNm_Tipo_Abastec(String nm_Tipo_Abastec) {
		this.nm_Tipo_Abastec = nm_Tipo_Abastec;
	}

	public String getNm_Razao_Social() {
		return nm_Razao_Social;
	}

	public void setNm_Razao_Social(String nm_Razao_Social) {
		this.nm_Razao_Social = nm_Razao_Social;
	}

	public double getVl_Unitario() {
		return vl_Unitario;
	}

	public void setVl_Unitario(double vl_Unitario) {
		this.vl_Unitario = vl_Unitario;
	}

	public double getVl_Combinado() {
		return Vl_Combinado;
	}

	public void setVl_Combinado(double vl_Combinado) {
		Vl_Combinado = vl_Combinado;
	}

	public double getVl_Km() {
		return Vl_Km;
	}

	public void setVl_Km(double vl_Km) {
		Vl_Km = vl_Km;
	}

	public String getDm_Opcao() {
		return dm_Opcao;
	}

	public void setDm_Opcao(String dm_Opcao) {
		this.dm_Opcao = dm_Opcao;
	}

	public String getNm_Modelo_Veiculo() {
		return nm_Modelo_Veiculo;
	}

	public void setNm_Modelo_Veiculo(String nm_Modelo_Veiculo) {
		this.nm_Modelo_Veiculo = nm_Modelo_Veiculo;
	}

	public long getOid_Tipo_Veiculo() {
		return oid_Tipo_Veiculo;
	}

	public void setOid_Tipo_Veiculo(long oid_Tipo_Veiculo) {
		this.oid_Tipo_Veiculo = oid_Tipo_Veiculo;
	}

	public double getVl_Media_Veiculo() {
		return vl_Media_Veiculo;
	}

	public void setVl_Media_Veiculo(double vl_Media_Veiculo) {
		this.vl_Media_Veiculo = vl_Media_Veiculo;
	}

	public double getVl_Media_Modelo() {
		return Vl_Media_Modelo;
	}

	public void setVl_Media_Modelo(double vl_Media_Modelo) {
		Vl_Media_Modelo = vl_Media_Modelo;
	}

	public double getVl_Media_Tipo() {
		return vl_Media_Tipo;
	}

	public void setVl_Media_Tipo(double vl_Media_Tipo) {
		this.vl_Media_Tipo = vl_Media_Tipo;
	}

	public double getVl_Media_Tipo_Modelo() {
		return Vl_Media_Tipo_Modelo;
	}

	public void setVl_Media_Tipo_Modelo(double vl_Media_Tipo_Modelo) {
		Vl_Media_Tipo_Modelo = vl_Media_Tipo_Modelo;
	}

	public String getDm_medida() {
		return dm_medida;
	}

	public void setDm_medida(String dm_medida) {
		this.dm_medida = dm_medida;
	}
}