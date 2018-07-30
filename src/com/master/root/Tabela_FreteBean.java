package com.master.root;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.OracleConnection2;

import com.master.ed.Tabela_FreteED;
import com.master.rn.Tabela_FreteRN;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;

public class Tabela_FreteBean {
  private String DM_Origem;
  private String NM_Origem;
  private String DM_Destino;
  private String NM_Destino;
  private String DT_Vigencia;
  private String DT_Validade;
  private double NR_Peso_Inicial;
  private double NR_Peso_Final;
  private double VL_Frete;
  private double VL_Frete_Kg;
  private double VL_Taxas;
  private double PE_Ad_Valorem;
  private double VL_Outros1;
  private double VL_Outros2;
  private double VL_Outros3;
  private double VL_Outros4;
  private double VL_Outros5;
  private double VL_Outros6;
  private double VL_Outros7;
  private double VL_Outros8;
  private double VL_Outros9;
  private String Usuario_Stamp;
  private String Dt_Stamp;
  private String Dm_Stamp;
  private long oid;
  private long oid_Produto;
  private long oid_Origem;
  private long oid_Destino;
  private long OID_Modal;
  private String oid_Pessoa;
  private String NM_Razao_Social;
  private String NM_Modal;
  private String NM_Produto;
  private String CD_Produto;
  private String CD_Modal;
  private String DM_Tipo_Pedagio;
  private double VL_Frete_Valor_Minimo;
  private double VL_Pedagio;
  private double VL_Pedagio_Minimo;
  private double VL_Frete_Minimo;
  private long nr_Cotacao;
  private long oid_Unidade;
  private long OID_Empresa;
  private String OID_Pessoa_Redespacho;
  private long NR_Prazo_Entrega;
  private long NR_Prazo_Faturamento;
  private long NR_Peso_Minimo;
  private String DM_ICMS;
  private String DM_Tipo_Tabela;
  private String DM_Inclusao;
  private String DM_Tipo_Peso;
  private String DM_Tipo_Peso_Taxas;
  private double VL_Ademe_Minimo;
  private double VL_Ad_Valorem_Minimo;
  private double VL_Maximo_Nota_Fiscal;
  private double VL_Minimo_Nota_Fiscal;
  private double VL_Despacho;
  private double PE_Devolucao;
  private double PE_Refaturamento;
  private double PE_Reentrega;
  private double PE_Ademe;
  private String DM_Unidade;
  private double PE_Reembolso;
  private double PE_Reembolso_Minimo;
  private double VL_Reembolso;
  private double VL_TX_Exc_Coleta;
  private double VL_C_Ate25;
  private double VL_C_Ate50;
  private double VL_C_Ate300;
  private double VL_C_Ate500;
  private double VL_C_Ate1000;
  private double VL_TX_Exc_Entrega;
  private double VL_C_Acima1000;
  private double PE_C_Ad_Valorem;
  private double VL_TX_Coleta;
  private double VL_TX_Entrega;
  private double VL_C_Taxa_Minima;

  private double VL_D_Ate1C;
  private double VL_D_Ate2C;
  private double VL_D_Ate3C;
  private double VL_D_Ate4C;
  private double VL_D_Ate5C;
  private double VL_D_Ate6C;
  private double VL_D_Ate7C;
  private double VL_D_Ate8C;
  private double VL_D_Ate9C;
  private double VL_D_Ate10C;
  private double VL_D_Ate11C;
  private double VL_D_Ate12C;
  private double VL_D_Ate13C;
  private double VL_D_Ate14C;
  private double VL_D_Ate15C;
  private double VL_D_Ate16C;
  private double VL_D_Ate17C;
  private double VL_D_Ate18C;
  private double VL_D_Ate19C;
  private double VL_D_Ate20C;
  private double VL_D_Ate21C;
  private double VL_D_Ate22C;
  private double VL_D_Ate23C;
  private double VL_D_Ate24C;
  private double VL_D_Ate25C;
  private double VL_D_Ate26C;
  private double VL_D_Ate27C;
  private double VL_D_Ate28C;
  private double VL_D_Ate29C;
  private double VL_D_Ate30C;
  private double VL_D_Ate50C;
  private double VL_D_Ate100C;

  private double VL_D_Ate1D;
  private double VL_D_Ate2D;
  private double VL_D_Ate3D;
  private double VL_D_Ate4D;
  private double VL_D_Ate5D;
  private double VL_D_Ate6D;
  private double VL_D_Ate7D;
  private double VL_D_Ate8D;
  private double VL_D_Ate9D;
  private double VL_D_Ate10D;
  private double VL_D_Ate11D;
  private double VL_D_Ate12D;
  private double VL_D_Ate13D;
  private double VL_D_Ate14D;
  private double VL_D_Ate15D;
  private double VL_D_Ate16D;
  private double VL_D_Ate17D;
  private double VL_D_Ate18D;
  private double VL_D_Ate19D;
  private double VL_D_Ate20D;
  private double VL_D_Ate21D;
  private double VL_D_Ate22D;
  private double VL_D_Ate23D;
  private double VL_D_Ate24D;
  private double VL_D_Ate25D;
  private double VL_D_Ate26D;
  private double VL_D_Ate27D;
  private double VL_D_Ate28D;
  private double VL_D_Ate29D;
  private double VL_D_Ate30D;
  private double VL_D_Ate50D;
  private double VL_D_Ate100D;

  private double VL_D_ExcedenteC;
  private double VL_D_ExcedenteD;
  private double VL_D_Excedente2C;
  private double VL_D_Excedente2D;
  private double VL_D_Excedente3C;
  private double VL_D_Excedente3D;
  private double PE_D_Ad_Valorem;
  private double VL_Especial1;
  private double VL_Especial2;
  private double VL_Especial3;
  private double VL_Especial4;
  private double VL_Especial5;
  private double VL_Especial6;
  private double VL_Especial7;
  private double VL_Especial8;
  private double VL_Especial9;
  private double VL_Especial10;

  private double VL_R_Ate10;
  private double VL_R_Ate20;
  private double VL_R_Ate30;
  private double VL_R_Ate50;
  private double VL_R_Ate70;
  private double VL_R_Ate100;
  private double VL_R_Ate150;
  private double VL_R_Ate200;
  private double VL_R_Acima200;
  private double VL_R_Ate7500;
  private double VL_R_Ate14500;
  private double VL_R_Acima14500;
  private double PE_AD_Valorem2;
  private double VL_Suframa_Minimo;
  private double PE_Suframa;
  private double PE_Fluvial;
  private double VL_Fluvial_Minimo;

  private double VL_C_Pedagio;
  private double VL_TX_KM_Rodado;
  private double VL_TX_Col_Urg_200;
  private double VL_TX_Col_Urg_1000;
  private double VL_TX_Col_Urg_Ton;
  private double VL_TX_Ent_Urg_200;
  private double VL_TX_Ent_Urg_1000;
  private double VL_TX_Ent_Urg_Ton;
  private double PE_Desc_FP;
  private double PE_Desc_FV;
  private double VL_E_Ate1;
  private double VL_E_1Kg;
  private double VL_E_Excedente;
  private double VL_E_Ad_Valorem;
  private double PE_E_Ad_Valorem;
  private double PE_Frete_Entrega;
  private String DM_Tipo_Localizacao_Destino;
  private String DM_Aplicacao;
  private String DM_Localizado;
  private double NR_Peso_TX_Minima;
  private double NR_Peso_TX_Entrega;
  private double NR_Peso_TX_Redespacho;
  private double VL_TX_Redespacho;
  private double VL_Excesso_TX_Redespacho;
  private double VL_D_Ate31C;
  private double VL_D_Ate32C;
  private double VL_D_Ate33C;
  private double VL_D_Ate34C;
  private double VL_D_Ate35C;
  private double NR_Peso_TX_Coleta;
  private double VL_D_Ate001C;
  private double VL_D_Ate025C;
  private double VL_D_Ate030C;
  private double VL_D_Ate050C;
  private double VL_D_Ate075C;

  private double NR_Prazo_Entrega_C;
  private double NR_Prazo_Entrega_D;
  private double NR_Prazo_Entrega_E;
  private double NR_Prazo_Entrega_R;

  public long getNr_Cotacao () {
    return nr_Cotacao;
  }

  public void setNr_Cotacao (long nr_Cotacao) {
    this.nr_Cotacao = nr_Cotacao;
  }

  public long getOid () {
    return oid;
  }

  public void setOid (long oid) {
    this.oid = oid;
  }

  public long getOid_Destino () {
    return oid_Destino;
  }

  public void setOid_Destino (long oid_Destino) {
    this.oid_Destino = oid_Destino;
  }

  public long getOid_Origem () {
    return oid_Origem;
  }

  public void setOid_Origem (long oid_Origem) {
    this.oid_Origem = oid_Origem;
  }

  public String getOid_Pessoa () {
    return oid_Pessoa;
  }

  public void setOid_Pessoa (String oid_Pessoa) {
    this.oid_Pessoa = oid_Pessoa;
  }

  public long getOid_Produto () {
    return oid_Produto;
  }

  public void setOid_Produto (long oid_Produto) {
    this.oid_Produto = oid_Produto;
  }

  public long getOid_Unidade () {
    return oid_Unidade;
  }

  public double getNR_Prazo_Entrega_R () {
    return NR_Prazo_Entrega_R;
  }

  public double getNR_Prazo_Entrega_E () {
    return NR_Prazo_Entrega_E;
  }

  public double getNR_Prazo_Entrega_D () {
    return NR_Prazo_Entrega_D;
  }

  public double getNR_Prazo_Entrega_C () {
    return NR_Prazo_Entrega_C;
  }

  public double getVL_D_Ate001C () {
    return VL_D_Ate001C;
  }

  public double getVL_D_Ate075C () {
    return VL_D_Ate075C;
  }

  public double getVL_D_Ate050C () {
    return VL_D_Ate050C;
  }

  public double getVL_D_Ate030C () {
    return VL_D_Ate030C;
  }

  public double getVL_D_Ate025C () {
    return VL_D_Ate025C;
  }

  public double getNR_Peso_TX_Coleta () {
    return NR_Peso_TX_Coleta;
  }

  public double getVL_D_Ate31C () {

    return VL_D_Ate31C;
  }

  public double getVL_D_Ate32C () {

    return VL_D_Ate32C;
  }

  public double getVL_D_Ate33C () {

    return VL_D_Ate33C;
  }

  public double getVL_D_Ate34C () {

    return VL_D_Ate34C;
  }

  public double getVL_D_Ate35C () {

    return VL_D_Ate35C;
  }

  public double getVL_Excesso_TX_Redespacho () {
    return VL_Excesso_TX_Redespacho;
  }

  public double getVL_TX_Redespacho () {
    return VL_TX_Redespacho;
  }

  public double getNR_Peso_TX_Redespacho () {

    return NR_Peso_TX_Redespacho;
  }

  public double getNR_Peso_TX_Entrega () {
    return NR_Peso_TX_Entrega;
  }

  public double getNR_Peso_TX_Minima () {
    return NR_Peso_TX_Minima;
  }

  public String getDM_Aplicacao () {
    return DM_Aplicacao;
  }

  public double getVL_Especial10 () {
    return VL_Especial10;
  }

  public double getVL_Especial9 () {
    return VL_Especial9;
  }

  public double getVL_Especial8 () {
    return VL_Especial8;
  }

  public double getVL_Especial7 () {
    return VL_Especial7;
  }

  public double getVL_Especial6 () {
    return VL_Especial6;
  }

  public double getVL_Especial5 () {
    return VL_Especial5;
  }

  public double getVL_Especial4 () {
    return VL_Especial4;
  }

  public double getVL_Especial3 () {
    return VL_Especial3;
  }

  public double getVL_Especial2 () {
    return VL_Especial2;
  }

  public double getVL_Especial1 () {
    return VL_Especial1;
  }

  public double getPE_D_Ad_Valorem () {
    return PE_D_Ad_Valorem;
  }

  public double getVL_D_Ate19D () {
    return VL_D_Ate19D;
  }

  public double getVL_D_Ate1C () {
    return VL_D_Ate1C;
  }

  public double getVL_D_Ate1D () {
    return VL_D_Ate1D;
  }

  public double getVL_D_Ate20C () {
    return VL_D_Ate20C;
  }

  public double getVL_D_Ate20D () {
    return VL_D_Ate20D;
  }

  public double getVL_D_Ate21C () {
    return VL_D_Ate21C;
  }

  public double getVL_D_Ate21D () {
    return VL_D_Ate21D;
  }

  public double getVL_D_Ate22C () {
    return VL_D_Ate22C;
  }

  public double getVL_D_Ate22D () {
    return VL_D_Ate22D;
  }

  public double getVL_D_Ate23C () {
    return VL_D_Ate23C;
  }

  public double getVL_D_Ate23D () {
    return VL_D_Ate23D;
  }

  public double getVL_D_Ate24C () {
    return VL_D_Ate24C;
  }

  public double getVL_D_Ate24D () {
    return VL_D_Ate24D;
  }

  public double getVL_D_Ate25C () {
    return VL_D_Ate25C;
  }

  public double getVL_D_Ate25D () {
    return VL_D_Ate25D;
  }

  public double getVL_D_Ate26C () {
    return VL_D_Ate26C;
  }

  public double getVL_D_Ate26D () {
    return VL_D_Ate26D;
  }

  public double getVL_D_Ate27C () {
    return VL_D_Ate27C;
  }

  public double getVL_D_Ate27D () {
    return VL_D_Ate27D;
  }

  public double getVL_D_Ate28C () {
    return VL_D_Ate28C;
  }

  public double getVL_D_Ate28D () {
    return VL_D_Ate28D;
  }

  public double getVL_D_Ate29C () {
    return VL_D_Ate29C;
  }

  public double getVL_D_Ate29D () {
    return VL_D_Ate29D;
  }

  public double getVL_D_Ate2C () {
    return VL_D_Ate2C;
  }

  public double getVL_D_Ate2D () {
    return VL_D_Ate2D;
  }

  public double getVL_D_Ate30C () {
    return VL_D_Ate30C;
  }

  public double getVL_D_Ate30D () {
    return VL_D_Ate30D;
  }

  public double getVL_D_Ate3C () {
    return VL_D_Ate3C;
  }

  public double getVL_D_Ate3D () {
    return VL_D_Ate3D;
  }

  public double getVL_D_Ate4C () {
    return VL_D_Ate4C;
  }

  public double getVL_D_Ate4D () {
    return VL_D_Ate4D;
  }

  public double getVL_D_Ate50C () {
    return VL_D_Ate50C;
  }

  public double getVL_D_Ate50D () {
    return VL_D_Ate50D;
  }

  public double getVL_D_Ate5C () {
    return VL_D_Ate5C;
  }

  public double getVL_D_Ate5D () {
    return VL_D_Ate5D;
  }

  public double getVL_D_Ate6C () {
    return VL_D_Ate6C;
  }

  public double getVL_D_Ate6D () {
    return VL_D_Ate6D;
  }

  public double getVL_D_Ate7C () {
    return VL_D_Ate7C;
  }

  public double getVL_D_Ate7D () {
    return VL_D_Ate7D;
  }

  public double getVL_D_Ate8C () {
    return VL_D_Ate8C;
  }

  public double getVL_D_Ate8D () {
    return VL_D_Ate8D;
  }

  public double getVL_D_Ate9C () {
    return VL_D_Ate9C;
  }

  public double getVL_D_Ate9D () {
    return VL_D_Ate9D;
  }

  public double getVL_D_Excedente2C () {
    return VL_D_Excedente2C;
  }

  public double getVL_D_Excedente2D () {
    return VL_D_Excedente2D;
  }

  public double getVL_D_Excedente3C () {
    return VL_D_Excedente3C;
  }

  public double getVL_D_Excedente3D () {
    return VL_D_Excedente3D;
  }

  public double getVL_D_ExcedenteC () {
    return VL_D_ExcedenteC;
  }

  public double getVL_D_ExcedenteD () {
    return VL_D_ExcedenteD;
  }

  public double getVL_D_Ate19C () {
    return VL_D_Ate19C;
  }

  public double getVL_D_Ate18D () {
    return VL_D_Ate18D;
  }

  public double getVL_D_Ate18C () {
    return VL_D_Ate18C;
  }

  public double getVL_D_Ate17D () {
    return VL_D_Ate17D;
  }

  public double getVL_D_Ate17C () {
    return VL_D_Ate17C;
  }

  public double getVL_D_Ate16D () {
    return VL_D_Ate16D;
  }

  public double getVL_D_Ate16C () {
    return VL_D_Ate16C;
  }

  public double getVL_D_Ate15D () {
    return VL_D_Ate15D;
  }

  public double getVL_D_Ate15C () {
    return VL_D_Ate15C;
  }

  public double getVL_D_Ate14D () {
    return VL_D_Ate14D;
  }

  public double getVL_D_Ate14C () {
    return VL_D_Ate14C;
  }

  public double getVL_D_Ate13D () {
    return VL_D_Ate13D;
  }

  public double getVL_D_Ate13C () {
    return VL_D_Ate13C;
  }

  public double getVL_D_Ate12D () {
    return VL_D_Ate12D;
  }

  public double getVL_D_Ate12C () {
    return VL_D_Ate12C;
  }

  public double getVL_D_Ate11D () {
    return VL_D_Ate11D;
  }

  public double getVL_D_Ate11C () {
    return VL_D_Ate11C;
  }

  public double getVL_D_Ate10D () {
    return VL_D_Ate10D;
  }

  public double getVL_D_Ate10C () {
    return VL_D_Ate10C;
  }

  public double getVL_D_Ate100D () {
    return VL_D_Ate100D;
  }

  public double getVL_D_Ate100C () {
    return VL_D_Ate100C;
  }

  public double getVL_C_Taxa_Minima () {
    return VL_C_Taxa_Minima;
  }

  public double getVL_TX_Entrega () {
    return VL_TX_Entrega;
  }

  public double getVL_TX_Coleta () {
    return VL_TX_Coleta;
  }

  public double getVL_C_Acima1000 () {
    return VL_C_Acima1000;
  }

  public double getPE_C_Ad_Valorem () {
    return PE_C_Ad_Valorem;
  }

  public double getVL_TX_Exc_Entrega () {
    return VL_TX_Exc_Entrega;
  }

  public double getVL_TX_Exc_Coleta () {
    return VL_TX_Exc_Coleta;
  }

  public double getVL_C_Ate500 () {
    return VL_C_Ate500;
  }

  public double getVL_C_Ate50 () {
    return VL_C_Ate50;
  }

  public double getVL_C_Ate300 () {
    return VL_C_Ate300;
  }

  public double getVL_C_Ate25 () {
    return VL_C_Ate25;
  }

  public double getVL_C_Ate1000 () {
    return VL_C_Ate1000;
  }

  public double getVL_Reembolso () {
    return VL_Reembolso;
  }

  public double getPE_Reembolso_Minimo () {
    return PE_Reembolso_Minimo;
  }

  public double getPE_Reembolso () {
    return PE_Reembolso;
  }

  public void setOid_Unidade (long oid_Unidade) {
    this.oid_Unidade = oid_Unidade;
  }

  public void setNR_Prazo_Entrega_R (double NR_Prazo_Entrega_R) {
    this.NR_Prazo_Entrega_R = NR_Prazo_Entrega_R;
  }

  public void setNR_Prazo_Entrega_E (double NR_Prazo_Entrega_E) {
    this.NR_Prazo_Entrega_E = NR_Prazo_Entrega_E;
  }

  public void setNR_Prazo_Entrega_D (double NR_Prazo_Entrega_D) {
    this.NR_Prazo_Entrega_D = NR_Prazo_Entrega_D;
  }

  public void setNR_Prazo_Entrega_C (double NR_Prazo_Entrega_C) {
    this.NR_Prazo_Entrega_C = NR_Prazo_Entrega_C;
  }

  public void setVL_D_Ate001C (double VL_D_Ate001C) {
    this.VL_D_Ate001C = VL_D_Ate001C;
  }

  public void setVL_D_Ate075C (double VL_D_Ate075C) {
    this.VL_D_Ate075C = VL_D_Ate075C;
  }

  public void setVL_D_Ate050C (double VL_D_Ate050C) {
    this.VL_D_Ate050C = VL_D_Ate050C;
  }

  public void setVL_D_Ate030C (double VL_D_Ate030C) {
    this.VL_D_Ate030C = VL_D_Ate030C;
  }

  public void setVL_D_Ate025C (double VL_D_Ate025C) {
    this.VL_D_Ate025C = VL_D_Ate025C;
  }

  public void setNR_Peso_TX_Coleta (double NR_Peso_TX_Coleta) {
    this.NR_Peso_TX_Coleta = NR_Peso_TX_Coleta;
  }

  public void setVL_D_Ate31C (double VL_D_Ate31C) {

    this.VL_D_Ate31C = VL_D_Ate31C;
  }

  public void setVL_D_Ate32C (double VL_D_Ate32C) {

    this.VL_D_Ate32C = VL_D_Ate32C;
  }

  public void setVL_D_Ate33C (double VL_D_Ate33C) {

    this.VL_D_Ate33C = VL_D_Ate33C;
  }

  public void setVL_D_Ate34C (double VL_D_Ate34C) {

    this.VL_D_Ate34C = VL_D_Ate34C;
  }

  public void setVL_D_Ate35C (double VL_D_Ate35C) {

    this.VL_D_Ate35C = VL_D_Ate35C;
  }

  public void setVL_Excesso_TX_Redespacho (double VL_Excesso_TX_Redespacho) {
    this.VL_Excesso_TX_Redespacho = VL_Excesso_TX_Redespacho;
  }

  public void setVL_TX_Redespacho (double VL_TX_Redespacho) {
    this.VL_TX_Redespacho = VL_TX_Redespacho;
  }

  public void setNR_Peso_TX_Redespacho (double NR_Peso_TX_Redespacho) {

    this.NR_Peso_TX_Redespacho = NR_Peso_TX_Redespacho;
  }

  public void setNR_Peso_TX_Entrega (double NR_Peso_TX_Entrega) {
    this.NR_Peso_TX_Entrega = NR_Peso_TX_Entrega;
  }

  public void setNR_Peso_TX_Minima (double NR_Peso_TX_Minima) {
    this.NR_Peso_TX_Minima = NR_Peso_TX_Minima;
  }

  public void setDM_Localizado (String DM_Localizado) {
    this.DM_Localizado = DM_Localizado;
  }

  public void setDM_Aplicacao (String DM_Aplicacao) {
    this.DM_Aplicacao = DM_Aplicacao;
  }

  public void setVL_Especial10 (double VL_Especial10) {
    this.VL_Especial10 = VL_Especial10;
  }

  public void setVL_Especial9 (double VL_Especial9) {
    this.VL_Especial9 = VL_Especial9;
  }

  public void setVL_Especial8 (double VL_Especial8) {
    this.VL_Especial8 = VL_Especial8;
  }

  public void setVL_Especial7 (double VL_Especial7) {
    this.VL_Especial7 = VL_Especial7;
  }

  public void setVL_Especial6 (double VL_Especial6) {
    this.VL_Especial6 = VL_Especial6;
  }

  public void setVL_Especial5 (double VL_Especial5) {
    this.VL_Especial5 = VL_Especial5;
  }

  public void setVL_Especial4 (double VL_Especial4) {
    this.VL_Especial4 = VL_Especial4;
  }

  public void setVL_Especial3 (double VL_Especial3) {
    this.VL_Especial3 = VL_Especial3;
  }

  public void setVL_Especial2 (double VL_Especial2) {
    this.VL_Especial2 = VL_Especial2;
  }

  public void setVL_Especial1 (double VL_Especial1) {
    this.VL_Especial1 = VL_Especial1;
  }

  public void setPE_D_Ad_Valorem (double PE_D_Ad_Valorem) {
    this.PE_D_Ad_Valorem = PE_D_Ad_Valorem;
  }

  public void setVL_D_ExcedenteD (double VL_D_ExcedenteD) {
    this.VL_D_ExcedenteD = VL_D_ExcedenteD;
  }

  public void setVL_D_ExcedenteC (double VL_D_ExcedenteC) {
    this.VL_D_ExcedenteC = VL_D_ExcedenteC;
  }

  public void setVL_D_Excedente3D (double VL_D_Excedente3D) {
    this.VL_D_Excedente3D = VL_D_Excedente3D;
  }

  public void setVL_D_Excedente3C (double VL_D_Excedente3C) {
    this.VL_D_Excedente3C = VL_D_Excedente3C;
  }

  public void setVL_D_Excedente2D (double VL_D_Excedente2D) {
    this.VL_D_Excedente2D = VL_D_Excedente2D;
  }

  public void setVL_D_Excedente2C (double VL_D_Excedente2C) {
    this.VL_D_Excedente2C = VL_D_Excedente2C;
  }

  public void setVL_D_Ate9D (double VL_D_Ate9D) {
    this.VL_D_Ate9D = VL_D_Ate9D;
  }

  public void setVL_D_Ate9C (double VL_D_Ate9C) {
    this.VL_D_Ate9C = VL_D_Ate9C;
  }

  public void setVL_D_Ate8D (double VL_D_Ate8D) {
    this.VL_D_Ate8D = VL_D_Ate8D;
  }

  public void setVL_D_Ate8C (double VL_D_Ate8C) {
    this.VL_D_Ate8C = VL_D_Ate8C;
  }

  public void setVL_D_Ate7D (double VL_D_Ate7D) {
    this.VL_D_Ate7D = VL_D_Ate7D;
  }

  public void setVL_D_Ate7C (double VL_D_Ate7C) {
    this.VL_D_Ate7C = VL_D_Ate7C;
  }

  public void setVL_D_Ate6D (double VL_D_Ate6D) {
    this.VL_D_Ate6D = VL_D_Ate6D;
  }

  public void setVL_D_Ate6C (double VL_D_Ate6C) {
    this.VL_D_Ate6C = VL_D_Ate6C;
  }

  public void setVL_D_Ate5D (double VL_D_Ate5D) {
    this.VL_D_Ate5D = VL_D_Ate5D;
  }

  public void setVL_D_Ate5C (double VL_D_Ate5C) {
    this.VL_D_Ate5C = VL_D_Ate5C;
  }

  public void setVL_D_Ate50D (double VL_D_Ate50D) {
    this.VL_D_Ate50D = VL_D_Ate50D;
  }

  public void setVL_D_Ate50C (double VL_D_Ate50C) {
    this.VL_D_Ate50C = VL_D_Ate50C;
  }

  public void setVL_D_Ate4D (double VL_D_Ate4D) {
    this.VL_D_Ate4D = VL_D_Ate4D;
  }

  public void setVL_D_Ate4C (double VL_D_Ate4C) {
    this.VL_D_Ate4C = VL_D_Ate4C;
  }

  public void setVL_D_Ate3D (double VL_D_Ate3D) {
    this.VL_D_Ate3D = VL_D_Ate3D;
  }

  public void setVL_D_Ate3C (double VL_D_Ate3C) {
    this.VL_D_Ate3C = VL_D_Ate3C;
  }

  public void setVL_D_Ate30D (double VL_D_Ate30D) {
    this.VL_D_Ate30D = VL_D_Ate30D;
  }

  public void setVL_D_Ate30C (double VL_D_Ate30C) {
    this.VL_D_Ate30C = VL_D_Ate30C;
  }

  public void setVL_D_Ate2D (double VL_D_Ate2D) {
    this.VL_D_Ate2D = VL_D_Ate2D;
  }

  public void setVL_D_Ate2C (double VL_D_Ate2C) {
    this.VL_D_Ate2C = VL_D_Ate2C;
  }

  public void setVL_D_Ate29D (double VL_D_Ate29D) {
    this.VL_D_Ate29D = VL_D_Ate29D;
  }

  public void setVL_D_Ate29C (double VL_D_Ate29C) {
    this.VL_D_Ate29C = VL_D_Ate29C;
  }

  public void setVL_D_Ate28D (double VL_D_Ate28D) {
    this.VL_D_Ate28D = VL_D_Ate28D;
  }

  public void setVL_D_Ate28C (double VL_D_Ate28C) {
    this.VL_D_Ate28C = VL_D_Ate28C;
  }

  public void setVL_D_Ate27D (double VL_D_Ate27D) {
    this.VL_D_Ate27D = VL_D_Ate27D;
  }

  public void setVL_D_Ate27C (double VL_D_Ate27C) {
    this.VL_D_Ate27C = VL_D_Ate27C;
  }

  public void setVL_D_Ate26D (double VL_D_Ate26D) {
    this.VL_D_Ate26D = VL_D_Ate26D;
  }

  public void setVL_D_Ate26C (double VL_D_Ate26C) {
    this.VL_D_Ate26C = VL_D_Ate26C;
  }

  public void setVL_D_Ate25D (double VL_D_Ate25D) {
    this.VL_D_Ate25D = VL_D_Ate25D;
  }

  public void setVL_D_Ate25C (double VL_D_Ate25C) {
    this.VL_D_Ate25C = VL_D_Ate25C;
  }

  public void setVL_D_Ate24D (double VL_D_Ate24D) {
    this.VL_D_Ate24D = VL_D_Ate24D;
  }

  public void setVL_D_Ate24C (double VL_D_Ate24C) {
    this.VL_D_Ate24C = VL_D_Ate24C;
  }

  public void setVL_D_Ate23D (double VL_D_Ate23D) {
    this.VL_D_Ate23D = VL_D_Ate23D;
  }

  public void setVL_D_Ate23C (double VL_D_Ate23C) {
    this.VL_D_Ate23C = VL_D_Ate23C;
  }

  public void setVL_D_Ate22D (double VL_D_Ate22D) {
    this.VL_D_Ate22D = VL_D_Ate22D;
  }

  public void setVL_D_Ate22C (double VL_D_Ate22C) {
    this.VL_D_Ate22C = VL_D_Ate22C;
  }

  public void setVL_D_Ate21D (double VL_D_Ate21D) {
    this.VL_D_Ate21D = VL_D_Ate21D;
  }

  public void setVL_D_Ate21C (double VL_D_Ate21C) {
    this.VL_D_Ate21C = VL_D_Ate21C;
  }

  public void setVL_D_Ate20D (double VL_D_Ate20D) {
    this.VL_D_Ate20D = VL_D_Ate20D;
  }

  public void setVL_D_Ate20C (double VL_D_Ate20C) {
    this.VL_D_Ate20C = VL_D_Ate20C;
  }

  public void setVL_D_Ate1D (double VL_D_Ate1D) {
    this.VL_D_Ate1D = VL_D_Ate1D;
  }

  public void setVL_D_Ate1C (double VL_D_Ate1C) {
    this.VL_D_Ate1C = VL_D_Ate1C;
  }

  public void setVL_D_Ate19D (double VL_D_Ate19D) {
    this.VL_D_Ate19D = VL_D_Ate19D;
  }

  public void setVL_D_Ate19C (double VL_D_Ate19C) {
    this.VL_D_Ate19C = VL_D_Ate19C;
  }

  public void setVL_D_Ate18D (double VL_D_Ate18D) {
    this.VL_D_Ate18D = VL_D_Ate18D;
  }

  public void setVL_D_Ate18C (double VL_D_Ate18C) {
    this.VL_D_Ate18C = VL_D_Ate18C;
  }

  public void setVL_D_Ate14C (double VL_D_Ate14C) {
    this.VL_D_Ate14C = VL_D_Ate14C;
  }

  public void setVL_D_Ate14D (double VL_D_Ate14D) {
    this.VL_D_Ate14D = VL_D_Ate14D;
  }

  public void setVL_D_Ate15C (double VL_D_Ate15C) {
    this.VL_D_Ate15C = VL_D_Ate15C;
  }

  public void setVL_D_Ate15D (double VL_D_Ate15D) {
    this.VL_D_Ate15D = VL_D_Ate15D;
  }

  public void setVL_D_Ate16C (double VL_D_Ate16C) {
    this.VL_D_Ate16C = VL_D_Ate16C;
  }

  public void setVL_D_Ate16D (double VL_D_Ate16D) {
    this.VL_D_Ate16D = VL_D_Ate16D;
  }

  public void setVL_D_Ate17C (double VL_D_Ate17C) {
    this.VL_D_Ate17C = VL_D_Ate17C;
  }

  public void setVL_D_Ate17D (double VL_D_Ate17D) {
    this.VL_D_Ate17D = VL_D_Ate17D;
  }

  public void setVL_D_Ate13D (double VL_D_Ate13D) {
    this.VL_D_Ate13D = VL_D_Ate13D;
  }

  public void setVL_D_Ate13C (double VL_D_Ate13C) {
    this.VL_D_Ate13C = VL_D_Ate13C;
  }

  public void setVL_D_Ate12D (double VL_D_Ate12D) {
    this.VL_D_Ate12D = VL_D_Ate12D;
  }

  public void setVL_D_Ate12C (double VL_D_Ate12C) {
    this.VL_D_Ate12C = VL_D_Ate12C;
  }

  public void setVL_D_Ate11D (double VL_D_Ate11D) {
    this.VL_D_Ate11D = VL_D_Ate11D;
  }

  public void setVL_D_Ate11C (double VL_D_Ate11C) {
    this.VL_D_Ate11C = VL_D_Ate11C;
  }

  public void setVL_D_Ate10D (double VL_D_Ate10D) {
    this.VL_D_Ate10D = VL_D_Ate10D;
  }

  public void setVL_D_Ate10C (double VL_D_Ate10C) {
    this.VL_D_Ate10C = VL_D_Ate10C;
  }

  public void setVL_D_Ate100D (double VL_D_Ate100D) {
    this.VL_D_Ate100D = VL_D_Ate100D;
  }

  public void setVL_D_Ate100C (double VL_D_Ate100C) {
    this.VL_D_Ate100C = VL_D_Ate100C;
  }

  public void setVL_C_Taxa_Minima (double VL_C_Taxa_Minima) {
    this.VL_C_Taxa_Minima = VL_C_Taxa_Minima;
  }

  public void setVL_TX_Coleta (double VL_TX_Coleta) {
    this.VL_TX_Coleta = VL_TX_Coleta;
  }

  public void setVL_TX_Entrega (double VL_TX_Entrega) {
    this.VL_TX_Entrega = VL_TX_Entrega;
  }

  public void setVL_C_Acima1000 (double VL_C_Acima1000) {
    this.VL_C_Acima1000 = VL_C_Acima1000;
  }

  public void setPE_C_Ad_Valorem (double PE_C_Ad_Valorem) {
    this.PE_C_Ad_Valorem = PE_C_Ad_Valorem;
  }

  public void setVL_TX_Exc_Coleta (double VL_TX_Exc_Coleta) {
    this.VL_TX_Exc_Coleta = VL_TX_Exc_Coleta;
  }

  public void setVL_TX_Exc_Entrega (double VL_TX_Exc_Entrega) {
    this.VL_TX_Exc_Entrega = VL_TX_Exc_Entrega;
  }

  public void setVL_C_Ate1000 (double VL_C_Ate1000) {
    this.VL_C_Ate1000 = VL_C_Ate1000;
  }

  public void setVL_C_Ate25 (double VL_C_Ate25) {
    this.VL_C_Ate25 = VL_C_Ate25;
  }

  public void setVL_C_Ate300 (double VL_C_Ate300) {
    this.VL_C_Ate300 = VL_C_Ate300;
  }

  public void setVL_C_Ate50 (double VL_C_Ate50) {
    this.VL_C_Ate50 = VL_C_Ate50;
  }

  public void setVL_C_Ate500 (double VL_C_Ate500) {
    this.VL_C_Ate500 = VL_C_Ate500;
  }

  public void setVL_Reembolso (double VL_Reembolso) {
    this.VL_Reembolso = VL_Reembolso;
  }

  public void setPE_Reembolso_Minimo (double PE_Reembolso_Minimo) {
    this.PE_Reembolso_Minimo = PE_Reembolso_Minimo;
  }

  public void setPE_Reembolso (double PE_Reembolso) {
    this.PE_Reembolso = PE_Reembolso;
  }

  public Tabela_FreteBean () {
    NR_Peso_Inicial = 0;
    NR_Peso_Final = 0;
  }

  public String getDM_Origem () {
    return DM_Origem;
  }

  public void setDM_Origem (String DM_Origem) {
    this.DM_Origem = DM_Origem;
  }

  public String getNM_Origem () {
    return NM_Origem;
  }

  public void setNM_Origem (String NM_Origem) {
    this.NM_Origem = NM_Origem;
  }

  public String getDM_Destino () {
    return DM_Destino;
  }

  public void setDM_Destino (String DM_Destino) {
    this.DM_Destino = DM_Destino;
  }

  public String getNM_Destino () {
    return NM_Destino;
  }

  public void setNM_Destino (String NM_Destino) {
    this.NM_Destino = NM_Destino;
  }

  public String getDT_Vigencia () {
    FormataDataBean DataFormatada = new FormataDataBean ();
    DataFormatada.setDT_FormataData (DT_Vigencia);
    DT_Vigencia = DataFormatada.getDT_FormataData ();
    return DT_Vigencia;
  }

  public void setDT_Vigencia (String DT_Vigencia) {
    this.DT_Vigencia = DT_Vigencia;
  }

  public String getDT_Validade () {
    FormataDataBean DataFormatada = new FormataDataBean ();
    DataFormatada.setDT_FormataData (DT_Validade);
    DT_Validade = DataFormatada.getDT_FormataData ();
    return DT_Validade;
  }

  public void setDT_Validade (String DT_Validade) {
    this.DT_Validade = DT_Validade;
  }

  public double getNR_Peso_Inicial () {
    return NR_Peso_Inicial;
  }

  public void setNR_Peso_Inicial (double NR_Peso_Inicial) {
    this.NR_Peso_Inicial = NR_Peso_Inicial;
  }

  public double getNR_Peso_Final () {
    return NR_Peso_Final;
  }

  public void setNR_Peso_Final (double NR_Peso_Final) {
    this.NR_Peso_Final = NR_Peso_Final;
  }

  public double getVL_Frete () {
    return VL_Frete;
  }

  public void setVL_Frete (double VL_Frete) {
    this.VL_Frete = VL_Frete;
  }

  public double getVL_Frete_Kg () {
    return VL_Frete_Kg;
  }

  public void setVL_Frete_Kg (double VL_Frete_Kg) {
    this.VL_Frete_Kg = VL_Frete_Kg;
  }

  public double getVL_Taxas () {
    return VL_Taxas;
  }

  public void setVL_Taxas (double VL_Taxas) {
    this.VL_Taxas = VL_Taxas;
  }

  public double getPE_Ad_Valorem () {
    return PE_Ad_Valorem;
  }

  public void setPE_Ad_Valorem (double PE_Ad_Valorem) {
    this.PE_Ad_Valorem = PE_Ad_Valorem;
  }

  public double getVL_Outros1 () {
    return VL_Outros1;
  }

  public void setVL_Outros1 (double VL_Outros1) {
    this.VL_Outros1 = VL_Outros1;
  }

  public double getVL_Outros2 () {
    return VL_Outros2;
  }

  public void setVL_Outros2 (double VL_Outros2) {
    this.VL_Outros2 = VL_Outros2;
  }

  public double getVL_Outros5 () {
    return VL_Outros5;
  }

  public void setVL_Outros5 (double VL_Outros5) {
    this.VL_Outros5 = VL_Outros5;
  }

  public double getVL_Outros4 () {
    return VL_Outros4;
  }

  public void setVL_Outros4 (double VL_Outros4) {
    this.VL_Outros4 = VL_Outros4;
  }

  public double getVL_Outros3 () {
    return VL_Outros3;
  }

  public void setVL_Outros3 (double VL_Outros3) {
    this.VL_Outros3 = VL_Outros3;
  }

  public double getVL_Outros6 () {
    return VL_Outros6;
  }

  public void setVL_Outros6 (double VL_Outros6) {
    this.VL_Outros6 = VL_Outros6;
  }

  public double getVL_Outros7 () {
    return VL_Outros7;
  }

  public void setVL_Outros7 (double VL_Outros7) {
    this.VL_Outros7 = VL_Outros7;
  }

  public double getVL_Outros8 () {
    return VL_Outros8;
  }

  public void setVL_Outros8 (double VL_Outros8) {
    this.VL_Outros8 = VL_Outros8;
  }

  public double getVL_Outros9 () {
    return VL_Outros9;
  }

  public void setVL_Outros9 (double VL_Outros9) {
    this.VL_Outros9 = VL_Outros9;
  }

  public long getOID_Produto () {
    return oid_Produto;
  }

  public void setOID_Produto (long n) {
    this.oid_Produto = n;
  }

  public long getOID_Origem () {
    return oid_Origem;
  }

  public void setOID_Origem (long n) {
    this.oid_Origem = n;
  }

  public long getOID_Destino () {
    return oid_Destino;
  }

  public void setOID_Destino (long n) {
    this.oid_Destino = n;
  }

  public String getNM_Produto () {
    return NM_Produto;
  }

  public void setNM_Produto (String NM_Produto) {
    this.NM_Produto = NM_Produto;
  }

  public String getCD_Produto () {
    return CD_Produto;
  }

  public void setCD_Produto (String CD_Produto) {
    this.CD_Produto = CD_Produto;
  }

  public long getOID_Modal () {
    return OID_Modal;
  }

  public void setOID_Modal (long n) {
    this.OID_Modal = n;
  }

  public String getCD_Modal () {
    return CD_Modal;
  }

  public void setCD_Modal (String CD_Modal) {
    this.CD_Modal = CD_Modal;
  }

  public String getNM_Modal () {
    return NM_Modal;
  }

  public void setNM_Modal (String NM_Modal) {
    this.NM_Modal = NM_Modal;
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

  public void insert () throws Exception {
    Connection conn = OracleConnection2.getWEB ();
    conn.setAutoCommit (false);
    
    // System.out.println("INCLUI TABELA 1");
    
    String xx = String.valueOf (System.currentTimeMillis ()).toString ().substring (4 , 13);
    setOID (new Long (xx).longValue ());

    /* Valida se já existe uma tabela com os mesmos dados */

    Tabela_FreteED tabela = new Tabela_FreteED();
    tabela.setOID_Pessoa (getOID_Pessoa ());
    tabela.setOID_Produto_Origem (new Long (getOID_Produto ()).intValue ());
    // System.out.println("INCLUI TABELA 2");

    tabela.setOID_Modal (new Long (getOID_Modal ()).intValue ());
    tabela.setOID_Cidade_Origem (getOID_Origem ());
    // System.out.println("INCLUI TABELA 3");

    tabela.setOID_Cidade_Destino (getOID_Destino ());
    tabela.setNR_Peso_Inicial(getNR_Peso_Inicial ());
    // System.out.println("INCLUI TABELA 4");
    
    tabela.setNR_Peso_Final (getNR_Peso_Final ());
    tabela.setDM_Origem (getDM_Origem ());
    tabela.setDM_Destino (getDM_Destino ());
    // System.out.println("INCLUI TABELA 5");

    if (new Tabela_FreteRN ().tabelaExiste (tabela)) {
      // System.out.println("INCLUI TABELA=Já existe uma tabela com estes dados");
      if (!"COPIA".equals(getDM_Inclusao())){
    	  throw new Mensagens ("Já existe uma tabela com estes dados");
      }
    }else {

	    String sql =
	        " INSERT INTO Tabelas_Fretes" +
	        "   (OID_Tabela_Frete, " +
	        "    OID_Produto, " +
	        "    OID_Modal, " +
	        "    OID_Pessoa, " +
	        "    OID_Origem, " +
	        "    OID_Destino, " +
	        "    DM_Origem, " +
	        "    NM_Origem, " +
	        "    DM_Destino, " +
	        "    NM_Destino, " +
	        "    NR_Peso_Inicial, " +
	        "    NR_Peso_Final, " +
	        "    DT_Vigencia, " +
	        "    DT_Validade, " +
	        "    VL_Frete, " +
	        "    VL_Frete_Kg, " +
	        "    VL_Taxas, " +
	        "    PE_Ad_Valorem, " +
	        "    VL_C_Ate25, " +
	        "    VL_C_Ate50, " +
	        "    VL_C_Ate300, " +
	        "    VL_C_Ate500, " +
	        "    VL_C_Ate1000, " +
	        "    VL_C_Acima1000, " +
	        "    VL_C_Taxa_Minima, " +
	        "    PE_C_Ad_Valorem, " +
	        "    VL_TX_Exc_Entrega, " +
	        "    VL_TX_Exc_Coleta, " +
	        "    VL_TX_Coleta, " +
	        "    VL_TX_Entrega, " +
	        "    NR_Peso_TX_Coleta, " +
	        "    NR_Peso_TX_Entrega, " +
	        "    NR_Peso_TX_Minima, " +
	        "    NR_Peso_TX_Redespacho, " +
	        "    VL_TX_Redespacho, " +
	        "    VL_Excesso_TX_Redespacho, " +
	
	        "    VL_D_Ate1C, " +
	        "    VL_D_Ate2C, " +
	        "    VL_D_Ate3C, " +
	        "    VL_D_Ate4C, " +
	        "    VL_D_Ate5C, " +
	        "    VL_D_Ate6C, " +
	        "    VL_D_Ate7C, " +
	        "    VL_D_Ate8C, " +
	        "    VL_D_Ate9C, " +
	        "    VL_D_Ate10C, " +
	        "    VL_D_Ate11C, " +
	        "    VL_D_Ate12C, " +
	        "    VL_D_Ate13C, " +
	        "    VL_D_Ate14C, " +
	        "    VL_D_Ate15C, " +
	        "    VL_D_Ate16C, " +
	        "    VL_D_Ate17C, " +
	        "    VL_D_Ate18C, " +
	        "    VL_D_Ate19C, " +
	        "    VL_D_Ate20C, " +
	        "    VL_D_Ate21C, " +
	        "    VL_D_Ate22C, " +
	        "    VL_D_Ate23C, " +
	        "    VL_D_Ate24C, " +
	        "    VL_D_Ate25C, " +
	        "    VL_D_Ate26C, " +
	        "    VL_D_Ate27C, " +
	        "    VL_D_Ate28C, " +
	        "    VL_D_Ate29C, " +
	        "    VL_D_Ate30C, " +
	        "    VL_D_Ate31C, " +
	        "    VL_D_Ate32C, " +
	        "    VL_D_Ate33C, " +
	        "    VL_D_Ate34C, " +
	        "    VL_D_Ate35C, " +
	        "    VL_D_Ate50C, " +
	        "    VL_D_Ate100C, " +
	
	        "    VL_D_Ate001C, " +
	        "    VL_D_Ate025C, " +
	        "    VL_D_Ate030C, " +
	        "    VL_D_Ate050C, " +
	        "    VL_D_Ate075C, " +
	
	        "    VL_D_Ate1D, " +
	        "    VL_D_Ate2D, " +
	        "    VL_D_Ate3D, " +
	        "    VL_D_Ate4D, " +
	        "    VL_D_Ate5D, " +
	        "    VL_D_Ate6D, " +
	        "    VL_D_Ate7D, " +
	        "    VL_D_Ate8D, " +
	        "    VL_D_Ate9D, " +
	        "    VL_D_Ate10D, " +
	        "    VL_D_Ate11D, " +
	        "    VL_D_Ate12D, " +
	        "    VL_D_Ate13D, " +
	        "    VL_D_Ate14D, " +
	        "    VL_D_Ate15D, " +
	        "    VL_D_Ate16D, " +
	        "    VL_D_Ate17D, " +
	        "    VL_D_Ate18D, " +
	        "    VL_D_Ate19D, " +
	        "    VL_D_Ate20D, " +
	        "    VL_D_Ate21D, " +
	        "    VL_D_Ate22D, " +
	        "    VL_D_Ate23D, " +
	        "    VL_D_Ate24D, " +
	        "    VL_D_Ate25D, " +
	        "    VL_D_Ate26D, " +
	        "    VL_D_Ate27D, " +
	        "    VL_D_Ate28D, " +
	        "    VL_D_Ate29D, " +
	        "    VL_D_Ate30D, " +
	        "    VL_D_Ate50D, " +
	        "    VL_D_Ate100D, " +
	        "    VL_D_ExcedenteC, " +
	        "    VL_D_Excedente2C, " +
	        "    VL_D_Excedente3C, " +
	        "    VL_D_ExcedenteD, " +
	        "    VL_D_Excedente2D, " +
	        "    VL_D_Excedente3D, " +
	        "    PE_D_Ad_Valorem, " +
	        "    VL_Outros1, " +
	        "    VL_Outros2, " +
	        "    VL_Outros3, " +
	        "    VL_Outros4, " +
	        "    VL_Outros5, " +
	        "    VL_Outros6, " +
	        "    VL_Outros7, " +
	        "    VL_Outros8, " +
	        "    VL_Outros9, " +
	        "    VL_Especial1, " +
	        "    VL_Especial2, " +
	        "    VL_Especial3, " +
	        "    VL_Especial4, " +
	        "    VL_Especial5, " +
	        "    VL_Especial6, " +
	        "    VL_Especial7, " +
	        "    VL_Especial8, " +
	        "    VL_Especial9, " +
	        "    VL_Especial10, " +
	        "    Dt_Stamp, " +
	        "    Usuario_Stamp, " +
	        "    Dm_Stamp, " +
	        "    DM_Tipo_Pedagio, " +
	        "    VL_Pedagio, " +
	        "    VL_Frete_Minimo, " +
	        "    NR_Cotacao, " +
	        "    Oid_Unidade, " +
	        "    OID_Empresa, " +
	        "    OID_Pessoa_Redespacho, " +
	        "    NR_Prazo_Entrega, " +
	        "    NR_Prazo_Faturamento, " +
	        "    NR_Peso_Minimo, " +
	        "    DM_ICMS, " +
	        "    DM_Tipo_Tabela, " +
	        "    DM_Aplicacao, " +
	        "    DM_Tipo_Peso, " +
	        "    DM_Tipo_Peso_Taxas, " +
	        "    VL_Ademe_Minimo, " +
	        "    VL_Ad_Valorem_Minimo, " +
	        "    VL_Maximo_Nota_Fiscal, " +
	        "    VL_Minimo_Nota_Fiscal, " +
	        "    VL_Despacho, " +
	        "    PE_Devolucao, " +
	        "    PE_Refaturamento, " +
	        "    PE_Reentrega, " +
	        "    PE_Ademe," +
	        "    PE_Reembolso," +
	        "    PE_Reembolso_Minimo," +
	        "    VL_Reembolso, " +
	        "    VL_R_Ate10, " +
	        "    VL_R_Ate20, " +
	        "    VL_R_Ate30, " +
	        "    VL_R_Ate50, " +
	        "    VL_R_Ate70, " +
	        "    VL_R_Ate100, " +
	        "    VL_R_Ate150, " +
	        "    VL_R_Ate200, " +
	        "    VL_R_Acima200, " +
	        "    VL_R_Ate7500, " +
	        "    VL_R_Ate14500, " +
	        "    VL_R_Acima14500, " +
	        "    PE_AD_Valorem2, " +
	        "    VL_Suframa_Minimo, " +
	        "    PE_Suframa, " +
	        "    PE_Fluvial, " +
	        "    VL_Fluvial_Minimo, " +
	        "    VL_C_Pedagio, " +
	        "    VL_TX_KM_Rodado, " +
	        "    VL_TX_Col_Urg_200, " +
	        "    VL_TX_Col_Urg_1000, " +
	        "    VL_TX_Col_Urg_Ton, " +
	        "    VL_TX_Ent_Urg_200, " +
	        "    VL_TX_Ent_Urg_1000, " +
	        "    VL_TX_Ent_Urg_Ton, " +
	        "    PE_Desc_FP, " +
	        "    PE_Desc_FV, " +
	        "    VL_E_Ate1, " +
	        "    VL_E_1Kg, " +
	        "    VL_E_Excedente, " +
	        "    VL_E_Ad_Valorem, " +
	        "    PE_E_Ad_Valorem, " +
	        "    VL_Pedagio_Minimo, " +
	        "    PE_Frete_Entrega)" +
	        " VALUES (" + getOID () +
	        "        ," + getOID_Produto () +
	        "        ," + getOID_Modal () +
	        "        ,'" + getOID_Pessoa () + "'" +
	        "        ," + getOID_Origem () +
	        "        ," + getOID_Destino () +
	        "        ,'" + getDM_Origem () + "'" +
	        "        ,'" + getNM_Origem () + "'" +
	        "        ,'" + getDM_Destino () + "'" +
	        "        ,'" + getNM_Destino () + "'" +
	        "        ," + getNR_Peso_Inicial () +
	        "        ," + getNR_Peso_Final () +
	        "        ," + JavaUtil.getSQLDate (getDT_Vigencia ()) +
	        "        ," + JavaUtil.getSQLDate (getDT_Validade ()) +
	        "        ," + getVL_Frete () +
	        "        ," + getVL_Frete_Kg () +
	        "        ," + getVL_Taxas () +
	        "        ," + getPE_Ad_Valorem () +
	
	        "        ," + getVL_C_Ate25 () +
	        "        ," + getVL_C_Ate50 () +
	        "        ," + getVL_C_Ate300 () +
	        "        ," + getVL_C_Ate500 () +
	        "        ," + getVL_C_Ate1000 () +
	        "        ," + getVL_C_Acima1000 () +
	        "        ," + getVL_C_Taxa_Minima () +
	        "        ," + getPE_C_Ad_Valorem () +
	        "        ," + getVL_TX_Exc_Entrega () +
	        "        ," + getVL_TX_Exc_Coleta () +
	        "        ," + getVL_TX_Coleta () +
	        "        ," + getVL_TX_Entrega () +
	        "        ," + getNR_Peso_TX_Coleta  () +
	        "        ," + getNR_Peso_TX_Entrega  () +
	        "        ," + getNR_Peso_TX_Minima  () +
	        "        ," + getNR_Peso_TX_Redespacho  () +
	        "        ," + getVL_TX_Redespacho  () +
	        "        ," + getVL_Excesso_TX_Redespacho  () +
	
	        "        ," + getVL_D_Ate1C () +
	        "        ," + getVL_D_Ate2C () +
	        "        ," + getVL_D_Ate3C () +
	        "        ," + getVL_D_Ate4C () +
	        "        ," + getVL_D_Ate5C () +
	        "        ," + getVL_D_Ate6C () +
	        "        ," + getVL_D_Ate7C () +
	        "        ," + getVL_D_Ate8C () +
	        "        ," + getVL_D_Ate9C () +
	        "        ," + getVL_D_Ate10C () +
	        "        ," + getVL_D_Ate11C () +
	        "        ," + getVL_D_Ate12C () +
	        "        ," + getVL_D_Ate13C () +
	        "        ," + getVL_D_Ate14C () +
	        "        ," + getVL_D_Ate15C () +
	        "        ," + getVL_D_Ate16C () +
	        "        ," + getVL_D_Ate17C () +
	        "        ," + getVL_D_Ate18C () +
	        "        ," + getVL_D_Ate19C () +
	        "        ," + getVL_D_Ate20C () +
	        "        ," + getVL_D_Ate21C () +
	        "        ," + getVL_D_Ate22C () +
	        "        ," + getVL_D_Ate23C () +
	        "        ," + getVL_D_Ate24C () +
	        "        ," + getVL_D_Ate25C () +
	        "        ," + getVL_D_Ate26C () +
	        "        ," + getVL_D_Ate27C () +
	        "        ," + getVL_D_Ate28C () +
	        "        ," + getVL_D_Ate29C () +
	        "        ," + getVL_D_Ate30C () +
	        "        ," + getVL_D_Ate31C () +
	        "        ," + getVL_D_Ate32C () +
	        "        ," + getVL_D_Ate33C () +
	        "        ," + getVL_D_Ate34C () +
	        "        ," + getVL_D_Ate35C () +
	        "        ," + getVL_D_Ate50C () +
	        "        ," + getVL_D_Ate100C () +
	
	        "        ," + getVL_D_Ate001C () +
	        "        ," + getVL_D_Ate025C () +
	        "        ," + getVL_D_Ate030C () +
	        "        ," + getVL_D_Ate050C () +
	        "        ," + getVL_D_Ate075C () +
	
	        "        ," + getVL_D_Ate1D () +
	        "        ," + getVL_D_Ate2D () +
	        "        ," + getVL_D_Ate3D () +
	        "        ," + getVL_D_Ate4D () +
	        "        ," + getVL_D_Ate5D () +
	        "        ," + getVL_D_Ate6D () +
	        "        ," + getVL_D_Ate7D () +
	        "        ," + getVL_D_Ate8D () +
	        "        ," + getVL_D_Ate9D () +
	        "        ," + getVL_D_Ate10D () +
	        "        ," + getVL_D_Ate11D () +
	        "        ," + getVL_D_Ate12D () +
	        "        ," + getVL_D_Ate13D () +
	        "        ," + getVL_D_Ate14D () +
	        "        ," + getVL_D_Ate15D () +
	        "        ," + getVL_D_Ate16D () +
	        "        ," + getVL_D_Ate17D () +
	        "        ," + getVL_D_Ate18D () +
	        "        ," + getVL_D_Ate19D () +
	        "        ," + getVL_D_Ate20D () +
	        "        ," + getVL_D_Ate21D () +
	        "        ," + getVL_D_Ate22D () +
	        "        ," + getVL_D_Ate23D () +
	        "        ," + getVL_D_Ate24D () +
	        "        ," + getVL_D_Ate25D () +
	        "        ," + getVL_D_Ate26D () +
	        "        ," + getVL_D_Ate27D () +
	        "        ," + getVL_D_Ate28D () +
	        "        ," + getVL_D_Ate29D () +
	        "        ," + getVL_D_Ate30D () +
	        "        ," + getVL_D_Ate50D () +
	        "        ," + getVL_D_Ate100D () +
	        "        ," + getVL_D_ExcedenteC () +
	        "        ," + getVL_D_Excedente2C () +
	        "        ," + getVL_D_Excedente3C () +
	        "        ," + getVL_D_ExcedenteD () +
	        "        ," + getVL_D_Excedente2D () +
	        "        ," + getVL_D_Excedente3D () +
	        "        ," + getPE_D_Ad_Valorem () +
	
	        "        ," + getVL_Outros1 () +
	        "        ," + getVL_Outros2 () +
	        "        ," + getVL_Outros3 () +
	        "        ," + getVL_Outros4 () +
	        "        ," + getVL_Outros5 () +
	        "        ," + getVL_Outros6 () +
	        "        ," + getVL_Outros7 () +
	        "        ," + getVL_Outros8 () +
	        "        ," + getVL_Outros9 () +
	        "        ," + getVL_Especial1 () +
	        "        ," + getVL_Especial2 () +
	        "        ," + getVL_Especial3 () +
	        "        ," + getVL_Especial4 () +
	        "        ," + getVL_Especial5 () +
	        "        ," + getVL_Especial6 () +
	        "        ," + getVL_Especial7 () +
	        "        ," + getVL_Especial8 () +
	        "        ," + getVL_Especial9 () +
	        "        ," + getVL_Especial10 () +
	        "        ," + JavaUtil.getSQLDate (getDt_Stamp ()) +
	        "        ," + getUsuario_Stamp () +
	        "        ," + JavaUtil.getSQLStringDef (getDm_Stamp () , "") +
	        "        ," + JavaUtil.getSQLStringDef (getDM_Tipo_Pedagio () , "") +
	        "        ," + getVL_Pedagio () +
	        "        ," + getVL_Frete_Minimo () +
	        "        ," + getNr_Cotacao () +
	        "        ," + getOid_Unidade () +
	        "        ," + getOID_Empresa () +
	        "        ,'" + getOID_Pessoa_Redespacho () + "'" +
	        "        ," + getNR_Prazo_Entrega () +
	        "        ," + getNR_Prazo_Faturamento () +
	        "        ," + getNR_Peso_Minimo () +
	        "        ,'" + JavaUtil.getValueDef (getDM_ICMS () , "N") + "'" +
	        "        ," + JavaUtil.getSQLStringDef (getDM_Tipo_Tabela () , "") +
	        "        ," + JavaUtil.getSQLStringDef (getDM_Aplicacao () , "") +
	        "        ," + JavaUtil.getSQLStringDef (getDM_Tipo_Peso () , "") +
	        "        ," + JavaUtil.getSQLStringDef (getDM_Tipo_Peso_Taxas () , "") +
	        "        ," + getVL_Ademe_Minimo () +
	        "        ," + getVL_Ad_Valorem_Minimo () +
	        "        ," + getVL_Maximo_Nota_Fiscal () +
	        "        ," + getVL_Minimo_Nota_Fiscal () +
	        "        ," + getVL_Despacho () +
	        "        ," + getPE_Devolucao () +
	        "        ," + getPE_Refaturamento () +
	        "        ," + getPE_Reentrega () +
	        "        ," + getPE_Ademe () +
	        "        ," + getPE_Reembolso () +
	        "        ," + getPE_Reembolso_Minimo () +
	        "        ," + getVL_Reembolso () +
	        "        ," + getVL_R_Ate10 () +
	        "        ," + getVL_R_Ate20 () +
	        "        ," + getVL_R_Ate30 () +
	        "        ," + getVL_R_Ate50 () +
	        "        ," + getVL_R_Ate70 () +
	        "        ," + getVL_R_Ate100 () +
	        "        ," + getVL_R_Ate150 () +
	        "        ," + getVL_R_Ate200 () +
	        "        ," + getVL_R_Acima200 () +
	        "        ," + getVL_R_Ate7500 () +
	        "        ," + getVL_R_Ate14500 () +
	        "        ," + getVL_R_Acima14500 () +
	        "        ," + getPE_AD_Valorem2 () +
	        "        ," + getVL_Suframa_Minimo () +
	        "        ," + getPE_Suframa () +
	        "        ," + getPE_Fluvial () +
	        "        ," + getVL_Fluvial_Minimo () +
	        "        ," + getVL_C_Pedagio () +
	        "        ," + getVL_TX_KM_Rodado () +
	        "        ," + getVL_TX_Col_Urg_200 () +
	        "        ," + getVL_TX_Col_Urg_1000 () +
	        "        ," + getVL_TX_Col_Urg_Ton () +
	        "        ," + getVL_TX_Ent_Urg_200 () +
	        "        ," + getVL_TX_Ent_Urg_1000 () +
	        "        ," + getVL_TX_Ent_Urg_Ton () +
	        "        ," + getPE_Desc_FP () +
	        "        ," + getPE_Desc_FV () +
	        "        ," + getVL_E_Ate1 () +
	        "        ," + getVL_E_1Kg () +
	        "        ," + getVL_E_Excedente () +
	        "        ," + getVL_E_Ad_Valorem () +
	        "        ," + getPE_E_Ad_Valorem () +
	        "        ," + getVL_Pedagio_Minimo () +
	        "        ," + getPE_Frete_Entrega () + ")";
	    // System.out.println(sql);
	    try {
	
	      PreparedStatement pstmt = conn.prepareStatement (sql);
	      pstmt.executeUpdate ();
	    }
	    catch (Exception e) {
	      conn.rollback ();
	      e.printStackTrace ();
	      throw e;
	    }
    }  
    /*
     * Faz o commit e fecha a conexão.
     */
    try {
      conn.commit ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
  }

  public void update () throws Exception {
    Connection conn = OracleConnection2.getWEB ();
    conn.setAutoCommit (false);
    String sql =
        " UPDATE Tabelas_Fretes" +
        " SET OID_Produto=" + getOID_Produto () +
        "    ,OID_Modal=" + getOID_Modal () +
        "    ,OID_Origem=" + getOID_Origem () +
        "    ,OID_Destino=" + getOID_Destino () +
        "    ,DM_Origem='" + getDM_Origem () + "'" +
        "    ,NM_Origem='" + getNM_Origem () + "'" +
        "    ,DM_Destino='" + getDM_Destino () + "'" +
        "    ,NM_Destino='" + getNM_Destino () + "'" +
        "    ,NR_Peso_Inicial=" + getNR_Peso_Inicial () +
        "    ,NR_Peso_Final=" + getNR_Peso_Final () +
        "    ,DT_Vigencia=" + JavaUtil.getSQLDate (getDT_Vigencia ()) +
        "    ,DT_Validade=" + JavaUtil.getSQLDate (getDT_Validade ()) +
        "    ,VL_Frete=" + getVL_Frete () +
        "    ,VL_Frete_Kg=" + getVL_Frete_Kg () +
        "    ,VL_Taxas=" + getVL_Taxas () +
        "    ,PE_Ad_Valorem=" + getPE_Ad_Valorem () +

        "    ,VL_TX_Exc_Coleta=" + getVL_TX_Exc_Coleta () +
        "    ,VL_C_Ate25=" + getVL_C_Ate25 () +
        "    ,VL_C_Ate50=" + getVL_C_Ate50 () +
        "    ,VL_C_Ate300=" + getVL_C_Ate300 () +
        "    ,VL_C_Ate500=" + getVL_C_Ate500 () +
        "    ,VL_C_Ate1000=" + getVL_C_Ate1000 () +
        "    ,VL_TX_Exc_Entrega=" + getVL_TX_Exc_Entrega () +
        "    ,VL_C_Acima1000=" + getVL_C_Acima1000 () +
        "    ,PE_C_Ad_Valorem=" + getPE_C_Ad_Valorem () +
        "    ,VL_TX_Coleta=" + getVL_TX_Coleta () +
        "    ,VL_TX_Entrega=" + getVL_TX_Entrega () +
        "    ,VL_C_Taxa_Minima=" + getVL_C_Taxa_Minima () +
        "    ,NR_Peso_TX_Coleta=" + getNR_Peso_TX_Coleta () +
        "    ,NR_Peso_TX_Entrega=" + getNR_Peso_TX_Entrega () +
        "    ,NR_Peso_TX_Minima=" + getNR_Peso_TX_Minima () +
        "    ,NR_Peso_TX_Redespacho=" + getNR_Peso_TX_Redespacho () +
        "    ,VL_TX_Redespacho=" + getVL_TX_Redespacho () +
        "    ,VL_Excesso_TX_Redespacho=" + getVL_Excesso_TX_Redespacho () +

        "    ,VL_D_Ate1C=" + getVL_D_Ate1C () +
        "    ,VL_D_Ate2C=" + getVL_D_Ate2C () +
        "    ,VL_D_Ate3C=" + getVL_D_Ate3C () +
        "    ,VL_D_Ate4C=" + getVL_D_Ate4C () +
        "    ,VL_D_Ate5C=" + getVL_D_Ate5C () +
        "    ,VL_D_Ate6C=" + getVL_D_Ate6C () +
        "    ,VL_D_Ate7C=" + getVL_D_Ate7C () +
        "    ,VL_D_Ate8C=" + getVL_D_Ate8C () +
        "    ,VL_D_Ate9C=" + getVL_D_Ate9C () +
        "    ,VL_D_Ate10C=" + getVL_D_Ate10C () +
        "    ,VL_D_Ate11C=" + getVL_D_Ate11C () +
        "    ,VL_D_Ate12C=" + getVL_D_Ate12C () +
        "    ,VL_D_Ate13C=" + getVL_D_Ate13C () +
        "    ,VL_D_Ate14C=" + getVL_D_Ate14C () +
        "    ,VL_D_Ate15C=" + getVL_D_Ate15C () +
        "    ,VL_D_Ate16C=" + getVL_D_Ate16C () +
        "    ,VL_D_Ate17C=" + getVL_D_Ate17C () +
        "    ,VL_D_Ate18C=" + getVL_D_Ate18C () +
        "    ,VL_D_Ate19C=" + getVL_D_Ate19C () +
        "    ,VL_D_Ate20C=" + getVL_D_Ate20C () +
        "    ,VL_D_Ate21C=" + getVL_D_Ate21C () +
        "    ,VL_D_Ate22C=" + getVL_D_Ate22C () +
        "    ,VL_D_Ate23C=" + getVL_D_Ate23C () +
        "    ,VL_D_Ate24C=" + getVL_D_Ate24C () +
        "    ,VL_D_Ate25C=" + getVL_D_Ate25C () +
        "    ,VL_D_Ate26C=" + getVL_D_Ate26C () +
        "    ,VL_D_Ate27C=" + getVL_D_Ate27C () +
        "    ,VL_D_Ate28C=" + getVL_D_Ate28C () +
        "    ,VL_D_Ate29C=" + getVL_D_Ate29C () +
        "    ,VL_D_Ate30C=" + getVL_D_Ate30C () +
        "    ,VL_D_Ate31C=" + getVL_D_Ate31C () +
        "    ,VL_D_Ate32C=" + getVL_D_Ate32C () +
        "    ,VL_D_Ate33C=" + getVL_D_Ate33C () +
        "    ,VL_D_Ate34C=" + getVL_D_Ate34C () +
        "    ,VL_D_Ate35C=" + getVL_D_Ate35C () +

        "    ,VL_D_Ate50C=" + getVL_D_Ate50C () +
        "    ,VL_D_Ate100C=" + getVL_D_Ate100C () +

        "    ,VL_D_Ate001C=" + getVL_D_Ate001C () +
        "    ,VL_D_Ate025C=" + getVL_D_Ate025C () +
        "    ,VL_D_Ate030C=" + getVL_D_Ate030C () +
        "    ,VL_D_Ate050C=" + getVL_D_Ate050C () +
        "    ,VL_D_Ate075C=" + getVL_D_Ate075C () +

        "    ,VL_D_Ate1D=" + getVL_D_Ate1D () +
        "    ,VL_D_Ate2D=" + getVL_D_Ate2D () +
        "    ,VL_D_Ate3D=" + getVL_D_Ate3D () +
        "    ,VL_D_Ate4D=" + getVL_D_Ate4D () +
        "    ,VL_D_Ate5D=" + getVL_D_Ate5D () +
        "    ,VL_D_Ate6D=" + getVL_D_Ate6D () +
        "    ,VL_D_Ate7D=" + getVL_D_Ate7D () +
        "    ,VL_D_Ate8D=" + getVL_D_Ate8D () +
        "    ,VL_D_Ate9D=" + getVL_D_Ate9D () +
        "    ,VL_D_Ate10D=" + getVL_D_Ate10D () +
        "    ,VL_D_Ate11D=" + getVL_D_Ate11D () +
        "    ,VL_D_Ate12D=" + getVL_D_Ate12D () +
        "    ,VL_D_Ate13D=" + getVL_D_Ate13D () +
        "    ,VL_D_Ate14D=" + getVL_D_Ate14D () +
        "    ,VL_D_Ate15D=" + getVL_D_Ate15D () +
        "    ,VL_D_Ate16D=" + getVL_D_Ate16D () +
        "    ,VL_D_Ate17D=" + getVL_D_Ate17D () +
        "    ,VL_D_Ate18D=" + getVL_D_Ate18D () +
        "    ,VL_D_Ate19D=" + getVL_D_Ate19D () +
        "    ,VL_D_Ate20D=" + getVL_D_Ate20D () +
        "    ,VL_D_Ate21D=" + getVL_D_Ate21D () +
        "    ,VL_D_Ate22D=" + getVL_D_Ate22D () +
        "    ,VL_D_Ate23D=" + getVL_D_Ate23D () +
        "    ,VL_D_Ate24D=" + getVL_D_Ate24D () +
        "    ,VL_D_Ate25D=" + getVL_D_Ate25D () +
        "    ,VL_D_Ate26D=" + getVL_D_Ate26D () +
        "    ,VL_D_Ate27D=" + getVL_D_Ate27D () +
        "    ,VL_D_Ate28D=" + getVL_D_Ate28D () +
        "    ,VL_D_Ate29D=" + getVL_D_Ate29D () +
        "    ,VL_D_Ate30D=" + getVL_D_Ate30D () +
        "    ,VL_D_Ate50D=" + getVL_D_Ate50D () +
        "    ,VL_D_Ate100D=" + getVL_D_Ate100D () +

        "    ,VL_D_ExcedenteC=" + getVL_D_ExcedenteC () +
        "    ,VL_D_Excedente2C=" + getVL_D_Excedente2C () +
        "    ,VL_D_Excedente3C=" + getVL_D_Excedente3C () +
        "    ,VL_D_ExcedenteD=" + getVL_D_ExcedenteD () +
        "    ,VL_D_Excedente2D=" + getVL_D_Excedente2D () +
        "    ,VL_D_Excedente3D=" + getVL_D_Excedente3D () +
        "    ,PE_D_Ad_Valorem=" + getPE_D_Ad_Valorem () +

        "    ,VL_Outros1=" + getVL_Outros1 () +
        "    ,VL_Outros2=" + getVL_Outros2 () +
        "    ,VL_Outros3=" + getVL_Outros3 () +
        "    ,VL_Outros4=" + getVL_Outros4 () +
        "    ,VL_Outros5=" + getVL_Outros5 () +
        "    ,VL_Outros6=" + getVL_Outros6 () +
        "    ,VL_Outros7=" + getVL_Outros7 () +
        "    ,VL_Outros8=" + getVL_Outros8 () +
        "    ,VL_Outros9=" + getVL_Outros9 () +
        "    ,VL_Especial1=" + getVL_Especial1 () +
        "    ,VL_Especial2=" + getVL_Especial2 () +
        "    ,VL_Especial3=" + getVL_Especial3 () +
        "    ,VL_Especial4=" + getVL_Especial4 () +
        "    ,VL_Especial5=" + getVL_Especial5 () +
        "    ,VL_Especial6=" + getVL_Especial6 () +
        "    ,VL_Especial7=" + getVL_Especial7 () +
        "    ,VL_Especial8=" + getVL_Especial8 () +
        "    ,VL_Especial9=" + getVL_Especial9 () +
        "    ,VL_Especial10=" + getVL_Especial10 () +
        "    ,DM_Tipo_Pedagio=" + JavaUtil.getSQLStringDef (getDM_Tipo_Pedagio () , "") +
        "    ,VL_Pedagio=" + getVL_Pedagio () +
        "    ,VL_Pedagio_Minimo=" + getVL_Pedagio_Minimo () +
        "    ,VL_Frete_Minimo=" + getVL_Frete_Minimo () +
        "    ,OID_Empresa=" + getOID_Empresa () +
        "    ,OID_Pessoa_Redespacho='" + getOID_Pessoa_Redespacho () + "'" +
        "    ,NR_Prazo_Entrega=" + getNR_Prazo_Entrega () +
        "    ,NR_Prazo_Faturamento=" + getNR_Prazo_Faturamento () +
        "    ,NR_Peso_Minimo=" + getNR_Peso_Minimo () +
        "    ,DM_ICMS=" + JavaUtil.getSQLStringDef (getDM_ICMS () , "") +
        "    ,DM_Tipo_Tabela=" + JavaUtil.getSQLStringDef (getDM_Tipo_Tabela () , "") +
        "    ,DM_Tipo_Peso=" + JavaUtil.getSQLStringDef (getDM_Tipo_Peso () , "") +
        "    ,DM_Tipo_Peso_Taxas=" + JavaUtil.getSQLStringDef (getDM_Tipo_Peso_Taxas () , "") +
        "    ,VL_Ademe_Minimo=" + getVL_Ademe_Minimo () +
        "    ,VL_Ad_Valorem_Minimo=" + getVL_Ad_Valorem_Minimo () +
        "    ,VL_Maximo_Nota_Fiscal=" + getVL_Maximo_Nota_Fiscal () +
        "    ,VL_Minimo_Nota_Fiscal=" + getVL_Minimo_Nota_Fiscal () +
        "    ,VL_Despacho=" + getVL_Despacho () +
        "    ,PE_Devolucao=" + getPE_Devolucao () +
        "    ,PE_Refaturamento=" + getPE_Refaturamento () +
        "    ,PE_Reentrega=" + getPE_Reentrega () +
        "    ,PE_Ademe=" + getPE_Ademe () +
        "    ,PE_Reembolso=" + getPE_Reembolso () +
        "    ,PE_Reembolso_Minimo=" + getPE_Reembolso_Minimo () +
        "    ,VL_Reembolso=" + getVL_Reembolso () +
        "    ,VL_R_Ate10=" + getVL_R_Ate10 () +
        "    ,VL_R_Ate20=" + getVL_R_Ate20 () +
        "    ,VL_R_Ate30=" + getVL_R_Ate30 () +
        "    ,VL_R_Ate50=" + getVL_R_Ate50 () +
        "    ,VL_R_Ate70=" + getVL_R_Ate70 () +
        "    ,VL_R_Ate100=" + getVL_R_Ate100 () +
        "    ,VL_R_Ate150=" + getVL_R_Ate150 () +
        "    ,VL_R_Ate200=" + getVL_R_Ate200 () +
        "    ,VL_R_Acima200=" + getVL_R_Acima200 () +
        "    ,VL_R_Ate7500=" + getVL_R_Ate7500 () +
        "    ,VL_R_Ate14500=" + getVL_R_Ate14500 () +
        "    ,VL_R_Acima14500=" + getVL_R_Acima14500 () +
        "    ,PE_AD_Valorem2=" + getPE_AD_Valorem2 () +
        "    ,VL_Suframa_Minimo=" + getVL_Suframa_Minimo () +
        "    ,PE_Suframa=" + getPE_Suframa () +
        "    ,PE_Fluvial=" + getPE_Fluvial () +
        "    ,VL_Fluvial_Minimo=" + getVL_Fluvial_Minimo () +
        "    ,VL_C_Pedagio=" + getVL_C_Pedagio () +
        "    ,VL_TX_KM_Rodado=" + getVL_TX_KM_Rodado () +
        "    ,VL_TX_Col_Urg_200=" + getVL_TX_Col_Urg_200 () +
        "    ,VL_TX_Col_Urg_1000=" + getVL_TX_Col_Urg_1000 () +
        "    ,VL_TX_Col_Urg_Ton=" + getVL_TX_Col_Urg_Ton () +
        "    ,VL_TX_Ent_Urg_200=" + getVL_TX_Ent_Urg_200 () +
        "    ,VL_TX_Ent_Urg_1000=" + getVL_TX_Ent_Urg_1000 () +
        "    ,VL_TX_Ent_Urg_Ton=" + getVL_TX_Ent_Urg_Ton () +
        "    ,PE_Desc_FP=" + getPE_Desc_FP () +
        "    ,PE_Desc_FV=" + getPE_Desc_FV () +
        "    ,VL_E_Ate1=" + getVL_E_Ate1 () +
        "    ,VL_E_1Kg=" + getVL_E_1Kg () +
        "    ,VL_E_Excedente=" + getVL_E_Excedente () +
        "    ,VL_E_Ad_Valorem=" + getVL_E_Ad_Valorem () +
        "    ,PE_E_Ad_Valorem=" + getPE_E_Ad_Valorem () +
        "    ,PE_Frete_Entrega=" + getPE_Frete_Entrega () +
        " WHERE OID_Tabela_Frete=" + getOID ();
    
    //System.out.println(sql);
    try {
      PreparedStatement pstmt = conn.prepareStatement (sql);
      pstmt.executeUpdate ();
    }
    catch (Exception e) {
      conn.rollback ();
      e.printStackTrace ();
      throw e;
    }
    try {
      conn.commit ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
  }

  public void delete () throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Tabela_Frete do DSN
      // o NM_Tabela_Frete de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    /*
     * Define o DELETE.
     */
    StringBuffer buff = new StringBuffer ();
    buff.append ("DELETE FROM Tabelas_Fretes ");
    buff.append ("WHERE OID_Tabela_Frete=?");
    /*
     * Define os dados do SQL
     * e executa o insert no banco.
     */
    try {
      PreparedStatement pstmt =
          conn.prepareStatement (buff.toString ());
      pstmt.setLong (1 , getOID ());
      pstmt.executeUpdate ();
    }
    catch (Exception e) {
      conn.rollback ();
      e.printStackTrace ();
      throw e;
    }
    /*
     * Faz o commit e fecha a conexão.
     */
    try {
      conn.commit ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
  }

  public static final Tabela_FreteBean getByProduto (String NR_CNPJ_CPF) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Tabela_Frete do DSN
      // o NM_Tabela_Frete de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    Tabela_FreteBean p = new Tabela_FreteBean ();
    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT Tabelas_Fretes.OID_Produto, Produtos.CD_Produto, Produtos.NM_Produto ");
      buff.append ("FROM Tabelas_Fretes, Produtos ");
      buff.append ("WHERE Tabelas_Fretes.OID_Produto = Produtos.OID_Produto  ");
      buff.append (" AND Tabelas_Fretes.OID_Pessoa ='");
      buff.append (NR_CNPJ_CPF);
      buff.append ("'");

      Statement stmt = conn.createStatement ();
      ResultSet cursor =
          stmt.executeQuery (buff.toString ());
      int i = 0;
      while (cursor.next () && i == 0) {
        i++;
        p.setOID_Produto (cursor.getLong (1));
        p.setCD_Produto (cursor.getString (2));
        p.setNM_Produto (cursor.getString (3));
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    return p;
  }


  public static final List lista_Produto_Cliente (String oid_Pessoa) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Tabela_Frete do DSN
      // o NM_Tabela_Frete de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    List tabelaLista = new ArrayList();

    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT Produtos.oid_Produto, Produtos.CD_Produto, Produtos.NM_Produto ");
      buff.append ("FROM Tabelas_Fretes, Produtos ");
      buff.append ("WHERE Tabelas_Fretes.OID_Produto = Produtos.OID_Produto  ");
      buff.append (" AND Tabelas_Fretes.OID_Pessoa ='");
      buff.append (oid_Pessoa);
      buff.append ("'");
      buff.append (" GROUP BY Produtos.oid_Produto, Produtos.CD_Produto, Produtos.NM_Produto ");

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());
      while (cursor.next ()) {
        Tabela_FreteBean p = new Tabela_FreteBean ();
        p.setOID_Produto (cursor.getLong (1));
        p.setCD_Produto (cursor.getString (2));
        p.setNM_Produto (cursor.getString (3));
        tabelaLista.add(p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    return tabelaLista;
  }

  public static final List lista_Modal_Cliente (String oid_Pessoa) throws Exception {
    /*
     * Abre a conexão com o banco
     */
    Connection conn = null;
    try {
      // Pede uma conexão ao gerenciador do driver
      // passando como parâmetro o NM_Tabela_Frete do DSN
      // o NM_Tabela_Frete de usuário e a senha do banco.
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }

    List tabelaLista = new ArrayList();

    try {
      StringBuffer buff = new StringBuffer ();
      buff.append ("SELECT Modal.oid_Modal, Modal.CD_Modal, Modal.NM_Modal ");
      buff.append ("FROM Tabelas_Fretes, Modal ");
      buff.append ("WHERE Tabelas_Fretes.OID_Modal = Modal.OID_Modal  ");
      buff.append (" AND Tabelas_Fretes.OID_Pessoa ='");
      buff.append (oid_Pessoa);
      buff.append ("'");
      buff.append (" GROUP BY Modal.oid_Modal, Modal.CD_Modal, Modal.NM_Modal ");



      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (buff.toString ());
      while (cursor.next ()) {
        
        Tabela_FreteBean p = new Tabela_FreteBean ();
        p.setOID_Modal (cursor.getLong (1));
        p.setCD_Modal (cursor.getString (2));
        p.setNM_Modal (cursor.getString (3));


        tabelaLista.add(p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      throw e;
    }
    return tabelaLista;
  }


  public static final Tabela_FreteBean getByOID (long oid) throws Exception {
    if (oid > 0) {
      Tabela_FreteBean filtro = new Tabela_FreteBean ();
      filtro.setOID (oid);
      List lista = getByRecord (filtro);
      Iterator iterator = lista.iterator ();
      if (iterator.hasNext ()) {
        return (Tabela_FreteBean) iterator.next ();
      }
      else {
        return new Tabela_FreteBean ();
      }
    }
    else {
      return new Tabela_FreteBean ();
    }
  }

  public static final List getAll () throws Exception {
    Tabela_FreteBean filtro = new Tabela_FreteBean ();
    filtro.setDM_Aplicacao ("CN");
    return getByRecord (filtro);
  }

  public static final List getAll (int oid_Modal) throws Exception {
    Tabela_FreteBean filtro = new Tabela_FreteBean ();
    filtro.setOID_Modal (oid_Modal);
    filtro.setDM_Aplicacao ("CN");

    return getByRecord (filtro);
  }

  public static final List getByNR_CNPJ_CPF_Lista (String NR_CNPJ_CPF) throws Exception {
    if (JavaUtil.doValida (NR_CNPJ_CPF)) {
      Tabela_FreteBean filtro = new Tabela_FreteBean ();
      filtro.setOID_Pessoa (NR_CNPJ_CPF);
      filtro.setDM_Aplicacao ("CN");
      return getByRecord (filtro);
    }
    else {
      return new ArrayList ();
    }
  }

  public static final List getByNR_CNPJ_CPF_Lista (String NR_CNPJ_CPF , int oid_Modal) throws Exception {
    if (JavaUtil.doValida (NR_CNPJ_CPF)) {
      Tabela_FreteBean filtro = new Tabela_FreteBean ();
      filtro.setOID_Pessoa (NR_CNPJ_CPF);
      filtro.setOID_Modal (oid_Modal);
      filtro.setDM_Aplicacao ("CN");
      return getByRecord (filtro);
    }
    else {
      return new ArrayList ();
    }
  }

  public static final List getByNR_CNPJ_CPF_Lista (String NR_CNPJ_CPF , String DM_Aplicacao) throws Exception {
    if (JavaUtil.doValida (NR_CNPJ_CPF)) {
      Tabela_FreteBean filtro = new Tabela_FreteBean ();
      filtro.setOID_Pessoa ( (NR_CNPJ_CPF));
      filtro.setDM_Aplicacao (DM_Aplicacao);
      return getByRecord (filtro);
    }
    else {
      return new ArrayList ();
    }
  }

  public static final List lista (String NR_CNPJ_CPF , String DM_Aplicacao , int oid_Modal) throws Exception {
    Tabela_FreteBean filtro = new Tabela_FreteBean ();
    if (JavaUtil.doValida (NR_CNPJ_CPF)) {
      filtro.setOID_Pessoa (NR_CNPJ_CPF);
    }
    if (JavaUtil.doValida (DM_Aplicacao)) {
      filtro.setDM_Aplicacao (DM_Aplicacao);
    }

    filtro.setOID_Modal (oid_Modal);

    return getByRecord (filtro);
  }

  public static final List getByRecord (Tabela_FreteBean filtro) throws Excecoes {
    Connection conn;
    try {
      conn = OracleConnection2.getWEB ();
      conn.setAutoCommit (false);
    }
    catch (Exception e) {
      throw new Excecoes (e.getMessage () , e , Tabela_FreteBean.class.getName () , "getByRecord(Tabela_FreteBean filtro)");
    }
    List toReturn = new ArrayList ();
    Tabela_FreteBean p;
    try {
      String sql =
          " SELECT " +
          "		   Tabelas_Fretes.OID_Tabela_Frete " +
          "       ,Tabelas_Fretes.OID_Produto " +
          "       ,Tabelas_Fretes.OID_Modal " +
          "       ,Tabelas_Fretes.OID_Pessoa " +
          "       ,Tabelas_Fretes.NR_Peso_Inicial " +
          "       ,Tabelas_Fretes.NR_Peso_Final " +
          "       ,Tabelas_Fretes.VL_Frete " +
          "       ,Tabelas_Fretes.VL_Frete_Kg " +
          "       ,Tabelas_Fretes.VL_Taxas " +
          "       ,Tabelas_Fretes.PE_Ad_Valorem " +
          "       ,Tabelas_Fretes.VL_Outros1 " +
          "       ,Tabelas_Fretes.VL_Outros2 " +
          "       ,Tabelas_Fretes.VL_Outros3 " +
          "       ,Tabelas_Fretes.VL_Outros4 " +
          "       ,Tabelas_Fretes.VL_Outros5 " +
          "       ,Tabelas_Fretes.VL_Outros6 " +
          "       ,Tabelas_Fretes.VL_Outros7 " +
          "       ,Tabelas_Fretes.VL_Outros8 " +
          "       ,Tabelas_Fretes.VL_Outros9 " +
          "       ,Tabelas_Fretes.Dt_Stamp " +
          "       ,Tabelas_Fretes.Usuario_Stamp " +
          "       ,Tabelas_Fretes.Dm_Stamp " +
          "       ,Pessoas.NM_Razao_Social" +
          "       ,Produtos.CD_Produto " +
          "       ,Produtos.NM_Produto " +
          "       ,Modal.CD_Modal " +
          "       ,Modal.NM_Modal " +
          "       ,Tabelas_Fretes.OID_Origem " +
          "       ,Tabelas_Fretes.OID_Destino " +
          "       ,Tabelas_Fretes.NM_Origem " +
          "       ,Tabelas_Fretes.NM_Destino " +
          "       ,Tabelas_Fretes.DM_Origem " +
          "       ,Tabelas_Fretes.DM_Destino " +
          "       ,Tabelas_Fretes.DT_Vigencia " +
          "       ,Tabelas_Fretes.DT_Validade " +
          "       ,Tabelas_Fretes.DM_Tipo_Pedagio " +
          "       ,Tabelas_Fretes.VL_Pedagio " +
          "       ,Tabelas_Fretes.VL_Frete_Minimo " +
          "       ,Tabelas_Fretes.OID_Empresa " +
          "       ,Tabelas_Fretes.OID_Pessoa_Redespacho " +
          "       ,Tabelas_Fretes.NR_Prazo_Entrega " +
          "       ,Tabelas_Fretes.NR_Prazo_Faturamento " +
          "       ,Tabelas_Fretes.NR_Peso_Minimo " +
          "       ,Tabelas_Fretes.DM_ICMS " +
          "       ,Tabelas_Fretes.DM_Tipo_Tabela " +
          "       ,Tabelas_Fretes.DM_Tipo_Peso " +
          "       ,Tabelas_Fretes.DM_Tipo_Peso_Taxas " +
          "       ,Tabelas_Fretes.VL_Ademe_Minimo " +
          "       ,Tabelas_Fretes.VL_Ad_Valorem_Minimo " +
          "       ,Tabelas_Fretes.VL_Maximo_Nota_Fiscal " +
          "       ,Tabelas_Fretes.VL_Minimo_Nota_Fiscal " +
          "       ,Tabelas_Fretes.VL_Despacho " +
          "       ,Tabelas_Fretes.PE_Devolucao " +
          "       ,Tabelas_Fretes.PE_Refaturamento " +
          "       ,Tabelas_Fretes.PE_Reentrega " +
          "       ,Tabelas_Fretes.PE_Ademe " +
          "       ,Tabelas_Fretes.VL_C_Ate25 " +
          "       ,Tabelas_Fretes.VL_C_Ate50 " +
          "       ,Tabelas_Fretes.VL_C_Ate300 " +
          "       ,Tabelas_Fretes.VL_C_Ate500 " +
          "       ,Tabelas_Fretes.VL_C_Ate1000 " +
          "       ,Tabelas_Fretes.VL_C_Acima1000 " +
          "       ,Tabelas_Fretes.VL_TX_Coleta " +
          "       ,Tabelas_Fretes.VL_TX_Entrega " +
          "       ,Tabelas_Fretes.VL_TX_Exc_Coleta " +
          "       ,Tabelas_Fretes.VL_TX_Exc_Entrega " +
          "       ,Tabelas_Fretes.PE_C_Ad_Valorem " +
          "       ,Tabelas_Fretes.VL_C_Taxa_Minima " +
          "       ,Tabelas_Fretes.VL_D_Ate1C " +
          "       ,Tabelas_Fretes.VL_D_Ate2C " +
          "       ,Tabelas_Fretes.VL_D_Ate3C " +
          "       ,Tabelas_Fretes.VL_D_Ate4C " +
          "       ,Tabelas_Fretes.VL_D_Ate5C " +
          "       ,Tabelas_Fretes.VL_D_Ate6C " +
          "       ,Tabelas_Fretes.VL_D_Ate7C " +
          "       ,Tabelas_Fretes.VL_D_Ate8C " +
          "       ,Tabelas_Fretes.VL_D_Ate9C " +
          "       ,Tabelas_Fretes.VL_D_Ate10C " +
          "       ,Tabelas_Fretes.VL_D_Ate11C " +
          "       ,Tabelas_Fretes.VL_D_Ate12C " +
          "       ,Tabelas_Fretes.VL_D_Ate13C " +
          "       ,Tabelas_Fretes.VL_D_Ate14C " +
          "       ,Tabelas_Fretes.VL_D_Ate15C " +
          "       ,Tabelas_Fretes.VL_D_Ate16C " +
          "       ,Tabelas_Fretes.VL_D_Ate17C " +
          "       ,Tabelas_Fretes.VL_D_Ate18C " +
          "       ,Tabelas_Fretes.VL_D_Ate19C " +
          "       ,Tabelas_Fretes.VL_D_Ate20C " +
          "       ,Tabelas_Fretes.VL_D_Ate21C " +
          "       ,Tabelas_Fretes.VL_D_Ate22C " +
          "       ,Tabelas_Fretes.VL_D_Ate23C " +
          "       ,Tabelas_Fretes.VL_D_Ate24C " +
          "       ,Tabelas_Fretes.VL_D_Ate25C " +
          "       ,Tabelas_Fretes.VL_D_Ate26C " +
          "       ,Tabelas_Fretes.VL_D_Ate27C " +
          "       ,Tabelas_Fretes.VL_D_Ate28C " +
          "       ,Tabelas_Fretes.VL_D_Ate29C " +
          "       ,Tabelas_Fretes.VL_D_Ate30C " +
          "       ,Tabelas_Fretes.VL_D_Ate50C " +
          "       ,Tabelas_Fretes.VL_D_Ate100C " +
          "       ,Tabelas_Fretes.VL_D_Ate1D " +
          "       ,Tabelas_Fretes.VL_D_Ate2D " +
          "       ,Tabelas_Fretes.VL_D_Ate3D " +
          "       ,Tabelas_Fretes.VL_D_Ate4D " +
          "       ,Tabelas_Fretes.VL_D_Ate5D " +
          "       ,Tabelas_Fretes.VL_D_Ate6D " +
          "       ,Tabelas_Fretes.VL_D_Ate7D " +
          "       ,Tabelas_Fretes.VL_D_Ate8D " +
          "       ,Tabelas_Fretes.VL_D_Ate9D " +
          "       ,Tabelas_Fretes.VL_D_Ate10D " +
          "       ,Tabelas_Fretes.VL_D_Ate11D " +
          "       ,Tabelas_Fretes.VL_D_Ate12D " +
          "       ,Tabelas_Fretes.VL_D_Ate13D " +
          "       ,Tabelas_Fretes.VL_D_Ate14D " +
          "       ,Tabelas_Fretes.VL_D_Ate15D " +
          "       ,Tabelas_Fretes.VL_D_Ate16D " +
          "       ,Tabelas_Fretes.VL_D_Ate17D " +
          "       ,Tabelas_Fretes.VL_D_Ate18D " +
          "       ,Tabelas_Fretes.VL_D_Ate19D " +
          "       ,Tabelas_Fretes.VL_D_Ate20D " +
          "       ,Tabelas_Fretes.VL_D_Ate21D " +
          "       ,Tabelas_Fretes.VL_D_Ate22D " +
          "       ,Tabelas_Fretes.VL_D_Ate23D " +
          "       ,Tabelas_Fretes.VL_D_Ate24D " +
          "       ,Tabelas_Fretes.VL_D_Ate25D " +
          "       ,Tabelas_Fretes.VL_D_Ate26D " +
          "       ,Tabelas_Fretes.VL_D_Ate27D " +
          "       ,Tabelas_Fretes.VL_D_Ate28D " +
          "       ,Tabelas_Fretes.VL_D_Ate29D " +
          "       ,Tabelas_Fretes.VL_D_Ate30D " +
          "       ,Tabelas_Fretes.VL_D_Ate50D " +
          "       ,Tabelas_Fretes.VL_D_Ate100D " +
          "       ,Tabelas_Fretes.VL_D_ExcedenteC " +
          "       ,Tabelas_Fretes.VL_D_Excedente2C " +
          "       ,Tabelas_Fretes.VL_D_Excedente3C " +
          "       ,Tabelas_Fretes.VL_D_ExcedenteD " +
          "       ,Tabelas_Fretes.VL_D_Excedente2D " +
          "       ,Tabelas_Fretes.VL_D_Excedente3D " +
          "       ,Tabelas_Fretes.PE_D_Ad_Valorem " +
          "       ,Produtos.DM_Unidade " +
          "       ,Tabelas_Fretes.PE_Reembolso " +
          "       ,Tabelas_Fretes.PE_Reembolso_Minimo " +
          "       ,Tabelas_Fretes.VL_Reembolso " +
          "       ,Tabelas_Fretes.VL_Especial1 " +
          "       ,Tabelas_Fretes.VL_Especial2 " +
          "       ,Tabelas_Fretes.VL_Especial3 " +
          "       ,Tabelas_Fretes.VL_Especial4 " +
          "       ,Tabelas_Fretes.VL_Especial5 " +
          "       ,Tabelas_Fretes.VL_Especial6 " +
          "       ,Tabelas_Fretes.VL_Especial7 " +
          "       ,Tabelas_Fretes.VL_Especial8 " +
          "       ,Tabelas_Fretes.VL_Especial9 " +
          "       ,Tabelas_Fretes.VL_Especial10 " +
          "       ,Tabelas_Fretes.VL_R_Ate10 " +
          "       ,Tabelas_Fretes.VL_R_Ate20 " +
          "       ,Tabelas_Fretes.VL_R_Ate30 " +
          "       ,Tabelas_Fretes.VL_R_Ate50 " +
          "       ,Tabelas_Fretes.VL_R_Ate70 " +
          "       ,Tabelas_Fretes.VL_R_Ate100 " +
          "       ,Tabelas_Fretes.VL_R_Ate150 " +
          "       ,Tabelas_Fretes.VL_R_Ate200 " +
          "       ,Tabelas_Fretes.VL_R_Acima200 " +
          "       ,Tabelas_Fretes.VL_R_Ate7500 " +
          "       ,Tabelas_Fretes.VL_R_Ate14500 " +
          "       ,Tabelas_Fretes.VL_R_Acima14500 " +
          "       ,Tabelas_Fretes.PE_AD_Valorem2 " +
          "       ,Tabelas_Fretes.VL_Suframa_Minimo " +
          "       ,Tabelas_Fretes.PE_Suframa " +
          "       ,Tabelas_Fretes.PE_Fluvial " +
          "       ,Tabelas_Fretes.VL_Fluvial_Minimo " +
          "       ,Tabelas_Fretes.VL_C_Pedagio " +
          "       ,Tabelas_Fretes.VL_TX_KM_Rodado " +
          "       ,Tabelas_Fretes.VL_TX_Col_Urg_200 " +
          "       ,Tabelas_Fretes.VL_TX_Col_Urg_1000 " +
          "       ,Tabelas_Fretes.VL_TX_Col_Urg_Ton " +
          "       ,Tabelas_Fretes.VL_TX_Ent_Urg_200 " +
          "       ,Tabelas_Fretes.VL_TX_Ent_Urg_1000 " +
          "       ,Tabelas_Fretes.VL_TX_Ent_Urg_Ton " +
          "       ,Tabelas_Fretes.PE_Desc_FP " +
          "       ,Tabelas_Fretes.PE_Desc_FV " +
          "       ,Tabelas_Fretes.VL_E_Ate1 " +
          "       ,Tabelas_Fretes.VL_E_1Kg " +
          "       ,Tabelas_Fretes.VL_E_Excedente " +
          "       ,Tabelas_Fretes.VL_E_Ad_Valorem " +
          "       ,Tabelas_Fretes.PE_E_Ad_Valorem " +
          "       ,Tabelas_Fretes.PE_Frete_Entrega " +
          "       ,Tabelas_Fretes.VL_D_Ate31C " +
          "       ,Tabelas_Fretes.VL_D_Ate32C " +
          "       ,Tabelas_Fretes.VL_D_Ate33C " +
          "       ,Tabelas_Fretes.VL_D_Ate34C " +
          "       ,Tabelas_Fretes.VL_D_Ate35C " +
          "       ,Tabelas_Fretes.VL_D_Ate001C " +
          "       ,Tabelas_Fretes.VL_D_Ate025C " +
          "       ,Tabelas_Fretes.VL_D_Ate030C " +
          "       ,Tabelas_Fretes.VL_D_Ate050C " +
          "       ,Tabelas_Fretes.VL_D_Ate075C " +
          "       ,Tabelas_Fretes.NR_Peso_TX_Coleta  " +
          "       ,Tabelas_Fretes.NR_Peso_TX_Entrega   " +
          "       ,Tabelas_Fretes.NR_Peso_TX_Minima  " +
          "       ,Tabelas_Fretes.NR_Peso_TX_Redespacho  " +
          "       ,Tabelas_Fretes.VL_TX_Redespacho  " +
          "       ,Tabelas_Fretes.VL_Excesso_TX_Redespacho  " +
          "       ,Tabelas_Fretes.DM_Aplicacao " +
          "       ,Tabelas_Fretes.VL_Pedagio_Minimo " +
          "       ,Tabelas_Fretes.NR_Prazo_Entrega_C " +
          "       ,Tabelas_Fretes.NR_Prazo_Entrega_D " +
          "       ,Tabelas_Fretes.NR_Prazo_Entrega_E " +
          "       ,Tabelas_Fretes.NR_Prazo_Entrega_R " +
          " FROM " +
          "		Tabelas_Fretes, Pessoas, Modal, Produtos " +
          " WHERE " +
          "		Tabelas_Fretes.OID_Produto = Produtos.OID_Produto " +
          " AND " +
          "		Tabelas_Fretes.OID_Modal = Modal.OID_Modal " +
          " AND " +
          "		Tabelas_Fretes.OID_Pessoa = Pessoas.OID_Pessoa ";

      if (filtro.getOID () > 0) {
        sql += " AND Tabelas_Fretes.OID_Tabela_Frete = " + filtro.getOID ();
      }

      if (JavaUtil.doValida (filtro.getOID_Pessoa ())) {
        sql += " AND Tabelas_Fretes.OID_Pessoa = '" + filtro.getOID_Pessoa () + "'";
      }

      if (filtro.getOID_Modal () > 0) {
        sql += " AND Tabelas_Fretes.OID_Modal = " + filtro.getOID_Modal ();
      }
      if (JavaUtil.doValida (filtro.getDM_Aplicacao ())) {
        sql += " AND Tabelas_Fretes.DM_Aplicacao ='" + filtro.getDM_Aplicacao () + "'";
      }
      sql += " ORDER BY Pessoas.NM_Razao_Social, DM_Aplicacao, NM_ORIGEM, NM_DESTINO";

      Statement stmt = conn.createStatement ();
      ResultSet cursor = stmt.executeQuery (sql);
      while (cursor.next ()) {
        p = new Tabela_FreteBean ();
        p.setOID (cursor.getLong (1));
        p.setOID_Produto (cursor.getLong (2));
        p.setOID_Modal (cursor.getLong (3));
        p.setOID_Pessoa (cursor.getString (4));
        p.setNR_Peso_Inicial (cursor.getDouble (5));
        p.setNR_Peso_Final (cursor.getDouble (6));
        p.setVL_Frete (cursor.getDouble (7));
        p.setVL_Frete_Kg (cursor.getDouble (8));
        p.setVL_Taxas (cursor.getDouble (9));
        p.setPE_Ad_Valorem (cursor.getDouble (10));
        p.setVL_Outros1 (cursor.getDouble (11));
        p.setVL_Outros2 (cursor.getDouble (12));
        p.setVL_Outros3 (cursor.getDouble (13));
        p.setVL_Outros4 (cursor.getDouble (14));
        p.setVL_Outros5 (cursor.getDouble (15));
        p.setVL_Outros8 (cursor.getDouble (16));
        p.setVL_Outros7 (cursor.getDouble (17));
        p.setVL_Outros6 (cursor.getDouble (18));
        p.setVL_Outros9 (cursor.getDouble (19));
        p.setDt_Stamp (cursor.getString (20));
        p.setUsuario_Stamp (cursor.getString (21));
        p.setDm_Stamp (cursor.getString (22));
        p.setNM_Razao_Social (cursor.getString (23));
        p.setCD_Produto (cursor.getString (24));
        p.setNM_Produto (cursor.getString (25));
        p.setCD_Modal (cursor.getString (26));
        p.setNM_Modal (cursor.getString (27));
        p.setOID_Origem (cursor.getLong (28));
        p.setOID_Destino (cursor.getLong (29));
        p.setNM_Origem (cursor.getString (30));
        p.setNM_Destino (cursor.getString (31));
        p.setDM_Origem (cursor.getString (32));
        p.setDM_Destino (cursor.getString (33));
        p.setDT_Vigencia (cursor.getString (34));
        p.setDT_Validade (cursor.getString (35));
        p.setDM_Tipo_Pedagio (cursor.getString (36));
        p.setVL_Pedagio (cursor.getDouble (37));
        p.setVL_Frete_Minimo (cursor.getDouble (38));
        p.setOID_Empresa (cursor.getLong ("OID_Empresa"));
        p.setOID_Pessoa_Redespacho (cursor.getString ("OID_Pessoa_Redespacho"));
        p.setNR_Prazo_Entrega (cursor.getLong ("NR_Prazo_Entrega"));
        p.setNR_Prazo_Faturamento (cursor.getLong ("NR_Prazo_Faturamento"));
        p.setNR_Peso_Minimo (cursor.getLong ("NR_Peso_Minimo"));
        p.setDM_ICMS (cursor.getString ("DM_ICMS"));
        p.setDM_Tipo_Tabela (cursor.getString ("DM_Tipo_Tabela"));
        p.setDM_Tipo_Peso (cursor.getString ("DM_Tipo_Peso"));
        p.setDM_Tipo_Peso_Taxas (cursor.getString ("DM_Tipo_Peso_Taxas"));
        p.setVL_Ademe_Minimo (cursor.getDouble ("VL_Ademe_Minimo"));
        p.setVL_Ad_Valorem_Minimo (cursor.getDouble ("VL_Ad_Valorem_Minimo"));
        p.setVL_Maximo_Nota_Fiscal (cursor.getDouble ("VL_Maximo_Nota_Fiscal"));
        p.setVL_Minimo_Nota_Fiscal (cursor.getDouble ("VL_Minimo_Nota_Fiscal"));
        p.setVL_Despacho (cursor.getDouble ("VL_Despacho"));
        p.setPE_Devolucao (cursor.getDouble ("PE_Devolucao"));
        p.setPE_Refaturamento (cursor.getDouble ("PE_Refaturamento"));
        p.setPE_Reentrega (cursor.getDouble ("PE_Reentrega"));
        p.setPE_Ademe (cursor.getDouble ("PE_Ademe"));
        p.setDM_Unidade (cursor.getString ("DM_Unidade"));
        p.setDM_Aplicacao (cursor.getString ("DM_Aplicacao"));
        p.setVL_Despacho (cursor.getDouble ("VL_Despacho"));

        p.setVL_C_Ate25 (cursor.getDouble ("VL_C_Ate25"));
        p.setVL_C_Ate50 (cursor.getDouble ("VL_C_Ate50"));
        p.setVL_C_Ate300 (cursor.getDouble ("VL_C_Ate300"));
        p.setVL_C_Ate500 (cursor.getDouble ("VL_C_Ate500"));
        p.setVL_C_Ate1000 (cursor.getDouble ("VL_C_Ate1000"));
        p.setVL_C_Acima1000 (cursor.getDouble ("VL_C_Acima1000"));


        p.setVL_TX_Coleta (cursor.getDouble ("VL_TX_Coleta"));
        p.setVL_TX_Entrega (cursor.getDouble ("VL_TX_Entrega"));
        p.setVL_TX_Exc_Coleta (cursor.getDouble ("VL_TX_Exc_Coleta"));
        p.setVL_TX_Exc_Entrega (cursor.getDouble ("VL_TX_Exc_Entrega"));
        p.setPE_C_Ad_Valorem (cursor.getDouble ("PE_C_Ad_Valorem"));
        p.setVL_C_Taxa_Minima (cursor.getDouble ("VL_C_Taxa_Minima"));

        p.setVL_D_Ate1C (cursor.getDouble ("VL_D_Ate1C"));
        p.setVL_D_Ate2C (cursor.getDouble ("VL_D_Ate2C"));
        p.setVL_D_Ate3C (cursor.getDouble ("VL_D_Ate3C"));
        p.setVL_D_Ate4C (cursor.getDouble ("VL_D_Ate4C"));
        p.setVL_D_Ate5C (cursor.getDouble ("VL_D_Ate5C"));
        p.setVL_D_Ate6C (cursor.getDouble ("VL_D_Ate6C"));
        p.setVL_D_Ate7C (cursor.getDouble ("VL_D_Ate7C"));
        p.setVL_D_Ate8C (cursor.getDouble ("VL_D_Ate8C"));
        p.setVL_D_Ate9C (cursor.getDouble ("VL_D_Ate9C"));
        p.setVL_D_Ate10C (cursor.getDouble ("VL_D_Ate10C"));
        p.setVL_D_Ate11C (cursor.getDouble ("VL_D_Ate11C"));
        p.setVL_D_Ate12C (cursor.getDouble ("VL_D_Ate12C"));
        p.setVL_D_Ate13C (cursor.getDouble ("VL_D_Ate13C"));
        p.setVL_D_Ate14C (cursor.getDouble ("VL_D_Ate14C"));
        p.setVL_D_Ate15C (cursor.getDouble ("VL_D_Ate15C"));
        p.setVL_D_Ate16C (cursor.getDouble ("VL_D_Ate16C"));
        p.setVL_D_Ate17C (cursor.getDouble ("VL_D_Ate17C"));
        p.setVL_D_Ate18C (cursor.getDouble ("VL_D_Ate18C"));
        p.setVL_D_Ate19C (cursor.getDouble ("VL_D_Ate19C"));
        p.setVL_D_Ate20C (cursor.getDouble ("VL_D_Ate20C"));
        p.setVL_D_Ate21C (cursor.getDouble ("VL_D_Ate21C"));
        p.setVL_D_Ate22C (cursor.getDouble ("VL_D_Ate22C"));
        p.setVL_D_Ate23C (cursor.getDouble ("VL_D_Ate23C"));
        p.setVL_D_Ate24C (cursor.getDouble ("VL_D_Ate24C"));
        p.setVL_D_Ate25C (cursor.getDouble ("VL_D_Ate25C"));
        p.setVL_D_Ate26C (cursor.getDouble ("VL_D_Ate26C"));
        p.setVL_D_Ate27C (cursor.getDouble ("VL_D_Ate27C"));
        p.setVL_D_Ate28C (cursor.getDouble ("VL_D_Ate28C"));
        p.setVL_D_Ate29C (cursor.getDouble ("VL_D_Ate29C"));
        p.setVL_D_Ate30C (cursor.getDouble ("VL_D_Ate30C"));
        p.setVL_D_Ate31C (cursor.getDouble ("VL_D_Ate31C"));
        p.setVL_D_Ate32C (cursor.getDouble ("VL_D_Ate32C"));
        p.setVL_D_Ate33C (cursor.getDouble ("VL_D_Ate33C"));
        p.setVL_D_Ate34C (cursor.getDouble ("VL_D_Ate34C"));
        p.setVL_D_Ate35C (cursor.getDouble ("VL_D_Ate35C"));
        p.setVL_D_Ate50C (cursor.getDouble ("VL_D_Ate50C"));
        p.setVL_D_Ate100C (cursor.getDouble ("VL_D_Ate100C"));

        p.setVL_D_Ate001C (cursor.getDouble ("VL_D_Ate001C"));
        p.setVL_D_Ate025C (cursor.getDouble ("VL_D_Ate025C"));
        p.setVL_D_Ate030C (cursor.getDouble ("VL_D_Ate030C"));
        p.setVL_D_Ate050C (cursor.getDouble ("VL_D_Ate050C"));
        p.setVL_D_Ate075C (cursor.getDouble ("VL_D_Ate075C"));

        p.setVL_D_Ate1D (cursor.getDouble ("VL_D_Ate1D"));
        p.setVL_D_Ate2D (cursor.getDouble ("VL_D_Ate2D"));
        p.setVL_D_Ate3D (cursor.getDouble ("VL_D_Ate3D"));
        p.setVL_D_Ate4D (cursor.getDouble ("VL_D_Ate4D"));
        p.setVL_D_Ate5D (cursor.getDouble ("VL_D_Ate5D"));
        p.setVL_D_Ate6D (cursor.getDouble ("VL_D_Ate6D"));
        p.setVL_D_Ate7D (cursor.getDouble ("VL_D_Ate7D"));
        p.setVL_D_Ate8D (cursor.getDouble ("VL_D_Ate8D"));
        p.setVL_D_Ate9D (cursor.getDouble ("VL_D_Ate9D"));
        p.setVL_D_Ate10D (cursor.getDouble ("VL_D_Ate10D"));
        p.setVL_D_Ate11D (cursor.getDouble ("VL_D_Ate11D"));
        p.setVL_D_Ate12D (cursor.getDouble ("VL_D_Ate12D"));
        p.setVL_D_Ate13D (cursor.getDouble ("VL_D_Ate13D"));
        p.setVL_D_Ate14D (cursor.getDouble ("VL_D_Ate14D"));
        p.setVL_D_Ate15D (cursor.getDouble ("VL_D_Ate15D"));
        p.setVL_D_Ate16D (cursor.getDouble ("VL_D_Ate16D"));
        p.setVL_D_Ate17D (cursor.getDouble ("VL_D_Ate17D"));
        p.setVL_D_Ate18D (cursor.getDouble ("VL_D_Ate18D"));
        p.setVL_D_Ate19D (cursor.getDouble ("VL_D_Ate19D"));
        p.setVL_D_Ate20D (cursor.getDouble ("VL_D_Ate20D"));
        p.setVL_D_Ate21D (cursor.getDouble ("VL_D_Ate21D"));
        p.setVL_D_Ate22D (cursor.getDouble ("VL_D_Ate22D"));
        p.setVL_D_Ate23D (cursor.getDouble ("VL_D_Ate23D"));
        p.setVL_D_Ate24D (cursor.getDouble ("VL_D_Ate24D"));
        p.setVL_D_Ate25D (cursor.getDouble ("VL_D_Ate25D"));
        p.setVL_D_Ate26D (cursor.getDouble ("VL_D_Ate26D"));
        p.setVL_D_Ate27D (cursor.getDouble ("VL_D_Ate27D"));
        p.setVL_D_Ate28D (cursor.getDouble ("VL_D_Ate28D"));
        p.setVL_D_Ate29D (cursor.getDouble ("VL_D_Ate29D"));
        p.setVL_D_Ate30D (cursor.getDouble ("VL_D_Ate30D"));
        p.setVL_D_Ate50D (cursor.getDouble ("VL_D_Ate50D"));
        p.setVL_D_Ate100D (cursor.getDouble ("VL_D_Ate100D"));


        p.setVL_D_ExcedenteC (cursor.getDouble ("VL_D_ExcedenteC"));
        p.setVL_D_ExcedenteD (cursor.getDouble ("VL_D_ExcedenteD"));
        p.setVL_D_Excedente2C (cursor.getDouble ("VL_D_Excedente2C"));
        p.setVL_D_Excedente2D (cursor.getDouble ("VL_D_Excedente2D"));
        p.setVL_D_Excedente3C (cursor.getDouble ("VL_D_Excedente3C"));
        p.setVL_D_Excedente3D (cursor.getDouble ("VL_D_Excedente3D"));
        p.setPE_D_Ad_Valorem (cursor.getDouble ("PE_D_Ad_Valorem"));

        p.setPE_Reembolso (cursor.getDouble ("PE_Reembolso"));
        p.setPE_Reembolso_Minimo (cursor.getDouble ("PE_Reembolso_Minimo"));
        p.setVL_Reembolso (cursor.getDouble ("VL_Reembolso"));

        p.setVL_Especial1 (cursor.getDouble ("VL_Especial1"));
        p.setVL_Especial2 (cursor.getDouble ("VL_Especial2"));
        p.setVL_Especial3 (cursor.getDouble ("VL_Especial3"));
        p.setVL_Especial4 (cursor.getDouble ("VL_Especial4"));
        p.setVL_Especial5 (cursor.getDouble ("VL_Especial5"));
        p.setVL_Especial6 (cursor.getDouble ("VL_Especial6"));
        p.setVL_Especial7 (cursor.getDouble ("VL_Especial7"));
        p.setVL_Especial8 (cursor.getDouble ("VL_Especial8"));
        p.setVL_Especial9 (cursor.getDouble ("VL_Especial9"));
        p.setVL_Especial10 (cursor.getDouble ("VL_Especial10"));
        p.setVL_R_Ate10 (cursor.getDouble ("VL_R_Ate10"));
        p.setVL_R_Ate20 (cursor.getDouble ("VL_R_Ate20"));
        p.setVL_R_Ate30 (cursor.getDouble ("VL_R_Ate30"));
        p.setVL_R_Ate50 (cursor.getDouble ("VL_R_Ate50"));
        p.setVL_R_Ate70 (cursor.getDouble ("VL_R_Ate70"));
        p.setVL_R_Ate100 (cursor.getDouble ("VL_R_Ate100"));
        p.setVL_R_Ate150 (cursor.getDouble ("VL_R_Ate150"));
        p.setVL_R_Ate200 (cursor.getDouble ("VL_R_Ate200"));
        p.setVL_R_Acima200 (cursor.getDouble ("VL_R_Acima200"));
        p.setVL_R_Ate7500 (cursor.getDouble ("VL_R_Ate7500"));
        p.setVL_R_Ate14500 (cursor.getDouble ("VL_R_Ate14500"));
        p.setVL_R_Acima14500 (cursor.getDouble ("VL_R_Acima14500"));
        p.setPE_AD_Valorem2 (cursor.getDouble ("PE_AD_Valorem2"));
        p.setVL_Suframa_Minimo (cursor.getDouble ("VL_Suframa_Minimo"));
        p.setPE_Suframa (cursor.getDouble ("PE_Suframa"));
        p.setPE_Fluvial (cursor.getDouble ("PE_Fluvial"));
        p.setVL_Fluvial_Minimo (cursor.getDouble ("VL_Fluvial_Minimo"));
        p.setVL_C_Pedagio (cursor.getDouble ("VL_C_Pedagio"));
        p.setVL_TX_KM_Rodado (cursor.getDouble ("VL_TX_KM_Rodado"));
        p.setVL_TX_Col_Urg_200 (cursor.getDouble ("VL_TX_Col_Urg_200"));
        p.setVL_TX_Col_Urg_1000 (cursor.getDouble ("VL_TX_Col_Urg_1000"));
        p.setVL_TX_Col_Urg_Ton (cursor.getDouble ("VL_TX_Col_Urg_Ton"));
        p.setVL_TX_Ent_Urg_200 (cursor.getDouble ("VL_TX_Ent_Urg_200"));
        p.setVL_TX_Ent_Urg_1000 (cursor.getDouble ("VL_TX_Ent_Urg_1000"));
        p.setVL_TX_Ent_Urg_Ton (cursor.getDouble ("VL_TX_Ent_Urg_Ton"));
        p.setPE_Desc_FP (cursor.getDouble ("PE_Desc_FP"));
        p.setPE_Desc_FV (cursor.getDouble ("PE_Desc_FV"));
        p.setVL_E_Ate1 (cursor.getDouble ("VL_E_Ate1"));
        p.setVL_E_1Kg (cursor.getDouble ("VL_E_1Kg"));
        p.setVL_E_Excedente (cursor.getDouble ("VL_E_Excedente"));
        p.setVL_E_Ad_Valorem (cursor.getDouble ("VL_E_Ad_Valorem"));
        p.setPE_E_Ad_Valorem (cursor.getDouble ("PE_E_Ad_Valorem"));
        p.setPE_Frete_Entrega (cursor.getDouble ("PE_Frete_Entrega"));


        p.setNR_Peso_TX_Coleta (cursor.getDouble ("NR_Peso_TX_Coleta"));
        p.setNR_Peso_TX_Entrega (cursor.getDouble ("NR_Peso_TX_Entrega"));
        p.setNR_Peso_TX_Minima (cursor.getDouble ("NR_Peso_TX_Minima"));
        p.setNR_Peso_TX_Redespacho (cursor.getDouble ("NR_Peso_TX_Redespacho"));
        p.setVL_TX_Redespacho (cursor.getDouble ("VL_TX_Redespacho"));
        p.setVL_Excesso_TX_Redespacho (cursor.getDouble ("VL_Excesso_TX_Redespacho"));

        p.setVL_Pedagio_Minimo (cursor.getDouble ("VL_Pedagio_Minimo"));

        p.setNR_Prazo_Entrega_C (cursor.getDouble ("NR_Prazo_Entrega_C"));
        p.setNR_Prazo_Entrega_D (cursor.getDouble ("NR_Prazo_Entrega_D"));
        p.setNR_Prazo_Entrega_E (cursor.getDouble ("NR_Prazo_Entrega_E"));
        p.setNR_Prazo_Entrega_R (cursor.getDouble ("NR_Prazo_Entrega_R"));

        toReturn.add (p);
      }
      cursor.close ();
      stmt.close ();
      conn.close ();
    }
    catch (SQLException e) {
      throw new Excecoes (e.getMessage () , e , Tabela_FreteBean.class.getName () , "getByOID(long oid)");
    }
    return toReturn;
  }


  //*** Relatório de Movimentos de Ordem de Servico
  public void relTabela_Frete (HttpServletRequest request , HttpServletResponse response) throws Excecoes {
    String Relatorio = request.getParameter ("Relatorio");
    String oid_Pessoa = request.getParameter ("oid_Pessoa");

    String descFiltro = "";    
	    if (JavaUtil.doValida (Relatorio)) {
	      Relatorio = "Tabela_Frete_Cliente";
	    }
	    if (!JavaUtil.doValida (Relatorio)) {
	      throw new Mensagens ("Nome do Relatório não informado!");
	    }
	
	    Tabela_FreteED ed = new Tabela_FreteED ();
	
	    if (JavaUtil.doValida (oid_Pessoa)) {
	      ed.setOID_Pessoa(oid_Pessoa);
	      descFiltro += " Cliente: " + oid_Pessoa;
	    }
	    
    ed.setDescFiltro (descFiltro);
    ed.setNomeRelatorio(Relatorio);
    ed.setResponse(response);
    
    new Tabela_FreteRN ().relTabela_Frete (ed);
    
  }

  
  
  
  public void setDM_Tipo_Pedagio (String DM_Tipo_Pedagio) {
    this.DM_Tipo_Pedagio = DM_Tipo_Pedagio;
  }

  public String getDM_Tipo_Pedagio () {
    return DM_Tipo_Pedagio;
  }

  public void setVL_Frete_Valor_Minimo (double VL_Frete_Valor_Minimo) {
    this.VL_Frete_Valor_Minimo = VL_Frete_Valor_Minimo;
  }

  public double getVL_Frete_Valor_Minimo () {
    return VL_Frete_Valor_Minimo;
  }

  public void setVL_Pedagio (double VL_Pedagio) {
    this.VL_Pedagio = VL_Pedagio;
  }

  public double getVL_Pedagio () {
    return VL_Pedagio;
  }

  public void setVL_Frete_Minimo (double VL_Frete_Minimo) {
    this.VL_Frete_Minimo = VL_Frete_Minimo;
  }

  public double getVL_Frete_Minimo () {
    return VL_Frete_Minimo;
  }

  public long getOID_Empresa () {
    return OID_Empresa;
  }

  public void setOID_Empresa (long OID_Empresa) {
    this.OID_Empresa = OID_Empresa;
  }

  public String getOID_Pessoa_Redespacho () {
    return OID_Pessoa_Redespacho;
  }

  public void setOID_Pessoa_Redespacho (String OID_Pessoa_Redespacho) {
    this.OID_Pessoa_Redespacho = OID_Pessoa_Redespacho;
  }

  public long getNR_Prazo_Entrega () {
    return NR_Prazo_Entrega;
  }

  public void setNR_Prazo_Entrega (long NR_Prazo_Entrega) {
    this.NR_Prazo_Entrega = NR_Prazo_Entrega;
  }

  public long getNR_Prazo_Faturamento () {
    return NR_Prazo_Faturamento;
  }

  public void setNR_Prazo_Faturamento (long NR_Prazo_Faturamento) {
    this.NR_Prazo_Faturamento = NR_Prazo_Faturamento;
  }

  public long getNR_Peso_Minimo () {
    return NR_Peso_Minimo;
  }

  public void setNR_Peso_Minimo (long NR_Peso_Minimo) {
    this.NR_Peso_Minimo = NR_Peso_Minimo;
  }

  public String getDM_ICMS () {
    return DM_ICMS;
  }

  public void setDM_ICMS (String DM_ICMS) {
    this.DM_ICMS = DM_ICMS;
  }

  public String getDM_Tipo_Tabela () {
    return DM_Tipo_Tabela;
  }

  public void setDM_Tipo_Tabela (String DM_Tipo_Tabela) {
    this.DM_Tipo_Tabela = DM_Tipo_Tabela;
  }

  public String getDM_Tipo_Peso () {
    return DM_Tipo_Peso;
  }

  public void setDM_Tipo_Peso (String DM_Tipo_Peso) {
    this.DM_Tipo_Peso = DM_Tipo_Peso;
  }

  public String getDM_Tipo_Peso_Taxas () {
	    return DM_Tipo_Peso_Taxas;
  }
  
  public void setDM_Tipo_Peso_Taxas (String DM_Tipo_Peso_Taxas) {
	    this.DM_Tipo_Peso_Taxas = DM_Tipo_Peso_Taxas;
  }
  
  public double getVL_Ademe_Minimo () {
    return VL_Ademe_Minimo;
  }

  public void setVL_Ademe_Minimo (double VL_Ademe_Minimo) {
    this.VL_Ademe_Minimo = VL_Ademe_Minimo;
  }

  public double getVL_Ad_Valorem_Minimo () {
    return VL_Ad_Valorem_Minimo;
  }

  public void setVL_Ad_Valorem_Minimo (double VL_Ad_Valorem_Minimo) {
    this.VL_Ad_Valorem_Minimo = VL_Ad_Valorem_Minimo;
  }

  public double getVL_Maximo_Nota_Fiscal () {
    return VL_Maximo_Nota_Fiscal;
  }

  public void setVL_Maximo_Nota_Fiscal (double VL_Maximo_Nota_Fiscal) {
    this.VL_Maximo_Nota_Fiscal = VL_Maximo_Nota_Fiscal;
  }

  public double getVL_Minimo_Nota_Fiscal () {
    return VL_Minimo_Nota_Fiscal;
  }

  public void setVL_Minimo_Nota_Fiscal (double VL_Minimo_Nota_Fiscal) {
    this.VL_Minimo_Nota_Fiscal = VL_Minimo_Nota_Fiscal;
  }

  public double getVL_Despacho () {
    return VL_Despacho;
  }

  public void setVL_Despacho (double VL_Despacho) {
    this.VL_Despacho = VL_Despacho;
  }

  public double getPE_Devolucao () {
    return PE_Devolucao;
  }

  public void setPE_Devolucao (double PE_Devolucao) {
    this.PE_Devolucao = PE_Devolucao;
  }

  public double getPE_Refaturamento () {
    return PE_Refaturamento;
  }

  public void setPE_Refaturamento (double PE_Refaturamento) {
    this.PE_Refaturamento = PE_Refaturamento;
  }

  public double getPE_Reentrega () {
    return PE_Reentrega;
  }

  public void setPE_Reentrega (double PE_Reentrega) {
    this.PE_Reentrega = PE_Reentrega;
  }

  public double getPE_Ademe () {
    return PE_Ademe;
  }

  public void setPE_Ademe (double PE_Ademe) {
    this.PE_Ademe = PE_Ademe;
  }

  public String getDM_Unidade () {
    return DM_Unidade;
  }

  public void setDM_Unidade (String DM_Unidade) {
    this.DM_Unidade = DM_Unidade;
  }

  public double getVL_R_Acima200 () {
    return this.VL_R_Acima200;
  }

  public void setVL_R_Acima200 (double acima200) {
    this.VL_R_Acima200 = acima200;
  }

  public double getVL_R_Ate10 () {
    return this.VL_R_Ate10;
  }

  public void setVL_R_Ate10 (double ate10) {
    this.VL_R_Ate10 = ate10;
  }

  public double getVL_R_Ate100 () {
    return this.VL_R_Ate100;
  }

  public void setVL_R_Ate100 (double ate100) {
    this.VL_R_Ate100 = ate100;
  }

  public double getVL_R_Ate150 () {
    return this.VL_R_Ate150;
  }

  public void setVL_R_Ate150 (double ate150) {
    this.VL_R_Ate150 = ate150;
  }

  public double getVL_R_Ate20 () {
    return this.VL_R_Ate20;
  }

  public void setVL_R_Ate20 (double ate20) {
    this.VL_R_Ate20 = ate20;
  }

  public double getVL_R_Ate200 () {
    return this.VL_R_Ate200;
  }

  public void setVL_R_Ate200 (double ate200) {
    this.VL_R_Ate200 = ate200;
  }

  public double getVL_R_Ate30 () {
    return this.VL_R_Ate30;
  }

  public void setVL_R_Ate30 (double ate30) {
    this.VL_R_Ate30 = ate30;
  }

  public double getVL_R_Ate50 () {
    return this.VL_R_Ate50;
  }

  public void setVL_R_Ate50 (double ate50) {
    this.VL_R_Ate50 = ate50;
  }

  public double getVL_R_Ate70 () {
    return this.VL_R_Ate70;
  }

  public void setVL_R_Ate70 (double ate70) {
    this.VL_R_Ate70 = ate70;
  }

  public double getPE_AD_Valorem2 () {
    return this.PE_AD_Valorem2;
  }

  public void setPE_AD_Valorem2 (double valorem2) {
    this.PE_AD_Valorem2 = valorem2;
  }

  public double getPE_Fluvial () {
    return this.PE_Fluvial;
  }

  public void setPE_Fluvial (double fluvial) {
    this.PE_Fluvial = fluvial;
  }

  public double getPE_Suframa () {
    return this.PE_Suframa;
  }

  public void setPE_Suframa (double suframa) {
    this.PE_Suframa = suframa;
  }

  public double getVL_Fluvial_Minimo () {
    return this.VL_Fluvial_Minimo;
  }

  public void setVL_Fluvial_Minimo (double fluvial_Minimo) {
    this.VL_Fluvial_Minimo = fluvial_Minimo;
  }

  public double getVL_R_Acima14500 () {
    return this.VL_R_Acima14500;
  }

  public void setVL_R_Acima14500 (double acima14500) {
    this.VL_R_Acima14500 = acima14500;
  }

  public double getVL_R_Ate14500 () {
    return this.VL_R_Ate14500;
  }

  public void setVL_R_Ate14500 (double ate14500) {
    this.VL_R_Ate14500 = ate14500;
  }

  public double getVL_R_Ate7500 () {
    return this.VL_R_Ate7500;
  }

  public void setVL_R_Ate7500 (double ate7500) {
    this.VL_R_Ate7500 = ate7500;
  }

  public double getVL_Suframa_Minimo () {
    return this.VL_Suframa_Minimo;
  }

  public void setVL_Suframa_Minimo (double suframa_Minimo) {
    this.VL_Suframa_Minimo = suframa_Minimo;
  }

  public double getPE_Desc_FP () {
    return this.PE_Desc_FP;
  }

  public void setPE_Desc_FP (double desc_FP) {
    this.PE_Desc_FP = desc_FP;
  }

  public double getPE_Desc_FV () {
    return this.PE_Desc_FV;
  }

  public void setPE_Desc_FV (double desc_FV) {
    this.PE_Desc_FV = desc_FV;
  }

  public double getPE_E_Ad_Valorem () {
    return this.PE_E_Ad_Valorem;
  }

  public void setPE_E_Ad_Valorem (double ad_Valorem) {
    this.PE_E_Ad_Valorem = ad_Valorem;
  }

  public double getPE_Frete_Entrega () {
    return this.PE_Frete_Entrega;
  }

  public void setPE_Frete_Entrega (double frete_Entrega) {
    this.PE_Frete_Entrega = frete_Entrega;
  }

  public double getVL_C_Pedagio () {
    return this.VL_C_Pedagio;
  }

  public void setVL_C_Pedagio (double pedagio) {
    this.VL_C_Pedagio = pedagio;
  }

  public double getVL_E_1Kg () {
    return this.VL_E_1Kg;
  }

  public void setVL_E_1Kg (double kg) {
    this.VL_E_1Kg = kg;
  }

  public double getVL_E_Ad_Valorem () {
    return this.VL_E_Ad_Valorem;
  }

  public void setVL_E_Ad_Valorem (double ad_Valorem) {
    this.VL_E_Ad_Valorem = ad_Valorem;
  }

  public double getVL_E_Ate1 () {
    return this.VL_E_Ate1;
  }

  public void setVL_E_Ate1 (double ate1) {
    this.VL_E_Ate1 = ate1;
  }

  public double getVL_E_Excedente () {
    return this.VL_E_Excedente;
  }

  public void setVL_E_Excedente (double excedente) {
    this.VL_E_Excedente = excedente;
  }

  public double getVL_TX_Col_Urg_1000 () {
    return this.VL_TX_Col_Urg_1000;
  }

  public void setVL_TX_Col_Urg_1000 (double col_Urg_1000) {
    this.VL_TX_Col_Urg_1000 = col_Urg_1000;
  }

  public double getVL_TX_Col_Urg_200 () {
    return this.VL_TX_Col_Urg_200;
  }

  public void setVL_TX_Col_Urg_200 (double col_Urg_200) {
    this.VL_TX_Col_Urg_200 = col_Urg_200;
  }

  public double getVL_TX_Col_Urg_Ton () {
    return this.VL_TX_Col_Urg_Ton;
  }

  public void setVL_TX_Col_Urg_Ton (double col_Urg_Ton) {
    this.VL_TX_Col_Urg_Ton = col_Urg_Ton;
  }

  public double getVL_TX_Ent_Urg_1000 () {
    return this.VL_TX_Ent_Urg_1000;
  }

  public void setVL_TX_Ent_Urg_1000 (double ent_Urg_1000) {
    this.VL_TX_Ent_Urg_1000 = ent_Urg_1000;
  }

  public double getVL_TX_Ent_Urg_200 () {
    return this.VL_TX_Ent_Urg_200;
  }

  public void setVL_TX_Ent_Urg_200 (double ent_Urg_200) {
    this.VL_TX_Ent_Urg_200 = ent_Urg_200;
  }

  public double getVL_TX_Ent_Urg_Ton () {
    return this.VL_TX_Ent_Urg_Ton;
  }

  public void setVL_TX_Ent_Urg_Ton (double ent_Urg_Ton) {
    this.VL_TX_Ent_Urg_Ton = ent_Urg_Ton;
  }

  public double getVL_TX_KM_Rodado () {
    return this.VL_TX_KM_Rodado;
  }

  public void setVL_TX_KM_Rodado (double rodado) {
    this.VL_TX_KM_Rodado = rodado;
  }

  public String getDM_Localizado () {
    return (getOID () == 0) ? "N" : "S";
  }

  public String getDM_Tipo_Localizacao_Destino () {
    return this.DM_Tipo_Localizacao_Destino;
  }

  public void setDM_Tipo_Localizacao_Destino (String tipo_Localizacao_Destino) {
    this.DM_Tipo_Localizacao_Destino = tipo_Localizacao_Destino;
  }

public String getDM_Inclusao() {
	return DM_Inclusao;
}

public void setDM_Inclusao(String copia_Inclusao) {
	DM_Inclusao = copia_Inclusao;
}

public double getVL_Pedagio_Minimo() {
	return VL_Pedagio_Minimo;
}

public void setVL_Pedagio_Minimo(double pedagio_Minimo) {
	VL_Pedagio_Minimo = pedagio_Minimo;
}
}
