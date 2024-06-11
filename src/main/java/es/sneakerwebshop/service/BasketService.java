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
import java.util.Collections;
import java.util.List;

@Service
public class BasketService {


    private ProductRepository productRepository;

    public BasketService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Getter
    private List<Product> basket = new ArrayList<>();


    //add product to basket
    //if product already exists in basket just increse by 1 instead of adding a new object into list
    public void addProductToBasket(int productId) {

        Product product = productRepository.findProductByProductId(productId);

        if (product == null) {
            return;
        }

        boolean found;
        for (Product p : basket) {
            if (productId == p.getProductId()) {
                int nowAmount = p.getStock();
                p.setStock(nowAmount + 1);
                found = true;
                break;
            }
        }

        if (product.getStock() >= 1) {
            found = false;
            Product p = new Product();

            p.setBrand(product.getBrand());
            p.setCategory(product.getCategory());
            p.setImage(product.getImage());
            p.setName(product.getName());
            p.setProductCost(product.getProductCost());
            p.setStock(1);
            p.setProductId(product.getProductId());

            basket.add(p);
        }
    }


    //remove product from basket
    public void removeProductFromBasket(int productId) {

        for (Product product : basket) {
            if (productId == product.getProductId()) {
                basket.remove(product);
                break;
            }
        }
    }




    //decrease product amount, if <1 delete from basket
    public List<Product> decreaseAmountInBasket(int productId) {

        for (Product product : basket) {
            if (productId == product.getProductId() && product.getStock() > 1) {
                int newAmount = product.getStock() - 1;
                product.setStock(newAmount);
                break;

            }
            //if product amount in basket is lower or equal to 0 remove from basket
            if (product.getStock() <= 0) {
                basket.remove(product);
                break;
            }
        }
        return basket;
    }


    //increase product amount, if stock is empty do not continue to increase amount....

    public List<Product> increaseAmountInBasket(int productId) {

        Product p = productRepository.findProductByProductId(productId);

        for (Product product : basket) {

            if (productId == product.getProductId() && p.getStock() > 1) {
                product.setStock(product.getStock() + 1);
                break;
            }

            // if theres is not anymore in stock just dont add or do anything, break out of the loop
            if (product.getProductId() == productId && p.getStock() <= 0) {
                break;
            }


        }
        return basket;
    }

}
