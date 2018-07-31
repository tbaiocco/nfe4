/*
 * Created on 12/11/2004
 *
 */
package com.master.ed;

/**
 * @author Tiago Sauter Lauxen
 * @author Ralph - Alterado - 07/07
 * @author Cristian - Alterado(Última Recapagem) - 07/07
 */
public class PneuED extends RelatorioBaseED {

	private static final long serialVersionUID = 2192965595346433838L;

	private int oid_Pneu;
	private String CD_Pneu;
	private String NR_Fabrica;

	private int oid_Fabricante_Pneu;
	//campos fk
	private String CD_Fabricante_Pneu;
	private String NM_Fabricante_Pneu;

	private long oid_Dimensao_Pneu;
	//campos fk
	private String nm_Dimensao_Pneu;

	private long oid_Tipo_Pneu;
	//campos fk
	private String nm_Tipo_Pneu;

	private int oid_Modelo_Pneu;
	//campos fk
	private String CD_Modelo_Pneu;
	private String NM_Modelo_Pneu;

	private double nr_Km_Acumulada;
	private String tx_Lonas;
	private String nr_Serie;

	private String oid_Fornecedor;
	//campos fk
	private String nr_Cnpj_Cpf;
	private String nm_Fornecedor;

	//	Compra
	private String dt_Nota_Fiscal;
	private long nr_Nota_Fiscal;
	private double vl_Preco;
	private double MM_Atual;
	private long nr_Perimetro;
	private String tx_Dot;
	private long nr_Vida;
	private long oid_Local_Estoque;
	private String nm_Local_Estoque;
	private String dt_Estoque;
	private String dm_Controle_Parcial;
	private long oid_Empresa;

	//fim do que interessa ao cadastro

	private String DM_Situacao;

	private String DM_Localizacao;

	private String DM_Posicao;

	private int KM_Atual;

	private String oid_Veiculo;

	private String NR_Placa;

	private String NR_Frota;

	private String notINTO_Loc;

	private String notINTO_Sit;

	public long oid_estoque;
	private String nm_estoque;

	private String cd_Item_Estoque;

	private String nm_Modelo_Pneu;

	private String nm_Fabricante_Pneu;

	private String nm_Motivo_Sucateamento;

	private long oid_Unidade;
	private String cd_Unidade;
	private String nm_Unidade;

	private int KM_Final;

	// LASZLO
	private double nr_Mm_Saida;

	private String nr_Fogo;

	private String dm_Virada;

	private String tx_Situacao;

	private double nr_Km_Inicial;

	private double nr_Mm_Inicial;

	private String dm_Tipo_Pneu;

	private String dm_Fabricante_Pneu;

	private String dm_Dimensao_Pneu;

	private String dm_Modelo_Pneu;

	private String dm_Vida_Pneu;

	private String dm_Perimetro;

	private String dm_MM_Atual;

	private String dm_Considera_Sucateados;

	private String Desc_Eixo;

	private String Desc_Localizacao_Pneu;

	private String dt_Inicial_Compra;

	private String dt_Final_Compra;

	private String nm_Razao_Social;

	private double MM;

	//Na frota
	private String dt_Entrada;

	private double nr_Km_Acumulada_Veiculo;

	private double nr_Hodometro_Veiculo;

	private String dm_Eixo;

	//Sucateado
	private String dt_Sucateamento;

	private long oid_Motivo_Sucateamento;

	private double nr_Mm_Sulco_Maior;

	private double nr_Mm_Sulco_Menor;

	private String dt_Inicial_Sucateamento;

	private String dt_Final_Sucateamento;

	private long oid_Unidade_Sucateamento;

	//No estoque
	private int diasEmEstoque;

	//Na recapagem
	private String dt_Remessa_Recapagem;

	private String dt_Promessa_Retorno_Recapagem;

	private String nm_Contato_Recapagem;

	private String oid_Fornecedor_Recapagem;

	private double vl_Negociado_Recapagem;

	private String nm_Fornecedor_Recapagem;

	private String nr_Os_Recapagem;

	//Última recapagem
	private String dt_Ultima_Recapagem;

	private String oid_Fornecedor_Ultima_Recapagem;

	private double vl_Ultima_Recapagem;

	private long nr_Nota_Fiscal_Ultima_Recapagem;

	private boolean dm_Garantia_Ultima_Recapagem;

	private long oid_Fabricante_Ultima_Recapagem;

	private long oid_Modelo_Pneu_Ultima_Recapagem;

	private String nr_Os_Ultima_Recapagem;

	private String nm_Garantia_Ultima_Recapagem;

	private String nm_Fornecedor_Ultima_Recapagem;

	private String nm_Fabricante_Ultima_Recapagem;

	private String nm_Modelo_Pneu_Ultima_Recapagem;

	private double nr_MM_Sulco_Ultima_Recapagem;

	//Venda
	private String dt_Venda;

	private double vl_Venda;

	private String tx_Comentario_Venda;

	//Recusa recapagem
	private String dt_Recusa_Recapagem;

	private String tx_Motivo_Recusa_Recapagem;

	private String nm_Responsavel_Recusa_Recapagem;

	private long nr_Nota_Fiscal_Recusa_Recapagem;

	private String oid_Fornecedor_Recusa_Recapagem;

	// Troca
	private String tx_Motivo_Troca;

	private int oid_Pneu_Retirado;

	// Campos para selecao
	private String dm_CP_Selecao;

	// Campos para calculo previsao de retirada
	private String array;

	private double twi;

	private double mm_Gastos;

	private double mm_Restantes;

	private double km_Restantes;

	private double km_Mm;

	private double nr_Diferenca_Perimetro;

	private double nr_Diferenca_MM;

	private String op_Vida;

	private String op_Perimetro;

	private String op_MM;

	private String nm_Vida;

	// Camos para consulta de análise de sucata
	private double nr_Km_Vida_N0;

	private double nr_Km_Vida_R1;

	private double nr_Km_Vida_R2;

	private double nr_Km_Vida_R3;

	private double nr_Km_Vida_R4;

	private double nr_Km_Vida_R5;

	private double nr_Km_Vida_R6;

	private String nm_Desenho_N0;

	private String nm_Desenho_R1;

	private String nm_Desenho_R2;

	private String nm_Desenho_R3;

	private String nm_Desenho_R4;

	private String nm_Desenho_R5;

	private String nm_Desenho_R6;

	// Campo para ordenação
	private String ordernaPor;

	public PneuED() {
	}

	public PneuED(int pneu) {
		oid_Pneu = pneu;
	}

	/**
	 * Construtor com todos os campos
	 */
	public PneuED(int oid_Pneu, String CD_Pneu, String NR_Fabrica,
			int oid_Fabricante_Pneu, String CD_Fabricante_Pneu,
			String NM_Fabricante_Pneu, int oid_Modelo_Pneu,
			String CD_Modelo_Pneu, String NM_Modelo_Pneu,
			String DM_Localizacao, String DM_Situacao, String DM_Posicao,
			int KM_Atual, double MM_Atual, String oid_Veiculo, String NR_Placa,
			String NR_Frota) {
		super();
		this.oid_Pneu = oid_Pneu;
		this.CD_Pneu = CD_Pneu;
		this.NR_Fabrica = NR_Fabrica;
		this.oid_Fabricante_Pneu = oid_Fabricante_Pneu;
		this.CD_Fabricante_Pneu = CD_Fabricante_Pneu;
		this.NM_Fabricante_Pneu = NM_Fabricante_Pneu;
		this.oid_Modelo_Pneu = oid_Modelo_Pneu;
		this.CD_Modelo_Pneu = CD_Modelo_Pneu;
		this.NM_Modelo_Pneu = NM_Modelo_Pneu;
		this.DM_Localizacao = DM_Localizacao;
		this.DM_Situacao = DM_Situacao;
		this.DM_Posicao = DM_Posicao;
		this.KM_Atual = KM_Atual;
		this.MM_Atual = MM_Atual;
		this.oid_Veiculo = oid_Veiculo;
		this.NR_Placa = NR_Placa;
		this.NR_Frota = NR_Frota;
	}

	/**
	 * Construtor com todos os campos incluindo o OID_ESTOQUE
	 */
	public PneuED(int oid_Pneu, String CD_Pneu, String NR_Fabrica,
			int oid_Fabricante_Pneu, String CD_Fabricante_Pneu,
			String NM_Fabricante_Pneu, int oid_Modelo_Pneu,
			String CD_Modelo_Pneu, String NM_Modelo_Pneu,
			String DM_Localizacao, String DM_Situacao, String DM_Posicao,
			int KM_Atual, double MM_Atual, String oid_Veiculo, String NR_Placa,
			String NR_Frota, long oid_Estoque) {
		super();
		this.oid_Pneu = oid_Pneu;
		this.CD_Pneu = CD_Pneu;
		this.NR_Fabrica = NR_Fabrica;
		this.oid_Fabricante_Pneu = oid_Fabricante_Pneu;
		this.CD_Fabricante_Pneu = CD_Fabricante_Pneu;
		this.NM_Fabricante_Pneu = NM_Fabricante_Pneu;
		this.oid_Modelo_Pneu = oid_Modelo_Pneu;
		this.CD_Modelo_Pneu = CD_Modelo_Pneu;
		this.NM_Modelo_Pneu = NM_Modelo_Pneu;
		this.DM_Localizacao = DM_Localizacao;
		this.DM_Situacao = DM_Situacao;
		this.DM_Posicao = DM_Posicao;
		this.KM_Atual = KM_Atual;
		this.MM_Atual = MM_Atual;
		this.oid_Veiculo = oid_Veiculo;
		this.NR_Placa = NR_Placa;
		this.NR_Frota = NR_Frota;
		this.oid_estoque = oid_Estoque;
	}

	/**
	 * Construtor com os campos da tabela Pneus
	 *
	 */
	public PneuED(int oid_Pneu, String CD_Pneu, String NR_Fabrica,
			int oid_Fabricante_Pneu, int oid_Modelo_Pneu,
			String DM_Localizacao, String DM_Situacao, int KM_Atual,
			double MM_Atual, String oid_Veiculo) {
		super();
		this.oid_Pneu = oid_Pneu;
		this.CD_Pneu = CD_Pneu;
		this.NR_Fabrica = NR_Fabrica;
		this.oid_Fabricante_Pneu = oid_Fabricante_Pneu;
		this.oid_Modelo_Pneu = oid_Modelo_Pneu;
		this.DM_Localizacao = DM_Localizacao;
		this.DM_Situacao = DM_Situacao;
		this.KM_Atual = KM_Atual;
		this.MM_Atual = MM_Atual;
		this.oid_Veiculo = oid_Veiculo;
	}

	public String getDescVida(){
		String vida = "NOVO";
		switch (new Long(nr_Vida).intValue()) {
		case 1:
			vida = "R1";
			break;
		case 2:
			vida = "R2";
			break;
		case 3:
			vida = "R3";
			break;
		case 4:
			vida = "R4";
			break;
		case 5:
			vida = "R5";
			break;
		case 6:
			vida = "R6";
			break;
		default:
			break;
		}
		return vida;
	}

	public String getCD_Pneu() {
		return CD_Pneu;
	}

	public void setCD_Pneu(String pneu) {
		CD_Pneu = pneu;
	}

	public String getDM_Localizacao() {
		return DM_Localizacao;
	}

	// *** Posição do pneu incluso no veiculo!
	public String getDesc_Posicao() {
		if ("1".equals(DM_Posicao)) {
			return "Direcao Lado Esquerdo";
		} else if ("2".equals(DM_Posicao)) {
			return "Direcao Lado Direito";
		} else if ("3".equals(DM_Posicao)) {
			return "Tracao Lado Esquerdo Externo";
		} else if ("4".equals(DM_Posicao)) {
			return "Tracao Lado Esquerdo Interno";
		} else if ("5".equals(DM_Posicao)) {
			return "Tracao Lado Direito Externo";
		} else if ("6".equals(DM_Posicao)) {
			return "Tracao Lado Direito Interno";
		} else if ("7".equals(DM_Posicao)) {
			return "Truck Lado Esquerdo Externo";
		} else if ("8".equals(DM_Posicao)) {
			return "Truck Lado Esquerdo Interno";
		} else if ("9".equals(DM_Posicao)) {
			return "Truck Lado Direito Externo";
		} else if ("10".equals(DM_Posicao)) {
			return "Truck Lado Direito Interno";
		} else if ("11".equals(DM_Posicao)) {
			return "Estepe 1";
		} else if ("12".equals(DM_Posicao)) {
			return "Estepe 2";
		} else if ("20".equals(DM_Posicao)) {
			return "Carreta Eixo 1 Esquerdo Externo";
		} else if ("21".equals(DM_Posicao)) {
			return "Carreta Eixo 1 Esquerdo Interno";
		} else if ("22".equals(DM_Posicao)) {
			return "Carreta Eixo 1 Direito Externo";
		} else if ("23".equals(DM_Posicao)) {
			return "Carreta Eixo 1 Direito Interno";
		} else if ("30".equals(DM_Posicao)) {
			return "Carreta Eixo 2 Esquerdo Externo";
		} else if ("31".equals(DM_Posicao)) {
			return "Carreta Eixo 2 Esquerdo Interno";
		} else if ("32".equals(DM_Posicao)) {
			return "Carreta Eixo 2 Direito Externo";
		} else if ("33".equals(DM_Posicao)) {
			return "Carreta Eixo 2 Direito Interno";
		} else if ("40".equals(DM_Posicao)) {
			return "Carreta Eixo 3 Esquerdo Externo";
		} else if ("41".equals(DM_Posicao)) {
			return "Carreta Eixo 3 Esquerdo Interno";
		} else if ("42".equals(DM_Posicao)) {
			return "Carreta Eixo 3 Direito Externo";
		} else if ("43".equals(DM_Posicao)) {
			return "Carreta Eixo 3 Direito Interno";
		} else if ("51".equals(DM_Posicao)) {
			return "Carreta Estepe 1";
		} else if ("52".equals(DM_Posicao)) {
			return "Carreta Estepe 2";
		}

		else if ("60".equals(DM_Posicao)) {
			return "Vagao1 Eixo 1 Esquerdo Externo";
		} else if ("61".equals(DM_Posicao)) {
			return "Vagao1 Eixo 1 Esquerdo Interno";
		} else if ("62".equals(DM_Posicao)) {
			return "Vagao1 Eixo 1 Direito Externo";
		} else if ("63".equals(DM_Posicao)) {
			return "Vagao1 Eixo 1 Direito Interno";
		} else if ("64".equals(DM_Posicao)) {
			return "Vagao1 Eixo 2 Esquerdo Externo";
		} else if ("65".equals(DM_Posicao)) {
			return "Vagao1 Eixo 2 Esquerdo Interno";
		} else if ("66".equals(DM_Posicao)) {
			return "Vagao1 Eixo 2 Direito Externo";
		} else if ("67".equals(DM_Posicao)) {
			return "Vagao1 Eixo 2 Direito Interno";
		} else if ("68".equals(DM_Posicao)) {
			return "Vagao1 Estepe 1";
		} else if ("69".equals(DM_Posicao)) {
			return "Vagao1 Estepe 2";
		}

		else if ("80".equals(DM_Posicao)) {
			return "Vagao2 Eixo 1 Esquerdo Externo";
		} else if ("81".equals(DM_Posicao)) {
			return "Vagao2 Eixo 1 Esquerdo Interno";
		} else if ("82".equals(DM_Posicao)) {
			return "Vagao2 Eixo 1 Direito Externo";
		} else if ("83".equals(DM_Posicao)) {
			return "Vagao2 Eixo 1 Direito Interno";
		} else if ("84".equals(DM_Posicao)) {
			return "Vagao2 Eixo 2 Esquerdo Externo";
		} else if ("85".equals(DM_Posicao)) {
			return "Vagao2 Eixo 2 Esquerdo Interno";
		} else if ("86".equals(DM_Posicao)) {
			return "Vagao2 Eixo 2 Direito Externo";
		} else if ("87".equals(DM_Posicao)) {
			return "Vagao2 Eixo 2 Direito Interno";
		} else if ("88".equals(DM_Posicao)) {
			return "Vagao2 Estepe 1";
		} else if ("89".equals(DM_Posicao)) {
			return "Vagao2 Estepe 2";
		}

		else {
			return "-----";
		}
	}

	public String getDesc_Localizacao() {
		if (this.getDM_Localizacao().equals("01")) {
			return "Estoque";
		} else if (this.getDM_Localizacao().equals("02")) {
			return "Manutenção";
		} else if (this.getDM_Localizacao().equals("03")) {
			return "Veículo";
		} else {
			return "Localização inválida";
		}
	}

	public String getDesc_Situacao() {
		if (this.getDM_Situacao().equals("1")) {
			return "Novo";
		} else if (this.getDM_Situacao().equals("2")) {
			return "Primeira recapagem";
		} else if (this.getDM_Situacao().equals("3")) {
			return "Segunda recapagem";
		} else if (this.getDM_Situacao().equals("4")) {
			return "Terceira recapagem";
		} else if (this.getDM_Situacao().equals("8")) {
			return "Sucata";
		} else if (this.getDM_Situacao().equals("9")) {
			return "Descarte";
		} else {
			return "Situação inválida";
		}
	}

	public void setDM_Localizacao(String legalizacao) {
		DM_Localizacao = legalizacao;
	}

	public String getDM_Situacao() {
		return DM_Situacao;
	}

	public void setDM_Situacao(String situacao) {
		DM_Situacao = situacao;
	}

	public int getKM_Atual() {
		return KM_Atual;
	}

	public void setKM_Atual(int atual) {
		KM_Atual = atual;
	}

	public String getNR_Fabrica() {
		return NR_Fabrica;
	}

	public void setNR_Fabrica(String fabrica) {
		NR_Fabrica = fabrica;
	}

	public int getOid_Fabricante_Pneu() {
		return oid_Fabricante_Pneu;
	}

	public void setOid_Fabricante_Pneu(int oid_Fabricante_Pneu) {
		this.oid_Fabricante_Pneu = oid_Fabricante_Pneu;
	}

	public int getOid_Modelo_Pneu() {
		return oid_Modelo_Pneu;
	}

	public void setOid_Modelo_Pneu(int oid_Modelo_Pneu) {
		this.oid_Modelo_Pneu = oid_Modelo_Pneu;
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

	public String getCD_Fabricante_Pneu() {
		return CD_Fabricante_Pneu;
	}

	public void setCD_Fabricante_Pneu(String fabricante_Pneu) {
		CD_Fabricante_Pneu = fabricante_Pneu;
	}

	public String getCD_Modelo_Pneu() {
		return CD_Modelo_Pneu;
	}

	public void setCD_Modelo_Pneu(String modelo_Pneu) {
		CD_Modelo_Pneu = modelo_Pneu;
	}

	public String getNM_Fabricante_Pneu() {
		return NM_Fabricante_Pneu;
	}

	public void setNM_Fabricante_Pneu(String fabricante_Pneu) {
		NM_Fabricante_Pneu = fabricante_Pneu;
	}

	public String getNM_Modelo_Pneu() {
		return NM_Modelo_Pneu;
	}

	public void setNM_Modelo_Pneu(String modelo_Pneu) {
		NM_Modelo_Pneu = modelo_Pneu;
	}

	public String getNR_Frota() {
		return NR_Frota;
	}

	public void setNR_Frota(String frota) {
		NR_Frota = frota;
	}

	public String getNR_Placa() {
		return NR_Placa;
	}

	public void setNR_Placa(String placa) {
		NR_Placa = placa;
	}

	public String getDM_Posicao() {
		return DM_Posicao;
	}

	public void setDM_Posicao(String posicao) {
		DM_Posicao = posicao;
	}

	public String getNotINTO_Loc() {
		return notINTO_Loc;
	}

	public void setNotINTO_Loc(String notINTO_Loc) {
		this.notINTO_Loc = notINTO_Loc;
	}

	public String getNotINTO_Sit() {
		return notINTO_Sit;
	}

	public void setNotINTO_Sit(String notINTO_Sit) {
		this.notINTO_Sit = notINTO_Sit;
	}

	public long getOid_estoque() {
		return oid_estoque;
	}

	public double getMM_Atual() {
		return MM_Atual;
	}

	public void setOid_estoque(long oid_estoque) {
		this.oid_estoque = oid_estoque;
	}

	public void setMM_Atual(double MM_Atual) {
		this.MM_Atual = MM_Atual;
	}

	public long getOid_Dimensao_Pneu() {
		return oid_Dimensao_Pneu;
	}

	public void setOid_Dimensao_Pneu(long oid_Dimensao_Pneu) {
		this.oid_Dimensao_Pneu = oid_Dimensao_Pneu;
	}

	public long getOid_Empresa() {
		return oid_Empresa;
	}

	public void setOid_Empresa(long oid_Empresa) {
		this.oid_Empresa = oid_Empresa;
	}

	public long getOid_Tipo_Pneu() {
		return oid_Tipo_Pneu;
	}

	public void setOid_Tipo_Pneu(long oid_Tipo_Pneu) {
		this.oid_Tipo_Pneu = oid_Tipo_Pneu;
	}

	public long getOid_Local_Estoque() {
		return oid_Local_Estoque;
	}

	public void setOid_Local_Estoque(long oid_Local_Estoque) {
		this.oid_Local_Estoque = oid_Local_Estoque;
	}

	public String getCd_Item_Estoque() {
		return cd_Item_Estoque;
	}

	public void setCd_Item_Estoque(String cd_Item_Estoque) {
		this.cd_Item_Estoque = cd_Item_Estoque;
	}

	public String getNr_Fogo() {
		return nr_Fogo;
	}

	public void setNr_Fogo(String nr_Fogo) {
		this.nr_Fogo = nr_Fogo;
	}

	public String getNm_Dimensao_Pneu() {
		return nm_Dimensao_Pneu;
	}

	public void setNm_Dimensao_Pneu(String nm_Dimensao_Pneu) {
		this.nm_Dimensao_Pneu = nm_Dimensao_Pneu;
	}

	public String getNm_Fornecedor() {
		return nm_Fornecedor;
	}

	public void setNm_Fornecedor(String nm_Fornecedor) {
		this.nm_Fornecedor = nm_Fornecedor;
	}

	public String getNm_Modelo_Pneu() {
		return nm_Modelo_Pneu;
	}

	public void setNm_Modelo_Pneu(String nm_Modelo_Pneu) {
		this.nm_Modelo_Pneu = nm_Modelo_Pneu;
	}

	public String getNm_Tipo_Pneu() {
		return nm_Tipo_Pneu;
	}

	public void setNm_Tipo_Pneu(String nm_Tipo_Pneu) {
		this.nm_Tipo_Pneu = nm_Tipo_Pneu;
	}

	public String getOid_Fornecedor() {
		return oid_Fornecedor;
	}

	public void setOid_Fornecedor(String oid_Fornecedor) {
		this.oid_Fornecedor = oid_Fornecedor;
	}

	public String getNm_Fabricante_Pneu() {
		return nm_Fabricante_Pneu;
	}

	public void setNm_Fabricante_Pneu(String nm_Fabricante_Pneu) {
		this.nm_Fabricante_Pneu = nm_Fabricante_Pneu;
	}

	public double getNr_Km_Acumulada() {
		return nr_Km_Acumulada;
	}

	public void setNr_Km_Acumulada(double nr_Km_Acumulada) {
		this.nr_Km_Acumulada = nr_Km_Acumulada;
	}

	public long getNr_Nota_Fiscal() {
		return nr_Nota_Fiscal;
	}

	public void setNr_Nota_Fiscal(long nr_Nota_Fiscal) {
		this.nr_Nota_Fiscal = nr_Nota_Fiscal;
	}

	public long getNr_Perimetro() {
		return nr_Perimetro;
	}

	public void setNr_Perimetro(long nr_Perimetro) {
		this.nr_Perimetro = nr_Perimetro;
	}

	public double getVl_Preco() {
		return vl_Preco;
	}

	public void setVl_Preco(double vl_Preco) {
		this.vl_Preco = vl_Preco;
	}

	public String getDm_Controle_Parcial() {
		return dm_Controle_Parcial;
	}

	public void setDm_Controle_Parcial(String dm_Controle_Parcial) {
		this.dm_Controle_Parcial = dm_Controle_Parcial;
	}

	public String getDm_CP_Selecao() {
		return dm_CP_Selecao;
	}

	public void setDm_CP_Selecao(String dm_CP_Selecao) {
		this.dm_CP_Selecao = dm_CP_Selecao;
	}

	public String getDt_Entrada() {
		return dt_Entrada;
	}

	public void setDt_Entrada(String dt_Entrada) {
		this.dt_Entrada = dt_Entrada;
	}

	public String getDt_Estoque() {
		return dt_Estoque;
	}

	public void setDt_Estoque(String dt_Estoque) {
		this.dt_Estoque = dt_Estoque;
	}

	public String getDt_Venda() {
		return dt_Venda;
	}

	public void setDt_Venda(String dt_Venda) {
		this.dt_Venda = dt_Venda;
	}

	public String getNm_Motivo_Sucateamento() {
		return nm_Motivo_Sucateamento;
	}

	public void setNm_Motivo_Sucateamento(String nm_Motivo_Sucateamento) {
		this.nm_Motivo_Sucateamento = nm_Motivo_Sucateamento;
	}

	public String getTx_Comentario_Venda() {
		return tx_Comentario_Venda;
	}

	public void setTx_Comentario_Venda(String tx_Comentario_Venda) {
		this.tx_Comentario_Venda = tx_Comentario_Venda;
	}

	public double getVl_Venda() {
		return vl_Venda;
	}

	public void setVl_Venda(double vl_Venda) {
		this.vl_Venda = vl_Venda;
	}

	public String getTx_Lonas() {
		return tx_Lonas;
	}

	public void setTx_Lonas(String tx_Lonas) {
		this.tx_Lonas = tx_Lonas;
	}

	public String getTx_Dot() {
		return tx_Dot;
	}

	public void setTx_Dot(String tx_Dot) {
		this.tx_Dot = tx_Dot;
	}

	public String getDm_Eixo() {
		return dm_Eixo;
	}

	public void setDm_Eixo(String dm_Eixo) {
		this.dm_Eixo = dm_Eixo;
	}

	public boolean getDm_Garantia_Ultima_Recapagem() {
		return dm_Garantia_Ultima_Recapagem;
	}

	public void setDm_Garantia_Ultima_Recapagem(
			boolean dm_Garantia_Ultima_Recapagem) {
		this.dm_Garantia_Ultima_Recapagem = dm_Garantia_Ultima_Recapagem;
	}

	public String getDt_Nota_Fiscal() {
		return dt_Nota_Fiscal;
	}

	public void setDt_Nota_Fiscal(String dt_Nota_Fiscal) {
		this.dt_Nota_Fiscal = dt_Nota_Fiscal;
	}

	public String getDt_Promessa_Retorno_Recapagem() {
		return dt_Promessa_Retorno_Recapagem;
	}

	public void setDt_Promessa_Retorno_Recapagem(
			String dt_Promessa_Retorno_Recapagem) {
		this.dt_Promessa_Retorno_Recapagem = dt_Promessa_Retorno_Recapagem;
	}

	public String getDt_Recusa_Recapagem() {
		return dt_Recusa_Recapagem;
	}

	public void setDt_Recusa_Recapagem(String dt_Recusa_Recapagem) {
		this.dt_Recusa_Recapagem = dt_Recusa_Recapagem;
	}

	public String getDt_Remessa_Recapagem() {
		return dt_Remessa_Recapagem;
	}

	public void setDt_Remessa_Recapagem(String dt_Remessa_Recapagem) {
		this.dt_Remessa_Recapagem = dt_Remessa_Recapagem;
	}

	public String getDt_Sucateamento() {
		return dt_Sucateamento;
	}

	public void setDt_Sucateamento(String dt_Sucateamento) {
		this.dt_Sucateamento = dt_Sucateamento;
	}

	public String getDt_Ultima_Recapagem() {
		return dt_Ultima_Recapagem;
	}

	public void setDt_Ultima_Recapagem(String dt_Ultima_Recapagem) {
		this.dt_Ultima_Recapagem = dt_Ultima_Recapagem;
	}

	public String getNm_Contato_Recapagem() {
		return nm_Contato_Recapagem;
	}

	public void setNm_Contato_Recapagem(String nm_Contato_Recapagem) {
		this.nm_Contato_Recapagem = nm_Contato_Recapagem;
	}

	public String getNm_Local_Estoque() {
		return nm_Local_Estoque;
	}

	public void setNm_Local_Estoque(String nm_Local_Estoque) {
		this.nm_Local_Estoque = nm_Local_Estoque;
	}

	public String getNm_Responsavel_Recusa_Recapagem() {
		return nm_Responsavel_Recusa_Recapagem;
	}

	public void setNm_Responsavel_Recusa_Recapagem(
			String nm_Responsavel_Recusa_Recapagem) {
		this.nm_Responsavel_Recusa_Recapagem = nm_Responsavel_Recusa_Recapagem;
	}

	public double getNr_Hodometro_Veiculo() {
		return nr_Hodometro_Veiculo;
	}

	public void setNr_Hodometro_Veiculo(double nr_Hodometro_Veiculo) {
		this.nr_Hodometro_Veiculo = nr_Hodometro_Veiculo;
	}

	public double getNr_Km_Acumulada_Veiculo() {
		return nr_Km_Acumulada_Veiculo;
	}

	public void setNr_Km_Acumulada_Veiculo(double nr_Km_Acumulada_Veiculo) {
		this.nr_Km_Acumulada_Veiculo = nr_Km_Acumulada_Veiculo;
	}

	public double getNr_Mm_Saida() {
		return nr_Mm_Saida;
	}

	public void setNr_Mm_Saida(double nr_Mm_Saida) {
		this.nr_Mm_Saida = nr_Mm_Saida;
	}

	public long getNr_Nota_Fiscal_Recusa_Recapagem() {
		return nr_Nota_Fiscal_Recusa_Recapagem;
	}

	public void setNr_Nota_Fiscal_Recusa_Recapagem(
			long nr_Nota_Fiscal_Recusa_Recapagem) {
		this.nr_Nota_Fiscal_Recusa_Recapagem = nr_Nota_Fiscal_Recusa_Recapagem;
	}

	public long getNr_Nota_Fiscal_Ultima_Recapagem() {
		return nr_Nota_Fiscal_Ultima_Recapagem;
	}

	public void setNr_Nota_Fiscal_Ultima_Recapagem(
			long nr_Nota_Fiscal_Ultima_Recapagem) {
		this.nr_Nota_Fiscal_Ultima_Recapagem = nr_Nota_Fiscal_Ultima_Recapagem;
	}

	public String getNr_Serie() {
		return nr_Serie;
	}

	public void setNr_Serie(String nr_Serie) {
		this.nr_Serie = nr_Serie;
	}

	public long getOid_Motivo_Sucateamento() {
		return oid_Motivo_Sucateamento;
	}

	public void setOid_Motivo_Sucateamento(long oid_Motivo_Sucateamento) {
		this.oid_Motivo_Sucateamento = oid_Motivo_Sucateamento;
	}

	public String getTx_Motivo_Recusa_Recapagem() {
		return tx_Motivo_Recusa_Recapagem;
	}

	public void setTx_Motivo_Recusa_Recapagem(String tx_Motivo_Recusa_Recapagem) {
		this.tx_Motivo_Recusa_Recapagem = tx_Motivo_Recusa_Recapagem;
	}

	public double getVl_Negociado_Recapagem() {
		return vl_Negociado_Recapagem;
	}

	public void setVl_Negociado_Recapagem(double vl_Negociado_Recapagem) {
		this.vl_Negociado_Recapagem = vl_Negociado_Recapagem;
	}

	public double getVl_Ultima_Recapagem() {
		return vl_Ultima_Recapagem;
	}

	public void setVl_Ultima_Recapagem(double vl_Ultima_Recapagem) {
		this.vl_Ultima_Recapagem = vl_Ultima_Recapagem;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getOid_Fornecedor_Recapagem() {
		return oid_Fornecedor_Recapagem;
	}

	public void setOid_Fornecedor_Recapagem(String oid_Fornecedor_Recapagem) {
		this.oid_Fornecedor_Recapagem = oid_Fornecedor_Recapagem;
	}

	public String getOid_Fornecedor_Recusa_Recapagem() {
		return oid_Fornecedor_Recusa_Recapagem;
	}

	public void setOid_Fornecedor_Recusa_Recapagem(
			String oid_Fornecedor_Recusa_Recapagem) {
		this.oid_Fornecedor_Recusa_Recapagem = oid_Fornecedor_Recusa_Recapagem;
	}

	public String getNm_Fornecedor_Recapagem() {
		return nm_Fornecedor_Recapagem;
	}

	public void setNm_Fornecedor_Recapagem(String nm_Fornecedor_Recapagem) {
		this.nm_Fornecedor_Recapagem = nm_Fornecedor_Recapagem;
	}

	public long getNr_Vida() {
		return nr_Vida;
	}

	public void setNr_Vida(long nr_Vida) {
		this.nr_Vida = nr_Vida;
	}

	public String getNr_Os_Ultima_Recapagem() {
		return nr_Os_Ultima_Recapagem;
	}

	public void setNr_Os_Ultima_Recapagem(String nr_Os_Ultima_Recapagem) {
		this.nr_Os_Ultima_Recapagem = nr_Os_Ultima_Recapagem;
	}

	public long getOid_Fabricante_Ultima_Recapagem() {
		return oid_Fabricante_Ultima_Recapagem;
	}

	public void setOid_Fabricante_Ultima_Recapagem(
			long oid_Fabricante_Ultima_Recapagem) {
		this.oid_Fabricante_Ultima_Recapagem = oid_Fabricante_Ultima_Recapagem;
	}

	public String getOid_Fornecedor_Ultima_Recapagem() {
		return oid_Fornecedor_Ultima_Recapagem;
	}

	public void setOid_Fornecedor_Ultima_Recapagem(
			String oid_Fornecedor_Ultima_Recapagem) {
		this.oid_Fornecedor_Ultima_Recapagem = oid_Fornecedor_Ultima_Recapagem;
	}

	public long getOid_Modelo_Pneu_Ultima_Recapagem() {
		return oid_Modelo_Pneu_Ultima_Recapagem;
	}

	public void setOid_Modelo_Pneu_Ultima_Recapagem(
			long oid_Modelo_Pneu_Ultima_Recapagem) {
		this.oid_Modelo_Pneu_Ultima_Recapagem = oid_Modelo_Pneu_Ultima_Recapagem;
	}

	public String getNm_Garantia_Ultima_Recapagem() {
		String nm = null;
		if (this.getDm_Garantia_Ultima_Recapagem() == false)
			nm = "Não";
		if (this.getDm_Garantia_Ultima_Recapagem() == true)
			nm = "Sim";
		return nm;
	}

	public void setNm_Garantia_Ultima_Recapagem(
			String nm_Garantia_Ultima_Recapagem) {
		this.nm_Garantia_Ultima_Recapagem = nm_Garantia_Ultima_Recapagem;
	}

	public String getNm_Fabricante_Ultima_Recapagem() {
		return nm_Fabricante_Ultima_Recapagem;
	}

	public void setNm_Fabricante_Ultima_Recapagem(
			String nm_Fabricante_Ultima_Recapagem) {
		this.nm_Fabricante_Ultima_Recapagem = nm_Fabricante_Ultima_Recapagem;
	}

	public String getNm_Modelo_Pneu_Ultima_Recapagem() {
		return nm_Modelo_Pneu_Ultima_Recapagem;
	}

	public void setNm_Modelo_Pneu_Ultima_Recapagem(
			String nm_Modelo_Pneu_Ultima_Recapagem) {
		this.nm_Modelo_Pneu_Ultima_Recapagem = nm_Modelo_Pneu_Ultima_Recapagem;
	}

	public String getNm_Fornecedor_Ultima_Recapagem() {
		return nm_Fornecedor_Ultima_Recapagem;
	}

	public void setNm_Fornecedor_Ultima_Recapagem(
			String nm_Fornecedor_Ultima_Recapagem) {
		this.nm_Fornecedor_Ultima_Recapagem = nm_Fornecedor_Ultima_Recapagem;
	}

	public double getNr_MM_Sulco_Ultima_Recapagem() {
		return nr_MM_Sulco_Ultima_Recapagem;
	}

	public void setNr_MM_Sulco_Ultima_Recapagem(
			double nr_MM_Sulco_Ultima_Recapagem) {
		this.nr_MM_Sulco_Ultima_Recapagem = nr_MM_Sulco_Ultima_Recapagem;
	}

	public String getDm_Virada() {
		return dm_Virada;
	}

	public void setDm_Virada(String dm_Virada) {
		this.dm_Virada = dm_Virada;
	}

	public String getTx_Situacao() {
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

	public void setTx_Situacao(String tx_Situacao) {
		this.tx_Situacao = tx_Situacao;
	}

	public int getDiasEmEstoque() {
		return diasEmEstoque;
	}

	public void setDiasEmEstoque(int diasEmEstoque) {
		this.diasEmEstoque = diasEmEstoque;
	}

	public double getNr_Mm_Sulco_Maior() {
		return nr_Mm_Sulco_Maior;
	}

	public void setNr_Mm_Sulco_Maior(double nr_Mm_Sulco_Maior) {
		this.nr_Mm_Sulco_Maior = nr_Mm_Sulco_Maior;
	}

	public double getNr_Mm_Sulco_Menor() {
		return nr_Mm_Sulco_Menor;
	}

	public void setNr_Mm_Sulco_Menor(double nr_Mm_Sulco_Menor) {
		this.nr_Mm_Sulco_Menor = nr_Mm_Sulco_Menor;
	}

	public String getDt_Final_Sucateamento() {
		return dt_Final_Sucateamento;
	}

	public void setDt_Final_Sucateamento(String dt_Final_Sucateamento) {
		this.dt_Final_Sucateamento = dt_Final_Sucateamento;
	}

	public String getDt_Inicial_Sucateamento() {
		return dt_Inicial_Sucateamento;
	}

	public void setDt_Inicial_Sucateamento(String dt_Inicial_Sucateamento) {
		this.dt_Inicial_Sucateamento = dt_Inicial_Sucateamento;
	}

	public int getOid_Pneu_Retirado() {
		return oid_Pneu_Retirado;
	}

	public void setOid_Pneu_Retirado(int oid_Pneu_Retirado) {
		this.oid_Pneu_Retirado = oid_Pneu_Retirado;
	}

	public String getTx_Motivo_Troca() {
		return tx_Motivo_Troca;
	}

	public void setTx_Motivo_Troca(String tx_Motivo_Troca) {
		this.tx_Motivo_Troca = tx_Motivo_Troca;
	}

	public String getNr_Os_Recapagem() {
		return nr_Os_Recapagem;
	}

	public void setNr_Os_Recapagem(String nr_Os_Recapagem) {
		this.nr_Os_Recapagem = nr_Os_Recapagem;
	}

	public String getArray() {
		return array;
	}

	public void setArray(String array) {
		this.array = array;
	}

	public double getNr_Km_Inicial() {
		return nr_Km_Inicial;
	}

	public void setNr_Km_Inicial(double nr_Km_Inicial) {
		this.nr_Km_Inicial = nr_Km_Inicial;
	}

	public double getNr_Mm_Inicial() {
		return nr_Mm_Inicial;
	}

	public void setNr_Mm_Inicial(double nr_Mm_Inicial) {
		this.nr_Mm_Inicial = nr_Mm_Inicial;
	}

	public String getDm_Dimensao_Pneu() {
		return dm_Dimensao_Pneu;
	}

	public void setDm_Dimensao_Pneu(String dm_Dimensao_Pneu) {
		this.dm_Dimensao_Pneu = dm_Dimensao_Pneu;
	}

	public String getDm_Fabricante_Pneu() {
		return dm_Fabricante_Pneu;
	}

	public void setDm_Fabricante_Pneu(String dm_Fabricante_Pneu) {
		this.dm_Fabricante_Pneu = dm_Fabricante_Pneu;
	}

	public String getDm_MM_Atual() {
		return dm_MM_Atual;
	}

	public void setDm_MM_Atual(String dm_MM_Atual) {
		this.dm_MM_Atual = dm_MM_Atual;
	}

	public String getDm_Modelo_Pneu() {
		return dm_Modelo_Pneu;
	}

	public void setDm_Modelo_Pneu(String dm_Modelo_Pneu) {
		this.dm_Modelo_Pneu = dm_Modelo_Pneu;
	}

	public String getDm_Perimetro() {
		return dm_Perimetro;
	}

	public void setDm_Perimetro(String dm_Perimetro) {
		this.dm_Perimetro = dm_Perimetro;
	}

	public String getDm_Tipo_Pneu() {
		return dm_Tipo_Pneu;
	}

	public void setDm_Tipo_Pneu(String dm_Tipo_Pneu) {
		this.dm_Tipo_Pneu = dm_Tipo_Pneu;
	}

	public String getDm_Vida_Pneu() {
		return dm_Vida_Pneu;
	}

	public void setDm_Vida_Pneu(String dm_Vida_Pneu) {
		this.dm_Vida_Pneu = dm_Vida_Pneu;
	}

	public String getDesc_Eixo() {
		String nm = null;
		if ("A".equals(this.getDm_Eixo())) {
			nm = "A - Direção";
		} else if ("B".equals(this.getDm_Eixo())) {
			nm = "B - Tração";
		} else if ("C".equals(this.getDm_Eixo())) {
			nm = "C - Truck";
		} else if ("D".equals(this.getDm_Eixo())) {
			nm = "D - 1º Eixo";
		} else if ("E".equals(this.getDm_Eixo())) {
			nm = "E - 2º Eixo";
		} else if ("F".equals(this.getDm_Eixo())) {
			nm = "F - 3º Eixo";
		} else if ("G".equals(this.getDm_Eixo())) {
			nm = "G - 4º Eixo";
		} else if ("S".equals(this.getDm_Eixo())) {
			nm = "Estepe";
		} else {
			return nm = this.getDm_Eixo() + " - Outros";
		}
		return nm;

	}

	public void setDesc_Eixo(String desc_Eixo) {
		Desc_Eixo = desc_Eixo;
	}

	public String getDm_Considera_Sucateados() {
		return dm_Considera_Sucateados;
	}

	public void setDm_Considera_Sucateados(String dm_Considera_Sucateados) {
		this.dm_Considera_Sucateados = dm_Considera_Sucateados;
	}

	public String getDesc_Localizacao_Pneu() {
		return Desc_Localizacao_Pneu;
	}

	public void setDesc_Localizacao_Pneu(String desc_Localizacao_Pneu) {
		Desc_Localizacao_Pneu = desc_Localizacao_Pneu;
	}

	public String getDt_Final_Compra() {
		return dt_Final_Compra;
	}

	public void setDt_Final_Compra(String dt_Final_Compra) {
		this.dt_Final_Compra = dt_Final_Compra;
	}

	public String getDt_Inicial_Compra() {
		return dt_Inicial_Compra;
	}

	public void setDt_Inicial_Compra(String dt_Inicial_Compra) {
		this.dt_Inicial_Compra = dt_Inicial_Compra;
	}

	public String getNm_Razao_Social() {
		return nm_Razao_Social;
	}

	public void setNm_Razao_Social(String nm_Razao_Social) {
		this.nm_Razao_Social = nm_Razao_Social;
	}

	public double getMM() {
		return MM;
	}

	public void setMM(double mm) {
		MM = mm;
	}

	public double getKm_Mm() {
		return km_Mm;
	}

	public void setKm_Mm(double km_Mm) {
		this.km_Mm = km_Mm;
	}

	public double getKm_Restantes() {
		return km_Restantes;
	}

	public void setKm_Restantes(double km_Restantes) {
		this.km_Restantes = km_Restantes;
	}

	public double getMm_Gastos() {
		return mm_Gastos;
	}

	public void setMm_Gastos(double mm_Gastos) {
		this.mm_Gastos = mm_Gastos;
	}

	public double getMm_Restantes() {
		return mm_Restantes;
	}

	public void setMm_Restantes(double mm_Restantes) {
		this.mm_Restantes = mm_Restantes;
	}

	public double getTwi() {
		return twi;
	}

	public void setTwi(double twi) {
		this.twi = twi;
	}

	public String getOrdernaPor() {
		return ordernaPor;
	}

	public void setOrdernaPor(String ordernaPor) {
		this.ordernaPor = ordernaPor;
	}

	public double getNr_Diferenca_MM() {
		return nr_Diferenca_MM;
	}

	public void setNr_Diferenca_MM(double nr_Diferenca_MM) {
		this.nr_Diferenca_MM = nr_Diferenca_MM;
	}

	public double getNr_Diferenca_Perimetro() {
		return nr_Diferenca_Perimetro;
	}

	public void setNr_Diferenca_Perimetro(double nr_Diferenca_Perimetro) {
		this.nr_Diferenca_Perimetro = nr_Diferenca_Perimetro;
	}

	public String getOp_Vida() {
		return op_Vida;
	}

	public void setOp_Vida(String op_Vida) {
		this.op_Vida = op_Vida;
	}

	public String getOp_MM() {
		return op_MM;
	}

	public void setOp_MM(String op_MM) {
		this.op_MM = op_MM;
	}

	public String getOp_Perimetro() {
		return op_Perimetro;
	}

	public void setOp_Perimetro(String op_Perimetro) {
		this.op_Perimetro = op_Perimetro;
	}

	public String getNm_Desenho_N0() {
		return nm_Desenho_N0;
	}

	public void setNm_Desenho_N0(String nm_Desenho_N0) {
		this.nm_Desenho_N0 = nm_Desenho_N0;
	}

	public String getNm_Desenho_R1() {
		return nm_Desenho_R1;
	}

	public void setNm_Desenho_R1(String nm_Desenho_R1) {
		this.nm_Desenho_R1 = nm_Desenho_R1;
	}

	public String getNm_Desenho_R2() {
		return nm_Desenho_R2;
	}

	public void setNm_Desenho_R2(String nm_Desenho_R2) {
		this.nm_Desenho_R2 = nm_Desenho_R2;
	}

	public String getNm_Desenho_R3() {
		return nm_Desenho_R3;
	}

	public void setNm_Desenho_R3(String nm_Desenho_R3) {
		this.nm_Desenho_R3 = nm_Desenho_R3;
	}

	public String getNm_Desenho_R4() {
		return nm_Desenho_R4;
	}

	public void setNm_Desenho_R4(String nm_Desenho_R4) {
		this.nm_Desenho_R4 = nm_Desenho_R4;
	}

	public String getNm_Desenho_R5() {
		return nm_Desenho_R5;
	}

	public void setNm_Desenho_R5(String nm_Desenho_R5) {
		this.nm_Desenho_R5 = nm_Desenho_R5;
	}

	public String getNm_Desenho_R6() {
		return nm_Desenho_R6;
	}

	public void setNm_Desenho_R6(String nm_Desenho_R6) {
		this.nm_Desenho_R6 = nm_Desenho_R6;
	}

	public String getNm_Unidade() {
		return nm_Unidade;
	}

	public void setNm_Unidade(String nm_Unidade) {
		this.nm_Unidade = nm_Unidade;
	}

	public double getNr_Km_Vida_N0() {
		return nr_Km_Vida_N0;
	}

	public void setNr_Km_Vida_N0(double nr_Km_Vida_N0) {
		this.nr_Km_Vida_N0 = nr_Km_Vida_N0;
	}

	public double getNr_Km_Vida_R1() {
		return nr_Km_Vida_R1;
	}

	public void setNr_Km_Vida_R1(double nr_Km_Vida_R1) {
		this.nr_Km_Vida_R1 = nr_Km_Vida_R1;
	}

	public double getNr_Km_Vida_R2() {
		return nr_Km_Vida_R2;
	}

	public void setNr_Km_Vida_R2(double nr_Km_Vida_R2) {
		this.nr_Km_Vida_R2 = nr_Km_Vida_R2;
	}

	public double getNr_Km_Vida_R3() {
		return nr_Km_Vida_R3;
	}

	public void setNr_Km_Vida_R3(double nr_Km_Vida_R3) {
		this.nr_Km_Vida_R3 = nr_Km_Vida_R3;
	}

	public double getNr_Km_Vida_R4() {
		return nr_Km_Vida_R4;
	}

	public void setNr_Km_Vida_R4(double nr_Km_Vida_R4) {
		this.nr_Km_Vida_R4 = nr_Km_Vida_R4;
	}

	public double getNr_Km_Vida_R5() {
		return nr_Km_Vida_R5;
	}

	public void setNr_Km_Vida_R5(double nr_Km_Vida_R5) {
		this.nr_Km_Vida_R5 = nr_Km_Vida_R5;
	}

	public double getNr_Km_Vida_R6() {
		return nr_Km_Vida_R6;
	}

	public void setNr_Km_Vida_R6(double nr_Km_Vida_R6) {
		this.nr_Km_Vida_R6 = nr_Km_Vida_R6;
	}

	public long getOid_Unidade_Sucateamento() {
		return oid_Unidade_Sucateamento;
	}

	public void setOid_Unidade_Sucateamento(long oid_Unidade_Sucateamento) {
		this.oid_Unidade_Sucateamento = oid_Unidade_Sucateamento;
	}

	public int getKM_Final() {
		return KM_Final;
	}

	public void setKM_Final(int final1) {
		KM_Final = final1;
	}

	public String getNr_Cnpj_Cpf() {
		return nr_Cnpj_Cpf;
	}

	public void setNr_Cnpj_Cpf(String nr_Cnpj_Cpf) {
		this.nr_Cnpj_Cpf = nr_Cnpj_Cpf;
	}

	public String getNm_Vida() {
		return nm_Vida;
	}

	public void setNm_Vida(String nm_Vida) {
		this.nm_Vida = nm_Vida;
	}

	public String getCd_Unidade() {
		return cd_Unidade;
	}

	public void setCd_Unidade(String cd_Unidade) {
		this.cd_Unidade = cd_Unidade;
	}

	public long getOid_Unidade() {
		return oid_Unidade;
	}

	public void setOid_Unidade(long oid_Unidade) {
		this.oid_Unidade = oid_Unidade;
	}

	public String getNm_estoque() {
		return nm_estoque;
	}

	public void setNm_estoque(String nm_estoque) {
		this.nm_estoque = nm_estoque;
	}

}