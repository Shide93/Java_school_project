package com.tsystems.javaschool.webshop.servlets.backend;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Shide on 28.02.2016.
 */
public class MainBackendServlet extends HttpServlet {
    @Override
    protected void doGet(final HttpServletRequest req,
                         final HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/backend/backend.jsp").forward(req, resp);
    }
}
