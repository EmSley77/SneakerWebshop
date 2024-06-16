package es.sneakerwebshop.service;
/*
 * Emanuel sleyman
 * 2024-06-12
 * Service for everything that has with searching to do
 */

import es.sneakerwebshop.entity.Product;
import es.sneakerwebshop.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class SearchService {


    private ProductRepository productRepository;

    public SearchService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //search for product
    public List<Product> searchForProducts(String search) {
        return productRepository.findByNameContainingOrCategoryContainingOrBrandContaining(search, search, search);
    }

    // get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    //category search
    public List<Product> getByCategorySearch(String s) {
        return productRepository.findByCategoryContaining(s);
    }

    //Brand search
    public List<Product> getByBrandSearch(String s) {
        return productRepository.findByBrandContaining(s);
    }

    public void getProductCategory(String s, List<Product> products, Model model) {
        products = getByCategorySearch(s);
        if (!products.isEmpty()) {
            List<String> arrImages = new ArrayList<>();
            for (Product p : products) {
                String byte64 = Base64.getEncoder().encodeToString(p.getImage());
                arrImages.add(byte64);
            }
            model.addAttribute("shoes", products);
            model.addAttribute("images", arrImages);
        }
    }

    public void getProductsByBrand(Model model, List<Product> products) {
        List<String> arrImages = new ArrayList<>();
        for (Product p : products) {
            String byte64 = Base64.getEncoder().encodeToString(p.getImage());
            arrImages.add(byte64);
        }
        model.addAttribute("shoes", products);
        model.addAttribute("images", arrImages);
    }

    public void getAllProducts(Model model, List<Product> productList) {
        List<String> arrImg = new ArrayList<>();
        for (Product p : productList) {
            String byte64 = Base64.getEncoder().encodeToString(p.getImage());
            arrImg.add(byte64);
        }
        model.addAttribute("shoes", productList);
        model.addAttribute("images", arrImg);
    }


    public void getSearchProducts(Model model, List<Product> products) {
        List<String> arrImages = new ArrayList<>();
        for (Product p : products) {
            String byte64 = Base64.getEncoder().encodeToString(p.getImage());
            arrImages.add(byte64);
        }
        model.addAttribute("shoes", products);
        model.addAttribute("images", arrImages);
    }

}
