package es.sneakerwebshop.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Objects;
@Data
@NoArgsConstructor
@Entity
public class Orderlines {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "orderline_id", nullable = false)
    private int orderlineId;
    @Basic
    @Column(name = "order_id", nullable = false)
    private int orderId;
    @Basic
    @Column(name = "product_id", nullable = false)
    private int productId;
    @Basic
    @Column(name = "amount", nullable = false)
    private int amount;
    @Basic
    @Column(name = "total_cost", nullable = false)
    private int totalCost;
    @Basic
    @Column(name = "product_image", nullable = false)
    private byte[] productImage;
    @Basic
    @Column(name = "product_name", nullable = false)
    private byte[] productName;
    @Basic
    @Column(name = "order_time", nullable = false)
    private Timestamp orderTime;

}
