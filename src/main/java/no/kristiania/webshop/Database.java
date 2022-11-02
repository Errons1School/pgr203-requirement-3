package no.kristiania.webshop;

import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Database {

    public static HikariDataSource getDataSource() throws IOException {
        var properties = new Properties();
        try (var reader = new FileReader("application.properties")) {
            properties.load(reader);
        }

        var dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(properties.getProperty("jdbc.url"));
        dataSource.setUsername(properties.getProperty("jdbc.username"));
        dataSource.setPassword(properties.getProperty("jdbc.password"));

        /*var azureUser = System.getenv("DB_USER");
        if (azureUser != null) {
            dataSource.setJdbcUrl(System.getenv("DB_URL"));
            dataSource.setUsername(azureUser);
            dataSource.setPassword(System.getenv("DB_PASSWORD"));

        } else {

        }*/

        var flyway = Flyway.configure().dataSource(dataSource).load();
        flyway.migrate();
        return dataSource;
    }
}
