package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.domain.dto.Product;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PRODUCT_GROUP")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductGroup {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID",unique = true)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(
            targetEntity = Product.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Product> products = new ArrayList<>();

    public ProductGroup(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProductGroup(String name) {
        this.name = name;
    }
}
