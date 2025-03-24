package com.hellFire.Product_Service.repository;

import com.hellFire.Product_Service.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
