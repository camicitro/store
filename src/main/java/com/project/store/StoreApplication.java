package com.project.store;

import com.project.store.controllers.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class StoreApplication {


	public static void main(String[] args) {

		SpringApplication.run(StoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(ProductController productController, SellerController sellerController, SaleController saleController) {
		return args -> {
			Scanner scanner = new Scanner(System.in);
			boolean running = true;

			while (running) {
				showMainMenu();
				int menuOption = getUserInput();
				switch (menuOption) {
						case 1:
							productController.createProduct();
							break;
						case 2:
							sellerController.createSeller();
							break;
						case 3:
							saleController.createSale();
							break;
						case 4:
							handleProductSearch(productController);
							break;
						case 5:
							sellerController.calculateSellerCommission();
							break;
						case 6:
							running = false;
							System.out.println("Finalizando ejecución...");
							break;
						default:
							System.out.println("El número no está en la lista de opciones");
					}
			}
		};
	}

	private void showMainMenu() {
		System.out.println("*** MENÚ DE INICIO ***");
		System.out.println("Seleccione una de las siguientes opciones (ingresar el número): ");
		System.out.println("1. Crear Producto");
		System.out.println("2. Crear Vendedor");
		System.out.println("3. Crear Venta");
		System.out.println("4. Buscar Productos");
		System.out.println("5. Calcular comisión de vendedor");
		System.out.println("6. Salir");
	}

	private int getUserInput() {
		Scanner scanner = new Scanner(System.in);
		while (!scanner.hasNextInt()) {
			System.out.println("Número ingresado no válido");
			scanner.next();
		}
		return scanner.nextInt();
	}

	private void handleProductSearch(ProductController productController) {
		System.out.println("Seleccione el tipo de búsqueda a realizar (ingresar el número): ");
		System.out.println("1. Por Categoría");
		System.out.println("2. Por Nombre");
		System.out.println("3. Por Rango de Precios");

		int searchOption = getUserInput();

		switch (searchOption) {
			case 1:
				productController.searchProductsByCategory();
				break;
			case 2:
				productController.searchProductsByName();
				break;
			case 3:
				productController.searchProductsByPriceRange();
				break;
			default:
				System.out.println("Opción de búsqueda no válida");
		}
	}
}