package es.sneakerwebshop.repository;

import es.sneakerwebshop.entity.Orderlines;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderlineRepository extends JpaRepository<Orderlines, Integer> {
}
