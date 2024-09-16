package com.project.store.services.product;

import com.project.store.entities.Product;
import com.project.store.enums.ProductCategory;
import com.project.store.exceptions.ObjectCreationException;
import com.project.store.exceptions.ProductNotFoundException;
import com.project.store.repositories.ProductRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(Product product){
        try{
            return productRepository.save(product);
        }
        catch(Exception e){
            throw new ObjectCreationException("Error al crear el producto: " + e.getMessage());
        }
    }

    @Override
    public boolean deleteProduct(Long productId){
        if(productRepository.existsById(productId)){
            try{
                productRepository.deleteById(productId);
                return true;
            } catch(DataIntegrityViolationException e){
                System.out.println("El producto con id " + productId + " tiene ventas existentes");
                throw new DataIntegrityViolationException("Error borrando el producto con id " + productId);
            }
        }
        throw new ProductNotFoundException("No existe el producto con el id " + productId);
    }

    @Override
    public Product findProductById(Long productId){
        Optional<Product> product = productRepository.findById(productId);
        if(product.isPresent()){
            return product.get();
        }
        throw new ProductNotFoundException("No existe el producto con el id " + productId);
    }

    @Override
    public List<Product> findAllProducts(){
        List<Product> products = productRepository.findAll();
        if(!products.isEmpty()){
            return products;
        }
        throw new ProductNotFoundException("No se encontraron productos");

    }

    @Override
    public List<Product> findProductsByCategory(ProductCategory productCategory){
        String category = productCategory.name();
        List<Product> filteredProducts = productRepository.findByCategory(category);
        if(!filteredProducts.isEmpty()){
            return filteredProducts;
        }
        throw new ProductNotFoundException("No se encontraron productos para la categoría " + productCategory.name());
    }

    @Override
    public List<Product> findProductsByPriceRange(Double priceFrom, Double priceTo ){
        List<Product> filteredProducts = productRepository.findByPriceRange(priceFrom, priceTo);
        if(!filteredProducts.isEmpty()){
            return filteredProducts;
        }
        throw new ProductNotFoundException("No se encontraron productos dentro del rango de precios " + priceFrom + "-" + priceTo);

    }

    @Override
    public List<Product> findProductsByName(String productName){
        List<Product> filteredProducts = productRepository.findByName(productName);
        if(!filteredProducts.isEmpty()){
            return filteredProducts;
        }
        throw new ProductNotFoundException("No se encontraron productos con el nombre " + productName);
    }

}
