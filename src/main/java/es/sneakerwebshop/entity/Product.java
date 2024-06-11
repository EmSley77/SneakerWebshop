package es.sneakerwebshop.entity;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Objects;

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
    @Lob
    @Basic
    @Column(name = "image", nullable = false)
    private byte[] image;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProductCost() {
        return productCost;
    }

    public void setProductCost(int productCost) {
        this.productCost = productCost;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId == product.productId && productCost == product.productCost && stock == product.stock && Objects.equals(category, product.category) && Objects.equals(brand, product.brand) && Objects.equals(name, product.name) && Arrays.equals(image, product.image);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(productId, category, brand, name, productCost, stock);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }
}
