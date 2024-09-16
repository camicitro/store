package com.project.store.repositories;

import com.project.store.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

    @Query(
            value = "SELECT SUM(s.seller_commission) AS total_commission "+
                    "FROM sales s "+
                    "JOIN sellers sel ON sel.seller_id = s.seller_id " +
                    "WHERE sel.seller_id = :sellerId "+
                    "AND s.sale_date BETWEEN :dateFrom AND :dateTo",
            nativeQuery = true
    )
    public Double calculateTotalCommissionOfSeller(@Param("sellerId") Long sellerId, @Param("dateFrom") LocalDate dateFrom, @Param("dateTo") LocalDate dateTo);
}
