package es.sneakerwebshop.ui;
/*
 * Emanuel sleyman
 * 2024-06-12
 * Controller for everything that has basket methods to do
 */

import es.sneakerwebshop.entity.Product;
import es.sneakerwebshop.service.BasketService;
import es.sneakerwebshop.service.SearchService;
import es.sneakerwebshop.service.UserService;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


@Controller
public class BasketController {

    private BasketService basketService;

    private SearchService searchService;

    private UserService userService;

    public BasketController(BasketService basketService, SearchService searchService, UserService userService) {
        this.basketService = basketService;
        this.searchService = searchService;
        this.userService = userService;
    }

    @GetMapping("sneaker-get-basket")
    public String getBasketPage(Model model) {
        List<Product> basket = basketService.getBasket();
        if (!basket.isEmpty()) {
            List<String> arrImages = new ArrayList<>();
            for (Product p : basket) {
                String images = Base64.getEncoder().encodeToString(p.getImage());
                arrImages.add(images);
            }

            model.addAttribute("basket", basket);
            model.addAttribute("images", arrImages);
            return "sneaker_basketpage";
        } else {
            model.addAttribute("emptyBasket", "No items in basket");
            return "sneaker_basketpage";
        }
    }

    @PostMapping("sneaker-add-basket")
    public String addToBasket(@RequestParam int id, Model model) {
        if (basketService.getproductStock(id) >= 1) {
            basketService.addProductToBasket(id);
            basketService.getBasket(model);
            return "sneaker_basketpage";
        } else {
            model.addAttribute("shoes", searchService.getAllProducts());
            return "sneaker_searchpage";
        }
    }


    @PostMapping("sneaker-delete-basket")
    public String deleteProduct(int id, Model model) {
        basketService.removeProductFromBasket(id);
        basketService.getBasket(model);
        return "sneaker_basketpage";
    }

    @PostMapping("sneaker-increase-amount")
    public String increaseAmount(int id, Model model) {
        basketService.increaseAmountInBasket(id);
        basketService.getBasket(model);
        return "sneaker_basketpage";

    }

    @PostMapping("sneaker-decrease-amount")
    public String decreaseAmount(Integer id, Model model) {
        basketService.decreaseAmountInBasket(id);
        basketService.getBasket(model);
        return "sneaker_basketpage";

    }


    @GetMapping("sneaker-get-basket-total")
    public String getBasket(Model model) {
        basketService.getBasket(model);
        model.addAttribute("totalCost", basketService.getBasketTotalCost());
        model.addAttribute("user", userService.getAccount());
        return "sneaker_confirmbasketpage";
    }

    //TODO: begin on order service, getting a notification when comepleting successful order, admin side, view orders, orderdetails with products and such


}


