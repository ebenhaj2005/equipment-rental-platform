package com.rentalplatform.equipmentrental.service;

import com.rentalplatform.equipmentrental.model.Cart;
import com.rentalplatform.equipmentrental.model.CartItem;
import com.rentalplatform.equipmentrental.model.Product;
import com.rentalplatform.equipmentrental.model.User;
import com.rentalplatform.equipmentrental.repository.CartItemRepository;
import com.rentalplatform.equipmentrental.repository.CartRepository;
import com.rentalplatform.equipmentrental.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
    }

    public Cart getOrCreateCart(User user) {
        return cartRepository.findByUserId(user.getId())
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    newCart.setTotalPrice(0.0);
                    return cartRepository.save(newCart);
                });
    }

    // NIEUWE METHODE (alias voor getOrCreateCart)
    public Cart getCart(User user) {
        return getOrCreateCart(user);
    }

    @Transactional
    public void addToCart(User user, Long productId, Integer quantity, LocalDate startDate, LocalDate endDate) {
        Cart cart = getOrCreateCart(user);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getQuantity() < quantity) {
            throw new RuntimeException("Insufficient stock");
        }

        long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate);
        double subtotal = product.getRentalPrice().doubleValue() * quantity * daysBetween;

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItem.setRentalStartDate(startDate);
        cartItem.setRentalEndDate(endDate);
        cartItem.setSubtotal(subtotal);

        cart.getItems().add(cartItem);
        cartItemRepository.save(cartItem);

        updateCartTotal(cart);
    }

    // NIEUWE METHODE (alias voor addToCart)
    public void addItemToCart(User user, Long productId, Integer quantity, LocalDate startDate, LocalDate endDate) {
        addToCart(user, productId, quantity, startDate, endDate);
    }

    @Transactional
    public void removeFromCart(Long itemId) {
        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        Cart cart = item.getCart();
        cart.getItems().remove(item);
        cartItemRepository.delete(item);

        updateCartTotal(cart);
    }

    // NIEUWE METHODE
    public void removeItemFromCart(User user, Long itemId) {
        removeFromCart(itemId);
    }

    @Transactional
    public void clearCart(User user) {
        Cart cart = getOrCreateCart(user);

        // Delete items from database first
        if (!cart.getItems().isEmpty()) {
            List<Long> itemIds = cart.getItems().stream()
                    .map(CartItem::getId)
                    .collect(java.util.stream.Collectors.toList());

            for (Long itemId : itemIds) {
                cartItemRepository.deleteById(itemId);
            }
        }

        // Clear the collection
        cart.getItems().clear();
        cart.setTotalPrice(0.0);
        cart.setUpdatedAt(LocalDateTime.now());
        cartRepository.save(cart);
    }

    private void updateCartTotal(Cart cart) {
        Double total = cart.getItems().stream()
                .mapToDouble(CartItem::getSubtotal)
                .sum();

        cart.setTotalPrice(total);
        cart.setUpdatedAt(LocalDateTime.now());
        cartRepository.save(cart);
    }
}