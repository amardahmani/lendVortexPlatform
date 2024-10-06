package com.lendvortex.bankservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "cards")
@NoArgsConstructor
@Getter
@Setter
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "card_id", columnDefinition = "UUID")
    private String cardId;


    @Column(name = "user_id", nullable = false)
    private Long userId;  // Foreign key to the User entity

    @Column(name = "last4", length = 4, nullable = false)
    private String last4;

    @Column(name = "expiration_month", nullable = false)
    private int expirationMonth;

    @Column(name = "expiration_year", nullable = false)
    private int expirationYear;

    @Enumerated(EnumType.STRING)
    @Column(name = "brand", nullable = false)
    private Brand brand;

    @Column(name = "country")
    private String country;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public enum Brand {
        VISA,
        MASTERCARD,
        AMERICAN_EXPRESS,
        DISCOVER,
        DINERS_CLUB,
        JCB,
        UNION_PAY,
    }
}
