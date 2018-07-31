package com.master.bd;

/**
 * <p>Title: Entrega_VeiculoBD </p>
 * <p>Description: Cadastro, exclusão e alteração de Packing-list de veículos para Transporte.
 * cliente: Transp. Pellenz.</p>
 * <p>Copyright: ÊxitoLogística & MasterCOM (c) 2005</p>
 * <p>Company: ÊxitoLogística Consultoria e Sistemas Ltda.</p>
 * @author Teófilo Poletto Baiocco
 * @version 1.0
 */

import com.master.util.*;
import com.master.util.bd.*;
import com.master.ed.Entrega_VeiculoED;
import java.util.*;
import java.sql.*;

import com.master.root.FormataDataBean;

public class Entrega_VeiculoBD {

  private ExecutaSQL executasql;

  public Entrega_VeiculoBD(ExecutaSQL sql) {
    this.executasql = sql;
  }




    public Entrega_VeiculoED inclui(Entrega_VeiculoED ed) throws Excecoes{

        String sql = null;
        long valOid = 0;
        FormataDataBean dataFormatada = new FormataDataBean();

        try{
            
            ResultSet rs = executasql.executarConsulta("select max(OID_entrega_veiculo) as result from entregas_veiculos");
            while (rs.next()){
              valOid = rs.getLong("result") + 1;
            }
            ed.setOid_entrega_veiculo(valOid);

          sql = " insert into Entregas_Veiculos " +
          		" (oid_Entrega_Veiculo, dt_stamp, usuario_stamp, dm_stamp, " +
          		" dm_estepe, "+
          		" dm_velocimetro, "+
          		" dm_tacografo, "+
          		" dm_chave_roda, "+
          		" dm_encendedor, "+
          		" dm_tapa_radiador, "+
          		" dm_tapa_volante, "+
          		" dm_tapa_bateria, "+
          		" dm_emblemas, "+
          		" dm_extintor, "+
          		" dm_cric, "+
          		" dm_manguera, "+
          		" dm_radio, "+
          		" dm_triangulo, "+
          		" dm_prot_pedales, "+
          		" dm_antena, "+
          		" dm_llaves, "+
          		" dm_muela_arasta, "+
          		" dm_destornillador, "+
          		" dm_limp_para_brisa, "+
          		" dm_porta_vaso, "+
          		" dm_cabo_luz, "+
          		" dm_herramientas, "+
          		" dm_faroles_niebla, "+
          		" dm_aire_condicionado, "+
          		" dm_microfono, "+
          		" dm_tapete, "+
          		" dm_primero_socorros, "+
          		" dm_cabeceras, "+
          		" dm_video_cassete, "+
          		" dm_cafetera, "+
          		" dm_geladera, "+
          		" dm_m_tv, "+
          		" dm_m_radio, "+
          		" dm_m_video_cassete, "+
          		" dm_m_carroceria, "+
          		" dm_m_chasi, "+
          		" dm_plato_llantas, "+
          		" dm_ventilador, "+
          		" dm_cambao, "+
          		" dm_retrovisor, "+
          		" dm_colchon, "+
          		" dm_cortina, "+
          		" dm_tv, "+
          		" dm_controle_remoto, "+
          		" nr_estepe, "+
          		" nr_velocimetro, "+
          		" nr_tacografo, "+
          		" nr_chave_roda, "+
          		" nr_encendedor, "+
          		" nr_tapa_radiador, "+
          		" nr_tapa_volante, "+
          		" nr_tapa_bateria, "+
          		" nr_emblemas, "+
          		" nr_extintor, "+
          		" nr_cric, "+
          		" nr_manguera, "+
          		" nr_radio, "+
          		" nr_triangulo, "+
          		" nr_prot_pedales, "+
          		" nr_antena, "+
          		" nr_llaves, "+
          		" nr_muela_arasta, "+
          		" nr_destornillador, "+
          		" nr_limp_para_brisa, "+
          		" nr_porta_vaso, "+
          		" nr_cabo_luz, "+
          		" nr_herramientas, "+
          		" nr_faroles_niebla, "+
          		" nr_aire_condicionado, "+
          		" nr_microfono, "+
          		" nr_tapete, "+
          		" nr_primero_socorros, "+
          		" nr_cabeceras, "+
          		" nr_video_cassete, "+
          		" nr_cafetera, "+
          		" nr_geladera, "+
          		" nr_m_tv, "+
          		" nr_m_radio, "+
          		" nr_m_video_cassete, "+
          		" nr_m_carroceria, "+
          		" nr_m_chasi, "+
          		" nr_plato_llantas, "+
          		" nr_ventilador, "+
          		" nr_cambao, "+
          		" nr_retrovisor, "+
          		" nr_colchon, "+
          		" nr_cortina, "+
          		" nr_tv, "+
          		" nr_controle_remoto, "+
          		" tx_outros, "+
          		" tx_para_brisa, "+
          		" tx_pneus, "+
          		" tx_bancos, "+
          		" tx_limpieza, "+
          		" tx_carroceria, "+
          		" tx_espejos, "+
          		" tx_faroles, "+
          		" tx_frente_general, "+
          		" tx_observacoes, "+
          		" nm_marca, "+
          		" nm_modelo, "+
          		" nr_nota_fiscal, "+
          		" nr_carroceria, "+
          		" nr_chasi, "+
          		" dt_retirada ) values ";
          sql += "('" + ed.getOid_entrega_veiculo() + "','" + Data.getDataDMY() + "',' ', 'S', '" +
			      ed.getDm_estepe() +"','"+
			      ed.getDm_velocimetro() +"','"+
			      ed.getDm_tacografo() +"','"+
			      ed.getDm_chave_roda() +"','"+
			      ed.getDm_encendedor() +"','"+
			      ed.getDm_tapa_radiador() +"','"+
			      ed.getDm_tapa_volante() +"','"+
			      ed.getDm_tapa_bateria() +"','"+
			      ed.getDm_emblemas() +"','"+
			      ed.getDm_extintor() +"','"+
			      ed.getDm_cric() +"','"+
			      ed.getDm_manguera() +"','"+
			      ed.getDm_radio() +"','"+
			      ed.getDm_triangulo() +"','"+
			      ed.getDm_prot_pedales() +"','"+
			      ed.getDm_antena() +"','"+
			      ed.getDm_llaves() +"','"+
			      ed.getDm_muela_arasta() +"','"+
			      ed.getDm_destornillador() +"','"+
			      ed.getDm_limp_para_brisa() +"','"+
			      ed.getDm_porta_vaso() +"','"+
			      ed.getDm_cabo_luz() +"','"+
			      ed.getDm_herramientas() +"','"+
			      ed.getDm_faroles_niebla() +"','"+
			      ed.getDm_aire_condicionado() +"','"+
			      ed.getDm_microfono() +"','"+
			      ed.getDm_tapete() +"','"+
			      ed.getDm_primero_socorros() +"','"+
			      ed.getDm_cabeceras() +"','"+
			      ed.getDm_video_cassete() +"','"+
			      ed.getDm_cafetera() +"','"+
			      ed.getDm_geladera() +"','"+
			      ed.getDm_m_tv() +"','"+
			      ed.getDm_m_radio() +"','"+
			      ed.getDm_m_video_cassete() +"','"+
			      ed.getDm_m_carroceria() +"','"+
			      ed.getDm_m_chasi() +"','"+
			      ed.getDm_plato_llantas() +"','"+
			      ed.getDm_ventilador() +"','"+
			      ed.getDm_cambao() +"','"+
			      ed.getDm_retrovisor() +"','"+
			      ed.getDm_colchon() +"','"+
			      ed.getDm_cortina() +"','"+
			      ed.getDm_tv() +"','"+
			      ed.getDm_controle_remoto() +"','"+
			      ed.getNr_estepe() +"','"+
			      ed.getNr_velocimetro() +"','"+
			      ed.getNr_tacografo() +"','"+
			      ed.getNr_chave_roda() +"','"+
			      ed.getNr_encendedor() +"','"+
			      ed.getNr_tapa_radiador() +"','"+
			      ed.getNr_tapa_volante() +"','"+
			      ed.getNr_tapa_bateria() +"','"+
			      ed.getNr_emblemas() +"','"+
			      ed.getNr_extintor() +"','"+
			      ed.getNr_cric() +"','"+
			      ed.getNr_manguera() +"','"+
			      ed.getNr_radio() +"','"+
			      ed.getNr_triangulo() +"','"+
			      ed.getNr_prot_pedales() +"','"+
			      ed.getNr_antena() +"','"+
			      ed.getNr_llaves() +"','"+
			      ed.getNr_muela_arasta() +"','"+
			      ed.getNr_destornillador() +"','"+
			      ed.getNr_limp_para_brisa() +"','"+
			      ed.getNr_porta_vaso() +"','"+
			      ed.getNr_cabo_luz() +"','"+
			      ed.getNr_herramientas() +"','"+
			      ed.getNr_faroles_niebla() +"','"+
			      ed.getNr_aire_condicionado() +"','"+
			      ed.getNr_microfono() +"','"+
			      ed.getNr_tapete() +"','"+
			      ed.getNr_primero_socorros() +"','"+
			      ed.getNr_cabeceras() +"','"+
			      ed.getNr_video_cassete() +"','"+
			      ed.getNr_cafetera() +"','"+
			      ed.getNr_geladera() +"','"+
			      ed.getNr_m_tv() +"','"+
			      ed.getNr_m_radio() +"','"+
			      ed.getNr_m_video_cassete() +"','"+
			      ed.getNr_m_carroceria() +"','"+
			      ed.getNr_m_chasi() +"','"+
			      ed.getNr_plato_llantas() +"','"+
			      ed.getNr_ventilador() +"','"+
			      ed.getNr_cambao() +"','"+
			      ed.getNr_retrovisor() +"','"+
			      ed.getNr_colchon() +"','"+
			      ed.getNr_cortina() +"','"+
			      ed.getNr_tv() +"','"+
			      ed.getNr_controle_remoto() +"','"+
			      ed.getTx_outros() +"','"+
			      ed.getTx_para_brisa() +"','"+
			      ed.getTx_pneus() +"','"+
			      ed.getTx_bancos() +"','"+
			      ed.getTx_limpieza() +"','"+
			      ed.getTx_carroceria() +"','"+
			      ed.getTx_espejos() +"','"+
			      ed.getTx_faroles() +"','"+
			      ed.getTx_frente_general() +"','"+
			      ed.getTx_observacoes() +"','"+
			      ed.getNm_marca() +"','"+
			      ed.getNm_modelo() +"','"+
			      ed.getNr_nota_fiscal() +"','"+
			      ed.getNr_carroceria() +"','"+
			      ed.getNr_chasi() +"','"+
			      ed.getDt_retirada() + "')";

// System.out.print(sql);

          int i = executasql.executarUpdate(sql);
        }
        catch(Exception exc){
            exc.printStackTrace();
          Excecoes excecoes = new Excecoes();
          excecoes.setClasse(this.getClass().getName());
          excecoes.setMetodo("inclui_Entrega_Veiculo");
          excecoes.setExc(exc);
          throw excecoes;
        }
        return ed;
      }

    public void altera(Entrega_VeiculoED ed) throws Excecoes{

        String sql = null;
        FormataDataBean dataFormatada = new FormataDataBean();

        try{
          sql = "update Entregas_Veiculos "+
                " set " +
                " Dm_estepe = '" + ed.getDm_estepe() +"',"+
                " Dm_velocimetro = '" + ed.getDm_velocimetro() +"',"+
                " Dm_tacografo = '" + ed.getDm_tacografo() +"',"+
                " Dm_chave_roda = '" + ed.getDm_chave_roda() +"',"+
                " Dm_encendedor = '" + ed.getDm_encendedor() +"',"+
                " Dm_tapa_radiador = '" + ed.getDm_tapa_radiador() +"',"+
                " Dm_tapa_volante = '" + ed.getDm_tapa_volante() +"',"+
                " Dm_tapa_bateria = '" + ed.getDm_tapa_bateria() +"',"+
                " Dm_emblemas = '" + ed.getDm_emblemas() +"',"+
                " Dm_extintor = '" + ed.getDm_extintor() +"',"+
                " Dm_cric = '" + ed.getDm_cric() +"',"+
                " Dm_manguera = '" + ed.getDm_manguera() +"',"+
                " Dm_radio = '" + ed.getDm_radio() +"',"+
                " Dm_triangulo = '" + ed.getDm_triangulo() +"',"+
                " Dm_prot_pedales = '" + ed.getDm_prot_pedales() +"',"+
                " Dm_antena = '" + ed.getDm_antena() +"',"+
                " Dm_llaves = '" + ed.getDm_llaves() +"',"+
                " Dm_muela_arasta = '" + ed.getDm_muela_arasta() +"',"+
                " Dm_destornillador = '" + ed.getDm_destornillador() +"',"+
                " Dm_limp_para_brisa = '" + ed.getDm_limp_para_brisa() +"',"+
                " Dm_porta_vaso = '" + ed.getDm_porta_vaso() +"',"+
                " Dm_cabo_luz = '" + ed.getDm_cabo_luz() +"',"+
                " Dm_herramientas = '" + ed.getDm_herramientas() +"',"+
                " Dm_faroles_niebla = '" + ed.getDm_faroles_niebla() +"',"+
                " Dm_aire_condicionado = '" + ed.getDm_aire_condicionado() +"',"+
                " Dm_microfono = '" + ed.getDm_microfono() +"',"+
                " Dm_tapete = '" + ed.getDm_tapete() +"',"+
                " Dm_primero_socorros = '" + ed.getDm_primero_socorros() +"',"+
                " Dm_cabeceras = '" + ed.getDm_cabeceras() +"',"+
                " Dm_video_cassete = '" + ed.getDm_video_cassete() +"',"+
                " Dm_cafetera = '" + ed.getDm_cafetera() +"',"+
                " Dm_geladera = '" + ed.getDm_geladera() +"',"+
                " Dm_m_tv = '" + ed.getDm_m_tv() +"',"+
                " Dm_m_radio = '" + ed.getDm_m_radio() +"',"+
                " Dm_m_video_cassete = '" + ed.getDm_m_video_cassete() +"',"+
                " Dm_m_carroceria = '" + ed.getDm_m_carroceria() +"',"+
                " Dm_m_chasi = '" + ed.getDm_m_chasi() +"',"+
                " Dm_plato_llantas = '" + ed.getDm_plato_llantas() +"',"+
                " Dm_ventilador = '" + ed.getDm_ventilador() +"',"+
                " Dm_cambao = '" + ed.getDm_cambao() +"',"+
                " Dm_retrovisor = '" + ed.getDm_retrovisor() +"',"+
                " Dm_colchon = '" + ed.getDm_colchon() +"',"+
                " Dm_cortina = '" + ed.getDm_cortina() +"',"+
                " Dm_tv = '" + ed.getDm_tv() +"',"+
                " Dm_controle_remoto = '" + ed.getDm_controle_remoto() +"',"+
                " Nr_estepe = '" + ed.getNr_estepe() +"',"+
                " Nr_velocimetro = '" + ed.getNr_velocimetro() +"',"+
                " Nr_tacografo = '" + ed.getNr_tacografo() +"',"+
                " Nr_chave_roda = '" + ed.getNr_chave_roda() +"',"+
                " Nr_encendedor = '" + ed.getNr_encendedor() +"',"+
                " Nr_tapa_radiador = '" + ed.getNr_tapa_radiador() +"',"+
                " Nr_tapa_volante = '" + ed.getNr_tapa_volante() +"',"+
                " Nr_tapa_bateria = '" + ed.getNr_tapa_bateria() +"',"+
                " Nr_emblemas = '" + ed.getNr_emblemas() +"',"+
                " Nr_extintor = '" + ed.getNr_extintor() +"',"+
                " Nr_cric = '" + ed.getNr_cric() +"',"+
                " Nr_manguera = '" + ed.getNr_manguera() +"',"+
                " Nr_radio = '" + ed.getNr_radio() +"',"+
                " Nr_triangulo = '" + ed.getNr_triangulo() +"',"+
                " Nr_prot_pedales = '" + ed.getNr_prot_pedales() +"',"+
                " Nr_antena = '" + ed.getNr_antena() +"',"+
                " Nr_llaves = '" + ed.getNr_llaves() +"',"+
                " Nr_muela_arasta = '" + ed.getNr_muela_arasta() +"',"+
                " Nr_destornillador = '" + ed.getNr_destornillador() +"',"+
                " Nr_limp_para_brisa = '" + ed.getNr_limp_para_brisa() +"',"+
                " Nr_porta_vaso = '" + ed.getNr_porta_vaso() +"',"+
                " Nr_cabo_luz = '" + ed.getNr_cabo_luz() +"',"+
                " Nr_herramientas = '" + ed.getNr_herramientas() +"',"+
                " Nr_faroles_niebla = '" + ed.getNr_faroles_niebla() +"',"+
                " Nr_aire_condicionado = '" + ed.getNr_aire_condicionado() +"',"+
                " Nr_microfono = '" + ed.getNr_microfono() +"',"+
                " Nr_tapete = '" + ed.getNr_tapete() +"',"+
                " Nr_primero_socorros = '" + ed.getNr_primero_socorros() +"',"+
                " Nr_cabeceras = '" + ed.getNr_cabeceras() +"',"+
                " Nr_video_cassete = '" + ed.getNr_video_cassete() +"',"+
                " Nr_cafetera = '" + ed.getNr_cafetera() +"',"+
                " Nr_geladera = '" + ed.getNr_geladera() +"',"+
                " Nr_m_tv = '" + ed.getNr_m_tv() +"',"+
                " Nr_m_radio = '" + ed.getNr_m_radio() +"',"+
                " Nr_m_video_cassete = '" + ed.getNr_m_video_cassete() +"',"+
                " Nr_m_carroceria = '" + ed.getNr_m_carroceria() +"',"+
                " Nr_m_chasi = '" + ed.getNr_m_chasi() +"',"+
                " Nr_plato_llantas = '" + ed.getNr_plato_llantas() +"',"+
                " Nr_ventilador = '" + ed.getNr_ventilador() +"',"+
                " Nr_cambao = '" + ed.getNr_cambao() +"',"+
                " Nr_retrovisor = '" + ed.getNr_retrovisor() +"',"+
                " Nr_colchon = '" + ed.getNr_colchon() +"',"+
                " Nr_cortina = '" + ed.getNr_cortina() +"',"+
                " Nr_tv = '" + ed.getNr_tv() +"',"+
                " Nr_controle_remoto = '" + ed.getNr_controle_remoto() +"',"+
                " Tx_outros = '" + ed.getTx_outros() +"',"+
                " Tx_para_brisa = '" + ed.getTx_para_brisa() +"',"+
                " Tx_pneus = '" + ed.getTx_pneus() +"',"+
                " Tx_bancos = '" + ed.getTx_bancos() +"',"+
                " Tx_limpieza = '" + ed.getTx_limpieza() +"',"+
                " Tx_carroceria = '" + ed.getTx_carroceria() +"',"+
                " Tx_espejos = '" + ed.getTx_espejos() +"',"+
                " Tx_faroles = '" + ed.getTx_faroles() +"',"+
                " Tx_frente_general = '" + ed.getTx_frente_general() +"',"+
                " Tx_observacoes = '" + ed.getTx_observacoes() +"',"+
                " Nm_marca = '" + ed.getNm_marca() +"',"+
                " Nm_modelo = '" + ed.getNm_modelo() +"',"+
                " Nr_nota_fiscal = '" + ed.getNr_nota_fiscal() +"',"+
                " Nr_carroceria = '" + ed.getNr_carroceria() +"',"+
                " Nr_chasi = '" + ed.getNr_chasi() +"',"+
                " Dt_retirada = '" + ed.getDt_retirada() + "' "+
                " Where OID_Entrega_Veiculo ='"+ed.getOid_entrega_veiculo()+"'";
	    // System.out.println(sql);

          int i = executasql.executarUpdate(sql);
        }

        catch(Exception exc){
          Excecoes excecoes = new Excecoes();
          excecoes.setClasse(this.getClass().getName());
          excecoes.setMensagem("Erro ao alterar");
          excecoes.setMetodo("altera_Entregas_Veiculos()");
          excecoes.setExc(exc);
          throw excecoes;
        }
      }

    public void deleta(Entrega_VeiculoED ed) throws Excecoes{

        String sql = null;
        String DM_Impresso = null;

        try{
          sql = "delete from Entregas_Veiculos Where oid_Entrega_Veiculo ='"+ed.getOid_entrega_veiculo() + "'";

          int i = executasql.executarUpdate(sql);
        }

        catch(Exception exc){
          Excecoes excecoes = new Excecoes();
          excecoes.setClasse(this.getClass().getName());
          excecoes.setMetodo("deleta_Entregas_Veiculos()");
          excecoes.setExc(exc);
          throw excecoes;
        }
      }

    public ArrayList lista(Entrega_VeiculoED ed)
    throws Excecoes{

        String sql = null;
        ArrayList list = new ArrayList();
        FormataDataBean dataFormatada = new FormataDataBean();

    // System.out.println("Lista Entrega bd");

        try{
          sql = "Select * "+
                " From  Entregas_Veiculos "+
                " Where 1=1 " ;

              if (JavaUtil.doValida(ed.getNm_marca())){
                  sql += " AND nm_marca = '" + ed.getNm_marca() + "'";
              }
              if (JavaUtil.doValida(ed.getNm_modelo())){
                  sql += " AND Nm_modelo = '" + ed.getNm_modelo() + "'";
              }
              if (JavaUtil.doValida(ed.getDt_retirada())){
                  sql += " AND Dt_retirada = '" + ed.getDt_retirada() + "'";
              }
              if (JavaUtil.doValida(ed.getNr_carroceria())){
                  sql += " AND Nr_carroceria = '" + ed.getNr_carroceria() + "'";
              }
              if (JavaUtil.doValida(ed.getNr_chasi())){
                  sql += " AND Nr_chasi = '" + ed.getNr_chasi() + "'";
              }
              if (JavaUtil.doValida(ed.getNr_nota_fiscal())){
                  sql += " AND Nr_nota_fiscal = '" + ed.getNr_nota_fiscal() + "'";
              }

          sql += " ORDER BY oid_Entrega_veiculo";

// System.out.println("Lista Entrega bd - " + sql);


          ResultSet res = null;
          res = this.executasql.executarConsulta(sql);
          double valor = 0;
          //popula
          while (res.next()){
// System.out.println("Lista Entrega wh 1");

	    	Entrega_VeiculoED edVolta = new Entrega_VeiculoED();
            edVolta.setOid_entrega_veiculo(res.getLong("oid_entrega_Veiculo"));
            edVolta.setNm_marca(res.getString("Nm_marca"));
            edVolta.setNm_modelo(res.getString("Nm_modelo"));
            edVolta.setNr_nota_fiscal(res.getString("Nr_nota_fiscal"));
            edVolta.setNr_carroceria(res.getString("Nr_carroceria"));
            edVolta.setNr_chasi(res.getString("Nr_chasi"));
            edVolta.setDt_retirada(res.getString("Dt_retirada"));
            
            list.add(edVolta);
          }
        }
        catch(SQLException e){
          throw new Excecoes(e.getMessage(), e, getClass().getName(), "lista(Entrega_VeiculoED ed)");
        }

        return list;
      }



    public Entrega_VeiculoED getByRecord(Entrega_VeiculoED ed)throws Excecoes{

        String sql = null;
        Entrega_VeiculoED edVolta = new Entrega_VeiculoED();
        FormataDataBean dataFormatada = new FormataDataBean();

        try{
          sql = "Select oid_Entrega_Veiculo, "+
                " dm_estepe, "+
          		" dm_velocimetro, "+
          		" dm_tacografo, "+
          		" dm_chave_roda, "+
          		" dm_encendedor, "+
          		" dm_tapa_radiador, "+
          		" dm_tapa_volante, "+
          		" dm_tapa_bateria, "+
          		" dm_emblemas, "+
          		" dm_extintor, "+
          		" dm_cric, "+
          		" dm_manguera, "+
          		" dm_radio, "+
          		" dm_triangulo, "+
          		" dm_prot_pedales, "+
          		" dm_antena, "+
          		" dm_llaves, "+
          		" dm_muela_arasta, "+
          		" dm_destornillador, "+
          		" dm_limp_para_brisa, "+
          		" dm_porta_vaso, "+
          		" dm_cabo_luz, "+
          		" dm_herramientas, "+
          		" dm_faroles_niebla, "+
          		" dm_aire_condicionado, "+
          		" dm_microfono, "+
          		" dm_tapete, "+
          		" dm_primero_socorros, "+
          		" dm_cabeceras, "+
          		" dm_video_cassete, "+
          		" dm_cafetera, "+
          		" dm_geladera, "+
          		" dm_m_tv, "+
          		" dm_m_radio, "+
          		" dm_m_video_cassete, "+
          		" dm_m_carroceria, "+
          		" dm_m_chasi, "+
          		" dm_plato_llantas, "+
          		" dm_ventilador, "+
          		" dm_cambao, "+
          		" dm_retrovisor, "+
          		" dm_colchon, "+
          		" dm_cortina, "+
          		" dm_tv, "+
          		" dm_controle_remoto, "+
          		" nr_estepe, "+
          		" nr_velocimetro, "+
          		" nr_tacografo, "+
          		" nr_chave_roda, "+
          		" nr_encendedor, "+
          		" nr_tapa_radiador, "+
          		" nr_tapa_volante, "+
          		" nr_tapa_bateria, "+
          		" nr_emblemas, "+
          		" nr_extintor, "+
          		" nr_cric, "+
          		" nr_manguera, "+
          		" nr_radio, "+
          		" nr_triangulo, "+
          		" nr_prot_pedales, "+
          		" nr_antena, "+
          		" nr_llaves, "+
          		" nr_muela_arasta, "+
          		" nr_destornillador, "+
          		" nr_limp_para_brisa, "+
          		" nr_porta_vaso, "+
          		" nr_cabo_luz, "+
          		" nr_herramientas, "+
          		" nr_faroles_niebla, "+
          		" nr_aire_condicionado, "+
          		" nr_microfono, "+
          		" nr_tapete, "+
          		" nr_primero_socorros, "+
          		" nr_cabeceras, "+
          		" nr_video_cassete, "+
          		" nr_cafetera, "+
          		" nr_geladera, "+
          		" nr_m_tv, "+
          		" nr_m_radio, "+
          		" nr_m_video_cassete, "+
          		" nr_m_carroceria, "+
          		" nr_m_chasi, "+
          		" nr_plato_llantas, "+
          		" nr_ventilador, "+
          		" nr_cambao, "+
          		" nr_retrovisor, "+
          		" nr_colchon, "+
          		" nr_cortina, "+
          		" nr_tv, "+
          		" nr_controle_remoto, "+
          		" tx_outros, "+
          		" tx_para_brisa, "+
          		" tx_pneus, "+
          		" tx_bancos, "+
          		" tx_limpieza, "+
          		" tx_carroceria, "+
          		" tx_espejos, "+
          		" tx_faroles, "+
          		" tx_frente_general, "+
          		" tx_observacoes, "+
          		" nm_marca, "+
          		" nm_modelo, "+
          		" nr_nota_fiscal, "+
          		" nr_carroceria, "+
          		" nr_chasi, "+
          		" dt_retirada "+
                " From  Entregas_Veiculos "+
                " Where 1=1 " ;
	      if (JavaUtil.doValida(String.valueOf(ed.getOid_entrega_veiculo()))){
	            sql += " AND oid_entrega_Veiculo = '" + ed.getOid_entrega_veiculo() + "'";
	          }

	      ResultSet res = null;
	      res = this.executasql.executarConsulta(sql);
	      double valor = 0;
	      //popula
	      while (res.next()){
	          edVolta.setOid_entrega_veiculo(res.getLong(1));
	          edVolta.setDm_estepe(res.getString(2));
	          edVolta.setDm_velocimetro(res.getString(3));
	          edVolta.setDm_tacografo(res.getString(4));
	          edVolta.setDm_chave_roda(res.getString(5));
	          edVolta.setDm_encendedor(res.getString(6));
	          edVolta.setDm_tapa_radiador(res.getString(7));
	          edVolta.setDm_tapa_volante(res.getString(8));
	          edVolta.setDm_tapa_bateria(res.getString(9));
	          edVolta.setDm_emblemas(res.getString(10));
	          edVolta.setDm_extintor(res.getString(11));
	          edVolta.setDm_cric(res.getString(12));
	          edVolta.setDm_manguera(res.getString(13));
	          edVolta.setDm_radio(res.getString(14));
	          edVolta.setDm_triangulo(res.getString(15));
	          edVolta.setDm_prot_pedales(res.getString(16));
	          edVolta.setDm_antena(res.getString(17));
	          edVolta.setDm_llaves(res.getString(18));
	          edVolta.setDm_muela_arasta(res.getString(19));
	          edVolta.setDm_destornillador(res.getString(20));
	          edVolta.setDm_limp_para_brisa(res.getString(21));
	          edVolta.setDm_porta_vaso(res.getString(22));
	          edVolta.setDm_cabo_luz(res.getString(23));
	          edVolta.setDm_herramientas(res.getString(24));
	          edVolta.setDm_faroles_niebla(res.getString(25));
	          edVolta.setDm_aire_condicionado(res.getString(26));
	          edVolta.setDm_microfono(res.getString(27));
	          edVolta.setDm_tapete(res.getString(28));
	          edVolta.setDm_primero_socorros(res.getString(29));
	          edVolta.setDm_cabeceras(res.getString(30));
	          edVolta.setDm_video_cassete(res.getString(31));
	          edVolta.setDm_cafetera(res.getString(32));
	          edVolta.setDm_geladera(res.getString(33));
	          edVolta.setDm_m_tv(res.getString(34));
	          edVolta.setDm_m_radio(res.getString(35));
	          edVolta.setDm_m_video_cassete(res.getString(36));
	          edVolta.setDm_m_carroceria(res.getString(37));
	          edVolta.setDm_m_chasi(res.getString(38));
	          edVolta.setDm_plato_llantas(res.getString(39));
	          edVolta.setDm_ventilador(res.getString(40));
	          edVolta.setDm_cambao(res.getString(41));
	          edVolta.setDm_retrovisor(res.getString(42));
	          edVolta.setDm_colchon(res.getString(43));
	          edVolta.setDm_cortina(res.getString(44));
	          edVolta.setDm_tv(res.getString(45));
	          edVolta.setDm_controle_remoto(res.getString(46));
	          edVolta.setNr_estepe(res.getDouble(47));
	          edVolta.setNr_velocimetro(res.getDouble(48));
	          edVolta.setNr_tacografo(res.getDouble(49));
	          edVolta.setNr_chave_roda(res.getDouble(50));
	          edVolta.setNr_encendedor(res.getDouble(51));
	          edVolta.setNr_tapa_radiador(res.getDouble(52));
	          edVolta.setNr_tapa_volante(res.getDouble(53));
	          edVolta.setNr_tapa_bateria(res.getDouble(54));
	          edVolta.setNr_emblemas(res.getDouble(55));
	          edVolta.setNr_extintor(res.getDouble(56));
	          edVolta.setNr_cric(res.getDouble(57));
	          edVolta.setNr_manguera(res.getDouble(58));
	          edVolta.setNr_radio(res.getDouble(59));
	          edVolta.setNr_triangulo(res.getDouble(60));
	          edVolta.setNr_prot_pedales(res.getDouble(61));
	          edVolta.setNr_antena(res.getDouble(62));
	          edVolta.setNr_llaves(res.getDouble(63));
	          edVolta.setNr_muela_arasta(res.getDouble(64));
	          edVolta.setNr_destornillador(res.getDouble(65));
	          edVolta.setNr_limp_para_brisa(res.getDouble(66));
	          edVolta.setNr_porta_vaso(res.getDouble(67));
	          edVolta.setNr_cabo_luz(res.getDouble(68));
	          edVolta.setNr_herramientas(res.getDouble(69));
	          edVolta.setNr_faroles_niebla(res.getDouble(70));
	          edVolta.setNr_aire_condicionado(res.getDouble(71));
	          edVolta.setNr_microfono(res.getDouble(72));
	          edVolta.setNr_tapete(res.getDouble(73));
	          edVolta.setNr_primero_socorros(res.getDouble(74));
	          edVolta.setNr_cabeceras(res.getDouble(75));
	          edVolta.setNr_video_cassete(res.getDouble(76));
	          edVolta.setNr_cafetera(res.getDouble(77));
	          edVolta.setNr_geladera(res.getDouble(78));
	          edVolta.setNr_m_tv(res.getDouble(79));
	          edVolta.setNr_m_radio(res.getDouble(80));
	          edVolta.setNr_m_video_cassete(res.getDouble(81));
	          edVolta.setNr_m_carroceria(res.getDouble(82));
	          edVolta.setNr_m_chasi(res.getDouble(83));
	          edVolta.setNr_plato_llantas(res.getDouble(84));
	          edVolta.setNr_ventilador(res.getDouble(85));
	          edVolta.setNr_cambao(res.getDouble(86));
	          edVolta.setNr_retrovisor(res.getDouble(87));
	          edVolta.setNr_colchon(res.getDouble(88));
	          edVolta.setNr_cortina(res.getDouble(89));
	          edVolta.setNr_tv(res.getDouble(90));
	          edVolta.setNr_controle_remoto(res.getDouble(91));
	          edVolta.setTx_outros(res.getString(92));
	          edVolta.setTx_para_brisa(res.getString(93));
	          edVolta.setTx_pneus(res.getString(94));
	          edVolta.setTx_bancos(res.getString(95));
	          edVolta.setTx_limpieza(res.getString(96));
	          edVolta.setTx_carroceria(res.getString(97));
	          edVolta.setTx_espejos(res.getString(98));
	          edVolta.setTx_faroles(res.getString(99));
	          edVolta.setTx_frente_general(res.getString(100));
	          edVolta.setTx_observacoes(res.getString(101));
	          edVolta.setNm_marca(res.getString(102));
	          edVolta.setNm_modelo(res.getString(103));
	          edVolta.setNr_nota_fiscal(res.getString(104));
	          edVolta.setNr_carroceria(res.getString(105));
	          edVolta.setNr_chasi(res.getString(106));
	          edVolta.setDt_retirada(res.getString(107));
	      }
        }
        catch(Exception exc){
          Excecoes excecoes = new Excecoes();
          excecoes.setClasse(this.getClass().getName());
          excecoes.setMensagem("Erro ao selecionar");
          excecoes.setMetodo("getByRecord_Entrega_Veiculo()");
          excecoes.setExc(exc);
          throw excecoes;
        }

        return edVolta;
      }

}
