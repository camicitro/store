package com.project.store.repositories;

import com.project.store.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(
            value = "SELECT * FROM products WHERE product_category = :productCategory",
            nativeQuery = true
    )
    public List<Product> findByCategory(@Param("productCategory") String productCategory);

    @Query(
            value = "SELECT * FROM products WHERE product_price BETWEEN :priceFrom AND :priceTo",
            nativeQuery = true
    )
    public List<Product> findByPriceRange(@Param("priceFrom") Double priceFrom, @Param("priceTo") Double priceTo);

    @Query(
            value = "SELECT * FROM products WHERE product_name LIKE %:productName%",
            nativeQuery = true
    )
    public List<Product> findByName(@Param("productName") String productName);
}

