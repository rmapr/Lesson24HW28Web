package lesson22HW28Web;

import com.google.gson.Gson;
import lesson22HW28Web.dto.Order;
import lesson22HW28Web.response.StandardResponse;
import lesson22HW28Web.response.StatusResponse;
import lesson22HW28Web.service.OrderRepository;
import lesson22HW28Web.service.OrderRepositoryImpl;

import static spark.Spark.get;
import static spark.Spark.post;

public class Main {
    public static void main(String[] args) {
        OrderRepository orderRepository = new OrderRepositoryImpl();
        Gson gson = new Gson();

        //http://localhost:4567/order
        post("/order", (request, response) -> {
            response.type("application/json");
            Order order = gson.fromJson(request.body(), Order.class);
            order.setCost();
            orderRepository.addOrder(order);
            return gson.toJson(new StandardResponse(StatusResponse.SUCCESS));
        }, gson::toJson);

        get("/order", (request, response) -> {
            response.type("application/json");
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(orderRepository.getAllOrders())));
        });

        get("/order/:id", (request, response) -> {
            response.type("application/json");
            try {
                return gson.toJson(new StandardResponse(StatusResponse.SUCCESS,
                        gson.toJsonTree(orderRepository.getOrder(Integer.parseInt(request.params(":id"))))));
            } catch (NumberFormatException e){
                System.out.println("Error parse!");
                return null;
            }
            });
        }
    }