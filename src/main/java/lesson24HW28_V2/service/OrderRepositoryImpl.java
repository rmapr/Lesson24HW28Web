package lesson24HW28_V2.service;

import lesson24HW28_V2.dto.Order;
import lesson24HW28_V2.dto.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.*;

@Setter
@Getter
@ToString
public class OrderRepositoryImpl implements OrderRepository {
    private static List<Order> orderList;

    public OrderRepositoryImpl() {
        orderList = new ArrayList<>();
    }

    @Override
    public Order getOrder(UUID uid) {
        Order order = null;
        try {
            order = orderList.stream()
                    .filter(o -> o.getUid().equals(uid))
                    .findFirst().get();
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
        return order;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderList;
    }

    @Override
    public Order addOrder(Product product) {
        Order order = new Order();
        order.setUid(UUID.randomUUID());
        order.setCreateOrder(new Timestamp(System.currentTimeMillis()));
        order.setUpdateOrder(new Timestamp(System.currentTimeMillis()));
        order.getProducts().add(product);
        order.setCost(order.getProducts().stream().mapToDouble(Product::getCost).sum());
        orderList.add(order);
        return order;
    }

    @Override
    public Order editOrder(UUID uId, Product product) {
        Order order = getOrder(uId);
        order.setUpdateOrder(new Timestamp(System.currentTimeMillis()));
        order.getProducts().add(product);
        order.setCost(order.getProducts().stream().mapToDouble(Product::getCost).sum());
        return order;
    }

    @Override
    public void deleteOrder(UUID uId) {
        Order order = getOrder(uId);
        try {
            if (orderList.remove(order)) {
                System.out.println("Order successfully delete");
            }
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }

    public Order deleteProduct(UUID uid) {
        for (Order order : orderList) {
            if (order.getProducts()
                    .removeIf(product -> product.getUid().equals(uid))) {
                System.out.println("Successfully delete Product");
                order.setUpdateOrder(new Timestamp(System.currentTimeMillis()));
                order.setCost(order.getProducts().stream().mapToDouble(Product::getCost).sum());
                System.out.println(orderList);
                return order;
            }
        }
        return null;
    }

}
