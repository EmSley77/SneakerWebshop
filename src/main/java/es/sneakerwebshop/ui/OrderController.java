package es.sneakerwebshop.ui;
/*
 * Emanuel sleyman
 * 2024-06-15
 * controller used for all order requests
 * TODO: need to add image , product name to make view orders more readable
 */
import es.sneakerwebshop.entity.Order;
import es.sneakerwebshop.entity.Orderlines;
import es.sneakerwebshop.entity.User;
import es.sneakerwebshop.service.OrderService;
import es.sneakerwebshop.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Base64;
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
            List<String> arrImg = new ArrayList<>();
            for (Orderlines o : orderlines) {
                String base64img = Base64.getEncoder().encodeToString(o.getProductImage());
                arrImg.add(base64img);
            }
            model.addAttribute("orderLines", orderlines);
            model.addAttribute("images",arrImg);
            return "sneaker_personalorderdetailspage";
        }
        else {
            model.addAttribute("orderList", orderService.getMyOrders());
            return "sneaker_ordersmadepage";
        }
    }

    @PostMapping("sneaker-make-order")
    public String makeOrder(@RequestParam String email, @RequestParam String password, Model model) {
        String result = orderService.makeOrder(email, password);
        User user =  userService.getAccount();
        if (result.equals("Order has been successfully made")) {
            model.addAttribute("orderresult", result);
            model.addAttribute("user", user);
            return "sneaker_orderconfirmpage";
        } else {
            model.addAttribute("orderresult", result);
            return "sneaker_confirmbasketpage";
        }
    }
}
