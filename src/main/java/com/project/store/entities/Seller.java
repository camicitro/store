package com.project.store.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "sellers")
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sellerId;
    @Column(name = "seller_name")
    private String sellerName;
    @Column(name = "seller_salary")
    private Double sellerSalary;

    public Seller( String sellerName, Double sellerSalary) {
        this.sellerName = sellerName;
        this.sellerSalary = sellerSalary;
    }

    public Seller() {
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Double getSellerSalary() {
        return sellerSalary;
    }

    public void setSellerSalary(Double sellerSalary) {
        this.sellerSalary = sellerSalary;
    }

    @Override
    public String toString() {
        return " Seller {" +
                " sellerId = " + sellerId +
                ", sellerName = '" + sellerName + '\'' +
                ", sellerSalary = " + sellerSalary +
                '}';
    }
}
