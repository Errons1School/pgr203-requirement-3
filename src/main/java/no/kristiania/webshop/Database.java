package no.kristiania.webshop;

import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Database {

    public static HikariDataSource getDataSource() throws IOException {
        var properties = new Properties();
        FileReader reader = new FileReader("application.properties");
        var dataSource = new HikariDataSource();
        if (reader != null){
            try (reader) {
                properties.load(reader);

                dataSource.setJdbcUrl(properties.getProperty("jdbc.url"));
                dataSource.setUsername(properties.getProperty("jdbc.username"));
                dataSource.setPassword(properties.getProperty("jdbc.password"));
            }
        }
        else{
            dataSource.setJdbcUrl(System.getenv("DB_URL"));
            dataSource.setUsername(System.getenv("DB_USER"));
            dataSource.setPassword(System.getenv("DB_PASSWORD"));
        }




        /*var azureUser = System.getenv("DB_USER");
        if (azureUser != null) {


        } else {

        }*/

        var flyway = Flyway.configure().dataSource(dataSource).load();
        flyway.migrate();
        return dataSource;
    }
}
