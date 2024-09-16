package com.project.store.entities;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long saleId;
    @Column(name = "sale_date")
    private LocalDate saleDate;
    @Column(name = "total_sale_amount")
    private Double totalSaleAmount;

    @Column(name = "seller_commission")
    private Double sellerCommission;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @NotNull
    @OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name = "sale_id")
    private List<SaleProduct> saleProductList;
    public Sale(){
    }
    public Sale(LocalDate saleDate, Double totalSaleAmount, Double sellerCommission, List<SaleProduct> saleProduct, Seller seller){
        this.sellerCommission = sellerCommission;
        this.saleDate = saleDate;
        this.totalSaleAmount = totalSaleAmount;
        this.saleProductList = saleProduct;
        this.seller = seller;
    }

    public List<SaleProduct> getSaleProductList() {
        return saleProductList;
    }

    public Double getSellerCommission() {
        return sellerCommission;
    }

    public void setSellerCommission(Double sellerCommission) {
        this.sellerCommission = sellerCommission;
    }

    public void setSaleProductList(List<SaleProduct> saleProductList) {
        this.saleProductList = saleProductList;
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Double getTotalSaleAmount() {
        return totalSaleAmount;
    }

    public void setTotalSaleAmount(Double totalSaleAmount) {
        this.totalSaleAmount = totalSaleAmount;
    }

    @Override
    public String toString() {
        String productsString = "";
        for (SaleProduct saleProduct : saleProductList) {
            productsString += saleProduct.getProduct().getProductName() + ", ";
        }
        if (!productsString.isEmpty()) {
            productsString = productsString.substring(0, productsString.length() - 2);
        }

        return "Sale {" +
                " saleId = " + saleId +
                ", saleDate = '" + saleDate + '\'' +
                ", seller = " + seller.getSellerName() +
                ", products = [ "  + productsString + " ]" +
                '}';
    }

}
