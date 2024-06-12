package es.sneakerwebshop.ui;
/*
 * Emanuel sleyman
 * 2024-06-12
 * Controller for everything that has basket methods to do
 */
import es.sneakerwebshop.entity.Product;
import es.sneakerwebshop.service.BasketService;
import es.sneakerwebshop.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BasketController {

    private BasketService basketService;

    private SearchService searchService;

    public BasketController(BasketService basketService, SearchService searchService) {
        this.basketService = basketService;
        this.searchService = searchService;
    }

    @GetMapping("sneaker-get-basket")
    public String getBasketPage() {
        return "sneaker_basketpage";
    }

    @PostMapping("sneaker-add-basket")
    public String addToBasket(@RequestParam int id, Model model) {
        try {
            basketService.addProductToBasket(id);
            model.addAttribute("basket", basketService.getBasket());
            return "sneaker_basketpage";
        }catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("shoes", searchService.getAllProducts());
            return "sneaker_searchpage";
        }
    }
}
