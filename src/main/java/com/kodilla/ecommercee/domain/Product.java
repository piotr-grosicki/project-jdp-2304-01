package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PRODUCT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PRICE")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_GROUP_ID")
    private ProductGroup productGroup;

    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "JOIN_PRODUCT_CART",
            joinColumns = {
                    @JoinColumn(
                    name = "PRODUCT_ID",
                    referencedColumnName = "ID")},
            inverseJoinColumns = {
                    @JoinColumn(
                    name = "CART_ID",
                    referencedColumnName = "ID")}
    )
    private List<Cart> cartList = new ArrayList<>();

}
