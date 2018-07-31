package com.master.ed;

import java.util.ArrayList;

public class ViagemInternacionalRelED extends MasterED implements java.io.Serializable{
    private String cnpj;
    private String cnpj_ie;
    private String fone;
    private String email;
    private String nome;
    private String endereco;
    private String cidade;
    private String uf;
    private String pais;
    private String nr_permisso_manif;
    private String transito_sim;
    private String transito_nao;
    private String dt_emissao;
    private String nro;
    private String folha_atual;
    private String folha_total;
    private String via;
    private String nr_placa;
    private String nr_peso_limite;
    private String nr_ano_veiculo;
    private String nr_placa_carreta;
    private String nm_modelo_veivulo;
    private String cnpj_premisso;
    private String nm_razao_social_premisso;
    private String nm_razao_social_premisso2;
    private String nm_aduana_remetente;
    private String nr_Aduana_remetente;
    private String nm_cidade_destino;
    private String cd_estado_destino;
    private String nm_Pais_destino;
    private String moeda;
    private String moeda2;
    private String nro_conhecimento;
    private String nro_conhecimento2;
    private String tipo_volumes;
    private String tipo_volumes2;
    private String nome_aduana_destino;
    private String codigo_aduana_destino;
    private String nome_aduana_destino2;
    private String codigo_aduana_destino2;
    private String nr_volumes;
    private String nr_volumes2;
    private String vl_peso_bruto;
    private String vl_peso_bruto2;
    private String vl_peso_nato;
    private String vl_peso_nato2;
    private String vl_frete_uss;
    private String vl_frete_uss2;
    private String vl_fto;
    private String vl_fto2;
    private String vl_seguro_uss;
    private String vl_seguro_uss2;
    private String nm_documento1;
    private String nm_documento2;
    private String nm_documento3;
    private String nm_documento4;
    private String nm_documento5;
    private String nm_documento12;
    private String nm_documento22;
    private String nm_documento32;
    private String nm_documento42;
    private String nm_documento52;
    
    private String pais_origem_mercadorias;
    private String cd_pais_origem_mercadorias;
    private String cd_pais_destino_mercadorias;
    private String pais_origem_mercadorias2;
    private String cd_pais_origem_mercadorias2;
    
    private String nm_nome_remetente;
    private String nm_endereco_remetente;
    private String nm_cidade_remetente;
    private String cd_estado_remetente;
    private String nm_pais_remetente;
    private String nm_nome_destinatario;
    private String nm_endereco_destinatario;
    private String nm_cidade_destinatario;
    private String cd_estado_destinatario;
    private String nm_pais_destinatario;
    private String nm_nome_consignatario;
    private String nm_endereco_consignatario;
    private String nm_cidade_consignatario;
    private String cd_estado_consignatario;
    private String nm_pais_consignatario;
    
    private String nm_nome_remetente2;
    private String nm_endereco_remetente2;
    private String nm_cidade_remetente2;
    private String cd_estado_remetente2;
    private String nm_pais_remetente2;
    private String nm_nome_destinatario2;
    private String nm_endereco_destinatario2;
    private String nm_cidade_destinatario2;
    private String cd_estado_destinatario2;
    private String nm_pais_destinatario2;
    private String nm_nome_consignatario2;
    private String nm_endereco_consignatario2;
    private String nm_cidade_consignatario2;
    private String cd_estado_consignatario2;
    private String nm_pais_consignatario2;
    
    private String lista_volume_observacao1;
    private String lista_volume_observacao2;
    private String lista_volume_observacao3;
    private String lista_volume_observacao4;
    private String lista_volume_observacao5;
    private String lista_volume_observacao6;
    private String lista_volume_observacao7;
    private String lista_volume_observacao8;
    private String lista_volume_observacao9;

    private String lista_volume_observacao12;
    private String lista_volume_observacao22;
    private String lista_volume_observacao32;
    private String lista_volume_observacao42;
    private String lista_volume_observacao52;
    private String lista_volume_observacao62;
    private String lista_volume_observacao72;
    private String lista_volume_observacao82;
    private String lista_volume_observacao92;
    
    private String nm_documento6;
    private String nm_documento62;
    private String lista_volume_observacao10;
    private String lista_volume_observacao11;
    private String lista_volume_observacao121;
    private String lista_volume_observacao13;
    private String lista_volume_observacao14;
    private String lista_volume_observacao15;
    private String lista_volume_observacao16;
    private String lista_volume_observacao17;
    private String lista_volume_observacao18;
    private String lista_volume_observacao102;
    private String lista_volume_observacao112;
    private String lista_volume_observacao122;
    private String lista_volume_observacao132;
    private String lista_volume_observacao142;
    private String lista_volume_observacao152;
    private String lista_volume_observacao162;
    private String lista_volume_observacao172;
    private String lista_volume_observacao182;
    
    private String poliza_seguro;
    private String perm_orig;
    private String perm_comp;
    private String perm_prop;
    
    private String nm_nome_prop;
    private String nm_endereco_prop;
    private String nm_cidade_prop;
    
    private String reboque;
    private String semi_reboque;
    
    private String tara;
    private String pl;
    private String p_geral;
    
    private String NCM;
    private String NCM2;
    private String NCM3;
    private String NCM4;
    private String NCM5;
    private String NCM6;
    private String NCM7;
    private String NCM8;
    private String NCM9;
    private String NCM10;
    
    private String NCM12;
    private String NCM22;
    private String NCM32;
    private String NCM42;
    private String NCM52;
    private String NCM62;
    private String NCM72;
    private String NCM82;
    private String NCM92;
    private String NCM102;
    
    private String rota_observacao1;
    private String rota_observacao2;
    private String rota_observacao3;
    private String rota_observacao4;
    private String rota_observacao5;
    private String rota_observacao6;
    private String rota_observacao7;
    private String rota_observacao8;
    private String rota_observacao9;
    private String rota_observacao10;
    private String rota_observacao11;
    private String rota_observacao12;
    private String motorista_nome;
    private String motorista_rg;
    private String lacres;
    private String lacres2;
    
    private String totais_peso;
    private String totais_peso_anterior;
    private String totais_peso_acumulado;
    private String totais_volumes;
    private String totais_volumes_anterior;
    private String totais_volumes_acumulado;
    
    private String nm_carimbo;
    
    
    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj){
    	this.cnpj = cnpj;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome){
    	this.nome = nome;
    }
    
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco){
    	this.endereco = endereco;
    }
    
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade){
        this.cidade = cidade;
    }
    
    public String getUf() {
        return uf;
    }
    public void setUf(String uf){
        this.uf = uf;
    }
    
    public String getPais() {
        return pais;
    }
    public void setPais(String pais){
        this.pais = pais;
    }

    public String getNr_permisso_manif() {
        return nr_permisso_manif;
    }
    public void setNr_permisso_manif(String nr_permisso_manif){
        this.nr_permisso_manif = nr_permisso_manif;
    }
    
    public String getTransito_sim() {
        return transito_sim;
    }
    public void setTransito_sim(String transito_sim){
        this.transito_sim = transito_sim;
    }
    
    public String getTransito_nao() {
        return transito_nao;
    }
    public void setTransito_nao(String transito_nao){
        this.transito_nao = transito_nao;
    }
    
    public String getDt_emissao() {
        return dt_emissao;
    }
    public void setDt_emissao(String dt_emissao){
        this.dt_emissao = dt_emissao;
    }
    
    public String getNro() {
        return nro;
    }
    public void setNro(String nro){
        this.nro = nro;
    }
    
    public String getFolha_atual() {
        return folha_atual;
    }
    public void setFolha_atual(String folha_atual){
        this.folha_atual = folha_atual;
    }
    
    public String getFolha_total() {
        return folha_total;
    }
    public void setFolha_total(String folha_total){
        this.folha_total = folha_total;
    }
    
    public String getVia() {
        return via;
    }
    public void setVia(String via){
        this.via = via;
    }
    
    public String getNr_placa() {
        return nr_placa;
    }
    public void setNr_placa(String nr_placa){
        this.nr_placa = nr_placa;
    }
    
    public String getNr_peso_limite() {
        return nr_peso_limite;
    }
    public void setNr_peso_limite(String nr_peso_limite){
        this.nr_peso_limite = nr_peso_limite;
    }
    
    public String getNr_ano_veiculo() {
        return nr_ano_veiculo;
    }
    public void setNr_ano_veiculo(String nr_ano_veiculo){
        this.nr_ano_veiculo = nr_ano_veiculo;
    }
    
    public String getNr_placa_carreta() {
        return nr_placa_carreta;
    }
    public void setNr_placa_carreta(String nr_placa_carreta){
        this.nr_placa_carreta = nr_placa_carreta;
    }
    
    public String getNm_modelo_veivulo() {
        return nm_modelo_veivulo;
    }
    public void setNm_modelo_veivulo(String nm_modelo_veivulo){
        this.nm_modelo_veivulo = nm_modelo_veivulo;
    }
    
    public String getCnpj_premisso() {
        return cnpj_premisso;
    }
    public void setCnpj_premisso(String cnpj_premisso){
        this.cnpj_premisso = cnpj_premisso;
    }    
    
    public String getNm_razao_social_premisso() {
        return nm_razao_social_premisso;
    }
    public void setNm_razao_social_premisso(String nm_razao_social_premisso){
        this.nm_razao_social_premisso = nm_razao_social_premisso;
    }   
    
    public String getNm_razao_social_premisso2() {
        return nm_razao_social_premisso2;
    }
    public void setNm_razao_social_premisso2(String nm_razao_social_premisso2){
        this.nm_razao_social_premisso2 = nm_razao_social_premisso2;
    }
    
    public String getNm_aduana_remetente() {
        return nm_aduana_remetente;
    }
    public void setNm_aduana_remetente(String nm_aduana_remetente){
        this.nm_aduana_remetente = nm_aduana_remetente;
    }   
    
    public String getNr_Aduana_remetente() {
        return nr_Aduana_remetente;
    }
    public void setNr_Aduana_remetente(String nr_Aduana_remetente){
        this.nr_Aduana_remetente = nr_Aduana_remetente;
    }
    
    public String getNm_cidade_destino() {
        return nm_cidade_destino;
    }
    public void setNm_cidade_destino(String nm_cidade_destino){
        this.nm_cidade_destino = nm_cidade_destino;
    }
    
    public String getCd_estado_destino() {
        return cd_estado_destino;
    }
    public void setCd_estado_destino(String cd_estado_destino){
        this.cd_estado_destino = cd_estado_destino;
    }
    
    public String getNm_Pais_destino() {
        return nm_Pais_destino;
    }
    public void setNm_Pais_destino(String nm_Pais_destino){
        this.nm_Pais_destino = nm_Pais_destino;
    }
    
    public String getMoeda() {
        return moeda;
    }
    public void setMoeda(String moeda){
        this.moeda = moeda;
    }
    
    public String getTipo_volumes() {
        return tipo_volumes;
    }
    public void setTipo_volumes(String tipo_volumes){
        this.tipo_volumes = tipo_volumes;
    }
    
    public String getNome_aduana_destino() {
        return nome_aduana_destino;
    }
    public void setNome_aduana_destino(String nome_aduana_destino){
        this.nome_aduana_destino = nome_aduana_destino;
    }
    
    public String getCodigo_aduana_destino() {
        return codigo_aduana_destino;
    }
    public void setCodigo_aduana_destino(String codigo_aduana_destino){
        this.codigo_aduana_destino = codigo_aduana_destino;
    }
    
    public String getNr_volumes() {
        return nr_volumes;
    }
    public void setNr_volumes(String nr_volumes){
        this.nr_volumes = nr_volumes;
    }
    
    public String getVl_peso_bruto() {
        return vl_peso_bruto;
    }
    public void setVl_peso_bruto(String vl_peso_bruto){
        this.vl_peso_bruto = vl_peso_bruto;
    }
    
    public String getVl_peso_nato() {
        return vl_peso_nato;
    }
    public void setVl_peso_nato(String vl_peso_nato){
        this.vl_peso_nato = vl_peso_nato;
    }
    
    public String getVl_frete_uss() {
        return vl_frete_uss;
    }
    public void setVl_frete_uss(String vl_frete_uss){
        this.vl_frete_uss = vl_frete_uss;
    }
    
    public String getVl_fto() {
        return vl_fto;
    }
    public void setVl_fto(String vl_fto){
        this.vl_fto = vl_fto;
    }
    
    public String getVl_seguro_uss() {
        return vl_seguro_uss;
    }
    public void setVl_seguro_uss(String vl_seguro_uss){
        this.vl_seguro_uss = vl_seguro_uss;
    }
    
    public String getNm_documento1() {
        return nm_documento1;
    }
    public void setNm_documento1(String nm_documento1){
        this.nm_documento1 = nm_documento1;
    }
    
    public String getNm_documento2() {
        return nm_documento2;
    }
    public void setNm_documento2(String nm_documento2){
        this.nm_documento2 = nm_documento2;
    }
    
    public String getNm_documento3() {
        return nm_documento3;
    }
    public void setNm_documento3(String nm_documento3){
        this.nm_documento3 = nm_documento3;
    }
    
    public String getNm_documento4() {
        return nm_documento4;
    }
    public void setNm_documento4(String nm_documento4){
        this.nm_documento4 = nm_documento4;
    }
    
    public String getNm_documento5() {
        return nm_documento5;
    }
    public void setNm_documento5(String nm_documento5){
        this.nm_documento5 = nm_documento5;
    }
    
    public String getPais_origem_mercadorias() {
        return pais_origem_mercadorias;
    }
    public void setPais_origem_mercadorias(String pais_origem_mercadorias){
        this.pais_origem_mercadorias = pais_origem_mercadorias;
    }
    
    public String getPais_origem_mercadorias2() {
        return pais_origem_mercadorias2;
    }
    public void setPais_origem_mercadorias2(String pais_origem_mercadorias2){
        this.pais_origem_mercadorias2 = pais_origem_mercadorias2;
    }
    
    public String getCd_pais_origem_mercadorias() {
        return cd_pais_origem_mercadorias;
    }
    public void setCd_pais_origem_mercadorias(String cd_pais_origem_mercadorias){
        this.cd_pais_origem_mercadorias = cd_pais_origem_mercadorias;
    }
    
    public String getCd_pais_origem_mercadorias2() {
        return cd_pais_origem_mercadorias2;
    }
    public void setCd_pais_origem_mercadorias2(String cd_pais_origem_mercadorias2){
        this.cd_pais_origem_mercadorias2 = cd_pais_origem_mercadorias2;
    }
    
    public String getNm_nome_remetente() {
        return nm_nome_remetente;
    }
    public void setNm_nome_remetente(String nm_nome_remetente){
        this.nm_nome_remetente = nm_nome_remetente;
    }
    
    public String getNm_endereco_remetente() {
        return nm_endereco_remetente;
    }
    public void setNm_endereco_remetente(String nm_endereco_remetente){
        this.nm_endereco_remetente = nm_endereco_remetente;
    }
    
    public String getNm_cidade_remetente() {
        return nm_cidade_remetente;
    }
    public void setNm_cidade_remetente(String nm_cidade_remetente){
        this.nm_cidade_remetente = nm_cidade_remetente;
    }
    
    public String getCd_estado_remetente() {
        return cd_estado_remetente;
    }
    public void setCd_estado_remetente (String cd_estado_remetente){
        this.cd_estado_remetente = cd_estado_remetente;
    }
    
    public String getNm_pais_remetente() {
        return nm_pais_remetente;
    }
    public void setNm_pais_remetente(String nm_pais_remetente){
        this.nm_pais_remetente = nm_pais_remetente;
    }
    public String getNm_nome_destinatario() {
        return nm_nome_destinatario;
    }
    public void setNm_nome_destinatario(String nm_nome_destinatario){
        this.nm_nome_destinatario = nm_nome_destinatario;
    }
    
    public String getNm_endereco_destinatario() {
        return nm_endereco_destinatario;
    }
    public void setNm_endereco_destinatario(String nm_endereco_destinatario){
        this.nm_endereco_destinatario = nm_endereco_destinatario;
    }
    
    public String getNm_cidade_destinatario() {
        return nm_cidade_destinatario;
    }
    public void setNm_cidade_destinatario(String nm_cidade_destinatario){
        this.nm_cidade_destinatario = nm_cidade_destinatario;
    }
    
    public String getCd_estado_destinatario() {
        return cd_estado_destinatario;
    }
    public void setCd_estado_destinatario (String cd_estado_destinatario){
        this.cd_estado_destinatario = cd_estado_destinatario;
    }
    
    public String getNm_pais_destinatario() {
        return nm_pais_destinatario;
    }
    public void setNm_pais_destinatario(String nm_pais_destinatario){
        this.nm_pais_destinatario = nm_pais_destinatario;
    }
    public String getNm_nome_consignatario() {
        return nm_nome_consignatario;
    }
    public void setNm_nome_consignatario(String nm_nome_consignatario){
        this.nm_nome_consignatario = nm_nome_consignatario;
    }
    
    public String getNm_endereco_consignatario() {
        return nm_endereco_consignatario;
    }
    public void setNm_endereco_consignatario(String nm_endereco_consignatario){
        this.nm_endereco_consignatario = nm_endereco_consignatario;
    }
    
    public String getNm_cidade_consignatario() {
        return nm_cidade_consignatario;
    }
    public void setNm_cidade_consignatario(String nm_cidade_consignatario){
        this.nm_cidade_consignatario = nm_cidade_consignatario;
    }
    
    public String getCd_estado_consignatario() {
        return cd_estado_consignatario;
    }
    public void setCd_estado_consignatario (String cd_estado_consignatario){
        this.cd_estado_consignatario = cd_estado_consignatario;
    }
    
    public String getNm_pais_consignatario() {
        return nm_pais_consignatario;
    }
    public void setNm_pais_consignatario(String nm_pais_consignatario){
        this.nm_pais_consignatario = nm_pais_consignatario;
    }
    
    public String getNm_nome_remetente2() {
        return nm_nome_remetente2;
    }
    public void setNm_nome_remetente2(String nm_nome_remetente2){
        this.nm_nome_remetente2 = nm_nome_remetente2;
    }
    
    public String getNm_endereco_remetente2() {
        return nm_endereco_remetente2;
    }
    public void setNm_endereco_remetente2(String nm_endereco_remetente2){
        this.nm_endereco_remetente2 = nm_endereco_remetente2;
    }
    
    public String getNm_cidade_remetente2() {
        return nm_cidade_remetente2;
    }
    public void setNm_cidade_remetente2(String nm_cidade_remetente2){
        this.nm_cidade_remetente2 = nm_cidade_remetente2;
    }
    
    public String getCd_estado_remetente2() {
        return cd_estado_remetente2;
    }
    public void setCd_estado_remetente2 (String cd_estado_remetente2){
        this.cd_estado_remetente2 = cd_estado_remetente2;
    }
    
    public String getNm_pais_remetente2() {
        return nm_pais_remetente2;
    }
    public void setNm_pais_remetente2(String nm_pais_remetente2){
        this.nm_pais_remetente2 = nm_pais_remetente2;
    }
    public String getNm_nome_destinatario2() {
        return nm_nome_destinatario2;
    }
    public void setNm_nome_destinatario2(String nm_nome_destinatario2){
        this.nm_nome_destinatario2 = nm_nome_destinatario2;
    }
    
    public String getNm_endereco_destinatario2() {
        return nm_endereco_destinatario2;
    }
    public void setNm_endereco_destinatario2(String nm_endereco_destinatario2){
        this.nm_endereco_destinatario2 = nm_endereco_destinatario2;
    }
    
    public String getNm_cidade_destinatario2() {
        return nm_cidade_destinatario2;
    }
    public void setNm_cidade_destinatario2(String nm_cidade_destinatario2){
        this.nm_cidade_destinatario2 = nm_cidade_destinatario2;
    }
    
    public String getCd_estado_destinatario2() {
        return cd_estado_destinatario2;
    }
    public void setCd_estado_destinatario2 (String cd_estado_destinatario2){
        this.cd_estado_destinatario2 = cd_estado_destinatario2;
    }
    
    public String getNm_pais_destinatario2() {
        return nm_pais_destinatario2;
    }
    public void setNm_pais_destinatario2(String nm_pais_destinatario2){
        this.nm_pais_destinatario2 = nm_pais_destinatario2;
    }
    public String getNm_nome_consignatario2() {
        return nm_nome_consignatario2;
    }
    public void setNm_nome_consignatario2(String nm_nome_consignatario2){
        this.nm_nome_consignatario2 = nm_nome_consignatario2;
    }
    
    public String getNm_endereco_consignatario2() {
        return nm_endereco_consignatario2;
    }
    public void setNm_endereco_consignatario2(String nm_endereco_consignatario2){
        this.nm_endereco_consignatario2 = nm_endereco_consignatario2;
    }
    
    public String getNm_cidade_consignatario2() {
        return nm_cidade_consignatario2;
    }
    public void setNm_cidade_consignatario2(String nm_cidade_consignatario2){
        this.nm_cidade_consignatario2 = nm_cidade_consignatario2;
    }
    
    public String getCd_estado_consignatario2() {
        return cd_estado_consignatario2;
    }
    public void setCd_estado_consignatario2 (String cd_estado_consignatario2){
        this.cd_estado_consignatario2 = cd_estado_consignatario2;
    }
    
    public String getNm_pais_consignatario2() {
        return nm_pais_consignatario2;
    }
    public void setNm_pais_consignatario2(String nm_pais_consignatario2){
        this.nm_pais_consignatario2 = nm_pais_consignatario2;
    }
        
    public String getLista_volume_observacao1() {
        return lista_volume_observacao1;
    }
    public void setLista_volume_observacao1(String lista_volume_observacao1){
        this.lista_volume_observacao1 = lista_volume_observacao1;
    }
    
    public String getLista_volume_observacao2() {
        return lista_volume_observacao2;
    }
    public void setLista_volume_observacao2(String lista_volume_observacao2){
        this.lista_volume_observacao2 = lista_volume_observacao2;
    }
    
    public String getLista_volume_observacao3() {
        return lista_volume_observacao3;
    }
    public void setLista_volume_observacao3(String lista_volume_observacao3){
        this.lista_volume_observacao3 = lista_volume_observacao3;
    }
    
    public String getLista_volume_observacao4() {
        return lista_volume_observacao4;
    }
    public void setLista_volume_observacao4(String lista_volume_observacao4){
        this.lista_volume_observacao4 = lista_volume_observacao4;
    }
    
    public String getLista_volume_observacao5() {
        return lista_volume_observacao5;
    }
    public void setLista_volume_observacao5(String lista_volume_observacao5){
        this.lista_volume_observacao5 = lista_volume_observacao5;
    }
    
    public String getLista_volume_observacao6() {
        return lista_volume_observacao6;
    }
    public void setLista_volume_observacao6(String lista_volume_observacao6){
        this.lista_volume_observacao6 = lista_volume_observacao6;
    }
    
    public String getLista_volume_observacao7() {
        return lista_volume_observacao7;
    }
    public void setLista_volume_observacao7(String lista_volume_observacao7){
        this.lista_volume_observacao7 = lista_volume_observacao7;
    }
    
    public String getLista_volume_observacao8() {
        return lista_volume_observacao8;
    }
    public void setLista_volume_observacao8(String lista_volume_observacao8){
        this.lista_volume_observacao8 = lista_volume_observacao8;
    }
    
    public String getLista_volume_observacao9() {
        return lista_volume_observacao9;
    }
    public void setLista_volume_observacao9(String lista_volume_observacao9){
        this.lista_volume_observacao9 = lista_volume_observacao9;
    }
    
    public String getLista_volume_observacao12() {
        return lista_volume_observacao12;
    }
    public void setLista_volume_observacao12(String lista_volume_observacao12){
        this.lista_volume_observacao12 = lista_volume_observacao12;
    }
    
    public String getLista_volume_observacao22() {
        return lista_volume_observacao22;
    }
    public void setLista_volume_observacao22(String lista_volume_observacao22){
        this.lista_volume_observacao22 = lista_volume_observacao22;
    }
    
    public String getLista_volume_observacao32() {
        return lista_volume_observacao32;
    }
    public void setLista_volume_observacao32(String lista_volume_observacao32){
        this.lista_volume_observacao32 = lista_volume_observacao32;
    }
    
    public String getLista_volume_observacao42() {
        return lista_volume_observacao42;
    }
    public void setLista_volume_observacao42(String lista_volume_observacao42){
        this.lista_volume_observacao42 = lista_volume_observacao42;
    }
    
    public String getLista_volume_observacao52() {
        return lista_volume_observacao52;
    }
    public void setLista_volume_observacao52(String lista_volume_observacao52){
        this.lista_volume_observacao52 = lista_volume_observacao52;
    }
    
    public String getLista_volume_observacao62() {
        return lista_volume_observacao62;
    }
    public void setLista_volume_observacao62(String lista_volume_observacao62){
        this.lista_volume_observacao62 = lista_volume_observacao62;
    }
    
    public String getLista_volume_observacao72() {
        return lista_volume_observacao72;
    }
    public void setLista_volume_observacao72(String lista_volume_observacao72){
        this.lista_volume_observacao72 = lista_volume_observacao72;
    }
    
    public String getLista_volume_observacao82() {
        return lista_volume_observacao82;
    }
    public void setLista_volume_observacao82(String lista_volume_observacao82){
        this.lista_volume_observacao82 = lista_volume_observacao82;
    }
    
    public String getLista_volume_observacao92() {
        return lista_volume_observacao92;
    }
    public void setLista_volume_observacao92(String lista_volume_observacao92){
        this.lista_volume_observacao92 = lista_volume_observacao92;
    }
        
    public String getRota_observacao1() {
        return rota_observacao1;
    }
    public void setRota_observacao1(String rota_observacao1){
        this.rota_observacao1 = rota_observacao1;
    }
    
    public String getRota_observacao2() {
        return rota_observacao2;
    }
    public void setRota_observacao2(String rota_observacao2){
        this.rota_observacao2 = rota_observacao2;
    }
    
    public String getRota_observacao3() {
        return rota_observacao3;
    }
    public void setRota_observacao3(String rota_observacao3){
        this.rota_observacao3 = rota_observacao3;
    }
    
    public String getMotorista_nome() {
        return motorista_nome;
    }
    public void setMotorista_nome(String motorista_nome){
        this.motorista_nome = motorista_nome;
    }
    
    public String getMotorista_rg() {
        return motorista_rg;
    }
    public void setMotorista_rg(String motorista_rg){
        this.motorista_rg = motorista_rg;
    }
    
    public String getLacres() {
        return lacres;
    }
    public void setLacres(String lacres){
        this.lacres = lacres;
    }
    
    public String getTotais_peso() {
        return totais_peso;
    }
    public void setTotais_peso(String totais_peso){
        this.totais_peso = totais_peso;
    }
    
    public String getTotais_peso_anterior() {
        return totais_peso_anterior;
    }
    public void setTotais_peso_anterior(String totais_peso_anterior){
        this.totais_peso_anterior = totais_peso_anterior;
    }
    
    public String getTotais_peso_acumulado() {
        return totais_peso_acumulado;
    }
    public void setTotais_peso_acumulado(String totais_peso_acumulado){
        this.totais_peso_acumulado = totais_peso_acumulado;
    }
    
    public String getTotais_volumes() {
        return totais_volumes;
    }
    public void setTotais_volumes(String totais_volumes){
        this.totais_volumes = totais_volumes;
    }
    
    public String getTotais_volumes_anterior() {
        return totais_volumes_anterior;
    }
    public void setTotais_volumes_anterior(String totais_volumes_anterior){
        this.totais_volumes_anterior = totais_volumes_anterior;
    }
    
    public String getTotais_volumes_acumulado() {
        return totais_volumes_acumulado;
    }
    public void setTotais_volumes_acumulado(String totais_volumes_acumulado){
        this.totais_volumes_acumulado = totais_volumes_acumulado;
    }
    public String getNro_conhecimento() {
        return nro_conhecimento;
    }
    public void setNro_conhecimento(String nro_conhecimento) {
        this.nro_conhecimento = nro_conhecimento;
    }
    public String getNro_conhecimento2() {
        return nro_conhecimento2;
    }
    public void setNro_conhecimento2(String nro_conhecimento2) {
        this.nro_conhecimento2 = nro_conhecimento2;
    }
    public String getTipo_volumes2() {
        return tipo_volumes2;
    }
    public void setTipo_volumes2(String tipo_volumes2) {
        this.tipo_volumes2 = tipo_volumes2;
    }
    public String getMoeda2() {
        return moeda2;
    }
    public void setMoeda2(String moeda2) {
        this.moeda2 = moeda2;
    }
    public String getCodigo_aduana_destino2() {
        return codigo_aduana_destino2;
    }
    public void setCodigo_aduana_destino2(String codigo_aduana_destino2) {
        this.codigo_aduana_destino2 = codigo_aduana_destino2;
    }
    public String getNome_aduana_destino2() {
        return nome_aduana_destino2;
    }
    public void setNome_aduana_destino2(String nome_aduana_destino2) {
        this.nome_aduana_destino2 = nome_aduana_destino2;
    }
    public String getLacres2() {
        return lacres2;
    }
    public void setLacres2(String lacres2) {
        this.lacres2 = lacres2;
    }
    public String getNr_volumes2() {
        return nr_volumes2;
    }
    public void setNr_volumes2(String nr_volumes2) {
        this.nr_volumes2 = nr_volumes2;
    }
    public String getVl_peso_bruto2() {
        return vl_peso_bruto2;
    }
    public void setVl_peso_bruto2(String vl_peso_bruto2) {
        this.vl_peso_bruto2 = vl_peso_bruto2;
    }
    public String getVl_peso_nato2() {
        return vl_peso_nato2;
    }
    public void setVl_peso_nato2(String vl_peso_nato2) {
        this.vl_peso_nato2 = vl_peso_nato2;
    }
    public String getVl_frete_uss2() {
        return vl_frete_uss2;
    }
    public void setVl_frete_uss2(String vl_frete_uss2) {
        this.vl_frete_uss2 = vl_frete_uss2;
    }
    public String getVl_fto2() {
        return vl_fto2;
    }
    public void setVl_fto2(String vl_fto2) {
        this.vl_fto2 = vl_fto2;
    }
    public String getVl_seguro_uss2() {
        return vl_seguro_uss2;
    }
    public void setVl_seguro_uss2(String vl_seguro_uss2) {
        this.vl_seguro_uss2 = vl_seguro_uss2;
    }
    public String getNm_documento12() {
        return nm_documento12;
    }
    public void setNm_documento12(String nm_documento12) {
        this.nm_documento12 = nm_documento12;
    }
    public String getNm_documento22() {
        return nm_documento22;
    }
    public void setNm_documento22(String nm_documento22) {
        this.nm_documento22 = nm_documento22;
    }
    public String getNm_documento32() {
        return nm_documento32;
    }
    public void setNm_documento32(String nm_documento32) {
        this.nm_documento32 = nm_documento32;
    }
    public String getNm_documento42() {
        return nm_documento42;
    }
    public void setNm_documento42(String nm_documento42) {
        this.nm_documento42 = nm_documento42;
    }
    public String getNm_documento52() {
        return nm_documento52;
    }
    public void setNm_documento52(String nm_documento52) {
        this.nm_documento52 = nm_documento52;
    }
    
    
    public String getNCM() {
        return NCM;
    }
    public void setNCM(String ncm) {
        NCM = ncm;
    }
   
    
    
    public String getLista_volume_observacao10() {
        return lista_volume_observacao10;
    }
    public void setLista_volume_observacao10(String lista_volume_observacao10) {
        this.lista_volume_observacao10 = lista_volume_observacao10;
    }
    public String getLista_volume_observacao11() {
        return lista_volume_observacao11;
    }
    public void setLista_volume_observacao11(String lista_volume_observacao11) {
        this.lista_volume_observacao11 = lista_volume_observacao11;
    }
    public String getLista_volume_observacao13() {
        return lista_volume_observacao13;
    }
    public void setLista_volume_observacao13(String lista_volume_observacao13) {
        this.lista_volume_observacao13 = lista_volume_observacao13;
    }
    public String getLista_volume_observacao14() {
        return lista_volume_observacao14;
    }
    public void setLista_volume_observacao14(String lista_volume_observacao14) {
        this.lista_volume_observacao14 = lista_volume_observacao14;
    }
    public String getLista_volume_observacao15() {
        return lista_volume_observacao15;
    }
    public void setLista_volume_observacao15(String lista_volume_observacao15) {
        this.lista_volume_observacao15 = lista_volume_observacao15;
    }
    public String getLista_volume_observacao16() {
        return lista_volume_observacao16;
    }
    public void setLista_volume_observacao16(String lista_volume_observacao16) {
        this.lista_volume_observacao16 = lista_volume_observacao16;
    }
    public String getLista_volume_observacao17() {
        return lista_volume_observacao17;
    }
    public void setLista_volume_observacao17(String lista_volume_observacao17) {
        this.lista_volume_observacao17 = lista_volume_observacao17;
    }
    public String getLista_volume_observacao18() {
        return lista_volume_observacao18;
    }
    public void setLista_volume_observacao18(String lista_volume_observacao18) {
        this.lista_volume_observacao18 = lista_volume_observacao18;
    }
    public String getNm_documento6() {
        return nm_documento6;
    }
    public void setNm_documento6(String nm_documento6) {
        this.nm_documento6 = nm_documento6;
    }
    
    public String getPerm_comp() {
        return perm_comp;
    }
    public void setPerm_comp(String perm_comp) {
        this.perm_comp = perm_comp;
    }
    public String getPerm_orig() {
        return perm_orig;
    }
    public void setPerm_orig(String perm_orig) {
        this.perm_orig = perm_orig;
    }
    public String getPerm_prop() {
        return perm_prop;
    }
    public void setPerm_prop(String perm_prop) {
        this.perm_prop = perm_prop;
    }
    public String getPoliza_seguro() {
        return poliza_seguro;
    }
    public void setPoliza_seguro(String poliza_seguro) {
        this.poliza_seguro = poliza_seguro;
    }
    
    public String getLista_volume_observacao102() {
        return lista_volume_observacao102;
    }
    public void setLista_volume_observacao102(String lista_volume_observacao102) {
        this.lista_volume_observacao102 = lista_volume_observacao102;
    }
    public String getLista_volume_observacao112() {
        return lista_volume_observacao112;
    }
    public void setLista_volume_observacao112(String lista_volume_observacao112) {
        this.lista_volume_observacao112 = lista_volume_observacao112;
    }
    public String getLista_volume_observacao132() {
        return lista_volume_observacao132;
    }
    public void setLista_volume_observacao132(String lista_volume_observacao132) {
        this.lista_volume_observacao132 = lista_volume_observacao132;
    }
    public String getLista_volume_observacao142() {
        return lista_volume_observacao142;
    }
    public void setLista_volume_observacao142(String lista_volume_observacao142) {
        this.lista_volume_observacao142 = lista_volume_observacao142;
    }
    public String getLista_volume_observacao152() {
        return lista_volume_observacao152;
    }
    public void setLista_volume_observacao152(String lista_volume_observacao152) {
        this.lista_volume_observacao152 = lista_volume_observacao152;
    }
    public String getLista_volume_observacao162() {
        return lista_volume_observacao162;
    }
    public void setLista_volume_observacao162(String lista_volume_observacao162) {
        this.lista_volume_observacao162 = lista_volume_observacao162;
    }
    public String getLista_volume_observacao172() {
        return lista_volume_observacao172;
    }
    public void setLista_volume_observacao172(String lista_volume_observacao172) {
        this.lista_volume_observacao172 = lista_volume_observacao172;
    }
    public String getLista_volume_observacao182() {
        return lista_volume_observacao182;
    }
    public void setLista_volume_observacao182(String lista_volume_observacao182) {
        this.lista_volume_observacao182 = lista_volume_observacao182;
    }
    public String getNm_documento62() {
        return nm_documento62;
    }
    public void setNm_documento62(String nm_documento62) {
        this.nm_documento62 = nm_documento62;
    }
    public String getNm_endereco_prop() {
        return nm_endereco_prop;
    }
    public void setNm_endereco_prop(String nm_endereco_prop) {
        this.nm_endereco_prop = nm_endereco_prop;
    }
    public String getNm_nome_prop() {
        return nm_nome_prop;
    }
    public void setNm_nome_prop(String nm_nome_prop) {
        this.nm_nome_prop = nm_nome_prop;
    }
    public String getReboque() {
        return reboque;
    }
    public void setReboque(String reboque) {
        this.reboque = reboque;
    }
    public String getSemi_reboque() {
        return semi_reboque;
    }
    public void setSemi_reboque(String semi_reboque) {
        this.semi_reboque = semi_reboque;
    }
    public String getPl() {
        return pl;
    }
    public void setPl(String pl) {
        this.pl = pl;
    }
    public String getTara() {
        return tara;
    }
    public void setTara(String tara) {
        this.tara = tara;
    }
    public String getP_geral() {
        return p_geral;
    }
    public void setP_geral(String p_geral) {
        this.p_geral = p_geral;
    }
    public String getNCM10() {
        return NCM10;
    }
    public void setNCM10(String ncm10) {
        NCM10 = ncm10;
    }
    public String getNCM2() {
        return NCM2;
    }
    public void setNCM2(String ncm2) {
        NCM2 = ncm2;
    }
    public String getNCM3() {
        return NCM3;
    }
    public void setNCM3(String ncm3) {
        NCM3 = ncm3;
    }
    public String getNCM4() {
        return NCM4;
    }
    public void setNCM4(String ncm4) {
        NCM4 = ncm4;
    }
    public String getNCM5() {
        return NCM5;
    }
    public void setNCM5(String ncm5) {
        NCM5 = ncm5;
    }
    public String getNCM6() {
        return NCM6;
    }
    public void setNCM6(String ncm6) {
        NCM6 = ncm6;
    }
    public String getNCM7() {
        return NCM7;
    }
    public void setNCM7(String ncm7) {
        NCM7 = ncm7;
    }
    public String getNCM8() {
        return NCM8;
    }
    public void setNCM8(String ncm8) {
        NCM8 = ncm8;
    }
    public String getNCM9() {
        return NCM9;
    }
    public void setNCM9(String ncm9) {
        NCM9 = ncm9;
    }
    public String getLista_volume_observacao122() {
        return lista_volume_observacao122;
    }
    public void setLista_volume_observacao122(String lista_volume_observacao122) {
        this.lista_volume_observacao122 = lista_volume_observacao122;
    }
    public String getLista_volume_observacao121() {
        return lista_volume_observacao121;
    }
    public void setLista_volume_observacao121(String lista_volume_observacao121) {
        this.lista_volume_observacao121 = lista_volume_observacao121;
    }
    public String getNCM102() {
        return NCM102;
    }
    public void setNCM102(String ncm102) {
        NCM102 = ncm102;
    }
    public String getNCM12() {
        return NCM12;
    }
    public void setNCM12(String ncm12) {
        NCM12 = ncm12;
    }
    public String getNCM22() {
        return NCM22;
    }
    public void setNCM22(String ncm22) {
        NCM22 = ncm22;
    }
    public String getNCM32() {
        return NCM32;
    }
    public void setNCM32(String ncm32) {
        NCM32 = ncm32;
    }
    public String getNCM42() {
        return NCM42;
    }
    public void setNCM42(String ncm42) {
        NCM42 = ncm42;
    }
    public String getNCM52() {
        return NCM52;
    }
    public void setNCM52(String ncm52) {
        NCM52 = ncm52;
    }
    public String getNCM62() {
        return NCM62;
    }
    public void setNCM62(String ncm62) {
        NCM62 = ncm62;
    }
    public String getNCM72() {
        return NCM72;
    }
    public void setNCM72(String ncm72) {
        NCM72 = ncm72;
    }
    public String getNCM82() {
        return NCM82;
    }
    public void setNCM82(String ncm82) {
        NCM82 = ncm82;
    }
    public String getNCM92() {
        return NCM92;
    }
    public void setNCM92(String ncm92) {
        NCM92 = ncm92;
    }
    public String getCd_pais_destino_mercadorias() {
        return cd_pais_destino_mercadorias;
    }
    public void setCd_pais_destino_mercadorias(
            String cd_pais_destino_mercadorias) {
        this.cd_pais_destino_mercadorias = cd_pais_destino_mercadorias;
    }
    public String getNm_cidade_prop() {
        return nm_cidade_prop;
    }
    public void setNm_cidade_prop(String nm_cidade_prop) {
        this.nm_cidade_prop = nm_cidade_prop;
    }
    public String getRota_observacao4() {
        return rota_observacao4;
    }
    public void setRota_observacao4(String rota_observacao4) {
        this.rota_observacao4 = rota_observacao4;
    }
    public String getRota_observacao5() {
        return rota_observacao5;
    }
    public void setRota_observacao5(String rota_observacao5) {
        this.rota_observacao5 = rota_observacao5;
    }
    public String getRota_observacao6() {
        return rota_observacao6;
    }
    public void setRota_observacao6(String rota_observacao6) {
        this.rota_observacao6 = rota_observacao6;
    }
    public String getRota_observacao7() {
        return rota_observacao7;
    }
    public void setRota_observacao7(String rota_observacao7) {
        this.rota_observacao7 = rota_observacao7;
    }
    public String getRota_observacao8() {
        return rota_observacao8;
    }
    public void setRota_observacao8(String rota_observacao8) {
        this.rota_observacao8 = rota_observacao8;
    }
    public String getRota_observacao9() {
        return rota_observacao9;
    }
    public void setRota_observacao9(String rota_observacao9) {
        this.rota_observacao9 = rota_observacao9;
    }
    public String getCnpj_ie() {
        return cnpj_ie;
    }
    public void setCnpj_ie(String cnpj_ie) {
        this.cnpj_ie = cnpj_ie;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getFone() {
        return fone;
    }
    public void setFone(String fone) {
        this.fone = fone;
    }
    public String getNm_carimbo() {
        return nm_carimbo;
    }
    public void setNm_carimbo(String nm_carimbo) {
        this.nm_carimbo = nm_carimbo;
    }
    public String getRota_observacao10() {
        return rota_observacao10;
    }
    public void setRota_observacao10(String rota_observacao10) {
        this.rota_observacao10 = rota_observacao10;
    }
    public String getRota_observacao11() {
        return rota_observacao11;
    }
    public void setRota_observacao11(String rota_observacao11) {
        this.rota_observacao11 = rota_observacao11;
    }
    public String getRota_observacao12() {
        return rota_observacao12;
    }
    public void setRota_observacao12(String rota_observacao12) {
        this.rota_observacao12 = rota_observacao12;
    }
}