package com.rentalplatform.equipmentrental.service;

import com.rentalplatform.equipmentrental.model.*;
import com.rentalplatform.equipmentrental.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;
    private final CartService cartService;
    private final ProductRepository productRepository;

    public RentalService(RentalRepository rentalRepository, CartService cartService, ProductRepository productRepository) {
        this.rentalRepository = rentalRepository;
        this.cartService = cartService;
        this.productRepository = productRepository;
    }

    @Transactional
    public Rental checkout(User user) {
        Cart cart = cartService.getOrCreateCart(user);

        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        // Create a copy of cart items to avoid concurrent modification
        List<CartItem> cartItemsCopy = new ArrayList<>(cart.getItems());

        // Check stock and update quantities
        for (CartItem item : cartItemsCopy) {
            Product product = item.getProduct();
            if (product.getQuantity() < item.getQuantity()) {
                throw new RuntimeException("Insufficient stock for: " + product.getName());
            }
            product.setQuantity(product.getQuantity() - item.getQuantity());
            product.setAvailable(product.getQuantity() > 0);
            productRepository.save(product);
        }

        // Create rental
        Rental rental = new Rental();
        rental.setUser(user);

        // Convert CartItems to RentalItems BEFORE clearing cart
        List<RentalItem> rentalItems = new ArrayList<>();
        double totalPrice = 0.0;

        for (CartItem cartItem : cartItemsCopy) {
            RentalItem rentalItem = new RentalItem();
            rentalItem.setProduct(cartItem.getProduct());
            rentalItem.setQuantity(cartItem.getQuantity());
            rentalItem.setRentalStartDate(cartItem.getRentalStartDate());
            rentalItem.setRentalEndDate(cartItem.getRentalEndDate());
            rentalItem.setSubtotal(cartItem.getSubtotal());
            rentalItems.add(rentalItem);
            totalPrice += cartItem.getSubtotal();
        }

        rental.setItems(rentalItems);
        rental.setTotalPrice(BigDecimal.valueOf(totalPrice));
        rental.setStatus("ACTIVE");

        // Save rental FIRST
        Rental savedRental = rentalRepository.save(rental);

        // THEN clear cart
        cartService.clearCart(user);

        return savedRental;
    }

    public List<Rental> getUserRentals(User user) {
        return rentalRepository.findByUserId(user.getId());
    }

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    // NIEUWE METHODE
    public Rental getRentalById(Long rentalId) {
        return rentalRepository.findById(rentalId)
                .orElseThrow(() -> new RuntimeException("Rental not found"));
    }
}