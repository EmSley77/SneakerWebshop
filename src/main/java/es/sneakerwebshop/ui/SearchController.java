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
        if (!search.isEmpty() ) {
            searchService.getSearchProducts(model, products);
            return "sneaker_searchpage";
        } else {
            List<Product> productList = searchService.getAllProducts();
            if (!productList.isEmpty()) {
                searchService.getAllProducts(model, productList);
                return "sneaker_searchpage";
            } else {
                return "sneaker_homepage";
            }
        }

    }

    @GetMapping("sneaker-category")
    public String getByCategory(String s, Model model) {
        List<Product> product = searchService.getByCategorySearch(s);
        if (!product.isEmpty()) {
            searchService.getProductCategory(s, product, model);
            return "sneaker_searchpage";
        } else {
            model.addAttribute("emptyList", "there is nothing to get");
            return "sneaker_homepage";
        }
    }


    @GetMapping("sneaker-brand")
    public String getByBrand(String s, Model model) {
        List<Product> products = searchService.getByBrandSearch(s);
        if (!products.isEmpty()) {
            searchService.getProductsByBrand(model, products);
            return "sneaker_searchpage";
        }
        model.addAttribute("emptyBrandList", "there is nothing to get");
        return "sneaker_homepage";
    }


}
