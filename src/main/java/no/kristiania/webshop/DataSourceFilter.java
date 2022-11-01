package no.kristiania.webshop;

import jakarta.servlet.*;
import org.eclipse.jetty.servlet.Source;

import java.io.IOException;

public class DataSourceFilter implements Filter {
    private final WebshopEndpointConfig config;

    public DataSourceFilter(WebshopEndpointConfig config) {
        this.config = config;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }
}
