package com.cqrs_read.services;

import com.cqrs_read.entities.Product;

import java.util.List;

public interface ProductService {

   List<Product> getAll();

}
