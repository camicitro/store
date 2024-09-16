package com.project.store.services.sale;

import com.project.store.entities.Product;
import com.project.store.entities.Sale;
import com.project.store.entities.SaleProduct;
import com.project.store.entities.Seller;
import com.project.store.exceptions.ObjectCreationException;
import com.project.store.exceptions.SaleNotFoundException;
import com.project.store.repositories.SaleRepository;
import com.project.store.services.product.ProductService;
import com.project.store.services.seller.SellerService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaleServiceImpl implements SaleService{
    private SaleRepository saleRepository;
    private SellerService sellerService;
    private ProductService productService;

    public SaleServiceImpl(SaleRepository saleRepository, SellerService sellerService, ProductService productService) {
        this.saleRepository = saleRepository;
        this.sellerService = sellerService;
        this.productService = productService;
    }

    @Override
    public List<Sale> findSalesBySeller(Long sellerId){
        List<Sale> saleList = saleRepository.findSalesBySeller(sellerId);
        if(!saleList.isEmpty()){
            return saleList;
        }
        throw new SaleNotFoundException("No hay ventas del vendedor con id " + sellerId);
    }

    @Override
    public Sale createSale(List<Long> productIdList, Long sellerId){
        try{
            Seller seller = sellerService.findSellerById(sellerId);
            List<SaleProduct> saleProductList = new ArrayList<>();
            LocalDate saleDate = LocalDate.now();
            Double totalAmount = 0.0;

            for(Long productId: productIdList){
                Product product = productService.findProductById(productId);
                SaleProduct saleProduct = new SaleProduct(product);
                saleProductList.add(saleProduct);
                totalAmount += product.getProductPrice();
            }

            Double sellerCommission = calculateCommission(totalAmount, saleProductList.size());

            Sale newSale = new Sale(saleDate, totalAmount, sellerCommission, saleProductList, seller);

            return saleRepository.save(newSale);
        } catch(Exception e){
            throw new ObjectCreationException("Error al crear la venta: " + e.getMessage());
        }

    }

    @Override
    public Double calculateCommission(Double totalAmount, int listSize){
        Double sellerCommission = 0.0;
        if(listSize <= 2){
            sellerCommission = totalAmount * 0.05;
        } else{
            sellerCommission = totalAmount * 0.1;
        }
        return sellerCommission;
    }

}
