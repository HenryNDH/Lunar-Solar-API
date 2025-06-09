package com.socratesadvice.kata.lunarcalapidemo.service;

import com.socratesadvice.kata.lunarcalapidemo.model.Product;
import com.socratesadvice.kata.lunarcalapidemo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // CREATE
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // READ all
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // READ by ID
    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

    // UPDATE
    public Product updateProduct(String id, Product productDetails) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            existingProduct.setDay(productDetails.getDay());
            existingProduct.setMonth(productDetails.getMonth());
            existingProduct.setYear(productDetails.getYear());
            existingProduct.setDesc(productDetails.getDesc());
            return productRepository.save(existingProduct);
        } else {
            throw new RuntimeException("Product not found with id: " + id);
        }
    }

    // DELETE
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}
