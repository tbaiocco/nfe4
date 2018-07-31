package com.master.util.bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.master.util.Excecoes;

public class ExecutaSQL {

    private Connection conexao = null;
    private Transacao transacao;

    public ExecutaSQL() {
    }

    public void setConnection(Connection connection) {
        this.conexao = connection;
        try {
            conexao.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected Connection getConnection() {
        return conexao;
    }

    protected void commit() {
        try {
            conexao.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void rollback() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void close() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet executarConsulta(String sql) throws Excecoes {
        //Log.Logsql("ExecutaSQL.executarConsulta","iniciou consulta");

        Statement st = null;
        ResultSet rs = null;
        if (conexao == null)
            throw new Excecoes("Conexão não está ativa!", this.getClass().getName(), "executarConsulta");
        
        try {
            st = conexao.createStatement();
            //st = conexao.createStatement(ResultSet.TYPE_FORWARD_ONLY,
            // ResultSet.CONCUR_READ_ONLY);
            if (transacao != null) {
            	transacao.addQuery(sql);
            }
            rs = st.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            throw new Excecoes(e.getMessage() + " errorCode: ["+e.getErrorCode()+"]", e, this.getClass().getName(), "executarConsulta");
        }
    }

    public int executarUpdate(String sql) throws SQLException, Excecoes {
        //Log.Logsql("ExecutaSQL.executarUpdate","iniciou update");
        Statement st = null;
        int result = 0;
        
        if (conexao == null)
            throw new Excecoes("Conexão não está ativa!", this.getClass().getName(), "executarUpdate");
        try {
            st = conexao.createStatement();
            //st.execute("ALTER SESSION SET NLS_DATE_FORMAT = 'dd/mm/yyyy
            // hh24:mi:ss'");
            if (transacao != null) {
            	transacao.addQuery(sql);
            }
            result = st.executeUpdate(sql);
            st.close();

        } catch (SQLException e) {
            throw e;
        } finally {
            //Log.Logsql("ExecutaSQL.executarUpdate","terminou update");
        }
        return result;
    }
	public void setTransacao(Transacao transacao) {
		this.transacao = transacao;
	}
	
	public final void lockTable(String nmTable, String nmTransaction){
        String lock = "LOCK TABLE " + nmTable + " IN ACCESS EXCLUSIVE MODE";
	Statement st = null;
	ResultSet rs = null;
	try	{
		st = conexao.createStatement();
        		rs = st.executeQuery("BEGIN " + nmTransaction + ";");
        		rs = st.executeQuery(lock);
		st.close();
            }
            catch(Exception e){
                e.printStackTrace(); 
            }
    }
    public final void unlockTable(String nmTransaction){

	Statement st = null;
	ResultSet rs = null;
	try	{
		st = conexao.createStatement();
        		rs = st.executeQuery("COMMIT " + nmTransaction + ";");
            }
            catch(Exception e){
                e.printStackTrace(); 
            }
        
    }

	public final void beginTransacao(String nmTransaction){
	    Statement st = null;
	    try     {
	            st = conexao.createStatement();
	            //st.execute("BEGIN " + nmTransaction + ";");
	            st.execute("BEGIN;");
	            st.close();
	    }
	    catch(Exception e){
	        e.printStackTrace();
	    }
	}
	public final void commitTransacao(String nmTransaction){

        Statement st = null;
        try     {
                st = conexao.createStatement();
                //st.execute("COMMIT " + nmTransaction + ";");
                st.execute("COMMIT;");
        }
        catch(Exception e){
            e.printStackTrace();
        }

	}
	
	public final void rollbackTransacao(String nmTransaction){

        Statement st = null;
        try     {
                st = conexao.createStatement();
                st.execute("ROLLBACK " + nmTransaction + ";");
        }
        catch(Exception e){
            e.printStackTrace();
        }

	}
	
}