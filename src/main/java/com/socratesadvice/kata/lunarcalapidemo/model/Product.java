package com.socratesadvice.kata.lunarcalapidemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonProperty; // Import this

@Document(collection = "products")
@Data // Generates getters, setters, toString, equals, hashCode
@NoArgsConstructor // Generates a no-argument constructor
@AllArgsConstructor // Generates a constructor with all fields
public class Product {

    @Id // Marks this field as the document's primary identifier
    private String id;
    @JsonProperty("UserID")
    private String userId;
    private int day;
    private int month;
    private String desc;
}