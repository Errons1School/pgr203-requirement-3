package no.kristiania.webshop;

import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import no.kristiania.webshop.db.ProductDao;

import java.io.StringReader;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("/products")
public class ProductEndpoint {


    @Inject
    private ProductDao dao;



    @GET
    public Response getAllProducts() throws URISyntaxException, SQLException {
        var result = Json.createArrayBuilder();
        var products = dao.getAllProduct();
        for (var prod : products) {
            result.add(Json.createObjectBuilder()
                    .add("name", prod.getName())
                    .add("category", prod.getCategory())
                    .add("img", prod.getImg())
                    .add("description", prod.getDescription())
                    .add("price", prod.getPrice())
                    .add("stock", prod.getStock())
            );
        }

        System.out.println("Successful get request!");

        return Response.ok(result.build().toString()).build();
    }

    @POST
    public Response addProducts(String body) throws SQLException {
        var reader = new StringReader(body);
        var jsonBody = Json.createReader(reader).readObject();

        var tmpProd = new Product(
                jsonBody.getString("name"),
                jsonBody.getString("category"),
                jsonBody.getString("img"),
                jsonBody.getString("description"),
                jsonBody.getInt("price"),
                jsonBody.getInt("stock")
        );

        dao.saveProduct(tmpProd);
        System.out.println("Added Product! Name:'" + tmpProd.getName() + "'");
        return Response.ok().build();

    }

}
