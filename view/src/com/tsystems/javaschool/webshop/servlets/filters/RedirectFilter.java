package com.tsystems.javaschool.webshop.servlets.filters;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Redirect from pages, that not allowed in current user state
 * (e.g. sign up page when authorized).
 */
public class RedirectFilter implements Filter {
    @Override
    public void init(final FilterConfig filterConfig)
            throws ServletException {

    }

    @Override
    public final void doFilter(final ServletRequest request,
                               final ServletResponse response,
                               final FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        // if unauthorized tries to buy
        if (session.getAttribute("user") == null) {

            if (req.getRequestURI().equals("/checkout.jsp")) {
                //TODO: send some variable with text: Sign in before buying
                resp.sendRedirect("/signin.jsp");
                return;
            }

            if (req.getRequestURI().equals("/profile")) {
                resp.sendRedirect("/");
                return;
            }
        }

        // if authorized tries to login or register
        if (session.getAttribute("user") != null
                && (req.getRequestURI().equals("/signin.jsp")
                    || req.getRequestURI().equals("/signup.jsp"))) {

            resp.sendRedirect("/");
            return;
        }
        //TODO: redirect to adminLogin when user enters backend

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
