package com.tsystems.javaschool.webshop.servlets;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet that catches and process all exceptions of application.
 */
public class ErrorHandlerServlet extends HttpServlet {

    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(ErrorHandlerServlet.class);

    @Override
    protected void doGet(final HttpServletRequest req,
                         final HttpServletResponse resp)
            throws ServletException, IOException {
        // Analyze the servlet exception
        Throwable throwable = (Throwable)
                req.getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer)
                req.getAttribute("javax.servlet.error.status_code");
        String servletName = (String)
                req.getAttribute("javax.servlet.error.servlet_name");
        if (servletName == null){
            servletName = "Unknown";
        }
        String requestUri = (String)
                req.getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null){
            requestUri = "Unknown";
        }
        String message = (String)
                req.getAttribute("javax.servlet.error.message");

        if (statusCode.equals(HttpServletResponse.SC_NOT_FOUND)) {
            RequestDispatcher rd = req.getRequestDispatcher("notFound.jsp");
            rd.forward(req, resp);
        } else {
            PrintWriter out = resp.getWriter();
            String title = "Error/Exception Information";
            String docType =
                    "<!doctype html public \"-//w3c//dtd html 4.0 " +
                            "transitional//en\">\n";
            out.println(docType +
                    "<html>\n" +
                    "<head><title>" + title + "</title></head>\n" +
                    "<body bgcolor=\"#f0f0f0\">\n");

            if (throwable == null && statusCode == null){
                out.println("<h2>Error information is missing</h2>");
                out.println("Please return to the <a href=\"" +
                        resp.encodeURL("http://localhost:8080/") +
                        "\">Home Page</a>.");
            } else if (statusCode != null){
                out.println("The status code : " + statusCode);
            } else{
                out.println("<h2>Error information</h2>");
                out.println("Servlet Name : " + servletName +
                        "</br></br>");
                out.println("Exception Type : " +
                        throwable.getClass().getName() +
                        "</br></br>");
                out.println("The request URI: " + requestUri +
                        "<br><br>");
                out.println("The exception message: " +
                        throwable.getMessage());
            }
            out.println("</body>");
            out.println("</html>");
//            LOGGER.error();
//            RequestDispatcher rd = req.getRequestDispatcher("error.jsp");
//            rd.forward(req, resp);
        }
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
