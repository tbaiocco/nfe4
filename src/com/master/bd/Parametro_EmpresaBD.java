package com.master.bd;

import java.sql.ResultSet;

import com.master.ed.Parametro_EmpresaED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;
 
/**
 * @author Vinícius e Jeanine
 * @serial Parametro Empresa
 * @serialData 02/04/2008
 */
public class Parametro_EmpresaBD extends BancoUtil {

  private ExecutaSQL executasql;

  public Parametro_EmpresaBD (ExecutaSQL sql) {
    super (sql);
    this.executasql = sql;
  }

//======================================== Altera Principal =========================================================================\\
  public void altera (Parametro_EmpresaED ed) throws Excecoes {
    String sql = null;
    try {
    	if (!doValida(this.getByRecord(ed).getDm_Tipo_Sistema())) {
      	  sql = "INSERT INTO parametros_empresas (dm_tipo_sistema ) VALUES ('XXX')";
      	  executasql.executarUpdate (sql);
        }
      sql = "UPDATE " +
          " Parametros_Empresas " +
          "SET " +
          " dm_tipo_sistema = '" + ed.getDm_Tipo_Sistema () + "', " +
          " dm_perfil_sistema = '" + ed.getDm_Perfil_Sistema () +"', " +
          " dm_operacao_sistema = '" + ed.getDm_Operacao_Sistema() + "', " +
          " dm_usaCTB = '" + ed.getDm_UsaCTB () + "', " +
          " nm_razao_social_empresa = '" + ed.getNm_Razao_Social_Empresa () + "', "+
          " dm_situacao = '" + ed.getDm_Situacao () + "', "+
          " oid_moeda_padrao = " + ed.getOid_Moeda_Padrao () + ", "+
          " oid_moeda_dollar = " + ed.getOid_Moeda_Dollar () + " ";
          
      executasql.executarUpdate (sql);
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
                          "altera(Parametro_EmpresaED ed)");
    }
  }
  
//======================================== Altera Ações Gerais =========================================================================\\
  public void alteraAcoesGerais (Parametro_EmpresaED ed) throws Excecoes {
    String sql = null;
    try {
    	if (!doValida(this.getByRecord(ed).getDm_Tipo_Sistema())) {
      	  sql = "INSERT INTO parametros_empresas (dm_tipo_sistema ) VALUES ('XXX')";
      	  executasql.executarUpdate (sql);
        }
      sql = "UPDATE " +
          " Parametros_Empresas " +
          "SET " +
          " dm_gera_embarque_viagem = '" + ed.getDm_Gera_Embarque_Viagem () + "', " +
          " dm_gera_rateio_custo_ordem_frete = '" + ed.getDm_Gera_Rateio_Custo_Ordem_Frete () + "', " +
          " dm_gera_ocorrencia_viagem = '" + ed.getDm_Gera_Ocorrencia_Viagem () + "', " +
          " dm_gera_custo_viagem = '" + ed.getDm_Gera_Custo_Viagem () + "', " +
          " dm_gera_compromisso_viagem = '" + ed.getDm_Gera_Compromisso_Viagem () + "', " +
          " dm_gera_compromisso_nota_fiscal_compra = '" + ed.getDm_Gera_Compromisso_Nota_Fiscal_Compra () + "', " +
          " dm_gera_compromisso_ordem_frete = '" + ed.getDm_Gera_Compromisso_Ordem_Frete () + "', " +
          " dm_gera_compromisso_ordem_frete_adiantamento = '" + ed.getDm_Gera_Compromisso_Ordem_Frete_Adiantamento () + "', " +
          " dm_gera_compromisso_ordem_abastecimento = '" + ed.getDm_Gera_Compromisso_Ordem_Abastecimento () + "', " +
          " dm_gera_compromisso_ir_ordem_frete = '" + ed.getDm_Gera_Compromisso_IR_Ordem_Frete () + "', " +
          " dm_gera_compromisso_inss_ordem_frete = '" + ed.getDm_Gera_Compromisso_INSS_Ordem_Frete () + "', " +
          " dm_gera_compromisso_master = '" + ed.getDm_Gera_Compromisso_Master () + "', " +
          " dm_gera_compromisso_movimento_ordem_servico = '" + ed.getDm_Gera_Compromisso_Movimento_Ordem_Servico () + "', " +
          " dm_gera_compromisso_motorista_proprietario = '" + ed.getDm_Gera_Compromisso_Motorista_Proprietario() + "', " +
          " dm_gera_lancamento_contabil = '" + ed.getDm_Gera_Lancamento_Contabil () + "', " +
          " dm_gera_lancamento_conta_corrente_ordem_frete_terceiro = '" + ed.getDm_Gera_Lancamento_Conta_Corrente_Ordem_Frete_Terceiro () + "', " +
          " dm_gera_lancamento_conta_corrente_ordem_frete_adiantamento = '" + ed.getDm_Gera_Lancamento_Conta_Corrente_Ordem_Frete_Adiantamento () + "', " +
          " dm_gera_lancamento_conta_corrente_ordem_frete = '" + ed.getDm_Gera_Lancamento_Conta_Corrente_Ordem_Frete () + "', " +
          " dm_gera_lancamento_conta_corrente_acerto_contas = '" + ed.getDm_Gera_Lancamento_Conta_Corrente_Acerto_Contas () + "', " +
          " dm_gera_livro_fiscal = '" + ed.getDm_Gera_Livro_Fiscal () + "', " + 
      	  " dm_atualiza_movimento_caixa = '" + ed.getDm_Atualiza_Movimento_Caixa () + "', " +
      	  " dm_liquida_compromisso = '" + ed.getDm_Liquida_Compromisso () + "', " +
      	  " dm_conhecimento_varios_manifestos = '" + ed.getDm_Conhecimento_Varios_Manifestos () + "', " +
      	  " dm_tranca_saldo_ordem_frete = '" + ed.getDm_Tranca_Saldo_Ordem_Frete () + "' ";
      	  
      executasql.executarUpdate (sql);
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
                          "alteraAcoesGerais(Parametro_EmpresaED ed)");
    }
  }
 
//======================================== Altera Aliquota ========================================================================\\  
  public void alteraAliquota (Parametro_EmpresaED ed) throws Excecoes {
	    String sql = null;
	    try {
	    	sql = "UPDATE " +
	          " Parametros_Empresas " +
	          "SET " +
	          " pe_aliquota_PIS_COFINS = " + ed.getPe_Aliquota_PIS_COFINS() +", " +
	          " pe_imposto_faixa1 = " + ed.getPe_Imposto_Faixa1() +", " +
	          " pe_imposto_faixa2 = " + ed.getPe_Imposto_Faixa2() +", " +
	          " pe_base_calculo = " + ed.getPe_Base_Calculo() +", " +
	          " pe_aliquota_empresa_INSS = " + ed.getPe_Aliquota_Empresa_INSS() +", " +
	          " pe_aliquota_prestador_INSS = " + ed.getPe_Aliquota_Prestador_INSS() +", " +
	          " pe_base_INSS = " + ed.getPe_Base_INSS() +", " +
	          " pe_base_SET_SENAT = " + ed.getPe_Base_SET_SENAT() +", " +
	          " pe_aliquota_prestador_Set_Senat = " + ed.getPe_Aliquota_Prestador_Set_Senat() +", " +
	          " pe_aliquota_COFINS = " + ed.getPe_Aliquota_COFINS() +", " +
	          " pe_aliquota_PIS = " + ed.getPe_Aliquota_PIS() +", " +
	          " pe_aliquota_CSLL = " + ed.getPe_Aliquota_CSLL() +", " +
	          " pe_comissao_motorista = " + ed.getPe_Comissao_Motorista() +", " +
	          " pe_base_comissao_motorista = " + ed.getPe_Base_Comissao_Motorista() +", " +
	          " pe_comissao_motorista_media = " + ed.getPe_Comissao_Motorista_Media() +", " +
	          " vl_faixa1 = " + ed.getVl_Faixa1() +", " +
	          " vl_faixa2 = " + ed.getVl_Faixa2() +", " +
	          " vl_deducao_faixa1 = " + ed.getVl_Deducao_Faixa1() +", " +
	          " vl_deducao_faixa2 = " + ed.getVl_Deducao_Faixa2() +", " +
	          " vl_dependente = " + ed.getVl_Dependente() +", " +
	          " vl_maximo_INSS = " + ed.getVl_Maximo_INSS() +", " +
	          " vl_frete_cortesia = " + ed.getVl_Frete_Cortesia() +", " +
	          " vl_reembolso = " + ed.getVl_Reembolso() +" ";
	          
	      executasql.executarUpdate (sql);
	    }
	    catch (Exception exc) {
	      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
	                          "alteraAliquota (Parametro_EmpresaED ed)");
	    }
	  }
  

//======================================== Altera Base Dados =========================================================================\\
public void alteraBaseDados (Parametro_EmpresaED ed) throws Excecoes {
	    String sql = null;
	    try {
	      sql = "UPDATE " +
	          " Parametros_Empresas " +
	          "SET " +
	          " path_relatorios = '" + ed.getPath_Relatorios () + "', " +
	          " path_relatorios_xsl = '" + ed.getPath_Relatorios_XSL () + "', " +
	          " path_imagens = '" + ed.getPath_imagens () + "', " +
	          " serverName = '" + ed.getServerName () + "', " +
	          " dt_hoje = '" + ed.getDt_Hoje () + "', " +
	          " hr_hoje = '" + ed.getHr_Hoje () + "', " +
	          " nm_versao = '" + ed.getNm_Versao () + "', " +
	          " document_form_action = '" + ed.getDocument_Form_Action () + "', " +
	          " appPath = '" + ed.getAppPath () + "', " +
	          " palmPath = '" + ed.getPalmPath () + "', " +
	          " palmModelo = '" + ed.getPalmModelo () + "', " +
	          " logTransactions = '" + ed.getLogTransactions() + "', " +
	          " port = " + ed.getPort() + ", " +
	          " contexto = '" + ed.getContexto() + "', " +
	          " webmaster = '" + ed.getWebmaster() + "' ";
	          
	      executasql.executarUpdate (sql);
	    }
	    catch (Exception exc) {
	      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
	                          "alteraBaseDados(Parametro_EmpresaED ed)");
	    }
	  }
	  
//======================================== Altera Calculos =========================================================================\\   
  public void alteraCalculos (Parametro_EmpresaED ed) throws Excecoes {
	    String sql = null;
	    try {
	      sql = "UPDATE " +
	          " Parametros_Empresas " +
	          "SET " +
	          " dm_calcula_inss = '" + ed.getDm_Calcula_INSS () + "', " +
	          " dm_calcula_irrf = '" + ed.getDm_Calcula_IRRF () + "', " +
	          " dm_calcula_pis_cofins = '" + ed.getDm_Calcula_PIS_COFINS () + "', " +
	          " dm_tipo_calculo_icms = '" + ed.getDm_Tipo_Calculo_ICMS () + "', " +
	          " dm_soma_pedagio_saldo_frete = '" + ed.getDm_Soma_Pedagio_Saldo_Frete () + "', " +
	          " dm_soma_sest_senat_saldo_frete = '" + ed.getDm_Soma_Sest_Senat_Saldo_Frete () + "', " +
	          " dm_soma_outros_saldo_frete = '" + ed.getDm_Soma_Outros_Saldo_Frete () + "' ";
	          
	      executasql.executarUpdate (sql);
	    }
	    catch (Exception exc) {
	      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
	                          "alteraCalculo(Parametro_EmpresaED ed)");
	    }
	  }

//======================================== Altera Carteira ==================================================================\\
  public void alteraCarteira (Parametro_EmpresaED ed) throws Excecoes {
	    String sql = null;
	    try {
	      sql = "UPDATE " +
	          " Parametros_Empresas " +
	          "SET " +
	          " oid_Carteira_Nota_Retorno = " + ed.getOid_Carteira_Nota_Retorno () + ", " +
	          " oid_Carteira_Faturamento = " + ed.getOid_Carteira_Faturamento () + " ";

	      executasql.executarUpdate (sql);
	    }
	    catch (Exception exc) {
	      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
	                          "alteraCarteira(Parametro_EmpresaED ed)");
	    }
	  }  
 
//======================================== Altera Centro Custo ========================================================================\\
  public void alteraCentroCusto (Parametro_EmpresaED ed) throws Excecoes {
	    String sql = null;
	    try {
	    	if (!doValida(this.getByRecord(ed).getDm_Tipo_Sistema())) {
	        	  sql = "INSERT INTO parametros_empresas (dm_tipo_sistema ) VALUES ('XXX')";
	        	  executasql.executarUpdate (sql);
	    	}
	      sql = "UPDATE " +
	          " Parametros_Empresas " +
	          "SET " +
	          " oid_Centro_Custo_Ordem_Frete = " + ed.getOid_Centro_Custo_Ordem_Frete () + ", " +
	          " oid_Centro_Custo_Movimento_Ordem_Servico = " + ed.getOid_Centro_Custo_Movimento_Ordem_Servico () + ", " +
	          " oid_Centro_Custo_Master = " + ed.getOid_Centro_Custo_Master () + ", " +
	          " oid_Centro_Custo_Entrega = " + ed.getOid_Centro_Custo_Entrega () + " ";

	      executasql.executarUpdate (sql);
	    }
	    catch (Exception exc) {
	      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
	                          "alteraCentroCusto(Parametro_EmpresaED ed)");
	    }
	  }

//======================================== Altera CFOP/TAXAS ====================================================\\
public void alteraCfopTaxa (Parametro_EmpresaED ed) throws Excecoes {
	    String sql = null;
	    try {
	      sql = "UPDATE " +
	          " Parametros_Empresas " +
	          "SET " +
	          " CFOP_Padrao = '" + ed.getCFOP_Padrao () + "', " +
	          " oid_CFOP_Pessoa_Fisica = " + ed.getOid_CFOP_Pessoa_Fisica () + ", " +
	          " oid_CFOP_Diferenciado = " + ed.getOid_CFOP_Diferenciado () + ", " +
	          " dm_Estado_Origem_CFOP = '" + ed.getDm_Estado_Origem_CFOP () + "', " +
	          " dm_Estado_Destino_CFOP = '" + ed.getDm_Estado_Destino_CFOP () + "', " +
	          " dm_Estado_Origem_Taxas = '" + ed.getDm_Estado_Origem_Taxas () + "', " +
	      	  " dm_Estado_Destino_Taxas = '" + ed.getDm_Estado_Destino_Taxas () + "' ";
	          
	      executasql.executarUpdate (sql);
	    }
	    catch (Exception exc) {
	      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
	                          "alteraCfopTaxa(Parametro_EmpresaED ed)");
	    }
	  }
  

//======================================== Altera Conta =============================================================================\\
  public void alteraConta (Parametro_EmpresaED ed) throws Excecoes {
	    String sql = null;
	    try {
	      sql = "UPDATE " +
	          " Parametros_Empresas " +
	          "SET " +
	          " oid_conta_credito_master = " + ed.getOid_Conta_Credito_Master() + ", " +
	          " oid_conta_debito_master = " + ed.getOid_Conta_Debito_Master() +", " +
	          " oid_conta_credito_pedagio = " + ed.getOid_Conta_Credito_Pedagio() +", " +
	          " oid_conta_debito_pedagio = " + ed.getOid_Conta_Debito_Pedagio() +", " +
	          " oid_conta_credito_entrega = " + ed.getOid_Conta_Credito_Entrega() +", " +
	          " oid_conta_debito_entrega = " + ed.getOid_Conta_Debito_Entrega() +", " +
	          " oid_conta_credito_ordem_frete_adiantamento = " + ed.getOid_Conta_Credito_Ordem_Frete_Adiantamento() +", " +
	          " oid_conta_ordem_frete_adiantamento = " + ed.getOid_Conta_Ordem_Frete_Adiantamento() +", " +
	          " oid_conta_frete_terceiros = " + ed.getOid_Conta_Frete_Terceiros() +", " +
	          " oid_conta_desconto_frete = " + ed.getOid_Conta_Desconto_Frete() +", " +
	          " oid_conta_despesa_bancaria = " + ed.getOid_Conta_Despesa_Bancaria() +", " +
	          " oid_conta_acerto_contas = " + ed.getOid_Conta_Acerto_Contas() +", " +
	          " oid_conta_ordem_frete = " + ed.getOid_Conta_Ordem_Frete() +", " +
	          " oid_Conta_ir_ordem_frete = " + ed.getOid_Conta_IR_Ordem_Frete() +", " +
	          " oid_conta_inss_ordem_frete = " + ed.getOid_Conta_INSS_Ordem_Frete() +", " +
	          " oid_conta_movimento_compromisso = " + ed.getOid_Conta_Movimento_Compromisso() +", " +
	          " oid_conta_movimento_ordem_servico = " + ed.getOid_Conta_Movimento_Ordem_Servico() +", " +
	          " oid_conta_credito_movimento_ordem_servico = " + ed.getOid_Conta_Credito_Movimento_Ordem_Servico() +", " +
	          " oid_conta_credito_ordem_frete = " + ed.getOid_Conta_Credito_Ordem_Frete() +", " +
	          " oid_conta_juridica_ordem_frete = " + ed.getOid_Conta_Juridica_Ordem_Frete() +", " +
	          " oid_conta_multa_transito = " + ed.getOid_Conta_Multa_Transito() +" ";
	      
	      executasql.executarUpdate (sql);
	    }
	    catch (Exception exc) {
	      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
	                          "alteraConta(Parametro_EmpresaED ed)");
	    }
	  }
  
//======================================== Altera Descrição ========================================================================\\
  public void alteraDescricao (Parametro_EmpresaED ed) throws Excecoes {
	    String sql = null;
	    try {
	      sql = "UPDATE " +
	          " Parametros_Empresas " +
	          "SET " +
	          " dm_saldo_programado = '" + ed.getDm_Saldo_Programado() + "', " +
	          " texto_imprimir_campo_nao_validado = '" + ed.getTexto_Imprimir_Campo_Nao_Validado() +"', " +
	          " dm_multi_moeda = '" + ed.getDm_Multi_Moeda() +"', " +
	          " vias_fatura = " + ed.getVias_Fatura() +", " +
	          " oid_condicao_pagamento_isento = " + ed.getOid_Condicao_Pagamento_Isento() +" ";
	
	      
	      executasql.executarUpdate (sql);
	    }
	    catch (Exception exc) {
	      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
	                          "alteraDescricao(Parametro_EmpresaED ed)");
	    }
	  }
    
//======================================== Altera Documento ========================================================================\\  
  public void alteraDocumento (Parametro_EmpresaED ed) throws Excecoes {
	    String sql = null;
	    try {
	      sql = "UPDATE " +
	          " Parametros_Empresas " +
	          "SET " +
	          " oid_tipo_documento_faturamento = " + ed.getOid_Tipo_Documento_Faturamento() +", " +
	          " oid_tipo_documento_ordem_servico = " + ed.getOid_Tipo_Documento_Ordem_Servico() +", " +
	          " oid_tipo_documento_faturamento_internacional = " + ed.getOid_Tipo_Documento_Faturamento_Internacional() +", " +
	          " oid_tipo_documento_master = " + ed.getOid_Tipo_Documento_Master() +", " +
	          " oid_tipo_documento_IR = " + ed.getOid_Tipo_Documento_IR() +", " +
	          " oid_tipo_documento_INSS = " + ed.getOid_Tipo_Documento_INSS() +", " +
	          " oid_tipo_documento_ordem_frete = " + ed.getOid_Tipo_Documento_Ordem_Frete() +", " +
	          " oid_tipo_documento_transferencia_conta_corrente = " + ed.getOid_Tipo_Documento_Transferencia_Conta_Corrente() +", " +
	          " oid_tipo_documento_entrega = " + ed.getOid_Tipo_Documento_Entrega() +", " +
	          " oid_tipo_documento_ordem_frete_adiantamento = " + ed.getOid_Tipo_Documento_Ordem_Frete_Adiantamento() +", " +
	          " oid_tipo_documento_nota_fiscal_entrada = " + ed.getOid_Tipo_Documento_Nota_Fiscal_Entrada() +", " +
	          " oid_tipo_documento_nota_fiscal_saida = " + ed.getOid_Tipo_Documento_Nota_Fiscal_Saida() +", " +
	          " oid_tipo_documento_faturamento_nota_fiscal_servico = " + ed.getOid_Tipo_Documento_Faturamento_Nota_Fiscal_Servico() +" ";
	          
	      executasql.executarUpdate (sql);
	    }
	    catch (Exception exc) {
	      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
	                          "alteraDocumento(Parametro_EmpresaED ed)");
	    }
	  }

//======================================== Altera Faturamento ==============================================================\\  
  public void alteraFaturamento (Parametro_EmpresaED ed) throws Excecoes {
	    String sql = null;
	    try {
	      sql = "UPDATE " +
	          " Parametros_Empresas " +
	          "SET " +
	          " dm_quebra_faturamento_tipo_conhecimento = '" + ed.getDm_Quebra_Faturamento_Tipo_Conhecimento() + "', " +
	          " dm_quebra_faturamento_unidade = '" + ed.getDm_Quebra_Faturamento_Unidade() + "', " +
	          " dm_quebra_faturamento_tipo_faturamento = '" + ed.getDm_Quebra_Faturamento_Tipo_Faturamento() +"' ";
	          
	      executasql.executarUpdate (sql);
	    }
	    catch (Exception exc) {
	      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
	                          "alteraFaturamento(Parametro_EmpresaED ed)");
	    }
	  } 
  
  
//======================================== Altera Formulario =======================================================================\\
  public void alteraFormulario (Parametro_EmpresaED ed) throws Excecoes {
	    String sql = null;
	    try {
	      sql = "UPDATE " +
	          " Parametros_Empresas " +
	          "SET " +
	          " dm_formulario_consolidacao_MIC = '" + ed.getDm_Formulario_Consolidacao_MIC () + "', " +
	          " dm_formulario_consolidacao_MIC_CRT = '" + ed.getDm_Formulario_Consolidacao_MIC_CRT () + "', " +
	          " dm_formulario_duplicata_proform = '" + ed.getDm_Formulario_Duplicata_Proform () + "', " +
	          " dm_formulario_duplicata = '" + ed.getDm_Formulario_Duplicata () + "', " +
	          " dm_formulario_demonstrativo_Cobranca = '" + ed.getDm_Formulario_Demonstrativo_Cobranca () + "', " +
	          " dm_formulario_protocolo_cobranca = '" + ed.getDm_Formulario_Protocolo_Cobranca () + "', " +
	          " dm_formulario_duplicata_internacional = '" + ed.getDm_Formulario_Duplicata_Internacional () + "', " +
	          " dm_formulario_minuta = '" + ed.getDm_Formulario_Minuta() +"', " +
	          " dm_formulario_ACT = '" + ed.getDm_Formulario_ACT () + "', " +
	          " dm_formulario_conhecimento_nacional = '" + ed.getDm_Formulario_Conhecimento_Nacional () + "', " +
	          " dm_formulario_conhecimento_internacional = '" + ed.getDm_Formulario_Conhecimento_Internacional () + "', " +
	          " dm_formulario_conhecimento_internacional_verso = '" + ed.getDm_Formulario_Conhecimento_Internacional_Verso () + "', " +
	          " dm_formulario_coleta = '" + ed.getDm_Formulario_Coleta () + "', " +
	          " dm_formulario_ordem_frete = '" + ed.getDm_Formulario_Ordem_Frete () +"', " +
	          " dm_formulario_ordem_frete_adiantamento = '" + ed.getDm_Formulario_Ordem_Frete_Adiantamento() + "', " +
	          " dm_formulario_ordem_abastecimento = '" + ed.getDm_Formulario_Ordem_Abastecimento () + "', " +
	          " dm_formulario_nota_fiscal = '" + ed.getDm_Formulario_Nota_Fiscal () + "', " +
	          " dm_formulario_ordem_servico = '" + ed.getDm_Formulario_Ordem_Servico () + "', " +
	          " dm_formulario_manifesto = '" + ed.getDm_Formulario_Manifesto () + "', " +
	          " dm_formulario_manifesto_redespacho = '" + ed.getDm_Formulario_Manifesto_Redespacho () + "', " +
	          " dm_formulario_romaneio_nota_fiscal = '" + ed.getDm_Formulario_Romaneio_Nota_Fiscal () + "', " +
	          " dm_formulario_romaneio_itens_nota_fiscal = '" + ed.getDm_Formulario_Romaneio_Itens_Nota_Fiscal () + "', " +
	          " dm_formulario_acerto_contas = '" + ed.getDm_Formulario_Acerto_Contas () + "', " +
	          " dm_formulario_romaneio_entrega = '" + ed.getDm_Formulario_Romaneio_Entrega () + "', " +
	          " dm_formulario_awb = '" + ed.getDm_Formulario_AWB () + "', " +
	          " dm_formulario_nota_fiscal_servico = '" + ed.getDm_Formulario_Nota_Fiscal_Servico () + "', "+
	          " nr_vias_ordem_frete_adiantamento = " + ed.getNr_Vias_Ordem_Frete_Adiantamento() + ", "+
	          " nr_vias_Ordem_frete = " + ed.getNr_Vias_Ordem_Frete() + " ";
	          
	      executasql.executarUpdate (sql);
	    }
	    catch (Exception exc) {
	      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
	                          "alteraFormulario(Parametro_EmpresaED ed)");
	    }
	  }
  
//======================================== Altera Frota =========================================================================\\
public void alteraFrota (Parametro_EmpresaED ed) throws Excecoes {
	    String sql = null;
	    try {
	      sql = "UPDATE " +
	          " Parametros_Empresas " +
	          "SET " +
	          " dm_base_comissao_motorista = '" + ed.getDm_Base_Comissao_Motorista () + "', " +
	          " dm_limite_credito_motorista_adiantamento_frete = '" + ed.getDm_Limite_Credito_Motorista_Adiantamento_Frete () + "', " +
	          " dm_exige_validade_lib_seguradora_motorista = '" + ed.getDm_Exige_Validade_Lib_Seguradora_Motorista () + "', " +
	          " movimento_ordem_servico_duplicada = '" + ed.getMovimento_Ordem_Servico_Duplicada () + "' ";
	          
	      executasql.executarUpdate (sql);
	    }
	    catch (Exception exc) {
	      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
	                          "alteraBaseDados(Parametro_EmpresaED ed)");
	    }
	  }

//======================================== Altera Historico =========================================================================\\
  public void alteraHistorico (Parametro_EmpresaED ed) throws Excecoes {
	    String sql = null;
	    try {
	      sql = "UPDATE " +
	          " Parametros_Empresas " +
	          "SET " +
	          " oid_historico_transferencia_banco = " + ed.getOid_Historico_Transferencia_Banco() + ", " +
	          " oid_historico_transferencia_caixa = " + ed.getOid_Historico_Transferencia_Caixa() + ", " +
	          " oid_historico_compensacao = " + ed.getOid_Historico_Compensacao() + ", " +
	          " oid_historico_devolucao_cheque = " + ed.getOid_Historico_Devolucao_Cheque() + ", " +
	          " oid_historico_cancelamento_lote_pagamento = " + ed.getOid_Historico_Cancelamento_Lote_Pagamento() +", " +
	          " oid_historico_cancelamento_ordem_frete = " + ed.getOid_Historico_Cancelamento_Ordem_Frete() + ", " +
	          " oid_historico_lancamento_ordem_frete_terceiro = " + ed.getOid_Historico_Lancamento_Ordem_Frete_Terceiro() + ", " +
	          " oid_historico_lancamento_ordem_frete = " + ed.getOid_Historico_Lancamento_Ordem_Frete() + ", " +
	          " oid_historico_lancamento_ordem_frete_adiantamento = " + ed.getOid_Historico_Lancamento_Ordem_Frete_Adiantamento() + ", " +
	          " oid_historico_liquidacao_cobranca = " + ed.getOid_Historico_Liquidacao_Cobranca() + ", " +
	          " oid_historico_liquidacao_cobranca_retorno = " + ed.getOid_Historico_Liquidacao_Cobranca_Retorno() + ", " +
	          " oid_historico_bordero = " + ed.getOid_Historico_Bordero() + " ";
	          
	      executasql.executarUpdate (sql);
	    }
	    catch (Exception exc) {
	      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
	                          "alteraHistorico(Parametro_EmpresaED ed)");
	    }
	  }
  
//======================================== Altera Impressao =========================================================================\\
public void alteraImpressao (Parametro_EmpresaED ed) throws Excecoes {
	    String sql = null;
	    try {
	      sql = "UPDATE " +
	          " Parametros_Empresas " +
	          "SET " +
	          " dm_Tipo_Impressao_Fatura = '" + ed.getDm_Tipo_Impressao_Fatura () + "', " +
	          " dm_Tipo_Impressao_Conhecimento_Nacional = '" + ed.getDm_Tipo_Impressao_Conhecimento_Nacional () + "', " +
	          " dm_Tipo_Impressao_Minuta = '" + ed.getDm_Tipo_Impressao_Minuta () + "', " +
	          " dm_Tipo_Impressao_Nota_Fiscal_Servico = '" + ed.getDm_Tipo_Impressao_Nota_Fiscal_Servico () + "', " +
	          " dm_Tipo_Impressao_Coleta = '" + ed.getDm_Tipo_Impressao_Coleta () + "' ";
	          
	      executasql.executarUpdate (sql);
	    }
	    catch (Exception exc) {
	      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
	                          "alteraImpressao(Parametro_EmpresaED ed)");
	    }
	  }
  
//======================================== Altera Instrucoes ========================================================================\\  
  public void alteraInstrucoes (Parametro_EmpresaED ed) throws Excecoes {
	    String sql = null;
	    try {
	      sql = "UPDATE " +
	          " Parametros_Empresas " +
	          "SET " +
	          " oid_tipo_instrucao_pago_total = " + ed.getOid_Tipo_Instrucao_Pago_Total() + ", " +
	          " oid_tipo_instrucao_pago_cartorio = " + ed.getOid_Tipo_Instrucao_Pago_Cartorio() +", " +
	          " oid_tipo_instrucao_pago_parcial = " + ed.getOid_Tipo_Instrucao_Pago_Parcial() + ", " +
	          " oid_tipo_instrucao_tarifa = " + ed.getOid_Tipo_Instrucao_Tarifa() + ", " +
	          " oid_tipo_instrucao_juros = " + ed.getOid_Tipo_Instrucao_Juros() + ", " +
	          " oid_tipo_instrucao_desconto = " + ed.getOid_Tipo_Instrucao_Desconto() + ", " +
	          " oid_tipo_instrucao_taxa_cobranca = " + ed.getOid_Tipo_Instrucao_Taxa_Cobranca() +", " +
	          " oid_tipo_instrucao_estorno = " + ed.getOid_Tipo_Instrucao_Estorno() +", " +
	          " oid_tipo_instrucao_valor_reembolso = " + ed.getOid_Tipo_Instrucao_Valor_Reembolso() +", " +
	          " oid_tipo_instrucao_juros_reembolso = " + ed.getOid_Tipo_Instrucao_Juros_Reembolso() +", " +
	          " oid_tipo_instrucao_variacao_cambial_ativa = " + ed.getOid_Tipo_Instrucao_Variacao_Cambial_Ativa() +", " +
	          " oid_tipo_instrucao_variacao_cambial_passiva = " + ed.getOid_Tipo_Instrucao_Variacao_Cambial_Passiva() +", " +
	          " oid_tipo_instrucao_imposto_retido1 = " + ed.getOid_Tipo_Instrucao_Imposto_Retido1() + ", " +
	          " oid_tipo_instrucao_imposto_retido2 = " + ed.getOid_Tipo_Instrucao_Imposto_Retido2() + ", " +
	          " oid_tipo_instrucao_devolucao_nota_fiscal = " + ed.getOid_Tipo_Instrucao_Devolucao_Nota_Fiscal() + ", " +
	          " oid_tipo_instrucao_remessa = " + ed.getOid_Tipo_Instrucao_Remessa() + " ";
	          
	          
	      executasql.executarUpdate (sql);
	    }
	    catch (Exception exc) {
	      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
	                          "alteraInstrucoes(Parametro_EmpresaED ed)");
	    }
	  }

//======================================== Altera Internacional =========================================================================\\
public void alteraTransporteInternacional (Parametro_EmpresaED ed) throws Excecoes {
	    String sql = null;
	    try {
	      sql = "UPDATE " +
	          " Parametros_Empresas " +
	          "SET " +
			  " oid_tipo_faturamento_exportador = " + ed.getOid_Tipo_Faturamento_Exportador () + ", " +
	          " oid_tipo_faturamento_importador = " + ed.getOid_Tipo_Faturamento_Importador () + ", " +
	          " oid_tipo_faturamento_consolidacao_MIC_CRT = " + ed.getOid_Tipo_Faturamento_Consolidacao_MIC_CRT () + ", " +
	          " oid_tipo_faturamento_real = " + ed.getOid_Tipo_Faturamento_Real () + " " +
	          " tx_via_primeiro_original_pt = '" + ed.getTx_Via_Primeiro_Original_Pt () + "', " +
	          " tx_via_segundo_original_pt = '" + ed.getTx_Via_Segundo_Original_Pt () + "', " +
	          " tx_via_terceiro_original_pt = '" + ed.getTx_Via_Terceiro_Original_Pt () + "', " +
	          " tx_via_primeiro_original_es = '" + ed.getTx_Via_Primeiro_Original_Es () + "', " +
	          " tx_via_segundo_original_es = '" + ed.getTx_Via_Segundo_Original_Es () + "', " +
	          " tx_via_terceiro_original_es = '" + ed.getTx_Via_Terceiro_Original_Es () + "', " +
	          " dm_impressao_tramo = '" + ed.getDm_Impressao_Tramo () + "', " +
	          " nm_sucessivos = '" + ed.getNm_Sucessivos () + "', " +
	          " nm_ano_permisso = '" + ed.getNm_Ano_Permisso () + "', " +
	          " completa_tx_via = '" + ed.getCompleta_TX_Via () + "' ";
	          
	          
	      executasql.executarUpdate (sql);
	    }
	    catch (Exception exc) {
	      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
	                          "alteraTransporteInternacional(Parametro_EmpresaED ed)");
	    }
	  }

//======================================== Altera Localização ==================================================================\\
  public void alteraLocalizacao (Parametro_EmpresaED ed) throws Excecoes {
	    String sql = null;
	    try {
	      sql = "UPDATE " +
	          " Parametros_Empresas " +
	          "SET " +
	          " oid_Localizacao = " + ed.getOid_Localizacao () + ", " +
	          " oid_Localizacao_Devolucao = " + ed.getOid_Localizacao_Devolucao () + ", " +
	          " oid_Localizacao_Troca = " + ed.getOid_Localizacao_Troca () + " ";

	      executasql.executarUpdate (sql);
	    }
	    catch (Exception exc) {
	      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
	                          "alteraLocalizacao(Parametro_EmpresaED ed)");
	    }
	  }

//======================================== Altera Modal ========================================================================\\
  public void alteraModal (Parametro_EmpresaED ed) throws Excecoes {
	    String sql = null;
	    try {
	      sql = "UPDATE " +
	          " Parametros_Empresas " +
	          "SET " +
	          " oid_modal = " + ed.getOid_Modal () + ", " +
	          " oid_modal_aereo_standard = " + ed.getOid_Modal_Aereo_Standard () + ", " +
	          " oid_modal_aereo_expressa = " + ed.getOid_Modal_Aereo_Expressa () + ", " +
	          " oid_modal_aereo_emergencial = " + ed.getOid_Modal_Aereo_Emergencial () + ", " +
	          " oid_modal_aereo_sedex = " + ed.getOid_Modal_Aereo_Sedex () + ", " +
	          " oid_modal_aereo_pac = " + ed.getOid_Modal_Aereo_Pac () + ", " +
	          " oid_modal_aereo_rodExp = " + ed.getOid_Modal_Aereo_RodExp () + " ";

	      executasql.executarUpdate (sql);
	    }
	    catch (Exception exc) {
	      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
	                          "alteraModal(Parametro_EmpresaED ed)");
	    }
	  }
  
//======================================== Altera Modelo ==================================================================\\
  public void alteraModelo (Parametro_EmpresaED ed) throws Excecoes {
	    String sql = null;
	    try {
	      sql = "UPDATE " +
	          " Parametros_Empresas " +
	          "SET " +
	          " oid_Modelo_Nota_Fiscal = " + ed.getOid_Modelo_Nota_Fiscal () + ", " +
	          " oid_Modelo_NF_DevFornecedor_Dentro_Estado = " + ed.getOid_Modelo_NF_DevFornecedor_Dentro_Estado () + ", " +
	          " oid_Modelo_NF_DevFornecedor_Fora_Estado = " + ed.getOid_Modelo_NF_DevFornecedor_Fora_Estado () + " ";

	      executasql.executarUpdate (sql);
	    }
	    catch (Exception exc) {
	      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
	                          "alteraModelo(Parametro_EmpresaED ed)");
	    }
	  }
  
//======================================== Altera Modelo Tabela =======================================================\\
  public void alteraModeloTabela (Parametro_EmpresaED ed) throws Excecoes {
	    String sql = null;
	    try {
	      sql = "UPDATE " +
	          " Parametros_Empresas " +
	          "SET " +
	          " nm_Modelo_Tabela_Rodoviario = '" + ed.getNm_Modelo_Tabela_Rodoviario () + "', " +
	          " nm_Modelo_Tabela_Aereo = '" + ed.getNm_Modelo_Tabela_Aereo () + "', " +
	          " nm_Modelo_Tabela_Documento = '" + ed.getNm_Modelo_Tabela_Documento () + "' ";

	      executasql.executarUpdate (sql);
	    }
	    catch (Exception exc) {
	      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
	                          "alteraModeloTabela(Parametro_EmpresaED ed)");
	    }
	  }  

//======================================== Altera Movimento ========================================================================\\  
  public void alteraMovimento (Parametro_EmpresaED ed) throws Excecoes {
	    String sql = null;
	    try {
	      sql = "UPDATE " +
	          " Parametros_Empresas " +
	          "SET " +
	          " oid_tipo_movimento_custo_entrega = " + ed.getOid_Tipo_Movimento_Custo_Entrega() +", " +
	          " oid_tipo_movimento_custo_previsto_entrega = " + ed.getOid_Tipo_Movimento_Custo_Previsto_Entrega() +", " +
	          " oid_tipo_movimento_custo_master = " + ed.getOid_Tipo_Movimento_Custo_Master() +", " +
	          " oid_tipo_movimento_custo_ordem_frete = " + ed.getOid_Tipo_Movimento_Custo_Ordem_Frete() +", " +
	          " oid_tipo_movimento_ressarcimento = " + ed.getOid_Tipo_Movimento_Ressarcimento() +", " +
	          " oid_tipo_movimento_custo_coleta = " + ed.getOid_Tipo_Movimento_Custo_Coleta() +", " +
	          " oid_tipo_movimento_custo_transferencia = " + ed.getOid_Tipo_Movimento_Custo_Transferencia() +", " +
	          " oid_tipo_movimento_custo_coleta_transferencia = " + ed.getOid_Tipo_Movimento_Custo_Coleta_Transferencia() +", " +
	          " oid_tipo_movimento_custo_transferencia_entrega = " + ed.getOid_Tipo_Movimento_Custo_Transferencia_Entrega() +", " +
	          " oid_tipo_movimento_custo_coleta_transferencia_entrega = " + ed.getOid_Tipo_Movimento_Custo_Coleta_Transferencia_Entrega() +", " +
	          " oid_tipo_movimento_custo_desconto = " + ed.getOid_Tipo_Movimento_Custo_Desconto() +", " +
	          " oid_tipo_movimento_custo_devolucao = " + ed.getOid_Tipo_Movimento_Custo_Devolucao() +", " +
	          " oid_tipo_movimento_custo_reentrega = " + ed.getOid_Tipo_Movimento_Custo_Reentrega() +", " +
	          " oid_tipo_movimento_produto = " + ed.getOid_Tipo_Movimento_Produto() +", " +
	          " oid_tipo_movimento_transf = " + ed.getOid_Tipo_Movimento_Transf() +", " +
	          " oid_tipo_movimento_ajuste_e = " + ed.getOid_Tipo_Movimento_Ajuste_E() +", " +
	          " oid_tipo_movimento_ajuste_s = " + ed.getOid_Tipo_Movimento_Ajuste_S() +", " +
	          " oid_tipo_movimento_ajuste_canc = " + ed.getOid_Tipo_Movimento_Ajuste_Canc() +", " +
	          " oid_tipo_movimento_ajuste_exc = " + ed.getOid_Tipo_Movimento_Ajuste_Exc() +" ";
	          
	      executasql.executarUpdate (sql);
	    }
	    catch (Exception exc) {
	      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
	                          "alteraMovimento(Parametro_EmpresaED ed)");
	    }
	  }
  
//======================================== Altera Natureza ==================================================================\\
  public void alteraNatureza (Parametro_EmpresaED ed) throws Excecoes {
	    String sql = null;
	    try {
	      sql = "UPDATE " +
	          " Parametros_Empresas " +
	          "SET " +
	          " oid_natureza_operacao_ordem_frete = " + ed.getOid_Natureza_Operacao_Ordem_Frete () + ", " +
	          " oid_natureza_operacao_entrega = " + ed.getOid_Natureza_Operacao_Entrega () + ", " +
	          " oid_natureza_operacao_master = " + ed.getOid_Natureza_Operacao_Master () + " ";

	      executasql.executarUpdate (sql);
	    }
	    catch (Exception exc) {
	      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
	                          "alteraNatureza(Parametro_EmpresaED ed)");
	    }
	  }

//======================================== Altera Numerações ==================================================================\\
  public void alteraNumeracoes (Parametro_EmpresaED ed) throws Excecoes {
    String sql = null;
    try {
    	if (!doValida(this.getByRecord(ed).getDm_Tipo_Sistema())) {
      	  sql = "INSERT INTO parametros_empresas (dm_tipo_sistema ) VALUES ('XXX')";
      	  executasql.executarUpdate (sql);
        }
      sql = "UPDATE " +
          " Parametros_Empresas " +
          "SET " +
          " dm_numera_ordem_frete = '" + ed.getDm_Numera_Ordem_Frete () + "', " +
          " dm_numera_coleta = '" + ed.getDm_Numera_Coleta () + "', " +
          " dm_numera_manifesto = '" + ed.getDm_Numera_Manifesto () + "', " +
          " dm_numera_conhecimento = '" + ed.getDm_Numera_Conhecimento () + "', " +
          " dm_numera_frete_internacional = '" + ed.getDm_Numera_Frete_Internacional () + "', " +
          " dm_numera_fatura = '" + ed.getDm_Numera_Fatura () + "' ";
    
      executasql.executarUpdate (sql);
    }
    catch (Exception exc) {
      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
                          "alteraNumercoes(Parametro_EmpresaED ed)");
    }
  }  

//======================================== Altera Ocorrencia ========================================================================\\  
  public void alteraOcorrencia (Parametro_EmpresaED ed) throws Excecoes {
	    String sql = null;
	    try {
	      sql = "UPDATE " +
	          " Parametros_Empresas " +
	          "SET " +
	          " oid_tipo_ocorrencia_desconto_ctrc = " + ed.getOid_Tipo_Ocorrencia_Desconto_CTRC() + ", " +
	          " oid_tipo_ocorrencia_estorno_ctrc = " + ed.getOid_Tipo_Ocorrencia_Estorno_CTRC() + ", " +
	          " oid_tipo_ocorrencia_cancelamento_ctrc = " + ed.getOid_Tipo_Ocorrencia_Cancelamento_CTRC() + ", " +
	          " oid_tipo_ocorrencia_devolucao_ctrc = " + ed.getOid_Tipo_Ocorrencia_Devolucao_CTRC()+ ", " +
	          " oid_tipo_ocorrencia_reentrega_ctrc = " + ed.getOid_Tipo_Ocorrencia_Reentrega_CTRC() + " ";
	          
	      executasql.executarUpdate (sql);
	    }
	    catch (Exception exc) {
	      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
	                          "alteraOcorrencia(Parametro_EmpresaED ed)");
	    }
	  }
  
//======================================== Altera Pessoa =========================================================\\
public void alteraPessoa (Parametro_EmpresaED ed) throws Excecoes {
	    String sql = null;
	    try {
	      sql = "UPDATE " +
	          " Parametros_Empresas " +
	          "SET " +
	          " oid_Fornecedor_INSS = '" + ed.getOid_Fornecedor_INSS () + "', " +
	          " oid_Fornecedor_IRRF = '" + ed.getOid_Fornecedor_IRRF () + "', " +
	          " oid_Cliente_Complemento_Padrao = '" + ed.getOid_Cliente_Complemento_Padrao () + "', " +
	          " oid_Pessoa_Padrao_Tabela_Frete = '" + ed.getOid_Pessoa_Padrao_Tabela_Frete () + "', " +
	          " dm_Valida_CEP = '" + ed.getDm_Valida_CEP () + "', " +
	          " dm_Verifica_CNPJ_CPF_Pessoa = '" + ed.getDm_Verifica_CNPJ_CPF_Pessoa () + "', " +
	          " dm_Envia_Email_Eventos = '" + ed.getDm_Envia_Email_Eventos () + "', " +
	          " geraCodigoCliente = '" + ed.getGeraCodigoCliente () + "' ";
	          
	      executasql.executarUpdate (sql);
	    }
	    catch (Exception exc) {
	      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
	                          "alteraPessoa(Parametro_EmpresaED ed)");
	    }
	  }
 
//======================================== Altera Serviço ==================================================================\\
  public void alteraServico (Parametro_EmpresaED ed) throws Excecoes {
	    String sql = null;
	    try {
	      sql = "UPDATE " +
	          " Parametros_Empresas " +
	          "SET " +
	          " oid_Tipo_Servico_Acerto_Contas = " + ed.getOid_Tipo_Servico_Acerto_Contas () + ", " +
	          " oid_Tipo_Servico_Abastecimento = " + ed.getOid_Tipo_Servico_Abastecimento () + " ";

	      executasql.executarUpdate (sql);
	    }
	    catch (Exception exc) {
	      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
	                          "alteraServico(Parametro_EmpresaED ed)");
	    }
	  } 

//======================================== Altera Transporte Nacional =========================================================\\
public void alteraTransporteNacional (Parametro_EmpresaED ed) throws Excecoes {
	    String sql = null;
	    try {
	      sql = "UPDATE " +
	          " Parametros_Empresas " +
	          "SET " +
	          " dm_Exige_Pagador_Cliente = '" + ed.getDm_Exige_Pagador_Cliente () + "', " +
	          " dm_Imprime_Conhecimento_Ordem_Frete = '" + ed.getDm_Imprime_Conhecimento_Ordem_Frete () + "', " +
	          " dm_Tipo_Conhecimento = '" + ed.getDm_Tipo_Conhecimento () + "', " +
	          " dm_Criterio_Comissao = '" + ed.getDm_Criterio_Comissao () + "', " +
	          " dm_Frete_Cortesia = '" + ed.getDm_Frete_Cortesia () + "', " +
	          " dm_Vincula_Adto_Ordem_Principal = '" + ed.getDm_Vincula_Adto_Ordem_Principal () + "', " +
	          " dm_Despacho = '" + ed.getDm_Despacho () + "', " +
	          " dm_Inclui_ICMS_Parcela_CTRC = '" + ed.getDm_Inclui_ICMS_Parcela_CTRC () + "', " +
	          " oid_Produto = " + ed.getOid_Produto () + ", " +
	          " nr_Peso_Cubado = " + ed.getNr_Peso_Cubado () + ", " +
	          " nr_Conhecimentos_Linha_Fatura = " + ed.getNr_Conhecimentos_Linha_Fatura () + ", " +
	          " nf_Multi_Conhecimento = '" + ed.getNf_Multi_Conhecimento () + "' ";
	          
	      executasql.executarUpdate (sql);
	    }
	    catch (Exception exc) {
	      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
	                          "alteraTransporteNacional(Parametro_EmpresaED ed)");
	    }
	  }

//======================================== Altera Unidade ==================================================================\\
  public void alteraUnidade (Parametro_EmpresaED ed) throws Excecoes {
	    String sql = null;
	    try {
	      sql = "UPDATE " +
	          " Parametros_Empresas " +
	          "SET " +
	          " oid_Unidade_Faturamento = " + ed.getOid_Unidade_Faturamento () + ", " +
	          " oid_Unidade_Faturamento_Minuta = " + ed.getOid_Unidade_Faturamento_Minuta () + ", " +
	          " oid_Unidade_Padrao = " + ed.getOid_Unidade_Padrao () + " ";

	      executasql.executarUpdate (sql);
	    }
	    catch (Exception exc) {
	      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
	                          "alteraUnidade(Parametro_EmpresaED ed)");
	    }
	  }
  
//======================================== Altera WMS =========================================================================\\
  public void alteraWMS (Parametro_EmpresaED ed) throws Excecoes {
	    String sql = null;
	    try {
	      sql = "UPDATE " +
	          " Parametros_Empresas " +
	          "SET " +
	          " nm_nivel_produto1 = '" + ed.getNm_Nivel_Produto1 () + "', " +
	          " nm_nivel_produto2 = '" + ed.getNm_Nivel_Produto2 () + "', " +
	          " nm_nivel_produto3 = '" + ed.getNm_Nivel_Produto3 () + "', " +
	          " nm_nivel_produto4 = '" + ed.getNm_Nivel_Produto4 () + "', " +
	          " nm_nivel_produto5 = '" + ed.getNm_Nivel_Produto5 () + "', " +
	          " dm_wms_nf_saida_numerada = '" + ed.getDm_Wms_Nf_Saida_Numerada () + "', " +
	          " oid_tipo_estoque = " + ed.getOid_Tipo_Estoque() +", " +
	          " oid_tipo_estoque_devolucao = " + ed.getOid_Tipo_Estoque_Devolucao() +", " +
	          " oid_tipo_estoque_troca = " + ed.getOid_Tipo_Estoque_Troca() +", " +
	          " oid_tipo_pallet = " + ed.getOid_Tipo_Pallet() +", " +
	          " oid_tipo_pedido = " + ed.getOid_Tipo_Pedido() +", " +
	          " oid_deposito = " + ed.getOid_Deposito() +", " +
	          " oid_operador = " + ed.getOid_Operador() +", " +
	          " oid_embalagem = " + ed.getOid_Embalagem() +" ";
	          
	          
	      executasql.executarUpdate (sql);
	    }
	    catch (Exception exc) {
	      throw new Excecoes (exc.getMessage () , exc , this.getClass ().getName () ,
	                          "alteraAcoesGerais(Parametro_EmpresaED ed)");
	    }
	  }
  
//===================================================================================================================================\\ 
 
  public Parametro_EmpresaED getByRecord (Parametro_EmpresaED ed) throws Excecoes {
    String sql = null;
    Parametro_EmpresaED edQBR = new Parametro_EmpresaED ();
    try {
      sql = "SELECT * " +
          " FROM " +
          " Parametros_Empresas ";
      ResultSet res = null;
      res = this.executasql.executarConsulta (sql);
      while (res.next ()) {
    	  
    	//======== Tela Principal ===========\\
    	edQBR.setDm_Tipo_Sistema(res.getString("dm_Tipo_Sistema"));
        edQBR.setDm_Operacao_Sistema(res.getString("dm_Operacao_Sistema"));
        edQBR.setDm_Perfil_Sistema(res.getString("Dm_Perfil_Sistema"));
        edQBR.setDm_Operacao_Sistema(res.getString("dm_Operacao_Sistema"));
        edQBR.setDm_UsaCTB(res.getString("dm_UsaCTB"));
        edQBR.setNm_Razao_Social_Empresa(res.getString("nm_Razao_Social_Empresa"));
        edQBR.setDm_Situacao(res.getString("dm_Situacao"));
        edQBR.setOid_Moeda_Padrao(res.getLong("oid_Moeda_Padrao"));
        edQBR.setOid_Moeda_Dollar(res.getLong("oid_Moeda_Dollar"));
        
        //======== Tela Ações Gerais ===========\\
        edQBR.setDm_Gera_Embarque_Viagem(res.getString("dm_Gera_Embarque_Viagem"));
        edQBR.setDm_Gera_Rateio_Custo_Ordem_Frete(res.getString("dm_Gera_Rateio_Custo_Ordem_Frete"));
        edQBR.setDm_Gera_Ocorrencia_Viagem(res.getString("dm_Gera_Ocorrencia_Viagem"));
        edQBR.setDm_Gera_Custo_Viagem(res.getString("dm_Gera_Custo_Viagem"));
        edQBR.setDm_Gera_Compromisso_Viagem(res.getString("dm_Gera_Compromisso_Viagem"));
        edQBR.setDm_Gera_Compromisso_Nota_Fiscal_Compra(res.getString("dm_Gera_Compromisso_Nota_Fiscal_Compra"));
        edQBR.setDm_Gera_Compromisso_Ordem_Frete(res.getString("dm_Gera_Compromisso_Ordem_Frete"));
        edQBR.setDm_Gera_Compromisso_Ordem_Frete_Adiantamento(res.getString("dm_Gera_Compromisso_Ordem_Frete_Adiantamento"));
        edQBR.setDm_Gera_Compromisso_Ordem_Abastecimento(res.getString("dm_Gera_Compromisso_Ordem_Abastecimento"));
        edQBR.setDm_Gera_Compromisso_IR_Ordem_Frete(res.getString("dm_Gera_Compromisso_IR_Ordem_Frete"));
        edQBR.setDm_Gera_Compromisso_INSS_Ordem_Frete(res.getString("dm_Gera_Compromisso_INSS_Ordem_Frete"));
        edQBR.setDm_Gera_Compromisso_Master(res.getString("dm_Gera_Compromisso_Master"));
        edQBR.setDm_Gera_Compromisso_Movimento_Ordem_Servico(res.getString("dm_Gera_Compromisso_Movimento_Ordem_Servico"));
        edQBR.setDm_Gera_Compromisso_Motorista_Proprietario(res.getString("dm_Gera_Compromisso_Motorista_Proprietario"));
        edQBR.setDm_Gera_Lancamento_Contabil(res.getString("dm_Gera_Lancamento_Contabil")); 
        edQBR.setDm_Gera_Lancamento_Conta_Corrente_Ordem_Frete_Terceiro(res.getString("dm_Gera_Lancamento_Conta_Corrente_Ordem_Frete_Terceiro"));
        edQBR.setDm_Gera_Lancamento_Conta_Corrente_Ordem_Frete_Adiantamento(res.getString("dm_Gera_Lancamento_Conta_Corrente_Ordem_Frete_Adiantamento"));
        edQBR.setDm_Gera_Lancamento_Conta_Corrente_Ordem_Frete(res.getString("dm_Gera_Lancamento_Conta_Corrente_Ordem_Frete"));
        edQBR.setDm_Gera_Lancamento_Conta_Corrente_Acerto_Contas(res.getString("dm_Gera_Lancamento_Conta_Corrente_Acerto_Contas"));
        edQBR.setDm_Gera_Livro_Fiscal(res.getString("dm_Gera_Livro_Fiscal"));
        edQBR.setDm_Atualiza_Movimento_Caixa(res.getString("dm_Atualiza_Movimento_Caixa"));
        edQBR.setDm_Liquida_Compromisso(res.getString("dm_Liquida_Compromisso"));
        edQBR.setDm_Conhecimento_Varios_Manifestos(res.getString("dm_Conhecimento_Varios_Manifestos"));
        edQBR.setDm_Tranca_Saldo_Ordem_Frete(res.getString("dm_Tranca_Saldo_Ordem_Frete"));
		
		//=========Tela Aliquota ============\\
        edQBR.setPe_Aliquota_PIS_COFINS(res.getLong("pe_Aliquota_PIS_COFINS"));
        edQBR.setPe_Imposto_Faixa1(res.getLong("pe_Imposto_Faixa1"));
        edQBR.setPe_Imposto_Faixa2(res.getLong("pe_Imposto_Faixa2"));
        edQBR.setPe_Base_Calculo(res.getLong("pe_Base_Calculo"));
        edQBR.setPe_Aliquota_Empresa_INSS(res.getLong("pe_Aliquota_Empresa_INSS"));
        edQBR.setPe_Aliquota_Prestador_INSS(res.getLong("pe_Aliquota_Prestador_INSS"));
        edQBR.setPe_Base_INSS(res.getLong("pe_Base_INSS"));
        edQBR.setPe_Base_SET_SENAT(res.getLong("pe_Base_SET_SENAT"));
        edQBR.setPe_Aliquota_Prestador_Set_Senat(res.getLong("pe_Aliquota_Prestador_Set_Senat"));
        edQBR.setPe_Aliquota_COFINS(res.getLong("pe_Aliquota_COFINS"));
        edQBR.setPe_Aliquota_PIS(res.getLong("pe_Aliquota_PIS"));
        edQBR.setPe_Aliquota_CSLL(res.getLong("pe_Aliquota_CSLL"));
        edQBR.setPe_Comissao_Motorista(res.getLong("pe_Comissao_Motorista"));
        edQBR.setPe_Base_Comissao_Motorista(res.getLong("pe_Base_Comissao_Motorista"));
        edQBR.setPe_Comissao_Motorista_Media(res.getLong("pe_Comissao_Motorista_Media"));
        edQBR.setVl_Faixa1(res.getLong("vl_Faixa1"));
        edQBR.setVl_Faixa2(res.getLong("vl_Faixa2"));
        edQBR.setVl_Deducao_Faixa1(res.getLong("vl_Deducao_Faixa1"));
        edQBR.setVl_Deducao_Faixa2(res.getLong("vl_Deducao_Faixa2"));
        edQBR.setVl_Dependente(res.getLong("vl_Dependente"));
        edQBR.setVl_Maximo_INSS(res.getLong("vl_Maximo_INSS"));
        edQBR.setVl_Frete_Cortesia(res.getLong("vl_Frete_Cortesia"));
        edQBR.setVl_Reembolso(res.getString("vl_Reembolso"));
		
		//======== Tela Base Dados ===========\\
        edQBR.setPath_Relatorios(res.getString("path_Relatorios"));
        edQBR.setPath_Relatorios_XSL(res.getString("path_Relatorios_XSL"));
        edQBR.setPath_imagens(res.getString("path_imagens"));
        edQBR.setServerName(res.getString("serverName"));
        edQBR.setDt_Hoje(res.getString("dt_Hoje"));
        edQBR.setHr_Hoje(res.getString("hr_Hoje"));
        edQBR.setNm_Versao(res.getString("nm_Versao"));
        edQBR.setDocument_Form_Action(res.getString("document_Form_Action"));
        edQBR.setAppPath(res.getString("appPath"));
        edQBR.setPalmPath(res.getString("palmPath"));
        edQBR.setPalmModelo(res.getString("palmModelo"));
        edQBR.setLogTransactions(res.getString("logTransactions"));
        edQBR.setPort(res.getLong("port"));
        edQBR.setContexto(res.getString("contexto"));
        edQBR.setWebmaster(res.getString("webmaster"));
		
		//======== Tela Calculos ===========\\
        edQBR.setDm_Calcula_INSS(res.getString("dm_Calcula_INSS"));
        edQBR.setDm_Calcula_IRRF(res.getString("dm_Calcula_IRRF"));
        edQBR.setDm_Calcula_PIS_COFINS(res.getString("dm_Calcula_PIS_COFINS"));
        edQBR.setDm_Tipo_Calculo_ICMS(res.getString("dm_Tipo_Calculo_ICMS"));
        edQBR.setDm_Soma_Pedagio_Saldo_Frete(res.getString("dm_Soma_Pedagio_Saldo_Frete"));
        edQBR.setDm_Soma_Sest_Senat_Saldo_Frete(res.getString("dm_Soma_Sest_Senat_Saldo_Frete"));
        edQBR.setDm_Soma_Outros_Saldo_Frete(res.getString("dm_Soma_Outros_Saldo_Frete"));
		
		//======== Tela Carteira ===========\\
        edQBR.setOid_Carteira_Nota_Retorno(res.getLong("oid_Carteira_Nota_Retorno"));
        edQBR.setOid_Carteira_Faturamento(res.getLong("oid_Carteira_Faturamento"));
		
		//======== Tela Centro Custo ===========\\
        edQBR.setOid_Centro_Custo_Ordem_Frete(res.getLong("oid_Centro_Custo_Ordem_Frete"));
        edQBR.setOid_Centro_Custo_Movimento_Ordem_Servico(res.getLong("oid_Centro_Custo_Movimento_Ordem_Servico"));
        edQBR.setOid_Centro_Custo_Master(res.getLong("oid_Centro_Custo_Master"));
        edQBR.setOid_Centro_Custo_Entrega(res.getLong("oid_Centro_Custo_Entrega"));
		
		//======== Tela CFOP/TAXAS ===========\\
        edQBR.setCFOP_Padrao(res.getString("CFOP_Padrao"));
        edQBR.setOid_CFOP_Pessoa_Fisica(res.getLong("oid_CFOP_Pessoa_Fisica"));
        edQBR.setOid_CFOP_Diferenciado(res.getLong("oid_CFOP_Diferenciado"));
        edQBR.setDm_Estado_Origem_CFOP(res.getString("dm_Estado_Origem_CFOP"));
        edQBR.setDm_Estado_Destino_CFOP(res.getString("dm_Estado_Destino_CFOP"));
        edQBR.setDm_Estado_Origem_Taxas(res.getString("dm_Estado_Origem_Taxas"));
        edQBR.setDm_Estado_Destino_Taxas(res.getString("dm_Estado_Destino_Taxas"));
		
		//======== Tela Contas ===============\\
        edQBR.setOid_Conta_Credito_Master(res.getLong("oid_Conta_Credito_Master"));
        edQBR.setOid_Conta_Debito_Master(res.getLong("oid_Conta_Debito_Master"));
        edQBR.setOid_Conta_Credito_Pedagio(res.getLong("oid_Conta_Credito_Pedagio"));
        edQBR.setOid_Conta_Debito_Pedagio(res.getLong("oid_Conta_Debito_Pedagio"));
        edQBR.setOid_Conta_Credito_Entrega(res.getLong("oid_Conta_Credito_Entrega"));
        edQBR.setOid_Conta_Debito_Entrega(res.getLong("oid_Conta_Debito_Entrega"));
        edQBR.setOid_Conta_Credito_Ordem_Frete_Adiantamento(res.getLong("oid_Conta_Credito_Ordem_Frete_Adiantamento"));
        edQBR.setOid_Conta_Ordem_Frete_Adiantamento(res.getLong("oid_Conta_Ordem_Frete_Adiantamento"));
        edQBR.setOid_Conta_Frete_Terceiros(res.getLong("oid_Conta_Frete_Terceiros"));
        edQBR.setOid_Conta_Desconto_Frete(res.getLong("oid_Conta_Desconto_Frete"));
        edQBR.setOid_Conta_Despesa_Bancaria(res.getLong("oid_Conta_Despesa_Bancaria"));
        edQBR.setOid_Conta_Acerto_Contas(res.getLong("oid_Conta_Acerto_Contas"));
        edQBR.setOid_Conta_Ordem_Frete(res.getLong("oid_Conta_Ordem_Frete"));
        edQBR.setOid_Conta_IR_Ordem_Frete(res.getLong("oid_Conta_IR_Ordem_Frete"));
        edQBR.setOid_Conta_INSS_Ordem_Frete(res.getLong("oid_Conta_INSS_Ordem_Frete"));
        edQBR.setOid_Conta_Movimento_Compromisso(res.getLong("oid_Conta_Movimento_Compromisso"));
        edQBR.setOid_Conta_Movimento_Ordem_Servico(res.getLong("oid_Conta_Movimento_Ordem_Servico"));
        edQBR.setOid_Conta_Credito_Movimento_Ordem_Servico(res.getLong("oid_Conta_Credito_Movimento_Ordem_Servico"));
        edQBR.setOid_Conta_Credito_Ordem_Frete(res.getLong("oid_Conta_Credito_Ordem_Frete"));
        edQBR.setOid_Conta_Juridica_Ordem_Frete(res.getLong("oid_Conta_Juridica_Ordem_Frete"));
        edQBR.setOid_Conta_Multa_Transito(res.getLong("oid_Conta_Multa_Transito"));
        
        //======== Tela Descrição ===========\\
        edQBR.setDm_Saldo_Programado(res.getString("dm_Saldo_Programado"));
        edQBR.setTexto_Imprimir_Campo_Nao_Validado(res.getString("texto_Imprimir_Campo_Nao_Validado"));
        edQBR.setDm_Multi_Moeda(res.getString("dm_Multi_Moeda"));
        edQBR.setVias_Fatura(res.getLong("vias_Fatura"));
        edQBR.setOid_Condicao_Pagamento_Isento(res.getLong("oid_Condicao_Pagamento_Isento"));
		
		//=========Tela Documento ============\\
        edQBR.setOid_Tipo_Documento_Faturamento(res.getLong("oid_Tipo_Documento_Faturamento"));
        edQBR.setOid_Tipo_Documento_Ordem_Servico(res.getLong("oid_Tipo_Documento_Ordem_Servico"));
        edQBR.setOid_Tipo_Documento_Faturamento_Internacional(res.getLong("oid_Tipo_Documento_Faturamento_Internacional"));
        edQBR.setOid_Tipo_Documento_Master(res.getLong("oid_Tipo_Documento_Master"));
        edQBR.setOid_Tipo_Documento_IR(res.getLong("oid_Tipo_Documento_IR"));
        edQBR.setOid_Tipo_Documento_INSS(res.getLong("oid_Tipo_Documento_INSS"));
        edQBR.setOid_Tipo_Documento_Ordem_Frete(res.getLong("oid_Tipo_Documento_Ordem_Frete"));
        edQBR.setOid_Tipo_Documento_Transferencia_Conta_Corrente(res.getLong("oid_Tipo_Documento_Transferencia_Conta_Corrente"));
        edQBR.setOid_Tipo_Documento_Entrega(res.getLong("oid_Tipo_Documento_Entrega"));
        edQBR.setOid_Tipo_Documento_Ordem_Frete_Adiantamento(res.getLong("oid_Tipo_Documento_Ordem_Frete_Adiantamento"));
        edQBR.setOid_Tipo_Documento_Nota_Fiscal_Entrada(res.getLong("oid_Tipo_Documento_Nota_Fiscal_Entrada"));
        edQBR.setOid_Tipo_Documento_Nota_Fiscal_Saida(res.getLong("oid_Tipo_Documento_Nota_Fiscal_Saida"));
        edQBR.setOid_Tipo_Documento_Faturamento_Nota_Fiscal_Servico(res.getLong("oid_Tipo_Documento_Faturamento_Nota_Fiscal_Servico"));
		
		//======== Tela Faturamento ===========\\
        edQBR.setDm_Quebra_Faturamento_Tipo_Conhecimento(res.getString("dm_Quebra_Faturamento_Tipo_Conhecimento"));
        edQBR.setDm_Quebra_Faturamento_Unidade(res.getString("dm_Quebra_Faturamento_Unidade"));
        edQBR.setDm_Quebra_Faturamento_Tipo_Faturamento(res.getString("dm_Quebra_Faturamento_Tipo_Faturamento"));
		
		//======== Tela Formulario ===========\\
        edQBR.setDm_Formulario_Consolidacao_MIC(res.getString("dm_Formulario_Consolidacao_MIC"));
        edQBR.setDm_Formulario_Consolidacao_MIC_CRT(res.getString("dm_Formulario_Consolidacao_MIC_CRT"));
        edQBR.setDm_Formulario_Duplicata_Proform(res.getString("dm_Formulario_Duplicata_Proform"));
        edQBR.setDm_Formulario_Duplicata(res.getString("dm_Formulario_Duplicata"));
        edQBR.setDm_Formulario_Demonstrativo_Cobranca(res.getString("dm_Formulario_Demonstrativo_Cobranca"));
        edQBR.setDm_Formulario_Protocolo_Cobranca(res.getString("dm_Formulario_Protocolo_Cobranca"));
        edQBR.setDm_Formulario_Duplicata_Internacional(res.getString("dm_Formulario_Duplicata_Internacional"));
        edQBR.setDm_Formulario_Minuta(res.getString("dm_Formulario_Minuta"));  // Nº 8 ====
        edQBR.setDm_Formulario_ACT(res.getString("dm_Formulario_ACT"));
        edQBR.setDm_Formulario_Conhecimento_Nacional(res.getString("dm_Formulario_Conhecimento_Nacional")); // Nº10====
        edQBR.setDm_Formulario_Conhecimento_Internacional(res.getString("dm_Formulario_Conhecimento_Internacional"));
        edQBR.setDm_Formulario_Conhecimento_Internacional_Verso(res.getString("dm_Formulario_Conhecimento_Internacional_Verso"));
        edQBR.setDm_Formulario_Coleta(res.getString("dm_Formulario_Coleta")); //Nº13 =========
        edQBR.setDm_Formulario_Ordem_Frete(res.getString("Dm_Formulario_Ordem_Frete")); //Nª 14 ====
        edQBR.setDm_Formulario_Ordem_Frete_Adiantamento(res.getString("dm_Formulario_Ordem_Frete_Adiantamento")); //Nº15===
        edQBR.setDm_Formulario_Ordem_Abastecimento(res.getString("dm_Formulario_Ordem_Abastecimento"));
        edQBR.setDm_Formulario_Nota_Fiscal(res.getString("dm_Formulario_Nota_Fiscal"));
        edQBR.setDm_Formulario_Ordem_Servico(res.getString("dm_Formulario_Ordem_Servico"));
        edQBR.setDm_Formulario_Manifesto(res.getString("dm_Formulario_Manifesto")); // Nº 19 =====
        edQBR.setDm_Formulario_Manifesto_Redespacho(res.getString("dm_Formulario_Manifesto_Redespacho"));
        edQBR.setDm_Formulario_Romaneio_Nota_Fiscal(res.getString("dm_Formulario_Romaneio_Nota_Fiscal"));
        edQBR.setDm_Formulario_Romaneio_Itens_Nota_Fiscal(res.getString("dm_Formulario_Romaneio_Itens_Nota_Fiscal"));
        edQBR.setDm_Formulario_Acerto_Contas(res.getString("dm_Formulario_Acerto_Contas")); //Nº 23 =====
        edQBR.setDm_Formulario_Romaneio_Entrega(res.getString("dm_Formulario_Romaneio_Entrega"));
        edQBR.setDm_Formulario_AWB(res.getString("dm_Formulario_AWB"));
        edQBR.setDm_Formulario_Nota_Fiscal_Servico(res.getString("dm_Formulario_Nota_Fiscal_Servico"));
        edQBR.setNr_Vias_Ordem_Frete_Adiantamento(res.getLong("nr_Vias_Ordem_Frete_Adiantamento"));
        edQBR.setNr_Vias_Ordem_Frete(res.getLong("nr_Vias_Ordem_Frete"));
		
		//======== Tela Frota ===========\\
        edQBR.setDm_Base_Comissao_Motorista(res.getString("dm_Base_Comissao_Motorista"));
        edQBR.setDm_Limite_Credito_Motorista_Adiantamento_Frete(res.getString("dm_Limite_Credito_Motorista_Adiantamento_Frete"));
        edQBR.setDm_Exige_Validade_Lib_Seguradora_Motorista(res.getString("dm_Exige_Validade_Lib_Seguradora_Motorista"));
        edQBR.setMovimento_Ordem_Servico_Duplicada(res.getString("movimento_Ordem_Servico_Duplicada"));
		
		//======== Tela Historico ============\\
        edQBR.setOid_Historico_Transferencia_Banco(res.getLong("oid_Historico_Transferencia_Banco"));
        edQBR.setOid_Historico_Transferencia_Caixa(res.getLong("oid_Historico_Transferencia_Caixa"));
        edQBR.setOid_Historico_Compensacao(res.getLong("oid_Historico_Compensacao"));
        edQBR.setOid_Historico_Devolucao_Cheque(res.getLong("oid_Historico_Devolucao_Cheque"));
        edQBR.setOid_Historico_Cancelamento_Lote_Pagamento(res.getLong("oid_Historico_Cancelamento_Lote_Pagamento"));
        edQBR.setOid_Historico_Cancelamento_Ordem_Frete(res.getLong("oid_Historico_Cancelamento_Ordem_Frete"));
        edQBR.setOid_Historico_Lancamento_Ordem_Frete_Terceiro(res.getLong("oid_Historico_Lancamento_Ordem_Frete_Terceiro"));
        edQBR.setOid_Historico_Lancamento_Ordem_Frete(res.getLong("oid_Historico_Lancamento_Ordem_Frete"));
        edQBR.setOid_Historico_Lancamento_Ordem_Frete_Adiantamento(res.getLong("oid_Historico_Lancamento_Ordem_Frete_Adiantamento"));
        edQBR.setOid_Historico_Liquidacao_Cobranca(res.getLong("oid_Historico_Liquidacao_Cobranca"));
        edQBR.setOid_Historico_Liquidacao_Cobranca_Retorno(res.getLong("oid_Historico_Liquidacao_Cobranca_Retorno"));
        edQBR.setOid_Historico_Bordero(res.getLong("oid_Historico_Bordero"));
		
		//======== Tela Impressão ===========\\
        edQBR.setDm_Tipo_Impressao_Fatura(res.getString("dm_Tipo_Impressao_Fatura"));
        edQBR.setDm_Tipo_Impressao_Conhecimento_Nacional(res.getString("dm_Tipo_Impressao_Conhecimento_Nacional"));
        edQBR.setDm_Tipo_Impressao_Minuta(res.getString("dm_Tipo_Impressao_Minuta"));
        edQBR.setDm_Tipo_Impressao_Nota_Fiscal_Servico(res.getString("dm_Tipo_Impressao_Nota_Fiscal_Servico"));
        edQBR.setDm_Tipo_Impressao_Coleta(res.getString("dm_Tipo_Impressao_Coleta"));
		
		//======== Tela Instrucoes ===========\\
        edQBR.setOid_Tipo_Instrucao_Pago_Total(res.getLong("oid_Tipo_Instrucao_Pago_Total"));
        edQBR.setOid_Tipo_Instrucao_Pago_Cartorio(res.getLong("oid_Tipo_Instrucao_Pago_Cartorio"));
        edQBR.setOid_Tipo_Instrucao_Pago_Parcial(res.getLong("oid_Tipo_Instrucao_Pago_Parcial"));
        edQBR.setOid_Tipo_Instrucao_Tarifa(res.getLong("oid_Tipo_Instrucao_Tarifa"));
        edQBR.setOid_Tipo_Instrucao_Juros(res.getLong("oid_Tipo_Instrucao_Juros"));
        edQBR.setOid_Tipo_Instrucao_Desconto(res.getLong("oid_Tipo_Instrucao_Desconto"));
        edQBR.setOid_Tipo_Instrucao_Taxa_Cobranca(res.getLong("oid_Tipo_Instrucao_Taxa_Cobranca"));
        edQBR.setOid_Tipo_Instrucao_Estorno(res.getLong("oid_Tipo_Instrucao_Estorno"));
        edQBR.setOid_Tipo_Instrucao_Valor_Reembolso(res.getLong("oid_Tipo_Instrucao_Valor_Reembolso"));
        edQBR.setOid_Tipo_Instrucao_Juros_Reembolso(res.getLong("oid_Tipo_Instrucao_Juros_Reembolso"));
        edQBR.setOid_Tipo_Instrucao_Variacao_Cambial_Ativa(res.getLong("oid_Tipo_Instrucao_Variacao_Cambial_Ativa"));
        edQBR.setOid_Tipo_Instrucao_Variacao_Cambial_Passiva(res.getLong("oid_Tipo_Instrucao_Variacao_Cambial_Passiva"));
        edQBR.setOid_Tipo_Instrucao_Imposto_Retido1(res.getLong("oid_Tipo_Instrucao_Imposto_Retido1"));
        edQBR.setOid_Tipo_Instrucao_Imposto_Retido2(res.getLong("oid_Tipo_Instrucao_Imposto_Retido2"));
        edQBR.setOid_Tipo_Instrucao_Devolucao_Nota_Fiscal(res.getLong("oid_Tipo_Instrucao_Devolucao_Nota_Fiscal"));
        edQBR.setOid_Tipo_Instrucao_Remessa(res.getLong("oid_Tipo_Instrucao_Remessa"));
		
		//======== Tela Transporte Internacional ===========\\
        edQBR.setOid_Tipo_Faturamento_Exportador(res.getLong("oid_Tipo_Faturamento_Exportador"));
        edQBR.setOid_Tipo_Faturamento_Importador(res.getLong("oid_Tipo_Faturamento_Importador"));
        edQBR.setOid_Tipo_Faturamento_Consolidacao_MIC_CRT(res.getLong("oid_Tipo_Faturamento_Consolidacao_MIC_CRT"));
        edQBR.setOid_Tipo_Faturamento_Real(res.getLong("oid_Tipo_Faturamento_Real"));
		edQBR.setTx_Via_Primeiro_Original_Pt(res.getString("tx_Via_Primeiro_Original_Pt"));
        edQBR.setTx_Via_Segundo_Original_Pt(res.getString("tx_Via_Segundo_Original_Pt"));
        edQBR.setTx_Via_Terceiro_Original_Pt(res.getString("tx_Via_Terceiro_Original_Pt"));
        edQBR.setTx_Via_Primeiro_Original_Es(res.getString("tx_Via_Primeiro_Original_Es"));
        edQBR.setTx_Via_Segundo_Original_Es(res.getString("tx_Via_Segundo_Original_Es"));
        edQBR.setTx_Via_Terceiro_Original_Es(res.getString("tx_Via_Terceiro_Original_Es"));
        edQBR.setDm_Impressao_Tramo(res.getString("dm_Impressao_Tramo"));
        edQBR.setNm_Sucessivos(res.getString("nm_Sucessivos"));
        edQBR.setNm_Ano_Permisso(res.getString("nm_Ano_Permisso"));
        edQBR.setCompleta_TX_Via(res.getString("completa_TX_Via"));
        
		//======== Tela Localização ===========\\
        edQBR.setOid_Localizacao(res.getLong("oid_Localizacao"));
        edQBR.setOid_Localizacao_Devolucao(res.getLong("oid_Localizacao_Devolucao"));
        edQBR.setOid_Localizacao_Troca(res.getLong("oid_Localizacao_Troca")); 
		
		//======== Tela Modal Serviços ===========\\
        edQBR.setOid_Modal(res.getLong("oid_Modal"));
        edQBR.setOid_Modal_Aereo_Standard(res.getLong("oid_Modal_Aereo_Standard"));
        edQBR.setOid_Modal_Aereo_Expressa(res.getLong("oid_Modal_Aereo_Expressa"));
        edQBR.setOid_Modal_Aereo_Emergencial(res.getLong("oid_Modal_Aereo_Emergencial"));
        edQBR.setOid_Modal_Aereo_Sedex(res.getLong("oid_Modal_Aereo_Sedex"));
        edQBR.setOid_Modal_Aereo_Pac(res.getLong("oid_Modal_Aereo_Pac"));
        edQBR.setOid_Modal_Aereo_RodExp(res.getLong("oid_Modal_Aereo_RodExp"));
		
		//======== Tela Modelo ===========\\
        edQBR.setOid_Modelo_Nota_Fiscal(res.getLong("oid_Modelo_Nota_Fiscal"));
        edQBR.setOid_Modelo_NF_DevFornecedor_Dentro_Estado(res.getLong("oid_Modelo_NF_DevFornecedor_Dentro_Estado"));
        edQBR.setOid_Modelo_NF_DevFornecedor_Fora_Estado(res.getLong("oid_Modelo_NF_DevFornecedor_Fora_Estado"));
		
		//======== Tela Modelo Tabela ===========\\
        edQBR.setNm_Modelo_Tabela_Rodoviario(res.getString("nm_Modelo_Tabela_Rodoviario"));
        edQBR.setNm_Modelo_Tabela_Aereo(res.getString("nm_Modelo_Tabela_Aereo"));
        edQBR.setNm_Modelo_Tabela_Documento(res.getString("nm_Modelo_Tabela_Documento")); 
		
		//=========Tela Movimento ============\\
        edQBR.setOid_Tipo_Movimento_Custo_Entrega(res.getLong("oid_Tipo_Movimento_Custo_Entrega"));
        edQBR.setOid_Tipo_Movimento_Custo_Previsto_Entrega(res.getLong("oid_Tipo_Movimento_Custo_Previsto_Entrega"));
        edQBR.setOid_Tipo_Movimento_Custo_Master(res.getLong("oid_Tipo_Movimento_Custo_Master"));
        edQBR.setOid_Tipo_Movimento_Custo_Ordem_Frete(res.getLong("oid_Tipo_Movimento_Custo_Ordem_Frete"));
        edQBR.setOid_Tipo_Movimento_Ressarcimento(res.getLong("oid_Tipo_Movimento_Ressarcimento"));
        edQBR.setOid_Tipo_Movimento_Custo_Coleta(res.getLong("oid_Tipo_Movimento_Custo_Coleta"));
        edQBR.setOid_Tipo_Movimento_Custo_Transferencia(res.getLong("oid_Tipo_Movimento_Custo_Transferencia"));
        edQBR.setOid_Tipo_Movimento_Custo_Coleta_Transferencia(res.getLong("oid_Tipo_Movimento_Custo_Coleta_Transferencia"));
        edQBR.setOid_Tipo_Movimento_Custo_Transferencia_Entrega(res.getLong("oid_Tipo_Movimento_Custo_Transferencia_Entrega"));
        edQBR.setOid_Tipo_Movimento_Custo_Coleta_Transferencia_Entrega(res.getLong("oid_Tipo_Movimento_Custo_Coleta_Transferencia_Entrega"));
        edQBR.setOid_Tipo_Movimento_Custo_Desconto(res.getLong("oid_Tipo_Movimento_Custo_Desconto"));
        edQBR.setOid_Tipo_Movimento_Custo_Devolucao(res.getLong("oid_Tipo_Movimento_Custo_Devolucao"));
        edQBR.setOid_Tipo_Movimento_Custo_Reentrega(res.getLong("oid_Tipo_Movimento_Custo_Reentrega"));
        edQBR.setOid_Tipo_Movimento_Produto(res.getLong("oid_Tipo_Movimento_Produto"));
        edQBR.setOid_Tipo_Movimento_Transf(res.getLong("oid_Tipo_Movimento_Transf"));
        edQBR.setOid_Tipo_Movimento_Ajuste_E(res.getLong("oid_Tipo_Movimento_Ajuste_E"));
        edQBR.setOid_Tipo_Movimento_Ajuste_S(res.getLong("oid_Tipo_Movimento_Ajuste_S"));
        edQBR.setOid_Tipo_Movimento_Ajuste_Canc(res.getLong("oid_Tipo_Movimento_Ajuste_Canc"));
        edQBR.setOid_Tipo_Movimento_Ajuste_Exc(res.getLong("oid_Tipo_Movimento_Ajuste_Exc"));
		
		//======== Tela Natureza Operaçoes ===========\\
        edQBR.setOid_Natureza_Operacao_Ordem_Frete(res.getLong("oid_Natureza_Operacao_Ordem_Frete"));
        edQBR.setOid_Natureza_Operacao_Entrega(res.getLong("oid_Natureza_Operacao_Entrega"));
        edQBR.setOid_Natureza_Operacao_Master(res.getLong("oid_Natureza_Operacao_Master"));
		
		//======== Tela Numerações ===========\\
        edQBR.setDm_Numera_Ordem_Frete(res.getString("dm_Numera_Ordem_Frete"));
        edQBR.setDm_Numera_Coleta(res.getString("dm_Numera_Coleta"));
        edQBR.setDm_Numera_Manifesto(res.getString("dm_Numera_Manifesto"));
        edQBR.setDm_Numera_Conhecimento(res.getString("dm_Numera_Conhecimento"));
        edQBR.setDm_Numera_Frete_Internacional(res.getString("dm_Numera_Frete_Internacional"));
        edQBR.setDm_Numera_Fatura(res.getString("dm_Numera_Fatura"));
		
		//======== Tela Ocorrencia ===========\\
        edQBR.setOid_Tipo_Ocorrencia_Desconto_CTRC(res.getLong("oid_Tipo_Ocorrencia_Desconto_CTRC"));
        edQBR.setOid_Tipo_Ocorrencia_Estorno_CTRC(res.getInt("Oid_Tipo_Ocorrencia_Estorno_CTRC"));
        edQBR.setOid_Tipo_Ocorrencia_Cancelamento_CTRC(res.getLong("oid_Tipo_Ocorrencia_Cancelamento_CTRC"));
        edQBR.setOid_Tipo_Ocorrencia_Devolucao_CTRC(res.getInt("oid_Tipo_Ocorrencia_Devolucao_CTRC"));
        edQBR.setOid_Tipo_Ocorrencia_Reentrega_CTRC(res.getInt("oid_Tipo_Ocorrencia_Reentrega_CTRC"));
		
		//======== Tela Pessoa ===========\\
        edQBR.setOid_Fornecedor_INSS (res.getString("oid_Fornecedor_INSS"));
        edQBR.setOid_Fornecedor_IRRF(res.getString("oid_Fornecedor_IRRF"));
        edQBR.setOid_Cliente_Complemento_Padrao(res.getString("oid_Cliente_Complemento_Padrao"));
        edQBR.setOid_Pessoa_Padrao_Tabela_Frete(res.getString("oid_Pessoa_Padrao_Tabela_Frete"));
        edQBR.setDm_Valida_CEP(res.getString("dm_Valida_CEP"));
        edQBR.setDm_Verifica_CNPJ_CPF_Pessoa (res.getString("dm_Verifica_CNPJ_CPF_Pessoa"));
        edQBR.setDm_Envia_Email_Eventos(res.getString("dm_Envia_Email_Eventos"));
        edQBR.setGeraCodigoCliente(res.getString("geraCodigoCliente"));
			
		//======== Tela Serviço ===========\\
        edQBR.setOid_Tipo_Servico_Acerto_Contas(res.getLong("oid_Tipo_Servico_Acerto_Contas"));
        edQBR.setOid_Tipo_Servico_Abastecimento(res.getLong("oid_Tipo_Servico_Abastecimento"));
		
		//======== Tela Transporte Nacional ===========\\
        edQBR.setDm_Exige_Pagador_Cliente (res.getString("dm_Exige_Pagador_Cliente"));
        edQBR.setDm_Imprime_Conhecimento_Ordem_Frete(res.getString("dm_Imprime_Conhecimento_Ordem_Frete"));
        edQBR.setDm_Tipo_Conhecimento(res.getString("dm_Tipo_Conhecimento"));
        edQBR.setDm_Criterio_Comissao(res.getString("dm_Criterio_Comissao"));
        edQBR.setDm_Frete_Cortesia(res.getString("dm_Frete_Cortesia"));
        edQBR.setDm_Vincula_Adto_Ordem_Principal(res.getString("dm_Vincula_Adto_Ordem_Principal"));
        edQBR.setDm_Despacho(res.getString("dm_Despacho"));
        edQBR.setDm_Inclui_ICMS_Parcela_CTRC(res.getString("dm_Inclui_ICMS_Parcela_CTRC"));
        edQBR.setOid_Produto(res.getLong("oid_Produto"));
        edQBR.setNr_Peso_Cubado(res.getLong("nr_Peso_Cubado"));
        edQBR.setNr_Conhecimentos_Linha_Fatura(res.getLong("nr_Conhecimentos_Linha_Fatura"));
        edQBR.setNf_Multi_Conhecimento(res.getString("nf_Multi_Conhecimento"));
		
		//======== Tela Unidade ===========\\
        edQBR.setOid_Unidade_Faturamento(res.getLong("oid_Unidade_Faturamento"));
        edQBR.setOid_Unidade_Faturamento_Minuta(res.getLong("oid_Unidade_Faturamento_Minuta"));
        edQBR.setOid_Unidade_Padrao(res.getLong("oid_Unidade_Padrao")); 
		
		//======== Tela WMS ===========\\
        edQBR.setNm_Nivel_Produto1(res.getString("nm_Nivel_Produto1"));
        edQBR.setNm_Nivel_Produto2(res.getString("nm_Nivel_Produto2"));
        edQBR.setNm_Nivel_Produto3(res.getString("nm_Nivel_Produto3"));
        edQBR.setNm_Nivel_Produto4(res.getString("nm_Nivel_Produto4"));
        edQBR.setNm_Nivel_Produto5(res.getString("nm_Nivel_Produto5"));
        edQBR.setDm_Wms_Nf_Saida_Numerada(res.getString("dm_Wms_Nf_Saida_Numerada"));
        edQBR.setOid_Tipo_Estoque(res.getLong("oid_Tipo_Estoque"));
        edQBR.setOid_Tipo_Estoque_Devolucao(res.getLong("oid_Tipo_Estoque_Devolucao"));
        edQBR.setOid_Tipo_Estoque_Troca(res.getLong("oid_Tipo_Estoque_Troca"));
        edQBR.setOid_Tipo_Pallet(res.getLong("oid_Tipo_Pallet"));
        edQBR.setOid_Tipo_Pedido(res.getLong("oid_Tipo_Pedido"));
        edQBR.setOid_Deposito(res.getLong("oid_Deposito"));
        edQBR.setOid_Operador(res.getLong("oid_Operador"));
        edQBR.setOid_Embalagem(res.getLong("oid_Embalagem"));
		
//=================================================================================================================
        
        //======== Limpa se for null - Principal ==========\\
        if (!doValida( edQBR.getDm_Tipo_Sistema())) {edQBR.setDm_Tipo_Sistema("");}
        if (!doValida( edQBR.getDm_Perfil_Sistema())) {edQBR.setDm_Perfil_Sistema("");}
        if (!doValida( edQBR.getDm_Operacao_Sistema())) {edQBR.setDm_Operacao_Sistema("");}
        if (!doValida( edQBR.getDm_UsaCTB())) {edQBR.setDm_UsaCTB("");}
        if (!doValida( edQBR.getNm_Razao_Social_Empresa())) {edQBR.setNm_Razao_Social_Empresa("");}
        if (!doValida( edQBR.getDm_Situacao())) {edQBR.setDm_Situacao("");}
        
		//======== Limpa se for null - Ações Gerais ==========\\
        if (!doValida( edQBR.getDm_Gera_Embarque_Viagem())) {edQBR.setDm_Gera_Embarque_Viagem("");}
        if (!doValida( edQBR.getDm_Gera_Rateio_Custo_Ordem_Frete())) {edQBR.setDm_Gera_Rateio_Custo_Ordem_Frete("");}
        if (!doValida( edQBR.getDm_Gera_Ocorrencia_Viagem())) {edQBR.setDm_Gera_Ocorrencia_Viagem("");}
        if (!doValida( edQBR.getDm_Gera_Custo_Viagem())) {edQBR.setDm_Gera_Custo_Viagem("");}
        if (!doValida( edQBR.getDm_Gera_Compromisso_Viagem())) {edQBR.setDm_Gera_Compromisso_Viagem("");}
        if (!doValida( edQBR.getDm_Gera_Compromisso_Nota_Fiscal_Compra())) {edQBR.setDm_Gera_Compromisso_Nota_Fiscal_Compra("");}
        if (!doValida( edQBR.getDm_Gera_Compromisso_Ordem_Frete())) {edQBR.setDm_Gera_Compromisso_Ordem_Frete("");}
        if (!doValida( edQBR.getDm_Gera_Compromisso_Ordem_Frete_Adiantamento())) {edQBR.setDm_Gera_Compromisso_Ordem_Frete_Adiantamento("");}
        if (!doValida( edQBR.getDm_Gera_Compromisso_Ordem_Abastecimento())) {edQBR.setDm_Gera_Compromisso_Ordem_Abastecimento("");}
        if (!doValida( edQBR.getDm_Gera_Compromisso_IR_Ordem_Frete())) {edQBR.setDm_Gera_Compromisso_IR_Ordem_Frete("");}
        if (!doValida( edQBR.getDm_Gera_Compromisso_INSS_Ordem_Frete())) {edQBR.setDm_Gera_Compromisso_INSS_Ordem_Frete("");}
        if (!doValida( edQBR.getDm_Gera_Compromisso_Master())) {edQBR.setDm_Gera_Compromisso_Master("");}
        if (!doValida( edQBR.getDm_Gera_Compromisso_Movimento_Ordem_Servico())) {edQBR.setDm_Gera_Compromisso_Movimento_Ordem_Servico("");}
        if (!doValida( edQBR.getDm_Gera_Compromisso_Motorista_Proprietario())) {edQBR.setDm_Gera_Compromisso_Motorista_Proprietario("");}
        if (!doValida( edQBR.getDm_Gera_Lancamento_Contabil())) {edQBR.setDm_Gera_Lancamento_Contabil("");}
        if (!doValida( edQBR.getDm_Gera_Lancamento_Conta_Corrente_Ordem_Frete_Terceiro())) {edQBR.setDm_Gera_Lancamento_Conta_Corrente_Ordem_Frete_Terceiro("");}
        if (!doValida( edQBR.getDm_Gera_Lancamento_Conta_Corrente_Ordem_Frete_Adiantamento())) {edQBR.setDm_Gera_Lancamento_Conta_Corrente_Ordem_Frete_Adiantamento("");}
        if (!doValida( edQBR.getDm_Gera_Lancamento_Conta_Corrente_Ordem_Frete())) {edQBR.setDm_Gera_Lancamento_Conta_Corrente_Ordem_Frete("");}
        if (!doValida( edQBR.getDm_Gera_Lancamento_Conta_Corrente_Acerto_Contas())) {edQBR.setDm_Gera_Lancamento_Conta_Corrente_Acerto_Contas("");}
        if (!doValida( edQBR.getDm_Gera_Livro_Fiscal())) {edQBR.setDm_Gera_Livro_Fiscal("");}
        if (!doValida( edQBR.getDm_Atualiza_Movimento_Caixa())) {edQBR.setDm_Atualiza_Movimento_Caixa("");}
        if (!doValida( edQBR.getDm_Liquida_Compromisso())) {edQBR.setDm_Liquida_Compromisso("");}
        if (!doValida( edQBR.getDm_Conhecimento_Varios_Manifestos())) {edQBR.setDm_Conhecimento_Varios_Manifestos("");}
        if (!doValida( edQBR.getDm_Tranca_Saldo_Ordem_Frete())) {edQBR.setDm_Tranca_Saldo_Ordem_Frete("");}
        
        //======== Limpa se for null - Aliquota ==========\\
        if (!doValida( edQBR.getVl_Reembolso())) {edQBR.setVl_Reembolso("");}
		
		//======== Limpa se for null - Base Dados ==========\\
        if (!doValida( edQBR.getPath_Relatorios())) {edQBR.setPath_Relatorios("");}
        if (!doValida( edQBR.getPath_Relatorios_XSL())) {edQBR.setPath_Relatorios_XSL("");}
        if (!doValida( edQBR.getPath_imagens())) {edQBR.setPath_imagens("");}
        if (!doValida( edQBR.getServerName())) {edQBR.setServerName("");}
        if (!doValida( edQBR.getDt_Hoje())) {edQBR.setDt_Hoje("");}
        if (!doValida( edQBR.getHr_Hoje())) {edQBR.setHr_Hoje("");}
        if (!doValida( edQBR.getNm_Versao())) {edQBR.setNm_Versao("");}
        if (!doValida( edQBR.getDocument_Form_Action())) {edQBR.setDocument_Form_Action("");}
        if (!doValida( edQBR.getAppPath())) {edQBR.setAppPath("");}
        if (!doValida( edQBR.getPalmPath())) {edQBR.setPalmPath("");}
        if (!doValida( edQBR.getPalmModelo())) {edQBR.setPalmModelo("");}
        if (!doValida( edQBR.getContexto())) {edQBR.setContexto("");}
        if (!doValida( edQBR.getWebmaster())) {edQBR.setWebmaster("");}
		
		//======== Limpa se for null - Calculos ==========\\
        if (!doValida( edQBR.getDm_Calcula_INSS())) {edQBR.setDm_Calcula_INSS("");}
        if (!doValida( edQBR.getDm_Calcula_IRRF())) {edQBR.setDm_Calcula_IRRF("");}
        if (!doValida( edQBR.getDm_Calcula_PIS_COFINS())) {edQBR.setDm_Calcula_PIS_COFINS("");}
        if (!doValida( edQBR.getDm_Tipo_Calculo_ICMS())) {edQBR.setDm_Tipo_Calculo_ICMS("");}
        if (!doValida( edQBR.getDm_Soma_Pedagio_Saldo_Frete())) {edQBR.setDm_Soma_Pedagio_Saldo_Frete("");}
        if (!doValida( edQBR.getDm_Soma_Sest_Senat_Saldo_Frete())) {edQBR.setDm_Soma_Sest_Senat_Saldo_Frete("");}
        if (!doValida( edQBR.getDm_Soma_Outros_Saldo_Frete())) {edQBR.setDm_Soma_Outros_Saldo_Frete("");}
		
		//======== Limpa se for null - CFOP/TAXAS ===========\\
        if (!doValida( edQBR.getCFOP_Padrao())) {edQBR.setCFOP_Padrao("");}
        if (!doValida( edQBR.getDm_Estado_Origem_CFOP())) {edQBR.setDm_Estado_Origem_CFOP("");}
        if (!doValida( edQBR.getDm_Estado_Destino_CFOP())) {edQBR.setDm_Estado_Destino_CFOP("");}
        if (!doValida( edQBR.getDm_Estado_Origem_Taxas())) {edQBR.setDm_Estado_Origem_Taxas("");}
        if (!doValida( edQBR.getDm_Estado_Destino_Taxas())) {edQBR.setDm_Estado_Destino_Taxas("");}
        
        //======== Limpa se for null - Descrição ===========\\
        if (!doValida( edQBR.getDm_Saldo_Programado())) {edQBR.setDm_Saldo_Programado("");}
        if (!doValida( edQBR.getTexto_Imprimir_Campo_Nao_Validado())) {edQBR.setTexto_Imprimir_Campo_Nao_Validado("");}
        if (!doValida( edQBR.getDm_Multi_Moeda())) {edQBR.setDm_Multi_Moeda("");}
		
		//======== Limpa se for null - Faturamento ==========\\
        if (!doValida( edQBR.getDm_Quebra_Faturamento_Tipo_Conhecimento())) {edQBR.setDm_Quebra_Faturamento_Tipo_Conhecimento("");}
        if (!doValida( edQBR.getDm_Quebra_Faturamento_Unidade())) {edQBR.setDm_Quebra_Faturamento_Unidade("");}
        if (!doValida( edQBR.getDm_Quebra_Faturamento_Tipo_Faturamento())) {edQBR.setDm_Quebra_Faturamento_Tipo_Faturamento("");}
        
		//======== Limpa se for null - Formulario ==========\\
        if (!doValida( edQBR.getDm_Formulario_Consolidacao_MIC())) {edQBR.setDm_Formulario_Consolidacao_MIC("");}
        if (!doValida( edQBR.getDm_Formulario_Consolidacao_MIC_CRT())) {edQBR.setDm_Formulario_Consolidacao_MIC_CRT("");}
        if (!doValida( edQBR.getDm_Formulario_Duplicata_Proform())) {edQBR.setDm_Formulario_Duplicata_Proform("");}
        if (!doValida( edQBR.getDm_Formulario_Duplicata())) {edQBR.setDm_Formulario_Duplicata("");}
        if (!doValida( edQBR.getDm_Formulario_Demonstrativo_Cobranca())) {edQBR.setDm_Formulario_Demonstrativo_Cobranca("");}
        if (!doValida( edQBR.getDm_Formulario_Protocolo_Cobranca())) {edQBR.setDm_Formulario_Protocolo_Cobranca("");}
        if (!doValida( edQBR.getDm_Formulario_Duplicata_Internacional())) {edQBR.setDm_Formulario_Duplicata_Internacional("");}
        if (!doValida( edQBR.getDm_Formulario_Minuta())) {edQBR.setDm_Formulario_Minuta("");}
        if (!doValida( edQBR.getDm_Formulario_ACT())) {edQBR.setDm_Formulario_ACT("");}
        if (!doValida( edQBR.getDm_Formulario_Conhecimento_Nacional())) {edQBR.setDm_Formulario_Conhecimento_Nacional("");}
        if (!doValida( edQBR.getDm_Formulario_Conhecimento_Internacional())) {edQBR.setDm_Formulario_Conhecimento_Internacional("");}
        if (!doValida( edQBR.getDm_Formulario_Conhecimento_Internacional_Verso())) {edQBR.setDm_Formulario_Conhecimento_Internacional_Verso("");}
        if (!doValida( edQBR.getDm_Formulario_Coleta())) {edQBR.setDm_Formulario_Coleta("");}
        if (!doValida( edQBR.getDm_Formulario_Ordem_Frete())) {edQBR.setDm_Formulario_Ordem_Frete("");}
        if (!doValida( edQBR.getDm_Formulario_Ordem_Frete_Adiantamento())) {edQBR.setDm_Formulario_Ordem_Frete_Adiantamento("");}
        if (!doValida( edQBR.getDm_Formulario_Ordem_Abastecimento())) {edQBR.setDm_Formulario_Ordem_Abastecimento("");}
        if (!doValida( edQBR.getDm_Formulario_Nota_Fiscal())) {edQBR.setDm_Formulario_Nota_Fiscal("");}
        if (!doValida( edQBR.getDm_Formulario_Ordem_Servico())) {edQBR.setDm_Formulario_Ordem_Servico("");}
        if (!doValida( edQBR.getDm_Formulario_Manifesto())) {edQBR.setDm_Formulario_Manifesto("");}
        if (!doValida( edQBR.getDm_Formulario_Manifesto_Redespacho())) {edQBR.setDm_Formulario_Manifesto_Redespacho("");}
        if (!doValida( edQBR.getDm_Formulario_Romaneio_Nota_Fiscal())) {edQBR.setDm_Formulario_Romaneio_Nota_Fiscal("");}
        if (!doValida( edQBR.getDm_Formulario_Romaneio_Itens_Nota_Fiscal())) {edQBR.setDm_Formulario_Romaneio_Itens_Nota_Fiscal("");}
        if (!doValida( edQBR.getDm_Formulario_Acerto_Contas())) {edQBR.setDm_Formulario_Acerto_Contas("");}
        if (!doValida( edQBR.getDm_Formulario_Romaneio_Entrega())) {edQBR.setDm_Formulario_Romaneio_Entrega("");}
        if (!doValida( edQBR.getDm_Formulario_AWB())) {edQBR.setDm_Formulario_AWB("");}
        if (!doValida( edQBR.getDm_Formulario_Nota_Fiscal_Servico())) {edQBR.setDm_Formulario_Nota_Fiscal_Servico("");}
		
		//======== Limpa se for null - Frota ==========\\
        if (!doValida( edQBR.getDm_Base_Comissao_Motorista())) {edQBR.setDm_Base_Comissao_Motorista("");}
        if (!doValida( edQBR.getDm_Limite_Credito_Motorista_Adiantamento_Frete())) {edQBR.setDm_Limite_Credito_Motorista_Adiantamento_Frete("");}
        if (!doValida( edQBR.getDm_Exige_Validade_Lib_Seguradora_Motorista())) {edQBR.setDm_Exige_Validade_Lib_Seguradora_Motorista("");}
		
		//======== Limpa se for null - Impressão ==========\\
        if (!doValida( edQBR.getDm_Tipo_Impressao_Fatura())) {edQBR.setDm_Tipo_Impressao_Fatura("");}
        if (!doValida( edQBR.getDm_Tipo_Impressao_Conhecimento_Nacional())) {edQBR.setDm_Tipo_Impressao_Conhecimento_Nacional("");}
        if (!doValida( edQBR.getDm_Tipo_Impressao_Minuta())) {edQBR.setDm_Tipo_Impressao_Minuta("");}
        if (!doValida( edQBR.getDm_Tipo_Impressao_Nota_Fiscal_Servico())) {edQBR.setDm_Tipo_Impressao_Nota_Fiscal_Servico("");}
        if (!doValida( edQBR.getDm_Tipo_Impressao_Coleta())) {edQBR.setDm_Tipo_Impressao_Coleta("");}
		
		//======== Limpa se for null - Transporte Internacional ==========\\
        if (!doValida( edQBR.getTx_Via_Primeiro_Original_Pt())) {edQBR.setTx_Via_Primeiro_Original_Pt("");}
        if (!doValida( edQBR.getTx_Via_Segundo_Original_Pt())) {edQBR.setTx_Via_Segundo_Original_Pt("");}
        if (!doValida( edQBR.getTx_Via_Terceiro_Original_Pt())) {edQBR.setTx_Via_Terceiro_Original_Pt("");}
        if (!doValida( edQBR.getTx_Via_Primeiro_Original_Es())) {edQBR.setTx_Via_Primeiro_Original_Es("");}
        if (!doValida( edQBR.getTx_Via_Segundo_Original_Es())) {edQBR.setTx_Via_Segundo_Original_Es("");}
        if (!doValida( edQBR.getTx_Via_Terceiro_Original_Es())) {edQBR.setTx_Via_Terceiro_Original_Es("");}
        if (!doValida( edQBR.getDm_Impressao_Tramo())) {edQBR.setDm_Impressao_Tramo("");}
        if (!doValida( edQBR.getNm_Sucessivos())) {edQBR.setNm_Sucessivos("");}
        if (!doValida( edQBR.getNm_Ano_Permisso())) {edQBR.setNm_Ano_Permisso("");}
		
		//======== Limpa se for null - Modelo Tabela ==========\\
        if (!doValida( edQBR.getNm_Modelo_Tabela_Rodoviario())) {edQBR.setNm_Modelo_Tabela_Rodoviario("");}
        if (!doValida( edQBR.getNm_Modelo_Tabela_Aereo())) {edQBR.setNm_Modelo_Tabela_Aereo("");}
        if (!doValida( edQBR.getNm_Modelo_Tabela_Documento())) {edQBR.setNm_Modelo_Tabela_Documento("");}
		
		//======== Limpa se for null - Numerações ==========\\
        if (!doValida( edQBR.getDm_Numera_Ordem_Frete())) {edQBR.setDm_Numera_Ordem_Frete("");}
        if (!doValida( edQBR.getDm_Numera_Coleta())) {edQBR.setDm_Numera_Coleta("");}
        if (!doValida( edQBR.getDm_Numera_Manifesto())) {edQBR.setDm_Numera_Manifesto("");}
        if (!doValida( edQBR.getDm_Numera_Conhecimento())) {edQBR.setDm_Numera_Conhecimento("");}
        if (!doValida( edQBR.getDm_Numera_Frete_Internacional())) {edQBR.setDm_Numera_Frete_Internacional("");}
        if (!doValida( edQBR.getDm_Numera_Fatura())) {edQBR.setDm_Numera_Fatura("");}
		
		//======== Limpa se for null - Pessoa ==========\\
        if (!doValida( edQBR.getOid_Fornecedor_INSS())) {edQBR.setOid_Fornecedor_INSS("");}
        if (!doValida( edQBR.getOid_Fornecedor_IRRF())) {edQBR.setOid_Fornecedor_IRRF("");}
        if (!doValida( edQBR.getOid_Cliente_Complemento_Padrao())) {edQBR.setOid_Cliente_Complemento_Padrao("");}
        if (!doValida( edQBR.getOid_Pessoa_Padrao_Tabela_Frete())) {edQBR.setOid_Pessoa_Padrao_Tabela_Frete("");}
        if (!doValida( edQBR.getDm_Valida_CEP())) {edQBR.setDm_Valida_CEP("");}
        if (!doValida( edQBR.getDm_Verifica_CNPJ_CPF_Pessoa())) {edQBR.setDm_Verifica_CNPJ_CPF_Pessoa("");}
        if (!doValida( edQBR.getDm_Envia_Email_Eventos())) {edQBR.setDm_Envia_Email_Eventos("");}
        if (!doValida( edQBR.getGeraCodigoCliente())) {edQBR.setGeraCodigoCliente("");}
		
		//======== Limpa se for null - Transporte Nacional ==========\\
        if (!doValida( edQBR.getDm_Exige_Pagador_Cliente())) {edQBR.setDm_Exige_Pagador_Cliente("");}
        if (!doValida( edQBR.getDm_Imprime_Conhecimento_Ordem_Frete())) {edQBR.setDm_Imprime_Conhecimento_Ordem_Frete("");}
        if (!doValida( edQBR.getDm_Tipo_Conhecimento())) {edQBR.setDm_Tipo_Conhecimento("");}
        if (!doValida( edQBR.getDm_Criterio_Comissao())) {edQBR.setDm_Criterio_Comissao("");}
        if (!doValida( edQBR.getDm_Frete_Cortesia())) {edQBR.setDm_Frete_Cortesia("");}
        if (!doValida( edQBR.getDm_Vincula_Adto_Ordem_Principal())) {edQBR.setDm_Vincula_Adto_Ordem_Principal("");}
        if (!doValida( edQBR.getDm_Despacho())) {edQBR.setDm_Despacho("");}
        if (!doValida( edQBR.getDm_Inclui_ICMS_Parcela_CTRC())) {edQBR.setDm_Inclui_ICMS_Parcela_CTRC("");}
        if (!doValida( edQBR.getNf_Multi_Conhecimento())) {edQBR.setNf_Multi_Conhecimento("");}
		
		//======== Limpa se for null - WMS ==========\\
        if (!doValida( edQBR.getNm_Nivel_Produto1())) {edQBR.setNm_Nivel_Produto1("");}
        if (!doValida( edQBR.getNm_Nivel_Produto2())) {edQBR.setNm_Nivel_Produto2("");}
        if (!doValida( edQBR.getNm_Nivel_Produto3())) {edQBR.setNm_Nivel_Produto3("");}
        if (!doValida( edQBR.getNm_Nivel_Produto4())) {edQBR.setNm_Nivel_Produto4("");}
        if (!doValida( edQBR.getNm_Nivel_Produto5())) {edQBR.setNm_Nivel_Produto5("");}
        if (!doValida( edQBR.getDm_Wms_Nf_Saida_Numerada())) {edQBR.setDm_Wms_Nf_Saida_Numerada("");}
        
      }
    }
    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "getByRecord(SistemaED ed)");
    }
    return edQBR;
  }
}