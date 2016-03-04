package com.tsystems.javaschool.webshop.servlets.filters;

import com.tsystems.javaschool.webshop.dao.entities.UserEntity;

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
 *  Backend auth filter.
 *
 *  Show auth form when non-admin tries to enter backend.
 */
public class BackendAuthFilter implements Filter {
    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public final void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        UserEntity user = (UserEntity) session.getAttribute("user");
        if (req.getMethod().equals("GET") && (user == null
                || !user.getIsAdmin())) {
            req.getRequestDispatcher("/backend/auth.jsp").forward(req, resp);
            return;
        }
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
