package com.master.root;

import java.sql.*;
import java.util.*;
import javax.servlet.http.*;

import auth.*;
import com.master.bd.*;
import com.master.ed.*;
import com.master.rn.*;
import com.master.util.*;
import com.master.util.bd.*;
import com.master.util.ed.*;

public class Movimento_Ordem_ServicoBean extends Transacao {

  private double NR_Quantidade;
  private String DT_Movimento_Ordem_Servico;
  private String DT_Vencimento;
  private String DT_Encerramento;
  private long NR_Documento;
  private long NR_Kilometragem;
  private double VL_Movimento;
  private double VL_Movimento_Pago;
  private double VL_Movimento_Faturado;
  private double VL_Cotacao;
  private double VL_Documento;
  private String TX_Observacao;
  private String DM_Faturado_Pago;
  private String Usuario_Stamp;
  private String Dt_Stamp;
  private String Dm_Stamp;
  private long oid;
  private int oid_Tipo_Servico;
  private int oid_Conta;
  private int oid_Ordem_Servico;
  private int oid_Tipo_Documento;
  private int oid_Estoque;
  private int oid_Unidade;
  private long oid_Moeda;
  private long oid_Usuario;
  private String oid_Pessoa;
  private String NM_Razao_Social;
  private String NM_Tipo_Servico;
  private String CD_Tipo_Servico;
  private String NM_Tipo_Documento;
  private String CD_Tipo_Documento;
  private ExecutaSQL executasql;
  private String DT_Entrada;
  private int Oid_Natureza_Operacao;
  private double Vl_Imposto;
  private double Pe_Imposto;
  private double VL_Total_Frete;
  private double VL_Adiantamento_Viagem;
  private double VL_Frete_Peso;
  private double VL_Frete_Terceiro;
  private double VL_Frete_Proprio;
  private String OID_Veiculo;
  private double VL_Unitario;
  private String TX_Servico;
  private String oid_Motorista;
  private double VL_Movimento_Extra;
  private long NR_Movimento_Ordem_Servico;
  private double NR_Litros;
  private String DM_Custo_Fixo;
  private String oid_Nota_Fiscal;
  private long oid_Item_Solicitacao_Entrega;

  Utilitaria util = new Utilitaria(executasql);
  private String DM_Acerto;
  private double VL_Compromisso;

  private double VL_Desconto;

  public double getVL_Unitario () {
    return VL_Unitario;
  }

  public void setVL_Unitario (double unitario) {
    VL_Unitario = unitario;
  }

  public void setVL_Frete_Peso (double VL_Frete_Peso) {
    this.VL_Frete_Peso = VL_Frete_Peso;
  }

  public double getVL_Frete_Peso () {
    return VL_Frete_Peso;
  }

  public void setVL_Frete_Terceiro (double VL_Frete_Terceiro) {
    this.VL_Frete_Terceiro = VL_Frete_Terceiro;
  }

  public double getVL_Frete_Terceiro () {
    return VL_Frete_Terceiro;
  }

  public void setVL_Frete_Proprio (double VL_Frete_Proprio) {
    this.VL_Frete_Proprio = VL_Frete_Proprio;
  }

  public double getVL_Frete_Proprio () {
    return VL_Frete_Proprio;
  }

  public void setOID_Veiculo (String OID_Veiculo) {
    this.OID_Veiculo = OID_Veiculo;
  }

  public String getOID_Veiculo () {
    return OID_Veiculo;
  }

  public void setTX_Servico (String TX_Servico) {
    this.TX_Servico = TX_Servico;
  }

  public String getOid_Motorista () {
    return this.oid_Motorista;
  }

  public double getVL_Compromisso () {
    return VL_Compromisso;
  }

  public String getDM_Acerto () {
    return DM_Acerto;
  }

  public String getDM_Custo_Fixo () {
    return DM_Custo_Fixo;
  }

  public double getNR_Litros () {
    return NR_Litros;
  }

  public double getVL_Movimento_Extra () {
    return VL_Movimento_Extra;
  }

  public void setOid_Motorista (String oid_Motorista) {
    this.oid_Motorista = oid_Motorista;
  }

  public void setVL_Compromisso (double VL_Compromisso) {
    this.VL_Compromisso = VL_Compromisso;
  }

  public void setDM_Acerto (String DM_Acerto) {
    this.DM_Acerto = DM_Acerto;
  }

  public void setDM_Custo_Fixo (String DM_Custo_Fixo) {
    this.DM_Custo_Fixo = DM_Custo_Fixo;
  }

  public void setNR_Litros (double NR_Litros) {
    this.NR_Litros = NR_Litros;
  }

  public void setVL_Movimento_Extra (double VL_Movimento_Extra) {
    this.VL_Movimento_Extra = VL_Movimento_Extra;
  }

  public String getTX_Servico () {
    return TX_Servico;
  }

  public long getNR_Movimento_Ordem_Servico () {
    return this.NR_Movimento_Ordem_Servico;
  }

  public Movimento_Ordem_ServicoBean () {
    NR_Documento = 0;
    NR_Kilometragem = 0;
    VL_Movimento = 0;
    VL_Movimento_Faturado = 0;
    VL_Movimento_Pago = 0;
    VL_Total_Frete = 0;
    VL_Adiantamento_Viagem = 0;
    VL_Cotacao = 0;
    VL_Documento = 0;
    TX_Observacao = " ";
    DM_Faturado_Pago = " ";
    Usuario_Stamp = "";
    Dm_Stamp = "";
    NM_Tipo_Documento = "";
    NM_Tipo_Servico = "";
    CD_Tipo_Servico = "";
    CD_Tipo_Documento = "";
    NM_Razao_Social = "";
  }

  public int getOID_Ordem_Servico () {
    return oid_Ordem_Servico;
  }

  public void setOID_Ordem_Servico (int oid_Ordem_Servico) {
    this.oid_Ordem_Servico = oid_Ordem_Servico;
  }

  public String getDT_Movimento_Ordem_Servico () {
    FormataDataBean DataFormatada = new FormataDataBean ();
    DataFormatada.setDT_FormataData (DT_Movimento_Ordem_Servico);
    DT_Movimento_Ordem_Servico = DataFormatada.getDT_FormataData ();

    return DT_Movimento_Ordem_Servico;
  }

  public void setDT_Movimento_Ordem_Servico (String DT_Movimento_Ordem_Servico) {
    this.DT_Movimento_Ordem_Servico = DT_Movimento_Ordem_Servico;
  }

  public String getDT_Vencimento () {
    FormataDataBean DataFormatada = new FormataDataBean ();
    DataFormatada.setDT_FormataData (DT_Vencimento);
    DT_Vencimento = DataFormatada.getDT_FormataData ();

    return DT_Vencimento;
  }

  public void setDT_Vencimento (String DT_Vencimento) {
    this.DT_Vencimento = DT_Vencimento;
  }

  public String getDT_Encerramento () {
    FormataDataBean DataFormatada = new FormataDataBean ();
    DataFormatada.setDT_FormataData (DT_Encerramento);
    DT_Encerramento = DataFormatada.getDT_FormataData ();

    return DT_Encerramento;
  }

  public void setDT_Encerramento (String DT_Encerramento) {
    this.DT_Encerramento = DT_Encerramento;
  }

  public long getNR_Documento () {
    return NR_Documento;
  }

  public void setNR_Documento (long NR_Documento) {
    this.NR_Documento = NR_Documento;
  }

  public long getNR_Kilometragem () {
    return NR_Kilometragem;
  }

  public void setNR_Kilometragem (long NR_Kilometragem) {
    this.NR_Kilometragem = NR_Kilometragem;
  }

  public double getNR_Quantidade () {
    return NR_Quantidade;
  }

  public void setNR_Quantidade (double NR_Quantidade) {
    this.NR_Quantidade = NR_Quantidade;
  }

  public double getVL_Movimento () {
    return VL_Movimento;
  }

  public void setVL_Movimento (double VL_Movimento) {
    this.VL_Movimento = VL_Movimento;
  }

  public double getVL_Movimento_Faturado () {
    return VL_Movimento_Faturado;
  }

  public void setVL_Movimento_Faturado (double VL_Movimento_Faturado) {
    this.VL_Movimento_Faturado = VL_Movimento_Faturado;
  }

  public double getVL_Movimento_Pago () {
    return VL_Movimento_Pago;
  }

  public void setVL_Movimento_Pago (double VL_Movimento_Pago) {
    this.VL_Movimento_Pago = VL_Movimento_Pago;
  }

  public double getVL_Total_Frete () {
    return VL_Total_Frete;
  }

  public void setVL_Total_Frete (double VL_Total_Frete) {
    this.VL_Total_Frete = VL_Total_Frete;
  }

  public double getVL_Adiantamento_Viagem () {
    return VL_Adiantamento_Viagem;
  }

  public void setVL_Adiantamento_Viagem (double VL_Adiantamento_Viagem) {
    this.VL_Adiantamento_Viagem = VL_Adiantamento_Viagem;
  }

  public double getVL_Cotacao () {
    return VL_Cotacao;
  }

  public void setVL_Cotacao (double VL_Cotacao) {
    this.VL_Cotacao = VL_Cotacao;
  }

  public double getVL_Documento () {
    return VL_Documento;
  }

  public void setVL_Documento (double VL_Documento) {
    this.VL_Documento = VL_Documento;
  }

  public String getTX_Observacao () {
    return TX_Observacao;
  }

  public void setTX_Observacao (String TX_Observacao) {
    this.TX_Observacao = TX_Observacao;
  }

  public String getDM_Faturado_Pago () {
    return DM_Faturado_Pago;
  }

  public void setDM_Faturado_Pago (String DM_Faturado_Pago) {
    this.DM_Faturado_Pago = DM_Faturado_Pago;
  }

  public int getOID_Tipo_Documento () {
    return oid_Tipo_Documento;
  }

  public void setOID_Tipo_Documento (int n) {
    this.oid_Tipo_Documento = n;
  }

  public int getOID_Conta () {
    return oid_Conta;
  }

  public void setOID_Conta (int n) {
    this.oid_Conta = n;
  }

  public String getNM_Tipo_Documento () {
    return NM_Tipo_Documento;
  }

  public void setNM_Tipo_Documento (String NM_Tipo_Documento) {
    this.NM_Tipo_Documento = NM_Tipo_Documento;
  }

  public String getCD_Tipo_Documento () {
    return CD_Tipo_Documento;
  }

  public void setCD_Tipo_Documento (String CD_Tipo_Documento) {
    this.CD_Tipo_Documento = CD_Tipo_Documento;
  }

  public String getOID_Pessoa () {
    return oid_Pessoa;
  }

  public void setOID_Pessoa (String n) {
    this.oid_Pessoa = n;
  }

  public String getNM_Razao_Social () {
    return NM_Razao_Social;
  }

  public void setNM_Razao_Social (String NM_Razao_Social) {
    this.NM_Razao_Social = NM_Razao_Social;
  }

  public int getOID_Tipo_Servico () {
    return oid_Tipo_Servico;
  }

  public void setOID_Tipo_Servico (int n) {
    this.oid_Tipo_Servico = n;
  }

  public long getOID_Moeda () {
    return oid_Moeda;
  }

  public void setOID_Moeda (long n) {
    this.oid_Moeda = n;
  }

  public int getOID_Estoque () {
    return oid_Estoque;
  }

  public void setOID_Estoque (int n) {
    this.oid_Estoque = n;
  }

  public int getOID_Unidade () {
    return oid_Unidade;
  }

  public void setOID_Unidade (int n) {
    this.oid_Unidade = n;
  }

  public String getNM_Tipo_Servico () {
    return NM_Tipo_Servico;
  }

  public void setNM_Tipo_Servico (String NM_Tipo_Servico) {
    this.NM_Tipo_Servico = NM_Tipo_Servico;
  }

  public String getCD_Tipo_Servico () {
    return CD_Tipo_Servico;
  }

  public void setCD_Tipo_Servico (String CD_Tipo_Servico) {
    this.CD_Tipo_Servico = CD_Tipo_Servico;
  }

  public void setDT_Entrada (String DT_Entrada) {
    this.DT_Entrada = DT_Entrada;
  }

  public String getDT_Entrada () {
    return DT_Entrada;
  }

  public void setVl_Imposto (double Vl_Imposto) {
    this.Vl_Imposto = Vl_Imposto;
  }

  public double getVl_Imposto () {
    return Vl_Imposto;
  }

  public void setPe_Imposto (double Pe_Imposto) {
    this.Pe_Imposto = Pe_Imposto;
  }

  public double getPe_Imposto () {
    return Pe_Imposto;
  }

  public void setOid_Natureza_Operacao (int Oid_Natureza_Operacao) {
    this.Oid_Natureza_Operacao = Oid_Natureza_Operacao;
  }

  public int getOid_Natureza_Operacao () {
    return Oid_Natureza_Operacao;
  }

  /*
   *---------------- Bloco Padrão para Todas Classes ------------------
   */
  public String getUsuario_Stamp () {
    return Usuario_Stamp;
  }

  public void setUsuario_Stamp (String Usuario_Stamp) {
    this.Usuario_Stamp = Usuario_Stamp;
  }

  public String getDt_Stamp () {
    return Dt_Stamp;
  }

  public void setDt_Stamp (String Dt_Stamp) {
    this.Dt_Stamp = Dt_Stamp;
  }

  public String getDm_Stamp () {
    return Dm_Stamp;
  }

  public void setDm_Stamp (String Dm_Stamp) {
    this.Dm_Stamp = Dm_Stamp;
  }

  public long getOID () {
    return oid;
  }

  public void setOID (long n) {
    this.oid = n;
  }

  public double getVL_Desconto() {
  	return VL_Desconto;
  }

  public void setVL_Desconto(double desconto) {
  	VL_Desconto = desconto;
  }

  public String getOid_Nota_Fiscal() {
  	return oid_Nota_Fiscal;
  }

  public void setOid_Nota_Fiscal(String oid_Nota_Fiscal) {
  	this.oid_Nota_Fiscal = oid_Nota_Fiscal;
  }

  public long getOid_Usuario() {
  	return oid_Usuario;
  }

  public void setOid_Usuario(long oid_Usuario) {
  	this.oid_Usuario = oid_Usuario;
  }
  public Movimento_Ordem_ServicoBean insert (HttpServletRequest request , HttpServletResponse response) throws Excecoes {

    // System.out.println ("1");

    String oid_Ordem_Servico = request.getParameter ("oid_Ordem_Servico");
    String oid_Tipo_Servico = request.getParameter ("oid_Tipo_Servico");
    String oid_Moeda = request.getParameter ("oid_Moeda");
    String oid_Tipo_Documento = request.getParameter ("oid_Tipo_Documento");
    String oid_Conta = request.getParameter ("oid_Conta");
    String oid_Natureza_Operacao = request.getParameter ("oid_Natureza_Operacao");
    String oid_Unidade = request.getParameter ("oid_Unidade");
    String oid_Pessoa = request.getParameter ("oid_Pessoa");
    String oid_Veiculo = request.getParameter ("FT_NR_Placa");
    String DT_Movimento_Ordem_Servico = request.getParameter ("FT_DT_Movimento_Ordem_Servico");
    String DT_Vencimento = request.getParameter ("FT_DT_Vencimento");
    String DT_Entrada = request.getParameter ("FT_DT_Entrada");
    String TX_Observacao = request.getParameter ("FT_TX_Observacao");
    String DM_Faturado_Pago = request.getParameter ("FT_DM_Faturado_Pago");
    String Pe_Imposto = request.getParameter ("FT_Pe_Imposto");
    String Vl_Imposto = request.getParameter ("FT_VL_Imposto");
    String VL_Cotacao = request.getParameter ("FT_VL_Cotacao");
    String VL_Movimento = request.getParameter ("FT_VL_Movimento");
    String VL_Documento = request.getParameter ("FT_VL_Documento");
    String VL_Compromisso = request.getParameter ("FT_VL_Compromisso");
    String VL_Unitario = request.getParameter ("FT_VL_Unitario");
    String NR_Kilometragem = request.getParameter ("FT_NR_Kilometragem");
    String NR_Documento = request.getParameter ("FT_NR_Documento");
    String NR_Quantidade = request.getParameter ("FT_NR_Quantidade");
    String oid_Motorista = request.getParameter ("oid_Motorista");
    String TX_Servico = request.getParameter ("FT_TX_Servico");
    String NR_Movimento_Ordem_Servico = request.getParameter ("FT_NR_Movimento_Ordem_Servico");
    String VL_Desconto = request.getParameter ("FT_VL_Desconto");

    if (util.doValida (oid_Ordem_Servico)) {
      setOID_Ordem_Servico (Integer.parseInt (oid_Ordem_Servico));
    }
    if (util.doValida (oid_Tipo_Servico)) {
      setOID_Tipo_Servico (Integer.parseInt (oid_Tipo_Servico));
    }
    if (util.doValida (oid_Moeda)) {
      setOID_Moeda (Integer.parseInt (oid_Moeda));
    }
    if (util.doValida (oid_Tipo_Documento)) {
      setOID_Tipo_Documento (Integer.parseInt (oid_Tipo_Documento));
    }
    if (util.doValida (oid_Conta)) {
      setOID_Conta (Integer.parseInt (oid_Conta));
    }
    if (util.doValida (oid_Natureza_Operacao)) {
      setOid_Natureza_Operacao (Integer.parseInt (oid_Natureza_Operacao));
    }
    if (util.doValida (oid_Unidade)) {
      setOID_Unidade (Integer.parseInt (oid_Unidade));
    }
    if (util.doValida (oid_Pessoa)) {
      setOID_Pessoa (oid_Pessoa);
    }
    if (util.doValida (oid_Veiculo)) {
      setOID_Veiculo (oid_Veiculo);
    }
    if (util.doValida (DT_Movimento_Ordem_Servico)) {
      setDT_Movimento_Ordem_Servico (DT_Movimento_Ordem_Servico);
    }
    if (util.doValida (DT_Vencimento)) {
      setDT_Vencimento (DT_Vencimento);
    }
    if (util.doValida (DT_Entrada)) {
      setDT_Entrada (DT_Entrada);
    }
    if (util.doValida (TX_Observacao)) {
      setTX_Observacao (TX_Observacao);
    }
    if (util.doValida (DM_Faturado_Pago)) {
      setDM_Faturado_Pago (DM_Faturado_Pago);
    }
    if (util.doValida (Pe_Imposto)) {
      setPe_Imposto (Double.parseDouble (Pe_Imposto));
    }
    if (util.doValida (Vl_Imposto)) {
      setVl_Imposto (Double.parseDouble (Vl_Imposto));
    }
    if (util.doValida (VL_Cotacao)) {
      setVL_Cotacao (Double.parseDouble (VL_Cotacao));
    }
    if (util.doValida (VL_Movimento)) {
      setVL_Movimento (Double.parseDouble (VL_Movimento));
    }
    if (util.doValida (VL_Documento)) {
      setVL_Documento (Double.parseDouble (VL_Documento));
    }
    if (util.doValida (VL_Compromisso)) {
      setVL_Compromisso (Double.parseDouble (VL_Compromisso));
    }
    if (util.doValida (VL_Unitario)) {
      setVL_Unitario (Double.parseDouble (VL_Unitario));
    }
    if (util.doValida (NR_Kilometragem)) {
      setNR_Kilometragem (Integer.parseInt (NR_Kilometragem));
    }
    if (util.doValida (NR_Documento)) {
      setNR_Documento (Integer.parseInt (NR_Documento));
    }
    if (util.doValida (NR_Quantidade)) {
      setNR_Quantidade (Double.parseDouble (NR_Quantidade));
    }
    if (util.doValida (oid_Motorista)) {
      setOid_Motorista (oid_Motorista);
    }
    if (util.doValida (TX_Servico)) {
      setTX_Servico (TX_Servico);
    }
    if (util.doValida (NR_Movimento_Ordem_Servico)) {
      setNR_Movimento_Ordem_Servico (Long.parseLong (NR_Movimento_Ordem_Servico));
    }

    if (util.doValida (VL_Desconto)) {
        setVL_Desconto(Double.parseDouble (VL_Desconto));
     }


	  try {
	        if (doExiste(oid_Pessoa, NR_Documento, oid_Tipo_Servico, oid_Veiculo, "", 0)) {
	            throw new Mensagens ("Já existem um Movimento de Orden de Serviço com este número de documento para este fornecedor com o mesmo tipo de serviço!");
	        }
	   }
	   catch (Exception e) {
	     throw new Excecoes (e.getMessage () , e , getClass ().getName () , "");
	   }

    try {
      insert ();
      return this;
    }
    catch (Excecoes e) {
      throw e;
    }
    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "insert(HttpServletRequest request, HttpServletResponse response)");
    }
  }

  public Movimento_Ordem_ServicoBean insert (Movimento_Ordem_ServicoBean ed) throws Excecoes {


    // System.out.println ("Movimento_Ordem_ServicoBean inclui");

    try {
      insert (ed);
      return this;
    }
    catch (Excecoes e) {
      throw e;
    }
    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "insert(HttpServletRequest request, HttpServletResponse response)");
    }
  }

  public void geraMovimentoOrdemServico (String oid_Ordem_Servico) throws Exception {

	  	Parametro_FixoED parametro_FixoED = new Parametro_FixoED();
    	System.out.println("geraMovimentoOrdemServico");

	    this.inicioTransacao ();
	    String cd_tipo_servico="";
	  	ResultSet res = null;
	  	ResultSet resServ = null;
	  	ResultSet resMov = null;
	    try {
	  	  		String sqlBusca =" SELECT  Ordens_Servicos.* " +
						          "  FROM  Ordens_Servicos" +
						          "  WHERE Ordens_Servicos.oid_Ordem_Servico =" + oid_Ordem_Servico;
	        	System.out.println(sqlBusca);
	    	  	res = sql.executarConsulta (sqlBusca);
		        if (res.next ()) {
		        	cd_tipo_servico  = "'" +(res.getString("NM_Servico1")+"          ").substring(0,3).trim()+ "'";
		        	cd_tipo_servico +=",'" +(res.getString("NM_Servico2")+"          ").substring(0,3).trim()+ "'";
		        	cd_tipo_servico +=",'" +(res.getString("NM_Servico3")+"          ").substring(0,3).trim()+ "'";
		        	cd_tipo_servico +=",'" +(res.getString("NM_Servico4")+"          ").substring(0,3).trim()+ "'";
		        	cd_tipo_servico +=",'" +(res.getString("NM_Servico5")+"          ").substring(0,3).trim()+ "'";
		        	cd_tipo_servico +=",'" +(res.getString("NM_Servico6")+"          ").substring(0,3).trim()+ "'";
		        	cd_tipo_servico +=",'" +(res.getString("NM_Servico7")+"          ").substring(0,3).trim()+ "'";
		        	cd_tipo_servico +=",'" +(res.getString("NM_Servico8")+"          ").substring(0,3).trim()+ "'";
		        	cd_tipo_servico +=",'" +(res.getString("NM_Servico9")+"          ").substring(0,3).trim()+ "'";
		        	cd_tipo_servico +=",'" +(res.getString("NM_Servico10")+"          ").substring(0,3).trim()+ "'";
		        	cd_tipo_servico += "'" +(res.getString("NM_Servico11")+"          ").substring(0,3).trim()+ "'";
		        	cd_tipo_servico +=",'" +(res.getString("NM_Servico12")+"          ").substring(0,3).trim()+ "'";
		        	cd_tipo_servico +=",'" +(res.getString("NM_Servico13")+"          ").substring(0,3).trim()+ "'";
		        	cd_tipo_servico +=",'" +(res.getString("NM_Servico14")+"          ").substring(0,3).trim()+ "'";
		        	cd_tipo_servico +=",'" +(res.getString("NM_Servico15")+"          ").substring(0,3).trim()+ "'";
		        	cd_tipo_servico +=",'" +(res.getString("NM_Servico16")+"          ").substring(0,3).trim()+ "'";
		        	cd_tipo_servico +=",'" +(res.getString("NM_Servico17")+"          ").substring(0,3).trim()+ "'";
		        	cd_tipo_servico +=",'" +(res.getString("NM_Servico18")+"          ").substring(0,3).trim()+ "'";
		        	cd_tipo_servico +=",'" +(res.getString("NM_Servico19")+"          ").substring(0,3).trim()+ "'";
		        	cd_tipo_servico +=",'" +(res.getString("NM_Servico20")+"          ").substring(0,3).trim()+ "'";
		        	cd_tipo_servico += "'" +(res.getString("NM_Servico21")+"          ").substring(0,3).trim()+ "'";
		        	cd_tipo_servico +=",'" +(res.getString("NM_Servico22")+"          ").substring(0,3).trim()+ "'";
		        	cd_tipo_servico +=",'" +(res.getString("NM_Servico23")+"          ").substring(0,3).trim()+ "'";
		        	cd_tipo_servico +=",'" +(res.getString("NM_Servico24")+"          ").substring(0,3).trim()+ "'";
		        	cd_tipo_servico +=",'" +(res.getString("NM_Servico25")+"          ").substring(0,3).trim()+ "'";


		        	sqlBusca=" SELECT oid_Tipo_Servico, Vl_Previsto FROM Tipos_Servicos WHERE cd_tipo_servico  IN (" + cd_tipo_servico +")";
		        	System.out.println(sqlBusca);
		    	  	resServ = sql.executarConsulta (sqlBusca);
			        while (resServ.next ()) {
			        	   sqlBusca=  " SELECT  oid_Movimento_Ordem_Servico " +
			        	   			  " FROM 	Movimentos_Ordens_Servicos" +
			        	   			  " WHERE 	oid_Ordem_Servico = " + res.getInt ("oid_Ordem_Servico") +
			        	   			  " AND 	oid_tipo_Servico =  " + resServ.getInt ("oid_tipo_Servico");
				    	  	resMov = sql.executarConsulta (sqlBusca);
					        if (!resMov.next ()) {
					               Movimento_Ordem_ServicoBean movServED = new Movimento_Ordem_ServicoBean ();
					               movServED.setOID_Ordem_Servico (res.getInt ("oid_Ordem_Servico"));
					               movServED.setOID_Tipo_Documento (1);
					               movServED.setOID_Tipo_Servico (resServ.getInt ("oid_tipo_Servico"));
					               movServED.setOID_Pessoa (res.getString("oid_Pessoa_Fornecedor"));
					               movServED.setDT_Entrada (Data.getDataDMY ());
					               movServED.setDT_Movimento_Ordem_Servico (Data.getDataDMY());
					               movServED.setDT_Vencimento (Data.getDataDMY ());
					               movServED.setNR_Documento (999999);
					               movServED.setNR_Quantidade (1);
					               movServED.setTX_Observacao ("Mov Gerado.");
					               movServED.setTX_Servico (" ");
					               movServED.setDM_Faturado_Pago ("P");
					               movServED.setNR_Kilometragem (res.getLong("NR_Kilometragem"));
					               movServED.setOID_Moeda (parametro_FixoED.getOID_Moeda_Padrao ());
					               movServED.setOID_Veiculo (res.getString("OID_Veiculo"));
					               movServED.setOid_Motorista (null);
					               double vl_movimento = resServ.getDouble ("VL_Previsto");
					               if (vl_movimento<=0)vl_movimento=1;
					               movServED.setVL_Documento (vl_movimento);
					               movServED.setVL_Movimento (vl_movimento);
					               movServED.setDM_Custo_Fixo("N");
					               movServED.insert ();
					        }
			        }
		        }
		  }  catch (SQLException e) {
				   throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "lista(HttpServletRequest request)");
		  }
	      finally {
			   this.fimTransacao (false);
	      }
  }



  public void insert () throws Exception {
	  this.inicioTransacao ();
      this.executasql = this.sql;
      this.inclui_Movimento(executasql);
      this.fimTransacao (true);
  }


  public void inclui_Movimento (ExecutaSQL executasql) throws Exception {

	    	System.out.println ("inclui_Movimento");

	    try {
	      String sql1 = null;
	      String chave = null;
	      String dt_encerramento=null;
	      int OID_Movimento_Ordem_Servico = 0;
	      ResultSet rs2 = null;


	      if ((getOID_Veiculo() == null || getOID_Veiculo().length()<=4) && getOID_Ordem_Servico ()>0) {
	          sql1 = " SELECT oid_Veiculo, nr_kilometragem FROM Ordens_Servicos WHERE oid_Ordem_Servico=" + getOID_Ordem_Servico ();
	          ResultSet rs = this.executasql.executarConsulta (sql1);
	          if (rs.next ()) {
	        	  setOID_Veiculo(rs.getString("oid_Veiculo"));
	        	  if (getNR_Kilometragem () <=0) {
	        		  setNR_Kilometragem(rs.getLong("nr_kilometragem"));
	        	  }
	          }
	      }
	      if ((getOID_Veiculo() == null || getOID_Veiculo().length()<=4)) {
	          throw new Mensagens ("Veiculo Nao Localizado!");
	      }

	      sql1 = " SELECT MAX(OID_MOVIMENTO_ORDEM_SERVICO) AS OID FROM Movimentos_Ordens_Servicos ";
	      ResultSet rs = executasql.executarConsulta (sql1);
	      if (rs.next ()) {
	        OID_Movimento_Ordem_Servico = rs.getInt ("OID") + 1;
	      }
	      setOID (OID_Movimento_Ordem_Servico);

	      // System.out.println ("4 b");
	      if (!"S".equals(getDM_Custo_Fixo())){
	        sql1 = " SELECT Ordens_Servicos.DT_Encerramento FROM Ordens_Servicos WHERE Ordens_Servicos.oid_Ordem_Servico = " + this.oid_Ordem_Servico;
	        rs = executasql.executarConsulta (sql1);
	        try {
	          if (rs.next ()) {
	            if (rs.getDate ("DT_Encerramento") != null) {
	              ///throw new Mensagens ("Ordem de Serviço já Encerrada!");
	            }
	          }
	        }
	        finally {
	          util.closeResultset (rs);
	        }
	      }

	      sql1 = " SELECT Tipos_Servicos.DM_Encerramento " +
		   		 " FROM Tipos_Servicos " +
		   		 " WHERE Tipos_Servicos.oid_Tipo_Servico = " +  getOID_Tipo_Servico ();
		   rs = executasql.executarConsulta (sql1);
		   try {
		       if (rs.next ()) {
		         if ("A".equals(rs.getString ("DM_Encerramento"))) {  //Encerramento automatico da OS
			    	dt_encerramento=Data.getDataDMY();
		         	sql1=" UPDATE Ordens_Servicos SET DM_Finalizada='F', DT_Encerramento='" + dt_encerramento + "' WHERE oid_Ordem_Servico=" + getOID_Ordem_Servico ();
		   	        executasql.executarUpdate (sql1);
		         }
		       }
		   }
		   finally {
		       util.closeResultset (rs);
		   }


	      if (getOID_Ordem_Servico() ==1) {
	    	  dt_encerramento=Data.getDataDMY();
	      }

	      if (getNR_Movimento_Ordem_Servico () <= 0) {
	        setNR_Movimento_Ordem_Servico (getOID ());
	      }

	      if (getVL_Documento ()==0) {
	    	  setVL_Documento (getVL_Movimento());
	      }

	      sql1 = " INSERT INTO Movimentos_Ordens_Servicos " +
	          " (OID_Movimento_Ordem_Servico," +
	          "  oid_Ordem_Servico," +
	          "  oid_Tipo_Documento," +
	          "  oid_Tipo_Servico," +
	          "  oid_Pessoa," +
	          "  DT_Movimento_Ordem_Servico," +
	          "  DT_Encerramento," +
	          "  DT_Vencimento," +
	          "  NR_Documento," +
	          "  NR_Quantidade," +
	          "  VL_Movimento," +
	          "  TX_Observacao," +
	          "  TX_Servico," +
	          "  DM_Faturado_Pago," +
	          "  NR_Kilometragem," +
	          "  oid_Estoque," +
	          "  oid_item_solicitacao_entrega," +
	          "  oid_Moeda," +
	          "  oid_Usuario," +
	          "  VL_Cotacao," +
	          "  VL_Documento," +
	          "  OID_Veiculo," +
	          "  VL_Unitario, " +
	          "  VL_Desconto, " +
	          "  oid_Motorista, " +
	          "  DM_Custo_Fixo, " +
	          "  NR_Movimento_Ordem_Servico)" +
	          "  VALUES ";

	      sql1 += "(" + getOID () + "," +
	          getOID_Ordem_Servico () + "," +
	          getOID_Tipo_Documento () + "," +
	          getOID_Tipo_Servico () + ",'" +
	          getOID_Pessoa () + "','" +
	          this.DT_Movimento_Ordem_Servico + "',";
	      sql1 += dt_encerramento == null ? "null," : "'" + dt_encerramento + "',";
	      sql1 += this.DT_Vencimento == null ? "null," : "'" + this.DT_Vencimento + "',";
	      sql1 += getNR_Documento () + "," +
	          getNR_Quantidade () + "," +
	          getVL_Movimento () + ",'" +
	          getTX_Observacao () + "','" +
	          getTX_Servico () + "','" +
	          getDM_Faturado_Pago () + "'," +
	          getNR_Kilometragem () + "," +
	          getOID_Estoque () + "," +
	          getOid_Item_Solicitacao_Entrega() + "," +
	          getOID_Moeda () + "," +
	          getOid_Usuario () + "," +
	          getVL_Cotacao () + "," +
	          getVL_Documento () + ",'" +
	          getOID_Veiculo () + "'," +
	          getVL_Unitario () + ", " +
	          getVL_Desconto() + ", " +
	          util.getSQLString (getOid_Motorista ()) + ", " +
	          util.getSQLString (getDM_Custo_Fixo ()) + ", " +
	          getNR_Movimento_Ordem_Servico () + ")";

	      System.out.println ("5=>" + sql1);

	      executasql.executarUpdate (sql1);



	      if (1==1) {//demais rotinas do mov os

	    	  sql1 =" SELECT Vencimentos_Veiculos.oid_vencimento_veiculo, Tipos_Vencimentos.NR_Dias " +
	    	  		" FROM   Vencimentos_Veiculos, Tipos_Vencimentos" +
	    	  		" WHERE Vencimentos_Veiculos.oid_tipo_vencimento = Tipos_Vencimentos.oid_tipo_vencimento" +
	    	  		" AND   Vencimentos_Veiculos.oid_Veiculo='" +  getOID_Veiculo () + "'" +
	    	  		" AND   Tipos_Vencimentos.oid_Tipo_Servico=" +  getOID_Tipo_Servico ();
		      System.out.println ("5=>" + sql1);
		      rs = executasql.executarConsulta (sql1);
		      try {
		        if (rs.next ()) {
		        	sql1=" UPDATE Vencimentos_Veiculos SET DT_Vencimento='" + Data.getSomaDiaData(DT_Movimento_Ordem_Servico, rs.getInt("NR_Dias")) + "'" +
		        	     " WHERE Vencimentos_Veiculos.oid_vencimento_veiculo=" + rs.getLong("oid_vencimento_veiculo");
				      System.out.println ("N=>" + sql1);
			          executasql.executarUpdate (sql1);

			        sql1 = " INSERT INTO Movimentos_Vencimentos_Veiculos (oid_movimentos_vencimentos_veiculos, oid_movimento_ordem_servico, oid_vencimento_veiculo, oid_veiculo)  " +
			        	   " VALUES ('" + String.valueOf (System.currentTimeMillis ()).toString () + "', " + OID_Movimento_Ordem_Servico  + ", " + rs.getLong("oid_vencimento_veiculo") + ", '" + getOID_Veiculo ()+ "')";
				      System.out.println ("N=>" + sql1);
				      executasql.executarUpdate (sql1);

		        }
		      }
		      finally {
		        util.closeResultset (rs);
		      }

		      if (getDM_Faturado_Pago ().equals ("E") && Parametro_FixoED.getInstancia ().getOID_Tipo_Servico_Abastecimento()==getOID_Tipo_Servico ()){
		        	  new Odometros_VeiculosBD(executasql).inclui(getOID_Veiculo (), "",  "abastecimento", getOid_Usuario () , getNR_Kilometragem (), 0);
		      }else {
		        	  new Odometros_VeiculosBD(executasql).inclui(getOID_Veiculo (), "",  "ordem_servico", getOid_Usuario () , getNR_Kilometragem (), 0);
		      }



		      int NR_Kilometragem_Servico=10000;
		      int oid_Servico_Preventivo=0;

		      // System.out.println ("NR_Kilometragem_Servico=>" + NR_Kilometragem_Servico);

		      sql1 = " SELECT Manutencoes_Preventivas.*, " +
		      		 "        Veiculos.NR_Kilometragem_Atual, " +
		      		 "        Veiculos.oid_modelo_veiculo  " +
			          " FROM  Manutencoes_Preventivas, Veiculos " +
			          " WHERE Manutencoes_Preventivas.oid_Veiculo = Veiculos.oid_Veiculo " +
			          "   AND Manutencoes_Preventivas.oid_Veiculo ='" + getOID_Veiculo () + "'" +
			          "   AND Manutencoes_Preventivas.oid_Tipo_Servico =" + getOID_Tipo_Servico ();

		      rs = executasql.executarConsulta (sql1);
		      try {
		        while (rs.next ()) {


		          sql1 = " SELECT Servicos_Preventivos.oid_Servico_Preventivo, Servicos_Preventivos.NR_Kilometragem_Servico  " +
			              " FROM      Servicos_Preventivos  " +
			              " WHERE     Servicos_Preventivos.oid_Tipo_Servico =" + getOID_Tipo_Servico () +
			              "   AND     Servicos_Preventivos.oid_Modelo_Veiculo = " + rs.getLong ("oid_modelo_veiculo");

		      // System.out.println ("NR_Kilometragem_Servico=>" + sql1);

		          rs2 = executasql.executarConsulta (sql1);
		          if (rs2.next ()) {
		            oid_Servico_Preventivo = rs2.getInt ("oid_Servico_Preventivo");
		            NR_Kilometragem_Servico = rs2.getInt ("NR_Kilometragem_Servico");
		          }
		      // System.out.println ("NR_Kilometragem_Servico=>" + NR_Kilometragem_Servico);

		          sql1 =  "  UPDATE  Manutencoes_Preventivas SET " +
			              "  NR_Kilometragem_Prevista_Anterior= " + rs.getLong ("NR_Kilometragem_Prevista")  +
			              " ,NR_Kilometragem_Realizada_Anterior= " + rs.getLong ("NR_Kilometragem_Realizada");
		          		  if (rs.getString ("DT_Servico") !=null && rs.getString ("DT_Servico").length()>4) {
		          			sql1+= " ,DT_Servico_Anterior='" + rs.getString ("DT_Servico")  + "'";
		          		  }
		          sql1+=  " ,oid_Servico_Preventivo_Anterior= " + rs.getLong ("oid_Servico_Preventivo") +
			              " ,OID_Movimento_Ordem_Servico_Anterior= " + rs.getLong ("OID_Movimento_Ordem_Servico") +
		          		  " ,NR_Kilometragem_Prevista= " + (getNR_Kilometragem () + NR_Kilometragem_Servico) +
			              " ,NR_Kilometragem_Realizada= " + (getNR_Kilometragem ()) +
			              " ,DT_Servico='" + this.DT_Movimento_Ordem_Servico + "'" +
			              " ,oid_Servico_Preventivo= " + oid_Servico_Preventivo +
			              " ,OID_Movimento_Ordem_Servico= " + getOID () +
			              "  WHERE oid_Manutencao_Preventiva= " + rs.getString ("oid_Manutencao_Preventiva") +
			              "  AND   oid_Veiculo ='" + getOID_Veiculo () + "'";
		          executasql.executarUpdate (sql1);
		        }
		      }
		      finally {
		        util.closeResultset (rs);
		      }

		      // System.out.println ("7");

		      if (this.getDM_Faturado_Pago ().equals ("E")) {
		        ResultSet cursor =
		            executasql.executarConsulta ("SELECT MAX(OID_Movimento_Estoque) FROM Movimentos_Estoques");
		        try {
		          long oid = 0;
		          while (cursor.next ()) {
		            oid = cursor.getLong (1);
		            oid++;
		            setOID (oid);
		          }
		        }
		        finally {
		          util.closeResultset (cursor);
		        }

		        sql1 = " INSERT INTO Movimentos_Estoques (OID_Movimento_Estoque, OID_Estoque, DT_Movimento, NR_Quantidade, VL_Unitario, Dt_Stamp, Usuario_Stamp, Dm_Stamp, DM_Movimento, DM_Destino, NM_Destino, oid_ordem_servico, oid_Movimento_Ordem_Servico, oid_Usuario, oid_veiculo, DT_Ordem)  ";
		        sql1 += "VALUES (" + oid + ", " + this.oid_Estoque + ", '" + this.DT_Movimento_Ordem_Servico + "' , " + this.NR_Quantidade + " , " + getVL_Movimento () + " , '" + this.DT_Movimento_Ordem_Servico + "','','','S','R','REQ' , " + this.oid_Ordem_Servico + ", " + OID_Movimento_Ordem_Servico  + ", " + getOid_Usuario() + ", '" + getOID_Veiculo ()+ "', '" + String.valueOf (Data.getDataYMD()+Data.getHoraHM()+Data.getMileSegundo())+ "')";

		        System.out.println ("N=>" + sql1);

		        executasql.executarUpdate (sql1);
		      }

		      // System.out.println ("8");

		      if (getVL_Compromisso ()>0 && this.getDM_Faturado_Pago ().equals ("F") && "S".equals (Parametro_FixoED.getInstancia ().getDM_Gera_Compromisso_Movimento_Ordem_Servico ())) {
		        Compromisso_ServicoED Compromisso_ServicoED = new Compromisso_ServicoED ();
		        Indice_FinanceiroBean ind = new Indice_FinanceiroBean ();
		        CompromissoED compromissoED = new CompromissoED ();
		        CompromissoED compromissoVoltaED = new CompromissoED ();
		        CompromissoBD compromissoBD = new CompromissoBD (this.sql);

		        double VL_Movimento = getVL_Compromisso ();

		        if (getOID_Moeda () > 1) { // 1 = padrao real
		          ind = Indice_FinanceiroBean.getByCotacao (1 , this.DT_Entrada);
		          if (ind.getVL_Cotacao () > 0) {
		            VL_Movimento = VL_Movimento / getVL_Cotacao () * ind.getVL_Cotacao ();
		          }
		        }

		        compromissoED.setOid_Pessoa (this.getOID_Pessoa ());
		        compromissoED.setNr_Parcela (new Integer ("1"));

		        compromissoED.setDM_Tipo_Pagamento ("1");

		        compromissoED.setVl_Compromisso (new Double (VL_Movimento));
		        compromissoED.setVl_Saldo (new Double (VL_Movimento));
		        compromissoED.setVl_Desconto_Ate_Vencimento (new Double ("0"));
		        compromissoED.setVl_Juro_Mora_dia (new Double ("0"));
		        compromissoED.setVl_Taxa_Banco (new Double ("0"));
		        compromissoED.setVl_Multa_Apos_Vencimento (new Double ("0"));
		        compromissoED.setDt_Emissao (this.DT_Movimento_Ordem_Servico);
		        compromissoED.setNr_Documento (String.valueOf (this.getNR_Documento ()));
		        compromissoED.setOid_Tipo_Documento (new Integer (this.getOID_Tipo_Documento ()));
		        compromissoED.setTx_Observacao ("");
		        compromissoED.setOid_Natureza_Operacao (new Integer (this.getOid_Natureza_Operacao ()));
		        compromissoED.setDt_Entrada (this.DT_Entrada);
		        compromissoED.setVl_Imposto (new Double (this.getVl_Imposto ()));
		        compromissoED.setPe_Imposto (new Double (this.getPe_Imposto ()));

		        compromissoED.setOid_Centro_Custo (new Integer (Parametro_FixoED.getInstancia ().getOID_Centro_Custo_Movimento_Ordem_Servico ()));

		        compromissoED.setOID_Movimento_Ordem_Servico (new Integer (OID_Movimento_Ordem_Servico));

		        // System.out.println ("OID_Movimento_Ordem_Servico->>>>>>>>>>>" + OID_Movimento_Ordem_Servico);

		        compromissoED.setOid_Conta (new Integer (this.getOID_Conta ()));

		        compromissoED.setOID_Usuario(getOid_Usuario());

		        compromissoED.setOid_Conta_Credito (new Integer (Parametro_FixoED.getInstancia ().getOID_Conta_Credito_Movimento_Ordem_Servico ()));

		        compromissoED.setOid_Unidade (new Long (this.getOID_Unidade ()));
		        compromissoED.setDt_Vencimento (this.DT_Vencimento);

		        compromissoVoltaED = compromissoBD.inclui (compromissoED);
		        compromissoED.setOid_Compromisso (compromissoVoltaED.getOid_Compromisso ());

		        chave = String.valueOf (this.getOID ()) + String.valueOf (compromissoED.getOid_Compromisso ());
		        Compromisso_ServicoED.setOID_Compromisso_Servico (chave);
		        Compromisso_ServicoED.setDT_Emissao (Parametro_FixoED.getInstancia ().getDT_Hoje ());
		        Compromisso_ServicoED.setHR_Compromisso_Servico (Parametro_FixoED.getInstancia ().getHR_Hoje ());
		        Compromisso_ServicoED.setDT_Compromisso_Servico (Data.getDataDMY ());
		        Compromisso_ServicoED.setOID_Compromisso (compromissoED.getOid_Compromisso ());
		        Compromisso_ServicoED.setOID_Ordem_Frete (String.valueOf (this.getOID ()));
		        Compromisso_ServicoED.setDt_stamp (this.getDt_Stamp ());
		        Compromisso_ServicoED.setUsuario_Stamp (this.getUsuario_Stamp ());
		        Compromisso_ServicoED.setDm_Stamp (this.getDm_Stamp ());

		        sql1 = " insert into Compromissos_Servicos (oid_compromisso_servico, oid_compromisso, oid_movimento_ordem_servico, dt_compromisso_servico, hr_compromisso_servico) values ";
		        sql1 += "('" + Compromisso_ServicoED.getOID_Compromisso_Servico () + "'," + Compromisso_ServicoED.getOID_Compromisso () + "," + this.getOID () + ",'" + Compromisso_ServicoED.getDT_Compromisso_Servico () + "','" + Compromisso_ServicoED.getHR_Compromisso_Servico () + "')";

		        executasql.executarUpdate (sql1);
		      }
	      }
	      System.out.println ("10");

	    }
	    catch (SQLException e) {
	      //faz rollback pois deu algum erro
	      this.abortaTransacao ();

	      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "insert()");
	    }
	  }


  public void update (HttpServletRequest request , HttpServletResponse response) throws Excecoes {
    String oid_Movimento_Ordem_Servico = request.getParameter ("oid_Movimento_Ordem_Servico");
    String oid_Ordem_Servico = request.getParameter ("oid_Ordem_Servico");
    String oid_Tipo_Servico = request.getParameter ("oid_Tipo_Servico");
    String oid_Moeda = request.getParameter ("oid_Moeda");
    String oid_Tipo_Documento = request.getParameter ("oid_Tipo_Documento");
    String oid_Pessoa = request.getParameter ("oid_Pessoa");
    String DT_Movimento_Ordem_Servico = request.getParameter ("FT_DT_Movimento_Ordem_Servico");
    String TX_Observacao = request.getParameter ("FT_TX_Observacao");
    String DM_Faturado_Pago = request.getParameter ("FT_DM_Faturado_Pago");
    String VL_Unitario = request.getParameter ("FT_VL_Unitario");
    String VL_Movimento = request.getParameter ("FT_VL_Movimento");
    String VL_Cotacao = request.getParameter ("FT_VL_Cotacao");
    String VL_Documento = request.getParameter ("FT_VL_Documento");
    String NR_Kilometragem = request.getParameter ("FT_NR_Kilometragem");
    String NR_Documento = request.getParameter ("FT_NR_Documento");
    String NR_Quantidade = request.getParameter ("FT_NR_Quantidade");
    String oid_Veiculo = request.getParameter ("oid_Veiculo");
    String oid_Motorista = request.getParameter ("oid_Motorista");
    String TX_Servico = request.getParameter ("FT_TX_Servico");

    String VL_Desconto = request.getParameter ("FT_VL_Desconto");

    if (oid_Movimento_Ordem_Servico != null && oid_Movimento_Ordem_Servico.length () > 0) {
      int b = (new Integer (oid_Movimento_Ordem_Servico)).intValue ();
      setOID (b);
    }
    if (oid_Ordem_Servico != null && oid_Ordem_Servico.length () > 0) {
      int b = (new Integer (oid_Ordem_Servico)).intValue ();
      setOID_Ordem_Servico (b);
    }
    if (oid_Tipo_Servico != null && oid_Tipo_Servico.length () > 0) {
      int b = (new Integer (oid_Tipo_Servico)).intValue ();
      setOID_Tipo_Servico (b);
    }
    if (oid_Moeda != null && oid_Moeda.length () > 0) {
      int b = (new Integer (oid_Moeda)).intValue ();
      setOID_Moeda (b);
    }
    if (oid_Tipo_Documento != null && oid_Tipo_Documento.length () > 0) {
      int b = (new Integer (oid_Tipo_Documento)).intValue ();
      setOID_Tipo_Documento (b);
    }
    if (oid_Pessoa != null && oid_Pessoa.length () > 0) {
      setOID_Pessoa (oid_Pessoa);
    }
    if (DT_Movimento_Ordem_Servico != null && DT_Movimento_Ordem_Servico.length () > 0) {
      setDT_Movimento_Ordem_Servico (DT_Movimento_Ordem_Servico);
    }
    if (TX_Observacao != null && TX_Observacao.length () > 0) {
      setTX_Observacao (TX_Observacao);
    }
    if (DM_Faturado_Pago != null && DM_Faturado_Pago.length () > 0) {
      setDM_Faturado_Pago (DM_Faturado_Pago);
    }
    if (util.doValida (VL_Unitario)) {
      setVL_Unitario (Double.parseDouble (VL_Unitario));
    }
    if (VL_Movimento != null && VL_Movimento.length () > 0) {
      setVL_Movimento (new Double (VL_Movimento).doubleValue ());
    }
    if (VL_Cotacao != null && VL_Cotacao.length () > 0) {
      setVL_Cotacao (new Double (VL_Cotacao).doubleValue ());
    }
    if (VL_Documento != null && VL_Documento.length () > 0) {
      setVL_Documento (new Double (VL_Documento).doubleValue ());
    }
    setNR_Kilometragem (0);
    if (NR_Kilometragem != null && NR_Kilometragem.length () > 0) {
      setNR_Kilometragem (new Integer (NR_Kilometragem).intValue ());
    }
    if (NR_Documento != null && NR_Documento.length () > 0) {
      setNR_Documento (new Integer (NR_Documento).intValue ());
    }
    if (NR_Quantidade != null && NR_Quantidade.length () > 0) {
      setNR_Quantidade (new Double (NR_Quantidade).doubleValue ());
    }
    if (oid_Veiculo != null && oid_Veiculo.length () > 0) {
      setOID_Veiculo (oid_Veiculo);
    }
    if (util.doValida (oid_Motorista)) {
      setOid_Motorista (oid_Motorista);
    }
    if (util.doValida (TX_Servico)) {
      setTX_Servico (TX_Servico);
    }

    if (util.doValida (VL_Desconto)) {
        setVL_Desconto(Double.parseDouble (VL_Desconto));
    }

    try {
	        if (doExiste(oid_Pessoa, NR_Documento, oid_Tipo_Servico, oid_Veiculo, DT_Movimento_Ordem_Servico, new Long (NR_Kilometragem).longValue())) {
	            throw new Mensagens ("Já existem um Movimento de Serviço registrado com estes dados.");
	        }
	   }
	   catch (Exception e) {
	     throw new Excecoes (e.getMessage () , e , getClass ().getName () , "");
	 }


    try {
      update ();
    }
    catch (Excecoes e) {
      throw e;
    }
    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , getClass ().getName () , "update(HttpServletRequest request, HttpServletResponse response)");
    }
  }

  public void update () throws Exception {
    inicioTransacao ();
    try {
      executasql = sql;
      if (oid_Ordem_Servico>1) {
	      StringBuffer buff = new StringBuffer ();
	      buff.append ("SELECT Ordens_Servicos.DT_Encerramento ");
	      buff.append ("FROM Ordens_Servicos ");
	      buff.append (" WHERE Ordens_Servicos.oid_Ordem_Servico = ");
	      buff.append (this.oid_Ordem_Servico);
	      ResultSet cursor = executasql.executarConsulta (buff.toString ());
	      try {
	        while (cursor.next ()) {
	          setDT_Encerramento (cursor.getString (1));
	        }
	      }
	      finally {
	        util.closeResultset (cursor);
	      }

	      if (this.DT_Encerramento != null) {
	        return;
	      }
      }
      String sql =
          " UPDATE Movimentos_Ordens_Servicos SET " +
          "  OID_Tipo_Documento = " + getOID_Tipo_Documento () +
          " ,OID_Tipo_Servico = " + getOID_Tipo_Servico () +
          " ,OID_Pessoa = " + util.getSQLString (getOID_Pessoa ()) +
          " ,DT_Movimento_Ordem_Servico = " + util.getSQLString (FormataData.formataDataTB (getDT_Movimento_Ordem_Servico ().toString ()).toString ()) +
          " ,NR_Documento = '" + getNR_Documento () + "'" +
          " ,NR_Quantidade = " + getNR_Quantidade () +
          " ,VL_Movimento = " + getVL_Movimento () +
          " ,TX_Observacao = " + util.getSQLString (getTX_Observacao ()) +
          " ,DM_Faturado_Pago = " + util.getSQLString (getDM_Faturado_Pago ()) +
          " ,NR_Kilometragem = " + getNR_Kilometragem () +
          " ,OID_Estoque = " + getOID_Estoque () +
          " ,OID_Moeda = " + getOID_Moeda () +
          " ,VL_Cotacao = " + getVL_Cotacao () +
          " ,VL_Documento = " + getVL_Documento () +
          " ,VL_Desconto = " + getVL_Desconto() +
          " ,OID_Veiculo = " + util.getSQLString (getOID_Veiculo ()) +
          " ,VL_Unitario = " + getVL_Unitario () +
          " ,TX_Servico = " + util.getSQLString (getTX_Servico ()) +
          " ,oid_Motorista = " + util.getSQLString (getOid_Motorista ()) +
          " ,NR_Movimento_Ordem_Servico = " + getNR_Movimento_Ordem_Servico () +
          " WHERE OID_Movimento_Ordem_Servico = " + getOID ();
      executasql.executarUpdate (sql);
      fimTransacao (true);
    }
    catch (Exception e) {
      abortaTransacao ();
      throw e;
    }
  }

  public void updateSimples () throws Exception {
	    inicioTransacao ();
	    try {
	      executasql = sql;

	      String sql =
	          " UPDATE Movimentos_Ordens_Servicos SET " +
	          "  DT_Movimento_Ordem_Servico = " + util.getSQLString (FormataData.formataDataTB (getDT_Movimento_Ordem_Servico ().toString ()).toString ()) +
	          " ,NR_Documento = " + getNR_Documento () +
	          " ,NR_Quantidade = " + getNR_Quantidade () +
	          " ,TX_Observacao = " + util.getSQLString (getTX_Observacao ()) +
	          " ,NR_Kilometragem = " + getNR_Kilometragem () +
	          " ,TX_Servico = " + util.getSQLString (getTX_Servico ()) +
	          " WHERE OID_Movimento_Ordem_Servico = " + getOID ();
	      executasql.executarUpdate (sql);
	      fimTransacao (true);
	    }
	    catch (Exception e) {
	      abortaTransacao ();
	      throw e;
	    }
	  }

  public void delete () throws Exception {
    inicioTransacao ();
    try {

    	System.out.println("delete Mvo1");

      executasql = sql;

  	  String sql = " SELECT oid_movimento_ordem_servico " +
  	  			   " FROM   movimentos_ordens_servicos, acertos " +
  	  			   " WHERE  movimentos_ordens_servicos.oid_ordem_servico = acertos.oid_ordem_servico " +
  	  			   " AND    movimentos_ordens_servicos.OID_Movimento_Ordem_Servico = " + getOID();

	   ResultSet res = this.executasql.executarConsulta (sql);
	   boolean existe = false;
	   if (res.next()){
		   return;
	   }


       sql =  "  UPDATE  Manutencoes_Preventivas SET " +
		      "  NR_Kilometragem_Prevista= NR_Kilometragem_Prevista_Anterior " +
		      " ,NR_Kilometragem_Realizada= NR_Kilometragem_Realizada_Anterior " +
		      " ,DT_Servico= DT_Servico_Anterior " +
		      " ,oid_Servico_Preventivo= oid_Servico_Preventivo_Anterior "+
		      " ,OID_Movimento_Ordem_Servico= OID_Movimento_Ordem_Servico_Anterior " +
		      "  WHERE OID_Movimento_Ordem_Servico= " +  getOID();
		System.out.println(sql);
		executasql.executarUpdate (sql);


	  sql =" UPDATE Pedagios SET oid_Movimento_Ordem_Servico=0, oid_Acerto=0 , DM_Situacao='P'  WHERE oid_Movimento_Ordem_Servico=" + getOID();
		System.out.println(sql);
		executasql.executarUpdate(sql);

	  sql = "delete from Movimentos_Ordens_Servicos where OID_Movimento_Ordem_Servico = " + getOID ();
	  	executasql.executarUpdate (sql);

      sql = "DELETE FROM Movimentos_Estoques WHERE OID_Movimento_Ordem_Servico=" + getOID ();
      	executasql.executarUpdate (sql);

    }
    finally {
      fimTransacao (true);
    }
  }

  public void exclui () throws Exception {
	    inicioTransacao ();
	    try {
	      executasql = sql;

	      String sqlDelete = "delete from Movimentos_Ordens_Servicos where OID_Movimento_Ordem_Servico = " + getOID ();
	      sql.executarUpdate (sqlDelete);
	      sqlDelete = "DELETE FROM Movimentos_Estoques WHERE OID_Movimento_Ordem_Servico=" + getOID ();
	      sql.executarUpdate (sqlDelete);

	    }
	    finally {
	      fimTransacao (true);
	    }
	  }

  public void retiraAbastecimento (long oid_Movimento_Ordem_Servico) throws Exception {
    inicioTransacao ();
    try {
      String sqlDelete = " UPDATE Movimentos_Ordens_Servicos SET Oid_Ordem_Servico=Oid_Ordem_Servico_Anterior, DM_Acerto=null where OID_Movimento_Ordem_Servico = " + oid_Movimento_Ordem_Servico;
      // System.out.println(sqlDelete);
      executasql = sql;
      sql.executarUpdate (sqlDelete);
    }
    finally {
      fimTransacao (true);
    }
  }

  public void retiraEstoque (long oid_Movimento_Ordem_Servico) throws Exception {
    inicioTransacao ();
    try {

      String sqlDelete = " UPDATE Movimentos_Ordens_Servicos SET Oid_Ordem_Servico=Oid_Ordem_Servico_Anterior, DM_Acerto=null where OID_Movimento_Ordem_Servico = " + oid_Movimento_Ordem_Servico;
      // System.out.println(sqlDelete);
      executasql = sql;
      sql.executarUpdate (sqlDelete);

      sqlDelete = " UPDATE Movimentos_Estoques SET Oid_Ordem_Servico=Oid_Ordem_Servico_Anterior, DM_Acerto=null where OID_Movimento_Ordem_Servico = " + oid_Movimento_Ordem_Servico;
      executasql = sql;
      sql.executarUpdate (sqlDelete);

    }
    finally {
      fimTransacao (true);
    }
  }



  public static final Movimento_Ordem_ServicoBean getByOID (long oid_Movimento_Ordem_Servico) throws Exception {
    /*
     * Abre a conexão com o banco
     */

    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Ordem_Servico do DSN
      // o NM_Ordem_Servico de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    Movimento_Ordem_ServicoBean p = new Movimento_Ordem_ServicoBean ();

    try {

	    String sqlBusca =
	        " SELECT Movimentos_Ordens_Servicos.* ," +
	        "        Tipos_Servicos.NM_Tipo_Servico, " +
	        "        Tipos_Servicos.CD_Tipo_Servico, " +
	        "        Tipos_Documentos.NM_Tipo_Documento, " +
	        "        Tipos_Documentos.CD_Tipo_Documento " +
	        " FROM Movimentos_Ordens_Servicos, Tipos_Servicos, Tipos_Documentos " +
	        " WHERE Movimentos_Ordens_Servicos.oid_Tipo_Documento = Tipos_Documentos.oid_Tipo_Documento " +
	        "   AND Movimentos_Ordens_Servicos.oid_Tipo_Servico   = Tipos_Servicos.oid_Tipo_Servico  " +
		    "   AND Movimentos_Ordens_Servicos.oid_Movimento_Ordem_Servico = " + oid_Movimento_Ordem_Servico ;

      Statement stmt = conn.createStatement ();
      Statement stmt2 = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (sqlBusca);

      while (cursor.next ()) {
        p.setOID (cursor.getLong ("OID_Movimento_Ordem_Servico"));
        p.setOID_Ordem_Servico (cursor.getInt ("OID_Ordem_Servico"));
        p.setOID_Tipo_Documento (cursor.getInt ("OID_Tipo_Documento"));
        p.setOID_Tipo_Servico (cursor.getInt ("OID_Tipo_Servico"));
        p.setOID_Pessoa (cursor.getString ("OID_Pessoa"));
        p.setDT_Movimento_Ordem_Servico (cursor.getString ("DT_Movimento_Ordem_Servico"));
        p.setNR_Documento (cursor.getLong ("NR_Documento"));
        p.setNR_Quantidade (cursor.getDouble ("NR_Quantidade"));
        p.setVL_Movimento (cursor.getDouble ("VL_Movimento"));
        p.setTX_Observacao (" ");
        if (cursor.getString ("TX_Observacao") != null && cursor.getString ("TX_Observacao").length()>4) {
        	p.setTX_Observacao (cursor.getString ("TX_Observacao"));
        }
        p.setDM_Faturado_Pago (cursor.getString ("DM_Faturado_Pago"));
        p.setNR_Kilometragem (cursor.getLong ("NR_Kilometragem"));
        p.setNM_Tipo_Documento (cursor.getString ("NM_Tipo_Documento"));
        p.setCD_Tipo_Documento (cursor.getString ("CD_Tipo_Documento"));
        p.setOID_Estoque (cursor.getInt ("OID_Estoque"));
        p.setOID_Moeda (cursor.getLong ("OID_Moeda"));
        p.setVL_Cotacao (cursor.getDouble ("VL_Cotacao"));
        p.setVL_Documento (cursor.getDouble ("VL_Documento"));
        p.setDT_Vencimento (cursor.getString ("DT_Vencimento"));
        p.setOID_Veiculo (cursor.getString ("OID_Veiculo"));
        p.setVL_Unitario (cursor.getDouble ("VL_Unitario"));
        p.setVL_Desconto(cursor.getDouble ("VL_Desconto"));
        p.setTX_Servico (cursor.getString ("TX_Servico"));
        p.setDM_Acerto (cursor.getString ("DM_Acerto"));
        p.setOid_Motorista (cursor.getString ("oid_Motorista"));
        p.setNR_Movimento_Ordem_Servico (cursor.getLong ("NR_Movimento_Ordem_Servico"));
        p.setOid_Nota_Fiscal("");

        String sql = " SELECT oid_Nota_Fiscal FROM notas_fiscais_transacoes " +
        			 " WHERE  nr_nota_fiscal=" + cursor.getLong ("NR_Documento") +
        			 " AND oid_Pessoa='" + cursor.getString ("OID_Pessoa") +"'";

        ResultSet resNF = stmt2.executeQuery (sql);

        if (resNF.next ()) {
            p.setOid_Nota_Fiscal(resNF.getString("oid_Nota_Fiscal"));
        }


        if (cursor.getLong("oid_Usuario")>0) {
            sql =  	" SELECT NM_Usuario  " +
			          	" FROM   Usuarios " +
			          	" WHERE  Usuarios.oid_Usuario = " + cursor.getLong("oid_Usuario");
            Statement stmt3 = conn.createStatement ();

            ResultSet resUser = stmt3.executeQuery (sql);

            if (resUser.next ()) {
                p.setTX_Observacao("Lancado por:" + resUser.getString("NM_Usuario") + " / " + p.getTX_Observacao());
		    }
        }

      }
      cursor.close ();
      stmt.close ();
      stmt2.close ();
      conn.close ();
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , Movimento_Ordem_ServicoBean.class.getName () , "getByOID(long oid)");
    }
    return p;
  }



  public long  getByNR_Compromisso (long oid_movimento_ordem_servico) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    long NR_Compromisso=0;
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Ordem_Servico do DSN
      // o NM_Ordem_Servico de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    try {
      String sql=" SELECT Compromissos.NR_Compromisso "  +
                 " FROM   Compromissos, Compromissos_Servicos " +
                 " WHERE  Compromissos_Servicos.oid_Compromisso = Compromissos.oid_Compromisso " +
                 " AND    Compromissos_Servicos.oid_movimento_ordem_servico = " + oid_movimento_ordem_servico;
             // System.out.println(sql);

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (sql);

      if (cursor.next ()) {
             // System.out.println("Comp" + cursor.getLong("NR_Compromisso"));
        NR_Compromisso=cursor.getLong("NR_Compromisso");
      }

      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , Movimento_Ordem_ServicoBean.class.getName () , "getByOID(long oid)");
    }
    return NR_Compromisso;
  }

  public static final Movimento_Ordem_ServicoBean getByVL_Despesas (long oid , String oid_Veiculo) throws Exception {
    // //// System.out.println("aqui");
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    Movimento_Ordem_ServicoBean p = new Movimento_Ordem_ServicoBean ();

    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT SUM(Movimentos_Ordens_Servicos.VL_Movimento) ");
      buff.append ("FROM Movimentos_Ordens_Servicos ");
      buff.append (" WHERE Movimentos_Ordens_Servicos.DM_FATURADO_PAGO = 'P'");
      buff.append (" AND Movimentos_Ordens_Servicos.oid_Ordem_Servico = ");
      buff.append (oid);
      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setVL_Movimento_Pago (cursor.getDouble (1));
      }

      buff.delete (0 , buff.length ());
      buff.append ("SELECT SUM(Movimentos_Ordens_Servicos.VL_Movimento) ");
      buff.append ("FROM Movimentos_Ordens_Servicos ");
      buff.append (" WHERE Movimentos_Ordens_Servicos.DM_FATURADO_PAGO = 'F'");
      buff.append (" AND Movimentos_Ordens_Servicos.oid_Ordem_Servico = ");
      buff.append (oid);

      cursor = stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setVL_Movimento_Faturado (cursor.getDouble (1));
      }

      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
    }
    return p;
  }

  public static final Movimento_Ordem_ServicoBean getValores_Acerto (long oid , String oid_Veiculo , long oid_acerto) throws Exception {

    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    Movimento_Ordem_ServicoBean p = new Movimento_Ordem_ServicoBean ();

    try {
      Statement stmt = conn.createStatement ();
      ResultSet cursor = null;
      StringBuffer buff = new StringBuffer ();
      String sql =
          " SELECT Acertos.VL_Cotacao, Motoristas.DM_Pagamento_Diaria   " +
          " FROM Acertos, Motoristas " +
          " WHERE Acertos.oid_Motorista = Motoristas.oid_Motorista" +
          " AND   Acertos.oid_Acerto= " + oid_acerto +
          "   and Acertos.VL_Cotacao > 0";
      // System.out.println ("vl_cotacao" + buff.toString ());

      cursor = stmt.executeQuery (sql);
      double vl_cotacao = 1;
      String DM_Pagamento_Diaria = "F";
      while (cursor.next ()) {
        vl_cotacao = cursor.getDouble (1);
        DM_Pagamento_Diaria = cursor.getString (2);
      }

      // System.out.println ("vl_->>>acao" + vl_cotacao);
      // System.out.println ("DM_Pagamento_Diaria->>" + DM_Pagamento_Diaria);

      buff.delete (0 , buff.length ());

      buff.append ("SELECT Movimentos_Ordens_Servicos.VL_Movimento, oid_moeda, VL_Documento, DM_FATURADO_PAGO, DM_Tipo_Despesa, NM_Tipo_Servico, Tipos_Servicos.DM_Medida,  Movimentos_Ordens_Servicos.NR_Quantidade ");
      buff.append ("FROM Movimentos_Ordens_Servicos, Tipos_Servicos ");
      buff.append (" WHERE Movimentos_Ordens_Servicos.oid_Tipo_Servico = Tipos_Servicos.oid_Tipo_Servico ");
      buff.append (" AND Movimentos_Ordens_Servicos.oid_Ordem_Servico = ");
      buff.append (oid);

      // System.out.println ("->> " + buff.toString ());

      cursor = stmt.executeQuery (buff.toString ());

      double VL_Movimento_Pago = 0;
      double VL_Movimento_Faturado = 0;
      double VL_Movimento_Extra = 0;
      double litros = 0;

      while (cursor.next ()) {

        if ("L".equals (cursor.getString ("DM_Medida"))) {
          litros += cursor.getDouble ("NR_Quantidade");
        }
        if ("E".equals (cursor.getString ("DM_Faturado_Pago"))) {
          litros += cursor.getDouble ("NR_Quantidade");
        }

        // System.out.println ("Tipo Servico : " + cursor.getString (6) + "->>" + cursor.getString (5));

        if ("S".equals (cursor.getString (5))) { //DIARIAS
          if ("F".equals (DM_Pagamento_Diaria)) {
            if (cursor.getLong (2) > 1) { //moeda =1 padraoreal
              VL_Movimento_Extra += cursor.getDouble (1) * vl_cotacao;
            }
            else {
              VL_Movimento_Extra += cursor.getDouble (3);
            }
          }
          else {
            if (cursor.getLong (2) > 1) { //moeda =1 padraoreal
              VL_Movimento_Pago += cursor.getDouble (1) * vl_cotacao;
            }
            else {
              VL_Movimento_Pago += cursor.getDouble (3);
            }
          }
        }
        else {
          if ("P".equals (cursor.getString (4))) {
            if (cursor.getLong (2) > 1) { //moeda =1 padraoreal
              VL_Movimento_Pago += cursor.getDouble (1) * vl_cotacao;
            }
            else {
              VL_Movimento_Pago += cursor.getDouble (3);
            }
          }
          if ("F".equals (cursor.getString (4))) {
            if (cursor.getLong (2) > 1) { //moeda =1 padraoreal
              VL_Movimento_Faturado += cursor.getDouble (1) * vl_cotacao;
            }
            else {
              VL_Movimento_Faturado += cursor.getDouble (3);
            }
          }

        }
      }
      p.setNR_Litros (litros);

      p.setVL_Movimento_Pago (VL_Movimento_Pago);
      p.setVL_Movimento_Faturado (VL_Movimento_Faturado);
      p.setVL_Movimento_Extra (VL_Movimento_Extra);

      buff.delete (0 , buff.length ());
      buff.append (" SELECT SUM(Ordens_Fretes.VL_Adiantamento1), SUM(Ordens_Fretes.VL_Adiantamento2), SUM(Ordens_Fretes.VL_Adiantamento_Terceiro), SUM(Ordens_Fretes.VL_Descontos) ");
      buff.append (" FROM Ordens_Fretes ");
      buff.append (" WHERE Ordens_Fretes.OID_Acerto = ");
      buff.append (oid_acerto);
      // System.out.println (buff.toString ());
      cursor = stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setVL_Adiantamento_Viagem (cursor.getDouble (1) + cursor.getDouble (2) + cursor.getDouble (3) - cursor.getDouble (4));
      }

      // System.out.println (" Soma =" + p.getVL_Adiantamento_Viagem ());

      buff.delete (0 , buff.length ());
      buff.append (" SELECT SUM(Ordens_Fretes.VL_Ordem_Frete) ");
      buff.append (" FROM Ordens_Fretes ");
      buff.append (" WHERE Ordens_Fretes.OID_Acerto = ");
      buff.append (oid_acerto);
      buff.append (" AND   Ordens_Fretes.DM_Frete ='T' ");

      cursor = stmt.executeQuery (buff.toString ());

      while (cursor.next ()) {
        p.setVL_Frete_Terceiro (cursor.getDouble (1));
      }

      buff.delete (0 , buff.length ());
      buff.append (" SELECT Conhecimentos.VL_Total_Frete, Conhecimentos.VL_Frete_Peso, Conhecimentos.NR_Conhecimento ");
      buff.append (" FROM  Conhecimentos ");
      buff.append (" WHERE Conhecimentos.oid_Acerto= ");
      buff.append (oid_acerto);

      cursor = stmt.executeQuery (buff.toString ());
      // System.out.println ("mov1");
      while (cursor.next ()) {
        // System.out.println (" tem cto ->>" + cursor.getDouble (1) + " - " + cursor.getDouble (2) + " numero= " + cursor.getDouble (3));

        p.setVL_Frete_Proprio (p.getVL_Frete_Proprio () + cursor.getDouble (1));
        p.setVL_Frete_Peso (p.getVL_Frete_Peso () + cursor.getDouble (2));
      }

      p.setVL_Total_Frete (p.getVL_Frete_Proprio () + p.getVL_Frete_Terceiro ());

      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , Movimento_Ordem_ServicoBean.class.getClass ().getName () , "getValores_Acerto(long oid, String oid_Veiculo, long oid_acerto)");
    }
    return p;
  }


  public final List getByOID_ListaME (long oid_Ordem_Servico) throws Exception {

	  return lista(oid_Ordem_Servico, 0, "", "", "", "", "", "", "", "", "S" );
  }

  public final List getByOID_ListaMS (long oid_Ordem_Servico) throws Exception {

	  return lista(oid_Ordem_Servico, 0, "", "", "", "", "", "", "", "", "N" );
  }

  public final List getByNR_Ordem_Servico_Lista (long NR_Ordem_Servico) throws Exception {

	  return lista(0,NR_Ordem_Servico, "", "", "", "", "", "", "", "","" );
  }

  public final List getAll () throws Exception {

	  return lista(0,0, "", "", "", "", "", "", "", "","" );

  }

  public List lista (HttpServletRequest request) throws Excecoes {
    String oid_Veiculo = request.getParameter ("oid_Veiculo");
    String oid_Pessoa = request.getParameter ("oid_Pessoa");
    String oid_Tipo_Servico = request.getParameter ("oid_Tipo_Servico");
    String DT_Inicial = request.getParameter ("FT_DT_Inicial");
    String DT_Final = request.getParameter ("FT_DT_Final");
    String NR_Movimento_Ordem_Servico = request.getParameter ("FT_NR_Movimento_Ordem_Servico");
    String NR_Documento = request.getParameter ("FT_NR_Documento");
    String DM_Tipo_Despesa = request.getParameter ("FT_DM_Tipo_Despesa");


    return lista(0,0,oid_Veiculo, DM_Tipo_Despesa, oid_Pessoa, oid_Tipo_Servico, DT_Inicial, DT_Final, NR_Movimento_Ordem_Servico, NR_Documento, "" );

  }

  public List lista (String oid_Veiculo, String DM_Tipo_Despesa) throws Excecoes {

	  return lista(0,0, oid_Veiculo, DM_Tipo_Despesa, "", "", "", "", "", "","" );
  }


  public List lista (long oid_Ordem_Servico, long NR_Ordem_Servico, String oid_Veiculo, String DM_Tipo_Despesa, String oid_Pessoa, String oid_Tipo_Servico, String DT_Inicial, String DT_Final, String NR_Movimento_Ordem_Servico, String NR_Documento, String DM_Estoque ) throws Excecoes {

	    String sqlBusca =
	        " SELECT Movimentos_Ordens_Servicos.* ," +
	        "        Tipos_Servicos.NM_Tipo_Servico, " +
	        "        Tipos_Servicos.CD_Tipo_Servico, " +
	        "        Tipos_Documentos.NM_Tipo_Documento, " +
	        "        Tipos_Documentos.CD_Tipo_Documento " +
	        " FROM Movimentos_Ordens_Servicos, Tipos_Servicos, Tipos_Documentos " +
	        " WHERE Movimentos_Ordens_Servicos.oid_Tipo_Documento = Tipos_Documentos.oid_Tipo_Documento " +
	        "   AND Movimentos_Ordens_Servicos.oid_Tipo_Servico   = Tipos_Servicos.oid_Tipo_Servico  ";

	      if (oid_Ordem_Servico>0) {
		    sqlBusca += "   AND Movimentos_Ordens_Servicos.OID_Ordem_Servico = " + oid_Ordem_Servico ;
	      }
	      if (NR_Ordem_Servico>0) {
			sqlBusca += "   AND Movimentos_Ordens_Servicos.OID_Ordem_Servico = " + NR_Ordem_Servico ;
		  }

	      if (util.doValida (oid_Veiculo)) {
	        sqlBusca += "   AND Movimentos_Ordens_Servicos.OID_Veiculo = '" + oid_Veiculo + "'";
	      }
	      if (util.doValida (oid_Pessoa)) {
	        sqlBusca += "   AND Movimentos_Ordens_Servicos.OID_Pessoa = '" + oid_Pessoa + "'";
	      }
	      if (util.doValida (oid_Tipo_Servico)) {
	        sqlBusca += "   AND Movimentos_Ordens_Servicos.OID_Tipo_Servico = '" + oid_Tipo_Servico + "'";
	      }
	      if (util.doValida (DT_Inicial)) {
	        sqlBusca += "   AND Movimentos_Ordens_Servicos.DT_Movimento_Ordem_Servico >= '" + DT_Inicial + "'";
	      }
	      if (util.doValida (DT_Final)) {
	        sqlBusca += "   AND Movimentos_Ordens_Servicos.DT_Movimento_Ordem_Servico <= '" + DT_Final + "'";
	      }
	      if (util.doValida (NR_Movimento_Ordem_Servico)) {
	        sqlBusca += "   AND Movimentos_Ordens_Servicos.NR_Movimento_Ordem_Servico = " + NR_Movimento_Ordem_Servico;
	      }
	      if (util.doValida (DM_Tipo_Despesa)) {
	        sqlBusca += "   AND Tipos_Servicos.DM_Tipo_Despesa = '" + DM_Tipo_Despesa + "'";
	      }
	      if ("S".equals(DM_Estoque)) {
		    sqlBusca += "   AND Movimentos_Ordens_Servicos.oid_Estoque > 0 ";
		  }
	      if ("N".equals(DM_Estoque)) {
			sqlBusca += "   AND Movimentos_Ordens_Servicos.oid_Estoque = 0 ";
	      }


	    sqlBusca += " ORDER BY Movimentos_Ordens_Servicos.DT_Movimento_Ordem_Servico DESC";
	    this.inicioTransacao ();
	    try {
	      ResultSet res = sql.executarConsulta (sqlBusca);
	      try {
	        List toReturn = new ArrayList ();
	        while (res.next ()) {
	          Movimento_Ordem_ServicoBean movOS = new Movimento_Ordem_ServicoBean ();
	          movOS.setOID (res.getLong ("OID_Movimento_Ordem_Servico"));
	          movOS.setOID_Ordem_Servico (res.getInt ("OID_Ordem_Servico"));
	          movOS.setOID_Tipo_Documento (res.getInt ("oid_Tipo_Documento"));
	          movOS.setOID_Tipo_Servico (res.getInt ("OID_Tipo_Servico"));
	          movOS.setOID_Pessoa (res.getString ("OID_Pessoa"));
	          movOS.setDT_Movimento_Ordem_Servico (res.getString ("DT_Movimento_Ordem_Servico"));
	          movOS.setNR_Documento (res.getLong ("NR_Documento"));
	          movOS.setNR_Quantidade (res.getDouble ("NR_Quantidade"));
	          movOS.setVL_Movimento (res.getDouble ("VL_Movimento"));
	          movOS.setTX_Observacao (res.getString ("TX_Observacao"));
	          movOS.setNR_Kilometragem (res.getLong ("NR_Kilometragem"));
	          movOS.setNM_Tipo_Servico (res.getString ("NM_Tipo_Servico"));
	          movOS.setCD_Tipo_Servico (res.getString ("CD_Tipo_Servico"));
	          movOS.setNM_Tipo_Documento (res.getString ("NM_Tipo_Documento"));
	          movOS.setCD_Tipo_Documento (res.getString ("CD_Tipo_Documento"));
	          movOS.setNM_Tipo_Documento (res.getString ("NM_Tipo_Documento"));
	          movOS.setDM_Faturado_Pago (res.getString ("DM_Faturado_Pago"));

	          movOS.setOID_Veiculo (res.getString ("OID_Veiculo"));
	          movOS.setVL_Unitario (res.getDouble ("VL_Unitario"));
	          movOS.setNR_Movimento_Ordem_Servico (res.getLong ("NR_Movimento_Ordem_Servico"));

	          if (res.getLong("oid_Estoque")>0){

	        	  sql.executarConsulta (sqlBusca);
	        	  sqlBusca="SELECT CD_Estoque, NM_Estoque FROM Estoques WHERE oid_Estoque=" + res.getLong("oid_Estoque");
	    	      ResultSet resEst = sql.executarConsulta (sqlBusca);
	              if (resEst.next ()) {
	            	  movOS.setNM_Tipo_Servico (res.getString ("NM_Tipo_Servico") + " / " + resEst.getString ("CD_Estoque") + "-" + resEst.getString ("NM_Estoque"));
	              }

	          }


	          toReturn.add (movOS);
	        }
	        return toReturn;
	      }
	      catch (SQLException e) {
	        throw new Excecoes (e.getMessage () , e , this.getClass ().getName () , "lista(HttpServletRequest request)");
	      }
	    }
	    finally {
	      this.fimTransacao (false);
	    }
	  }


  //*** Relatório de Movimentos de Ordem de Servico
   public void relMovimentosOrdemServico (HttpServletRequest request , HttpServletResponse response) throws Excecoes {
     String Relatorio = request.getParameter ("Relatorio");
     String oid_Ordem_Servico = request.getParameter ("oid_Ordem_Servico");
     String NR_Ordem_Servico = request.getParameter ("FT_NR_Ordem_Servico");
     String oid_Veiculo = request.getParameter ("oid_Veiculo");
     String oid_Unidade = request.getParameter ("oid_Unidade");
     String NM_Fantasia = request.getParameter ("FT_NM_Fantasia");
     String DT_Inicial = request.getParameter ("FT_DT_Inicial");
     String DT_Final = request.getParameter ("FT_DT_Final");
     String DM_Tipo_Pagamento = request.getParameter ("FT_DM_Tipo_Pagamento");
     String DM_Tipo_Despesa = request.getParameter ("FT_DM_Tipo_Despesa");
     String oid_Tipo_Servico = request.getParameter ("oid_Tipo_Servico");
     String CD_Tipo_Servico = request.getParameter ("FT_CD_Tipo_Servico");
     String NM_Tipo_Servico = request.getParameter ("FT_NM_Tipo_Servico");
     String oid_Fornecedor_OS = request.getParameter ("oid_Fornecedor_OS");
     String DM_Tipo_Relatorio = request.getParameter ("FT_DM_Tipo_Relatorio");
     String oid_Fornecedor_Movimento_OS = request.getParameter ("oid_Pessoa");

     String descFiltro = "";

     if (util.doValida (DM_Tipo_Relatorio) && DM_Tipo_Relatorio.equals ("D")) {
       Relatorio = "Manutencao_Tipo_Despesa";
     }
     if (!util.doValida (Relatorio)) {
       throw new Mensagens ("Nome do Relatório não informado!");
     }

     Movimento_Ordem_ServicoED ed = new Movimento_Ordem_ServicoED (response , Relatorio);

     if (util.doValida (oid_Ordem_Servico)) {
       ed.setOid_ordem_servico (Integer.parseInt (oid_Ordem_Servico));
       descFiltro = "OS: " + NR_Ordem_Servico;
     }
     if (util.doValida (oid_Unidade)) {
         ed.setOid_Unidade (new Integer(oid_Unidade).intValue());
         descFiltro += " Unidade: " + NM_Fantasia;
       }
     if (util.doValida (oid_Veiculo)) {
       ed.setOid_veiculo (oid_Veiculo);
       descFiltro += " Veículo: " + oid_Veiculo;
     }
     if (util.doValida (DT_Inicial)) {
       ed.setDt_inicial (DT_Inicial);
       descFiltro += " de " + DT_Inicial;
     }
     if (util.doValida (DT_Final)) {
       ed.setDt_final (DT_Final);
       descFiltro += " a " + DT_Final;
     }
     if (util.doValida (DM_Tipo_Pagamento)) {
       ed.setDm_tipo_pagamento (DM_Tipo_Pagamento);
       descFiltro += " Tipo Pagamento: " + DM_Tipo_Pagamento;
     }
     if (util.doValida (DM_Tipo_Despesa)) {
       ed.setDm_tipo_despesa (DM_Tipo_Despesa);
       descFiltro += " Tipo Despesa: " + DM_Tipo_Despesa;
     }
     if (util.doValida (oid_Tipo_Servico)) {
       ed.setOid_tipo_servico (oid_Tipo_Servico);
       descFiltro += " Tp Serv.: " + CD_Tipo_Servico + "-" + NM_Tipo_Servico;
     }
     if (util.doValida (oid_Fornecedor_OS)) {
       ed.setOid_fornecedor_os (oid_Fornecedor_OS);
       descFiltro += " Forn. OS: " + oid_Fornecedor_OS;
     }
     if (util.doValida (oid_Fornecedor_Movimento_OS)) {
       ed.setOid_fornecedor_movimento_os (oid_Fornecedor_Movimento_OS);
       descFiltro += " Forn. Mov. OS: " + oid_Fornecedor_Movimento_OS;
     }

     ed.setDescFiltro (descFiltro);
     new Movimento_Ordem_ServicoRN ().relMovimentosOrdemServico (ed);
   }

  public void relAbastecimento (HttpServletRequest request , HttpServletResponse response) throws Excecoes {
    String Relatorio = request.getParameter ("Relatorio");
    String oid_Ordem_Servico = request.getParameter ("oid_Ordem_Servico");
    String NR_Ordem_Servico = request.getParameter ("FT_NR_Ordem_Servico");
    String oid_Empresa = request.getParameter ("oid_Empresa");
    String oid_Unidade = request.getParameter ("oid_Unidade");
    String oid_Veiculo = request.getParameter ("oid_Veiculo");
    String DT_Inicial = request.getParameter ("FT_DT_Inicial");
    String DT_Final = request.getParameter ("FT_DT_Final");
    String oid_Tipo_Servico = request.getParameter ("oid_Tipo_Servico");
    String CD_Tipo_Servico = request.getParameter ("FT_CD_Tipo_Servico");
    String NM_Tipo_Servico = request.getParameter ("FT_NM_Tipo_Servico");
    String oid_Fornecedor_OS = request.getParameter ("oid_Fornecedor_OS");
    String oid_Fornecedor_Movimento_OS = request.getParameter ("oid_Pessoa");
    String NM_Fantasia = request.getParameter ("FT_NM_Fantasia");

    String descFiltro = "";

    if (!util.doValida (Relatorio)) {
      throw new Mensagens ("Nome do Relatório não informado!");
    }

    Movimento_Ordem_ServicoED ed = new Movimento_Ordem_ServicoED (response , Relatorio);


    if (util.doValida (oid_Ordem_Servico)) {
        ed.setOid_ordem_servico (Integer.parseInt (oid_Ordem_Servico));
        descFiltro = "OS: " + NR_Ordem_Servico;
      }
      if (util.doValida (oid_Unidade)) {
          ed.setOid_Unidade (new Integer(oid_Unidade).intValue());
          descFiltro += " Unidade: " + NM_Fantasia;
        }
      if (util.doValida (oid_Veiculo)) {
        ed.setOid_veiculo (oid_Veiculo);
        descFiltro += " Veículo: " + oid_Veiculo;
      }
      if (util.doValida (DT_Inicial)) {
        ed.setDt_inicial (DT_Inicial);
        descFiltro += " de " + DT_Inicial;
      }
      if (util.doValida (DT_Final)) {
        ed.setDt_final (DT_Final);
        descFiltro += " a " + DT_Final;
      }
      if (util.doValida (oid_Tipo_Servico)) {
        ed.setOid_tipo_servico (oid_Tipo_Servico);
        descFiltro += " Tp Serv.: " + CD_Tipo_Servico + "-" + NM_Tipo_Servico;
      }
      if (util.doValida (oid_Fornecedor_OS)) {
        ed.setOid_fornecedor_os (oid_Fornecedor_OS);
        descFiltro += " Forn. OS: " + oid_Fornecedor_OS;
      }
      if (util.doValida (oid_Fornecedor_Movimento_OS)) {
        ed.setOid_fornecedor_movimento_os (oid_Fornecedor_Movimento_OS);
        descFiltro += " Forn. Mov. OS: " + oid_Fornecedor_Movimento_OS;
      }

      ed.setDescFiltro (descFiltro);

    //Tipo despesa abastecimento
    ed.setDm_tipo_despesa ("C");

    ed.setDescFiltro (descFiltro);
    new Movimento_Ordem_ServicoRN ().relAbastecimento (ed);
  }

  public void relKMsRodados (HttpServletRequest request , HttpServletResponse response) throws Excecoes {
    String Relatorio = request.getParameter ("Relatorio");
    String oid_Empresa = request.getParameter ("oid_Empresa");
    String oid_Unidade = request.getParameter ("oid_Unidade");
    String oid_Veiculo = request.getParameter ("oid_Veiculo");
    String DT_Inicial = request.getParameter ("FT_DT_Inicial");
    String DT_Final = request.getParameter ("FT_DT_Final");

    if (!util.doValida (Relatorio)) {
      throw new Mensagens ("Nome do Relatório não informado!");
    }

    Movimento_Ordem_ServicoED ed = new Movimento_Ordem_ServicoED (response , Relatorio);
    if (util.doValida (oid_Empresa)) {
      ed.setOid_Empresa (Integer.parseInt (oid_Empresa));
    }
    if (util.doValida (oid_Unidade)) {
      ed.setOid_Unidade (Integer.parseInt (oid_Unidade));
    }
    if (util.doValida (oid_Veiculo)) {
      ed.setOid_veiculo (oid_Veiculo);
    }
    if (util.doValida (DT_Inicial)) {
      ed.setDt_inicial (DT_Inicial);
    }
    if (util.doValida (DT_Final)) {
      ed.setDt_final (DT_Final);
    }

    new Movimento_Ordem_ServicoRN ().relKMsRodados (ed);
  }

  public void setNR_Movimento_Ordem_Servico (long movimento_Ordem_Servico) {
    this.NR_Movimento_Ordem_Servico = movimento_Ordem_Servico;
  }

  public boolean doExiste (String oid_Pessoa , String NR_Documento) throws Exception {
	  return this.doExiste(oid_Pessoa, NR_Documento, "" ,"", "",0);
   }


  public boolean doExiste (String oid_Pessoa , String NR_Documento, String oid_Tipo_Servico) throws Exception {
	  return this.doExiste(oid_Pessoa, NR_Documento, oid_Tipo_Servico,"", "",0);
  }

  public boolean doExiste (String oid_Pessoa , String NR_Documento, String oid_Tipo_Servico, String oid_Veiculo) throws Exception {
	  return this.doExiste(oid_Pessoa, NR_Documento, oid_Tipo_Servico, oid_Veiculo, "", 0);
  }


  public boolean doExiste (Movimento_Ordem_ServicoBean filtro) throws Exception {
	  return this.doExiste(filtro.getOID_Pessoa (), String.valueOf(filtro.getNR_Documento ()), String.valueOf(filtro.getOID_Tipo_Servico ()), filtro.getOID_Veiculo(), filtro.getDT_Movimento_Ordem_Servico (), filtro.getNR_Kilometragem ());
  }

  public boolean doExiste (String oid_Pessoa , String NR_Documento, String oid_Tipo_Servico, String oid_Veiculo, String DT_Movimento_Ordem_Servico, long NR_Kilometragem) throws Exception {
	    /*
	     * Abre a conexão com o banco
	     */
	  	boolean existe = false;

	    Connection conn = null;
	    try {
	      // Pede uma conexão ao gerenciador do driver
	      // passando como parâmetro o NM_Ordem_Servico do DSN
	      // o NM_Ordem_Servico de usuário e a senha do banco.
	      conn = OracleConnection2.getWEB ();
	      conn.setAutoCommit (false);
	    }
	    catch (Exception e) {
	      e.printStackTrace ();
	      throw e;
	    }

	    try {

	      Statement stmt = conn.createStatement ();

    	  String sql = " SELECT NR_Documento FROM  Movimentos_Ordens_Servicos " +
	        " WHERE NR_Documento = '" + NR_Documento + "'";
	 		if (util.doValida(oid_Pessoa)){
	 			sql +=" AND  oid_Pessoa = '" + oid_Pessoa + "'";
	 		}
	 		if(util.doValida(oid_Veiculo)){
	 			sql += " AND  oid_Veiculo = '" + oid_Veiculo + "'";
	 		}
	 		if(util.doValida(DT_Movimento_Ordem_Servico)){
	 			sql += " AND  DT_Movimento_Ordem_Servico = '" + DT_Movimento_Ordem_Servico + "'";
	 		}
	 		if(util.doValida(oid_Tipo_Servico)){
	 			sql += " AND  oid_Tipo_Servico = '" + oid_Tipo_Servico +"'";
	 		}
	 		if (NR_Kilometragem>0) {
	 			sql += " AND  nr_kilometragem = '" + NR_Kilometragem +"'";
	 		}
	      System.out.println("->" + sql);
	      ResultSet res = stmt.executeQuery (sql);

          if (res.next()){
              existe = true;
          }
          res.close ();
	      stmt.close ();
	      conn.close ();
	    }
	    catch (Exception e) {
	      e.printStackTrace ();
	    }
	    return existe;
}

public long getOid_Item_Solicitacao_Entrega() {
	return oid_Item_Solicitacao_Entrega;
}

public void setOid_Item_Solicitacao_Entrega(long oid_Item_Solicitacao_Entrega) {
	this.oid_Item_Solicitacao_Entrega = oid_Item_Solicitacao_Entrega;
}



}
