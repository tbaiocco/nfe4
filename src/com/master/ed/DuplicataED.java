package com.master.ed;

import javax.servlet.http.*;

/**
 * @author André Valadas
 */

public class DuplicataED
    extends RelatorioBaseED {

  public DuplicataED () {
    try {
      jbInit ();
    }
    catch (Exception ex) {
      ex.printStackTrace ();
    }
  }

  public DuplicataED (HttpServletResponse response , String nomeRelatorio) {
    super (response , nomeRelatorio);
  }

  public DuplicataED (long duplicata) {
    oid_Duplicata = duplicata;
  }

  public DuplicataED (String oid_Pessoa) {
    this.oid_Pessoa = oid_Pessoa;
  }

  //private Integer oid_Duplicata;
  private long oid_Duplicata;
  private long oid_Duplicata_Principal;
  private String nr_Documento;
  private Integer nr_Parcela;
  private String dt_Emissao;
  private String dt_Fluxo;
  private String dt_Fluxo_Inicial;
  private String dt_Fluxo_Final;
  private String DM_Tipo_Documento;
  private String dt_Vencimento;
  private String DT_Vencimento_Inicial;
  private String DT_Vencimento_Final;
  private Double vl_Duplicata;
  private Double vl_Taxa_Cobranca;
  private Integer nr_Duplicata;
  private Double vl_Desconto_Faturamento;
  private Double vl_Desconto_Vencimento;
  private Double vl_Saldo;
  private Integer oid_Tipo_Documento;
  private Integer oid_Carteira;
  private String oid_Pessoa;
  private Long oid_Unidade;
  private Long oid_Grupo_Economico;
  private String nr_CNPJ_CPF;
  private String nm_Razao_Social;
  private String cd_Tipo_Documento;
  private String nm_Tipo_Documento;
  private String cd_Vendedor;
  private String nm_Vendedor;
  private String cd_Carteira;
  private String nm_Carteira;
  private String cd_Unidade;
  private String nm_Fantasia;
  private String dm_Numeracao_Automatica;
  private String nr_Proximo_Numero;
  private String oid_Vendedor;
  private String CD_Conta_Corrente;
  private String NM_Banco;
  private String NR_Bancario;
  private Double VL_Juro_Mora_Dia;
  private Double VL_Multa;
  private String DT_Emissao_Inicial;
  private String DT_Emissao_Final;
  private long NR_Duplicata_Inicial;
  private long NR_Duplicata_Final;
  private String NM_Endereco;
  private String NR_CEP;
  private String NM_Bairro;
  private String NM_Cidade;
  private String CD_Estado;
  private String NM_Pais;
  private String NM_Inscricao_Estadual;
  private Integer nr_Duplicata_Final;
  private String CD_Moeda;
  private Integer OID_Moeda;
  private String CD_Banco;
  private String NR_Bloqueto_informado;
  private String NR_Bloqueto_Calculado;
  private String DM_Quebra_Faturamento;
  private String DM_Tipo_Inclusao;
  private String NR_Digito_Bloqueto;
  private double vl_Saldo_Atualizado;
  private String DM_Remete_EDI;
  private int oidBordero;
  private String DM_Duplicata_Com_Origem;
  private String NR_Fone_Fax;
  private String NM_EMail;
  private String NR_CRT;
  private String NR_Conhecimento;
  private String DT_Emissao_CRT;
  private String DT_Vencimento_CRT;
  private String VL_CRT_Dolar;
  private String VL_Total_CRT_Dolar;
  private String VL_Cambio_Dia;
  private String VL_Numerario_Antecipado;
  private String VL_Total_Duplicata_Moeda_Unidade;
  private String VL_Por_Extenso;
  private String NM_Condicoes_Especiais;
  private String NR_Faturas_Por_CRT;
  private String VL_Total_CRTs;

  private String VL_Percentual_Desconto;
  private String VL_Faixa_Inicial_Desconto;
  private String VL_Faixa_Final_Desconto;

  private String DT_Emissao_Fatura;
  private String DT_Vencimento_Fatura;
  private String NR_Fatura;
  private String VL_Fatura;
  private String DT_Aceite;
  private String DT_Recebimento;
  private String VL_Taxa_Cobranca;

  private String NM_Razao_Social_Cliente;
  private String NM_Endereco_Cliente;
  private String NM_Bairro_Cliente;
  private String NR_CEP_Cliente;
  private String NM_Cidade_Cliente;
  private String CD_Estado_Cliente;
  private String NM_Pais_Cliente;
  private String Nr_CNPJ_CPF_Cliente;
  private String NM_Inscricao_Estadual_Cliente;

  private String TX_Observacao;

  private String Nr_Remessa;

  private double VL_Tarifa;
  private double VL_Desconto;
  private double VL_Desconto_Faturamento;
  private double VL_Pago;
  private double VL_Juros;
  private double VL_Liquido;
  private String DM_Tipo_Pagamento;
  private double VL_Juros_Reembolso;
  private double VL_Reembolso;
  private double VL_Variacao_Cambial_Passiva;
  private double VL_Variacao_Cambial_Ativa;
  private double VL_Devolucao_Nota_Fiscal;
  private double VL_Saldo_Calculado;
  private String DT_Movimento;
  private double VL_Saldo_Atual;
  private double VL_Variacao_Cambial;
  private double VL_Cotacao_Emissao;
  private double VL_Cotacao_Pagamento;
  private int NR_Dias_Atraso;
  private String DM_Atualiza_Saldo;
  private double VL_Cotacao_Informada;
  private String oid_Nota_Fiscal;
  private String oid_Protocolo_Entrega;
  private int NR_Nota_Fiscal;
  private String DM_Situacao;

  private String DM_Tipo_Cobranca;

  private String NM_Razao_Social;
  private String DT_Credito;
  private double VL_Imposto_Retido1;
  private double VL_Imposto_Retido2;

  private String NM_Praca_Pagamento;
  private double VL_Total_Frete_Conhecimento;
  private String OID_Conhecimento;
  private double vl_Comissao;
  private String NM_Situacao;

  private double VL_Titulo_Inicial;
  private double VL_Titulo_Final;
  private String DM_Lista;

  private String NM_Site;

  // public Long getOid_Duplicata() {
  // return oid_Duplicata;
  // }
  // public void setOid_Duplicata(Long oid_Duplicata) {
  // this.oid_Duplicata = oid_Duplicata;
  // }

  public String getVL_Total_CRTs () {
    return VL_Total_CRTs;
  }

  public void setVL_Total_CRTs (String total_CRTs) {
    VL_Total_CRTs = total_CRTs;
  }

  public String getDM_Tipo_Documento () {
    return DM_Tipo_Documento;
  }

  public void setDM_Tipo_Documento (String DM_Tipo_Documento) {
    this.DM_Tipo_Documento = DM_Tipo_Documento;
  }

  public String getNR_Faturas_Por_CRT () {
    return NR_Faturas_Por_CRT;
  }

  public void setNR_Faturas_Por_CRT (String faturas_Por_CRT) {
    NR_Faturas_Por_CRT = faturas_Por_CRT;
  }

  public String getVL_Por_Extenso () {
    return VL_Por_Extenso;
  }

  public void setVL_Por_Extenso (String por_Extenso) {
    VL_Por_Extenso = por_Extenso;
  }

  public String getVL_Cambio_Dia () {
    return VL_Cambio_Dia;
  }

  public void setVL_Cambio_Dia (String cambio_Dia) {
    VL_Cambio_Dia = cambio_Dia;
  }

  public String getVL_CRT_Dolar () {
    return VL_CRT_Dolar;
  }

  public void setVL_CRT_Dolar (String dolar) {
    VL_CRT_Dolar = dolar;
  }

  public String getVL_Faixa_Final_Desconto () {
    return VL_Faixa_Final_Desconto;
  }

  public void setVL_Faixa_Final_Desconto (String faixa_Final_Desconto) {
    VL_Faixa_Final_Desconto = faixa_Final_Desconto;
  }

  public String getVL_Faixa_Inicial_Desconto () {
    return VL_Faixa_Inicial_Desconto;
  }

  public void setVL_Faixa_Inicial_Desconto (String faixa_Inicial_Desconto) {
    VL_Faixa_Inicial_Desconto = faixa_Inicial_Desconto;
  }

  public String getVL_Fatura () {
    return VL_Fatura;
  }

  public void setVL_Fatura (String fatura) {
    VL_Fatura = fatura;
  }

  public String getVL_Numerario_Antecipado () {
    return VL_Numerario_Antecipado;
  }

  public void setVL_Numerario_Antecipado (String numerario_Antecipado) {
    VL_Numerario_Antecipado = numerario_Antecipado;
  }

  public String getVL_Percentual_Desconto () {
    return VL_Percentual_Desconto;
  }

  public void setVL_Percentual_Desconto (String percentual_Desconto) {
    VL_Percentual_Desconto = percentual_Desconto;
  }

  public String getVL_Taxa_Cobranca () {
    return VL_Taxa_Cobranca;
  }

  public void setVL_Taxa_Cobranca (String taxa_Cobranca) {
    VL_Taxa_Cobranca = taxa_Cobranca;
  }

  public String getVL_Total_CRT_Dolar () {
    return VL_Total_CRT_Dolar;
  }

  public void setVL_Total_CRT_Dolar (String total_CRT_Dolar) {
    VL_Total_CRT_Dolar = total_CRT_Dolar;
  }

  public String getVL_Total_Duplicata_Moeda_Unidade () {
    return VL_Total_Duplicata_Moeda_Unidade;
  }

  public void setVL_Total_Duplicata_Moeda_Unidade (String total_Duplicata_Moeda_Unidade) {
    VL_Total_Duplicata_Moeda_Unidade = total_Duplicata_Moeda_Unidade;
  }

  public String getDT_Recebimento () {
    return DT_Recebimento;
  }

  public void setDT_Recebimento (String recebimento) {
    DT_Recebimento = recebimento;
  }

  public String getDT_Aceite () {
    return DT_Aceite;
  }

  public void setDT_Aceite (String aceite) {
    DT_Aceite = aceite;
  }

  public String getNM_Condicoes_Especiais () {
    return NM_Condicoes_Especiais;
  }

  public void setNM_Condicoes_Especiais (String condicoes_Especiais) {
    NM_Condicoes_Especiais = condicoes_Especiais;
  }

  public String getDT_Emissao_Fatura () {
    return DT_Emissao_Fatura;
  }

  public void setDT_Emissao_Fatura (String emissao_Fatura) {
    DT_Emissao_Fatura = emissao_Fatura;
  }

  public String getDT_Vencimento_Fatura () {
    return DT_Vencimento_Fatura;
  }

  public void setDT_Vencimento_Fatura (String vencimento_Fatura) {
    DT_Vencimento_Fatura = vencimento_Fatura;
  }

  public String getNR_Fatura () {
    return NR_Fatura;
  }

  public void setNR_Fatura (String fatura) {
    NR_Fatura = fatura;
  }

  public String getNM_Bairro_Cliente () {
    return NM_Bairro_Cliente;
  }

  public void setNM_Bairro_Cliente (String bairro_Cliente) {
    NM_Bairro_Cliente = bairro_Cliente;
  }

  public String getDM_Duplicata_Com_Origem () {
    return DM_Duplicata_Com_Origem;
  }

  public void setDM_Duplicata_Com_Origem (String duplicata_Com_Origem) {
    DM_Duplicata_Com_Origem = duplicata_Com_Origem;
  }

  public String getCD_Estado_Cliente () {
    return CD_Estado_Cliente;
  }

  public void setCD_Estado_Cliente (String estado_Cliente) {
    CD_Estado_Cliente = estado_Cliente;
  }

  public String getNM_Cidade_Cliente () {
    return NM_Cidade_Cliente;
  }

  public void setNM_Cidade_Cliente (String cidade_Cliente) {
    NM_Cidade_Cliente = cidade_Cliente;
  }

  public String getNM_Endereco_Cliente () {
    return NM_Endereco_Cliente;
  }

  public void setNM_Endereco_Cliente (String endereco_Cliente) {
    NM_Endereco_Cliente = endereco_Cliente;
  }

  public String getNM_Inscricao_Estadual_Cliente () {
    return NM_Inscricao_Estadual_Cliente;
  }

  public void setNM_Inscricao_Estadual_Cliente (String inscricao_Estadual_Cliente) {
    NM_Inscricao_Estadual_Cliente = inscricao_Estadual_Cliente;
  }

  public String getNM_Pais_Cliente () {
    return NM_Pais_Cliente;
  }

  public void setNM_Pais_Cliente (String pais_Cliente) {
    NM_Pais_Cliente = pais_Cliente;
  }

  public String getNM_Razao_Social_Cliente () {
    return NM_Razao_Social_Cliente;
  }

  public void setNM_Razao_Social_Cliente (String razao_Social_Cliente) {
    NM_Razao_Social_Cliente = razao_Social_Cliente;
  }

  public String getNR_CEP_Cliente () {
    return NR_CEP_Cliente;
  }

  public void setNR_CEP_Cliente (String cliente) {
    NR_CEP_Cliente = cliente;
  }

  public String getNr_CNPJ_CPF_Cliente () {
    return Nr_CNPJ_CPF_Cliente;
  }

  public void setNr_CNPJ_CPF_Cliente (String nr_CNPJ_CPF_Cliente) {
    Nr_CNPJ_CPF_Cliente = nr_CNPJ_CPF_Cliente;
  }

  public String getDT_Emissao_CRT () {
    return DT_Emissao_CRT;
  }

  public void setDT_Emissao_CRT (String emissao_CRT) {
    DT_Emissao_CRT = emissao_CRT;
  }

  public String getDT_Vencimento_CRT () {
    return DT_Vencimento_CRT;
  }

  public void setDT_Vencimento_CRT (String vencimento_CRT) {
    DT_Vencimento_CRT = vencimento_CRT;
  }

  public String getNR_CRT () {
    return NR_CRT;
  }

  public void setNR_CRT (String nr_crt) {
    NR_CRT = nr_crt;
  }

  public String getNM_EMail () {
    return NM_EMail;
  }

  public void setNM_EMail (String mail) {
    NM_EMail = mail;
  }

  public String getNR_Fone_Fax () {
    return NR_Fone_Fax;
  }

  public void setNR_Fone_Fax (String fone_Fax) {
    NR_Fone_Fax = fone_Fax;
  }

  public String getNM_Pais () {
    return NM_Pais;
  }

  public void setNM_Pais (String pais) {
    NM_Pais = pais;
  }

  public int getOidBordero () {
    return oidBordero;
  }

  public void setOidBordero (int oidBordero) {
    this.oidBordero = oidBordero;
  }

  public String getDt_Emissao () {
    return dt_Emissao;
  }

  public String getDt_Vencimento () {
    return dt_Vencimento;
  }

  public Integer getNr_Duplicata () {
    return nr_Duplicata;
  }

  public String getNr_Documento () {
    return nr_Documento;
  }

  public Integer getNr_Parcela () {
    return nr_Parcela;
  }

  public String getOid_Vendedor () {
    return oid_Vendedor;
  }

  public long getOid_Duplicata () {
    return oid_Duplicata;
  }

  public Integer getOid_Carteira () {
    return oid_Carteira;
  }

  public String getOid_Pessoa () {
    return oid_Pessoa;
  }

  public Integer getOid_Tipo_Documento () {
    return oid_Tipo_Documento;
  }

  public Long getOid_Unidade () {
    return oid_Unidade;
  }

  public Double getVl_Duplicata () {
    return vl_Duplicata;
  }

  public Double getVl_Desconto_Faturamento () {
    return vl_Desconto_Faturamento;
  }

  public Double getVl_Taxa_Cobranca () {
    return vl_Taxa_Cobranca;
  }

  public Double getVl_Saldo () {
    return vl_Saldo;
  }

  public void setVl_Saldo (Double vl_Saldo) {
    this.vl_Saldo = vl_Saldo;
  }

  public void setVl_Taxa_Cobranca (Double vl_Taxa_Cobranca) {
    this.vl_Taxa_Cobranca = vl_Taxa_Cobranca;
  }

  public void setVl_Desconto_Faturamento (Double vl_Desconto_Faturamento) {
    this.vl_Desconto_Faturamento = vl_Desconto_Faturamento;
  }

  public void setVl_Duplicata (Double vl_Duplicata) {
    this.vl_Duplicata = vl_Duplicata;
  }

  public void setOid_Unidade (Long oid_Unidade) {
    this.oid_Unidade = oid_Unidade;
  }

  public void setOid_Tipo_Documento (Integer oid_Tipo_Documento) {
    this.oid_Tipo_Documento = oid_Tipo_Documento;
  }

  public void setOid_Pessoa (String oid_Pessoa) {
    this.oid_Pessoa = oid_Pessoa;
  }

  public void setOid_Carteira (Integer oid_Carteira) {
    this.oid_Carteira = oid_Carteira;
  }

  public void setOid_Duplicata (long oid_Duplicata) {
    this.oid_Duplicata = oid_Duplicata;
  }

  public void setOid_Vendedor (String oid_Vendedor) {
    this.oid_Vendedor = oid_Vendedor;
  }

  public void setNr_Parcela (Integer nr_Parcela) {
    this.nr_Parcela = nr_Parcela;
  }

  public void setNr_Documento (String nr_Documento) {
    this.nr_Documento = nr_Documento;
  }

  public void setNr_Duplicata (Integer nr_Duplicata) {
    this.nr_Duplicata = nr_Duplicata;
  }

  public void setDt_Vencimento (String dt_Vencimento) {
    this.dt_Vencimento = dt_Vencimento;
  }

  public void setDt_Emissao (String dt_Emissao) {
    this.dt_Emissao = dt_Emissao;
  }

  public void setNr_CNPJ_CPF (String nr_CNPJ_CPF) {
    this.nr_CNPJ_CPF = nr_CNPJ_CPF;
  }

  public String getNr_CNPJ_CPF () {
    return nr_CNPJ_CPF;
  }

  public void setNm_Razao_Social (String nm_Razao_Social) {
    this.nm_Razao_Social = nm_Razao_Social;
  }

  public String getNm_Razao_Social () {
    return nm_Razao_Social;
  }

  public void setCd_Tipo_Documento (String cd_Tipo_Documento) {
    this.cd_Tipo_Documento = cd_Tipo_Documento;
  }

  public String getCd_Tipo_Documento () {
    return cd_Tipo_Documento;
  }

  public void setNm_Tipo_Documento (String nm_Tipo_Documento) {
    this.nm_Tipo_Documento = nm_Tipo_Documento;
  }

  public String getNm_Tipo_Documento () {
    return nm_Tipo_Documento;
  }

  public void setCd_Vendedor (String cd_Vendedor) {
    this.cd_Vendedor = cd_Vendedor;
  }

  public String getCd_Vendedor () {
    return cd_Vendedor;
  }

  public void setNm_Vendedor (String nm_Vendedor) {
    this.nm_Vendedor = nm_Vendedor;
  }

  public String getNm_Vendedor () {
    return nm_Vendedor;
  }

  public void setCd_Carteira (String cd_Carteira) {
    this.cd_Carteira = cd_Carteira;
  }

  public String getCd_Carteira () {
    return cd_Carteira;
  }

  public void setNm_Carteira (String nm_Carteira) {
    this.nm_Carteira = nm_Carteira;
  }

  public String getNm_Carteira () {
    return nm_Carteira;
  }

  public void setCd_Unidade (String cd_Unidade) {
    this.cd_Unidade = cd_Unidade;
  }

  public String getCd_Unidade () {
    return cd_Unidade;
  }

  public void setNm_Fantasia (String nm_Fantasia) {
    this.nm_Fantasia = nm_Fantasia;
  }

  public String getNm_Fantasia () {
    return nm_Fantasia;
  }

  public void setDm_Numeracao_Automatica (String dm_Numeracao_Automatica) {
    this.dm_Numeracao_Automatica = dm_Numeracao_Automatica;
  }

  public String getDm_Numeracao_Automatica () {
    return dm_Numeracao_Automatica;
  }

  public void setNr_Proximo_Numero (String nr_Proximo_Numero) {
    this.nr_Proximo_Numero = nr_Proximo_Numero;
  }

  public String getNr_Proximo_Numero () {
    return nr_Proximo_Numero;
  }

  public void setCD_Conta_Corrente (String CD_Conta_Corrente) {
    this.CD_Conta_Corrente = CD_Conta_Corrente;
  }

  public String getCD_Conta_Corrente () {
    return CD_Conta_Corrente;
  }

  public void setNM_Banco (String NM_Banco) {
    this.NM_Banco = NM_Banco;
  }

  public String getNM_Banco () {
    return NM_Banco;
  }

  public void setNR_Bancario (String NR_Bancario) {
    this.NR_Bancario = NR_Bancario;
  }

  public String getNR_Bancario () {
    return NR_Bancario;
  }

  public void setVL_Juro_Mora_Dia (Double VL_Juro_Mora_Dia) {
    this.VL_Juro_Mora_Dia = VL_Juro_Mora_Dia;
  }

  public Double getVL_Juro_Mora_Dia () {
    return VL_Juro_Mora_Dia;
  }

  public void setVL_Multa (Double VL_Multa) {
    this.VL_Multa = VL_Multa;
  }

  public Double getVL_Multa () {
    return VL_Multa;
  }

  public void setDT_Emissao_Inicial (String DT_Emissao_Inicial) {
    this.DT_Emissao_Inicial = DT_Emissao_Inicial;
  }

  public String getDT_Emissao_Inicial () {
    return DT_Emissao_Inicial;
  }

  public void setDT_Emissao_Final (String DT_Emissao_Final) {
    this.DT_Emissao_Final = DT_Emissao_Final;
  }

  public String getDT_Emissao_Final () {
    return DT_Emissao_Final;
  }

  public void setNM_Endereco (String NM_Endereco) {
    this.NM_Endereco = NM_Endereco;
  }

  public String getNM_Endereco () {
    return NM_Endereco;
  }

  public void setNR_CEP (String NR_CEP) {
    this.NR_CEP = NR_CEP;
  }

  public String getNR_CEP () {
    return NR_CEP;
  }

  public void setNM_Bairro (String NM_Bairro) {
    this.NM_Bairro = NM_Bairro;
  }

  public String getNM_Bairro () {
    return NM_Bairro;
  }

  public void setNM_Cidade (String NM_Cidade) {
    this.NM_Cidade = NM_Cidade;
  }

  public String getNM_Cidade () {
    return NM_Cidade;
  }

  public void setCD_Estado (String CD_Estado) {
    this.CD_Estado = CD_Estado;
  }

  public String getCD_Estado () {
    return CD_Estado;
  }

  public void setNM_Inscricao_Estadual (String NM_Inscricao_Estadual) {
    this.NM_Inscricao_Estadual = NM_Inscricao_Estadual;
  }

  public String getNM_Inscricao_Estadual () {
    return NM_Inscricao_Estadual;
  }

  public void setNr_Duplicata_Final (Integer nr_Duplicata_Final) {
    this.nr_Duplicata_Final = nr_Duplicata_Final;
  }

  public Integer getNr_Duplicata_Final () {
    return nr_Duplicata_Final;
  }

  public void setCD_Moeda (String CD_Moeda) {
    this.CD_Moeda = CD_Moeda;
  }

  public String getCD_Moeda () {
    return CD_Moeda;
  }

  public void setOID_Moeda (Integer OID_Moeda) {
    this.OID_Moeda = OID_Moeda;
  }

  public Integer getOID_Moeda () {
    return OID_Moeda;
  }

  public void setCD_Banco (String CD_Banco) {
    this.CD_Banco = CD_Banco;
  }

  public String getCD_Banco () {
    return CD_Banco;
  }

  public void setNR_Bloqueto_informado (String NR_Bloqueto_informado) {
    this.NR_Bloqueto_informado = NR_Bloqueto_informado;
  }

  public String getNR_Bloqueto_informado () {
    return NR_Bloqueto_informado;
  }

  public void setNR_Bloqueto_Calculado (String NR_Bloqueto_Calculado) {
    this.NR_Bloqueto_Calculado = NR_Bloqueto_Calculado;
  }

  public String getNR_Bloqueto_Calculado () {
    return NR_Bloqueto_Calculado;
  }

  public void setDM_Quebra_Faturamento (String DM_Quebra_Faturamento) {
    this.DM_Quebra_Faturamento = DM_Quebra_Faturamento;
  }

  public String getDM_Quebra_Faturamento () {
    return DM_Quebra_Faturamento;
  }

  public void setNR_Digito_Bloqueto (String NR_Digito_Bloqueto) {
    this.NR_Digito_Bloqueto = NR_Digito_Bloqueto;
  }

  public String getNR_Digito_Bloqueto () {
    return NR_Digito_Bloqueto;
  }

  public void setVl_Saldo_Atualizado (double vl_Saldo_Atualizado) {
    this.vl_Saldo_Atualizado = vl_Saldo_Atualizado;
  }

  public void setVL_Cotacao_Informada (double VL_Cotacao_Informada) {
    this.VL_Cotacao_Informada = VL_Cotacao_Informada;
  }

  public void setDM_Atualiza_Saldo (String DM_Atualiza_Saldo) {
    this.DM_Atualiza_Saldo = DM_Atualiza_Saldo;
  }

  public void setNR_Dias_Atraso (int NR_Dias_Atraso) {
    this.NR_Dias_Atraso = NR_Dias_Atraso;
  }

  public void setVL_Cotacao_Pagamento (double VL_Cotacao_Pagamento) {

    this.VL_Cotacao_Pagamento = VL_Cotacao_Pagamento;
  }

  public void setVL_Cotacao_Emissao (double VL_Cotacao_Emissao) {

    this.VL_Cotacao_Emissao = VL_Cotacao_Emissao;
  }

  public void setVL_Variacao_Cambial (double VL_Variacao_Cambial) {
    this.VL_Variacao_Cambial = VL_Variacao_Cambial;
  }

  public void setVL_Saldo_Atual (double VL_Saldo_Atual) {
    this.VL_Saldo_Atual = VL_Saldo_Atual;
  }

  public void setVL_Desconto (double VL_Desconto) {
    this.VL_Desconto = VL_Desconto;
  }

  public void setVL_Variacao_Cambial_Passiva (double VL_Variacao_Cambial_Passiva) {
    this.VL_Variacao_Cambial_Passiva = VL_Variacao_Cambial_Passiva;
  }

  public void setDT_Movimento (String DT_Movimento) {
    this.DT_Movimento = DT_Movimento;
  }

  public void setVL_Saldo_Calculado (double VL_Saldo_Calculado) {
    this.VL_Saldo_Calculado = VL_Saldo_Calculado;
  }

  public void setVL_Tarifa (double VL_Tarifa) {
    this.VL_Tarifa = VL_Tarifa;
  }

  public void setVL_Reembolso (double VL_Reembolso) {
    this.VL_Reembolso = VL_Reembolso;
  }

  public void setDM_Tipo_Pagamento (String DM_Tipo_Pagamento) {
    this.DM_Tipo_Pagamento = DM_Tipo_Pagamento;
  }

  public void setVL_Variacao_Cambial_Ativa (double VL_Variacao_Cambial_Ativa) {

    this.VL_Variacao_Cambial_Ativa = VL_Variacao_Cambial_Ativa;
  }

  public void setVL_Devolucao_Nota_Fiscal (double VL_Devolucao_Nota_Fiscal) {
    this.VL_Devolucao_Nota_Fiscal = VL_Devolucao_Nota_Fiscal;
  }

  public void setVL_Desconto_Faturamento (double VL_Desconto_Faturamento) {
    this.VL_Desconto_Faturamento = VL_Desconto_Faturamento;
  }

  public void setVL_Juros (double VL_Juros) {
    this.VL_Juros = VL_Juros;
  }

  public void setVL_Juros_Reembolso (double VL_Juros_Reembolso) {
    this.VL_Juros_Reembolso = VL_Juros_Reembolso;
  }

  public void setVL_Liquido (double VL_Liquido) {
    this.VL_Liquido = VL_Liquido;
  }

  public void setVL_Pago (double VL_Pago) {
    this.VL_Pago = VL_Pago;
  }

  public double getVl_Saldo_Atualizado () {
    return vl_Saldo_Atualizado;
  }

  public double getVL_Cotacao_Informada () {
    return VL_Cotacao_Informada;
  }

  public String getDM_Atualiza_Saldo () {
    return DM_Atualiza_Saldo;
  }

  public int getNR_Dias_Atraso () {
    return NR_Dias_Atraso;
  }

  public double getVL_Cotacao_Pagamento () {

    return VL_Cotacao_Pagamento;
  }

  public double getVL_Cotacao_Emissao () {

    return VL_Cotacao_Emissao;
  }

  public double getVL_Variacao_Cambial () {
    return VL_Variacao_Cambial;
  }

  public double getVL_Saldo_Atual () {
    return VL_Saldo_Atual;
  }

  public String getDT_Movimento () {
    return DT_Movimento;
  }

  public double getVL_Saldo_Calculado () {
    return VL_Saldo_Calculado;
  }

  public double getVL_Tarifa () {
    return VL_Tarifa;
  }

  public double getVL_Reembolso () {
    return VL_Reembolso;
  }

  public String getDM_Tipo_Pagamento () {
    return DM_Tipo_Pagamento;
  }

  public double getVL_Variacao_Cambial_Ativa () {

    return VL_Variacao_Cambial_Ativa;
  }

  public double getVL_Variacao_Cambial_Passiva () {

    return VL_Variacao_Cambial_Passiva;
  }

  public double getVL_Desconto () {
    return VL_Desconto;
  }

  public double getVL_Devolucao_Nota_Fiscal () {
    return VL_Devolucao_Nota_Fiscal;
  }

  public double getVL_Desconto_Faturamento () {
    return VL_Desconto_Faturamento;
  }

  public double getVL_Liquido () {
    return VL_Liquido;
  }

  public double getVL_Juros_Reembolso () {
    return VL_Juros_Reembolso;
  }

  public double getVL_Juros () {
    return VL_Juros;
  }

  public double getVL_Pago () {
    return VL_Pago;
  }

  public void setDM_Remete_EDI (String DM_Remete_EDI) {
    this.DM_Remete_EDI = DM_Remete_EDI;
  }

  public String getDM_Remete_EDI () {
    return DM_Remete_EDI;
  }

  public String getDT_Vencimento_Final () {
    return DT_Vencimento_Final;
  }

  public String getDT_Vencimento_Inicial () {
    return DT_Vencimento_Inicial;
  }

  public void setDT_Vencimento_Final (String vencimento_Final) {
    DT_Vencimento_Final = vencimento_Final;
  }

  public void setDT_Vencimento_Inicial (String vencimento_Inicial) {
    DT_Vencimento_Inicial = vencimento_Inicial;
  }

  public String getTX_Observacao () {
    return TX_Observacao;
  }

  public void setTX_Observacao (String observacao) {
    TX_Observacao = observacao;
  }

  public String getNr_Remessa () {
    return Nr_Remessa;
  }

  public void setNr_Remessa (String nr_Remessa) {
    Nr_Remessa = nr_Remessa;
  }

  public String getOid_Nota_Fiscal () {
    return oid_Nota_Fiscal;
  }

  public String getNM_Situacao () {
    return NM_Situacao;
  }

  public String getOID_Conhecimento () {
    return OID_Conhecimento;
  }

  public double getVL_Total_Frete_Conhecimento () {
    return VL_Total_Frete_Conhecimento;
  }

  public String getNR_Conhecimento () {
    return NR_Conhecimento;
  }

  public double getVL_Imposto_Retido2 () {
    return VL_Imposto_Retido2;
  }

  public double getVL_Imposto_Retido1 () {
    return VL_Imposto_Retido1;
  }

  public String getDT_Credito () {
    return DT_Credito;
  }

  public void setOid_Nota_Fiscal (String oid_Nota_Fiscal) {
    this.oid_Nota_Fiscal = oid_Nota_Fiscal;
  }

  public void setNM_Situacao (String NM_Situacao) {
    this.NM_Situacao = NM_Situacao;
  }

  public void setOID_Conhecimento (String OID_Conhecimento) {
    this.OID_Conhecimento = OID_Conhecimento;
  }

  public void setVL_Total_Frete_Conhecimento (double VL_Total_Frete_Conhecimento) {
    this.VL_Total_Frete_Conhecimento = VL_Total_Frete_Conhecimento;
  }

  public void setNR_Conhecimento (String NR_Conhecimento) {
    this.NR_Conhecimento = NR_Conhecimento;
  }

  public void setVL_Imposto_Retido2 (double VL_Imposto_Retido2) {
    this.VL_Imposto_Retido2 = VL_Imposto_Retido2;
  }

  public void setVL_Imposto_Retido1 (double VL_Imposto_Retido1) {
    this.VL_Imposto_Retido1 = VL_Imposto_Retido1;
  }

  public void setDT_Credito (String DT_Credito) {
    this.DT_Credito = DT_Credito;
  }

  public int getNR_Nota_Fiscal () {
    return NR_Nota_Fiscal;
  }

  public void setNR_Nota_Fiscal (int nota_Fiscal) {
    NR_Nota_Fiscal = nota_Fiscal;
  }

  public String getDM_Situacao () {
    return DM_Situacao;
  }

  public void setDM_Situacao (String situacao) {
    DM_Situacao = situacao;
  }

  public String getNM_Razao_Social () {
    return NM_Razao_Social;
  }

  public void setNM_Razao_Social (String razao_Social) {
    NM_Razao_Social = razao_Social;
  }

  public String getDM_Tipo_Cobranca () {
    return DM_Tipo_Cobranca;
  }

  public void setDM_Tipo_Cobranca (String tipo_Cobranca) {
    DM_Tipo_Cobranca = tipo_Cobranca;
  }

  public String getNM_Praca_Pagamento () {
    return NM_Praca_Pagamento;
  }

  public void setNM_Praca_Pagamento (String praca_Pagamento) {
    NM_Praca_Pagamento = praca_Pagamento;
  }

  private void jbInit () throws Exception {
  }

  public double getVl_Comissao () {
    return vl_Comissao;
  }

  public Long getOid_Grupo_Economico () {
    return oid_Grupo_Economico;
  }

  public Double getVl_Desconto_Vencimento () {
    return vl_Desconto_Vencimento;
  }

  public String getDM_Lista () {
    return DM_Lista;
  }

  public double getVL_Titulo_Inicial () {
    return VL_Titulo_Inicial;
  }

  public double getVL_Titulo_Final () {
    return VL_Titulo_Final;
  }

  public void setVl_Comissao (double vl_Comissao) {
    this.vl_Comissao = vl_Comissao;
  }

  public void setOid_Grupo_Economico (Long oid_Grupo_Economico) {
    this.oid_Grupo_Economico = oid_Grupo_Economico;
  }

  public void setVl_Desconto_Vencimento (Double vl_Desconto_Vencimento) {
    this.vl_Desconto_Vencimento = vl_Desconto_Vencimento;
  }

  public void setDM_Lista (String DM_Lista) {
    this.DM_Lista = DM_Lista;
  }

  public void setVL_Titulo_Inicial (double VL_Titulo_Inicial) {
    this.VL_Titulo_Inicial = VL_Titulo_Inicial;
  }

  public void setVL_Titulo_Final (double VL_Titulo_Final) {
    this.VL_Titulo_Final = VL_Titulo_Final;
  }

public String getOid_Protocolo_Entrega() {
	return oid_Protocolo_Entrega;
}

public void setOid_Protocolo_Entrega(String oid_Protocolo_Entrega) {
	this.oid_Protocolo_Entrega = oid_Protocolo_Entrega;
}

public long getNR_Duplicata_Final() {
	return NR_Duplicata_Final;
}

public void setNR_Duplicata_Final(long duplicata_Final) {
	NR_Duplicata_Final = duplicata_Final;
}

public long getNR_Duplicata_Inicial() {
	return NR_Duplicata_Inicial;
}

public void setNR_Duplicata_Inicial(long duplicata_Inicial) {
	NR_Duplicata_Inicial = duplicata_Inicial;
}

public String getNM_Site() {
	return NM_Site;
}

public void setNM_Site(String site) {
	NM_Site = site;
}

public String getDt_Fluxo() {
	return dt_Fluxo;
}

public void setDt_Fluxo(String dt_Fluxo) {
	this.dt_Fluxo = dt_Fluxo;
}

public String getDt_Fluxo_Final() {
	return dt_Fluxo_Final;
}

public void setDt_Fluxo_Final(String dt_Fluxo_Final) {
	this.dt_Fluxo_Final = dt_Fluxo_Final;
}

public String getDt_Fluxo_Inicial() {
	return dt_Fluxo_Inicial;
}

public void setDt_Fluxo_Inicial(String dt_Fluxo_Inicial) {
	this.dt_Fluxo_Inicial = dt_Fluxo_Inicial;
}

public long getOid_Duplicata_Principal() {
	return oid_Duplicata_Principal;
}

public void setOid_Duplicata_Principal(long oid_Duplicata_Principal) {
	this.oid_Duplicata_Principal = oid_Duplicata_Principal;
}

public String getDM_Tipo_Inclusao() {
	return DM_Tipo_Inclusao;
}

public void setDM_Tipo_Inclusao(String tipo_Inclusao) {
	DM_Tipo_Inclusao = tipo_Inclusao;
}
}
