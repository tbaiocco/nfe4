package com.master.root;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.master.util.BancoUtil;
import com.master.util.JavaUtil;

import auth.OracleConnection2;

public class Tipo_PedidoBean extends JavaUtil {

    private String CD_Tipo_Pedido;
    private String NM_Tipo_Pedido;
    private String Usuario_Stamp;
    private String Dt_Stamp;
    private String Dm_Stamp;
    private int oid;
    private String DM_Tipo_Pedido;
    private String DM_Nota_Fiscal;
    private String CD_Grupo_CFO;

    public Tipo_PedidoBean() {
    }

    public String getDescDM_Tipo_Pedido() {
        if ("C".equals(DM_Tipo_Pedido))
            return "Compra";
        else if ("V".equals(DM_Tipo_Pedido))
            return "Venda";
        else if ("S".equals(DM_Tipo_Pedido))
            return "Serviço";
        else return "Não informado!";
    }
    
    public String getDescDM_Nota_Fiscal() {
        if ("I".equals(DM_Nota_Fiscal))
            return "Numera na Impressão";
        else if ("E".equals(DM_Nota_Fiscal))
            return "Exige na Entrada";
        else return "Não informado!";
    }
    
    public String getCD_Tipo_Pedido() {
        return CD_Tipo_Pedido;
    }

    public void setCD_Tipo_Pedido(String CD_Tipo_Pedido) {
        this.CD_Tipo_Pedido = CD_Tipo_Pedido;
    }

    public String getNM_Tipo_Pedido() {
        return NM_Tipo_Pedido;
    }

    public void setNM_Tipo_Pedido(String NM_Tipo_Pedido) {
        this.NM_Tipo_Pedido = NM_Tipo_Pedido;
    }

    public String getDM_Tipo_Pedido() {
        return DM_Tipo_Pedido;
    }

    public void setDM_Tipo_Pedido(String DM_Tipo_Pedido) {
        this.DM_Tipo_Pedido = DM_Tipo_Pedido;
    }

    /*
     * ---------------- Bloco Padrão para Todas Classes ------------------
     */
    public String getUsuario_Stamp() {
        return Usuario_Stamp;
    }

    public void setUsuario_Stamp(String Usuario_Stamp) {
        this.Usuario_Stamp = Usuario_Stamp;
    }

    public String getDt_Stamp() {
        return Dt_Stamp;
    }

    public void setDt_Stamp(String Dt_Stamp) {
        this.Dt_Stamp = Dt_Stamp;
    }

    public String getDm_Stamp() {
        return Dm_Stamp;
    }

    public void setDm_Stamp(String Dm_Stamp) {
        this.Dm_Stamp = Dm_Stamp;
    }

    public int getOID() {
        return oid;
    }

    public void setOID(int n) {
        this.oid = n;
    }

    public void insert() throws Exception {
        /*
         * Abre a conexão com o banco
         */
        Connection conn = null;
        try {
            // Pede uma conexão ao gerenciador do driver
            // passando como parâmetro o NM_Tipo_Pedido do DSN
            // o NM_Tipo_Pedido de usuário e a senha do banco.
            conn = OracleConnection2.getWEB();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        /*
         * Gera um novo código (OID)
         */
        try {
            Statement stmt = conn.createStatement();
            ResultSet cursor = stmt.executeQuery("SELECT MAX(OID_Tipo_Pedido) FROM Tipos_Pedidos");

            while (cursor.next()) {
                int oid = cursor.getInt(1);
                setOID(oid + 1);
            }
            cursor.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        /*
         * Define o insert.
         */
        StringBuffer buff = new StringBuffer();
        buff.append("INSERT INTO Tipos_Pedidos (OID_Tipo_Pedido, CD_Tipo_Pedido, NM_Tipo_Pedido, DM_Tipo_Pedido, CD_Grupo_CFO, Dt_Stamp, Usuario_Stamp, Dm_Stamp, DM_Nota_Fiscal) ");
        buff.append("VALUES (?,?,?,?,?,?,?,?,?)");
        /*
         * Define os dados do SQL e executa o insert no banco.
         */
        try {
            PreparedStatement pstmt = conn.prepareStatement(buff.toString());
            pstmt.setInt(1, getOID());
            pstmt.setString(2, getCD_Tipo_Pedido());
            pstmt.setString(3, getNM_Tipo_Pedido());
            pstmt.setString(4, getDM_Tipo_Pedido());
            pstmt.setString(5, getCD_Grupo_CFO());
            pstmt.setString(6, getDt_Stamp());
            pstmt.setString(7, getUsuario_Stamp());
            pstmt.setString(8, getDm_Stamp());
            pstmt.setString(9, getDM_Nota_Fiscal());
            pstmt.executeUpdate();
        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
            throw e;
        }
        /*
         * Faz o commit e fecha a conexão.
         */
        try {
            conn.commit();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void update() throws Exception {
        
        //*** Valida se código já existe
        new BancoUtil().doValidaUpdate(getOID(), getCD_Tipo_Pedido(), "Tipos_Pedidos", "OID_Tipo_Pedido", "CD_Tipo_Pedido");
        /*
         * Abre a conexão com o banco
         */
        Connection conn = null;
        try {
            // Pede uma conexão ao gerenciador do driver
            // passando como parâmetro o NM_Tipo_Pedido do DSN
            // o NM_Tipo_Pedido de usuário e a senha do banco.
            conn = OracleConnection2.getWEB();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        /*
         * Define o update.
         */
        StringBuffer buff = new StringBuffer();
        buff.append("UPDATE Tipos_Pedidos SET CD_Tipo_Pedido=?, NM_Tipo_Pedido=?, DM_Tipo_Pedido=?, CD_Grupo_CFO=?, Dt_Stamp=?, Usuario_Stamp=?, Dm_Stamp=?, DM_Nota_Fiscal=? ");
        buff.append("WHERE OID_Tipo_Pedido=?");
        /*
         * Define os dados do SQL e executa o insert no banco.
         */
        try {
            PreparedStatement pstmt = conn.prepareStatement(buff.toString());
            pstmt.setString(1, getCD_Tipo_Pedido());
            pstmt.setString(2, getNM_Tipo_Pedido());
            pstmt.setString(3, getDM_Tipo_Pedido());
            pstmt.setString(4, getCD_Grupo_CFO());
            pstmt.setString(5, getDt_Stamp());
            pstmt.setString(6, getUsuario_Stamp());
            pstmt.setString(7, getDm_Stamp());
            pstmt.setString(8, getDM_Nota_Fiscal());
            pstmt.setInt(9, getOID());
            pstmt.executeUpdate();
        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
            throw e;
        }
        /*
         * Faz o commit e fecha a conexão.
         */
        try {
            conn.commit();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void delete() throws Exception {
        /*
         * Abre a conexão com o banco
         */
        Connection conn = null;
        try {
            // Pede uma conexão ao gerenciador do driver
            // passando como parâmetro o NM_Tipo_Pedido do DSN
            // o NM_Tipo_Pedido de usuário e a senha do banco.
            conn = OracleConnection2.getWEB();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        /*
         * Define o DELETE.
         */
        StringBuffer buff = new StringBuffer();
        buff.append("DELETE FROM Tipos_Pedidos ");
        buff.append("WHERE OID_Tipo_Pedido=?");
        /*
         * Define os dados do SQL e executa o insert no banco.
         */
        try {
            PreparedStatement pstmt = conn.prepareStatement(buff.toString());
            pstmt.setInt(1, getOID());
            pstmt.executeUpdate();
        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
            throw e;
        }
        /*
         * Faz o commit e fecha a conexão.
         */
        try {
            conn.commit();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static final Tipo_PedidoBean getByOID(int oid) throws Exception {
        /*
         * Abre a conexão com o banco
         */
        Connection conn = null;
        try {
            // Pede uma conexão ao gerenciador do driver
            // passando como parâmetro o NM_Tipo_Pedido do DSN
            // o NM_Tipo_Pedido de usuário e a senha do banco.
            conn = OracleConnection2.getWEB();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        Tipo_PedidoBean p = new Tipo_PedidoBean();
        try {
            StringBuffer buff = new StringBuffer();
            buff.append("SELECT OID_Tipo_Pedido, ");
            buff.append("	CD_Tipo_Pedido, ");
            buff.append("	NM_Tipo_Pedido, ");
            buff.append("	DM_Tipo_Pedido, ");
            buff.append("	DM_Nota_Fiscal, ");
            buff.append("	CD_Grupo_CFO 	");
            buff.append("FROM Tipos_Pedidos ");
            buff.append("WHERE OID_Tipo_Pedido= ");
            buff.append(oid);

            Statement stmt = conn.createStatement();
            ResultSet cursor = stmt.executeQuery(buff.toString());

            while (cursor.next()) {
                p.setOID(cursor.getInt(1));
                p.setCD_Tipo_Pedido(cursor.getString(2));
                p.setNM_Tipo_Pedido(cursor.getString(3));
                p.setDM_Tipo_Pedido(cursor.getString(4));
                p.setDM_Nota_Fiscal(cursor.getString(5));
                p.setCD_Grupo_CFO(cursor.getString(6));
            }
            cursor.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }

    public static final Tipo_PedidoBean getByCD_Tipo_Pedido(String CD_Tipo_Pedido) throws Exception {
        /*
         * Abre a conexão com o banco
         */
        Connection conn = null;
        try {
            // Pede uma conexão ao gerenciador do driver
            // passando como parâmetro o NM_Tipo_Pedido do DSN
            // o NM_Tipo_Pedido de usuário e a senha do banco.
            conn = OracleConnection2.getWEB();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        Tipo_PedidoBean p = new Tipo_PedidoBean();
        try {
            StringBuffer buff = new StringBuffer();
            buff.append("SELECT OID_Tipo_Pedido, ");
            buff.append("	CD_Tipo_Pedido, ");
            buff.append("	NM_Tipo_Pedido, ");
            buff.append("	DM_Tipo_Pedido, ");
            buff.append("	DM_Nota_Fiscal, ");
            buff.append("	CD_Grupo_CFO ");
            buff.append("FROM Tipos_Pedidos ");
            buff.append("WHERE CD_Tipo_Pedido= '");
            buff.append(CD_Tipo_Pedido);
            buff.append("'");

            Statement stmt = conn.createStatement();
            ResultSet cursor = stmt.executeQuery(buff.toString());

            while (cursor.next()) {
                p.setOID(cursor.getInt(1));
                p.setCD_Tipo_Pedido(cursor.getString(2));
                p.setNM_Tipo_Pedido(cursor.getString(3));
                p.setDM_Tipo_Pedido(cursor.getString(4));
                p.setDM_Nota_Fiscal(cursor.getString(5));
                p.setCD_Grupo_CFO(cursor.getString(6));
            }
            cursor.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }

    public static final List getByNM_Tipo_Pedido(String NM_Tipo_Pedido) throws Exception {
        /*
         * Abre a conexão com o banco
         */
        Connection conn = null;
        try {
            // Pede uma conexão ao gerenciador do driver
            // passando como parâmetro o NM_Tipo_Pedido do DSN
            // o NM_Tipo_Pedido de usuário e a senha do banco.
            conn = OracleConnection2.getWEB();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        List Tipos_Pedidos_Lista = new ArrayList();
        try {
            StringBuffer buff = new StringBuffer();
            buff.append("SELECT OID_Tipo_Pedido, ");
            buff.append("	CD_Tipo_Pedido, ");
            buff.append("	NM_Tipo_Pedido, ");
            buff.append("	DM_Tipo_Pedido, ");
            buff.append("	DM_Nota_Fiscal, ");
            buff.append("	CD_Grupo_CFO ");
            buff.append("FROM Tipos_Pedidos ");
            buff.append("WHERE NM_Tipo_Pedido LIKE'");
            buff.append(NM_Tipo_Pedido);
            buff.append("%'");
            buff.append(" ORDER BY CD_Tipo_Pedido");

            Statement stmt = conn.createStatement();
            ResultSet cursor = stmt.executeQuery(buff.toString());

            while (cursor.next()) {
                Tipo_PedidoBean p = new Tipo_PedidoBean();
                p.setOID(cursor.getInt(1));
                p.setCD_Tipo_Pedido(cursor.getString(2));
                p.setNM_Tipo_Pedido(cursor.getString(3));
                p.setDM_Tipo_Pedido(cursor.getString(4));
                p.setDM_Nota_Fiscal(cursor.getString(5));
                p.setCD_Grupo_CFO(cursor.getString(6));
                Tipos_Pedidos_Lista.add(p);
            }
            cursor.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Tipos_Pedidos_Lista;
    }

    public static final List getByTipo_Pedido(String CD_Tipo_Pedido, String NM_Tipo_Pedido, String DM_Tipo_Pedido) throws Exception {
        /*
         * Abre a conexão com o banco
         */
        Connection conn = null;
        try {
            // Pede uma conexão ao gerenciador do driver
            // passando como parâmetro o NM_Tipo_Pedido do DSN
            // o NM_Tipo_Pedido de usuário e a senha do banco.
            conn = OracleConnection2.getWEB();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        List Tipos_Pedidos_Lista = new ArrayList();
        try {
            StringBuffer buff = new StringBuffer();
            buff.append("SELECT OID_Tipo_Pedido, ");
            buff.append("	CD_Tipo_Pedido, ");
            buff.append("	NM_Tipo_Pedido, ");
            buff.append("	DM_Tipo_Pedido, ");
            buff.append("	DM_Nota_Fiscal, ");
            buff.append("	CD_Grupo_CFO 	");
            buff.append("FROM Tipos_Pedidos ");
            buff.append("WHERE 1 = 1");
            if (JavaUtil.doValida(CD_Tipo_Pedido))
                buff.append(" AND CD_Tipo_Pedido = '" + CD_Tipo_Pedido + "'");
            if (JavaUtil.doValida(NM_Tipo_Pedido))
                buff.append(" AND NM_Tipo_Pedido LIKE '" + NM_Tipo_Pedido + "%'");
            if (JavaUtil.doValida(DM_Tipo_Pedido))
                buff.append(" AND DM_Tipo_Pedido = '" + DM_Tipo_Pedido + "'");

            Statement stmt = conn.createStatement();
            ResultSet cursor = stmt.executeQuery(buff.toString());

            while (cursor.next()) {
                Tipo_PedidoBean p = new Tipo_PedidoBean();
                p.setOID(cursor.getInt(1));
                p.setCD_Tipo_Pedido(cursor.getString(2));
                p.setNM_Tipo_Pedido(cursor.getString(3));
                p.setDM_Tipo_Pedido(cursor.getString(4));
                p.setDM_Nota_Fiscal(cursor.getString(5));
                p.setCD_Grupo_CFO(cursor.getString(6));
                Tipos_Pedidos_Lista.add(p);
            }
            cursor.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Tipos_Pedidos_Lista;
    }

    public static final Tipo_PedidoBean getByTipo_Pedido2(String CD_Tipo_Pedido, String DM_Tipo_Pedido) throws Exception {
        /*
         * Abre a conexão com o banco
         */
        Connection conn = null;
        try {
            // Pede uma conexão ao gerenciador do driver
            // passando como parâmetro o NM_Tipo_Pedido do DSN
            // o NM_Tipo_Pedido de usuário e a senha do banco.
            conn = OracleConnection2.getWEB();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        Tipo_PedidoBean p = new Tipo_PedidoBean();
        try {
            StringBuffer buff = new StringBuffer();
            buff.append("SELECT OID_Tipo_Pedido, ");
            buff.append("	CD_Tipo_Pedido, ");
            buff.append("	NM_Tipo_Pedido, ");
            buff.append("	DM_Tipo_Pedido, ");
            buff.append("	DM_Nota_Fiscal, ");
            buff.append("	CD_Grupo_CFO 	");
            buff.append("FROM Tipos_Pedidos ");
            buff.append("WHERE 1 = 1");
            if (JavaUtil.doValida(CD_Tipo_Pedido))
                buff.append(" AND CD_Tipo_Pedido = '" + CD_Tipo_Pedido + "'");
            if (JavaUtil.doValida(DM_Tipo_Pedido))
                buff.append(" AND DM_Tipo_Pedido = '" + DM_Tipo_Pedido + "'");

            Statement stmt = conn.createStatement();
            ResultSet cursor = stmt.executeQuery(buff.toString());

            if (cursor.next()) {
                p.setOID(cursor.getInt(1));
                p.setCD_Tipo_Pedido(cursor.getString(2));
                p.setNM_Tipo_Pedido(cursor.getString(3));
                p.setDM_Tipo_Pedido(cursor.getString(4));
                p.setDM_Nota_Fiscal(cursor.getString(5));
                p.setCD_Grupo_CFO(cursor.getString(6));
            }
            cursor.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }

    public static final List getAll() throws Exception {
        Connection conn = null;
        try {
            // Pede uma conexão ao gerenciador do driver
            // passando como parâmetro o nome do DSN
            // o nome de usuário e a senha do banco.
            conn = OracleConnection2.getWEB();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        List Tipos_Pedidos_Lista = new ArrayList();
        try {
            StringBuffer buff = new StringBuffer();
            buff.append("SELECT OID_Tipo_Pedido, ");
            buff.append("	CD_Tipo_Pedido, ");
            buff.append("	NM_Tipo_Pedido, ");
            buff.append("	DM_Tipo_Pedido, ");
            buff.append("	DM_Nota_Fiscal, ");
            buff.append("	CD_Grupo_CFO 	");
            buff.append("FROM Tipos_Pedidos ");
            buff.append("ORDER BY CD_Tipo_Pedido");

            Statement stmt = conn.createStatement();
            ResultSet cursor = stmt.executeQuery(buff.toString());

            while (cursor.next()) {
                Tipo_PedidoBean p = new Tipo_PedidoBean();
                p.setOID(cursor.getInt(1));
                p.setCD_Tipo_Pedido(cursor.getString(2));
                p.setNM_Tipo_Pedido(cursor.getString(3));
                p.setDM_Tipo_Pedido(cursor.getString(4));
                p.setDM_Nota_Fiscal(cursor.getString(5));
                p.setCD_Grupo_CFO(cursor.getString(6));
                Tipos_Pedidos_Lista.add(p);
            }
            cursor.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Tipos_Pedidos_Lista;
    }

    public String getCD_Grupo_CFO() {
        return CD_Grupo_CFO;
    }

    public void setCD_Grupo_CFO(String grupo_CFO) {
        CD_Grupo_CFO = grupo_CFO;
    }

    public String getDM_Nota_Fiscal() {
        return DM_Nota_Fiscal;
    }
    public void setDM_Nota_Fiscal(String nota_Fiscal) {
        DM_Nota_Fiscal = nota_Fiscal;
    }
}