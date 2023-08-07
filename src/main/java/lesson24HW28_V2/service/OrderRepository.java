package lesson24HW28_V2.service;

import lesson24HW28_V2.dto.Order;
import lesson24HW28_V2.dto.Product;

import java.util.List;
import java.util.UUID;

public interface OrderRepository {

    Order getOrder(UUID uid);
    List<Order> getAllOrders();
    Order addOrder(Product product);
    Order editOrder(UUID uId, Product product);
    void deleteOrder(UUID uId);
    Order deleteProduct(UUID uid);
}
