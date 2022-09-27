package yang.practice.springresttemplatedemo.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private String id = UUID.randomUUID().toString();

    private String name;

    private String brand;

    private double price;

    private int quantity;

    public Product(String name, String brand, double price, int quantity) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
    }
}
