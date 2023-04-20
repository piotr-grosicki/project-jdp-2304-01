package com.kodilla.ecommercee.domain;

import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
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


    @Column(name = "activationKey")
    private long activationKey;


    @OneToMany(
            targetEntity = OrderDetails.class,
            mappedBy = "user",
            cascade = CascadeType.ALL
    )
    private List<OrderDetails> orders;


    public User(String firstName, String lastName, boolean status, long activationKey) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.orders = new ArrayList<>();
        this.activationKey = activationKey;
    }

}
