package com.rentalplatform.equipmentrental;

import com.rentalplatform.equipmentrental.model.Category;
import com.rentalplatform.equipmentrental.model.Product;
import com.rentalplatform.equipmentrental.repository.CategoryRepository;
import com.rentalplatform.equipmentrental.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class DataLoader implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public DataLoader(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Check if data already exists
        if (categoryRepository.count() > 0) {
            System.out.println("✅ Data already loaded, skipping initialization");
            return;
        }

        System.out.println("🔄 Loading initial data...");

        // Create categories
        Category kabels = createCategory("Kabels", "Alle soorten kabels voor audiovisuele apparatuur");
        Category verlichting = createCategory("Verlichting", "Lampen, spots en LED panelen");
        Category controlepanelen = createCategory("Controlepanelen", "DMX en andere controlapparatuur");

        // Create products
        createProduct("XLR Kabel 10m", "Professionele XLR kabel met vergulde connectoren", 5.00, 20, kabels);
        createProduct("USB-C Kabel", "Snelle USB-C oplaadkabel, 2 meter", 3.50, 15, kabels);
        createProduct("Stroomkabel 3m", "Rood-witte stroomkabel", 2.00, 30, kabels);
        createProduct("PAR LED 36W", "Full color LED par can, RGB", 25.00, 5, verlichting);
        createProduct("Spot 500W", "Halogenspot met 500W lamp", 40.00, 3, verlichting);
        createProduct("LED Panel 1x1m", "Dimbaar RGB LED paneel", 60.00, 2, verlichting);
        createProduct("DMX Controller 512", "512-kanaals DMX besturingseenheid", 75.00, 1, controlepanelen);
        createProduct("DMX Kabel 5pin", "5-pins DMX kabel 10 meter", 8.00, 10, controlepanelen);
        createProduct("Voetschakelaar", "Twee kanaals voetschakelaar voor DMX", 15.00, 4, controlepanelen);
        createProduct("Kaapladder 5m", "Zwarte kaapladder 5 meter", 12.00, 8, kabels);

        System.out.println("✅ Data loaded successfully!");
        System.out.println("   - Categories: " + categoryRepository.count());
        System.out.println("   - Products: " + productRepository.count());
    }

    private Category createCategory(String name, String description) {
        Category category = new Category();
        category.setName(name);
        category.setDescription(description);
        return categoryRepository.save(category);
    }

    private void createProduct(String name, String description, double price, int quantity, Category category) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setRentalPrice(BigDecimal.valueOf(price));
        product.setQuantity(quantity);
        product.setCategory(category);
        product.setAvailable(true);
        productRepository.save(product);
    }
}
