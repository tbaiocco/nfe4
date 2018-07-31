package com.master.ed;

import java.io.Serializable;

import com.master.root.ClienteBean;

/**
 * @author André Valadas
 * @serial Cheques Clientes
 * @serialData 01/04/2005
 */
public class Cheque_ClienteED extends MasterED implements Serializable {

    public Cheque_ClienteED() {
        super();
    }
    public Cheque_ClienteED(int oid_Cheque_Cliente) {
        this.oid_Cheque_Cliente = oid_Cheque_Cliente;
    }
    public Cheque_ClienteED(boolean banco, boolean corrente, boolean cliente, boolean entrega, boolean nota, boolean motivo_dev) {
        carregaBanco = banco;
        carregaCCorrente = corrente;
        carregaCliente = cliente;
        carregaEntrega = entrega;
        carregaNota = nota;
        carregaMotivoDev = motivo_dev;
    }
    public Cheque_ClienteED(int oid_cheque_cliente, boolean banco, boolean corrente, boolean cliente, boolean entrega, boolean nota, boolean motivo_dev) {
        carregaBanco = banco;
        carregaCCorrente = corrente;
        carregaCliente = cliente;
        carregaEntrega = entrega;
        carregaNota = nota;
        oid_Cheque_Cliente = oid_cheque_cliente;
        carregaMotivoDev = motivo_dev;
    }
    
    public ClienteBean edCliente = new ClienteBean();
    public Nota_Fiscal_EletronicaED edNota = new Nota_Fiscal_EletronicaED();
    public EntregaED edEntrega = new EntregaED();
    public BancoED edBanco = new BancoED();
    public Conta_CorrenteED edConta_Corrente = new Conta_CorrenteED();
    public Motivo_DevolucaoED edMotivo = new Motivo_DevolucaoED();
    
    private boolean carregaCliente = true;
    private boolean carregaNota = true;
    private boolean carregaEntrega = true;
    private boolean carregaBanco = true;
    private boolean carregaCCorrente = true;
    private boolean carregaMotivoDev = true;
    
    private int oid_Cheque_Cliente;
    private String oid_Cliente;
    private String oid_Nota_Fiscal;
    private int oid_Entrega;
    //*** P/ Inclusão apartir de um Lote de Recebimento
    private int oid_Lote_Recebimento;
    
    private int oid_Banco;
    private String NR_Comp;
    private String NR_Agencia;
    private String NR_C1;
    private String NR_Conta;
    private String NR_C2;
    private String NR_Cheque;
    private String NR_C3;
    private String DT_Programado;
    private double VL_Cheque;
    private String DM_Origem;// (Proprio/Terceiro)
    private String DT_Entrada;// (Data Sistema)
    private String NM_Pessoa_Emissor;

    private String oid_Conta_Corrente;
    private String oid_Conta_Corrente_Unidade;
    private String DT_Apresentacao1;
    private String DT_Apresentacao2;
    private String DT_Apresentacao3;
    private String DT_Compensacao;
    private String DM_Situacao;
    private int oid_Motivo_Devolucao;// (11-Falta Fundos/15-Sustado...)
    private String DM_Apresentacao;
    private double VL_Taxa;
    private double VL_Juro_Mora_Dia;
    private double VL_Saldo;
    private String NR_Vale;
    
    private String DT_Entrada_Final;
    private String DT_Programado_Final;
    private String DT_Compensacao_Final;
    private String DT_Apresentacao1_Final;
    private String DT_Apresentacao2_Final;
    private String DT_Apresentacao3_Final;
    private boolean semLote;
    private boolean semEntrega;
    
    public String getDescDM_Origem() {
        if ("P".equals(DM_Origem))
            return "Próprio";
        else if ("T".equals(DM_Origem))
            return "Terceiro";
        else return "Não informado!";
    }
    public String getDescSituacao() {
        if ("F".equals(DM_Situacao))
            return "Compensado";
        else if ("D".equals(DM_Situacao))
            return "Devolvido";
        else if ("C".equals(DM_Situacao))
            return "Cancelado";
        else if ("N".equals(DM_Situacao))
            return "Em espera";
        else return "Não encontrada";
    }
    
    public String getDescApresentacao() {
        if ("S".equals(DM_Apresentacao))
            return "Permitida";
        else if ("N".equals(DM_Apresentacao))
            return "Bloqueada";
        else return "Não encontrada";
    }
    
    public String getDM_Origem() {
        return DM_Origem;
    }
    public String getDT_Apresentacao1() {
        return DT_Apresentacao1;
    }
    public String getDT_Apresentacao2() {
        return DT_Apresentacao2;
    }
    public String getDT_Compensacao() {
        return DT_Compensacao;
    }
    public String getDT_Entrada() {
        return DT_Entrada;
    }
    public String getDT_Programado() {
        return DT_Programado;
    }
    public String getNR_Agencia() {
        return NR_Agencia;
    }
    public String getNR_Cheque() {
        return NR_Cheque;
    }
    public String getNR_Conta() {
        return NR_Conta;
    }
    public String getNR_Vale() {
        return NR_Vale;
    }
    public int getOid_Banco() {
        return oid_Banco;
    }
    public int getOid_Cheque_Cliente() {
        return oid_Cheque_Cliente;
  }
    public String getOid_Cliente() {
        return oid_Cliente;
    }
    public String getOid_Conta_Corrente() {
        return oid_Conta_Corrente;
    }
    public int getOid_Entrega() {
        return oid_Entrega;
    }
    public String getOid_Nota_Fiscal() {
        return oid_Nota_Fiscal;
    }
    public double getVL_Cheque() {
        return VL_Cheque;
    }
    public double getVL_Juro_Mora_Dia() {
        return VL_Juro_Mora_Dia;
    }
    public double getVL_Saldo() {
        return VL_Saldo;
    }
    public double getVL_Taxa() {
        return VL_Taxa;
    }
    public void setDM_Origem(String origem) {
        DM_Origem = origem;
    }
    public void setDT_Apresentacao1(String apresentacao1) {
        DT_Apresentacao1 = apresentacao1;
    }
    public void setDT_Apresentacao2(String apresentacao2) {
        DT_Apresentacao2 = apresentacao2;
    }
    public void setDT_Compensacao(String compensacao) {
        DT_Compensacao = compensacao;
    }
    public void setDT_Entrada(String entrada) {
        DT_Entrada = entrada;
    }
    public void setDT_Programado(String programado) {
        DT_Programado = programado;
    }
    public void setNR_Agencia(String agencia) {
        NR_Agencia = agencia;
    }
    public void setNR_Cheque(String cheque) {
        NR_Cheque = cheque;
    }
    public void setNR_Conta(String conta) {
        NR_Conta = conta;
    }
    public void setNR_Vale(String vale) {
        NR_Vale = vale;
    }
    public void setOid_Banco(int oid_Banco) {
        this.oid_Banco = oid_Banco;
    }
    public void setOid_Cheque_Cliente(int oid_Cheque_Cliente) {
        this.oid_Cheque_Cliente = oid_Cheque_Cliente;
  }
    public void setOid_Cliente(String oid_Cliente) {
        this.oid_Cliente = oid_Cliente;
    }
    public void setOid_Conta_Corrente(String oid_Conta_Corrente) {
        this.oid_Conta_Corrente = oid_Conta_Corrente;
    }
    public void setOid_Entrega(int oid_Entrega) {
        this.oid_Entrega = oid_Entrega;
    }
    public void setOid_Nota_Fiscal(String oid_Nota_Fiscal) {
        this.oid_Nota_Fiscal = oid_Nota_Fiscal;
    }
    public void setVL_Cheque(double cheque) {
        VL_Cheque = cheque;
    }
    public void setVL_Juro_Mora_Dia(double juro_Mora_Dia) {
        VL_Juro_Mora_Dia = juro_Mora_Dia;
    }
    public void setVL_Saldo(double saldo) {
        VL_Saldo = saldo;
    }
    public void setVL_Taxa(double taxa) {
        VL_Taxa = taxa;
    }
    public String getNM_Pessoa_Emissor() {
        return NM_Pessoa_Emissor;
    }
    public void setNM_Pessoa_Emissor(String NM_Pessoa_Emissor) {
        this.NM_Pessoa_Emissor = NM_Pessoa_Emissor;
    }
    public String getDT_Compensacao_Final() {
        return DT_Compensacao_Final;
    }
    public void setDT_Compensacao_Final(String compensacao_Final) {
        DT_Compensacao_Final = compensacao_Final;
    }
    public String getDT_Entrada_Final() {
        return DT_Entrada_Final;
    }
    public void setDT_Entrada_Final(String entrada_Final) {
        DT_Entrada_Final = entrada_Final;
    }
    public String getDT_Programado_Final() {
        return DT_Programado_Final;
    }
    public void setDT_Programado_Final(String programado_Final) {
        DT_Programado_Final = programado_Final;
    }
    public int getOid_Lote_Recebimento() {
        return oid_Lote_Recebimento;
    }
    public void setOid_Lote_Recebimento(int oid_Lote_Recebimento) {
        this.oid_Lote_Recebimento = oid_Lote_Recebimento;
    }
    public boolean isSemLote() {
        return semLote;
    }
    public void setSemLote(boolean semLote) {
        this.semLote = semLote;
    }
    public boolean isSemEntrega() {
        return semEntrega;
    }
    public void setSemEntrega(boolean semEntrega) {
        this.semEntrega = semEntrega;
    }
    public boolean isCarregaBanco() {
        return carregaBanco;
    }
    public void setCarregaBanco(boolean carregaBanco) {
        this.carregaBanco = carregaBanco;
    }
    public boolean isCarregaCCorrente() {
        return carregaCCorrente;
    }
    public void setCarregaCCorrente(boolean carregaCCorrente) {
        this.carregaCCorrente = carregaCCorrente;
    }
    public boolean isCarregaCliente() {
        return carregaCliente;
    }
    public void setCarregaCliente(boolean carregaCliente) {
        this.carregaCliente = carregaCliente;
    }
    public boolean isCarregaEntrega() {
        return carregaEntrega;
    }
    public void setCarregaEntrega(boolean carregaEntrega) {
        this.carregaEntrega = carregaEntrega;
    }
    public boolean isCarregaNota() {
        return carregaNota;
    }
    public void setCarregaNota(boolean carregaNota) {
        this.carregaNota = carregaNota;
    }
    public String getDT_Apresentacao1_Final() {
        return DT_Apresentacao1_Final;
    }
    public void setDT_Apresentacao1_Final(String apresentacao1_Final) {
        DT_Apresentacao1_Final = apresentacao1_Final;
    }
    public String getDT_Apresentacao2_Final() {
        return DT_Apresentacao2_Final;
    }
    public void setDT_Apresentacao2_Final(String apresentacao2_Final) {
        DT_Apresentacao2_Final = apresentacao2_Final;
    }
    public String getDT_Apresentacao3() {
        return DT_Apresentacao3;
    }
    public void setDT_Apresentacao3(String apresentacao3) {
        DT_Apresentacao3 = apresentacao3;
    }
    public String getDT_Apresentacao3_Final() {
        return DT_Apresentacao3_Final;
    }
    public void setDT_Apresentacao3_Final(String apresentacao3_Final) {
        DT_Apresentacao3_Final = apresentacao3_Final;
    }
    public String getDM_Apresentacao() {
        return DM_Apresentacao;
    }
    public void setDM_Apresentacao(String apresentacao) {
        DM_Apresentacao = apresentacao;
    }
    public String getDM_Situacao() {
        return DM_Situacao;
    }
    public void setDM_Situacao(String situacao) {
        DM_Situacao = situacao;
    }
    public int getOid_Motivo_Devolucao() {
        return oid_Motivo_Devolucao;
    }
    public void setOid_Motivo_Devolucao(int oid_Motivo_Devolucao) {
        this.oid_Motivo_Devolucao = oid_Motivo_Devolucao;
    }
    public boolean isCarregaMotivoDev() {
        return carregaMotivoDev;
    }
    public void setCarregaMotivoDev(boolean carregaMotivoDev) {
        this.carregaMotivoDev = carregaMotivoDev;
    }
    public String getOid_Conta_Corrente_Unidade() {
        return oid_Conta_Corrente_Unidade;
    }
    public void setOid_Conta_Corrente_Unidade(String oid_Conta_Corrente_Unidade) {
        this.oid_Conta_Corrente_Unidade = oid_Conta_Corrente_Unidade;
    }
    public String getNR_C1() {
        return NR_C1;
    }
    public void setNR_C1(String nr_c1) {
        NR_C1 = nr_c1;
    }
    public String getNR_C2() {
        return NR_C2;
    }
    public void setNR_C2(String nr_c2) {
        NR_C2 = nr_c2;
    }
    public String getNR_C3() {
        return NR_C3;
    }
    public void setNR_C3(String nr_c3) {
        NR_C3 = nr_c3;
    }
    public String getNR_Comp() {
        return NR_Comp;
    }
    public void setNR_Comp(String comp) {
        NR_Comp = comp;
    }
}