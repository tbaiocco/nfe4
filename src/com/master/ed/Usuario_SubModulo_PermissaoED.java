package com.master.ed;
/**
 * @author André Valadas
 */
public class Usuario_SubModulo_PermissaoED {

    /** Tipos de permissões que o usuário tem no módulo */
    public Usuario_SubModulo_PermissaoED() {
        super();
    }
    public Usuario_SubModulo_PermissaoED(int oid_Usuario_SubModulo_Permissao) {
        this.oid_Usuario_SubModulo_Permissao = oid_Usuario_SubModulo_Permissao;
    }  
    
    private int oid_Usuario_SubModulo_Permissao;
    private int oid_Usuario_SubModulo;
	private int oid_Tipo_Permissao_SubModulo;
	
	//*** Objetos de Dados
	public Usuario_SubModuloED Usuario_SubModuloED = new Usuario_SubModuloED();
	public Tipo_Permissao_SubModuloED Tipo_Permissao_SubModuloED = new Tipo_Permissao_SubModuloED();
	
    public int getOid_Tipo_Permissao_SubModulo() {
        return oid_Tipo_Permissao_SubModulo;
    }
    public int getOid_Usuario_SubModulo() {
        return oid_Usuario_SubModulo;
    }
    public int getOid_Usuario_SubModulo_Permissao() {
        return oid_Usuario_SubModulo_Permissao;
    }
    public void setOid_Tipo_Permissao_SubModulo(int oid_Tipo_Permissao_SubModulo) {
        this.oid_Tipo_Permissao_SubModulo = oid_Tipo_Permissao_SubModulo;
    }
    public void setOid_Usuario_SubModulo(int oid_Usuario_SubModulo) {
        this.oid_Usuario_SubModulo = oid_Usuario_SubModulo;
    }
    public void setOid_Usuario_SubModulo_Permissao(int oid_Usuario_SubModulo_Permissao) {
        this.oid_Usuario_SubModulo_Permissao = oid_Usuario_SubModulo_Permissao;
    }
}