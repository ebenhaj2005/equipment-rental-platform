package com.rentalplatform.equipmentrental.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rentals")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"password", "rentals", "cart"})
    private User user;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    private String status;

    @Column(name = "rental_date")
    private LocalDateTime rentalDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "rental_id")
    @JsonIgnoreProperties({"rental"})
    private List<RentalItem> items = new ArrayList<>();

    public Rental() {
        this.rentalDate = LocalDateTime.now();
    }

    // Getters and Setters (blijven hetzelfde)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDateTime rentalDate) {
        this.rentalDate = rentalDate;
    }

    public List<RentalItem> getItems() {
        return items;
    }

    public void setItems(List<RentalItem> items) {
        this.items = items;
    }
}