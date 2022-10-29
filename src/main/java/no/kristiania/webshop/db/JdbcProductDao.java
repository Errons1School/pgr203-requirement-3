package no.kristiania.webshop.db;

import no.kristiania.webshop.Product;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcProductDao implements ProductDao{
    
    private final DataSource dataSource;

    public JdbcProductDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void saveProduct(Product product) throws SQLException {
        var connection = dataSource.getConnection();

        var statementProduct = connection.prepareStatement("""
            INSERT INTO products (nameprod, catagory, img, proddesc, price, stock)
            VALUES (?, ?, ?, ?, ?, ?);
        """, Statement.RETURN_GENERATED_KEYS);

        statementProduct.setString(1, product.getName());
        statementProduct.setString(2, product.getCategory());
        statementProduct.setString(3, product.getImg());
        statementProduct.setString(4, product.getDescription());
        statementProduct.setInt(5, product.getPrice());
        statementProduct.setInt(6, product.getStock());
        statementProduct.executeUpdate();
    }

    @Override
    public List<Product> getAllProduct() throws SQLException {
        var connection = dataSource.getConnection();

        var statementProducts = connection.prepareStatement("""
            SELECT *
            FROM products;
        """);

        List<Product> products = new ArrayList<>();

        var resultProducts = statementProducts.executeQuery();
        resultProducts.next();
        var tmpProduct = new Product();
        tmpProduct.setName(resultProducts.getString(1));
        tmpProduct.setCategory(resultProducts.getString(2));
        tmpProduct.setImg(resultProducts.getString(3));
        tmpProduct.setDescription(resultProducts.getString(4));
        tmpProduct.setPrice(resultProducts.getInt(5));
        tmpProduct.setStock(resultProducts.getInt(6));
        products.add(tmpProduct);
        return products;
    }
    
}
