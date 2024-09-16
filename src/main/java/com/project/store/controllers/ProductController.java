package com.project.store.controllers;

import com.project.store.entities.Product;
import com.project.store.enums.ProductCategory;
import com.project.store.exceptions.CategoryNotFoundException;
import com.project.store.exceptions.ProductNotFoundException;
import com.project.store.services.product.ProductService;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Scanner;

@Controller
public class ProductController {

    private ProductService productService;
    private final Scanner scanner = new Scanner(System.in);


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public void createProduct(){

        String productName = getProductNameFromInput();
        Double productPrice = getProductPriceFromInput();

        ProductCategory selectedCategory = showCategories();

        Product newProduct = new Product(productName, productPrice, selectedCategory);
        productService.createProduct(newProduct);

        System.out.println("Producto creado exitosamente: " + newProduct.toString());

    }

    public void searchProductsByCategory(){
        ProductCategory selectedCateogry = showCategories();
        List<Product> productList = productService.findProductsByCategory(selectedCateogry);
        showFilteredProducts(productList);
    }

    public void searchProductsByName(){
        String productName = getProductNameFromInput();
        List<Product> productList = productService.findProductsByName(productName);
        showFilteredProducts(productList);
    }

    public void searchProductsByPriceRange(){
        System.out.println("Primero ingresará el precio desde");
        Double priceFrom = getProductPriceFromInput();
        System.out.println("Ahora el precio hasta");
        Double priceTo = getProductPriceFromInput();

        List<Product> productList = productService.findProductsByPriceRange(priceFrom, priceTo);
        showFilteredProducts(productList);
    }

    private ProductCategory showCategories(){
        System.out.println("Las categorias disponibles son: ");
        for (ProductCategory category : ProductCategory.values()) {
            System.out.println("- " + category);
        }

        ProductCategory selectedCategory = null;
        while (selectedCategory == null) {
            System.out.println("Ingrese la categoría del producto: ");
            String enteredCategory = scanner.nextLine().toUpperCase();
            try {
                selectedCategory = ProductCategory.valueOf(enteredCategory);
            } catch (IllegalArgumentException e) {
                System.out.println("La categoria ingresada no es válida, igrese una categoría de la lista");
                //throw new CategoryNotFoundException("Categoría: " + enteredCategory + " no encontrada");
            }
        }
        return selectedCategory;
    }

    private String getProductNameFromInput(){
        String productName = null;
        while(productName == null || productName.trim().isEmpty()){
            System.out.println("Ingrese el nombre del producto: ");
            productName = scanner.nextLine();
        }
        return productName;
    }

    private void showFilteredProducts(List<Product> productList){
        for(Product product: productList){
            System.out.println(product.toString());
        }
    }

    private Double getProductPriceFromInput(){
        Double productPrice = null;
        while(productPrice == null){
            System.out.println("Ingrese el precio del producto: ");
            if(scanner.hasNextDouble()){
                productPrice = scanner.nextDouble();
                scanner.nextLine();
                if(productPrice < 0){
                    System.out.println("El precio no puede ser negativo");
                    productPrice = null;
                }
            }else {
                System.out.println("El precio ingresado no es un número válido");
                scanner.next();
            }

        }
        return productPrice;
    }

    public void showAllProducts(){
        List<Product> productList = productService.findAllProducts();
        if (productList.isEmpty()){
            throw new ProductNotFoundException("No hay productos para vender");
        }
        for(Product product: productList){
            System.out.println(product.toString());
        }
    }

}
