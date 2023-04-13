package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name="USER")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "STATUS", nullable = false)
    private boolean status;

    @GeneratedValue
    @Column(name = "activationKey")
    private String activationKey = generateActivationKey();

    @OneToMany(
            targetEntity = Cart.class,
            mappedBy = "user",
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.REMOVE
            }
    )
    private Set<Cart> carts;

    @OneToMany(
            targetEntity = OrderDetails.class,
            mappedBy = "user",
            cascade = CascadeType.ALL
    )
    private Set<OrderDetails> orders;

    private String generateActivationKey(){
        Random random = new Random();
        return Integer.toString(random.nextInt(99999999));
    }

    public User(String firstName, String lastName, boolean status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.carts = new HashSet<>();
        this.orders = new HashSet<>();
        this.activationKey = generateActivationKey();
    }

    public User(Long id, String firstName, String lastName, boolean status, String activationKey, Set<Cart> carts, Set<OrderDetails> orders) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.activationKey = generateActivationKey();
        this.carts = carts;
        this.orders = orders;
    }

    public void addCart(Cart cart) {
        carts.add(cart);
    }
}
