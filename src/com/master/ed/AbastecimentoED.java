/*
 * Created on 11/04/2005
 *
 */
package com.master.ed;


/**
 * @author Régis
 */

public class AbastecimentoED extends RelatorioBaseED {

	private static final long serialVersionUID = -8957829581902199225L;
	
	//Laszlo
    private long oid_Abastecimento;
    private long oid_Empresa;
    private String oid_Veiculo;
    private String oid_Motorista;
    private long oid_Bomba;
    private long oid_Pessoa;
    private String oid_Fornecedor;
    private String dm_Tipo_Abastec;
    private String dm_Completo;
    private String dt_Abastecimento;
    private String dt_Vencimento;
    private double nr_Quantidade;
    private double vl_Abastecimento;
    private double nr_Kilometragem;
    private double nr_Odometro;
    private String nr_Documento;
    private long nr_Apontador_Ctf;
    private long nr_Numabast_Ctf;
    private String dt_Debito_Ctf;
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
    private double vl_Combinado;
    private double vl_Km;
    private double nr_Media_Inferior;
    private double nr_Media_Superior;
    private String nm_Marca_Veiculo;
    private String nm_Modelo_Veiculo;
    private String nm_Tipo_Veiculo;
    private String dm_Virada;
    
    private String dm_Stamp;
    private String dt_Stamp;
    private String usuario_Stamp;
    
    // Para uso interno apenas ....
    private double nr_Kilometragem_Percurso_Media;
    private double nr_Quantidade_Media;
    private double vl_Abastecimento_Media;
    
    public AbastecimentoED() {
        super();
    }

	public String getDm_Completo() {
		return dm_Completo;
	}

	public void setDm_Completo(String dm_Completo) {
		this.dm_Completo = dm_Completo;
	}

	public String getDm_Stamp() {
		return dm_Stamp;
	}

	public void setDm_Stamp(String dm_Stamp) {
		this.dm_Stamp = dm_Stamp;
	}

	public String getDm_Tipo_Abastec() {
		return dm_Tipo_Abastec;
	}

	public void setDm_Tipo_Abastec(String dm_Tipo_Abastec) {
		this.dm_Tipo_Abastec = dm_Tipo_Abastec;
	}

	public String getDt_Abastecimento() {
		return dt_Abastecimento;
	}

	public void setDt_Abastecimento(String dt_Abastecimento) {
		this.dt_Abastecimento = dt_Abastecimento;
	}

	public String getDt_Debito_Ctf() {
		return dt_Debito_Ctf;
	}

	public void setDt_Debito_Ctf(String dt_Debito_Ctf) {
		this.dt_Debito_Ctf = dt_Debito_Ctf;
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

	public String getDt_Stamp() {
		return dt_Stamp;
	}

	public void setDt_Stamp(String dt_Stamp) {
		this.dt_Stamp = dt_Stamp;
	}

	public String getDt_Vencimento() {
		return dt_Vencimento;
	}

	public void setDt_Vencimento(String dt_Vencimento) {
		this.dt_Vencimento = dt_Vencimento;
	}

	public String getNm_Marca_Veiculo() {
		return nm_Marca_Veiculo;
	}

	public void setNm_Marca_Veiculo(String nm_Marca_Veiculo) {
		this.nm_Marca_Veiculo = nm_Marca_Veiculo;
	}

	public String getNm_Modelo_Veiculo() {
		return nm_Modelo_Veiculo;
	}

	public void setNm_Modelo_Veiculo(String nm_Modelo_Veiculo) {
		this.nm_Modelo_Veiculo = nm_Modelo_Veiculo;
	}

	public String getNm_Motorista() {
		return nm_Motorista;
	}

	public void setNm_Motorista(String nm_Motorista) {
		this.nm_Motorista = nm_Motorista;
	}

	public String getNm_Razao_Social() {
		return nm_Razao_Social;
	}

	public void setNm_Razao_Social(String nm_Razao_Social) {
		this.nm_Razao_Social = nm_Razao_Social;
	}

	public String getNm_Tipo_Abastec() {
		return nm_Tipo_Abastec;
	}

	public void setNm_Tipo_Abastec(String nm_Tipo_Abastec) {
		this.nm_Tipo_Abastec = nm_Tipo_Abastec;
	}

	public String getNm_Tipo_Servico() {
		return nm_Tipo_Servico;
	}

	public void setNm_Tipo_Servico(String nm_Tipo_Servico) {
		this.nm_Tipo_Servico = nm_Tipo_Servico;
	}

	public long getNr_Apontador_Ctf() {
		return nr_Apontador_Ctf;
	}

	public void setNr_Apontador_Ctf(long nr_Apontador_Ctf) {
		this.nr_Apontador_Ctf = nr_Apontador_Ctf;
	}

	public String getNr_Documento() {
		return nr_Documento;
	}

	public void setNr_Documento(String nr_Documento) {
		this.nr_Documento = nr_Documento;
	}

	public String getNr_Frota() {
		return nr_Frota;
	}

	public void setNr_Frota(String nr_Frota) {
		this.nr_Frota = nr_Frota;
	}

	public double getNr_Kilometragem() {
		return nr_Kilometragem;
	}

	public void setNr_Kilometragem(double nr_Kilometragem) {
		this.nr_Kilometragem = nr_Kilometragem;
	}

	public double getNr_Kilometragem_Percurso() {
		return nr_Kilometragem_Percurso;
	}

	public void setNr_Kilometragem_Percurso(double nr_Kilometragem_Percurso) {
		this.nr_Kilometragem_Percurso = nr_Kilometragem_Percurso;
	}

	public double getNr_Kilometragem_Percurso_Media() {
		return nr_Kilometragem_Percurso_Media;
	}

	public void setNr_Kilometragem_Percurso_Media(
			double nr_Kilometragem_Percurso_Media) {
		this.nr_Kilometragem_Percurso_Media = nr_Kilometragem_Percurso_Media;
	}

	public double getNr_Media_Inferior() {
		return nr_Media_Inferior;
	}

	public void setNr_Media_Inferior(double nr_Media_Inferior) {
		this.nr_Media_Inferior = nr_Media_Inferior;
	}

	public double getNr_Media_Superior() {
		return nr_Media_Superior;
	}

	public void setNr_Media_Superior(double nr_Media_Superior) {
		this.nr_Media_Superior = nr_Media_Superior;
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

	public double getNr_Quantidade() {
		return nr_Quantidade;
	}

	public void setNr_Quantidade(double nr_Quantidade) {
		this.nr_Quantidade = nr_Quantidade;
	}

	public double getNr_Quantidade_Media() {
		return nr_Quantidade_Media;
	}

	public void setNr_Quantidade_Media(double nr_Quantidade_Media) {
		this.nr_Quantidade_Media = nr_Quantidade_Media;
	}

	public long getOid_Abastecimento() {
		return oid_Abastecimento;
	}

	public void setOid_Abastecimento(long oid_Abastecimento) {
		this.oid_Abastecimento = oid_Abastecimento;
	}

	public long getOid_Bomba() {
		return oid_Bomba;
	}

	public void setOid_Bomba(long oid_Bomba) {
		this.oid_Bomba = oid_Bomba;
	}

	public long getOid_Empresa() {
		return oid_Empresa;
	}

	public void setOid_Empresa(long oid_Empresa) {
		this.oid_Empresa = oid_Empresa;
	}

	public String getOid_Fornecedor() {
		return oid_Fornecedor;
	}

	public void setOid_Fornecedor(String oid_Fornecedor) {
		this.oid_Fornecedor = oid_Fornecedor;
	}

	public String getOid_Motorista() {
		return oid_Motorista;
	}

	public void setOid_Motorista(String oid_Motorista) {
		this.oid_Motorista = oid_Motorista;
	}

	public long getOid_Pessoa() {
		return oid_Pessoa;
	}

	public void setOid_Pessoa(long oid_Pessoa) {
		this.oid_Pessoa = oid_Pessoa;
	}

	public long getOid_Usuario() {
		return oid_Usuario;
	}

	public void setOid_Usuario(long oid_Usuario) {
		this.oid_Usuario = oid_Usuario;
	}

	public String getOid_Veiculo() {
		return oid_Veiculo;
	}

	public void setOid_Veiculo(String oid_Veiculo) {
		this.oid_Veiculo = oid_Veiculo;
	}

	public String getUsuario_Stamp() {
		return usuario_Stamp;
	}

	public void setUsuario_Stamp(String usuario_Stamp) {
		this.usuario_Stamp = usuario_Stamp;
	}

	public double getVl_Abastecimento() {
		return vl_Abastecimento;
	}

	public void setVl_Abastecimento(double vl_Abastecimento) {
		this.vl_Abastecimento = vl_Abastecimento;
	}

	public double getVl_Abastecimento_Media() {
		return vl_Abastecimento_Media;
	}

	public void setVl_Abastecimento_Media(double vl_Abastecimento_Media) {
		this.vl_Abastecimento_Media = vl_Abastecimento_Media;
	}

	public double getVl_Combinado() {
		return vl_Combinado;
	}

	public void setVl_Combinado(double vl_Combinado) {
		this.vl_Combinado = vl_Combinado;
	}

	public double getVl_Km() {
		return vl_Km;
	}

	public void setVl_Km(double vl_Km) {
		this.vl_Km = vl_Km;
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

	public String getDm_Virada() {
		return dm_Virada;
	}

	public void setDm_Virada(String dm_Virada) {
		this.dm_Virada = dm_Virada;
	}

	public String getNm_Tipo_Veiculo() {
		return nm_Tipo_Veiculo;
	}

	public void setNm_Tipo_Veiculo(String nm_Tipo_Veiculo) {
		this.nm_Tipo_Veiculo = nm_Tipo_Veiculo;
	}

   
}