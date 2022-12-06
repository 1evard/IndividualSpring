package com.infir.autopartstore.Models;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "basket")
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull(message = "Ввод обязателен")
    @Range(min = 0, max = 500, message = "Значение от 0 до 500")
    private String quantity;

    @ManyToOne(optional = true, cascade = CascadeType.DETACH)
    private Order order;

    @ManyToOne(optional = true, cascade = CascadeType.DETACH)
    private Products productss;

    public Basket(Long id, String quantity, Order order, Products productss) {
        this.id = id;
        this.quantity = quantity;
        this.order = order;
        this.productss = productss;
    }

    public Basket() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Products getProducts() {
        return productss;
    }

    public void setProducts(Products products) {
        this.productss = products;
    }
}
