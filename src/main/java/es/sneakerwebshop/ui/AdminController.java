package es.sneakerwebshop.ui;
/*
 * Emanuel sleyman
 * 2024-06-12
 * Controller for admin
 */

import es.sneakerwebshop.entity.Product;
import es.sneakerwebshop.service.AdminService;
import es.sneakerwebshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
public class AdminController {


    private AdminService adminService;

    private ProductService productService;

    public AdminController(AdminService adminService, ProductService productService) {
        this.adminService = adminService;
        this.productService = productService;
    }

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


    //user adds a new product
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
        List<Product> allProducts = adminService.getAllProducts();
        List<String> arrImg = new ArrayList<>();
        for (Product product : allProducts) {
            String base64Img = Base64.getEncoder().encodeToString(product.getImage());
            arrImg.add(base64Img);
        }
        model.addAttribute("allProductList", allProducts);
        model.addAttribute("images", arrImg);
        model.addAttribute("result", result);
        return "sneaker_admin_allproductspage";
    }
}
