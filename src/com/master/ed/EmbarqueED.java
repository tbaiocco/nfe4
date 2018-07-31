package com.master.ed;

import java.util.Calendar;

import com.master.util.Data;

public class EmbarqueED extends MasterED implements Comparable {
  
   public int compareTo(Object object) {

       Calendar data1 = Calendar.getInstance();
       Calendar data2 = Calendar.getInstance();

       String d1 = "";
       String d2 = "";

       try {
           EmbarqueED embarqueED = (EmbarqueED)object;

           //data corrente
           d1 = this.getDT_Chegada();
           if (d1 == null || d1.equals("")) {
               d1 = Data.getDataDMY();
           }
           //// System.out.println("d1 = "+d1);
           data1.set(Calendar.DATE,Integer.parseInt(d1.substring(0,2)));
           data1.set(Calendar.MONTH,Integer.parseInt(d1.substring(3,5)));
           data1.set(Calendar.YEAR,Integer.parseInt(d1.substring(6,10)));

           //data passada por parametro
           d2 = embarqueED.getDT_Chegada();
           if (d2 == null || d2.equals("")) {
               d2 = Data.getDataDMY();
           }
           //// System.out.println("d2 = "+d2);
           data2.set(Calendar.DATE,Integer.parseInt(d2.substring(0,2)));
           data2.set(Calendar.MONTH,Integer.parseInt(d2.substring(3,5)));
           data2.set(Calendar.YEAR,Integer.parseInt(d2.substring(6,10)));

           //-1 se a data2 for menor, 1 se a data2 for maior, 0 se for igual
           long diferenca = Data.diferencaDatasDias(data1,data2);
           //// System.out.println("diferenca = "+diferenca);
           if (diferenca > 0) {
               return 1;
           }
           else if (diferenca < 0) {
               return -1;
           }
           else {
               return 0;
           }
       }
       catch (Exception e) {
           e.printStackTrace();
           return 0;
       }
   }


  private String DT_Emissao;
  private String DT_Emissao_Final;
  private long OID_Cidade_Destino;
  private String CD_Unidade;
  private String DM_Expresso;
  private long NR_Embarque;
  private String DT_Previsao_Chegada;
  private String DT_Previsao_Chegada_Final;
  private String HR_Previsao_Chegada;
  private String DT_Nova_Previsao_Chegada;
  private String HR_Nova_Previsao_Chegada;
  private String DT_Entrega;
  private String DT_Entrega_Final;
  private String HR_Entrega;
  private long OID_Cidade_Origem;
  private String NM_Motorista;
  private String NR_Placa;
  private String NM_Serie;
  private String NM_Cidade_Destino;
  private String NM_Cidade_Origem;
  private String NM_Cidade_Apoio;
  private String DT_Chegada;
  private String HR_Chegada;
  private String DM_Situacao;
  private String NR_Celular;
  private long OID_Embarque;
  private String OID_Nota_Fiscal;
  private String NR_Nota_Fiscal;
  private String DT_Saida;
  private String HR_Saida;
  private String DT_Saida_Anterior;
  private String DT_Chegada_Anterior;
  private String DT_Entrega_Anterior;
  private String DM_Nota_Lancada;
  private String NM_Ocorrencia;
  private String DT_Ocorrencia_Embarque;
  private String HR_Ocorrencia_Embarque;
  private String DT_Previsao_Chegada_Anterior;
  private String OID_Veiculo;
  private String OID_Veiculo_Carreta;
  private String NR_Placa_Carreta;
  private String TX_Descricao;
  private String DM_Carga_Veiculo;
  private long OID_Cidade_Apoio;
  private long OID_Tipo_Veiculo;
  private long OID_Tipo_Veiculo_Carreta;
  private long OID_Rota;
  private String DM_Tipo_Frota;
  private String NM_Tipo_Veiculo;
  private String CD_Roteiro;
  private String NR_Pedido;
  private String NM_Roteiro;
  private String OID_Pessoa_Senha;

  private String DM_Procedencia;
  private String DT_Ultimo_Contato;
  private String HR_Ultimo_Contato;
  private String DT_Proximo_Contato;
  private String HR_Proximo_Contato;
  private String TX_Ultima_Observacao;

  private String NR_Frota;

  private long NR_Odometro_Inicial;
  private long OID_Estado_Destino;


  private String CD_Estado_Origem;
  private String CD_Estado_Destino;
  private double VL_Total_Embarque;
  private double NR_Peso;
  private String NR_Liberacao;
  private String NM_Proprietario;
  private String NR_CNPJ_CPF;
  private String TX_Detalhes;
  private String TX_Detalhes1;
  private String NR_Conhecimento;
  private String dm_tipoC;
  private String dm_tipoF;
  private String dm_tipoA;
  private java.util.Collection Trechos;
  private java.util.Collection Apoios;
  private java.util.Collection EmbarqueDetalhes;
  private String NM_Embarcador;
  private String OID_Manifesto;
  
  private double VL_Rota;
  private double VL_Adto_Real;
  private double VL_Adto_Peso;
  private double VL_Adto_Dolar;
  
  private String NM_Cidade_Cruze;

//fatura
  private String NR_Fatura;
  private long OID_Pais;

  private String NR_MIC;
  private String NR_Manifesto;
  
  private String NR_CRT;
  
  private String OID_Pessoa;
  private String OID_Pessoa_Destinatario;
  private String DM_Tipo_Embarque;

  private String NR_Chassi;
  private String NR_Carro;
  
  private String REM_DEST;
  private String NM_Remetente;
  private String NM_Destinatario;
  
  private int NR_Dias;
  private String DT_Coleta;
  
  private String dataRel;
  private String siglaRel;

  public void setOID_Embarque(long OID_Embarque) {
    this.OID_Embarque = OID_Embarque;
  }
  public long getOID_Embarque() {
    return OID_Embarque;
  }
  public void setOID_Cidade_Destino(long OID_Cidade_Destino) {
    this.OID_Cidade_Destino = OID_Cidade_Destino;
  }
  public long getOID_Cidade_Destino() {
    return OID_Cidade_Destino;
  }
  public void setDT_Emissao(String DT_Emissao) {
    this.DT_Emissao = DT_Emissao;
  }
  public String getDT_Emissao() {
    return DT_Emissao;
  }

  public void setCD_Unidade(String CD_Unidade) {
    this.CD_Unidade = CD_Unidade;
  }
  public String getCD_Unidade() {
    return CD_Unidade;
  }
  public void setDM_Expresso(String DM_Expresso) {
    this.DM_Expresso = DM_Expresso;
  }
  public String getDM_Expresso() {
    return DM_Expresso;
  }
  public void setNR_Embarque(long NR_Embarque) {
    this.NR_Embarque = NR_Embarque;
  }
  public long getNR_Embarque() {
    return NR_Embarque;
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
  public void setNM_Motorista(String NM_Motorista) {
    this.NM_Motorista = NM_Motorista;
  }
  public String getNM_Motorista() {
    return NM_Motorista;
  }
  public void setOID_Cidade_Origem(long OID_Cidade_Origem) {
    this.OID_Cidade_Origem = OID_Cidade_Origem;
  }
  public long getOID_Cidade_Origem() {
    return OID_Cidade_Origem;
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
  public void setNM_Cidade_Origem(String NM_Cidade_Origem) {
    this.NM_Cidade_Origem = NM_Cidade_Origem;
  }
  public String getNM_Cidade_Origem() {
    return NM_Cidade_Origem;
  }
  public void setDT_Chegada(String DT_Chegada) {
    this.DT_Chegada = DT_Chegada;
  }
  public String getDT_Chegada() {
    return DT_Chegada;
  }
  public void setHR_Chegada(String HR_Chegada) {
    this.HR_Chegada = HR_Chegada;
  }
  public String getHR_Chegada() {
    return HR_Chegada;
  }
  public void setDM_Situacao(String DM_Situacao) {
    this.DM_Situacao = DM_Situacao;
  }
  public String getDM_Situacao() {
    return DM_Situacao;
  }
  public void setNR_Celular(String NR_Celular) {
    this.NR_Celular = NR_Celular;
  }
  public String getNR_Celular() {
    return NR_Celular;
  }
  public void setOID_Nota_Fiscal(String OID_Nota_Fiscal) {
    this.OID_Nota_Fiscal = OID_Nota_Fiscal;
  }
  public String getOID_Nota_Fiscal() {
    return OID_Nota_Fiscal;
  }
  public void setNR_Nota_Fiscal(String NR_Nota_Fiscal) {
    this.NR_Nota_Fiscal = NR_Nota_Fiscal;
  }
  public String getNR_Nota_Fiscal() {
    return NR_Nota_Fiscal;
  }

  public void setDT_Entrega(String DT_Entrega) {
    this.DT_Entrega = DT_Entrega;
  }
  public String getDT_Entrega() {
    return DT_Entrega;
  }

  public void setDT_Nova_Previsao_Chegada(String DT_Nova_Previsao_Chegada) {
    this.DT_Nova_Previsao_Chegada = DT_Nova_Previsao_Chegada;
  }
  public String getDT_Nova_Previsao_Chegada() {
    return DT_Nova_Previsao_Chegada;
  }


  public void setHR_Entrega(String HR_Entrega) {
    this.HR_Entrega = HR_Entrega;
  }
  public String getHR_Entrega() {
    return HR_Entrega;
  }


  public void setHR_Nova_Previsao_Chegada(String HR_Nova_Previsao_Chegada) {
    this.HR_Nova_Previsao_Chegada = HR_Nova_Previsao_Chegada;
  }
  public String getHR_Nova_Previsao_Chegada() {
    return HR_Nova_Previsao_Chegada;
  }
  public void setDT_Saida(String DT_Saida) {
    this.DT_Saida = DT_Saida;
  }
  public String getDT_Saida() {
    return DT_Saida;
  }
  public void setHR_Saida(String HR_Saida) {
    this.HR_Saida = HR_Saida;
  }
  public String getHR_Saida() {
    return HR_Saida;
  }
  public void setDT_Saida_Anterior(String DT_Saida_Anterior) {
    this.DT_Saida_Anterior = DT_Saida_Anterior;
  }
  public String getDT_Saida_Anterior() {
    return DT_Saida_Anterior;
  }
  public void setDT_Chegada_Anterior(String DT_Chegada_Anterior) {
    this.DT_Chegada_Anterior = DT_Chegada_Anterior;
  }
  public String getDT_Chegada_Anterior() {
    return DT_Chegada_Anterior;
  }
  public void setDT_Entrega_Anterior(String DT_Entrega_Anterior) {
    this.DT_Entrega_Anterior = DT_Entrega_Anterior;
  }
  public String getDT_Entrega_Anterior() {
    return DT_Entrega_Anterior;
  }
  public void setDM_Nota_Lancada(String DM_Nota_Lancada) {
    this.DM_Nota_Lancada = DM_Nota_Lancada;
  }
  public String getDM_Nota_Lancada() {
    return DM_Nota_Lancada;
  }
  public void setNM_Ocorrencia(String NM_Ocorrencia) {
    this.NM_Ocorrencia = NM_Ocorrencia;
  }
  public String getNM_Ocorrencia() {
    return NM_Ocorrencia;
  }
  public void setDT_Ocorrencia_Embarque(String DT_Ocorrencia_Embarque) {
    this.DT_Ocorrencia_Embarque = DT_Ocorrencia_Embarque;
  }
  public String getDT_Ocorrencia_Embarque() {
    return DT_Ocorrencia_Embarque;
  }
  public void setHR_Ocorrencia_Embarque(String HR_Ocorrencia_Embarque) {
    this.HR_Ocorrencia_Embarque = HR_Ocorrencia_Embarque;
  }
  public String getHR_Ocorrencia_Embarque() {
    return HR_Ocorrencia_Embarque;
  }
  public void setDT_Previsao_Chegada_Anterior(String DT_Previsao_Chegada_Anterior) {
    this.DT_Previsao_Chegada_Anterior = DT_Previsao_Chegada_Anterior;
  }
  public String getDT_Previsao_Chegada_Anterior() {
    return DT_Previsao_Chegada_Anterior;
  }
  public void setOID_Veiculo(String OID_Veiculo) {
    this.OID_Veiculo = OID_Veiculo;
  }
  public String getOID_Veiculo() {
    return OID_Veiculo;
  }
  public void setOID_Veiculo_Carreta(String OID_Veiculo_Carreta) {
    this.OID_Veiculo_Carreta = OID_Veiculo_Carreta;
  }
  public String getOID_Veiculo_Carreta() {
    return OID_Veiculo_Carreta;
  }
  public void setNR_Placa_Carreta(String NR_Placa_Carreta) {
    this.NR_Placa_Carreta = NR_Placa_Carreta;
  }
  public String getNR_Placa_Carreta() {
    return NR_Placa_Carreta;
  }
  public void setTX_Descricao(String TX_Descricao) {
    this.TX_Descricao = TX_Descricao;
  }
  public String getTX_Descricao() {
    return TX_Descricao;
  }
  public void setDM_Carga_Veiculo(String DM_Carga_Veiculo) {
    this.DM_Carga_Veiculo = DM_Carga_Veiculo;
  }
  public String getDM_Carga_Veiculo() {
    return DM_Carga_Veiculo;
  }
  public void setOID_Cidade_Apoio(long OID_Cidade_Apoio) {
    this.OID_Cidade_Apoio = OID_Cidade_Apoio;
  }
  public long getOID_Cidade_Apoio() {
    return OID_Cidade_Apoio;
  }
  public void setOID_Tipo_Veiculo(long OID_Tipo_Veiculo) {
    this.OID_Tipo_Veiculo = OID_Tipo_Veiculo;
  }
  public long getOID_Tipo_Veiculo() {
    return OID_Tipo_Veiculo;
  }
  public void setOID_Rota(long OID_Rota) {
    this.OID_Rota = OID_Rota;
  }
  public long getOID_Rota() {
    return OID_Rota;
  }
  public void setDM_Tipo_Frota(String DM_Tipo_Frota) {
    this.DM_Tipo_Frota = DM_Tipo_Frota;
  }
  public String getDM_Tipo_Frota() {
    return DM_Tipo_Frota;
  }
  public void setNM_Tipo_Veiculo(String NM_Tipo_Veiculo) {
    this.NM_Tipo_Veiculo = NM_Tipo_Veiculo;
  }
  public String getNM_Tipo_Veiculo() {
    return NM_Tipo_Veiculo;
  }
  public void setCD_Roteiro(String CD_Roteiro) {
    this.CD_Roteiro = CD_Roteiro;
  }
  public String getCD_Roteiro() {
    return CD_Roteiro;
  }
  public long getOID_Tipo_Veiculo_Carreta() {
    return OID_Tipo_Veiculo_Carreta;
  }
  public void setOID_Tipo_Veiculo_Carreta(long OID_Tipo_Veiculo_Carreta) {
    this.OID_Tipo_Veiculo_Carreta = OID_Tipo_Veiculo_Carreta;
  }
  public void setNR_Pedido(String NR_Pedido) {
    this.NR_Pedido = NR_Pedido;
  }
  public String getNR_Pedido() {
    return NR_Pedido;
  }
  public String getDT_Previsao_Chegada_Final() {
    return DT_Previsao_Chegada_Final;
  }
  public void setDT_Previsao_Chegada_Final(String DT_Previsao_Chegada_Final) {
    this.DT_Previsao_Chegada_Final = DT_Previsao_Chegada_Final;
  }
  public String getDT_Emissao_Final() {
    return DT_Emissao_Final;
  }
  public void setDT_Emissao_Final(String DT_Emissao_Final) {
    this.DT_Emissao_Final = DT_Emissao_Final;
  }
  public String getDT_Entrega_Final() {
    return DT_Entrega_Final;
  }
  public void setDT_Entrega_Final(String DT_Entrega_Final) {
    this.DT_Entrega_Final = DT_Entrega_Final;
  }
  public void setNM_Roteiro(String NM_Roteiro) {
    this.NM_Roteiro = NM_Roteiro;
  }
  public String getNM_Roteiro() {
    return NM_Roteiro;
  }
  public void setOID_Pessoa_Senha(String OID_Pessoa_Senha) {
    this.OID_Pessoa_Senha = OID_Pessoa_Senha;
  }
  public String getOID_Pessoa_Senha() {
    return OID_Pessoa_Senha;
  }



  public long getNR_Odometro_Inicial() {
    return NR_Odometro_Inicial;
  }
  public void setNR_Odometro_Inicial(long NR_Odometro_Inicial) {
    this.NR_Odometro_Inicial = NR_Odometro_Inicial;
  }

  public long getOID_Estado_Destino() {
    return OID_Estado_Destino;
  }
  public void setOID_Estado_Destino(long OID_Estado_Destino) {
    this.OID_Estado_Destino = OID_Estado_Destino;
  }

  public String getNR_Fatura() {
    return NR_Fatura;
  }
  public void setNR_Fatura(String NR_Fatura) {
    this.NR_Fatura = NR_Fatura;
  }
  public String getDM_Procedencia() {
    return DM_Procedencia;
  }
  public void setDM_Procedencia(String DM_Procedencia) {
    this.DM_Procedencia = DM_Procedencia;
  }
  public String getDT_Proximo_Contato() {
    return DT_Proximo_Contato;
  }
  public void setDT_Proximo_Contato(String DT_Proximo_Contato) {
    this.DT_Proximo_Contato = DT_Proximo_Contato;
  }
  public String getDT_Ultimo_Contato() {
    return DT_Ultimo_Contato;
  }
  public void setDT_Ultimo_Contato(String DT_Ultimo_Contato) {
    this.DT_Ultimo_Contato = DT_Ultimo_Contato;
  }
  public String getHR_Proximo_Contato() {
    return HR_Proximo_Contato;
  }
  public void setHR_Proximo_Contato(String HR_Proximo_Contato) {
    this.HR_Proximo_Contato = HR_Proximo_Contato;
  }
  public String getHR_Ultimo_Contato() {
    return HR_Ultimo_Contato;
  }
  public void setHR_Ultimo_Contato(String HR_Ultimo_Contato) {
    this.HR_Ultimo_Contato = HR_Ultimo_Contato;
  }
  public String getTX_Ultima_Observacao() {
    return TX_Ultima_Observacao;
  }
  public void setTX_Ultima_Observacao(String TX_Ultima_Observacao) {
    this.TX_Ultima_Observacao = TX_Ultima_Observacao;
  }
  public String getNR_Frota() {
    return NR_Frota;
  }
  public void setNR_Frota(String NR_Frota) {
    this.NR_Frota = NR_Frota;
  }
  public String getNM_Cidade_Apoio() {
    return NM_Cidade_Apoio;
  }
  public void setNM_Cidade_Apoio(String NM_Cidade_Apoio) {
    this.NM_Cidade_Apoio = NM_Cidade_Apoio;
  }

	public String getCD_Estado_Destino() {
		return CD_Estado_Destino;
	}
	public void setCD_Estado_Destino(String estado_Destino) {
		CD_Estado_Destino = estado_Destino;
	}
	public String getCD_Estado_Origem() {
		return CD_Estado_Origem;
	}
	public void setCD_Estado_Origem(String estado_Origem) {
		CD_Estado_Origem = estado_Origem;
	}
	public String getNM_Proprietario() {
		return NM_Proprietario;
	}
	public void setNM_Proprietario(String proprietario) {
		NM_Proprietario = proprietario;
	}
	public String getNR_CNPJ_CPF() {
		return NR_CNPJ_CPF;
	}
	public void setNR_CNPJ_CPF(String nr_cnpj_cpf) {
		NR_CNPJ_CPF = nr_cnpj_cpf;
	}
	public String getNR_Liberacao() {
		return NR_Liberacao;
	}
	public void setNR_Liberacao(String liberacao) {
		NR_Liberacao = liberacao;
	}
	public double getNR_Peso() {
		return NR_Peso;
	}
	public void setNR_Peso(double peso) {
		NR_Peso = peso;
	}
	public String getTX_Detalhes() {
		return TX_Detalhes;
	}
	public void setTX_Detalhes(String detalhes) {
		TX_Detalhes = detalhes;
	}
	public double getVL_Total_Embarque() {
		return VL_Total_Embarque;
	}
	public void setVL_Total_Embarque(double total_Embarque) {
		VL_Total_Embarque = total_Embarque;
	}
    public String getNR_Conhecimento() {
	    return NR_Conhecimento;
    }
    public void setNR_Conhecimento(String conhecimento) {
    	NR_Conhecimento = conhecimento;
    }
	public String getDm_tipoA() {
		return dm_tipoA;
	}

	public void setDm_tipoA(String dm_tipoA) {
		this.dm_tipoA = dm_tipoA;
	}

	public String getDm_tipoC() {
		return dm_tipoC;
	}

	public void setDm_tipoC(String dm_tipoC) {
		this.dm_tipoC = dm_tipoC;
	}

	public String getDm_tipoF() {
		return dm_tipoF;
	}

	public void setDm_tipoF(String dm_tipoF) {
		this.dm_tipoF = dm_tipoF;
	}

	public java.util.Collection getTrechos() {
		return Trechos;
	}

	public void setTrechos(java.util.Collection trechos) {
		Trechos = trechos;
	}

	public java.util.Collection getApoios() {
		return Apoios;
	}

	public void setApoios(java.util.Collection apoios) {
		Apoios = apoios;
	}

	public String getNM_Embarcador() {
		return NM_Embarcador;
	}

	public void setNM_Embarcador(String embarcador) {
		NM_Embarcador = embarcador;
	}


    public void setOID_Pais(long OID_Pais) {
	  this.OID_Pais = OID_Pais;
	}
	public long getOID_Pais() {
	  return OID_Pais;
	}


	public String getTX_Detalhes1() {
		return TX_Detalhes1;
	}
	public void setTX_Detalhes1(String detalhes1) {
		TX_Detalhes1 = detalhes1;
	}

	public String getOID_Manifesto() {
		return OID_Manifesto;
	}
	public void setOID_Manifesto(String OID_Manifesto) {
		this.OID_Manifesto = OID_Manifesto;
	}


	public String getNR_Manifesto() {
	    return NR_Manifesto;
	}
	public void setNR_Manifesto(String manifesto) {
	    NR_Manifesto = manifesto;
	}
	public String getNR_MIC() {
	    return NR_MIC;
	}
	public void setNR_MIC(String nr_mic) {
	    NR_MIC = nr_mic;
	}
	public String getNR_Carro() {
	    return NR_Carro;
	}
	public void setNR_Carro(String carro) {
	    NR_Carro = carro;
	}
	public String getNR_Chassi() {
	    return NR_Chassi;
	}
	public void setNR_Chassi(String chassi) {
	    NR_Chassi = chassi;
	}
	public String getNR_CRT() {
	    return NR_CRT;
	}
	public void setNR_CRT(String nr_crt) {
	    NR_CRT = nr_crt;
	}
	public String getOID_Pessoa() {
	    return OID_Pessoa;
	}
	public void setOID_Pessoa(String pessoa) {
	    OID_Pessoa = pessoa;
	}
	public String getOID_Pessoa_Destinatario() {
	    return OID_Pessoa_Destinatario;
	}
	public void setOID_Pessoa_Destinatario(String pessoa_Destinatario) {
	    OID_Pessoa_Destinatario = pessoa_Destinatario;
	}
	public String getDM_Tipo_Embarque() {
	    return DM_Tipo_Embarque;
	}
	public void setDM_Tipo_Embarque(String tipo_Embarque) {
	    DM_Tipo_Embarque = tipo_Embarque;
	}
	public String getNM_Destinatario() {
	    return NM_Destinatario;
	}
	public void setNM_Destinatario(String destinatario) {
	    NM_Destinatario = destinatario;
	}
	public String getNM_Remetente() {
	    return NM_Remetente;
	}
	public void setNM_Remetente(String remetente) {
	    NM_Remetente = remetente;
	}
	public String getREM_DEST() {
	    return REM_DEST;
	}
	public void setREM_DEST(String rem_dest) {
	    REM_DEST = rem_dest;
	}
	public String getDataRel() {
	    return dataRel;
	}
	public void setDataRel(String dataRel) {
	    this.dataRel = dataRel;
	}
	public java.util.Collection getEmbarqueDetalhes() {
	    return EmbarqueDetalhes;
	}
	public void setEmbarqueDetalhes(java.util.Collection embarqueDetalhes) {
	    EmbarqueDetalhes = embarqueDetalhes;
	}
	public String getSiglaRel() {
	    return siglaRel;
	}
	public void setSiglaRel(String siglaRel) {
	    this.siglaRel = siglaRel;
	}
	public int getNR_Dias() {
	    return NR_Dias;
	}
	public void setNR_Dias(int dias) {
	    NR_Dias = dias;
	}
	public String getDT_Coleta() {
	    return DT_Coleta;
	}
	public void setDT_Coleta(String coleta) {
	    DT_Coleta = coleta;
	}

	public double getVL_Adto_Dolar() {
	    return VL_Adto_Dolar;
	}
	public void setVL_Adto_Dolar(double adto_Dolar) {
	    VL_Adto_Dolar = adto_Dolar;
	}
	public double getVL_Adto_Peso() {
	    return VL_Adto_Peso;
	}
	public void setVL_Adto_Peso(double adto_Peso) {
	    VL_Adto_Peso = adto_Peso;
	}
	public double getVL_Adto_Real() {
	    return VL_Adto_Real;
	}
	public void setVL_Adto_Real(double adto_Real) {
	    VL_Adto_Real = adto_Real;
	}
	public double getVL_Rota() {
	    return VL_Rota;
	}
	public void setVL_Rota(double rota) {
	    VL_Rota = rota;
	}
	public String getNM_Cidade_Cruze() {
	    return NM_Cidade_Cruze;
	}
	public void setNM_Cidade_Cruze(String cidade_Cruze) {
	    NM_Cidade_Cruze = cidade_Cruze;
	}
}
