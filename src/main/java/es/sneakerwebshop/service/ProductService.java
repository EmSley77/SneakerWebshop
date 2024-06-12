package es.sneakerwebshop.service;
/*
 * Emanuel sleyman
 * 2024-06-12
 * Service for everything that has with Products to do
 */

import es.sneakerwebshop.entity.Product;
import es.sneakerwebshop.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    //add a shoe to inventory
    public String addProduct(String category, String brand, String name, int productCost, int stock, double shoeSize, MultipartFile image) {
        try {
            Product product = new Product();
            product.setName(name);
            product.setBrand(brand);
            product.setCategory(category);
            product.setProductCost(productCost);
            product.setStock(stock);
            product.setImage(image.getBytes());

            productRepository.save(product);
            return "Shoe was successfully added";
        } catch (Exception e) {
            e.printStackTrace();
            return "Could not save shoe, try again";
        }
    }

    //View all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    //delete by id in admin page when viewing all shoes-page
    public void deleteProduct(int id) {
        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
