package no.kristiania.server;

import jakarta.json.Json;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.io.StringReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Path("/products")
public class ProductEndpoint {

    private final static List<Product> products = new ArrayList<>();


    @GET
    public Response getAllProducts() throws URISyntaxException {
        var result = Json.createArrayBuilder();
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
    public Response addProducts(String body) {
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

        products.add(tmpProd);
        System.out.println("Added Product! Name:'" + tmpProd.getName() + "'");
        return Response.ok().build();

    }

    static {
        products.add(new Product(
                "acer",
                "laptop",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSOqCspEYYS3XO-De0ytx4WpAvX9OeAu_3F8vugL4ZL&s",
                "This is a good laptop",
                14955,
                15
        ));
        products.add(new Product(
                "mac air",
                "laptop",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSOqCspEYYS3XO-De0ytx4WpAvX9OeAu_3F8vugL4ZL&s",
                "best there is",
                22955,
                2
        ));
        products.add(new Product(
                "legion",
                "laptop",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSOqCspEYYS3XO-De0ytx4WpAvX9OeAu_3F8vugL4ZL&s",
                "best there is",
                22955,
                2
        ));
        products.add(new Product(
                "racer naga",
                "mouse",
                "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBwgHBgkIBwgKCgkLDRYPDQwMDRsUFRAWIB0iIiAdHx8kKDQsJCYxJx8fLT0tMTU3Ojo6Iys/RD84QzQ5OjcBCgoKDQwNGg8PGjclHyU3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3N//AABEIAFwAXAMBIgACEQEDEQH/xAAbAAEAAgMBAQAAAAAAAAAAAAAABgcEBQgDAv/EADkQAAEDAwEFBgMFCAMAAAAAAAEAAgMEBREGEiExQWEHE1FxgZEiMqEUUlRygiVDkqKxwfDxCBUk/8QAFwEBAQEBAAAAAAAAAAAAAAAAAAECA//EABoRAQEBAQEBAQAAAAAAAAAAAAABAhExIQP/2gAMAwEAAhEDEQA/ALxREQEREBERAREQEREBERAXnUzxUsEk9RI2OKNpc97jgNA5lei8qqBlVTyQSZ2XjBxxHVBW2o+1qjheafT0Lat34qXIi/SOLvoPNQW9Xm93vamnu0smeEWdhjfJrd3uM9V8a20hVWa9VU1PC2Kic8Za0nG0R8zRyaT578jwWgjmkp3GN+coMSeqrogWvDmTM4gE4ePU8fX2xv2lsu92pYop219ZSiR2GmOrIJPkDvWHVyiVuHb/AAPMLURsdTVYnhYx8gJ3OG7fz6HqqLcs+vdRULGvqKiG4U/hUANdjo9uPqCrF0vrC3aiJhi26eta3adTS8SPFp4OHl6gKitOTufIzvBGT+8cBgD8vXOP9qQVMMkexU0kjoqiF23HKw4cxw5hQXwii+g9Vs1LbXNqNiO50uGVUTdwPg9o+67HocjfjJlCAiIgIiIIl2mNp2aYqqqWamimhYTGJ5AwS+MeTzPLrhUBLcKeup9pp+JgyCRhzOjh4dRuXQfaJpaHVGn5YS3FZTtdLSvHEPx8vkeHseS5kqWmWd5kzT1zBtbTfllHlyP0Qe01SCAGnOea+WHDtnLeuViwkOaGy4je74ml24Pz15L7l7yN2zI0g9RhXg3FBVmJ4wcY5KXUNybLGGuxlVwybZ3k4UosVqvlfH31Jb5vs44zy4jjA8dp2M+mVZLfEtk+1tRdarTd7hvVtwXxHEkWd00Z+Zh/seRAKv2yXakvdrprlb5e8pqhm0w8x4g+BByCORC51uEcMcWxUXSlkk4OZSgy/wA+4LedkWr2WO+OslZLi218g7hzjuhm4DyDtw88eJWtfnZO1nO5rxfiIi5tiIiDzqJmU9PJPKSI4mF7iBncBkrl66S095uUt0YxsffSPkY1o3AOOcLqQjIwVzhr2wHSmqp6WNhbQVeaijONwBPxM/ST7FqCMVMDmtI2Q9n3Xf5uSydx9uEFbsmgA3xz7/4SN49Cs7aD271jSQjOfFamuVc3lbKsvlotJIsNphdN+JnBdjyB3/0WguN8ud0d+0K2aZg4Rl2GN8mjclVH8J3LXHirdWrvd16y45jhfMxzvXnEvaRvwrLLpDsn1Q7U2l2fan7VfREQVBPF+74X+o+ocpqqS/47GIVt+bh/flkBzn4S3L+Xjnn1V2qAiIgKLdoulWar09JTM2W10B76jkdu2ZAOBP3SNx9+SlKIOYYdNam750D9P3MSMOHf+Zxbno7GyfQrKn0rqOKEySWG47IGTswFx9hkrpREHItdlhfG9rmPacOa9pBafAg8Fqn8V1tqDS1k1FHsXi3xTuxhsu9sjfJ4wR7qqr72G1H2h77BdojCTlsNY0hzem23OfYKioIzgrIG1I5kcbXPke4NYxoJLieAAHE9FZtu7DLu+Vv/AGN4ooI+fcMdK767IVm6R7PrDpQiaigdPW4waupIc8fl5N9AM88oMHsl0hLpawvkr2BtyrnCSduc920fIzPiMknq4+CnKIoCIiAiIgIiICIiAiIgIiIP/9k=",
                "best there is",
                255,
                10
        ));
        products.add(new Product(
                "dell E200",
                "mouse",
                "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBwgHBgkIBwgKCgkLDRYPDQwMDRsUFRAWIB0iIiAdHx8kKDQsJCYxJx8fLT0tMTU3Ojo6Iys/RD84QzQ5OjcBCgoKDQwNGg8PGjclHyU3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3N//AABEIAFwAXAMBIgACEQEDEQH/xAAbAAEAAgMBAQAAAAAAAAAAAAAABgcEBQgDAv/EADkQAAEDAwEFBgMFCAMAAAAAAAEAAgMEBREGEiExQWEHE1FxgZEiMqEUUlRygiVDkqKxwfDxCBUk/8QAFwEBAQEBAAAAAAAAAAAAAAAAAAECA//EABoRAQEBAQEBAQAAAAAAAAAAAAABAhExIQP/2gAMAwEAAhEDEQA/ALxREQEREBERAREQEREBERAXnUzxUsEk9RI2OKNpc97jgNA5lei8qqBlVTyQSZ2XjBxxHVBW2o+1qjheafT0Lat34qXIi/SOLvoPNQW9Xm93vamnu0smeEWdhjfJrd3uM9V8a20hVWa9VU1PC2Kic8Za0nG0R8zRyaT578jwWgjmkp3GN+coMSeqrogWvDmTM4gE4ePU8fX2xv2lsu92pYop219ZSiR2GmOrIJPkDvWHVyiVuHb/AAPMLURsdTVYnhYx8gJ3OG7fz6HqqLcs+vdRULGvqKiG4U/hUANdjo9uPqCrF0vrC3aiJhi26eta3adTS8SPFp4OHl6gKitOTufIzvBGT+8cBgD8vXOP9qQVMMkexU0kjoqiF23HKw4cxw5hQXwii+g9Vs1LbXNqNiO50uGVUTdwPg9o+67HocjfjJlCAiIgIiIIl2mNp2aYqqqWamimhYTGJ5AwS+MeTzPLrhUBLcKeup9pp+JgyCRhzOjh4dRuXQfaJpaHVGn5YS3FZTtdLSvHEPx8vkeHseS5kqWmWd5kzT1zBtbTfllHlyP0Qe01SCAGnOea+WHDtnLeuViwkOaGy4je74ml24Pz15L7l7yN2zI0g9RhXg3FBVmJ4wcY5KXUNybLGGuxlVwybZ3k4UosVqvlfH31Jb5vs44zy4jjA8dp2M+mVZLfEtk+1tRdarTd7hvVtwXxHEkWd00Z+Zh/seRAKv2yXakvdrprlb5e8pqhm0w8x4g+BByCORC51uEcMcWxUXSlkk4OZSgy/wA+4LedkWr2WO+OslZLi218g7hzjuhm4DyDtw88eJWtfnZO1nO5rxfiIi5tiIiDzqJmU9PJPKSI4mF7iBncBkrl66S095uUt0YxsffSPkY1o3AOOcLqQjIwVzhr2wHSmqp6WNhbQVeaijONwBPxM/ST7FqCMVMDmtI2Q9n3Xf5uSydx9uEFbsmgA3xz7/4SN49Cs7aD271jSQjOfFamuVc3lbKsvlotJIsNphdN+JnBdjyB3/0WguN8ud0d+0K2aZg4Rl2GN8mjclVH8J3LXHirdWrvd16y45jhfMxzvXnEvaRvwrLLpDsn1Q7U2l2fan7VfREQVBPF+74X+o+ocpqqS/47GIVt+bh/flkBzn4S3L+Xjnn1V2qAiIgKLdoulWar09JTM2W10B76jkdu2ZAOBP3SNx9+SlKIOYYdNam750D9P3MSMOHf+Zxbno7GyfQrKn0rqOKEySWG47IGTswFx9hkrpREHItdlhfG9rmPacOa9pBafAg8Fqn8V1tqDS1k1FHsXi3xTuxhsu9sjfJ4wR7qqr72G1H2h77BdojCTlsNY0hzem23OfYKioIzgrIG1I5kcbXPke4NYxoJLieAAHE9FZtu7DLu+Vv/AGN4ooI+fcMdK767IVm6R7PrDpQiaigdPW4waupIc8fl5N9AM88oMHsl0hLpawvkr2BtyrnCSduc920fIzPiMknq4+CnKIoCIiAiIgIiICIiAiIgIiIP/9k=",
                "best there is",
                255,
                10
        ));
        products.add(new Product(
                "RTX3090",
                "gpu",
                "http://storage-asset.msi.com/global/picture/image/feature/vga/NVIDIA/VGA-2020/image/vga-body.png",
                "best there is",
                15000,
                100
        ));
        products.add(new Product(
                "RTX4090",
                "gpu",
                "http://storage-asset.msi.com/global/picture/image/feature/vga/NVIDIA/VGA-2020/image/vga-body.png",
                "best there is",
                25500,
                1
        ));

    }
}
