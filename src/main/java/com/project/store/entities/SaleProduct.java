package com.project.store.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "sale-product")
public class SaleProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long saleProductId;

    @ManyToOne()
    @JoinColumn(name = "product_id")
    private Product product;

    public SaleProduct(Product product) {
        this.product = product;
    }

    public SaleProduct() {
    }

    public Long getSaleProductId() {
        return saleProductId;
    }

    public void setSaleProductId(Long saleProductId) {
        this.saleProductId = saleProductId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
