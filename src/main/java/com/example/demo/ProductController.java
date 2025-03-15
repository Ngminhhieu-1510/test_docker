package com.example.demo;

import com.example.demo.models.Book;
import com.example.demo.repositories.BookRepository;
import com.example.demo.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    // Danh sách tạm để demo
    private List<Product> products = new ArrayList<>();

    public ProductController() {
        // Thêm một số sản phẩm mẫu
        products.add(new Product(1, "Product A", 100.0));
        products.add(new Product(2, "Product B", 150.0));
    }
    @Autowired
    private BookService bookService;  // Inject BookService


    // Endpoint GET: Lấy danh sách sản phẩm
    @GetMapping
    public List<Product> getAllProducts() {
        return products;
    }

    // Endpoint GET: Lấy sản phẩm theo id
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return products.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    // Endpoint POST: Thêm sản phẩm mới
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        products.add(product);
        return product;
    }


    // Endpoint PUT: Cập nhật thông tin sản phẩm theo id
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product updatedProduct) {
        for (Product p : products) {
            if (p.getId() == id) {
                p.setName(updatedProduct.getName());
                p.setPrice(updatedProduct.getPrice());
                return p;
            }
        }
        return null;
    }

    // Endpoint DELETE: Xóa sản phẩm theo id
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        products.removeIf(p -> p.getId() == id);
        return "Deleted product with id " + id;
    }


}

// Lớp Product dùng làm Data Transfer Object (DTO)
class Product {
    private int id;
    private String name;
    private double price;

    public Product() {}

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // Getter & Setter
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}

