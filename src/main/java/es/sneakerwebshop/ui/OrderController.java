package es.sneakerwebshop.ui;
/*
 * Emanuel sleyman
 * 2024-06-15
 * controller used for all order requests
 */
import es.sneakerwebshop.entity.Order;
import es.sneakerwebshop.entity.Orderlines;
import es.sneakerwebshop.service.OrderService;
import es.sneakerwebshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OrderController {

    private OrderService orderService;

    private UserService userService;

    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    //user get orders
    @GetMapping("sneaker-get-my-orders")
    public String getMyOrders(Model model) {
        List<Order> orderList = orderService.getMyOrders();
        if (!orderList.isEmpty()) {
            model.addAttribute("orderList", orderList);
            return "sneaker_ordersmadepage";
        }
        else {
            model.addAttribute("account", userService.getAccount());
            return "sneaker_accountpage";
        }
    }


    @GetMapping("sneaker-get-my-orderdetails")
    public String getMyOrderDetails(Model model, @RequestParam int orderId) {
        List<Orderlines> orderlines = orderService.getOrderDetails(orderId);

        if (!orderlines.isEmpty()) {
            model.addAttribute("orderLines", orderlines);
            return "sneaker_personalorderdetailspage";
        }
        else {
            model.addAttribute("orderList", orderService.getMyOrders());
            return "sneaker_ordersmadepage";
        }
    }
}
