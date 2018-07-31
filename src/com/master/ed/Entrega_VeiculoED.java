/*
 * Created on 18/06/2005
 */
package com.master.ed;

import com.master.root.FormataDataBean;


/**
 * @author Teofilo Poletto Baiocco
 *
 */
public class Entrega_VeiculoED extends MasterED {

    private long oid_entrega_veiculo; 
    private String dm_estepe; 
    private String dm_velocimetro; 
    private String dm_tacografo; 
    private String dm_chave_roda; 
    private String dm_encendedor; 
    private String dm_tapa_radiador; 
    private String dm_tapa_volante; 
    private String dm_tapa_bateria; 
    private String dm_emblemas; 
    private String dm_extintor; 
    private String dm_cric; 
    private String dm_manguera; 
    private String dm_radio; 
    private String dm_triangulo; 
    private String dm_prot_pedales; 
    private String dm_antena; 
    private String dm_llaves; 
    private String dm_muela_arasta; 
    private String dm_destornillador; 
    private String dm_limp_para_brisa; 
    private String dm_porta_vaso; 
    private String dm_cabo_luz; 
    private String dm_herramientas; 
    private String dm_faroles_niebla; 
    private String dm_aire_condicionado; 
    private String dm_microfono; 
    private String dm_tapete; 
    private String dm_primero_socorros; 
    private String dm_cabeceras; 
    private String dm_video_cassete; 
    private String dm_cafetera; 
    private String dm_geladera; 
    private String dm_m_tv; 
    private String dm_m_radio; 
    private String dm_m_video_cassete; 
    private String dm_m_carroceria; 
    private String dm_m_chasi; 
    private String dm_plato_llantas; 
    private String dm_ventilador; 
    private String dm_cambao; 
    private String dm_retrovisor; 
    private String dm_colchon; 
    private String dm_cortina; 
    private String dm_tv; 
    private String dm_controle_remoto; 
    private String tx_outros; 
    private String tx_para_brisa; 
    private String tx_pneus; 
    private String tx_bancos; 
    private String tx_limpieza; 
    private String tx_carroceria; 
    private String tx_espejos; 
    private String tx_faroles; 
    private String tx_frente_general; 
    private String tx_observacoes; 
    private String nm_marca; 
    private String nm_modelo; 
    private String nr_nota_fiscal; 
    private String nr_carroceria; 
    private String nr_chasi; 
    private String dt_retirada;
    
    private double nr_estepe;
    private double nr_velocimetro; 
    private double nr_tacografo; 
    private double nr_chave_roda; 
    private double nr_encendedor; 
    private double nr_tapa_radiador; 
    private double nr_tapa_volante; 
    private double nr_tapa_bateria; 
    private double nr_emblemas; 
    private double nr_extintor; 
    private double nr_cric; 
    private double nr_manguera; 
    private double nr_radio; 
    private double nr_triangulo; 
    private double nr_prot_pedales; 
    private double nr_antena; 
    private double nr_llaves; 
    private double nr_muela_arasta; 
    private double nr_destornillador; 
    private double nr_limp_para_brisa; 
    private double nr_porta_vaso; 
    private double nr_cabo_luz; 
    private double nr_herramientas; 
    private double nr_faroles_niebla; 
    private double nr_aire_condicionado; 
    private double nr_microfono; 
    private double nr_tapete; 
    private double nr_primero_socorros; 
    private double nr_cabeceras; 
    private double nr_video_cassete; 
    private double nr_cafetera; 
    private double nr_geladera; 
    private double nr_m_tv; 
    private double nr_m_radio; 
    private double nr_m_video_cassete; 
    private double nr_m_carroceria; 
    private double nr_m_chasi; 
    private double nr_plato_llantas; 
    private double nr_ventilador; 
    private double nr_cambao; 
    private double nr_retrovisor; 
    private double nr_colchon; 
    private double nr_cortina; 
    private double nr_tv; 
    private double nr_controle_remoto;
    
    public Entrega_VeiculoED() {
    }
    
    public String getDm_aire_condicionado() {
        return dm_aire_condicionado;
    }
    public void setDm_aire_condicionado(String dm_aire_condicionado) {
        this.dm_aire_condicionado = dm_aire_condicionado;
    }
    public String getDm_antena() {
        return dm_antena;
    }
    public void setDm_antena(String dm_antena) {
        this.dm_antena = dm_antena;
    }
    public String getDm_cabeceras() {
        return dm_cabeceras;
    }
    public void setDm_cabeceras(String dm_cabeceras) {
        this.dm_cabeceras = dm_cabeceras;
    }
    public String getDm_cabo_luz() {
        return dm_cabo_luz;
    }
    public void setDm_cabo_luz(String dm_cabo_luz) {
        this.dm_cabo_luz = dm_cabo_luz;
    }
    public String getDm_cafetera() {
        return dm_cafetera;
    }
    public void setDm_cafetera(String dm_cafetera) {
        this.dm_cafetera = dm_cafetera;
    }
    public String getDm_cambao() {
        return dm_cambao;
    }
    public void setDm_cambao(String dm_cambao) {
        this.dm_cambao = dm_cambao;
    }
    public String getDm_chave_roda() {
        return dm_chave_roda;
    }
    public void setDm_chave_roda(String dm_chave_roda) {
        this.dm_chave_roda = dm_chave_roda;
    }
    public String getDm_colchon() {
        return dm_colchon;
    }
    public void setDm_colchon(String dm_colchon) {
        this.dm_colchon = dm_colchon;
    }
    public String getDm_controle_remoto() {
        return dm_controle_remoto;
    }
    public void setDm_controle_remoto(String dm_controle_remoto) {
        this.dm_controle_remoto = dm_controle_remoto;
    }
    public String getDm_cortina() {
        return dm_cortina;
    }
    public void setDm_cortina(String dm_cortina) {
        this.dm_cortina = dm_cortina;
    }
    public String getDm_cric() {
        return dm_cric;
    }
    public void setDm_cric(String dm_cric) {
        this.dm_cric = dm_cric;
    }
    public String getDm_destornillador() {
        return dm_destornillador;
    }
    public void setDm_destornillador(String dm_destornillador) {
        this.dm_destornillador = dm_destornillador;
    }
    public String getDm_emblemas() {
        return dm_emblemas;
    }
    public void setDm_emblemas(String dm_emblemas) {
        this.dm_emblemas = dm_emblemas;
    }
    public String getDm_encendedor() {
        return dm_encendedor;
    }
    public void setDm_encendedor(String dm_encendedor) {
        this.dm_encendedor = dm_encendedor;
    }
    public String getDm_estepe() {
        return dm_estepe;
    }
    public void setDm_estepe(String dm_estepe) {
        this.dm_estepe = dm_estepe;
    }
    public String getDm_extintor() {
        return dm_extintor;
    }
    public void setDm_extintor(String dm_extintor) {
        this.dm_extintor = dm_extintor;
    }
    public String getDm_faroles_niebla() {
        return dm_faroles_niebla;
    }
    public void setDm_faroles_niebla(String dm_faroles_niebla) {
        this.dm_faroles_niebla = dm_faroles_niebla;
    }
    public String getDm_geladera() {
        return dm_geladera;
    }
    public void setDm_geladera(String dm_geladera) {
        this.dm_geladera = dm_geladera;
    }
    public String getDm_herramientas() {
        return dm_herramientas;
    }
    public void setDm_herramientas(String dm_herramientas) {
        this.dm_herramientas = dm_herramientas;
    }
    public String getDm_limp_para_brisa() {
        return dm_limp_para_brisa;
    }
    public void setDm_limp_para_brisa(String dm_limp_para_brisa) {
        this.dm_limp_para_brisa = dm_limp_para_brisa;
    }
    public String getDm_llaves() {
        return dm_llaves;
    }
    public void setDm_llaves(String dm_llaves) {
        this.dm_llaves = dm_llaves;
    }
    public String getDm_m_carroceria() {
        return dm_m_carroceria;
    }
    public void setDm_m_carroceria(String dm_m_carroceria) {
        this.dm_m_carroceria = dm_m_carroceria;
    }
    public String getDm_m_chasi() {
        return dm_m_chasi;
    }
    public void setDm_m_chasi(String dm_m_chasi) {
        this.dm_m_chasi = dm_m_chasi;
    }
    public String getDm_m_radio() {
        return dm_m_radio;
    }
    public void setDm_m_radio(String dm_m_radio) {
        this.dm_m_radio = dm_m_radio;
    }
    public String getDm_m_tv() {
        return dm_m_tv;
    }
    public void setDm_m_tv(String dm_m_tv) {
        this.dm_m_tv = dm_m_tv;
    }
    public String getDm_m_video_cassete() {
        return dm_m_video_cassete;
    }
    public void setDm_m_video_cassete(String dm_m_video_cassete) {
        this.dm_m_video_cassete = dm_m_video_cassete;
    }
    public String getDm_manguera() {
        return dm_manguera;
    }
    public void setDm_manguera(String dm_manguera) {
        this.dm_manguera = dm_manguera;
    }
    public String getDm_microfono() {
        return dm_microfono;
    }
    public void setDm_microfono(String dm_microfono) {
        this.dm_microfono = dm_microfono;
    }
    public String getDm_muela_arasta() {
        return dm_muela_arasta;
    }
    public void setDm_muela_arasta(String dm_muela_arasta) {
        this.dm_muela_arasta = dm_muela_arasta;
    }
    public String getDm_plato_llantas() {
        return dm_plato_llantas;
    }
    public void setDm_plato_llantas(String dm_plato_llantas) {
        this.dm_plato_llantas = dm_plato_llantas;
    }
    public String getDm_porta_vaso() {
        return dm_porta_vaso;
    }
    public void setDm_porta_vaso(String dm_porta_vaso) {
        this.dm_porta_vaso = dm_porta_vaso;
    }
    public String getDm_primero_socorros() {
        return dm_primero_socorros;
    }
    public void setDm_primero_socorros(String dm_primero_socorros) {
        this.dm_primero_socorros = dm_primero_socorros;
    }
    public String getDm_prot_pedales() {
        return dm_prot_pedales;
    }
    public void setDm_prot_pedales(String dm_prot_pedales) {
        this.dm_prot_pedales = dm_prot_pedales;
    }
    public String getDm_radio() {
        return dm_radio;
    }
    public void setDm_radio(String dm_radio) {
        this.dm_radio = dm_radio;
    }
    public String getDm_retrovisor() {
        return dm_retrovisor;
    }
    public void setDm_retrovisor(String dm_retrovisor) {
        this.dm_retrovisor = dm_retrovisor;
    }
    public String getDm_tacografo() {
        return dm_tacografo;
    }
    public void setDm_tacografo(String dm_tacografo) {
        this.dm_tacografo = dm_tacografo;
    }
    public String getDm_tapa_bateria() {
        return dm_tapa_bateria;
    }
    public void setDm_tapa_bateria(String dm_tapa_bateria) {
        this.dm_tapa_bateria = dm_tapa_bateria;
    }
    public String getDm_tapa_radiador() {
        return dm_tapa_radiador;
    }
    public void setDm_tapa_radiador(String dm_tapa_radiador) {
        this.dm_tapa_radiador = dm_tapa_radiador;
    }
    public String getDm_tapa_volante() {
        return dm_tapa_volante;
    }
    public void setDm_tapa_volante(String dm_tapa_volante) {
        this.dm_tapa_volante = dm_tapa_volante;
    }
    public String getDm_tapete() {
        return dm_tapete;
    }
    public void setDm_tapete(String dm_tapete) {
        this.dm_tapete = dm_tapete;
    }
    public String getDm_triangulo() {
        return dm_triangulo;
    }
    public void setDm_triangulo(String dm_triangulo) {
        this.dm_triangulo = dm_triangulo;
    }
    public String getDm_tv() {
        return dm_tv;
    }
    public void setDm_tv(String dm_tv) {
        this.dm_tv = dm_tv;
    }
    public String getDm_velocimetro() {
        return dm_velocimetro;
    }
    public void setDm_velocimetro(String dm_velocimetro) {
        this.dm_velocimetro = dm_velocimetro;
    }
    public String getDm_ventilador() {
        return dm_ventilador;
    }
    public void setDm_ventilador(String dm_ventilador) {
        this.dm_ventilador = dm_ventilador;
    }
    public String getDm_video_cassete() {
        return dm_video_cassete;
    }
    public void setDm_video_cassete(String dm_video_cassete) {
        this.dm_video_cassete = dm_video_cassete;
    }
    public String getDt_retirada() {
        return FormataDataBean.getFormatDate(dt_retirada);
    }
    public void setDt_retirada(String dt_retirada) {
        this.dt_retirada = dt_retirada;
    }
    public String getNm_marca() {
        return nm_marca;
    }
    public void setNm_marca(String nm_marca) {
        this.nm_marca = nm_marca;
    }
    public String getNm_modelo() {
        return nm_modelo;
    }
    public void setNm_modelo(String nm_modelo) {
        this.nm_modelo = nm_modelo;
    }
    public String getNr_carroceria() {
        return nr_carroceria;
    }
    public void setNr_carroceria(String nr_carroceria) {
        this.nr_carroceria = nr_carroceria;
    }
    public String getNr_chasi() {
        return nr_chasi;
    }
    public void setNr_chasi(String nr_chasi) {
        this.nr_chasi = nr_chasi;
    }
    public String getNr_nota_fiscal() {
        return nr_nota_fiscal;
    }
    public void setNr_nota_fiscal(String nr_nota_fiscal) {
        this.nr_nota_fiscal = nr_nota_fiscal;
    }
    public long getOid_entrega_veiculo() {
        return oid_entrega_veiculo;
    }
    public void setOid_entrega_veiculo(long oid_entrega_veiculo) {
        this.oid_entrega_veiculo = oid_entrega_veiculo;
    }
    public String getTx_bancos() {
        return tx_bancos;
    }
    public void setTx_bancos(String tx_bancos) {
        this.tx_bancos = tx_bancos;
    }
    public String getTx_carroceria() {
        return tx_carroceria;
    }
    public void setTx_carroceria(String tx_carroceria) {
        this.tx_carroceria = tx_carroceria;
    }
    public String getTx_espejos() {
        return tx_espejos;
    }
    public void setTx_espejos(String tx_espejos) {
        this.tx_espejos = tx_espejos;
    }
    public String getTx_faroles() {
        return tx_faroles;
    }
    public void setTx_faroles(String tx_faroles) {
        this.tx_faroles = tx_faroles;
    }
    public String getTx_frente_general() {
        return tx_frente_general;
    }
    public void setTx_frente_general(String tx_frente_general) {
        this.tx_frente_general = tx_frente_general;
    }
    public String getTx_limpieza() {
        return tx_limpieza;
    }
    public void setTx_limpieza(String tx_limpieza) {
        this.tx_limpieza = tx_limpieza;
    }
    public String getTx_observacoes() {
        return tx_observacoes;
    }
    public void setTx_observacoes(String tx_observacoes) {
        this.tx_observacoes = tx_observacoes;
    }
    public String getTx_outros() {
        return tx_outros;
    }
    public void setTx_outros(String tx_outros) {
        this.tx_outros = tx_outros;
    }
    public String getTx_para_brisa() {
        return tx_para_brisa;
    }
    public void setTx_para_brisa(String tx_para_brisa) {
        this.tx_para_brisa = tx_para_brisa;
    }
    public String getTx_pneus() {
        return tx_pneus;
    }
    public void setTx_pneus(String tx_pneus) {
        this.tx_pneus = tx_pneus;
    }
    public double getNr_aire_condicionado() {
        return nr_aire_condicionado;
    }
    public void setNr_aire_condicionado(double nr_aire_condicionado) {
        this.nr_aire_condicionado = nr_aire_condicionado;
    }
    public double getNr_antena() {
        return nr_antena;
    }
    public void setNr_antena(double nr_antena) {
        this.nr_antena = nr_antena;
    }
    public double getNr_cabeceras() {
        return nr_cabeceras;
    }
    public void setNr_cabeceras(double nr_cabeceras) {
        this.nr_cabeceras = nr_cabeceras;
    }
    public double getNr_cabo_luz() {
        return nr_cabo_luz;
    }
    public void setNr_cabo_luz(double nr_cabo_luz) {
        this.nr_cabo_luz = nr_cabo_luz;
    }
    public double getNr_cafetera() {
        return nr_cafetera;
    }
    public void setNr_cafetera(double nr_cafetera) {
        this.nr_cafetera = nr_cafetera;
    }
    public double getNr_cambao() {
        return nr_cambao;
    }
    public void setNr_cambao(double nr_cambao) {
        this.nr_cambao = nr_cambao;
    }
    public double getNr_chave_roda() {
        return nr_chave_roda;
    }
    public void setNr_chave_roda(double nr_chave_roda) {
        this.nr_chave_roda = nr_chave_roda;
    }
    public double getNr_colchon() {
        return nr_colchon;
    }
    public void setNr_colchon(double nr_colchon) {
        this.nr_colchon = nr_colchon;
    }
    public double getNr_controle_remoto() {
        return nr_controle_remoto;
    }
    public void setNr_controle_remoto(double nr_controle_remoto) {
        this.nr_controle_remoto = nr_controle_remoto;
    }
    public double getNr_cortina() {
        return nr_cortina;
    }
    public void setNr_cortina(double nr_cortina) {
        this.nr_cortina = nr_cortina;
    }
    public double getNr_cric() {
        return nr_cric;
    }
    public void setNr_cric(double nr_cric) {
        this.nr_cric = nr_cric;
    }
    public double getNr_destornillador() {
        return nr_destornillador;
    }
    public void setNr_destornillador(double nr_destornillador) {
        this.nr_destornillador = nr_destornillador;
    }
    public double getNr_emblemas() {
        return nr_emblemas;
    }
    public void setNr_emblemas(double nr_emblemas) {
        this.nr_emblemas = nr_emblemas;
    }
    public double getNr_encendedor() {
        return nr_encendedor;
    }
    public void setNr_encendedor(double nr_encendedor) {
        this.nr_encendedor = nr_encendedor;
    }
    public double getNr_estepe() {
        return nr_estepe;
    }
    public void setNr_estepe(double nr_estepe) {
        this.nr_estepe = nr_estepe;
    }
    public double getNr_extintor() {
        return nr_extintor;
    }
    public void setNr_extintor(double nr_extintor) {
        this.nr_extintor = nr_extintor;
    }
    public double getNr_faroles_niebla() {
        return nr_faroles_niebla;
    }
    public void setNr_faroles_niebla(double nr_faroles_niebla) {
        this.nr_faroles_niebla = nr_faroles_niebla;
    }
    public double getNr_geladera() {
        return nr_geladera;
    }
    public void setNr_geladera(double nr_geladera) {
        this.nr_geladera = nr_geladera;
    }
    public double getNr_herramientas() {
        return nr_herramientas;
    }
    public void setNr_herramientas(double nr_herramientas) {
        this.nr_herramientas = nr_herramientas;
    }
    public double getNr_limp_para_brisa() {
        return nr_limp_para_brisa;
    }
    public void setNr_limp_para_brisa(double nr_limp_para_brisa) {
        this.nr_limp_para_brisa = nr_limp_para_brisa;
    }
    public double getNr_llaves() {
        return nr_llaves;
    }
    public void setNr_llaves(double nr_llaves) {
        this.nr_llaves = nr_llaves;
    }
    public double getNr_m_carroceria() {
        return nr_m_carroceria;
    }
    public void setNr_m_carroceria(double nr_m_carroceria) {
        this.nr_m_carroceria = nr_m_carroceria;
    }
    public double getNr_m_chasi() {
        return nr_m_chasi;
    }
    public void setNr_m_chasi(double nr_m_chasi) {
        this.nr_m_chasi = nr_m_chasi;
    }
    public double getNr_m_radio() {
        return nr_m_radio;
    }
    public void setNr_m_radio(double nr_m_radio) {
        this.nr_m_radio = nr_m_radio;
    }
    public double getNr_m_tv() {
        return nr_m_tv;
    }
    public void setNr_m_tv(double nr_m_tv) {
        this.nr_m_tv = nr_m_tv;
    }
    public double getNr_m_video_cassete() {
        return nr_m_video_cassete;
    }
    public void setNr_m_video_cassete(double nr_m_video_cassete) {
        this.nr_m_video_cassete = nr_m_video_cassete;
    }
    public double getNr_manguera() {
        return nr_manguera;
    }
    public void setNr_manguera(double nr_manguera) {
        this.nr_manguera = nr_manguera;
    }
    public double getNr_microfono() {
        return nr_microfono;
    }
    public void setNr_microfono(double nr_microfono) {
        this.nr_microfono = nr_microfono;
    }
    public double getNr_muela_arasta() {
        return nr_muela_arasta;
    }
    public void setNr_muela_arasta(double nr_muela_arasta) {
        this.nr_muela_arasta = nr_muela_arasta;
    }
    public double getNr_plato_llantas() {
        return nr_plato_llantas;
    }
    public void setNr_plato_llantas(double nr_plato_llantas) {
        this.nr_plato_llantas = nr_plato_llantas;
    }
    public double getNr_porta_vaso() {
        return nr_porta_vaso;
    }
    public void setNr_porta_vaso(double nr_porta_vaso) {
        this.nr_porta_vaso = nr_porta_vaso;
    }
    public double getNr_primero_socorros() {
        return nr_primero_socorros;
    }
    public void setNr_primero_socorros(double nr_primero_socorros) {
        this.nr_primero_socorros = nr_primero_socorros;
    }
    public double getNr_prot_pedales() {
        return nr_prot_pedales;
    }
    public void setNr_prot_pedales(double nr_prot_pedales) {
        this.nr_prot_pedales = nr_prot_pedales;
    }
    public double getNr_radio() {
        return nr_radio;
    }
    public void setNr_radio(double nr_radio) {
        this.nr_radio = nr_radio;
    }
    public double getNr_retrovisor() {
        return nr_retrovisor;
    }
    public void setNr_retrovisor(double nr_retrovisor) {
        this.nr_retrovisor = nr_retrovisor;
    }
    public double getNr_tacografo() {
        return nr_tacografo;
    }
    public void setNr_tacografo(double nr_tacografo) {
        this.nr_tacografo = nr_tacografo;
    }
    public double getNr_tapa_bateria() {
        return nr_tapa_bateria;
    }
    public void setNr_tapa_bateria(double nr_tapa_bateria) {
        this.nr_tapa_bateria = nr_tapa_bateria;
    }
    public double getNr_tapa_radiador() {
        return nr_tapa_radiador;
    }
    public void setNr_tapa_radiador(double nr_tapa_radiador) {
        this.nr_tapa_radiador = nr_tapa_radiador;
    }
    public double getNr_tapa_volante() {
        return nr_tapa_volante;
    }
    public void setNr_tapa_volante(double nr_tapa_volante) {
        this.nr_tapa_volante = nr_tapa_volante;
    }
    public double getNr_tapete() {
        return nr_tapete;
    }
    public void setNr_tapete(double nr_tapete) {
        this.nr_tapete = nr_tapete;
    }
    public double getNr_triangulo() {
        return nr_triangulo;
    }
    public void setNr_triangulo(double nr_triangulo) {
        this.nr_triangulo = nr_triangulo;
    }
    public double getNr_tv() {
        return nr_tv;
    }
    public void setNr_tv(double nr_tv) {
        this.nr_tv = nr_tv;
    }
    public double getNr_velocimetro() {
        return nr_velocimetro;
    }
    public void setNr_velocimetro(double nr_velocimetro) {
        this.nr_velocimetro = nr_velocimetro;
    }
    public double getNr_ventilador() {
        return nr_ventilador;
    }
    public void setNr_ventilador(double nr_ventilador) {
        this.nr_ventilador = nr_ventilador;
    }
    public double getNr_video_cassete() {
        return nr_video_cassete;
    }
    public void setNr_video_cassete(double nr_video_cassete) {
        this.nr_video_cassete = nr_video_cassete;
    }
}