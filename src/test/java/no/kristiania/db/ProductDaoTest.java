package no.kristiania.db;

import no.kristiania.webshop.Product;
import no.kristiania.webshop.db.JdbcProductDao;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductDaoTest {

    private final JdbcProductDao dao = new JdbcProductDao(InMemoryDataSource.createTestDataSource().getConnection());

    public ProductDaoTest() throws SQLException {}

    //    We sort the arrays for Platforms because when we check for .isEqual the order of array must mach.
    @Test
    public void saveGameTest() throws SQLException {
        var product = new Product(
                "laptop", "laptop", "laptop", "laptop", 199, 1
        );

        dao.saveProduct(product);

        var dbProducts = dao.getAllProduct();

        assertThat(dbProducts.get(0))
                .usingRecursiveComparison()
                .isEqualTo(product)
                .isNotSameAs(product);
    }


}
