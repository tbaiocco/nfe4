package com.master.ed;

import com.master.util.JavaUtil;

public class PessoaED extends MasterED {

    public PessoaED() { 
        CD_Cidade = "";
        CD_Estado = "";
        Dm_Situacao = "";
        Dm_Stamp = "";
        Dt_Stamp = "";
        EMail = "";
        NM_Bairro = "";
        NM_Cidade = "";
        NM_Endereco = "";
        NM_Fantasia = "";
        NM_Inscricao_Estadual = "";
        NM_Razao_Social = "";
        NR_CEP = "";
        NR_CNPJ_CPF = "";
        NR_Telefone = "";
        Oid_Cidade = 0;
        oid_Pessoa = "";
        Usuario_Stamp = "";
    }

    public PessoaED(String oid_Pessoa) {
        super();
        this.oid_Pessoa = oid_Pessoa;
    }

    private String NR_CNPJ_CPF;
    private String NM_Razao_Social;
    private String NM_Fantasia;
    private String NM_Endereco;
    private String NM_Bairro;
    private String NR_CEP;
    private String NM_Inscricao_Estadual;
    private long Oid_Cidade;
    private String NM_Cidade;
    private String CD_Cidade;
    private String Usuario_Stamp;
    private String Dt_Stamp;
    private String Dm_Stamp;
    private String oid_Pessoa;
    private String Dm_Situacao;
    private String CD_Estado;
    private String NR_Telefone;
    private String EMail;
    private long oid_Empresa;

    public static String getNR_CNPJ_CPFFormatado(String value) {
        if (JavaUtil.doValida(value))
            return value.length() > 11 ? JavaUtil.formatar(value, "##.###.###/####-##") : JavaUtil.formatar(value, "###.###.###-##");
        else return value;
    }
    public String getNR_CNPJ_CPFFormatado() {
       return getNR_CNPJ_CPFFormatado(NR_CNPJ_CPF);
    }
    public static String getInscEstadualFormatada(String value) {
        if (JavaUtil.doValida(value))
            return JavaUtil.formatar(value, "###/#######");
        else return value;
    }
    public String getInscEstadualFormatada() {
       return getInscEstadualFormatada(NM_Inscricao_Estadual);
    }
    
    public static String getDescSEFAZ(String value) {
        return "D".equals(value) ? "INSCRIÇÃO DIFERENTE" : "N".equals(value) ? "INSCRIÇÃO NÃO CADASTRADA" : "Não Verificada!";
    }

    public String getNR_Telefone() {
        return NR_Telefone;
    }

    public void setNR_Telefone(String telefone) {
        NR_Telefone = telefone;
    }

    public String getDm_Situacao() {
        return Dm_Situacao;
    }

    public String getDm_Stamp() {
        return Dm_Stamp;
    }

    public String getDt_Stamp() {
        return Dt_Stamp;
    }

    public String getNM_Bairro() {
        return NM_Bairro;
    }

    public String getNM_Endereco() {
        return NM_Endereco;
    }

    public String getNM_Fantasia() {
        return NM_Fantasia;
    }

    public String getNM_Inscricao_Estadual() {
        return NM_Inscricao_Estadual;
    }

    public String getNM_Razao_Social() {
        return NM_Razao_Social;
    }

    public String getNR_CEP() {
        return NR_CEP;
    }

    public String getNR_CNPJ_CPF() {
        return NR_CNPJ_CPF;
    }
    
    public long getOid_Cidade() {
        return Oid_Cidade;
    }

    public String getOid_Pessoa() {
        return oid_Pessoa;
    }

    public String getUsuario_Stamp() {
        return Usuario_Stamp;
    }

    public void setUsuario_Stamp(String Usuario_Stamp) {
        this.Usuario_Stamp = Usuario_Stamp;
    }

    public void setOid_Pessoa(String oid_Pessoa) {
        this.oid_Pessoa = oid_Pessoa;
    }

    public void setOid_Cidade(long Oid_Cidade) {
        this.Oid_Cidade = Oid_Cidade;
    }

    public void setNR_CNPJ_CPF(String NR_CNPJ_CPF) {
        this.NR_CNPJ_CPF = NR_CNPJ_CPF;
    }

    public void setDm_Situacao(String Dm_Situacao) {
        this.Dm_Situacao = Dm_Situacao;
    }

    public void setDm_Stamp(String Dm_Stamp) {
        this.Dm_Stamp = Dm_Stamp;
    }

    public void setDt_Stamp(String Dt_Stamp) {
        this.Dt_Stamp = Dt_Stamp;
    }

    public void setNM_Bairro(String NM_Bairro) {
        this.NM_Bairro = NM_Bairro;
    }

    public void setNM_Endereco(String NM_Endereco) {
        this.NM_Endereco = NM_Endereco;
    }

    public void setNM_Fantasia(String NM_Fantasia) {
        this.NM_Fantasia = NM_Fantasia;
    }

    public void setNM_Inscricao_Estadual(String NM_Inscricao_Estadual) {
        this.NM_Inscricao_Estadual = NM_Inscricao_Estadual;
    }

    public void setNM_Razao_Social(String NM_Razao_Social) {
        this.NM_Razao_Social = NM_Razao_Social;
    }

    public void setNR_CEP(String NR_CEP) {
        this.NR_CEP = NR_CEP;
    }

    public void setNM_Cidade(String NM_Cidade) {
        this.NM_Cidade = NM_Cidade;
    }

    public String getNM_Cidade() {
        return NM_Cidade;
    }

    public void setCD_Estado(String CD_Estado) {
        this.CD_Estado = CD_Estado;
    }

    public String getCD_Estado() {
        return CD_Estado;
    }

    public String getEMail() {
        return EMail;
    }
    public void setEMail(String mail) {
        EMail = mail;
    }
    public String getCD_Cidade() {
        return this.CD_Cidade;
    }
    public void setCD_Cidade(String cidade) {
        this.CD_Cidade = cidade;
    }

	public long getOid_Empresa() {
		return oid_Empresa;
	}

	public void setOid_Empresa(long oid_Empresa) {
		this.oid_Empresa = oid_Empresa;
	}
}