package es.sneakerwebshop.ui;
/*
 * Emanuel sleyman
 * 2024-06-12
 * Controller for everything that has with searching to do
 */

import es.sneakerwebshop.entity.Product;
import es.sneakerwebshop.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
public class SearchController {

    private SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("sneaker-get-search-sneaker")
    public String getSneakers(@RequestParam String search, Model model) {
        List<Product> products = searchService.searchForProducts(search);
        if (!search.isEmpty()) {
            // TODO: make this into a won method, to get products and their image
            List<String> arrImages = new ArrayList<>();
            for (Product p : products) {
                String byte64 = Base64.getEncoder().encodeToString(p.getImage());
                arrImages.add(byte64);
            }
            model.addAttribute("shoes", products);
            model.addAttribute("images", arrImages);
            return "sneaker_searchpage";
        } else {
            model.addAttribute("shoes", searchService.getAllProducts());
            return "sneaker_searchpage";
        }

    }

    @GetMapping("sneaker-category")
    public String getByCategory(String s, Model model) {
        List<Product> products = searchService.getByCategorySearch(s);
        if (!products.isEmpty()) {
            // TODO: make this into a won method, to get products and their image
            List<String> arrImages = new ArrayList<>();
            for (Product p : products) {
                String byte64 = Base64.getEncoder().encodeToString(p.getImage());
                arrImages.add(byte64);
            }
            model.addAttribute("shoes", products);
            model.addAttribute("images", arrImages);
        }
        model.addAttribute("emptyList", "there is nothing to get");
        return "sneaker_homepage";
    }


    @GetMapping("sneaker-brand")
    public String getByBrand(String s, Model model) {
        List<Product> products = searchService.getByBrandSearch(s);
        if (!products.isEmpty()) {
            // TODO: make this into a won method, to get products and their image
            List<String> arrImages = new ArrayList<>();
            for (Product p : products) {
                String byte64 = Base64.getEncoder().encodeToString(p.getImage());
                arrImages.add(byte64);
            }
            model.addAttribute("shoes", products);
            model.addAttribute("images", arrImages);

        }
        model.addAttribute("emptyBrandList", "there is nothing to get");
        return "sneaker_homepage";
    }


}
