package lesson22HW28Web.service;

import lesson22HW28Web.dto.Order;

import java.util.Collection;

public interface OrderRepository {
//3.1 Реалізувати метод отримання замовлення за ID
//3.2 Реалізувати метод отримання всіх замовлень
//3.3 Реалізувати метод додавання замовлення
    Order getOrder(Integer id);
    Collection<Order> getAllOrders();
    void addOrder(Order order);
}
