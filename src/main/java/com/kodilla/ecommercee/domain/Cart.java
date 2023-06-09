package com.kodilla.ecommercee.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CART")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID",unique = true)
    private Long id;

    @OneToOne(
            cascade = CascadeType.REFRESH,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToMany(
            cascade = CascadeType.REFRESH,
            fetch = FetchType.LAZY
    )
    private List<Product> productList = new ArrayList<>();

    public Cart(User user) {
        this.user = user;
    }

    public Cart(User user, List<Product> productList) {
        this.user = user;
        this.productList = productList;
    }
}
