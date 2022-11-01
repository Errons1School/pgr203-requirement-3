package no.kristiania.webshop;

import com.zaxxer.hikari.HikariDataSource;

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

        var azureUser = System.getenv("DB_USER");
        if (azureUser != null) {
            dataSource.setJdbcUrl(System.getenv("DB_URL"));
            dataSource.setUsername(azureUser);
            dataSource.setPassword(System.getenv("DB_PASSWORD"));

        } else {
            dataSource.setJdbcUrl(properties.getProperty("jdbc.docker.url"));
            dataSource.setUsername(properties.getProperty("jdbc.docker.username"));
            dataSource.setPassword(properties.getProperty("jdbc.docker.password"));
        }

        return dataSource;
    }
}
