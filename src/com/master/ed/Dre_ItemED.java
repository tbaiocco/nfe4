package com.master.ed;

import java.io.Serializable;
import com.master.rn.DreRN;
import com.master.rn.Dre_ItemRN;
import com.master.util.Excecoes;

/**
 * @author Régis Steigleder
 * @serial Itens DRE
 * @serialData 02/2006
 */
public class Dre_ItemED extends RelatorioBaseED implements Serializable {

    public Dre_ItemED() {
        super();
    }
    
    private long oid_Dre_Item;
    private String cd_Estrutural;
    private String nm_Item;
    private String dm_Tipo_Conta;
    private String dm_Acao;
    private Integer nr_Grau;
    private long oid_Dre;
    // Funções 
    private boolean incluidoOK;
    private boolean codigoExistente;  
    //Campos de tela
    private String cd_Unidade;
    private long oid_Unidade;
    private String nm_Fantasia;
    private String cd_Dre;
    private String nm_Dre;
    private String tx_Mes_Ano_Base;
    private String dm_Mod_Saida;
    //Campos de relatorios
    private double vl_Valor1;
    private double vl_Valor2;
    private double vl_Valor3;
    private double vl_Valor4;
    private double vl_Valor5;
    private double vl_Valor6;
    private double vl_Valor7;
    private double vl_Valor8;
    private double vl_Valor9;
    private double vl_Perc1;
    private double vl_Perc2;
    private double vl_Perc3;
    private double vl_Perc4;
    private double vl_Perc5;
    private double vl_Perc6;
    private double vl_Perc7;
    private double vl_Perc8;
    private double vl_Perc9;
    private String tx_Mes1;
    private String tx_Mes2;
    private String tx_Mes3;
    private String tx_Mes4;
    private String tx_Mes5;
    private String tx_Mes6;
    
	public String getCd_Estrutural() {
		return cd_Estrutural;
	}
	public void setCd_Estrutural(String cd_Estrutural) {
		this.cd_Estrutural = cd_Estrutural;
	}
	public String getDm_Acao() {
		return dm_Acao;
	}
	public void setDm_Acao(String dm_Acao) {
		this.dm_Acao = dm_Acao;
	}
	public String getDm_Tipo_Conta() {
		return dm_Tipo_Conta;
	}
	public void setDm_Tipo_Conta(String dm_Tipo_Conta) {
		this.dm_Tipo_Conta = dm_Tipo_Conta;
	}
	public String getNm_Item() {
		return nm_Item;
	}
	public void setNm_Item(String nm_Item) {
		this.nm_Item = nm_Item;
	}
	public Integer getNr_Grau() {
		return nr_Grau;
	}
	public void setNr_Grau(Integer nr_Grau) {
		this.nr_Grau = nr_Grau;
	}
	public long getOid_Dre() {
		return oid_Dre;
	}
	public void setOid_Dre(long oid_Dre) {
		this.oid_Dre = oid_Dre;
	}
	public long getOid_Dre_Item() {
		return oid_Dre_Item;
	}
	public void setOid_Dre_Item(long oid_Dre_Item) {
		this.oid_Dre_Item = oid_Dre_Item;
	}
	public String getCd_Unidade() {
		return cd_Unidade;
	}
	public void setCd_Unidade(String cd_Unidade) {
		this.cd_Unidade = cd_Unidade;
	}
	public String getNm_Fantasia() {
		return nm_Fantasia;
	}
	public void setNm_Fantasia(String nm_Fantasia) {
		this.nm_Fantasia = nm_Fantasia;
	}
	public long getOid_Unidade() {
		return oid_Unidade;
	}
	public void setOid_Unidade(long oid_Unidade) {
		this.oid_Unidade = oid_Unidade;
	}
	public String getCd_Dre() {
		return cd_Dre;
	}
	public void setCd_Dre(String cd_Dre) {
		this.cd_Dre = cd_Dre;
	}
	public String getTx_Mes_Ano_Base() {
		return tx_Mes_Ano_Base;
	}
	public void setTx_Mes_Ano_Base(String tx_Mes_Ano_Base) {
		this.tx_Mes_Ano_Base = tx_Mes_Ano_Base;
	}
	public String getDm_Mod_Saida() {
		return dm_Mod_Saida;
	}
	public void setDm_Mod_Saida(String dm_Mod_Saida) {
		this.dm_Mod_Saida = dm_Mod_Saida;
	}
	public String getNm_Dre() {
		return nm_Dre;
	}
	public void setNm_Dre(String nm_Dre) {
		this.nm_Dre = nm_Dre;
	}
	public double getVl_Perc1() {
		return vl_Perc1;
	}
	public void setVl_Perc1(double vl_Perc1) {
		this.vl_Perc1 = vl_Perc1;
	}
	public double getVl_Perc2() {
		return vl_Perc2;
	}
	public void setVl_Perc2(double vl_Perc2) {
		this.vl_Perc2 = vl_Perc2;
	}
	public double getVl_Perc3() {
		return vl_Perc3;
	}
	public void setVl_Perc3(double vl_Perc3) {
		this.vl_Perc3 = vl_Perc3;
	}
	public double getVl_Perc4() {
		return vl_Perc4;
	}
	public void setVl_Perc4(double vl_Perc4) {
		this.vl_Perc4 = vl_Perc4;
	}
	public double getVl_Perc5() {
		return vl_Perc5;
	}
	public void setVl_Perc5(double vl_Perc5) {
		this.vl_Perc5 = vl_Perc5;
	}
	public double getVl_Perc6() {
		return vl_Perc6;
	}
	public void setVl_Perc6(double vl_Perc6) {
		this.vl_Perc6 = vl_Perc6;
	}
	public double getVl_Perc7() {
		return vl_Perc7;
	}
	public void setVl_Perc7(double vl_Perc7) {
		this.vl_Perc7 = vl_Perc7;
	}
	public double getVl_Perc8() {
		return vl_Perc8;
	}
	public void setVl_Perc8(double vl_Perc8) {
		this.vl_Perc8 = vl_Perc8;
	}
	public double getVl_Perc9() {
		return vl_Perc9;
	}
	public void setVl_Perc9(double vl_Perc9) {
		this.vl_Perc9 = vl_Perc9;
	}
	public double getVl_Valor1() {
		return vl_Valor1;
	}
	public void setVl_Valor1(double vl_Valor1) {
		this.vl_Valor1 = vl_Valor1;
	}
	public double getVl_Valor2() {
		return vl_Valor2;
	}
	public void setVl_Valor2(double vl_Valor2) {
		this.vl_Valor2 = vl_Valor2;
	}
	public double getVl_Valor3() {
		return vl_Valor3;
	}
	public void setVl_Valor3(double vl_Valor3) {
		this.vl_Valor3 = vl_Valor3;
	}
	public double getVl_Valor4() {
		return vl_Valor4;
	}
	public void setVl_Valor4(double vl_Valor4) {
		this.vl_Valor4 = vl_Valor4;
	}
	public double getVl_Valor5() {
		return vl_Valor5;
	}
	public void setVl_Valor5(double vl_Valor5) {
		this.vl_Valor5 = vl_Valor5;
	}
	public double getVl_Valor6() {
		return vl_Valor6;
	}
	public void setVl_Valor6(double vl_Valor6) {
		this.vl_Valor6 = vl_Valor6;
	}
	public double getVl_Valor7() {
		return vl_Valor7;
	}
	public void setVl_Valor7(double vl_Valor7) {
		this.vl_Valor7 = vl_Valor7;
	}
	public double getVl_Valor8() {
		return vl_Valor8;
	}
	public void setVl_Valor8(double vl_Valor8) {
		this.vl_Valor8 = vl_Valor8;
	}
	public double getVl_Valor9() {
		return vl_Valor9;
	}
	public void setVl_Valor9(double vl_Valor9) {
		this.vl_Valor9 = vl_Valor9;
	}
	public String getTx_Mes1() {
		return tx_Mes1;
	}
	public void setTx_Mes1(String tx_Mes1) {
		this.tx_Mes1 = tx_Mes1;
	}
	public String getTx_Mes2() {
		return tx_Mes2;
	}
	public void setTx_Mes2(String tx_Mes2) {
		this.tx_Mes2 = tx_Mes2;
	}
	public String getTx_Mes3() {
		return tx_Mes3;
	}
	public void setTx_Mes3(String tx_Mes3) {
		this.tx_Mes3 = tx_Mes3;
	}
	public String getTx_Mes4() {
		return tx_Mes4;
	}
	public void setTx_Mes4(String tx_Mes4) {
		this.tx_Mes4 = tx_Mes4;
	}
	public String getTx_Mes5() {
		return tx_Mes5;
	}
	public void setTx_Mes5(String tx_Mes5) {
		this.tx_Mes5 = tx_Mes5;
	}
	public String getTx_Mes6() {
		return tx_Mes6;
	}
	public void setTx_Mes6(String tx_Mes6) {
		this.tx_Mes6 = tx_Mes6;
	}
	/**
	 * Separa a conta com grau imediatamente inferior
	 */
	public String pegaGrauInferior() {
		int i = this.cd_Estrutural.lastIndexOf(".");
		if (i < 0) {
			return "*";
		}else {
			return this.cd_Estrutural.substring(0, i);
		}	
	}
	/**
	 * Calcula o grau ( soma quantos pontos tem o código estrutural ) 
	 */	
	public Integer calcGrau(String cd_Estrutural){
		int cta = 0;
		int sai = 0 ;
		do {
			sai = cd_Estrutural.indexOf(".",sai);
			if (sai > -1 ){
				cta++;
				sai++;
			}	
		} while (sai > 0);
		return new Integer(cta);
	}	
	/**
	 * isIncluidoOK
	 * Verifica se o código já existe para inclusão
	 * Retorna false se existir e não inclui
	 * @return boolean
	 * @throws Excecoes
	 */
	public boolean isIncluidoOK() throws Excecoes {
		if (this.isCodigoExistente() == false){
			Dre_ItemRN rn = new Dre_ItemRN();
			rn.inclui(this);
			return true;
		}else{
			return false;
		}
	}
	public void setIncluidoOK(boolean incluidoOK) {
		this.incluidoOK = incluidoOK;
	}
	/***
	 * isCodigoExistente()
	 * Verifica se o codigo deste ed já existe cadastrado 
	 * @return boolean
	 * @throws Excecoes
	 */
	public boolean isCodigoExistente()  throws Excecoes {
		boolean dmVolta = true;
		DreED ed = new DreED();                		  // Instancia ed para a busca
		ed.setCd_Dre(this.getCd_Estrutural()); 		  // Busca o registro
		ed = new DreRN().getByRecord(ed);          	  // Retorna o registro no mesmo ed
		if (ed.getOid_Dre()== 0){                     // Se registro não existir retorna false
			dmVolta = false;
		}else{
			if (ed.getOid_Dre()== this.getOid_Dre()){ // Se registro é o mesmo oid retorna false	
				dmVolta =  false;
			}	
		}
		return dmVolta;
	}
	public void setCodigoExistente(boolean codigoExistente) {
		this.codigoExistente = codigoExistente;
	}
}