package com.project.store.controllers;

import com.project.store.entities.Seller;
import com.project.store.exceptions.SellerNotFoundException;
import com.project.store.services.seller.SellerService;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

@Controller
public class SellerController {
    private SellerService sellerService;
    private final Scanner scanner = new Scanner(System.in);

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    public void createSeller(){
        String sellerName = null;
        while(sellerName == null || sellerName.trim().isEmpty()){
            System.out.println("Ingrese el nombre del vendedor");
            sellerName = scanner.nextLine();
        }

        Double sellerSalary = null;
        while(sellerSalary == null){
            System.out.println("Ingrese el salario del vendedor");
            if(scanner.hasNextDouble()){
                sellerSalary = scanner.nextDouble();
                scanner.nextLine();
                if(sellerSalary < 0){
                    System.out.println("El salario no puede ser negativo");
                    sellerSalary = null;
                }
            } else{
                System.out.println("El salario ingresado no es un número válido");
                scanner.next();
            }
        }
        Seller newSeller = new Seller(sellerName, sellerSalary);
        sellerService.createSeller(newSeller);

        System.out.println("Vendedor creado exitosamente: " + newSeller.toString());
    }

    public void calculateSellerCommission(){
        System.out.println("Los vendedores existentes son: ");
        List<Seller> allSellers = sellerService.findAllSellers();
        for(Seller seller: allSellers){
            System.out.println(seller.toString());
        }
        Long sellerId = null;
        while(sellerId == null){
            System.out.println("Ingrese el id del vendedor: ");
            if(scanner.hasNextLong()){
                sellerId = scanner.nextLong();
                scanner.nextLine();
                if(sellerId < 0){
                    System.out.println("El id no puede ser negativo");
                    sellerId = null;
                }
            }else {
                System.out.println("El id ingresado no es un número válido");
                scanner.next();
            }

        }
        Seller seller = sellerService.findSellerById(sellerId);
        if(seller == null){
            throw new SellerNotFoundException("No existe ningún vendedor con id " + sellerId);
        }

        System.out.println("Ingrese el rango de fechas.");
        System.out.println("Primero la fecha de inicio");
        LocalDate dateFrom = getDateFromInput();

        System.out.println("Ahora la fecha de fin");
        LocalDate dateTo = getDateFromInput();

        Double commissionValue = sellerService.calculateTotalCommissionOfSeller(sellerId, dateFrom, dateTo);
        System.out.println("Para el vendedor: " + seller.toString());
        System.out.println("La comisión desde " + dateFrom + " hasta " + dateTo + " es: " + commissionValue);
    }

    private LocalDate getDateFromInput(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = null;
        while(date == null){
            System.out.print("Ingrese la fecha (yyyy-MM-dd): ");
            String dateStr = scanner.nextLine();
            try {
                date = LocalDate.parse(dateStr, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("El formato de fecha no es válido. Por favor, use el formato yyyy-MM-dd.");
            }
        }
        return date;
    }

    public void showAllSellers(){
        List<Seller> sellerList = sellerService.findAllSellers();
        if(sellerList.isEmpty()){
            throw new SellerNotFoundException("No hay vendedores disponibles");
        }
        for(Seller seller: sellerList){
            System.out.println(seller.toString());
        }
    }
}
