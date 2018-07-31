package com.master.ed;
/**
 * @author André Valadas
 */
public class Usuario_SubModuloED {

    /** Cadastro dos submodulos que o usuario tem permissao ou não de acessar */
    public Usuario_SubModuloED() {
        super();
    }
    public Usuario_SubModuloED(int oid_Usuario_SubModulo) {
        this.oid_Usuario_SubModulo = oid_Usuario_SubModulo;
    }  
    
    private int oid_Usuario_SubModulo;
	private int oid_Usuario;
	private int oid_SubModulo_Permissao;
	
	//*** Objetos de Dados
	public UsuarioED UsuarioED = new UsuarioED();
	public SubModulo_PermissaoED SubModulo_PermissaoED = new SubModulo_PermissaoED();
	
    public int getOid_SubModulo_Permissao() {
        return oid_SubModulo_Permissao;
    }
    public int getOid_Usuario() {
        return oid_Usuario;
    }
    public int getOid_Usuario_SubModulo() {
        return oid_Usuario_SubModulo;
    }
    public void setOid_SubModulo_Permissao(int oid_SubModulo_Permissao) {
        this.oid_SubModulo_Permissao = oid_SubModulo_Permissao;
    }
    public void setOid_Usuario(int oid_Usuario) {
        this.oid_Usuario = oid_Usuario;
    }
    public void setOid_Usuario_SubModulo(int oid_Usuario_SubModulo) {
        this.oid_Usuario_SubModulo = oid_Usuario_SubModulo;
    }
}