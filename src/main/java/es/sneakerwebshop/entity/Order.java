package es.sneakerwebshop.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Objects;
@Data
@NoArgsConstructor
@Entity(name = "orders")
public class Order {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "order_id", nullable = false)
    private int orderId;
    @Basic
    @Column(name = "user_id", nullable = false)
    private int userId;
    @Basic
    @Column(name = "total_cost", nullable = false)
    private int totalCost;
    @Basic
    @Column(name = "order_date", nullable = false)
    private Date orderDate;
    @Basic
    @Column(name = "order_status", nullable = false, length = 45)
    private String orderStatus;
    @Basic
    @Column(name = "order_user_address", nullable = false, length = 45)
    private String orderUserAddress;

    public Order(int userId, int totalCost, Date orderDate, String orderStatus, String orderUserAddress) {
        this.userId = userId;
        this.totalCost = totalCost;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.orderUserAddress = orderUserAddress;
    }
}
