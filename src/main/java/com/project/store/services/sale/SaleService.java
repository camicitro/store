package com.project.store.services.sale;

import com.project.store.entities.Sale;

import java.util.List;

public interface SaleService {

    public List<Sale> findSalesBySeller(Long sellerId);
    public Sale createSale(List<Long> productList, Long sellerId);
    public Double calculateCommission(Double totalAmount, int listSize);

}
