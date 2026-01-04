package com.rentalplatform.equipmentrental.controller;

import com.rentalplatform.equipmentrental.dto.CartDTO;
import com.rentalplatform.equipmentrental.dto.CartItemDTO;
import com.rentalplatform.equipmentrental.dto.ProductDTO;
import com.rentalplatform.equipmentrental.model.Cart;
import com.rentalplatform.equipmentrental.model.CartItem;
import com.rentalplatform.equipmentrental.model.User;
import com.rentalplatform.equipmentrental.service.CartService;
import com.rentalplatform.equipmentrental.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:5500", "http://localhost:5500"}, allowCredentials = "true")
public class CartController {

    private final CartService cartService;
    private final UserService userService;

    public CartController(CartService cartService, UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getCart(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not authenticated");
        }

        try {
            User user = userService.getUserById(userId);
            Cart cart = cartService.getCart(user);
            CartDTO cartDTO = convertToDTO(cart);
            return ResponseEntity.ok(cartDTO);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addToCart(
            @RequestParam Long productId,
            @RequestParam Integer quantity,
            @RequestParam String startDate,
            @RequestParam String endDate,
            HttpSession session) {

        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Not authenticated");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }

        try {
            User user = userService.getUserById(userId);
            LocalDate start = LocalDate.parse(startDate);
            LocalDate end = LocalDate.parse(endDate);

            cartService.addItemToCart(user, productId, quantity, start, end);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Product added to cart");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @DeleteMapping("/remove/{itemId}")
    public ResponseEntity<?> removeFromCart(@PathVariable Long itemId, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not authenticated");
        }

        try {
            User user = userService.getUserById(userId);
            cartService.removeItemFromCart(user, itemId);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Item removed from cart");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @DeleteMapping("/clear")
    public ResponseEntity<?> clearCart(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not authenticated");
        }

        try {
            User user = userService.getUserById(userId);
            cartService.clearCart(user);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Cart cleared");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    private CartDTO convertToDTO(Cart cart) {
        CartDTO dto = new CartDTO();
        dto.setId(cart.getId());
        dto.setTotalPrice(cart.getTotalPrice());

        List<CartItemDTO> itemDTOs = cart.getItems().stream()
                .map(this::convertItemToDTO)
                .collect(Collectors.toList());
        dto.setItems(itemDTOs);

        return dto;
    }

    private CartItemDTO convertItemToDTO(CartItem item) {
        CartItemDTO dto = new CartItemDTO();
        dto.setId(item.getId());
        dto.setQuantity(item.getQuantity());
        dto.setRentalStartDate(item.getRentalStartDate());
        dto.setRentalEndDate(item.getRentalEndDate());
        dto.setSubtotal(item.getSubtotal());

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(item.getProduct().getId());
        productDTO.setName(item.getProduct().getName());
        productDTO.setDescription(item.getProduct().getDescription());
        productDTO.setRentalPrice(item.getProduct().getRentalPrice());
        dto.setProduct(productDTO);

        return dto;
    }
}