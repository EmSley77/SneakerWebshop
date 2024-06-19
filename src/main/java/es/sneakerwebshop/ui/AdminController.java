package es.sneakerwebshop.ui;
/*
 * Emanuel sleyman
 * 2024-06-12
 * Controller for admin
 */

import es.sneakerwebshop.entity.Order;
import es.sneakerwebshop.entity.Orderlines;
import es.sneakerwebshop.entity.Product;
import es.sneakerwebshop.entity.User;
import es.sneakerwebshop.service.AdminService;
import es.sneakerwebshop.service.OrderService;
import es.sneakerwebshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
public class AdminController {


    private AdminService adminService;

    private ProductService productService;

    private OrderService orderService;

    public AdminController(AdminService adminService, ProductService productService, OrderService orderService) {
        this.adminService = adminService;
        this.productService = productService;
        this.orderService = orderService;
    }

    // get admin page
    @GetMapping("sneaker-admin-page")
    public String getAdminPage() {
        return "sneaker_adminpage";
    }

    @PostMapping("sneaker-register-admin")
    public String registerAdmin(
            @RequestParam String name,
            @RequestParam String lastname,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam int telephoneNumber,
            @RequestParam String address,
            @RequestParam String repeatPassword,
            @RequestParam String repeatEmail,
            Model model) {

        String result = adminService.createAdminAccount(name, lastname, email, password, telephoneNumber, address);
        if (result.equals("Admin added") && repeatEmail.equals(email) && repeatPassword.equals(password)) {
            return "sneaker_adminpage";
        } else {
            model.addAttribute("result", result);
            return "sneaker_adminpage";
        }
    }


    //get all products for admin to remove or edit
    @GetMapping("sneaker-get-all-products-admin")
    public String getAllProducts(Model model) {
        List<Product> allProducts = adminService.getAllProducts();
        if (!allProducts.isEmpty()) {
            List<String> arrImg = new ArrayList<>();
            for (Product product : allProducts) {
                String base64Img = Base64.getEncoder().encodeToString(product.getImage());
                arrImg.add(base64Img);
            }
            model.addAttribute("allProductList", allProducts);
            model.addAttribute("images", arrImg);
            return "sneaker_admin_allproductspage";
        } else {
            return "sneaker_adminpage";
        }
    }


    //Admin2 adds a new product
    @PostMapping("sneaker-add-product")
    public String addProduct(
            @RequestParam String category,
            @RequestParam String brand,
            @RequestParam String name,
            @RequestParam int productCost,
            @RequestParam int stock,
            @RequestParam double shoeSize,
            @RequestParam MultipartFile image,
            Model model) {


        String result = productService.addProduct(category, brand, name, productCost, stock, shoeSize, image);
        if (result.equals("Shoe was successfully added")) {
            model.addAttribute("result", result);
            return "sneaker_adminpage";
        } else {
            model.addAttribute("result", result);
            return "sneaker_adminpage";
        }


    }

    //delete product
    @PostMapping("sneaker-admin-delete-product")
    public String deleteProduct(@RequestParam int id, Model model) {
        String result = adminService.deleteProduct(id);
        if (result.equals("product deleted")) {
            List<Product> allProducts = adminService.getAllProducts();
            adminService.getProducts(model, allProducts);
            return "sneaker_admin_allproductspage";
        } else {
            List<Product> allProducts = adminService.getAllProducts();
            adminService.getProducts(model, allProducts);
            model.addAttribute("result", result);
            return "sneaker_admin_allproductspage";

        }
    }

    //update product
    @PostMapping("sneaker-admin-update-product")
    public String updateProduct(
            @RequestParam(required = false) Integer productCost,
            @RequestParam(required = false) Integer stock,
            @RequestParam(required = false) Double shoeSize,
            @RequestParam(required = false) MultipartFile image,
            @RequestParam int productId,
            Model model) {
        String result = adminService.editProduct(productCost, stock, shoeSize, image, productId);
        adminService.getProductsAfterEditing(model);
        model.addAttribute("result", result);
        return "sneaker_admin_allproductspage";
    }

    //get Orders
    @GetMapping("sneaker-get-all-orders")
    public String getOrders(Model model) {
        List<Order> allOrders = adminService.getOrders();
        if (!allOrders.isEmpty()) {
            model.addAttribute("orderList", allOrders);
            return "sneaker_admin_orderspage";
        } else {
            return "sneaker_adminpage";
        }

    }

    @GetMapping("sneaker-get-sent-orders")
    public String getSentOrders(Model model) {
        List<Order> allOrders = adminService.getSentOrders();
        if (!allOrders.isEmpty()) {
            model.addAttribute("orderList", allOrders);
            return "sneaker_admin_orderspage";
        } else {
            return "sneaker_adminpage";
        }

    }

    @GetMapping("sneaker-get-pending-orders")
    public String getPendingOrders(Model model) {
        List<Order> allOrders = adminService.getPendingOrders();
        if (!allOrders.isEmpty()) {
            model.addAttribute("orderList", allOrders);
            return "sneaker_admin_pendingorderpage";
        } else {
            return "sneaker_adminpage";
        }
    }


    // change order status from 'pending' to 'Sent'
    @PostMapping("sneaker-admin-update-order-status")
    public String changeOrderStatus(@RequestParam int orderId, Model model) {
        String result = adminService.orderStatusSent(orderId);
        if (result.equals("Order status changed to Sent")) {
            List<Order> allOrders = adminService.getPendingOrders();
            model.addAttribute("orderList", allOrders);
            return "sneaker_admin_pendingorderpage";
        } else {
            List<Order> allOrders = adminService.getPendingOrders();
            model.addAttribute("orderList", allOrders);
            return "sneaker_admin_pendingorderpage";
        }

    }

    // get admins
    @GetMapping("sneaker-admin-get-admins")
    public String getAdmins(Model model) {
        List<User> adminList = adminService.getAdminUsers();
        if (!adminList.isEmpty()) {
            model.addAttribute("userList", adminList);
            return "sneaker_admin_getuserpage";
        } else {
            return "sneaker_adminpage";
        }

    }

    // get Users
    @GetMapping("sneaker-admin-get-users")
    public String getUsers(Model model) {
        List<User> userList = adminService.getUsers();
        if (!userList.isEmpty()) {
            model.addAttribute("userList", userList);
            return "sneaker_admin_getuserpage";
        } else {
            return "sneaker_adminpage";
        }

    }

    // get user order
    @GetMapping("sneaker-admin-get-user-orders")
    public String getuserOrders(@RequestParam int userId, Model model) {
        List<Order> orderList = orderService.getUserOrders(userId);
        if (!orderList.isEmpty()) {
            model.addAttribute("orders", orderList);
            return "sneaker_admin_userorderspage";
        } else {
            return "sneaker_adminpage";
        }
    }

    //get user order details
    @GetMapping("sneaker-admin-get-user-order-details")
    public String getUserOrderDetails(@RequestParam int orderId,  Model model) {
        List<Orderlines> orderlinesList = orderService.getOrderDetails(orderId);
        if (!orderlinesList.isEmpty()) {
            List<String> arrimg = new ArrayList<>();
            for (Orderlines orderlines : orderlinesList) {
                String base64Img = Base64.getEncoder().encodeToString(orderlines.getProductImage());
                arrimg.add(base64Img);
            }

            model.addAttribute("orderlines", orderlinesList);
            model.addAttribute("images", arrimg);
            return "sneaker_admin_userorderdetailspage";
        } else {
            return "sneaker_admin_userorderspage";
        }
    }
}
