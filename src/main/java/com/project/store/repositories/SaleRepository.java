package com.project.store.repositories;

import com.project.store.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(
            value = "SELECT * FROM sales WHERE seller_id LIKE :sellerId",
            nativeQuery = true
    )
    public List<Sale> findSalesBySeller(@Param("sellerId") Long sellerId);

    /*@Query(
            value = "SELECT SUM(seller_commission) AS total_commission FROM sales " +
            "WHERE seller_id LIKE :sellerId " +
            "AND sale_date BETWEEN :dateFrom AND :dateTo",
            nativeQuery = true
    )
    public Double calculateTotalCommissionOfSeller(@Param("sellerId") Long sellerId, @Param("dateFrom") Date dateFrom, @Param("dateTo") Date dateTo);
    */
}
