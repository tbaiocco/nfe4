/*
 * Created on 01/12/2004
 *
 */
package com.master.util.login;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.master.ed.UsuarioED;
import com.master.util.ed.Parametro_FixoED;

/**
 * @author Tiago Sauter Lauxen
 *
 */
public class LoginFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	throws IOException, ServletException {
		if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
			String host = request.getServerName();
			int port = request.getServerPort();
			HttpServletRequest httpServletRequest = (HttpServletRequest)request;
			HttpServletResponse httpServletResponse = (HttpServletResponse)response;
			String paginaLogin = "/principal/nalthus.jsp";
			String contexto = httpServletRequest.getContextPath();
			String url = "http://" + host + ":" + port + contexto + "/";
			Parametro_FixoED.getInstancia().setServerName(host);
			Parametro_FixoED.getInstancia().setPort(port);
			Parametro_FixoED.getInstancia().setContexto(contexto);
	        paginaLogin = contexto + paginaLogin;

            if (doValida(httpServletRequest, httpServletResponse, paginaLogin)) {
				chain.doFilter(request, response);
			} else {
				String paginaRequisitada = httpServletRequest.getRequestURI();
				doRedireciona(httpServletRequest, httpServletResponse, paginaLogin, paginaRequisitada);
			}
		} else {
			 System.err.println("Erro ao acessar request!\n" +
					"Request não é do tipo HttpServletrequest\n" +
					"LoginFilter.doFilter");
			throw new ServletException("Erro ao acessar request!");
		}
	}

	public void destroy() {
	}

	public FilterConfig getFilterConfig() {
		return null;
	}

	public void setFilterConfig(FilterConfig config) {
	}

	private void doRedireciona(HttpServletRequest request, HttpServletResponse response, String pagina, String paginaRequisitada)
	throws IOException {
		HttpSession session = request.getSession();
		String host = request.getServerName();
		int port = request.getServerPort();
		String parametros = "?";
		Enumeration enumeration = request.getParameterNames();
		while (enumeration.hasMoreElements()) {
			if (!"?".equals(parametros)) {
				parametros += "&";
			}
			String parameterName = (String)enumeration.nextElement();
			parametros += parameterName + "=" + request.getParameter(parameterName);
		}
		session.setAttribute("paginaRequisitada", paginaRequisitada);
        session.setAttribute("parametros", parametros);
		if ("?".equals(parametros)) {
			parametros = "";
		}
		if (response instanceof HttpServletResponse) {
        	String url = "http://" + host + ":" + port + pagina;
        	((HttpServletResponse)response).sendRedirect(url);
        }
	}

	private boolean doValida(HttpServletRequest request, HttpServletResponse response, String paginaLogin)
	throws IOException, ServletException {
		String pagina = request.getRequestURI();
		String contexto = request.getContextPath();
		HttpSession session = request.getSession(true);
		String paginaRequisitada = ".." + pagina.substring(contexto.length());
		int pos = -1;
        String perfil = "";
        // Variáveis para checar o perfil do sistema que o usuário está acessando
        if (pagina.endsWith("/web.jsp")) {
        	pos = pagina.indexOf("/web.jsp");
        } else if (pagina.endsWith("/titulo.jsp")) {
        	pos = pagina.indexOf("/titulo.jsp");
        }
        if (pos > -1) {
        	perfil = pagina.substring(pos - 1, pos);
        }
        // Valida se o usuário está acesando a página de login, caso positivo, não deve validar nada
        if (!pagina.equals(paginaLogin)) {
        	// Valida se o usuário está logado (ao logar, é criada a variável de sessão usuario,
        	// caso esta exista, indica que o usuário está logado)
        	if (session != null && session.getAttribute("usuario") != null
        			&& (session.getAttribute("usuario") instanceof UsuarioED)) {
        		UsuarioED usuario = (UsuarioED)session.getAttribute("usuario");
        		if (perfil.equals("") || perfil.equals(usuario.getDM_Perfil())) {
        			return true;
        		} else {
//        			return false;
        			return true;
        		}
        	} else {
        		//TODO Levar o request
        		//session.setAttribute("paginaRequisitada", paginaRequisitada);
//				return false;
        		return true;
        	}
        } else {
        	return true;
        }
	}
}