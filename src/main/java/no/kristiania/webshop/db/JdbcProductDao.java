package no.kristiania.webshop.db;

import jakarta.inject.Inject;
import no.kristiania.webshop.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcProductDao implements ProductDao{
    
    private final Connection connection;

    @Inject
    public JdbcProductDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void saveProduct(Product product) throws SQLException {

        /*try (connection) {


        }*/
        var sql = """
                INSERT INTO products (nameprod, catagory, img, proddesc, price, stock)
                VALUES (?, ?, ?, ?, ?, ?);
            """;

        try (var statementProduct = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            statementProduct.setString(1, product.getName());
            statementProduct.setString(2, product.getCategory());
            statementProduct.setString(3, product.getImg());
            statementProduct.setString(4, product.getDescription());
            statementProduct.setInt(5, product.getPrice());
            statementProduct.setInt(6, product.getStock());
            statementProduct.executeUpdate();
            try (var generatedKeys = statementProduct.getGeneratedKeys()) {
                generatedKeys.next();
                product.setId(generatedKeys.getLong(1));
            }
        }
    }

    @Override
    public List<Product> getAllProduct() throws SQLException {

            var sql = """
                SELECT *
                FROM products;
            """;

            try (var statementProducts = connection.prepareStatement(sql)) {
                List<Product> products = new ArrayList<>();

                var resultProducts = statementProducts.executeQuery();
               // resultProducts.next();
                while (resultProducts.next()){
                    var tmpProduct = new Product();
                    tmpProduct.setId(resultProducts.getLong(1));
                    tmpProduct.setName(resultProducts.getString(2));
                    tmpProduct.setCategory(resultProducts.getString(3));
                    tmpProduct.setImg(resultProducts.getString(4));
                    tmpProduct.setDescription(resultProducts.getString(5));
                    tmpProduct.setPrice(resultProducts.getInt(6));
                    tmpProduct.setStock(resultProducts.getInt(7));

                    products.add(tmpProduct);

                }

                return products;
            }


    }

}
