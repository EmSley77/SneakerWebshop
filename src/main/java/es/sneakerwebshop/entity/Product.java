package es.sneakerwebshop.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "product_id", nullable = false)
    private int productId;
    @Basic
    @Column(name = "category", nullable = false, length = 45)
    private String category;
    @Basic
    @Column(name = "brand", nullable = false, length = 45)
    private String brand;
    @Basic
    @Column(name = "name", nullable = false, length = 45)
    private String name;
    @Basic
    @Column(name = "product_cost", nullable = false)
    private int productCost;
    @Basic
    @Column(name = "stock", nullable = false)
    private int stock;
    @Basic
    @Column(name = "shoe_size", nullable = false)
    private double shoeSize;
    @Lob
    @Basic
    @Column(name = "image", nullable = false)
    private byte[] image;

    public Product(String category, String brand, String name, int productCost, int stock, double shoeSize, byte[] image) {
        this.category = category;
        this.brand = brand;
        this.name = name;
        this.productCost = productCost;
        this.stock = stock;
        this.shoeSize = shoeSize;
        this.image = image;
    }


}
