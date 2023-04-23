package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "USER")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "IS_BLOCKED", nullable = false)
    private boolean isBlocked;

    @Column(name = "ACTIVATION_KEY_EXPIRATION")
    private LocalDateTime activationKeyExpiration;
    private UUID activationKey;

    @OneToMany(
            targetEntity = OrderDetails.class,
            mappedBy = "user",
            cascade = CascadeType.ALL
    )
    private List<OrderDetails> orders;


    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String firstName, String lastName, boolean isBlocked) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.isBlocked = isBlocked;
        this.orders = new ArrayList<>();
    }
}
