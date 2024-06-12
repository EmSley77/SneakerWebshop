package es.sneakerwebshop.service;
/*
 * Emanuel sleyman
 * 2024-06-12
 * Service for everything that has with searching to do
 */
import es.sneakerwebshop.entity.Product;
import es.sneakerwebshop.repository.ProductRepository;
import org.springframework.stereotype.Service;

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





}
