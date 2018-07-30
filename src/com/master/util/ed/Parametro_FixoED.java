package com.master.util.ed;

import java.util.Locale;

import com.master.util.*;

import br.cte.model.Empresa;

public class Parametro_FixoED {
  // Vari�vel da inst�ncia para n�o precisar instanciar esta classe
  private static Parametro_FixoED instancia;

  private Empresa empresaEmissao;

  private String serverName;
  private int port;
  private String contexto;
  private String webmaster;
  private int OID_Tipo_Servico_Requisicao_Estoque;

  private long OID_Tipo_Documento_Faturamento;
  private int OID_Tipo_Documento_Ordem_Servico;
  private long OID_Tipo_Documento_Faturamento_Internacional;
  private int OID_Historico_Liquidacao_Cobranca;
  private int OID_Historico_Liquidacao_Cobranca_Retorno;
  private int OID_Carteira_Nota_Retorno;
  private long OID_Carteira_Faturamento;
  private int OID_Condicao_Pagamento_Isento;
  private long OID_Unidade_Faturamento;
  private long OID_Unidade_Faturamento_Minuta;
  private int OID_Centro_Custo_Ordem_Frete;
  private int OID_Conta_Ordem_Frete;
  private int OID_Conta_IR_Ordem_Frete;
  private int OID_Tipo_Documento_IR;
  private int OID_Conta_INSS_Ordem_Frete;
  private int OID_Tipo_Documento_INSS;
  private int OID_Conta_Movimento_Compromisso;
  private int OID_Historico_Bordero;
  private int OID_Tipo_Documento_Ordem_Frete;
  private int OID_Tipo_Documento_Transferencia_Conta_Corrente;
  private int OID_Centro_Custo_Movimento_Ordem_Servico;
  private int OID_Conta_Movimento_Ordem_Servico;
  private int OID_Tipo_Faturamento_Consolidacao_MIC_CRT;
  private String DT_Hoje;
  private String HR_Hoje;
  private double PE_Aliquota_PIS_COFINS;
  private double VL_Faixa1;
  private double VL_Faixa2;
  private double VL_Faixa3;
  private double VL_Faixa4;
  private double VL_Deducao_Faixa1;
  private double VL_Deducao_Faixa2;
  private double VL_Deducao_Faixa3;
  private double VL_Deducao_Faixa4;
  private double PE_Imposto_Faixa1;
  private double PE_Imposto_Faixa2;
  private double PE_Imposto_Faixa3;
  private double PE_Imposto_Faixa4;
  private double PE_Base_Calculo;
  private double VL_Dependente;
  private long OID_CFOP_Pessoa_Fisica;
  private long OID_CFOP_Diferenciado;
  private int OID_Natureza_Operacao_Ordem_Frete;
  private int OID_Conta_Credito_Movimento_Ordem_Servico;

  private int OID_Conta_PIS_COFINS_CSLL;
  private int OID_Conta_INSS;
  private int OID_Conta_IRRF;
  private int OID_Conta_ISSQN;

  private int OID_Conta_Credito_Ordem_Frete;
  private int OID_Conta_Juridica_Ordem_Frete;
  private double VL_Maximo_INSS;
  private long NR_Peso_Cubado;
  private double PE_Aliquota_Empresa_INSS;
  private double PE_Aliquota_Prestador_INSS;
  private double PE_Base_INSS;
  private double PE_Base_SET_SENAT;
  private double PE_Aliquota_Prestador_Set_Senat;
  private String DM_Calcula_INSS;
  private String DM_Calcula_INSS_Para_Motorista;
  private int OID_Conta_Credito_Pedagio;
  private int OID_Conta_Debito_Pedagio;
  private int OID_Conta_Multa_Transito;
  private int OID_Conta_Credito_Master;
  private int OID_Conta_Debito_Master;
  private int OID_Natureza_Operacao_Master;
  private int OID_Centro_Custo_Master;
  private int OID_Tipo_Documento_Master;
  private int OID_Tipo_Movimento_Custo_Entrega;
  private int OID_Tipo_Movimento_Custo_Previsto_Entrega;
  private int OID_Tipo_Movimento_Custo_Master;
  private int OID_Tipo_Movimento_Custo_Ordem_Frete;
  private int OID_Tipo_Movimento_Ressarcimento;
  private int OID_Tipo_Movimento_Custo_Coleta;
  private int OID_Tipo_Movimento_Custo_Transferencia;
  private int OID_Tipo_Movimento_Custo_Coleta_Transferencia;
  private int OID_Tipo_Movimento_Custo_Transferencia_Entrega;
  private int OID_Tipo_Movimento_Custo_Coleta_Transferencia_Entrega;
  private int OID_Tipo_Movimento_Custo_Desconto;
  private int OID_Tipo_Movimento_Custo_Devolucao;
  private int OID_Conta_Credito_Entrega;
  private int OID_Conta_Debito_Entrega;
  private int OID_Tipo_Documento_Entrega;
  private int OID_Centro_Custo_Entrega;
  private int OID_Natureza_Operacao_Entrega;
  private String DM_Despacho;
  private String DM_Tipo_Calculo_ICMS;

  private String DM_Atualiza_Odometro_Veiculo;
  private String DM_Atualiza_Odometro_Acerto;
  private String DM_Atualiza_Odometro_Ordem_Servico;
  private String DM_Atualiza_Odometro_Pneus;
  private String DM_Atualiza_Odometro_Portaria;
  private String DM_Atualiza_Odometro_Abastecimento;
  private String DM_Atualiza_Odometro_Rastreador;

  private int OID_Unidade_Padrao;
  private int OID_Moeda_Padrao;
  private int OID_Moeda_Dollar;
  private String DM_Exige_Pagador_Cliente;
  private int OID_Conta_Credito_Ordem_Frete_Adiantamento;
  private int OID_Conta_Ordem_Frete_Adiantamento;
  private int OID_Conta_Juridica_Ordem_Frete_Adiantamento;
  private int OID_Tipo_Documento_Ordem_Frete_Adiantamento;
  private String DM_Imprime_Conhecimento_Ordem_Frete;
  private String DM_Tipo_Conhecimento;
  private String DM_Gera_Embarque_Viagem;
  private String DM_Gera_Rateio_Custo_Ordem_Frete;
  private String DM_Gera_Ocorrencia_Viagem;
  private String DM_Gera_Compromisso_Viagem;
  private String DM_Gera_Custo_Viagem;
  private String DM_Gera_Compromisso_Nota_Fiscal_Compra;
  private String DM_Gera_Compromisso_Ordem_Frete;
  private String DM_Gera_Compromisso_Ordem_Frete_Adiantamento;
  private String DM_Gera_Compromisso_Ordem_Abastecimento;
  private String DM_Gera_Compromisso_IR_Ordem_Frete = "S";
  private String DM_Gera_Compromisso_INSS_Ordem_Frete = "N";
  private String DM_Gera_Compromisso_Master;
  private String DM_Estado_Origem_Taxas;
  private String DM_Estado_Destino_Taxas;
  private String DM_Subtrai_Pedagio_PIS_COFINS;
  private int OID_Tipo_Instrucao_Remessa;
  private String OID_Cliente_Complemento_Padrao;
  private String DM_Numera_Ordem_Frete;
  private String DM_Numera_Coleta;
  private String NM_Sucessivos;
  private String DM_Numera_Manifesto;
  private String DM_Numera_Conhecimento;
  private String DM_Numera_Frete_Internacional;
  private String DM_Numera_Fatura;
  private String DM_Soma_Pedagio_Saldo_Frete;
  private String DM_Soma_Sest_Senat_Saldo_Frete;
  private String DM_Soma_IRRF_Saldo_Frete;
  private String DM_Soma_INSS_Prestador_Saldo_Frete;
  private String DM_Soma_Outros_Saldo_Frete;

  private String DM_Soma_Coleta_Saldo_Frete;
  private String DM_Soma_Carga_Saldo_Frete;
  private String DM_Soma_Descarga_Saldo_Frete;
  private String DM_Soma_Premio_Saldo_Frete;
  private String DM_Soma_Descontos_Saldo_Frete;

  private String DM_Dia_Nao_Util;

  private String DM_Criterio_Comissao;
  private String DM_Calcula_IRRF;
  private String DM_Frete_Cortesia;
  private double VL_Frete_Cortesia;
  private String VL_Reembolso;
  private String DM_Calcula_PIS_COFINS;
  private double PE_Aliquota_COFINS;
  private double PE_Aliquota_PIS;
  private int OID_Tipo_Instrucao_Pago_Total;
  private int OID_Tipo_Instrucao_Taxa_Cobranca;
  private int OID_Tipo_Instrucao_Pago_Cartorio;
  private int OID_Tipo_Instrucao_Pago_Parcial;
  private int OID_Tipo_Instrucao_Devolucao_Nota_Fiscal;
  private int OID_Tipo_Instrucao_Tarifa;
  private int OID_Tipo_Instrucao_Juros;
  private int OID_Tipo_Instrucao_Desconto;
  private int OID_Tipo_Instrucao_Estorno;
  private int OID_Tipo_Instrucao_Imposto_Retido1;
  private int OID_Tipo_Instrucao_Imposto_Retido2;

  private String DM_Vincula_Adto_Ordem_Principal;
  private String DM_Quebra_Faturamento_Tipo_Conhecimento;
  private String DM_Quebra_Faturamento_Unidade;
  private String DM_Quebra_Faturamento_Tipo_Faturamento;
  private String DM_Gera_Lancamento_Contabil;
  private String DM_Gera_Lancamento_Contabil_Acerto;
  private String DM_Gera_Lancamento_Conta_Corrente_Ordem_Frete_Terceiro;
  private String DM_Gera_Lancamento_Conta_Corrente_Ordem_Frete_Adiantamento;
  private String DM_Gera_Lancamento_Conta_Corrente_Ordem_Frete;
  private String DM_Gera_Lancamento_Conta_Corrente_Acerto_Contas;
  private int OID_Tipo_Instrucao_Variacao_Cambial_Ativa;
  private int OID_Tipo_Instrucao_Variacao_Cambial_Passiva;
  private int OID_Tipo_Instrucao_Valor_Reembolso;
  private int OID_Tipo_Instrucao_Juros_Reembolso;
  private int OID_Historico_Transferencia_Banco;
  private int OID_Historico_Transferencia_Caixa;
  private int OID_Historico_Compensacao;
  private int OID_Historico_Estorno;
  private int OID_Historico_Devolucao_Cheque;
  private int OID_Historico_Cancelamento_Lote_Pagamento;
  private int OID_Historico_Lancamento_Ordem_Frete_Terceiro;
  private int OID_Historico_Lancamento_Ordem_Frete;
  private int OID_Historico_Lancamento_Ordem_Frete_Adiantamento;
  private int OID_Historico_Cancelamento_Ordem_Frete;
  private int OID_Tipo_Ocorrencia_Desconto_CTRC;
  private int OID_Tipo_Ocorrencia_Estorno_CTRC;
  private int OID_Tipo_Ocorrencia_Cancelamento_CTRC;
  private int OID_Tipo_Ocorrencia_Devolucao_CTRC;
  private int OID_Tipo_Ocorrencia_Reentrega_CTRC;
  private int OID_Tipo_Movimento_Produto;
  private String NM_Nivel_Produto1;
  private String NM_Nivel_Produto2;
  private String NM_Nivel_Produto3;
  private String NM_Nivel_Produto4;
  private String NM_Nivel_Produto5;
  private int OID_Deposito;
  private int OID_Operador;
  private int OID_Modal;
  private int OID_Modal_Aereo_Standard;
  private int OID_Modal_Aereo_Expressa;
  private int OID_Modal_Aereo_Emergencial;
  private int OID_Modal_Aereo_Sedex;
  private int OID_Modal_Aereo_Pac;
  private int OID_Modal_Aereo_RodExp;
  private int OID_Embalagem;
  private long oid_Tipo_Pedido;
  private int OID_Tipo_Estoque;
  private int OID_Tipo_Estoque_Devolucao;
  private int OID_Tipo_Estoque_Troca;
  private int OID_Tipo_Pallet;
  private int OID_Localizacao;
  private int OID_Localizacao_Devolucao;
  private int OID_Localizacao_Troca;
  private int OID_Conta_Frete_Terceiros;
  private int OID_Conta_Desconto_Frete;
  private int OID_Conta_Despesa_Bancaria;
  private int OID_Conta_Acerto_Contas;
  private int oid_Tipo_Documento_Nota_Fiscal_Entrada;
  private int oid_Tipo_Documento_Nota_Fiscal_Saida;
  private int oid_Modelo_Nota_Fiscal;
  private int oid_Modelo_NF_DevFornecedor_Dentro_Estado;
  private int oid_Modelo_NF_DevFornecedor_Fora_Estado;
  private String NM_Versao;
  private String NM_Razao_Social_Empresa;
  private String DM_Situacao;
  private String DM_Gera_Compromisso_Movimento_Ordem_Servico;
  private String DM_Atualiza_Movimento_Caixa;
  private String DM_Tipo_Sistema;
  private String DM_Operacao_Sistema;
  private String DM_Perfil_Sistema;
  private String DM_Inclui_ICMS_Parcela_CTRC;
  private String DM_Formulario_Consolidacao_MIC;
  private String DM_Formulario_Consolidacao_MIC_CRT;
  private String DM_Formulario_Duplicata_Proform;
  private String DM_Formulario_Duplicata;
  private String DM_Formulario_Demonstrativo_Cobranca;
  private String DM_Formulario_Protocolo_Cobranca;
  private String DM_Formulario_Duplicata_Internacional;
  private String DM_Formulario_Minuta;
  private String DM_Formulario_ACT;
  private String DM_Formulario_Conhecimento_Nacional;
  private String DM_Formulario_Conhecimento_Internacional;
  private String DM_Formulario_Conhecimento_Internacional_Verso;
  private String DM_Formulario_Coleta;
  private String DM_Formulario_Ordem_Frete;
  private String DM_Formulario_Ordem_Frete_Adiantamento;
  private String DM_Formulario_Ordem_Abastecimento;
  private String DM_Formulario_Nota_Fiscal;

  private String DM_Formulario_Ordem_Servico;
  private String DM_Formulario_Manifesto;
  private String DM_Formulario_Manifesto_Redespacho;
  private String DM_Formulario_Romaneio_Notal_Fiscal;
  private String DM_Formulario_Acerto_Contas;
  private String DM_Formulario_Romaneio_Entrega;
  private double PE_Aliquota_CSLL;
  private String document_Form_Action;
  private String EDI_Contabil;
  private String EDI_Seguradora;
  private String EDI_Banco;
  private String EDI_Cliente;
  private String EDI_Fiscal;
  private String EDI_Duplicata;
  private boolean completa_TX_Via;
  private String TX_Via_Primeiro_Original_Pt;
  private String TX_Via_Segundo_Original_Pt;
  private String TX_Via_Terceiro_Original_Pt;
  private String TX_Via_Primeiro_Original_Es;
  private String TX_Via_Segundo_Original_Es;
  private String TX_Via_Terceiro_Original_Es;
  private long oid_Tipo_Faturamento_Exportador;
  private long oid_Tipo_Faturamento_Importador;
  private String DM_Liquida_Compromisso;
  private String dm_Impressao_Tramo;
  private boolean NF_Multi_Conhecimento;
  private String PATH_RELATORIOS;
  private String PATH_RELATORIOS_XSL;
  private String PATH_IMAGENS;
  private long NR_Vias_Ordem_Frete_Adiantamento;
  private long NR_Vias_Ordem_Frete;
  private String DM_Saldo_Programado = "N";
  private int OID_Tipo_Servico_Acerto_Contas;
  private int OID_Tipo_Servico_Abastecimento;
  private int OID_Produto;
  private int NR_Conhecimentos_Linha_Fatura;
  private String OID_Pessoa_Padrao_Tabela_Frete;
  private long OID_Tipo_Documento_Faturamento_Nota_Fiscal_Servico;
  private String NM_Modelo_Tabela_Rodoviario = "N";
  private String NM_Modelo_Tabela_Aereo = "N";
  private String NM_Modelo_Tabela_Documento = "N";
  private String CFOP_Padrao;
  private String DM_Tipo_Impressao_Fatura = "XSL";
  private String DM_Tipo_Impressao_Ordem_Frete = "XSL";
  private String appPath = "/data/sistema";
  private String palmPath = "/mnt/ftproot";
  private String palmModelo = "E";
  private String doc_ePath = "/nfe";
  private boolean logTransactions;
  private String DM_Estado_Origem_CFOP;
  private String DM_Estado_Destino_CFOP;
  private int OID_Tipo_Movimento_Transf = 3;
  private int OID_Tipo_Movimento_Ajuste_E = 4;
  private int OID_Tipo_Movimento_Ajuste_S = 5;
  private int OID_Tipo_Movimento_Ajuste_Canc = 8; //Cancelamento
  private int OID_Tipo_Movimento_Ajuste_Exc = 9; //Exclusao
  private String DM_Formulario_AWB;
  private boolean movimento_Ordem_Servico_Duplicada;
  private String textoImprimirCampoNaoValidado = "(n�o possui)";
  private double PE_Comissao_Motorista;
  private double PE_Base_Comissao_Motorista;
  private String DM_Base_Comissao_Motorista;
  private double PE_Comissao_Motorista_Media;
  private String DM_Limite_Credito_Motorista_Adiantamento_Frete;
  private String DM_Multi_Moeda;
  private String OID_Fornecedor_INSS;
  private String OID_Fornecedor_IRRF;

  private String OID_Fornecedor_COFINS;
  private String OID_Fornecedor_CSLL;
  private String OID_Fornecedor_ISSQN;
  private String OID_Fornecedor_PIS;

  private boolean geraCodigoCliente = false;
  private String DM_Conhecimento_Varios_Manifestos;
  private String DM_Tipo_Impressao_Conhecimento_Nacional;
  private String DM_Tipo_Impressao_Minuta;
  private String DM_Tipo_Impressao_Nota_Fiscal_Servico;
  private String DM_Valida_CEP;
  private String DM_Exige_Validade_Lib_Seguradora_Motorista = "N";
  private String dmUsaCTB = "F";
  private String DM_Tipo_Impressao_Coleta;
  private int OID_Tipo_Movimento_Custo_Reentrega;
  private String DM_Formulario_Nota_Fiscal_Servico;
  private String DM_Gera_Compromisso_Motorista_Proprietario;
  private String DM_Tranca_Saldo_Ordem_Frete = "N";
  private String DM_Verifica_CNPJ_CPF_Pessoa = "N";
  private String dm_Wms_Nf_Saida_Numerada = "S";

  private String DM_Calcula_Previsao_Entrega="N";
  private String DT_Inicial_Previsao_Entrega="EMISSAO_CTO";
  private String DM_Comissao_Informada;

  private String DM_Gera_Livro_Fiscal = "N";

  private int OID_Tipo_Faturamento_Real = 0;

  private String NM_Ano_Permisso;

  private int vias_Fatura = 1;

  private String DM_Envia_Email_Eventos = "N";

  private boolean exigePedidoNotaCompra;

  public static synchronized Parametro_FixoED getInstancia () {
    if (instancia == null) {
      instancia = new Parametro_FixoED ();
      Locale loc = new Locale("pt","BR");
      Locale.setDefault(loc);
    }
    return instancia;
  }

  public String getEDI_Seguradora () {
    return EDI_Seguradora;
  }

  public String getEDI_Duplicata () {
    return EDI_Duplicata;
  }

  public String getEDI_Fiscal () {
    return EDI_Fiscal;
  }

  public String getEDI_Contabil () {
    return EDI_Contabil;
  }

  public String getEDI_Cliente () {
    return EDI_Cliente;
  }

  public String getEDI_Banco () {
    return EDI_Banco;
  }

  public String getDM_Liquida_Compromisso () {

    return DM_Liquida_Compromisso;
  }

  public String getNM_Modelo_Tabela_Rodoviario () {
    return NM_Modelo_Tabela_Rodoviario;
  }

  public String getNM_Modelo_Tabela_Documento () {
    return NM_Modelo_Tabela_Documento;
  }

  public String getNM_Modelo_Tabela_Aereo () {
    return NM_Modelo_Tabela_Aereo;
  }

  public int getOID_Tipo_Documento_Transferencia_Conta_Corrente () {
    return OID_Tipo_Documento_Transferencia_Conta_Corrente;
  }

  public int getOID_Historico_Cancelamento_Lote_Pagamento () {
    return OID_Historico_Cancelamento_Lote_Pagamento;
  }

  public long getOID_Tipo_Documento_Faturamento_Nota_Fiscal_Servico () {
    return OID_Tipo_Documento_Faturamento_Nota_Fiscal_Servico;
  }

  public String getDM_Formulario_Minuta () {
    return DM_Formulario_Minuta;
  }

  public int getOID_Historico_Lancamento_Ordem_Frete_Adiantamento () {
    return OID_Historico_Lancamento_Ordem_Frete_Adiantamento;
  }

  public int getOID_Historico_Lancamento_Ordem_Frete () {
    return OID_Historico_Lancamento_Ordem_Frete;
  }

  public String getDM_Gera_Lancamento_Conta_Corrente_Ordem_Frete_Adiantamento () {
    return DM_Gera_Lancamento_Conta_Corrente_Ordem_Frete_Adiantamento;
  }

  public String getDM_Gera_Lancamento_Conta_Corrente_Ordem_Frete () {
    return DM_Gera_Lancamento_Conta_Corrente_Ordem_Frete;
  }

  public int getOID_Tipo_Instrucao_Devolucao_Nota_Fiscal () {

    return OID_Tipo_Instrucao_Devolucao_Nota_Fiscal;
  }

  public String getDM_Gera_Lancamento_Conta_Corrente_Ordem_Frete_Terceiro () {
    return DM_Gera_Lancamento_Conta_Corrente_Ordem_Frete_Terceiro;
  }

  public int getOID_Historico_Lancamento_Ordem_Frete_Terceiro () {
    return OID_Historico_Lancamento_Ordem_Frete_Terceiro;
  }

  public String getDM_Saldo_Programado () {
    return DM_Saldo_Programado;
  }

  public void setDM_Saldo_Programado (String saldo_Programado) {
    DM_Saldo_Programado = saldo_Programado;
  }

  public String getDM_Formulario_Romaneio_Entrega () {
    return this.DM_Formulario_Romaneio_Entrega;
  }

  public void setDM_Formulario_Romaneio_Entrega (String formulario_Romaneio_Entrega) {
    this.DM_Formulario_Romaneio_Entrega = formulario_Romaneio_Entrega;
  }

  public int getOID_Produto () {
    return OID_Produto;
  }

  public void setOID_Produto (int OID_Produto) {
    this.OID_Produto = OID_Produto;
  }

  public int getNR_Conhecimentos_Linha_Fatura () {
    return NR_Conhecimentos_Linha_Fatura;
  }

  public void setNR_Conhecimentos_Linha_Fatura (int NR_Conhecimentos_Linha_Fatura) {
    this.NR_Conhecimentos_Linha_Fatura = NR_Conhecimentos_Linha_Fatura;
  }

  public String getOID_Pessoa_Padrao_Tabela_Frete () {
    return this.OID_Pessoa_Padrao_Tabela_Frete;
  }

  public void setOID_Pessoa_Padrao_Tabela_Frete (String pessoa_Padrao_Tabela_Frete) {
    this.OID_Pessoa_Padrao_Tabela_Frete = pessoa_Padrao_Tabela_Frete;
  }

  public int getOID_Tipo_Estoque_Devolucao () {
    return OID_Tipo_Estoque_Devolucao;
  }

  public void setOID_Tipo_Estoque_Devolucao (int tipo_Estoque_Devolucao) {
    OID_Tipo_Estoque_Devolucao = tipo_Estoque_Devolucao;
  }

  public int getOID_Localizacao_Devolucao () {
    return OID_Localizacao_Devolucao;
  }

  public void setOID_Localizacao_Devolucao (int localizacao_Devolucao) {
    OID_Localizacao_Devolucao = localizacao_Devolucao;
  }

  public int getOID_Localizacao_Troca () {
    return OID_Localizacao_Troca;
  }

  public void setOID_Localizacao_Troca (int localizacao_Troca) {
    OID_Localizacao_Troca = localizacao_Troca;
  }

  public int getOID_Tipo_Estoque_Troca () {
    return OID_Tipo_Estoque_Troca;
  }

  public void setOID_Tipo_Estoque_Troca (int tipo_Estoque_Troca) {
    OID_Tipo_Estoque_Troca = tipo_Estoque_Troca;
  }

  public String getCFOP_Padrao () {
    return this.CFOP_Padrao;
  }

  public void setCFOP_Padrao (String padrao) {
    this.CFOP_Padrao = padrao;
  }

  public String getAppPath () {
    return appPath;
  }

  public void setAppPath (String appPath) {
    this.appPath = appPath;
  }

  public String getRelatoriosMatricialOutput () {
    return getAppPath () + "/relatoriosmatricial/output";
  }

  public String getRelatoriosMatricialPath () {
    return getAppPath () + "/relatoriosmatricial";
  }

  public boolean isLogTransactions () {
    return logTransactions;
  }

  public void setLogTransactions (boolean logTransactions) {
    this.logTransactions = logTransactions;
  }

  public String getDM_Estado_Destino_CFOP () {
    return DM_Estado_Destino_CFOP;
  }

  public void setDM_Estado_Destino_CFOP (String estado_Destino_CFOP) {
    DM_Estado_Destino_CFOP = estado_Destino_CFOP;
  }

  public String getDM_Estado_Origem_CFOP () {
    return DM_Estado_Origem_CFOP;
  }

  public void setDM_Estado_Origem_CFOP (String estado_Origem_CFOP) {
    DM_Estado_Origem_CFOP = estado_Origem_CFOP;
  }

  public int getOid_Tipo_Movimento_Ajuste_E () {
    return OID_Tipo_Movimento_Ajuste_E;
  }

  public void setOid_Tipo_Movimento_Ajuste_E (int oid_Tipo_Movimento_Ajuste_E) {
    this.OID_Tipo_Movimento_Ajuste_E = oid_Tipo_Movimento_Ajuste_E;
  }

  public int getOid_Tipo_Movimento_Ajuste_S () {
    return OID_Tipo_Movimento_Ajuste_S;
  }

  public void setOid_Tipo_Movimento_Ajuste_S (int oid_Tipo_Movimento_Ajuste_S) {
    this.OID_Tipo_Movimento_Ajuste_S = oid_Tipo_Movimento_Ajuste_S;
  }

  public int getOid_Tipo_Movimento_Transf () {
    return OID_Tipo_Movimento_Transf;
  }

  public void setOid_Tipo_Movimento_Transf (int oid_Tipo_Movimento_Transf) {
    this.OID_Tipo_Movimento_Transf = oid_Tipo_Movimento_Transf;
  }

  public String getDM_Tipo_Calculo_ICMS () {
    return this.DM_Tipo_Calculo_ICMS;
  }

  public void setDM_Tipo_Calculo_ICMS (String tipo_Calculo_ICMS) {
    this.DM_Tipo_Calculo_ICMS = tipo_Calculo_ICMS;
  }

  public int getOID_Modal_Aereo_Standard () {
    return this.OID_Modal_Aereo_Standard;
  }

  public void setOID_Modal_Aereo_Standard (int modal_Aereo_Standard) {
    this.OID_Modal_Aereo_Standard = modal_Aereo_Standard;
  }

  public String getDM_Formulario_AWB () {
    return this.DM_Formulario_AWB;
  }

  public void setDM_Formulario_AWB (String formulario_AWB) {
    this.DM_Formulario_AWB = formulario_AWB;
  }

  public int getOID_Moeda_Padrao () {
    return OID_Moeda_Padrao;
  }

  public void setOID_Moeda_Padrao (int moeda_Padrao) {
    OID_Moeda_Padrao = moeda_Padrao;
  }

  public boolean isMovimento_Ordem_Servico_Duplicada () {
    return this.movimento_Ordem_Servico_Duplicada;
  }

  public int getOid_Modelo_NF_DevFornecedor_Dentro_Estado () {
    return oid_Modelo_NF_DevFornecedor_Dentro_Estado;
  }

  public void setMovimento_Ordem_Servico_Duplicada (boolean movimento_Ordem_Servico_Duplicada) {
    this.movimento_Ordem_Servico_Duplicada = movimento_Ordem_Servico_Duplicada;
  }

  public String getTextoImprimirCampoNaoValidado () {
    return this.textoImprimirCampoNaoValidado;
  }

  public void setTextoImprimirCampoNaoValidado (String textoImprimirCampoNaoValidado) {
    this.textoImprimirCampoNaoValidado = textoImprimirCampoNaoValidado;
  }

  public void setOid_Modelo_NF_DevFornecedor_Dentro_Estado (int oid_Modelo_NF_DevFornecedor_Dentro_Estado) {
    this.oid_Modelo_NF_DevFornecedor_Dentro_Estado = oid_Modelo_NF_DevFornecedor_Dentro_Estado;
  }

  public int getOid_Modelo_NF_DevFornecedor_Fora_Estado () {
    return oid_Modelo_NF_DevFornecedor_Fora_Estado;
  }

  public String getDM_Gera_Embarque_Viagem () {
    return DM_Gera_Embarque_Viagem;
  }

  public int getOID_Conta_Acerto_Contas () {
    return OID_Conta_Acerto_Contas;
  }

  public long getNR_Peso_Cubado () {
    return NR_Peso_Cubado;
  }

  public String getDM_Gera_Lancamento_Conta_Corrente_Acerto_Contas () {
    return DM_Gera_Lancamento_Conta_Corrente_Acerto_Contas;
  }

  public String getDM_Multi_Moeda () {
    return DM_Multi_Moeda;
  }

  public double getPE_Comissao_Motorista_Media () {
    return PE_Comissao_Motorista_Media;
  }

  public long getNR_Vias_Ordem_Frete () {
    return NR_Vias_Ordem_Frete;
  }

  public void setOid_Modelo_NF_DevFornecedor_Fora_Estado (int oid_Modelo_NF_DevFornecedor_Fora_Estado) {
    this.oid_Modelo_NF_DevFornecedor_Fora_Estado = oid_Modelo_NF_DevFornecedor_Fora_Estado;
  }

  public void setDM_Gera_Embarque_Viagem (String DM_Gera_Embarque_Viagem) {
    this.DM_Gera_Embarque_Viagem = DM_Gera_Embarque_Viagem;
  }

  public void setOID_Conta_Acerto_Contas (int OID_Conta_Acerto_Contas) {
    this.OID_Conta_Acerto_Contas = OID_Conta_Acerto_Contas;
  }

  public void setNR_Peso_Cubado (long NR_Peso_Cubado) {
    this.NR_Peso_Cubado = NR_Peso_Cubado;
  }

  public void setDM_Gera_Lancamento_Conta_Corrente_Acerto_Contas (String DM_Gera_Lancamento_Conta_Corrente_Acerto_Contas) {
    this.DM_Gera_Lancamento_Conta_Corrente_Acerto_Contas = DM_Gera_Lancamento_Conta_Corrente_Acerto_Contas;
  }

  public void setDM_Multi_Moeda (String DM_Multi_Moeda) {
    this.DM_Multi_Moeda = DM_Multi_Moeda;
  }

  public void setPE_Comissao_Motorista_Media (double PE_Comissao_Motorista_Media) {
    this.PE_Comissao_Motorista_Media = PE_Comissao_Motorista_Media;
  }

  public void setNR_Vias_Ordem_Frete (long NR_Vias_Ordem_Frete) {
    this.NR_Vias_Ordem_Frete = NR_Vias_Ordem_Frete;
  }

  public int getOID_Historico_Liquidacao_Cobranca () {
    return OID_Historico_Liquidacao_Cobranca;
  }

  public void setOID_Historico_Liquidacao_Cobranca (int historico_Liquidacao_Cobranca) {
    OID_Historico_Liquidacao_Cobranca = historico_Liquidacao_Cobranca;
  }

  public int getOID_Historico_Devolucao_Cheque () {
    return OID_Historico_Devolucao_Cheque;
  }

  public void setOID_Historico_Devolucao_Cheque (int historico_Devolucao_Cheque) {
    OID_Historico_Devolucao_Cheque = historico_Devolucao_Cheque;
  }

  public int getOID_Condicao_Pagamento_Isento () {
    return OID_Condicao_Pagamento_Isento;
  }

  public void setOID_Condicao_Pagamento_Isento (int condicao_Pagamento_Isento) {
    OID_Condicao_Pagamento_Isento = condicao_Pagamento_Isento;
  }

  public boolean isGeraCodigoCliente () {
    return geraCodigoCliente;
  }

  public String getDM_Imprime_Conhecimento_Ordem_Frete () {
    return DM_Imprime_Conhecimento_Ordem_Frete;
  }

  public void setGeraCodigoCliente (boolean geraCodigoCliente) {
    this.geraCodigoCliente = geraCodigoCliente;
  }

  public void setDM_Imprime_Conhecimento_Ordem_Frete (String DM_Imprime_Conhecimento_Ordem_Frete) {
    this.DM_Imprime_Conhecimento_Ordem_Frete = DM_Imprime_Conhecimento_Ordem_Frete;
  }

  /**
   * - Contabilidade - por Regis em out/2005
   */
  public String getDmUsaCTB () {
    return dmUsaCTB;
  }

  public void setDmUsaCTB (String dmUsaCTB) {
    this.dmUsaCTB = dmUsaCTB;
  }

  public String getPalmPath () {
    return palmPath;
  }

  public String getDM_Tipo_Impressao_Conhecimento_Nacional () {
    return DM_Tipo_Impressao_Conhecimento_Nacional;
  }

  public int getOID_Conta_Movimento_Compromisso () {
    return OID_Conta_Movimento_Compromisso;
  }

  public int getOID_Tipo_Movimento_Custo_Transferencia () {
    return OID_Tipo_Movimento_Custo_Transferencia;
  }

  public int getOID_Tipo_Movimento_Transf () {
    return OID_Tipo_Movimento_Transf;
  }

  public int getOID_Tipo_Movimento_Custo_Transferencia_Entrega () {
    return OID_Tipo_Movimento_Custo_Transferencia_Entrega;
  }

  public int getOID_Tipo_Movimento_Custo_Coleta_Transferencia_Entrega () {
    return OID_Tipo_Movimento_Custo_Coleta_Transferencia_Entrega;
  }

  public int getOID_Tipo_Movimento_Custo_Coleta_Transferencia () {
    return OID_Tipo_Movimento_Custo_Coleta_Transferencia;
  }

  public String getDM_Conhecimento_Varios_Manifestos () {

    return DM_Conhecimento_Varios_Manifestos;
  }

  public String getDM_Soma_Outros_Saldo_Frete () {
    return DM_Soma_Outros_Saldo_Frete;
  }

  public int getOID_Tipo_Instrucao_Taxa_Cobranca () {
    return OID_Tipo_Instrucao_Taxa_Cobranca;
  }

  public String getDM_Tipo_Conhecimento () {
    return DM_Tipo_Conhecimento;
  }

  public int getOID_Historico_Cancelamento_Ordem_Frete () {
    return OID_Historico_Cancelamento_Ordem_Frete;
  }

  public int getOID_Tipo_Documento_Ordem_Servico () {
    return OID_Tipo_Documento_Ordem_Servico;
  }

  public void setPalmPath (String palmPath) {
    this.palmPath = palmPath;
  }

  public void setDM_Tipo_Impressao_Conhecimento_Nacional (String DM_Tipo_Impressao_Conhecimento_Nacional) {
    this.DM_Tipo_Impressao_Conhecimento_Nacional = DM_Tipo_Impressao_Conhecimento_Nacional;
  }

  public void setOID_Conta_Movimento_Compromisso (int OID_Conta_Movimento_Compromisso) {
    this.OID_Conta_Movimento_Compromisso = OID_Conta_Movimento_Compromisso;
  }

  public void setOID_Tipo_Movimento_Custo_Transferencia (int OID_Tipo_Movimento_Custo_Transferencia) {
    this.OID_Tipo_Movimento_Custo_Transferencia = OID_Tipo_Movimento_Custo_Transferencia;
  }

  public void setOID_Tipo_Movimento_Transf (int OID_Tipo_Movimento_Transf) {
    this.OID_Tipo_Movimento_Transf = OID_Tipo_Movimento_Transf;
  }

  public void setOID_Tipo_Movimento_Custo_Transferencia_Entrega (int OID_Tipo_Movimento_Custo_Transferencia_Entrega) {
    this.OID_Tipo_Movimento_Custo_Transferencia_Entrega = OID_Tipo_Movimento_Custo_Transferencia_Entrega;
  }

  public void setOID_Tipo_Movimento_Custo_Coleta_Transferencia_Entrega (int OID_Tipo_Movimento_Custo_Coleta_Transferencia_Entrega) {
    this.OID_Tipo_Movimento_Custo_Coleta_Transferencia_Entrega = OID_Tipo_Movimento_Custo_Coleta_Transferencia_Entrega;
  }

  public void setOID_Tipo_Movimento_Custo_Coleta_Transferencia (int OID_Tipo_Movimento_Custo_Coleta_Transferencia) {
    this.OID_Tipo_Movimento_Custo_Coleta_Transferencia = OID_Tipo_Movimento_Custo_Coleta_Transferencia;
  }

  public void setDM_Conhecimento_Varios_Manifestos (String DM_Conhecimento_Varios_Manifestos) {

    this.DM_Conhecimento_Varios_Manifestos = DM_Conhecimento_Varios_Manifestos;
  }

  public void setDM_Soma_Outros_Saldo_Frete (String DM_Soma_Outros_Saldo_Frete) {
    this.DM_Soma_Outros_Saldo_Frete = DM_Soma_Outros_Saldo_Frete;
  }

  public void setOID_Tipo_Instrucao_Taxa_Cobranca (int OID_Tipo_Instrucao_Taxa_Cobranca) {
    this.OID_Tipo_Instrucao_Taxa_Cobranca = OID_Tipo_Instrucao_Taxa_Cobranca;
  }

  public void setDM_Tipo_Conhecimento (String DM_Tipo_Conhecimento) {
    this.DM_Tipo_Conhecimento = DM_Tipo_Conhecimento;
  }

  public void setOID_Historico_Cancelamento_Ordem_Frete (int OID_Historico_Cancelamento_Ordem_Frete) {
    this.OID_Historico_Cancelamento_Ordem_Frete = OID_Historico_Cancelamento_Ordem_Frete;
  }

  public void setOID_Tipo_Documento_Ordem_Servico (int OID_Tipo_Documento_Ordem_Servico) {
    this.OID_Tipo_Documento_Ordem_Servico = OID_Tipo_Documento_Ordem_Servico;
  }

  public String getOID_Fornecedor_INSS () {
    return OID_Fornecedor_INSS;
  }

  public void setOID_Fornecedor_INSS (String fornecedor_INSS) {
    OID_Fornecedor_INSS = fornecedor_INSS;
  }

  public String getOID_Fornecedor_IRRF () {
    return OID_Fornecedor_IRRF;
  }

  public void setOID_Fornecedor_IRRF (String fornecedor_IRRF) {
    OID_Fornecedor_IRRF = fornecedor_IRRF;
  }

  public int getOID_Moeda_Dollar () {
    return OID_Moeda_Dollar;
  }

  public void setOID_Moeda_Dollar (int moeda_Dollar) {
    OID_Moeda_Dollar = moeda_Dollar;
  }

  public String getWebmaster () {
    return webmaster;
  }

  public String getDM_Formulario_Nota_Fiscal_Servico () {
    return DM_Formulario_Nota_Fiscal_Servico;
  }

  public int getOID_Tipo_Movimento_Custo_Reentrega () {
    return OID_Tipo_Movimento_Custo_Reentrega;
  }

  public String getDM_Gera_Rateio_Custo_Ordem_Frete () {
    return DM_Gera_Rateio_Custo_Ordem_Frete;
  }

  public String getDM_Formulario_Manifesto_Redespacho () {
    return DM_Formulario_Manifesto_Redespacho;
  }

  public String getDM_Formulario_Demonstrativo_Cobranca () {
    return DM_Formulario_Demonstrativo_Cobranca;
  }

  public String getDM_Base_Comissao_Motorista () {
    return DM_Base_Comissao_Motorista;
  }

  public double getPE_Comissao_Motorista () {
    return PE_Comissao_Motorista;
  }

  public double getPE_Base_Comissao_Motorista () {
    return PE_Base_Comissao_Motorista;
  }

  public String getDM_Tipo_Impressao_Coleta () {
    return DM_Tipo_Impressao_Coleta;
  }

  public String getDM_Limite_Credito_Motorista_Adiantamento_Frete () {
    return DM_Limite_Credito_Motorista_Adiantamento_Frete;
  }

  public void setWebmaster (String webmaster) {
    this.webmaster = webmaster;
  }

  public void setDM_Formulario_Nota_Fiscal_Servico (String DM_Formulario_Nota_Fiscal_Servico) {
    this.DM_Formulario_Nota_Fiscal_Servico = DM_Formulario_Nota_Fiscal_Servico;
  }

  public void setOID_Tipo_Movimento_Custo_Reentrega (int OID_Tipo_Movimento_Custo_Reentrega) {
    this.OID_Tipo_Movimento_Custo_Reentrega = OID_Tipo_Movimento_Custo_Reentrega;
  }

  public void setDM_Gera_Rateio_Custo_Ordem_Frete (String
                                                   DM_Gera_Rateio_Custo_Ordem_Frete) {
    this.DM_Gera_Rateio_Custo_Ordem_Frete = DM_Gera_Rateio_Custo_Ordem_Frete;
  }

  public void setDM_Formulario_Manifesto_Redespacho (String
                                                     DM_Formulario_Manifesto_Redespacho) {
    this.DM_Formulario_Manifesto_Redespacho =
        DM_Formulario_Manifesto_Redespacho;
  }

  public void setDM_Formulario_Demonstrativo_Cobranca (String
                                                       DM_Formulario_Demonstrativo_Cobranca) {
    this.DM_Formulario_Demonstrativo_Cobranca =
        DM_Formulario_Demonstrativo_Cobranca;
  }

  public void setDM_Base_Comissao_Motorista (String DM_Base_Comissao_Motorista) {
    this.DM_Base_Comissao_Motorista = DM_Base_Comissao_Motorista;
  }

  public void setPE_Comissao_Motorista (double PE_Comissao_Motorista) {
    this.PE_Comissao_Motorista = PE_Comissao_Motorista;
  }

  public void setPE_Base_Comissao_Motorista (double PE_Base_Comissao_Motorista) {
    this.PE_Base_Comissao_Motorista = PE_Base_Comissao_Motorista;
  }

  public void setDM_Tipo_Impressao_Coleta (String DM_Tipo_Impressao_Coleta) {
    this.DM_Tipo_Impressao_Coleta = DM_Tipo_Impressao_Coleta;
  }

  public void setDM_Limite_Credito_Motorista_Adiantamento_Frete (String
                                                                 DM_Limite_Credito_Motorista_Adiantamento_Frete) {
    this.DM_Limite_Credito_Motorista_Adiantamento_Frete =
        DM_Limite_Credito_Motorista_Adiantamento_Frete;
  }

  public int getOID_Tipo_Movimento_Ajuste_Canc () {
    return OID_Tipo_Movimento_Ajuste_Canc;
  }

  public void setOID_Tipo_Movimento_Ajuste_Canc (int tipo_Movimento_Ajuste_Canc) {
    OID_Tipo_Movimento_Ajuste_Canc = tipo_Movimento_Ajuste_Canc;
  }

  public int getOID_Tipo_Movimento_Ajuste_Exc () {
    return OID_Tipo_Movimento_Ajuste_Exc;
  }

  public void setOID_Tipo_Movimento_Ajuste_Exc (int tipo_Movimento_Ajuste_Exc) {
    OID_Tipo_Movimento_Ajuste_Exc = tipo_Movimento_Ajuste_Exc;
  }

  public String getDM_Valida_CEP () {
    return DM_Valida_CEP;
  }

  public void setDM_Valida_CEP (String valida_CEP) {
    DM_Valida_CEP = valida_CEP;
  }

  public String getDM_Gera_Compromisso_INSS_Ordem_Frete () {
    return DM_Gera_Compromisso_INSS_Ordem_Frete;
  }

  public void setDM_Gera_Compromisso_INSS_Ordem_Frete (
      String gera_Compromisso_INSS_Ordem_Frete) {
    DM_Gera_Compromisso_INSS_Ordem_Frete = gera_Compromisso_INSS_Ordem_Frete;
  }

  public String getDM_Gera_Compromisso_IR_Ordem_Frete () {
    return DM_Gera_Compromisso_IR_Ordem_Frete;
  }

  public void setDM_Gera_Compromisso_IR_Ordem_Frete (
      String gera_Compromisso_IR_Ordem_Frete) {
    DM_Gera_Compromisso_IR_Ordem_Frete = gera_Compromisso_IR_Ordem_Frete;
  }

  public int getOID_Conta_INSS_Ordem_Frete () {
    return OID_Conta_INSS_Ordem_Frete;
  }

  public void setOID_Conta_INSS_Ordem_Frete (int conta_INSS_Ordem_Frete) {
    OID_Conta_INSS_Ordem_Frete = conta_INSS_Ordem_Frete;
  }

  public int getOID_Conta_IR_Ordem_Frete () {
    return OID_Conta_IR_Ordem_Frete;
  }

  public void setOID_Conta_IR_Ordem_Frete (int conta_IR_Ordem_Frete) {
    OID_Conta_IR_Ordem_Frete = conta_IR_Ordem_Frete;
  }

  public int getOID_Tipo_Documento_INSS () {
    return OID_Tipo_Documento_INSS;
  }

  public void setOID_Tipo_Documento_INSS (int tipo_Documento_INSS) {
    OID_Tipo_Documento_INSS = tipo_Documento_INSS;
  }

  public int getOID_Tipo_Documento_IR () {
    return OID_Tipo_Documento_IR;
  }

  public void setOID_Tipo_Documento_IR (int tipo_Documento_IR) {
    OID_Tipo_Documento_IR = tipo_Documento_IR;
  }

  public String getDM_Exige_Validade_Lib_Seguradora_Motorista () {
    return DM_Exige_Validade_Lib_Seguradora_Motorista;
  }

  public void setDM_Exige_Validade_Lib_Seguradora_Motorista (
      String exige_Validade_Lib_Seguradora_Motorista) {
    DM_Exige_Validade_Lib_Seguradora_Motorista = exige_Validade_Lib_Seguradora_Motorista;
  }

  public String getDM_Formulario_Consolidacao_MIC () {
    return DM_Formulario_Consolidacao_MIC;
  }

  public void setDM_Formulario_Consolidacao_MIC (String formulario_Consolidacao_MIC) {
    DM_Formulario_Consolidacao_MIC = formulario_Consolidacao_MIC;
  }

  public String getDM_Formulario_Consolidacao_MIC_CRT () {
    return DM_Formulario_Consolidacao_MIC_CRT;
  }

  public void setDM_Formulario_Consolidacao_MIC_CRT (String formulario_Consolidacao_MIC_CRT) {
    DM_Formulario_Consolidacao_MIC_CRT = formulario_Consolidacao_MIC_CRT;
  }

  public String getDM_Formulario_Duplicata_Proform () {
    return DM_Formulario_Duplicata_Proform;
  }

  public void setDM_Formulario_Duplicata_Proform (String formulario_Duplicata_Proform) {
    DM_Formulario_Duplicata_Proform = formulario_Duplicata_Proform;
  }

  public String getDM_Formulario_Duplicata_Internacional () {
    return DM_Formulario_Duplicata_Internacional;
  }

  public void setDM_Formulario_Duplicata_Internacional (String DM_Formulario_Duplicata_Internacional) {
    this.DM_Formulario_Duplicata_Internacional = DM_Formulario_Duplicata_Internacional;
  }

  public int getOID_Tipo_Faturamento_Consolidacao_MIC_CRT () {
    return OID_Tipo_Faturamento_Consolidacao_MIC_CRT;
  }

  public void setOID_Tipo_Faturamento_Consolidacao_MIC_CRT (int tipo_Faturamento_Consolidacao_MIC_CRT) {
    OID_Tipo_Faturamento_Consolidacao_MIC_CRT = tipo_Faturamento_Consolidacao_MIC_CRT;
  }

  public String getDM_Tipo_Impressao_Fatura () {
    return DM_Tipo_Impressao_Fatura;
  }

  public void setDM_Tipo_Impressao_Fatura (String tipo_Impressao_Fatura) {
    DM_Tipo_Impressao_Fatura = tipo_Impressao_Fatura;
  }

  public int getOID_Historico_Bordero () {
    return OID_Historico_Bordero;
  }

  public void setOID_Historico_Bordero (int historico_Bordero) {
    OID_Historico_Bordero = historico_Bordero;
  }

  public String getDm_Impressao_Tramo () {
    return dm_Impressao_Tramo;
  }

  public void setDm_Impressao_Tramo (String dm_Impressao_Tramo) {
    this.dm_Impressao_Tramo = dm_Impressao_Tramo;
  }

  public long getOid_Tipo_Faturamento_Exportador () {
    return oid_Tipo_Faturamento_Exportador;
  }

  public void setOid_Tipo_Faturamento_Exportador (long oid_Tipo_Faturamento_Exportador) {
    this.oid_Tipo_Faturamento_Exportador = oid_Tipo_Faturamento_Exportador;
  }

  public long getOid_Tipo_Faturamento_Importador () {
    return oid_Tipo_Faturamento_Importador;
  }

  public void setOid_Tipo_Faturamento_Importador (long oid_Tipo_Faturamento_Importador) {
    this.oid_Tipo_Faturamento_Importador = oid_Tipo_Faturamento_Importador;
  }

  public boolean isCompleta_TX_Via () {
    return completa_TX_Via;
  }

  public void setCompleta_TX_Via (boolean completa_TX_Via) {
    this.completa_TX_Via = completa_TX_Via;
  }

  public String getTX_Via_Primeiro_Original_Es () {
    return TX_Via_Primeiro_Original_Es;
  }

  public void setTX_Via_Primeiro_Original_Es (String via_Primeiro_Original_Es) {
    TX_Via_Primeiro_Original_Es = via_Primeiro_Original_Es;
  }

  public String getTX_Via_Primeiro_Original_Pt () {
    return TX_Via_Primeiro_Original_Pt;
  }

  public void setTX_Via_Primeiro_Original_Pt (String via_Primeiro_Original_Pt) {
    TX_Via_Primeiro_Original_Pt = via_Primeiro_Original_Pt;
  }

  public String getTX_Via_Segundo_Original_Es () {
    return TX_Via_Segundo_Original_Es;
  }

  public void setTX_Via_Segundo_Original_Es (String via_Segundo_Original_Es) {
    TX_Via_Segundo_Original_Es = via_Segundo_Original_Es;
  }

  public String getTX_Via_Segundo_Original_Pt () {
    return TX_Via_Segundo_Original_Pt;
  }

  public void setTX_Via_Segundo_Original_Pt (String via_Segundo_Original_Pt) {
    TX_Via_Segundo_Original_Pt = via_Segundo_Original_Pt;
  }

  public String getTX_Via_Terceiro_Original_Es () {
    return TX_Via_Terceiro_Original_Es;
  }

  public void setTX_Via_Terceiro_Original_Es (String via_Terceiro_Original_Es) {
    TX_Via_Terceiro_Original_Es = via_Terceiro_Original_Es;
  }

  public String getTX_Via_Terceiro_Original_Pt () {
    return TX_Via_Terceiro_Original_Pt;
  }

  public void setTX_Via_Terceiro_Original_Pt (String via_Terceiro_Original_Pt) {
    TX_Via_Terceiro_Original_Pt = via_Terceiro_Original_Pt;
  }

  public String getVL_Reembolso () {
    return VL_Reembolso;
  }

  public void setVL_Reembolso (String reembolso) {
    VL_Reembolso = reembolso;
  }

  public String getNM_Sucessivos () {
    return NM_Sucessivos;
  }

  public void setNM_Sucessivos (String sucessivos) {
    NM_Sucessivos = sucessivos;
  }

  public String getDocument_Form_Action () {
    return document_Form_Action;
  }

  public void setDocument_Form_Action (String document_Form_Action) {
    this.document_Form_Action = document_Form_Action;
  }

  public double getPE_Aliquota_CSLL () {
    return PE_Aliquota_CSLL;
  }

  public void setPE_Aliquota_CSLL (double aliquota_CSLL) {
    PE_Aliquota_CSLL = aliquota_CSLL;
  }

  public long getOID_Tipo_Documento_Faturamento () {
    return OID_Tipo_Documento_Faturamento;
  }

  public void setOID_Tipo_Documento_Faturamento (long OID_Tipo_Documento_Faturamento) {
    this.OID_Tipo_Documento_Faturamento = OID_Tipo_Documento_Faturamento;
  }

  public long getOID_Tipo_Documento_Faturamento_Internacional () {
    return OID_Tipo_Documento_Faturamento_Internacional;
  }

  public void setOID_Tipo_Documento_Faturamento_Internacional (long OID_Tipo_Documento_Faturamento_Internacional) {
    this.OID_Tipo_Documento_Faturamento_Internacional = OID_Tipo_Documento_Faturamento_Internacional;
  }

  public void setOID_Carteira_Faturamento (long OID_Carteira_Faturamento) {
    this.OID_Carteira_Faturamento = OID_Carteira_Faturamento;
  }

  public long getOID_Carteira_Faturamento () {
    return OID_Carteira_Faturamento;
  }

  public void setOID_Unidade_Faturamento (long OID_Unidade_Faturamento) {
    this.OID_Unidade_Faturamento = OID_Unidade_Faturamento;
  }

  public long getOID_Unidade_Faturamento () {
    return OID_Unidade_Faturamento;
  }

  public void setOID_Centro_Custo_Ordem_Frete (int OID_Centro_Custo_Ordem_Frete) {
    this.OID_Centro_Custo_Ordem_Frete = OID_Centro_Custo_Ordem_Frete;
  }

  public int getOID_Centro_Custo_Ordem_Frete () {
    return OID_Centro_Custo_Ordem_Frete;
  }

  public void setOID_Conta_Ordem_Frete (int OID_Conta_Ordem_Frete) {
    this.OID_Conta_Ordem_Frete = OID_Conta_Ordem_Frete;
  }

  public int getOID_Conta_Ordem_Frete () {
    return OID_Conta_Ordem_Frete;
  }

  public void setOID_Tipo_Documento_Ordem_Frete (int OID_Tipo_Documento_Ordem_Frete) {
    this.OID_Tipo_Documento_Ordem_Frete = OID_Tipo_Documento_Ordem_Frete;
  }

  public int getOID_Tipo_Documento_Ordem_Frete () {
    return OID_Tipo_Documento_Ordem_Frete;
  }

  public void setOID_Centro_Custo_Movimento_Ordem_Servico (int OID_Centro_Custo_Movimento_Ordem_Servico) {
    this.OID_Centro_Custo_Movimento_Ordem_Servico = OID_Centro_Custo_Movimento_Ordem_Servico;
  }

  public int getOID_Centro_Custo_Movimento_Ordem_Servico () {
    return OID_Centro_Custo_Movimento_Ordem_Servico;
  }

  public void setOID_Conta_Movimento_Ordem_Servico (int OID_Conta_Movimento_Ordem_Servico) {
    this.OID_Conta_Movimento_Ordem_Servico = OID_Conta_Movimento_Ordem_Servico;
  }

  public int getOID_Conta_Movimento_Ordem_Servico () {
    return OID_Conta_Movimento_Ordem_Servico;
  }

  public void setDT_Hoje (String DT_Hoje) {
    this.DT_Hoje = DT_Hoje;
  }

  public String getDT_Hoje () {
    return DT_Hoje;
  }

  public void setHR_Hoje (String HR_Hoje) {
    this.HR_Hoje = HR_Hoje;
  }

  public String getHR_Hoje () {
    return HR_Hoje;
  }

  public void setVL_Faixa1 (double VL_Faixa1) {
    this.VL_Faixa1 = VL_Faixa1;
  }

  public double getVL_Faixa1 () {
    return VL_Faixa1;
  }

  public void setVL_Faixa2 (double VL_Faixa2) {
    this.VL_Faixa2 = VL_Faixa2;
  }

  public double getVL_Faixa2 () {
    return VL_Faixa2;
  }

  public void setVL_Deducao_Faixa1 (double VL_Deducao_Faixa1) {
    this.VL_Deducao_Faixa1 = VL_Deducao_Faixa1;
  }

  public double getVL_Deducao_Faixa1 () {
    return VL_Deducao_Faixa1;
  }

  public void setVL_Deducao_Faixa2 (double VL_Deducao_Faixa2) {
    this.VL_Deducao_Faixa2 = VL_Deducao_Faixa2;
  }

  public double getVL_Deducao_Faixa2 () {
    return VL_Deducao_Faixa2;
  }

  public void setPE_Imposto_Faixa1 (double PE_Imposto_Faixa1) {
    this.PE_Imposto_Faixa1 = PE_Imposto_Faixa1;
  }

  public double getPE_Imposto_Faixa1 () {
    return PE_Imposto_Faixa1;
  }

  public void setPE_Imposto_Faixa2 (double PE_Imposto_Faixa2) {
    this.PE_Imposto_Faixa2 = PE_Imposto_Faixa2;
  }

  public double getPE_Imposto_Faixa2 () {
    return PE_Imposto_Faixa2;
  }

  public void setPE_Base_Calculo (double PE_Base_Calculo) {
    this.PE_Base_Calculo = PE_Base_Calculo;
  }

  public double getPE_Base_Calculo () {
    return PE_Base_Calculo;
  }

  public void setVL_Dependente (double VL_Dependente) {
    this.VL_Dependente = VL_Dependente;
  }

  public double getVL_Dependente () {
    return VL_Dependente;
  }

  public void setOID_Natureza_Operacao_Ordem_Frete (int OID_Natureza_Operacao_Ordem_Frete) {
    this.OID_Natureza_Operacao_Ordem_Frete = OID_Natureza_Operacao_Ordem_Frete;
  }

  public int getOID_Natureza_Operacao_Ordem_Frete () {
    return OID_Natureza_Operacao_Ordem_Frete;
  }

  public void setOID_Tipo_Ocorrencia_Estorno_CTRC (int OID_Tipo_Ocorrencia_Estorno_CTRC) {
    this.OID_Tipo_Ocorrencia_Estorno_CTRC = OID_Tipo_Ocorrencia_Estorno_CTRC;
  }

  public int getOID_Tipo_Ocorrencia_Estorno_CTRC () {
    return OID_Tipo_Ocorrencia_Estorno_CTRC;
  }

  public void setOID_Conta_Credito_Ordem_Frete (int OID_Conta_Credito_Ordem_Frete) {
    this.OID_Conta_Credito_Ordem_Frete = OID_Conta_Credito_Ordem_Frete;
  }

  public int getOID_Conta_Credito_Ordem_Frete () {
    return OID_Conta_Credito_Ordem_Frete;
  }

  public void setOID_Conta_Credito_Movimento_Ordem_Servico (int OID_Conta_Credito_Movimento_Ordem_Servico) {
    this.OID_Conta_Credito_Movimento_Ordem_Servico = OID_Conta_Credito_Movimento_Ordem_Servico;
  }

  public int getOID_Conta_Credito_Movimento_Ordem_Servico () {
    return OID_Conta_Credito_Movimento_Ordem_Servico;
  }

  public void setOID_Conta_Juridica_Ordem_Frete (int OID_Conta_Juridica_Ordem_Frete) {
    this.OID_Conta_Juridica_Ordem_Frete = OID_Conta_Juridica_Ordem_Frete;
  }

  public int getOID_Conta_Juridica_Ordem_Frete () {
    return OID_Conta_Juridica_Ordem_Frete;
  }

  public void setVL_Maximo_INSS (double VL_Maximo_INSS) {
    this.VL_Maximo_INSS = VL_Maximo_INSS;
  }

  public double getVL_Maximo_INSS () {
    return VL_Maximo_INSS;
  }

  public void setPE_Aliquota_Empresa_INSS (double PE_Aliquota_Empresa_INSS) {
    this.PE_Aliquota_Empresa_INSS = PE_Aliquota_Empresa_INSS;
  }

  public double getPE_Aliquota_Empresa_INSS () {
    return PE_Aliquota_Empresa_INSS;
  }

  public void setPE_Aliquota_Prestador_INSS (double PE_Aliquota_Prestador_INSS) {
    this.PE_Aliquota_Prestador_INSS = PE_Aliquota_Prestador_INSS;
  }

  public double getPE_Aliquota_Prestador_INSS () {
    return PE_Aliquota_Prestador_INSS;
  }

  public void setPE_Base_INSS (double PE_Base_INSS) {
    this.PE_Base_INSS = PE_Base_INSS;
  }

  public double getPE_Base_INSS () {
    return PE_Base_INSS;
  }

  public void setPE_Base_SET_SENAT (double PE_Base_SET_SENAT) {
    this.PE_Base_SET_SENAT = PE_Base_SET_SENAT;
  }

  public double getPE_Base_SET_SENAT () {
    return PE_Base_SET_SENAT;
  }

  public void setPE_Aliquota_Prestador_Set_Senat (double PE_Aliquota_Prestador_Set_Senat) {
    this.PE_Aliquota_Prestador_Set_Senat = PE_Aliquota_Prestador_Set_Senat;
  }

  public double getPE_Aliquota_Prestador_Set_Senat () {
    return PE_Aliquota_Prestador_Set_Senat;
  }

  public void setDM_Calcula_INSS (String DM_Calcula_INSS) {
    this.DM_Calcula_INSS = DM_Calcula_INSS;
  }

  public String getDM_Calcula_INSS () {
    return DM_Calcula_INSS;
  }

  public int getOID_Conta_Credito_Pedagio () {
    return OID_Conta_Credito_Pedagio;
  }

  public int getOID_Conta_Debito_Pedagio () {
    return OID_Conta_Debito_Pedagio;
  }

  public void setOID_Conta_Credito_Pedagio (int OID_Conta_Credito_Pedagio) {
    this.OID_Conta_Credito_Pedagio = OID_Conta_Credito_Pedagio;
  }

  public void setOID_Conta_Debito_Pedagio (int OID_Conta_Debito_Pedagio) {
    this.OID_Conta_Debito_Pedagio = OID_Conta_Debito_Pedagio;
  }

  public void setOID_Conta_Credito_Master (int OID_Conta_Credito_Master) {
    this.OID_Conta_Credito_Master = OID_Conta_Credito_Master;
  }

  public int getOID_Conta_Credito_Master () {
    return OID_Conta_Credito_Master;
  }

  public void setOID_Conta_Debito_Master (int OID_Conta_Debito_Master) {
    this.OID_Conta_Debito_Master = OID_Conta_Debito_Master;
  }

  public int getOID_Conta_Debito_Master () {
    return OID_Conta_Debito_Master;
  }

  public void setOID_Tipo_Documento_Master (int OID_Tipo_Documento_Master) {
    this.OID_Tipo_Documento_Master = OID_Tipo_Documento_Master;
  }

  public int getOID_Tipo_Documento_Master () {
    return OID_Tipo_Documento_Master;
  }

  public void setOID_Natureza_Operacao_Master (int OID_Natureza_Operacao_Master) {
    this.OID_Natureza_Operacao_Master = OID_Natureza_Operacao_Master;
  }

  public int getOID_Natureza_Operacao_Master () {
    return OID_Natureza_Operacao_Master;
  }

  public void setOID_Centro_Custo_Master (int OID_Centro_Custo_Master) {
    this.OID_Centro_Custo_Master = OID_Centro_Custo_Master;
  }

  public int getOID_Centro_Custo_Master () {
    return OID_Centro_Custo_Master;
  }

  public void setOID_Tipo_Movimento_Custo_Entrega (int OID_Tipo_Movimento_Custo_Entrega) {
    this.OID_Tipo_Movimento_Custo_Entrega = OID_Tipo_Movimento_Custo_Entrega;
  }

  public int getOID_Tipo_Movimento_Custo_Entrega () {
    return OID_Tipo_Movimento_Custo_Entrega;
  }

  public void setOID_Tipo_Movimento_Custo_Master (int OID_Tipo_Movimento_Custo_Master) {
    this.OID_Tipo_Movimento_Custo_Master = OID_Tipo_Movimento_Custo_Master;
  }

  public int getOID_Tipo_Movimento_Custo_Master () {
    return OID_Tipo_Movimento_Custo_Master;
  }

  public void setOID_Tipo_Movimento_Custo_Ordem_Frete (int OID_Tipo_Movimento_Custo_Ordem_Frete) {
    this.OID_Tipo_Movimento_Custo_Ordem_Frete = OID_Tipo_Movimento_Custo_Ordem_Frete;
  }

  public int getOID_Tipo_Movimento_Custo_Ordem_Frete () {
    return OID_Tipo_Movimento_Custo_Ordem_Frete;
  }

  public void setOID_Tipo_Movimento_Custo_Coleta (int OID_Tipo_Movimento_Custo_Coleta) {
    this.OID_Tipo_Movimento_Custo_Coleta = OID_Tipo_Movimento_Custo_Coleta;
  }

  public int getOID_Tipo_Movimento_Custo_Coleta () {
    return OID_Tipo_Movimento_Custo_Coleta;
  }

  public void setOID_Conta_Credito_Entrega (int OID_Conta_Credito_Entrega) {
    this.OID_Conta_Credito_Entrega = OID_Conta_Credito_Entrega;
  }

  public int getOID_Conta_Credito_Entrega () {
    return OID_Conta_Credito_Entrega;
  }

  public void setOID_Conta_Debito_Entrega (int OID_Conta_Debito_Entrega) {
    this.OID_Conta_Debito_Entrega = OID_Conta_Debito_Entrega;
  }

  public int getOID_Conta_Debito_Entrega () {
    return OID_Conta_Debito_Entrega;
  }

  public void setOID_Tipo_Documento_Entrega (int OID_Tipo_Documento_Entrega) {
    this.OID_Tipo_Documento_Entrega = OID_Tipo_Documento_Entrega;
  }

  public int getOID_Tipo_Documento_Entrega () {
    return OID_Tipo_Documento_Entrega;
  }

  public void setOID_Centro_Custo_Entrega (int OID_Centro_Custo_Entrega) {
    this.OID_Centro_Custo_Entrega = OID_Centro_Custo_Entrega;
  }

  public int getOID_Centro_Custo_Entrega () {
    return OID_Centro_Custo_Entrega;
  }

  public void setOID_Natureza_Operacao_Entrega (int OID_Natureza_Operacao_Entrega) {
    this.OID_Natureza_Operacao_Entrega = OID_Natureza_Operacao_Entrega;
  }

  public int getOID_Natureza_Operacao_Entrega () {
    return OID_Natureza_Operacao_Entrega;
  }

  public void setNM_Empresa (String NM_Empresa) {
    base_EmpresaED.setNM_Empresa (NM_Empresa);
  }

  public String getNM_Empresa () {
    return base_EmpresaED.getNM_Empresa ();
  }

  public String getDM_Despacho () {
    return DM_Despacho;
  }

  public void setDM_Despacho (String DM_Despacho) {
    this.DM_Despacho = DM_Despacho;
  }

  public void setNM_Base (String NM_Base) {
    base_EmpresaED.setNM_Base (NM_Base);
  }

  public String getNM_Base () {
    return base_EmpresaED.getNM_Base ();
  }

  public int getOID_Unidade_Padrao () {
    return OID_Unidade_Padrao;
  }

  public void setOID_Unidade_Padrao (int OID_Unidade_Padrao) {
    this.OID_Unidade_Padrao = OID_Unidade_Padrao;
  }

  public int getOID_Modal () {
    return OID_Modal;
  }

  public void setOID_Modal (int OID_Modal) {
    this.OID_Modal = OID_Modal;
  }

  public void setDM_Exige_Pagador_Cliente (String DM_Exige_Pagador_Cliente) {
    this.DM_Exige_Pagador_Cliente = DM_Exige_Pagador_Cliente;
  }

  public String getDM_Exige_Pagador_Cliente () {
    return DM_Exige_Pagador_Cliente;
  }

  public int getOID_Tipo_Documento_Ordem_Frete_Adiantamento () {
    return OID_Tipo_Documento_Ordem_Frete_Adiantamento;
  }

  public void setOID_Tipo_Documento_Ordem_Frete_Adiantamento (int OID_Tipo_Documento_Ordem_Frete_Adiantamento) {
    this.OID_Tipo_Documento_Ordem_Frete_Adiantamento = OID_Tipo_Documento_Ordem_Frete_Adiantamento;
  }

  public int getOID_Conta_Ordem_Frete_Adiantamento () {
    return OID_Conta_Ordem_Frete_Adiantamento;
  }

  public void setOID_Conta_Ordem_Frete_Adiantamento (int OID_Conta_Ordem_Frete_Adiantamento) {
    this.OID_Conta_Ordem_Frete_Adiantamento = OID_Conta_Ordem_Frete_Adiantamento;
  }

  public int getOID_Conta_Credito_Ordem_Frete_Adiantamento () {
    return OID_Conta_Credito_Ordem_Frete_Adiantamento;
  }

  public void setOID_Conta_Credito_Ordem_Frete_Adiantamento (int OID_Conta_Credito_Ordem_Frete_Adiantamento) {
    this.OID_Conta_Credito_Ordem_Frete_Adiantamento = OID_Conta_Credito_Ordem_Frete_Adiantamento;
  }

  public void setDM_Gera_Ocorrencia_Viagem (String DM_Gera_Ocorrencia_Viagem) {
    this.DM_Gera_Ocorrencia_Viagem = DM_Gera_Ocorrencia_Viagem;
  }

  public String getDM_Gera_Ocorrencia_Viagem () {
    return DM_Gera_Ocorrencia_Viagem;
  }

  public String getDM_Gera_Compromisso_Viagem () {
    return DM_Gera_Compromisso_Viagem;
  }

  public void setDM_Gera_Compromisso_Viagem (String DM_Gera_Compromisso_Viagem) {
    this.DM_Gera_Compromisso_Viagem = DM_Gera_Compromisso_Viagem;
  }

  public void setDM_Estado_Origem_Taxas (String DM_Estado_Origem_Taxas) {
    this.DM_Estado_Origem_Taxas = DM_Estado_Origem_Taxas;
  }

  public String getDM_Estado_Origem_Taxas () {
    return DM_Estado_Origem_Taxas;
  }

  public void setDM_Estado_Destino_Taxas (String DM_Estado_Destino_Taxas) {
    this.DM_Estado_Destino_Taxas = DM_Estado_Destino_Taxas;
  }

  public String getDM_Estado_Destino_Taxas () {
    return DM_Estado_Destino_Taxas;
  }

  public void setOID_Tipo_Instrucao_Remessa (int OID_Tipo_Instrucao_Remessa) {
    this.OID_Tipo_Instrucao_Remessa = OID_Tipo_Instrucao_Remessa;
  }

  public int getOID_Tipo_Instrucao_Remessa () {
    return OID_Tipo_Instrucao_Remessa;
  }

  public void setOID_Cliente_Complemento_Padrao (String OID_Cliente_Complemento_Padrao) {
    this.OID_Cliente_Complemento_Padrao = OID_Cliente_Complemento_Padrao;
  }

  public String getOID_Cliente_Complemento_Padrao () {
    return OID_Cliente_Complemento_Padrao;
  }

  public void setDM_Numera_Ordem_Frete (String DM_Numera_Ordem_Frete) {
    this.DM_Numera_Ordem_Frete = DM_Numera_Ordem_Frete;
  }

  public String getDM_Numera_Ordem_Frete () {
    return DM_Numera_Ordem_Frete;
  }

  public void setDM_Numera_Coleta (String DM_Numera_Coleta) {
    this.DM_Numera_Coleta = DM_Numera_Coleta;
  }

  public String getDM_Numera_Coleta () {
    return DM_Numera_Coleta;
  }

  public void setDM_Numera_Manifesto (String DM_Numera_Manifesto) {
    this.DM_Numera_Manifesto = DM_Numera_Manifesto;
  }

  public String getDM_Numera_Manifesto () {
    return DM_Numera_Manifesto;
  }

  public void setDM_Numera_Conhecimento (String DM_Numera_Conhecimento) {
    this.DM_Numera_Conhecimento = DM_Numera_Conhecimento;
  }

  public String getDM_Numera_Conhecimento () {
    return DM_Numera_Conhecimento;
  }

  public void setDM_Numera_Frete_Internacional (String DM_Numera_Frete_Internacional) {
    this.DM_Numera_Frete_Internacional = DM_Numera_Frete_Internacional;
  }

  public String getDM_Numera_Frete_Internacional () {
    return DM_Numera_Frete_Internacional;
  }

  public void setDM_Numera_Fatura (String DM_Numera_Fatura) {
    this.DM_Numera_Fatura = DM_Numera_Fatura;
  }

  public String getDM_Numera_Fatura () {
    return DM_Numera_Fatura;
  }

  public void setDM_Soma_Pedagio_Saldo_Frete (String DM_Soma_Pedagio_Saldo_Frete) {
    this.DM_Soma_Pedagio_Saldo_Frete = DM_Soma_Pedagio_Saldo_Frete;
  }

  public String getDM_Soma_Pedagio_Saldo_Frete () {
    return DM_Soma_Pedagio_Saldo_Frete;
  }

  public void setDM_Soma_Sest_Senat_Saldo_Frete (String DM_Soma_Sest_Senat_Saldo_Frete) {
    this.DM_Soma_Sest_Senat_Saldo_Frete = DM_Soma_Sest_Senat_Saldo_Frete;
  }

  public String getDM_Soma_Sest_Senat_Saldo_Frete () {
    return DM_Soma_Sest_Senat_Saldo_Frete;
  }

  public void setDM_Criterio_Comissao (String DM_Criterio_Comissao) {
    this.DM_Criterio_Comissao = DM_Criterio_Comissao;
  }

  public String getDM_Criterio_Comissao () {
    return DM_Criterio_Comissao;
  }

  public void setDM_Calcula_IRRF (String DM_Calcula_IRRF) {
    this.DM_Calcula_IRRF = DM_Calcula_IRRF;
  }

  public String getDM_Calcula_IRRF () {
    return DM_Calcula_IRRF;
  }

  public void setDM_Frete_Cortesia (String DM_Frete_Cortesia) {
    this.DM_Frete_Cortesia = DM_Frete_Cortesia;
  }

  public String getDM_Frete_Cortesia () {
    return DM_Frete_Cortesia;
  }

  public void setVL_Frete_Cortesia (double VL_Frete_Cortesia) {
    this.VL_Frete_Cortesia = VL_Frete_Cortesia;
  }

  public double getVL_Frete_Cortesia () {
    return VL_Frete_Cortesia;
  }

  public void setDM_Calcula_PIS_COFINS (String DM_Calcula_PIS_COFINS) {
    this.DM_Calcula_PIS_COFINS = DM_Calcula_PIS_COFINS;
  }

  public String getDM_Calcula_PIS_COFINS () {
    return DM_Calcula_PIS_COFINS;
  }

  public void setPE_Aliquota_COFINS (double PE_Aliquota_COFINS) {
    this.PE_Aliquota_COFINS = PE_Aliquota_COFINS;
  }

  public double getPE_Aliquota_COFINS () {
    return PE_Aliquota_COFINS;
  }

  public void setPE_Aliquota_PIS (double PE_Aliquota_PIS) {
    this.PE_Aliquota_PIS = PE_Aliquota_PIS;
  }

  public double getPE_Aliquota_PIS () {
    return PE_Aliquota_PIS;
  }

  public void setOID_Tipo_Instrucao_Pago_Total (int OID_Tipo_Instrucao_Pago_Total) {
    this.OID_Tipo_Instrucao_Pago_Total = OID_Tipo_Instrucao_Pago_Total;
  }

  public int getOID_Tipo_Instrucao_Pago_Total () {
    return OID_Tipo_Instrucao_Pago_Total;
  }

  public void setOID_Tipo_Instrucao_Pago_Cartorio (int OID_Tipo_Instrucao_Pago_Cartorio) {
    this.OID_Tipo_Instrucao_Pago_Cartorio = OID_Tipo_Instrucao_Pago_Cartorio;
  }

  public int getOID_Tipo_Instrucao_Pago_Cartorio () {
    return OID_Tipo_Instrucao_Pago_Cartorio;
  }

  public void setOID_Tipo_Instrucao_Pago_Parcial (int OID_Tipo_Instrucao_Pago_Parcial) {
    this.OID_Tipo_Instrucao_Pago_Parcial = OID_Tipo_Instrucao_Pago_Parcial;
  }

  public int getOID_Tipo_Instrucao_Pago_Parcial () {
    return OID_Tipo_Instrucao_Pago_Parcial;
  }

  public void setOID_Tipo_Instrucao_Tarifa (int OID_Tipo_Instrucao_Tarifa) {
    this.OID_Tipo_Instrucao_Tarifa = OID_Tipo_Instrucao_Tarifa;
  }

  public int getOID_Tipo_Instrucao_Tarifa () {
    return OID_Tipo_Instrucao_Tarifa;
  }

  public void setOID_Tipo_Instrucao_Juros (int OID_Tipo_Instrucao_Juros) {
    this.OID_Tipo_Instrucao_Juros = OID_Tipo_Instrucao_Juros;
  }

  public int getOID_Tipo_Instrucao_Juros () {
    return OID_Tipo_Instrucao_Juros;
  }

  public void setOID_Tipo_Instrucao_Desconto (int OID_Tipo_Instrucao_Desconto) {
    this.OID_Tipo_Instrucao_Desconto = OID_Tipo_Instrucao_Desconto;
  }

  public int getOID_Tipo_Instrucao_Desconto () {
    return OID_Tipo_Instrucao_Desconto;
  }

  public void setDM_Quebra_Faturamento_Tipo_Conhecimento (String DM_Quebra_Faturamento_Tipo_Conhecimento) {
    this.DM_Quebra_Faturamento_Tipo_Conhecimento = DM_Quebra_Faturamento_Tipo_Conhecimento;
  }

  public String getDM_Quebra_Faturamento_Tipo_Conhecimento () {
    return DM_Quebra_Faturamento_Tipo_Conhecimento;
  }

  public void setDM_Quebra_Faturamento_Unidade (String DM_Quebra_Faturamento_Unidade) {
    this.DM_Quebra_Faturamento_Unidade = DM_Quebra_Faturamento_Unidade;
  }

  public String getDM_Quebra_Faturamento_Unidade () {
    return DM_Quebra_Faturamento_Unidade;
  }

  public void setDM_Quebra_Faturamento_Tipo_Faturamento (String DM_Quebra_Faturamento_Tipo_Faturamento) {
    this.DM_Quebra_Faturamento_Tipo_Faturamento = DM_Quebra_Faturamento_Tipo_Faturamento;
  }

  public String getDM_Quebra_Faturamento_Tipo_Faturamento () {
    return DM_Quebra_Faturamento_Tipo_Faturamento;
  }

  public void setDM_Gera_Lancamento_Contabil (String DM_Gera_Lancamento_Contabil) {
    this.DM_Gera_Lancamento_Contabil = DM_Gera_Lancamento_Contabil;
  }

  public String getDM_Gera_Lancamento_Contabil () {
    return DM_Gera_Lancamento_Contabil;
  }

  public int getOID_Tipo_Instrucao_Juros_Reembolso () {
    return OID_Tipo_Instrucao_Juros_Reembolso;
  }

  public void setOID_Tipo_Instrucao_Juros_Reembolso (int OID_Tipo_Instrucao_Juros_Reembolso) {
    this.OID_Tipo_Instrucao_Juros_Reembolso = OID_Tipo_Instrucao_Juros_Reembolso;
  }

  public void setOID_Tipo_Instrucao_Valor_Reembolso (int OID_Tipo_Instrucao_Valor_Reembolso) {
    this.OID_Tipo_Instrucao_Valor_Reembolso = OID_Tipo_Instrucao_Valor_Reembolso;
  }

  public void setOID_Tipo_Instrucao_Variacao_Cambial_Ativa (int OID_Tipo_Instrucao_Variacao_Cambial_Ativa) {
    this.OID_Tipo_Instrucao_Variacao_Cambial_Ativa = OID_Tipo_Instrucao_Variacao_Cambial_Ativa;
  }

  public void setOID_Tipo_Instrucao_Variacao_Cambial_Passiva (int OID_Tipo_Instrucao_Variacao_Cambial_Passiva) {
    this.OID_Tipo_Instrucao_Variacao_Cambial_Passiva = OID_Tipo_Instrucao_Variacao_Cambial_Passiva;
  }

  public int getOID_Tipo_Instrucao_Valor_Reembolso () {
    return OID_Tipo_Instrucao_Valor_Reembolso;
  }

  public int getOID_Tipo_Instrucao_Variacao_Cambial_Ativa () {
    return OID_Tipo_Instrucao_Variacao_Cambial_Ativa;
  }

  public int getOID_Tipo_Instrucao_Variacao_Cambial_Passiva () {
    return OID_Tipo_Instrucao_Variacao_Cambial_Passiva;
  }

  public int getOID_Tipo_Movimento_Custo_Previsto_Entrega () {
    return OID_Tipo_Movimento_Custo_Previsto_Entrega;
  }

  public void setOID_Tipo_Movimento_Custo_Previsto_Entrega (int OID_Tipo_Movimento_Custo_Previsto_Entrega) {
    this.OID_Tipo_Movimento_Custo_Previsto_Entrega = OID_Tipo_Movimento_Custo_Previsto_Entrega;
  }

  public void setOID_Historico_Compensacao (int OID_Historico_Compensacao) {
    this.OID_Historico_Compensacao = OID_Historico_Compensacao;
  }

  public int getOID_Historico_Compensacao () {
    return OID_Historico_Compensacao;
  }

  public int getOID_Tipo_Ocorrencia_Desconto_CTRC () {
    return OID_Tipo_Ocorrencia_Desconto_CTRC;
  }

  public void setOID_Tipo_Ocorrencia_Desconto_CTRC (int OID_Tipo_Ocorrencia_Desconto_CTRC) {
    this.OID_Tipo_Ocorrencia_Desconto_CTRC = OID_Tipo_Ocorrencia_Desconto_CTRC;
  }

  public int getOID_Tipo_Movimento_Custo_Desconto () {
    return OID_Tipo_Movimento_Custo_Desconto;
  }

  public void setOID_Tipo_Movimento_Custo_Desconto (int OID_Tipo_Movimento_Custo_Desconto) {
    this.OID_Tipo_Movimento_Custo_Desconto = OID_Tipo_Movimento_Custo_Desconto;
  }

  public int getOID_Tipo_Movimento_Produto () {
    return OID_Tipo_Movimento_Produto;
  }

  public void setOID_Tipo_Movimento_Produto (int OID_Tipo_Movimento_Produto) {
    this.OID_Tipo_Movimento_Produto = OID_Tipo_Movimento_Produto;
  }

  public int getOID_Deposito () {
    return OID_Deposito;
  }

  public void setOID_Deposito (int OID_Deposito) {
    this.OID_Deposito = OID_Deposito;
  }

  public int getOID_Operador () {
    return OID_Operador;
  }

  public void setOID_Operador (int OID_Operador) {
    this.OID_Operador = OID_Operador;
  }

  public void setNM_Database_URL (String NM_Database_URL) {
    base_EmpresaED.setNM_Database_URL (NM_Database_URL);
  }

  public String getNM_Database_URL () {
    return base_EmpresaED.getNM_Database_URL ();
  }

  public String getNM_Database_Pwd () {
    return base_EmpresaED.getNM_Database_Pwd ();
  }

  public String getNM_Database_Usuario () {
    return base_EmpresaED.getNM_Database_Usuario ();
  }

  public void setNM_Database_Pwd (String NM_Database_Pwd) {
    base_EmpresaED.setNM_Database_Pwd (NM_Database_Pwd);
  }

  public void setNM_Database_Usuario (String NM_Database_Usuario) {
    base_EmpresaED.setNM_Database_Usuario (NM_Database_Usuario);
  }

  public void setNM_Databese_Sid (String NM_Databese_Sid) {
    base_EmpresaED.setNM_Databese_Sid (NM_Databese_Sid);
  }

  public String getNM_Databese_Sid () {
    return base_EmpresaED.getNM_Databese_Sid ();
  }

  public void setNM_Database_Host (String NM_Database_Host) {
    base_EmpresaED.setNM_Database_Host (NM_Database_Host);
  }

  public void setNM_Database_Port (int NM_Database_Port) {
    base_EmpresaED.setNM_Database_Port (NM_Database_Port);
  }

  public String getNM_Database_Host () {
    return base_EmpresaED.getNM_Database_Host ();
  }

  public int getNM_Database_Port () {
    return base_EmpresaED.getNM_Database_Port ();
  }

  public String getNM_Database_Driver () {
    return base_EmpresaED.getNM_Database_Driver ();
  }

  public void setNM_Database_Driver (String NM_Database_Driver) {
    base_EmpresaED.setNM_Database_Driver (NM_Database_Driver);
  }

  public String getNM_Database_UsuarioBase2 () {
    return base_EmpresaED.getNM_Database_UsuarioBase2 ();
  }

  public void setNM_Database_UsuarioBase2 (String NM_Database_UsuarioBase2) {
    base_EmpresaED.setNM_Database_UsuarioBase2 (NM_Database_UsuarioBase2);
  }

  public String getNM_Database_PwdBase2 () {
    return base_EmpresaED.getNM_Database_PwdBase2 ();
  }

  public void setNM_Database_PwdBase2 (String NM_Database_PwdBase2) {
    base_EmpresaED.setNM_Database_PwdBase2 (NM_Database_PwdBase2);
  }

  public void setNM_Database_URLBase2 (String NM_Database_URLBase2) {
    base_EmpresaED.setNM_Database_URLBase2 (NM_Database_URLBase2);
  }

  public String getNM_Database_URLBase2 () {
    return base_EmpresaED.getNM_Database_URLBase2 ();
  }

  public String getNM_Database_DriverBase2 () {
    return base_EmpresaED.getNM_Database_DriverBase2 ();
  }

  public void setNM_Database_DriverBase2 (String NM_Database_DriverBase2) {
    base_EmpresaED.setNM_Database_DriverBase2 (NM_Database_DriverBase2);
  }

  public void setNM_Nivel_Produto1 (String NM_Nivel_Produto1) {
    this.NM_Nivel_Produto1 = NM_Nivel_Produto1;
  }

  public String getNM_Nivel_Produto1 () {
    return NM_Nivel_Produto1;
  }

  public void setNM_Nivel_Produto2 (String NM_Nivel_Produto2) {
    this.NM_Nivel_Produto2 = NM_Nivel_Produto2;
  }

  public String getNM_Nivel_Produto2 () {
    return NM_Nivel_Produto2;
  }

  public void setNM_Nivel_Produto3 (String NM_Nivel_Produto3) {
    this.NM_Nivel_Produto3 = NM_Nivel_Produto3;
  }

  public String getNM_Nivel_Produto3 () {
    return NM_Nivel_Produto3;
  }

  public void setNM_Nivel_Produto4 (String NM_Nivel_Produto4) {
    this.NM_Nivel_Produto4 = NM_Nivel_Produto4;
  }

  public String getNM_Nivel_Produto4 () {
    return NM_Nivel_Produto4;
  }

  public void setNM_Nivel_Produto5 (String NM_Nivel_Produto5) {
    this.NM_Nivel_Produto5 = NM_Nivel_Produto5;
  }

  public String getNM_Nivel_Produto5 () {
    return NM_Nivel_Produto5;
  }

  public void setOID_Tipo_Pallet (int OID_Tipo_Pallet) {
    this.OID_Tipo_Pallet = OID_Tipo_Pallet;
  }

  public int getOID_Tipo_Pallet () {
    return OID_Tipo_Pallet;
  }

  public void setOID_Embalagem (int OID_Embalagem) {
    this.OID_Embalagem = OID_Embalagem;
  }

  public int getOID_Embalagem () {
    return OID_Embalagem;
  }

  public void setOID_Localizacao (int OID_Localizacao) {
    this.OID_Localizacao = OID_Localizacao;
  }

  public int getOID_Localizacao () {
    return OID_Localizacao;
  }

  public void setOID_Tipo_Estoque (int OID_Tipo_Estoque) {
    this.OID_Tipo_Estoque = OID_Tipo_Estoque;
  }

  public int getOID_Tipo_Estoque () {
    return OID_Tipo_Estoque;
  }

  public String getDM_Gera_Compromisso_Ordem_Frete () {
    return DM_Gera_Compromisso_Ordem_Frete;
  }

  public void setDM_Gera_Compromisso_Ordem_Frete (String DM_Gera_Compromisso_Ordem_Frete) {
    this.DM_Gera_Compromisso_Ordem_Frete = DM_Gera_Compromisso_Ordem_Frete;
  }

  public String getDM_Gera_Compromisso_Master () {
    return DM_Gera_Compromisso_Master;
  }

  public void setDM_Gera_Compromisso_Master (String DM_Gera_Compromisso_Master) {
    this.DM_Gera_Compromisso_Master = DM_Gera_Compromisso_Master;
  }

  public void setOID_Conta_Frete_Terceiros (int OID_Conta_Frete_Terceiros) {
    this.OID_Conta_Frete_Terceiros = OID_Conta_Frete_Terceiros;
  }

  public int getOID_Conta_Frete_Terceiros () {
    return OID_Conta_Frete_Terceiros;
  }

  public int getOID_Conta_Desconto_Frete () {
    return OID_Conta_Desconto_Frete;
  }

  public int getOID_Conta_Despesa_Bancaria () {
    return OID_Conta_Despesa_Bancaria;
  }

  public void setOID_Conta_Desconto_Frete (int OID_Conta_Desconto_Frete) {
    this.OID_Conta_Desconto_Frete = OID_Conta_Desconto_Frete;
  }

  public void setOID_Conta_Despesa_Bancaria (int OID_Conta_Despesa_Bancaria) {
    this.OID_Conta_Despesa_Bancaria = OID_Conta_Despesa_Bancaria;
  }

  public int getOID_Tipo_Instrucao_Estorno () {
    return OID_Tipo_Instrucao_Estorno;
  }

  public void setOID_Tipo_Instrucao_Estorno (int OID_Tipo_Instrucao_Estorno) {
    this.OID_Tipo_Instrucao_Estorno = OID_Tipo_Instrucao_Estorno;
  }

  public int getOID_Tipo_Ocorrencia_Cancelamento_CTRC () {
    return OID_Tipo_Ocorrencia_Cancelamento_CTRC;
  }

  public void setOID_Tipo_Ocorrencia_Cancelamento_CTRC (int OID_Tipo_Ocorrencia_Cancelamento_CTRC) {
    this.OID_Tipo_Ocorrencia_Cancelamento_CTRC = OID_Tipo_Ocorrencia_Cancelamento_CTRC;
  }

  public String getNM_Versao () {
    return NM_Versao;
  }

  public void setNM_Razao_Social_Empresa (String NM_Razao_Social_Empresa) {
    this.NM_Razao_Social_Empresa = NM_Razao_Social_Empresa;
  }

  public String getNM_Razao_Social_Empresa () {
    return NM_Razao_Social_Empresa;
  }

  public void setDM_Situacao (String DM_Situacao) {
    this.DM_Situacao = DM_Situacao;
  }

  public String getDM_Situacao () {
    return DM_Situacao;
  }

  public String getDM_Menu () {
    return "GMSUL".equals (getNM_Empresa ()) ? "../principal/gm/" : "../principal/" + getNM_Empresa ().toLowerCase () + "/";
  }

  public String getPATH_IMAGENS () {
    return PATH_IMAGENS;
  }

  public void setPATH_IMAGENS (String path_imagens) {
    PATH_IMAGENS = path_imagens;
  }

  public String getPATH_RELATORIOS () {
    return PATH_RELATORIOS;
  }

  public void setPATH_RELATORIOS (String path_relatorios) {
    PATH_RELATORIOS = path_relatorios;
  }

  public int getOid_Tipo_Documento_Nota_Fiscal_Entrada () {
    return oid_Tipo_Documento_Nota_Fiscal_Entrada;
  }

  public void setOid_Tipo_Documento_Nota_Fiscal_Entrada (int oid_Tipo_Documento_Nota_Fiscal_Entrada) {
    this.oid_Tipo_Documento_Nota_Fiscal_Entrada = oid_Tipo_Documento_Nota_Fiscal_Entrada;
  }

  public String getDM_Gera_Compromisso_Movimento_Ordem_Servico () {
    return DM_Gera_Compromisso_Movimento_Ordem_Servico;
  }

  public void setDM_Gera_Compromisso_Movimento_Ordem_Servico (String DM_Gera_Compromisso_Movimento_Ordem_Servico) {
    this.DM_Gera_Compromisso_Movimento_Ordem_Servico = DM_Gera_Compromisso_Movimento_Ordem_Servico;
  }

  public String getDM_Atualiza_Movimento_Caixa () {
    return DM_Atualiza_Movimento_Caixa;
  }

  public void setDM_Atualiza_Movimento_Caixa (String DM_Atualiza_Movimento_Caixa) {
    this.DM_Atualiza_Movimento_Caixa = DM_Atualiza_Movimento_Caixa;
  }

  public void setDM_Formulario_Duplicata (String DM_Formulario_Duplicata) {
    this.DM_Formulario_Duplicata = DM_Formulario_Duplicata;
  }

  public String getDM_Formulario_Duplicata () {
    return DM_Formulario_Duplicata;
  }

  public String getDM_Inclui_ICMS_Parcela_CTRC () {
    return DM_Inclui_ICMS_Parcela_CTRC;
  }

  public void setDM_Inclui_ICMS_Parcela_CTRC (String DM_Inclui_ICMS_Parcela_CTRC) {
    this.DM_Inclui_ICMS_Parcela_CTRC = DM_Inclui_ICMS_Parcela_CTRC;
  }

  public String getDM_Formulario_Coleta () {
    return DM_Formulario_Coleta;
  }

  public String getDM_Formulario_Conhecimento_Internacional () {
    return DM_Formulario_Conhecimento_Internacional;
  }

  public String getDM_Formulario_Conhecimento_Nacional () {
    return DM_Formulario_Conhecimento_Nacional;
  }

  public String getDM_Formulario_Manifesto () {
    return DM_Formulario_Manifesto;
  }

  public String getDM_Formulario_Nota_Fiscal () {
    return DM_Formulario_Nota_Fiscal;
  }

  public String getDM_Formulario_Ordem_Frete () {
    return DM_Formulario_Ordem_Frete;
  }

  public void setDM_Formulario_Ordem_Frete (String DM_Formulario_Ordem_Frete) {
    this.DM_Formulario_Ordem_Frete = DM_Formulario_Ordem_Frete;
  }

  public void setDM_Formulario_Nota_Fiscal (String DM_Formulario_Nota_Fiscal) {
    this.DM_Formulario_Nota_Fiscal = DM_Formulario_Nota_Fiscal;
  }

  public void setDM_Formulario_Manifesto (String DM_Formulario_Manifesto) {
    this.DM_Formulario_Manifesto = DM_Formulario_Manifesto;
  }

  public void setDM_Formulario_Conhecimento_Nacional (String DM_Formulario_Conhecimento_Nacional) {
    this.DM_Formulario_Conhecimento_Nacional = DM_Formulario_Conhecimento_Nacional;
  }

  public void setDM_Formulario_Conhecimento_Internacional (String DM_Formulario_Conhecimento_Internacional) {
    this.DM_Formulario_Conhecimento_Internacional = DM_Formulario_Conhecimento_Internacional;
  }

  public void setDM_Formulario_Coleta (String DM_Formulario_Coleta) {
    this.DM_Formulario_Coleta = DM_Formulario_Coleta;
  }

  public int getOid_Tipo_Documento_Nota_Fiscal_Saida () {
    return oid_Tipo_Documento_Nota_Fiscal_Saida;
  }

  public void setOid_Tipo_Documento_Nota_Fiscal_Saida (int oid_Tipo_Documento_Nota_Fiscal_Saida) {
    this.oid_Tipo_Documento_Nota_Fiscal_Saida = oid_Tipo_Documento_Nota_Fiscal_Saida;
  }

  public int getOid_Modelo_Nota_Fiscal () {
    return oid_Modelo_Nota_Fiscal;
  }

  public void setOid_Modelo_Nota_Fiscal (int oid_Modelo_Nota_Fiscal) {
    this.oid_Modelo_Nota_Fiscal = oid_Modelo_Nota_Fiscal;
  }

  public void setDM_Tipo_Sistema (String DM_Tipo_Sistema) {
    this.DM_Tipo_Sistema = DM_Tipo_Sistema;
  }

  public String getDM_Tipo_Sistema () {
    return DM_Tipo_Sistema;
  }

  public void setDM_Gera_Compromisso_Ordem_Frete_Adiantamento (String DM_Gera_Compromisso_Ordem_Frete_Adiantamento) {
    this.DM_Gera_Compromisso_Ordem_Frete_Adiantamento = DM_Gera_Compromisso_Ordem_Frete_Adiantamento;
  }

  public String getDM_Gera_Compromisso_Ordem_Frete_Adiantamento () {
    return DM_Gera_Compromisso_Ordem_Frete_Adiantamento;
  }

  public String getDM_Formulario_Ordem_Frete_Adiantamento () {
    return DM_Formulario_Ordem_Frete_Adiantamento;
  }

  public void setDM_Formulario_Ordem_Frete_Adiantamento (String DM_Formulario_Ordem_Frete_Adiantamento) {
    this.DM_Formulario_Ordem_Frete_Adiantamento = DM_Formulario_Ordem_Frete_Adiantamento;
  }

  public boolean isNF_Multi_Conhecimento () {
    return NF_Multi_Conhecimento;
  }

  public void setNF_Multi_Conhecimento (boolean NF_Multi_Conhecimento) {
    this.NF_Multi_Conhecimento = NF_Multi_Conhecimento;
  }

  public void setNR_Vias_Ordem_Frete_Adiantamento (long NR_Vias_Ordem_Frete_Adiantamento) {
    this.NR_Vias_Ordem_Frete_Adiantamento = NR_Vias_Ordem_Frete_Adiantamento;
  }

  public long getNR_Vias_Ordem_Frete_Adiantamento () {
    return NR_Vias_Ordem_Frete_Adiantamento;
  }

  /**
   * URL que o usu�rio usou para acessar o sistema (ex.:
   * http://localhost:8180/sistema/)
   */
  public String getUrl () {
    return "http://" + getServerName () + ":" + getPort () + getContexto () + "/";
  }

  /**
   * URL local para acessar o sistema (ex.: http://localhost:8180/sistema/)
   */
  public String getUrlLocal () {
    return "http://127.0.0.1:" + getPort () + getContexto () + "/";
  }

  public String getContexto () {
    return this.contexto;
  }

  public void setContexto (String contexto) {
    this.contexto = contexto;
  }

  public String getServerName () {
    return this.serverName;
  }

  public void setServerName (String serverName) {
    this.serverName = serverName;
  }

  public int getPort () {
    return this.port;
  }

  public int getOID_Tipo_Instrucao_Imposto_Retido2 () {
    return OID_Tipo_Instrucao_Imposto_Retido2;
  }

  public int getOID_Tipo_Instrucao_Imposto_Retido1 () {
    return OID_Tipo_Instrucao_Imposto_Retido1;
  }

  public String getDM_Operacao_Sistema () {
    return DM_Operacao_Sistema;
  }

  public String getDM_Perfil_Sistema () {
    return DM_Perfil_Sistema;
  }

  public String getDM_Formulario_Ordem_Servico () {
    return DM_Formulario_Ordem_Servico;
  }

  public void setPort (int port) {
    this.port = port;
  }

  public void setOID_Tipo_Instrucao_Imposto_Retido2 (int OID_Tipo_Instrucao_Imposto_Retido2) {
    this.OID_Tipo_Instrucao_Imposto_Retido2 = OID_Tipo_Instrucao_Imposto_Retido2;
  }

  public void setOID_Tipo_Instrucao_Imposto_Retido1 (int OID_Tipo_Instrucao_Imposto_Retido1) {
    this.OID_Tipo_Instrucao_Imposto_Retido1 = OID_Tipo_Instrucao_Imposto_Retido1;
  }

  public void setDM_Operacao_Sistema (String DM_Operacao_Sistema) {
    this.DM_Operacao_Sistema = DM_Operacao_Sistema;
  }

  public void setDM_Perfil_Sistema (String DM_Perfil_Sistema) {
    this.DM_Perfil_Sistema = DM_Perfil_Sistema;
  }

  public void setDM_Formulario_Ordem_Servico (String DM_Formulario_Ordem_Servico) {
    this.DM_Formulario_Ordem_Servico = DM_Formulario_Ordem_Servico;
  }

  public void setEDI_Seguradora (String EDI_Seguradora) {
    this.EDI_Seguradora = EDI_Seguradora;
  }

  public void setEDI_Duplicata (String EDI_Duplicata) {
    this.EDI_Duplicata = EDI_Duplicata;
  }

  public void setEDI_Fiscal (String EDI_Fiscal) {
    this.EDI_Fiscal = EDI_Fiscal;
  }

  public void setEDI_Contabil (String EDI_Contabil) {
    this.EDI_Contabil = EDI_Contabil;
  }

  public void setEDI_Cliente (String EDI_Cliente) {
    this.EDI_Cliente = EDI_Cliente;
  }

  public void setEDI_Banco (String EDI_Banco) {
    this.EDI_Banco = EDI_Banco;
  }

  public void setDM_Liquida_Compromisso (String DM_Liquida_Compromisso) {

    this.DM_Liquida_Compromisso = DM_Liquida_Compromisso;
  }

  public void setNM_Modelo_Tabela_Rodoviario (String NM_Modelo_Tabela_Rodoviario) {
    this.NM_Modelo_Tabela_Rodoviario = NM_Modelo_Tabela_Rodoviario;
  }

  public void setNM_Modelo_Tabela_Documento (String NM_Modelo_Tabela_Documento) {
    this.NM_Modelo_Tabela_Documento = NM_Modelo_Tabela_Documento;
  }

  public void setNM_Modelo_Tabela_Aereo (String NM_Modelo_Tabela_Aereo) {
    this.NM_Modelo_Tabela_Aereo = NM_Modelo_Tabela_Aereo;
  }

  public void setOID_Tipo_Documento_Transferencia_Conta_Corrente (int OID_Tipo_Documento_Transferencia_Conta_Corrente) {
    this.OID_Tipo_Documento_Transferencia_Conta_Corrente = OID_Tipo_Documento_Transferencia_Conta_Corrente;
  }

  public void setOID_Historico_Cancelamento_Lote_Pagamento (int OID_Historico_Cancelamento_Lote_Pagamento) {
    this.OID_Historico_Cancelamento_Lote_Pagamento = OID_Historico_Cancelamento_Lote_Pagamento;
  }

  public void setOID_Tipo_Documento_Faturamento_Nota_Fiscal_Servico (long OID_Tipo_Documento_Faturamento_Nota_Fiscal_Servico) {
    this.OID_Tipo_Documento_Faturamento_Nota_Fiscal_Servico = OID_Tipo_Documento_Faturamento_Nota_Fiscal_Servico;
  }

  public void setDM_Formulario_Minuta (String DM_Formulario_Minuta) {
    this.DM_Formulario_Minuta = DM_Formulario_Minuta;
  }

  public void setOID_Historico_Lancamento_Ordem_Frete_Adiantamento (int OID_Historico_Lancamento_Ordem_Frete_Adiantamento) {
    this.OID_Historico_Lancamento_Ordem_Frete_Adiantamento = OID_Historico_Lancamento_Ordem_Frete_Adiantamento;
  }

  public void setOID_Historico_Lancamento_Ordem_Frete (int OID_Historico_Lancamento_Ordem_Frete) {
    this.OID_Historico_Lancamento_Ordem_Frete = OID_Historico_Lancamento_Ordem_Frete;
  }

  public void setDM_Gera_Lancamento_Conta_Corrente_Ordem_Frete_Adiantamento (String DM_Gera_Lancamento_Conta_Corrente_Ordem_Frete_Adiantamento) {
    this.DM_Gera_Lancamento_Conta_Corrente_Ordem_Frete_Adiantamento = DM_Gera_Lancamento_Conta_Corrente_Ordem_Frete_Adiantamento;
  }

  public void setDM_Gera_Lancamento_Conta_Corrente_Ordem_Frete (String DM_Gera_Lancamento_Conta_Corrente_Ordem_Frete) {
    this.DM_Gera_Lancamento_Conta_Corrente_Ordem_Frete = DM_Gera_Lancamento_Conta_Corrente_Ordem_Frete;
  }

  public void setOID_Tipo_Instrucao_Devolucao_Nota_Fiscal (int OID_Tipo_Instrucao_Devolucao_Nota_Fiscal) {
    this.OID_Tipo_Instrucao_Devolucao_Nota_Fiscal = OID_Tipo_Instrucao_Devolucao_Nota_Fiscal;
    this.OID_Tipo_Instrucao_Devolucao_Nota_Fiscal = OID_Tipo_Instrucao_Devolucao_Nota_Fiscal;
  }

  public void setDM_Gera_Lancamento_Conta_Corrente_Ordem_Frete_Terceiro (String DM_Gera_Lancamento_Conta_Corrente_Ordem_Frete_Terceiro) {
    this.DM_Gera_Lancamento_Conta_Corrente_Ordem_Frete_Terceiro = DM_Gera_Lancamento_Conta_Corrente_Ordem_Frete_Terceiro;
  }

  public void setOID_Historico_Lancamento_Ordem_Frete_Terceiro (int OID_Historico_Lancamento_Ordem_Frete_Terceiro) {
    this.OID_Historico_Lancamento_Ordem_Frete_Terceiro = OID_Historico_Lancamento_Ordem_Frete_Terceiro;
  }

  public void setOID_Tipo_Servico_Acerto_Contas (int OID_Tipo_Servico_Acerto_Contas) {
    this.OID_Tipo_Servico_Acerto_Contas = OID_Tipo_Servico_Acerto_Contas;
  }

  public int getOID_Tipo_Servico_Acerto_Contas () {
    return OID_Tipo_Servico_Acerto_Contas;
  }

  public String getDM_Formulario_Acerto_Contas () {
    return DM_Formulario_Acerto_Contas;
  }

  public void setDM_Formulario_Acerto_Contas (String DM_Formulario_Acerto_Contas) {
    this.DM_Formulario_Acerto_Contas = DM_Formulario_Acerto_Contas;
  }

  public String getDM_Formulario_Conhecimento_Internacional_Verso () {
    return DM_Formulario_Conhecimento_Internacional_Verso;
  }

  public void setDM_Formulario_Conhecimento_Internacional_Verso (String formulario_Conhecimento_Internacional_Verso) {
    DM_Formulario_Conhecimento_Internacional_Verso = formulario_Conhecimento_Internacional_Verso;
  }

  public String getDM_Gera_Compromisso_Motorista_Proprietario () {
    return DM_Gera_Compromisso_Motorista_Proprietario;
  }

  public void setDM_Gera_Compromisso_Motorista_Proprietario (
      String gera_Compromisso_Motorista_Proprietario) {
    DM_Gera_Compromisso_Motorista_Proprietario = gera_Compromisso_Motorista_Proprietario;
  }


  public String getDM_Tranca_Saldo_Ordem_Frete () {
    return DM_Tranca_Saldo_Ordem_Frete;
  }

  public void setDM_Tranca_Saldo_Ordem_Frete (String tranca_Saldo_Ordem_Frete) {
    DM_Tranca_Saldo_Ordem_Frete = tranca_Saldo_Ordem_Frete;
  }

  public long getOid_Tipo_Pedido () {
    return oid_Tipo_Pedido;
  }

  public String getDM_Vincula_Adto_Ordem_Principal () {
    return DM_Vincula_Adto_Ordem_Principal;
  }

  public void setOid_Tipo_Pedido (long oid_Tipo_Pedido) {
    this.oid_Tipo_Pedido = oid_Tipo_Pedido;
  }

  public void setDM_Vincula_Adto_Ordem_Principal (String DM_Vincula_Adto_Ordem_Principal) {
    this.DM_Vincula_Adto_Ordem_Principal = DM_Vincula_Adto_Ordem_Principal;
  }

  public long getOID_CFOP_Diferenciado () {
    return OID_CFOP_Diferenciado;
  }

  public void setOID_CFOP_Diferenciado (long diferenciado) {
    OID_CFOP_Diferenciado = diferenciado;
  }

  public long getOID_CFOP_Pessoa_Fisica () {
    return OID_CFOP_Pessoa_Fisica;
  }

  public void setOID_CFOP_Pessoa_Fisica (long pessoa_Fisica) {
    OID_CFOP_Pessoa_Fisica = pessoa_Fisica;
  }

  public int getOID_Tipo_Faturamento_Real () {
    return OID_Tipo_Faturamento_Real;
  }

  public void setOID_Tipo_Faturamento_Real (int tipo_Faturamento_Real) {
    OID_Tipo_Faturamento_Real = tipo_Faturamento_Real;
  }

  public String getPalmModelo () {
    return palmModelo;
  }

  public void setPalmModelo (String palmModelo) {
    this.palmModelo = palmModelo;
  }

  public int getVias_Fatura () {
    return vias_Fatura;
  }

  public String getGetEDI_Importa_Nota_FiscalPath () {
    return getEDI_Importa_Nota_FiscalPath;
  }

  public String getDM_Gera_Custo_Viagem () {
    return DM_Gera_Custo_Viagem;
  }

  public String getDM_Verifica_CNPJ_CPF_Pessoa () {
    return DM_Verifica_CNPJ_CPF_Pessoa;
  }

  public String getDM_Envia_Email_Eventos () {
    return DM_Envia_Email_Eventos;
  }

  public String getDM_Formulario_Romaneio_Notal_Fiscal () {

    return DM_Formulario_Romaneio_Notal_Fiscal;
  }

  public int getOID_Tipo_Movimento_Ajuste_S () {
    return OID_Tipo_Movimento_Ajuste_S;
  }

  public int getOID_Tipo_Movimento_Ajuste_E () {
    return OID_Tipo_Movimento_Ajuste_E;
  }

  public int getOID_Tipo_Movimento_Custo_Devolucao () {
    return OID_Tipo_Movimento_Custo_Devolucao;
  }

  public int getOID_Modal_Aereo_Sedex () {
    return OID_Modal_Aereo_Sedex;
  }

  public int getOID_Modal_Aereo_RodExp () {
    return OID_Modal_Aereo_RodExp;
  }

  public int getOID_Modal_Aereo_Pac () {
    return OID_Modal_Aereo_Pac;
  }

  public int getOID_Modal_Aereo_Expressa () {
    return OID_Modal_Aereo_Expressa;
  }

  public int getOID_Modal_Aereo_Emergencial () {
    return OID_Modal_Aereo_Emergencial;
  }

  public int getOID_Tipo_Ocorrencia_Reentrega_CTRC () {
    return OID_Tipo_Ocorrencia_Reentrega_CTRC;
  }

  public int getOID_Tipo_Ocorrencia_Devolucao_CTRC () {
    return OID_Tipo_Ocorrencia_Devolucao_CTRC;
  }

  public String getDM_Gera_Compromisso_Nota_Fiscal_Compra () {
    return DM_Gera_Compromisso_Nota_Fiscal_Compra;
  }

  public String getDM_Gera_Compromisso_Ordem_Abastecimento () {
    return DM_Gera_Compromisso_Ordem_Abastecimento;
  }

  public String getDM_Formulario_Ordem_Abastecimento () {
    return DM_Formulario_Ordem_Abastecimento;
  }

  public long getOID_Unidade_Faturamento_Minuta () {
    return OID_Unidade_Faturamento_Minuta;
  }

  public String getDM_Tipo_Impressao_Minuta () {
    return DM_Tipo_Impressao_Minuta;
  }

  public int getOID_Historico_Transferencia_Caixa () {
    return OID_Historico_Transferencia_Caixa;
  }

  public int getOID_Historico_Transferencia_Banco () {
    return OID_Historico_Transferencia_Banco;
  }

  public String getDM_Formulario_Ordem_Carga () {
    return DM_Formulario_Ordem_Carga;
  }

  public String getDM_Numera_Ordem_Carga () {
    return DM_Numera_Ordem_Carga;
  }

  public int getOID_Tipo_Movimento_Ressarcimento () {
    return OID_Tipo_Movimento_Ressarcimento;
  }

  public String getDM_Formulario_Minuta_Embarque () {

    return DM_Formulario_Minuta_Embarque;
  }

  public String getDM_Tipo_Impressao_Nota_Fiscal_Servico () {
    return DM_Tipo_Impressao_Nota_Fiscal_Servico;
  }

  public String getDM_DT_Conhecimento () {
    return DM_DT_Conhecimento;
  }

  public String getDM_Saldo_Acerto_Comissao_Motorista () {
    return DM_Saldo_Acerto_Comissao_Motorista;
  }

  public int getOID_Tipo_Servico_Abastecimento () {
    return OID_Tipo_Servico_Abastecimento;
  }

  public String getDM_Formulario_ACT () {
    return DM_Formulario_ACT;
  }

  public int getOID_Conta_Multa_Transito () {
    return OID_Conta_Multa_Transito;
  }

  public void setVias_Fatura (int vias_Fatura) {
    this.vias_Fatura = vias_Fatura;
  }

  public void setGetEDI_Importa_Nota_FiscalPath (String getEDI_Importa_Nota_FiscalPath) {
    this.getEDI_Importa_Nota_FiscalPath = getEDI_Importa_Nota_FiscalPath;
  }

  public void setDM_Gera_Custo_Viagem (String DM_Gera_Custo_Viagem) {
    this.DM_Gera_Custo_Viagem = DM_Gera_Custo_Viagem;
  }

  public void setDM_Verifica_CNPJ_CPF_Pessoa (String DM_Verifica_CNPJ_CPF_Pessoa) {
    this.DM_Verifica_CNPJ_CPF_Pessoa = DM_Verifica_CNPJ_CPF_Pessoa;
  }

  public void setDM_Envia_Email_Eventos (String DM_Envia_Email_Eventos) {
    this.DM_Envia_Email_Eventos = DM_Envia_Email_Eventos;
  }

  public void setDM_Formulario_Romaneio_Notal_Fiscal (String DM_Formulario_Romaneio_Notal_Fiscal) {

    this.DM_Formulario_Romaneio_Notal_Fiscal = DM_Formulario_Romaneio_Notal_Fiscal;
  }

  public void setOID_Tipo_Movimento_Ajuste_S (int OID_Tipo_Movimento_Ajuste_S) {
    this.OID_Tipo_Movimento_Ajuste_S = OID_Tipo_Movimento_Ajuste_S;
  }

  public void setOID_Tipo_Movimento_Ajuste_E (int OID_Tipo_Movimento_Ajuste_E) {
    this.OID_Tipo_Movimento_Ajuste_E = OID_Tipo_Movimento_Ajuste_E;
  }

  public void setOID_Tipo_Movimento_Custo_Devolucao (int OID_Tipo_Movimento_Custo_Devolucao) {
    this.OID_Tipo_Movimento_Custo_Devolucao = OID_Tipo_Movimento_Custo_Devolucao;
  }

  public void setOID_Modal_Aereo_Sedex (int OID_Modal_Aereo_Sedex) {
    this.OID_Modal_Aereo_Sedex = OID_Modal_Aereo_Sedex;
  }

  public void setOID_Modal_Aereo_RodExp (int OID_Modal_Aereo_RodExp) {
    this.OID_Modal_Aereo_RodExp = OID_Modal_Aereo_RodExp;
  }

  public void setOID_Modal_Aereo_Pac (int OID_Modal_Aereo_Pac) {
    this.OID_Modal_Aereo_Pac = OID_Modal_Aereo_Pac;
  }

  public void setOID_Modal_Aereo_Expressa (int OID_Modal_Aereo_Expressa) {
    this.OID_Modal_Aereo_Expressa = OID_Modal_Aereo_Expressa;
  }

  public void setOID_Modal_Aereo_Emergencial (int OID_Modal_Aereo_Emergencial) {
    this.OID_Modal_Aereo_Emergencial = OID_Modal_Aereo_Emergencial;
  }


  public void setOID_Tipo_Ocorrencia_Reentrega_CTRC (int OID_Tipo_Ocorrencia_Reentrega_CTRC) {
    this.OID_Tipo_Ocorrencia_Reentrega_CTRC = OID_Tipo_Ocorrencia_Reentrega_CTRC;
  }

  public void setOID_Tipo_Ocorrencia_Devolucao_CTRC (int OID_Tipo_Ocorrencia_Devolucao_CTRC) {
    this.OID_Tipo_Ocorrencia_Devolucao_CTRC = OID_Tipo_Ocorrencia_Devolucao_CTRC;
  }

  public void setDM_Gera_Compromisso_Nota_Fiscal_Compra (String DM_Gera_Compromisso_Nota_Fiscal_Compra) {
    this.DM_Gera_Compromisso_Nota_Fiscal_Compra = DM_Gera_Compromisso_Nota_Fiscal_Compra;
  }

  public void setDM_Gera_Compromisso_Ordem_Abastecimento (String DM_Gera_Compromisso_Ordem_Abastecimento) {
    this.DM_Gera_Compromisso_Ordem_Abastecimento = DM_Gera_Compromisso_Ordem_Abastecimento;
  }

  public void setDM_Formulario_Ordem_Abastecimento (String DM_Formulario_Ordem_Abastecimento) {
    this.DM_Formulario_Ordem_Abastecimento = DM_Formulario_Ordem_Abastecimento;
  }

  public void setOID_Unidade_Faturamento_Minuta (long OID_Unidade_Faturamento_Minuta) {
    this.OID_Unidade_Faturamento_Minuta = OID_Unidade_Faturamento_Minuta;
  }

  public void setDM_Tipo_Impressao_Minuta (String DM_Tipo_Impressao_Minuta) {
    this.DM_Tipo_Impressao_Minuta = DM_Tipo_Impressao_Minuta;
  }

  public void setOID_Historico_Transferencia_Caixa (int OID_Historico_Transferencia_Caixa) {
    this.OID_Historico_Transferencia_Caixa = OID_Historico_Transferencia_Caixa;
  }

  public void setOID_Historico_Transferencia_Banco (int OID_Historico_Transferencia_Banco) {
    this.OID_Historico_Transferencia_Banco = OID_Historico_Transferencia_Banco;
  }

  public void setDM_Formulario_Ordem_Carga (String DM_Formulario_Ordem_Carga) {
    this.DM_Formulario_Ordem_Carga = DM_Formulario_Ordem_Carga;
  }

  public void setDM_Numera_Ordem_Carga (String DM_Numera_Ordem_Carga) {
    this.DM_Numera_Ordem_Carga = DM_Numera_Ordem_Carga;
  }

  public void setOID_Tipo_Movimento_Ressarcimento (int OID_Tipo_Movimento_Ressarcimento) {
    this.OID_Tipo_Movimento_Ressarcimento = OID_Tipo_Movimento_Ressarcimento;
  }

  public void setDM_Formulario_Minuta_Embarque (String DM_Formulario_Minuta_Embarque) {

    this.DM_Formulario_Minuta_Embarque = DM_Formulario_Minuta_Embarque;
  }

  public void setDM_Tipo_Impressao_Nota_Fiscal_Servico (String DM_Tipo_Impressao_Nota_Fiscal_Servico) {
    this.DM_Tipo_Impressao_Nota_Fiscal_Servico = DM_Tipo_Impressao_Nota_Fiscal_Servico;
  }

  public void setDM_DT_Conhecimento (String DM_DT_Conhecimento) {
    this.DM_DT_Conhecimento = DM_DT_Conhecimento;
  }

  public void setDM_Saldo_Acerto_Comissao_Motorista (String DM_Saldo_Acerto_Comissao_Motorista) {
    this.DM_Saldo_Acerto_Comissao_Motorista = DM_Saldo_Acerto_Comissao_Motorista;
  }

  public void setOID_Tipo_Servico_Abastecimento (int OID_Tipo_Servico_Abastecimento) {
    this.OID_Tipo_Servico_Abastecimento = OID_Tipo_Servico_Abastecimento;
  }

  public void setDM_Formulario_ACT (String DM_Formulario_ACT) {
    this.DM_Formulario_ACT = DM_Formulario_ACT;
  }

  public void setOID_Conta_Multa_Transito (int OID_Conta_Multa_Transito) {
    this.OID_Conta_Multa_Transito = OID_Conta_Multa_Transito;
  }

  public String getNM_Ano_Permisso () {
    return NM_Ano_Permisso;
  }

  public void setNM_Ano_Permisso (String ano_Permisso) {
    NM_Ano_Permisso = ano_Permisso;
  }

  public double getPE_Aliquota_PIS_COFINS () {
    return PE_Aliquota_PIS_COFINS;
  }

  public void setPE_Aliquota_PIS_COFINS (double aliquota_PIS_COFINS) {
    PE_Aliquota_PIS_COFINS = aliquota_PIS_COFINS;
  }

  public int getOID_Historico_Liquidacao_Cobranca_Retorno () {
    return OID_Historico_Liquidacao_Cobranca_Retorno;
  }

  public void setOID_Historico_Liquidacao_Cobranca_Retorno (
      int historico_Liquidacao_Cobranca_Retorno) {
    OID_Historico_Liquidacao_Cobranca_Retorno = historico_Liquidacao_Cobranca_Retorno;
  }

  public int getOID_Carteira_Nota_Retorno () {
    return OID_Carteira_Nota_Retorno;
  }

  public void setOID_Carteira_Nota_Retorno (int carteira_Nota_Retorno) {
    OID_Carteira_Nota_Retorno = carteira_Nota_Retorno;
  }

public String getDM_Gera_Livro_Fiscal() {
	return DM_Gera_Livro_Fiscal;
}
public void setDM_Gera_Livro_Fiscal(String gera_Livro_Fiscal) {
	DM_Gera_Livro_Fiscal = gera_Livro_Fiscal;
}

public String getDM_Formulario_Protocolo_Cobranca() {
	return DM_Formulario_Protocolo_Cobranca;
}

public void setDM_Formulario_Protocolo_Cobranca (String DM_Formulario_Protocolo_Cobranca) {
    this.DM_Formulario_Protocolo_Cobranca = DM_Formulario_Protocolo_Cobranca;
}

public String getDm_Wms_Nf_Saida_Numerada() {
	return dm_Wms_Nf_Saida_Numerada;
}

public String getDM_Tipo_Impressao_Ordem_Frete() {
	return DM_Tipo_Impressao_Ordem_Frete;
}

public void setDM_Tipo_Impressao_Ordem_Frete(String tipo_Impressao_Ordem_Frete) {
	DM_Tipo_Impressao_Ordem_Frete = tipo_Impressao_Ordem_Frete;
}


public String getPATH_RELATORIOS_XSL() {
	return PATH_RELATORIOS_XSL;
}

public void setPATH_RELATORIOS_XSL(String path_relatorios_xsl) {
	PATH_RELATORIOS_XSL = path_relatorios_xsl;
}

public String getDM_Calcula_Previsao_Entrega() {
	return DM_Calcula_Previsao_Entrega;
}

public void setDM_Calcula_Previsao_Entrega(String calcula_Prazo_Entrega) {
	DM_Calcula_Previsao_Entrega = calcula_Prazo_Entrega;
}

public String getDM_Calcula_INSS_Para_Motorista() {
	return DM_Calcula_INSS_Para_Motorista;
}

public void setDM_Calcula_INSS_Para_Motorista(String calcula_INSS_Para_Motorista) {
	DM_Calcula_INSS_Para_Motorista = calcula_INSS_Para_Motorista;
}

public String getDM_Soma_Carga_Saldo_Frete() {
	return DM_Soma_Carga_Saldo_Frete;
}

public void setDM_Soma_Carga_Saldo_Frete(String soma_Carga_Saldo_Frete) {
	DM_Soma_Carga_Saldo_Frete = soma_Carga_Saldo_Frete;
}

public String getDM_Soma_Coleta_Saldo_Frete() {
	return DM_Soma_Coleta_Saldo_Frete;
}

public void setDM_Soma_Coleta_Saldo_Frete(String soma_Coleta_Saldo_Frete) {
	DM_Soma_Coleta_Saldo_Frete = soma_Coleta_Saldo_Frete;
}

public String getDM_Soma_Descarga_Saldo_Frete() {
	return DM_Soma_Descarga_Saldo_Frete;
}

public void setDM_Soma_Descarga_Saldo_Frete(String soma_Descarga_Saldo_Frete) {
	DM_Soma_Descarga_Saldo_Frete = soma_Descarga_Saldo_Frete;
}

public String getDM_Soma_Descontos_Saldo_Frete() {
	return DM_Soma_Descontos_Saldo_Frete;
}

public void setDM_Soma_Descontos_Saldo_Frete(String soma_Descontos_Saldo_Frete) {
	DM_Soma_Descontos_Saldo_Frete = soma_Descontos_Saldo_Frete;
}

public String getDM_Soma_Premio_Saldo_Frete() {
	return DM_Soma_Premio_Saldo_Frete;
}

public void setDM_Soma_Premio_Saldo_Frete(String soma_Premio_Saldo_Frete) {
	DM_Soma_Premio_Saldo_Frete = soma_Premio_Saldo_Frete;
}
public String getDM_Dia_Nao_Util() {
	return DM_Dia_Nao_Util;
}

public void setDM_Dia_Nao_Util(String dia_Util_Feriado) {
	DM_Dia_Nao_Util = dia_Util_Feriado;
}

public String getDT_Inicial_Previsao_Entrega() {
	return DT_Inicial_Previsao_Entrega;
}

public void setDT_Inicial_Previsao_Entrega(
		String data_Inicial_Previsao_Entrega) {
	DT_Inicial_Previsao_Entrega = data_Inicial_Previsao_Entrega;
}


public String getDM_Soma_INSS_Prestador_Saldo_Frete() {
	return DM_Soma_INSS_Prestador_Saldo_Frete;
}


public void setDM_Soma_INSS_Prestador_Saldo_Frete(
		String soma_INSS_Prestador_Saldo_Frete) {
	DM_Soma_INSS_Prestador_Saldo_Frete = soma_INSS_Prestador_Saldo_Frete;
}

public String getDM_Soma_IRRF_Saldo_Frete() {
	return DM_Soma_IRRF_Saldo_Frete;
}

public void setDM_Soma_IRRF_Saldo_Frete(String soma_IRRF_Saldo_Frete) {
	DM_Soma_IRRF_Saldo_Frete = soma_IRRF_Saldo_Frete;
}

public int getOID_Conta_Juridica_Ordem_Frete_Adiantamento() {
	return OID_Conta_Juridica_Ordem_Frete_Adiantamento;
}

public void setOID_Conta_Juridica_Ordem_Frete_Adiantamento(
		int Conta_Juridica_Ordem_Frete_Adiantamento) {
	OID_Conta_Juridica_Ordem_Frete_Adiantamento = Conta_Juridica_Ordem_Frete_Adiantamento;
}

public String getDM_Comissao_Informada() {
	return DM_Comissao_Informada;
}

public void setDM_Comissao_Informada(String comissao_Informada) {
	DM_Comissao_Informada = comissao_Informada;
}

public String getDM_Gera_Lancamento_Contabil_Acerto() {
	return DM_Gera_Lancamento_Contabil_Acerto;
}

public void setDM_Gera_Lancamento_Contabil_Acerto(
		String gera_Lancamento_Contabil_Acerto) {
	DM_Gera_Lancamento_Contabil_Acerto = gera_Lancamento_Contabil_Acerto;
}

public boolean isExigePedidoNotaCompra() {
	return exigePedidoNotaCompra;
}

public void setExigePedidoNotaCompra(boolean exigePedidoNotaCompra) {
	this.exigePedidoNotaCompra = exigePedidoNotaCompra;
}

public String getOID_Fornecedor_COFINS() {
	return OID_Fornecedor_COFINS;
}

public void setOID_Fornecedor_COFINS(String fornecedor_COFINS) {
	OID_Fornecedor_COFINS = fornecedor_COFINS;
}

public String getOID_Fornecedor_CSLL() {
	return OID_Fornecedor_CSLL;
}

public void setOID_Fornecedor_CSLL(String fornecedor_CSLL) {
	OID_Fornecedor_CSLL = fornecedor_CSLL;
}

public String getOID_Fornecedor_ISSQN() {
	return OID_Fornecedor_ISSQN;
}

public void setOID_Fornecedor_ISSQN(String fornecedor_ISSQN) {
	OID_Fornecedor_ISSQN = fornecedor_ISSQN;
}

public String getOID_Fornecedor_PIS() {
	return OID_Fornecedor_PIS;
}

public void setOID_Fornecedor_PIS(String fornecedor_PIS) {
	OID_Fornecedor_PIS = fornecedor_PIS;
}

public int getOID_Conta_INSS() {
	return OID_Conta_INSS;
}

public void setOID_Conta_INSS(int conta_INSS) {
	OID_Conta_INSS = conta_INSS;
}

public int getOID_Conta_IRRF() {
	return OID_Conta_IRRF;
}

public void setOID_Conta_IRRF(int conta_IRRF) {
	OID_Conta_IRRF = conta_IRRF;
}

public int getOID_Conta_ISSQN() {
	return OID_Conta_ISSQN;
}

public void setOID_Conta_ISSQN(int conta_ISSQN) {
	OID_Conta_ISSQN = conta_ISSQN;
}

public int getOID_Conta_PIS_COFINS_CSLL() {
	return OID_Conta_PIS_COFINS_CSLL;
}

public void setOID_Conta_PIS_COFINS_CSLL(int conta_PIS_COFINS_CSLL) {
	OID_Conta_PIS_COFINS_CSLL = conta_PIS_COFINS_CSLL;
}

public String getDM_Subtrai_Pedagio_PIS_COFINS() {
	return DM_Subtrai_Pedagio_PIS_COFINS;
}

public void setDM_Subtrai_Pedagio_PIS_COFINS(String subtrai_Pedagio_PIS_COFINS) {
	DM_Subtrai_Pedagio_PIS_COFINS = subtrai_Pedagio_PIS_COFINS;
}

  private Base_EmpresaED base_EmpresaED = new Base_EmpresaED ();
  private String getEDI_Importa_Nota_FiscalPath;
  private String DM_Saldo_Acerto_Comissao_Motorista;
  private String DM_DT_Conhecimento;
  private String DM_Formulario_Minuta_Embarque;
  private String DM_Numera_Ordem_Carga;
  private String DM_Formulario_Ordem_Carga;

  public Parametro_FixoED () {
    // Seta os par�metros padr�es
    doSetParametrosPadrao ();

    if ("KIELING".equals (getNM_Empresa ())) {
        if ("CTE".equals (getNM_Base ())) {
        	setNM_Database_Host ("//127.0.0.1:5432");
            setNM_Database_Port (5432);
//            setNM_Database_URL ("jdbc:postgresql://127.0.0.1:5432/sistema");
            setNM_Database_URL ("jdbc:postgresql://127.0.0.1:5432/nadadebd");
            setNM_Database_Driver ("org.postgresql.Driver");
            setNM_Database_Usuario ("postgres");
            setNM_Database_Pwd ("2u7@3.92xz");
          appPath = "/data/doc-e/";
          PATH_RELATORIOS = "/data/doc-e/relatoriosJasper/";
          PATH_IMAGENS = "/data/doc-e/relatoriosJasper/imagens/";
          PATH_RELATORIOS_XSL = "/data/doc-e/relatorioxsl/";
          setDocument_Form_Action ("http://ns2.kieling.com.br:8180/cte/");
          doc_ePath = "http://ns2.kieling.com.br:8180/cte";
        }

        PATH_RELATORIOS = appPath + "/relatoriosJasper/";
        PATH_IMAGENS = appPath + "/relatoriosJasper/imagens/";
        System.setProperty ("CaminhoXSL" , appPath + "/relatorioxsl/");
        System.setProperty("pro_path", appPath + "/WEB-INF/props/");
        doSetParametrosMiro ();

      } else if ("MIRO".equals (getNM_Empresa ())) {
          if ("CTE".equals (getNM_Base ())) {
          	setNM_Database_Host ("//127.0.0.1:5432");
              setNM_Database_Port (5432);
              setNM_Database_URL ("jdbc:postgresql://127.0.0.1:5432/miro");
              setNM_Database_Driver ("org.postgresql.Driver");
              setNM_Database_Usuario ("postgres");
              setNM_Database_Pwd ("");
            appPath = "/data/cte/";
            PATH_RELATORIOS = "/data/cte/relatoriosJasper/";
            PATH_IMAGENS = "/data/cte/relatoriosJasper/imagens/";
            PATH_RELATORIOS_XSL = "/data/cte/relatorioxsl/";
            setDocument_Form_Action ("http://177.43.246.18:8080/cte/");
            doc_ePath = "http://177.43.246.18:8080/cte";
          } else if ("HOMOLOGA".equals (getNM_Base ())) {
            	setNM_Database_Host ("//127.0.0.1:5432");
                setNM_Database_Port (5432);
                setNM_Database_URL ("jdbc:postgresql://127.0.0.1:5432/homologa");
                setNM_Database_Driver ("org.postgresql.Driver");
                setNM_Database_Usuario ("postgres");
                setNM_Database_Pwd ("");
              appPath = "/data/hcte/";
              PATH_RELATORIOS = "/data/hcte/relatoriosJasper/";
              PATH_IMAGENS = "/data/hcte/relatoriosJasper/imagens/";
              PATH_RELATORIOS_XSL = "/data/hcte/relatorioxsl/";
              setDocument_Form_Action ("http://177.43.246.18:8080/hcte/");
              doc_ePath = "http://177.43.246.18:8080/hcte";
            }

          PATH_RELATORIOS = appPath + "/relatoriosJasper/";
          PATH_IMAGENS = appPath + "/relatoriosJasper/imagens/";
          System.setProperty ("CaminhoXSL" , appPath + "/relatorioxsl/");
          System.setProperty("pro_path", appPath + "/WEB-INF/props/");
          doSetParametrosMiro ();
        } else if ("TSG".equals (getNM_Empresa ())) {
        	if ("CTE".equals (getNM_Base ())) {
                setNM_Database_Host ("//127.0.0.1:5432");
                setNM_Database_Port (5432);
                setNM_Database_URL ("jdbc:postgresql://127.0.0.1:5432/tsg");
                setNM_Database_Driver ("org.postgresql.Driver");
                setNM_Database_Usuario ("postgres");
                setNM_Database_Pwd ("");
              appPath = "/data/doc-e/";
              PATH_RELATORIOS = appPath+"relatoriosJasper/";
              PATH_IMAGENS = appPath+"relatoriosJasper/imagens/";
              PATH_RELATORIOS_XSL = appPath+"relatorioxsl/";
              setDocument_Form_Action ("http://186.215.196.202:8080/doc-e/");
              doc_ePath = "http://186.215.196.202:8080/doc-e";
        	}
        } else if ("ATR".equals (getNM_Empresa ())) {
            if ("CTE".equals (getNM_Base ())) {
              	setNM_Database_Host ("//127.0.0.1:5432");
                  setNM_Database_Port (5432);
                  setNM_Database_URL ("jdbc:postgresql://127.0.0.1:5432/homo_atr");
                  setNM_Database_Driver ("org.postgresql.Driver");
                  setNM_Database_Usuario ("postgres");
                  setNM_Database_Pwd ("");
                appPath = "/data/cte/";
                PATH_RELATORIOS = "/data/cte/relatoriosJasper/";
                PATH_IMAGENS = "/data/cte/relatoriosJasper/imagens/";
                PATH_RELATORIOS_XSL = "/data/cte/relatorioxsl/";
                setDocument_Form_Action ("http://177.43.246.18:8080/cte/");
                doc_ePath = "http://177.43.246.18:8080/cte";
              }

              PATH_RELATORIOS = appPath + "/relatoriosJasper/";
              PATH_IMAGENS = appPath + "/relatoriosJasper/imagens/";
              System.setProperty ("CaminhoXSL" , appPath + "/relatorioxsl/");
              System.setProperty("pro_path", appPath + "/WEB-INF/props/");
              doSetParametrosMiro ();
            }
  }

  private void doSetParametrosPadrao () {
    // /PADROES DEFAULT
    NM_Versao = "08.0.1";
    NM_Razao_Social_Empresa = "";

    DM_Gera_Livro_Fiscal = "N";
    dm_Wms_Nf_Saida_Numerada = "S";
    DM_Tipo_Sistema = "";
    DM_Operacao_Sistema = "-----O---------"; //"NIAWDOM--------" => Nacional, Internacional, Aereo, Wms, Distribuidora, Office, Manutencao
    DM_Perfil_Sistema = "Lite";

    DM_Multi_Moeda = "N";
    DM_Envia_Email_Eventos = "N";
    vias_Fatura = 1;
    NM_Ano_Permisso = "/XX";

    DM_Saldo_Programado = "N";
    DM_Liquida_Compromisso = "Lote_Pagamento";

    DM_Tipo_Calculo_ICMS = "POR_DENTRO";

    setDocument_Form_Action ("http://localhost:8180/Sistema/");

    DM_Situacao = "OK";
    // DM_Situacao="Sistema em Manuten��o. Aguarde !!!";

    DM_Dia_Nao_Util="SD"; //sabado, domingo

    DM_Formulario_Duplicata = "Fatura_Padrao";
    DM_Formulario_Demonstrativo_Cobranca = "Demonstrativo_Cobranca_Modelo1";
    DM_Formulario_Protocolo_Cobranca = "Protocolo_Cobranca";
    DM_Formulario_Minuta = "Minuta_Padrao";
    DM_Formulario_Romaneio_Notal_Fiscal = "Romaneio_Nota_Fiscal_Padrao";
    DM_Formulario_Conhecimento_Nacional = "Conhecimento_Nacional_Padrao";
    DM_Formulario_Ordem_Servico = "Ordem_Servico_Padrao";
    DM_Formulario_Minuta_Embarque = "Minuta_Embarque_Padrao";
    DM_Tipo_Impressao_Conhecimento_Nacional = "XSL";
    DM_Tipo_Impressao_Minuta = "XSL";
    DM_Tipo_Impressao_Nota_Fiscal_Servico = "XSL";
    DM_Tipo_Impressao_Coleta = "XSL";
    DM_DT_Conhecimento = "IMPRESSAO";

    DM_Formulario_Conhecimento_Internacional = "Conhecimento_Internacional_Padrao";
    DM_Formulario_Coleta = "Coleta_Padrao";
    DM_Formulario_Ordem_Frete = "Ordem_Frete_Padrao";
    DM_Formulario_Ordem_Frete_Adiantamento = "Ordem_Frete_Adiantamento_Padrao";
    DM_Formulario_Ordem_Abastecimento = "Ordem_Frete_Adiantamento_Padrao";
    DM_Formulario_Nota_Fiscal = "Nota_Fiscal_Padrao";
    DM_Formulario_Manifesto = "Manifesto_Padrao";
    DM_Formulario_Manifesto_Redespacho = "Manifesto_Redespacho_Padrao";
    DM_Formulario_Acerto_Contas = "Acerto_de_Contas";
    DM_Formulario_Romaneio_Entrega = "Romaneio_Entrega_Padrao";

    EDI_Contabil = "//data//edi//contabil//contabil.txt";
    EDI_Seguradora = "//data//edi//seguradora//seguro.txt";
    EDI_Duplicata = "//data//edi//duplicata//duplicata.txt";
    EDI_Banco = "//data//edi//banco//banco.txt";
    EDI_Cliente = "//data//edi//cliente//cliente.txt";
    EDI_Fiscal = "//data//edi//fiscal//fiscal.txt";

    NM_Sucessivos = "";
    VL_Reembolso = "";

    completa_TX_Via = false;
    exigePedidoNotaCompra = false;

    TX_Via_Primeiro_Original_Pt = "";
    TX_Via_Segundo_Original_Pt = "";
    TX_Via_Terceiro_Original_Pt = "";

    TX_Via_Primeiro_Original_Es = "";
    TX_Via_Segundo_Original_Es = "";
    TX_Via_Terceiro_Original_Es = "";

    oid_Tipo_Faturamento_Exportador = 1;
    oid_Tipo_Faturamento_Importador = 2;
    dm_Impressao_Tramo = "50";

    DM_Imprime_Conhecimento_Ordem_Frete = "Nacional";
    DM_Tipo_Conhecimento = "Nacional";

    DM_Conhecimento_Varios_Manifestos = "N";

    DM_Gera_Lancamento_Contabil = "N";
    DM_Quebra_Faturamento_Tipo_Conhecimento = "N";
    DM_Quebra_Faturamento_Unidade = "N";

    DM_Atualiza_Movimento_Caixa = "N";

    DM_Quebra_Faturamento_Tipo_Faturamento = "N";

    DM_Calcula_PIS_COFINS = "N";
    DM_Inclui_ICMS_Parcela_CTRC = "N";
    DM_Calcula_INSS_Para_Motorista = "N";
    DM_Calcula_INSS = "S";
    DM_Calcula_IRRF = "S";
    DM_Despacho = "N";
    DM_Exige_Pagador_Cliente = "N";
    DM_Gera_Ocorrencia_Viagem = "N";
    DM_Gera_Compromisso_Viagem = "N";

    DM_Gera_Compromisso_Ordem_Frete = "S";
    DM_Gera_Rateio_Custo_Ordem_Frete = "N";
    DM_Gera_Embarque_Viagem = "N";

    DM_Gera_Compromisso_Ordem_Frete_Adiantamento = "N";
    DM_Gera_Compromisso_Motorista_Proprietario = "N";
    DM_Gera_Compromisso_Ordem_Abastecimento = "N";
    DM_Gera_Compromisso_Master = "N";
    DM_Gera_Compromisso_Movimento_Ordem_Servico = "S";
    DM_Gera_Compromisso_Nota_Fiscal_Compra = "S";
    DM_Gera_Lancamento_Conta_Corrente_Ordem_Frete_Terceiro = "N";
    DM_Gera_Custo_Viagem="N";
    DM_Gera_Lancamento_Conta_Corrente_Ordem_Frete_Adiantamento = "N";

    DM_Gera_Lancamento_Conta_Corrente_Ordem_Frete = "N";

    DM_Gera_Lancamento_Conta_Corrente_Acerto_Contas = "N";

    OID_Tipo_Faturamento_Consolidacao_MIC_CRT = 7;

    DM_Formulario_Consolidacao_MIC = "";
    DM_Formulario_Consolidacao_MIC_CRT = "";

    DM_Formulario_Duplicata_Proform = "";

    DM_Soma_Pedagio_Saldo_Frete = "N";
    DM_Soma_Outros_Saldo_Frete = "S";
    DM_Soma_Sest_Senat_Saldo_Frete = "N";
    DM_Soma_Coleta_Saldo_Frete = "S";
    DM_Soma_Carga_Saldo_Frete = "S";
    DM_Soma_Descarga_Saldo_Frete = "S";
    DM_Soma_Premio_Saldo_Frete = "S";
    DM_Soma_Descontos_Saldo_Frete = "S";
    DM_Soma_INSS_Prestador_Saldo_Frete="S";
    DM_Soma_IRRF_Saldo_Frete="S";

    DM_Vincula_Adto_Ordem_Principal = "TODOS";

    DM_Frete_Cortesia = "PARAMETRO";
    VL_Frete_Cortesia = 0.01;

    DM_Atualiza_Odometro_Veiculo = "S";
    DM_Atualiza_Odometro_Acerto = "N";
    DM_Atualiza_Odometro_Ordem_Servico = "N";
    DM_Atualiza_Odometro_Pneus = "N";
    DM_Atualiza_Odometro_Portaria = "N";
    DM_Atualiza_Odometro_Abastecimento = "S";

    DM_Limite_Credito_Motorista_Adiantamento_Frete = "S";

    DM_Estado_Origem_Taxas = "R"; // remetente
    DM_Estado_Destino_Taxas = "D"; // pagador

    DM_Estado_Origem_CFOP = "C"; // == COLETA
    DM_Estado_Destino_CFOP = "E"; // == ENTREGA

    DM_Numera_Ordem_Frete = "E"; // I=Inclusao E=Emissao
    DM_Numera_Coleta = "E";
    DM_Numera_Manifesto = "E";
    DM_Numera_Conhecimento = "E";
    DM_Numera_Frete_Internacional = "E";
    DM_Numera_Fatura = "E";

    DM_Criterio_Comissao = "TODOS_CTOS_LIQUIDO";

    PE_Comissao_Motorista = 0;
    PE_Base_Comissao_Motorista = 14;
    DM_Base_Comissao_Motorista = "FP";
    DM_Saldo_Acerto_Comissao_Motorista = "DESCONTA";

    PE_Comissao_Motorista_Media = 0;

    NR_Vias_Ordem_Frete_Adiantamento = 1;
    NR_Vias_Ordem_Frete = 1;

    NR_Peso_Cubado = 1;

    VL_Maximo_INSS = 381.41 ; //293.50; jan/08
    PE_Aliquota_Empresa_INSS = 0.2;
    PE_Aliquota_Prestador_INSS = 0.11;
    PE_Base_INSS = 0.2;
    PE_Base_SET_SENAT = 0.2;
    PE_Aliquota_Prestador_Set_Senat = 0.025;

	VL_Faixa1 = 1499.16; ///1372.81; // 1313.70; jan/09
    VL_Faixa2 = 2246.76;
    VL_Faixa3 = 2995.71;
    VL_Faixa4 = 3743.20; // 2625.12; jan/09

    VL_Deducao_Faixa1 = 112.43;
    VL_Deducao_Faixa2 = 280.94;
    VL_Deducao_Faixa3 = 505.62;
    VL_Deducao_Faixa4 = 692.78;

    PE_Imposto_Faixa1 = 0.075;
    PE_Imposto_Faixa2 = 0.15;
    PE_Imposto_Faixa3 = 0.225;
    PE_Imposto_Faixa4 = 0.275;

    PE_Base_Calculo = 0.4;
    VL_Dependente = 150.69;// 137.99; //132.05;



    OID_Tipo_Ocorrencia_Desconto_CTRC = 1;

    OID_Tipo_Ocorrencia_Devolucao_CTRC = 1;
    OID_Tipo_Ocorrencia_Reentrega_CTRC = 1;

    OID_Tipo_Ocorrencia_Cancelamento_CTRC = 1;
    oid_Tipo_Documento_Nota_Fiscal_Entrada = 12;
    oid_Tipo_Documento_Nota_Fiscal_Saida = 14;
    OID_Tipo_Documento_Transferencia_Conta_Corrente = 1;
    OID_Tipo_Documento_Ordem_Servico = 8;
    OID_Tipo_Servico_Requisicao_Estoque = 83;

    OID_Unidade_Padrao = 1;
    OID_Moeda_Padrao = 1;
    oid_Modelo_Nota_Fiscal = 1;
    oid_Modelo_NF_DevFornecedor_Dentro_Estado = 9;
    oid_Modelo_NF_DevFornecedor_Fora_Estado = 10;
    OID_Produto = 1;
    OID_Historico_Lancamento_Ordem_Frete_Terceiro = 1;
    OID_Historico_Lancamento_Ordem_Frete = 1;
    OID_Historico_Lancamento_Ordem_Frete_Adiantamento = 1;

    OID_Conta_Acerto_Contas = 7;

    NM_Nivel_Produto1 = "Area";
    NM_Nivel_Produto2 = "Departamento";
    NM_Nivel_Produto3 = "Setor";
    NM_Nivel_Produto4 = "Grupo";

    NM_Nivel_Produto5 = "Diversos";

    NM_Nivel_Produto5 = "Diversos";

    // Bloqueia inclus�o da mesma nf em dois ou mais conhecimentos:
    setNF_Multi_Conhecimento (false);

    setDT_Hoje (Data.getDataDMY ());
    setHR_Hoje (Data.getHoraHM ());

    setNR_Conhecimentos_Linha_Fatura (2);
    setCFOP_Padrao ("6351");
    DM_Tipo_Impressao_Fatura = "Matricial";

    DM_Base_Comissao_Motorista = "TF";

    logTransactions = false;
    DM_Tipo_Calculo_ICMS = "POR_DENTRO";
    DM_Formulario_AWB = "AWB_Padrao";
    movimento_Ordem_Servico_Duplicada = true;
  }

  private void doSetParametrosMiro () {

    DM_Tipo_Sistema = "Transporte";
    DM_Perfil_Sistema = "Master";
    DM_Operacao_Sistema = "N--W-OM--------"; //"NIAWDOM--------" => Nacional, Internacional, Aereo, Wms, Distribuidora, Office, Manutencao

    NR_Peso_Cubado = 1;

    setDmUsaCTB ("T");
    //DM_Liquida_Compromisso = "Pelo_Compromisso";

    NM_Razao_Social_Empresa = "-TRANSMIRO-";

    DM_Formulario_Duplicata = "Fatura_Miro";

    DM_Tipo_Impressao_Fatura = "MATRICIAL";
    //DM_Tipo_Impressao_Conhecimento_Nacional = "JASPER";
    DM_Tipo_Impressao_Conhecimento_Nacional = "MATRICIAL";
    DM_Tipo_Impressao_Minuta = "XSL";
    DM_Tipo_Impressao_Coleta = "MATRICIAL";
    DM_Tipo_Impressao_Ordem_Frete="MATRICIAL";
    DM_Comissao_Informada = "VALOR_INFORMADO";
    EDI_Contabil =   "//data//miro//arquivos//edi//contabil//contabil.txt";
    EDI_Seguradora = "//data//miro//arquivos//edi//seguradora//seguro.txt";
    EDI_Duplicata =  "//data//miro//arquivos//edi//duplicata//duplicata.txt";
    EDI_Banco =      "//data//miro//arquivos//edi//banco//banco.txt";
    EDI_Cliente =    "//data//miro//arquivos//edi//cliente//cliente.txt";
    EDI_Fiscal =     "//data//miro//arquivos//edi//fiscal//fiscal.txt";
    OID_Tipo_Estoque = 1;
    DM_Formulario_Demonstrativo_Cobranca = "Demonstrativo_Cobranca_Modelo1";

    DM_Inclui_ICMS_Parcela_CTRC = "N";
    DM_Formulario_Coleta = "Coleta_Miro";

    OID_Fornecedor_INSS   = "99999900007326";
    OID_Fornecedor_COFINS = "99999900007164";
    OID_Fornecedor_IRRF   = "99999900007164";
    OID_Fornecedor_CSLL   = "99999900007164";
    OID_Fornecedor_PIS    = "99999900007164";
    OID_Fornecedor_ISSQN  = "99999900007407";

    OID_Conta_PIS_COFINS_CSLL = 359;
    OID_Conta_INSS = 358;
    OID_Conta_IRRF = 352;
    OID_Conta_ISSQN = 775;

    DM_Formulario_Ordem_Frete = "Ordem_Frete_Miro";
    DM_Formulario_Ordem_Frete_Adiantamento = "Ordem_Frete_Adiantamento_Miro";
    DM_Formulario_Manifesto = "Manifesto_Miro";
    DM_Formulario_Conhecimento_Nacional = "Conhecimento_Nacional_Miro";
    DM_Formulario_Acerto_Contas = "Acerto_de_Contas_Miro";
    DM_Formulario_Minuta = "Minuta_Padrao";
    DM_Formulario_Romaneio_Notal_Fiscal ="Romaneio_Nota_Fiscal_Padrao";
    DM_Formulario_Nota_Fiscal_Servico ="Nota_Fiscal_Servico_Miro";
    DM_Imprime_Conhecimento_Ordem_Frete = "Nacional";
    DM_Tipo_Conhecimento = "Nacional";
    DM_Gera_Compromisso_Motorista_Proprietario = "N";

    DM_Gera_Livro_Fiscal="S";

    DM_Gera_Compromisso_Ordem_Frete_Adiantamento = "N";
    DM_Gera_Compromisso_Ordem_Frete = "S";
    DM_Soma_Sest_Senat_Saldo_Frete = "S";
    DM_Gera_Compromisso_Nota_Fiscal_Compra = "S";

    DM_Limite_Credito_Motorista_Adiantamento_Frete = "S";
    DM_Subtrai_Pedagio_PIS_COFINS = "S";
    DM_Gera_Lancamento_Contabil = "S";
    DM_Gera_Lancamento_Contabil_Acerto = "N";
    DM_Conhecimento_Varios_Manifestos = "D";

    DM_Gera_Ocorrencia_Viagem = "N";
    DM_Gera_Compromisso_Viagem = "N";

    // PE_Base_Calculo = 0;
    DM_Calcula_INSS = "S";
    DM_Calcula_IRRF = "S";
    DM_Calcula_INSS_Para_Motorista = "S";

    DM_Numera_Ordem_Frete = "E";
    DM_Soma_Pedagio_Saldo_Frete = "N";
    DM_Soma_Coleta_Saldo_Frete = "N";
    DM_Soma_Carga_Saldo_Frete = "N";
    DM_Soma_Descarga_Saldo_Frete = "N";
    DM_Soma_Premio_Saldo_Frete = "N";
    DM_Soma_Descontos_Saldo_Frete = "N";
    DM_Soma_Outros_Saldo_Frete = "N";

    DM_Despacho = "N";

    DM_Gera_Lancamento_Conta_Corrente_Ordem_Frete_Adiantamento = "N";
    DM_Gera_Lancamento_Conta_Corrente_Ordem_Frete = "N";

    DM_Estado_Origem_Taxas = "U"; // pagador  // RP 31/03/08
    DM_Estado_Destino_Taxas = "D"; // pagador // RP 31/03/08

    DM_Atualiza_Odometro_Veiculo = "S";
    DM_Atualiza_Odometro_Acerto = "N";
    DM_Atualiza_Odometro_Ordem_Servico = "N";
    DM_Atualiza_Odometro_Pneus = "N";
    DM_Atualiza_Odometro_Portaria = "N";
    DM_Atualiza_Odometro_Abastecimento = "S";

    DM_Estado_Origem_CFOP = "R"; // R - REMETENTE U-Unidade
    DM_Estado_Destino_CFOP = "DESTINATARIO"; // DESTINATARIO
    OID_Conta_IR_Ordem_Frete = 1365;
    OID_Tipo_Documento_IR = 11;
    OID_Tipo_Ocorrencia_Desconto_CTRC = 5;
    OID_Tipo_Ocorrencia_Cancelamento_CTRC = 22;
    OID_Tipo_Instrucao_Pago_Total = 6; //
    OID_Tipo_Instrucao_Pago_Cartorio = 43; //
    OID_Tipo_Instrucao_Pago_Parcial = 20; //
    OID_Tipo_Instrucao_Tarifa = 18;
    OID_Tipo_Instrucao_Juros = 27; //
    OID_Tipo_Instrucao_Desconto =38;
    OID_Tipo_Instrucao_Taxa_Cobranca = 18;

    OID_Tipo_Instrucao_Estorno = 23;
    OID_Tipo_Instrucao_Valor_Reembolso = 4;
    OID_Tipo_Instrucao_Juros_Reembolso = 4;
    OID_Tipo_Instrucao_Variacao_Cambial_Ativa = 4;
    OID_Tipo_Instrucao_Variacao_Cambial_Passiva = 4;

    OID_Cliente_Complemento_Padrao = "87283164000151";
    OID_Pessoa_Padrao_Tabela_Frete = "87283164000151";
    OID_Tipo_Servico_Requisicao_Estoque = 83;

    OID_Unidade_Padrao = 1;
    OID_Historico_Compensacao = 9;
    OID_Historico_Estorno = 31;

    OID_Historico_Cancelamento_Lote_Pagamento = 9;

    OID_Tipo_Movimento_Custo_Desconto = 13;
    OID_Modal = 1;
    OID_Conta_Credito_Pedagio = 20;
    OID_Conta_Debito_Pedagio = 20;
    OID_Tipo_Documento_Master = 15;
    OID_Tipo_Documento_Transferencia_Conta_Corrente = 1;

    OID_Natureza_Operacao_Master = 1;
    OID_Conta_Credito_Master = 1;
    OID_Conta_Debito_Master = 1;
    OID_Centro_Custo_Master = 1;

    //novos
    OID_Tipo_Movimento_Custo_Coleta = 81;
    OID_Tipo_Movimento_Custo_Entrega = 82;
    OID_Tipo_Movimento_Custo_Reentrega = 83;
    OID_Tipo_Movimento_Custo_Master = 84;
    OID_Tipo_Movimento_Custo_Ordem_Frete = 85;
    OID_Tipo_Movimento_Custo_Transferencia = 85;
    OID_Tipo_Movimento_Custo_Coleta_Transferencia = 86;
    OID_Tipo_Movimento_Custo_Transferencia_Entrega = 87;
    OID_Tipo_Movimento_Custo_Previsto_Entrega = 82;
    OID_Tipo_Movimento_Custo_Coleta_Transferencia_Entrega = 85;
    OID_Tipo_Movimento_Custo_Desconto = 22;

    OID_Natureza_Operacao_Entrega = 45;
    OID_Tipo_Movimento_Custo_Entrega = 16;
    OID_Conta_Credito_Entrega = 59;
    OID_Conta_Debito_Entrega = 59;

    OID_Tipo_Documento_Entrega = 5;
    OID_Centro_Custo_Entrega = 3;
    OID_Tipo_Documento_Faturamento = 15;
    OID_Carteira_Faturamento = 2;

    OID_Unidade_Faturamento_Minuta = 1;
    OID_Unidade_Faturamento = 1;

    OID_Centro_Custo_Ordem_Frete = 5; //3;

    OID_Conta_Credito_Ordem_Frete =98; //59
    OID_Conta_Ordem_Frete = 98; //59
    OID_Tipo_Documento_Ordem_Frete = 3; //

    OID_Conta_Ordem_Frete_Adiantamento = 926; //134;
    OID_Conta_Juridica_Ordem_Frete_Adiantamento = 926;

    OID_Tipo_Documento_Ordem_Frete_Adiantamento = 61; //21;
    OID_Conta_Juridica_Ordem_Frete = 99; //9
    OID_Centro_Custo_Movimento_Ordem_Servico = 3;
    OID_Conta_Movimento_Ordem_Servico = 13;
    OID_Conta_Credito_Movimento_Ordem_Servico = 13;
    OID_Natureza_Operacao_Ordem_Frete = 45; //45
    OID_Tipo_Ocorrencia_Estorno_CTRC = 6;

    OID_Conta_Frete_Terceiros = 9;
    OID_Conta_Desconto_Frete = 109;
    OID_Conta_Despesa_Bancaria = 107;

    OID_Historico_Lancamento_Ordem_Frete = 1;
    OID_Historico_Lancamento_Ordem_Frete_Adiantamento = 1;

    OID_Tipo_Servico_Abastecimento = 9;
    OID_Tipo_Servico_Acerto_Contas = 3;

  }

public int getOID_Historico_Estorno() {
	return OID_Historico_Estorno;
}

public void setOID_Historico_Estorno(int historico_Estorno) {
	OID_Historico_Estorno = historico_Estorno;
}

public double getVL_Faixa3() {
	return VL_Faixa3;
}

public void setVL_Faixa3(double faixa3) {
	VL_Faixa3 = faixa3;
}

public double getVL_Faixa4() {
	return VL_Faixa4;
}

public void setVL_Faixa4(double faixa4) {
	VL_Faixa4 = faixa4;
}

public double getPE_Imposto_Faixa3() {
	return PE_Imposto_Faixa3;
}

public void setPE_Imposto_Faixa3(double imposto_Faixa3) {
	PE_Imposto_Faixa3 = imposto_Faixa3;
}

public double getPE_Imposto_Faixa4() {
	return PE_Imposto_Faixa4;
}

public void setPE_Imposto_Faixa4(double imposto_Faixa4) {
	PE_Imposto_Faixa4 = imposto_Faixa4;
}

public double getVL_Deducao_Faixa3() {
	return VL_Deducao_Faixa3;
}

public void setVL_Deducao_Faixa3(double deducao_Faixa3) {
	VL_Deducao_Faixa3 = deducao_Faixa3;
}

public double getVL_Deducao_Faixa4() {
	return VL_Deducao_Faixa4;
}

public void setVL_Deducao_Faixa4(double deducao_Faixa4) {
	VL_Deducao_Faixa4 = deducao_Faixa4;
}

public int getOID_Tipo_Servico_Requisicao_Estoque() {
	return OID_Tipo_Servico_Requisicao_Estoque;
}

public void setOID_Tipo_Servico_Requisicao_Estoque(
		int tipo_Servico_Requisicao_Estoque) {
	OID_Tipo_Servico_Requisicao_Estoque = tipo_Servico_Requisicao_Estoque;
}

public String getDM_Atualiza_Odometro_Abastecimento() {
	return DM_Atualiza_Odometro_Abastecimento;
}

public void setDM_Atualiza_Odometro_Abastecimento(
		String atualiza_Odometro_Abastecimento) {
	DM_Atualiza_Odometro_Abastecimento = atualiza_Odometro_Abastecimento;
}

public String getDM_Atualiza_Odometro_Acerto() {
	return DM_Atualiza_Odometro_Acerto;
}

public void setDM_Atualiza_Odometro_Acerto(String atualiza_Odometro_Acerto) {
	DM_Atualiza_Odometro_Acerto = atualiza_Odometro_Acerto;
}

public String getDM_Atualiza_Odometro_Ordem_Servico() {
	return DM_Atualiza_Odometro_Ordem_Servico;
}

public void setDM_Atualiza_Odometro_Ordem_Servico(
		String atualiza_Odometro_Ordem_Servico) {
	DM_Atualiza_Odometro_Ordem_Servico = atualiza_Odometro_Ordem_Servico;
}

public String getDM_Atualiza_Odometro_Pneus() {
	return DM_Atualiza_Odometro_Pneus;
}

public void setDM_Atualiza_Odometro_Pneus(String atualiza_Odometro_Pneus) {
	DM_Atualiza_Odometro_Pneus = atualiza_Odometro_Pneus;
}

public String getDM_Atualiza_Odometro_Portaria() {
	return DM_Atualiza_Odometro_Portaria;
}

public void setDM_Atualiza_Odometro_Portaria(String atualiza_Odometro_Portaria) {
	DM_Atualiza_Odometro_Portaria = atualiza_Odometro_Portaria;
}

public String getDM_Atualiza_Odometro_Rastreador() {
	return DM_Atualiza_Odometro_Rastreador;
}

public void setDM_Atualiza_Odometro_Rastreador(
		String atualiza_Odometro_Rastreador) {
	DM_Atualiza_Odometro_Rastreador = atualiza_Odometro_Rastreador;
}

public String getDM_Atualiza_Odometro_Veiculo() {
	return DM_Atualiza_Odometro_Veiculo;
}

public void setDM_Atualiza_Odometro_Veiculo(String atualiza_Odometro_Veiculo) {
	DM_Atualiza_Odometro_Veiculo = atualiza_Odometro_Veiculo;
}

public String getDoc_ePath() {
	return doc_ePath;
}

public void setDoc_ePath(String doc_ePath) {
	this.doc_ePath = doc_ePath;
}

public Empresa getEmpresaEmissao() {
	return empresaEmissao;
}

public void setEmpresaEmissao(Empresa empresaEmissao) {
	this.empresaEmissao = empresaEmissao;
}
}


