package yang.practice.springresttemplatedemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yang.practice.springresttemplatedemo.model.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private List<Product> products;

    public ProductController() {
        this.products = new ArrayList<>();
        this.products.add(new Product("Iphone", "Apple", 34000, 7));
        this.products.add(new Product("MAC", "Apple", 67000.23, 5));
        this.products.add(new Product("Apple Watch", "Apple", 1000.67, 10));
    }

    @GetMapping(value = "/{id}")
    public Product fetchProducts(@PathVariable("id") String productId) {
        return products.get(1);
    }

    @GetMapping()
    public List<Product> fetchProducts() {
        return products;
    }

    @PostMapping()
    public Product createProduct(@RequestBody Product product) {

        // Create product with ID;
        String productID = UUID.randomUUID().toString();
        product.setId(productID);
        products.add(product);
        return product;
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable String id, @RequestBody Product product) {
        for (Product p : products) {
            if (id.equals(p.getId())) {
                p.setName(product.getName());
                p.setBrand(product.getBrand());
                p.setPrice(product.getPrice());
                p.setQuantity(product.getQuantity());
            }
        }
        return product;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable String id) {
        products.remove(1);
        return ResponseEntity.ok().build();
    }
}
