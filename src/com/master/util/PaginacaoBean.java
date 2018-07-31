package com.master.util;

/**
 * Título: PaginacaoBean Descrição: Bean que faz a paginação das consultas Data
 * da criação: 10/2003 Atualizado em: 12/2003 Empresa: ÊxitoLogística Mastercom
 * Autor: Carlos Eduardo de Holleben
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.master.util.ed.Parametro_FixoED;

public class PaginacaoBean {

    Parametro_FixoED parametro_FixoED = new Parametro_FixoED();
    // Variável que define se terá cabeçalho
    boolean bCabecalho = true;

    //DECLARAÇÃO DE VARIÁVEIS
    //Variáveis de conexão
    Connection cn = null;
    Statement s = null;
    boolean conectado; // Conectado sim ou não?

    //Dados da conexão
    public String dbURL = "";
    public String pwd = "";
    public String usuario = "";

    //Variáveis para as funções de Agregados, como a SUM
    //de algum campo numérico.
    String strAgregados = "";
    String strcadeiaFA = "";

    //Variáveis globais para saber se existe um elemento ou não e
    //auxiliar para guardar o target do enlace.
    boolean encontrado = false;
    String strTarget = "";

    //Variáveis para guardar a cadeia que mostrará o rodapé com os
    //números de página.
    String strcadeiaPaginas = "";

    //Variáveis para as cores.
    String corFundoTabela = "#000066";
    String corCabecalhoTabela = "#CCCCCC";
    String corRodapeTabela = "#DDDDDD";
    String corTextoFuncoesAgregado = "#123456";
    String corTextoCabecalhoTabela = "#000000";
    String corFilaImpar = "#FFFFFF";
    String corFilaPar = "#EFEFEF";
    String corFundoTitulo = "#000066";
    String corTextoTitulo = "#FFFFFF";
    String corTextoFilas = "#123456";

    //Estas são genéricas para o formato dos resultados, como
    //a largura da tabela, o tamanho da letra, o tipo da letra etc.
    String strLarguraTabela = "70%";
    String strTamanhoLetra = "2";
    String strHtml_sem_resultados = "";
    String strTitulo_Consulta = "Consulta";
    String strTipoLetra = "Verdana";
    boolean bl_mostrar_paginaXdeY = false;
    boolean bl_mostrar_paginas = true;
    String strMensagem_nao_ha_resultados = "<br><P><b><font size='" + strTamanhoLetra + "' face='" + strTipoLetra
            + "' color='#ff1111'>Não foram encontrados registros para esta consulta!</font></b><P>";

    //Talvez as mais importantes são estas variáveis que são arrays
    //das dimensões.
    String arCampos_IrA[][];
    String arParametrosAdicionais[][];
    String arTitulo_Campos[];
    String arOrdenar_Campos[][];
    String arFAgregados_Campos[][];
    String arNomes_Campos[];
    String arPassagemDeParametros[][];

    //Necesitamos uma variável para saber em que campo vamos
    //contar o número de registros que há na consulta.
    String strCampoContarRegistros = "";

    //Ao clicar na página seguinte ou anterior, terá que
    //dizer ao bean o nome da página.
    String que_pagina = "wmsL002.jsp";

    //Variáveis que formarão a consulta. Muito importantes.
    String strSelect = "";
    String strFrom = "";
    String strGroupBy = "";
    String strWhere = "";
    String strOrderBy = "";
    String enlace1, enlace2 = "";
    boolean hay_orderby = false;

    //Variável para passar outros parâmetros se fizerem falta.
    //ao estilo de "&miparam1=valor1&miparam2=valor2"
    String strParametrosPaginacao = "&";

    //Esta variável segue aqui porém não implementaremos a impressão.
    boolean imprimir = true;

    //Típica variável que me ajudará a contar registros.
    int contador = 1;

    //Quantos registros por página, se inicializa em 10.
    int registros = 10;

    //Esta variável me diz em que registro estamos, para mostrar
    //o resto a partir deste.
    int RegistroAtual = 1;

    public PaginacaoBean() throws Exception {
        //        usuario = "postgres";
        //        pwd = "panazzolo";
        //        dbURL = "jdbc:postgresql://192.168.10.215:5432/panazzolo";

        usuario = parametro_FixoED.getNM_Database_Usuario();
        pwd = parametro_FixoED.getNM_Database_Pwd();
        dbURL = parametro_FixoED.getNM_Database_URL();

        Conectar();
    }

    public void Conectar() throws Exception {
        conectado = true;
        try {
            Class.forName("org.postgresql.Driver");
            cn = DriverManager.getConnection(dbURL, usuario, pwd);
            s = cn.createStatement();
        } catch (Exception e) {
            conectado = false;
            throw e;
        }
    }

    public void Fazer_cadeiaSemDados() {
        strHtml_sem_resultados = "<P>";
        strHtml_sem_resultados += strMensagem_nao_ha_resultados;
        strHtml_sem_resultados += " <P>&nbsp;</P>     ";
    }

    public boolean TemRegistros() {
        String strsql = "";
        //CRIA-SE O SELECT
        strsql = strSelect;
        strsql += strFrom;
        strsql += strWhere;
        if (strGroupBy != null) {
            strsql += strGroupBy;
        }
        strsql += strOrderBy;
        boolean bolTem = false;
        int intTotalReg = 0;
        try {
            Statement s1 = cn.createStatement();
            ResultSet rsTotalRegistros = s1.executeQuery("select count(" + strCampoContarRegistros + ") AS TOTAL_REGISTROS " + strFrom + strWhere);
            if (rsTotalRegistros != null) {
                rsTotalRegistros.next();
                intTotalReg = rsTotalRegistros.getInt("TOTAL_REGISTROS");
                if (intTotalReg > 0)
                    bolTem = true;
            }
            rsTotalRegistros.close();
            s1.close();
        } catch (Exception j) {
        }
        return bolTem;
    }

    public void CerrarConexion() {
        try {
            s.close();
            cn.close();
        } catch (Exception e) {
        }
    }

    public void setCabecalho(boolean SiNo) {
        bCabecalho = SiNo;
    }

    public void setTextoNaoHaResultados(String v_mensagem) {
        strMensagem_nao_ha_resultados = v_mensagem;
    }

    public void setParametrosPaginacao(String v_otros) {
        strParametrosPaginacao = v_otros;
    }

    public String getParametrosPaginacao() {
        return strParametrosPaginacao;
    }

    public void setLarguraTabela(String strLargura) {
        strLarguraTabela = strLargura;
    }

    public void setcorFundoTabela(String RGBcor) {
        corFundoTabela = RGBcor;
    }

    public void setcorCabecalhoTabela(String RGBcor) {
        corCabecalhoTabela = RGBcor;
    }

    public void setTipoLetra(String strTipoLetra) {
        this.strTipoLetra = strTipoLetra;
    }

    public void setcorRodapeTabela(String RGBcor) {
        corRodapeTabela = RGBcor;
    }

    public void setTamanhoLetra(String tamanho) {
        strTamanhoLetra = tamanho;
    }

    public void setcorTextoCabecalhoTabela(String RGBcor) {
        corTextoCabecalhoTabela = RGBcor;
    }

    public void setcorFilaImpar(String RGBcor) {
        corFilaImpar = RGBcor;
    }

    public void setcorFilaPar(String RGBcor) {
        corFilaPar = RGBcor;
    }

    public void setcorTextoFilas(String RGBcor) {
        corTextoFilas = RGBcor;
    }

    public void setcorTextoTitulo(String RGBcor) {
        corTextoTitulo = RGBcor;
    }

    public void setRegistros_Pagina(int intRegistros) {
        registros = intRegistros;
    }

    public void setRegistroAtual(int intRegistro) {
        RegistroAtual = intRegistro;
    }

    public void setTitulos_Campos(String array_titulos_campos[]) {
        arTitulo_Campos = array_titulos_campos;
    }

    public void setPassagemDeParametros(String array_PassagemDeParametros[][]) {
        arPassagemDeParametros = array_PassagemDeParametros;
    }

    public void setOrdenar_Campo(String array_ordenar_campos[][]) {
        arOrdenar_Campos = array_ordenar_campos;
    }

    public void setTitulo_Consulta(String strTitulo) {
        strTitulo_Consulta = strTitulo;
    }

    public void setCamposIrA(String array_CamposIRA[][]) {
        arCampos_IrA = array_CamposIRA;
    }

    public void setParametrosAdicionais(String array_ParametrosAdicionais[][]) {
        arParametrosAdicionais = array_ParametrosAdicionais;
    }

    public String[][] getCamposIrA() {
        return arCampos_IrA;
    }

    public void setPagina(String strPagina) {
        que_pagina = strPagina;
    }

    public void setCampoContarRegistros(String Campo) {
        strCampoContarRegistros = Campo;
    }

    public String getCampoContarRegistros() {
        return strCampoContarRegistros;
    }

    public void setFAgregados_Campo(String array1[][]) {
        arFAgregados_Campos = array1;
    }

    public void setNomes_Campos(String array_nombres_campos[]) {
        arNomes_Campos = array_nombres_campos;
    }

    public void setMostrar_paginaXdeY(boolean SioNo) {
        bl_mostrar_paginaXdeY = SioNo;
    }

    public void setMostrar_Paginas(boolean SioNo) {
        bl_mostrar_paginas = SioNo;
    }

    public void setSelect(String strSelect1) {
        strSelect = strSelect1;
    }

    public void setFrom(String strFrom1) {
        strFrom = strFrom1;
    }

    public void setGroupBy(String strGroupBy1) {
        strGroupBy = strGroupBy1;
    }

    public void setWhere(String strWhere1) {
        strWhere = strWhere1;
    }

    public void setOrderBy(String strOrderBy1) {
        strOrderBy = strOrderBy1;
    }

    public String Mostrar_Registros() {
        String strsql = "";
        String strcadeia = "";
        int contador = 1;
        int i = 0;

        // CRIA O SELECT
        strsql = strSelect;
        strsql += strFrom;
        strsql += strWhere;

        // GROUP BY 25/02/2002

        if (strGroupBy != null) {
            strsql += strGroupBy;
        }
        strsql += strOrderBy;

        // System.out.println(strsql + " Paginacao ");

        try {

            // ********************************************
            // SACA OS DADOS PARA AS FUNÇÕES DOS AGREGADOS.
            // ********************************************

            Statement sfa = cn.createStatement();
            ResultSet rsAgregados;

            if (arFAgregados_Campos != null) {
                for (int mk = 0; mk < arFAgregados_Campos.length; mk++) {
                    strAgregados = "SELECT ";
                    strAgregados += "'<DIV align=\"" + arFAgregados_Campos[mk][4] + "\">' || TO_CHAR(" + arFAgregados_Campos[mk][2] + "(" + arFAgregados_Campos[mk][1]
                            + "),'99,999,999,990,90') || '</div>' ";
                    strAgregados += strFrom;
                    strAgregados += strWhere;
                    //// System.out.println(strAgregados);
                    rsAgregados = sfa.executeQuery(strAgregados);
                    if (rsAgregados != null) {
                        rsAgregados.next();
                        arFAgregados_Campos[mk][3] = rsAgregados.getString(1);
                    }
                    // CALCULA O NÚMERO DE PÁGINAS QUE HÁ NO SELECT
                    rsAgregados.close();
                }
            }

            // *******************************************
            // SACA O NÚMERO DE REGISTROS DA TABELA
            // *******************************************
            int intTotalReg = 0;
            Statement s1 = cn.createStatement();
            ResultSet rsTotalRegistros = s1.executeQuery("select count (" + strCampoContarRegistros + ") AS TOTAL_REGISTROS  " + strFrom + strWhere);
            if (rsTotalRegistros != null) {
                rsTotalRegistros.next();
                intTotalReg = rsTotalRegistros.getInt("TOTAL_REGISTROS");
            }
            // CALCULA O NÚMERO DE PÁGINAS QUE EXISTEM NO SELECT
            // *************************************************
            int Total_Paginas = 0;
            if (intTotalReg > 0) {
                Total_Paginas = intTotalReg / registros;
                if (intTotalReg % registros > 0)
                    Total_Paginas++;
            }

            rsTotalRegistros.close();
            s1.close();

            // FIM DOS REGISTROS DA TABELA
            // ***************************
            if (intTotalReg > 0 && (RegistroAtual >= 1)) {
                strcadeia += "<table width='" + strLarguraTabela + "' border='0' cellspacing='2' cellpadding='1' align='center'>";
                strcadeia += "    <tr bgcor'" + corFundoTabela + "'> ";
                strcadeia += "    <td> ";
                strcadeia += "<table style=\"background: black \" width='100%' cellspacing='1' cellpadding='3' align='center'>";

                if (bCabecalho == true) {
                    strcadeia += "    <tr bgcolor='" + corFundoTitulo + "'> ";

                    if (imprimir) {
                        strcadeia += "    <td colspan='" + arNomes_Campos.length + "'><b><font size='" + strTamanhoLetra + "' face='" + strTipoLetra + "' color='" + corTextoTitulo + "'><b>"
                                + strTitulo_Consulta + "</b></font></b></td>";
                    } else {
                        strcadeia += "    <td colspan='" + (arNomes_Campos.length - 1) + "'><b><font size='" + strTamanhoLetra + "' face='" + strTipoLetra + "' color='" + corTextoTitulo + "'><b>"
                                + strTitulo_Consulta + "</b></font></b></td>";
                        // strcadeia+=" <td align='right'<a
                        // href='imprimir_busqueda.jsp?formulario=1'><image
                        // src='images/printer2a.gif' border='0'
                        // alt='Versi&oacute;n imprimible'></a></td>";
                    }
                    strcadeia += "</tr>";
                }

                strcadeia += " <tr> ";
                // order by passado passado pelo array
                hay_orderby = false;
                for (int k = 0; k < arTitulo_Campos.length; k++) {
                    strcadeia += "<td bgcolor='" + corCabecalhoTabela + "'><font size='" + strTamanhoLetra + "' face='" + strTipoLetra + "' color='" + corTextoCabecalhoTabela + "'><b>"
                            + arTitulo_Campos[k] + "</b></font>";
                    try {
                        for (int mm = 0; mm < arOrdenar_Campos.length; mm++) {
                            if (arTitulo_Campos[k].equals(arOrdenar_Campos[mm][0])) {
                                strcadeia += "&nbsp;&nbsp;<a href=" + que_pagina + "?orderby=order%20by%20" + arOrdenar_Campos[mm][1] + strParametrosPaginacao
                                        + "><img width=\"14\" height=\"15\" align=\"absmiddle\" alt=\"Ordenar Ascendente\" border=\"0\"" + "src=\"../Imagens/order_down1.gif\"></a>&nbsp;<a href="
                                        + que_pagina + "?orderby=order%20by%20" + arOrdenar_Campos[mm][1] + "%20DESC" + strParametrosPaginacao
                                        + "><img width=\"14\" height=\"15\" align=\"absmiddle\" alt=\"Ordenar descendentemente\" border=\"0\"" + "src=\"../Imagens/order_up1.gif\"></a>";
                            }
                        }
                    } catch (Exception e) {
                    }
                    strcadeia += "</td>";
                    // strcadeia+="</tr>";
                    // ********************************************************************
                    // PÕE OS RESULTADOS DAS FUNÇÕES DE AGREGADO EM UMA CADEIA.
                    // strcadeiaFA
                    // ********************************************************************
                    try {
                        if (arFAgregados_Campos.length > 0)
                            strcadeiaFA = " <tr> ";
                    } catch (Exception j) {
                    }
                    for (int yk = 0; yk < arTitulo_Campos.length; yk++) {
                        try {
                            encontrado = false;
                            for (int sa = 0; sa < arFAgregados_Campos.length; sa++) {
                                if (arTitulo_Campos[yk].equals(arFAgregados_Campos[sa][0])) {
                                    encontrado = true;
                                    strcadeiaFA += "<td bgcolor='" + corCabecalhoTabela + "'><font size='" + strTamanhoLetra + "' face='" + strTipoLetra + "' color='" + corTextoFuncoesAgregado
                                            + "'><b>" + arFAgregados_Campos[sa][3] + "</b></font>";
                                }
                            }
                            if (!(encontrado))
                                strcadeiaFA += "<td bgcolor='" + corCabecalhoTabela + "'><img src=\"../Imagens/punto_trans.gif\">";
                        } catch (Exception e) {
                        }
                        strcadeiaFA += "</td>";
                    }
                    try {
                        if (arFAgregados_Campos.length > 0)
                            strcadeiaFA += " </tr> ";
                    } catch (Exception j) {
                    }
                }
                strcadeia += "</tr>";

                ResultSet rs = s.executeQuery(strsql);

                if (intTotalReg > 0 && (rs.next() || RegistroAtual >= 1)) {

                    int contador_registros = 1;
                    int pagina = 1;
                    int registros_anteriores = 1;

                    // ****************************************************************
                    // SABER OS REGISTROS ANTERIORES E A PAGINA EM QUE O USUARIO
                    // ESTA
                    // ****************************************************************
                    while (registros_anteriores < RegistroAtual) {
                        rs.next();
                        registros_anteriores++;
                        // CALCULA QUE PAGINA ESTA SENDO MOSTRADA
                        // ****************************************
                        if (contador_registros >= registros) {
                            contador_registros = 0;
                            pagina++; // VARIÁVEL CUJO VALOR É A PAGINA EM QUE
                                      // SE ESTA
                        }
                        contador_registros++;
                    }

                    // ***************************
                    // PAGINA DA CONSULTA
                    // ***************************
                    int inicio = 1;

                    // SE CALCULA EM QUE PAGINA DEVE COMEÇAR A CADEIA DE PAGINAS
                    // *********************************************************
                    if (pagina > 10)
                        inicio = pagina - 5;
                    else if (pagina == 10)
                        inicio = 9;

                    int contador_paginas = 1;

                    // FAZER A CADEIA QUE SERVEM PARA MOSTRAR AS PAGINAS
                    // *********************************************************
                    for (int y = inicio; y <= Total_Paginas; y++) {
                        if (y != pagina)
                            strcadeiaPaginas += "<a href='" + que_pagina + "?Atual=" + String.valueOf((y * registros) + 1 - registros) + "&Direccion=S&orderby=" + strOrderBy + strParametrosPaginacao
                                    + "'>";
                        else
                            strcadeiaPaginas += "<b><font size='" + strTamanhoLetra + "' face='" + strTipoLetra + "'>[";

                        strcadeiaPaginas += y;

                        if (y != pagina)
                            strcadeiaPaginas += "</a>";
                        else
                            strcadeiaPaginas += "]</font></b>";

                        if (y != Total_Paginas)
                            strcadeiaPaginas += " ";
                        contador_paginas++;
                        if (contador_paginas > 10)
                            break;
                    }

                    // REGISTROS DA CONSULTA
                    // ********************************************
                    do {
                        if (i == 0) {
                            strcadeia += "<tr bgcolor='" + corFilaImpar + "' onClick=\"hacer_click(this)\" onMouseOut=\"salir(this,'" + corFilaImpar + "');\""
                                    + " onMouseOver=\"dentro(this, '#efef99');\">";
                            i = 1;
                        } else {
                            strcadeia += "<tr bgcolor='" + corFilaPar + "' onClick=\"hacer_click(this)\" onMouseOut=\"salir(this,'" + corFilaPar + "');\""
                                    + " onMouseOver=\"dentro(this, '#efef99');\">";
                            i = 0;
                        }

                        String strParametroAdicional = "";
                        int cont_parametros = 0;
                        while (cont_parametros < arParametrosAdicionais.length) {
                            strParametroAdicional += "&" + arParametrosAdicionais[cont_parametros][0] + "=" + arParametrosAdicionais[cont_parametros][1];
                            cont_parametros++;
                        }

                        String strParametro, pag, enlace1;

                        int cont;

                        for (int j = 0; j < arNomes_Campos.length; j++) {

                            strParametro = strParametroAdicional;
                            pag = "";
                            enlace1 = "";
                            cont = 0;
                            String nC = rs.getString(arNomes_Campos[j]);

                            while (cont < arPassagemDeParametros.length) {
                                strParametro += "&" + arPassagemDeParametros[cont][0] + "=" + rs.getString(arPassagemDeParametros[cont][1]);
                                cont++;
                            }

                            // SE CONTROLA SE TERÁ QUE POR <A HREF ..> NO CAMPO
                            int g = 0;
                            boolean enlace = false;

                            while (g < arCampos_IrA.length) {
                                // SIM, TERÁ QUE POR UM LINK ...
                                if (arCampos_IrA[g][0] == arNomes_Campos[j]) {
                                    enlace = true;
                                    strTarget = "";
                                    try {
                                        if (arCampos_IrA[g][2] != null) {
                                            strTarget = " target=" + arCampos_IrA[g][2];
                                        }
                                    } catch (Exception e) {
                                    }

                                    pag = arCampos_IrA[g][1];
                                }
                                g++;
                            }

                            enlace1 = "<a " + strTarget + " href=\"" + pag + "?";

                            // SE IMPRIME A FILA COM O VALOR
                            if (!enlace) {
                                strcadeia += "<td><font size='" + strTamanhoLetra + "' face='" + strTipoLetra + "' color='" + corTextoFilas + "'>" + nC + "</font></td>";
                            } else {
                                strcadeia += "<td><font size='" + strTamanhoLetra + "' face='" + strTipoLetra + "' color='" + corTextoFilas + "'>" + enlace1 + strParametro + "\">" + nC
                                        + "</a></font></td>";
                            }
                        }
                        strcadeia += "</tr>";
                        contador++;
                    } while (contador <= registros && rs.next());

                    // SE IMPRIME AS FUÇÕES DO AGREGADO
                    try {
                        if (arFAgregados_Campos.length > 0)
                            strcadeia += strcadeiaFA;
                    } catch (Exception j) {
                    }
                    // RODAPÉ DA TABELA 1 --> PAGINA 1 DE n
                    // ***************************************
                    if (bl_mostrar_paginaXdeY) {
                        strcadeia += " <tr> ";
                        strcadeia += "<td colspan='" + arTitulo_Campos.length + 1 + "' bgcolor='" + corRodapeTabela + "'><font size='" + strTamanhoLetra + "' face='" + strTipoLetra + "' color='"
                                + corTextoCabecalhoTabela + "'>P\u00e1gina " + pagina + " de " + Total_Paginas + "</font></td>";
                        strcadeia += "</tr> ";
                    }
                    // RODAPÉ DA TABELA --> P\u00E1GINA : 1 2 3 4 5 6 7 8 9
                    // ....... n
                    // **************************************************************
                    if ((bl_mostrar_paginas) && (Total_Paginas >= pagina)) {
                        strcadeia += " <tr> ";
                        strcadeia += "<td colspan='" + arTitulo_Campos.length + 1 + "' bgcolor='" + corRodapeTabela + "' align='center'><font size='" + strTamanhoLetra + "' face='" + strTipoLetra
                                + "' color='" + corTextoCabecalhoTabela + "'>" + strcadeiaPaginas + "</font></td>";
                        strcadeia += "</tr> ";
                    }
                    strcadeia += "</table>";
                    strcadeia += "</td></td></table>";

                    // *************************************************************
                    // SE CALCULA SE TERÁ QUE POR ANTERIOR E/OU SEGUINTE
                    // *************************************************************
                    String StrNavegacao = "";
                    StrNavegacao += "<table width='508' border='0' cellspacing='2' cellpadding='1' align='center'>";
                    StrNavegacao += "<tr align='center'>";
                    if (RegistroAtual >= registros + 1) {
                        StrNavegacao += "<td width='220'>";
                        StrNavegacao += "<div align='right'><a href='" + que_pagina + "?Atual=" + String.valueOf(RegistroAtual - registros) + "&Direccion=Ant&orderby=" + strOrderBy
                                + strParametrosPaginacao + "'>";
                        StrNavegacao += "<font size='" + strTamanhoLetra + "'face='" + strTipoLetra + "'>" + "<img src='../Imagens/back.gif' border='0' alt='" + registros
                                + " registros anteriores'></font></a></div>";
                        StrNavegacao += "</td>";
                    } else {
                        StrNavegacao += "<td width='220'>\u00a0\u00a0&nbsp;</td>";
                    }
                    StrNavegacao += "<td width='220'>\u00a0\u00a0&nbsp;</td>";
                    if ((contador - 1 < intTotalReg) && (RegistroAtual == 1)) {
                        StrNavegacao += "<td width='220'>";
                        StrNavegacao += "<div align='left'><a href='" + que_pagina + "?Atual=" + String.valueOf(RegistroAtual + registros) + "&Direccion=Sig&orderby=" + strOrderBy
                                + strParametrosPaginacao + "'>";
                        StrNavegacao += "<font size='" + strTamanhoLetra + "'face='" + strTipoLetra + "'>" + "<img src='../Imagens/next.gif' border='0' alt='" + registros
                                + " registros seguintes'></font></a></div>";
                        StrNavegacao += "</td>";
                    } else {
                        if (RegistroAtual - 1 + contador - 1 < intTotalReg) {
                            StrNavegacao += "<td width='220'> ";
                            StrNavegacao += "<div align='left'><a href='" + que_pagina + "?Atual=" + String.valueOf(RegistroAtual + registros) + "&Direccion=Sig&orderby=" + strOrderBy
                                    + strParametrosPaginacao + "'>";
                            StrNavegacao += "<font size='" + strTamanhoLetra + "'face='" + strTipoLetra + "'>" + "<img src='../Imagens/next.gif' border='0' alt='" + registros
                                    + " registros seguintes'></font></a></div>";
                            StrNavegacao += "</td>";
                        } else {
                            StrNavegacao += "<td>\u00a0&nbsp;</td>";
                        }
                    }
                    StrNavegacao += "</tr>";
                    StrNavegacao += "</table>";
                    strcadeia += StrNavegacao;
                }
                rs.close();
            } else {
                Fazer_cadeiaSemDados();
                strcadeia = strHtml_sem_resultados;
            }
        } catch (Exception e) {
            strMensagem_nao_ha_resultados = "<P><b><font size='3' face='" + strTipoLetra + "' color='#ff1111'>Ocorreu o seguinte erro:</font></b><P>" + "<font size='" + strTamanhoLetra + "' face='"
                    + strTipoLetra + "' color='" + corTextoCabecalhoTabela + "'>" + e + "</p>";
            Fazer_cadeiaSemDados();
            //strMensagem_nao_ha_resultados = "<p><b> Se ha producido un
            // error:</b><p>"+e+"</p>";
            strcadeia = strHtml_sem_resultados;
            e.printStackTrace();
        }
        return strcadeia;
    }
}