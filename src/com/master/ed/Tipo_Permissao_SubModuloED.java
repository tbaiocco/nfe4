package com.master.ed;

/**
 * @author André Valadas
 * USADO SOMENTE PELOS PROGRAMADORES
 */
public class Tipo_Permissao_SubModuloED {

    /*** Permissões para cada SubMódulo(TELA) */
    public Tipo_Permissao_SubModuloED() {
        super();
    }
    public Tipo_Permissao_SubModuloED(int oid_Tipo_Permissao_SubModulo) {
        this.oid_Tipo_Permissao_SubModulo = oid_Tipo_Permissao_SubModulo;
    }
    public Tipo_Permissao_SubModuloED(int oid_Tipo_Permissao_SubModulo, int oid_SubModulo_Permissao) {
        this.oid_Tipo_Permissao_SubModulo = oid_Tipo_Permissao_SubModulo;
        this.oid_SubModulo_Permissao = oid_SubModulo_Permissao;
    }
    public Tipo_Permissao_SubModuloED(String dm_tipo_permissao, String nm_tipo_permissao) {
        DM_Tipo_Permissao = dm_tipo_permissao;
        NM_Tipo_Permissao = nm_tipo_permissao;
    }
    
	private int oid_Tipo_Permissao_SubModulo;
	private int oid_SubModulo_Permissao;
	private String DM_Tipo_Permissao; // 1, 2, 3, etc...
	private String NM_Tipo_Permissao; // 1=INCLUIR, 2=ALTERAR, 3=EXCLUIR, etc...

    public String getDM_Tipo_Permissao() {
        return DM_Tipo_Permissao;
    }
    public String getNM_Tipo_Permissao() {
        return NM_Tipo_Permissao;
    }
    public int getOid_SubModulo_Permissao() {
        return oid_SubModulo_Permissao;
    }
    public int getOid_Tipo_Permissao_SubModulo() {
        return oid_Tipo_Permissao_SubModulo;
    }
    public void setDM_Tipo_Permissao(String tipo_Permissao) {
        DM_Tipo_Permissao = tipo_Permissao;
    }
    public void setNM_Tipo_Permissao(String tipo_Permissao) {
        NM_Tipo_Permissao = tipo_Permissao;
    }
    public void setOid_SubModulo_Permissao(int oid_SubModulo_Permissao) {
        this.oid_SubModulo_Permissao = oid_SubModulo_Permissao;
    }
    public void setOid_Tipo_Permissao_SubModulo(int oid_Tipo_Permissao_SubModulo) {
        this.oid_Tipo_Permissao_SubModulo = oid_Tipo_Permissao_SubModulo;
    }
}