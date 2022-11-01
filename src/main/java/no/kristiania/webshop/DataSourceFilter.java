package no.kristiania.webshop;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceFilter implements Filter {
    private final WebshopEndpointConfig config;

    public DataSourceFilter(WebshopEndpointConfig config) {
        this.config = config;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)  throws IOException, ServletException {
        Connection connection = null;
        try {
            connection = config.createConnectionForRequest();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        filterChain.doFilter(servletRequest, servletResponse);
        config.cleanRequestConnection();
    }
}
