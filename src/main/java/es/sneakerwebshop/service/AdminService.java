package es.sneakerwebshop.service;
/*
 * Emanuel sleyman
 * 2024-06-12
 * this class is a service that is responsible for Admin users
 */

import es.sneakerwebshop.entity.Order;
import es.sneakerwebshop.entity.Product;
import es.sneakerwebshop.entity.User;
import es.sneakerwebshop.repository.OrderRepository;
import es.sneakerwebshop.repository.ProductRepository;
import es.sneakerwebshop.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class AdminService {

    private UserRepository userRepository;

    private ProductRepository productRepository;

    private OrderRepository orderRepository;

    public AdminService(UserRepository userRepository, ProductRepository productRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public String createAdminAccount(String name, String lastname, String email, String password, int telephoneNumber, String address) {
        try {

            User ifUserExists = userRepository.findByEmailAndPassword(email, password);

            if (ifUserExists != null) {
                return "User with given credentials already exists, try other credentials";
            }

            User user = new User();
            user.setName(name);
            user.setLastname(lastname);
            user.setEmail(email);
            user.setPassword(password);
            user.setTelephoneNumber(telephoneNumber);
            user.setAddress(address);
            user.setRole(1);
            user.setRegistrationDate(Date.valueOf(LocalDate.now()));

            userRepository.save(user);
            return "Admin added";

        } catch (Exception e) {
            e.printStackTrace();
            return "could not create account";
        }
    }

    // get orders that are Pending
    public List<Order> getPendingOrders() {
        return orderRepository.findOrdersByOrderStatus("Pending");
    }

    // get orders that are sent
    public List<Order> getSentOrders() {
        return orderRepository.findOrdersByOrderStatus("Sent");
    }


    // get orders
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    // get admins
    public List<User> getAdminUsers() {
        return userRepository.findUsersByRole(1);
    }

    // get Users
    public List<User> getUsers() {
        return userRepository.findUsersByRole(0);
    }


    //change order status to sent
    public String orderStatusSent(int orderId) {
        try {

            Order order = orderRepository.findOrderByOrderId(orderId);
            if (order == null) {
                return "Could not find order to update status";
            }

            order.setOrderStatus("Sent");
            orderRepository.save(order);
            return "Order status changed to Sent";


        } catch (Exception e) {
            e.printStackTrace();
            return "Could not update Order status";
        }
    }


    //amdmin gets all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    //delete product,
    //TODO: may need to change on delete act in db to cascade, NEEDS TO BE TESTED! 2024.06.16
    public String deleteProduct(int productId) {
        try {
            productRepository.deleteById(productId);
            return "product deleted";
        } catch (Exception e) {
            e.printStackTrace();
            return "could not delete item";
        }
    }

    //get products refactored controller method
    public void getProducts(Model model, List<Product> allProducts) {
        if (!allProducts.isEmpty()) {
            List<String> arrImg = new ArrayList<>();
            for (Product product : allProducts) {
                String base64Img = Base64.getEncoder().encodeToString(product.getImage());
                arrImg.add(base64Img);
            }
            model.addAttribute("allProductList", allProducts);
            model.addAttribute("images", arrImg);
        }
    }

    //edit product
    public String editProduct(Integer productCost, Integer stock, Double shoeSize, MultipartFile image, int productId) {
        try {

            Product product = productRepository.findProductByProductId(productId);
            if (product == null) {
                return "cant find product to edit";
            }

            if (productCost != null) {
                product.setProductCost(productCost);
            }

            if (stock != null) {
                product.setStock(stock);
            }

            if (shoeSize != null) {
                product.setShoeSize(shoeSize);
            }

            if (image != null && !image.isEmpty()) {
                product.setImage(image.getBytes());
            }

            productRepository.save(product);
            return "updated product";

        } catch (Exception e) {
            e.printStackTrace();
            return "could not update product";
        }
    }

    //get product after editing
    public void getProductsAfterEditing(Model model) {
        List<Product> allProducts = getAllProducts();
        List<String> arrImg = new ArrayList<>();
        for (Product product : allProducts) {
            String base64Img = Base64.getEncoder().encodeToString(product.getImage());
            arrImg.add(base64Img);
        }
        model.addAttribute("allProductList", allProducts);
        model.addAttribute("images", arrImg);
    }


}
