package com.socratesadvice.kata.lunarcalapidemo.controller;

import com.socratesadvice.kata.lunarcalapidemo.model.Product;
import com.socratesadvice.kata.lunarcalapidemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/calendar-entries")
@CrossOrigin(origins = "http://localhost:5173") // Allow requests from your React app's origin
public class ProductController {

    @Autowired
    private ProductService productService;

    // CREATE a new calendar entry
    // POST http://localhost:8080/api/calendar-entries
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    // READ calendar entries
    // GET http://localhost:8080/api/calendar-entries (gets all)
    // GET http://localhost:8080/api/calendar-entries?userId={yourUserID} (gets by user)
    @GetMapping
    public ResponseEntity<List<Product>> getCalendarEntries(
            @RequestParam(required = false) String userId) { // 'userId' parameter is optional
        List<Product> products;
        if (userId != null && !userId.trim().isEmpty()) { // Check if userId parameter is provided and not empty
            products = productService.getProductsByUserId(userId);
        } else {
            products = productService.getAllProducts(); // If no userId, return all (as per your original method)
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // READ a calendar entry by ID
    // GET http://localhost:8080/api/calendar-entries/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        return productService.getProductById(id)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // UPDATE an existing calendar entry
    // PUT http://localhost:8080/api/calendar-entries/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody Product productDetails) {
        try {
            Product updatedProduct = productService.updateProduct(id, productDetails);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } catch (RuntimeException e) {
            // Log the exception for debugging on the server side
            System.err.println("Error updating product with ID " + id + ": " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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