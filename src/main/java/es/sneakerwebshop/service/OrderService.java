package es.sneakerwebshop.service;
/*
 * Emanuel sleyman
 * 2024-06-11
 * this service is for Order methods, ordering, viewing orders, and other
*/
import es.sneakerwebshop.entity.Order;
import es.sneakerwebshop.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Make order



    // view order , admin & user


    // get order details, with order id, Admin and user


    // get all orders, Admin






}
