package com.master.util;


public abstract class PaginacaoLinks {

     /**
     * Retorna indice de navegacao para consultas paginadas
     * @param String url - url da pagina 
     * @param int currPage - index da pagina corrente
     * @param int numRecInPage - numero de registros por pagina de dados
     * @param int totalRecords - numero total de registros da consult
     */
    public static String getLinks(String url, int currPage, int NumRecInPage, int totalRecords) {
        String result = "";
        int totalPages = totalRecords / NumRecInPage;

        if ((totalRecords % NumRecInPage) > 0)
            totalPages++;

        if (totalRecords > NumRecInPage) {
            // adiciona link para a pagina anterior
            if (currPage > 0) {
                result += "<A HREF=\"" + url + "&currPage=" + (currPage - 1) + "&totalRecords=" + totalRecords + "\" target='frmPrincipal'>Anterior</A> - ";
            }
            result += "Pagina " + (currPage + 1) + " de " + totalPages + " - ";
            int contPrevLoop = 0, contPrevLink = 0;
            int LinkPgIdx;
            if (currPage > 0) {
                LinkPgIdx = currPage - 5;
                while (contPrevLoop < 5) {
                    contPrevLoop++;
                    LinkPgIdx++;
                    if ((LinkPgIdx >= 0) && (LinkPgIdx != currPage)) {
                        contPrevLink++;

                        result += "<A HREF=\"" + url + "&currPage=" + (LinkPgIdx) + "&totalRecords=" + totalRecords + "\" target='frmPrincipal'>" + (LinkPgIdx + 1) + "</A> ";
                    }
                }
            } else {
                LinkPgIdx = currPage;
            }
            // numero da pagina atual
            result += " <FONT color='Red'><b>" + (currPage + 1) + "</b></FONT> ";
            int contLink = 10 - contPrevLink; // num ero de links a produzir na barra
            int contLastLink = 0;
            contLastLink++;
            LinkPgIdx++;
            while ((contLastLink < contLink) && (LinkPgIdx < totalPages)) {
                if (LinkPgIdx >= 0) {
                    result += "<A HREF=\"" + url + "&currPage=" + (LinkPgIdx) + "&totalRecords=" + totalRecords + "\" target='frmPrincipal'>" + (LinkPgIdx + 1) + "</A> ";
                }
                contLastLink++;
                LinkPgIdx++;
            }

            if ((currPage + 1) < totalPages) {
                result += "- <A HREF=\"" + url + "&currPage=" + (currPage + 1) + "&totalRecords=" + totalRecords + "\" target='frmPrincipal'>Proxima</A> ";
            }
        }
        return result;
    }
    
    public static String getLinksSubmit(int currPage, int NumRecInPage, int totalRecords) {
        String result = "";
        int totalPages = totalRecords / NumRecInPage;

        if ((totalRecords % NumRecInPage) > 0)
            totalPages++;

        if (totalRecords > NumRecInPage) {
            // adiciona link para a pagina anterior
            if (currPage > 0) {
                result += "<A href='javascript:document.forms[0].currPage.value="+(currPage - 1)+";document.forms[0].totalRecords.value="+totalRecords+"; document.forms[0].acao.value=\"L\"; document.forms[0].submit();'>Anterior</A> - ";
            }
            result += "Pagina " + (currPage + 1) + " de " + totalPages + " - ";
            int contPrevLoop = 0, contPrevLink = 0;
            int LinkPgIdx;
            if (currPage > 0) {
                LinkPgIdx = currPage - 5;
                while (contPrevLoop < 5) {
                    contPrevLoop++;
                    LinkPgIdx++;
                    if ((LinkPgIdx >= 0) && (LinkPgIdx != currPage)) {
                        contPrevLink++;

                        result += "<A href='javascript:document.forms[0].currPage.value="+(LinkPgIdx)+";document.forms[0].totalRecords.value="+totalRecords+"; document.forms[0].acao.value=\"L\"; document.forms[0].submit();'>" + (LinkPgIdx + 1) + "</A> ";
                    }
                }
            } else {
                LinkPgIdx = currPage;
            }
            // numero da pagina atual
            result += " <FONT color='Red'><b>" + (currPage + 1) + "</b></FONT> ";
            int contLink = 10 - contPrevLink; // num ero de links a produzir na barra
            int contLastLink = 0;
            contLastLink++;
            LinkPgIdx++;
            while ((contLastLink < contLink) && (LinkPgIdx < totalPages)) {
                if (LinkPgIdx >= 0) {
                    result += "<A href='javascript:document.forms[0].currPage.value="+(LinkPgIdx)+";document.forms[0].totalRecords.value="+totalRecords+"; document.forms[0].acao.value=\"L\"; document.forms[0].submit();'>" + (LinkPgIdx + 1) + "</A> ";
                }
                contLastLink++;
                LinkPgIdx++;
            }

            if ((currPage + 1) < totalPages) {
                result += "- <A href='javascript:document.forms[0].currPage.value="+(currPage + 1)+";document.forms[0].totalRecords.value="+totalRecords+"; document.forms[0].acao.value=\"L\"; document.forms[0].submit();'>Proxima</A> ";
            }
        }
        return result;
    }
}