package com.project.store.services.product;

import com.project.store.entities.Product;
import com.project.store.enums.ProductCategory;

import java.util.List;

public interface ProductService {
    public Product createProduct(Product product);

    public boolean deleteProduct(Long productId);
    public Product findProductById(Long productId);
    public List<Product> findAllProducts();
    public List<Product> findProductsByCategory(ProductCategory productCategory);
    public List<Product> findProductsByPriceRange(Double priceFrom, Double priceTo);
    public List<Product> findProductsByName(String productName);
}
