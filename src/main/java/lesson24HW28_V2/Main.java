package lesson24HW28_V2;

import com.google.gson.Gson;
import lesson24HW28_V2.dto.Product;
import lesson24HW28_V2.service.OrderRepositoryImpl;

import java.util.UUID;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        System.currentTimeMillis();
        OrderRepositoryImpl orderRepository = new OrderRepositoryImpl();

        //http://localhost:4567/order
        get("/order", (request, response) -> {
            response.type("application/json");
            return new Gson().toJsonTree(orderRepository.getAllOrders());
        });
        get("/order/:uuid", (request, response) -> {
            response.type("application/json");
            UUID id = UUID.fromString(request.params(":uuid"));

                return new Gson().toJsonTree(orderRepository.getOrder(id));
        });

        post("/order", (request, response) -> {
            response.type("application/json");

            Product product = new Gson().fromJson(request.body(), Product.class);
            product.setUid(UUID.randomUUID());

            return new Gson().toJsonTree(orderRepository.addOrder(product));
        });

        put("/order/:uuid", (request, response) -> {
            response.type("application/json");
            UUID id = UUID.fromString(request.params(":uuid"));

            Product product = new Gson().fromJson(request.body(), Product.class);
            product.setUid(UUID.randomUUID());

            return new Gson().toJsonTree(orderRepository.editOrder(id, product));
        });

//        delete order
        delete("/order/:uuid", (request, response) -> {
            response.type("application/json");
            UUID id = UUID.fromString(request.params(":uuid"));

            orderRepository.deleteOrder(id);
            return new Gson().toJsonTree(orderRepository);
        });

//        delete product
        delete("/order/product/:uuid", (request, response) -> {
            response.type("application/json");
            UUID pId = UUID.fromString(request.params(":uuid"));

            return new Gson().toJsonTree(orderRepository.deleteProduct(pId));
        });

    }
}
