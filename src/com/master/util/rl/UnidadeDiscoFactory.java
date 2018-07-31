package com.master.util.rl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.master.iu.Cheque_ClienteBean;
import com.master.util.Excecoes;

/**
 * @author André Valadas
 */
public class UnidadeDiscoFactory {
    public static void saveToDisk(HttpServletRequest request, HttpServletResponse response) throws Excecoes {
        String operacao = request.getParameter("operacao");
        if ("malote_eletronico".equals(operacao)) {
            String arquivo = new Cheque_ClienteBean().geraMaloteEletronico(request);
            request.setAttribute("arquivo", arquivo);
        } else throw new Excecoes("Relatório não informado ou inexistente");
    }
}
