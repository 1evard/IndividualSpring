package com.infir.autopartstore.Models;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "currentBalance")
public class CurrentBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull(message = "Ввод обязателен")
    @Range(min = 0, max = 500, message = "Значение от 0 до 500")
    private String quantity;

    @OneToOne(optional = true, mappedBy = "currentBalance")
    private Products products;

    public CurrentBalance(Long id, String quantity, Products products) {
        this.id = id;
        this.quantity = quantity;
        this.products = products;
    }

    public CurrentBalance() {

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

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }
}
