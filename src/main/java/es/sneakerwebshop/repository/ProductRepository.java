package es.sneakerwebshop.repository;

import es.sneakerwebshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findProductByProductId(int productId);

    List<Product> findByNameContainingOrCategoryContainingOrBrandContaining(String search, String search1, String search2);

    List<Product> findByCategoryContaining(String s);

    List<Product> findByBrandContaining(String s);
}
