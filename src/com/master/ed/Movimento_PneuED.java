package com.master.ed;

import com.master.root.VeiculoBean;

/**
 * @author André Valadas - Movimento Pneus
 */
public class Movimento_PneuED extends RelatorioBaseED {

	private static final long serialVersionUID = -4419150559050102218L;

	public Movimento_PneuED() {
		super();
	}

	public Movimento_PneuED(int movimento_Pneu) {
		oid_Movimento_Pneu = movimento_Pneu;
	}

	public Movimento_PneuED(String localizacao, String situacao,
			String inclusao, String remocao, int km_inicial, int km_final,
			int km_pneu, int oid_pneu, String oid_veiculo, String observacao) {
		DM_Localizacao = localizacao;
		DM_Situacao = situacao;
		DT_Inclusao = inclusao;
		DT_Remocao = remocao;
		KM_Inicial = km_inicial;
		KM_Final = km_final;
		KM_Pneu = km_pneu;
		oid_Pneu = oid_pneu;
		oid_Veiculo = oid_veiculo;
		TX_Observacao = observacao;
	}

	public PneuED edPneu = new PneuED();

	public VeiculoBean edVeiculo = new VeiculoBean();

	private int oid_Movimento_Pneu;

	private int oid_Pneu;

	private int KM_Pneu;

	private String DM_Localizacao;

	private String DM_Situacao;

	private String oid_Veiculo;

	private String DT_Inclusao;

	private int KM_Inicial;

	private String DT_Remocao;

	private int KM_Final;

	private String HR_Alteracao;

	private String TX_Observacao;

	// Laszlo //
	private long oid_Empresa;

	private String dt_Entrada;

	private double nr_Odometro_Entrada;

	private double nr_Km_Acumulada_Entrada;

	private String dt_Saida;

	private double nr_Odometro_Saida;

	private double nr_Km_Acumulada_Saida;

	private String dm_Posicao;

	private String dm_Eixo;

	private String tx_Motivo;

	private long oid_Pneu_Colocado;

	private long nr_Vida;

	private double nr_Odometro_Veiculo;

	private double nr_Km_Acumulada_Veiculo;

	private double nr_Media_Comparacao;

	private String dm_Rodou_Pouco;

	private double nr_Mm_Entrada;

	private double nr_Mm_Saida;

	private String nr_Fogo_Entrada;

	private String nr_Fogo_Saida;

	private String nr_Frota;

	private long oid_Local_Estoque;

	private String dm_Tipo_Movimento;

	private String dm_Posicao_Destino;

	private double nr_Km_Veiculo;

	private String dt_Inicio;

	private String dt_Fim;

	private String nr_Fogo;

	private String dt_Inicial;

	private String dt_Final;

	private String dt_Inicial_Saida;

	private String dt_Final_Saida;

	private String dt_Ultima_Recapagem;

	private String dt_Nota_Fiscal;

	private double vl_Pneu;

	private String nm_Modelo_Pneu;

	private String nm_Dimensao_Pneu;

	private String nm_Fabricante_Pneu;

	private String nm_Razao_Social;

	private String nm_Vida;

	public String getDesc_Localizacao() {
		if ("01".equals(DM_Localizacao)) {
			return "Estoque";
		} else if ("02".equals(DM_Localizacao)) {
			return "Manutenção";
		} else if ("03".equals(DM_Localizacao)) {
			return "Veículo";
		} else {
			return "Localização inválida";
		}
	}

	public String getDesc_Situacao() {
		if ("1".equals(DM_Situacao)) {
			return "Novo";
		} else if ("2".equals(DM_Situacao)) {
			return "Primeira recapagem";
		} else if ("3".equals(DM_Situacao)) {
			return "Segunda recapagem";
		} else if ("4".equals(DM_Situacao)) {
			return "Terceira recapagem";
		} else if ("8".equals(DM_Situacao)) {
			return "Sucata";
		} else if ("9".equals(DM_Situacao)) {
			return "Descarte";
		} else {
			return "Situação inválida";
		}
	}

	public String getDM_Localizacao() {
		return DM_Localizacao;
	}

	public void setDM_Localizacao(String localizacao) {
		DM_Localizacao = localizacao;
	}

	public String getDM_Situacao() {
		return DM_Situacao;
	}

	public void setDM_Situacao(String situacao) {
		DM_Situacao = situacao;
	}

	public String getDT_Inclusao() {
		return DT_Inclusao;
	}

	public void setDT_Inclusao(String inclusao) {
		DT_Inclusao = inclusao;
	}

	public String getDT_Remocao() {
		return DT_Remocao;
	}

	public void setDT_Remocao(String remocao) {
		DT_Remocao = remocao;
	}

	public PneuED getEdPneu() {
		return edPneu;
	}

	public void setEdPneu(PneuED edPneu) {
		this.edPneu = edPneu;
	}

	public VeiculoBean getEdVeiculo() {
		return edVeiculo;
	}

	public void setEdVeiculo(VeiculoBean edVeiculo) {
		this.edVeiculo = edVeiculo;
	}

	public int getKM_Pneu() {
		return KM_Pneu;
	}

	public void setKM_Pneu(int pneu) {
		KM_Pneu = pneu;
	}

	public int getOid_Pneu() {
		return oid_Pneu;
	}

	public void setOid_Pneu(int oid_Pneu) {
		this.oid_Pneu = oid_Pneu;
	}

	public String getOid_Veiculo() {
		return oid_Veiculo;
	}

	public void setOid_Veiculo(String oid_Veiculo) {
		this.oid_Veiculo = oid_Veiculo;
	}

	public String getTX_Observacao() {
		return TX_Observacao;
	}

	public void setTX_Observacao(String observacao) {
		TX_Observacao = observacao;
	}

	public int getOid_Movimento_Pneu() {
		return oid_Movimento_Pneu;
	}

	public void setOid_Movimento_Pneu(int oid_Movimento_Pneu) {
		this.oid_Movimento_Pneu = oid_Movimento_Pneu;
	}

	public int getKM_Final() {
		return KM_Final;
	}

	public void setKM_Final(int final1) {
		KM_Final = final1;
	}

	public int getKM_Inicial() {
		return KM_Inicial;
	}

	public void setKM_Inicial(int inicial) {
		KM_Inicial = inicial;
	}

	public String getHR_Alteracao() {
		return HR_Alteracao;
	}

	public void setHR_Alteracao(String alteracao) {
		HR_Alteracao = alteracao;
	}

	public String getDm_Eixo() {
		return dm_Eixo;
	}

	public void setDm_Eixo(String dm_Eixo) {
		this.dm_Eixo = dm_Eixo;
	}

	public String getDm_Posicao() {
		return dm_Posicao;
	}

	public void setDm_Posicao(String dm_Posicao) {
		this.dm_Posicao = dm_Posicao;
	}

	public String getDm_Rodou_Pouco() {
		return dm_Rodou_Pouco;
	}

	public void setDm_Rodou_Pouco(String dm_Rodou_Pouco) {
		this.dm_Rodou_Pouco = dm_Rodou_Pouco;
	}

	public String getDt_Entrada() {
		return dt_Entrada;
	}

	public void setDt_Entrada(String dt_Entrada) {
		this.dt_Entrada = dt_Entrada;
	}

	public String getDt_Saida() {
		return dt_Saida;
	}

	public void setDt_Saida(String dt_Saida) {
		this.dt_Saida = dt_Saida;
	}

	public double getNr_Km_Acumulada_Entrada() {
		return nr_Km_Acumulada_Entrada;
	}

	public void setNr_Km_Acumulada_Entrada(double nr_Km_Acumulada_Entrada) {
		this.nr_Km_Acumulada_Entrada = nr_Km_Acumulada_Entrada;
	}

	public double getNr_Km_Acumulada_Saida() {
		return nr_Km_Acumulada_Saida;
	}

	public void setNr_Km_Acumulada_Saida(double nr_Km_Acumulada_Saida) {
		this.nr_Km_Acumulada_Saida = nr_Km_Acumulada_Saida;
	}

	public double getNr_Km_Acumulada_Veiculo() {
		return nr_Km_Acumulada_Veiculo;
	}

	public void setNr_Km_Acumulada_Veiculo(double nr_Km_Acumulada_Veiculo) {
		this.nr_Km_Acumulada_Veiculo = nr_Km_Acumulada_Veiculo;
	}

	public double getNr_Media_Comparacao() {
		return nr_Media_Comparacao;
	}

	public void setNr_Media_Comparacao(double nr_Media_Comparacao) {
		this.nr_Media_Comparacao = nr_Media_Comparacao;
	}

	public double getNr_Odometro_Entrada() {
		return nr_Odometro_Entrada;
	}

	public void setNr_Odometro_Entrada(double nr_Odometro_Entrada) {
		this.nr_Odometro_Entrada = nr_Odometro_Entrada;
	}

	public double getNr_Odometro_Saida() {
		return nr_Odometro_Saida;
	}

	public void setNr_Odometro_Saida(double nr_Odometro_Saida) {
		this.nr_Odometro_Saida = nr_Odometro_Saida;
	}

	public double getNr_Odometro_Veiculo() {
		return nr_Odometro_Veiculo;
	}

	public void setNr_Odometro_Veiculo(double nr_Odometro_Veiculo) {
		this.nr_Odometro_Veiculo = nr_Odometro_Veiculo;
	}

	public long getNr_Vida() {
		return nr_Vida;
	}

	public void setNr_Vida(long nr_Vida) {
		this.nr_Vida = nr_Vida;
	}

	public long getOid_Pneu_Colocado() {
		return oid_Pneu_Colocado;
	}

	public void setOid_Pneu_Colocado(long oid_Pneu_Colocado) {
		this.oid_Pneu_Colocado = oid_Pneu_Colocado;
	}

	public String getTx_Motivo() {
		return tx_Motivo;
	}

	public void setTx_Motivo(String tx_Motivo) {
		this.tx_Motivo = tx_Motivo;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public long getOid_Empresa() {
		return oid_Empresa;
	}

	public void setOid_Empresa(long oid_Empresa) {
		this.oid_Empresa = oid_Empresa;
	}

	public double getNr_Mm_Entrada() {
		return nr_Mm_Entrada;
	}

	public void setNr_Mm_Entrada(double nr_Mm_Entrada) {
		this.nr_Mm_Entrada = nr_Mm_Entrada;
	}

	public double getNr_Mm_Saida() {
		return nr_Mm_Saida;
	}

	public void setNr_Mm_Saida(double nr_Mm_Saida) {
		this.nr_Mm_Saida = nr_Mm_Saida;
	}

	public String getNr_Fogo_Entrada() {
		return nr_Fogo_Entrada;
	}

	public void setNr_Fogo_Entrada(String nr_Fogo_Entrada) {
		this.nr_Fogo_Entrada = nr_Fogo_Entrada;
	}

	public String getNr_Fogo_Saida() {
		return nr_Fogo_Saida;
	}

	public void setNr_Fogo_Saida(String nr_Fogo_Saida) {
		this.nr_Fogo_Saida = nr_Fogo_Saida;
	}

	public String getNr_Frota() {
		return nr_Frota;
	}

	public void setNr_Frota(String nr_Frota) {
		this.nr_Frota = nr_Frota;
	}

	public long getOid_Local_Estoque() {
		return oid_Local_Estoque;
	}

	public void setOid_Local_Estoque(long oid_Local_Estoque) {
		this.oid_Local_Estoque = oid_Local_Estoque;
	}

	public String getDm_Tipo_Movimento() {
		return dm_Tipo_Movimento;
	}

	public void setDm_Tipo_Movimento(String dm_Tipo_Movimento) {
		this.dm_Tipo_Movimento = dm_Tipo_Movimento;
	}

	public String getDm_Posicao_Destino() {
		return dm_Posicao_Destino;
	}

	public void setDm_Posicao_Destino(String dm_Posicao_Destino) {
		this.dm_Posicao_Destino = dm_Posicao_Destino;
	}

	public double getNr_Km_Veiculo() {
		return nr_Km_Veiculo;
	}

	public void setNr_Km_Veiculo(double nr_Km_Veiculo) {
		this.nr_Km_Veiculo = nr_Km_Veiculo;
	}

	public String getDt_Fim() {
		return dt_Fim;
	}

	public void setDt_Fim(String dt_Fim) {
		this.dt_Fim = dt_Fim;
	}

	public String getDt_Inicio() {
		return dt_Inicio;
	}

	public void setDt_Inicio(String dt_Inicio) {
		this.dt_Inicio = dt_Inicio;
	}

	public String getNr_Fogo() {
		return nr_Fogo;
	}

	public void setNr_Fogo(String nr_Fogo) {
		this.nr_Fogo = nr_Fogo;
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

	public String getDt_Ultima_Recapagem() {
		return dt_Ultima_Recapagem;
	}

	public void setDt_Ultima_Recapagem(String dt_Ultima_Recapagem) {
		this.dt_Ultima_Recapagem = dt_Ultima_Recapagem;
	}

	public String getDt_Nota_Fiscal() {
		return dt_Nota_Fiscal;
	}

	public void setDt_Nota_Fiscal(String dt_Nota_Fiscal) {
		this.dt_Nota_Fiscal = dt_Nota_Fiscal;
	}

	public String getNm_Dimensao_Pneu() {
		return nm_Dimensao_Pneu;
	}

	public void setNm_Dimensao_Pneu(String nm_Dimensao_Pneu) {
		this.nm_Dimensao_Pneu = nm_Dimensao_Pneu;
	}

	public String getNm_Fabricante_Pneu() {
		return nm_Fabricante_Pneu;
	}

	public void setNm_Fabricante_Pneu(String nm_Fabricante_Pneu) {
		this.nm_Fabricante_Pneu = nm_Fabricante_Pneu;
	}

	public String getNm_Modelo_Pneu() {
		return nm_Modelo_Pneu;
	}

	public void setNm_Modelo_Pneu(String nm_Modelo_Pneu) {
		this.nm_Modelo_Pneu = nm_Modelo_Pneu;
	}

	public String getNm_Razao_Social() {
		return nm_Razao_Social;
	}

	public void setNm_Razao_Social(String nm_Razao_Social) {
		this.nm_Razao_Social = nm_Razao_Social;
	}

	public String getNm_Vida() {
		String nm = null;
		if (this.getNr_Vida() == 0)
			nm = "Novo";
		if (this.getNr_Vida() == 1)
			nm = "1º rec";
		if (this.getNr_Vida() == 2)
			nm = "2º rec";
		if (this.getNr_Vida() == 3)
			nm = "3º rec";
		if (this.getNr_Vida() == 4)
			nm = "4º rec";
		if (this.getNr_Vida() == 5)
			nm = "5º rec";
		if (this.getNr_Vida() == 6)
			nm = "6º rec";
		return nm;
	}

	public void setNm_Vida(String nm_Vida) {
		this.nm_Vida = nm_Vida;
	}

	public double getVl_Pneu() {
		return vl_Pneu;
	}

	public void setVl_Pneu(double vl_Pneu) {
		this.vl_Pneu = vl_Pneu;
	}

	public String getDt_Final_Saida() {
		return dt_Final_Saida;
	}

	public void setDt_Final_Saida(String dt_Final_Saida) {
		this.dt_Final_Saida = dt_Final_Saida;
	}

	public String getDt_Inicial_Saida() {
		return dt_Inicial_Saida;
	}

	public void setDt_Inicial_Saida(String dt_Inicial_Saida) {
		this.dt_Inicial_Saida = dt_Inicial_Saida;
	}
}