package no.kristiania.webshop;

import no.kristiania.webshop.db.JdbcProductDao;
import no.kristiania.webshop.db.ProductDao;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.glassfish.jersey.server.ResourceConfig;

import javax.sql.DataSource;
import java.sql.Connection;

public class WebshopEndpointConfig extends ResourceConfig {
    private ThreadLocal<Connection> requestConnection = new ThreadLocal<>();

    private DataSource dataSource;

    public WebshopEndpointConfig(DataSource dataSource) {
        super(ProductEndpoint.class);

        this.dataSource = dataSource;
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(JdbcProductDao.class).to(ProductDao.class);
                bindFactory(() -> requestConnection.get())
                        .to(Connection.class)
                        .in(RequestScoped.class);
            }
        });
    }
}
