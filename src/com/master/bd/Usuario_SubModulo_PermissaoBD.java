/*
 * Created on 25/02/2005
 */
package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import com.master.ed.Tipo_Permissao_SubModuloED;
import com.master.ed.Usuario_SubModuloED;
import com.master.ed.Usuario_SubModulo_PermissaoED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.Mensagens;
import com.master.util.bd.ExecutaSQL;

/**
 * @author André Valadas
 */
public class Usuario_SubModulo_PermissaoBD extends BancoUtil{

    private ExecutaSQL executasql;

    public Usuario_SubModulo_PermissaoBD(ExecutaSQL sql) {
        this.executasql = sql;
    }

    public Usuario_SubModulo_PermissaoED inclui(Usuario_SubModulo_PermissaoED ed) throws Excecoes {

        String sql = null;

        try {            
            ed.setOid_Usuario_SubModulo_Permissao(getAutoIncremento("oid_Usuario_SubModulo_Permissao", "Usuarios_SubModulos_Permissoes"));  
            
            sql = " INSERT INTO Usuarios_SubModulos_Permissoes (" +
            	  "		 oid_Usuario_SubModulo_Permissao" +
            	  "		,oid_Usuario_SubModulo" +
            	  "		,oid_Tipo_Permissao_SubModulo) " +
            	  " VALUES (" +
            	  	ed.getOid_Usuario_SubModulo_Permissao() +
            	  	"," + ed.getOid_Usuario_SubModulo() +
            	  	"," + ed.getOid_Tipo_Permissao_SubModulo() +")";
                
            executasql.executarUpdate(sql);
        	return ed;
        	
        }catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "inclui()");
        }
    }

    public void altera(Usuario_SubModulo_PermissaoED ed) throws Excecoes {

        String sql = null;

        try {
            sql =  " UPDATE Usuarios_SubModulos_Permissoes SET ";
            sql += " 	oid_Tipo_Permissao_SubModulo = "+ed.getOid_Tipo_Permissao_SubModulo();
            sql += " WHERE oid_Usuario_SubModulo_Permissao = " + ed.getOid_Usuario_SubModulo_Permissao();
            
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "altera()");
        }
    }

    public void deleta(Usuario_SubModulo_PermissaoED ed) throws Excecoes {

        String sql = null;
        try {
            sql = " DELETE FROM Usuarios_SubModulos_Permissoes " +
            	  " WHERE oid_Usuario_SubModulo_Permissao = " + ed.getOid_Usuario_SubModulo_Permissao();
            
            executasql.executarUpdate(sql);
        
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "deleta()");
        }
    }
    
    public ArrayList lista(Usuario_SubModulo_PermissaoED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();

        try {

            sql = " SELECT * " +
            	  " FROM Usuarios_SubModulos_Permissoes" +
            	  " WHERE Usuarios_SubModulos_Permissoes.oid_Usuario_SubModulo = Usuarios_SubModulos.oid_Usuario_SubModulo" +
            	  "   AND Usuarios_SubModulos_Permissoes.oid_Tipo_Permissao_SubModulo = Tipos_Permissoes_SubModulos.oid_Tipo_Permissao_SubModulo";
            //** FILTROS
            if (ed.getOid_Usuario_SubModulo_Permissao() > 0)
                sql += "   AND Usuarios_SubModulos_Permissoes.oid_Usuario_SubModulo_Permissao = "+ed.getOid_Usuario_SubModulo_Permissao();
            if (ed.getOid_Usuario_SubModulo() > 0)
                sql += "   AND Usuarios_SubModulos_Permissoes.oid_Usuario_SubModulo = "+ed.getOid_Usuario_SubModulo();
            if (ed.getOid_Tipo_Permissao_SubModulo() > 0)
                sql += "   AND Usuarios_SubModulos_Permissoes.oid_Tipo_Permissao_SubModulo = "+ed.getOid_Tipo_Permissao_SubModulo();
            //*** Usuário SubMódulos
            if (ed.Usuario_SubModuloED.getOid_Usuario() > 0)
                sql += "	AND Usuarios_SubModulos.oid_Usuario = "+ed.Usuario_SubModuloED.getOid_Usuario();
            sql += " ORDER BY Tipos_Permissoes_SubModulos.DM_Tipo_Permissao";

            ResultSet res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
            	
                Usuario_SubModulo_PermissaoED edVolta = new Usuario_SubModulo_PermissaoED();
          
                edVolta.setOid_Usuario_SubModulo_Permissao(res.getInt("oid_Usuario_SubModulo_Permissao"));
                edVolta.setOid_Usuario_SubModulo(res.getInt("oid_Usuario_SubModulo"));
                edVolta.setOid_Tipo_Permissao_SubModulo(res.getInt("oid_Tipo_Permissao_SubModulo"));
                //*** Dados do Usuário SubMódulo
                if (edVolta.getOid_Usuario_SubModulo() > 0) {
                    edVolta.Usuario_SubModuloED = new Usuario_SubModuloBD(executasql).getByRecord(new Usuario_SubModuloED(edVolta.getOid_Usuario_SubModulo()));
                }
                //*** Dados do Tipo de Permissão
                if (edVolta.getOid_Tipo_Permissao_SubModulo() > 0) {
                    edVolta.Tipo_Permissao_SubModuloED = new Tipo_Permissao_SubModuloBD(executasql).getByRecord(new Tipo_Permissao_SubModuloED(edVolta.getOid_Tipo_Permissao_SubModulo()));
                }
                list.add(edVolta);
            }
            return list;
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "lista()");
        }
    }
        
    public Usuario_SubModulo_PermissaoED getByRecord(Usuario_SubModulo_PermissaoED ed) throws Excecoes {

        Iterator iterator = this.lista(ed).iterator();
        if (iterator.hasNext()) {
            return (Usuario_SubModulo_PermissaoED) iterator.next();
        } else throw new Mensagens("Registro não encontrado!");
    }
}