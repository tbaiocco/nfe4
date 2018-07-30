/*
 * Created on 07/04/2005
 *
 */
package com.master.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/**
 * @author Tiago
 *
 */

public class RequestUtil {
    public static HttpSession getSession(HttpServletRequest request) {
        return request.getSession(true);
    }
    public static Object getSessionAttribute(HttpServletRequest request, String atributo) {
        return getSession(request).getAttribute(atributo);
    }
}