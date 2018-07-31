/*
 * Relatorio_Impostos_Notas_FiscaisED.java
 *
 * Created on 8 de Março de 2004, 10:35
 */

package com.master.ed;

/**
 *
 * @author  administrador
 */
public class Relatorio_Impostos_Notas_FiscaisED {
    
    String nm_razao_social;
    String nr_nota_fiscal;
    String dt_entrada;
    String vl_pis;
    String vl_inss;
    String vl_irrf;
    String vl_cofins;
    String vl_csll;
    String vl_isqn;
    String vl_liquido_nota_fiscal;

    /** Creates a new instance of Relatorio_Impostos_Notas_FiscaisED */
    public Relatorio_Impostos_Notas_FiscaisED() {
    }
    

    public void setNm_razao_social(String nm_razao_social){
        this.nm_razao_social = nm_razao_social;
    }
    public void setNr_nota_fiscal(String nr_nota_fiscal){
        this.nr_nota_fiscal = nr_nota_fiscal;
    }
    public void setDt_entrada(String dt_entrada){
        this.dt_entrada = dt_entrada;
    }
    public void setVl_pis(String vl_pis){
        this.vl_pis = vl_pis;
    }
    public void setVl_inss(String vl_inss){
        this.vl_inss = vl_inss;
    }
    public void setVl_irrf(String vl_irrf){
        this.vl_irrf = vl_irrf;
    }
    public void setVl_cofins(String vl_cofins){
        this.vl_cofins = vl_cofins;
    }
    public void setVl_csll(String vl_csll){
    	this.vl_csll = vl_csll;
    }
    public void setVl_isqn(String vl_isqn) {
        this.vl_isqn = vl_isqn;
    }
    public void setVl_liquido_nota_fiscal(String vl_liquido_nota_fiscal){
        this.vl_liquido_nota_fiscal = vl_liquido_nota_fiscal;
    }

    public String getNm_razao_social(){
        return this.nm_razao_social;
    }
    public String getNr_nota_fiscal(){
        return this.nr_nota_fiscal;
    }
    public String getDt_entrada(){
        return this.dt_entrada;
    }
    public String getVl_pis(){
        return this.vl_pis;
    }
    public String getVl_inss(){
        return this.vl_inss;
    }
    public String getVl_irrf(){
        return this.vl_irrf;
    }
    public String getVl_cofins(){
        return this.vl_cofins;
    }
    public String getVl_csll(){
    	return this.vl_csll;
    }
    public String getVl_isqn() {
        return this.vl_isqn;
    }
    public String getVl_liquido_nota_fiscal(){
        return this.vl_liquido_nota_fiscal;
    }

}
