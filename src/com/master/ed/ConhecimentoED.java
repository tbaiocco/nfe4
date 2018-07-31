package com.master.ed;

import javax.servlet.http.*;

public class ConhecimentoED
    extends RelatorioBaseED {
	private String NM_Empresa;

	  private String oid_pessoa_unidade;

	  private String CD_Classe;
	  private String CD_Tarifa;
	  private double VL_Tarifa;

	private String DM_Exportacao;
	private String OID_Unidade_Operacional;

	private String NM_Procedencia_Veiculo;
	private String DM_Procedencia_Carreta;
	private String NM_Procedencia_Carreta;
	private String DT_Comprovante_Entrega;
	private String DT_Chegada_Cliente;
	private String HR_Chegada_Cliente;
	private String DT_Saida_Viagem;
	private String HR_Saida_Viagem;

	private String DM_Acerto;

	private String NM_Motorista;
	private String NM_Relatorio;
	private String NM_Mapa;
	 private double NR_Entrega_Pendente;
	 private double NR_Entrega_Justificado;
	 private double NR_Antecipada_Entrega;
	 private double NR_Atrasada_Entrega;
	 private double NR_Perf;
	 private String DT_Cancelamento;
	 private double VL_Total_Frete_Canc;
	 private double VL_ICMS_Canc;
	 private double VL_Base_Calc_Canc;
	 private double PE_Aliquota_ICMS_Canc;

	private long OID_Unidade_Atual;
    private long OID_Unidade_Destino;
    private double NR_Tempo_Cliente;
  private String OID_Conhecimento;
  private String OID_Novo_Conhecimento;
  private long NR_Conhecimento;
  private String NM_Serie;
  private String DM_Viagem;
  private String DM_Responsavel_Cobranca;
  private String DM_Tipo_Pagamento;
  private String DM_Tipo_Faturamento;
  private double PE_Aliquota_ICMS;
  private String TX_Observacao;
  private String DM_Isento_Seguro;
  private String NM_Atendente;
  private String NM_Natureza;
  private String NM_Especie;
  private String DT_Previsao_Entrega;
  private String HR_Previsao_Entrega;
  private String NM_Pessoa_Entrega;
  private String DT_Emissao;
  private String OID_Pessoa;
  private String OID_Pessoa_Destinatario;
  private String OID_Pessoa_Consignatario;
  private long OID_Modal;
  private long OID_Unidade;
  private String OID_Vendedor;
  private long OID_Cidade_Aereo_Origem;
  private long OID_Cidade_Aereo_Destino;
  private long OID_Cidade;
  private long OID_Cidade_Destino;
  private String OID_Tabela_Frete;
  private String OID_Pessoa_Pagador;
  private long OID_Produto;
  private long OID_Coleta;
  private String NM_Pessoa_Remetente;
  private String NM_Pessoa_Destinatario;
  private String NM_Pessoa_Consignatario;
  private String NM_Pessoa_Pagador;
  private String DM_Justifica_Atrazo;
  private String CD_Unidade;
  private double VL_Peso;
  private double VL_Peso_Cubado;
  private double VL_Nota_Fiscal;
  private double VL_Nota_Fiscal_Seguro;
  private double VL_Nota_Fiscal_Inicial;
  private double VL_Nota_Fiscal_Final;
  private double NR_Peso_Inicial;
  private double NR_Peso_Final;
  private double VL_Frete;
  private double VL_Frete_Redespacho;
  private double VL_ICMS;
  private long VL_Volumes;
  private String DM_Tipo_Conhecimento;
  private String DM_Impresso;
  private long OID_Estado_Origem;
  private long OID_Estado_Destino;
  private String CD_CFO;
  private long OID_Taxa;
  private double VL_FRETE_PESO;
  private double VL_FRETE_VALOR;
  private double VL_SEC_CAT;
  private double VL_PEDAGIO;
  private double VL_DESPACHO;
  private double VL_OUTROS1;
  private double VL_OUTROS2;
  private double VL_TOTAL_FRETE;
  private double VL_BASE_CALCULO_ICMS;
  private String NM_Endereco_Remetente;
  private String NM_Cidade_Remetente;
  private String CD_Estado_Remetente;
  private String NM_INSCRICAO_Remetente;
  private String NM_Endereco_Destinatario;
  private String NM_Cidade_Destinatario;
  private String CD_Estado_Destinatario;
  private String NM_INSCRICAO_Destinatario;
  private String NM_Endereco_Consignatario;
  private String NM_Cidade_Consignatario;
  private String CD_Estado_Consignatario;
  private String NM_INSCRICAO_Consignatario;
  private String NM_Cidade_CTRC_Origem;
  private String NM_Cidade_CTRC_Destino;
  private String CD_Estado_CTRC_Origem;
  private String CD_Estado_CTRC_Destino;
  private double PE_ALIQUOTA_ICMS;
  private String NM_Razao_Social_Pagador;
  private String NR_VOLUMES_Itens;
  private String NM_Produto;
  private String CD_Referencia;
  private String NR_NOTA_FISCAL;
  private String dt_emissao_nota;
  private String nr_peso;
  private String nr_peso_cubado;
  private String VL_Nota_Fiscal_CTRC;
  private String dt_Emissao_Inicial;
  private String dt_Emissao_Final;
  private String dt_Previsao_Entrega_Inicial;
  private String dt_Previsao_Entrega_Final;
  private String dm_Situacao_Cobranca;
  private String NM_Fantasia_Unidade;
  private String OID_Nota_Fiscal;
  private String DM_Tipo_Embarque;
  private String DM_Situacao;
  private String OID_Pessoa_Redespacho;
  private String NM_Cidade_Redespacho;
  private String NM_Endereco_Redespacho;
  private String NM_INSCRICAO_Redespacho;
  private String NM_Pessoa_Redespacho;
  private String NR_CNPJ_CPF_Redespacho;
  private long NR_Duplicata;
  private long NR_Frete_Terceiro;
  private long NR_Conhecimento_Final;
  private long NR_Conhecimento_Inicial;
  private String DM_Tipo_Produto;
  private String DM_Tipo_Tabela_Frete;
  private String DM_Tipo_Coleta;
  private String DM_Tipo_Entrega;
  private long OID_Empresa;
  private String OID_Pessoa_Entregadora;
  private String OID_Cliente_Redespacho;
  private long OID_Lote;
  private long OID_Participacao_Frete;
  private long OID_Redespacho;
  private long OID_Acerto;
  private long OID_Usuario;
  private String DM_Situacao_Entrega;
  private String DM_Frete_Recebido;
  private String DM_Frete_Emitido;
  private String DM_Tipo_Remessa;
  private String DM_Origem;
  private String DM_Destino;
  private long OID_Origem;
  private long OID_Destino;
  private double VL_Total_Custo;
  private String DM_Tipo_Transporte;
  private String OID_Veiculo;
  private String DM_Tipo_Comissao;
  private String DM_Relatorio;
  private String DM_Conhecimento_Varias_Notas_Fiscais;
  private String DM_Tipo_Impressao;
  //Atributo incluído para a impressão do relatório de ICMS
  private String DM_Imprime_Cancelados;
  private long NR_Pagina_Inicial;
  private double VL_Custo_Entrega;
  private String OID_Carreta;
  private String OID_Carreta2;
  private String DT_Entrega;
  private double NR_Peso;
  private double NR_Peso_Cubado;
  private double NR_Volumes;
  private double PE_Carga_Expressa;
  private String DM_Layout;
  private String NM_Lote_Faturamento;
  private String NR_Transacao_Pedagio;
  private String DM_Tipo_Documento;
  private String DM_Tipo_Cobranca;
  private String DM_Tipo_Desconto_Pedagio;
  private double PE_Comissao_Acerto;
  private String DM_Retira_Aeroporto;
  private String TX_Local_Retirada;
  private double VL_Taxa_Terrestre_Origem;
  private double VL_Taxa_Terrestre_Destino;
  private double VL_Taxa_Agente;
  private double VL_Taxa_Transportador;
  private String DM_Tipo_Tarifa;
  private String NR_Master;
  private int oid_Unidade_Agente;
  private String CD_Unidade_Agente;
  private String NM_Unidade_Agente;
  private String CD_CFO_Conhecimento_Editado;
  private boolean forcaCalculo;
  private double NR_Cubagem;
  private double VL_Total_Redespacho;
  private String DM_Calcular;
  private double VL_Base_Comissao_Acerto;
  private String NM_Situacao;
  private double VL_ISSQN;
  private long OID_Lote_Faturamento;
  private String TX_Ano;
  private String TX_Semestre;
  private String NM_Liberacao_Veiculo;
  private String DM_Monitoramento;
  private double PE_Taxa_Ajuste;
  private long NR_Minuta;
  private String DM_Procedencia_Veiculo;
  private long OID_Grupo_Economico;
  private String CD_Roteiro;
  private String NM_Roteiro;
  private long NR_ACT;
  private long NR_Documento;
  private String TX_Roteiro;
  private String NM_Tipo_Documento;
  private long OID_Centro_Custo;
  private String DT_Coleta;
  private String HR_Coleta;
  private String DT_Solicitado_Coleta;
  private String HR_Solicitado_Coleta;
  private String DT_Confirmacao_Coleta;
  private String HR_Confirmacao_Coleta;
  private String DT_Realizacao_Coleta;
  private String HR_Realizacao_Coleta;

  private double A1;
  private double A2;
  private double A3;
  private double D0;
  private double D1;
  private double D2;
  private double D3;
  private double D4;
  private double D5;
  private double D6;
  private double D7;
  private double D8;
  private double NR_Embarques;
  private long NR_Nota_Fiscal_Servico;
  private long NR_AWB;
  private String OID_Motorista;
  private String DM_Cia_Aerea;
  private String DM_Tipo_Embalagem;
  private double VL_Frete_Aereo;
  private double VL_Master;
  private String TX_Observacao_AWB;
  private String NM_Natureza_AWB;
  private double NR_Peso_AWB;
  private double NR_Volumes_AWB;
  private String NM_Especie_AWB;
  private String DM_Impressao_AWB;
  private String NR_Lote;
  private long OID_Tipo_Ocorrencia;
  private String HR_Entrega;
  private long NR_Dias_Entrega_Realizado;
  private long NR_Prazo_Entrega;
  private String OID_Comprovante_Entrega;
  private double VL_Tarifa_AWB;
  private long NR_Postagem;
  private String DM_Ordem;
  private String DM_Tipo_Postagem;
  private String DM_Tipo_Pagamento_AWB;
  private double VL_Nota_Fiscal_Declarado;
  private double VL_Frete_Valor_AWB;
  private String TX_Observacao_AWB2;
  private String TX_Observacao_AWB3;
  private String TX_Observacao_AWB4;
  private String TX_Observacao_AWB5;
  private String TX_Observacao_AWB6;
  private String NR_Controle_Entrega;

  private String CD_Rota_Entrega;
  private String NR_Remessa;
  private String File1;
  private String File2;
  private String File3;
  private String File4;

  private long oid_Programacao_Carga;
  private String NR_CEP_Remetente;
  private String NR_CEP_Destinatario;
  private String NM_Pessoa_Entregadora;
  private double VL_Frete_Entregadora;
  private String DT_Previsao_Entregadora;
  private String NR_Conhecimento_Entregadora;
  private String NR_Pedido;
  private String NM_Usuario;
  private String DT_Programada;
  private String CD_Modal;
  private String TX_Ocorrencias;
  private String NR_Conhecimento_Redespacho;

  private String OID_Rota_Entrega;

  private String ID_Cotacao;
  private String URL_Cotacao;

  private long oid_Ocorrencia_Conhecimento;

  private String NM_CH_NFe;
  private String NR_Romaneio;
  private long NR_PIN_Suframa;

  ///CTE
  private String OID_Pessoa_Expedidor;
  private String DM_CTe;
  private String NM_DACTE;
  private String NM_Usuario_Logado;
  private String NM_CH_CTe;
  private String DM_Status_CTe;
  private String NM_Protocolo_Envio_CTe;
  private String NM_Protocolo_Retorno_CTe;
  private String DT_Envio_CTe;
  private String DT_Recebimento_CTe;
  private String NM_Status_CTe;
  private String DM_Status_Cancelamento_CTe;
  private String DT_Envio_Cancelamento_CTe;
  private String DT_Recebimento_Cancelamento_CTe;
  private String NM_Status_Cancelamento_CTe;
  private String DM_Status_Anulacao_CTe;
  private String DT_Envio_Anulacao_CTe;
  private String DT_Recebimento_Anulacao_CTe;
  private String NM_Status_Anulacao_CTe;
  private String NM_Protocolo_Cancelamento_CTe;
  private String NM_Protocolo_Anulacao_CTe;

  private String OID_Conhecimento_Substituto;
  private String OID_Conhecimento_Original;

  //AREO
  private String CD_IATA_ORIGEM;
  private String CD_IATA_DESTINO;
  private String CD_IATA_PASSAGEM;
  private long OID_Parceiro;
  private String TX_Dimensoes;
  private String CD_Manuseio;
  private String CD_Carga_Especial;
  private String CD_ONU;

  public ConhecimentoED () {
    super ();
    try {
      jbInit ();
    }
    catch (Exception ex) {
      ex.printStackTrace ();
    }
  }

  public ConhecimentoED (HttpServletResponse response , String nomeRelatorio) {
    super (response , nomeRelatorio);
  }

  public void setOID_Conhecimento (String OID_Conhecimento) {
    this.OID_Conhecimento = OID_Conhecimento;
  }

  public String getOID_Conhecimento () {
    return OID_Conhecimento;
  }

  public void setNR_Conhecimento (long NR_Conhecimento) {
    this.NR_Conhecimento = NR_Conhecimento;
  }

  public long getNR_Conhecimento () {
    return NR_Conhecimento;
  }

  public void setNM_Serie (String NM_Serie) {
    this.NM_Serie = NM_Serie;
  }

  public String getNM_Serie () {
    return NM_Serie;
  }

  public void setDM_Responsavel_Cobranca (String DM_Responsavel_Cobranca) {
    this.DM_Responsavel_Cobranca = DM_Responsavel_Cobranca;
  }

  public String getDM_Responsavel_Cobranca () {
    return DM_Responsavel_Cobranca;
  }

  public void setDM_Tipo_Pagamento (String DM_Tipo_Pagamento) {
    this.DM_Tipo_Pagamento = DM_Tipo_Pagamento;
  }

  public String getDM_Tipo_Pagamento () {
    return DM_Tipo_Pagamento;
  }

  public void setDM_Tipo_Faturamento (String DM_Tipo_Faturamento) {
    this.DM_Tipo_Faturamento = DM_Tipo_Faturamento;
  }

  public String getDM_Tipo_Faturamento () {
    return DM_Tipo_Faturamento;
  }

  public void setPE_Aliquota_ICMS (double PE_Aliquota_ICMS) {
    this.PE_Aliquota_ICMS = PE_Aliquota_ICMS;
  }

  public double getPE_Aliquota_ICMS () {
    return PE_Aliquota_ICMS;
  }

  public void setTX_Observacao (String TX_Observacao) {
    this.TX_Observacao = TX_Observacao;
  }

  public String getTX_Observacao () {
    return TX_Observacao;
  }

  public void setDM_Isento_Seguro (String DM_Isento_Seguro) {
    this.DM_Isento_Seguro = DM_Isento_Seguro;
  }

  public String getDM_Isento_Seguro () {
    return DM_Isento_Seguro;
  }

  public void setNM_Atendente (String NM_Atendente) {
    this.NM_Atendente = NM_Atendente;
  }

  public String getNM_Atendente () {
    return NM_Atendente;
  }

  public void setNM_Natureza (String NM_Natureza) {
    this.NM_Natureza = NM_Natureza;
  }

  public String getNM_Natureza () {
    return NM_Natureza;
  }

  public void setDT_Previsao_Entrega (String DT_Previsao_Entrega) {
    this.DT_Previsao_Entrega = DT_Previsao_Entrega;
  }

  public String getDT_Previsao_Entrega () {
    return DT_Previsao_Entrega;
  }

  public void setHR_Previsao_Entrega (String HR_Previsao_Entrega) {
    this.HR_Previsao_Entrega = HR_Previsao_Entrega;
  }

  public String getHR_Previsao_Entrega () {
    return HR_Previsao_Entrega;
  }

  public void setNM_Pessoa_Entrega (String NM_Pessoa_Entrega) {
    this.NM_Pessoa_Entrega = NM_Pessoa_Entrega;
  }

  public String getNM_Pessoa_Entrega () {
    return NM_Pessoa_Entrega;
  }

  public void setDT_Emissao (String DT_Emissao) {
    this.DT_Emissao = DT_Emissao;
  }

  public String getDT_Emissao () {
    return DT_Emissao;
  }

  public void setOID_Pessoa (String OID_Pessoa) {
    this.OID_Pessoa = OID_Pessoa;
  }

  public String getOID_Pessoa () {
    return OID_Pessoa;
  }

  public void setOID_Pessoa_Destinatario (String OID_Pessoa_Destinatario) {
    this.OID_Pessoa_Destinatario = OID_Pessoa_Destinatario;
  }

  public String getOID_Pessoa_Destinatario () {
    return OID_Pessoa_Destinatario;
  }

  public void setOID_Pessoa_Consignatario (String OID_Pessoa_Consignatario) {
    this.OID_Pessoa_Consignatario = OID_Pessoa_Consignatario;
  }

  public String getOID_Pessoa_Consignatario () {
    return OID_Pessoa_Consignatario;
  }

  public void setOID_Modal (long OID_Modal) {
    this.OID_Modal = OID_Modal;
  }

  public long getOID_Modal () {
    return OID_Modal;
  }

  public void setOID_Unidade (long OID_Unidade) {
    this.OID_Unidade = OID_Unidade;
  }

  public long getOID_Unidade () {
    return OID_Unidade;
  }

  public void setOID_Vendedor (String OID_Vendedor) {
    this.OID_Vendedor = OID_Vendedor;
  }

  public String getOID_Vendedor () {
    return OID_Vendedor;
  }

  public void setOID_Cidade (long OID_Cidade) {
    this.OID_Cidade = OID_Cidade;
  }

  public long getOID_Cidade () {
    return OID_Cidade;
  }

  public void setOID_Cidade_Destino (long OID_Cidade_Destino) {
    this.OID_Cidade_Destino = OID_Cidade_Destino;
  }

  public long getOID_Cidade_Destino () {
    return OID_Cidade_Destino;
  }

  public void setOID_Tabela_Frete (String OID_Tabela_Frete) {
    this.OID_Tabela_Frete = OID_Tabela_Frete;
  }

  public String getOID_Tabela_Frete () {
    return OID_Tabela_Frete;
  }

  public void setOID_Pessoa_Pagador (String OID_Pessoa_Pagador) {
    this.OID_Pessoa_Pagador = OID_Pessoa_Pagador;
  }

  public String getOID_Pessoa_Pagador () {
    return OID_Pessoa_Pagador;
  }

  public void setOID_Produto (long OID_Produto) {
    this.OID_Produto = OID_Produto;
  }

  public long getOID_Produto () {
    return OID_Produto;
  }

  public void setOID_Coleta (long OID_Coleta) {
    this.OID_Coleta = OID_Coleta;
  }

  public long getOID_Coleta () {
    return OID_Coleta;
  }

  public void setNM_Pessoa_Remetente (String NM_Pessoa_Remetente) {
    this.NM_Pessoa_Remetente = NM_Pessoa_Remetente;
  }

  public String getNM_Pessoa_Remetente () {
    return NM_Pessoa_Remetente;
  }

  public void setNM_Pessoa_Destinatario (String NM_Pessoa_Destinatario) {
    this.NM_Pessoa_Destinatario = NM_Pessoa_Destinatario;
  }

  public String getNM_Pessoa_Destinatario () {
    return NM_Pessoa_Destinatario;
  }

  public void setNM_Pessoa_Consignatario (String NM_Pessoa_Consignatario) {
    this.NM_Pessoa_Consignatario = NM_Pessoa_Consignatario;
  }

  public String getNM_Pessoa_Consignatario () {
    return NM_Pessoa_Consignatario;
  }

  public void setCD_Unidade (String CD_Unidade) {
    this.CD_Unidade = CD_Unidade;
  }

  public String getCD_Unidade () {
    return CD_Unidade;
  }

  public void setVL_Peso (double VL_Peso) {
    this.VL_Peso = VL_Peso;
  }

  public double getVL_Peso () {
    return VL_Peso;
  }

  public void setVL_Peso_Cubado (double VL_Peso_Cubado) {
    this.VL_Peso_Cubado = VL_Peso_Cubado;
  }

  public double getVL_Peso_Cubado () {
    return VL_Peso_Cubado;
  }

  public void setVL_Nota_Fiscal (double VL_Nota_Fiscal) {
    this.VL_Nota_Fiscal = VL_Nota_Fiscal;
  }

  public double getVL_Nota_Fiscal () {
    return VL_Nota_Fiscal;
  }

  public void setVL_Frete (double VL_Frete) {
    this.VL_Frete = VL_Frete;
  }

  public double getVL_Frete () {
    return VL_Frete;
  }

  public void setVL_ICMS (double VL_ICMS) {
    this.VL_ICMS = VL_ICMS;
  }

  public double getVL_ICMS () {
    return VL_ICMS;
  }

  public void setVL_Volumes (long VL_Volumes) {
    this.VL_Volumes = VL_Volumes;
  }

  public long getVL_Volumes () {
    return VL_Volumes;
  }

  public void setDM_Tipo_Conhecimento (String DM_Tipo_Conhecimento) {
    this.DM_Tipo_Conhecimento = DM_Tipo_Conhecimento;
  }

  public String getDM_Tipo_Conhecimento () {
    return DM_Tipo_Conhecimento;
  }

  public void setDM_Impresso (String DM_Impresso) {
    this.DM_Impresso = DM_Impresso;
  }

  public String getDM_Impresso () {
    return DM_Impresso;
  }

  public void setOID_Estado_Origem (long OID_Estado_Origem) {
    this.OID_Estado_Origem = OID_Estado_Origem;
  }

  public long getOID_Estado_Origem () {
    return OID_Estado_Origem;
  }

  public void setOID_Estado_Destino (long OID_Estado_Destino) {
    this.OID_Estado_Destino = OID_Estado_Destino;
  }

  public long getOID_Estado_Destino () {
    return OID_Estado_Destino;
  }

  public void setCD_CFO (String CD_CFO) {
    this.CD_CFO = CD_CFO;
  }

  public String getCD_CFO () {
    return CD_CFO;
  }

  public void setOID_Taxa (long OID_Taxa) {
    this.OID_Taxa = OID_Taxa;
  }

  public long getOID_Taxa () {
    return OID_Taxa;
  }

  public void setVL_FRETE_PESO (double VL_FRETE_PESO) {
    this.VL_FRETE_PESO = VL_FRETE_PESO;
  }

  public double getVL_FRETE_PESO () {
    return VL_FRETE_PESO;
  }

  public void setVL_FRETE_VALOR (double VL_FRETE_VALOR) {
    this.VL_FRETE_VALOR = VL_FRETE_VALOR;
  }

  public double getVL_FRETE_VALOR () {
    return VL_FRETE_VALOR;
  }

  public void setVL_SEC_CAT (double VL_SEC_CAT) {
    this.VL_SEC_CAT = VL_SEC_CAT;
  }

  public double getVL_SEC_CAT () {
    return VL_SEC_CAT;
  }

  public void setVL_PEDAGIO (double VL_PEDAGIO) {
    this.VL_PEDAGIO = VL_PEDAGIO;
  }

  public double getVL_PEDAGIO () {
    return VL_PEDAGIO;
  }

  public void setVL_DESPACHO (double VL_DESPACHO) {
    this.VL_DESPACHO = VL_DESPACHO;
  }

  public double getVL_DESPACHO () {
    return VL_DESPACHO;
  }

  public void setVL_OUTROS1 (double VL_OUTROS1) {
    this.VL_OUTROS1 = VL_OUTROS1;
  }

  public double getVL_OUTROS1 () {
    return VL_OUTROS1;
  }

  public void setVL_OUTROS2 (double VL_OUTROS2) {
    this.VL_OUTROS2 = VL_OUTROS2;
  }

  public double getVL_OUTROS2 () {
    return VL_OUTROS2;
  }

  public void setVL_TOTAL_FRETE (double VL_TOTAL_FRETE) {
    this.VL_TOTAL_FRETE = VL_TOTAL_FRETE;
  }

  public double getVL_TOTAL_FRETE () {
    return VL_TOTAL_FRETE;
  }

  public void setVL_BASE_CALCULO_ICMS (double VL_BASE_CALCULO_ICMS) {
    this.VL_BASE_CALCULO_ICMS = VL_BASE_CALCULO_ICMS;
  }

  public double getVL_BASE_CALCULO_ICMS () {
    return VL_BASE_CALCULO_ICMS;
  }

  public void setNM_Endereco_Remetente (String NM_Endereco_Remetente) {
    this.NM_Endereco_Remetente = NM_Endereco_Remetente;
  }

  public String getNM_Endereco_Remetente () {
    return NM_Endereco_Remetente;
  }

  public void setNM_Cidade_Remetente (String NM_Cidade_Remetente) {
    this.NM_Cidade_Remetente = NM_Cidade_Remetente;
  }

  public String getNM_Cidade_Remetente () {
    return NM_Cidade_Remetente;
  }

  public void setCD_Estado_Remetente (String CD_Estado_Remetente) {
    this.CD_Estado_Remetente = CD_Estado_Remetente;
  }

  public String getCD_Estado_Remetente () {
    return CD_Estado_Remetente;
  }

  public void setNM_INSCRICAO_Remetente (String NM_INSCRICAO_Remetente) {
    this.NM_INSCRICAO_Remetente = NM_INSCRICAO_Remetente;
  }

  public String getNM_INSCRICAO_Remetente () {
    return NM_INSCRICAO_Remetente;
  }

  public void setNM_Endereco_Destinatario (String NM_Endereco_Destinatario) {
    this.NM_Endereco_Destinatario = NM_Endereco_Destinatario;
  }

  public String getNM_Endereco_Destinatario () {
    return NM_Endereco_Destinatario;
  }

  public void setNM_Cidade_Destinatario (String NM_Cidade_Destinatario) {
    this.NM_Cidade_Destinatario = NM_Cidade_Destinatario;
  }

  public String getNM_Cidade_Destinatario () {
    return NM_Cidade_Destinatario;
  }

  public void setCD_Estado_Destinatario (String CD_Estado_Destinatario) {
    this.CD_Estado_Destinatario = CD_Estado_Destinatario;
  }

  public String getCD_Estado_Destinatario () {
    return CD_Estado_Destinatario;
  }

  public void setNM_INSCRICAO_Destinatario (String NM_INSCRICAO_Destinatario) {
    this.NM_INSCRICAO_Destinatario = NM_INSCRICAO_Destinatario;
  }

  public String getNM_INSCRICAO_Destinatario () {
    return NM_INSCRICAO_Destinatario;
  }

  public void setNM_Endereco_Consignatario (String NM_Endereco_Consignatario) {
    this.NM_Endereco_Consignatario = NM_Endereco_Consignatario;
  }

  public String getNM_Endereco_Consignatario () {
    return NM_Endereco_Consignatario;
  }

  public void setNM_Cidade_Consignatario (String NM_Cidade_Consignatario) {
    this.NM_Cidade_Consignatario = NM_Cidade_Consignatario;
  }

  public String getNM_Cidade_Consignatario () {
    return NM_Cidade_Consignatario;
  }

  public void setCD_Estado_Consignatario (String CD_Estado_Consignatario) {
    this.CD_Estado_Consignatario = CD_Estado_Consignatario;
  }

  public String getCD_Estado_Consignatario () {
    return CD_Estado_Consignatario;
  }

  public void setNM_INSCRICAO_Consignatario (String NM_INSCRICAO_Consignatario) {
    this.NM_INSCRICAO_Consignatario = NM_INSCRICAO_Consignatario;
  }

  public String getNM_INSCRICAO_Consignatario () {
    return NM_INSCRICAO_Consignatario;
  }

  public void setNM_Cidade_CTRC_Origem (String NM_Cidade_CTRC_Origem) {
    this.NM_Cidade_CTRC_Origem = NM_Cidade_CTRC_Origem;
  }

  public String getNM_Cidade_CTRC_Origem () {
    return NM_Cidade_CTRC_Origem;
  }

  public void setNM_Cidade_CTRC_Destino (String NM_Cidade_CTRC_Destino) {
    this.NM_Cidade_CTRC_Destino = NM_Cidade_CTRC_Destino;
  }

  public String getNM_Cidade_CTRC_Destino () {
    return NM_Cidade_CTRC_Destino;
  }

  public void setCD_Estado_CTRC_Origem (String CD_Estado_CTRC_Origem) {
    this.CD_Estado_CTRC_Origem = CD_Estado_CTRC_Origem;
  }

  public String getCD_Estado_CTRC_Origem () {
    return CD_Estado_CTRC_Origem;
  }

  public void setCD_Estado_CTRC_Destino (String CD_Estado_CTRC_Destino) {
    this.CD_Estado_CTRC_Destino = CD_Estado_CTRC_Destino;
  }

  public String getCD_Estado_CTRC_Destino () {
    return CD_Estado_CTRC_Destino;
  }

  public void setPE_ALIQUOTA_ICMS (double PE_ALIQUOTA_ICMS) {
    this.PE_ALIQUOTA_ICMS = PE_ALIQUOTA_ICMS;
  }

  public double getPE_ALIQUOTA_ICMS () {
    return PE_ALIQUOTA_ICMS;
  }

  public void setNM_Razao_Social_Pagador (String NM_Razao_Social_Pagador) {
    this.NM_Razao_Social_Pagador = NM_Razao_Social_Pagador;
  }

  public String getNM_Razao_Social_Pagador () {
    return NM_Razao_Social_Pagador;
  }

  public void setNR_VOLUMES_Itens (String NR_VOLUMES_Itens) {
    this.NR_VOLUMES_Itens = NR_VOLUMES_Itens;
  }

  public String getNR_VOLUMES_Itens () {
    return NR_VOLUMES_Itens;
  }

  public void setNM_Produto (String NM_Produto) {
    this.NM_Produto = NM_Produto;
  }

  public String getNM_Produto () {
    return NM_Produto;
  }

  public void setCD_Referencia (String CD_Referencia) {
    this.CD_Referencia = CD_Referencia;
  }

  public String getCD_Referencia () {
    return CD_Referencia;
  }

  public void setNR_NOTA_FISCAL (String NR_NOTA_FISCAL) {
    this.NR_NOTA_FISCAL = NR_NOTA_FISCAL;
  }

  public String getNR_NOTA_FISCAL () {
    return NR_NOTA_FISCAL;
  }

  public void setDt_emissao_nota (String dt_emissao_nota) {
    this.dt_emissao_nota = dt_emissao_nota;
  }

  public String getDt_emissao_nota () {
    return dt_emissao_nota;
  }

  public void setNr_peso (String nr_peso) {
    this.nr_peso = nr_peso;
  }

  public String getNr_peso () {
    return nr_peso;
  }

  public void setNr_peso_cubado (String nr_peso_cubado) {
    this.nr_peso_cubado = nr_peso_cubado;
  }

  public String getNr_peso_cubado () {
    return nr_peso_cubado;
  }

  public void setVL_Nota_Fiscal_CTRC (String VL_Nota_Fiscal_CTRC) {
    this.VL_Nota_Fiscal_CTRC = VL_Nota_Fiscal_CTRC;
  }

  public String getVL_Nota_Fiscal_CTRC () {
    return VL_Nota_Fiscal_CTRC;
  }

  public void setDt_Emissao_Inicial (String dt_Emissao_Inicial) {
    this.dt_Emissao_Inicial = dt_Emissao_Inicial;
  }

  public String getDt_Emissao_Inicial () {
    return dt_Emissao_Inicial;
  }

  public void setDt_Emissao_Final (String dt_Emissao_Final) {
    this.dt_Emissao_Final = dt_Emissao_Final;
  }

  public String getDt_Emissao_Final () {
    return dt_Emissao_Final;
  }

  public void setDm_Situacao_Cobranca (String dm_Situacao_Cobranca) {
    this.dm_Situacao_Cobranca = dm_Situacao_Cobranca;
  }

  public void setVL_Nota_Fiscal_Inicial (double VL_Nota_Fiscal_Inicial) {
    this.VL_Nota_Fiscal_Inicial = VL_Nota_Fiscal_Inicial;
  }

  public void setVL_Nota_Fiscal_Final (double VL_Nota_Fiscal_Final) {
    this.VL_Nota_Fiscal_Final = VL_Nota_Fiscal_Final;
  }

  public void setDM_Tipo_Cobranca (String DM_Tipo_Cobranca) {
    this.DM_Tipo_Cobranca = DM_Tipo_Cobranca;
  }

  public void setDM_Tipo_Documento (String DM_Tipo_Documento) {
    this.DM_Tipo_Documento = DM_Tipo_Documento;
  }

  public String getDm_Situacao_Cobranca () {
    return dm_Situacao_Cobranca;
  }

  public double getVL_Nota_Fiscal_Inicial () {
    return VL_Nota_Fiscal_Inicial;
  }

  public double getVL_Nota_Fiscal_Final () {
    return VL_Nota_Fiscal_Final;
  }

  public String getDM_Tipo_Cobranca () {
    return DM_Tipo_Cobranca;
  }

  public String getDM_Tipo_Documento () {
    return DM_Tipo_Documento;
  }

  public void setNM_Fantasia_Unidade (String NM_Fantasia_Unidade) {
    this.NM_Fantasia_Unidade = NM_Fantasia_Unidade;
  }

  public String getNM_Fantasia_Unidade () {
    return NM_Fantasia_Unidade;
  }

  public void setOID_Nota_Fiscal (String OID_Nota_Fiscal) {
    this.OID_Nota_Fiscal = OID_Nota_Fiscal;
  }

  public String getOID_Nota_Fiscal () {
    return OID_Nota_Fiscal;
  }

  public void setDM_Tipo_Embarque (String DM_Tipo_Embarque) {
    this.DM_Tipo_Embarque = DM_Tipo_Embarque;
  }

  public String getDM_Tipo_Embarque () {
    return DM_Tipo_Embarque;
  }

  public void setDM_Situacao (String DM_Situacao) {
    this.DM_Situacao = DM_Situacao;
  }

  public String getDM_Situacao () {
    return DM_Situacao;
  }

  public void setOID_Pessoa_Redespacho (String OID_Pessoa_Redespacho) {
    this.OID_Pessoa_Redespacho = OID_Pessoa_Redespacho;
  }

  public String getOID_Pessoa_Redespacho () {
    return OID_Pessoa_Redespacho;
  }

  public void setNM_Cidade_Redespacho (String NM_Cidade_Redespacho) {
    this.NM_Cidade_Redespacho = NM_Cidade_Redespacho;
  }

  public String getNM_Cidade_Redespacho () {
    return NM_Cidade_Redespacho;
  }

  public void setNM_Endereco_Redespacho (String NM_Endereco_Redespacho) {
    this.NM_Endereco_Redespacho = NM_Endereco_Redespacho;
  }

  public String getNM_Endereco_Redespacho () {
    return NM_Endereco_Redespacho;
  }

  public void setNM_INSCRICAO_Redespacho (String NM_INSCRICAO_Redespacho) {
    this.NM_INSCRICAO_Redespacho = NM_INSCRICAO_Redespacho;
  }

  public String getNM_INSCRICAO_Redespacho () {
    return NM_INSCRICAO_Redespacho;
  }

  public void setNM_Pessoa_Redespacho (String NM_Pessoa_Redespacho) {
    this.NM_Pessoa_Redespacho = NM_Pessoa_Redespacho;
  }

  public String getNM_Pessoa_Redespacho () {
    return NM_Pessoa_Redespacho;
  }

  public void setNR_CNPJ_CPF_Redespacho (String NR_CNPJ_CPF_Redespacho) {
    this.NR_CNPJ_CPF_Redespacho = NR_CNPJ_CPF_Redespacho;
  }

  public String getNR_CNPJ_CPF_Redespacho () {
    return NR_CNPJ_CPF_Redespacho;
  }

  public void setNR_Duplicata (long NR_Duplicata) {
    this.NR_Duplicata = NR_Duplicata;
  }

  public long getNR_Duplicata () {
    return NR_Duplicata;
  }

  public void setNR_Conhecimento_Inicial (long NR_Conhecimento_Inicial) {
    this.NR_Conhecimento_Inicial = NR_Conhecimento_Inicial;
  }

  public long getNR_Conhecimento_Inicial () {
    return NR_Conhecimento_Inicial;
  }

  public void setNR_Conhecimento_Final (long NR_Conhecimento_Final) {
    this.NR_Conhecimento_Final = NR_Conhecimento_Final;
  }

  public long getNR_Conhecimento_Final () {
    return NR_Conhecimento_Final;
  }

  public void setDM_Tipo_Produto (String DM_Tipo_Produto) {
    this.DM_Tipo_Produto = DM_Tipo_Produto;
  }

  public String getDM_Tipo_Produto () {
    return DM_Tipo_Produto;
  }

  public String getDM_Tipo_Coleta () {
    return DM_Tipo_Coleta;
  }

  public String getDM_Tipo_Entrega () {
    return DM_Tipo_Entrega;
  }

  public String getDM_Tipo_Tabela_Frete () {
    return DM_Tipo_Tabela_Frete;
  }

  public void setDM_Tipo_Coleta (String DM_Tipo_Coleta) {
    this.DM_Tipo_Coleta = DM_Tipo_Coleta;
  }

  public void setDM_Tipo_Entrega (String DM_Tipo_Entrega) {
    this.DM_Tipo_Entrega = DM_Tipo_Entrega;
  }

  public void setDM_Tipo_Tabela_Frete (String DM_Tipo_Tabela_Frete) {
    this.DM_Tipo_Tabela_Frete = DM_Tipo_Tabela_Frete;
  }

  public long getOID_Empresa () {
    return OID_Empresa;
  }

  public void setOID_Empresa (long OID_Empresa) {
    this.OID_Empresa = OID_Empresa;
  }

  public void setOID_Pessoa_Entregadora (String OID_Pessoa_Entregadora) {
    this.OID_Pessoa_Entregadora = OID_Pessoa_Entregadora;
  }

  public String getOID_Pessoa_Entregadora () {
    return OID_Pessoa_Entregadora;
  }

  public void setOID_Lote (long OID_Lote) {
    this.OID_Lote = OID_Lote;
  }

  public long getOID_Lote () {
    return OID_Lote;
  }

  public void setOID_Acerto (long OID_Acerto) {
    this.OID_Acerto = OID_Acerto;
  }

  public long getOID_Acerto () {
    return OID_Acerto;
  }

  public void setOID_Usuario (long OID_Usuario) {
    this.OID_Usuario = OID_Usuario;
  }

  public long getOID_Usuario () {
    return OID_Usuario;
  }

  public void setDM_Situacao_Entrega (String DM_Situacao_Entrega) {
    this.DM_Situacao_Entrega = DM_Situacao_Entrega;
  }

  public String getDM_Situacao_Entrega () {
    return DM_Situacao_Entrega;
  }

  public void setDM_Frete_Recebido (String DM_Frete_Recebido) {
    this.DM_Frete_Recebido = DM_Frete_Recebido;
  }

  public String getDM_Frete_Recebido () {
    return DM_Frete_Recebido;
  }

  public void setDM_Frete_Emitido (String DM_Frete_Emitido) {
    this.DM_Frete_Emitido = DM_Frete_Emitido;
  }

  public String getDM_Frete_Emitido () {
    return DM_Frete_Emitido;
  }

  public void setDM_Tipo_Remessa (String DM_Tipo_Remessa) {
    this.DM_Tipo_Remessa = DM_Tipo_Remessa;
  }

  public String getDM_Tipo_Remessa () {
    return DM_Tipo_Remessa;
  }

  public void setDM_Origem (String DM_Origem) {
    this.DM_Origem = DM_Origem;
  }

  public String getDM_Origem () {
    return DM_Origem;
  }

  public void setDM_Destino (String DM_Destino) {
    this.DM_Destino = DM_Destino;
  }

  public String getDM_Destino () {
    return DM_Destino;
  }

  public void setOID_Origem (long OID_Origem) {
    this.OID_Origem = OID_Origem;
  }

  public long getOID_Origem () {
    return OID_Origem;
  }

  public void setOID_Destino (long OID_Destino) {
    this.OID_Destino = OID_Destino;
  }

  public long getOID_Destino () {
    return OID_Destino;
  }

  public void setVL_Total_Custo (double VL_Total_Custo) {
    this.VL_Total_Custo = VL_Total_Custo;
  }

  public double getVL_Total_Custo () {
    return VL_Total_Custo;
  }

  public void setDM_Tipo_Transporte (String DM_Tipo_Transporte) {
    this.DM_Tipo_Transporte = DM_Tipo_Transporte;
  }

  public String getDM_Tipo_Transporte () {
    return DM_Tipo_Transporte;
  }

  public void setOID_Veiculo (String OID_Veiculo) {
    this.OID_Veiculo = OID_Veiculo;
  }

  public String getOID_Veiculo () {
    return OID_Veiculo;
  }

  public void setDM_Tipo_Comissao (String DM_Tipo_Comissao) {
    this.DM_Tipo_Comissao = DM_Tipo_Comissao;
  }

  public String getDM_Tipo_Comissao () {
    return DM_Tipo_Comissao;
  }

  public void setDM_Relatorio (String DM_Relatorio) {
    this.DM_Relatorio = DM_Relatorio;
  }

  public String getDM_Relatorio () {
    return DM_Relatorio;
  }

  public void setDM_Conhecimento_Varias_Notas_Fiscais (String DM_Conhecimento_Varias_Notas_Fiscais) {
    this.DM_Conhecimento_Varias_Notas_Fiscais = DM_Conhecimento_Varias_Notas_Fiscais;
  }

  public String getDM_Conhecimento_Varias_Notas_Fiscais () {
    return DM_Conhecimento_Varias_Notas_Fiscais;
  }

  public void setDM_Tipo_Impressao (String DM_Tipo_Impressao) {
    this.DM_Tipo_Impressao = DM_Tipo_Impressao;
  }

  public String getDM_Tipo_Impressao () {
    return DM_Tipo_Impressao;
  }

  public void setNR_Pagina_Inicial (long NR_Pagina_Inicial) {
    this.NR_Pagina_Inicial = NR_Pagina_Inicial;
  }

  public long getNR_Pagina_Inicial () {
    return NR_Pagina_Inicial;
  }

  public void setVL_Custo_Entrega (double VL_Custo_Entrega) {
    this.VL_Custo_Entrega = VL_Custo_Entrega;
  }

  public double getVL_Custo_Entrega () {
    return VL_Custo_Entrega;
  }

  public void setOID_Carreta (String OID_Carreta) {
    this.OID_Carreta = OID_Carreta;
  }

  public String getOID_Carreta () {
    return OID_Carreta;
  }

  public void setDT_Entrega (String DT_Entrega) {
    this.DT_Entrega = DT_Entrega;
  }

  public String getDT_Entrega () {
    return DT_Entrega;
  }

  public void setNR_Peso (double NR_Peso) {
    this.NR_Peso = NR_Peso;
  }

  public double getNR_Peso () {
    return NR_Peso;
  }

  public void setNR_Peso_Cubado (double NR_Peso_Cubado) {
    this.NR_Peso_Cubado = NR_Peso_Cubado;
  }

  public double getNR_Peso_Cubado () {
    return NR_Peso_Cubado;
  }

  public void setNR_Volumes (double NR_Volumes) {
    this.NR_Volumes = NR_Volumes;
  }

  public double getNR_Volumes () {
    return NR_Volumes;
  }

  public void setPE_Carga_Expressa (double PE_Carga_Expressa) {
    this.PE_Carga_Expressa = PE_Carga_Expressa;
  }

  public double getPE_Carga_Expressa () {
    return PE_Carga_Expressa;
  }

  public void setDM_Imprime_Cancelados (String DM_Imprime_Cancelados) {
    this.DM_Imprime_Cancelados = DM_Imprime_Cancelados;
  }

  public String getDM_Imprime_Cancelados () {
    return DM_Imprime_Cancelados;
  }

  public void setDM_Layout (String DM_Layout) {
    this.DM_Layout = DM_Layout;
  }

  public String getDM_Layout () {
    return DM_Layout;
  }

  public String getOID_Carreta2 () {
    return OID_Carreta2;
  }

  public void setOID_Carreta2 (String carreta2) {
    OID_Carreta2 = carreta2;
  }

  public String getNM_Lote_Faturamento () {
    return NM_Lote_Faturamento;
  }

  public void setNM_Lote_Faturamento (String NM_Lote_Faturamento) {
    this.NM_Lote_Faturamento = NM_Lote_Faturamento;
  }

  public String getNR_Transacao_Pedagio () {
    return NR_Transacao_Pedagio;
  }

  public void setNR_Transacao_Pedagio (String NR_Transacao_Pedagio) {
    this.NR_Transacao_Pedagio = NR_Transacao_Pedagio;
  }

  public String getDM_Tipo_Desconto_Pedagio () {
    return this.DM_Tipo_Desconto_Pedagio;
  }

  public void setDM_Tipo_Desconto_Pedagio (String tipo_Desconto_Pedagio) {
    this.DM_Tipo_Desconto_Pedagio = tipo_Desconto_Pedagio;
  }

  public double getPE_Comissao_Acerto () {
    return this.PE_Comissao_Acerto;
  }

  public void setPE_Comissao_Acerto (double comissao_Acerto) {
    this.PE_Comissao_Acerto = comissao_Acerto;
  }

  public String getNM_Pessoa_Pagador () {
    return this.NM_Pessoa_Pagador;
  }

  public void setNM_Pessoa_Pagador (String pessoa_Pagador) {
    this.NM_Pessoa_Pagador = pessoa_Pagador;
  }

  public String getDM_Retira_Aeroporto () {
    return this.DM_Retira_Aeroporto;
  }

  public void setDM_Retira_Aeroporto (String retira_Aeroporto) {
    this.DM_Retira_Aeroporto = retira_Aeroporto;
  }

  public String getTX_Local_Retirada () {
    return this.TX_Local_Retirada;
  }

  public void setTX_Local_Retirada (String local_Retirada) {
    this.TX_Local_Retirada = local_Retirada;
  }

  public double getVL_Taxa_Agente () {
    return this.VL_Taxa_Agente;
  }

  public void setVL_Taxa_Agente (double taxa_Agente) {
    this.VL_Taxa_Agente = taxa_Agente;
  }

  public double getVL_Taxa_Terrestre_Destino () {
    return this.VL_Taxa_Terrestre_Destino;
  }

  public void setVL_Taxa_Terrestre_Destino (double taxa_Terrestre_Destino) {
    this.VL_Taxa_Terrestre_Destino = taxa_Terrestre_Destino;
  }

  public double getVL_Taxa_Terrestre_Origem () {
    return this.VL_Taxa_Terrestre_Origem;
  }

  public void setVL_Taxa_Terrestre_Origem (double taxa_Terrestre_Origem) {
    this.VL_Taxa_Terrestre_Origem = taxa_Terrestre_Origem;
  }

  public double getVL_Taxa_Transportador () {
    return this.VL_Taxa_Transportador;
  }

  public void setVL_Taxa_Transportador (double taxa_Transportador) {
    this.VL_Taxa_Transportador = taxa_Transportador;
  }

  public String getDM_Tipo_Tarifa () {
    return this.DM_Tipo_Tarifa;
  }

  public void setDM_Tipo_Tarifa (String tipo_Tarifa) {
    this.DM_Tipo_Tarifa = tipo_Tarifa;
  }

  public String getNR_Master () {
    return this.NR_Master;
  }

  public void setNR_Master (String master) {
    this.NR_Master = master;
  }

  public int getOid_Unidade_Agente () {
    return this.oid_Unidade_Agente;
  }

  public void setOid_Unidade_Agente (int oid_Unidade_Agente) {
    this.oid_Unidade_Agente = oid_Unidade_Agente;
  }

  public String getCD_Unidade_Agente () {
    return this.CD_Unidade_Agente;
  }

  public void setCD_Unidade_Agente (String unidade_Agente) {
    this.CD_Unidade_Agente = unidade_Agente;
  }

  public String getNM_Unidade_Agente () {
    return this.NM_Unidade_Agente;
  }

  public void setNM_Unidade_Agente (String unidade_Agente) {
    this.NM_Unidade_Agente = unidade_Agente;
  }

  public String getCD_CFO_Conhecimento_Editado () {
    return this.CD_CFO_Conhecimento_Editado;
  }

  public void setCD_CFO_Conhecimento_Editado (String conhecimento_Editado) {
    this.CD_CFO_Conhecimento_Editado = conhecimento_Editado;
  }

  public boolean isForcaCalculo () {
    return forcaCalculo;
  }

  public String getNR_Remessa () {
    return NR_Remessa;
  }

  public String getNR_Controle_Entrega () {
    return NR_Controle_Entrega;
  }

  public String getTX_Observacao_AWB6 () {
    return TX_Observacao_AWB6;
  }

  public String getTX_Observacao_AWB5 () {
    return TX_Observacao_AWB5;
  }

  public String getTX_Observacao_AWB4 () {
    return TX_Observacao_AWB4;
  }

  public String getTX_Observacao_AWB3 () {
    return TX_Observacao_AWB3;
  }

  public String getTX_Observacao_AWB2 () {
    return TX_Observacao_AWB2;
  }

  public double getVL_Frete_Valor_AWB () {
    return VL_Frete_Valor_AWB;
  }

  public double getVL_Nota_Fiscal_Declarado () {
    return VL_Nota_Fiscal_Declarado;
  }

  public String getDM_Tipo_Pagamento_AWB () {
    return DM_Tipo_Pagamento_AWB;
  }

  public String getDM_Tipo_Postagem () {
    return DM_Tipo_Postagem;
  }

  public String getDM_Ordem () {
    return DM_Ordem;
  }

  public long getNR_Postagem () {
    return NR_Postagem;
  }

  public double getNR_Peso_Inicial () {
    return NR_Peso_Inicial;
  }

  public double getNR_Peso_Final () {
    return NR_Peso_Final;
  }

  public double getVL_Tarifa_AWB () {
    return VL_Tarifa_AWB;
  }

  public String getOID_Comprovante_Entrega () {
    return OID_Comprovante_Entrega;
  }

  public long getNR_Dias_Entrega_Realizado () {
    return NR_Dias_Entrega_Realizado;
  }

  public String getHR_Entrega () {
    return HR_Entrega;
  }

  public long getOID_Tipo_Ocorrencia () {
    return OID_Tipo_Ocorrencia;
  }

  public String getNR_Lote () {
    return NR_Lote;
  }

  public String getDM_Impressao_AWB () {
    return DM_Impressao_AWB;
  }

  public String getNM_Especie_AWB () {
    return NM_Especie_AWB;
  }

  public double getNR_Volumes_AWB () {
    return NR_Volumes_AWB;
  }

  public double getNR_Peso_AWB () {
    return NR_Peso_AWB;
  }

  public String getNM_Natureza_AWB () {
    return NM_Natureza_AWB;
  }

  public String getTX_Observacao_AWB () {
    return TX_Observacao_AWB;
  }

  public long getOID_Cidade_Aereo_Origem () {
    return OID_Cidade_Aereo_Origem;
  }

  public long getOID_Cidade_Aereo_Destino () {
    return OID_Cidade_Aereo_Destino;
  }

  public double getVL_Master () {
    return VL_Master;
  }

  public double getVL_Frete_Aereo () {
    return VL_Frete_Aereo;
  }

  public String getDM_Tipo_Embalagem () {
    return DM_Tipo_Embalagem;
  }

  public String getDM_Cia_Aerea () {
    return DM_Cia_Aerea;
  }

  public String getOID_Motorista () {
    return OID_Motorista;
  }

  public long getNR_AWB () {
    return NR_AWB;
  }

  public long getNR_Nota_Fiscal_Servico () {
    return NR_Nota_Fiscal_Servico;
  }

  public double getNR_Embarques () {
    return NR_Embarques;
  }

  public double getD8 () {
    return D8;
  }

  public double getD7 () {
    return D7;
  }

  public double getD6 () {
    return D6;
  }

  public double getD5 () {
    return D5;
  }

  public double getD4 () {
    return D4;
  }

  public double getD3 () {
    return D3;
  }

  public double getD2 () {
    return D2;
  }

  public double getD1 () {
    return D1;
  }

  public double getD0 () {
    return D0;
  }

  public String getDT_Coleta () {
    return DT_Coleta;
  }

  public long getOID_Centro_Custo () {

    return OID_Centro_Custo;
  }

  public String getNM_Tipo_Documento () {
    return NM_Tipo_Documento;
  }

  public String getTX_Roteiro () {
    return TX_Roteiro;
  }

  public long getNR_Documento () {
    return NR_Documento;
  }

  public long getNR_ACT () {
    return NR_ACT;
  }

  public String getNM_Roteiro () {
    return NM_Roteiro;
  }

  public String getCD_Roteiro () {
    return CD_Roteiro;
  }

  public long getOID_Grupo_Economico () {
    return OID_Grupo_Economico;
  }

  public String getDM_Procedencia_Veiculo () {
    return DM_Procedencia_Veiculo;
  }

  public long getNR_Minuta () {
    return NR_Minuta;
  }

  public double getPE_Taxa_Ajuste () {
    return PE_Taxa_Ajuste;
  }

  public String getDM_Monitoramento () {
    return DM_Monitoramento;
  }

  public String getNM_Liberacao_Veiculo () {
    return NM_Liberacao_Veiculo;
  }

  public long getOID_Lote_Faturamento () {
    return OID_Lote_Faturamento;
  }

  public double getVL_ISSQN () {
    return VL_ISSQN;
  }

  public String getNM_Situacao () {
    return NM_Situacao;
  }

  public double getVL_Base_Comissao_Acerto () {
    return VL_Base_Comissao_Acerto;
  }

  public String getDM_Calcular () {

    return DM_Calcular;
  }

  public double getVL_Total_Redespacho () {
    return VL_Total_Redespacho;
  }

  public double getNR_Cubagem () {
    return NR_Cubagem;
  }

  public void setForcaCalculo (boolean forcaCalculo) {
    this.forcaCalculo = forcaCalculo;
  }

  public void setNR_Remessa (String NR_Remessa) {
    this.NR_Remessa = NR_Remessa;
  }

  public void setNR_Controle_Entrega (String NR_Controle_Entrega) {
    this.NR_Controle_Entrega = NR_Controle_Entrega;
  }

  public void setTX_Observacao_AWB6 (String TX_Observacao_AWB6) {
    this.TX_Observacao_AWB6 = TX_Observacao_AWB6;
  }

  public void setTX_Observacao_AWB5 (String TX_Observacao_AWB5) {
    this.TX_Observacao_AWB5 = TX_Observacao_AWB5;
  }

  public void setTX_Observacao_AWB4 (String TX_Observacao_AWB4) {
    this.TX_Observacao_AWB4 = TX_Observacao_AWB4;
  }

  public void setTX_Observacao_AWB3 (String TX_Observacao_AWB3) {
    this.TX_Observacao_AWB3 = TX_Observacao_AWB3;
  }

  public void setTX_Observacao_AWB2 (String TX_Observacao_AWB2) {
    this.TX_Observacao_AWB2 = TX_Observacao_AWB2;
  }

  public void setVL_Frete_Valor_AWB (double VL_Frete_Valor_AWB) {
    this.VL_Frete_Valor_AWB = VL_Frete_Valor_AWB;
  }

  public void setVL_Nota_Fiscal_Declarado (double VL_Nota_Fiscal_Declarado) {
    this.VL_Nota_Fiscal_Declarado = VL_Nota_Fiscal_Declarado;
  }

  public void setDM_Tipo_Pagamento_AWB (String DM_Tipo_Pagamento_AWB) {
    this.DM_Tipo_Pagamento_AWB = DM_Tipo_Pagamento_AWB;
  }

  public void setDM_Tipo_Postagem (String DM_Tipo_Postagem) {
    this.DM_Tipo_Postagem = DM_Tipo_Postagem;
  }

  public void setDM_Ordem (String DM_Ordem) {
    this.DM_Ordem = DM_Ordem;
  }

  public void setNR_Postagem (long NR_Postagem) {
    this.NR_Postagem = NR_Postagem;
  }

  public void setNR_Peso_Inicial (double NR_Peso_Inicial) {
    this.NR_Peso_Inicial = NR_Peso_Inicial;
  }

  public void setNR_Peso_Final (double NR_Peso_Final) {
    this.NR_Peso_Final = NR_Peso_Final;
  }

  public void setVL_Tarifa_AWB (double VL_Tarifa_AWB) {
    this.VL_Tarifa_AWB = VL_Tarifa_AWB;
  }

  public void setOID_Comprovante_Entrega (String OID_Comprovante_Entrega) {
    this.OID_Comprovante_Entrega = OID_Comprovante_Entrega;
  }

  public void setNR_Dias_Entrega_Realizado (long NR_Dias_Entrega_Realizado) {
    this.NR_Dias_Entrega_Realizado = NR_Dias_Entrega_Realizado;
  }

  public void setHR_Entrega (String HR_Entrega) {
    this.HR_Entrega = HR_Entrega;
  }

  public void setOID_Tipo_Ocorrencia (long OID_Tipo_Ocorrencia) {
    this.OID_Tipo_Ocorrencia = OID_Tipo_Ocorrencia;
  }

  public void setNR_Lote (String NR_Lote) {
    this.NR_Lote = NR_Lote;
  }

  public void setDM_Impressao_AWB (String DM_Impressao_AWB) {
    this.DM_Impressao_AWB = DM_Impressao_AWB;
  }

  public void setNM_Especie_AWB (String NM_Especie_AWB) {
    this.NM_Especie_AWB = NM_Especie_AWB;
  }

  public void setNR_Volumes_AWB (double NR_Volumes_AWB) {
    this.NR_Volumes_AWB = NR_Volumes_AWB;
  }

  public void setNR_Peso_AWB (double NR_Peso_AWB) {
    this.NR_Peso_AWB = NR_Peso_AWB;
  }

  public void setNM_Natureza_AWB (String NM_Natureza_AWB) {
    this.NM_Natureza_AWB = NM_Natureza_AWB;
  }

  public void setTX_Observacao_AWB (String TX_Observacao_AWB) {
    this.TX_Observacao_AWB = TX_Observacao_AWB;
  }

  public void setOID_Cidade_Aereo_Origem (long OID_Cidade_Aereo_Origem) {
    this.OID_Cidade_Aereo_Origem = OID_Cidade_Aereo_Origem;
  }

  public void setOID_Cidade_Aereo_Destino (long OID_Cidade_Aereo_Destino) {
    this.OID_Cidade_Aereo_Destino = OID_Cidade_Aereo_Destino;
  }

  public void setVL_Master (double VL_Master) {
    this.VL_Master = VL_Master;
  }

  public void setVL_Frete_Aereo (double VL_Frete_Aereo) {
    this.VL_Frete_Aereo = VL_Frete_Aereo;
  }

  public void setDM_Tipo_Embalagem (String DM_Tipo_Embalagem) {
    this.DM_Tipo_Embalagem = DM_Tipo_Embalagem;
  }

  public void setDM_Cia_Aerea (String DM_Cia_Aerea) {
    this.DM_Cia_Aerea = DM_Cia_Aerea;
  }

  public void setOID_Motorista (String OID_Motorista) {
    this.OID_Motorista = OID_Motorista;
  }

  public void setNR_AWB (long NR_AWB) {
    this.NR_AWB = NR_AWB;
  }

  public void setNR_Nota_Fiscal_Servico (long NR_Nota_Fiscal_Servico) {
    this.NR_Nota_Fiscal_Servico = NR_Nota_Fiscal_Servico;
  }

  public void setNR_Embarques (double NR_Embarques) {
    this.NR_Embarques = NR_Embarques;
  }

  public void setD0 (double D0) {
    this.D0 = D0;
  }

  public void setD1 (double D1) {
    this.D1 = D1;
  }

  public void setD2 (double D2) {
    this.D2 = D2;
  }

  public void setD3 (double D3) {
    this.D3 = D3;
  }

  public void setD4 (double D4) {
    this.D4 = D4;
  }

  public void setD5 (double D5) {
    this.D5 = D5;
  }

  public void setD6 (double D6) {
    this.D6 = D6;
  }

  public void setD7 (double D7) {
    this.D7 = D7;
  }

  public void setD8 (double D8) {
    this.D8 = D8;
  }

  public void setDT_Coleta (String DT_Coleta) {
    this.DT_Coleta = DT_Coleta;
  }

  public void setOID_Centro_Custo (long OID_Centro_Custo) {

    this.OID_Centro_Custo = OID_Centro_Custo;
  }

  public void setNM_Tipo_Documento (String NM_Tipo_Documento) {
    this.NM_Tipo_Documento = NM_Tipo_Documento;
  }

  public void setTX_Roteiro (String TX_Roteiro) {
    this.TX_Roteiro = TX_Roteiro;
  }

  public void setNR_Documento (long NR_Documento) {
    this.NR_Documento = NR_Documento;
  }

  public void setNR_ACT (long NR_ACT) {
    this.NR_ACT = NR_ACT;
  }

  public void setNM_Roteiro (String NM_Roteiro) {
    this.NM_Roteiro = NM_Roteiro;
  }

  public void setCD_Roteiro (String CD_Roteiro) {
    this.CD_Roteiro = CD_Roteiro;
  }

  public void setOID_Grupo_Economico (long OID_Grupo_Economico) {
    this.OID_Grupo_Economico = OID_Grupo_Economico;
  }

  public void setDM_Procedencia_Veiculo (String DM_Procedencia_Veiculo) {
    this.DM_Procedencia_Veiculo = DM_Procedencia_Veiculo;
  }

  public void setNR_Minuta (long NR_Minuta) {
    this.NR_Minuta = NR_Minuta;
  }

  public void setPE_Taxa_Ajuste (double PE_Taxa_Ajuste) {
    this.PE_Taxa_Ajuste = PE_Taxa_Ajuste;
  }

  public void setDM_Monitoramento (String DM_Monitoramento) {
    this.DM_Monitoramento = DM_Monitoramento;
  }

  public void setNM_Liberacao_Veiculo (String NM_Liberacao_Veiculo) {
    this.NM_Liberacao_Veiculo = NM_Liberacao_Veiculo;
  }

  public void setOID_Lote_Faturamento (long OID_Lote_Faturamento) {
    this.OID_Lote_Faturamento = OID_Lote_Faturamento;
  }

  public void setVL_ISSQN (double VL_ISSQN) {
    this.VL_ISSQN = VL_ISSQN;
  }

  public void setNM_Situacao (String NM_Situacao) {
    this.NM_Situacao = NM_Situacao;
  }

  public void setVL_Base_Comissao_Acerto (double VL_Base_Comissao_Acerto) {
    this.VL_Base_Comissao_Acerto = VL_Base_Comissao_Acerto;
  }

  public void setDM_Calcular (String DM_Calcular) {

    this.DM_Calcular = DM_Calcular;
  }

  public void setVL_Total_Redespacho (double VL_Total_Redespacho) {
    this.VL_Total_Redespacho = VL_Total_Redespacho;
  }

  public void setNR_Cubagem (double NR_Cubagem) {
    this.NR_Cubagem = NR_Cubagem;
  }

  public String getNM_Especie () {
    return NM_Especie;
  }

  public void setNM_Especie (String especie) {
    NM_Especie = especie;
  }

  public String getTX_Ano () {
    return TX_Ano;
  }

  public void setTX_Ano (String ano) {
    TX_Ano = ano;
  }

  public String getTX_Semestre () {
    return TX_Semestre;
  }

  public void setTX_Semestre (String semestre) {
    TX_Semestre = semestre;
  }

  private void jbInit () throws Exception {
  }

  public String getCD_Rota_Entrega () {
    return CD_Rota_Entrega;
  }

  public void setCD_Rota_Entrega (String rota_Entrega) {
    CD_Rota_Entrega = rota_Entrega;
  }

  public long getOid_Programacao_Carga () {
    return oid_Programacao_Carga;
  }

  public String getDt_Previsao_Entrega_Final () {
    return dt_Previsao_Entrega_Final;
  }

  public String getDt_Previsao_Entrega_Inicial () {
    return dt_Previsao_Entrega_Inicial;
  }

  public String getDT_Programada () {
    return DT_Programada;
  }

  public String getNM_Usuario () {
    return NM_Usuario;
  }

  public String getNR_Pedido () {
    return NR_Pedido;
  }

  public String getNR_Conhecimento_Entregadora () {
    return NR_Conhecimento_Entregadora;
  }

  public String getDT_Previsao_Entregadora () {
    return DT_Previsao_Entregadora;
  }

  public double getVL_Frete_Entregadora () {
    return VL_Frete_Entregadora;
  }

  public String getNM_Pessoa_Entregadora () {
    return NM_Pessoa_Entregadora;
  }

  public double getA1 () {
    return A1;
  }

  public double getA2 () {
    return A2;
  }

  public double getA3 () {
    return A3;
  }

  public String getNR_CEP_Destinatario () {
    return NR_CEP_Destinatario;
  }

  public String getNR_CEP_Remetente () {
    return NR_CEP_Remetente;
  }

  public void setOid_Programacao_Carga (long oid_Programacao_Carga) {
    this.oid_Programacao_Carga = oid_Programacao_Carga;
  }

  public void setDt_Previsao_Entrega_Final (String dt_Previsao_Entrega_Final) {
    this.dt_Previsao_Entrega_Final = dt_Previsao_Entrega_Final;
  }

  public void setDt_Previsao_Entrega_Inicial (String dt_Previsao_Entrega_Inicial) {
    this.dt_Previsao_Entrega_Inicial = dt_Previsao_Entrega_Inicial;
  }

  public void setDT_Programada (String DT_Programada) {
    this.DT_Programada = DT_Programada;
  }

  public void setNM_Usuario (String NM_Usuario) {
    this.NM_Usuario = NM_Usuario;
  }

  public void setNR_Pedido (String NR_Pedido) {
    this.NR_Pedido = NR_Pedido;
  }

  public void setNR_Conhecimento_Entregadora (String NR_Conhecimento_Entregadora) {
    this.NR_Conhecimento_Entregadora = NR_Conhecimento_Entregadora;
  }

  public void setDT_Previsao_Entregadora (String DT_Previsao_Entregadora) {
    this.DT_Previsao_Entregadora = DT_Previsao_Entregadora;
  }

  public void setVL_Frete_Entregadora (double VL_Frete_Entregadora) {
    this.VL_Frete_Entregadora = VL_Frete_Entregadora;
  }

  public void setNM_Pessoa_Entregadora (String NM_Pessoa_Entregadora) {
    this.NM_Pessoa_Entregadora = NM_Pessoa_Entregadora;
  }

  public void setA3 (double A3) {
    this.A3 = A3;
  }

  public void setA2 (double A2) {
    this.A2 = A2;
  }

  public void setA1 (double A1) {
    this.A1 = A1;
  }

  public void setNR_CEP_Destinatario (String NR_CEP_Destinatario) {
    this.NR_CEP_Destinatario = NR_CEP_Destinatario;
  }

  public void setNR_CEP_Remetente (String NR_CEP_Remetente) {
    this.NR_CEP_Remetente = NR_CEP_Remetente;
  }

public String getCD_Modal() {
	return CD_Modal;
}

public void setCD_Modal(String modal) {
	CD_Modal = modal;
}

public String getTX_Ocorrencias() {
	return TX_Ocorrencias;
}

public void setTX_Ocorrencias(String ocorrencias) {
	TX_Ocorrencias = ocorrencias;
}

public double getVL_Nota_Fiscal_Seguro() {
	return VL_Nota_Fiscal_Seguro;
}

public void setVL_Nota_Fiscal_Seguro(double nota_Fiscal_Seguro) {
	VL_Nota_Fiscal_Seguro = nota_Fiscal_Seguro;
}

public long getOID_Participacao_Frete() {
	return OID_Participacao_Frete;
}

public void setOID_Participacao_Frete(long participacao_Frete) {
	OID_Participacao_Frete = participacao_Frete;
}

public String getOID_Rota_Entrega() {
	return OID_Rota_Entrega;
}

public void setOID_Rota_Entrega(String rota_Entrega) {
	OID_Rota_Entrega = rota_Entrega;
}

public String getHR_Coleta() {
	return HR_Coleta;
}

public void setHR_Coleta(String coleta) {
	HR_Coleta = coleta;
}

public String getDT_Confirmacao_Coleta() {
	return DT_Confirmacao_Coleta;
}

public void setDT_Confirmacao_Coleta(String confirmacao_Coleta) {
	DT_Confirmacao_Coleta = confirmacao_Coleta;
}

public String getHR_Confirmacao_Coleta() {
	return HR_Confirmacao_Coleta;
}

public void setHR_Confirmacao_Coleta(String confirmacao_Coleta) {
	HR_Confirmacao_Coleta = confirmacao_Coleta;
}

public String getDT_Solicitado_Coleta() {
	return DT_Solicitado_Coleta;
}

public void setDT_Solicitado_Coleta(String solicitado_Coleta) {
	DT_Solicitado_Coleta = solicitado_Coleta;
}

public String getHR_Solicitado_Coleta() {
	return HR_Solicitado_Coleta;
}

public void setHR_Solicitado_Coleta(String solicitado_Coleta) {
	HR_Solicitado_Coleta = solicitado_Coleta;
}

public String getDT_Realizacao_Coleta() {
	return DT_Realizacao_Coleta;
}

public void setDT_Realizacao_Coleta(String realizacao_Coleta) {
	DT_Realizacao_Coleta = realizacao_Coleta;
}

public String getHR_Realizacao_Coleta() {
	return HR_Realizacao_Coleta;
}

public void setHR_Realizacao_Coleta(String realizacao_Coleta) {
	HR_Realizacao_Coleta = realizacao_Coleta;
}

public long getNR_Prazo_Entrega() {
	return NR_Prazo_Entrega;
}

public void setNR_Prazo_Entrega(long prazo_Entrega) {
	NR_Prazo_Entrega = prazo_Entrega;
}

public String getDM_Justifica_Atrazo() {
	return DM_Justifica_Atrazo;
}

public void setDM_Justifica_Atrazo(String justifica_Atrazo) {
	DM_Justifica_Atrazo = justifica_Atrazo;
}

public String getOID_Novo_Conhecimento() {
	return OID_Novo_Conhecimento;
}

public void setOID_Novo_Conhecimento(String novo_Conhecimento) {
	OID_Novo_Conhecimento = novo_Conhecimento;
}

public long getNR_Frete_Terceiro() {
	return NR_Frete_Terceiro;
}

public void setNR_Frete_Terceiro(long frete_Terceiro) {
	NR_Frete_Terceiro = frete_Terceiro;
}

public String getOID_Cliente_Redespacho() {
	return OID_Cliente_Redespacho;
}

public void setOID_Cliente_Redespacho(String cliente_Redespacho) {
	OID_Cliente_Redespacho = cliente_Redespacho;
}

public double getVL_Frete_Redespacho() {
	return VL_Frete_Redespacho;
}

public void setVL_Frete_Redespacho(double frete_Redespacho) {
	VL_Frete_Redespacho = frete_Redespacho;
}

public String getNR_Conhecimento_Redespacho() {
	return NR_Conhecimento_Redespacho;
}

public void setNR_Conhecimento_Redespacho(String conhecimento_Redespacho) {
	NR_Conhecimento_Redespacho = conhecimento_Redespacho;
}

public long getOID_Redespacho() {
	return OID_Redespacho;
}

public void setOID_Redespacho(long redespacho) {
	OID_Redespacho = redespacho;
}

public long getOid_Ocorrencia_Conhecimento() {
	return oid_Ocorrencia_Conhecimento;
}

public void setOid_Ocorrencia_Conhecimento(long oid_Ocorrencia_Conhecimento) {
	this.oid_Ocorrencia_Conhecimento = oid_Ocorrencia_Conhecimento;
}

public String getDM_Viagem() {
	return DM_Viagem;
}

public void setDM_Viagem(String viagem) {
	DM_Viagem = viagem;
}

public String getID_Cotacao() {
	return ID_Cotacao;
}

public void setID_Cotacao(String cotacao) {
	ID_Cotacao = cotacao;
}

public String getURL_Cotacao() {
	return URL_Cotacao;
}

public void setURL_Cotacao(String cotacao) {
	URL_Cotacao = cotacao;
}

public String getNM_Empresa() {
	return NM_Empresa;
}

public void setNM_Empresa(String empresa) {
	NM_Empresa = empresa;
}

public long getOID_Unidade_Atual() {
	return OID_Unidade_Atual;
}

public void setOID_Unidade_Atual(long unidade_Atual) {
	OID_Unidade_Atual = unidade_Atual;
}

public long getOID_Unidade_Destino() {
	return OID_Unidade_Destino;
}

public void setOID_Unidade_Destino(long unidade_Destino) {
	OID_Unidade_Destino = unidade_Destino;
}

public String getOID_Unidade_Operacional() {
	return OID_Unidade_Operacional;
}

public void setOID_Unidade_Operacional(String unidade_Operacional) {
	OID_Unidade_Operacional = unidade_Operacional;
}

public double getNR_Tempo_Cliente() {
	return NR_Tempo_Cliente;
}

public void setNR_Tempo_Cliente(double tempo_Cliente) {
	NR_Tempo_Cliente = tempo_Cliente;
}

public String getDM_Exportacao() {
	return DM_Exportacao;
}

public void setDM_Exportacao(String exportacao) {
	DM_Exportacao = exportacao;
}

public String getDM_Procedencia_Carreta() {
	return DM_Procedencia_Carreta;
}

public void setDM_Procedencia_Carreta(String procedencia_Carreta) {
	DM_Procedencia_Carreta = procedencia_Carreta;
}

public String getNM_Procedencia_Carreta() {
	return NM_Procedencia_Carreta;
}

public void setNM_Procedencia_Carreta(String procedencia_Carreta) {
	NM_Procedencia_Carreta = procedencia_Carreta;
}

public String getNM_Procedencia_Veiculo() {
	return NM_Procedencia_Veiculo;
}

public void setNM_Procedencia_Veiculo(String procedencia_Veiculo) {
	NM_Procedencia_Veiculo = procedencia_Veiculo;
}

public String getDT_Chegada_Cliente() {
	return DT_Chegada_Cliente;
}

public void setDT_Chegada_Cliente(String chegada_Cliente) {
	DT_Chegada_Cliente = chegada_Cliente;
}

public String getDT_Comprovante_Entrega() {
	return DT_Comprovante_Entrega;
}

public void setDT_Comprovante_Entrega(String comprovante_Entrega) {
	DT_Comprovante_Entrega = comprovante_Entrega;
}

public String getDT_Saida_Viagem() {
	return DT_Saida_Viagem;
}

public void setDT_Saida_Viagem(String saida_Viagem) {
	DT_Saida_Viagem = saida_Viagem;
}

public String getHR_Chegada_Cliente() {
	return HR_Chegada_Cliente;
}

public void setHR_Chegada_Cliente(String chegada_Cliente) {
	HR_Chegada_Cliente = chegada_Cliente;
}

public String getHR_Saida_Viagem() {
	return HR_Saida_Viagem;
}

public void setHR_Saida_Viagem(String saida_Viagem) {
	HR_Saida_Viagem = saida_Viagem;
}

public String getNM_Mapa() {
	return NM_Mapa;
}

public void setNM_Mapa(String mapa) {
	NM_Mapa = mapa;
}

public String getNM_Motorista() {
	return NM_Motorista;
}

public void setNM_Motorista(String motorista) {
	NM_Motorista = motorista;
}

public String getNM_Relatorio() {
	return NM_Relatorio;
}

public void setNM_Relatorio(String relatorio) {
	NM_Relatorio = relatorio;
}

public double getNR_Antecipada_Entrega() {
	return NR_Antecipada_Entrega;
}

public void setNR_Antecipada_Entrega(double antecipada_Entrega) {
	NR_Antecipada_Entrega = antecipada_Entrega;
}

public double getNR_Atrasada_Entrega() {
	return NR_Atrasada_Entrega;
}

public void setNR_Atrasada_Entrega(double atrasada_Entrega) {
	NR_Atrasada_Entrega = atrasada_Entrega;
}

public double getNR_Entrega_Justificado() {
	return NR_Entrega_Justificado;
}

public void setNR_Entrega_Justificado(double entrega_Justificado) {
	NR_Entrega_Justificado = entrega_Justificado;
}

public double getNR_Entrega_Pendente() {
	return NR_Entrega_Pendente;
}

public void setNR_Entrega_Pendente(double entrega_Pendente) {
	NR_Entrega_Pendente = entrega_Pendente;
}

public String getDM_Acerto() {
	return DM_Acerto;
}

public void setDM_Acerto(String acerto) {
	DM_Acerto = acerto;
}

public String getDT_Cancelamento() {
	return DT_Cancelamento;
}

public void setDT_Cancelamento(String cancelamento) {
	DT_Cancelamento = cancelamento;
}

public double getPE_Aliquota_ICMS_Canc() {
	return PE_Aliquota_ICMS_Canc;
}

public void setPE_Aliquota_ICMS_Canc(double aliquota_ICMS_Canc) {
	PE_Aliquota_ICMS_Canc = aliquota_ICMS_Canc;
}

public double getVL_Base_Calc_Canc() {
	return VL_Base_Calc_Canc;
}

public void setVL_Base_Calc_Canc(double base_Calc_Canc) {
	VL_Base_Calc_Canc = base_Calc_Canc;
}

public double getVL_ICMS_Canc() {
	return VL_ICMS_Canc;
}

public void setVL_ICMS_Canc(double canc) {
	VL_ICMS_Canc = canc;
}

public double getVL_Total_Frete_Canc() {
	return VL_Total_Frete_Canc;
}

public void setVL_Total_Frete_Canc(double total_Frete_Canc) {
	VL_Total_Frete_Canc = total_Frete_Canc;
}

public double getNR_Perf() {
	return NR_Perf;
}

public void setNR_Perf(double perf) {
	NR_Perf = perf;
}

public String getCD_Carga_Especial() {
	return CD_Carga_Especial;
}

public void setCD_Carga_Especial(String carga_Especial) {
	CD_Carga_Especial = carga_Especial;
}

public String getCD_Classe() {
	return CD_Classe;
}

public void setCD_Classe(String classe) {
	CD_Classe = classe;
}

public String getCD_IATA_DESTINO() {
	return CD_IATA_DESTINO;
}

public void setCD_IATA_DESTINO(String cd_iata_destino) {
	CD_IATA_DESTINO = cd_iata_destino;
}

public String getCD_IATA_ORIGEM() {
	return CD_IATA_ORIGEM;
}

public void setCD_IATA_ORIGEM(String cd_iata_origem) {
	CD_IATA_ORIGEM = cd_iata_origem;
}

public String getCD_IATA_PASSAGEM() {
	return CD_IATA_PASSAGEM;
}

public void setCD_IATA_PASSAGEM(String cd_iata_passagem) {
	CD_IATA_PASSAGEM = cd_iata_passagem;
}

public String getCD_Manuseio() {
	return CD_Manuseio;
}

public void setCD_Manuseio(String manuseio) {
	CD_Manuseio = manuseio;
}

public String getCD_ONU() {
	return CD_ONU;
}

public void setCD_ONU(String cd_onu) {
	CD_ONU = cd_onu;
}

public String getCD_Tarifa() {
	return CD_Tarifa;
}

public void setCD_Tarifa(String tarifa) {
	CD_Tarifa = tarifa;
}

public String getDM_CTe() {
	return DM_CTe;
}

public void setDM_CTe(String te) {
	DM_CTe = te;
}

public String getDM_Status_Anulacao_CTe() {
	return DM_Status_Anulacao_CTe;
}

public void setDM_Status_Anulacao_CTe(String status_Anulacao_CTe) {
	DM_Status_Anulacao_CTe = status_Anulacao_CTe;
}

public String getDM_Status_Cancelamento_CTe() {
	return DM_Status_Cancelamento_CTe;
}

public void setDM_Status_Cancelamento_CTe(String status_Cancelamento_CTe) {
	DM_Status_Cancelamento_CTe = status_Cancelamento_CTe;
}

public String getDM_Status_CTe() {
	return DM_Status_CTe;
}

public void setDM_Status_CTe(String status_CTe) {
	DM_Status_CTe = status_CTe;
}

public String getDT_Envio_Anulacao_CTe() {
	return DT_Envio_Anulacao_CTe;
}

public void setDT_Envio_Anulacao_CTe(String envio_Anulacao_CTe) {
	DT_Envio_Anulacao_CTe = envio_Anulacao_CTe;
}

public String getDT_Envio_Cancelamento_CTe() {
	return DT_Envio_Cancelamento_CTe;
}

public void setDT_Envio_Cancelamento_CTe(String envio_Cancelamento_CTe) {
	DT_Envio_Cancelamento_CTe = envio_Cancelamento_CTe;
}

public String getDT_Envio_CTe() {
	return DT_Envio_CTe;
}

public void setDT_Envio_CTe(String envio_CTe) {
	DT_Envio_CTe = envio_CTe;
}

public String getDT_Recebimento_Anulacao_CTe() {
	return DT_Recebimento_Anulacao_CTe;
}

public void setDT_Recebimento_Anulacao_CTe(String recebimento_Anulacao_CTe) {
	DT_Recebimento_Anulacao_CTe = recebimento_Anulacao_CTe;
}

public String getDT_Recebimento_Cancelamento_CTe() {
	return DT_Recebimento_Cancelamento_CTe;
}

public void setDT_Recebimento_Cancelamento_CTe(
		String recebimento_Cancelamento_CTe) {
	DT_Recebimento_Cancelamento_CTe = recebimento_Cancelamento_CTe;
}

public String getDT_Recebimento_CTe() {
	return DT_Recebimento_CTe;
}

public void setDT_Recebimento_CTe(String recebimento_CTe) {
	DT_Recebimento_CTe = recebimento_CTe;
}

public String getFile1() {
	return File1;
}

public void setFile1(String file1) {
	File1 = file1;
}

public String getFile2() {
	return File2;
}

public void setFile2(String file2) {
	File2 = file2;
}

public String getFile3() {
	return File3;
}

public void setFile3(String file3) {
	File3 = file3;
}

public String getFile4() {
	return File4;
}

public void setFile4(String file4) {
	File4 = file4;
}

public String getNM_CH_CTe() {
	return NM_CH_CTe;
}

public void setNM_CH_CTe(String te) {
	NM_CH_CTe = te;
}

public String getNM_CH_NFe() {
	return NM_CH_NFe;
}

public void setNM_CH_NFe(String fe) {
	NM_CH_NFe = fe;
}

public String getNM_DACTE() {
	return NM_DACTE;
}

public void setNM_DACTE(String nm_dacte) {
	NM_DACTE = nm_dacte;
}

public String getNM_Protocolo_Anulacao_CTe() {
	return NM_Protocolo_Anulacao_CTe;
}

public void setNM_Protocolo_Anulacao_CTe(String protocolo_Anulacao_CTe) {
	NM_Protocolo_Anulacao_CTe = protocolo_Anulacao_CTe;
}

public String getNM_Protocolo_Cancelamento_CTe() {
	return NM_Protocolo_Cancelamento_CTe;
}

public void setNM_Protocolo_Cancelamento_CTe(String protocolo_Cancelamento_CTe) {
	NM_Protocolo_Cancelamento_CTe = protocolo_Cancelamento_CTe;
}

public String getNM_Protocolo_Envio_CTe() {
	return NM_Protocolo_Envio_CTe;
}

public void setNM_Protocolo_Envio_CTe(String protocolo_Envio_CTe) {
	NM_Protocolo_Envio_CTe = protocolo_Envio_CTe;
}

public String getNM_Protocolo_Retorno_CTe() {
	return NM_Protocolo_Retorno_CTe;
}

public void setNM_Protocolo_Retorno_CTe(String protocolo_Retorno_CTe) {
	NM_Protocolo_Retorno_CTe = protocolo_Retorno_CTe;
}

public String getNM_Status_Anulacao_CTe() {
	return NM_Status_Anulacao_CTe;
}

public void setNM_Status_Anulacao_CTe(String status_Anulacao_CTe) {
	NM_Status_Anulacao_CTe = status_Anulacao_CTe;
}

public String getNM_Status_Cancelamento_CTe() {
	return NM_Status_Cancelamento_CTe;
}

public void setNM_Status_Cancelamento_CTe(String status_Cancelamento_CTe) {
	NM_Status_Cancelamento_CTe = status_Cancelamento_CTe;
}

public String getNM_Status_CTe() {
	return NM_Status_CTe;
}

public void setNM_Status_CTe(String status_CTe) {
	NM_Status_CTe = status_CTe;
}

public String getNM_Usuario_Logado() {
	return NM_Usuario_Logado;
}

public void setNM_Usuario_Logado(String usuario_Logado) {
	NM_Usuario_Logado = usuario_Logado;
}

public long getNR_PIN_Suframa() {
	return NR_PIN_Suframa;
}

public void setNR_PIN_Suframa(long suframa) {
	NR_PIN_Suframa = suframa;
}

public String getNR_Romaneio() {
	return NR_Romaneio;
}

public void setNR_Romaneio(String romaneio) {
	NR_Romaneio = romaneio;
}

public String getOID_Conhecimento_Original() {
	return OID_Conhecimento_Original;
}

public void setOID_Conhecimento_Original(String conhecimento_Original) {
	OID_Conhecimento_Original = conhecimento_Original;
}

public String getOID_Conhecimento_Substituto() {
	return OID_Conhecimento_Substituto;
}

public void setOID_Conhecimento_Substituto(String conhecimento_Substituto) {
	OID_Conhecimento_Substituto = conhecimento_Substituto;
}

public long getOID_Parceiro() {
	return OID_Parceiro;
}

public void setOID_Parceiro(long parceiro) {
	OID_Parceiro = parceiro;
}

public String getOID_Pessoa_Expedidor() {
	return OID_Pessoa_Expedidor;
}

public void setOID_Pessoa_Expedidor(String pessoa_Expedidor) {
	OID_Pessoa_Expedidor = pessoa_Expedidor;
}

public String getTX_Dimensoes() {
	return TX_Dimensoes;
}

public void setTX_Dimensoes(String dimensoes) {
	TX_Dimensoes = dimensoes;
}

public double getVL_Tarifa() {
	return VL_Tarifa;
}

public void setVL_Tarifa(double tarifa) {
	VL_Tarifa = tarifa;
}

public String getOid_pessoa_unidade() {
	return oid_pessoa_unidade;
}

public void setOid_pessoa_unidade(String oid_pessoa_unidade) {
	this.oid_pessoa_unidade = oid_pessoa_unidade;
}
}
