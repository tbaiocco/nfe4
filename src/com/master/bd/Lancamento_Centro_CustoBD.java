package com.master.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import com.master.ed.Centro_CustoED;
import com.master.ed.Lancamento_Centro_CustoED;
import com.master.util.BancoUtil;
import com.master.util.Data;
import com.master.util.Excecoes;
import com.master.util.Mensagens;
import com.master.util.bd.ExecutaSQL;

public class Lancamento_Centro_CustoBD extends BancoUtil {

    private ExecutaSQL executasql;

    public Lancamento_Centro_CustoBD(ExecutaSQL sql) {
        super(sql);
        this.executasql = sql;
    }

    public Lancamento_Centro_CustoED inclui(Lancamento_Centro_CustoED ed) throws Excecoes {

        String sql = null;
        try {
            ed.setOid_Lancamento_Centro_Custo(getAutoIncremento("oid_Lancamento_Centro_Custo", "Lancamentos_Centros_Custos"));
            
            sql = " INSERT INTO Lancamentos_Centros_Custos (" +
                  "      oid_Lancamento_Centro_Custo" +
                  "     ,oid_Centro_Custo" +
                  "     ,oid_Nota_Fiscal" +
                  "     ,oid_lancamento" +
                  "     ,PE_Lancamento" +
                  "     ,VL_Lancamento" +
                  "     ,DM_Tipo_Lancamento" +
                  "     ,dt_stamp" +
                  "     ,usuario_stamp" +
                  "     ,dm_stamp) " +
                  " VALUES (" + 
                      ed.getOid_Lancamento_Centro_Custo() +
                      "," + ed.getOid_Centro_Custo() + 
                      ",'" + ed.getOid_Nota_Fiscal() + "'" +
                      "," + ed.getOid_Lancamento() + 
                      "," + ed.getPE_Lancamento() +
                      "," + ed.getVL_Lancamento() + 
                      ",'" + getValueDef(ed.getDM_Tipo_Lancamento(), "R") + "'" +
                      ",'" + ed.getDt_stamp() + "'" +
                      ",'" + ed.getUsuario_Stamp() + "'" +
                      ",'" + ed.getDm_Stamp() + "')";
            executasql.executarUpdate(sql);
            
            //*** Calcula Rateiro entre Nota_Fiscal X Lancamentos_Centros_Custos
            if (!"N".equals(ed.getDM_Tipo_Lancamento()) && doValida(ed.getOid_Nota_Fiscal()))
                this.calculaRateio(ed.getOid_Nota_Fiscal());
            
            return ed;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "inclui()");
        }
    }

    public void altera(Lancamento_Centro_CustoED ed) throws Excecoes {

        String sql = null;
        try {
            sql = " UPDATE Lancamentos_Centros_Custos SET " +
                  "      oid_Centro_Custo =" + ed.getOid_Centro_Custo() + 
                  "     ,PE_Lancamento =" + ed.getPE_Lancamento() + 
                  "     ,Vl_Lancamento = " + ed.getVL_Lancamento() + 
                  "     ,dt_stamp ='" + Data.getDataDMY() + "'" + 
                  "     ,usuario_stamp ='" + ed.getUsuario_Stamp() + "'" + 
                  "     ,dm_stamp =" + ed.getDm_Stamp() + 
                  " WHERE oid_Lancamento_Centro_Custo =" + ed.getOid_Lancamento_Centro_Custo();
            executasql.executarUpdate(sql);
            
            //*** Calcula Rateiro entre Nota_Fiscal X Lancamentos_Centros_Custos
            if (doValida(ed.getOid_Nota_Fiscal()))
                this.calculaRateio(ed.getOid_Nota_Fiscal());
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "altera()");
        }
    }

    public void deleta(Lancamento_Centro_CustoED ed) throws Excecoes {

        String sql = null;
        try {
            sql = " DELETE FROM Lancamentos_Centros_Custos " +
                  " WHERE oid_Lancamento_Centro_Custo =" + ed.getOid_Lancamento_Centro_Custo();
            executasql.executarUpdate(sql);
            
            //*** Calcula Rateiro entre Nota_Fiscal X Lancamentos_Centros_Custos
            if (doValida(ed.getOid_Nota_Fiscal()))
            {
                if ("N".equals(ed.getDM_Tipo_Lancamento()))
                {
                    sql = " DELETE FROM Lancamentos_Centros_Custos " +
                          " WHERE oid_Nota_Fiscal = '" + ed.getOid_Nota_Fiscal()+"'";
                    executasql.executarUpdate(sql);
                } else this.calculaRateio(ed.getOid_Nota_Fiscal());
            }
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "deleta()");
        }
    }

    public ArrayList lista(Lancamento_Centro_CustoED ed) throws Excecoes {

        String sql = null;
        ArrayList list = new ArrayList();
        try {
            sql = " SELECT * " +
                  " FROM Lancamentos_Centros_Custos " + 
                  " WHERE 1=1 ";

            if (ed.getOid_Lancamento_Centro_Custo() > 0)
            {
                sql += " AND oid_Lancamento_Centro_Custo = " + ed.getOid_Lancamento_Centro_Custo();
            } else {
                if (ed.getOid_Centro_Custo() > 0)
                    sql += " AND oid_Centro_Custo = " + ed.getOid_Centro_Custo();
                if (doValida(ed.getOid_Nota_Fiscal()))
                    sql += " AND oid_Nota_Fiscal = '" + ed.getOid_Nota_Fiscal() +"'";
                if (ed.getOid_Lancamento() > 0)
                    sql += " AND oid_Lancamento = " + ed.getOid_Lancamento();
            }
            sql += " ORDER BY oid_Lancamento_Centro_Custo";
            
            ResultSet res = this.executasql.executarConsulta(sql);
            while (res.next())
            {
                Lancamento_Centro_CustoED edVolta = new Lancamento_Centro_CustoED();
                edVolta.setOid_Lancamento_Centro_Custo(res.getInt("oid_Lancamento_Centro_Custo"));
                edVolta.setOid_Nota_Fiscal(res.getString("oid_Nota_Fiscal"));
                edVolta.setOid_Lancamento(res.getInt("oid_Lancamento"));
                edVolta.setOid_Centro_Custo(res.getInt("oid_Centro_Custo"));
                edVolta.setDM_Tipo_Lancamento(res.getString("DM_Tipo_Lancamento"));
                edVolta.setPE_Lancamento(res.getDouble("PE_Lancamento"));
                edVolta.setVL_Lancamento(res.getDouble("VL_Lancamento"));
                if (edVolta.getOid_Centro_Custo() > 0)
                    edVolta.edCentro = new Centro_CustoBD(executasql).getByRecord(new Centro_CustoED(new Integer(edVolta.getOid_Centro_Custo())));

                list.add(edVolta);
            }
            return list;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "lista()");
        }
    }

    public Lancamento_Centro_CustoED getByRecord(Lancamento_Centro_CustoED ed) throws Excecoes {

        try {
            Iterator it = this.lista(ed).iterator();
            if (it.hasNext())
                return (Lancamento_Centro_CustoED) it.next();
            else return new Lancamento_Centro_CustoED();
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "getByRecord()");
        }
    }
    
    public void calculaRateio(String oid_Nota_Fiscal) throws Excecoes {
        
        try {
            if (!doValida(oid_Nota_Fiscal))
                return;
            double vlNotaFiscal = getTableDoubleValue("VL_Liquido_Nota_Fiscal", "Notas_Fiscais", "oid_Nota_Fiscal = '"+oid_Nota_Fiscal+"'");
            double vlRateado = getTableDoubleValue("sum(VL_Lancamento)", "Lancamentos_Centros_Custos", "oid_Nota_Fiscal = '"+oid_Nota_Fiscal+"' AND DM_Tipo_Lancamento <> 'N'");
            
            if (vlRateado > vlNotaFiscal)
                throw new Mensagens("Valor dos Lançamentos não podem Ultrapassar o Valor Líquido da Nota Fiscal!");
            
            executasql.executarUpdate(" UPDATE Lancamentos_Centros_Custos SET" +
                                      "     VL_Lancamento = "+(vlNotaFiscal-vlRateado)+
                                      " WHERE oid_Nota_Fiscal = '"+oid_Nota_Fiscal+"'" +
                                      "   AND DM_Tipo_Lancamento = 'N'");
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "calculaRateio()");
        }
    }
}