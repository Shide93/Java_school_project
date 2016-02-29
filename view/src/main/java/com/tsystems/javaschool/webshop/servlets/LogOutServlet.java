package com.tsystems.javaschool.webshop.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet that used to log out users.
 */
public class LogOutServlet extends HttpServlet {
    @Override
    protected final void doGet(final HttpServletRequest req,
                               final HttpServletResponse resp)
            throws ServletException, IOException {

        if (req.getSession().getAttribute("user") != null) {
            req.getSession().invalidate();
            Cookie clrUserID = new Cookie("userID", "");
            clrUserID.setMaxAge(0);
            resp.addCookie(clrUserID);
            System.out.println("blah");
            resp.sendRedirect("");
        }
    }
}
