package com.socratesadvice.kata.lunarcalapidemo.repository; // <-- IMPORTANT: Make sure this package matches your base package + .repository

import com.socratesadvice.kata.lunarcalapidemo.model.Product; // <-- IMPORTANT: Adjust this import to your actual model package
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository // Marks this interface as a Spring Data repository
public interface ProductRepository extends MongoRepository<Product, String> {
    // MongoRepository provides methods like save(), findById(), findAll(), deleteById(), etc.
    // The first type parameter (Product) is the entity/document type.
    // The second type parameter (String) is the type of the ID field of your entity/document.
    // You can add custom query methods here if needed, e.g.,
    // List<Product> findByNameContaining(String name);
}