package es.sneakerwebshop.ui;
/*
 * Emanuel sleyman
 * 2024-06-12
 * Controller for products
 */

import es.sneakerwebshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

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


}
