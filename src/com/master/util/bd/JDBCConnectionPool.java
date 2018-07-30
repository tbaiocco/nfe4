package com.master.util.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnectionPool extends ObjectPool {

    private String dsn, usr, pwd;

    public JDBCConnectionPool(String driver, String dsn, String usr, String pwd) {
        try {
            Class.forName(driver).newInstance();
            this.dsn = dsn;
            this.usr = usr;
            this.pwd = pwd;
// System.out.println("str conexao: " + dsn);
            Connection cx = pegaConexao();
            soltaConexao(cx);
        } catch (Exception e) {
            // System.out.println("str conexao: " + dsn);
            // System.out.println("ERRO NA CONEXAO COM O BD!");
            e.printStackTrace();
        }

    }

    Object create() throws SQLException {
    	try{
    		Connection c = DriverManager.getConnection(dsn, usr, pwd);
    		return c;
    	} catch (Exception e){
    		e.printStackTrace();
    		throw new SQLException();
    	}
    }

    boolean validate(Object o) {
        try {
            return (!((Connection) o).isClosed());
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    void expire(Object o) {
        Connection cn = (Connection) o;
        try {
            cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // System.out.println(e.getErrorCode() + " -> " + e.getMessage());
        }
    }

    public Connection pegaConexao() throws SQLException {
        try {
            return ((Connection) super.checkOut());
        } catch (Exception e) {
            throw ((SQLException) e);
        }
    }

    public void soltaConexao(Connection c) {
        super.checkIn(c);
    }
}