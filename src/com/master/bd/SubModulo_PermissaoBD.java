/*
 * Created on 25/02/2005
 */
package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import com.master.ed.SubModulo_PermissaoED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.Mensagens;
import com.master.util.bd.ExecutaSQL;

/**
 * @author André Valadas
 */
public class SubModulo_PermissaoBD extends BancoUtil{

    private ExecutaSQL executasql;

    public SubModulo_PermissaoBD(ExecutaSQL sql) {
        this.executasql = sql;
    }

    public SubModulo_PermissaoED inclui(SubModulo_PermissaoED ed) throws Excecoes {

        String sql = null;

        try {            
            ed.setOid_SubModulo_Permissao(getAutoIncremento("oid_SubModulo_Permissao", "SubModulos_Permissoes"));  
            
            sql = " INSERT INTO SubModulos_Permissoes (" +
            	  "		oid_SubModulo_Permissao" +
            	  "		,CD_Modulo" +
            	  "		,CD_SubModulo" +
            	  "		,NM_SubModulo) " +
            	  " VALUES (" +
            	  	ed.getOid_SubModulo_Permissao() +
            	  	",'" + ed.getCD_Modulo() + "'" +
            	  	",'" + ed.getCD_SubModulo() + "'" +
            	  	",'" + ed.getNM_SubModulo() + "')";
                
            executasql.executarUpdate(sql);
        	return ed;
        	
        }catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "inclui()");
        }
    }

    public void altera(SubModulo_PermissaoED ed) throws Excecoes {

        String sql = null;

        try {
            sql =  " UPDATE SubModulos_Permissoes SET ";
            sql += " 	CD_SubModulo = '"+ed.getCD_SubModulo()+"'" +
            	   "   ,NM_SubModulo = '"+ed.getNM_SubModulo()+"'";
            sql += " WHERE oid_SubModulo_Permissao = " + ed.getOid_SubModulo_Permissao();
            
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "altera()");
        }
    }

    public void deleta(SubModulo_PermissaoED ed) throws Excecoes {

        String sql = null;
        try {
            sql = " DELETE FROM SubModulos_Permissoes " +
            	  " WHERE oid_SubModulo_Permissao = " + ed.getOid_SubModulo_Permissao();
            
            executasql.executarUpdate(sql);
        
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "deleta()");
        }
    }
    
    public ArrayList lista(SubModulo_PermissaoED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();

        try {

            sql = " SELECT * " +
            	  " FROM SubModulos_Permissoes " +
            	  " WHERE 1 = 1";
            //** FILTROS
            if (ed.getOid_SubModulo_Permissao() > 0)
                sql += "	AND oid_SubModulo_Permissao = "+ed.getOid_SubModulo_Permissao();
            if (doValida(ed.getCD_Modulo()))
                sql += "	AND CD_Modulo = '"+ed.getCD_Modulo()+"'";
            if (doValida(ed.getCD_SubModulo()))
                sql += "	AND CD_SubModulo = '"+ed.getCD_SubModulo()+"'";
            sql += " ORDER BY CD_SubModulo, NM_SubModulo";

            ResultSet res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
            	
                SubModulo_PermissaoED edVolta = new SubModulo_PermissaoED();
          
                edVolta.setOid_SubModulo_Permissao(res.getInt("oid_SubModulo_Permissao"));
                edVolta.setCD_Modulo(res.getString("CD_Modulo"));
                edVolta.setCD_SubModulo(res.getString("CD_SubModulo"));
                edVolta.setNM_SubModulo(res.getString("NM_SubModulo"));
                list.add(edVolta);
            }
            return list;
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "lista()");
        }
    }
        
    public SubModulo_PermissaoED getByRecord(SubModulo_PermissaoED ed) throws Excecoes {

        Iterator iterator = this.lista(ed).iterator();
        if (iterator.hasNext()) {
            return (SubModulo_PermissaoED) iterator.next();
        } else throw new Mensagens("Registro não encontrado!");
    }
}