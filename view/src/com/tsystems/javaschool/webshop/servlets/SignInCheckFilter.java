package com.tsystems.javaschool.webshop.servlets;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet filter that checks is user already authorized.
 *
 * Checks is the user have authorized cookie and associates session with user.
 * Otherwise creates an unauthorized session and set cookie userId = -1.
 */
public class SignInCheckFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession();

        // if user already associated to session
        if (session.getAttribute("userID") != null) {
            if (session.getAttribute("userID").equals("-1")
                    && req.getRequestURI().equals("/checkout")) {       // if unauthorized tries to buy
                resp.sendRedirect("/signin.jsp");
                return;
            }
            chain.doFilter(request, response);
            return;
        }
        //TODO: how to save unauth user cart when he close browser??????
        //TODO: check auth user cookie and associate session
        //if user enters site first time
        if (req.getCookies() == null) {
            session.setAttribute("userID", req.getServletContext().getAttribute("UNAUTHORIZED_USER"));

        } else {
            //if user enters site repeatedly
         /*   for (Cookie cookie : req.getCookies()) {
                if (cookie.getName().equals("userID")) {
                    // req.
                }
            }*/
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
