package es.sneakerwebshop.service;
/*
 * Emanuel sleyman
 * 2024-06-12
 * this class is a service that is responsible for Admin users
 */

import es.sneakerwebshop.entity.Product;
import es.sneakerwebshop.entity.User;
import es.sneakerwebshop.repository.ProductRepository;
import es.sneakerwebshop.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class AdminService {

    private UserRepository userRepository;

    private ProductRepository productRepository;

    public AdminService(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
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


    //amdmin gets all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    //delete product,
    //TODO: may need to change on delete act in db to cascade
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


}
