package com.master.ed;

public class Conhecimento_InternacionalED extends MasterED{
 
    private String OID_Conhecimento;
    private long NR_Conhecimento;
    private long NR_Conhecimento_Final;
    private String NM_Conhecimento;
    private String NM_Serie;
    private String DM_Responsavel_Cobranca;
    private String DM_Tipo_Pagamento;
    private String DM_Situacao;
    private String TX_Observacao1;
    private String DM_Isento_Seguro;
    private String DT_Emissao;
    private String DT_Emissao_Inicial;
    private String DT_Emissao_Final;
    private String DT_Fim;
    private String OID_Pessoa;
    private String OID_Pessoa_Destinatario;
    private String OID_Pessoa_Consignatario;
    private String OID_Pessoa_Notificar;
    private long OID_Modal;
    private int OID_Unidade_Origem;
    private String CD_Unidade_Origem;
    private String NM_Fantasia_Origem;
    private int OID_Unidade_Destino;
    private String CD_Unidade_Destino;
    private String NM_Fantasia_Destino;
    private int OID_Unidade_Fronteira;
    private String CD_Unidade_Fronteira;
    private String NM_Fantasia_Fronteira;
    private long OID_Cidade;
    private long OID_Cidade_Destino;
    private String OID_Pessoa_Pagador;
    private long OID_Produto;
    private long OID_Coleta;
    private long NR_Coleta;
    
    private String NM_Pessoa_Remetente;
    private String NM_Razao_Social_Remetente;
    private String NM_Remetente;
    private String NM_Endereco_Remetente;
    private String NM_Pais_Remetente;
    private String NR_CNPJ_Remetente_Complementar;
    
    private String NM_Pessoa_Destinatario;
    private String NR_CPF_CNPJ_Remetente;
    private String NR_CNPJ_Destinatario_Complementar;
    private String NM_Pessoa_Consignatario;
    
    private String NM_Pessoa_Notificar;
    private String NM_Notificar;
    
    private String OID_Pessoa_Cotacao;
    private String NM_Cotacao;
    
    private String OID_Pessoa_Devedor_Exportador;
    private String NM_Devedor_Exportador;
    
    private String OID_Pessoa_Devedor_Importador;
    private String NM_Devedor_Importador;
    
    private String CD_Unidade;
    private double VL_Peso;
    private double VL_Peso_Cubado;
    private double VL_Nota_Fiscal;
    private double VL_Frete;
    private double VL_Reembolso;
    private String NM_Gasto_Destinatario1;
    private String NM_Gasto_Destinatario2;
    private String NM_Gasto_Destinatario3;
    private String NM_Gasto_Destinatario4;
    private String NM_Gasto_Remetente1;
    private String NM_Gasto_Remetente2;
    private String NM_Gasto_Remetente3;
    private String NM_Gasto_Remetente4;
    private String TX_Documentos1;
    private String TX_Remetente;
    private double VL_Gasto_Remetente1;
    private double VL_Gasto_Remetente2;
    private double VL_Gasto_Remetente3;
    private double VL_Gasto_Remetente4;
    private double VL_Gasto_RemetenteTotal;
    private double VL_Gasto_Destinatario1;
    private double VL_Gasto_Destinatario2;
    private double VL_Gasto_Destinatario3;
    private double VL_Gasto_Destinatario4;
    private double VL_Gasto_DestinatarioTotal;
    private String DT_Conversao;
    private String TX_Alfandega1;
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
    private long NR_Original;
    private long OID_Cidade_Embarque;
    private double VL_Seguro;
    private String TX_Documentos2;
    private String TX_Documentos3;
    private String TX_Documentos4;
    private String TX_Documentos5;
    private String TX_Documentos6;
    private String NM_Endereco_Notificar;
    private String NM_Endereco_Destinatario;
    private String NM_Destinatario;
    private String NM_Consignatario;
    private String NM_Endereco_Consignatario;
    private String CD_Pais;
    private String NR_Permisso;
    private String DM_Exportacao_Importacao;
    private String NR_Fatura;
    private double VL_Mercadoria;
    private double VL_Dolar;
    
    private String TX_Declaracao1;
    private String TX_Declaracao2;
    private String TX_Declaracao3;
    private String TX_Declaracao4;
    private String TX_Declaracao5;
    private String TX_Declaracao6;
    private String TX_Declaracao7;
    private String TX_Declaracao8;
    private String TX_Alfandega2;
    private String TX_Alfandega3;
    private String TX_Alfandega4;
    private String TX_Alfandega5;
    private String TX_Alfandega6;
    private double NR_Volumes;
    private String NM_Endereco_Remetente2;
    private String OID_Conhecimento_Internacional_Fatura;
    private String NM_Cidade_Remetente;
    private String NM_Cidade_Destinatario;
    private String NM_Cidade_Consignatario;
    private String NM_Cidade_Notificar;
    private String NM_Estado_Remetente;
    private String NM_Estado_Destinatario;
    private String NM_Estado_Consignatario;
    private String NM_Estado_Notificar;
    private String NM_Pais_Destinatario;
    private String NM_Pais_Consignatario;
    private String NM_Pais_Notificar;
    private String NM_Transportador;
    private String NM_Endereco_Transportador;
    private String NM_Cidade_Transportador;
    private String NM_Estado_Transportador;
    private String NM_Pais_Transportador;
    private String NM_Cidade_Embarque;
    private String CD_Estado_Embarque;
    private String NM_Pais_Embarque;
    private String NM_Cidade_Entrega;
    private String CD_Estado_Entrega;
    private String NM_Pais_Entrega;
    private String NM_Cidade_Emissao;
    private String CD_Estado_Emissao;
    private String NM_Pais_Emissao;
    private String NM_Gastos_Cidade1;
    private String NM_Gastos_Cidade2;
    private String NM_Gastos_Cidade3;
    private String NM_Gastos_Cidade4;
    private String via;
    private String DM_Moeda;
    private String DM_SO;
    private String DM_Folha;
    private String OID_Produto_Custo;
    private String NM_Produto_Custo;
    private String OID_Unidade_Negocio;
    private String NM_Unidade_Negocio;
    
    private String DM_Moeda1;
    private String DM_Moeda2;
    private String DM_Moeda3;
    private String DM_Moeda4;
    private String DM_Moeda5;
    private String DM_Moeda6;
    private String DM_Moeda7;
    private String DM_Moeda8;
    private String DM_Moeda9;
    private String DM_Moeda10;
    
    //  private String NR_CPF_CNPJ_InsEst_Transportador;
    
    private String NM_Cidade_Estado_Pais_Remetente;
    private String NM_Cidade_Estado_Pais_Destinatario;
    private String NM_Cidade_Estado_Pais_Consignatario;
    private String NM_Cidade_Estado_Pais_Notificar;
    private String NM_Cidade_Estado_Pais_Transportador;
    private String NM_Cidade_Estado_Pais_Embarque;
    private String NM_Cidade_Estado_Pais_Emissao;
    private String NM_Cidade_Estado_Pais_Entrega;
    private String NR_CPF_CNPJ_Destinatario;
    private String NR_CPF_CNPJ_Consignatario;
    private String NR_CPF_CNPJ_Notificar;
    private String NM_Sucessivos;
    private String NM_EMail;
    private String NR_Fone_Fax_Transportador;
    private String NR_CPF_CNPJ_InscEst_Transportador;
    
    private String NR_CPF_CNPJ_Consignatario_Complementar;
    private String NR_CPF_CNPJ_Notificar_Complementar;
    
    private String TX_VL_Mercadoria;
    private String TX_VL_Frete;
    private String TX_VL_Peso;
    private String TX_VL_Peso_Cubado;
    private String TX_VL_Nota_Fiscal;
    private String TX_VL_Reembolso;
    private String TX_NR_Volumes;
    
    private String TX_VL_Gasto_Remetente1;
    private String TX_VL_Gasto_Remetente2;
    private String TX_VL_Gasto_Remetente3;
    private String TX_VL_Gasto_Remetente4;
    private String TX_VL_Gasto_RemetenteTotal;
    private String TX_VL_Gasto_Destinatario1;
    private String TX_VL_Gasto_Destinatario2;
    private String TX_VL_Gasto_Destinatario3;
    private String TX_VL_Gasto_Destinatario4;
    private String TX_VL_Gasto_DestinatarioTotal;
    
  //TEO 18/04/05
  private String VL_Mercadoria_Editado;
  private String VL_Frete_Editado;
  private String VL_Peso_Editado;
  private String VL_Peso_Cubado_Editado;
  private String NR_Volumes_Editado;
  private long NR_Volumes_Observacao;
  
  private String NM_Icoterm;
  
  private String DM_Veiculo_Novo;
  private double VL_RCTRC;
  private double VL_Desconto_RCTRC;
  private double VL_RCTR_VI;
  private double VL_RCTR_DC;
  
  private double c15_VL_Frete_Peso;
  private double c15_VL_Ad_Valorem;
  private double c15_VL_Taxas;
  private double c15_VL_Outros;
  private double c15_VL_Total;

    private double VL_Frete_Peso;
    private double VL_Frete_Valor;
    private double VL_Taxas;
    private double VL_Outros;
    private double VL_Total_Custo;
    private double VL_Total_Frete;
    private double VL_Peso_Cubado_Calculado;
    private long oid_Tabela_Frete;
    private long oid_Origem;
    private long oid_Destino;
    private double VL_Frete_Merc;
    private String DT_Cruze;
    private String DT_Entrega;
    private String NM_Pessoa_Pagador;
    
    private java.util.Collection CRTDetalhes;
    private String dataRel;
    private String siglaRel;
    
    private String DT_Fatura;
    private String DT_Vencimento;
    private String DT_Pagamento;
    private long dias_Fatura;
    private long dias_Vcto;
    private long dias_Pgto;
    
    private String OID_Vendedor;
    private String NM_Vendedor;
    private double VL_Comissao;
    private double PE_Comissao;
    
    private String DM_Imprime1;
    private String DM_Imprime2;
    private String DM_Imprime3;
    private String DM_ImprimeCP;
    
    private String NM_Manifesto;
    private double VL_Receita;
    private double VL_Despesa;
    private double VL_Rota;
    private double NR_KM_Rodado;
    private String OID_Veiculo;
    private String NR_Placa;
    private String NM_Roteiro;
    private String CD_Roteiro;
    
    private double PE_Exportador;
    
    private java.util.Collection Faturas;
    
    
    public long getOid_Tabela_Frete() {
        return this.oid_Tabela_Frete;
    }
    public void setOid_Tabela_Frete(long oid_Tabela_Frete) {
        this.oid_Tabela_Frete = oid_Tabela_Frete;
    }
    public double getVL_Peso_Cubado_Calculado() {
        return this.VL_Peso_Cubado_Calculado;
    }
    public void setVL_Peso_Cubado_Calculado(double peso_Cubado_Calculado) {
        this.VL_Peso_Cubado_Calculado = peso_Cubado_Calculado;
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
    public String getNM_Devedor_Importador() {
        return NM_Devedor_Importador;
    }
    public void setNM_Devedor_Importador(String devedor_Importador) {
        NM_Devedor_Importador = devedor_Importador;
    }
    public String getOID_Pessoa_Devedor_Importador() {
        return OID_Pessoa_Devedor_Importador;
    }
    public void setOID_Pessoa_Devedor_Importador(String pessoa_Devedor_Importador) {
        OID_Pessoa_Devedor_Importador = pessoa_Devedor_Importador;
    }
    public String getNM_Devedor_Exportador() {
        return NM_Devedor_Exportador;
    }
    public void setNM_Devedor_Exportador(String devedor_Exportador) {
        NM_Devedor_Exportador = devedor_Exportador;
    }
    public String getOID_Pessoa_Devedor_Exportador() {
        return OID_Pessoa_Devedor_Exportador;
    }
    public void setOID_Pessoa_Devedor_Exportador(String pessoa_Devedor_Exportador) {
        OID_Pessoa_Devedor_Exportador = pessoa_Devedor_Exportador;
    }
    public String getNM_Cotacao() {
        return NM_Cotacao;
    }
    public void setNM_Cotacao(String cotacao) {
        NM_Cotacao = cotacao;
    }
    public String getOID_Pessoa_Cotacao() {
        return OID_Pessoa_Cotacao;
    }
    public void setOID_Pessoa_Cotacao(String pessoa_Cotacao) {
        OID_Pessoa_Cotacao = pessoa_Cotacao;
    }
    public int getOID_Unidade_Fronteira() {
        return OID_Unidade_Fronteira;
    }
    public void setOID_Unidade_Fronteira(int unidade_Fronteira) {
        OID_Unidade_Fronteira = unidade_Fronteira;
    }
    public double getVL_Dolar() {
        return VL_Dolar;
    }
    public void setVL_Dolar(double dolar) {
        VL_Dolar = dolar;
    }
    public String getTX_NR_Volumes() {
        return TX_NR_Volumes;
    }
    public void setTX_NR_Volumes(String volumes) {
        TX_NR_Volumes = volumes;
    }
    public String getTX_VL_Gasto_Destinatario1() {
        return TX_VL_Gasto_Destinatario1;
    }
    public void setTX_VL_Gasto_Destinatario1(String gasto_Destinatario1) {
        TX_VL_Gasto_Destinatario1 = gasto_Destinatario1;
    }
    public String getTX_VL_Gasto_Destinatario2() {
        return TX_VL_Gasto_Destinatario2;
    }
    public void setTX_VL_Gasto_Destinatario2(String gasto_Destinatario2) {
        TX_VL_Gasto_Destinatario2 = gasto_Destinatario2;
    }
    public String getTX_VL_Gasto_Destinatario3() {
        return TX_VL_Gasto_Destinatario3;
    }
    public void setTX_VL_Gasto_Destinatario3(String gasto_Destinatario3) {
        TX_VL_Gasto_Destinatario3 = gasto_Destinatario3;
    }
    public String getTX_VL_Gasto_Destinatario4() {
        return TX_VL_Gasto_Destinatario4;
    }
    public void setTX_VL_Gasto_Destinatario4(String gasto_Destinatario4) {
        TX_VL_Gasto_Destinatario4 = gasto_Destinatario4;
    }
    public String getTX_VL_Gasto_DestinatarioTotal() {
        return TX_VL_Gasto_DestinatarioTotal;
    }
    public void setTX_VL_Gasto_DestinatarioTotal(String gasto_DestinatarioTotal) {
        TX_VL_Gasto_DestinatarioTotal = gasto_DestinatarioTotal;
    }
    public String getTX_VL_Gasto_Remetente1() {
        return TX_VL_Gasto_Remetente1;
    }
    public void setTX_VL_Gasto_Remetente1(String gasto_Remetente1) {
        TX_VL_Gasto_Remetente1 = gasto_Remetente1;
    }
    public String getTX_VL_Gasto_Remetente2() {
        return TX_VL_Gasto_Remetente2;
    }
    public void setTX_VL_Gasto_Remetente2(String gasto_Remetente2) {
        TX_VL_Gasto_Remetente2 = gasto_Remetente2;
    }
    public String getTX_VL_Gasto_Remetente3() {
        return TX_VL_Gasto_Remetente3;
    }
    public void setTX_VL_Gasto_Remetente3(String gasto_Remetente3) {
        TX_VL_Gasto_Remetente3 = gasto_Remetente3;
    }
    public String getTX_VL_Gasto_Remetente4() {
        return TX_VL_Gasto_Remetente4;
    }
    public void setTX_VL_Gasto_Remetente4(String gasto_Remetente4) {
        TX_VL_Gasto_Remetente4 = gasto_Remetente4;
    }
    public String getTX_VL_Gasto_RemetenteTotal() {
        return TX_VL_Gasto_RemetenteTotal;
    }
    public void setTX_VL_Gasto_RemetenteTotal(String gasto_RemetenteTotal) {
        TX_VL_Gasto_RemetenteTotal = gasto_RemetenteTotal;
    }
    public String getTX_VL_Nota_Fiscal() {
        return TX_VL_Nota_Fiscal;
    }
    public void setTX_VL_Nota_Fiscal(String nota_Fiscal) {
        TX_VL_Nota_Fiscal = nota_Fiscal;
    }
    public String getTX_VL_Peso() {
        return TX_VL_Peso;
    }
    public void setTX_VL_Peso(String peso) {
        TX_VL_Peso = peso;
    }
    public String getTX_VL_Peso_Cubado() {
        return TX_VL_Peso_Cubado;
    }
    public void setTX_VL_Peso_Cubado(String peso_Cubado) {
        TX_VL_Peso_Cubado = peso_Cubado;
    }
    public String getTX_VL_Reembolso() {
        return TX_VL_Reembolso;
    }
    public void setTX_VL_Reembolso(String reembolso) {
        TX_VL_Reembolso = reembolso;
    }
    public String getTX_VL_Frete() {
        return TX_VL_Frete;
    }
    public void setTX_VL_Frete(String frete) {
        TX_VL_Frete = frete;
    }
    public String getTX_VL_Mercadoria() {
        return TX_VL_Mercadoria;
    }
    public void setTX_VL_Mercadoria(String mercadoria) {
        TX_VL_Mercadoria = mercadoria;
    }
    
    public String getNM_Notificar() {
        return NM_Notificar;
    }
    public void setNM_Notificar(String notificar) {
        NM_Notificar = notificar;
    }
    public String getNR_CNPJ_Destinatario_Complementar() {
        return NR_CNPJ_Destinatario_Complementar;
    }
    public void setNR_CNPJ_Destinatario_Complementar(
            String destinatario_Complementar) {
        NR_CNPJ_Destinatario_Complementar = destinatario_Complementar;
    }
    public String getNR_CNPJ_Remetente_Complementar() {
        return NR_CNPJ_Remetente_Complementar;
    }
    public void setNR_CNPJ_Remetente_Complementar(String remetente_Complementar) {
        NR_CNPJ_Remetente_Complementar = remetente_Complementar;
    }
    public String getNM_Razao_Social_Remetente() {
        return NM_Razao_Social_Remetente;
    }
    public void setNM_Razao_Social_Remetente(String razao_Social_Remetente) {
        NM_Razao_Social_Remetente = razao_Social_Remetente;
    }
    public int getOID_Unidade_Destino() {
        return OID_Unidade_Destino;
    }
    public void setOID_Unidade_Destino(int unidade_Destino) {
        OID_Unidade_Destino = unidade_Destino;
    }
    public int getOID_Unidade_Origem() {
        return OID_Unidade_Origem;
    }
    public void setOID_Unidade_Origem(int unidade_Origem) {
        OID_Unidade_Origem = unidade_Origem;
    }
    //public String getNR_CPF_CNPJ_InsEst_Transportador() {
    //	return NR_CPF_CNPJ_InsEst_Transportador;
    //}
    //public void setNR_CPF_CNPJ_InsEst_Transportador(String insEst_Transportador) {
    //	NR_CPF_CNPJ_InsEst_Transportador = insEst_Transportador;
    //}
    public String getNR_CPF_CNPJ_InscEst_Transportador() {
        return NR_CPF_CNPJ_InscEst_Transportador;
    }
    public void setNR_CPF_CNPJ_InscEst_Transportador(String inscEst_Transportador) {
        NR_CPF_CNPJ_InscEst_Transportador = inscEst_Transportador;
    }
    public String getNR_Fone_Fax_Transportador() {
        return NR_Fone_Fax_Transportador;
    }
    public void setNR_Fone_Fax_Transportador(String fone_Fax_Transportador) {
        NR_Fone_Fax_Transportador = fone_Fax_Transportador;
    }
    public String getNM_EMail() {
        return NM_EMail;
    }
    public void setNM_EMail(String mail) {
        NM_EMail = mail;
    }
    public String getTX_Documentos4() {
        return TX_Documentos4;
    }
    public void setTX_Documentos4(String documentos4) {
        TX_Documentos4 = documentos4;
    }
    public String getTX_Documentos5() {
        return TX_Documentos5;
    }
    public void setTX_Documentos5(String documentos5) {
        TX_Documentos5 = documentos5;
    }
    public String getTX_Documentos6() {
        return TX_Documentos6;
    }
    public void setTX_Documentos6(String documentos6) {
        TX_Documentos6 = documentos6;
    }
    public String getTX_Alfandega4() {
        return TX_Alfandega4;
    }
    public void setTX_Alfandega4(String alfandega4) {
        TX_Alfandega4 = alfandega4;
    }
    public String getTX_Alfandega5() {
        return TX_Alfandega5;
    }
    public void setTX_Alfandega5(String alfandega5) {
        TX_Alfandega5 = alfandega5;
    }
    public String getTX_Alfandega6() {
        return TX_Alfandega6;
    }
    public void setTX_Alfandega6(String alfandega6) {
        TX_Alfandega6 = alfandega6;
    }
    public String getTX_Declaracao5() {
        return TX_Declaracao5;
    }
    public void setTX_Declaracao5(String declaracao5) {
        TX_Declaracao5 = declaracao5;
    }
    public String getTX_Declaracao6() {
        return TX_Declaracao6;
    }
    public void setTX_Declaracao6(String declaracao6) {
        TX_Declaracao6 = declaracao6;
    }
    public String getTX_Declaracao7() {
        return TX_Declaracao7;
    }
    public void setTX_Declaracao7(String declaracao7) {
        TX_Declaracao7 = declaracao7;
    }
    public String getTX_Declaracao8() {
        return TX_Declaracao8;
    }
    public void setTX_Declaracao8(String declaracao8) {
        TX_Declaracao8 = declaracao8;
    }
    public String getNM_Sucessivos() {
        return NM_Sucessivos;
    }
    public void setNM_Sucessivos(String sucessivos) {
        NM_Sucessivos = sucessivos;
    }
    public String getNR_CPF_CNPJ_Notificar() {
        return NR_CPF_CNPJ_Notificar;
    }
    public void setNR_CPF_CNPJ_Notificar(String notificar) {
        NR_CPF_CNPJ_Notificar = notificar;
    }
    public String getNR_CPF_CNPJ_Consignatario() {
        return NR_CPF_CNPJ_Consignatario;
    }
    public void setNR_CPF_CNPJ_Consignatario(String consignatario) {
        NR_CPF_CNPJ_Consignatario = consignatario;
    }
    public String getNR_CPF_CNPJ_Destinatario() {
        return NR_CPF_CNPJ_Destinatario;
    }
    public void setNR_CPF_CNPJ_Destinatario(String destinatario) {
        NR_CPF_CNPJ_Destinatario = destinatario;
    }
    public String getNR_CPF_CNPJ_Remetente() {
        return NR_CPF_CNPJ_Remetente;
    }
    public void setNR_CPF_CNPJ_Remetente(String remetente) {
        NR_CPF_CNPJ_Remetente = remetente;
    }
    public String getNM_Cidade_Estado_Pais_Entrega() {
        return NM_Cidade_Estado_Pais_Entrega;
    }
    public void setNM_Cidade_Estado_Pais_Entrega(String cidade_Estado_Pais_Entrega) {
        NM_Cidade_Estado_Pais_Entrega = cidade_Estado_Pais_Entrega;
    }
    public String getNM_Cidade_Estado_Pais_Emissao() {
        return NM_Cidade_Estado_Pais_Emissao;
    }
    public void setNM_Cidade_Estado_Pais_Emissao(String cidade_Estado_Pais_Emissao) {
        NM_Cidade_Estado_Pais_Emissao = cidade_Estado_Pais_Emissao;
    }
    public String getNM_Cidade_Estado_Pais_Embarque() {
        return NM_Cidade_Estado_Pais_Embarque;
    }
    public void setNM_Cidade_Estado_Pais_Embarque(String cidade_Estado_Pais_Embarque) {
        NM_Cidade_Estado_Pais_Embarque = cidade_Estado_Pais_Embarque;
    }
    public String getNM_Cidade_Estado_Pais_Transportador() {
        return NM_Cidade_Estado_Pais_Transportador;
    }
    public void setNM_Cidade_Estado_Pais_Transportador(
            String cidade_Estado_Pais_Transportador) {
        NM_Cidade_Estado_Pais_Transportador = cidade_Estado_Pais_Transportador;
    }
    public String getNM_Cidade_Estado_Pais_Consignatario() {
        return NM_Cidade_Estado_Pais_Consignatario;
    }
    public void setNM_Cidade_Estado_Pais_Consignatario(
            String cidade_Estado_Pais_Consignatario) {
        NM_Cidade_Estado_Pais_Consignatario = cidade_Estado_Pais_Consignatario;
    }
    public String getNM_Cidade_Estado_Pais_Destinatario() {
        return NM_Cidade_Estado_Pais_Destinatario;
    }
    public void setNM_Cidade_Estado_Pais_Destinatario(
            String cidade_Estado_Pais_Destinatario) {
        NM_Cidade_Estado_Pais_Destinatario = cidade_Estado_Pais_Destinatario;
    }
    public String getNM_Cidade_Estado_Pais_Notificar() {
        return NM_Cidade_Estado_Pais_Notificar;
    }
    public void setNM_Cidade_Estado_Pais_Notificar(
            String cidade_Estado_Pais_Notificar) {
        NM_Cidade_Estado_Pais_Notificar = cidade_Estado_Pais_Notificar;
    }
    public String getNM_Cidade_Estado_Pais_Remetente() {
        return NM_Cidade_Estado_Pais_Remetente;
    }
    public void setNM_Cidade_Estado_Pais_Remetente(
            String cidade_Estado_Pais_Remetente) {
        NM_Cidade_Estado_Pais_Remetente = cidade_Estado_Pais_Remetente;
    }
    public void setOID_Conhecimento(String OID_Conhecimento) {
        this.OID_Conhecimento = OID_Conhecimento;
    }
    public String getOID_Conhecimento() {
        return OID_Conhecimento;
    }
    public void setNR_Conhecimento(long NR_Conhecimento) {
        this.NR_Conhecimento = NR_Conhecimento;
    }
    public long getNR_Conhecimento() {
        return NR_Conhecimento;
    }
    public void setNM_Serie(String NM_Serie) {
        this.NM_Serie = NM_Serie;
    }
    public String getNM_Serie() {
        return NM_Serie;
    }
    public void setDM_Responsavel_Cobranca(String DM_Responsavel_Cobranca) {
        this.DM_Responsavel_Cobranca = DM_Responsavel_Cobranca;
    }
    public String getDM_Responsavel_Cobranca() {
        return DM_Responsavel_Cobranca;
    }
    public void setDM_Tipo_Pagamento(String DM_Tipo_Pagamento) {
        this.DM_Tipo_Pagamento = DM_Tipo_Pagamento;
    }
    public String getDM_Tipo_Pagamento() {
        return DM_Tipo_Pagamento;
    }
    public void setDM_Situacao(String DM_Situacao) {
        this.DM_Situacao = DM_Situacao;
    }
    public String getDM_Situacao() {
        return DM_Situacao;
    }
    public void setTX_Observacao1(String TX_Observacao1) {
        this.TX_Observacao1 = TX_Observacao1;
    }
    public String getTX_Observacao1() {
        return TX_Observacao1;
    }
    public void setDM_Isento_Seguro(String DM_Isento_Seguro) {
        this.DM_Isento_Seguro = DM_Isento_Seguro;
    }
    public String getDM_Isento_Seguro() {
        return DM_Isento_Seguro;
    }
    public void setDT_Emissao(String DT_Emissao) {
        this.DT_Emissao = DT_Emissao;
    }
    public String getDT_Emissao() {
        return DT_Emissao;
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
    public void setOID_Pessoa_Consignatario(String OID_Pessoa_Consignatario) {
        this.OID_Pessoa_Consignatario = OID_Pessoa_Consignatario;
    }
    public String getOID_Pessoa_Consignatario() {
        return OID_Pessoa_Consignatario;
    }
    public void setOID_Modal(long OID_Modal) {
        this.OID_Modal = OID_Modal;
    }
    public long getOID_Modal() {
        return OID_Modal;
    }
    public void setOID_Cidade(long OID_Cidade) {
        this.OID_Cidade = OID_Cidade;
    }
    public long getOID_Cidade() {
        return OID_Cidade;
    }
    public void setOID_Cidade_Destino(long OID_Cidade_Destino) {
        this.OID_Cidade_Destino = OID_Cidade_Destino;
    }
    public long getOID_Cidade_Destino() {
        return OID_Cidade_Destino;
    }
    public void setOID_Pessoa_Pagador(String OID_Pessoa_Pagador) {
        this.OID_Pessoa_Pagador = OID_Pessoa_Pagador;
    }
    public String getOID_Pessoa_Pagador() {
        return OID_Pessoa_Pagador;
    }
    public void setOID_Produto(long OID_Produto) {
        this.OID_Produto = OID_Produto;
    }
    public long getOID_Produto() {
        return OID_Produto;
    }
    public void setOID_Coleta(long OID_Coleta) {
        this.OID_Coleta = OID_Coleta;
    }
    public long getOID_Coleta() {
        return OID_Coleta;
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
    public void setNM_Pessoa_Consignatario(String NM_Pessoa_Consignatario) {
        this.NM_Pessoa_Consignatario = NM_Pessoa_Consignatario;
    }
    public String getNM_Pessoa_Consignatario() {
        return NM_Pessoa_Consignatario;
    }
    public void setCD_Unidade(String CD_Unidade) {
        this.CD_Unidade = CD_Unidade;
    }
    public String getCD_Unidade() {
        return CD_Unidade;
    }
    public void setVL_Peso(double VL_Peso) {
        this.VL_Peso = VL_Peso;
    }
    public double getVL_Peso() {
        return VL_Peso;
    }
    public void setVL_Peso_Cubado(double VL_Peso_Cubado) {
        this.VL_Peso_Cubado = VL_Peso_Cubado;
    }
    public double getVL_Peso_Cubado() {
        return VL_Peso_Cubado;
    }
    public void setVL_Nota_Fiscal(double VL_Nota_Fiscal) {
        this.VL_Nota_Fiscal = VL_Nota_Fiscal;
    }
    public double getVL_Nota_Fiscal() {
        return VL_Nota_Fiscal;
    }
    public void setVL_Frete(double VL_Frete) {
        this.VL_Frete = VL_Frete;
    }
    public double getVL_Frete() {
        return VL_Frete;
    }
    public void setVL_Reembolso(double VL_Reembolso) {
        this.VL_Reembolso = VL_Reembolso;
    }
    public double getVL_Reembolso() {
        return VL_Reembolso;
    }
    public void setNR_Volumes(double NR_Volumes) {
        this.NR_Volumes = NR_Volumes;
    }
    public double getNR_Volumes() {
        return NR_Volumes;
    }
    public void setNM_Gasto_Destinatario1(String NM_Gasto_Destinatario1) {
        this.NM_Gasto_Destinatario1 = NM_Gasto_Destinatario1;
    }
    public String getNM_Gasto_Destinatario1() {
        return NM_Gasto_Destinatario1;
    }
    public void setNM_Gasto_Destinatario2(String NM_Gasto_Destinatario2) {
        this.NM_Gasto_Destinatario2 = NM_Gasto_Destinatario2;
    }
    public String getNM_Gasto_Destinatario2() {
        return NM_Gasto_Destinatario2;
    }
    public void setNM_Gasto_Destinatario3(String NM_Gasto_Destinatario3) {
        this.NM_Gasto_Destinatario3 = NM_Gasto_Destinatario3;
    }
    public String getNM_Gasto_Destinatario3() {
        return NM_Gasto_Destinatario3;
    }
    public void setNM_Gasto_Destinatario4(String NM_Gasto_Destinatario4) {
        this.NM_Gasto_Destinatario4 = NM_Gasto_Destinatario4;
    }
    public String getNM_Gasto_Destinatario4() {
        return NM_Gasto_Destinatario4;
    }
    public void setNM_Gasto_Remetente1(String NM_Gasto_Remetente1) {
        this.NM_Gasto_Remetente1 = NM_Gasto_Remetente1;
    }
    public String getNM_Gasto_Remetente1() {
        return NM_Gasto_Remetente1;
    }
    public void setNM_Gasto_Remetente2(String NM_Gasto_Remetente2) {
        this.NM_Gasto_Remetente2 = NM_Gasto_Remetente2;
    }
    public String getNM_Gasto_Remetente2() {
        return NM_Gasto_Remetente2;
    }
    public void setNM_Gasto_Remetente3(String NM_Gasto_Remetente3) {
        this.NM_Gasto_Remetente3 = NM_Gasto_Remetente3;
    }
    public String getNM_Gasto_Remetente3() {
        return NM_Gasto_Remetente3;
    }
    public void setNM_Gasto_Remetente4(String NM_Gasto_Remetente4) {
        this.NM_Gasto_Remetente4 = NM_Gasto_Remetente4;
    }
    public String getNM_Gasto_Remetente4() {
        return NM_Gasto_Remetente4;
    }
    public void setTX_Documentos1(String TX_Documentos1) {
        this.TX_Documentos1 = TX_Documentos1;
    }
    public String getTX_Documentos1() {
        return TX_Documentos1;
    }
    public void setTX_Declaracao1(String TX_Declaracao1) {
        this.TX_Declaracao1 = TX_Declaracao1;
    }
    public String getTX_Declaracao1() {
        return TX_Declaracao1;
    }
    public void setTX_Remetente(String TX_Remetente) {
        this.TX_Remetente = TX_Remetente;
    }
    public String getTX_Remetente() {
        return TX_Remetente;
    }
    public void setVL_Gasto_Remetente1(double VL_Gasto_Remetente1) {
        this.VL_Gasto_Remetente1 = VL_Gasto_Remetente1;
    }
    public double getVL_Gasto_Remetente1() {
        return VL_Gasto_Remetente1;
    }
    public void setVL_Gasto_Remetente2(double VL_Gasto_Remetente2) {
        this.VL_Gasto_Remetente2 = VL_Gasto_Remetente2;
    }
    public double getVL_Gasto_Remetente2() {
        return VL_Gasto_Remetente2;
    }
    public void setVL_Gasto_Remetente3(double VL_Gasto_Remetente3) {
        this.VL_Gasto_Remetente3 = VL_Gasto_Remetente3;
    }
    public double getVL_Gasto_Remetente3() {
        return VL_Gasto_Remetente3;
    }
    public void setVL_Gasto_Remetente4(double VL_Gasto_Remetente4) {
        this.VL_Gasto_Remetente4 = VL_Gasto_Remetente4;
    }
    public double getVL_Gasto_Remetente4() {
        return VL_Gasto_Remetente4;
    }
    public void setVL_Gasto_Destinatario1(double VL_Gasto_Destinatario1) {
        this.VL_Gasto_Destinatario1 = VL_Gasto_Destinatario1;
    }
    public double getVL_Gasto_Destinatario1() {
        return VL_Gasto_Destinatario1;
    }
    public void setVL_Gasto_Destinatario2(double VL_Gasto_Destinatario2) {
        this.VL_Gasto_Destinatario2 = VL_Gasto_Destinatario2;
    }
    public double getVL_Gasto_Destinatario2() {
        return VL_Gasto_Destinatario2;
    }
    public void setVL_Gasto_Destinatario3(double VL_Gasto_Destinatario3) {
        this.VL_Gasto_Destinatario3 = VL_Gasto_Destinatario3;
    }
    public double getVL_Gasto_Destinatario3() {
        return VL_Gasto_Destinatario3;
    }
    public void setVL_Gasto_Destinatario4(double VL_Gasto_Destinatario4) {
        this.VL_Gasto_Destinatario4 = VL_Gasto_Destinatario4;
    }
    public double getVL_Gasto_Destinatario4() {
        return VL_Gasto_Destinatario4;
    }
    public void setDT_Conversao(String DT_Conversao) {
        this.DT_Conversao = DT_Conversao;
    }
    public String getDT_Conversao() {
        return DT_Conversao;
    }
    public void setTX_Alfandega1(String TX_Alfandega1) {
        this.TX_Alfandega1 = TX_Alfandega1;
    }
    public String getTX_Alfandega1() {
        return TX_Alfandega1;
    }
    public void setTX_Observacao2(String TX_Observacao2) {
        this.TX_Observacao2 = TX_Observacao2;
    }
    public String getTX_Observacao2() {
        return TX_Observacao2;
    }
    public void setTX_Observacao3(String TX_Observacao3) {
        this.TX_Observacao3 = TX_Observacao3;
    }
    public String getTX_Observacao3() {
        return TX_Observacao3;
    }
    public void setTX_Observacao4(String TX_Observacao4) {
        this.TX_Observacao4 = TX_Observacao4;
    }
    public String getTX_Observacao4() {
        return TX_Observacao4;
    }
    public void setTX_Observacao5(String TX_Observacao5) {
        this.TX_Observacao5 = TX_Observacao5;
    }
    public String getTX_Observacao5() {
        return TX_Observacao5;
    }
    public void setTX_Observacao6(String TX_Observacao6) {
        this.TX_Observacao6 = TX_Observacao6;
    }
    public String getTX_Observacao6() {
        return TX_Observacao6;
    }
    public void setTX_Observacao7(String TX_Observacao7) {
        this.TX_Observacao7 = TX_Observacao7;
    }
    public String getTX_Observacao7() {
        return TX_Observacao7;
    }
    public void setTX_Observacao8(String TX_Observacao8) {
        this.TX_Observacao8 = TX_Observacao8;
    }
    public String getTX_Observacao8() {
        return TX_Observacao8;
    }
    public void setTX_Observacao9(String TX_Observacao9) {
        this.TX_Observacao9 = TX_Observacao9;
    }
    public String getTX_Observacao9() {
        return TX_Observacao9;
    }
    public void setNR_Original(long NR_Original) {
        this.NR_Original = NR_Original;
    }
    public long getNR_Original() {
        return NR_Original;
    }
    public void setOID_Cidade_Embarque(long OID_Cidade_Embarque) {
        this.OID_Cidade_Embarque = OID_Cidade_Embarque;
    }
    public long getOID_Cidade_Embarque() {
        return OID_Cidade_Embarque;
    }
    public void setVL_Seguro(double VL_Seguro) {
        this.VL_Seguro = VL_Seguro;
    }
    public double getVL_Seguro() {
        return VL_Seguro;
    }
    public void setTX_Documentos2(String TX_Documentos2) {
        this.TX_Documentos2 = TX_Documentos2;
    }
    public String getTX_Documentos2() {
        return TX_Documentos2;
    }
    public void setTX_Documentos3(String TX_Documentos3) {
        this.TX_Documentos3 = TX_Documentos3;
    }
    public String getTX_Documentos3() {
        return TX_Documentos3;
    }
    public void setNM_Endereco_Remetente(String NM_Endereco_Remetente) {
        this.NM_Endereco_Remetente = NM_Endereco_Remetente;
    }
    public String getNM_Endereco_Remetente() {
        return NM_Endereco_Remetente;
    }
    public void setNM_Remetente(String NM_Remetente) {
        this.NM_Remetente = NM_Remetente;
    }
    public String getNM_Remetente() {
        return NM_Remetente;
    }
    public void setNM_Endereco_Destinatario(String NM_Endereco_Destinatario) {
        this.NM_Endereco_Destinatario = NM_Endereco_Destinatario;
    }
    public String getNM_Endereco_Destinatario() {
        return NM_Endereco_Destinatario;
    }
    public void setNM_Destinatario(String NM_Destinatario) {
        this.NM_Destinatario = NM_Destinatario;
    }
    public String getNM_Destinatario() {
        return NM_Destinatario;
    }
    public void setNM_Consignatario(String NM_Consignatario) {
        this.NM_Consignatario = NM_Consignatario;
    }
    public String getNM_Consignatario() {
        return NM_Consignatario;
    }
    public void setNM_Endereco_Consignatario(String NM_Endereco_Consignatario) {
        this.NM_Endereco_Consignatario = NM_Endereco_Consignatario;
    }
    public String getNM_Endereco_Consignatario() {
        return NM_Endereco_Consignatario;
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
    public void setNR_Fatura(String NR_Fatura) {
        this.NR_Fatura = NR_Fatura;
    }
    public String getNR_Fatura() {
        return NR_Fatura;
    }
    public void setVL_Mercadoria(double VL_Mercadoria) {
        this.VL_Mercadoria = VL_Mercadoria;
    }
    public double getVL_Mercadoria() {
        return VL_Mercadoria;
    }
    public void setTX_Declaracao2(String TX_Declaracao2) {
        this.TX_Declaracao2 = TX_Declaracao2;
    }
    public String getTX_Declaracao2() {
        return TX_Declaracao2;
    }
    public void setTX_Declaracao3(String TX_Declaracao3) {
        this.TX_Declaracao3 = TX_Declaracao3;
    }
    public String getTX_Declaracao3() {
        return TX_Declaracao3;
    }
    public void setTX_Alfandega2(String TX_Alfandega2) {
        this.TX_Alfandega2 = TX_Alfandega2;
    }
    public String getTX_Alfandega2() {
        return TX_Alfandega2;
    }
    public void setTX_Alfandega3(String TX_Alfandega3) {
        this.TX_Alfandega3 = TX_Alfandega3;
    }
    public String getTX_Alfandega3() {
        return TX_Alfandega3;
    }
    public String getTX_Observacao10() {
        return TX_Observacao10;
    }
    public String getTX_Observacao11() {
        return TX_Observacao11;
    }
    public String getTX_Observacao12() {
        return TX_Observacao12;
    }
    public String getTX_Observacao13() {
        return TX_Observacao13;
    }
    public String getTX_Observacao14() {
        return TX_Observacao14;
    }
    public String getTX_Observacao15() {
        return TX_Observacao15;
    }
    public void setTX_Observacao10(String TX_Observacao10) {
        this.TX_Observacao10 = TX_Observacao10;
    }
    public void setTX_Observacao11(String TX_Observacao11) {
        this.TX_Observacao11 = TX_Observacao11;
    }
    public void setTX_Observacao12(String TX_Observacao12) {
        this.TX_Observacao12 = TX_Observacao12;
    }
    public void setTX_Observacao13(String TX_Observacao13) {
        this.TX_Observacao13 = TX_Observacao13;
    }
    public void setTX_Observacao14(String TX_Observacao14) {
        this.TX_Observacao14 = TX_Observacao14;
    }
    public void setTX_Observacao15(String TX_Observacao15) {
        this.TX_Observacao15 = TX_Observacao15;
    }
    
    public void setTX_Declaracao4(String TX_Declaracao4) {
        this.TX_Declaracao4 = TX_Declaracao4;
    }
    public String getTX_Declaracao4() {
        return TX_Declaracao4;
    }
    
    public void setNM_Endereco_Remetente2(String NM_Endereco_Remetente2) {
        this.NM_Endereco_Remetente2 = NM_Endereco_Remetente2;
    }
    public String getNM_Endereco_Remetente2() {
        return NM_Endereco_Remetente2;
    }
    public void setOID_Pessoa_Notificar(String OID_Pessoa_Notificar) {
        this.OID_Pessoa_Notificar = OID_Pessoa_Notificar;
    }
    public String getOID_Pessoa_Notificar() {
        return OID_Pessoa_Notificar;
    }
    
    public String getDM_SO() {
        return DM_SO;
    }
    
    public void setDM_SO(String dm_so) {
        DM_SO = dm_so;
    }
    public String getOID_Conhecimento_Internacional_Fatura() {
        return OID_Conhecimento_Internacional_Fatura;
    }
    public void setOID_Conhecimento_Internacional_Fatura(
            String conhecimento_Internacional_Fatura) {
        OID_Conhecimento_Internacional_Fatura = conhecimento_Internacional_Fatura;
    }
    public String getNM_Conhecimento() {
        return NM_Conhecimento;
    }
    public void setNM_Conhecimento(String conhecimento) {
        NM_Conhecimento = conhecimento;
    }
    public String getNM_Cidade_Consignatario() {
        return NM_Cidade_Consignatario;
    }
    public void setNM_Cidade_Consignatario(String cidade_Consignatario) {
        NM_Cidade_Consignatario = cidade_Consignatario;
    }
    public String getNM_Cidade_Destinatario() {
        return NM_Cidade_Destinatario;
    }
    public void setNM_Cidade_Destinatario(String cidade_Destinatario) {
        NM_Cidade_Destinatario = cidade_Destinatario;
    }
    public String getNM_Cidade_Remetente() {
        return NM_Cidade_Remetente;
    }
    public void setNM_Cidade_Remetente(String cidade_Remetente) {
        NM_Cidade_Remetente = cidade_Remetente;
    }
    public String getNM_Estado_Consignatario() {
        return NM_Estado_Consignatario;
    }
    public void setNM_Estado_Consignatario(String estado_Consignatario) {
        NM_Estado_Consignatario = estado_Consignatario;
    }
    public String getNM_Estado_Destinatario() {
        return NM_Estado_Destinatario;
    }
    public void setNM_Estado_Destinatario(String estado_Destinatario) {
        NM_Estado_Destinatario = estado_Destinatario;
    }
    public String getNM_Estado_Remetente() {
        return NM_Estado_Remetente;
    }
    public void setNM_Estado_Remetente(String estado_Remetente) {
        NM_Estado_Remetente = estado_Remetente;
    }
    public String getNM_Pais_Consignatario() {
        return NM_Pais_Consignatario;
    }
    public void setNM_Pais_Consignatario(String pais_Consignatario) {
        NM_Pais_Consignatario = pais_Consignatario;
    }
    public String getNM_Pais_Destinatario() {
        return NM_Pais_Destinatario;
    }
    public void setNM_Pais_Destinatario(String pais_Destinatario) {
        NM_Pais_Destinatario = pais_Destinatario;
    }
    public String getNM_Pais_Remetente() {
        return NM_Pais_Remetente;
    }
    public void setNM_Pais_Remetente(String pais_Remetente) {
        NM_Pais_Remetente = pais_Remetente;
    }
    public String getNM_Cidade_Notificar() {
        return NM_Cidade_Notificar;
    }
    public void setNM_Cidade_Notificar(String cidade_Notificar) {
        NM_Cidade_Notificar = cidade_Notificar;
    }
    public String getNM_Endereco_Notificar() {
        return NM_Endereco_Notificar;
    }
    public void setNM_Endereco_Notificar(String endereco_Notificar) {
        NM_Endereco_Notificar = endereco_Notificar;
    }
    public String getNM_Estado_Notificar() {
        return NM_Estado_Notificar;
    }
    public void setNM_Estado_Notificar(String estado_Notificar) {
        NM_Estado_Notificar = estado_Notificar;
    }
    public String getNM_Pais_Notificar() {
        return NM_Pais_Notificar;
    }
    public void setNM_Pais_Notificar(String pais_Notificar) {
        NM_Pais_Notificar = pais_Notificar;
    }
    public String getNM_Pessoa_Notificar() {
        return NM_Pessoa_Notificar;
    }
    public void setNM_Pessoa_Notificar(String pessoa_Notificar) {
        NM_Pessoa_Notificar = pessoa_Notificar;
    }
    public String getNM_Endereco_Transportador() {
        return NM_Endereco_Transportador;
    }
    public void setNM_Endereco_Transportador(String endereco_Transportador) {
        NM_Endereco_Transportador = endereco_Transportador;
    }
    public String getNM_Transportador() {
        return NM_Transportador;
    }
    public void setNM_Transportador(String transportador) {
        NM_Transportador = transportador;
    }
    public String getVia() {
        return via;
    }
    public void setVia(String via) {
        this.via = via;
    }
    public String getNM_Cidade_Transportador() {
        return NM_Cidade_Transportador;
    }
    public void setNM_Cidade_Transportador(String cidade_Transportador) {
        NM_Cidade_Transportador = cidade_Transportador;
    }
    public String getNM_Estado_Transportador() {
        return NM_Estado_Transportador;
    }
    public void setNM_Estado_Transportador(String estado_Transportador) {
        NM_Estado_Transportador = estado_Transportador;
    }
    public String getNM_Pais_Transportador() {
        return NM_Pais_Transportador;
    }
    public void setNM_Pais_Transportador(String pais_Transportador) {
        NM_Pais_Transportador = pais_Transportador;
    }
    public String getCD_Estado_Embarque() {
        return CD_Estado_Embarque;
    }
    public void setCD_Estado_Embarque(String estado_Embarque) {
        CD_Estado_Embarque = estado_Embarque;
    }
    public String getNM_Cidade_Embarque() {
        return NM_Cidade_Embarque;
    }
    public void setNM_Cidade_Embarque(String cidade_Embarque) {
        NM_Cidade_Embarque = cidade_Embarque;
    }
    public String getNM_Pais_Embarque() {
        return NM_Pais_Embarque;
    }
    public void setNM_Pais_Embarque(String pais_Embarque) {
        NM_Pais_Embarque = pais_Embarque;
    }
    public String getCD_Estado_Entrega() {
        return CD_Estado_Entrega;
    }
    public void setCD_Estado_Entrega(String estado_Entrega) {
        CD_Estado_Entrega = estado_Entrega;
    }
    public String getNM_Cidade_Entrega() {
        return NM_Cidade_Entrega;
    }
    public void setNM_Cidade_Entrega(String cidade_Entrega) {
        NM_Cidade_Entrega = cidade_Entrega;
    }
    public String getNM_Pais_Entrega() {
        return NM_Pais_Entrega;
    }
    public void setNM_Pais_Entrega(String pais_Entrega) {
        NM_Pais_Entrega = pais_Entrega;
    }
    public String getDM_Moeda() {
        return DM_Moeda;
    }
    public void setDM_Moeda(String moeda) {
        DM_Moeda = moeda;
    }
    public String getCD_Estado_Emissao() {
        return CD_Estado_Emissao;
    }
    public void setCD_Estado_Emissao(String estado_Emissao) {
        CD_Estado_Emissao = estado_Emissao;
    }
    public String getNM_Cidade_Emissao() {
        return NM_Cidade_Emissao;
    }
    public void setNM_Cidade_Emissao(String cidade_Emissao) {
        NM_Cidade_Emissao = cidade_Emissao;
    }
    public String getNM_Pais_Emissao() {
        return NM_Pais_Emissao;
    }
    public void setNM_Pais_Emissao(String pais_Emissao) {
        NM_Pais_Emissao = pais_Emissao;
    }
    public String getNM_Gastos_Cidade1() {
        return NM_Gastos_Cidade1;
    }
    public void setNM_Gastos_Cidade1(String gastos_Cidade1) {
        NM_Gastos_Cidade1 = gastos_Cidade1;
    }
    public String getNM_Gastos_Cidade2() {
        return NM_Gastos_Cidade2;
    }
    public void setNM_Gastos_Cidade2(String gastos_Cidade2) {
        NM_Gastos_Cidade2 = gastos_Cidade2;
    }
    public String getNM_Gastos_Cidade3() {
        return NM_Gastos_Cidade3;
    }
    public void setNM_Gastos_Cidade3(String gastos_Cidade3) {
        NM_Gastos_Cidade3 = gastos_Cidade3;
    }
    public String getNM_Gastos_Cidade4() {
        return NM_Gastos_Cidade4;
    }
    public void setNM_Gastos_Cidade4(String gastos_Cidade4) {
        NM_Gastos_Cidade4 = gastos_Cidade4;
    }
    public double getVL_Gasto_DestinatarioTotal() {
        return VL_Gasto_DestinatarioTotal;
    }
    public void setVL_Gasto_DestinatarioTotal(double gasto_DestinatarioTotal) {
        VL_Gasto_DestinatarioTotal = gasto_DestinatarioTotal;
    }
    public double getVL_Gasto_RemetenteTotal() {
        return VL_Gasto_RemetenteTotal;
    }
    public void setVL_Gasto_RemetenteTotal(double gasto_RemetenteTotal) {
        VL_Gasto_RemetenteTotal = gasto_RemetenteTotal;
    }
    public String getDM_Folha() {
        return DM_Folha;
    }
    public void setDM_Folha(String folha) {
        DM_Folha = folha;
    }
    public String getNM_Produto_Custo() {
        return NM_Produto_Custo;
    }
    public void setNM_Produto_Custo(String NM_Produto_Custo) {
        this.NM_Produto_Custo = NM_Produto_Custo;
    }
    public String getNM_Unidade_Negocio() {
        return NM_Unidade_Negocio;
    }
    public void setNM_Unidade_Negocio(String NM_Unidade_Negocio) {
        this.NM_Unidade_Negocio = NM_Unidade_Negocio;
    }
    public String getOID_Produto_Custo() {
        return OID_Produto_Custo;
    }
    public String getOID_Unidade_Negocio() {
        return OID_Unidade_Negocio;
    }
    public void setOID_Produto_Custo(String OID_Produto_Custo) {
        this.OID_Produto_Custo = OID_Produto_Custo;
    }
    public void setOID_Unidade_Negocio(String OID_Unidade_Negocio) {
        this.OID_Unidade_Negocio = OID_Unidade_Negocio;
    }
	public String getNR_Volumes_Editado() {
	    return NR_Volumes_Editado;
	}
	public void setNR_Volumes_Editado(String volumes_Editado) {
	    NR_Volumes_Editado = volumes_Editado;
	}
	public String getVL_Frete_Editado() {
	    return VL_Frete_Editado;
	}
	public void setVL_Frete_Editado(String frete_Editado) {
	    VL_Frete_Editado = frete_Editado;
	}
	public String getVL_Mercadoria_Editado() {
	    return VL_Mercadoria_Editado;
	}
	public void setVL_Mercadoria_Editado(String mercadoria_Editado) {
	    VL_Mercadoria_Editado = mercadoria_Editado;
	}
	public String getVL_Peso_Cubado_Editado() {
	    return VL_Peso_Cubado_Editado;
	}
	public void setVL_Peso_Cubado_Editado(String peso_Cubado_Editado) {
	    VL_Peso_Cubado_Editado = peso_Cubado_Editado;
	}
	public String getVL_Peso_Editado() {
	    return VL_Peso_Editado;
	}
	public void setVL_Peso_Editado(String peso_Editado) {
	    VL_Peso_Editado = peso_Editado;
	}
	public long getNR_Volumes_Observacao() {
	    return NR_Volumes_Observacao;
	}
	public void setNR_Volumes_Observacao(long volumes_Observacao) {
	    NR_Volumes_Observacao = volumes_Observacao;
	}
    public double getVL_Frete_Peso() {
        return this.VL_Frete_Peso;
    }
    public void setVL_Frete_Peso(double frete_Peso) {
        this.VL_Frete_Peso = frete_Peso;
    }
    public double getVL_Frete_Valor() {
        return this.VL_Frete_Valor;
    }
    public void setVL_Frete_Valor(double frete_Valor) {
        this.VL_Frete_Valor = frete_Valor;
    }
    public double getVL_Outros() {
        return this.VL_Outros;
    }
    public void setVL_Outros(double outros) {
        this.VL_Outros = outros;
    }
    public double getVL_Taxas() {
        return this.VL_Taxas;
    }
    public void setVL_Taxas(double taxas) {
        this.VL_Taxas = taxas;
    }
    public double getVL_Total_Custo() {
        return this.VL_Total_Custo;
    }
    public void setVL_Total_Custo(double total_Custo) {
        this.VL_Total_Custo = total_Custo;
    }
    public double getVL_Total_Frete() {
        return this.VL_Total_Frete;
    }
    public void setVL_Total_Frete(double total_Frete) {
        this.VL_Total_Frete = total_Frete;
    }
    public String getNR_CPF_CNPJ_Consignatario_Complementar() {
        return NR_CPF_CNPJ_Consignatario_Complementar;
    }
    public void setNR_CPF_CNPJ_Consignatario_Complementar(
            String consignatario_Complementar) {
        NR_CPF_CNPJ_Consignatario_Complementar = consignatario_Complementar;
    }
    public String getNR_CPF_CNPJ_Notificar_Complementar() {
        return NR_CPF_CNPJ_Notificar_Complementar;
    }
    public void setNR_CPF_CNPJ_Notificar_Complementar(
            String notificar_Complementar) {
        NR_CPF_CNPJ_Notificar_Complementar = notificar_Complementar;
    }
    
	public String getNM_Icoterm() {
	    return NM_Icoterm;
	}
	public void setNM_Icoterm(String icoterm) {
	    NM_Icoterm = icoterm;
	}
    public String getDT_Fim() {
        return DT_Fim;
    }
    public void setDT_Fim(String fim) {
        DT_Fim = fim;
    }
    public String getDM_Moeda1() {
        return DM_Moeda1;
    }
    public void setDM_Moeda1(String moeda1) {
        DM_Moeda1 = moeda1;
    }
    public String getDM_Moeda2() {
        return DM_Moeda2;
    }
    public void setDM_Moeda2(String moeda2) {
        DM_Moeda2 = moeda2;
    }
    public String getDM_Moeda3() {
        return DM_Moeda3;
    }
    public void setDM_Moeda3(String moeda3) {
        DM_Moeda3 = moeda3;
    }
    public String getDM_Moeda4() {
        return DM_Moeda4;
    }
    public void setDM_Moeda4(String moeda4) {
        DM_Moeda4 = moeda4;
    }
    public String getDM_Moeda5() {
        return DM_Moeda5;
    }
    public void setDM_Moeda5(String moeda5) {
        DM_Moeda5 = moeda5;
    }
    public String getDM_Moeda6() {
        return DM_Moeda6;
    }
    public void setDM_Moeda6(String moeda6) {
        DM_Moeda6 = moeda6;
    }
    public String getDM_Moeda7() {
        return DM_Moeda7;
    }
    public void setDM_Moeda7(String moeda7) {
        DM_Moeda7 = moeda7;
    }
    public String getDM_Moeda8() {
        return DM_Moeda8;
    }
    public void setDM_Moeda8(String moeda8) {
        DM_Moeda8 = moeda8;
    }
    
    public String getDM_Moeda9() {
        return DM_Moeda9;
    }
    public void setDM_Moeda9(String moeda9) {
        DM_Moeda9 = moeda9;
    }
    public String getDM_Moeda10() {
        return DM_Moeda10;
    }
    public void setDM_Moeda10(String moeda10) {
        DM_Moeda10 = moeda10;
    }
	public double getC15_VL_Ad_Valorem() {
	    return c15_VL_Ad_Valorem;
	}
	public void setC15_VL_Ad_Valorem(double ad_Valorem) {
	    c15_VL_Ad_Valorem = ad_Valorem;
	}
	public double getC15_VL_Frete_Peso() {
	    return c15_VL_Frete_Peso;
	}
	public void setC15_VL_Frete_Peso(double frete_Peso) {
	    c15_VL_Frete_Peso = frete_Peso;
	}
	public double getC15_VL_Outros() {
	    return c15_VL_Outros;
	}
	public void setC15_VL_Outros(double outros) {
	    c15_VL_Outros = outros;
	}
	public double getC15_VL_Taxas() {
	    return c15_VL_Taxas;
	}
	public void setC15_VL_Taxas(double taxas) {
	    c15_VL_Taxas = taxas;
	}
	public double getC15_VL_Total() {
	    return c15_VL_Total;
	}
	public void setC15_VL_Total(double total) {
	    c15_VL_Total = total;
	}
	
	public String getDM_Veiculo_Novo() {
	    return DM_Veiculo_Novo;
	}
	public void setDM_Veiculo_Novo(String veiculo_Novo) {
	    DM_Veiculo_Novo = veiculo_Novo;
	}
	public double getVL_Desconto_RCTRC() {
	    return VL_Desconto_RCTRC;
	}
	public void setVL_Desconto_RCTRC(double desconto_RCTRC) {
	    VL_Desconto_RCTRC = desconto_RCTRC;
	}
	public double getVL_RCTR_DC() {
	    return VL_RCTR_DC;
	}
	public void setVL_RCTR_DC(double vl_rctr_dc) {
	    VL_RCTR_DC = vl_rctr_dc;
	}
	public double getVL_RCTR_VI() {
	    return VL_RCTR_VI;
	}
	public void setVL_RCTR_VI(double vl_rctr_vi) {
	    VL_RCTR_VI = vl_rctr_vi;
	}
	public double getVL_RCTRC() {
	    return VL_RCTRC;
	}
	public void setVL_RCTRC(double vl_rctrc) {
	    VL_RCTRC = vl_rctrc;
	}
    public long getOid_Destino() {
        return oid_Destino;
    }
    public void setOid_Destino(long oid_Destino) {
        this.oid_Destino = oid_Destino;
    }
    public long getOid_Origem() {
        return oid_Origem;
    }
    public void setOid_Origem(long oid_Origem) {
        this.oid_Origem = oid_Origem;
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
    public java.util.Collection getCRTDetalhes() {
        return CRTDetalhes;
    }
    public void setCRTDetalhes(java.util.Collection detalhes) {
        CRTDetalhes = detalhes;
    }
    public String getDataRel() {
        return dataRel;
    }
    public void setDataRel(String dataRel) {
        this.dataRel = dataRel;
    }
    public String getSiglaRel() {
        return siglaRel;
    }
    public void setSiglaRel(String siglaRel) {
        this.siglaRel = siglaRel;
    }
    public double getVL_Frete_Merc() {
        return VL_Frete_Merc;
    }
    public void setVL_Frete_Merc(double frete_Merc) {
        VL_Frete_Merc = frete_Merc;
    }
    public String getDT_Cruze() {
        return DT_Cruze;
    }
    public void setDT_Cruze(String cruze) {
        DT_Cruze = cruze;
    }
    public String getDT_Entrega() {
        return DT_Entrega;
    }
    public void setDT_Entrega(String entrega) {
        DT_Entrega = entrega;
    }
    public String getNM_Pessoa_Pagador() {
        return NM_Pessoa_Pagador;
    }
    public void setNM_Pessoa_Pagador(String pessoa_Pagador) {
        NM_Pessoa_Pagador = pessoa_Pagador;
    }
    public long getDias_Fatura() {
        return dias_Fatura;
    }
    public void setDias_Fatura(long dias_Fatura) {
        this.dias_Fatura = dias_Fatura;
    }
    public long getDias_Pgto() {
        return dias_Pgto;
    }
    public void setDias_Pgto(long dias_Pgto) {
        this.dias_Pgto = dias_Pgto;
    }
    public long getDias_Vcto() {
        return dias_Vcto;
    }
    public void setDias_Vcto(long dias_Vcto) {
        this.dias_Vcto = dias_Vcto;
    }
    public String getDT_Fatura() {
        return DT_Fatura;
    }
    public void setDT_Fatura(String fatura) {
        DT_Fatura = fatura;
    }
    public String getDT_Pagamento() {
        return DT_Pagamento;
    }
    public void setDT_Pagamento(String pagamento) {
        DT_Pagamento = pagamento;
    }
    public String getDT_Vencimento() {
        return DT_Vencimento;
    }
    public void setDT_Vencimento(String vencimento) {
        DT_Vencimento = vencimento;
    }
    public String getOID_Vendedor() {
        return OID_Vendedor;
    }
    public void setOID_Vendedor(String vendedor) {
        OID_Vendedor = vendedor;
    }
    public String getNM_Vendedor() {
        return NM_Vendedor;
    }
    public void setNM_Vendedor(String vendedor) {
        NM_Vendedor = vendedor;
    }
    public double getPE_Comissao() {
        return PE_Comissao;
    }
    public void setPE_Comissao(double comissao) {
        PE_Comissao = comissao;
    }
    public double getVL_Comissao() {
        return VL_Comissao;
    }
    public void setVL_Comissao(double comissao) {
        VL_Comissao = comissao;
    }
    public String getDM_Imprime1() {
        return DM_Imprime1;
    }
    public void setDM_Imprime1(String imprime1) {
        DM_Imprime1 = imprime1;
    }
    public String getDM_Imprime2() {
        return DM_Imprime2;
    }
    public void setDM_Imprime2(String imprime2) {
        DM_Imprime2 = imprime2;
    }
    public String getDM_Imprime3() {
        return DM_Imprime3;
    }
    public void setDM_Imprime3(String imprime3) {
        DM_Imprime3 = imprime3;
    }
    public String getDM_ImprimeCP() {
        return DM_ImprimeCP;
    }
    public void setDM_ImprimeCP(String imprimeCP) {
        DM_ImprimeCP = imprimeCP;
    }
    public long getNR_Conhecimento_Final() {
        return NR_Conhecimento_Final;
    }
    public void setNR_Conhecimento_Final(long conhecimento_Final) {
        NR_Conhecimento_Final = conhecimento_Final;
    }
    public java.util.Collection getFaturas() {
        return Faturas;
    }
    public void setFaturas(java.util.Collection faturas) {
        Faturas = faturas;
    }
    /**
     * @return Returns the nM_Manifesto.
     */
    public String getNM_Manifesto() {
        return NM_Manifesto;
    }
    /**
     * @param manifesto The nM_Manifesto to set.
     */
    public void setNM_Manifesto(String manifesto) {
        NM_Manifesto = manifesto;
    }
    /**
     * @return Returns the vL_Despesa.
     */
    public double getVL_Despesa() {
        return VL_Despesa;
    }
    /**
     * @param despesa The vL_Despesa to set.
     */
    public void setVL_Despesa(double despesa) {
        VL_Despesa = despesa;
    }
    /**
     * @return Returns the vL_Receita.
     */
    public double getVL_Receita() {
        return VL_Receita;
    }
    /**
     * @param receita The vL_Receita to set.
     */
    public void setVL_Receita(double receita) {
        VL_Receita = receita;
    }
    /**
     * @return Returns the nR_KM_Rodado.
     */
    public double getNR_KM_Rodado() {
        return NR_KM_Rodado;
    }
    /**
     * @param rodado The nR_KM_Rodado to set.
     */
    public void setNR_KM_Rodado(double rodado) {
        NR_KM_Rodado = rodado;
    }
    /**
     * @return Returns the vL_Rota.
     */
    public double getVL_Rota() {
        return VL_Rota;
    }
    /**
     * @param rota The vL_Rota to set.
     */
    public void setVL_Rota(double rota) {
        VL_Rota = rota;
    }
    public String getNM_Roteiro() {
        return NM_Roteiro;
    }
    public void setNM_Roteiro(String roteiro) {
        NM_Roteiro = roteiro;
    }
    public String getNR_Placa() {
        return NR_Placa;
    }
    public void setNR_Placa(String placa) {
        NR_Placa = placa;
    }
    public String getOID_Veiculo() {
        return OID_Veiculo;
    }
    public void setOID_Veiculo(String veiculo) {
        OID_Veiculo = veiculo;
    }
    public String getCD_Roteiro() {
        return CD_Roteiro;
    }
    public void setCD_Roteiro(String roteiro) {
        CD_Roteiro = roteiro;
    }
    public double getPE_Exportador() {
        return PE_Exportador;
    }
    public void setPE_Exportador(double exportador) {
        PE_Exportador = exportador;
    }
	public String getCD_Unidade_Destino() {
		return CD_Unidade_Destino;
	}
	public void setCD_Unidade_Destino(String unidade_Destino) {
		CD_Unidade_Destino = unidade_Destino;
	}
	public String getCD_Unidade_Fronteira() {
		return CD_Unidade_Fronteira;
	}
	public void setCD_Unidade_Fronteira(String unidade_Fronteira) {
		CD_Unidade_Fronteira = unidade_Fronteira;
	}
	public String getCD_Unidade_Origem() {
		return CD_Unidade_Origem;
	}
	public void setCD_Unidade_Origem(String unidade_Origem) {
		CD_Unidade_Origem = unidade_Origem;
	}
	public String getNM_Fantasia_Destino() {
		return NM_Fantasia_Destino;
	}
	public void setNM_Fantasia_Destino(String fantasia_Destino) {
		NM_Fantasia_Destino = fantasia_Destino;
	}
	public String getNM_Fantasia_Fronteira() {
		return NM_Fantasia_Fronteira;
	}
	public void setNM_Fantasia_Fronteira(String fantasia_Fronteira) {
		NM_Fantasia_Fronteira = fantasia_Fronteira;
	}
	public String getNM_Fantasia_Origem() {
		return NM_Fantasia_Origem;
	}
	public void setNM_Fantasia_Origem(String fantasia_Origem) {
		NM_Fantasia_Origem = fantasia_Origem;
	}
	public long getNR_Coleta() {
		return NR_Coleta;
	}
	public void setNR_Coleta(long coleta) {
		NR_Coleta = coleta;
	}
}
