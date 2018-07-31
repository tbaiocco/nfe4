/*
 * Created on 19/10/2005
 */
package com.master.util;


import java.sql.ResultSet;
import java.sql.SQLException;

import com.master.util.bd.ExecutaSQL;
import com.master.util.bd.Transacao;

/**
 * @author Teofilo Poletto Baiocco
 */
public class Utilitaria extends Transacao {


    public Utilitaria() {
        super();
    }
    public Utilitaria(ExecutaSQL executasql) {
        super(executasql);
    }

    String query = "";

    /**
     * Retorna um valor String de um campo especifico de uma tabela
     */
    public String getTableStringValue(String campo, String from, String where) throws Excecoes {

        this.inicioTransacao();
        String objResult = "";
        try{

            query =  " select "+campo+" as String_Value from "+from;
            if (!where.equals(""))
                query += " where " + where;

            ResultSet res = this.sql.executarConsulta(query);

            if (res.next())
                objResult = res.getString("String_Value");

        } catch(Exception exc){
            throw new Excecoes(exc.getMessage(), exc,
                    this.getClass().getName(), "getTableValue()");
        } finally {
            this.fimTransacao(false);
        }
        return objResult;
    }

    /**
     * Retorna um valor INT de um campo especifico de uma tabela
     */
    public int getTableIntValue(String campo, String from, String where) throws Excecoes {

        this.inicioTransacao();
        int objResult = 0;
        try{

            query =  " select "+campo+" as Int_Value from "+from;
            if (!where.equals(""))
                query += " where " + where;

            ResultSet res = this.sql.executarConsulta(query);

            if (res.next())
                objResult = res.getInt("Int_Value");

        }catch(Exception exc){
            throw new Excecoes(exc.getMessage(), exc,
                    this.getClass().getName(), "getTableValue()");
        } finally {
            this.fimTransacao(false);
        }
        return objResult;
    }

    /**
     * Retorna um valor DOUBLE de um campo especifico de uma tabela
     */
    public double getTableDoubleValue(String campo, String from, String where) throws Excecoes {

        this.inicioTransacao();
        double objResult = 0;
        try{

            query =  " select "+campo+" as Double_Value from "+from;
            if (!where.equals(""))
                query += " where " + where;

            ResultSet res = this.sql.executarConsulta(query);

            if (res.next())
                objResult = res.getDouble("Double_Value");

        }catch(Exception exc){
            throw new Excecoes(exc.getMessage(), exc,
                    this.getClass().getName(), "getTableValue()");
        } finally {
            this.fimTransacao(false);
        }
        return objResult;
    }

    /**
     * Retorna o próximo ID disponível
     */
    public int getAutoIncremento(String campo, String from) throws Excecoes {

        return (getAutoIncremento(campo, from, true));

    }

    public String trunca(String campo, int tamanho) throws Excecoes {
      if (campo.length()> tamanho){
        campo= campo.substring(0,tamanho);
      }
      return (campo);

    }


    public int getAutoIncremento(String campo, String from , boolean abreTransacao) throws Excecoes {

        return (getMaximo(campo, from, "",abreTransacao) + 1);

    }

    /**
     * Retorna o maior valor do campo informado
     */
    public int getMaximo(String campo, String from, String where) throws Excecoes {
        return getMaximo(campo, from, where, true);
    }

    /**
     * Retorna o maior valor do campo informado
     */
    public int getMaximo(String campo, String from, String where, boolean abreTransacao) throws Excecoes {

        int next = -1;
        if (abreTransacao) {
        	this.inicioTransacao();
        }
        try{

            query =  " select max("+campo+") as count from "+from;
            if (!where.equals(""))
                query += " where " + where;

            ResultSet res = this.sql.executarConsulta(query);

            if (res.next())
                next = res.getInt("count");

        }catch(Exception exc){
            throw new Excecoes(exc.getMessage(), exc,
            this.getClass().getName(), "getMaximo()");
        } finally {
            if (abreTransacao)
                this.fimTransacao(false);
        }
        return next;
    }
    /**
     * Retorna o maior DATA do campo informado
     */
    public String getMaximoData(String campo, String from, String where) throws Excecoes {

        String next = null;

        this.inicioTransacao();
        try{

            query = " select max("+campo+") as max_data from "+from;
            if (!where.equals(""))
                query += " where " + where;

            ResultSet res = this.sql.executarConsulta(query);

            if (res.next())
                next = res.getString("max_data");

        }catch(Exception exc){
            throw new Excecoes(exc.getMessage(), exc,
                    this.getClass().getName(), "getMaximoData()");
        } finally {
            this.fimTransacao(false);
        }

        return next;
    }

    /**
     * Verifica se o registro existe
     */
    public boolean doExiste(String strFrom, String strWhere) throws Excecoes {

        boolean existe = false;

        this.inicioTransacao();
        try {

            query = " select count(*) as count";
            query+= " from "+ strFrom;
            query+= " where 1=1 and "+ strWhere;

            ResultSet res = this.sql.executarConsulta(query);
            if (res.next()){
                existe = (res.getInt("count") > 0);
            }

        } catch (SQLException exc) {
            throw new Excecoes(exc.getMessage(), exc,
                    this.getClass().getName(), "doExiste()");
        } finally {
            this.fimTransacao(false);
        }
        return existe;
    }

    /**
     * Verifica se o parâmetro do request é válido
     */
    public static boolean doValida(String parametro) {
        return JavaUtil.doValida(parametro);
    }

    /**
     * Verifica se o parâmetro do request é válido [STRING]
     */
    public String getValueDef(String value, String strDefault) {
        return JavaUtil.getValueDef(value, strDefault);
    }

    /**
     * Verifica se o parâmetro do request é válido [DOUBLE]
     */
    public double getValueDef(String value, double dblDefault) {

        return (doValida(value)) ? Double.parseDouble(value) : dblDefault;
    }

    /**
     * Verifica se o parâmetro é válido [DOUBLE]
     */
    public double getValueDef(double value, double dblDefault) {

        return JavaUtil.getValueDef(value, dblDefault);
    }

    /**
     * Verifica se o parâmetro do request é válido [INT]
     */
    public int getValueDef(int value, int intDefault) {
        return JavaUtil.getValueDef(value, intDefault);
    }

    /**
     * Verifica se o parâmetro do request é válido [INT]
     */
    public int getValueDef(String value, int intDefault) {
        return (doValida(value)) ? Integer.parseInt(value) : intDefault;
    }

    public String getSQLString(String string) {
        return JavaUtil.getSQLString(string);
    }

    public static String getSQLStringLike(String string) {
        return JavaUtil.getSQLStringLike(string);
    }

    public String getSQLStringDef(String string, String defaultValue) {
        return JavaUtil.getSQLStringDef(string, defaultValue);
    }

    /**
     * Verifica se
     * - O ID foi excluído por outro usuário
     * - Já existe outro ID com o mesmo código
     */
    public void doValidaUpdate(int oid, String cd, String table, String fieldOid, String fieldCd) throws Excecoes {
        String from = table;
        String where = fieldOid + " = " + oid;
        // Valida se o registro em edição foi excluído por outro usuário
        if (doExiste(from, where)) {
            where = fieldOid + " <> " + oid +
            		" and " + fieldCd + " = '" + cd + "'";
            // Valida se existe outro registro com o mesmo código
            if (doExiste(from, where)) {
                throw new Mensagens("Já existe um registro com este código!");
            }
        } else throw new Excecoes("ID informado para atualização não existe (pode ter sido " +
        		"excluído por outro usuário), efetue a consulta novamente!");
    }

    public void closeResultset(ResultSet res) {
    	if (res != null) {
	        try {
	            res.close();
	        } catch (SQLException e) {
                e.printStackTrace();
            }
	        try {
	        	if (res.getStatement() != null) {
	        		res.getStatement().close();
	        	}
	        } catch (SQLException e) {
	            //No driver jdbc mais novo, o statement é fechado com o fechamento do resultset
	        }
    	}
    }

    public String getSQLDate(String date) {
        return JavaUtil.getSQLDate(date);
    }


    /**
     * 	Retorna o código de cor aleatoriamente
     */
    public String getCorAleatoria() {
    	
    	long rand[] = {16,16,16,16,16,16};
    	String cor="0";
    	String hextable[] = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};	// Used for conversion.
    	for (int nowAt=0; nowAt < rand.length; nowAt++)
    		{
    		while ( rand[nowAt] > 15 )
    			{
    			rand[nowAt]=Math.round((Math.random()*10)+(Math.random()*10));
    			}
    		}
    		if (rand[2] < 9) rand[2]=9;
    		if (rand[2] > 12) rand[2]=12;
    		cor=hextable[(int)rand[0]]+hextable[(int)rand[1]]+hextable[(int)rand[2]]+hextable[(int)rand[3]]+hextable[(int)rand[4]]+hextable[(int)rand[5]];
    	
        return cor;
    }

    /**
     * 	Retorna o código de cor sequencialmente
     */
    public String getCorGrafico(int cta) {
    	int i = 0, modulo = 0;
    	String aCor[]={"99F0E9","99F0C9","99F0A9","99F099","B9F099","D9F099","E9F099",
    				   "F0E999","F0C999","F0A999","F09999","F099B9","F099D9","F099F0",
    				   "99E9F0","99C9F0","99A9F0","9999F0","B999F0","D999F0","E999F0" };
    	if (cta < 21) 
    		i = cta;
    	else {
    		modulo = cta / 21 ;
    		i = cta - (modulo * 21);
    	}
    	return aCor[i];
    }
    
}
