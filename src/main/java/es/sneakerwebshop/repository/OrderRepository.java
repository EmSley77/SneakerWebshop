package es.sneakerwebshop.repository;

import es.sneakerwebshop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findOrdersByUserId(int userId);

    List<Order> findOrdersByStatus(String pending);

    Order findOrderByOrderId(int orderId);
}
