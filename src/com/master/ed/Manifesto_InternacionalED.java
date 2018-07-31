package com.master.ed;

public class Manifesto_InternacionalED extends MasterED{

  private String OID_Conhecimento;
  private String OID_Pessoa;
  private String OID_Pessoa_Destinatario;
  private String DT_Emissao;
  private String DT_Emissao_Inicial;
  private String DT_Emissao_Final;
  private String DT_Fim;
  private long NR_Conhecimento;
  private long OID_Unidade;
  private int OID_Unidade_Origem;
  private int OID_Unidade_Destino;
  private int OID_Unidade_Fronteira;
  
  private String CD_Unidade;
  private String NM_Pessoa_Remetente;
  private String NM_Pessoa_Destinatario;
  private String DM_Expresso;
  private String DM_Tipo_Operacao;
  
  
  private long NR_Manifesto_Internacional;
  private String NM_Manifesto_Internacional;
  private String DT_Previsao_Chegada;
  private String HR_Previsao_Chegada;
  private long NR_Odometro_Inicial;
  private String DT_Troca_Motorista;
  private long NR_Odometro_Troca_Motorista;
  private long OID_Cidade;
  private long OID_Seguradora;
  private String NM_Liberacao_Seguradora;
  private String NR_Placa;
  private String OID_Veiculo;
  private String NM_Serie;
  private String NM_Cidade_Destino;
  private String NM_Cidade_Unidade;
  private String OID_Pessoa_Reveza;
  private String OID_Manifesto_Internacional;
  private long NR_Acerto;
  private long OID_Acerto;
  private String DM_Lancado_Ordem_Frete;
  private String OID_Veiculo_Carreta;
  private String CD_Roteiro;
  private String NM_Roteiro;
  private long OID_Embarque;
  private long NR_Embarque;
  private long OID_Cidade_Origem;
  private double VL_Total_Frete_CTRC;
  private String NR_Master;
  private double NR_Total_Peso_CTRC;
  private String DM_Transito_Aduaneiro;
  private long OID_Cidade_Alfandega;
  private long OID_Cidade_Destino;
  private long OID_Aduana;
  private String NM_Aduana;
  private String CD_Aduana;
  private String NR_Aduana;
  private String TX_Observacao1;
  private String TX_Observacao2;
  private String TX_Observacao3;
  private String TX_Observacao4;
  private String TX_Observacao5;
  private String TX_Observacao6;
  private String TX_Observacao7;
  private String TX_Observacao8;
  private String TX_Observacao9;
  private String TX_Observacao10;
  private String TX_Observacao11;
  private String TX_Observacao12;
  private String TX_Observacao13;
  private String TX_Observacao14;
  private String TX_Observacao15;
  private String TX_Observacao16;
  private String TX_Observacao17;
  private String TX_Observacao18;
  private String NM_Lacre;
  private String TX_Rota1;
  private String TX_Rota2;
  private String TX_Rota3;
  private String TX_Rota4;
  private String TX_Rota5;
  private String TX_Rota6;
  private String TX_Rota7;
  private String TX_Rota8;
  private String TX_Rota9;
  private String CD_Pais;
  private String NR_Permisso;
  private String DM_Exportacao_Importacao;
  private long OID_Aduana_Destino;

  private String OID_Pessoa_Permisso;
  private String OID_Pessoa_Permisso2;
  
  private String NM_Pessoa_Proprietario;
  
  private String NM_Pais;
  
  private long OID_Tipo_Ocorrencia;
  private String DT_Chegada;
  private String HR_Chegada;
  private String DM_Tipo;
  private String NM_Pessoa_Entrega;
  private String DM_Situacao;
  
  private java.util.Collection MICDetalhes;
  private String dataRel;
  private String siglaRel;
  private double VL_Peso;
  private double VL_Peso_Cubado;
  private double VL_Nota_Fiscal;
  private double NR_Volumes;
  private double VL_Total_Frete;
  private String NM_Conhecimento;
  
  private double VL_Ordem_Frete;
  private double VL_Adto;
  private double VL_Saldo;
  private double VL_INSS;
  private double VL_IRRF;
  private double VL_Set_Senat;
  private String NM_Seguradora;
  private String NM_Ordem_Frete;
  private String NM_Proprietario;
  
  private String NM_End_Proprietario;
  private String NM_Cid_UF_Pais_Proprietario;

  public void setOID_Conhecimento(String OID_Conhecimento) {
    this.OID_Conhecimento = OID_Conhecimento;
  }
  public String getOID_Conhecimento() {
    return OID_Conhecimento;
  }
  public void setOID_Manifesto_Internacional(String OID_Manifesto_Internacional) {
    this.OID_Manifesto_Internacional = OID_Manifesto_Internacional;
  }
  public String getOID_Manifesto_Internacional() {
    return OID_Manifesto_Internacional;
  }
  public void setOID_Pessoa(String OID_Pessoa) {
    this.OID_Pessoa = OID_Pessoa;
  }
  public String getOID_Pessoa() {
    return OID_Pessoa;
  }
  public void setOID_Pessoa_Destinatario(String OID_Pessoa_Destinatario) {
    this.OID_Pessoa_Destinatario = OID_Pessoa_Destinatario;
  }
  public String getOID_Pessoa_Destinatario() {
    return OID_Pessoa_Destinatario;
  }
  public void setOID_Unidade(long OID_Unidade) {
    this.OID_Unidade = OID_Unidade;
  }
  public long getOID_Unidade() {
    return OID_Unidade;
  }
  public void setDT_Emissao(String DT_Emissao) {
    this.DT_Emissao = DT_Emissao;
  }
  public String getDT_Emissao() {
    return DT_Emissao;
  }
  public void setNR_Conhecimento(long NR_Conhecimento) {
    this.NR_Conhecimento = NR_Conhecimento;
  }
  public long getNR_Conhecimento() {
    return NR_Conhecimento;
  }
  public void setCD_Unidade(String CD_Unidade) {
    this.CD_Unidade = CD_Unidade;
  }
  public String getCD_Unidade() {
    return CD_Unidade;
  }
  public void setNM_Pessoa_Remetente(String NM_Pessoa_Remetente) {
    this.NM_Pessoa_Remetente = NM_Pessoa_Remetente;
  }
  public String getNM_Pessoa_Remetente() {
    return NM_Pessoa_Remetente;
  }
  public void setNM_Pessoa_Destinatario(String NM_Pessoa_Destinatario) {
    this.NM_Pessoa_Destinatario = NM_Pessoa_Destinatario;
  }
  public String getNM_Pessoa_Destinatario() {
    return NM_Pessoa_Destinatario;
  }
  public void setDM_Expresso(String DM_Expresso) {
    this.DM_Expresso = DM_Expresso;
  }
  public String getDM_Expresso() {
    return DM_Expresso;
  }

  public void setDM_Tipo_Operacao(String DM_Tipo_Operacao) {
    this.DM_Tipo_Operacao = DM_Tipo_Operacao;
  }
  public String getDM_Tipo_Operacao() {
    return DM_Tipo_Operacao;
  }
  
  
  public void setNR_Manifesto_Internacional(long NR_Manifesto_Internacional) {
    this.NR_Manifesto_Internacional = NR_Manifesto_Internacional;
  }
  public long getNR_Manifesto_Internacional() {
    return NR_Manifesto_Internacional;
  }
  public void setDT_Previsao_Chegada(String DT_Previsao_Chegada) {
    this.DT_Previsao_Chegada = DT_Previsao_Chegada;
  }
  public String getDT_Previsao_Chegada() {
    return DT_Previsao_Chegada;
  }
  public void setHR_Previsao_Chegada(String HR_Previsao_Chegada) {
    this.HR_Previsao_Chegada = HR_Previsao_Chegada;
  }
  public String getHR_Previsao_Chegada() {
    return HR_Previsao_Chegada;
  }
  public void setNR_Odometro_Inicial(long NR_Odometro_Inicial) {
    this.NR_Odometro_Inicial = NR_Odometro_Inicial;
  }
  public long getNR_Odometro_Inicial() {
    return NR_Odometro_Inicial;
  }
  public void setNM_Liberacao_Seguradora(String NM_Liberacao_Seguradora) {
    this.NM_Liberacao_Seguradora = NM_Liberacao_Seguradora;
  }
  public String getNM_Liberacao_Seguradora() {
    return NM_Liberacao_Seguradora;
  }
  public void setDT_Troca_Motorista(String DT_Troca_Motorista) {
    this.DT_Troca_Motorista = DT_Troca_Motorista;
  }
  public String getDT_Troca_Motorista() {
    return DT_Troca_Motorista;
  }
  public void setNR_Odometro_Troca_Motorista(long NR_Odometro_Troca_Motorista) {
    this.NR_Odometro_Troca_Motorista = NR_Odometro_Troca_Motorista;
  }
  public long getNR_Odometro_Troca_Motorista() {
    return NR_Odometro_Troca_Motorista;
  }
  public void setOID_Cidade(long OID_Cidade) {
    this.OID_Cidade = OID_Cidade;
  }
  public long getOID_Cidade() {
    return OID_Cidade;
  }
  public void setOID_Veiculo(String OID_Veiculo) {
    this.OID_Veiculo = OID_Veiculo;
  }
  public String getOID_Veiculo() {
    return OID_Veiculo;
  }
  public void setOID_Seguradora(long OID_Seguradora) {
    this.OID_Seguradora = OID_Seguradora;
  }
  public long getOID_Seguradora() {
    return OID_Seguradora;
  }
  public void setOID_Pessoa_Reveza(String OID_Pessoa_Reveza) {
    this.OID_Pessoa_Reveza = OID_Pessoa_Reveza;
  }
  public String getOID_Pessoa_Reveza() {
    return OID_Pessoa_Reveza;
  }
  public void setNR_Placa(String NR_Placa) {
    this.NR_Placa = NR_Placa;
  }
  public String getNR_Placa() {
    return NR_Placa;
  }
  public void setNM_Serie(String NM_Serie) {
    this.NM_Serie = NM_Serie;
  }
  public String getNM_Serie() {
    return NM_Serie;
  }
  public void setNM_Cidade_Destino(String NM_Cidade_Destino) {
    this.NM_Cidade_Destino = NM_Cidade_Destino;
  }
  public String getNM_Cidade_Destino() {
    return NM_Cidade_Destino;
  }
  public void setNM_Cidade_Unidade(String NM_Cidade_Unidade) {
    this.NM_Cidade_Unidade = NM_Cidade_Unidade;
  }
  public String getNM_Cidade_Unidade() {
    return NM_Cidade_Unidade;
  }
  public void setNR_Acerto(long NR_Acerto) {
    this.NR_Acerto = NR_Acerto;
  }
  public long getNR_Acerto() {
    return NR_Acerto;
  }
  public void setOID_Acerto(long OID_Acerto) {
    this.OID_Acerto = OID_Acerto;
  }
  public long getOID_Acerto() {
    return OID_Acerto;
  }
  public void setDM_Lancado_Ordem_Frete(String DM_Lancado_Ordem_Frete) {
    this.DM_Lancado_Ordem_Frete = DM_Lancado_Ordem_Frete;
  }
  public String getDM_Lancado_Ordem_Frete() {
    return DM_Lancado_Ordem_Frete;
  }
  public void setOID_Veiculo_Carreta(String OID_Veiculo_Carreta) {
    this.OID_Veiculo_Carreta = OID_Veiculo_Carreta;
  }
  public String getOID_Veiculo_Carreta() {
    return OID_Veiculo_Carreta;
  }
  public void setCD_Roteiro(String CD_Roteiro) {
    this.CD_Roteiro = CD_Roteiro;
  }
  public String getCD_Roteiro() {
    return CD_Roteiro;
  }
  public void setNM_Roteiro(String NM_Roteiro) {
    this.NM_Roteiro = NM_Roteiro;
  }
  public String getNM_Roteiro() {
    return NM_Roteiro;
  }
  public void setOID_Embarque(long OID_Embarque) {
    this.OID_Embarque = OID_Embarque;
  }
  public long getOID_Embarque() {
    return OID_Embarque;
  }
  public void setNR_Embarque(long NR_Embarque) {
    this.NR_Embarque = NR_Embarque;
  }
  public long getNR_Embarque() {
    return NR_Embarque;
  }
  public void setOID_Cidade_Origem(long OID_Cidade_Origem) {
    this.OID_Cidade_Origem = OID_Cidade_Origem;
  }
  public long getOID_Cidade_Origem() {
    return OID_Cidade_Origem;
  }
  public void setVL_Total_Frete_CTRC(double VL_Total_Frete_CTRC) {
    this.VL_Total_Frete_CTRC = VL_Total_Frete_CTRC;
  }
  public double getVL_Total_Frete_CTRC() {
    return VL_Total_Frete_CTRC;
  }
  public void setNR_Master(String NR_Master) {
    this.NR_Master = NR_Master;
  }
  public String getNR_Master() {
    return NR_Master;
  }
  public void setNR_Total_Peso_CTRC(double NR_Total_Peso_CTRC) {
    this.NR_Total_Peso_CTRC = NR_Total_Peso_CTRC;
  }
  public double getNR_Total_Peso_CTRC() {
    return NR_Total_Peso_CTRC;
  }
  public void setDM_Transito_Aduaneiro(String DM_Transito_Aduaneiro) {
    this.DM_Transito_Aduaneiro = DM_Transito_Aduaneiro;
  }
  public String getDM_Transito_Aduaneiro() {
    return DM_Transito_Aduaneiro;
  }
  public void setOID_Cidade_Alfandega(long OID_Cidade_Alfandega) {
    this.OID_Cidade_Alfandega = OID_Cidade_Alfandega;
  }
  public long getOID_Cidade_Alfandega() {
    return OID_Cidade_Alfandega;
  }
  public void setOID_Cidade_Destino(long OID_Cidade_Destino) {
    this.OID_Cidade_Destino = OID_Cidade_Destino;
  }
  public long getOID_Cidade_Destino() {
    return OID_Cidade_Destino;
  }
  public void setOID_Aduana(long OID_Aduana) {
    this.OID_Aduana = OID_Aduana;
  }
  public long getOID_Aduana() {
    return OID_Aduana;
  }
  public void setNM_Aduana(String NM_Aduana) {
    this.NM_Aduana = NM_Aduana;
  }
  public String getNM_Aduana() {
    return NM_Aduana;
  }
  public void setCD_Aduana(String CD_Aduana) {
    this.CD_Aduana = CD_Aduana;
  }
  public String getCD_Aduana() {
    return CD_Aduana;
  }
  public void setNR_Aduana(String NR_Aduana) {
    this.NR_Aduana = NR_Aduana;
  }
  public String getNR_Aduana() {
    return NR_Aduana;
  }
  public String getNM_Lacre() {
    return NM_Lacre;
  }
  public void setNM_Lacre(String NM_Lacre) {
    this.NM_Lacre = NM_Lacre;
  }
  public String getTX_Observacao1() {
    return TX_Observacao1;
  }
  public String getTX_Observacao2() {
    return TX_Observacao2;
  }
  public String getTX_Observacao3() {
    return TX_Observacao3;
  }
  public String getTX_Rota1() {
    return TX_Rota1;
  }
  public String getTX_Rota2() {
    return TX_Rota2;
  }
  public String getTX_Rota3() {
    return TX_Rota3;
  }
  public String getTX_Rota4() {
    return TX_Rota4; 
  }
  public String getTX_Rota5() {
    return TX_Rota5;
  }
  public void setTX_Observacao1(String TX_Observacao1) {
    this.TX_Observacao1 = TX_Observacao1;
  }
  public void setTX_Observacao2(String TX_Observacao2) {
    this.TX_Observacao2 = TX_Observacao2;
  }
  public void setTX_Observacao3(String TX_Observacao3) {
    this.TX_Observacao3 = TX_Observacao3;
  }
  public void setTX_Rota1(String TX_Rota1) {
    this.TX_Rota1 = TX_Rota1;
  }
  public void setTX_Rota2(String TX_Rota2) {
    this.TX_Rota2 = TX_Rota2;
  }
  public void setTX_Rota3(String TX_Rota3) {
    this.TX_Rota3 = TX_Rota3;
  }
  public void setTX_Rota4(String TX_Rota4) {
    this.TX_Rota4 = TX_Rota4;
  }
  public void setTX_Rota5(String TX_Rota5) {
    this.TX_Rota5 = TX_Rota5;
  }
  public void setCD_Pais(String CD_Pais) {
    this.CD_Pais = CD_Pais;
  }
  public String getCD_Pais() {
    return CD_Pais;
  }
  public void setNR_Permisso(String NR_Permisso) {
    this.NR_Permisso = NR_Permisso;
  }
  public String getNR_Permisso() {
    return NR_Permisso;
  }
  public void setDM_Exportacao_Importacao(String DM_Exportacao_Importacao) {
    this.DM_Exportacao_Importacao = DM_Exportacao_Importacao;
  }
  public String getDM_Exportacao_Importacao() {
    return DM_Exportacao_Importacao;
  }
  public void setOID_Aduana_Destino(long OID_Aduana_Destino) {
    this.OID_Aduana_Destino = OID_Aduana_Destino;
  }
  public long getOID_Aduana_Destino() {
    return OID_Aduana_Destino;
  }
  public void setOID_Pessoa_Permisso(String OID_Pessoa_Permisso) {
    this.OID_Pessoa_Permisso = OID_Pessoa_Permisso;
  }
  public String getOID_Pessoa_Permisso() {
    return OID_Pessoa_Permisso;
  }
  public void setOID_Pessoa_Permisso2(String OID_Pessoa_Permisso2) {
    this.OID_Pessoa_Permisso2 = OID_Pessoa_Permisso2;
  }
  public String getOID_Pessoa_Permisso2() {
    return OID_Pessoa_Permisso2;
  }


	public int getOID_Unidade_Destino() {
	    return OID_Unidade_Destino;
	}
	public void setOID_Unidade_Destino(int unidade_Destino) {
	    OID_Unidade_Destino = unidade_Destino;
	}
	public int getOID_Unidade_Fronteira() {
	    return OID_Unidade_Fronteira;
	}
	public void setOID_Unidade_Fronteira(int unidade_Fronteira) {
	    OID_Unidade_Fronteira = unidade_Fronteira;
	}
	public int getOID_Unidade_Origem() {
	    return OID_Unidade_Origem;
	}
	public void setOID_Unidade_Origem(int unidade_Origem) {
	    OID_Unidade_Origem = unidade_Origem;
	}
	
	
	public String getTX_Rota6() {
	    return TX_Rota6;
	}
	public void setTX_Rota6(String rota6) {
	    TX_Rota6 = rota6;
	}
	public String getTX_Rota7() {
	    return TX_Rota7;
	}
	public void setTX_Rota7(String rota7) {
	    TX_Rota7 = rota7;
	}
	public String getTX_Rota8() {
	    return TX_Rota8;
	}
	public void setTX_Rota8(String rota8) {
	    TX_Rota8 = rota8;
	}
	public String getTX_Rota9() {
	    return TX_Rota9;
	}
	public void setTX_Rota9(String rota9) {
	    TX_Rota9 = rota9;
	}
	public String getNM_Pais() {
	    return NM_Pais;
	}
	public void setNM_Pais(String pais) {
	    NM_Pais = pais;
	}
	public String getNM_Manifesto_Internacional() {
	    return NM_Manifesto_Internacional;
	}
	public void setNM_Manifesto_Internacional(String manifesto_Internacional) {
	    NM_Manifesto_Internacional = manifesto_Internacional;
	}
	public String getDT_Fim() {
	    return DT_Fim;
	}
	public void setDT_Fim(String fim) {
	    DT_Fim = fim;
	}
	public String getNM_Pessoa_Proprietario() {
	    return NM_Pessoa_Proprietario;
	}
	public void setNM_Pessoa_Proprietario(String pessoa_Proprietario) {
	    NM_Pessoa_Proprietario = pessoa_Proprietario;
	}
	public long getOID_Tipo_Ocorrencia() {
	    return OID_Tipo_Ocorrencia;
	}
	public void setOID_Tipo_Ocorrencia(long tipo_Ocorrencia) {
	    OID_Tipo_Ocorrencia = tipo_Ocorrencia;
	}
	public String getDT_Chegada() {
	    return DT_Chegada;
	}
	public void setDT_Chegada(String chegada) {
	    DT_Chegada = chegada;
	}
	public String getHR_Chegada() {
	    return HR_Chegada;
	}
	public void setHR_Chegada(String chegada) {
	    HR_Chegada = chegada;
	}
	public String getDM_Tipo() {
	    return DM_Tipo;
	}
	public void setDM_Tipo(String tipo) {
	    DM_Tipo = tipo;
	}
	public String getNM_Pessoa_Entrega() {
	    return NM_Pessoa_Entrega;
	}
	public void setNM_Pessoa_Entrega(String pessoa_Entrega) {
	    NM_Pessoa_Entrega = pessoa_Entrega;
	}
	public String getDM_Situacao() {
	    return DM_Situacao;
	}
	public void setDM_Situacao(String situacao) {
	    DM_Situacao = situacao;
	}
	public String getDataRel() {
	    return dataRel;
	}
	public void setDataRel(String dataRel) {
	    this.dataRel = dataRel;
	}
	public java.util.Collection getMICDetalhes() {
	    return MICDetalhes;
	}
	public void setMICDetalhes(java.util.Collection detalhes) {
	    MICDetalhes = detalhes;
	}
	public String getSiglaRel() {
	    return siglaRel;
	}
	public void setSiglaRel(String siglaRel) {
	    this.siglaRel = siglaRel;
	}
	public String getDT_Emissao_Final() {
	    return DT_Emissao_Final;
	}
	public void setDT_Emissao_Final(String emissao_Final) {
	    DT_Emissao_Final = emissao_Final;
	}
	public String getDT_Emissao_Inicial() {
	    return DT_Emissao_Inicial;
	}
	public void setDT_Emissao_Inicial(String emissao_Inicial) {
	    DT_Emissao_Inicial = emissao_Inicial;
	}
	public double getNR_Volumes() {
	    return NR_Volumes;
	}
	public void setNR_Volumes(double volumes) {
	    NR_Volumes = volumes;
	}
	public double getVL_Nota_Fiscal() {
	    return VL_Nota_Fiscal;
	}
	public void setVL_Nota_Fiscal(double nota_Fiscal) {
	    VL_Nota_Fiscal = nota_Fiscal;
	}
	public double getVL_Peso() {
	    return VL_Peso;
	}
	public void setVL_Peso(double peso) {
	    VL_Peso = peso;
	}
	public double getVL_Peso_Cubado() {
	    return VL_Peso_Cubado;
	}
	public void setVL_Peso_Cubado(double peso_Cubado) {
	    VL_Peso_Cubado = peso_Cubado;
	}
	public double getVL_Total_Frete() {
	    return VL_Total_Frete;
	}
	public void setVL_Total_Frete(double total_Frete) {
	    VL_Total_Frete = total_Frete;
	}
	public String getNM_Conhecimento() {
	    return NM_Conhecimento;
	}
	public void setNM_Conhecimento(String conhecimento) {
	    NM_Conhecimento = conhecimento;
	}
	public String getNM_Ordem_Frete() {
	    return NM_Ordem_Frete;
	}
	public void setNM_Ordem_Frete(String ordem_Frete) {
	    NM_Ordem_Frete = ordem_Frete;
	}
	public String getNM_Seguradora() {
	    return NM_Seguradora;
	}
	public void setNM_Seguradora(String seguradora) {
	    NM_Seguradora = seguradora;
	}
	public double getVL_Adto() {
	    return VL_Adto;
	}
	public void setVL_Adto(double adto) {
	    VL_Adto = adto;
	}
	public double getVL_INSS() {
	    return VL_INSS;
	}
	public void setVL_INSS(double vl_inss) {
	    VL_INSS = vl_inss;
	}
	public double getVL_IRRF() {
	    return VL_IRRF;
	}
	public void setVL_IRRF(double vl_irrf) {
	    VL_IRRF = vl_irrf;
	}
	public double getVL_Ordem_Frete() {
	    return VL_Ordem_Frete;
	}
	public void setVL_Ordem_Frete(double ordem_Frete) {
	    VL_Ordem_Frete = ordem_Frete;
	}
	public double getVL_Saldo() {
	    return VL_Saldo;
	}
	public void setVL_Saldo(double saldo) {
	    VL_Saldo = saldo;
	}
	public double getVL_Set_Senat() {
	    return VL_Set_Senat;
	}
	public void setVL_Set_Senat(double set_Senat) {
	    VL_Set_Senat = set_Senat;
	}
	public String getNM_Proprietario() {
	    return NM_Proprietario;
	}
	public void setNM_Proprietario(String proprietario) {
	    NM_Proprietario = proprietario;
	}
	public String getNM_Cid_UF_Pais_Proprietario() {
	    return NM_Cid_UF_Pais_Proprietario;
	}
	public void setNM_Cid_UF_Pais_Proprietario(String cid_UF_Pais_Proprietario) {
	    NM_Cid_UF_Pais_Proprietario = cid_UF_Pais_Proprietario;
	}
	public String getNM_End_Proprietario() {
	    return NM_End_Proprietario;
	}
	public void setNM_End_Proprietario(String end_Proprietario) {
	    NM_End_Proprietario = end_Proprietario;
	}
	public String getTX_Observacao10() {
	    return TX_Observacao10;
	}
	public void setTX_Observacao10(String observacao10) {
	    TX_Observacao10 = observacao10;
	}
	public String getTX_Observacao11() {
	    return TX_Observacao11;
	}
	public void setTX_Observacao11(String observacao11) {
	    TX_Observacao11 = observacao11;
	}
	public String getTX_Observacao12() {
	    return TX_Observacao12;
	}
	public void setTX_Observacao12(String observacao12) {
	    TX_Observacao12 = observacao12;
	}
	public String getTX_Observacao13() {
	    return TX_Observacao13;
	}
	public void setTX_Observacao13(String observacao13) {
	    TX_Observacao13 = observacao13;
	}
	public String getTX_Observacao14() {
	    return TX_Observacao14;
	}
	public void setTX_Observacao14(String observacao14) {
	    TX_Observacao14 = observacao14;
	}
	public String getTX_Observacao15() {
	    return TX_Observacao15;
	}
	public void setTX_Observacao15(String observacao15) {
	    TX_Observacao15 = observacao15;
	}
	public String getTX_Observacao16() {
	    return TX_Observacao16;
	}
	public void setTX_Observacao16(String observacao16) {
	    TX_Observacao16 = observacao16;
	}
	public String getTX_Observacao17() {
	    return TX_Observacao17;
	}
	public void setTX_Observacao17(String observacao17) {
	    TX_Observacao17 = observacao17;
	}
	public String getTX_Observacao18() {
	    return TX_Observacao18;
	}
	public void setTX_Observacao18(String observacao18) {
	    TX_Observacao18 = observacao18;
	}
	public String getTX_Observacao4() {
	    return TX_Observacao4;
	}
	public void setTX_Observacao4(String observacao4) {
	    TX_Observacao4 = observacao4;
	}
	public String getTX_Observacao5() {
	    return TX_Observacao5;
	}
	public void setTX_Observacao5(String observacao5) {
	    TX_Observacao5 = observacao5;
	}
	public String getTX_Observacao6() {
	    return TX_Observacao6;
	}
	public void setTX_Observacao6(String observacao6) {
	    TX_Observacao6 = observacao6;
	}
	public String getTX_Observacao7() {
	    return TX_Observacao7;
	}
	public void setTX_Observacao7(String observacao7) {
	    TX_Observacao7 = observacao7;
	}
	public String getTX_Observacao8() {
	    return TX_Observacao8;
	}
	public void setTX_Observacao8(String observacao8) {
	    TX_Observacao8 = observacao8;
	}
	public String getTX_Observacao9() {
	    return TX_Observacao9;
	}
	public void setTX_Observacao9(String observacao9) {
	    TX_Observacao9 = observacao9;
	}
	
	
}
