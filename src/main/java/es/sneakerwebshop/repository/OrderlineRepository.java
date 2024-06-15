package es.sneakerwebshop.repository;

import es.sneakerwebshop.entity.Orderlines;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderlineRepository extends JpaRepository<Orderlines, Integer> {
    List<Orderlines> findOrderLinesByOrderId(int orderId);
}
