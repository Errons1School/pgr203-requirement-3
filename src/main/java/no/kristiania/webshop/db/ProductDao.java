package no.kristiania.webshop.db;

import no.kristiania.webshop.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {

    void saveProduct(Product product) throws SQLException;

    List<Product> getAllProduct() throws SQLException;
     Product getProduct(long index) throws SQLException;

}
