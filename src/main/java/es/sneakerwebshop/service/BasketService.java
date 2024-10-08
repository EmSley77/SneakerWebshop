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
import org.springframework.ui.Model;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
@SessionScope
public class BasketService {


    private ProductRepository productRepository;

    public BasketService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Getter
    private List<Product> basket = new ArrayList<>();


    // add product to basket
    public void addProductToBasket(int productId) {

        Product product = productRepository.findProductByProductId(productId);

        if (product == null) {
            return;
        }

        boolean found = false;
        for (Product p : basket) {
            if (productId == p.getProductId()) {
                if (product.getStock() > p.getStock()) {
                    int nowAmount = p.getStock();
                    p.setStock(nowAmount + 1);
                    p.setProductCost(p.getStock() * p.getProductCost());
                    found = true;
                }
                break;
            }

        }

        //check if not found and if stock amount is higher than 1
        if (!found && product.getStock() >= 1) {
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
    public void decreaseAmountInBasket(int productId) {

        Product p = productRepository.findProductByProductId(productId);

        if (p == null || p.getStock() <= 0) {
            return;
        }

        for (Product productInBasket : basket) {

            if (productId == productInBasket.getProductId()) {
                if (productInBasket.getStock() > 1) {
                    int newAmount = productInBasket.getStock() - 1;
                    productInBasket.setStock(newAmount);
                    productInBasket.setProductCost(newAmount * p.getProductCost());
                } else {
                    basket.remove(productInBasket);
                }
                break;
            }
        }
    }


    //increase product amount, if stock is empty do not continue to increase amount....

    public void increaseAmountInBasket(int productId) {

        Product p = productRepository.findProductByProductId(productId);
        if (p == null) {
            return;
        }

        for (Product productInBasket : basket) {

            if (productId == productInBasket.getProductId()) {
                if (p.getStock() > productInBasket.getStock()) {
                    int newAmount = productInBasket.getStock() + 1;
                    productInBasket.setStock(newAmount);
                    productInBasket.setProductCost(newAmount * p.getProductCost());
                }
                //break iteration over the basket after finding product
                break;
            }
        }
    }


    public int getproductStock(int id) {
        return productRepository.findProductByProductId(id).getStock();
    }

    public void getBasket(Model model) {
        List<Product> basket = getBasket();
        if (!basket.isEmpty()) {
            List<String> arrImages = new ArrayList<>();
            for (Product p : basket) {
                String images = Base64.getEncoder().encodeToString(p.getImage());
                arrImages.add(images);
            }

            model.addAttribute("basket", basket);
            model.addAttribute("images", arrImages);

        }
    }

    //get basket total Price
    public int getBasketTotalCost() {
        int total = 0;
        for (Product product: basket) {
            total += product.getProductCost();

        }
        return total;
    }

}
