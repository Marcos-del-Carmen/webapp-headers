package controllers;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/cabeceras-request")
public class CabeceraHttpRequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String metodoHttp = req.getMethod();
        String requestUri = req.getRequestURI();
        String requestUrl = req.getRequestURL().toString();
        String contextPath = req.getContextPath();
        String servletPath  = req.getServletPath();

        String ip = req.getLocalAddr();
        String ipCliente = req.getRemoteAddr();
        int port = req.getLocalPort();
        String scheme = req.getScheme();
        String host = req.getHeader("host");
        String url = scheme + "://" + host + contextPath + servletPath;
        String url2 = scheme + "://" + ip + port + contextPath + servletPath;

        resp.setContentType("text/html; charset=UTF-8");

        try(PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("   <head>");
            out.println("       <meta charset=\"UTF-8\">");
            out.println("       <title>Cabecera http request</title>");
            out.println("   </head>");
            out.println("   <body>");
            out.println("       <h1>Cabecera http request</h1>");
            out.println("       <ul>");
            out.println("           <li> Metodo HTTP: " + metodoHttp + " </li>");
            out.println("           <li> Request URI: " + requestUri + " </li>");
            out.println("           <li> Request URL: " + requestUrl + " </li>");
            out.println("           <li> Context PATH: " + contextPath + " </li>");
            out.println("           <li> Servlet PATH: " + servletPath + " </li>");
            out.println("           <li> IP local: " + ip + " </li>");
            out.println("           <li> IP cliente: " + ipCliente + " </li>");
            out.println("           <li> Puerto: " + port + " </li>");
            out.println("           <li> Scheme: " + scheme + " </li>");
            out.println("           <li> Host: " + host + " </li>");
            out.println("           <li> URL: " + url + " </li>");
            out.println("           <li> URL2: " + url2 + " </li>");

            Enumeration<String> headersNames = req.getHeaderNames();
            while (headersNames.hasMoreElements()) {
                String header = headersNames.nextElement();
                out.println("       <li>" + header + ":" + req.getHeader(header) + "</li>");
            }
            out.println("       </ul>");
            out.println("   </body>");
            out.println("</html>");
        }
    }
}
