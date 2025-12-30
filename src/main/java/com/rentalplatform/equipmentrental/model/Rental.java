package com.rental.equipmentrental.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rentals")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "rental_id")
    private List<CartItem> items = new ArrayList<>();

    @Column(nullable = false)
    private Double totalPrice;

    @Column(nullable = false)
    private String status = "CONFIRMED";

    @Column(nullable = false)
    private LocalDateTime rentalDate = LocalDateTime.now();
}