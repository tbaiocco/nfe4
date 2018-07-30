package com.master.iu;
/**
 * <p>Title: cad507Bean </p>
 * <p>Description: Cadastro, exclusão e alteração de Packing-list de veículos para Transporte.
 * cliente: Transp. Pellenz.</p>
 * <p>Copyright: ÊxitoLogística & MasterCOM (c) 2005</p>
 * <p>Company: ÊxitoLogística Consultoria e Sistemas Ltda.</p>
 * @author Teófilo Poletto Baiocco
 * @version 1.0
 */

import javax.servlet.http.*;
import com.master.rn.Entrega_VeiculoRN;
import com.master.ed.Entrega_VeiculoED;
import com.master.util.Excecoes;
import com.master.util.JavaUtil;

import java.util.*;
import com.master.root.FormataDataBean;
import com.master.util.Data;

public class cad507Bean {


    public Entrega_VeiculoED inclui(HttpServletRequest request)throws Excecoes{

        try{
            Entrega_VeiculoRN Entrega_VeiculoRN = new Entrega_VeiculoRN();
            Entrega_VeiculoED edVolta = new Entrega_VeiculoED();
            

            edVolta.setDm_estepe(request.getParameter("FT_DM_Estepe"));
            edVolta.setDm_velocimetro(request.getParameter("FT_DM_Velocimetro"));
            edVolta.setDm_tacografo(request.getParameter("FT_DM_Tacografo"));
            edVolta.setDm_chave_roda(request.getParameter("FT_DM_Chave_Roda"));
            edVolta.setDm_encendedor(request.getParameter("FT_DM_Encendedor"));
            edVolta.setDm_tapa_radiador(request.getParameter("FT_DM_Tapa_Radiador"));
            edVolta.setDm_tapa_volante(request.getParameter("FT_DM_Tapa_Volante"));
            edVolta.setDm_tapa_bateria(request.getParameter("FT_DM_Tapa_Bateria"));
            edVolta.setDm_emblemas(request.getParameter("FT_DM_Emblemas"));
            edVolta.setDm_extintor(request.getParameter("FT_DM_Extintor"));
            edVolta.setDm_cric(request.getParameter("FT_DM_Cric"));
            edVolta.setDm_manguera(request.getParameter("FT_DM_Manguera"));
            edVolta.setDm_radio(request.getParameter("FT_DM_Radio"));
            edVolta.setDm_triangulo(request.getParameter("FT_DM_Triangulo"));
            edVolta.setDm_prot_pedales(request.getParameter("FT_DM_Prot_Pedales"));
            edVolta.setDm_antena(request.getParameter("FT_DM_Antena"));
            edVolta.setDm_llaves(request.getParameter("FT_DM_Llaves"));
            edVolta.setDm_muela_arasta(request.getParameter("FT_DM_Muela_Arasta"));
            edVolta.setDm_destornillador(request.getParameter("FT_DM_Destornillador"));
            edVolta.setDm_limp_para_brisa(request.getParameter("FT_DM_Limp_Para_Brisa"));
            edVolta.setDm_porta_vaso(request.getParameter("FT_DM_Porta_Vaso"));
            edVolta.setDm_cabo_luz(request.getParameter("FT_DM_Cabo_Luz"));
            edVolta.setDm_herramientas(request.getParameter("FT_DM_Herramientas"));
            edVolta.setDm_faroles_niebla(request.getParameter("FT_DM_Faroles_Niebla"));
            edVolta.setDm_aire_condicionado(request.getParameter("FT_DM_Aire_Condicionado"));
            edVolta.setDm_microfono(request.getParameter("FT_DM_Microfono"));
            edVolta.setDm_tapete(request.getParameter("FT_DM_Tapete"));
            edVolta.setDm_primero_socorros(request.getParameter("FT_DM_Primero_Socorros"));
            edVolta.setDm_cabeceras(request.getParameter("FT_DM_Cabeceras"));
            edVolta.setDm_video_cassete(request.getParameter("FT_DM_Video_Cassete"));
            edVolta.setDm_cafetera(request.getParameter("FT_DM_Cafetera"));
            edVolta.setDm_geladera(request.getParameter("FT_DM_Geladera"));
            edVolta.setDm_m_tv(request.getParameter("FT_DM_M_TV"));
            edVolta.setDm_m_radio(request.getParameter("FT_DM_M_Radio"));
            edVolta.setDm_m_video_cassete(request.getParameter("FT_DM_M_Video_Cassete"));
            edVolta.setDm_m_carroceria(request.getParameter("FT_DM_M_Carroceria"));
            edVolta.setDm_m_chasi(request.getParameter("FT_DM_M_Chasi"));
            edVolta.setDm_plato_llantas(request.getParameter("FT_DM_Plato_Llantas"));
            edVolta.setDm_ventilador(request.getParameter("FT_DM_Ventilador"));
            edVolta.setDm_cambao(request.getParameter("FT_DM_Cambao"));
            edVolta.setDm_retrovisor(request.getParameter("FT_DM_Retrovisor"));
            edVolta.setDm_colchon(request.getParameter("FT_DM_Colchon"));
            edVolta.setDm_cortina(request.getParameter("FT_DM_Cortina"));
            edVolta.setDm_tv(request.getParameter("FT_DM_TV"));
            edVolta.setDm_controle_remoto(request.getParameter("FT_DM_Controle_Remoto"));
            
            if(JavaUtil.doValida(request.getParameter("FT_NR_Estepe"))) edVolta.setNr_estepe(Double.parseDouble(request.getParameter("FT_NR_Estepe")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Velocimetro"))) edVolta.setNr_velocimetro(Double.parseDouble(request.getParameter("FT_NR_Velocimetro")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Tacografo"))) edVolta.setNr_tacografo(Double.parseDouble(request.getParameter("FT_NR_Tacografo")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Chave_Roda"))) edVolta.setNr_chave_roda(Double.parseDouble(request.getParameter("FT_NR_Chave_Roda")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Encendedor"))) edVolta.setNr_encendedor(Double.parseDouble(request.getParameter("FT_NR_Encendedor")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Tapa_Radiador"))) edVolta.setNr_tapa_radiador(Double.parseDouble(request.getParameter("FT_NR_Tapa_Radiador")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Tapa_Volante"))) edVolta.setNr_tapa_volante(Double.parseDouble(request.getParameter("FT_NR_Tapa_Volante")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Tapa_Bateria"))) edVolta.setNr_tapa_bateria(Double.parseDouble(request.getParameter("FT_NR_Tapa_Bateria")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Emblemas"))) edVolta.setNr_emblemas(Double.parseDouble(request.getParameter("FT_NR_Emblemas")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Extintor"))) edVolta.setNr_extintor(Double.parseDouble(request.getParameter("FT_NR_Extintor")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Cric"))) edVolta.setNr_cric(Double.parseDouble(request.getParameter("FT_NR_Cric")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Manguera"))) edVolta.setNr_manguera(Double.parseDouble(request.getParameter("FT_NR_Manguera")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Radio"))) edVolta.setNr_radio(Double.parseDouble(request.getParameter("FT_NR_Radio")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Triangulo"))) edVolta.setNr_triangulo(Double.parseDouble(request.getParameter("FT_NR_Triangulo")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Prot_Pedales"))) edVolta.setNr_prot_pedales(Double.parseDouble(request.getParameter("FT_NR_Prot_Pedales")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Antena"))) edVolta.setNr_antena(Double.parseDouble(request.getParameter("FT_NR_Antena")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Llaves"))) edVolta.setNr_llaves(Double.parseDouble(request.getParameter("FT_NR_Llaves")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Muela_Arasta"))) edVolta.setNr_muela_arasta(Double.parseDouble(request.getParameter("FT_NR_Muela_Arasta")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Destornillador"))) edVolta.setNr_destornillador(Double.parseDouble(request.getParameter("FT_NR_Destornillador")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Limp_Para_Brisa"))) edVolta.setNr_limp_para_brisa(Double.parseDouble(request.getParameter("FT_NR_Limp_Para_Brisa")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Porta_Vaso"))) edVolta.setNr_porta_vaso(Double.parseDouble(request.getParameter("FT_NR_Porta_Vaso")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Cabo_Luz"))) edVolta.setNr_cabo_luz(Double.parseDouble(request.getParameter("FT_NR_Cabo_Luz")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Herramientas"))) edVolta.setNr_herramientas(Double.parseDouble(request.getParameter("FT_NR_Herramientas")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Faroles_Niebla"))) edVolta.setNr_faroles_niebla(Double.parseDouble(request.getParameter("FT_NR_Faroles_Niebla")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Aire_Condicionado"))) edVolta.setNr_aire_condicionado(Double.parseDouble(request.getParameter("FT_NR_Aire_Condicionado")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Microfono"))) edVolta.setNr_microfono(Double.parseDouble(request.getParameter("FT_NR_Microfono")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Tapete"))) edVolta.setNr_tapete(Double.parseDouble(request.getParameter("FT_NR_Tapete")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Primero_Socorros"))) edVolta.setNr_primero_socorros(Double.parseDouble(request.getParameter("FT_NR_Primero_Socorros")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Cabeceras"))) edVolta.setNr_cabeceras(Double.parseDouble(request.getParameter("FT_NR_Cabeceras")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Video_Cassete"))) edVolta.setNr_video_cassete(Double.parseDouble(request.getParameter("FT_NR_Video_Cassete")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Cafetera"))) edVolta.setNr_cafetera(Double.parseDouble(request.getParameter("FT_NR_Cafetera")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Geladera"))) edVolta.setNr_geladera(Double.parseDouble(request.getParameter("FT_NR_Geladera")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_M_TV"))) edVolta.setNr_m_tv(Double.parseDouble(request.getParameter("FT_NR_M_TV")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_M_Radio"))) edVolta.setNr_m_radio(Double.parseDouble(request.getParameter("FT_NR_M_Radio")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_M_Video_Cassete"))) edVolta.setNr_m_video_cassete(Double.parseDouble(request.getParameter("FT_NR_M_Video_Cassete")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_M_Carroceria"))) edVolta.setNr_m_carroceria(Double.parseDouble(request.getParameter("FT_NR_M_Carroceria")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_M_Chasi"))) edVolta.setNr_m_chasi(Double.parseDouble(request.getParameter("FT_NR_M_Chasi")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Plato_Llantas"))) edVolta.setNr_plato_llantas(Double.parseDouble(request.getParameter("FT_NR_Plato_Llantas")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Ventilador"))) edVolta.setNr_ventilador(Double.parseDouble(request.getParameter("FT_NR_Ventilador")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Cambao"))) edVolta.setNr_cambao(Double.parseDouble(request.getParameter("FT_NR_Cambao")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Retrovisor"))) edVolta.setNr_retrovisor(Double.parseDouble(request.getParameter("FT_NR_Retrovisor")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Colchon"))) edVolta.setNr_colchon(Double.parseDouble(request.getParameter("FT_NR_Colchon")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Cortina"))) edVolta.setNr_cortina(Double.parseDouble(request.getParameter("FT_NR_Cortina")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_TV"))) edVolta.setNr_tv(Double.parseDouble(request.getParameter("FT_NR_TV")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Controle_Remoto"))) edVolta.setNr_controle_remoto(Double.parseDouble(request.getParameter("FT_NR_Controle_Remoto")));
            
            edVolta.setTx_outros(request.getParameter("FT_TX_Outros"));
            edVolta.setTx_para_brisa(request.getParameter("FT_TX_Para_Brisa"));
            edVolta.setTx_pneus(request.getParameter("FT_TX_Pneus"));
            edVolta.setTx_bancos(request.getParameter("FT_TX_Bancos"));
            edVolta.setTx_limpieza(request.getParameter("FT_TX_Limpieza"));
            edVolta.setTx_carroceria(request.getParameter("FT_TX_Carroceria"));
            edVolta.setTx_espejos(request.getParameter("FT_TX_Espejos"));
            edVolta.setTx_faroles(request.getParameter("FT_TX_Faroles"));
            edVolta.setTx_frente_general(request.getParameter("FT_TX_Frente_General"));
            edVolta.setTx_observacoes(request.getParameter("FT_TX_Observacoes"));
            edVolta.setNm_marca(request.getParameter("FT_NM_Marca"));
            edVolta.setNm_modelo(request.getParameter("FT_NM_Modelo"));
            edVolta.setNr_nota_fiscal(request.getParameter("FT_NR_Nota_Fiscal"));
            edVolta.setNr_carroceria(request.getParameter("FT_NR_Carroceria"));
            edVolta.setNr_chasi(request.getParameter("FT_NR_Chasi"));
            edVolta.setDt_retirada(request.getParameter("FT_DT_Retirada"));

            return new Entrega_VeiculoRN().inclui(edVolta);
            
        }catch (Excecoes exc){
            throw exc;
        }catch(Exception exc){
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("erro ao incluir");
            excecoes.setMetodo("inclui_VeiculoxAcessorio");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }


    public void altera(HttpServletRequest request)throws Excecoes{

        try{
            Entrega_VeiculoRN Entrega_VeiculoRN = new Entrega_VeiculoRN();
            Entrega_VeiculoED edVolta = new Entrega_VeiculoED();
            
            edVolta.setOid_entrega_veiculo(Long.parseLong(request.getParameter("oid_Entrega_Veiculo")));
            edVolta.setDm_estepe(request.getParameter("FT_DM_Estepe"));
            edVolta.setDm_velocimetro(request.getParameter("FT_DM_Velocimetro"));
            edVolta.setDm_tacografo(request.getParameter("FT_DM_Tacografo"));
            edVolta.setDm_chave_roda(request.getParameter("FT_DM_Chave_Roda"));
            edVolta.setDm_encendedor(request.getParameter("FT_DM_Encendedor"));
            edVolta.setDm_tapa_radiador(request.getParameter("FT_DM_Tapa_Radiador"));
            edVolta.setDm_tapa_volante(request.getParameter("FT_DM_Tapa_Volante"));
            edVolta.setDm_tapa_bateria(request.getParameter("FT_DM_Tapa_Bateria"));
            edVolta.setDm_emblemas(request.getParameter("FT_DM_Emblemas"));
            edVolta.setDm_extintor(request.getParameter("FT_DM_Extintor"));
            edVolta.setDm_cric(request.getParameter("FT_DM_Cric"));
            edVolta.setDm_manguera(request.getParameter("FT_DM_Manguera"));
            edVolta.setDm_radio(request.getParameter("FT_DM_Radio"));
            edVolta.setDm_triangulo(request.getParameter("FT_DM_Triangulo"));
            edVolta.setDm_prot_pedales(request.getParameter("FT_DM_Prot_Pedales"));
            edVolta.setDm_antena(request.getParameter("FT_DM_Antena"));
            edVolta.setDm_llaves(request.getParameter("FT_DM_Llaves"));
            edVolta.setDm_muela_arasta(request.getParameter("FT_DM_Muela_Arasta"));
            edVolta.setDm_destornillador(request.getParameter("FT_DM_Destornillador"));
            edVolta.setDm_limp_para_brisa(request.getParameter("FT_DM_Limp_Para_Brisa"));
            edVolta.setDm_porta_vaso(request.getParameter("FT_DM_Porta_Vaso"));
            edVolta.setDm_cabo_luz(request.getParameter("FT_DM_Cabo_Luz"));
            edVolta.setDm_herramientas(request.getParameter("FT_DM_Herramientas"));
            edVolta.setDm_faroles_niebla(request.getParameter("FT_DM_Faroles_Niebla"));
            edVolta.setDm_aire_condicionado(request.getParameter("FT_DM_Aire_Condicionado"));
            edVolta.setDm_microfono(request.getParameter("FT_DM_Microfono"));
            edVolta.setDm_tapete(request.getParameter("FT_DM_Tapete"));
            edVolta.setDm_primero_socorros(request.getParameter("FT_DM_Primero_Socorros"));
            edVolta.setDm_cabeceras(request.getParameter("FT_DM_Cabeceras"));
            edVolta.setDm_video_cassete(request.getParameter("FT_DM_Video_Cassete"));
            edVolta.setDm_cafetera(request.getParameter("FT_DM_Cafetera"));
            edVolta.setDm_geladera(request.getParameter("FT_DM_Geladera"));
            edVolta.setDm_m_tv(request.getParameter("FT_DM_M_TV"));
            edVolta.setDm_m_radio(request.getParameter("FT_DM_M_Radio"));
            edVolta.setDm_m_video_cassete(request.getParameter("FT_DM_M_Video_Cassete"));
            edVolta.setDm_m_carroceria(request.getParameter("FT_DM_M_Carroceria"));
            edVolta.setDm_m_chasi(request.getParameter("FT_DM_M_Chasi"));
            edVolta.setDm_plato_llantas(request.getParameter("FT_DM_Plato_Llantas"));
            edVolta.setDm_ventilador(request.getParameter("FT_DM_Ventilador"));
            edVolta.setDm_cambao(request.getParameter("FT_DM_Cambao"));
            edVolta.setDm_retrovisor(request.getParameter("FT_DM_Retrovisor"));
            edVolta.setDm_colchon(request.getParameter("FT_DM_Colchon"));
            edVolta.setDm_cortina(request.getParameter("FT_DM_Cortina"));
            edVolta.setDm_tv(request.getParameter("FT_DM_TV"));
            edVolta.setDm_controle_remoto(request.getParameter("FT_DM_Controle_Remoto"));
            
            if(JavaUtil.doValida(request.getParameter("FT_NR_Estepe"))) edVolta.setNr_estepe(Double.parseDouble(request.getParameter("FT_NR_Estepe")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Velocimetro"))) edVolta.setNr_velocimetro(Double.parseDouble(request.getParameter("FT_NR_Velocimetro")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Tacografo"))) edVolta.setNr_tacografo(Double.parseDouble(request.getParameter("FT_NR_Tacografo")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Chave_Roda"))) edVolta.setNr_chave_roda(Double.parseDouble(request.getParameter("FT_NR_Chave_Roda")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Encendedor"))) edVolta.setNr_encendedor(Double.parseDouble(request.getParameter("FT_NR_Encendedor")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Tapa_Radiador"))) edVolta.setNr_tapa_radiador(Double.parseDouble(request.getParameter("FT_NR_Tapa_Radiador")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Tapa_Volante"))) edVolta.setNr_tapa_volante(Double.parseDouble(request.getParameter("FT_NR_Tapa_Volante")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Tapa_Bateria"))) edVolta.setNr_tapa_bateria(Double.parseDouble(request.getParameter("FT_NR_Tapa_Bateria")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Emblemas"))) edVolta.setNr_emblemas(Double.parseDouble(request.getParameter("FT_NR_Emblemas")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Extintor"))) edVolta.setNr_extintor(Double.parseDouble(request.getParameter("FT_NR_Extintor")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Cric"))) edVolta.setNr_cric(Double.parseDouble(request.getParameter("FT_NR_Cric")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Manguera"))) edVolta.setNr_manguera(Double.parseDouble(request.getParameter("FT_NR_Manguera")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Radio"))) edVolta.setNr_radio(Double.parseDouble(request.getParameter("FT_NR_Radio")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Triangulo"))) edVolta.setNr_triangulo(Double.parseDouble(request.getParameter("FT_NR_Triangulo")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Prot_Pedales"))) edVolta.setNr_prot_pedales(Double.parseDouble(request.getParameter("FT_NR_Prot_Pedales")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Antena"))) edVolta.setNr_antena(Double.parseDouble(request.getParameter("FT_NR_Antena")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Llaves"))) edVolta.setNr_llaves(Double.parseDouble(request.getParameter("FT_NR_Llaves")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Muela_Arasta"))) edVolta.setNr_muela_arasta(Double.parseDouble(request.getParameter("FT_NR_Muela_Arasta")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Destornillador"))) edVolta.setNr_destornillador(Double.parseDouble(request.getParameter("FT_NR_Destornillador")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Limp_Para_Brisa"))) edVolta.setNr_limp_para_brisa(Double.parseDouble(request.getParameter("FT_NR_Limp_Para_Brisa")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Porta_Vaso"))) edVolta.setNr_porta_vaso(Double.parseDouble(request.getParameter("FT_NR_Porta_Vaso")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Cabo_Luz"))) edVolta.setNr_cabo_luz(Double.parseDouble(request.getParameter("FT_NR_Cabo_Luz")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Herramientas"))) edVolta.setNr_herramientas(Double.parseDouble(request.getParameter("FT_NR_Herramientas")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Faroles_Niebla"))) edVolta.setNr_faroles_niebla(Double.parseDouble(request.getParameter("FT_NR_Faroles_Niebla")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Aire_Condicionado"))) edVolta.setNr_aire_condicionado(Double.parseDouble(request.getParameter("FT_NR_Aire_Condicionado")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Microfono"))) edVolta.setNr_microfono(Double.parseDouble(request.getParameter("FT_NR_Microfono")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Tapete"))) edVolta.setNr_tapete(Double.parseDouble(request.getParameter("FT_NR_Tapete")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Primero_Socorros"))) edVolta.setNr_primero_socorros(Double.parseDouble(request.getParameter("FT_NR_Primero_Socorros")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Cabeceras"))) edVolta.setNr_cabeceras(Double.parseDouble(request.getParameter("FT_NR_Cabeceras")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Video_Cassete"))) edVolta.setNr_video_cassete(Double.parseDouble(request.getParameter("FT_NR_Video_Cassete")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Cafetera"))) edVolta.setNr_cafetera(Double.parseDouble(request.getParameter("FT_NR_Cafetera")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Geladera"))) edVolta.setNr_geladera(Double.parseDouble(request.getParameter("FT_NR_Geladera")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_M_TV"))) edVolta.setNr_m_tv(Double.parseDouble(request.getParameter("FT_NR_M_TV")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_M_Radio"))) edVolta.setNr_m_radio(Double.parseDouble(request.getParameter("FT_NR_M_Radio")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_M_Video_Cassete"))) edVolta.setNr_m_video_cassete(Double.parseDouble(request.getParameter("FT_NR_M_Video_Cassete")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_M_Carroceria"))) edVolta.setNr_m_carroceria(Double.parseDouble(request.getParameter("FT_NR_M_Carroceria")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_M_Chasi"))) edVolta.setNr_m_chasi(Double.parseDouble(request.getParameter("FT_NR_M_Chasi")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Plato_Llantas"))) edVolta.setNr_plato_llantas(Double.parseDouble(request.getParameter("FT_NR_Plato_Llantas")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Ventilador"))) edVolta.setNr_ventilador(Double.parseDouble(request.getParameter("FT_NR_Ventilador")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Cambao"))) edVolta.setNr_cambao(Double.parseDouble(request.getParameter("FT_NR_Cambao")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Retrovisor"))) edVolta.setNr_retrovisor(Double.parseDouble(request.getParameter("FT_NR_Retrovisor")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Colchon"))) edVolta.setNr_colchon(Double.parseDouble(request.getParameter("FT_NR_Colchon")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Cortina"))) edVolta.setNr_cortina(Double.parseDouble(request.getParameter("FT_NR_Cortina")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_TV"))) edVolta.setNr_tv(Double.parseDouble(request.getParameter("FT_NR_TV")));
            if(JavaUtil.doValida(request.getParameter("FT_NR_Controle_Remoto"))) edVolta.setNr_controle_remoto(Double.parseDouble(request.getParameter("FT_NR_Controle_Remoto")));
            
            edVolta.setTx_outros(request.getParameter("FT_TX_Outros"));
            edVolta.setTx_para_brisa(request.getParameter("FT_TX_Para_Brisa"));
            edVolta.setTx_pneus(request.getParameter("FT_TX_Pneus"));
            edVolta.setTx_bancos(request.getParameter("FT_TX_Bancos"));
            edVolta.setTx_limpieza(request.getParameter("FT_TX_Limpieza"));
            edVolta.setTx_carroceria(request.getParameter("FT_TX_Carroceria"));
            edVolta.setTx_espejos(request.getParameter("FT_TX_Espejos"));
            edVolta.setTx_faroles(request.getParameter("FT_TX_Faroles"));
            edVolta.setTx_frente_general(request.getParameter("FT_TX_Frente_General"));
            edVolta.setTx_observacoes(request.getParameter("FT_TX_Observacoes"));
            edVolta.setNm_marca(request.getParameter("FT_NM_Marca"));
            edVolta.setNm_modelo(request.getParameter("FT_NM_Modelo"));
            edVolta.setNr_nota_fiscal(request.getParameter("FT_NR_Nota_Fiscal"));
            edVolta.setNr_carroceria(request.getParameter("FT_NR_Carroceria"));
            edVolta.setNr_chasi(request.getParameter("FT_NR_Chasi"));
            edVolta.setDt_retirada(request.getParameter("FT_DT_Retirada"));
            
            Entrega_VeiculoRN.altera(edVolta);
            
        }catch (Excecoes exc){
            throw exc;
        }catch(Exception exc){
            exc.printStackTrace();
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("erro ao incluir");
            excecoes.setMetodo("altera_VeiculoxAcessorio");
            excecoes.setExc(exc);
            throw excecoes;
        }
     }

    public void deleta(HttpServletRequest request)throws Excecoes{

        try{
            Entrega_VeiculoRN Entrega_VeiculoRN = new Entrega_VeiculoRN();
            Entrega_VeiculoED edVolta = new Entrega_VeiculoED();
            
            edVolta.setOid_entrega_veiculo(Long.parseLong(request.getParameter("oid_Entrega_Veiculo")));
            Entrega_VeiculoRN.deleta(edVolta);
            
        }catch (Excecoes exc){
            throw exc;
        }catch(Exception exc){
            Excecoes excecoes = new Excecoes();
            excecoes.setClasse(this.getClass().getName());
            excecoes.setMensagem("erro ao excluir");
            excecoes.setMetodo("excluir_VeiculoxAcessorio");
            excecoes.setExc(exc);
            throw excecoes;
        }
    }

	  public Entrega_VeiculoED getByRecord(HttpServletRequest request)throws Excecoes{
	
	      Entrega_VeiculoRN Entrega_VeiculoRN = new Entrega_VeiculoRN();
	      Entrega_VeiculoED edVolta = new Entrega_VeiculoED();
	      
	      edVolta.setOid_entrega_veiculo(Long.parseLong(request.getParameter("oid_Entrega_Veiculo")));
	
	      return new Entrega_VeiculoRN().getByRecord(edVolta);
	
	  }
     
	  public ArrayList Lista(HttpServletRequest request)throws Excecoes{

	      Entrega_VeiculoRN Entrega_VeiculoRN = new Entrega_VeiculoRN();
          Entrega_VeiculoED edVolta = new Entrega_VeiculoED();
          
          if(JavaUtil.doValida(request.getParameter("FT_NM_Marca"))) edVolta.setNm_marca(request.getParameter("FT_NM_Marca"));
          if(JavaUtil.doValida(request.getParameter("FT_NM_Modelo")))edVolta.setNm_modelo(request.getParameter("FT_NM_Modelo"));
          if(JavaUtil.doValida(request.getParameter("FT_NR_Nota_Fiscal")))edVolta.setNr_nota_fiscal(request.getParameter("FT_NR_Nota_Fiscal"));
          if(JavaUtil.doValida(request.getParameter("FT_NR_Carroceria")))edVolta.setNr_carroceria(request.getParameter("FT_NR_Carroceria"));
          if(JavaUtil.doValida(request.getParameter("FT_NR_Chasi")))edVolta.setNr_chasi(request.getParameter("FT_NR_Chasi"));
          if(JavaUtil.doValida(request.getParameter("FT_DT_Retirada")))edVolta.setDt_retirada(request.getParameter("FT_DT_Retirada"));
          
	      return new Entrega_VeiculoRN().lista(edVolta);
	  }


  }
