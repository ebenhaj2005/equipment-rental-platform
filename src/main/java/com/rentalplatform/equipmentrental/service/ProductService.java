package com.rentalplatform.equipmentrental.service;

import com.rentalplatform.equipmentrental.model.Category;
import com.rentalplatform.equipmentrental.model.Product;
import com.rentalplatform.equipmentrental.repository.CategoryRepository;
import com.rentalplatform.equipmentrental.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product createProduct(Product productDetails) {
        productDetails.setName(productDetails.getName());
        productDetails.setDescription(productDetails.getDescription());
        productDetails.setRentalPrice(productDetails.getRentalPrice());
        productDetails.setQuantity(productDetails.getQuantity());
        productDetails.setAvailable(productDetails.getAvailable());

        return productRepository.save(productDetails);
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product product = getProductById(id);

        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setRentalPrice(productDetails.getRentalPrice());
        product.setQuantity(productDetails.getQuantity());
        product.setAvailable(productDetails.getQuantity() > 0);

        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}