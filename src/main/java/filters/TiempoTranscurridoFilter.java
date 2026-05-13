package filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;
import java.util.logging.Logger;

@WebFilter("/*")
public class TiempoTranscurridoFilter implements Filter {
    private static final Logger logger =  Logger.getLogger("TiempoTranscurridoFilter");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        long tiempoInicio = System.currentTimeMillis();
        chain.doFilter(request, response);
        long tiempoFin = System.currentTimeMillis();

        long tiempoTotal = tiempoFin - tiempoInicio;
        logger.info("El tiempo de carga de la página es de " + tiempoTotal + " milisegundos");
    }
}
