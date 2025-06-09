// src/main/java/com/socratesadvice/kata/lunarcalapidemo/controller/ProductController.java
package com.socratesadvice.kata.lunarcalapidemo.controller; // <-- IMPORTANT: Adjust this package

import com.socratesadvice.kata.lunarcalapidemo.model.Product; // <-- IMPORTANT: Adjust this import
import com.socratesadvice.kata.lunarcalapidemo.service.ProductService; // <-- IMPORTANT: Adjust this import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Marks this class as a REST controller
@RequestMapping("/api/calendar-entries") // Base URL for all calendar entry-related endpoints
public class ProductController { // You might consider renaming this to LunarCalendarEntryController for clarity

    @Autowired // Injects the ProductService
    private ProductService productService;

    // CREATE a new calendar entry
    // POST http://localhost:8080/api/calendar-entries
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED); // 201 Created
    }

    // READ all calendar entries
    // GET http://localhost:8080/api/calendar-entries
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK); // 200 OK
    }

    // READ a calendar entry by ID
    // GET http://localhost:8080/api/calendar-entries/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        return productService.getProductById(id)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK)) // 200 OK if found
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)); // 404 Not Found if not found
    }

    // UPDATE an existing calendar entry
    // PUT http://localhost:8080/api/calendar-entries/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody Product productDetails) {
        try {
            Product updatedProduct = productService.updateProduct(id, productDetails);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK); // 200 OK
        } catch (RuntimeException e) {
            // In a real application, you'd handle specific exceptions (e.g., NotFoundException)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }

    // DELETE a calendar entry
    // DELETE http://localhost:8080/api/calendar-entries/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content for successful deletion
    }
}