package com.master.ed;

public class WMS_Tipo_MovimentoED extends MasterED {

    public WMS_Tipo_MovimentoED() {
        super();
    }
    public WMS_Tipo_MovimentoED(int oid_Tipo_Movimento_Produto) {
        Oid_Tipo_Movimento_Produto = oid_Tipo_Movimento_Produto;
    }
    
    private int Oid_Tipo_Movimento_Produto;
    private String nm_Descricao;
    private String dm_Origem;
    private String dm_Destino;
    private String dm_Situacao;
    private String dm_NF;
    private String dm_tipo_movimento;

    public String getDescDM_Tipo_Movimento() {
        if ("E".equals(dm_tipo_movimento))
            return "Entrada";
        else if ("S".equals(dm_tipo_movimento))
            return "Saída";
        else if ("AE".equals(dm_tipo_movimento))
            return "Ajuste Entrada";
        else if ("AS".equals(dm_tipo_movimento))
            return "Ajuste Saída";
        else if ("T".equals(dm_tipo_movimento))
            return "Transferência";
        else if ("D".equals(dm_tipo_movimento))
            return "Devolução";
        else return "Não encontrado!";
    }
    
    public String getDm_Destino() {
        return dm_Destino;
    }
    public String getDm_NF() {
        return dm_NF;
    }
    public String getDm_Origem() {
        return dm_Origem;
    }
    public String getDm_Situacao() {
        return dm_Situacao;
    }
    public String getNm_Descricao() {
        return nm_Descricao;
    }
    public int getOid_Tipo_Movimento_Produto() {
        return Oid_Tipo_Movimento_Produto;
    }
    public void setDm_Destino(String dm_Destino) {
        this.dm_Destino = dm_Destino;
    }
    public void setDm_NF(String dm_NF) {
        this.dm_NF = dm_NF;
    }
    public void setDm_Origem(String dm_Origem) {
        this.dm_Origem = dm_Origem;
    }
    public void setDm_Situacao(String dm_Situacao) {
        this.dm_Situacao = dm_Situacao;
    }
    public void setNm_Descricao(String nm_Descricao) {
        this.nm_Descricao = nm_Descricao;
    }
    public void setOid_Tipo_Movimento_Produto(int Oid_Tipo_Movimento_Produto) {
        this.Oid_Tipo_Movimento_Produto = Oid_Tipo_Movimento_Produto;
    }
    public String getDm_tipo_movimento() {
        return dm_tipo_movimento;
    }
    public void setDm_tipo_movimento(String dm_tipo_movimento) {
        this.dm_tipo_movimento = dm_tipo_movimento;
    }
}