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

    private UserService userService;

    public OrderService(OrderRepository orderRepository, OrderlineRepository orderlineRepository, UserService userService) {
        this.orderRepository = orderRepository;
        this.orderlineRepository = orderlineRepository;
        this.userService = userService;
    }

    // Make order


    // view order , admin
    public List<Order> getUserOrders(int userId) {
        return orderRepository.findOrdersByUserId(userId);
    }

    //users gets orders and view button in account oage
    public List<Order> getMyOrders() {
        return orderRepository.findOrdersByUserId(userService.getUserId());
    }



    // get order details, with order id, Admin and user
    //use order id to get order detail
    public List<Orderlines> getOrderDetails(int orderId) {
        return orderlineRepository.findOrderLinesByOrderId(orderId);
    }


    // get all orders, Admin
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }


}
