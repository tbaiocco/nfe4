package com.master.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import com.master.ed.Carga_EntregaED;
import com.master.ed.EntregadorED;
import com.master.root.FormataDataBean;
import com.master.root.UnidadeBean;
import com.master.root.VeiculoBean;
import com.master.util.BancoUtil;
import com.master.util.Excecoes;
import com.master.util.bd.ExecutaSQL;

/**
 * @author André Valadas
 */
public class Carga_EntregaBD extends BancoUtil {

    private ExecutaSQL executasql;

    public Carga_EntregaBD(ExecutaSQL sql) {
        super(sql);
        this.executasql = sql;
    }

    public Carga_EntregaED inclui(Carga_EntregaED ed) throws Excecoes {

        String sql = null;
        try {
            ed.setOid_Carga_Entrega(getAutoIncremento("oid_Carga_Entrega", "Cargas_Entregas"));
            ed.setNR_Carga(getMaximo("NR_PROXIMA_CARGA_ENTREGA",
    	  	               			 "Parametros_Filiais",
    	  	               			 "oid_Unidade = "+ ed.getOid_Unidade()));	
            
            sql = " INSERT INTO Cargas_Entregas (" +
            	  "		 oid_Carga_Entrega" +
            	  "		,oid_Unidade" +
            	  "		,oid_Entregador" +
            	  "		,oid_Veiculo" +
            	  "		,NR_Carga" +
            	  "		,DT_Entrega) " +
            	  " VALUES (" +
            	  	ed.getOid_Carga_Entrega() +
            	  	"," + ed.getOid_Unidade() +
            	  	"," + ed.getOid_Entregador() +
            	  	",'" + ed.getOid_Veiculo() + "'" +
            	  	"," + ed.getNR_Carga() +
            	  	",'"+ ed.getDT_Entrega() + "')";
            
            executasql.executarUpdate(sql);
            //*** Atualiza Parametros_Filiais
            executasql.executarUpdate(" UPDATE Parametros_Filiais SET" +
            						  " NR_PROXIMA_CARGA_ENTREGA = " + (ed.getNR_Carga()+1) + 
            						  " WHERE oid_Unidade = "  + ed.getOid_Unidade());
        	return ed;
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "inclui()");
        }
    }

    public void deleta(Carga_EntregaED ed) throws Excecoes {

        String sql = null;
        try {
            sql = " DELETE FROM Cargas_Entregas " +
            	  " WHERE oid_Carga_Entrega = " + ed.getOid_Carga_Entrega();
            executasql.executarUpdate(sql);
            
            //*** Exclui Referencias
            sql = " UPDATE Pedidos SET " +
                  "     oid_Carga_Entrega = NULL" +
                  " WHERE oid_Carga_Entrega = " + ed.getOid_Carga_Entrega();
            executasql.executarUpdate(sql);
            
            sql = " UPDATE Notas_Fiscais SET " +
                  "     oid_Carga_Entrega = NULL" +
                  " WHERE oid_Carga_Entrega = " + ed.getOid_Carga_Entrega();
            executasql.executarUpdate(sql);
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "deleta()");
        }
    }
    
    public ArrayList lista(Carga_EntregaED ed) throws Excecoes {

        String sql = null;
        ResultSet res = null;
        Map cacheUni = new HashMap();
        Map cacheEnt = new HashMap();
        Map cacheVei = new HashMap();
        ArrayList list = new ArrayList();

        try {
            sql = " SELECT Cargas_Entregas.* " +
            	  " FROM Cargas_Entregas, Unidades, Entregadores, Veiculos " +
            	  " WHERE Cargas_Entregas.oid_Unidade = Unidades.oid_Unidade" +
            	  "   AND Cargas_Entregas.oid_Entregador = Entregadores.oid_Entregador " +
            	  "   AND Cargas_Entregas.oid_Veiculo = Veiculos.oid_Veiculo";
            	  
            if (ed.getOid_Carga_Entrega() > 0)
                sql += "   AND Cargas_Entregas.oid_Carga_Entrega = "+ed.getOid_Carga_Entrega();
            else {
                if (ed.getOid_Unidade() > 0)
                    sql += "   AND Cargas_Entregas.oid_Unidade = "+ed.getOid_Unidade();
                if (ed.getOid_Entregador() > 0)
                    sql += "   AND Cargas_Entregas.oid_Entregador = "+ed.getOid_Entregador();
                if (doValida(ed.getOid_Veiculo()))
                    sql += "   AND Cargas_Entregas.oid_Veiculo = '"+ed.getOid_Veiculo()+"'";
                if (ed.getNR_Carga() > 0)
                    sql += "   AND Cargas_Entregas.NR_Carga = "+ed.getNR_Carga();
                if (doValida(ed.getDT_Entrega()))
                    sql += "   AND Cargas_Entregas.DT_Entrega = "+getSQLDate(ed.getDT_Entrega());
                sql += " ORDER BY Cargas_Entregas.NR_Carga";
            }
            res = this.executasql.executarConsulta(sql);

            while (res.next())
            {
                Carga_EntregaED edVolta = new Carga_EntregaED();
          
                edVolta.setOid_Carga_Entrega(res.getInt("oid_Carga_Entrega"));
                edVolta.setOid_Unidade(res.getInt("oid_Unidade"));
                edVolta.setOid_Entregador(res.getInt("oid_Entregador"));
                edVolta.setOid_Veiculo(res.getString("oid_Veiculo"));
                //*** Carrega Dados
                if (edVolta.getOid_Unidade() > 0 && ed.isCarregaUnidade())
                {
                    if (!cacheUni.containsKey(String.valueOf(edVolta.getOid_Unidade())))
                        cacheUni.put(String.valueOf(edVolta.getOid_Unidade()), UnidadeBean.getByOID_Unidade(edVolta.getOid_Unidade()));
                    edVolta.edUnidade = (UnidadeBean) cacheUni.get(String.valueOf(edVolta.getOid_Unidade()));
                }                    
                if (edVolta.getOid_Entregador() > 0 && ed.isCarregaEntregador())
                {
                    if (!cacheEnt.containsKey(String.valueOf(edVolta.getOid_Entregador())))
                        cacheEnt.put(String.valueOf(edVolta.getOid_Entregador()), new EntregadorBD(executasql).getByRecord(new EntregadorED(edVolta.getOid_Entregador())));
                    edVolta.edEntregador = (EntregadorED)cacheEnt.get(String.valueOf(edVolta.getOid_Entregador()));
                }
                if (doValida(edVolta.getOid_Veiculo()) && ed.isCarregaVeiculo())
                {   
                    if (!cacheVei.containsKey(edVolta.getOid_Veiculo()))
                    {
                        edVolta.edVeiculo.setOID(edVolta.getOid_Veiculo());
                        cacheVei.put(edVolta.getOid_Veiculo(), VeiculoBean.getByRecord(edVolta.edVeiculo));
                    }
                    edVolta.edVeiculo = (VeiculoBean) cacheVei.get(edVolta.getOid_Veiculo());
                }                
                edVolta.setNR_Carga(res.getInt("NR_Carga"));
                edVolta.setDT_Entrega(FormataDataBean.getFormatDate(res.getString("DT_Entrega")));
                
                edVolta.setNR_QT_Pedidos(res.getInt("NR_QT_Pedidos"));
                edVolta.setNR_QT_Notas(res.getInt("NR_QT_Notas"));
                edVolta.setVL_Carga(res.getDouble("VL_Carga"));
                edVolta.setPesagem_Carga(res.getDouble("Pesagem_Carga"));
                list.add(edVolta);
            }
            return list;
            
        } catch (Exception exc) {
            throw new Excecoes(exc.getMessage(), exc, this.getClass().getName(), "lista()");
        } finally {
            closeResultset(res);
            cacheUni.clear();
            cacheEnt.clear();
            cacheVei.clear();
            cacheUni = null;
            cacheEnt = null;
            cacheVei = null;
        }
    }
        
    public Carga_EntregaED getByRecord(Carga_EntregaED ed) throws Excecoes {

        Iterator iterator = this.lista(ed).iterator();
        if (iterator.hasNext()) {
            return (Carga_EntregaED) iterator.next();            
        } else return new Carga_EntregaED();
    }
    
    //*** Atribui Valores
    public void updateTotais(int oid_Carga_Entrega) throws Excecoes {

        try {
            if (oid_Carga_Entrega < 1)
                throw new Excecoes("Carga Entrega não informada!");
            
            this.executasql.executarUpdate(" UPDATE Cargas_Entregas SET " +
                                           "     VL_Carga = (SELECT sum(VL_Total_Pedido) FROM Pedidos WHERE Pedidos.oid_Carga_Entrega = "+oid_Carga_Entrega+")" +
                                           "    ,Pesagem_Carga = (SELECT sum(ItePed.NR_Peso_Real) FROM Itens_Pedidos ItePed, Pedidos Ped WHERE ItePed.oid_Pedido = Ped.oid_Pedido AND Ped.oid_Carga_Entrega = "+oid_Carga_Entrega+")" +
                                           "    ,NR_QT_Pedidos = (SELECT count(*) FROM Pedidos WHERE Pedidos.oid_Carga_Entrega = "+oid_Carga_Entrega+")" +
                                           "    ,NR_QT_Notas = (SELECT count(*) FROM Notas_Fiscais WHERE Notas_Fiscais.oid_Carga_Entrega = "+oid_Carga_Entrega+")" +
                                           " WHERE oid_Carga_Entrega = "+oid_Carga_Entrega);
        } catch (SQLException e) {
            throw new Excecoes(e.getMessage(), this.getClass().getName(), "updateTotais()");
        }
    }
}