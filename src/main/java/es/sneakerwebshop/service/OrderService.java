package es.sneakerwebshop.service;
/*
 * Emanuel sleyman
 * 2024-06-11
 * this service is for Order methods, ordering, viewing orders, and other
 */

import es.sneakerwebshop.entity.Order;
import es.sneakerwebshop.entity.Orderlines;
import es.sneakerwebshop.entity.User;
import es.sneakerwebshop.repository.OrderRepository;
import es.sneakerwebshop.repository.OrderlineRepository;
import es.sneakerwebshop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    private UserRepository userRepository;

    private OrderlineRepository orderlineRepository;

    private BasketService basketService;

    private UserService userService;

    public OrderService(OrderRepository orderRepository, OrderlineRepository orderlineRepository, UserService userService, UserRepository userRepository, BasketService basketService) {
        this.orderRepository = orderRepository;
        this.orderlineRepository = orderlineRepository;
        this.userService = userService;
        this.userRepository = userRepository;
        this.basketService = basketService;
    }

    // Make order
    public String makeOrder(String email, String password) {
        User user = userRepository.findByEmailAndPassword(email, password);
        if (user == null) {
            return "No user found with email " + email + "and password " + password;
        }

        Order order = new Order();

        order.setTotalCost(basketService.getBasketTotalCost());
        order.setUserId(user.getUserId());
        order.setOrderDate(Date.valueOf(LocalDate.now()));
        order.setOrderStatus("Pending");





    }

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
