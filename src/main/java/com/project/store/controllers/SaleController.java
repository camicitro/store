package com.project.store.controllers;

import com.project.store.entities.Sale;
import com.project.store.services.sale.SaleService;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Controller
public class SaleController {
    private SaleService saleService;
    private ProductController productController;
    private SellerController sellerController;
    private final Scanner scanner = new Scanner(System.in);

    public SaleController(SaleService saleService, ProductController productController, SellerController sellerController) {
        this.saleService = saleService;
        this.productController = productController;
        this.sellerController = sellerController;
    }

    public void createSale(){
        System.out.println("El listado de productos en venta es: ");
        productController.showAllProducts();

        System.out.println("Ingrese la cantidad de productos a comprar ");
        int amount = getIntegerFromInput();

        List<Long> productIdList = new ArrayList<>();
        for(int i = 1; i <= amount; i++){
            System.out.println("Ingrese el id del producto");
            Long productId = (long) getIntegerFromInput();
            productIdList.add(productId);
        }

        System.out.println("El listado de vendedores es: ");
        sellerController.showAllSellers();
        System.out.println("Ingrese el id del vendedor");
        Long sellerId = (long) getIntegerFromInput();

        Sale newSale = saleService.createSale(productIdList, sellerId);
        System.out.println("Venta creada exitosamente: " + newSale.toString());
    }

    private Integer getIntegerFromInput(){
        Integer num = null;
        while(num == null){
            System.out.println("Ingrese un número");
            if(scanner.hasNextInt()){
                num = scanner.nextInt();
                scanner.nextLine();
                if(num < 0){
                    System.out.println("El número no puede ser negativo");
                    num = null;
                }
            } else{
                System.out.println("El número ingresado no es un número válido");
                scanner.next();
            }
        }
        return num;
    }

}
