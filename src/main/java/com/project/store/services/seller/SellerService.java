package com.project.store.services.seller;

import com.project.store.entities.Seller;

import java.time.LocalDate;
import java.util.List;

public interface SellerService {

    public Seller findSellerById(Long sellerId);

    public boolean deleteSeller(Long sellerId);
    public Seller createSeller(Seller seller);

    public List<Seller> findAllSellers();

    public Double calculateTotalCommissionOfSeller(Long sellerId, LocalDate dateFrom, LocalDate dateTo);
}
