package com.project.store.entities;

import com.project.store.enums.ProductCategory;

public class Product {
    private String productId;
    private String productName;
    private Double productPrice;
    private ProductCategory productCategory;

    public Product(String productId, String productName, Double productPrice, ProductCategory productCategory) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCategory = productCategory;
    }

    public Product() {
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }
}
