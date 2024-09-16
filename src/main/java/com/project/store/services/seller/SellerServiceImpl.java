package com.project.store.services.seller;

import com.project.store.entities.Seller;
import com.project.store.exceptions.ObjectCreationException;
import com.project.store.exceptions.SellerNotFoundException;
import com.project.store.repositories.SellerRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SellerServiceImpl implements SellerService{
    private SellerRepository sellerRepository;

    public SellerServiceImpl(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Override
    public Seller findSellerById(Long sellerId){
        Optional<Seller> seller = sellerRepository.findById(sellerId);
        if(seller.isPresent()){
            return seller.get();
        }
        throw new SellerNotFoundException("No existe ningún vendedor con id " + sellerId);
    }

    @Override
    public List<Seller> findAllSellers(){
        List<Seller> sellers = sellerRepository.findAll();
        if(!sellers.isEmpty()){
            return sellers;
        }
        throw new SellerNotFoundException("No se encontraron vendedores");
    }

    @Override
    public Seller createSeller(Seller seller){
        try{
            return sellerRepository.save(seller);
        }
        catch(Exception e){
            throw new ObjectCreationException("Error al crear el vendedor: " + e.getMessage());
        }
    }


    @Override
    public boolean deleteSeller(Long sellerId){
        if(sellerRepository.existsById(sellerId)){
            try{
                sellerRepository.deleteById(sellerId);
                return true;
            } catch(DataIntegrityViolationException e){
                System.out.println("El vendedor con id " + sellerId + " tiene ventas existentes");
                throw new DataIntegrityViolationException("Error borrando el vendedor con id " + sellerId);
            }
        }
        throw new SellerNotFoundException("No existe el vendedor con el id " + sellerId);
    }

    @Override
    public Double calculateTotalCommissionOfSeller(Long sellerId, LocalDate dateFrom, LocalDate dateTo){
        try{
            Seller seller = findSellerById(sellerId);
            if(seller == null){
                throw new SellerNotFoundException("No existe ningún vendedor con id " + sellerId);
            }
            if (dateFrom.isAfter(dateTo)) {
                throw new IllegalArgumentException("La fecha de inicio no puede ser una fecha mayor a la fecha de fin");
            }
            Double totalCommission = sellerRepository.calculateTotalCommissionOfSeller(sellerId, dateFrom, dateTo);
            return totalCommission;
        } catch (Exception e){
            throw new RuntimeException("Error al calcular la comisión total del vendedor ", e);
        }
    }

}
