package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "ORDER_DETAILS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "TOTAL_PRICE")
    private BigDecimal totalPrice;

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "CARD_ID")
    private Cart cart;

    @ManyToOne(
            targetEntity = User.class,
            cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "USER_ID")
    private User user;

    public OrderDetails(BigDecimal totalPrice, Cart cart, User user) {
        this.totalPrice = totalPrice;
        this.cart = cart;
        this.user = user;
    }
}
