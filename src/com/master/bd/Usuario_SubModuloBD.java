/*
 * Created on 25/02/2005
 */
package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import com.master.ed.SubModulo_PermissaoED;
import com.master.ed.UsuarioED;
import com.master.ed.Usuario_SubModuloED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.Mensagens;
import com.master.util.bd.ExecutaSQL;

/**
 * @author André Valadas
 */
public class Usuario_SubModuloBD extends BancoUtil{

    private ExecutaSQL executasql;

    public Usuario_SubModuloBD(ExecutaSQL sql) {
        this.executasql = sql;
    }

    public Usuario_SubModuloED inclui(Usuario_SubModuloED ed) throws Excecoes {

        String sql = null;

        try {            
            ed.setOid_Usuario_SubModulo(getAutoIncremento("oid_Usuario_SubModulo", "Usuarios_SubModulos"));  
            
            sql = " INSERT INTO Usuarios_SubModulos (" +
            	  "		 oid_Usuario_SubModulo" +
            	  "		,oid_Usuario" +
            	  "		,oid_SubModulo_Permissao) " +
            	  " VALUES (" +
            	  	ed.getOid_Usuario_SubModulo() +
            	  	"," + ed.getOid_Usuario() +
            	  	"," + ed.getOid_SubModulo_Permissao() +")";
                
            executasql.executarUpdate(sql);
        	return ed;
        	
        }catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "inclui()");
        }
    }

    public void altera(Usuario_SubModuloED ed) throws Excecoes {

        String sql = null;

        try {
            sql =  " UPDATE Usuarios_SubModulos SET ";
            sql += " 	oid_SubModulo_Permissao = "+ed.getOid_SubModulo_Permissao();
            sql += " WHERE oid_Usuario_SubModulo = " + ed.getOid_Usuario_SubModulo();
            
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "altera()");
        }
    }

    public void deleta(Usuario_SubModuloED ed) throws Excecoes {

        String sql = null;
        try {
            sql = " DELETE FROM Usuarios_SubModulos " +
            	  " WHERE oid_Usuario_SubModulo = " + ed.getOid_Usuario_SubModulo();
            
            executasql.executarUpdate(sql);
        
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "deleta()");
        }
    }
    
    public ArrayList lista(Usuario_SubModuloED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();

        try {

            sql = " SELECT * " +
            	  " FROM Usuarios_SubModulos, SubModulos_Permissoes, Usuarios " +
            	  " WHERE Usuarios_SubModulos.oid_SubModulo_Permissao = SubModulos_Permissoes.oid_SubModulo_Permissao" +
            	  "   AND Usuarios_SubModulos.oid_Usuario = Usuarios.oid_Usuario";
            //** FILTROS
            if (ed.getOid_Usuario_SubModulo() > 0)
                sql += "   AND Usuarios_SubModulos.oid_Usuario_SubModulo = "+ed.getOid_Usuario_SubModulo();
            if (ed.getOid_Usuario() > 0)
                sql += "   AND Usuarios_SubModulos.oid_Usuario = "+ed.getOid_Usuario();
            if (ed.getOid_SubModulo_Permissao() > 0)
                sql += "   AND Usuarios_SubModulos.oid_SubModulo_Permissao = "+ed.getOid_SubModulo_Permissao();
            //*** SubMódulos
            if (doValida(ed.SubModulo_PermissaoED.getCD_Modulo()))
                sql += "	AND SubModulos_Permissoes.CD_Modulo = '"+ed.SubModulo_PermissaoED.getCD_Modulo()+"'";
            sql += " ORDER BY SubModulos_Permissoes.CD_SubModulo, SubModulos_Permissoes.NM_SubModulo";

            ResultSet res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
            	
                Usuario_SubModuloED edVolta = new Usuario_SubModuloED();
          
                edVolta.setOid_Usuario_SubModulo(res.getInt("oid_Usuario_SubModulo"));
                edVolta.setOid_Usuario(res.getInt("oid_Usuario"));
                edVolta.setOid_SubModulo_Permissao(res.getInt("oid_SubModulo_Permissao"));
                //*** Dados do Usuário
                if (edVolta.getOid_Usuario() > 0) {
                    edVolta.UsuarioED = new UsuarioBD(executasql).getByRecord(new UsuarioED(new Integer(edVolta.getOid_Usuario())));
                }
                //*** Dados do SubMódulo Permissão
                if (edVolta.getOid_SubModulo_Permissao() > 0) {
                    edVolta.SubModulo_PermissaoED = new SubModulo_PermissaoBD(executasql).getByRecord(new SubModulo_PermissaoED(edVolta.getOid_SubModulo_Permissao()));
                }
                list.add(edVolta);
            }
            return list;
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "lista()");
        }
    }
        
    public Usuario_SubModuloED getByRecord(Usuario_SubModuloED ed) throws Excecoes {

        Iterator iterator = this.lista(ed).iterator();
        if (iterator.hasNext()) {
            return (Usuario_SubModuloED) iterator.next();
        } else throw new Mensagens("Registro não encontrado!");
    }
}