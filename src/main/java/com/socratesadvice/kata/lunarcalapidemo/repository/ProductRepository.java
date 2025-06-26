package com.socratesadvice.kata.lunarcalapidemo.repository;

import com.socratesadvice.kata.lunarcalapidemo.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository; // Recommended to add @Repository

import java.util.List;

@Repository // Marks this interface as a Spring Data Repository
public interface ProductRepository extends MongoRepository<Product, String> {
 List<Product> findByUserId(String userId);
}