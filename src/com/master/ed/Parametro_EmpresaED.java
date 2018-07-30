package com.master.ed;
 
/**
 * @author Vin�cius e Jeanine
 * @serial Parametro Empresa
 * @serialData 02/04/2008
 */  

public class Parametro_EmpresaED {
	
	private static final long serialVersionUID = 1908134503863998704L;
	
	//========== Tela Prncipal =============\\
	private String dm_Tipo_Sistema;
	private String dm_Perfil_Sistema;
	private String dm_Operacao_Sistema;
	private String dm_UsaCTB;
	private String nm_Razao_Social_Empresa;
	private long oid_Moeda_Padrao;
	private long oid_Moeda_Dollar;
	private String dm_Situacao;
	
	//============ A��es Gerais ===========\\ 
	private String dm_Gera_Embarque_Viagem;
	private String dm_Gera_Rateio_Custo_Ordem_Frete;
	private String dm_Gera_Ocorrencia_Viagem;
	private String dm_Gera_Custo_Viagem;
	private String dm_Gera_Compromisso_Viagem;
	private String dm_Gera_Compromisso_Nota_Fiscal_Compra;
	private String dm_Gera_Compromisso_Ordem_Frete;
	private String dm_Gera_Compromisso_Ordem_Frete_Adiantamento;
	private String dm_Gera_Compromisso_Ordem_Abastecimento;
	private String dm_Gera_Compromisso_IR_Ordem_Frete;
	private String dm_Gera_Compromisso_INSS_Ordem_Frete;
	private String dm_Gera_Compromisso_Master;
	private String dm_Gera_Compromisso_Movimento_Ordem_Servico;
	private String dm_Gera_Compromisso_Motorista_Proprietario;
	private String dm_Gera_Lancamento_Contabil;
	private String dm_Gera_Lancamento_Conta_Corrente_Ordem_Frete_Terceiro;
	private String dm_Gera_Lancamento_Conta_Corrente_Ordem_Frete_Adiantamento;
	private String dm_Gera_Lancamento_Conta_Corrente_Ordem_Frete;
	private String dm_Gera_Lancamento_Conta_Corrente_Acerto_Contas;
	private String dm_Gera_Livro_Fiscal;
	private String dm_Atualiza_Movimento_Caixa;
	private String dm_Liquida_Compromisso; 
	private String dm_Conhecimento_Varios_Manifestos;
	private String dm_Tranca_Saldo_Ordem_Frete;
	
	//=========== Tela Aliquota ==========\\
	private double pe_Aliquota_PIS_COFINS;
	private double pe_Imposto_Faixa1;
	private double pe_Imposto_Faixa2;
	private double pe_Base_Calculo;
	private double pe_Aliquota_Empresa_INSS;
	private double pe_Aliquota_Prestador_INSS;
	private double pe_Base_INSS;
	private double pe_Base_SET_SENAT;
	private double pe_Aliquota_Prestador_Set_Senat;
	private double pe_Aliquota_COFINS;
	private double pe_Aliquota_PIS;
	private double pe_Aliquota_CSLL;
	private double pe_Comissao_Motorista;
	private double pe_Base_Comissao_Motorista;
	private double pe_Comissao_Motorista_Media;
	private double vl_Faixa1;
	private double vl_Faixa2;
	private double vl_Deducao_Faixa1;
	private double vl_Deducao_Faixa2;
	private double vl_Dependente;
	private double vl_Maximo_INSS;
	private double vl_Frete_Cortesia;
	private String vl_Reembolso;

	//======= Tela Base Dados ========\\
	private String path_Relatorios;
	private String path_Relatorios_XSL;
	private String path_imagens;
	private String serverName;
	private String dt_Hoje; 
	private String hr_Hoje;
	private String nm_Versao;
	private String document_Form_Action;
	private String appPath;	
	private String palmPath;
	private String palmModelo;
	private String logTransactions;
	private long port;
	private String contexto;
	private String webmaster;
	
	//============ Tela Calculo ======\\ 
	private String dm_Calcula_INSS;
	private String dm_Calcula_IRRF;
	private String dm_Calcula_PIS_COFINS;
	private String dm_Tipo_Calculo_ICMS; 
	private String dm_Soma_Pedagio_Saldo_Frete;
	private String dm_Soma_Sest_Senat_Saldo_Frete;
	private String dm_Soma_Outros_Saldo_Frete;
	
	//============ Carteira =======================\\ 
	private long oid_Carteira_Nota_Retorno;
	private long oid_Carteira_Faturamento; 
	
	//=========== Tela Centro de Custo ======\\ 
	private long oid_Centro_Custo_Ordem_Frete;
	private long oid_Centro_Custo_Movimento_Ordem_Servico;
	private long oid_Centro_Custo_Master;
	private long oid_Centro_Custo_Entrega;
	
	//============ CFOP/ TAXAS ===================\\ 
	private String CFOP_Padrao;
	private long oid_CFOP_Pessoa_Fisica;
	private long oid_CFOP_Diferenciado;
	private String dm_Estado_Origem_CFOP;
	private String dm_Estado_Destino_CFOP;
	private String dm_Estado_Origem_Taxas;
	private String dm_Estado_Destino_Taxas;

	//========== Tela Contas ==============\\
	private long oid_Conta_Credito_Master;
	private long oid_Conta_Debito_Master;
	private long oid_Conta_Credito_Pedagio;
	private long oid_Conta_Debito_Pedagio;
	private long oid_Conta_Credito_Entrega;
	private long oid_Conta_Debito_Entrega;
	private long oid_Conta_Credito_Ordem_Frete_Adiantamento;
	private long oid_Conta_Ordem_Frete_Adiantamento;
	private long oid_Conta_Frete_Terceiros;
	private long oid_Conta_Desconto_Frete;
	private long oid_Conta_Despesa_Bancaria;
	private long oid_Conta_Acerto_Contas;
	private long oid_Conta_Ordem_Frete;
	private long oid_Conta_IR_Ordem_Frete;
	private long oid_Conta_INSS_Ordem_Frete;
	private long oid_Conta_Movimento_Compromisso;
	private long oid_Conta_Movimento_Ordem_Servico;
	private long oid_Conta_Credito_Movimento_Ordem_Servico;
	private long oid_Conta_Credito_Ordem_Frete;
	private long oid_Conta_Juridica_Ordem_Frete;
	private long oid_Conta_Multa_Transito;
	
	//======== Tela Descri��o ==========\\  
	private String dm_Saldo_Programado;
	private String texto_Imprimir_Campo_Nao_Validado;
	private String dm_Multi_Moeda;
	private long vias_Fatura;
	private long oid_Condicao_Pagamento_Isento;
	
	//======== Tela Documento ==========\\  
	private long oid_Tipo_Documento_Faturamento;
	private long oid_Tipo_Documento_Ordem_Servico;
	private long oid_Tipo_Documento_Faturamento_Internacional;
	private long oid_Tipo_Documento_Master;
	private long oid_Tipo_Documento_IR;
	private long oid_Tipo_Documento_INSS;
	private long oid_Tipo_Documento_Ordem_Frete;
	private long oid_Tipo_Documento_Transferencia_Conta_Corrente;
	private long oid_Tipo_Documento_Entrega;
	private long oid_Tipo_Documento_Ordem_Frete_Adiantamento;
	private long oid_Tipo_Documento_Nota_Fiscal_Entrada;
	private long oid_Tipo_Documento_Nota_Fiscal_Saida;
	private long oid_Tipo_Documento_Faturamento_Nota_Fiscal_Servico;
	
	//=========== Tela Faturamento ==========\\
	private String dm_Quebra_Faturamento_Tipo_Conhecimento;
	private String dm_Quebra_Faturamento_Unidade;
	private String dm_Quebra_Faturamento_Tipo_Faturamento;
	
	//========== Tela Formularios =========\\
	private String dm_Formulario_Consolidacao_MIC;
	private String dm_Formulario_Consolidacao_MIC_CRT;
	private String dm_Formulario_Duplicata_Proform;
	private String dm_Formulario_Duplicata;
	private String dm_Formulario_Demonstrativo_Cobranca;
	private String dm_Formulario_Protocolo_Cobranca;
	private String dm_Formulario_Duplicata_Internacional;
	private String dm_Formulario_Minuta;
	private String dm_Formulario_ACT;
	private String dm_Formulario_Conhecimento_Nacional;
	private String dm_Formulario_Conhecimento_Internacional;
	private String dm_Formulario_Conhecimento_Internacional_Verso;
	private String dm_Formulario_Coleta;
	private String dm_Formulario_Ordem_Frete;
	private String dm_Formulario_Ordem_Frete_Adiantamento;
	private String dm_Formulario_Ordem_Abastecimento;
	private String dm_Formulario_Nota_Fiscal;
	private String dm_Formulario_Ordem_Servico;
	private String dm_Formulario_Manifesto;
	private String dm_Formulario_Manifesto_Redespacho;
	private String dm_Formulario_Romaneio_Nota_Fiscal;
	private String dm_Formulario_Romaneio_Itens_Nota_Fiscal;
	private String dm_Formulario_Acerto_Contas;
	private String dm_Formulario_Romaneio_Entrega;
	private String dm_Formulario_AWB;
	private String dm_Formulario_Nota_Fiscal_Servico; 
	private long nr_Vias_Ordem_Frete_Adiantamento;
	private long nr_Vias_Ordem_Frete;
	
	//============ Tela Frota ======\\
	private String dm_Base_Comissao_Motorista;
	private String dm_Limite_Credito_Motorista_Adiantamento_Frete;
	private String dm_Exige_Validade_Lib_Seguradora_Motorista;
	private String movimento_Ordem_Servico_Duplicada; //combobox
	
	//========== Tela Historicos ==========\\
	private long oid_Historico_Transferencia_Banco;
	private long oid_Historico_Transferencia_Caixa;
	private long oid_Historico_Compensacao;
	private long oid_Historico_Devolucao_Cheque;
	private long oid_Historico_Cancelamento_Lote_Pagamento;
	private long oid_Historico_Cancelamento_Ordem_Frete;
	private long oid_Historico_Lancamento_Ordem_Frete_Terceiro;
	private long oid_Historico_Lancamento_Ordem_Frete;
	private long oid_Historico_Lancamento_Ordem_Frete_Adiantamento;
	private long oid_Historico_Liquidacao_Cobranca;
	private long oid_Historico_Liquidacao_Cobranca_Retorno;
	private long oid_Historico_Bordero;
	
	//============ Tela Impress�o ======\\
	private String dm_Tipo_Impressao_Fatura;
	private String dm_Tipo_Impressao_Conhecimento_Nacional;
	private String dm_Tipo_Impressao_Minuta;
	private String dm_Tipo_Impressao_Nota_Fiscal_Servico;
	private String dm_Tipo_Impressao_Coleta;
	
	//========== Tela Instrucoes ==========\\
	private long oid_Tipo_Instrucao_Pago_Total;
	private long oid_Tipo_Instrucao_Pago_Cartorio;
	private long oid_Tipo_Instrucao_Pago_Parcial;
	private long oid_Tipo_Instrucao_Tarifa;
	private long oid_Tipo_Instrucao_Juros;
	private long oid_Tipo_Instrucao_Desconto;
	private long oid_Tipo_Instrucao_Taxa_Cobranca;
	private long oid_Tipo_Instrucao_Estorno;
	private long oid_Tipo_Instrucao_Valor_Reembolso;
	private long oid_Tipo_Instrucao_Juros_Reembolso;
	private long oid_Tipo_Instrucao_Variacao_Cambial_Ativa;
	private long oid_Tipo_Instrucao_Variacao_Cambial_Passiva;
	private long oid_Tipo_Instrucao_Imposto_Retido1;
	private long oid_Tipo_Instrucao_Imposto_Retido2;
	private long oid_Tipo_Instrucao_Devolucao_Nota_Fiscal;
	private long oid_Tipo_Instrucao_Remessa;
	
	//======= Transporte Internacional ========\\
	private String tx_Via_Primeiro_Original_Pt;
	private String tx_Via_Segundo_Original_Pt;
	private String tx_Via_Terceiro_Original_Pt;
	private String tx_Via_Primeiro_Original_Es;
	private String tx_Via_Segundo_Original_Es;
	private String tx_Via_Terceiro_Original_Es;
	private String dm_Impressao_Tramo;
	private String nm_Sucessivos;
	private String nm_Ano_Permisso;
	private String completa_TX_Via; //combobox
	private long oid_Tipo_Faturamento_Exportador;
	private long oid_Tipo_Faturamento_Importador;
	private long oid_Tipo_Faturamento_Consolidacao_MIC_CRT;
	private long oid_Tipo_Faturamento_Real;
	
	//============ Tela Localiza��o ======\\ 
	private long oid_Localizacao;
	private long oid_Localizacao_Devolucao;
	private long oid_Localizacao_Troca;
	
	//=========== Tela MODAL SERVI�OS ======\\ 
	private long oid_Modal;
	private long oid_Modal_Aereo_Standard;
	private long oid_Modal_Aereo_Expressa;
	private long oid_Modal_Aereo_Emergencial;
	private long oid_Modal_Aereo_Sedex;
	private long oid_Modal_Aereo_Pac;
	private long oid_Modal_Aereo_RodExp;
	
	//============ Tela Modelo ============\\ 
	private long oid_Modelo_Nota_Fiscal;
	private long oid_Modelo_NF_DevFornecedor_Dentro_Estado;
	private long oid_Modelo_NF_DevFornecedor_Fora_Estado;
	
	//============ Modelo Tabela ==================\\ 
	private String nm_Modelo_Tabela_Rodoviario = "N";
	private String nm_Modelo_Tabela_Aereo = "N";
	private String nm_Modelo_Tabela_Documento = "N";
	
	//========== Tela Movimentos ==========\\
	private long oid_Tipo_Movimento_Custo_Entrega;
	private long oid_Tipo_Movimento_Custo_Previsto_Entrega;
	private long oid_Tipo_Movimento_Custo_Master;
	private long oid_Tipo_Movimento_Custo_Ordem_Frete;
	private long oid_Tipo_Movimento_Ressarcimento;
	private long oid_Tipo_Movimento_Custo_Coleta;
	private long oid_Tipo_Movimento_Custo_Transferencia;
	private long oid_Tipo_Movimento_Custo_Coleta_Transferencia;
	private long oid_Tipo_Movimento_Custo_Transferencia_Entrega;
	private long oid_Tipo_Movimento_Custo_Coleta_Transferencia_Entrega;
	private long oid_Tipo_Movimento_Custo_Desconto;
	private long oid_Tipo_Movimento_Custo_Devolucao;
	private long oid_Tipo_Movimento_Custo_Reentrega;
	private long oid_Tipo_Movimento_Produto;
	private long oid_Tipo_Movimento_Transf;
	private long oid_Tipo_Movimento_Ajuste_E;
	private long oid_Tipo_Movimento_Ajuste_S;
	private long oid_Tipo_Movimento_Ajuste_Canc;
	private long oid_Tipo_Movimento_Ajuste_Exc;
	
	//====== Tela Natureza Opera��o ======\\ 
	private long oid_Natureza_Operacao_Ordem_Frete;
	private long oid_Natureza_Operacao_Entrega;
	private long oid_Natureza_Operacao_Master;
	
	//============ Tela Numera��es ======\\ 
	private String dm_Numera_Ordem_Frete;
	private String dm_Numera_Coleta;
	private String dm_Numera_Manifesto;
	private String dm_Numera_Conhecimento;
	private String dm_Numera_Frete_Internacional;
	private String dm_Numera_Fatura;
	
	//========== Tela Ocorr�ncias =========\\
	private long oid_Tipo_Ocorrencia_Desconto_CTRC;
	private long oid_Tipo_Ocorrencia_Estorno_CTRC;
	private long oid_Tipo_Ocorrencia_Cancelamento_CTRC;
	private long oid_Tipo_Ocorrencia_Devolucao_CTRC;
	private long oid_Tipo_Ocorrencia_Reentrega_CTRC;
	
	//============ Tela Pessoa ================================\\ 
	private String oid_Fornecedor_INSS;
	private String oid_Fornecedor_IRRF;
	private String oid_Cliente_Complemento_Padrao;
	private String oid_Pessoa_Padrao_Tabela_Frete;
	private String dm_Valida_CEP;
	private String dm_Verifica_CNPJ_CPF_Pessoa = "N";
	private String dm_Envia_Email_Eventos = "N";
	private String geraCodigoCliente;
	
	//=========== Servi�o ========================\\ 
	private long oid_Tipo_Servico_Acerto_Contas;
	private long oid_Tipo_Servico_Abastecimento;
		
	//======= Tela Transporte Nacional ======\\
	private String dm_Exige_Pagador_Cliente; 
	private String dm_Imprime_Conhecimento_Ordem_Frete;
	private String dm_Tipo_Conhecimento;
	private String dm_Criterio_Comissao; 
	private String dm_Frete_Cortesia; 
	private String dm_Vincula_Adto_Ordem_Principal;
	private String dm_Despacho;
	private String dm_Inclui_ICMS_Parcela_CTRC; 
	private long oid_Produto;
	private long nr_Peso_Cubado;   
	private long nr_Conhecimentos_Linha_Fatura;
	private String nf_Multi_Conhecimento; 
	
	//============ Tela Unidade ======\\ 
	private long oid_Unidade_Faturamento;
	private long oid_Unidade_Faturamento_Minuta;
	private long oid_Unidade_Padrao;
	
	//=========== Tela WMS ==========\\
	private String nm_Nivel_Produto1;
	private String nm_Nivel_Produto2;
	private String nm_Nivel_Produto3;
	private String nm_Nivel_Produto4;
	private String nm_Nivel_Produto5;
	private String dm_Wms_Nf_Saida_Numerada;
	private long oid_Tipo_Estoque;
	private long oid_Tipo_Estoque_Devolucao;
	private long oid_Tipo_Estoque_Troca;
	private long oid_Tipo_Pallet; 
	private long oid_Tipo_Pedido;
	private long oid_Deposito;
	private long oid_Operador;
	private long oid_Embalagem;
	
//	============ Getters and Setters Tela Principal  ================================================================================\\
	public String getDm_Operacao_Sistema() {
		return dm_Operacao_Sistema;
	}
	public void setDm_Operacao_Sistema(String dm_Operacao_Sistema) {
		this.dm_Operacao_Sistema = dm_Operacao_Sistema;
	}
	public String getDm_Perfil_Sistema() {
		return dm_Perfil_Sistema;
	}
	public void setDm_Perfil_Sistema(String dm_Perfil_Sistema) {
		this.dm_Perfil_Sistema = dm_Perfil_Sistema;
	}
	public String getDm_Tipo_Sistema() {
		return dm_Tipo_Sistema;
	}
	public void setDm_Tipo_Sistema(String dm_Tipo_Sistema) {
		this.dm_Tipo_Sistema = dm_Tipo_Sistema;
	}
	public String getDm_UsaCTB() {
		return dm_UsaCTB;
	}
	public void setDm_UsaCTB(String dm_UsaCTB) {
		this.dm_UsaCTB = dm_UsaCTB;
	}
	public String getNm_Razao_Social_Empresa() {
		return nm_Razao_Social_Empresa;
	}
	public void setNm_Razao_Social_Empresa(String nm_Razao_Social_Empresa) {
		this.nm_Razao_Social_Empresa = nm_Razao_Social_Empresa;
	}
	public String getDm_Situacao() {
		return dm_Situacao;
	}
	public void setDm_Situacao(String dm_Situacao) {
		this.dm_Situacao = dm_Situacao;
	}
	public long getOid_Moeda_Dollar() {
		return oid_Moeda_Dollar;
	}
	public void setOid_Moeda_Dollar(long oid_Moeda_Dollar) {
		this.oid_Moeda_Dollar = oid_Moeda_Dollar;
	}
	public long getOid_Moeda_Padrao() {
		return oid_Moeda_Padrao;
	}
	public void setOid_Moeda_Padrao(long oid_Moeda_Padrao) {
		this.oid_Moeda_Padrao = oid_Moeda_Padrao;
	}
	
//============ Getters and Setters Tela A��es Gerais =============================================================\\	
	public String getDm_Gera_Compromisso_INSS_Ordem_Frete() {
		return dm_Gera_Compromisso_INSS_Ordem_Frete;
	}
	public void setDm_Gera_Compromisso_INSS_Ordem_Frete(
			String dm_Gera_Compromisso_INSS_Ordem_Frete) {
		this.dm_Gera_Compromisso_INSS_Ordem_Frete = dm_Gera_Compromisso_INSS_Ordem_Frete;
	}
	public String getDm_Gera_Compromisso_IR_Ordem_Frete() {
		return dm_Gera_Compromisso_IR_Ordem_Frete;
	}
	public void setDm_Gera_Compromisso_IR_Ordem_Frete(
			String dm_Gera_Compromisso_IR_Ordem_Frete) {
		this.dm_Gera_Compromisso_IR_Ordem_Frete = dm_Gera_Compromisso_IR_Ordem_Frete;
	}
	public String getDm_Gera_Compromisso_Master() {
		return dm_Gera_Compromisso_Master;
	}
	public void setDm_Gera_Compromisso_Master(String dm_Gera_Compromisso_Master) {
		this.dm_Gera_Compromisso_Master = dm_Gera_Compromisso_Master;
	}
	public String getDm_Gera_Compromisso_Motorista_Proprietario() {
		return dm_Gera_Compromisso_Motorista_Proprietario;
	}
	public void setDm_Gera_Compromisso_Motorista_Proprietario(
			String dm_Gera_Compromisso_Motorista_Proprietario) {
		this.dm_Gera_Compromisso_Motorista_Proprietario = dm_Gera_Compromisso_Motorista_Proprietario;
	}
	public String getDm_Gera_Compromisso_Movimento_Ordem_Servico() {
		return dm_Gera_Compromisso_Movimento_Ordem_Servico;
	}
	public void setDm_Gera_Compromisso_Movimento_Ordem_Servico(
			String dm_Gera_Compromisso_Movimento_Ordem_Servico) {
		this.dm_Gera_Compromisso_Movimento_Ordem_Servico = dm_Gera_Compromisso_Movimento_Ordem_Servico;
	}
	public String getDm_Gera_Compromisso_Nota_Fiscal_Compra() {
		return dm_Gera_Compromisso_Nota_Fiscal_Compra;
	}
	public void setDm_Gera_Compromisso_Nota_Fiscal_Compra(
			String dm_Gera_Compromisso_Nota_Fiscal_Compra) {
		this.dm_Gera_Compromisso_Nota_Fiscal_Compra = dm_Gera_Compromisso_Nota_Fiscal_Compra;
	}
	public String getDm_Gera_Compromisso_Ordem_Abastecimento() {
		return dm_Gera_Compromisso_Ordem_Abastecimento;
	}
	public void setDm_Gera_Compromisso_Ordem_Abastecimento(
			String dm_Gera_Compromisso_Ordem_Abastecimento) {
		this.dm_Gera_Compromisso_Ordem_Abastecimento = dm_Gera_Compromisso_Ordem_Abastecimento;
	}
	public String getDm_Gera_Compromisso_Ordem_Frete() {
		return dm_Gera_Compromisso_Ordem_Frete;
	}
	public void setDm_Gera_Compromisso_Ordem_Frete(
			String dm_Gera_Compromisso_Ordem_Frete) {
		this.dm_Gera_Compromisso_Ordem_Frete = dm_Gera_Compromisso_Ordem_Frete;
	}
	public String getDm_Gera_Compromisso_Ordem_Frete_Adiantamento() {
		return dm_Gera_Compromisso_Ordem_Frete_Adiantamento;
	}
	public void setDm_Gera_Compromisso_Ordem_Frete_Adiantamento(
			String dm_Gera_Compromisso_Ordem_Frete_Adiantamento) {
		this.dm_Gera_Compromisso_Ordem_Frete_Adiantamento = dm_Gera_Compromisso_Ordem_Frete_Adiantamento;
	}
	public String getDm_Gera_Compromisso_Viagem() {
		return dm_Gera_Compromisso_Viagem;
	}
	public void setDm_Gera_Compromisso_Viagem(String dm_Gera_Compromisso_Viagem) {
		this.dm_Gera_Compromisso_Viagem = dm_Gera_Compromisso_Viagem;
	}
	public String getDm_Gera_Custo_Viagem() {
		return dm_Gera_Custo_Viagem;
	}
	public void setDm_Gera_Custo_Viagem(String dm_Gera_Custo_Viagem) {
		this.dm_Gera_Custo_Viagem = dm_Gera_Custo_Viagem;
	}
	public String getDm_Gera_Embarque_Viagem() {
		return dm_Gera_Embarque_Viagem;
	}
	public void setDm_Gera_Embarque_Viagem(String dm_Gera_Embarque_Viagem) {
		this.dm_Gera_Embarque_Viagem = dm_Gera_Embarque_Viagem;
	}
	public String getDm_Gera_Lancamento_Conta_Corrente_Acerto_Contas() {
		return dm_Gera_Lancamento_Conta_Corrente_Acerto_Contas;
	}
	public void setDm_Gera_Lancamento_Conta_Corrente_Acerto_Contas(
			String dm_Gera_Lancamento_Conta_Corrente_Acerto_Contas) {
		this.dm_Gera_Lancamento_Conta_Corrente_Acerto_Contas = dm_Gera_Lancamento_Conta_Corrente_Acerto_Contas;
	}
	public String getDm_Gera_Lancamento_Conta_Corrente_Ordem_Frete() {
		return dm_Gera_Lancamento_Conta_Corrente_Ordem_Frete;
	}
	public void setDm_Gera_Lancamento_Conta_Corrente_Ordem_Frete(
			String dm_Gera_Lancamento_Conta_Corrente_Ordem_Frete) {
		this.dm_Gera_Lancamento_Conta_Corrente_Ordem_Frete = dm_Gera_Lancamento_Conta_Corrente_Ordem_Frete;
	}
	public String getDm_Gera_Lancamento_Conta_Corrente_Ordem_Frete_Adiantamento() {
		return dm_Gera_Lancamento_Conta_Corrente_Ordem_Frete_Adiantamento;
	}
	public void setDm_Gera_Lancamento_Conta_Corrente_Ordem_Frete_Adiantamento(
			String dm_Gera_Lancamento_Conta_Corrente_Ordem_Frete_Adiantamento) {
		this.dm_Gera_Lancamento_Conta_Corrente_Ordem_Frete_Adiantamento = dm_Gera_Lancamento_Conta_Corrente_Ordem_Frete_Adiantamento;
	}
	public String getDm_Gera_Lancamento_Conta_Corrente_Ordem_Frete_Terceiro() {
		return dm_Gera_Lancamento_Conta_Corrente_Ordem_Frete_Terceiro;
	}
	public void setDm_Gera_Lancamento_Conta_Corrente_Ordem_Frete_Terceiro(
			String dm_Gera_Lancamento_Conta_Corrente_Ordem_Frete_Terceiro) {
		this.dm_Gera_Lancamento_Conta_Corrente_Ordem_Frete_Terceiro = dm_Gera_Lancamento_Conta_Corrente_Ordem_Frete_Terceiro;
	}
	public String getDm_Gera_Lancamento_Contabil() {
		return dm_Gera_Lancamento_Contabil;
	}
	public void setDm_Gera_Lancamento_Contabil(String dm_Gera_Lancamento_Contabil) {
		this.dm_Gera_Lancamento_Contabil = dm_Gera_Lancamento_Contabil;
	}
	public String getDm_Gera_Livro_Fiscal() {
		return dm_Gera_Livro_Fiscal;
	}
	public void setDm_Gera_Livro_Fiscal(String dm_Gera_Livro_Fiscal) {
		this.dm_Gera_Livro_Fiscal = dm_Gera_Livro_Fiscal;
	}
	public String getDm_Gera_Ocorrencia_Viagem() {
		return dm_Gera_Ocorrencia_Viagem;
	}
	public void setDm_Gera_Ocorrencia_Viagem(String dm_Gera_Ocorrencia_Viagem) {
		this.dm_Gera_Ocorrencia_Viagem = dm_Gera_Ocorrencia_Viagem;
	}
	public String getDm_Gera_Rateio_Custo_Ordem_Frete() {
		return dm_Gera_Rateio_Custo_Ordem_Frete;
	}
	public void setDm_Gera_Rateio_Custo_Ordem_Frete(
			String dm_Gera_Rateio_Custo_Ordem_Frete) {
		this.dm_Gera_Rateio_Custo_Ordem_Frete = dm_Gera_Rateio_Custo_Ordem_Frete;
	}
	public String getDm_Atualiza_Movimento_Caixa() {
		return dm_Atualiza_Movimento_Caixa;
	}
	public void setDm_Atualiza_Movimento_Caixa(String dm_Atualiza_Movimento_Caixa) {
		this.dm_Atualiza_Movimento_Caixa = dm_Atualiza_Movimento_Caixa;
	}
	public String getDm_Conhecimento_Varios_Manifestos() {
		return dm_Conhecimento_Varios_Manifestos;
	}
	public void setDm_Conhecimento_Varios_Manifestos(
			String dm_Conhecimento_Varios_Manifestos) {
		this.dm_Conhecimento_Varios_Manifestos = dm_Conhecimento_Varios_Manifestos;
	}
	public String getDm_Liquida_Compromisso() {
		return dm_Liquida_Compromisso;
	}
	public void setDm_Liquida_Compromisso(String dm_Liquida_Compromisso) {
		this.dm_Liquida_Compromisso = dm_Liquida_Compromisso;
	}
	public String getDm_Tranca_Saldo_Ordem_Frete() {
		return dm_Tranca_Saldo_Ordem_Frete;
	}
	public void setDm_Tranca_Saldo_Ordem_Frete(String dm_Tranca_Saldo_Ordem_Frete) {
		this.dm_Tranca_Saldo_Ordem_Frete = dm_Tranca_Saldo_Ordem_Frete;
	}
	
//============ Getters and Setters Tela Aliquota ===============================================================================\\
	public double getPe_Aliquota_COFINS() {
		return pe_Aliquota_COFINS;
	}
	public void setPe_Aliquota_COFINS(double pe_Aliquota_COFINS) {
		this.pe_Aliquota_COFINS = pe_Aliquota_COFINS;
	}
	public double getPe_Aliquota_CSLL() {
		return pe_Aliquota_CSLL;
	}
	public void setPe_Aliquota_CSLL(double pe_Aliquota_CSLL) {
		this.pe_Aliquota_CSLL = pe_Aliquota_CSLL;
	}
	public double getPe_Aliquota_Empresa_INSS() {
		return pe_Aliquota_Empresa_INSS;
	}
	public void setPe_Aliquota_Empresa_INSS(double pe_Aliquota_Empresa_INSS) {
		this.pe_Aliquota_Empresa_INSS = pe_Aliquota_Empresa_INSS;
	}
	public double getPe_Aliquota_PIS() {
		return pe_Aliquota_PIS;
	}
	public void setPe_Aliquota_PIS(double pe_Aliquota_PIS) {
		this.pe_Aliquota_PIS = pe_Aliquota_PIS;
	}
	public double getPe_Aliquota_PIS_COFINS() {
		return pe_Aliquota_PIS_COFINS;
	}
	public void setPe_Aliquota_PIS_COFINS(double pe_Aliquota_PIS_COFINS) {
		this.pe_Aliquota_PIS_COFINS = pe_Aliquota_PIS_COFINS;
	}
	public double getPe_Aliquota_Prestador_INSS() {
		return pe_Aliquota_Prestador_INSS;
	}
	public void setPe_Aliquota_Prestador_INSS(double pe_Aliquota_Prestador_INSS) {
		this.pe_Aliquota_Prestador_INSS = pe_Aliquota_Prestador_INSS;
	}
	public double getPe_Aliquota_Prestador_Set_Senat() {
		return pe_Aliquota_Prestador_Set_Senat;
	}
	public void setPe_Aliquota_Prestador_Set_Senat(
		double pe_Aliquota_Prestador_Set_Senat) {
		this.pe_Aliquota_Prestador_Set_Senat = pe_Aliquota_Prestador_Set_Senat;
	}
	public double getPe_Base_Calculo() {
		return pe_Base_Calculo;
	}
	public void setPe_Base_Calculo(double pe_Base_Calculo) {
		this.pe_Base_Calculo = pe_Base_Calculo;
	}
	public double getPe_Base_Comissao_Motorista() {
		return pe_Base_Comissao_Motorista;
	}
	public void setPe_Base_Comissao_Motorista(double pe_Base_Comissao_Motorista) {
		this.pe_Base_Comissao_Motorista = pe_Base_Comissao_Motorista;
	}
	public double getPe_Base_INSS() {
		return pe_Base_INSS;
	}
	public void setPe_Base_INSS(double pe_Base_INSS) {
		this.pe_Base_INSS = pe_Base_INSS;
	}
	public double getPe_Base_SET_SENAT() {
		return pe_Base_SET_SENAT;
	}
	public void setPe_Base_SET_SENAT(double pe_Base_SET_SENAT) {
		this.pe_Base_SET_SENAT = pe_Base_SET_SENAT;
	}
	public double getPe_Comissao_Motorista() {
		return pe_Comissao_Motorista;
	}
	public void setPe_Comissao_Motorista(double pe_Comissao_Motorista) {
		this.pe_Comissao_Motorista = pe_Comissao_Motorista;
	}
	public double getPe_Comissao_Motorista_Media() {
		return pe_Comissao_Motorista_Media;
	}
	public void setPe_Comissao_Motorista_Media(double pe_Comissao_Motorista_Media) {
		this.pe_Comissao_Motorista_Media = pe_Comissao_Motorista_Media;
	}
	public double getPe_Imposto_Faixa1() {
		return pe_Imposto_Faixa1;
	}
	public void setPe_Imposto_Faixa1(double pe_Imposto_Faixa1) {
		this.pe_Imposto_Faixa1 = pe_Imposto_Faixa1;
	}
	public double getPe_Imposto_Faixa2() {
		return pe_Imposto_Faixa2;
	}
	public void setPe_Imposto_Faixa2(double pe_Imposto_Faixa2) {
		this.pe_Imposto_Faixa2 = pe_Imposto_Faixa2;
	}
	public double getVl_Deducao_Faixa1() {
		return vl_Deducao_Faixa1;
	}
	public void setVl_Deducao_Faixa1(double vl_Deducao_Faixa1) {
		this.vl_Deducao_Faixa1 = vl_Deducao_Faixa1;
	}
	public double getVl_Deducao_Faixa2() {
		return vl_Deducao_Faixa2;
	}
	public void setVl_Deducao_Faixa2(double vl_Deducao_Faixa2) {
		this.vl_Deducao_Faixa2 = vl_Deducao_Faixa2;
	}
	public double getVl_Dependente() {
		return vl_Dependente;
	}
	public void setVl_Dependente(double vl_Dependente) {
		this.vl_Dependente = vl_Dependente;
	}
	public double getVl_Faixa1() {
		return vl_Faixa1;
	}
	public void setVl_Faixa1(double vl_Faixa1) {
		this.vl_Faixa1 = vl_Faixa1;
	}
	public double getVl_Faixa2() {
		return vl_Faixa2;
	}
	public void setVl_Faixa2(double vl_Faixa2) {
		this.vl_Faixa2 = vl_Faixa2;
	}
	public double getVl_Frete_Cortesia() {
		return vl_Frete_Cortesia;
	}
	public void setVl_Frete_Cortesia(double vl_Frete_Cortesia) {
		this.vl_Frete_Cortesia = vl_Frete_Cortesia;
	}
	public double getVl_Maximo_INSS() {
		return vl_Maximo_INSS;
	}
	public void setVl_Maximo_INSS(double vl_Maximo_INSS) {
		this.vl_Maximo_INSS = vl_Maximo_INSS;
	}
	public String getVl_Reembolso() {
		return vl_Reembolso;
	}
	public void setVl_Reembolso(String vl_Reembolso) {
		this.vl_Reembolso = vl_Reembolso;
	}
		
	//============ Getters and Setters Base Dados ============================================================================\\
	public String getAppPath() {
		return appPath;
	}
	public void setAppPath(String appPath) {
		this.appPath = appPath;
	}
	public String getDocument_Form_Action() {
		return document_Form_Action;
	}
	public void setDocument_Form_Action(String document_Form_Action) {
		this.document_Form_Action = document_Form_Action;
	}
	public String getDt_Hoje() {
		return dt_Hoje;
	}
	public void setDt_Hoje(String dt_Hoje) {
		this.dt_Hoje = dt_Hoje;
	}
	public String getHr_Hoje() {
		return hr_Hoje;
	}
	public void setHr_Hoje(String hr_Hoje) {
		this.hr_Hoje = hr_Hoje;
	}
	public String getNm_Versao() {
		return nm_Versao;
	}
	public void setNm_Versao(String nm_Versao) {
		this.nm_Versao = nm_Versao;
	}
	public String getPalmModelo() {
		return palmModelo;
	}
	public void setPalmModelo(String palmModelo) {
		this.palmModelo = palmModelo;
	}
	public String getPalmPath() {
		return palmPath;
	}
	public void setPalmPath(String palmPath) {
		this.palmPath = palmPath;
	}
	public String getPath_imagens() {
		return path_imagens;
	}
	public void setPath_imagens(String path_imagens) {
		this.path_imagens = path_imagens;
	}
	public String getPath_Relatorios() {
		return path_Relatorios;
	}
	public void setPath_Relatorios(String path_Relatorios) {
		this.path_Relatorios = path_Relatorios;
	}
	public String getPath_Relatorios_XSL() {
		return path_Relatorios_XSL;
	}
	public void setPath_Relatorios_XSL(String path_Relatorios_XSL) {
		this.path_Relatorios_XSL = path_Relatorios_XSL;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public String getLogTransactions() {
		return logTransactions;
	}
	public void setLogTransactions(String logTransactions) {
		this.logTransactions = logTransactions;
	}
	public String getContexto() {
		return contexto;
	}
	public void setContexto(String contexto) {
		this.contexto = contexto;
	}
	public long getPort() {
		return port;
	}
	public void setPort(long port) {
		this.port = port;
	}
	public String getWebmaster() {
		return webmaster;
	}
	public void setWebmaster(String webmaster) {
		this.webmaster = webmaster;
	}
	
	
//============ Getters and Setters Calculos ================================================================================\\
	public String getDm_Calcula_INSS() {
		return dm_Calcula_INSS;
	}
	public void setDm_Calcula_INSS(String dm_Calcula_INSS) {
		this.dm_Calcula_INSS = dm_Calcula_INSS;
	}
	public String getDm_Calcula_IRRF() {
		return dm_Calcula_IRRF;
	}
	public void setDm_Calcula_IRRF(String dm_Calcula_IRRF) {
		this.dm_Calcula_IRRF = dm_Calcula_IRRF;
	}
	public String getDm_Calcula_PIS_COFINS() {
		return dm_Calcula_PIS_COFINS;
	}
	public void setDm_Calcula_PIS_COFINS(String dm_Calcula_PIS_COFINS) {
		this.dm_Calcula_PIS_COFINS = dm_Calcula_PIS_COFINS;
	}
	public String getDm_Soma_Outros_Saldo_Frete() {
		return dm_Soma_Outros_Saldo_Frete;
	}
	public void setDm_Soma_Outros_Saldo_Frete(String dm_Soma_Outros_Saldo_Frete) {
		this.dm_Soma_Outros_Saldo_Frete = dm_Soma_Outros_Saldo_Frete;
	}
	public String getDm_Soma_Pedagio_Saldo_Frete() {
		return dm_Soma_Pedagio_Saldo_Frete;
	}
	public void setDm_Soma_Pedagio_Saldo_Frete(String dm_Soma_Pedagio_Saldo_Frete) {
		this.dm_Soma_Pedagio_Saldo_Frete = dm_Soma_Pedagio_Saldo_Frete;
	}
	public String getDm_Soma_Sest_Senat_Saldo_Frete() {
		return dm_Soma_Sest_Senat_Saldo_Frete;
	}
	public void setDm_Soma_Sest_Senat_Saldo_Frete(
			String dm_Soma_Sest_Senat_Saldo_Frete) {
		this.dm_Soma_Sest_Senat_Saldo_Frete = dm_Soma_Sest_Senat_Saldo_Frete;
	}
	public String getDm_Tipo_Calculo_ICMS() {
		return dm_Tipo_Calculo_ICMS;
	}
	public void setDm_Tipo_Calculo_ICMS(String dm_Tipo_Calculo_ICMS) {
		this.dm_Tipo_Calculo_ICMS = dm_Tipo_Calculo_ICMS;
	}

//============ Getters and Setters Tela Carteira ===================================================================\\
	public long getOid_Carteira_Faturamento() {
		return oid_Carteira_Faturamento;
	}
	public void setOid_Carteira_Faturamento(long carteira_Faturamento) {
		oid_Carteira_Faturamento = carteira_Faturamento;
	}
	public long getOid_Carteira_Nota_Retorno() {
		return oid_Carteira_Nota_Retorno;
	}
	public void setOid_Carteira_Nota_Retorno(long carteira_Nota_Retorno) {
		oid_Carteira_Nota_Retorno = carteira_Nota_Retorno;
	}

//============ Getters and Setters Tela Centro de Custo ==============================================================\\	
	public long getOid_Centro_Custo_Entrega() {
		return oid_Centro_Custo_Entrega;
	}
	public void setOid_Centro_Custo_Entrega(long oid_Centro_Custo_Entrega) {
		this.oid_Centro_Custo_Entrega = oid_Centro_Custo_Entrega;
	}
	public long getOid_Centro_Custo_Master() {
		return oid_Centro_Custo_Master;
	}
	public void setOid_Centro_Custo_Master(long oid_Centro_Custo_Master) {
		this.oid_Centro_Custo_Master = oid_Centro_Custo_Master;
	}
	public long getOid_Centro_Custo_Movimento_Ordem_Servico() {
		return oid_Centro_Custo_Movimento_Ordem_Servico;
	}
	public void setOid_Centro_Custo_Movimento_Ordem_Servico(
			long oid_Centro_Custo_Movimento_Ordem_Servico) {
		this.oid_Centro_Custo_Movimento_Ordem_Servico = oid_Centro_Custo_Movimento_Ordem_Servico;
	}
	public long getOid_Centro_Custo_Ordem_Frete() {
		return oid_Centro_Custo_Ordem_Frete;
	}
	public void setOid_Centro_Custo_Ordem_Frete(long oid_Centro_Custo_Ordem_Frete) {
		this.oid_Centro_Custo_Ordem_Frete = oid_Centro_Custo_Ordem_Frete;
	}
	
//============ Getters and Setters CFOP/ TAXAS ================================\\ 
	public String getCFOP_Padrao() {
		return CFOP_Padrao;
	}
	public void setCFOP_Padrao(String padrao) {
		CFOP_Padrao = padrao;
	}
	public String getDm_Estado_Destino_CFOP() {
		return dm_Estado_Destino_CFOP;
	}
	public void setDm_Estado_Destino_CFOP(String dm_Estado_Destino_CFOP) {
		this.dm_Estado_Destino_CFOP = dm_Estado_Destino_CFOP;
	}
	public String getDm_Estado_Destino_Taxas() {
		return dm_Estado_Destino_Taxas;
	}
	public void setDm_Estado_Destino_Taxas(String dm_Estado_Destino_Taxas) {
		this.dm_Estado_Destino_Taxas = dm_Estado_Destino_Taxas;
	}
	public String getDm_Estado_Origem_CFOP() {
		return dm_Estado_Origem_CFOP;
	}
	public void setDm_Estado_Origem_CFOP(String dm_Estado_Origem_CFOP) {
		this.dm_Estado_Origem_CFOP = dm_Estado_Origem_CFOP;
	}
	public String getDm_Estado_Origem_Taxas() {
		return dm_Estado_Origem_Taxas;
	}
	public void setDm_Estado_Origem_Taxas(String dm_Estado_Origem_Taxas) {
		this.dm_Estado_Origem_Taxas = dm_Estado_Origem_Taxas;
	}
	public long getOid_CFOP_Diferenciado() {
		return oid_CFOP_Diferenciado;
	}
	public void setOid_CFOP_Diferenciado(long oid_CFOP_Diferenciado) {
		this.oid_CFOP_Diferenciado = oid_CFOP_Diferenciado;
	}
	public long getOid_CFOP_Pessoa_Fisica() {
		return oid_CFOP_Pessoa_Fisica;
	}
	public void setOid_CFOP_Pessoa_Fisica(long oid_CFOP_Pessoa_Fisica) {
		this.oid_CFOP_Pessoa_Fisica = oid_CFOP_Pessoa_Fisica;
	}
	
//============ Getters and Setters Tela Contas ====================================================================================\\
	public long getOid_Conta_Credito_Master() {
		return oid_Conta_Credito_Master;
	}
	public void setOid_Conta_Credito_Master(long oid_Conta_Credito_Master) {
		this.oid_Conta_Credito_Master = oid_Conta_Credito_Master;
	}
	public long getOid_Conta_Credito_Pedagio() {
		return oid_Conta_Credito_Pedagio;
	}
	public void setOid_Conta_Credito_Pedagio(long oid_Conta_Credito_Pedagio) {
		this.oid_Conta_Credito_Pedagio = oid_Conta_Credito_Pedagio;
	}
	public long getOid_Conta_Debito_Master() {
		return oid_Conta_Debito_Master;
	}
	public void setOid_Conta_Debito_Master(long oid_Conta_Debito_Master) {
		this.oid_Conta_Debito_Master = oid_Conta_Debito_Master;
	}
	public long getOid_Conta_Debito_Pedagio() {
		return oid_Conta_Debito_Pedagio;
	}
	public void setOid_Conta_Debito_Pedagio(long oid_Conta_Debito_Pedagio) {
		this.oid_Conta_Debito_Pedagio = oid_Conta_Debito_Pedagio;
	}
	public long getOid_Conta_Acerto_Contas() {
		return oid_Conta_Acerto_Contas;
	}
	public void setOid_Conta_Acerto_Contas(long oid_Conta_Acerto_Contas) {
		this.oid_Conta_Acerto_Contas = oid_Conta_Acerto_Contas;
	}
	public long getOid_Conta_Credito_Entrega() {
		return oid_Conta_Credito_Entrega;
	}
	public void setOid_Conta_Credito_Entrega(long oid_Conta_Credito_Entrega) {
		this.oid_Conta_Credito_Entrega = oid_Conta_Credito_Entrega;
	}
	public long getOid_Conta_Credito_Movimento_Ordem_Servico() {
		return oid_Conta_Credito_Movimento_Ordem_Servico;
	}
	public void setOid_Conta_Credito_Movimento_Ordem_Servico(
			long oid_Conta_Credito_Movimento_Ordem_Servico) {
		this.oid_Conta_Credito_Movimento_Ordem_Servico = oid_Conta_Credito_Movimento_Ordem_Servico;
	}
	public long getOid_Conta_Credito_Ordem_Frete() {
		return oid_Conta_Credito_Ordem_Frete;
	}
	public void setOid_Conta_Credito_Ordem_Frete(long oid_Conta_Credito_Ordem_Frete) {
		this.oid_Conta_Credito_Ordem_Frete = oid_Conta_Credito_Ordem_Frete;
	}
	public long getOid_Conta_Credito_Ordem_Frete_Adiantamento() {
		return oid_Conta_Credito_Ordem_Frete_Adiantamento;
	}
	public void setOid_Conta_Credito_Ordem_Frete_Adiantamento(
			long oid_Conta_Credito_Ordem_Frete_Adiantamento) {
		this.oid_Conta_Credito_Ordem_Frete_Adiantamento = oid_Conta_Credito_Ordem_Frete_Adiantamento;
	}
	public long getOid_Conta_Debito_Entrega() {
		return oid_Conta_Debito_Entrega;
	}
	public void setOid_Conta_Debito_Entrega(long oid_Conta_Debito_Entrega) {
		this.oid_Conta_Debito_Entrega = oid_Conta_Debito_Entrega;
	}
	public long getOid_Conta_Desconto_Frete() {
		return oid_Conta_Desconto_Frete;
	}
	public void setOid_Conta_Desconto_Frete(long oid_Conta_Desconto_Frete) {
		this.oid_Conta_Desconto_Frete = oid_Conta_Desconto_Frete;
	}
	public long getOid_Conta_Despesa_Bancaria() {
		return oid_Conta_Despesa_Bancaria;
	}
	public void setOid_Conta_Despesa_Bancaria(long oid_Conta_Despesa_Bancaria) {
		this.oid_Conta_Despesa_Bancaria = oid_Conta_Despesa_Bancaria;
	}
	public long getOid_Conta_Frete_Terceiros() {
		return oid_Conta_Frete_Terceiros;
	}
	public void setOid_Conta_Frete_Terceiros(long oid_Conta_Frete_Terceiros) {
		this.oid_Conta_Frete_Terceiros = oid_Conta_Frete_Terceiros;
	}
	public long getOid_Conta_INSS_Ordem_Frete() {
		return oid_Conta_INSS_Ordem_Frete;
	}
	public void setOid_Conta_INSS_Ordem_Frete(long oid_Conta_INSS_Ordem_Frete) {
		this.oid_Conta_INSS_Ordem_Frete = oid_Conta_INSS_Ordem_Frete;
	}
	public long getOid_Conta_IR_Ordem_Frete() {
		return oid_Conta_IR_Ordem_Frete;
	}
	public void setOid_Conta_IR_Ordem_Frete(long oid_Conta_IR_Ordem_Frete) {
		this.oid_Conta_IR_Ordem_Frete = oid_Conta_IR_Ordem_Frete;
	}
	public long getOid_Conta_Juridica_Ordem_Frete() {
		return oid_Conta_Juridica_Ordem_Frete;
	}
	public void setOid_Conta_Juridica_Ordem_Frete(
			long oid_Conta_Juridica_Ordem_Frete) {
		this.oid_Conta_Juridica_Ordem_Frete = oid_Conta_Juridica_Ordem_Frete;
	}
	public long getOid_Conta_Movimento_Compromisso() {
		return oid_Conta_Movimento_Compromisso;
	}
	public void setOid_Conta_Movimento_Compromisso(
			long oid_Conta_Movimento_Compromisso) {
		this.oid_Conta_Movimento_Compromisso = oid_Conta_Movimento_Compromisso;
	}
	public long getOid_Conta_Movimento_Ordem_Servico() {
		return oid_Conta_Movimento_Ordem_Servico;
	}
	public void setOid_Conta_Movimento_Ordem_Servico(
			long oid_Conta_Movimento_Ordem_Servico) {
		this.oid_Conta_Movimento_Ordem_Servico = oid_Conta_Movimento_Ordem_Servico;
	}
	public long getOid_Conta_Multa_Transito() {
		return oid_Conta_Multa_Transito;
	}
	public void setOid_Conta_Multa_Transito(long oid_Conta_Multa_Transito) {
		this.oid_Conta_Multa_Transito = oid_Conta_Multa_Transito;
	}
	public long getOid_Conta_Ordem_Frete() {
		return oid_Conta_Ordem_Frete;
	}
	public void setOid_Conta_Ordem_Frete(long oid_Conta_Ordem_Frete) {
		this.oid_Conta_Ordem_Frete = oid_Conta_Ordem_Frete;
	}
	public long getOid_Conta_Ordem_Frete_Adiantamento() {
		return oid_Conta_Ordem_Frete_Adiantamento;
	}
	public void setOid_Conta_Ordem_Frete_Adiantamento(
			long oid_Conta_Ordem_Frete_Adiantamento) {
		this.oid_Conta_Ordem_Frete_Adiantamento = oid_Conta_Ordem_Frete_Adiantamento;
	}
	
//============ Getters and Setters Tela Descri��o ================================================================================\\	
	public String getDm_Multi_Moeda() {
		return dm_Multi_Moeda;
	}
	public void setDm_Multi_Moeda(String dm_Multi_Moeda) {
		this.dm_Multi_Moeda = dm_Multi_Moeda;
	}
	public String getDm_Saldo_Programado() {
		return dm_Saldo_Programado;
	}
	public void setDm_Saldo_Programado(String dm_Saldo_Programado) {
		this.dm_Saldo_Programado = dm_Saldo_Programado;
	}
	public long getOid_Condicao_Pagamento_Isento() {
		return oid_Condicao_Pagamento_Isento;
	}
	public void setOid_Condicao_Pagamento_Isento(long oid_Condicao_Pagamento_Isento) {
		this.oid_Condicao_Pagamento_Isento = oid_Condicao_Pagamento_Isento;
	}
	public String getTexto_Imprimir_Campo_Nao_Validado() {
		return texto_Imprimir_Campo_Nao_Validado;
	}
	public void setTexto_Imprimir_Campo_Nao_Validado(
			String texto_Imprimir_Campo_Nao_Validado) {
		this.texto_Imprimir_Campo_Nao_Validado = texto_Imprimir_Campo_Nao_Validado;
	}
	public long getVias_Fatura() {
		return vias_Fatura;
	}
	public void setVias_Fatura(long vias_Fatura) {
		this.vias_Fatura = vias_Fatura;
	}
	
//============ Getters and Setters Tela Documento ================================================================================\\	
	public long getOid_Tipo_Documento_Entrega() {
		return oid_Tipo_Documento_Entrega;
	}
	public void setOid_Tipo_Documento_Entrega(long oid_Tipo_Documento_Entrega) {
		this.oid_Tipo_Documento_Entrega = oid_Tipo_Documento_Entrega;
	}
	public long getOid_Tipo_Documento_Faturamento() {
		return oid_Tipo_Documento_Faturamento;
	}
	public void setOid_Tipo_Documento_Faturamento(
			long oid_Tipo_Documento_Faturamento) {
		this.oid_Tipo_Documento_Faturamento = oid_Tipo_Documento_Faturamento;
	}
	public long getOid_Tipo_Documento_Faturamento_Internacional() {
		return oid_Tipo_Documento_Faturamento_Internacional;
	}
	public void setOid_Tipo_Documento_Faturamento_Internacional(
			long oid_Tipo_Documento_Faturamento_Internacional) {
		this.oid_Tipo_Documento_Faturamento_Internacional = oid_Tipo_Documento_Faturamento_Internacional;
	}
	public long getOid_Tipo_Documento_Faturamento_Nota_Fiscal_Servico() {
		return oid_Tipo_Documento_Faturamento_Nota_Fiscal_Servico;
	}
	public void setOid_Tipo_Documento_Faturamento_Nota_Fiscal_Servico(
			long oid_Tipo_Documento_Faturamento_Nota_Fiscal_Servico) {
		this.oid_Tipo_Documento_Faturamento_Nota_Fiscal_Servico = oid_Tipo_Documento_Faturamento_Nota_Fiscal_Servico;
	}
	public long getOid_Tipo_Documento_INSS() {
		return oid_Tipo_Documento_INSS;
	}
	public void setOid_Tipo_Documento_INSS(long oid_Tipo_Documento_INSS) {
		this.oid_Tipo_Documento_INSS = oid_Tipo_Documento_INSS;
	}
	public long getOid_Tipo_Documento_IR() {
		return oid_Tipo_Documento_IR;
	}
	public void setOid_Tipo_Documento_IR(long oid_Tipo_Documento_IR) {
		this.oid_Tipo_Documento_IR = oid_Tipo_Documento_IR;
	}
	public long getOid_Tipo_Documento_Master() {
		return oid_Tipo_Documento_Master;
	}
	public void setOid_Tipo_Documento_Master(long oid_Tipo_Documento_Master) {
		this.oid_Tipo_Documento_Master = oid_Tipo_Documento_Master;
	}
	public long getOid_Tipo_Documento_Nota_Fiscal_Entrada() {
		return oid_Tipo_Documento_Nota_Fiscal_Entrada;
	}
	public void setOid_Tipo_Documento_Nota_Fiscal_Entrada(
			long oid_Tipo_Documento_Nota_Fiscal_Entrada) {
		this.oid_Tipo_Documento_Nota_Fiscal_Entrada = oid_Tipo_Documento_Nota_Fiscal_Entrada;
	}
	public long getOid_Tipo_Documento_Nota_Fiscal_Saida() {
		return oid_Tipo_Documento_Nota_Fiscal_Saida;
	}
	public void setOid_Tipo_Documento_Nota_Fiscal_Saida(
			long oid_Tipo_Documento_Nota_Fiscal_Saida) {
		this.oid_Tipo_Documento_Nota_Fiscal_Saida = oid_Tipo_Documento_Nota_Fiscal_Saida;
	}
	public long getOid_Tipo_Documento_Ordem_Frete() {
		return oid_Tipo_Documento_Ordem_Frete;
	}
	public void setOid_Tipo_Documento_Ordem_Frete(
			long oid_Tipo_Documento_Ordem_Frete) {
		this.oid_Tipo_Documento_Ordem_Frete = oid_Tipo_Documento_Ordem_Frete;
	}
	public long getOid_Tipo_Documento_Ordem_Frete_Adiantamento() {
		return oid_Tipo_Documento_Ordem_Frete_Adiantamento;
	}
	public void setOid_Tipo_Documento_Ordem_Frete_Adiantamento(
			long oid_Tipo_Documento_Ordem_Frete_Adiantamento) {
		this.oid_Tipo_Documento_Ordem_Frete_Adiantamento = oid_Tipo_Documento_Ordem_Frete_Adiantamento;
	}
	public long getOid_Tipo_Documento_Ordem_Servico() {
		return oid_Tipo_Documento_Ordem_Servico;
	}
	public void setOid_Tipo_Documento_Ordem_Servico(
			long oid_Tipo_Documento_Ordem_Servico) {
		this.oid_Tipo_Documento_Ordem_Servico = oid_Tipo_Documento_Ordem_Servico;
	}
	public long getOid_Tipo_Documento_Transferencia_Conta_Corrente() {
		return oid_Tipo_Documento_Transferencia_Conta_Corrente;
	}
	public void setOid_Tipo_Documento_Transferencia_Conta_Corrente(
			long oid_Tipo_Documento_Transferencia_Conta_Corrente) {
		this.oid_Tipo_Documento_Transferencia_Conta_Corrente = oid_Tipo_Documento_Transferencia_Conta_Corrente;
	}
	
//============ Getters and Setters Tela Faturamento ==========================================================================\\	
	public String getDm_Quebra_Faturamento_Tipo_Conhecimento() {
		return dm_Quebra_Faturamento_Tipo_Conhecimento;
	}
	public void setDm_Quebra_Faturamento_Tipo_Conhecimento(
			String dm_Quebra_Faturamento_Tipo_Conhecimento) {
		this.dm_Quebra_Faturamento_Tipo_Conhecimento = dm_Quebra_Faturamento_Tipo_Conhecimento;
	}
	public String getDm_Quebra_Faturamento_Tipo_Faturamento() {
		return dm_Quebra_Faturamento_Tipo_Faturamento;
	}
	public void setDm_Quebra_Faturamento_Tipo_Faturamento(
			String dm_Quebra_Faturamento_Tipo_Faturamento) {
		this.dm_Quebra_Faturamento_Tipo_Faturamento = dm_Quebra_Faturamento_Tipo_Faturamento;
	}
	public String getDm_Quebra_Faturamento_Unidade() {
		return dm_Quebra_Faturamento_Unidade;
	}
	public void setDm_Quebra_Faturamento_Unidade(
			String dm_Quebra_Faturamento_Unidade) {
		this.dm_Quebra_Faturamento_Unidade = dm_Quebra_Faturamento_Unidade;
	}
	
//============ Getters and Setters Tela Formularios  ================================================================================\\
	public String getDm_Formulario_Acerto_Contas() {
		return dm_Formulario_Acerto_Contas;
	}
	public void setDm_Formulario_Acerto_Contas(String dm_Formulario_Acerto_Contas) {
		this.dm_Formulario_Acerto_Contas = dm_Formulario_Acerto_Contas;
	}
	public String getDm_Formulario_ACT() {
		return dm_Formulario_ACT;
	}
	public void setDm_Formulario_ACT(String dm_Formulario_ACT) {
		this.dm_Formulario_ACT = dm_Formulario_ACT;
	}
	public String getDm_Formulario_Coleta() {
		return dm_Formulario_Coleta;
	}
	public void setDm_Formulario_Coleta(String dm_Formulario_Coleta) {
		this.dm_Formulario_Coleta = dm_Formulario_Coleta;
	}
	public String getDm_Formulario_Conhecimento_Internacional() {
		return dm_Formulario_Conhecimento_Internacional;
	}
	public void setDm_Formulario_Conhecimento_Internacional(
			String dm_Formulario_Conhecimento_Internacional) {
		this.dm_Formulario_Conhecimento_Internacional = dm_Formulario_Conhecimento_Internacional;
	}
	public String getDm_Formulario_Conhecimento_Internacional_Verso() {
		return dm_Formulario_Conhecimento_Internacional_Verso;
	}
	public void setDm_Formulario_Conhecimento_Internacional_Verso(
			String dm_Formulario_Conhecimento_Internacional_Verso) {
		this.dm_Formulario_Conhecimento_Internacional_Verso = dm_Formulario_Conhecimento_Internacional_Verso;
	}
	public String getDm_Formulario_Conhecimento_Nacional() {
		return dm_Formulario_Conhecimento_Nacional;
	}
	public void setDm_Formulario_Conhecimento_Nacional(
			String dm_Formulario_Conhecimento_Nacional) {
		this.dm_Formulario_Conhecimento_Nacional = dm_Formulario_Conhecimento_Nacional;
	}
	public String getDm_Formulario_Consolidacao_MIC() {
		return dm_Formulario_Consolidacao_MIC;
	}
	public void setDm_Formulario_Consolidacao_MIC(
			String dm_Formulario_Consolidacao_MIC) {
		this.dm_Formulario_Consolidacao_MIC = dm_Formulario_Consolidacao_MIC;
	}
	public String getDm_Formulario_Consolidacao_MIC_CRT() {
		return dm_Formulario_Consolidacao_MIC_CRT;
	}
	public void setDm_Formulario_Consolidacao_MIC_CRT(
			String dm_Formulario_Consolidacao_MIC_CRT) {
		this.dm_Formulario_Consolidacao_MIC_CRT = dm_Formulario_Consolidacao_MIC_CRT;
	}
	public String getDm_Formulario_Demonstrativo_Cobranca() {
		return dm_Formulario_Demonstrativo_Cobranca;
	}
	public void setDm_Formulario_Demonstrativo_Cobranca(
			String dm_Formulario_Demonstrativo_Cobranca) {
		this.dm_Formulario_Demonstrativo_Cobranca = dm_Formulario_Demonstrativo_Cobranca;
	}
	public String getDm_Formulario_Duplicata() {
		return dm_Formulario_Duplicata;
	}
	public void setDm_Formulario_Duplicata(String dm_Formulario_Duplicata) {
		this.dm_Formulario_Duplicata = dm_Formulario_Duplicata;
	}
	public String getDm_Formulario_Duplicata_Internacional() {
		return dm_Formulario_Duplicata_Internacional;
	}
	public void setDm_Formulario_Duplicata_Internacional(
			String dm_Formulario_Duplicata_Internacional) {
		this.dm_Formulario_Duplicata_Internacional = dm_Formulario_Duplicata_Internacional;
	}
	public String getDm_Formulario_Duplicata_Proform() {
		return dm_Formulario_Duplicata_Proform;
	}
	public void setDm_Formulario_Duplicata_Proform(
			String dm_Formulario_Duplicata_Proform) {
		this.dm_Formulario_Duplicata_Proform = dm_Formulario_Duplicata_Proform;
	}
	public String getDm_Formulario_Manifesto() {
		return dm_Formulario_Manifesto;
	}
	public void setDm_Formulario_Manifesto(String dm_Formulario_Manifesto) {
		this.dm_Formulario_Manifesto = dm_Formulario_Manifesto;
	}
	public String getDm_Formulario_Manifesto_Redespacho() {
		return dm_Formulario_Manifesto_Redespacho;
	}
	public void setDm_Formulario_Manifesto_Redespacho(
			String dm_Formulario_Manifesto_Redespacho) {
		this.dm_Formulario_Manifesto_Redespacho = dm_Formulario_Manifesto_Redespacho;
	}
	public String getDm_Formulario_Minuta() {
		return dm_Formulario_Minuta;
	}
	public void setDm_Formulario_Minuta(String dm_Formulario_Minuta) {
		this.dm_Formulario_Minuta = dm_Formulario_Minuta;
	}
	public String getDm_Formulario_Nota_Fiscal() {
		return dm_Formulario_Nota_Fiscal;
	}
	public void setDm_Formulario_Nota_Fiscal(String dm_Formulario_Nota_Fiscal) {
		this.dm_Formulario_Nota_Fiscal = dm_Formulario_Nota_Fiscal;
	}
	public String getDm_Formulario_Ordem_Abastecimento() {
		return dm_Formulario_Ordem_Abastecimento;
	}
	public void setDm_Formulario_Ordem_Abastecimento(
			String dm_Formulario_Ordem_Abastecimento) {
		this.dm_Formulario_Ordem_Abastecimento = dm_Formulario_Ordem_Abastecimento;
	}
	public String getDm_Formulario_Ordem_Frete() {
		return dm_Formulario_Ordem_Frete;
	}
	public void setDm_Formulario_Ordem_Frete(String dm_Formulario_Ordem_Frete) {
		this.dm_Formulario_Ordem_Frete = dm_Formulario_Ordem_Frete;
	}
	public String getDm_Formulario_Ordem_Frete_Adiantamento() {
		return dm_Formulario_Ordem_Frete_Adiantamento;
	}
	public void setDm_Formulario_Ordem_Frete_Adiantamento(
			String dm_Formulario_Ordem_Frete_Adiantamento) {
		this.dm_Formulario_Ordem_Frete_Adiantamento = dm_Formulario_Ordem_Frete_Adiantamento;
	}
	public String getDm_Formulario_Ordem_Servico() {
		return dm_Formulario_Ordem_Servico;
	}
	public void setDm_Formulario_Ordem_Servico(String dm_Formulario_Ordem_Servico) {
		this.dm_Formulario_Ordem_Servico = dm_Formulario_Ordem_Servico;
	}
	public String getDm_Formulario_Protocolo_Cobranca() {
		return dm_Formulario_Protocolo_Cobranca;
	}
	public void setDm_Formulario_Protocolo_Cobranca(
			String dm_Formulario_Protocolo_Cobranca) {
		this.dm_Formulario_Protocolo_Cobranca = dm_Formulario_Protocolo_Cobranca;
	}
	public String getDm_Formulario_Romaneio_Entrega() {
		return dm_Formulario_Romaneio_Entrega;
	}
	public void setDm_Formulario_Romaneio_Entrega(
			String dm_Formulario_Romaneio_Entrega) {
		this.dm_Formulario_Romaneio_Entrega = dm_Formulario_Romaneio_Entrega;
	}
	public String getDm_Formulario_Romaneio_Itens_Nota_Fiscal() {
		return dm_Formulario_Romaneio_Itens_Nota_Fiscal;
	}
	public void setDm_Formulario_Romaneio_Itens_Nota_Fiscal(
			String dm_Formulario_Romaneio_Itens_Nota_Fiscal) {
		this.dm_Formulario_Romaneio_Itens_Nota_Fiscal = dm_Formulario_Romaneio_Itens_Nota_Fiscal;
	}
	public String getDm_Formulario_Romaneio_Nota_Fiscal() {
		return dm_Formulario_Romaneio_Nota_Fiscal;
	}
	public void setDm_Formulario_Romaneio_Nota_Fiscal(
			String dm_Formulario_Romaneio_Nota_Fiscal) {
		this.dm_Formulario_Romaneio_Nota_Fiscal = dm_Formulario_Romaneio_Nota_Fiscal;
	}
	public String getDm_Formulario_AWB() {
		return dm_Formulario_AWB;
	}
	public void setDm_Formulario_AWB(String dm_Formulario_AWB) {
		this.dm_Formulario_AWB = dm_Formulario_AWB;
	}
	public String getDm_Formulario_Nota_Fiscal_Servico() {
		return dm_Formulario_Nota_Fiscal_Servico;
	}
	public void setDm_Formulario_Nota_Fiscal_Servico(
			String dm_Formulario_Nota_Fiscal_Servico) {
		this.dm_Formulario_Nota_Fiscal_Servico = dm_Formulario_Nota_Fiscal_Servico;
	}
	public long getNr_Vias_Ordem_Frete() {
		return nr_Vias_Ordem_Frete;
	}
	public void setNr_Vias_Ordem_Frete(long nr_Vias_Ordem_Frete) {
		this.nr_Vias_Ordem_Frete = nr_Vias_Ordem_Frete;
	}
	public long getNr_Vias_Ordem_Frete_Adiantamento() {
		return nr_Vias_Ordem_Frete_Adiantamento;
	}
	public void setNr_Vias_Ordem_Frete_Adiantamento(
			long nr_Vias_Ordem_Frete_Adiantamento) {
		this.nr_Vias_Ordem_Frete_Adiantamento = nr_Vias_Ordem_Frete_Adiantamento;
	}
	
//============ Getters and Setters Frota ================================================================================\\
	public String getDm_Base_Comissao_Motorista() {
		return dm_Base_Comissao_Motorista;
	}
	public void setDm_Base_Comissao_Motorista(String dm_Base_Comissao_Motorista) {
		this.dm_Base_Comissao_Motorista = dm_Base_Comissao_Motorista;
	}
	public String getDm_Exige_Validade_Lib_Seguradora_Motorista() {
		return dm_Exige_Validade_Lib_Seguradora_Motorista;
	}
	public void setDm_Exige_Validade_Lib_Seguradora_Motorista(
			String dm_Exige_Validade_Lib_Seguradora_Motorista) {
		this.dm_Exige_Validade_Lib_Seguradora_Motorista = dm_Exige_Validade_Lib_Seguradora_Motorista;
	}
	public String getDm_Limite_Credito_Motorista_Adiantamento_Frete() {
		return dm_Limite_Credito_Motorista_Adiantamento_Frete;
	}
	public void setDm_Limite_Credito_Motorista_Adiantamento_Frete(
			String dm_Limite_Credito_Motorista_Adiantamento_Frete) {
		this.dm_Limite_Credito_Motorista_Adiantamento_Frete = dm_Limite_Credito_Motorista_Adiantamento_Frete;
	}
	public String getMovimento_Ordem_Servico_Duplicada() {
		return movimento_Ordem_Servico_Duplicada;
	}
	public void setMovimento_Ordem_Servico_Duplicada(
			String movimento_Ordem_Servico_Duplicada) {
		this.movimento_Ordem_Servico_Duplicada = movimento_Ordem_Servico_Duplicada;
	}
		
//============ Getters and Setters Tela Historico =================================================================================\\
	public long getOid_Historico_Cancelamento_Lote_Pagamento() {
		return oid_Historico_Cancelamento_Lote_Pagamento;
	}
	public void setOid_Historico_Cancelamento_Lote_Pagamento(long oid_Historico_Cancelamento_Lote_Pagamento) {
		this.oid_Historico_Cancelamento_Lote_Pagamento = oid_Historico_Cancelamento_Lote_Pagamento;
	}
	public long getOid_Historico_Compensacao() {
		return oid_Historico_Compensacao;
	}
	public void setOid_Historico_Compensacao(long oid_Historico_Compensacao) {
		this.oid_Historico_Compensacao = oid_Historico_Compensacao;
	}
	public long getOid_Historico_Bordero() {
		return oid_Historico_Bordero;
	}
	public void setOid_Historico_Bordero(long oid_Historico_Bordero) {
		this.oid_Historico_Bordero = oid_Historico_Bordero;
	}
	public long getOid_Historico_Cancelamento_Ordem_Frete() {
		return oid_Historico_Cancelamento_Ordem_Frete;
	}
	public void setOid_Historico_Cancelamento_Ordem_Frete(
			long oid_Historico_Cancelamento_Ordem_Frete) {
		this.oid_Historico_Cancelamento_Ordem_Frete = oid_Historico_Cancelamento_Ordem_Frete;
	}
	public long getOid_Historico_Devolucao_Cheque() {
		return oid_Historico_Devolucao_Cheque;
	}
	public void setOid_Historico_Devolucao_Cheque(
			long oid_Historico_Devolucao_Cheque) {
		this.oid_Historico_Devolucao_Cheque = oid_Historico_Devolucao_Cheque;
	}
	public long getOid_Historico_Lancamento_Ordem_Frete() {
		return oid_Historico_Lancamento_Ordem_Frete;
	}
	public void setOid_Historico_Lancamento_Ordem_Frete(
			long oid_Historico_Lancamento_Ordem_Frete) {
		this.oid_Historico_Lancamento_Ordem_Frete = oid_Historico_Lancamento_Ordem_Frete;
	}
	public long getOid_Historico_Lancamento_Ordem_Frete_Adiantamento() {
		return oid_Historico_Lancamento_Ordem_Frete_Adiantamento;
	}
	public void setOid_Historico_Lancamento_Ordem_Frete_Adiantamento(
			long oid_Historico_Lancamento_Ordem_Frete_Adiantamento) {
		this.oid_Historico_Lancamento_Ordem_Frete_Adiantamento = oid_Historico_Lancamento_Ordem_Frete_Adiantamento;
	}
	public long getOid_Historico_Lancamento_Ordem_Frete_Terceiro() {
		return oid_Historico_Lancamento_Ordem_Frete_Terceiro;
	}
	public void setOid_Historico_Lancamento_Ordem_Frete_Terceiro(
			long oid_Historico_Lancamento_Ordem_Frete_Terceiro) {
		this.oid_Historico_Lancamento_Ordem_Frete_Terceiro = oid_Historico_Lancamento_Ordem_Frete_Terceiro;
	}
	public long getOid_Historico_Liquidacao_Cobranca() {
		return oid_Historico_Liquidacao_Cobranca;
	}
	public void setOid_Historico_Liquidacao_Cobranca(
			long oid_Historico_Liquidacao_Cobranca) {
		this.oid_Historico_Liquidacao_Cobranca = oid_Historico_Liquidacao_Cobranca;
	}
	public long getOid_Historico_Liquidacao_Cobranca_Retorno() {
		return oid_Historico_Liquidacao_Cobranca_Retorno;
	}
	public void setOid_Historico_Liquidacao_Cobranca_Retorno(
			long oid_Historico_Liquidacao_Cobranca_Retorno) {
		this.oid_Historico_Liquidacao_Cobranca_Retorno = oid_Historico_Liquidacao_Cobranca_Retorno;
	}
	public long getOid_Historico_Transferencia_Banco() {
		return oid_Historico_Transferencia_Banco;
	}
	public void setOid_Historico_Transferencia_Banco(
			long oid_Historico_Transferencia_Banco) {
		this.oid_Historico_Transferencia_Banco = oid_Historico_Transferencia_Banco;
	}
	public long getOid_Historico_Transferencia_Caixa() {
		return oid_Historico_Transferencia_Caixa;
	}
	public void setOid_Historico_Transferencia_Caixa(
			long oid_Historico_Transferencia_Caixa) {
		this.oid_Historico_Transferencia_Caixa = oid_Historico_Transferencia_Caixa;
	}
	
//============ Getters and Setters Impress�o ================================================================================\\
	public String getDm_Tipo_Impressao_Coleta() {
		return dm_Tipo_Impressao_Coleta;
	}
	public void setDm_Tipo_Impressao_Coleta(String dm_Tipo_Impressao_Coleta) {
		this.dm_Tipo_Impressao_Coleta = dm_Tipo_Impressao_Coleta;
	}
	public String getDm_Tipo_Impressao_Conhecimento_Nacional() {
		return dm_Tipo_Impressao_Conhecimento_Nacional;
	}
	public void setDm_Tipo_Impressao_Conhecimento_Nacional(
			String dm_Tipo_Impressao_Conhecimento_Nacional) {
		this.dm_Tipo_Impressao_Conhecimento_Nacional = dm_Tipo_Impressao_Conhecimento_Nacional;
	}
	public String getDm_Tipo_Impressao_Fatura() {
		return dm_Tipo_Impressao_Fatura;
	}
	public void setDm_Tipo_Impressao_Fatura(String dm_Tipo_Impressao_Fatura) {
		this.dm_Tipo_Impressao_Fatura = dm_Tipo_Impressao_Fatura;
	}
	public String getDm_Tipo_Impressao_Minuta() {
		return dm_Tipo_Impressao_Minuta;
	}
	public void setDm_Tipo_Impressao_Minuta(String dm_Tipo_Impressao_Minuta) {
		this.dm_Tipo_Impressao_Minuta = dm_Tipo_Impressao_Minuta;
	}
	public String getDm_Tipo_Impressao_Nota_Fiscal_Servico() {
		return dm_Tipo_Impressao_Nota_Fiscal_Servico;
	}
	public void setDm_Tipo_Impressao_Nota_Fiscal_Servico(
			String dm_Tipo_Impressao_Nota_Fiscal_Servico) {
		this.dm_Tipo_Impressao_Nota_Fiscal_Servico = dm_Tipo_Impressao_Nota_Fiscal_Servico;
	}
	
//============ Getters and Setters Tela Instrucoes ================================================================================\\
	public long getOid_Tipo_Instrucao_Desconto() {
		return oid_Tipo_Instrucao_Desconto;
	}
	public void setOid_Tipo_Instrucao_Desconto(long oid_Tipo_Instrucao_Desconto) {
		this.oid_Tipo_Instrucao_Desconto = oid_Tipo_Instrucao_Desconto;
	}
	public long getOid_Tipo_Instrucao_Estorno() {
		return oid_Tipo_Instrucao_Estorno;
	}
	public void setOid_Tipo_Instrucao_Estorno(long oid_Tipo_Instrucao_Estorno) {
		this.oid_Tipo_Instrucao_Estorno = oid_Tipo_Instrucao_Estorno;
	}
	public long getOid_Tipo_Instrucao_Juros() {
		return oid_Tipo_Instrucao_Juros;
	}
	public void setOid_Tipo_Instrucao_Juros(long oid_Tipo_Instrucao_Juros) {
		this.oid_Tipo_Instrucao_Juros = oid_Tipo_Instrucao_Juros;
	}
	public long getOid_Tipo_Instrucao_Juros_Reembolso() {
		return oid_Tipo_Instrucao_Juros_Reembolso;
	}
	public void setOid_Tipo_Instrucao_Juros_Reembolso(long oid_Tipo_Instrucao_Juros_Reembolso) {
		this.oid_Tipo_Instrucao_Juros_Reembolso = oid_Tipo_Instrucao_Juros_Reembolso;
	}
	public long getOid_Tipo_Instrucao_Pago_Cartorio() {
		return oid_Tipo_Instrucao_Pago_Cartorio;
	}
	public void setOid_Tipo_Instrucao_Pago_Cartorio(long oid_Tipo_Instrucao_Pago_Cartorio) {
		this.oid_Tipo_Instrucao_Pago_Cartorio = oid_Tipo_Instrucao_Pago_Cartorio;
	}
	public long getOid_Tipo_Instrucao_Pago_Parcial() {
		return oid_Tipo_Instrucao_Pago_Parcial;
	}
	public void setOid_Tipo_Instrucao_Pago_Parcial(long oid_Tipo_Instrucao_Pago_Parcial) {
		this.oid_Tipo_Instrucao_Pago_Parcial = oid_Tipo_Instrucao_Pago_Parcial;
	}
	public long getOid_Tipo_Instrucao_Pago_Total() {
		return oid_Tipo_Instrucao_Pago_Total;
	}
	public void setOid_Tipo_Instrucao_Pago_Total(long oid_Tipo_Instrucao_Pago_Total) {
		this.oid_Tipo_Instrucao_Pago_Total = oid_Tipo_Instrucao_Pago_Total;
	}
	public long getOid_Tipo_Instrucao_Tarifa() {
		return oid_Tipo_Instrucao_Tarifa;
	}
	public void setOid_Tipo_Instrucao_Tarifa(long oid_Tipo_Instrucao_Tarifa) {
		this.oid_Tipo_Instrucao_Tarifa = oid_Tipo_Instrucao_Tarifa;
	}
	public long getOid_Tipo_Instrucao_Taxa_Cobranca() {
		return oid_Tipo_Instrucao_Taxa_Cobranca;
	}
	public void setOid_Tipo_Instrucao_Taxa_Cobranca(long oid_Tipo_Instrucao_Taxa_Cobranca) {
		this.oid_Tipo_Instrucao_Taxa_Cobranca = oid_Tipo_Instrucao_Taxa_Cobranca;
	}
	public long getOid_Tipo_Instrucao_Valor_Reembolso() {
		return oid_Tipo_Instrucao_Valor_Reembolso;
	}
	public void setOid_Tipo_Instrucao_Valor_Reembolso(long oid_Tipo_Instrucao_Valor_Reembolso) {
		this.oid_Tipo_Instrucao_Valor_Reembolso = oid_Tipo_Instrucao_Valor_Reembolso;
	}
	public long getOid_Tipo_Instrucao_Variacao_Cambial_Ativa() {
		return oid_Tipo_Instrucao_Variacao_Cambial_Ativa;
	}
	public void setOid_Tipo_Instrucao_Variacao_Cambial_Ativa(long oid_Tipo_Instrucao_Variacao_Cambial_Ativa) {
		this.oid_Tipo_Instrucao_Variacao_Cambial_Ativa = oid_Tipo_Instrucao_Variacao_Cambial_Ativa;
	}
	public long getOid_Tipo_Instrucao_Variacao_Cambial_Passiva() {
		return oid_Tipo_Instrucao_Variacao_Cambial_Passiva;
	}
	public void setOid_Tipo_Instrucao_Variacao_Cambial_Passiva(long oid_Tipo_Instrucao_Variacao_Cambial_Passiva) {
		this.oid_Tipo_Instrucao_Variacao_Cambial_Passiva = oid_Tipo_Instrucao_Variacao_Cambial_Passiva;
	}
	public long getOid_Tipo_Instrucao_Devolucao_Nota_Fiscal() {
		return oid_Tipo_Instrucao_Devolucao_Nota_Fiscal;
	}
	public void setOid_Tipo_Instrucao_Devolucao_Nota_Fiscal(
			long oid_Tipo_Instrucao_Devolucao_Nota_Fiscal) {
		this.oid_Tipo_Instrucao_Devolucao_Nota_Fiscal = oid_Tipo_Instrucao_Devolucao_Nota_Fiscal;
	}
	public long getOid_Tipo_Instrucao_Imposto_Retido1() {
		return oid_Tipo_Instrucao_Imposto_Retido1;
	}
	public void setOid_Tipo_Instrucao_Imposto_Retido1(
			long oid_Tipo_Instrucao_Imposto_Retido1) {
		this.oid_Tipo_Instrucao_Imposto_Retido1 = oid_Tipo_Instrucao_Imposto_Retido1;
	}
	public long getOid_Tipo_Instrucao_Imposto_Retido2() {
		return oid_Tipo_Instrucao_Imposto_Retido2;
	}
	public void setOid_Tipo_Instrucao_Imposto_Retido2(
			long oid_Tipo_Instrucao_Imposto_Retido2) {
		this.oid_Tipo_Instrucao_Imposto_Retido2 = oid_Tipo_Instrucao_Imposto_Retido2;
	}
	public long getOid_Tipo_Instrucao_Remessa() {
		return oid_Tipo_Instrucao_Remessa;
	}
	public void setOid_Tipo_Instrucao_Remessa(long oid_Tipo_Instrucao_Remessa) {
		this.oid_Tipo_Instrucao_Remessa = oid_Tipo_Instrucao_Remessa;
	}
	
//============ Getters and Setters Transporte Internacional ================================================================================\\
	public String getCompleta_TX_Via() {
		return completa_TX_Via;
	}
	public void setCompleta_TX_Via(String completa_TX_Via) {
		this.completa_TX_Via = completa_TX_Via;
	}
	public String getDm_Impressao_Tramo() {
		return dm_Impressao_Tramo;
	}
	public void setDm_Impressao_Tramo(String dm_Impressao_Tramo) {
		this.dm_Impressao_Tramo = dm_Impressao_Tramo;
	}
	public String getNm_Ano_Permisso() {
		return nm_Ano_Permisso;
	}
	public void setNm_Ano_Permisso(String nm_Ano_Permisso) {
		this.nm_Ano_Permisso = nm_Ano_Permisso;
	}
	public String getNm_Sucessivos() {
		return nm_Sucessivos;
	}
	public void setNm_Sucessivos(String nm_Sucessivos) {
		this.nm_Sucessivos = nm_Sucessivos;
	}
	public String getTx_Via_Primeiro_Original_Es() {
		return tx_Via_Primeiro_Original_Es;
	}
	public void setTx_Via_Primeiro_Original_Es(String tx_Via_Primeiro_Original_Es) {
		this.tx_Via_Primeiro_Original_Es = tx_Via_Primeiro_Original_Es;
	}
	public String getTx_Via_Primeiro_Original_Pt() {
		return tx_Via_Primeiro_Original_Pt;
	}
	public void setTx_Via_Primeiro_Original_Pt(String tx_Via_Primeiro_Original_Pt) {
		this.tx_Via_Primeiro_Original_Pt = tx_Via_Primeiro_Original_Pt;
	}
	public String getTx_Via_Segundo_Original_Es() {
		return tx_Via_Segundo_Original_Es;
	}
	public void setTx_Via_Segundo_Original_Es(String tx_Via_Segundo_Original_Es) {
		this.tx_Via_Segundo_Original_Es = tx_Via_Segundo_Original_Es;
	}
	public String getTx_Via_Segundo_Original_Pt() {
		return tx_Via_Segundo_Original_Pt;
	}
	public void setTx_Via_Segundo_Original_Pt(String tx_Via_Segundo_Original_Pt) {
		this.tx_Via_Segundo_Original_Pt = tx_Via_Segundo_Original_Pt;
	}
	public String getTx_Via_Terceiro_Original_Es() {
		return tx_Via_Terceiro_Original_Es;
	}
	public void setTx_Via_Terceiro_Original_Es(String tx_Via_Terceiro_Original_Es) {
		this.tx_Via_Terceiro_Original_Es = tx_Via_Terceiro_Original_Es;
	}
	public String getTx_Via_Terceiro_Original_Pt() {
		return tx_Via_Terceiro_Original_Pt;
	}
	public void setTx_Via_Terceiro_Original_Pt(String tx_Via_Terceiro_Original_Pt) {
		this.tx_Via_Terceiro_Original_Pt = tx_Via_Terceiro_Original_Pt;
	}

//============ Getters and Setters Tela Localiza��o ===========================================================================\\
	
	public long getOid_Localizacao() {
		return oid_Localizacao;
	}
	public void setOid_Localizacao(long oid_Localizacao) {
		this.oid_Localizacao = oid_Localizacao;
	}
	public long getOid_Localizacao_Devolucao() {
		return oid_Localizacao_Devolucao;
	}
	public void setOid_Localizacao_Devolucao(long oid_Localizacao_Devolucao) {
		this.oid_Localizacao_Devolucao = oid_Localizacao_Devolucao;
	}
	public long getOid_Localizacao_Troca() {
		return oid_Localizacao_Troca;
	}
	public void setOid_Localizacao_Troca(long oid_Localizacao_Troca) {
		this.oid_Localizacao_Troca = oid_Localizacao_Troca;
	}
	
//============ Getters and Setters Tela Modal Servi�os  ===========================================================\\
	public long getOid_Modal() {
		return oid_Modal;
	}
	public void setOid_Modal(long oid_Modal) {
		this.oid_Modal = oid_Modal;
	}
	public long getOid_Modal_Aereo_Emergencial() {
		return oid_Modal_Aereo_Emergencial;
	}
	public void setOid_Modal_Aereo_Emergencial(long oid_Modal_Aereo_Emergencial) {
		this.oid_Modal_Aereo_Emergencial = oid_Modal_Aereo_Emergencial;
	}
	public long getOid_Modal_Aereo_Expressa() {
		return oid_Modal_Aereo_Expressa;
	}
	public void setOid_Modal_Aereo_Expressa(long oid_Modal_Aereo_Expressa) {
		this.oid_Modal_Aereo_Expressa = oid_Modal_Aereo_Expressa;
	}
	public long getOid_Modal_Aereo_Pac() {
		return oid_Modal_Aereo_Pac;
	}
	public void setOid_Modal_Aereo_Pac(long oid_Modal_Aereo_Pac) {
		this.oid_Modal_Aereo_Pac = oid_Modal_Aereo_Pac;
	}
	public long getOid_Modal_Aereo_RodExp() {
		return oid_Modal_Aereo_RodExp;
	}
	public void setOid_Modal_Aereo_RodExp(long oid_Modal_Aereo_RodExp) {
		this.oid_Modal_Aereo_RodExp = oid_Modal_Aereo_RodExp;
	}
	public long getOid_Modal_Aereo_Sedex() {
		return oid_Modal_Aereo_Sedex;
	}
	public void setOid_Modal_Aereo_Sedex(long oid_Modal_Aereo_Sedex) {
		this.oid_Modal_Aereo_Sedex = oid_Modal_Aereo_Sedex;
	}
	public long getOid_Modal_Aereo_Standard() {
		return oid_Modal_Aereo_Standard;
	}
	public void setOid_Modal_Aereo_Standard(long oid_Modal_Aereo_Standard) {
		this.oid_Modal_Aereo_Standard = oid_Modal_Aereo_Standard;
	}
	
//============ Getters and Setters Tela Modelo ===========================================================================\\	
	public long getOid_Modelo_NF_DevFornecedor_Dentro_Estado() {
		return oid_Modelo_NF_DevFornecedor_Dentro_Estado;
	}
	public void setOid_Modelo_NF_DevFornecedor_Dentro_Estado(
			long oid_Modelo_NF_DevFornecedor_Dentro_Estado) {
		this.oid_Modelo_NF_DevFornecedor_Dentro_Estado = oid_Modelo_NF_DevFornecedor_Dentro_Estado;
	}
	public long getOid_Modelo_NF_DevFornecedor_Fora_Estado() {
		return oid_Modelo_NF_DevFornecedor_Fora_Estado;
	}
	public void setOid_Modelo_NF_DevFornecedor_Fora_Estado(
			long oid_Modelo_NF_DevFornecedor_Fora_Estado) {
		this.oid_Modelo_NF_DevFornecedor_Fora_Estado = oid_Modelo_NF_DevFornecedor_Fora_Estado;
	}
	public long getOid_Modelo_Nota_Fiscal() {
		return oid_Modelo_Nota_Fiscal;
	}
	public void setOid_Modelo_Nota_Fiscal(long oid_Modelo_Nota_Fiscal) {
		this.oid_Modelo_Nota_Fiscal = oid_Modelo_Nota_Fiscal;
	}

//============ Getters and Setters Tela Modelo Tabela ===================================================================\\
	public String getNm_Modelo_Tabela_Aereo() {
		return nm_Modelo_Tabela_Aereo;
	}
	public void setNm_Modelo_Tabela_Aereo(String modelo_Tabela_Aereo) {
		nm_Modelo_Tabela_Aereo = modelo_Tabela_Aereo;
	}
	public String getNm_Modelo_Tabela_Documento() {
		return nm_Modelo_Tabela_Documento;
	}
	public void setNm_Modelo_Tabela_Documento(String modelo_Tabela_Documento) {
		nm_Modelo_Tabela_Documento = modelo_Tabela_Documento;
	}
	public String getNm_Modelo_Tabela_Rodoviario() {
		return nm_Modelo_Tabela_Rodoviario;
	}
	public void setNm_Modelo_Tabela_Rodoviario(String modelo_Tabela_Rodoviario) {
		nm_Modelo_Tabela_Rodoviario = modelo_Tabela_Rodoviario;
	}
	
//============ Getters and Setters Tela Movimento ================================================================================\\
	public long getOid_Tipo_Movimento_Ajuste_Canc() {
		return oid_Tipo_Movimento_Ajuste_Canc;
	}
	public void setOid_Tipo_Movimento_Ajuste_Canc(
			long oid_Tipo_Movimento_Ajuste_Canc) {
		this.oid_Tipo_Movimento_Ajuste_Canc = oid_Tipo_Movimento_Ajuste_Canc;
	}
	public long getOid_Tipo_Movimento_Ajuste_E() {
		return oid_Tipo_Movimento_Ajuste_E;
	}
	public void setOid_Tipo_Movimento_Ajuste_E(long oid_Tipo_Movimento_Ajuste_E) {
		this.oid_Tipo_Movimento_Ajuste_E = oid_Tipo_Movimento_Ajuste_E;
	}
	public long getOid_Tipo_Movimento_Ajuste_Exc() {
		return oid_Tipo_Movimento_Ajuste_Exc;
	}
	public void setOid_Tipo_Movimento_Ajuste_Exc(long oid_Tipo_Movimento_Ajuste_Exc) {
		this.oid_Tipo_Movimento_Ajuste_Exc = oid_Tipo_Movimento_Ajuste_Exc;
	}
	public long getOid_Tipo_Movimento_Ajuste_S() {
		return oid_Tipo_Movimento_Ajuste_S;
	}
	public void setOid_Tipo_Movimento_Ajuste_S(long oid_Tipo_Movimento_Ajuste_S) {
		this.oid_Tipo_Movimento_Ajuste_S = oid_Tipo_Movimento_Ajuste_S;
	}
	public long getOid_Tipo_Movimento_Custo_Coleta() {
		return oid_Tipo_Movimento_Custo_Coleta;
	}
	public void setOid_Tipo_Movimento_Custo_Coleta(
			long oid_Tipo_Movimento_Custo_Coleta) {
		this.oid_Tipo_Movimento_Custo_Coleta = oid_Tipo_Movimento_Custo_Coleta;
	}
	public long getOid_Tipo_Movimento_Custo_Coleta_Transferencia() {
		return oid_Tipo_Movimento_Custo_Coleta_Transferencia;
	}
	public void setOid_Tipo_Movimento_Custo_Coleta_Transferencia(
			long oid_Tipo_Movimento_Custo_Coleta_Transferencia) {
		this.oid_Tipo_Movimento_Custo_Coleta_Transferencia = oid_Tipo_Movimento_Custo_Coleta_Transferencia;
	}
	public long getOid_Tipo_Movimento_Custo_Coleta_Transferencia_Entrega() {
		return oid_Tipo_Movimento_Custo_Coleta_Transferencia_Entrega;
	}
	public void setOid_Tipo_Movimento_Custo_Coleta_Transferencia_Entrega(
			long oid_Tipo_Movimento_Custo_Coleta_Transferencia_Entrega) {
		this.oid_Tipo_Movimento_Custo_Coleta_Transferencia_Entrega = oid_Tipo_Movimento_Custo_Coleta_Transferencia_Entrega;
	}
	public long getOid_Tipo_Movimento_Custo_Desconto() {
		return oid_Tipo_Movimento_Custo_Desconto;
	}
	public void setOid_Tipo_Movimento_Custo_Desconto(
			long oid_Tipo_Movimento_Custo_Desconto) {
		this.oid_Tipo_Movimento_Custo_Desconto = oid_Tipo_Movimento_Custo_Desconto;
	}
	public long getOid_Tipo_Movimento_Custo_Devolucao() {
		return oid_Tipo_Movimento_Custo_Devolucao;
	}
	public void setOid_Tipo_Movimento_Custo_Devolucao(
			long oid_Tipo_Movimento_Custo_Devolucao) {
		this.oid_Tipo_Movimento_Custo_Devolucao = oid_Tipo_Movimento_Custo_Devolucao;
	}
	public long getOid_Tipo_Movimento_Custo_Entrega() {
		return oid_Tipo_Movimento_Custo_Entrega;
	}
	public void setOid_Tipo_Movimento_Custo_Entrega(
			long oid_Tipo_Movimento_Custo_Entrega) {
		this.oid_Tipo_Movimento_Custo_Entrega = oid_Tipo_Movimento_Custo_Entrega;
	}
	public long getOid_Tipo_Movimento_Custo_Master() {
		return oid_Tipo_Movimento_Custo_Master;
	}
	public void setOid_Tipo_Movimento_Custo_Master(
			long oid_Tipo_Movimento_Custo_Master) {
		this.oid_Tipo_Movimento_Custo_Master = oid_Tipo_Movimento_Custo_Master;
	}
	public long getOid_Tipo_Movimento_Custo_Ordem_Frete() {
		return oid_Tipo_Movimento_Custo_Ordem_Frete;
	}
	public void setOid_Tipo_Movimento_Custo_Ordem_Frete(
			long oid_Tipo_Movimento_Custo_Ordem_Frete) {
		this.oid_Tipo_Movimento_Custo_Ordem_Frete = oid_Tipo_Movimento_Custo_Ordem_Frete;
	}
	public long getOid_Tipo_Movimento_Custo_Previsto_Entrega() {
		return oid_Tipo_Movimento_Custo_Previsto_Entrega;
	}
	public void setOid_Tipo_Movimento_Custo_Previsto_Entrega(
			long oid_Tipo_Movimento_Custo_Previsto_Entrega) {
		this.oid_Tipo_Movimento_Custo_Previsto_Entrega = oid_Tipo_Movimento_Custo_Previsto_Entrega;
	}
	public long getOid_Tipo_Movimento_Custo_Reentrega() {
		return oid_Tipo_Movimento_Custo_Reentrega;
	}
	public void setOid_Tipo_Movimento_Custo_Reentrega(
			long oid_Tipo_Movimento_Custo_Reentrega) {
		this.oid_Tipo_Movimento_Custo_Reentrega = oid_Tipo_Movimento_Custo_Reentrega;
	}
	public long getOid_Tipo_Movimento_Custo_Transferencia() {
		return oid_Tipo_Movimento_Custo_Transferencia;
	}
	public void setOid_Tipo_Movimento_Custo_Transferencia(
			long oid_Tipo_Movimento_Custo_Transferencia) {
		this.oid_Tipo_Movimento_Custo_Transferencia = oid_Tipo_Movimento_Custo_Transferencia;
	}
	public long getOid_Tipo_Movimento_Custo_Transferencia_Entrega() {
		return oid_Tipo_Movimento_Custo_Transferencia_Entrega;
	}
	public void setOid_Tipo_Movimento_Custo_Transferencia_Entrega(
			long oid_Tipo_Movimento_Custo_Transferencia_Entrega) {
		this.oid_Tipo_Movimento_Custo_Transferencia_Entrega = oid_Tipo_Movimento_Custo_Transferencia_Entrega;
	}
	public long getOid_Tipo_Movimento_Produto() {
		return oid_Tipo_Movimento_Produto;
	}
	public void setOid_Tipo_Movimento_Produto(long oid_Tipo_Movimento_Produto) {
		this.oid_Tipo_Movimento_Produto = oid_Tipo_Movimento_Produto;
	}
	public long getOid_Tipo_Movimento_Ressarcimento() {
		return oid_Tipo_Movimento_Ressarcimento;
	}
	public void setOid_Tipo_Movimento_Ressarcimento(
			long oid_Tipo_Movimento_Ressarcimento) {
		this.oid_Tipo_Movimento_Ressarcimento = oid_Tipo_Movimento_Ressarcimento;
	}
	public long getOid_Tipo_Movimento_Transf() {
		return oid_Tipo_Movimento_Transf;
	}
	public void setOid_Tipo_Movimento_Transf(long oid_Tipo_Movimento_Transf) {
		this.oid_Tipo_Movimento_Transf = oid_Tipo_Movimento_Transf;
	}

//============ Getters and Setters Tela Natureza ================================================================================\\
	public long getOid_Natureza_Operacao_Entrega() {
		return oid_Natureza_Operacao_Entrega;
	}
	public void setOid_Natureza_Operacao_Entrega(long oid_Natureza_Operacao_Entrega) {
		this.oid_Natureza_Operacao_Entrega = oid_Natureza_Operacao_Entrega;
	}
	public long getOid_Natureza_Operacao_Master() {
		return oid_Natureza_Operacao_Master;
	}
	public void setOid_Natureza_Operacao_Master(long oid_Natureza_Operacao_Master) {
		this.oid_Natureza_Operacao_Master = oid_Natureza_Operacao_Master;
	}
	public long getOid_Natureza_Operacao_Ordem_Frete() {
		return oid_Natureza_Operacao_Ordem_Frete;
	}
	public void setOid_Natureza_Operacao_Ordem_Frete(
			long oid_Natureza_Operacao_Ordem_Frete) {
		this.oid_Natureza_Operacao_Ordem_Frete = oid_Natureza_Operacao_Ordem_Frete;
	}
	
//============ Getters and Setters Tela Numera��es  ================================================================================\\
	public String getDm_Numera_Coleta() {
		return dm_Numera_Coleta;
	}
	public void setDm_Numera_Coleta(String dm_Numera_Coleta) {
		this.dm_Numera_Coleta = dm_Numera_Coleta;
	}
	public String getDm_Numera_Conhecimento() {
		return dm_Numera_Conhecimento;
	}
	public void setDm_Numera_Conhecimento(String dm_Numera_Conhecimento) {
		this.dm_Numera_Conhecimento = dm_Numera_Conhecimento;
	}
	public String getDm_Numera_Fatura() {
		return dm_Numera_Fatura;
	}
	public void setDm_Numera_Fatura(String dm_Numera_Fatura) {
		this.dm_Numera_Fatura = dm_Numera_Fatura;
	}
	public String getDm_Numera_Frete_Internacional() {
		return dm_Numera_Frete_Internacional;
	}
	public void setDm_Numera_Frete_Internacional(
			String dm_Numera_Frete_Internacional) {
		this.dm_Numera_Frete_Internacional = dm_Numera_Frete_Internacional;
	}
	public String getDm_Numera_Manifesto() {
		return dm_Numera_Manifesto;
	}
	public void setDm_Numera_Manifesto(String dm_Numera_Manifesto) {
		this.dm_Numera_Manifesto = dm_Numera_Manifesto;
	}
	public String getDm_Numera_Ordem_Frete() {
		return dm_Numera_Ordem_Frete;
	}
	public void setDm_Numera_Ordem_Frete(String dm_Numera_Ordem_Frete) {
		this.dm_Numera_Ordem_Frete = dm_Numera_Ordem_Frete;
	}
	
//============ Getters and Setters Tela Ocorr�ncia ==================================================================================\\
	public long getOid_Tipo_Ocorrencia_Cancelamento_CTRC() {
		return oid_Tipo_Ocorrencia_Cancelamento_CTRC;
	}
	public void setOid_Tipo_Ocorrencia_Cancelamento_CTRC(
			long oid_Tipo_Ocorrencia_Cancelamento_CTRC) {
		this.oid_Tipo_Ocorrencia_Cancelamento_CTRC = oid_Tipo_Ocorrencia_Cancelamento_CTRC;
	}
	public long getOid_Tipo_Ocorrencia_Desconto_CTRC() {
		return oid_Tipo_Ocorrencia_Desconto_CTRC;
	}
	public void setOid_Tipo_Ocorrencia_Desconto_CTRC(
			long oid_Tipo_Ocorrencia_Desconto_CTRC) {
		this.oid_Tipo_Ocorrencia_Desconto_CTRC = oid_Tipo_Ocorrencia_Desconto_CTRC;
	}
	public long getOid_Tipo_Ocorrencia_Devolucao_CTRC() {
		return oid_Tipo_Ocorrencia_Devolucao_CTRC;
	}
	public void setOid_Tipo_Ocorrencia_Devolucao_CTRC(
			long oid_Tipo_Ocorrencia_Devolucao_CTRC) {
		this.oid_Tipo_Ocorrencia_Devolucao_CTRC = oid_Tipo_Ocorrencia_Devolucao_CTRC;
	}
	public long getOid_Tipo_Ocorrencia_Estorno_CTRC() {
		return oid_Tipo_Ocorrencia_Estorno_CTRC;
	} 
	public void setOid_Tipo_Ocorrencia_Estorno_CTRC(
			long oid_Tipo_Ocorrencia_Estorno_CTRC) {
		this.oid_Tipo_Ocorrencia_Estorno_CTRC = oid_Tipo_Ocorrencia_Estorno_CTRC;
	}
	public long getOid_Tipo_Ocorrencia_Reentrega_CTRC() {
		return oid_Tipo_Ocorrencia_Reentrega_CTRC;
	}
	public void setOid_Tipo_Ocorrencia_Reentrega_CTRC(
			long oid_Tipo_Ocorrencia_Reentrega_CTRC) {
		this.oid_Tipo_Ocorrencia_Reentrega_CTRC = oid_Tipo_Ocorrencia_Reentrega_CTRC;
	}

//====================== Getters and Setters Transporte Pessoa ===============================\\
	
	public String getDm_Envia_Email_Eventos() {
		return dm_Envia_Email_Eventos;
	}
	public void setDm_Envia_Email_Eventos(String dm_Envia_Email_Eventos) {
		this.dm_Envia_Email_Eventos = dm_Envia_Email_Eventos;
	}
	public String getDm_Valida_CEP() {
		return dm_Valida_CEP;
	}
	public void setDm_Valida_CEP(String dm_Valida_CEP) {
		this.dm_Valida_CEP = dm_Valida_CEP;
	}
	public String getDm_Verifica_CNPJ_CPF_Pessoa() {
		return dm_Verifica_CNPJ_CPF_Pessoa;
	}
	public void setDm_Verifica_CNPJ_CPF_Pessoa(String dm_Verifica_CNPJ_CPF_Pessoa) {
		this.dm_Verifica_CNPJ_CPF_Pessoa = dm_Verifica_CNPJ_CPF_Pessoa;
	}
	public String getGeraCodigoCliente() {
		return geraCodigoCliente;
	}
	public void setGeraCodigoCliente(String geraCodigoCliente) {
		this.geraCodigoCliente = geraCodigoCliente;
	}
	public String getOid_Cliente_Complemento_Padrao() {
		return oid_Cliente_Complemento_Padrao;
	}
	public void setOid_Cliente_Complemento_Padrao(
			String oid_Cliente_Complemento_Padrao) {
		this.oid_Cliente_Complemento_Padrao = oid_Cliente_Complemento_Padrao;
	}
	public String getOid_Fornecedor_INSS() {
		return oid_Fornecedor_INSS;
	}
	public void setOid_Fornecedor_INSS(String oid_Fornecedor_INSS) {
		this.oid_Fornecedor_INSS = oid_Fornecedor_INSS;
	}
	public String getOid_Fornecedor_IRRF() {
		return oid_Fornecedor_IRRF;
	}
	public void setOid_Fornecedor_IRRF(String oid_Fornecedor_IRRF) {
		this.oid_Fornecedor_IRRF = oid_Fornecedor_IRRF;
	}
	public String getOid_Pessoa_Padrao_Tabela_Frete() {
		return oid_Pessoa_Padrao_Tabela_Frete;
	}
	public void setOid_Pessoa_Padrao_Tabela_Frete(
			String oid_Pessoa_Padrao_Tabela_Frete) {
		this.oid_Pessoa_Padrao_Tabela_Frete = oid_Pessoa_Padrao_Tabela_Frete;
	}
	
//============ Getters and Setters Tela Servi�o ===================================================================\\	
	public long getOid_Tipo_Servico_Abastecimento() {
		return oid_Tipo_Servico_Abastecimento;
	}
	public void setOid_Tipo_Servico_Abastecimento(long tipo_Servico_Abastecimento) {
		oid_Tipo_Servico_Abastecimento = tipo_Servico_Abastecimento;
	}
	public long getOid_Tipo_Servico_Acerto_Contas() {
		return oid_Tipo_Servico_Acerto_Contas;
	}
	public void setOid_Tipo_Servico_Acerto_Contas(long tipo_Servico_Acerto_Contas) {
		oid_Tipo_Servico_Acerto_Contas = tipo_Servico_Acerto_Contas;
	}
	
//============ Getters and Setters Tipo Faturamento ================================================================================\\
	public long getOid_Tipo_Faturamento_Consolidacao_MIC_CRT() {
		return oid_Tipo_Faturamento_Consolidacao_MIC_CRT;
	}
	public void setOid_Tipo_Faturamento_Consolidacao_MIC_CRT(
			long oid_Tipo_Faturamento_Consolidacao_MIC_CRT) {
		this.oid_Tipo_Faturamento_Consolidacao_MIC_CRT = oid_Tipo_Faturamento_Consolidacao_MIC_CRT;
	}
	public long getOid_Tipo_Faturamento_Exportador() {
		return oid_Tipo_Faturamento_Exportador;
	}
	public void setOid_Tipo_Faturamento_Exportador(
			long oid_Tipo_Faturamento_Exportador) {
		this.oid_Tipo_Faturamento_Exportador = oid_Tipo_Faturamento_Exportador;
	}
	public long getOid_Tipo_Faturamento_Importador() {
		return oid_Tipo_Faturamento_Importador;
	}
	public void setOid_Tipo_Faturamento_Importador(
			long oid_Tipo_Faturamento_Importador) {
		this.oid_Tipo_Faturamento_Importador = oid_Tipo_Faturamento_Importador;
	}
	public long getOid_Tipo_Faturamento_Real() {
		return oid_Tipo_Faturamento_Real;
	}
	public void setOid_Tipo_Faturamento_Real(long oid_Tipo_Faturamento_Real) {
		this.oid_Tipo_Faturamento_Real = oid_Tipo_Faturamento_Real;
	}

//======================= Getters and Setters Transporte Nacional ===============================\\
	public String getDm_Criterio_Comissao() {
		return dm_Criterio_Comissao;
	}
	public void setDm_Criterio_Comissao(String dm_Criterio_Comissao) {
		this.dm_Criterio_Comissao = dm_Criterio_Comissao;
	}
	public String getDm_Despacho() {
		return dm_Despacho;
	}
	public void setDm_Despacho(String dm_Despacho) {
		this.dm_Despacho = dm_Despacho;
	}
	public String getDm_Exige_Pagador_Cliente() {
		return dm_Exige_Pagador_Cliente;
	}
	public void setDm_Exige_Pagador_Cliente(String dm_Exige_Pagador_Cliente) {
		this.dm_Exige_Pagador_Cliente = dm_Exige_Pagador_Cliente;
	}
	public String getDm_Frete_Cortesia() {
		return dm_Frete_Cortesia;
	}
	public void setDm_Frete_Cortesia(String dm_Frete_Cortesia) {
		this.dm_Frete_Cortesia = dm_Frete_Cortesia;
	}
	public String getDm_Imprime_Conhecimento_Ordem_Frete() {
		return dm_Imprime_Conhecimento_Ordem_Frete;
	}
	public void setDm_Imprime_Conhecimento_Ordem_Frete(
			String dm_Imprime_Conhecimento_Ordem_Frete) {
		this.dm_Imprime_Conhecimento_Ordem_Frete = dm_Imprime_Conhecimento_Ordem_Frete;
	}
	public String getDm_Inclui_ICMS_Parcela_CTRC() {
		return dm_Inclui_ICMS_Parcela_CTRC;
	}
	public void setDm_Inclui_ICMS_Parcela_CTRC(String dm_Inclui_ICMS_Parcela_CTRC) {
		this.dm_Inclui_ICMS_Parcela_CTRC = dm_Inclui_ICMS_Parcela_CTRC;
	}
	public String getDm_Tipo_Conhecimento() {
		return dm_Tipo_Conhecimento;
	}
	public void setDm_Tipo_Conhecimento(String dm_Tipo_Conhecimento) {
		this.dm_Tipo_Conhecimento = dm_Tipo_Conhecimento;
	}
	public String getDm_Vincula_Adto_Ordem_Principal() {
		return dm_Vincula_Adto_Ordem_Principal;
	}
	public void setDm_Vincula_Adto_Ordem_Principal(
			String dm_Vincula_Adto_Ordem_Principal) {
		this.dm_Vincula_Adto_Ordem_Principal = dm_Vincula_Adto_Ordem_Principal;
	}
	public String getNf_Multi_Conhecimento() {
		return nf_Multi_Conhecimento;
	}
	public void setNf_Multi_Conhecimento(String nf_Multi_Conhecimento) {
		this.nf_Multi_Conhecimento = nf_Multi_Conhecimento;
	}
	public long getNr_Conhecimentos_Linha_Fatura() {
		return nr_Conhecimentos_Linha_Fatura;
	}
	public void setNr_Conhecimentos_Linha_Fatura(long nr_Conhecimentos_Linha_Fatura) {
		this.nr_Conhecimentos_Linha_Fatura = nr_Conhecimentos_Linha_Fatura;
	}
	public long getNr_Peso_Cubado() {
		return nr_Peso_Cubado;
	}
	public void setNr_Peso_Cubado(long nr_Peso_Cubado) {
		this.nr_Peso_Cubado = nr_Peso_Cubado;
	}
	public long getOid_Produto() {
		return oid_Produto;
	}
	public void setOid_Produto(long oid_Produto) {
		this.oid_Produto = oid_Produto;
	}

//============ Getters and Setters Tela Unidade ================================================================================\\	
	public long getOid_Unidade_Faturamento() {
		return oid_Unidade_Faturamento;
	}
	public void setOid_Unidade_Faturamento(long oid_Unidade_Faturamento) {
		this.oid_Unidade_Faturamento = oid_Unidade_Faturamento;
	}
	public long getOid_Unidade_Faturamento_Minuta() {
		return oid_Unidade_Faturamento_Minuta;
	}
	public void setOid_Unidade_Faturamento_Minuta(
			long oid_Unidade_Faturamento_Minuta) {
		this.oid_Unidade_Faturamento_Minuta = oid_Unidade_Faturamento_Minuta;
	}
	public long getOid_Unidade_Padrao() {
		return oid_Unidade_Padrao;
	}
	public void setOid_Unidade_Padrao(long oid_Unidade_Padrao) {
		this.oid_Unidade_Padrao = oid_Unidade_Padrao;
	}
	
//============ Getters and Setters Tela WMS ===============================================================================\\
	public String getDm_Wms_Nf_Saida_Numerada() {
		return dm_Wms_Nf_Saida_Numerada;
	}
	public void setDm_Wms_Nf_Saida_Numerada(String dm_Wms_Nf_Saida_Numerada) {
		this.dm_Wms_Nf_Saida_Numerada = dm_Wms_Nf_Saida_Numerada;
	}
	public String getNm_Nivel_Produto1() {
		return nm_Nivel_Produto1;
	}
	public void setNm_Nivel_Produto1(String nm_Nivel_Produto1) {
		this.nm_Nivel_Produto1 = nm_Nivel_Produto1;
	}
	public String getNm_Nivel_Produto2() {
		return nm_Nivel_Produto2;
	}
	public void setNm_Nivel_Produto2(String nm_Nivel_Produto2) {
		this.nm_Nivel_Produto2 = nm_Nivel_Produto2;
	}
	public String getNm_Nivel_Produto3() {
		return nm_Nivel_Produto3;
	}
	public void setNm_Nivel_Produto3(String nm_Nivel_Produto3) {
		this.nm_Nivel_Produto3 = nm_Nivel_Produto3;
	}
	public String getNm_Nivel_Produto4() {
		return nm_Nivel_Produto4;
	}
	public void setNm_Nivel_Produto4(String nm_Nivel_Produto4) {
		this.nm_Nivel_Produto4 = nm_Nivel_Produto4;
	}
	public String getNm_Nivel_Produto5() {
		return nm_Nivel_Produto5;
	}
	public void setNm_Nivel_Produto5(String nm_Nivel_Produto5) {
		this.nm_Nivel_Produto5 = nm_Nivel_Produto5;
	}
	public long getOid_Deposito() {
		return oid_Deposito;
	}
	public void setOid_Deposito(long oid_Deposito) {
		this.oid_Deposito = oid_Deposito;
	}
	public long getOid_Embalagem() {
		return oid_Embalagem;
	}
	public void setOid_Embalagem(long oid_Embalagem) {
		this.oid_Embalagem = oid_Embalagem;
	}
	public long getOid_Operador() {
		return oid_Operador;
	}
	public void setOid_Operador(long oid_Operador) {
		this.oid_Operador = oid_Operador;
	}
	public long getOid_Tipo_Estoque() {
		return oid_Tipo_Estoque;
	}
	public void setOid_Tipo_Estoque(long oid_Tipo_Estoque) {
		this.oid_Tipo_Estoque = oid_Tipo_Estoque;
	}
	public long getOid_Tipo_Estoque_Devolucao() {
		return oid_Tipo_Estoque_Devolucao;
	}
	public void setOid_Tipo_Estoque_Devolucao(long oid_Tipo_Estoque_Devolucao) {
		this.oid_Tipo_Estoque_Devolucao = oid_Tipo_Estoque_Devolucao;
	}
	public long getOid_Tipo_Estoque_Troca() {
		return oid_Tipo_Estoque_Troca;
	}
	public void setOid_Tipo_Estoque_Troca(long oid_Tipo_Estoque_Troca) {
		this.oid_Tipo_Estoque_Troca = oid_Tipo_Estoque_Troca;
	}
	public long getOid_Tipo_Pallet() {
		return oid_Tipo_Pallet;
	}
	public void setOid_Tipo_Pallet(long oid_Tipo_Pallet) {
		this.oid_Tipo_Pallet = oid_Tipo_Pallet;
	}
	public long getOid_Tipo_Pedido() {
		return oid_Tipo_Pedido;
	}
	public void setOid_Tipo_Pedido(long oid_Tipo_Pedido) {
		this.oid_Tipo_Pedido = oid_Tipo_Pedido;
	}

//===================================================================================================================================\\	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

}
