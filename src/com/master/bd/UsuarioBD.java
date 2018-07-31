package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.master.ed.UsuarioED;
import com.master.root.UnidadeBean;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.Utilitaria;
import com.master.util.bd.ExecutaSQL;

public class UsuarioBD {

    private ExecutaSQL executasql;
    Utilitaria util = new Utilitaria(executasql);

    public UsuarioBD(ExecutaSQL sql) {
        this.executasql = sql;
        util = new Utilitaria(this.executasql);
    }

    public UsuarioED inclui(UsuarioED ed) throws Excecoes {

        String sql = null;
        try {

            ResultSet cursor = executasql.executarConsulta("SELECT MAX(OID_Usuario) FROM Usuarios");

			while (cursor.next())
			{
				int oid = cursor.getInt(1);
				ed.setOid_Usuario(new Integer(oid + 1));
			}
            sql = " INSERT INTO " +
            	  " Usuarios ( " +
            	  " OID_Usuario, " +
            	  " oid_Unidade, " +
            	  " NM_Usuario, " +
            	  " DT_STAMP, " +
            	  " USUARIO_STAMP, " +
            	  " DM_STAMP, " +
            	  " NM_Senha, " +
            	  " DM_Perfil " ;
    		if (ed.getOid_Empresa() > 0 )
            	  sql += ",oid_Empresa " +
            	  ",oid_Menu_Perfil " ;
        	sql+= " ) VALUES " +
             	  "(" + 
            	  ed.getOid_Usuario() + 
            	  "," + ed.getOid_Unidade() + 
            	  ",'" + ed.getNm_Usuario() + 
            	  "','" + ed.getDt_stamp() + 
            	  "','" + ed.getUsuario_Stamp() + 
            	  "','" + ed.getDm_Stamp() + 
            	  "','" + ed.getNM_Senha() + 
            	  "','" + ed.getDM_Perfil() + "' " ;
        	if (ed.getOid_Empresa() > 0 )
        		sql+= ", " + ed.getOid_Empresa() +
        			  ", " + ed.getOid_Menu_Perfil();
            sql+= ")";

           	executasql.executarUpdate(sql);

            if (util.doValida(ed.getOid_Pessoa()))
            {
                int oid_Senha = util.getAutoIncremento("oid_Senha", "Senhas");
                sql = " insert into Senhas (OID_Senha, NM_Senha, OID_Pessoa)";
                sql += " values (" + oid_Senha + " ,'" + ed.getNM_Senha() + "','" + ed.getOid_Pessoa() + "')";
                executasql.executarUpdate(sql);
            }
        }
        catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao incluir Usuario");
            excecoes.setMetodo("inclui(Usuario_ED)");
            excecoes.setExc(exc);
            throw excecoes;
        }
        return ed;
    }

    public void altera(UsuarioED ed) throws Excecoes {

        String sql = null;

        try {

            sql = " update Usuarios set ";
            sql += " NM_Usuario = '" + ed.getNm_Usuario() + "', ";
            sql += " DT_STAMP = '" + ed.getDt_stamp() + "', ";
            sql += " USUARIO_STAMP = '" + ed.getUsuario_Stamp() + "', ";
            sql += " NM_Email = '" + ed.getNm_Email() + "', ";
            sql += " DM_Situacao = '" + ed.getDM_Situacao() + "', ";
            sql += " oid_Pessoa = '" + ed.getOid_Pessoa() + "', ";
            sql += " DM_STAMP = '" + ed.getDm_Stamp() + "', ";
            sql += " oid_Unidade = " + ed.getOid_Unidade() + ", ";
            sql += " NM_Senha = '" + ed.getNM_Senha() + "', ";
            sql += " DM_Perfil = '" + ed.getDM_Perfil() + "' ";
            if (ed.getOid_Empresa() > 0 )
            	sql += ", oid_Menu_Perfil = " + ed.getOid_Menu_Perfil() + " ";
            sql += " where oid_Usuario = " + ed.getOid_Usuario();

            executasql.executarUpdate(sql);
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao alterar Usuario");
            excecoes.setMetodo("altera(UsuarioED)");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

    public void deleta(UsuarioED ed) throws Excecoes {

        String sql = null;

        try {

            sql = "delete from Usuarios WHERE oid_Usuario = ";
            sql += ed.getOid_Usuario();

            executasql.executarUpdate(sql);
        }
        catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao excluir Usuario");
            excecoes.setMetodo("deleta(UsuarioED");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

    public ArrayList lista(UsuarioED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();

        try {
            sql = " SELECT " +
            	  " *, " +
            	  " Usuarios.oid_Empresa as oid_emp " +
                  " FROM " +
                  " Usuarios, " +
                  " Unidades, " +
                  " Pessoas " ;
            if (ed.getOid_Empresa()>0)
            	sql+= ", Menus_Perfis " ;
            sql+= " WHERE  " +
                  " Usuarios.oid_Unidade = Unidades.oid_Unidade " +
                  " AND Unidades.oid_Pessoa = Pessoas.oid_Pessoa ";
            if (ed.getOid_Empresa()>0)
            	sql+= " AND Usuarios.Oid_Menu_Perfil = Menus_Perfis.Oid_Menu_Perfil ";
            sql+= " AND Usuarios.oid_Usuario > 0 ";
            if (ed.getOid_Empresa() > 0) {
                sql += " and Usuarios.oid_Empresa = " + ed.getOid_Empresa() + " ";
            }
            if (ed.getOid_Menu_Perfil() > 0) {
                sql += " and Usuarios.Oid_Menu_Perfil = " + ed.getOid_Menu_Perfil() + " ";
            }
            if (ed.getOid_Usuario() != null && ed.getOid_Usuario().intValue() > 0) {
                sql += " and Usuarios.oid_Usuario = '" + ed.getOid_Usuario() + "'";
            }
            if (ed.getOid_Unidade() != null && ed.getOid_Unidade().intValue() > 0) {
                sql += " and unidades.oid_Unidade = " + ed.getOid_Unidade();
            }
            if (util.doValida(ed.getNm_Usuario())) {
                sql += " and Usuarios.nm_Usuario LIKE '" + ed.getNm_Usuario() + "%'";
            }
            sql += " order by nm_usuario";
// System.out.println("############ lista ususarios = "+sql);
            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                UsuarioED edVolta = new UsuarioED();
                edVolta.setOid_Usuario(new Integer(res.getString("oid_Usuario")));
                edVolta.setNm_Usuario(res.getString("nm_Usuario"));
                edVolta.setCD_Unidade(res.getString("CD_Unidade"));
                edVolta.setDM_Administrador(" ");
                if ("S".equals(res.getString("dm_administrador"))){
                  edVolta.setDM_Administrador("*");
                }
                edVolta.setNM_Razao_Social(res.getString("NM_Razao_Social"));
                edVolta.setDM_Perfil(res.getString("DM_Perfil"));
                edVolta.setOid_Unidade(new Integer(res.getInt("oid_Unidade")));
                if (edVolta.getOid_Unidade() != null && edVolta.getOid_Unidade().intValue() > 0)
                    edVolta.edUnidade = UnidadeBean.getByOID_Unidade(edVolta.getOid_Unidade().intValue());
                //Novo	
                edVolta.setOid_Empresa(res.getLong("oid_emp"));
                if (ed.getOid_Empresa()>0) {
                	edVolta.setNm_Menu_Perfil(res.getString("nm_Menu_Perfil"));
                	edVolta.setOid_Menu_Perfil(res.getLong("oid_Menu_Perfil"));
                	edVolta.setNM_Senha(res.getString("nm_Senha"));
                	edVolta.setNm_Unidade(res.getString("nm_Unidade"));
                }	
                list.add(edVolta);
            }
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro no m�todo listar");
            excecoes.setMetodo("lista(Usuario_ED");
            excecoes.setExc(exc);
            throw excecoes;
        }
        return list;
    }

    public ArrayList lista_Help(UsuarioED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();

        try {
        	
        	 sql = "select " +
       	  "* " +
       	  "from " +
       	  "Usuarios as u, " +
       	  "Empresas_Help as e " +
       	  "where " + 
       	  "u.oid_Empresa = e.oid_Empresa " ;
       
        	 if (ed.getOid_Empresa() > 0){
        		 sql += " and u.oid_Empresa = " + ed.getOid_Empresa() ;
        	 }
        	 if (ed.getOid_Usuario() != null && ed.getOid_Usuario().intValue() > 0) {
                 sql += " and Usuarios.oid_Usuario = '" + ed.getOid_Usuario() + "'";
             }
        	 
        	 sql += " order by nm_usuario";
             ResultSet res = this.executasql.executarConsulta(sql);
             while (res.next())
             {
            	 UsuarioED edVolta = new UsuarioED();
            	 edVolta.setOid_Usuario(new Integer(res.getString("oid_Usuario")));
            	 edVolta.setNm_Usuario(res.getString("nm_Usuario"));
            	 edVolta.setNM_Razao_Social(res.getString("nm_Empresa"));
            	 list.add(edVolta);
             }
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro no m�todo listar");
            excecoes.setMetodo("lista(Usuario_ED");
            excecoes.setExc(exc);
            throw excecoes;
        }
        return list;
    }
          
          
    public UsuarioED getByRecord(UsuarioED ed) throws Excecoes {

        String sql = null;

        UsuarioED edVolta = new UsuarioED();

        try {

            sql = " SELECT Usuarios.*, Pessoas.NM_Razao_Social, Unidades.CD_Unidade  " + 
            	  " FROM  Usuarios, Unidades, Pessoas "  +
            	  " WHERE Usuarios.oid_Unidade = Unidades.oid_Unidade " + 
            	  " AND   Unidades.oid_Pessoa = Pessoas.oid_Pessoa";
            if (ed.getOid_Usuario()!= null &&  ed.getOid_Usuario().intValue()>0) {
                sql += " AND OID_Usuario = " + ed.getOid_Usuario();
            }else {
                sql += " AND NM_Usuario = '" + ed.getNm_Usuario() + "'" +
                	   " AND NM_Senha = '" + ed.getNM_Senha() + "'";
            }

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next()) {
                edVolta = new UsuarioED();
                edVolta.setOid_Usuario(new Integer(res.getInt("oid_Usuario")));
                edVolta.setNm_Usuario(res.getString("nm_Usuario"));
                edVolta.setOid_Pessoa(res.getString("oid_Pessoa"));
                edVolta.setOid_Unidade(new Integer(res.getInt("oid_Unidade")));
                edVolta.setCD_Unidade(res.getString("CD_Unidade"));
                edVolta.setNM_Razao_Social(res.getString("NM_Razao_Social"));
                edVolta.setDM_Perfil(res.getString("DM_Perfil"));
                edVolta.setDM_Situacao(res.getString("DM_Situacao"));
                edVolta.setNm_Email(res.getString("NM_Email"));
                edVolta.setDM_Administrador(res.getString("DM_Administrador"));
                edVolta.setNM_Senha(res.getString("NM_Senha"));
                if (edVolta.getOid_Unidade() != null && edVolta.getOid_Unidade().intValue() > 0)
                    edVolta.edUnidade = UnidadeBean.getByOID_Unidade(edVolta.getOid_Unidade().intValue());

                inclui_Acesso_Usuario(edVolta.getOid_Usuario().longValue());
            
            }
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao recuperar registro! [" + exc.getMessage() + "]");
            excecoes.setMetodo("getByRecord");
            excecoes.setExc(exc);
            throw excecoes;
        }
        return edVolta;
    }

    public void geraRelatorio(UsuarioED ed) throws Excecoes {

        //    String sql = null;
        //
        //    UsuarioED edVolta = new UsuarioED();
        //
        //    try{
        //
        //      sql = "select * from Usuarios where oid_Usuario > 0";
        //
        //      if (ed.getCD_Usuario() != null && !ed.getCD_Usuario().equals("")){
        //        sql += " and CD_Usuario = '" + ed.getCD_Usuario() + "'";
        //      }
        //      if (ed.getCD_Remessa() != null && !ed.getCD_Remessa().equals("")){
        //        sql += " and CD_Remessa = '" + ed.getCD_Remessa() + "'";
        //      }
        //
        //      ResultSet res = null;
        //      res = this.executasql.executarConsulta(sql);
        //
        //      UsuarioRL Usuario_rl = new UsuarioRL();
        //      Usuario_rl.geraRelatEstoque(res);
        //    }
        //    catch (Excecoes e){
        //      throw e;
        //    }
        //    catch(Exception exc){
        //      Excecoes exce = new Excecoes();
        //      exce.setExc(exc);
        //      exce.setMensagem("Erro no m�todo listar");
        //      exce.setClasse(this.getClass().getName());
        //      exce.setMetodo("geraRelatorio(UsuarioED ed)");
        //    }
        //
    }

    public byte[] geraRelacaoUsuarios(UsuarioED ed) throws Excecoes {

    	
    	return null;
        
    }
    
    public boolean getByEncrypt(String chave, String usuario) throws Excecoes {
        String sql = null;
        String chave_privada = "";
        boolean ok = false;

        try {
            int tam = chave.length();
            int tam0 = ((tam - 28) / 2);
            int tam1 = 14 + ((tam - 28) / 2);
            int tam2 = 14 + ((tam - 28) / 3);

            String senhaR = chave.substring(0, tam0);
            String senhaL = chave.substring(tam1, tam2 + tam0);
            String senhaM = chave.substring((tam2 + 14) + tam0, tam);

            chave_privada = senhaL + senhaM + senhaR;

            sql = " select * from usuarios " + " WHERE  UPPER(nm_senha) = UPPER('" + chave_privada + "') and UPPER(cd_usuario) = UPPER('" + usuario + "')";
            ResultSet res = null;
            res = this.executasql.executarConsulta(sql);
            while (res.next()) {
                ok = true;
            }
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao recuperar usuario");
            excecoes.setExc(exc);
            throw excecoes;
        }
        return ok;
    }
    
    public boolean getByEncrypt(String chave, String usuario, String acao) throws Excecoes {
        String sql = null;
        String chave_privada = "";
        boolean ok = false;

        try {
            int tam = chave.length();
            int tam0 = ((tam - 28) / 2);
            int tam1 = 14 + ((tam - 28) / 2);
            int tam2 = 14 + ((tam - 28) / 3);

            String senhaR = chave.substring(0, tam0);
            String senhaL = chave.substring(tam1, tam2 + tam0);
            String senhaM = chave.substring((tam2 + 14) + tam0, tam);
            
            chave_privada = senhaL + senhaM + senhaR;
            chave_privada = this.limpaCampo(chave_privada);
            

            sql = " select * from usuarios " + " WHERE  UPPER(nm_senha) = UPPER('" + chave_privada + "') and UPPER(nm_usuario) = UPPER('" + usuario + "')" +
            	  " and " +acao+ " = 'S'";
            ResultSet res = null;
            res = this.executasql.executarConsulta(sql);
            while (res.next()) {
                ok = true;
            }
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao recuperar usuario");
            excecoes.setExc(exc);
            throw excecoes;
        }
        return ok;
    }
    
    public UsuarioED getByRecord_Encrypt(UsuarioED ed) throws Excecoes {

        String sql = null;
        String chave_privada = "";
        UsuarioED edVolta = new UsuarioED();

        try {
        	
            sql = "select * from Usuarios, Unidades, Pessoas where 1=2 and " + " Usuarios.oid_Unidade = Unidades.oid_Unidade and" + " Unidades.oid_Pessoa = Pessoas.oid_Pessoa";

            if (String.valueOf(ed.getOid_Usuario()) != null && !String.valueOf(ed.getOid_Usuario()).equals("0") && !String.valueOf(ed.getOid_Usuario()).equals("null")) {
                sql = "select * from Usuarios, Unidades, Pessoas where" + " Usuarios.oid_Unidade = Unidades.oid_Unidade and" + " Unidades.oid_Pessoa = Pessoas.oid_Pessoa";
                sql += " and OID_Usuario = " + ed.getOid_Usuario();
            }

            if (ed.getNm_Usuario() != null && !ed.getNm_Usuario().equals("") && !ed.getNm_Usuario().equals("null") && ed.getNM_Senha() != null && !ed.getNM_Senha().equals("")
                    && !ed.getNM_Senha().equals("null")) {
            	String chave = ed.getNM_Senha();
            	int tam = chave.length();
                int tam0 = ((tam - 28) / 2);
                int tam1 = 14 + ((tam - 28) / 2);
                int tam2 = 14 + ((tam - 28) / 3);

                String senhaR = chave.substring(0, tam0);
                String senhaL = chave.substring(tam1, tam2 + tam0);
                String senhaM = chave.substring((tam2 + 14) + tam0, tam);

                chave_privada = senhaL + senhaM + senhaR;
                
                chave_privada = this.limpaCampo(chave_privada);
            	
                sql = "select * from Usuarios, Unidades, Pessoas where" + " Usuarios.oid_Unidade = Unidades.oid_Unidade and" + " Unidades.oid_Pessoa = Pessoas.oid_Pessoa";
                sql += " and NM_Usuario = '" + ed.getNm_Usuario() + "'";
                sql += " and NM_Senha = '" + chave_privada + "'";

            
            }

            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next()) {
                edVolta = new UsuarioED();
                edVolta.setOid_Usuario(new Integer(res.getInt("oid_Usuario")));
                edVolta.setNm_Usuario(res.getString("nm_Usuario"));
                edVolta.setOid_Unidade(new Integer(res.getInt("oid_Unidade")));
                edVolta.setCD_Unidade(res.getString("CD_Unidade"));
                edVolta.setNM_Razao_Social(res.getString("NM_Fantasia"));
                edVolta.setDM_Situacao(res.getString("DM_Situacao"));
                
                edVolta.setDM_Perfil(res.getString("DM_Perfil"));
                edVolta.setDM_Administrador(res.getString("DM_Administrador"));
                edVolta.setNM_Senha(res.getString("NM_Senha"));
                if (edVolta.getOid_Unidade() != null && edVolta.getOid_Unidade().intValue() > 0)
                    edVolta.edUnidade = UnidadeBean.getByOID_Unidade(edVolta.getOid_Unidade().intValue());

                inclui_Acesso_Usuario(edVolta.getOid_Usuario().longValue());
            
                edVolta.setOid_Empresa(res.getInt("oid_Empresa"));
            
            }
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao recuperar registro! [" + exc.getMessage() + "]");
            excecoes.setMetodo("getByRecord");
            excecoes.setExc(exc);
            throw excecoes;
        }
        return edVolta;
    }

    
    public void inclui_Acesso_Usuario(long oid_Usuario) throws Excecoes {

        String sql = null;

        try {
        	
            long oid_Acesso_Usuario = (new Long (String.valueOf (System.currentTimeMillis ()).toString ()).longValue ());
            long nr_Ultimo_Acesso = (new Long (String.valueOf (System.currentTimeMillis ()).toString ().substring(0,10)).longValue ());
            sql = "SELECT oid_Acesso_Usuario FROM Acessos_Usuarios WHERE nr_Ultimo_Acesso=" + nr_Ultimo_Acesso;

            // System.out.println(sql);
            
            ResultSet res = this.executasql.executarConsulta(sql);
            if (!res.next()) {

	                sql = " INSERT INTO Acessos_Usuarios (" + 
	          	  " oid_Acesso_Usuario, " +
	          	  " nr_Ultimo_Acesso, " +
	          	  " OID_Usuario, " +
	          	  " DT_Acesso, " +
	          	  " HR_Acesso " +
	          	  " ) VALUES " +
	           	  "(" + 
	           	    oid_Acesso_Usuario+ 
	              "," + nr_Ultimo_Acesso + 
	          	  "," + oid_Usuario + 
	          	  ",'" + Data.getDataDMY () + 
	          	  "','" + Data.getHoraHM() + "')";
	                
	                // System.out.println(sql);
	                
	                executasql.executarUpdate(sql);
            }
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro inclui_Acesso_Usuario! [" + exc.getMessage() + "]");
            excecoes.setMetodo("inclui_Acesso_Usuario");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }
    
    /**
     * Busca o usuario e a empresa validando se o usuario existe para ea empresa e sua senha.
     * @param ed
     * @return
     * @throws Excecoes
     */
    public UsuarioED getByRecord_Encrypt_Stgp(UsuarioED ed) throws Excecoes {
        String sql = null;
        String chave_privada = "";
        UsuarioED edVolta = new UsuarioED();
        try {
        	// Distrincha a senha....
        	String chave = ed.getNM_Senha();
        	int tam = chave.length();
            int tam0 = ((tam - 28) / 2);
            int tam1 = 14 + ((tam - 28) / 2);
            int tam2 = 14 + ((tam - 28) / 3);
            String senhaR = chave.substring(0, tam0);
            String senhaL = chave.substring(tam1, tam2 + tam0);
            String senhaM = chave.substring((tam2 + 14) + tam0, tam);
            chave_privada = senhaL + senhaM + senhaR;
            // Chegando na senha finalmente...
            chave_privada = this.limpaCampo(chave_privada);
            
            sql = "select " +
            	  "* " +
            	  "from " +
            	  "Usuarios as u, " +
            	  "Empresas_Stgp as e " +
            	  "where " + 
            	  "u.oid_Empresa = e.oid_Empresa and " + 
            	  "u.nm_Usuario = '" + ed.getNm_Usuario() + "' and " +
            	  "u.nm_Senha = '" + chave_privada + "' and " +
            	  "e.nr_Cnpj = '" + ed.getNr_Cnpj() +"'" ;
            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next()) {
                edVolta = new UsuarioED();
                edVolta.setOid_Usuario(new Integer(res.getInt("oid_Usuario")));
                edVolta.setNm_Usuario(res.getString("nm_Usuario"));
                edVolta.setOid_Unidade(new Integer(res.getInt("oid_Unidade")));
                //edVolta.setCD_Unidade(res.getString("CD_Unidade"));
                //edVolta.setNM_Razao_Social(res.getString("NM_Fantasia"));
                edVolta.setDM_Perfil(res.getString("DM_Perfil"));
                edVolta.setDM_Administrador(res.getString("DM_Administrador"));
                edVolta.setNM_Senha(res.getString("NM_Senha"));
                if (edVolta.getOid_Unidade() != null && edVolta.getOid_Unidade().intValue() > 0)
                    edVolta.edUnidade = UnidadeBean.getByOID_Unidade(edVolta.getOid_Unidade().intValue());
                edVolta.setOid_Empresa(res.getLong("oid_Empresa"));
                edVolta.setNm_Razao_Social(res.getString("nm_Razao_Social"));
                // Menu
                edVolta.setOid_Menu_Perfil(res.getLong("oid_Menu_Perfil"));

                inclui_Acesso_Usuario(edVolta.getOid_Usuario().longValue());
                
            }
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao recuperar registro! [" + exc.getMessage() + "]");
            excecoes.setMetodo("getByRecord_Encrypt_Stgp()");
            excecoes.setExc(exc);
            throw excecoes;
        }
        return edVolta;
    }

    /**
     * Busca o usuario e a empresa validando se o usuario existe para e a empresa e sua senha.
     * @param ed
     * @return
     * @throws Excecoes
     */
    public UsuarioED getByRecord_Encrypt_Help(UsuarioED ed) throws Excecoes {
        String sql = null;
        String chave_privada = "";
        UsuarioED edVolta = new UsuarioED();
        try {
        	// Distrincha a senha....
        	String chave = ed.getNM_Senha();
        	int tam = chave.length();
            int tam0 = ((tam - 28) / 2);
            int tam1 = 14 + ((tam - 28) / 2);
            int tam2 = 14 + ((tam - 28) / 3);
            String senhaR = chave.substring(0, tam0);
            String senhaL = chave.substring(tam1, tam2 + tam0);
            String senhaM = chave.substring((tam2 + 14) + tam0, tam);
            chave_privada = senhaL + senhaM + senhaR;
            // Chegando na senha finalmente...
            chave_privada = this.limpaCampo(chave_privada);
    
            sql = "select " +
            	  "* " +
            	  "from " +
            	  "Usuarios as u, " +
            	  "Empresas_Help as e " +
            	  "where " + 
            	  "u.oid_Empresa = e.oid_Empresa and " + 
            	  "u.nm_Usuario = '" + ed.getNm_Usuario() + "' and " +
            	  "u.nm_Senha = '" + chave_privada + "' " ;
            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next()) {
                edVolta = new UsuarioED();
                edVolta.setOid_Usuario(new Integer(res.getInt("oid_Usuario")));
                edVolta.setNm_Usuario(res.getString("nm_Usuario"));
                edVolta.setOid_Unidade(new Integer(res.getInt("oid_Unidade")));
                //edVolta.setCD_Unidade(res.getString("CD_Unidade"));
                //edVolta.setNM_Razao_Social(res.getString("NM_Fantasia"));
                edVolta.setDM_Perfil(res.getString("DM_Perfil"));
                edVolta.setDM_Administrador(res.getString("DM_Administrador"));
                edVolta.setNM_Senha(res.getString("NM_Senha"));
                if (edVolta.getOid_Unidade() != null && edVolta.getOid_Unidade().intValue() > 0)
                    edVolta.edUnidade = UnidadeBean.getByOID_Unidade(edVolta.getOid_Unidade().intValue());
                edVolta.setOid_Empresa(res.getLong("oid_Empresa"));
                //edVolta.setNm_Razao_Social(res.getString("nm_Razao_Social"));
                // Menu
                //edVolta.setOid_Menu_Perfil(res.getLong("oid_Menu_Perfil"));

                inclui_Acesso_Usuario(edVolta.getOid_Usuario().longValue());
                
            }
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao recuperar registro! [" + exc.getMessage() + "]");
            excecoes.setMetodo("getByRecord_Encrypt_Help()");
            excecoes.setExc(exc);
            throw excecoes;
        }
        return edVolta;
    }
   
    /**
     * Busca o usuario e a empresa validando se o usuario existe para e a empresa e sua senha.
     * @param ed
     * @return
     * @throws Excecoes
     */
    public UsuarioED getByRecord_Help(UsuarioED ed) throws Excecoes {
        String sql = null;
        UsuarioED edVolta = new UsuarioED();
        try {
           
            sql = "select " +
            	  "* " +
            	  "from " +
            	  "Usuarios as u, " +
            	  "Empresas_Help as e " +
            	  "where " + 
            	  "u.oid_Empresa = e.oid_Empresa " ;
            if (ed.getOid_Usuario() != null && ed.getOid_Usuario().intValue() > 0) {
                sql += " and u.oid_Usuario = " + ed.getOid_Usuario() ;
            }
            	
            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next()) {
                edVolta = new UsuarioED();
                edVolta.setOid_Usuario(new Integer(res.getInt("oid_Usuario")));
                edVolta.setNm_Usuario(res.getString("nm_Usuario"));
                edVolta.setOid_Unidade(new Integer(res.getInt("oid_Unidade")));
                edVolta.setDM_Perfil(res.getString("DM_Perfil"));
                edVolta.setDM_Administrador(res.getString("DM_Administrador"));
                edVolta.setNM_Senha(res.getString("NM_Senha"));
                edVolta.setNM_Razao_Social(res.getString("nm_empresa"));
                if (edVolta.getOid_Unidade() != null && edVolta.getOid_Unidade().intValue() > 0)
                    edVolta.edUnidade = UnidadeBean.getByOID_Unidade(edVolta.getOid_Unidade().intValue());
                edVolta.setOid_Empresa(res.getLong("oid_Empresa"));
                inclui_Acesso_Usuario(edVolta.getOid_Usuario().longValue());
                
            }
        } catch (Exception exc) {
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("Erro ao recuperar registro! [" + exc.getMessage() + "]");
            excecoes.setMetodo("getByRecord_Help()");
            excecoes.setExc(exc);
            throw excecoes;
        }
        return edVolta;
    }

    private static String limpaCampo(String str) {

        String ret="";

        if (str != null) {
                char[] strChar = str.toCharArray();
                char[] novaStr = new char[strChar.length];
                int j=0;
                for (int i=0;i<strChar.length;i++) {
                        if ( (strChar[i]!='*'))	{
                                novaStr[j] = strChar[i];
                                j++;
                        }
                }
                char[] cRet = new char[j];

                System.arraycopy(novaStr,0,cRet,0,j);

                ret = new String(cRet);
        }
        return ret;
  }
    
}
