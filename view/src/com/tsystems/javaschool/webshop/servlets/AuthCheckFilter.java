package com.tsystems.javaschool.webshop.servlets;

import com.tsystems.javaschool.webshop.dao.entities.UserEntity;
import com.tsystems.javaschool.webshop.services.api.AccountService;
import com.tsystems.javaschool.webshop.services.impl.AccountServiceImpl;

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
 */
public class AuthCheckFilter implements Filter {
    /**
     * AccountService instance
     */
    private AccountService accountService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        accountService = new AccountServiceImpl();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        System.out.println("AUTHER AAAA = "+((HttpServletRequest) request).getRequestURL());
        // if user already associated to session - pass through
        if (session.getAttribute("user") != null) {
            chain.doFilter(request, response);
            return;
        }
        //TODO: how to save unauth user cart when he close browser? - create cartFilter and set cart cookie to users!
        //if user enters site with user cookie - associate user with session
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userID")) {
                    UserEntity user = accountService.getUser(Integer.parseInt(cookie.getValue()));
                    if (user != null) {
                        req.getSession().setAttribute("user", user);
                        break;
                    }
                }
            }
        }
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
