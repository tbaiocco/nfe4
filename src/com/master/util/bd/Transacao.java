package com.master.util.bd;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.util.Iterator;

import com.master.util.TransacaoLog;
import com.master.util.ed.Parametro_FixoED;

import br.cte.model.Empresa;

public class Transacao {

    protected ExecutaSQL sql;
    protected ExecutaSQL sqlBase2;
    private boolean bParticipa;
    private boolean bParticipaBase2;
    private static Parametro_FixoED parametro_FixoED = null;

    private static JDBCConnectionPool jdbcConPool = null;

//    private static JDBCConnectionPool jdbcConPoolBase2 = new JDBCConnectionPool(parametro_FixoED.getNM_Database_DriverBase2(),
//			   															   parametro_FixoED.getNM_Database_URLBase2(),
//			   															   parametro_FixoED.getNM_Database_UsuarioBase2(),
//			   															   parametro_FixoED.getNM_Database_PwdBase2());

    private TransacaoLog trancacoesLog = new TransacaoLog();

    public Transacao() {
        sql = null;
        sqlBase2 = null;
        bParticipa = false;
        bParticipaBase2 = false;

        parametro_FixoED = Parametro_FixoED.getInstancia();

        jdbcConPool = new JDBCConnectionPool(parametro_FixoED.getNM_Database_Driver(),
				   parametro_FixoED.getNM_Database_URL(),
				   parametro_FixoED.getNM_Database_Usuario(),
				   parametro_FixoED.getNM_Database_Pwd());

    }

    public Transacao(Empresa empresa) {
        sql = null;
        sqlBase2 = null;
        bParticipa = false;
        bParticipaBase2 = false;

        parametro_FixoED = Parametro_FixoED.getInstancia();

        jdbcConPool = new JDBCConnectionPool(empresa.getDbDriver(),
				   empresa.getDbURL(),
				   empresa.getDbUser(),
				   empresa.getDbPass());

    }

    public Transacao(br.mdfe.model.Empresa empresa) {
        sql = null;
        sqlBase2 = null;
        bParticipa = false;
        bParticipaBase2 = false;

        parametro_FixoED = Parametro_FixoED.getInstancia();

        jdbcConPool = new JDBCConnectionPool(empresa.getDbDriver(),
				   empresa.getDbURL(),
				   empresa.getDbUser(),
				   empresa.getDbPass());

    }

    protected Transacao(ExecutaSQL executasql) {
        if (executasql != null) {
            sql = executasql;
            bParticipa = true;
            bParticipaBase2 = true;
        }
//        else new Transacao();
    }

    protected void inicioTransacao() {
        try {
            if (!bParticipa && sql == null || sql.getConnection().isClosed())
            {
                sql = new ExecutaSQL();
                sql.setConnection(jdbcConPool.pegaConexao());
                if (Parametro_FixoED.getInstancia().isLogTransactions()) {
                	doSetPID();
                	sql.setTransacao(this);
                }
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

//    protected void inicioTransacaoBase2() {
//        try {
//            if (!bParticipaBase2 && sqlBase2 == null || sqlBase2.getConnection().isClosed())
//            {
//            	sqlBase2 = new ExecutaSQL();
//            	sqlBase2.setConnection(jdbcConPoolBase2.pegaConexao());
//                if (Parametro_FixoED.getInstancia().isLogTransactions()) {
//                	doSetPID();
//                	sqlBase2.setTransacao(this);
//                }
//            }
//        } catch (Exception exc) {
//            exc.printStackTrace();
//        }
//    }

    protected void fimTransacao(boolean flag) {
        if (sql != null && !bParticipaBase2) {
            if (flag)
                sql.commit();

            sql.close();
            jdbcConPool.soltaConexao(sql.getConnection());
            sql = null;
            writeLog();
        }
    }

    protected void fimTransacaoBase2(boolean flag) {
        if (sqlBase2 != null && !bParticipaBase2) {
            if (flag)
            	sqlBase2.commit();

            sqlBase2.close();
            jdbcConPool.soltaConexao(sqlBase2.getConnection());
            sqlBase2 = null;
            writeLog();
        }
    }

    protected void abortaTransacao() {
        if (sql != null) {
            sql.rollback();
            sql.close();
            jdbcConPool.soltaConexao(sql.getConnection());
            sql = null;
        }
    }

    private void doSetPID() {
    	try {
			ResultSet res = sql.executarConsulta("select pg_backend_pid from pg_backend_pid()");
			try {
				if (res.next()) {
					trancacoesLog.setPid(res.getInt("pg_backend_pid"));
					trancacoesLog.setClasse(getClass().getName());
				}
			} finally {
		    	if (res != null) {
		            res.close();
		            if (res.getStatement() != null) {
		            	res.getStatement().close();
		            }
		    	}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
    }

    protected void doBeginTransaction() {
    	try {
			int i = sql.executarUpdate("BEGIN");

		} catch (Throwable e) {
			e.printStackTrace();
		}
    }

    protected void addQuery(String sql) {
    	trancacoesLog.getComandos().add(sql);
    	writeLog();
    }
    protected void writeLog() {
    	if (Parametro_FixoED.getInstancia().isLogTransactions()) {
	    	try {
	    		String directory = "/tmp/";
		    	File file = new File(directory);
		    	if (!file.exists()) {
		    		directory = "c:/temp/";

		    	}
		    	String arquivo = directory + trancacoesLog.getPid() + ".log";
		    	file = new File(arquivo);
		    	OutputStream out = new FileOutputStream(file);
		    	String output = "";
		    	Iterator iterator = trancacoesLog.getComandos().iterator();
		    	while (iterator.hasNext()) {
		    		output += "\n" + iterator.next();
		    	}
		    	byte[] b = output.getBytes();
		    	out.write(b);
		    	out.flush();
		    	out.close();
	    	} catch (Throwable e) {
	    		e.printStackTrace();
	    	}
    	}
    }
}