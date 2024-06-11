package es.sneakerwebshop.service;
/*
*Emanuel sleyman
*2024-06-11
*this service contains all the important methods belonging to basket
*/
import es.sneakerwebshop.entity.Product;
import es.sneakerwebshop.repository.ProductRepository;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BasketService {


    private ProductRepository productRepository;

    public BasketService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Getter
    private List<Product> basket = new ArrayList<>();


    //add product to basket...
    public void addProduct(int productId) {
        Product product = productRepository.findProductByProductId(productId);
        if (product == null) {
            return;
        }

        if (product.getStock() >= 1) {
            basket.add(product);
        }
    }


    //remove product from basket....



    //decrease product amount, if <1 delete from basket...


    //increase product amount, if stock is empty do not continue to increase amount....


}
