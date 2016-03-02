package com.tsystems.javaschool.webshop.servlets.filters;

import com.tsystems.javaschool.webshop.dao.entities.UserEntity;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Shide on 01.03.2016.
 */
public class BackendAuthFilter implements Filter {
    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        UserEntity user = (UserEntity) session.getAttribute("user");
        if (req.getMethod().equals("GET") && (user == null || !user.getIsAdmin())) {
            req.getRequestDispatcher("/backend/auth.jsp").forward(req, resp);
            return;
        }
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
