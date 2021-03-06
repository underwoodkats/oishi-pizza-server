package com.underwoodkats.oishipizza.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * This entity describes an Item object.
 * This entity has many to many relation with entity Order.
 * Item is a abstract definition for all elements that could be in the menu,
 * such as pizzas, beverages, desserts and others.
 */
@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "orderItems"})
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "priceDollar")
    private Double priceDollar;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private Item.Type type;

    @Column(name = "description")
    private String description;

    @Column(name = "imagePath")
    private String imagePath;

    public enum Type {
        PIZZA,
        BEVERAGE,
        DESSERT,
        OTHER
    }

    @OneToMany(
            mappedBy = "item",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private Set<OrderItem> orderItems;

    public Item(String title, Double priceDollar, Item.Type type, String description, String imagePath) {
        this.title = title;
        this.priceDollar = priceDollar;
        this.type = type;
        this.description = description;
        this.imagePath = imagePath;
    }

    public Item() {
    }
}
