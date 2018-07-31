/*
 * Created on 05/04/2005
 *
 */
package com.master.ed;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Tiago
 *
 */
public abstract class RelatorioBaseED extends MasterED {
    public RelatorioBaseED() {
        super();
    }
    public RelatorioBaseED(HttpServletResponse response, String nomeRelatorio) {
        this.response = response;
        this.nomeRelatorio = nomeRelatorio;
    }

    /** Variáveis do Jasper **/
    private HashMap hashMap; //(OPCIONAL) caso queira incluir algum Parametro diferentes dos DEFAULT(ja vem setado por DEFAULT o PATH_IMAGENS e RELATORIO)
    private List lista; //(OBRIGATÓRIA)
    private Collection sublista = new ArrayList(); //Para subrelatórios
    private Collection sublista1 = new ArrayList(); //Para subrelatórios
    private Collection sublista2 = new ArrayList(); //Para subrelatórios
    private Collection sublista3 = new ArrayList(); //Para subrelatórios
    private HttpServletResponse response; //(OBRIGATÓRIA)
    private HttpServletRequest request; //(OBRIGATÓRIA para o LASZLO)
    private String nomeRelatorio; //(OBRIGATÓRIA)
    private String descFiltro;
    private String cabecalho_Titulo;
    private String cabecalho_Empresa;
    private String cabecalho_Unidade;
    private String cabecalho_Periodo;
    private String filtro_1;
    private String filtro_2;
    private String filtro_3;
    private String filtro_4;
    private String filtro_5;
    private String filtro_6;
    private String filtro_7;
    private String filtro_8;
    private String filtro_9;
    private String filtro_10;

    //*** Layout
    private String Layout;

    public String getLayout() {
        return Layout;
    }
    public void setLayout(String layout) {
        Layout = layout;
    }
    public HashMap getHashMap() {
        return hashMap;
    }
    public List getLista() {
        return lista;
    }
    public String getNomeRelatorio() {
        return nomeRelatorio;
    }
    public HttpServletResponse getResponse() {
        return response;
    }
    public void setHashMap(HashMap hashMap) {
        this.hashMap = hashMap;
    }
    public void setLista(List lista) {
        this.lista = lista;
    }
    public void setNomeRelatorio(String nomeRelatorio) {
        this.nomeRelatorio = nomeRelatorio;
    }
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }
    public String getDescFiltro() {
        return this.descFiltro;
    }
    public void setDescFiltro(String descFiltro) {
        this.descFiltro = descFiltro;
    }
    public Collection getSublista() {
        return sublista;
    }

    public void setSublista(Collection sublista) {
        this.sublista = sublista;
    }
    public Collection getSublista2() {
        return sublista2;
    }
    public void setSublista2(Collection sublista2) {
        this.sublista2 = sublista2;
    }
    public Collection getSublista3() {
        return sublista3;
    }
    public void setSublista3(Collection sublista3) {
        this.sublista3 = sublista3;
    }

    public Collection getSublista1() {
		return sublista1;
	}
	public void setSublista1(Collection sublista1) {
		this.sublista1 = sublista1;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public String getCabecalho_Empresa() {
		return cabecalho_Empresa;
	}
	public void setCabecalho_Empresa(String cabecalho_Empresa) {
		this.cabecalho_Empresa = cabecalho_Empresa;
	}
	public String getCabecalho_Periodo() {
		return cabecalho_Periodo;
	}
	public void setCabecalho_Periodo(String cabecalho_Periodo) {
		this.cabecalho_Periodo = cabecalho_Periodo;
	}
	public String getCabecalho_Unidade() {
		return cabecalho_Unidade;
	}
	public void setCabecalho_Unidade(String cabecalho_Unidade) {
		this.cabecalho_Unidade = cabecalho_Unidade;
	}
	public String getFiltro_1() {
		return filtro_1;
	}
	public void setFiltro_1(String filtro_1) {
		this.filtro_1 = filtro_1;
	}
	public String getFiltro_10() {
		return filtro_10;
	}
	public void setFiltro_10(String filtro_10) {
		this.filtro_10 = filtro_10;
	}
	public String getFiltro_2() {
		return filtro_2;
	}
	public void setFiltro_2(String filtro_2) {
		this.filtro_2 = filtro_2;
	}
	public String getFiltro_3() {
		return filtro_3;
	}
	public void setFiltro_3(String filtro_3) {
		this.filtro_3 = filtro_3;
	}
	public String getFiltro_4() {
		return filtro_4;
	}
	public void setFiltro_4(String filtro_4) {
		this.filtro_4 = filtro_4;
	}
	public String getFiltro_5() {
		return filtro_5;
	}
	public void setFiltro_5(String filtro_5) {
		this.filtro_5 = filtro_5;
	}
	public String getFiltro_6() {
		return filtro_6;
	}
	public void setFiltro_6(String filtro_6) {
		this.filtro_6 = filtro_6;
	}
	public String getFiltro_7() {
		return filtro_7;
	}
	public void setFiltro_7(String filtro_7) {
		this.filtro_7 = filtro_7;
	}
	public String getFiltro_8() {
		return filtro_8;
	}
	public void setFiltro_8(String filtro_8) {
		this.filtro_8 = filtro_8;
	}
	public String getFiltro_9() {
		return filtro_9;
	}
	public void setFiltro_9(String filtro_9) {
		this.filtro_9 = filtro_9;
	}
	public String getCabecalho_Titulo() {
		return cabecalho_Titulo;
	}
	public void setCabecalho_Titulo(String cabecalho_Titulo) {
		this.cabecalho_Titulo = cabecalho_Titulo;
	}
}
