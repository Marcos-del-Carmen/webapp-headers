package listeners;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class IndexListener implements ServletContextListener, ServletRequestListener, HttpSessionListener {
    ServletContext servletContext;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        servletContext = sce.getServletContext();
        servletContext.log("....::::: Mensaje en al inicializar context del indexListener");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        servletContext.log("....::::: Mensaje en al destruir context del indexListener");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        servletContext.log("....::::: Mensaje en al destruit el request del indexListener");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        servletContext.log("....::::: Mensaje en al inicializar el request del indexListener");
        sre.getServletContext().setAttribute("nombreCompleto", "Marcos Sánchez del Carmen.");
        ServletRequestListener.super.requestInitialized(sre);
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        servletContext.log("....::::: Mensaje en al inicializar la sesion del indexListener");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        servletContext.log("....::::: Mensaje en al destruir la sesion del indexListener");
    }
}
