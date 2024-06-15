package es.sneakerwebshop.service;
/*
 * Emanuel sleyman
 * 2024-06-11
 * this service is for Order methods, ordering, viewing orders, and other
 */

import es.sneakerwebshop.entity.Order;
import es.sneakerwebshop.entity.Orderlines;
import es.sneakerwebshop.repository.OrderRepository;
import es.sneakerwebshop.repository.OrderlineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    private OrderlineRepository orderlineRepository;

    public OrderService(OrderRepository orderRepository, OrderlineRepository orderlineRepository) {
        this.orderRepository = orderRepository;
        this.orderlineRepository = orderlineRepository;
    }

    // Make order


    // view order , admin & user
    public List<Order> getUserOrders(int userId) {
        return orderRepository.findOrdersByUserId(userId);
    }

    // get order details, with order id, Admin and user
    public List<Orderlines> getOrderDetails(int orderId) {
        return orderlineRepository.findOrderLinesByOrderId(orderId);
    }


    // get all orders, Admin
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }


}
