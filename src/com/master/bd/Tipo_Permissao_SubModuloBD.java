/*
 * Created on 25/02/2005
 */
package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import com.master.ed.Tipo_Permissao_SubModuloED;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.Mensagens;
import com.master.util.bd.ExecutaSQL;

/**
 * @author Andr� Valadas
 */
public class Tipo_Permissao_SubModuloBD extends BancoUtil{

    private ExecutaSQL executasql;

    public Tipo_Permissao_SubModuloBD(ExecutaSQL sql) {
        this.executasql = sql;
    }

    public Tipo_Permissao_SubModuloED inclui(Tipo_Permissao_SubModuloED ed) throws Excecoes {

        String sql = null;

        try {
            ed.setOid_Tipo_Permissao_SubModulo(getAutoIncremento("oid_Tipo_Permissao_SubModulo", "Tipos_Permissoes_SubModulos"));
                
            sql = " INSERT INTO Tipos_Permissoes_SubModulos (" +
            	  "		oid_Tipo_Permissao_SubModulo" +
            	  "		,oid_SubModulo_Permissao" +
            	  "		,DM_Tipo_Permissao" +
            	  "		,NM_Tipo_Permissao) " +
            	  " VALUES (" +
            	  	ed.getOid_Tipo_Permissao_SubModulo() +
            	  	"," + ed.getOid_SubModulo_Permissao() +
            	  	",'" + ed.getDM_Tipo_Permissao() + "'" +
            	  	",'" + ed.getNM_Tipo_Permissao() + "')";
                
            executasql.executarUpdate(sql);
        	return ed;
        	
        }catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "inclui()");
        }
    }

    public void altera(Tipo_Permissao_SubModuloED ed) throws Excecoes {

        String sql = null;

        try {
            sql =  " UPDATE Tipos_Permissoes_SubModulos SET ";
            sql += " 	DM_Tipo_Permissao = '"+ed.getDM_Tipo_Permissao()+"'" +
            	   "   ,NM_Tipo_Permissao = '"+ed.getNM_Tipo_Permissao()+"'";
            sql += " WHERE oid_Tipo_Permissao_SubModulo = " + ed.getOid_Tipo_Permissao_SubModulo();
            
            executasql.executarUpdate(sql);
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "altera()");
        }
    }

    public void deleta(Tipo_Permissao_SubModuloED ed) throws Excecoes {

        String sql = null;
        try {
            sql = " DELETE FROM Tipos_Permissoes_SubModulos " +
            	  " WHERE oid_Tipo_Permissao_SubModulo = " + ed.getOid_Tipo_Permissao_SubModulo();
            
            executasql.executarUpdate(sql);
        
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "deleta()");
        }
    }
    
    public ArrayList lista(Tipo_Permissao_SubModuloED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();

        try {

            sql = " SELECT * " +
            	  " FROM Tipos_Permissoes_SubModulos " +
            	  " WHERE oid_SubModulo_Permissao = SubModulos_Permissoes.oid_SubModulo_Permissao";
            //** FILTROS
            if (ed.getOid_Tipo_Permissao_SubModulo() > 0)
                sql += "	AND oid_Tipo_Permissao_SubModulo = "+ed.getOid_Tipo_Permissao_SubModulo();
            if (ed.getOid_SubModulo_Permissao() > 0)
                sql += "	AND oid_SubModulo_Permissao = "+ed.getOid_SubModulo_Permissao();
            if (doValida(ed.getDM_Tipo_Permissao()))
                sql += "	AND DM_Tipo_Permissao = '"+ed.getDM_Tipo_Permissao()+"'";
            sql += " ORDER BY DM_Tipo_Permissao";

            ResultSet res = this.executasql.executarConsulta(sql);

            //popula
            while (res.next()) {
            	
                Tipo_Permissao_SubModuloED edVolta = new Tipo_Permissao_SubModuloED();
          
                edVolta.setOid_Tipo_Permissao_SubModulo(res.getInt("oid_Tipo_Permissao_SubModulo"));
                edVolta.setOid_SubModulo_Permissao(res.getInt("oid_SubModulo_Permissao"));
                edVolta.setDM_Tipo_Permissao(res.getString("DM_Tipo_Permissao"));
                edVolta.setNM_Tipo_Permissao(res.getString("NM_Tipo_Permissao"));
                list.add(edVolta);
            }
            return list;
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "lista()");
        }
    }
        
    public Tipo_Permissao_SubModuloED getByRecord(Tipo_Permissao_SubModuloED ed) throws Excecoes {

        Iterator iterator = this.lista(ed).iterator();
        if (iterator.hasNext()) {
            return (Tipo_Permissao_SubModuloED) iterator.next();
        } else throw new Mensagens("Registro n�o encontrado!");
    }
    
    //*** Lista com Permiss�es Basicas cadastardas em "PermissaoBase"
    public ArrayList listaPermissoesBase() throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();

        try {

            return list;
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "listaPermissoesBase()");
        }
    }
}