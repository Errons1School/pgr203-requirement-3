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

        try {
            var con = config.createConnectionForRequest();
            con.setAutoCommit(false);
            //con.beginRequest();
            filterChain.doFilter(servletRequest, servletResponse);
            con.commit();
            con.close();
            //con.endRequest();


            config.cleanRequestConnection();
        } catch (SQLException e) {

        }

    }
}
