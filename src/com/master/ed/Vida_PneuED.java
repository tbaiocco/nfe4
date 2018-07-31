package com.master.ed;



/**
 * @serial Vidas de Pneus
 * @serialData 06/2007
 * @author Ralph Renato
 */
public class Vida_PneuED extends RelatorioBaseED {

	private static final long serialVersionUID = 3419480339101195277L;

	private long oid_Vida_Pneu;
	private long oid_Pneu;
	private String cd_Pneu;
	private long nr_Vida;

	private String oid_Fornecedor;
	private String nr_Cnpj_Cpf;
	private String nm_Fornecedor;

	private long oid_Empresa;

	private long oid_Fabricante_Banda;
	private String cd_Fabricante_Banda;
	private String nm_Fabricante_Banda;

	private long oid_Banda;
	private String cd_Banda;
	private String nm_Banda;

	private String oid_Veiculo;
	private String dm_Eixo;

	private long nr_Nota_Fiscal_Recape;
	private double vl_Vida;
	private String dt_Inicial;
	private String dt_Final;

	private double nr_Km_Inicial;
	private double nr_Km_Final;

	private double nr_Mm_Inicial;
	private double nr_Mm_Final;
	private String nr_Os;

	private String nm_Vida;
	private String nm_Vida_Ext;

	// Consultas/Relatórios
	private String nr_Fogo;
	private String nr_Frota;
	private String nm_Dimensao_Pneu;
	private String nm_Fabricante_Pneu;
	private String nm_Razao_Social;
	private String cd_Modelo_Pneu;
	private String nm_Modelo_Pneu;
	private long oid_Dimensao_Pneu;
	private long oid_Fabricante_Pneu;
	private long oid_Modelo_Pneu;
	private String dm_Situacao;
	private String dm_Total;
	private String dm_Vida_Ate;
	private String dm_Vida_So;
	private String dm_Agrupa_Eixo;
	private String dm_Agrupa_Dimensao;
	private String dm_Agrupa_Marca;
	private String dm_Agrupa_Modelo;
	private String dm_Agrupa_Desenho;

	private String dm_Tipo;
	private String dm_Vl_Venda;
	private int nr_Vida_Ate;
	private int nr_Vida_So;

	// Campos recursivos
	private double nr_Km_Vida;
	private double nr_Km_Vida_N0;
	private double nr_Km_Vida_R1;
	private double nr_Km_Vida_R2;
	private double nr_Km_Vida_R3;
	private double nr_Km_Vida_R4;
	private double nr_Km_Vida_R5;
	private double nr_Km_Vida_R6;
	private double vl_Vida_N0;
	private double vl_Vida_R1;
	private double vl_Vida_R2;
	private double vl_Vida_R3;
	private double vl_Vida_R4;
	private double vl_Vida_R5;
	private double vl_Vida_R6;
	private double vl_Vida_Total;
	private int nr_Qtd_Total_Vidas;
	private int nr_Qtd_N0;
	private int nr_Qtd_R1;
	private int nr_Qtd_R2;
	private int nr_Qtd_R3;
	private int nr_Qtd_R4;
	private int nr_Qtd_R5;
	private int nr_Qtd_R6;
	private double vl_Venda;
	private String dm_Fabricante_Pneu;
	private String dm_Dimensao_Pneu;
	private String dm_Modelo_Pneu;
	private String nm_Graf;

	public Vida_PneuED() {
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

	public String getDm_Eixo() {
		return dm_Eixo;
	}
	public void setDm_Eixo(String dm_Eixo) {
		this.dm_Eixo = dm_Eixo;
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

	public double getNr_Km_Final() {
		return nr_Km_Final;
	}
	public void setNr_Km_Final(double nr_Km_Final) {
		this.nr_Km_Final = nr_Km_Final;
	}
	public double getNr_Km_Inicial() {
		return nr_Km_Inicial;
	}
	public void setNr_Km_Inicial(double nr_Km_Inicial) {
		this.nr_Km_Inicial = nr_Km_Inicial;
	}
	public double getNr_Mm_Final() {
		return nr_Mm_Final;
	}
	public void setNr_Mm_Final(double nr_Mm_Final) {
		this.nr_Mm_Final = nr_Mm_Final;
	}
	public double getNr_Mm_Inicial() {
		return nr_Mm_Inicial;
	}
	public void setNr_Mm_Inicial(double nr_Mm_Inicial) {
		this.nr_Mm_Inicial = nr_Mm_Inicial;
	}
	public long getNr_Nota_Fiscal_Recape() {
		return nr_Nota_Fiscal_Recape;
	}
	public void setNr_Nota_Fiscal_Recape(long nr_Nota_Fiscal_Recape) {
		this.nr_Nota_Fiscal_Recape = nr_Nota_Fiscal_Recape;
	}
	public String getNr_Os() {
		return nr_Os;
	}
	public void setNr_Os(String nr_Os) {
		this.nr_Os = nr_Os;
	}
	public long getNr_Vida() {
		return nr_Vida;
	}
	public void setNr_Vida(long nr_Vida) {
		this.nr_Vida = nr_Vida;
	}
	public long getOid_Empresa() {
		return oid_Empresa;
	}
	public void setOid_Empresa(long oid_Empresa) {
		this.oid_Empresa = oid_Empresa;
	}
	public long getOid_Fabricante_Banda() {
		return oid_Fabricante_Banda;
	}
	public void setOid_Fabricante_Banda(long oid_Fabricante_Banda) {
		this.oid_Fabricante_Banda = oid_Fabricante_Banda;
	}
	public String getOid_Fornecedor() {
		return oid_Fornecedor;
	}
	public void setOid_Fornecedor(String oid_Fornecedor) {
		this.oid_Fornecedor = oid_Fornecedor;
	}
	public long getOid_Banda() {
		return oid_Banda;
	}
	public void setOid_Banda(long oid_Banda) {
		this.oid_Banda = oid_Banda;
	}
	public long getOid_Pneu() {
		return oid_Pneu;
	}
	public void setOid_Pneu(long oid_Pneu) {
		this.oid_Pneu = oid_Pneu;
	}
	public String getOid_Veiculo() {
		return oid_Veiculo;
	}
	public void setOid_Veiculo(String oid_Veiculo) {
		this.oid_Veiculo = oid_Veiculo;
	}
	public long getOid_Vida_Pneu() {
		return oid_Vida_Pneu;
	}
	public void setOid_Vida_Pneu(long oid_Vida_Pneu) {
		this.oid_Vida_Pneu = oid_Vida_Pneu;
	}
	public double getVl_Vida() {
		return vl_Vida;
	}
	public void setVl_Vida(double vl_Vida) {
		this.vl_Vida = vl_Vida;
	}
	public String getNr_Fogo() {
		return nr_Fogo;
	}
	public void setNr_Fogo(String nr_Fogo) {
		this.nr_Fogo = nr_Fogo;
	}
	public String getNm_Vida() {
		return nm_Vida ;
	}
	public void setNm_Vida(String nm_Vida) {
		this.nm_Vida = nm_Vida;
	}
	public String getNm_Fabricante_Banda() {
		return nm_Fabricante_Banda;
	}
	public void setNm_Fabricante_Banda(String nm_Fabricante_Banda) {
		this.nm_Fabricante_Banda = nm_Fabricante_Banda;
	}
	public String getNm_Fornecedor() {
		return nm_Fornecedor;
	}
	public void setNm_Fornecedor(String nm_Fornecedor) {
		this.nm_Fornecedor = nm_Fornecedor;
	}
	public String getNm_Banda() {
		return nm_Banda;
	}
	public void setNm_Banda(String nm_Banda) {
		this.nm_Banda = nm_Banda;
	}
	public String getNm_Vida_Ext() {
		String nm = null;
		if (this.getNr_Vida() == 0)
			nm = "Novo";
		if (this.getNr_Vida() == 1)
			nm = "1º Rec.";
		if (this.getNr_Vida() == 2)
			nm = "2º Rec.";
		if (this.getNr_Vida() == 3)
			nm = "3º Rec.";
		if (this.getNr_Vida() == 4)
			nm = "4º Rec.";
		if (this.getNr_Vida() == 5)
			nm = "5º Rec.";
		if (this.getNr_Vida() == 6)
			nm = "6º Rec.";
		if (this.getNr_Vida() == 7)
			nm = "7º Rec.";
		return nm;
	}
	public void setNm_Vida_Ext(String nm_Vida_Ext) {
		this.nm_Vida_Ext = nm_Vida_Ext;
	}
	public String getNr_Frota() {
		return nr_Frota;
	}
	public void setNr_Frota(String nr_Frota) {
		this.nr_Frota = nr_Frota;
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

	public long getOid_Dimensao_Pneu() {
		return oid_Dimensao_Pneu;
	}

	public void setOid_Dimensao_Pneu(long oid_Dimensao_Pneu) {
		this.oid_Dimensao_Pneu = oid_Dimensao_Pneu;
	}

	public long getOid_Fabricante_Pneu() {
		return oid_Fabricante_Pneu;
	}

	public void setOid_Fabricante_Pneu(long oid_Fabricante_Pneu) {
		this.oid_Fabricante_Pneu = oid_Fabricante_Pneu;
	}

	public long getOid_Modelo_Pneu() {
		return oid_Modelo_Pneu;
	}

	public void setOid_Modelo_Pneu(long oid_Modelo_Pneu) {
		this.oid_Modelo_Pneu = oid_Modelo_Pneu;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
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

	public int getNr_Qtd_N0() {
		return nr_Qtd_N0;
	}

	public void setNr_Qtd_N0(int nr_Qtd_N0) {
		this.nr_Qtd_N0 = nr_Qtd_N0;
	}

	public int getNr_Qtd_R1() {
		return nr_Qtd_R1;
	}

	public void setNr_Qtd_R1(int nr_Qtd_R1) {
		this.nr_Qtd_R1 = nr_Qtd_R1;
	}

	public int getNr_Qtd_R2() {
		return nr_Qtd_R2;
	}

	public void setNr_Qtd_R2(int nr_Qtd_R2) {
		this.nr_Qtd_R2 = nr_Qtd_R2;
	}

	public int getNr_Qtd_R3() {
		return nr_Qtd_R3;
	}

	public void setNr_Qtd_R3(int nr_Qtd_R3) {
		this.nr_Qtd_R3 = nr_Qtd_R3;
	}

	public int getNr_Qtd_R4() {
		return nr_Qtd_R4;
	}

	public void setNr_Qtd_R4(int nr_Qtd_R4) {
		this.nr_Qtd_R4 = nr_Qtd_R4;
	}

	public int getNr_Qtd_R5() {
		return nr_Qtd_R5;
	}

	public void setNr_Qtd_R5(int nr_Qtd_R5) {
		this.nr_Qtd_R5 = nr_Qtd_R5;
	}

	public int getNr_Qtd_R6() {
		return nr_Qtd_R6;
	}

	public void setNr_Qtd_R6(int nr_Qtd_R6) {
		this.nr_Qtd_R6 = nr_Qtd_R6;
	}

	public double getVl_Vida_N0() {
		return vl_Vida_N0;
	}

	public void setVl_Vida_N0(double vl_Vida_N0) {
		this.vl_Vida_N0 = vl_Vida_N0;
	}

	public double getVl_Vida_R1() {
		return vl_Vida_R1;
	}

	public void setVl_Vida_R1(double vl_Vida_R1) {
		this.vl_Vida_R1 = vl_Vida_R1;
	}

	public double getVl_Vida_R2() {
		return vl_Vida_R2;
	}

	public void setVl_Vida_R2(double vl_Vida_R2) {
		this.vl_Vida_R2 = vl_Vida_R2;
	}

	public double getVl_Vida_R3() {
		return vl_Vida_R3;
	}

	public void setVl_Vida_R3(double vl_Vida_R3) {
		this.vl_Vida_R3 = vl_Vida_R3;
	}

	public double getVl_Vida_R4() {
		return vl_Vida_R4;
	}

	public void setVl_Vida_R4(double vl_Vida_R4) {
		this.vl_Vida_R4 = vl_Vida_R4;
	}

	public double getVl_Vida_R5() {
		return vl_Vida_R5;
	}

	public void setVl_Vida_R5(double vl_Vida_R5) {
		this.vl_Vida_R5 = vl_Vida_R5;
	}

	public double getVl_Vida_R6() {
		return vl_Vida_R6;
	}

	public void setVl_Vida_R6(double vl_Vida_R6) {
		this.vl_Vida_R6 = vl_Vida_R6;
	}

	public double getVl_Venda() {
		return vl_Venda;
	}

	public void setVl_Venda(double vl_Venda) {
		this.vl_Venda = vl_Venda;
	}


	public String getDm_Situacao() {
		return dm_Situacao;
	}

	public void setDm_Situacao(String dm_Situacao) {
		this.dm_Situacao = dm_Situacao;
	}

	public String getDm_Total() {
		return dm_Total;
	}

	public void setDm_Total(String dm_Total) {
		this.dm_Total = dm_Total;
	}

	public String getDm_Vida_Ate() {
		return dm_Vida_Ate;
	}

	public void setDm_Vida_Ate(String dm_Vida_Ate) {
		this.dm_Vida_Ate = dm_Vida_Ate;
	}

	public String getDm_Vida_So() {
		return dm_Vida_So;
	}

	public void setDm_Vida_So(String dm_Vida_So) {
		this.dm_Vida_So = dm_Vida_So;
	}

	public String getDm_Tipo() {
		return dm_Tipo;
	}

	public void setDm_Tipo(String dm_Tipo) {
		this.dm_Tipo = dm_Tipo;
	}

	public String getDm_Vl_Venda() {
		return dm_Vl_Venda;
	}

	public void setDm_Vl_Venda(String dm_Vl_Venda) {
		this.dm_Vl_Venda = dm_Vl_Venda;
	}

	public String getDm_Agrupa_Dimensao() {
		return dm_Agrupa_Dimensao;
	}

	public void setDm_Agrupa_Dimensao(String dm_Agrupa_Dimensao) {
		this.dm_Agrupa_Dimensao = dm_Agrupa_Dimensao;
	}

	public String getDm_Agrupa_Eixo() {
		return dm_Agrupa_Eixo;
	}

	public void setDm_Agrupa_Eixo(String dm_Agrupa_Eixo) {
		this.dm_Agrupa_Eixo = dm_Agrupa_Eixo;
	}

	public String getDm_Agrupa_Marca() {
		return dm_Agrupa_Marca;
	}

	public void setDm_Agrupa_Marca(String dm_Agrupa_Marca) {
		this.dm_Agrupa_Marca = dm_Agrupa_Marca;
	}

	public String getDm_Agrupa_Modelo() {
		return dm_Agrupa_Modelo;
	}

	public void setDm_Agrupa_Modelo(String dm_Agrupa_Modelo) {
		this.dm_Agrupa_Modelo = dm_Agrupa_Modelo;
	}

	public int getNr_Vida_Ate() {
		return nr_Vida_Ate;
	}

	public void setNr_Vida_Ate(int nr_Vida_Ate) {
		this.nr_Vida_Ate = nr_Vida_Ate;
	}

	public int getNr_Vida_So() {
		return nr_Vida_So;
	}

	public void setNr_Vida_So(int nr_Vida_So) {
		this.nr_Vida_So = nr_Vida_So;
	}

	public String getDm_Agrupa_Desenho() {
		return dm_Agrupa_Desenho;
	}

	public void setDm_Agrupa_Desenho(String dm_Agrupa_Desenho) {
		this.dm_Agrupa_Desenho = dm_Agrupa_Desenho;
	}

	public String getNm_Razao_Social() {
		return nm_Razao_Social;
	}

	public void setNm_Razao_Social(String nm_Razao_Social) {
		this.nm_Razao_Social = nm_Razao_Social;
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

	public String getDm_Modelo_Pneu() {
		return dm_Modelo_Pneu;
	}

	public void setDm_Modelo_Pneu(String dm_Modelo_Pneu) {
		this.dm_Modelo_Pneu = dm_Modelo_Pneu;
	}

	public int getNr_Qtd_Total_Vidas() {
		return nr_Qtd_Total_Vidas;
	}

	public void setNr_Qtd_Total_Vidas(int nr_Qtd_Total_Vidas) {
		this.nr_Qtd_Total_Vidas = nr_Qtd_Total_Vidas;
	}

	public double getVl_Vida_Total() {
		return vl_Vida_Total;
	}

	public void setVl_Vida_Total(double vl_Vida_Total) {
		this.vl_Vida_Total = vl_Vida_Total;
	}

	public double getNr_Km_Vida() {
		return nr_Km_Vida;
	}

	public void setNr_Km_Vida(double nr_Km_Vida) {
		this.nr_Km_Vida = nr_Km_Vida;
	}

	public String getNm_Graf() {
		return nm_Graf;
	}

	public void setNm_Graf(String nm_Graf) {
		this.nm_Graf = nm_Graf;
	}

	public String getCd_Banda() {
		return cd_Banda;
	}

	public void setCd_Banda(String cd_Banda) {
		this.cd_Banda = cd_Banda;
	}

	public String getCd_Fabricante_Banda() {
		return cd_Fabricante_Banda;
	}

	public void setCd_Fabricante_Banda(String cd_Fabricante_Banda) {
		this.cd_Fabricante_Banda = cd_Fabricante_Banda;
	}

	public String getNr_Cnpj_Cpf() {
		return nr_Cnpj_Cpf;
	}

	public void setNr_Cnpj_Cpf(String nr_Cnpj_Cpf) {
		this.nr_Cnpj_Cpf = nr_Cnpj_Cpf;
	}

	public String getCd_Pneu() {
		return cd_Pneu;
	}

	public void setCd_Pneu(String cd_Pneu) {
		this.cd_Pneu = cd_Pneu;
	}

	public String getCd_Modelo_Pneu() {
		return cd_Modelo_Pneu;
	}

	public void setCd_Modelo_Pneu(String cd_Modelo_Pneu) {
		this.cd_Modelo_Pneu = cd_Modelo_Pneu;
	}

}
