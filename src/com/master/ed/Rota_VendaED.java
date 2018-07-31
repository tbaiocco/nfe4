/*
 * Created on 01/10/2004
 */
package com.master.ed;

import com.master.util.JavaUtil;

/**
 * @author Andre Valadas
 */
public class Rota_VendaED extends MasterED{
	
    public Rota_VendaED(String oid_Vendedor) {
        this.oid_Vendedor = oid_Vendedor;
    }
    public Rota_VendaED(String oid_Vendedor, String oid_Cliente) {
        this.oid_Vendedor = oid_Vendedor;
        this.oid_Cliente = oid_Cliente;
    }
    public Rota_VendaED() {
        super();
    }
    
	private int oid_Rota_Venda;
	private String oid_Vendedor;
	private String oid_Cliente;
	private String CD_Rota_Venda;
	private int NR_Sequencia;
	private String DM_Dia;
	private String DM_Situacao;
	
	//*** Implementados
	private String CD_Vendedor;
	private String NM_Vendedor;
	private String CD_Cliente;
	private String NM_Cliente;
	
    
	//*** Para montar o código da Rota
    public static String getCD_Dia(String DM_Dia, int NR_Sequencia) {
        
        String Codigo = "";
        String Sequencia = String.valueOf(NR_Sequencia);
        if (DM_Dia.toUpperCase().equals("SEG"))
            Codigo = "A";
        else if (DM_Dia.toUpperCase().equals("TER"))
            Codigo = "B";
        else if (DM_Dia.toUpperCase().equals("QUA"))
            Codigo = "C";
        else if (DM_Dia.toUpperCase().equals("QUI"))
            Codigo = "D";
        else if (DM_Dia.toUpperCase().equals("SEX"))
            Codigo = "E";
        else if (DM_Dia.toUpperCase().equals("SAB"))
            Codigo = "F";
        else if (DM_Dia.toUpperCase().equals("DOM"))
            Codigo = "G";
        else // System.err.println("DM_DIA não reconhecido!!");         
        Codigo += JavaUtil.LFill(Sequencia, 3, true);
        return Codigo;
    }
    //*** Para montar o código da Rota
    public static String getCD_Dia(String CD_Dia) {
        
        if ("A".equals(CD_Dia.toUpperCase()))
            return "SEG";
        else if ("B".equals(CD_Dia.toUpperCase()))
            return "TER";
        else if ("C".equals(CD_Dia.toUpperCase()))
            return "QUA";
        else if ("D".equals(CD_Dia.toUpperCase()))
            return "QUI";
        else if ("E".equals(CD_Dia.toUpperCase()))
            return "SEX";
        else if ("F".equals(CD_Dia.toUpperCase()))
            return "SAB";
        else if ("G".equals(CD_Dia.toUpperCase()))
            return "DOM";
        else return "XXX";
    }
    
    public String getDiaSemana() {
        if (DM_Dia.equals("SEG"))
            return "A - Segunda-Feira";
        else if (DM_Dia.equals("TER"))
            return "B - Terça-Feira";
        else if (DM_Dia.equals("QUA"))
            return "C - Quarta-Feira";
        else if (DM_Dia.equals("QUI"))
            return "D - Quinta-Feira";
        else if (DM_Dia.equals("SEX"))
            return "E - Sexta-Feira";
        else if (DM_Dia.equals("SAB"))
            return "F - Sábado";
        else if (DM_Dia.equals("DOM"))
            return "G - Domingo";
        else return "Não Encontrado!";
    }
    /**
     * @return Returns the cD_Cliente.
     */
    public String getCD_Cliente() {
        return CD_Cliente;
    }
    /**
     * @param cliente The cD_Cliente to set.
     */
    public void setCD_Cliente(String cliente) {
        CD_Cliente = cliente;
    }
    /**
     * @return Returns the cD_Rota_Venda.
     */
    public String getCD_Rota_Venda() {
        return CD_Rota_Venda;
    }
    /**
     * @param rota_Venda The cD_Rota_Venda to set.
     */
    public void setCD_Rota_Venda(String rota_Venda) {
        CD_Rota_Venda = rota_Venda;
    }
    /**
     * @return Returns the cD_Vendedor.
     */
    public String getCD_Vendedor() {
        return CD_Vendedor;
    }
    /**
     * @param vendedor The cD_Vendedor to set.
     */
    public void setCD_Vendedor(String vendedor) {
        CD_Vendedor = vendedor;
    }
    /**
     * @return Returns the dM_Dia.
     */
    public String getDM_Dia() {
        return DM_Dia;
    }
    /**
     * @param dia The dM_Dia to set.
     */
    public void setDM_Dia(String dia) {
        DM_Dia = dia;
    }
    /**
     * @return Returns the dM_Situacao.
     */
    public String getDM_Situacao() {
        return DM_Situacao;
    }
    /**
     * @param situacao The dM_Situacao to set.
     */
    public void setDM_Situacao(String situacao) {
        DM_Situacao = situacao;
    }
    /**
     * @return Returns the nM_Cliente.
     */
    public String getNM_Cliente() {
        return NM_Cliente;
    }
    /**
     * @param cliente The nM_Cliente to set.
     */
    public void setNM_Cliente(String cliente) {
        NM_Cliente = cliente;
    }
    /**
     * @return Returns the nM_Vendedor.
     */
    public String getNM_Vendedor() {
        return NM_Vendedor;
    }
    /**
     * @param vendedor The nM_Vendedor to set.
     */
    public void setNM_Vendedor(String vendedor) {
        NM_Vendedor = vendedor;
    }
    /**
     * @return Returns the nR_Sequencia.
     */
    public int getNR_Sequencia() {
        return NR_Sequencia;
    }
    /**
     * @param sequencia The nR_Sequencia to set.
     */
    public void setNR_Sequencia(int sequencia) {
        NR_Sequencia = sequencia;
    }
    /**
     * @return Returns the oid_Cliente.
     */
    public String getOid_Cliente() {
        return oid_Cliente;
    }
    /**
     * @param oid_Cliente The oid_Cliente to set.
     */
    public void setOid_Cliente(String oid_Cliente) {
        this.oid_Cliente = oid_Cliente;
    }
    /**
     * @return Returns the oid_Rota_Venda.
     */
    public int getOid_Rota_Venda() {
        return oid_Rota_Venda;
    }
    /**
     * @param oid_Rota_Venda The oid_Rota_Venda to set.
     */
    public void setOid_Rota_Venda(int oid_Rota_Venda) {
        this.oid_Rota_Venda = oid_Rota_Venda;
    }
    /**
     * @return Returns the oid_Vendedor.
     */
    public String getOid_Vendedor() {
        return oid_Vendedor;
    }
    /**
     * @param oid_Vendedor The oid_Vendedor to set.
     */
    public void setOid_Vendedor(String oid_Vendedor) {
        this.oid_Vendedor = oid_Vendedor;
    }
}
