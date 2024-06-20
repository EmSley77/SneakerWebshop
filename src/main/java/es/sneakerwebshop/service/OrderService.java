package es.sneakerwebshop.service;
/*
 * Emanuel sleyman
 * 2024-06-11
 * this service is for Order methods, ordering, viewing orders, and other
 */

import es.sneakerwebshop.entity.Order;
import es.sneakerwebshop.entity.Orderlines;
import es.sneakerwebshop.entity.Product;
import es.sneakerwebshop.entity.User;
import es.sneakerwebshop.repository.OrderRepository;
import es.sneakerwebshop.repository.OrderlineRepository;
import es.sneakerwebshop.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    //TODO: productFk was removed may need to fix that
    //TODO: user details such as address, name, lastname, number and more need to be reeadonly and updateable in confirmorderpage
    //TODO: make when ordering more realife alike and not so stale
    //TODO: view everything on last page before ordering everything
    //TODO: fix better layout in webpages
    @Transactional
    public String makeOrder(String email, String password) {

        User user = userRepository.findByEmailAndPassword(email, password);

        if (user == null) {
            return "No user found with email " + email + "and password " + password;
        }

        if (!email.equals(user.getEmail()))

        if (basketService.getBasket().isEmpty()) {
            return "Cannot set an order with an empty basket";
        }

        if (basketService.getBasketTotalCost() == 0) {
            return "Cannot set order when basket total is '0' ";
        }



        Order order = new Order();

        order.setTotalCost(basketService.getBasketTotalCost());
        order.setUserId(user.getUserId());
        order.setOrderDate(Date.valueOf(LocalDate.now()));
        order.setOrderStatus("Pending");
        order.setOrderUserAddress(user.getAddress());

        orderRepository.save(order);

        //do not break in this loop, we want to get all the products not the first only.
        for (Product p : basketService.getBasket()) {
            //create an orderline for each product
            Orderlines orderlines = new Orderlines();

            orderlines.setOrderId(order.getOrderId());
            orderlines.setOrderTime(Timestamp.valueOf(LocalDateTime.now()));
            orderlines.setTotalCost(p.getProductCost());
            orderlines.setAmount(p.getStock());
            orderlines.setProductImage(p.getImage());
            orderlines.setProductName(p.getName());
            //save after first product has been filled and move on the next product in the basket, or list
            orderlineRepository.save(orderlines);

        }

        return "Order has been successfully made";

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
